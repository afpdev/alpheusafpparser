package com.mgz.afp.base;

import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.modca.NOP_NoOperation;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.enums.SFFlag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SFIExtensionsAndPaddingTest {

    @Test
    public void testSFIExtensionRoundTrip() throws IOException, AFPParserException {
        // [MODCA-3-021] [MODCA-3-024] [MODCA-3-028]
        StructuredFieldIntroducer sfi = new StructuredFieldIntroducer();
        sfi.setSFLength(10);
        sfi.setSFTypeID(com.mgz.afp.enums.SFTypeID.NOP_NoOperation);
        byte[] extData = new byte[]{0x01, 0x02};
        sfi.setExtensionData(extData);

        byte[] serialized = sfi.toBytes();
        // 2 bytes length + 3 bytes type + 1 byte flag + 2 bytes reserved + 1 byte extLen + 2 bytes extData = 11 bytes
        assertEquals(11, serialized.length);
        assertEquals(0x80, serialized[5] & 0x80); // hasExtension flag
        assertEquals(0x03, serialized[8]); // extLen = data len (2) + 1 = 3

        StructuredFieldIntroducer parsed = StructuredFieldIntroducer.parse(new ByteArrayInputStream(serialized));
        assertEquals(10, parsed.getSFLength());
        assertTrue(parsed.isFlagSet(SFFlag.hasExtension));
        assertEquals(3, parsed.getExtensionLength());
        assertArrayEquals(extData, parsed.getExtensionData());
    }

    @Test
    public void testSFPaddingSingleByteFormat() throws IOException, AFPParserException {
        // [MODCA-3-026] [MODCA-3-027] [MODCA-3-036] [MODCA-3-040] [MODCA-3-041]
        // NOP SF with 4 bytes of data and 4 bytes of padding.
        // Total SF Length = 8 (introducer) + 4 (data) + 4 (padding) = 16 (0x0010)
        // Flag byte = 0x08 (isPadded)
        byte[] sfBytes = new byte[] {
            0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xEE, (byte)0xEE, 0x08, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04, // Data
            0x00, 0x00, 0x00, 0x04  // Padding: 4 bytes, last byte is length
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(sfBytes));
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertNotNull(sf);
        assertTrue(sf instanceof NOP_NoOperation);
        assertTrue(sf.getStructuredFieldIntroducer().isFlagSet(SFFlag.isPadded));

        // Verify data was correctly decoded (4 bytes)
        // NOP doesn't have much to decode but we can check if it was handled
        assertEquals(16, sf.getStructuredFieldIntroducer().getSFLength());

        // Round trip write
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        sf.writeAFP(baos, config);
        assertArrayEquals(sfBytes, baos.toByteArray());
    }

    @Test
    public void testSFISegmentation() throws IOException, AFPParserException {
        // [MODCA-3-025]
        // NOP SF with isSegmented flag set (0x20)
        byte[] sfBytes = new byte[] {
            0x5A, 0x00, 0x0C, (byte)0xD3, (byte)0xEE, (byte)0xEE, (byte)0x20, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(sfBytes));
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertNotNull(sf);
        assertTrue(sf.getStructuredFieldIntroducer().isFlagSet(SFFlag.isSegmented));

        // Round trip write
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        sf.writeAFP(baos, config);
        assertArrayEquals(sfBytes, baos.toByteArray());
    }

    @Test
    public void testSFPaddingThreeByteFormat() throws IOException, AFPParserException {
        // [MODCA-3-026] [MODCA-3-037] [MODCA-3-038] [MODCA-3-040] [MODCA-3-041]
        // NOP SF with 4 bytes of data and 256 bytes of padding.
        // Total SF Length = 8 (introducer) + 4 (data) + 256 (padding) = 268 (0x010C)
        // Flag byte = 0x08 (isPadded)
        // Padding length 256 (0x0100) -> bytes: 0x01, 0x00, 0x00
        int dataLen = 4;
        int padLen = 256;
        int totalLen = 8 + dataLen + padLen;
        byte[] sfBytes = new byte[1 + totalLen];
        sfBytes[0] = 0x5A;
        sfBytes[1] = (byte)((totalLen >> 8) & 0xFF);
        sfBytes[2] = (byte)(totalLen & 0xFF);
        sfBytes[3] = (byte)0xD3;
        sfBytes[4] = (byte)0xEE;
        sfBytes[5] = (byte)0xEE;
        sfBytes[6] = 0x08; // isPadded

        // Data at offset 9
        sfBytes[9] = 0x01; sfBytes[10] = 0x02; sfBytes[11] = 0x03; sfBytes[12] = 0x04;

        // Padding at offset 9 + 4 = 13
        // Last three bytes are length: 0x01, 0x00, 0x00
        int paddingEnd = 1 + totalLen;
        sfBytes[paddingEnd - 3] = 0x01;
        sfBytes[paddingEnd - 2] = 0x00;
        sfBytes[paddingEnd - 1] = 0x00;

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(sfBytes));
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertNotNull(sf);
        assertTrue(sf.getStructuredFieldIntroducer().isFlagSet(SFFlag.isPadded));
        assertEquals(totalLen, sf.getStructuredFieldIntroducer().getSFLength());

        // Round trip write
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        sf.writeAFP(baos, config);
        assertArrayEquals(sfBytes, baos.toByteArray());
    }
}
