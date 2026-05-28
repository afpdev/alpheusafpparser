package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AFPParserBufferTest {

    @Test
    public void testBufferVsStreamParsing() throws IOException, AFPParserException {
        File afpFile = new File("src/test/resources/afp/afp-goca-reference-03/Chapter_5.afp");
        if (!afpFile.exists()) {
            return; // Skip if test file not found in this environment
        }

        // 1. Parse via Stream
        AFPParserConfiguration streamConf = new AFPParserConfiguration();
        streamConf.setAFPFile(afpFile);
        AFPParser streamParser = new AFPParser(streamConf);
        List<String> streamSfs = new ArrayList<>();
        StructuredField sf;
        while ((sf = streamParser.parseNextSF()) != null) {
            streamSfs.add(sf.toString());
        }
        streamParser.quitParsing();

        // 2. Parse via Buffer
        AFPParserConfiguration bufferConf = new AFPParserConfiguration();
        bufferConf.setAFPFile(afpFile);
        // Force buffer loading by calling getByteBuffer() if needed,
        // though AFPParser should do it if afpFile is set.
        assertNotNull(bufferConf.getByteBuffer(), "ByteBuffer should be available");

        AFPParser bufferParser = new AFPParser(bufferConf);
        List<String> bufferSfs = new ArrayList<>();
        while ((sf = bufferParser.parseNextSF()) != null) {
            bufferSfs.add(sf.toString());
        }
        bufferParser.quitParsing();

        // 3. Compare
        assertEquals(streamSfs.size(), bufferSfs.size(), "Should have same number of SFs");
        for (int i = 0; i < streamSfs.size(); i++) {
            assertEquals(streamSfs.get(i), bufferSfs.get(i), "SF at index " + i + " should match");
        }
    }
}
