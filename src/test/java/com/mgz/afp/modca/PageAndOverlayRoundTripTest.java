package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class PageAndOverlayRoundTripTest {

    @Test
    public void testBMORoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8) | Res(2) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 2 + 6 = 25. SFLen = 24 (0x0018)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xA8, (byte) 0xDF, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xE5, (byte) 0xC5, (byte) 0xD9, (byte) 0xD3, (byte) 0xC1, (byte) 0xE8, (byte) 0xF1, // "OVERLAY1"
            0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BMO_BeginOverlay(), data);
    }

    @Test
    public void testEMORoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 + 6 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA9, (byte) 0xDF, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xE5, (byte) 0xC5, (byte) 0xD9, (byte) 0xD3, (byte) 0xC1, (byte) 0xE8, (byte) 0xF1, // "OVERLAY1"
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new EMO_EndOverlay(), data);
    }

    @Test
    public void testBPSRoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 + 2 + 6 = 25. SFLen = 24 (0x0018)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xA8, 0x5F, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xE2, (byte) 0xC5, (byte) 0xC7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1, // "PSEG0001"
            0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new BPS_BeginPageSegment(), data);
    }

    @Test
    public void testEPSRoundTrip() throws Exception {
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, 0x5F, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xE2, (byte) 0xC5, (byte) 0xC7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1 // "PSEG0001"
        };
        RoundTripTestUtils.assertRoundTrip(new EPS_EndPageSegment(), data);
    }

    @Test
    public void testIPGRoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8) | Res(8) | Flags(1) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 8 + 1 + 6 = 32. SFLen = 31 (0x001F)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1F, (byte) 0xD3, (byte) 0xAF, (byte) 0xAF, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1, // "PAGE0001"
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new IPG_IncludePage(), data);
    }

    @Test
    public void testIPORoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8) | X(3) | Y(3) | Rot(2) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 3 + 3 + 2 + 6 = 31. SFLen = 30 (0x001E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1E, (byte) 0xD3, (byte) 0xAF, (byte) 0xD8, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xE5, (byte) 0xC5, (byte) 0xD9, (byte) 0xD3, (byte) 0xC1, (byte) 0xE8, (byte) 0xF1, // "OVERLAY1"
            0x00, 0x00, 0x00,
            0x00, 0x00, 0x00,
            0x00, 0x00, // Rotation 0
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new IPO_IncludePageOverlay(), data);
    }

    @Test
    public void testIPSRoundTrip() throws Exception {
        // 0x5A | SFLen(2) | SFType(3) | Flag(1) | Res(2) | Name(8) | X(3) | Y(3) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 3 + 3 + 6 = 29. SFLen = 28 (0x001C)
        // Payload length = 14. actualLength > 14 is FALSE. triplets will be NULL.
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xAF, (byte) 0x5F, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xE2, (byte) 0xC5, (byte) 0xC7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1, // "PSEG0001"
            0x00, 0x00, 0x00,
            0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new IPS_IncludePageSegment(), data);
    }

    @Test
    public void testMPGRoundTrip() throws Exception {
        // MPG has repeating groups with triplets.
        // RG Len(2) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xAF, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // RG len 8, Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new MPG_MapPage(), data);
    }

    @Test
    public void testMPORoundTrip() throws Exception {
        // MPO has repeating groups with triplets.
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xD8, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // RG len 8, Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new MPO_MapPageOverlay(), data);
    }

    @Test
    public void testMPSRoundTrip() throws Exception {
        // MPS has repeating groups (fixed length 12).
        // SFI(9) | RG Len (1) | Res(3) | RG(12)
        // RG(12) = Res(4) | Name(8)
        // Total Len: 1 + 8 + 1 + 3 + 12 = 25. SFLen = 24 (0x0018)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xB1, (byte) 0x5F, 0x00, 0x00, 0x00,
            0x0C, 0x00, 0x00, 0x00, // RG Len 12, Res(3)
            0x00, 0x00, 0x00, 0x00, (byte) 0xD7, (byte) 0xE2, (byte) 0xC5, (byte) 0xC7, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1 // RG: Res(4), Name "PSEG0001"
        };
        RoundTripTestUtils.assertRoundTrip(new MPS_MapPageSegment(), data);
    }
}
