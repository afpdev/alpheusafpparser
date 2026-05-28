
Advanced Function Presentation Consortium
Data Stream and Object Architectures
Intelligent Printer Data Stream
Reference
AFPC-0001-12


Note:
Before using this information, read the information in “Notices”.
AFPC-0001-12
Thirteenth Edition (June 2023)
This edition applies to the Intelligent Printer Data Stream™ (IPDS™) architecture. It is the fifth edition produced by the AFP
Consortium™(AFPC™) and replaces and makes obsolete the previous edition (AFPC-0001-11). This edition remains
current until a new edition is published. This publication also applies to any subsequent releases of Advanced Function
Presentation™ (AFP™) products that use the IPDS architecture until otherwise indicated in a new edition.
Technicalchanges are indicated in green, with a green vertical bar to the left of the change. Editorial changes that have no
technical significance are not noted. For a detailed list of changes, see “Changes in This Edition”.
Internet
Visit our home page: www.afpcinc.org [IPDS-FM-001]


## Preface
## This book describes the functions and services associated with the Intelligent Printer Data Stream (IPDS)
architecture.
This book is a reference, not a tutorial. It complements individual product publications, but does not describe
product implementations of the architecture. [IPDS-FM-002]
## Who Should Read This Book
This book is for system programmers and other developers who need such information to develop or adapt a
product or program to interoperate with other presentation products in an Advanced Function Presentation
(AFP) environment.
The Intelligent Printer Data Stream Reference describes the function and composition of elements sent to
printers that support the Intelligent Printer Data Stream (IPDS) architecture. However, this book does not
describe any specific hardware or licensed programs that implement the IPDS architecture.
This book documents the architecture that encompasses IPDS products available at the time of publication.
Use this book in conjunction with your printer documentation to:
• Check for the correct input to an IPDS-supported printer [IPDS-FM-003]
• Learn about the input to IPDS printers [IPDS-FM-004]
• Develop print server programs to control IPDS printers [IPDS-FM-005]
• Support the design of programs that provide input for the data stream; for example, a text formatting program [IPDS-FM-006]
You should note that the IPDS architecture provides a host-to-printer interface intended to be used by
programs requiring direct control over printers. [IPDS-FM-007]
## AFP Consortium (AFPC)
The Advanced Function Presentation (AFP) architectures began as the strategic, general purpose document
and information presentation architecture for the IBM
® Corporation. The first specifications and products go
back to 1984. Although all of the components of the architecture have grown over the years, the major
concepts of object-driven structures, print integrity, resource management, and support for high print speeds
were built in from the start.
In the early twenty-first century, IBM saw the need to enable applications to create color output that is
independent from the device used for printing and to preserve color consistency, quality, and fidelity of the
printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™
(AFPCC™). The goal was to extend the object architectures with support for full-color devices including
support for comprehensive color management. The idea of doing this via a consortium consisting of the
primary AFP architecture users was to build synergism with partners from across the relevant industries, such
as hardware manufacturers that produce printers as well as software vendors of composition, work flow,
viewer, and transform tools. Quickly more than 30 members came together in regular meetings and work group
sessions to create the AFP Color Management Object Content Architecture™ (CMOCA™). A major milestone
was reached by the AFP Color Consortium with the initial official release of the CMOCA specification in May
2006.
Since the cooperation between the members of the AFP Color Consortium turned out to be very effective and
valuable, it was decided to broaden the scope of the consortium efforts and IBM soon announced its plans to
open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding [IPDS-FM-008]


iv IPDS Reference
member of the consortium was transferred to the InfoPrint ® Solutions Company, an IBM/Ricoh® joint venture;
currently Ricoh holds the founding member position. In February 2009, the consortium was incorporated under
a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open
standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures
was transferred at that time to the AFP Consortium. [IPDS-FM-009]
## Publication History
The IPDS Reference was first published by IBM in 1987 and has had several enhancements and updates
since that time. The first eight editions were published by IBM Corporation and later editions were published by
the AFP Consortium.
First Edition published by IBM Corporation
S544-3417-00 dated August 1987
Second Edition published by IBM Corporation
S544-3417-01 dated March 1989
This edition provides enhanced detail and clarification, including:
• A more complete description of the X [IPDS-FM-010]
m,Ym coordinate system and units of measure
• More detail on Acknowledge Replies [IPDS-FM-011]
• More detail on exception handling and exception IDs [IPDS-FM-012]
The following major new functions were added:
• Load Resource Equivalence command (later renamed to Activate Resource command) [IPDS-FM-013]
• New action codes and exception IDs. [IPDS-FM-014]
• Rules for copy group processing [IPDS-FM-015]
• STM property pairs and OPC self-defining field data [IPDS-FM-016]
• XOA Control Edge Marks command [IPDS-FM-017]
• XOH Set Media Origin command [IPDS-FM-018]
Third Edition published by IBM Corporation
S544-3417-02 dated February 1990
This edition provides an extensive restructuring to improve readability and ease of reference; the
following major new functions were added:
• The Define User Area command provides additional control over the area of a page that is [IPDS-FM-019]
accessible to an application program. This command can be used by a print-driver program to print
non-subvertible security labels.
• The XOH Define Group Boundary command provides a means of grouping pages. [IPDS-FM-020]
• The XOH Specify Group Operation command provides a means of specifying an operation to be [IPDS-FM-021]
performed on a group of pages. The operation can be performed by a printer or by a pre-processing
or post-processing device attached to the printer.
This edition describes the relationship between the IPDS architecture and the new SAA/CCS
architectures that IBM announced in May 1989. Chapter One describes the function of IPDS
architecture within Systems Application Architecture
®. Chapter Fourteen provides additional detail
about IPDS functional divisions and supplies compliance and migration information.
Description of some of the data that IPDS carries has been removed from this edition and a much
more complete description of this data is provided in the following new books:
Graphics Object Content Architecture Reference, SC31-6804
Image Object Content Architecture Reference, SC31-6805
Presentation Text Object Content Architecture Reference, SC31-6803 [IPDS-FM-022]


Fourth Edition published by IBM Corporation
S544-3417-03 dated August 1991
This edition was further restructured to improve readability and ease of reference; the following major
new functions were added:
• A data type for each field in the syntax tables [IPDS-FM-023]
• Envelope media [IPDS-FM-024]
• Magnetic Ink Character Recognition (MICR) printing [IPDS-FM-025]
• Relative-metric fonts [IPDS-FM-026]
• Several new exception IDs [IPDS-FM-027]
• Two new image compression algorithms: [IPDS-FM-028]
1. ABIC (Bilevel Q-Coder) compression algorithm [IPDS-FM-029]
2. G3 Facsimile Two-Dimensional Coding 'Scheme (G3 MR) compression algorithm [IPDS-FM-030]
Description of some of the bar code data that IPDS carries has been removed from this edition and a
much more complete description of this data is provided in the following new book:
Bar Code Object Content Architecture™ Reference, S544-3766
Fifth Edition published by IBM Corporation
S544-3417-04 dated August 1993
This edition provides enhanced detail and the following major new functions:
• Additional information added to all exception IDs [IPDS-FM-031]
• An overview section describing IPDS resources and resource processing [IPDS-FM-032]
• Enhanced Chapter 1 to describe how the IPDS Architecture fits into IBM's presentation [IPDS-FM-033]
environments
• Enhanced description of IPDS presentation spaces and their coordinate systems [IPDS-FM-034]
• More complete glossary [IPDS-FM-035]
• Product identification in the XOH-OPC reply [IPDS-FM-036]
• Several new exception IDs [IPDS-FM-037]
• XOH Select Medium Modifications command [IPDS-FM-038]
• XOH Stack Received Pages command [IPDS-FM-039]
The following commands have been renamed:
• “Delete Font” has been renamed to “Deactivate Font” [IPDS-FM-040]
• “Delete Overlay” has been renamed to “Deactivate Overlay” [IPDS-FM-041]
• “Delete Page Segment” has been renamed to “Deactivate Page Segment” [IPDS-FM-042]
• “Load Resource Equivalence” has been renamed to “Activate Resource” [IPDS-FM-043]
Sixth Edition published by IBM Corporation
S544-3417-05 dated March 1996
This edition provides enhanced detail and the following major new functions:
• Activate Printer Alarm command [IPDS-FM-044]
• General enhancements include: [IPDS-FM-045]
– Additional information added to exception IDs
– Deactivate Font command moved to Device-Control command set
– Empty LFE commands now allowed
– Font resource clarifications
– New intervention required sense data
– Presentation space mixing clarifications
– Several new exception IDs
– The term “data block” renamed to “object area” [IPDS-FM-046]


vi IPDS Reference
• Input media identification [IPDS-FM-047]
• IPDS Dialog management [IPDS-FM-048]
• Microfilm media [IPDS-FM-049]
• N-up page placement and orientation [IPDS-FM-050]
• Outline fonts including: [IPDS-FM-051]
– Adobe® Type 1 font technology
– Query extensions
– Separate code page and font character set resources
• Replicate-and-trim mapping for IO Image [IPDS-FM-052]
• Resource query enhancements [IPDS-FM-053]
• Support for multiple media sources and multiple media destinations [IPDS-FM-054]
Seventh Edition published by IBM Corporation
S544-3417-06 dated November 2002
This edition provides enhanced detail and the following major new functions:
• Activation failed NACK [IPDS-FM-055]
• All architected units of measure [IPDS-FM-056]
• Bilevel IO-Image color [IPDS-FM-057]
• CID-keyed font technology [IPDS-FM-058]
• Color fidelity [IPDS-FM-059]
• Cut-sheet emulation [IPDS-FM-060]
• Data object resources [IPDS-FM-061]
• Data validation and ribbon fault errors [IPDS-FM-062]
• Default character in outline code pages [IPDS-FM-063]
• Double-byte outline fonts [IPDS-FM-064]
• Double-byte raster to outline font migration support [IPDS-FM-065]
• EPS and EPS with transparency [IPDS-FM-066]
• Extended group ID format for OS/400 [IPDS-FM-067]
®
• Extended overlay and page segment support [IPDS-FM-068]
• Extended page counters control [IPDS-FM-069]
• External printer name [IPDS-FM-070]
• Finishing (cutting, folding, inserting, punching, stapling, stitching) [IPDS-FM-071]
• Finishing fidelity [IPDS-FM-072]
• Font character set extensions [IPDS-FM-073]
• Full-process text color (PTOCA PT3) [IPDS-FM-074]
• G3 MH image compression algorithm [IPDS-FM-075]
• GOCA box draw, image resolution, partial arcs, and process color [IPDS-FM-076]
• IOCA Image resources and IOCA tile resources [IPDS-FM-077]
• IOCA full-process color support (FS11, FS40, FS42, FS45) [IPDS-FM-078]
• Logical page and object area coloring and shading [IPDS-FM-079]
• Media identification by OID [IPDS-FM-080]
• Multiple raster-font resolutions [IPDS-FM-081]
• Named groups [IPDS-FM-082]
• Object container architecture [IPDS-FM-083]
• Object container OID for color mapping table [IPDS-FM-084]
• Operator-directed recovery [IPDS-FM-085]
• Page overlay rotation [IPDS-FM-086]
• PDF pages, PDF pages with transparency, and PDF resources [IPDS-FM-087]
• Physical media selection extensions [IPDS-FM-088]
• Postal bar codes (Australia Post, Dutch KIX, Japan Postal, and Royal Mail) [IPDS-FM-089]
• Presentation fidelity control [IPDS-FM-090]


• Printer-detected forms mismatch [IPDS-FM-091]
• Printer set-up verification [IPDS-FM-092]
• Resident color profiles [IPDS-FM-093]
• Resource version support [IPDS-FM-094]
• Saved pages [IPDS-FM-095]
• Scale-to-fill mapping option [IPDS-FM-096]
• Set Media Size enhancements [IPDS-FM-097]
• Toner saver [IPDS-FM-098]
• Two-dimensional (2D) bar codes (Data Matrix, MaxiCode, and PDF417) [IPDS-FM-099]
• UP 3ITM finishing [IPDS-FM-100]
• XOH Separate Continuous Forms command [IPDS-FM-101]
Eighth Edition published by IBM Corporation
S544-3417-07 dated May 2006
This edition provides enhanced detail and the following major new functions:
• Additional data objects: GIF , JFIF , JPEG2000, PCL®, and TIFF [IPDS-FM-102]
• Additional group information [IPDS-FM-103]
• Code 93 bar code [IPDS-FM-104]
• Color management [IPDS-FM-105]
• GCGID-to-Unicode enhancements [IPDS-FM-106]
• Identification of TrueType and OpenType font errors [IPDS-FM-107]
• Intelligent Mail [IPDS-FM-108]
® Barcode
• JBIG2 image compression algorithm [IPDS-FM-109]
• MICR enhancements [IPDS-FM-110]
• PLANET bar code [IPDS-FM-111]
• QR Code 2D bar code [IPDS-FM-112]
• Rasterize Presentation Object command [IPDS-FM-113]
• Text fidelity control [IPDS-FM-114]
• TrueType/OpenType font support (data-object fonts) [IPDS-FM-115]
• UCC/EAN 128 bar code [IPDS-FM-116]
• UP
3I Print Data
Ninth Edition published by AFP Consortium
AFPC-0001-01 dated June 2010
This edition provides enhanced detail and the following major new functions:
• Bind and trim finishing [IPDS-FM-117]
• Clarifications based on multiple-company experience [IPDS-FM-118]
• Cubic Bézier Curves GOCA drawing order [IPDS-FM-119]
• Data Matrix encodation [IPDS-FM-120]
• Desired bar code symbol width [IPDS-FM-121]
• Exception ID additions and updates [IPDS-FM-122]
• Exception IDs registered with action code X'1A' [IPDS-FM-123]
• Extension of IDO color override for IOCA [IPDS-FM-124]
• Image Resolution (X'9A') triplet [IPDS-FM-125]
• IOCA Set Extended Bilevel Image Color exception ID [IPDS-FM-126]
• Large copy set values [IPDS-FM-127]
• Multi-image TIFF containers [IPDS-FM-128]
• Multi-page PDF containers [IPDS-FM-129]
• Number of pages in a group [IPDS-FM-130]
• Partial arcs with clockwise drawing direction [IPDS-FM-131]


viii IPDS Reference
• Partial support for HT and TTC CMRs [IPDS-FM-132]
• Passthrough for audit color-conversion CMRs [IPDS-FM-133]
• Rasterize Presentation Object extensions for color management [IPDS-FM-134]
• Retired items identified [IPDS-FM-135]
• Royal Mail RED Tag bar code [IPDS-FM-136]
• Scale-to-Fill mapping option for graphics objects [IPDS-FM-137]
• Set default support in GDD for normal line width [IPDS-FM-138]
• Set default support in GDD for process color [IPDS-FM-139]
• Set Line End GOCA drawing order [IPDS-FM-140]
• Set Line Join GOCA drawing order [IPDS-FM-141]
• Small bar code symbol support [IPDS-FM-142]
• Tag Image File Format (TIFF) without transparency [IPDS-FM-143]
• Unicode values in IPDS code pages [IPDS-FM-144]
• UP 3I Enhancements [IPDS-FM-145]
Tenth Edition published by AFP Consortium
AFPC-0001-09 dated August 2011
This edition provides enhanced detail and the following major new functions:
• AFPC TIFF Subset [IPDS-FM-146]
• BCOCA™ subset BCD2 [IPDS-FM-147]
• Clarifications for: [IPDS-FM-148]
– Color simulation when a page is saved
– Description of sense-byte format 0 byte 18
– Device resolution
– Duplicate code point exception in an LCP command
– EHC flags within an RPO command
– Exception ID descriptions
– Fidelity-control triplet descriptions and the Exception Handling Control flowchart
– FQN (X'02') triplet in the AR command triplets-use table
– IOCA self-defining fields table
– PCA for exception IDs X'0200..01', X'021C..01', and X'021E..01'
– Unsupported group ID formats in a Group Information (X'6E') triplet
• CMOCA error codes X'12' and X'13' [IPDS-FM-149]
• GOCA subset GRS3 [IPDS-FM-150]
• ICC DeviceLink CMRs [IPDS-FM-151]
• Intelligent Mail Container Barcode [IPDS-FM-152]
• IPDS support for MO:DCA™ Interchange Set 3 (IS/3) [IPDS-FM-153]
• Overview section to describe how color is specified within IPDS commands [IPDS-FM-154]
• PTOCA subset PT4 [IPDS-FM-155]
• Retired Exception IDs more fully described [IPDS-FM-156]
• Specific error condition identified in NACKs for multi-bulleted exception IDs [IPDS-FM-157]
• Text glyph runs in PTOCA data [IPDS-FM-158]
– Allows better user control over text layout
– Supports Unicode complex text
• User-selected presentation-space size for PDF objects [IPDS-FM-159]


Eleventh Edition published by AFP Consortium
AFPC-0001-10 dated June 2014
This edition provides enhanced detail and the following major new functions:
• Acknowledge Reply extended to allow additional debug information to be returned after an error [IPDS-FM-160]
occurs
• Bar Code Type/Modifier Self-Defining Field for the OPC reply; also deprecates the Bar Code [IPDS-FM-161]
Common Set
• Bearer Bars for Interleaved 2-of-5 bar codes [IPDS-FM-162]
• Color for Bilevel and Grayscale Image (allows color to be specified for all bilevel image objects [IPDS-FM-163]
supported within IPDS pages and overlays; also allows grayscale images to be colorized)
• Command and triplet descriptions updated to ensure mention of each appropriate exception ID in [IPDS-FM-164]
context
• Deprecated the Royal Mail RED TAG bar code type [IPDS-FM-165]
• Deprecated the USPS POSTNET and PLANET bar code types [IPDS-FM-166]
• Finishing operations for folding (center-fold out, c-fold in, c-fold out, accordion-fold in, accordion-fold [IPDS-FM-167]
out, single gate-fold in, single gate-fold out, double parallel-fold in, double parallel-fold out, double
gate-fold in, and double gate-fold out)
• Glossary definition for the term “deprecated” [IPDS-FM-168]
• Glossary definitions for several terms (particularly color terms) [IPDS-FM-169]
• GOCA Custom Line Types [IPDS-FM-170]
• GOCA Marker Size support [IPDS-FM-171]
• GOCA Nonzero Winding Mode [IPDS-FM-172]
• GS1 DataBar bar codes [IPDS-FM-173]
• Human-readable object names for captured objects [IPDS-FM-174]
• Index entries provided for each exception ID definition indicating where the exception ID is defined [IPDS-FM-175]
and used
• Internal rendering intent supported in presentation data objects; includes more information in the [IPDS-FM-176]
CMRs-Used trace entry
• Keep Group Together as a Recovery Unit (new SGO Group Operation that allows repositioning and [IPDS-FM-177]
error recovery on a group boundary)
• Media Feed Direction returned in the OPC reply [IPDS-FM-178]
• Metadata Object Content Architecture (MOCA) added; metadata can be carried in MO:DCA print [IPDS-FM-179]
files and documents, but is currently not supported in IPDS data streams
• New data stream structures: [IPDS-FM-180]
– 2 new action codes (X'1B' and X'2B')
– 3 new commands (RRR, RRRL, and WTC)
– 18 new exception IDs (X'4040..00' (with action codes X'1B' and X'2B'), X'0408..05', X'0412..04',
X'0201..03', X'020A..06', X'020D..30', X'020D..31', X'020D..32', X'0238..04', X'0238..10',
X'0238..11', X'0255..0B', X'0256..24', X'0293..00', X'0293..03', X'0293..04', X'0140..00' (with action
codes X'1B' and X'2B'), X'0141..00')
– 1 new XOA order (XOA Obtain Additional Exception Information)
– 3 new XOH-OPC self-defining fields (X'000F', X'0024', and X'0027')
– 3 new object self-defining fields (TAP , TOC, and TDD)
– 15 new STM property pairs (X'1204', X'1205', X'1600', X'2001', X'4113', X'4114', X'4115', X'4132',
X'4403', X'500E', X'5801', X'80F9', X'F102', X'F211', and X'FF03')
• Object area orientation extended to any angle measured in degrees and minutes [IPDS-FM-181]
• Object container version information can now be returned in the XOH-OPC reply [IPDS-FM-182]
• Portable Network Graphics (PNG) objects (AFPC PNG Subset) [IPDS-FM-183]
• Preprinted Form Overlay (PFO) – provides a more accurate simulation of preprinted forms [IPDS-FM-184]
• Remove Resident Resource (RRR) – new command to allow deactivated data-object resources and [IPDS-FM-185]
data-object-font components to be removed from the printer
• Request Resident Resource List (RRRL) – new command to allow a print server to obtain a list of all [IPDS-FM-186]
printer-resident resources that includes information useful to a resource administrator [IPDS-FM-187]


x IPDS Reference
• Royal Mail Mailmark TM bar codes [IPDS-FM-188]
• Text objects – allows text to be packaged in an object and positioned on a page or overlay like any [IPDS-FM-189]
other presentation object; defines a new command called Write Text Control (WTC)
• TIFF LZW with Differencing Predictor compression algorithm for IOCA images [IPDS-FM-190]
• Updates to the description of unsupported IOCA function in an IPDS environment [IPDS-FM-191]
• XOA Obtain Additional Exception Information – new XOA order to allow a printer server to obtain [IPDS-FM-192]
additional debug information after a NACK has been reported
• Clarifications for: [IPDS-FM-193]
– Color and color management
– Exception IDs
– Length field ranges
– Mixing rules
– Secondary resources
– Text-major text
– Triplets
Twelfth Edition published by AFP Consortium
AFPC-0001-11 dated December 2016
This edition provides enhanced detail to support the IPDS products that were introduced in the years
2014 through 2016 and to support the work of the AFP Consortium. Specifically, the following new [IPDS-FM-194]
function and clarification has been added:
• Clarifications for: [IPDS-FM-195]
– Better identification of early IPDS products manufactured by IBM and Océ
– IOCA self-defining fields have been labeled to distinguish them from IPDS self-defining fields;
note that both IOCA and IPDS defines self-defining fields
– Retired items and retired exception IDs
– Sense data format used for various exception IDs
• Glossary definitions for several terms (particularly color, FOCA, GOCA, and metadata terms) [IPDS-FM-196]
• Glossary updated to include the current definition for all AFP terms [IPDS-FM-197]
• GOCA custom patterns supported [IPDS-FM-198]
• GOCA linear and radial gradients supported [IPDS-FM-199]
• Improvements to object-container-version reporting: [IPDS-FM-200]
– Explicitly listed the object OIDs that constitute the variations when describing the object containers
that have variations defined for them
– TrueType/OpenType added to the list of object containers that have variations defined for them
• New appendix describing each numbered retired item and also items that have been unretired [IPDS-FM-201]
• New data stream structures: [IPDS-FM-202]
– 1 new text subset ID (LF4)
– 2 new object OIDs (AFPC SVG Subset object and non-OCA resource object)
– 3 new XOH-OPC self-defining fields (X'0025', X'0026', and X'0028')
– 25 new STM property pairs (X'1206', X'1207', X'1208', X'1209', X'120A', X'120B', X'1304', X'1305',
X'2002', X'4116', X'4117', X'4130', X'4131', X'6004', X'6005', X'6006', X'6007', X'6008', X'6009',
X'A0nn' (used in new places), X'F004', X'F005', X'F604', X'F605', and X'FC01')
– 45 new and updated exception IDs (X'0109..00', X'020D..0F', X'020D..13', X'020E..02',
X'020E..03', X'020E..04', X'020E..05', X'0256..B1', X'0256..B2', X'0256..B3', X'0256..B4',
X'0257..02', X'027C..0D', X'0300..08', X'035E..00', X'0368..06', X'03DC..00', X'03DC..01',
X'03DC..02', X'03DC..03', X'03DC..04', X'03DC..05', X'03DC..06', X'03DC..07', X'03DD..00',
X'03DD..01', X'03DD..02', X'03DD..03', X'03DD..04', X'03DD..05', X'03DD..06', X'03DD..07',
X'03DE..00', X'03DE..01', X'03DE..02', X'03DE..03', X'03DE..04', X'03DE..05', X'03DE..06',
X'03DE..07', X'03DF..00', X'03DF..01', X'03DF..02', X'0500..04', and X'059B..10')
• New finishing support: [IPDS-FM-203]


– New finishing operation for fold out
– New finishing options field defined with a new option to crease instead of fold for all defined fold
operations
– New XOH-OPC Finishing Options self-defining field so that a printer can report all supported
options
• New OPC Recognized Group ID Formats self-defining field so that a printer can list the Group ID [IPDS-FM-204]
(X'00') triplet formats that are recognized; this self-defining field can help a host program to
determine what Group ID format to use
• New OPC Supported Device Resolutions self-defining field so that a printer can provide a list of the [IPDS-FM-205]
currently supported resolutions; previously, the IM-Image and Coded-Font Resolution self-defining
field provided some of this information, but it was not always complete and it only covered the
resolution-dependent data types
• New subset ID LF4 added to the STM Loaded-Font command-set vector to identify support for [IPDS-FM-206]
FOCA code pages when LF3 FOCA fonts are not also supported; code pages are used with
TrueType/OpenType font objects as well as with LF3 fonts
• Printer support for the MO:DCA GA (Graphic Arts) function set [IPDS-FM-207]
• Seventeen new STM property pairs for functions that are optional, but previously not reported in [IPDS-FM-208]
STM replies
• STM orientation-support property pair (X'A0nn') now used for all presentation objects [IPDS-FM-209]
• Support for Non-OCA Resource objects (used with PDF and SVG objects) [IPDS-FM-210]
• Support for Scalable Vector Graphics (SVG) objects [IPDS-FM-211]
• The description of the X'059B..10' exception was updated to include an ordered list of possible [IPDS-FM-212]
causes
• TrueType/OpenType fonts can be used with OCA presentation objects and with PDF and SVG [IPDS-FM-213]
objects
• Two new STM property pairs to cover options and choices related to the GOCA architecture (font [IPDS-FM-214]
positioning and cell positioning) [IPDS-FM-215]


xii IPDS Reference
## How to Use This Book
This book is divided into sixteen chapters and four appendixes:
• Chapter 1, “A Presentation Architecture Perspective” introduces the AFPC presentation [IPDS-FM-216]
architectures and describes the role of data streams and data objects.
• Chapter 2, “Introduction to IPDS” introduces the Intelligent Printer Data Stream as a component [IPDS-FM-217]
of printing subsystems. This chapter also describes IPDS functional divisions.
• Chapter 3, “IPDS Overview” describes the key concepts and terminology used by IPDS [IPDS-FM-218]
Architecture.
• Chapter 4, “Device-Control Command Set” describes the commands used to set up a logical [IPDS-FM-219]
page, communicate device controls, manage resources, and handle the acknowledgment protocol.
• Chapter 5, “Text Command Set” describes the commands for presenting PTOCA text [IPDS-FM-220]
information in a page, a page segment, or an overlay.
• Chapter 6, “IM-Image Command Set” describes the commands for presenting image raster [IPDS-FM-221]
data in a page, a page segment, or an overlay.
• Chapter 7, “IO-Image Command Set” describes the commands for presenting IOCA image [IPDS-FM-222]
data in a page, a page segment, or an overlay.
• Chapter 8, “Graphics Command Set” describes the commands for presenting GOCA graphics [IPDS-FM-223]
data in a page, a page segment, or an overlay.
• Chapter 9, “Bar Code Command Set” describes the commands for presenting BCOCA data in [IPDS-FM-224]
a page, a page segment, or an overlay.
• Chapter 10, “Object Container Command Set” describes the commands for downloading object [IPDS-FM-225]
containers and presenting object container data in a page, a page segment, or an overlay.
• Chapter 11, “Metadata Command Set ” describes the commands for associating metadata with [IPDS-FM-226]
any of many types of objects.
• Chapter 12, “Overlay Command Set” describes the commands for controlling frequently [IPDS-FM-227]
accessed user data in a page, a page segment, or an overlay. An overlay contains its own environment and
appears the same on every page on which it is printed.
• Chapter 13, “Page-Segment Command Set” describes the commands for controlling frequently [IPDS-FM-228]
accessed user data in a page. Unlike an overlay, a page segment is not independent of its page
environment.
• Chapter 14, “Loaded-Font Command Set” describes the commands for downloading coded- [IPDS-FM-229]
font information to the printer.
• Chapter 15, “Triplets” describes substructures called triplets that are used within some IPDS [IPDS-FM-230]
commands.
• Chapter 16, “Exception Reporting” provides additional information about the Acknowledge [IPDS-FM-231]
Reply that is used by IPDS devices for exception reporting. A complete list of printer exception-reporting
codes is provided along with a description of page-counter and copy-counter adjustments.
• Chapter 17, “Compliance” provides a complete description of the IPDS functional divisions, [IPDS-FM-232]
IPDS support requirements, and migration functions.
• Appendix A, “IPDS Commands Sorted by Command Code” provides a table listing the IPDS [IPDS-FM-233]
command codes in numeric order, the meaning of the codes, and the section in this document where they
are described.
• Appendix B, “Examples of IPDS Command Sequences” provides examples of IPDS command [IPDS-FM-234]
sequences that could be used to drive an IPDS printer. [IPDS-FM-235]


• Appendix C, “Image Compression and Recording Algorithms” provides a brief description of [IPDS-FM-236]
the algorithms used in the IO-Image command set for image compression and recording.
• Appendix D, “Retired Items” lists each retired item that is mentioned within the body of this [IPDS-FM-237]
book and also lists those items that have been unretired.
The “Glossary” defines terms used within the book. [IPDS-FM-238]
## How to Read the Syntax Diagrams
Throughout this book, syntax is described using the following format that shows the syntax of a command in a
horizontal representation followed by a table showing the data of the command. Refer to “Notation
Conventions” and “The IPDS Command Format” for a detailed description of the
command syntax. The syntax includes six basic data types:
CODE Architected constant
CHAR Character string
BITS Bit string
UBIN Unsigned binary
SBIN Signed binary
UNDF Undefined type
## Command Syntax
Command
Length
Command ID Flags Correlation ID Data [IPDS-FM-239]
## Data Syntax
Offset Type Name Range Meaning Subset Range
The
field's
offset
The
field's
data
type
Name of field,
if applicable
Architecturally
valid range of
values
Meaning or purpose of the data element Range defined
by a subset of an
IPDS command
set
Note: The specific heading for the subset range column in a table will identify a specific subset, such as PS1
Range, or will indicate Required when the command does not belong to a specific subset.
The following is an example of IPDS syntax (for the Deactivate Page Segment command):
```
Length X'D66F' Flag CID Data
```
The data field is as follows:
Offset Type Name Range Meaning PS1 Range
0–1 CODE HAID X'0000'
X'0001' –
X'7EFF'
Deactivate All indicator
Page Segment Host-Assigned ID
X'0000'
X'0001' –
X'007F'


xiv IPDS Reference
## Related Publications
Several other publications can help you understand the architecture concepts described in this book. AFP
Consortium publications and a few other AFP publications are available on the AFP Consortium website,
www.afpcinc.org.
Table 1. AFP Consortium Architecture References
AFP Architecture Publication Order Number
AFP Programming Guide and Line Data Reference AFPC-0010
Bar Code Object Content Architecture Reference AFPC-0005
Color Management Object Content Architecture Reference AFPC-0006
Font Object Content Architecture Reference AFPC-0007
Graphics Object Content Architecture for Advanced Function Presentation Reference AFPC-0008
Image Object Content Architecture Reference AFPC-0003
Intelligent Printer Data Stream Reference AFPC-0001
Metadata Object Content Architecture Reference AFPC-0013
Mixed Object Document Content Architecture™ (MO:DCA) Reference AFPC-0004
Presentation Text Object Content Architecture Reference AFPC-0009
Table 2. Additional AFP Consortium Documentation
AFPC Publication Order Number
AFP Color Management Architecture™ (ACMA™) G550-1046 (IBM)
AFPC Company Abbreviation Registry AFPC-0012
AFPC Font Typeface Registry AFPC-0016
BCOCA Frequently Asked Questions AFPC-0011
MO:DCA-L: The OS/2 PM Metafile (.met) Format AFPC-0014
Presentation Object Subsets for AFP AFPC-0002
Recommended IPDS Values for Object Container Versions AFPC-0017
Table 3. AFP Font-Related Documentation
Publication Order Number
Character Data Representation Architecture Reference and Registry;
please refer to the online version for the most current information
(https://www.ibm.com/software/globalization/cdra/index.html)
SC09-2190 (IBM)
Font Summary for AFP Font Collection S544-5633 (IBM)
Technical Reference for Code Pages S544-3802 (IBM) [IPDS-FM-240]


Table 4. UP3I Architecture Documentation
UP3I Publication Order Number
Universal Printer Pre- and Post-Processing Interface (UP 3I) Specification Available at
www.afpcinc.org


xvi IPDS Reference


## Changes in This Edition
Changes between this edition and the previous edition are marked by a vertical bar “|” in the left margin. [IPDS-FM-241]
This edition provides enhanced detail to support the IPDS products that were introduced in the years 2017
through 2023 and to support the work of the AFP Consortium. Specifically, the following new function and
clarification has been added:
• Clarifications for: [IPDS-FM-242]
– The definition of foreground for GOCA graphics objects and BCOCA bar code objects
– The description of the scale-to-fit mapping option in the Image Resolution (X'9A') triplet
– The idea that the PDF presentation space size in the Object Container Presentation Space Size (X'9C')
triplet also specifies the PDF box that the PDF should be rendered to
– The OPC self-defining fields whose last encountered instance specifies the characteristic
– The orientation support required to satisfy MO:DCA interchange set IS/3
– The XOA Obtain Additional Exception Information (OAEI) order
• Glossary updated to include the current definition for all AFP terms [IPDS-FM-243]
• Known colorant names defined for the CMOCA Colorant Identification List tag are also supported in the OPC [IPDS-FM-244]
Colorant-Identification self-defining field
• Metadata now supported: [IPDS-FM-245]
– “AFP Tagging” metadata format supported
– New exception class: Metadata specification check
– New Metadata command set, including an MO1 command set subset and an MS1 data tower level
– New Metadata state, which can be entered from many other states
– New STM Metadata command-set vector
• MO:DCA GA (Graphic Arts) function set updated to remove “resident color profile” from the list of required [IPDS-FM-246]
IPDS support
• New data stream structures: [IPDS-FM-247]
– 1 new XOA Order:
◦ XOA Request Setup Name List (RSNL)
– 2 new triplets:
◦ Invoke Tertiary Resource (X'A2')
◦ Setup Name (X'9E')
– 2 new XOH-OPC self-defining fields (X'0029' and X'002A')
– 4 new Acknowledge Reply acknowledge types (X'07', X'08', X'47', and X'48')
– 5 new commands:
◦ Activate Setup Name (ASN), in the Device-Control command set
◦ Data Object Resource Equivalence 2 (DORE2), in the Object Container command set
◦ Delete Home-State Metadata (DHM), in the Metadata command set
◦ Write Metadata Control (WMC), in the Metadata command set
◦ Write Metadata (WM), in the Metadata command set [IPDS-FM-248]


xviii IPDS Reference
– 14 new STM property pairs (X'1306', X'1307', X'120D', X'120E', X'4304', X'5501', X'5506', X'700A',
X'80FA', X'D001', X'F212', X'F403', X'FF14', and X'FF48')
– 75 new and updated exception IDs (X'010A..00', X'010D..00', X'0200..01', X'0202..02', X'0202..05',
X'0205..01', X'020B..05', X'020D..10', X'020D..11', X'020D..12', X'020D..19', X'021A..01', X'021A..03',
X'021C..01', X'021E..01', X'0256..C1', X'0256..C2', X'0256..C3', X'0256..C4', X'0256..C5', X'0256..C6',
X'027A..01', X'027B..01', X'027C..09', X'027C..0A', X'029D..01', X'029D..02', X'029D..03', X'02A1..00',
X'02A1..01', X'02A2..00', X'02D0..00', X'02D0..01', X'040F..01', X'040F..04', X'040F..13', X'040F..14',
X'040F..15', X'040F..16', X'040F..17', X'040F..18', X'040F..19', X'040F..1A', X'040F..1B', X'040F..1C',
X'040F..1D', X'040F..1E', X'040F..20', X'040F..30', X'040F..31', X'040F..32', X'040F..33', X'040F..34',
X'040F..35', X'040F..36', X'040F..37', X'040F..38', X'040F..39', X'040F..3A', X'040F..3B', X'0412..05',
X'0500.03', X'059B..18', X'05B3..0F', X'05B3..10', X'05B3..11', X'0601..00', X'0602..00', X'0602..10',
X'0602..20', X'0602..30', X'0602..40', X'0602..50', X'0603..00', and X'50FA..00')
• New bar code support: [IPDS-FM-249]
– Aztec Code
– Data Matrix Rectangular Extension: 24 new rectangular sizes for the Data Matrix bar code type
– Intelligent Mail Package Barcode
– QR Code with Image, which allows printing images in conjunction with QR code bar codes; such images
are secondary resources to a QR code bar code
◦ New Invoke Tertiary Resource (X'A2') triplet is used to invoke CMRs for such images
– “T oo much data” support for the QR Code and Data Matrix bar codes
• New Data Object Resource Equivalence 2 (DORE2) command fixes a bug that was introduced when use of [IPDS-FM-250]
native object container TrueType/OpenType Fonts as secondary resources was added; DORE2 is very
similar to DORE, but adds the ability to specify the HAID pool that should be searched
• New IOCA support: [IPDS-FM-251]
– Ability for a printer to report support of IOCA transparency masks, for function sets that do not include that
functionality
– Function sets FS14 and FS48
– nColor images
– nColor Names parameter
• New OPC Printer Speed self-defining field to report a printer’s speed [IPDS-FM-252]
• New Parameter ID value for the OPC Product Identifier self-defining field to allow a printer to report [IPDS-FM-253]
information, such as the EC level, about the subsystems on the printer
• New Trim finishing support [IPDS-FM-254]
• “Object-container” state has been renamed to “object-container resource” state to better distinguish it from [IPDS-FM-255]
“page object container” state and “overlay object container” state
• PTOCA encrypted text strings supported [IPDS-FM-256]
• Setup Names, a new concept that allows association of a name with a set of printer configuration settings, [IPDS-FM-257]
supported, including:
– New Activate Setup Name (ASN) command
– New OPC Active Setup Name self-defining field
– New Setup Name (X'9E') triplet
– New XOA Request Setup Name List (RSNL) order
– Two new acknowledge types for the replies to the ASN and XOA-RSNL
• STM property pair X'120B' retired, becoming retired item 149 [IPDS-FM-258]


Contents





- Preface [IPDS-FM-259]
  - Who Should Read This Book [IPDS-FM-260]
  - AFP Consortium (AFPC) [IPDS-FM-261]
  - Publication History [IPDS-FM-262]
  - How to Use This Book [IPDS-FM-263]
  - How to Read the Syntax Diagrams [IPDS-FM-264]
  - Related Publications [IPDS-FM-265]
  - Changes in This Edition [IPDS-FM-266]
- Figures [IPDS-FM-267]
- Tables [IPDS-FM-268]
    [Chapter 1
    The Presentation Environment
    Architecture Components
    Data Streams
    Objects
    # Chapter 2
    IPDS Architecture as a Component of Printing Subsystems
    The Spooled System Environment
    The Mainframe Interactive Environment
    The Intelligent Workstation or Departmental System Environment
    The Local Area Network Environment
    Communication with an IPDS Device
    IPDS Functional Divisions [IPDS-FM-269]
    # Chapter 3
    The IPDS Presentation Environment
    Cut-Sheet Emulation Mode
    Overlays and Page Segments
    Using an Overlay as a Preprinted Form
    IPDS Mixing Rules
    Foreground and Background
    Merging Presentation Spaces
    General Mixing Rules
    Formblend Mixing Rule
    IPDS Default Mixing Rule
    Logical Page and Object Area Coloring
    Specifying Color
    Color Management
    Basic Concepts
    CMR-Usage Hierarchy
    Data-Object-Level CMRs
    Medium-Overlay-Level CMRs
    Page-Overlay-Level CMRs
    Page-Level CMRs
    Home-State-Level CMRs
    Default CMRs
    CMR-Usage Hierarchy Processing
    Selecting Color-Conversion CMRs
    Pass-Through Audit Color-Conversion CMRs
    CMR-Usage Hierarchy Summary
    Color Management Compliance
    Required Color-Management Function
    Optional Color-Management Function
    Color Resource Relationships
    Ordered Data


    xx IPDS Reference
    Fixed Medium Information
    Fonts
    Coded-Font Components
    Data-Object-Font Components
    Expressing Linear Measurements
    Coordinate Systems
    X
    m,Ym Coordinate System (Medium)
    Xp,Yp Coordinate System (Logical Page)
    I,B Coordinate System (Text)
    Character Development
    I,B Orientation
    The Four Basic Object Orientations
    Other Object Orientations
    Xt,Yt Coordinate System (Text)
    Xg,Yg Coordinate System (Graphics)
    Xio,Yio Coordinate System (IO Image)
    Xbc,Ybc Coordinate System (Bar Code)
    Xoc,Yoc Coordinate System (Object Container)
    Xoa,Yoa Coordinate System (Object Area)
    Coordinate System Relationships
    The Valid Printable Area
    The User-Printable Area
    Position Exceptions for Presentation Objects
    Logical Positioning and Physical Pels
    Processing IPDS Commands
    Notation Conventions
    L-Unit Range Conversion Algorithm
    The IPDS Command Format
    Host Acknowledgment Requests
    Printer Acknowledge Replies
    IPDS Operating States
    Home State
    Presentation-
    Object States
    Page State
    Overlay State
    Page Segment State
    Font State
    Code Page State
    IO-Image Resource State
    Object-Container Resource State
    Metadata State
    Anystate
    Summary of the IPDS States and Commands
    IPDS Resources
    Resource IDs
    HAID Pools
    Resource Management
    Physical Presence of Resources
    Availability of Resources
    Invocation of Resources
    Data Object Resources, Data-Object-Font Components, and Setup Files
    Multi-Page Resource Objects
    Pre-Rasterizing and Caching Presentation Objects
    Saving and Including Pages
    Relationship Between FOCA Character Metrics and TrueType Character Metrics
    Horizontal Metrics
    Vertical Metrics
    Simulating Vertical Metrics
    Resource Management Summary
    Metadata
    Exception Handling
    Pages Exactly-As-Requested [IPDS-FM-270]


    Best-Possible Output
    Exception-Handling Combinations
    Exception-Handling Control
    Presentation Fidelity Control
    Color Simulation Guidelines
    Simulating Out-of-Gamut Colors
    Simulating Colors on a Black-Only Printer without Grayscale Capability
    Simulating Colors with Grayscale
    Default Handling
    # Chapter 4
    Acknowledge Reply
    Acknowledge Reply Data Format
    Four-Byte Page and Copy Counter Format
    Eighteen-Byte Page and Copy Counter Format
    General Rules for the Acknowledge Reply
    Activate Resource
    Activate Resource Triplet Considerations
    Activate Setup Name

    ASN Reply
    Apply Finishing Operations
    Apply Finishing Operations Triplet Considerations
    Begin Page
    Deactivate Font
    Define User Area
    End
    End Page
    Include Saved Page
    Group ID (X'00') Triplet Considerations
    Invoke CMR
    Load Copy Control
    Rules for Copy Subgroup Exception Processing
    When The Page Is To Be Printed:
    When The Page Is Not To Be Printed:
    Load Font Equivalence
    Logical Page Descriptor
    Area Coloring Triplet Considerations
    Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
    Logical Page Position
    Manage IPDS Dialog
    No Operation
    Presentation Fidelity Control
    Rasterize Presentation Object
  - Processing Rules [IPDS-FM-271]
    Preprocessing Overlays
    Preprocessing Data Object Resources
  - Limitations [IPDS-FM-272]
  - Command Syntax [IPDS-FM-273]
    Color Specification (X'4E') Triplet Considerations
    Object Offset (X'5A') Triplet Considerations
    Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
    Image Resolution (X'9A') Triplet Considerations
    Object Container Presentation Space Size (X'9C') Triplet Considerations
    Sense Type and Model
    Acknowledge Reply for Sense Type and Model
    Device-Control Command-Set Vector
    Text Command-Set Vector
    IM-Image Command-Set Vector
    IO-Image Command-Set Vector
    Graphics Command-Set Vector
    Bar Code Command-Set Vector
    Object Container Command-Set Vector
    Metadata Command-Set Vector [IPDS-FM-274]



    xxii IPDS Reference
    Overlay Command-Set Vector
    Page Segment Command-Set Vector
    Loaded-Font Command-Set Vector
    Set Home State
    Set Presentation Environment
    Presentation Environment Triplet Considerations
    Execute Order Anystate
    XOA Activate Printer Alarm
    XOA Alternate Offset Stacker
    XOA Control Edge Marks
    XOA Discard Buffered Data
    XOA Discard Unstacked Pages
    XOA Exception-Handling Control
    XOA Mark Form
    XOA Obtain Additional Exception Information
    OAEI Reply
    XOA Print-Quality Control
    XOA Request Resource List
    Resource List Query
    Resource List Reply
    XOA Request Setup Name List [IPDS-FM-275]

    RSNL Reply
    Setup Name Information entry
    Execute Order Home State
    XOH Deactivate Saved Page Group
    Group ID (X'00') Triplet Considerations
    XOH Define Group Boundary
    Coded Graphic Character Set Global Identifier (X'01') Triplet Considerations
    Finishing Operation (X'85') Triplet Considerations
    UP3I Finishing Operation (X'8E') Triplet Considerations
    XOH Eject to Front Facing
    XOH Erase Residual Font Data
    XOH Erase Residual Print Data
    XOH Obtain Printer Characteristics
    OPC Command
    OPC Reply
    Printable-Area Self-Defining Field
    Symbol-Set Support Self-Defining Field
    IM-Image and Coded-Font Resolution Self-Defining Field
    Storage Pools Self-Defining Field
    Retired Item 130 (Standard OCA Color Value Support Self-Defining Field)
    Installed Features Self-Defining Field
    Available Features Self-Defining Field
    Resident Symbol-Set Support Self-Defining Field
    Print-Quality Support Self-Defining Field
    XOA-RRL RT & RIDF Support Self-Defining Field
    Activate Resource RT & RIDF Support Self-Defining Field
    Medium Modification IDs Supported Self-Defining Field
    Deprecated (Common Bar Code Type/Modifier Self-Defining Field)
    Bar Code Type/Modifier Self-Defining Field
    Media-Destinations Self-Defining Field
    Supported Group Operations Self-Defining Field
    Product Identifier Self-Defining Field
    Object-Container Type Support Self-Defining Field
    DF Deactivation Types Supported Self-Defining Field
    PFC Triplets Supported Self-Defining Field
    Printer Setup Self-Defining Field
    Finishing Operations Self-Defining Field
    UP3I Tupel Self-Defining Field
    UP3I Paper Input Media Self-Defining Field
    Colorant-Identification Self-Defining Field
    Device-Appearance Self-Defining Field [IPDS-FM-276]


    Keep-Group-T ogether-as-a-Recovery-Unit Self-Defining Field
    Recognized Group ID Formats Self-Defining Field
    Supported Device Resolutions Self-Defining Field
    Object-Container Version Support Self-Defining Field
    Finishing Options Self-Defining Field
    Printer Speed Self-Defining Field
    Active Setup Name Self-Defining Field
    XOH Page Counters Control
    XOH Print Buffered Data
    XOH Remove Saved Page Group
    Group ID (X'00') Triplet Considerations
    XOH Select Input Media Source
    XOH Select Medium Modifications
    XOH Separate Continuous Forms
    XOH Set Media Origin
    XOH Set Media Size
    XOH Specify Group Operation
    XOH Stack Received Pages
    XOH Trace
    Acknowledge Reply for the XOH Trace Command
    Printer-Generated Trace Entries
    Begin-Trace Trace Entry
    Begin-Page Trace Entry
    Begin-Overlay Trace Entry
    Begin-Presentation-Object Trace Entry
    CMRs-Used Trace Entry
    CMR-Activation Trace Entry
    CMR-Invocation Trace Entry
    Media-Source-Selection Trace Entry
    Exception-ID Trace Entry
    Free-Form Trace Entry
    Include-Saved-Page Trace Entry
    Include-Overlay Trace Entry
    Include-Data-Object Trace Entry
    Device-Appearance Trace Entry
    Color-Fidelity Trace Entry
    CMR-Tag-Fidelity Trace Entry
    Begin-Print-Unit Trace Entry
    Trace-Full Trace Entry
    End-Object Trace Entry
    CMR-Deactivation Trace Entry [IPDS-FM-277]
    # Chapter 5
    The Text Presentation Space
    The Text Object Area
    Mapping the Text Presentation Space
    Interaction Between Text Objects and Text-Major Text
    Load Equivalence
    Write Text Control
    Text Area Position
    Text Output Control
    Area Coloring Triplet Considerations
    Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
    Text Data Descriptor
    Write Text
    Spanning
    Unicode Support
    Unicode Complex Text
    PTOCA Unicode Complex Text (UCT) Control Sequence
    PTOCA Glyph Layout Controls
    Control Sequence Summary [IPDS-FM-278]
    # Chapter 6


    xxiv IPDS Reference
    Write Image Control
    Image Size
    Input Image Data Format
    Image Magnification
    Output Image Orientation
    Output Image Location
    Image Color
    Standard OCA Color-Value Table
    Write Image
    # Chapter 7
    The IO-Image Presentation Space
    The IO-Image Object Area
    Mapping the IO-Image Presentation Space
    Using IO Image as a Resource
    Write Image Control 2
    Image Area Position
    Image Output Control
    Area Coloring Triplet Considerations
    Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
    Image Data Descriptor
    Write Image 2
    Unsupported IOCA function in an IPDS Environment [IPDS-FM-279]
    # Chapter 8
    Drawing-Order Coordinate System
    Graphics Presentation Space Window
    Graphics Object Area
    Positioning the Graphics Presentation Space Window
    Write Graphics Control
    Graphics Area Position
    Graphics Output Control
    Area Coloring Triplet Considerations
    Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
    Mapping Control Options
    Scale-to-Fit Mapping
    Scale-to-Fill Mapping
    Center-and-Trim Mapping
    Position-and-Trim Mapping
    Graphics Data Descriptor
    Write Graphics
    Begin Segment Introducer
    Drawing Orders
    # Chapter 9
    Bar Code Presentation Space
    Bar Code Object Area
    Positioning the Bar Code Presentation Space
    Write Bar Code Control
    Self-Defining Fields within the Write Bar Code Control
    Bar Code Area Position
    Bar Code Output Control
    Area Coloring Triplet Considerations
    Invoke CMR (X'92') and Invoke Tertiary Resource X'A2' Triplet Considerations
    Bar Code Data Descriptor
    Color Specification (X'4E') Triplet Considerations
    Write Bar Code
# Chapter 10. Object Container Command Set.................................. 563
Object Container Presentation Space...................................................................................................... 564
Object Container Object Area................................................................................................................. 564
Data Object Resource Equivalence Entries............................................................................................... 565
Data Object Resource Equivalence......................................................................................................... 569 [IPDS-FM-280]


Data Object Resource Equivalence 2....................................................................................................... 571
Deactivate Data-Object-Font Component................................................................................................. 573
Deactivate Data Object Resource........................................................................................................... 574
Include Data Object............................................................................................................................. 575
Data Object Area Position................................................................................................................. 577
Data Object Output Control............................................................................................................... 580
Override for Presentation Space Reset Mixing (X'70') triplet................................................................. 584
Color Management Resources...................................................................................................... 584
Data Object Data Descriptor.............................................................................................................. 585
Override for Color Specification (X'4E') Triplet................................................................................... 586
Override for Object Offset (X'5A') Triplet.......................................................................................... 588
Override for Image Resolution (X'9A') Triplet..................................................................................... 588
Override for Object Container Presentation Space Size (X'9C') Triplet.................................................... 589
Remove Resident Resource.................................................................................................................. 590
Request Resident Resource List............................................................................................................. 592
Acknowledge Reply for Request Resident Resource List......................................................................... 593
Write Object Container Control............................................................................................................... 595
Object Container Area Position.......................................................................................................... 596
Object Container Output Control......................................................................................................... 599
Area Coloring Triplet Considerations............................................................................................... 602
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations.................................................. 602
Mapping Control Options.............................................................................................................. 603
Scale-to-Fit Mapping............................................................................................................... 603
Center-and-Trim Mapping......................................................................................................... 604
Position-and-Trim Mapping....................................................................................................... 605
Position Mapping.................................................................................................................... 606
Scale-to-Fill Mapping............................................................................................................... 606
UP
3I-Print-Data Mapping.......................................................................................................... 607
Object Container Data Descriptor....................................................................................................... 609
Color Management Triplet Considerations........................................................................................ 610
Color Specification (X'4E') Triplet Considerations................................................................................611
Image Resolution (X'9A') Triplet Considerations.................................................................................611
Object Offset (X'5A') Triplet Considerations...................................................................................... 612
Object Container Presentation Space Size (X'9C') Triplet Considerations................................................ 612
TrueType/OpenType Font Triplet Considerations............................................................................... 612
Write Object Container......................................................................................................................... 613 [IPDS-FM-281]
# Chapter 11. Metadata Command Set........................................ 615
Delete Home-State Metadata................................................................................................................. 616
Write Metadata Control......................................................................................................................... 617
Metadata Data Descriptor................................................................................................................. 618
Write Metadata................................................................................................................................... 619 [IPDS-FM-282]
# Chapter 12. Overlay Command Set......................................... 621
Overlay Command-Set Commands......................................................................................................... 621
Begin Overlay..................................................................................................................................... 623
Deactivate Overlay.............................................................................................................................. 625
Include Overlay................................................................................................................................... 627 [IPDS-FM-283]
# Chapter 13. Page-Segment Command Set.................................... 631
Page-Segment Command-Set Commands............................................................................................... 631
Begin Page Segment........................................................................................................................... 632
Deactivate Page Segment..................................................................................................................... 633
Include Page Segment......................................................................................................................... 634 [IPDS-FM-284]
# Chapter 14. Loaded-Font Command Set..................................... 635
Graphic Character Placement Fundamentals............................................................................................ 636
Font Inline Sequences...................................................................................................................... 637
Font Parameter Relationships............................................................................................................ 641
Characters Printed in the 0° Font Inline Sequence............................................................................. 641
Characters Printed in the 90° Font Inline Sequence............................................................................ 642
Characters Printed in the 180° Font Inline Sequence.......................................................................... 643
Characters Printed in the 270° Font Inline Sequence.......................................................................... 644 [IPDS-FM-285]


xxvi IPDS Reference
Printing a Kerned Character.............................................................................................................. 645
Printing an Underscore Character....................................................................................................... 645
Printing an Underscore with PTOCA PT2............................................................................................. 646
LF1-Type Coded-Font Command Summary.............................................................................................. 647
The Long Format LFI....................................................................................................................... 648
The Short Format LFI....................................................................................................................... 648
Parts of an LF1-Type Coded Font....................................................................................................... 649
LF2-Type Coded-Font Command Summary.............................................................................................. 650
LF3-Type Coded-Font Command Summary.............................................................................................. 650
LF4-Type Code-Page Command Summary............................................................................................... 651
Invoking a Coded Font.......................................................................................................................... 651
Load Code Page................................................................................................................................. 652
Load Code Page Control....................................................................................................................... 656
Load Font.......................................................................................................................................... 662
Load Font Character Set Control............................................................................................................ 671
Load Font Control................................................................................................................................ 675
Load Font Index.................................................................................................................................. 687
Load Symbol Set................................................................................................................................. 698 [IPDS-FM-286]
# Chapter 15. Triplets.................................................... 703
Group ID (X'00') Triplet......................................................................................................................... 705
Coded Graphic Character Set Global Identifier (X'01') Triplet........................................................................ 709
Fully Qualified Name (X'02') Triplet...........................................................................................................711
Color Specification (X'4E') Triplet............................................................................................................ 713
Encoding Scheme ID (X'50') Triplet......................................................................................................... 720
Object Offset (X'5A') Triplet.................................................................................................................... 722
Local Date and Time Stamp (X'62') Triplet................................................................................................. 724
Group Information (X'6E') Triplet............................................................................................................. 727
Presentation Space Reset Mixing (X'70') Triplet......................................................................................... 731
Toner Saver (X'74') Triplet..................................................................................................................... 732
Color Fidelity (X'75') Triplet.................................................................................................................... 734
Metric Adjustment (X'79') Triplet............................................................................................................. 737
Font Resolution and Metric T echnology (X'84') Triplet.................................................................................. 740
Finishing Operation (X'85') Triplet........................................................................................................... 742
Text Fidelity (X'86') Triplet..................................................................................................................... 755
Finishing Fidelity (X'88') Triplet............................................................................................................... 757
Data Object Font Descriptor (X'8B') Triplet................................................................................................ 759
Linked Font (X'8D') Triplet..................................................................................................................... 765
UP
3I Finishing Operation (X'8E') Triplet.................................................................................................... 767
Color Management Resource Descriptor (X'91') Triplet................................................................................ 769
Invoke CMR (X'92') Triplet..................................................................................................................... 772
Rendering Intent (X'95') Triplet............................................................................................................... 774
CMR Tag Fidelity (X'96') Triplet............................................................................................................... 777
Device Appearance (X'97') Triplet........................................................................................................... 779
Image Resolution (X'9A') Triplet.............................................................................................................. 781
Object Container Presentation Space Size (X'9C') Triplet............................................................................. 783
Setup Name (X'9E') Triplet.................................................................................................................... 786
Invoke Tertiary Resource (X'A2') Triplet.................................................................................................... 787 [IPDS-FM-287]
# Chapter 16. Exception Reporting.......................................... 789
General Rules for Exceptions................................................................................................................. 789
Exception-Handling Control................................................................................................................... 792
Exception Reporting........................................................................................................................ 793
Alternate Exception Actions............................................................................................................... 793
Exception-Presentation Processing..................................................................................................... 794
Page Continuation...................................................................................................................... 794
Exception Page Print................................................................................................................... 794
Classes of Exceptions.......................................................................................................................... 795
Sense Byte Information........................................................................................................................ 796
Formats for Sense Bytes 4-18 and 20-23.............................................................................................. 798
Action Codes (Sense Byte 2 for Printers That Return 24 Sense Bytes)............................................................ 802
Exception Reporting Codes................................................................................................................... 806
Exception Classes........................................................................................................................... 807 [IPDS-FM-288]


Printer Exception IDs........................................................................................................................... 807
Command-Reject Exceptions............................................................................................................ 808
Equipment Check with Intervention Required Exceptions......................................................................... 809
Intervention-Required Exceptions........................................................................................................811
Equipment-Check Exceptions............................................................................................................ 819
Data-Check Exceptions.................................................................................................................... 820
Specification Checks—Metadata Exceptions......................................................................................... 824
Specification Checks—IO-Image Exceptions......................................................................................... 826
Specification Checks—Bar Code Exceptions........................................................................................ 835
Specification Checks—Graphics Data Exceptions.................................................................................. 846
Specification Checks—General Exceptions.......................................................................................... 857
Conditions Requiring Host Notification................................................................................................. 918
Data Object Error Codes....................................................................................................................... 922
Error Codes for Anacomp and AnaStack Objects................................................................................... 922
Error Codes for Color Mapping Table and Color Profile Objects................................................................. 922
Error Codes for IO-Image Objects and IOCA Tile Resources..................................................................... 922
Error Codes for Other Data Objects..................................................................................................... 922
Page and Copy Counter Adjustments...................................................................................................... 926
Page and Copy Counter Adjustments When a Data-Stream Exception Occurs................................................. 929
Page Counter Scenarios....................................................................................................................... 931
Introduction.................................................................................................................................... 931
Scenario 1..................................................................................................................................... 931
Scenario 2..................................................................................................................................... 932
Scenario 3..................................................................................................................................... 932
Scenario 4..................................................................................................................................... 933
Scenario 5..................................................................................................................................... 934
Scenario 6..................................................................................................................................... 935
Scenario 7..................................................................................................................................... 935
Scenario 8..................................................................................................................................... 936
Scenario 9..................................................................................................................................... 937
Scenario 10................................................................................................................................... 938
Scenario 11.................................................................................................................................... 939
Scenario 12................................................................................................................................... 940
Non-IPDS Sense Data.......................................................................................................................... 941
Non-IPDS Action Codes................................................................................................................... 941
Command-Reject Exceptions............................................................................................................ 944
Equipment Check with Intervention Required Exceptions......................................................................... 945
Intervention-Required Exceptions....................................................................................................... 946
Reserved for Bus-Out Parity Check Exceptions..................................................................................... 951
Equipment-Check Exceptions............................................................................................................ 952
Data-Check Exceptions.................................................................................................................... 960
Specification Checks—IO-Image Exceptions......................................................................................... 965
Specification Checks—Bar Code Exceptions........................................................................................ 966
Specification Checks—Graphics Data Exceptions.................................................................................. 967
Specification Check—General Exceptions............................................................................................ 969
Conditions Requiring Host Notification................................................................................................. 976 [IPDS-FM-289]
# Chapter 17. Compliance................................................. 977
IPDS Functional Divisions..................................................................................................................... 977
IPDS Command Sets and Command-Set Subsets...................................................................................... 979
Data Divisions.................................................................................................................................... 981
Data Towers and Data-T ower Levels................................................................................................... 981
IPDS Support Requirements.................................................................................................................. 983
Command-Set Support Requirements................................................................................................. 983
Data Tower Support Requirements..................................................................................................... 983
IPDS Support for MO:DCA Interchange Set IS/3........................................................................................ 984
Required IPDS Command Set support................................................................................................. 984
Additional Required Support.............................................................................................................. 985
Additional required support for the MO:DCA GA (Graphic Arts) Function Set.................................................... 986
Migration Functions............................................................................................................................. 987 [IPDS-FM-290]
# Appendix A. IPDS Commands Sorted by Command Code........................ 989


xxviii IPDS Reference [IPDS-FM-291]
# Appendix B. Examples of IPDS Command Sequences........................... 993
A Printer-Initialization Sequence............................................................................................................. 995
The Page-Segment Sequence............................................................................................................... 996
The Overlay Sequence......................................................................................................................... 997
The Page Sequence............................................................................................................................ 999 [IPDS-FM-292]
# Appendix C. Image Compression and Recording Algorithms..................... 1001
Modified ITU—TSS Modified READ Algorithm (IBM MMR).......................................................................... 1001
Run-Length 4 Compression Algorithm (RL4)............................................................................................ 1002
ABIC (Bilevel Q-Coder) Compression Algorithm (ABIC).............................................................................. 1003
Concatenated ABIC Compression Algorithm............................................................................................ 1003
ITU—TSS T.4 Facsimile Coding Scheme (G3 MH, One-Dimensional)............................................................ 1003
ITU—TSS T.4 Facsimile Coding Scheme (G3 MR, Two-Dimensional)............................................................ 1003
ITU—TSS T.6 Facsimile Coding Scheme (G4 MMR) Compression Algorithm.................................................. 1003
ISO/ITU—TSS JPEG Compression Algorithms......................................................................................... 1003
JBIG2 (Joint Bi-level Image Experts Group) Compression Algorithm.............................................................. 1004
Solid Fill Rectangle............................................................................................................................. 1004
TIFF LZW Compression Algorithm......................................................................................................... 1004
TIFF LZW with Differencing Predictor Compression Algorithm...................................................................... 1004
RIDIC Recording Algorithm.................................................................................................................. 1005
Unpadded RIDIC Recording Algorithm.................................................................................................... 1005 [IPDS-FM-293]
# Appendix D. Retired Items.............................................. 1007
Notices............................................................ 1023
Trademarks....................................................................................................................................... 1024
Glossary........................................................... 1027
Index............................................................. 1069 [IPDS-FM-294]


## Figures
1. Presentation Environment........................................................................................................................ 1 [IPDS-FM-295]
2. Presentation Model................................................................................................................................ 3 [IPDS-FM-296]
3. Presentation Page.................................................................................................................................. 5 [IPDS-FM-297]
4. IPDS Products in the Spooled System Environment....................................................................................... 9 [IPDS-FM-298]
5. IPDS Products in the Mainframe Interactive Environment.............................................................................. 10 [IPDS-FM-299]
6. IPDS Products in the Workstation or Departmental System Environment.......................................................... 11 [IPDS-FM-300]
7. IPDS Products in the Local Area Network (LAN) Environment........................................................................ 12 [IPDS-FM-301]
8. IPDS Functional Divisions...................................................................................................................... 16 [IPDS-FM-302]
9. IPDS Presentation Spaces..................................................................................................................... 19 [IPDS-FM-303]
10. Object Areas in a Logical Page.............................................................................................................. 20 [IPDS-FM-304]
11. Examples of Multiple Pages on a Medium Presentation Space..................................................................... 21 [IPDS-FM-305]
12. Logical Division of Continuous Forms for Cut-Sheet Emulation..................................................................... 22 [IPDS-FM-306]
13. A Sample Page with an Overlay and Page Segment.................................................................................. 25 [IPDS-FM-307]
14. Merging Presentation Spaces............................................................................................................... 29 [IPDS-FM-308]
15. Examples of Shaded Areas.................................................................................................................. 32 [IPDS-FM-309]
16. CMR-Usage Hierarchy........................................................................................................................ 36 [IPDS-FM-310]
17. Selecting Appropriate Color-Conversion CMRs......................................................................................... 40 [IPDS-FM-311]
18. Xm,Ym Coordinate System: Recommended Default Media Origins................................................................. 49 [IPDS-FM-312]
19. Xm,Ym Coordinate System - Other Allowed Default Media Origins................................................................. 50 [IPDS-FM-313]
20. Xm,Ym Coordinate System for Envelopes................................................................................................. 51 [IPDS-FM-314]
21. Default Media Origin for Computer Output on 105 mm Microfilm (Shown Cut into Microfiche).............................. 52 [IPDS-FM-315]
22. Default Media Origin for Computer Output on 16 mm Microfilm (CINE Representation)...................................... 52 [IPDS-FM-316]
23. Default Media Origin for Computer Output on 16 mm Microfilm (COMIC Representation)................................... 52 [IPDS-FM-317]
24. Locating Data by Xm,Ym and Xp,Yp Coordinates......................................................................................... 53 [IPDS-FM-318]
25. Object Area Rotation in Xp,Yp Coordinate System...................................................................................... 54 [IPDS-FM-319]
26. The I,B Coordinate System on the Logical Page........................................................................................ 55 [IPDS-FM-320]
27. The Usable I,B Text Orientations............................................................................................................ 56 [IPDS-FM-321]
28. Object Area Rotation in I,B Coordinate System, Part 1................................................................................ 57 [IPDS-FM-322]
29. Object Area Rotation in I,B Coordinate System, Part 2................................................................................ 58 [IPDS-FM-323]
30. 15 Degree Object Area Rotation in I,B Coordinate System........................................................................... 59 [IPDS-FM-324]
31. Calculating the Current Text Position...................................................................................................... 61 [IPDS-FM-325]
32. Example of the Valid Printable Area........................................................................................................ 62 [IPDS-FM-326]
33. Example of the User’s Valid Printable Area............................................................................................... 64 [IPDS-FM-327]
34. Logical Position and Next Pel to Be Printed for Four I,B Orientations............................................................. 65 [IPDS-FM-328]
35. An Example of IPDS Command Processing............................................................................................. 66 [IPDS-FM-329]
36. Relationship between Presentation-Object States (other than IM-Image State) and Metadata State..................... 73 [IPDS-FM-330]
37. Relationship between Home State, Page State, and the Presentation-Object States, and between Page State [IPDS-FM-331]
and Metadata State........................................................................................................................... 74
38. Relationship between Home State, Overlay State, and the Presentation-Object States, and between Overlay [IPDS-FM-332]
State and Metadata State................................................................................................................... 75
39. Relationship between Home State, Page Segment State, and the Presentation-Object States............................ 76 [IPDS-FM-333]
40. Relationship between Home State and Font State..................................................................................... 77 [IPDS-FM-334]
41. Relationship between Home State and Code Page State............................................................................ 78 [IPDS-FM-335]
42. Relationship between Home State and IO-Image Resource State, and between IO-Image Resource State and [IPDS-FM-336]
Metadata State................................................................................................................................. 79
43. Relationship between Home State and Object-Container Resource State, and between Object-Container [IPDS-FM-337]
Resource State and Metadata State...................................................................................................... 80
44. Relationship between Other States and Metadata State.............................................................................. 81 [IPDS-FM-338]
45. The Complete IPDS State Diagram........................................................................................................ 87 [IPDS-FM-339]
46. Examples of IPDS Commands Involved with Saving and Including Pages..................................................... 106 [IPDS-FM-340]
47. TrueType Font Horizontal Metrics......................................................................................................... 107 [IPDS-FM-341]
48. TrueType Font Vertical Metrics............................................................................................................ 109 [IPDS-FM-342]
49. Some Exception-Handling Combinations................................................................................................116 [IPDS-FM-343]
50. Xm-Axis and Ym-Axis for Duplex Printing................................................................................................ 183 [IPDS-FM-344]
51. N-up Partitions for Various Physical Media............................................................................................. 184 [IPDS-FM-345]
52. N-up Partition Layouts with SMO = X'00'................................................................................................ 186 [IPDS-FM-346]
53. N-up Partition Layouts with SMO = X'01'................................................................................................ 186 [IPDS-FM-347]
54. N-up Partition Layouts with SMO = X'02'................................................................................................ 187 [IPDS-FM-348]
55. N-up Partition Layouts with SMO = X'03'................................................................................................ 187 [IPDS-FM-349]


xxx IPDS Reference
56. Using the LPP Command to Position the Logical Page When There Is One Page per Side............................... 210 [IPDS-FM-350]
57. Page Positioning and Orientation Examples............................................................................................211 [IPDS-FM-351]
58. Continuous-Forms, Duplex, 2-Up Example............................................................................................ 212 [IPDS-FM-352]
59. Example Showing Three Edge Marks................................................................................................... 272 [IPDS-FM-353]
60. Exception-Handling Control (spans four pages)....................................................................................... 285 [IPDS-FM-354]
61. Examples of Groups and Group Operations........................................................................................... 319 [IPDS-FM-355]
62. Examples of Nested Finishing Operations.............................................................................................. 320 [IPDS-FM-356]
63. BCOCA Bar Code Subsets................................................................................................................. 352 [IPDS-FM-357]
64. The XOH Set Media Origin Command (Cut-Sheet Media)......................................................................... 390 [IPDS-FM-358]
65. The XOH Set Media Origin Command (Wide Continuous-Forms Media)....................................................... 391 [IPDS-FM-359]
66. The XOH Set Media Origin Command (Narrow Continuous-Forms Media).................................................... 392 [IPDS-FM-360]
67. Examples of Commonly Used SMO/Duplex Combinations........................................................................ 393 [IPDS-FM-361]
68. The XOH Set Media Origin Command (Front Side of an Envelope).............................................................. 394 [IPDS-FM-362]
69. The XOH Set Media Origin Command (Back Side of an X [IPDS-FM-363]
m-Axis Duplex Envelope)......................................... 394
70. The XOH Set Media Origin Command (Back Side of a Ym-Axis Duplex Envelope).......................................... 394 [IPDS-FM-364]
71. Examples Showing the Effect of SMS (Method 3) and SMO Command Combinations..................................... 400 [IPDS-FM-365]
72. Text Presentation Space.................................................................................................................... 457 [IPDS-FM-366]
73. Locating, Sizing, and Orienting the Text Object Area................................................................................ 458 [IPDS-FM-367]
74. Example of Position Mapping for Text (Error Case).................................................................................. 468 [IPDS-FM-368]
75. IM Image Where the Output Size Is Less Than the Input Size..................................................................... 484 [IPDS-FM-369]
76. IM Image Where the Output Size is Greater Than the Input Size................................................................. 484 [IPDS-FM-370]
77. Example of IM-Image Magnification and Replication Where the Output Size Is Greater Than the Input [IPDS-FM-371]
Size............................................................................................................................................. 485
78. IO-Image Presentation Space............................................................................................................. 495 [IPDS-FM-372]
79. Locating, Sizing, and Orienting the Image Object Area............................................................................. 499 [IPDS-FM-373]
80. Example of Scale-to-Fit Mapping......................................................................................................... 506 [IPDS-FM-374]
81. Example of Center-and-Trim Mapping................................................................................................... 507 [IPDS-FM-375]
82. Example of Position-and-Trim Mapping................................................................................................. 508 [IPDS-FM-376]
83. Example of Replicate-and-Trim Mapping............................................................................................... 510 [IPDS-FM-377]
84. Example of IO-Image Scale-to-Fill Mapping.............................................................................................511 [IPDS-FM-378]
85. Graphics Presentation Space.............................................................................................................. 522 [IPDS-FM-379]
86. Graphics Mapping............................................................................................................................ 525 [IPDS-FM-380]
87. Locating, Sizing, and Orienting the Graphics Object Area.......................................................................... 527 [IPDS-FM-381]
88. An Example of Graphics Scale-to-Fit Mapping........................................................................................ 536 [IPDS-FM-382]
89. Example of Graphics Scale-to-Fill Mapping............................................................................................ 537 [IPDS-FM-383]
90. Example of Graphics Center-and-Trim Mapping...................................................................................... 538 [IPDS-FM-384]
91. Example of Graphics Position-and-Trim Mapping.................................................................................... 539 [IPDS-FM-385]
92. Example of the Bar Code Presentation Space Mapped into the Bar Code Object Area..................................... 549 [IPDS-FM-386]
93. Locating the Bar Code Object Area...................................................................................................... 551 [IPDS-FM-387]
94. Bar Code Symbols within the Bar Code Presentation Space...................................................................... 559 [IPDS-FM-388]
95. Locating, Sizing, and Orienting the Object Container Object Area............................................................... 565 [IPDS-FM-389]
96. An Example of Object Container Scale-to-Fit Mapping.............................................................................. 603 [IPDS-FM-390]
97. Example of Object Container Center-and-Trim Mapping............................................................................ 604 [IPDS-FM-391]
98. Example of Object Container Position-and-Trim Mapping.......................................................................... 605 [IPDS-FM-392]
99. Example of Object Container Scale-to-Fill Mapping.................................................................................. 606 [IPDS-FM-393]
100. Example of Object Container UP 3I-Print-Data Mapping........................................................................... 607 [IPDS-FM-394]
101. Example of Various Font Inline Sequences Producing the Same Character Orientation.................................. 637 [IPDS-FM-395]
102. Rotation of Character Patterns........................................................................................................... 638 [IPDS-FM-396]
103. The 32 Ways to Print Text................................................................................................................. 639 [IPDS-FM-397]
104. Font Inline Sequence of 0°................................................................................................................ 641 [IPDS-FM-398]
105. Font Inline Sequence of 90°.............................................................................................................. 642 [IPDS-FM-399]
106. Font Inline Sequence of 180°............................................................................................................. 643 [IPDS-FM-400]
107. Font Inline Sequence of 270°............................................................................................................. 644 [IPDS-FM-401]
108. Left-Kerned and Right-Kerned Character............................................................................................. 645 [IPDS-FM-402]
109. Underscore Character..................................................................................................................... 645 [IPDS-FM-403]
110. Examples of Underscores Created by the PTOCA Underscore Control Sequence......................................... 646 [IPDS-FM-404]
111. Overview of Sample Double-Byte Coded Font Section Records................................................................ 649 [IPDS-FM-405]
112. Example of the V(y) and W(y) Values.................................................................................................. 670 [IPDS-FM-406]
113. Character Rotation with Respect to the Logical Page Coordinate System.................................................... 691 [IPDS-FM-407]
114. Examples of Finishing Operations (spans three pages)........................................................................... 750 [IPDS-FM-408]
115. Reference Edges for Various Kinds of Media......................................................................................... 753 [IPDS-FM-409]
116. Character Placement Based on Character Rotation, Inline Direction, and Baseline Direction........................... 762 [IPDS-FM-410]


117. Layout of a Negative Acknowledge Reply (NACK).................................................................................. 797 [IPDS-FM-411]
118. Logical Paper Path and Page Counters................................................................................................ 802 [IPDS-FM-412]
119. IPDS Functional Divisions................................................................................................................. 978 [IPDS-FM-413]
120. Data Towers and Data-T ower Levels................................................................................................... 982 [IPDS-FM-414]
121. RIDIC Raster Scan......................................................................................................................... 1005 [IPDS-FM-415]
122. Example of GOC Replicate-and-Trim Mapping (retired item 135).............................................................. 1020 [IPDS-FM-416]


xxxii IPDS Reference


## Tables
1. AFP Consortium Architecture References................................................................................................. xiv [IPDS-FM-417]
2. Additional AFP Consortium Documentation............................................................................................... xiv [IPDS-FM-418]
3. AFP Font-Related Documentation........................................................................................................... xiv [IPDS-FM-419]
4. UP 3I Architecture Documentation.............................................................................................................xv [IPDS-FM-420]
5. Foreground and Background.................................................................................................................. 27 [IPDS-FM-421]
6. Default Mixing Rules............................................................................................................................. 31 [IPDS-FM-422]
7. Area Coloring...................................................................................................................................... 33 [IPDS-FM-423]
8. Object Coloring.................................................................................................................................... 33 [IPDS-FM-424]
9. Position Exceptions for Presentation Objects............................................................................................. 64 [IPDS-FM-425]
10. Field Ranges for Commonly-Supported Measurement Bases....................................................................... 69 [IPDS-FM-426]
11. IPDS Command Code Summary............................................................................................................ 83 [IPDS-FM-427]
12. Installation and Removal of Downloaded Resources.................................................................................. 93 [IPDS-FM-428]
13. Activation and Deactivation of Downloaded Resources............................................................................... 94 [IPDS-FM-429]
14. Activation and Deactivation of Resident Resources, Coded Fonts, and Data-Object Fonts................................. 95 [IPDS-FM-430]
15. Invocation of Resources....................................................................................................................... 98 [IPDS-FM-431]
16. IO Images used as Resources............................................................................................................... 99 [IPDS-FM-432]
17. Object Containers Used in the IPDS Environment...................................................................................... 99 [IPDS-FM-433]
18. Resource Management Summary......................................................................................................... 111 [IPDS-FM-434]
19. Device Control Commands................................................................................................................. 123 [IPDS-FM-435]
20. Acknowledge Protocol....................................................................................................................... 123 [IPDS-FM-436]
21. Acknowledge Types.......................................................................................................................... 128 [IPDS-FM-437]
22. Valid RID Entry Lengths..................................................................................................................... 138 [IPDS-FM-438]
23. RT/RIDF Triplet Combinations............................................................................................................. 155 [IPDS-FM-439]
24. Required and Optional Deactivation Types............................................................................................. 165 [IPDS-FM-440]
25. LCC Keywords................................................................................................................................. 178 [IPDS-FM-441]
26. Media Source Commands.................................................................................................................. 180 [IPDS-FM-442]
27. Exception Continuation Rules............................................................................................................. 194 [IPDS-FM-443]
28. XOA Order Summary........................................................................................................................ 268 [IPDS-FM-444]
29. Architecturally-Valid RT and RIDF Query Combinations............................................................................ 298 [IPDS-FM-445]
30. XOH Order Summary........................................................................................................................ 313 [IPDS-FM-446]
31. Group Operation Nesting................................................................................................................... 318 [IPDS-FM-447]
32. Triplets Used With Each Group Operation.............................................................................................. 322 [IPDS-FM-448]
33. OPC Self-Defining Field Summary....................................................................................................... 329 [IPDS-FM-449]
34. Common Values for Bar Code Types and Modifiers.................................................................................. 349 [IPDS-FM-450]
35. Relationship Between SDF X'000E' and X'000F'...................................................................................... 353 [IPDS-FM-451]
36. Printer-Generated Trace Entries.......................................................................................................... 414 [IPDS-FM-452]
37. Text Commands............................................................................................................................... 457 [IPDS-FM-453]
38. Summary of Control Sequences.......................................................................................................... 476 [IPDS-FM-454]
39. IM-Image Commands........................................................................................................................ 479 [IPDS-FM-455]
40. IM-Image and IO-Image Comparison.................................................................................................... 479 [IPDS-FM-456]
41. Standard OCA Color-Value Table......................................................................................................... 489 [IPDS-FM-457]
42. IO-Image Commands........................................................................................................................ 493 [IPDS-FM-458]
43. IM-Image and IO-Image Comparison.................................................................................................... 493 [IPDS-FM-459]
44. IOCA Self-Defining Fields................................................................................................................... 517 [IPDS-FM-460]
45. Exception IDs for IOCA Function Sets................................................................................................... 519 [IPDS-FM-461]
46. Graphics Commands........................................................................................................................ 521 [IPDS-FM-462]
47. Summary of GOCA Drawing Orders..................................................................................................... 544 [IPDS-FM-463]
48. Additional Drawing Orders Supported by Some Printers............................................................................ 546 [IPDS-FM-464]
49. Bar Code Commands........................................................................................................................ 547 [IPDS-FM-465]
50. Object Container Commands.............................................................................................................. 563 [IPDS-FM-466]
51. Secondary Resource Usage............................................................................................................... 566 [IPDS-FM-467]
52. Metadata Commands [IPDS-FM-468]
........................................................................................................................ 615
53. Overlay Commands.......................................................................................................................... 621 [IPDS-FM-469]
54. Page Segment Commands................................................................................................................. 631 [IPDS-FM-470]
55. Loaded-Font Commands................................................................................................................... 635 [IPDS-FM-471]
56. Identifying the Baseline Offset Value..................................................................................................... 640 [IPDS-FM-472]
57. Triplets Summary............................................................................................................................. 703 [IPDS-FM-473]
58. Standard OCA Color-Value Table......................................................................................................... 717 [IPDS-FM-474]
59. Color Space Examples...................................................................................................................... 718 [IPDS-FM-475]


xxxiv IPDS Reference
60. Examples of the Date Fields............................................................................................................... 726 [IPDS-FM-476]
61. Sheet and Group Operations.............................................................................................................. 744 [IPDS-FM-477]
62. CMR Processing Modes.................................................................................................................... 769 [IPDS-FM-478]
63. Exception ID Specific Information......................................................................................................... 799 [IPDS-FM-479]
64. Action Codes................................................................................................................................... 802 [IPDS-FM-480]
65. Error Codes for Data Objects.............................................................................................................. 922 [IPDS-FM-481]
66. Method of Adjusting the Counters........................................................................................................ 926 [IPDS-FM-482]
67. Method of Adjusting the Counters When a Data-Stream Exception Occurs................................................... 929 [IPDS-FM-483]
68. Retired Non-IPDS Action Codes.......................................................................................................... 941 [IPDS-FM-484]
69. Command-Set and Data-T ower Summary.............................................................................................. 981 [IPDS-FM-485]
70. IPDS Command Code Summary......................................................................................................... 989 [IPDS-FM-486]
71. IPDS Acknowledge Reply................................................................................................................... 991 [IPDS-FM-487]
72. A Typical IPDS Command Sequence.................................................................................................... 993 [IPDS-FM-488]
73. Run-Length 4 Compression............................................................................................................... 1002 [IPDS-FM-489]
74. Object Containers Used in the IPDS Environment (retired item 136)........................................................... 1021 [IPDS-FM-490]
75. Object Containers Used in the IPDS Environment (retired item 137)........................................................... 1021 [IPDS-FM-491]
76. Object Containers Used in the IPDS Environment (retired item 138)........................................................... 1021 [IPDS-FM-492]


