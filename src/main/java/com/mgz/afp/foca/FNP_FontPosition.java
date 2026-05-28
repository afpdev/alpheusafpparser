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
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Font Position (FNP).
 */
public class FNP_FontPosition extends StructuredField {
  @AFPField
  private List<FNP_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnpRepeatingGroupLength(FNC_FontControl.FNP_REPEATING_GROUP_LENGTH_DEFAULT);
    }
    var rgLen = fnc.getFnpRepeatingGroupLength() & 0xFF;

    var actualLength = getActualLength(sfData, offset, length);
    repeatingGroups = new ArrayList<>();

    var pos = 0;
    while (pos + rgLen <= actualLength) {
      var rg = new FNP_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, rgLen, config);
      repeatingGroups.add(rg);
      pos += rgLen;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnpRepeatingGroupLength(FNC_FontControl.FNP_REPEATING_GROUP_LENGTH_DEFAULT);
    }
    var rgLen = fnc.getFnpRepeatingGroupLength() & 0xFF;

    var baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        var rgBaos = new ByteArrayOutputStream();
        rg.writeAFP(rgBaos, config);
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

  public List<FNP_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<FNP_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  public void addFNPRepeatingGroup(FNP_RepeatingGroup rg) {
    if (rg == null) {
      return;
    }
    if (this.repeatingGroups == null) {
      this.repeatingGroups = new ArrayList<>();
    }
    repeatingGroups.add(rg);
  }

  public void removeRepeatingGroup(FNP_RepeatingGroup rg) {
    if (this.repeatingGroups == null) {
      return;
    }
    this.repeatingGroups.remove(rg);
  }

  /**
   * FNP Repeating Group.
   */
  @XmlRootElement
  public static class FNP_RepeatingGroup implements IAFPDecodeableWriteable {
    @AFPField(size = 2)
    private byte[] reserved0_1 = new byte[] {0x00, 0x00};
    @AFPField
    private short lowercaseHeight;
    @AFPField
    private short capMHeight;
    @AFPField
    private short maxAscenderHeight;
    @AFPField
    private short maxDescenderDepth;
    @AFPField(size = 5)
    private byte[] reserved10_14 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00};
    @AFPField
    private byte retired15 = 0x01;
    @AFPField
    private byte reserved16 = 0x00;
    @AFPField
    private short underscoreWidth;
    @AFPField
    private byte underscoreWidthFraction = 0x00;
    @AFPField
    private short underscorePosition;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, 22);
      reserved0_1 = new byte[2];
      System.arraycopy(sfData, offset, reserved0_1, 0, reserved0_1.length);
      lowercaseHeight = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      capMHeight = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      maxAscenderHeight = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
      maxDescenderDepth = UtilBinaryDecoding.parseShort(sfData, offset + 8, 2);
      reserved10_14 = new byte[5];
      System.arraycopy(sfData, offset + 10, reserved10_14, 0, reserved10_14.length);
      retired15 = sfData[offset + 15];
      reserved16 = sfData[offset + 16];
      underscoreWidth = UtilBinaryDecoding.parseShort(sfData, offset + 17, 2);
      underscoreWidthFraction = sfData[offset + 19];
      underscorePosition = UtilBinaryDecoding.parseShort(sfData, offset + 20, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(reserved0_1);
      os.write(UtilBinaryDecoding.shortToByteArray(lowercaseHeight, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(capMHeight, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(maxAscenderHeight, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(maxDescenderDepth, 2));
      os.write(reserved10_14);
      os.write(retired15);
      os.write(reserved16);
      os.write(UtilBinaryDecoding.shortToByteArray(underscoreWidth, 2));
      os.write(underscoreWidthFraction);
      os.write(UtilBinaryDecoding.shortToByteArray(underscorePosition, 2));
    }

    public byte[] getReserved0_1() {
      return reserved0_1;
    }

    public void setReserved0_1(byte[] reserved0_1) {
      this.reserved0_1 = reserved0_1;
    }

    public short getLowercaseHeight() {
      return lowercaseHeight;
    }

    public void setLowercaseHeight(short lowercaseHeight) {
      this.lowercaseHeight = lowercaseHeight;
    }

    public short getCapMHeight() {
      return capMHeight;
    }

    public void setCapMHeight(short capMHeight) {
      this.capMHeight = capMHeight;
    }

    public short getMaxAscenderHeight() {
      return maxAscenderHeight;
    }

    public void setMaxAscenderHeight(short maxAscenderHeight) {
      this.maxAscenderHeight = maxAscenderHeight;
    }

    public short getMaxDescenderDepth() {
      return maxDescenderDepth;
    }

    public void setMaxDescenderDepth(short maxDescenderDepth) {
      this.maxDescenderDepth = maxDescenderDepth;
    }

    public byte[] getReserved10_14() {
      return reserved10_14;
    }

    public void setReserved10_14(byte[] reserved10_14) {
      this.reserved10_14 = reserved10_14;
    }

    public byte getRetired15() {
      return retired15;
    }

    public void setRetired15(byte retired15) {
      this.retired15 = retired15;
    }

    public byte getReserved16() {
      return reserved16;
    }

    public void setReserved16(byte reserved16) {
      this.reserved16 = reserved16;
    }

    public short getUnderscoreWidth() {
      return underscoreWidth;
    }

    public void setUnderscoreWidth(short underscoreWidth) {
      this.underscoreWidth = underscoreWidth;
    }

    public byte getUnderscoreWidthFraction() {
      return underscoreWidthFraction;
    }

    public void setUnderscoreWidthFraction(byte underscoreWidthFraction) {
      this.underscoreWidthFraction = underscoreWidthFraction;
    }

    public short getUnderscorePosition() {
      return underscorePosition;
    }

    public void setUnderscorePosition(short underscorePosition) {
      this.underscorePosition = underscorePosition;
    }

  }
}
