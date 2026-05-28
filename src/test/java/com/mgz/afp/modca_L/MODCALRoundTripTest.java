package com.mgz.afp.modca_L;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class MODCALRoundTripTest {

    @Test
    public void testBCARoundTrip() throws Exception {
        // BCA: D3A877
        // Name (8): COLORTBL (C3 D6 D3 D6 D9 E3 C2 D3)
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, 0x77, 0x00, 0x00, 0x00,
            (byte) 0xC3, (byte) 0xD6, (byte) 0xD3, (byte) 0xD6, (byte) 0xD9, (byte) 0xE3, (byte) 0xC2, (byte) 0xD3,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BCA_BeginColorAttributeTable(), data);
    }

    @Test
    public void testECARoundTrip() throws Exception {
        // ECA: D3A977
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA9, 0x77, 0x00, 0x00, 0x00,
            (byte) 0xC3, (byte) 0xD6, (byte) 0xD3, (byte) 0xD6, (byte) 0xD9, (byte) 0xE3, (byte) 0xC2, (byte) 0xD3,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new ECA_EndColorAttributeTable(), data);
    }

    @Test
    public void testMCARoundTrip() throws Exception {
        // MCA: D3AB77
        // RG: Len(2) | Triplet(6)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, 0x77, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MCA_MapColorAttributeTable(), data);
    }

    @Test
    public void testCATRoundTrip() throws Exception {
        // CAT: D3B077
        // BasePart (3): ResetLCT(1)=0x80, Res(1)=0, LocalID(1)=1
        // OtherData (4): 0x01 02 03 04
        // Total Len: 1 + 8 + 3 + 4 = 16. SFLen = 15 (0x000F)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0F, (byte) 0xD3, (byte) 0xB0, 0x77, 0x00, 0x00, 0x00,
            (byte) 0x80, 0x00, 0x01,
            0x01, 0x02, 0x03, 0x04
        };
        RoundTripTestUtils.assertRoundTrip(new CAT_ColorAttributeTable(), data);
    }
}
