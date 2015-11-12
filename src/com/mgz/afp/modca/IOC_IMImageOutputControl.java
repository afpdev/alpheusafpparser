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

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

public class IOC_IMImageOutputControl extends StructuredField {
	int xOrigin;
	int yOrigin;
	AFPOrientation xRotation;
	AFPOrientation yRotation;
	byte[] constantData10_17;
	ImageMapping xImageMapping;
	ImageMapping yImageMapping;
	byte[] constantData22_23;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		xOrigin = UtilBinaryDecoding.parseInt(sfData, offset, 3);
		yOrigin = UtilBinaryDecoding.parseInt(sfData, offset +3, 3);
		xRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +6, 2));
		yRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +8, 2));
		constantData10_17 = new byte[8];
		System.arraycopy(sfData, offset +10, constantData10_17, 0, constantData10_17.length);
		xImageMapping = ImageMapping.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +18, 2));
		yImageMapping = ImageMapping.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +20, 2));
		int actualLength = getActualLength(sfData, offset, length);
		// Error in MODCA spec: field constantData22_23 is optional.
		if(actualLength>22){
			constantData22_23 = new byte[2];
			System.arraycopy(sfData, offset +22, constantData22_23, 0, constantData22_23.length);
		}else{
			constantData22_23 = null;
		}
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(24);
		baos.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
		baos.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
		baos.write(xRotation.toBytes());
		baos.write(yRotation.toBytes());
		baos.write(constantData10_17);
		baos.write(xImageMapping.toBytes());
		baos.write(yImageMapping.toBytes());
		if(constantData22_23!=null) baos.write(constantData22_23);
		
		writeFullStructuredField(os, baos.toByteArray());
	}
	
	public static enum ImageMapping{
		ImagePointToOnePel(0x03E8),
		ImagePointToTwoPel(0x07D0);
		int code;
		ImageMapping(int code){ this.code = code;}
		public static ImageMapping valueOf(short code){
			for(ImageMapping im : values()) if(im.code==code) return im;
			return null;
		}
		public byte[] toBytes(){ return UtilBinaryDecoding.intToByteArray(code, 2); }
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

	public byte[] getConstantData10_17() {
		return constantData10_17;
	}

	public void setConstantData10_17(byte[] constantData10_17) {
		this.constantData10_17 = constantData10_17;
	}

	public ImageMapping getxImageMapping() {
		return xImageMapping;
	}

	public void setxImageMapping(ImageMapping xImageMapping) {
		this.xImageMapping = xImageMapping;
	}

	public ImageMapping getyImageMapping() {
		return yImageMapping;
	}

	public void setyImageMapping(ImageMapping yImageMapping) {
		this.yImageMapping = yImageMapping;
	}

	public byte[] getConstantData22_23() {
		return constantData22_23;
	}

	public void setConstantData22_23(byte[] constantData22_23) {
		this.constantData22_23 = constantData22_23;
	}
}
