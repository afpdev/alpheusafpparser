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
package com.mgz.afp.lineData;

public enum RCD_XMD_RecordTypeElementType {
  Body,
  PageHeader,
  PageTrailer,
  GroupHeader;

  public static RCD_XMD_RecordTypeElementType valuesOf(byte code) {
    for (RCD_XMD_RecordTypeElementType rt : values()) {
      if (rt.ordinal() == code) {
        return rt;
      }
    }
    return null;
  }

  public int toByte() {
    return this.ordinal();
  }
}