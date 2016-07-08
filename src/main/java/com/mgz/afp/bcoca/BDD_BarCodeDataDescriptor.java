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
package com.mgz.afp.bcoca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BDD_BarCodeDataDescriptor extends StructuredField {
  @AFPField
  static byte Reserved1 = 0x00;
  @AFPField
  AFPUnitBase unitBase;
  @AFPField
  short unitsPerUnitBaseX;
  @AFPField
  short unitsPerUnitBaseY;
  @AFPField
  int presentationSpaceWidth;
  @AFPField
  int presentationSpaceLength;
  @AFPField
  short desiredSymbolWidth;
  @AFPField
  BarCodeType barcodeType;
  @AFPField
  byte barcodeModifier;
  @AFPField
  short fontLocalIDForHRI;
  @AFPField
  int color;
  @AFPField
  short moduleWidthInMils;
  @AFPField
  int elementHeight;
  @AFPField
  short heightMultiplier;
  @AFPField
  int wideToNarrowRatio;

  public static byte getReserved1() {
    return Reserved1;
  }

  public static void setReserved1(byte reserved1) {
    Reserved1 = reserved1;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 23);

    unitBase = AFPUnitBase.valueOf(sfData[offset]);
    unitsPerUnitBaseX = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
    unitsPerUnitBaseY = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
    presentationSpaceWidth = UtilBinaryDecoding.parseInt(sfData, offset + 6, 2);
    presentationSpaceLength = UtilBinaryDecoding.parseInt(sfData, offset + 8, 2);
    desiredSymbolWidth = UtilBinaryDecoding.parseShort(sfData, offset + 10, 2);
    barcodeType = BarCodeType.valueOf(sfData[offset + 12]);
    barcodeModifier = sfData[offset + 13];
    fontLocalIDForHRI = UtilBinaryDecoding.parseShort(sfData, offset + 14, 1);
    color = UtilBinaryDecoding.parseInt(sfData, offset + 15, 2);
    moduleWidthInMils = UtilBinaryDecoding.parseShort(sfData, offset + 17, 1);
    elementHeight = UtilBinaryDecoding.parseInt(sfData, offset + 18, 2);
    heightMultiplier = UtilBinaryDecoding.parseShort(sfData, offset + 20, 1);
    wideToNarrowRatio = UtilBinaryDecoding.parseInt(sfData, offset + 21, 2);

  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(unitBase.toByte());
    baos.write(Reserved1);
    baos.write(UtilBinaryDecoding.shortToByteArray(unitsPerUnitBaseX, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(unitsPerUnitBaseY, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(presentationSpaceWidth, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(presentationSpaceLength, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(desiredSymbolWidth, 2));
    baos.write(barcodeType.toByte());
    baos.write(barcodeModifier);
    baos.write(UtilBinaryDecoding.shortToByteArray(fontLocalIDForHRI, 1));
    baos.write(UtilBinaryDecoding.intToByteArray(color, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(moduleWidthInMils, 1));
    baos.write(UtilBinaryDecoding.intToByteArray(elementHeight, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(heightMultiplier, 1));
    baos.write(UtilBinaryDecoding.intToByteArray(wideToNarrowRatio, 2));

    writeFullStructuredField(os, baos.toByteArray());
  }

  public AFPUnitBase getUnitBase() {
    return unitBase;
  }

  public void setUnitBase(AFPUnitBase unitBase) {
    this.unitBase = unitBase;
  }

  public short getUnitsPerUnitBaseX() {
    return unitsPerUnitBaseX;
  }

  public void setUnitsPerUnitBaseX(short unitsPerUnitBaseX) {
    this.unitsPerUnitBaseX = unitsPerUnitBaseX;
  }

  public short getUnitsPerUnitBaseY() {
    return unitsPerUnitBaseY;
  }

  public void setUnitsPerUnitBaseY(short unitsPerUnitBaseY) {
    this.unitsPerUnitBaseY = unitsPerUnitBaseY;
  }

  public int getPresentationSpaceWidth() {
    return presentationSpaceWidth;
  }

  public void setPresentationSpaceWidth(int presentationSpaceWidth) {
    this.presentationSpaceWidth = presentationSpaceWidth;
  }

  public int getPresentationSpaceLength() {
    return presentationSpaceLength;
  }

  public void setPresentationSpaceLength(int presentationSpaceLength) {
    this.presentationSpaceLength = presentationSpaceLength;
  }

  public short getDesiredSymbolWidth() {
    return desiredSymbolWidth;
  }

  public void setDesiredSymbolWidth(short desiredSymbolWidth) {
    this.desiredSymbolWidth = desiredSymbolWidth;
  }

  public BarCodeType getBarcodeType() {
    return barcodeType;
  }

  public void setBarcodeType(BarCodeType barcodeType) {
    this.barcodeType = barcodeType;
  }

  public byte getBarcodeModifier() {
    return barcodeModifier;
  }

  public void setBarcodeModifier(byte barcodeModifier) {
    this.barcodeModifier = barcodeModifier;
  }

  public short getFontLocalIDForHRI() {
    return fontLocalIDForHRI;
  }

  public void setFontLocalIDForHRI(short fontLocalIDForHRI) {
    this.fontLocalIDForHRI = fontLocalIDForHRI;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public short getModuleWidthInMils() {
    return moduleWidthInMils;
  }

  public void setModuleWidthInMils(short moduleWidthInMils) {
    this.moduleWidthInMils = moduleWidthInMils;
  }

  public int getElementHeight() {
    return elementHeight;
  }

  public void setElementHeight(int elementHeight) {
    this.elementHeight = elementHeight;
  }

  public short getHeightMultiplier() {
    return heightMultiplier;
  }

  public void setHeightMultiplier(short heightMultiplier) {
    this.heightMultiplier = heightMultiplier;
  }

  public int getWideToNarrowRatio() {
    return wideToNarrowRatio;
  }

  public void setWideToNarrowRatio(int wideToNarrowRatio) {
    this.wideToNarrowRatio = wideToNarrowRatio;
  }

  public enum BarCodeType {
    Code39_3of9Code_AIM_USS_39(0x01, true, true, new byte[]{0x01, 0x02}),
    MSI_MmodifiedPlesseyCode(0x02, true, true, new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09}),
    UPC_CGPC_VersionA(0x03, true, true, new byte[]{0x00}),
    UPC_CGPC_VersionE(0x05, true, true, new byte[]{0x00}),
    UPC_TwoDigit_Supplemental_Periodicals(0x06, true, true, new byte[]{0x00, 0x01, 0x02}),
    UPC_FiveDigit_Supplemental_Paperbacks(0x07, true, true, new byte[]{0x00, 0x01, 0x02}),
    EAN_8_includingJANShort(0x08, true, true, new byte[]{0x00}),
    EAN_13_includingJANStandard(0x09, true, true, new byte[]{0x00}),
    Industrial_2of5(0x0A, false, false, new byte[]{0x01, 0x02}),
    Matrix_2ofFive(0x0B, false, false, new byte[]{0x01, 0x02}),

    Interleaved_2of5__ITF14__AIM_USS_I_2of5(0x0C, true, true, new byte[]{0x01, 0x02, 0x03, 0x04}),
    Codabar_2of7_AIM_USS_Codabar(0x0D, false, true, new byte[]{0x01, 0x02}),
    Code_128__GS1_128__UCC_EAN_128__AIM_USS_128__IntelligentMail__ContainerBarcode(0x11, false, true, new byte[]{0x02, 0x03, 0x04, 0x05}),
    EAN_TwoDigit_Supplemental(0x16, true, true, new byte[]{0x00, 0x01}),
    EAN_FiveDigit_Supplemental(0x17, true, true, new byte[]{0x00, 0x01}),
    POSTNET_PLANET(0x18, false, false, new byte[]{0x00, 0x01, 0x02, 0x03, 0x04}),
    RM4SCC_DutchKIX(0x1A, false, true, new byte[]{0x00, 0x01}),
    JapanPostalBarCode(0x1B, false, true, new byte[]{0x00, 0x01}),
    DataMatrix_GS1DataMatrix_2D(0x1C, false, true, new byte[]{0x00}),
    MaxiCode_2D(0x1D, false, true, new byte[]{0x00}),
    PDF417_2D(0x1E, false, true, new byte[]{0x00, 0x01}),
    AustraliaPostBarCode(0x1F, false, true, new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08}),
    QRCode_2D(0x20, false, true, new byte[]{0x02}),
    Code93(0x21, false, true, new byte[]{0x00}),
    IntelligentMailBarcode(0x22, false, true, new byte[]{0x00, 0x01, 0x02, 0x03}),
    RoyalMail_RED_TAG(0x23, false, false, new byte[]{0x00}),
    GS1_DataBar(0x24, false, false, new byte[]{0x00, 0x01, 0x02, 0x04, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x17, 0x18, 0x19, 0x1A, 0x1B}),

    // Retired Bar Codes:
    Retired_Item7(0x04, false, false, new byte[]{0x00, 0x01, 0x02, 0x03, 0x04}),
    Retired_Item10(0x0E, false, false, new byte[]{0x00}),
    Retired_Item11(0x0F, false, false, new byte[]{0x00}),
    Retired_Item12(0x10, false, false, new byte[]{0x01, 0x02}),
    // Retired_Item20(0x11,?,?,new byte[]{0x01}), // see BCOCA page
    Retired_Item13(0x12, false, false, new byte[]{0x01, 0x02}),
    Retired_Item14(0x13, false, false, new byte[]{0x01, 0x02, 0x3}),
    Retired_Item15(0x14, false, false, new byte[]{0x00}),
    Retired_Item16(0x15, false, false, new byte[]{0x01, 0x02}),
    Retired_Item19(0x19, false, false, new byte[]{0x00, 0x01, 0x02, 0x03}),
    Retired_Item22(0xEC, false, false, new byte[]{0x02}),
    Retired_Item23(0xED, false, false, new byte[]{0x00}),
    Retired_Item24(0xEE, false, false, new byte[]{0x00}),
    Retired_Item25(0xEF, false, false, new byte[]{0x00, 0x01});

    int code;
    boolean isBCD1;
    boolean isBCD2;

    BarCodeType(int code, boolean isBCD1, boolean isBCD2, byte[] possibleBarCodeModfier) {
      this.code = code;
      this.isBCD1 = isBCD1;
      this.isBCD2 = isBCD2;
    }

    public static BarCodeType valueOf(int barcodeTypeCode) {
      for (BarCodeType bct : values()) if (bct.code == barcodeTypeCode) return bct;
      return null;
    }

    public byte toByte() {
      return (byte) code;
    }

    public boolean isBCD1() {
      return isBCD1;
    }

    public boolean isBCD2() {
      return isBCD2;
    }
  }
}
