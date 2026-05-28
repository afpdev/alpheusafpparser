package com.mgz.afp.ptoca;

import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.SEA_SetEncryptedAlternate;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.SKI_SetKeyInformation;
import com.mgz.util.Constants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PTOCAConcatenationTest {

    @Test
    public void testSEAConcatenation() throws Exception {
        // SEA 1: Unchained, Chained flag (Type 9D), Len 7 (Payload: 4 reserved + 1 text)
        // 2B D3 07 9D 00 00 00 00 C1 ("A") -> 9 bytes
        // SEA 2: Chained, Unchained flag (Type 9C), Len 7
        // 07 9C 00 00 00 00 C2 ("B") -> 7 bytes
        // Total SF Length: 9 + 9 + 7 = 25 (0x0019)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x19, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x2B, (byte) 0xD3, 0x07, (byte) 0x9D, 0x00, 0x00, 0x00, 0x00, (byte) 0xC1,
            0x07, (byte) 0x9C, 0x00, 0x00, 0x00, 0x00, (byte) 0xC2
        };

        PTX_PresentationTextData ptx = new PTX_PresentationTextData();
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(Constants.cpIBM500);
        ptx.decodeAFP(ptxBytes, 9, ptxBytes.length - 9, config);

        List<PTOCAControlSequence> sequences = ptx.getControlSequences();

        // Should return 1 merged sequence
        assertEquals(1, sequences.size(), "SEA controls should be merged");
        assertTrue(sequences.get(0) instanceof SEA_SetEncryptedAlternate);

        assertEquals("AB", new String(((SEA_SetEncryptedAlternate)sequences.get(0)).getAlternateText(), Constants.cpIBM500));
    }

    @Test
    public void testSKIConcatenation() throws Exception {
        // SKI 1: Unchained, Chained flag (Type 9B), Len 7 (4 res + 1 key)
        // 2B D3 07 9B 00 00 00 00 01 -> 9 bytes
        // SKI 2: Chained, Unchained flag (Type 9A), Len 7
        // 07 9A 00 00 00 00 04 -> 7 bytes
        // Total: 9 + 9 + 7 = 25 (0x0019)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x19, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x2B, (byte) 0xD3, 0x07, (byte) 0x9B, 0x00, 0x00, 0x00, 0x00, 0x01,
            0x07, (byte) 0x9A, 0x00, 0x00, 0x00, 0x00, 0x04
        };

        PTX_PresentationTextData ptx = new PTX_PresentationTextData();
        AFPParserConfiguration config = new AFPParserConfiguration();
        ptx.decodeAFP(ptxBytes, 9, ptxBytes.length - 9, config);

        List<PTOCAControlSequence> sequences = ptx.getControlSequences();

        assertEquals(1, sequences.size(), "SKI controls should be merged");
        assertTrue(sequences.get(0) instanceof SKI_SetKeyInformation);

        byte[] mergedKeyInfo = ((SKI_SetKeyInformation)sequences.get(0)).getKeyInfo();
        assertEquals(2, mergedKeyInfo.length);
        assertEquals(0x01, mergedKeyInfo[0]);
        assertEquals(0x04, mergedKeyInfo[1]);
    }

    @Test
    public void testSEAReset() throws Exception {
        // SEA 1: Unchained, Chained (Type 9D), Len 7: "A"
        // SEA 2: Chained, Unchained (Type 9C), Len 6: RESET
        // Total: 9 + 9 + 6 = 24 (0x0018)
        byte[] ptxBytes = new byte[] {
            0x5A, 0x00, 0x18, (byte) 0xD3, (byte) 0xEE, (byte) 0x9B, 0x00, 0x00, 0x00,
            0x2B, (byte) 0xD3, 0x07, (byte) 0x9D, 0x00, 0x00, 0x00, 0x00, (byte) 0xC1,
            0x06, (byte) 0x9C, 0x00, 0x00, 0x00, 0x00
        };

        PTX_PresentationTextData ptx = new PTX_PresentationTextData();
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(Constants.cpIBM500);
        ptx.decodeAFP(ptxBytes, 9, ptxBytes.length - 9, config);

        List<PTOCAControlSequence> sequences = ptx.getControlSequences();

        // After SEA 1 ("A"), a Reset SEA (Len 6) should result in 1 SEA with NO text (null or empty)
        assertEquals(1, sequences.size());
        assertTrue(sequences.get(0) instanceof SEA_SetEncryptedAlternate);
        assertNull(((SEA_SetEncryptedAlternate)sequences.get(0)).getAlternateText());
    }

}
