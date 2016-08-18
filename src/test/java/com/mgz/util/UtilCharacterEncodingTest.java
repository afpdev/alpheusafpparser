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

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;


public class UtilCharacterEncodingTest {

  @Test
  public void testStringToByteArray() {
    SortedMap<String, Charset> charsets = Charset.availableCharsets();

    String testStrings[] = new String[]{"hallo welt", "ÄÖßabc€!$!§Üäö \t\n\r", "", null};
    int[] lens = new int[]{0, 1, 3, 8, 100, 256, 1024};
    byte[] fillers = new byte[]{0x00, (byte) 0xff, 0x12, Constants.EBCDIC_ID_FILLER};

    for (Charset chs : charsets.values()) {
      if (!chs.canEncode()) continue;

      for (String str : testStrings) {
        for (int len : lens) {
          for (byte filler : fillers) {

            byte[] expected = str != null && str.length() > 0 ? str.getBytes(chs) : new byte[]{};

            byte[] result = UtilCharacterEncoding.stringToByteArray(str, chs, len, filler);

            for (int i = 0; i < expected.length && i < result.length; i++) {
              assertEquals("Resulting bytes are not as expected.", expected[i], result[i]);
            }

            assertEquals("Length of resulting byte[] is not as expected.", len, result.length);
            if (len > expected.length) {
              for (int i = expected.length; i < result.length; i++)
                assertEquals("Filler byte is not as expected.", result[i], filler);
            }
          }
        }
      }
    }
  }
}
