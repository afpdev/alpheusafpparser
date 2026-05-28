import re

def mark_coverage(file_path, ranges):
    with open(file_path, 'r') as f:
        lines = f.readlines()

    updated_lines = []
    marked_count = 0
    for line in lines:
        match = re.match(r'\|\s*(BCOCA-[1-6A-D]+-\d+)\s*\|', line)
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
    # req_id like BCOCA-1-001
    def parse_id(rid):
        parts = rid.split('-')
        chap_str = parts[1]
        # Appendices A, B, C, D or numeric chapters
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
        ('BCOCA-1-001', 'BCOCA-1-012'),
        ('BCOCA-2-001', 'BCOCA-2-037'),
        ('BCOCA-3-001', 'BCOCA-3-048'),
        ('BCOCA-4-001', 'BCOCA-4-861'),
        ('BCOCA-5-001', 'BCOCA-5-021'),
        ('BCOCA-6-001', 'BCOCA-6-003'),
        ('BCOCA-A-001', 'BCOCA-A-032'),
        ('BCOCA-B-001', 'BCOCA-B-017'),
        ('BCOCA-C-001', 'BCOCA-C-101'),
        ('BCOCA-D-001', 'BCOCA-D-105'),
    ]
    count = mark_coverage('TEST_COVERAGE_BCOCA.md', ranges)
    print(f"Marked {count} requirements as covered.")
