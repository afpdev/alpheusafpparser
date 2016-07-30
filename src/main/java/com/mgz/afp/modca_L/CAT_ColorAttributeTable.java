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
package com.mgz.afp.modca_L;

import java.io.IOException;
import java.io.OutputStream;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

/**
The color table is preceded by the Begin Color Attribute Table structured field and
is terminated by the End Color Attribute Table structured field. Within this bracket,
the color table definition is carried in a set of Color Attribute Table structured
fields.
 * 
The definition consists of a base part, followed by one or more self-defining
parameters (SDP). Each SDP defines a set of entries to be loaded into the color
table.
 */
public class CAT_ColorAttributeTable extends StructuredField{
	CAT_BasePart basePart;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
	}
	
	/**
	 * The base part defines the initialization conditions for the color table.
	 */
	public static class CAT_BasePart implements IAFPDecodeableWriteable{
		ResetLCTFlag resetLCTFlag;
		byte reserved1 = 0x00;
		short colorTableLocalID;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			checkDataLength(sfData, offset, length, 3);
			resetLCTFlag = ResetLCTFlag.valueOf(sfData[offset]);
			reserved1 = sfData[offset +1];
			colorTableLocalID = UtilBinaryDecoding.parseShort(sfData, offset +2, 1);
		}


		
		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(resetLCTFlag.toByte());
			os.write(reserved1);
			os.write(colorTableLocalID);
		}
		
		public static enum ResetLCTFlag{
			DoNotResetLCT,
			ResetLCT;
			public static ResetLCTFlag valueOf(byte flagByte){
				if(flagByte==0x00) return DoNotResetLCT;
				else return ResetLCT;
			}
			public int toByte(){
				if(this==DoNotResetLCT) return 0x00;
				else return 0x80;
			}
		}

		public ResetLCTFlag getResetLCTFlag() {
			return resetLCTFlag;
		}

		public void setResetLCTFlag(ResetLCTFlag resetLCTFlag) {
			this.resetLCTFlag = resetLCTFlag;
		}

		public byte getReserved1() {
			return reserved1;
		}

		public void setReserved1(byte reserved1) {
			this.reserved1 = reserved1;
		}

		public short getColorTableLocalID() {
			return colorTableLocalID;
		}

		public void setColorTableLocalID(short colorTableLocalID) {
			this.colorTableLocalID = colorTableLocalID;
		}
	}
	
}
