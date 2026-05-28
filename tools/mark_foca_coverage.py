import sys
import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        match = re.match(r'\|\s*(FOCA-[1-7A-C]+-\d+)\s*\|', line)
        if match:
            req_id = match.group(1)
            # Check if req_id is in any of the ranges
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
    # req_id like FOCA-1-001
    def parse_id(rid):
        parts = rid.split('-')
        chap_str = parts[1]
        # Appendices A, B, C or numeric chapters
        if chap_str.isdigit():
            chapter = int(chap_str)
        else:
            # Map A to 101, B to 102, etc.
            chapter = 100 + ord(chap_str) - ord('A') + 1
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
        ('FOCA-1-001', 'FOCA-1-037'),
        ('FOCA-2-001', 'FOCA-2-105'),
        ('FOCA-3-001', 'FOCA-3-212'),
        ('FOCA-4-001', 'FOCA-4-105'),
        ('FOCA-5-001', 'FOCA-5-526'),
        ('FOCA-6-001', 'FOCA-6-274'),
        ('FOCA-7-001', 'FOCA-7-017'),
        ('FOCA-A-001', 'FOCA-A-030'),
        ('FOCA-B-001', 'FOCA-B-059'),
        ('FOCA-C-001', 'FOCA-C-026'),
    ]
    count = mark_coverage('TEST_COVERAGE_FOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
