import sys
import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        match = re.match(r'\|\s*(PTOCA-[1-6A-C]+-\d+)\s*\|', line)
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
    # req_id like PTOCA-1-001
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
        ('PTOCA-1-001', 'PTOCA-1-042'),
        ('PTOCA-2-001', 'PTOCA-2-010'),
        ('PTOCA-3-001', 'PTOCA-3-285'),
        ('PTOCA-4-001', 'PTOCA-4-400'),
        ('PTOCA-4-401', 'PTOCA-4-600'),
        ('PTOCA-4-601', 'PTOCA-4-769'),
        ('PTOCA-5-001', 'PTOCA-5-071'),
        ('PTOCA-6-001', 'PTOCA-6-089'),
        ('PTOCA-A-001', 'PTOCA-A-018'),
        ('PTOCA-B-001', 'PTOCA-B-056'),
        ('PTOCA-C-001', 'PTOCA-C-148'),
    ]
    count = mark_coverage('TEST_COVERAGE_PTOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
