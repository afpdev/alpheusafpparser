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

import com.mgz.afp.base.StructuredFieldBaseTriplets;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;


/**
 * MO:DCA, page 220.<br>
 * and Programming Guide and Line Data Reference(ha3l3r04.pdf), page 101.<br>
 * <br>
 * The Include Page Segment structured field references a page segment resource
object that is to be positioned on the page or overlay. For line-mode or
mixed-mode applications only, a value of X'FFFFFF' may be used for either the
{@link #xOrigin}, the {@link #yOrigin}, or both.<br>
<br>
The Include Page Segment structured field references a page segment resource
object that is to be presented on the page or overlay presentation space. The IPS
specifies a reference point on the including page or overlay coordinate system that
may be used to position objects contained in the page segment. A page segment
can be referenced at any time during page or overlay state, but not during an
object state. The page segment inherits the active environment group definition of
the including page or overlay.

 */
public class IPS_IncludePageSegment extends StructuredFieldBaseTriplets {
	String pageSegmentName;
	int xOrigin;
	int yOrigin;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		pageSegmentName = new String(sfData,offset,8,config.getAfpCharSet());
		xOrigin = UtilBinaryDecoding.parseInt(sfData, offset +8, 3);
		yOrigin = UtilBinaryDecoding.parseInt(sfData, offset +11, 3);

		int actualLength = getActualLength(sfData, offset, length);
		if(actualLength>14){
			super.decodeAFP(sfData, offset +16, actualLength -16, config);
		}else{
			triplets = null;
		}
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(UtilCharacterEncoding.stringToByteArray(pageSegmentName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
		baos.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
		baos.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
		if(triplets!=null){
			for(Triplet t : triplets) t.writeAFP(baos, config);
		}
		writeFullStructuredField(os, baos.toByteArray());
	}

	public String getPageSegmentName() {
		return pageSegmentName;
	}

	public void setPageSegmentName(String pageSegmentName) {
		this.pageSegmentName = pageSegmentName;
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
}
