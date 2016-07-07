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

import com.mgz.util.UtilBinaryDecoding;

public enum AFPOrientation {
  ori0(0x0000),
  ori90(0x2D00),
  ori180(0x5A00),
  ori270(0x8700),
  AsDefined(0xFFFF);
  int code;

  AFPOrientation(int orientationCode) {
    this.code = orientationCode;
  }

  public static AFPOrientation valueOf(int orientationCode) {
    for (AFPOrientation ori : values()) if (ori.code == orientationCode) return ori;
    return null;
  }

  public byte[] toBytes() {
    return UtilBinaryDecoding.intToByteArray(code, 2);
  }
}
