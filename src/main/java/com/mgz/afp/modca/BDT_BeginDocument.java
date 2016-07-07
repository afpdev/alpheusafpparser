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

import com.mgz.afp.base.StructuredFieldBaseName;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class BDT_BeginDocument extends StructuredFieldBaseName {
  protected List<Triplet> triplets;
  byte[] reserved8_9 = {0x00, 0x00};

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    super.decodeAFP(sfData, offset, length, config);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 8) {
      reserved8_9 = new byte[]{sfData[offset + 8], sfData[offset + 9]};
    } else {
      reserved8_9 = null;
    }
    if (actualLength > 10) {
      triplets = TripletParser.parseTriplets(sfData, offset + 10, actualLength - 10, config);
    } else {
      triplets = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(name, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    if (reserved8_9 != null) baos.write(reserved8_9);
    if (triplets != null) {
      for (Triplet triplet : triplets) {
        triplet.writeAFP(baos, config);
      }
    }
    writeFullStructuredField(os, baos.toByteArray());
  }

  public final List<Triplet> getTriplets() {
    return triplets;
  }

  public final void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  public final void addTriplet(Triplet triplet) {
    if (triplet == null) return;
    if (triplets == null) triplets = new ArrayList<Triplet>();
    triplets.add(triplet);
  }

  public final void removeTriplet(Triplet triplet) {
    if (triplet == null || triplets == null) return;
    triplets.remove(triplet);
  }

}
