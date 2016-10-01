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
package com.mgz.afp.ptoca.controlSequence;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@AFPType
public abstract class PTOCAControlSequence implements IAFPDecodeableWriteable {
  @AFPField(isHidden = true)
  ControlSequenceIntroducer csi;

  public ControlSequenceIntroducer getCsi() {
    return csi;
  }

  public void setCsi(ControlSequenceIntroducer csi) {
    this.csi = csi;
  }

  public enum ControlSequenceFunctionType {
    Undefined(0x00),
    SIM_SetInlineMargin(0xC0), // (SIM)” on page 87
    SIA_SetIntercharacterAdjustment(0xC2), // (SIA)” on page 84
    SVI_SetVariableSpaceCharacterIncrement(0xC4), // (SVI)” on page 95
    AMI_AbsoluteMoveInline(0xC6), // (AMI)” on page 53
    RMI_RelativeMoveInline(0xC8), // (RMI)” on page 71
    // Baseline Controls
    SBI_SetBaselineIncrement(0xD0), // (SBI)” on page 75
    AMB_AbsoluteMoveBaseline(0xD2), // (AMB)” on page 51
    RMB_RelativeMoveBaseline(0xD4), // (RMB)” on page 69
    BLN_BeginLine(0xD8), // (BLN)” on page 55
    STO_SetTextOrientation(0xF6), // (STO)” on page 92
    // Controls for Data Strings
    TRN_TransparentData(0xDA), // (TRN)” on page 103
    RPS_RepeatString(0xEE), // (RPS)” on page 73
    NOP_NoOperation(0xF8), // (NOP)” on page 63
    // Controls for Rules
    DIR_DrawIaxisRule(0xE4), // (DIR)” on page 60
    DBR_DrawBaxisRule(0xE6), // (DBR)” on page 58
    // Character Controls
    STC_SetTextColor(0x74), // (STC)” on page 89
    SEC_SetExtendedTextColor(0x80), // (SEC)” on page 79
    SCFL_SetCodedFontLocal(0xF0), // (SCFL)” on page 77
    BSU_BeginSuppression(0xF2), // (BSU)” on page 56
    ESU_EndSuppression(0xF4), // (ESU)” on page 62
    // Field Controls
    OVS_Overstrike(0x72), // (OVS)” on page 64
    USC_Underscore(0x76), // (USC)” on page 105
    TBM_TemporaryBaselineMove(0x78); // (TBM)” on page 97


    int typeCode;

    ControlSequenceFunctionType(int typeCode) {
      this.typeCode = typeCode;
    }

    public static ControlSequenceFunctionType valueOf(short typeCode) throws AFPParserException {
      for (ControlSequenceFunctionType csft : values())
        if (csft.typeCode == typeCode || csft.typeCode == (typeCode - 1)) return csft;
      return Undefined;
    }

    public int toByte(boolean isChained) {
      return isChained ? (typeCode + 1) : typeCode;
    }
  }

  public enum PTOCA_BypassFlag {
    BypassRelativeMoveInline,
    BypassAbsoluteMoveInline,
    BypassSpaceCharactersVariableSpaceCharacters,
    NoBypass;

    public static PTOCA_BypassFlag valueOf(byte flagByte) {
      if ((flagByte & 0x08) == 0x08) return BypassRelativeMoveInline;
      else if ((flagByte & 0x04) == 0x04) return BypassAbsoluteMoveInline;
      else if ((flagByte & 0x02) == 0x02) return BypassSpaceCharactersVariableSpaceCharacters;
      else if ((flagByte & 0x01) == 0x01) return NoBypass;
      else return null;
    }

    public int toByte() {
      if (this == BypassRelativeMoveInline) return 0x08;
      else if (this == BypassAbsoluteMoveInline) return 0x04;
      else if (this == BypassSpaceCharactersVariableSpaceCharacters) return 0x02;
      else return 0x01;
    }
  }

  public static class ControlSequenceIntroducer {

    short csPrefix; // 0x2B
    short csClass; // 0xD3
    short length;
    @AFPField(isEditable = false)
    ControlSequenceFunctionType controlSequenceFunctionType;
    short originalCSFT;
    volatile boolean isChained;

    /**
     * The is Chained parameter indicates that the preceding control sequence was flaged as chained,
     * and therefore the following control sequence introducer is in the "chained" format.
     */
    public static ControlSequenceIntroducer parseCSI(boolean isChained, byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      ControlSequenceIntroducer csi = new ControlSequenceIntroducer();
      short originalCSFT;
      int pos = 0;
      if (isChained) {
        csi.csPrefix = -1;
        csi.csClass = -1;
      } else {
        csi.csPrefix = UtilBinaryDecoding.parseShort(sfData, offset, 1);
        csi.csClass = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
        pos = 2;
      }
      csi.length = UtilBinaryDecoding.parseShort(sfData, offset + pos, 1);
      originalCSFT = UtilBinaryDecoding.parseShort(sfData, offset + pos + 1, 1);
      csi.controlSequenceFunctionType = ControlSequenceFunctionType.valueOf(originalCSFT);

      csi.isChained = isChained = (originalCSFT & 0x01) != 0;

      return csi;
    }

    public byte[] toBytes() {
      byte[] data = null;
      if (csPrefix == -1 && csClass == -1) {
        // Chained CSI with length of 2 bytes.
        data = new byte[2];
        data[0] = (byte) length;
        data[1] = (byte) controlSequenceFunctionType.toByte(isChained);
      } else {
        // Not chained CSI with length of 4 bytes.
        data = new byte[4];
        data[0] = (byte) csPrefix;
        data[1] = (byte) csClass;
        data[2] = (byte) length;
        data[3] = (byte) controlSequenceFunctionType.toByte(isChained);
      }
      return data;
    }

    public short getCsPrefix() {
      return csPrefix;
    }

    public void setCsPrefix(short csPrefix) {
      this.csPrefix = csPrefix;
    }

    public short getCsClass() {
      return csClass;
    }

    public void setCsClass(short csClass) {
      this.csClass = csClass;
    }

    public short getLength() {
      return length;
    }

    public void setLength(short length) {
      this.length = length;
    }

    public ControlSequenceFunctionType getControlSequenceFunctionType() {
      return controlSequenceFunctionType;
    }

    public void setControlSequenceFunctionType(
            ControlSequenceFunctionType controlSequenceFunctionType) {
      this.controlSequenceFunctionType = controlSequenceFunctionType;
    }

    /**
     * If true the NEXT control sequence that follows thsi control sequence has a control sequence
     * identifier in the "chained" format.
     */
    public boolean isChained() {
      return isChained;
    }

    public void setChained(boolean isChained) {
      this.isChained = isChained;
    }

    public short getOriginalCSFT() {
      return originalCSFT;
    }

    public void setOriginalCSFT(short originalCSFT) {
      this.originalCSFT = originalCSFT;
    }
  }

  public static class Undefined extends PTOCAControlSequence {
    @AFPField
    byte[] undefinedData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        undefinedData = new byte[actualLength];
        System.arraycopy(sfData, offset, undefinedData, 0, actualLength);
      } else {
        undefinedData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (undefinedData != null) os.write(undefinedData);
    }

  }

  /**
   * PTOCA, Page 51. <br>The Absolute Move Baseline control sequence moves the baseline coordinate
   * relative to the I-axis.
   */
  public static class AMB_AbsoluteMoveBaseline extends PTOCAControlSequence {
    short displacement;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      displacement = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(displacement, 2));
    }


    public short getDisplacement() {
      return displacement;
    }

    public void setDisplacement(short displacement) {
      this.displacement = displacement;
    }
  }

  /**
   * PTOCA, Page 53. <br>The Absolute Move Inline control sequence moves the inline coordinate
   * position relative to the B-axis.
   */
  public static class AMI_AbsoluteMoveInline extends PTOCAControlSequence {
    short displacement;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      displacement = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(displacement, 2));
    }


    public short getDisplacement() {
      return displacement;
    }

    public void setDisplacement(short displacement) {
      this.displacement = displacement;
    }
  }

  /**
   * PTOCA, Page 55. <br> The Begin Line control sequence begins a new line.
   */
  public static class BLN_BeginLine extends PTOCAControlSequence {
    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException { /* NOP */}

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException { /* NOP */}

  }

  /**
   * PTOCA, Page 56. <br>The Begin Suppression control sequence marks the beginning of a string of
   * presentation text that may be suppressed from the visible output.
   */
  public static class BSU_BeginSuppression extends PTOCAControlSequence {
    short suppressionID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      suppressionID = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(suppressionID);
    }

    public short getSuppressionID() {
      return suppressionID;
    }

    public void setSuppressionID(short suppressionID) {
      this.suppressionID = suppressionID;
    }
  }

  /**
   * PTOCA, Page 58. <br>The Draw B-axis Rule control sequence draws a rule in the B-direction.
   */
  public static class DBR_DrawBaxisRule extends PTOCAControlSequence {
    short length;
    Short width;
    Byte widthFraction;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      this.length = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 1) {
        width = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
        widthFraction = sfData[offset + 4];
      } else {
        width = null;
        widthFraction = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(length, 2));
      if (width != null) {
        os.write(UtilBinaryDecoding.shortToByteArray(width, 2));
        os.write(widthFraction != null ? widthFraction : 0x00);
      }
    }


    public short getLength() {
      return length;
    }

    public void setLength(short length) {
      this.length = length;
    }

    public Short getWidth() {
      return width;
    }

    public void setWidth(Short width) {
      this.width = width;
    }

    public Byte getWidthFraction() {
      return widthFraction;
    }

    public void setWidthFraction(Byte widthFraction) {
      this.widthFraction = widthFraction;
    }
  }

  /* PTOCA, Page 60. <br> The Draw I-axis Rule control sequence draws a rule in the I-direction.*/
  public static class DIR_DrawIaxisRule extends PTOCAControlSequence {
    short length;
    Short width;
    Byte widthFraction;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      this.length = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 2) {
        width = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
        widthFraction = sfData[offset + 4];
      } else {
        width = null;
        widthFraction = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(length, 2));
      if (width != null) {
        os.write(UtilBinaryDecoding.shortToByteArray(width, 2));
        os.write(widthFraction != null ? widthFraction : 0x00);
      }
    }


    public short getLength() {
      return length;
    }

    public void setLength(short length) {
      this.length = length;
    }

    public Short getWidth() {
      return width;
    }

    public void setWidth(Short width) {
      this.width = width;
    }

    public Byte getWidthFraction() {
      return widthFraction;
    }

    public void setWidthFraction(Byte widthFraction) {
      this.widthFraction = widthFraction;
    }
  }

  /* PTOCA, Page 62. <br> */
  public static class ESU_EndSuppression extends PTOCAControlSequence {
    short suppressionID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      suppressionID = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(suppressionID);
    }

    public short getSuppressionID() {
      return suppressionID;
    }

    public void setSuppressionID(short suppressionID) {
      this.suppressionID = suppressionID;
    }
  }

  /* PTOCA, Page 63. <br> */
  public static class NOP_NoOperation extends PTOCAControlSequence {
    byte[] ignoredData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        ignoredData = new byte[actualLength];
        System.arraycopy(sfData, offset, ignoredData, 0, actualLength);
      } else {
        ignoredData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (ignoredData != null) os.write(ignoredData);
    }


    public byte[] getIgnoredData() {
      return ignoredData;
    }

    public void setIgnoredData(byte[] ignoredData) {
      this.ignoredData = ignoredData;
    }
  }

  /* PTOCA, Page 64. <br> */
  public static class OVS_Overstrike extends PTOCAControlSequence {
    PTOCA_BypassFlag bypassFlag;
    int overStrikeCharacterCodePoint;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      bypassFlag = PTOCA_BypassFlag.valueOf(sfData[offset]);
      overStrikeCharacterCodePoint = UtilBinaryDecoding.parseInt(sfData, offset + 1, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(bypassFlag.toByte());
      os.write(UtilBinaryDecoding.intToByteArray(overStrikeCharacterCodePoint, 2));
    }

    public PTOCA_BypassFlag getBypassFlag() {
      return bypassFlag;
    }

    public void setBypassFlag(PTOCA_BypassFlag bypassFlag) {
      this.bypassFlag = bypassFlag;
    }

    public int getOverStrikeCharacterCodePoint() {
      return overStrikeCharacterCodePoint;
    }

    public void setOverStrikeCharacterCodePoint(int overStrikeCharacterCodePoint) {
      this.overStrikeCharacterCodePoint = overStrikeCharacterCodePoint;
    }


  }

  /* PTOCA, Page 69. <br> */
  public static class RMB_RelativeMoveBaseline extends PTOCAControlSequence {
    short increment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      increment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(increment, 2));
    }


    public short getIncrement() {
      return increment;
    }

    public void setIncrement(short increment) {
      this.increment = increment;
    }
  }

  /* PTOCA, Page 71. <br> */
  public static class RMI_RelativeMoveInline extends PTOCAControlSequence {
    short increment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      increment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(increment, 2));
    }


    public short getIncrement() {
      return increment;
    }

    public void setIncrement(short increment) {
      this.increment = increment;
    }
  }

  /* PTOCA, Page 73. <br> */
  public static class RPS_RepeatString extends PTOCAControlSequence {
    short repeatLength;
    byte[] repeatData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      repeatLength = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLegth = StructuredField.getActualLength(sfData, offset, length);
      if (actualLegth > 2) {
        repeatData = new byte[actualLegth - 2];
        System.arraycopy(sfData, offset + 2, repeatData, 0, repeatData.length);
      } else {
        repeatData = null;
      }

    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(repeatLength, 2));
      if (repeatData != null) os.write(repeatData);
    }


    public short getRepeatLength() {
      return repeatLength;
    }

    public void setRepeatLength(short repeatLength) {
      this.repeatLength = repeatLength;
    }

    public byte[] getRepeatData() {
      return repeatData;
    }

    public void setRepeatData(byte[] repeatData) {
      this.repeatData = repeatData;
    }

    public void setRepeatData(String data, Charset encoding) {
      this.repeatData = data.getBytes(encoding);
    }

  }

  /* PTOCA, Page 75. <br> The Set Baseline Increment control sequence specifies the increment to be added to the current baseline coordinate when a Begin Line control sequence is executed. This is a modal control sequence. */
  public static class SBI_SetBaselineIncrement extends PTOCAControlSequence {
    short increment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      increment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(increment, 2));
    }


    public short getIncrement() {
      return increment;
    }

    public void setIncrement(short increment) {
      this.increment = increment;
    }
  }

  /* PTOCA, Page 77. <br> The Set Coded Font Local control sequence activates a coded font and specifies the character attributes to be used. This is a modal control sequence. */
  public static class SCFL_SetCodedFontLocal extends PTOCAControlSequence {
    short codedFontLocalID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      codedFontLocalID = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.intToByteArray(codedFontLocalID, 1));
    }


    public short getCodedFontLocalID() {
      return codedFontLocalID;
    }

    public void setCodedFontLocalID(short codedFontLocalID) {
      this.codedFontLocalID = codedFontLocalID;
    }
  }

  /* PTOCA, Page 79. <br> The Set Extended Text Color control sequence specifies a color value and defines the color space and encoding for that value. The specified color value is applied to foreground areas of the text presentation space. */
  public static class SEC_SetExtendedTextColor extends PTOCAControlSequence {
    byte reserved4 = 0x00;
    AFPColorSpace colorSpace;
    byte[] reserved6_9 = new byte[4];
    byte nrOfBitsComponent1;
    byte nrOfBitsComponent2;
    byte nrOfBitsComponent3;
    byte nrOfBitsComponent4;
    byte[] colorValue;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      reserved4 = sfData[offset];
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 1]);
      reserved6_9 = new byte[4];
      System.arraycopy(sfData, offset + 2, reserved6_9, 0, reserved6_9.length);
      nrOfBitsComponent1 = sfData[offset + 6];
      nrOfBitsComponent2 = sfData[offset + 7];
      nrOfBitsComponent3 = sfData[offset + 8];
      nrOfBitsComponent4 = sfData[offset + 9];
      colorValue = new byte[4];
      System.arraycopy(sfData, offset + 10, colorValue, 0, colorValue.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(reserved4);
      os.write(colorSpace.toByte());
      os.write(reserved6_9);
      os.write(nrOfBitsComponent1);
      os.write(nrOfBitsComponent2);
      os.write(nrOfBitsComponent3);
      os.write(nrOfBitsComponent4);
      os.write(colorValue);
    }

    public byte getReserved4() {
      return reserved4;
    }

    public void setReserved4(byte reserved4) {
      this.reserved4 = reserved4;
    }

    public AFPColorSpace getColorSpace() {
      return colorSpace;
    }

    public void setColorSpace(AFPColorSpace colorSpace) {
      this.colorSpace = colorSpace;
    }

    public byte[] getReserved6_9() {
      return reserved6_9;
    }

    public void setReserved6_9(byte[] reserved6_9) {
      this.reserved6_9 = reserved6_9;
    }

    public byte getNrOfBitsComponent1() {
      return nrOfBitsComponent1;
    }

    public void setNrOfBitsComponent1(byte nrOfBitsComponent1) {
      this.nrOfBitsComponent1 = nrOfBitsComponent1;
    }

    public byte getNrOfBitsComponent2() {
      return nrOfBitsComponent2;
    }

    public void setNrOfBitsComponent2(byte nrOfBitsComponent2) {
      this.nrOfBitsComponent2 = nrOfBitsComponent2;
    }

    public byte getNrOfBitsComponent3() {
      return nrOfBitsComponent3;
    }

    public void setNrOfBitsComponent3(byte nrOfBitsComponent3) {
      this.nrOfBitsComponent3 = nrOfBitsComponent3;
    }

    public byte getNrOfBitsComponent4() {
      return nrOfBitsComponent4;
    }

    public void setNrOfBitsComponent4(byte nrOfBitsComponent4) {
      this.nrOfBitsComponent4 = nrOfBitsComponent4;
    }

    public byte[] getColorValue() {
      return colorValue;
    }

    public void setColorValue(byte[] colorValue) {
      this.colorValue = colorValue;
    }
  }

  /* PTOCA, Page 84. <br> The Set Intercharacter Adjustment control sequence specifies additional increment or decrement between graphic characters. This is a modal control sequence. */
  public static class SIA_SetIntercharacterAdjustment extends PTOCAControlSequence {
    short adjustment;
    SIA_Direction direction;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      adjustment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      if (StructuredField.getActualLength(sfData, offset, length) > 2) {
        direction = SIA_Direction.valueOf(sfData[offset + 1]);
      } else {
        direction = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(adjustment, 2));
      if (direction != null) os.write(direction.toByte());
    }

    public short getAdjustment() {
      return adjustment;
    }

    public void setAdjustment(short adjustment) {
      this.adjustment = adjustment;
    }

    public SIA_Direction getDirection() {
      return direction;
    }

    public void setDirection(SIA_Direction direction) {
      this.direction = direction;
    }

    public enum SIA_Direction {
      PositiveIDirection,
      NegativeIDirection;

      public static SIA_Direction valueOf(byte codeByte) {
        if (codeByte == 0) return PositiveIDirection;
        else return NegativeIDirection;
      }

      public int toByte() {
        if (this == PositiveIDirection) return 0x00;
        else return 0x01;
      }
    }
  }

  /* PTOCA, Page 87. <br> The Set Inline Margin control sequence specifies the position of an inline margin. This is a modal control sequence. */
  public static class SIM_SetInlineMargin extends PTOCAControlSequence {
    short displacement;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      displacement = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(displacement, 2));
    }


    public short getDisplacement() {
      return displacement;
    }

    public void setDisplacement(short displacement) {
      this.displacement = displacement;
    }
  }

  /* PTOCA, Page 89. <br> The Set Text Color control sequence specifies a color attribute for the foreground areas of the text presentation space. */
  public static class STC_SetTextColor extends PTOCAControlSequence {
    AFPColorValue foregroundColor;
    STC_Precision precision;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      foregroundColor = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
      if (StructuredField.getActualLength(sfData, offset, length) > 2) {
        precision = STC_Precision.valueOf(sfData[offset + 2]);
      } else {
        precision = null;
      }

    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(foregroundColor.toByte2());
      if (precision != null) os.write(precision.toByte());
    }

    public AFPColorValue getForegroundColor() {
      return foregroundColor;
    }

    public void setForegroundColor(AFPColorValue foregroundColor) {
      this.foregroundColor = foregroundColor;
    }

    public STC_Precision getPrecision() {
      return precision;
    }

    public void setPrecision(STC_Precision precision) {
      this.precision = precision;
    }

    public enum STC_Precision {
      IfSpecifiedColorNotSupported_EceptionAndDefault0xFF07,
      IfSpecifiedColorNotSupported_SubstitutColorOrDefaul0xFF07;

      public static STC_Precision valueOf(byte codeByte) {
        if (codeByte == 0x00) return IfSpecifiedColorNotSupported_EceptionAndDefault0xFF07;
        else return IfSpecifiedColorNotSupported_SubstitutColorOrDefaul0xFF07;
      }

      public int toByte() {
        if (this == IfSpecifiedColorNotSupported_EceptionAndDefault0xFF07) return 0x00;
        else return 0x01;
      }
    }
  }

  /* PTOCA, Page 92. <br> The Set Text Orientation control sequence establishes the I-direction and B-direction for the subsequent text. This is a modal control sequence. */
  public static class STO_SetTextOrientation extends PTOCAControlSequence {
    AFPOrientation xOrientation;
    AFPOrientation yOrientation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      xOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
      yOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 2, 2));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(xOrientation.toBytes());
      os.write(yOrientation.toBytes());
    }

    public AFPOrientation getxOrientation() {
      return xOrientation;
    }

    public void setxOrientation(AFPOrientation xOrientation) {
      this.xOrientation = xOrientation;
    }

    public AFPOrientation getyOrientation() {
      return yOrientation;
    }

    public void setyOrientation(AFPOrientation yOrientation) {
      this.yOrientation = yOrientation;
    }
  }

  /* PTOCA, Page 95. <br> The Set Variable Space Character Increment control sequence specifies the increment for a variable space character.*/
  public static class SVI_SetVariableSpaceCharacterIncrement extends PTOCAControlSequence {
    short increment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      increment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(increment, 2));
    }


    public short getIncrement() {
      return increment;
    }

    public void setIncrement(short increment) {
      this.increment = increment;
    }
  }

  /* PTOCA, Page 97. <br> The Temporary Baseline Move control sequence changes the position of the baseline without changing the established baseline. */
  public static class TBM_TemporaryBaselineMove extends PTOCAControlSequence {
    TBM_Direction direction;
    TBM_Precision precision;
    Short temporaryBaselineIncrement;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      direction = TBM_Direction.valueOf(sfData[offset]);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength >= 2) {
        precision = TBM_Precision.valueOf(sfData[offset + 1]);
        if (actualLength >= 3) {
          temporaryBaselineIncrement = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
        } else {
          temporaryBaselineIncrement = null;
        }
      } else {
        precision = null;
        temporaryBaselineIncrement = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(direction.toByte());
      if (precision != null) {
        os.write(precision.toByte());
        if (temporaryBaselineIncrement != null) {
          os.write(UtilBinaryDecoding.shortToByteArray(temporaryBaselineIncrement, 2));
        }
      }
    }

    public TBM_Direction getDirection() {
      return direction;
    }

    public void setDirection(TBM_Direction direction) {
      this.direction = direction;
    }

    public TBM_Precision getPrecision() {
      return precision;
    }

    public void setPrecision(TBM_Precision precision) {
      this.precision = precision;
    }

    public Short getTemporaryBaselineIncrement() {
      return temporaryBaselineIncrement;
    }

    public void setTemporaryBaselineIncrement(Short temporaryBaselineIncrement) {
      this.temporaryBaselineIncrement = temporaryBaselineIncrement;
    }

    public enum TBM_Direction {
      DoNotChangeBaseline,
      ReturnToEstablishedBaseline,
      MoveAwayFromIAxis,
      MoveTowardIAxis;

      public static TBM_Direction valueOf(byte codeByte) throws AFPParserException {
        for (TBM_Direction dir : values()) if (dir.ordinal() == codeByte) return dir;
        throw new AFPParserException("The TBM direction code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }

    public enum TBM_Precision {
      AccuratelyPlaced,
      MayBeSimulated;

      public static TBM_Precision valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x00) return AccuratelyPlaced;
        else if (codeByte == 0x01) return MayBeSimulated;
        throw new AFPParserException("The TBM precision code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /* PTOCA, Page 103. <br> The Transparent Data control sequence contains a sequence of code points that are presented without a scan for embedded control sequences. */
  public static class TRN_TransparentData extends PTOCAControlSequence {
    String transparentData;
    byte[] transparentDataEBCDIC;

    volatile boolean isUseEBCDICData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        transparentDataEBCDIC = new byte[actualLength];
        System.arraycopy(sfData, offset, transparentDataEBCDIC, 0, actualLength);
        transparentData = new String(transparentDataEBCDIC, config.getAfpCharSet());
      } else {
        transparentData = null;
        transparentDataEBCDIC = null;
      }
    }

    /**
     * If {@link #isUseEBCDICData()} is set tot true the EBCDIC data is written out without
     * performing an encoding/decoding round trip. See {@link #setUseEBCDICData(boolean)}.
     */
    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (isUseEBCDICData && transparentDataEBCDIC != null) {
        os.write(transparentDataEBCDIC);
      } else if (transparentData != null) {
        os.write(transparentData.getBytes(config.getAfpCharSet()));
      }
    }

    public String getTransparentData() {
      return transparentData;
    }

    public void setTransparentData(String transparentData) {
      this.transparentData = transparentData;
    }

    public byte[] getTransparentDataEBCDIC() {
      return transparentDataEBCDIC;
    }

    public void setTransparentDataEBCDIC(byte[] transparentDataEBCDIC) {
      this.transparentDataEBCDIC = transparentDataEBCDIC;
    }

    /**
     * Returns true if the EBCDIC data is used in {@link #writeAFP(OutputStream,
     * AFPParserConfiguration)} without performing a encoding/decoding round trip.
     */
    public boolean isUseEBCDICData() {
      return isUseEBCDICData;
    }

    /**
     * If given parameter is true the EBCDIC data is used in {@link #writeAFP(OutputStream,
     * AFPParserConfiguration)} without performing an encoding/decoding round trip.<br> This might
     * be usefull if problems occure with special code points (eg. user defined characters, variable
     * space character) that can't be properly mapped to Unicode characters and therefore, when
     * encoded back to EBCDIC, may produce unwanted results.
     */
    public void setUseEBCDICData(boolean isUseEBCDICData) {
      this.isUseEBCDICData = isUseEBCDICData;
    }
  }

  /* PTOCA, Page 105. <br> The Underscore control sequence identifies text fields that are to be underscored. */
  public static class USC_Underscore extends PTOCAControlSequence {
    PTOCA_BypassFlag bypassFlag;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      bypassFlag = PTOCA_BypassFlag.valueOf(sfData[offset]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(bypassFlag.toByte());
    }

    public PTOCA_BypassFlag getBypassFlag() {
      return bypassFlag;
    }

    public void setBypassFlag(PTOCA_BypassFlag bypassFlag) {
      this.bypassFlag = bypassFlag;
    }
  }
}
