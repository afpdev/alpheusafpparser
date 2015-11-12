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
The structured field class code that has been assigned to
the MO:DCA architecture.
 */
public enum SFClass {
	Undefined(0),
	D3(0xD3);
	
	int val;
	SFClass(int val){
		this.val=val;
	}
	
	public int toByte(){
		return val;
	}
	
	/**
	 * Returns the {@link SFClass} for given byte value sfClass.
	 * @param sfClass
	 * @return
	 */
	public static SFClass valueOf(int sfClass){
		if(sfClass==0xD3) return D3;
		else return Undefined;
	}
}
