package com.mgz.afp.ioca;

import com.mgz.afp.RoundTripTestUtils;
import com.mgz.afp.ioca.IPD_Segment.*;
import org.junit.jupiter.api.Test;

public class IOCARoundTripTest {

    @Test
    public void testIDDRoundTrip_Basic() throws Exception {
        // IDD: D3A6FB
        // UnitBase: 0x00 (10 inches)
        // xUnits: 2400 (0x0960)
        // yUnits: 2400 (0x0960)
        // xSize: 1000 (0x03E8)
        // ySize: 2000 (0x07D0)
        // Total Payload Len: 9
        // Total Len: 1 + 8 + 9 = 18. SFLen = 17 (0x0011)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x11, (byte) 0xD3, (byte) 0xA6, (byte) 0xFB, 0x00, 0x00, 0x00,
            0x00, 0x09, 0x60, 0x09, 0x60, 0x03, (byte) 0xE8, 0x07, (byte) 0xD0
        };
        RoundTripTestUtils.assertRoundTrip(new IDD_ImageDataDescriptor(), data);
    }

    @Test
    public void testIPDRoundTrip_Segments() throws Exception {
        // IPD: D3EEFB
        // Segments:
        // 1. ImageSize (0x94), Len 9, UnitBase=0, xUnits=2400, yUnits=2400, xSize=1000, ySize=2000
        //    -> 94 09 00 09 60 09 60 03 E8 07 D0
        // 2. ImageEncoding (0x95), Len 2, Comp=0x03 (NoComp), Rec=0x01 (RIDIC)
        //    -> 95 02 03 01
        // 3. ImageData (0xFE92), Len 4, Data=01 02 03 04
        //    -> FE 92 00 04 01 02 03 04
        // Total Segments Len: 11 + 4 + 8 = 23
        // Total Len: 1 + 8 + 23 = 32. SFLen = 31 (0x001F)
        byte[] data = new byte[] {
            0x5A, 0x00, 0x1F, (byte) 0xD3, (byte) 0xEE, (byte) 0xFB, 0x00, 0x00, 0x00,
            (byte) 0x94, 0x09, 0x00, 0x09, 0x60, 0x09, 0x60, 0x03, (byte) 0xE8, 0x07, (byte) 0xD0,
            (byte) 0x95, 0x02, 0x03, 0x01,
            (byte) 0xFE, (byte) 0x92, 0x00, 0x04, 0x01, 0x02, 0x03, 0x04
        };
        RoundTripTestUtils.assertRoundTrip(new IPD_ImagePictureData(), data);
    }

    @Test
    public void testBeginImageContentRoundTrip() throws Exception {
        // Type (0x91) | Len (0x01) | ObjType (0x01)
        byte[] data = new byte[] { (byte) 0x91, 0x01, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new BeginImageContent(), data);
    }

    @Test
    public void testEndImageContentRoundTrip() throws Exception {
        // Type (0x93) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x93, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new EndImageContent(), data);
    }

    @Test
    public void testIDESizeRoundTrip() throws Exception {
        // Type (0x96) | Len (0x01) | Bits (0x08)
        byte[] data = new byte[] { (byte) 0x96, 0x01, 0x08 };
        RoundTripTestUtils.assertRoundTrip(new IDESize(), data);
    }

    @Test
    public void testIDEStructureRoundTrip() throws Exception {
        // Type (0x9B) | Len (0x08) | Flags (0x00) | ColorSpace (0x01 = RGB) | Res (00 00 00) | CompSizes (08 08 08)
        byte[] data = new byte[] { (byte) 0x9B, 0x08, 0x00, 0x01, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08 };
        RoundTripTestUtils.assertRoundTrip(new IDEStructure(), data);
    }

    @Test
    public void testImageLUTIDRoundTrip() throws Exception {
        // Type (0x97) | Len (0x01) | LUTID (0x05)
        byte[] data = new byte[] { (byte) 0x97, 0x01, 0x05 };
        RoundTripTestUtils.assertRoundTrip(new ImageLUTID(), data);
    }

    @Test
    public void testFunctionSetIdentificationRoundTrip() throws Exception {
        // Type (0xF7) | Len (0x02) | Cat (0x01) | FctSet (0x0A)
        byte[] data = new byte[] { (byte) 0xF7, 0x02, 0x01, 0x0A };
        RoundTripTestUtils.assertRoundTrip(new FunctionSetIdentification(), data);
    }

    @Test
    public void testBeginSegmentRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_4.md [IOCA-4-011]
        // Type (0x70) | Len (0x04) | Name (0x01 02 03 04)
        byte[] data = new byte[] { 0x70, 0x04, 0x01, 0x02, 0x03, 0x04 };
        RoundTripTestUtils.assertRoundTrip(new BeginSegment(), data);
    }

    @Test
    public void testEndSegmentRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_4.md [IOCA-4-012]
        // Type (0x71) | Len (0x00)
        byte[] data = new byte[] { 0x71, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new EndSegment(), data);
    }

    @Test
    public void testImageEncodingRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-066]
        // Type (0x95) | Len (0x02) | Comp (0x03) | Rec (0x01)
        byte[] data = new byte[] { (byte) 0x95, 0x02, 0x03, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new ImageEncoding(), data);
    }

    @Test
    public void testBandImageRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-086]
        // Type (0x98) | Len (0x04) | NumBands (0x02) | Band1Size (0x08) | Band2Size (0x08)
        byte[] data = new byte[] { (byte) 0x98, 0x04, 0x02, 0x08, 0x08 };
        RoundTripTestUtils.assertRoundTrip(new BandImage(), data);
    }

    @Test
    public void testExternalAlgorithmSpecificationRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-115]
        // Type (0x9F) | Len (0x06) | Type(0x10=Comp) | Res(0) | ID(0x83=JPEG) | Res(0) | Ver(0x01) | Res(0) | Marker(0xC0) | Res(0,0,0)
        // Corrected Length based on code: Type(0x9F) | Len(10) | ...
        byte[] data = new byte[] {
            (byte) 0x9F, 0x0A, 0x10, 0x00,
            (byte) 0x83, 0x00, 0x01, 0x00, (byte) 0xC0, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new ExternalAlgorithmSpecification(), data);
    }

    @Test
    public void testImageSubsamplingRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-144]
        // Type (0xFECE) | Len (0x0004) | FieldType(0x01) | Len(0x02) | H(2) | V(2)
        byte[] data = new byte[] { (byte) 0xFE, (byte) 0xCE, 0x00, 0x04, 0x01, 0x02, 0x02, 0x02 };
        RoundTripTestUtils.assertRoundTrip(new ImageSubsampling(), data);
    }

    @Test
    public void testBeginTileRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-162]
        // Type (0x8C) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x8C, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new BeginTile(), data);
    }

    @Test
    public void testEndTileRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-165]
        // Type (0x8D) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x8D, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new EndTile(), data);
    }

    @Test
    public void testTilePositionRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-167]
        // Type (0xB5) | Len (0x08) | H(0) | V(0)
        byte[] data = new byte[] { (byte) 0xB5, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new TilePosition(), data);
    }

    @Test
    public void testTileSizeRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-171]
        // Type (0xB6) | Len (0x09) | H(100) | V(100) | RelRes(1)
        byte[] data = new byte[] { (byte) 0xB6, 0x09, 0x00, 0x00, 0x00, 0x64, 0x00, 0x00, 0x00, 0x64, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new TileSize(), data);
    }

    @Test
    public void testTileSetColorRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-180]
        // Type (0xB7) | Len (0x0B) | Space(1) | Res(0,0,0) | Bits(8,8,8,0) | Color(FF,00,00)
        byte[] data = new byte[] {
            (byte) 0xB7, 0x0B, 0x01, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08, 0x00, (byte) 0xFF, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new TileSetColor(), data);
    }

    @Test
    public void testIncludeTileRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-195]
        // Type (0xFEB8) | Len (0x0004) | ID(0x00000001)
        byte[] data = new byte[] { (byte) 0xFE, (byte) 0xB8, 0x00, 0x04, 0x00, 0x00, 0x00, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new IncludeTile(), data);
    }

    @Test
    public void testTileTOCRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-198]
        // Type (0xFEBB) | Len (0x001C) | Res(0,0) | [H(0), V(0), HS(100), VS(100), Rel(1), Comp(3), Off(0)]
        byte[] data = new byte[] {
            (byte) 0xFE, (byte) 0xBB, 0x00, 0x1C, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x64, 0x00, 0x00, 0x00, 0x64,
            0x01, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new TileTOC(), data);
    }

    @Test
    public void testBeginTransparencyMaskRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-222]
        // Type (0x8E) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x8E, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new BeginTransparencyMask(), data);
    }

    @Test
    public void testEndTransparencyMaskRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-225]
        // Type (0x8F) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x8F, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new EndTransparencyMask(), data);
    }

    @Test
    public void testSetBilevelImageColorRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-155]
        // Type (0xF6) | Len (0x04) | Area(1) | Res(0) | Color(0x0001)
        byte[] data = new byte[] { (byte) 0xF6, 0x04, 0x01, 0x00, 0x00, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new SetBilevelImageColor(), data);
    }

    @Test
    public void testSetExtendedBilevelImageColorRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-147]
        // Type (0xF4) | Len (0x0D) | Res(0) | Space(1) | Res(0,0,0,0) | Sizes(8,8,8,0) | Val(FF,00,00)
        byte[] data = new byte[] {
            (byte) 0xF4, 0x0D, 0x00, 0x01,
            0x00, 0x00, 0x00, 0x00,
            0x08, 0x08, 0x08, 0x00,
            (byte) 0xFF, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new SetExtendedBilevelImageColor(), data);
    }

    @Test
    public void testBandImageDataRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-235]
        // Type (0xFE9C) | Len (0x0007) | Band(1) | Res(0,0) | Data(01 02 03 04)
        byte[] data = new byte[] { (byte) 0xFE, (byte) 0x9C, 0x00, 0x07, 0x01, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04 };
        RoundTripTestUtils.assertRoundTrip(new BandImageData(), data);
    }

    @Test
    public void testNColorNamesRoundTrip() throws Exception {
        // Reference: specifications/markdown/ioca-reference-09/Chapter_5.md [IOCA-5-110]
        // Type (0xFEB3) | Len (0x000A) | Res(0,0) | [Res(0), Len(6), "COLOR1"]
        // Note: Code uses UTF-16BE for text representation in text field, but raw bytes for round trip.
        byte[] color1 = "COLOR1".getBytes(java.nio.charset.StandardCharsets.UTF_16BE);
        byte[] data = new byte[6 + 2 + color1.length];
        data[0] = (byte) 0xFE; data[1] = (byte) 0xB3;
        data[2] = 0x00; data[3] = (byte) (2 + 2 + color1.length);
        data[4] = 0x00; data[5] = 0x00;
        data[6] = 0x00; data[7] = (byte) color1.length;
        System.arraycopy(color1, 0, data, 8, color1.length);

        RoundTripTestUtils.assertRoundTrip(new nColorNames(), data);
    }
}
