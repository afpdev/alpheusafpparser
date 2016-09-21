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
package com.mgz.afp.modca_L;

import com.mgz.afp.base.IHasRepeatingGroups;
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
 * MO:DCA-L, page 12. <br><br> The Map Color Attribute Table structured field maps a unique Resource
 * Local ID to the name of a Begin Color Attribute Table structured field. A local ID may be
 * embedded one or more times within an objects data.
 *
 *
 * MO:DCA, page 383. <br><br> Map Color Attribute Table (MCA) structured field in MO:DCA-L data
 * streams. Note that the MO:DCA-L format has been functionally capped and is no longer defined in
 * the MO:DCA reference; for a definition of this format, see MO:DCA-L: The OS/2 Presentation
 * Manager Metafile (.met) Format.
 */

public class MCA_MapColorAttribteTable extends StructuredFieldBaseRepeatingGroups implements IHasRepeatingGroups {
  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    this.setRepeatingGroups(new ArrayList<IRepeatingGroup>());
    int pos = 0;
    while (pos < actualLength) {
      MCA_RepeatinGroup rg = new MCA_RepeatinGroup();
      rg.decodeAFP(sfData, offset + pos, -1, config);
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

  public static class MCA_RepeatinGroup extends RepeatingGroupWithTriplets {
  }
}
