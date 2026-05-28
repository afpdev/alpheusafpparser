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

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * FNN Technology-specific Identifier (Section 3).
 * Contains the length and name of a technology-specific identifier.
 */
@XmlRootElement
public class FNN_TSIdentifier {

  @AFPField
  private int tsidLen;

  @AFPField
  private String tsid;

  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config, byte tsFormat) throws AFPParserException {
    tsidLen = sfData[offset] & 0xFF;
    // FOCA: tsidLen defines the length of the character identifier plus the length of this field (1 byte).
    int nameLen = tsidLen - 1;
    if (nameLen > 0) {
      // Technology format: 0x03 = ASCII, 0x05 = CID binary
      Charset charset = (tsFormat == 0x03) ? StandardCharsets.US_ASCII : StandardCharsets.ISO_8859_1;
      tsid = new String(sfData, offset + 1, nameLen, charset);
    } else {
      tsid = "";
    }
  }

  public void writeAFP(OutputStream os, AFPParserConfiguration config, byte tsFormat) throws IOException {
    os.write(tsidLen & 0xFF);
    if (tsid != null && !tsid.isEmpty()) {
      Charset charset = (tsFormat == 0x03) ? StandardCharsets.US_ASCII : StandardCharsets.ISO_8859_1;
      os.write(tsid.getBytes(charset));
    }
  }

  public int getTsidLen() {
    return tsidLen;
  }

  public void setTsidLen(int tsidLen) {
    this.tsidLen = tsidLen;
  }

  public String getTsid() {
    return tsid;
  }

  public void setTsid(String tsid) {
    this.tsid = tsid;
  }
}
