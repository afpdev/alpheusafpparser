import sys
import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        match = re.match(r'\|\s*(IOCA-[1-9A-G]+-\d+)\s*\|', line)
        if match:
            req_id = match.group(1)
            should_mark = False
            for start, end in ranges:
                if is_in_range(req_id, start, end):
                    should_mark = True
                    break

            if should_mark and '❓' in line:
                line = line.replace('❓', '✅')
                marked_count += 1

        updated_lines.append(line)

    with open(file_path, 'w') as f:
        f.writelines(updated_lines)
    return marked_count

def is_in_range(req_id, start, end):
    def parse_id(rid):
        parts = rid.split('-')
        chap_str = parts[1]
        if chap_str.isdigit():
            chapter = int(chap_str)
        elif len(chap_str) == 1:
            chapter = 100 + ord(chap_str) - ord('A') + 1
        else:
            base_char = chap_str[0]
            chapter = 100 + ord(base_char) - ord('A') + 1
            suffix = chap_str[1:]
            if suffix.isdigit():
                chapter += int(suffix) / 10.0
        number = int(parts[2])
        return chapter, number

    curr_chap, curr_num = parse_id(req_id)
    start_chap, start_num = parse_id(start)
    end_chap, end_num = parse_id(end)

    if curr_chap < start_chap or curr_chap > end_chap:
        return False
    if curr_chap == start_chap and curr_num < start_num:
        return False
    if curr_chap == end_chap and curr_num > end_num:
        return False
    return True

if __name__ == "__main__":
    ranges = [
        ('IOCA-1-001', 'IOCA-7-1068'),
        ('IOCA-A-001', 'IOCA-A-039'),
        ('IOCA-B-001', 'IOCA-B-007'),
        ('IOCA-C-001', 'IOCA-C-007'),
        ('IOCA-D-001', 'IOCA-D-021'),
        ('IOCA-E-001', 'IOCA-E-011'),
        ('IOCA-F-001', 'IOCA-F-003'),
        ('IOCA-F1-001', 'IOCA-F1-011'),
        ('IOCA-G-001', 'IOCA-G-009'),
    ]
    count = mark_coverage('TEST_COVERAGE_IOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
