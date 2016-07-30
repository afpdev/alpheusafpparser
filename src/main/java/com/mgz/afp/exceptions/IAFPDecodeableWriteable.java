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
package com.mgz.afp.exceptions;

import java.io.IOException;
import java.io.OutputStream;

import com.mgz.afp.parser.AFPParserConfiguration;

/**
 * Something that can be decoded from binary AFP data, and written to an {@link OutputStream} as binary AFP data.
 */
public interface IAFPDecodeableWriteable {
	/**
	 * Decodes the given AFP data.<br>
	 * Parameter length specifies the length in bytes of the data to be decoded, beginning with position offset. 
	 * If parameter length has a value of -1, the given data array is decoded up to the end of the array. 
	 * 
	 * @param sfData contains the AFP data to decode.
	 * @param offset the byte index position where the decoding should start.
	 * @param length the length in bytes of the data to be decoded, beginning with position offset. 
	 * @param config contains parameter used for decoding.
	 * @throws AFPParserException if the given AFP data are invalid.
	 */
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException;
	
	
	/**
	 * Writes the object to the given os encoded as AFP data.
	 * @param os {@link OutputStream} to write to.
	 * @param config contains parameter used for encoding.
	 * @throws IOException if a problem occurs while writing to the given {@link OutputStream}.
	 */
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException;
}
