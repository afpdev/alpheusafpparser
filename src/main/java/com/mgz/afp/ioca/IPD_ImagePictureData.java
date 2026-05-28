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

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.ioca.IPD_Segment;
import com.mgz.afp.ioca.IPD_Segment.BandImage;
import com.mgz.afp.ioca.IPD_Segment.BandImageData;
import com.mgz.afp.ioca.IPD_Segment.BeginImageContent;
import com.mgz.afp.ioca.IPD_Segment.BeginSegment;
import com.mgz.afp.ioca.IPD_Segment.BeginTile;
import com.mgz.afp.ioca.IPD_Segment.BeginTransparencyMask;
import com.mgz.afp.ioca.IPD_Segment.EndImageContent;
import com.mgz.afp.ioca.IPD_Segment.EndSegment;
import com.mgz.afp.ioca.IPD_Segment.EndTile;
import com.mgz.afp.ioca.IPD_Segment.EndTransparencyMask;
import com.mgz.afp.ioca.IPD_Segment.ExternalAlgorithmSpecification;
import com.mgz.afp.ioca.IPD_Segment.FunctionSetIdentification;
import com.mgz.afp.ioca.IPD_Segment.IDESize;
import com.mgz.afp.ioca.IPD_Segment.IDEStructure;
import com.mgz.afp.ioca.IPD_Segment.IPD_SegmentExtended;
import com.mgz.afp.ioca.IPD_Segment.IPD_SegmentType;
import com.mgz.afp.ioca.IPD_Segment.ImageData;
import com.mgz.afp.ioca.IPD_Segment.ImageEncoding;
import com.mgz.afp.ioca.IPD_Segment.ImageLUTID;
import com.mgz.afp.ioca.IPD_Segment.ImageSize;
import com.mgz.afp.ioca.IPD_Segment.ImageSubsampling;
import com.mgz.afp.ioca.IPD_Segment.IncludeTile;
import com.mgz.afp.ioca.IPD_Segment.SetBilevelImageColor;
import com.mgz.afp.ioca.IPD_Segment.SetExtendedBilevelImageColor;
import com.mgz.afp.ioca.IPD_Segment.TilePosition;
import com.mgz.afp.ioca.IPD_Segment.TileSetColor;
import com.mgz.afp.ioca.IPD_Segment.TileSize;
import com.mgz.afp.ioca.IPD_Segment.TileTOC;
import com.mgz.afp.ioca.IPD_Segment.UnknownSegmentExtended;
import com.mgz.afp.ioca.IPD_Segment.UnknownSegmentLong;
import com.mgz.afp.ioca.IPD_Segment.nColorNames;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class IPD_ImagePictureData extends StructuredField {

  private static final EnumMap<IPD_SegmentType, Supplier<IPD_Segment>> SEGMENT_SUPPLIERS =
      new EnumMap<>(IPD_SegmentType.class);

  static {
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BeginSegment, BeginSegment::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.EndSegment, EndSegment::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BeginImageContent, BeginImageContent::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.EndImageContent, EndImageContent::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ImageSize, ImageSize::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ImageEncoding, ImageEncoding::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.IDESize, IDESize::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ImageLUTID, ImageLUTID::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BandImage, BandImage::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.IDEStructure, IDEStructure::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ExternalAlgorithmSpecification, ExternalAlgorithmSpecification::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ImageSubsampling, ImageSubsampling::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BeginTile, BeginTile::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.EndTile, EndTile::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.TilePosition, TilePosition::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.TileSize, TileSize::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.TileSetColor, TileSetColor::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.SetExtendedBilevelImageColor, SetExtendedBilevelImageColor::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.SetBilevelImageColor, SetBilevelImageColor::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.FunctionSetIdentification, FunctionSetIdentification::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.IncludeTile, IncludeTile::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.TileTOC, TileTOC::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BeginTransparencyMask, BeginTransparencyMask::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.EndTransparencyMask, EndTransparencyMask::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.ImageData, ImageData::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.BandImageData, BandImageData::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.nColorNames, nColorNames::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.UnknownIPDSegmentLong, UnknownSegmentLong::new);
    SEGMENT_SUPPLIERS.put(IPD_SegmentType.UnknownIPDSegmentExtended, UnknownSegmentExtended::new);
  }

  private List<IPD_Segment> listOfSegments;

  @Override
  public void reset() {
    super.reset();
    listOfSegments = null;
  }

  public List<IPD_Segment> getListOfSegments() {
    return listOfSegments;
  }

  public void setListOfSegments(List<IPD_Segment> listOfSegments) {
    this.listOfSegments = listOfSegments;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = super.getActualLength(sfData, offset, length);

    listOfSegments = new ArrayList<IPD_Segment>();
    int pos = 0;
    while (pos < actualLength) {
      int segmentTypeCode = sfData[offset + pos] & 0xFF;

      int introducerLen = 2;
      if (segmentTypeCode == 0xFE) {
        if (pos + 4 > actualLength) {
          throw new AFPParserException("Truncated extended IPD segment introducer at offset " + pos);
        }
        segmentTypeCode = UtilBinaryDecoding.parseInt(sfData, offset + pos, 2);
        introducerLen = 4;
      } else {
        if (pos + 2 > actualLength) {
          throw new AFPParserException("Truncated IPD segment introducer at offset " + pos);
        }
      }
      IPD_SegmentType segmentType = IPD_SegmentType.valueOf(segmentTypeCode);

      IPD_Segment ipdSegment = IpdSegmentPool.acquire(segmentType);
      if (ipdSegment == null) {
        var supplier = SEGMENT_SUPPLIERS.get(segmentType);
        if (supplier == null) {
          throw new AFPParserException("No supplier for IPD segment type: " + segmentType);
        }
        ipdSegment = supplier.get();
      }

      ipdSegment.decodeAFP(sfData, offset + pos, actualLength - pos, config);

      if (pos + introducerLen + ipdSegment.getLengthOfFollowingData() > actualLength) {
        throw new AFPParserException("Truncated IPD segment payload at offset " + pos);
      }

      listOfSegments.add(ipdSegment);

      pos += (introducerLen + ipdSegment.getLengthOfFollowingData());
    }
  }

  @Override
  public void release() {
    if (listOfSegments != null) {
      for (IPD_Segment segment : listOfSegments) {
        segment.release();
      }
      listOfSegments = null;
    }
    super.release();
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    if (listOfSegments != null && !listOfSegments.isEmpty()) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      for (IPD_Segment segment : listOfSegments) {
        segment.writeAFP(baos, config);
      }
      writeFullStructuredField(os, baos.toByteArray());
    } else {
      writeFullStructuredField(os, (byte[]) null);
    }
  }
}