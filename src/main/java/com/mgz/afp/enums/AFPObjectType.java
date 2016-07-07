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

public enum AFPObjectType{
	PageSegment(0x5F),
	OtherObjectData(0x92),
	GraphicsGOCA(0xBB),
	BarCode(0xEB),
	Overlay(0xDF),
	Image(0xFB);
	int code;
	AFPObjectType(int code){ this.code = code;}
	public static AFPObjectType valueOf(short code){
		for(AFPObjectType ot:values()) if(ot.code == code) return ot;
		return null;
	}
	public int toByte(){ return code; }
}