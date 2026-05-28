Chapter 2. Introduction to BCOCA
This chapter:
• Provides a brief overview of bar codes [BCOCA-2-001]
• Describes the basic elements of a bar code system [BCOCA-2-002]
• Describes how bar code system performance is measured [BCOCA-2-003]
What Is a Bar Code?
A bar code is an accurate, easy, and inexpensive method of data presentation and data entry for Automatic
Identification (AutoID) information systems. Bar codes are the predominant AutoID technology used to collect
data about any person, place, or thing. Bar codes are used for item tracking, inventory control, time and
attendance recording, check-in/check-out, order entry, document tracking, monitoring work in progress,
controlling access to secure areas, shipping and receiving, warehousing, point-of sale operations, patient care,
and other applications.
A bar code is a predetermined pattern of elements, such as bars, spaces, and two-dimensional modules, that
represent numeric or alphanumeric information in a machine-readable form. The way the elements are
arranged is called a symbology. The Universal Product Code (UPC), the European Article-Numbering (EAN)
system, Code 39, Interleaved 2-of-5, and Code 128 are some examples of symbologies.
How Data Is Presented
Physical media and printers are used to present bar code data. Paper is the most common form of physical
media used to present data — for example, retail shelf labels, shipping containers, books, documents,
electronic forms, and mailing envelopes. However, other physical media are also used, such as fabric labels
and corrosive-resistant metal tags. The physical media must be durable enough to withstand the expected
wear and have the requisite optical properties to allow scanning equipment to read the bar code successfully.
Symbol printing can occur either on-demand in real-time or off-line in a batch printing process. The printer
technology, printer element size, printer tolerances, and optical properties of the physical media and marking
agent all determine the readability of the bar code.
How Data Is Retrieved
Data contained in a bar code symbol is retrieved by scanning the printed elements with an optical device called
a scanner. The scanning device develops logic signals corresponding to the difference in reflectivity of the
printed bars and the underlying physical media. The logic signals are translated from a serial pulse stream into
digitized computer readable data by a device called a decoder. The digitized data is transmitted to the host
computer for processing.
A bar code system consists of four major elements:
1. The bar code symbology used to encode the data [BCOCA-2-004]
2. The physical media on which the bar code is printed [BCOCA-2-005]
3. The type of printing device used to print the bar code on the physical media [BCOCA-2-006]
4. The scanning device used to read the bar code. [BCOCA-2-007]
The following sections describe these elements in greater detail. [BCOCA-2-008]


Bar Code Symbology
Linear Symbologies
A bar code symbol consists of six parts, as illustrated in Figure 4. The complete symbol consists of a start
margin, a start character, the data or message characters, an optional check-digit character, a stop character,
and a stop margin.
Figure 4. Bar Code Symbol Structure
*  D  E  S  T  O  N  E  G  * [BCOCA-2-009]
}} }} } } }}} } } } }
Start
Margin
Stop
Margin
Start
Character
Stop
CharacterMessage Data
Check-Digit
Character
The start and stop margins, sometimes referred to as quiet zones, are void of any printed character. They are
typically white. The margin areas are used to instruct the decoder that the scanner is about to encounter a bar
code symbol.
The start character, which precedes the first character of the bar code message, is a special bar and space
pattern used to identify the beginning of a bar code symbol. The start character enables the decoder to
determine that a bar code symbol is being scanned and not some other sequence of reflective and non-
reflective areas that might have the same pattern as one of the characters in the symbol.
The message portion of the symbol contains the data to be stored. The data characters are encoded as a
series of parallel bar and space patterns according to the bar code symbology used. Refer to Appendix A, “Bar
Code Symbology Specification References”,  for a list of the bar code symbology specifications.
Most bar code symbologies define a mandatory or optional check-digit character (or characters). The value of
the check-digit character is determined by an arithmetic operation performed on the data characters in the
message when the symbol is created. When used, the check-digit character becomes the last character of the
message immediately preceding the stop character.
The stop character is also a special bar and space pattern. Its purpose is to signal the end of the symbol. When
a check-digit character is used, the stop character instructs the decoder to perform the check-digit calculation
on the message data characters and compare the computed value to the encoded check-digit character. The
decoder also uses the stop character to know that it can decode and validate the message data characters. If
the message data characters are valid, the data is transmitted to the host computer for processing. Otherwise,
an error signal is generated.
The bar and space patterns used to encode the start and stop characters are generally not symmetrical, that
is, the same bar and space pattern is not used for both characters. This feature enables a decoder to scan in
the forward or reverse directions.
Figure 5  shows examples of linear bar code symbols. [BCOCA-2-010]


Figure 5. Examples of Linear Bar Code Symbols (spans three pages)
(Part 1 of figure)
Intelligent Mail Barcode
Modifier X’03’
(encoding 01 234 567094  987654321 01234567891)
US POSTNET
Zip+4
(encoding 12345+6789)
PLANET Code
(encoding 00123456789)
Japan Postal Bar Code
Modifier X'00'
(encoding 15400233-16-4)
Australia Post Bar Code
Customer Barcode 2 using Table C
(encoding 56439111ABA 9)
99  M  123456  -----ABC1234 [BCOCA-2-011]
USPS SCAN REQUIRED
Intelligent Mail Container Barcode
Code 128, Modifier X’05’
(encoding 99M123456-----ABC1234)
Royal Mail Mailmark
(bar code type C)
DAATATTTADTAATTFADDDDTTFTFDDDDFFDFDAFTADDTFFTDDATADTTFATTDAFDTFDDA
Royal Mail (RM4SCC)
(encoding SN34RD1A)
UK and Singapore version
Royal Mail (RM4SCC)
(encoding )2500GG30250
Dutch KIX version
USPS TRACKING # eVS
9374 8901 0000 0003 9850 39 [BCOCA-2-012]
Intelligent Mail Package Barcode
Code 128, Modifier X’06’
(encoding 42021234 9374890100000003985039) [BCOCA-2-013]


(Part 2 of figure)
0 512345 67890 [BCOCA-2-014]
UPC Version A
(encoding 01234567890)
0 1078349 [BCOCA-2-015]
UPC Version E
(encoding 078349)
0 806338 95260 [BCOCA-2-016]
4 2
UPC A + Two-digit Supplemental
(encoding 00633895260, supplemental = 24)
0 698277 21123 [BCOCA-2-017]
6 2 8 1 2 [BCOCA-2-018]
UPC A + Five-digit Supplemental
(encoding 09827721123, supplemental = 21826)
2468 1230
EAN 8
(encoding 2468123)
0412345 678903 [BCOCA-2-019]
EAN 13
(encoding 041234567890)
0 412345 678903 [BCOCA-2-020]
9 9
EAN + 2 Digit Supplemental
(encoding 041234567890, supplemental = 99)
0 412345 678903 [BCOCA-2-021]
1 2 3 4 5 [BCOCA-2-022]
EAN + 5 Digit Supplemental
(encoding 041234567890, supplemental = 54321)
Code 128
(encoding ABC123abc@456)
ABC1 2 3 a b c @ 4 5 6
Industrial  2-of-5
(encoding 54321068)
54321068
Interleaved 2-of-5
(encoding 54321068)
5432106854321068
Matrix  2-of-5
(encoding 54321068)
MSI - no check digit
(encoding 80523)
80523
Codabar
(encoding A34698735B)
34698735
UCC/EAN 128
00011111112222222229
(encoding      00011111112222222229)
F
N
C
1


(Part 3 of figure)
GS1 DataBar Omnidirectional
(encoding 20012345678909)
(01)20012345678909 GS1 DataBar Truncated
(encoding 20012345678909)
(01)20012345678909
GS1 DataBar Stacked
(encoding 20012345678909)
(01)20012345678909
GS1 DataBar Stacked Omnidirectional
(encoding 20012345678909)
(01)20012345678909
GS1 DataBar Limited
(encoding 15012345678907)
(01)15012345678907
GS1 DataBar Expanded
(01)98898765432106(3202)012345(15)991231
(encoding  0198898765432106320201234515991231)
GS1 DataBar Expanded Stacked
(01)98898765432106(3202)012345(15)991231
(encoding  0198898765432106320201234515991231)
Code 93
(encoding 39OR93
yielding a 1.82 inch wide symbol)
39OR93
Code 39 (3-of-9 Code)
(encoding 39OR93 with check character
yielding a 2.32 inch wide symbol)
39OR93W


Two-Dimensional Matrix Symbologies
Two-dimensional matrix symbologies (sometimes called area symbologies) allow large amounts of information
to be encoded in a two-dimensional matrix. These symbologies are usually rectangular and usually require a
quiet zone around all four sides; for example, the Data Matrix symbology requires a quiet zone at least one
module wide around the symbol. Two-dimensional matrix symbologies use extensive data compaction and
error correction codes, allowing large amounts of character or binary data to be encoded.
Unlike most linear bar codes, Human-Readable Interpretation (HRI) is not provided with the bar code symbol.
Figure 6 shows examples of two-dimensional matrix bar code symbols.
Figure 6. Examples of 2D Matrix Bar Code Symbols
QR Code 2D Symbol
MaxiCode 2D SymbolData Matrix 2D Symbol
(encoding A1B2C3D4E5F6G7H8I9J0K1L2)
QR Code with Image 2D Symbol
(Image is part of the AFP Consortium logo)
Aztec Code 2D Symbol
Han Xin Code 2D Symbol [BCOCA-2-023]


Two-Dimensional Stacked Symbologies
Two-dimensional stacked symbologies allow large amounts of information to be encoded by effectively
stacking short one-dimensional symbols in a row/column arrangement. This reduces the amount of space that
is typically consumed by conventional linear bar code symbols and allows for a large variety of rectangular bar
code shapes. Figure 7 shows an example of a two-dimensional stacked symbology.
Figure 7. Examples of 2D Stacked Bar Code Symbols
PDF417 Truncated PDF417
Stop
Pattern
Stop
Pattern
Start
Pattern
Start
Pattern
Left Row
Indicator
Codewords
Left Row
Indicator
Codewords
Right Row
Indicator
Codewords
3 Data Symbol Characters [BCOCA-2-024]
per Row
3 Data Symbol Characters [BCOCA-2-025]
per Row
13 Rows


Bar Code Symbol Generation
Generating a bar code symbol is a four-step process:
1. Identify the bar code symbology to be used and the data to be encoded in the message. [BCOCA-2-026]
2. Translate the data characters into a binary sequence for encoding. [BCOCA-2-027]
3. Create the bar and space pattern that represents each character. [BCOCA-2-028]
4. Format the individual characters into a completed bar code symbol. [BCOCA-2-029]
The general structure of a bar code symbol is implemented differently in each of the bar code symbologies.
The various symbologies can be categorized according to the encoding technique used and the information
density.
Bar Code Encoding Techniques
There are two commonly used encoding techniques: module width and non-return-to-zero (NRZ) encoding.
Module width encoding techniques are generally used in industrial applications. Commercial applications
generally use NRZ. Data in module width encoding is represented differently from data in NRZ encoding.
Module width encoding techniques encode binary data through the contrast of wide and narrow element
widths. A narrow element (bar or space) is known as the module width and represents data whose logic value
is zero. A wide element (bar or space) represents data whose logic value is one and whose width is typically
two to three times the narrow element. The ratio of elements or wide-to-narrow ratio (WE:NE) is one of the
distinguishing features of the symbologies using this technique. These bar codes are referred to as two-level
codes. With this technique, there are definite transitions from black to white and white to black separating each
binary bit from its adjacent binary bits. Examples of bar code symbologies that use this form of encoding are
Code 39 and Interleaved 2-of-5.
NRZ encoding techniques encode binary data through the reflectivity of the bars and spaces. A logic value of
zero is represented as a reflective surface and the logic value of one as a non-reflective surface. There is no
transition between bits unless the logic state changes. Therefore, a sequence of logic zeros and ones can be
represented by the width of a single reflective or non-reflective element. Bar codes utilizing NRZ encoding
techniques are sometimes referred to as four-level codes because up to four data bits of the same logic value
can be contained within a single reflective or non-reflective element. Examples of bar code symbologies that
use this form of encoding are UPC and EAN.
Information Density
Information density is the number of message characters that can be encoded per unit length. Density is
commonly divided into three categories: high, medium, and low. A high-density bar code generally contains
more than eight characters per inch; a medium-density bar code contains from four to eight characters per
inch; a low-density bar code contains less than four characters per inch.
Two factors influence bar code density: the code structure (two-level or four-level) and the module width. Bar
code density increases or decreases by varying the module width when it is printed. Module widths are
generally separated into three groups: high resolution, medium resolution, and low resolution. High-resolution
module widths are typically less than 0.009 inch; medium-resolution module widths are between 0.009 inch
and 0.020 inch; low-resolution module widths are greater than 0.020 inch. The criteria for selecting module
widths are the application requirements and the printer characteristics. [BCOCA-2-030]


Physical Media
Bar code symbols can be printed on a wide variety of physical media. The most common physical media are
adhesive labels, cards, and documents. Since the physical media functions as an optical storage device, the
optical characteristics are very important. Specifically, the surface reflectivity of the physical media at a specific
optical wavelength and the radiation pattern are critical.
Surface reflectivity is defined by the amount of light reflected when an optical emitter irradiates the physical
media surface. As a general industry guideline, the physical media should reflect between 70% and 90% of the
incident light. A white physical media is generally used to achieve this high reflectivity. The reflected radiation
pattern is defined in terms of how the optical pattern leaves the physical media. A shiny surface results in a
narrow radiation pattern. A dull or matte surface produces a diffused, or broad, pattern. Narrow radiation
patterns can cause problems for scanners.
Another optical characteristic is the transparency of the physical media. If the physical media is too
transparent, the material underneath the label, card, or document affects the reflectivity. Paper bleed occurs
with transparent or translucent physical media. Paper bleed is caused by the scattering of incident light rays
within the physical media or from the underlying surface. This scattered light is picked up by the scanner
adding to the reflecting light off the physical media surface and increases the reflected signal. The result tends
to make the bars appear larger and the spaces appear narrower than what was actually printed.
Printers
A wide variety of printers can print bar codes. Both impact and non-impact printers are used to achieve low,
moderate, or high speed throughput. The types of printing technologies include — drum, daisywheel, dot
matrix, thermal, thermal transfer, ink jet, laser, electrostatic, letterpress, lithography, offset, gravure, and
flexography. The drum, dot matrix, thermal, and daisywheel printing systems are used for low to moderate
throughput applications. Ink jet, laser, electrostatic, and others, are used for high throughput. Regardless of the
printing technology used, print quality is the critical factor in producing machine readable bar code symbols.
Print quality is determined by the print mechanism, the physical media, and the marking agent. The major
factors influencing print quality are:
• Marking agent spread/shrink [BCOCA-2-031]
• Marking agent voids/specks [BCOCA-2-032]
• Marking agent smearing [BCOCA-2-033]
• Marking agent non-uniformity [BCOCA-2-034]
• Bar and space width tolerances [BCOCA-2-035]
• Bar edge roughness [BCOCA-2-036]
All of these factors are potential sources of system errors. They must be closely controlled to ensure readable
bar code symbols.


Scanners
Data stored in a bar code symbol is retrieved by the movement of an optical scanner across the symbol, or vice
versa. The scanner can be statically mounted, as in a conveyor system, or movable, as with a hand-held wand.
The scanner functions are the same.
Binary data encoded in the wide or narrow bars and spaces is extracted by the scanner's optical system. The
optical system consists of an emitter, a photodetector, and an optical lens. The emitter sends a beam of light
through the optical lens over the symbol, while the photodetector simultaneously responds to changes in the
reflected light levels. The photodetector produces a high output current when the reflected signal is large and a
low output current when the reflected signal is small. A low reflected signal occurs when the beam is over a
bar. Conversely, a high reflected signal occurs when the beam is over a space. These changes in current result
in an analog waveform. The waveform is processed by the decoder, that digitizes the information. The digitized
information is then sent to the host computer for processing.
The performance of bar code systems is generally described in terms of two parameters. The first parameter is
called the first read rate. The term is defined as the ratio of the number of good scans, or reads, to the number
of scan attempts. Typically, a good bar code system should have a first read rate of better than 80%. A low first
read rate is normally caused by a poorly printed symbol.
The second parameter used to evaluate system performance is the substitution error rate. This is the ratio of
the number of invalid, or incorrect, characters entered into the data base to the number of valid characters
entered. Substitution error rate is dependent on the structure of the bar code symbology, the quality of the
printed symbol, and the design of the decoding algorithm. [BCOCA-2-037]


