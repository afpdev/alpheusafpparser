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
 * Specifies the unit base of an AFP measurement.
 */
public enum AFPUnitBase {
  /**
   * Units are 10 inches.
   */
  Inches10(0x00),
  /**
   * Units are 10 centimeters.
   */
  Centimeter10(0x01),
  /**
   * Units are defined by a logical resolution ratio.
   */
  Logical_ResolutionRatio(0x02);
  int code;

  AFPUnitBase(int code) {
    this.code = code;
  }

  /**
   * Returns the {@link AFPUnitBase} for the given unit base code.
   *
   * @param unitBaseCode the unit base code
   * @return the corresponding {@link AFPUnitBase}, or null if unknown
   */
  public static AFPUnitBase valueOf(byte unitBaseCode) {
    for (AFPUnitBase ub : values()) {
      if (ub.code == unitBaseCode) {
        return ub;
      }
    }
    return null;
  }

  /**
   * Returns the byte code of this unit base.
   *
   * @return the byte code
   */
  public byte toByte() {
    return (byte) code;
  }
}