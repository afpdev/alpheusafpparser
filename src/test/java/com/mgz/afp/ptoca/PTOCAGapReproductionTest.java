package com.mgz.afp.ptoca;

import com.mgz.afp.RoundTripTestUtils;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.PTOCAControlSequenceParser;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PTOCAGapReproductionTest {

    @Test
    public void testFreeStandingTextParsing() throws Exception {
        // PTX: D3EE9B
        // Payload: "ABC" (EBCDIC CP500: C1 C2 C3) followed by SCFL 2B D3 03 F0 01
        // ABC = C1 C2 C3
        // SCFL = 2B D3 03 F0 01
        // Total Payload: 3 + 5 = 8 bytes. SF length = 9 + 8 = 17.
        byte[] data = new byte[] {
            0x5A, 0x00, 0x11, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            (byte) 0xC1, (byte) 0xC2, (byte) 0xC3, 0x2B, (byte) 0xD3, 0x03, (byte) 0xF0, 0x01
        };

        PTX_PresentationTextData ptx = new PTX_PresentationTextData();
        ptx.decodeAFP(data, 0, 17, new AFPParserConfiguration());
        List<PTOCAControlSequence> sequences = ptx.getControlSequences();

        assertNotNull(sequences);
        assertEquals(2, sequences.size());
        assertTrue(sequences.get(0) instanceof PTOCAControlSequence.GraphicCharacters);
        assertTrue(sequences.get(1) instanceof PTOCAControlSequence.SCFL_SetCodedFontLocal);
    }

    @Test
    public void testGLCParsing() throws Exception {
        // GLC: 2B D3 0A 6C ... (Length 10, Type 6C)
        // Total CS length = 2 (prefix) + 10 (LL) = 12 bytes.
        // GLC.decodeAFP needs at least 8 bytes from offset to get FF Name.
        // Let's provide 12 bytes.
        byte[] data = new byte[] {
            0x2B, (byte) 0xD3, 0x0A, (byte) 0x6C,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };
        PTX_PresentationTextData ptx = new PTX_PresentationTextData();
        ptx.decodeAFP(data, 0, 12, new AFPParserConfiguration());
        List<PTOCAControlSequence> sequences = ptx.getControlSequences();
        assertNotNull(sequences);
        assertEquals(1, sequences.size());
        assertTrue(sequences.get(0) instanceof PTOCAControlSequence.GLC_GlyphLayoutControl);
    }
}
