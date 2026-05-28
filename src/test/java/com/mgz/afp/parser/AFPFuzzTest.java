package com.mgz.afp.parser;

import com.code_intelligence.jazzer.junit.FuzzTest;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;

import java.io.ByteArrayInputStream;

public class AFPFuzzTest {

    @FuzzTest(maxDuration = "1m")
    public void fuzzParser(byte[] data) {
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        // We want to catch unexpected exceptions (crashes),
        // so we can either escalate or just let it run.
        // If escalate is true, AFPParserException will be thrown for malformed data.
        // Jazzer will ignore expected exceptions if we configure it,
        // but by default it catches all unhandled exceptions.
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        try {
            while (true) {
                StructuredField sf = parser.parseNextSF();
                if (sf == null) {
                    break;
                }
                // Optionally do something with sf to trigger deeper lazy parsing if any
            }
        } catch (AFPParserException e) {
            // Expected exception for malformed AFP data when escalate is true
        } catch (Throwable t) {
            // Unexpected exceptions (e.g. ArrayIndexOutOfBoundsException, NullPointerException)
            // will be caught by Jazzer and reported as a finding.
            throw t;
        }
    }
}
