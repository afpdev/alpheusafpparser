package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class EnvironmentAndResourceGroupRoundTripTest {

    @Test
    public void testBDGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xC4, 0x00, 0x00, 0x00,
            (byte)0xC4, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BDG_BeginDocumentEnvironmentGroup(), data);
    }

    @Test
    public void testEDGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xC4, 0x00, 0x00, 0x00,
            (byte)0xC4, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EDG_EndDocumentEnvironmentGroup(), data);
    }

    @Test
    public void testBFMRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xCD, 0x00, 0x00, 0x00,
            (byte)0xC6, (byte)0xD4, (byte)0xC1, (byte)0xD7, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BFM_BeginFormMap(), data);
    }

    @Test
    public void testEFMRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xCD, 0x00, 0x00, 0x00,
            (byte)0xC6, (byte)0xD4, (byte)0xC1, (byte)0xD7, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EFM_EndFormMap(), data);
    }

    @Test
    public void testBMMRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xCC, 0x00, 0x00, 0x00,
            (byte)0xD4, (byte)0xD4, (byte)0xC1, (byte)0xD7, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BMM_BeginMediumMap(), data);
    }

    @Test
    public void testEMMRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xCC, 0x00, 0x00, 0x00,
            (byte)0xD4, (byte)0xD4, (byte)0xC1, (byte)0xD7, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EMM_EndMediumMap(), data);
    }

    @Test
    public void testBRGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xC6, 0x00, 0x00, 0x00,
            (byte)0xD9, (byte)0xC5, (byte)0xE2, 0x40, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BRG_BeginResourceGroup(), data);
    }

    @Test
    public void testERGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xC6, 0x00, 0x00, 0x00,
            (byte)0xD9, (byte)0xC5, (byte)0xE2, 0x40, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new ERG_EndResourceGroup(), data);
    }

    @Test
    public void testBAGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xC9, 0x00, 0x00, 0x00,
            (byte)0xC1, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BAG_BeginActiveEnvironmentGroup(), data);
    }

    @Test
    public void testEAGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xC9, 0x00, 0x00, 0x00,
            (byte)0xC1, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EAG_EndActiveEnvironmentGroup(), data);
    }

    @Test
    public void testBFGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xC5, 0x00, 0x00, 0x00,
            (byte)0xC6, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BFG_BeginFormEnvironmentGroup(), data);
    }

    @Test
    public void testEFGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xC5, 0x00, 0x00, 0x00,
            (byte)0xC6, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EFG_EndFormEnvironmentGroup(), data);
    }

    @Test
    public void testBSGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte)0xD3, (byte)0xA8, (byte)0xD9, 0x00, 0x00, 0x00,
            (byte)0xD9, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40,
            0x06, 0x65, (byte)0xE3, (byte)0xC5, (byte)0xE2, (byte)0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BSG_BeginResourceEnvironmentGroup(), data);
    }

    @Test
    public void testESGRoundTrip() throws Exception {
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xD9, 0x00, 0x00, 0x00,
            (byte)0xD9, (byte)0xC5, (byte)0xC7, 0x40, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new ESG_EndResourceEnvironmentGroup(), data);
    }

    @Test
    public void testERSRoundTrip() throws Exception {
        // ERS: D3A9CE
        // Name (8): RESOURCE1 (D9 C5 E2 D6 E4 D9 C3 C5)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xCE, 0x00, 0x00, 0x00,
            (byte) 0xD9, (byte) 0xC5, (byte) 0xE2, (byte) 0xD6, (byte) 0xE4, (byte) 0xD9, (byte) 0xC3, (byte) 0xC5
        };
        RoundTripTestUtils.assertRoundTrip(new ERS_EndResource(), data);
    }

    @Test
    public void testBRSRoundTrip() throws Exception {
        // BRS: D3A8CE
        // Name (8): RESOURCE1
        // Reserved (2): 00 00
        // Triplet (6): Comment "TEST"
        byte[] data = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xA8, (byte) 0xCE, 0x00, 0x00, 0x00,
            (byte) 0xD9, (byte) 0xC5, (byte) 0xE2, (byte) 0xD6, (byte) 0xE4, (byte) 0xD9, (byte) 0xC3, (byte) 0xC5,
            0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BRS_BeginResource(), data);
    }
}
