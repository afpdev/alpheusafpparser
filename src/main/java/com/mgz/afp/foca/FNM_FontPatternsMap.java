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
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Font Patterns Map (FNM).
 */
public class FNM_FontPatternsMap extends StructuredField {

  @AFPField
  private List<FNM_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnmRepeatingGroupLength((byte) 8); // Default
    }
    var rgLen = fnc.getFnmRepeatingGroupLength() & 0xFF;

    var actualLength = getActualLength(sfData, offset, length);
    repeatingGroups = new ArrayList<>();

    var pos = 0;
    while (pos + rgLen <= actualLength) {
      var rg = new FNM_RepeatingGroup();
      rg.charDataOffset = UtilBinaryDecoding.parseLong(sfData, offset + pos, 4);
      rg.charDataCount = UtilBinaryDecoding.parseLong(sfData, offset + pos + 4, 4);
      repeatingGroups.add(rg);
      pos += rgLen;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var fnc = config.getCurrentFontControl();
    if (fnc == null) {
      fnc = new FNC_FontControl();
      fnc.setFnmRepeatingGroupLength((byte) 8);
    }
    var rgLen = fnc.getFnmRepeatingGroupLength() & 0xFF;

    var baos = new ByteArrayOutputStream();
    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        var rgBaos = new ByteArrayOutputStream();
        rgBaos.write(UtilBinaryDecoding.longToByteArray(rg.charDataOffset, 4));
        rgBaos.write(UtilBinaryDecoding.longToByteArray(rg.charDataCount, 4));

        var rgData = rgBaos.toByteArray();
        if (rgData.length < rgLen) {
          baos.write(rgData);
          baos.write(new byte[rgLen - rgData.length]);
        } else {
          baos.write(rgData, 0, rgLen);
        }
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<FNM_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<FNM_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  /**
   * FNM Repeating Group.
   */
  public static class FNM_RepeatingGroup {
    @AFPField
    private long charDataOffset;
    @AFPField
    private long charDataCount;

    public long getCharDataOffset() {
      return charDataOffset;
    }

    public void setCharDataOffset(long charDataOffset) {
      this.charDataOffset = charDataOffset;
    }

    public long getCharDataCount() {
      return charDataCount;
    }

    public void setCharDataCount(long charDataCount) {
      this.charDataCount = charDataCount;
    }
  }
}
