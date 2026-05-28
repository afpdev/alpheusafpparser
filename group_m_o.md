### Map Graphics Object (MGO)

The Map Graphics Object structured field specifies how a graphics data object is mapped into its object area. [MODCA-5-1222]

#### MGO (X'D3ABBB') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABBB'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1223]

One repeating group in the following format: [MODCA-5-1225]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1224]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1226]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1227]|

#### MGO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Graphics Object structured field as follows: [MODCA-5-1228]

| **Triplet** | **Type** | **Usage** [MODCA-5-1229]|
| :--- | :--- | :--- [MODCA-5-1230]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MGO structured field are:<br>Value Description [MODCA-5-1231]<br>X'10' Position and trim [MODCA-5-1232]<br>X'20' Scale to fit [MODCA-5-1233]<br>X'30' Center and trim [MODCA-5-1234]<br>X'50' Retired mapping option; see “Retired Parameters”. [MODCA-5-1235]<br>X'60' Scale to fill<br>All others Reserved [MODCA-5-1236]|

**Note:** If this structured field is not present in the data stream, the architected default is scale to fit. [MODCA-5-1237]

#### MGO Exception Condition Summary

**X'01'** The Map Graphics Object structured field contains more than one repeating group.

**X'02'** A Mapping Option (X'04') triplet value of X'00' is specified. [MODCA-5-1238]


### Map Image Object (MIO)

The Map Image Object structured field specifies how an image data object is mapped into its object area. [MODCA-5-1239]

#### MIO (X'D3ABFB') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABFB'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1240]

One repeating group in the following format: [MODCA-5-1242]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1241]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1243]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1244]|

#### MIO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Image Object structured field as follows: [MODCA-5-1245]

| **Triplet** | **Type** | **Usage** [MODCA-5-1246]|
| :--- | :--- | :--- [MODCA-5-1247]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MIO structured field are:<br>Value Description [MODCA-5-1248]<br>X'10' Position and trim [MODCA-5-1249]<br>X'20' Scale to fit [MODCA-5-1250]<br>X'30' Center and trim [MODCA-5-1251]<br>X'41' Migration mapping option: Image point-to-pel. See “Coexistence Triplets” for a description. [MODCA-5-1252]<br>X'42' Migration mapping option: Image point-to-pel with double dot. See “Coexistence Triplets” for a description. [MODCA-5-1253]<br>X'50' Migration mapping option: Replicate and trim. See “Coexistence Triplets” for a description. [MODCA-5-1254]<br>X'60' Scale to fill<br>All others Reserved [MODCA-5-1255]|

**Note:** If this structured field is not present in the data stream, the architected default is scale to fit. [MODCA-5-1256]

#### MIO Exception Condition Summary

**X'01'** The Map Image Object structured field contains more than one repeating group.

**X'02'** A Mapping Option (X'04') triplet value of X'00' is specified. [MODCA-5-1257]


### Medium Modification Control (MMC)

The Medium Modification Control structured field specifies the medium modifications to be applied for a copy subgroup specified in the Medium Copy Count (MCC) structured field. [MODCA-5-1258]

#### MMC (X'D3A788') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A788'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1259]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1260]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1261]|
| 0 | CODE | **MMCid** | 1–127 | Medium Modification Control identifier | M | X'06' [MODCA-5-1262]|
| 1 | CODE | | X'FF' | Constant data | M | X'06' [MODCA-5-1263]|
| 2–n | CODE | | | Zero or more keywords in ascending order, in the format shown in the following table. When keywords occur in pairs, the ordering applies to the first keyword. | | [MODCA-5-1264]|

**MMC Keywords**

| **Keyword ID** | **Parameter Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :---: | :---: |
| X'0E' | X'01'–X'20', X'FF' | Horizontal print adjustment; retired for the IBM 3800 printer | O | X'02' |
| X'90' | X'01'–X'FF' | Media destination selector—high. **Note:** X'00' is not valid with keyword X'9100' | O | X'02' |
| X'91' | X'01'–X'FF' | Media destination selector—low. **Note:** X'00' is not valid with keyword X'9000' | O | X'02' |
| X'A0' | X'00'–X'FE', X'FF' | Fixed medium information: a local identifier for the particular fixed medium information selected. X'FF' selects all supported. | O | X'02' |
| X'A1' | X'00' | Fixed perforation cut. Apply a perforation cut at a fixed location on the physical medium. | O | X'02' |
| X'A2' | X'00' | Fixed separation cut. Apply a separation cut at a fixed location on the physical medium. | O | X'02' |
| X'B4' | X'00'–X'FF' | Presentation subsystem set-up ID: high-order byte | O | X'00' |
| X'B5' | X'00'–X'FF' | Presentation subsystem set-up ID: low-order byte | O | X'00' |
| X'D1' | X'00'–X'01' | Offset stack/edge mark change:<br>X'00' No offset stack or edge mark change<br>X'01' Apply offset stack or edge mark change | O | X'02' |
| X'D2' | X'01'–X'7F' | Medium Preprinted Form Overlay (M-PFO) local ID | O | X'02' |
| X'E0' | X'01'–X'02' | Media source selection format:<br>X'01' Media source selector in Format 1<br>X'02' Media source selector in Format 2 | O | X'02' |
| X'E1' | X'01'–X'04', X'41', X'64', X'01'–X'FF' | Media source selector: Format 1 (X'01'–X'04' source ID, X'41' envelope, X'64' manual), Format 2 (X'01'–X'FF') | O | X'02' |
| X'E8' | X'00'–X'FF' | Media type local ID: high-order byte | O | X'02' |
| X'E9' | X'00'–X'FF' | Media type local ID: low-order byte | O | X'02' |
| X'F1' | X'00'–X'01' | Forms flash; retired for the IBM 3800 printer | O | X'02' |
| X'F2' | X'01'–X'7F' | Medium overlay local identifier | O | X'02' |
| X'F3' | X'01'–X'7F' | Text suppression local identifier | O | X'02' |
| X'F4' | X'01'–X'03' | Duplex control:<br>X'01' Simplex<br>X'02' Normal duplex<br>X'03' Tumble duplex | O | X'02' |
| X'F8' | X'01'–X'FE', X'FF' | Print quality control:<br>X'01' Lowest quality level<br>X'FE' Highest quality level<br>X'FF' Printer default | O | X'02' |
| X'F9' | X'00'–X'01' | Constant forms control:<br>X'00' Inactive<br>X'01' Active | O | X'02' |
| X'FC' | X'01'–X'04' | N-up format control:<br>X'01' 1-up format<br>X'02' 2-up format<br>X'03' 3-up format<br>X'04' 4-up format | O | X'02' |

#### MMC Semantics

**MMCid** Medium Modification Control Identifier. The identifier for the modifications specified by this structured field. This identifier is specified in a repeating group in the Medium Copy Control (MCC) structured field.

**Keyword X'0Enn'** Retired keyword for the IBM 3800 printer. See “Retired Parameters” for a description.

**Keyword X'90nn'** Specifies the high-order portion of a two-byte media destination ID. The allowed range is X'00'—X'FF'. The value X'00' is not valid if keyword X'91' also specifies a value of X'00', that is, the media destination ID X'0000' is reserved. This keyword may appear once. If this keyword is not present, the high-order portion of the media destination ID is set to X'00'. If this [MODCA-5-1265] keyword is not present and the X'91' keyword is not present, the media destination is not specified and a presentation environment default is used.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media destination specified by this keyword must match the media destination specified for the other copy subgroup in the pair.

**Keyword X'91nn'** Specifies the low-order portion of a two-byte media destination ID. The allowed range is X'00'—X'FF'. The value X'00' is not valid if keyword X'90' also specifies a value of X'00', that is, the media destination ID X'0000' is reserved. This keyword may appear once. If this keyword is not present, the low-order portion of the media destination ID is set to X'00'. If this keyword is not present and the X'90' keyword is not present, the media destination is not specified and a presentation environment default is used.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media destination specified by this keyword must match the media destination specified for the other copy subgroup in the pair.

**Keyword X'A0nn'** Specifies the local ID of fixed medium information that a printer or a printer-attached device applies to a sheet-side. This application is independent of data provided through the data stream, and does not mix with the print data provided in the data stream. Fixed medium information is applied either before or after the data stream information is presented.

| **Value** | **Description** |
| :--- | :--- |
| X'00'—X'FE' | Select a particular local ID for fixed medium information to be applied to the sheet-side. |
| X'FF' | Select all currently-supported local IDs for fixed medium information to be applied to the sheet-side. |

This keyword may appear multiple times and specify multiple local IDs for fixed medium information.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same local IDs for fixed medium information.

**Keyword X'A100'** Specifies a perforation cut at a fixed location on the physical medium according to the current setup of the printer or printer-attached device.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same perforation cuts.

**Keyword X'A200'** Specifies a separation cut at a fixed location on the physical medium according to the current setup of the printer or printer-attached device.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same separation cuts.

**Keyword X'B4nn'** Specifies the high-order portion of a two-byte presentation subsystem set-up ID. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'B5nn' keyword that immediately follows it and that specifies the low-order portion of the two-byte presentation subsystem set-up ID. The X'B4nn'—X'B5nn' keyword pair may appear multiple times. If the keyword pair is not present, a presentation subsystem set-up ID is not specified. The set-up ID specified by the X'B4nn' and X'B5nn' keywords is compared against the set-up IDs generated by the presentation subsystem, which typically consists of the presentation device and pre/post processing devices. If a match is found, presentation is allowed to proceed. If there is no match, the required set-up is not active in the presentation subsystem and presentation is terminated.

**Note:** A set-up ID is not the same as a setup name, which is a user-created name for a set of specific settings on a presentation device. A presentation device can support setup names, or set-up IDs, or both (the two functions do not necessarily interact). [MODCA-5-1266]

**Keyword X'B5nn'** Specifies the low-order portion of a two-byte presentation subsystem set-up ID. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'B4nn' keyword that immediately precedes it and that specifies the high-order portion of the two-byte presentation subsystem set-up ID. The X'B4nn'—X'B5nn' keyword pair may appear multiple times. If the keyword pair is not present, a presentation subsystem set-up ID is not specified. The set-up ID specified by the X'B4nn' and X'B5nn' keywords is compared against the set-up IDs generated by the presentation subsystem, which typically consists of the presentation device and pre/post processing devices. If a match is found, presentation is allowed to proceed. If there is no match, the required set-up is not active in the presentation subsystem and presentation is terminated.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same presentation subsystem set-up IDs.

**Application Notes:**

1. When presentation is terminated, the print file is put into a state where it can be [MODCA-5-1267] resubmitted when the presentation subsystem is reconfigured to generate the required set-up IDs.
2. Presentation Subsystem set-up IDs are intended to be specified for one or more [MODCA-5-1268] documents in a print file. It is therefore recommended that the same IDs are specified in all the medium maps in the form map.

**Keyword X'D1nn'** Specifies whether the sheets generated by the current medium map should be offset (jogged) from the sheets generated by the previous medium map or whether the edge marks applied to sheets generated by this medium map should be changed from the edge marks applied to sheets generated by the previous medium map. This keyword applies to all sheets generated by the current medium map and needs to be specified only once. If this keyword is omitted, the default is X'00' (no offset, no change in edge marks).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'00' | No offset (no jog), no change in edge marks |
| X'01' | Apply offset (jog) or change edge marks |

**Note:** When processing partition ejects with N-up presentation, multiple medium maps may be invoked while building a single sheet. In that case, only the first X'D1nn' keyword is processed for a sheet. All other X'D1nn' keywords specified in medium maps invoked for the same sheet are ignored.

**Implementation Note:** Print servers that automatically issue a jog command between jobs and between multiple copies of a job may ignore the X'D1nn' keyword in the medium map used for the first sheet of the user's print file.

Table 23 shows how the jog control specified by this keyword is processed with N-up presentation and conditional media ejects when an existing medium map (MM) is replaced by a new medium map. The “Result” column defines whether the sheet processed with the new medium map is jogged with respect to the previous sheet and what type of media eject (sheet or partition) occurs when the new medium map is invoked. Note that in AFP environments a jog is accomplished with the generation of an IPDS jog command when the medium map that specifies the jog is first invoked. [MODCA-5-1269]

**Table 23. Sheet Jogging and Conditional Ejects**

| **Jog Control in Existing MM** | **Jog Control in New MM** | **Eject Control in New MM** | **Result** |
| :--- | :--- | :--- | :--- |
| | | | **Eject** | **Jog** |
| No jog | Jog | Partition | New sheet | Jog |
| No jog | Jog | New sheet | New sheet | Jog |
| Jog | Jog | Partition | Partition | Jog |
| Jog | Jog | New sheet | New sheet | Jog |
| Jog | No jog | Partition | New sheet | No jog |
| Jog | No jog | New sheet | New sheet | No jog |
| No jog | No jog | Partition | Partition | No jog |
| No jog | No jog | New sheet | New sheet | No jog |

**Keyword X'D2nn'** Specifies the local identifier of a Medium Preprinted Form Overlay (M-PFO) that is to be applied to all sheet-sides generated by this copy subgroup. The M-PFO is applied last, after all other data has been applied to the sheet-side. The allowed ID range is X'01'—X'7F'. The X'D2nn' keyword may appear once. If this keyword is specified more than once, the additional occurrences are ignored. This limits the number of M-PFOs to one per sheet-side. The local ID must be mapped to the name of an M-PFO in a Map Medium Overlay (MMO) structured field.

**Keyword X'E0nn'** Specifies the format of the media source selector (X'E1') keyword. This keyword may appear once. If this keyword is omitted, the X'E1' keyword, if present, is specified in Format 1.

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | The X'E1' keyword is specified in Format 1. |
| X'02' | The X'E1' keyword is specified in Format 2. |

**Keyword X'E1nn'** Specifies the media source. This keyword is defined in several formats. The format is selected by a X'E0' keyword or is defaulted to Format 1 if the X'E0' keyword is omitted. This keyword may appear once. If this keyword is omitted, the media source is not specified and a presentation environment default is used.

**Notes:**

1. If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, [MODCA-5-1270] the media source specified by this keyword must match the media source specified for the other copy subgroup in the pair.
2. The selected media source may be an inserter bin. Inserter bins do not support printing [MODCA-5-1271] from the data stream, therefore printing is suppressed when pages, PMC overlays, and medium overlays are processed with media from an inserter bin. When a requested media source, which may be an inserter bin, is not available, the presentation systems uses a default bin and ensures that it is not an inserter bin, therefore pages and overlays that are associated with an inserter bin are printed if the inserter bin is not available.

**Application Notes:**

1. In AFP environments, the default media source is normally the first media source reported [MODCA-5-1272] by the printer in the IPDS XOH-OPC reply.
2. To cause the insertion of a single sheet from the inserter bin, the application generates a [MODCA-5-1273] data stream with one (simplex printing) or two (duplex printing) “placeholder” pages that are processed with the medium map that selects an inserter bin as the media source. If the inserter bin is available, a sheet is inserted but these pages will not be printed on the inserted sheet. However, if the inserter bin is not available, the presentation system will [MODCA-5-1274] use a default media source that is not an inserter bin and the placeholder pages will be printed. This method can be extended to inserting multiple sheets by specifying multiple placeholder pages in the data stream.
3. An application can also cause the insertion of one or more sheets without generating [MODCA-5-1275] placeholder pages. This is done by specifying two consecutive Invoke Medium Map (IMM) structured fields in the data stream, where the first invoked medium map selects an inserter bin and specifies the constant front (keyword X'F901') function and simplex printing, and the second invoked medium map resumes page printing from a non-inserter bin. Multiple inserted sheets can be generated in this manner by specifying a copy count that is greater than one.

**X'E1nn' Format 1**

Specifies a value that identifies either a presentation device media source ID or the characteristics associated with a presentation device media source. The keyword values in Format 1 are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Media source ID X'00' |
| X'02' | Media source ID X'01' |
| X'03' | Media source ID X'02' |
| X'04' | Media source ID X'03' |
| X'41' | Envelope media source |
| X'64' | Manual feed media source |

**X'E1nn' Format 2**

Specifies a value that identifies a presentation device media source ID. The keyword values in Format 2 can be in the range X'01' to X'FF' and specify media source IDs whose values are one less than the keyword values:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Media source ID X'00' |
| X'02' | Media source ID X'01' |
| ⋮ | ⋮ |
| X'FE' | Media source ID X'FD' |
| X'FF' | Media source ID X'FE' |

**Keyword X'E8nn'** Specifies the high-order portion of a two-byte local ID to select a media type. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'E9nn' keyword that immediately follows it and that specifies the low-order portion of the two-byte media type local ID. The X'E8nn'–X'E9nn' keyword pair may appear only once. The media type local ID is mapped to a media type name or media type OID in the Map Media Type (MMT) structured field. If it is mapped to both, the media type OID takes precedence. If this keyword pair is present, it overrides the media source specified with the X'E1nn' keyword unless the presentation device doesn't support media type selection, in which case a specified media source is used. If the keyword pair is not present, the media is selected from the media source specified with the X'E1nn' keyword. A registry of standard media types along with their OID is provided in “Media Type Identifiers”.

**Keyword X'E9nn'** Specifies the low-order portion of a two-byte local ID to select a media type. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'E8nn' keyword that immediately precedes it and that specifies the high-order portion of the two-byte media type local ID. The X'E8nn'–X'E9nn' keyword pair may appear only once. The media type local ID is mapped to a media type name or media type OID in the Map Media Type (MMT) structured field. If it is mapped to both, the media type OID takes precedence. If this keyword pair is present, it overrides the media source specified with the X'E1nn' keyword unless the presentation device doesn't support media type selection, in which case a specified media source is used. If the keyword pair is not present, the media is selected from the media source specified with the X'E1nn' keyword. A registry of standard media types along with their OID is provided in “Media Type Identifiers”.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media type specified by this keyword must match the media type specified for the other copy subgroup in the pair.

**Implementation Note:** AFP print servers will attempt to select the media type requested by the X'E8'/X'E9' keyword pair using the following priority:

1. Attempt to find an available media source containing the media type that matches [MODCA-5-1276] the specified OID. The media source cannot be an inserter bin.
2. Attempt to find an available media source containing the media type that matches [MODCA-5-1277] the specified name. The media source cannot be an inserter bin.
3. Attempt to find an available media source whose ID matches the ID specified in a [MODCA-5-1278] X'E1' keyword on the MMC.
4. Use the presentation process defaults for finding an available media source. [MODCA-5-1279]

**Keyword X'F1nn'** Retired keyword for the IBM 3800 printer. See “Retired Parameters” for a description.

**Keyword X'F2nn'** Specifies the local identifier of a medium overlay that is to be applied to all sheet-sides generated by this copy subgroup. This keyword may appear a maximum of eight times in an MMC structured field. The allowed ID range is X'01'–X'7F'. The local ID must be mapped to the name of the medium overlay in a Map Medium Overlay (MMO) structured field.

**Keyword X'F3nn'** Specifies the local identifier of a text suppression that is to be applied to all sheet-sides generated by this copy subgroup. This keyword may appear a maximum of eight times in an MMC structured field. The allowed ID range is X'01'–X'7F'.

**Keyword X'F4nn'** Specifies whether data is generated on the front side of the sheet (simplex) or on both sides of the sheet (duplex). If duplex is specified, the first copy subgroup in a pair generates the front sheet-side, and the second copy subgroup in the pair generates the back sheet-side. This keyword may appear once. If this keyword is omitted, the default is X'01' (simplex).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Simplex |
| X'02' | Normal duplex. The media is turned around the Ym axis. |
| X'03' | Tumble duplex. The media is turned around the Xm axis. |

See **Figure 60** for a description of normal duplex and tumble duplex. [MODCA-5-1280]

<!-- Page XX -->

**Figure 60. Normal Duplex and Tumble Duplex Printing**

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same value for this keyword.

**Keyword X'F8nn'** Specifies the level of print quality to be used on all sheet-sides generated by this copy subgroup. The mapping of print quality levels to physical print quality is presentation-system-dependent. This keyword may appear once.

The allowed quality level range is X'01'–X'FF', and is defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Lowest print quality level |
| X'FE' | Highest print quality level |
| X'FF' | Device default print quality |

**Keyword X'F9nn'** Specifies whether both variable page data and medium overlay data or only medium overlay data should be generated on all sheet-sides generated by this copy subgroup. This function [MODCA-5-1281] is known as constant forms control. Note that PMC overlays are considered variable page data for this keyword. This keyword may appear once. If this keyword is omitted, the default is X'00' (present both medium overlay data and variable page data).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'00' | Present both medium overlay data and variable page data |
| X'01' | Present only medium overlay data. If no medium overlays are specified for this copy subgroup, no data is presented on the sheet-sides generated by this copy subgroup. |

**Keyword X'FCnn'** Specifies the number of pages to be placed on a physical medium using N-up partitioning. In N-up partitioning, each side of the physical medium is divided into a number of equal-size partitions, where the number of partitions is indicated by the number N in “N-up”. If duplex is specified, the same N-up partitioning is applied to the back side as is applied to the front side. With simplex N-up partitioning, N pages are placed on the physical medium, and with duplex N-up partitioning, 2N pages are placed on the physical medium. Pages placed into partitions may be blank pages generated by setting PgFlgs bit 0 = B'1' in the Page Position (PGP) structured field repeating group.

Pages are placed into partitions using either a default N-up page placement or an explicit N-up page placement, as specified in the Page Position (PGP) structured field. In default N-up page placement, consecutive pages in the data stream are placed into consecutively-numbered partitions. In explicit N-up page placement, consecutive pages in the data stream are processed using consecutive PGP repeating groups and are placed into explicitly-specified partitions. For more information on placement, see “Page Position (PGP) Format 2”.

Pages may be rotated within their partitions so that the page presentation space X axis is at a 0°, 90°, 180°, or 270° orientation with respect to the medium presentation space X axis. This rotation is specified in the Page Position structured field.

Pages are positioned within their partition relative to the partition origin using the offsets specified in the Page Position structured field. Modifications may be applied to pages before they are placed in their partition using the Page Modification Control (PMC) structured field.

**Figure 21** shows the partitioning for wide continuous-forms media, narrow continuous-forms media, and cut-sheet media. Partitioning is not used with envelope media. **Figure 61** through **Figure 72** show partition numbering for various media. This keyword may appear once.

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | 1-up partitioning. The medium presentation space is divided into one partition. One page (simplex) or two pages (duplex) are presented on the physical medium. |
| X'02' | 2-up partitioning. The medium presentation space is divided into two partitions. Two pages (simplex) or four pages (duplex) are presented on the physical medium. |
| X'03' | 3-up partitioning. The medium presentation space is divided into three partitions. Three pages (simplex) or six pages (duplex) are presented on the physical medium. |
| X'04' | 4-up partitioning. The medium presentation space is divided into four partitions. Four pages (simplex) or eight pages (duplex) are presented on the physical medium. |

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same value for this keyword. [MODCA-5-1282]

**Application Note:** IPDS printers require that pages be contained within their partition if default N-up page placement is specified, otherwise an exception is generated. This restriction does not exist if explicit N-up page placement is specified. That is, pages may overflow their partition without necessarily causing an exception.

#### MMC Exception Condition Summary

**X'02'** An undefined keyword is encountered in an MMC structured field. [MODCA-5-1283]


### Map Media Destination (MMD)

The Map Media Destination structured field maps a media destination local ID to the name of a media destination. [MODCA-5-1284]

**Architecture Note:** A media destination local ID is specified with the X'90nn' + X'91nn' keyword pair on the MMC structured field.

#### MMD (X'D3ABCD') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABCD'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1285]

One or more repeating groups in the following format: [MODCA-5-1287]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1286]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 14–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1288]|
| 2–n | Triplets | | | See MMD Semantics for triplet applicability. | M | X'14' [MODCA-5-1289]|

#### MMD Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Media Destination structured field repeating groups as follows: [MODCA-5-1290]

| **Triplet** | **Type** | **Usage** [MODCA-5-1291]|
| :--- | :--- | :--- [MODCA-5-1292]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1293]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'12'—Media Destination Reference. The media destination reference may be specified in the following format:<br>• FQNFmt = X'00'; the reference is made with a character-encoded name. [MODCA-5-1294]|
| X'22' | | **Extended Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Extended Resource Local Identifier Triplet X'22'”. The only Extended Resource Local Identifier type that may appear is X'42'—Media Destination Resource. |

**Architecture Note:** In the UP3i architecture, the media destination name must be encoded using UTF-16BE; it is therefore recommended that the same encoding be used in the FQN type X'12' triplet when FQNFmt = X'00'.

**Architecture Note:** The local IDs used with resource type X'42' are specified with a X'90nn' + X'91nn' keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this resource type is restricted to 2-byte values.

Within the same medium map, you may not map the same media destination local ID to more than one media destination name or a X'01' exception condition exists. Within the same medium map, different media destination local IDs may be mapped to the same media destination name.

**Implementation Note:** AFP print servers will process the media destination name as follows. Note that, for UP3i devices, media destination names are reported as UP3i tupel names in the UP3i Tupel sdf in the IPDS XOH-OPC reply. The same UP3i Tupel sdf also specifies a 2-byte tupel ID.

* If a media destination local ID is specified in the MMC, the server checks for a mapping to a media [MODCA-5-1295] destination name in MMD structured fields in the Medium Map.
  – If a mapping is found, the server checks the UP3i Tupel sdfs in the IPDS XOH-OPC for a matching tupel name. If one is found, the server uses the tupel ID (which is also reported in the UP3i Tupel sdf) that corresponds to that name as a media destination ID to select the media destination.
  – If no mapping is found, or if a mapping is found but there is no matching tupel name, the server uses the MMC media destination local ID to select the media destination.
* If there is no media destination local ID specified in the MMC, the server selects a default media [MODCA-5-1296] destination.

#### MMD Exception Condition Summary

**X'01'** The same LID is mapped to more than one media destination within the same structured field.

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'12' (Media Destination Reference) [MODCA-5-1297] appears within any repeating group.
* An Extended Resource Local Identifier (X'22') triplet type other than X'42' appears within any [MODCA-5-1298] repeating group.


### Map Medium Overlay (MMO)

The Map Medium Overlay structured field maps one-byte medium overlay local identifiers that are specified by keywords in the Medium Modification Control (MMC) structured field to medium overlay names. [MODCA-5-1299]

#### MMO (X'D3B1DF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B1DF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1300]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1301]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1302]|
| 0 | UBIN | **RGLength** | | X'0C' Length of each repeating group | M | X'06' [MODCA-5-1303]|
| 1–3 | Reserved | | | Should be zero | M | X'06' |

Zero to 127 repeating groups in the following format: [MODCA-5-1304]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0 | UBIN | **OVLid** | | X'01'–X'7F' Medium overlay local identifier | M | X'06' [MODCA-5-1305]|
| 1 | BITS | **Flags** | | Bit 0: Raster indicator; retired for the IBM 3800 printer<br>Bits 1–7: Reserved; should be zero | M | X'06' [MODCA-5-1306]|
| 2–3 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1307]|
| 4–11 | CHAR | **OVLname** | | Name of medium overlay | M | X'06' [MODCA-5-1308]|

#### MMO Semantics

**RGLength** Length of each repeating group. Set to 12.

**OVLid** Medium overlay local identifier as specified by a keyword in an MMC structured field. The allowed range is X'01'–X'7F' and must be unique to each repeating group.

**Flags** Bit Description:
* **0** Retired parameter for the IBM 3800 printer. See “Retired Parameters” for a description. [MODCA-5-1309]
* **1–7** Reserved; should be zero.

**OVLname** External name of the medium overlay. [MODCA-5-1310]


### Map Media Type (MMT)

The Map Media Type structured field maps a media type local ID to the name or OID of a media type. See “Media Type Identifiers” for a list of media types registered by their name and their OID.

**Architecture Note:** A media type local ID is specified with the X'E8nn' + X'E9nn' keyword pair on the MMC structured field.

#### MMT (X'D3AB88') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AB88'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1311]

One or more repeating groups in the following format: [MODCA-5-1313]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1312]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 14–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1314]|
| 2–n | Triplets | | | See MMT Semantics for triplet applicability. | M | X'14' [MODCA-5-1315]|

#### MMT Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Media Type structured field repeating groups as follows: [MODCA-5-1316]

| **Triplet** | **Type** | **Usage** [MODCA-5-1317]|
| :--- | :--- | :--- [MODCA-5-1318]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1319]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. May occur twice in each repeating group if one occurrence uses FQNFmt X'00' (name), and the other occurrence uses FQNFmt X'10' (OID). See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'11'—Media Type Reference. The media type reference may be specified in one of two ways:<br>• If FQNFmt = X'00', the reference is made with a character-encoded name. [MODCA-5-1320] [MODCA-5-1321]<br>• If FQNFmt = X'10', the reference is made with an ASN.1 OID encoded using the definite short form. See “Media Type Identifiers”. [MODCA-5-1322]|
| X'22' | | **Extended Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Extended Resource Local Identifier Triplet X'22'”. The only Extended Resource Local Identifier type that may appear is X'40'—Media Type resource. |

**Architecture Note:** In the IPDS architecture, the media type name must be encoded using IBM code page 500, character set 640 (plus space character). It is strongly recommended that the same encoding be used in the FQN type X'11' triplet when FQNFmt = X'00', since not all print servers are able to process other encodings.

**Architecture Note:** The local IDs used with resource type X'40' are specified with a X'E8nn' + X'E9nn' keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this resource type is restricted to 2-byte values.

If the FQN type X'11' triplet is specified twice in a repeating group, the FQNFmt X'10'—OID reference, takes precedence.

Within the same medium map, you may not map the same Resource Local ID to more than one media type or a X'01' exception condition exists. The media type may be specified with an FQN type X'11' triplet using FQNFmt X'10' (OID reference), an FQN type X'11' triplet using FQNFmt X'00' (name reference), or both. Within the same medium map, different Resource Local IDs may be mapped to the same media type. [MODCA-5-1323]

**Implementation Note:** AFP print servers will attempt to select the requested media type using the following priority:

1. Attempt to find an available media source containing the media type that matches the specified OID. [MODCA-5-1324] The media source cannot be an inserter bin.
2. Attempt to find an available media source containing the media type that matches the specified [MODCA-5-1325] name. The media source cannot be an inserter bin.
3. Attempt to find an available media source whose ID matches the ID specified in a X'E1' keyword on [MODCA-5-1326] the MMC.
4. Use the presentation process defaults for finding an available media source. [MODCA-5-1327]

#### MMT Exception Condition Summary

**X'01'** The same LID is mapped to more than one media type within the same structured field.

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'11' (Media Type Reference) [MODCA-5-1328] appears within any repeating group.
* An Extended Resource Local Identifier (X'22') triplet type other than X'40' appears within any [MODCA-5-1329] repeating group.


### Map Page (MPG)

The Map Page structured field identifies a page that is to be merged with data specified for the current page by using an Include Page (IPG) structured field. [MODCA-5-1330]

#### MPG (X'D3ABAF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABAF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1331]

One repeating group in the following format: [MODCA-5-1333]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1332]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 12–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1334]|
| 2–n | Triplets | | | See MPG Semantics for triplet applicability. | M | X'14' [MODCA-5-1335]|

#### MPG Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Page structured field as follows: [MODCA-5-1336]

| **Triplet** | **Type** | **Usage** [MODCA-5-1337]|
| :--- | :--- | :--- [MODCA-5-1338]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once in each repeating group. Specifies encoding for character strings. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1339]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'83'—Begin Document Reference. Specifies the name of the document that contains the page. [MODCA-5-1340]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'87'—Begin Page Reference. Specifies the name of the page to be mapped. [MODCA-5-1343]|
| X'5A' | | **Object Offset** Optional. May occur once, with ObjTpe=X'AF', to specify that pages are the objects to be counted. Specifies the number of pages preceding the page to be mapped. When this triplet is specified, the page name in the FQN type X'87' triplet is ignored. See “Object Offset Triplet X'5A'”. [MODCA-5-1344]|

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified.

#### MPG Exception Condition Summary

**X'01'** This exception condition exists when:
* Multiple type X'87' (Begin Page Reference) Fully Qualified Name triplets appear within the [MODCA-5-1345] repeating group.
* Multiple type X'83' (Begin Document Reference) Fully Qualified Name triplets appear within [MODCA-5-1346] the repeating group.

**X'02'** A Fully Qualified Name (X'02') triplet other than a type X'87' (Begin Page Reference) or a type X'83' (Begin Document Reference) appears within the repeating group. [MODCA-5-1347]


### Map Page Overlay (MPO)

The Map Page Overlay structured field maps local identifiers to page overlay names. [MODCA-5-1348]

#### MPO (X'D3ABD8') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABD8'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1349]

One to 254 repeating groups in the following format: [MODCA-5-1351]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1350]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 11–(n)+1 | Total length of this repeating group | M | X'06' [MODCA-5-1352]|
| 2–n | Triplets | | | See MPO Semantics for triplet applicability. | M | X'14' [MODCA-5-1353]|

#### MPO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Page Overlay structured field as follows: [MODCA-5-1354]

| **Triplet** | **Type** | **Usage** [MODCA-5-1355]|
| :--- | :--- | :--- [MODCA-5-1356]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for character strings. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1357]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'84'—Begin Resource Object Reference which must match the name of an overlay resource. [MODCA-5-1358]|
| X'24' | | **Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Resource Local Identifier Triplet X'24'”. The only Resource Local Identifier type that may appear is X'02'—Page Overlay. [MODCA-5-1359]|

Within the same Map Page Overlay structured field, you may not map the same Resource Local ID to more than one page overlay resource or a X'01' exception condition exists. However, you may use two or more repeating groups within the same Map Page Overlay structured field to map different LIDs to the same page overlay resource. [MODCA-5-1360]

**Application Notes:**

1. The local identifier specified in the MPO structured field is not used to reference the page overlay when it is [MODCA-5-1361] included on a page with an IPO or PMC structured field. It may optionally be used in an application-dependent manner to manage the overlay resource.
2. To optimize print performance, it is strongly recommended that the same encoding scheme be used for a [MODCA-5-1362] resource reference wherever in a print file that resource reference is specified.

**Architecture Note:** In AFP environments, the following retired triplets are used on this structured field:
* **Page Overlay Conditional Processing (X'46') triplet**, may occur zero or more times; see “Page Overlay [MODCA-5-1363] Conditional Processing Triplet X'46'”.
* **Resource Usage Attribute (X'47') triplet**, may occur zero or once; see “Resource Usage Attribute [MODCA-5-1364] Triplet X'47'”.

#### MPO Exception Condition Summary

**X'01'** This exception condition exists when:
* An overlay with the same name as that specified on the FQN type X'84' triplet cannot be [MODCA-5-1365] located.
* Multiple FQN type X'84' triplets appear within the same repeating group. [MODCA-5-1366]
* Multiple type X'02' Resource Local Identifier (X'24') triplets appear within the same repeating [MODCA-5-1367] group.
* The same LID is mapped to more than one page overlay within the same structured field. [MODCA-5-1368]

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'84' (Begin Resource Object [MODCA-5-1369] Reference) appears within any repeating group.
* A Resource Local Identifier (X'24') triplet type other than X'02' appears within any repeating [MODCA-5-1370] group.


### Map Page Segment (MPS)

The Map Page Segment structured field identifies page segments that are required to present a page on a physical medium.

#### MPS (X'D3B15F') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B15F'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1371]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1372]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1373]|
| 0 | UBIN | **RGLength** | | X'0C' Length of each repeating group | M | X'06' [MODCA-5-1374]|
| 1–3 | Reserved | | | Should be zero | M | X'06' |

Zero to 127 repeating groups in the following format: [MODCA-5-1375]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–3 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1376]|
| 4–11 | CHAR | **PsegName** | | Name of page segment | M | X'06' [MODCA-5-1377]|

#### MPS Semantics

**RGLength** Length of each repeating group. Set to 12.

**PsegName** External name of the page segment.

**Application Notes:**

1. A page segment included on a page or overlay with an IPS may optionally be mapped with an MPS in the [MODCA-5-1378] AEG for that page or overlay. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment data is sent to the presentation device as part of the page or overlay and is called a soft page segment.
2. To optimize print performance, it is strongly recommended that the same encoding scheme be used for a [MODCA-5-1379] resource reference wherever in a print file that resource reference is specified. [MODCA-5-1380]


### Map Presentation Text (MPT)

The Map Presentation Text structured field specifies how a presentation text object that contains an Object Environment Group (OEG) is mapped into its object area. [MODCA-5-1381]

#### MPT (X'D3AB9B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AB9B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1382]

One repeating group in the following format: [MODCA-5-1384]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1383]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1385]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1386]|

#### MPT Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Presentation Text structured field as follows: [MODCA-5-1387]

| **Triplet** | **Type** | **Usage** [MODCA-5-1388]|
| :--- | :--- | :--- [MODCA-5-1389]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MPT structured field are:<br>Value Description [MODCA-5-1390]<br>X'00' Position<br>All others Reserved [MODCA-5-1391]|

**Note:** If this structured field is not present in the data stream, the architected default is position. [MODCA-5-1392]

#### MPT Exception Condition Summary

**X'01'** The Map Presentation Text structured field contains more than one repeating group. [MODCA-5-1393]


### Map Suppression (MSU)

The Map Suppression structured field maps one-byte text suppression local identifiers to text suppression names. Suppressible text is identified in presentation text objects with a local identifier and is bracketed with control sequences that specify the beginning and the end of the suppression. A text suppression is activated by specifying its local identifier in a Medium Modification Control (MMC) structured field in a medium map. [MODCA-5-1394]

#### MSU (X'D3ABEA') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABEA'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1395]

Zero to 127 repeating groups in the following format: [MODCA-5-1397]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1396]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **SUPname** | | Name of text suppression | M | X'06' [MODCA-5-1398]|
| 8 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1399]|
| 9 | CODE | **SUPid** | | X'01'–X'7F' Text suppression local identifier | M | X'06' [MODCA-5-1400]|

#### MSU Semantics

**SUPname** Name of the text suppression.

**SUPid** Text suppression local identifier, as specified by a keyword in an MMC structured field. The allowed range is X'01'—X'7F'.

**Note:** The local ID may be mapped to more than one text suppression name.

**Architecture Note:** When processing AFP line data with Page Definitions, the Descriptor structured fields can enable the text suppression function for a record, and, if so, assign an eight-byte name to the suppression function. This name is mapped to a local identifier using the MSU structured field. [MODCA-5-1401]


### No Operation (NOP)

The No Operation structured field performs no function. [MODCA-5-1402]

#### NOP (X'D3EEEE') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3EEEE'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1403]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1404]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1405]|
| 0–n | UNDF | **UndfData** | | Up to 32,759 bytes of data with no architectural definition | O | X'00' [MODCA-5-1406]|

#### NOP Semantics

**UndfData** Is data that has no architectural definition.

The No Operation structured field may be specified within any begin-end domain.

**Note:** The No Operation structured field may be used to carry comments or any other type of unarchitected data. Although this is not recommended, it may also be used to carry semantic data in private or exchange data streams. However, because receivers of interchange data streams should ignore the content of No Operation structured fields, and because receiver-generator products are not required to propagate No Operation structured fields, no semantics should be attached to the data carried by the No Operation structured field in interchange data streams. [MODCA-5-1407]


### Object Area Descriptor (OBD)

The Object Area Descriptor structured field specifies the size and attributes of an object area presentation space.

#### OBD (X'D3A66B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A66B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1408]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1409]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1410]|
| 0–n | Triplets | | | See OBD Semantics for triplet applicability. | M | X'14' [MODCA-5-1411]|

#### OBD Semantics

**Triplets** Appear in the Object Area Descriptor structured field as follows: [MODCA-5-1412]

| **Triplet** | **Type** | **Usage** [MODCA-5-1413]|
| :--- | :--- | :--- [MODCA-5-1414]|
| X'43' | | **Descriptor Position** Mandatory. Must occur once. See “Descriptor Position Triplet X'43'”. [MODCA-5-1415]|
| X'4B' | | **Measurement Units** Mandatory. Must occur once. See “Measurement Units Triplet X'4B'”. [MODCA-5-1416]|
| X'4C' | | **Object Area Size** Mandatory. Must occur once. See “Object Area Size Triplet X'4C'”. [MODCA-5-1417] [MODCA-5-1418]|
| X'4E' | | **Color Specification** Optional. May occur once. Specifies a color for the object area. When this triplet is specified on an object area, the complete object area becomes foreground data that is colored with the specified color before any object data is added to the area. If the default mixing rules are used, the object area overpaints (covers) any data that is underneath. See “Color Specification Triplet X'4E'”. [MODCA-5-1419]|
| X'70' | | **Presentation Space Reset Mixing** Optional. May occur once. If this triplet specifies a reset to the color of medium (BgMxFlag=B'1'), the reset takes place at the point in the data stream where the triplet occurs. This triplet may not appear with a Presentation Space Mixing Rules triplet. See “Presentation Space Reset Mixing Triplet X'70'”. [MODCA-5-1423]|
| X'71' | | **Presentation Space Mixing Rules** Optional. May occur once. This triplet may not appear with a Presentation Space Reset Mixing triplet. See “Presentation Space Mixing Rules Triplet X'71'”. [MODCA-5-1424]|

**Note:** The Color Specification (X'4E') triplet is not permitted on the OBD for presentation text that may optionally occur in the AEG for a page or overlay. [MODCA-5-1420]

**Implementation Note:** The Presentation Space Mixing Rules (X'71') triplet is currently not used in AFP environments.

**Architecture Note:** Triplets that affect the object area presentation space are processed in the order in which they occur on the OBD. For example, if a Presentation Space Reset Mixing (X'70') triplet on the OBD is followed by a Color Specification (X'4E') triplet, the object area is colored with the color specified in the X'4E' triplet and covers any data underneath it regardless of whether the X'70' triplet specified “reset to color of medium” or “do not reset to color of medium”. If a Color Specification (X'4E') triplet is followed by a X'70' triplet, and if the X'70' triplet specified “reset to color of medium”, the object area is colored with color of medium. If the X'70' triplet specified “do not reset to color of medium”, the X'70' triplet does not change the object area and it remains foreground data colored with the color specified by the X'4E' triplet.

#### OBD Exception Condition Summary

**X'01'** The OBD structured field contains both a Presentation Space Reset Mixing triplet and a Presentation Space Mixing Rules triplet. [MODCA-5-1425]


### Object Area Position (OBP)

The Object Area Position structured field specifies the origin and orientation of the object area, and the origin and orientation of the object content within the object area. [MODCA-5-1426]

#### OBP (X'D3AC6B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AC6B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1427]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1428]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1429]|
| 0 | CODE | **OAPosID** | X'01'–X'7F' | The object area position identifier | M | X'06' [MODCA-5-1430]|

One repeating group in the following format: [MODCA-5-1430]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 23 | Total length of this repeating group | M | X'06' [MODCA-5-1431]|
| 2–4 | SBIN | **XoaOset** | -32,768–32,767 | X-axis origin of the object area | M | X'06' [MODCA-5-1432]|
| 5–7 | SBIN | **YoaOset** | -32,768–32,767 | Y-axis origin of the object area | M | X'06' [MODCA-5-1433]|
| 8–9 | CODE | **XoaOrent** | | The object area's X-axis rotation from the X axis of the reference coordinate system | M | X'06' [MODCA-5-1434]|
| 10–11 | CODE | **YoaOrent** | | The object area's Y axis rotation from the X axis of the reference coordinate system | M | X'06' [MODCA-5-1435]|
| 12 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1438]|
| 13–15 | SBIN | **XocaOset** | -32,768–32,767 | X-axis origin for object content | M | X'06' [MODCA-5-1439]|
| 16–18 | SBIN | **YocaOset** | -32,768–32,767 | Y-axis origin for object content | M | X'06' [MODCA-5-1440]|
| 19–20 | CODE | **XocaOrent** | X'0000' | The object content's X-axis rotation from the X axis of the object area coordinate system | M | X'06' [MODCA-5-1441]|
| 21–22 | CODE | **YocaOrent** | X'2D00' | The object content's Y-axis rotation from the X axis of the object area coordinate system | M | X'06' [MODCA-5-1442]|
| 23 | CODE | **RefCSys** | X'00', X'01', X'05' | Reference coordinate system:<br>X'00' Page/overlay; origin by IPS<br>X'01' Page/overlay; standard origin<br>X'05' Retired value | M | X'06' [MODCA-5-1443]|

**Rotation Formats (XoaOrent, YoaOrent)**

| **Bits** | **Meaning** |
| :--- | :--- |
| 0–8 | Degrees rotation (0–359) |
| 9–14 | Minutes rotation (0–59) |
| 15 | Reserved |

#### OBP Semantics

**OAPosID** Specifies an identifier for this Object Area Position structured field that is unique within the environment group. It is used to associate the Object Area Position structured field with the Object Area Descriptor structured field.

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**XoaOset** Specifies the offset along the X axis, Xpg or Xol, of the referenced coordinate system to the origin of the X axis, Xoa, for the object area coordinate system. The value is expressed in terms of the number of referenced coordinate system X-axis measurement units.

**YoaOset** Specifies the offset along the Y axis, Ypg or Yol, of the referenced coordinate system to the origin of the Y axis, Yoa, for the object area coordinate system. The value is expressed in terms of the number of referenced coordinate system Y-axis measurement units.

**XoaOrent** Specifies the amount of clockwise rotation of the object area's X axis, Xoa, about its defined origin relative to the X axis of the reference coordinate system.

**YoaOrent** Specifies the amount of clockwise rotation of the object area's Y axis, Yoa, about its defined origin relative to the X axis of the reference coordinate system. The **YoaOrent** value must be 90 degrees greater than the **XoaOrent** value or a X'01' exception condition exists. [MODCA-5-1444]

**Note:** If the object area orientation is such that the sum of the object area origin offset and the object area extent exceeds the size of the including presentation space in either the X or Y direction, all of the object area will not fit. If an attempt is made to present data in the portion that falls outside, that portion of the data is not presented, and a X'01' exception condition exists. [MODCA-5-1445]

**XocaOset** Specifies the offset along the X axis of the object area coordinate system, Xoa, to the X origin of the object content. The value is expressed in terms of the number of object area coordinate system X-axis measurement units.

**YocaOset** Specifies the offset along the Y axis of the object area coordinate system, Yoa, to the Y origin of the object content. The value is expressed in terms of the number of object area coordinate system Y-axis measurement units.

**Notes:**

1. The object content is developed in the data object presentation space; within the context [MODCA-5-1446] of this structured field the two terms are synonymous.
2. The **XocaOset** and **YocaOset** parameters are used only when a position or position and [MODCA-5-1447] trim mapping is specified. They are ignored for all other mappings.

**XocaOrent** Specifies the amount of rotation of the object content's X axis about its defined origin relative to the X axis of the object area coordinate system.

**YocaOrent** Specifies the amount of rotation of the object content's Y axis about its defined origin relative to the X axis of the object area coordinate system.

**Note:** If the object content orientation is such that the origin offset exceeds the size of the object area presentation space, the object data will not fit. If the mapping option is position (X'00') and an attempt is made to present data outside the object area, that portion is not presented and a X'01' exception condition exists.

**RefCSys** Specifies the coordinate system and origin used to position the object area.

| **Value** | **Description** |
| :--- | :--- |
| X'00' | Used only if the object is part of a page segment. The reference coordinate system is the including page or overlay coordinate system. Origin is defined by IPS. |
| X'01' | The reference coordinate system is the including page or overlay coordinate system. Standard origin (0,0). |
| X'05' | Retired value. See “Retired Parameters”. |

#### OBP Exception Condition Summary

**X'01'** This exception condition exists when:
* The value specified for **YoaOrent** is not 90 degrees greater rotation than the value specified [MODCA-5-1448] for **XoaOrent**.
* An attempt is made to present data outside the presentation space of the containing [MODCA-5-1449] coordinate system.
* The mapping option is position and an attempt is made to present data outside the object [MODCA-5-1450] area presentation space. [MODCA-5-1451]


### Object Container Data (OCD)

The Object Container Data structured field contains the data for an object carried in an object container. See “Object Type Identifiers” for the list of object types that may be carried in an object container. [MODCA-5-1452]

#### OCD (X'D3EE92') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3EE92'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1453]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1454]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1455]|
| 0–n | UNDF | **ObjCdat** | | Up to 32,759 bytes of object data | O | X'00' [MODCA-5-1456]|

#### OCD Semantics

**ObjCdat** Contains the object data.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-1457]
