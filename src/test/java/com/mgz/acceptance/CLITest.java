package com.mgz.acceptance;

import com.mgz.cli.Afp2Xml;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class CLITest {

    @Test
    public void testCLIDirectoryMode() throws Exception {
        File tempDir = new File("build/test-dir");
        setupTestDirectory(tempDir);

        Afp2Xml.execute(new String[]{"-d", tempDir.getAbsolutePath()});

        verifyDirectoryOutput(tempDir);
    }

    @Test
    public void testCLIImplicitDirectoryMode() throws Exception {
        File tempDir = new File("build/test-dir-implicit");
        setupTestDirectory(tempDir);

        // Pass directory without -d flag
        Afp2Xml.execute(new String[]{tempDir.getAbsolutePath()});

        verifyDirectoryOutput(tempDir);
    }

    @Test
    public void testCLIDirectoryFlagWithPath() throws Exception {
        File tempDir = new File("build/test-dir-flag-path");
        setupTestDirectory(tempDir);

        // Pass -d followed by the directory path
        Afp2Xml.execute(new String[]{"-d", tempDir.getAbsolutePath()});

        verifyDirectoryOutput(tempDir);
    }

    private void setupTestDirectory(File tempDir) throws Exception {
        if (tempDir.exists()) {
            File[] files = tempDir.listFiles();
            if (files != null) {
                for (File f : files) f.delete();
            }
            tempDir.delete();
        }
        tempDir.mkdirs();

        File afpFile1 = new File(tempDir, "test1.afp");
        File afpFile2 = new File(tempDir, "test2.AFP"); // test case insensitivity
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), afpFile1.toPath());
        Files.copy(new File("src/test/resources/afp/minimal.afp").toPath(), afpFile2.toPath());
    }

    private void verifyDirectoryOutput(File tempDir) throws Exception {
        File xmlFile1 = new File(tempDir, "test1.afp.xml");
        File xmlFile2 = new File(tempDir, "test2.AFP.xml");

        assertTrue(xmlFile1.exists(), "XML file 1 should exist");
        assertTrue(xmlFile2.exists(), "XML file 2 should exist");

        assertTrue(Files.readString(xmlFile1.toPath()).contains("<AFPDocument"), "XML 1 should contain AFPDocument tag");
        assertTrue(Files.readString(xmlFile2.toPath()).contains("<AFPDocument"), "XML 2 should contain AFPDocument tag");
    }

    @Test
    public void testDefaultOutputFileXml() throws Exception {
        File inputFile = new File("src/test/resources/afp/minimal.afp");
        File expectedOutputFile = new File(inputFile.getAbsolutePath() + ".xml");

        if (expectedOutputFile.exists()) {
            expectedOutputFile.delete();
        }

        Afp2Xml.execute(new String[]{inputFile.getAbsolutePath()});

        try {
            assertTrue(expectedOutputFile.exists(), "Default XML output file should be created");
        } finally {
            if (expectedOutputFile.exists()) {
                expectedOutputFile.delete();
            }
        }
    }

    @Test
    public void testExplicitStdoutWithDash() throws Exception {
        File inputFile = new File("src/test/resources/afp/minimal.afp");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            Afp2Xml.execute(new String[]{inputFile.getAbsolutePath(), "-"});

            String output = bos.toString();
            assertTrue(output.contains("<AFPDocument"), "Stdout should contain XML output");

            File unexpectedOutputFile = new File(inputFile.getAbsolutePath() + ".xml");
            assertFalse(unexpectedOutputFile.exists(), "Default output file should NOT be created when '-' is used");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testCLIMain() throws Exception {
        File inputFile = new File("src/test/resources/afp/minimal.afp");
        File outputFile = new File("build/test-output.xml");
        outputFile.getParentFile().mkdirs();

        if (outputFile.exists()) {
            outputFile.delete();
        }

        Afp2Xml.execute(new String[]{inputFile.getAbsolutePath(), outputFile.getAbsolutePath()});

        assertTrue(outputFile.exists(), "Output XML file should exist");

        List<String> lines = Files.readAllLines(outputFile.toPath());
        boolean foundXmlHeader = false;
        boolean foundAfpDocTag = false;

        for (String line : lines) {
            if (line.contains("<?xml version=") && line.contains("1.0")) {
                foundXmlHeader = true;
            }
            if (line.contains("<AFPDocument")) {
                foundAfpDocTag = true;
            }
        }

        assertTrue(foundXmlHeader, "XML header not found in output");
        assertTrue(foundAfpDocTag, "AFPDocument tag not found in output");
    }
}
