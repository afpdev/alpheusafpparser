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
package com.mgz.afp.lineData;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The Line Descriptor Count structured field specifies the number of Line Descriptor (LND), Record
 * Descriptor (RCD) or XML Descriptor (XMD) structured fields in the Data Map Transmission Subcase.
 */
public class LNC_LineDescriptorCount extends StructuredField {
  int numberOfSFInBDX;


  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    numberOfSFInBDX = UtilBinaryDecoding.parseInt(sfData, offset, 2);
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    writeFullStructuredField(os, UtilBinaryDecoding.intToByteArray(numberOfSFInBDX, 2));
  }

  public int getNumberOfSFInBDX() {
    return numberOfSFInBDX;
  }

  public void setNumberOfSFInBDX(int numberSFInBDX) {
    this.numberOfSFInBDX = numberSFInBDX;
  }
}
