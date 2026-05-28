package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class PresentationControlRoundTripTest {

    @Test
    public void testPECRoundTrip() throws Exception {
        // PEC: D3A7A8
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 6 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xA7, (byte) 0xA8, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new PEC_PresentationEnvironmentControl(), data);
    }

    @Test
    public void testPFCRoundTrip() throws Exception {
        // PFC: D3B288
        // Reserved0 (1): 0x00
        // Flag (1): 0x00 (ResetFidelityControlsToDefault)
        // Reserved2_3 (2): 0x00 00
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 4 + 6 = 19. SFLen = 18 (0x0012)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x12, (byte) 0xD3, (byte) 0xB2, (byte) 0x88, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new PFC_PresentationFidelityControl(), data);
    }

    @Test
    public void testPMCRoundTrip() throws Exception {
        // PMC: D3A7AF
        // ID (1): 0x01
        // Reserved (1): 0x00
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 2 + 6 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA7, (byte) 0xAF, 0x00, 0x00, 0x00,
            0x01, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new PMC_PageModificationControl(), data);
    }

    @Test
    public void testPPORoundTrip() throws Exception {
        // PPO: D3ADC3
        // RG: Len(2)=18, ObjType(1)=FB, Res(2)=0, Flag(1)=0, X(3)=0, Y(3)=0, Triplet(6)=Comment "TEST"
        // Total Len: 1 + 8 + 18 = 27. SFLen = 26 (0x001A)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1A, (byte) 0xD3, (byte) 0xAD, (byte) 0xC3, 0x00, 0x00, 0x00,
            0x00, 0x12, (byte) 0xFB, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new PPO_PreprocessPresentationObject(), data);
    }

    @Test
    public void testCTCRoundTrip() throws Exception {
        // CTC: D3A79B
        // Data: 0x00 00 00 00 00 00 00 00 2D 00
        // Total Len: 1 + 8 + 10 = 19. SFLen = 18 (0x0012)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x12, (byte) 0xD3, (byte) 0xA7, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x2D, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new CTC_ComposedTextControl(), data);
    }
}
