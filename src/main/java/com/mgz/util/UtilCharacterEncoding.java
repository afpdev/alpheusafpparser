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
import java.nio.charset.Charset;

public class UtilCharacterEncoding {
  final public static char[] hexArray = "0123456789ABCDEF".toCharArray();

  /**
   * Returns the hex value for a given character
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
    for (char hexChr : hexArray) if (hexChr == chr) return true;
    return false;
  }

  public static String bytesToHexString(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
  }

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
  public static byte[] stringToByteArray(String str, Charset charsetForEncoding, int lenOfByteArray, byte filler) {
    if (charsetForEncoding == null) charsetForEncoding = Charset.defaultCharset();
    byte[] encoded = str != null && str.length() > 0 ? str.getBytes(charsetForEncoding) : new byte[]{};
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

  public static String decodeEBCDIC(byte[] sfData, int offset, int length, AFPParserConfiguration config) {
    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    return new String(sfData, offset, actualLength, config.getAfpCharSet());
  }

  public static boolean isEBCDIC(byte[] data) {
    for (int i = 0; i < data.length; i++) {
      if (!Character.isDefined((char) data[i])) return true;
    }
    return false;
  }

  public static String reduceLabel(String s) {
    if (s == null) return "null";
    s = s.trim();
    if (s.length() == 0) return "";

    StringBuilder sb = new StringBuilder();
    if (Character.isLowerCase(s.charAt(0))) sb.append(Character.toUpperCase(s.charAt(0)));


    for (char ch : s.toCharArray()) {
      if (Character.isUpperCase(ch)) sb.append(ch);
    }

    if (sb.length() == 0) sb.append(s.indexOf(0));

    return sb.toString();
  }

  public static String addBlankBeforeUpperCaseGroupAndDigitGroup(String name) {
    StringBuilder sb = new StringBuilder();
    boolean isFirstChar = true;

    for (int i = 0; i < name.length(); i++) {
      char c = name.charAt(i);

      if (i > 0) {
        char prevC = name.charAt(i - 1);
        if (Character.isDigit(c) && !Character.isDigit(prevC)) sb.append(' ');
        else if (Character.isUpperCase(c) && !Character.isUpperCase(prevC)) sb.append(' ');
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
    if (text == null) return null;
    text = text.replace("0x", "").trim();
    if (text.length() == 0) return null;

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

    if (baos.size() == 0) return null;
    return baos.toByteArray();
  }

}
