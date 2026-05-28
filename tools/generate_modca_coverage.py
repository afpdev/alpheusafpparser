import re
import os

def generate_coverage():
    spec_dir = 'specifications/markdown/modca-reference-10'
    logical_order = [
        'Front_Matter.md',
        'Chapter_1.md',
        'Chapter_2.md',
        'Chapter_3.md',
        'Chapter_4.md',
        'Chapter_5.md',
        'Chapter_6.md',
        'Chapter_7.md',
        'Chapter_8.md',
        'Appendix_A.md',
        'Appendix_B.md',
        'Appendix_C.md',
        'Appendix_D.md',
        'Appendix_E.md',
        'Appendix_F.md',
        'Glossary.md',
        'Index.md',
        'Notices.md'
    ]

    output = ["# Granular Test Coverage - MO:DCA\n", "| Requirement ID | Summary | Coverage |", "| :--- | :--- | :---: |"]

    for filename in logical_order:
        file_path = os.path.join(spec_dir, filename)
        if not os.path.exists(file_path):
            continue

        with open(file_path, 'r') as f:
            lines = f.readlines()

        for line in lines:
            line = line.strip()
            if not line: continue

            # Find all matches in the line for MODCA-X-XXX or MODCA-X-XXXX
            matches = re.findall(r'\[(MODCA-[1-8A-FMG]-\d{1,4})\]', line)
            if matches:
                for req_id in matches:
                    # Exclude Front Matter IDs from the coverage report
                    if '-FM-' in req_id:
                        continue

                    # Heuristic for summary: take text before the ID, clean it up
                    parts = line.split(f'[{req_id}]')
                    summary = parts[0].strip()

                    # Clean summary: remove other IDs
                    summary = re.sub(r'\[MODCA-[1-8A-FMG]-\d{1,4}\]', '', summary)
                    summary = summary.replace('#', '').replace('*', '').strip()

                    if len(summary) < 5:
                         # Try to take some text from after the ID if before is empty
                         if len(parts) > 1:
                             summary = parts[1].strip()
                             summary = re.sub(r'\[MODCA-[1-8A-FMG]-\d{1,4}\]', '', summary).strip()

                    if len(summary) < 5:
                         summary = line.replace(f'[{req_id}]', '').strip()
                         summary = re.sub(r'\[MODCA-[1-8A-FMG]-\d{1,4}\]', '', summary).strip()

                    # Final cleanup of pipes for markdown table
                    summary = summary.replace('|', ' ').strip()

                    # Truncate summary if too long
                    if len(summary) > 100:
                        summary = summary[:97] + "..."

                    # Default to ❓ as per typical initialization, though ROADMAP says 0%
                    output.append(f"| {req_id} | {summary} | ❓ |")

    with open('TEST_COVERAGE_MODCA.md', 'w') as f:
        f.write('\n'.join(output) + '\n')

if __name__ == "__main__":
    generate_coverage()
