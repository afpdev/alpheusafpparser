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
package com.mgz.afp.enums;

/**
 * MO:DCA, page 301.<br>
 * <br>
 * Specifies the reference coordinate system.
 */
public enum AFPReferenceCoordinateSystem {
	AsDefined_PageOverlayIPS,
	Standard,
	Retired;
	public static AFPReferenceCoordinateSystem valueOf(byte codeByte){
		if(codeByte == 0x00) return AsDefined_PageOverlayIPS;
		else if(codeByte == 0x01) return Standard;
		else if(codeByte == 0x05) return Retired;
		return null;
	}
	
	public int toByte(){
		if(this==AsDefined_PageOverlayIPS) return 0x00;
		else if (this == Standard) return 0x01;
		else return 0x05;
	}
}
