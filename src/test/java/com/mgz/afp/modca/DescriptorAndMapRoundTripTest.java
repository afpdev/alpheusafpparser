package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class DescriptorAndMapRoundTripTest {

    @Test
    public void testPGDRoundTrip() throws Exception {
        // PGD: D3A6AF
        // XUnitBase (1): 0x00 (Inches)
        // YUnitBase (1): 0x00 (Inches)
        // XUnits (2): 0x05A0 (1440)
        // YUnits (2): 0x05A0 (1440)
        // XSize (3): 0x002E70 (11888)
        // YSize (3): 0x0041B0 (16816)
        // Reserved (3): 0x00 00 00
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 15 + 6 = 30. SFLen = 29 (0x001D)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1D, (byte) 0xD3, (byte) 0xA6, (byte) 0xAF, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x05, (byte) 0xA0, 0x05, (byte) 0xA0, 0x00, 0x2E, 0x70, 0x00, 0x41, (byte) 0xB0,
            0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new PGD_PageDescriptor(), data);
    }

    @Test
    public void testMDDRoundTrip() throws Exception {
        // MDD: D3A688
        // XUnitBase (1): 0x00
        // YUnitBase (1): 0x00
        // XUnits (2): 0x05A0
        // YUnits (2): 0x05A0
        // XMediumExtent (3): 0x002E70
        // YMediumExtent (3): 0x0041B0
        // Flag (1): 0x00
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 13 + 6 = 28. SFLen = 27 (0x001B)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1B, (byte) 0xD3, (byte) 0xA6, (byte) 0x88, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x05, (byte) 0xA0, 0x05, (byte) 0xA0, 0x00, 0x2E, 0x70, 0x00, 0x41, (byte) 0xB0,
            0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MDD_MediumDescriptor(), data);
    }

    @Test
    public void testOBDRoundTrip() throws Exception {
        // OBD: D3A66B
        // OBD extends StructuredFieldBaseTriplets which just parses triplets from offset 0
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 6 = 15. SFLen = 14 (0x000E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0E, (byte) 0xD3, (byte) 0xA6, 0x6B, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new OBD_ObjectAreaDescriptor(), data);
    }

    @Test
    public void testCDDRoundTrip() throws Exception {
        // CDD: D3A692
        // RetiredParameters (12): All 0
        // Triplet (6): Comment "TEST"
        // Total Len: 1 + 8 + 12 + 6 = 27. SFLen = 26 (0x001A)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1A, (byte) 0xD3, (byte) 0xA6, (byte) 0x92, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new CDD_ContainerDataDescriptor(), data);
    }

    @Test
    public void testFGDRoundTrip() throws Exception {
        // FGD: D3A6C5
        // Constant Data (4): 0x00 01 00 FF
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xA6, (byte) 0xC5, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, (byte) 0xFF
        };
        RoundTripTestUtils.assertRoundTrip(new FGD_FormEnvironmentGroupDescriptor(), data);
    }

    @Test
    public void testMDRRoundTrip() throws Exception {
        // MDR: D3ABC3
        // Repeating Group: Len(2) | Triplet(6)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xC3, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MDR_MapDataResource(), data);
    }

    @Test
    public void testMCFFormat1RoundTrip() throws Exception {
        // MCF F1: D3B18A
        // SFI: D3B18A 00 00 00
        // RG Len(1): 26 (0x1A) - actually 28 or 30 in code? Let's check MCF_RepeatingGroup.decodeAFP
        // Code says:
        // codedFontLocalID(1), res(1), codedFontSectionID(1), res(1) -> 4 bytes
        // fontNames(3*8) -> 24 bytes
        // rot(2) if len==30 -> 2 bytes
        // Total 28 or 30.
        // Let's use 28.
        byte[] data = new byte[] {
            0x5A, 0x00, 0x21, (byte) 0xD3, (byte) 0xB1, (byte) 0x8A, 0x00, 0x00, 0x00,
            0x1C, 0x00, 0x00, 0x00, // RG Len 28, Res 3 bytes
            0x01, 0x00, 0x01, 0x00, // RG: LID 1, Res, Section 1, Res
            (byte) 0xC6, (byte) 0xD6, (byte) 0xD5, (byte) 0xE3, (byte) 0xD5, (byte) 0xC1, (byte) 0xD4, (byte) 0xC5, // FONTNAME
            (byte) 0xC3, (byte) 0xD7, (byte) 0xD5, (byte) 0xC1, (byte) 0xD4, (byte) 0xC5, (byte) 0xF1, (byte) 0xF1, // CPNAME11
            (byte) 0xC3, (byte) 0xE2, (byte) 0xD5, (byte) 0xC1, (byte) 0xD4, (byte) 0xC5, (byte) 0xF1, (byte) 0xF1  // CSNAME11
        };
        // Total data len: 9 + 4 + 28 = 41. SFI Len = 40 (0x0028)
        data[1] = 0x00;
        data[2] = 0x28;

        RoundTripTestUtils.assertRoundTrip(new MCF_MapCodedFont_Format1(), data);
    }

    @Test
    public void testMCFFormat2RoundTrip() throws Exception {
        // MCF F2: D3AB8A
        // RG: Len(2)=10, Triplet(8)
        // Total Len: 1 + 8 + 10 = 19. SFI Len = 18 (0x0012)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x12, (byte) 0xD3, (byte) 0xAB, (byte) 0x8A, 0x00, 0x00, 0x00,
            0x00, 0x0A, 0x08, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new MCF_MapCodedFont_Format2(), data);
    }

    @Test
    public void testMBCRoundTrip() throws Exception {
        // MBC: D3ABEB
        // RG: Len(2)=8 | Triplet(6)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xEB, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        // MBC.decodeAFP calls TripletParser.parseTriplet(sfData, offset, length, config)
        // Wait, MBC.java:
        // lengthOfRepeatingGroup = UtilBinaryDecoding.parseShort(sfData, offset, 2);
        // triplet = TripletParser.parseTriplet(sfData, offset, length, config);
        // It passes SAME offset to parseTriplet as it used for lengthOfRepeatingGroup.
        // But parseTriplet expects triplet at that offset.
        // This means MBC expects the repeating group to START with the triplet, but the triplet
        // IS the repeating group? No, lengthOfRepeatingGroup is 2 bytes at the start.
        // If MBC.decodeAFP is:
        // lengthOfRepeatingGroup = parseShort(sfData, offset, 2);
        // triplet = parseTriplet(sfData, offset + 2, length - 2, config); // Should be this?
        // Let's check MBC.java again.
        RoundTripTestUtils.assertRoundTrip(new MBC_MapBarCodeObject(), data);
    }

    @Test
    public void testMCDRoundTrip() throws Exception {
        // MCD: D3AB92
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0x92, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MCD_MapContainerData(), data);
    }

    @Test
    public void testMGORoundTrip() throws Exception {
        // MGO: D3ABBB
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xBB, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MGO_MapGraphicsObject(), data);
    }

    @Test
    public void testMIORoundTrip() throws Exception {
        // MIO: D3ABFB
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xFB, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MIO_MapImageObject(), data);
    }

    @Test
    public void testMMDRoundTrip() throws Exception {
        // MMD: D3ABCD
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xCD, 0x00, 0x00, 0x00,
            0x00, 0x08, 0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new MMD_MapMediaDestination(), data);
    }

    @Test
    public void testMMORoundTrip() throws Exception {
        // MMO: D3B1DF
        // MMO extends StructuredFieldBaseRepeatingGroups and has no preamble byte
        // RG: LID(1), Flag(1), Res(2), Name(8) -> Total 12 bytes.
        // Code says: RG length is lengthOfEachRepeatingGroup (from byte 0 of payload)
        // Total Len: 1 + 8 + 1 + 12 = 22. SFI Len = 21 (0x0015)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x15, (byte) 0xD3, (byte) 0xB1, (byte) 0xDF, 0x00, 0x00, 0x00,
            0x0C, 0x00, 0x00, 0x00, // RG Len 12, Res 3 bytes
            0x01, 0x00, 0x00, 0x00, // RG: LID 1, Flag 0, Res 2
            (byte) 0xD6, (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, 0x40, 0x40, 0x40, 0x40 // "OMAP1"
        };
        // Total data len: 9 + 4 + 12 = 25. SFI Len = 24 (0x0018)
        data[2] = 0x18;

        RoundTripTestUtils.assertRoundTrip(new MMO_MapMediumOverlay(), data);
    }

    @Test
    public void testOBPRoundTrip() throws Exception {
        // OBP: D3AC6B
        // Payload: ID(1) + RG(23) = 24 bytes.
        // Total: 9 + 24 = 33. SFLen = 32 (0x20).
        byte[] data = new byte[33];
        data[0] = 0x5A; data[1] = 0x00; data[2] = 0x20;
        data[3] = (byte) 0xD3; data[4] = (byte) 0xAC; data[5] = 0x6B;
        data[9] = 0x01; // ObjPosID
        data[10] = 23; // RG length
        data[32] = 0x01; // Reference Coordinate System: Standard
        RoundTripTestUtils.assertRoundTrip(new OBP_ObjectAreaPosition(), data);
    }

    @Test
    public void testMSURoundTrip() throws Exception {
        // MSU: D3ABEA
        // RG (10 bytes): Name(8) | Res(1) | LID(1)
        // Total Len: 1 + 8 + 10 = 19. SFLen = 18 (0x0012)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x12, (byte) 0xD3, (byte) 0xAB, (byte) 0xEA, 0x00, 0x00, 0x00,
            (byte) 0xE2, (byte) 0xE4, (byte) 0xD7, (byte) 0xD7, (byte) 0xD9, (byte) 0xC5, (byte) 0xE2, (byte) 0xE2, // "SUPPRESS"
            0x00, 0x01
        };
        RoundTripTestUtils.assertRoundTrip(new MSU_MapSuppression(), data);
    }
}
