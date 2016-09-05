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

public enum AFPUnitBase {
  Inches10(0x00),
  Centimeter10(0x01),
  Logical_ResolutionRatio(0x02);
  int code;

  AFPUnitBase(int code) {
    this.code = code;
  }

  public static AFPUnitBase valueOf(byte unitBaseCode) {
    for (AFPUnitBase ub : values()) {
      if (ub.code == unitBaseCode) {
        return ub;
      }
    }
    return null;
  }

  public byte toByte() {
    if (this == Inches10) {
      return 0x00;
    } else {
      return 0x01;
    }
  }
}