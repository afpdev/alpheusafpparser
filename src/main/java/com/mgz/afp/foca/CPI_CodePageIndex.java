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
import com.mgz.afp.foca.CPC_CodePageControl.CPIRepeatingGroupLength;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CPI_CodePageIndex extends StructuredField {
  private static final Charset cpIBM500 = Constants.cpIBM500;

  @AFPField
  List<CPI_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {


    CPC_CodePageControl cpc = config.getCurrentCodePageControl();
    CPIRepeatingGroupLength cpiRGLen = cpc.getCpiRepeatingGroupLength();
    short nrOfBytes = cpiRGLen.nrOfBytes();
    int minLength = 9 + nrOfBytes;

    checkDataLength(sfData, offset, length, minLength);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > minLength) {
      repeatingGroups = new ArrayList<CPI_RepeatingGroup>();

      int pos = 0;
      while (pos < actualLength) {

        CPI_RepeatingGroup cpirg = new CPI_RepeatingGroup();
        cpirg.graphicCharacterGID = new String(sfData, offset + pos, 8, cpIBM500);
        cpirg.graphicCharacterUseFlags = GraphicCharacterUseFlag.valueOf(sfData[offset + pos + 8] & 0xFF);
        cpirg.codePoint = UtilBinaryDecoding.parseInt(sfData, offset + pos + 9, nrOfBytes);

        pos += minLength;

        if (cpiRGLen.isUnicodeScalarValues() && pos < actualLength) {
          short numberOfUnicodeScalarValues = UtilBinaryDecoding.parseShort(sfData, offset + pos, 1);
          if (numberOfUnicodeScalarValues > 0) {
            cpirg.unicodeScalarValues = new ArrayList<Long>(numberOfUnicodeScalarValues);
            checkDataLength(sfData, offset, length, minLength + 1 + (numberOfUnicodeScalarValues * 4));
            for (int i = 0; i < numberOfUnicodeScalarValues; i++) {
              cpirg.unicodeScalarValues.add(Long.valueOf(UtilBinaryDecoding.parseLong(sfData, offset + pos, 4)));
              pos += 4;
            }
          } else {
            cpirg.unicodeScalarValues = null;
          }
        } else {
          cpirg.unicodeScalarValues = null;
        }

        repeatingGroups.add(cpirg);

      }
    } else {
      repeatingGroups = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    CPC_CodePageControl cpc = config.getCurrentCodePageControl();
    CPIRepeatingGroupLength cpiRGLen = cpc.getCpiRepeatingGroupLength();
    short nrOfBytes = cpiRGLen.nrOfBytes();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (CPI_RepeatingGroup rg : repeatingGroups) {
      baos.write(UtilCharacterEncoding.stringToByteArray(rg.graphicCharacterGID, cpIBM500, 8, Constants.EBCDIC_ID_FILLER));
      baos.write(GraphicCharacterUseFlag.toByte(rg.graphicCharacterUseFlags));
      baos.write(UtilBinaryDecoding.intToByteArray(rg.codePoint, nrOfBytes));
      if (cpiRGLen.isUnicodeScalarValues()) {
        if (rg.unicodeScalarValues == null) {
          baos.write(0);
        } else {
          baos.write(rg.unicodeScalarValues.size());
          for (Long usv : rg.unicodeScalarValues) {
            baos.write(UtilBinaryDecoding.longToByteArray(usv, 4));
          }
        }
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public enum GraphicCharacterUseFlag {
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

    public static EnumSet<GraphicCharacterUseFlag> valueOf(int flagByte) {
      EnumSet<GraphicCharacterUseFlag> result = EnumSet.noneOf(GraphicCharacterUseFlag.class);

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

    public static int toByte(EnumSet<GraphicCharacterUseFlag> flags) {
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

  public static class CPI_RepeatingGroup {
    @AFPField
    String graphicCharacterGID;
    @AFPField
    EnumSet<GraphicCharacterUseFlag> graphicCharacterUseFlags;
    @AFPField
    int codePoint;
    /**
     * Unicode scalar value to be mapped to the GCGID value and code point value.
     */
    @AFPField
    List<Long> unicodeScalarValues;

    public String getGraphicCharacterGID() {
      return graphicCharacterGID;
    }

    public void setGraphicCharacterGID(String graphicCharacterGID) {
      this.graphicCharacterGID = graphicCharacterGID;
    }

    public EnumSet<GraphicCharacterUseFlag> getGraphicCharacterUseFlags() {
      return graphicCharacterUseFlags;
    }

    public void setGraphicCharacterUseFlags(
        EnumSet<GraphicCharacterUseFlag> graphicCharacterUseFlags) {
      this.graphicCharacterUseFlags = graphicCharacterUseFlags;
    }

    public int getCodePoint() {
      return codePoint;
    }

    public void setCodePoint(int codePoint) {
      this.codePoint = codePoint;
    }

    public List<Long> getUnicodeScalarValues() {
      return unicodeScalarValues;
    }

    public void setUnicodeScalarValues(List<Long> unicodeScalarValues) {
      this.unicodeScalarValues = unicodeScalarValues;
    }
  }
}
