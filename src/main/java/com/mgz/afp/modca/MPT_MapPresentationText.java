/*
Copyright 2024 Alpheus AFP Parser Contributors

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MO:DCA, page 240.<br> <br> The Map Presentation Text structured field specifies the mapping
 * of a presentation text data object presentation space to an object area.
 */
public class MPT_MapPresentationText extends StructuredFieldBaseRepeatingGroups {
  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    repeatingGroups = new ArrayList<IRepeatingGroup>();

    int actualLength = getActualLength(sfData, offset, length);
    int pos = 0;
    while (pos < actualLength) {
      MPT_RepeatingGroup rg = new MPT_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      repeatingGroups.add(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (IRepeatingGroup rg : repeatingGroups) {
        rg.writeAFP(baos, config);
      }
    }
    writeFullStructuredField(os, baos.toByteArray());
  }

  @XmlRootElement
  public static class MPT_RepeatingGroup extends RepeatingGroupWithTriplets {
  }
}
