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
package com.mgz.afp.modca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MCC_MediumCopyCount extends StructuredField {
  List<MCC_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 0;
    repeatingGroups = new ArrayList<MCC_MediumCopyCount.MCC_RepeatingGroup>();
    while (pos < actualLength) {
      MCC_RepeatingGroup rg = new MCC_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      repeatingGroups.add(rg);
      pos += 6;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (MCC_RepeatingGroup rg : repeatingGroups) {
      rg.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<MCC_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<MCC_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  public void addRepeatingGroup(MCC_RepeatingGroup rg) {
    if (rg == null) {
      return;
    }
    if (repeatingGroups == null) {
      repeatingGroups = new ArrayList<MCC_MediumCopyCount.MCC_RepeatingGroup>();
    }
    repeatingGroups.add(rg);
  }

  public void removeRepeatingGroup(MCC_RepeatingGroup rg) {
    if (repeatingGroups == null) {
      return;
    }
    repeatingGroups.remove(rg);
  }

  public static class MCC_RepeatingGroup implements IAFPDecodeableWriteable {
    short startingCopyNumber;
    short endingCopyNumber;
    byte reserved4 = 0x00;
    byte mediumModificationControlIdentifier;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, 6);
      startingCopyNumber = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      endingCopyNumber = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      reserved4 = sfData[4];
      mediumModificationControlIdentifier = sfData[5];
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(startingCopyNumber, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(endingCopyNumber, 2));
      os.write(reserved4);
      os.write(mediumModificationControlIdentifier);
    }

    public short getStartingCopyNumber() {
      return startingCopyNumber;
    }

    public void setStartingCopyNumber(short startingCopyNumber) {
      this.startingCopyNumber = startingCopyNumber;
    }

    public short getEndingCopyNumber() {
      return endingCopyNumber;
    }

    public void setEndingCopyNumber(short endingCopyNumber) {
      this.endingCopyNumber = endingCopyNumber;
    }

    public byte getReserved4() {
      return reserved4;
    }

    public void setReserved4(byte reserved4) {
      this.reserved4 = reserved4;
    }

    public byte getMediumModificationControlIdentifier() {
      return mediumModificationControlIdentifier;
    }

    public void setMediumModificationControlIdentifier(
        byte mediumModificationControlIdentifier) {
      this.mediumModificationControlIdentifier = mediumModificationControlIdentifier;
    }
  }
}
