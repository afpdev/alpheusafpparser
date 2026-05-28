package com.mgz.util;

import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharsetNameTest {

    @Test
    public void testGetCharsetFromCodePageName() {
        // Test standard 8-byte EBCDIC names with trailing spaces
        Charset cs = UtilCharacterEncoding.getCharsetFromCodePageName("T10273  ");
        assertNotNull(cs, "Should resolve T10273 with spaces");
        assertEquals("IBM273", cs.name());

        cs = UtilCharacterEncoding.getCharsetFromCodePageName("T10500  ");
        assertNotNull(cs, "Should resolve T10500 with spaces");
        assertEquals("IBM500", cs.name());

        // Test names starting with T1V10
        cs = UtilCharacterEncoding.getCharsetFromCodePageName("T1V10273");
        assertNotNull(cs, "Should resolve T1V10273");
        assertEquals("IBM273", cs.name());

        // Test names with fewer than 8 characters (if supported)
        cs = UtilCharacterEncoding.getCharsetFromCodePageName("T10273");
        assertNotNull(cs, "Should resolve T10273 (short)");
        assertEquals("IBM273", cs.name());
    }
}
