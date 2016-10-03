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
 * MO:DCA, page 327. <br><br> The Page Modification Control structured field specifies modifications
 * to be applied to a page presented on a physical medium. If the ID of a specific PMC is selected
 * in the PGP structured field of the active medium map in N-up mode, only the modifications
 * specified by that PMC are applied to pages placed on the medium. If a specific PMC is not
 * selected in N-up mode, all modifications specified by all PMCs in the active medium map are
 * applied to pages placed on the medium.
 */
public class PMC_PageModificationControl extends StructuredFieldBaseTriplets {
  byte pageModificationControlID;
  byte reserved1 = 0x00;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    pageModificationControlID = sfData[offset];
    reserved1 = sfData[offset + 1];
    int actualLenth = getActualLength(sfData, offset, length);
    if (actualLenth > 2) {
      super.decodeAFP(sfData, offset + 2, actualLenth - 2, config);
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(pageModificationControlID);
    baos.write(reserved1);
    if (triplets != null) {
      for (Triplet t : triplets) {
        t.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public byte getPageModificationControlID() {
    return pageModificationControlID;
  }

  public void setPageModificationControlID(byte pageModificationControlID) {
    this.pageModificationControlID = pageModificationControlID;
  }

  public byte getReserved1() {
    return reserved1;
  }

  public void setReserved1(byte reserved1) {
    this.reserved1 = reserved1;
  }

}
