package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class DocumentAndPageGroupRoundTripTest {

    @Test
    public void testBDTRoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8) | Res(2) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 2 + 6 = 25. SFLen = 24 (0x0018)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, 0x00, 0x00, 0x00,
            (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3, 0x40, 0x40, 0x40, 0x40, // "TEST"
            0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BDT_BeginDocument(), data);
    }

    @Test
    public void testEDTRoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xA8, 0x00, 0x00, 0x00,
            (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3, 0x40, 0x40, 0x40, 0x40 // "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new EDT_EndDocument(), data);
    }

    @Test
    public void testBPGRoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 + 6 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xAF, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1, // "PAGE0001"
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BPG_BeginPage(), data);
    }

    @Test
    public void testEPGRoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xAF, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1 // "PAGE0001"
        };
        RoundTripTestUtils.assertRoundTrip(new EPG_EndPage(), data);
    }

    @Test
    public void testBNGRoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 + 6 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xAD, 0x00, 0x00, 0x00,
            (byte) 0xC7, (byte) 0xD9, (byte) 0xD7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1, // "GRP00001"
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BNG_BeginNamedPageGroup(), data);
    }

    @Test
    public void testENGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xAD, 0x00, 0x00, 0x00,
            (byte) 0xC7, (byte) 0xD9, (byte) 0xD7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1 // "GRP00001"
        };
        RoundTripTestUtils.assertRoundTrip(new ENG_EndNamedPageGroup(), data);
    }

    @Test
    public void testBDIRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xA7, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD5, (byte) 0xC4, (byte) 0xC5, (byte) 0xD7, 0x40, 0x40, 0x40, // "INDEX"
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BDI_BeginDocumentIndex(), data);
    }

    @Test
    public void testEDIRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xA7, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD5, (byte) 0xC4, (byte) 0xC5, (byte) 0xD7, 0x40, 0x40, 0x40 // "INDEX"
        };
        RoundTripTestUtils.assertRoundTrip(new EDI_EndDocumentIndex(), data);
    }

    @Test
    public void testEPFRoundTrip() throws Exception {
        // EPF: D3A9A5
        // Name (8): PRTFILE1 (D7 D9 E3 C6 C9 D3 C5 F1)
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA9, (byte) 0xA5, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xD9, (byte) 0xE3, (byte) 0xC6, (byte) 0xC9, (byte) 0xD3, (byte) 0xC5, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new EPF_EndPrintFile(), data);
    }

    @Test
    public void testBPFRoundTrip() throws Exception {
        // BPF: D3A8A5
        // Name (8): PRTFILE1
        // Triplet (6): Comment "TEST"
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xA5, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xD9, (byte) 0xE3, (byte) 0xC6, (byte) 0xC9, (byte) 0xD3, (byte) 0xC5, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BPF_BeginPrintFile(), data);
    }

    @Test
    public void testPGPFormat1RoundTrip() throws Exception {
        // PGP F1: D3ACAF
        // xOrigin (3): 0x000100
        // yOrigin (3): 0x000200
        // Total Len: 1 + 8 + 6 = 15. SFI Len = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xAC, (byte) 0xAF, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x00, 0x02, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new PGP_PagePosition_Format1(), data);
    }

    @Test
    public void testPGPFormat2RoundTrip() throws Exception {
        // PGP F2: D3B1AF
        // Payload: constant(1) | RG
        // RG: Len(2) | xOrigin(3) | yOrigin(3) | rot(2) | SSPS(1) | Flags(1) | PMC(1) -> 13 bytes
        // Total Len: 1 + 8 + 1 + 13 = 23. SFI Len = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xB1, (byte) 0xAF, 0x00, 0x00, 0x00,
            0x00, // constant 0
            0x00, 0x0D, 0x00, 0x01, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01
        };
        RoundTripTestUtils.assertRoundTrip(new PGP_PagePosition_Format2(), data);
    }

}
