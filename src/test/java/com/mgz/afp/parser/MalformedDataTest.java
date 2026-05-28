package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.ptoca.PTX_PresentationTextData;
import com.mgz.afp.modca.MCF_MapCodedFont_Format1;
import com.mgz.afp.ioca.IPD_ImagePictureData;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MalformedDataTest {

    @Test
    public void testTruncatedPTOCAControlSequenceIntroducer() throws Exception {
        // PTX field with truncated CSI (only 0x2B, missing 0xD3 and length/type)
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0A, // Length 10
            (byte)0xD3, (byte)0xEE, (byte)0x9B, // PTX
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x2B // Truncated CSI
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException for truncated PTOCA CSI");
    }

    @Test
    public void testTruncatedPTOCAControlSequencePayload() throws Exception {
        // PTX field with CSI specifying length 4 (2 bytes payload), but only 1 byte available
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0C, // Length 12
            (byte)0xD3, (byte)0xEE, (byte)0x9B, // PTX
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x2B, (byte)0xD3, 0x04, (byte)0xDA, // TRN, length 4 (2 bytes payload)
            0x01 // Only 1 byte payload
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException for truncated PTOCA payload");
    }

    @Test
    public void testZeroLengthRepeatingGroupMCF1() throws Exception {
        // MCF1 with RG length 0, and enough total length to try parsing one
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0D, // Length 13
            (byte)0xD3, (byte)0xB1, (byte)0x8A, // MCF1
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x00, // RG Length = 0 (MALFORMED)
            0x00, 0x00, 0x00, // Reserved
            0x01 // 1 byte extra
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException for zero-length RG in MCF1");
    }

    @Test
    public void testTruncatedIPDSegmentIntroducer() throws Exception {
        // IPD with truncated segment introducer (only 1 byte)
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x09, // Length 9
            (byte)0xD3, (byte)0xEE, (byte)0xFB, // IPD
            0x00, // Flags
            0x00, 0x00, // Reserved
            (byte)0x70 // Only segment type, missing length
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException for truncated IPD segment introducer");
    }
}
