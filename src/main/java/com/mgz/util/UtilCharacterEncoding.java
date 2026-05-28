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

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParserConfiguration;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * Utility class for character encoding and hex string operations.
 */
public class UtilCharacterEncoding {
  private static final Logger logger = Logger.getLogger(UtilCharacterEncoding.class.getName());

  private static final char[] EBCDIC_CP500_TO_UTF8 = {
    0x0000, 0x0001, 0x0002, 0x0003, 0x009c, 0x0009, 0x0086, 0x007f,
    0x0097, 0x008d, 0x008e, 0x000b, 0x000c, 0x000d, 0x000e, 0x000f,
    0x0010, 0x0011, 0x0012, 0x0013, 0x009d, 0x000a, 0x0008, 0x0087,
    0x0018, 0x0019, 0x0092, 0x008f, 0x001c, 0x001d, 0x001e, 0x001f,
    0x0080, 0x0081, 0x0082, 0x0083, 0x0084, 0x000a, 0x0017, 0x001b,
    0x0088, 0x0089, 0x008a, 0x008b, 0x008c, 0x0005, 0x0006, 0x0007,
    0x0090, 0x0091, 0x0016, 0x0093, 0x0094, 0x0095, 0x0096, 0x0004,
    0x0098, 0x0099, 0x009a, 0x009b, 0x0014, 0x0015, 0x009e, 0x001a,
    0x0020, 0x00a0, 0x00e2, 0x00e4, 0x00e0, 0x00e1, 0x00e3, 0x00e5,
    0x00e7, 0x00f1, 0x005b, 0x002e, 0x003c, 0x0028, 0x002b, 0x0021,
    0x0026, 0x00e9, 0x00ea, 0x00eb, 0x00e8, 0x00ed, 0x00ee, 0x00ef,
    0x00ec, 0x00df, 0x005d, 0x0024, 0x002a, 0x0029, 0x003b, 0x005e,
    0x002d, 0x002f, 0x00c2, 0x00c4, 0x00c0, 0x00c1, 0x00c3, 0x00c5,
    0x00c7, 0x00d1, 0x00a6, 0x002c, 0x0025, 0x005f, 0x003e, 0x003f,
    0x00f8, 0x00c9, 0x00ca, 0x00cb, 0x00c8, 0x00cd, 0x00ce, 0x00cf,
    0x00cc, 0x0060, 0x003a, 0x0023, 0x0040, 0x0027, 0x003d, 0x0022,
    0x00d8, 0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067,
    0x0068, 0x0069, 0x00ab, 0x00bb, 0x00f0, 0x00fd, 0x00fe, 0x00b1,
    0x00b0, 0x006a, 0x006b, 0x006c, 0x006d, 0x006e, 0x006f, 0x0070,
    0x0071, 0x0072, 0x00aa, 0x00ba, 0x00e6, 0x00b8, 0x00c6, 0x00a4,
    0x00b5, 0x007e, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077, 0x0078,
    0x0079, 0x007a, 0x00a1, 0x00bf, 0x00d0, 0x00dd, 0x00de, 0x00ae,
    0x00a2, 0x00a3, 0x00a5, 0x00b7, 0x00a9, 0x00a7, 0x00b6, 0x00bc,
    0x00bd, 0x00be, 0x00ac, 0x007c, 0x00af, 0x00a8, 0x00b4, 0x00d7,
    0x007b, 0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047,
    0x0048, 0x0049, 0x00ad, 0x00f4, 0x00f6, 0x00f2, 0x00f3, 0x00f5,
    0x007d, 0x004a, 0x004b, 0x004c, 0x004d, 0x004e, 0x004f, 0x0050,
    0x0051, 0x0052, 0x00b9, 0x00fb, 0x00fc, 0x00f9, 0x00fa, 0x00ff,
    0x005c, 0x00f7, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058,
    0x0059, 0x005a, 0x00b2, 0x00d4, 0x00d6, 0x00d2, 0x00d3, 0x00d5,
    0x0030, 0x0031, 0x0032, 0x0033, 0x0034, 0x0035, 0x0036, 0x0037,
    0x0038, 0x0039, 0x00b3, 0x00db, 0x00dc, 0x00d9, 0x00da, 0x009f,
  };

  private static final char[] EBCDIC_CP273_TO_UTF8 = {
    0x0000, 0x0001, 0x0002, 0x0003, 0x009c, 0x0009, 0x0086, 0x007f,
    0x0097, 0x008d, 0x008e, 0x000b, 0x000c, 0x000d, 0x000e, 0x000f,
    0x0010, 0x0011, 0x0012, 0x0013, 0x009d, 0x000a, 0x0008, 0x0087,
    0x0018, 0x0019, 0x0092, 0x008f, 0x001c, 0x001d, 0x001e, 0x001f,
    0x0080, 0x0081, 0x0082, 0x0083, 0x0084, 0x000a, 0x0017, 0x001b,
    0x0088, 0x0089, 0x008a, 0x008b, 0x008c, 0x0005, 0x0006, 0x0007,
    0x0090, 0x0091, 0x0016, 0x0093, 0x0094, 0x0095, 0x0096, 0x0004,
    0x0098, 0x0099, 0x009a, 0x009b, 0x0014, 0x0015, 0x009e, 0x001a,
    0x0020, 0x00a0, 0x00e2, 0x007b, 0x00e0, 0x00e1, 0x00e3, 0x00e5,
    0x00e7, 0x00f1, 0x00c4, 0x002e, 0x003c, 0x0028, 0x002b, 0x0021,
    0x0026, 0x00e9, 0x00ea, 0x00eb, 0x00e8, 0x00ed, 0x00ee, 0x00ef,
    0x00ec, 0x007e, 0x00dc, 0x0024, 0x002a, 0x0029, 0x003b, 0x005e,
    0x002d, 0x002f, 0x00c2, 0x005b, 0x00c0, 0x00c1, 0x00c3, 0x00c5,
    0x00c7, 0x00d1, 0x00f6, 0x002c, 0x0025, 0x005f, 0x003e, 0x003f,
    0x00f8, 0x00c9, 0x00ca, 0x00cb, 0x00c8, 0x00cd, 0x00ce, 0x00cf,
    0x00cc, 0x0060, 0x003a, 0x0023, 0x00a7, 0x0027, 0x003d, 0x0022,
    0x00d8, 0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067,
    0x0068, 0x0069, 0x00ab, 0x00bb, 0x00f0, 0x00fd, 0x00fe, 0x00b1,
    0x00b0, 0x006a, 0x006b, 0x006c, 0x006d, 0x006e, 0x006f, 0x0070,
    0x0071, 0x0072, 0x00aa, 0x00ba, 0x00e6, 0x00b8, 0x00c6, 0x00a4,
    0x00b5, 0x00df, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077, 0x0078,
    0x0079, 0x007a, 0x00a1, 0x00bf, 0x00d0, 0x00dd, 0x00de, 0x00ae,
    0x00a2, 0x00a3, 0x00a5, 0x00b7, 0x00a9, 0x0040, 0x00b6, 0x00bc,
    0x00bd, 0x00be, 0x00ac, 0x007c, 0x00af, 0x00a8, 0x00b4, 0x00d7,
    0x00e4, 0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047,
    0x0048, 0x0049, 0x00ad, 0x00f4, 0x00a6, 0x00f2, 0x00f3, 0x00f5,
    0x00fc, 0x004a, 0x004b, 0x004c, 0x004d, 0x004e, 0x004f, 0x0050,
    0x0051, 0x0052, 0x00b9, 0x00fb, 0x007d, 0x00f9, 0x00fa, 0x00ff,
    0x00d6, 0x00f7, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058,
    0x0059, 0x005a, 0x00b2, 0x00d4, 0x005c, 0x00d2, 0x00d3, 0x00d5,
    0x0030, 0x0031, 0x0032, 0x0033, 0x0034, 0x0035, 0x0036, 0x0037,
    0x0038, 0x0039, 0x00b3, 0x00db, 0x005d, 0x00d9, 0x00da, 0x009f,
  };

  /**
   * Character array of hex digits.
   */
  public static final char[] hexArray = "0123456789ABCDEF".toCharArray();

  /**
   * Returns the hex value for a given character.
   *
   * @param hexDigit the hex digit character
   * @return the integer value of the hex digit
   */
  public static int valOfHexDigit(char hexDigit) {
    int result = 0;
    switch (hexDigit) {
      case '0':
        result = 0;
        break;
      case '1':
        result = 1;
        break;
      case '2':
        result = 2;
        break;
      case '3':
        result = 3;
        break;
      case '4':
        result = 4;
        break;
      case '5':
        result = 5;
        break;
      case '6':
        result = 6;
        break;
      case '7':
        result = 7;
        break;
      case '8':
        result = 8;
        break;
      case '9':
        result = 9;
        break;
      case 'A':
        result = 10;
        break;
      case 'a':
        result = 10;
        break;
      case 'B':
        result = 11;
        break;
      case 'b':
        result = 11;
        break;
      case 'C':
        result = 12;
        break;
      case 'c':
        result = 12;
        break;
      case 'D':
        result = 13;
        break;
      case 'd':
        result = 13;
        break;
      case 'E':
        result = 14;
        break;
      case 'e':
        result = 14;
        break;
      case 'F':
        result = 15;
        break;
      case 'f':
        result = 15;
        break;
      default:
        result = -1;
        break;
    }
    return result;
  }

  /**
   * Returns true if the given char is a hex digit. Hex digits are: 0123456789ABCDEF.
   *
   * @param chr character to test.
   * @return true if the given character is a hex digit.
   */
  public static boolean isHexDigit(char chr) {
    for (char hexChr : hexArray) {
      if (hexChr == chr) {
        return true;
      }
    }
    return false;
  }

  /**
   * Converts a byte array to a hex string.
   *
   * @param bytes the byte array to convert
   * @return the resulting hex string
   */
  public static String bytesToHexString(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
  }

  /**
   * Converts a byte array to a hex string with spaces between bytes.
   *
   * @param bytes the byte array to convert
   * @return the resulting hex string with spaces
   */
  public static String bytesToHexStringWithSpace(byte[] bytes) {
    char[] hexChars = new char[2 + ((bytes.length - 1) * 3)];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 3] = hexArray[v >>> 4];
      hexChars[j * 3 + 1] = hexArray[v & 0x0F];
      if (hexChars.length < (j * 3 + 2)) {
        hexChars[j * 3 + 2] = ' ';
      }
    }
    return new String(hexChars);
  }

  /**
   * Converts the given {@link String} into a byte array of given length using the given {@link
   * Charset} for encoding. If the encoded bytes of the given {@link String} is shorter than the
   * given length, the additional encoded bytes are filled with given byte filler. If the encoded
   * bytes of the given {@link String} is longer than the given length, the additional encoded bytes
   * are truncated.
   *
   * @param str                {@link String} to encode.
   * @param charsetForEncoding {@link Charset} used for encoding.
   * @param lenOfByteArray     length of the resulting byte array.
   * @param filler             byte used for pedding if encoded string is shorter than given
   *                           length.
   * @return byte array containing the resulting encoded {@link String}
   */
  public static byte[] stringToByteArray(
      String str, Charset charsetForEncoding, int lenOfByteArray, byte filler) {
    if (charsetForEncoding == null) {
      charsetForEncoding = Charset.defaultCharset();
    }
    byte[] encoded =
        str != null && str.length() > 0 ? str.getBytes(charsetForEncoding) : new byte[] {};
    byte[] result = new byte[lenOfByteArray];
    for (int i = 0; i < lenOfByteArray; i++) {
      if (i < encoded.length) {
        result[i] = encoded[i];
      } else {
        result[i] = filler;
      }
    }
    return result;
  }

  /**
   * Decodes EBCDIC data from a byte array.
   *
   * @param sfData the byte array containing EBCDIC data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @param config the parser configuration for charset resolution
   * @return the decoded string
   */
  public static String decodeEbcdic(
      byte[] sfData, int offset, int length, AFPParserConfiguration config) {
    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    if (actualLength <= 0) {
      return "";
    }
    String charsetName = config.getAfpCharSet().name();
    if ("IBM500".equals(charsetName) || "Cp500".equals(charsetName)) {
      return decodeCp500(sfData, offset, actualLength);
    } else if ("IBM273".equals(charsetName) || "Cp273".equals(charsetName)) {
      return decodeCp273(sfData, offset, actualLength);
    }
    return new String(sfData, offset, actualLength, config.getAfpCharSet());
  }

  /**
   * Decodes EBCDIC data from a {@link ByteBuffer}.
   *
   * @param buffer the buffer containing EBCDIC data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @param config the parser configuration for charset resolution
   * @return the decoded string
   */
  public static String decodeEbcdic(
      ByteBuffer buffer, int offset, int length, AFPParserConfiguration config) {
    int actualLength = length != -1 ? length : buffer.limit() - offset;
    if (actualLength <= 0) {
      return "";
    }
    String charsetName = config.getAfpCharSet().name();
    if ("IBM500".equals(charsetName) || "Cp500".equals(charsetName)) {
      return decodeCp500(buffer, offset, actualLength);
    } else if ("IBM273".equals(charsetName) || "Cp273".equals(charsetName)) {
      return decodeCp273(buffer, offset, actualLength);
    }

    byte[] data = new byte[actualLength];
    int oldPos = buffer.position();
    buffer.position(offset);
    buffer.get(data);
    buffer.position(oldPos);
    return new String(data, config.getAfpCharSet());
  }

  /**
   * Decodes CP500 EBCDIC data using a fast lookup table.
   *
   * @param data the byte array containing CP500 data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @return the decoded string
   */
  public static String decodeCp500(byte[] data, int offset, int length) {
    char[] out = new char[length];
    for (int i = 0; i < length; i++) {
      out[i] = EBCDIC_CP500_TO_UTF8[data[offset + i] & 0xFF];
    }
    return new String(out);
  }

  /**
   * Decodes CP500 EBCDIC data from a {@link ByteBuffer} using a fast lookup table.
   *
   * @param buffer the buffer containing CP500 data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @return the decoded string
   */
  public static String decodeCp500(ByteBuffer buffer, int offset, int length) {
    char[] out = new char[length];
    for (int i = 0; i < length; i++) {
      out[i] = EBCDIC_CP500_TO_UTF8[buffer.get(offset + i) & 0xFF];
    }
    return new String(out);
  }

  /**
   * Decodes CP273 EBCDIC data using a fast lookup table.
   *
   * @param data the byte array containing CP273 data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @return the decoded string
   */
  public static String decodeCp273(byte[] data, int offset, int length) {
    char[] out = new char[length];
    for (int i = 0; i < length; i++) {
      out[i] = EBCDIC_CP273_TO_UTF8[data[offset + i] & 0xFF];
    }
    return new String(out);
  }

  /**
   * Decodes CP273 EBCDIC data from a {@link ByteBuffer} using a fast lookup table.
   *
   * @param buffer the buffer containing CP273 data
   * @param offset the starting offset
   * @param length the number of bytes to decode
   * @return the decoded string
   */
  public static String decodeCp273(ByteBuffer buffer, int offset, int length) {
    char[] out = new char[length];
    for (int i = 0; i < length; i++) {
      out[i] = EBCDIC_CP273_TO_UTF8[buffer.get(offset + i) & 0xFF];
    }
    return new String(out);
  }

  /**
   * Checks if the given byte array is likely EBCDIC data.
   *
   * @param data the byte array to check
   * @return true if it is likely EBCDIC
   */
  public static boolean isEbcdic(byte[] data) {
    for (int i = 0; i < data.length; i++) {
      if (!Character.isDefined((char) data[i])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the given data, when decoded with the given charset, consists mostly of
   * printable characters.
   *
   * @param data    byte array to test.
   * @param charset Charset used for decoding.
   * @return true if data is human-readable.
   */
  public static boolean isHumanReadable(byte[] data, Charset charset) {
    if (data == null || data.length == 0) {
      return false;
    }
    if (charset == null) {
      charset = Constants.cpIBM500;
    }

    String charsetName = charset.name();
    if ("IBM500".equals(charsetName) || "Cp500".equals(charsetName)) {
      return isHumanReadableCp500(data);
    } else if ("IBM273".equals(charsetName) || "Cp273".equals(charsetName)) {
      return isHumanReadableCp273(data);
    }

    String decoded = new String(data, charset);
    int printableCount = 0;
    for (int i = 0; i < decoded.length(); i++) {
      char c = decoded.charAt(i);
      // We count printable characters and common whitespace/control characters as human-readable.
      // EBCDIC Next Line (NEL) is U+0085.
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / decoded.length() >= 0.9;
  }

  /**
   * Returns true if the given data in a {@link ByteBuffer}, when decoded with the given charset,
   * consists mostly of printable characters.
   *
   * @param buffer  buffer to test.
   * @param offset  starting offset.
   * @param length  length to test.
   * @param charset Charset used for decoding.
   * @return true if data is human-readable.
   */
  public static boolean isHumanReadable(ByteBuffer buffer, int offset, int length, Charset charset) {
    int actualLength = length != -1 ? length : buffer.limit() - offset;
    if (actualLength <= 0) {
      return false;
    }
    if (charset == null) {
      charset = Constants.cpIBM500;
    }

    String charsetName = charset.name();
    if ("IBM500".equals(charsetName) || "Cp500".equals(charsetName)) {
      return isHumanReadableCp500(buffer, offset, actualLength);
    } else if ("IBM273".equals(charsetName) || "Cp273".equals(charsetName)) {
      return isHumanReadableCp273(buffer, offset, actualLength);
    }

    byte[] data = new byte[actualLength];
    int oldPos = buffer.position();
    buffer.position(offset);
    buffer.get(data);
    buffer.position(oldPos);

    String decoded = new String(data, charset);
    int printableCount = 0;
    for (int i = 0; i < decoded.length(); i++) {
      char c = decoded.charAt(i);
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / decoded.length() >= 0.9;
  }

  private static boolean isHumanReadableCp500(byte[] data) {
    int printableCount = 0;
    for (byte b : data) {
      char c = EBCDIC_CP500_TO_UTF8[b & 0xFF];
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / data.length >= 0.9;
  }

  private static boolean isHumanReadableCp500(ByteBuffer buffer, int offset, int length) {
    int printableCount = 0;
    for (int i = 0; i < length; i++) {
      char c = EBCDIC_CP500_TO_UTF8[buffer.get(offset + i) & 0xFF];
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / length >= 0.9;
  }

  private static boolean isHumanReadableCp273(byte[] data) {
    int printableCount = 0;
    for (byte b : data) {
      char c = EBCDIC_CP273_TO_UTF8[b & 0xFF];
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / data.length >= 0.9;
  }

  private static boolean isHumanReadableCp273(ByteBuffer buffer, int offset, int length) {
    int printableCount = 0;
    for (int i = 0; i < length; i++) {
      char c = EBCDIC_CP273_TO_UTF8[buffer.get(offset + i) & 0xFF];
      if (!Character.isISOControl(c) || c == '\n' || c == '\r' || c == '\t' || c == '\u0085') {
        printableCount++;
      }
    }
    return (double) printableCount / length >= 0.9;
  }

  /**
   * Reduces a label by keeping only uppercase characters.
   *
   * @param s the string to reduce
   * @return the reduced string
   */
  public static String reduceLabel(String s) {
    if (s == null) {
      return "null";
    }
    s = s.trim();
    if (s.length() == 0) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    if (Character.isLowerCase(s.charAt(0))) {
      sb.append(Character.toUpperCase(s.charAt(0)));
    }

    for (char ch : s.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        sb.append(ch);
      }
    }

    if (sb.length() == 0) {
      sb.append(s.indexOf(0));
    }

    return sb.toString();
  }

  /**
   * Adds a blank before groups of uppercase characters and digits.
   *
   * @param name the string to modify
   * @return the modified string
   */
  public static String addBlankBeforeUpperCaseGroupAndDigitGroup(String name) {
    StringBuilder sb = new StringBuilder();
    boolean isFirstChar = true;

    for (int i = 0; i < name.length(); i++) {
      char c = name.charAt(i);

      if (i > 0) {
        char prevC = name.charAt(i - 1);
        if (Character.isDigit(c) && !Character.isDigit(prevC)) {
          sb.append(' ');
        } else if (Character.isUpperCase(c) && !Character.isUpperCase(prevC)) {
          sb.append(' ');
        }
      }

      if (c == '_') {
        sb.append(' ');
        isFirstChar = true;
      } else {
        if (isFirstChar) {
          c = Character.toUpperCase(c);
          isFirstChar = false;
        }
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * Turns the given String of hex digits into a byte array. The given String may or may not contain
   * spaces and hex prefixes ("0x"). The
   *
   * @return array of bytes.
   */
  public static Object hexStringWithSpacesToByteArray(String text) {
    if (text == null) {
      return null;
    }
    text = text.replace("0x", "").trim();
    if (text.length() == 0) {
      return null;
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    int byteVal = 0;

    boolean isSecondHexDigit = false;
    for (int i = 0; i < text.length(); i++) {
      char chr = text.charAt(i);
      if (UtilCharacterEncoding.isHexDigit(chr)) {
        if (isSecondHexDigit) {
          baos.write(byteVal);
          byteVal = 0;
          isSecondHexDigit = false;
        } else {
          byteVal <<= 4;
          byteVal += UtilCharacterEncoding.valOfHexDigit(chr);
          isSecondHexDigit = true;
        }
      } else {
        if (isSecondHexDigit) {
          baos.write(byteVal);
          byteVal = 0;
          isSecondHexDigit = false;
        } else {
          continue;
        }
      }
    }

    if (baos.size() == 0) {
      return null;
    }
    return baos.toByteArray();
  }

  /**
   * Maps an AFP Code Page name (e.g., T1V10500) to a Java Charset.
   *
   * @param cpName AFP Code Page name.
   * @return The corresponding Charset, or null if not found.
   */
  public static Charset getCharsetFromCodePageName(String cpName) {
    if (cpName == null) {
      return null;
    }
    cpName = cpName.trim();
    if (cpName.isEmpty()) {
      return null;
    }
    String cpNumStr = null;
    if (cpName.startsWith("T1V10")) {
      cpNumStr = cpName.substring(5).trim();
    } else if (cpName.startsWith("T10")) {
      cpNumStr = cpName.substring(3).trim();
    } else if (cpName.startsWith("T1")) {
      // Common pattern is T1xxxxxx where last digits are the code page.
      // We try to take all digits from the end.
      int i = cpName.length() - 1;
      while (i >= 0 && Character.isDigit(cpName.charAt(i))) {
        i--;
      }
      cpNumStr = cpName.substring(i + 1);
    }

    if (cpNumStr != null && !cpNumStr.isEmpty()) {
      Charset cs = lookupCharset(cpNumStr);
      if (cs == null && cpNumStr.startsWith("100") && cpNumStr.length() > 3) {
        cs = lookupCharset(cpNumStr.substring(3));
      }

      if (cs == null) {
        logger.warning("Failed to find charset for code page number: " + cpNumStr);
      }
      return cs;
    }
    return null;
  }

  /**
   * Sanitizes a string for XML 1.0 by replacing invalid characters with a space.
   *
   * @param s the string to sanitize
   * @return the sanitized string
   */
  public static String sanitizeForXml(String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }

    boolean needsSanitization = false;
    for (int i = 0; i < s.length(); i++) {
      int codePoint = s.codePointAt(i);
      if (!isValidXml10CodePoint(codePoint)) {
        needsSanitization = true;
        break;
      }
      if (Character.isSupplementaryCodePoint(codePoint)) {
        i++;
      }
    }

    if (!needsSanitization) {
      return s;
    }

    StringBuilder sb = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++) {
      int codePoint = s.codePointAt(i);
      if (isValidXml10CodePoint(codePoint)) {
        sb.appendCodePoint(codePoint);
      } else {
        sb.append(' ');
      }
      if (Character.isSupplementaryCodePoint(codePoint)) {
        i++;
      }
    }
    return sb.toString();
  }

  private static boolean isValidXml10CodePoint(int cp) {
    return (cp == 0x9)
        || (cp == 0xA)
        || (cp == 0xD)
        || ((cp >= 0x20) && (cp <= 0xD7FF))
        || ((cp >= 0xE000) && (cp <= 0xFFFD))
        || ((cp >= 0x10000) && (cp <= 0x10FFFF));
  }

  private static Charset lookupCharset(String cpNumStr) {
    if (cpNumStr == null || cpNumStr.isEmpty()) {
      return null;
    }
    String[] prefixes = {"Cp", "IBM", "IBM-"};
    for (String prefix : prefixes) {
      String charsetName = prefix + cpNumStr;
      if (Charset.isSupported(charsetName)) {
        return Charset.forName(charsetName);
      }
    }

    // Try padding to 3 digits
    if (cpNumStr.length() > 0 && cpNumStr.length() < 3) {
      String padded = cpNumStr;
      while (padded.length() < 3) {
        padded = "0" + padded;
      }
      for (String prefix : prefixes) {
        String charsetName = prefix + padded;
        if (Charset.isSupported(charsetName)) {
          return Charset.forName(charsetName);
        }
      }
    }

    // Try stripping leading zeros
    if (cpNumStr.startsWith("0")) {
      String trimmed = cpNumStr.replaceFirst("^0+", "");
      if (!trimmed.isEmpty()) {
        for (String prefix : prefixes) {
          String charsetName = prefix + trimmed;
          if (Charset.isSupported(charsetName)) {
            return Charset.forName(charsetName);
          }
        }
      }
    }

    return null;
  }

}
