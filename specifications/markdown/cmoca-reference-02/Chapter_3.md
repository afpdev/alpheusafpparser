# Chapter 3. Color Management Resource (CMR)

A CMR consists of a header followed by CMR data. [CMOCA-3-001]

## General Color Conversion Concepts

To start, for simplicity, assume that the source data is specified in a device-independent color space (that is, an ICC Profile Connection Space (PCS) such as CIEXYZ or CIELAB). The procedure for producing output uses the following sequence: [CMOCA-3-002]

**Figure 4. Procedure for Producing Halftone Output from PCS** [CMOCA-3-003]

1.  Color Convert from PCS to color space of output device [CMOCA-3-004]
2.  Color Calibration [CMOCA-3-005]
3.  Halftone Data [CMOCA-3-006]

When color data has been processed using the above sequence, the resulting object can be stored in a database. It might be useful to keep an audit trail of the operations that were performed to create the object. This audit information can be merely descriptive, or it can be used to undo some of the operations performed on the object and thus restore it to the original form when it was expressed in the PCS color space. In this case, the inverse of each function is applied in this sequence: [CMOCA-3-007]

**Figure 5. Procedure for Converting Input Data to Full Color in PCS** [CMOCA-3-008]

1.  Undo Halftoning (Note: typically not stored) [CMOCA-3-009]
2.  Undo Color Calibration [CMOCA-3-010]
3.  Undo Color Conversion (i.e. convert source color space to PCS) [CMOCA-3-011]

Here is a more mathematical way of expressing that sequence:
(halftone data)⁻¹ → (color calibration)⁻¹ → (color conversion)⁻¹ [CMOCA-3-012]

In actuality, halftoning is not typically undone. The halftoned version is typically not stored in the database. Further, undoing the halftoning is very complex. Note also that objects prepared for a display are not halftoned. Therefore, this document assumes that no attempt will be made to go from halftoned data back to the object represented in the PCS color space. [CMOCA-3-013]

Now, let's assume that the source data is specified in a device-dependent color space and it is desired to render it on an output device. The source data must be converted to the color space of the device. This involves the following combined sequence: [CMOCA-3-014]

**Figure 6. Procedure for Creating Halftone Data from Device-Specific Color Data** [CMOCA-3-015]

1.  Convert from source color space to PCS [CMOCA-3-016]
2.  Undo source Color Calibration [CMOCA-3-017]
3.  Color Calibration (Target) [CMOCA-3-018]
4.  Convert from PCS to color space of output device [CMOCA-3-019]
5.  Halftone Data [CMOCA-3-020]

Information that relates to the creation of the source data is referred to as an **audit CMR**. It might describe how the source data was created, as in an audit halftone. Or it might describe how to undo an operation that was used to create the source data. For instance, an audit color conversion profile tells how to convert from the color space of the source data to a PCS. Audit tone transfer curves have been architected to describe an action that was done to create the source data. There is also an optional tag that describes how to undo the tone transfer curve. [CMOCA-3-021]

The above discussion assumes that the color was specified using a color space. It is also possible to specify a color using an index and then define how to produce that color using a palette that tells which component inks/toners are used, in which concentration. An Indexed CMR is used to define how to produce the indexed colors. [CMOCA-3-022]

## Understanding the Use of CMRs

There are seven basic uses of the CMR that must be considered: [CMOCA-3-023]

1.  **Audit Halftone** can be attached to an image that has been halftoned and indicates which halftone was used. [CMOCA-3-024]
2.  **Audit Tone Transfer Curve** indicates a one-dimensional conversion that was applied to the input color before halftoning. The inverse curve needs to be applied to get back to the input color space. [CMOCA-3-025]
3.  **Audit Color Conversion** is used to convert from the input color space to a profile connection space. [CMOCA-3-026]
4.  **Instruction Color Conversion** is used to convert from the profile connection space to the color space of the output device. [CMOCA-3-027]
5.  **Instruction Tone Transfer Curve** is a one-dimensional conversion applied to the output color before halftoning (or a set of 1-D conversions, one for each color component). [CMOCA-3-028]
6.  **Instruction Halftone** is used to halftone the output colored data. [CMOCA-3-029]
7.  **Link Color Conversion** provides an efficient means for converting directly from the input color space to the output color space and can be substituted for the Audit Color Conversion/Instruction Color Conversion pair. A special type of Link Color Conversion, ICC DeviceLink, converts directly from input to output space without reference to an audit/instruction pair. [CMOCA-3-030]

When a presentation device is processing data using the CMR system, these are the basic steps that are followed. Note that device-default CMRs are used if an applicable CMR is not explicitly invoked. If a CMR is ignored, the device must accept it but does not error check or process the contents. [CMOCA-3-031]

1.  The device ignores an Audit Halftone. It might be useful information to store with the image in a database, but it is not currently used in the presentation device since undoing halftoning is not a simple process. [CMOCA-3-032]
2.  The device applies an applicable Audit Tone Transfer Curve. If an inverse tone transfer curve is specified, it is used. Otherwise, the function specified by the tone transfer curve must be inverted before it is applied. Note that the inverse tone transfer curve is not a well-defined function. [CMOCA-3-033]
    **Note:** It is strongly recommended that any audit Tone Transfer Curve that is used should be invoked at the object level. This helps avoid ambiguity that might occur since the selection of the TTC is based only on the number of components in the color space.
3.  If an applicable ICC DeviceLink exists, it is used. This link color conversion converts the input color space to the output color space of the current device. If this CMR is selected for use, the next two steps (selection of Audit Color Conversion and Instruction Color Conversion CMRs) are skipped. [CMOCA-3-034]
4.  If an applicable Audit Color Conversion exists, it is used. This audit Color Conversion converts the input color space (RGB, CMYK, grayscale...) to a profile connection space. [CMOCA-3-035]
5.  An Instruction Color Conversion is used to convert from the profile connection space into the output color space of the current device. [CMOCA-3-036]
6.  If an Instruction Tone Transfer Curve exists, the color is modified using it. [CMOCA-3-037]
7.  The colored data is halftoned using an Instruction Halftone. [CMOCA-3-038]

In reality, the processing of sequential steps can be combined to improve performance. A Link Color Conversion CMR can be used in place of a combined audit/instruction color conversion pair. Chapter 6 discusses how CMRs are used in more detail. [CMOCA-3-039]

The Indexed CMR does not fit into the scheme discussed above. It is used to define how to produce indexed color. [CMOCA-3-040]

## CMR Syntax

This is the syntax of a CMR. Bytes 0–163 represent the CMR Header. Each field in the CMR header has a fixed length. The fields from byte 10–155 are character data encoded as UTF-16BE. Data in these fields are left aligned. If the number of the characters in these fields is smaller than the field length, the remaining bytes will be padded with @ (X'0040'). Any field from byte 44–139 is “unspecified” if it is filled with @ characters. [CMOCA-3-041]

The CMR name is the concatenated fields in bytes 10–155, exactly in the order specified in the CMR header. The CMR name is 73 characters (146 bytes) long. It should be unique, since it is the name that will be used in the MO:DCA data stream to reference the resource. For example, if the CMR header fields are: [CMOCA-3-042]

Alias=JohnMay4, CMRType=HT, CMRVersion=1.2, ManufacturerName=IBM, DeviceType=4100, DeviceModel=PD1, MediaBrightness=94, MediaColor=wht, MediaFinish=gl, MediaWeight=90, number of device levels=2, Halftone type=rnd, line screen frequency=141, resolution=600, and rotation=proc [CMOCA-3-043]

then the CMR name is:
`JohnMay4HT001.200IBM@@4100@@PD194@whtgl90@2@@@@rnd@@@141@600@proc@@@@@@@@` [CMOCA-3-044]

### Table 4. CMR Object Syntax

| Length | Offset | Type | Name | Range | Meaning | M/O |
| :---: | :--- | :--- | :--- | :--- | :--- | :---: |
| 4 | 0–3 | 4-byte UBIN | Length | X'000000A4'–X'FFFFFFFF' | CMR length, including length field [CMOCA-3-045] | M |
| 4 | 4–7 | CODE | CMRSig | X'434D5239' | Signature of this CMR [CMOCA-3-046] | M |
| 2 | 8–9 | | Reserved | X'0000' | Reserved; should be set to zero [CMOCA-3-047] | M |
| **(CMR Name starts here. It is composed of bytes 10–155.)** [CMOCA-3-048] | | | | | | |
| 16 | 10–25 | UTF16 | CMRAlias | No restriction | Human-readable alias [CMOCA-3-049] | M |
| 4 | 26–29 | UTF16 | CMRType | CC (X'0043 0043') | Color Conversion [CMOCA-3-050] | M |
| | | | | DL (X'0044 004C') | ICC DeviceLink Color Conversion [CMOCA-3-051] | |
| | | | | HT (X'0048 0054') | Halftone [CMOCA-3-052] | |
| | | | | IX (X'0049 0058') | Indexed [CMOCA-3-053] | |
| | | | | LK (X'004C 004B') | Link Color Conversion [CMOCA-3-054] | |
| | | | | TC (X'0054 0043') | Tone Transfer Curve [CMOCA-3-055] | |
| 14 | 30–43 | UTF16 | CMRVersion | ddd.ddd | CMRVersion number [CMOCA-3-056] | M |
| | | | | AFP .ddd | Special AFP version number [CMOCA-3-057] | |
| | | | | generic | “generic” [CMOCA-3-058] | |
| | | | | pasthru | “passthrough” [CMOCA-3-059] | |
| 10 | 44–53 | UTF16 | Manufacturer Name | See description | Name of the manufacturer [CMOCA-3-060] | M |
| 12 | 54–65 | UTF16 | DeviceType | See description | Type of the device [CMOCA-3-061] | M |
| 6 | 66–71 | UTF16 | DeviceModel | See description | Model of the device [CMOCA-3-062] | M |
| 6 | 72–77 | UTF16 | MediaBrightness | 0–100 | For print media, percentage of light reflected [CMOCA-3-063] | M |
| | | | | Zxy | For screen, a CIE illuminant [CMOCA-3-064] | |
| 6 | 78–83 | UTF16 | MediaColor | | Color of the media: [CMOCA-3-065] | M |
| | | | | blu | blue [CMOCA-3-066] | |
| | | | | buf | buff [CMOCA-3-067] | |
| | | | | gdr | goldenrod [CMOCA-3-068] | |
| | | | | grn | green [CMOCA-3-069] | |
| | | | | gry | gray [CMOCA-3-070] | |
| | | | | ivy | ivory [CMOCA-3-071] | |
| | | | | noc | no-color [CMOCA-3-072] | |
| | | | | org | orange [CMOCA-3-073] | |
| | | | | pnk | pink [CMOCA-3-074] | |
| | | | | red | red [CMOCA-3-075] | |
| | | | | wht | white [CMOCA-3-076] | |
| | | | | ylw | yellow [CMOCA-3-077] | |
| | | | | custom | three upper-case characters [A,Z] [CMOCA-3-078] | |
| | | | | @@@ | not specified [CMOCA-3-079] | |
| 4 | 84–87 | UTF16 | MediaFinish | | Surface characteristics: [CMOCA-3-080] | M |
| | | | | cm | commodity [CMOCA-3-081] | |
| | | | | ct | coated [CMOCA-3-082] | |
| | | | | gl | glossy [CMOCA-3-083] | |
| | | | | hg | high-gloss [CMOCA-3-084] | |
| | | | | mt | matte [CMOCA-3-085] | |
| | | | | no | none [CMOCA-3-086] | |
| | | | | np | newsprint [CMOCA-3-087] | |
| | | | | sg | semi-gloss [CMOCA-3-088] | |
| | | | | st | satin [CMOCA-3-089] | |
| | | | | tr | treated [CMOCA-3-090] | |
| | | | | custom | two upper-case characters [A,Z] [CMOCA-3-091] | |
| | | | | @@ | not specified [CMOCA-3-092] | |
| 6 | 88–93 | UTF16 | MediaWeight | 1–999 | The basic weight of the paper [CMOCA-3-093] | M |
| 10 | 94–103 | UTF16 | Prop1 | See description | CMRType property-specific field 1 [CMOCA-3-094] | M |
| 12 | 104–115 | UTF16 | Prop2 | See description | CMRType property-specific field 2 [CMOCA-3-095] | M |
| 8 | 116–123 | UTF16 | Prop3 | See description | CMRType property-specific field 3 [CMOCA-3-096] | M |
| 8 | 124–131 | UTF16 | Prop4 | See description | CMRType property-specific field 4 [CMOCA-3-097] | M |
| 8 | 132–139 | UTF16 | Prop5 | See description | CMRType property-specific field 5 [CMOCA-3-098] | M |
| 16 | 140–155 | UTF16 | @@@@@@@@ | | Reserved [CMOCA-3-099] | M |
| **(CMR Name ends here. It is composed of bytes 10–155.)** [CMOCA-3-100] | | | | | | |
| 8 | 156–163 | | X'00...00' | | Reserved; should be set to zero [CMOCA-3-101] | M |
| Variable | 164–end | | CMRData | Any | Resource data [CMOCA-3-102] | O |

**Notes:**
1. **M/O** denotes a mandatory or optional field. [CMOCA-3-103]
2. **UTF16** denotes UTF-16BE. [CMOCA-3-104]

## CMR Header Semantics

**Length**
The length of the complete CMR, including the Length parameter. Length may be 164 (X'000000A4') bytes up to X'FFFFFFFF'. [CMOCA-3-105]

**CMRSig**
The signature of the CMR that allows it to be easily recognized. It will be three ASCII characters “CMR” followed by X'39', that is, X'434D5239'. [CMOCA-3-106]

**CMRAlias**
Eight-character user-defined string to enable an easy way of identifying the CMR. [CMOCA-3-107]

**CMRType**
Five CMRTypes are defined in this Color Management Object Content Architecture. They are: Halftone, Tone Transfer Curve, Color Conversion, Link Color Conversion, and Indexed. The value of the CMRType must be specified in the header or an exception will be generated. Note that a Link Color Conversion CMR has two possible identifiers in this field: LK for LinkColorConversion LUT subset and DL for ICC DeviceLink subset. [CMOCA-3-108]

**CMRVersion**
The CMRVersion number consists of a major version number (an integer of 1–3 digits) and a minor version number (an integer of 1–3 digits), separated by a decimal point (. =DECIMAL POINT=X'002E'). If the number of digits is smaller than 3, zeroes will be padded to the left side of the major number or to the right side of the minor number. For example, if the version number is 1.20 then the value of the CMRVersion is 001.200. [CMOCA-3-109]

A value of “AFP .ddd” is used to specify a minor version number along with “AFP” to indicate that the CMR is a standard Color Conversion CMR that is supported by the AFP Consortium. The supported standard color spaces will be spaces like SWOP, CMYK, and sRGB. [CMOCA-3-110]

A value of “generic” (X'0067 0065 006E 0065 0072 0069 0063') in this field identifies a generic CMR. Only Halftone and Tone Transfer Curve CMRs may be identified as generic. CMR data in generic CMRs is optional and is not used in AFP color management systems. [CMOCA-3-111]

A value of “pasthru” (X'0070 0061 0073 0074 0068 0072 0075') identifies a color space that should not be color-converted. Only Color Conversion CMRs may be identified as passthrough. There is no data in a passthrough CMR. [CMOCA-3-112]

The value of the CMRVersion must be specified in the header. The CMRVersion tracks changes besides the changes in the device-specific fields, media-specific fields, and the CMRType property-specific fields. It reflects changes of algorithm, toner, and so on. [CMOCA-3-113]

### Device-Specific Fields

For IPDS receivers, the ManufacturerName, DeviceType, and DeviceModel values must be provided in accordance with the IPDS description of the Product Identifier self-defining field of the XOH Obtain Printer Characteristics (OPC) reply. Refer to the Intelligent Printer Data Stream Reference. The field descriptions are as follows: [CMOCA-3-114]

*   **ManufacturerName:** Name of the manufacturer. [CMOCA-3-115]
*   **DeviceType:** Device type of the printer that corresponds to the device type imprinted on the serial number plate that is physically attached to the printer. [CMOCA-3-116]
*   **DeviceModel:** Model number of the printer that corresponds to the model number imprinted on the serial number plate that is physically attached to the printer. [CMOCA-3-117]

For the non-IPDS devices, a maximum of five characters are allowed for the ManufacturerName. The stock symbol (maximum five characters), a unique name assigned by stock exchanges worldwide, is recommended to be used for the ManufacturerName. The DeviceType and DeviceModel have to be unique and meaningful for the devices. Alternatively, the ICC Manufacture ASCII Signature and Device ASCII Signature can be used for the ManufacturerName and the DeviceModel. [CMOCA-3-118]

**Implementation notes:** [CMOCA-3-119]

1.  If the DeviceType is unspecified (@@@@@@), then it automatically matches the DeviceType of the target device. Similarly, if the DeviceModel is unspecified (@@@), then it automatically matches the DeviceModel of the target device. The DeviceType and DeviceModel are sometimes used by print servers to determine which CMRs to send to the presentation device. In particular, link CMRs are targeted for a particular device based on the DeviceType and DeviceModel of the instruction Color Conversion CMR. Multiple link CMRs can be associated with (or mapped to) an audit CC CMR in the CMR RAT. The link CMRs that are sent down to the device are determined by finding matches with the DeviceType and DeviceModel of the target device. Furthermore, Generic Tone Transfer Curve and Halftone CMRs can have mapped device-specific CMRs in the CMR RAT; such mapped CMRs are sent to the device if the DeviceType and DeviceModel in the mapped CMR match the DeviceType and DeviceModel of the target device. In some situations, it is acceptable to let the CMR header values for DeviceType and DeviceModel be unspecified (@@@@@@ or @@@). For example, CMRs that will be used only as audit CMRs can have unspecified values for DeviceType and DeviceModel. If a link CMR or a device-specific HT or TTC CMR is associated with another CMR in the CMR RAT and does not specify a DeviceType and/or DeviceModel, the unspecified parameter(s) match the DeviceType and/or DeviceModel of any target printer. [CMOCA-3-120]
2.  The device types and model numbers specified in the XOH-OPC reply and in the CMR header's DeviceType might not use the same format. For instance, for the InfoPrint 4100, the XOH-OPC reply for the device type would be “004100” encoded using EBCDIC. In the CMR header, the DeviceType is padded with “@” on the right. Therefore, depending on the input provided to the Installer, the CMR DeviceType field might be “004100” or “4100@@” encoded using UTF-16BE. Tools that compare the device type in the XOH-OPC reply and in the CMR header must be prepared to indicate a match taking into account the differences in padding practices. [CMOCA-3-121]

### Media-Specific Fields

Media-specific fields describe the media and consist of four attributes: media brightness, media color, media finish, and media weight. The values for the MediaColor and the MediaFinish are consistent with the values defined by the Internet Printing Protocol (IPP) of the Printer Working Group (PWG). If the target device is a display, only media brightness is specified. [CMOCA-3-122]

To use an instruction CMR, its media type must match the media currently being used by the device. Similarly, in order to use an ICC DeviceLink CMR, its media attributes must match the device's media attributes. See “Matching Media Type of CMR With Media Type of Device” for a discussion of this requirement. [CMOCA-3-123]

*   **MediaBrightness:** For print media, indicates the percentage of light reflected from the media. The brightness is measured with a brightmeter machine. The scale is based on the TAPPI GE scale in the US and the ISO scale in the rest of the world. The ISO scale is usually about two units higher than the GE value. For example, 100 ISO brightness is equivalent to 98 brightness on the GE scale. In order to ensure that the CMR's media type matches the media currently being used in the device, the scale that is used to specify each value must be the same. For screens, the brightness is defined as the CIE standard illuminant as Zxy, where Z is a capitalized letter, and xy is a two-digit number (see ISO/CIE 10526:1999: CIE standard illuminants for colorimetry). For example, D50, D65, etc. [CMOCA-3-124]
*   **MediaColor:** Indicates the color of the media being specified. CMOCA-recommended values exist to encourage interoperability; a CMOCA-recommended value should be used if appropriate for a CMR associated with a specific media. The value “noc” means transparency. Custom values may be defined by the administrator. There is no restriction on what value may be entered for this field as it is not checked for validity. [CMOCA-3-125]
*   **MediaFinish:** Indicates the surface characteristics of the media. CMOCA-recommended values exist to encourage interoperability; a CMOCA-recommended value should be used if appropriate for a CMR associated with a specific media. The value “no” means no coating. Custom values may be defined by the administrator. There is no restriction on what value may be entered for this field as it is not checked for validity. [CMOCA-3-126]
*   **MediaWeight:** Indicates the weight of the media rounded to the nearest whole number of grams per square meter. [CMOCA-3-127]

### CMRType Property-Specific Fields

#### CMRType=Halftone

**Note:** These fields are informational only. They are not checked for validity. Any value may be entered in the Prop fields since no error checking is done. [CMOCA-3-128]

*   **Prop1: Number of Device Levels** [CMOCA-3-129]
    Defines the number of device levels. The number should be greater than 1. For example, if the number of device levels is equal to 3, then the device levels are 0, 1, and 2. If the number of device levels is different for different components, this property represents the maximum value.
*   **Prop2: Halftone Type** [CMOCA-3-130]
    Defines the halftone type. Halftone types are divided into four major categories: clustered-dot, stochastic, dispersed, and error diffusion. The dot shape is used to specify the type of the clustered-dot, and the error diffusion filter name is used to specify the type of error diffusion halftone. [CMOCA-3-131]

**Table 5. Halftone Types** [CMOCA-3-132]

| Value | Meaning |
| :--- | :--- |
| **rnd@@@** | Round dot for the clustered-dot halftone [CMOCA-3-133] |
| **sqr@@@** | Square dot for the clustered-dot halftone [CMOCA-3-134] |
| **dia@@@** | Diamond dot for the clustered-dot halftone [CMOCA-3-135] |
| **rhm@@@** | Rhombus dot for the clustered-dot halftone [CMOCA-3-136] |
| **elp@@@** | Elliptical dot for the clustered-dot halftone [CMOCA-3-137] |
| **eud@@@** | Euclidean dot for the clustered-dot halftone [CMOCA-3-138] |
| **lin@@@** | Line shape dot for the clustered-dot halftone [CMOCA-3-139] |
| **sto@@@** | Stochastic halftone [CMOCA-3-140] |
| **dsp@@@** | Dispersed halftone [CMOCA-3-141] |
| **erd@@@** | Unspecified error diffusion halftone [CMOCA-3-142] |
| **f-d@@@** | Floyd-Steinberg error diffusion halftone [CMOCA-3-143] |
| **jjn@@@** | Jarvis-Judice-Ninke error diffusion halftone [CMOCA-3-144] |
| **stu@@@** | Stucki error diffusion halftone [CMOCA-3-145] |
| **brk@@@** | Burkes error diffusion halftone [CMOCA-3-146] |
| **sra@@@** | Sierra error diffusion halftone [CMOCA-3-147] |
| **s-a@@@** | Stevenson Arce error diffusion halftone [CMOCA-3-148] |

*   **Prop3: Line Screen Frequency** [CMOCA-3-149]
    Defines the maximum line screen frequency of all the component screens. Line frequency is specified in terms of the printer's resolution. A line screen frequency of zero should be used for stochastic, dispersed, and error diffusion type halftones.
*   **Prop4: Resolution** [CMOCA-3-150]
    Defines the printer resolution in dots per inch in the array width (screen width) direction. Halftone screens are normally designed for different printer resolutions. If the resolutions differ for different components, this property represents the maximum value.
*   **Prop5: Rotation** [CMOCA-3-151]
    Defines the orientation of the halftone. There are three possible values: orientation independent, along the scan direction, and along the process direction. [CMOCA-3-152]

**Table 6. Halftone Rotations** [CMOCA-3-153]

| Value | Meaning |
| :--- | :--- |
| **indp** | Orientation independent [CMOCA-3-154] |
| **scan** | Scan direction [CMOCA-3-155] |
| **proc** | Process direction [CMOCA-3-156] |

#### CMRtype=Tone Transfer Curve

**Note:** These fields are informational only. They are not checked for validity. Any value may be entered in the Prop fields since no error checking is done. [CMOCA-3-157]

*   **Prop1: Profile/Device Class Signature** [CMOCA-3-158]
    The definition of the Device Class Signature is consistent with the definition in the ICC header. There are four basic profile/device classes: Input, Display, Output, and ColorSpace Conversion. [CMOCA-3-159]

**Table 7. ICC Profile/Device Classes for Tone Transfer Curve CMRs** [CMOCA-3-160]

| Value | Meaning |
| :--- | :--- |
| **scnr@** | Input Device [CMOCA-3-161] |
| **mntr@** | Display Device [CMOCA-3-162] |
| **prtr@** | Output Device [CMOCA-3-163] |
| **spac@** | ColorSpace Conversion [CMOCA-3-164] |

*   **Prop2: Look-and-Feel** [CMOCA-3-165]
    Look-and-Feel produced in the output when this Tone Transfer Curve is applied. See Appendix B, “Generic CMR Name Registry” for an explanation of what these values mean. [CMOCA-3-166]

**Table 8. Look-and-Feel Values** [CMOCA-3-167]

| Value | Meaning |
| :--- | :--- |
| **hilmid** | Highlight Midtone [CMOCA-3-168] |
| **standd** | Standard [CMOCA-3-169] |
| **dark@@** | Dark [CMOCA-3-170] |
| **accutn** | Accutone [CMOCA-3-171] |

*   **Prop3: Halftone Characterization** [CMOCA-3-172]
    This Tone Transfer Curve was designed to work with a particular Halftone. This value is used to identify that Halftone. For clustered-dot halftones, it is the line screen frequency (Prop3 of Halftone). For other types of halftones, it is the halftone type (Halftone Prop2 but just the first four characters).
*   **Prop4: Reserved for future use.** [CMOCA-3-173]
*   **Prop5: Reserved for future use.** [CMOCA-3-174]

#### CMRType=Color Conversion

**Note:** These fields are informational only. They are not checked for validity. Any value may be entered in the Prop fields since no error checking is done. [CMOCA-3-175]

*   **Prop1: Profile/Device Class Signature** [CMOCA-3-176]
    It is consistent with the definition of the Profile/Device Class Signature in the ICC header. [CMOCA-3-177]

**Table 9. ICC Profile/Device Classes for Color Conversion CMRs** [CMOCA-3-178]

| Value | Meaning |
| :--- | :--- |
| **scnr@** | Input Device profile [CMOCA-3-179] |
| **mntr@** | Display Device profile [CMOCA-3-180] |
| **prtr@** | Output Device profile [CMOCA-3-181] |
| **spac@** | ColorSpace Conversion profile [CMOCA-3-182] |

*   **Prop2: Reserved for future use.** [CMOCA-3-183]
*   **Prop3: Reserved for future use.** [CMOCA-3-184]
*   **Prop4: Color Space of Data** [CMOCA-3-185]
    It is consistent with the definition of the Color Space of Data in the ICC header. Table 10 shows the possible values. [CMOCA-3-186]

**Table 10. The ICC Color Space of Data** [CMOCA-3-187]

| Value | Meaning |
| :--- | :--- |
| **XYZ@** | XYZData [CMOCA-3-188] |
| **Lab@** | labData [CMOCA-3-189] |
| **Luv@** | luvData [CMOCA-3-190] |
| **YCbr** | YCbCrData [CMOCA-3-191] |
| **Yxy@** | YxyData [CMOCA-3-192] |
| **RGB@** | rgbData [CMOCA-3-193] |
| **GRAY** | grayData [CMOCA-3-194] |
| **HSV@** | hsvData [CMOCA-3-195] |
| **HLS@** | hlsData [CMOCA-3-196] |
| **CMYK** | cmykData [CMOCA-3-197] |
| **CMY@** | cmyData [CMOCA-3-198] |
| **2CLR** | 2colorData [CMOCA-3-199] |
| **3CLR** | 3colorData (if not listed above) [CMOCA-3-200] |
| **4CLR** | 4colorData (if not listed above) [CMOCA-3-201] |
| **5CLR** | 5colorData [CMOCA-3-202] |
| **6CLR** | 6colorData [CMOCA-3-203] |
| **7CLR** | 7colorData [CMOCA-3-204] |
| **8CLR** | 8colorData [CMOCA-3-205] |
| **9CLR** | 9colorData [CMOCA-3-206] |
| **ACLR** | 10colorData [CMOCA-3-207] |
| **BCLR** | 11colorData [CMOCA-3-208] |
| **CCLR** | 12colorData [CMOCA-3-209] |
| **DCLR** | 13colorData [CMOCA-3-210] |
| **ECLR** | 14colorData [CMOCA-3-211] |
| **FCLR** | 15colorData [CMOCA-3-212] |

*   **Prop5: PCS** [CMOCA-3-213]
    The profile connection space specified as either CIEXYZ (XYZ) or CIELAB (Lab), encoded as for Prop4. [CMOCA-3-214]

#### CMRType=Link Color Conversion

**Note:** These fields are informational only. They are not checked for validity. Any value may be entered in the Prop fields since no error checking is done. [CMOCA-3-215]

*   **Prop1: Input Device ManufacturerName** [CMOCA-3-216]
    The ManufacturerName for the input device. Note that the ManufacturerName for the output device is defined in the device-specific fields of this CMR name.
*   **Prop2: Input DeviceType** [CMOCA-3-217]
    The DeviceType for the input device. Note that the DeviceType for the output device is defined in the device-specific fields of this CMR name.
*   **Prop3: Input DeviceModel** [CMOCA-3-218]
    The DeviceModel for the input device. Note that the DeviceModel for the output device is defined in the device-specific fields of this CMR name.
*   **Prop4: Input Color Space** [CMOCA-3-219]
    The same as Color Space of Data defined in the ICC header, encoded as in Table 10.
*   **Prop5: Output Color Space** [CMOCA-3-220]
    Device-specific color space, a subset of the Color Space of Data defined in the ICC profile header. Possible values are shown in the following table. [CMOCA-3-221]

**Table 11. Output Color Spaces** [CMOCA-3-222]

| Value | Meaning |
| :--- | :--- |
| **RGB@** | rgbData [CMOCA-3-223] |
| **GRAY** | grayData [CMOCA-3-224] |
| **CMYK** | cmykData [CMOCA-3-225] |
| **CMY@** | cmyData [CMOCA-3-226] |
| **2CLR** | 2colorData [CMOCA-3-227] |
| **3CLR** | 3colorData (if not listed above) [CMOCA-3-228] |
| **4CLR** | 4colorData (if not listed above) [CMOCA-3-229] |
| **5CLR** | 5colorData [CMOCA-3-230] |
| **6CLR** | 6colorData [CMOCA-3-231] |
| **7CLR** | 7colorData [CMOCA-3-232] |
| **8CLR** | 8colorData [CMOCA-3-233] |
| **9CLR** | 9colorData [CMOCA-3-234] |
| **ACLR** | 10colorData [CMOCA-3-235] |
| **BCLR** | 11colorData [CMOCA-3-236] |
| **CCLR** | 12colorData [CMOCA-3-237] |
| **DCLR** | 13colorData [CMOCA-3-238] |
| **ECLR** | 14colorData [CMOCA-3-239] |
| **FCLR** | 15colorData [CMOCA-3-240] |

#### CMRType=Indexed

**Note:** These fields are informational only. They are not checked for validity. Any value may be entered in the Prop fields since no error checking is done. [CMOCA-3-241]

*   **Prop1: Reserved for future use.** [CMOCA-3-242]
*   **Prop2: Reserved for future use.** [CMOCA-3-243]
*   **Prop3: Reserved for future use.** [CMOCA-3-244]
*   **Prop4: Reserved for future use.** [CMOCA-3-245]
*   **Prop5: Reserved for future use.** [CMOCA-3-246]

## CMR Data

**Data:** CMR data.
The content of the data is defined by the CMR type. The CMR data carries the color resource data. The resource data is carried in a tagged format. The tags are loosely based on the TIFF tag syntax, but with significant changes and additions. The tag syntax is defined in Chapter 5, “CMR Data Architecture”. [CMOCA-3-247]

CMR data is optional for generic and passthrough CMRs. If CMR data is specified for a generic or passthrough CMR, it is ignored. [CMOCA-3-248]

## CMR Header Exception Conditions

On encountering an error, an exception is raised. Exception conditions have a format of EC-xxxxyy. xxxx represents the tag value. For the purposes of error reporting, the fields in the CMR header are treated as “implied tags”. The architecture defines the tags that describe data fields to have TagIDs of X'0000'–X'FFFF'. However, IDs in the range X'EF00'–X'EFFF' have been reserved for error handling in the CMR Header. Currently, IDs in the range X'EFF0'–X'EFF7' are used for CMR header error codes. [CMOCA-3-249]

The error codes (yy in EC-xxxxyy) are used as follows:
*   X'03' Invalid length [CMOCA-3-250]
*   X'10' Invalid or unsupported field value [CMOCA-3-251]

The exception conditions are as follows: [CMOCA-3-252]

*   **EC-EFF003 Invalid Length Value:** The specified Length is invalid. [CMOCA-3-253]
*   **EC-EFF110 Invalid Field Value:** The specified value for CMRSig is not X'434D5239'. [CMOCA-3-254]
*   **EC-EFF210 Invalid Field Value:** The specified CMRType is invalid. [CMOCA-3-255]
*   **EC-EFF310 Invalid Field Value:** The specified CMRVersion is invalid. [CMOCA-3-256]
*   **EC-EFF410 Invalid Field Value:** The specified MediaBrightness is invalid. [CMOCA-3-257]
*   **EC-EFF510** Retired item 1. [CMOCA-3-258]
*   **EC-EFF610** Retired item 2. [CMOCA-3-259]
*   **EC-EFF710 Invalid Field Value:** The specified MediaWeight is invalid. [CMOCA-3-260]

**CMRData:** Exceptions in the data are defined by the actual tags.
Prop 1–5 are informational. The values are not checked. [CMOCA-3-261]
