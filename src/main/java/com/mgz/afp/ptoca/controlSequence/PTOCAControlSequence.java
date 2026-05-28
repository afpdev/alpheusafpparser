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

import javax.xml.bind.annotation.XmlRootElement;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@AFPType
@XmlSeeAlso({
    PTOCAControlSequence.TRN_TransparentData.class,
    PTOCAControlSequence.GraphicCharacters.class,
    PTOCAControlSequence.Undefined.class,
    PTOCAControlSequence.AMI_AbsoluteMoveInline.class,
    PTOCAControlSequence.AMB_AbsoluteMoveBaseline.class,
    PTOCAControlSequence.RMI_RelativeMoveInline.class,
    PTOCAControlSequence.RMB_RelativeMoveBaseline.class,
    PTOCAControlSequence.SIM_SetInlineMargin.class,
    PTOCAControlSequence.SBI_SetBaselineIncrement.class,
    PTOCAControlSequence.BLN_BeginLine.class,
    PTOCAControlSequence.STO_SetTextOrientation.class,
    PTOCAControlSequence.SCFL_SetCodedFontLocal.class,
    PTOCAControlSequence.STC_SetTextColor.class,
    PTOCAControlSequence.SEC_SetExtendedTextColor.class,
    PTOCAControlSequence.SIA_SetIntercharacterAdjustment.class,
    PTOCAControlSequence.SVI_SetVariableSpaceCharacterIncrement.class,
    PTOCAControlSequence.TBM_TemporaryBaselineMove.class,
    PTOCAControlSequence.BSU_BeginSuppression.class,
    PTOCAControlSequence.ESU_EndSuppression.class,
    PTOCAControlSequence.OVS_Overstrike.class,
    PTOCAControlSequence.USC_Underscore.class,
    PTOCAControlSequence.RPS_RepeatString.class,
    PTOCAControlSequence.DIR_DrawIaxisRule.class,
    PTOCAControlSequence.DBR_DrawBaxisRule.class,
    PTOCAControlSequence.NOP_NoOperation.class,
    PTOCAControlSequence.UCT_UnicodeComplexText.class,
    PTOCAControlSequence.GLC_GlyphLayoutControl.class,
    PTOCAControlSequence.ENC_EncryptedData.class,
    PTOCAControlSequence.SKI_SetKeyInformation.class,
    PTOCAControlSequence.SEA_SetEncryptedAlternate.class,
    PTOCAControlSequence.GIR_GlyphIdRun.class,
    PTOCAControlSequence.GAR_GlyphAdvanceRun.class,
    PTOCAControlSequence.GOR_GlyphOffsetRun.class
})
public abstract sealed class PTOCAControlSequence implements IAFPDecodeableWriteable permits PTOCAControlSequence.TRN_TransparentData, PTOCAControlSequence.GraphicCharacters, PTOCAControlSequence.Undefined, PTOCAControlSequence.AMI_AbsoluteMoveInline, PTOCAControlSequence.AMB_AbsoluteMoveBaseline, PTOCAControlSequence.RMI_RelativeMoveInline, PTOCAControlSequence.RMB_RelativeMoveBaseline, PTOCAControlSequence.SIM_SetInlineMargin, PTOCAControlSequence.SBI_SetBaselineIncrement, PTOCAControlSequence.BLN_BeginLine, PTOCAControlSequence.STO_SetTextOrientation, PTOCAControlSequence.SCFL_SetCodedFontLocal, PTOCAControlSequence.STC_SetTextColor, PTOCAControlSequence.SEC_SetExtendedTextColor, PTOCAControlSequence.SIA_SetIntercharacterAdjustment, PTOCAControlSequence.SVI_SetVariableSpaceCharacterIncrement, PTOCAControlSequence.TBM_TemporaryBaselineMove, PTOCAControlSequence.BSU_BeginSuppression, PTOCAControlSequence.ESU_EndSuppression, PTOCAControlSequence.OVS_Overstrike, PTOCAControlSequence.USC_Underscore, PTOCAControlSequence.RPS_RepeatString, PTOCAControlSequence.DIR_DrawIaxisRule, PTOCAControlSequence.DBR_DrawBaxisRule, PTOCAControlSequence.NOP_NoOperation, PTOCAControlSequence.UCT_UnicodeComplexText, PTOCAControlSequence.GLC_GlyphLayoutControl, PTOCAControlSequence.ENC_EncryptedData, PTOCAControlSequence.SKI_SetKeyInformation, PTOCAControlSequence.SEA_SetEncryptedAlternate, PTOCAControlSequence.GIR_GlyphIdRun, PTOCAControlSequence.GAR_GlyphAdvanceRun, PTOCAControlSequence.GOR_GlyphOffsetRun, Undefined {
  @AFPField(isHidden = true)
  ControlSequenceIntroducer csi;

  /**
   * Resets the control sequence to its initial state for reuse.
   */
  public void reset() {
    csi = null;
  }

  /**
   * Recursively releases any resources held by this control sequence back to their pools.
   */
  public void release() {
    ControlSequencePool.release(this);
  }

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
    UCT_UnicodeComplexText(0x6A), // (UCT)” on page 123
    GLC_GlyphLayoutControl(0x6C), // (GLC)” on page 84
    ENC_EncryptedData(0x98), // (ENC)” on page 101
    SKI_SetKeyInformation(0x9A), // (SKI)” on page 78
    SEA_SetEncryptedAlternate(0x9C), // (SEA)” on page 88
    GIR_GlyphIdRun(0x8A), // (GIR)” on page 83
    GAR_GlyphAdvanceRun(0x8C), // (GAR)” on page 82
    GOR_GlyphOffsetRun(0x8E), // (GOR)” on page 89
    // Field Controls
    OVS_Overstrike(0x72), // (OVS)” on page 64
    USC_Underscore(0x76), // (USC)” on page 105
    TBM_TemporaryBaselineMove(0x78), // (TBM)” on page 97
    GraphicCharacters(0xFF);

    int typeCode;

    ControlSequenceFunctionType(int typeCode) {
      this.typeCode = typeCode;
    }

    public static ControlSequenceFunctionType valueOf(short typeCode) throws AFPParserException {
      for (ControlSequenceFunctionType csft : values()) {
        if (csft.typeCode == typeCode || csft.typeCode == (typeCode - 1)) {
          return csft;
        }
      }
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
      if ((flagByte & 0x08) == 0x08) {
        return BypassRelativeMoveInline;
      } else if ((flagByte & 0x04) == 0x04) {
        return BypassAbsoluteMoveInline;
      } else if ((flagByte & 0x02) == 0x02) {
        return BypassSpaceCharactersVariableSpaceCharacters;
      } else if ((flagByte & 0x01) == 0x01) {
        return NoBypass;
      } else {
        return null;
      }
    }

    public int toByte() {
      if (this == BypassRelativeMoveInline) {
        return 0x08;
      } else if (this == BypassAbsoluteMoveInline) {
        return 0x04;
      } else if (this == BypassSpaceCharactersVariableSpaceCharacters) {
        return 0x02;
      } else {
        return 0x01;
      }
    }
  }

  @XmlRootElement
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
      csi.length = (short) (sfData[offset + pos] & 0xFF);
      originalCSFT = (short) (sfData[offset + pos + 1] & 0xFF);
      csi.controlSequenceFunctionType = ControlSequenceFunctionType.valueOf(originalCSFT);

      csi.isChained = (originalCSFT & 0x01) != 0;

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

  @XmlRootElement
  @XmlType(name = "ptocaUndefined")
  public static final class Undefined extends PTOCAControlSequence {
    @AFPField
    byte[] undefinedData;
    String text;

    @Override
    public void reset() {
      super.reset();
      undefinedData = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        undefinedData = new byte[actualLength];
        System.arraycopy(sfData, offset, undefinedData, 0, actualLength);
        if (UtilCharacterEncoding.isHumanReadable(undefinedData, config.getAfpCharSet())) {
          text = new String(undefinedData, config.getAfpCharSet());
        }
      } else {
        undefinedData = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (undefinedData != null) {
        os.write(undefinedData);
      }
    }

  }

  /**
   * PTOCA, Page 51. <br>The Absolute Move Baseline control sequence moves the baseline coordinate
   * relative to the I-axis.
   */
  @XmlRootElement
  public static final class AMB_AbsoluteMoveBaseline extends PTOCAControlSequence {
    short displacement;

    @Override
    public void reset() {
      super.reset();
      displacement = 0;
    }

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
  @XmlRootElement
  public static final class AMI_AbsoluteMoveInline extends PTOCAControlSequence {
    short displacement;

    @Override
    public void reset() {
      super.reset();
      displacement = 0;
    }

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
  @XmlRootElement
  public static final class BLN_BeginLine extends PTOCAControlSequence {
    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException { /* NOP */}

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException { /* NOP */}

  }

  /**
   * PTOCA, Page 56. <br>The Begin Suppression control sequence marks the beginning of a string of
   * presentation text that may be suppressed from the visible output.
   */
  @XmlRootElement
  public static final class BSU_BeginSuppression extends PTOCAControlSequence {
    short suppressionID;

    @Override
    public void reset() {
      super.reset();
      suppressionID = 0;
    }

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
  @XmlRootElement
  public static final class DBR_DrawBaxisRule extends PTOCAControlSequence {
    short length;
    Short width;
    Byte widthFraction;

    @Override
    public void reset() {
      super.reset();
      length = 0;
      width = null;
      widthFraction = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      this.length = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength >= 4) {
        width = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
        if (actualLength >= 5) {
          widthFraction = sfData[offset + 4];
        } else {
          widthFraction = null;
        }
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

  /** PTOCA, Page 60. <br> The Draw I-axis Rule control sequence draws a rule in the I-direction.*/
  @XmlRootElement
  public static final class DIR_DrawIaxisRule extends PTOCAControlSequence {
    short length;
    Short width;
    Byte widthFraction;

    @Override
    public void reset() {
      super.reset();
      length = 0;
      width = null;
      widthFraction = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      this.length = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength >= 4) {
        width = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
        if (actualLength >= 5) {
          widthFraction = sfData[offset + 4];
        } else {
          widthFraction = null;
        }
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

  /** PTOCA, Page 62. <br> */
  @XmlRootElement
  public static final class ESU_EndSuppression extends PTOCAControlSequence {
    short suppressionID;

    @Override
    public void reset() {
      super.reset();
      suppressionID = 0;
    }

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

  /** PTOCA, Page 63. <br> */
  @XmlRootElement
  @XmlType(name = "ptocaNOP_NoOperation")
  public static final class NOP_NoOperation extends PTOCAControlSequence {
    byte[] ignoredData;
    String text;

    @Override
    public void reset() {
      super.reset();
      ignoredData = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        ignoredData = new byte[actualLength];
        System.arraycopy(sfData, offset, ignoredData, 0, actualLength);
        if (UtilCharacterEncoding.isHumanReadable(ignoredData, config.getAfpCharSet())) {
          text = new String(ignoredData, config.getAfpCharSet());
        }
      } else {
        ignoredData = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (ignoredData != null) {
        os.write(ignoredData);
      }
    }

    public byte[] getIgnoredData() {
      return ignoredData;
    }

    public void setIgnoredData(byte[] ignoredData) {
      this.ignoredData = ignoredData;
    }
  }

  /** PTOCA, Page 64. <br> */
  @XmlRootElement
  public static final class OVS_Overstrike extends PTOCAControlSequence {
    PTOCA_BypassFlag bypassFlag;
    int overStrikeCharacterCodePoint;
    String text;

    @Override
    public void reset() {
      super.reset();
      bypassFlag = null;
      overStrikeCharacterCodePoint = 0;
      text = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      bypassFlag = PTOCA_BypassFlag.valueOf(sfData[offset]);
      overStrikeCharacterCodePoint = UtilBinaryDecoding.parseInt(sfData, offset + 1, 2);
      if (overStrikeCharacterCodePoint <= 0xFF) {
        text = new String(new byte[] {(byte) overStrikeCharacterCodePoint}, config.getAfpCharSet());
      } else {
        text = new String(
            UtilBinaryDecoding.intToByteArray(overStrikeCharacterCodePoint, 2),
            config.getAfpCharSet());
      }
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

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }
  }

  /** PTOCA, Page 69. <br> */
  @XmlRootElement
  public static final class RMB_RelativeMoveBaseline extends PTOCAControlSequence {
    short increment;

    @Override
    public void reset() {
      super.reset();
      increment = 0;
    }

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

  /** PTOCA, Page 71. <br> */
  @XmlRootElement
  public static final class RMI_RelativeMoveInline extends PTOCAControlSequence {
    short increment;

    @Override
    public void reset() {
      super.reset();
      increment = 0;
    }

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

  /** PTOCA, Page 73. <br> */
  @XmlRootElement
  public static final class RPS_RepeatString extends PTOCAControlSequence {
    short repeatLength;
    byte[] repeatData;
    String text;

    @Override
    public void reset() {
      super.reset();
      repeatLength = 0;
      repeatData = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      repeatLength = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      int actualLegth = StructuredField.getActualLength(sfData, offset, length);
      if (actualLegth > 2) {
        repeatData = new byte[actualLegth - 2];
        System.arraycopy(sfData, offset + 2, repeatData, 0, repeatData.length);

        int len = repeatLength & 0xFFFF;
        byte[] fullData = new byte[len];
        for (int i = 0; i < len; i++) {
          fullData[i] = repeatData[i % repeatData.length];
        }
        if (UtilCharacterEncoding.isHumanReadable(fullData, config.getAfpCharSet())) {
          text = new String(fullData, config.getAfpCharSet());
        }
      } else {
        repeatData = null;
        text = null;
      }

    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(repeatLength, 2));
      if (repeatData != null) {
        os.write(repeatData);
      }
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

  /** PTOCA, Page 75. <br> The Set Baseline Increment control sequence specifies the increment to be added to the current baseline coordinate when a Begin Line control sequence is executed. This is a modal control sequence. */
  @XmlRootElement
  public static final class SBI_SetBaselineIncrement extends PTOCAControlSequence {
    short increment;

    @Override
    public void reset() {
      super.reset();
      increment = 0;
    }

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

  /** PTOCA, Page 77. <br> The Set Coded Font Local control sequence activates a coded font and specifies the character attributes to be used. This is a modal control sequence. */
  @XmlRootElement
  public static final class SCFL_SetCodedFontLocal extends PTOCAControlSequence {
    short codedFontLocalID;

    @Override
    public void reset() {
      super.reset();
      codedFontLocalID = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      codedFontLocalID = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      Charset cs = config.getCharsetForLID(codedFontLocalID);
      if (cs != null) {
        config.setAfpCharSet(cs);
      }
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

  /** PTOCA, Page 79. <br> The Set Extended Text Color control sequence specifies a color value and defines the color space and encoding for that value. The specified color value is applied to foreground areas of the text presentation space. */
  @XmlRootElement
  public static final class SEC_SetExtendedTextColor extends PTOCAControlSequence {
    byte reserved4 = 0x00;
    AFPColorSpace colorSpace;
    byte[] reserved6_9 = new byte[4];
    byte nrOfBitsComponent1;
    byte nrOfBitsComponent2;
    byte nrOfBitsComponent3;
    byte nrOfBitsComponent4;
    byte[] colorValue;

    @Override
    public void reset() {
      super.reset();
      reserved4 = 0x00;
      colorSpace = null;
      reserved6_9 = new byte[4];
      nrOfBitsComponent1 = 0;
      nrOfBitsComponent2 = 0;
      nrOfBitsComponent3 = 0;
      nrOfBitsComponent4 = 0;
      colorValue = null;
    }

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

  /** PTOCA, Page 84. <br> The Set Intercharacter Adjustment control sequence specifies additional increment or decrement between graphic characters. This is a modal control sequence. */
  @XmlRootElement
  public static final class SIA_SetIntercharacterAdjustment extends PTOCAControlSequence {
    short adjustment;
    SIA_Direction direction;

    @Override
    public void reset() {
      super.reset();
      adjustment = 0;
      direction = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      adjustment = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      if (StructuredField.getActualLength(sfData, offset, length) >= 3) {
        direction = SIA_Direction.valueOf(sfData[offset + 2]);
      } else {
        direction = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(adjustment, 2));
      if (direction != null) {
        os.write(direction.toByte());
      }
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
        if (codeByte == 0) {
          return PositiveIDirection;
        } else {
          return NegativeIDirection;
        }
      }

      public int toByte() {
        if (this == PositiveIDirection) {
          return 0x00;
        } else {
          return 0x01;
        }
      }
    }
  }

  /** PTOCA, Page 87. <br> The Set Inline Margin control sequence specifies the position of an inline margin. This is a modal control sequence. */
  @XmlRootElement
  public static final class SIM_SetInlineMargin extends PTOCAControlSequence {
    short displacement;

    @Override
    public void reset() {
      super.reset();
      displacement = 0;
    }

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

  /** PTOCA, Page 89. <br> The Set Text Color control sequence specifies a color attribute for the foreground areas of the text presentation space. */
  @XmlRootElement
  public static final class STC_SetTextColor extends PTOCAControlSequence {
    AFPColorValue foregroundColor;
    STC_Precision precision;

    @Override
    public void reset() {
      super.reset();
      foregroundColor = null;
      precision = null;
    }

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
      if (precision != null) {
        os.write(precision.toByte());
      }
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
        if (codeByte == 0x00) {
          return IfSpecifiedColorNotSupported_EceptionAndDefault0xFF07;
        } else {
          return IfSpecifiedColorNotSupported_SubstitutColorOrDefaul0xFF07;
        }
      }

      public int toByte() {
        if (this == IfSpecifiedColorNotSupported_EceptionAndDefault0xFF07) {
          return 0x00;
        } else {
          return 0x01;
        }
      }
    }
  }

  /** PTOCA, Page 92. <br> The Set Text Orientation control sequence establishes the I-direction and B-direction for the subsequent text. This is a modal control sequence. */
  @XmlRootElement
  public static final class STO_SetTextOrientation extends PTOCAControlSequence {
    AFPOrientation xOrientation;
    AFPOrientation yOrientation;

    @Override
    public void reset() {
      super.reset();
      xOrientation = null;
      yOrientation = null;
    }

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

  /** PTOCA, Page 95. <br> The Set Variable Space Character Increment control sequence specifies the increment for a variable space character.*/
  @XmlRootElement
  public static final class SVI_SetVariableSpaceCharacterIncrement extends PTOCAControlSequence {
    short increment;

    @Override
    public void reset() {
      super.reset();
      increment = 0;
    }

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

  /** PTOCA, Page 97. <br> The Temporary Baseline Move control sequence changes the position of the baseline without changing the established baseline. */
  @XmlRootElement
  public static final class TBM_TemporaryBaselineMove extends PTOCAControlSequence {
    TBM_Direction direction;
    TBM_Precision precision;
    Short temporaryBaselineIncrement;

    @Override
    public void reset() {
      super.reset();
      direction = null;
      precision = null;
      temporaryBaselineIncrement = null;
    }

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
        for (TBM_Direction dir : values()) {
          if (dir.ordinal() == codeByte) {
            return dir;
          }
        }
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
        if (codeByte == 0x00) {
          return AccuratelyPlaced;
        } else if (codeByte == 0x01) {
          return MayBeSimulated;
        }
        throw new AFPParserException("The TBM precision code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /** PTOCA, Page 103. <br> The Transparent Data control sequence contains a sequence of code points that are presented without a scan for embedded control sequences. */
  @XmlRootElement
  public static final class TRN_TransparentData extends PTOCAControlSequence {
    String transparentData;
    byte[] transparentDataEBCDIC;

    volatile boolean isUseEBCDICData;

    @Override
    public void reset() {
      super.reset();
      transparentData = null;
      transparentDataEBCDIC = null;
      isUseEBCDICData = false;
    }

    @XmlElement(name = "text")
    public String getText() {
      if (transparentData == null || transparentData.isEmpty()) {
        return null;
      }
      return UtilCharacterEncoding.sanitizeForXml(transparentData);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        transparentData = UtilCharacterEncoding.decodeEbcdic(sfData, offset, length, config);
        if (isUseEBCDICData) {
          transparentDataEBCDIC = new byte[actualLength];
          System.arraycopy(sfData, offset, transparentDataEBCDIC, 0, actualLength);
        } else {
          transparentDataEBCDIC = null;
        }
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

    @javax.xml.bind.annotation.XmlTransient
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

  /**
   * PTOCA, Page 123. <br>The Unicode Complex Text control sequence marks the start of a string of
   * code points, all of which are to be processed as graphic characters.
   */
  @XmlRootElement
  public static final class UCT_UnicodeComplexText extends PTOCAControlSequence {
    byte uctVers = 0x01;
    int ctLength;
    byte ctFlags;
    byte bidiCt;
    byte glyphCt;
    short altiPos;
    byte[] complexText;

    @Override
    public void reset() {
      super.reset();
      uctVers = 0x01;
      ctLength = 0;
      ctFlags = 0;
      bidiCt = 0;
      glyphCt = 0;
      altiPos = 0;
      complexText = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      if (complexText == null || complexText.length == 0) {
        return null;
      }
      return UtilCharacterEncoding.sanitizeForXml(new String(complexText, java.nio.charset.StandardCharsets.UTF_16BE));
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
        throws AFPParserException {
      uctVers = sfData[offset];
      ctLength = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
      ctFlags = sfData[offset + 4];
      bidiCt = sfData[offset + 6];
      glyphCt = sfData[offset + 7];
      altiPos = UtilBinaryDecoding.parseShort(sfData, offset + 12, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(uctVers);
      os.write(0x00); // Reserved
      os.write(UtilBinaryDecoding.intToByteArray(ctLength, 2));
      os.write(ctFlags);
      os.write(0x00); // Reserved
      os.write(bidiCt);
      os.write(glyphCt);
      os.write(new byte[4]); // Reserved
      os.write(UtilBinaryDecoding.shortToByteArray(altiPos, 2));
      if (complexText != null) {
        os.write(complexText);
      }
    }

    public byte getUctVers() {
      return uctVers;
    }

    public void setUctVers(byte uctVers) {
      this.uctVers = uctVers;
    }

    public int getCtLength() {
      return ctLength;
    }

    public void setCtLength(int ctLength) {
      this.ctLength = ctLength;
    }

    public byte getCtFlags() {
      return ctFlags;
    }

    public void setCtFlags(byte ctFlags) {
      this.ctFlags = ctFlags;
    }

    public byte getBidiCt() {
      return bidiCt;
    }

    public void setBidiCt(byte bidiCt) {
      this.bidiCt = bidiCt;
    }

    public byte getGlyphCt() {
      return glyphCt;
    }

    public void setGlyphCt(byte glyphCt) {
      this.glyphCt = glyphCt;
    }

    public short getAltiPos() {
      return altiPos;
    }

    public void setAltiPos(short altiPos) {
      this.altiPos = altiPos;
    }

    public byte[] getComplexText() {
      return complexText;
    }

    public void setComplexText(byte[] complexText) {
      this.complexText = complexText;
      this.ctLength = (complexText != null) ? complexText.length : 0;
    }
  }

  /**
   * PTOCA, Page 101. <br>The Encrypted Data control sequence contains a sequence of bytes that are
   * encrypted and must be decrypted into text strings for standard text processing.
   */
  @XmlRootElement
  public static final class ENC_EncryptedData extends PTOCAControlSequence {
    @AFPField
    int reserved4_7 = 0x00;
    @AFPField
    byte[] encryptedData;

    @Override
    public void reset() {
      super.reset();
      reserved4_7 = 0x00;
      encryptedData = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      reserved4_7 = UtilBinaryDecoding.parseInt(sfData, offset, 4);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 4) {
        encryptedData = new byte[actualLength - 4];
        System.arraycopy(sfData, offset + 4, encryptedData, 0, encryptedData.length);
      } else {
        encryptedData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
      if (encryptedData != null) {
        os.write(encryptedData);
      }
    }

    public int getReserved4_7() {
      return reserved4_7;
    }

    public void setReserved4_7(int reserved4_7) {
      this.reserved4_7 = reserved4_7;
    }

    public byte[] getEncryptedData() {
      return encryptedData;
    }

    public void setEncryptedData(byte[] encryptedData) {
      this.encryptedData = encryptedData;
    }
  }

  /**
   * PTOCA, Page 78. <br>The Set Key Information control sequence provides encryption key
   * information to be used with Encrypted Data (ENC) controls.
   */
  @XmlRootElement
  public static final class SKI_SetKeyInformation extends PTOCAControlSequence {
    @AFPField
    int reserved4_7 = 0x00;
    @AFPField
    byte[] keyInfo;

    @Override
    public void reset() {
      super.reset();
      reserved4_7 = 0x00;
      keyInfo = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      reserved4_7 = UtilBinaryDecoding.parseInt(sfData, offset, 4);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 4) {
        keyInfo = new byte[actualLength - 4];
        System.arraycopy(sfData, offset + 4, keyInfo, 0, keyInfo.length);
      } else {
        keyInfo = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (keyInfo == null || keyInfo.length <= 249) {
        os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
        if (keyInfo != null) {
          os.write(keyInfo);
        }
      } else {
        // Concatenated payload support: split into multiple chained control sequences.
        int remaining = keyInfo.length;
        int offset = 0;
        while (remaining > 0) {
          int chunkLen = Math.min(remaining, 249);
          remaining -= chunkLen;
          boolean last = (remaining == 0);

          if (offset > 0) {
            // Write chained introducer for subsequent chunks.
            // Length is chunkLen + 6 (4 reserved + 1 length byte + 1 type byte)
            os.write(chunkLen + 6);
            os.write(ControlSequenceFunctionType.SKI_SetKeyInformation.toByte(!last));
          } else {
            // Update the first chunk's CSI if it was originally thought to be the only one.
            if (!last && csi != null) {
              csi.setChained(true);
              csi.setLength((short) (chunkLen + 6));
            }
          }

          os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
          os.write(keyInfo, offset, chunkLen);
          offset += chunkLen;
        }
      }
    }

    public int getReserved4_7() {
      return reserved4_7;
    }

    public void setReserved4_7(int reserved4_7) {
      this.reserved4_7 = reserved4_7;
    }

    public byte[] getKeyInfo() {
      return keyInfo;
    }

    public void setKeyInfo(byte[] keyInfo) {
      this.keyInfo = keyInfo;
    }
  }

  /**
   * PTOCA, Page 88. <br>The Set Encrypted Alternate control sequence contains the alternate text
   * as a series of code points to be used if the decryption of the encrypted bytes in the ENC
   * control fails.
   */
  @XmlRootElement
  public static final class SEA_SetEncryptedAlternate extends PTOCAControlSequence {
    @AFPField
    int reserved4_7 = 0x00;
    @AFPField
    byte[] alternateText;
    String text;

    @Override
    public void reset() {
      super.reset();
      reserved4_7 = 0x00;
      alternateText = null;
      text = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      reserved4_7 = UtilBinaryDecoding.parseInt(sfData, offset, 4);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 4) {
        alternateText = new byte[actualLength - 4];
        System.arraycopy(sfData, offset + 4, alternateText, 0, alternateText.length);
        if (UtilCharacterEncoding.isHumanReadable(alternateText, config.getAfpCharSet())) {
          text = new String(alternateText, config.getAfpCharSet());
        }
      } else {
        alternateText = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (alternateText == null || alternateText.length <= 249) {
        os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
        if (alternateText != null) {
          os.write(alternateText);
        }
      } else {
        // Concatenated payload support: split into multiple chained control sequences.
        int remaining = alternateText.length;
        int offset = 0;
        while (remaining > 0) {
          int chunkLen = Math.min(remaining, 249);
          remaining -= chunkLen;
          boolean last = (remaining == 0);

          if (offset > 0) {
            // Write chained introducer for subsequent chunks.
            // Length is chunkLen + 6 (4 reserved + 1 length byte + 1 type byte)
            os.write(chunkLen + 6);
            os.write(ControlSequenceFunctionType.SEA_SetEncryptedAlternate.toByte(!last));
          } else {
            // Update the first chunk's CSI if it was originally thought to be the only one.
            if (!last && csi != null) {
              csi.setChained(true);
              csi.setLength((short) (chunkLen + 6));
            }
          }

          os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
          os.write(alternateText, offset, chunkLen);
          offset += chunkLen;
        }
      }
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    public int getReserved4_7() {
      return reserved4_7;
    }

    public void setReserved4_7(int reserved4_7) {
      this.reserved4_7 = reserved4_7;
    }

    public byte[] getAlternateText() {
      return alternateText;
    }

    public void setAlternateText(byte[] alternateText) {
      this.alternateText = alternateText;
    }
  }

  /** PTOCA, Page 105. <br> The Underscore control sequence identifies text fields that are to be underscored. */
  @XmlRootElement
  public static final class USC_Underscore extends PTOCAControlSequence {
    PTOCA_BypassFlag bypassFlag;

    @Override
    public void reset() {
      super.reset();
      bypassFlag = null;
    }

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

  /**
   * Represents a run of free-standing graphic characters in a PTX field.
   */
  @XmlRootElement
  public static final class GraphicCharacters extends PTOCAControlSequence {
    @AFPField
    byte[] data;
    String text;

    @Override
    public void reset() {
      super.reset();
      data = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 0) {
        text = UtilCharacterEncoding.decodeEbcdic(sfData, offset, length, config);
        data = new byte[actualLength];
        System.arraycopy(sfData, offset, data, 0, actualLength);
      } else {
        data = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (data != null) {
        os.write(data);
      }
    }

    public byte[] getData() {
      return data;
    }

    public void setData(byte[] data) {
      this.data = data;
    }
  }

  @XmlRootElement
  public static final class GLC_GlyphLayoutControl extends PTOCAControlSequence {
    short iAdvance;
    short oidLgth;
    short ffnLgth;
    byte[] fontOid;
    String ffontName;

    @Override
    public void reset() {
      super.reset();
      iAdvance = 0;
      oidLgth = 0;
      ffnLgth = 0;
      fontOid = null;
      ffontName = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
        throws AFPParserException {
      iAdvance = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      oidLgth = (short) (sfData[offset + 2] & 0xFF);
      ffnLgth = (short) (sfData[offset + 3] & 0xFF);

      if (oidLgth > 0) {
        fontOid = new byte[oidLgth];
        System.arraycopy(sfData, offset + 8, fontOid, 0, oidLgth);
      }
      if (ffnLgth > 0) {
        ffontName =
            new String(
                sfData,
                offset + 8 + (oidLgth > 0 ? oidLgth : 0),
                ffnLgth,
                java.nio.charset.StandardCharsets.UTF_16BE);
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(iAdvance, 2));
      os.write(oidLgth & 0xFF);
      os.write(ffnLgth & 0xFF);
      os.write(new byte[4]); // Reserved
      if (fontOid != null) {
        os.write(fontOid);
      }
      if (ffontName != null) {
        os.write(ffontName.getBytes(java.nio.charset.StandardCharsets.UTF_16BE));
      }
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(ffontName);
    }

    public short getIAdvance() {
      return iAdvance;
    }

    public void setIAdvance(short iAdvance) {
      this.iAdvance = iAdvance;
    }

    public short getOidLgth() {
      return oidLgth;
    }

    public void setOidLgth(short oidLgth) {
      this.oidLgth = oidLgth;
    }

    public short getFfnLgth() {
      return ffnLgth;
    }

    public void setFfnLgth(short ffnLgth) {
      this.ffnLgth = ffnLgth;
    }

    public byte[] getFontOid() {
      return fontOid;
    }

    public void setFontOid(byte[] fontOid) {
      this.fontOid = fontOid;
    }

    public String getFfontName() {
      return UtilCharacterEncoding.sanitizeForXml(ffontName);
    }

    public void setFfontName(String ffontName) {
      this.ffontName = ffontName;
    }
  }

  @XmlRootElement
  public static final class GIR_GlyphIdRun extends PTOCAControlSequence {
    int[] glyphIds;

    @Override
    public void reset() {
      super.reset();
      glyphIds = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
        throws AFPParserException {
      // PTOCA-04 (AFPC-0005-04), page 83: LL=4 + n*2. Bytes 2-3 are Reserved.
      if (length >= 2) {
        int count = (length - 2) / 2;
        glyphIds = new int[count];
        for (int i = 0; i < count; i++) {
          glyphIds[i] = UtilBinaryDecoding.parseInt(sfData, offset + 2 + i * 2, 2);
        }
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(new byte[2]); // Reserved
      if (glyphIds != null) {
        for (int id : glyphIds) {
          os.write(UtilBinaryDecoding.intToByteArray(id, 2));
        }
      }
    }

    public int[] getGlyphIds() {
      return glyphIds;
    }

    public void setGlyphIds(int[] glyphIds) {
      this.glyphIds = glyphIds;
    }
  }

  @XmlRootElement
  public static final class GAR_GlyphAdvanceRun extends PTOCAControlSequence {
    short[] advances;

    @Override
    public void reset() {
      super.reset();
      advances = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
        throws AFPParserException {
      // PTOCA-04 (AFPC-0005-04), page 82: LL=4 + n*2. Bytes 2-3 are Reserved.
      if (length >= 2) {
        int count = (length - 2) / 2;
        advances = new short[count];
        for (int i = 0; i < count; i++) {
          advances[i] = UtilBinaryDecoding.parseShort(sfData, offset + 2 + i * 2, 2);
        }
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(new byte[2]); // Reserved
      if (advances != null) {
        for (short adv : advances) {
          os.write(UtilBinaryDecoding.shortToByteArray(adv, 2));
        }
      }
    }

    public short[] getAdvances() {
      return advances;
    }

    public void setAdvances(short[] advances) {
      this.advances = advances;
    }
  }

  @XmlRootElement
  public static final class GOR_GlyphOffsetRun extends PTOCAControlSequence {
    short[] offsets;

    @Override
    public void reset() {
      super.reset();
      offsets = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
        throws AFPParserException {
      // PTOCA-04 (AFPC-0005-04), page 89: LL=4 + n*2. Bytes 2-3 are Reserved.
      if (length >= 2) {
        int count = (length - 2) / 2;
        offsets = new short[count];
        for (int i = 0; i < count; i++) {
          offsets[i] = UtilBinaryDecoding.parseShort(sfData, offset + 2 + i * 2, 2);
        }
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(new byte[2]); // Reserved
      if (offsets != null) {
        for (short off : offsets) {
          os.write(UtilBinaryDecoding.shortToByteArray(off, 2));
        }
      }
    }

    public short[] getOffsets() {
      return offsets;
    }

    public void setOffsets(short[] offsets) {
      this.offsets = offsets;
    }
  }
}
