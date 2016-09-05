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
package com.mgz.afp.modca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IID_IMImageInputDescriptor extends StructuredField {
  byte[] constantData0_11;
  AFPUnitBase xUnitBase;
  AFPUnitBase yUnitBase;
  short xUnitsPerUnitBase;
  short yUnitsPerUnitBase;
  short xSize;
  short ySize;
  byte[] constantData22_27;
  short xDefaultCellSize;
  short yDefaultCellSize;
  byte[] constantData32_33;
  AFPColorValue color;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    constantData0_11 = new byte[12];
    System.arraycopy(sfData, offset, constantData0_11, 0, constantData0_11.length);
    xUnitBase = AFPUnitBase.valueOf(sfData[offset + 12]);
    yUnitBase = AFPUnitBase.valueOf(sfData[offset + 13]);
    xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 14, 2);
    yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 16, 2);
    xSize = UtilBinaryDecoding.parseShort(sfData, offset + 18, 2);
    ySize = UtilBinaryDecoding.parseShort(sfData, offset + 20, 2);
    constantData22_27 = new byte[6];
    System.arraycopy(sfData, offset + 22, constantData22_27, 0, constantData22_27.length);
    xDefaultCellSize = UtilBinaryDecoding.parseShort(sfData, offset + 28, 2);
    yDefaultCellSize = UtilBinaryDecoding.parseShort(sfData, offset + 30, 2);
    int actualLength = getActualLength(sfData, offset, length);
    // Error in MODCA spec: constantData32_33 is optional.
    if (actualLength > 32) {
      constantData32_33 = new byte[2];
      System.arraycopy(sfData, offset + 32, constantData32_33, 0, constantData32_33.length);
    } else {
      constantData32_33 = null;
    }
    // Error in MODCA spec: color is optional.
    if (actualLength > 34) {
      color = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 34, 2));
    } else {
      color = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(constantData0_11);
    baos.write(xUnitBase.toByte());
    baos.write(yUnitBase.toByte());
    baos.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(xSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(ySize, 2));
    baos.write(constantData22_27);
    baos.write(UtilBinaryDecoding.shortToByteArray(xDefaultCellSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yDefaultCellSize, 2));
    if (constantData32_33 != null) {
      baos.write(constantData32_33);
      if (color != null) baos.write(color.toByte2());
    }

    writeFullStructuredField(os, baos.toByteArray());
  }
}
