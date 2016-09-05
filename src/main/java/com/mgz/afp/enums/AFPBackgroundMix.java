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

import com.mgz.afp.exceptions.AFPParserException;

public enum AFPBackgroundMix {
  Default(0x00),
  LeaveAlone(0x05);
  int code;

  AFPBackgroundMix(int code) {
    this.code = code;
  }

  public static AFPBackgroundMix valueOf(byte codeByte) throws AFPParserException {
    if (codeByte == 0x00) {
      return Default;
    } else if (codeByte == 0x05) {
      return LeaveAlone;
    } else {
      throw new AFPParserException("The background mixing code 0x" + Integer.toHexString(codeByte) + " is undefined.");
    }
  }

  public int toByte() {
    return code;
  }
}
