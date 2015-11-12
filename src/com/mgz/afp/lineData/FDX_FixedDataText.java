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
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;

/**
 *  Programming Guide and Line Data Reference(ha3l3r04.pdf), page 92.<br>
 *  <br>
 * The Fixed Data Text structured field contains text that can be selected and
presented with LND, RCD or XMD structured fields in the Page Definition. This
text is used when flag bit 7 of the LND, RCD or XMD is set to B'1'. Any number of
FDX structured fields can appear, but the total number of data bytes must match
bytes 0–1 of the Fixed Data Size (FDS) structured field. The output should fit on
the page, and the fit can be affected by the size of the font used.
 *
 */
public class FDX_FixedDataText extends StructuredField{
	@AFPField(maxSize=65535)
	String text;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException{
		int actualLength = getActualLength(sfData, offset, length);
		if(actualLength>0) text = new String(sfData,0,8,config.getAfpCharSet());
		else text = null;
	}
	

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException{
		if(text!=null) writeFullStructuredField(os,text.getBytes(config.getAfpCharSet()));
		else writeFullStructuredField(os,null);
	}

	public final void setText(String text){
		this.text=text;
	}

	public final String getText(){
		return text;
	}

}
