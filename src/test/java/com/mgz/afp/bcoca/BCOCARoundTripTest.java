package com.mgz.afp.bcoca;

import com.mgz.afp.RoundTripTestUtils;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;

public class BCOCARoundTripTest {

    @Test
    public void testBBCRoundTrip() throws Exception {
        // BBC: D3A8EB
        // Name (8): BARCODE1 (C2 C1 D9 C3 D6 C4 C5 F1)
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xEB, 0x00, 0x00, 0x00,
            (byte) 0xC2, (byte) 0xC1, (byte) 0xD9, (byte) 0xC3, (byte) 0xD6, (byte) 0xC4, (byte) 0xC5, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BBC_BeginBarCodeObject(), data);
    }

    @Test
    public void testEBCRoundTrip() throws Exception {
        // EBC: D3A9EB
        // Name (8): BARCODE1
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA9, (byte) 0xEB, 0x00, 0x00, 0x00,
            (byte) 0xC2, (byte) 0xC1, (byte) 0xD9, (byte) 0xC3, (byte) 0xD6, (byte) 0xC4, (byte) 0xC5, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new EBC_EndBarCodeObject(), data);
    }

    @Test
    public void testBDDRoundTrip() throws Exception {
        // BDD: D3A6EB
        // Data (23 bytes):
        // UnitBase: 0x00 (10 inches)
        // Reserved: 0x00
        // UnitsPerX: 0x05A0 (1440)
        // UnitsPerY: 0x05A0 (1440)
        // Width: 0x0000
        // Length: 0x0000
        // DesiredWidth: 0x0000
        // Type: 0x01 (Code 39)
        // Modifier: 0x01
        // FontLocalID: 0x00
        // Color: 0x0000
        // ModuleWidth: 0x00
        // ElementHeight: 0x0000
        // HeightMultiplier: 0x00
        // Ratio: 0x0000
        // Total Payload: 23 bytes
        // Total Len: 1 + 8 + 23 = 32. SFLen = 31 (0x001F)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1F, (byte) 0xD3, (byte) 0xA6, (byte) 0xEB, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x05, (byte) 0xA0, 0x05, (byte) 0xA0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new BDD_BarCodeDataDescriptor(), data);
    }

    @Test
    public void testBDARoundTrip() throws Exception {
        // BDA: D3EEEB
        // Data:
        // Flags: 0x80 (HRINotPresent)
        // xOffset: 0x0000
        // yOffset: 0x0000
        // barCodeData: 0x01 02 03
        // Total Payload: 5 + 3 = 8 bytes
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xEE, (byte) 0xEB, 0x00, 0x00, 0x00,
            (byte) 0x80, 0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        BDD_BarCodeDataDescriptor bdd = new BDD_BarCodeDataDescriptor();
        // Set a barcode type that doesn't have special parameters to keep it simple
        bdd.setBarcodeType(BDD_BarCodeDataDescriptor.BarCodeType.Code39_3of9Code_AIM_USS_39);
        config.setCurrentBarCodeDataDescriptor(bdd);

        RoundTripTestUtils.assertRoundTrip(new BDA_BarCodeData(), data, config);
    }
}
