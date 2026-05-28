package com.mgz.afp.lineData;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class LineDataRoundTripTest {

    @Test
    public void testBDXRoundTrip() throws Exception {
        // BDX: D3A8E3
        // Name (8): DMX1     (C4 D4 E7 F1 40 40 40 40)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA8, (byte) 0xE3, 0x00, 0x00, 0x00,
            (byte) 0xC4, (byte) 0xD4, (byte) 0xE7, (byte) 0xF1, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new BDX_BeginDataMapTransmitionSubcase(), data);
    }

    @Test
    public void testEDXRoundTrip() throws Exception {
        // EDX: D3A9E3
        // Name (8): DMX1     (C4 D4 E7 F1 40 40 40 40)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xE3, 0x00, 0x00, 0x00,
            (byte) 0xC4, (byte) 0xD4, (byte) 0xE7, (byte) 0xF1, 0x40, 0x40, 0x40, 0x40
        };
        RoundTripTestUtils.assertRoundTrip(new EDX_EndDataMapTransmitionSubcase(), data);
    }

    @Test
    public void testBPMRoundTrip() throws Exception {
        // BPM: D3A8CB
        // Name (8): PAGEDEF1 (D7 C1 C7 C5 C4 C5 C6 F1)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA8, (byte) 0xCB, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new BPM_BeginPageMap(), data);
    }

    @Test
    public void testEPMRoundTrip() throws Exception {
        // EPM: D3A9CB
        // Name (8): PAGEDEF1
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xCB, 0x00, 0x00, 0x00,
            (byte) 0xD7, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EPM_EndPageMap(), data);
    }

    @Test
    public void testDXDRoundTrip() throws Exception {
        // DXD: D3A6E3
        // Data: 0x00 01 00 FF
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xA6, (byte) 0xE3, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, (byte) 0xFF
        };
        RoundTripTestUtils.assertRoundTrip(new DXD_DataMapTransmitionSubcaseDescriptor(), data);
    }

    @Test
    public void testEDMRoundTrip() throws Exception {
        // EDM: D3A9CA
        // Name (8): DATAMAP1 (C4 C1 E3 C1 D4 C1 D7 F1)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xCA, 0x00, 0x00, 0x00,
            (byte) 0xC4, (byte) 0xC1, (byte) 0xE3, (byte) 0xC1, (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EDM_EndDataMap(), data);
    }

    @Test
    public void testIDMRoundTrip() throws Exception {
        // IDM: D3ABCA
        // Name (8): DATAMAP1
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xAB, (byte) 0xCA, 0x00, 0x00, 0x00,
            (byte) 0xC4, (byte) 0xC1, (byte) 0xE3, (byte) 0xC1, (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new IDM_InvokeDataMap(), data);
    }

    @Test
    public void testBDMRoundTrip() throws Exception {
        // BDM: D3A8CA
        // Name (8): DATAMAP1
        // DataFormat (1): 0x01 (UsingRCD)
        // Total Len: 1 + 8 + 9 = 18. SFLen = 17 (0x0011)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x11, (byte) 0xD3, (byte) 0xA8, (byte) 0xCA, 0x00, 0x00, 0x00,
            (byte) 0xC4, (byte) 0xC1, (byte) 0xE3, (byte) 0xC1, (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, (byte) 0xF1,
            0x01
        };
        RoundTripTestUtils.assertRoundTrip(new BDM_BeginDataMap(), data);
    }

    @Test
    public void testLNCRoundTrip() throws Exception {
        // LNC: D3AAE7
        // Number of SF (2): 0x000A (10)
        // Total Len: 1 + 8 + 2 = 11. SFLen = 10 (0x000A)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0A, (byte) 0xD3, (byte) 0xAA, (byte) 0xE7, 0x00, 0x00, 0x00,
            0x00, 0x0A
        };
        RoundTripTestUtils.assertRoundTrip(new LNC_LineDescriptorCount(), data);
    }

    @Test
    public void testFDSRoundTrip() throws Exception {
        // FDS: D3AAEC
        // Number of bytes (2): 0x0004 (4)
        // Total Len: 1 + 8 + 2 = 11. SFLen = 10 (0x000A)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0A, (byte) 0xD3, (byte) 0xAA, (byte) 0xEC, 0x00, 0x00, 0x00,
            0x00, 0x04
        };
        RoundTripTestUtils.assertRoundTrip(new FDS_FixedDataSize(), data);
    }

    @Test
    public void testFDXRoundTrip() throws Exception {
        // FDX: D3EEEC
        // Text: ABCD (C1 C2 C3 C4 in EBCDIC)
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xEE, (byte) 0xEC, 0x00, 0x00, 0x00,
            (byte) 0xC1, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4
        };
        RoundTripTestUtils.assertRoundTrip(new FDX_FixedDataText(), data);
    }

    @Test
    public void testLNDRoundTrip() throws Exception {
        // LND: D3A6E7
        // Flags (2): 0x2004 (GenerateInlinePosition_NewPosition, RelativeBaselinePosition_RelativePosition)
        // InlinePos (2): 0x0064 (100)
        // BaselinePos (2): 0xFF9C (-100 as signed short)
        // InlineOrient (2): 0x0000
        // BaselineOrient (2): 0x2D00
        // FontLocalId (1): 0x01
        // ChannelCode (1): 0x00
        // Skipping (2): 0x0000
        // Spacing (2): 0x0000
        // Reusing (2): 0x0000
        // Suppression (8): FILLER
        // ShiftOutFont (1): 0x00
        // DataStart (4): 0x00000001
        // DataLen (2): 0x000A
        // TextColor (2): 0x0000 (Default)
        // NextConditional (2): 0x0000
        // SubpageID (1): 0x00
        // CCPID (2): 0x0000
        // Total Len: 1 + 8 + 40 = 49. SFLen = 48 (0x0030)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x30, (byte) 0xD3, (byte) 0xA6, (byte) 0xE7, 0x00, 0x00, 0x00,
            0x20, 0x04, 0x00, 0x64, (byte) 0xFF, (byte) 0x9C, 0x00, 0x00, 0x2D, 0x00, 0x01, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x00,
            0x00, 0x00, 0x00, 0x01, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new LND_LineDescriptor(), data);
    }

    @Test
    public void testCCPRoundTrip() throws Exception {
        // CCP: D3A7CA
        // CCPID (2): 0x0001
        // NextCCPID (2): 0x0000
        // Flag (1): 0x80 (BeforeSubpageActions)
        // Reserved (1): 0x00
        // NumGroups (2): 0x0001
        // LenGroup (2): 0x0018 (24)
        // LenComp (2): 0x0004
        // -- Repeating Group --
        // Timing (1): 0x01 (Immediately)
        // MedMapAction (1): 0x02 (InvokeNamedMediumMap)
        // MedMapName (8): MEDMAP1  (D4 C5 C4 D4 C1 D7 F1 40)
        // DataMapAction (1): 0x00 (Ignore)
        // DataMapName (8): FILLER
        // Comparison (1): 0x01 (EqualTo)
        // ComparisonString (4): TEST (E3 C5 E2 E3)
        // Total Len: 1 + 8 + 12 + 24 = 45. SFLen = 44 (0x002C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x2C, (byte) 0xD3, (byte) 0xA7, (byte) 0xCA, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x00, (byte) 0x80, 0x00, 0x00, 0x01, 0x00, 0x18, 0x00, 0x04,
            0x01, 0x02, (byte) 0xD4, (byte) 0xC5, (byte) 0xC4, (byte) 0xD4, (byte) 0xC1, (byte) 0xD7, (byte) 0xF1, 0x40,
            0x00, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x01,
            (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new CCP_ConditionalProcessingControl(), data);
    }

    @Test
    public void testRCDRoundTrip() throws Exception {
        // RCD: D3A68D
        // RecordID (10): RECORDID01
        // RecordType (1): 0x00 (UsingLND)
        // Flags (3): 0x200400 (GenerateInlinePosition_NewPosition, RelativeBaselinePosition_RelativePosition)
        // Reserved (1): 0x00
        // InlinePos (2): 0x0064 (100)
        // BaselinePos (2): 0xFF9C (-100)
        // InlineOrient (2): 0x0000
        // BaselineOrient (2): 0x2D00
        // FontLocalId (1): 0x01
        // FieldRCDPointer (2): 0x0000
        // Suppression (8): FILLER
        // ShiftOutFont (1): 0x00
        // DataStart (4): 0x00000001
        // DataLen (2): 0x000A
        // NextConditional (2): 0x0000
        // SubpageID (1): 0x00
        // CCPID (2): 0x0000
        // StartingPage (2): 0x0000
        // EndSpace (2): 0x0000
        // FieldAlignment (1): 0x00
        // FieldDelimiter (2): 0x0000
        // FieldNumber (2): 0x0000
        // AddBaseline (2): 0x0000
        // Reserved (13): 0...0
        // Total Len: 1 + 8 + 70 = 79. SFLen = 78 (0x004E)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x4E, (byte) 0xD3, (byte) 0xA6, (byte) 0x8D, 0x00, 0x00, 0x00,
            (byte) 0xD9, (byte) 0xC5, (byte) 0xC3, (byte) 0xD6, (byte) 0xD9, (byte) 0xC4, (byte) 0xC9, (byte) 0xC4, (byte) 0xF0, (byte) 0xF1,
            0x00, 0x20, 0x04, 0x00, 0x00, 0x00, 0x64, (byte) 0xFF, (byte) 0x9C, 0x00, 0x00, 0x2D, 0x00, 0x01, 0x00, 0x00,
            0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new RCD_RecordDescriptor(), data);
    }

    @Test
    public void testXMDRoundTrip() throws Exception {
        // XMD: D3A68E
        // ElementType (1): 0x00
        // Flags (3): 0x200400 (GenerateInlinePosition_NewPosition, RelativeBaselinePosition_RelativePosition)
        // Reserved (1): 0x00
        // InlinePos (2): 0x0064 (100)
        // BaselinePos (2): 0xFF9C (-100)
        // InlineOrient (2): 0x0000
        // BaselineOrient (2): 0x2D00
        // FontLocalId (1): 0x01
        // FieldXMDPointer (2): 0x0000
        // Reserved (2): 0x0000
        // Suppression (8): FILLER
        // Reserved (1): 0x00
        // DataStart (3): 0x000001
        // DataLen (2): 0x000A
        // NextConditional (2): 0x0000
        // SubpageID (1): 0x00
        // CCPID (2): 0x0000
        // StartingPage (2): 0x0000
        // EndSpace (2): 0x0000
        // FieldAlignment (1): 0x00
        // FieldDelimiter (2): 0x0000
        // FieldNumber (2): 0x0000
        // AddBaseline (2): 0x0000
        // Reserved (13): 0...0
        // Total Len: 1 + 8 + 62 = 71. SFLen = 70 (0x0046)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x46, (byte) 0xD3, (byte) 0xA6, (byte) 0x8E, 0x00, 0x00, 0x00,
            0x00, 0x20, 0x04, 0x00, 0x00, 0x00, 0x64, (byte) 0xFF, (byte) 0x9C, 0x00, 0x00, 0x2D, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00,
            0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x00, 0x00, 0x00, 0x01, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new XMD_XMLDescriptor(), data);
    }
}
