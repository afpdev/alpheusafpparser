# Chapter 2. Introduction to IPDS
The Intelligent Printer Data Stream (IPDS) is the host-to-printer data stream for Advanced Function
Presentation subsystems. It provides an attachment-independent interface for controlling and managing all-
points-addressable (APA) printers that allows the presentation of pages containing an architecturally unlimited
mixture of different data types: text, image, graphics, bar code, and object container.
In addition, the IPDS architecture incorporates the following features:
• Different applications, which can be independent of one another, create source data (such as graphics, [IPDS-2-001]
image, bar code, and text). IPDS architecture allows the output of these independent applications to be
merged at print time so that an integrated mixed-data page results.
IPDS architecture makes this possible by carrying text and independently defined blocks of data, such as
image, graphics, and bar code. Similarly defined objects are carried by the Mixed Object Document Content
Architecture (MO:DCA), thus making it possible to use the same objects in both environments.
• IPDS data streams are independent of the carrying communications protocol. This allows the transmission of [IPDS-2-002]
the same data stream to printers attached to channels, controllers, local area networks, and any other type of
networking link that supports the transparent transmission of data.
• IPDS products transfer all data and controls through self-identifying structured fields, called IPDS [IPDS-2-003]
commands, which describe the presentation of print data and provide for the following:
– The dynamic management of resident fonts and of downloaded resources, such as overlays, page
segments, and loaded fonts
– The control of device functions such as duplexing, media-bin selection, and output finishing
– The comprehensive handling of exception functions, so users can control the level of exception handling.
• IPDS architecture provides an extensive acknowledgment protocol at the data-stream level. This [IPDS-2-004]
acknowledgment protocol helps synchronize host and printer processes, exchange query-reply information,
and return detailed exception information.
• IPDS architecture provides support for media finishing using printer-attached devices or using pre- [IPDS-2-005]
processing and post-processing devices. In addition to traditional printer-controlled finishing, constructs are
also provided to enable IPDS data streams to be used within Universal Printer Pre- and Post-Processing
Interface (UP3I) environments.
• IPDS architecture provides support for color management whose goal is to provide consistent and accurate [IPDS-2-006]
color output across different devices and to provide flexible controls that enable the tuning of output to exact
specifications.


IPDS Architecture as a Component of Printing Subsystems
Each of the printer environments shown in the following four figures contains a printing subsystem.
Presentation Services is the component where IPDS commands are generated in each of the diagrammed
printing subsystems. These subsystems have the following elements in common:
Source Applications
Existing or new applications generate source data. Some applications generate text data that
previously would have been directed to line printers (non-IPDS printers); for example, those that
generate 1403 data in the mainframe environment, SNA Character String (SCS) data in the mid-range
environment, and ASCII data in the PC LAN environment.
Other applications generate APA text or other data types such as image, graphics, and bar code.
Presentation Services
A software component known generically as presentation services accepts source data and
transforms it to IPDS commands without changing the existing source applications. In addition,
presentation services permits the output of line-printer applications to be enhanced by other IPDS
functions. These functions include duplexing, overlays (electronic forms), and multiple high-quality
fonts.
Note: Host presentation services need not utilize all IPDS commands and parameters. The IPDS
architecture allows functional trade-offs whereby the host can use a variety of alternate IPDS
command sequences to present the same pages.
IPDS Printers
IPDS printers are printers that support the common IPDS printer interface. These printers can attach to
several different system or subsystem environments via one or more communication protocols. For
example, there are presentation-services programs that support a wide range of IPDS printers
attached using 370-channel protocols, SNA protocols, AS/400-Twinax protocols, 3270-data-stream
protocols, and local-area-network protocols.
The same IPDS data structures can be used in a broad range of printer environments including:
• The spooled system environment [IPDS-2-007]
• The mainframe interactive environment [IPDS-2-008]
• The intelligent workstation or departmental system environment [IPDS-2-009]
• The local area network environment [IPDS-2-010]


The Spooled System Environment
A host application creates and sends print data either to the spool or directly to presentation services.
Presentation services takes the print data from the spool or application and generates the IPDS commands
that drive the printer.
Figure 4. IPDS Products in the Spooled System Environment
IPDS Printer
Spool Files
Host System
ApplicationPresentation
Services
Controller IPDS Printer
Display Display
Local Area Network
Workstation or
Departmental System
Presentation
Services Application
IPDS Printer Display
PC
Workstation
IPDS Printer
In the spooled system environment, presentation services software provides presentation-services functions
for mainframe operating systems. Other Advanced Function Presentation (AFP) software products create
complex data and printing resources, such as typographic fonts, that are stored on the spool or in system
libraries. The important data streams in this environment include the presentation form of MO:DCA as well as
older data streams important for printing. As part of its presentation-services function, presentation services
software converts MO:DCA data, and older data streams such as 1403 line data into the IPDS commands that
are used to drive attached printers.
Note: In the spooled system environment, applications should not generate IPDS commands directly since
most presentation-services programs accept AFP data streams or data created for line printers and
convert it into IPDS commands for the application. [IPDS-2-011]


The Mainframe Interactive Environment
A controller interfaces with a display cluster, a printer, and a host. A host application creates print data and,
using the host presentation services, generates IPDS commands that reach the printer through the controller.
Figure 5. IPDS Products in the Mainframe Interactive Environment
Spool Files
Host System
Presentation
Services Application
Controller IPDS Printer
Display Display
Local Area Network
Workstation or
Departmental System
Presentation
Services Application
IPDS Printer Display IPDS Printer
PC
Workstation
IPDS Printer
In the mainframe interactive environment, IBM's Graphical Data Display Manager (GDDM) Release 2.1 or later
provides presentation-services functions for 327x-attached IPDS printers. Because of the many different
application programming interfaces of GDDM, applications can create complex pages with a mixture of text,
graphics, and image, that GDDM sends to the printer in the form of IPDS commands. [IPDS-2-012]


The Intelligent Workstation or Departmental System Environment
Applications in an independent workstation or in a small departmental system can create print data to be sent
to the workstation's presentation services. The workstations's presentation services generate IPDS commands
that drive the printer.
Figure 6. IPDS Products in the Workstation or Departmental System Environment
Spool Files
Host System
Presentation
Services Application
Controller IPDS Printer
Display Display
Local Area Network
Workstation or
Departmental System
Presentation
Services Application
IPDS Printer Display IPDS Printer
PC
Workstation
IPDS Printer
In the workstation environment, presentation-services functions are typically provided by the operating system.
Functions provided include converting SNA Character String (SCS) data into IPDS data, and creating or
transporting IPDS commands with a mixture of text, image, graphics, and bar code data. [IPDS-2-013]


The Local Area Network Environment
A personal computer workstation connected to a local area network (LAN) sends print data to a print server on
the LAN. Presentation services in the print server controls the spooling of data files and generates the IPDS
commands that drive the printer.
Figure 7. IPDS Products in the Local Area Network (LAN) Environment
Spool Files
Host System
Presentation
Services Application
Controller IPDS Printer
Display Display
Local Area Network
Workstation or
Departmental System
Presentation
Services Application
IPDS Printer Display IPDS Printer
Print
Server
IPDS Printer
Presentation
Services
In the LAN environment, presentation-services functions are provided by AFP software products. Among the
functions provided is the conversion of other data, such as MO:DCA, ASCII, ditroff, TIFF , GIF , JPEG, EPS,
PDF , and PostScript, into IPDS data. [IPDS-2-014]


Communication with an IPDS Device
Communication between an IPDS device and a presentation services program involves two logical
concepts: a carrying-protocol session and an IPDS dialog. The carrying-protocol session uses one of many
possible physical attachments and an appropriate communications protocol to transport IPDS commands and
Acknowledge Replies. The IPDS dialog consists of the IPDS commands and IPDS Acknowledge Replies that
are transparently carried by the carrying protocol.
The carrying-protocol session can be established using any physical attachment and protocol that allows
bidirectional communications to flow. This generality allows the transmission of the IPDS data stream to
printers attached to channels, controllers, local area networks, wide area networks, and any other type of
communication link that supports the transparent transmission of data. For example, the following attachments
(and protocols) have been used to carry an IPDS dialog:
Coax (SNA/LU1.0)
Coax (3270/DSC)
ESCON channel (CCWs)
Ethernet (TCP/IP)
FDDI (TCP/IP)
FICON
® channel (CCWs)
RS232 (SNA/LU6.2)
Token Ring (SNA/LU6.2)
Token Ring (TCP/IP)
Twinax (Arctic)
Wireless Ethernet (TCP/IP)
370 and 390 parallel channel (CCWs) [IPDS-2-015]
The IPDS dialog begins with the first IPDS command that an IPDS device receives and ends when either an
IPDS command explicitly ends the dialog or when the carrying-protocol session ends. Some IPDS printers
support an optional command, called Manage IPDS Dialog, that can explicitly start or stop an IPDS dialog. This
command allows the IPDS dialog to share a printer with other data streams, and allows a printer to
conveniently switch between multiple communication sessions.
There can be multiple independent sessions, each with an IPDS dialog. When a printer receives a Manage
IPDS Dialog command to end a dialog, the printer can then safely switch to a different dialog or session. [IPDS-2-016]


IPDS Functional Divisions
The IPDS architecture is divided into several functional areas called command sets, each representing a major
printer capability. A command set consists of IPDS commands, including semantics, syntax, and the
architecturally-valid values for each field in the command. The architecture also contains a registry of
exception-reporting codes for error conditions in each of its command sets and for printer-related failure, fault,
or host-notification conditions.
Each command set is further divided into at least one subset of defined function and a subset of optional
function. Some command sets contain more than one subset of defined function and some command sets are
defined to carry object data.
The IPDS command-set design supports several printer technologies. Product developers can match
command-set implementations to the specific needs of their product. Figure 8 illustrates the IPDS
functional divisions. For a complete description of IPDS functional divisions, refer to Chapter 17, “Compliance”.
The IPDS architecture contains the following command sets:
Device Control This command set contains the IPDS commands that initialize the environment for a logical
page, communicate device controls, manage resources, and handle the acknowledgment
protocol. Support of the defined subset (DC1) of this command set is mandatory.
Text This command set contains the IPDS commands for presenting text information in a page, a
page segment, or an overlay.
IM Image This command set contains the IPDS commands for presenting images in a page, a page
segment, or an overlay.
IO Image This command set contains the IPDS commands for presenting images in a page, a page
segment, or an overlay. The IO-Image command set provides functions, such as image
compression and grayscale, that the IM-Image command set does not provide.
Graphics This command set contains the IPDS commands for presenting graphics in a page, a page
segment, or an overlay.
Bar Code This command set contains the IPDS commands for presenting machine-readable bar code
information in a page, a page segment, or an overlay.
Object
Container
This command set contains the IPDS commands to store and present IPDS constructs
containing data whose definitions are not controlled by an AFPC presentation architecture.
These stored constructs are called object containers.
Metadata This command set contains the IPDS commands to associate metadata with objects in the
IPDS data stream.
Page Segment This command set contains the IPDS commands to store and present IPDS constructs
containing text, graphics, image, and bar code information. These stored constructs, that can
be merged with a logical page to assume the current environment, are called page segments.
Overlay This command set contains the IPDS commands to store and present IPDS constructs
containing text, graphics, image, and bar code information. These stored constructs are called
overlays.
Loaded Font This command set contains the IPDS commands to load font information. [IPDS-2-017]


For the following IPDS command sets, a data tower defines the data carried. A data tower is divided into levels.
A higher level of a data tower consists of all lower levels plus some set of additional function. Some data tower
levels are defined and controlled by other architectures and are simply registered by the IPDS architecture.
The data towers are:
Text This data tower contains Presentation Text Object Content Architecture (PTOCA) control
sequences, contained in the data field of the Write Text command, and used to present text
information in a page, a page segment, or an overlay. The text data tower contains four
presentation text (PT) levels, PT1, PT2, PT3, and PT4, defined by the PTOCA architecture.
IM Image This data tower contains image data, contained in the data field of the Write Image command.
Image data can be presented in a page, a page segment, or an overlay. The IM-Image data
tower contains one level, IMD1, defined by the IPDS architecture.
IO Image This data tower contains Image Object Content Architecture (IOCA) self-defining fields,
contained in the data field of the Write Image 2 command, and used to present image data in a
page, a page segment, or an overlay. The IO-Image data tower contains several levels, FS10,
FS11, FS14,
FS40, FS42, FS45, and FS48 , defined by the IOCA Architecture.
Graphics This data tower contains Graphics Object Content Architecture (GOCA) drawing orders,
contained in the data field of the Write Graphics command, and used to present graphics in a
page, a page segment, or an overlay. The graphics data tower contains two levels, DR/2V0
and GRS3, defined by the GOCA Architecture.
Bar Code This data tower contains Bar Code Object Content Architecture (BCOCA) data controls,
contained in the data field of the Write Bar Code command, and used to present machine-
readable bar code information in a page, a page segment, or an overlay. The bar code data
tower contains two levels, BCD1 and BCD2, defined by the BCOCA architecture.
Metadata This data tower contains Metadata Object Content Architecture (MOCA) objects, contained in
the data field of the Write Metadata command. Metadata is associated with objects based on
its location in the datastream. The metadata data tower contains one level, MS1, defined by
the MOCA architecture. [IPDS-2-018]


Figure 8. IPDS Functional Divisions
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
function for text, IO-Image, graphics, and bar code data. [IPDS-2-019]


