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
import javax.xml.bind.annotation.XmlRootElement;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.goca.GAD_DrawingOrder.GBAR_BeginArea;
import com.mgz.afp.goca.GAD_DrawingOrder.GBCP_BeginCustomPattern;
import com.mgz.afp.goca.GAD_DrawingOrder.GBIMG_BeginImageAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GBOX_BoxAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GBSEG_BeginSegment;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBEZ_CubicBezierCurveAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBIMG_BeginImageAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCCBEZ_CubicBezierCurveAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCBOX_BoxAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCCHST_CharacterStringAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCFARC_FullArcAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCFLT_FilletAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCHST_CharacterStringAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCLINE_LineAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCMRK_MarkerAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCOMT_Comment;
import com.mgz.afp.goca.GAD_DrawingOrder.GCPARC_PartialArcAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GCRLINE_RelativeLineAtCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GDPT_DeletePattern;
import com.mgz.afp.goca.GAD_DrawingOrder.GEAR_EndArea;
import com.mgz.afp.goca.GAD_DrawingOrder.GECP_EndCustomPattern;
import com.mgz.afp.goca.GAD_DrawingOrder.GEIMG_EndImage;
import com.mgz.afp.goca.GAD_DrawingOrder.GEPROL_EndProlog;
import com.mgz.afp.goca.GAD_DrawingOrder.GESEG_EndSegment;
import com.mgz.afp.goca.GAD_DrawingOrder.GEXO_ExtendedOrder;
import com.mgz.afp.goca.GAD_DrawingOrder.GFARC_FullArcAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GFLT_FilletAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GIMD_ImageData;
import com.mgz.afp.goca.GAD_DrawingOrder.GLGD_LinearGradient;
import com.mgz.afp.goca.GAD_DrawingOrder.GLINE_LineAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GMRK_MarkerAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GNOP1_NopOperation;
import com.mgz.afp.goca.GAD_DrawingOrder.GPARC_PartialArcAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GRGD_RadialGradient;
import com.mgz.afp.goca.GAD_DrawingOrder.GRLINE_RelativeLineAtGivenPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GSAP_SetArcParameters;
import com.mgz.afp.goca.GAD_DrawingOrder.GSBMX_SetBackgroundMix;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCA_SetCharacterAngle;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCC_SetCharacterCell;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCD_SetCharacterDirection;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCH_SetCharacterShear;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCLT_SetCustomLineType;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCOL_SetColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCP_SetCurrentPosition;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCR_SetCharacterPrecision;
import com.mgz.afp.goca.GAD_DrawingOrder.GSCS_SetCharacterSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSECOL_SetExtendedColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSFLW_SetFractionLineWidth;
import com.mgz.afp.goca.GAD_DrawingOrder.GSGCH_SegmentCharacteristics;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLE_SetLineEnd;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLJ_SetLineJoin;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLT_SetLineType;
import com.mgz.afp.goca.GAD_DrawingOrder.GSLW_SetLineWidth;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMC_SetMarkerCell;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMT_SetMarkerSymbol;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMX_SetMix;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMP_SetMarkerPrecision;
import com.mgz.afp.goca.GAD_DrawingOrder.GSMS_SetMarkerSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPIK_SetPickIdentifier;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPCOL_SetProcessColor;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPRP_SetPatternReferencePoint;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPS_SetPatternSet;
import com.mgz.afp.goca.GAD_DrawingOrder.GSPT_SetPatternSymbol;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.MnemonicPerformanceMonitor;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * GOCA, 164.<br><br>
 * <p>
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

  private static final Map<Short, Supplier<GAD_DrawingOrder>> DO_SUPPLIERS = new HashMap<>();
  private static final Map<Short, Supplier<GAD_DrawingOrder>> DO_EXT_SUPPLIERS = new HashMap<>();

  static {
    DO_SUPPLIERS.put((short) 0x00, GNOP1_NopOperation::new);
    DO_SUPPLIERS.put((short) 0x01, GCOMT_Comment::new);
    DO_SUPPLIERS.put((short) 0x04, GSGCH_SegmentCharacteristics::new);
    DO_SUPPLIERS.put((short) 0x08, GSPS_SetPatternSet::new);
    DO_SUPPLIERS.put((short) 0x0a, GSCOL_SetColor::new);
    DO_SUPPLIERS.put((short) 0x0c, GSMX_SetMix::new);
    DO_SUPPLIERS.put((short) 0x0d, GSBMX_SetBackgroundMix::new);
    DO_SUPPLIERS.put((short) 0x11, GSFLW_SetFractionLineWidth::new);
    DO_SUPPLIERS.put((short) 0x18, GSLT_SetLineType::new);
    DO_SUPPLIERS.put((short) 0x19, GSLW_SetLineWidth::new);
    DO_SUPPLIERS.put((short) 0x1a, GSLE_SetLineEnd::new);
    DO_SUPPLIERS.put((short) 0x1b, GSLJ_SetLineJoin::new);
    DO_SUPPLIERS.put((short) 0x20, GSCLT_SetCustomLineType::new);
    DO_SUPPLIERS.put((short) 0x21, GSCP_SetCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x22, GSAP_SetArcParameters::new);
    DO_SUPPLIERS.put((short) 0x26, GSECOL_SetExtendedColor::new);
    DO_SUPPLIERS.put((short) 0x28, GSPT_SetPatternSymbol::new);
    DO_SUPPLIERS.put((short) 0x29, GSMT_SetMarkerSymbol::new);
    DO_SUPPLIERS.put((short) 0x33, GSCC_SetCharacterCell::new);
    DO_SUPPLIERS.put((short) 0x34, GSCA_SetCharacterAngle::new);
    DO_SUPPLIERS.put((short) 0x35, GSCH_SetCharacterShear::new);
    DO_SUPPLIERS.put((short) 0x37, GSMC_SetMarkerCell::new);
    DO_SUPPLIERS.put((short) 0x38, GSCS_SetCharacterSet::new);
    DO_SUPPLIERS.put((short) 0x39, GSCR_SetCharacterPrecision::new);
    DO_SUPPLIERS.put((short) 0x3a, GSCD_SetCharacterDirection::new);
    DO_SUPPLIERS.put((short) 0x3b, GSMP_SetMarkerPrecision::new);
    DO_SUPPLIERS.put((short) 0x3c, GSMS_SetMarkerSet::new);
    DO_SUPPLIERS.put((short) 0x3e, GEPROL_EndProlog::new);
    DO_SUPPLIERS.put((short) 0x43, GSPIK_SetPickIdentifier::new);
    DO_SUPPLIERS.put((short) 0x5e, GECP_EndCustomPattern::new);
    DO_SUPPLIERS.put((short) 0x60, GEAR_EndArea::new);
    DO_SUPPLIERS.put((short) 0x68, GBAR_BeginArea::new);
    DO_SUPPLIERS.put((short) 0x70, GBSEG_BeginSegment::new);
    DO_SUPPLIERS.put((short) 0x71, GESEG_EndSegment::new);
    DO_SUPPLIERS.put((short) 0x80, GCBOX_BoxAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x81, GCLINE_LineAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x82, GCMRK_MarkerAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x83, GCCHST_CharacterStringAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x85, GCFLT_FilletAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x87, GCFARC_FullArcAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x91, GCBIMG_BeginImageAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0x92, GIMD_ImageData::new);
    DO_SUPPLIERS.put((short) 0x93, GEIMG_EndImage::new);
    DO_SUPPLIERS.put((short) 0xa0, GSPRP_SetPatternReferencePoint::new);
    DO_SUPPLIERS.put((short) 0xa1, GCRLINE_RelativeLineAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0xa3, GCPARC_PartialArcAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0xa5, GCCBEZ_CubicBezierCurveAtCurrentPosition::new);
    DO_SUPPLIERS.put((short) 0xb2, GSPCOL_SetProcessColor::new);
    DO_SUPPLIERS.put((short) 0xc0, GBOX_BoxAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xc1, GLINE_LineAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xc2, GMRK_MarkerAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xc3, GCHST_CharacterStringAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xc5, GFLT_FilletAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xc7, GFARC_FullArcAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xd1, GBIMG_BeginImageAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xe1, GRLINE_RelativeLineAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xe3, GPARC_PartialArcAtGivenPosition::new);
    DO_SUPPLIERS.put((short) 0xde, GBCP_BeginCustomPattern::new);
    DO_SUPPLIERS.put((short) 0xdf, GDPT_DeletePattern::new);
    DO_SUPPLIERS.put((short) 0xe5, GCBEZ_CubicBezierCurveAtGivenPosition::new);

    DO_EXT_SUPPLIERS.put((short) 0xDC, GLGD_LinearGradient::new);
    DO_EXT_SUPPLIERS.put((short) 0xDD, GRGD_RadialGradient::new);
  }

  @AFPField
  private List<GAD_DrawingOrder> drawingOrders;

  @Override
  public void reset() {
    super.reset();
    drawingOrders = null;
  }

  public static final List<GAD_DrawingOrder> buildDrawingOrders(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {

    int actualLength = length != -1 ? length : sfData.length - offset;

    List<GAD_DrawingOrder> drawingOrders = new ArrayList<GAD_DrawingOrder>();

    int pos = 0;
    while (pos < actualLength) {

      int dotLength = 0;
      GAD_DrawingOrder drawingOrder = null;

      short drawingOrderCode = (short) (sfData[offset + pos] & 0xFF);
      if (drawingOrderCode == (short) 0xFE) {
        short qualifier = (short) (sfData[offset + pos + 1] & 0xFF);
        drawingOrder = DrawingOrderPool.acquire(drawingOrderCode, qualifier);
        if (drawingOrder == null) {
          drawingOrder = DO_EXT_SUPPLIERS.getOrDefault(qualifier, GEXO_ExtendedOrder::new).get();
        }
        dotLength = UtilBinaryDecoding.parseInt(sfData, offset + pos + 2, 2) + 4;
      } else {
        drawingOrder = DrawingOrderPool.acquire(drawingOrderCode);
        if (drawingOrder == null) {
          drawingOrder = DO_SUPPLIERS.getOrDefault(drawingOrderCode, () -> null).get();
        }

        if (drawingOrder == null) {
          throw new AFPParserException("The drawing order code 0x" + Integer.toHexString(drawingOrderCode) + " is unknown.");
        }

        switch (drawingOrderCode) {
          case 0x00:
            dotLength = 1;
            break;
          case 0x08:
          case 0x0a:
          case 0x0c:
          case 0x0d:
          case 0x18:
          case 0x19:
          case 0x1a:
          case 0x1b:
          case 0x28:
          case 0x29:
          case 0x38:
          case 0x39:
          case 0x3a:
          case 0x3b:
          case 0x3c:
          case 0x3e:
          case 0x43:
          case 0x5e:
          case 0x68:
          case 0x71:
            dotLength = 2;
            break;
          case 0x70:
            dotLength = 14 + UtilBinaryDecoding.parseInt(sfData, offset + pos + 8, 2);
            break;
          default:
            dotLength = (sfData[offset + pos + 1] & 0xFF) + 2;
            break;
        }
      }

      MnemonicPerformanceMonitor.startParse(drawingOrder);
      try {
        drawingOrder.decodeAFP(sfData, offset + pos, dotLength, config);
      } finally {
        MnemonicPerformanceMonitor.endParse();
      }
      drawingOrders.add(drawingOrder);

      pos += dotLength;

    }

    return drawingOrders;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 0) {
      drawingOrders = buildDrawingOrders(sfData, offset, actualLength, config);
    } else {
      drawingOrders = null;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    if (drawingOrders != null && !drawingOrders.isEmpty()) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      for (GAD_DrawingOrder order : drawingOrders) {
        order.writeAFP(baos, config);
      }
      writeFullStructuredField(os, baos.toByteArray());
    } else {
      writeFullStructuredField(os, (byte[]) null);
    }
  }

  public List<GAD_DrawingOrder> getDrawingOrders() {
    return drawingOrders;
  }

  public void setDrawingOrders(List<GAD_DrawingOrder> drawingOrders) {
    this.drawingOrders = drawingOrders;
  }

  @Override
  public void release() {
    if (drawingOrders != null) {
      for (GAD_DrawingOrder order : drawingOrders) {
        order.release();
      }
      drawingOrders = null;
    }
    super.release();
  }

}

