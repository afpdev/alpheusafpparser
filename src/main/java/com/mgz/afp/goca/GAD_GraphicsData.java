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
package com.mgz.afp.goca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.goca.GAD_DrawingOrder.GBAR_BeginArea;
import com.mgz.afp.goca.GAD_DrawingOrder.GBIMG_BeginImageAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GBOX_BoxAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBEZ_CubicBezierCurveAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBIMG_BeginImageAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBOX_BoxAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCCBEZ_CubicBezierCurveAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCCHST_CharacterStringAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCFARC_FullArcAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCFLT_FilletAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCHST_CharacterStringAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCLINE_LineAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCMRK_MarkerAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCOMT_Comment;
import com.mgz.afp.goca.GAD_DrawingOrder.GCPARC_PartialArcAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCRLINE_RelativeLineAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GDGCH_SegmentCharacteristics;
import com.mgz.afp.goca.GAD_DrawingOrder.GEAR_EndArea;
import com.mgz.afp.goca.GAD_DrawingOrder.GEIMD_EndImage;
import com.mgz.afp.goca.GAD_DrawingOrder.GEPROL_EndProlog;
import com.mgz.afp.goca.GAD_DrawingOrder.GEXO_ExtendedOrder;
import com.mgz.afp.goca.GAD_DrawingOrder.GFARC_FullArcAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GFLT_FilletAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GIMD_ImageData;
import com.mgz.afp.goca.GAD_DrawingOrder.GLINE_LineAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GMRK_MarkerAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GNOP1_NopOperation;
import com.mgz.afp.goca.GAD_DrawingOrder.GPARC_PartialArcAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GRLINE_RelativeLineAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GSAP_SetArcParameters;
import com.mgz.afp.goca.GAD_DrawingOrder.GSBMX_SetBackgroundMix;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCA_SetCharacterAngle;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCC_SetCharacterCell;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCD_SetCharacterDirection;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCH_SetCharacterShear;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCOL_SetColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCP_SetCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCR_SetCharacterPrecision;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCS_SetCharacterSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSECOL_SetExtendedColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSFLW_SetFractionLineWidth;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLE_SetLineEnd;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLJ_SetLineJoin;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLT_SetLineType;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLW_SetLineWidth;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMC_SetMarkerCell;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMP_SetMarkerPrecision;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMS_SetMarkerSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMT_SetMarkerSymbol;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMX_SetMix;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPCOL_SetProcessColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPS_SetPatternSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPT_SetPatternSymbol;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * GOCA, 164.<br><br>
 *
 * The graphics segments for a graphics object are contained within one or more GAD structured
 * fields. Receipt of the first segment starts the drawing process. No restrictions exist on how
 * much or how little graphics data is specified in a single GAD, except for the length limit of the
 * structured field. A GAD, for example, can carry partial segments, full segments, multiple
 * segments, or any combination of these. The only requirement is that the data itself is ordered in
 * the sequence that is expected for immediate processing and that the last GAD completes the last
 * segment. Because this environment does not support the calling of segments, all segments should
 * be chained segments. Any unchained segments in the data are ignored. The GAD structured field is
 * optional in a MO:DCA graphics object and may be repeated multiple times.
 */
public class GAD_GraphicsData extends StructuredField {

  private static final List<GAD_DrawingOrder> buildDrawingOrders(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {

    int actualLength = length != -1 ? length : sfData.length - offset;

    List<GAD_DrawingOrder> drawingOrders = new ArrayList<GAD_DrawingOrder>();

    int pos = 0;
    while (pos < actualLength) {

      int dotLength = 0;
      GAD_DrawingOrder drawingOrder = null;

      int drawingOrderCode = UtilBinaryDecoding.parseInt(sfData, offset + pos, 1);
      switch (drawingOrderCode) {
        case 0x00: {
          drawingOrder = new GNOP1_NopOperation();
          dotLength = 1;
        }
        break;
        case 0x01: {
          drawingOrder = new GCOMT_Comment();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x04: {
          drawingOrder = new GDGCH_SegmentCharacteristics();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x08: {
          drawingOrder = new GSPS_SetPatternSet();
          dotLength = 2;
        }
        break;
        case 0x0a: {
          drawingOrder = new GSCOL_SetColor();
          dotLength = 2;
        }
        break;
        case 0x0c: {
          drawingOrder = new GSMX_SetMix();
          dotLength = 2;
        }
        break;
        case 0x0d: {
          drawingOrder = new GSBMX_SetBackgroundMix();
          dotLength = 2;
        }
        break;
        case 0x11: {
          drawingOrder = new GSFLW_SetFractionLineWidth();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x18: {
          drawingOrder = new GSLT_SetLineType();
          dotLength = 2;
        }
        break;
        case 0x19: {
          drawingOrder = new GSLW_SetLineWidth();
          dotLength = 2;
        }
        break;
        case 0x1a: {
          drawingOrder = new GSLE_SetLineEnd();
          dotLength = 2;
        }
        break;
        case 0x1b: {
          drawingOrder = new GSLJ_SetLineJoin();
          dotLength = 2;
        }
        break;
        case 0x21: {
          drawingOrder = new GSCP_SetCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x22: {
          drawingOrder = new GSAP_SetArcParameters();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x26: {
          drawingOrder = new GSECOL_SetExtendedColor();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x28: {
          drawingOrder = new GSPT_SetPatternSymbol();
          dotLength = 2;
        }
        break;
        case 0x29: {
          drawingOrder = new GSMT_SetMarkerSymbol();
          dotLength = 2;
        }
        break;
        case 0x33: {
          drawingOrder = new GSCC_SetCharacterCell();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x34: {
          drawingOrder = new GSCA_SetCharacterAngle();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x35: {
          drawingOrder = new GSCH_SetCharacterShear();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x37: {
          drawingOrder = new GSMC_SetMarkerCell();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x38: {
          drawingOrder = new GSCS_SetCharacterSet();
          dotLength = 2;
        }
        break;
        case 0x39: {
          drawingOrder = new GSCR_SetCharacterPrecision();
          dotLength = 2;
        }
        break;
        case 0x3a: {
          drawingOrder = new GSCD_SetCharacterDirection();
          dotLength = 2;
        }
        break;
        case 0x3b: {
          drawingOrder = new GSMP_SetMarkerPrecision();
          dotLength = 2;
        }
        break;
        case 0x3c: {
          drawingOrder = new GSMS_SetMarkerSet();
          dotLength = 2;
        }
        break;
        case 0x3e: {
          drawingOrder = new GEPROL_EndProlog();
          dotLength = 2;
        }
        break;
        case 0x60: {
          drawingOrder = new GEAR_EndArea();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x68: {
          drawingOrder = new GBAR_BeginArea();
          dotLength = 2;
        }
        break;
        case 0x80: {
          drawingOrder = new GCBOX_BoxAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x81: {
          drawingOrder = new GCLINE_LineAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x82: {
          drawingOrder = new GCMRK_MarkerAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x83: {
          drawingOrder = new GCCHST_CharacterStringAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x85: {
          drawingOrder = new GCFLT_FilletAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x87: {
          drawingOrder = new GCFARC_FullArcAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x91: {
          drawingOrder = new GCBIMG_BeginImageAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x92: {
          drawingOrder = new GIMD_ImageData();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0x93: {
          drawingOrder = new GEIMD_EndImage();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xa1: {
          drawingOrder = new GCRLINE_RelativeLineAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xa3: {
          drawingOrder = new GCPARC_PartialArcAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xa5: {
          drawingOrder = new GCCBEZ_CubicBezierCurveAtCurrentPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xb2: {
          drawingOrder = new GSPCOL_SetProcessColor();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc0: {
          drawingOrder = new GBOX_BoxAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc1: {
          drawingOrder = new GLINE_LineAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc2: {
          drawingOrder = new GMRK_MarkerAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc3: {
          drawingOrder = new GCHST_CharacterStringAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc5: {
          drawingOrder = new GFLT_FilletAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xc7: {
          drawingOrder = new GFARC_FullArcAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xd1: {
          drawingOrder = new GBIMG_BeginImageAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xe1: {
          drawingOrder = new GRLINE_RelativeLineAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xe3: {
          drawingOrder = new GPARC_PartialArcAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xe5: {
          drawingOrder = new GCBEZ_CubicBezierCurveAtGivenPosition();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 1, 1) + 2;
        }
        break;
        case 0xfe: {
          drawingOrder = new GEXO_ExtendedOrder();
          dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 2, 2) + 4;
        }
        break;
        default: {
          drawingOrder = null;
        }
        break;
      }

      if (drawingOrder == null) {
        throw new AFPParserException("The drawing order code 0x" + Integer.toHexString(drawingOrderCode) + "is unknown.");
      }

      drawingOrder.decodeAFP(sfData, offset + pos, dotLength, config);
      drawingOrders.add(drawingOrder);

      pos += dotLength;

    }

    return drawingOrders;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
  }

  public static class BeginSegment implements IAFPDecodeableWriteable {
    public static short COMMANDCODE_BeginSegment = 0x70;
    @AFPField
    short commandCode = COMMANDCODE_BeginSegment;
    @AFPField
    short lengtOfFollowingParameters = 0x0C;
    @AFPField
    String nameOfSegment;
    @AFPField
    byte flagAnyValue;
    @AFPField
    EnumSet<SegmentPropertiesFlag> segmentPropertiesFlags = EnumSet.noneOf(SegmentPropertiesFlag.class);
    @AFPField
    int segmentDataLength;
    @AFPField
    String nameOfPredecessorSuccessorSegment;
    @AFPField
    List<GAD_DrawingOrder> drawingOrders;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {

      commandCode = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      lengtOfFollowingParameters = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);
      nameOfSegment = new String(sfData, offset + 2, 4, Constants.cpIBM500);
      flagAnyValue = sfData[offset + 6];
      segmentPropertiesFlags = SegmentPropertiesFlag.valueOF(sfData[offset + 7]);
      segmentDataLength = UtilBinaryDecoding.parseInt(sfData, offset + 8, 2);
      nameOfPredecessorSuccessorSegment = new String(sfData, offset + 10, 4, Constants.cpIBM500);

      if (segmentDataLength > 0) {
        drawingOrders = buildDrawingOrders(sfData, offset + 13, segmentDataLength, config);
      } else {
        drawingOrders = null;
      }
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      byte[] drawingOrdersData = null;

      os.write(commandCode);
      os.write(lengtOfFollowingParameters);
      os.write(nameOfSegment.getBytes(Constants.cpIBM500));
      os.write(flagAnyValue);
      if (segmentPropertiesFlags != null)
        os.write(SegmentPropertiesFlag.toByte(segmentPropertiesFlags));
      else os.write(0x00);

      if (drawingOrders != null && drawingOrders.size() > 0) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (GAD_DrawingOrder order : drawingOrders) {
          if (order == null) continue;
          order.writeAFP(baos, config);
        }
        drawingOrdersData = baos.toByteArray();
        segmentDataLength = drawingOrdersData.length;
      } else {
        segmentDataLength = 0;
      }

      os.write(UtilBinaryDecoding.intToByteArray(segmentDataLength, 2));
      os.write(nameOfPredecessorSuccessorSegment.getBytes(Constants.cpIBM500));

      if (drawingOrdersData != null) {
        os.write(drawingOrdersData);
      }
    }

    /**
     * Sets the given {@link SegmentPropertiesFlag} and un-sets corresponding mutual exclusive
     * flags.
     *
     * @param flag {@link SegmentPropertiesFlag} to set.
     */
    public void setSegmentPropertiesFlag(SegmentPropertiesFlag flag) {
      if (segmentPropertiesFlags == null)
        segmentPropertiesFlags = EnumSet.noneOf(SegmentPropertiesFlag.class);
      SegmentPropertiesFlag.setFlag(segmentPropertiesFlags, flag);
    }

    public short getCommandCode() {
      return commandCode;
    }

    public void setCommandCode(short commandCode) {
      this.commandCode = commandCode;
    }

    public short getLengtOfFollowingParameters() {
      return lengtOfFollowingParameters;
    }

    public void setLengtOfFollowingParameters(short lengtOfFollowingParameters) {
      this.lengtOfFollowingParameters = lengtOfFollowingParameters;
    }

    public String getNameOfSegment() {
      return nameOfSegment;
    }

    public void setNameOfSegment(String nameOfSegment) {
      this.nameOfSegment = nameOfSegment;
    }

    public byte getFlagAnyValue() {
      return flagAnyValue;
    }

    public void setFlagAnyValue(byte flagAnyValue) {
      this.flagAnyValue = flagAnyValue;
    }

    public EnumSet<SegmentPropertiesFlag> getSegmentPropertiesFlags() {
      return segmentPropertiesFlags;
    }

    public void setSegmentPropertiesFlags(
            EnumSet<SegmentPropertiesFlag> segmentPropertiesFlags) {
      this.segmentPropertiesFlags = segmentPropertiesFlags;
    }

    public int getSegmentDataLength() {
      return segmentDataLength;
    }

    public void setSegmentDataLength(int segmentDataLength) {
      this.segmentDataLength = segmentDataLength;
    }

    public String getNameOfPredecessorSuccessorSegment() {
      return nameOfPredecessorSuccessorSegment;
    }

    public void setNameOfPredecessorSuccessorSegment(
            String nameOfPredecessorSuccessorSegment) {
      this.nameOfPredecessorSuccessorSegment = nameOfPredecessorSuccessorSegment;
    }

    public List<GAD_DrawingOrder> getDrawingOrders() {
      return drawingOrders;
    }

    public void setDrawingOrders(List<GAD_DrawingOrder> drawingOrders) {
      this.drawingOrders = drawingOrders;
    }

    public enum SegmentPropertiesFlag implements IMutualExclusiveGroupedFlag {
      Chained(0),
      Unchained(0),
      NoProlog(1),
      Prolog(1),
      NewSegment(2),
      Reserved_01(2),
      Reserved_10(2),
      AppendToExisting(2);

      int group;

      SegmentPropertiesFlag(int group) {
        this.group = group;
      }

      public static EnumSet<SegmentPropertiesFlag> valueOF(byte flagsByte) {
        EnumSet<SegmentPropertiesFlag> result = EnumSet.noneOf(SegmentPropertiesFlag.class);
        if ((flagsByte & 0x80) == 0) result.add(Chained);
        else result.add(Unchained);
        if ((flagsByte & 0x10) == 0) result.add(NoProlog);
        else result.add(Prolog);
        int crap = (flagsByte >> 1) & 0x03;
        if (crap == 0x00) result.add(NewSegment);
        else if (crap == 0x01) result.add(Reserved_01);
        else if (crap == 0x02) result.add(Reserved_10);
        else if (crap == 0x03) result.add(AppendToExisting);
        return result;
      }

      public static int toByte(EnumSet<SegmentPropertiesFlag> flags) {
        int result = 0;

        if (flags.contains(Unchained)) result |= 0x80;
        if (flags.contains(Prolog)) result |= 0x10;
        if (flags.contains(Reserved_01)) result += 2;
        else if (flags.contains(Reserved_10)) result += 4;
        else if (flags.contains(AppendToExisting)) result += 6;

        return result;
      }

      public static void setFlag(EnumSet<SegmentPropertiesFlag> set, SegmentPropertiesFlag flag) {
        new MutualExclusiveGroupedFlagHandler<SegmentPropertiesFlag>().setFlag(set, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }

  }
}

