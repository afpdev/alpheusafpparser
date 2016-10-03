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

import com.mgz.afp.exceptions.AFPParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class UtilBinaryDecoding {


  /**
   * Converts the given short value to an array of byte of given length. In Java a short value has
   * the size of 2 bytes.
   */
  public static final byte[] shortToByteArray(short value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 2; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  public static final short parseShort(InputStream is, int length) throws AFPParserException, IOException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8);
      result += is.read();
    }
    return (short) result;
  }

  public static short parseShort(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    short result = 0;
    for (int i = 0; i < length; i++) {
      result = (short) (result << 8);
      result += (sfData[offset + i] & 0xFF);
    }
    return result;
  }

  public static final int parseInt(InputStream is, int length) throws IOException {
    if (length > 4) {
      throw new IOException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8);
      result += is.read();
    }
    return result;
  }

  public static int parseInt(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 4) {
      throw new AFPParserException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8);
      result += (sfData[offset + i] & 0xFF);
    }
    return result;
  }

  /**
   * Converts the given int value to an array of byte of given length. In Java an int value has the
   * size of 4 bytes.
   */
  public static final byte[] intToByteArray(int value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 4; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  public static final long parseLong(InputStream is, int length) throws IOException {
    if (length > 8) {
      throw new IOException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8);
      result += is.read();
    }
    return result;
  }

  public static long parseLong(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 8) {
      throw new AFPParserException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8);
      result += (sfData[offset + i] << 0) & 0xFF;
    }
    return result;
  }

  /**
   * Converts the given int value to an array of byte of given length. In Java an int value has the
   * size of 4 bytes.
   */
  public static final byte[] longToByteArray(long value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 8; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  public static BitSet parseBitSet(byte[] sfData, int offset, int length) {
    BitSet bitSet = new BitSet(length * 8);

    int pos = 0;
    for (int i = 0; i < length; i++) {
      byte b = sfData[offset + i];
      for (int j = 0; j < 8; j++) {
        boolean val = (b & (0x01 << 7 - j)) != 0;
        bitSet.set(pos, val);
        pos++;
      }
    }

    return bitSet;
  }

  public static byte[] bitSetToByteArray(BitSet bitSet, int byteLen) {
    byte[] result = new byte[byteLen];

    for (int i = 0; i < byteLen; i++) {
      for (int j = 7; j >= 0; j--) {
        if (bitSet.length() < (i * 8 + (7 - j))) {
          return result;
        }

        if (bitSet.get(i * 8 + (7 - j))) {
          result[i] |= (0x01 << j);
        }
      }
    }

    return result;
  }
}
