# Chapter 17. Compliance
This chapter provides a complete description of the IPDS functional divisions, IPDS support requirements, and
migration functions.
IPDS Functional Divisions
IPDS architecture is divided into several functional areas called command sets, each representing a major
printer capability. A command set consists of IPDS commands, including semantics (the meaning of the
command and its parameters), syntax (the command structure/format), and the architecturally-valid values for
each field in the command. The architecture also contains a registry of exception-reporting codes for error
conditions in each of its command sets and for printer-related failure, fault, or host-notification conditions.
Each command set is further divided into at least one subset of defined function and a subset of optional
function. Some command sets contain more than one subset of defined function and some command sets are
defined to carry object data.
The IPDS command-set design supports several printer, intermediate-device, pre-processor, and post-
processor technologies. Product developers can match command-set implementations to the specific needs of
their product.


Figure 119 illustrates the IPDS functional divisions.
Figure 119. IPDS Functional Divisions
Device Control - DC1 (mandatory)
Defined Subsets within IPDS Command Sets
IM
Image
IM1
IO
Image
IO1
Bar
Code Object
Container
BC1
Page
Segment
Overlay
Loaded Font
OC1 PS1 OL1 LF2 LF1LF3
Text
TX1
Graphics
GR1 LF4
Metadata
MO1
Each command set contains one or more defined subsets as shown. All command sets also provide optional
function.
PTOCA
PT2
PTOCA
PT1
PTOCA
PT3
IMD1 IOCA
FS10
IOCA
FS11
IOCA
FS45
IOCA
FS48
IOCA
FS42
GOCA
DR/2V0
BCOCA
BCD1
Graphics
Text
IM
Image
IO Image
Bar
Code
Data Towers
IOCA
FS40
BCOCA
BCD2
GOCA
GRS3
PTOCA
PT4
IOCA
FS14
MOCA
MS1
Metadata
Each data tower contains at least one level. Some IPDS printers support additional (optional) data-tower
function for text, IO-Image, graphics, and bar code data. [IPDS-17-001]


IPDS Command Sets and Command-Set Subsets
The IPDS architecture contains the following command sets:
• Device-Control command set [IPDS-17-002]
• Text command set [IPDS-17-003]
• IM-Image command set [IPDS-17-004]
• IO-Image command set [IPDS-17-005]
• Graphics command set [IPDS-17-006]
• Bar Code command set [IPDS-17-007]
• Object-Container command set [IPDS-17-008]
• Metadata command set [IPDS-17-009]
• Page-Segment command set [IPDS-17-010]
• Overlay command set [IPDS-17-011]
• Loaded-Font command set [IPDS-17-012]
IPDS Architecture defines at least one subset of defined function for each command set. The Sense Type and
Model reply specifies the subset of each command set supported by a printer.
Note: Multiple subsets of a single command set are not necessarily hierarchically related.
Device Control This command set contains the IPDS commands that initialize the environment for a logical
page, communicate device controls, manage resources, and handle the acknowledgment
protocol. The Device-Control command set contains one subset, DC1.
DC1: The DC1 subset contains the mandatory IPDS commands and corresponding
mandatory field values in the Device-Control command set. All IPDS printers must support the
DC1 subset.
Text This command set contains the IPDS commands, excluding the data field of the Write Text
command, for presenting text information in a page, a page segment, or an overlay. The Text
command set contains one subset, TX1.
TX1: The TX1 subset contains the IPDS commands and corresponding field values, excluding
the data field of the Write Text command, that are mandatory if text is supported.
IM Image This command set contains the IPDS commands, excluding the data field of the Write Image
command, for presenting images in a page, a page segment, or an overlay. The IM-Image
command set contains one subset, IM1.
IM1: The IM1 subset contains the IPDS commands and corresponding field values, excluding
the data field of the Write Image command, that are mandatory if IM-Image is supported.
IO Image This command set contains the IPDS commands, excluding the data field of the Write Image 2
command, for presenting images in a page, a page segment, or an overlay. The IO-Image
command set provides functions, such as image compression and grayscale, not provided by
the IM-Image command set. The IO-Image command set contains one subset, IO1.
IO1: The IO1 subset contains the IPDS commands and corresponding field values, excluding
the data field of the Write Image 2 command, that are mandatory if IO-Image is supported
Graphics This command set contains the IPDS commands, excluding the data field of the Write
Graphics command, for presenting graphics in a page, a page segment, or an overlay. The
Graphics command set contains one subset, GR1.
GR1: The GR1 subset contains the IPDS commands and corresponding field values,
excluding the data field of the Write Graphics command, that are mandatory if graphics is
supported.


Bar Code This command set contains the IPDS commands, excluding the data field of the Write Bar
Code command, for presenting machine-readable bar code information in a page, a page
segment, or an overlay. The Bar Code command set contains one subset, BC1.
BC1: The BC1 subset contains the IPDS commands and corresponding field values,
excluding the data field of the Write Bar Code command, that are mandatory if bar codes are
supported.
Object
Container
This command set contains the IPDS commands to store and present IPDS constructs
containing data whose definitions are not controlled by an AFPC presentation architecture.
These stored constructs are called object containers. The Object-Container command set
contains one subset, OC1.
OC1: The OC1 subset contains the IPDS commands and corresponding field values in the
Object-Container command set needed to support presentation and non-presentation object
containers.
Metadata This command set contains the IPDS commands, excluding the data field of the Write
Metadata command, for associating metadata with objects. The Metadata command set
contains one subset, MO1.
MO1: The MO1 subset contains the IPDS commands and corresponding field values in the
Metadata command set that are mandatory if metadata is supported.
Page Segment This command set contains the IPDS commands to store and present IPDS page segment
constructs containing text, graphics, image, and bar code information. These stored
constructs, that can be merged with a logical page to assume the current environment, are
called page segments. The Page-Segment command set contains one subset, PS1.
PS1: The PS1 subset contains the IPDS commands and corresponding field values that are
mandatory if page segments are supported.
Overlay This command set contains the IPDS commands to store and present IPDS overlay constructs
containing text, image, graphics, bar code, and object container information. These stored
constructs are called overlays. The Overlay command set contains one subset, OL1.
OL1: The OL1 subset contains the IPDS commands and corresponding field values that are
mandatory if overlays are supported.
Loaded Font This command set contains the IPDS commands to load font information. The Loaded-Font
command set contains the following subsets: LF1, LF2, LF3, and LF4. Printers can support
any combination of these subsets.
LF1: The LF1 subset consists of the IPDS commands and corresponding field values in the
Loaded-Font command set needed to support fully-described fonts, fully-described font
sections, and font indexes.
LF2: The LF2 subset consists of the IPDS commands and corresponding field values needed
to support symbol-set coded fonts.
LF3: The LF3 subset consists of the IPDS commands and corresponding field values in the
Loaded-Font command set needed to support font character sets and code pages.
LF4: The LF4 subset consists of the IPDS commands and corresponding field values in the
Loaded-Font command set needed to support code pages. This subset is used when code
pages are supported for TrueType/OpenType fonts, but the LF3 subset is not supported. [IPDS-17-013]


Data Divisions
For some IPDS command sets, a data tower defines the data carried in the Write command of the
corresponding IPDS command set. A data tower is divided into levels. A higher level of a data tower consists of
all lower levels plus some set of additional function. Some data tower levels are defined and controlled by other
architectures and are simply registered in the IPDS architecture. Table 69 summarizes this. [IPDS-17-014]

### Table 69. Command-Set and Data-Tower Summary

| IPDS Command Set | Purpose | Command-Set Subsets | Data Tower Levels | Architecture Describing the Data Tower |
| :--- | :--- | :--- | :--- | :--- |
| Device Control | control printer | DC1 | none [IPDS-17-015]| |
| Text | print text data | TX1 | PT1, PT2, PT3, PT4 | PTOCA [IPDS-17-016]|
| IM Image | print IM-Image data | IM1 | IMD1 | IPDS [IPDS-17-017]|
| IO Image | print IO-Image data | IO1 | FS10, FS11, FS14, FS40, FS42, FS45, FS48 | IOCA [IPDS-17-018]|
| Graphics | print graphics data | GR1 | DR/2V0, GRS3 | GOCA [IPDS-17-019]|
| Bar Code | print bar code data | BC1 | BCD1, BCD2 | BCOCA [IPDS-17-020]|
| Object Container | control object containers | OC1 | none | Specific to object container [IPDS-17-021]|
| Metadata | control metadata | MO1 | MS1 | MOCA [IPDS-17-022]|
| Page Segment | control page segments | PS1 | none [IPDS-17-023]| |
| Overlay | control overlays | OL1 | none [IPDS-17-024]| |
| Loaded Font | control coded fonts | LF1, LF2, LF3, LF4 | none [IPDS-17-025]| |
Data Towers and Data-Tower Levels
There is a data tower for each of the following command sets:
• Text
• IM Image [IPDS-17-026]
• IO Image [IPDS-17-027]
• Graphics [IPDS-17-028]
• Bar Code [IPDS-17-029]
• Metadata [IPDS-17-030]
At least one level of every data tower is defined; some towers also include optional function. The Sense Type
and Model reply specifies the level of each data tower supported by a printer. Figure 120
illustrates the data towers and data-tower levels. The data towers are:
Text This data tower contains Presentation Text Object Content Architecture (PTOCA) control
sequences and code points, contained in the data field of the Write Text command. The
control sequences are used to present text information in a page, a page segment, or an
overlay. The text data tower contains four presentation text (PT) levels, PT1, PT2, PT3, and
PT4, defined by the PTOCA architecture. [IPDS-17-031]


IM Image This data tower contains IM-Image data contained in the data field of the Write Image
command. IM-Image data can be presented in a page, a page segment, or an overlay. The IM-
Image data tower contains one level, IMD1, defined by the IPDS architecture.
IO Image This data tower contains Image Object Content Architecture (IOCA) self-defining fields,
contained in the data field of the Write Image 2 command, and used to present image data in a
page, a page segment, or an overlay. The IO-Image data tower contains several levels, FS10,
FS11, FS14,
FS40, FS42, FS45, and FS48 , defined by the IOCA Architecture.
Graphics This data tower contains Graphics Object Content Architecture (GOCA) drawing orders,
contained in the data field of the Write Graphics command, and used to present graphics in a
page, a page segment, or an overlay. The graphics data tower contains two levels, DR/2V0
and GRS3, defined by the GOCA Architecture.
Bar Code This data tower contains Bar Code Object Content Architecture (BCOCA) parameters,
contained in the data field of the Write Bar Code command, and used to present machine-
readable bar code information in a page, a page segment, or an overlay. The bar code data
tower contains two levels, BCD1 and BCD2, defined by the BCOCA architecture.
Metadata This data tower contains a Metadata Object Content Architecture (MOCA) metadata object,
contained in the data field of the Write Metadata command, and used to associate metadata
with an object, or objects, in the IPDS data stream. The metadata data tower contains one
level, MS1, defined by the MOCA Architecture.
Figure 120. Data Towers and Data-Tower Levels
PTOCA
PT2
PTOCA
PT1
PTOCA
PT3
IMD1 IOCA
FS10
IOCA
FS11
IOCA
FS45
IOCA
FS48
IOCA
FS42
GOCA
DR/2V0
BCOCA
BCD1
Graphics
Text
IM
Image
IO Image
Bar
Code
Data Towers
IOCA
FS40
BCOCA
BCD2
GOCA
GRS3
PTOCA
PT4
IOCA
FS14
MOCA
MS1
Metadata


IPDS Support Requirements
To claim support of the IPDS architecture, a printer product must do the following:
• Implement the DC1 subset of the device control command set. [IPDS-17-032]
• Implement at least one of the following IPDS command set subsets: [IPDS-17-033]
– TX1
– IM1
– IO1
– GR1
– BC1
• Generate mandatory IPDS exceptions in accordance with the following rules: [IPDS-17-034]
– Mandatory exceptions must be generated by a printer only if the printer supports the function or command
to which the exception applies.
– Mandatory exceptions that can be caused by multiple conditions must be generated by a printer under all
the conditions that are applicable to the functions and commands supported by the printer.
– A mandatory exception can be presented with any of the action codes registered for the exception ID.
– Wherever an OCA-defined exception is classified as mandatory, the IPDS architecture requires that the
exception be generated regardless of whether the OCA specifies the exception to be mandatory or
optional. For all other OCA-defined exceptions, the IPDS architecture defers the mandatory/optional
specification to the appropriate OCA.
To claim support of the IPDS architecture, a presentation-services product must do the following:
• All commands generated by the presentation services must conform to the IPDS state diagram. [IPDS-17-035]
• The syntax for all commands generated by the presentation services must conform with that defined by the [IPDS-17-036]
IPDS Architecture.
• Accept syntactically-correct Acknowledge Replies and process the data contained therein, including: [IPDS-17-037]
– Page and copy counters
– STM reply
– XOH-OPC reply
– XOA-RRL reply
– Exception IDs and sense data
Command-Set Support Requirements
To claim support of the Text, IM-Image, IO-Image, Graphics, Bar Code, or Metadata command sets, a printer
product must implement a defined subset of the command set. Printers can support additional, optional
elements of the command set. In addition, a level of the corresponding data tower must be implemented.
To claim support of any other IPDS command set, a printer product must implement a defined subset of the
command set. Printers can support additional, optional elements of the command set.
Data Tower Support Requirements
To claim support of a data tower, a printer product must implement at least one level of the data tower. Printers
can support additional tower levels, and additional optional elements of the data tower. [IPDS-17-038]


IPDS Support for MO:DCA Interchange Set IS/3
One of the fundamental promises of the AFP Consortium to customers is more consistent implementations and
better interoperability of products. This goal has been addressed with the formal definition of a MO:DCA
interchange set (called IS/3) and the following list of the functions that an IPDS printer must support to ensure
that all IS/3-compliant documents can be printed.
Required IPDS Command Set support
• Device Control command set at the DC1 level [IPDS-17-039]
• Text command set at the TX1 level plus support for the PTOCA PT3 subset, including the following: [IPDS-17-040]
– Set Text Color (STC) control sequence Precision parameter (byte 6) is retired
– Exception ID X'021A..03' (Invalid Unicode Data) is required if Unicode data is supported
– Highlight color space clarifications for Indexed CMRs
• IO-Image command set at the IO1 level plus support for the FS10 and FS45 function sets (an FS45 vector [IPDS-17-041]
must be returned in the STM reply plus either an FS10 vector, an FS11 vector, or both). The following
additional support is required:
– Extended IOCA Bilevel color support (STM property pair X'4401')
– Extended IOCA Tile-Set-Color support (STM property pair X'4402')
• Graphics command set at the GR1 level plus support for the GOCA GRS3 subset [IPDS-17-042]
• Bar Code command set at the BC1 level plus support for the BCOCA BCD2 subset [IPDS-17-043]
• Object Container command set at the OC1 level plus support for the following object types: [IPDS-17-044]
As resources (downloaded or activated in home state)
– CMR (Color Management Resource)
– CMT (Color Mapping Table)
– GIF (Graphics Interchange Format)
– IOCA Tile Resource
– JPEG (Joint Photographic Experts Group) AFPC JPEG Subset
– TIFF (Tag Image File Format) AFPC Subset
– TIFF (Tag Image File Format) with transparency
– TIFF (Tag Image File Format) without transparency
– TIFF multiple-image file with transparency
– TIFF multiple-image file without transparency
– TrueType/OpenType Font
– TrueType/OpenType Font Collection
As presentation objects (supported in page and overlay states and includable via IDO command)
– GIF (Graphics Interchange Format)
– JPEG (Joint Photographic Experts Group) AFPC JPEG Subset
– TIFF (Tag Image File Format) AFPC Subset
– TIFF (Tag Image File Format) with transparency
– TIFF (Tag Image File Format) without transparency
– TIFF multiple-image file with transparency
– TIFF multiple-image file without transparency
• Page Segment command set at the PS1 level [IPDS-17-045]
• Overlay command set at the OL1 level [IPDS-17-046]
• Code pages (identified by support for the Loaded-font command set at the LF3 or LF4 level); note that this [IPDS-17-047]
includes support for the LCP and LCPC commands, but excludes support for FOCA fonts. [IPDS-17-048]


Additional Required Support
• Support for negative object-area offset values in the BCOC, DOOC, IOC, GOC, and OCOC self-defining [IPDS-17-049]
fields
• Support for the full range of logical page offset values in the LPP command [IPDS-17-050]
• Support for the 0, 90, 180, and 270 degree object area orientations in the BCAP , IAP , GAP , andOCAP self- [IPDS-17-051]
defining fields
• Support for all defined values within units-per-unit-base fields (X'0001'–X'7FFF') [IPDS-17-052]
• STM properties: [IPDS-17-053]
– All function listed for IS/3 is supported (STM property pair X'FC00'); all IS/3 function that is not covered by
STM property pairs or OPC self-defining fields is implicitly covered by this property pair
– Multiple copy & copy-subgroup support in LCC (STM property pair X'6001')
– Media-source-selection support in LCC (STM property pair X'6002')
– Media-destination-selection support in LCC (STM property pair X'6003')
– Explicit page placement and orientation support in the LPP command; includes support for all page
placement values (STM property pair X'6101')
– Logical page and object area coloring (STM property pair X'6201')
– If at least one sheet finishing operation is supported, Apply Finishing Operations command (STM property
pair X'7002')
– Set Presentation Environment command (STM property pair X'7008')
– Activate Resource command (STM property pair X'702E')
– Presentation Fidelity Control (STM property pair X'7034')
– Invoke CMR (ICMR) command (STM property pair X'706B')
– Rasterize Presentation Object (RPO) command (STM property pair X'707B')
– XOA Alternate Offset Stacker (STM property pair X'800A') or XOA Control Edge Marks (STM property pair
X'800C')
– If at least one finishing operation is supported, Specify Group Operation (STM property pair X'9003') –
Operation: Finish
– If at least one finishing operation is supported, Define Group Boundary (STM property pair X'9004')
– XOH Select Input Media Source (STM property pair X'9015')
– XOH Set Media Origin (STM property pair X'9016')
– Indexed CMRs (STM property pair X'E004')
– Pass-through audit color-conversion CMRs (STM property pair X'E102')
– Data-object font support (STM property pair X'F204')
– Color Management (STM property pair X'F205')
– Device Appearance (STM property pair X'F206')
– Simplex N-up supported in LCC (STM property pair X'F704')
– If duplex is supported, Simplex and duplex N-up supported in LCC (STM property pair X'F804')
– All text orientations (STM property pair X'50FF')
– Basic OCA color support in text, IO-Image, graphics, and bar code data (all Standard OCA color values
recognized including color of medium, STM property pair X'4022' or property pair X'4003' along with all
color values supported) [IPDS-17-054]


– Unpadded RIDIC recording algorithm support (STM property pair X'5204')
– Image Resolution (X'9A') triplet support (STM property pair X'5800')
– Scale to fill mapping option for IOCA, and Object Containers (STM property pair X'F301')
– Data-object-resource support (STM property pair X'1201')
– IOCA image as a resource and includable via IDO (STM property pair X'1202')
– Support for all four orientations in the Include Overlay command (STM property pair X'A004')
– Extended (Unicode mapping) code page support (STM property pair X'B005')
• OPC self-defining fields: [IPDS-17-055]
– Return either Input media ID user name or OID in OPC reply (SDF X'0001')
– AR command support for RT = data-object font, RIDF = data-object-font format (SDF X'000B')
– If at least one finishing operation is supported, Finish group-operation support (SDF X'0012')
– Print Fidelity Control triplets supported (SDF X'0016')
◦ Color Fidelity (X'75') triplet
◦ Text Fidelity (X'86') triplet
◦ If at least one finishing operation is supported, Finishing Fidelity (X'88') triplet
◦ CMR Tag Fidelity (X'96') triplet
– If at least one setup ID is supported and active, Printer Setup (SDF X'0017') – no specific setups required
– If at least one finishing operation is supported, Finishing Operations (SDF X'0018') – no specific operations
required
Additional required support for the MO:DCA GA (Graphic Arts) Function
Set
• Support for the following object types: [IPDS-17-056]
As resources (downloaded or activated in home state)
– PDF multiple-page file without transparency
– PDF multiple-page file with transparency
– PDF single page without transparency
– PDF single page with transparency
– PDF (Portable Document Format) resource object
As presentation objects (supported in page and overlay states and includable via IDO command)
– PDF multiple-page file without transparency
– PDF multiple-page file with transparency
– PDF single page without transparency
– PDF single page with transparency
• STM properties: [IPDS-17-057]
– All function listed for MO:DCA GA is supported (STM property pair X'FC01'); all MO:DCA GA function that
is not covered by STM property pairs or OPC self-defining fields is implicitly covered by this property pair
– Object Container Presentation Space Size (X'9C') triplet support for PDF objects (STM property pair
X'1203')


Migration Functions
Some IPDS functions are provided for migration and coexistence. IPDS products might not provide complete
support for these functions in all AFP environments. The migration functions include:
• XOH Set Media Size (an optional Device Control command) [IPDS-17-058]
• IM-Image command set [IPDS-17-059]
• Optional compression algorithms (for IO Image) [IPDS-17-060]
• Downloaded symbol sets (using the LSS command) [IPDS-17-061]
• Font-modification parameters (bits 3–7 of LFE byte 14) [IPDS-17-062]
• Ordered pages (bit 0 of LPD byte 15) [IPDS-17-063]




