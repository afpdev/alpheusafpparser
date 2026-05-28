
Advanced Function Presentation Consortium
Data Stream and Object Architectures
AFP Programming Guide and
Line Data Reference
AFPC-0010-05


Note:
Before using this information, read the information in “Notices”.
AFPC-0010-05
Sixth Edition (February 2018)
This edition applies to the AFP™ Line Data architecture. It is the first edition produced by the AFP Consortium™ (AFPC™)
and replaces and makes obsolete the previous edition (S544-3884-04) that was published by IBM ®. This edition remains
current until a new edition is published.
Specific changes are indicated by a vertical bar to the left of the change. For a detailed list of changes, see “Summary of
Changes” on page vii.
Internet
Visit our home page: www.afpcinc.org [LINEDATA-FM-001]


Preface
This book is a reference for printing line-mode and mixed-mode data in an AFP environment. It describes the
presentation of line-mode data streams using the Page Definition (PageDef) print control object. Line-mode
data streams that adhere to the specifications in this document are accepted for printing by presentation
services programs
in most system environments.
This book also defines structured fields and objects that are used exclusively for processing line data. Because
many of these objects are either defined directly by the Mixed Object Document Content Architecture™
(MO:DCA™) or based on MO:DCA definitions, readers should also familiarize themselves with the Mixed
Object Document Content Architecture (MO:DCA) Reference.
Note: The AFP Line Data architecture has been stabilized such that it can be fully used within AFP products
and environments, but will not be extended. Many AFP products support both line data and Mixed
Object Document Content Architecture (MO:DCA) documents.
This book is a reference, not a tutorial. It complements individual product publications, but does not describe
product implementations of the architecture.
This book is intended for programmers who write applications that generate line-mode and mixed-mode data
streams or data stream objects for printing across AFP
system environments.
AFP Consortium (AFPC)
The Advanced Function Presentation™ (AFP) architectures began as the strategic, general purpose document
and information presentation architecture for the IBM Corporation. The first specifications and products go
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
open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding
member of the consortium was transferred to the InfoPrint
® Solutions Company, an IBM/Ricoh® joint venture;
currently Ricoh holds the founding member position. In February 2009, the consortium was incorporated under
a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open
standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures
was transferred at that time to the AFP Consortium. [LINEDATA-FM-002]


iv AFP Programming Guide and Line Data Reference
Related Publications
Several other publications can help you understand the architecture concepts described in this book. AFP
Consortium publications and a few other AFP publications are available on the AFP Consortium website,
www.afpcinc.org.
### Table 1. AFP Consortium Architecture References

| AFP Architecture Publication | Order Number |
| :--- | :--- |
| AFP Programming Guide and Line Data Reference | AFPC-0010 [LINEDATA-FM-003]|
| Bar Code Object Content Architecture Reference | AFPC-0005 [LINEDATA-FM-004]|
| Color Management Object Content Architecture Reference | AFPC-0006 [LINEDATA-FM-005]|
| Font Object Content Architecture Reference | AFPC-0007 [LINEDATA-FM-006]|
| Graphics Object Content Architecture for Advanced Function Presentation Reference | AFPC-0008 [LINEDATA-FM-007]|
| Image Object Content Architecture Reference | AFPC-0003 [LINEDATA-FM-008]|
| Intelligent Printer Data Stream Reference | AFPC-0001 [LINEDATA-FM-009]|
| Metadata Object Content Architecture Reference | AFPC-0013 [LINEDATA-FM-010]|
| Mixed Object Document Content Architecture (MO:DCA) Reference | AFPC-0004 [LINEDATA-FM-011]|
| Presentation Text Object Content Architecture Reference | AFPC-0009 [LINEDATA-FM-012]|

### Table 2. Additional AFP Consortium Documentation

| AFPC Publication | Order Number [LINEDATA-FM-013]|
| :--- | :--- |
| AFP Color Management Architecture™ (ACMA™) | G550-1046 (IBM) [LINEDATA-FM-014]|
| AFPC Company Abbreviation Registry | AFPC-0012 [LINEDATA-FM-015]|
| AFPC Font Typeface Registry | AFPC-0016 [LINEDATA-FM-016]|
| BCOCA Frequently Asked Questions | AFPC-0011 [LINEDATA-FM-017]|
| MO:DCA-L: The OS/2 PM Metafile (.met) Format | AFPC-0014 [LINEDATA-FM-018]|
| Presentation Object Subsets for AFP | AFPC-0002 [LINEDATA-FM-019]|
| Recommended IPDS Values for Object Container Versions | AFPC-0017 [LINEDATA-FM-020]|

### Table 3. AFP Font-Related Documentation

| Publication | Order Number [LINEDATA-FM-021]|
| :--- | :--- |
| Character Data Representation Architecture Reference and Registry; please refer to the online version for the most current information (http://www-306.ibm.com/software/globalization/cdra/index.jsp) | SC09-2190 (IBM) [LINEDATA-FM-022]|
| Font Summary for AFP Font Collection | S544-5633 (IBM) [LINEDATA-FM-023]|
| Technical Reference for Code Pages | S544-3802 (IBM) [LINEDATA-FM-024]|

### Table 4. UP3I™ Architecture Documentation

| UP3I Publication | Order Number [LINEDATA-FM-025]|
| :--- | :--- |
| Universal Printer Pre- and Post-Processing Interface (UP3I) Specification | Available at www.afpcinc.org [LINEDATA-FM-026]|


vi AFP Programming Guide and Line Data Reference [LINEDATA-FM-027]


AFP Programming Guide and Line Data Reference vii
Summary of Changes
Changes between this edition and the previous edition are marked by a vertical bar “|” in the left margin. [LINEDATA-FM-028]
This sixth edition of the AFP Programming Guide and Line Data Reference contains the following changes:
• Ability added to create BCOCA™ bar code data from multiple FIELD commands using the new Concatenate [LINEDATA-FM-029]
Bar Code Data (X'93') triplet
• Ability added to reuse the Concatenate Bar Code Data (X'93') triplet within RCDs and XMDs [LINEDATA-FM-030]
• Ability added to specify a desired bar code symbol width [LINEDATA-FM-031]
• AFP Consortium information added [LINEDATA-FM-032]
• Glossary terms added and improved; previous versions of this book provided terms used by or related to line [LINEDATA-FM-033]
data, but this version includes a much more complete set of terms related to the entire AFP architecture
• IBM-specific product information removed [LINEDATA-FM-034]
• Product information updated to be more inclusive [LINEDATA-FM-035]
• Style changes made to match other AFPC books [LINEDATA-FM-036]
• Support for ICC DeviceLink Color Management Resources (CMRs) [LINEDATA-FM-037]
• Support for PTOCA text objects (with an Object Environment Group) as an OCA object that can be included [LINEDATA-FM-038]
in line data in a mixed-mode document or with an IOB
Note: The AFP Line Data architecture has been stabilized such that it can be fully used within AFP products
and environments, but will not be extended. Many AFP products support both line data and Mixed
Object Document Content Architecture (MO:DCA) documents. [LINEDATA-FM-039]


viii AFP Programming Guide and Line Data Reference [LINEDATA-FM-040]


Contents
Preface . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . iii
AFP Consortium (AFPC) ...........................................................................................................................iii
Related Publications ............................................................................................................................... iv
Summary of Changes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . vii
Figures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xv
Tables . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xvii [LINEDATA-FM-041]
# Chapter 1. Introduction . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .1
Related Architectures ............................................................................................................................... 2
System Model......................................................................................................................................... 2
Supported Environments........................................................................................................................... 3 [LINEDATA-FM-042]
# Chapter 2. Line Data and MO:DCA (AFP) Data . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .5
Line Data............................................................................................................................................... 5
IBM Mainframe Environments ................................................................................................................ 5
AIX, Linux, and Windows Environments ................................................................................................... 9
IBM i Environment ............................................................................................................................... 9
IBM OS/2 Environment......................................................................................................................... 9
Line Data Summary ................................................................................................................................. 9
Record-Format Line Data.................................................................................................................... 12
Unicode Line Data............................................................................................................................. 13
XML Data ........................................................................................................................................ 13
MO:DCA Data Summary ......................................................................................................................... 14
Combining Line Data with MO:DCA Structured Fields ................................................................................... 14
The Function of the Page Definition ........................................................................................................... 14 [LINEDATA-FM-043]
# Chapter 3. Using a Page Definition to Print Data . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 15
Common Examples of Page Definition Use ................................................................................................. 15
Using More than One Page Definition ........................................................................................................ 15
Page Definition Structure ........................................................................................................................ 16
Resource Environment Group.............................................................................................................. 18
BSG (Begin Resource Environment Group) ........................................................................................ 18
MDR (Map Data Resource) ............................................................................................................. 18
MPO (Map Page Overlay)............................................................................................................... 19
PPO (Preprocess Presentation Object).............................................................................................. 19
ESG (End Resource Environment Group) .......................................................................................... 19
Data Map Structure ........................................................................................................................... 19
Active Environment Group Structure ..................................................................................................... 20
Font Lists .................................................................................................................................... 21
Table Reference Characters............................................................................................................ 22
Page Overlays ............................................................................................................................. 23
Page Segments............................................................................................................................ 23
Data Objects................................................................................................................................ 23
Color Management........................................................................................................................ 24
Structured Fields .......................................................................................................................... 24
Data Map Transmission Subcase ......................................................................................................... 26
Data Map Transmission Subcase with LNDs....................................................................................... 26
Data Map Transmission Subcase with RCDs ...................................................................................... 27
Data Map Transmission Subcase with XMDs ...................................................................................... 27
Data Map Transmission Subcase Structure ............................................................................................ 28
Field Formatting—LND Processing ........................................................................................................... 29
Field Formatting—RCD Processing ........................................................................................................... 29
Field Formatting—XMD Processing........................................................................................................... 30
Using Conditional Processing in a Page Definition ........................................................................................ 30
Using Different Formats for Different Subsets of Output ............................................................................. 30
Conditionally Skipping to a New Page or a New Sheet .............................................................................. 31
Processing Line Data with Shift-Out/Shift-In (SOSI) Controls .......................................................................... 32 [LINEDATA-FM-044]


x AFP Programming Guide and Line Data Reference
Printing Bar Codes with a Page Definition ................................................................................................... 34
Printing Graphics with a Page Definition ..................................................................................................... 35
Relative Baseline Positioning—LND Processing .......................................................................................... 35
Skip-to-Channel Processing for Relative Baseline Positioning..................................................................... 36
Relative Baseline Positioning—RCD Processing .......................................................................................... 36
Relative Baseline Positioning—XMD Processing.......................................................................................... 37
Relative Inline Positioning—XMD Processing .............................................................................................. 37
The Function of the Form Definition ........................................................................................................... 38 [LINEDATA-FM-045]
# Chapter 4. Mixed Documents: Adding MO:DCA Structured Fields to Line Data . . . . . . . . . . 39
X'5A' Carriage Control Character .............................................................................................................. 40
Print File Structure ................................................................................................................................. 40
Finishing Operations for a Print File ........................................................................................................... 42
Inline Resource Group Structure............................................................................................................... 43
Programming Considerations for Inline Resources ................................................................................... 44
Invoke Data Map ................................................................................................................................... 44
Sample IDM Structured Field ............................................................................................................... 45
Invoke Medium Map............................................................................................................................... 45
Sample IMM Structured Field............................................................................................................... 46
Using Structured Fields to Skip to a New Page or Sheet ............................................................................ 46
IMM Structured Fields to Insert a Blank Sheet ......................................................................................... 47
Variable-Length and Fixed-Length Records................................................................................................. 48
Position and Orientation of Objects ........................................................................................................... 49
Positioning With Respect to Current Descriptor ............................................................................................ 51
Current LND Position ......................................................................................................................... 51
Current RCD Position......................................................................................................................... 51
Include Page Segment ........................................................................................................................... 51
Positioning of Page Segments ............................................................................................................. 52
Location of Page Segment Origin ..................................................................................................... 52
Position and Orientation of IM Image Objects in a Page Segment............................................................ 52
Position and Orientation of Image, Graphics, and Bar Code Objects in a Page Segment .............................. 53
Sample IPS Structured Field................................................................................................................ 53
Include Page Overlay ............................................................................................................................. 54
Positioning Overlays .......................................................................................................................... 54
Location of Overlay Origin............................................................................................................... 54
Orientation of Overlay .................................................................................................................... 54
Position and Orientation of IM Image Object in an Overlay ..................................................................... 54
Position and Orientation of IO Image, Graphics, and Bar Code Objects in an Overlay .................................. 55
Sample IPO Structured Field ............................................................................................................... 55
Include Object ...................................................................................................................................... 55
Including Data Objects Directly in Line Data ................................................................................................ 56
Including Bar Code, Graphics, IO Image, and Presentation Text Objects with OEG
.......................................... 56
Including IM Image Objects ................................................................................................................. 57
Including Standalone Presentation Text(PTX) Structured Fields ................................................................. 57
Record Format When Using PTX Structured Fields .............................................................................. 58
Using the PTX Structured Field ........................................................................................................ 58
Use of Fonts ................................................................................................................................ 60
Boxes and Rules .......................................................................................................................... 61
Composed Documents ........................................................................................................................... 63
Programming Options ........................................................................................................................ 63
Overall Document Structure ................................................................................................................ 64
Document Indexing................................................................................................................................ 64
Document Links .................................................................................................................................... 64 [LINEDATA-FM-046]
# Chapter 5. Structured Fields in a Page Definition and in Line Data . . . . . . . . . . . . . . . . . . . 65
Structured Field Format .......................................................................................................................... 65
Structured Field Descriptions ................................................................................................................... 66
Notation Conventions......................................................................................................................... 66
Structured Field Triplets...................................................................................................................... 67
External Resource Object Naming Conventions....................................................................................... 67
Begin and End Structured Fields .......................................................................................................... 68
Begin Data Map (BDM)........................................................................................................................... 69
BDM (X'D3A8CA') Syntax ................................................................................................................... 69 [LINEDATA-FM-047]


AFP Programming Guide and Line Data Reference xi
BDM Semantics ................................................................................................................................ 69
BDM Triplets .................................................................................................................................... 70
Encoding Scheme ID (X'50') Triplet................................................................................................... 70
Page Count Control (X'7C') Triplet .................................................................................................... 71
Triplet X'7C' Syntax................................................................................................................... 71
Triplet X'7C' Semantics .............................................................................................................. 71
Margin Definition (X'7F') Triplet ........................................................................................................ 73
Triplet X'7F' Syntax ................................................................................................................... 73
Triplet X'7F' Semantics .............................................................................................................. 73
Begin Data Map Transmission Subcase (BDX) ............................................................................................ 76
BDX (X'D3A8E3') Syntax .................................................................................................................... 76
BDX Semantics ................................................................................................................................ 76
Begin Page Map (BPM) .......................................................................................................................... 77
BPM (X'D3A8CB') Syntax ................................................................................................................... 77
BPM Semantics ................................................................................................................................ 77
Conditional Processing Control (CCP) ....................................................................................................... 78
CCP (X'D3A7CA') Syntax ................................................................................................................... 78
CCP Semantics ................................................................................................................................ 78
Data Map Transmission Subcase Descriptor (DXD) ...................................................................................... 82
DXD (X'D3A6E3') Syntax .................................................................................................................... 82
DXD Semantics ................................................................................................................................ 82
End Data Map (EDM) ............................................................................................................................. 83
EDM (X'D3A9CA') Syntax ................................................................................................................... 83
EDM Semantics ................................................................................................................................ 83
End Data Map Transmission Subcase (EDX)............................................................................................... 84
EDX (X'D3A9E3') Syntax .................................................................................................................... 84
EDX Semantics ................................................................................................................................ 84
End Page Map (EPM)............................................................................................................................. 85
EPM (X'D3A9CB') Syntax ................................................................................................................... 85
EPM Semantics ................................................................................................................................ 85
Fixed Data Size (FDS)............................................................................................................................ 86
FDS (X'D3AAEC') Syntax ................................................................................................................... 86
FDS Semantics ................................................................................................................................ 86
Fixed Data Text (FDX) ............................................................................................................................ 87
FDX (X'D3EEEC') Syntax ................................................................................................................... 87
FDX Semantics ................................................................................................................................ 87
Invoke Data Map (IDM)........................................................................................................................... 88
IDM (X'D3ABCA') Syntax .................................................................................................................... 88
IDM Semantics ................................................................................................................................. 88
Include Object (IOB) .............................................................................................................................. 89
IOB (X'D3AFC3') Syntax..................................................................................................................... 89
IOB Semantics ................................................................................................................................. 90
IOB Triplets...................................................................................................................................... 92
Extended Resource Local Identifier (X'22') Triplet ................................................................................ 92
Triplet X'22' Syntax ................................................................................................................... 92
Triplet X'22' Semantics .............................................................................................................. 92
Include Page Overlay (IPO) ..................................................................................................................... 93
IPO (X'D3AFD8') Syntax..................................................................................................................... 93
IPO Semantics ................................................................................................................................. 93
Include Page Segment (IPS).................................................................................................................... 95
IPS (X'D3AF5F') Syntax ..................................................................................................................... 95
IPS Semantics.................................................................................................................................. 95
Line Descriptor Count (LNC) .................................................................................................................... 97
LNC (X'D3AAE7') Syntax .................................................................................................................... 97
LNC Semantics ................................................................................................................................ 97
Line Descriptor (LND)............................................................................................................................. 98
LND (X'D3A6E7') Syntax .................................................................................................................... 98
LND Semantics ................................................................................................................................ 99
LND Triplets................................................................................................................................... 107
Fully Qualified Name (X'02') Triplet ................................................................................................. 107
Triplet X'02' Syntax ................................................................................................................. 107
Triplet X'02' Semantics ............................................................................................................ 107 [LINEDATA-FM-048]


xii AFP Programming Guide and Line Data Reference
Extended Resource Local Identifier (X'22') Triplet .............................................................................. 108
Triplet X'22' Syntax ................................................................................................................. 108
Triplet X'22' Semantics ............................................................................................................ 108
Color Specification (X'4E') Triplet ................................................................................................... 109
Bar Code Symbol Descriptor (X'69') Triplet ........................................................................................110
Triplet X'69' Syntax ..................................................................................................................110
Triplet X'69' Semantics ............................................................................................................. 111
Resource Object Include (X'6C') Triplet.............................................................................................116
Triplet X'6C' Syntax..................................................................................................................116
Triplet X'6C' Semantics .............................................................................................................116
Additional Bar Code Parameters (X'7B') Triplet...................................................................................118
Triplet X'7B' Semantics .............................................................................................................118
Object Reference Qualifier (X'89') Triplet...........................................................................................119
Triplet X'89' Syntax ..................................................................................................................119
Triplet X'89' Semantics .............................................................................................................119
Color Management Resource Descriptor (X'91') Triplet ....................................................................... 121
Triplet X'91' Syntax ................................................................................................................. 121
Triplet X'91' Semantics ............................................................................................................ 121
Concatenate Bar Code Data (X'93') Triplet
....................................................................................... 122
Triplet X'93' Syntax ................................................................................................................. 122
Triplet X'93' Semantics ............................................................................................................ 122
Record Descriptor (RCD) ...................................................................................................................... 124
RCD (X'D3A68D') Syntax.................................................................................................................. 124
RCD Semantics .............................................................................................................................. 125
Logical Page Eject Processing ........................................................................................................... 132
RCD Triplets .................................................................................................................................. 133
Fully Qualified Name (X'02') Triplet ................................................................................................. 133
Triplet X'02' Syntax ................................................................................................................. 133
Triplet X'02' Semantics ............................................................................................................ 133
Fully Qualified Name (X'02') Triplet ................................................................................................. 134
Extended Resource Local Identifier (X'22') Triplet .............................................................................. 135
Color Specification (X'4E') Triplet ................................................................................................... 136
Bar Code Symbol Descriptor (X'69') Triplet ....................................................................................... 137
Resource Object Include (X'6C') Triplet............................................................................................ 138
Additional Bar Code Parameters (X'7B') Triplet.................................................................................. 139
Graphics Descriptor (X'7E') Triplet .................................................................................................. 140
Triplet X'7E' Syntax ................................................................................................................. 140
Triplet X'7E' Semantics ............................................................................................................ 141
Object Reference Qualifier (X'89') Triplet.......................................................................................... 146
Color Management Resource Descriptor (X'91') Triplet ....................................................................... 147
Concatenate Bar Code Data (X'93') Triplet
....................................................................................... 148
Rendering Intent (X'95') Triplet....................................................................................................... 149
Triplet X'95' Syntax ................................................................................................................. 149
Triplet X'95' Semantics ............................................................................................................ 149
XML Descriptor (XMD) ......................................................................................................................... 151
XMD (X'D3A68E') Syntax.................................................................................................................. 151
XMD Semantics .............................................................................................................................. 152
Logical Page Eject Processing ........................................................................................................... 157
XMD Triplets .................................................................................................................................. 158
Fully Qualified Name (X'02') Triplet ................................................................................................. 158
Extended Resource Local Identifier (X'22') Triplet .............................................................................. 159
Color Specification (X'4E') Triplet ................................................................................................... 160
Bar Code Symbol Descriptor (X'69') Triplet ....................................................................................... 161
Resource Object Include (X'6C') Triplet............................................................................................ 162
Additional Bar Code Parameters (X'7B') Triplet.................................................................................. 163
Graphics Descriptor (X'7E') Triplet .................................................................................................. 164
XML Name (X'8A') Triplet ............................................................................................................. 165
Triplet X'8A' Syntax ................................................................................................................. 165
Triplet X'8A' Semantics ............................................................................................................ 165
Color Management Resource Descriptor (X'91') Triplet ....................................................................... 166
Concatenate Bar Code Data (X'93') Triplet
.................................................................................... 167
Rendering Intent (X'95') Triplet....................................................................................................... 168 [LINEDATA-FM-049]


AFP Programming Guide and Line Data Reference xiii
Appendix A. Document and Resource Object Diagrams . . . . . . . . . . . . . . . . . . . . . . . . . . 169
Appendix B. Cross-References. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 179
Structured Fields Arranged Alphabetically................................................................................................. 179
Structured Fields Arranged Numerically by Hexadecimal Code ..................................................................... 183
PTOCA Control Sequences Arranged Alphabetically................................................................................... 187
PTOCA Control Sequences Arranged Numerically ..................................................................................... 189
Notices . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 191
Trademarks........................................................................................................................................ 192
Glossary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195
Index . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 237 [LINEDATA-FM-050]


xiv AFP Programming Guide and Line Data Reference [LINEDATA-FM-051]


Figures
1. AFP System Printing Relationships ............................................................................................................ 2 [LINEDATA-FM-052]
2. Formatted and Unformatted Line Data Records ............................................................................................ 6 [LINEDATA-FM-053]
3. Valid Line Data Records ........................................................................................................................ 10 [LINEDATA-FM-054]
4. Valid Record-Format Line Data ............................................................................................................... 12 [LINEDATA-FM-055]
5. Printing a Data Set in z/OS Multiple Times with Different Page Definitions ........................................................ 16 [LINEDATA-FM-056]
6. Page Definition Structure ....................................................................................................................... 16 [LINEDATA-FM-057]
7. Resource Environment Group Structure for a Page Definition ........................................................................ 18 [LINEDATA-FM-058]
8. Data Map Structure for a Page Definition................................................................................................... 19 [LINEDATA-FM-059]
9. Data Map Active Environment Group Structure for a Page Definition ............................................................... 20 [LINEDATA-FM-060]
10. PPFA Code for Page Definition with Six TRCs to Select Typographic Fonts..................................................... 22 [LINEDATA-FM-061]
11. Data Map Transmission Subcase with LNDs............................................................................................. 26 [LINEDATA-FM-062]
12. PPFA Code for Page Definition with Conditional Processing ........................................................................ 31 [LINEDATA-FM-063]
13. PPFA Code for Page Definition to Skip to New Page .................................................................................. 32 [LINEDATA-FM-064]
14. PPFA Code for Page Definition to Skip to New Sheet ................................................................................. 32 [LINEDATA-FM-065]
15. Structure of a Print File ........................................................................................................................ 41 [LINEDATA-FM-066]
16. Structure of an Inline Resource Group .................................................................................................... 43 [LINEDATA-FM-067]
17. Sample Invoke Data Map Structured Field ............................................................................................... 45 [LINEDATA-FM-068]
18. Returning Control to First Medium Map in Form Definition ........................................................................... 46 [LINEDATA-FM-069]
19. Sample Invoke Medium Map Structured Field ........................................................................................... 46 [LINEDATA-FM-070]
20. Using an IDM Structured Field to Skip to a New Page................................................................................. 47 [LINEDATA-FM-071]
21. Using an IMM Structured Field to Skip to a New Sheet................................................................................ 47 [LINEDATA-FM-072]
22. Using Two IMM Structured Fields to Force a Blank Sheet ............................................................................ 47 [LINEDATA-FM-073]
23. Form Definition With Two IMMs to Force a Blank Sheet .............................................................................. 48 [LINEDATA-FM-074]
24. Three Versions of the Invoke Data Map Structured Field ............................................................................. 48 [LINEDATA-FM-075]
25. Include Page Segment Structured Field................................................................................................... 53 [LINEDATA-FM-076]
26. Include Page Overlay Structured Field .................................................................................................... 55 [LINEDATA-FM-077]
27. Presentation Text Structured Field.......................................................................................................... 58 [LINEDATA-FM-078]
28. Text Controls to Draw a Box .................................................................................................................. 62 [LINEDATA-FM-079]
29. Relationship of Margin Definition to Text Orientation................................................................................... 75 [LINEDATA-FM-080]
30. Structure of a Print File ...................................................................................................................... 170 [LINEDATA-FM-081]
31. Structure of a Mixed Line-Page Document ............................................................................................. 171 [LINEDATA-FM-082]
32. Structure of a Presentation Page Object ................................................................................................ 171 [LINEDATA-FM-083]
33. Structure of Line Format Data ............................................................................................................. 172 [LINEDATA-FM-084]
34. Structure of a Presentation Text Data Object .......................................................................................... 172 [LINEDATA-FM-085]
35. Structure of an IM Image Data Object ................................................................................................... 173 [LINEDATA-FM-086]
36. Structure of an IO Image Data Object.................................................................................................... 173 [LINEDATA-FM-087]
37. Structure of a Graphics Data Object ..................................................................................................... 174 [LINEDATA-FM-088]
38. Structure of a Bar Code Data Object ..................................................................................................... 174 [LINEDATA-FM-089]
39. Structure of a Page Segment Resource Object ....................................................................................... 175 [LINEDATA-FM-090]
40. Structure of an Overlay Resource Object ............................................................................................... 175 [LINEDATA-FM-091]
41. Structure of a Form Definition Resource Object....................................................................................... 176 [LINEDATA-FM-092]
42. Structure of a Page Definition Resource Object....................................................................................... 177 [LINEDATA-FM-093]


xvi AFP Programming Guide and Line Data Reference [LINEDATA-FM-094]


Tables
1. AFP Consortium Architecture References .................................................................................................. iv [LINEDATA-FM-095]
2. Additional AFP Consortium Documentation ................................................................................................ iv [LINEDATA-FM-096]
3. AFP Font-Related Documentation ............................................................................................................ iv [LINEDATA-FM-097]
4. UP3I™ Architecture Documentation ........................................................................................................... v [LINEDATA-FM-098]
5. ANSI Carriage Control Characters ............................................................................................................. 7 [LINEDATA-FM-099]
6. Machine Code Control Characters ............................................................................................................. 7 [LINEDATA-FM-100]
7. Platform Support of Data formats............................................................................................................... 9 [LINEDATA-FM-101]
8. Use of TRCs in Page Mode and 3800 Compatibility Mode ............................................................................. 23 [LINEDATA-FM-102]
9. Initial Text Conditions in PTD-2................................................................................................................ 25 [LINEDATA-FM-103]
10. Position and Rotation of Objects in Line Data and MO:DCA Data .................................................................. 49 [LINEDATA-FM-104]
11. Control Sequences Used in PTX Structured Field ...................................................................................... 60 [LINEDATA-FM-105]
12. Structured Field Triplet Syntax............................................................................................................... 67 [LINEDATA-FM-106]
13. CCP Repeating Group Structure ............................................................................................................ 79 [LINEDATA-FM-107]
14. Color-Value Table ............................................................................................................................. 105 [LINEDATA-FM-108]
15. Structured Fields Arranged Alphabetically.............................................................................................. 179 [LINEDATA-FM-109]
16. Structured Fields Arranged Numerically by Hexadecimal Code................................................................... 183 [LINEDATA-FM-110]
17. PTOCA Control Sequences Arranged Alphabetically................................................................................ 187 [LINEDATA-FM-111]


xviii AFP Programming Guide and Line Data Reference [LINEDATA-FM-112]


