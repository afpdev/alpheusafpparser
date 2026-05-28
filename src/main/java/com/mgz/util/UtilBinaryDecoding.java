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
import java.nio.ByteBuffer;
import java.util.BitSet;

/**
 * Utility class for binary decoding operations.
 */
public class UtilBinaryDecoding {

  /**
   * Converts the given short value to an array of byte of given length. In Java a short value has
   * the size of 2 bytes.
   *
   * @param value the short value to convert
   * @param nrOfBytes the number of bytes in the resulting array
   * @return the byte array representation of the short value
   */
  public static final byte[] shortToByteArray(short value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 2; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  /**
   * Parses a short value from a {@link ByteBuffer} at the given offset.
   *
   * @param buffer the buffer to parse from
   * @param offset the starting offset in the buffer
   * @param length the number of bytes to parse
   * @return the parsed short value
   * @throws AFPParserException if the length exceeds the size of a short
   */
  public static short parseShort(ByteBuffer buffer, int offset, int length) throws AFPParserException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get(offset + i) & 0xFF);
    }
    return (short) result;
  }

  /**
   * Parses a short value from a {@link ByteBuffer}.
   *
   * @param buffer the buffer to parse from
   * @param length the number of bytes to parse
   * @return the parsed short value
   * @throws AFPParserException if the length exceeds the size of a short
   */
  public static short parseShort(ByteBuffer buffer, int length) throws AFPParserException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get() & 0xFF);
    }
    return (short) result;
  }

  /**
   * Parses an integer value from a {@link ByteBuffer} at the given offset.
   *
   * @param buffer the buffer to parse from
   * @param offset the starting offset in the buffer
   * @param length the number of bytes to parse
   * @return the parsed integer value
   * @throws AFPParserException if the length exceeds the size of an integer
   */
  public static int parseInt(ByteBuffer buffer, int offset, int length) throws AFPParserException {
    if (length > 4) {
      throw new AFPParserException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get(offset + i) & 0xFF);
    }
    return result;
  }

  /**
   * Parses a short value from an input stream.
   *
   * @param is the input stream to read from
   * @param length the number of bytes to read
   * @return the parsed short value
   * @throws AFPParserException if the length exceeds the size of a short
   * @throws IOException if an I/O error occurs
   */
  public static final short parseShort(InputStream is, int length)
      throws AFPParserException, IOException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      int b = is.read();
      if (b == -1) {
        throw new IOException("Reached end of stream while parsing short.");
      }
      result = (result << 8) | (b & 0xFF);
    }
    return (short) result;
  }

  /**
   * Parses a short value from a byte array.
   *
   * @param sfData the byte array to parse from
   * @param offset the starting offset in the array
   * @param length the number of bytes to parse
   * @return the parsed short value
   * @throws AFPParserException if the length exceeds the size of a short
   */
  public static short parseShort(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 2) {
      throw new AFPParserException("Short has max length of two bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (sfData[offset + i] & 0xFF);
    }
    return (short) result;
  }

  /**
   * Parses a long value from a {@link ByteBuffer} at the given offset.
   *
   * @param buffer the buffer to parse from
   * @param offset the starting offset in the buffer
   * @param length the number of bytes to parse
   * @return the parsed long value
   * @throws AFPParserException if the length exceeds the size of a long
   */
  public static long parseLong(ByteBuffer buffer, int offset, int length) throws AFPParserException {
    if (length > 8) {
      throw new AFPParserException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get(offset + i) & 0xFF);
    }
    return result;
  }

  /**
   * Parses an integer value from a {@link ByteBuffer}.
   *
   * @param buffer the buffer to parse from
   * @param length the number of bytes to parse
   * @return the parsed integer value
   * @throws AFPParserException if the length exceeds the size of an integer
   */
  public static int parseInt(ByteBuffer buffer, int length) throws AFPParserException {
    if (length > 4) {
      throw new AFPParserException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get() & 0xFF);
    }
    return result;
  }

  /**
   * Parses an integer value from an input stream.
   *
   * @param is the input stream to read from
   * @param length the number of bytes to read
   * @return the parsed integer value
   * @throws IOException if an I/O error occurs or the length exceeds the size of an integer
   */
  public static final int parseInt(InputStream is, int length) throws IOException {
    if (length > 4) {
      throw new IOException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      int b = is.read();
      if (b == -1) {
        throw new IOException("Reached end of stream while parsing integer.");
      }
      result = (result << 8) | (b & 0xFF);
    }
    return result;
  }

  /**
   * Parses a long value from a {@link ByteBuffer}.
   *
   * @param buffer the buffer to parse from
   * @param length the number of bytes to parse
   * @return the parsed long value
   * @throws AFPParserException if the length exceeds the size of a long
   */
  public static long parseLong(ByteBuffer buffer, int length) throws AFPParserException {
    if (length > 8) {
      throw new AFPParserException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (buffer.get() & 0xFF);
    }
    return result;
  }

  /**
   * Parses an integer value from a byte array.
   *
   * @param sfData the byte array to parse from
   * @param offset the starting offset in the array
   * @param length the number of bytes to parse
   * @return the parsed integer value
   * @throws AFPParserException if the length exceeds the size of an integer
   */
  public static int parseInt(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 4) {
      throw new AFPParserException("Integer has max length of four bytes.");
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (sfData[offset + i] & 0xFF);
    }
    return result;
  }

  /**
   * Converts the given int value to an array of byte of given length. In Java an int value has the
   * size of 4 bytes.
   *
   * @param value the integer value to convert
   * @param nrOfBytes the number of bytes in the resulting array
   * @return the byte array representation of the integer value
   */
  public static final byte[] intToByteArray(int value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 4; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  /**
   * Parses a long value from an input stream.
   *
   * @param is the input stream to read from
   * @param length the number of bytes to read
   * @return the parsed long value
   * @throws IOException if an I/O error occurs or the length exceeds the size of a long
   */
  public static final long parseLong(InputStream is, int length) throws IOException {
    if (length > 8) {
      throw new IOException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      int b = is.read();
      if (b == -1) {
        throw new IOException("Reached end of stream while parsing long.");
      }
      result = (result << 8) | (b & 0xFF);
    }
    return result;
  }

  /**
   * Parses a long value from a byte array.
   *
   * @param sfData the byte array to parse from
   * @param offset the starting offset in the array
   * @param length the number of bytes to parse
   * @return the parsed long value
   * @throws AFPParserException if the length exceeds the size of a long
   */
  public static long parseLong(byte[] sfData, int offset, int length) throws AFPParserException {
    if (length > 8) {
      throw new AFPParserException("Long integer has max length of eight bytes.");
    }
    long result = 0;
    for (int i = 0; i < length; i++) {
      result = (result << 8) | (sfData[offset + i] & 0xFF);
    }
    return result;
  }

  /**
   * Converts the given long value to an array of byte of given length. In Java a long value has the
   * size of 8 bytes.
   *
   * @param value the long value to convert
   * @param nrOfBytes the number of bytes in the resulting array
   * @return the byte array representation of the long value
   */
  public static final byte[] longToByteArray(long value, int nrOfBytes) {
    byte[] result = new byte[nrOfBytes];
    for (int i = 0; i < nrOfBytes && i < 8; i++) {
      result[nrOfBytes - 1 - i] = (byte) (value >>> (i * 8));
    }
    return result;
  }

  /**
   * Parses a BitSet from a byte array.
   *
   * @param sfData the byte array to parse from
   * @param offset the starting offset in the array
   * @param length the number of bytes to parse
   * @return the parsed BitSet
   */
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

  /**
   * Parses a BitSet from a {@link ByteBuffer}.
   *
   * @param buffer the buffer to parse from
   * @param length the number of bytes to parse
   * @return the parsed BitSet
   */
  public static BitSet parseBitSet(ByteBuffer buffer, int length) {
    BitSet bitSet = new BitSet(length * 8);

    int pos = 0;
    for (int i = 0; i < length; i++) {
      byte b = buffer.get();
      for (int j = 0; j < 8; j++) {
        boolean val = (b & (0x01 << 7 - j)) != 0;
        bitSet.set(pos, val);
        pos++;
      }
    }

    return bitSet;
  }

  /**
   * Converts a BitSet to a byte array.
   *
   * @param bitSet the BitSet to convert
   * @param byteLen the number of bytes in the resulting array
   * @return the byte array representation of the BitSet
   */
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
