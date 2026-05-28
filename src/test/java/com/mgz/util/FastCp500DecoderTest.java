package com.mgz.util;

import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastCp500DecoderTest {

    @Test
    public void testDecodeCp500() {
        String testString = "Hello World! @#$ 123";
        Charset cp500 = Charset.forName("Cp500");
        byte[] ebcdicBytes = testString.getBytes(cp500);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(cp500);

        String decoded = UtilCharacterEncoding.decodeEbcdic(ebcdicBytes, 0, ebcdicBytes.length, config);
        assertEquals(testString, decoded, "Fast decoder output should match standard Charset decoder output");
    }

    @Test
    public void testDecodeCp500WithOffset() {
        String testString = "Prefix Hello World! Suffix";
        Charset cp500 = Charset.forName("Cp500");
        byte[] ebcdicBytes = testString.getBytes(cp500);

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(cp500);

        // Decode "Hello World!"
        String decoded = UtilCharacterEncoding.decodeEbcdic(ebcdicBytes, 7, 12, config);
        assertEquals("Hello World!", decoded);
    }
}
