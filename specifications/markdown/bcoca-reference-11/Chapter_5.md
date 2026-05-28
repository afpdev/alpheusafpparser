Chapter 5. Exception Conditions
This chapter lists the BCOCA exception conditions required to be detected by the bar code object processor
when processing the bar code data structures and specifies the standard actions to be taken.
Note: The BCOCA data-check and specification-check exception conditions are registered in the exception
reporting chapter of the IPDS Reference. All new BCOCA exception conditions must also be registered
in the IPDS Reference in addition to being defined in this chapter.
Specification-Check Exceptions
A specification-check exception indicates that the bar code object processor has received a bar code request
with invalid or unsupported data parameters or values.
Exception Description
EC-0100 Retired item 4
EC-0200 Retired item 5
EC-0300 The bar code type specified in the BSD data structure is invalid or unsupported.
Standard Action: Terminate bar code object processing.
EC-0400 A font local ID specified in the BSD data structure is unsupported or not available.
For those symbologies that require a specific type style or code page for HRI, the BCOCA
receiver cannot determine the type style or code page of the specified font.
Standard Action: If the requested font is not available, a font substitution can be made
preserving as many characteristics as possible of the originally requested font while still
preserving the original code page. Otherwise, terminate bar code object processing.
Some bar code symbologies specify a set of type styles to be used for HRI data. Font
substitution for HRI data must follow the bar code symbology specification being used.
EC-0500 The color specified in the BSD data structure is invalid or unsupported.
Standard Action: The device default color is used.
EC-0505 The unit base specified in the BSD data structure is invalid or unsupported.
Standard Action: Terminate bar code object processing.
EC-0600 The module width specified in the BSD data structure is invalid or unsupported.
Standard Action: The bar code object processor uses the closest smaller width. If the smaller
value is less than the smallest supported width or zero, the bar code object processor uses the
smallest supported value.
EC-0605 The units per unit base specified in the BSD data structure is invalid or unsupported.
Standard Action: Terminate bar code object processing.
EC-0610 The desired-symbol-width parameter value is invalid.
Standard Action: Use a value of X'0000' for this parameter.
EC-0611 A desired symbol width was specified, but a bar code symbol cannot be generated that fits
within the specified width.
Standard Action: Use a value of X'0000' for the desired-symbol-width parameter; the resulting
symbol is larger than the desired symbol width. [BCOCA-5-001]


EC-0700 The element height specified in the BSD data structure is invalid or unsupported.
Standard Action: The bar code object processor uses the closest smaller height. If the smaller
value is less than the smallest supported element height or zero, the bar code object
processor uses the smallest supported value.
EC-0705 The presentation space extents specified in the BSD data structure are invalid or unsupported.
Standard Action: Terminate bar code object processing.
EC-0800 The height multiplier specified in the BSD data structure is invalid.
Standard Action: The bar code object processor uses X'01'.
EC-0805 The element height and height multiplier values specified are invalid for the selected GS1
DataBar modifier.
Standard Action: Use the smallest height defined for the GS1 DataBar modifier value.
EC-0900 The wide-to-narrow ratio specified in the BSD data structure is invalid or unsupported.
Standard Action: The bar code object processor uses the default wide-to-narrow ratio. The
default ratio is in the range of 2.25 through 3.00 to 1. The MSI (modified Plessey code) bar
code, however, uses a default wide-to-narrow ratio of 2.00 to 1.
EC-0A00 The bar code origin (X offset value or Y offset value) given in the BSA data structure is invalid
or unsupported.
Standard Action: Terminate bar code object processing.
EC-0B00 The bar code modifier in the BSD data structure is invalid or unsupported for the bar code type
specified in the same BSD.
Standard Action: Terminate bar code object processing.
EC-0C00 The length of the variable data specified in the BSA data structure plus any bar code object
processor generated check digits is invalid or unsupported.
Standard Action: Terminate bar code object processing.
EC-0D00 Retired item 6
EC-0E00 The first check-digit calculation resulted in a value of 10; this is defined as an exception
condition in some of the modifier options for MSI (modified Plessey code) bar codes in the
BSD data structure.
Standard Action: Terminate bar code object processing.
EC-0F00 Either the matrix row size value or the number of rows value specified in the BSA data
structure is unsupported. Both of these values must be within the range of supported sizes for
the symbology.
Standard Action: Use X'0000' for the unsupported value so that an appropriate size is used
based on the amount of symbol data.
EC-0F01 An invalid structured append sequence indicator was specified in the BSA data structure. For
a Data Matrix or QR Code symbol, the sequence indicator must be between 1 and 16
inclusive. For a MaxiCode symbol, the sequence indicator must be between 1 and 8 inclusive.
For an Aztec Code symbol, the sequence indicator must be between 1 and 26, inclusive.
Standard Action: Present the bar code symbol without structured append information.
EC-0F02 A structured append sequence indicator specified in the BSA data structure is larger than the
total number of structured append symbols.
Standard Action: Present the bar code symbol without structured append information.
Specification-Check Exceptions [BCOCA-5-002]


EC-0F03 Mismatched structured append information was specified in the BSA data structure. One of
the sequence-indicator and total-number-of-symbols parameters was X'00', but the other was
not X'00'.
Standard Action: Present the bar code symbol without structured append information.
EC-0F04 An invalid number of structured append symbols was specified in the BSA data structure. For
a Data Matrix or QR Code symbol, the total number of symbols must be between 2 and 16
inclusive. For a MaxiCode symbol, the total number of symbols must be between 2 and 8
inclusive. For an Aztec Code symbol, the total number of symbols must be between 2 and 26,
inclusive.
Standard Action: Present the bar code symbol without structured append information.
EC-0F05 For a MaxiCode symbol, the symbol mode value specified in the BSA data structure is invalid.
Standard Action: Terminate bar code object processing.
EC-0F06 For a PDF417 symbol, the number of data symbol characters per row value specified in the
BSA data structure is invalid.
Standard Action: Terminate bar code object processing.
EC-0F07 For a PDF417 symbol, the desired number of rows value specified in the BSA data structure is
invalid.
This exception condition can also occur when the number of rows times the number of data
symbol characters per row is greater than 928.
Standard Action: Proceed as if X'FF' was specified.
EC-0F08 For a PDF417 symbol, too much data was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F09 For a PDF417 symbol, the security level value specified in the BSA data structure is invalid.
Standard Action: Proceed as if security level 8 was specified.
EC-0F0A An incompatible combination of Data Matrix parameters was specified in the BSA data
structure. The following conditions can cause this exception condition:
• A structured append was specified (byte 10 not X'00'), but either the reader programming [BCOCA-5-003]
flag was set to B'1' or a hdr/trl macro was specified.
• The GS1 FNC1 flag was set to B'1', but either the industry FNC1 flag was set to B'1', the [BCOCA-5-004]
reader programming flag was set to B'1', or a hdr/trl macro was specified.
• The industry FNC1 flag was set to B'1', but either the GS1 FNC1 flag was set to B'1', the [BCOCA-5-005]
reader programming flag was set to B'1', or a hdr/trl macro was specified.
• The reader programming flag was set to B'1', but either a structured append was specified, [BCOCA-5-006]
one of the FNC1 flags was set to B'1', or a hdr/trl macro was specified.
• A hdr/trl macro was specified, but either a structured append was specified, one of the FNC1 [BCOCA-5-007]
flags was set to B'1', or the reader programming flag was set to B'1'.
Standard Action: Terminate bar code object processing.
EC-0F0B An invalid structured append file identification value was specified in the BSA data structure.
Each byte of the 2-byte file identification value must be in the range X'01'–X'FE'.
Standard Action: Present the bar code symbol without structured append information.
EC-0F0C A Macro PDF417 Control Block length value specified in the BSA data structure is invalid.
Standard Action: Terminate bar code object processing.
EC-0F0D Data within a Macro PDF417 Control Block specified in the BSA data structure is invalid.
Specification-Check Exceptions [BCOCA-5-008]


Standard Action: Present the bar code symbol without a Macro PDF417 Control Block.
EC-0F0E For a QR Code symbol, an invalid or unsupported conversion value was specified in the BSA
data structure.
Standard Action: Terminate bar code object processing.
EC-0F0F For a QR Code symbol, an invalid version value was specified in the BSA data structure.
Standard Action: Proceed as if X'00' had been specified.
EC-0F10 For a QR Code symbol, an invalid error-correction-level value was specified in the BSA data
structure.
Standard Action: Proceed as if X'03' had been specified.
EC-0F11 For a QR Code symbol, an invalid combination of special-function flags was specified in the
BSA data structure. Only one of the FNC1 flags can be B'1'.
Standard Action: Terminate bar code object processing.
EC-0F12 For a QR Code symbol, an invalid application-indicator value was specified in the BSA data
structure.
Standard Action: Terminate bar code processing.
EC-0F13 For an Intelligent Mail Package Barcode symbol, data within the USPS Service Banner string
specified in the BSA data structure is invalid or results in a USPS Service Banner that is too
long to fit in the prescribed width of the symbol.
Standard Action: Terminate bar code object processing.
EC-0F14 For an Intelligent Mail Package Barcode symbol, the USPS Service Banner is not suppressed
yet the Service Banner string provided has length 0.
Standard Action: Terminate bar code object processing.
EC-0F15 For an Intelligent Mail Package Barcode symbol, the length of the USPS Service Banner string
is not an even value.
Standard Action: Terminate bar code object processing.
EC-0F16 For a QR Code symbol, too much data was specified in the BSA data structure, and the too
much data flag forbid making the version bigger to fit the data.
Standard Action: Terminate bar code object processing.
EC-0F17 For an Aztec Code symbol, too much data was specified in the BSA data structure, and the
too much data flag forbid making the version bigger to fit the data.
Standard Action: Terminate bar code object processing.
EC-0F18 For an Aztec Code symbol, an invalid desired-number-of-layers value was specified in the
BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F19 For an Aztec Code symbol, an invalid error-correction-level value was specified in the BSA
data structure.
Standard Action: Terminate bar code object processing.
EC-0F1A For an Aztec Code symbol, an invalid combination of special-function flags was specified in
the BSA data structure. Only one of the FNC1 flags can be B'1'.
Standard Action: Terminate bar code object processing.
EC-0F1B For an Aztec Code symbol, an invalid application-indicator value was specified in the BSA
data structure.
Standard Action: Terminate bar code object processing.
Specification-Check Exceptions [BCOCA-5-009]


EC-0F1C For an Aztec Code symbol, the structured-append-ID-length value was not X'00' for a symbol
that was not part of a structured append.
Standard Action: Terminate bar code object processing.
EC-0F1D For an Aztec Code symbol, the structured append ID contains an invalid character.
Standard Action: Terminate bar code object processing.
EC-0F1E For an Aztec Code symbol, too much data was specified in the BSA data structure to be able
to fit the resulting codewords, in combination with the required error-correction codewords,
into a reader-initialization symbol.
Standard Action: Terminate bar code object processing.
EC-0F20 For a Data Matrix symbol, too much data was specified in the BSA data structure, and the too
much data flag forbid making the symbol bigger to fit the data.
Standard Action: Terminate bar code object processing.
EC-0F21 For a Han Xin Code symbol, too much data was specified in the BSA data structure, and the
too much data flag forbid making the version bigger to fit the data.
Standard Action: Terminate bar code object processing.
EC-0F22 For a Han Xin Code symbol, an invalid version value was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F23 For a Han Xin Code symbol, an invalid error-correction-level value was specified in the BSA
data structure.
Standard Action: Terminate bar code object processing.
EC-0F24 For a Han Xin Code symbol, an invalid combination of special-function flags was specified in
the BSA data structure. Only one of the FNC1 flags can be B'1'.
Standard Action: Terminate bar code object processing.
EC-0F25 For a Han Xin Code symbol, an invalid application-indicator value was specified in the BSA
data structure.
Standard Action: Terminate bar code object processing.
EC-0F30 For a QR Code with Image bar code, the image information length specified in the BSA data
structure was either invalid or was too large to fit into the repeating groups total length.
Standard Action: Terminate bar code object processing.
EC-0F31 For a QR Code with Image bar code, an invalid image local ID value was specified in the BSA
data structure: the value must be in the range X'0000'–X'7FFF'.
Standard Action: Terminate bar code object processing.
EC-0F32 For a QR Code with Image bar code, an invalid or unsupported unit-base value for the image-
object-area offset was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F33 For a QR Code with Image bar code, an invalid or unsupported units-per-unit-base value for
the image-object-area offset was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F34 For a QR Code with Image bar code, an invalid or unsupported X or Y offset value for the
image-object-area origin was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
Specification-Check Exceptions [BCOCA-5-010]


EC-0F35 For a QR Code with Image bar code, an invalid or unsupported image-object-area orientation
was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F36 For a QR Code with Image bar code, an invalid or unsupported image-object-area reference
coordinate system was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F37 For a QR Code with Image bar code, an invalid or unsupported unit-base value for the image-
object-area extent was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F38 For a QR Code with Image bar code, an invalid or unsupported units-per-unit-base value for
the image-object-area extent was specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F39 For a QR Code with Image bar code, an invalid or unsupported X or Y extent value was
specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F3A For a QR Code with Image bar code, an invalid or unsupported mapping option value was
specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-0F3B For a QR Code with Image bar code, an invalid repeating groups total length value was
specified in the BSA data structure.
Standard Action: Terminate bar code object processing.
EC-1000 The human-readable interpretation location specified in the BSA data structure is invalid.
Standard Action: Terminate bar code object processing.
EC-1100 A portion of the bar code, including the bar and space patterns, the Bearer Bars (Interleaved
2-of-5), the Identification Bars and USPS Banner (Intelligent Mail Container Barcode or
Intelligent Mail Package Barcode), the RED TAG indicator (Royal Mail RED TAG
(deprecated)), the zipper pattern and contrast block (MaxiCode), any image printed in
conjunction with a QR Code symbol (QR Code with Image), and the HRI, extends outside of
either:
• The bar code presentation space [BCOCA-5-011]
• The intersection of the mapped bar code presentation space and the controlling [BCOCA-5-012]
environment object area
• The maximum presentation area. [BCOCA-5-013]
Standard Action: Terminate bar code object processing.
All bar code symbols must be presented in their entirety. Whenever a partial bar code pattern
is presented, for whatever reason, it is obscured to make it unscannable.
EC-1200 Invalid data was encountered in a GS1 DataBar Expanded, GS1 DataBar Expanded Stacked,
GS1-128, or UCC/EAN 128 or symbol; one or more of the following conditions was
encountered:
• FNC1 is not the first data character (for UCC/EAN 128 symbols only) [BCOCA-5-014]
• Invalid application identifier (ai) value encountered [BCOCA-5-015]
• Data for an ai doesn't match the ai definition [BCOCA-5-016]
• Insufficient (or no) data following an ai [BCOCA-5-017]
• T oo much data for an ai [BCOCA-5-018]
• Invalid use of FNC1 character [BCOCA-5-019]
Specification-Check Exceptions [BCOCA-5-020]


Standard Action: Terminate bar code object processing.
EC-1201 Within a Data Matrix bar code object, a C40, T ext, X12, or EDIFACT encodation scheme was
selected and a character was encountered within the bar code data that is not valid for that
encodation scheme. These encodation schemes do not support all 256 possible input
characters.
Standard Action: Produce the bar code symbol using the auto-encoding encodation scheme.
EC-1202 Invalid or insufficient data was encountered in a Royal Mail RED TAG (deprecated) bar code
object. There must be exactly 21 numeric digits in the input data.
Standard Action: Terminate bar code object processing.
EC-1203 Invalid or insufficient data was encountered in an Intelligent Mail Container Barcode object.
There must be exactly 22 characters in the input data that are within the field ranges shown in
T able 14 .
Standard Action: Terminate bar code object processing.
EC-1204 Invalid, insufficient, or too much data was encountered in a Royal Mail Mailmark bar code. The
valid data for the parameters of each modifier (X'00'–X'01') is defined within the Royal Mail
Mailmark Definition Document.
Standard Action: Terminate bar code object processing.
EC-1205 Invalid or insufficient data was encountered in an Intelligent Mail Package Barcode object.
There must be exactly 22, 26, 30, or 34 numeric characters in the input data.
Standard Action: Terminate bar code object processing.
Data-Check Exceptions
A data-check exception indicates that the bar code object processor has detected an undefined character.
Exception Description
EC-2100 An invalid or undefined character, according to the rules of the symbology specification, has
been detected in the bar code data.
Standard Action: Terminate bar code object processing.
Data-Check Exceptions [BCOCA-5-021]




Copyright © AFP Consortium 1991, 2025 169
