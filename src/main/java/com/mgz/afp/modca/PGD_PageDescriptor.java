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

import com.mgz.afp.base.StructuredFieldBaseTriplets;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 309. <br><br>
 * <p>
 * The Page Descriptor structured field specifies the size and attributes of a page or overlay
 * presentation space.
 */
public class PGD_PageDescriptor extends StructuredFieldBaseTriplets {
  AFPUnitBase xUnitBase;
  AFPUnitBase yUnitBase;
  short xUnitsPerUnitBase;
  short yUnitsPerUnitBase;
  int xSize;
  int ySize;
  byte[] reserved12_14;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    xUnitBase = AFPUnitBase.valueOf(sfData[offset]);
    yUnitBase = AFPUnitBase.valueOf(sfData[offset + 1]);
    xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
    yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
    xSize = UtilBinaryDecoding.parseInt(sfData, offset + 6, 3);
    ySize = UtilBinaryDecoding.parseInt(sfData, offset + 9, 3);
    int actualLength = getActualLength(sfData, offset, length);

    if (actualLength > 12) {
      reserved12_14 = new byte[Math.min(actualLength - 12, 3)];
      System.arraycopy(sfData, offset + 12, reserved12_14, 0, reserved12_14.length);
    }

    if (actualLength > 15) {
      super.decodeAFP(sfData, offset + 15, actualLength - 15, config);
    } else {
      triplets = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(xUnitBase.toByte());
    baos.write(yUnitBase.toByte());
    baos.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(xSize, 3));
    baos.write(UtilBinaryDecoding.intToByteArray(ySize, 3));
    baos.write(reserved12_14);
    if (triplets != null) {
      for (Triplet t : triplets) {
        t.writeAFP(baos, config);
      }
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

  public int getxSize() {
    return xSize;
  }

  public void setxSize(int xSize) {
    this.xSize = xSize;
  }

  public int getySize() {
    return ySize;
  }

  public void setySize(int ySize) {
    this.ySize = ySize;
  }

  public byte[] getReserved12_14() {
    return reserved12_14;
  }

  public void setReserved12_14(byte[] reserved12_14) {
    this.reserved12_14 = reserved12_14;
  }
}
