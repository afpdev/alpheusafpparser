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
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Code Page Index (CPI).
 */
public class CPI_CodePageIndex extends StructuredField {

  @AFPField
  private List<CPI_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var cpc = config.getCurrentCodePageControl();
    if (cpc == null) {
      cpc = new CPC_CodePageControl(); // Fallback
      cpc.setCpiRepeatingGroupLength(CPIRepeatingGroupLength.SingleByteCodePage);
    }
    var cpiRGLen = cpc.getCpiRepeatingGroupLength();
    var nrOfBytes = cpiRGLen.nrOfBytes();
    var baseRGSize = 9 + nrOfBytes;

    var actualLength = getActualLength(sfData, offset, length);
    if (actualLength >= baseRGSize) {
      repeatingGroups = new ArrayList<>();

      var pos = 0;
      while (pos + baseRGSize <= actualLength) {
        var cpirg = new CPI_RepeatingGroup();
        cpirg.graphicCharacterGID = new String(sfData, offset + pos, 8, config.getAfpCharSet()).trim();
        cpirg.graphicCharacterUseFlags = GraphicCharacterUseFlag.valueOf(sfData[offset + pos + 8] & 0xFF);
        cpirg.codePoint = UtilBinaryDecoding.parseInt(sfData, offset + pos + 9, nrOfBytes);

        pos += baseRGSize;

        if (cpiRGLen.isUnicodeScalarValues() && pos < actualLength) {
          int numberOfUnicodeScalarValues = sfData[offset + pos] & 0xFF;
          pos += 1;
          if (numberOfUnicodeScalarValues > 0) {
            cpirg.unicodeScalarValues = new ArrayList<>(numberOfUnicodeScalarValues);
            for (int i = 0; i < numberOfUnicodeScalarValues && pos + 4 <= actualLength; i++) {
              cpirg.unicodeScalarValues.add(UtilBinaryDecoding.parseLong(sfData, offset + pos, 4));
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
    var cpc = config.getCurrentCodePageControl();
    if (cpc == null) {
      cpc = new CPC_CodePageControl();
      cpc.setCpiRepeatingGroupLength(CPIRepeatingGroupLength.SingleByteCodePage);
    }
    var cpiRGLen = cpc.getCpiRepeatingGroupLength();
    var nrOfBytes = cpiRGLen.nrOfBytes();

    var baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        baos.write(UtilCharacterEncoding.stringToByteArray(rg.graphicCharacterGID, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
        baos.write(GraphicCharacterUseFlag.toByte(rg.graphicCharacterUseFlags));
        baos.write(UtilBinaryDecoding.intToByteArray(rg.codePoint, nrOfBytes));
        if (cpiRGLen.isUnicodeScalarValues()) {
          if (rg.unicodeScalarValues == null) {
            baos.write(0);
          } else {
            baos.write(rg.unicodeScalarValues.size());
            for (var usv : rg.unicodeScalarValues) {
              baos.write(UtilBinaryDecoding.longToByteArray(usv, 4));
            }
          }
        }
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<CPI_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<CPI_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  /**
   * Graphic Character Use Flags.
   */
  public enum GraphicCharacterUseFlag {
    /**
     * When this flag is set, the character is an invalid coded character.
     */
    InvalidCodedCharacter,
    /**
     * When this flag is set, the character not to be printed.
     */
    NoPresentation,
    /**
     * When this flag bit is set, the print position is not to be incremented.
     */
    NoIncrement;

    public static EnumSet<GraphicCharacterUseFlag> valueOf(int flagByte) {
      var result = EnumSet.noneOf(GraphicCharacterUseFlag.class);

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
      var result = 0;
      if (flags == null) {
        return result;
      }

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

  /**
   * CPI Repeating Group.
   */
  public static class CPI_RepeatingGroup {
    @AFPField
    private String graphicCharacterGID;
    @AFPField
    private EnumSet<GraphicCharacterUseFlag> graphicCharacterUseFlags;
    @AFPField
    private int codePoint;
    /**
     * Unicode scalar value to be mapped to the GCGID value and code point value.
     */
    @AFPField
    private List<Long> unicodeScalarValues;

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
