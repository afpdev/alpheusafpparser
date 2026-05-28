import sys
import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        match = re.match(r'\|\s*(MOCA-[1-6]-\d+)\s*\|', line)
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
    # req_id like MOCA-1-001
    def parse_id(rid):
        parts = rid.split('-')
        chapter = int(parts[1])
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
        ('MOCA-1-001', 'MOCA-1-070'),
        ('MOCA-2-001', 'MOCA-2-003'),
        ('MOCA-3-001', 'MOCA-3-004'),
        ('MOCA-4-001', 'MOCA-4-038'),
        ('MOCA-5-001', 'MOCA-5-026'),
        ('MOCA-6-001', 'MOCA-6-032'),
    ]
    count = mark_coverage('TEST_COVERAGE_MOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
