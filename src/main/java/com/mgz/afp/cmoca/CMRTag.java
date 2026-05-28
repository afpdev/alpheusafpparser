/*
Copyright 2024 Rudolf Fiala

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

import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.util.UtilBinaryDecoding;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CMOCA CMR Data Tag as defined in CMOCA Reference, Chapter 5.
 */
public class CMRTag {
  private final int tagId;
  private final int fieldType;
  private final long count;
  private final long valueOffset;
  private byte[] data;

  public CMRTag(int tagId, int fieldType, long count, long valueOffset) {
    this.tagId = tagId;
    this.fieldType = fieldType;
    this.count = count;
    this.valueOffset = valueOffset;
  }

  public int getTagId() {
    return tagId;
  }

  public int getFieldType() {
    return fieldType;
  }

  public long getCount() {
    return count;
  }

  public long getValueOffset() {
    return valueOffset;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  /**
   * Parses the CMRData into a list of CMRTags.
   *
   * @param cmrData the raw CMR data starting at byte 164 of the CMR resource.
   * @return a list of parsed tags.
   * @throws AFPParserException if parsing fails.
   */
  public static List<CMRTag> parseTags(byte[] cmrData) throws AFPParserException {
    List<CMRTag> tags = new ArrayList<>();
    if (cmrData == null || cmrData.length < 12) {
      return tags;
    }

    int offset = 0;
    while (offset + 12 <= cmrData.length) {
      int tagId = UtilBinaryDecoding.parseInt(cmrData, offset, 2);
      int fieldType = cmrData[offset + 3] & 0xFF;
      long count = UtilBinaryDecoding.parseLong(cmrData, offset + 4, 4);
      long valueOffset = UtilBinaryDecoding.parseLong(cmrData, offset + 8, 4);

      CMRTag tag = new CMRTag(tagId, fieldType, count, valueOffset);
      tags.add(tag);

      offset += 12;
      if (tagId == 0xFFFF) {
        break;
      }
    }

    // Now resolve data for each tag
    int currentTagOffset = 0;
    for (CMRTag tag : tags) {
      if (tag.getTagId() == 0xFFFF) {
        currentTagOffset += 12;
        continue;
      }

      int dataLength = (int) (tag.getCount() * getFieldTypeSize(tag.getFieldType()));
      if (dataLength <= 0) {
        currentTagOffset += 12;
        continue;
      }

      byte[] tagData = new byte[dataLength];
      if (dataLength <= 4) {
        // Data is inline in ValueOffset field, left-aligned
        System.arraycopy(cmrData, currentTagOffset + 8, tagData, 0, dataLength);
      } else {
        // Data is at valueOffset
        if (tag.getValueOffset() + dataLength <= cmrData.length) {
          System.arraycopy(cmrData, (int) tag.getValueOffset(), tagData, 0, dataLength);
        }
      }
      tag.setData(tagData);
      currentTagOffset += 12;
    }

    return tags;
  }

  private static int getFieldTypeSize(int fieldType) {
    switch (fieldType) {
      case 0x01: // 1-byte UBIN
      case 0x05: // BYTE
      case 0x06: // ASCII
      case 0x08: // CODE
      case 0x09: // BITS
        return 1;
      case 0x02: // 2-byte UBIN
      case 0x07: // UTF16
        return 2;
      case 0x04: // 4-byte UBIN
        return 4;
      default:
        return 1;
    }
  }

  @Override
  public String toString() {
    return String.format("Tag[ID=0x%04X, Type=0x%02X, Count=%d, Offset=%d]", tagId, fieldType, count, valueOffset);
  }
}
