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
package com.mgz.afp.lineData;

import com.mgz.afp.base.StructuredFieldBaseTriplets;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;

/**
 * Programming Guide and Line Data Reference(ha3l3r04.pdf), page 104.<br> <br> The Line Descriptor
 * structured field contains information, such as line position, text orientation, font selection,
 * field selection, and conditional processing identification, used to format line data.
 */
public class LND_LineDescriptor extends StructuredFieldBaseTriplets {
  EnumSet<LND_Flag> flags;
  int inlinePosition;
  int baselinePosition; // Note: IfF relative position, value is signed short, if absolute position unsigned short.
  AFPOrientation inlineOrientation;
  AFPOrientation baselineOrientation;
  short primaryFontLocalId;
  byte channelCode;
  int nextLNDIfSkipping;
  int nextLNDIfSpacing;
  int nextLNDIfReusingData;
  String suppressionTokenName;
  byte shiftOutLocalFontID;
  int dataStartPosition;
  int dataLength;
  AFPColorValue textColor;
  int nextLNDIfConditionalProcessing;
  short subpageID;
  int ccpIdentifier;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    flags = LND_Flag.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
    inlinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
    if (flags.contains(LND_Flag.RelativeBaselinePosition_AbsolutePosition)) {
      baselinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2); // Absolute position: values is unsigned.
    } else {
      baselinePosition = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2); // Relative position: value is signed.
    }
    inlineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 6, 2));
    baselineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 8, 2));
    primaryFontLocalId = UtilBinaryDecoding.parseShort(sfData, offset + 10, 1);
    channelCode = sfData[offset + 11];
    nextLNDIfSkipping = UtilBinaryDecoding.parseInt(sfData, offset + 12, 2);
    nextLNDIfSpacing = UtilBinaryDecoding.parseInt(sfData, offset + 14, 2);
    nextLNDIfReusingData = UtilBinaryDecoding.parseInt(sfData, offset + 16, 2);
    suppressionTokenName = new String(sfData, offset + 18, 8, config.getAfpCharSet());
    shiftOutLocalFontID = sfData[offset + 26];
    dataStartPosition = UtilBinaryDecoding.parseInt(sfData, offset + 27, 4);
    dataLength = UtilBinaryDecoding.parseInt(sfData, offset + 31, 2);
    textColor = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 33, 2));
    nextLNDIfConditionalProcessing = UtilBinaryDecoding.parseInt(sfData, offset + 35, 2);
    subpageID = UtilBinaryDecoding.parseShort(sfData, offset + 37, 1);
    ccpIdentifier = UtilBinaryDecoding.parseInt(sfData, offset + 38, 2);

    if (getActualLength(sfData, offset, length) > 40) {
      super.decodeAFP(sfData, offset + 40, -1, config);
    } else {
      triplets = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(LND_Flag.toBytes(flags));
    baos.write(UtilBinaryDecoding.intToByteArray(inlinePosition, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(baselinePosition, 2)); // Note: IF relative position value is signed short, if absolute position unsigned short.
    baos.write(inlineOrientation.toBytes());
    baos.write(baselineOrientation.toBytes());
    baos.write(primaryFontLocalId);
    baos.write(channelCode);
    baos.write(UtilBinaryDecoding.intToByteArray(nextLNDIfSkipping, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(nextLNDIfSpacing, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(nextLNDIfReusingData, 2));
    baos.write(UtilCharacterEncoding.stringToByteArray(suppressionTokenName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    baos.write(shiftOutLocalFontID);
    baos.write(UtilBinaryDecoding.intToByteArray(dataStartPosition, 4));
    baos.write(UtilBinaryDecoding.intToByteArray(dataLength, 2));
    baos.write(textColor.toByte());
    baos.write(UtilBinaryDecoding.intToByteArray(nextLNDIfConditionalProcessing, 2));
    baos.write(subpageID);
    baos.write(UtilBinaryDecoding.intToByteArray(ccpIdentifier, 2));

    if (triplets != null) for (Triplet t : triplets) t.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public EnumSet<LND_Flag> getFlags() {
    return flags;
  }

  public void setFlags(EnumSet<LND_Flag> flags) {
    this.flags = flags;
  }

  public int getInlinePosition() {
    return inlinePosition;
  }

  public void setInlinePosition(int inlinePosition) {
    this.inlinePosition = inlinePosition;
  }

  public int getBaselinePosition() {
    return baselinePosition;
  }

  public void setBaselinePosition(int baselinePosition) {
    this.baselinePosition = baselinePosition;
  }

  public short getPrimaryFontLocalId() {
    return primaryFontLocalId;
  }

  public void setPrimaryFontLocalId(short primaryFontLocalId) {
    this.primaryFontLocalId = primaryFontLocalId;
  }

  public byte getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(byte channelCode) {
    this.channelCode = channelCode;
  }

  public int getNextLNDIfSkipping() {
    return nextLNDIfSkipping;
  }

  public void setNextLNDIfSkipping(int nextLNDIfSkipping) {
    this.nextLNDIfSkipping = nextLNDIfSkipping;
  }

  public int getNextLNDIfSpacing() {
    return nextLNDIfSpacing;
  }

  public void setNextLNDIfSpacing(int nextLNDIfSpacing) {
    this.nextLNDIfSpacing = nextLNDIfSpacing;
  }

  public int getNextLNDIfReusingData() {
    return nextLNDIfReusingData;
  }

  public void setNextLNDIfReusingData(int nextLNDIfReusingData) {
    this.nextLNDIfReusingData = nextLNDIfReusingData;
  }

  public String getSuppressionTokenName() {
    return suppressionTokenName;
  }

  public void setSuppressionTokenName(String suppressionTokenName) {
    this.suppressionTokenName = suppressionTokenName;
  }

  public byte getShiftOutLocalFontID() {
    return shiftOutLocalFontID;
  }

  public void setShiftOutLocalFontID(byte shiftOutLocalFontID) {
    this.shiftOutLocalFontID = shiftOutLocalFontID;
  }

  public int getDataStartPosition() {
    return dataStartPosition;
  }

  public void setDataStartPosition(int dataStartPosition) {
    this.dataStartPosition = dataStartPosition;
  }

  public int getDataLength() {
    return dataLength;
  }

  public void setDataLength(int dataLength) {
    this.dataLength = dataLength;
  }

  public AFPColorValue getTextColor() {
    return textColor;
  }

  public void setTextColor(AFPColorValue textColor) {
    this.textColor = textColor;
  }

  public int getNextLNDIfConditionalProcessing() {
    return nextLNDIfConditionalProcessing;
  }

  public void setNextLNDIfConditionalProcessing(int nextLNDIfConditionalProcessing) {
    this.nextLNDIfConditionalProcessing = nextLNDIfConditionalProcessing;
  }

  public short getSubpageID() {
    return subpageID;
  }

  public void setSubpageID(short subpageID) {
    this.subpageID = subpageID;
  }

  public int getCcpIdentifier() {
    return ccpIdentifier;
  }

  public void setCcpIdentifier(int ccpIdentifier) {
    this.ccpIdentifier = ccpIdentifier;
  }

  public AFPOrientation getInlineOrientation() {
    return inlineOrientation;
  }

  public void setInlineOrientation(AFPOrientation inlineOrientation) {
    this.inlineOrientation = inlineOrientation;
  }

  public AFPOrientation getBaselineOrientation() {
    return baselineOrientation;
  }

  public void setBaselineOrientation(AFPOrientation baselineOrientation) {
    this.baselineOrientation = baselineOrientation;
  }

  public enum LND_Flag implements IMutualExclusiveGroupedFlag {
    Skipping_CurrentPageDoesNotEnd(0),
    Skipping_CurrentPagesEnds(0),
    Spacing_CurrentPageDoesNotEnd(1),
    Spacing_CurrentPagesEnds(1),
    GenerateInlinePosition_CurrentPosition(2),
    GenerateInlinePosition_NewPosition(2),
    GenerateBaselinePosition_CurrentPosition(3),
    GenerateBaselinePosition_NewPosition(3),
    GenerateFontChange_TRC_MRC_Default(4),
    GenerateFontChange_AsSpecified(4),
    GenerateSuppression_TextIsNotSuppressible(5),
    GenerateSuppression_TextIsSuppressible(5),
    ReuseRecord_DoNotReuse(6),
    ReuseRecord_DoReuse(6),
    UseFixedData_DoNotPresent(7),
    UseFixedData_DoPresent(7),
    UseCompatibilityTRC_DoNotUse(8),
    UseCompatibilityTRC_DoUse(8),
    SetTextColor_DefaultColor(9),
    SetTextColor_AsSpecified(9),
    ConditionalProcessing_DoNotPerformCP(10),
    ConditionalProcessing_DoPerformCP(10),
    ResourceObjectInclude_DoNotIncludeResource(12),
    ResourceObjectInclude_DoIncludeResource(12),
    RelativeBaselinePosition_AbsolutePosition(13),
    RelativeBaselinePosition_RelativePosition(13),;
    private static MutualExclusiveGroupedFlagHandler<LND_Flag> handler = new MutualExclusiveGroupedFlagHandler<LND_Flag>();
    int group;

    LND_Flag(int group) {
      this.group = group;
    }

    public static EnumSet<LND_Flag> valueOf(int flagBytes) {
      EnumSet<LND_Flag> result = EnumSet.noneOf(LND_Flag.class);

      if ((flagBytes & 0x8000) != 0) result.add(Skipping_CurrentPagesEnds);
      else result.add(Skipping_CurrentPageDoesNotEnd);
      if ((flagBytes & 0x4000) != 0) result.add(Spacing_CurrentPagesEnds);
      else result.add(Spacing_CurrentPageDoesNotEnd);
      if ((flagBytes & 0x2000) != 0) result.add(GenerateInlinePosition_NewPosition);
      else result.add(GenerateInlinePosition_CurrentPosition);
      if ((flagBytes & 0x1000) != 0) result.add(GenerateBaselinePosition_NewPosition);
      else result.add(GenerateBaselinePosition_CurrentPosition);
      if ((flagBytes & 0x0800) != 0) result.add(GenerateFontChange_AsSpecified);
      else result.add(GenerateFontChange_TRC_MRC_Default);
      if ((flagBytes & 0x0400) != 0) result.add(GenerateSuppression_TextIsSuppressible);
      else result.add(GenerateSuppression_TextIsNotSuppressible);
      if ((flagBytes & 0x0200) != 0) result.add(ReuseRecord_DoReuse);
      else result.add(ReuseRecord_DoNotReuse);
      if ((flagBytes & 0x0100) != 0) result.add(UseFixedData_DoPresent);
      else result.add(UseFixedData_DoNotPresent);
      // 0x0080 is not used.
      if ((flagBytes & 0x0040) != 0) result.add(UseCompatibilityTRC_DoUse);
      else result.add(UseCompatibilityTRC_DoNotUse);
      if ((flagBytes & 0x0020) != 0) result.add(SetTextColor_AsSpecified);
      else result.add(SetTextColor_DefaultColor);
      if ((flagBytes & 0x0010) != 0) result.add(ConditionalProcessing_DoPerformCP);
      else result.add(ConditionalProcessing_DoNotPerformCP);
      if ((flagBytes & 0x0008) != 0) result.add(ResourceObjectInclude_DoIncludeResource);
      else result.add(ResourceObjectInclude_DoNotIncludeResource);
      if ((flagBytes & 0x0004) != 0) result.add(RelativeBaselinePosition_RelativePosition);
      else result.add(RelativeBaselinePosition_AbsolutePosition);

      return result;
    }

    public static byte[] toBytes(EnumSet<LND_Flag> flags) {
      int result = 0;

      if (flags.contains(Skipping_CurrentPagesEnds)) result |= 0x8000;
      if (flags.contains(Spacing_CurrentPagesEnds)) result |= 0x4000;
      if (flags.contains(GenerateInlinePosition_NewPosition)) result |= 0x2000;
      if (flags.contains(GenerateBaselinePosition_NewPosition)) result |= 0x1000;
      if (flags.contains(GenerateFontChange_AsSpecified)) result |= 0x0800;
      if (flags.contains(GenerateSuppression_TextIsSuppressible)) result |= 0x0400;
      if (flags.contains(ReuseRecord_DoReuse)) result |= 0x0200;
      if (flags.contains(UseFixedData_DoPresent)) result |= 0x0100;
      // 0x0080 is not used.
      if (flags.contains(UseCompatibilityTRC_DoUse)) result |= 0x0040;
      if (flags.contains(SetTextColor_AsSpecified)) result |= 0x0020;
      if (flags.contains(ConditionalProcessing_DoPerformCP)) result |= 0x0010;
      if (flags.contains(ResourceObjectInclude_DoIncludeResource)) result |= 0x0008;
      if (flags.contains(RelativeBaselinePosition_RelativePosition)) result |= 0x0004;

      return UtilBinaryDecoding.intToByteArray(result, 2);
    }

    public static void setFlag(EnumSet<LND_Flag> flags, LND_Flag flag) {
      handler.setFlag(flags, flag);
    }

    @Override
    public int getGroup() {
      return group;
    }
  }
}
