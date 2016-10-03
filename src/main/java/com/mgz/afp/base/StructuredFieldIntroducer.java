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
package com.mgz.afp.base;

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.SFFlag;
import com.mgz.afp.enums.SFTypeID;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;


/**
 * <b>Structured Field Introducer (SFI)</b><br> The MO:DCA Structured Field Introducer (SFI)
 * identifies type and length of the structured field.
 */
public class StructuredFieldIntroducer {

  /**
   * SFLength[0,1]
   */
  @AFPField(isEditable = false)
  int sfLength;
  @AFPField(isEditable = false)
  SFTypeID sfTypeID;
  /**
   * FlagByte[5]
   */
  @AFPField
  EnumSet<SFFlag> flagByte;
  /**
   * reserved[6,7] (should be zero; usually used as SF counter[6,7].
   */
  @AFPField
  int reserved = 0x0000;
  /**
   * ExtLength[8] Optional. Length of the extension including the length of ExtLength itself
   */
  @AFPField
  short extenstionLength;
  /**
   * ExtData[9-263] Optional. Contains up to 254 bytes of application-defined SFI extension data.
   * For ExtData to be valid, bit 0 of FlagByte must be B'1'.<br> A structured field introducer may
   * be extended by up to 255 bytes. The presence of an SFI extension is indicated by a value of
   * B'1' in bit 0 of the SFI flag byte. If an extension is present, the introducer is at least 8
   * bytes, but not more than 263 bytes, in length. The first byte of the extension specifies its
   * length. If an extension to the structured field introducer is present, the structured field's
   * data can occupy up to 32,759 bytes, less the length of the extension.
   */
  @AFPField(maxSize = 254)
  byte[] extenstion;
  AFPParserConfiguration actualConfig;
  @AFPField(isHidden = true)
  private long fileOffset;

  public static StructuredFieldIntroducer parse(InputStream is) throws AFPParserException {
    StructuredFieldIntroducer sfi = new StructuredFieldIntroducer();

    try {
      sfi.sfLength = UtilBinaryDecoding.parseInt(is, 2);

      sfi.sfTypeID = SFTypeID.parse(is);

      sfi.flagByte = SFFlag.valueOf(is.read());

      sfi.reserved = UtilBinaryDecoding.parseInt(is, 2);
    } catch (IOException ioex) {
      throw new AFPParserException("Failed to decode SFI header.", ioex);
    }

    try {
      if (sfi.isFlagSet(SFFlag.hasExtension)) {
        sfi.extenstionLength = (short) is.read();
        sfi.extenstion = new byte[sfi.extenstionLength - 1];
        if (sfi.extenstionLength - 1 < is.read(sfi.extenstion, 0, sfi.extenstionLength - 1)) {
          throw new AFPParserException("Failed to read SFI extension data.");
        }
      }
    } catch (IOException ioex) {
      throw new AFPParserException("Failed to decode decode SFI extension data.", ioex);
    }

    return sfi;
  }

  public byte[] toBytes() throws IOException {
    ByteArrayOutputStream b;
    if (flagByte == null || !flagByte.contains(SFFlag.hasExtension)) {
      b = new ByteArrayOutputStream(8);
    } else {
      b = new ByteArrayOutputStream(8 + extenstionLength);
    }

    b.write(UtilBinaryDecoding.intToByteArray(sfLength, 2));
    if (sfTypeID != null) {
      b.write(sfTypeID.toBytes());
    } else {
      b.write(new byte[] {0, 0, 0});
    }
    if (flagByte != null) {
      b.write(SFFlag.toByte(flagByte));
    } else {
      b.write(0);
    }
    b.write(UtilBinaryDecoding.intToByteArray(reserved, 2));
    if (flagByte != null && flagByte.contains(SFFlag.hasExtension)) {
      b.write(UtilBinaryDecoding.shortToByteArray(extenstionLength, 1));
      b.write(extenstion);
    }

    return b.toByteArray();
  }

  public int getLengthOfStructuredFieldIntroducerIncludingExtension() {
    if (isFlagSet(SFFlag.hasExtension)) {
      return 8;
    } else {
      return 8 + extenstionLength;
    }
  }

  public int getSFLength() {
    return sfLength;
  }

  public void setSFLength(int length) {
    sfLength = length;
  }

  public short getExtensionLength() {
    return extenstionLength;
  }

  public byte[] getExtensionData() {
    return extenstion;
  }

  /**
   * Sets the SFI extender Data. The corresponding {@link SFFlag#hasExtension} flag and size of
   * extender data of SFI is set accordingly. If the given extender data is null, the {@link
   * SFFlag#hasExtension} flag is revoked and size of extender data of SFI is set to zero (not
   * used).
   */
  public void setExtensionData(byte[] sfiExtensionData) {
    if (sfiExtensionData != null && sfiExtensionData.length > 254) {
      byte[] tmp = new byte[254];
      System.arraycopy(sfiExtensionData, 0, tmp, 0, 254);
      sfiExtensionData = tmp;
    }
    extenstion = sfiExtensionData;
    if (sfiExtensionData != null) {
      if (flagByte == null) {
        flagByte = EnumSet.noneOf(SFFlag.class);
      }
      flagByte.add(SFFlag.hasExtension);
      extenstionLength = (short) (sfiExtensionData.length + 1);
    } else {
      if (flagByte != null) {
        flagByte.remove(SFFlag.hasExtension);
      }
      extenstionLength = (short) 0;
    }
  }

  public int getReserved() {
    return reserved;
  }

  public void setReserved(int reserved) {
    this.reserved = reserved;
  }

  public boolean isFlagSet(SFFlag flag) {
    if (this.flagByte == null) {
      return false;
    } else {
      return flagByte.contains(flag);
    }
  }

  public void setFlag(SFFlag flag) {
    if (this.flagByte == null) {
      flagByte = EnumSet.noneOf(SFFlag.class);
    }
    flagByte.add(flag);
  }

  public void removeFlag(SFFlag flag) {
    if (flagByte == null) {
      return;
    } else {
      flagByte.remove(flag);
    }
  }

  public SFTypeID getSFTypeID() {
    return sfTypeID;
  }

  public void setSFTypeID(SFTypeID sfTypeID) {
    this.sfTypeID = sfTypeID;
  }

  /**
   * Returns the position in the file where the structured field begins. A structured field begins
   * with the 0x5A byte.
   */
  public long getFileOffset() {
    return fileOffset;
  }

  public void setFileOffset(long fileOffset) {
    this.fileOffset = fileOffset;
  }

  public EnumSet<SFFlag> getFlagByte() {
    return flagByte;
  }

  public void setFlagByte(EnumSet<SFFlag> flagByte) {
    this.flagByte = flagByte;
  }


  public AFPParserConfiguration getActualConfig() {
    return actualConfig;
  }

  public void setActualConfig(AFPParserConfiguration actualConfig) {
    this.actualConfig = actualConfig;
  }

  @Override
  public String toString() {
    return "StructuredFieldIntroducer{" +
        "sfTypeID=" + sfTypeID +
        '}';
  }
}
