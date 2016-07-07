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
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class CPD_CodePageDescriptor extends StructuredField {
  private static final Charset cpIBM500 = Constants.cpIBM500;
  /**
   * This is the length of the IBM registered GCGID (AFP uses the eight-character identifier
   * format), or a user-assigned GCGID.
   */
  @AFPField
  public short graphicCharacterGIDLength;
  /**
   * The character string assigned to this field is intended to aid the end user, who may need to
   * edit the code page, in identifying the set of characters represented by the code page. The name
   * or title used should correspond to the name assigned by the IBM registration authority when the
   * code page was registered. Though it may be any name or title that has meaning to the creator or
   * editor of the code page. Unless otherwise specified, character string data is encoded as CPGID
   * 500, GCSGID 103 (see “Parameter Types” on page 64).
   */
  @AFPField
  String codePageDescription;
  /**
   * The number of assigned code points in the code page that equals the number of Code Page Index
   * (CPI) repeating groups.
   */
  @AFPField
  long numberOfCodedGraphicCharactersAssigned;
  /**
   * This is the IBM registered GCSGID, or it may be a user defined GCSGID number from the reserved
   * number space X'FF00' to X'FFFE'.
   */
  @AFPField
  int graphicCharacterSetGID;
  /**
   * This is the IBM registered CPGID, or it may be a user defined CPGID number from the reserved
   * number space X'FF00' to X'FFFE'.
   */
  @AFPField
  int codePageGID;
  /**
   * This parameter identifies the code page as either EBCDIC-Presentation encoded, IBM-PC-Data
   * (ASCII) encoded, or UCS-Presentation encoded. It also specifies the code points as either fixed
   * single-byte values or fixed double-byte values.
   */
  @AFPField(isOptional = true, indexNr = 0)
  EncodingScheme encodingScheme;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    checkDataLength(sfData, 0, -1, 42);

    codePageDescription = new String(sfData, offset, 32, cpIBM500);
    graphicCharacterGIDLength = UtilBinaryDecoding.parseShort(sfData, offset + 32, 2);
    numberOfCodedGraphicCharactersAssigned = UtilBinaryDecoding.parseLong(sfData, offset + 34, 4);
    graphicCharacterSetGID = UtilBinaryDecoding.parseInt(sfData, offset + 38, 2);
    ;
    codePageGID = UtilBinaryDecoding.parseInt(sfData, offset + 40, 2);
    if (length > 42 && sfData.length > offset + 42) {
      encodingScheme = EncodingScheme.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 42, 2));
    } else {
      encodingScheme = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    baos.write(UtilCharacterEncoding.stringToByteArray(codePageDescription, cpIBM500, 32, Constants.EBCDIC_ID_FILLER));
    baos.write(UtilBinaryDecoding.shortToByteArray(graphicCharacterGIDLength, 2));
    baos.write(UtilBinaryDecoding.longToByteArray(numberOfCodedGraphicCharactersAssigned, 4));
    baos.write(UtilBinaryDecoding.intToByteArray(graphicCharacterSetGID, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(codePageGID, 2));
    if (encodingScheme != null) {
      baos.write(encodingScheme.toBytes());
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getCodePageDescription() {
    return codePageDescription;
  }

  public void setCodePageDescription(String codePageDescription) {
    this.codePageDescription = codePageDescription;
  }

  public short getGraphicCharacterGIDLength() {
    return graphicCharacterGIDLength;
  }

  public void setGraphicCharacterGIDLength(short graphicCharacterGIDLength) {
    this.graphicCharacterGIDLength = graphicCharacterGIDLength;
  }

  public long getNumberOfCodedGraphicCharactersAssigned() {
    return numberOfCodedGraphicCharactersAssigned;
  }

  public void setNumberOfCodedGraphicCharactersAssigned(
          long numberOfCodedGraphicCharactersAssigned) {
    this.numberOfCodedGraphicCharactersAssigned = numberOfCodedGraphicCharactersAssigned;
  }

  public int getGraphicCharacterSetGID() {
    return graphicCharacterSetGID;
  }

  public void setGraphicCharacterSetGID(int graphicCharacterSetGID) {
    this.graphicCharacterSetGID = graphicCharacterSetGID;
  }

  public int getCodePageGID() {
    return codePageGID;
  }

  public void setCodePageGID(int codePageGID) {
    this.codePageGID = codePageGID;
  }

  public EncodingScheme getEncodingScheme() {
    return encodingScheme;
  }

  public void setEncodingScheme(EncodingScheme encodingScheme) {
    this.encodingScheme = encodingScheme;
  }

  public static enum EncodingScheme {
    NoEncodingSchemeSpecified(0x0000),
    SingleByte_EncodingNotSpecified(0x0100),
    DoubleByte_EncodingNotSpecified(0x0200),
    SingleByte_IBMPCData(0x2100),
    SingleByte_EBCDICPresentation(0x6100),
    DoubleByte_EBCDICPresentation(0x6200),
    DoubleByte_UCSPresentation(0x8200);

    int encodingSchemeCode;

    EncodingScheme(int encodingSchemeCode) {
      this.encodingSchemeCode = encodingSchemeCode;
    }

    public static EncodingScheme valueOf(int encodingSchemeCode) {
      for (EncodingScheme es : values())
        if (es.encodingSchemeCode == encodingSchemeCode) return es;
      return null;
    }

    public byte[] toBytes() {
      return UtilBinaryDecoding.intToByteArray(encodingSchemeCode, 2);
    }

    /**
     * Returns true, if this is a double-byte encoding scheme, false otherwise.
     *
     * @return true, if this is a double-byte encoding scheme, false otherwise.
     */
    public boolean isDoubleByte() {
      return this == DoubleByte_EncodingNotSpecified || this == DoubleByte_EBCDICPresentation || this == DoubleByte_UCSPresentation;
    }
  }
}
