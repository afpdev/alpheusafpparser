import re
import os
import sys

def get_coverage(spec, filename):
    if spec == 'db2z_12_charbook':
        return 'N/A'

    afp_filename = filename.replace('.md', '.afp')
    afp_path = os.path.join('src/test/resources/afp', spec, afp_filename)

    if os.path.exists(afp_path):
        return '✅ Covered'
    else:
        return 'TBD'

def transform(filepath):
    with open(filepath, 'r') as f:
        lines = f.readlines()

    table_data = []
    stats = {}

    description_lines = []
    header_title = ""

    for line in lines:
        if line.startswith('# '):
            header_title = line
        elif line.startswith('|') and not (line.startswith('| ID |') or line.startswith('| :--- |') or line.startswith('| Specification |')):
            parts = [p.strip() for p in line.split('|')]
            if len(parts) >= 4:
                id_col = parts[1]
                title = parts[2]

                m = re.match(r'\[([^\]]+)\]\(([^)]+)\)', id_col)
                if m:
                    id_val = m.group(1)
                    link_path = m.group(2)

                    link_parts = link_path.split('/')
                    if len(link_parts) >= 4:
                        spec = link_parts[2]
                        filename = link_parts[3]

                        coverage = get_coverage(spec, filename)

                        if spec not in stats:
                            stats[spec] = {'covered': 0, 'total': 0, 'na': 0}

                        stats[spec]['total'] += 1
                        if coverage == '✅ Covered':
                            stats[spec]['covered'] += 1
                        elif coverage == 'N/A':
                            stats[spec]['na'] += 1

                        table_data.append(f"| [{id_val}]({link_path}) | {title} | {coverage} |\n")
        elif not line.startswith('|') and not line.startswith('# ') and not line.startswith('## '):
            if line.strip():
                line = line.replace("The coverage status is currently TBD (To Be Determined).",
                                    "The coverage status is verified by the presence of corresponding architectural .afp test files.")
                description_lines.append(line)

    # Build Summary Table
    summary = ["## Coverage Summary\n\n"]
    summary.append("| Specification | Covered | Total | % |\n")
    summary.append("| :--- | :---: | :---: | :---: |\n")

    sorted_specs = sorted(stats.keys())
    for spec in sorted_specs:
        covered = stats[spec]['covered']
        total = stats[spec]['total']
        na = stats[spec]['na']

        effective_total = total - na
        if effective_total > 0:
            percent = (covered / effective_total) * 100
            percent_str = f"{percent:.1f}%"
        else:
            percent_str = "N/A"

        summary.append(f"| {spec} | {covered} | {total} | {percent_str} |\n")

    summary.append("\n")

    # Reconstruct final content
    final_output = []
    final_output.append(header_title)
    final_output.extend(summary)
    for line in description_lines:
        final_output.append(line)
    final_output.append("\n")

    # Add the main table
    final_output.append("| ID | Title | Coverage |\n")
    final_output.append("| :--- | :--- | :--- |\n")
    final_output.extend(table_data)

    return "".join(final_output)

if __name__ == "__main__":
    filepath = 'TEST_COVERAGE.md'
    if len(sys.argv) > 1:
        filepath = sys.argv[1]

    new_content = transform(filepath)
    with open(filepath, 'w') as f:
        f.write(new_content)
