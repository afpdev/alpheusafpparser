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
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Font Orientation (FNO).
 */
@XmlRootElement
public class FNO_FontOrientation extends StructuredField {

  @AFPField
  private List<FNO_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnoRepeatingGroupLength((byte) 26); // Default
    }
    var rgLen = fnc.getFnoRepeatingGroupLength() & 0xFF;

    var actualLength = getActualLength(sfData, offset, length);
    repeatingGroups = new ArrayList<>();

    var pos = 0;
    while (pos + rgLen <= actualLength) {
      var rg = new FNO_RepeatingGroup();
      rg.reserved0_1 = new byte[2];
      System.arraycopy(sfData, offset + pos, rg.reserved0_1, 0, 2);
      rg.charRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + pos + 2, 2));
      rg.maxBaselineOffset = UtilBinaryDecoding.parseShort(sfData, offset + pos + 4, 2);
      rg.maxCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 6, 2);
      rg.spaceCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 8, 2);
      rg.maxBaselineExtent = UtilBinaryDecoding.parseShort(sfData, offset + pos + 10, 2);
      rg.controlFlags = FnoControlFlag.valueOf(sfData[offset + pos + 12]);
      rg.reserved13 = sfData[offset + pos + 13];
      rg.emSpaceIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 14, 2);
      rg.reserved16_17 = new byte[2];
      System.arraycopy(sfData, offset + pos + 16, rg.reserved16_17, 0, 2);
      rg.figureSpaceIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 18, 2);
      rg.nominalCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 20, 2);
      rg.defaultBaselineIncrement = UtilBinaryDecoding.parseInt(sfData, offset + pos + 22, 2);
      rg.minASpace = UtilBinaryDecoding.parseShort(sfData, offset + pos + 24, 2);

      repeatingGroups.add(rg);
      pos += rgLen;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnoRepeatingGroupLength((byte) 26);
    }
    var rgLen = fnc.getFnoRepeatingGroupLength() & 0xFF;

    var baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        var rgBaos = new ByteArrayOutputStream();
        rgBaos.write(rg.reserved0_1 != null ? rg.reserved0_1 : new byte[] {0, 0});
        rgBaos.write(rg.charRotation != null ? rg.charRotation.toBytes() : new byte[] {0, 0});
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.maxBaselineOffset, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.maxCharacterIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.spaceCharacterIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.maxBaselineExtent, 2));
        rgBaos.write(FnoControlFlag.toByte(rg.controlFlags));
        rgBaos.write(rg.reserved13);
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.emSpaceIncrement, 2));
        rgBaos.write(rg.reserved16_17 != null ? rg.reserved16_17 : new byte[] {0, 0});
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.figureSpaceIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.nominalCharacterIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray((short) rg.defaultBaselineIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.minASpace, 2));

        var rgData = rgBaos.toByteArray();
        if (rgData.length < rgLen) {
          baos.write(rgData);
          baos.write(new byte[rgLen - rgData.length]);
        } else {
          baos.write(rgData, 0, rgLen);
        }
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<FNO_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<FNO_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  /**
   * FNO Control Flag.
   */
  public enum FnoControlFlag {
    BaselineCoordinateSystem_XAxisDown, // bit 0
    UniformASpace, // bit 1
    FontIndexNumberPresent, // bit 2
    KerningDataPresent; // bit 3

    public static EnumSet<FnoControlFlag> valueOf(byte b) {
      var result = EnumSet.noneOf(FnoControlFlag.class);
      if ((b & 0x80) != 0) result.add(BaselineCoordinateSystem_XAxisDown);
      if ((b & 0x40) != 0) result.add(UniformASpace);
      if ((b & 0x20) != 0) result.add(FontIndexNumberPresent);
      if ((b & 0x10) != 0) result.add(KerningDataPresent);
      return result;
    }

    public static byte toByte(EnumSet<FnoControlFlag> flags) {
      byte result = 0;
      if (flags == null) return result;
      if (flags.contains(BaselineCoordinateSystem_XAxisDown)) result |= 0x80;
      if (flags.contains(UniformASpace)) result |= 0x40;
      if (flags.contains(FontIndexNumberPresent)) result |= 0x20;
      if (flags.contains(KerningDataPresent)) result |= 0x10;
      return result;
    }
  }

  /**
   * FNO Repeating Group.
   */
  public static class FNO_RepeatingGroup {
    @AFPField(size = 2)
    private byte[] reserved0_1 = new byte[] {0x00, 0x00};
    @AFPField
    private AFPOrientation charRotation;
    @AFPField
    private short maxBaselineOffset;
    @AFPField
    private short maxCharacterIncrement;
    @AFPField
    private short spaceCharacterIncrement;
    @AFPField
    private short maxBaselineExtent;
    @AFPField
    private EnumSet<FnoControlFlag> controlFlags;
    @AFPField
    private byte reserved13 = 0x00;
    @AFPField
    private short emSpaceIncrement;
    @AFPField(size = 2)
    private byte[] reserved16_17 = new byte[] {0x00, 0x00};
    @AFPField
    private short figureSpaceIncrement;
    @AFPField
    private short nominalCharacterIncrement;
    @AFPField
    private int defaultBaselineIncrement;
    @AFPField
    private short minASpace;

    public AFPOrientation getCharRotation() {
      return charRotation;
    }

    public void setCharRotation(AFPOrientation charRotation) {
      this.charRotation = charRotation;
    }

    public short getMaxBaselineOffset() {
      return maxBaselineOffset;
    }

    public void setMaxBaselineOffset(short maxBaselineOffset) {
      this.maxBaselineOffset = maxBaselineOffset;
    }

    public short getMaxCharacterIncrement() {
      return maxCharacterIncrement;
    }

    public void setMaxCharacterIncrement(short maxCharacterIncrement) {
      this.maxCharacterIncrement = maxCharacterIncrement;
    }

    public short getSpaceCharacterIncrement() {
      return spaceCharacterIncrement;
    }

    public void setSpaceCharacterIncrement(short spaceCharacterIncrement) {
      this.spaceCharacterIncrement = spaceCharacterIncrement;
    }

    public short getMaxBaselineExtent() {
      return maxBaselineExtent;
    }

    public void setMaxBaselineExtent(short maxBaselineExtent) {
      this.maxBaselineExtent = maxBaselineExtent;
    }

    public EnumSet<FnoControlFlag> getControlFlags() {
      return controlFlags;
    }

    public void setControlFlags(EnumSet<FnoControlFlag> controlFlags) {
      this.controlFlags = controlFlags;
    }

    public short getEmSpaceIncrement() {
      return emSpaceIncrement;
    }

    public void setEmSpaceIncrement(short emSpaceIncrement) {
      this.emSpaceIncrement = emSpaceIncrement;
    }

    public short getFigureSpaceIncrement() {
      return figureSpaceIncrement;
    }

    public void setFigureSpaceIncrement(short figureSpaceIncrement) {
      this.figureSpaceIncrement = figureSpaceIncrement;
    }

    public short getNominalCharacterIncrement() {
      return nominalCharacterIncrement;
    }

    public void setNominalCharacterIncrement(short nominalCharacterIncrement) {
      this.nominalCharacterIncrement = nominalCharacterIncrement;
    }

    public int getDefaultBaselineIncrement() {
      return defaultBaselineIncrement;
    }

    public void setDefaultBaselineIncrement(int defaultBaselineIncrement) {
      this.defaultBaselineIncrement = defaultBaselineIncrement;
    }

    public short getMinASpace() {
      return minASpace;
    }

    public void setMinASpace(short minASpace) {
      this.minASpace = minASpace;
    }
  }
}
