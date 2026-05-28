package com.mgz.acceptance;

import com.mgz.cli.Afp2Xml;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Afp2TextTest {

    private Path tempDir;

    @BeforeEach
    public void setup() throws IOException {
        tempDir = Files.createTempDirectory("afp2text-test");
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (Files.exists(tempDir)) {
            Files.walk(tempDir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    public void testDirectoryModeWithTextXpath() throws Exception {
        File afpFile = new File(tempDir.toFile(), "test.afp");
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), afpFile.toPath());

        // Run with -x "/text()"
        Afp2Xml.execute(new String[]{"-d", tempDir.toAbsolutePath().toString(), "-x", "/text()"});

        File txtFile = new File(tempDir.toFile(), "test.afp.txt");
        File xmlFile = new File(tempDir.toFile(), "test.afp.xml");

        assertTrue(txtFile.exists(), "Text file should be created when xpath is /text()");
        assertFalse(xmlFile.exists(), "XML file should NOT be created when xpath is /text()");
    }

    @Test
    public void testSingleFileModeWithTextXpathNoOutput() throws Exception {
        Path inputFilePath = tempDir.resolve("single.afp");
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), inputFilePath);

        // Run with -x "/text()" but no output file
        Afp2Xml.execute(new String[]{"-x", "/text()", inputFilePath.toAbsolutePath().toString()});

        File txtFile = new File(inputFilePath.toAbsolutePath().toString() + ".txt");
        try {
            assertTrue(txtFile.exists(), "Text file should be created automatically when xpath is /text() and no output is specified");
        } finally {
            if (txtFile.exists()) {
                txtFile.delete();
            }
        }
    }
}
