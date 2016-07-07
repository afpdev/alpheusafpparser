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
package com.mgz.afp.modca;

import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.RepeatingGroupBase;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;

/**
 * MO:DCA, page 312. <br><br> The Page Position structured field specifies the position and
 * orientation of a page's presentation space on the medium presentation space for the physical
 * medium. The PGP may be located in a medium map or in the document environment group of a form
 * map. When present in the active medium map, it overrides a PGP in the document environment group
 * of the form map. If N-up partitioning is specified by the Medium Modification Control structured
 * field in the active medium map, the medium presentation spaces on the front and back sides of a
 * sheet are divided into N partitions; and the Page Position structured field specifies the
 * partition into which each page is mapped and with respect to which the page presentation space is
 * positioned and oriented. The N-up page-to-partition mapping can be specified in two mutually
 * exclusive ways: <ul> <li>Default N-up page placement. Pages are processed in the order in which
 * they appear in the data stream and are placed into consecutively-numbered partitions, that is,
 * the first page is placed into partition 1, the second page is placed into partition 2, the third
 * page is placed into partition 3, and the 4th page is placed into partition 4. Partition numbering
 * for various media is shown in Figure 58 on page 320 to Figure 69 on page 326. <li> Explicit N-up
 * page placement. Pages are processed in the order in which they appear in the data stream and are
 * placed into the partition that is explicitly specified by the repeating group for the page.
 * Multiple pages may be placed into the same partition. If N-up simplex is specified, the Page
 * Position structured field must contain N repeating groups, one for each page on the sheet-side.
 * If N-up duplex is specified, the Page Position structured field must contain 2N repeating groups,
 * one for each page on the sheet. </ul>
 */
public class PGP_PagePosition_Format2 extends StructuredFieldBaseRepeatingGroups {
  byte constant0;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    constant0 = sfData[offset];
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 1;
    while (pos < actualLength) {
      PGP_RepeatingGroup rg = new PGP_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      addRepeatingGroup(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(constant0);
    for (IRepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public byte getConstant0() {
    return constant0;
  }

  public void setConstant0(byte constant0) {
    this.constant0 = constant0;
  }

  public static class PGP_RepeatingGroup extends RepeatingGroupBase {

    int xOrigin;
    int yOrigin;
    AFPOrientation xRotation;
    PGP_SheetSideAndPartitionSelection sheetSideAndPartitionSelection;
    EnumSet<PGP_RGFlag> flags;
    byte pageModififationControlID;


    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config); // Decodes length of RG.
      xOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 1, 3);
      yOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 4, 3);
      xRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 7, 2));
      sheetSideAndPartitionSelection = PGP_SheetSideAndPartitionSelection.valueOf(sfData[offset + 9]);
      flags = PGP_RGFlag.valueOf(sfData[offset + 10]);
      pageModififationControlID = sfData[offset + 11];
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      super.writeAFP(os, config);
      os.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
      os.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
      os.write(xRotation.toBytes());
      os.write(sheetSideAndPartitionSelection.toByte());
      os.write(PGP_RGFlag.toByte(flags));
      os.write(pageModififationControlID);
    }

    public int getxOrigin() {
      return xOrigin;
    }

    public void setxOrigin(int xOrigin) {
      this.xOrigin = xOrigin;
    }

    public int getyOrigin() {
      return yOrigin;
    }

    public void setyOrigin(int yOrigin) {
      this.yOrigin = yOrigin;
    }

    public AFPOrientation getxRotation() {
      return xRotation;
    }

    public void setxRotation(AFPOrientation xRotation) {
      this.xRotation = xRotation;
    }

    public PGP_SheetSideAndPartitionSelection getSheetSideAndPartitionSelection() {
      return sheetSideAndPartitionSelection;
    }

    public void setSheetSideAndPartitionSelection(
            PGP_SheetSideAndPartitionSelection sheetSideAndPartitionSelection) {
      this.sheetSideAndPartitionSelection = sheetSideAndPartitionSelection;
    }

    public EnumSet<PGP_RGFlag> getFlags() {
      return flags;
    }

    public void setFlags(EnumSet<PGP_RGFlag> flags) {
      this.flags = flags;
    }

    public byte getPageModififationControlID() {
      return pageModififationControlID;
    }

    public void setPageModififationControlID(byte pageModififationControlID) {
      this.pageModififationControlID = pageModififationControlID;
    }

    public static enum PGP_SheetSideAndPartitionSelection {
      PageFrontSideIfNoNUp_DefaultFrontSideIfNUp(0x00),
      PageBackSideIfNoNUp_DefaultBackSideIfNup(0x01),
      Partition1_FrontSide(0x10),
      Partition1_BackSide(0x11),
      Partition2_FrontSide(0x20),
      Partition2_BackSide(0x21),
      Partition3_FrontSide(0x30),
      Partition3_BackSide(0x31),
      Partition4_FrontSide(0x40),
      Partition4_BackSide(0x41);
      int code;

      PGP_SheetSideAndPartitionSelection(int code) {
        this.code = code;
      }

      public static PGP_SheetSideAndPartitionSelection valueOf(byte codeByte) {
        for (PGP_SheetSideAndPartitionSelection ssps : values())
          if (ssps.code == codeByte) return ssps;
        return null;
      }

      public int toByte() {
        return code;
      }
    }

    public static enum PGP_RGFlag implements IMutualExclusiveGroupedFlag {
      VariablPageData_PresentVPDInPartiton(0),
      VariablPageData_DoNotPresentVPDInPartiton(0),
      PCMOverlay_PresentPCMOverlaysInPartition(1),
      PCMOverlay_DoNotPresentPCMOverlaysInPartition(1),
      PCMOverlayPosition_PageOrigin(2),
      PCMOverlayPosition_PartitionOrigin(2),
      PageViewControl_IntendedForViewing(3),
      PageViewControl_NotIntendedForViewing(3);
      static final MutualExclusiveGroupedFlagHandler<PGP_RGFlag> handler = new MutualExclusiveGroupedFlagHandler<PGP_RGFlag>();
      int group;

      PGP_RGFlag(int group) {
        this.group = group;
      }

      public static void setFlag(EnumSet<PGP_RGFlag> flags, PGP_RGFlag flag) {
        handler.setFlag(flags, flag);
      }

      public static EnumSet<PGP_RGFlag> valueOf(byte codeByte) {
        EnumSet<PGP_RGFlag> result = EnumSet.noneOf(PGP_RGFlag.class);
        if ((codeByte & 0x80) == 0) result.add(VariablPageData_PresentVPDInPartiton);
        else result.add(VariablPageData_DoNotPresentVPDInPartiton);
        if ((codeByte & 0x40) == 0) result.add(PCMOverlay_PresentPCMOverlaysInPartition);
        else result.add(PCMOverlay_DoNotPresentPCMOverlaysInPartition);
        if ((codeByte & 0x20) == 0) result.add(PCMOverlayPosition_PageOrigin);
        else result.add(PCMOverlayPosition_PartitionOrigin);
        if ((codeByte & 0x10) == 0) result.add(PageViewControl_IntendedForViewing);
        else result.add(PageViewControl_NotIntendedForViewing);
        return result;
      }

      public static int toByte(EnumSet<PGP_RGFlag> flags) {
        int result = 0;

        if (flags.contains(VariablPageData_DoNotPresentVPDInPartiton)) result |= 0x80;
        if (flags.contains(PCMOverlay_DoNotPresentPCMOverlaysInPartition)) result |= 0x40;
        if (flags.contains(PCMOverlayPosition_PartitionOrigin)) result |= 0x20;
        if (flags.contains(PageViewControl_NotIntendedForViewing)) result |= 0x10;

        return result;
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }
}
