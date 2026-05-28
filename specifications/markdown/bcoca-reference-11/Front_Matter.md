
Advanced Function Presentation Consortium
Data Stream and Object Architectures
Bar Code Object Content
Architecture Reference
AFPC-0005-11


Copyright © AFP Consortium 1991, 2025 ii
Note:
Before using this information, read the information in “Notices” .
AFPC-0005-11
Twelfth Edition (December 2025)
This edition applies to the Bar Code Object Content Architecture™ (BCOCA™). It is the fifth edition produced by the AFP
Consortium™ (AFPC™) and replaces and makes obsolete the previous edition (AFPC-0005-10). This edition remains
current until a new edition is published. This publication also applies to any subsequent releases of Advanced Function
Presentation™ (AFP™) products that use the BCOCA architecture until otherwise indicated in a new edition.
T echnical changes are indicated in green, with a green vertical bar to the left of the change. Editorial changes that have no
technical significance are not noted. For a detailed list of changes, see “Changes in This Edition”  .
Internet
Visit our home page: www.afpconsortium.org [BCOCA-FM-001]


Copyright © AFP Consortium 1991, 2025 iii
Preface
This book describes the functions and services associated with the Bar Code Object Content Architecture
(BCOCA).
This book is a reference, not a tutorial. It complements individual product publications, but does not describe
product implementations of the architecture.
Who Should Read This Book
This book is for systems programmers and other developers who need such information to develop or adapt a
product or program to interoperate with other presentation products in an Advanced Function Presentation
(AFP) environment.
AFP Consortium
The AFP Consortium is an international group bringing together voices from across the printing and
presentation
industry to keep the AFP architecture up to date and continually improving. AFP Consortium
members, often market competitors, work together to ensure this stable, efficient, flexible architecture
continues to thrive, even as the world of printing and presentation
changes.
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
open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding
member of the consortium was transferred to the InfoPrint
® Solutions Company, an IBM/Ricoh® joint venture;
currently Ricoh holds the founding member position. In February 2009, the consortium was incorporated under
a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open
standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures
was transferred at that time to the AFP Consortium. [BCOCA-FM-002]


iv BCOCA Reference
Publication History
The BCOCA Reference was first published by IBM in 1987 as part of the IPDS™ Reference; it was published
as an independent architecture document in 1991 and has had several enhancements and updates since that
time. The first seven editions were published by IBM Corporation and later editions were published by the AFP
Consortium.
First Edition published by IBM Corporation
S544-3766-00 dated August 1991
Second Edition published by IBM Corporation
S544-3766-01 dated July 1993
This edition provides enhanced detail and clarifications:
• Additional information has been provided to aid in the generation of BCOCA objects. [BCOCA-FM-003]
• Chapter 1 has been enhanced to describe how the BCOCA architecture fits into IBM's [BCOCA-FM-004]
presentation environments.
• The glossary has been extensively revised. [BCOCA-FM-005]
Third Edition published by IBM Corporation
S544-3766-02 dated December 1997
This edition provides enhanced detail and the following major new functions:
• Additional information to aid in the generation of BCOCA objects [BCOCA-FM-006]
• Check digit details for all symbologies [BCOCA-FM-007]
• Glossary updates [BCOCA-FM-008]
• Many clarifications [BCOCA-FM-009]
• New UPC/EAN supplemental modifiers [BCOCA-FM-010]
• Two new postal bar codes: [BCOCA-FM-011]
1. Japan Postal Bar Code [BCOCA-FM-012]
2. Royal Mail Postal Bar Code (RM4SCC) [BCOCA-FM-013]
Fourth Edition published by IBM Corporation
S544-3766-03 dated June 2000
This edition provides enhanced detail and the following major new functions:
• Additional information to aid in the generation of BCOCA objects [BCOCA-FM-014]
• A method of suppressing trailing blanks when bar codes are built from AFP line data [BCOCA-FM-015]
• Editorial improvements for color, module width, bar code descriptions, and the list of [BCOCA-FM-016]
symbology specifications
• Information about the Code 39 character set [BCOCA-FM-017]
• Information about UCC/EAN 128 [BCOCA-FM-018]
• Two new postal bar codes: [BCOCA-FM-019]
1. Australia Post Bar Code [BCOCA-FM-020]
2. Dutch KIX postal bar code (a variation of the RM4SCC code) [BCOCA-FM-021]
Publication History


BCOCA Reference v
Fifth Edition published by IBM Corporation
S544-3766-04 dated May 2001
This edition provides enhanced detail and the following major new functions:
• Additional information to aid in the generation of BCOCA objects [BCOCA-FM-022]
• A method of suppressing a bar code symbol so that just the human-readable interpretation [BCOCA-FM-023]
(HRI) is printed
• Three new two-dimensional bar code symbologies: [BCOCA-FM-024]
1. Data Matrix [BCOCA-FM-025]
2. MaxiCode [BCOCA-FM-026]
3. PDF417 [BCOCA-FM-027]
Sixth Edition published by IBM Corporation
S544-3766-05 dated November 2003
This edition provides enhanced detail and the following major new functions:
• Additional information, clarifications, and pictures to aid in the generation of BCOCA objects [BCOCA-FM-028]
• Two new bar code types to provide additional symbol variations: [BCOCA-FM-029]
1. Code 93 1D bar code [BCOCA-FM-030]
2. QR Code [BCOCA-FM-031]
® 2D bar code
• Two new bar code variations: [BCOCA-FM-032]
1. PLANET , a variation of POSTNET [BCOCA-FM-033]
2. UCC/EAN 128, a variation of Code 128 [BCOCA-FM-034]
Seventh Edition published by IBM Corporation
S544-3766-06 dated July 2006
This edition provides enhanced detail and the following major new functions:
• Additional information, clarifications, and pictures to aid in the generation of BCOCA objects [BCOCA-FM-035]
• A new bar code type: [BCOCA-FM-036]
– USPS Four-State bar code (also called OneCodeSOLUTION bar code, later renamed to
Intelligent Mail® Barcode)
• Enhancements: [BCOCA-FM-037]
– Additional color spaces (RGB, CMYK, highlight, and CIELAB)
– Shift-out, shift-in (SOSI) support for QR Code
– UCC/EAN 128 clarifications and modifier X'04'
Eighth Edition published by the AFP Consortium
AFPC-0005-07 dated January 2011
This edition provides enhanced detail and the following major new functions:
• New bar code types and modifiers: [BCOCA-FM-038]
– Intelligent Mail Container Barcode
– Royal Mail RED TAG
• A new BCOCA subset called BCD2 [BCOCA-FM-039]
• Enhancements: [BCOCA-FM-040]
– Clarification for MaxiCode EOT character
– Control over Data Matrix encodation scheme
– Correction to Japan Postal check digit algorithm
– Default parameter value recommendations
– Desired symbol width parameter
– GS1 terminology
– Guidelines for printing HRI
Publication History


vi BCOCA Reference
– Retired items identified
– Small fixed-size bar codes
– Small Intelligent Mail Barcodes
– Symbol origin clarification
• Additional information, clarifications, and pictures to aid in the generation of BCOCA objects [BCOCA-FM-041]
Ninth Edition published by the AFP Consortium
AFPC-0005-08 dated May 2012
This edition provides enhanced detail and the following new function:
• A new bar code type and several modifiers for the GS1 DataBar family of bar codes: [BCOCA-FM-042]
– GS1 DataBar Omnidirectional
– GS1 DataBar Truncated
– GS1 DataBar Stacked
– GS1 DataBar Stacked Omnidirectional
– GS1 DataBar Limited
– GS1 DataBar Expanded
– GS1 DataBar Expanded Stacked
• Bearer Bars for Interleaved 2-of-5 and ITF-14 symbols [BCOCA-FM-043]
• Information about the role of the BCOCA BCD2 subset in MO:DCA™ Interchange Set 3 [BCOCA-FM-044]
(IS/3)
• Additional information, clarifications, and pictures to improve readability [BCOCA-FM-045]
Tenth Edition published by the AFP Consortium
AFPC-0005-09 dated June 2015
This edition provides enhanced detail and the following new function:
• A new bar code type called Royal Mail Mailmark ® [BCOCA-FM-046]
• Two new bar code modifiers for Royal Mail Mailmark: Barcode C (66 bars) and Barcode L [BCOCA-FM-047]
(78 bars)
• Royal Mail RED TAG bar code type has been deprecated [BCOCA-FM-048]
• POSTNET and PLANET bar codes have been deprecated [BCOCA-FM-049]
• One new exception ID (EC-1204) [BCOCA-FM-050]
• New appendix describing each numbered retired item and also identifying items that have [BCOCA-FM-051]
been unretired
• Metadata Object Content Architecture (MOCA) added; metadata can be carried in MO:DCA [BCOCA-FM-052]
print files and documents, but is currently not supported in IPDS data streams
• Extensive glossary additions for color terms and new AFP terms [BCOCA-FM-053]
• Additional information and clarifications to improve readability [BCOCA-FM-054]
Eleventh Edition published by the AFP Consortium
AFPC-0005-10 dated December 2023
This edition provides enhanced detail and the following new function:
• New bar code types: [BCOCA-FM-055]
– Aztec Code (new type X'26', new modifiers X'00'–X'03')
– Intelligent Mail Package Barcode (existing type X'11', new modifier X'06')
– QR Code with Image; this addition provides the ability to print some number of images in
conjunction with a QR Code symbol (existing type X'20', new modifier X'12')
• Extended Rectangular Data Matrix; this addition results in a Data Matrix bar code having 18 [BCOCA-FM-056]
new rectangular sizes (existing type X'1C', new modifier X'01')
• As a result of adding the QR Code with Image bar code type: [BCOCA-FM-057]
Publication History


BCOCA Reference vii
– The Xqr,Yqr coordinate system
– The X'64' unit base, meaning “one percent” (of the QR Code symbol)
– The Image Information Block in the QR Code with Image special-function parameters
• A new “too much data” special-function parameters control flag, specifying the behavior if [BCOCA-FM-058]
there is too much data to fit in a requested bar code size, for the existing Data Matrix and
QR Code, and the new Aztec Code and QR Code with Image bar code types
• A clarification that the Dutch KIX bar code has no check digit [BCOCA-FM-059]
• 26 new exception IDs (EC-0F13 to EC-0F3B, and EC-1205) [BCOCA-FM-060]
• 3 updated exception ID descriptions (EC-0F01, EC-0F04, and EC-1100) [BCOCA-FM-061]
• Updated glossary to include the current definition for all AFP terms [BCOCA-FM-062]
• Additional information and clarifications to improve readability [BCOCA-FM-063]
Publication History


viii BCOCA Reference
How to Use This Book
This book is divided into six chapters and four appendixes:
• Chapter 1, “A Presentation Architecture Perspective”,  introduces the AFPC presentation [BCOCA-FM-064]
architectures and describes the role of data streams and data objects.
• Chapter 2, “Introduction to BCOCA”,  describes bar code symbols, bar code symbologies, and the [BCOCA-FM-065]
basic elements of a bar code system.
• Chapter 3, “BCOCA Overview”,  describes the key concepts of the BCOCA architecture and its [BCOCA-FM-066]
relationship to other presentation architectures.
• Chapter 4, “BCOCA Data Structures”,  defines the data structures, fields, and valid data values [BCOCA-FM-067]
assigned to and reserved or retired for the BCOCA architecture.
• Chapter 5, “Exception Conditions”,  lists the exceptions to the BCOCA definitions and what to do [BCOCA-FM-068]
when such exceptions occur.
• Chapter 6, “Compliance”,  describes requirements for valid generators and receivers of a [BCOCA-FM-069]
BCOCA object.
• Appendix A, “Bar Code Symbology Specification References”,  lists the bar code symbology [BCOCA-FM-070]
specifications referenced in this document.
• Appendix B, “MO:DCA Environment”,  describes how BCOCA bar code objects are defined and [BCOCA-FM-071]
used in the MO:DCA environment.
• Appendix C, “IPDS Environment”,  describes how BCOCA bar code objects are defined and [BCOCA-FM-072]
used in the IPDS environment.
• Appendix D, “Retired Items”,  lists each retired item that is mentioned within the body of this [BCOCA-FM-073]
book and also lists those items that have been unretired.
The “Glossary”  defines terms used within the book.
How to Use This Book


BCOCA Reference ix
How to Read the Syntax Diagrams
Throughout this book, syntax for the BCOCA data structures is described using the structure defined in T able
1.
Table 1. Data Structure Syntax
Offset Type Name Range Meaning BCD1 Range BCD2 Range
The field’s offset,
data type, or
both
Name of
field, if
applicable
Range of
valid values,
if applicable
Meaning or purpose of the data
element
Subset of the
range of values
that must be
supported by all
BCOCA
receivers; refer
to Chapter 6,
“Compliance”,
 for
additional details
Subset of the
range of values
that must be
supported by all
BCD2 receivers;
BCD2 is the bar
code subset
used for the
MO:DCA IS/3
interchange set
The five basic data types used in BCOCA syntax tables are:
CODE Architected constant
BITS Bit string
SBIN Signed binary
UBIN Unsigned binary
UNDF Undefined data type
The following is an example of a BCOCA data structure:
Table 2. Bar Code Symbol Data (BSA) Data Structure
Offset Type Name Range Meaning BCD1 Range BCD2 Range
0 BITS Bar code flags [BCOCA-FM-074]
bit 0 HRI B'0'
B'1'
HRI is presented
HRI not presented
B'0'
B'1'
B'0'
B'1'
bits 1–2 Position B'00'
B'01'
B'10'
Default
HRI below
HRI above
B'00'
B'01'
B'10'
B'00'
B'01'
B'10'
bit 3 SSCAST B'0'
B'1'
Asterisk is not presented
Asterisk is presented
B'0'
B'1'
B'0'
B'1'
bit 4 B'0' Retired item 21 B'0' B'0'
bit 5 Suppress
bar code
symbol
B'0'
B'1'
Bar code suppression:
Present symbol
Suppress symbol
B'0' B'0'
B'1'
bit 6 Suppress
blanks
B'0'
B'1'
Desired method of adjusting
for trailing blanks:
Don't suppress
Suppress and adjust
B'0' B'0'
bit 7 B'0' Retired item 3 B'0' B'0'
1–2 UBIN X offset X'0001' –
X'7FFF'
X
bc-coordinate of the symbol
origin in the bar code
presentation space
X'0001'–X'7FFF'
Refer to the note
following the
table.
X'0001'–X'7FFF'
Refer to the note
following the
table.
How to Read the Syntax Diagrams [BCOCA-FM-075]


x BCOCA Reference
Table 2 Bar Code Symbol Data (BSA) Data Structure (cont'd.)
Offset Type Name Range Meaning BCD1 Range BCD2 Range
3–4 UBIN Y offset X'0001' –
X'7FFF'
Ybc-coordinate of the symbol
origin in the bar code
presentation space
X'0001'–X'7FFF'
Refer to the note
following the
table.
X'0001'–X'7FFF'
Refer to the note
following the
table.
The following special-function information is only used with the following bar code types:
Aztec Code, Data Matrix, Han Xin Code, Intelligent Mail Package Barcode, MaxiCode, PDF417,
QR Code, QR Code with Image
5–n Special
functions
See field
description
Special-function information
that is specific to the bar code
type
Not supported in
BCD1
See field
description
The following symbol data is specified for all bar code types
n+1 to
end
UNDF Data Any value
defined for
the bar code
type selected
by the BSD
Data to be encoded Any value
defined for the
bar code type
selected by the
BSD
Any value
defined for the
bar code type
selected by the
BSD
Note: The BCD1 and BCD2 range for these fields has been specified assuming a unit of measure of 1/1440 of
an inch. Many receivers support the BCD1 or BCD2 subset plus additional function. If a receiver
supports additional units of measure, the BCOCA architecture requires the receiver to support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-unit Range Conversion Algorithm” on
page 21.
How to Read the Syntax Diagrams [BCOCA-FM-076]


BCOCA Reference xi
Notation Conventions
The following notation conventions apply to the BCOCA data structures.
• Each byte contains eight bits. [BCOCA-FM-077]
• Bytes of a BCOCA data structure are numbered from left to right beginning with byte 0 with the leftmost byte [BCOCA-FM-078]
as most significant; this is called Big Endian. For example, if a structure is three bytes long and has two
fields, a two-byte field followed by a one-byte field, the bytes are numbered as follows:
Bytes 0–1 Field 1
Byte 2 Field 2
Byte 0 is the leftmost, high-order byte for the first field.
• Bit strings are numbered beginning with 0. For example, a one-byte bit string contains bit 0, bit 1, ..., bit 7. [BCOCA-FM-079]
• For numerical binary data, bit 0 is the most significant bit. For example, decimal 13 is equivalent to binary [BCOCA-FM-080]
B'00001101'.
• Field values are expressed in hexadecimal or binary notation: [BCOCA-FM-081]
X'7FFF' = +32,767
B'0001' = 1
B'01111110' = X'7E' = +126
• Some bits or bytes are labeled reserved. The content of reserved fields is not checked by BCOCA receivers. [BCOCA-FM-082]
However, BCOCA generators should set reserved fields to the specified value, if one is given, or to zero.
• Some fields or values are labeled Retired item n, where n is an identifying number. These fields or values are [BCOCA-FM-083]
reserved for a particular purpose and must not be used for any other purpose. Refer to Appendix D, “Retired
Items”,  for a description of the individual retired items.
• Values not explicitly defined in the range column of a field are reserved. [BCOCA-FM-084]
• Additional information about specific fields is listed after each data structure table. [BCOCA-FM-085]
• The term default is used in the description of some bits or bytes in the meaning column of the data structure [BCOCA-FM-086]
tables. The default values for these fields are described in the field descriptions that follow the data structure
tables.
Notation Conventions


xii BCOCA Reference
Bar Code Abbreviations
Abbreviations used in this book have the following meanings:
AIM USS Automatic Identification Manufacturers Uniform Symbol Specification
DMRE Extended Rectangular Data Matrix (“Data Matrix Rectangulaire Étendu” in French)
EAN European Article Numbering
GS1 Global Standards 1
ITF-14 Interleaved 2-of-5 encoding 13 input digits and a check digit
JAN Japanese Article Numbering
MSI MSI Data Corporation
PDF417 Portable Data File 417
PLANET PostaL Alpha Numeric Encoding T echnique (United States Postal Service)
POSTNET POST al Numeric Encoding T echnique (United States Postal Service)
QR Code Quick Response Code
RM4SCC Royal Mail 4 State Customer Code
UCC Uniform Code Council
UPC Universal Product Code (United States)
UPC/CGPC Universal Product Code (United States) and the Canadian Grocery Product Code
USPS United States Postal Service
USS Uniform Symbol Specification
Bar Code Abbreviations [BCOCA-FM-087]


BCOCA Reference xiii
Related Publications
Several other publications can help you understand the architecture concepts described in this book. The AFP
Consortium publications and other AFP publications below are available on the AFP Consortium web site,
www.afpconsortium.org .
Table 3. AFP Consortium Architecture References
AFP Architecture Publication Book Identification
AFP Programming Guide and Line Data Reference AFPC-0010
Bar Code Object Content Architecture Reference AFPC-0005
Color Management Object Content Architecture Reference AFPC-0006
Font Object Content Architecture Reference AFPC-0007
Graphics Object Content Architecture for Advanced Function Presentation Reference AFPC-0008
Image Object Content Architecture Reference AFPC-0003
Intelligent Printer Data Stream™ Reference AFPC-0001
Metadata Object Content Architecture Reference AFPC-0013
Mixed Object Document Content Architecture™ (MO:DCA) Reference AFPC-0004
Presentation Text Object Content Architecture Reference AFPC-0009
Table 4. Additional AFP Consortium Documentation
AFPC Publication Book Identification
AFP Color Management Architecture™ (ACMA™) G550-1046 (IBM)
AFPC Company Abbreviation Registry AFPC-0012
AFPC Font Typeface Registry AFPC-0016
BCOCA Frequently Asked Questions AFPC-0011
Metadata Guide for AFP AFPC-0018
MO:DCA-L: The OS/2 PM Metafile (.met) Format AFPC-0014
Presentation Object Subsets for AFP AFPC-0002
Recommended IPDS Values for Object Container Versions AFPC-0017
Table 5. AFP Font-Related Documentation
Publication Book Identification
Character Data Representation Architecture Reference and Registry SC09-2190 (IBM)
Font Summary for AFP Font Collection S544-5633 (IBM)
Technical Reference for Code Pages S544-3802 (IBM)
Related Publications


xiv BCOCA Reference


BCOCA Reference xv
Changes in This Edition [BCOCA-FM-088]
Changes between this edition and the previous edition are marked by a vertical bar (|) in the left margin. [BCOCA-FM-089]
This edition provides enhanced detail to support the BCOCA products that were introduced in the years 2023
through 2025 and to support the work of the AFP Consortium. Specifically, the following new function has been
added:
• A new bar code type: Han Xin Code (new type X'27', new modifier X'00') [BCOCA-FM-090]
• Clarification regarding Data Matrix bar code sizes chosen by the printer [BCOCA-FM-091]
• 5 new exception IDs (EC-0F21 to EC-0F25) [BCOCA-FM-092]
• Updated glossary to include the current definition for all AFP terms [BCOCA-FM-093]
• Additional information and clarifications to improve readability [BCOCA-FM-094]
Changes in This Edition [BCOCA-FM-095]


xvi BCOCA Reference


Copyright © AFP Consortium 1991, 2025 xvii
Contents
Preface . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . iii
Who Should Read This Book .....................................................................................................................iii
AFP Consortium......................................................................................................................................iii
Publication History.................................................................................................................................. iv
How to Use This Book ............................................................................................................................ viii
How to Read the Syntax Diagrams......................................................................................................... ix
Notation Conventions.......................................................................................................................... xi
Bar Code Abbreviations.......................................................................................................................xii
Related Publications .............................................................................................................................. xiii
Changes in This Edition . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xv
Figures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xxi
Tables . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xxiii
Chapter 1. A Presentation Architecture Perspective. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .1
The Presentation Environment ................................................................................................................... 1
Architecture Components.......................................................................................................................... 2
Data Streams ..................................................................................................................................... 2
Objects ............................................................................................................................................. 4
Chapter 2. Introduction to BCOCA . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .7
What Is a Bar Code? ................................................................................................................................ 7
How Data Is Presented......................................................................................................................... 7
How Data Is Retrieved ......................................................................................................................... 7
Elements of a Bar Code System ................................................................................................................. 7
Bar Code Symbology ........................................................................................................................... 8
Linear Symbologies......................................................................................................................... 8
Two-Dimensional Matrix Symbologies ............................................................................................... 12
Two-Dimensional Stacked Symbologies ............................................................................................ 13
Bar Code Symbol Generation .......................................................................................................... 14
Bar Code Encoding T echniques ....................................................................................................... 14
Information Density ....................................................................................................................... 14
Physical Media ................................................................................................................................. 15
Printers ........................................................................................................................................... 15
Scanners......................................................................................................................................... 16
Performance Measurement ..................................................................................................................... 16
Chapter 3. BCOCA Overview . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 17
General BCOCA Concepts ...................................................................................................................... 17
Bar Code Object Processor ..................................................................................................................... 18
Bar Code Presentation Space .................................................................................................................. 20
Coordinate System ............................................................................................................................ 20
Measurements ................................................................................................................................. 20
L-unit Range Conversion Algorithm................................................................................................... 21
Percentage Measurements ............................................................................................................. 22
Symbol Placement ............................................................................................................................ 23
Symbol Orientation ............................................................................................................................ 24
Symbol Size..................................................................................................................................... 25
Human-Readable Interpretation (HRI) Guidelines .................................................................................... 26
Chapter 4. BCOCA Data Structures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 29
BCD1 Subset ....................................................................................................................................... 29
BCD2 Subset ....................................................................................................................................... 29
Bar Code Symbol Descriptor (BSD) ........................................................................................................... 31
Default Value Recommendations.......................................................................................................... 45
Bar Code Type and Modifier Descriptions ............................................................................................... 48
Code 39 (3-of-9 Code), AIM USS-39 (modifier values X'01' and X'02') ...................................................... 48
MSI (modified Plessey code, modifier values X'01' through X'09') ............................................................ 49 [BCOCA-FM-096]


xviii BCOCA Reference
UPC/CGPC – Version A (modifier value X'00')..................................................................................... 50
UPC/CGPC – Version E (modifier value X'00')..................................................................................... 50
UPC – Two-Digit Supplemental (modifier values X'00' through X'02')........................................................ 51
UPC – Five-Digit Supplemental (modifier values X'00' through X'02') ....................................................... 52
EAN-8 (includes JAN-short, modifier value X'00') ................................................................................. 53
EAN-13 (includes JAN-standard, modifier value X'00') .......................................................................... 53
Industrial 2-of-5 (modifier values X'01' and X'02') ................................................................................. 54
Matrix 2-of-5 (modifier values X'01' and X'02') ..................................................................................... 54
Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 (modifier values X'01' through X'04') .......................................... 54
Codabar, 2-of-7, AIM USS-Codabar (modifier values X'01' and X'02')....................................................... 56
Code 128 (modifier values X'02' through X'06').................................................................................... 57
Code 128 modifier X'02' – Code 128 symbol, using original (1986) start-character algorithm ..................... 58
Code 128 modifier X'03' – UCC/EAN 128 symbol, without parentheses in the HRI .................................. 59
Code 128 modifier X'04' – UCC/EAN 128 and GS1-128 symbols, with parentheses in the HRI .................. 60
Code 128 modifier X'05' – Intelligent Mail Container Barcode ............................................................. 61
Code 128 modifier X'06' – Intelligent Mail Package Barcode .............................................................. 63
EAN Two-Digit Supplemental (modifier values X'00' and X'01') ............................................................... 64
EAN Five-Digit Supplemental (modifier values X'00' and X'01') ............................................................... 65
POSTNET and PLANET (both deprecated, modifier values X'00' through X'04').......................................... 66
Royal Mail RM4SCC and Dutch KIX (modifier values X'00' and X'01')....................................................... 67
Japan Postal Bar Code (modifier values X'00' and X'01') ....................................................................... 68
Data Matrix and GS1 DataMatrix (modifier values X'00' and X'01') ........................................................... 70
MaxiCode (modifier value X'00') ....................................................................................................... 71
PDF417 (modifier values X'00' and X'01') ........................................................................................... 72
Australia Post Bar Code (modifier values X'01' through X'08') ................................................................. 74
QR Code (modifier values X'02' and X'12') ......................................................................................... 75
QR Code modifier X'12' – QR Code with Image ............................................................................... 75
Code 93 (modifier value X'00') ......................................................................................................... 77
Intelligent Mail Barcode (modifier values X'00' through X'03').................................................................. 78
Royal Mail RED TAG (deprecated), modifier value X'00' ........................................................................ 79
GS1 DataBar ............................................................................................................................... 81
Royal Mail Mailmark (modifier values X'00' and X'01') ........................................................................... 86
Aztec Code (modifier values X'00' through X'03') ................................................................................. 87
Han Xin Code (modifier value X'00')
.................................................................................................. 89
Check Digit Calculation Methods .......................................................................................................... 90
Bar Code Symbol Data (BSA) .................................................................................................................. 94
Aztec Code Special-Function Parameters ............................................................................................ 100
Data Matrix Special-Function Parameters............................................................................................. 106
Han Xin Code Special-Function Parameters
..........................................................................................114
Intelligent Mail Package Barcode Special-Function Parameters .................................................................118
MaxiCode Special-Function Parameters .............................................................................................. 120
PDF417 Special-Function Parameters ................................................................................................. 125
QR Code Special-Function Parameters ............................................................................................... 131
QR Code with Image Special-Function Parameters ................................................................................ 139
Image Information Block............................................................................................................... 146
Valid Code Pages and Type Styles...................................................................................................... 149
Valid Characters and Data Lengths ..................................................................................................... 151
Characters and Code Points.............................................................................................................. 157
Code 128 Code Page....................................................................................................................... 160
Chapter 5. Exception Conditions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 161
Specification-Check Exceptions.............................................................................................................. 161
Data-Check Exceptions ........................................................................................................................ 167
Chapter 6. Compliance. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 169
Generator Rules.................................................................................................................................. 169
Receiver Rules ................................................................................................................................... 169
Appendix A. Bar Code Symbology Specification References . . . . . . . . . . . . . . . . . . . . . . 171
Appendix B. MO:DCA Environment. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 175
Bar Codes in MO:DCA Documents.......................................................................................................... 175
Bar Code Data Object Structured Fields ................................................................................................... 176 [BCOCA-FM-097]


BCOCA Reference xix
Bar Code Data Descriptor (BDD) ........................................................................................................ 176
Bar Code Data (BDA)....................................................................................................................... 176
Appendix C. IPDS Environment . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 177
IPDS Bar Code Command Set ............................................................................................................... 177
Write Bar Code Control Command ...................................................................................................... 177
Write Bar Code Command ................................................................................................................ 178
Additional Related Commands ............................................................................................................... 179
BCOCA Exception Conditions and IPDS Exception IDs ............................................................................... 180
Appendix D. Retired Items . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 183
Notices . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 191
Trademarks........................................................................................................................................ 192
Glossary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195
Index . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 237 [BCOCA-FM-098]


xx BCOCA Reference


Copyright © AFP Consortium 1991, 2025 xxi
Figures
1. Presentation Environment ........................................................................................................................ 1 [BCOCA-FM-099]
2. Presentation Model ................................................................................................................................ 3 [BCOCA-FM-100]
3. Presentation Page.................................................................................................................................. 5 [BCOCA-FM-101]
4. Bar Code Symbol Structure ...................................................................................................................... 8 [BCOCA-FM-102]
5. Examples of Linear Bar Code Symbols (spans three pages) ............................................................................ 9 [BCOCA-FM-103]
6. Examples of 2D Matrix Bar Code Symbols................................................................................................. 12 [BCOCA-FM-104]
7. Examples of 2D Stacked Bar Code Symbols .............................................................................................. 13 [BCOCA-FM-105]
8. Bar Code Presentation Space ................................................................................................................. 20 [BCOCA-FM-106]
9. Bar Code Orientations........................................................................................................................... 24 [BCOCA-FM-107]
10. BCOCA Function and Subsetting ........................................................................................................... 30 [BCOCA-FM-108]
11. Example of a MaxiCode Bar Code Symbol with Zipper and Contrast Block .................................................... 124 [BCOCA-FM-109]
12. Subset of EBCDIC Code Page 500 That Can Be Translated T o GLI 0 .......................................................... 126 [BCOCA-FM-110]
13. Subset of EBCDIC Code Page 500 That Can Be Translated T o ECI 000020.................................................. 135 [BCOCA-FM-111]
14. For use in the figures following, this is the image to be placed in conjunction with the QR Code symbol ............... 140 [BCOCA-FM-112]
15. The X qr,Yqr coordinate system and Image Object Area .............................................................................. 141 [BCOCA-FM-113]
16. The same QR Code with Image, but with the image rotated 90° in relation to the QR Code symbol..................... 142 [BCOCA-FM-114]
17. The same QR Code with Image, but with the image rotated 45° in relation to the QR Code symbol..................... 142 [BCOCA-FM-115]
18. Code 128 Code Page (CPGID = 1303, GCSGID = 1454) .......................................................................... 160 [BCOCA-FM-116]


xxii BCOCA Reference


Copyright © AFP Consortium 1991, 2025 xxiii
Tables
1. Data Structure Syntax ............................................................................................................................ ix [BCOCA-FM-117]
2. Bar Code Symbol Data (BSA) Data Structure .............................................................................................. ix [BCOCA-FM-118]
3. AFP Consortium Architecture References ................................................................................................. xiii [BCOCA-FM-119]
4. Additional AFP Consortium Documentation ............................................................................................... xiii [BCOCA-FM-120]
5. AFP Font-Related Documentation ........................................................................................................... xiii [BCOCA-FM-121]
6. Field Ranges for Commonly-Supported Measurement Bases ........................................................................ 22 [BCOCA-FM-122]
7. Human-Readable Interpretation Type Style Recommendations ...................................................................... 26 [BCOCA-FM-123]
8. Bar Code Symbol Descriptor (BSD) Data Structure...................................................................................... 31 [BCOCA-FM-124]
9. Bar Code Types ................................................................................................................................... 34 [BCOCA-FM-125]
10. Modifier Values by Bar Code Type.......................................................................................................... 36 [BCOCA-FM-126]
11. Standard OCA Color-Value T able ........................................................................................................... 39 [BCOCA-FM-127]
12. Sizing T argets for Fixed-Size Bar Code Types........................................................................................... 41 [BCOCA-FM-128]
13. Recommended Default Values for Module Width, Element Height, and Wide-to-Narrow Ratio ............................. 45 [BCOCA-FM-129]
14. Intelligent Mail Container Barcode Data Field Ranges ................................................................................ 61 [BCOCA-FM-130]
15. Valid Code Points for Direct Input to a Japan Postal Bar Code ...................................................................... 69 [BCOCA-FM-131]
16. Australia Post Modifier Values ............................................................................................................... 74 [BCOCA-FM-132]
17. Royal Mail RED TAG (deprecated) Data Field Ranges................................................................................ 79 [BCOCA-FM-133]
18. Modifier Values for a GS1 DataBar Expanded Stacked Bar Code .................................................................. 84 [BCOCA-FM-134]
19. Check Digit Calculation Methods ........................................................................................................... 90 [BCOCA-FM-135]
20. Bar Code Symbol Data (BSA) Data Structure ........................................................................................... 94 [BCOCA-FM-136]
21. Aztec Code Special-Function Parameters .............................................................................................. 100 [BCOCA-FM-137]
22. Supported Number of Layers for an Aztec Code Symbol ........................................................................... 103 [BCOCA-FM-138]
23. Data Matrix Special-Function Parameters .............................................................................................. 106 [BCOCA-FM-139]
24. Supported Sizes for a Modifier X'00' Data Matrix Symbol........................................................................... 109 [BCOCA-FM-140]
25. Supported Sizes for a Modifier X'01' Data Matrix Symbol............................................................................110 [BCOCA-FM-141]
26. Han Xin Code Special-Function Parameters ............................................................................................114 [BCOCA-FM-142]
27. Supported Versions for a Han Xin Code Symbol .......................................................................................116 [BCOCA-FM-143]
28. Intelligent Mail Package Barcode Special-Function Parameters ...................................................................118 [BCOCA-FM-144]
29. MaxiCode Special-Function Parameters................................................................................................ 120 [BCOCA-FM-145]
30. PDF417 Special-Function Parameters .................................................................................................. 125 [BCOCA-FM-146]
31. QR Code Special-Function Parameters ................................................................................................. 131 [BCOCA-FM-147]
32. Supported Versions for a QR Code Symbol ............................................................................................ 136 [BCOCA-FM-148]
33. QR Code with Image Special-Function Parameters.................................................................................. 143 [BCOCA-FM-149]
34. Valid Code Pages and Type Styles ....................................................................................................... 149 [BCOCA-FM-150]
35. Valid Characters and Data Lengths ...................................................................................................... 151 [BCOCA-FM-151]
36. Characters and Code Points Commonly used in the BCOCA Symbologies (Not a Complete Listing)................... 157 [BCOCA-FM-152]
37. MO:DCA Bar Code Data Descriptor (BDD)............................................................................................. 176 [BCOCA-FM-153]
38. MO:DCA Bar Code Data (BDA) ........................................................................................................... 176 [BCOCA-FM-154]
39. IPDS Bar Code Data Descriptor (BCDD) ............................................................................................... 178 [BCOCA-FM-155]
40. BCOCA Exception Conditions and IPDS Exception IDs ............................................................................ 180 [BCOCA-FM-156]
41. Valid Code Pages and Type Styles ....................................................................................................... 188 [BCOCA-FM-157]


xxiv BCOCA Reference


Copyright © AFP Consortium 1991, 2025 1
