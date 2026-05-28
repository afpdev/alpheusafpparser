import sys
import re

def check_ioca_status(file_path):
    with open(file_path, 'r') as f:
        content = f.read()

    matches = re.findall(r'\|\s*(IOCA-[1-9A-G]\d*-\d+)\s*\|.*?\|\s*([✅❓])\s*\|', content)

    status_map = {m[0]: m[1] == '✅' for m in matches}

    chapters = {}
    for req_id, covered in status_map.items():
        parts = req_id.split('-')
        chap = parts[1]
        # Remove numbers from chapter if any (e.g. F1)
        chap = re.sub(r'\d+', '', chap)
        if chap not in chapters:
            chapters[chap] = {'total': 0, 'covered': 0}
        chapters[chap]['total'] += 1
        if covered:
            chapters[chap]['covered'] += 1

    for chap in sorted(chapters.keys()):
        stats = chapters[chap]
        print(f"Chapter {chap}: {stats['covered']}/{stats['total']} ({stats['covered']/stats['total']*100:.1f}%)")

if __name__ == "__main__":
    check_ioca_status('TEST_COVERAGE_IOCA.md')
