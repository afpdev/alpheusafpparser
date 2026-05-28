import os

def generate_large_afp(output_path, size_mb=10):
    # DOC00001 in EBCDIC IBM273 (German)
    name_ebcdic = bytes([0xC4, 0xD6, 0xC3, 0xF0, 0xF0, 0xF0, 0xF0, 0xF1])

    # BDT: 5A + length(2) + type(3) + flag(1) + reserved(2) + payload
    bdt = bytes([0x5A, 0x00, 0x10, 0xD3, 0xA8, 0xA8, 0x00, 0x00, 0x00]) + name_ebcdic

    # PTX: 5A + length(2) + D3EE9B + flag(1) + reserved(2) + payload
    # TRN Control Sequence: 2BD3 + length(1) + DA(chained=0) + data
    # We use a payload that fits in one SF. Max length 32767.
    # Payload = 32000.

    # "Hällö Wörld" in CP273
    german_text = bytes.fromhex("c8c093936a40e66a999384")
    # "äöüßÄÖÜ" in CP273
    umlauts = bytes.fromhex("c06ad0a14ae05a")

    base_text = german_text + b" " + umlauts + b" " + b"This is a PTX field to test processing speed. " * 50
    ptx_data = (base_text * 10)[:31996]

    # PTOCA TRN Control Sequence (chained=0)
    # 2B D3 + length(1) + DA + payload
    # The length byte in TRN includes the length byte and function byte (2 bytes) + data
    # But wait, PTOCA spec says length byte is length of CS including itself.
    # So length = 2 + len(ptx_data)
    # If len(ptx_data) = 31996, length = 31998. Oh, length byte is only 1 byte!
    # Max length of a CS is 255.

    cs_list = []
    chunk_size = 250
    for i in range(0, len(ptx_data), chunk_size):
        chunk = ptx_data[i:i+chunk_size]
        cs_len = len(chunk) + 2
        cs_list.append(bytes([0x2B, 0xD3, cs_len, 0xDA]) + chunk)

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
    generate_large_afp("src/test/resources/afp/large_ibm273.afp", size_mb=10)
