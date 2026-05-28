package com.mgz.util;

import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.*;

public class HeuristicValidationTest {

    @Test
    public void testIsHumanReadable_StandardEBCDIC() {
        String text = "Hello World";
        byte[] data = text.getBytes(Constants.cpIBM500);
        assertTrue(UtilCharacterEncoding.isHumanReadable(data, Constants.cpIBM500), "Standard EBCDIC text should be human-readable");
    }

    @Test
    public void testIsHumanReadable_WithNEL() {
        // EBCDIC Next Line (NEL) is U+0085
        // In some EBCDIC environments, NEL is at 0x15, but it seems IBM500 here maps 0x15 to \n (U+000A)
        // Let's use \u0085 directly to verify the heuristic handles it.
        String text = "Line 1" + "\u0085" + "Line 2";
        byte[] data = text.getBytes(Constants.cpIBM500);

        assertTrue(UtilCharacterEncoding.isHumanReadable(data, Constants.cpIBM500), "Text with NEL should be human-readable");
    }

    @Test
    public void testIsHumanReadable_Mixed() {
        String text = "This is a longer string that should remain human readable even with a few controls\n\r\t";
        byte[] data = text.getBytes(Constants.cpIBM500);
        assertTrue(UtilCharacterEncoding.isHumanReadable(data, Constants.cpIBM500), "Mixed text with mostly printable should be human-readable");
    }

    @Test
    public void testIsHumanReadable_BinaryData() {
        byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i; // Includes many ISO controls
        }
        assertFalse(UtilCharacterEncoding.isHumanReadable(data, Constants.cpIBM500), "Random binary data should NOT be human-readable");
    }

    @Test
    public void testIsHumanReadable_EdgeCases() {
        assertFalse(UtilCharacterEncoding.isHumanReadable(null, Constants.cpIBM500), "Null data should not be human-readable");
        assertFalse(UtilCharacterEncoding.isHumanReadable(new byte[0], Constants.cpIBM500), "Empty data should not be human-readable");
    }

    @Test
    public void testIsHumanReadable_DefaultCharset() {
        String text = "Hello World";
        byte[] data = text.getBytes(Constants.cpIBM500);
        assertTrue(UtilCharacterEncoding.isHumanReadable(data, null), "Should default to IBM500 if charset is null");
    }
}
