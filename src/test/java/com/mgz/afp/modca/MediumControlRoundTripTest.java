package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class MediumControlRoundTripTest {

    @Test
    public void testMCCRoundTrip() throws Exception {
        // MCC: D3A288
        // RG (6 bytes): Start(2)=1, End(2)=1, Res(1)=0, MMCID(1)=1
        // Total Len: 1 + 8 + 6 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xA2, (byte) 0x88, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x01, 0x00, 0x01
        };
        RoundTripTestUtils.assertRoundTrip(new MCC_MediumCopyCount(), data);
    }

    @Test
    public void testMMCMultiKeywordRoundTrip() throws Exception {
        // MMC: D3A788
        // MMCID(1)=1, Const(1)=0,
        // Keyword 1: DuplexControl(0xF4), Param(1)=1
        // Keyword 2: PrintQualityControl(0xF8), Param(1)=2
        // Total Len: 1 + 8 + 2 + 2 + 2 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xA7, (byte) 0x88, 0x00, 0x00, 0x00,
            0x01, 0x00, (byte) 0xF4, 0x01, (byte) 0xF8, 0x02
        };
        RoundTripTestUtils.assertRoundTrip(new MMC_MediumModificationControl(), data);
    }

    @Test
    public void testMPTRoundTrip() throws Exception {
        // MPT: D3AB9B
        // RG: Len(2)=5 | Triplet: MappingOption(0x04)=0x00
        // Total Len: 1 + 8 + 5 = 14. SFLen = 13 (0x000D)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0D, (byte) 0xD3, (byte) 0xAB, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x00, 0x05, 0x03, 0x04, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new MPT_MapPresentationText(), data);
    }

    @Test
    public void testMMCRoundTrip() throws Exception {
        // MMC: D3A788
        // MMCID(1)=1, Const(1)=0, Keyword(2): DuplexControl(0xF4), Param(1)=1
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xA7, (byte) 0x88, 0x00, 0x00, 0x00,
            0x01, 0x00, (byte) 0xF4, 0x01
        };
        RoundTripTestUtils.assertRoundTrip(new MMC_MediumModificationControl(), data);
    }

    @Test
    public void testMMTRoundTrip() throws Exception {
        // MMT: D3AB88
        // RG: Len(2) | Reserved(6) | Triplet(6)
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xAB, (byte) 0x88, 0x00, 0x00, 0x00,
            0x00, 0x0E, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // RG len 14, Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new MMT_MapMediaType(), data);
    }

    @Test
    public void testMFCRoundTrip() throws Exception {
        // MFC: D3A088
        // Flag(1)=0x80, Res(1)=0, Boundary(1)=1, Scope(1)=1, Triplet(6)
        // Total Len: 1 + 8 + 4 + 6 = 19. SFLen = 18 (0x0012)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x12, (byte) 0xD3, (byte) 0xA0, (byte) 0x88, 0x00, 0x00, 0x00,
            (byte) 0x80, 0x00, 0x01, 0x01,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MFC_MediumFinishingControl(), data);
    }

    @Test
    public void testIMMRoundTrip() throws Exception {
        // IMM: D3ABCC
        // Name(8) | CommentTriplet(6)
        // Total Len: 1 + 8 + 8 + 6 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xAB, (byte) 0xCC, 0x00, 0x00, 0x00,
            (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, (byte) 0xF1, 0x40, 0x40, 0x40, 0x40, // "MAP1"
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // Comment "TEST"
        };
        RoundTripTestUtils.assertRoundTrip(new IMM_InvokeMediumMap(), data);
    }
}
