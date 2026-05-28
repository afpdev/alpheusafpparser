import re
import os
import sys

def parse_test_coverage():
    requirements = []
    if not os.path.exists('TEST_COVERAGE.md'):
        print("TEST_COVERAGE.md not found.")
        return []
    with open('TEST_COVERAGE.md', 'r') as f:
        for line in f:
            m = re.match(r'\|\s*\[([^\]]+)\]\(([^)]+)\)\s*\|', line)
            if m:
                base_id = m.group(1)
                file_path = m.group(2)
                requirements.append((base_id, file_path))
    return requirements

def annotate_file(base_id, file_path):
    if not os.path.exists(file_path):
        print(f"File not found: {file_path}")
        return

    with open(file_path, 'r') as f:
        content = f.read()

    # Normalize line endings to \n
    content = content.replace('\r\n', '\n')
    lines = content.split('\n')

    req_counter = 1
    id_pattern = re.compile(rf'\[{re.escape(base_id)}-\d{{3}}\]')

    new_lines = []
    has_separator = False

    for i, line in enumerate(lines):
        stripped = line.strip()

        # Skip empty lines
        if not stripped:
            new_lines.append(line)
            continue

        # Headers, rules, copyright
        if stripped.startswith('#') or stripped == '---' or 'Copyright ©' in stripped:
            new_lines.append(line)
            continue

        # Table detection
        if '|' in line:
            if stripped.startswith('| :---') or stripped.startswith('|:---'):
                has_separator = True
                new_lines.append(line)
                continue

            # Check if it's a data row (heuristically)
            if not id_pattern.search(line):
                # If it's a header row (before separator), skip
                if not has_separator:
                    # Look ahead for separator
                    is_header = False
                    for j in range(i + 1, min(i + 5, len(lines))):
                        if lines[j].strip().startswith('| :---') or lines[j].strip().startswith('|:---'):
                            is_header = True
                            break
                    if is_header:
                        new_lines.append(line)
                        continue

                # It's a data row
                cells = line.split('|')
                last_idx = -1
                # Find last cell that has content (ignoring outer pipes if they exist)
                # Markdown tables can be | cell1 | cell2 | or cell1 | cell2
                for k in range(len(cells)-1, -1, -1):
                    if cells[k].strip():
                        last_idx = k
                        break

                if last_idx != -1:
                    req_id = f"[{base_id}-{req_counter:03}]"
                    cells[last_idx] = cells[last_idx].rstrip() + " " + req_id
                    line = "|".join(cells)
                    req_counter += 1
            new_lines.append(line)
            continue

        # Reset table state when not in table
        has_separator = False

        # List item
        # Match bullets or numbered lists
        m = re.match(r'^(\s*[•\-\*\d\.]+\s+)(.*)', line)
        if m and not id_pattern.search(line):
            prefix, text = m.groups()
            if len(text.strip()) > 5:
                req_id = f"[{base_id}-{req_counter:03}]"
                line = f"{prefix}{text.rstrip()} {req_id}"
                req_counter += 1
            new_lines.append(line)
            continue

        # Paragraph
        if not id_pattern.search(line) and len(stripped) > 20:
             # Check if next line is empty or doesn't exist or is a different type
             is_last_in_block = False
             if i + 1 == len(lines) or not lines[i+1].strip() or lines[i+1].strip().startswith('#') or '|' in lines[i+1]:
                 is_last_in_block = True

             if is_last_in_block:
                req_id = f"[{base_id}-{req_counter:03}]"
                line = line.rstrip() + " " + req_id
                req_counter += 1

        new_lines.append(line)

    with open(file_path, 'w') as f:
        f.write('\n'.join(new_lines))

    return req_counter - 1

if __name__ == "__main__":
    specs = parse_test_coverage()
    total_annotated = 0
    for base_id, file_path in specs:
        count = annotate_file(base_id, file_path)
        print(f"Annotated {file_path} ({base_id}): {count} requirements.")
        total_annotated += count
    print(f"Total requirements annotated: {total_annotated}")
