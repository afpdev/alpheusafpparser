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
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

/**
 * MO:DCA, page 274.<br>
 * <br>
The Medium Modification Control structured field specifies the medium
modifications to be applied for a copy subgroup specified in the Medium Copy
Count (MCC) structured field.
 */
public class MMC_MediumModificationControl extends StructuredField {
	byte mmcIdentifier;
	byte constantData1;
	List<MMC_KeyWord> keywords;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		mmcIdentifier = sfData[offset];
		constantData1 = sfData[offset +1];
		int actualLength = getActualLength(sfData, offset, length);
		if(actualLength>2){
			keywords = new ArrayList<MMC_KeyWord>();
			int pos=2;
			MMC_KeyWord kw = new MMC_KeyWord();
			kw.decodeAFP(sfData, offset +pos, actualLength -pos, config);
			keywords.add(kw);
			pos+=2;
		}else{
			keywords = null;
		}
	}
	


	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(mmcIdentifier);
		baos.write(constantData1);
		if(keywords!=null){
			for(MMC_KeyWord kw : keywords) kw.writeAFP(baos, config);
		}
		
		writeFullStructuredField(os, baos.toByteArray());
	}

	public byte getMmcIdentifier() {
		return mmcIdentifier;
	}
	public void setMmcIdentifier(byte mmcIdentifier) {
		this.mmcIdentifier = mmcIdentifier;
	}
	public byte getConstantData1() {
		return constantData1;
	}
	public void setConstantData1(byte constantData1) {
		this.constantData1 = constantData1;
	}
	public List<MMC_KeyWord> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<MMC_KeyWord> keywords) {
		this.keywords = keywords;
	}	
	public void addKeyword(MMC_KeyWord keyword){
		if(keyword==null) return;
		if(keywords==null) keywords = new ArrayList<MMC_KeyWord>();
		keywords.add(keyword);
	}
	public void removeKeyword(MMC_KeyWord keyword){
		if(keywords==null) return;
		else keywords.remove(keyword);
	}
	
	public static class MMC_KeyWord implements IAFPDecodeableWriteable{
		MMC_KeyWordID keywordID;
		short parameter;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			keywordID = MMC_KeyWordID.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
			parameter = UtilBinaryDecoding.parseShort(sfData, offset +1, 1);
		}



		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(keywordID.toByte());
			os.write(parameter);
		}		
		
		public MMC_KeyWordID getKeywordID() {
			return keywordID;
		}

		public void setKeywordID(MMC_KeyWordID keywordID) {
			this.keywordID = keywordID;
		}

		public short getParameter() {
			return parameter;
		}

		public void setParameter(short parameter) {
			this.parameter = parameter;
		}
		
		public static enum MMC_KeyWordID{
			HorizontalPrintAdjustment_RetiredFor3800(0x0E),
			MediumDestinationSelector_high(0x90),
			MediumDestinationSelector_low(0x91),
			FixedMediumInformation(0xA0),
			FixedPerforationCut(0xA1),
			FixedSeparationCut(0xA2),
			PresentationSubsystemSetUpID_high(0xB4),
			PresentationSubsystemSetUpID_low(0xB5),
			OffsetStackEdgeMarkChange(0xD1),
			MediaSourceSelectionFormat(0xE0),
			MediaSourceSelector(0xE1),
			MediaTypeLocalID_high(0xE8),
			MediaTypeLocalID_low(0xE9),
			FormsFlash_RetiredFor3800(0xF1),
			MediumOverlayLocalID(0xF2),
			TextSuppressionLocalID(0xF3),
			DuplexControl(0xF4),
			PrintQualityControl(0xF8),
			ConstantFormsControl(0xF9),
			NUpFormatConrol(0xFC);
			
			int code;
			MMC_KeyWordID(int code){
				this.code = code;
			}
			public static MMC_KeyWordID valueOf(short codeByte){
				for(MMC_KeyWordID kwid : values()) if(kwid.code == codeByte) return kwid;
				return null;
			}
			public int toByte(){ return this.code; }
		}
	}


}
