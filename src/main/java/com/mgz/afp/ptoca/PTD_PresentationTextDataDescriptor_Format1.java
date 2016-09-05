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
package com.mgz.afp.ptoca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 614. <br><br> The Presentation Text Data Descriptor Format 1 structured field
 * specifies the size of a text object presentation space and the measurement units used for the
 * size and for all linear measurements within the text object.
 */
public class PTD_PresentationTextDataDescriptor_Format1 extends StructuredField {
  AFPUnitBase xUnitBase;
  AFPUnitBase yUnitBase;
  short xUnitsPerUnitBase;
  short yUnitsPerUnitBase;
  short xSize;
  short ySize;
  byte[] reserved10_11;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 10);

    xUnitBase = AFPUnitBase.valueOf(sfData[offset]);
    yUnitBase = AFPUnitBase.valueOf(sfData[offset + 1]);
    xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
    yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
    xSize = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
    ySize = UtilBinaryDecoding.parseShort(sfData, offset + 8, 2);

    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    if (actualLength > 10) {
      reserved10_11 = new byte[2];
      System.arraycopy(sfData, offset + 10, reserved10_11, 0, reserved10_11.length);
    } else {
      reserved10_11 = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(xUnitBase.toByte());
    baos.write(yUnitBase.toByte());
    baos.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(xSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(ySize, 2));
    if (reserved10_11 != null) {
      baos.write(reserved10_11, 0, 2);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public AFPUnitBase getxUnitBase() {
    return xUnitBase;
  }

  public void setxUnitBase(AFPUnitBase xUnitBase) {
    this.xUnitBase = xUnitBase;
  }

  public AFPUnitBase getyUnitBase() {
    return yUnitBase;
  }

  public void setyUnitBase(AFPUnitBase yUnitBase) {
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

  public short getxSize() {
    return xSize;
  }

  public void setxSize(short xSize) {
    this.xSize = xSize;
  }

  public short getySize() {
    return ySize;
  }

  public void setySize(short ySize) {
    this.ySize = ySize;
  }

  public byte[] getReserved10_11() {
    return reserved10_11;
  }

  public void setReserved10_11(byte[] reserved10_11) {
    this.reserved10_11 = reserved10_11;
  }

}
