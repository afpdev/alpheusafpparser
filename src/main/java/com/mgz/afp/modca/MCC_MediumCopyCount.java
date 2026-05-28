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
import javax.xml.bind.annotation.XmlRootElement;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
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
      checkDataLength(sfData, offset + pos, actualLength - pos, 6);
      short startingCopyNumber = UtilBinaryDecoding.parseShort(sfData, offset + pos, 2);
      short endingCopyNumber = UtilBinaryDecoding.parseShort(sfData, offset + pos + 2, 2);
      byte reserved4 = sfData[offset + pos + 4];
      byte mediumModificationControlIdentifier = sfData[offset + pos + 5];

      repeatingGroups.add(new MCC_RepeatingGroup(startingCopyNumber, endingCopyNumber, reserved4, mediumModificationControlIdentifier));
      pos += 6;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (MCC_RepeatingGroup rg : repeatingGroups) {
        baos.write(UtilBinaryDecoding.shortToByteArray(rg.startingCopyNumber(), 2));
        baos.write(UtilBinaryDecoding.shortToByteArray(rg.endingCopyNumber(), 2));
        baos.write(rg.reserved4());
        baos.write(rg.mediumModificationControlIdentifier());
      }
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

  @XmlRootElement
  public record MCC_RepeatingGroup(
      @AFPField short startingCopyNumber,
      @AFPField short endingCopyNumber,
      @AFPField byte reserved4,
      @AFPField byte mediumModificationControlIdentifier) {}
}
