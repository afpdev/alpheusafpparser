Chapter 3. BCOCA Overview
This chapter provides an overview of the BCOCA architecture and describes:
• General BCOCA concepts [BCOCA-3-001]
• Bar code object processor concepts [BCOCA-3-002]
• Bar code presentation space concepts [BCOCA-3-003]
General BCOCA Concepts
The BCOCA architecture is an object content architecture used to describe and generate bar code symbols.
BCOCA objects can exist in, or be invoked by, a number of environments. Each of these controlling
environments can be specialized for a particular application area. For example, the controlling environment
can be:
• An environment involved in electronically distributing documents in a network; for example, the MO:DCA [BCOCA-3-004]
environment
• A presentation system communicating with hard-copy presentation devices; for example, the IPDS [BCOCA-3-005]
environment
• An environment that controls how line data is presented; for example, the AFP Line Data environment [BCOCA-3-006]
In these environments, multiple bar code symbols with the same attributes can be specified within a single bar
code object as described in Appendix B, “MO:DCA Environment”,  and Appendix C, “IPDS
Environment”, . When multiple bar code symbols of the same type are to be printed on a page, the
symbols can be combined into a single object, which avoids having to repeat the same descriptor in multiple
objects.


A BCOCA receiver consists of a bar code object processor. The primary function of the bar code object
processor is to develop one or more bar code symbols of the same type within an abstract presentation space,
as illustrated in Figure 8 . In turn, these abstract bar code presentation spaces are mapped into
areas defined within the controlling environments. Examples of controlling environment areas include the IPDS
bar code object area for printing bar code symbols, and the MO:DCA object area for interchange. For
additional information, refer to Appendix B, “MO:DCA Environment”,  and Appendix C, “IPDS
Environment”, .
Input to the bar code object processor consists of:
• Data to be encoded [BCOCA-3-007]
• Bar code symbology to be used [BCOCA-3-008]
• Bar code presentation space size parameters [BCOCA-3-009]
• Bar code symbol location within the bar code presentation space [BCOCA-3-010]
• Module width of the narrow bar code element (or desired symbol width) [BCOCA-3-011]
• T otal element height of the bar code symbol [BCOCA-3-012]
• Check digit generation option [BCOCA-3-013]
• Wide-to-narrow element ratio [BCOCA-3-014]
• Human-readable interpretation (HRI) presence, location, and type style [BCOCA-3-015]
• Color of the bar code symbol elements [BCOCA-3-016]
• For 2D symbologies, special functions such as: [BCOCA-3-017]
– Ability to ignore escape sequences
– Application indicator
– EBCDIC-to-ASCII translation
– Error correction level
– Macro characters to indicate a specific header or trailer
– Matrix row size
– Number of data symbol characters per row
– Number of rows
– Security level
– Structured append information
– Symbol conforms to specific industry standards
– Symbol is reader programming information
– Symbol mode
– T est pattern (zipper)
– Version
The bar code object processor:
• Validates all input parameters and generates exception conditions as appropriate. [BCOCA-3-018]
• Generates the bar and space patterns of the input data to be encoded according to the rules of the specified [BCOCA-3-019]
bar code symbology.
– For two-level codes, the bar and space patterns are generated using the module width and wide-to-narrow
ratio input parameters.
– For discrete codes, whose bar and space patterns for each character start and end with a bar, an
intercharacter gap is required. The bar code object processor automatically inserts these gaps. The
intercharacter gap is one module width wide.
• Generates, uses, and encodes check digit(s) according to the rules of the symbology and the check-digit [BCOCA-3-020]
option input parameter (modifier field). [BCOCA-3-021]


• For 2D matrix symbologies, encodes and compacts the data, inserts codewords for special functions, [BCOCA-3-022]
generates ECC characters, determines the proper placement of the bits in the matrix, and generates the
finder patterns.
• For 2D stacked symbologies, generates codewords from the input data using a combination of compaction [BCOCA-3-023]
schemes based on the input data, generates start and stop patterns, generates the left row and right row
indicator codewords (that have the number of rows and columns and security level encoded within),
generates the symbol length descriptor, and generates the error correction and detection codewords.
• Generates the appropriate start and stop bar and space patterns for all bar code types and versions [BCOCA-3-024]
including the UPC-family center and delineator patterns.
• Generates the HRI text characters and places them above or below the symbol as directed. [BCOCA-3-025]
• Suppresses presentation of the bar code symbol if directed by the suppress bar code symbol flag. This can [BCOCA-3-026]
be used to print just the HRI.
• Places the bar code symbol and HRI, if present, in the bar code presentation space at the location specified. [BCOCA-3-027]
The user is responsible for insuring that the symbol and HRI information is totally contained within the bar
code presentation space, and that there is sufficient empty space for the quiet zones.
• For the QR Code with Image bar code, provides or accesses an image object processor to place the desired [BCOCA-3-028]
image in the bar code presentation space at the size and location specified. The user is responsible for
ensuring that the image is totally contained within the bar code presentation space, and that the resulting QR
Code is scannable despite the placed image.
Notes:
1. The BCOCA object generator is responsible for insuring that there is sufficient empty space for quiet [BCOCA-3-029]
zones. Some symbologies require extra space if a wand-type scanner is to be used.
2. All bar code symbols must be presented in their entirety. Whenever a partial bar code pattern is presented, [BCOCA-3-030]
for whatever reason, it is obscured to make it unscannable. [BCOCA-3-031]


A bar code presentation space is a linear, two-dimensional space. An orthogonal coordinate system is used to
define any point within the presentation space. Distances within the coordinate system are measured in logical
units, also known as L-units. One or more bar code symbols of the same type may be placed within the
presentation space. Figure 8 shows a bar code presentation space containing two bar code symbols.
Figure 8. Bar Code Presentation Space
Bar Code
Presentation
Space Origin
Bar Code
Symbol Origin
X Extent of Bar Code
Presentation Space
bc
Y Extent of Bar Code
Presentation Space
bc
Bar Code
Symbol Origin
+Ybc
+Xbc
S544-3
S544-3
Coordinate System
The Xbc,Ybc coordinate system is the bar code presentation space coordinate system. The origin of this system
(xbc=0, ybc=0) is the top-left corner. Positive X bc values increase from left to right. Positive Y bc values increase
from top to bottom.
The size of the bar code presentation space in the X bc dimension is called the Xbc extent. The size of the bar
code presentation space in the Y bc dimension is called the Ybc extent.
An additional coordinate system, the X qr,Yqr coordinate system, is used when processing one specific type of
bar code; see “QR Code with Image Special-Function Parameters”  for details.
Measurements
In general usage, linear measurements are expressed as a specific number followed by a unit called the
measurement base. The measurement base is typically a well known unit such as an inch or a centimeter. For
example, in the measurement 12 inches, the measurement base is inches; in the measurement 12
centimeters, the measurement base is centimeters. Since we know the length of one inch or one centimeter, it
is easy to measure 12 of these units.
In BCOCA data structures, linear measurements are expressed as numbers called logical units (L-units).
When a number is expressed in terms of L-units, an appropriate measurement base must be used to interpret
the value of the number. The measurement base is separately supplied in the Bar Code Symbol Descriptor
(BSD).


Measurement bases used in BCOCA objects are expressed using a unit base field and a units per unit base
field:
Unit base A one-byte code that represents the length of the measurement base. A value
of X'00' specifies that the length of the measurement base is ten inches. A
value of X'01' specifies that the length of the measurement base is ten
centimeters.
Units per unit base A two-byte field that contains the number of units in the measurement base.
The previous general-usage examples had a unit base of one inch or one
centimeter and a units per unit base of one. The BCOCA architecture allows
the units per unit base to be any value between X'0001' and X'7FFF', but
requires all bar code object processors to at least support X'3840' (14,400)
units per ten inches. Many bar code object processors also support X'0960'
(2400) units per ten inches.
For example, within bar code symbol data, the X and Y offset values for placing the bar code symbol within the
presentation space might be expressed as X'00F0' (240) L-units in the X-direction and X'01E0' (480) L-units in
the Y-direction. For a unit base of X'00' (ten inches) with 2400 units per unit base, this describes a point 1 inch
over and 2 inches down from the origin of the presentation space.
Units of measure is the length of the measurement base, specified by the unit base field, divided by the value
of units per unit base. For example, the units of measure for a bar code presentation space might be
expressed as 1/240 of an inch; there are 240 units in one inch. The term L-unit is sometimes used as a
synonym for unit of measure.
Resolution is the reciprocal of units of measure. For example, the resolution of the bar code presentation
space would be expressed as 240 units per inch.
L-unit Range Conversion Algorithm
Some field values within BCOCA data structures are specified assuming a unit of measure of 1/1440 of an
inch. These fields are designated as such with a reference to this algorithm. If a BCOCA receiver supports
additional units of measure, the BCOCA architecture requires the receiver to at least support a range
equivalent to the specified range relative to each supported unit of measure. Table 6  lists the
equivalent field ranges for the most commonly used units of measure.
The values required to be supported when 14,400 units per 10 inches is specified for a field are listed in the
BCOCA data structure. If additional units of measure are supported, the field values that the BCOCA
architecture requires a bar code object processor to support for these alternate units of measure are calculated
using the following algorithm:
1. Calculate the number of supported units per inch as follows: [BCOCA-3-032]
• If the length of the measurement base for a field is ten inches, divide the number of supported units that [BCOCA-3-033]
applies to the desired field by ten.
• If the length of the measurement base for a field is ten centimeters, multiply the number of supported [BCOCA-3-034]
units per ten centimeters (one decimeter) that applies to the desired field by 0.254, the approximate
number of decimeters per inch.
2. Calculate the number of supported units per BCOCA unit as follows: [BCOCA-3-035]
• Divide the number of supported units per inch calculated in the previous step by 1440 (the number of [BCOCA-3-036]
BCOCA units per inch). [BCOCA-3-037]


3. Calculate the required value in the supported unit of measure as follows: [BCOCA-3-038]
• Multiply the BCOCA-specified subset range values for the desired field, after converting to base ten, by [BCOCA-3-039]
the supported units per BCOCA-specified unit calculated in the previous step.
• Round off the product to the nearest integer; for example, 2.5 would become 3 and 2.4 would become 2. [BCOCA-3-040]
• Adjust the new range so that it is a subset of the BCOCA-specified subset range. [BCOCA-3-041]
For example, suppose that the specified range is X'0001'–X'7FFF' when using 14,400 units per 10 inches. The
equivalent range at a unit of measure of 1/240 of an inch is calculated as follows:
1. Supported units per inch = 2400 / 10 = 240 [BCOCA-3-042]
2. Supported units per BCOCA unit = 240 / 1440 = 1/6 [BCOCA-3-043]
3. Range at 2400 units per 10 inches: [BCOCA-3-044]
a. X'0001' = 1 (converted to base ten)
(1)(1/6) = 0.1667
b. X'7FFF' = 32,767 (converted to base ten)
(32,767)(1/6) = 5461.1667
Therefore, the equivalent range at 2400 units per 10 inches is “1 to 5461” that in hexadecimal is X'0001' to
X'1555'. Table 6shows the BCOCA-required ranges for several commonly supported measurement bases.
Table 6. Field Ranges for Commonly-Supported Measurement Bases
14,400 units per 10 inches 5670 units per 10
centimeters
2400 units per 10 inches 945 units per 10 [BCOCA-3-045]
centimeters
X'0001'–X'7FFF' X'0001'–X'7FFF' X'0001'–X'1555' X'0001'–X'1555'
Percentage Measurements
In addition to the above L-unit-based measurement system, the QR Code with Image bar code has an
additional measurement system, where linear measurements can be specified as percentages of the size of
the QR Code symbol. A value of X'64' for the unit base specifies that the length of the measurement base is
1% of the size of the QR Code symbol.
As an example using a unit base of X'64', if a value of X'000A' (10) is specified for units per unit base, the units
of measure would be 0.1% (one-tenth of one percent, or one-thousandth) of the size of the QR Code symbol.
Furthermore, if the extent values for the image object area are specified as (X'00FA', X'00C8')—(250, 200) in
decimal— that would indicate the image object area is one-quarter as wide and one-fifth as high as the QR
Code symbol.
For more on this measurement system, see “QR Code with Image Special-Function Parameters” . [BCOCA-3-046]


Symbol Placement
One or more bar code symbols may be placed within the bar code presentation space. The origin of the bar
code symbol is defined to be the top-left corner of an imaginary rectangle of minimum size that bounds the bar-
space patterns (or two-dimensional module patterns) of the symbol. The height of the symbol is measured in
the +Y
bc direction. The width of the symbol is measured in the +X bc direction.
Note: In most cases, the symbol origin is the top-left corner of the leftmost bar; however, this is not an
appropriate origin for some bar code types, such as Dutch KIX, Intelligent Mail Barcode, MaxiCode, and
Royal Mail Mailmark. The original BCOCA symbol origin definition was the “top-left corner of the leftmost
bar”; therefore, some implementations might still use the original definition (this is not considered to be a
deviation from the architecture for these older implementations). For GS1 DataBar symbols, the origin of
the bar code symbol is the top-left corner of the leftmost space (since GS1 DataBar symbols begin with
a space).
The BCOCA object generator is responsible for insuring that there is sufficient empty space for quiet zones.
Some symbologies require extra space if a wand-type scanner is to be used. Exception condition EC-1100
exists if any portion of the bar code, including the bar and space patterns, the Bearer Bars (Interleaved 2-of-5),
the Identification Bars and USPS Banner (Intelligent Mail Container Barcode or Intelligent Mail Package
Barcode), the RED TAG indicator (Royal Mail RED TAG (deprecated)), the zipper pattern and contrast block
(MaxiCode), any image printed in conjunction with a QR Code symbol (QR Code with Image), and the HRI,
extends outside of the bar code presentation space.
Symbol Placement


Symbol Orientation
Bar code users typically think of a bar code symbol in one of two orientations (picket fence or ladder) although
linear symbols are usually defined in the picket fence orientation. Orientation of a bar code symbol into either
the picket fence or ladder orientation is accomplished by rotating the bar code object area within the controlling
environment. In the MO:DCA environment this orientation is specified in the Object Area Position (OBP)
structured field; in the IPDS environment this orientation is specified in the Bar Code Area Position (BCAP)
structure in the Write Bar Code Control (WBCC) command.
All BCOCA implementations allow the object area to be rotated to one or more of the following orientations: 0°,
90°, 180°, 270°. Most of the implementations support all four orientations, thus allowing a bar code symbol to
be presented in either a picket fence or ladder orientation or in one of the other two (upside-down) orientations.
In addition, some BCOCA implementations allow the object area to be rotated to any angle.
A picket fence bar code or symbol is presented horizontally. In this orientation, the bars look like a picket fence.
A ladder bar code or symbol is presented vertically. In this orientation, the bars look like the rungs of a ladder.
Figure 9 shows two bar code symbols as examples of the two orientations.
Figure 9. Bar Code Orientations
Picket Fence
Orientation
Ladder
Orientation
S544-3766-01
S544-3766-01
Symbol Orientation


Symbol Size
The height of a bar code symbol is controlled by the bar code symbology definition, by the amount of data to be
encoded, and by various BCOCA parameters. The width of the symbol is usually dependent on the amount of
data to be encoded and by choices made in various BCOCA parameters. Default values exist for most of the
BCOCA parameters that can be used to produce minimal-size, scannable symbols; refer to your printer
documentation for information about the specific default values used by BCOCA printers.
Some BCOCA implementations support the desired symbol width parameter. This parameter provides a target
width for the symbol and allows the BCOCA receiver to calculate an optimal module width value based on the
target width. Implementations that don’t support the desired symbol width parameter require the BCOCA
generator to provide an appropriate module width value.
Linear Symbologies
The element-height and height-multiplier parameters specify the height of the symbol. For
some bar code types, these parameters also include the height of the human-readable
interpretation (HRI). Refer to the description of the element-height parameter  for a
description of the height for specific linear symbols. Some bar code symbologies explicitly
specify the bar code symbol height; in this case, the element-height and height-multiplier
parameters are ignored. The symbologies that explicitly specify the symbol height are as
follows: Australia Post Bar Code, Intelligent Mail Barcode, Japan Postal Bar Code, POSTNET
(deprecated), RM4SCC, Royal Mail RED TAG (deprecated), and Royal Mail Mailmark.
Two-Dimensional Matrix Symbologies
The MaxiCode symbology specifies a fixed physical size; the element-height and height-
multiplier parameters are ignored for MaxiCode symbols. Some BCOCA receivers provide
“small-symbol support” that allows the symbol to be produced at either an optimal or a small
size; the module-width parameter is used to select the small or optimal size.
Data Matrix symbols are rectangular and are made up of a pattern of light and dark squares
(called modules). The size of each module is specified in the module-width parameter and the
number of rows and columns of these modules is controlled by the desired-number-of-rows
and desired-row-size parameters and the amount of data to be encoded. The element-height
and height-multiplier parameters are ignored for Data Matrix symbols.
QR Code symbols are square and are made up of a pattern of light and dark squares (called
modules). The size of each module is specified in the module-width parameter; the number of
rows and columns of these modules is controlled by the version parameter, the error
correction level selected, and the amount of data to be encoded. The element-height and
height-multiplier parameters are ignored for QR Code symbols.
Aztec Code symbols are square and are made up of a pattern of light and dark squares (called
modules). The size of each module is specified in the module-width parameter; the number of
rows and columns of these modules is controlled by the desired-number-of-rows parameter,
the error correction level selected, and the amount of data to be encoded. The element-height
and height-multiplier parameters are ignored for Aztec Code symbols.
Han Xin Code symbols are square and are made up of a pattern of light and dark squares
(called modules). The size of each module is specified in the module-width parameter; the
number of rows and columns of these modules is controlled by the version parameter, the
error correction level selected, and the amount of data to be encoded. The element-height and
height-multiplier parameters are ignored for Han Xin Code symbols.
Two-Dimensional Stacked Symbologies
PDF417 symbols are rectangular and are made up of a pattern of light and dark rectangles
(called modules). The size of each module is specified in the module-width, element-height,
and height-multiplier parameters and the number of rows and columns of these modules is
controlled by the data-symbols and rows parameters and the amount of data to be encoded. A
PDF417 symbol must contain at least 3 rows.
Symbol Size


Bar code symbols are meant to be read by machines and are usually difficult for a human to interpret; therefore
some bar code symbols allow a human-readable interpretation (HRI) to be printed near the symbol. HRI is the
printed translation of bar code characters into equivalent Latin alphabetic characters, Arabic numeral decimal
digits, and common special characters normally used for printed human communication. The BCOCA
architecture allows the bar code object to specify whether or not HRI is printed and whether the HRI is above
or below the symbol. Table 7shows which bar code types allow HRI and recommends a font type style for
each.
The first place a BCOCA implementor should look for HRI guidelines is the bar code symbology specification; if
the symbology specification does not provide enough details on HRI, the implementor should then use the
BCOCA guidelines described in this section.
Table 7. Human-Readable Interpretation Type Style Recommendations
Type Bar Code Symbology HRI Supported? Recommended
Font Type Style
X'01' Code 39 (3-of-9 Code), AIM USS-39 Yes; above or below OCR A
X'02' MSI (modified Plessey code) Yes; above or below OCR A
X'03' UPC/CGPC – Version A Yes; below only OCR B
X'05' UPC/CGPC – Version E Yes; below only OCR B
X'06' UPC – Two-Digit Supplemental (Periodicals) Yes; above only OCR B
X'07' UPC – Five-Digit Supplemental (Paperbacks) Yes; above only OCR B
X'08' EAN-8 (includes JAN-short) Yes; below only OCR B
X'09' EAN-13 (includes JAN-standard) Yes; below only OCR B
X'0A' Industrial 2-of-5 Yes; above or below OCR A
X'0B' Matrix 2-of-5 Yes; above or below OCR A
X'0C' Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 Yes; above or below OCR A
X'0D' Codabar, 2-of-7, AIM USS-Codabar Yes; above or below OCR A
X'11' Code 128, AIM USS-128
Code 128 modifier X'02'
Yes; above or below OCR B
UCC/EAN 128
Code 128 modifier X'03'
Yes; above or below OCR B
UCC/EAN 128 and GS1-128
Code 128 modifier X'04'
Yes; above or below OCR B
Intelligent Mail Container Barcode
Code 128 modifier X'05'
Yes; below only a bold, sans-serif
font
Intelligent Mail Package Barcode
Code 128 modifier X'06'
Yes; below only a bold, sans-serif
font
X'16' EAN Two-Digit Supplemental Yes; above only OCR B
X'17' EAN Five-Digit Supplemental Yes; above only OCR B
X'18' POSTNET (deprecated) and PLANET (deprecated) No None
X'1A' RM4SCC and Dutch KIX No None
X'1B' Japan Postal Bar Code No None [BCOCA-3-047]


Table 7 Human-Readable Interpretation Type Style Recommendations (cont'd.)
Type Bar Code Symbology HRI Supported? Recommended
Font Type Style
X'1C' Data Matrix, GS1 DataMatrix (2D bar code) No None
X'1D' MaxiCode (2D bar code) No None
X'1E' PDF417 (2D bar code) No None
X'1F' Australia Post Bar Code Yes; above only OCR A
X'20' QR Code, QR Code with Image (2D bar code) No None
X'21' Code 93 Yes; above or below OCR B plus the □
and ■ characters
X'22' Intelligent Mail Barcode Yes; above or below OCR A
X'23' Royal Mail RED TAG (deprecated) No None
X'24' GS1 DataBar Yes; below only OCR B
X'25' Royal Mail Mailmark No None
X'26' Aztec Code No None
X'27' Han Xin Code No None
The Bar Code Symbol Data (BSA) structure contains flags (in byte 0) that control whether or not HRI is printed
(bit 0), for some symbols whether the HRI is positioned above or below the symbol (bits 1–2), and for Code 39
symbols whether or not an asterisk is presented for the start and stop characters (bit 3). These flags are
ignored for symbologies that do not allow HRI. If the bar-code-symbol-suppression flag (bit 5) is B'1', the HRI
position flags are ignored and should be set to B'00'.
The Bar Code Symbol Descriptor (BSD) structure contains the local ID of a font to be used when HRI is
requested. A value of X'FF' indicates that a presentation device selected font is to be used. Since most
BCOCA receivers provide resident font resources for use with the supported bar code symbologies, specifying
a local ID of X'FF' is recommended.
Some symbologies, such as UPC, EAN, and Intelligent Mail Barcode specify the size and position of the HRI
characters. Other symbologies do not provide guidance; for these it is recommended that the font size be
selected based on the width of the bar code symbol and that the HRI string be centered on the width of the bar
code symbol. It is also recommended that the distance between the characters and the bars be one module
width.
Some bar code types and modifiers call for the calculation and presentation of check digits. Check digits are a
method of verifying data integrity during the bar coding reading process. Except for UPC/CGPC Version E, the
check digit is always presented in the bar code bar and space patterns, but is not always presented in the HRI.
Refer to “Check Digit Calculation Methods”  for a description of check digit calculation methods and
the presence or absence of the check digit in the HRI.
Code 128 modifier X'04' causes left and right parentheses to be shown within the HRI string to distinguish each
application identifier within a GS1-128 symbol. Application identifiers are also surrounded by parentheses in
the HRI for GS1 DataBar symbols. [BCOCA-3-048]




