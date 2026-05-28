# Chapter 6. CMR Processing

## Overview of CMR Processing

CMRs are used to describe a process that:
*   Takes presentation data specified in an input color space [CMOCA-6-001]
*   Converts it to the output color space of the presentation device [CMOCA-6-002]
*   Modifies the colors to create the desired output for a particular device [CMOCA-6-003]
*   Halftones the output data [CMOCA-6-004]

The actual input and output device color spaces constrain which CMRs are applicable. There can be multiple CMRs that are invoked, but only some of them are usable for given data. [CMOCA-6-005]

Further, CMRs can be invoked at multiple levels of the AFP document hierarchy and it is possible to have more than one CMR that is applicable for a given task at one time. For instance, there can be two audit color conversions from RGB to CIELAB; one is SMPTE-C and the other is sRGB. One can be invoked at the object level and the other at the page level. IPDS hierarchical rules and last-received-wins (when multiple conflicting CMRs exist at the same level) are used to resolve conflicts. [CMOCA-6-006]

Media matching also affects the hierarchical search. If the media attributes specified in the CMR header do not match the media currently in use by the device, the hierarchical search may continue, looking for a CMR that better matches the media. This is described in “Matching Media Type of CMR With Media Type of Device”. [CMOCA-6-007]

Note that there might be multiple CMRs of a given type invoked at one particular level. For instance, there could be two audit Color Conversion CMRs attached to a GOCA object, one for CMYK input data and the other for RGB data. Colors within the GOCA object might be specified using both color spaces and the appropriate CMR would be used each time. [CMOCA-6-008]

Taken as a whole, the CMR system can seem complex. But a typical situation will be simple. Some complicated explanations will be included later to clearly define what must be done in complex situations, but they will rarely be encountered. [CMOCA-6-009]

When a CMR is needed, the device searches the hierarchy for an applicable CMR that applies to the current color space. The AFP architecture hierarchy for CMRs is as follows: [CMOCA-6-010]

1.  **CMR invoked with an object.** Note that CMRs attached to an object received in home state are ignored and that CMRs can be attached to an object when it is included using an IDO. (See “Color Conversion Profiles Within TIFF, JFIF, and GIF” for a discussion of profiles embedded within the object.) [CMOCA-6-011]
2.  **CMR invoked with a page or overlay** [CMOCA-6-012]
3.  **CMR invoked in home state** [CMOCA-6-013]
4.  **Device default CMR** [CMOCA-6-014]

If two applicable CMRs that both apply to the current color space are invoked at the same level, the last one invoked is used. If no applicable CMR is explicitly invoked, a device default is used. [CMOCA-6-015]

For color conversions, link CMRs are normally used to improve throughput. The following discussion assumes that there are no active link CMRs. “Link Color Conversion CMRs”, discusses Link Color Conversion CMRs. [CMOCA-6-016]

1.  Presentation data specifies an input color space. Knowing that color space, a search is done of the invoked audit Tone Transfer Curve CMRs to find the first one that has the same number of components. If one is not found, the identity tone transfer curve (that is, the printer default) is used. [CMOCA-6-017]
2.  Next, knowing the input color space, a search is done of the invoked audit Color Conversion CMRs to find the first one that has that input color space. This is done by examining the Color Space Signature field within the ICC profile header. In cases where the name of the input color space is unknown, the number of components in the input data will be used to select a Color Conversion CMR. [CMOCA-6-018]
3.  A search of the invoked instruction Color Conversion CMRs is done to find the first one with the required output color space. In most cases, this will be a device default CMR. Note that the audit and instruction PCSs do not need to be the same. The device has the ability to convert between CIELAB and CIEXYZ, the available PCSs. [CMOCA-6-019]
4.  A search is done of the invoked instruction Tone Transfer Curve CMRs to find one with the correct number of components. [CMOCA-6-020]
5.  A search is done of the invoked Halftone CMRs to find one with the right number of components. [CMOCA-6-021]
6.  The colored data is converted and halftoned using these CMRs. Note that it is possible to combine some of the operations to improve performance. [CMOCA-6-022]

## “Applicable”, “Selected” CMRs

CMRs must be applicable in order to be used. If a CMR is not applicable, it may be ignored and no NACK is issued. The following examples explain the meaning of applicable. [CMOCA-6-023]

*   An instruction Tone Transfer Curve CMR with three components is not applicable if the device is monochrome. [CMOCA-6-024]
*   An instruction Halftone CMR with three components is not applicable on a CMYK device. [CMOCA-6-025]
*   An audit Color Conversion CMR that converts from a three-component space is not applicable if the input image has four components. [CMOCA-6-026]
*   An output Color Conversion CMR that converts into a four-component color space is not applicable if the device is a CMYK printer that is running in a monochrome-appearance mode. [CMOCA-6-027]

In order to select a CMR, the hierarchy is searched as discussed above, looking for an applicable CMR. The first applicable CMR found in the hierarchy is selected and used. [CMOCA-6-028]

## Link Color Conversion CMRs

Link Color Conversion CMRs provide an efficient method for converting directly from the input color space to the output color space. This is useful for optimizing performance. [CMOCA-6-029]

### Link Color Conversion CMRs Based on Audit/Instruction Color Conversion CMRs

Link Color Conversion CMRs with subset types of X'01' (LinkColorConversion LUT) and X'02' (LinkColorConversion Identity) are architected to require two special tags:
*   Audit CMR identifier: A Link Audit CMR OID tag [CMOCA-6-030]
*   Instruction CMR Identifier: A Link Instruction CMR OID tag [CMOCA-6-031]

When Color Conversion CMRs are sent to the device, an OID must be attached. The OID uniquely identifies the CMR and is used to connect the audit and instruction Color Conversion CMRs with the matching link CMR. The OID format is described in the MO:DCA Registry Appendix of the Mixed Object Document Content Architecture (MO:DCA) Reference. [CMOCA-6-032]

The audit and instruction CMRs are identified as described above. Then a search is done of Link Color Conversion CMRs to find a link CMR that combines the audit and instruction CMRs. This is done by comparing the CMR OIDs with those specified in the link CMR. Note that any activated link CMR can be used. It does not need to be invoked in order to be eligible for use. [CMOCA-6-033]

Note, further, that an audit CMR must be identified to use a downloaded link CMR. If the audit-type information from within an object (for example, a TIFF image) is used, there is no way to identify the link that can be substituted. So if it is desirable to use a link CMR, an audit CMR must be attached to the TIFF object. The audit CMR takes precedence over the ICC profile specified within the TIFF. [CMOCA-6-034]

Link Color Conversion CMRs also require tags containing the CMR names of the audit and instruction CMRs. These two tags are required but are for informational purposes only and are not used while selecting the link CMR. [CMOCA-6-035]

### Link Color Conversions: ICC DeviceLink Subset

In some situations, it might be desirable to use a conversion that goes direct from input color space to output color space without using an intermediate conversion into the PCS. For instance, a direct conversion can be used to avoid losing information when conversions go in and out of the PCS. This might preserve information about how much black to use, or information about a spot color. The ICC DeviceLink subset of the link CMR can be used in this situation. Typically, the ICC DeviceLink Profile would be created in some special customized or hand-crafted manner and be targeted at a particular device. [CMOCA-6-036]

A link CMR with subset type of ICC DeviceLink must be invoked in order to be used. Note that other link CMRs do not need to be invoked. The ICC DeviceLink subset can be easily identified by checking the CMRType field in the CMR header: it will be “DL”. Since an ICC DeviceLink can be invoked at various levels of the hierarchy, a hierarchical search must occur in order to select an ICC DeviceLink for use. [CMOCA-6-037]

This special link CMR has precedence over audit/instruction color conversions. In the following, “Look for an ICC DeviceLink” means that the input color space matches that of the data and the output space matches that of the device. If multiple device links are invoked at a given level of the hierarchy, the last one invoked will be selected. [CMOCA-6-038]

The existence of an audit CC at some level has precedence over an ICC DeviceLink at a lower level. The search algorithm is shown here and the figure below diagrammatically shows how to search. [CMOCA-6-039]

*   **Search at the object level** [CMOCA-6-040]
    *   Look for an ICC DeviceLink. If found, stop and use it. [CMOCA-6-041]
    *   Else look for an audit CC CMR. If found, stop, find an instruction CC CMR by searching all levels. Use the selected pair. [CMOCA-6-042]
*   **If an ICC Profile exists within the object (e.g., TIFF)**, use it and find an instruction CC CMR by searching all levels. [CMOCA-6-043]
*   **Else search at the page/overlay level** [CMOCA-6-044]
    *   Look for an ICC DeviceLink. If found, stop and use it. [CMOCA-6-045]
    *   Else look for an audit CC CMR. If found, stop, find an instruction CC CMR by searching all levels. Use the selected pair. [CMOCA-6-046]
*   **Else search at higher levels** [CMOCA-6-047]
    *   Look for an ICC DeviceLink. If found, stop and use it. [CMOCA-6-048]
    *   Else look for an audit CC CMR. If found, stop, find an instruction CC CMR by searching all levels. Use the selected pair. [CMOCA-6-049]
*   **Else use the default audit CC CMR** [CMOCA-6-050]
    *   Note that there is no default ICC DeviceLink CMR. [CMOCA-6-051]
    *   Find an instruction CC CMR by searching all levels. Use the selected pair. [CMOCA-6-052]

**Figure 11. Selecting Appropriate Color-Conversion CMRs** [CMOCA-6-053]

## Audit/Instruction/Link Color Conversion CMRs

An audit Color Conversion CMR describes device-dependent colors or non-device colors (for example, sRGB) in the presentation data. It provides a way of converting from the input color space to a profile connection space (PCS). [CMOCA-6-054]

An instruction Color Conversion CMR provides a way of converting from the PCS to the device output color space. Note that only certain instruction CMRs are applicable for a given device. For instance:
*   If the device is a CMYK printer, only tone transfer curves with 4 components are applicable. [CMOCA-6-055]
*   Some devices support only bilevel halftone screens, not multilevel screens. [CMOCA-6-056]
*   If the device is an RGB display, it requires color conversions that convert into the RGB of the display. [CMOCA-6-057]

The device may ignore any instruction CMRs that are not applicable, thus making the search path shorter. Similarly, an ICC DeviceLink CMR must have an output color space that matches the color space of the device. If it does not match, the device may ignore it. [CMOCA-6-058]

Table 49 shows which ICC profiles may be used for audit CMRs and which may be used for instruction CMRs. [CMOCA-6-059]

### Table 49. Profile Subsets in Audit and Instruction Color Conversion CMRs

| Type | Audit | Instruction |
| :--- | :--- | :--- |
| Monochrome input profile | Yes | No [CMOCA-6-060]|
| Monochrome display profile | Yes | Yes [CMOCA-6-061]|
| Monochrome output profile | Yes | Yes [CMOCA-6-062]|
| Three-component matrix-based input profile | Yes | No [CMOCA-6-063]|
| Three-component matrix-based display profile | Yes | Yes [CMOCA-6-064]|
| N-component LUT-based input profile | Yes | No [CMOCA-6-065]|
| N-component LUT-based display profile | Yes | Yes [CMOCA-6-066]|
| N-component LUT-based output profiles | Yes | Yes [CMOCA-6-067]|
| ColorSpace conversion profile | Yes | No [CMOCA-6-068]|

Input type profiles describe colors coming from a scanner or digital camera and therefore they are not used as instruction CMRs. [CMOCA-6-069]

## Creating Link Color Conversion CMRs – LinkColorConversion LUT subset

A link CMR describes how to convert directly from an input color space to an output device color space. It links an audit and an instruction CMR. It will define four color conversions, one for each of the possible rendering intents. Both the audit and the instruction CMR have rendering intents specified in their ICC header. The rendering intent in the ICC profile header of the instruction CMR becomes the Default Rendering Intent of the link CMR. [CMOCA-6-070]

The link creation process combines the audit and instruction CMRs and creates four LUTs. Each LUT of the link CMR collapses all the steps of the two (audit and instruction) color conversions into a single multidimensional lookup table. It is possible that some of the LUTs will be identical if not enough information exists to create all versions separately. In this case, the offset for the LUTs could be the same. [CMOCA-6-071]

To create each link LUT, the appropriate color conversion based on rendering intent must be selected from the ICC profiles for both the audit and the instruction Color Conversion CMRs. To create each one of the four link LUTs: [CMOCA-6-072]

1.  The rendering intent of the particular link LUT being created is identified. [CMOCA-6-073]
2.  The color conversion corresponding to that rendering intent is selected from the audit CMR. [CMOCA-6-074]
3.  The color conversion corresponding to that rendering intent is selected from the instruction CMR. [CMOCA-6-075]
4.  The selected audit and instruction color conversions are combined to produce the link LUT for that rendering intent. [CMOCA-6-076]

The selection is based on the rendering intent of the link LUT currently being created and the goal is to use the color conversion from both the audit and the instruction CMR that is for that particular rendering intent. [CMOCA-6-077]

When the color conversion rule for one of the rendering intents is not available, another color conversion must be used. The substitution methods are discussed below. [CMOCA-6-078]

The basic rules for selecting a color conversion that corresponds to a given rendering intent for a given ICC profile are as follows: [CMOCA-6-079]

1.  If the appropriate AT oBxTag (or BT oAxTag) for that rendering intent exists, it is used. (AT oB0Tag is used for perceptual, AT oB1Tag is used for media-relative colorimetric, AT oB2Tag is used for saturation.) [CMOCA-6-080]
2.  If that tag does not exist, the rendering intent in the header of this ICC profile is noted. The tag corresponding to this rendering intent is used. [CMOCA-6-081]
3.  If that tag does not exist, the implementation is device-dependent. [CMOCA-6-082]

For audit CMRs, only the AT oBxTags are considered, not the BT oAxTags. If no AT oBxTags exist:
*   For monochrome profiles, the grayTRCTag is used for all rendering intents. [CMOCA-6-083]
*   For three-component matrix-based profiles, the matrix/TRC combinations are used for all rendering intents. [CMOCA-6-084]

For instruction CMRs, only the BT oAxTags are considered, not the AT oBxTags. If no BT oAxTags exist:
*   For monochrome profiles, the inverse of the grayTRCTag is used for all rendering intents. [CMOCA-6-085]
*   For three-component matrix-based profiles, the inverse of the matrix/TRC transformation, as described in the ICC specification, is used for all rendering intents. [CMOCA-6-086]

In order to create the link LUT for ICC-absolute colorimetric rendering intent, the following are combined:
*   From the audit CMR, use the same tag(s) as were used when creating the link LUT for media-relative colorimetric. Apply a white point conversion based on the audit CMR's ICC mediaWhitePointTag. [CMOCA-6-087]
*   From the instruction CMR, use the same tag(s) as were used when creating the link LUT for media-relative colorimetric. Apply a white point conversion based on the instruction CMR's ICC mediaWhitePointTag. [CMOCA-6-088]

## Tone Transfer Curve and Printer Calibration

Processing of color information involves:
1.  Converting color into the device color space (CMYK on normal printers) [CMOCA-6-089]
2.  Modifying the color for each plane [CMOCA-6-090]
3.  Halftoning each plane [CMOCA-6-091]

The second step, modifying the color for each plane, is typically a one-dimensional conversion and is represented as a 1-D array called a curve. The curve would actually be a set of curves, one for each of the color planes in the color space. This document will use the term “curve” to represent the whole set of curves. Actually, there can be multiple curves, each performing a different function. The curves are used sequentially although, in practice, they might be concatenated to form one curve for improved performance. The effect of each curve is a “delta” to the previous curve. [CMOCA-6-092]

There are two basic uses of these modifying curves:
*   There must be some way to put the device into a well-known state and maintain it in that state. This well-known state should be the state it was in when the default instruction Color Conversion and Halftone CMRs were created. [CMOCA-6-093]
*   The user might want to control the look-and-feel of the output. [CMOCA-6-094]

Each device may handle these processes in different ways, but the following describes one way of dealing with this complexity. There are four curves to be applied sequentially in this example: [CMOCA-6-095]

*   **Tone Transfer Curve (TTC):** This curve is contained in a CMR and can be specified by the user to modify the behavior of a device so that a desired relationship between input and output is achieved. This could include ink limiting, linearization, lightness, contrast, the relationship between the highlights, midtones and shadow regions, or even reverse-video. Only one Tone Transfer Curve is used during the processing of a color object. It is selected using the rules of the CMOCA hierarchy. If no Tone Transfer Curve is specified, the printer default (the identity) will be used. [CMOCA-6-096]
*   **Operator Requested Curve:** This curve allows the user the same control of the look-and-feel of the output as the Tone Transfer Curve. However, this curve is controlled by input from the printer console and is not specified in the data stream. The Operator Requested Curve will be constant for a complete print job or larger boundary. [CMOCA-6-097]
*   **Tone Reproduction Curve:** This curve is used to put the printer into a known state. It compensates for dot gain, printer characteristics, typical humidity, paper characteristics, ink/toner characteristics, speed, etc. There might be different Tone Reproduction Curves in one printer for printing on different sides, different engines, or different media. The assumption is that, after applying the Tone Reproduction Curve, the device is in an optimal state. [CMOCA-6-098]
*   **Calibration Curve:** This curve is used to modify the behavior of a device, returning it to a known state. The assumption is that this known state is the optimal state. The Calibration Curve might be something controlled by the operator or might be automatically controlled within the printer. Changes to the Calibration Curve might need to occur relatively frequently due to changing ink/toner characteristics and changing humidity. The Calibration Curve might be different for each Tone Reproduction Curve in the printer. [CMOCA-6-099]

The Tone Transfer Curve (TTC) and the Operator Requested Curve perform essentially the same function, but the first is transmitted in the data stream and the second is controlled via the printer's user interface. Normally, the two curves would be applied sequentially. However, in some cases, the device might want the Operator Requested Curve to override the TTC, effectively ignoring the TTC. If an applicable TTC is ignored, an error condition exists that is governed by exception ID X'025E..05'. This exception signifies that an “invoked, selected CMR was not used”. The effect of this exception is controlled by the Color Fidelity Triplet and by Error Handling Control (EHC). The Color Fidelity Triplet or EHC can allow processing to continue by using a device default (identity) TTC or can force processing to stop. Reporting of the NACK is also controlled by the Color Fidelity Triplet or the EHC. Thus, by using the Color Fidelity Triplet or EHC, the user can control whether the Operator Requested Curve is allowed to override the TTC. [CMOCA-6-100]

Note that the above discussion assumes that the printer calibration is done digitally, in software, before the color is halftoned. It is also possible to mechanically control the output after it is halftoned. For instance, it might be possible to regulate the amount of ink emitted by an inkjet. This control could be used instead of the Calibration Curve. [CMOCA-6-101]

## Use of Indexed CMRs

Indexed CMRs provide rules about how to render indexed colors. Indexed CMRs apply to indexed colors that are specified using the Highlight color space. They do not apply to indexed colors found within PostScript or other non-IPDS data objects. [CMOCA-6-102]

For Indexed CMRs, both instruction and audit processing modes are valid. However, only Indexed CMRs with a processing mode of instruction will be used. Indexed CMRs that have an audit processing mode specified are ignored. [CMOCA-6-103]

The tags in the Indexed CMR allow the CMR to use various color spaces in the descriptions. These color spaces can be grayscale (monochrome), named colorants, RGB, CMYK, or CIELAB. A conversion from the index into CIELAB must always be provided. If a conversion into another color space is provided, it is used when applicable. For instance, if a conversion into CMYK is provided and the device is a CMYK printer, the conversion is used. The CMYK is assumed to be the device's CMYK and no color conversion CMRs are used. However, if a conversion into RGB is specified for that same CMYK printer, it is not applicable and the conversion into CIELAB will be used instead. In this case, the instruction Color Conversion selected from the hierarchy is used to convert the CIELAB into the output space of the device. [CMOCA-6-104]

If the color palette is given in terms of named colorants, and some of the colorants required to produce a particular index are not available in the device, then the CIELAB palette information will be used instead of the named colorant information. [CMOCA-6-105]

Indexed colors will ultimately be rendered in one of two color spaces:
1.  The output color space of the device (typically CMYK for printers and RGB for displays) [CMOCA-6-106]
2.  A named colorant space, which could include spot colors that are available in the printer and/or colorants from the output color space of the device. [CMOCA-6-107]

In the first case, the Tone Transfer Curve CMR and the Halftone CMR selected from the hierarchy for the output color space are used. In the second case, the number of component named-colorants is noted. The hierarchy is searched for a Tone Transfer Curve and a Halftone that have this same number of components and the CMRs that are found are used. [CMOCA-6-108]

The Indexed CMR to be used is selected using the normal hierarchical rules. Media-matching rules also apply. When the Indexed CMR is selected, its palettes are searched for the index in question. If the index is not found, IPDS exception processing is performed. No attempt is made to look for the index in any other Indexed CMR. [CMOCA-6-109]

If a highlight color index is specified in the data stream, but cannot be resolved by an Indexed CMR, IPDS exception ID X'020E..03' is used. This can occur in two situations:
*   No host-invoked Indexed CMR is found in the hierarchy. [CMOCA-6-110]
*   The required index is not found in the Indexed CMR that was selected. [CMOCA-6-111]

## Allowed Processing Modes

There are three possible processing modes: audit, instruction, and link. Only certain processing modes are allowed with each specific type of CMR. An exception occurs if the processing mode is not valid for the CMR type. The following table shows which processing modes are valid. In addition, the device should ignore (without causing an exception) certain types of CMRs. That is also shown in the table. [CMOCA-6-112]

A CMR is generic if the CMRVersion in the CMR Header is “generic”. Only Tone Transfer Curve CMRs and Halftone CMRs have registered generic versions, so if the type is anything else, an exception occurs. [CMOCA-6-113]

### Table 50. Allowed Processing Modes

| CMR Type | Processing Mode: Non-Generic CMRs - Audit | Processing Mode: Non-Generic CMRs - Instruction | Processing Mode: Non-Generic CMRs - Link | Processing Mode: Generic CMRs - Audit | Processing Mode: Generic CMRs - Instruction | Processing Mode: Generic CMRs - Link |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| Color Conversion | valid | valid | invalid - error | invalid - error | invalid - error | invalid - error [CMOCA-6-114]|
| Tone Transfer Curve | valid | valid | invalid - error | valid - ignored¹ | valid | invalid - error [CMOCA-6-115]|
| Halftone | valid - ignored | valid | invalid - error | valid - ignored¹ | valid | invalid - error [CMOCA-6-116]|
| Link Color Conversion | invalid - error | invalid - error | valid | invalid - error | invalid - error | invalid - error [CMOCA-6-117]|
| Indexed | valid - ignored | valid | invalid - error | invalid - error | invalid - error | invalid - error [CMOCA-6-118]|

**Notes:**
1. Generic Tone Transfer Curves and generic Halftones are ignored because, in order to replace a generic CMR with a specific CMR, you must know the targeted device. This is unknown for an audit CMR. [CMOCA-6-119]

A CMR is passthrough if the CMRVersion in the CMR Header is “pasthru”. Only Color Conversions may be passthrough so, if the CMR type is anything else, an exception occurs. The mode of a passthrough CC CMR must be audit. The CMR is ignored if the mode is specified as instruction. If the mode is link, an exception is generated since that is not valid for any Color Conversion CMRs. [CMOCA-6-120]

## Device Default CMRs

Every device must supply both audit and instruction default CMRs. [CMOCA-6-121]

### Default Instruction CMRs

The required default instruction CMRs are used to process colors in the color space of the device. If the device has multiple appearances—for instance, a printer that can function as both a full-color printer and a monochrome printer—then defaults for both identities must be available in the device. [CMOCA-6-122]

For optimum quality, the device should have default instruction CMRs that are tuned for each type of media that it supports. For instance, a printer that officially supports three different paper types would have three different Color Conversion instruction CMRs available. It is possible that some media have different characteristics on the two sides. In this case, the device would have a default Color Conversion CMR for each side. [CMOCA-6-123]

In some cases, a printer controller might be ripping pages that are sent to one of several print engines. It might be doing load-balancing among the engines. In this case, the printer controller would have a default Color Conversion CMR for each engine, and possibly for each media type on each engine. [CMOCA-6-124]

The default Halftone and Tone Transfer Curve CMRs might also differ depending on the media or engine. [CMOCA-6-125]

The device is responsible for supplying the following device default instruction CMRs:
*   An instruction Halftone CMR that takes the device color space as input. [CMOCA-6-126]
*   An instruction Tone Transfer Curve CMR. [CMOCA-6-127]
*   An instruction Color Conversion CMR from an ICC Profile Connection Space (CIEXYZ or CIELAB) to the device color space. The device must have the ability to convert between CIEXYZ and CIELAB or else it must supply color conversions into the device output space from both. This CMR should have profiles for all rendering intents. [CMOCA-6-128]
*   There are no default Indexed CMRs. [CMOCA-6-129]

The default instruction Color Conversion CMRs can be used when constructing Link Color Conversion CMRs. Therefore, the device manufacturer “publishes” these CMRs. OIDs for these CMRs are created by both the device and the host system wishing to create link CMRs using them. The OIDs are the same in both situations. Thus, the link CMR successfully matches the device's default CMR. [CMOCA-6-130]

Note that printer default CMRs are used only when no other applicable CMR is invoked in the hierarchy. In some cases it might be desirable to ensure that the device uses its defaults since it might have knowledge that the application does not have. For instance, if the sides of the paper have different characteristics, then the device might have different default Color Conversion CMRs for each side. The device will know which side it is printing on and should select the appropriate color conversion. [CMOCA-6-131]

To force the printer to choose an applicable default instruction CMR, no applicable CMR of that type should be invoked within the IPDS hierarchy. The printer may not use its default to override an applicable CMR that is invoked at any level of the hierarchy. [CMOCA-6-132]

### Default Audit CMRs

Audit CMRs are not dependent on the output device or the media, so only one default in each category is required. [CMOCA-6-133]

The device is responsible for supplying the following device default audit CMRs:
*   An audit Color Conversion CMR from input RGB to CIEXYZ or CIELAB. (A display may assume that the RGB is its device RGB.) [CMOCA-6-134]
*   An audit Color Conversion CMR from input CMYK to CIEXYZ or CIELAB. (A full-color printer may assume that the CMYK is its device CMYK.) [CMOCA-6-135]
*   An audit Color Conversion CMR from input grayscale to CIEXYZ or CIELAB. [CMOCA-6-136]
*   An audit Color Conversion CMR from YCrCb or YCbCr to CIEXYZ or CIELAB. [CMOCA-6-137]
*   Audit Tone Transfer Curve CMRs that apply for varying number of components. [CMOCA-6-138]

If any other color spaces such as Luv, HSV, HLS, Yxy, or CMY are used within the data stream, the application is responsible for providing a color conversion CMR. The device may generate an exception if an applicable Color Conversion CMR is not supplied. [CMOCA-6-139]

The following audit CMR defaults have been architected: [CMOCA-6-140]

*   **RGB:** In an AFP printer, this is SMPTE-C RGB. In a display, this represents its device RGB. [CMOCA-6-141]
*   **CMYK:** In an AFP full-color printer, this represents the CMYK of the presentation device. In an AFP monochrome printer, this is SWOP CMYK. In an AFP full-color printer working in grayscale mode, the CMYK represents the CMYK of the device when it is in full-color mode. In a display, this is SWOP CMYK. [CMOCA-6-142]
*   **Grayscale:** The Grayscale default applies when the color space has one component and more than 1 bit per pixel. This includes color spaces that are specified as YCbCr where only the Y component is present. In an AFP full-color printer, C = M = Y = 0; K = 1 – gray_value. In an AFP monochrome printer, the grayscale is the device's grayscale. In a display, R = G = B = gray_value. [CMOCA-6-143]
*   **YCbCr or YCrCb:** The default YCbCr is based on CCIR Recommendation 601-1 but components are normalized so as to occupy the full 256 levels of an 8-bit encoding. This version of YCbCr is used by TIFF and JFIF as their default. [CMOCA-6-144]

**Notes:**
*   The default white point for both CIELAB and CIEXYZ is D50. [CMOCA-6-145]
*   **Halftone:** No architected default. [CMOCA-6-146]
*   **Tone Transfer Curves:** Identity. [CMOCA-6-147]
*   **Indexed:** No architected default. [CMOCA-6-148]

### Default CMRs to Replace Generic CMRs

Instruction Halftone CMRs and instruction Tone Transfer Curve CMRs can be generic. Generic CMRs must be replaced by device-specific CMRs. The device is required to have its own device-specific versions of all the generic CMRs that are registered in the Color Management Object Content Architecture (see Appendix B, “Generic CMR Name Registry”). If the device does not recognize a generic CMR name, it NACKs using exception ID X'025E..04'. [CMOCA-6-149]

### Passthrough Audit Color Conversion CMRs

An audit Color Conversion CMR can specify the version to be “pasthru”. Passthrough CMRs are defined only for Color Conversion CMRs. Prop4, the color space for a CC CMR, should be specified. When this passthrough CMR is invoked and Prop4 is the same as the color space of the device, then the color values will be passed through without color conversion. If Prop4 is not the same as the device color space, or not specified, then the passthrough CC CMR is ignored. [CMOCA-6-150]

The CMRVersion in the CMR header indicates whether a CMR is passthrough. Prop4 in the header indicates the color space. Other Prop fields are unspecified. A passthrough Color Conversion is valid only as an audit CMR. It is ignored if its mode is instruction. An error is returned if the mode is link. [CMOCA-6-151]

A passthrough CC CMR is treated like other audit CC CMRs in terms of selecting an audit CC CMR from the hierarchy. A passthrough CC CMR has no data. There is no device-specific CMR that can be substituted for the passthrough CC CMR. It merely instructs the device to avoid doing any color conversion. [CMOCA-6-152]

## Matching Media Type of CMR With Media Type of Device

In some cases, it is important to know if a CMR is media-specific. It is media-specific if the media is completely specified. It is not media-specific in the following cases:
*   If the target device is a display screen and the MediaBrightness is not specified or is not a valid entry. [CMOCA-6-153]
*   If the target device is a printer and one or more of the four media fields in the header are not specified or are invalid. [CMOCA-6-154]

When an instruction CMR is needed, the hierarchy is searched to find an applicable CMR. There are two possible outcomes:
*   An applicable CMR is found in the hierarchy and is selected. [CMOCA-6-155]
*   No CMR is selected from the hierarchy and a default CMR must be used. [CMOCA-6-156]

(Refer to the specification for detailed rules on matching media characteristics.) [CMOCA-6-157]

## Treatment of Named and Highlight Colors

The AFP architecture supports OCA named colors such as Blue, Red, and Brown that are inherently device-dependent. As such, these named colors should not be used when an exact color is required. AFP architecture has recommended RGB values for each OCA named color. It is recommended that these RGB values be interpreted as SMPTE-C values and mapped to a device's output color space. [CMOCA-6-158]

It is recommended that OCA Black (X'0008'), presented on a CMYK output device, be rendered as C=M=Y=X'00' and K=X'FF'. [CMOCA-6-159]

The AFP architecture also supports a Highlight color space. It can be used in two ways:
*   The highlight color number specifies the spot color to use. The range is X'0000'–X'00FF'. [CMOCA-6-160]
*   The highlight color number is interpreted as an index into a palette. The range is X'0100'–X'FFFF'. The Indexed CMR describes which colorants are used to render that color. [CMOCA-6-161]

## Color Conversion Profiles Within TIFF, JFIF, and GIF

Some presentation data objects contain internal color management information. ICC profiles can be embedded within TIFF, JFIF, and GIF. [CMOCA-6-162]

When presentation data objects contain internal color management information, the device will use internal audit-like color management information, if any, when no applicable audit CMR is invoked with the object. The object-level audit CMR has priority over internal color management information. However, the internal color management information has priority over any audit CMRs at the page, home state, or device-default levels. [CMOCA-6-163]

So the hierarchy is:
1.  Object-level audit CMR [CMOCA-6-164]
2.  ICC profile within the object [CMOCA-6-165]
3.  Page-level audit CMR [CMOCA-6-166]
4.  Homestate-level audit CMR [CMOCA-6-167]
5.  Printer default audit CMR [CMOCA-6-168]

## CMR Usage Within EPS, PDF

### Audit Color Conversions

PostScript supports the following input color spaces:
*   **Device-Independent Color Spaces:** CIEBasedABC, CIEBasedA, CIEBasedDEF, CIEBasedDEFG [CMOCA-6-169]
*   **Device-Dependent Color Spaces:** DeviceGray, DeviceRGB, DeviceCMYK [CMOCA-6-170]
*   **Special Color Spaces:** Pattern, Indexed, Separation, DeviceN [CMOCA-6-171]

### Instruction Color Conversions

The appropriate printer-default instruction Color Conversion CMR will be used for the default Color Rendering Dictionary (CRD).
*   If a CRD is specified within the PostScript data stream, it will be used instead of the default CRD. [CMOCA-6-172]
*   If an instruction Color Conversion CMR is associated directly with the EPS/PDF object, it will override both the default CRD and any CRD specified within the EPS/PDF object. [CMOCA-6-173]

### Link Color Conversion CMRs

Link Color Conversion CMRs should be used when processing EPS/PDF if a device reports support for Link CC CMRs and it reports that it can reliably apply CMRs to EPS/PDF. [CMOCA-6-174]

### Halftones

The appropriate printer-default instruction Halftone CMR will be used for the default EPS/PDF halftone.
*   If a halftone is specified within the EPS/PDF object, it will be used instead of the default EPS/PDF halftone. [CMOCA-6-175]
*   If an instruction Halftone CMR is associated directly with the EPS/PDF object, it will override both the default halftone and any halftone specified within the EPS/PDF object. [CMOCA-6-176]

### Tone Transfer Curves

The printer default instruction Tone Transfer Curve is the identity so it will have no effect on EPS/PDF.
*   If a Transfer Function is specified within the EPS/PDF object, it will be used. [CMOCA-6-177]
*   If an instruction Tone Transfer Curve CMR is associated directly with the EPS/PDF object, it will override any Transfer Function specified within the EPS/PDF object. [CMOCA-6-178]

## Caveat

Some devices cannot completely, reliably ensure that a selected CMR is actually applied. This is because some EPS/PDF objects can be created in such a way that it is not possible to override the parameters. [CMOCA-6-179]

## Different Encodings of CIELAB

CIELAB is defined as follows:
*   **L** has a value between 0.0 and 100.0 [CMOCA-6-180]
*   **a** has a value between –128.0 and 127.0 [CMOCA-6-181]
*   **b** has a value between –128.0 and 127.0 [CMOCA-6-182]

The encoding for L consistently maps the range [0.0, 100.0] to [X'0000', X'FFFF'] or to [X'00', X'FF'] depending on the number of bytes in the representation. [CMOCA-6-183]

Different architectures convert the range for a and b into 1-byte or 2-byte values differently. Care must be taken when using values from the different architectures to ensure that they are converted to a common encoding. [CMOCA-6-184]

## Implications for Drivers

It is preferable for the host to suppress the downloading of CMRs that are not applicable to the device, but this is not required. [CMOCA-6-185]

## Generic CMRs Versus Device-Specific CMRs

Certain instruction CMRs can be generic. Generic CMRs are defined for only two CMR types: Halftone CMRs and Tone Transfer Curve CMRs. The generic CMR must always be replaced by a device-specific CMR by either the server or the device. [CMOCA-6-186]

## Partial Support of TTC and HT CMRs

In some cases, a device provides only partial support for certain types of CMRs. The device needs to declare support in the IPDS STM reply to enable the host to transmit the CMR to the device. [CMOCA-6-187]
