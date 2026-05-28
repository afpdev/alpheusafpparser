import os
import re

def fix_package_separator(filepath):
    with open(filepath, 'r') as f:
        lines = f.readlines()

    changed = False
    new_lines = []
    for i, line in enumerate(lines):
        if line.strip().startswith('package '):
            if i > 0 and lines[i-1].strip() != '':
                new_lines.append('\n')
                changed = True
        new_lines.append(line)

    if changed:
        with open(filepath, 'w') as f:
            f.writelines(new_lines)
        return True
    return False

def fix_excessive_newlines(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Replace 3 or more newlines with 2 newlines (one empty line)
    new_content = re.sub(r'\n\n\n+', '\n\n', content)

    if new_content != content:
        with open(filepath, 'w') as f:
            f.write(new_content)
        return True
    return False

def main():
    java_files = []
    for root, dirs, files in os.walk('src/main/java'):
        for file in files:
            if file.endswith('.java'):
                java_files.append(os.path.join(root, file))

    package_fixes = 0
    newline_fixes = 0
    for filepath in java_files:
        if fix_package_separator(filepath):
            package_fixes += 1
        if fix_excessive_newlines(filepath):
            newline_fixes += 1

    print(f"Fixed package separator in {package_fixes} files")
    print(f"Fixed excessive newlines in {newline_fixes} files")

if __name__ == '__main__':
    main()
