package com.mgz.acceptance;

import com.mgz.cli.Afp2Xml;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class CLIDirectoryErrorTest {

    @Test
    public void testContinueOnExceptionInDirectoryMode() throws Exception {
        File tempDir = new File("build/test-dir-error");
        if (tempDir.exists()) {
            File[] files = tempDir.listFiles();
            if (files != null) {
                for (File f : files) f.delete();
            }
            tempDir.delete();
        }
        tempDir.mkdirs();

        // 1. Create a valid AFP file
        File validAfp = new File(tempDir, "valid.afp");
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), validAfp.toPath());

        // 2. Create a corrupted/invalid AFP file that will cause an exception
        File invalidAfp = new File(tempDir, "invalid.afp");
        try (FileOutputStream fos = new FileOutputStream(invalidAfp)) {
            fos.write(new byte[]{0x5A, 0x00, 0x03, 0x00, 0x00, 0x00}); // Incomplete/Invalid SF
        }

        // 3. Create another valid AFP file to see if it gets processed after the invalid one
        File validAfp2 = new File(tempDir, "valid2.afp");
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), validAfp2.toPath());

        // Call the new execute method which doesn't call System.exit
        int result = Afp2Xml.execute(new String[]{"-d", tempDir.getAbsolutePath()});

        // It should return 1 because at least one file failed
        assertEquals(1, result, "Exit code should be 1 due to processing errors");

        // Verify that valid files were processed successfully
        File validXml = new File(tempDir, "valid.afp.xml");
        File valid2Xml = new File(tempDir, "valid2.afp.xml");

        assertTrue(validXml.exists(), "valid.afp.xml should exist");
        assertTrue(valid2Xml.exists(), "valid2.afp.xml should exist");

        assertTrue(Files.readString(validXml.toPath()).contains("<AFPDocument"), "valid.afp.xml should be valid XML");
        assertTrue(Files.readString(valid2Xml.toPath()).contains("<AFPDocument"), "valid2.afp.xml should be valid XML");
    }
}
