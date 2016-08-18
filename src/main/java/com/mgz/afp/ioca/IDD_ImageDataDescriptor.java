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
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.ioca.IDD_SelfDefiningField.SelfDefiningFieldType;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class IDD_ImageDataDescriptor extends StructuredField {
  AFPUnitBase unitBase;
  short xImagePointsPerUnitBase;
  short yImagePointsPerUnitBase;
  short widthOfImageInImagePoints;
  short heightOfImageInImagePoints;
  List<IDD_SelfDefiningField> selfDefiningFields;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 9);

    unitBase = AFPUnitBase.valueOf(sfData[offset]);
    xImagePointsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 1, 2);
    yImagePointsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 3, 2);
    widthOfImageInImagePoints = UtilBinaryDecoding.parseShort(sfData, offset + 5, 2);
    heightOfImageInImagePoints = UtilBinaryDecoding.parseShort(sfData, offset + 7, 2);
    selfDefiningFields = new ArrayList<IDD_SelfDefiningField>();

    int actualLength = length != -1 ? length : sfData.length - offset;
    if (actualLength > 10) {
      int pos = 9;
      while (pos < actualLength) {
        IDD_SelfDefiningField sdf = null;
        SelfDefiningFieldType fieldType = SelfDefiningFieldType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + pos, 1));
        if (fieldType == SelfDefiningFieldType.SetBilevelImageColor) {
          sdf = new IDD_SelfDefiningField.SetBilevelImageColor();
        } else if (fieldType == SelfDefiningFieldType.SetExtendedBilevelImageColor) {
          sdf = new IDD_SelfDefiningField.SetExtendedBilevelImageColor();
        } else if (fieldType == SelfDefiningFieldType.SetExtendedBilevelImageColor) {
          sdf = new IDD_SelfDefiningField.IOCAFunctionSetIdentification();
        } else {
          sdf = new IDD_SelfDefiningField.UnknownSelfDefiningField();
        }
        sdf.decodeAFP(sfData, offset + pos, -1, config);
        pos += (sdf.lengthOfFollowingData + 2);
      }
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(unitBase.toByte());
    baos.write(UtilBinaryDecoding.shortToByteArray(xImagePointsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(yImagePointsPerUnitBase, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(widthOfImageInImagePoints, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(heightOfImageInImagePoints, 2));

    if (selfDefiningFields != null) {
      for (IDD_SelfDefiningField sdf : selfDefiningFields) {
        sdf.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public AFPUnitBase getUnitBase() {
    return unitBase;
  }

  public void setUnitBase(AFPUnitBase unitBase) {
    this.unitBase = unitBase;
  }

  public short getxImagePointsPerUnitBase() {
    return xImagePointsPerUnitBase;
  }

  public void setxImagePointsPerUnitBase(short xImagePointsPerUnitBase) {
    this.xImagePointsPerUnitBase = xImagePointsPerUnitBase;
  }

  public short getyImagePointsPerUnitBase() {
    return yImagePointsPerUnitBase;
  }

  public void setyImagePointsPerUnitBase(short yImagePointsPerUnitBase) {
    this.yImagePointsPerUnitBase = yImagePointsPerUnitBase;
  }

  public short getWidthOfImageInImagePoints() {
    return widthOfImageInImagePoints;
  }

  public void setWidthOfImageInImagePoints(short widthOfImageInImagePoints) {
    this.widthOfImageInImagePoints = widthOfImageInImagePoints;
  }

  public short getHeightOfImageInImagePoints() {
    return heightOfImageInImagePoints;
  }

  public void setHeightOfImageInImagePoints(short heightOfImageInImagePoints) {
    this.heightOfImageInImagePoints = heightOfImageInImagePoints;
  }

  public List<IDD_SelfDefiningField> getSelfDefiningFields() {
    return selfDefiningFields;
  }

  public void setSelfDefiningFields(List<IDD_SelfDefiningField> selfDefiningFields) {
    this.selfDefiningFields = selfDefiningFields;
  }

  public void addSelfDefiningFields(IDD_SelfDefiningField selfDefiningField) {
    if (selfDefiningFields == null) {
      selfDefiningFields = new ArrayList<IDD_SelfDefiningField>();
    }
    selfDefiningFields.add(selfDefiningField);
  }

  public void removeSelfDefiningFields(IDD_SelfDefiningField selfDefiningField) {
    if (selfDefiningFields == null) {
      return;
    }
    selfDefiningFields.remove(selfDefiningField);
  }
}
