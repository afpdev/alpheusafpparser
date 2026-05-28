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
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Font Index (FNI).
 */
public class FNI_FontIndex extends StructuredField {

  @AFPField
  private List<FNI_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFniRepeatingGroupLength((short) 28); // Default
    }
    var rgLen = fnc.getFniRepeatingGroupLength();

    var actualLength = getActualLength(sfData, offset, length);
    repeatingGroups = new ArrayList<>();

    var pos = 0;
    while (pos + rgLen <= actualLength) {
      var rg = new FNI_RepeatingGroup();
      rg.gcgid = new String(sfData, offset + pos, 8, config.getAfpCharSet()).trim();
      rg.charIncrement = UtilBinaryDecoding.parseShort(sfData, offset + pos + 8, 2);
      rg.ascenderHeight = UtilBinaryDecoding.parseShort(sfData, offset + pos + 10, 2);
      rg.descenderDepth = UtilBinaryDecoding.parseShort(sfData, offset + pos + 12, 2);
      rg.kernableCharacterFlags = (short) (sfData[offset + pos + 14] & 0xFF);
      rg.reserved15 = sfData[offset + pos + 15];
      rg.aSpace = UtilBinaryDecoding.parseShort(sfData, offset + pos + 16, 2);
      rg.bSpace = UtilBinaryDecoding.parseShort(sfData, offset + pos + 18, 2);
      rg.cSpace = UtilBinaryDecoding.parseShort(sfData, offset + pos + 20, 2);
      rg.reserved22_23 = UtilBinaryDecoding.parseShort(sfData, offset + pos + 22, 2);
      rg.baselineOffset = UtilBinaryDecoding.parseShort(sfData, offset + pos + 24, 2);
      rg.reserved26_27 = UtilBinaryDecoding.parseShort(sfData, offset + pos + 26, 2);

      repeatingGroups.add(rg);
      pos += rgLen;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFniRepeatingGroupLength((short) 28);
    }
    var rgLen = fnc.getFniRepeatingGroupLength();

    var baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        var rgBaos = new ByteArrayOutputStream();
        rgBaos.write(UtilCharacterEncoding.stringToByteArray(rg.gcgid, config.getAfpCharSet(), 8, (byte)0x40));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.charIncrement, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.ascenderHeight, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.descenderDepth, 2));
        rgBaos.write(rg.kernableCharacterFlags);
        rgBaos.write(rg.reserved15);
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.aSpace, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.bSpace, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.cSpace, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.reserved22_23, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.baselineOffset, 2));
        rgBaos.write(UtilBinaryDecoding.shortToByteArray(rg.reserved26_27, 2));

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

  public List<FNI_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<FNI_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  /**
   * FNI Repeating Group.
   */
  public static class FNI_RepeatingGroup {
    @AFPField
    private String gcgid;
    @AFPField
    private short charIncrement;
    @AFPField
    private short ascenderHeight;
    @AFPField
    private short descenderDepth;
    @AFPField
    private short kernableCharacterFlags;
    @AFPField
    private byte reserved15;
    @AFPField
    private short aSpace;
    @AFPField
    private short bSpace;
    @AFPField
    private short cSpace;
    @AFPField
    private short reserved22_23;
    @AFPField
    private short baselineOffset;
    @AFPField
    private short reserved26_27;

    public String getGcgid() {
      return gcgid;
    }

    public void setGcgid(String gcgid) {
      this.gcgid = gcgid;
    }

    public short getCharIncrement() {
      return charIncrement;
    }

    public void setCharIncrement(short charIncrement) {
      this.charIncrement = charIncrement;
    }

    public short getAscenderHeight() {
      return ascenderHeight;
    }

    public void setAscenderHeight(short ascenderHeight) {
      this.ascenderHeight = ascenderHeight;
    }

    public short getDescenderDepth() {
      return descenderDepth;
    }

    public void setDescenderDepth(short descenderDepth) {
      this.descenderDepth = descenderDepth;
    }

    public short getKernableCharacterFlags() {
      return kernableCharacterFlags;
    }

    public void setKernableCharacterFlags(short kernableCharacterFlags) {
      this.kernableCharacterFlags = kernableCharacterFlags;
    }

    public byte getReserved15() {
      return reserved15;
    }

    public void setReserved15(byte reserved15) {
      this.reserved15 = reserved15;
    }

    public short getASpace() {
      return aSpace;
    }

    public void setASpace(short aSpace) {
      this.aSpace = aSpace;
    }

    public short getBSpace() {
      return bSpace;
    }

    public void setBSpace(short bSpace) {
      this.bSpace = bSpace;
    }

    public short getCSpace() {
      return cSpace;
    }

    public void setCSpace(short cSpace) {
      this.cSpace = cSpace;
    }

    public short getBaselineOffset() {
      return baselineOffset;
    }

    public void setBaselineOffset(short baselineOffset) {
      this.baselineOffset = baselineOffset;
    }
  }
}
