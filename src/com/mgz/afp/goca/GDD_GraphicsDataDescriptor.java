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
package com.mgz.afp.goca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

public class GDD_GraphicsDataDescriptor extends StructuredField {
	List<GDD_Parameter> gddParameters;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		int actualLength = length!=-1 ? length : sfData.length - offset;
		
		gddParameters = new ArrayList<GDD_Parameter>();
		
		int pos=0;
		while(pos<actualLength){
			int paramLength = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2)+1;
			
			GDD_Parameter gddParameter = GDD_Parameter.buildGDDParameter(sfData,offset+pos,paramLength,config);
			gddParameters.add(gddParameter);
			
			pos+=paramLength;
		}
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		byte[] netData = null;
		if(gddParameters!=null){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for(GDD_Parameter gddParameter : gddParameters){
				gddParameter.writeAFP(baos, config);
			}
			netData=baos.toByteArray();
		}
		
		super.writeFullStructuredField(os, netData);
	}
}