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
import java.util.ArrayList;

/**
 * MO:DCA, page 233.<br> <br> The Map Coded Font structured field maps a unique coded font resource
 * local ID, which may be embedded one or more times within an object's data and descriptor, to the
 * identifier of a coded font resource object. This identifier may be specified in one of the
 * following formats: <ul> <li> A coded font Global Resource Identifier (GRID) <li> A coded font
 * name <li> A combination of code page name and font character set name </ul> Additionally, the Map
 * Coded Font structured field specifies a set of resource attributes for the coded font. For a
 * description of coded fonts, see the Font Object Content Architecture Reference.
 */
public class MCF_MapCodedFont_Format2 extends StructuredFieldBaseRepeatingGroups {
  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    repeatingGroups = new ArrayList<IRepeatingGroup>();
    int pos = 0;
    while (pos < actualLength) {
      MCF_RepeatingGroup rg = new MCF_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, -1, config);
      repeatingGroups.add(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (IRepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class MCF_RepeatingGroup extends RepeatingGroupWithTriplets {
  }
}
