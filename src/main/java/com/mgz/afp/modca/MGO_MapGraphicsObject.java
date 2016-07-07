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

import com.mgz.afp.base.RepeatingGroupWithTriplets;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;

import java.io.IOException;
import java.io.OutputStream;

/**
 * MO:DCA, page 271.<br> <br> The Map Graphics Object structured field specifies how a graphics data
 * object is mapped into its object area.
 */
public class MGO_MapGraphicsObject extends StructuredFieldBaseRepeatingGroups {

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    MGO_RepeatinGroup rg = new MGO_RepeatinGroup();
    rg.decodeAFP(sfData, offset, length, config);
    addRepeatingGroup(rg);
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config)
          throws IOException {
    // TODO Auto-generated method stub

  }


  public static class MGO_RepeatinGroup extends RepeatingGroupWithTriplets {
  }
}
