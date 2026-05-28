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

package com.mgz.afp.cmoca;

import com.mgz.util.UtilCharacterEncoding;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;

import javax.xml.bind.annotation.XmlElement;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Color Management Resource (CMR).
 * CMOCA Reference, Chapter 3.
 */
public class CMR_ColorManagementResource extends StructuredField {

  @AFPField
  private long length; // 4 bytes
  @AFPField
  private String signature; // 4 bytes, "CMR9"
  @AFPField
  private int reserved1; // 2 bytes
  @AFPField
  private String alias; // 16 bytes
  @AFPField
  private String type; // 4 bytes
  @AFPField
  private String version; // 14 bytes
  @AFPField
  private String manufacturerName; // 10 bytes
  @AFPField
  private String deviceType; // 12 bytes
  @AFPField
  private String deviceModel; // 6 bytes
  @AFPField
  private String mediaBrightness; // 6 bytes
  @AFPField
  private String mediaColor; // 6 bytes
  @AFPField
  private String mediaFinish; // 4 bytes
  @AFPField
  private String mediaWeight; // 6 bytes
  @AFPField
  private String prop1; // 10 bytes
  @AFPField
  private String prop2; // 12 bytes
  @AFPField
  private String prop3; // 8 bytes
  @AFPField
  private String prop4; // 8 bytes
  @AFPField
  private String prop5; // 8 bytes
  @AFPField
  private String reserved2; // 16 bytes
  @AFPField
  private long reserved3; // 8 bytes
  @AFPField
  private byte[] cmrData;

  private List<CMRTag> tags;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    checkDataLength(sfData, offset, actualLength, 164);

    this.length = UtilBinaryDecoding.parseLong(sfData, offset, 4);
    this.signature = new String(sfData, offset + 4, 4, Constants.cpIBM500);
    this.reserved1 = UtilBinaryDecoding.parseInt(sfData, offset + 8, 2);

    // CMR Name starts here (Bytes 10-155)
    this.alias = new String(sfData, offset + 10, 16, Constants.utf16be).trim();
    this.type = new String(sfData, offset + 26, 4, Constants.utf16be).trim();
    this.version = new String(sfData, offset + 30, 14, Constants.utf16be).trim();
    this.manufacturerName = new String(sfData, offset + 44, 10, Constants.utf16be).trim();
    this.deviceType = new String(sfData, offset + 54, 12, Constants.utf16be).trim();
    this.deviceModel = new String(sfData, offset + 66, 6, Constants.utf16be).trim();
    this.mediaBrightness = new String(sfData, offset + 72, 6, Constants.utf16be).trim();
    this.mediaColor = new String(sfData, offset + 78, 6, Constants.utf16be).trim();
    this.mediaFinish = new String(sfData, offset + 84, 4, Constants.utf16be).trim();
    this.mediaWeight = new String(sfData, offset + 88, 6, Constants.utf16be).trim();
    this.prop1 = new String(sfData, offset + 94, 10, Constants.utf16be).trim();
    this.prop2 = new String(sfData, offset + 104, 12, Constants.utf16be).trim();
    this.prop3 = new String(sfData, offset + 116, 8, Constants.utf16be).trim();
    this.prop4 = new String(sfData, offset + 124, 8, Constants.utf16be).trim();
    this.prop5 = new String(sfData, offset + 132, 8, Constants.utf16be).trim();
    this.reserved2 = new String(sfData, offset + 140, 16, Constants.utf16be).trim();

    this.reserved3 = UtilBinaryDecoding.parseLong(sfData, offset + 156, 8);

    if (actualLength > 164) {
      this.cmrData = new byte[actualLength - 164];
      System.arraycopy(sfData, offset + 164, this.cmrData, 0, this.cmrData.length);
      this.tags = CMRTag.parseTags(this.cmrData);
    } else {
      this.cmrData = null;
      this.tags = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    int dataLen = (cmrData != null) ? cmrData.length : 0;
    byte[] payload = new byte[164 + dataLen];

    this.length = payload.length;
    System.arraycopy(UtilBinaryDecoding.longToByteArray(this.length, 4), 0, payload, 0, 4);

    byte[] sigBytes = (signature != null ? signature : "CMR9").getBytes(Constants.cpIBM500);
    System.arraycopy(sigBytes, 0, payload, 4, Math.min(sigBytes.length, 4));

    System.arraycopy(UtilBinaryDecoding.intToByteArray(reserved1, 2), 0, payload, 8, 2);

    writeUTF16Padded(payload, 10, alias, 16);
    writeUTF16Padded(payload, 26, type, 4);
    writeUTF16Padded(payload, 30, version, 14);
    writeUTF16Padded(payload, 44, manufacturerName, 10);
    writeUTF16Padded(payload, 54, deviceType, 12);
    writeUTF16Padded(payload, 66, deviceModel, 6);
    writeUTF16Padded(payload, 72, mediaBrightness, 6);
    writeUTF16Padded(payload, 78, mediaColor, 6);
    writeUTF16Padded(payload, 84, mediaFinish, 4);
    writeUTF16Padded(payload, 88, mediaWeight, 6);
    writeUTF16Padded(payload, 94, prop1, 10);
    writeUTF16Padded(payload, 104, prop2, 12);
    writeUTF16Padded(payload, 116, prop3, 8);
    writeUTF16Padded(payload, 124, prop4, 8);
    writeUTF16Padded(payload, 132, prop5, 8);
    writeUTF16Padded(payload, 140, reserved2, 16);

    System.arraycopy(UtilBinaryDecoding.longToByteArray(reserved3, 8), 0, payload, 156, 8);

    if (cmrData != null) {
      System.arraycopy(cmrData, 0, payload, 164, cmrData.length);
    }

    writeFullStructuredField(os, payload);
  }

  private void writeUTF16Padded(byte[] target, int offset, String value, int lengthInBytes) {
    byte[] bytes = (value != null ? value : "").getBytes(Constants.utf16be);
    int toCopy = Math.min(bytes.length, lengthInBytes);
    System.arraycopy(bytes, 0, target, offset, toCopy);
    // Pad with UTF-16 '@' (X'0040')
    for (int i = toCopy; i < lengthInBytes; i += 2) {
      if (i + 1 < lengthInBytes) {
        target[offset + i] = 0x00;
        target[offset + i + 1] = 0x40;
      } else {
        target[offset + i] = 0x00; // Should not happen with even lengthInBytes
      }
    }
  }

  // Getters and Setters
  public long getLength() { return length; }

  public void setLength(long length) { this.length = length; }

  public String getSignature() { return signature; }

  public void setSignature(String signature) { this.signature = signature; }

  public int getReserved1() { return reserved1; }

  public void setReserved1(int reserved1) { this.reserved1 = reserved1; }

  public String getAlias() { return alias; }

  @XmlElement(name = "text")
  public String getText() {
    return UtilCharacterEncoding.sanitizeForXml(alias);
  }

  public void setAlias(String alias) { this.alias = alias; }

  public String getType() { return type; }

  public void setType(String type) { this.type = type; }

  public String getVersion() { return version; }

  public void setVersion(String version) { this.version = version; }

  public String getManufacturerName() { return manufacturerName; }

  public void setManufacturerName(String manufacturerName) { this.manufacturerName = manufacturerName; }

  public String getDeviceType() { return deviceType; }

  public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

  public String getDeviceModel() { return deviceModel; }

  public void setDeviceModel(String deviceModel) { this.deviceModel = deviceModel; }

  public String getMediaBrightness() { return mediaBrightness; }

  public void setMediaBrightness(String mediaBrightness) { this.mediaBrightness = mediaBrightness; }

  public String getMediaColor() { return mediaColor; }

  public void setMediaColor(String mediaColor) { this.mediaColor = mediaColor; }

  public String getMediaFinish() { return mediaFinish; }

  public void setMediaFinish(String mediaFinish) { this.mediaFinish = mediaFinish; }

  public String getMediaWeight() { return mediaWeight; }

  public void setMediaWeight(String mediaWeight) { this.mediaWeight = mediaWeight; }

  public String getProp1() { return prop1; }

  public void setProp1(String prop1) { this.prop1 = prop1; }

  public String getProp2() { return prop2; }

  public void setProp2(String prop2) { this.prop2 = prop2; }

  public String getProp3() { return prop3; }

  public void setProp3(String prop3) { this.prop3 = prop3; }

  public String getProp4() { return prop4; }

  public void setProp4(String prop4) { this.prop4 = prop4; }

  public String getProp5() { return prop5; }

  public void setProp5(String prop5) { this.prop5 = prop5; }

  public String getReserved2() { return reserved2; }

  public void setReserved2(String reserved2) { this.reserved2 = reserved2; }

  public long getReserved3() { return reserved3; }

  public void setReserved3(long reserved3) { this.reserved3 = reserved3; }

  public byte[] getCmrData() { return cmrData; }

  public void setCmrData(byte[] cmrData) { this.cmrData = cmrData; }
  public List<CMRTag> getTags() { return tags; }
  public void setTags(List<CMRTag> tags) { this.tags = tags; }
}
