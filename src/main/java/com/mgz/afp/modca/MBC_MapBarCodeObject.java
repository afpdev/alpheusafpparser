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

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MBC_MapBarCodeObject extends StructuredField {
  short lengthOfRepeatingGroup;
  Triplet triplet;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, offset, length, 5);
    lengthOfRepeatingGroup = UtilBinaryDecoding.parseShort(sfData, offset, 2);
    triplet = TripletParser.parseTriplet(sfData, offset, length, config);
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilBinaryDecoding.shortToByteArray(lengthOfRepeatingGroup, 2));
    triplet.writeAFP(baos, config);
    writeFullStructuredField(os, baos.toByteArray());
  }

  public short getLengthOfRepeatingGroup() {
    return lengthOfRepeatingGroup;
  }

  public void setLengthOfRepeatingGroup(short lengthOfRepeatingGroup) {
    this.lengthOfRepeatingGroup = lengthOfRepeatingGroup;
  }

  public Triplet getTriplet() {
    return triplet;
  }

  public void setTriplet(Triplet triplet) {
    this.triplet = triplet;
  }
}
