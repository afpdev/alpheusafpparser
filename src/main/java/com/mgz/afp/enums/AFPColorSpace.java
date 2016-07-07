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
 * Color spaces in AFP.
 */
public enum AFPColorSpace {
  RGB(0x01),
  YCrCb(0x02),
  CMYK(0x04),
  Highlight(0x06),
  CIELAB(0x08),
  YCbCr(0x12),
  StandardOCA(0x40);
  int code;

  AFPColorSpace(int code) {
    this.code = code;
  }

  public static AFPColorSpace valueOf(byte code) {
    for (AFPColorSpace cs : values()) if (cs.code == code) return cs;
    return null;
  }

  public int toByte() {
    return code;
  }
}