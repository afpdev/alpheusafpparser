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
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IPG_IncludePage extends StructuredFieldBaseTriplets {
  String pageName;
  byte[] reserved8_15 = new byte[8];
  IPG_Flag flags;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    pageName = new String(sfData, 0, 8, config.getAfpCharSet());
    reserved8_15 = new byte[8];
    System.arraycopy(sfData, offset + 8, reserved8_15, 0, reserved8_15.length);
    flags = IPG_Flag.valueOf(sfData[offset + 16]);
    int actualLength = getActualLength(sfData, offset, length);

    super.decodeAFP(sfData, offset + 17, actualLength - 17, config); // Decode triplets.
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(pageName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    baos.write(reserved8_15);
    baos.write(flags.toByte());
    for (Triplet t : triplets) {
      t.writeAFP(baos, config);
    }
    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getPageName() {
    return pageName;
  }

  public void setPageName(String pageName) {
    this.pageName = pageName;
  }

  public byte[] getReserved8_15() {
    return reserved8_15;
  }

  public void setReserved8_15(byte[] reserved8_15) {
    this.reserved8_15 = reserved8_15;
  }

  public IPG_Flag getFlags() {
    return flags;
  }

  public void setFlags(IPG_Flag flags) {
    this.flags = flags;
  }

  public enum IPG_Flag {
    Reserved,
    PageIsContainedInResourceDocument;

    public static IPG_Flag valueOf(byte flagByte) {
      if ((flagByte & 0x80) != 0) {
        return PageIsContainedInResourceDocument;
      } else {
        return Reserved;
      }
    }

    public int toByte() {
      if (this == PageIsContainedInResourceDocument) {
        return 0x80;
      } else {
        return 0x00;
      }
    }
  }
}
