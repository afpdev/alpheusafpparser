package com.mgz.acceptance;

import com.mgz.cli.Afp2Xml;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class MemoryLeakTest {

    @Test
    public void testMemoryUsage() throws Exception {
        Path inputPath = Path.of("src/test/resources/afp/minimal.afp");
        File outputFile = File.createTempFile("memory-test", ".xml");
        outputFile.deleteOnExit();

        System.out.println("Starting memory usage test...");
        for (int i = 0; i < 100; i++) {
            Afp2Xml.main(new String[]{"-x", "//AFPDocument", inputPath.toAbsolutePath().toString(), outputFile.getAbsolutePath()});
            if (i % 10 == 0) {
                System.gc();
                long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.printf("Iteration %d: Used Memory = %d KB%n", i, usedMemory / 1024);
            }
        }
        System.out.println("Memory usage test completed.");
    }
}
