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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 307. <br><br> The Presentation Fidelity Control structured field specifies the user
 * fidelity requirements for data presented on physical media and for operations performed on
 * physical media. The scope of the Presentation Fidelity Control structured field is the document
 * or print file controlled by the form map that contains this structured field. <br><br> Triplets
 * are used on the Presentation Fidelity Control structured field to define specific presentation
 * fidelity requirements that are to be applied by the presentation process as data is presented on
 * physical media. While triplets may be conceptually related, each triplet is processed
 * independently of any other triplet. Therefore, it is the responsibility of the generator of the
 * Presentation Fidelity Control structured field to ensure cross-triplet consistency. If a
 * particular fidelity triplet is not specified on this structured field, or if this structured
 * field is not specified, presentation process defaults are used to control the presentation
 * fidelity.
 */
public class PFC_PresentationFidelityControl extends StructuredFieldBaseTriplets {
  byte reserved0 = 0x00;
  PFC_Flag flag;
  byte[] reserved2_3 = new byte[2];

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    reserved0 = sfData[offset];
    flag = PFC_Flag.valueOf(sfData[offset + 1]);
    reserved2_3 = new byte[2];
    System.arraycopy(sfData, offset + 2, reserved2_3, 0, reserved2_3.length);
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 4) {
      super.decodeAFP(sfData, offset + 4, actualLength - 4, config);
    } else {
      triplets = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(reserved0);
    baos.write(flag.toByte());
    baos.write(reserved2_3);
    if (triplets != null) {
      for (Triplet t : triplets) t.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public byte getReserved0() {
    return reserved0;
  }

  public void setReserved0(byte reserved0) {
    this.reserved0 = reserved0;
  }

  public PFC_Flag getFlag() {
    return flag;
  }

  public void setFlag(PFC_Flag flag) {
    this.flag = flag;
  }

  public byte[] getReserved2_3() {
    return reserved2_3;
  }

  public void setReserved2_3(byte[] reserved2_3) {
    this.reserved2_3 = reserved2_3;
  }

  public static enum PFC_Flag {
    ResetFidelityControlsToDefault,
    DoNotResetFidelityControlsToDefault;

    public static PFC_Flag valueOf(byte flagByte) {
      if ((flagByte & 0x80) == 0) return ResetFidelityControlsToDefault;
      else return DoNotResetFidelityControlsToDefault;
    }

    public int toByte() {
      if (this == ResetFidelityControlsToDefault) return 0x00;
      else return 0x80;
    }
  }
}
