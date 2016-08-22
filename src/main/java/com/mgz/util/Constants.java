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
package com.mgz.util;

import java.nio.charset.Charset;

public class Constants {
  public static final Charset cpIBM500 = Charset.forName("IBM500");
  public static final byte[] EMPTYBYTES_2 = new byte[]{0x00, 0x00};
  public static final byte[] EMPTYBYTES_3 = new byte[]{0x00, 0x00, 0x00};
  public static final byte[] EMPTYBYTES_4 = new byte[]{0x00, 0x00, 0x00, 0x00};
  public static final byte EBCDIC_BLANK = 0x40;
  public static final byte EBCDIC_ID_FILLER = EBCDIC_BLANK;
  public static int AFPBeginByte_0xA5 = 0x5A;
}
