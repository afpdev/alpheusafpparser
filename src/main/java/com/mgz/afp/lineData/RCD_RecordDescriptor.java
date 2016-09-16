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

/**
 * *  Programming Guide and Line Data Reference(ha3l3r04.pdf), page 127.<br> <br> The Record
 * Descriptor structured field contains information, such as record position, text orientation, font
 * selection, field selection, and conditional processing identification, used to format line data
 * that consists of records tagged with record identifiers.
 */
public class RCD_RecordDescriptor extends StructuredFieldBaseTriplets {
  String recordDescriptorID;
  RCD_XMD_RecordTypeElementType recordType;
  EnumSet<RCD_Flag> flags;
  byte reserved14 = 0x00;
  int inlinePosition;
  int baselinePosition; // Note: IfF relative position, value is signed short, if absolute position unsigned short.
  AFPOrientation inlineOrientation;
  AFPOrientation baselineOrientation;
  short primaryFontLocalId;
  int fieldRCDPointer;
  String suppressionTokenName;
  short shiftOutFontLocalID;
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
  byte[] reserved57_69 = new byte[13];

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    recordDescriptorID = new String(sfData, offset, 10, config.getAfpCharSet());
    recordType = RCD_XMD_RecordTypeElementType.valuesOf(sfData[offset + 10]);
    flags = RCD_Flag.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 11, 3));
    reserved14 = sfData[offset + 14];
    inlinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 15, 2);
    if (flags.contains(RCD_Flag.RelativeBaselinePosition_AbsolutePosition)) {
      baselinePosition = UtilBinaryDecoding.parseInt(sfData, offset + 17, 2);
    } else {
      baselinePosition = UtilBinaryDecoding.parseShort(sfData, offset + 17, 2);
    }
    inlineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 19, 2));
    baselineOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 21, 2));
    primaryFontLocalId = UtilBinaryDecoding.parseShort(sfData, offset + 23, 1);
    fieldRCDPointer = UtilBinaryDecoding.parseInt(sfData, offset + 24, 2);
    suppressionTokenName = new String(sfData, offset + 26, 8, config.getAfpCharSet());
    shiftOutFontLocalID = UtilBinaryDecoding.parseShort(sfData, offset + 34, 1);
    dataStartPosition = UtilBinaryDecoding.parseInt(sfData, offset + 35, 4);
    dataLength = UtilBinaryDecoding.parseInt(sfData, offset + 39, 2);
    conditionalProcessingRCDPointer = UtilBinaryDecoding.parseInt(sfData, offset + 41, 2);
    subpageID = sfData[offset + 43];
    ccpIdentifier = UtilBinaryDecoding.parseInt(sfData, offset + 44, 2);
    startingPageNumber = UtilBinaryDecoding.parseInt(sfData, offset + 46, 2);
    endSpace = UtilBinaryDecoding.parseInt(sfData, offset + 48, 2);
    fieldAllignment = sfData[offset + 50];
    fieldDelimiter = UtilBinaryDecoding.parseInt(sfData, offset + 51, 2);
    fieldNumber = UtilBinaryDecoding.parseInt(sfData, offset + 53, 2);
    additionalBaselineIncrement = UtilBinaryDecoding.parseInt(sfData, offset + 55, 2);
    reserved57_69 = new byte[13];
    System.arraycopy(sfData, offset + 48, reserved57_69, 0, reserved57_69.length);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 71) {
      super.decodeAFP(sfData, offset + 70, actualLength - 70, config);
    } else {
      this.setTriplets(null);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(recordDescriptorID, config.getAfpCharSet(), 10, Constants.EBCDIC_ID_FILLER));
    baos.write(recordType.toByte());
    baos.write(RCD_Flag.toBytes(flags));
    baos.write(reserved14);
    baos.write(UtilBinaryDecoding.intToByteArray(inlinePosition, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(baselinePosition, 2));
    baos.write(inlineOrientation.toBytes());
    baos.write(baselineOrientation.toBytes());
    baos.write(primaryFontLocalId);
    baos.write(UtilBinaryDecoding.intToByteArray(fieldRCDPointer, 2));
    baos.write(UtilCharacterEncoding.stringToByteArray(suppressionTokenName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    baos.write(shiftOutFontLocalID);
    baos.write(UtilBinaryDecoding.intToByteArray(dataStartPosition, 4));
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
    baos.write(reserved57_69);
    if (this.getTriplets() != null) for (Triplet t : this.getTriplets()) t.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getRecordDescriptorID() {
    return recordDescriptorID;
  }

  public void setRecordDescriptorID(String recordDescriptorID) {
    this.recordDescriptorID = recordDescriptorID;
  }

  public RCD_XMD_RecordTypeElementType getRecordType() {
    return recordType;
  }

  public void setRecordType(RCD_XMD_RecordTypeElementType recordType) {
    this.recordType = recordType;
  }

  public EnumSet<RCD_Flag> getFlags() {
    return flags;
  }

  public void setFlags(EnumSet<RCD_Flag> flags) {
    this.flags = flags;
  }

  public byte getReserved14() {
    return reserved14;
  }

  public void setReserved14(byte reserved14) {
    this.reserved14 = reserved14;
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

  public int getFieldRCDPointer() {
    return fieldRCDPointer;
  }

  public void setFieldRCDPointer(int fieldRCDPointer) {
    this.fieldRCDPointer = fieldRCDPointer;
  }

  public String getSuppressionTokenName() {
    return suppressionTokenName;
  }

  public void setSuppressionTokenName(String suppressionTokenName) {
    this.suppressionTokenName = suppressionTokenName;
  }

  public short getShiftOutFontLocalID() {
    return shiftOutFontLocalID;
  }

  public void setShiftOutFontLocalID(short shiftOutFontLocalID) {
    this.shiftOutFontLocalID = shiftOutFontLocalID;
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

  public byte[] getReserved57_69() {
    return reserved57_69;
  }

  public void setReserved57_69(byte[] reserved57_69) {
    this.reserved57_69 = reserved57_69;
  }

  public enum RCD_Flag {
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
    FieldRCD_RecordRCD(6),
    FieldRCD_FieldRCD(6),
    UseFixedData_DoNotPresent(7),
    UseFixedData_DoPresent(7),
    // (8)	reserved.
    // (9)	reserved.
    // (10)	reserved.
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
    UseRecordID_DoNotSelectRecordID(21),
    UseRecordID_SelectRecordID(21),
    // (22)	reserved.
    // (23)	reserved.
    ;
    private static MutualExclusiveGroupedFlagHandler<RCD_Flag> handler = new MutualExclusiveGroupedFlagHandler<RCD_Flag>();
    int group;

    RCD_Flag(int group) {
      this.group = group;
    }

    public static EnumSet<RCD_Flag> valueOf(int flagBytes) {
      EnumSet<RCD_Flag> result = EnumSet.noneOf(RCD_Flag.class);
      // 0x800000 is not used.
      // 0x400000 is not used.
      if ((flagBytes & 0x200000) != 0) result.add(GenerateInlinePosition_NewPosition);
      else result.add(GenerateInlinePosition_CurrentPosition);
      if ((flagBytes & 0x100000) != 0) result.add(GenerateBaselinePosition_NewPosition);
      else result.add(GenerateBaselinePosition_CurrentPosition);
      if ((flagBytes & 0x080000) != 0) result.add(GenerateFontChange_AsSpecified);
      else result.add(GenerateFontChange_TRC_MRC_Default);
      if ((flagBytes & 0x040000) != 0) result.add(GenerateSuppression_TextIsSuppressible);
      else result.add(GenerateSuppression_TextIsNotSuppressible);
      if ((flagBytes & 0x020000) != 0) result.add(FieldRCD_FieldRCD);
      else result.add(FieldRCD_RecordRCD);
      if ((flagBytes & 0x010000) != 0) result.add(UseFixedData_DoPresent);
      else result.add(UseFixedData_DoNotPresent);
      // 0x008000 is not used.
      // 0x004000 is not used.
      // 0x002000 is not used.
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
      if ((flagBytes & 0x000004) != 0) result.add(UseRecordID_SelectRecordID);
      else result.add(UseRecordID_DoNotSelectRecordID);
      // 0x000002 is not used.
      // 0x000001 is not used.
      return result;
    }

    public static byte[] toBytes(EnumSet<RCD_Flag> flags) {
      int result = 0;

      // 0x800000 is not used.
      // 0x400000 is not used.
      if (flags.contains(GenerateInlinePosition_NewPosition))
        result |= 0x200000; // (flagBytes & 0x200000)!=0) result.add(GenerateInlinePosition_NewPosition);
      if (flags.contains(GenerateBaselinePosition_NewPosition))
        result |= 0x100000; // (flagBytes & 0x100000)!=0) result.add(GenerateBaselinePosition_NewPosition);
      if (flags.contains(GenerateFontChange_AsSpecified))
        result |= 0x080000; // (flagBytes & 0x080000)!=0) result.add(GenerateFontChange_AsSpecified);
      if (flags.contains(GenerateSuppression_TextIsSuppressible))
        result |= 0x040000; // (flagBytes & 0x040000)!=0) result.add(GenerateSuppression_TextIsSuppressible);
      if (flags.contains(FieldRCD_FieldRCD))
        result |= 0x020000; // (flagBytes & 0x020000)!=0) result.add(FieldRCD_RecordRCD);
      if (flags.contains(UseFixedData_DoPresent))
        result |= 0x010000; // (flagBytes & 0x010000)!=0) result.add(UseFixedData_DoPresent);
      // 0x008000 is not used.
      // 0x004000 is not used.
      // 0x002000 is not used.
      if (flags.contains(ConditionalProcessing_DoPerformCP))
        result |= 0x001000; // (flagBytes & 0x001000)!=0) result.add(ConditionalProcessing_DoNotPerformCP);
      // 0x000800 is not used.
      if (flags.contains(RelativeBaselinePosition_RelativePosition))
        result |= 0x000400; // (flagBytes & 0x000400)!=0) result.add(RelativeBaselinePosition_AbsolutePosition);
      // 0x000200 is not used.
      // 0x000100 is not used.
      if (flags.contains(NewPage_LogicalPageEject))
        result |= 0x000080; // (flagBytes & 0x000080)!=0) result.add(NewPage_NoEffect);
      if (flags.contains(PrintPageNumber_PrintPageNumber))
        result |= 0x000040; // (flagBytes & 0x000040)!=0) result.add(PrintPageNumber_NoEffect);
      if (flags.contains(ResetPageNumber_ResetPageNumberToSpecifiedValue))
        result |= 0x000020; // (flagBytes & 0x000020)!=0) result.add(ResetPageNumber_NoEffect);
      if (flags.contains(GroupIndicator_SaveGroupHeader))
        result |= 0x000010; // (flagBytes & 0x000010)!=0) result.add(GroupIndicator_InputDataIsNotPartOfAGroup);
      if (flags.contains(FieldDelimeterSize_2Bytes))
        result |= 0x000008; // (flagBytes & 0x000008)!=0) result.add(FieldDelimeterSize_1Byte);
      if (flags.contains(UseRecordID_SelectRecordID))
        result |= 0x000004; // (flagBytes & 0x000004)!=0) result.add(UseRecordID_DoNotSelectRecordID);
      // 0x000002 is not used.
      // 0x000001 is not used.

      return UtilBinaryDecoding.intToByteArray(result, 3);
    }

    public static void setFlag(EnumSet<RCD_Flag> flags, RCD_Flag flag) {
      handler.setFlag(flags, flag);
    }
  }
}
