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
import com.mgz.afp.base.RepeatingGroupWithTriplets;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * MO:DCA, page 285.<br> <br> The Map Media Destination structured field maps a media destination
 * local ID to the name of a media destination.
 */
public class MMD_MapMediaDestination extends StructuredFieldBaseRepeatingGroups {

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 0;
    while (pos < actualLength) {
      MMD_RepeatingGroup rg = new MMD_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      addRepeatingGroup(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (IRepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class MMD_RepeatingGroup extends RepeatingGroupWithTriplets {
  }
}
