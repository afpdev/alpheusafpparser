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
import java.util.ArrayList;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

public class MCF_MapCodedFont_Format1 extends StructuredField {
	short lengthOfRepeatingGroup;
	byte[] reserved1_3 = new byte[3];
	List<MCF_RepeatingGroup> repeatingGroups;
	
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		lengthOfRepeatingGroup = UtilBinaryDecoding.parseShort(sfData, offset, 1);
		reserved1_3 = new byte[3];
		System.arraycopy(sfData, offset +1, reserved1_3, 0, reserved1_3.length);
		
		int actualLength = getActualLength(sfData, offset, length);
		if(actualLength>4){
			repeatingGroups = new ArrayList<MCF_MapCodedFont_Format1.MCF_RepeatingGroup>((actualLength-4)/lengthOfRepeatingGroup);
			int pos = 4;
			while(pos<actualLength){
				MCF_RepeatingGroup rg = new MCF_RepeatingGroup();
				rg.decodeAFP(sfData, offset +pos, lengthOfRepeatingGroup, config);
				repeatingGroups.add(rg);
				pos+=lengthOfRepeatingGroup;
			}
		}else{
			repeatingGroups = null;
		}
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(lengthOfRepeatingGroup);
		baos.write(reserved1_3);
		if(repeatingGroups!=null){
			for(MCF_RepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);
		}
		
		writeFullStructuredField(os, baos.toByteArray());
	}
	
	public short getLengthOfRepeatingGroup() {
		return lengthOfRepeatingGroup;
	}

	public void setLengthOfRepeatingGroup(short lengthOfRepeatingGroup) {
		this.lengthOfRepeatingGroup = lengthOfRepeatingGroup;
	}

	public byte[] getReserved1_3() {
		return reserved1_3;
	}

	public void setReserved1_3(byte[] reserved1_3) {
		this.reserved1_3 = reserved1_3;
	}

	public List<MCF_RepeatingGroup> getRepeatingGroups() {
		return repeatingGroups;
	}

	public void setRepeatingGroups(List<MCF_RepeatingGroup> repeatingGroups) {
		this.repeatingGroups = repeatingGroups;
	}
	
	public void addRepeatingGroup(MCF_RepeatingGroup repeatingGroup){
		if(repeatingGroup==null) return;
		if(this.repeatingGroups==null) repeatingGroups = new ArrayList<MCF_MapCodedFont_Format1.MCF_RepeatingGroup>();
		repeatingGroups.add(repeatingGroup);
	}
	
	public void removeRepeatingGroup(MCF_RepeatingGroup repeatingGroup){
		if(this.repeatingGroups==null) return;
		repeatingGroups.remove(repeatingGroup);
	}
	
	@AFPType
	public static class MCF_RepeatingGroup implements IAFPDecodeableWriteable{
		short codedFontLocalID;
		byte reserved1 = 0x00;
		short codedFontSectionID;
		byte reserved3 = 0x00;
		String codedFontName;
		byte[] codedFontNullName;
		String codePageName;
		byte[] codePageNullName;
		String fontCharacterSetName;
		byte[] fontCharacterSetNullName;
		AFPOrientation characterRotation;
		
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			codedFontLocalID = UtilBinaryDecoding.parseShort(sfData, offset, 1);
			reserved1 = sfData[offset +1];
			codedFontSectionID = UtilBinaryDecoding.parseShort(sfData, offset +2, 1);
			reserved3 = sfData[offset +3];
			int hint = UtilBinaryDecoding.parseInt(sfData, offset +4, 2);
			if(hint!=0xFFFF){
				codedFontNullName = null;
				codedFontName = new String(sfData, offset +4, 8, config.getAfpCharSet());
			}else{
				codedFontName = null;
				codedFontNullName = new byte[8];
				System.arraycopy(sfData, offset +4, codedFontNullName, 0, codedFontNullName.length);
			}
			hint = UtilBinaryDecoding.parseInt(sfData, offset +12, 2);
			if(hint!=0xFFFF){
				codePageNullName = null;
				codePageName = new String(sfData, offset +12, 8, config.getAfpCharSet());
			}else{
				codePageName = null;
				codePageNullName = new byte[8];
				System.arraycopy(sfData, offset +12, codePageNullName, 0, codePageNullName.length);
			}
			hint = UtilBinaryDecoding.parseInt(sfData, offset +20, 2);
			if(hint!=0xFFFF){
				fontCharacterSetNullName = null;
				fontCharacterSetName = new String(sfData, offset +20, 8, config.getAfpCharSet());
			}else{
				fontCharacterSetName = null;
				fontCharacterSetNullName = new byte[8];
				System.arraycopy(sfData, offset +20, fontCharacterSetNullName, 0, fontCharacterSetNullName.length);
			}
			
			int actualLength = getActualLength(sfData, offset, length);
			if(actualLength==30){
				characterRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 28, 2));
			}else{
				characterRotation = null;
			}
		}


		
		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(codedFontLocalID);
			os.write(reserved1);
			os.write(codedFontSectionID);
			os.write(reserved3);
			if(codedFontName!=null) os.write(UtilCharacterEncoding.stringToByteArray(codedFontName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
			else os.write(codedFontNullName);
			if(codePageName!=null) os.write(UtilCharacterEncoding.stringToByteArray(codePageName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
			else os.write(codePageNullName);
			if(fontCharacterSetName!=null) os.write(UtilCharacterEncoding.stringToByteArray(fontCharacterSetName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
			else os.write(fontCharacterSetNullName);
			if(characterRotation!=null) os.write(characterRotation.toBytes());
		}

		public short getCodedFontLocalID() {
			return codedFontLocalID;
		}

		public void setCodedFontLocalID(short codedFontLocalID) {
			this.codedFontLocalID = codedFontLocalID;
		}

		public byte getReserved1() {
			return reserved1;
		}

		public void setReserved1(byte reserved1) {
			this.reserved1 = reserved1;
		}

		public short getCodedFontSectionID() {
			return codedFontSectionID;
		}

		public void setCodedFontSectionID(short codedFontSectionID) {
			this.codedFontSectionID = codedFontSectionID;
		}

		public byte getReserved3() {
			return reserved3;
		}

		public void setReserved3(byte reserved3) {
			this.reserved3 = reserved3;
		}

		public String getCodedFontName() {
			return codedFontName;
		}

		public void setCodedFontName(String codedFontName) {
			this.codedFontName = codedFontName;
		}

		public byte[] getCodedFontNullName() {
			return codedFontNullName;
		}

		public void setCodedFontNullName(byte[] codedFontNullName) {
			this.codedFontNullName = codedFontNullName;
		}

		public String getCodePageName() {
			return codePageName;
		}

		public void setCodePageName(String codePageName) {
			this.codePageName = codePageName;
		}

		public byte[] getCodePageNullName() {
			return codePageNullName;
		}

		public void setCodePageNullName(byte[] codePageNullName) {
			this.codePageNullName = codePageNullName;
		}

		public String getFontCharacterSetName() {
			return fontCharacterSetName;
		}

		public void setFontCharacterSetName(String fontCharacterSetName) {
			this.fontCharacterSetName = fontCharacterSetName;
		}

		public byte[] getFontCharacterSetNullName() {
			return fontCharacterSetNullName;
		}

		public void setFontCharacterSetNullName(byte[] fontCharacterSetNullName) {
			this.fontCharacterSetNullName = fontCharacterSetNullName;
		}

		public AFPOrientation getCharacterRotation() {
			return characterRotation;
		}

		public void setCharacterRotation(AFPOrientation characterRotation) {
			this.characterRotation = characterRotation;
		}
	}
}
