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

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPColorValue;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

public abstract class GAD_DrawingOrder implements IAFPDecodeableWriteable {
	@AFPField
	short drawingOrderType;

	protected static abstract class DrawingOrder_HasPoints extends GAD_DrawingOrder{
		@AFPField(isHidden=true)
		protected boolean isAtCurrentPosition;
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		List<GOCA_Point> points;
		
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			if(lengthOfFollowingData>0){
				points = new ArrayList<GOCA_Point>();
				int pos = 0;
				while(pos<lengthOfFollowingData){
					GOCA_Point lp = new GOCA_Point();
					lp.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 + pos , 2);
					lp.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 + pos + 2, 2);
					points.add(lp);
					pos+=4;
				}
			}else{
				points=null;
			}
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			byte[] lineEndpointsData = null;
			if(points!=null && points.size()>0){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				for(GOCA_Point lp : points){
					if(lp==null) continue;
					UtilBinaryDecoding.shortToByteArray(lp.xCoordinate, 2);
					UtilBinaryDecoding.shortToByteArray(lp.yCoordinate, 2);
				}
				lineEndpointsData=baos.toByteArray();
				lengthOfFollowingData=(short)lineEndpointsData.length;
			}else{
				lengthOfFollowingData=0;
			}
			
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			if(lineEndpointsData!=null) os.write(lineEndpointsData);
		}
		
		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		/**
		 * Returns the list of points.
		 * @return
		 */
		public List<GOCA_Point> getPoints() {
			return points;
		}

		/**
		 * Sets the line endpoints and updates the {@link #lengthOfFollowingData} accordingly.
		 * @param points
		 */
		public void setPoints(List<GOCA_Point> points) {
			this.points = points;
			if(points==null) lengthOfFollowingData=0;
			else lengthOfFollowingData = (short)(4 * points.size());
		}
		
		/**
		 * 
		 * Adds the given {@link GOCA_Point} to the end of the list of line points and updates the {@link #lengthOfFollowingData} accordingly.
		 * If the given {@link GOCA_Point} is null, this method does nothing.
		 * @param point
		 */
		public void addPoint(GOCA_Point point){
			if(point==null) return;
			if(points==null) points = new ArrayList<GOCA_Point>();
			points.add(point);
			lengthOfFollowingData=(short)(4 * points.size());
		}

		/**
		 * Removes the given {@link GOCA_Point} from the list of line points and updates the {@link #lengthOfFollowingData} accordingly.
		 * If the given {@link GOCA_Point} is null, this method does nothing.
		 * @param point
		 */
		public void removePoint(GOCA_Point point){
			if(point==null || points==null) return;
			points.remove(point);
			lengthOfFollowingData=(short)(4 * points.size());
		}
	}	

	public static class GNOP1_NopOperation extends GAD_DrawingOrder{
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
		}
	}
	
	public static class GCOMT_Comment extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField(maxSize=255)
		byte[] comment;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			comment = new byte[lengthOfFollowingData];
			System.arraycopy(sfData, offset + 2, comment, 0, comment.length);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			lengthOfFollowingData = comment != null ? (short)comment.length : 0;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(comment);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public byte[] getComment() {
			return comment;
		}

		public void setComment(byte[] comment) {
			this.comment = comment;
		}
	}

	public static class GDGCH_SegmentCharacteristics extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short identificationCode;
		@AFPField(maxSize=255)
		byte[] parameters;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			identificationCode = UtilBinaryDecoding.parseShort(sfData, offset +2 , 1);
			parameters = new byte[lengthOfFollowingData-1];
			System.arraycopy(sfData, offset + 3, parameters, 0, lengthOfFollowingData-1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			lengthOfFollowingData = parameters != null ? (short)(1 + parameters.length) : 1;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(identificationCode);
			if(parameters!=null) os.write(parameters);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getIdentificationCode() {
			return identificationCode;
		}

		public void setIdentificationCode(short identificationCode) {
			this.identificationCode = identificationCode;
		}

		public byte[] getParameters() {
			return parameters;
		}

		public void setParameters(byte[] parameters) {
			this.parameters = parameters;
		}
	}

	public static class GSPS_SetPatternSet extends GAD_DrawingOrder{
		@AFPField
		short patternLocalID;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			patternLocalID = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(patternLocalID);
		}

		public short getPatternLocalID() {
			return patternLocalID;
		}

		public void setPatternLocalID(short patternLocalID) {
			this.patternLocalID = patternLocalID;
		}
	}

	public static class GSCOL_SetColor extends GAD_DrawingOrder{
		@AFPField
		AFPColorValue color;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			color = AFPColorValue.valueOf(sfData[offset + 1]);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(color!=null ? color.toByte() : AFPColorValue.DeviceDefault_0x00.toByte());
		}

		public AFPColorValue getColor() {
			return color;
		}

		public void setColor(AFPColorValue color) {
			this.color = color;
		}
	}

	public static class GSMX_SetMix extends GAD_DrawingOrder{
		@AFPField
		short mixMode;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			mixMode = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(mixMode);
		}

		public short getMixMode() {
			return mixMode;
		}

		public void setMixMode(short mixMode) {
			this.mixMode = mixMode;
		}
	}

	public static class GSBMX_SetBackgroundMix extends GAD_DrawingOrder{
		@AFPField
		short mixMode;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			mixMode = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(mixMode);
		}

		public short getMixMode() {
			return mixMode;
		}

		public void setMixMode(short mixMode) {
			this.mixMode = mixMode;
		}
	}

	public static class GSFLW_SetFractionLineWidth extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short integralMultiplier;
		@AFPField
		short fractionalMultiplier;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			integralMultiplier = UtilBinaryDecoding.parseShort(sfData, offset +2 , 1);
			fractionalMultiplier = UtilBinaryDecoding.parseShort(sfData, offset +3 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(integralMultiplier);
			os.write(fractionalMultiplier);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getIntegralMultiplier() {
			return integralMultiplier;
		}

		public void setIntegralMultiplier(short integralMultiplier) {
			this.integralMultiplier = integralMultiplier;
		}

		public short getFractionalMultiplier() {
			return fractionalMultiplier;
		}

		public void setFractionalMultiplier(short fractionalMultiplier) {
			this.fractionalMultiplier = fractionalMultiplier;
		}
	}

	public static class GSLT_SetLineType extends GAD_DrawingOrder{
		@AFPField
		short lineType;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lineType = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lineType);
		}

		public short getLineType() {
			return lineType;
		}

		public void setLineType(short lineType) {
			this.lineType = lineType;
		}
		
		public static enum LineType{
			Default,
			Dotted,
			ShortDashed,
			DashDot,
			DoubleDotted,
			LongDashed,
			DashDoubleDot,
			Solid,
			Invisible;
			public static LineType valueOf(byte codeByte) throws AFPParserException{
				for(LineType le : values()) if(le.ordinal()==codeByte) return le;
				throw new AFPParserException("The " + LineType.class.getSimpleName() + " code 0x" + Integer.toHexString(codeByte) + " is undefined.");
			}
			public int toByte(){
				return ordinal();
			}
		} 
	}

	public static class GSLW_SetLineWidth extends GAD_DrawingOrder{
		@AFPField
		short lineWidth;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lineWidth = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lineWidth);
		}

		public short getLineWidth() {
			return lineWidth;
		}

		public void setLineWidth(short lineWidth) {
			this.lineWidth = lineWidth;
		}
	}

	public static class GSLE_SetLineEnd extends GAD_DrawingOrder{
		@AFPField
		LineEnd lineEnd;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lineEnd = LineEnd.valueOf(sfData[ offset +1]);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lineEnd.toByte());
		}
		
		public static enum LineEnd{
			Default,
			Flat,
			Square,
			Round;
			public static LineEnd valueOf(byte codeByte) throws AFPParserException{
				for(LineEnd le : values()) if(le.ordinal()==codeByte) return le;
				throw new AFPParserException("The " + LineEnd.class.getSimpleName() + " code 0x" + Integer.toHexString(codeByte) + " is undefined.");
			}
			public int toByte(){
				return ordinal();
			}
		}
		
		public LineEnd getLineEnd() {
			return lineEnd;
		}

		public void setLineEnd(LineEnd lineEnd) {
			this.lineEnd = lineEnd;
		}
	}

	public static class GSLJ_SetLineJoin extends GAD_DrawingOrder{
		@AFPField
		LineJoin lineJoin;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lineJoin = LineJoin.valueOf(sfData[offset +1]);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lineJoin.toByte());
		}

		public static enum LineJoin{
			Default,
			Bevel,
			Round,
			Miter;
			public static LineJoin valueOf(byte codeByte) throws AFPParserException{
				for(LineJoin le : values()) if(le.ordinal()==codeByte) return le;
				throw new AFPParserException("The " + LineJoin.class.getSimpleName() + " code 0x" + Integer.toHexString(codeByte) + " is undefined.");
			}
			public int toByte(){
				return ordinal();
			}
		}

		public LineJoin getLineJoin() {
			return lineJoin;
		}

		public void setLineJoin(LineJoin lineJoin) {
			this.lineJoin = lineJoin;
		}
	}

	public static class GSCP_SetCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short coordinateX;
		@AFPField
		short coordinateY;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			coordinateX = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			coordinateY = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(coordinateX);
			os.write(coordinateY);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getCoordinateX() {
			return coordinateX;
		}

		public void setCoordinateX(short coordinateX) {
			this.coordinateX = coordinateX;
		}

		public short getCoordinateY() {
			return coordinateY;
		}

		public void setCoordinateY(short coordinateY) {
			this.coordinateY = coordinateY;
		}
	}

	public static class GSAP_SetArcParameters extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short arcTransformP;
		@AFPField
		short arcTransformQ;
		@AFPField
		short arcTransformR;
		@AFPField
		short arcTransformS;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			arcTransformP = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			arcTransformQ = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			arcTransformR = UtilBinaryDecoding.parseShort(sfData, offset +6 , 2);
			arcTransformS = UtilBinaryDecoding.parseShort(sfData, offset +8 , 2);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.shortToByteArray(arcTransformP, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(arcTransformQ, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(arcTransformR, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(arcTransformS, 2));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getArcTransformP() {
			return arcTransformP;
		}

		public void setArcTransformP(short arcTransformP) {
			this.arcTransformP = arcTransformP;
		}

		public short getArcTransformQ() {
			return arcTransformQ;
		}

		public void setArcTransformQ(short arcTransformQ) {
			this.arcTransformQ = arcTransformQ;
		}

		public short getArcTransformR() {
			return arcTransformR;
		}

		public void setArcTransformR(short arcTransformR) {
			this.arcTransformR = arcTransformR;
		}

		public short getArcTransformS() {
			return arcTransformS;
		}

		public void setArcTransformS(short arcTransformS) {
			this.arcTransformS = arcTransformS;
		}
	}

	public static class GSECOL_SetExtendedColor extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		AFPColorValue color;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			color = AFPColorValue.valueOf(UtilBinaryDecoding.parseInt(sfData, offset +2 , 2));
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(color!=null ? color.toByte2() : AFPColorValue.DeviceDefault_0xFF00.toByte2());
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public AFPColorValue getColor() {
			return color;
		}

		public void setColor(AFPColorValue color) {
			this.color = color;
		}
	}

	public static class GSPT_SetPatternSymbol extends GAD_DrawingOrder{
		@AFPField
		short patternSymbolCodePoint;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			patternSymbolCodePoint = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(patternSymbolCodePoint);
		}

		public short getPatternSymbolCodePoint() {
			return patternSymbolCodePoint;
		}

		public void setPatternSymbolCodePoint(short patternSymbolCodePoint) {
			this.patternSymbolCodePoint = patternSymbolCodePoint;
		}
	}

	public static class GSMT_SetMarkerSymbol extends GAD_DrawingOrder{
		@AFPField
		short markerSymbolCodePoint;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			markerSymbolCodePoint = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(markerSymbolCodePoint);
		}

		public short getMarkerSymbolCodePoint() {
			return markerSymbolCodePoint;
		}

		public void setMarkerSymbolCodePoint(short markerSymbolCodePoint) {
			this.markerSymbolCodePoint = markerSymbolCodePoint;
		}
	}

	public static class GSCC_SetCharacterCell extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short widthOfCharacterCellIntegerPart;
		@AFPField
		short heightOfCharacterCellIntegerPart;
		@AFPField(isOptional=true)
		Short widthOfCharacterCellFractionalPart;
		@AFPField(isOptional=true)
		Short heightOfCharacterCellFractionalPart;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			widthOfCharacterCellIntegerPart = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			heightOfCharacterCellIntegerPart = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);

			if(lengthOfFollowingData==8){
				widthOfCharacterCellFractionalPart = UtilBinaryDecoding.parseShort(sfData, offset +6 , 2);
				heightOfCharacterCellFractionalPart = UtilBinaryDecoding.parseShort(sfData, offset +8 , 2);
			}
			
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.shortToByteArray(widthOfCharacterCellIntegerPart, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(heightOfCharacterCellIntegerPart, 2));
			if(lengthOfFollowingData==8){
				os.write(UtilBinaryDecoding.shortToByteArray(widthOfCharacterCellFractionalPart!=null ? widthOfCharacterCellFractionalPart : (short) 0, 2));
				os.write(UtilBinaryDecoding.shortToByteArray(heightOfCharacterCellFractionalPart!=null ? heightOfCharacterCellFractionalPart : (short) 0, 2));
			}
		}

		public short getWidthOfCharacterCellIntegerPart() {
			return widthOfCharacterCellIntegerPart;
		}

		public void setWidthOfCharacterCellIntegerPart(
				short widthOfCharacterCellIntegerPart) {
			this.widthOfCharacterCellIntegerPart = widthOfCharacterCellIntegerPart;
		}

		public short getHeightOfCharacterCellIntegerPart() {
			return heightOfCharacterCellIntegerPart;
		}

		public void setHeightOfCharacterCellIntegerPart(
				short heightOfCharacterCellIntegerPart) {
			this.heightOfCharacterCellIntegerPart = heightOfCharacterCellIntegerPart;
		}

		public Short getWidthOfCharacterCellFractionalPart() {
			return widthOfCharacterCellFractionalPart;
		}

		public void setWidthOfCharacterCellFractionalPart( Short widthOfCharacterCellFractionalPart) {
			this.widthOfCharacterCellFractionalPart = widthOfCharacterCellFractionalPart;
			if(widthOfCharacterCellFractionalPart!=null){
				lengthOfFollowingData=8;
			}else if(heightOfCharacterCellFractionalPart==null){
				lengthOfFollowingData=4;
			}
		}

		public Short getHeightOfCharacterCellFractionalPart() {
			return heightOfCharacterCellFractionalPart;
		}

		public void setHeightOfCharacterCellFractionalPart(Short heightOfCharacterCellFractionalPart) {
			this.heightOfCharacterCellFractionalPart = heightOfCharacterCellFractionalPart;
			if(heightOfCharacterCellFractionalPart!=null){
				lengthOfFollowingData=8;
			}else if(widthOfCharacterCellFractionalPart==null){
				lengthOfFollowingData=4;
			}
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}
		
	}

	public static class GSCA_SetCharacterAngle extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point anglePoint;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			anglePoint = new GOCA_Point();
			anglePoint.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			anglePoint.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(anglePoint.toBytes());
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public GOCA_Point getAnglePoint() {
			return anglePoint;
		}

		/**
		 * The character angle attribute controls the angle of the character baseline with
		 * respect to the GPS Xg axis for subsequent character strings. This angle is specified
		 * using the values XPOS and YPOS, where the character baseline is a line parallel to
		 * the line that passes through the points (Xg=0,Yg=0) and (Xg=XPOS,Yg=YPOS). The
		 * angle of the baseline relative to the Xg-axis of GPS is then the angle whose tangent
		 * is YPOS/XPOS. The values of YPOS and XPOS are not required to be the sine and
		 * cosine of the angle.<br>
		 * <ul>
		 * <li>If YPOS is zero, and XPOS is positive, the character angle is 0°.
		 * <li>If XPOS is zero, and YPOS is positive, the character angle is 90°.
		 * <li>If YPOS is zero, and XPOS is negative, the character angle is 180°.
		 * <li>If XPOS is zero, and YPOS is negative, the character angle is 270°.
		 * </ul>
		 * @param anglePoint that determine the character angle.
		 */
		public void setAnglePoint(GOCA_Point anglePoint) {
			this.anglePoint = anglePoint;
		}
	}

	public static class GSCH_SetCharacterShear extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short dividendOfShearRatio;
		@AFPField
		short divisorOfShearRatio;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			dividendOfShearRatio = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			divisorOfShearRatio = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.shortToByteArray(dividendOfShearRatio, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(divisorOfShearRatio, 2));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getDividendOfShearRatio() {
			return dividendOfShearRatio;
		}

		public void setDividendOfShearRatio(short dividendOfShearRatio) {
			this.dividendOfShearRatio = dividendOfShearRatio;
		}

		public short getDivisorOfShearRatio() {
			return divisorOfShearRatio;
		}

		public void setDivisorOfShearRatio(short divisorOfShearRatio) {
			this.divisorOfShearRatio = divisorOfShearRatio;
		}
	}

	public static class GSMC_SetMarkerCell extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short widthOfMarkerCell;
		@AFPField
		short heightOfMarkerCell;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			widthOfMarkerCell = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			heightOfMarkerCell = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);

		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.shortToByteArray(widthOfMarkerCell, 2));
			os.write(UtilBinaryDecoding.shortToByteArray(heightOfMarkerCell, 2));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getWidthOfMarkerCell() {
			return widthOfMarkerCell;
		}

		public void setWidthOfMarkerCell(short widthOfMarkerCell) {
			this.widthOfMarkerCell = widthOfMarkerCell;
		}

		public short getHeightOfMarkerCell() {
			return heightOfMarkerCell;
		}

		public void setHeightOfMarkerCell(short heightOfMarkerCell) {
			this.heightOfMarkerCell = heightOfMarkerCell;
		}
	}

	public static class GSCS_SetCharacterSet extends GAD_DrawingOrder{
		@AFPField
		short characterSetLocalID;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			characterSetLocalID = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(characterSetLocalID);
		}

		public short getCharacterSetLocalID() {
			return characterSetLocalID;
		}

		public void setCharacterSetLocalID(short characterSetLocalID) {
			this.characterSetLocalID = characterSetLocalID;
		}
	}

	public static class GSCR_SetCharacterPrecision extends GAD_DrawingOrder{
		@AFPField
		short characterPrecision;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			characterPrecision = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(characterPrecision);
		}

		public short getCharacterPrecision() {
			return characterPrecision;
		}

		public void setCharacterPrecision(short characterPrecision) {
			this.characterPrecision = characterPrecision;
		}
	}

	public static class GSCD_SetCharacterDirection extends GAD_DrawingOrder{
		@AFPField
		short characterDirection;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			characterDirection = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(characterDirection);
		}

		public short getCharacterDirection() {
			return characterDirection;
		}

		public void setCharacterDirection(short characterDirection) {
			this.characterDirection = characterDirection;
		}
	}

	public static class GSMP_SetMarkerPrecision extends GAD_DrawingOrder{
		@AFPField
		short markerPrecision;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			markerPrecision = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(markerPrecision);
		}

		public short getMarkerPrecision() {
			return markerPrecision;
		}

		public void setMarkerPrecision(short markerPrecision) {
			this.markerPrecision = markerPrecision;
		}
	}

	public static class GSMS_SetMarkerSet extends GAD_DrawingOrder{
		@AFPField
		short markerSetLocalID;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			markerSetLocalID = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(markerSetLocalID);
		}

		public short getMarkerSetLocalID() {
			return markerSetLocalID;
		}

		public void setMarkerSetLocalID(short markerSetLocalID) {
			this.markerSetLocalID = markerSetLocalID;
		}
	}

	public static class GEPROL_EndProlog extends GAD_DrawingOrder{
		@AFPField
		short reserved0 = 0x00;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			reserved0 = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(reserved0);
		}

		/**
		 * Returns the byte 2, a reserved value that should always be 0x00.
		 * @return reserved value 
		 */
		public short getReserved0() {
			return reserved0;
		}

		/**
		 * Sets the byte 2, a reserved value that should always be 0x00.
		 * @param reserved_0x00 value for the reserved value.
		 */
		public void setReserved0(short reserved_0x00) {
			this.reserved0 = reserved_0x00;
		}
	}

	public static class GEAR_EndArea extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField(maxSize=255)
		byte[] data;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			data = new byte[lengthOfFollowingData];
			System.arraycopy(sfData, offset+2, data, 0, data.length);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			lengthOfFollowingData= data!=null ? (short)data.length : 0;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			if(data!=null) os.write(data);
		}

		public byte[] getData() {
			return data;
		}

		/**
		 * Sets the data value of this {@link GEAR_EndArea}.
		 * The value of {@link #lengthOfFollowingData} is set accordingly.
		 * @param data array of byte that contains the data of this {@link GEAR_EndArea}. Can be null or empty.
		 */
		public void setData(byte[] data) {
			this.data = data;
			if(data==null) lengthOfFollowingData=0;
			else lengthOfFollowingData = (short)data.length;
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}
	}

	public static class GBAR_BeginArea extends GAD_DrawingOrder{
		@AFPField
		short internalFlags;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			internalFlags = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(internalFlags);
		}

		/**
		 * Returns the value of the {@link #internalFlags} byte.
		 * Only the least significant byte of the returned short value is used.
		 * @return
		 */
		public short getInternalFlags() {
			return internalFlags;
		}

		/**
		 * Sets the value of the {@link #internalFlags} byte.
		 * Only the least significant byte of the given short value is used.
		 * @param internalFlags
		 */
		public void setInternalFlags(short internalFlags) {
			this.internalFlags = internalFlags;
		}
	}

	public static class GCBOX_BoxAtCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short reserved2_3 = 0x0000;
		@AFPField
		GOCA_Point diagonalCorner;
		@AFPField(isOptional=true, indexNr=0)
		Short xAxisLengthForRoundCorner;
		@AFPField(isOptional=true, indexNr=1)
		Short yAxisLengthForRoundCorner;
		
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			reserved2_3 = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			diagonalCorner = new GOCA_Point();
			diagonalCorner.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			diagonalCorner.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +6 , 2);
			if(lengthOfFollowingData>=8) xAxisLengthForRoundCorner = UtilBinaryDecoding.parseShort(sfData, offset +8 , 2);
			if(lengthOfFollowingData==10) yAxisLengthForRoundCorner = UtilBinaryDecoding.parseShort(sfData, offset +10 , 2);

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			short actualLengthOfFollowingData = 6;
			if(xAxisLengthForRoundCorner!=null){
				actualLengthOfFollowingData=8;
				if(yAxisLengthForRoundCorner!=null) actualLengthOfFollowingData =10;
			}
			lengthOfFollowingData = actualLengthOfFollowingData;
			
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.intToByteArray(reserved2_3, 2));
			os.write(diagonalCorner.toBytes());
			if(xAxisLengthForRoundCorner!=null){
				os.write(UtilBinaryDecoding.shortToByteArray(xAxisLengthForRoundCorner, 2));
				if(yAxisLengthForRoundCorner!=null) os.write(UtilBinaryDecoding.shortToByteArray(yAxisLengthForRoundCorner, 2));
			}
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getReserved2_3() {
			return reserved2_3;
		}

		public void setReserved2_3(short reserved2_3) {
			this.reserved2_3 = reserved2_3;
		}

		public Short getxAxisLengthForRoundCorner() {
			return xAxisLengthForRoundCorner;
		}

		/**
		 * Sets the value of {@link #xAxisLengthForRoundCorner}.
		 * Sets the value of {@link #lengthOfFollowingData} accordingly.
		 * @param yAxisLengthForRoundCorner value of {@link #xAxisLengthForRoundCorner}, may be null.
		 */
		public void setxAxisLengthForRoundCorner(Short xAxisLengthForRoundCorner) {
			this.xAxisLengthForRoundCorner = xAxisLengthForRoundCorner;
			if(this.xAxisLengthForRoundCorner!=null){
				if(this.yAxisLengthForRoundCorner!=null) lengthOfFollowingData=10;
				else lengthOfFollowingData=8;
			}
		}

		public Short getyAxisLengthForRoundCorner() {
			return yAxisLengthForRoundCorner;
		}

		/**
		 * Sets the value of {@link #yAxisLengthForRoundCorner}.
		 * Sets the value of {@link #lengthOfFollowingData} accordingly.
		 * @param yAxisLengthForRoundCorner value of {@link #yAxisLengthForRoundCorner}, may be null.
		 */
		public void setyAxisLengthForRoundCorner(Short yAxisLengthForRoundCorner) {
			this.yAxisLengthForRoundCorner = yAxisLengthForRoundCorner;
			if(this.yAxisLengthForRoundCorner!=null){
				lengthOfFollowingData=10;
			}else{
				if(xAxisLengthForRoundCorner==null) lengthOfFollowingData=6;
				else lengthOfFollowingData = 8;
			}
		}

		public GOCA_Point getDiagonalCorner() {
			return diagonalCorner;
		}

		public void setDiagonalCorner(GOCA_Point diagonalCorner) {
			this.diagonalCorner = diagonalCorner;
		}
	}

	public static class GCLINE_LineAtCurrentPosition extends DrawingOrder_HasPoints{
		public GCLINE_LineAtCurrentPosition() {
			isAtCurrentPosition=true;
		}
	}

	public static class GCMRK_MarkerAtCurrentPosition extends DrawingOrder_HasPoints{
		public GCMRK_MarkerAtCurrentPosition() {
			isAtCurrentPosition=true;
		}
	}

	public static class GCCHST_CharacterStringAtCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField(maxSize=255)
		byte[] codePoints;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			if(lengthOfFollowingData>0){
				codePoints = new byte[lengthOfFollowingData];
				System.arraycopy(sfData, offset + 2, codePoints, 0, codePoints.length);
			}else{
				codePoints = null;
			}

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			if(codePoints!=null) lengthOfFollowingData=(short)codePoints.length;
			else lengthOfFollowingData=0;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			if(codePoints!=null) os.write(codePoints);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public byte[] getCodePoints() {
			return codePoints;
		}

		public void setCodePoints(byte[] codePoints) {
			this.codePoints = codePoints;
		}
	}

	public static class GCFLT_FilletAtCurrentPosition extends DrawingOrder_HasPoints{
		public GCFLT_FilletAtCurrentPosition() {
			isAtCurrentPosition = true;
		}
	}

	public static class GFARC_FullArcAtGivenPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point arcCenter;
		@AFPField
		short multiplierIntegerPortion;
		@AFPField
		short multiplierFractionalPortion;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			arcCenter = new GOCA_Point();
			arcCenter.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			arcCenter.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			multiplierIntegerPortion = UtilBinaryDecoding.parseShort(sfData, offset +6 , 1);
			multiplierFractionalPortion = UtilBinaryDecoding.parseShort(sfData, offset +7 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(arcCenter.toBytes());
			os.write(UtilBinaryDecoding.shortToByteArray(multiplierIntegerPortion, 1));
			os.write(UtilBinaryDecoding.shortToByteArray(multiplierFractionalPortion, 1));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getMultiplierIntegerPortion() {
			return multiplierIntegerPortion;
		}

		public void setMultiplierIntegerPortion(short multiplierIntegerPortion) {
			this.multiplierIntegerPortion = multiplierIntegerPortion;
		}

		public short getMultiplierFractionalPortion() {
			return multiplierFractionalPortion;
		}

		public void setMultiplierFractionalPortion(short multiplierFractionalPortion) {
			this.multiplierFractionalPortion = multiplierFractionalPortion;
		}

		public GOCA_Point getArcCenter() {
			return arcCenter;
		}

		public void setArcCenter(GOCA_Point arcCenter) {
			this.arcCenter = arcCenter;
		}
	}

	public static class GCBIMG_BeginImageAtCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short formatOfImageData;
		@AFPField
		short reserved3 = 0x00;
		@AFPField
		int widthOfImageInImagePoints;
		@AFPField
		int heightOfImageInImagePoints;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			formatOfImageData = UtilBinaryDecoding.parseShort(sfData, offset +2 , 1);
			reserved3 = UtilBinaryDecoding.parseShort(sfData, offset +3 , 1);
			widthOfImageInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset +4 , 2);
			heightOfImageInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset +4 , 2);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(formatOfImageData);
			os.write(reserved3);
			os.write(UtilBinaryDecoding.intToByteArray(widthOfImageInImagePoints, 2));
			os.write(UtilBinaryDecoding.intToByteArray(heightOfImageInImagePoints, 2));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getFormatOfImageData() {
			return formatOfImageData;
		}

		public void setFormatOfImageData(short formatOfImageData) {
			this.formatOfImageData = formatOfImageData;
		}

		public short getReserved3() {
			return reserved3;
		}

		public void setReserved3(short reserved3) {
			this.reserved3 = reserved3;
		}

		public int getWidthOfImageInImagePoints() {
			return widthOfImageInImagePoints;
		}

		public void setWidthOfImageInImagePoints(int widthOfImageInImagePoints) {
			this.widthOfImageInImagePoints = widthOfImageInImagePoints;
		}

		public int getHeightOfImageInImagePoints() {
			return heightOfImageInImagePoints;
		}

		public void setHeightOfImageInImagePoints(int heightOfImageInImagePoints) {
			this.heightOfImageInImagePoints = heightOfImageInImagePoints;
		}
	}

	public static class GIMD_ImageData extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField(maxSize=255)
		byte[] imageData;
		
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			imageData = new byte[lengthOfFollowingData];
			System.arraycopy(sfData, offset+2, imageData, 0, imageData.length);

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			if(imageData==null) lengthOfFollowingData=0;
			else lengthOfFollowingData = (short)imageData.length;
			
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			if(imageData!=null) os.write(imageData);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public byte[] getImageData() {
			return imageData;
		}

		/**
		 * Sets the image data and updates {@link #lengthOfFollowingData}.
		 * @param imageData image data, can be null or empty.
		 */
		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
			if(imageData==null) lengthOfFollowingData = 0;
			else lengthOfFollowingData = (short)imageData.length;
		}
	}

	public static class GEIMD_EndImage extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData=0x00;
		@AFPField(maxSize=255)
		byte[] reservedData = new byte[0];

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			reservedData = new byte[lengthOfFollowingData];
			System.arraycopy(sfData, offset+2, reservedData, 0, reservedData.length);

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			if(reservedData==null) lengthOfFollowingData=0;
			else lengthOfFollowingData = (short)reservedData.length;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			if(reservedData!=null) os.write(reservedData);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public byte[] getReservedData() {
			return reservedData;
		}

		/**
		 * Sets the {@link #reservedData} and updates {@link #lengthOfFollowingData}.<br>
		 * According to GOCA spec, the reserved data should contain only 0x00 values.
		 * @param reservedData the reserved data, can be null or empty.
		 */
		public void setReservedData(byte[] reservedData) {
			this.reservedData = reservedData;
			if(reservedData==null) lengthOfFollowingData = 0;
			else lengthOfFollowingData = (short) reservedData.length;
		}
	}

	public static class GCRLINE_RelativeLineAtCurrentPosition extends DrawingOrder_HasPoints{
		public GCRLINE_RelativeLineAtCurrentPosition() {
			isAtCurrentPosition = true;
		}
	}

	public static class GCPARC_PartialArcAtCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point arcCenter;
		@AFPField
		short multiplierIntegerPortion;
		@AFPField
		short multiplierFractionalPortion;
		@AFPField
		int startAngle;
		@AFPField
		int sweepAngle;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			arcCenter = new GOCA_Point();
			arcCenter.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			arcCenter.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			multiplierIntegerPortion = UtilBinaryDecoding.parseShort(sfData, offset +6 , 1);
			multiplierFractionalPortion = UtilBinaryDecoding.parseShort(sfData, offset +7 , 1);
			startAngle = UtilBinaryDecoding.parseInt(sfData, offset +8 , 4);
			sweepAngle = UtilBinaryDecoding.parseInt(sfData, offset +12 , 4);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(arcCenter.toBytes());
			os.write(multiplierIntegerPortion);
			os.write(multiplierFractionalPortion);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public GOCA_Point getArcCenter() {
			return arcCenter;
		}

		public void setArcCenter(GOCA_Point arcCenter) {
			this.arcCenter = arcCenter;
		}

		public short getMultiplierIntegerPortion() {
			return multiplierIntegerPortion;
		}

		public void setMultiplierIntegerPortion(short multiplierIntegerPortion) {
			this.multiplierIntegerPortion = multiplierIntegerPortion;
		}

		public short getMultiplierFractionalPortion() {
			return multiplierFractionalPortion;
		}

		public void setMultiplierFractionalPortion(short multiplierFractionalPortion) {
			this.multiplierFractionalPortion = multiplierFractionalPortion;
		}

		/**
		 * see {@link #setStartAngle(int)}.
		 * @return start angle of the partial arc.
		 */
		public int getStartAngle() {
			return startAngle;
		}

		/**
		 * {@link #startAngle} and {@link #sweepAngle} are defined as signed 32-bit integers, whose 
		 * range is restricted to positive values, that is, X'00000000' to X'7FFFFFFF'.
		 * The START and SWEEP angles are the numbers, in degrees, that result from 
		 * dividing the integers by 65536 (216) and interpreting the result as a modulo 
		 * 360 number. The effective range of the angles is therefore greater than or 
		 * equal to 0° and less than 360°.
		 * For example, if the sweep angle is specified to be X'00007FFF', its value is
		 * 32767÷65536 modulo 360 = .5°.
		 * @param startAngle
		 */
		public void setStartAngle(int startAngle) {
			this.startAngle = startAngle;
		}

		/**
		 * see {@link #setSweepAngle(int)}.
		 * @return sweep angle of the partial arc.
		 */
		public int getSweepAngle() {
			return sweepAngle;
		}

		/**
		 * {@link #startAngle} and {@link #sweepAngle} are defined as signed 32-bit integers, whose 
		 * range is restricted to positive values, that is, X'00000000' to X'7FFFFFFF'.
		 * The START and SWEEP angles are the numbers, in degrees, that result from 
		 * dividing the integers by 65536 (216) and interpreting the result as a modulo 
		 * 360 number. The effective range of the angles is therefore greater than or 
		 * equal to 0° and less than 360°.
		 * For example, if the sweep angle is specified to be X'00007FFF', its value is
		 * 32767÷65536 modulo 360 = .5°.
		 */
		public void setSweepAngle(int sweepAngle) {
			this.sweepAngle = sweepAngle;
		}
	}

	public static class GCCBEZ_CubicBezierCurveAtCurrentPosition extends DrawingOrder_HasPoints{
		public GCCBEZ_CubicBezierCurveAtCurrentPosition() {
			isAtCurrentPosition=true;
		}
	}

	public static class GSPCOL_SetProcessColor extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short reserved2 = 0x00;
		@AFPField
		AFPColorSpace colorSpace;
		@AFPField
		int reserved4_7 = 0x00;
		@AFPField
		byte nrOfBitsComponent1;
		@AFPField
		byte nrOfBitsComponent2;
		@AFPField
		byte nrOfBitsComponent3;
		@AFPField
		byte nrOfBitsComponent4;
		@AFPField(maxSize=255-10)
		byte[] colorValue;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			reserved2 = UtilBinaryDecoding.parseShort(sfData, offset +2 , 1);
			colorSpace = AFPColorSpace.valueOf(sfData[offset +3]);
			reserved4_7 = UtilBinaryDecoding.parseInt(sfData, offset +4 , 4);
			nrOfBitsComponent1 = sfData[offset + 8];
			nrOfBitsComponent2 = sfData[offset + 9];
			nrOfBitsComponent3 = sfData[offset + 10];
			nrOfBitsComponent4 = sfData[offset + 11];
			colorValue = new byte[lengthOfFollowingData - 10];
			System.arraycopy(sfData, offset+12, colorValue, 0, colorValue.length);
		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			lengthOfFollowingData = colorValue!=null ? (short)(10 + colorValue.length) : 10;
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(reserved2);
			os.write(colorSpace.toByte());
			os.write(UtilBinaryDecoding.intToByteArray(reserved4_7, 4));
			os.write(nrOfBitsComponent1);
			os.write(nrOfBitsComponent2);
			os.write(nrOfBitsComponent3);
			os.write(nrOfBitsComponent4);
			os.write(colorValue);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getReserved2() {
			return reserved2;
		}

		public void setReserved2(short reserved2) {
			this.reserved2 = reserved2;
		}

		public AFPColorSpace getColorSpace() {
			return colorSpace;
		}

		public void setColorSpace(AFPColorSpace colorSpace) {
			this.colorSpace = colorSpace;
		}

		public int getReserved4_7() {
			return reserved4_7;
		}

		public void setReserved4_7(int reserved4_7) {
			this.reserved4_7 = reserved4_7;
		}

		public byte getNrOfBitsComponent1() {
			return nrOfBitsComponent1;
		}

		public void setNrOfBitsComponent1(byte nrOfBitsComponent1) {
			this.nrOfBitsComponent1 = nrOfBitsComponent1;
		}

		public byte getNrOfBitsComponent2() {
			return nrOfBitsComponent2;
		}

		public void setNrOfBitsComponent2(byte nrOfBitsComponent2) {
			this.nrOfBitsComponent2 = nrOfBitsComponent2;
		}

		public byte getNrOfBitsComponent3() {
			return nrOfBitsComponent3;
		}

		public void setNrOfBitsComponent3(byte nrOfBitsComponent3) {
			this.nrOfBitsComponent3 = nrOfBitsComponent3;
		}

		public byte getNrOfBitsComponent4() {
			return nrOfBitsComponent4;
		}

		public void setNrOfBitsComponent4(byte nrOfBitsComponent4) {
			this.nrOfBitsComponent4 = nrOfBitsComponent4;
		}

		public byte[] getColorValue() {
			return colorValue;
		}

		public void setColorValue(byte[] colorValue) {
			this.colorValue = colorValue;
			lengthOfFollowingData = colorValue!=null ? (short)(colorValue.length) : 10;
		}
	}

	public static class GBOX_BoxAtGivenPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short reserved2_3 = 0x00;
		@AFPField
		GOCA_Point firstCorner;
		@AFPField
		GOCA_Point diagonalCorner;
		@AFPField(isOptional=true,indexNr=0)
		Short xAxisLengthForRoundCorner;
		@AFPField(isOptional=true,indexNr=1)
		Short yAxisLengthForRoundCorner;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			firstCorner = new GOCA_Point();
			firstCorner.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			firstCorner.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +6 , 2);
			diagonalCorner = new GOCA_Point();
			diagonalCorner.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +8 , 2);
			diagonalCorner.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +10 , 2);
			if(lengthOfFollowingData>=12) xAxisLengthForRoundCorner = UtilBinaryDecoding.parseShort(sfData, offset +12 , 2);
			if(lengthOfFollowingData==14) yAxisLengthForRoundCorner = UtilBinaryDecoding.parseShort(sfData, offset +14 , 2);

		}


		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			short actualLengthOfFollowingData = 10;
			if(xAxisLengthForRoundCorner!=null){
				actualLengthOfFollowingData=12;
				if(yAxisLengthForRoundCorner!=null) actualLengthOfFollowingData =14;
			}
			lengthOfFollowingData = actualLengthOfFollowingData;
			
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(UtilBinaryDecoding.shortToByteArray(reserved2_3, 2));
			os.write(firstCorner.toBytes());
			os.write(diagonalCorner.toBytes());
			if(xAxisLengthForRoundCorner!=null){
				os.write(UtilBinaryDecoding.shortToByteArray(xAxisLengthForRoundCorner, 2));
				if(yAxisLengthForRoundCorner!=null) os.write(UtilBinaryDecoding.shortToByteArray(yAxisLengthForRoundCorner, 2));
			}
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getReserved2_3() {
			return reserved2_3;
		}

		public void setReserved2_3(short reserved2_3) {
			this.reserved2_3 = reserved2_3;
		}

		public GOCA_Point getFirstCorner() {
			return firstCorner;
		}

		public void setFirstCorner(GOCA_Point firstCorner) {
			this.firstCorner = firstCorner;
		}

		public GOCA_Point getDiagonalCorner() {
			return diagonalCorner;
		}

		public void setDiagonalCorner(GOCA_Point diagonalCorner) {
			this.diagonalCorner = diagonalCorner;
		}

		public Short getxAxisLengthForRoundCorner() {
			return xAxisLengthForRoundCorner;
		}

		public void setxAxisLengthForRoundCorner(Short xAxisLengthForRoundCorner) {
			this.xAxisLengthForRoundCorner = xAxisLengthForRoundCorner;
		}

		public Short getyAxisLengthForRoundCorner() {
			return yAxisLengthForRoundCorner;
		}

		public void setyAxisLengthForRoundCorner(Short yAxisLengthForRoundCorner) {
			this.yAxisLengthForRoundCorner = yAxisLengthForRoundCorner;
		}
	}

	public static class GLINE_LineAtGivenPosition extends DrawingOrder_HasPoints{
		public GLINE_LineAtGivenPosition() {
			isAtCurrentPosition=false;
		}
	}

	public static class GMRK_MarkerAtGivenPosition extends DrawingOrder_HasPoints{
		public GMRK_MarkerAtGivenPosition() {
			isAtCurrentPosition=false;
		}
	}

	public static class GCHST_CharacterStringAtGivenPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point originPoint;
		@AFPField(isOptional=true,maxSize = 255-4)
		byte[] codePoints;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			originPoint = new GOCA_Point();
			originPoint.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			originPoint.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			if(lengthOfFollowingData>4){
				codePoints = new byte[lengthOfFollowingData-4];
				System.arraycopy(sfData, offset+6, codePoints, 0, codePoints.length);
			}else{
				codePoints=null;
			}
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			short actualLengthOfFollowingData = 4;
			if(codePoints!=null) actualLengthOfFollowingData = (short)(4 + codePoints.length);
			lengthOfFollowingData = actualLengthOfFollowingData;
			
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(originPoint.toBytes());
			if(codePoints!=null){
				os.write(codePoints);
			}
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public GOCA_Point getOriginPoint() {
			return originPoint;
		}

		public void setOriginPoint(GOCA_Point originPoint) {
			this.originPoint = originPoint;
		}

		public byte[] getCodePoints() {
			return codePoints;
		}

		public void setCodePoints(byte[] codePoints) {
			this.codePoints = codePoints;
			lengthOfFollowingData = codePoints !=null ? (short)(4 + codePoints.length) : 4;
		}
	}

	public static class GFLT_FilletAtGivenPosition extends DrawingOrder_HasPoints{
		public GFLT_FilletAtGivenPosition() {
			isAtCurrentPosition=false;
		}
	}

	public static class GCFARC_FullArcAtCurrentPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		short multiplierIntegerPortion;
		@AFPField
		short multiplierFractionalPortion;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);
			multiplierIntegerPortion = UtilBinaryDecoding.parseShort(sfData, offset +2 , 1);
			multiplierFractionalPortion = UtilBinaryDecoding.parseShort(sfData, offset +3 , 1);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(multiplierIntegerPortion);
			os.write(multiplierFractionalPortion);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public short getMultiplierIntegerPortion() {
			return multiplierIntegerPortion;
		}

		public void setMultiplierIntegerPortion(short multiplierIntegerPortion) {
			this.multiplierIntegerPortion = multiplierIntegerPortion;
		}

		public short getMultiplierFractionalPortion() {
			return multiplierFractionalPortion;
		}

		public void setMultiplierFractionalPortion(short multiplierFractionalPortion) {
			this.multiplierFractionalPortion = multiplierFractionalPortion;
		}
	}

	public static class GBIMG_BeginImageAtGivenPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point origin;
		@AFPField
		short formatOfImageData;
		@AFPField
		short reserved3 = 0x00;
		@AFPField
		int widthOfImageInImagePoints;
		@AFPField
		int heightOfImageInImagePoints;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			origin = new GOCA_Point();
			origin.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			origin.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);
			formatOfImageData = UtilBinaryDecoding.parseShort(sfData, offset +6 , 1);
			reserved3 = UtilBinaryDecoding.parseShort(sfData, offset +7 , 1);
			widthOfImageInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset +8 , 2);
			heightOfImageInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset +10 , 2);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(origin.toBytes());
			os.write(formatOfImageData);
			os.write(reserved3);
			os.write(UtilBinaryDecoding.intToByteArray(widthOfImageInImagePoints, 2));
			os.write(UtilBinaryDecoding.intToByteArray(heightOfImageInImagePoints, 2));
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public GOCA_Point getOrigin() {
			return origin;
		}

		public void setOrigin(GOCA_Point origin) {
			this.origin = origin;
		}

		public short getFormatOfImageData() {
			return formatOfImageData;
		}

		public void setFormatOfImageData(short formatOfImageData) {
			this.formatOfImageData = formatOfImageData;
		}

		public short getReserved3() {
			return reserved3;
		}

		public void setReserved3(short reserved3) {
			this.reserved3 = reserved3;
		}

		public int getWidthOfImageInImagePoints() {
			return widthOfImageInImagePoints;
		}

		public void setWidthOfImageInImagePoints(int widthOfImageInImagePoints) {
			this.widthOfImageInImagePoints = widthOfImageInImagePoints;
		}

		public int getHeightOfImageInImagePoints() {
			return heightOfImageInImagePoints;
		}

		public void setHeightOfImageInImagePoints(int heightOfImageInImagePoints) {
			this.heightOfImageInImagePoints = heightOfImageInImagePoints;
		}
	}

	public static class GRLINE_RelativeLineAtGivenPosition extends DrawingOrder_HasPoints{
		public GRLINE_RelativeLineAtGivenPosition() {
			isAtCurrentPosition=false;
		}
	}

	public static class GPARC_PartialArcAtGivenPosition extends GAD_DrawingOrder{
		@AFPField
		short lengthOfFollowingData;
		@AFPField
		GOCA_Point lineStartPoint;
		@AFPField
		GOCA_Point arcCenter;
		@AFPField
		short multiplierIntegerPortion;
		@AFPField
		short multiplierFractionalPortion;
		@AFPField
		int startAngle;
		@AFPField
		int sweepAngle;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			lineStartPoint = new GOCA_Point();
			lineStartPoint.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);
			lineStartPoint.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +4 , 2);

			arcCenter = new GOCA_Point();
			arcCenter.xCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +6 , 2);
			arcCenter.yCoordinate = UtilBinaryDecoding.parseShort(sfData, offset +8 , 2);
			multiplierIntegerPortion = UtilBinaryDecoding.parseShort(sfData, offset +10 , 1);
			multiplierFractionalPortion = UtilBinaryDecoding.parseShort(sfData, offset +11 , 1);
			startAngle = UtilBinaryDecoding.parseInt(sfData, offset +12 , 4);
			sweepAngle = UtilBinaryDecoding.parseInt(sfData, offset +16 , 4);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			os.write(drawingOrderType);
			os.write(lengthOfFollowingData);
			os.write(lineStartPoint.toBytes());
			os.write(arcCenter.toBytes());
			os.write(multiplierIntegerPortion);
			os.write(multiplierFractionalPortion);
		}

		public short getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(short lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public GOCA_Point getLineStartPoint() {
			return lineStartPoint;
		}

		public void setLineStartPoint(GOCA_Point lineStartPoint) {
			this.lineStartPoint = lineStartPoint;
		}

		public GOCA_Point getArcCenter() {
			return arcCenter;
		}

		public void setArcCenter(GOCA_Point arcCenter) {
			this.arcCenter = arcCenter;
		}

		public short getMultiplierIntegerPortion() {
			return multiplierIntegerPortion;
		}

		public void setMultiplierIntegerPortion(short multiplierIntegerPortion) {
			this.multiplierIntegerPortion = multiplierIntegerPortion;
		}

		public short getMultiplierFractionalPortion() {
			return multiplierFractionalPortion;
		}

		public void setMultiplierFractionalPortion(short multiplierFractionalPortion) {
			this.multiplierFractionalPortion = multiplierFractionalPortion;
		}

		public int getStartAngle() {
			return startAngle;
		}

		public void setStartAngle(int startAngle) {
			this.startAngle = startAngle;
		}

		public int getSweepAngle() {
			return sweepAngle;
		}

		public void setSweepAngle(int sweepAngle) {
			this.sweepAngle = sweepAngle;
		}
	}

	public static class GCBEZ_CubicBezierCurveAtGivenPosition extends DrawingOrder_HasPoints{
		public GCBEZ_CubicBezierCurveAtGivenPosition() {
			isAtCurrentPosition=false;
		}
	}

	public static class GEXO_ExtendedOrder extends GAD_DrawingOrder{
		@AFPField
		short qualifier;
		@AFPField
		int lengthOfFollowingData;
		@AFPField(maxSize=65535,isOptional=true)
		byte[] extendedData;

		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			drawingOrderType = UtilBinaryDecoding.parseShort(sfData,offset,1);
			qualifier = UtilBinaryDecoding.parseShort(sfData, offset +1 , 1);

			lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset +2 , 2);

			extendedData = new byte[lengthOfFollowingData];
			System.arraycopy(sfData, offset+4, extendedData, 0, extendedData.length);
		}

		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			lengthOfFollowingData = extendedData!=null ? extendedData.length : 0;
			os.write(drawingOrderType);
			os.write(qualifier);
			os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData,2));
			os.write(extendedData);
		}

		public short getQualifier() {
			return qualifier;
		}

		public void setQualifier(short qualifier) {
			this.qualifier = qualifier;
		}

		public int getLengthOfFollowingData() {
			return lengthOfFollowingData;
		}

		public void setLengthOfFollowingData(int lengthOfFollowingData) {
			this.lengthOfFollowingData = lengthOfFollowingData;
		}

		public byte[] getExtendedData() {
			return extendedData;
		}

		public void setExtendedData(byte[] extendedData) {
			this.extendedData = extendedData;
			lengthOfFollowingData = extendedData!=null ? extendedData.length : 0;
		}
	}

	/**
	 * Specifies a point as used in GOCA.
	 */
	public static class GOCA_Point{
		@AFPField
		short xCoordinate;
		@AFPField
		short yCoordinate;

		public byte[] toBytes(){return new byte[]{(byte)(xCoordinate>>>8),(byte)(xCoordinate & 0xFF),(byte)(yCoordinate>>>8),(byte)(yCoordinate & 0xFF)};}
		public short getxCoordinate() {return xCoordinate;}
		public void setxCoordinate(short xCoordinate) {this.xCoordinate = xCoordinate;}
		public short getyCoordinate() {return yCoordinate;}
		public void setyCoordinate(short yCoordinate) {this.yCoordinate = yCoordinate;}
	}
}
