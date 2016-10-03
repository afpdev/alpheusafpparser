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
package com.mgz.afp.foca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.EnumSet;

/**
 * The Code Page Control (CPC) contains information about the code page.
 */
public class CPC_CodePageControl extends StructuredField {
  private static final Charset cpIBM500 = Constants.cpIBM500;

  /**
   * "This parameter is EBCDIC encoded using CPGID 500 (International #5) and GCSGID 103
   * (International DP 94 (ASCII))."
   */
  @AFPField
  String defaultGraphicCharacterGlobalID;
  @AFPField
  EnumSet<DefaultCharacterUseFlag> defaultCharacterUseFlags;
  @AFPField
  CPIRepeatingGroupLength cpiRepeatingGroupLength;
  @AFPField
  short spaceCharacterSectionNumber;
  @AFPField
  short spaceCharacterCodePoint;
  @AFPField
  EnumSet<CodePageUseFlag> codePageUseFlags;
  @AFPField
  long unicodeScalarValue;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length,
                        AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 13);

    defaultGraphicCharacterGlobalID = new String(sfData, 0, 8, cpIBM500);
    defaultCharacterUseFlags = DefaultCharacterUseFlag.valueOf(sfData[8] & 0xFF);
    cpiRepeatingGroupLength = CPIRepeatingGroupLength.valueOf(sfData[9] & 0xFF);
    spaceCharacterSectionNumber = UtilBinaryDecoding.parseShort(sfData, offset + 10, 1);
    spaceCharacterCodePoint = UtilBinaryDecoding.parseShort(sfData, offset + 11, 1);
    codePageUseFlags = CodePageUseFlag.valueOf(sfData[12] & 0xFF);
    if (cpiRepeatingGroupLength == CPIRepeatingGroupLength.SingleByteCodePageUnicodeScalarValues
        || cpiRepeatingGroupLength == CPIRepeatingGroupLength.DoubleByteCodePageUnicodeScalarValues) {
      unicodeScalarValue = UtilBinaryDecoding.parseLong(sfData, 13, 4);
    }

  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(UtilCharacterEncoding.stringToByteArray(defaultGraphicCharacterGlobalID, cpIBM500, 8, Constants.EBCDIC_ID_FILLER));
    baos.write(DefaultCharacterUseFlag.toByte(defaultCharacterUseFlags));
    baos.write(cpiRepeatingGroupLength.val);
    baos.write(UtilBinaryDecoding.shortToByteArray(spaceCharacterSectionNumber, 1));
    baos.write(UtilBinaryDecoding.shortToByteArray(spaceCharacterCodePoint, 1));
    baos.write(CodePageUseFlag.toByte(codePageUseFlags));
    if (cpiRepeatingGroupLength == CPIRepeatingGroupLength.SingleByteCodePageUnicodeScalarValues
        || cpiRepeatingGroupLength == CPIRepeatingGroupLength.DoubleByteCodePageUnicodeScalarValues) {
      baos.write(UtilBinaryDecoding.longToByteArray(unicodeScalarValue, 4));
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getDefaultGraphicCharacterGlobalID() {
    return defaultGraphicCharacterGlobalID;
  }

  public void setDefaultGraphicCharacterGlobalID(
      String defaultGraphicCharacterGlobalID) {
    this.defaultGraphicCharacterGlobalID = defaultGraphicCharacterGlobalID;
  }

  public EnumSet<DefaultCharacterUseFlag> getDefaultCharacterUseFlags() {
    return defaultCharacterUseFlags;
  }

  public void setDefaultCharacterUseFlags(
      EnumSet<DefaultCharacterUseFlag> defaultCharacterUseFlags) {
    this.defaultCharacterUseFlags = defaultCharacterUseFlags;
  }

  public CPIRepeatingGroupLength getCpiRepeatingGroupLength() {
    return cpiRepeatingGroupLength;
  }

  public void setCpiRepeatingGroupLength(
      CPIRepeatingGroupLength cpiRepeatingGroupLength) {
    this.cpiRepeatingGroupLength = cpiRepeatingGroupLength;
  }

  public short getSpaceCharacterSectionNumber() {
    return spaceCharacterSectionNumber;
  }

  public void setSpaceCharacterSectionNumber(short spaceCharacterSectionNumber) {
    this.spaceCharacterSectionNumber = spaceCharacterSectionNumber;
  }

  public short getSpaceCharacterCodePoint() {
    return spaceCharacterCodePoint;
  }

  public void setSpaceCharacterCodePoint(short spaceCharacterCodePoint) {
    this.spaceCharacterCodePoint = spaceCharacterCodePoint;
  }

  public EnumSet<CodePageUseFlag> getCodePageUseFlags() {
    return codePageUseFlags;
  }

  public void setCodePageUseFlags(EnumSet<CodePageUseFlag> codePageUseFlags) {
    this.codePageUseFlags = codePageUseFlags;
  }

  public long getUnicodeScalarValue() {
    return unicodeScalarValue;
  }

  public void setUnicodeScalarValue(long unicodeScalarValue) {
    this.unicodeScalarValue = unicodeScalarValue;
  }

  public enum DefaultCharacterUseFlag {
    /**
     * When this flag is set, the character is an invalid coded character (see “Invalid Coded
     * Character” on page 112). When this flag bit is set to B'0' (off), the character is a valid
     * coded character.
     */
    InvalidCodedCharacter,
    /**
     * When this flag is set, the character not to be printed (see “No Presentation” on page 113).
     * When this flag bit is set to B'0' (off), the character is to be printed.
     */
    NoPresentation,
    /**
     * When this flag bit is set, the print position is not to be incremented (see “No Increment” on
     * page 112). When this flag bit is set to B'0' (off), the print position is to be incremented.
     */
    NoIncrement;

    public static EnumSet<DefaultCharacterUseFlag> valueOf(int flagByte) {
      EnumSet<DefaultCharacterUseFlag> result = EnumSet.noneOf(DefaultCharacterUseFlag.class);

      if ((flagByte & 0x80) != 0) {
        result.add(InvalidCodedCharacter);
      }
      if ((flagByte & 0x40) != 0) {
        result.add(NoPresentation);
      }
      if ((flagByte & 0x20) != 0) {
        result.add(NoIncrement);
      }

      return result;
    }

    public static int toByte(EnumSet<DefaultCharacterUseFlag> flags) {
      int result = 0;

      if (flags.contains(InvalidCodedCharacter)) {
        result += 0x80;
      }
      if (flags.contains(NoPresentation)) {
        result += 0x40;
      }
      if (flags.contains(NoIncrement)) {
        result += 0x20;
      }

      return result;
    }
  }

  public enum CPIRepeatingGroupLength {
    SingleByteCodePage(0x0A),
    DoubleByteCodePage(0x0B),
    SingleByteCodePageUnicodeScalarValues(0xFE),
    DoubleByteCodePageUnicodeScalarValues(0xFF);

    int val;

    CPIRepeatingGroupLength(int val) {
      this.val = val;
    }

    public static CPIRepeatingGroupLength valueOf(int cpiRepeatingGroupLengthByte) {
      for (CPIRepeatingGroupLength cpiRGL : values()) {
        if (cpiRGL.val == cpiRepeatingGroupLengthByte) {
          return cpiRGL;
        }
      }
      return null;
    }

    public int toByte() {
      return val;
    }

    public short nrOfBytes() {
      if (this == DoubleByteCodePage || this == DoubleByteCodePageUnicodeScalarValues) {
        return 2;
      } else {
        return 1;
      }
    }

    public boolean isUnicodeScalarValues() {
      if (this == SingleByteCodePageUnicodeScalarValues || this == DoubleByteCodePageUnicodeScalarValues) {
        return true;
      } else {
        return false;
      }
    }
  }

  public enum CodePageUseFlag {
    /**
     * When this flag bit is set to B'1' (on), the CPI repeating groups are sorted in ascending code
     * point order. When this flag bit is set to B'0' (off), the CPI repeating groups are sorted in
     * ascending character ID order.
     */
    SortOrder,
    /**
     * This flag is used to enable variable spacing. When the flag is set, the PTOCA variable space
     * increment is used whenever the code point for the variable space character is encountered.
     * Some AFP products, such as DCF, use variable spacing for text justification and therefore
     * require this flag to be set. When the flag is not set, all PTOCA SVI control sequences are
     * ignored and the space character is printed as defined in the accompanying font whenever the
     * variable space code point is encountered. If a code page is built that has this flag not set,
     * it should not be used with AFP products that require the variable space function.
     */
    VariableSpaceEnable;

    public static EnumSet<CodePageUseFlag> valueOf(int flagByte) {
      EnumSet<CodePageUseFlag> result = EnumSet.noneOf(CodePageUseFlag.class);

      if ((flagByte & 0x80) != 0) {
        result.add(SortOrder);
      }
      if ((flagByte & 0x08) != 0) {
        result.add(VariableSpaceEnable);
      }

      return result;
    }

    public static int toByte(EnumSet<CodePageUseFlag> flags) {
      int result = 0;

      if (flags.contains(SortOrder)) {
        result += 0x80;
      }
      if (flags.contains(VariableSpaceEnable)) {
        result += 0x08;
      }

      return result;
    }
  }
}
