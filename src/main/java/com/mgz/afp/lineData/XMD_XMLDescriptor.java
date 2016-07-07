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
import com.mgz.afp.enums.AFPOrientation;
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

public class XMD_XMLDescriptor extends StructuredFieldBaseTriplets {
  RCD_XMD_RecordTypeElementType elementType;
  EnumSet<XMD_Flag> flags;
  byte reserved4 = 0x00;
  int inlinePosition;
  int baselinePosition; // Note: If relative position, value is signed short, if absolute position unsigned short.
  AFPOrientation inlineOrientation;
  AFPOrientation baselineOrientation;
  short primaryFontLocalId;
  int fieldXMDPointer;
  byte[] reserved16_17 = new byte[2];
  String suppressionTokenName;
  byte reserved26 = 0x00;
  int dataStartPosition;
  int dataLength;
  int conditionalProcessingRCDPointer;
  byte subpageID;
  int ccpIdentifier;
  int startingPageNumber;
  int endSpace;
  byte fieldAllignment;
  int fieldDelimiter;
  int fieldNumber;
  int additionalBaselineIncrement;
  byte[] reserved49_61 = new byte[13];

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    elementType = RCD_XMD_RecordTypeElementType.valuesOf(sfData[offset]);
    flags = XMD_Flag.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 1, 3));
    reserved4 = sfData[offset + 4];
    inlinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 5, 2);
    baselinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 7, 2);
    inlineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 9, 2));
    baselineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 11, 2));
    primaryFontLocalId = UtilBinaryDecoding.parseShort(sfData, offset + 13, 1);
    fieldXMDPointer = UtilBinaryDecoding.parseInt(sfData, offset + 14, 2);
    reserved16_17 = new byte[2];
    System.arraycopy(sfData, offset + 16, reserved16_17, 0, reserved16_17.length);
    suppressionTokenName = new String(sfData, offset + 18, 8, config.getAfpCharSet());
    reserved26 = sfData[offset + 26];
    dataStartPosition = UtilBinaryDecoding.parseInt(sfData, offset + 27, 3);
    dataLength = UtilBinaryDecoding.parseInt(sfData, offset + 31, 2);
    conditionalProcessingRCDPointer = UtilBinaryDecoding.parseInt(sfData, offset + 33, 2);
    subpageID = sfData[offset + 35];
    ccpIdentifier = UtilBinaryDecoding.parseInt(sfData, offset + 36, 2);
    startingPageNumber = UtilBinaryDecoding.parseInt(sfData, offset + 38, 2);
    endSpace = UtilBinaryDecoding.parseInt(sfData, offset + 40, 2);
    fieldAllignment = sfData[offset + 42];
    fieldDelimiter = UtilBinaryDecoding.parseInt(sfData, offset + 43, 2);
    fieldNumber = UtilBinaryDecoding.parseInt(sfData, offset + 45, 2);
    additionalBaselineIncrement = UtilBinaryDecoding.parseInt(sfData, offset + 47, 2);
    reserved49_61 = new byte[13];
    System.arraycopy(sfData, offset + 49, reserved49_61, 0, reserved49_61.length);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 63) {
      super.decodeAFP(sfData, offset, actualLength, config);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(elementType.toByte());
    baos.write(XMD_Flag.toBytes(flags));
    baos.write(reserved4);
    baos.write(UtilBinaryDecoding.intToByteArray(inlinePosition, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(baselinePosition, 2));
    baos.write(inlineOrientation.toBytes());
    baos.write(baselineOrientation.toBytes());
    baos.write(UtilBinaryDecoding.shortToByteArray(primaryFontLocalId, 1));
    baos.write(UtilBinaryDecoding.intToByteArray(fieldXMDPointer, 2));
    baos.write(reserved16_17);
    baos.write(UtilCharacterEncoding.stringToByteArray(suppressionTokenName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    baos.write(reserved26);
    baos.write(UtilBinaryDecoding.intToByteArray(dataStartPosition, 3));
    baos.write(UtilBinaryDecoding.intToByteArray(dataLength, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(conditionalProcessingRCDPointer, 2));
    baos.write(subpageID);
    baos.write(UtilBinaryDecoding.intToByteArray(ccpIdentifier, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(startingPageNumber, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(endSpace, 2));
    baos.write(fieldAllignment);
    baos.write(UtilBinaryDecoding.intToByteArray(fieldDelimiter, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(fieldNumber, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(additionalBaselineIncrement, 2));
    baos.write(reserved49_61);
    if (triplets != null) for (Triplet t : triplets) t.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public RCD_XMD_RecordTypeElementType getElementType() {
    return elementType;
  }

  public void setElementType(RCD_XMD_RecordTypeElementType elementType) {
    this.elementType = elementType;
  }

  public EnumSet<XMD_Flag> getFlags() {
    return flags;
  }

  public void setFlags(EnumSet<XMD_Flag> flags) {
    this.flags = flags;
  }

  public byte getReserved4() {
    return reserved4;
  }

  public void setReserved4(byte reserved4) {
    this.reserved4 = reserved4;
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

  public short getPrimaryFontLocalId() {
    return primaryFontLocalId;
  }

  public void setPrimaryFontLocalId(short primaryFontLocalId) {
    this.primaryFontLocalId = primaryFontLocalId;
  }

  public int getFieldXMDPointer() {
    return fieldXMDPointer;
  }

  public void setFieldXMDPointer(int fieldXMDPointer) {
    this.fieldXMDPointer = fieldXMDPointer;
  }

  public byte[] getReserved16_17() {
    return reserved16_17;
  }

  public void setReserved16_17(byte[] reserved16_17) {
    this.reserved16_17 = reserved16_17;
  }

  public String getSuppressionTokenName() {
    return suppressionTokenName;
  }

  public void setSuppressionTokenName(String suppressionTokenName) {
    this.suppressionTokenName = suppressionTokenName;
  }

  public byte getReserved26() {
    return reserved26;
  }

  public void setReserved26(byte reserved26) {
    this.reserved26 = reserved26;
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

  public int getConditionalProcessingRCDPointer() {
    return conditionalProcessingRCDPointer;
  }

  public void setConditionalProcessingRCDPointer(
          int conditionalProcessingRCDPointer) {
    this.conditionalProcessingRCDPointer = conditionalProcessingRCDPointer;
  }

  public byte getSubpageID() {
    return subpageID;
  }

  public void setSubpageID(byte subpageID) {
    this.subpageID = subpageID;
  }

  public int getCcpIdentifier() {
    return ccpIdentifier;
  }

  public void setCcpIdentifier(int ccpIdentifier) {
    this.ccpIdentifier = ccpIdentifier;
  }

  public int getStartingPageNumber() {
    return startingPageNumber;
  }

  public void setStartingPageNumber(int startingPageNumber) {
    this.startingPageNumber = startingPageNumber;
  }

  public int getEndSpace() {
    return endSpace;
  }

  public void setEndSpace(int endSpace) {
    this.endSpace = endSpace;
  }

  public byte getFieldAllignment() {
    return fieldAllignment;
  }

  public void setFieldAllignment(byte fieldAllignment) {
    this.fieldAllignment = fieldAllignment;
  }

  public int getFieldDelimiter() {
    return fieldDelimiter;
  }

  public void setFieldDelimiter(int fieldDelimiter) {
    this.fieldDelimiter = fieldDelimiter;
  }

  public int getFieldNumber() {
    return fieldNumber;
  }

  public void setFieldNumber(int fieldNumber) {
    this.fieldNumber = fieldNumber;
  }

  public int getAdditionalBaselineIncrement() {
    return additionalBaselineIncrement;
  }

  public void setAdditionalBaselineIncrement(int additionalBaselineIncrement) {
    this.additionalBaselineIncrement = additionalBaselineIncrement;
  }

  public byte[] getReserved49_61() {
    return reserved49_61;
  }

  public void setReserved49_61(byte[] reserved49_61) {
    this.reserved49_61 = reserved49_61;
  }

  public static enum XMD_Flag {
    // (0) 0x800000 reserved.
    // (1) 0x400000 reserved.
    GenerateInlinePosition_CurrentPosition(2),
    GenerateInlinePosition_NewPosition(2),
    GenerateBaselinePosition_CurrentPosition(3),
    GenerateBaselinePosition_NewPosition(3),
    GenerateFontChange_TRC_MRC_Default(4),
    GenerateFontChange_AsSpecified(4),
    GenerateSuppression_TextIsNotSuppressible(5),
    GenerateSuppression_TextIsSuppressible(5),
    FieldXMD_Element(6),
    FieldXMD_Field(6),
    UseFixedData_DoNotPresent(7),
    UseFixedData_DoPresent(7),
    // (8)	reserved.
    // (9)	reserved.
    AttributeXMD_Element(10),
    AttributeXMD_Attribute(10),
    ConditionalProcessing_DoNotPerformCP(11),
    ConditionalProcessing_DoPerformCP(11),
    // (12)	reserved.
    RelativeBaselinePosition_AbsolutePosition(13),
    RelativeBaselinePosition_RelativePosition(13),
    // (14)	reserved.
    // (15)	reserved.
    NewPage_NoEffect(16),
    NewPage_LogicalPageEject(16),
    PrintPageNumber_NoEffect(17),
    PrintPageNumber_PrintPageNumber(17),
    ResetPageNumber_NoEffect(18),
    ResetPageNumber_ResetPageNumberToSpecifiedValue(18),
    GroupIndicator_InputDataIsNotPartOfAGroup(19),
    GroupIndicator_SaveGroupHeader(19),
    FieldDelimeterSize_1Byte(20),
    FieldDelimeterSize_2Bytes(20),
    UseStartTag_DoNotSelectStartTag(21),
    UseStartTag_DoSelectStartTag(21),
    // (22)	reserved.
    HaiderTrailerContinued_IsNotAContinuationOfHeaderTrailer(23),
    HaiderTrailerContinued_IsAContinuationOfHeaderTrailer(23);

    private static MutualExclusiveGroupedFlagHandler<XMD_Flag> handler = new MutualExclusiveGroupedFlagHandler<XMD_Flag>();
    int group;

    XMD_Flag(int group) {
      this.group = group;
    }

    public static EnumSet<XMD_Flag> valueOf(int flagBytes) {
      EnumSet<XMD_Flag> result = EnumSet.noneOf(XMD_Flag.class);

      // (0) 0x800000 reserved.
      // (1) 0x400000 reserved.
      if ((flagBytes & 0x200000) != 0) result.add(GenerateInlinePosition_NewPosition);
      else result.add(GenerateInlinePosition_CurrentPosition);
      if ((flagBytes & 0x100000) != 0) result.add(GenerateBaselinePosition_NewPosition);
      else result.add(GenerateBaselinePosition_CurrentPosition);
      if ((flagBytes & 0x080000) != 0) result.add(GenerateFontChange_AsSpecified);
      else result.add(GenerateFontChange_TRC_MRC_Default);
      if ((flagBytes & 0x040000) != 0) result.add(GenerateSuppression_TextIsSuppressible);
      else result.add(GenerateSuppression_TextIsNotSuppressible);
      if ((flagBytes & 0x020000) != 0) result.add(FieldXMD_Field);
      else result.add(FieldXMD_Element);
      if ((flagBytes & 0x010000) != 0) result.add(UseFixedData_DoPresent);
      else result.add(UseFixedData_DoNotPresent);
      // 0x008000 is not used.
      // 0x004000 is not used.
      if ((flagBytes & 0x002000) != 0) result.add(AttributeXMD_Attribute);
      else result.add(AttributeXMD_Element);
      if ((flagBytes & 0x001000) != 0) result.add(ConditionalProcessing_DoPerformCP);
      else result.add(ConditionalProcessing_DoNotPerformCP);
      // 0x000800 is not used.
      if ((flagBytes & 0x000400) != 0) result.add(RelativeBaselinePosition_RelativePosition);
      else result.add(RelativeBaselinePosition_AbsolutePosition);
      // 0x000200 is not used.
      // 0x000100 is not used.
      if ((flagBytes & 0x000080) != 0) result.add(NewPage_LogicalPageEject);
      else result.add(NewPage_NoEffect);
      if ((flagBytes & 0x000040) != 0) result.add(PrintPageNumber_PrintPageNumber);
      else result.add(PrintPageNumber_NoEffect);
      if ((flagBytes & 0x000020) != 0)
        result.add(ResetPageNumber_ResetPageNumberToSpecifiedValue);
      else result.add(ResetPageNumber_NoEffect);
      if ((flagBytes & 0x000010) != 0) result.add(GroupIndicator_SaveGroupHeader);
      else result.add(GroupIndicator_InputDataIsNotPartOfAGroup);
      if ((flagBytes & 0x000008) != 0) result.add(FieldDelimeterSize_2Bytes);
      else result.add(FieldDelimeterSize_1Byte);
      if ((flagBytes & 0x000004) != 0) result.add(UseStartTag_DoSelectStartTag);
      else result.add(UseStartTag_DoNotSelectStartTag);
      // 0x000002 is not used.
      if ((flagBytes & 0x000001) != 0)
        result.add(HaiderTrailerContinued_IsAContinuationOfHeaderTrailer);
      else result.add(HaiderTrailerContinued_IsNotAContinuationOfHeaderTrailer);

      return result;
    }

    public static byte[] toBytes(EnumSet<XMD_Flag> flags) {
      int result = 0;

      // 0x800000 is not used.
      // 0x400000 is not used.
      if (flags.contains(GenerateInlinePosition_NewPosition)) result |= 0x200000;
      if (flags.contains(GenerateBaselinePosition_NewPosition)) result |= 0x100000;
      if (flags.contains(GenerateFontChange_AsSpecified)) result |= 0x080000;
      if (flags.contains(GenerateSuppression_TextIsSuppressible)) result |= 0x040000;
      if (flags.contains(FieldXMD_Field)) result |= 0x020000;
      if (flags.contains(UseFixedData_DoPresent)) result |= 0x010000;
      // 0x008000 is not used.
      // 0x004000 is not used.
      if (flags.contains(AttributeXMD_Attribute)) result |= 0x002000;
      if (flags.contains(ConditionalProcessing_DoPerformCP)) result |= 0x001000;
      // 0x000800 is not used.
      if (flags.contains(RelativeBaselinePosition_RelativePosition)) result |= 0x000400;
      // 0x000200 is not used.
      // 0x000100 is not used.
      if (flags.contains(NewPage_LogicalPageEject)) result |= 0x000080;
      if (flags.contains(PrintPageNumber_PrintPageNumber)) result |= 0x000040;
      if (flags.contains(ResetPageNumber_ResetPageNumberToSpecifiedValue)) result |= 0x000020;
      if (flags.contains(GroupIndicator_SaveGroupHeader)) result |= 0x000010;
      if (flags.contains(FieldDelimeterSize_2Bytes)) result |= 0x000008;
      if (flags.contains(UseStartTag_DoSelectStartTag)) result |= 0x000004;
      // 0x000002 is not used.
      if (flags.contains(HaiderTrailerContinued_IsAContinuationOfHeaderTrailer))
        result |= 0x000001;

      return UtilBinaryDecoding.intToByteArray(result, 3);
    }

    public static void setFlag(EnumSet<XMD_Flag> flags, XMD_Flag flag) {
      handler.setFlag(flags, flag);
    }
  }
}
