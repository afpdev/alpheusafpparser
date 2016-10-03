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
package com.mgz.afp.enums;

import java.io.IOException;
import java.io.InputStream;

public enum SFTypeID {
  Undefined(0, 0, 0),
  /**
   * The Begin Active Environment Group structured field begins an Active Environment Group, which
   * establishes the environment parameters for the page or overlay. The scope of the active
   * environment group is the containing page or overlay.
   */
  BAG_BeginActiveEnvironmentGroup(0xD3, 0xA8, 0xC9),
  BBC_BeginBarCodeObject(0xD3, 0xA8, 0xEB),
  BCA_BeginColorAttributeTable(0xD3, 0xA8, 0x77),
  BCF_BeginCodedFont(0xD3, 0xA8, 0x8A), // FOCA page 128
  BCP_BeginCodePage(0xD3, 0xA8, 0x87), // FOCA page 129
  BDA_BarCodeData(0xD3, 0xEE, 0xEB),
  BDD_BarCodeDataDescriptor(0xD3, 0xA6, 0xEB),
  BDG_BeginDocumentEnvironmentGroup(0xD3, 0xA8, 0xC4),
  BDI_BeginDocumentIndex(0xD3, 0xA8, 0xA7),
  BDM_BeginDataMap(0xD3, 0xA8, 0xCA),
  BDT_BeginDocument(0xD3, 0xA8, 0xA8),
  BDX_BeginDataMapTransmitionSubcase(0xD3, 0xA8, 0xE3),
  BFG_BeginFormEnvironmentGroup(0xD3, 0xA8, 0xC5),
  BFM_BeginFormMap(0xD3, 0xA8, 0xCD),
  BFN_BeginFont(0xD3, 0xA8, 0x89), // FOCA page 131
  BGR_BeginGraphicsObject(0xD3, 0xA8, 0xBB),
  BII_BeginIMImageObject(0xD3, 0xA8, 0x7B),
  BIM_BeginImageObject(0xD3, 0xA8, 0xFB),
  BMM_BeginMediumMap(0xD3, 0xA8, 0xCC),
  BMO_BeginOverlay(0xD3, 0xA8, 0xDF),
  BNG_BeginNamedPageGroup(0xD3, 0xA8, 0xAD),
  BOC_BeginObjectContainer(0xD3, 0xA8, 0x92),
  BOG_BeginObjectEnvironmentGroup(0xD3, 0xA8, 0xC7),
  BPF_BeginPrintFile(0xD3, 0xA8, 0xA5),
  BPG_BeginPage(0xD3, 0xA8, 0xAF),
  BPM_BeginPageMap(0xD3, 0xA8, 0xCB),
  BPS_BeginPageSegment(0xD3, 0xA8, 0x5F),
  BPT_BeginPresentationTextObject(0xD3, 0xA8, 0x9B),
  BRG_BeginResourceGroup(0xD3, 0xA8, 0xC6),
  BRS_BeginResource(0xD3, 0xA8, 0xCE),
  BSG_BeginResourceEnvironmentGroup(0xD3, 0xA8, 0xD9),
  CAT_ColorAttributeTable(0xD3, 0xB0, 0x77),
  CCP_ConditionalProcessingControl(0xD3, 0xA7, 0xCA),
  CDD_ContainerDataDescriptor(0xD3, 0xA6, 0x92),
  CFC_CodedFontControl(0xD3, 0xA7, 0x8A), // FOCA page 133
  CFI_CodedFontIndex(0xD3, 0x8C, 0x8A), // FOCA page 134
  CPC_CodePageControl(0xD3, 0xA7, 0x87),    // FOCA page 137
  CPD_CodePageDescriptor(0xD3, 0xA6, 0x87),// FOCA page 141
  CPI_CodePageIndex(0xD3, 0x8C, 0x87), // FOCA page 143
  CTC_ComposedTextControl(0xD3, 0xA7, 0x9B),
  DXD_DataMapTransmitionSubcaseDescriptor(0xD3, 0xA6, 0xE3),
  EAG_EndActiveEnvironmentGroup(0xD3, 0xA9, 0xC9),
  EBC_EndBarCodeObject(0xD3, 0xA9, 0xEB),
  ECA_EndColorAttributeTable(0xD3, 0xA9, 0x77),
  ECF_EndCodedFont(0xD3, 0xA9, 0x8A), // FOCA page 147
  ECP_EndCodePage(0xD3, 0xA9, 0x87), // FOCA page 148
  EDG_EndDocumentEnvironmentGroup(0xD3, 0xA9, 0xC4),
  EDI_EndDocumentIndex(0xD3, 0xA9, 0xA7),
  EDM_EndDataMap(0xD3, 0xA9, 0xCA),
  EDT_EndDocument(0xD3, 0xA9, 0xA8),
  EFG_EndFormEnvironmentGroup(0xD3, 0xA9, 0xC5),
  EFM_EndFormMap(0xD3, 0xA9, 0xCD),
  EFN_EndFont(0xD3, 0xA9, 0x89), // FOCA page 149
  EGR_EndGraphicsObject(0xD3, 0xA9, 0xBB),
  EDX_EndDataMapTransmitionSubcase(0xD3, 0xA9, 0xE3),
  EII_EndIMImageObject(0xD3, 0xA9, 0x7B),
  EIM_EndImageObject(0xD3, 0xA9, 0xFB),
  EMM_EndMediumMap(0xD3, 0xA9, 0xCC),
  EMO_EndOverlay(0xD3, 0xA9, 0xDF),
  ENG_EndNamedPageGroup(0xD3, 0xA9, 0xAD),
  EOC_EndObjectContainer(0xD3, 0xA9, 0x92),
  EOG_EndObjectEnvironmentGroup(0xD3, 0xA9, 0xC7),
  EPF_EndPrintFile(0xD3, 0xA9, 0xA5),
  EPG_EndPage(0xD3, 0xA9, 0xAF),
  EPM_EndPageMap(0xD3, 0xA9, 0xCB),
  EPS_EndPageSegment(0xD3, 0xA9, 0x5F),
  EPT_EndPresentationTextObject(0xD3, 0xA9, 0x9B),
  ERG_EndResourceGroup(0xD3, 0xA9, 0xC6),
  ERS_EndResource(0xD3, 0xA9, 0xCE),
  ESG_EndResourceEnvironmentGroup(0xD3, 0xA9, 0xD9),
  FDS_FixedDataSize(0xD3, 0xAA, 0xEC),
  FDX_FixedDataText(0xD3, 0xEE, 0xEC),
  FGD_FormEnvironmentGroupDescriptor(0xD3, 0xA6, 0xC5),
  FNC_FontControl(0xD3, 0xA7, 0x89), // FOCA page 150
  FND_FontDescriptor(0xD3, 0xA6, 0x89),    // FOCA page 157
  FNG_FontPatterns(0xD3, 0xEE, 0x89), // FOCA page 162
  FNI_FontIndex(0xD3, 0x8C, 0x89), // FOCA page 166
  FNM_FontPatternsMap(0xD3, 0xA2, 0x89), // FOCA page 171
  FNN_FontNameMap(0xD3, 0xAB, 0x89), // FOCA page 173
  FNO_FontOrientation(0xD3, 0xAE, 0x89), // FOCA page 177
  FNP_FontPosition(0xD3, 0xAC, 0x89), // FOCA page 182
  GAD_GraphicsData(0xD3, 0xEE, 0xBB),
  GDD_GraphicsDataDescriptor(0xD3, 0xA6, 0xBB),
  ICP_IMImageCellPosition(0xD3, 0xAC, 0x7B),
  IDD_ImageDataDescriptor(0xD3, 0xA6, 0xFB),
  IDM_InvokeDataMap(0xD3, 0xAB, 0xCA),
  IEL_IndexElement(0xD3, 0xB2, 0xA7),
  IID_IMImageInputDescriptor(0xD3, 0xA6, 0x7B),
  IMM_InvokeMediumMap(0xD3, 0xAB, 0xCC),
  IOB_IncludeObject(0xD3, 0xAF, 0xC3),
  IOC_IMImageOutputControl(0xD3, 0xA7, 0x7B),
  IPD_ImagePictureData(0xD3, 0xEE, 0xFB),
  IPG_IncludePage(0xD3, 0xAF, 0xAF),
  IPO_IncludePageOverlay(0xD3, 0xAF, 0xD8),
  IPS_IncludePageSegment(0xD3, 0xAF, 0x5F),
  IRD_IMImageRasterData(0xD3, 0xEE, 0x7B),
  LLE_LinkLogicalElement(0xD3, 0xB4, 0x90),
  LNC_LineDescriptorCount(0xD3, 0xAA, 0xE7),
  LND_LineDescriptor(0xD3, 0xA6, 0xE7),
  MBC_MapBarCodeObject(0xD3, 0xAB, 0xEB),
  MCA_MapColorAttribteTable(0xD3, 0xAB, 0x77),
  MCC_MediumCopyCount(0xD3, 0xA2, 0x88),
  MCD_MapContainerData(0xD3, 0xAB, 0x92),
  MCF_MapCodedFont_Format1(0xD3, 0xB1, 0x8A),
  MCF_MapCodedFont_Format2(0xD3, 0xAB, 0x8A),
  MDD_MediumDescriptor(0xD3, 0xA6, 0x88),
  MDR_MapDataResource(0xD3, 0xAB, 0xC3),
  MFC_MediumFinishingControl(0xD3, 0xA0, 0x88),
  MGO_MapGraphicsObject(0xD3, 0xAB, 0xBB),
  MIO_MapImageObject(0xD3, 0xAB, 0xFB),
  MMC_MediumModificationControl(0xD3, 0xA7, 0x88),
  MMD_MapMediaDestination(0xD3, 0xAB, 0xCD),
  MMO_MapMediumOverlay(0xD3, 0xB1, 0xDF),
  MMT_MapMediaType(0xD3, 0xAB, 0x88),
  MPG_MapPage(0xD3, 0xAB, 0xAF),
  MPO_MapPageOverlay(0xD3, 0xAB, 0xD8),
  MPS_MapPageSegment(0xD3, 0xB1, 0x5F),
  MSU_MapSuppression(0xD3, 0xAB, 0xEA),
  NOP_NoOperation(0xD3, 0xEE, 0xEE),
  OBD_ObjectAreaDescriptor(0xD3, 0xA6, 0x6B),
  OBP_ObjectAreaPosition(0xD3, 0xAC, 0x6B),
  OCD_ObjectContainerData(0xD3, 0xEE, 0x92),
  PEC_PresentationEnvironmentControl(0xD3, 0xA7, 0xA8),
  PFC_PresentationFidelityControl(0xD3, 0xB2, 0x88),
  PGD_PageDescriptor(0xD3, 0xA6, 0xAF),
  PGP_PagePosition_Format1(0xD3, 0xAC, 0xAF),
  PGP_PagePosition_Format2(0xD3, 0xB1, 0xAF),
  PMC_PageModificationControl(0xD3, 0xA7, 0xAF),
  PPO_PreprocessPresentationObject(0xD3, 0xAD, 0xC3),
  PTD_PresentationTextDataDescriptor_Format1(0xD3, 0xA6, 0x9B),
  PTD_PresentationTextDataDescriptor_Format2(0xD3, 0xB1, 0x9B),
  PTX_PresentationTextData(0xD3, 0xEE, 0x9B),
  RCD_RecordDescriptor(0xD3, 0xA6, 0x8D),
  TLE_TagLogicalElement(0xD3, 0xA0, 0x90),
  XMD_XMLDescriptor(0xD3, 0xA6, 0x8E),;


  /**
   * SFTypeID[2].
   */
  SFClass sfClass;
  /**
   * SFTypeID[3].
   */
  SFType sfType;
  /**
   * SFTypeID[4].
   */
  SFCategory sfCategory;

  SFTypeID(int sfClass, int sfType, int sfCategory) {
    this.sfClass = SFClass.valueOf(sfClass);
    this.sfType = SFType.valueOf(sfType);
    this.sfCategory = SFCategory.valueOf(sfCategory);
  }

  public static SFTypeID parse(InputStream is) throws IOException {
    int sfClass = is.read();
    int sfType = is.read();
    int sfCategory = is.read();

    for (SFTypeID sfTypeID : SFTypeID.values()) {
      if (sfTypeID.sfClass.val == sfClass
          && sfTypeID.sfType.val == sfType
          && sfTypeID.sfCategory.val == sfCategory) {
        return sfTypeID;
      }
    }

    return Undefined;
  }

  public byte[] toBytes() {
    byte[] data = new byte[3];
    data[0] = (byte) sfClass.val;
    data[1] = (byte) sfType.val;
    data[2] = (byte) sfCategory.val;
    return data;
  }

  public SFClass getSfClass() {
    return sfClass;
  }

  public void setSfClass(SFClass sfClass) {
    this.sfClass = sfClass;
  }

  public SFType getSfType() {
    return sfType;
  }

  public void setSfType(SFType sfType) {
    this.sfType = sfType;
  }

  public SFCategory getSfCategory() {
    return sfCategory;
  }

  public void setSfCategory(SFCategory sfCategory) {
    this.sfCategory = sfCategory;
  }
}
