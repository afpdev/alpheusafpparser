import os
import re

def split_md_file(filepath, output_base_dir):
    filename = os.path.basename(filepath)
    doc_name = os.path.splitext(filename)[0]
    doc_dir = os.path.join(output_base_dir, doc_name)

    if not os.path.exists(doc_dir):
        os.makedirs(doc_dir)

    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    sections = []
    current_section_name = "Front_Matter"
    current_section_content = []

    # Regex to identify Chapter, Appendix, Notices, Glossary, or Index headers at start of line
    # Support both regular space and non-breaking space (\u00a0)
    chapter_regex = re.compile(r'^(Chapter[\s\u00a0]+\d+|Appendix[\s\u00a0]+[A-Z]|Notices|Glossary|Index)(\.|\s|$)', re.IGNORECASE)

    for line in lines:
        stripped_line = line.strip()
        # Heuristic to avoid TOC: TOC entries usually end with a page number and have leading dots or many dots
        is_toc = "...." in stripped_line or (re.search(r"\.\.\s+\d+$", stripped_line) and not chapter_regex.match(stripped_line))

        if chapter_regex.match(stripped_line) and not is_toc:
            # Save current section
            sections.append((current_section_name, current_section_content))

            # Start new section
            match = chapter_regex.match(stripped_line)
            # Normalize name: replace spaces and non-breaking spaces with underscores
            header_type_num = re.sub(r'[\s\u00a0]+', '_', match.group(1))
            current_section_name = header_type_num
            current_section_content = [line]
        else:
            current_section_content.append(line)

    # Append the last section
    sections.append((current_section_name, current_section_content))

    # Write sections to files
    for name, content in sections:
        if not content:
            continue

        # Clean name for filename
        clean_name = name.replace(' ', '_').replace('\u00a0', '_')
        output_filename = f"{clean_name}.md"
        output_path = os.path.join(doc_dir, output_filename)

        # Handle duplicates by appending a counter
        counter = 1
        final_path = output_path
        while os.path.exists(final_path):
            final_path = os.path.join(doc_dir, f"{clean_name}_{counter}.md")
            counter += 1

        with open(final_path, 'w', encoding='utf-8') as f:
            f.writelines(content)

    print(f"Split {filename} into {len(sections)} sections in {doc_dir}")

def main():
    md_dir = "specifications/markdown"

    for filename in os.listdir(md_dir):
        if filename.endswith(".md") and os.path.isfile(os.path.join(md_dir, filename)):
            filepath = os.path.join(md_dir, filename)
            split_md_file(filepath, md_dir)

if __name__ == "__main__":
    main()
