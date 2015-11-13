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
import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.StructuredFieldBaseName;
import com.mgz.afp.enums.AFPObjectType;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

/**
 * MO:DCA, page 200.<br>
 * and Programming Guide and Line Data Reference(ha3l3r04.pdf), page 200.<br>
 * <br>
An Include Object structured field references an object and optionally contains
parameters that identify the object and that specify presentation parameters such
as object position, size, orientation, mapping, and default color. Where the
presentation parameters conflict with parameters specified in the object’s
environment group (OEG), the parameters in the Include Object structured field
override. If the referenced object is a page segment, the IOB parameters override
the corresponding environment group parameters on all data objects in the page
segment.
 */
public class IOB_IncludeObject extends StructuredFieldBaseName {
	byte reserved8 = 0x00;
	AFPObjectType objectType;
	int xOrigin;
	int yOrigin;
	AFPOrientation xRotation;
	AFPOrientation yRotation;
	int xOriginOfContent;
	int yOriginOfContent;
	byte referenceCoordinateSystem;
	List<Triplet> triplets;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData, offset, length, 28);
		super.decodeAFP(sfData, offset, length, config); // Decode name.
		reserved8 = sfData[offset +8];
		objectType = AFPObjectType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +9, 1));
		xOrigin = UtilBinaryDecoding.parseInt(sfData, offset +10, 3);
		yOrigin = UtilBinaryDecoding.parseInt(sfData, offset +13, 3);
		xRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset +16, 2));
		yRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset +18, 2));
		xOriginOfContent = UtilBinaryDecoding.parseInt(sfData, offset +20, 3);
		yOriginOfContent = UtilBinaryDecoding.parseInt(sfData, offset +23, 3);
		referenceCoordinateSystem = sfData[26];
		
		triplets = TripletParser.parseTriplets(sfData, offset +27, -1, config);
		
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(UtilCharacterEncoding.stringToByteArray(getName(), config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
		baos.write(reserved8);
		baos.write(objectType.toByte());
		baos.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
		baos.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
		baos.write(xRotation.toBytes());
		baos.write(yRotation.toBytes());
		baos.write(UtilBinaryDecoding.intToByteArray(xOriginOfContent, 3));
		baos.write(UtilBinaryDecoding.intToByteArray(yOriginOfContent, 3));
		baos.write(referenceCoordinateSystem);
		if(triplets!=null){
			for(Triplet triplet : triplets) triplet.writeAFP(baos, config);
		}
		writeFullStructuredField(os, baos.toByteArray());
	}

	public byte getReserved8() {
		return reserved8;
	}

	public void setReserved8(byte reserved8) {
		this.reserved8 = reserved8;
	}

	public AFPObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(AFPObjectType objectType) {
		this.objectType = objectType;
	}

	public int getxOrigin() {
		return xOrigin;
	}

	public void setxOrigin(int xOrigin) {
		this.xOrigin = xOrigin;
	}

	public int getyOrigin() {
		return yOrigin;
	}

	public void setyOrigin(int yOrigin) {
		this.yOrigin = yOrigin;
	}

	public AFPOrientation getxRotation() {
		return xRotation;
	}

	public void setxRotation(AFPOrientation xRotation) {
		this.xRotation = xRotation;
	}

	public AFPOrientation getyRotation() {
		return yRotation;
	}

	public void setyRotation(AFPOrientation yRotation) {
		this.yRotation = yRotation;
	}

	public int getxOriginOfContent() {
		return xOriginOfContent;
	}

	public void setxOriginOfContent(int xOriginOfContent) {
		this.xOriginOfContent = xOriginOfContent;
	}

	public int getyOriginOfContent() {
		return yOriginOfContent;
	}

	public void setyOriginOfContent(int yOriginOfContent) {
		this.yOriginOfContent = yOriginOfContent;
	}

	public byte getReferenceCoordinateSystem() {
		return referenceCoordinateSystem;
	}

	public void setReferenceCoordinateSystem(byte referenceCoordinateSystem) {
		this.referenceCoordinateSystem = referenceCoordinateSystem;
	}

	public List<Triplet> getTriplets() {
		return triplets;
	}

	public void setTriplets(List<Triplet> triplets) {
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
