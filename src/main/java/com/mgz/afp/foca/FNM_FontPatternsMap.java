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
package com.mgz.afp.foca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FNM_FontPatternsMap extends StructuredField {
  @AFPField
  List<FNM_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);

    repeatingGroups = new ArrayList<FNM_FontPatternsMap.FNM_RepeatingGroup>();

    int pos = 0;
    while (pos < actualLength) {
      FNM_RepeatingGroup rg = new FNM_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      repeatingGroups.add(rg);
      pos += 8;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    if (repeatingGroups != null) {
      for (FNM_RepeatingGroup rg : repeatingGroups) {
        rg.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class FNM_RepeatingGroup implements IAFPDecodeableWriteable {
    @AFPField
    short characterBoxWidth;
    @AFPField
    short characterBoxHeight;
    @AFPField
    long patternDataOffset;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, 8);
      characterBoxWidth = UtilBinaryDecoding.parseShort(sfData, offset, 2);
      characterBoxHeight = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      patternDataOffset = UtilBinaryDecoding.parseLong(sfData, offset + 4, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(characterBoxWidth, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(characterBoxHeight, 2));
      os.write(UtilBinaryDecoding.longToByteArray(patternDataOffset, 4));
    }

    public short getCharacterBoxWidth() {
      return characterBoxWidth;
    }

    public void setCharacterBoxWidth(short characterBoxWidth) {
      this.characterBoxWidth = characterBoxWidth;
    }

    public short getCharacterBoxHeight() {
      return characterBoxHeight;
    }

    public void setCharacterBoxHeight(short characterBoxHeight) {
      this.characterBoxHeight = characterBoxHeight;
    }

    public long getPatternDataOffset() {
      return patternDataOffset;
    }

    public void setPatternDataOffset(long patternDataOffset) {
      this.patternDataOffset = patternDataOffset;
    }
  }


}
