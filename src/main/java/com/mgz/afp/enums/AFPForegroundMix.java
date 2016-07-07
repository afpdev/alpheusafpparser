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

public enum AFPForegroundMix {
  Default(0x00),
  Overpaint(0x02);
  int code;

  AFPForegroundMix(int code) {
    this.code = code;
  }

  public static AFPForegroundMix valueOf(byte codeByte) throws AFPParserException {
    if (codeByte == 0x00) return Default;
    else if (codeByte == 0x02) return Overpaint;
    else
      throw new AFPParserException("The foreground mixing code 0x" + Integer.toHexString(codeByte) + " is undefined.");
  }

  public int toByte() {
    return code;
  }
}
