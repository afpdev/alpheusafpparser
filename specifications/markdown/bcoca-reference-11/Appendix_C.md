Appendix C. IPDS Environment
Intelligent Printer Data Stream (IPDS) is the AFPC data stream for controlling advanced function printer
devices. It supports all points addressable printing functions that allow text and individual blocks of image,
graphics, and bar code data to be positioned and presented at any point on a printed page.
All IPDS printer commands are defined in structured field format that is described in the Intelligent Printer Data
Stream Reference. Refer to this document for a description of the architecture.
IPDS Bar Code Command Set
The IPDS bar code command set contains the commands and controls for presenting bar code information on
a page, a page segment, or an overlay.
The BCOCA bar code object processor is invoked to process the BCOCA data structures contained within the
IPDS bar code commands. The BCOCA data structures must contain the BCD1 or BCD2 subset range of field
values and may, optionally, contain the full range of field values. The bar code object processor generates the
requested bar code symbols on a page, page segment, or overlay.
The IPDS Bar Code Command Set consists of the following commands:
• Write Bar Code Control [BCOCA-C-001]
• Write Bar Code [BCOCA-C-002]
An IPDS bar code object has the following basic structure:
Write Bar Code Control command (contains the BCOCA BSD structure and other information)
Zero or more Write Bar Code commands (contains the BCOCA BSA structure); there is one Write Bar
Code command per bar code symbol
End command
Write Bar Code Control Command
The Write Bar Code Control command is sent to the printer to direct it to establish initialization parameters for
one or more bar code symbols of the same type on the page, page segment, or overlay. The parameters of this
command define the bar code presentation space, define the bar code object area, map the bar code
presentation space to the bar code object area, and establish the initial conditions for printing the bar code.
The Write Bar Code Control command contains three self-defining fields in the following order:
1. Bar Code Area Position (BCAP) defines the position and orientation of the bar code object area. [BCOCA-C-003]
2. Bar Code Output Control (BCOC) is optional and specifies the size of the bar code object area, the offset of [BCOCA-C-004]
the presentation space in the bar code object area, and the mapping of the bar code presentation space
into the bar code object area.
The only valid mapping option is position. For the position mapping option, the top-left corner of the bar
code presentation space, also known as the origin of the bar code presentation space, is offset from the
origin of the bar code object area by the X and Y offset values specified in the BCOC command. If the
BCOC is not specified, the origin of the bar code presentation space is coincident with the origin of the bar
code object area. Portions of the bar code presentation space may fall outside of the bar code object area
without an exception condition being raised. However, exception condition EC-1100 exists if any portion of
the bar code, including the bar and space patterns, the Bearer Bars (Interleaved 2-of-5), the Identification
Bars and USPS Banner (Intelligent Mail Container Barcode or Intelligent Mail Package Barcode), the RED
TAG indicator (Royal Mail RED TAG (deprecated)), the zipper pattern and contrast block (MaxiCode), any [BCOCA-C-005]


image printed in conjunction with a QR Code symbol (QR Code with Image), and the HRI, is not totally
contained within the bar code object area.
3. Bar Code Data Descriptor (BCDD) defines the bar code presentation space size, the bar code type to be [BCOCA-C-006]
generated, and other associated bar code symbology parameters.
The following defines the format of the BCDD: [BCOCA-C-007]
### Table 39. IPDS Bar Code Data Descriptor (BCDD)

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | LENGTH | X'001B' – end of BCDD | Length of BCDD | X'001B' – end of BCDD | X'001B' – end of BCDD [BCOCA-C-008]|
| 2–3 | CODE | SDF ID | X'A6EB' | BCDD Self-defining-field ID | X'A6EB' | X'A6EB' [BCOCA-C-009]|
| 4–26 | UNDF | BSD | | Bar Code Symbol Descriptor; see “Bar Code Symbol Descriptor (BSD)” for parameter definitions. [BCOCA-C-010]| | |
| 27–end | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets. | Triplets not supported in BCD1 [BCOCA-C-011]| |
| | | | X'4E' | Color Specification triplet | | Color Specification (X'4E') triplet [BCOCA-C-012]|
When a Color Specification (X'4E') triplet is present in the BCDD, this triplet overrides the color value specified
in BSD bytes 15-16. If multiple X'4E' triplets are specified, the last one specified is used and the others are
ignored. Printers that do not support extended bar code color support ignore all X'4E' triplets.
Write Bar Code Command
The Write Bar Code command transmits data to be printed as a single bar code symbol, parameters to specify
special functions for 2D bar codes, and flags to specify attributes specific to the symbol. The Write Bar Code
command also contains the parameters to position the bar code symbol within the bar code object area. The
data portion of the WBC is defined in “Bar Code Symbol Data (BSA)” .
IPDS Environment


Additional Related Commands
The following commands are used for query and resource management functions. Only an overview of these
commands is presented in this manual. The commands are described in detail in the Intelligent Printer Data
Stream Reference
Sense Type and Model (STM): Requests information from the printer that identifies the type and model of the
device and the command sets supported. The information requested is returned in the Special Data Area of the
Acknowledge Reply to the STM command. The command sets and data levels supported are also returned as
part of the acknowledgement data.
Execute Order Homestate - Obtain Printer Characteristics (XOH OPC): Requests information from the printer
that identifies various characteristics of the device. The characteristics include information about the bar code
symbologies supported, printable area currently available, coded font resolution, and color support.
Execute Order Anystate - Request Resource List (XOA RRL): Requests the printer to return a specified list of
available resources, that is, fonts, overlays, and page segments, in the Acknowledge Reply to this command.
This information can be used by host application programs to perform a variety of resource management
functions.
Load Font Equivalence (LFE): This command is sent to the printer to map Local Identifiers referenced in the
BCDD to a specific font in the printer.
Font Control Commands: The host can use the following commands to activate and deactivate fonts for
printing HRI information:
• Activate Resource [BCOCA-C-013]
• Load Code Page [BCOCA-C-014]
• Load Code Page Control [BCOCA-C-015]
• Load Font [BCOCA-C-016]
• Load Font Character Set [BCOCA-C-017]
• Load Font Control [BCOCA-C-018]
• Load Font Equivalence [BCOCA-C-019]
• Load Font Index [BCOCA-C-020]
• Load Symbol Set [BCOCA-C-021]
• Deactivate Font [BCOCA-C-022]
Image Control Commands: The host can use the following commands to download and later include image
objects, as well as to create a mapping from a BCOCA Image local ID to the HAID of the image object:
• Write Image Control 2 and Write Image 2 – Downloads an IO-image object in home state for potential later [BCOCA-C-023]
use in a QR Code with Image bar code.
• Write Object Container Control and Write Object Container – Downloads an object container image object in [BCOCA-C-024]
home state for potential later use in a QR Code with Image bar code.
• Data Object Resource Equivalence or Data Object Resource Equivalence 2 – Can be used to map a BCOCA [BCOCA-C-025]
Image local ID, specified in the QR Code with Image special-function parameters, to the HAID of a
downloaded image object in IPDS.
• Include Data Object – Although this command would not be used in the case of an image object being placed [BCOCA-C-026]
in conjunction with a QR Code symbol, it is the IPDS command that most closely resembles the functionality
provided in BCOCA when placing the image object with the correct location, size, and orientation.
IPDS Environment


BCOCA Exception Conditions and IPDS Exception IDs
The IPDS Architecture defines its own exception condition codes, called exception IDs, which consist of three
bytes. BCOCA exception conditions are mapped to IPDS exception IDs by mapping the two-byte BCOCA code
to the last two bytes of the IPDS exception ID; the first byte is either X'02', X'04', or X'08'. The IPDS
Architecture also defines its own exception responses (called AEAs and PCAs). In some cases, this exception
response is the same as the standard action defined by BCOCA. Where it is not, the IPDS exception response
overrides the BCOCA standard action. T able 40shows the mapping of BCOCA exception conditions to IPDS
exception IDs.
### Table 40. BCOCA Exception Conditions and IPDS Exception IDs

| BCOCA Exception Condition | IPDS Exception ID |
| :--- | :--- |
| EC-0300 | X'0403..00' [BCOCA-C-027]|
| EC-0400 | X'0404..00' [BCOCA-C-028]|
| EC-0500 | X'0405..00' [BCOCA-C-029]|
| EC-0505 | X'0205..05' [BCOCA-C-030]|
| EC-0600 | X'0406..00' [BCOCA-C-031]|
| EC-0605 | X'0206..05' [BCOCA-C-032]|
| EC-0610 | X'0406..10' [BCOCA-C-033]|
| EC-0611 | X'0406..11' [BCOCA-C-034]|
| EC-0700 | X'0407..00' [BCOCA-C-035]|
| EC-0705 | X'0207..05' [BCOCA-C-036]|
| EC-0800 | X'0408..00' [BCOCA-C-037]|
| EC-0805 | X'0408..05' [BCOCA-C-038]|
| EC-0900 | X'0409..00' [BCOCA-C-039]|
| EC-0A00 | X'040A..00' [BCOCA-C-040]|
| EC-0B00 | X'040B..00' [BCOCA-C-041]|
| EC-0C00 | X'040C..00' [BCOCA-C-042]|
| EC-0E00 | X'040E..00' [BCOCA-C-043]|
| EC-0F00 | X'040F..00' [BCOCA-C-044]|
| EC-0F01 | X'040F..01' [BCOCA-C-045]|
| EC-0F02 | X'040F..02' [BCOCA-C-046]|
| EC-0F03 | X'040F..03' [BCOCA-C-047]|
| EC-0F04 | X'040F..04' [BCOCA-C-048]|
| EC-0F05 | X'040F..05' [BCOCA-C-049]|
| EC-0F06 | X'040F..06' [BCOCA-C-050]|
| EC-0F07 | X'040F..07' [BCOCA-C-051]|
| EC-0F08 | X'040F..08' [BCOCA-C-052]|
| EC-0F09 | X'040F..09' [BCOCA-C-053]|
| EC-0F0A | X'040F..0A' [BCOCA-C-054]|
| EC-0F0B | X'040F..0B' [BCOCA-C-055]|
| EC-0F0C | X'040F..0C' [BCOCA-C-056]|
| EC-0F0D | X'040F..0D' [BCOCA-C-057]|
| EC-0F0E | X'040F..0E' [BCOCA-C-058]|
| EC-0F0F | X'040F..0F' [BCOCA-C-059]|
| EC-0F10 | X'040F..10' [BCOCA-C-060]|
| EC-0F11 | X'040F..11' [BCOCA-C-061]|
| EC-0F12 | X'040F..12' [BCOCA-C-062]|
| EC-0F13 | X'040F..13' [BCOCA-C-063]|
| EC-0F14 | X'040F..14' [BCOCA-C-064]|
| EC-0F15 | X'040F..15' [BCOCA-C-065]|
| EC-0F16 | X'040F..16' [BCOCA-C-066]|
| EC-0F17 | X'040F..17' [BCOCA-C-067]|
| EC-0F18 | X'040F..18' [BCOCA-C-068]|
| EC-0F19 | X'040F..19' [BCOCA-C-069]|
| EC-0F1A | X'040F..1A' [BCOCA-C-070]|
| EC-0F1B | X'040F..1B' [BCOCA-C-071]|
| EC-0F1C | X'040F..1C' [BCOCA-C-072]|
| EC-0F1D | X'040F..1D' [BCOCA-C-073]|
| EC-0F1E | X'040F..1E' [BCOCA-C-074]|
| EC-0F20 | X'040F..20' [BCOCA-C-075]|
| EC-0F21 | X'040F..21' [BCOCA-C-076]|
| EC-0F22 | X'040F..22' [BCOCA-C-077]|
| EC-0F23 | X'040F..23' [BCOCA-C-078]|
| EC-0F24 | X'040F..24' [BCOCA-C-079]|
| EC-0F25 | X'040F..25' [BCOCA-C-080]|
| EC-0F30 | X'040F..30' [BCOCA-C-081]|
| EC-0F31 | X'040F..31' [BCOCA-C-082]|
| EC-0F32 | X'040F..32' [BCOCA-C-083]|
| EC-0F33 | X'040F..33' [BCOCA-C-084]|
| EC-0F34 | X'040F..34' [BCOCA-C-085]|
| EC-0F35 | X'040F..35' [BCOCA-C-086]|
| EC-0F36 | X'040F..36' [BCOCA-C-087]|
| EC-0F37 | X'040F..37' [BCOCA-C-088]|
| EC-0F38 | X'040F..38' [BCOCA-C-089]|
| EC-0F39 | X'040F..39' [BCOCA-C-090]|
| EC-0F3A | X'040F..3A' [BCOCA-C-091]|
| EC-0F3B | X'040F..3B' [BCOCA-C-092]|
| EC-1000 | X'0410..00' [BCOCA-C-093]|
| EC-1100 | X'0411..00' [BCOCA-C-094]|
| EC-1200 | X'0412..00' [BCOCA-C-095]|
| EC-1201 | X'0412..01' [BCOCA-C-096]|
| EC-1202 | X'0412..02' [BCOCA-C-097]|
| EC-1203 | X'0412..03' [BCOCA-C-098]|
| EC-1204 | X'0412..04' [BCOCA-C-099]|
| EC-1205 | X'0412..05' [BCOCA-C-100]|
| EC-2100 | X'0821..00' [BCOCA-C-101]|
IPDS Environment


Copyright © AFP Consortium 1991, 2025 183
