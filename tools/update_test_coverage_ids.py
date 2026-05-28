import re
import sys

def get_id(spec, filename):
    spec_map = {
        'afp-goca-reference-03': 'GOCA',
        'bcoca-reference-11': 'BCOCA',
        'cmoca-reference-02': 'CMOCA',
        'db2z_12_charbook': 'DB2Z',
        'foca-reference-06': 'FOCA',
        'ioca-reference-09': 'IOCA',
        'ipds-reference-12': 'IPDS',
        'linedata-reference-05': 'LINEDATA',
        'moca-reference-02': 'MOCA',
        'modca-reference-10': 'MODCA',
        'ptoca-reference-04': 'PTOCA'
    }

    prefix = spec_map.get(spec, spec)

    if filename == 'Front_Matter.md':
        suffix = 'FM'
    elif filename == 'Glossary.md':
        suffix = 'GLO'
    elif filename == 'Index.md':
        suffix = 'IDX'
    elif filename == 'Notices.md':
        suffix = 'NOT'
    else:
        m = re.match(r'Chapter_(\d+)\.md', filename)
        if m:
            suffix = m.group(1)
        else:
            m = re.match(r'Appendix_([A-Z])(?:_(\d+))?\.md', filename)
            if m:
                if m.group(2):
                    suffix = f"{m.group(1)}{m.group(2)}"
                else:
                    suffix = m.group(1)
            else:
                suffix = filename.replace('.md', '')

    return f"{prefix}-{suffix}"

def transform(filepath):
    with open(filepath, 'r') as f:
        lines = f.readlines()

    output = []
    in_table = False
    for line in lines:
        if line.startswith('| Specification |') or line.startswith('| ID |'):
            in_table = True
            output.append('| ID | Title | Coverage |\n')
            continue

        if in_table and line.startswith('| :--- |'):
            # Count columns in the next line or based on header?
            # Let's just output 3 columns alignment
            output.append('| :--- | :--- | :--- |\n')
            continue

        if in_table and line.startswith('|'):
            parts = [p.strip() for p in line.split('|')]
            if parts and parts[0] == '': parts = parts[1:]
            if parts and parts[-1] == '': parts = parts[:-1]

            if len(parts) == 5: # ID, Specification, File, Title, Coverage
                id_val, spec, filename, title, coverage = parts
                if id_val == 'ID': continue # skip header if redundant

                # Extract ID from link if already linked
                m = re.match(r'\[([^\]]+)\]', id_val)
                if m: id_val = m.group(1)

                # Re-calculate ID to be sure it follows latest rules
                id_val = get_id(spec, filename)

                link = f"specifications/markdown/{spec}/{filename}"
                output.append(f"| [{id_val}]({link}) | {title} | {coverage} |\n")
            elif len(parts) == 4: # Specification, File, Title, Coverage
                spec, filename, title, coverage = parts
                if spec == 'Specification': continue

                id_val = get_id(spec, filename)
                link = f"specifications/markdown/{spec}/{filename}"
                output.append(f"| [{id_val}]({link}) | {title} | {coverage} |\n")
            elif len(parts) == 3: # ID, Title, Coverage
                # If it's already 3 columns, maybe we want to refresh the link/ID
                id_col, title, coverage = parts
                if id_col == 'ID': continue

                # Try to extract spec and filename from link
                m = re.match(r'\[([^\]]+)\]\(([^)]+)\)', id_col)
                if m:
                    id_val = m.group(1)
                    link_path = m.group(2)
                    # link_path is specifications/markdown/SPEC/FILE.md
                    link_parts = link_path.split('/')
                    if len(link_parts) >= 4:
                        spec = link_parts[2]
                        filename = link_parts[3]
                        id_val = get_id(spec, filename)
                        link = f"specifications/markdown/{spec}/{filename}"
                        output.append(f"| [{id_val}]({link}) | {title} | {coverage} |\n")
                    else:
                        output.append(line)
                else:
                    output.append(line)
            else:
                output.append(line)
        else:
            output.append(line)

    return "".join(output)

if __name__ == "__main__":
    filepath = 'TEST_COVERAGE.md'
    if len(sys.argv) > 1:
        filepath = sys.argv[1]
    print(transform(filepath), end='')
