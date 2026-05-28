
import os

afp_packages = [
    "com.mgz.afp.modca",
    "com.mgz.afp.ptoca",
    "com.mgz.afp.foca",
    "com.mgz.afp.ioca",
    "com.mgz.afp.goca",
    "com.mgz.afp.bcoca",
    "com.mgz.afp.lineData",
    "com.mgz.afp.modca_L",
    "com.mgz.afp.cmoca"
]

sf_type_ids = [
    "BAG_BeginActiveEnvironmentGroup", "BBC_BeginBarCodeObject", "BCA_BeginColorAttributeTable",
    "BCF_BeginCodedFont", "BCP_BeginCodePage", "BDA_BarCodeData", "BDD_BarCodeDataDescriptor",
    "BDG_BeginDocumentEnvironmentGroup", "BDI_BeginDocumentIndex", "BDM_BeginDataMap",
    "BDT_BeginDocument", "BDX_BeginDataMapTransmitionSubcase", "BFG_BeginFormEnvironmentGroup",
    "BFM_BeginFormMap", "BFN_BeginFont", "BGR_BeginGraphicsObject", "BII_BeginIMImageObject",
    "BIM_BeginImageObject", "BMM_BeginMediumMap", "BMO_BeginOverlay", "BNG_BeginNamedPageGroup",
    "BOC_BeginObjectContainer", "BOG_BeginObjectEnvironmentGroup", "BPF_BeginPrintFile",
    "BPG_BeginPage", "BPM_BeginPageMap", "BPS_BeginPageSegment", "BPT_BeginPresentationTextObject",
    "BRG_BeginResourceGroup", "BRS_BeginResource", "BSG_BeginResourceEnvironmentGroup",
    "CAT_ColorAttributeTable", "CCP_ConditionalProcessingControl", "CDD_ContainerDataDescriptor",
    "CMR_ColorManagementResource", "CFC_CodedFontControl", "CFI_CodedFontIndex", "CPC_CodePageControl",
    "CPD_CodePageDescriptor", "CPI_CodePageIndex", "CTC_ComposedTextControl",
    "DXD_DataMapTransmitionSubcaseDescriptor", "EAG_EndActiveEnvironmentGroup", "EBC_EndBarCodeObject",
    "ECA_EndColorAttributeTable", "ECF_EndCodedFont", "ECP_EndCodePage", "EDG_EndDocumentEnvironmentGroup",
    "EDI_EndDocumentIndex", "EDM_EndDataMap", "EDT_EndDocument", "EFG_EndFormEnvironmentGroup",
    "EFM_EndFormMap", "EFN_EndFont", "EGR_EndGraphicsObject", "EDX_EndDataMapTransmitionSubcase",
    "EII_EndIMImageObject", "EIM_EndImageObject", "EMM_EndMediumMap", "EMO_EndOverlay",
    "ENG_EndNamedPageGroup", "EOC_EndObjectContainer", "EOG_EndObjectEnvironmentGroup",
    "EPF_EndPrintFile", "EPG_EndPage", "EPM_EndPageMap", "EPS_EndPageSegment",
    "EPT_EndPresentationTextObject", "ERG_EndResourceGroup", "ERS_EndResource",
    "ESG_EndResourceEnvironmentGroup", "FDS_FixedDataSize", "FDX_FixedDataText",
    "FGD_FormEnvironmentGroupDescriptor", "FNC_FontControl", "FND_FontDescriptor", "FNG_FontPatterns",
    "FNI_FontIndex", "FNM_FontPatternsMap", "FNN_FontNameMap", "FNO_FontOrientation",
    "FNP_FontPosition", "GAD_GraphicsData", "GDD_GraphicsDataDescriptor", "ICP_IMImageCellPosition",
    "IDD_ImageDataDescriptor", "IDM_InvokeDataMap", "IEL_IndexElement", "IID_IMImageInputDescriptor",
    "IMM_InvokeMediumMap", "IOB_IncludeObject", "IOC_IMImageOutputControl", "IPD_ImagePictureData",
    "IPG_IncludePage", "IPO_IncludePageOverlay", "IPS_IncludePageSegment", "IRD_IMImageRasterData",
    "LLE_LinkLogicalElement", "LNC_LineDescriptorCount", "LND_LineDescriptor", "MPT_MapPresentationText",
    "MBC_MapBarCodeObject", "MCA_MapColorAttributeTable", "MCC_MediumCopyCount", "MCD_MapContainerData",
    "MCF_MapCodedFont_Format1", "MCF_MapCodedFont_Format2", "MDD_MediumDescriptor",
    "MDR_MapDataResource", "MFC_MediumFinishingControl", "MGO_MapGraphicsObject",
    "MIO_MapImageObject", "MMC_MediumModificationControl", "MMD_MapMediaDestination",
    "MMO_MapMediumOverlay", "MMT_MapMediaType", "MPG_MapPage", "MPO_MapPageOverlay",
    "MPS_MapPageSegment", "MSU_MapSuppression", "NOP_NoOperation", "OBD_ObjectAreaDescriptor",
    "OBP_ObjectAreaPosition", "OCD_ObjectContainerData", "PEC_PresentationEnvironmentControl",
    "PFC_PresentationFidelityControl", "PGD_PageDescriptor", "PGP_PagePosition_Format1",
    "PGP_PagePosition_Format2", "PMC_PageModificationControl", "PPO_PreprocessPresentationObject",
    "PTD_PresentationTextDataDescriptor_Format1", "PTD_PresentationTextDataDescriptor_Format2",
    "PTX_PresentationTextData", "RCD_RecordDescriptor", "TLE_TagLogicalElement", "XMD_XMLDescriptor"
]

mapping = {}
for sf_id in sf_type_ids:
    found = False
    for pkg in afp_packages:
        class_path = pkg.replace(".", "/") + "/" + sf_id + ".java"
        if os.path.exists("src/main/java/" + class_path):
            mapping[sf_id] = pkg + "." + sf_id
            found = True
            break
    if not found:
        print(f"Warning: No implementation found for {sf_id}")

print("  static {")
for sf_id in sorted(mapping.keys()):
    print(f"    SF_SUPPLIERS.put(SFTypeID.{sf_id}, {mapping[sf_id]}::new);")
print("  }")
