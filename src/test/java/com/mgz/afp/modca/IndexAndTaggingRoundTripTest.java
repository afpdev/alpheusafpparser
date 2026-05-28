package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class IndexAndTaggingRoundTripTest {

    @Test
    public void testTLERoundTrip() throws Exception {
        // TLE: D3A090
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 6 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xA0, (byte) 0x90, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new TLE_TagLogicalElement(), data);
    }

    @Test
    public void testIELRoundTrip() throws Exception {
        // IEL: D3B2A7
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 6 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xB2, (byte) 0xA7, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new IEL_IndexElement(), data);
    }

    @Test
    public void testLLERoundTrip() throws Exception {
        // LLE: D3B490
        // LinkType (1): 0x01 (NavigationLink)
        // Reserved (1): 0x00
        // Repeating Group: Len(2) | Function(1) | Triplet(6)
        // RG Len = 2 + 1 + 6 = 9 (0x0009)
        // Total Len: 1 + 8 + 2 + 9 = 20. SFLen = 19 (0x0013)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x13, (byte) 0xD3, (byte) 0xB4, (byte) 0x90, 0x00, 0x00, 0x00,
            0x01, 0x00,
            0x00, 0x09, 0x01, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new LLE_LinkLogicalElement(), data);
    }
}
