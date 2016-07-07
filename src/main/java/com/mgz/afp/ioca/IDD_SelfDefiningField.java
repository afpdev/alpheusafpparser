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
package com.mgz.afp.ioca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.OutputStream;

public abstract class IDD_SelfDefiningField implements IAFPDecodeableWriteable {

  SelfDefiningFieldType fieldType;
  short lengthOfFollowingData;

  public SelfDefiningFieldType getFieldType() {
    return fieldType;
  }

  public void setFieldType(SelfDefiningFieldType fieldType) {
    this.fieldType = fieldType;
  }

  public short getLengthOfFollowingData() {
    return lengthOfFollowingData;
  }

  public void setLengthOfFollowingData(short lengthOfFollowingData) {
    this.lengthOfFollowingData = lengthOfFollowingData;
  }


  public static enum SelfDefiningFieldType {
    Unknown(0x00),
    SetBilevelImageColor(0xF6),
    SetExtendedBilevelImageColor(0xF4),
    IOCAFunctionSetIdentification(0xF7),;
    int fieldType;

    SelfDefiningFieldType(int fieldTypeByte) {
      this.fieldType = fieldTypeByte;
    }

    public static SelfDefiningFieldType valueOf(short fieldTypeByte) {
      for (SelfDefiningFieldType t : values()) if (t.fieldType == fieldTypeByte) return t;
      return Unknown;
    }

    public int toByte() {
      return fieldType;
    }
  }

  public static class SetBilevelImageColor extends IDD_SelfDefiningField {
    short applicabilityArea;
    short reserved3 = 0x00;
    AFPColorValue color;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      IDD_ImageDataDescriptor.checkDataLength(sfData, offset, length, 6);
      fieldType = SelfDefiningFieldType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      applicabilityArea = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3 = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      color = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 4, 2));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(fieldType.toByte());
      os.write(lengthOfFollowingData);
      os.write(applicabilityArea);
      os.write(reserved3);
      os.write(color.toByte2());
    }

    public short getApplicabilityArea() {
      return applicabilityArea;
    }

    public void setApplicabilityArea(short applicabilityArea) {
      this.applicabilityArea = applicabilityArea;
    }

    public short getReserved3() {
      return reserved3;
    }

    public void setReserved3(short reserved3) {
      this.reserved3 = reserved3;
    }

    public AFPColorValue getColor() {
      return color;
    }

    public void setColor(AFPColorValue color) {
      this.color = color;
    }
  }

  public static class SetExtendedBilevelImageColor extends IDD_SelfDefiningField {
    short reserved2 = 0x00;
    AFPColorSpace colorSpace;
    byte[] reserved4_7 = new byte[]{0x00, 0x00};
    byte nrOfBitsComponent1;
    byte nrOfBitsComponent2;
    byte nrOfBitsComponent3;
    byte nrOfBitsComponent4;
    byte[] colorValue;


    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      IDD_ImageDataDescriptor.checkDataLength(sfData, offset, length, 6);
      fieldType = SelfDefiningFieldType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      reserved2 = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 3]);
      reserved4_7 = new byte[4];
      System.arraycopy(sfData, offset + 5, reserved4_7, 0, 4);
      nrOfBitsComponent1 = sfData[offset + 8];
      nrOfBitsComponent2 = sfData[offset + 9];
      nrOfBitsComponent3 = sfData[offset + 10];
      nrOfBitsComponent4 = sfData[offset + 11];
      colorValue = new byte[lengthOfFollowingData - 10];
      System.arraycopy(sfData, offset + 12, colorValue, 0, colorValue.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(fieldType.toByte());
      os.write(lengthOfFollowingData);
      os.write(reserved2);
      os.write(colorSpace.toByte());
      os.write(reserved4_7);
      os.write(nrOfBitsComponent1);
      os.write(nrOfBitsComponent2);
      os.write(nrOfBitsComponent3);
      os.write(nrOfBitsComponent4);
      os.write(colorValue);
    }

    public short getReserved2() {
      return reserved2;
    }

    public void setReserved2(short reserved2) {
      this.reserved2 = reserved2;
    }

    public AFPColorSpace getColorSpace() {
      return colorSpace;
    }

    public void setColorSpace(AFPColorSpace colorSpace) {
      this.colorSpace = colorSpace;
    }

    public byte[] getReserved4_7() {
      return reserved4_7;
    }

    public void setReserved4_7(byte[] reserved4_7) {
      this.reserved4_7 = reserved4_7;
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

  public static class IOCAFunctionSetIdentification extends IDD_SelfDefiningField {
    short functionSetCategory;
    IOCAFunctionSetIdentification.FunctionSetIdentifier functionSetIdentifier;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 6);
      fieldType = SelfDefiningFieldType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      functionSetCategory = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      functionSetIdentifier = FunctionSetIdentifier.valueOf(sfData[offset + 3]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(fieldType.toByte());
      os.write(lengthOfFollowingData);
      os.write(functionSetCategory);
      os.write(functionSetIdentifier.toByte());
    }

    public short getFunctionSetCategory() {
      return functionSetCategory;
    }

    public void setFunctionSetCategory(short functionSetCategory) {
      this.functionSetCategory = functionSetCategory;
    }

    public IOCAFunctionSetIdentification.FunctionSetIdentifier getFunctionSetIdentifier() {
      return functionSetIdentifier;
    }

    public void setFunctionSetIdentifier(IOCAFunctionSetIdentification.FunctionSetIdentifier functionSetIdentifier) {
      this.functionSetIdentifier = functionSetIdentifier;
    }

    public static enum FunctionSetIdentifier {
      FS10(0x0A),
      FS11(0x0B),
      FS40(0x28),
      FS42(0x2A),
      FS45(0x2D);
      int code;

      FunctionSetIdentifier(int fsCodeByte) {
        this.code = fsCodeByte;
      }

      public static IOCAFunctionSetIdentification.FunctionSetIdentifier valueOf(byte fsCodeByte) {
        for (IOCAFunctionSetIdentification.FunctionSetIdentifier fsi : values())
          if (fsi.code == fsCodeByte) return fsi;
        return null;
      }

      public int toByte() {
        return code;
      }
    }
  }

  public static class UnknownSelfDefiningField extends IDD_SelfDefiningField {
    short unknownFieldType;
    byte data[];

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      unknownFieldType = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      if (lengthOfFollowingData > 0) {
        data = new byte[lengthOfFollowingData];
        System.arraycopy(sfData, offset + 2, data, 0, data.length);
      } else {
        data = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (data == null) lengthOfFollowingData = 0;
      else lengthOfFollowingData = (short) data.length;
      os.write(unknownFieldType);
      os.write(lengthOfFollowingData);
      if (data != null) os.write(data);
    }
  }

}