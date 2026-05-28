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

import com.mgz.afp.base.RepeatingGroupPool;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * FNN - Font Name Map
 * The Font Name Map is used to map IBM character names to the character names in outline fonts.
 */
@XmlRootElement
public class FNN_FontNameMap extends StructuredFieldBaseRepeatingGroups {

  private byte ibmFormat = 0x02; // IBM character ID format: EBCDIC GCGID

  private byte technologyFormat = 0x03; // Technology-specific character ID format

  private List<FNN_TSIdentifier> tsIdentifiers;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var actualLength = getActualLength(sfData, offset, length);
    if (actualLength < 2) {
      return;
    }

    ibmFormat = sfData[offset];
    technologyFormat = sfData[offset + 1];

    var pos = 2;

    // FNC provides repeating group length and GCGID count.
    var rgLen = 12; // Default
    var gcgidCount = -1;
    if (config != null && config.getCurrentFontControl() != null) {
      rgLen = config.getCurrentFontControl().getFnnRepeatingGroupLength() & 0xFF;
      gcgidCount = config.getCurrentFontControl().getFnnIbmNameGcgidCount();
    }

    // Section 2: Repeating Groups
    if (gcgidCount > 0 && rgLen > 0) {
      for (int i = 0; i < gcgidCount && (pos + rgLen) <= actualLength; i++) {
        var rg = RepeatingGroupPool.acquire(FNN_RepeatingGroup.class);
        if (rg == null) {
          rg = new FNN_RepeatingGroup();
        }
        rg.decodeAFP(sfData, offset + pos, rgLen, config);
        addRepeatingGroup(rg);
        pos += rgLen;
      }
    }

    // Section 3: Technology-specific identifiers
    tsIdentifiers = new ArrayList<>();
    while (pos < actualLength) {
      var tsid = new FNN_TSIdentifier();
      tsid.decodeAFP(sfData, offset + pos, actualLength - pos, config, technologyFormat);
      if (tsid.getTsidLen() <= 0) {
          // Robustness: prevent infinite loop if length is 0 or negative
          break;
      }
      tsIdentifiers.add(tsid);
      pos += tsid.getTsidLen();
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var baos = new ByteArrayOutputStream();
    baos.write(ibmFormat);
    baos.write(technologyFormat);

    if (repeatingGroups != null) {
      for (var rg : repeatingGroups) {
        rg.writeAFP(baos, config);
      }
    }

    if (tsIdentifiers != null) {
      for (var tsid : tsIdentifiers) {
        tsid.writeAFP(baos, config, technologyFormat);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public byte getIbmFormat() {
    return ibmFormat;
  }

  public void setIbmFormat(byte ibmFormat) {
    this.ibmFormat = ibmFormat;
  }

  public byte getTechnologyFormat() {
    return technologyFormat;
  }

  public void setTechnologyFormat(byte technologyFormat) {
    this.technologyFormat = technologyFormat;
  }

  @XmlElement(name = "tsIdentifier")
  public List<FNN_TSIdentifier> getTsIdentifiers() {
    return tsIdentifiers;
  }

  public void setTsIdentifiers(List<FNN_TSIdentifier> tsIdentifiers) {
    this.tsIdentifiers = tsIdentifiers;
  }
}
