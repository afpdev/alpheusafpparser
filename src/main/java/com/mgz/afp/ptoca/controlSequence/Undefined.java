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
package com.mgz.afp.ptoca.controlSequence;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.OutputStream;

public class Undefined extends PTOCAControlSequence {
  short undefinedControlSequenceFunctionType;
  byte[] data;


  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    if (offset > 0) {
      // Read one byte before actual offset of given data to determine what CSFT code has bin given.
      undefinedControlSequenceFunctionType = UtilBinaryDecoding.parseShort(sfData, offset - 1, 1);
    } else {
      undefinedControlSequenceFunctionType = 0x00;
    }

    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    if (actualLength > 0) {
      data = new byte[actualLength];
      System.arraycopy(sfData, offset, data, 0, actualLength);
    } else {
      data = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    if (!csi.isChained) {
      os.write(csi.getCsPrefix());
      os.write(csi.getCsClass());
    }
    os.write(csi.getLength());
    os.write(undefinedControlSequenceFunctionType);
  }
}
