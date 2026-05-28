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

package com.mgz.afp.ioca;

import javax.xml.bind.annotation.XmlRootElement;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.ioca.IPD_Segment.AlgorithmSpecificationCompression.CompressionAlgorithmID;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public abstract sealed class IPD_Segment implements IAFPDecodeableWriteable {
  IPD_Segment.IPD_SegmentType segmentType;
  int lengthOfFollowingData;

  /**
   * Resets the segment to its initial state for reuse.
   */
  public void reset() {
    segmentType = null;
    lengthOfFollowingData = 0;
  }

  /**
   * Recursively releases any resources held by this segment back to their pools.
   */
  public void release() {
    IpdSegmentPool.release(this);
  }

  public IPD_Segment.IPD_SegmentType getSegmentType() {
    return segmentType;
  }

  public void setSegmentType(IPD_Segment.IPD_SegmentType segmentType) {
    this.segmentType = segmentType;
  }

  public int getLengthOfFollowingData() {
    return lengthOfFollowingData;
  }

  public void setLengthOfFollowingData(int lengthOfFollowingData) {
    this.lengthOfFollowingData = lengthOfFollowingData;
  }

  public enum IPD_SegmentType {
    BeginSegment(0x70),
    EndSegment(0x71),
    BeginImageContent(0x91),
    EndImageContent(0x93),
    ImageSize(0x94),
    ImageEncoding(0x95),
    IDESize(0x96),
    ImageLUTID(0x97),
    BandImage(0x98),
    IDEStructure(0x9B),
    ExternalAlgorithmSpecification(0x9F),
    ImageSubsampling(0xFECE), // Defined by two byte, see IOCA spec, page 51.
    BeginTile(0x8C),
    EndTile(0x8D),
    TilePosition(0xB5),
    TileSize(0xB6),
    TileSetColor(0xB7),
    IncludeTile(0xFEB8),
    TileTOC(0xFEBB),
    BeginTransparencyMask(0x8E),
    EndTransparencyMask(0x8F),
    SetExtendedBilevelImageColor(0xF4),
    SetBilevelImageColor(0xF6),
    FunctionSetIdentification(0xF7),
    ImageData(0xFE92),
    BandImageData(0xFE9C),
    nColorNames(0xFEB3),
    UnknownIPDSegmentLong(-1),
    UnknownIPDSegmentExtended(-2);

    int type;

    IPD_SegmentType(int type) {
      this.type = type;
    }

    public static IPD_Segment.IPD_SegmentType valueOf(int segmentTypeCode) {
      for (IPD_Segment.IPD_SegmentType t : values()) {
        if (t.type == segmentTypeCode) {
          return t;
        }
      }
      return segmentTypeCode > 0xFF ? UnknownIPDSegmentExtended : UnknownIPDSegmentLong;
    }

    public byte[] toBytes() {
      if (type >= 0xFE00) {
        return UtilBinaryDecoding.intToByteArray(type, 2); // Extended Segment.
      } else {
        return UtilBinaryDecoding.shortToByteArray((short) type, 1); // Long Segment.
      }
    }
  }

  public enum IPD_CompressionAlgorithm {
    IBM_MMR_ModfiedModifiedRead(0x01),
    NoCompression(0x03),
    RL4_RunLength4(0x06),
    ABIC_BilevelQCoder(0x08),
    TIFF_Algorithm2(0x09),
    ConcatenatedABIC(0x0A),
    ColorCompressionUsedByOS2(0x0B),
    TIFF_PackBits(0x0C),
    TIFF_LZW(0x0D),
    SolidFillRectangle(0x20),
    G3_ModifiedHuffman(0x80),
    G3_ModifiedREAD(0x81),
    G4_ModifiedModifiedREAD(0x82),
    JPEG(0x83),
    JBIG2(0x84),
    UserDefinedAlgorithm(0xFE);
    int type;

    IPD_CompressionAlgorithm(int type) {
      this.type = type;
    }

    public static IPD_Segment.IPD_CompressionAlgorithm valueOf(short segmentTypeByte) {
      for (IPD_Segment.IPD_CompressionAlgorithm t : values()) {
        if (t.type == segmentTypeByte) {
          return t;
        }
      }
      return null;
    }

    public int toByte() {
      return type;
    }
  }

  public enum IPD_RecordingAlgorithm {
    Retired(0x00),
    RIDIC_RecordingImageDataInlineCoding(0x01),
    BottomToTop(0x03),
    UnpaddedRIDIC(0x04),
    ExternalAlgorithm(0xFE);
    int type;

    IPD_RecordingAlgorithm(int codeByte) {
      this.type = codeByte;
    }

    public static IPD_Segment.IPD_RecordingAlgorithm valueOf(short segmentTypeByte) {
      for (IPD_Segment.IPD_RecordingAlgorithm t : values()) {
        if (t.type == segmentTypeByte) {
          return t;
        }
      }
      return null;
    }

    public int toByte() {
      return type;
    }
  }

  public enum IPD_BitOrder {
    LeftToRight(0x00),
    RightToLeft(0x01);
    int type;

    IPD_BitOrder(int codeByte) {
      this.type = codeByte;
    }

    public static IPD_Segment.IPD_BitOrder valueOf(short segmentTypeByte) {
      for (IPD_Segment.IPD_BitOrder t : values()) {
        if (t.type == segmentTypeByte) {
          return t;
        }
      }
      return null;
    }

    public int toByte() {
      return type;
    }
  }

  protected static abstract sealed class IPD_SegmentLong extends IPD_Segment {
  }

  protected static abstract sealed class IPD_SegmentExtended extends IPD_Segment {
  }

  public static final class UnknownSegmentLong extends IPD_Segment.IPD_SegmentLong {
    byte[] data;
    String text;

    @Override
    public void reset() {
      super.reset();
      data = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      if (lengthOfFollowingData > 0) {
        data = new byte[lengthOfFollowingData];
        System.arraycopy(sfData, offset + 2, data, 0, data.length);
        if (UtilCharacterEncoding.isHumanReadable(data, config.getAfpCharSet())) {
          text = new String(data, config.getAfpCharSet());
        }
      } else {
        data = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      if (data != null) {
        lengthOfFollowingData = (short) data.length;
      } else {
        lengthOfFollowingData = 0;
      }
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      if (data != null) {
        os.write(data);
      }
    }
  }

  public static final class UnknownSegmentExtended extends IPD_Segment.IPD_SegmentExtended {
    byte[] data;
    String text;

    @Override
    public void reset() {
      super.reset();
      data = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 2));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      if (lengthOfFollowingData > 0) {
        data = new byte[lengthOfFollowingData];
        System.arraycopy(sfData, offset + 4, data, 0, data.length);
        if (UtilCharacterEncoding.isHumanReadable(data, config.getAfpCharSet())) {
          text = new String(data, config.getAfpCharSet());
        }
      } else {
        data = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      if (data != null) {
        lengthOfFollowingData = data.length;
      } else {
        lengthOfFollowingData = 0;
      }
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      if (data != null) {
        os.write(data);
      }
    }
  }

  @XmlType(name = "iocaBeginSegment")
  public static final class BeginSegment extends IPD_Segment.IPD_SegmentLong {
    byte[] name;
    String text;

    @Override
    public void reset() {
      super.reset();
      name = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      if (lengthOfFollowingData > 0) {
        name = new byte[lengthOfFollowingData];
        System.arraycopy(sfData, offset + 2, name, 0, name.length);
        if (UtilCharacterEncoding.isHumanReadable(name, config.getAfpCharSet())) {
          text = new String(name, config.getAfpCharSet());
        }
      } else {
        name = null;
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      if (name != null) {
        lengthOfFollowingData = name.length;
      } else {
        lengthOfFollowingData = 0;
      }
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      if (name != null) {
        os.write(name);
      }
    }

    public byte[] getName() {
      return name;
    }

    public void setName(byte[] name) {
      this.name = name;
    }
  }

  public static final class EndSegment extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
    }
  }

  public static final class BeginImageContent extends IPD_Segment.IPD_SegmentLong {
    short objectType;

    @Override
    public void reset() {
      super.reset();
      objectType = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      objectType = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(objectType);
    }
  }

  public static final class EndImageContent extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
    }
  }

  public static final class ImageSize extends IPD_Segment.IPD_SegmentLong {
    AFPUnitBase unitBase;
    short xUnitsPerUnitBase;
    short yUnitsPerUnitBase;
    short xImageSize;
    short yImageSize;

    @Override
    public void reset() {
      super.reset();
      unitBase = null;
      xUnitsPerUnitBase = 0;
      yUnitsPerUnitBase = 0;
      xImageSize = 0;
      yImageSize = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      unitBase = AFPUnitBase.valueOf(sfData[offset + 2]);
      xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 3, 2);
      yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 5, 2);
      xImageSize = UtilBinaryDecoding.parseShort(sfData, offset + 7, 2);
      yImageSize = UtilBinaryDecoding.parseShort(sfData, offset + 9, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(unitBase.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(xImageSize, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(yImageSize, 2));
    }
  }

  public static final class ImageEncoding extends IPD_Segment.IPD_SegmentLong {
    IPD_Segment.IPD_CompressionAlgorithm compressionAlgorithm;
    IPD_Segment.IPD_RecordingAlgorithm recordingAlgorithm;
    IPD_Segment.IPD_BitOrder bitOrder;

    @Override
    public void reset() {
      super.reset();
      compressionAlgorithm = null;
      recordingAlgorithm = null;
      bitOrder = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      compressionAlgorithm = IPD_CompressionAlgorithm.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      recordingAlgorithm = IPD_RecordingAlgorithm.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 3, 1));
      if (lengthOfFollowingData > 2) {
        bitOrder = IPD_BitOrder.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 4, 1));
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      if (bitOrder != null) {
        lengthOfFollowingData = 3;
      } else {
        lengthOfFollowingData = 2;
      }
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(compressionAlgorithm.toByte());
      os.write(recordingAlgorithm.toByte());
      if (bitOrder != null) {
        os.write(bitOrder.toByte());
      }
    }
  }

  public static final class IDESize extends IPD_Segment.IPD_SegmentLong {
    short numberOfBitsInEachIDE;

    @Override
    public void reset() {
      super.reset();
      numberOfBitsInEachIDE = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      numberOfBitsInEachIDE = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(UtilBinaryDecoding.shortToByteArray(numberOfBitsInEachIDE, 1));
    }
  }

  public static final class ImageLUTID extends IPD_Segment.IPD_SegmentLong {
    short lutId;

    @Override
    public void reset() {
      super.reset();
      lutId = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      lutId = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 1;
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(lutId);
    }

    public short getLutId() {
      return lutId;
    }

    public void setLutId(short lutId) {
      this.lutId = lutId;
    }
  }

  public static final class BandImage extends IPD_Segment.IPD_SegmentLong {
    short numberOfBands;
    List<Short> bandSizes;

    @Override
    public void reset() {
      super.reset();
      numberOfBands = 0;
      bandSizes = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      numberOfBands = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);

      bandSizes = new ArrayList<Short>(numberOfBands);
      for (int i = 0; i < numberOfBands; i++) {
        bandSizes.add(UtilBinaryDecoding.parseShort(sfData, offset + 3 + i, 1));
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      numberOfBands = (short) bandSizes.size();
      lengthOfFollowingData = (short) (2 + bandSizes.size());

      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
      os.write(numberOfBands);
      for (Short s : bandSizes) {
        os.write(s);
      }
    }
  }

  public static final class IDEStructure extends IPD_Segment.IPD_SegmentLong {
    EnumSet<IDEStructure.IDEStructureFlag> flags;
    AFPColorSpace colorSpace;
    byte[] reserved4_6 = new byte[] {0x00, 0x00, 0x00};
    List<Short> componentSizes;

    @Override
    public void reset() {
      super.reset();
      flags = null;
      colorSpace = null;
      reserved4_6 = new byte[] {0x00, 0x00, 0x00};
      componentSizes = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      flags = IDEStructureFlag.valueOf(sfData[offset + 2]);
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 3]);
      reserved4_6 = new byte[3];
      System.arraycopy(sfData, offset + 4, reserved4_6, 0, reserved4_6.length);
      componentSizes = new ArrayList<>();
      int pos = 0;
      while (pos < lengthOfFollowingData - 5) {
        componentSizes.add(UtilBinaryDecoding.parseShort(sfData, offset + 7 + pos, 1));
        pos++;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 5 + (componentSizes != null ? componentSizes.size() : 0);

      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
      os.write(IDEStructureFlag.toByte(flags));
      os.write(colorSpace.toByte());
      os.write(reserved4_6);
      if (componentSizes != null) {
        for (Short s : componentSizes) {
          os.write(s);
        }
      }
    }

    /**
     * Sets the flag and unsets the mutual exclusive flag.
     */
    public void setFlag(IDEStructure.IDEStructureFlag flag) {
      if (flags == null) {
        flags = EnumSet.noneOf(IDEStructure.IDEStructureFlag.class);
      }
      MutualExclusiveGroupedFlagHandler<IDEStructure.IDEStructureFlag> handler = new MutualExclusiveGroupedFlagHandler<IDEStructure.IDEStructureFlag>();
      handler.setFlag(flags, flag);
    }

    public EnumSet<IDEStructure.IDEStructureFlag> getFlags() {
      return flags;
    }

    public void setFlags(EnumSet<IDEStructure.IDEStructureFlag> flags) {
      this.flags = flags;
    }

    public AFPColorSpace getColorSpace() {
      return colorSpace;
    }

    public void setColorSpace(AFPColorSpace colorSpace) {
      this.colorSpace = colorSpace;
    }

    public byte[] getReserved4_6() {
      return reserved4_6;
    }

    public void setReserved4_6(byte[] reserved4_6) {
      this.reserved4_6 = reserved4_6;
    }

    public List<Short> getComponentSizes() {
      return componentSizes;
    }

    public void setComponentSizes(List<Short> componentSizes) {
      this.componentSizes = componentSizes;
    }

    public enum IDEStructureFlag implements IMutualExclusiveGroupedFlag {
      Additive(0),
      Subtractive(0),
      GrayCodingOff(1),
      GrayCodingOn(1);
      int group;

      IDEStructureFlag(int group) {
        this.group = group;
      }

      public static EnumSet<IDEStructure.IDEStructureFlag> valueOf(byte flagByte) {
        EnumSet<IDEStructure.IDEStructureFlag> result = EnumSet.noneOf(IDEStructure.IDEStructureFlag.class);
        if ((flagByte & 0x80) == 0) {
          result.add(Additive);
        } else {
          result.add(Subtractive);
        }
        if ((flagByte & 0x40) == 0) {
          result.add(GrayCodingOff);
        } else {
          result.add(GrayCodingOn);
        }
        return result;
      }

      public static int toByte(EnumSet<IDEStructure.IDEStructureFlag> flags) {
        int result = 0;
        if (flags.contains(Subtractive)) {
          result |= 0x80;
        }
        if (flags.contains(GrayCodingOn)) {
          result |= 0x40;
        }
        return result;
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }

  public static final class ExternalAlgorithmSpecification extends IPD_Segment.IPD_SegmentLong {
    ExternalAlgorithmSpecification.AlgorithmType algorithmType;
    short reserved3 = 0x00;
    IPD_Segment.AlgorithmSpecification algorithmSpecification;

    @Override
    public void reset() {
      super.reset();
      algorithmType = null;
      reserved3 = 0x00;
      algorithmSpecification = null;
    }

    @Override
    public void release() {
      if (algorithmSpecification != null) {
        // AlgorithmSpecification is not pooled in this implementation,
        // but for future-proofing and consistency:
        algorithmSpecification = null;
      }
      super.release();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      algorithmType = AlgorithmType.valueOF(sfData[offset + 2]);
      reserved3 = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      if (lengthOfFollowingData > 3) {
        if (algorithmType == AlgorithmType.Recording) {
          algorithmSpecification = new AlgorithmSpecificationRecording();
          algorithmSpecification.decodeAFP(sfData, offset + 4, -1, config);
        } else {
          algorithmSpecification = AlgorithmSpecificationCompression.buildCompressionAlgorithmSpecification(sfData, offset + 4, -1, config);
        }
      } else {
        algorithmSpecification = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      byte[] algorithmSpecificationData = null;
      if (algorithmSpecification != null) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        algorithmSpecification.writeAFP(baos, config);
        algorithmSpecificationData = baos.toByteArray();
        lengthOfFollowingData = (short) (2 + algorithmSpecificationData.length);
      } else {
        lengthOfFollowingData = 2;
      }

      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(algorithmType.toByte());
      os.write(reserved3);
      if (algorithmSpecificationData != null) {
        os.write(algorithmSpecificationData);
      }
    }

    public enum AlgorithmType {
      Recording(0x00),
      Compressing(0x10);
      int code;

      AlgorithmType(int code) {
        this.code = code;
      }

      public static ExternalAlgorithmSpecification.AlgorithmType valueOF(byte codeByte) {
        if (codeByte == 0x00) {
          return Recording;
        } else {
          return Compressing;
        }
      }

      public int toByte() {
        return code;
      }
    }
  }

  protected abstract static sealed class AlgorithmSpecification implements IAFPDecodeableWriteable {
    /**
     * Resets the algorithm specification to its initial state for reuse.
     */
    public abstract void reset();
  }

  public static final class AlgorithmSpecificationRecording extends IPD_Segment.AlgorithmSpecification {
    short direction;
    short boundaryLengthForPadding;
    short allignmentForPadding;

    @Override
    public void reset() {
      direction = 0;
      boundaryLengthForPadding = 0;
      allignmentForPadding = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      direction = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      boundaryLengthForPadding = UtilBinaryDecoding.parseShort(sfData, offset + 1, 2);
      allignmentForPadding = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(direction);
      os.write(boundaryLengthForPadding);
      os.write(allignmentForPadding);
    }
  }

  public abstract static sealed class AlgorithmSpecificationCompression extends IPD_Segment.AlgorithmSpecification {
    AlgorithmSpecificationCompression.CompressionAlgorithmID compressionAlgorithmID;

    public static IPD_Segment.AlgorithmSpecificationCompression buildCompressionAlgorithmSpecification(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      IPD_Segment.AlgorithmSpecificationCompression result = null;
      AlgorithmSpecificationCompression.CompressionAlgorithmID caid = CompressionAlgorithmID.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      if (caid == CompressionAlgorithmID.JPEG) {
        result = new JPEGCompressionAlgorithmSpecification();
      } else {
        result = new UserDefinedCompressionAlgorithmSpecification();
      }
      result.decodeAFP(sfData, offset, length, config);
      return result;
    }

    public AlgorithmSpecificationCompression.CompressionAlgorithmID getCompressionAlgorithmID() {
      return compressionAlgorithmID;
    }

    public void setCompressionAlgorithmID(AlgorithmSpecificationCompression.CompressionAlgorithmID compressionAlgorithmID) {
      this.compressionAlgorithmID = compressionAlgorithmID;
    }

    public enum CompressionAlgorithmID {
      JPEG(0x83),
      Userdefined(0xFE);
      int code;

      CompressionAlgorithmID(int code) {
        this.code = code;
      }

      public static AlgorithmSpecificationCompression.CompressionAlgorithmID valueOf(short compressionIDByte) {
        if (compressionIDByte == 0x83) {
          return JPEG;
        } else {
          return Userdefined;
        }
      }

      public int toByte() {
        return code;
      }
    }
  }

  public static final class JPEGCompressionAlgorithmSpecification extends IPD_Segment.AlgorithmSpecificationCompression {
    short reserved1 = 0x00;
    short version;
    short reserved3 = 0x00;
    JPEGCompressionAlgorithmSpecificationMarker marker;
    byte[] reserved5_7 = new byte[] {0x00, 0x00, 0x00};

    @Override
    public void reset() {
      compressionAlgorithmID = null;
      reserved1 = 0x00;
      version = 0;
      reserved3 = 0x00;
      marker = null;
      reserved5_7 = new byte[] {0x00, 0x00, 0x00};
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      compressionAlgorithmID = CompressionAlgorithmID.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      reserved1 = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      version = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3 = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      marker = JPEGCompressionAlgorithmSpecificationMarker.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 4, 1));
      reserved5_7 = new byte[3];
      System.arraycopy(sfData, offset + 5, reserved5_7, 0, reserved5_7.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(compressionAlgorithmID.toByte());
      os.write(reserved1);
      os.write(version);
      os.write(reserved3);
      os.write(marker.toByte());
      os.write(reserved5_7);
    }

    public enum JPEGCompressionAlgorithmSpecificationMarker {
      NonDifferentialHuffman_BaselineDCT(0xC0),
      NonDifferentialHuffman_ExtendedSequentialDCT(0xC1),
      NonDifferentialHuffman_ProgressiveDCT(0xC2),
      NonDifferentialHuffman_LosslessSequential(0xC3),

      DifferentialHuffman_Sequential(0xC5),
      DifferentialHuffman_ProgressiveDCT(0xC6),
      DifferentialHuffman_Lossless(0xC7),

      NonDifferentialArithmethic_ExtendedSequentialDCT(0xC9),
      NonDifferentialArithmethic_ProgressiveDCT(0xCA),
      NonDifferentialArithmethic_LosslessSequential(0xCB),

      DifferentialArithmethic_SequentialDCT(0xCD),
      DifferentialArithmethic_ProgressiveDCT(0xCE),
      DifferentialArithmethic_Lossless(0xCF);
      int code;

      JPEGCompressionAlgorithmSpecificationMarker(int code) {
        this.code = code;
      }

      public static JPEGCompressionAlgorithmSpecificationMarker valueOf(short codeByte) {
        for (JPEGCompressionAlgorithmSpecificationMarker marker : values()) {
          if (marker.code == codeByte) {
            return marker;
          }
        }
        return null;
      }

      public int toByte() {
        return code;
      }
    }
  }

  public static final class UserDefinedCompressionAlgorithmSpecification extends IPD_Segment.AlgorithmSpecificationCompression {
    short lengthOfData;
    long compressionAlgorithmCodePoint;
    byte[] userDefinedSpecification;

    @Override
    public void reset() {
      compressionAlgorithmID = null;
      lengthOfData = 0;
      compressionAlgorithmCodePoint = 0;
      userDefinedSpecification = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      compressionAlgorithmID = CompressionAlgorithmID.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      compressionAlgorithmCodePoint = UtilBinaryDecoding.parseLong(sfData, offset + 2, 4);
      if (lengthOfData > 4) {
        userDefinedSpecification = new byte[lengthOfData - 4];
        System.arraycopy(sfData, offset + 6, userDefinedSpecification, 0, userDefinedSpecification.length);
      } else {
        userDefinedSpecification = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (userDefinedSpecification == null) {
        lengthOfData = 4;
      } else {
        lengthOfData = (short) (4 + userDefinedSpecification.length);
      }

      os.write(compressionAlgorithmID.toByte());
      os.write(lengthOfData);
      os.write(UtilBinaryDecoding.longToByteArray(compressionAlgorithmCodePoint, 4));
      if (userDefinedSpecification != null) {
        os.write(userDefinedSpecification);
      }
    }
  }

  public static final class ImageSubsampling extends IPD_Segment.IPD_SegmentExtended {
    List<ImageSubsampling.ImageSubsamplingField> listOfFields;

    @Override
    public void reset() {
      super.reset();
      listOfFields = null;
    }

    @Override
    public void release() {
      if (listOfFields != null) {
        // ImageSubsamplingField is not pooled in this implementation
        listOfFields = null;
      }
      super.release();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2)); // Actually has length of two, however first byte is significant.
      lengthOfFollowingData = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2); // Starts at [2] and has length 2.
      if (lengthOfFollowingData > 0) {
        listOfFields = ImageSubsamplingField.buildListOfFields(sfData, offset + 4, lengthOfFollowingData, config);
      } else {
        listOfFields = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      byte[] fieldsData = null;
      if (listOfFields != null && !listOfFields.isEmpty()) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (ImageSubsampling.ImageSubsamplingField field : listOfFields) {
          if (field == null) {
            continue;
          }
          field.writeAFP(baos, config);
        }
        fieldsData = baos.toByteArray();
        lengthOfFollowingData = (short) fieldsData.length;
      } else {
        lengthOfFollowingData = 0;
      }

      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      if (fieldsData != null) {
        os.write(fieldsData);
      }
    }

    public static abstract sealed class ImageSubsamplingField implements IAFPDecodeableWriteable {
      short fieldType;

      /**
       * Resets the subsampling field to its initial state for reuse.
       */
      public void reset() {
        fieldType = 0;
      }

      public static List<ImageSubsampling.ImageSubsamplingField> buildListOfFields(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
        List<ImageSubsampling.ImageSubsamplingField> result = new ArrayList<ImageSubsampling.ImageSubsamplingField>();
        int actualLength = length != -1 ? length : sfData.length - offset;
        int pos = 0;
        while (pos < actualLength) {
          if (pos + 2 > actualLength) {
            throw new AFPParserException("Truncated image subsampling field at offset " + pos);
          }
          short fieldType = UtilBinaryDecoding.parseShort(sfData, offset + pos, 1);
          if (fieldType == 0x01) {
            ImageSubsamplingField.SamplingRatios ratios = new SamplingRatios();
            ratios.decodeAFP(sfData, offset + pos, actualLength - pos, config);
            result.add(ratios);
            pos += 2 + ratios.lengthOfFollowingData;
          } else {
            throw new AFPParserException("The image subsampling field type 0x" + Integer.toHexString(fieldType) + " is undefined.");
          }
        }
        return result;
      }

      public short getFieldType() {
        return fieldType;
      }

      public void setFieldType(short fieldType) {
        this.fieldType = fieldType;
      }

      public static final class SamplingRatios extends ImageSubsampling.ImageSubsamplingField {
        short lengthOfFollowingData;
        List<SamplingRatios.SamplingRatiosRepeatingGroup> samplingRatiosRepeatingGroups;

        @Override
        public void reset() {
          fieldType = 0;
          lengthOfFollowingData = 0;
          samplingRatiosRepeatingGroups = null;
        }

        @Override
        public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
          int actualLength = StructuredField.getActualLength(sfData, offset, length);
          if (actualLength < 2) {
            throw new AFPParserException("SamplingRatios payload too short: " + actualLength);
          }
          fieldType = UtilBinaryDecoding.parseShort(sfData, offset, 1);
          lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
          if (2 + lengthOfFollowingData > actualLength) {
            throw new AFPParserException("Truncated SamplingRatios payload. Expected " + (2 + lengthOfFollowingData) + " but got " + actualLength);
          }
          int pos = 0;
          samplingRatiosRepeatingGroups = new ArrayList<SamplingRatios.SamplingRatiosRepeatingGroup>();
          while (pos + 2 <= lengthOfFollowingData) {
            samplingRatiosRepeatingGroups.add(new SamplingRatiosRepeatingGroup(
                sfData[offset + 2 + pos],
                sfData[offset + 2 + pos + 1]
            ));
            pos += 2;
          }
        }

        @Override
        public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
          lengthOfFollowingData = (short) (samplingRatiosRepeatingGroups != null
              ? samplingRatiosRepeatingGroups.size() * 2 : 0);
          os.write(fieldType);
          os.write(lengthOfFollowingData);
          if (samplingRatiosRepeatingGroups != null) {
            for (SamplingRatios.SamplingRatiosRepeatingGroup rg : samplingRatiosRepeatingGroups) {
              os.write(rg.nrOfHorizontalSamples());
              os.write(rg.nrOfVerticalSamples());
            }
          }
        }

        /**
         * Returns the length of following data.
         *
         * @return length of following data
         */
        public short getLengthOfFollowingData() {
          if (samplingRatiosRepeatingGroups == null) {
            lengthOfFollowingData = 0;
          } else {
            lengthOfFollowingData = (short) (samplingRatiosRepeatingGroups.size() * 2);
          }
          return lengthOfFollowingData;
        }

        public List<SamplingRatios.SamplingRatiosRepeatingGroup> getSamplingRatiosRepeatingGroups() {
          return samplingRatiosRepeatingGroups;
        }

        public void setSamplingRatiosRepeatingGroups(List<SamplingRatios.SamplingRatiosRepeatingGroup> samplingRatiosRepeatingGroups) {
          this.samplingRatiosRepeatingGroups = samplingRatiosRepeatingGroups;
          if (samplingRatiosRepeatingGroups != null) {
            lengthOfFollowingData = (short) (samplingRatiosRepeatingGroups.size() * 2);
          }
        }

        public record SamplingRatiosRepeatingGroup(
            @AFPField byte nrOfHorizontalSamples, @AFPField byte nrOfVerticalSamples) {}
      }
    }
  }

  public static final class BeginTile extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
    }

  }

  public static final class EndTile extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
    }
  }

  public static final class TilePosition extends IPD_Segment.IPD_SegmentLong {
    int horizontalOffset;
    int verticalOffset;

    @Override
    public void reset() {
      super.reset();
      horizontalOffset = 0;
      verticalOffset = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      horizontalOffset = UtilBinaryDecoding.parseInt(sfData, offset + 2, 4);
      verticalOffset = UtilBinaryDecoding.parseInt(sfData, offset + 6, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
      os.write(UtilBinaryDecoding.intToByteArray(horizontalOffset, 4));
      os.write(UtilBinaryDecoding.intToByteArray(verticalOffset, 4));
    }
  }

  public static final class TileSize extends IPD_Segment.IPD_SegmentLong {
    int horizontalSizeInImagePoints;
    int verticalSizeInImagePoints;
    TileSize.RelativeTileResolution relativeResolution;

    @Override
    public void reset() {
      super.reset();
      horizontalSizeInImagePoints = 0;
      verticalSizeInImagePoints = 0;
      relativeResolution = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      horizontalSizeInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset + 2, 4);
      verticalSizeInImagePoints = UtilBinaryDecoding.parseInt(sfData, offset + 6, 4);
      if (lengthOfFollowingData == 9) {
        relativeResolution = RelativeTileResolution.valueOf(sfData[offset + 10]);
      } else {
        relativeResolution = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (relativeResolution == null) {
        lengthOfFollowingData = 0x08;
      } else {
        lengthOfFollowingData = 0x09;
      }

      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
      os.write(UtilBinaryDecoding.intToByteArray(horizontalSizeInImagePoints, 4));
      os.write(UtilBinaryDecoding.intToByteArray(verticalSizeInImagePoints, 4));
      if (relativeResolution != null) {
        os.write(relativeResolution.toByte());
      }
    }

    public enum RelativeTileResolution {
      SameAsImagePresentationSpace(0x01),
      HalfOfImagePresentationSpace(0x02);
      int code;

      RelativeTileResolution(int code) {
        this.code = code;
      }

      public static TileSize.RelativeTileResolution valueOf(byte codeByte) {
        for (TileSize.RelativeTileResolution rtr : values()) {
          if (rtr.code == codeByte) {
            return rtr;
          }
        }
        return null;
      }

      public int toByte() {
        return code;
      }
    }
  }

  public static final class TileSetColor extends IPD_Segment.IPD_SegmentLong {
    AFPColorSpace colorSpace;
    byte[] reserved3_5 = new byte[] {0x00, 0x00, 0x00};
    short nrOfBitsIDEsComponent1;
    short nrOfBitsIDEsComponent2;
    short nrOfBitsIDEsComponent3;
    short nrOfBitsIDEsComponent4;
    byte[] color;

    @Override
    public void reset() {
      super.reset();
      colorSpace = null;
      reserved3_5 = new byte[] {0x00, 0x00, 0x00};
      nrOfBitsIDEsComponent1 = 0;
      nrOfBitsIDEsComponent2 = 0;
      nrOfBitsIDEsComponent3 = 0;
      nrOfBitsIDEsComponent4 = 0;
      color = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 2]);
      reserved3_5 = new byte[3];
      System.arraycopy(sfData, offset + 3, reserved3_5, 0, reserved3_5.length);
      nrOfBitsIDEsComponent1 = UtilBinaryDecoding.parseShort(sfData, offset + 6, 1);
      nrOfBitsIDEsComponent2 = UtilBinaryDecoding.parseShort(sfData, offset + 7, 1);
      nrOfBitsIDEsComponent3 = UtilBinaryDecoding.parseShort(sfData, offset + 8, 1);
      nrOfBitsIDEsComponent4 = UtilBinaryDecoding.parseShort(sfData, offset + 9, 1);
      color = new byte[lengthOfFollowingData - 8];
      System.arraycopy(sfData, offset + 10, color, 0, color.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 8 + color.length;
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
      os.write(colorSpace.toByte());
      os.write(reserved3_5);
      os.write(nrOfBitsIDEsComponent1);
      os.write(nrOfBitsIDEsComponent2);
      os.write(nrOfBitsIDEsComponent3);
      os.write(nrOfBitsIDEsComponent4);
      os.write(color);
    }
  }

  public static final class IncludeTile extends IPD_Segment.IPD_SegmentExtended {
    long tileResourceLocalID;

    @Override
    public void reset() {
      super.reset();
      tileResourceLocalID = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2)); // Two bytes segment type.
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2); // Two bytes length of following data.
      tileResourceLocalID = UtilBinaryDecoding.parseLong(sfData, offset + 4, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      os.write(UtilBinaryDecoding.longToByteArray(tileResourceLocalID, 4));
    }
  }

  public static final class TileTOC extends IPD_Segment.IPD_SegmentExtended {
    byte[] reserved4_5 = new byte[] {0x00, 0x00};
    List<TileTOC.TileTOC_RepeatingGroup> listOfRepeatingGroups;

    @Override
    public void reset() {
      super.reset();
      reserved4_5 = new byte[] {0x00, 0x00};
      listOfRepeatingGroups = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength < 6) {
        throw new AFPParserException("TileTOC payload too short: " + actualLength);
      }
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2)); // Two bytes segment type.
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2); // Two bytes length of following data.
      if (4 + lengthOfFollowingData > actualLength) {
        throw new AFPParserException("Truncated TileTOC payload. Expected " + (4 + lengthOfFollowingData) + " but got " + actualLength);
      }
      reserved4_5 = new byte[2];
      System.arraycopy(sfData, offset + 4, reserved4_5, 0, reserved4_5.length);
      int pos = 2;
      if (pos < lengthOfFollowingData) {
        listOfRepeatingGroups = new ArrayList<TileTOC.TileTOC_RepeatingGroup>();
        while (pos + 26 <= lengthOfFollowingData) {
          int horizontalOffset = UtilBinaryDecoding.parseInt(sfData, offset + 4 + pos, 4);
          int verticalOffset = UtilBinaryDecoding.parseInt(sfData, offset + 4 + pos + 4, 4);
          int horizontalSize = UtilBinaryDecoding.parseInt(sfData, offset + 4 + pos + 8, 4);
          int verticalSize = UtilBinaryDecoding.parseInt(sfData, offset + 4 + pos + 12, 4);
          var relRes = TileSize.RelativeTileResolution.valueOf(sfData[offset + 4 + pos + 16]);
          var compAlg = IPD_CompressionAlgorithm.valueOf(
              UtilBinaryDecoding.parseShort(sfData, offset + 4 + pos + 17, 1));
          long offsetToTile = UtilBinaryDecoding.parseLong(sfData, offset + 4 + pos + 18, 8);
          listOfRepeatingGroups.add(new TileTOC_RepeatingGroup(horizontalOffset, verticalOffset,
              horizontalSize, verticalSize, relRes, compAlg, offsetToTile));
          pos += 26;
        }
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (listOfRepeatingGroups == null || listOfRepeatingGroups.isEmpty()) {
        lengthOfFollowingData = 2;
      } else {
        lengthOfFollowingData = 2 + listOfRepeatingGroups.size() * 26;
      }
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      os.write(reserved4_5);
      if (listOfRepeatingGroups != null) {
        for (TileTOC.TileTOC_RepeatingGroup rg : listOfRepeatingGroups) {
          os.write(UtilBinaryDecoding.intToByteArray(rg.horizontalOffset(), 4));
          os.write(UtilBinaryDecoding.intToByteArray(rg.verticalOffset(), 4));
          os.write(UtilBinaryDecoding.intToByteArray(rg.horizontalSize(), 4));
          os.write(UtilBinaryDecoding.intToByteArray(rg.verticalSize(), 4));
          os.write(rg.relativeTileResolution().toByte());
          os.write(rg.compressionAlgorithmID().toByte());
          os.write(UtilBinaryDecoding.longToByteArray(rg.offsetInBytesFromBeginSegmentToBeginTile(), 8));
        }
      }
    }

    /**
     * Note that the IOCA specifies an unsigned 8byte value for {@link
     * #offsetInBytesFromBeginSegmentToBeginTile} which is in fact an quite astronomical number.
     * This record however support only signed 8byte values (long) which is about 1.84467*10^19
     * (2^64-1) bytes which is in fact only half the size as specified in IOCA but is still large
     * enough to cover Image Picture Data in the range of millions of terrabyte.<br>
     */
    @XmlRootElement
    public record TileTOC_RepeatingGroup(
        @AFPField int horizontalOffset,
        @AFPField int verticalOffset,
        @AFPField int horizontalSize,
        @AFPField int verticalSize,
        @AFPField TileSize.RelativeTileResolution relativeTileResolution,
        @AFPField IPD_CompressionAlgorithm compressionAlgorithmID,
        @AFPField long offsetInBytesFromBeginSegmentToBeginTile) {}
  }

  public static final class BeginTransparencyMask extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
    }
  }

  public static final class EndTransparencyMask extends IPD_Segment.IPD_SegmentLong {
    @Override
    public void reset() {
      super.reset();
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(segmentType.toBytes());
      os.write(lengthOfFollowingData);
    }
  }

  public static final class SetBilevelImageColor extends IPD_Segment.IPD_SegmentLong {
    short area;
    short reserved3 = 0x00;
    short nameColor;

    @Override
    public void reset() {
      super.reset();
      area = 0;
      reserved3 = 0x00;
      nameColor = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      area = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3 = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      nameColor = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 4;
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(area);
      os.write(reserved3);
      os.write(UtilBinaryDecoding.shortToByteArray(nameColor, 2));
    }
  }

  public static final class SetExtendedBilevelImageColor extends IPD_Segment.IPD_SegmentLong {
    short reserved2 = 0x00;
    AFPColorSpace colorSpace;
    byte[] reserved4_7 = new byte[] {0x00, 0x00, 0x00, 0x00};
    byte colSize1;
    byte colSize2;
    byte colSize3;
    byte colSize4;
    byte[] color;

    @Override
    public void reset() {
      super.reset();
      reserved2 = 0x00;
      colorSpace = null;
      reserved4_7 = new byte[] {0x00, 0x00, 0x00, 0x00};
      colSize1 = 0;
      colSize2 = 0;
      colSize3 = 0;
      colSize4 = 0;
      color = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      reserved2 = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 3]);
      reserved4_7 = new byte[4];
      System.arraycopy(sfData, offset + 4, reserved4_7, 0, reserved4_7.length);
      colSize1 = sfData[offset + 8];
      colSize2 = sfData[offset + 9];
      colSize3 = sfData[offset + 10];
      colSize4 = sfData[offset + 11];
      if (lengthOfFollowingData > 10) {
        color = new byte[lengthOfFollowingData - 10];
        System.arraycopy(sfData, offset + 12, color, 0, color.length);
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 10 + (color != null ? color.length : 0);
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(reserved2);
      os.write(colorSpace.toByte());
      os.write(reserved4_7);
      os.write(colSize1);
      os.write(colSize2);
      os.write(colSize3);
      os.write(colSize4);
      if (color != null) {
        os.write(color);
      }
    }
  }

  public static final class FunctionSetIdentification extends IPD_Segment.IPD_SegmentLong {
    short category = 0x01;
    short functionSet;

    @Override
    public void reset() {
      super.reset();
      category = 0x01;
      functionSet = 0;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset, 1));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      category = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      functionSet = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = 2;
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 1));
      os.write(category);
      os.write(functionSet);
    }
  }

  public static final class nColorNames extends IPD_Segment.IPD_SegmentExtended {
    short reserved4_5;
    List<ColorNameRepeatingGroup> repeatingGroups;
    String text;

    @Override
    public void reset() {
      super.reset();
      reserved4_5 = 0;
      repeatingGroups = null;
      text = null;
    }

    @XmlElement(name = "text")
    public String getText() {
      return UtilCharacterEncoding.sanitizeForXml(text);
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
      lengthOfFollowingData = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
      reserved4_5 = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      if (lengthOfFollowingData > 2) {
        repeatingGroups = new ArrayList<ColorNameRepeatingGroup>();
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        while (pos < lengthOfFollowingData - 2) {
          short reserved = UtilBinaryDecoding.parseShort(sfData, offset + 6 + pos, 1);
          short nameLen = UtilBinaryDecoding.parseShort(sfData, offset + 6 + pos + 1, 1);
          byte[] colorName = new byte[nameLen];
          System.arraycopy(sfData, offset + 6 + pos + 2, colorName, 0, nameLen);
          repeatingGroups.add(new ColorNameRepeatingGroup(reserved, colorName));

          if (sb.length() > 0) {
            sb.append(", ");
          }
          sb.append(new String(colorName, Constants.utf16be).trim());

          pos += 2 + nameLen;
        }
        text = sb.toString();
      } else {
        text = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      int payloadLen = 2;
      if (repeatingGroups != null) {
        for (ColorNameRepeatingGroup rg : repeatingGroups) {
          payloadLen += 2 + rg.colorName().length;
        }
      }
      lengthOfFollowingData = payloadLen;
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(reserved4_5, 2));
      if (repeatingGroups != null) {
        for (ColorNameRepeatingGroup rg : repeatingGroups) {
          os.write(rg.reserved());
          os.write(rg.colorName().length);
          os.write(rg.colorName());
        }
      }
    }

    public record ColorNameRepeatingGroup(short reserved, byte[] colorName) {}
  }

  public static final class ImageData extends IPD_Segment.IPD_SegmentExtended {
    byte[] imageData;

    @Override
    public void reset() {
      super.reset();
      imageData = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      imageData = new byte[lengthOfFollowingData];
      System.arraycopy(sfData, offset + 4, imageData, 0, imageData.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      lengthOfFollowingData = imageData.length;
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      os.write(imageData);
    }
  }

  public static final class BandImageData extends IPD_Segment.IPD_SegmentExtended {
    short bandNumber;
    byte[] reserved5_6 = new byte[] {0x00, 0x00};
    byte[] bandData;

    @Override
    public void reset() {
      super.reset();
      bandNumber = 0;
      reserved5_6 = new byte[] {0x00, 0x00};
      bandData = null;
    }

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      segmentType = IPD_SegmentType.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 2));
      lengthOfFollowingData = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
      bandNumber = UtilBinaryDecoding.parseShort(sfData, offset + 4, 1);
      reserved5_6 = new byte[2];
      System.arraycopy(sfData, offset + 5, reserved5_6, 0, reserved5_6.length);
      if (lengthOfFollowingData > 3) {
        bandData = new byte[lengthOfFollowingData - 3];
        System.arraycopy(sfData, offset + 7, bandData, 0, bandData.length);
      } else {
        bandData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (bandData == null) {
        lengthOfFollowingData = 3;
      } else {
        lengthOfFollowingData = bandData.length + 3;
      }
      os.write(segmentType.toBytes());
      os.write(UtilBinaryDecoding.intToByteArray(lengthOfFollowingData, 2));
      os.write(bandNumber);
      os.write(reserved5_6);
      if (bandData != null) os.write(bandData);
    }
  }
}
