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
package com.mgz.afp.parser;

import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.triplets.Triplet;
import com.mgz.afp.triplets.Triplet.TripletID;
import com.mgz.util.UtilBinaryDecoding;

public class TripletParser {
	
	/**
	 * Parses Triplets from given data, starting at position offset for length bytes.
	 * If parameter length = -1, it tries to parse all up to the end of sfData.
	 * @param sfData
	 * @param offset
	 * @param length
	 * @param config
	 * @return List of resulting Triplets contained in the given data.
	 */
	public static List<Triplet> parseTriplets(byte[] sfData, int offset, int length, AFPParserConfiguration config){
		List<Triplet> resultingTriplets = new ArrayList<Triplet>();
		
		int actualLength = StructuredField.getActualLength(sfData, offset, length);
		int pos =0;
		while(pos<actualLength){
			Triplet triplet;
			try{
				triplet = parseTriplet(sfData, offset +pos, actualLength -pos, config);
			}catch(AFPParserException pex){
				Triplet.Undefined undef = null;
				triplet = undef = new Triplet.Undefined();
				undef.setParsingException(pex);
				byte[] tripletData = new byte[actualLength];
				System.arraycopy(sfData, offset, tripletData, 0, actualLength);
				undef.setTripletData(tripletData);
				undef.setLength((short)(sfData[offset+pos] & 0xFF));
				undef.setTripletID(TripletID.Undefined);
			}
			resultingTriplets.add(triplet);
			
			pos+= triplet.getLength();
		}
		                                                                                                             
		
		return resultingTriplets;
	}
	
	public static Triplet parseTriplet(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException{
		Triplet resultingTriplet = null;
		
		short tripletLength = UtilBinaryDecoding.parseShort(sfData, offset, 1);
		short actualTripletID = UtilBinaryDecoding.parseShort(sfData, offset +1, 1);
		
		TripletID tripletID = null;

		if(actualTripletID != Triplet.UNFORTUNATE_TRIPLETID){
			try{
				tripletID = TripletID.valueOf(actualTripletID);
			}catch(AFPParserException ex){
				tripletID = TripletID.Undefined;
			}

		}else{
			// Handle unfortunate triplet ID.
			// Determine if it is a ResourceObjectType or ObjectFunctionSetSpecification_Retired;

			int significantNumber = UtilBinaryDecoding.parseInt(sfData, offset +4, 2);
			if(significantNumber==0x8000){
				// It is a ObjectFunctionSetSpecification_Retired
				tripletID = TripletID.ObjectFunctionSetSpecification_Retired;
			}else{
				// It is a ResourceObjectType
				tripletID = TripletID.ResourceObjectType;
			}
			
		}
		
		if(tripletID == TripletID.Undefined){
			resultingTriplet = new Triplet.Undefined();
			resultingTriplet.setLength(tripletLength);
			resultingTriplet.setTripletID(TripletID.Undefined);
		}else{
			resultingTriplet = createTripletInstance(tripletID);
			resultingTriplet.setLength(tripletLength);
		}
		
		resultingTriplet.decodeAFP(sfData, offset, tripletLength, config);
		
		return resultingTriplet;
	}
	
	public static final Triplet createTripletInstance(TripletID tid){
		Triplet cs = null;

		try{
			String classname = Triplet.class.getName()+ "$" + tid.name();
			Class<?> clazz  = Class.forName(classname);
			cs = (Triplet) clazz.newInstance();
		}catch(Exception cnfex){
			// NOP.
		}

		if(cs==null) cs = new Triplet.Undefined();
		cs.setTripletID(tid);

		return cs;
	}	
}
