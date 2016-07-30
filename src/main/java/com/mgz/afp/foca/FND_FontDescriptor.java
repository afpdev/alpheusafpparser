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
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.List;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

public class FND_FontDescriptor extends StructuredField {
	private static final Charset cpIBM500 = Constants.cpIBM500;
	@AFPField
	String typefaceDescription;
	@AFPField
	FontWeightClass fontWeightClass;
	@AFPField
	FontWidthClass fontWidthClass;
	
	@AFPField
	short maxVerticalSize;
	@AFPField
	short nominalVerticalSize;
	@AFPField
	short minVerticalSize;
	
	@AFPField
	short maxHorizontalSize;
	@AFPField
	short nominalHorizontalSize;
	@AFPField
	short minHorizontalSize;
	
	@AFPField
	short designGeneralClass;
	@AFPField
	short designSubClass;
	@AFPField
	short designSpecificGroup;
	
	@AFPField(size=15)
	byte[] reserved49_63 = new byte[15];
	
	@AFPField
	EnumSet<FontDesignFlag> fontDesignFlags;
	
	@AFPField(size=10)
	byte[] reserved66_75 = new byte[10];
	
	@AFPField
	int GCSGID_FontGraphicCharacterSetGlobalID;
	@AFPField
	int FGID_FontTypefaceGlobalID;
	
	@AFPField
	List<Triplet> triplets;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		checkDataLength(sfData, offset, length, 80);
		
		typefaceDescription = new String(sfData,offset,32,cpIBM500);

		fontWeightClass = FontWeightClass.valueOf(sfData[offset+32]);
		fontWidthClass = FontWidthClass.valueOf(sfData[offset+33]);
		
		maxVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset+34, 2);
		nominalVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset+36, 2);
		minVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset+38, 2);
		
		maxHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset+40, 2);
		nominalHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset+42, 2);
		minHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset+44, 2);
		
		designGeneralClass = UtilBinaryDecoding.parseShort(sfData, offset+46, 1);
		designSubClass = UtilBinaryDecoding.parseShort(sfData, offset+47, 1);
		designSpecificGroup = UtilBinaryDecoding.parseShort(sfData, offset+48, 1);
		
		
		
		reserved49_63 = new byte[15];
		System.arraycopy(sfData, offset + 49, reserved49_63, 0, reserved49_63.length);
		
		fontDesignFlags = FontDesignFlag.valueOf(sfData[offset + 64]);
		
		reserved66_75 = new byte[10];
		System.arraycopy(sfData, offset + 66, reserved66_75, 0, 10);
		
		GCSGID_FontGraphicCharacterSetGlobalID = UtilBinaryDecoding.parseInt(sfData, offset + 76, 2);
		FGID_FontTypefaceGlobalID = UtilBinaryDecoding.parseInt(sfData, offset + 78, 2);
		
		int actualLength = getActualLength(sfData, offset, length);
		if(actualLength>80){
			triplets = TripletParser.parseTriplets(sfData, offset + 80, -1, config);
		}else{triplets=null;}
		
	}

	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(UtilCharacterEncoding.stringToByteArray(typefaceDescription,cpIBM500,32,Constants.EBCDIC_BLANK));
		baos.write(fontWeightClass.toByte());
		baos.write(fontWidthClass.toByte());
		baos.write(UtilBinaryDecoding.shortToByteArray(maxVerticalSize, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(nominalVerticalSize,2));
		baos.write(UtilBinaryDecoding.shortToByteArray(minVerticalSize,2));
		baos.write(UtilBinaryDecoding.shortToByteArray(maxHorizontalSize, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(nominalHorizontalSize, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(minHorizontalSize, 2));
		baos.write(UtilBinaryDecoding.shortToByteArray(designGeneralClass,1));
		baos.write(UtilBinaryDecoding.shortToByteArray(designSubClass, 1));
		baos.write(UtilBinaryDecoding.shortToByteArray(designSpecificGroup, 1));
		baos.write(reserved49_63!=null ? reserved49_63 : new byte[15]);
		baos.write(FontDesignFlag.toByte(fontDesignFlags));
		baos.write(0x00);
		baos.write(reserved66_75!=null ? reserved66_75 : new byte[10]);
		
		baos.write(UtilBinaryDecoding.intToByteArray(GCSGID_FontGraphicCharacterSetGlobalID, 2));
		baos.write(UtilBinaryDecoding.intToByteArray(FGID_FontTypefaceGlobalID, 2));
		
		if(triplets!=null){
			for(Triplet t : triplets) t.writeAFP(baos, config);
		}
		
		writeFullStructuredField(os, baos.toByteArray());	
	}	
	public static enum FontWeightClass{
		 Ultralight,
		 Extralight,
		 Light,
		 Semilight,
		 Medium_Normal,
		 Semibold,
		 Bold,
		 Extrabold,
		 Ultrabold;
		 public static FontWeightClass valueOf(byte code){
			 for(FontWeightClass fwc : values()) if(fwc.ordinal()==code) return fwc;
			 return null;
		 }
		 public int toByte(){ return ordinal(); }
	}
	public static enum FontWidthClass{
		Ultracondensed,
		Extracondensed,
		Condensed,
		Semicondensed,
		Medium_Normal,
		Semiexpanded,
		Expanded,
		Extraexpanded,
		Ultraexpanded;
		 public static FontWidthClass valueOf(byte code){
			 for(FontWidthClass fwc : values()) if(fwc.ordinal()==code) return fwc;
			 return null;
		 }
		 public int toByte(){ return ordinal(); }
	}
	public static enum FontDesignFlag{
		Italic,
		Underscored,
		Hollow,
		Overstruck;
		 public static EnumSet<FontDesignFlag> valueOf(byte code){
			 EnumSet<FontDesignFlag> result = EnumSet.noneOf(FontDesignFlag.class);
			 if((code & 0x80)!=0) result.add(Italic);
			 if((code & 0x40)!=0) result.add(Underscored);
			 if((code & 0x10)!=0) result.add(Hollow);
			 if((code & 0x08)!=0) result.add(Overstruck);
			 
			 return result;
		 }
		 public static int toByte(EnumSet<FontDesignFlag> fontDesignFlags){ 
			 int result = 0;
			 if(fontDesignFlags.contains(Italic)) result|=0x80;
			 if(fontDesignFlags.contains(Underscored)) result|=0x40;
			 if(fontDesignFlags.contains(Hollow)) result|=0x10;
			 if(fontDesignFlags.contains(Overstruck)) result|=0x08;
			 return result;
		 }
	}
	public String getTypefaceDescription() {
		return typefaceDescription;
	}




	public void setTypefaceDescription(String typefaceDescription) {
		this.typefaceDescription = typefaceDescription;
	}




	public FontWeightClass getFontWeightClass() {
		return fontWeightClass;
	}




	public void setFontWeightClass(FontWeightClass fontWeightClass) {
		this.fontWeightClass = fontWeightClass;
	}




	public FontWidthClass getFontWidthClass() {
		return fontWidthClass;
	}




	public void setFontWidthClass(FontWidthClass fontWidthClass) {
		this.fontWidthClass = fontWidthClass;
	}




	public short getMaxVerticalSize() {
		return maxVerticalSize;
	}




	public void setMaxVerticalSize(short maxVerticalSize) {
		this.maxVerticalSize = maxVerticalSize;
	}




	public short getNominalVerticalSize() {
		return nominalVerticalSize;
	}




	public void setNominalVerticalSize(short nominalVerticalSize) {
		this.nominalVerticalSize = nominalVerticalSize;
	}




	public short getMinVerticalSize() {
		return minVerticalSize;
	}




	public void setMinVerticalSize(short minVerticalSize) {
		this.minVerticalSize = minVerticalSize;
	}




	public short getMaxHorizontalSize() {
		return maxHorizontalSize;
	}




	public void setMaxHorizontalSize(short maxHorizontalSize) {
		this.maxHorizontalSize = maxHorizontalSize;
	}




	public short getNominalHorizontalSize() {
		return nominalHorizontalSize;
	}




	public void setNominalHorizontalSize(short nominalHorizontalSize) {
		this.nominalHorizontalSize = nominalHorizontalSize;
	}




	public short getMinHorizontalSize() {
		return minHorizontalSize;
	}




	public void setMinHorizontalSize(short minHorizontalSize) {
		this.minHorizontalSize = minHorizontalSize;
	}




	public short getDesignGeneralClass() {
		return designGeneralClass;
	}




	public void setDesignGeneralClass(short designGeneralClass) {
		this.designGeneralClass = designGeneralClass;
	}




	public short getDesignSubClass() {
		return designSubClass;
	}




	public void setDesignSubClass(short designSubClass) {
		this.designSubClass = designSubClass;
	}




	public short getDesignSpecificGroup() {
		return designSpecificGroup;
	}




	public void setDesignSpecificGroup(short designSpecificGroup) {
		this.designSpecificGroup = designSpecificGroup;
	}




	public byte[] getReserved49_63() {
		return reserved49_63;
	}




	public void setReserved49_63(byte[] reserved49to63) {
		this.reserved49_63 = reserved49to63;
	}




	public EnumSet<FontDesignFlag> getFontDesignFlags() {
		return fontDesignFlags;
	}




	public void setFontDesignFlags(EnumSet<FontDesignFlag> fontDesignFlags) {
		this.fontDesignFlags = fontDesignFlags;
	}




	public byte[] getReserved66_75() {
		return reserved66_75;
	}




	public void setReserved66_75(byte[] reserved66to75) {
		this.reserved66_75 = reserved66to75;
	}




	public int getGCSGID_FontGraphicCharacterSetGlobalID() {
		return GCSGID_FontGraphicCharacterSetGlobalID;
	}




	public void setGCSGID_FontGraphicCharacterSetGlobalID(
			int gCSGID_FontGraphicCharacterSetGlobalID) {
		GCSGID_FontGraphicCharacterSetGlobalID = gCSGID_FontGraphicCharacterSetGlobalID;
	}




	public int getFGID_FontTypefaceGlobalID() {
		return FGID_FontTypefaceGlobalID;
	}




	public void setFGID_FontTypefaceGlobalID(int fGID_FontTypefaceGlobalID) {
		FGID_FontTypefaceGlobalID = fGID_FontTypefaceGlobalID;
	}




	public List<Triplet> getTriplets() {
		return triplets;
	}




	public void setTriplets(List<Triplet> triplets) {
		this.triplets = triplets;
	}
}
