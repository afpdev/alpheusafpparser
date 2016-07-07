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
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 296.<br> <br> The Map Suppression structured field maps one-byte text suppression
 * local identifiers to text suppression names. Suppressible text is identified in presentation text
 * objects with a local identifier and is bracketed with control sequences that specify the
 * beginning and the end of the suppression. A text suppression is activated by specifying its local
 * identifier in a Medium Modification Control (MMC) structured field in a medium map.
 */
public class MSU_MapSuppression extends StructuredFieldBaseRepeatingGroups {
  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 0) {
      int pos = 0;
      while (pos < actualLength) {
        MSU_RepeatingGroup rg = new MSU_RepeatingGroup();
        rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
        addRepeatingGroup(rg);
        pos += 10;
      }
    } else {
      repeatingGroups = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (IRepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class MSU_RepeatingGroup implements IRepeatingGroup {
    String nameOfTextSuppresstion;
    byte reserved8 = 0x00;
    byte localID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      nameOfTextSuppresstion = new String(sfData, offset, 8, config.getAfpCharSet());
      reserved8 = sfData[offset + 8];
      localID = sfData[offset + 9];
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilCharacterEncoding.stringToByteArray(nameOfTextSuppresstion, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
      os.write(reserved8);
      os.write(localID);
    }

    public String getNameOfTextSuppresstion() {
      return nameOfTextSuppresstion;
    }

    public void setNameOfTextSuppresstion(String nameOfTextSuppresstion) {
      this.nameOfTextSuppresstion = nameOfTextSuppresstion;
    }

    public byte getReserved8() {
      return reserved8;
    }

    public void setReserved8(byte reserved8) {
      this.reserved8 = reserved8;
    }

    public byte getLocalID() {
      return localID;
    }

    public void setLocalID(byte localID) {
      this.localID = localID;
    }
  }
}
