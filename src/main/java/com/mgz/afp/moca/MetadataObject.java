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

package com.mgz.afp.moca;

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * MOCA Chapter 4. Metadata Object (MO)
 */
@AFPType
@XmlAccessorType(XmlAccessType.NONE)
public class MetadataObject {

  @AFPField
  private long moLength;

  @AFPField
  private int headerLength;

  @AFPField(serializedSize = 6)
  private String moType;

  @AFPField(serializedSize = 8)
  private String moFormat;

  @AFPField(serializedSize = 20)
  private String moCompression;

  @AFPField
  private int moNameLength;

  @AFPField
  private String moName;

  @AFPField
  private byte[] moData;

  public void decode(byte[] data) throws AFPParserException {
    if (data == null || data.length < 50) {
      throw new AFPParserException("Invalid MOCA data: too short");
    }
    moLength = UtilBinaryDecoding.parseLong(data, 0, 4);
    headerLength = UtilBinaryDecoding.parseInt(data, 4, 2);
    moType = new String(data, 6, 6, StandardCharsets.UTF_16BE);
    moFormat = new String(data, 12, 8, StandardCharsets.UTF_16BE);
    moCompression = new String(data, 20, 20, StandardCharsets.UTF_16BE);
    // Offset 40-47 Reserved (8 bytes)
    moNameLength = UtilBinaryDecoding.parseInt(data, 48, 2);

    int currentOffset = 50;
    if (moNameLength > 0) {
      moName = new String(data, currentOffset, moNameLength, StandardCharsets.UTF_16BE);
      currentOffset += moNameLength;
    }

    int dataStartOffset = 4 + headerLength;
    if (data.length > dataStartOffset) {
      int dataLength = data.length - dataStartOffset;
      moData = new byte[dataLength];
      System.arraycopy(data, dataStartOffset, moData, 0, dataLength);
    }
  }

  @XmlElement(name = "MOLength")
  public long getMoLength() {
    return moLength;
  }

  public void setMoLength(long moLength) {
    this.moLength = moLength;
  }

  @XmlElement(name = "HeaderLength")
  public int getHeaderLength() {
    return headerLength;
  }

  public void setHeaderLength(int headerLength) {
    this.headerLength = headerLength;
  }

  @XmlElement(name = "MOType")
  public String getMoType() {
    return trimMoca(moType);
  }

  public void setMoType(String moType) {
    this.moType = moType;
  }

  @XmlElement(name = "MOFormat")
  public String getMoFormat() {
    return trimMoca(moFormat);
  }

  public void setMoFormat(String moFormat) {
    this.moFormat = moFormat;
  }

  @XmlElement(name = "MOCompression")
  public String getMoCompression() {
    return trimMoca(moCompression);
  }

  private String trimMoca(String s) {
    if (s == null) {
      return null;
    }
    int last = s.length();
    while (last > 0 && (s.charAt(last - 1) <= ' ' || s.charAt(last - 1) == '@')) {
      last--;
    }
    return s.substring(0, last);
  }

  public void setMoCompression(String moCompression) {
    this.moCompression = moCompression;
  }

  @XmlElement(name = "MONameLength")
  public int getMoNameLength() {
    return moNameLength;
  }

  public void setMoNameLength(int moNameLength) {
    this.moNameLength = moNameLength;
  }

  @XmlElement(name = "MOName")
  public String getMoName() {
    return UtilCharacterEncoding.sanitizeForXml(moName);
  }

  public void setMoName(String moName) {
    this.moName = moName;
  }

  @XmlElement(name = "MOData")
  public byte[] getMoData() {
    return moData;
  }

  public void setMoData(byte[] moData) {
    this.moData = moData;
  }

  @XmlElement(name = "MODataText")
  public String getMoDataText() {
    if (moData == null) {
      return null;
    }
    // Most MOData is XML or text-based (XMP, AFPT, etc.)
    return UtilCharacterEncoding.sanitizeForXml(new String(moData, StandardCharsets.UTF_16BE));
  }
}
