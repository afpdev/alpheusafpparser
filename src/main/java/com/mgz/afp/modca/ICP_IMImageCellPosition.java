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
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

public class ICP_IMImageCellPosition extends StructuredField{
	short xOffset;
	short yOffset;
	int xSize;
	int ySize;
	int xSizeOfFillRectangle;
	int ySizeOfFillRectangle;
	
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData, offset, length, 12);
		
		xOffset = UtilBinaryDecoding.parseShort(sfData, offset, 2);
		yOffset = UtilBinaryDecoding.parseShort(sfData, offset +2, 2);
		xSize = UtilBinaryDecoding.parseInt(sfData, offset +4, 2);
		ySize = UtilBinaryDecoding.parseInt(sfData, offset +6, 2);
		xSizeOfFillRectangle = UtilBinaryDecoding.parseInt(sfData, offset +8, 2);
		ySizeOfFillRectangle = UtilBinaryDecoding.parseInt(sfData, offset +10, 2);
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(12);
		baos.write(UtilBinaryDecoding.shortToByteArray(xOffset, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(yOffset, 2));
		baos.write(UtilBinaryDecoding.intToByteArray(xSize, 2));
		baos.write(UtilBinaryDecoding.intToByteArray(ySize, 2));
		baos.write(UtilBinaryDecoding.intToByteArray(xSizeOfFillRectangle, 2));
		baos.write(UtilBinaryDecoding.intToByteArray(ySizeOfFillRectangle, 2));

		writeFullStructuredField(os, baos.toByteArray());
	}
}
