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
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Base class for {@link StructuredField}s that consists only of opaque data.
 */
@XmlAccessorType(XmlAccessType.NONE)
public class StructuredFieldBaseData extends StructuredField {
  @AFPField(maxSize = 32759)
  protected byte[] data;
  @XmlTransient
  protected java.nio.ByteBuffer payloadBuffer;
  protected String text;

  @Override
  public void reset() {
    super.reset();
    data = null;
    payloadBuffer = null;
    text = null;
  }

  @XmlElement(name = "text")
  public String getText() {
    return UtilCharacterEncoding.sanitizeForXml(text);
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 0) {
      data = new byte[actualLength];
      System.arraycopy(sfData, offset, data, 0, actualLength);
      if (actualLength < 1024 && UtilCharacterEncoding.isHumanReadable(data, config.getAfpCharSet())) {
        text = new String(data, config.getAfpCharSet());
      }
    } else {
      data = null;
      text = null;
    }
  }

  @Override
  public void decodeAFP(java.nio.ByteBuffer buffer, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = length != -1 ? length : buffer.limit() - offset;
    if (actualLength > 0) {
      if (actualLength < 1024 && UtilCharacterEncoding.isHumanReadable(buffer, offset, actualLength, config.getAfpCharSet())) {
        text = UtilCharacterEncoding.decodeEbcdic(buffer, offset, actualLength, config);
      } else {
        text = null;
      }
      payloadBuffer = buffer.slice(offset, actualLength).asReadOnlyBuffer();
      data = null;
    } else {
      payloadBuffer = null;
      data = null;
      text = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    if (payloadBuffer != null) {
      writeFullStructuredField(os, payloadBuffer);
    } else {
      writeFullStructuredField(os, data);
    }
  }

  public byte[] getData() {
    if (data == null && payloadBuffer != null) {
      synchronized (this) {
        if (data == null) {
          data = new byte[payloadBuffer.remaining()];
          int oldPos = payloadBuffer.position();
          payloadBuffer.get(data);
          payloadBuffer.position(oldPos);
        }
      }
    }
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
    this.payloadBuffer = null;
  }

}
