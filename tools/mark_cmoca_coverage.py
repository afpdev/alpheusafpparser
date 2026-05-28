import sys
import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        # Match CMOCA-X-XXX
        match = re.search(r'(CMOCA-[1-6A-C]-\d+)', line)
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
    # req_id like CMOCA-1-001
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
        ('CMOCA-1-001', 'CMOCA-1-063'),
        ('CMOCA-2-001', 'CMOCA-2-034'),
        ('CMOCA-3-001', 'CMOCA-3-261'),
        ('CMOCA-4-001', 'CMOCA-4-133'),
        ('CMOCA-5-001', 'CMOCA-5-314'),
        ('CMOCA-6-001', 'CMOCA-6-187'),
        ('CMOCA-A-001', 'CMOCA-A-047'),
        ('CMOCA-B-001', 'CMOCA-B-017'),
        ('CMOCA-C-001', 'CMOCA-C-029'),
    ]
    count = mark_coverage('TEST_COVERAGE_CMOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
