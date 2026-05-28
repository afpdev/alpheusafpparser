import sys
import re

def check_goca_status(file_path):
    with open(file_path, 'r') as f:
        content = f.read()

    # Extract all GOCA requirement IDs and their status
    # Assuming rows are like | GOCA-X-YYY | Summary | ❓ |
    matches = re.findall(r'\|\s*(GOCA-[1-9A-D]-\d+)\s*\|.*?\|\s*([✅❓])\s*\|', content)

    status_map = {m[0]: m[1] == '✅' for m in matches}

    chapters = {}
    for req_id, covered in status_map.items():
        parts = req_id.split('-')
        chap = parts[1]
        if chap not in chapters:
            chapters[chap] = {'total': 0, 'covered': 0}
        chapters[chap]['total'] += 1
        if covered:
            chapters[chap]['covered'] += 1

    for chap in sorted(chapters.keys()):
        stats = chapters[chap]
        print(f"Chapter {chap}: {stats['covered']}/{stats['total']} ({stats['covered']/stats['total']*100:.1f}%)")

if __name__ == "__main__":
    check_goca_status('TEST_COVERAGE_GOCA.md')
