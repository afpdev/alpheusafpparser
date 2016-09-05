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

public enum SFFlag {
  isSegmented,
  hasExtension,
  isPadded;

  public static EnumSet<SFFlag> valueOf(int flagByte) {
    EnumSet<SFFlag> result = EnumSet.noneOf(SFFlag.class);

    if ((flagByte & 0x80) != 0) {
      result.add(isSegmented);
    }
    if ((flagByte & 0x20) != 0) {
      result.add(hasExtension);
    }
    if ((flagByte & 0x08) != 0) {
      result.add(isPadded);
    }

    return result;
  }

  /**
   * Converts the {@link SFFlag} in given {@link EnumSet} to AFP SF FlagByte.
   */
  public static int toByte(EnumSet<SFFlag> flags) {
    int result = 0;

    if (flags.contains(isSegmented)) {
      result += 0x80;
    }
    if (flags.contains(hasExtension)) {
      result += 0x20;
    }
    if (flags.contains(isPadded)) {
      result += 0x08;
    }

    return result;
  }
}
