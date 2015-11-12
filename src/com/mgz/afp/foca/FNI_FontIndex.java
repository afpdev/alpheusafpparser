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
package com.mgz.afp.foca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.foca.FNI_FontIndex.FNI_RepeatingGroup.ComparatorForFNIRepeatinGroups;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;


public class FNI_FontIndex extends StructuredField {
	/** Associated {@link FNC_FontControl} SF that specifies the repeating group length. */
	@AFPField
	List<FNI_RepeatingGroup> repeatingGroups;
	
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		if(config.getCurrentFontControl()==null){
			throw new AFPParserException(AFPParserConfiguration.class.getSimpleName()+": current font control is not set.");
		}
		int repeatingGroupLength = config.getCurrentFontControl().fniRepeatingGroupLength;
		int actualLength = getActualLength(sfData, offset, length);

		repeatingGroups = new ArrayList<FNI_RepeatingGroup>(actualLength / repeatingGroupLength);
		
		int pos = 0;
		while(pos<actualLength){
			FNI_RepeatingGroup repeatingGroup = new FNI_RepeatingGroup();
			repeatingGroup.decodeAFP(sfData, offset+pos, repeatingGroupLength, config);
			repeatingGroups.add(repeatingGroup);

			pos+=repeatingGroupLength;
		}
		
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		if(repeatingGroups!=null){
			if(repeatingGroups.size()>1){
				synchronized (repeatingGroups) {
					Collections.sort(repeatingGroups, new ComparatorForFNIRepeatinGroups());
				}				
			}
			for(FNI_RepeatingGroup rg : repeatingGroups){
				rg.writeAFP(baos, config);
			}
		}

		writeFullStructuredField(os, baos.toByteArray());
	}
	
	public static class FNI_RepeatingGroup implements IAFPDecodeableWriteable{
		/**
		 * Comparator used to sort {@link Collection}s of {@link FNI_RepeatingGroup}s by {@link FNI_RepeatingGroup#graphicCharacterGlobalID_GCGID}.
		 */
		public static class ComparatorForFNIRepeatinGroups implements Comparator<FNI_RepeatingGroup>{
			@Override
			public int compare(FNI_RepeatingGroup o1, FNI_RepeatingGroup o2) {
				return o1.graphicCharacterGlobalID_GCGID.compareTo(o2.graphicCharacterGlobalID_GCGID);
			}
		}
		
		@AFPField
		volatile short repeatingGroupLength;
		
		@AFPField
		String graphicCharacterGlobalID_GCGID;
		@AFPField
		short characterIncrement;
		@AFPField
		short ascenderHeight;
		@AFPField
		short descenderDepth;
		@AFPField(size=2)
		byte[] reserved14_15 = new byte[]{0x00,0x00};
		@AFPField
		int fnmIndex;
		@AFPField
		short ASpace;
		@AFPField
		short BSpace;
		@AFPField
		short CSpace;
		@AFPField(size=2)
		byte[] reserved24_25 = new byte[]{0x00,0x00};
		@AFPField
		short baselineOffset;
		
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			repeatingGroupLength = (short)(length!=-1 ? length : sfData.length-offset);
			
			graphicCharacterGlobalID_GCGID = new String(sfData,offset,8,Constants.cpIBM500);
			characterIncrement = UtilBinaryDecoding.parseShort(sfData, offset+8, 2);
			if(repeatingGroupLength>10){
				ascenderHeight = UtilBinaryDecoding.parseShort(sfData, offset+10, 2);
				descenderDepth = UtilBinaryDecoding.parseShort(sfData, offset+12, 2);
				reserved14_15 = Constants.EMPTYBYTES_2;
				fnmIndex = UtilBinaryDecoding.parseInt(sfData, offset + 16, 2);
				ASpace = UtilBinaryDecoding.parseShort(sfData, offset + 18, 2);
				BSpace = UtilBinaryDecoding.parseShort(sfData, offset + 20, 2);
				CSpace = UtilBinaryDecoding.parseShort(sfData, offset + 22, 2);
				reserved24_25 = Constants.EMPTYBYTES_2;
				baselineOffset = UtilBinaryDecoding.parseShort(sfData, offset + 26, 2);
			}else{
				ascenderHeight = -1;
				descenderDepth = -1;
				reserved14_15 = null;
				fnmIndex = -1;
				ASpace = -1;
				BSpace = -1;
				CSpace = -1;
				reserved24_25 = null;
				baselineOffset = -1;
			}
		}
		
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(UtilCharacterEncoding.stringToByteArray(graphicCharacterGlobalID_GCGID,Constants.cpIBM500,8,Constants.EBCDIC_ID_FILLER));
			os.write(UtilBinaryDecoding.shortToByteArray(characterIncrement, 2));
			if(repeatingGroupLength>10){
				os.write(UtilBinaryDecoding.shortToByteArray(ascenderHeight, 2));
				os.write(UtilBinaryDecoding.shortToByteArray(descenderDepth, 2));
				os.write(reserved14_15);
				os.write(UtilBinaryDecoding.intToByteArray(fnmIndex, 2));
				os.write(UtilBinaryDecoding.shortToByteArray(ASpace, 2));
				os.write(UtilBinaryDecoding.shortToByteArray(BSpace, 2));
				os.write(UtilBinaryDecoding.shortToByteArray(CSpace, 2));
				os.write(reserved24_25);
				os.write(UtilBinaryDecoding.shortToByteArray(baselineOffset, 2));
			}
		}
		public short getRepeatingGroupLength() {
			return repeatingGroupLength;
		}

		/**
		 * Sets the repeating group length.
		 * Use this only for newly created  {@link FNI_RepeatingGroup} where fields habe been set manually.
		 * If the {@link FNI_RepeatingGroup} is set by {@link #decodeAFP(byte[], int, int, AFPParserConfiguration)}, 
		 * the repeating group length is determined by the parameters.
		 * The length of the repeating group has the 
		 * @param repeatingGroupLength
		 */
		public void setRepeatingGroupLength(short repeatingGroupLength) {
			this.repeatingGroupLength = repeatingGroupLength;
		}

		public String getGraphicCharacterGlobalID_GCGID() {
			return graphicCharacterGlobalID_GCGID;
		}

		public void setGraphicCharacterGlobalID_GCGID(
				String graphicCharacterGlobalID_GCGID) {
			this.graphicCharacterGlobalID_GCGID = graphicCharacterGlobalID_GCGID;
		}

		public short getCharacterIncrement() {
			return characterIncrement;
		}

		public void setCharacterIncrement(short characterIncrement) {
			this.characterIncrement = characterIncrement;
		}

		public short getAscenderHeight() {
			return ascenderHeight;
		}

		public void setAscenderHeight(short ascenderHeight) {
			this.ascenderHeight = ascenderHeight;
		}

		public short getDescenderDepth() {
			return descenderDepth;
		}

		public void setDescenderDepth(short descenderDepth) {
			this.descenderDepth = descenderDepth;
		}

		public byte[] getReserved14_15() {
			return reserved14_15;
		}

		public void setReserved14_15(byte[] reserved14_15) {
			this.reserved14_15 = reserved14_15;
		}

		public int getFnmIndex() {
			return fnmIndex;
		}

		public void setFnmIndex(int fnmIndex) {
			this.fnmIndex = fnmIndex;
		}

		public short getASpace() {
			return ASpace;
		}

		public void setASpace(short aSpace) {
			ASpace = aSpace;
		}

		public short getBSpace() {
			return BSpace;
		}

		public void setBSpace(short bSpace) {
			BSpace = bSpace;
		}

		public short getCSpace() {
			return CSpace;
		}

		public void setCSpace(short cSpace) {
			CSpace = cSpace;
		}

		public byte[] getReserved24_25() {
			return reserved24_25;
		}

		public void setReserved24_25(byte[] reserved24_25) {
			this.reserved24_25 = reserved24_25;
		}

		public short getBaselineOffset() {
			return baselineOffset;
		}

		public void setBaselineOffset(short baselineOffset) {
			this.baselineOffset = baselineOffset;
		}
	}


	/**
	 * Returns the list of {@link FNI_RepeatingGroup}s.
	 * The returning list is ordered by {@link FNI_RepeatingGroup#graphicCharacterGlobalID_GCGID}.
	 * @return ordered list of {@link FNI_RepeatingGroup}s.
	 */
	public List<FNI_RepeatingGroup> getRepeatingGroups() {
		if(repeatingGroups!=null && repeatingGroups.size()>1){
			synchronized (repeatingGroups) {
				Collections.sort(repeatingGroups, new ComparatorForFNIRepeatinGroups());	
			}
		}
		return repeatingGroups;
	}

	/**
	 * Set the list of {@link FNI_RepeatingGroup}s.
	 * Orderes the given list by {@link FNI_RepeatingGroup#graphicCharacterGlobalID_GCGID}.
	 * @param repeatingGroups list of {@link FNI_RepeatingGroup}s.
	 */
	public void setRepeatingGroups(List<FNI_RepeatingGroup> repeatingGroups) {
		this.repeatingGroups = repeatingGroups;
		synchronized (repeatingGroups) {
			Collections.sort(repeatingGroups, new ComparatorForFNIRepeatinGroups());	
		}		
	}	
	
	/**
	 * Adds the given {@link FNI_RepeatingGroup} to the list of repeating groups and orders the list by {@link FNI_RepeatingGroup#graphicCharacterGlobalID_GCGID}.
	 * @param repeatinGroup
	 */
	public void addRepeatingGroup(FNI_RepeatingGroup repeatinGroup){
		if(repeatingGroups==null){
			repeatingGroups = new ArrayList<FNI_FontIndex.FNI_RepeatingGroup>();
			repeatingGroups.add(repeatinGroup);
		}else{
			repeatingGroups.add(repeatinGroup);
			synchronized (repeatingGroups) {
				Collections.sort(repeatingGroups, new ComparatorForFNIRepeatinGroups());	
			}
		}
	}
	
}
