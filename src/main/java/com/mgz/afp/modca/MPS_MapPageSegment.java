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

import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 295.<br> <br> The Map Page Segment structured field identifies page segments that
 * are required to present a page on a physical medium.
 */
public class MPS_MapPageSegment extends StructuredFieldBaseRepeatingGroups {
  short lengthOfRepeatingGroup;
  byte[] reserved1_3 = new byte[3];

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    lengthOfRepeatingGroup = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    reserved1_3 = new byte[3];
    System.arraycopy(sfData, offset + 1, reserved1_3, 0, reserved1_3.length);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 4) {
      int pos = 4;
      while (pos < actualLength) {
        MPS_RepeatingGroup rg = new MPS_RepeatingGroup();
        rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
        addRepeatingGroup(rg);
        pos += lengthOfRepeatingGroup;
      }
    } else {
      this.setRepeatingGroups(null);
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(lengthOfRepeatingGroup);
    baos.write(reserved1_3);
    if (this.getRepeatingGroups() != null) {
      for (IRepeatingGroup rg : this.getRepeatingGroups()) rg.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public short getLengthOfRepeatingGroup() {
    return lengthOfRepeatingGroup;
  }

  public void setLengthOfRepeatingGroup(short lengthOfRepeatingGroup) {
    this.lengthOfRepeatingGroup = lengthOfRepeatingGroup;
  }

  public byte[] getReserved1_3() {
    return reserved1_3;
  }

  public void setReserved1_3(byte[] reserved1_3) {
    this.reserved1_3 = reserved1_3;
  }

  public static class MPS_RepeatingGroup implements IRepeatingGroup {
    byte[] reserved0_3 = new byte[4];
    String nameOfPageSegment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, 12);
      reserved0_3 = new byte[4];
      System.arraycopy(sfData, offset, reserved0_3, 0, reserved0_3.length);
      nameOfPageSegment = new String(sfData, offset + 4, 8, config.getAfpCharSet());
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(reserved0_3);
      os.write(UtilCharacterEncoding.stringToByteArray(nameOfPageSegment, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    }
  }
}
