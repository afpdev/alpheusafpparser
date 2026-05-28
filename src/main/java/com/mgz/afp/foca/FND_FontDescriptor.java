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
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Font Descriptor (FND).
 */
@XmlRootElement
public class FND_FontDescriptor extends StructuredField {

  @AFPField(size = 32)
  private String typefaceDescription;
  @AFPField
  private byte fontWeightClass;
  @AFPField
  private byte fontWidthClass;
  @AFPField
  private short maxVerticalSize;
  @AFPField
  private short nominalVerticalSize;
  @AFPField
  private short minimumVerticalSize;
  @AFPField
  private short maxHorizontalSize;
  @AFPField
  private short nominalHorizontalSize;
  @AFPField
  private short minimumHorizontalSize;
  @AFPField
  private byte designGeneralClass;
  @AFPField
  private byte designSubclass;
  @AFPField
  private byte designSpecificGroup;
  @AFPField(size = 15)
  private byte[] reserved49_63 = new byte[15];
  @AFPField
  private short fontDesignFlags;
  @AFPField(size = 10)
  private byte[] reserved66_75 = new byte[10];
  @AFPField
  private short gcsgid;
  @AFPField
  private short fgid;
  @AFPField
  private List<Triplet> triplets;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    var actualLength = getActualLength(sfData, offset, length);
    checkDataLength(sfData, offset, length, 80);

    typefaceDescription = new String(sfData, offset, 32, config.getAfpCharSet()).trim();
    fontWeightClass = sfData[offset + 32];
    fontWidthClass = sfData[offset + 33];
    maxVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset + 34, 2);
    nominalVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset + 36, 2);
    minimumVerticalSize = UtilBinaryDecoding.parseShort(sfData, offset + 38, 2);
    maxHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset + 40, 2);
    nominalHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset + 42, 2);
    minimumHorizontalSize = UtilBinaryDecoding.parseShort(sfData, offset + 44, 2);
    designGeneralClass = sfData[offset + 46];
    designSubclass = sfData[offset + 47];
    designSpecificGroup = sfData[offset + 48];
    System.arraycopy(sfData, offset + 49, reserved49_63, 0, 15);
    fontDesignFlags = UtilBinaryDecoding.parseShort(sfData, offset + 64, 2);
    System.arraycopy(sfData, offset + 66, reserved66_75, 0, 10);
    gcsgid = UtilBinaryDecoding.parseShort(sfData, offset + 76, 2);
    fgid = UtilBinaryDecoding.parseShort(sfData, offset + 78, 2);

    if (actualLength > 80) {
      triplets = TripletParser.parseTriplets(sfData, offset + 80, actualLength - 80, config);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(typefaceDescription, config.getAfpCharSet(), 32, (byte) 0x40));
    baos.write(fontWeightClass);
    baos.write(fontWidthClass);
    baos.write(UtilBinaryDecoding.shortToByteArray(maxVerticalSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(nominalVerticalSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(minimumVerticalSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(maxHorizontalSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(nominalHorizontalSize, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(minimumHorizontalSize, 2));
    baos.write(designGeneralClass);
    baos.write(designSubclass);
    baos.write(designSpecificGroup);
    baos.write(reserved49_63);
    baos.write(UtilBinaryDecoding.shortToByteArray(fontDesignFlags, 2));
    baos.write(reserved66_75);
    baos.write(UtilBinaryDecoding.shortToByteArray(gcsgid, 2));
    baos.write(UtilBinaryDecoding.shortToByteArray(fgid, 2));

    if (triplets != null) {
      for (var triplet : triplets) {
        triplet.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getText() {
    return UtilCharacterEncoding.sanitizeForXml(typefaceDescription);
  }

  public String getTypefaceDescription() {
    return typefaceDescription;
  }

  public void setTypefaceDescription(String typefaceDescription) {
    this.typefaceDescription = typefaceDescription;
  }

  public byte getFontWeightClass() {
    return fontWeightClass;
  }

  public void setFontWeightClass(byte fontWeightClass) {
    this.fontWeightClass = fontWeightClass;
  }

  public byte getFontWidthClass() {
    return fontWidthClass;
  }

  public void setFontWidthClass(byte fontWidthClass) {
    this.fontWidthClass = fontWidthClass;
  }

  public short getMaxVerticalSize() {
    return maxVerticalSize;
  }

  public void setMaxVerticalSize(short maxVerticalSize) {
    this.maxVerticalSize = maxVerticalSize;
  }

  public short getNominalVerticalSize() {
    return nominalVerticalSize;
  }

  public void setNominalVerticalSize(short nominalVerticalSize) {
    this.nominalVerticalSize = nominalVerticalSize;
  }

  public short getMinimumVerticalSize() {
    return minimumVerticalSize;
  }

  public void setMinimumVerticalSize(short minimumVerticalSize) {
    this.minimumVerticalSize = minimumVerticalSize;
  }

  public short getMaxHorizontalSize() {
    return maxHorizontalSize;
  }

  public void setMaxHorizontalSize(short maxHorizontalSize) {
    this.maxHorizontalSize = maxHorizontalSize;
  }

  public short getNominalHorizontalSize() {
    return nominalHorizontalSize;
  }

  public void setNominalHorizontalSize(short nominalHorizontalSize) {
    this.nominalHorizontalSize = nominalHorizontalSize;
  }

  public short getMinimumHorizontalSize() {
    return minimumHorizontalSize;
  }

  public void setMinimumHorizontalSize(short minimumHorizontalSize) {
    this.minimumHorizontalSize = minimumHorizontalSize;
  }

  public byte getDesignGeneralClass() {
    return designGeneralClass;
  }

  public void setDesignGeneralClass(byte designGeneralClass) {
    this.designGeneralClass = designGeneralClass;
  }

  public byte getDesignSubclass() {
    return designSubclass;
  }

  public void setDesignSubclass(byte designSubclass) {
    this.designSubclass = designSubclass;
  }

  public byte getDesignSpecificGroup() {
    return designSpecificGroup;
  }

  public void setDesignSpecificGroup(byte designSpecificGroup) {
    this.designSpecificGroup = designSpecificGroup;
  }

  public short getFontDesignFlags() {
    return fontDesignFlags;
  }

  public void setFontDesignFlags(short fontDesignFlags) {
    this.fontDesignFlags = fontDesignFlags;
  }

  public short getGcsgid() {
    return gcsgid;
  }

  public void setGcsgid(short gcsgid) {
    this.gcsgid = gcsgid;
  }

  public short getFgid() {
    return fgid;
  }

  public void setFgid(short fgid) {
    this.fgid = fgid;
  }

  public List<Triplet> getTriplets() {
    return triplets;
  }

  public void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }
}
