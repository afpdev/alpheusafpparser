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

import com.mgz.afp.base.IHasTriplets;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CDD_ContainerDataDescriptor extends StructuredField implements IHasTriplets {
  byte retiredParameters[];
  List<Triplet> triplets;


  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, -1);

    retiredParameters = new byte[12];
    System.arraycopy(sfData, 0, retiredParameters, 0, 12);

    triplets = TripletParser.parseTriplets(sfData, 12, -1, config);
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(retiredParameters);
    if (triplets != null) {
      for (Triplet triplet : triplets) {
        triplet.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<Triplet> getTriplets() {
    return triplets;
  }

  public void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  public void addTriplet(Triplet triplet) {
    if (triplet == null) {
      return;
    }
    if (triplets == null) {
      triplets = new ArrayList<Triplet>();
    }
    triplets.add(triplet);
  }

  public void removeTriplet(Triplet triplet) {
    if (triplets == null) {
      return;
    }
    triplets.remove(triplet);
  }
}
