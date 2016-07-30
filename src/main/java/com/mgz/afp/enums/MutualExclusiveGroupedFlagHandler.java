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

import java.util.EnumSet;

public class MutualExclusiveGroupedFlagHandler<E extends Enum<E>> {
	/** 
	 * Sets the given flag and unsets mutual exclusive flags. 
	 * 
	 * @param flags {@link EnumSet} containing flags that are set.
	 * @param flag flag to set.
	 */
	public void setFlag(EnumSet<E> flags, E flag) {
		@SuppressWarnings("static-access")
		EnumSet<E> allFlags = flags.allOf(flag.getDeclaringClass());
		int flagGroup = ((IMutualExclusiveGroupedFlag)flag).getGroup();
		for(E unsetFlag : allFlags){
			int unsetGroup = ((IMutualExclusiveGroupedFlag)unsetFlag).getGroup();
			if(flagGroup==unsetGroup && unsetFlag!=flag) flags.remove(flag);
		}
		flags.add(flag);
	}

}
