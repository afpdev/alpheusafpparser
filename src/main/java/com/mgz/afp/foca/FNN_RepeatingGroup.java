/*
Copyright 2024 Alpheus AFP Parser Authors

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

import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.OutputStream;

/**
 * FNN Repeating Group (Section 2).
 * Contains the mapping between an IBM GCGID and an offset to its technology-specific identifier.
 */
@XmlRootElement
public class FNN_RepeatingGroup implements IRepeatingGroup {

  @AFPField(size = 8)
  private String gcgid;

  @AFPField
  private long tsOffset;

  @Override
  public void reset() {
    gcgid = null;
    tsOffset = 0;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    // FOCA spec says Section 2 RG length is typically 12 bytes.
    // However, FNC.FNNRepeatingGroupLength (byte 35) defines the actual length.
    // The calling decodeAFP in FNN_FontNameMap should pass the correct length.

    gcgid = new String(sfData, offset, 8, config.getAfpCharSet()).trim();
    tsOffset = UtilBinaryDecoding.parseLong(sfData, offset + 8, 4);
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    os.write(UtilCharacterEncoding.stringToByteArray(gcgid, config.getAfpCharSet(), 8, (byte) 0x40));
    os.write(UtilBinaryDecoding.longToByteArray(tsOffset, 4));
  }

  public String getGcgid() {
    return gcgid;
  }

  public void setGcgid(String gcgid) {
    this.gcgid = gcgid;
  }

  public long getTsOffset() {
    return tsOffset;
  }

  public void setTsOffset(long tsOffset) {
    this.tsOffset = tsOffset;
  }
}
