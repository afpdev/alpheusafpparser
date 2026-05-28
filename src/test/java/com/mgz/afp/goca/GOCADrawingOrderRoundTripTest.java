package com.mgz.afp.goca;

import com.mgz.afp.RoundTripTestUtils;
import com.mgz.afp.goca.GAD_DrawingOrder.*;
import org.junit.jupiter.api.Test;

public class GOCADrawingOrderRoundTripTest {

    @Test
    public void testGSCOLRoundTrip() throws Exception {
        // GSCOL (0x0A) | Color (0x01 = Blue)
        byte[] data = new byte[] { 0x0A, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSCOL_SetColor(), data);
    }

    @Test
    public void testGSMXRoundTrip() throws Exception {
        // GSMX (0x0C) | Mix (0x01 = Overpaint)
        byte[] data = new byte[] { 0x0C, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSMX_SetMix(), data);
    }

    @Test
    public void testGSLWRoundTrip() throws Exception {
        // GSLW (0x19) | Width (0x02)
        byte[] data = new byte[] { 0x19, 0x02 };
        RoundTripTestUtils.assertRoundTrip(new GSLW_SetLineWidth(), data);
    }

    @Test
    public void testGSLERoundTrip() throws Exception {
        // GSLE (0x1A) | End (0x01 = Flat)
        byte[] data = new byte[] { 0x1A, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSLE_SetLineEnd(), data);
    }

    @Test
    public void testGSLJRoundTrip() throws Exception {
        // GSLJ (0x1B) | Join (0x01 = Bevel)
        byte[] data = new byte[] { 0x1B, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSLJ_SetLineJoin(), data);
    }

    @Test
    public void testGSCPRoundTrip() throws Exception {
        // GSCP (0x21) | Len (0x04) | X (0x0064) | Y (0x00C8)
        byte[] data = new byte[] { 0x21, 0x04, 0x00, 0x64, 0x00, (byte) 0xC8 };
        RoundTripTestUtils.assertRoundTrip(new GSCP_SetCurrentPosition(), data);
    }

    @Test
    public void testGSCARoundTrip() throws Exception {
        // GSCA (0x34) | Len (0x04) | X (0x0001) | Y (0x0000) (0 degrees)
        byte[] data = new byte[] { 0x34, 0x04, 0x00, 0x01, 0x00, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GSCA_SetCharacterAngle(), data);
    }

    @Test
    public void testGBARRoundTrip() throws Exception {
        // GBAR (0x68) | Flags (0x00)
        byte[] data = new byte[] { 0x68, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GBAR_BeginArea(), data);
    }

    @Test
    public void testGEARRoundTrip() throws Exception {
        // GEAR (0x60) | Len (0x04) | Data (0x01 02 03 04)
        byte[] data = new byte[] { 0x60, 0x04, 0x01, 0x02, 0x03, 0x04 };
        RoundTripTestUtils.assertRoundTrip(new GEAR_EndArea(), data);
    }

    @Test
    public void testGCBOXRoundTrip() throws Exception {
        // GCBOX (0x80) | Len (0x06) | Res (0x0000) | X (0x0100) | Y (0x0200)
        byte[] data = new byte[] { (byte) 0x80, 0x06, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GCBOX_BoxAtCurrentPosition(), data);
    }

    @Test
    public void testGCCHSTRoundTrip() throws Exception {
        // GCCHST (0x83) | Len (0x04) | "TEST" in EBCDIC
        byte[] data = new byte[] { (byte) 0x83, 0x04, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 };
        RoundTripTestUtils.assertRoundTrip(new GCCHST_CharacterStringAtCurrentPosition(), data);
    }

    @Test
    public void testGCHSTRoundTrip() throws Exception {
        // GCHST (0xC3) | Len (0x08) | X (0x0064) | Y (0x00C8) | "TEST" in EBCDIC
        byte[] data = new byte[] { (byte) 0xC3, 0x08, 0x00, 0x64, 0x00, (byte) 0xC8, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 };
        RoundTripTestUtils.assertRoundTrip(new GCHST_CharacterStringAtGivenPosition(), data);
    }

    @Test
    public void testGEXORoundTrip() throws Exception {
        // GEXO (0xFE) | Qual (0x01) | Len (0x0002) | Data (0xAABB)
        byte[] data = new byte[] { (byte) 0xFE, 0x01, 0x00, 0x02, (byte) 0xAA, (byte) 0xBB };
        RoundTripTestUtils.assertRoundTrip(new GEXO_ExtendedOrder(), data);
    }

    @Test
    public void testGNOP1RoundTrip() throws Exception {
        // Reference: GOCA Chapter 6 - GNOP1 (0x00)
        byte[] data = new byte[] { 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GNOP1_NopOperation(), data);
    }

    @Test
    public void testGCOMTRoundTrip() throws Exception {
        // Reference: GOCA Chapter 6 - GCOMT (0x01)
        byte[] data = new byte[] { 0x01, 0x04, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 }; // "TEST"
        RoundTripTestUtils.assertRoundTrip(new GCOMT_Comment(), data);
    }

    @Test
    public void testGBSEGRoundTrip() throws Exception {
        // Reference: GOCA Chapter 6 - GBSEG (0x70)
        // commandCode(1) | length(1) | name(4) | flag(1) | props(1) | dataLen(2) | predName(4)
        byte[] data = new byte[] {
            0x70, 0x0C,
            (byte) 0xE2, (byte) 0xC5, (byte) 0xC7, 0x40, // "SEG "
            0x00, 0x00,
            0x00, 0x00,
            0x40, 0x40, 0x40, 0x40 // predName "    "
        };
        RoundTripTestUtils.assertRoundTrip(new GBSEG_BeginSegment(), data);
    }

    @Test
    public void testGSLTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-871]
        // GSLT (0x18) | LineType (0x01 = Dotted)
        byte[] data = new byte[] { 0x18, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSLT_SetLineType(), data);
    }

    @Test
    public void testGSBMXRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-677]
        // GSBMX (0x0D) | Mode (0x05 = Leave Alone)
        byte[] data = new byte[] { 0x0D, 0x05 };
        RoundTripTestUtils.assertRoundTrip(new GSBMX_SetBackgroundMix(), data);
    }

    @Test
    public void testGESEGRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-118]
        // GESEG (0x71) | Reserved (0x00)
        byte[] data = new byte[] { 0x71, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GESEG_EndSegment(), data);
    }

    @Test
    public void testGSPSRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-951]
        // GSPS (0x08) | LCID (0x01)
        byte[] data = new byte[] { 0x08, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSPS_SetPatternSet(), data);
    }

    @Test
    public void testGSAPRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-659]
        // GSAP (0x22) | Len (0x08) | P (1) | Q (1) | R (0) | S (0)
        byte[] data = new byte[] { 0x22, 0x08, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GSAP_SetArcParameters(), data);
    }

    @Test
    public void testGSCCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-703]
        // GSCC (0x33) | Len (0x04) | Width (10) | Height (11)
        byte[] data = new byte[] { 0x33, 0x04, 0x00, 0x0A, 0x00, 0x0B };
        RoundTripTestUtils.assertRoundTrip(new GSCC_SetCharacterCell(), data);
    }

    @Test
    public void testGSCDRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-725]
        // GSCD (0x3A) | Direction (0x01 = Left to right)
        byte[] data = new byte[] { 0x3A, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSCD_SetCharacterDirection(), data);
    }

    @Test
    public void testGSCHRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-763]
        // GSCH (0x35) | Len (0x04) | HX (1) | HY (1)
        byte[] data = new byte[] { 0x35, 0x04, 0x00, 0x01, 0x00, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSCH_SetCharacterShear(), data);
    }

    @Test
    public void testGSCSRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-751]
        // GSCS (0x38) | LCID (0x01)
        byte[] data = new byte[] { 0x38, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSCS_SetCharacterSet(), data);
    }

    @Test
    public void testGSFLWRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-838]
        // GSFLW (0x11) | Len (0x02) | MH (1) | MFR (0)
        byte[] data = new byte[] { 0x11, 0x02, 0x01, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GSFLW_SetFractionLineWidth(), data);
    }

    @Test
    public void testGSECOLRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-825]
        // GSECOL (0x26) | Len (0x02) | Color (0x0001 = Blue)
        byte[] data = new byte[] { 0x26, 0x02, 0x00, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSECOL_SetExtendedColor(), data);
    }

    @Test
    public void testGSPTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-960]
        // GSPT (0x28) | Pattern (0x10 = Solid fill)
        byte[] data = new byte[] { 0x28, 0x10 };
        RoundTripTestUtils.assertRoundTrip(new GSPT_SetPatternSymbol(), data);
    }

    @Test
    public void testGSMTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-916]
        // GSMT (0x29) | Symbol (0x01 = Cross)
        byte[] data = new byte[] { 0x29, 0x01 };
        RoundTripTestUtils.assertRoundTrip(new GSMT_SetMarkerSymbol(), data);
    }

    @Test
    public void testGSMCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-892]
        // GSMC (0x37) | Len (0x04) | Width (7) | Height (7)
        byte[] data = new byte[] { 0x37, 0x04, 0x00, 0x07, 0x00, 0x07 };
        RoundTripTestUtils.assertRoundTrip(new GSMC_SetMarkerCell(), data);
    }

    @Test
    public void testGSMSRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-908]
        // GSMS (0x3C) | LCID (0x00 = Default)
        byte[] data = new byte[] { 0x3C, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GSMS_SetMarkerSet(), data);
    }

    @Test
    public void testGLINERoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-399]
        // GLINE (0xC1) | Len (0x08) | P1(100, 200) | P2(300, 400)
        byte[] data = new byte[] { (byte) 0xC1, 0x08, 0x00, 0x64, 0x00, (byte) 0xC8, 0x01, 0x2C, 0x01, (byte) 0x90 };
        RoundTripTestUtils.assertRoundTrip(new GLINE_LineAtGivenPosition(), data);
    }

    @Test
    public void testGMRKRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-465]
        // GMRK (0xC2) | Len (0x04) | P1(10, 20)
        byte[] data = new byte[] { (byte) 0xC2, 0x04, 0x00, 0x0A, 0x00, 0x14 };
        RoundTripTestUtils.assertRoundTrip(new GMRK_MarkerAtGivenPosition(), data);
    }

    @Test
    public void testGBOXRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-195]
        // GBOX (0xC0) | Len (0x0E) | Res(0) | P1(100, 200) | P2(300, 400) | RoundX(5) | RoundY(10)
        byte[] data = new byte[] {
            (byte) 0xC0, 0x0E, 0x00, 0x00,
            0x00, 0x64, 0x00, (byte) 0xC8,
            0x01, 0x2C, 0x01, (byte) 0x90,
            0x00, 0x05, 0x00, 0x0A
        };
        RoundTripTestUtils.assertRoundTrip(new GBOX_BoxAtGivenPosition(), data);
    }

    @Test
    public void testGRLINERoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-622]
        // GRLINE (0xE1) | Len (0x06) | Start(100, 200) | Offset(10, 20)
        byte[] data = new byte[] { (byte) 0xE1, 0x06, 0x00, 0x64, 0x00, (byte) 0xC8, 0x0A, 0x14 };
        RoundTripTestUtils.assertRoundTrip(new GRLINE_RelativeLineAtGivenPosition(), data);
    }

    @Test
    public void testGCLINERoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-411]
        // GCLINE (0x81) | Len (0x04) | P1(500, 600)
        byte[] data = new byte[] { (byte) 0x81, 0x04, 0x01, (byte) 0xF4, 0x02, 0x58 };
        RoundTripTestUtils.assertRoundTrip(new GCLINE_LineAtCurrentPosition(), data);
    }

    @Test
    public void testGCMRKRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-475]
        // GCMRK (0x82) | Len (0x04) | P1(700, 800)
        byte[] data = new byte[] { (byte) 0x82, 0x04, 0x02, (byte) 0xBC, 0x03, 0x20 };
        RoundTripTestUtils.assertRoundTrip(new GCMRK_MarkerAtCurrentPosition(), data);
    }

    @Test
    public void testGCRLINERoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-634]
        // GCRLINE (0xA1) | Len (0x02) | Offset(5, 5)
        byte[] data = new byte[] { (byte) 0xA1, 0x02, 0x05, 0x05 };
        RoundTripTestUtils.assertRoundTrip(new GCRLINE_RelativeLineAtCurrentPosition(), data);
    }

    @Test
    public void testGSCRRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-739]
        // GSCR (0x39) | Precision (0x02 = Character precision)
        byte[] data = new byte[] { 0x39, 0x02 };
        RoundTripTestUtils.assertRoundTrip(new GSCR_SetCharacterPrecision(), data);
    }

    @Test
    public void testGSMPRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-083]
        // GSMP (0x3B) | Precision (0x02)
        byte[] data = new byte[] { 0x3B, 0x02 };
        RoundTripTestUtils.assertRoundTrip(new GSMP_SetMarkerPrecision(), data);
    }

    @Test
    public void testGEPROLRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-330]
        // GEPROL (0x3E) | Reserved (0x00)
        byte[] data = new byte[] { 0x3E, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GEPROL_EndProlog(), data);
    }

    @Test
    public void testGSPIKRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-117]
        // GSPIK (0x43) | ID (0x07)
        byte[] data = new byte[] { 0x43, 0x07 };
        RoundTripTestUtils.assertRoundTrip(new GSPIK_SetPickIdentifier(), data);
    }

    @Test
    public void testGSGCHRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-652]
        // GSGCH (0x04) | Len (0x02) | ID (0x01) | Param (0xAA)
        byte[] data = new byte[] { 0x04, 0x02, 0x01, (byte) 0xAA };
        RoundTripTestUtils.assertRoundTrip(new GSGCH_SegmentCharacteristics(), data);
    }

    @Test
    public void testGSPRPRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-938]
        // GSPRP (0xA0) | Len (0x06) | Flags (0x00) | Res (0x00) | X (0x0064) | Y (0x00C8)
        byte[] data = new byte[] { (byte) 0xA0, 0x06, 0x00, 0x00, 0x00, 0x64, 0x00, (byte) 0xC8 };
        RoundTripTestUtils.assertRoundTrip(new GSPRP_SetPatternReferencePoint(), data);
    }

    @Test
    public void testGCPARCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-511]
        // GCPARC (0xA3) | Len (0x0E) | Center(100, 200) | MH(1) | MFR(0) | Start(0) | Sweep(180*65536)
        byte[] data = new byte[] {
            (byte) 0xA3, 0x0E,
            0x00, 0x64, 0x00, (byte) 0xC8,
            0x01, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x01, 0x20, 0x00, 0x00 // 180 * 65536 = 11796480 = 0x01200000
        };
        RoundTripTestUtils.assertRoundTrip(new GCPARC_PartialArcAtCurrentPosition(), data);
    }

    @Test
    public void testGFARCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-366]
        // GFARC (0xC7) | Len (0x06) | Center(10, 20) | MH(1) | MFR(0)
        byte[] data = new byte[] {
            (byte) 0xC7, 0x06,
            0x00, 0x0A, 0x00, 0x14,
            0x01, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new GFARC_FullArcAtGivenPosition(), data);
    }

    @Test
    public void testGCFARCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-373]
        // GCFARC (0x87) | Len (0x02) | MH(2) | MFR(0)
        byte[] data = new byte[] { (byte) 0x87, 0x02, 0x02, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GCFARC_FullArcAtCurrentPosition(), data);
    }

    @Test
    public void testGPARCRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-500]
        // GPARC (0xE3) | Len (0x12) | Start(50, 60) | Center(100, 200) | MH(1) | MFR(0) | StartAng(0) | Sweep(90)
        byte[] data = new byte[] {
            (byte) 0xE3, 0x12,
            0x00, 0x32, 0x00, 0x3C,
            0x00, 0x64, 0x00, (byte) 0xC8,
            0x01, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x00, (byte) 0x5A, 0x00, 0x00 // 90 * 65536 = 5898240 = 0x005A0000
        };
        RoundTripTestUtils.assertRoundTrip(new GPARC_PartialArcAtGivenPosition(), data);
    }

    @Test
    public void testGFLTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-338]
        // GFLT (0xC5) | Len (0x08) | P1(10, 10) | P2(20, 20)
        byte[] data = new byte[] { (byte) 0xC5, 0x08, 0x00, 0x0A, 0x00, 0x0A, 0x00, 0x14, 0x00, 0x14 };
        RoundTripTestUtils.assertRoundTrip(new GFLT_FilletAtGivenPosition(), data);
    }

    @Test
    public void testGCFLTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-350]
        // GCFLT (0x85) | Len (0x04) | P1(100, 100)
        byte[] data = new byte[] { (byte) 0x85, 0x04, 0x00, 0x64, 0x00, 0x64 };
        RoundTripTestUtils.assertRoundTrip(new GCFLT_FilletAtCurrentPosition(), data);
    }

    @Test
    public void testGCCBEZRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-271]
        // GCCBEZ (0xA5) | Len (0x0C) | P1, P2, P3
        byte[] data = new byte[] {
            (byte) 0xA5, 0x0C,
            0x00, 0x01, 0x00, 0x01,
            0x00, 0x02, 0x00, 0x02,
            0x00, 0x03, 0x00, 0x03
        };
        RoundTripTestUtils.assertRoundTrip(new GCCBEZ_CubicBezierCurveAtCurrentPosition(), data);
    }

    @Test
    public void testGCBEZRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-253]
        // GCBEZ (0xE5) | Len (0x10) | P0, P1, P2, P3
        byte[] data = new byte[] {
            (byte) 0xE5, 0x10,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x01, 0x00, 0x01,
            0x00, 0x02, 0x00, 0x02,
            0x00, 0x03, 0x00, 0x03
        };
        RoundTripTestUtils.assertRoundTrip(new GCBEZ_CubicBezierCurveAtGivenPosition(), data);
    }

    @Test
    public void testGSCLTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-794]
        // GSCLT (0x20) | Len (0x04) | Dash(1, 0) | Move(1, 0)
        byte[] data = new byte[] { 0x20, 0x04, 0x01, 0x00, 0x01, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GSCLT_SetCustomLineType(), data);
    }

    @Test
    public void testGBCPRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-139]
        // GBCP (0xDE) | Len (0x0D) | Res(0) | Flags(0) | Set(1) | Sym(2) | Win(0, 10, 0, 10)
        byte[] data = new byte[] {
            (byte) 0xDE, 0x0D,
            0x00, 0x00,
            0x00, 0x01, 0x02,
            0x00, 0x00, 0x00, 0x0A,
            0x00, 0x00, 0x00, 0x0A
        };
        RoundTripTestUtils.assertRoundTrip(new GBCP_BeginCustomPattern(), data);
    }

    @Test
    public void testGECPRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-317]
        // GECP (0x5E) | Reserved (0x00)
        byte[] data = new byte[] { 0x5E, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GECP_EndCustomPattern(), data);
    }

    @Test
    public void testGDPTRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-294]
        // GDPT (0xDF) | Len (0x04) | Res(0) | Set(1) | Sym(2)
        byte[] data = new byte[] { (byte) 0xDF, 0x04, 0x00, 0x00, 0x01, 0x02 };
        RoundTripTestUtils.assertRoundTrip(new GDPT_DeletePattern(), data);
    }

    @Test
    public void testGSPCOLRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-973]
        // GSPCOL (0xB2) | Len (0x0D) | Res(0) | Space(0x01=RGB) | Res(0) | Bits(8,8,8,0) | Val(FF,00,00)
        byte[] data = new byte[] {
            (byte) 0xB2, 0x0D,
            0x00, 0x01,
            0x00, 0x00, 0x00, 0x00,
            0x08, 0x08, 0x08, 0x00,
            (byte) 0xFF, 0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new GSPCOL_SetProcessColor(), data);
    }

    @Test
    public void testGIMDRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-388]
        // GIMD (0x92) | Len (0x04) | Data(01 02 03 04)
        byte[] data = new byte[] { (byte) 0x92, 0x04, 0x01, 0x02, 0x03, 0x04 };
        RoundTripTestUtils.assertRoundTrip(new GIMD_ImageData(), data);
    }

    @Test
    public void testGEIMGRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-323]
        // GEIMG (0x93) | Len (0x00)
        byte[] data = new byte[] { (byte) 0x93, 0x00 };
        RoundTripTestUtils.assertRoundTrip(new GEIMG_EndImage(), data);
    }

    @Test
    public void testGBIMGRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-164]
        // GBIMG (0xD1) | Len (0x0A) | Origin(10, 20) | Format(0) | Res(0) | Width(100) | Height(200)
        byte[] data = new byte[] {
            (byte) 0xD1, 0x0A,
            0x00, 0x0A, 0x00, 0x14,
            0x00, 0x00,
            0x00, 0x64, 0x00, (byte) 0xC8
        };
        RoundTripTestUtils.assertRoundTrip(new GBIMG_BeginImageAtGivenPosition(), data);
    }

    @Test
    public void testGCBIMGRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-173]
        // GCBIMG (0x91) | Len (0x06) | Format(0) | Res(0) | Width(100) | Height(200)
        byte[] data = new byte[] {
            (byte) 0x91, 0x06,
            0x00, 0x00,
            0x00, 0x64, 0x00, (byte) 0xC8
        };
        RoundTripTestUtils.assertRoundTrip(new GCBIMG_BeginImageAtCurrentPosition(), data);
    }

    @Test
    public void testGLGDRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-427]
        // GEXO (0xFE) | Qual (0xDC) | Len (0x001F) | ...
        // Res(0000) | Set(1) | Sym(2) | Start(0,0) | End(10,10)
        // ColSpecS: Len(13) | Res(0) | Space(1) | Res(0) | Bits(8,8,8,0) | Val(FF,00,00)
        // EndCol: (00,FF,00)
        // Outside: (0,0)
        byte[] data = new byte[] {
            (byte) 0xFE, (byte) 0xDC, 0x00, 0x1F,
            0x00, 0x00, 0x01, 0x02,
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x0A, 0x00, 0x0A,
            0x0D, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08, 0x00, (byte) 0xFF, 0x00, 0x00,
            0x00, (byte) 0xFF, 0x00,
            0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new GLGD_LinearGradient(), data);
    }

    @Test
    public void testGRGDRoundTrip() throws Exception {
        // Reference: specifications/markdown/afp-goca-reference-03/Chapter_7.md [GOCA-7-540]
        // GEXO (0xFE) | Qual (0xDD) | Len (0x0023) | ...
        // Res(0000) | Set(1) | Sym(2) | Start(0,0,1,0) | End(10,10,1,0)
        // ColSpecS: same as above
        // EndCol: (00,FF,00)
        // Outside: (0,0)
        byte[] data = new byte[] {
            (byte) 0xFE, (byte) 0xDD, 0x00, 0x23,
            0x00, 0x00, 0x01, 0x02,
            0x00, 0x00, 0x00, 0x00, 0x01, 0x00,
            0x00, 0x0A, 0x00, 0x0A, 0x01, 0x00,
            0x0D, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08, 0x00, (byte) 0xFF, 0x00, 0x00,
            0x00, (byte) 0xFF, 0x00,
            0x00, 0x00
        };
        RoundTripTestUtils.assertRoundTrip(new GRGD_RadialGradient(), data);
    }
}
