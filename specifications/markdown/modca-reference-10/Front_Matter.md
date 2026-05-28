Advanced Function Presentation Consortium
Data Stream and Object Architectures
Mixed Object Document
Content Architecture (MO:DCA)
Reference

Note:
Before using this information, read the information in “Notices”.
Eleventh Edition (June 2023)
This edition applies to the Mixed Object Document Content Architecture™ (MO:DCA™). It replaces and makes obsolete
the previous edition, AFPC-0004-09. This edition remains current until a new edition is published.
T echnical changes are indicatedin green, with a green vertical bar to the left of the change. Editorial changes that have no
technical significance are not noted. For a detailed list of changes, see “Summary of Changes”.
Internet
Visit our home page at: www.afpcinc.org [MODCA-FM-001]

# Preface
This book describes the functions and services associated with the MO:DCA architecture.
This book is a reference, not a tutorial. It complements individual product publications, but does not describe
product implementations of the architecture.
Who Should Read This Book
This book is for systems programmers and other developers who need such information to develop or adapt a
product or program to interoperate with other presentation products.
AFP Consortium
The Advanced Function Presentation™ (AFP™) architectures began as the strategic, general purpose
document and information presentation architecture for the IBM ® Corporation. The first specifications and
products go back to 1984. Although all of the components of the architecture have grown over the years, the
major concepts of object-driven structures, print integrity, resource management, and support for high print
speeds were built in from the start.
In the early twenty-first century, IBM saw the need to enable applications to create color output that is
independent from the device used for printing and to preserve color consistency, quality, and fidelity of the
printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™
(AFPCC™). The goal was to extended the AFP architecture with support for full-color devices including
support for comprehensive color management. The purpose of doing this via a consortium consisting of the
primary AFP architecture users was to build synergism with partners from across the relevant industries, such
as hardware manufacturers that produce printers as well as software vendors of composition, work flow, viewer
and transform tools. More than 30 members came together in regular meetings and work group sessions to
create the AFP Color Management Object Content Architecture™ (CMOCA™), and the extensions required to
support CMOCA within the other components of the AFP architecture. A major milestone was reached by the
AFP Color Consortium with the release of the specifications of all components of the AFP Color Management
Architecture™ (ACMA™) in May 2006.
Due to the success of the AFP Color Consortium, it was decided to broaden the scope of the consortium efforts
and in September 2006 IBM announced its plans to open up the complete scope of the AFP architecture to the
consortium. In June 2007, IBM's role as founding member of the consortium was transferred to the InfoPrint®
Solutions Company, an IBM/Ricoh® joint venture. In February 2009, the consortium was incorporated under a
new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open
standards body called the AFP Consortium™ (AFPC™). Ownership of and responsibility for the AFP
architectures was transferred at that time to the AFP Consortium. [MODCA-FM-002]

How to Use This Book
This book is divided into eight chapters, six appendixes, and a glossary.
• Chapter 1, “A Presentation Architecture Perspective” introduces the AFP architectures and positions the [MODCA-FM-003]
MO:DCA architecture as a strategic presentation data stream architecture.
• Chapter 2, “Introduction to the MO:DCA Architecture” introduces the concepts that form the basis of the [MODCA-FM-004]
MO:DCA architecture.
• Chapter 3, “MO:DCA Overview” provides an overview of MO:DCA data structures and their use. [MODCA-FM-005]
• Chapter 4, “MO:DCA Objects” provides the structure definitions for MO:DCA objects. [MODCA-FM-006]
• Chapter 5, “MO:DCA Structured Fields” provides the syntax and semantics for MO:DCA structured fields. [MODCA-FM-007]
• Chapter 6, “MO:DCA Triplets” provides the syntax and semantics for MO:DCA triplet data structures. [MODCA-FM-008]
• Chapter 7, “MO:DCA Interchange Sets” provides complete descriptions of the MO:DCA interchange sets and [MODCA-FM-009]
describes how products can become valid generators and receivers of the MO:DCA architecture.
• Chapter 8, “MO:DCA Function Sets” provides complete descriptions of the MO:DCA function sets and [MODCA-FM-010]
defines the extensions made by each registered function set to specific interchange sets of the MO:DCA
architecture.
• Appendix A, “Color Resources” provides information on color resources and on color to grayscale [MODCA-FM-011]
conversion.
• Appendix B, “Resource Access T able (RAT)” defines the Resource Access T able, which is used to locate and [MODCA-FM-012]
process resources such as TrueType and OpenType fonts.
• Appendix C, “MO:DCA Migration Functions” provides the syntax and semantics for MO:DCA migration [MODCA-FM-013]
structured fields, triplets, parameters, and provides the structure definitions for MO:DCA migration objects.
• Appendix D, “MO:DCA Registry” provides a registry for object type identifiers, media type identifiers, and [MODCA-FM-014]
color profile identifiers.
• Appendix E, “Cross-References” provides tables of MO:DCA structured fields and triplets sorted by identifier [MODCA-FM-015]
and by name.
• Appendix F , “Object OID Algorithms” provides the algorithms for generating Object Identifiers (OIDs) such as [MODCA-FM-016]
TrueType/OpenType font OIDs, Color Management Resource (CMR) OIDs, and data object OIDs.
• The Glossary defines some of the terms used within this book. [MODCA-FM-017]

How to Read the Syntax Diagrams
Throughout this book, syntax is described using the following formats. The syntax of the structured field, the
principal MODCA data structure, is shown with a horizontal representation, followed by a table that lists the
data elements contained in the structured field. The syntax of the triplet, the secondary MO:DCA data
structure, is shown using the table only. Six basic data types are used in the syntax descriptions:
CODE Architected constant
CHAR Character string, which may consist of any code points
BITS Bit string
UBIN Unsigned binary
SBIN Signed binary
UNDF Undefined type
Structured Field Introducer
Structured Field Introducer
SF Length (2B) ID = X'D3TTCC' Flags (1B) Reserved
X'0000'
Structured Field Data
The meanings of the elements of the horizontal representation are as follows:
• The Structured Field Introducer, which identifies the length and the function or type of the structured field, is [MODCA-FM-018]
composed of the following parameters:
Element Meaning
SF Length The total length of the structured field including the length of the SF Length
element.
ID = X'D3TTCC' The structured field identifier—consisting of the structured field class, type,
and category codes—that uniquely identifies each MO:DCA structured field.
Flags The set of bits or flags that identify if the structured field is segmented of if a
structured field extender or padding is to be used.
• The Structured Field Data, which provides the structured field's effect, is contained in the set of parameters [MODCA-FM-019]
described in the table.
For a detailed discussion of the data elements comprising MO:DCA structured fields, see “MO:DCA Structured
Field Syntax”.
Data
The syntax for a MO:DCA data structure is as follows:
Offset Type Name Range Meaning M/O Exc
The field's
byte offset.
The
field's
data
type.
Name of field, if
applicable.
Range of valid
values, if
applicable.
Meaning or purpose of the data
element.
or
O
Code
Certain fields may be denoted in the Meaning column as reserved. A reserved field is a parameter that has no
functional definition at the current time, but may have at some time in the future. All bytes in any field that the
MO:DCA architecture defines as a reserved field should be given a value of zero by generating applications.
Receiving applications should ignore any values contained in a reserved field. [MODCA-FM-020]

Additional columns appear to the right of the Meaning column. These columns are:
M/O Mandatory or optional
Exc Exception code for the exception conditions that are possible for the data element. See
“Exception Conditions” for further information concerning exception conditions.
The following is an example of the MO:DCA syntax:
Structured Field Introducer
SF Length (2B) ID = X'D3AFD8' Flags (1B) Reserved;
X'0000'
Structured Field Data
Offset Type Name Range Meaning M/O Exc
0–7 CHAR OvlyName Name of the overlay resource M X'06'
8–10 SBIN XolOset -32,768–32,767 X-axis origin for the page overlay M X'06'
X'FFFFFF' Retired value
11–13 SBIN YolOset -32,768–32,767 Y-axis origin for the page overlay M X'06'
X'FFFFFF' Retired value
14–15 CODE OvlyOrent X'0000', X'2D00',
X'5A00', X'8700'
The overlay's X-axis rotation
from the X axis of the including
page coordinate system:
X'0000' 0 degrees
X'2D00' 90 degrees
X'5A00' 180 degrees
X'8700' 270 degrees
O X'02'
16–n Triplets See “IPO Semantics”for triplet applicability.
O X'10'

Related Publications
Following is a list of the AFP Architecture publications.
AFP Architecture Publications
Several other publications can help you understand the architecture concepts described in this book. AFP
Consortium publications are available on the AFP Consortium web site at www.afpcinc.org.
Table 1. AFPC Architecture Documentation
AFP Architecture Publication Book Identification
AFP Programming Guide and Line Data Reference AFPC-0010
Bar Code Object Content Architecture™ Reference AFPC-0005
Color Management Object Content Architecture Reference AFPC-0006
Font Object Content Architecture Reference AFPC-0007
Graphics Object Content Architecture for AFP Reference AFPC-0008
Image Object Content Architecture Reference AFPC-0003
Intelligent Printer Data Stream™ Reference AFPC-0001
Metadata Object Content Architecture Reference AFPC-0013
Mixed Object Document Content Architecture (MO:DCA) Reference AFPC-0004
Presentation Text Object Content Architecture Reference AFPC-0009
Table 2. Additional AFP Consortium Documentation
AFPC Publication Book Identification
AFP Color Management Architecture (ACMA) G550–1046 (IBM)
AFPC Company Abbreviation Registry AFPC-0012
AFPC Font Typeface Registry AFPC-0016
BCOCA™ Frequently Asked Questions AFPC-0011
MO:DCA-L: The OS/2® PM Metafile (.met) Format AFPC-0014
Presentation Object Subsets for AFP AFPC-0002
Recommended IPDS™ Values for Object Container Versions AFPC-0017
Table 3. AFP Font-Related Documentation
AFP Font-Related Publication Book Identification
Character Data Representation Architecture Reference and Registry;
For the most current information, please refer to the online version at:
http://www-01.ibm.com/software/globalization/cdra
SC09-2190 (IBM)
Font Summary for AFP Font Collection S544-5633 (IBM)
Technical Reference for Code Pages S544-3802 (IBM) [MODCA-FM-021]

Table 4. UP3I Architecture Documentation
UP3I Publication Book Identification
Universal Printer Pre- and Post-Processing Interface (UP 3ITM) Specification Available at:
www.afpcinc.org
Table 5. International Organization for Standardization (ISO) Documentation
ISO Publication Book Identification
Document management – AFP/Archive ISO 18565:2015, available at:
www.iso.org

# Summary of Changes
This eleventh edition of the Mixed Object Document Content Architecture (MO:DCA) Reference contains the
following significant architecture extensions:
• Support for metadata objects has been expanded to allow metadata to be associated with additional AFP [MODCA-FM-022]
objects
• Support for IOCA nColor images and the FS48 function set that includes them [MODCA-FM-023]
• Support for IOCA function set FS14, an extension of FS11 [MODCA-FM-024]
• Support for QR Code with Image, which allows a variety of data objects to be put on top of AFP QR Code bar [MODCA-FM-025]
codes
• Support for additional Trim finishing [MODCA-FM-026]
• Support for setup names, which allow print files to specify the printer settings with which they should be [MODCA-FM-027]
printed
• Numerous corrections and clarifications [MODCA-FM-028]
As stated in the edition notice, the additions are marked in this publication in green, with green revision bars
located on the left-hand side of a page. [MODCA-FM-029]

# Contents
Preface . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . iii
Who Should Read This Book .....................................................................................................................iii
AFP Consortium......................................................................................................................................iii
How to Use This Book ............................................................................................................................. iv
How to Read the Syntax Diagrams.......................................................................................................... v
Structured Field Introducer................................................................................................................ v
Data............................................................................................................................................. v
Related Publications ...............................................................................................................................vii
AFP Architecture Publications ...............................................................................................................vii
Summary of Changes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ix
Figures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xxix
Tables . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xxxi
Chapter 1. A Presentation Architecture Perspective. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .1
The Presentation Environment ................................................................................................................... 1
Architecture Components.......................................................................................................................... 2
Data Streams ..................................................................................................................................... 2
Objects ............................................................................................................................................. 4
Chapter 2. Introduction to the MO:DCA Architecture . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .7
What is the Mixed Object Document Content Architecture? .............................................................................. 7
Organization of the Architecture.................................................................................................................. 9
Compliance with the Architecture .............................................................................................................. 10
MO:DCA Concepts ................................................................................................................................ 10
Print Files ........................................................................................................................................ 10
Documents ...................................................................................................................................... 10
Pages............................................................................................................................................. 10
Overlays ......................................................................................................................................... 11
Page Segments ................................................................................................................................ 11
Objects ........................................................................................................................................... 11
Data Objects................................................................................................................................ 11
Resource Objects ......................................................................................................................... 12
Secondary Resource Objects .......................................................................................................... 12
T ertiary Resource Objects
............................................................................................................... 13
Multi-page Resource Objects .......................................................................................................... 13
Resource Object Mapping .............................................................................................................. 14
Preloading and Preprocessing Resource Objects ................................................................................ 14
Object Containers ............................................................................................................................. 14
Environment Groups .......................................................................................................................... 15
Document Environment Groups ....................................................................................................... 15
Resource Environment Groups ........................................................................................................ 15
Active Environment Groups............................................................................................................. 15
Object Environment Groups ............................................................................................................ 16
Resource Groups .............................................................................................................................. 16
Page Groups.................................................................................................................................... 17
Print Control Objects.......................................................................................................................... 17
Process Elements ............................................................................................................................. 17
Chapter 3. MO:DCA Overview . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 19
MO:DCA Data Structures ........................................................................................................................ 19
Notation Conventions ............................................................................................................................. 19
MO:DCA Structured Field Syntax.............................................................................................................. 20
Structured Field Introducer .................................................................................................................. 20
SFI Syntax .................................................................................................................................. 20
SFI Semantics.............................................................................................................................. 21
Type Codes ................................................................................................................................. 22
Category Codes ........................................................................................................................... 23 [MODCA-FM-030]

Structured Field Data ......................................................................................................................... 24
Structured Field Introducer Extension .................................................................................................... 24
Structured Field Segmentation ............................................................................................................. 24
Structured Field Padding .................................................................................................................... 24
Structured Field Formats .................................................................................................................... 25
Data Stream Format .......................................................................................................................... 25
MO:DCA Data Stream States................................................................................................................... 25
Environment Hierarchies .................................................................................................................... 25
Processing Order .............................................................................................................................. 26
Resource Search Order...................................................................................................................... 26
Structured Field Parameters .................................................................................................................... 28
Mandatory and Optional Parameters ..................................................................................................... 28
Mandatory Parameters................................................................................................................... 28
Optional Parameters...................................................................................................................... 28
Parameter Categories ........................................................................................................................ 28
Fixed Parameters ......................................................................................................................... 28
Self-identifying Parameters ............................................................................................................. 28
Repeating Groups......................................................................................................................... 28
Parameter Values.............................................................................................................................. 29
Specified Values ........................................................................................................................... 29
Default Values.............................................................................................................................. 29
Hierarchical Defaults ................................................................................................................. 29
Architected Defaults .................................................................................................................. 29
Default Indicator ........................................................................................................................... 30
Parameter Occurrence ....................................................................................................................... 30
Single-Occurrence Parameters ........................................................................................................ 30
Multiple-Occurrence Parameters ...................................................................................................... 30
Parameter Types .............................................................................................................................. 30
Bit String ..................................................................................................................................... 31
Character String ........................................................................................................................... 31
Code.......................................................................................................................................... 31
Global Identifier ............................................................................................................................ 31
Local Identifier.............................................................................................................................. 31
Name ......................................................................................................................................... 32
Number ...................................................................................................................................... 32
Coordinate Systems............................................................................................................................... 34
Measurement and Rotation ..................................................................................................................... 35
Measurement ................................................................................................................................... 35
Measurement Units ....................................................................................................................... 35
Measurement Unit Formats............................................................................................................. 36
Extent ........................................................................................................................................ 36
Offset ......................................................................................................................................... 37
Rotation .......................................................................................................................................... 38
Rotation Units .............................................................................................................................. 40
Shape ........................................................................................................................................ 41
Presentation Space Mixing ...................................................................................................................... 41
Foreground and Background ............................................................................................................... 41
Merging Presentation Spaces .............................................................................................................. 43
Mixing Rules .................................................................................................................................... 44
Default Mixing Rule ........................................................................................................................... 45
Preprinted Form Overlay (PFO) Mixing .................................................................................................. 45
UP3i Print Data Mixing ....................................................................................................................... 46
Color Management ................................................................................................................................ 47
CMR names..................................................................................................................................... 47
CMR types ...................................................................................................................................... 47
Processing modes............................................................................................................................. 48
CMR Installation ............................................................................................................................... 49
CMRs and presentation devices ........................................................................................................... 50
Associating CMRs with document components........................................................................................ 50
Rendering intent ............................................................................................................................... 52
CMRs and print media........................................................................................................................ 53
CMR Processing............................................................................................................................... 53 [MODCA-FM-031]

CMR association and scope ............................................................................................................ 53
CMR processing mode................................................................................................................... 54
CMR hierarchy rules...................................................................................................................... 55
Generic CMR processing................................................................................................................ 55
Default CMRs .............................................................................................................................. 56
CMR exception processing ............................................................................................................. 56
CMRs in Print file level Resource Groups ........................................................................................... 56
Metadata Objects in AFP ........................................................................................................................ 56
Associating MOs with an AFP print file ................................................................................................... 57
MO association and scope .............................................................................................................. 57
MO Hierarchy Rules ...................................................................................................................... 57
Default MOs ................................................................................................................................ 57
Font T echnologies ................................................................................................................................. 57
Relationship Between FOCA Character Metrics and TrueType Character Metrics: Implementation Issues ............ 57
Horizontal Metrics ......................................................................................................................... 57
Vertical Metrics............................................................................................................................. 59
Simulating Vertical Metrics .............................................................................................................. 59
Document Indexing................................................................................................................................ 62
Index Elements................................................................................................................................. 62
T ag Logical Elements ......................................................................................................................... 64
Document Links .................................................................................................................................... 65
Link Logical Elements ........................................................................................................................ 66
Annotations and Appends ................................................................................................................... 67
N-up Presentation ................................................................................................................................. 67
Cut-sheet Emulation (CSE) Print Mode ...................................................................................................... 68
Simulation of Preprinted Forms ................................................................................................................ 69
Document Finishing ............................................................................................................................... 70
Exception Conditions ............................................................................................................................. 71
Classifications .................................................................................................................................. 71
Detection......................................................................................................................................... 72
Exception Action ............................................................................................................................... 73
Chapter 4. MO:DCA Objects . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 75
Object Syntax Structure .......................................................................................................................... 75
Print File.............................................................................................................................................. 75
Document ............................................................................................................................................ 77
Document Index.................................................................................................................................... 78
Resource Environment Group .................................................................................................................. 78
Page .................................................................................................................................................. 79
Page Group ......................................................................................................................................... 82
Resource Objects.................................................................................................................................. 83
Font Objects .................................................................................................................................... 83
Font Object Content Architecture (FOCA) Fonts .................................................................................. 83
TrueType/OpenType Fonts ............................................................................................................. 84
Overlay Objects ................................................................................................................................ 85
Page Segment Objects....................................................................................................................... 87
Resource Groups .................................................................................................................................. 87
External Resource Naming Conventions ................................................................................................ 89
Print Control Objects .............................................................................................................................. 90
Form Map........................................................................................................................................ 90
Document Environment Group......................................................................................................... 91
Medium Map .................................................................................................................................... 92
Invocation of Medium Maps............................................................................................................. 93
Data Objects ........................................................................................................................................ 97
Bar Code Objects.............................................................................................................................. 98
Mapping the Bar Code Presentation Space ...................................................................................... 100
Graphics Objects ............................................................................................................................ 101
Mapping the Graphics Presentation Space ....................................................................................... 103
Image Objects ................................................................................................................................ 107
Mapping the Image Presentation Space........................................................................................... 108
T ext Objects.................................................................................................................................... 111
Mapping the T ext Presentation Space (T ext Object with OEG) ...............................................................114
Object Containers ............................................................................................................................114 [MODCA-FM-032]

Mapping the Container Data Presentation Space ................................................................................117
Chapter 5. MO:DCA Structured Fields . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 119
General Information ..............................................................................................................................119
Begin Active Environment Group (BAG) ................................................................................................... 120
BAG (X'D3A8C9') Syntax.................................................................................................................. 120
BAG Semantics .............................................................................................................................. 120
BAG Exception Condition Summary .................................................................................................... 120
Begin Bar Code Object (BBC) ................................................................................................................ 121
BBC (X'D3A8EB') Syntax.................................................................................................................. 121
BBC Semantics .............................................................................................................................. 121
BBC Exception Condition Summary .................................................................................................... 122
Bar Code Data (BDA) ........................................................................................................................... 123
BDA (X'D3EEEB') Syntax ................................................................................................................. 123
BDA Semantics .............................................................................................................................. 123
Bar Code Data Descriptor (BDD) ............................................................................................................ 124
BDD (X'D3A6EB') Syntax.................................................................................................................. 124
BDD Semantics .............................................................................................................................. 124
Begin Document Environment Group (BDG) ............................................................................................. 125
BDG (X'D3A8C4') Syntax.................................................................................................................. 125
BDG Semantics .............................................................................................................................. 125
BDG Exception Condition Summary.................................................................................................... 125
Begin Document Index (BDI).................................................................................................................. 126
BDI (X'D3A8A7') Syntax ................................................................................................................... 126
BDI Semantics ............................................................................................................................... 126
BDI Exception Condition Summary ..................................................................................................... 127
Begin Document (BDT)......................................................................................................................... 128
BDT (X'D3A8A8') Syntax .................................................................................................................. 128
BDT Semantics .............................................................................................................................. 128
BDT Exception Condition Summary .................................................................................................... 130
Begin Form Map (BFM) ........................................................................................................................ 131
BFM (X'D3A8CD') Syntax ................................................................................................................. 131
BFM Semantics .............................................................................................................................. 131
BFM Exception Condition Summary .................................................................................................... 131
Begin Graphics Object (BGR) ................................................................................................................ 132
BGR (X'D3A8BB') Syntax ................................................................................................................. 132
BGR Semantics .............................................................................................................................. 132
BGR Exception Condition Summary.................................................................................................... 133
Begin Image Object (BIM) ..................................................................................................................... 134
BIM (X'D3A8FB') Syntax................................................................................................................... 134
BIM Semantics ............................................................................................................................... 134
BIM Exception Condition Summary ..................................................................................................... 135
Begin Medium Map (BMM) .................................................................................................................... 136
BMM (X'D3A8CC') Syntax................................................................................................................. 136
BMM Semantics ............................................................................................................................. 136
BMM Exception Condition Summary ................................................................................................... 137
Begin Overlay (BMO) ........................................................................................................................... 138
BMO (X'D3A8DF') Syntax ................................................................................................................. 138
BMO Semantics.............................................................................................................................. 138
BMO Exception Condition Summary ................................................................................................... 139
Begin Named Page Group (BNG) ........................................................................................................... 140
BNG (X'D3A8AD') Syntax ................................................................................................................. 140
BNG Semantics .............................................................................................................................. 140
Nesting Rules for Keep Group T ogether recovery units............................................................................ 142
BNG Exception Condition Summary.................................................................................................... 143
Begin Object Container (BOC) ............................................................................................................... 144
BOC (X'D3A892') Syntax .................................................................................................................. 144
BOC Semantics .............................................................................................................................. 144
BOC Exception Condition Summary.................................................................................................... 148
Begin Object Environment Group (BOG) .................................................................................................. 149
BOG (X'D3A8C7') Syntax ................................................................................................................. 149
BOG Semantics .............................................................................................................................. 149
BOG Exception Condition Summary.................................................................................................... 149 [MODCA-FM-033]

Begin Print File (BPF)........................................................................................................................... 150
BPF (X'D3A8A5') Syntax .................................................................................................................. 150
BPF Semantics............................................................................................................................... 150
BPF Exception Condition Summary .................................................................................................... 151
Begin Page (BPG) ............................................................................................................................... 152
BPG (X'D3A8AF') Syntax.................................................................................................................. 152
BPG Semantics .............................................................................................................................. 152
BPG Exception Condition Summary .................................................................................................... 154
Begin Page Segment (BPS)................................................................................................................... 155
BPS (X'D3A85F') Syntax .................................................................................................................. 155
BPS Semantics .............................................................................................................................. 155
BPS Exception Condition Summary .................................................................................................... 156
Begin Presentation T ext Object (BPT) ...................................................................................................... 157
BPT (X'D3A89B') Syntax .................................................................................................................. 157
BPT Semantics............................................................................................................................... 157
BPT Exception Condition Summary .................................................................................................... 158
Begin Resource Group (BRG) ................................................................................................................ 159
BRG (X'D3A8C6') Syntax.................................................................................................................. 159
BRG Semantics .............................................................................................................................. 159
BRG Exception Condition Summary.................................................................................................... 160
Begin Resource (BRS) ......................................................................................................................... 161
BRS (X'D3A8CE') Syntax.................................................................................................................. 161
BRS Semantics .............................................................................................................................. 161
Using the BRS to Envelop Inline TrueType/OpenType Resources .............................................................. 164
Using the BRS to Envelop Inline Color Management Resources................................................................ 165
BRS Exception Condition Summary .................................................................................................... 168
Begin Resource Environment Group (BSG) .............................................................................................. 169
BSG (X'D3A8D9') Syntax.................................................................................................................. 169
BSG Semantics .............................................................................................................................. 169
BSG Exception Condition Summary .................................................................................................... 169
Container Data Descriptor (CDD)............................................................................................................ 170
CDD (X'D3A692') Syntax .................................................................................................................. 170
CDD Semantics .............................................................................................................................. 170
End Active Environment Group (EAG) ..................................................................................................... 173
EAG (X'D3A9C9') Syntax.................................................................................................................. 173
EAG Semantics .............................................................................................................................. 173
EAG Exception Condition Summary .................................................................................................... 173
End Bar Code Object (EBC) .................................................................................................................. 174
EBC (X'D3A9EB') Syntax.................................................................................................................. 174
EBC Semantics .............................................................................................................................. 174
EBC Exception Condition Summary .................................................................................................... 174
End Document Environment Group (EDG)................................................................................................ 175
EDG (X'D3A9C4') Syntax.................................................................................................................. 175
EDG Semantics .............................................................................................................................. 175
EDG Exception Condition Summary.................................................................................................... 175
End Document Index (EDI) .................................................................................................................... 176
EDI (X'D3A9A7') Syntax ................................................................................................................... 176
EDI Semantics ............................................................................................................................... 176
EDI Exception Condition Summary ..................................................................................................... 176
End Document (EDT) ........................................................................................................................... 177
EDT (X'D3A9A8') Syntax .................................................................................................................. 177
EDT Semantics .............................................................................................................................. 177
EDT Exception Condition Summary .................................................................................................... 177
End Form Map (EFM)........................................................................................................................... 178
EFM (X'D3A9CD') Syntax ................................................................................................................. 178
EFM Semantics .............................................................................................................................. 178
EFM Exception Condition Summary .................................................................................................... 178
End Graphics Object (EGR)................................................................................................................... 179
EGR (X'D3A9BB') Syntax ................................................................................................................. 179
EGR Semantics .............................................................................................................................. 179
EGR Exception Condition Summary.................................................................................................... 179
End Image Object (EIM)........................................................................................................................ 180 [MODCA-FM-034]

EIM (X'D3A9FB') Syntax................................................................................................................... 180
EIM Semantics ............................................................................................................................... 180
EIM Exception Condition Summary ..................................................................................................... 180
End Medium Map (EMM) ...................................................................................................................... 181
EMM (X'D3A9CC') Syntax................................................................................................................. 181
EMM Semantics ............................................................................................................................. 181
EMM Exception Condition Summary ................................................................................................... 181
End Overlay (EMO) ............................................................................................................................. 182
EMO (X'D3A9DF') Syntax ................................................................................................................. 182
EMO Semantics.............................................................................................................................. 182
EMO Exception Condition Summary ................................................................................................... 182
End Named Page Group (ENG).............................................................................................................. 183
ENG (X'D3A9AD') Syntax ................................................................................................................. 183
ENG Semantics .............................................................................................................................. 183
ENG Exception Condition Summary.................................................................................................... 184
End Object Container (EOC).................................................................................................................. 185
EOC (X'D3A992') Syntax .................................................................................................................. 185
EOC Semantics .............................................................................................................................. 185
EOC Exception Condition Summary.................................................................................................... 185
End Object Environment Group (EOG)..................................................................................................... 186
EOG (X'D3A9C7') Syntax ................................................................................................................. 186
EOG Semantics .............................................................................................................................. 186
EOG Exception Condition Summary.................................................................................................... 186
End Print File (EPF) ............................................................................................................................. 187
EPF (X'D3A9A5') Syntax .................................................................................................................. 187
EPF Semantics............................................................................................................................... 187
EPF Exception Condition Summary .................................................................................................... 187
End Page (EPG) ................................................................................................................................. 188
EPG (X'D3A9AF') Syntax.................................................................................................................. 188
EPG Semantics .............................................................................................................................. 188
EPG Exception Condition Summary .................................................................................................... 188
End Page Segment (EPS) ..................................................................................................................... 189
EPS (X'D3A95F') Syntax .................................................................................................................. 189
EPS Semantics .............................................................................................................................. 189
EPS Exception Condition Summary .................................................................................................... 189
End Presentation T ext Object (EPT) ........................................................................................................ 190
EPT (X'D3A99B') Syntax .................................................................................................................. 190
EPT Semantics............................................................................................................................... 190
EPT Exception Condition Summary .................................................................................................... 190
End Resource Group (ERG) .................................................................................................................. 191
ERG (X'D3A9C6') Syntax.................................................................................................................. 191
ERG Semantics .............................................................................................................................. 191
ERG Exception Condition Summary.................................................................................................... 191
End Resource (ERS)............................................................................................................................ 192
ERS (X'D3A9CE') Syntax.................................................................................................................. 192
ERS Semantics .............................................................................................................................. 192
ERS Exception Condition Summary .................................................................................................... 192
End Resource Environment Group (ESG) ................................................................................................. 193
ESG (X'D3A9D9') Syntax.................................................................................................................. 193
ESG Semantics .............................................................................................................................. 193
ESG Exception Condition Summary .................................................................................................... 193
Graphics Data (GAD) ........................................................................................................................... 194
GAD (X'D3EEBB') Syntax ................................................................................................................. 194
GAD Semantics .............................................................................................................................. 194
Graphics Data Descriptor (GDD)............................................................................................................. 195
GDD (X'D3A6BB') Syntax ................................................................................................................. 195
GDD Semantics .............................................................................................................................. 195
Image Data Descriptor (IDD).................................................................................................................. 196
IDD (X'D3A6FB') Syntax ................................................................................................................... 196
IDD Semantics ............................................................................................................................... 196
Index Element (IEL) ............................................................................................................................. 197
IEL (X'D3B2A7') Syntax.................................................................................................................... 197
IEL Semantics ................................................................................................................................ 197 [MODCA-FM-035]

IEL Exception Condition Summary...................................................................................................... 198
Invoke Medium Map (IMM) .................................................................................................................... 199
IMM (X'D3ABCC') Syntax ................................................................................................................. 199
IMM Semantics............................................................................................................................... 199
Effect On Parameter Values .......................................................................................................... 199
Parameter Conflict Resolution ....................................................................................................... 200
Include Object (IOB) ............................................................................................................................ 201
IOB (X'D3AFC3') Syntax................................................................................................................... 201
IOB Semantics ............................................................................................................................... 203
IOB Exception Condition Summary ..................................................................................................... 217
Image Picture Data (IPD) ...................................................................................................................... 218
IPD (X'D3EEFB') Syntax................................................................................................................... 218
IPD Semantics ............................................................................................................................... 218
Include Page (IPG) .............................................................................................................................. 219
IPG (X'D3AFAF') Syntax ................................................................................................................... 219
IPG Semantics ............................................................................................................................... 219
Include Page Overlay (IPO) ................................................................................................................... 222
IPO (X'D3AFD8') Syntax................................................................................................................... 222
IPO Semantics ............................................................................................................................... 222
IPO Exception Condition Summary ..................................................................................................... 223
Include Page Segment (IPS).................................................................................................................. 224
IPS (X'D3AF5F') Syntax ................................................................................................................... 224
IPS Semantics................................................................................................................................ 224
IPS Exception Condition Summary ..................................................................................................... 225
Link Logical Element (LLE) .................................................................................................................... 226
LLE (X'D3B490') Syntax ................................................................................................................... 226
LLE Semantics ............................................................................................................................... 226
LLE Exception Condition Summary ..................................................................................................... 231
Map Bar Code Object (MBC) ................................................................................................................. 232
MBC (X'D3ABEB') Syntax ................................................................................................................. 232
MBC Semantics .............................................................................................................................. 232
MBC Exception Condition Summary.................................................................................................... 232
Medium Copy Count (MCC)................................................................................................................... 233
MCC (X'D3A288') Syntax.................................................................................................................. 233
MCC Semantics.............................................................................................................................. 233
MCC Exception Condition Summary ................................................................................................... 234
Map Container Data (MCD) ................................................................................................................... 235
MCD (X'D3AB92') Syntax ................................................................................................................. 235
MCD Semantics.............................................................................................................................. 235
MCD Exception Condition Summary ................................................................................................... 236
Map Coded Font (MCF) Format 2 ........................................................................................................... 237
MCF (X'D3AB8A') Syntax ................................................................................................................. 237
MCF Semantics .............................................................................................................................. 237
MCF Usage Information.................................................................................................................... 240
Double-byte Font References ............................................................................................................ 241
Using the X'50' Triplet to Specify Encoding ........................................................................................... 241
MCF Exception Condition Summary.................................................................................................... 242
Medium Descriptor (MDD) ..................................................................................................................... 244
MDD (X'D3A688') Syntax.................................................................................................................. 244
MDD Semantics.............................................................................................................................. 244
Map Data Resource (MDR) ................................................................................................................... 246
MDR (X'D3ABC3') Syntax ................................................................................................................. 246
MDR Semantics.............................................................................................................................. 246
Using the X'50' Triplet to Specify Encoding ........................................................................................... 254
Using the MDR to Map a TrueType/OpenType Font ................................................................................ 254
Font Name ................................................................................................................................ 254
Font Install Program .................................................................................................................... 255
TrueType/OpenType Font Resources in a Resource Library............................................................. 256
TrueType/OpenType Font Resources in an External (Print file level) Resource Group............................ 256
Using the MDR to Map a Color Management Resource (CMR) ................................................................. 259
CMR Name ............................................................................................................................... 259
Generic CMRs ........................................................................................................................... 259
Link CMRs ................................................................................................................................ 259 [MODCA-FM-036]

CMR Install Program ................................................................................................................... 259
CMRs in a Resource Library.......................................................................................................... 260
CMRs in an External (Print file level) Resource Group......................................................................... 261
Using the MDR to Map a Data Object Resource..................................................................................... 264
MDR Exception Condition Summary ................................................................................................... 264
Medium Finishing Control (MFC) ............................................................................................................ 265
MFC (X'D3A088') Syntax .................................................................................................................. 266
MFC Semantics .............................................................................................................................. 267
Finishing Operation Nesting Rules.................................................................................................. 270
MFC Exception Condition Summary................................................................................................ 272
Map Graphics Object (MGO).................................................................................................................. 273
MGO (X'D3ABBB') Syntax ................................................................................................................ 273
MGO Semantics ............................................................................................................................. 273
MGO Exception Condition Summary ................................................................................................... 273
Map Image Object (MIO)....................................................................................................................... 274
MIO (X'D3ABFB') Syntax .................................................................................................................. 274
MIO Semantics ............................................................................................................................... 274
MIO Exception Condition Summary..................................................................................................... 275
Medium Modification Control (MMC)........................................................................................................ 276
MMC (X'D3A788') Syntax ................................................................................................................. 276
MMC Semantics ............................................................................................................................. 277
MMC Exception Condition Summary ................................................................................................... 285
Map Media Destination (MMD) ............................................................................................................... 286
MMD (X'D3ABCD') Syntax ................................................................................................................ 286
MMD Semantics ............................................................................................................................. 286
MMD Exception Condition Summary ................................................................................................... 287
Map Medium Overlay (MMO) ................................................................................................................. 288
MMO (X'D3B1DF') Syntax................................................................................................................. 288
MMO Semantics ............................................................................................................................. 288
Map Media Type (MMT) ........................................................................................................................ 289
MMT (X'D3AB88') Syntax ................................................................................................................. 289
MMT Semantics.............................................................................................................................. 289
MMT Exception Condition Summary ................................................................................................... 291
Map Page (MPG) ................................................................................................................................ 292
MPG (X'D3ABAF') Syntax ................................................................................................................. 292
MPG Semantics.............................................................................................................................. 292
MPG Exception Condition Summary ................................................................................................... 293
Map Page Overlay (MPO) ..................................................................................................................... 294
MPO (X'D3ABD8') Syntax ................................................................................................................. 294
MPO Semantics.............................................................................................................................. 294
MPO Exception Condition Summary ................................................................................................... 295
Map Page Segment (MPS) .................................................................................................................... 296
MPS (X'D3B15F') Syntax .................................................................................................................. 296
MPS Semantics .............................................................................................................................. 296
Map Presentation T ext (MPT)................................................................................................................. 297
MPT (X'D3AB9B') Syntax.................................................................................................................. 297
MPT Semantics .............................................................................................................................. 297
MPT Exception Condition Summary .................................................................................................... 297
Map Suppression (MSU) ....................................................................................................................... 298
MSU (X'D3ABEA') Syntax ................................................................................................................. 298
MSU Semantics .............................................................................................................................. 298
No Operation (NOP) ............................................................................................................................ 299
NOP (X'D3EEEE') Syntax ................................................................................................................. 299
NOP Semantics .............................................................................................................................. 299
Object Area Descriptor (OBD) ................................................................................................................ 300
OBD (X'D3A66B') Syntax.................................................................................................................. 300
OBD Semantics .............................................................................................................................. 300
OBD Exception Condition Summary.................................................................................................... 301
Object Area Position (OBP) ................................................................................................................... 302
OBP (X'D3AC6B') Syntax ................................................................................................................. 302
OBP Semantics .............................................................................................................................. 303
OBP Exception Condition Summary .................................................................................................... 304 [MODCA-FM-037]

Object Container Data (OCD)................................................................................................................. 305
OCD (X'D3EE92') Syntax.................................................................................................................. 305
OCD Semantics .............................................................................................................................. 305
Presentation Environment Control (PEC).................................................................................................. 306
PEC (X'D3A7A8') Syntax .................................................................................................................. 306
PEC Semantics .............................................................................................................................. 306
Presentation Fidelity Control (PFC) ......................................................................................................... 308
PFC (X'D3B288') Syntax................................................................................................................... 308
PFC Semantics .............................................................................................................................. 308
Page Descriptor (PGD) ......................................................................................................................... 310
PGD (X'D3A6AF') Syntax.................................................................................................................. 310
PGD Semantics .............................................................................................................................. 310
PGD Exception Condition Summary.................................................................................................... 312
Page Position (PGP) Format 2 ............................................................................................................... 313
PGP (X'D3B1AF') Syntax.................................................................................................................. 313
PGP Semantics .............................................................................................................................. 314
PGP Exception Condition Summary .................................................................................................... 320
Partition Numbering for N-up ............................................................................................................. 320
Page Modification Control (PMC) ............................................................................................................ 327
PMC (X'D3A7AF') Syntax ................................................................................................................. 327
PMC Semantics .............................................................................................................................. 327
Preprocess Presentation Object (PPO) .................................................................................................... 329
PPO (X'D3ADC3') Syntax ................................................................................................................. 329
PPO Semantics .............................................................................................................................. 329
Processing Rules ............................................................................................................................ 337
Preprocessing overlays ................................................................................................................ 338
Preprocessing data objects ........................................................................................................... 338
Scale-to-fit or scale-to-fill .......................................................................................................... 338
Position, position-and-trim, or center-and-trim............................................................................... 338
Limitations................................................................................................................................. 338
PPO Exception Condition Summary .................................................................................................... 339
Presentation T ext Data Descriptor (PTD) Format 2 ..................................................................................... 340
PTD (X'D3B19B') Syntax .................................................................................................................. 340
PTD Semantics .............................................................................................................................. 340
Presentation T ext Data (PTX)................................................................................................................. 341
PTX (X'D3EE9B') Syntax .................................................................................................................. 341
PTX Semantics............................................................................................................................... 341
T ag Logical Element (TLE) .................................................................................................................... 342
TLE (X'D3A090') Syntax ................................................................................................................... 342
TLE Semantics ............................................................................................................................... 342
Chapter 6. MO:DCA Triplets . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 345
General Information ............................................................................................................................. 345
Triplet Format ..................................................................................................................................... 345
Triplet Syntax ................................................................................................................................. 347
Triplet Semantics ............................................................................................................................ 347
Coded Graphic Character Set Global Identifier Triplet X'01' .......................................................................... 348
Triplet X'01' Syntax: GCSGID/CPGID Form .......................................................................................... 348
Triplet X'01' Syntax: CCSID Form ....................................................................................................... 349
Triplet X'01' Semantics ..................................................................................................................... 349
Structured Fields Using Triplet X'01' .................................................................................................... 350
Fully Qualified Name Triplet X'02'............................................................................................................ 351
Triplet X'02' Syntax .......................................................................................................................... 351
Triplet X'02' Semantics ..................................................................................................................... 353
Constructing Object Identifiers (OIDs) ............................................................................................. 357
Global Resource Identifier (GRID) Definition ..................................................................................... 358
Structured Fields Using Triplet X'02' .................................................................................................... 359
Mapping Option Triplet X'04' .................................................................................................................. 360
Triplet X'04' Syntax .......................................................................................................................... 360
Triplet X'04' Semantics ..................................................................................................................... 360
Structured Fields Using Triplet X'04' .................................................................................................... 362
Object Classification Triplet X'10' ............................................................................................................ 363
Triplet X'10' Syntax .......................................................................................................................... 363 [MODCA-FM-038]

Triplet X'10' Semantics ..................................................................................................................... 363
Structured Fields Using Triplet X'10' .................................................................................................... 366
MO:DCA Interchange Set Triplet X'18' ..................................................................................................... 367
Triplet X'18' Syntax .......................................................................................................................... 367
Triplet X'18'Semantics...................................................................................................................... 367
Structured Fields Using Triplet X'18' .................................................................................................... 368
Font Descriptor Specification Triplet X'1F' ................................................................................................. 369
Triplet X'1F' Syntax.......................................................................................................................... 369
Triplet X'1F' Semantics ..................................................................................................................... 370
Structured Field Using Triplet X'1F' ..................................................................................................... 372
Font Coded Graphic Character Set Global Identifier Triplet X'20'.................................................................... 373
Triplet X'20' Syntax .......................................................................................................................... 373
Triplet X'20' Semantics ..................................................................................................................... 373
Structured Fields Using Triplet X'20' .................................................................................................... 373
Resource Object Type Triplet X'21'.......................................................................................................... 374
Triplet X'21' Syntax .......................................................................................................................... 374
Triplet X'21' Semantics ..................................................................................................................... 374
Structured Field Using Triplet (X'21') ................................................................................................... 375
Extended Resource Local Identifier Triplet X'22' ......................................................................................... 376
Triplet X'22' Syntax .......................................................................................................................... 376
Triplet X'22' Semantics ..................................................................................................................... 376
Structured Fields Using Triplet X'22' .................................................................................................... 377
Resource Local Identifier Triplet X'24' ...................................................................................................... 378
Triplet X'24' Syntax .......................................................................................................................... 378
Triplet X'24' Semantics ..................................................................................................................... 378
Structured Fields Using Triplet X'24' .................................................................................................... 378
Resource Section Number Triplet X'25' .................................................................................................... 379
Triplet X'25' Syntax .......................................................................................................................... 379
Triplet X'25' Semantics ..................................................................................................................... 379
Structured Field Using Triplet X'25' ..................................................................................................... 379
Character Rotation Triplet X'26' .............................................................................................................. 380
Triplet X'26' Syntax .......................................................................................................................... 380
Triplet X'26' Semantics ..................................................................................................................... 380
Structured Field Using Triplet X'26' ..................................................................................................... 380
Object Byte Offset Triplet X'2D'............................................................................................................... 381
Triplet X'2D' Syntax ......................................................................................................................... 381
Triplet X'2D' Semantics .................................................................................................................... 381
Structured Field Using Triplet X'2D' ..................................................................................................... 381
Attribute Value Triplet X'36' .................................................................................................................... 382
Triplet X'36' Syntax .......................................................................................................................... 382
Triplet X'36' Semantics ..................................................................................................................... 382
Structured Field Using Triplet X'36' ..................................................................................................... 382
Descriptor Position Triplet X'43' .............................................................................................................. 383
Triplet X'43' Syntax .......................................................................................................................... 383
Triplet X'43' Semantics ..................................................................................................................... 383
Structured Field Using Triplet X'43' ..................................................................................................... 383
Media Eject Control Triplet X'45' ............................................................................................................. 384
Triplet X'45' Syntax .......................................................................................................................... 384
Triplet X'45' Semantics ..................................................................................................................... 384
Structured Field Using Triplet X'45' ..................................................................................................... 387
Measurement Units Triplet X'4B' ............................................................................................................. 388
Triplet X'4B' Syntax ......................................................................................................................... 388
Triplet X'4B' Semantics..................................................................................................................... 388
Structured Fields Using Triplet X'4B'.................................................................................................... 388
Object Area Size Triplet X'4C' ................................................................................................................ 389
Triplet X'4C' Syntax ......................................................................................................................... 389
Triplet X'4C' Semantics .................................................................................................................... 389
Structured Fields Using Triplet X'4C' ................................................................................................... 389
Area Definition Triplet X'4D' ................................................................................................................... 390
Triplet X'4D' Syntax ......................................................................................................................... 390
Triplet X'4D' Semantics .................................................................................................................... 390
Structured Field Using Triplet X'4D' ..................................................................................................... 390 [MODCA-FM-039]

Color Specification Triplet X'4E' .............................................................................................................. 391
Triplet X'4E' Syntax ......................................................................................................................... 391
Triplet X'4E' Semantics..................................................................................................................... 391
Structured Fields Using Triplet X'4E'.................................................................................................... 394
Encoding Scheme ID Triplet X'50' ........................................................................................................... 395
Triplet X'50' Syntax .......................................................................................................................... 395
Triplet X'50' Semantics ..................................................................................................................... 395
Structured Fields Using Triplet X'50' .................................................................................................... 397
Medium Map Page Number Triplet X'56' ................................................................................................... 398
Triplet X'56' Syntax .......................................................................................................................... 398
Triplet X'56' Semantics ..................................................................................................................... 398
Structured Fields Using Triplet X'56' .................................................................................................... 398
Object Byte Extent Triplet X'57'............................................................................................................... 399
Triplet X'57' Syntax .......................................................................................................................... 399
Triplet X'57' Semantics ..................................................................................................................... 399
Structured Fields Using Triplet X'57' .................................................................................................... 399
Object Structured Field Offset Triplet X'58' ................................................................................................ 400
Triplet X'58' Syntax .......................................................................................................................... 400
Triplet X'58' Semantics ..................................................................................................................... 400
Structured Field Using Triplet X'58' ..................................................................................................... 400
Object Structured Field Extent Triplet X'59' ............................................................................................... 401
Triplet X'59' Syntax .......................................................................................................................... 401
Triplet X'59' Semantics ..................................................................................................................... 401
Structured Field Using Triplet X'59' ..................................................................................................... 401
Object Offset Triplet X'5A'...................................................................................................................... 402
Triplet X'5A' Syntax ......................................................................................................................... 402
Triplet X'5A' Semantics..................................................................................................................... 402
Structured Fields Using Triplet X'5A'.................................................................................................... 403
Font Horizontal Scale Factor Triplet X'5D' ................................................................................................. 404
Triplet X'5D' Syntax ......................................................................................................................... 404
Triplet X'5D' Semantics .................................................................................................................... 404
Structured Field Using Triplet X'5D' ..................................................................................................... 404
Object Count Triplet X'5E' ..................................................................................................................... 405
Triplet X'5E' Syntax ......................................................................................................................... 405
Triplet X'5E' Semantics..................................................................................................................... 405
Structured Fields Using Triplet X'5E'.................................................................................................... 405
Local Date and Time Stamp Triplet X'62'................................................................................................... 407
Triplet X'62' Syntax .......................................................................................................................... 407
Triplet X'62' Semantics ..................................................................................................................... 407
Structured Fields Using Triplet X'62' .................................................................................................... 408
Comment Triplet X'65' .......................................................................................................................... 409
Triplet X'65' Syntax .......................................................................................................................... 409
Triplet X'65' Semantics ..................................................................................................................... 409
Structured Fields Using Triplet X'65' .................................................................................................... 409
Medium Orientation Triplet X'68' ............................................................................................................. 410
Triplet X'68' Syntax .......................................................................................................................... 410
Triplet X'68' Semantics ..................................................................................................................... 410
Structured Field Using Triplet X'68' ......................................................................................................411
Resource Object Include Triplet X'6C' ...................................................................................................... 412
Triplet X'6C' Syntax ......................................................................................................................... 412
Triplet X'6C' Semantics .................................................................................................................... 412
Structured Field Using Triplet X'6C' ..................................................................................................... 413
Presentation Space Reset Mixing Triplet X'70' ........................................................................................... 414
Triplet X'70' Syntax .......................................................................................................................... 414
Triplet X'70' Semantics ..................................................................................................................... 414
Structured Fields Using Triplet X'70' .................................................................................................... 414
Presentation Space Mixing Rules Triplet X'71' ........................................................................................... 416
Triplet X'71' Syntax .......................................................................................................................... 416
Triplet X'71' Semantics ..................................................................................................................... 416
Structured Fields Using Triplet X'71' .................................................................................................... 417
Universal Date and Time Stamp Triplet X'72' ............................................................................................. 418
Triplet X'72' Syntax .......................................................................................................................... 418 [MODCA-FM-040]

Triplet X'72' Semantics ..................................................................................................................... 418
Structured Fields Using Triplet X'72' .................................................................................................... 419
T oner Saver Triplet X'74' ....................................................................................................................... 420
Triplet X'74' Syntax .......................................................................................................................... 420
Triplet X'74' Semantics ..................................................................................................................... 420
Structured Field Using Triplet X'74' ..................................................................................................... 420
Color Fidelity Triplet X'75' ...................................................................................................................... 421
Triplet X'75' Syntax .......................................................................................................................... 421
Triplet X'75' Semantics ..................................................................................................................... 422
Structured Field Using Triplet X'75' ..................................................................................................... 423
Font Fidelity Triplet X'78' ....................................................................................................................... 424
Triplet X'78' Syntax .......................................................................................................................... 424
Triplet X'78' Semantics ..................................................................................................................... 424
Structured Field Using Triplet X'78' ..................................................................................................... 424
Attribute Qualifier Triplet X'80' ................................................................................................................ 425
Triplet X'80' Syntax .......................................................................................................................... 425
Triplet X'80' Semantics ..................................................................................................................... 425
Structured Field Using Triplet X'80' ..................................................................................................... 425
Page Position Information Triplet X'81' ..................................................................................................... 426
Triplet X'81' Syntax .......................................................................................................................... 426
Triplet X'81' Semantics ..................................................................................................................... 426
Structured Fields Using Triplet X'81' .................................................................................................... 426
Parameter Value Triplet X'82' ................................................................................................................. 427
Triplet X'82' Syntax .......................................................................................................................... 427
Triplet X'82' Semantics ..................................................................................................................... 427
Structured Field Using Triplet X'82' ..................................................................................................... 427
Presentation Control Triplet X'83' ............................................................................................................ 428
Triplet X'83' Syntax .......................................................................................................................... 428
Triplet X'83' Semantics ..................................................................................................................... 428
Structured Fields Using Triplet X'83' .................................................................................................... 428
Font Resolution and Metric T echnology Triplet X'84'.................................................................................... 429
Triplet X'84' Syntax .......................................................................................................................... 429
Triplet X'84' Semantics ..................................................................................................................... 429
Structured Field Using Triplet X'84' ..................................................................................................... 429
Finishing Operation Triplet X'85' ............................................................................................................. 430
Triplet X'85' Syntax .......................................................................................................................... 430
Triplet X'85' Semantics ..................................................................................................................... 431
Structured Field Using Triplet X'85' ..................................................................................................... 441
T ext Fidelity Triplet X'86' ....................................................................................................................... 442
Triplet X'86' Syntax .......................................................................................................................... 442
Triplet X'86' Semantics ..................................................................................................................... 442
Structured Field Using Triplet X'86' ..................................................................................................... 443
Media Fidelity Triplet X'87' ..................................................................................................................... 444
Triplet X'87' Syntax .......................................................................................................................... 444
Triplet X'87' Semantics ..................................................................................................................... 444
Structured Field Using Triplet X'87' ..................................................................................................... 444
Finishing Fidelity Triplet X'88' ................................................................................................................. 445
Triplet X'88' Syntax .......................................................................................................................... 445
Triplet X'88' Semantics ..................................................................................................................... 445
Structured Field Using Triplet X'88' ..................................................................................................... 446
Data-Object Font Descriptor Triplet X'8B'.................................................................................................. 447
Triplet X'8B' Syntax ......................................................................................................................... 447
Triplet X'8B' Semantics..................................................................................................................... 447
Structured Field Using Triplet X'8B' ..................................................................................................... 450
Locale Selector Triplet X'8C' .................................................................................................................. 451
Triplet X'8C' Syntax ......................................................................................................................... 451
Triplet X'8C' Semantics .................................................................................................................... 452
Structured Field Using Triplet X'8C' ..................................................................................................... 453
UP3i Finishing Operation Triplet X'8E'...................................................................................................... 454
Triplet X'8E' Syntax ......................................................................................................................... 454
Triplet X'8E' Semantics..................................................................................................................... 454
Structured Field Using Triplet X'8E' ..................................................................................................... 454 [MODCA-FM-041]

MO:DCA Function Set Triplet X'8F'.......................................................................................................... 455
Triplet X'8F' Syntax.......................................................................................................................... 455
Triplet X'8F' Semantics ..................................................................................................................... 455
Structured Fields Using Triplet X'8F' .................................................................................................... 455
Color Management Resource Descriptor Triplet X'91' .................................................................................. 456
Triplet X'91' Syntax .......................................................................................................................... 456
Triplet X'91' Semantics ..................................................................................................................... 456
Structured Fields Using Triplet X'91' .................................................................................................... 457
Rendering Intent Triplet X'95' ................................................................................................................. 458
Triplet X'95' Syntax .......................................................................................................................... 458
Triplet X'95' Semantics ..................................................................................................................... 459
Structured Fields Using Triplet X'95' .................................................................................................... 460
CMR T ag Fidelity Triplet X'96'................................................................................................................. 461
Triplet X'96' Syntax .......................................................................................................................... 461
Triplet X'96' Semantics ..................................................................................................................... 461
Structured Field Using Triplet X'96' ..................................................................................................... 462
Device Appearance Triplet X'97' ............................................................................................................. 463
Triplet X'97' Syntax .......................................................................................................................... 463
Triplet X'97' Semantics ..................................................................................................................... 463
Structured Field Using Triplet X'97' ..................................................................................................... 463
Image Resolution Triplet X'9A'................................................................................................................ 464
Triplet X'9A' Syntax ......................................................................................................................... 464
Triplet X'9A' Semantics..................................................................................................................... 464
Structured Fields Using Triplet X'9A'.................................................................................................... 465
Object Container Presentation Space Size Triplet X'9C' ............................................................................... 466
Triplet X'9C' Syntax ......................................................................................................................... 466
Triplet X'9C' Semantics .................................................................................................................... 466
Structured Fields Using Triplet X'9C' ................................................................................................... 467
Keep Group T ogether Triplet X'9D' .......................................................................................................... 468
Triplet X'9D' Syntax ......................................................................................................................... 468
Triplet X'9D' Semantics .................................................................................................................... 468
Structured Field Using Triplet X'9D' ..................................................................................................... 468
Setup Name Triplet X'9E'
...................................................................................................................... 469
Triplet X'9E' Syntax ......................................................................................................................... 469
Triplet X'9E' Semantics ..................................................................................................................... 469
Structured Field Using Triplet X'9E' ..................................................................................................... 469
Triplet Extender Triplet X'FF' .................................................................................................................. 470
Triplet X'FF' Syntax ......................................................................................................................... 470
Triplet X'FF' Semantics..................................................................................................................... 470
Structured Fields Using Triplet X'FF'.................................................................................................... 470
Chapter 7. MO:DCA Interchange Sets . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 471
Interchange Sets ................................................................................................................................. 471
Interchange Set Compliance Requirements .............................................................................................. 472
MO:DCA Interchange Set 1 ................................................................................................................... 473
Data Stream Syntax Structure............................................................................................................ 473
Document...................................................................................................................................... 474
Page ............................................................................................................................................ 474
Active Environment Group (AEG) ....................................................................................................... 474
Graphics Object (GOCA DR/2V0) ....................................................................................................... 475
Object Environment Group (OEG) for Graphics Object ............................................................................ 475
Image Object (IOCA FS10) ............................................................................................................... 475
Object Environment Group (OEG) for Image Object................................................................................ 475
Presentation T ext Object (PTOCA PT1) ............................................................................................... 476
Resource Syntax Structure................................................................................................................ 476
Overlay ......................................................................................................................................... 476
Permitted Structured Fields ............................................................................................................... 476
Structured Field Parameters.......................................................................................................... 476
Begin Active Environment Group.................................................................................................... 477
Begin Document ......................................................................................................................... 477
Begin Graphics Object ................................................................................................................. 479
Begin Image Object..................................................................................................................... 479
Begin Object Environment Group ................................................................................................... 479 [MODCA-FM-042]

Begin Overlay ............................................................................................................................ 479
Begin Page................................................................................................................................ 479
Begin Presentation T ext Object ...................................................................................................... 479
End Active Environment Group ...................................................................................................... 479
End Document ........................................................................................................................... 480
End Graphics Object ................................................................................................................... 480
End Image Object ....................................................................................................................... 480
End Object Environment Group...................................................................................................... 480
End Overlay............................................................................................................................... 480
End Page .................................................................................................................................. 480
End Presentation T ext Object ........................................................................................................ 480
Graphics Data ............................................................................................................................ 480
Graphics Data Descriptor ............................................................................................................. 481
Image Data Descriptor ................................................................................................................. 481
Image Picture Data ..................................................................................................................... 481
Include Page Overlay .................................................................................................................. 481
Invoke Medium Map .................................................................................................................... 481
Map Coded Font, Format 2 ........................................................................................................... 481
MCF Font Names ................................................................................................................... 483
Map Graphics Object ................................................................................................................... 483
Map Image Object....................................................................................................................... 484
Map Page Overlay ...................................................................................................................... 484
No Operation ............................................................................................................................. 484
Object Area Descriptor................................................................................................................. 484
Object Area Position.................................................................................................................... 485
Page Descriptor.......................................................................................................................... 487
Presentation T ext Data................................................................................................................. 487
Presentation T ext Data Descriptor, Format 2 ..................................................................................... 487
MO:DCA Presentation Interchange Set 2 (IS/2).......................................................................................... 488
MO:DCA Interchange Set 3 (IS/3) ........................................................................................................... 489
1.0 Functional Subsets ..................................................................................................................... 489 [MODCA-FM-043]
2.0 Compliance .............................................................................................................................. 489 [MODCA-FM-044]
2.1 Migration Functions (as defined in Appendix C - MO:DCA Migration Functions) ................................... 490 [MODCA-FM-045]
2.2 Structured Field Introducer....................................................................................................... 490 [MODCA-FM-046]
2.3 Exception Conditions .............................................................................................................. 490 [MODCA-FM-047]
3.0 Data Stream Object Structure ....................................................................................................... 491 [MODCA-FM-048]
4.0 Print Control Object Structure ....................................................................................................... 500 [MODCA-FM-049]
5.0 Structured Fields and Triplets ....................................................................................................... 501 [MODCA-FM-050]
5.1 Begin Structured Fields ........................................................................................................... 501 [MODCA-FM-051]
5.2 End Structured Fields ............................................................................................................. 504 [MODCA-FM-052]
5.3 Structured Fields without Triplets............................................................................................... 505 [MODCA-FM-053]
5.4 Structured Fields with Triplets ................................................................................................... 507 [MODCA-FM-054]
6.0 Architected T ables ...................................................................................................................... 514 [MODCA-FM-055]
6.1 Standard OCA Color Value T able............................................................................................... 514 [MODCA-FM-056]
6.2 Color Mapping T able (CMT) ..................................................................................................... 514 [MODCA-FM-057]
6.3 Resource Access T ables (RAT s)................................................................................................ 514 [MODCA-FM-058]
TrueType/OpenType Font (TTF/OTF) Repeating Group .................................................................. 514
Color Management Resource (CMR) Repeating Group................................................................... 514
Data Object Resource (DOR) Repeating Group ............................................................................ 514
7.0 Migration Functions included in IS/3 ............................................................................................... 515 [MODCA-FM-059]
7.1 Obsolete Functions ................................................................................................................ 515 [MODCA-FM-060]
7.2 Retired Functions................................................................................................................... 515 [MODCA-FM-061]
7.3 Coexistence Functions............................................................................................................ 515 [MODCA-FM-062]
MO:DCA AFP Archive Interchange Set (AFP/A) ......................................................................................... 516
Chapter 8. MO:DCA Function Sets . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 517
Function Sets ..................................................................................................................................... 517
MO:DCA Function Set X'0001': MO:DCA GA (Graphic Arts) ..................................................................... 517
MO:DCA GA and IS/3 .................................................................................................................. 517
Appendix A. Color Resources . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 521
Standard OCA Color Value T able ............................................................................................................ 521
Converting Colors to Grayscale in MO:DCA Environments ........................................................................... 523 [MODCA-FM-063]

CIELab Color Space ........................................................................................................................ 523
RGB Color Space............................................................................................................................ 523
CMYK Color Space ......................................................................................................................... 523
Standard OCA Color Space (Named Colors)......................................................................................... 524
Highlight Color Space ...................................................................................................................... 524
The Color Mapping T able Resource......................................................................................................... 524
Color Mapping T able in MO:DCA Environments ..................................................................................... 524
Color Mapping T able Container ...................................................................................................... 524
Color Mapping T able in IPDS Environments .......................................................................................... 524
Color Mapping T able Definition........................................................................................................... 525
Color Mapping T able Syntax.......................................................................................................... 526
Color Mapping T able Semantics ..................................................................................................... 528
Color Mapping T able Exception Condition Summary........................................................................... 529
Appendix B. Resource Access Table (RAT) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 531
Font Interchange Information ................................................................................................................. 531
The Resource Access T able (RAT) .......................................................................................................... 531
Resource Access T able in MO:DCA Environments ................................................................................. 531
Resource Access T able in IPDS Environments ...................................................................................... 531
Resource Access T able Definition ........................................................................................................... 532
Resource Access T able Syntax .......................................................................................................... 533
Resource Access T able Semantics ..................................................................................................... 534
Resource Access T able Exception Condition Summary ........................................................................... 535
Repeating Group Definition for TrueType and OpenType Font Resources........................................................ 535
Repeating Group Flag Definitions for TrueType and OpenType Font Resources ........................................... 536
T able Vector Definitions for TrueType and OpenType Font Resources ........................................................ 537
Repeating Group Definition for Color Management Resources (CMRs) ........................................................... 540
Repeating Group Flag Definitions for Color Management Resources.......................................................... 540
T able Vector Definitions for Color Management Resources ...................................................................... 541
Repeating Group Definition for Data Object Resources................................................................................ 543
Repeating Group Flag Definitions for Data Object Resources ................................................................... 544
T able Vector Definitions for Data Object Resources ................................................................................ 545
Appendix C. MO:DCA Migration Functions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 553
Migration Functions ............................................................................................................................. 553
Obsolete Functions.............................................................................................................................. 554
Obsolete Structured Fields ................................................................................................................ 554
Composed T ext Control (CTC) ....................................................................................................... 554
CTC (X'D3A79B') Syntax.......................................................................................................... 554
CTC Semantics...................................................................................................................... 554
Begin Form Environment Group (BFG) ............................................................................................ 554
BFG (X'D3A8C5') Syntax ......................................................................................................... 554
BFG Semantics...................................................................................................................... 555
End Form Environment Group (EFG) .............................................................................................. 555
EFG (X'D3A9C5') Syntax ......................................................................................................... 555
EFG Semantics...................................................................................................................... 555
Form Environment Group Descriptor (FGD)...................................................................................... 555
FGD (X'D3A6C5') Syntax ......................................................................................................... 555
FGD Semantics...................................................................................................................... 555
Obsolete Structured Field Names ....................................................................................................... 556
Composed T ext Data (CTX) Structured Field (X'D3EE9B') ................................................................... 556
Composed T ext Descriptor (CTD) Structured Field (X'D3A69B') ............................................................ 556
Begin Composed T ext (BCT) Structured Field (X'D3A89B') .................................................................. 556
End Composed T ext (ECT) Structured Field (X'D3A99B') .................................................................... 556
Retired Functions ................................................................................................................................ 557
Retired Structured Fields .................................................................................................................. 557
Retired Triplets ............................................................................................................................... 557
MDD Two-up Triplet X'10' ............................................................................................................. 557
MDD Two-up Triplet X'10' Syntax ............................................................................................... 557
MDD Two-up Triplet X'10' Semantics .......................................................................................... 558
Structured Field Using MDD Two-up Triplet X'10' ........................................................................... 558
T ext Orientation Triplet X'1D' ......................................................................................................... 558
Triplet X'1D' Syntax................................................................................................................. 558 [MODCA-FM-064]

Triplet X'1D' Semantics ............................................................................................................ 559
Structured Field Using Triplet X'1D' ............................................................................................ 559
Object Function Set Specification Triplet X'21' ................................................................................... 559
Triplet X'21' Syntax ................................................................................................................. 560
Triplet X'21' Semantics ............................................................................................................ 560
Structured Field Using Triplet X'21' ............................................................................................. 561
Line Data Object Position Migration Triplet X'27' ................................................................................ 561
Triplet X'27' Syntax ................................................................................................................. 561
Triplet X'27' Semantics ............................................................................................................ 561
Structured Fields Using Triplet X'27' ........................................................................................... 564
Page Overlay Conditional Processing Triplet X'46' ............................................................................. 564
Triplet X'46' Syntax ................................................................................................................. 565
Triplet X'46' Semantics ............................................................................................................ 565
Structured Fields Using Triplet X'46' ........................................................................................... 566
Resource Usage Attribute Triplet X'47'............................................................................................. 566
Triplet X'47' Syntax ................................................................................................................. 566
Triplet X'47' Semantics ............................................................................................................ 566
Structured Fields Using Triplet X'47' ........................................................................................... 567
Object Checksum Triplet X'63' ....................................................................................................... 567
Triplet X'63' Syntax ................................................................................................................. 567
Triplet X'63' Semantics ............................................................................................................ 567
Structured Fields Using Triplet X'63' ........................................................................................... 568
Object Origin Identifier Triplet X'64' ................................................................................................. 568
Triplet X'64' Syntax ................................................................................................................. 568
Triplet X'64' Semantics ............................................................................................................ 569
Structured Fields Using Triplet X'64' ........................................................................................... 569
IMM Insertion Triplet X'73'............................................................................................................. 569
Triplet X'73' Syntax ................................................................................................................. 570
Triplet X'73' Semantics ............................................................................................................ 570
Structured Field Using Triplet X'73' ............................................................................................. 570
Retired Parameters ......................................................................................................................... 570
MMC Keyword X'0Enn' ................................................................................................................ 570
MMC Keyword X'F1nn'................................................................................................................. 571
MMO Flag Byte Bit 0.................................................................................................................... 571
Triplet X'62' StampType X'01' ........................................................................................................ 571
OBP RefCSys (Byte 23) = X'05' ..................................................................................................... 571
IPO value of X'FFFFFF' for XolOset, YolOset .................................................................................... 572
IPS value of X'FFFFFF' for XpsOset, YpsOset................................................................................... 572
CDD Bytes 0–11 ......................................................................................................................... 572
GRID Font Width value of X'FFFF' .................................................................................................. 573
MGO Mapping Option X'50': Replicate-and-Trim................................................................................ 573
IOB RefCSys = X'00' ................................................................................................................... 573
Triplet X'22' ResType = X'30' ......................................................................................................... 573
MFC MFCScpe = X'06'– Printjob MFC ............................................................................................. 573
Triplet X'18' ISid = X'0C00' ............................................................................................................ 574
Retired Interchange Set ........................................................................................................................ 575
MO:DCA Interchange Set 2 ............................................................................................................... 575
Data Stream Syntax Structure ....................................................................................................... 575
Document ................................................................................................................................. 576
Document Index ......................................................................................................................... 576
Resource Group ......................................................................................................................... 576
Page ........................................................................................................................................ 576
Overlay..................................................................................................................................... 577
Active Environment Group ............................................................................................................ 577
Bar Code Object (BCOCA BCD1)................................................................................................... 578
Object Environment Group (OEG) for Bar Code Object ....................................................................... 578
Graphics Object (GOCA DR/2V0)................................................................................................... 578
Object Environment Group (OEG) for Graphics Object........................................................................ 578
Image Object (IOCA FS10 or FS11) ................................................................................................ 579
Object Environment Group (OEG) for Image Object ........................................................................... 579
Presentation T ext Object (PTOCA PT1) ........................................................................................... 579
Permitted Structured Fields........................................................................................................... 579
Structured Field Parameters ..................................................................................................... 580 [MODCA-FM-065]

Bar Code Data ....................................................................................................................... 580
Bar Code Data Descriptor......................................................................................................... 581
Begin Active Environment Group ............................................................................................... 581
Begin Bar Code Object ............................................................................................................ 581
Begin Document Index ............................................................................................................ 581
Begin Document..................................................................................................................... 581
Begin Graphics Object ............................................................................................................. 582
Begin Image Object ................................................................................................................ 582
Begin Object Environment Group ............................................................................................... 583
Begin Overlay ........................................................................................................................ 583
Begin Page ........................................................................................................................... 583
Begin Presentation T ext Object.................................................................................................. 583
Begin Resource Group ............................................................................................................ 583
End Active Environment Group.................................................................................................. 583
End Bar Code Object............................................................................................................... 583
End Document Index ............................................................................................................... 583
End Document ....................................................................................................................... 584
End Graphics Object ............................................................................................................... 584
End Image Object ................................................................................................................... 584
End Object Environment Group ................................................................................................. 584
End Overlay .......................................................................................................................... 584
End Page.............................................................................................................................. 584
End Presentation T ext Object .................................................................................................... 584
End Resource Group............................................................................................................... 584
Graphics Data........................................................................................................................ 585
Graphics Data Descriptor ......................................................................................................... 585
Image Data Descriptor ............................................................................................................. 585
Image Picture Data ................................................................................................................. 585
Include Page Overlay .............................................................................................................. 585
Index Element........................................................................................................................ 586
Invoke Medium Map ................................................................................................................ 586
Map Bar Code Object .............................................................................................................. 586
Map Coded Font, Format 2 ....................................................................................................... 587
Map Graphics Object............................................................................................................... 588
Map Image Object .................................................................................................................. 589
Map Page Overlay .................................................................................................................. 589
No Operation ......................................................................................................................... 590
Object Area Descriptor............................................................................................................. 590
Object Area Position ............................................................................................................... 591
Page Descriptor ..................................................................................................................... 592
Presentation T ext Data ............................................................................................................ 593
Presentation T ext Data Descriptor, Format 2................................................................................. 593
Coexistence Functions ......................................................................................................................... 594
Coexistence Objects........................................................................................................................ 594
AFP Page Segment..................................................................................................................... 594
Positioning of IM Image Objects in an AFP Page Segment .............................................................. 594
Orientation of Objects in an AFP Page Segment............................................................................ 594
Positioning of IO Image and Graphics Objects in an AFP Page Segment ............................................ 594
Font Mapping for Graphics Objects in an AFP Page Segment .......................................................... 595
T ext Objects in an AFP Page Segment ........................................................................................ 595
IM Image Object ......................................................................................................................... 595
IM Image Object Structure ........................................................................................................ 596
IM Image Structured Fields ....................................................................................................... 597
Coexistence Structured Fields ........................................................................................................... 597
Map Coded Font (MCF-1) Format 1 ................................................................................................ 598
MCF-1 (X'D3B18A') Syntax....................................................................................................... 598
MCF-1 Semantics................................................................................................................... 598
Page Position (PGP-1) Format 1 .................................................................................................... 600
PGP-1 (X'D3ACAF') Syntax ...................................................................................................... 600
PGP-1 Semantics ................................................................................................................... 600
Presentation T ext Data Descriptor (PTD-1) Format 1 .......................................................................... 600
PTD-1 (X'D3A69B') Syntax ....................................................................................................... 600
PTD-1 Semantics ................................................................................................................... 601 [MODCA-FM-066]

Begin IM Image Object (BII) .......................................................................................................... 601
BII (X'D3A87B') Syntax ............................................................................................................ 601
BII Semantics ........................................................................................................................ 601
End IM Image Object (EII)............................................................................................................. 602
EII (X'D3A97B') Syntax ............................................................................................................ 602
EII Semantics ........................................................................................................................ 602
IM Image Cell Position (ICP) ......................................................................................................... 602
ICP (X'D3AC7B') Syntax .......................................................................................................... 602
ICP Semantics ....................................................................................................................... 603
IM Image Input Descriptor (IID) ...................................................................................................... 603
IID (X'D3A67B') Syntax ............................................................................................................ 603
IID Semantics ........................................................................................................................ 604
IM Image Output Control (IOC) ...................................................................................................... 605
IOC (X'D3A77B') Syntax .......................................................................................................... 605
IOC Semantics....................................................................................................................... 605
IM Image Raster Data (IRD) .......................................................................................................... 607
IRD (X'D3EE7B') Syntax .......................................................................................................... 607
IRD Semantics....................................................................................................................... 607
Coexistence Triplets ........................................................................................................................ 607
Coexistence Parameters .................................................................................................................. 607
Triplet X'04' Mapping Option X'41': Image Point-to-Pel ........................................................................ 608
Triplet X'04' Mapping Option X'42': Image Point-to-Pel with Double Dot .................................................. 608
Triplet X'04' Mapping Option X'50': Replicate and Trim ........................................................................ 608
Appendix D. MO:DCA Registry . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 609
Object Type Identifiers .......................................................................................................................... 609
Registered Encoded Object-type OIDs ................................................................................................ 610
Object Type Summary...................................................................................................................... 624
Non-OCA Object Types Supported by the IOB Structured Field ................................................................. 626
Data Objects and Supported Secondary Resources ............................................................................... 626
Media Type Identifiers .......................................................................................................................... 628
Media Type Summary ...................................................................................................................... 629
Resident Color Profile Identifiers............................................................................................................. 634
Resident Color Profile Summary......................................................................................................... 635
Appendix E. Cross-References . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 637
MO:DCA Structured Fields Sorted by Identifier .......................................................................................... 637
MO:DCA Structured Fields Sorted by Acronym .......................................................................................... 641
MO:DCA Triplets Sorted by Identifier ....................................................................................................... 644
MO:DCA Triplets Sorted by Name ........................................................................................................... 647
Appendix F . Object OID Algorithms. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 651
TrueType and OpenType Font Object OID Generation Algorithm ................................................................... 651
Color Management Resource and Data Object OID Generation Algorithm ....................................................... 652
Notices . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 655
Trademarks........................................................................................................................................ 656
Glossary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 657
Index . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 699 [MODCA-FM-067]

# Figures
1. Presentation Environment ........................................................................................................................ 1 [MODCA-FM-068]
2. Presentation Model ................................................................................................................................ 3 [MODCA-FM-069]
3. Presentation Page.................................................................................................................................. 5 [MODCA-FM-070]
4. MO:DCA Presentation Document Components ............................................................................................ 8 [MODCA-FM-071]
5. A MO:DCA Presentation Space Coordinate System .................................................................................... 35 [MODCA-FM-072]
6. Presentation Space Extents ................................................................................................................... 37 [MODCA-FM-073]
7. Offset of a Coordinate System ................................................................................................................ 38 [MODCA-FM-074]
8. Examples of Coordinate System Orientation .............................................................................................. 39 [MODCA-FM-075]
9. Inheritance of Coordinate System Orientation............................................................................................. 39 [MODCA-FM-076]
10. Rotation of the X and Y Axes ................................................................................................................. 40 [MODCA-FM-077]
11. Rotation Units for the Data Object Area — Arbitrary Orientation .................................................................... 41 [MODCA-FM-078]
12. A Page Overlay Applied to a Page in Two Different Orientations ................................................................... 41 [MODCA-FM-079]
13. Merging Presentation Spaces ............................................................................................................... 44 [MODCA-FM-080]
14. Horizontal Metrics: TrueType/OpenType Fonts and FOCA Fonts .................................................................. 58 [MODCA-FM-081]
15. Vertical Metrics: TrueType/OpenType Fonts and FOCA Fonts ...................................................................... 60 [MODCA-FM-082]
16. Page level IEL: Offset and Extent ........................................................................................................... 63 [MODCA-FM-083]
17. Page group level IEL: Offset and Extent .................................................................................................. 63 [MODCA-FM-084]
18. Page level IEL: Use of Medium Map Information ....................................................................................... 64 [MODCA-FM-085]
19. A Document with Logical T ags............................................................................................................... 65 [MODCA-FM-086]
20. Document Annotation using the LLE ....................................................................................................... 67 [MODCA-FM-087]
21. N-up Partitions for Various Physical Media ............................................................................................... 68 [MODCA-FM-088]
22. Logical Division of Continuous Forms for Cut-sheet Emulation ..................................................................... 69 [MODCA-FM-089]
23. Print File Structure .............................................................................................................................. 76 [MODCA-FM-090]
24. Document Structure ............................................................................................................................ 77 [MODCA-FM-091]
25. Document Index Structure .................................................................................................................... 78 [MODCA-FM-092]
26. Resource Environment Group Structure .................................................................................................. 78 [MODCA-FM-093]
27. Page Structure................................................................................................................................... 79 [MODCA-FM-094]
28. Page Group Structure.......................................................................................................................... 82 [MODCA-FM-095]
29. Overlay Structure ............................................................................................................................... 85 [MODCA-FM-096]
30. Page Segment Structure ...................................................................................................................... 87 [MODCA-FM-097]
31. External (Print file level) Resource Group Structure.................................................................................... 88 [MODCA-FM-098]
32. BRS/ERS Envelope for Resources in External (Print File Level) Resource Group ............................................. 89 [MODCA-FM-099]
33. Form Map Structure ............................................................................................................................ 91 [MODCA-FM-100]
34. Document Environment Group Structure ................................................................................................. 92 [MODCA-FM-101]
35. Copy Subgroups ................................................................................................................................ 93 [MODCA-FM-102]
36. Medium Map Structure ........................................................................................................................ 95 [MODCA-FM-103]
37. Object Area Positioning on a Page ......................................................................................................... 97 [MODCA-FM-104]
38. Bar Code Object Structure ................................................................................................................... 98 [MODCA-FM-105]
39. Bar Code Presentation Space Mapping: Position....................................................................................... 99 [MODCA-FM-106]
40. Graphics Object Structure .................................................................................................................. 101 [MODCA-FM-107]
41. Graphics Presentation Space Mapping: Scale to Fit ................................................................................. 103 [MODCA-FM-108]
42. Graphics Presentation Space Mapping: Scale to Fill................................................................................. 104 [MODCA-FM-109]
43. Graphics Presentation Space Mapping: Center and Trim .......................................................................... 105 [MODCA-FM-110]
44. Graphics Presentation Space Mapping: Position and Trim ......................................................................... 106 [MODCA-FM-111]
45. Image Object Structure ...................................................................................................................... 107 [MODCA-FM-112]
46. Image Presentation Space Mapping: Scale to Fit..................................................................................... 108 [MODCA-FM-113]
47. Image Presentation Space Mapping: Scale to Fill .................................................................................... 109 [MODCA-FM-114]
48. Image Presentation Space Mapping: Center and Trim ...............................................................................110 [MODCA-FM-115]
49. Image Presentation Space Mapping: Position and Trim ............................................................................. 111 [MODCA-FM-116]
50. Presentation T ext Object Structure - Without OEG .................................................................................... 111 [MODCA-FM-117]
51. Presentation T ext Object Structure - With OEG ........................................................................................112 [MODCA-FM-118]
52. T ext Presentation Space Mapping: Position.............................................................................................114 [MODCA-FM-119]
53. Use of the IOB to Include Object Container Data ......................................................................................115 [MODCA-FM-120]
54. Object Container Structure for Presentation Objects .................................................................................116 [MODCA-FM-121]
55. Object Container Structure for Non-Presentation Objects ...........................................................................117 [MODCA-FM-122]
56. Triplets in Link Attribute, Source, and T arget Repeating Groups.................................................................. 228 [MODCA-FM-123]
57. Example of a Full Font Name in Two Languages ..................................................................................... 255 [MODCA-FM-124]
58. Examples of MDR Repeating Groups.................................................................................................... 258 [MODCA-FM-125]
59. Examples of MDR Repeating Groups.................................................................................................... 263 [MODCA-FM-126]

60. Normal Duplex and Tumble Duplex Printing ........................................................................................... 283 [MODCA-FM-127]
61. 1-up Partition Numbering, Front Sheet-Side ........................................................................................... 320 [MODCA-FM-128]
62. 2-up Partition Numbering, Front Sheet-Side ........................................................................................... 321 [MODCA-FM-129]
63. 3-up Partition Numbering, Front Sheet-Side ........................................................................................... 321 [MODCA-FM-130]
64. 4-up Partition Numbering, Front Sheet-Side ........................................................................................... 322 [MODCA-FM-131]
65. 1-up Partition Numbering, Back Sheet-Side, Normal Duplex ...................................................................... 322 [MODCA-FM-132]
66. 2-up Partition Numbering, Back Sheet-Side, Normal Duplex ...................................................................... 323 [MODCA-FM-133]
67. 3-up Partition Numbering, Back Sheet-Side, Normal Duplex ...................................................................... 323 [MODCA-FM-134]
68. 4-up Partition Numbering, Back Sheet-Side, Normal Duplex ...................................................................... 324 [MODCA-FM-135]
69. 1-up Partition Numbering, Back Sheet-Side, Tumble Duplex ...................................................................... 324 [MODCA-FM-136]
70. 2-up Partition Numbering, Back Sheet-Side, Tumble Duplex ...................................................................... 325 [MODCA-FM-137]
71. 3-up Partition Numbering, Back Sheet-Side, Tumble Duplex ...................................................................... 325 [MODCA-FM-138]
72. 4-up Partition Numbering, Back Sheet-Side, Tumble Duplex ...................................................................... 326 [MODCA-FM-139]
73. Landscape and Portrait Orientation and Layout ........................................................................................411 [MODCA-FM-140]
74. Examples of Finishing Operations ........................................................................................................ 437 [MODCA-FM-141]
75. Examples of Additional Finishing Operations .......................................................................................... 438 [MODCA-FM-142]
76. More Examples of Additional Finishing Operations .................................................................................. 439 [MODCA-FM-143]
77. Media Reference Edge and Corner Definitions........................................................................................ 440 [MODCA-FM-144]
78. Character Placement Based on Character Rotation and Inline and Baseline Direction ..................................... 449 [MODCA-FM-145]
79. MO:DCA IS/1: Document Structure ...................................................................................................... 474 [MODCA-FM-146]
80. MO:DCA IS/1: Page Structure ............................................................................................................. 474 [MODCA-FM-147]
81. MO:DCA IS/1: Active Environment Group Structure ................................................................................. 474 [MODCA-FM-148]
82. MO:DCA IS/1: Graphics Object Structure............................................................................................... 475 [MODCA-FM-149]
83. MO:DCA IS/1: Object Environment Group for Graphics Object Structure ...................................................... 475 [MODCA-FM-150]
84. MO:DCA IS/1: Image Object Structure .................................................................................................. 475 [MODCA-FM-151]
85. MO:DCA IS/1: Object Environment Group for Image Object Structure .......................................................... 475 [MODCA-FM-152]
86. MO:DCA IS/1: Presentation T ext Object Structure ................................................................................... 476 [MODCA-FM-153]
87. MO:DCA IS/1: Overlay Structure.......................................................................................................... 476 [MODCA-FM-154]
88. Color Mapping T able Container ........................................................................................................... 524 [MODCA-FM-155]
89. MO:DCA IS/2: Document Structure ...................................................................................................... 576 [MODCA-FM-156]
90. MO:DCA IS/2: Document Index Structure .............................................................................................. 576 [MODCA-FM-157]
91. MO:DCA IS/2: Resource Group Structure .............................................................................................. 576 [MODCA-FM-158]
92. MO:DCA IS/2: Page Structure ............................................................................................................. 576 [MODCA-FM-159]
93. MO:DCA IS/2: Overlay Structure.......................................................................................................... 577 [MODCA-FM-160]
94. MO:DCA IS/2: Active Environment Group Structure ................................................................................. 577 [MODCA-FM-161]
95. MO:DCA IS/2: Bar Code Object Structure .............................................................................................. 578 [MODCA-FM-162]
96. MO:DCA IS/2: Object Environment Group for Bar Code Object Structure...................................................... 578 [MODCA-FM-163]
97. MO:DCA IS/2: Graphics Object Structure............................................................................................... 578 [MODCA-FM-164]
98. MO:DCA IS/2: Object Environment Group for Graphics Object Structure ...................................................... 578 [MODCA-FM-165]
99. MO:DCA IS/2: Image Object Structure .................................................................................................. 579 [MODCA-FM-166]
100. MO:DCA IS/2: Object Environment Group for Image Object Structure ........................................................ 579 [MODCA-FM-167]
101. MO:DCA IS/2: Presentation T ext Object Structure.................................................................................. 579 [MODCA-FM-168]
102. AFP Page Segment Structure............................................................................................................ 594 [MODCA-FM-169]
103. Two Forms of IM Image.................................................................................................................... 596 [MODCA-FM-170]
104. IM Image Object Structure: Simple (Non-celled) Image ........................................................................... 596 [MODCA-FM-171]
105. IM Image Object Structure: Complex (Celled) Image .............................................................................. 597 [MODCA-FM-172]

# Tables
1. AFPC Architecture Documentation............................................................................................................vii [MODCA-FM-173]
2. Additional AFP Consortium Documentation ................................................................................................vii [MODCA-FM-174]
3. AFP Font-Related Documentation ............................................................................................................vii [MODCA-FM-175]
4. UP 3I Architecture Documentation ............................................................................................................ viii [MODCA-FM-176]
5. International Organization for Standardization (ISO) Documentation ............................................................... viii [MODCA-FM-177]
6. Structured Field Introducer (SFI) ............................................................................................................. 20 [MODCA-FM-178]
7. Type Codes ........................................................................................................................................ 22 [MODCA-FM-179]
8. Maximum Absolute Values of Numbers in the MO:DCA Architecture ............................................................... 33 [MODCA-FM-180]
9. MO:DCA Coordinate Systems................................................................................................................. 34 [MODCA-FM-181]
10. Format for Numbers Expressed in Rotation Units ...................................................................................... 40 [MODCA-FM-182]
11. Foreground/Background in Data Object Presentation Spaces....................................................................... 42 [MODCA-FM-183]
12. Default Color Mixing Rules ................................................................................................................... 45 [MODCA-FM-184]
13. Color Mixing Rules for PFOs ................................................................................................................. 46 [MODCA-FM-185]
14. CMR Type: Processing Mode and Generic Capability ................................................................................. 49 [MODCA-FM-186]
15. Bit Representation of MO:DCA Exception Condition Categories ................................................................... 73 [MODCA-FM-187]
16. Default BPT Page-Level Initial T ext Conditions........................................................................................ 158 [MODCA-FM-188]
17. Print Server CMR Processing: Inline CMRs ............................................................................................ 166 [MODCA-FM-189]
18. Link Sources and Link T argets............................................................................................................. 228 [MODCA-FM-190]
19. Valid ESidCP/ESidUD Combinations for the MCF .................................................................................... 241 [MODCA-FM-191]
20. Valid ESidUD/ESidCP Combinations for the MDR.................................................................................... 254 [MODCA-FM-192]
21. Print Server CMR Processing: CMRs in Resource Libraries ....................................................................... 260 [MODCA-FM-193]
22. Print Server CMR Processing: Inline CMRs ............................................................................................ 262 [MODCA-FM-194]
23. Sheet Jogging and Conditional Ejects ................................................................................................... 280 [MODCA-FM-195]
24. Supported ESidCP Values.................................................................................................................. 397 [MODCA-FM-196]
25. Supported ESidUD Values.................................................................................................................. 397 [MODCA-FM-197]
26. Additional ESidUD Values in AFP Line Data ........................................................................................... 397 [MODCA-FM-198]
27. IS/3 Objects .................................................................................................................................... 491 [MODCA-FM-199]
28. IS/3 Containers - Presentation Objects.................................................................................................. 498 [MODCA-FM-200]
29. IS/3 Containers - Non-Presentation Objects ........................................................................................... 498 [MODCA-FM-201]
30. IS/3 IOB and DOR RAT Presentation Object Containers ........................................................................... 499 [MODCA-FM-202]
31. IS/3 Data Objects and Secondary Resources ......................................................................................... 499 [MODCA-FM-203]
32. IS/3 Print Control Objects ................................................................................................................... 500 [MODCA-FM-204]
33. IS/3 Begin Structured Fields ............................................................................................................... 501 [MODCA-FM-205]
34. IS/3 End Structured Fields .................................................................................................................. 504 [MODCA-FM-206]
35. IS/3 Structured Fields without Triplets ................................................................................................... 505 [MODCA-FM-207]
36. IS/3 Structured Fields with Triplets ....................................................................................................... 507 [MODCA-FM-208]
37. Presentation Objects Processed with Data Object Resource (DOR) RAT...................................................... 515 [MODCA-FM-209]
38. IS/3 + MO:DCA GA Containers - Presentation Objects ............................................................................. 517 [MODCA-FM-210]
39. IS/3 + MO:DCA GA IOB and DOR RAT Presentation Object Containers ....................................................... 518 [MODCA-FM-211]
40. IS/3 + MO:DCA GA Data Objects and Secondary Resources ..................................................................... 518 [MODCA-FM-212]
41. IS/3 + MO:DCA GA Begin Structured Fields ........................................................................................... 518 [MODCA-FM-213]
42. IS/3 + MO:DCA GA Structured Fields with Triplets ................................................................................... 518 [MODCA-FM-214]
43. Color Values.................................................................................................................................... 521 [MODCA-FM-215]
44. Data Object Resources Processed with RAT RG ..................................................................................... 543 [MODCA-FM-216]
45. Position and Rotation of Objects in Line Data and MO:DCA Data ................................................................ 563 [MODCA-FM-217]
46. IOC: Valid Values for XoaOrent and YoaOrent......................................................................................... 606 [MODCA-FM-218]
47. Registered Object Types Sorted by Component ID .................................................................................. 624 [MODCA-FM-219]
48. Non-OCA Object Types Supported by the IOB ........................................................................................ 626 [MODCA-FM-220]
49. Data Objects and Secondary Resources ............................................................................................... 626 [MODCA-FM-221]
50. Registered Media Types Sorted by Component ID ................................................................................... 629 [MODCA-FM-222]
51. Registered Media Types Sorted by Media Names.................................................................................... 631 [MODCA-FM-223]
52. Color Profile Registry ........................................................................................................................ 635 [MODCA-FM-224]
53. Structured Fields Sorted by ID............................................................................................................. 637 [MODCA-FM-225]
54. Structured Fields Sorted by Acronym .................................................................................................... 641 [MODCA-FM-226]
55. Triplets Sorted by ID.......................................................................................................................... 644 [MODCA-FM-227]
56. Triplets Sorted by Name..................................................................................................................... 647 [MODCA-FM-228]

