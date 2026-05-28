package com.mgz.afp.ptoca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TRNEncodingTest {

    @Test
    public void testMCF1SetsCharsetForPTX_273_WithSpaces() throws Exception {
        // MCF1 mapping LID 1 to T10273 (Code Page 273)

        ByteArrayOutputStream mcfBaos = new ByteArrayOutputStream();
        mcfBaos.write(0x5A);
        // length: 8 (introducer) + 4 (header) + 30 (RG) = 42 (0x002A)
        mcfBaos.write(0x00);
        mcfBaos.write(0x2A);
        mcfBaos.write(0xD3);
        mcfBaos.write(0xB1);
        mcfBaos.write(0x8A);
        mcfBaos.write(0x00); // Flags
        mcfBaos.write(0x00); // Reserved
        mcfBaos.write(0x00); // Reserved

        mcfBaos.write(30); // RG length
        mcfBaos.write(new byte[]{0, 0, 0}); // Reserved

        // RG 1
        mcfBaos.write(0x01); // LID 1
        mcfBaos.write(0x00); // Reserved
        mcfBaos.write(0x01); // Section ID
        mcfBaos.write(0x00); // Reserved
        mcfBaos.write("FONT0001".getBytes(Constants.cpIBM500)); // Font Name
        mcfBaos.write("T10273  ".getBytes(Constants.cpIBM500)); // CP Name: T10273 with spaces
        mcfBaos.write("C10273  ".getBytes(Constants.cpIBM500)); // FCS Name
        mcfBaos.write(new byte[]{0, 0}); // Orientation

        byte[] mcf1Bytes = mcfBaos.toByteArray();

        // PTX with SCFL and TRN
        // "Abzugsberechtigte Gebühren:" in CP273:
        byte[] textBytes = new byte[] {
            (byte)0xc1, (byte)0x82, (byte)0xa9, (byte)0xa4, (byte)0x87, (byte)0xa2, (byte)0x82, (byte)0x85, (byte)0x99, (byte)0x85, (byte)0x83, (byte)0x88, (byte)0xa3, (byte)0x89, (byte)0x87, (byte)0xa3, (byte)0x85, (byte)0x40, (byte)0xc7, (byte)0x85, (byte)0x82, (byte)0xd0, (byte)0x88, (byte)0x99, (byte)0x85, (byte)0x95, (byte)0x7a
        };

        ByteArrayOutputStream ptxBaos = new ByteArrayOutputStream();
        // SFI for PTX
        ptxBaos.write(0x5A);
        // length: 8 (introducer) + 5 (SCFL) + (textBytes.length + 4) (TRN)
        // 8 + 5 + 31 = 44 (0x002C)
        ptxBaos.write(0x00);
        ptxBaos.write(0x2C);
        ptxBaos.write(0xD3);
        ptxBaos.write(0xEE);
        ptxBaos.write(0x9B);
        ptxBaos.write(0x00); // Flags
        ptxBaos.write(0x00); // Reserved
        ptxBaos.write(0x00); // Reserved

        // SCFL LID 1: 2B D3 03 F0 01
        ptxBaos.write(new byte[]{0x2B, (byte)0xD3, 0x03, (byte)0xF0, 0x01});
        // TRN: 2B D3 length DA data
        ptxBaos.write(0x2B);
        ptxBaos.write(0xD3);
        ptxBaos.write(textBytes.length + 2);
        ptxBaos.write(0xDA);
        ptxBaos.write(textBytes);

        byte[] ptxBytes = ptxBaos.toByteArray();

        byte[] afpData = new byte[mcf1Bytes.length + ptxBytes.length];
        System.arraycopy(mcf1Bytes, 0, afpData, 0, mcf1Bytes.length);
        System.arraycopy(ptxBytes, 0, afpData, mcf1Bytes.length, ptxBytes.length);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(afpData));
        AFPParser parser = new AFPParser(config);

        StructuredField mcf = parser.parseNextSF();
        assertEquals("MCF_MapCodedFont_Format1", mcf.getClass().getSimpleName());

        StructuredField ptx = parser.parseNextSF();
        assertEquals("PTX_PresentationTextData", ptx.getClass().getSimpleName());

        PTX_PresentationTextData ptxSF = (PTX_PresentationTextData) ptx;
        String extractedText = null;
        for (com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence cs : ptxSF.getControlSequences()) {
            if (cs instanceof com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.TRN_TransparentData) {
                extractedText = ((com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.TRN_TransparentData) cs).getText();
            }
        }

        System.out.println("Extracted text: " + extractedText);
        assertNotNull(extractedText, "Extracted text should not be null");
        assertTrue(extractedText.contains("Gebühren"), "Extracted text should contain Gebühren, got: " + extractedText);
    }
}
