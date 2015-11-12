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
package com.mgz.afp.lineData;

import java.io.IOException;
import java.io.OutputStream;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

/**
 * Programming Guide and Line Data Reference (ha3l3r04.pdf), page 91<br>
 * <br>
 * The {@link FDS_FixedDataSize} structured field specifies the number of bytes of text found in
 * the following {@link FDX_FixedDataText} structured fields.
 */
public class FDS_FixedDataSize extends StructuredField {
	int numberOfDataBytesInFollowingFDX;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData, offset, length, 2);
		numberOfDataBytesInFollowingFDX = UtilBinaryDecoding.parseInt(sfData, offset, 2);
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		writeFullStructuredField(os, UtilBinaryDecoding.intToByteArray(numberOfDataBytesInFollowingFDX, 2));
	}

	/**
	 * Number of data bytes in following FDX structured fields
	 * @return number of data bytes in following FDX structured fields.
	 */
	public int getNumberOfDataBytesInFollowingFDX() {
		return numberOfDataBytesInFollowingFDX;
	}

	/**
	 * Sets the number of data bytes in following FDX structured fields.
	 * @param numberOfDataBytesInFollowingFDX
	 */
	public void setNumberOfDataBytesInFollowingFDX(
			int numberOfDataBytesInFollowingFDX) {
		this.numberOfDataBytesInFollowingFDX = numberOfDataBytesInFollowingFDX;
	}
}
