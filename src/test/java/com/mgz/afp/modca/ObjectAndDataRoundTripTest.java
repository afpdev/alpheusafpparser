package com.mgz.afp.modca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;

public class ObjectAndDataRoundTripTest {

    @Test
    public void testBOCRoundTrip() throws Exception {
        // BOC: D3A892
        // Name (8): OBJCONT1 (D6 C2 D1 C3 D6 D5 E3 F1)
        // Triplet (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Len: 1 + 8 + 14 = 23. SFLen = 22 (0x0016)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0x92, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xC2, (byte) 0xD1, (byte) 0xC3, (byte) 0xD6, (byte) 0xD5, (byte) 0xE3, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BOC_BeginObjectContainer(), data);
    }

    @Test
    public void testEOCRoundTrip() throws Exception {
        // EOC: D3A992
        // Name (8): OBJCONT1
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0x92, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xC2, (byte) 0xD1, (byte) 0xC3, (byte) 0xD6, (byte) 0xD5, (byte) 0xE3, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EOC_EndObjectContainer(), data);
    }

    @Test
    public void testOCDRoundTrip() throws Exception {
        // OCD: D3EE92
        // Data: 0x01 02 03 04
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xEE, (byte) 0x92, 0x00, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04
        };
        RoundTripTestUtils.assertRoundTrip(new OCD_ObjectContainerData(), data);
    }

    @Test
    public void testBIMRoundTrip() throws Exception {
        // BIM: D3A8FB
        // Name (8): IMAGE001 (C9 D4 C1 C7 C5 F0 F0 F1)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA8, (byte) 0xFB, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD4, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new BIM_BeginImageObject(), data);
    }

    @Test
    public void testEIMRoundTrip() throws Exception {
        // EIM: D3A9FB
        // Name (8): IMAGE001
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xFB, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD4, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EIM_EndImageObject(), data);
    }

    @Test
    public void testBIIRoundTrip() throws Exception {
        // BII: D3A87B
        // Name (8): IMIMAGE1 (C9 D4 C9 D4 C1 C7 C5 F1)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA8, (byte) 0x7B, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD4, (byte) 0xC9, (byte) 0xD4, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new BII_BeginIMImageObject(), data);
    }

    @Test
    public void testEIIRoundTrip() throws Exception {
        // EII: D3A97B
        // Name (8): IMIMAGE1
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0x7B, 0x00, 0x00, 0x00,
            (byte) 0xC9, (byte) 0xD4, (byte) 0xC9, (byte) 0xD4, (byte) 0xC1, (byte) 0xC7, (byte) 0xC5, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EII_EndIMImageObject(), data);
    }

    @Test
    public void testIRDRoundTrip() throws Exception {
        // IRD: D3EE7B
        // Data: 0xFF 0x00 0xAA 0x55
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xEE, (byte) 0x7B, 0x00, 0x00, 0x00,
            (byte) 0xFF, 0x00, (byte) 0xAA, 0x55
        };
        RoundTripTestUtils.assertRoundTrip(new IRD_IMImageRasterData(), data);
    }

    @Test
    public void testNOPRoundTrip() throws Exception {
        // NOP: D3EEEE
        // Data: 0x01 02 03 04
        // Total Len: 1 + 8 + 4 = 13. SFLen = 12 (0x000C)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x0C, (byte) 0xD3, (byte) 0xEE, (byte) 0xEE, 0x00, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04
        };
        RoundTripTestUtils.assertRoundTrip(new NOP_NoOperation(), data);
    }

    @Test
    public void testEOGRoundTrip() throws Exception {
        // EOG: D3A9C7
        // Name (8): OBJENVG1 (D6 C2 D1 C5 D5 E5 C7 F1)
        // Total Len: 1 + 8 + 8 = 17. SFLen = 16 (0x0010)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x10, (byte) 0xD3, (byte) 0xA9, (byte) 0xC7, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xC2, (byte) 0xD1, (byte) 0xC5, (byte) 0xD5, (byte) 0xE5, (byte) 0xC7, (byte) 0xF1
        };
        RoundTripTestUtils.assertRoundTrip(new EOG_EndObjectEnvironmentGroup(), data);
    }

    @Test
    public void testIOBRoundTrip() throws Exception {
        // IOB: D3AFC3
        // Name (8): OBJ00001 (D6 C2 D1 F0 F0 F0 F0 F1)
        // Reserved (1): 00
        // ObjectType (1): 0x92 (OtherObjectData)
        // xOrigin (3): 0x000100
        // yOrigin (3): 0x000200
        // xRotation (2): 0x0000 (ori0)
        // yRotation (2): 0x2D00 (ori90)
        // xOriginOfContent (3): 0x000000
        // yOriginOfContent (3): 0x000000
        // RefCoordSystem (1): 0x01
        // Triplets (6): Comment "TEST" (06 65 E3 C5 E2 E3)
        // Total Payload Len: 8 + 1 + 1 + 3 + 3 + 2 + 2 + 3 + 3 + 1 + 6 = 33
        // Total Len: 1 + 8 + 33 = 42. SFLen = 41 (0x0029)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x29, (byte) 0xD3, (byte) 0xAF, (byte) 0xC3, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xC2, (byte) 0xD1, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF1,
            0x00, (byte) 0x92, 0x00, 0x01, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x2D, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new IOB_IncludeObject(), data);
    }

    @Test
    public void testBOGRoundTrip() throws Exception {
        // BOG: D3A8C7
        // Name (8): OBJENVG1
        // Triplet (6): Comment "TEST"
        byte[] data = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xA8, (byte) 0xC7, 0x00, 0x00, 0x00,
            (byte) 0xD6, (byte) 0xC2, (byte) 0xD1, (byte) 0xC5, (byte) 0xD5, (byte) 0xE5, (byte) 0xC7, (byte) 0xF1,
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };
        RoundTripTestUtils.assertRoundTrip(new BOG_BeginObjectEnvironmentGroup(), data);
    }

    @Test
    public void testOBPRoundTrip() throws Exception {
        // OBP: D3AC6B
        // ID (1): 01
        // RG: Len(1)=23, xOrigin(3)=0x100, yOrigin(3)=0x200, xRot(2)=0, yRot(2)=0x2D00, Res(1)=0,
        //     xContent(3)=0, yContent(3)=0, xRotCont(2)=0, yRotCont(2)=0x2D00, Ref(1)=1
        // Total Len: 1 + 8 + 1 + 23 = 33. SFI Len = 32 (0x0020)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x20, (byte) 0xD3, (byte) 0xAC, 0x6B, 0x00, 0x00, 0x00,
            0x01,
            0x17, 0x00, 0x01, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x2D, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x2D, 0x00, 0x01
        };
        RoundTripTestUtils.assertRoundTrip(new OBP_ObjectAreaPosition(), data);
    }

    @Test
    public void testIIDRoundTrip() throws Exception {
        // IID: D3A67B
        // Payload: constantData0_11 (12) | xUB(1) | yUB(1) | xU(2) | yU(2) | xS(2) | yS(2) | c22_27(6) | xCS(2) | yCS(2) | c32_33(2) | color(2)
        // Total Payload Len: 12+2+4+4+6+4+2+2 = 36
        // Total Len: 1 + 8 + 36 = 45. SFLen = 44 (0x002C)
        byte[] data = new byte[45];
        data[0] = 0x5A;
        data[1] = 0x00;
        data[2] = 0x2C;
        data[3] = (byte) 0xD3;
        data[4] = (byte) 0xA6;
        data[5] = 0x7B;
        // xUB = 0, yUB = 0
        data[21] = 0x00;
        data[22] = 0x00;
        // xU=2400 (0960), yU=2400 (0960), xS=1000 (03E8), yS=2000 (07D0)
        data[23] = 0x09; data[24] = 0x60;
        data[25] = 0x09; data[26] = 0x60;
        data[27] = 0x03; data[28] = (byte)0xE8;
        data[29] = 0x07; data[30] = (byte)0xD0;
        // xCS=240 (00F0), yCS=240 (00F0)
        data[37] = 0x00; data[38] = (byte)0xF0;
        data[39] = 0x00; data[40] = (byte)0xF0;
        // c32_33 = 00 00
        data[41] = 0x00; data[42] = 0x00;
        // color = 0007 (Blue)
        data[43] = 0x00; data[44] = 0x07;

        RoundTripTestUtils.assertRoundTrip(new IID_IMImageInputDescriptor(), data);
    }

    @Test
    public void testICPRoundTrip() throws Exception {
        // ICP: D3AC7B
        // Payload (12): xOff(2) | yOff(2) | xSize(2) | ySize(2) | xFill(2) | yFill(2)
        // Total Len: 1 + 8 + 12 = 21. SFLen = 20 (0x0014)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x14, (byte) 0xD3, (byte) 0xAC, (byte) 0x7B, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x02, 0x03, (byte) 0xE8, 0x07, (byte) 0xD0, 0x03, (byte) 0xE8, 0x07, (byte) 0xD0
        };
        RoundTripTestUtils.assertRoundTrip(new ICP_IMImageCellPosition(), data);
    }

    @Test
    public void testOCDWithMOCARoundTrip() throws Exception {
        // [MODCA-5-304] [MOCA-4-001]
        // OCD with MOCA payload
        int mocaHeaderSize = 50; // DES + AFPT + NONE + Reserved + MONameLen(0)
        byte[] mocaHeader = new byte[mocaHeaderSize];
        // MOLength (4B) = 60
        mocaHeader[3] = 60;
        // HeaderLength (2B) = 46
        mocaHeader[5] = 46;
        // MOType "DES"
        System.arraycopy("DES".getBytes(java.nio.charset.StandardCharsets.UTF_16BE), 0, mocaHeader, 6, 6);
        // MOFormat "AFPT"
        System.arraycopy("AFPT".getBytes(java.nio.charset.StandardCharsets.UTF_16BE), 0, mocaHeader, 12, 8);
        // MOCompression "NONE"
        System.arraycopy("NONE@@@@@@@@".getBytes(java.nio.charset.StandardCharsets.UTF_16BE), 0, mocaHeader, 20, 20);
        // MONameLength (2B) = 0 (48-49)

        byte[] mocaData = "MOCADATA".getBytes(java.nio.charset.StandardCharsets.UTF_16BE);
        // "MOCADATA" in UTF-16BE is 16 bytes.
        // Header is 50 bytes. Total 66 bytes.
        byte[] fullMoca = new byte[66];
        // MOLength (4B) = 66
        mocaHeader[3] = 66;
        System.arraycopy(mocaHeader, 0, fullMoca, 0, mocaHeaderSize);
        System.arraycopy(mocaData, 0, fullMoca, 50, mocaData.length);

        // OCD SF: D3EE92. Payload len = 66. SFLen = 9 + 66 - 1 = 74 (0x004A)
        byte[] data = new byte[1 + 8 + 66];
        data[0] = 0x5A;
        data[1] = 0x00;
        data[2] = 0x4A;
        data[3] = (byte) 0xD3;
        data[4] = (byte) 0xEE;
        data[5] = (byte) 0x92;
        System.arraycopy(fullMoca, 0, data, 9, 66);

        RoundTripTestUtils.assertRoundTrip(new OCD_ObjectContainerData(), data);
    }

    @Test
    public void testIOCRoundTrip() throws Exception {
        // IOC: D3A77B
        // Payload: xO(3) | yO(3) | xR(2) | yR(2) | c10_17(8) | xM(2) | yM(2) | c22_23(2) -> 24 bytes
        // Total Len: 1 + 8 + 24 = 33. SFLen = 32 (0x0020)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x20, (byte) 0xD3, (byte) 0xA7, (byte) 0x7B, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x03, (byte) 0xE8, 0x03, (byte) 0xE8, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new IOC_IMImageOutputControl(), data);
    }
}
