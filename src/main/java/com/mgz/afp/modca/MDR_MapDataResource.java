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
 * MO:DCA, page 243.<br> <br> The Map Data Resource structured field specifies resources that are
 * required for presentation. Each resource reference is defined in a repeating group and is
 * identified with a file name, the identifier of a begin structured field for the resource, or any
 * other identifier associated with the resource. The MDR repeating group may additionally specify a
 * local or internal identifier for the resource object. Such a local identifier may be embedded one
 * or more times within an object's data.
 */
public class MDR_MapDataResource extends StructuredFieldBaseRepeatingGroups {
  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    this.setRepeatingGroups(new ArrayList<IRepeatingGroup>());

    int acualLength = getActualLength(sfData, offset, length);
    int pos = 0;
    while (pos < acualLength) {
      MDR_RepeatingGroup rg = new MDR_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, acualLength - pos, config);
      this.addRepeatingGroup(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (IRepeatingGroup rg : this.getRepeatingGroups()) rg.writeAFP(baos, config);
    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class MDR_RepeatingGroup extends RepeatingGroupWithTriplets {
  }

}
