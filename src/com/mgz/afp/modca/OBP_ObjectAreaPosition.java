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

import com.mgz.afp.base.RepeatingGroupBase;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.enums.AFPReferenceCoordinateSystem;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

/**
 * MO:DCA, page 298.<br>
 * <br>
 * The Object Area Descriptor structured field specifies the size and attributes of an
 * object area presentation space.
 */
public class OBP_ObjectAreaPosition extends StructuredField{
	byte objectAreaPositionID;
	OBP_RepeatingGroup repeatingGroup;
	
	@Override
	public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
		objectAreaPositionID = sfData[offset];
		repeatingGroup = new OBP_RepeatingGroup();
		repeatingGroup.decodeAFP(sfData, offset +1, getActualLength(sfData, offset, length)-1, config);
	}



	@Override
	public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(24);
		baos.write(objectAreaPositionID);
		repeatingGroup.writeAFP(baos, config);
		
		writeFullStructuredField(os, baos.toByteArray());
	}

	public byte getObjectAreaPositionID() {
		return objectAreaPositionID;
	}

	public void setObjectAreaPositionID(byte objectAreaPositionID) {
		this.objectAreaPositionID = objectAreaPositionID;
	}
	
	public OBP_RepeatingGroup getRepeatingGroup() {
		return repeatingGroup;
	}

	public void setRepeatingGroup(OBP_RepeatingGroup repeatingGroup) {
		this.repeatingGroup = repeatingGroup;
	}

	public static class OBP_RepeatingGroup extends RepeatingGroupBase{
		int xOrigin;
		int yOrigin;
		AFPOrientation xRotation;
		AFPOrientation yRotation;
		byte reserved11 = 0x00;
		int xOriginOfContent;
		int yOriginOfContent;
		AFPOrientation xRotationOfContent;
		AFPOrientation yRotationOfContent;
		AFPReferenceCoordinateSystem referenceCoordinateSystem;
		
		@Override
		public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
			super.decodeAFP(sfData, offset, length, config); // Decode RG length.
			xOrigin = UtilBinaryDecoding.parseInt(sfData, offset +1, 3);
			yOrigin = UtilBinaryDecoding.parseInt(sfData, offset +4, 3);
			xRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +7, 2));
			yRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +9, 2));
			reserved11 = sfData[offset +11];
			xOriginOfContent = UtilBinaryDecoding.parseInt(sfData, offset +12, 3);
			yOriginOfContent = UtilBinaryDecoding.parseInt(sfData, offset +15, 3);
			xRotationOfContent = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +18, 2));
			yRotationOfContent = AFPOrientation.valueOf(UtilBinaryDecoding.parseShort(sfData, offset +20, 2));
			referenceCoordinateSystem = AFPReferenceCoordinateSystem.valueOf(sfData[offset +22]);
		}


		
		@Override
		public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
			super.writeAFP(os, config); // Writes RG length.
			os.write(UtilBinaryDecoding.intToByteArray(xOrigin,3));
			os.write(UtilBinaryDecoding.intToByteArray(yOrigin,3));
			os.write(xRotation.toBytes());
			os.write(yRotation.toBytes());
			os.write(reserved11);
			os.write(UtilBinaryDecoding.intToByteArray(xOriginOfContent,3));
			os.write(UtilBinaryDecoding.intToByteArray(yOriginOfContent,3));
			os.write(xRotationOfContent.toBytes());
			os.write(yRotationOfContent.toBytes());
			os.write(referenceCoordinateSystem.toByte());
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

		public AFPOrientation getxRotation() {
			return xRotation;
		}

		public void setxRotation(AFPOrientation xRotation) {
			this.xRotation = xRotation;
		}

		public AFPOrientation getyRotation() {
			return yRotation;
		}

		public void setyRotation(AFPOrientation yRotation) {
			this.yRotation = yRotation;
		}

		public byte getReserved11() {
			return reserved11;
		}

		public void setReserved11(byte reserved11) {
			this.reserved11 = reserved11;
		}

		public int getxOriginOfContent() {
			return xOriginOfContent;
		}

		public void setxOriginOfContent(int xOriginOfContent) {
			this.xOriginOfContent = xOriginOfContent;
		}

		public int getyOriginOfContent() {
			return yOriginOfContent;
		}

		public void setyOriginOfContent(int yOriginOfContent) {
			this.yOriginOfContent = yOriginOfContent;
		}

		public AFPOrientation getxRotationOfContent() {
			return xRotationOfContent;
		}

		public void setxRotationOfContent(AFPOrientation xRotationOfContent) {
			this.xRotationOfContent = xRotationOfContent;
		}

		public AFPOrientation getyRotationOfContent() {
			return yRotationOfContent;
		}

		public void setyRotationOfContent(AFPOrientation yRotationOfContent) {
			this.yRotationOfContent = yRotationOfContent;
		}

		public AFPReferenceCoordinateSystem getReferenceCoordinateSystem() {
			return referenceCoordinateSystem;
		}

		public void setReferenceCoordinateSystem(
				AFPReferenceCoordinateSystem referenceCoordinateSystem) {
			this.referenceCoordinateSystem = referenceCoordinateSystem;
		}
	}
}
