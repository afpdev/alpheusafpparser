import os

def generate_large_afp(output_path, size_mb=10):
    name_ebcdic = b"DOC00001" # Default CP500

    # BDT: 5A + length(2) + type(3) + flag(1) + reserved(2) + payload
    bdt = bytes([0x5A, 0x00, 0x10, 0xD3, 0xA8, 0xA8, 0x00, 0x00, 0x00]) + name_ebcdic

    # PTOCA sequences
    # AMI: 2B D3 04 C6 + 2 bytes displacement
    # AMB: 2B D3 04 D2 + 2 bytes displacement
    # SCFL: 2B D3 03 F0 + 1 byte LID
    # TRN: 2B D3 LL DA + data

    cs_list = []
    # Add some modal controls
    cs_list.append(bytes([0x2B, 0xD3, 0x04, 0xC6, 0x00, 0x64])) # AMI 100
    cs_list.append(bytes([0x2B, 0xD3, 0x04, 0xD2, 0x00, 0x64])) # AMB 100
    cs_list.append(bytes([0x2B, 0xD3, 0x03, 0xF0, 0x01]))       # SCFL 1

    # Add TRN
    text = b"Hello World! " * 10
    cs_list.append(bytes([0x2B, 0xD3, len(text) + 2, 0xDA]) + text)

    full_ptx_payload = b"".join(cs_list)
    ptx_sf_len = len(full_ptx_payload) + 8
    ptx_header = bytes([0x5A, (ptx_sf_len >> 8) & 0xFF, ptx_sf_len & 0xFF, 0xD3, 0xEE, 0x9B, 0x00, 0x00, 0x00])
    ptx_field = ptx_header + full_ptx_payload

    # EDT: 5A + length(2) + type(3) + flag(1) + reserved(2) + payload
    edt = bytes([0x5A, 0x00, 0x10, 0xD3, 0xA9, 0xA8, 0x00, 0x00, 0x00]) + name_ebcdic

    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    with open(output_path, 'wb') as f:
        f.write(bdt)
        num_fields = (size_mb * 1024 * 1024) // len(ptx_field)
        for _ in range(num_fields):
            f.write(ptx_field)
        f.write(edt)

if __name__ == "__main__":
    generate_large_afp("src/test/resources/afp/perf_ptx.afp", size_mb=10)
