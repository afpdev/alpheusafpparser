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
import com.mgz.util.UtilBinaryDecoding;

/**
 * Color values as used in AFP.
 */
public enum AFPColorValue {
  /** Device default 0x00. */
  DeviceDefault_0x00(0x0000, 0, 0, 0),
  /** Device default 0xFF00. */
  DeviceDefault_0xFF00(0xFF00, 0, 0, 0),
  /** Blue 0x01. */
  Blue_0x01(0x0001, 0, 0, 255),
  /** Blue 0xFF01. */
  Blue_0xFF01(0xFF01, 0, 0, 255),
  /** Red 0x02. */
  Red_0x02(0x0002, 255, 0, 0),
  /** Red 0xFF02. */
  Red_0xFF02(0xFF02, 255, 0, 0),
  /** Magenta 0x03. */
  Magenta_0x03(0x0003, 255, 0, 255),
  /** Magenta 0xFF03. */
  Magenta_0xFF03(0xFF03, 255, 0, 255),
  /** Green 0x04. */
  Green_0x04(0x0004, 0, 255, 0),
  /** Green 0xFF04. */
  Green_0xFF04(0xFF04, 0, 255, 0),
  /** Cyan 0x05. */
  Cyan_0x05(0x0005, 0, 255, 255),
  /** Cyan 0xFF05. */
  Cyan_0xFF05(0xFF05, 0, 255, 255),
  /** Yellow 0x06. */
  Yellow_0x06(0x0006, 255, 255, 0),
  /** Yellow 0xFF06. */
  Yellow_0xFF06(0xFF06, 255, 255, 0),
  /** White Device default 0x07. */
  White_DeviceDefault_0x07(0x0007, 255, 255, 255),
  /** White Device default 0xFF07. */
  White_DeviceDefault_0xFF07(0xFF07, 255, 255, 255),
  /** Black Color of medium 0x08. */
  Black_ColorOfMedium_0x08(0x0008, 0, 0, 0),
  /** Black Color of medium 0xFF08. */
  Black_ColorOfMedium_0xFF08(0xFF08, 0, 0, 0),
  /** Dark blue 0x09. */
  DarkBlue_0x09(0x0009, 0, 0, 170),
  /** Dark blue 0xFF09. */
  DarkBlue_0xFF09(0xFF09, 0, 0, 170),
  /** Orange 0x0A. */
  Orange_0x0A(0x000A, 255, 128, 0),
  /** Orange 0xFF0A. */
  Orange_0xFF0A(0xFF0A, 255, 128, 0),
  /** Purple 0x0B. */
  Purple_0x0B(0x000B, 170, 0, 170),
  /** Purple 0xFF0B. */
  Purple_0xFF0B(0xFF0B, 170, 0, 170),
  /** Dark green 0x0C. */
  DarkGreen_0x0C(0x000C, 0, 146, 0),
  /** Dark green 0xFF0C. */
  DarkGreen_0xFF0C(0xFF0C, 0, 146, 0),
  /** Dark turquoise 0x0D. */
  DarkTurquoise_0x0D(0x000D, 0, 146, 170),
  /** Dark turquoise 0xFF0D. */
  DarkTurquoise_0xFF0D(0xFF0D, 0, 146, 170),
  /** Mustard 0x0E. */
  Mustard_0x0E(0x000E, 196, 160, 32),
  /** Mustard 0xFF0E. */
  Mustard_0xFF0E(0xFF0E, 196, 160, 32),
  /** Gray 0x0F. */
  Gray_0x0F(0x000F, 131, 131, 131),
  /** Gray 0xFF0F. */
  Gray_0xFF0F(0xFF0F, 131, 131, 131),
  /** Brown 0x10. */
  Brown_0x10(0x0010, 144, 48, 0),
  /** Brown 0xFF10. */
  Brown_0xFF10(0xFF10, 144, 48, 0),
  /** Default 0xFF. */
  Default_0xFF(0x00FF, 0, 0, 0),
  /** Default 0xFFFF. */
  Default_0xFFFF(0xFFFF, 0, 0, 0);

  int code;
  int red;
  int green;
  int blue;

  AFPColorValue(int code, int red, int green, int blue) {
    this.code = code;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Returns the AFPColorValue for the given code.
   *
   * @param code the color code
   * @return the corresponding AFPColorValue
   * @throws AFPParserException if the color code is undefined
   */
  public static AFPColorValue valueOf(int code) throws AFPParserException {
    for (AFPColorValue cv : values()) {
      if (cv.code == code) {
        return cv;
      }
    }
    throw new AFPParserException(AFPColorValue.class.getSimpleName() + ": color code 0x" + Integer.toHexString(code) + " is undefined.");
  }

  /**
   * Returns the lower byte of the color code.
   *
   * @return the byte value
   */
  public int toByte() {
    return code & 0xFF;
  }

  /**
   * Returns the RGB values as an array.
   *
   * @return the RGB values
   */
  public int[] toRgb() {
    return new int[] {red, green, blue};
  }

  /**
   * Returns the 2-byte representation of the color code.
   *
   * @return the 2-byte representation
   */
  public byte[] toByte2() {
    return UtilBinaryDecoding.intToByteArray(code, 2);
  }
}