package com.mgz.xml;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatefulEncodingTest {

    @Test
    public void testSCFLSwitchesCharsetInPTX() throws Exception {
        // MCF2 mapping LID 1 to CP273 and LID 2 to CP500
        // Triplet 0x24: len=4, type=0x05 (CodedFont), LID=1 -> byte[] {0x04, 0x24, 0x05, 0x01}
        // Triplet 0x02: len=12, type=0x85, format=0, name="T1V10273" -> byte[] {0x0C, 0x02, 0x85, 0x00, ...}
        // RG Len: 2 (for len) + 4 (T0x24) + 12 (T0x02) = 18 (0x0012)
        // MCF2 Total Payload: 2 * 18 = 36 (0x0024)
        // Total SF Length: 36 + 8 (introducer without 5A) = 44 (0x002C)
        byte[] mcf2Bytes = new byte[] {
            0x5A, 0x00, 0x2C, (byte) 0xD3, (byte) 0xAB, (byte) 0x8A, 0x00, 0x00, 0x00,
            // RG 1: LID 1 -> CP273
            0x00, 0x12, 0x04, 0x24, 0x05, 0x01,
            0x0C, 0x02, (byte) 0x85, 0x00, (byte) 0xE3, (byte) 0xF1, (byte) 0xE5, (byte) 0xF1, (byte) 0xF0, (byte) 0xF2, (byte) 0xF7, (byte) 0xF3,
            // RG 2: LID 2 -> CP500
            0x00, 0x12, 0x04, 0x24, 0x05, 0x02,
            0x0C, 0x02, (byte) 0x85, 0x00, (byte) 0xE3, (byte) 0xF1, (byte) 0xE5, (byte) 0xF1, (byte) 0xF0, (byte) 0xF5, (byte) 0xF0, (byte) 0xF0
        };

        // PTX with SCFL switches
        // Total SF Length: 4 * 5 = 20 (0x0014) + 8 (introducer without 5A) = 28 (0x001C)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x1C, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            // SCFL LID 1
            0x2B, (byte) 0xD3, 0x03, (byte) 0xF0, 0x01,
            // TRN 0xC0 -> 'ä' in CP273
            0x2B, (byte) 0xD3, 0x03, (byte) 0xDA, (byte) 0xC0,
            // SCFL LID 2
            0x2B, (byte) 0xD3, 0x03, (byte) 0xF0, 0x02,
            // TRN 0xC0 -> '{' in CP500
            0x2B, (byte) 0xD3, 0x03, (byte) 0xDA, (byte) 0xC0
        };

        byte[] afpData = new byte[mcf2Bytes.length + ptxBytes.length];
        System.arraycopy(mcf2Bytes, 0, afpData, 0, mcf2Bytes.length);
        System.arraycopy(ptxBytes, 0, afpData, mcf2Bytes.length, ptxBytes.length);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(afpData));
        AFPParser parser = new AFPParser(config);

        StructuredField mcf = parser.parseNextSF();
        assertEquals("MCF_MapCodedFont_Format2", mcf.getClass().getSimpleName());

        StructuredField ptx = parser.parseNextSF();
        assertEquals("PTX_PresentationTextData", ptx.getClass().getSimpleName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Afp2XmlWriter.writeXML(baos, ptx, config);
        String xml = baos.toString();

        System.out.println("XML output:\n" + xml);

        // Check that both 'ä' and '{' are present in the output
        assertTrue(xml.contains("ä"), "XML should contain ä (from CP273 switch)");
        assertTrue(xml.contains("{"), "XML should contain { (from CP500 switch)");
    }
}
