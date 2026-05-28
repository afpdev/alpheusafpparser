import os

def generate_minimal_afp(output_path):
    # DOC00001 in EBCDIC IBM500
    name_ebcdic = bytes([0xC4, 0xD6, 0xC3, 0xF0, 0xF0, 0xF0, 0xF0, 0xF1])

    # BDT: 5A + length(2) + type(3) + flag(1) + reserved(2) + payload
    # Length = 8 (SFI) + 8 (name) = 16 (0x0010)
    bdt = bytes([0x5A, 0x00, 0x10, 0xD3, 0xA8, 0xA8, 0x00, 0x00, 0x00]) + name_ebcdic

    # EDT: same length and payload, different type
    edt = bytes([0x5A, 0x00, 0x10, 0xD3, 0xA9, 0xA8, 0x00, 0x00, 0x00]) + name_ebcdic

    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    with open(output_path, 'wb') as f:
        f.write(bdt)
        f.write(edt)

if __name__ == "__main__":
    generate_minimal_afp("src/test/resources/afp/minimal.afp")
