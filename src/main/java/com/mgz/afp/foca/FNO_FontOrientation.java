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
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FNO_FontOrientation extends StructuredField {
  List<FNO_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, FNO_RepeatingGroup.RGLENGTH);
    int actualLength = length != -1 ? length : sfData.length - offset;

    repeatingGroups = new ArrayList<FNO_FontOrientation.FNO_RepeatingGroup>(4);

    int pos = 0;
    while (pos < actualLength) {
      FNO_RepeatingGroup rg = new FNO_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, FNO_RepeatingGroup.RGLENGTH, config);
      repeatingGroups.add(rg);
      pos += FNO_RepeatingGroup.RGLENGTH;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(FNC_FontControl.FNORepeatingGroupLength * repeatingGroups.size());

    for (FNO_RepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<FNO_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<FNO_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  public enum FNO_ControlFlag implements IMutualExclusiveGroupedFlag {
    FontIndex_Number0(0),
    FontIndex_Number1(0),
    FontIndex_Number2(0),
    FontIndex_Number3(0),
    FontIndex_Number4(0),
    FontIndex_Number5(0),
    FontIndex_Number6(0),
    FontIndex_Number7(0),
    KerningData_NoKerningData(1),
    KerningData_WithKerningData(1),
    ASpace_Minimum(2),
    ASpace_Uniform(2),
    BaselineOffset_Maximum(3),
    BaselineOffset_Uniform(3),
    CharacterIncrement_Maximum(4),
    CharacterIncrement_Uniform(4);

    int group;

    FNO_ControlFlag(int group) {
      this.group = group;
    }

    public static EnumSet<FNO_ControlFlag> valueOf(byte controlFlagByte) {
      EnumSet<FNO_ControlFlag> result = EnumSet.noneOf(FNO_ControlFlag.class);

      int fontIndexNumber = controlFlagByte >>> 5;
      if (fontIndexNumber == 0) result.add(FontIndex_Number0);
      else if (fontIndexNumber == 1) result.add(FontIndex_Number1);
      else if (fontIndexNumber == 2) result.add(FontIndex_Number2);
      else if (fontIndexNumber == 3) result.add(FontIndex_Number3);
      else if (fontIndexNumber == 4) result.add(FontIndex_Number4);
      else if (fontIndexNumber == 5) result.add(FontIndex_Number5);
      else if (fontIndexNumber == 6) result.add(FontIndex_Number6);
      else if (fontIndexNumber == 7) result.add(FontIndex_Number7);

      if ((controlFlagByte & 0x08) == 0) result.add(KerningData_NoKerningData);
      else result.add(KerningData_WithKerningData);

      if ((controlFlagByte & 0x04) == 0) result.add(ASpace_Minimum);
      else result.add(ASpace_Uniform);

      if ((controlFlagByte & 0x02) == 0) result.add(BaselineOffset_Maximum);
      else result.add(BaselineOffset_Uniform);

      if ((controlFlagByte & 0x01) == 0) result.add(CharacterIncrement_Maximum);
      else result.add(CharacterIncrement_Uniform);

      return result;
    }

    public static int toByte(EnumSet<FNO_ControlFlag> controlFlags) {
      int result = 0;
      if (controlFlags.contains(FontIndex_Number1)) result = 1;
      else if (controlFlags.contains(FontIndex_Number2)) result = 2;
      else if (controlFlags.contains(FontIndex_Number3)) result = 3;
      else if (controlFlags.contains(FontIndex_Number4)) result = 4;
      else if (controlFlags.contains(FontIndex_Number5)) result = 5;
      else if (controlFlags.contains(FontIndex_Number6)) result = 6;
      else if (controlFlags.contains(FontIndex_Number7)) result = 7;
      result = result << 5;

      if (controlFlags.contains(KerningData_WithKerningData)) result |= 0x08;
      if (controlFlags.contains(ASpace_Uniform)) result |= 0x04;
      if (controlFlags.contains(BaselineOffset_Uniform)) result |= 0x02;
      if (controlFlags.contains(CharacterIncrement_Uniform)) result |= 0x01;

      return result;
    }

    public static void setFlag(EnumSet<FNO_ControlFlag> set, FNO_ControlFlag flag) {
      new MutualExclusiveGroupedFlagHandler<FNO_ControlFlag>().setFlag(set, flag);
    }

    @Override
    public int getGroup() {
      return group;
    }
  }

  public static class FNO_RepeatingGroup implements IAFPDecodeableWriteable {
    public static final int RGLENGTH = 26;
    @AFPField(size = 2)
    public byte[] reserved0_1 = new byte[]{0x00, 0x0};
    @AFPField
    public byte reserved13 = 0x00;
    @AFPField(size = 2)
    public byte[] reserved16_17 = new byte[]{0x00, 0x0};
    @AFPField
    AFPOrientation characterRotation;
    @AFPField
    short maxBaselineOffset;
    @AFPField
    short maxCharacterIncrement;
    @AFPField
    short spaceCharacterIncrement;
    @AFPField
    short maxBaselineExtent;
    @AFPField
    EnumSet<FNO_ControlFlag> controlFlags;
    @AFPField
    short emSpaceIncrement;
    @AFPField
    short figureSpaceIncrement;
    @AFPField
    short nominalCharacterIncrement;
    @AFPField
    int defaultBaselineIncrement;
    @AFPField
    short minASpace;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, RGLENGTH);
      reserved0_1 = new byte[]{sfData[offset], sfData[offset + 1]};
      characterRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 2, 2));
      maxBaselineOffset = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      maxCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
      spaceCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 8, 2);
      maxBaselineExtent = UtilBinaryDecoding.parseShort(sfData, offset + 10, 2);
      controlFlags = FNO_ControlFlag.valueOf(sfData[offset + 12]);
      reserved13 = sfData[offset + 13];
      emSpaceIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 14, 2);
      reserved16_17 = new byte[]{sfData[offset + 16], sfData[offset + 17]};
      figureSpaceIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 18, 2);
      nominalCharacterIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 20, 2);
      defaultBaselineIncrement = UtilBinaryDecoding.parseInt(sfData, offset + 22, 2);
      minASpace = UtilBinaryDecoding.parseShort(sfData, offset + 24, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(reserved0_1);
      os.write(characterRotation.toBytes());
      os.write(UtilBinaryDecoding.shortToByteArray(maxBaselineOffset, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(maxCharacterIncrement, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(spaceCharacterIncrement, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(maxBaselineExtent, 2));
      os.write(FNO_ControlFlag.toByte(controlFlags));
      os.write(reserved13);
      os.write(UtilBinaryDecoding.shortToByteArray(emSpaceIncrement, 2));
      os.write(reserved16_17);
      os.write(UtilBinaryDecoding.shortToByteArray(figureSpaceIncrement, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(nominalCharacterIncrement, 2));
      os.write(UtilBinaryDecoding.intToByteArray(defaultBaselineIncrement, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(minASpace, 2));
    }

    public AFPOrientation getCharacterRotation() {
      return characterRotation;
    }

    public void setCharacterRotation(AFPOrientation characterRotation) {
      this.characterRotation = characterRotation;
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

    public EnumSet<FNO_ControlFlag> getControlFlags() {
      return controlFlags;
    }

    /**
     * Sets the given {@link FNO_ControlFlag} and un-sets mutual exclusive flags.
     */
    public void setControlFlag(FNO_ControlFlag controlFlag) {
      if (controlFlags == null) {
        controlFlags = EnumSet.noneOf(FNO_ControlFlag.class);
      }
      FNO_ControlFlag.setFlag(controlFlags, controlFlag);
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
