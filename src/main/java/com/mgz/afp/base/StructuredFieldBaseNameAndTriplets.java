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
package com.mgz.afp.base;

import com.mgz.afp.base.annotations.AFPField;
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

public abstract class StructuredFieldBaseNameAndTriplets extends StructuredFieldBaseName implements IHasTriplets {
  @AFPField
  protected List<Triplet> triplets;


  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    super.decodeAFP(sfData, offset, length, config);
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 8) {
      triplets = TripletParser.parseTriplets(sfData, 8, sfData.length - 8, config);
    } else {
      triplets = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    if (name != null) {
      baos.write(UtilCharacterEncoding.stringToByteArray(name, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    }
    if (triplets != null) {
      for (Triplet triplet : triplets) {
        triplet.writeAFP(baos, config);
      }
    }
    writeFullStructuredField(os, baos.toByteArray());
  }

  @Override
  public final List<Triplet> getTriplets() {
    return triplets;
  }

  @Override
  public final void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  @Override
  public final void addTriplet(Triplet triplet) {
    if (triplet != null) {
      if (triplets == null) {
        triplets = new ArrayList<Triplet>();
      }
      triplets.add(triplet);
    }
  }

  @Override
  public final void removeTriplet(Triplet triplet) {
    if (triplet != null && triplets != null) {
      triplets.remove(triplet);
    }
  }

  @Override
  public void accept(final StructuredFieldVisitor visitor) {
    visitor.handle(this);
  }
}
