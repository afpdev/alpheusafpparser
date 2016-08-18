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
package com.mgz.test;

import com.mgz.util.UtilBinaryDecoding;

import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilBinaryDecodingTest {

  @Test
  public void testBitSet() {
    byte[] empty = new byte[0];

    BitSet bitSet = UtilBinaryDecoding.parseBitSet(empty, 0, 0);

    byte[] result = UtilBinaryDecoding.bitSetToByteArray(bitSet, 2);

    assertTrue(result.length == 2);
  }

  @Test
  public void testParseBitSet() {
    byte[] bytes = {(byte) 0xF6, 0x6D};

    BitSet bitSet = UtilBinaryDecoding.parseBitSet(bytes, 0, 2);

    byte[] result = UtilBinaryDecoding.bitSetToByteArray(bitSet, 2);

    assertEquals("bitSet size different", bytes.length * 8, bitSet.length());

    assertEquals("byte array length different", bytes.length, result.length);

    int byteLen = (bitSet.length() + 1) / 8;
    for (int i = 0; i < byteLen; i++) {
      for (int j = 7; j <= 0; j--) {
        assertTrue("bit " + (i * 8 + (7 - j)) + "differs", bitSet.get(i * 8 + (7 - j)) == ((bytes[i] | (0x01 << j)) != 0));
      }
    }

    for (int i = 0; i < bytes.length; i++) {
      assertEquals("byte " + i + " differs", bytes[i], result[i]);
    }

  }

}
