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
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.List;

/**
 * Font Control (FNC).
 */
public class FNC_FontControl extends StructuredField {
  public static final byte FNP_REPEATING_GROUP_LENGTH_DEFAULT = 0x16;
  public static final byte FNN_REPEATING_GROUP_LENGTH_DEFAULT = 0x0C;
  public static final byte FNO_REPEATING_GROUP_LENGTH_DEFAULT = 0x1A;

  @AFPField
  private byte retired0 = 0x01;
  @AFPField
  private PatternTechnologyIdentifier patternTechnologyIdentifier;
  @AFPField
  private byte reserved2 = 0x00;
  @AFPField
  private EnumSet<FncFontUseFlag> fontUseFlags;
  @AFPField
  private FontUnitBase xUnitBase;
  @AFPField
  private FontUnitBase yUnitBase;
  @AFPField
  private short xUnitsPerUnitBase;
  @AFPField
  private short yUnitsPerUnitBase;
  @AFPField
  private short maxCharacterBoxWidth;
  @AFPField
  private short maxCharacterBoxHeight;
  @AFPField
  private byte fnoRepeatingGroupLength = FNO_REPEATING_GROUP_LENGTH_DEFAULT;
  @AFPField
  private short fniRepeatingGroupLength;
  @AFPField
  private RasterPatternDataAlignment rasterPatternDataAlignment;
  @AFPField
  private int rasterPatternDataCount;
  @AFPField
  private byte fnpRepeatingGroupLength = FNP_REPEATING_GROUP_LENGTH_DEFAULT;
  @AFPField
  private byte fnmRepeatingGroupLength;
  @AFPField
  private byte shapeResolutionXUnitBase10Inches = 0x00;
  @AFPField
  private byte shapeResolutionYUnitBase10Inches = 0x00;
  @AFPField
  private short shapeResolutionXUnitsPerUnitBase;
  @AFPField
  private short shapeResolutionYUnitsPerUnitBase;
  @AFPField
  private long outlinePatternDataCount;
  @AFPField(size = 3)
  private byte[] reserved32_34 = {0x00, 0x00, 0x00};
  @AFPField
  private byte fnnRepeatingGroupLength = FNN_REPEATING_GROUP_LENGTH_DEFAULT;
  @AFPField
  private long fnnDataCount;
  @AFPField
  private int fnnIbmNameGcgidCount;
  @AFPField
  private List<Triplet> triplets;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 22);

    var actualLength = getActualLength(sfData, offset, length);

    patternTechnologyIdentifier = PatternTechnologyIdentifier.valueOf(sfData[offset + 1]);
    fontUseFlags = FncFontUseFlag.valueOf(sfData[offset + 3]);
    xUnitBase = FontUnitBase.valueOf(sfData[offset + 4]);
    yUnitBase = FontUnitBase.valueOf(sfData[offset + 5]);
    xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
    yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 8, 2);
    maxCharacterBoxWidth = UtilBinaryDecoding.parseShort(sfData, offset + 10, 2);
    maxCharacterBoxHeight = UtilBinaryDecoding.parseShort(sfData, offset + 12, 2);
    fnoRepeatingGroupLength = sfData[offset + 14];
    fniRepeatingGroupLength = (short) (sfData[offset + 15] & 0xFF);
    rasterPatternDataAlignment = RasterPatternDataAlignment.valueOf(sfData[offset + 16]);
    rasterPatternDataCount = UtilBinaryDecoding.parseInt(sfData, offset + 17, 3);
    fnpRepeatingGroupLength = sfData[offset + 20];
    fnmRepeatingGroupLength = sfData[offset + 21];

    if (actualLength >= 26) {
      shapeResolutionXUnitBase10Inches = sfData[offset + 22];
      shapeResolutionYUnitBase10Inches = sfData[offset + 23];
      shapeResolutionXUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 24, 2);
    }

    if (actualLength >= 28) {
      shapeResolutionYUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 26, 2);
    }

    if (actualLength >= 32) {
      outlinePatternDataCount = UtilBinaryDecoding.parseLong(sfData, offset + 28, 4);
    }

    if (actualLength >= 42) {
      fnnRepeatingGroupLength = sfData[offset + 35];
      fnnDataCount = UtilBinaryDecoding.parseLong(sfData, offset + 36, 4);
      fnnIbmNameGcgidCount = UtilBinaryDecoding.parseInt(sfData, offset + 40, 2);
    }

    if (actualLength >= 43) {
      triplets = TripletParser.parseTriplets(sfData, offset + 42, actualLength - 42, config);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var baos = new ByteArrayOutputStream();

    baos.write(retired0);
    baos.write(patternTechnologyIdentifier != null ? patternTechnologyIdentifier.toByte() : 0x05);
    baos.write(reserved2);
    baos.write(FncFontUseFlag.toByte(fontUseFlags));
    baos.write(xUnitBase != null ? xUnitBase.toByte() : 0x00);
    baos.write(yUnitBase != null ? yUnitBase.toByte() : 0x00);
    baos.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(maxCharacterBoxWidth, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(maxCharacterBoxHeight, 2));
    baos.write(fnoRepeatingGroupLength);
    baos.write(UtilBinaryDecoding.shortToByteArray(fniRepeatingGroupLength, 1));
    baos.write(rasterPatternDataAlignment != null ? rasterPatternDataAlignment.toByte() : (byte) 0x00);
    baos.write(UtilBinaryDecoding.intToByteArray(rasterPatternDataCount, 3));
    baos.write(fnpRepeatingGroupLength);
    baos.write(fnmRepeatingGroupLength);

    // Optional data:
    if (xUnitsPerUnitBase != 0 || yUnitsPerUnitBase != 0 || shapeResolutionXUnitsPerUnitBase != 0 || shapeResolutionYUnitsPerUnitBase != 0
        || outlinePatternDataCount != 0 || fnnIbmNameGcgidCount != 0 || (triplets != null && !triplets.isEmpty())) {
      baos.write(shapeResolutionXUnitBase10Inches);
      baos.write(shapeResolutionYUnitBase10Inches);
      baos.write(UtilBinaryDecoding.shortToByteArray(shapeResolutionXUnitsPerUnitBase, 2));
      baos.write(UtilBinaryDecoding.shortToByteArray(shapeResolutionYUnitsPerUnitBase, 2));
      if (patternTechnologyIdentifier != PatternTechnologyIdentifier.LaserMatrixNBitWide) {
        baos.write(UtilBinaryDecoding.longToByteArray(outlinePatternDataCount, 4));
        baos.write(reserved32_34);
        baos.write(fnnRepeatingGroupLength);
        baos.write(UtilBinaryDecoding.longToByteArray(fnnDataCount, 4));
        baos.write(UtilBinaryDecoding.intToByteArray(fnnIbmNameGcgidCount, 2));
      }
      if (triplets != null) {
        for (var t : triplets) {
          t.writeAFP(baos, config);
        }
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public PatternTechnologyIdentifier getPatternTechnologyIdentifier() {
    return patternTechnologyIdentifier;
  }

  public void setPatternTechnologyIdentifier(
      PatternTechnologyIdentifier patternTechnologyIdentifier) {
    this.patternTechnologyIdentifier = patternTechnologyIdentifier;
  }

  public EnumSet<FncFontUseFlag> getFontUseFlags() {
    return fontUseFlags;
  }

  public void setFontUseFlags(EnumSet<FncFontUseFlag> fontUseFlags) {
    this.fontUseFlags = fontUseFlags;
  }

  public FontUnitBase getxUnitBase() {
    return xUnitBase;
  }

  public void setxUnitBase(FontUnitBase xUnitBase) {
    this.xUnitBase = xUnitBase;
  }

  public FontUnitBase getyUnitBase() {
    return yUnitBase;
  }

  public void setyUnitBase(FontUnitBase yUnitBase) {
    this.yUnitBase = yUnitBase;
  }

  public short getxUnitsPerUnitBase() {
    return xUnitsPerUnitBase;
  }

  public void setxUnitsPerUnitBase(short xUnitsPerUnitBase) {
    this.xUnitsPerUnitBase = xUnitsPerUnitBase;
  }

  public short getyUnitsPerUnitBase() {
    return yUnitsPerUnitBase;
  }

  public void setyUnitsPerUnitBase(short yUnitsPerUnitBase) {
    this.yUnitsPerUnitBase = yUnitsPerUnitBase;
  }

  public short getMaxCharacterBoxWidth() {
    return maxCharacterBoxWidth;
  }

  public void setMaxCharacterBoxWidth(short maxCharacterBoxWidth) {
    this.maxCharacterBoxWidth = maxCharacterBoxWidth;
  }

  public short getMaxCharacterBoxHeight() {
    return maxCharacterBoxHeight;
  }

  public void setMaxCharacterBoxHeight(short maxCharacterBoxHeight) {
    this.maxCharacterBoxHeight = maxCharacterBoxHeight;
  }

  public short getFniRepeatingGroupLength() {
    return fniRepeatingGroupLength;
  }

  public void setFniRepeatingGroupLength(short fniRepeatingGroupLength) {
    this.fniRepeatingGroupLength = fniRepeatingGroupLength;
  }

  public RasterPatternDataAlignment getRasterPatternDataAlignment() {
    return rasterPatternDataAlignment;
  }

  public void setRasterPatternDataAlignment(
      RasterPatternDataAlignment rasterPatternDataAlignment) {
    this.rasterPatternDataAlignment = rasterPatternDataAlignment;
  }

  public int getRasterPatternDataCount() {
    return rasterPatternDataCount;
  }

  public void setRasterPatternDataCount(int rasterPatternDataCount) {
    this.rasterPatternDataCount = rasterPatternDataCount;
  }

  public byte getFnmRepeatingGroupLength() {
    return fnmRepeatingGroupLength;
  }

  public void setFnmRepeatingGroupLength(byte fnmRepeatingGroupLength) {
    this.fnmRepeatingGroupLength = fnmRepeatingGroupLength;
  }

  public byte getShapeResolutionXUnitBase10Inches() {
    return shapeResolutionXUnitBase10Inches;
  }

  public void setShapeResolutionXUnitBase10Inches(
      byte shapeResolutionXUnitBase10Inches) {
    this.shapeResolutionXUnitBase10Inches = shapeResolutionXUnitBase10Inches;
  }

  public byte getShapeResolutionYUnitBase10Inches() {
    return shapeResolutionYUnitBase10Inches;
  }

  public void setShapeResolutionYUnitBase10Inches(
      byte shapeResolutionYUnitBase10Inches) {
    this.shapeResolutionYUnitBase10Inches = shapeResolutionYUnitBase10Inches;
  }

  public short getShapeResolutionXUnitsPerUnitBase() {
    return shapeResolutionXUnitsPerUnitBase;
  }

  public void setShapeResolutionXUnitsPerUnitBase(
      short shapeResolutionXUnitsPerUnitBase) {
    this.shapeResolutionXUnitsPerUnitBase = shapeResolutionXUnitsPerUnitBase;
  }

  public short getShapeResolutionYUnitsPerUnitBase() {
    return shapeResolutionYUnitsPerUnitBase;
  }

  public void setShapeResolutionYUnitsPerUnitBase(
      short shapeResolutionYUnitsPerUnitBase) {
    this.shapeResolutionYUnitsPerUnitBase = shapeResolutionYUnitsPerUnitBase;
  }

  public long getOutlinePatternDataCount() {
    return outlinePatternDataCount;
  }

  public void setOutlinePatternDataCount(long outlinePatternDataCount) {
    this.outlinePatternDataCount = outlinePatternDataCount;
  }

  public byte[] getReserved32_34() {
    return reserved32_34;
  }

  public void setReserved32_34(byte[] reservedByte32_34) {
    reserved32_34 = reservedByte32_34;
  }

  public long getFnnDataCount() {
    return fnnDataCount;
  }

  public void setFnnDataCount(long fnnDataCount) {
    this.fnnDataCount = fnnDataCount;
  }

  public int getFnnIbmNameGcgidCount() {
    return fnnIbmNameGcgidCount;
  }

  public void setFnnIbmNameGcgidCount(int fnnIbmNameGcgidCount) {
    this.fnnIbmNameGcgidCount = fnnIbmNameGcgidCount;
  }

  public List<Triplet> getTriplets() {
    return triplets;
  }

  public void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  public byte getRetired0() {
    return retired0;
  }

  public void setRetired0(byte retired0) {
    this.retired0 = retired0;
  }

  public byte getReserved2() {
    return reserved2;
  }

  public void setReserved2(byte reserved2) {
    this.reserved2 = reserved2;
  }

  public byte getFnoRepeatingGroupLength() {
    return fnoRepeatingGroupLength;
  }

  public void setFnoRepeatingGroupLength(byte fnoRepeatingGroupLength) {
    this.fnoRepeatingGroupLength = fnoRepeatingGroupLength;
  }

  public byte getFnpRepeatingGroupLength() {
    return fnpRepeatingGroupLength;
  }

  public void setFnpRepeatingGroupLength(byte fnpRepeatingGroupLength) {
    this.fnpRepeatingGroupLength = fnpRepeatingGroupLength;
  }

  public byte getFnnRepeatingGroupLength() {
    return fnnRepeatingGroupLength;
  }

  public void setFnnRepeatingGroupLength(byte fnnRepeatingGroupLength) {
    this.fnnRepeatingGroupLength = fnnRepeatingGroupLength;
  }

  /**
   * Pattern Technology Identifier.
   */
  public enum PatternTechnologyIdentifier {
    LaserMatrixNBitWide((byte) 0x05),
    CompositeAdobeType0((byte) 0x1E),
    Type1FontPrinterFileBinary((byte) 0x1F);
    byte patternTechnologyIdentifierByte;

    PatternTechnologyIdentifier(byte code) {
      this.patternTechnologyIdentifierByte = code;
    }

    public static PatternTechnologyIdentifier valueOf(byte patternTechnologyIdentifierByte) {
      for (var pti : values()) {
        if (pti.patternTechnologyIdentifierByte == patternTechnologyIdentifierByte) {
          return pti;
        }
      }
      return null;
    }

    public byte toByte() {
      return patternTechnologyIdentifierByte;
    }
  }

  /**
   * Font Use Flag.
   */
  public enum FncFontUseFlag {
    MICRPrinting, // bit 7
    ExtensionFont, // bit 6
    RetiredDoNotShiftBaseLineOffset, // bit 3
    UniformRasterPatternSize; // bit 1

    public static EnumSet<FncFontUseFlag> valueOf(byte fontUseFlagByte) {
      var result = EnumSet.noneOf(FncFontUseFlag.class);
      if (((fontUseFlagByte & 0xFF) & 0x80) > 0) {
        result.add(MICRPrinting);
      }
      if (((fontUseFlagByte & 0xFF) & 0x40) > 0) {
        result.add(ExtensionFont);
      }
      if (((fontUseFlagByte & 0xFF) & 0x08) > 0) {
        result.add(RetiredDoNotShiftBaseLineOffset);
      }
      if (((fontUseFlagByte & 0xFF) & 0x02) > 0) {
        result.add(UniformRasterPatternSize);
      }

      return result;
    }

    public static byte toByte(EnumSet<FncFontUseFlag> fontUseFlags) {
      byte result = 0;
      if (fontUseFlags == null) {
        return result;
      }

      if (fontUseFlags.contains(MICRPrinting)) {
        result |= 0x80;
      }
      if (fontUseFlags.contains(ExtensionFont)) {
        result |= 0x40;
      }
      if (fontUseFlags.contains(RetiredDoNotShiftBaseLineOffset)) {
        result |= 0x08;
      }
      if (fontUseFlags.contains(UniformRasterPatternSize)) {
        result |= 0x02;
      }

      return result;
    }
  }

  /**
   * Font Unit Base.
   */
  public enum FontUnitBase {
    BaseIsFixedAt10Inches,
    BaseIsRelativ;

    public static FontUnitBase valueOf(byte fontUnitBaseByte) {
      if (fontUnitBaseByte == 0x00) {
        return BaseIsFixedAt10Inches;
      }
      if (fontUnitBaseByte == 0x02) {
        return BaseIsRelativ;
      }
      return null;
    }

    public byte toByte() {
      if (this == BaseIsFixedAt10Inches) {
        return 0x00;
      }
      if (this == BaseIsRelativ) {
        return 0x02;
      }
      return 0x00;
    }
  }

  /**
   * Raster Pattern Data Alignment.
   */
  public enum RasterPatternDataAlignment {
    Alignment_1Byte((byte) 0x00),
    Alignment_4Byte((byte) 0x02),
    Alignment_8Byte((byte) 0x03);

    byte rasterPatternAlignmentByte;

    RasterPatternDataAlignment(byte rasterPatternAlignmentByte) {
      this.rasterPatternAlignmentByte = rasterPatternAlignmentByte;
    }

    public static RasterPatternDataAlignment valueOf(byte rasterPatternAlignmentCode) {
      for (var rpda : values()) {
        if (rasterPatternAlignmentCode == rpda.rasterPatternAlignmentByte) {
          return rpda;
        }
      }
      return null;
    }

    public byte toByte() {
      return rasterPatternAlignmentByte;
    }
  }
}
