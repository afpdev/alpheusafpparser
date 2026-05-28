# Chapter 4. CMR Types

The following CMRTypes are defined: [CMOCA-4-001]

*   Halftone [CMOCA-4-002]
*   Tone Transfer Curve [CMOCA-4-003]
*   Color Conversion [CMOCA-4-004]
*   Link Color Conversion [CMOCA-4-005]
*   Indexed [CMOCA-4-006]

Each of the CMRTypes is described in more detail below. [CMOCA-4-007]

In the following descriptions, the Optional Tags and Mandatory Tags are listed to show which tags are meaningful for each type and subset. Any other tags present are ignored by the receiver. The Comment tag is optional. The End Data tag is required in all CMR objects. [CMOCA-4-008]

## Halftone CMR

**CMR Type HT (X'0048 0054')** [CMOCA-4-009]

### Description

Halftone can be classified into two types: point-operation halftone and neighborhood-operation halftone. The output of the point-operation halftone depends only on the value of the current pixel. It can be used for numerous common halftone types including clustered dot, dispersed, and stochastic. Threshold arrays are commonly used for the bilevel point-operation halftones, and lookup tables are commonly used for the multilevel point-operation halftones. The error diffusion halftone is commonly used for the neighborhood-operation halftone. The most common error diffusion filters include Floyd-Steinberg, Jarvis-Judice-Ninke, Stucki, etc. The Halftone Subset tag indicates the halftone type, and thus determines required and optional tags for this Halftone CMR. [CMOCA-4-010]

### Subset X'01': Bilevel Point-Operation Halftone

*   **Mandatory Tags:** Halftone Subset, Array Width, Array Height, Bilevel Point-Operation Screen Data, End Data [CMOCA-4-011]
*   **Optional Tags:** Comment, Date and Time Stamp, Number of Components, Offset Tiling [CMOCA-4-012]

### Subset X'02': Multilevel Point-Operation Halftone

*   **Mandatory Tags:** Halftone Subset, Array Width, Array Height, Max Image Value, Number of Device Levels, Multilevel Point-Operation Screen Data, End Data [CMOCA-4-013]
*   **Optional Tags:** Comment, Date and Time Stamp, Number of Components, Offset Tiling [CMOCA-4-014]

### Subset X'03': Bilevel Error Diffusion Halftone

*   **Mandatory Tags:** Halftone Subset, Array Width, Array Height, Error Diffusion Filter, Location of Current Pixel, Raster Direction, Boundary Condition, Threshold Value, End Data [CMOCA-4-015]
*   **Optional Tags:** Comment, Date and Time Stamp, Number of Components [CMOCA-4-016]

### Subset X'04': Multilevel Error Diffusion Halftone

*   **Mandatory Tags:** Halftone Subset, Array Width, Array Height, Number of Device Levels, Error Diffusion Filter, Location of Current Pixel, Raster Direction, Boundary Condition, Quantization Boundary Table, End Data [CMOCA-4-017]
*   **Optional Tags:** Comment, Date and Time Stamp, Number of Components [CMOCA-4-018]

## Tone Transfer Curve CMR

**CMR Type TC (X'0054 0043')** [CMOCA-4-019]

### Description

Tone transfer curves are applied to data prior to halftoning or output. The inverse tone transfer curves are applied to restore data to the original state. The printer tone transfer curve produces a desired appearance by compensating for dot gain. The tone transfer curve for a display or other input device is used to correct non-linearity (gamma) of the device. Currently, there are two tone transfer curve subsets: ToneTransferCurve Array and ToneTransferCurve Identity. The Subset tag indicates the tone transfer curve type, and thus determines required and optional tags for this Tone Transfer Curve CMR. [CMOCA-4-020]

### Subset X'01': ToneTransferCurve Array

*   **Mandatory Tags:** Tone Transfer Curve Subset, Tone Transfer Curve Data, End Data [CMOCA-4-021]
*   **Optional Tags:** Comment, Date and Time Stamp, Number of Components, Tone Transfer Curve Length, Inverse Tone Transfer Curve Data [CMOCA-4-022]

### Subset X'02': ToneTransferCurve Identity

The tone transfer curve for each component is the identity. No data is sent with this subset, that is, no tone transfer curve is to be applied. This subset is implemented for performance reasons. [CMOCA-4-023]

*   **Mandatory Tags:** Tone Transfer Curve Subset, End Data [CMOCA-4-024]
*   **Optional Tags:** Comment, Date and Time Stamp [CMOCA-4-025]

## Color Conversion CMR

**CMR Type CC (X'0043 0043')** [CMOCA-4-026]

### Description

Each instance of this CMR type is a subset of the standard ICC profile. This allows the CMR to be used in any color management system. [CMOCA-4-027]

The ICC Profile Data starts with a 128-byte header followed by the ICCTags. The ICCHeaderFields are contained in pre-defined byte positions as defined in Table 35. [CMOCA-4-028]

Each subset of the ICC profile type, selected by the ICC Profile Subset tag, defines a subset of the ICC specification. For each subset, the Color Management Object Content Architecture defines the mandatory and optional ICCHeaderFields and ICCTags. Optional ICCTags and ICCHeaderFields will be processed as applicable if encountered. Any other ICCTags will be ignored. [CMOCA-4-029]

**Note:** The chromaticAdaptationTag is shown as optional for each subset. However, it is mandatory if the value in the mediaWhitePointTag is not D50. [CMOCA-4-030]

Two ICCHeaderFields are mandatory for the Color Conversion CMR: Color Space of Data and Profile Connection Space. The descriptions are as follows: [CMOCA-4-031]

*   **Color Space of Data:** The ICC-supported color spaces and their signatures are listed in Table 10. [CMOCA-4-032]
*   **Profile Connection Space:** The ICC profile connection space is either CIELAB D50 or CIEXYZ D50 for all ICC profiles except the ICC DeviceLink profile. The CIELAB signature is “Lab” and the CIEXYZ signature is “XYZ”. [CMOCA-4-033]

The currently allowed ICC profile subsets for Color Conversion CMRs include all the ICC profile types except for the DeviceLink and the Named Colour profiles. (For complete information, please refer to sections 8.6 and 8.9 in ICC 1:2004-10 Version 4.2.0.0.) [CMOCA-4-034]

The ICC profile subsets for the Color Conversion CMR are listed in Table 12. [CMOCA-4-035]

### **Table 12. ICC Profile Subsets for the Color Conversion CMR**

| Subset Name | Usage | Basic Intents |
| :--- | :--- | :--- |
| Monochrome input profile | Scanner, digital camera | Device → PCS [CMOCA-4-036]|
| Monochrome display profile | Display | Device ← → PCS [CMOCA-4-037]|
| Monochrome output profile | Printer | Device → PCS [CMOCA-4-038]|
| Three-component matrix-based input profile | Scanner, digital camera | Device → PCS [CMOCA-4-039]|
| Three-component matrix-based display profile | Display | Device ← → PCS [CMOCA-4-040]|
| N-component LUT-based input profile | Scanner, digital camera | Device → PCS [CMOCA-4-041]|
| N-component LUT-based display profile | Display | Device ← → PCS [CMOCA-4-042]|
| N-component LUT-based output profiles | Printer, film recorder | PCS → Device [CMOCA-4-043]|
| ColorSpace conversion profile | Non-device color space (e.g., sRGB, D65) | Non-device ← → PCS [CMOCA-4-044]|

The Basic Intents Column describes the most commonly used color conversion direction(s) for each ICC profile subset. [CMOCA-4-045]

*   **Mandatory Tags:** ICC Profile Subset, ICC Profile Data, End Data [CMOCA-4-046]
*   **Optional Tags:** Comment, Date and Time Stamp, ICC Profile Filename [CMOCA-4-047]

**Implementation note:** It is very important to include the ICC Profile Filename tag for debugging purposes regardless of the fact that it is optional. [CMOCA-4-048]

### Subset X'01': Monochrome Input Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-049]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-050]|
| 20–23 | Profile Connection Space [CMOCA-4-051]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-052]
*   **Mandatory ICCTags:** profileDescriptionTag, grayTRCTag, mediaWhitePointTag, copyrightTag [CMOCA-4-053]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB0Tag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag [CMOCA-4-054]

### Subset X'02': Monochrome Display Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-055]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-056]|
| 20–23 | Profile Connection Space [CMOCA-4-057]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-058]
*   **Mandatory ICCTags:** profileDescriptionTag, grayTRCTag, mediaWhitePointTag, copyrightTag [CMOCA-4-059]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB0Tag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag [CMOCA-4-060]

### Subset X'03': Monochrome Output Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-061]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-062]|
| 20–23 | Profile Connection Space [CMOCA-4-063]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-064]
*   **Mandatory ICCTags:** profileDescriptionTag, grayTRCTag, mediaWhitePointTag, copyrightTag [CMOCA-4-065]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB0Tag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag [CMOCA-4-066]

### Subset X'04': Three-Component Matrix-Based Input Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-067]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-068]|
| 20–23 | Profile Connection Space [CMOCA-4-069]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-070]
*   **Mandatory ICCTags:** profileDescriptionTag, redMatrixColumnTag, greenMatrixColumnTag, blueMatrixColumnTag, redTRCTag, greenTRCTag, blueTRCTag, mediaWhitePointTag, copyrightTag [CMOCA-4-071]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB0Tag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag, gamutTag [CMOCA-4-072]

### Subset X'05': Three-Component Matrix-Based Display Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-073]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-074]|
| 20–23 | Profile Connection Space [CMOCA-4-075]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-076]
*   **Mandatory ICCTags:** profileDescriptionTag, redMatrixColumnTag, greenMatrixColumnTag, blueMatrixColumnTag, redTRCTag, greenTRCTag, blueTRCTag, mediaWhitePointTag, copyrightTag [CMOCA-4-077]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB0Tag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag, gamutTag [CMOCA-4-078]

### Subset X'06': N-Component LUT-Based Input Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-079]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-080]|
| 20–23 | Profile Connection Space [CMOCA-4-081]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-082]
*   **Mandatory ICCTags:** profileDescriptionTag, AT oB0Tag, mediaWhitePointTag, copyrightTag [CMOCA-4-083]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB1Tag, AT oB2Tag, BT oA0Tag, BT oA1Tag, BT oA2Tag, gamutTag [CMOCA-4-084]

### Subset X'07': N-Component LUT-Based Display Profile

*   **Mandatory ICCHeaderFields** [CMOCA-4-085]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-086]|
| 20–23 | Profile Connection Space [CMOCA-4-087]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-088]
*   **Mandatory ICCTags:** profileDescriptionTag, AT oB0Tag, BT oA0Tag, mediaWhitePointTag, copyrightTag [CMOCA-4-089]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB1Tag, AT oB2Tag, BT oA1Tag, BT oA2Tag, gamutTag [CMOCA-4-090]

### Subset X'08': N-Component LUT-Based Output Profiles

*   **Mandatory ICCHeaderFields** [CMOCA-4-091]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-092]|
| 20–23 | Profile Connection Space [CMOCA-4-093]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-094]
*   **Mandatory ICCTags:** profileDescriptionTag, AT oB0Tag, BT oA0Tag, AT oB1Tag, BT oA1Tag, AT oB2Tag, BT oA2Tag, gamutTag, mediaWhitePointTag, copyrightTag [CMOCA-4-095]
*   **Optional ICCTags:** chromaticAdaptationTag, colorantTableTag [CMOCA-4-096]

### Subset X'09': ColorSpace Conversion Profile

This subset should be used as an audit color conversion. [CMOCA-4-097]

*   **Mandatory ICCHeaderFields** [CMOCA-4-098]

| Byte Offset | Content |
| :--- | :--- |
| 16–19 | Color Space of Data [CMOCA-4-099]|
| 20–23 | Profile Connection Space [CMOCA-4-100]|

*   **Optional ICCHeaderFields:** All other header fields [CMOCA-4-101]
*   **Mandatory ICCTags:** profileDescriptionTag, AT oB0Tag, BT oA0Tag, mediaWhitePointTag, copyrightTag [CMOCA-4-102]
*   **Optional ICCTags:** chromaticAdaptationTag, AT oB1Tag, AT oB2Tag, BT oA1Tag, BT oA2Tag, gamutTag [CMOCA-4-103]

### Subset X'0A': Retired Item 3 (Abstract Profile)

## Link Color Conversion CMR

**CMR Type LK (X'004C 004B') or DL (X'0044 004C')** [CMOCA-4-104]

### Description

The purpose of the Link Color Conversion CMR is to convert directly from the input to the output color space. There are two major types of Link Color Conversion: [CMOCA-4-105]

*   **Link Color Conversion** with up to four Lookup Tables (LUTs) representing different rendering intents. It is selected for use based on the audit and instruction OIDs that are contained in its tags. [CMOCA-4-106]
*   **ICC DeviceLink** contains an ICC profile of type DeviceLink. It contains a complex color conversion description (with up to five processing elements) for exactly one rendering intent. It is selected for use when it is found during a search of the hierarchy of invoked link CMRs (only ICC DeviceLink CMRs are invoked, other link CMR subsets are not invoked). It is not based on an audit and instruction Color Conversion pair. [CMOCA-4-107]

**LinkColorConversion LUT subset:** Its purpose is to combine an audit Color Conversion CMR with an instruction Color Conversion CMR to improve performance. It allows up to four LUTs, each representing one of the four ICC rendering intents. It is permissible to reference the same LUT tag data for multiple rendering intents. The LUT is constructed by combining the processing required for the audit and the instruction color conversions. If one of the AT oB/BT oA tags in an audit or instruction CMR is missing when constructing the link LUT, the tag data for the rendering intent specified in that CMR's ICC profile header is used in place of the missing tag data. The default rendering intent profile header is used in place of the missing tag data. The default rendering intent for the Link Color Conversion CMR is the rendering intent specified in the ICCHeader Field of the instruction Color Conversion CMR. The processing detail is described in “Creating Link Color Conversion CMRs – LinkColorConversion LUT subset”. [CMOCA-4-108]

**ICC DeviceLink subset:** Its purpose is to provide a customized path to convert directly from input to output space with no dependency on an audit and instruction CMR. It allows only one single AT oB0Tag representing one rendering intent. The rendering intent is indicated in the header of the ICC profile. The AT oB0Tag contains up to five processing elements, possibly making the conversion more complex than if a single LUT were used. Whereas link CMRs in general are not invoked, CMRs with this subset ID must be invoked in order to be used. A hierarchical search is performed to determine if there is an applicable ICC DeviceLink that can be used. The device should search for an ICC DeviceLink before searching for an Audit/Instruction Color Conversion pair. The currently active Rendering Intent is ignored when an ICC DeviceLink is selected for use. [CMOCA-4-109]

Currently, three Link Color Conversion subsets are defined. Table 22 lists the Link Color Conversion CMR subsets and their descriptions: [CMOCA-4-110]

### **Table 22. Link Color Conversion Subsets**

| Subset ID | Subset Name | Usage |
| :--- | :--- | :--- |
| X'01' | LinkColorConversion LUT | Connection between two device color spaces, or the connection between a non-device color space and a device color space (e.g., scanner→printer or sRGB→printer). Selected based on OIDs of selected audit/instruction CC CMRs. [CMOCA-4-111]|
| X'02' | LinkColorConversion Identity | No conversion needed. Selected based on OIDs of selected audit/instruction CC CMRs. [CMOCA-4-112]|
| X'03' | ICC DeviceLink | Direct conversion between input and output space without reference to audit/instruction CC pair and without use of a PCS. [CMOCA-4-113]|

### Subset X'01': LinkColorConversion LUT

*   **Mandatory Tags:** Link Color Conversion Subset, Link Audit CMR OID, Link Instruction CMR OID, Link Audit CMR Name, Link Instruction CMR Name, Default Rendering Intent, Link LUT Perceptual, End Data [CMOCA-4-114]
    **Note:** LUTs for all four rendering intents must be provided, but duplicate LUTs may be identified by setting the appropriate additional use flag in a specified Link LUT tag. For example, when all four LUTs are identical, only the Link LUT Perceptual tag is specified. When an additional use flag for a specific rendering intent is set to B'1', the Link-LUT tag for that rendering intent is omitted.
*   **Optional Tags:** Comment, Date and Time Stamp, Link LUT Media-Relative Colorimetric, Link LUT Saturation, Link LUT ICC-Absolute Colorimetric, Link CMRE Identifier [CMOCA-4-115]

### Subset X'02': LinkColorConversion Identity

This subset is used when the input space is the same as the device's output space and no color conversion is to be done. There is no data with this subset. The OIDs for the audit and instruction Color Conversion CMR must be the same. [CMOCA-4-116]

*   **Mandatory Tags:** Link Color Conversion Subset, Link Audit CMR OID, Link Instruction CMR OID, Link Audit CMR Name, Link Instruction CMR Name, End Data [CMOCA-4-117]
*   **Optional Tags:** Comment, Date and Time Stamp, Link CMRE Identifier [CMOCA-4-118]

### Subset X'03': ICC DeviceLink

*   **Mandatory Tags:** Link Color Conversion Subset, ICC Profile Data, End Data [CMOCA-4-119]
*   **Optional Tags:** Comment, Date and Time Stamp, ICC Profile Filename [CMOCA-4-120]
*   **Mandatory ICCTags:** profileDescriptionTag, copyrightTag, profileSequenceDescTag, AtoB0Tag, colorantTableTag (required only if Data Colour Space is xCLR), colorantTableOutTag (required only if Profile Connection Space is xCLR) [CMOCA-4-121]
*   **Optional ICCTags:** None [CMOCA-4-122]

## Indexed CMR

**CMR Type IX (X'0049 0058')** [CMOCA-4-123]

### Description

An Indexed CMR contains one or more Color Palette tags that translate 2-byte indexed color values to the target color space. Five Color Palette tags are defined for the color spaces of gray, RGB, CMYK, CIELAB, and named colorants. The named colorants are defined through a set of colorant names that are specified in the Colorant Identification List tag. Currently, only one Indexed CMR subset is defined for the multi-output color spaces. It allows the mixture of different output color spaces in an Indexed CMR. When multiple Color Palette tags are present in a CMR, and the same indexed color value is specified in different Color Palette tags, the indexed color value in the Color Palette tag with the lower TagID number is used. If the color space of that Color Palette tag is not applicable for the output device, the CIELAB value specified for this indexed color value in the Color Palette tag is used for the substitution. [CMOCA-4-124]

### **Table 23. List of Color Palette Tags**

| Name | Meaning |
| :--- | :--- |
| Color Palette Gray | Color Palette tag for monochrome output devices [CMOCA-4-125]|
| Color Palette CMYK | Color Palette tag for CMYK output devices [CMOCA-4-126]|
| Color Palette RGB | Color Palette tag for RGB output devices [CMOCA-4-127]|
| Color Palette CIELAB | Color Palette tag for the D50 CIELAB color space [CMOCA-4-128]|
| Color Palette Named Colorants | Color Palette tag for the named colorants color space [CMOCA-4-129]|

### Subset X'01': Multi-Output Color Spaces

*   **Mandatory Tags:** Indexed Subset, one of Color Palette tags, End Data [CMOCA-4-130]
    **Note:** Number of Named Colorants tag and Colorant Identification List tag are mandatory if Color Palette Named Colorants tag is specified.
*   **Optional Tags:** Comment, Date and Time Stamp, Color Palette tags [CMOCA-4-131]

**Exception Condition:**
If no Color Palette tag is specified, exception condition EC-50400E exists. It is shown in “Color Palette Named Colorants”. [CMOCA-4-132]

**EC-50400E Missing Required Tag:** At least one Color Palette tag is required but none were specified. [CMOCA-4-133]
