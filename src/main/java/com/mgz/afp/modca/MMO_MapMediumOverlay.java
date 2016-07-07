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
 * MO:DCA, page 287.<br> <br> The Map Medium Overlay structured field maps one-byte medium overlay
 * local identifiers that are specified by keywords in the Medium Modification Control (MMC)
 * structured field to medium overlay names.
 */
public class MMO_MapMediumOverlay extends StructuredFieldBaseRepeatingGroups {
  short lengtOfEachRepeatingGroup;
  byte[] reserved1_3 = new byte[3];

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    lengtOfEachRepeatingGroup = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    reserved1_3 = new byte[3];
    System.arraycopy(sfData, offset + 1, reserved1_3, 0, reserved1_3.length);
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 4) {
      int pos = 4;
      while (pos < actualLength) {
        MMO_PrepeatingGroup rg = new MMO_PrepeatingGroup();
        rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
        addRepeatingGroup(rg);
        pos += lengtOfEachRepeatingGroup;
      }
    } else {
      repeatingGroups = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(lengtOfEachRepeatingGroup);
    baos.write(reserved1_3);
    if (repeatingGroups != null) {
      for (IRepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public short getLengtOfEachRepeatingGroup() {
    return lengtOfEachRepeatingGroup;
  }

  public void setLengtOfEachRepeatingGroup(short lengtOfEachRepeatingGroup) {
    this.lengtOfEachRepeatingGroup = lengtOfEachRepeatingGroup;
  }

  public byte[] getReserved1_3() {
    return reserved1_3;
  }

  public void setReserved1_3(byte[] reserved1_3) {
    this.reserved1_3 = reserved1_3;
  }

  public static class MMO_PrepeatingGroup implements IRepeatingGroup {
    short mediumOverlayLocalId;
    MMO_Flag flag;
    byte[] reserved2_3 = new byte[2];
    String nameOfMediumOverlay;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      mediumOverlayLocalId = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      flag = MMO_Flag.valueOf(sfData[offset + 1]);
      reserved2_3 = new byte[2];
      System.arraycopy(sfData, offset + 3, reserved2_3, 0, reserved2_3.length);
      nameOfMediumOverlay = new String(sfData, offset + 4, 8, config.getAfpCharSet());
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(mediumOverlayLocalId);
      os.write(flag.toByte());
      os.write(reserved2_3);
      os.write(UtilCharacterEncoding.stringToByteArray(nameOfMediumOverlay, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    }

    public short getMediumOverlayLocalId() {
      return mediumOverlayLocalId;
    }

    public void setMediumOverlayLocalId(short mediumOverlayLocalId) {
      this.mediumOverlayLocalId = mediumOverlayLocalId;
    }

    public MMO_Flag getFlag() {
      return flag;
    }

    public void setFlag(MMO_Flag flag) {
      this.flag = flag;
    }

    public byte[] getReserved2_3() {
      return reserved2_3;
    }

    public void setReserved2_3(byte[] reserved2_3) {
      this.reserved2_3 = reserved2_3;
    }

    public String getNameOfMediumOverlay() {
      return nameOfMediumOverlay;
    }

    public void setNameOfMediumOverlay(String nameOfMediumOverlay) {
      this.nameOfMediumOverlay = nameOfMediumOverlay;
    }

    /**
     * Shows whether the overlay is to be loaded into the printer as a raster pattern overlay or as
     * a coded overlay:
     */
    public enum MMO_Flag {
      RasterIndicator_CodedOverlay,
      RasterIndicator_RasterOverlay;

      public static MMO_Flag valueOf(byte codeByte) {
        if ((codeByte & 0x80) == 0x00) return RasterIndicator_CodedOverlay;
        else return RasterIndicator_RasterOverlay;
      }

      public int toByte() {
        if (this == RasterIndicator_CodedOverlay) return 0x00;
        else return 0x80;
      }
    }
  }
}
