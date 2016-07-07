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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.RepeatingGroupWithTriplets;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;

/**
 * MO:DCA, page 288.<br>
 * <br> 
The Map Media Type structured field maps a media type local ID to the name or
OID of a media type. See “Media Type Identifiers” on page 639 for a list of media
types registered by their name and their OID.
 */
public class MMT_MapMediaType extends StructuredFieldBaseRepeatingGroups {

	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		int actualLength = getActualLength(sfData, offset, length);
		int pos = 0;
		while(pos<actualLength){
			MMT_RepeatinGroup rg = new MMT_RepeatinGroup();
			rg.decodeAFP(sfData, offset +pos, actualLength -pos, config);
			addRepeatingGroup(rg);
			pos+=rg.getRepeatingGroupLength();
		}
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for(IRepeatingGroup rg :repeatingGroups) rg.writeAFP(baos, config);
		writeFullStructuredField(os, baos.toByteArray());
	}

	public static class MMT_RepeatinGroup extends RepeatingGroupWithTriplets{
	}
}
