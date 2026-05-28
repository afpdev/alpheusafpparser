package com.mgz.xml;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.ptoca.PTX_PresentationTextData;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MDRPTXXMLTest {

    @Test
    public void testMDRSetsCharsetForPTX_273() throws Exception {
        // MDR with T1V10273 (Code Page 273)
        // 5A 00 16 D3 AB C3 00 00 00 (SFI)
        // 00 0E (RG Len)
        // 0C 02 85 00 E3 F1 E5 F1 F0 F2 F7 F3 (Triplet 0x02, Type 0x85, Format 0, Name T1V10273)
        byte[] mdrBytes = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xAB, (byte) 0xC3, 0x00, 0x00, 0x00,
            0x00, 0x0E, 0x0C, 0x02, (byte) 0x85, 0x00,
            (byte) 0xE3, (byte) 0xF1, (byte) 0xE5, (byte) 0xF1, (byte) 0xF0, (byte) 0xF2, (byte) 0xF7, (byte) 0xF3
        };

        // PTX with TRN and byte 0xC0
        // 5A 00 0D D3 EE 9B 00 00 00 (SFI)
        // 2B D3 03 DA C0 (TRN CSI + data)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x0D, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x2B, (byte) 0xD3, 0x03, (byte) 0xDA, (byte) 0xC0
        };

        byte[] afpData = new byte[mdrBytes.length + ptxBytes.length];
        System.arraycopy(mdrBytes, 0, afpData, 0, mdrBytes.length);
        System.arraycopy(ptxBytes, 0, afpData, mdrBytes.length, ptxBytes.length);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(afpData));
        AFPParser parser = new AFPParser(config);

        StructuredField mdr = parser.parseNextSF();
        assertEquals("MDR_MapDataResource", mdr.getClass().getSimpleName());

        StructuredField ptx = parser.parseNextSF();
        assertEquals("PTX_PresentationTextData", ptx.getClass().getSimpleName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Afp2XmlWriter.writeXML(baos, ptx, config);
        String xml = baos.toString();

        System.out.println("XML for CP273:\n" + xml);
        // In CP273, 0xC0 should be 'ä'
        assertTrue(xml.contains("ä"), "XML should contain ä for CP273");
    }

    @Test
    public void testMDRSetsCharsetForPTX_500() throws Exception {
        // MDR with T1V10500 (Code Page 500)
        // 5A 00 16 D3 AB C3 00 00 00 (SFI)
        // 00 0E (RG Len)
        // 0C 02 85 00 E3 F1 E5 F1 F0 F5 F0 F0 (Triplet 0x02, Type 0x85, Format 0, Name T1V10500)
        byte[] mdrBytes = new byte[] {
            0x5A, 0x00, 0x16, (byte) 0xD3, (byte) 0xAB, (byte) 0xC3, 0x00, 0x00, 0x00,
            0x00, 0x0E, 0x0C, 0x02, (byte) 0x85, 0x00,
            (byte) 0xE3, (byte) 0xF1, (byte) 0xE5, (byte) 0xF1, (byte) 0xF0, (byte) 0xF5, (byte) 0xF0, (byte) 0xF0
        };

        // PTX with TRN and byte 0xC0
        // 5A 00 0D D3 EE 9B 00 00 00 (SFI)
        // 2B D3 03 DA C0 (TRN CSI + data)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x0D, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x2B, (byte) 0xD3, 0x03, (byte) 0xDA, (byte) 0xC0
        };

        byte[] afpData = new byte[mdrBytes.length + ptxBytes.length];
        System.arraycopy(mdrBytes, 0, afpData, 0, mdrBytes.length);
        System.arraycopy(ptxBytes, 0, afpData, mdrBytes.length, ptxBytes.length);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(afpData));
        AFPParser parser = new AFPParser(config);

        StructuredField mdr = parser.parseNextSF();
        assertEquals("MDR_MapDataResource", mdr.getClass().getSimpleName());

        StructuredField ptx = parser.parseNextSF();
        assertEquals("PTX_PresentationTextData", ptx.getClass().getSimpleName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Afp2XmlWriter.writeXML(baos, ptx, config);
        String xml = baos.toString();

        System.out.println("XML for CP500:\n" + xml);
        // In CP500, 0xC0 should be '{'
        assertTrue(xml.contains("{"), "XML should contain { for CP500");
    }
}
