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
package com.mgz.afp.ptoca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.PTOCAControlSequenceParser;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import com.mgz.util.UtilBinaryDecoding;

public class PTD_PresentationTextDataDescriptor_Format2 extends StructuredField {
	AFPUnitBase xUnitBase;
	AFPUnitBase yUnitBase;
	short xUnitsPerUnitBase;
	short yUnitsPerUnitBase;
	short xSize;
	short ySize;
	byte[] reserved12_13;
	List<PTOCAControlSequence> controlSequences;
	
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData, offset, length, 12);
		
		xUnitBase = AFPUnitBase.valueOf(sfData[offset]);
		yUnitBase = AFPUnitBase.valueOf(sfData[offset +1]);
		xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset +2, 2);
		yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset +4, 2);
		xSize = UtilBinaryDecoding.parseShort(sfData, offset +7, 2);
		ySize = UtilBinaryDecoding.parseShort(sfData, offset +10, 2);

		int actualLength = StructuredField.getActualLength(sfData,offset,length);
		if(actualLength>13){
			reserved12_13 = new byte[2];
			System.arraycopy(sfData, offset +12, reserved12_13, 0, reserved12_13.length);
			
			if(actualLength>15){
				controlSequences = PTOCAControlSequenceParser.parseControlSequences(sfData, offset +15, -1, config);
			}else{
				controlSequences = null;
			}			
		}else{
			reserved12_13 = null;
			controlSequences = null;
		}
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(xUnitBase.toByte());
		baos.write(yUnitBase.toByte());
		baos.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(xSize, 3));
		baos.write(UtilBinaryDecoding.shortToByteArray(ySize, 3));
		if(reserved12_13!=null){
			baos.write(reserved12_13,0,2);
		}
		
		writeFullStructuredField(os, baos.toByteArray());
	}

	public AFPUnitBase getxUnitBase() {
		return xUnitBase;
	}

	public void setxUnitBase(AFPUnitBase xUnitBase) {
		this.xUnitBase = xUnitBase;
	}

	public AFPUnitBase getyUnitBase() {
		return yUnitBase;
	}

	public void setyUnitBase(AFPUnitBase yUnitBase) {
		this.yUnitBase = yUnitBase;
	}

	public short getxUnitsPerUnitBase() {
		return xUnitsPerUnitBase;
	}

	public void setxUnitsPerUnitBase(short xUnitsPerUnitBase) {
		this.xUnitsPerUnitBase = xUnitsPerUnitBase;
	}

	public short getyUnitsPerUnitBase() {
		return yUnitsPerUnitBase;
	}

	public void setyUnitsPerUnitBase(short yUnitsPerUnitBase) {
		this.yUnitsPerUnitBase = yUnitsPerUnitBase;
	}

	public short getxSize() {
		return xSize;
	}

	public void setxSize(short xSize) {
		this.xSize = xSize;
	}

	public short getySize() {
		return ySize;
	}

	public void setySize(short ySize) {
		this.ySize = ySize;
	}

	public byte[] getReserved12_13() {
		return reserved12_13;
	}

	public void setReserved12_13(byte[] reserved12_13) {
		this.reserved12_13 = reserved12_13;
	}

	public List<PTOCAControlSequence> getControlSeqences() {
		return controlSequences;
	}

	public void setControlSeqences(List<PTOCAControlSequence> controlSeqences) {
		this.controlSequences = controlSeqences;
	}
	
	public void addControlSequence(PTOCAControlSequence controlSequence){
		if(controlSequence==null) return;
		if(controlSequences==null) controlSequences = new ArrayList<PTOCAControlSequence>();
		controlSequences.add(controlSequence);
	}

	public void remoceControlSequence(PTOCAControlSequence controlSequence){
		if(controlSequences==null) return;
		controlSequences.remove(controlSequence);
	}
}
