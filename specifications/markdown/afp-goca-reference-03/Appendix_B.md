Appendix B. Intelligent Printer Data Stream (IPDS) Environment
The Intelligent Printer Data Stream (IPDS) architecture is the strategic AFP data stream for controlling advanced function printer devices. It supports the all-points-addressable printing function that allows text and individual image, graphics, and barcode objects to be positioned and presented at any point on the printed page.
All IPDS printer commands are defined in self-defining field formats that are described in the Intelligent Printer
Data Stream Reference. The reader is referred to this document for a definitive description of the architecture.
Graphics in the IPDS Environment
The Wr ite Graphics Control command is sent to the printer to establish the control parameters and initial drawing conditions to be used in presenting the picture data. The picture segments themselves are sent to the printer as data in one or more Wr ite Graphics commands.
The IPDS architecture supports the GOCA subsets DR/2V0 and GRS3, as described in Chapter 9, “Compliance”.
IPDS Graphics Command Set
The IPDS Graphics Command Set consists of the following commands:
• Write Graphics Control (X'D684') [GOCA-B-001]
• Write Graphics (X'D685') [GOCA-B-002]
W rite Graphics Control Command
The Wr ite Graphics Control command is sent to the printer to indicate that the command sequence that follows is directed to a graphics object on the current page, overlay , or page segment that is being constructed by the device. The parameters of this command define the size, placement, and orientation of the graphics object area and establish the initial conditions for interpreting the graphics data.
Upon receiving this command the printer enters the appropriate graphics state and initializes control for processing graphics picture segments that are sent in subsequent Wr ite Graphics commands. The End command received in graphics state terminates the processing of graphics data.
The drawing processor can be invoked in any one of three IPDS printer states, as follows:
• Page state [GOCA-B-003]
• Overlay state [GOCA-B-004]
• Page Segment state [GOCA-B-005]
When the drawing processor is invoked in Overlay or Page Segment state, the picture data sent to the printer is saved as part of the Overlay or Page Segment definition for later inclusion on pages via the Load Copy
Control, Include Overlay , or Include Page Segment commands.
Positive acknowledgement of graphics commands in Overlay state or Page Segment state means that general syntax and validity checks have been made and that the command, or command sequence, has been accepted for processing. Additional exceptions that are detected when the data is included on the page are reported at that time, assuming that exception reporting is enabled. [GOCA-B-006]

---

Output Control Definitions
Graphics Object Areas
Pictures are presented in rectangular output areas called object areas. Object areas can be positioned at any addressable point on a page or in any Overlay or Page Segment definition and can be defined in any one of four orientations (0°, 90°, 180°, and 270°) relative to the axis of the reference system.
Object areas correspond to the Usable Area (UA) defined in “Usable Area (UA)”.
The size, position, and orientation of object areas are defined to the printer by parameters that are specified in the Write Graphics Control command.
GPS Window
The GPS window is a rectangular area within the GPS specified in GPS coordinates. This is the part of the picture that is mapped to the object area. The graphics data within this window is always trimmed by the printer , before the data is mapped to the object area.
The GPS window parameters are specified to the printer in the Wr ite Graphics Control command.
Mapping Control Options
The data within the GPS Window can be mapped to the object area as specified by the Mapping
Control Option parameter of the Wr ite Graphics Control command. These options are:
Center and T rim Map the center of the GPS Window to the center of the object area and present to scale. Excess picture data, if any , is trimmed at object area boundaries.
Scale to Fit Map the center of the window to the center of the object area and scale to fit.
The scaling is symmetric and the aspect ratio is preserved. All picture data within the window is always presented when this option is specified.
Scale to Fill Map the center of the window to the center of the object area and scale to fill.
The scaling may be asymmetric and the aspect ratio may not be preserved.
All picture data within the window is always presented when this option is specified.
Position and T rim Map the upper left-hand corner of the window to an off set point within the object area and present to scale. Excess picture data, if any , is trimmed at object area boundaries.
Mapping Defaults
If this parameter is omitted, Position and T rim is used. Excess picture data, if any , is trimmed at page boundaries and the off set position is defined to be the origin of the object area.
W rite Graphics Control Data
The Wr ite Graphics Control data is made up of three consecutive self-defining fields, as follows:
• Graphics Area Position (GAP) [GOCA-B-007]
• Graphics Output Control (GOC) [GOCA-B-008]
• Graphics Data Descriptor (GDD) [GOCA-B-009]
Graphics Area Position
This self-defining field defines the position and orientation of the Graphics object area relative to a reference coordinate system. It is a mandatory field in the Wr ite Graphics Control command.
Graphics Output Control
This self-defining field specifies the size of the graphics object area and the mapping option for the graphics object. It is an optional data field in the Write Graphics Control command. If this field is omitted, the size of the graphics object area is made equal to the size of the GPS window , as specified in the Graphics Data Descriptor , and the Position and T rim option applies, where the offs et origin position is defined to be the same as the object area origin.
It is an exception if there is an attempt to present data outside the boundary of the logical page. [GOCA-B-010]

---

Graphics Data Descriptor
This is a mandatory self-defining field in the Write Graphics Control command. It specifies the parameters that define the GPS Window in GPS and sets the drawing default conditions.
W rite Graphics Command
The Wr ite Graphics command transmits graphics data to the printer . The data that is carried in this command consists of picture segments that in turn contain the drawing orders that define the picture in GPS.
The segments that are sent to the printer are of two types:
• Chained [GOCA-B-011]
• Unchained [GOCA-B-012]
The type is indicated by the flag specified in the Begin Segment header .
The chained segments are the picture. The unchained segments are ignored, since calling of segments is not supported in AFP GOCA.
All segments sent to the printer are executed in immediate mode. That is, the drawing orders, except for unchained segments, are executed as they are received and are not retained or stored as named segments.
The receipt of the first “chained segment” is an implicit command to the printer to start the drawing process.
There are no restrictions on how much, or how little, data is sent to the printer in a single IPDS Wr ite Graphics command, except for the 32K length limit of the command. A Wr ite Graphics command, for example, can transmit partial segments, full segments, multiple segments, or any combination of these. The only requirement is that the data itself is ordered in the sequence that is expected for immediate processing and that the last WG command completes the last segment.
The Begin Segment command supported by IPDS printers is shown in “Begin Segment Command”.
Additional Related Commands
The following commands are used for query and resource management functions. Only an overview of these commands is presented in this document. They are described in detail in the Intelligent Printer Data Stream
Reference.
Sense Type and Model (STM)
Requests information from the printer that identifies the type and model of the device and the command sets supported. The information requested is returned in the Special Data Area of the
Acknowledge Reply to the STM command. The command sets and data levels supported are also returned as part of the acknowledgement data.
Execute Order Homestate—Obtain Printer Characteristics (XOH OPC)
Requests information from the printer that identifies various characteristics of the device. The characteristics include information about the printable area currently available, symbol-set support, image and font resolution, and other device characteristics.
Execute Order Anystate—Request Resource List (XOA RRL)
Requests the printer to return a specified list of available resources—that is, fonts, overlays, and page segments—in the Acknowledge Reply to this command. This information can be used by host programs to perform a variety of resource management functions.
Load Font Equivalence (LFE)
This command is sent to the printer to map Local Identifiers referenced in graphics to a specified font in the printer . [GOCA-B-013]

---

The correlation function provided by this command is independent of any specific font technology implemented by the printing device. That is, the device can resolve this mapping to stored font patterns downloaded from the host, or from permanently resident patterns.
The same font resource can be used for text, graphics, and bar code data.
Font Commands
The host can use commands defined in the IPDS Loaded Font command set and Device Control command set to download and manage fonts in the printer . The following commands are provided:
• Activate Resource [GOCA-B-014]
• Deactivate Font [GOCA-B-015]
• Load Code Page [GOCA-B-016]
• Load Code Page Control [GOCA-B-017]
• Load Font [GOCA-B-018]
• Load Font Character Set Control [GOCA-B-019]
• Load Font Control [GOCA-B-020]
• Load Font Index [GOCA-B-021]
• Load Symbol Set [GOCA-B-022]
IPDS Exceptions
In the IPDS environment, GOCA exception conditions are mapped to IPDS exceptions and reported. The mapping is shown below in Table 15.
T o map a GOCA exception condition to an IPDS exception, the general rule is simply to add a X'03' on the front of the four digits of the GOCA exception condition: GOCA exception condition EC-9301 becomes IPDS exception X'0393..01'. However , there are exception conditions that do not follow the general rule—these are noted in Table 15.
Table 15. Mapping from GOCA Exception Condition to IPDS Exception
GOCA exception condition IPDS exception
IPC-0002 X'0300..02'
IPC-0003 X'0300..03'
IPC-0021 X'0300..21'
CPC-0001 X'0300..01'
CPC-7001 X'0370..01'
CPC-7082 X'0370..82'
CPC-70C1 X'0370..C1'
CPC-70C5 X'0370..C5'
EC-0001 X'0300..01'
EC-0002 (retired) X'0300..02'
EC-0003 X'0300..03'
EC-0004 X'0300..04'
EC-0008 X'0300..08'
EC-000A None; exception condition not reported in IPDS environment (see “Note”)
EC-000C X'0300..0C'
EC-000D X'0300..0D'

---

Table 15 Mapping from GOCA Exception Condition to IPDS Exception (cont'd.)
GOCA exception condition IPDS exception
EC-000E X'0300..0E'
EC-0400 X'0304..00'
EC-0E02 X'020E..02' (see “Note”)
EC-0E03 X'020E..03' (see “Note”)
EC-0E04 X'020E..04' (see “Note”)
EC-0E05 X'020E..05' (see “Note”)
EC-3400 X'0334..00'
EC-3E00 X'033E..00'
EC-5E00 X'035E..00'
EC-6000 X'0360..00'
EC-6800 X'0368..00'
EC-6801 X'0368..01'
EC-6802 X'0368..02'
EC-6803 X'0368..03'
EC-6804 X'0368..04'
EC-6805 X'0368..05'
EC-6806 X'0368..06'
EC-9200 X'0392..00'
EC-9201 X'0392..01'
EC-9300 X'0393..00'
EC-9301 X'0393..01'
EC-C000 X'03C0..00'
EC-C001 X'03C0..01'
EC-C200 X'03C2..00'
EC-C201 X'03C2..01'
EC-C202 (obsolete) X'03C2..02'
EC-C300 X'03C3..00'
EC-C301 X'03C3..01'
EC-C302 X'03C3..02'
EC-C303 X'03C3..03'
EC-C601 X'03C6..01'
EC-D100 X'03D1..00'
EC-D101 X'03D1..01'
EC-D102 X'03D1..02'
EC-D103 X'03D1..03'
EC-D104 X'03D1..04'
EC-DC00 X'03DC..00'

---

Table 15 Mapping from GOCA Exception Condition to IPDS Exception (cont'd.)
GOCA exception condition IPDS exception
EC-DC01 X'03DC..01'
EC-DC02 X'03DC..02'
EC-DC03 X'03DC..03'
EC-DC04 X'03DC..04'
EC-DC05 X'03DC..05'
EC-DC06 X'03DC..06'
EC-DC07 X'03DC..07'
EC-DD00 X'03DD..00'
EC-DD01 X'03DD..01'
EC-DD02 X'03DD..02'
EC-DD03 X'03DD..03'
EC-DD04 X'03DD..04'
EC-DD05 X'03DD..05'
EC-DD06 X'03DD..06'
EC-DD07 X'03DD..07'
EC-DE00 X'03DE..00'
EC-DE01 X'03DE..01'
EC-DE02 X'03DE..02'
EC-DE03 X'03DE..03'
EC-DE04 X'03DE..04'
EC-DE05 X'03DE..05'
EC-DE06 X'03DE..06'
EC-DE07 X'03DE..07'
EC-DF00 X'03DF ..00'
EC-DF01 X'03DF ..01'
EC-DF02 X'03DF ..02'
EC-E100 X'03E1..00'
EC-E300 X'03E3..00'
EC-E302 X'03E3..02'
EC-E303 X'03E3..03'
Note: This exception condition does not follow the general rule for mapping described above.
If a GOCA exception condition has a Standard Action shown in this Reference, that action is ignored in the
IPDS environment; instead, the IPDS Alternate Exception Action or Page Continuation Action is performed, when appropriate. [GOCA-B-023]

---

Copyright © AFP Consortium 1997, 2017 195
