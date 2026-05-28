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

package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.StructuredFieldBaseData;
import com.mgz.afp.base.StructuredFieldBaseDataPool;
import com.mgz.afp.base.StructuredFieldErrornouslyBuilt;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.base.Undefined;
import com.mgz.afp.bcoca.BDD_BarCodeDataDescriptor;
import com.mgz.afp.enums.SFFlag;
import com.mgz.afp.enums.SFTypeID;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.IHasTriplets;
import com.mgz.afp.foca.CPC_CodePageControl;
import com.mgz.afp.foca.CPD_CodePageDescriptor;
import com.mgz.afp.foca.FNC_FontControl;
import com.mgz.afp.modca.MCF_MapCodedFont_Format1;
import com.mgz.afp.modca.MCF_MapCodedFont_Format2;
import com.mgz.afp.modca.MDR_MapDataResource;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.MnemonicPerformanceMonitor;
import com.mgz.util.UtilCharacterEncoding;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * The AFPParser is the main class for parsing AFP data streams.
 * It reads structured fields from an input stream and builds corresponding
 * Java objects.
 */
public class AFPParser {

  private static final EnumMap<SFTypeID, Supplier<StructuredField>> SF_SUPPLIERS =
      new EnumMap<>(SFTypeID.class);

  static {
    SF_SUPPLIERS.put(SFTypeID.BAG_BeginActiveEnvironmentGroup, com.mgz.afp.modca.BAG_BeginActiveEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BBC_BeginBarCodeObject, com.mgz.afp.bcoca.BBC_BeginBarCodeObject::new);
    SF_SUPPLIERS.put(SFTypeID.BCA_BeginColorAttributeTable, com.mgz.afp.modca_L.BCA_BeginColorAttributeTable::new);
    SF_SUPPLIERS.put(SFTypeID.BCF_BeginCodedFont, com.mgz.afp.foca.BCF_BeginCodedFont::new);
    SF_SUPPLIERS.put(SFTypeID.BCP_BeginCodePage, com.mgz.afp.foca.BCP_BeginCodePage::new);
    SF_SUPPLIERS.put(SFTypeID.BDA_BarCodeData, com.mgz.afp.bcoca.BDA_BarCodeData::new);
    SF_SUPPLIERS.put(SFTypeID.BDD_BarCodeDataDescriptor, com.mgz.afp.bcoca.BDD_BarCodeDataDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.BDG_BeginDocumentEnvironmentGroup, com.mgz.afp.modca.BDG_BeginDocumentEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BDI_BeginDocumentIndex, com.mgz.afp.modca.BDI_BeginDocumentIndex::new);
    SF_SUPPLIERS.put(SFTypeID.BDM_BeginDataMap, com.mgz.afp.lineData.BDM_BeginDataMap::new);
    SF_SUPPLIERS.put(SFTypeID.BDT_BeginDocument, com.mgz.afp.modca.BDT_BeginDocument::new);
    SF_SUPPLIERS.put(SFTypeID.BDX_BeginDataMapTransmitionSubcase, com.mgz.afp.lineData.BDX_BeginDataMapTransmitionSubcase::new);
    SF_SUPPLIERS.put(SFTypeID.BFG_BeginFormEnvironmentGroup, com.mgz.afp.modca.BFG_BeginFormEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BFM_BeginFormMap, com.mgz.afp.modca.BFM_BeginFormMap::new);
    SF_SUPPLIERS.put(SFTypeID.BFN_BeginFont, com.mgz.afp.foca.BFN_BeginFont::new);
    SF_SUPPLIERS.put(SFTypeID.BGR_BeginGraphicsObject, com.mgz.afp.goca.BGR_BeginGraphicsObject::new);
    SF_SUPPLIERS.put(SFTypeID.BII_BeginIMImageObject, com.mgz.afp.modca.BII_BeginIMImageObject::new);
    SF_SUPPLIERS.put(SFTypeID.BIM_BeginImageObject, com.mgz.afp.modca.BIM_BeginImageObject::new);
    SF_SUPPLIERS.put(SFTypeID.BMM_BeginMediumMap, com.mgz.afp.modca.BMM_BeginMediumMap::new);
    SF_SUPPLIERS.put(SFTypeID.BMO_BeginOverlay, com.mgz.afp.modca.BMO_BeginOverlay::new);
    SF_SUPPLIERS.put(SFTypeID.BNG_BeginNamedPageGroup, com.mgz.afp.modca.BNG_BeginNamedPageGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BOC_BeginObjectContainer, com.mgz.afp.modca.BOC_BeginObjectContainer::new);
    SF_SUPPLIERS.put(SFTypeID.BOG_BeginObjectEnvironmentGroup, com.mgz.afp.modca.BOG_BeginObjectEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BPF_BeginPrintFile, com.mgz.afp.modca.BPF_BeginPrintFile::new);
    SF_SUPPLIERS.put(SFTypeID.BPG_BeginPage, com.mgz.afp.modca.BPG_BeginPage::new);
    SF_SUPPLIERS.put(SFTypeID.BPM_BeginPageMap, com.mgz.afp.lineData.BPM_BeginPageMap::new);
    SF_SUPPLIERS.put(SFTypeID.BPS_BeginPageSegment, com.mgz.afp.modca.BPS_BeginPageSegment::new);
    SF_SUPPLIERS.put(SFTypeID.BPT_BeginPresentationTextObject, com.mgz.afp.ptoca.BPT_BeginPresentationTextObject::new);
    SF_SUPPLIERS.put(SFTypeID.BRG_BeginResourceGroup, com.mgz.afp.modca.BRG_BeginResourceGroup::new);
    SF_SUPPLIERS.put(SFTypeID.BRS_BeginResource, com.mgz.afp.modca.BRS_BeginResource::new);
    SF_SUPPLIERS.put(SFTypeID.BSG_BeginResourceEnvironmentGroup, com.mgz.afp.modca.BSG_BeginResourceEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.CAT_ColorAttributeTable, com.mgz.afp.modca_L.CAT_ColorAttributeTable::new);
    SF_SUPPLIERS.put(SFTypeID.CCP_ConditionalProcessingControl, com.mgz.afp.lineData.CCP_ConditionalProcessingControl::new);
    SF_SUPPLIERS.put(SFTypeID.CDD_ContainerDataDescriptor, com.mgz.afp.modca.CDD_ContainerDataDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.CMR_ColorManagementResource, com.mgz.afp.cmoca.CMR_ColorManagementResource::new);
    SF_SUPPLIERS.put(SFTypeID.CFC_CodedFontControl, com.mgz.afp.foca.CFC_CodedFontControl::new);
    SF_SUPPLIERS.put(SFTypeID.CFI_CodedFontIndex, com.mgz.afp.foca.CFI_CodedFontIndex::new);
    SF_SUPPLIERS.put(SFTypeID.CPC_CodePageControl, com.mgz.afp.foca.CPC_CodePageControl::new);
    SF_SUPPLIERS.put(SFTypeID.CPD_CodePageDescriptor, com.mgz.afp.foca.CPD_CodePageDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.CPI_CodePageIndex, com.mgz.afp.foca.CPI_CodePageIndex::new);
    SF_SUPPLIERS.put(SFTypeID.CTC_ComposedTextControl, com.mgz.afp.modca.CTC_ComposedTextControl::new);
    SF_SUPPLIERS.put(SFTypeID.DXD_DataMapTransmitionSubcaseDescriptor, com.mgz.afp.lineData.DXD_DataMapTransmitionSubcaseDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.EAG_EndActiveEnvironmentGroup, com.mgz.afp.modca.EAG_EndActiveEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.EBC_EndBarCodeObject, com.mgz.afp.bcoca.EBC_EndBarCodeObject::new);
    SF_SUPPLIERS.put(SFTypeID.ECA_EndColorAttributeTable, com.mgz.afp.modca_L.ECA_EndColorAttributeTable::new);
    SF_SUPPLIERS.put(SFTypeID.ECF_EndCodedFont, com.mgz.afp.foca.ECF_EndCodedFont::new);
    SF_SUPPLIERS.put(SFTypeID.ECP_EndCodePage, com.mgz.afp.foca.ECP_EndCodePage::new);
    SF_SUPPLIERS.put(SFTypeID.EDG_EndDocumentEnvironmentGroup, com.mgz.afp.modca.EDG_EndDocumentEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.EDI_EndDocumentIndex, com.mgz.afp.modca.EDI_EndDocumentIndex::new);
    SF_SUPPLIERS.put(SFTypeID.EDM_EndDataMap, com.mgz.afp.lineData.EDM_EndDataMap::new);
    SF_SUPPLIERS.put(SFTypeID.EDT_EndDocument, com.mgz.afp.modca.EDT_EndDocument::new);
    SF_SUPPLIERS.put(SFTypeID.EDX_EndDataMapTransmitionSubcase, com.mgz.afp.lineData.EDX_EndDataMapTransmitionSubcase::new);
    SF_SUPPLIERS.put(SFTypeID.EFG_EndFormEnvironmentGroup, com.mgz.afp.modca.EFG_EndFormEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.EFM_EndFormMap, com.mgz.afp.modca.EFM_EndFormMap::new);
    SF_SUPPLIERS.put(SFTypeID.EFN_EndFont, com.mgz.afp.foca.EFN_EndFont::new);
    SF_SUPPLIERS.put(SFTypeID.EGR_EndGraphicsObject, com.mgz.afp.goca.EGR_EndGraphicsObject::new);
    SF_SUPPLIERS.put(SFTypeID.EII_EndIMImageObject, com.mgz.afp.modca.EII_EndIMImageObject::new);
    SF_SUPPLIERS.put(SFTypeID.EIM_EndImageObject, com.mgz.afp.modca.EIM_EndImageObject::new);
    SF_SUPPLIERS.put(SFTypeID.EMM_EndMediumMap, com.mgz.afp.modca.EMM_EndMediumMap::new);
    SF_SUPPLIERS.put(SFTypeID.EMO_EndOverlay, com.mgz.afp.modca.EMO_EndOverlay::new);
    SF_SUPPLIERS.put(SFTypeID.ENG_EndNamedPageGroup, com.mgz.afp.modca.ENG_EndNamedPageGroup::new);
    SF_SUPPLIERS.put(SFTypeID.EOC_EndObjectContainer, com.mgz.afp.modca.EOC_EndObjectContainer::new);
    SF_SUPPLIERS.put(SFTypeID.EOG_EndObjectEnvironmentGroup, com.mgz.afp.modca.EOG_EndObjectEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.EPF_EndPrintFile, com.mgz.afp.modca.EPF_EndPrintFile::new);
    SF_SUPPLIERS.put(SFTypeID.EPG_EndPage, com.mgz.afp.modca.EPG_EndPage::new);
    SF_SUPPLIERS.put(SFTypeID.EPM_EndPageMap, com.mgz.afp.lineData.EPM_EndPageMap::new);
    SF_SUPPLIERS.put(SFTypeID.EPS_EndPageSegment, com.mgz.afp.modca.EPS_EndPageSegment::new);
    SF_SUPPLIERS.put(SFTypeID.EPT_EndPresentationTextObject, com.mgz.afp.ptoca.EPT_EndPresentationTextObject::new);
    SF_SUPPLIERS.put(SFTypeID.ERG_EndResourceGroup, com.mgz.afp.modca.ERG_EndResourceGroup::new);
    SF_SUPPLIERS.put(SFTypeID.ERS_EndResource, com.mgz.afp.modca.ERS_EndResource::new);
    SF_SUPPLIERS.put(SFTypeID.ESG_EndResourceEnvironmentGroup, com.mgz.afp.modca.ESG_EndResourceEnvironmentGroup::new);
    SF_SUPPLIERS.put(SFTypeID.FDS_FixedDataSize, com.mgz.afp.lineData.FDS_FixedDataSize::new);
    SF_SUPPLIERS.put(SFTypeID.FDX_FixedDataText, com.mgz.afp.lineData.FDX_FixedDataText::new);
    SF_SUPPLIERS.put(SFTypeID.FGD_FormEnvironmentGroupDescriptor, com.mgz.afp.modca.FGD_FormEnvironmentGroupDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.FNC_FontControl, com.mgz.afp.foca.FNC_FontControl::new);
    SF_SUPPLIERS.put(SFTypeID.FND_FontDescriptor, com.mgz.afp.foca.FND_FontDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.FNG_FontPatterns, com.mgz.afp.foca.FNG_FontPatterns::new);
    SF_SUPPLIERS.put(SFTypeID.FNI_FontIndex, com.mgz.afp.foca.FNI_FontIndex::new);
    SF_SUPPLIERS.put(SFTypeID.FNM_FontPatternsMap, com.mgz.afp.foca.FNM_FontPatternsMap::new);
    SF_SUPPLIERS.put(SFTypeID.FNN_FontNameMap, com.mgz.afp.foca.FNN_FontNameMap::new);
    SF_SUPPLIERS.put(SFTypeID.FNO_FontOrientation, com.mgz.afp.foca.FNO_FontOrientation::new);
    SF_SUPPLIERS.put(SFTypeID.FNP_FontPosition, com.mgz.afp.foca.FNP_FontPosition::new);
    SF_SUPPLIERS.put(SFTypeID.GAD_GraphicsData, com.mgz.afp.goca.GAD_GraphicsData::new);
    SF_SUPPLIERS.put(SFTypeID.GDD_GraphicsDataDescriptor, com.mgz.afp.goca.GDD_GraphicsDataDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.ICP_IMImageCellPosition, com.mgz.afp.modca.ICP_IMImageCellPosition::new);
    SF_SUPPLIERS.put(SFTypeID.IDD_ImageDataDescriptor, com.mgz.afp.ioca.IDD_ImageDataDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.IDM_InvokeDataMap, com.mgz.afp.lineData.IDM_InvokeDataMap::new);
    SF_SUPPLIERS.put(SFTypeID.IEL_IndexElement, com.mgz.afp.modca.IEL_IndexElement::new);
    SF_SUPPLIERS.put(SFTypeID.IID_IMImageInputDescriptor, com.mgz.afp.modca.IID_IMImageInputDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.IMM_InvokeMediumMap, com.mgz.afp.modca.IMM_InvokeMediumMap::new);
    SF_SUPPLIERS.put(SFTypeID.IOB_IncludeObject, com.mgz.afp.modca.IOB_IncludeObject::new);
    SF_SUPPLIERS.put(SFTypeID.IOC_IMImageOutputControl, com.mgz.afp.modca.IOC_IMImageOutputControl::new);
    SF_SUPPLIERS.put(SFTypeID.IPD_ImagePictureData, com.mgz.afp.ioca.IPD_ImagePictureData::new);
    SF_SUPPLIERS.put(SFTypeID.IPG_IncludePage, com.mgz.afp.modca.IPG_IncludePage::new);
    SF_SUPPLIERS.put(SFTypeID.IPO_IncludePageOverlay, com.mgz.afp.modca.IPO_IncludePageOverlay::new);
    SF_SUPPLIERS.put(SFTypeID.IPS_IncludePageSegment, com.mgz.afp.modca.IPS_IncludePageSegment::new);
    SF_SUPPLIERS.put(SFTypeID.IRD_IMImageRasterData, com.mgz.afp.modca.IRD_IMImageRasterData::new);
    SF_SUPPLIERS.put(SFTypeID.LLE_LinkLogicalElement, com.mgz.afp.modca.LLE_LinkLogicalElement::new);
    SF_SUPPLIERS.put(SFTypeID.LNC_LineDescriptorCount, com.mgz.afp.lineData.LNC_LineDescriptorCount::new);
    SF_SUPPLIERS.put(SFTypeID.LND_LineDescriptor, com.mgz.afp.lineData.LND_LineDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.MBC_MapBarCodeObject, com.mgz.afp.modca.MBC_MapBarCodeObject::new);
    SF_SUPPLIERS.put(SFTypeID.MCA_MapColorAttributeTable, com.mgz.afp.modca_L.MCA_MapColorAttributeTable::new);
    SF_SUPPLIERS.put(SFTypeID.MCC_MediumCopyCount, com.mgz.afp.modca.MCC_MediumCopyCount::new);
    SF_SUPPLIERS.put(SFTypeID.MCD_MapContainerData, com.mgz.afp.modca.MCD_MapContainerData::new);
    SF_SUPPLIERS.put(SFTypeID.MCF_MapCodedFont_Format1, com.mgz.afp.modca.MCF_MapCodedFont_Format1::new);
    SF_SUPPLIERS.put(SFTypeID.MCF_MapCodedFont_Format2, com.mgz.afp.modca.MCF_MapCodedFont_Format2::new);
    SF_SUPPLIERS.put(SFTypeID.MDD_MediumDescriptor, com.mgz.afp.modca.MDD_MediumDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.MDR_MapDataResource, com.mgz.afp.modca.MDR_MapDataResource::new);
    SF_SUPPLIERS.put(SFTypeID.MFC_MediumFinishingControl, com.mgz.afp.modca.MFC_MediumFinishingControl::new);
    SF_SUPPLIERS.put(SFTypeID.MGO_MapGraphicsObject, com.mgz.afp.modca.MGO_MapGraphicsObject::new);
    SF_SUPPLIERS.put(SFTypeID.MIO_MapImageObject, com.mgz.afp.modca.MIO_MapImageObject::new);
    SF_SUPPLIERS.put(SFTypeID.MMC_MediumModificationControl, com.mgz.afp.modca.MMC_MediumModificationControl::new);
    SF_SUPPLIERS.put(SFTypeID.MMD_MapMediaDestination, com.mgz.afp.modca.MMD_MapMediaDestination::new);
    SF_SUPPLIERS.put(SFTypeID.MMO_MapMediumOverlay, com.mgz.afp.modca.MMO_MapMediumOverlay::new);
    SF_SUPPLIERS.put(SFTypeID.MMT_MapMediaType, com.mgz.afp.modca.MMT_MapMediaType::new);
    SF_SUPPLIERS.put(SFTypeID.MPG_MapPage, com.mgz.afp.modca.MPG_MapPage::new);
    SF_SUPPLIERS.put(SFTypeID.MPO_MapPageOverlay, com.mgz.afp.modca.MPO_MapPageOverlay::new);
    SF_SUPPLIERS.put(SFTypeID.MPS_MapPageSegment, com.mgz.afp.modca.MPS_MapPageSegment::new);
    SF_SUPPLIERS.put(SFTypeID.MPT_MapPresentationText, com.mgz.afp.modca.MPT_MapPresentationText::new);
    SF_SUPPLIERS.put(SFTypeID.MSU_MapSuppression, com.mgz.afp.modca.MSU_MapSuppression::new);
    SF_SUPPLIERS.put(SFTypeID.NOP_NoOperation, com.mgz.afp.modca.NOP_NoOperation::new);
    SF_SUPPLIERS.put(SFTypeID.OBD_ObjectAreaDescriptor, com.mgz.afp.modca.OBD_ObjectAreaDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.OBP_ObjectAreaPosition, com.mgz.afp.modca.OBP_ObjectAreaPosition::new);
    SF_SUPPLIERS.put(SFTypeID.OCD_ObjectContainerData, com.mgz.afp.modca.OCD_ObjectContainerData::new);
    SF_SUPPLIERS.put(SFTypeID.PEC_PresentationEnvironmentControl, com.mgz.afp.modca.PEC_PresentationEnvironmentControl::new);
    SF_SUPPLIERS.put(SFTypeID.PFC_PresentationFidelityControl, com.mgz.afp.modca.PFC_PresentationFidelityControl::new);
    SF_SUPPLIERS.put(SFTypeID.PGD_PageDescriptor, com.mgz.afp.modca.PGD_PageDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.PGP_PagePosition_Format1, com.mgz.afp.modca.PGP_PagePosition_Format1::new);
    SF_SUPPLIERS.put(SFTypeID.PGP_PagePosition_Format2, com.mgz.afp.modca.PGP_PagePosition_Format2::new);
    SF_SUPPLIERS.put(SFTypeID.PMC_PageModificationControl, com.mgz.afp.modca.PMC_PageModificationControl::new);
    SF_SUPPLIERS.put(SFTypeID.PPO_PreprocessPresentationObject, com.mgz.afp.modca.PPO_PreprocessPresentationObject::new);
    SF_SUPPLIERS.put(SFTypeID.PTD_PresentationTextDataDescriptor_Format1, com.mgz.afp.ptoca.PTD_PresentationTextDataDescriptor_Format1::new);
    SF_SUPPLIERS.put(SFTypeID.PTD_PresentationTextDataDescriptor_Format2, com.mgz.afp.ptoca.PTD_PresentationTextDataDescriptor_Format2::new);
    SF_SUPPLIERS.put(SFTypeID.PTX_PresentationTextData, com.mgz.afp.ptoca.PTX_PresentationTextData::new);
    SF_SUPPLIERS.put(SFTypeID.RCD_RecordDescriptor, com.mgz.afp.lineData.RCD_RecordDescriptor::new);
    SF_SUPPLIERS.put(SFTypeID.TLE_TagLogicalElement, com.mgz.afp.modca.TLE_TagLogicalElement::new);
    SF_SUPPLIERS.put(SFTypeID.XMD_XMLDescriptor, com.mgz.afp.lineData.XMD_XMLDescriptor::new);
  }
  AFPParserConfiguration parserConf;
  long nrOfBytesRead;
  long nrOfSFBuilt;
  long nrOfErrSFBuilt;

  /**
   * Constructor.
   *
   * @param parserConfiguration see {@link AFPParserConfiguration}
   */
  public AFPParser(AFPParserConfiguration parserConfiguration) {
    nrOfBytesRead = 0;
    nrOfSFBuilt = 0;
    nrOfErrSFBuilt = 0;
    parserConf = parserConfiguration;
  }

  /**
   * Creates a new instance of a {@link StructuredField} based on the given introducer.
   *
   * @param sfi the structured field introducer
   * @return a new instance of the corresponding structured field, or an Undefined SF if not found
   */
  public static StructuredField createSFInstance(StructuredFieldIntroducer sfi) {
    StructuredField sf = com.mgz.afp.base.StructuredFieldPool.acquire(sfi.getSFTypeID());
    if (sf == null) {
      sf = SF_SUPPLIERS.getOrDefault(sfi.getSFTypeID(), Undefined::new).get();
    }
    sf.setStructuredFieldIntroducer(sfi);
    return sf;
  }

  /**
   * Reloads the payload of a structured field from the input stream.
   *
   * @param sf the structured field to reload
   * @throws AFPParserException if reloading fails or the source file is unknown
   */
  public static void reload(StructuredField sf) throws AFPParserException {
    if (sf == null || sf.getStructuredFieldIntroducer() == null) {
      return;
    }

    StructuredFieldIntroducer sfi = sf.getStructuredFieldIntroducer();
    AFPParserConfiguration conf = sfi.getActualConfig();
    if (conf.getAFPFile() == null) {
      throw new AFPParserException("The file from whitch the structured field has been loaded is unknown.");
    }

    synchronized (conf) {
      try {
        if (conf.getByteBuffer() != null) {
          reloadFromBuffer(sf, sfi, conf);
        } else {
          reloadFromStream(sf, sfi, conf);
        }
      } catch (Throwable th) {
        if (th instanceof AFPParserException) {
          throw (AFPParserException) th;
        } else {
          throw new AFPParserException("Reload failed.", th);
        }
      }
    }
  }

  private static void reloadFromBuffer(StructuredField sf, StructuredFieldIntroducer sfi, AFPParserConfiguration conf) throws AFPParserException, IOException {
    var buffer = conf.getByteBuffer();
    int payloadStart = (int) sfi.getFileOffset() + 1 + sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
    int lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();

    if (lenOfGrossPayload > 0) {
      byte[] sfData;
      byte[] padding;

      if (sfi.isFlagSet(SFFlag.isPadded)) {
        int lenOfPadding = buffer.get(payloadStart + lenOfGrossPayload - 1) & 0xFF;
        if (lenOfPadding == 0) {
          lenOfPadding = UtilBinaryDecoding.parseInt(buffer, payloadStart + lenOfGrossPayload - 3, 2);
        }
        int lenOfSFData = lenOfGrossPayload - lenOfPadding;

        sfData = new byte[lenOfSFData];
        padding = new byte[lenOfPadding];
        int oldPos = buffer.position();
        buffer.position(payloadStart);
        buffer.get(sfData);
        buffer.get(padding);
        buffer.position(oldPos);
      } else {
        sfData = new byte[lenOfGrossPayload];
        int oldPos = buffer.position();
        buffer.position(payloadStart);
        buffer.get(sfData);
        buffer.position(oldPos);
        padding = null;
      }

      sf.setPadding(padding);
      MnemonicPerformanceMonitor.startParse(sf);
      try {
        sf.decodeAFP(sfData, 0, -1, conf);
      } finally {
        MnemonicPerformanceMonitor.endParse();
      }
    }
  }

  private static void reloadFromStream(StructuredField sf, StructuredFieldIntroducer sfi, AFPParserConfiguration conf) throws AFPParserException {
    InputStream is = null;
    try {
      conf.setInputStream(null);
      is = conf.getInputStream();
      long lenSFI = sfi.getFileOffset() + 1 + sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
      if (is.skip(lenSFI) < lenSFI) {
        throw new AFPParserException("Failed to skip over SF Introducer.");
      }

      int lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
      byte[] grossPayload = new byte[lenOfGrossPayload];
      byte[] sfData;
      byte[] padding;

      // Determine payload.
      if (lenOfGrossPayload > 0) {

        int read = 0;
        while (read < lenOfGrossPayload) {
          int len = is.read(grossPayload, read, lenOfGrossPayload - read);
          if (len == -1) {
            throw new AFPParserException("Reached end of file before end of structured field.");
          } else {
            read += len;
          }
        }

        // Determine net payload.
        if (sfi.isFlagSet(SFFlag.isPadded)) {
          int lenOfPadding = grossPayload[grossPayload.length - 1];
          if (lenOfPadding == 0) {
            lenOfPadding = UtilBinaryDecoding.parseInt(grossPayload, grossPayload.length - 3, 2);
          }

          int lenOfSFData = lenOfGrossPayload - lenOfPadding;

          sfData = new byte[lenOfSFData];
          padding = new byte[lenOfPadding];

          System.arraycopy(grossPayload, 0, sfData, 0, lenOfSFData);
          System.arraycopy(grossPayload, lenOfSFData, padding, 0, lenOfPadding);

        } else {
          sfData = grossPayload;
          padding = null;
        }

        sf.setPadding(padding);
        sf.decodeAFP(sfData, 0, -1, conf);
      }

    } catch (IOException ioex) {
      throw new AFPParserException("Reload failed.", ioex);
    } finally {
      if (is != null) {
        try {
          is.close();
          conf.setInputStream(null);
        } catch (IOException e) {
          throw new AFPParserException("Failed to close input stream.", e);
        }
      }
    }
  }

  /**
   * Call this method to perform the parsing. Blocks until the parsing finished, either by end of
   * input stream or the occurrence of an {@link AFPParserException}.
   */
  public final StructuredField parseNextSF() throws AFPParserException {
    try {
      if (parserConf.getByteBuffer() != null) {
        return parseNextSFFromBuffer();
      } else {
        return parseNextSFFromStream();
      }
    } catch (IOException e) {
      throw new AFPParserException("Failed to access input for parsing.", e);
    }
  }

  private StructuredField parseNextSFFromBuffer() throws AFPParserException, IOException {
    StructuredFieldIntroducer sfi = null;
    StructuredFieldErrornouslyBuilt errSf = null;
    var buffer = parserConf.getByteBuffer();

    int pos = (int) nrOfBytesRead;
    int limit = buffer.limit();

    while (pos < limit && (buffer.get(pos) & 0xFF) != 0x5A) {
      pos++;
    }

    if (pos >= limit) {
      nrOfBytesRead = limit;
      return null;
    }

    nrOfBytesRead = pos + 1;
    int sfiStart = (int) nrOfBytesRead;

    try {
      try {
        sfi = StructuredFieldIntroducer.parse(buffer, sfiStart);
        sfi.setFileOffset(pos);
      } catch (Throwable e) {
        errSf = new StructuredFieldErrornouslyBuilt();
        errSf.setFileOffset(pos);
        errSf.setCausingException(e);
        // Try to capture raw SFI if possible
        int lenToCapture = Math.min(8, buffer.limit() - sfiStart);
        if (lenToCapture > 0) {
            byte[] rawSfi = new byte[lenToCapture];
            int oldPos = buffer.position();
            buffer.position(sfiStart);
            buffer.get(rawSfi);
            buffer.position(oldPos);
            errSf.setRawIntroducer(rawSfi);
        }
        throw e;
      }

      StructuredField sf;
      if (parserConf.isParseToStructuredFieldsBaseData) {
        sf = StructuredFieldBaseDataPool.acquire();
        sf.setStructuredFieldIntroducer(sfi);
      } else {
        sf = createSFInstance(sfi);
      }

      int lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
      int payloadStart = sfiStart + sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();

      boolean mustDecode = isMustDecode(sf);

      if (parserConf.isBuildShallow() && !mustDecode) {
        var actualConf = parserConf.clone();
        actualConf.setInputStream(null);
        sfi.setActualConfig(actualConf);
      } else {
        if (lenOfGrossPayload > 0) {
          byte[] padding = null;
          int lenOfSFData = lenOfGrossPayload;

          if (sfi.isFlagSet(SFFlag.isPadded)) {
            int lenOfPadding = buffer.get(payloadStart + lenOfGrossPayload - 1) & 0xFF;
            if (lenOfPadding == 0) {
              lenOfPadding = UtilBinaryDecoding.parseInt(buffer, payloadStart + lenOfGrossPayload - 3, 2);
            }
            lenOfSFData = lenOfGrossPayload - lenOfPadding;
            sf.setPadding(buffer.slice(payloadStart + lenOfSFData, lenOfPadding).asReadOnlyBuffer());
          } else {
            sf.setPadding((byte[]) null);
          }
          MnemonicPerformanceMonitor.startParse(sf);
          try {
            sf.decodeAFP(buffer, payloadStart, lenOfSFData, parserConf);
          } finally {
            MnemonicPerformanceMonitor.endParse();
          }
        }
      }

      handleStatePreservation(sf);
      nrOfBytesRead = pos + 1 + sfi.getSFLength();
      nrOfSFBuilt++;
      return sf;

    } catch (Throwable e) {
      if (errSf == null) {
        errSf = new StructuredFieldErrornouslyBuilt();
        errSf.setStructuredFieldIntroducer(sfi != null ? sfi : new StructuredFieldIntroducer());
        errSf.setFileOffset(pos);
        if (sfi != null) {
            errSf.setTypeId(sfi.getSFTypeID());
        }
      }
      errSf.setCausingException(e);
      if (sfi != null) {
        int len = sfi.getSFLength();
        int oldPos = buffer.position();
        try {
          if (pos + 1 + len <= buffer.limit()) {
            byte[] data = new byte[len];
            buffer.position(pos + 1);
            buffer.get(data);
            errSf.setData(data);
          }
        } finally {
          buffer.position(oldPos);
        }
        nrOfBytesRead = pos + 1 + len;
      }
      nrOfSFBuilt++;
      nrOfErrSFBuilt++;
      if (parserConf.isEscalateParsingErrors()) {
        if (e instanceof AFPParserException) throw (AFPParserException) e;
        throw new AFPParserException("Error parsing SF from buffer", e);
      }
      return errSf;
    }
  }

  private StructuredField parseNextSFFromStream() throws AFPParserException {
    StructuredFieldIntroducer sfi = null;
    StructuredFieldErrornouslyBuilt errSf = null;
    try {
      var is = parserConf.getInputStream();

      int tmp;
      byte[] sfData;
      byte[] padding;
      do {
        tmp = is.read();
        if (tmp != -1) {
          nrOfBytesRead++;
        }
      }
      while (tmp != 0x5A && tmp != -1); // Move to the begin of next SF, or EOF.

      if (tmp != -1) {
        try {
          sfi = StructuredFieldIntroducer.parse(is);
        } catch (Throwable e) {
          if (errSf == null) {
            errSf = new StructuredFieldErrornouslyBuilt();
            errSf.setStructuredFieldIntroducer(new StructuredFieldIntroducer());
            errSf.setFileOffset(nrOfBytesRead - 1);
          }
          if (e instanceof AFPParserException) {
            ((AFPParserException) e).setErrornouslyBuiltStructuredField(errSf);
            try {
              error((AFPParserException) e);
            } catch (AFPParserException ex) {
              if (parserConf.isEscalateParsingErrors()) {
                throw ex;
              }
            }
          } else {
            AFPParserException afpex = new AFPParserException("An exception occured when parsing structured field introducer at file index position 0x" + Long.toHexString(nrOfBytesRead) + ".", e);
            afpex.setErrornouslyBuiltStructuredField(errSf);
            try {
              error(afpex);
            } catch (AFPParserException ex) {
              if (parserConf.isEscalateParsingErrors()) {
                throw ex;
              }
            }
          }
          return errSf;
        }
        sfi.setFileOffset(nrOfBytesRead - 1);

        StructuredField sf;
        if (parserConf.isParseToStructuredFieldsBaseData) {
          sf = StructuredFieldBaseDataPool.acquire();
          sf.setStructuredFieldIntroducer(sfi);
        } else {
          sf = createSFInstance(sfi);
        }

        var lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();

        boolean mustDecode = isMustDecode(sf);

        if (parserConf.isBuildShallow() && !mustDecode) {
          var actualConf = parserConf.clone();
          actualConf.setInputStream(null);
          sfi.setActualConfig(actualConf);
          if (is.skip(lenOfGrossPayload) < lenOfGrossPayload) {
            throw new AFPParserException("Failed to skip payload while building shallow objects.");
          }

        } else {

          var grossPayload = new byte[lenOfGrossPayload];

          try {
            // Determine payload.
            if (lenOfGrossPayload > 0) {

              var read = 0;
              while (read < lenOfGrossPayload) {
                var len = is.read(grossPayload, read, lenOfGrossPayload - read);
                if (len == -1) {
                  throw new AFPParserException("Reached end of file before end of structured field.");
                } else {
                  read += len;
                }
              }

              // Determine net payload.
              if (sfi.isFlagSet(SFFlag.isPadded)) {
                int lenOfPadding = grossPayload[grossPayload.length - 1] & 0xFF;
                if (lenOfPadding == 0) {
                  lenOfPadding = UtilBinaryDecoding.parseInt(grossPayload, grossPayload.length - 3, 2);
                }

                var lenOfSFData = lenOfGrossPayload - lenOfPadding;

                sfData = new byte[lenOfSFData];
                padding = new byte[lenOfPadding];

                System.arraycopy(grossPayload, 0, sfData, 0, lenOfSFData);
                System.arraycopy(grossPayload, lenOfSFData, padding, 0, lenOfPadding);

              } else {
                sfData = grossPayload;
                padding = null;
              }

              sf.setPadding(padding);
              MnemonicPerformanceMonitor.startParse(sf);
              try {
                sf.decodeAFP(sfData, 0, -1, parserConf);
              } finally {
                MnemonicPerformanceMonitor.endParse();
              }
            }
          } catch (Throwable th) {
            sf = errSf = new StructuredFieldErrornouslyBuilt();
            errSf.setCausingException(th);
            errSf.setStructuredFieldIntroducer(sfi);
            errSf.setFileOffset(sfi.getFileOffset());
            errSf.setTypeId(sfi.getSFTypeID());
            errSf.setData(grossPayload);
            if (parserConf.isEscalateParsingErrors()) {
              throw th;
            }
          }

        }
        if (sf != null) {
          handleStatePreservation(sf);
          nrOfBytesRead += sf.getStructuredFieldIntroducer().getSFLength();
          nrOfSFBuilt++;
        }

        return sf;

      } else {
        return null;
      }

    } catch (Throwable e) {

      if (errSf == null) {
        errSf = new StructuredFieldErrornouslyBuilt();
        errSf.setStructuredFieldIntroducer(sfi);
        if (sfi != null) {
            errSf.setFileOffset(sfi.getFileOffset());
            errSf.setTypeId(sfi.getSFTypeID());
        }
      }

      nrOfBytesRead += errSf.getStructuredFieldIntroducer().getSFLength();
      nrOfSFBuilt++;
      nrOfErrSFBuilt++;

      // Call error() which may or may not re-throw the given exception
      if (e instanceof AFPParserException) {
        ((AFPParserException) e).setErrornouslyBuiltStructuredField(errSf);
        error((AFPParserException) e);
      } else {
        AFPParserException afpex = new AFPParserException("An exception occured when parsing structured field at file index position 0x" + Long.toHexString(nrOfBytesRead) + ".", e);
        afpex.setErrornouslyBuiltStructuredField(errSf);
        error(afpex);
      }

      return errSf;
    }
  }

  private boolean isMustDecode(StructuredField sf) {
    return sf instanceof FNC_FontControl
        || sf instanceof CPD_CodePageDescriptor
        || sf instanceof CPC_CodePageControl
        || sf instanceof BDD_BarCodeDataDescriptor
        || sf instanceof MCF_MapCodedFont_Format1
        || sf instanceof MCF_MapCodedFont_Format2
        || sf instanceof MDR_MapDataResource;
  }

  private void handleStatePreservation(StructuredField sf) {
    // Preserve certain SFs which maybe referenced by later SFs.
    switch (sf) {
      case FNC_FontControl fnc -> parserConf.setCurrentFontControl(fnc);
      case CPD_CodePageDescriptor cpd -> parserConf.setCurrentCodePageDescriptor(cpd);
      case CPC_CodePageControl cpc -> parserConf.setCurrentPageControl(cpc);
      case BDD_BarCodeDataDescriptor bdd -> parserConf.setCurrentBarCodeDataDescriptor(bdd);
      case MCF_MapCodedFont_Format1 mcf1 -> handleMCF1(mcf1);
      case MCF_MapCodedFont_Format2 mcf2 -> handleMCF2(mcf2);
      case MDR_MapDataResource mdr -> handleMDR(mdr);
      default -> { }
    }
  }

  /**
   * This method is called by the parser if an error condition is reached by the parser, e.g. the
   * AFP stream has errors. This method just throws the {@link AFPParserException}. Override this
   * method in order to handle/ignore exception by your application and continue parsing.
   */
  public void error(AFPParserException afpExc) throws AFPParserException {
    throw afpExc;
  }

  private void handleMCF1(MCF_MapCodedFont_Format1 mcf1) {
    if (mcf1.getRepeatingGroups() == null) {
      return;
    }
    mcf1.getRepeatingGroups().stream()
        .filter(rg -> rg.getCodePageName() != null)
        .forEach(rg -> {
          var cs = UtilCharacterEncoding.getCharsetFromCodePageName(rg.getCodePageName());
          if (cs != null) {
            parserConf.addCodedFontCharsetMapping(rg.getCodedFontLocalID(), cs);
          }
        });
  }

  private void handleMCF2(MCF_MapCodedFont_Format2 mcf2) {
    if (mcf2.getRepeatingGroups() == null) {
      return;
    }
    mcf2.getRepeatingGroups().stream()
        .filter(IHasTriplets.class::isInstance)
        .map(IHasTriplets.class::cast)
        .forEach(rg -> {
          List<Triplet> triplets = rg.getTriplets();
          Short lid = null;
          java.nio.charset.Charset cs = null;
          if (triplets != null) {
            for (Triplet t : triplets) {
              if (t instanceof Triplet.ResourceLocalIdentifier rli) {
                if (rli.getResourceType() == Triplet.ResourceLocalIdentifier.RLI_ResourceType.CodedFont) {
                  lid = rli.getResourceLocalID();
                }
              } else if (t instanceof Triplet.FullyQualifiedName fqn) {
                if (fqn.getType() == Triplet.GlobalID_Use.CodePageNameReference) {
                  cs = UtilCharacterEncoding.getCharsetFromCodePageName(fqn.getNameAsString());
                }
              }
            }
          }
          if (lid != null && cs != null) {
            parserConf.addCodedFontCharsetMapping(lid, cs);
          }
        });
  }

  private void handleMDR(MDR_MapDataResource mdr) {
    if (mdr.getRepeatingGroups() == null) {
      return;
    }
    mdr.getRepeatingGroups().stream()
        .filter(IHasTriplets.class::isInstance)
        .map(IHasTriplets.class::cast)
        .forEach(rg -> {
          List<Triplet> triplets = rg.getTriplets();
          Short lid = null;
          java.nio.charset.Charset cs = null;
          if (triplets != null) {
            for (Triplet t : triplets) {
              if (t instanceof Triplet.ResourceLocalIdentifier rli) {
                if (rli.getResourceType() == Triplet.ResourceLocalIdentifier.RLI_ResourceType.CodedFont) {
                  lid = rli.getResourceLocalID();
                }
              } else if (t instanceof Triplet.FullyQualifiedName fqn) {
                if (fqn.getType() == Triplet.GlobalID_Use.CodePageNameReference) {
                  cs = UtilCharacterEncoding.getCharsetFromCodePageName(fqn.getNameAsString());
                  if (cs != null) {
                    parserConf.setAfpCharSet(cs);
                  }
                }
              }
            }
          }
          if (lid != null && cs != null) {
            parserConf.addCodedFontCharsetMapping(lid, cs);
          }
        });
  }

  /**
   * Returns the total number of bytes read so far.
   *
   * @return the number of bytes read
   */
  public long getCountReadByte() {
    return nrOfBytesRead;
  }

  /**
   * Sets the number of bytes read, effectively setting the starting offset for the next parsing
   * operation.
   *
   * @param offset the new offset
   */
  public void setNrOfBytesRead(long offset) {
    this.nrOfBytesRead = offset;
  }

  /**
   * Quits the parsing process and cleans up resources.
   *
   * @throws AFPParserException if closing the input stream fails
   */
  public void quitParsing() throws AFPParserException {
    parserConf.resetCurrentAFPObjects();

    if (parserConf.isParserOwnsInputStream && parserConf.inputStream != null) {
      try {
        parserConf.inputStream.close();
      } catch (IOException e) {
        throw new AFPParserException("Failed to close input stream.", e);
      }
    }
  }

  /**
   * Returns the total number of structured fields that has been built so far.
   *
   * @return the number of structured fields built
   */
  public long getNrOfSFBuilt() {
    return nrOfSFBuilt;
  }

  /**
   * Returns the number of structured fields that has been built with errors so far.
   *
   * @return the number of structured fields built with errors
   */
  public long getNrOfSFBuiltWithErrors() {
    return nrOfErrSFBuilt;
  }
}
