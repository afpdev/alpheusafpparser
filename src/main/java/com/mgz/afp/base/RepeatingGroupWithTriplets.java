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

import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.AFPValidationException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RepeatingGroupWithTriplets extends RepeatingGroupBase implements IHasTriplets {
  protected List<Triplet> triplets;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    super.decodeAFP(sfData, offset, length, config);
    if (repeatingGroupLength > 2) {
      triplets = TripletParser.parseTriplets(sfData, offset + 2, repeatingGroupLength - 2, config);
    }
  }

  @Override
  public void validate() throws AFPValidationException {
    // TODO: validate.

  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    os.write(UtilBinaryDecoding.intToByteArray(repeatingGroupLength, 2));
    if (triplets != null) for (Triplet t : triplets) t.writeAFP(os, config);
  }

  public List<Triplet> getTriplets() {
    return triplets;
  }

  public void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  public void addTriplet(Triplet triplet) {
    if (triplet == null) return;
    if (triplets == null) triplets = new ArrayList<Triplet>();
    triplets.add(triplet);
  }

  public void removeTriplet(Triplet triplet) {
    if (triplets == null) return;
    else triplets.add(triplet);
  }
}
