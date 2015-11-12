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

/**
 * MO:DCA, page 262.<br>
 * <br>
 * The Medium Finishing Control structured field specifies the finishing requirements
for physical media. Finishing can be specified for a media collection at the print file
level or at the document level by placing the MFC in the document environment
group (DEG) of the form map. Finishing can be specified for a media collection at
the medium map level by placing the MFC in a medium map. Finishing can be
specified for individual media, or sheets, at the medium map level by placing the
MFC in a medium map.
 *
 */
public class MFC_MediumFinishingControl extends StructuredFieldBaseTriplets {
	public static enum MFC_Flag{
		ProcessAsNOP,
		ProcessAsSpecified;
		public static MFC_Flag valueOf(byte codeByte){
			if(codeByte == 0x00) return ProcessAsNOP;
			else return ProcessAsSpecified;
		}
		public int toByte(){ 
			if(this==ProcessAsNOP) return 0x00;
			else return 0x80;
		}
	}
	public static enum MFC_BoundaryConditionForSheetCollection{
		NoSheetCollection,
		BeginSheetCollection,
		ContinueSheetCollection;
		public static MFC_BoundaryConditionForSheetCollection valueOf(byte codeByte){
			for(MFC_BoundaryConditionForSheetCollection c : values()) if(codeByte == c.ordinal()) return c;
			return null;
		}
		public int toByte(){ return this.ordinal(); }
	}
	public static enum MFC_Scope{
		PrintFileLevel,
		DocumentLevel_AllDocuments,
		DocumentLevel_SelectedDocument,
		MediumMapLevel_EachMediumOrSheet,
		MediumMapLevel_CollectionOfMediaOrSheet,
		PrintjobLevel_RETIRED;
		public static MFC_Scope valueOf(byte codeByte){
			for(MFC_Scope c : values()) if(codeByte == (c.ordinal()+1)) return c;
			return null;
		}
		public int toByte(){ return this.ordinal()+1; }
	}
	
	MFC_Flag flag;
	byte reserved1 = 0x00;
	MFC_BoundaryConditionForSheetCollection boundaryConditionForSheetCollection;
	MFC_Scope scope;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		flag = MFC_Flag.valueOf(sfData[offset]);
		reserved1 = sfData[offset +1];
		boundaryConditionForSheetCollection = MFC_BoundaryConditionForSheetCollection.valueOf(sfData[offset +2]);
		scope = MFC_Scope.valueOf(sfData[offset +3]);
		
		super.decodeAFP(sfData, offset +4, getActualLength(sfData, offset, length)-4, config);
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(flag.toByte());
		baos.write(reserved1);
		baos.write(boundaryConditionForSheetCollection.toByte());
		baos.write(scope.toByte());
		for(Triplet t: triplets) t.writeAFP(baos, config);
		
		writeFullStructuredField(os, baos.toByteArray());
	}

	public MFC_Flag getFlag() {
		return flag;
	}

	public void setFlag(MFC_Flag flag) {
		this.flag = flag;
	}

	public byte getReserved1() {
		return reserved1;
	}

	public void setReserved1(byte reserved1) {
		this.reserved1 = reserved1;
	}

	public MFC_BoundaryConditionForSheetCollection getBoundaryConditionForSheetCollection() {
		return boundaryConditionForSheetCollection;
	}

	public void setBoundaryConditionForSheetCollection(
			MFC_BoundaryConditionForSheetCollection boundaryConditionForSheetCollection) {
		this.boundaryConditionForSheetCollection = boundaryConditionForSheetCollection;
	}

	public MFC_Scope getScope() {
		return scope;
	}

	public void setScope(MFC_Scope scope) {
		this.scope = scope;
	}
}
