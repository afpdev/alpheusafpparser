/*
Copyright 2015 Rudolf Fiala

This file is part of Alpheus AFP Parser.

Alpheus AFP Parser is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Alpheus AFP Parser is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Alpheus AFP Parser.  If not, see <http://www.gnu.org/licenses/>
*/
package com.mgz.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UtilCharacterEncodingTest {

  @Test
  public void testGetCharsetFromCodePageName() {
    assertNotNull(UtilCharacterEncoding.getCharsetFromCodePageName("T1001141"), "T1001141 should return a charset");
    assertEquals(Charset.forName("IBM01141"), UtilCharacterEncoding.getCharsetFromCodePageName("T1001141"));
    assertEquals(Charset.forName("IBM500"), UtilCharacterEncoding.getCharsetFromCodePageName("T1V10500"));
    assertEquals(Charset.forName("IBM037"), UtilCharacterEncoding.getCharsetFromCodePageName("T1000037"));
  }

  @Test
  public void testStringToByteArray() {
    SortedMap<String, Charset> charsets = Charset.availableCharsets();

    String testStrings[] = new String[] {"hallo welt", "ÄÖßabc€!$!§Üäö \t\n\r", "", null};
    int[] lens = new int[] {0, 1, 3, 8, 100, 256, 1024};
    byte[] fillers = new byte[] {0x00, (byte) 0xff, 0x12, Constants.EBCDIC_ID_FILLER};

    for (Charset chs : charsets.values()) {
      if (!chs.canEncode()) {
        continue;
      }

      for (String str : testStrings) {
        for (int len : lens) {
          for (byte filler : fillers) {

            byte[] expected = str != null && str.length() > 0 ? str.getBytes(chs) : new byte[] {};

            byte[] result = UtilCharacterEncoding.stringToByteArray(str, chs, len, filler);

            for (int i = 0; i < expected.length && i < result.length; i++) {
              assertEquals(expected[i], result[i], "Resulting bytes are not as expected.");
            }

            assertEquals(len, result.length, "Length of resulting byte[] is not as expected.");
            if (len > expected.length) {
              for (int i = expected.length; i < result.length; i++) {
                assertEquals(filler, result[i], "Filler byte is not as expected.");
              }
            }
          }
        }
      }
    }
  }

  @Test
  public void testSanitizeForXml() {
    assertEquals("Hello World", UtilCharacterEncoding.sanitizeForXml("Hello World"));
    assertEquals("Hello World", UtilCharacterEncoding.sanitizeForXml("Hello\u001aWorld"));
    assertEquals("   ", UtilCharacterEncoding.sanitizeForXml("\u0000\u0001\u0002"));
    assertEquals("\t\n\r", UtilCharacterEncoding.sanitizeForXml("\t\n\r"));
    assertEquals("A B", UtilCharacterEncoding.sanitizeForXml("A\u0008B"));

    // Supplementary characters (Emoji) - should be valid
    String emoji = "\uD83D\uDE00"; // 😀
    assertEquals(emoji, UtilCharacterEncoding.sanitizeForXml(emoji));

    // Some invalid values in ranges
    assertEquals(" ", UtilCharacterEncoding.sanitizeForXml("\uFFFE"));
    assertEquals(" ", UtilCharacterEncoding.sanitizeForXml("\uFFFF"));
  }
}
