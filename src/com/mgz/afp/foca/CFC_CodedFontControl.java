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
package com.mgz.afp.foca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.IHasTriplets;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;

/**
 * The Coded Font Control (CFC) structured field specifies the length of the repeating group in the Coded Font Index (CFI) structured field.
 */
public class CFC_CodedFontControl extends StructuredField implements IHasTriplets{
	/** This is a control parameter, used to manage the data structures. The value contained in this parameter defines the length of the repeating group used in the Coded Font Index (CFI) structured field. */
	@AFPField
	public static final int CFIRepeatingGroupLength = 0x19;
	/** This is a retired parameter which must be set to a constant value of X'01'. No significance should be attached to the value by any using application, but any font generator should set the value as indicated. */
	@AFPField
	public static final int RETIRED = 0x01;
	@AFPField
	List<Triplet> triplets;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData,offset,length,-1);
		
		TripletParser.parseTriplets(sfData, 2, -1, config);
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(CFIRepeatingGroupLength);
		baos.write(RETIRED);
		if(triplets!=null){
			for(Triplet triplet : triplets){
				triplet.writeAFP(baos, config);
			}
		}
		
		writeFullStructuredField(os, baos.toByteArray());
	}	
	
	public final List<Triplet> getTriplets() {
		return triplets;
	}

	public final void setTriplets(List<Triplet> triplets) {
		this.triplets = triplets;
	}
	
	public final void addTriplet(Triplet triplet){
		if(triplet==null) return;
		if(triplets==null) triplets = new ArrayList<Triplet>();
		triplets.add(triplet);
	}

	public final void removeTriplet(Triplet triplet){
		if(triplets==null) return;
		triplets.remove(triplet);
	}	
}
