package com.mgz.afp.ptoca.controlSequence;

import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.PTOCAControlSequenceParser;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.*;
import com.mgz.util.UtilBinaryDecoding;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PTOCAControlSequenceRoundTripTest {

    private void assertCSRoundTrip(PTOCAControlSequence cs, byte[] payload, boolean isChained) throws Exception {
        ControlSequenceIntroducer csi = new ControlSequenceIntroducer();
        String className = cs.getClass().getSimpleName();
        ControlSequenceFunctionType type = ControlSequenceFunctionType.valueOf(className);
        csi.setControlSequenceFunctionType(type);
        csi.setChained(isChained);
        csi.setLength((short) (payload.length + 2));
        if (!isChained) {
            csi.setCsPrefix((short) 0x2B);
            csi.setCsClass((short) 0xD3);
        } else {
            csi.setCsPrefix((short) -1);
            csi.setCsClass((short) -1);
        }
        cs.setCsi(csi);

        AFPParserConfiguration config = new AFPParserConfiguration();
        cs.decodeAFP(payload, 0, payload.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cs.writeAFP(baos, config);
        assertArrayEquals(payload, baos.toByteArray(), "Payload round-trip failed for " + className);

        byte[] csiBytes = csi.toBytes();
        if (isChained) {
            assertArrayEquals(new byte[]{(byte) csi.getLength(), (byte) type.toByte(isChained)}, csiBytes);
        } else {
            assertArrayEquals(new byte[]{(byte) 0x2B, (byte) 0xD3, (byte) csi.getLength(), (byte) type.toByte(isChained)}, csiBytes);
        }
    }

    @Test
    public void testAMB_AbsoluteMoveBaselineRoundTrip() throws Exception {
        assertCSRoundTrip(new AMB_AbsoluteMoveBaseline(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testAMI_AbsoluteMoveInlineRoundTrip() throws Exception {
        assertCSRoundTrip(new AMI_AbsoluteMoveInline(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testBLN_BeginLineRoundTrip() throws Exception {
        assertCSRoundTrip(new BLN_BeginLine(), new byte[]{}, false);
    }

    @Test
    public void testBSU_BeginSuppressionRoundTrip() throws Exception {
        assertCSRoundTrip(new BSU_BeginSuppression(), new byte[]{0x01}, false);
    }

    @Test
    public void testDBR_DrawBaxisRuleRoundTrip() throws Exception {
        assertCSRoundTrip(new DBR_DrawBaxisRule(), new byte[]{0x00, 0x64}, false);
        assertCSRoundTrip(new DBR_DrawBaxisRule(), new byte[]{0x00, 0x64, 0x00, 0x05, 0x00}, false);
    }

    @Test
    public void testDIR_DrawIaxisRuleRoundTrip() throws Exception {
        assertCSRoundTrip(new DIR_DrawIaxisRule(), new byte[]{0x00, 0x64}, false);
        assertCSRoundTrip(new DIR_DrawIaxisRule(), new byte[]{0x00, 0x64, 0x00, 0x05, 0x00}, false);
    }

    @Test
    public void testESU_EndSuppressionRoundTrip() throws Exception {
        assertCSRoundTrip(new ESU_EndSuppression(), new byte[]{0x01}, false);
    }

    @Test
    public void testNOP_NoOperationRoundTrip() throws Exception {
        assertCSRoundTrip(new NOP_NoOperation(), new byte[]{0x01, 0x02, 0x03}, false);
    }

    @Test
    public void testOVS_OverstrikeRoundTrip() throws Exception {
        assertCSRoundTrip(new OVS_Overstrike(), new byte[]{0x01, 0x00, 0x64}, false);
        OVS_Overstrike ovs = new OVS_Overstrike();
        ovs.decodeAFP(new byte[]{0x01, 0x00, (byte) 0xC1}, 0, 3, new AFPParserConfiguration());
        assertEquals("A", ovs.getText());
    }

    @Test
    public void testRMB_RelativeMoveBaselineRoundTrip() throws Exception {
        assertCSRoundTrip(new RMB_RelativeMoveBaseline(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testRMI_RelativeMoveInlineRoundTrip() throws Exception {
        assertCSRoundTrip(new RMI_RelativeMoveInline(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testRPS_RepeatStringRoundTrip() throws Exception {
        assertCSRoundTrip(new RPS_RepeatString(), new byte[]{0x00, 0x05, 0x01, 0x02}, false);
    }

    @Test
    public void testSBI_SetBaselineIncrementRoundTrip() throws Exception {
        assertCSRoundTrip(new SBI_SetBaselineIncrement(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testSCFL_SetCodedFontLocalRoundTrip() throws Exception {
        assertCSRoundTrip(new SCFL_SetCodedFontLocal(), new byte[]{0x01}, false);
    }

    @Test
    public void testSEC_SetExtendedTextColorRoundTrip() throws Exception {
        byte[] payload = new byte[14];
        payload[1] = 0x01; // ColorSpace RGB
        payload[10] = (byte) 0xFF; // R
        assertCSRoundTrip(new SEC_SetExtendedTextColor(), payload, false);
    }

    @Test
    public void testSIA_SetIntercharacterAdjustmentRoundTrip() throws Exception {
        assertCSRoundTrip(new SIA_SetIntercharacterAdjustment(), new byte[]{0x00, 0x05}, false);
        assertCSRoundTrip(new SIA_SetIntercharacterAdjustment(), new byte[]{0x00, 0x05, 0x01}, false);
    }

    @Test
    public void testSIM_SetInlineMarginRoundTrip() throws Exception {
        assertCSRoundTrip(new SIM_SetInlineMargin(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testSTC_SetTextColorRoundTrip() throws Exception {
        assertCSRoundTrip(new STC_SetTextColor(), new byte[]{0x00, 0x01}, false);
        assertCSRoundTrip(new STC_SetTextColor(), new byte[]{0x00, 0x01, 0x00}, false);
    }

    @Test
    public void testSTO_SetTextOrientationRoundTrip() throws Exception {
        assertCSRoundTrip(new STO_SetTextOrientation(), new byte[]{0x00, 0x00, 0x2D, 0x00}, false);
    }

    @Test
    public void testSVI_SetVariableSpaceCharacterIncrementRoundTrip() throws Exception {
        assertCSRoundTrip(new SVI_SetVariableSpaceCharacterIncrement(), new byte[]{0x00, 0x64}, false);
    }

    @Test
    public void testTBM_TemporaryBaselineMoveRoundTrip() throws Exception {
        assertCSRoundTrip(new TBM_TemporaryBaselineMove(), new byte[]{0x01}, false);
        assertCSRoundTrip(new TBM_TemporaryBaselineMove(), new byte[]{0x02, 0x00}, false);
        assertCSRoundTrip(new TBM_TemporaryBaselineMove(), new byte[]{0x03, 0x01, 0x00, 0x64}, false);
    }

    @Test
    public void testTRN_TransparentDataRoundTrip() throws Exception {
        assertCSRoundTrip(new TRN_TransparentData(), new byte[]{0x01, 0x02, 0x03}, false);
    }

    @Test
    public void testUSC_UnderscoreRoundTrip() throws Exception {
        assertCSRoundTrip(new USC_Underscore(), new byte[]{0x01}, false);
    }

    @Test
    public void testUCT_UnicodeComplexTextRoundTrip() throws Exception {
        UCT_UnicodeComplexText uct = new UCT_UnicodeComplexText();
        byte[] params = new byte[14];
        params[0] = 0x01; // UCTVERS
        params[2] = 0x00;
        params[3] = 0x04; // CTLNGTH = 4
        params[4] = 0x00; // CTFLGS
        params[6] = 0x02; // BIDICT
        params[7] = 0x01; // GLYPHCT
        params[12] = 0x05;
        params[13] = (byte) 0xA0; // ALTIPOS = 1440

        byte[] text = new byte[] {0x00, 0x41, 0x00, 0x42}; // "AB" in UTF-16BE

        ControlSequenceIntroducer csi = new ControlSequenceIntroducer();
        csi.setControlSequenceFunctionType(ControlSequenceFunctionType.UCT_UnicodeComplexText);
        csi.setLength((short) 16);
        csi.setCsPrefix((short) 0x2B);
        csi.setCsClass((short) 0xD3);
        uct.setCsi(csi);

        uct.decodeAFP(params, 0, params.length, new AFPParserConfiguration());
        uct.setComplexText(text);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        uct.writeAFP(baos, new AFPParserConfiguration());

        byte[] expected = new byte[14 + 4];
        System.arraycopy(params, 0, expected, 0, 14);
        System.arraycopy(text, 0, expected, 14, 4);

        assertArrayEquals(expected, baos.toByteArray());
        assertEquals("AB", uct.getText());
    }

    @Test
    public void testUCTThroughParser() throws Exception {
        byte[] data = new byte[] {
            0x2B, (byte)0xD3, 0x10, 0x6A, // CSI: length 16, type UCT
            0x01, 0x00, 0x00, 0x04, // UCTVERS, Reserved, CTLNGTH=4
            0x00, 0x00, 0x02, 0x01, // CTFLGS, Reserved, BIDICT, GLYPHCT
            0x00, 0x00, 0x00, 0x00, // Reserved
            0x05, (byte)0xA0,        // ALTIPOS
            0x00, 0x41, 0x00, 0x42  // "AB"
        };

        List<PTOCAControlSequence> list = PTOCAControlSequenceParser.parseControlSequences(data, 0, data.length, new AFPParserConfiguration());
        assertEquals(1, list.size());
        assertTrue(list.get(0) instanceof UCT_UnicodeComplexText);
        UCT_UnicodeComplexText uct = (UCT_UnicodeComplexText) list.get(0);
        assertEquals(4, uct.getCtLength());
        assertArrayEquals(new byte[]{0x00, 0x41, 0x00, 0x42}, uct.getComplexText());
        assertEquals("AB", uct.getText());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(uct.getCsi().toBytes());
        uct.writeAFP(baos, new AFPParserConfiguration());
        assertArrayEquals(data, baos.toByteArray());
    }

    @Test
    public void testChainedControlSequence() throws Exception {
        assertCSRoundTrip(new AMI_AbsoluteMoveInline(), new byte[]{0x00, 0x64}, true);
    }

    @Test
    public void testENC_EncryptedDataRoundTrip() throws Exception {
        assertCSRoundTrip(new ENC_EncryptedData(), new byte[]{0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03}, false);
    }

    @Test
    public void testSKI_SetKeyInformationRoundTrip() throws Exception {
        assertCSRoundTrip(new SKI_SetKeyInformation(), new byte[]{0x00, 0x00, 0x00, 0x00, (byte) 0xAA, (byte) 0xBB, (byte) 0xCC}, false);
    }

    @Test
    public void testSEA_SetEncryptedAlternateRoundTrip() throws Exception {
        assertCSRoundTrip(new SEA_SetEncryptedAlternate(), new byte[]{0x00, 0x00, 0x00, 0x00, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3}, false);
    }

    @Test
    public void testGraphicCharactersRoundTrip() throws Exception {
        GraphicCharacters gc = new GraphicCharacters();
        byte[] payload = new byte[]{(byte) 0xC1, (byte) 0xC2, (byte) 0xC3}; // "ABC" in CP500
        AFPParserConfiguration config = new AFPParserConfiguration();
        gc.decodeAFP(payload, 0, payload.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gc.writeAFP(baos, config);
        assertArrayEquals(payload, baos.toByteArray());
        assertEquals("ABC", gc.getText());
    }

    @Test
    public void testGLC_GlyphLayoutControlRoundTrip() throws Exception {
        GLC_GlyphLayoutControl glc = new GLC_GlyphLayoutControl();
        byte[] payload = new byte[8 + 13 + 4]; // 8 reserved/metadata + 13 OID + 4 name
        payload[0] = 0x05; payload[1] = (byte) 0xA0; // iAdvance = 1440
        payload[2] = 13; // oidLgth
        payload[3] = 4; // ffnLgth
        byte[] oid = new byte[]{0x06, 0x0B, 0x2B, 0x06, 0x01, 0x04, 0x01, (byte)0x82, 0x37, 0x11, 0x02, 0x03, 0x02};
        System.arraycopy(oid, 0, payload, 8, 13);
        byte[] name = "AB".getBytes(java.nio.charset.StandardCharsets.UTF_16BE);
        System.arraycopy(name, 0, payload, 8 + 13, 4);

        assertCSRoundTrip(glc, payload, false);
        assertEquals("AB", glc.getText());
    }

    @Test
    public void testGIR_GlyphIdRunRoundTrip() throws Exception {
        assertCSRoundTrip(new GIR_GlyphIdRun(), new byte[]{0x00, 0x00, 0x00, 0x01, 0x00, 0x02}, false);
    }

    @Test
    public void testGAR_GlyphAdvanceRunRoundTrip() throws Exception {
        assertCSRoundTrip(new GAR_GlyphAdvanceRun(), new byte[]{0x00, 0x00, 0x00, 0x64, 0x00, (byte) 0xC8}, false);
    }

    @Test
    public void testGOR_GlyphOffsetRunRoundTrip() throws Exception {
        assertCSRoundTrip(new GOR_GlyphOffsetRun(), new byte[]{0x00, 0x00, 0x00, 0x0A, (byte) 0xFF, (byte) 0xF6}, false);
    }

    @Test
    public void testInterleavedGraphicCharactersThroughParser() throws Exception {
        // "ABC" followed by AMI(100)
        // "ABC" = C1 C2 C3
        // AMI = 2B D3 04 C6 00 64
        byte[] data = new byte[]{
            (byte) 0xC1, (byte) 0xC2, (byte) 0xC3,
            0x2B, (byte) 0xD3, 0x04, (byte) 0xC6, 0x00, 0x64
        };

        List<PTOCAControlSequence> list = PTOCAControlSequenceParser.parseControlSequences(data, 0, data.length, new AFPParserConfiguration());
        assertEquals(2, list.size());
        assertTrue(list.get(0) instanceof GraphicCharacters);
        assertEquals("ABC", ((GraphicCharacters) list.get(0)).getText());
        assertTrue(list.get(1) instanceof AMI_AbsoluteMoveInline);
        assertEquals(100, ((AMI_AbsoluteMoveInline) list.get(1)).getDisplacement());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (PTOCAControlSequence cs : list) {
            if (cs.getCsi() != null) {
                baos.write(cs.getCsi().toBytes());
            }
            cs.writeAFP(baos, new AFPParserConfiguration());
        }
        assertArrayEquals(data, baos.toByteArray());
    }
}
