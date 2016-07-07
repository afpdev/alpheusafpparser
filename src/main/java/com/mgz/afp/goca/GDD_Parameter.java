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
package com.mgz.afp.goca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.AFPBackgroundMix;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.enums.AFPForegroundMix;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLE_SetLineEnd.LineEnd;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLJ_SetLineJoin.LineJoin;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLT_SetLineType.LineType;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.EnumSet;


public abstract class GDD_Parameter implements IAFPDecodeableWriteable {
  public static short PARAMETERTYPE_WindowSpecification = 0xF6;
  public static short PARAMETERTYPE_DrawingOrderSubset_RETIRED = 0xF7;
  public static short PARAMETERTYPE_SetCurrentDefaultInstruction = 0x21;

  @AFPField
  short parameterType;
  @AFPField
  short lengthOfFollowingData;

  public static GDD_Parameter buildGDDParameter(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    short parameterType = UtilBinaryDecoding.parseShort(sfData, offset, 1);

    GDD_Parameter gddParameter = null;
    if (parameterType == PARAMETERTYPE_SetCurrentDefaultInstruction) {
      gddParameter = SetCurrentDefaultInstruction.buildSetCurrentDefaultInstruction(sfData, offset, length, config);
    } else if (parameterType == PARAMETERTYPE_WindowSpecification) {
      gddParameter = new WindowSpecification();
    } else if (parameterType == PARAMETERTYPE_DrawingOrderSubset_RETIRED) {
      gddParameter = new DrawingOrderSubsetParameterRetired();
    }

    if (gddParameter != null) {
      gddParameter.decodeAFP(sfData, offset, length, config);
    } else {
      throw new AFPParserException("GDD parameter type 0x" + Integer.toHexString(parameterType) + " is unknown.");
    }

    return gddParameter;
  }

  public static SetCurrentDefaultInstruction buildSetCurrentDefaultInstruction(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    short attributeType = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);

    SetCurrentDefaultInstruction setCurrentDefaultInstruction = null;
    if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_DRAWING) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.DrawingAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_LINE) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.LineAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_Character) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.CharacterAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_Marker) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.MarkerAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_PATTERN) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.PatternAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_ARC) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.ArcParameters();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_PROZESSCOLOR) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.ProcessColorAttributes();
    } else if (attributeType == SetCurrentDefaultInstruction.ATTRIBUTETYPE_NORMALLINEWIDTH) {
      setCurrentDefaultInstruction = new SetCurrentDefaultInstruction.NormalLineWidthAttribute();
    }

    if (setCurrentDefaultInstruction != null) {
      setCurrentDefaultInstruction.decodeAFP(sfData, offset, length, config);
    } else {
      throw new AFPParserException("The attribute type 0x" + Integer.toHexString(attributeType) + " of GDD parameter SetCurrentDefaultInstruction is unknown.");
    }

    return setCurrentDefaultInstruction;
  }

  /**
   * Returns the type code of this {@link GDD_Parameter}. This values is set by the sub-type of
   * {@link GDD_Parameter}.
   *
   * @return type code of this {@link GDD_Parameter}.
   */
  public short getParameterType() {
    return parameterType;
  }

  /**
   * Returns the length of following data. This values is set by the sub-type of {@link
   * GDD_Parameter}.
   *
   * @return length of following data.
   */
  public short getLengthOfFollowingData() {
    return lengthOfFollowingData;
  }

  public void setLengthOfFollowingData(short lengthOfFollowingData) {
    this.lengthOfFollowingData = lengthOfFollowingData;
  }

  /**
   * GOCA, page 161.<br><br> Defaults can be set by the appropriate Set Current Defaults
   * instructions. For a complete description of this instruction, see “Set Current Defaults (SCD)
   * Instruction” on page 60. Each occurrence of the Set Current Defaults instruction specifies a
   * particular attribute set. The following tables show the maximum set of attributes allowed.
   * Subsets of these attribute sets are also allowed, using the MASK bits as selectors for
   * attributes in the particular attribute set. The format of the attribute sets is described in
   * “Set Current Defaults (SCD) Instruction” on page 60. In the tables below, two possibilities
   * exist. If the FLAG byte equals X'8F', the LENGTH byte would be specified as the second value
   * shown, and the values shown in bytes 6-n would be specified as shown. If the FLAG byte instead
   * equals X'0F', the LENGTH byte would be specified as the first value shown (that is, 4), and
   * bytes 6-n would not be specified.
   */
  public abstract static class SetCurrentDefaultInstruction extends GDD_Parameter {
    public static final short ATTRIBUTETYPE_DRAWING = 0x00;
    public static final short ATTRIBUTETYPE_LINE = 0x01;
    public static final short ATTRIBUTETYPE_Character = 0x02;
    public static final short ATTRIBUTETYPE_Marker = 0x03;
    public static final short ATTRIBUTETYPE_PATTERN = 0x04;
    public static final short ATTRIBUTETYPE_ARC = 0x0B;
    public static final short ATTRIBUTETYPE_PROZESSCOLOR = 0x10;
    public static final short ATTRIBUTETYPE_NORMALLINEWIDTH = 0x11;

    @AFPField(isEditable = false)
    short attributeType;
    @AFPField
    BitSet mask;
    @AFPField
    SetCurrentDefaultInstruction.Flag flag;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      parameterType = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      attributeType = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      mask = UtilBinaryDecoding.parseBitSet(sfData, offset + 3, 2);
      flag = Flag.valueOf(sfData[offset + 5]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(parameterType);
      os.write(lengthOfFollowingData);
      os.write(attributeType);
      os.write(UtilBinaryDecoding.bitSetToByteArray(mask, 2));
      os.write(flag.toByte());
    }

    public short getAttributeType() {
      return attributeType;
    }

    public BitSet getMask() {
      return mask;
    }

    public void setMask(byte[] mask) {
      this.mask = UtilBinaryDecoding.parseBitSet(mask, 0, Math.min(2, mask.length));
    }

    public void setMask(BitSet mask) {
      this.mask = mask;
    }

    public SetCurrentDefaultInstruction.Flag getFlag() {
      return flag;
    }

    public void setFlag(SetCurrentDefaultInstruction.Flag flag) {
      this.flag = flag;
    }

    public enum Flag {
      UseStandardDefault,
      UseValuesAsSpecified;

      public static SetCurrentDefaultInstruction.Flag valueOf(byte code) {
        if (code == 0x0F) return UseStandardDefault;
        else return UseValuesAsSpecified;
      }

      public int toByte() {
        if (this == UseStandardDefault) return 0x0F;
        else return 0x8F;
      }
    }

    public static class DrawingAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      AFPColorValue color;
      @AFPField
      AFPForegroundMix foregroundMix;
      @AFPField
      AFPBackgroundMix backgroundMix;

      public DrawingAttributes() {
        this.attributeType = ATTRIBUTETYPE_DRAWING;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);

        int actualLength = StructuredField.getActualLength(sfData, offset, length);

        if (flag == Flag.UseValuesAsSpecified) {
          int pos = 6;
          if (mask.get(0) && pos < actualLength) {
            color = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + pos, 2));
            pos += 2;
          } else {
            color = null;
          }
          foregroundMix = mask.get(2) && pos < actualLength ? AFPForegroundMix.valueOf(sfData[offset + pos++]) : null;
          backgroundMix = mask.get(3) && pos < actualLength ? AFPBackgroundMix.valueOf(sfData[offset + pos++]) : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0))
            baos.write(color != null ? color.toByte2() : AFPColorValue.DeviceDefault_0xFF00.toByte2());
          if (mask.get(2))
            baos.write(foregroundMix != null ? foregroundMix.toByte() : 0x00);
          if (mask.get(3))
            baos.write(backgroundMix != null ? backgroundMix.toByte() : 0x00);
          attributeData = baos.toByteArray();
        }
        this.lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;

        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public AFPColorValue getColor() {
        return color;
      }

      public void setColor(AFPColorValue color) {
        this.color = color;
        mask.set(0, color != null);
      }

      public AFPForegroundMix getForegroundMix() {
        return foregroundMix;
      }

      public void setForegroundMix(AFPForegroundMix foregroundMix) {
        this.foregroundMix = foregroundMix;
        mask.set(2, foregroundMix != null);
      }

      public AFPBackgroundMix getBackgroundMix() {
        return backgroundMix;
      }

      public void setBackgroundMix(AFPBackgroundMix backgroundMix) {
        this.backgroundMix = backgroundMix;
        mask.set(3, backgroundMix != null);
      }
    }

    public static class LineAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      LineType lineType;
      @AFPField
      Short lineWidth;
      @AFPField
      LineEnd lineEnd;
      @AFPField
      LineJoin lineJoin;

      public LineAttributes() {
        this.attributeType = ATTRIBUTETYPE_LINE;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);

        int actualLength = StructuredField.getActualLength(sfData, offset, length);

        if (flag == Flag.UseValuesAsSpecified) {
          int pos = 6;
          lineType = mask.get(0) && pos < actualLength ? LineType.valueOf(sfData[offset + pos++]) : null;
          lineWidth = mask.get(1) && pos < actualLength ? UtilBinaryDecoding.parseShort(sfData, offset + pos++, 1) : null;
          lineEnd = mask.get(2) && pos < actualLength ? LineEnd.valueOf(sfData[offset + pos++]) : null;
          lineJoin = mask.get(3) && pos < actualLength ? LineJoin.valueOf(sfData[offset + pos++]) : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0))
            baos.write(lineType != null ? lineType.toByte() : LineType.Default.toByte());
          if (mask.get(1)) baos.write(lineWidth != null ? lineWidth : 0x00);
          if (mask.get(2))
            baos.write(lineEnd != null ? lineEnd.toByte() : LineEnd.Default.toByte());
          if (mask.get(3))
            baos.write(lineJoin != null ? lineJoin.toByte() : LineJoin.Default.toByte());
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;

        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public LineType getLineType() {
        return lineType;
      }

      public void setLineType(LineType lineType) {
        this.lineType = lineType;
        mask.set(0, lineType != null);
      }

      public Short getLineWidth() {
        return lineWidth;
      }

      public void setLineWidth(Short lineWidth) {
        this.lineWidth = lineWidth;
        mask.set(1, lineWidth != null);
      }

      public LineEnd getLineEnd() {
        return lineEnd;
      }

      public void setLineEnd(LineEnd lineEnd) {
        this.lineEnd = lineEnd;
        mask.set(2, lineEnd != null);
      }

      public LineJoin getLineJoin() {
        return lineJoin;
      }

      public void setLineJoin(LineJoin lineJoin) {
        this.lineJoin = lineJoin;
        mask.set(3, lineJoin != null);
      }
    }

    public static class CharacterAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      Long characterAngleXY;
      @AFPField
      Long characterCellSizeWH;
      @AFPField
      Byte characterDirection;
      @AFPField
      Byte characterPrecision;
      @AFPField
      Short characterSet;
      @AFPField
      Long characterShearXY;

      public CharacterAttributes() {
        this.attributeType = ATTRIBUTETYPE_Character;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        int actualLength = StructuredField.getActualLength(sfData, offset, length);
        if (flag == Flag.UseValuesAsSpecified) {
          int pos = 6;
          if (mask.get(0) && pos < actualLength) {
            characterAngleXY = UtilBinaryDecoding.parseLong(sfData, offset + pos, 4);
            pos += 4;
          } else {
            characterAngleXY = null;
          }
          if (mask.get(1) && pos < actualLength) {
            characterCellSizeWH = UtilBinaryDecoding.parseLong(sfData, offset + pos, 4);
            pos += 4;
          } else {
            characterCellSizeWH = null;
          }
          characterDirection = mask.get(2) ? sfData[offset + pos++] : null;
          characterPrecision = mask.get(3) ? sfData[offset + pos++] : null;
          characterSet = mask.get(4) ? UtilBinaryDecoding.parseShort(sfData, offset + pos++, 1) : null;
          characterShearXY = mask.get(5) ? UtilBinaryDecoding.parseLong(sfData, offset + pos, 4) : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0))
            baos.write(characterAngleXY != null ? UtilBinaryDecoding.longToByteArray(characterAngleXY, 4) : Constants.EMPTYBYTES_4);
          if (mask.get(1))
            baos.write(characterCellSizeWH != null ? UtilBinaryDecoding.longToByteArray(characterCellSizeWH, 4) : Constants.EMPTYBYTES_4);
          if (mask.get(2))
            baos.write(characterDirection != null ? characterDirection : 0x00);
          if (mask.get(3))
            baos.write(characterPrecision != null ? characterPrecision : 0x00);
          if (mask.get(4)) baos.write(characterSet != null ? characterSet : 0x00);
          if (mask.get(5))
            baos.write(characterShearXY != null ? UtilBinaryDecoding.longToByteArray(characterShearXY, 4) : Constants.EMPTYBYTES_4);
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;
        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public Long getCharacterAngleXY() {
        return characterAngleXY;
      }

      public void setCharacterAngleXY(Long characterAngleXY) {
        this.characterAngleXY = characterAngleXY;
        mask.set(0, characterAngleXY != null);
      }

      public Long getCharacterCellSizeWH() {
        return characterCellSizeWH;
      }

      public void setCharacterCellSizeWH(Long characterCellSizeWH) {
        this.characterCellSizeWH = characterCellSizeWH;
        mask.set(1, characterCellSizeWH != null);
      }

      public Byte getCharacterDirection() {
        return characterDirection;
      }

      public void setCharacterDirection(Byte characterDirection) {
        this.characterDirection = characterDirection;
        mask.set(2, characterDirection != null);
      }

      public Byte getCharacterPrecision() {
        return characterPrecision;
      }

      public void setCharacterPrecision(Byte characterPrecision) {
        this.characterPrecision = characterPrecision;
        mask.set(3, characterPrecision != null);
      }

      public Short getCharacterSet() {
        return characterSet;
      }

      public void setCharacterSet(Short characterSet) {
        this.characterSet = characterSet;
        mask.set(4, characterSet != null);
      }

      public Long getCharacterShearXY() {
        return characterShearXY;
      }

      public void setCharacterShearXY(Long characterShearXY) {
        this.characterShearXY = characterShearXY;
        mask.set(5, characterShearXY != null);
      }
    }

    public static class MarkerAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      Byte markerPrecision;
      @AFPField
      Byte markerSet;
      @AFPField
      Byte markerSymbol;

      public MarkerAttributes() {
        this.attributeType = ATTRIBUTETYPE_Marker;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        int actualLength = StructuredField.getActualLength(sfData, offset, length);
        if (flag == Flag.UseValuesAsSpecified) {
          int pos = 6;
          markerPrecision = mask.get(0) && pos < actualLength ? sfData[offset + pos++] : null;
          markerSet = mask.get(1) && pos < actualLength ? sfData[offset + pos++] : null;
          markerSymbol = mask.get(2) && pos < actualLength ? sfData[offset + pos++] : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0)) baos.write(markerPrecision != null ? markerPrecision : 0x00);
          if (mask.get(1)) baos.write(markerSet != null ? markerSet : 0x00);
          if (mask.get(2)) baos.write(markerSymbol != null ? markerSymbol : 0x00);
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;

        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public Byte getMarkerPrecision() {
        return markerPrecision;
      }

      public void setMarkerPrecision(Byte markerPrecision) {
        this.markerPrecision = markerPrecision;
        mask.set(0, markerPrecision != null);
      }

      public Byte getMarkerSet() {
        return markerSet;
      }

      public void setMarkerSet(Byte markerSet) {
        this.markerSet = markerSet;
        mask.set(1, markerSet != null);
      }

      public Byte getMarkerSymbol() {
        return markerSymbol;
      }

      public void setMarkerSymbol(Byte markerSymbol) {
        this.markerSymbol = markerSymbol;
        mask.set(2, markerSymbol != null);
      }
    }

    public static class PatternAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      Byte patternSet;
      @AFPField
      Byte patternSymbol;

      public PatternAttributes() {
        this.attributeType = ATTRIBUTETYPE_PATTERN;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        int actualLength = StructuredField.getActualLength(sfData, offset, length);
        if (flag == Flag.UseValuesAsSpecified) {
          int pos = 6;
          patternSet = mask.get(0) && pos < actualLength ? sfData[offset + pos++] : null;
          patternSymbol = mask.get(1) && pos < actualLength ? sfData[offset + pos++] : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0)) baos.write(patternSet != null ? patternSet : 0x00);
          if (mask.get(1)) baos.write(patternSymbol != null ? patternSymbol : 0x00);
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;
        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public Byte getPatternSet() {
        return patternSet;
      }

      public void setPatternSet(Byte patternSet) {
        this.patternSet = patternSet;
        mask.set(0, patternSet != null);
      }

      public Byte getPatternSymbol() {
        return patternSymbol;
      }

      public void setPatternSymbol(Byte patternSymbol) {
        this.patternSymbol = patternSymbol;
        mask.set(1, patternSymbol != null);
      }
    }

    public static class ArcParameters extends SetCurrentDefaultInstruction {
      @AFPField
      Short arcTransformP;
      @AFPField
      Short arcTransformQ;
      @AFPField
      Short arcTransformR;
      @AFPField
      Short arcTransformS;

      public ArcParameters() {
        this.attributeType = ATTRIBUTETYPE_ARC;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        if (flag == Flag.UseValuesAsSpecified) {
          int actualLength = StructuredField.getActualLength(sfData, offset, length);
          int pos = 6;
          if (mask.get(0) && pos < actualLength) {
            arcTransformP = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2);
            pos += 2;
          } else {
            arcTransformP = null;
          }
          if (mask.get(1) && pos < actualLength) {
            arcTransformQ = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2);
            pos += 2;
          } else {
            arcTransformQ = null;
          }
          if (mask.get(2) && pos < actualLength) {
            arcTransformR = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2);
            pos += 2;
          } else {
            arcTransformR = null;
          }
          if (mask.get(3) && pos < actualLength) {
            arcTransformS = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2);
            pos += 2;
          } else {
            arcTransformS = null;
          }
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0))
            baos.write(arcTransformP != null ? UtilBinaryDecoding.shortToByteArray(arcTransformP, 2) : Constants.EMPTYBYTES_2);
          if (mask.get(1))
            baos.write(arcTransformQ != null ? UtilBinaryDecoding.shortToByteArray(arcTransformQ, 2) : Constants.EMPTYBYTES_2);
          if (mask.get(2))
            baos.write(arcTransformR != null ? UtilBinaryDecoding.shortToByteArray(arcTransformR, 2) : Constants.EMPTYBYTES_2);
          if (mask.get(3))
            baos.write(arcTransformS != null ? UtilBinaryDecoding.shortToByteArray(arcTransformS, 2) : Constants.EMPTYBYTES_2);
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;
        super.writeAFP(os, config);
        os.write(attributeData);
      }

      public Short getArcTransformP() {
        return arcTransformP;
      }

      public void setArcTransformP(Short arcTransformP) {
        this.arcTransformP = arcTransformP;
        mask.set(0, arcTransformP != null);
      }

      public Short getArcTransformQ() {
        return arcTransformQ;
      }

      public void setArcTransformQ(Short arcTransformQ) {
        this.arcTransformQ = arcTransformQ;
        mask.set(1, arcTransformQ != null);
      }

      public Short getArcTransformR() {
        return arcTransformR;
      }

      public void setArcTransformR(Short arcTransformR) {
        this.arcTransformR = arcTransformR;
        mask.set(2, arcTransformR != null);
      }

      public Short getArcTransformS() {
        return arcTransformS;
      }

      public void setArcTransformS(Short arcTransformS) {
        this.arcTransformS = arcTransformS;
        mask.set(3, arcTransformS != null);
      }
    }

    public static class ProcessColorAttributes extends SetCurrentDefaultInstruction {
      @AFPField
      AFPForegroundMix foregroundMix;
      @AFPField
      AFPBackgroundMix backgroundMix;
      @AFPField(minSize = 12, maxSize = 14)
      byte[] processColor;

      public ProcessColorAttributes() {
        attributeType = ATTRIBUTETYPE_PROZESSCOLOR;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        if (flag == Flag.UseValuesAsSpecified) {
          int actualLength = StructuredField.getActualLength(sfData, offset, length);
          int pos = 6;
          foregroundMix = mask.get(0) && pos < actualLength ? AFPForegroundMix.valueOf(sfData[offset + pos++]) : null;
          backgroundMix = mask.get(1) && pos < actualLength ? AFPBackgroundMix.valueOf(sfData[offset + pos++]) : null;
          if (mask.get(2) && pos < actualLength) {
            processColor = new byte[lengthOfFollowingData - 7 + 1];
            System.arraycopy(sfData, offset + 8, processColor, 0, processColor.length);
          } else {
            processColor = null;
          }
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        byte[] attributeData = null;
        if (flag == Flag.UseValuesAsSpecified) {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          if (mask.get(0))
            baos.write(foregroundMix != null ? foregroundMix.toByte() : AFPForegroundMix.Default.toByte());
          if (mask.get(1))
            baos.write(backgroundMix != null ? backgroundMix.toByte() : AFPBackgroundMix.Default.toByte());
          if (mask.get(2))
            baos.write(processColor != null ? processColor : Constants.EMPTYBYTES_3);
          attributeData = baos.toByteArray();
        }
        lengthOfFollowingData = attributeData != null ? (short) (4 + attributeData.length) : 4;

        super.writeAFP(os, config);
        if (attributeData != null) os.write(attributeData);
      }

      public AFPForegroundMix getForegroundMix() {
        return foregroundMix;
      }

      public void setForegroundMix(AFPForegroundMix foregroundMix) {
        this.foregroundMix = foregroundMix;
        mask.set(0, foregroundMix != null);
      }

      public AFPBackgroundMix getBackgroundMix() {
        return backgroundMix;
      }

      public void setBackgroundMix(AFPBackgroundMix backgroundMix) {
        this.backgroundMix = backgroundMix;
        mask.set(1, backgroundMix != null);
      }

      public byte[] getProcessColor() {
        return processColor;
      }

      public void setProcessColor(byte[] processColor) {
        this.processColor = processColor;
        mask.set(2, processColor != null);
      }
    }

    public static class NormalLineWidthAttribute extends SetCurrentDefaultInstruction {
      @AFPField
      Integer normalLineWidth;

      public NormalLineWidthAttribute() {
        attributeType = ATTRIBUTETYPE_NORMALLINEWIDTH;
      }

      @Override
      public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        super.decodeAFP(sfData, offset, length, config);
        if (flag == Flag.UseValuesAsSpecified) {
          normalLineWidth = mask.get(0) ? UtilBinaryDecoding.parseInt(sfData, offset + 6, 2) : null;
        }
      }

      @Override
      public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
        if (flag == Flag.UseValuesAsSpecified && mask.get(0)) {
          lengthOfFollowingData = 6;
        } else {
          lengthOfFollowingData = 4;
        }

        super.writeAFP(os, config);
        if (flag == Flag.UseValuesAsSpecified && mask.get(0)) {
          os.write(UtilBinaryDecoding.intToByteArray(normalLineWidth != null ? normalLineWidth : 0, 2));
        }
      }

      public Integer getNormalLineWidth() {
        return normalLineWidth;
      }

      public void setNormalLineWidth(Integer normalLineWidth) {
        this.normalLineWidth = normalLineWidth;
        mask.set(0, normalLineWidth != null);
      }
    }
  }

  public static class WindowSpecification extends GDD_Parameter {
    static final int PARAMETERTYPE_WINDOWSSPECIFICATION = 0xF6;
    @AFPField
    WindowSpecification.WindowSpecificationFlag flags;
    @AFPField
    short reserved3 = 0x00;
    @AFPField
    short geometricParameterFormat = 0x00;
    @AFPField
    AFPUnitBase unitBaseGPS;
    @AFPField
    int unitsPerUnitBaseX;
    @AFPField
    int unitsPerUnitBaseY;
    @AFPField
    int imageResolutionXY;
    @AFPField
    int leftEdgeOfGPSWindow;
    @AFPField
    int rightEdgeOfGPSWindow;
    @AFPField
    int bottomEdgeOfGPSWindow;
    @AFPField
    int topEdgeOfGPSWindow;
    @AFPField(maxSize = 4, isOptional = true)
    byte[] reservedBytesObsolete;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      parameterType = PARAMETERTYPE_WINDOWSSPECIFICATION;
      lengthOfFollowingData = sfData[offset + 1];
      unitBaseGPS = AFPUnitBase.valueOf(sfData[offset + 2]);
      reserved3 = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      geometricParameterFormat = UtilBinaryDecoding.parseShort(sfData, offset + 4, 1);
      unitBaseGPS = AFPUnitBase.valueOf(sfData[offset + 5]);
      unitsPerUnitBaseX = UtilBinaryDecoding.parseInt(sfData, offset + 6, 2);
      unitsPerUnitBaseY = UtilBinaryDecoding.parseInt(sfData, offset + 8, 2);
      imageResolutionXY = UtilBinaryDecoding.parseInt(sfData, offset + 10, 2);
      leftEdgeOfGPSWindow = UtilBinaryDecoding.parseInt(sfData, offset + 12, 2);
      rightEdgeOfGPSWindow = UtilBinaryDecoding.parseInt(sfData, offset + 14, 2);
      bottomEdgeOfGPSWindow = UtilBinaryDecoding.parseInt(sfData, offset + 16, 2);
      topEdgeOfGPSWindow = UtilBinaryDecoding.parseInt(sfData, offset + 18, 2);

      if (lengthOfFollowingData >= 22) {
        // Obsolete bytes are present. See. page 159 of GOCA
        reservedBytesObsolete = new byte[4];
        System.arraycopy(sfData, offset + 20, reservedBytesObsolete, 0, 4);
      } else {
        reservedBytesObsolete = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = reservedBytesObsolete != null ? (short) (22 + reservedBytesObsolete.length) : 22;
      os.write(parameterType);
      os.write(lengthOfFollowingData);
      os.write(reserved3);
      os.write(unitBaseGPS.toByte());
      os.write(UtilBinaryDecoding.intToByteArray(unitsPerUnitBaseX, 2));
      os.write(UtilBinaryDecoding.intToByteArray(unitsPerUnitBaseY, 2));
      os.write(UtilBinaryDecoding.intToByteArray(imageResolutionXY, 2));
      os.write(UtilBinaryDecoding.intToByteArray(leftEdgeOfGPSWindow, 2));
      os.write(UtilBinaryDecoding.intToByteArray(rightEdgeOfGPSWindow, 2));
      os.write(UtilBinaryDecoding.intToByteArray(bottomEdgeOfGPSWindow, 2));
      os.write(UtilBinaryDecoding.intToByteArray(topEdgeOfGPSWindow, 2));
      if (reservedBytesObsolete != null) {
        os.write(reservedBytesObsolete);
      }
    }

    public WindowSpecification.WindowSpecificationFlag getFlags() {
      return flags;
    }

    public void setFlags(WindowSpecification.WindowSpecificationFlag flags) {
      this.flags = flags;
    }

    public short getReserved3() {
      return reserved3;
    }

    public void setReserved3(short reserved3) {
      this.reserved3 = reserved3;
    }

    public short getGeometricParameterFormat() {
      return geometricParameterFormat;
    }

    public void setGeometricParameterFormat(short geometricParameterFormat) {
      this.geometricParameterFormat = geometricParameterFormat;
    }

    public AFPUnitBase getUnitBaseGPS() {
      return unitBaseGPS;
    }

    public void setUnitBaseGPS(AFPUnitBase unitBaseGPS) {
      this.unitBaseGPS = unitBaseGPS;
    }

    public int getUnitsPerUnitBaseX() {
      return unitsPerUnitBaseX;
    }

    public void setUnitsPerUnitBaseX(int unitsPerUnitBaseX) {
      this.unitsPerUnitBaseX = unitsPerUnitBaseX;
    }

    public int getUnitsPerUnitBaseY() {
      return unitsPerUnitBaseY;
    }

    public void setUnitsPerUnitBaseY(int unitsPerUnitBaseY) {
      this.unitsPerUnitBaseY = unitsPerUnitBaseY;
    }

    public int getImageResolutionXY() {
      return imageResolutionXY;
    }

    public void setImageResolutionXY(int imageResolutionXY) {
      this.imageResolutionXY = imageResolutionXY;
    }

    public int getLeftEdgeOfGPSWindow() {
      return leftEdgeOfGPSWindow;
    }

    public void setLeftEdgeOfGPSWindow(int leftEdgeOfGPSWindow) {
      this.leftEdgeOfGPSWindow = leftEdgeOfGPSWindow;
    }

    public int getRightEdgeOfGPSWindow() {
      return rightEdgeOfGPSWindow;
    }

    public void setRightEdgeOfGPSWindow(int rightEdgeOfGPSWindow) {
      this.rightEdgeOfGPSWindow = rightEdgeOfGPSWindow;
    }

    public int getBottomEdgeOfGPSWindow() {
      return bottomEdgeOfGPSWindow;
    }

    public void setBottomEdgeOfGPSWindow(int bottomEdgeOfGPSWindow) {
      this.bottomEdgeOfGPSWindow = bottomEdgeOfGPSWindow;
    }

    public int getTopEdgeOfGPSWindow() {
      return topEdgeOfGPSWindow;
    }

    public void setTopEdgeOfGPSWindow(int topEdgeOfGPSWindow) {
      this.topEdgeOfGPSWindow = topEdgeOfGPSWindow;
    }

    public byte[] getReservedBytesObsolete() {
      return reservedBytesObsolete;
    }

    public void setReservedBytesObsolete(byte[] reservedBytesObsolete) {
      this.reservedBytesObsolete = reservedBytesObsolete;
      lengthOfFollowingData = reservedBytesObsolete != null ? (short) (22 + reservedBytesObsolete.length) : 22;
    }

    public enum WindowSpecificationFlag implements IMutualExclusiveGroupedFlag {
      PicturePresentationSpace_2D(0),
      PicturePresentationSpace_Undefined(0),
      PictureDimensions_Undefined(1),
      PictureDimensions_Absolute(1),
      PictureResoulution_NotDefined_or_NonSymetric(2),
      PictureResoulution_XYEqual_DefinedByIMXYRES(2),
      SymetricImage_PictureResoulution_NotDefined_or_Symetric(3),
      SymetricImage_PictureResoulution_120x140dpi(3);
      ;

      int group;

      WindowSpecificationFlag(int group) {
        this.group = group;
      }

      public static EnumSet<WindowSpecification.WindowSpecificationFlag> valueOf(byte flagByte) {
        EnumSet<WindowSpecification.WindowSpecificationFlag> result = EnumSet.noneOf(WindowSpecification.WindowSpecificationFlag.class);

        if ((flagByte & 0x80) == 0) result.add(PicturePresentationSpace_2D);
        else result.add(PicturePresentationSpace_Undefined);
        if ((flagByte & 0x40) == 0) result.add(PictureDimensions_Undefined);
        else result.add(PictureDimensions_Absolute);
        if ((flagByte & 0x10) == 0)
          result.add(PictureResoulution_NotDefined_or_NonSymetric);
        else result.add(PictureResoulution_XYEqual_DefinedByIMXYRES);
        if ((flagByte & 0x08) == 0)
          result.add(SymetricImage_PictureResoulution_NotDefined_or_Symetric);
        else result.add(SymetricImage_PictureResoulution_120x140dpi);

        return result;
      }

      public static int toByte(EnumSet<WindowSpecification.WindowSpecificationFlag> controlFlags) {
        int result = 0;

        if (controlFlags.contains(PicturePresentationSpace_Undefined)) result |= 0x80;
        if (controlFlags.contains(PictureDimensions_Absolute)) result |= 0x40;
        if (controlFlags.contains(PictureResoulution_XYEqual_DefinedByIMXYRES))
          result |= 0x10;
        if (controlFlags.contains(SymetricImage_PictureResoulution_120x140dpi))
          result |= 0x08;

        return result;
      }

      public static void setFlag(EnumSet<WindowSpecification.WindowSpecificationFlag> set, WindowSpecification.WindowSpecificationFlag flag) {
        new MutualExclusiveGroupedFlagHandler<WindowSpecification.WindowSpecificationFlag>().setFlag(set, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }

  }

  public static class DrawingOrderSubsetParameterRetired extends GDD_Parameter {
    @AFPField
    short drawingOrderSubset;
    @AFPField(size = 2)
    byte[] reserved3_4;
    @AFPField
    short subsetLevel;
    @AFPField
    short version;
    @AFPField
    short lengthOfFollowingField;
    @AFPField
    short coordinateFormat;

    public DrawingOrderSubsetParameterRetired() {
      parameterType = PARAMETERTYPE_DrawingOrderSubset_RETIRED;
      lengthOfFollowingData = 0x07;
      drawingOrderSubset = 0xB0;
      reserved3_4 = new byte[]{0x00, 0x00};
      subsetLevel = 0x02;
      version = 0x00;
      lengthOfFollowingField = 0x01;
      coordinateFormat = 0x00;
    }


    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      parameterType = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      drawingOrderSubset = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3_4 = new byte[2];
      System.arraycopy(sfData, offset + 3, reserved3_4, 0, reserved3_4.length);
      subsetLevel = UtilBinaryDecoding.parseShort(sfData, offset + 5, 1);
      version = UtilBinaryDecoding.parseShort(sfData, offset + 6, 1);
      lengthOfFollowingField = UtilBinaryDecoding.parseShort(sfData, offset + 7, 1);
      coordinateFormat = UtilBinaryDecoding.parseShort(sfData, offset + 8, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(parameterType);
      os.write(lengthOfFollowingData);
      os.write(drawingOrderSubset);
      os.write(reserved3_4);
      os.write(subsetLevel);
      os.write(version);
      os.write(lengthOfFollowingField);
      os.write(coordinateFormat);
    }
  }

}