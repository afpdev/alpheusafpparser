Chapter 4. BCOCA Data Structures
This chapter contains the BCOCA data structures, fields, and valid data definitions. Two data structures are
described: the Bar Code Symbol Descriptor (BSD) and the Bar Code Symbol Data (BSA).
BCD1 Subset
The BCOCA architecture provides a wide range of bar code function to cover many different symbologies that
are defined for a variety of uses. Not all of the defined BCOCA function is supported by all BCOCA receivers.
A subset of the full capabilities of the BCOCA architecture, called BCD1, is defined to specify the minimum
support required of all BCOCA receivers. Each field within a BCOCA data structure allows a range of possible
values that is shown in the Range column of the syntax table; the BCD1 Range column specifies the values
that every receiver supports. Most receivers support more than the minimum ranges.
BCD2 Subset
BCD2 is a superset of BCD1 that provides additional function and bar code types that are required by the
MO:DCA IS/3 interchange set. In particular, BCD2 adds the following functions:
• Additional bar code types: [BCOCA-4-001]
Australia Post Bar Code
Codabar
Code 93
Code 128, modifiers X'02' and X'03'
Data Matrix, modifier X'00' (2D bar code)
Intelligent Mail Barcode
Japan Postal Bar Code
MaxiCode (2D bar code)
PDF417 (2D bar code)
QR Code, modifier X'02' (2D bar code)
RM4SCC (Royal Mail and Dutch KIX)
• Bar code symbol suppression [BCOCA-4-002]
• Color specification triplet in the MO:DCA and IPDS Bar Code Data Descriptor [BCOCA-4-003]
• Full range for font local IDs [BCOCA-4-004]
• Full range for units per unit base [BCOCA-4-005]
The AFP Consortium recommends that BCOCA implementations support at least the function defined for
BCD2.




Figure 10. BCOCA Function and Subsetting
BCOCA
BCD2
BCD1Bar Code Types and Modifiers:
Code 39 (3-of-9 Code), AIM USS-39
EAN 8 (includes JAN-short)
EAN 13 (includes JAN-standard)
EAN Five-digit Supplemental - modifier X'00'
EAN Two-digit Supplemental - modifier X'00'
Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 - modifiers X'01' and X'02'
MSI (modified Plessey code)
UPC/CGPC Version A
UPC/CGPC Version E
UPC Five-digit Supplemental - modifier X'00'
UPC Two-digit Supplemental - modifier X'00'
Additional Function:
Zero-degree object-area orientation support
Bar Code Types and Modifiers:
Australia Post Bar Code
Codabar, 2-of-7, AIM USS-Codabar
Code 93
Code 128 - AIM USS-128 - modifier X'02'
Code 128 - UCC/EAN 128 - modifier X'03'
Data Matrix, GS1 DataMatrix (2D bar code) - modifier X'00'
Intelligent Mail Barcode
Japan Postal Bar Code
MaxiCode (2D bar code)
PDF417 (2D bar code)
QR Code (2D bar code) - modifier X'02'
RM4SCC - modifier X'00'
RM4SCC - Dutch KIX - modifier X'01'
Additional Function:
Extended bar code color support
Full range for font local IDs
Full range for units per unit base
Symbol suppression
Bar Code Types and Modifiers:
Aztec Code (2D bar code)
Bearer Bars - Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 - modifiers X'03' and X'04'
Code 128 - GS1-128, UCC/EAN 128 - modifier X'04'
Code 128 - Intelligent Mail Container Barcode - modifier X'05'
Code 128 - Intelligent Mail Package Barcode - modifier X'06’
Data Matrix, GS1 DataMatrix (2D bar code) - modifier X'01'
EAN Five-digit Supplemental - modifier X'01'
EAN Two-digit Supplemental - modifier X'01'
GS1 DataBar
Han Xin Code (2D bar code)
Industrial 2-of-5
Matrix 2-of-5
POSTNET (deprecated) - modifiers X'00' through X'03'
POSTNET (deprecated) - PLANET (deprecated) - modifier X'04'
QR Code with Image (2D bar code) - modifier X'12'
Royal Mail Mailmark
Royal Mail RED TAG (deprecated)
UPC Two-digit Supplemental - modifiers X'01' and X'02'
UPC Five-digit Supplemental - modifiers X'01' and X'02'
Additional Function:
0 , 90 , 180 , and 270 object-area orientation support [BCOCA-4-006]
o o o o
All values of degrees and minutes for object-area orientation
Bar code objects may be sent in any order
Desired method of adjusting for trailing blanks
Desired symbol width
Small-symbol support
Standard OCA color support
User control of Data Matrix encodation scheme
BCOCAKey:
BCD2
BCD1
BCOCA Data StructuresThe BSD specifies the size of the bar code presentation space, the type of bar code to be generated, and the
parameters used to generate the bar code symbols. [BCOCA-4-007]
### Table 8. Bar Code Symbol Descriptor (BSD) Data Structure

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' | X'00' [BCOCA-4-008]|
| 1 | | | X'00' | Reserved | X'00' | X'00' [BCOCA-4-009]|
| 2–3 | UBIN | $X_{UPUB}$ | X'0001'–X'7FFF' | Units per unit base in the $X_{bc}$ direction | X'3840' | X'0001'–X'7FFF' [BCOCA-4-010]|
| 4–5 | UBIN | $Y_{UPUB}$ | X'0001'–X'7FFF' | Units per unit base in the $Y_{bc}$ direction; must be the same as $X_{UPUB}$ | X'3840' | X'0001'–X'7FFF' [BCOCA-4-011]|
| 6–7 | UBIN | $X$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Width of bar code presentation space in L-units<br>Default | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [BCOCA-4-012]|
| 8–9 | UBIN | $Y$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Length of bar code presentation space in L-units<br>Default | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [BCOCA-4-013]|
| 10–11 | UBIN | Symbol width | X'0000'<br>X'0001'–X'7FFF' | Desired symbol width:<br>Not specified (use module width)<br>Desired width of symbol in L-units<br>Not supported by all BCOCA receivers | X'0000' | X'0000' [BCOCA-4-014]|
| 12 | CODE | Type | X'01'–X'03',<br>X'05'–X'0D',<br>X'11',<br>X'16'–X'18',<br>X'1A'–X'27' | Bar code type | Specified in Table 9 | Specified in Table 9 [BCOCA-4-015]|
| 13 | CODE | Modifier | See field description | Bar code modifier | Specified in Table 10 | Specified in Table 10 [BCOCA-4-016]|
| 14 | CODE | Local ID | X'00'–X'FE'<br>X'FF' | Font Local ID for HRI<br>Default | X'01'–X'7F'<br>X'FF' | X'00'–X'FE'<br>X'FF' [BCOCA-4-017]|
| 15–16 | CODE | Color | X'0000'–X'0010'<br>X'FF00'–X'FF08'<br>X'FFFF' | Color | X'FF07' | X'FF07' [BCOCA-4-018]|
| 17 | UBIN | Module width | X'01'–X'FE'<br>X'FF' | Module width in mils<br>Default | Device specific<br>X'FF' | Device specific<br>X'FF' [BCOCA-4-019]|
| 18–19 | UBIN | Element height | X'0001'–X'7FFF'<br>X'FFFF' | Element height in L-units<br>Default | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [BCOCA-4-020]|
| 20 | UBIN | Multiplier | X'01'–X'FF' | Height multiplier | X'01'–X'FF' | X'01'–X'FF' [BCOCA-4-021]|
| 21–22 | UBIN | WE:NE | X'0000'<br>X'0001'–X'7FFF'<br>X'FFFF' | Bar code (see byte 12) does not use ratio<br>Wide-to-narrow ratio<br>Default | X'0000'<br>At least one value<br>X'FFFF' | X'0000'<br>At least one value<br>X'FFFF' [BCOCA-4-022]|
Note: The BCD1 and BCD2 range for these fields has been specified assuming a unit of measure of 1/1440 of
an inch. Many receivers support the BCD1 or BCD2 subset plus additional function. If a receiver
supports additional units of measure, the BCOCA architecture requires the receiver to at least support a
range equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-unit Range Conversion Algorithm” on
page 21.
The following is a description of the fields defined in the BSD data structure and applicable exception
conditions. Unless explicitly specified, the standard action to be taken for all exception conditions is to report
the exception condition, terminate the bar code object processing, and continue processing with the next
object.
Byte 0 Unit base
Indicates the length of the measurement unit base. The value X'00' indicates that the
measurement unit base is ten inches. The value X'01' indicates that the measurement unit
base is ten centimeters. Exception condition EC-0505 exists if the unit base specified is invalid
or unsupported.
The value X'02' is retired as Retired item 1.
Byte 1 Reserved
Bytes 2–3 $X_{UPUB}$
Specifies the number of units per unit base in the $X_{bc}$ direction. Exception condition EC-0605
exists if the units per unit base value specified is invalid or unsupported.
Bytes 4–5 $Y_{UPUB}$
Specifies the number of units per unit base in the $Y_{bc}$ direction and must be equal to the value
specified in $X_{UPUB}$. Exception condition EC-0605 exists if the units per unit base value
specified is invalid or unsupported.
Bytes 6–7 $X$ extent
Specifies the width in the $X_{bc}$ direction of the presentation space in L-units. The measurement
base is specified in bytes 0–5. A value of X'FFFF' indicates that the width of the controlling
environment area in the $X_{bc}$ direction is to be used. Exception condition EC-0705 exists if the
presentation space extent specified is invalid or unsupported.
Note: The size of a bar code symbol is not always known in advance. It is good practice to
specify the size of the bar code presentation space large enough to include plenty of
white space around the expected symbols and HRI. [BCOCA-4-023]



Bytes 8–9 $Y$ extent
Specifies the length in the $Y_{bc}$ direction of the presentation space in L-units. The measurement
base is specified in bytes 0–5. A value of X'FFFF' indicates that the length of the controlling
environment area in the $Y_{bc}$ direction is to be used. Exception condition EC-0705 exists if the
presentation space extent specified is invalid or unsupported.
Bytes 10–11 Desired symbol width (not supported by all BCOCA receivers)
Note: This is an optional parameter that is not supported by all BCOCA receivers; this
parameter is ignored by products that do not support this function. IPDS printers report
support for this function with property pair X'1302'.
Specifies a desired width for the entire bar code symbol in L-units. The measurement base is
specified in bytes 0–5. A value of X'0000' indicates that the width of the symbol is determined
by other BSD parameters (module width, WE:NE, and amount of data). For BCOCA receivers
that support the desired symbol width parameter, exception condition EC-0610 exists if the
specified value is invalid.
The quiet zone is not included in the symbol width for most bar code types. However, when
Bearer Bars are used with an Interleaved 2-of-5 symbol, the symbol width includes the quiet
zone on both ends of the symbol and also the width of the vertical Bearer Bars (if present).
The BCOCA receiver will use the desired symbol width value to attempt to create the widest
bar code symbol that fits within the desired symbol width. The BCOCA receiver does this by:
1. Ignoring the specified module width value (byte 17) [BCOCA-4-024]
2. Calculating an optimal module width value that will produce the widest symbol that fits into [BCOCA-4-025]
the desired width. The following algorithm is used for all symbologies except for fixed-size
symbols:
a. First the BCOCA receiver calculates how many X values there will be in the symbol
and divides this total into the desired symbol width producing a target X value. X is the
term used to describe the intended width of a bar code's narrowest element (a bar or a
2D module; spaces are also measured in X values). Wide elements are multiples of
the narrow element. For symbologies that use a wide-to-narrow ratio (WE:NE), the
multiple is not necessarily an integer value.
b. Then the target value is converted into printer pel units and adjusted by rounding down
to the nearest pel. If the result is larger than the maximum supported module width,
the maximum supported module width is used.
Exception EC-0611 exists if the result is smaller than the minimum supported module
width. The standard action for this exception condition is to produce a bar code symbol
using the module width value (byte 17); this symbol will be larger than the desired
symbol width.
c. The resulting value replaces the module width value within the BSD and the symbol is
generated using that value and all of the other user-specified BSD values to produce
the requested symbol. The resulting symbol might be smaller than the desired symbol
width.
3. For fixed-size symbols, the optimal-symbol-size value is used unless the BCOCA receiver [BCOCA-4-026]
provides small-symbol support (in which case the value used can be either the optimal or
the small value, whichever is best for producing a symbol close to the desired symbol
width). Exception condition EC-0611 exists if the resulting fixed-size symbol is wider than
the desired symbol width.
The fixed-size bar code types are: Australia Post Bar Code, Dutch KIX, Intelligent Mail
Barcode, MaxiCode, PLANET (deprecated), POSTNET (deprecated), RM4SCC, Royal
Mail RED TAG (deprecated), and Royal Mail Mailmark. [BCOCA-4-027]



4. For UPC or EAN symbols that include a supplemental (bar code types X'06', X'07', X'16', [BCOCA-4-028]
X'17' with modifier X'01' or X'02'), the desired symbol width includes both the base symbol
and the supplemental.
Note: When a non-zero value is specified in the desired-symbol-width field, an appropriate
module-width value should also be specified in byte 17 (a good choice is X'FF' to select
the default module width). The module-width value is used in the following cases:
• When the standard action for exception EC-0611 is taken because the printer cannot [BCOCA-4-029]
generate a symbol that fits within the desired width.
• When the bar code object is sent to a BCOCA receiver that does not support the [BCOCA-4-030]
desired-symbol-width parameter.
• When X'0000' is specified in the desired-symbol-width field. [BCOCA-4-031]
Byte 12 Type
Indicates the type of bar code symbol to be generated. Exception condition EC-0300 exists if
the bar code type value is invalid or unsupported. Exception condition EC-1100 exists if a
portion of the bar code symbol extends beyond the bar code presentation space, the
intersection of the mapped bar code presentation space and the controlling environment
object area, or beyond the maximum presentation area.
The bar code types are defined as follows: [BCOCA-4-032]
### Table 9. Bar Code Types

| Type | Bar Code Symbology | In BCD1 Subset? | In BCD2 Subset? |
| :--- | :--- | :--- | :--- |
| X'01' | Code 39 (3-of-9 Code), AIM USS-39 | Yes | Yes [BCOCA-4-033]|
| X'02' | MSI (modified Plessey code) | Yes | Yes [BCOCA-4-034]|
| X'03' | UPC/CGPC—Version A | Yes | Yes [BCOCA-4-035]|
| X'05' | UPC/CGPC—Version E | Yes | Yes [BCOCA-4-036]|
| X'06' | UPC—Two-Digit Supplemental (Periodicals) | Yes | Yes [BCOCA-4-037]|
| X'07' | UPC—Five-Digit Supplemental (Paperbacks) | Yes | Yes [BCOCA-4-038]|
| X'08' | EAN-8 (includes JAN-short) | Yes | Yes [BCOCA-4-039]|
| X'09' | EAN-13 (includes JAN-standard) | Yes | Yes [BCOCA-4-040]|
| X'0A' | Industrial 2-of-5 | No | No [BCOCA-4-041]|
| X'0B' | Matrix 2-of-5 | No | No [BCOCA-4-042]|
| X'0C' | Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 | Yes | Yes [BCOCA-4-043]|
| X'0D' | Codabar, 2-of-7, AIM USS-Codabar | No | Yes [BCOCA-4-044]|
| X'11' | Code 128, GS1-128, UCC/EAN 128, AIM USS-128, Intelligent Mail Container Barcode, Intelligent Mail Package Barcode | No | Yes [BCOCA-4-045]|
| X'16' | EAN Two-Digit Supplemental | Yes | Yes [BCOCA-4-046]|
| X'17' | EAN Five-Digit Supplemental | Yes | Yes [BCOCA-4-047]|
| X'18' | POSTNET (deprecated) and PLANET (deprecated) | No | No [BCOCA-4-048]|
| X'1A' | RM4SCC and Dutch KIX | No | Yes [BCOCA-4-049]|
| X'1B' | Japan Postal Bar Code | No | Yes [BCOCA-4-050]|
| X'1C' | Data Matrix, GS1 DataMatrix (2D bar code) | No | Yes [BCOCA-4-051]|
| X'1D' | MaxiCode (2D bar code) | No | Yes [BCOCA-4-052]|
| X'1E' | PDF417 (2D bar code) | No | Yes [BCOCA-4-053]|
| X'1F' | Australia Post Bar Code | No | Yes [BCOCA-4-054]|
| X'20' | QR Code, QR Code with Image (2D bar code) | No | Yes [BCOCA-4-055]|
| X'21' | Code 93 | No | Yes [BCOCA-4-056]|
| X'22' | Intelligent Mail Barcode | No | Yes [BCOCA-4-057]|
| X'23' | Royal Mail RED TAG (deprecated) | No | No [BCOCA-4-058]|
| X'24' | GS1 DataBar | No | No [BCOCA-4-059]|
| X'25' | Royal Mail Mailmark | No | No [BCOCA-4-060]|
| X'26' | Aztec Code | No | No [BCOCA-4-061]|
| X'27' | Han Xin Code | No | No [BCOCA-4-062]|

**Retired Bar Code Types** [BCOCA-4-063]

| Type | Bar Code Symbology | In BCD1 Subset? | In BCD2 Subset? |
| :--- | :--- | :--- | :--- |
| X'04' | Retired item 7 | No | No [BCOCA-4-064]|
| X'0E' | Retired item 10 | No | No [BCOCA-4-065]|
| X'0F' | Retired item 11 | No | No [BCOCA-4-066]|
| X'10' | Retired item 12 | No | No [BCOCA-4-067]|
| X'12' | Retired item 13 | No | No [BCOCA-4-068]|
| X'13' | Retired item 14 | No | No [BCOCA-4-069]|
| X'14' | Retired item 15 | No | No [BCOCA-4-070]|
| X'15' | Retired item 16 | No | No [BCOCA-4-071]|
| X'19' | Retired item 19 | No | No [BCOCA-4-072]|
| X'EC' | Retired item 22 | No | No [BCOCA-4-073]|
| X'ED' | Retired item 23 | No | No [BCOCA-4-074]|
| X'EE' | Retired item 24 | No | No [BCOCA-4-075]|
| X'EF' | Retired item 25 | No | No [BCOCA-4-076]|
Note: In the table above, when a given bar code type is said to be in a subset, that means that
at least one combination of that bar code type and some modifier value (byte 13) is in
the subset.



### Byte 13 Modifier
The modifier field gives additional processing information about the bar code symbol to be
generated. For example, it indicates whether a check-digit is to be generated for the bar code
symbol. The check digit algorithm and placement are defined in “Check Digit Calculation
Methods”. Exception condition EC-0B00 exists if the bar code modifier is invalid or
unsupported for the bar code type specified.
Table 10 defines the BCD1 and BCD2 bar code modifier codes that must be supported for
each bar code type specified.
Table 10. Modifier Values by Bar Code Type
Type Bar Code Symbology Modifier Value
(byte 13)
In BCD1
Subset?
In BCD2
Subset?
X'01' Code 39 (3-of-9 Code), AIM USS-39 X'01' and X'02' Yes Yes
X'02' MSI (modified Plessey code) X'01' through X'09' Yes Yes
X'03' UPC/CGPC Version A X'00' Yes Yes
X'05' UPC/CGPC Version E X'00' Yes Yes
X'06' UPC - Two-Digit Supplemental X'00' Yes Yes
X'01' and X'02' No No
X'07' UPC - Five-Digit Supplemental X'00' Yes Yes
X'01' and X'02' No No
X'08' EAN 8 (includes JAN-short) X'00' Yes Yes
X'09' EAN 13 (includes JAN-standard) X'00' Yes Yes
X'0A' Industrial 2-of-5 X'01' and X'02' No No
X'0B' Matrix 2-of-5 X'01' and X'02' No No
X'0C' Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 X'01' through X'02' Yes Yes
X'03' through X'04' No No
X'0D' Codabar, 2-of-7, AIM USS-Codabar X'01' and X'02' No Yes
X'11' Code 128, UCC/EAN 128, AIM USS-128 X'02' through X'03' No Yes
GS1-128, UCC/EAN 128 X'04' No No
Intelligent Mail Container Barcode X'05' No No
Intelligent Mail Package Barcode X'06' No No
X'16' EAN Two-Digit Supplemental X'00' Yes Yes
X'01' No No
X'17' EAN Five-Digit Supplemental X'00' Yes Yes
X'01' No No
X'18' POSTNET (deprecated) and
PLANET (deprecated)
X'00' through X'04' No No
X'1A' RM4SCC and Dutch KIX X'00' and X'01' No Yes
X'1B' Japan Postal Bar Code X'00' and X'01' No Yes [BCOCA-4-077]



Table 10 Modifier Values by Bar Code Type (cont'd.)
Type Bar Code Symbology Modifier Value
(byte 13)
In BCD1
Subset?
In BCD2
Subset?
X'1C' Data Matrix, GS1 DataMatrix (2D bar code) X'00' No Yes
Data Matrix, GS1 DataMatrix, including
DMRE symbols (2D bar code)
X'01' No No
X'1D' MaxiCode (2D bar code) X'00' No Yes
X'1E' PDF417 (2D bar code) X'00' and X'01' No Yes
X'1F' Australia Post Bar Code X'01' through X'08' No Yes
X'20' QR Code (2D bar code) X'02' No Yes
QR Code with Image (2D bar code) X'12' No No
X'21' Code 93 X'00' No Yes
X'22' Intelligent Mail Barcode X'00' through X'03' No Yes
X'23' Royal Mail RED TAG (deprecated) X'00' No No
X'24' GS1 DataBar X'00' through X'04'
X'11' through X'1B'
No No
X'25' Royal Mail Mailmark X'00' and X'01' No No
X'26' Aztec Code X'00' through X'03' No No
X'27'
Han Xin Code X'00' No No
Retired Bar Code Modifier Values
X'04' Retired item 7 X'00' through X'04' No No
X'0E' Retired item 10 X'00' No No
X'0F' Retired item 11 X'00' No No
X'10' Retired item 12 X'01' and X'02' No No
X'11' Retired item 20 X'01' No No
X'12' Retired item 13 X'01' and X'02' No No
X'13' Retired item 14 X'01' through X'03' No No
X'14' Retired item 15 X'00' No No
X'15' Retired item 16 X'01' and X'02' No No
X'16' Retired item 17 X'02' through X'03' No No
X'17' Retired item 18 X'02' through X'03' No No
X'19' Retired item 19 X'00' through X'03' No No
X'EC' Retired item 22 X'02' No No
X'ED' Retired item 23 X'00' No No
X'EE' Retired item 24 X'00' No No
X'EF' Retired item 25 X'00' and X'01' No No
Refer to “Bar Code Type and Modifier Descriptions” for a detailed description of
each bar code type and modifier combination. [BCOCA-4-078]



### Byte 14 Local ID
Specifies the local ID of a font to be used when HRI is requested. A value of X'FF' indicates
that a presentation device selected font is to be used. Since most BCOCA receivers provide
resident font resources for use with the supported bar code symbologies, specifying a local ID
of X'FF' is recommended.
Some bar code symbology specifications do not specify a type style for HRI information.
However, the UPC and EAN symbologies specify OCR-B for HRI; refer to Table 34 . The location of the HRI is specified and varies depending on the symbology selected.
For bar code types that do not allow HRI information, this field is ignored; these are: Aztec
Code, Data Matrix, Han Xin Code,
Japan Postal Bar Code, MaxiCode, PDF417, POSTNET
(deprecated), QR Code, QR Code with Image, RM4SCC, Royal Mail RED TAG (deprecated),
and Royal Mail Mailmark.
For those symbologies that require a specific type style or code page for HRI, exception
condition EC-0400 exists if the printer cannot determine the type style or code page of the
specified font.
Notes:
1. Specifying LID = X'FF' is the easiest way to guarantee that a proper font is selected. If [BCOCA-4-079]
another LID is specified, the font must be appropriate for the specified symbology; using a
printer-resident font is recommended in this case.
2. Not all printers can determine the type style or code page of a coded font from the IPDS [BCOCA-4-080]
LFC, LF , LFI, LSS, LCPC, LCP , or LFCSC commands.
Exception condition EC-0400 exists if a local ID is unsupported or the font is not available. If
the requested font is not available, a substitution can be made that preserves as many
characteristics as possible of the originally requested font; the code page selected must be a
superset of the requested code page. Otherwise, terminate bar code object processing and
continue with the next object.
Some bar code symbologies specify a set of type styles to be used for HRI data. Font
substitution for HRI data must follow the bar code symbology specification being used. [BCOCA-4-081]



Bytes 15–16 Color
Specifies the color in which the bars of the bar code symbol and the HRI is to be presented
(note 4 describes another way to specify color). Valid values for specifying color
include the OCA standard color values (X'0000'–X'0010' and X'FF00'–X'FF08') shown in Table
11 and the special value X'FFFF' that selects the device default color. Exception condition EC- [BCOCA-4-082]
0500 exists if the color specified is invalid or unsupported. If the color is unsupported, the [BCOCA-4-083]
presentation device default color is used. Some devices simulate an unsupported color
without reporting an exception condition.
The specified color value is applied to foreground areas of the bar code presentation space.
Foreground areas consist of the following:
– Bars and 2D modules
– Stroked and filled portion of HRI characters
All other areas of the bar code presentation space are background. [BCOCA-4-084]
### Table 11. Standard OCA Color-Value Table
| Value | Color | Red (R) | Green (G) | Blue (B) |
| :--- | :--- | :---: | :---: | :---: |
| X'0000' or X'FF00' | Device default [BCOCA-4-085]| | | |
| X'0001' or X'FF01' | Blue | 0 | 0 | 255 [BCOCA-4-086]|
| X'0002' or X'FF02' | Red | 255 | 0 | 0 [BCOCA-4-087]|
| X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 [BCOCA-4-088]|
| X'0004' or X'FF04' | Green | 0 | 255 | 0 [BCOCA-4-089]|
| X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 [BCOCA-4-090]|
| X'0006' or X'FF06' | Yellow | 255 | 255 | 0 [BCOCA-4-091]|
| X'0007' | White; see note 1 | 255 | 255 | 255 [BCOCA-4-092]|
| X'0008' | Black; see note 2 | 0 | 0 | 0 [BCOCA-4-093]|
| X'0009' | Dark blue | 0 | 0 | 170 [BCOCA-4-094]|
| X'000A' | Orange | 255 | 128 | 0 [BCOCA-4-095]|
| X'000B' | Purple | 170 | 0 | 170 [BCOCA-4-096]|
| X'000C' | Dark green | 0 | 146 | 0 [BCOCA-4-097]|
| X'000D' | Dark turquoise | 0 | 146 | 170 [BCOCA-4-098]|
| X'000E' | Mustard | 196 | 160 | 32 [BCOCA-4-099]|
| X'000F' | Gray | 131 | 131 | 131 [BCOCA-4-100]|
| X'0010' | Brown | 144 | 48 | 0 [BCOCA-4-101]|
| X'FF07' | Device default [BCOCA-4-102]| | | |
| X'FF08' | Color of medium; also known as reset color [BCOCA-4-103]| | | |
Note: The table specifies the RGB values for each named color; the actual printed color is device dependent.
Notes:
1. The color rendered on presentation devices that do not support white is device dependent. [BCOCA-4-104]
For example, some printers simulate with color of medium, which results in white when
white media is used.
2. It is recommended that OCA Black (X'0008') be rendered as C=M=Y= X'00' and K = X'FF'. [BCOCA-4-105]



3. Some symbologies, such as Data Matrix, allow the bar code symbol to be presented in a [BCOCA-4-106]
reverse video manner (light modules on a dark background). T o achieve this effect, color
the bar code object area with a dark color and specify color of medium (X'FF08') for the
symbol color. In a MO:DCA environment, the bar code object area can be colored using a
Color Specification triplet in the Object Area Descriptor. In an IPDS environment, the bar
code object area can be colored using a Color Specification triplet in the Bar Code Output
Control.
4. In some environments, such as AFP Line Data, IPDS, and MO:DCA environments, colors [BCOCA-4-107]
for the bar code symbol and HRI (using an RGB, CMYK, highlight, or CIELAB color value)
can be specified with a Color Specification (X'4E') triplet. In this case, the Color
Specification triplet overrides the color value specified in BSD bytes 15-16. Refer to
Appendix C, “IPDS Environment”, and Appendix B, “MO:DCA Environment”, for more information about color specification in these environments.
5. Neither the color specified in the BSD, bytes 15–16, or in the Color Specification (X'4E') [BCOCA-4-108]
triplet discussed in the previous note, have any effect on the color of an image in a QR
Code with Image bar code.
Byte 17 Module width
This parameter specifies the width in mils (thousandths of an inch) of the smallest defined bar
code element (bar, space, or 2D module). Some bar code symbologies refer to this value as
the unit or X-dimension width. The widths of all symbol elements are normally expressed as
multiples (not necessarily integer multiples) of the module width. A value of X'FF' indicates the
default module width of the presentation device is to be used; refer to Table 13 for
a list of recommended default values. Exception condition EC-0600 exists if the module width
specified is invalid or unsupported. For this condition, the bar code object processor uses the
closest smaller width. If the smaller value is less than the smallest supported width or zero, the
bar code object processor uses the smallest supported value.
Note: Most BCOCA implementations support a limited module-width range because device
resolution does not allow very small symbols to be accurately produced. The limitations
are symbology specific and are commonly in the range 9–36 mils for UPC and EAN
symbols and 7–254 mils for most other symbologies; refer to your product
documentation for specific ranges supported.
For bar code types that explicitly specify the module width, this field is ignored. Bar code types
that explicitly specify the module width are: Australia Post Bar Code, Dutch KIX, Intelligent
Mail Barcode, MaxiCode, PLANET (deprecated), POSTNET (deprecated), RM4SCC, Royal
Mail RED TAG (deprecated), and Royal Mail Mailmark.
Some bar code types explicitly specify the module width, but allow for a tolerance range in
creating the symbol. Some BCOCA receivers can produce either an optimal-size symbol or a
small-size symbol for these fixed-size bar codes. This is called “small-symbol support” and is
controlled by the value of the module-width parameter, as follows:
Optimal symbol
Specify X'FF' to produce an optimal size symbol. This value is recommended.
Small symbol
Specify any value in the range X'01' – X'FE' to produce the smallest symbol
that meets the symbology tolerances. Because this symbol is at the lower
boundary of the symbology-defined tolerance range, external conditions
(such as printer contrast setting, toner consistency, and paper absorbency)
might cause this symbol to not scan properly.
Note that BCOCA receivers that do not provide “small-symbol support” simply ignore the
module-width value (with one exception) and produce an optimal size symbol. The exception
is that both options (optimal and small) are supported for Intelligent Mail Barcodes.
The following table describes this option for the fixed-size symbologies. [BCOCA-4-109]



Note: The table provides theoretical sizes. Presentation devices must map the module width
specification (or recommendation) to an integer number of device pels. This mapping
yields an approximation of the user request and can cause the actual width and height
of a bar code symbol to be slightly different at different device resolutions. Refer to the
symbology specification for bar code types that list multiple widths. [BCOCA-4-110]
### Table 12. Sizing Targets for Fixed-Size Bar Code Types
| Bar Code Type | Optimal-Symbol Size | Small-Symbol Size |
| :--- | :--- | :--- |
| Australia Post Bar Code | Symbol width = 39.60 mm, 55.85 mm, or 72.15 mm<br>Symbol height = 5.00 mm | (only with small-symbol support)<br>Symbol width = 37.0 mm, 52.2 mm, or 67.5 mm<br>Symbol height = 4.2 mm [BCOCA-4-111]|
| MaxiCode | Symbol width = 25.5 mm<br>Symbol height = 24.38 mm | (only with small-symbol support)<br>Symbol width = 24.03 mm<br>Symbol height = 22.98 mm [BCOCA-4-112]|
| POSTNET (deprecated) | Symbol width = 1.429 in, 2.338 in, or 2.793 in<br>Symbol height = 0.125 in | (only with small-symbol support)<br>Symbol width = 1.307 in, 2.140 in, or 2.557 in<br>Symbol height = 0.115 in [BCOCA-4-113]|
| PLANET (deprecated) | Symbol width = 2.793 in or 3.247 in<br>Symbol height = 0.125 in | (only with small-symbol support)<br>Symbol width = 2.557 in or 2.973 in<br>Symbol height = 0.115 in [BCOCA-4-114]|
| RM4SCC (for a 5 character symbol) | Symbol width = 38.61 mm<br>Symbol height = 5.03 mm | (only with small-symbol support)<br>Symbol width = 35.31 mm<br>Symbol height = 4.22 mm [BCOCA-4-115]|
| Dutch KIX (for an 8 character symbol) | Symbol width = 36.30 mm<br>Symbol height = 5.03 mm | (only with small-symbol support)<br>Symbol width = 33.19 mm<br>Symbol height = 4.22 mm [BCOCA-4-116]|
| Intelligent Mail Barcode | Symbol width = 2.95 in<br>Symbol height = 0.145 in | Symbol width = 2.68 in<br>Symbol height = 0.125 in [BCOCA-4-117]|
Note: Some IPDS printers used the original
USPS symbology specification that defined
the smallest symbol size as 2.58 inches
wide and 0.160 inches high. The USPS
specification (Revision B) was changed in
2006 to allow the height of the smallest [BCOCA-4-118]
symbol to be closer to the height of a
POSTNET (deprecated) symbol (yielding a
smallest symbol size of 2.68 inches wide
and 0.134 inches high). In 2007, the
specification (Revision D) was changed
again to allow the smallest symbol to be
0.125 inches high. [BCOCA-4-119]
Royal Mail RED TAG
(deprecated) Symbol width = 56.32 mm
Symbol height = 5.03 mm
(only with small-symbol support)
Symbol width = 54 mm
Symbol height = 4.22 mm
Royal Mail Mailmark
Symbol width (Bar code C) = 79.08 mm
Symbol width (Bar code L) = 93.45 mm
Symbol height = 5.10 mm
(only with small-symbol support)
Symbol width (Bar code C) = 69.85 mm
Symbol width (Bar code L) = 82.55 mm
Symbol height = 4.22 mm
The following equations can be used to convert between L-units, mils, and millimeters, where
X is the symbol for multiplication and / is the symbol for division: [BCOCA-4-120]



1. Inches X (units per unit base) = L-units, also L-units / (units per unit base) = inches [BCOCA-4-121]
For example, when units per unit base is 1440ths, Inches X 1440 = L-units
2. Inches X 1000 = mils, also mils / 1000 = inches [BCOCA-4-122]
3. Inches X 25.4 = mm, also mm / 25.4 = inches [BCOCA-4-123]
From (1), (2), and (3) above, using units per unit base of 1440:
mils X 1.44 = L-units and mm X 1440 / 25.4 = L-units
Bytes 18–19 Element height
Specifies the height in L-units along the Y bc axis of the bar code symbol bar elements. The
measurement unit base is specified in BSD bytes 0–5. The element height and height-
multiplier values are used to specify the total bar height presented. The height of the HRI is not
included in this total height for many bar code symbologies; however, for the following
symbologies, the total symbol height includes both bar patterns as well as the HRI:
• UPC/CGPC Version A, modifier X'00' [BCOCA-4-124]
• UPC/CGPC Version E, modifier X'00' [BCOCA-4-125]
• UPC Two-Digit Supplemental, modifiers X'01' and X'02' (the total height applies to the main [BCOCA-4-126]
symbol; the height of the supplement is calculated from the main-symbol height)
• UPC Five-Digit Supplemental, modifiers X'01' and X'02' (the total height applies to the main [BCOCA-4-127]
symbol; the height of the supplement is calculated from the main-symbol height)
• EAN-8, modifier X'00' [BCOCA-4-128]
• EAN-13, modifier X'00' [BCOCA-4-129]
• EAN Two-Digit Supplemental, modifier X'01' (the total height applies to the main symbol; the [BCOCA-4-130]
height of the supplement is calculated from the main-symbol height)
• EAN Five-Digit Supplemental, modifier X'01' (the total height applies to the main symbol; the [BCOCA-4-131]
height of the supplement is calculated from the main-symbol height)
Notes:
1. If the total height includes the height of the HRI characters and it is less than or equal to [BCOCA-4-132]
the height of the HRI characters, the result is device dependent. Some BCOCA products
report exception condition EC-0700, other products use the total height as the height of
the tallest bar.
2. For Interleaved 2-of-5 symbols, the total height does not include the width of horizontal [BCOCA-4-133]
Bearer Bars placed above and below the symbol.
3. Since the modules for a Data Matrix symbol and a QR Code symbol are defined to be [BCOCA-4-134]
square, the module width parameter specifies both dimensions, and the element height
and height multiplier parameters are not used for these symbologies.
A value of X'FFFF' indicates the default element height of the presentation device is to be
used; refer to Table 13 for a list of recommended default values. For bar code
types that explicitly specify the element height, this field is ignored; these are: Australia Post
Bar Code, Aztec Code, Data Matrix, Han Xin Code,
Intelligent Mail Barcode, Japan Postal Bar
Code, MaxiCode, POSTNET (deprecated), QR Code, QR Code with Image, RM4SCC, Royal
Mail RED TAG (deprecated), and Royal Mail Mailmark. Exception condition EC-0700 exists if
the element height specified is invalid or unsupported. For this condition, the bar code object
processor uses the closest smaller height. If the smaller value is less than the smallest
supported element height or zero, the bar code object processor uses the smallest supported
value.
The height of GS1 DataBar symbols depends on the version of the symbol. Exception
condition EC-0805 exists if the element height and height multiplier values specified are
invalid for the modifier selected. Rules for GS1 DataBar symbol heights are as follows: [BCOCA-4-135]



• GS1 DataBar Omnidirectional – The symbol height specified must be greater than or equal [BCOCA-4-136]
to 33 times the module width.
• GS1 DataBar Truncated – The symbol height specified must be greater than or equal to 13 [BCOCA-4-137]
times the module width.
• GS1 DataBar Stacked – The symbol height is fixed; the element height and height multiplier [BCOCA-4-138]
parameters are ignored.
• GS1 DataBar Stacked Omnidirectional – The row height specified must be greater than or [BCOCA-4-139]
equal to 33 times the module width; the symbol height includes both rows plus the height of
the three-module-high separator pattern.
• GS1 DataBar Limited – The symbol height specified must be greater than or equal to 10 [BCOCA-4-140]
times the module width.
• GS1 DataBar Expanded – The symbol height specified must be greater than or equal to 34 [BCOCA-4-141]
times the module width.
• GS1 DataBar Expanded Stacked – The symbol height is fixed; the element height and [BCOCA-4-142]
height multiplier parameters are ignored.
Byte 20 Height multiplier
Specifies a value that, when multiplied by the element height, yields the total bar height
presented. Exception condition EC-0800 exists if the height multiplier is invalid. For this
condition, the bar code object processor uses a height multiplier of X'01'. For bar code types
that explicitly specify the height multiplier, this field is ignored; these are: Australia Post Bar
Code, Aztec Code, Data Matrix, Han Xin Code,
Intelligent Mail Barcode, Japan Postal Bar
Code, MaxiCode, POSTNET (deprecated), QR Code, QR Code with Image, RM4SCC, Royal
Mail RED TAG (deprecated), and Royal Mail Mailmark.
When the default element height (X'FFFF') is specified, the height multiplier value is ignored
and a height multiplier of 1 is used.
Bytes 21–22 WE:NE
Specifies the ratio of the wide-element dimension to the narrow-element dimension when only
two different size elements exist, that is, for a two-level bar code symbol. The ratio is
expressed as a decimal number and normally varies between 2.00 and 3.00. Refer to the
appropriate symbology specification and printer specification to determine if values outside of
the normal range (decimal values below 2.00 and above 3.00) are supported for that
symbology; if an unsupported (or invalid) WE:NE value is specified, exception condition EC-
0900 exists. [BCOCA-4-143]
The WE:NE parameter is used with the following bar code types:
• X'01' – Code 39 (3-of-9 Code), AIM USS-39 [BCOCA-4-144]
• X'02' – MSI (modified Plessey code) [BCOCA-4-145]
• X'0A' – Industrial 2-of-5 [BCOCA-4-146]
• X'0B' – Matrix 2-of-5 [BCOCA-4-147]
• X'0C' – Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 [BCOCA-4-148]
• X'0D' – Codabar, 2-of-7, AIM USS-Codabar [BCOCA-4-149]
This parameter is the binary representation of a decimal number of the form n.nnnn; the
decimal point follows the first significant digit. For example, a WE:NE value of X'0002'
represents a wide-to-narrow ratio of 2 to 1 and a WE:NE value of X'00E1' represents a wide-
to-narrow ratio of 2.25 to 1. A particular wide-to-narrow ratio can be encoded in several ways;
for example, the WE:NE values X'0015', X'00D2', X'0834', and X'5208' all represent a wide-to-
narrow ratio of 2.1 to 1.
The value X'FFFF' indicates that the bar code object processor is to use the default ratio for
the specified bar code symbology or presentation device; refer to Table 13 for a list
of recommended default values. If the presentation device cannot present the specified [BCOCA-4-150]



narrow-element or wide-element width, exception condition EC-0900 exists. For this condition,
the bar code object processor uses the default wide-to-narrow ratio. The default ratio is in the
range of 2.25 through 3.00 to 1. The MSI (modified Plessey code) bar code, however, uses a
default wide-to-narrow ratio of 2.00 to 1.
The wide-to-narrow ratio parameter is not applicable to all bar code types. The Australia Post
Bar Code, Aztec Code, Code 93, Data Matrix, GS1 DataBar, Han Xin Code, Intelligent Mail
Barcode, Japan Postal Bar Code, MaxiCode, PDF417, POSTNET (deprecated), QR Code,
QR Code with Image, RM4SCC, Royal Mail RED TAG (deprecated), and Royal Mail Mailmark
symbologies do not define a wide-to-narrow ratio. The Code 128, EAN, and UPC symbologies
are referred to as four-level codes. A four-level bar code has four bar-and-space-width levels.
The second, third, and fourth levels are automatically calculated as two, three, and four times
the module width. When these bar code types are specified, this field is ignored. [BCOCA-4-151]



Default Value Recommendations
It is desirable that BCOCA implementations be reasonably consistent so that print jobs appear essentially the
same regardless of which printer prints the job and regardless of which transform or display product creates
bar code symbols from BCOCA input. The following table provides recommendations for what BCOCA
implementations should use when the default module width, element height, or wide-to-narrow ratio is
specified. Many BCOCA implementations existed before these recommendations were first published; refer to
your printer documentation for the exact default values used by your printer.
Some bar code symbologies explicitly specify the module width or element height; in these cases, the following
table lists the module width or element height value defined for the symbology. Refer to the description of
module width (byte 17) and element height (bytes 18–19) for a list of the symbologies that explicitly specify
these values.
### Table 13. Recommended Default Values for Module Width, Element Height, and Wide-to-Narrow Ratio
| Type | Bar Code Symbology | Recommended Default Module Width<sup>1</sup> | Recommended Default Element Height | Recommended Default Wide-to-Narrow Ratio |
| :--- | :--- | :--- | :--- | :--- |
| X'01' | Code 39 (3-of-9 Code), AIM USS-39 | 13 mils | Greater of 250 mils or 15% of symbol width | 2.5 [BCOCA-4-152]|
| X'02' | MSI (modified Plessey code) | 13 mils | Greater of 300 mils or 15% of symbol width | 2.0 [BCOCA-4-153]|
| X'03' | UPC/CGPC-Version A | 13 mils | 1020 mils | Not applicable [BCOCA-4-154]|
| X'05' | UPC/CGPC-Version E | 13 mils | 1020 mils | Not applicable [BCOCA-4-155]|
| X'06' | UPC—Two-Digit Supplemental (Periodicals) | 13 mils | 770 mils (bar height) | Not applicable [BCOCA-4-156]|
| X'07' | UPC—Five-Digit Supplemental (Paperbacks) | 13 mils | 770 mils (bar height) | Not applicable [BCOCA-4-157]|
| X'08' | EAN-8 (includes JAN-short) | 13 mils | 840 mils | Not applicable [BCOCA-4-158]|
| X'09' | EAN-13 (includes JAN-standard) | 13 mils | 1020 mils | Not applicable [BCOCA-4-159]|
| X'0A' | Industrial 2-of-5 | 13 mils | Greater of 250 mils or 15% of symbol width | 2.5 [BCOCA-4-160]|
| X'0B' | Matrix 2-of-5 | 13 mils | Greater of 250 mils or 15% of symbol width | 2.5 [BCOCA-4-161]|
| X'0C' | Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 | 13 mils<sup>2</sup> | Greater of 250 mils or 15% of symbol width<sup>2</sup> | 2.5 [BCOCA-4-162]|
| X'0D' | Codabar, 2-of-7, AIM USS-Codabar | 13 mils | Greater of 250 mils or 15% of symbol width | 2.5 [BCOCA-4-163]|
| X'11' | Code 128, AIM USS-128 [BCOCA-4-164]| | | |
| | Code 128 modifier X'02' | 13 mils | Greater of 250 mils or 15% of symbol width | Not applicable [BCOCA-4-165]|
| | UCC/EAN 128, Code 128 modifier X'03' | 13 mils | Greater of 250 mils or 15% of symbol width | Not applicable [BCOCA-4-166]|
| | UCC/EAN 128 and GS1-128, Code 128 modifier X'04' | 13 mils | Greater of 250 mils or 15% of symbol width | Not applicable [BCOCA-4-167]|
| | Intelligent Mail Container Barcode, Code 128 modifier X'05' | 25 mils | 925 mils | Not applicable [BCOCA-4-168]|
| | Intelligent Mail Package Barcode, Code 128 modifier X'06' | 16 mils | 750 mils | Not applicable [BCOCA-4-169]|
| X'16' | EAN Two-Digit Supplemental | 13 mils | 840 mils (bar height) | Not applicable [BCOCA-4-170]|
| X'17' | EAN Five-Digit Supplemental | 13 mils | 840 mils (bar height) | Not applicable [BCOCA-4-171]|
| X'18' | POSTNET (deprecated) and PLANET (deprecated) | 20 mils with a horizontal pitch of 22 bars/inch | 125 mils | Not applicable [BCOCA-4-172]|
| X'1A' | RM4SCC and Dutch KIX | 20 mils with a horizontal pitch of 22 bars/inch | 198 mils | Not applicable [BCOCA-4-173]|
| X'1B' | Japan Postal Bar Code | 24 mils | 6 times module width | Not applicable [BCOCA-4-174]|
| X'1C' | Data Matrix, GS1 DataMatrix (2D bar code) | 21 mils | 21 mils | Not applicable [BCOCA-4-175]|
| X'1D' | MaxiCode (2D bar code) | Defined in symbology | Defined in symbology | Not applicable [BCOCA-4-176]|
| X'1E' | PDF417 (2D bar code) | 14 mils | 4 times module width | Not applicable [BCOCA-4-177]|
| X'1F' | Australia Post Bar Code | 20 mils with a horizontal pitch of 23.5 bars/inch | 197 mils | Not applicable [BCOCA-4-178]|
| X'20' | QR Code, QR Code with Image (2D bar code) | 12 mils | 12 mils | Not applicable [BCOCA-4-179]|
| X'21' | Code 93 | 13 mils | Greater of 250 mils or 15% of symbol width | Not applicable [BCOCA-4-180]|
| X'22' | Intelligent Mail Barcode | 20 mils with a horizontal pitch of 22 bars/inch | 145 mils | Not applicable [BCOCA-4-181]|
| X'23' | Royal Mail RED TAG (deprecated) | 20 mils with a horizontal pitch of 23 bars/inch | 198 mils | Not applicable [BCOCA-4-182]|
| X'24' | GS1 DataBar: | 10 mils | | Not applicable [BCOCA-4-183]|
| | Omnidirectional (X'00') | | 33 times modwidth [BCOCA-4-184]| |
| | Truncated (X'01') | | 13 times modwidth [BCOCA-4-185]| |
| | Stacked (X'02') | | Not applicable [BCOCA-4-186]| |
| | Stacked - Omnidirectional (X'03') | | 33 times modwidth [BCOCA-4-187]| |
| | Limited (X'04') | | 10 times modwidth [BCOCA-4-188]| |
| | Expanded (X'11') | | 34 times modwidth [BCOCA-4-189]| |
| | Expanded - Stacked (X'12'–X'1B') | | Not applicable [BCOCA-4-190]| |
| X'25' | Royal Mail Mailmark | 21 mils with a horizontal pitch of 21.2 bars/inch | 201 mils | Not applicable [BCOCA-4-191]|
| X'26' | Aztec Code | 14 mils | 14 mils | Not applicable [BCOCA-4-192]|
| X'27' | Han Xin Code | 12 mils | 12 mils | Not applicable [BCOCA-4-193]|

**Notes:**
1. Module width measures the width of the smallest bar in the symbol and, for most bar codes, this is also the size of the smallest space. However, some postal bar codes specify symbol width in terms of bar width and also horizontal pitch. Horizontal pitch measures the number of bars per inch (or bars per 25.4 mm); this typically causes the spaces between bars to be different than the bar width. [BCOCA-4-194]
2. The module width and element height for ITF-14 symbols is defined by the application specification based on the needs of the application. Therefore, the default values might not be appropriate for all applications of the ITF-14 symbol; refer to GS1 General Specifications for more information. [BCOCA-4-195]




Bar Code Type and Modifier Descriptions
Each bar code type supports one or more variations that are specified with a modifier value, as follows:
Code 39 (3-of-9 Code), AIM USS-39 (modifier values X'01' and X'02')
Code 39 (3-of-9 Code)
(encoding 39OR93 with check character
yielding a 2.32 inch wide symbol)
39OR93W
X'01' Present the bar code without a generated check digit.
X'02' Generate a check digit and present it with the bar code.
Note: The Code 39 character set contains 43 characters including numbers, upper-case alphabetics, and
some special characters. The Code 39 Specification also provides a method of encoding all 128 ASCII
characters by using two bar code characters for those ASCII characters that are not in the standard
Code 39 character set. This is sometimes referred to as “Extended Code 39” and is supported by all
BCOCA receivers. In this case, the two bar code characters used to specify the “extended character”
will be shown in the Human-Readable Interpretation and the bar code scanner will interpret the two-
character combination bar/space pattern appropriately.
Code 39 (3-of-9 Code) [BCOCA-4-196]




MSI (modified Plessey code, modifier values X'01' through X'09')
MSI - no check digit
(encoding 80523)
80523
X'01' Present the bar code without check digits generated by the printer. Specify 3 to 15 digits of input data.
X'02' Present the bar code with a generated IBM modulo-10 check digit. This check digit will be the second
check digit; the first check digit is the last byte of the BSA data. Specify 2 to 14 digits of input data.
X'03' Present the bar code with two check digits. Both check digits are generated using the IBM modulo-10
algorithm. Specify 1 to 13 digits of input data.
X'04' Present the bar code with two check digits. The first check digit is generated using the NCR modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals the remainder;
exception condition EC-0E00 exists if the first check-digit calculation results in a value of 10. Specify 1
to 13 digits of input data.
X'05' Present the bar code with two check digits. The first check digit is generated using the IBM modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals the remainder;
exception condition EC-0E00 exists if the first check-digit calculation results in a value of 10. Specify 1
to 13 digits of input data.
X'06' Present the bar code with two check digits. The first check digit is generated using the NCR modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals 11 minus the
remainder; a first check digit value of 10 is assigned the value zero. Specify 1 to 13 digits of input data.
X'07' Present the bar code with two check digits. The first check digit is generated using the IBM modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals 11 minus the
remainder; a first check digit value of 10 is assigned the value zero. Specify 1 to 13 digits of input data.
X'08' Present the bar code with two check digits. The first check digit is generated using the NCR modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals 11 minus the
remainder; exception condition EC-0E00 exists if the first check-digit calculation results in a value of
10. Specify 1 to 13 digits of input data. [BCOCA-4-197]
X'09' Present the bar code with two check digits. The first check digit is generated using the IBM modulo-11
algorithm; the second using the IBM modulo-10 algorithm. The first check digit equals 11 minus the
remainder; exception condition EC-0E00 exists if the first check-digit calculation results in a value of
10. Specify 1 to 13 digits of input data. [BCOCA-4-198]
MSI (modified Plessey code) [BCOCA-4-199]




UPC/CGPC – Version A (modifier value X'00')
0 512345 67890 [BCOCA-4-200]
UPC Version A
(encoding 01234567890)
X'00' Present the standard UPC-A bar code with a generated check digit. The data to be encoded consists
of eleven digits. The first digit is the number-system digit; the next ten digits are the article number.
Specify 11 digits of input data. The first digit is the number system character; the remaining digits are
information characters.
Note: The UPC-A symbology is controlled by the GS1 standards organization and is described in GS1 General
Specifications.
UPC/CGPC – Version E (modifier value X'00')
0 1078349 [BCOCA-4-201]
UPC Version E
(encoding 078349)
X'00' Present a UPC-E bar code symbol. Of the 10 input digits, six digits are encoded. The check digit is
generated using all 10 input data digits. The check digit is not encoded; it is only used to assign odd or
even parity to the six encoded digits.
Specify 10 digits of input data. Version E suppresses some zeros that can occur in the information
characters to produce a shorter symbol. All 10 digits are information characters; the number system
character should not be specified (it is assumed to be 0).
Note: The UPC-E symbology is controlled by the GS1 standards organization and is described in GS1 General
Specifications.
UPC/CGPC—Version A and Version E [BCOCA-4-202]




UPC – Two-Digit Supplemental (modifier values X'00' through X'02')
0 806338 95260 [BCOCA-4-203]
4 2
UPC A + Two-digit Supplemental
(encoding 00633895260, supplemental = 24)
X'00' Present a UPC Two-Digit Supplemental bar code symbol. This option assumes that the base UPC
Version A or E symbol is presented as a separate bar code object. The bar and space patterns used
for the two supplemental digits are left-odd or left-even parity, with the parity determined by the digit
combination.
Specify 2 digits of input data.
X'01' The UPC Two-Digit Supplemental bar code symbol is preceded by a UPC Version A, Number System
0, bar code symbol. The bar code object contains both the UPC Version A symbol and the Two-Digit
Supplemental symbol. The input data consists of the number system digit (must be 0), the ten-digit
article number, and the two supplement digits, in that order. A check digit is generated for the UPC
Version A symbol. The Two-Digit Supplemental bar code is presented after the UPC Version A symbol
using left-odd and left-even parity as determined by the two supplemental digits.
Specify 13 digits of input data.
X'02' The UPC Two-Digit Supplemental bar code symbol is preceded by a UPC Version E symbol. The bar
code object contains both the UPC Version E symbol and the Two-Digit Supplemental symbol. The
input data consists of the ten-digit article number and the two supplemental digits. The bar code object
processor generates the six-digit UPC Version E symbol and a check digit. The check digit is used to
determine the parity pattern of the six-digit Version E symbol. The Two-Digit Supplemental bar code
symbol is presented after the Version E symbol using left-odd and left-even parity as determined by
the two digits.
Specify 12 digits of input data.
Note: The UPC Two-Digit Supplemental symbology is controlled by the GS1 standards organization and is
described in GS1 General Specifications.
UPC—Two-Digit Supplemental [BCOCA-4-204]




UPC – Five-Digit Supplemental (modifier values X'00' through X'02')
0 698277 21123 [BCOCA-4-205]
6 2 8 1 2 [BCOCA-4-206]
UPC A + Five-digit Supplemental
(encoding 09827721123, supplemental = 21826)
X'00' Present the UPC Five-Digit Supplemental bar code symbol. This option assumes that the base UPC
Version A or E symbol is presented as a separate bar code object. A check digit is generated from the
five supplemental digits and is used to assign the left-odd and left-even parity of the Five-Digit
Supplemental bar code. The supplemental check digit is not encoded or interpreted.
Specify 5 digits of input data.
X'01' The UPC Five-Digit Supplemental bar code symbol is preceded by a UPC Version A, Number System
0, bar code symbol. The bar code object contains both the UPC Version A symbol and the Five-Digit
Supplemental symbol. The input data consists of the number system digit (must be 0), the ten-digit
article number, and the five supplement digits, in that order. A check digit is generated for the UPC
Version A symbol. A second check digit is generated from the five supplement digits. It is used to
assign the left-odd and left-even parity of the Five-Digit Supplemental bar code symbol. The
supplement check digit is not encoded or interpreted.
Specify 16 digits of input data.
X'02' The UPC Five-Digit Supplemental bar code symbol is preceded by a UPC Version E symbol. The bar
code object contains both the UPC Version E symbol and the Five-Digit Supplemental symbol. The
input data consists of the ten-digit article number and the Five-Digit Supplemental data. The bar code
object processor generates the six-digit UPC Version E symbol and check digit. The check digit is
used to determine the parity pattern of the Version E symbol. The Five-Digit Supplemental bar code
symbol is presented after the Version E symbol. A second check digit is calculated for the Five-Digit
Supplemental data and is used to assign the left-odd and left-even parity. The supplement check digit
is not encoded or interpreted.
Specify 15 digits of input data.
Note: The UPC Five-Digit Supplemental symbology is controlled by the GS1 standards organization and is
described in GS1 General Specifications.
UPC—Five-Digit Supplemental [BCOCA-4-207]




EAN-8 (includes JAN-short, modifier value X'00')
2468 1230
EAN 8
(encoding 2468123)
X'00' Present an EAN-8 bar code symbol. The input data consists of seven digits: two flag digits and five
article number digits. All seven digits are encoded along with a generated check digit.
Note: The EAN-8 symbology is controlled by the GS1 standards organization and is described in GS1 General
Specifications.
EAN-13 (includes JAN-standard, modifier value X'00')
0412345 678903 [BCOCA-4-208]
EAN 13
(encoding 041234567890)
X'00' Present an EAN-13 bar code symbol. The input data consists of twelve digits: two flag digits and ten
article number digits, in that order. The first flag digit is not encoded. The second flag digit, the article
number digits, and generated check digit are encoded. The first flag digit is presented in HRI form at
the bottom of the left quiet zone. The first flag digit governs the A and B number-set pattern of the bar
and space coding of the six digits to the left of the symbol center pattern.
Note: The EAN-13 symbology is controlled by the GS1 standards organization and is described in GS1
General Specifications.
EAN-8 and EAN-13




Industrial 2-of-5 (modifier values X'01' and X'02')
Industrial  2-of-5
(encoding 54321068)
54321068
X'01' Present the bar code without a generated check digit.
X'02' Generate a check digit and present it with the bar code.
Matrix 2-of-5 (modifier values X'01' and X'02')
54321068
Matrix  2-of-5
(encoding 54321068)
X'01' Present the bar code symbol without a generated check digit.
X'02' Generate a check digit and present it with the bar code.
Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 (modifier values X'01' through X'04')
Interleaved 2-of-5
(encoding 54321068)
54321068
The Interleaved 2-of-5 symbology requires an even number of digits, and the printer will add a leading zero if
necessary to meet this requirement.
X'01' Present the bar code symbol without a check digit.
X'02' Generate a check digit and present it with the bar code.
2-of-5 Codes




X'03' Present the bar code symbol with a generated check digit and with Bearer Bars that completely
surround the bar/space pattern.
The purpose of Bearer Bars is to reduce the possibility of misreads or short scans that might occur
when a skewed scanning beam enters or exits the barcode symbol through its top or bottom edge.
Bearer Bars should be a constant minimum thickness of twice the width of the narrow bar, placed
directly against the top, bottom, and sides of the symbol plus quiet zone. The Bearer Bars should
completely surround the symbol, including the quiet zones, which are a minimum of 10 times the X
dimension.
ITF-14 Symbol with Surrounding Bearer Bars
15400141288763
(encoding 1540014128876)
X'04' Present the bar code symbol with a generated check digit and with Bearer Bars that are placed at the
top and the bottom of the bar/space pattern.
The purpose of Bearer Bars is to reduce the possibility of misreads or short scans that might occur
when a skewed scanning beam enters or exits the barcode symbol through its top or bottom edge.
Bearer Bars should be a constant minimum thickness of twice the width of the narrow bar, placed
directly against the top and bottom of the symbol bars.
Interleaved 2-of-5 Symbol with
Bearer Bars at Top and Bottom
1234567895
(encoding 1234567895)
Note: ITF-14 is a special case of Interleaved 2-of-5, which encodes 13 input digits and a check digit. The ITF-
14 symbology is controlled by the GS1 standards organization and is described in GS1 General [BCOCA-4-209]
Specifications.
2-of-5 Codes




Codabar, 2-of-7, AIM USS-Codabar (modifier values X'01' and X'02')
Codabar
(encoding A34698735B)
3 4 6 9 8 7 3 5 [BCOCA-4-210]
X'01' Present the bar code without a generated check digit. The input data consists of a start character,
digits to be encoded, and a stop character, in that order. Start and stop characters can be A, B, C, or D,
and can only be used at the beginning and end of the symbol.
X'02' Generate a check digit and present it with the bar code. The input data consists of a start character,
digits to be encoded, and a stop character, in that order. Start and stop characters can be A, B, C, or D,
and can only be used at the beginning and end of the symbol.
Codabar, 2-of-7




Code 128 (modifier values X'02' through X'06')
Code 128 is a general purpose symbology that has been used in several ways. BCOCA architecture uses the
following modifiers to support some of these uses:
Modifier X'02' – AIM USS-128
This is a basic Code 128 symbol that is defined in USS-128 Uniform Symbology Specification
published by AIM.
Modifiers X'03' – UCC/EAN 128
This is a variation of the Code 128 symbol that was originally defined in UCC/EAN-128 Application
Identifier Standard and the Application Standard for Shipping Container Codes published by the
Uniform Code Council and was also defined by the European Article Numbering Association (EAN). A
newer description of the UCC/EAN 128 symbology is available in GS1 General Specifications. The
GS1 standards group became the successor to the organizations previously known as EAN and UCC.
Many BCOCA implementations use the earlier specifications.
Modifier X'04' – UCC/EAN 128 and GS1-128
This is a variation of the Code 128 symbol identical to modifier 03 except that parentheses are used in
the HRI to distinguish each application identifier (ai). A UCC/EAN-128 symbol can use either modifier
X'03' or modifier X'04'. GS1-128 symbols use modifier X'04'.
Modifier X'05' – Intelligent Mail Container Barcode
This is a bar code that is defined in BARCODE, CONTAINER, INTELLIGENT MAIL (USPS-B-3215)
published by the United States Postal Service (USPS). The bar code uses a special form of the GS1-
128 symbol that is defined in GS1 General Specifications published by GS1. [BCOCA-4-211]
Modifier X'06' – Intelligent Mail Package Barcode
This is a bar code that is defined in Barcode, Package, Intelligent Mail (USPS2000508) published by
the United States Postal Service (USPS). The bar code uses a special form of the GS1-128 symbol
that is defined in GS1 General Specifications published by GS1.
The 1986 symbology definition for Code 128 defined an algorithm for generating a start character and then
changed that algorithm in 1993 to accommodate the UCC/EAN 128 variation of this bar code. Many BCOCA
printers have implemented the 1986 version (using modifier X'02'), some BCOCA printers have changed to
use the 1993 algorithm (with modifier X'02'), and some BCOCA printers support both algorithms. When
producing UCC/EAN 128 bar codes for printers that explicitly support UCC/EAN 128, modifier X'03' or modifier
X'04' should be specified. For printers that do not explicitly support UCC/EAN 128, specifying modifier X'02'
might produce a valid UCC/EAN 128 bar code (see notes in the modifier descriptions).
The data (BSA bytes n+1 to end) for UCC/EAN 128 and GS1-128 bar codes is in the form:
FNC1, ai, data, [m], [FNC1], ai, data, [m], [FNC1], ..., ai, data, [m]
Where “FNC1” is the FNC1 function character (X'8F'), “ai” is an application identifier, “data” is defined for
each registered application identifier, and “m” is a modulo 10 check digit (calculated using the same check
digit algorithm as is used for UPC version A bar codes); note that not all application identifiers require a
modulo 10 check digit (m). Also, note that all except the first “FNC1” are field separator characters that only
appear when the preceding ai data is of variable length. Refer to UCC/EAN-128 APPLICATION IDENTIFIER
STANDARD from the Uniform Code Council, Inc. for a description of application identifiers and the use of
“FNC1”. When building the bar code symbol, the printer will:
1. produce a start character based on the 1993 algorithm [BCOCA-4-212]
2. bar encode the data including all of the “FNC1”, “ai”, “data”, and “m” check digit [BCOCA-4-213]
3. produce a modulo 103 check digit [BCOCA-4-214]
4. produce a stop character. [BCOCA-4-215]
The Intelligent Mail Tray Barcode defined by the United States Postal Service uses the Code 128 bar code
symbology.
Code 128




Code 128 modifier X'02' – Code 128 symbol, using original (1986) start-character
algorithm
Code 128
(encoding ABC123abc@456)
AB C 1 2 3 a b c @ 4 5 6
Generate a Code 128 symbol using subset A, B, or C as appropriate to produce the shortest possible bar code
from the given data, using the start-character algorithm that was published in the original (1986) edition of the
Code 128 Symbology Specification. The Code 128 code page (CPGID = 1303, GCSGID = 1454) is used to
interpret the bar code symbol data. Generate a check digit and present it with the bar code.
Note: Some IPDS printers incorrectly use the modifier X'03' start-character algorithm even when modifier X'02'
is specified; this produces a valid UCC/EAN 128 symbol when valid UCC/EAN 128 data is provided.
However, in general, modifier X'02' should not be used to produce UCC/EAN 128 symbols since this
value causes other IPDS printers to use the original Code 128 start-symbol algorithm that will generate
a Start (Code B) instead of the Start (Code C) that UCC/EAN 128 requires. Some bar code scanners
can handle either start character for a UCC/EAN 128 symbol, but others require the Start (Code C)
character.
IPDS printers should use the original start-character algorithm when modifier X'02' is specified. Known
printers that incorrectly use the UCC/EAN 128 start-character algorithm when modifier X'02' is specified
include: IBM 4312, IBM 4317, IBM 4324, Infoprint
® 20, Infoprint 21, Infoprint 32, Infoprint 40, Infoprint
45, Infoprint 70, Infoprint 2070, Infoprint 2085, and Infoprint 2105.
Code 128




Code 128 modifier X'03' – UCC/EAN 128 symbol, without parentheses in the HRI
SCC-14 and Sell-By Date Concatenated in a UCC/EAN-128 Symbol
019061414100768715001230
(encoding      019061414100768715001230)
F
N
C
1
Generate a Code 128 symbol using subset A, B, or C as appropriate to produce the shortest possible bar code
from the given data, using the version of the start-character algorithm that was modified for producing
UCC/EAN 128 symbols. If the first data character is FNC1 (as is required for a UCC/EAN 128 symbol) and is
followed by valid UCC/EAN 128 data, the printer will generate a Start (Code C) character. The Code 128 code
page (CPGID = 1303, GCSGID = 1454) is used to interpret the bar code symbol data. Generate a check digit
and present it with the bar code.
The UCC/EAN 128 data is checked for validity and exception condition EC-1200 exists if one or more of the
following conditions are encountered:
• FNC1 is not the first data character [BCOCA-4-216]
• Invalid application identifier (ai) value encountered [BCOCA-4-217]
• Data for an ai doesn't match the ai definition [BCOCA-4-218]
• Insufficient (or no) data following an ai [BCOCA-4-219]
• T oo much data for an ai [BCOCA-4-220]
• Invalid use of FNC1 character [BCOCA-4-221]
Notes:
1. UCC/EAN 128 is a variation of Code 128 that begins with an FNC1 character, followed by an Application [BCOCA-4-222]
Identifier and the data to be bar encoded. All of these characters (including the FNC1 character) must be
supplied within the Bar Code Symbol Data (BSA). UCC/EAN 128 also requires that the symbol begin in
subset C. The GS1-128 symbology allows symbols to begin with either subset A, B, or C.
2. For UCC/EAN 128 symbols, the start character, the FNC1 characters, the modulo 103 check digit, and the [BCOCA-4-223]
stop character are not shown in the human readable format.
Code 128




Code 128 modifier X'04' – UCC/EAN 128 and GS1-128 symbols, with parentheses in
the HRI
SCC-14 and Sell-By Date Concatenated in a UCC/EAN-128 Symbol
(01)90614141007687(15)001230
(encoding      019061414100768715001230)
F
N
C
1
Generate a Code 128 symbol in the same manner as for modifier X'03', but use parentheses in the HRI to
distinguish each application identifier (ai). The printer inserts the parentheses in the printed HRI when modifier
X'04' is specified; these parentheses are not part of the input data.
Note: The GS1-128 symbology is controlled by the GS1 standards organization and is described in GS1
General Specifications.
Code 128




Code 128 modifier X'05' – Intelligent Mail Container Barcode
99  M  123456  -----ABC1234 [BCOCA-4-224]
USPS SCAN REQUIRED
(encoding   99M123456-----ABC1234)
F
N
C
1
Intelligent Mail Container Barcode
The Intelligent Mail Container Barcode symbology is defined and used by the United States Postal Service
(USPS) for the Full Service category of automation discounts. The bar code uses a special form of the GS1-
128 (also known as UCC/EAN 128) symbology for printing on mailer-generated pallet labels to uniquely [BCOCA-4-225]
identify pallets and similar containers and to identify the mail owner; a unique serial number can also be
provided for each container.
The printer will generate a GS1-128 symbol as described in the USPS symbology specification (BARCODE,
CONTAINER INTELLIGENT MAIL); the GS1-128 Specification is used to produce the bar code symbol. The
Code 128 code page is used to interpret the bar code symbol data (CPGID = 1303, GCSGID = 1454; refer to
Figure 18). The printer will also produce an appropriate USPS Banner (USPS SCAN REQUIRED)
and Identification Bars above and below the symbol. If requested, HRI will be printed below the symbol using
two blanks as separators between each field of the HRI.
The Intelligent Mail Container Barcode symbology allows for a variety of symbol sizes. The module width must
be between 23 mils and 27 mils and the height must be between 0.75 inches and 1.1 inches. A symbol width
between 6.25 inches and 7.25 inches is recommended.
The input data for the bar code is alphanumeric and consists of 22 characters as shown in Table 14. The serial
number field must be padded on the left with either leading zeros (code point X'F0') or leading dashes (code
point X'60'); leading zeros are recommended. The BCOCA symbol data is checked for validity and exception
condition EC-1203 exists if the data is invalid or insufficient.
Table 14. Intelligent Mail Container Barcode Data Field Ranges
Field Name Source Field Size and Data
Type
Field Range
Function 1
Symbol
Character
USPS
assigned
1 byte FNC1 (X'8F') [BCOCA-4-226]
Application
Identifier
USPS
assigned
2 bytes (numeric) 99 [BCOCA-4-227]
Type Indicator USPS
assigned
1 byte (alphabetic) M [BCOCA-4-228]
Mailer ID USPS
assigned
either 6 bytes or 9 bytes
(numeric)
Six-byte Mailer IDs are in the range 000000–899999
Nine-byte Mailer IDs are in the range 900000000–999999999
Serial Number Mailer
assigned
either 12 bytes or
9 bytes (alphanumeric) [BCOCA-4-229]
Any alphanumeric value
When the Mailer ID is 6 bytes, the Serial Number is 12 bytes
When the Mailer ID is 9 bytes, the Serial Number is 9 bytes
Code 128




The user must provide sufficient white space around the bar code for quiet zones (the printer does not provide
the quiet zones). A quiet zone of at least 0.125 inches is required above and below the bar code. A quiet zone
of at least 10 times the module width is required to the left and right of the bar code.
The origin of the bar code symbol is defined to be the top-left corner of an imaginary rectangle of minimum size
that bounds the bar and space pattern. Since the HRI, USPS Banner, Identification Bars, and quiet zone are
outside of the imaginary rectangle, it is important to make sure that the symbol is positioned to allow for these
items. If any part of the symbol, HRI, USPS Banner, or Identification Bars fall outside the bar code presentation
space, exception ID EC-1100 exists.
Code 128




Code 128 modifier X'06' – Intelligent Mail Package Barcode
USPS TRACKING # eVS
9374 8901 0000 0003 9850 39 [BCOCA-4-230]
Intelligent Mail Package Barcode
(encoding    42021234    9374890100000003985039)
F
N
C
1
F
N
C
1
The Intelligent Mail Package Barcode symbology is defined and used by the United States Postal Service
(USPS) for parcels, and is required to obtain certain discounts. The bar code uses a special form of the GS1-
128 (also known as UCC/EAN 128) symbology. Concatenated data allows for presenting both routing [BCOCA-4-231]
information and package identification information in a single bar code. Fields are available to specify mailer
identification, device identification, package serial number, as well as other fields. There are three different
overall data formats, based on the possible values of the “Channel Application Identifier” (AI) field; different
fields are included in the bar code based on the AI value. However, even within a given format, some fields can
vary in size, and the routing information is optional in all cases.
The printer will generate a GS1-128 symbol as described in the USPS symbology specification (Barcode,
Package, Intelligent Mail); the GS1-128 Specification is used to produce the bar code symbol. The Code 128
code page (CPGID = 1303, GCSGID = 1454; refer to Figure 18) is used to interpret the bar code
symbol data.
HRI will be printed below the symbol, in groups of four digits separated by a blank space character; routing
information is not included in the HRI, even when included in the bar code itself. The printer will also produce
an appropriate USPS Service Banner, and Identification Bars above and below the symbol. The text
comprising the USPS Service Banner is passed in the “special functions” area of the BSA, and is encoded in
UTF-16BE. The symbology specification states that both the HRI and the Service Banner are to be printed
using a boldface, sans serif font, such as Helvetica Bold or Arial Bold.
The Intelligent Mail Package Barcode symbology allows for a variety of symbol sizes. The module width must
be between 13 mils and 21 mils, with widths between 15 mils and 17 mils being preferred. The module height
is officially stated as a minimum of 0.75 inches, but an exception process allows for the possibility of heights
down to 0.5 inches. The symbol width varies based on both the module size and the number of digits in the bar
code data.
The input data for the bar code is restricted to numeric characters and is always 22, 26, 30, or 34 digits long.
The BCOCA symbol data is checked for validity and exception condition EC-1205 exists if the data is invalid or
insufficient.
The user must provide sufficient white space around the bar code for a quiet zone (the printer does not provide
the quiet zone). A quiet zone of at least 0.125 inches is required above and below the bar code. A quiet zone of
at least 10 times the module width is required to the left and right of the bar code.
The origin of the bar code symbol is defined to be the top-left corner of an imaginary rectangle of minimum size
that bounds the bar and space pattern. Since the HRI, Service Banner, Identification Bars, and quiet zone are
outside of the imaginary rectangle, it is important to make sure that the symbol is positioned to allow for these
items. If any part of the symbol, HRI, Service Banner, or Identification Bars fall outside the bar code
presentation space, exception ID EC-1100 exists.
Code 128




EAN Two-Digit Supplemental (modifier values X'00' and X'01')
0 412345 678903 [BCOCA-4-232]
9 9
EAN + 2 Digit Supplemental
(encoding 041234567890, supplemental = 99)
X'00' Present the EAN Two-Digit Supplemental bar code symbol. This option assumes that the base EAN-
13 symbol is presented as a separate bar code object. The value of the Two-Digit Supplemental data [BCOCA-4-233]
determines their bar and space patterns chosen from number sets A and B.
Specify 2 digits of input data.
X'01' The Two-Digit Supplemental bar code symbol is preceded by a normal EAN-13 bar code symbol. The
bar code object contains both the EAN-13 symbol and the Two-Digit Supplemental symbol. The Two-
Digit Supplemental bar code is presented after the EAN-13 symbol using left-odd and left-even parity
as determined by the two supplemental digits chosen from number sets A and B.
Specify 14 digits of input data.
Note: The EAN Two-Digit Supplemental symbology is controlled by the GS1 standards organization and is
described in GS1 General Specifications.
EAN Two-Digit Supplemental [BCOCA-4-234]




EAN Five-Digit Supplemental (modifier values X'00' and X'01')
0 412345 678903 [BCOCA-4-235]
1 2 3 4 5 [BCOCA-4-236]
EAN + 5 Digit Supplemental
(encoding 041234567890, supplemental = 54321)
X'00' Present the EAN Five-Digit Supplemental bar code. This option assumes that the base EAN-13
symbol is presented as a separate bar code object. A check digit is calculated from the five
supplemental digits. The check digit is also used to assign the bar and space patterns from number
sets A and B for the five supplemental digits. The check digit is not encoded or interpreted.
Specify 5 digits of input data.
X'01' The Five-Digit Supplemental bar code symbol is preceded by a normal EAN-13 bar code symbol. The
bar code object contains both the EAN-13 symbol and the Five-Digit Supplemental symbol. A check
digit is generated from the Five-Digit Supplemental data. The check digit is used to assign the bar and
space patterns from number sets A and B. The check digit is not encoded or interpreted.
Specify 17 digits of input data.
Note: The EAN Five-Digit Supplemental symbology is controlled by the GS1 standards organization and is
described in GS1 General Specifications.
EAN Five-Digit Supplemental [BCOCA-4-237]




POSTNET and PLANET (both deprecated, modifier values X'00' through X'04')
US POSTNET
Zip+4
(encoding 12345+6789)
PLANET Code
(encoding 00123456789)
Note: The POSTNET and PLANET symbologies have been retired by the United States Postal Service and
have also been deprecated in the BCOCA architecture. For a description of the replacement, refer to the
“Intelligent Mail Barcode (modifier values X'00' through X'03')”.
For all POSTNET modifiers that follow, the BSA HRI flag field and the BSD element height, height multiplier,
and wide-to-narrow ratio fields are not applicable to the POSTNET bar code symbology. These fields are
ignored because the POSTNET symbology defines specific values for these parameters.
Some BCOCA implementations use the module width parameter to specify one of two symbol sizes (small or
optimal); refer to the description of module width for details. This function is called small-symbol
support; printers that do not provide small-symbol support ignore the module width field.
X'00' Present a POSTNET ZIP Code bar code symbol. The ZIP Code to be encoded is defined as a five-
digit, numeric (0–9), data variable to the BSA data structure. The POSTNET ZIP Code bar code
consists of a leading frame bar, the encoded ZIP Code data, a correction digit, and a trailing frame bar.
X'01' Present a POSTNET ZIP+4 bar code symbol. The ZIP+4 code to be encoded is defined as a nine-
digit, numeric (0–9), data variable to the BSA data structure. The POSTNET ZIP+4 bar code consists
of a leading frame bar, the encoded ZIP+4 data, a correction digit, and a trailing frame bar.
X'02' Present a POSTNET Advanced Bar Code (ABC) bar code symbol. The ABC code to be encoded is
defined as an eleven-digit, numeric (0–9), data variable to the BSA data structure. The POSTNET ABC
bar code consists of a leading frame bar, the encoded ABC data, a correction digit, and a trailing frame
bar.
Note: An 11-digit POSTNET bar code is called a Delivery Point bar code.
X'03' Present a POSTNET variable-length bar code symbol. The data to be encoded is defined as an n-digit,
numeric (0–9), data variable to the BSA data structure. The bar code symbol is generated without
length checking; the symbol is not guaranteed to be scannable or interpretable. The POSTNET
variable-length bar code consists of a leading frame bar, the encoded data, a correction digit, and a
trailing frame bar.
X'04' Present a PLANET Code symbol. The PLANET Code is a reverse topology variation of POSTNET that
encodes 11 digits of data; the first 2 digits represent a service code (such as, 21 = Origin Confirm and
22 = Destination Confirm) and the next 9 digits identify the mail piece. A 12th digit is generated by the [BCOCA-4-238]
printer as a check digit. The PLANET Code symbol consists of a leading frame bar, the encoded data,
a check digit, and a trailing frame bar.
POSTNET and PLANET




Royal Mail RM4SCC and Dutch KIX (modifier values X'00' and X'01')
Royal Mail (RM4SCC)
(encoding SN34RD1A)
UK and Singapore version
Royal Mail (RM4SCC)
(encoding )2500GG30250
Dutch KIX version
This is a 4-state customer code defined by the Royal Mail Postal service of England for use in bar coding
postal code information. This symbology is also called the Royal Mail bar code or the 4-State customer code.
The symbology (as defined for modifier X'00') is used in the United Kingdom and in Singapore. A variation
called KIX (KlantenIndeX = customer index, as defined for modifier X'01') is used in the Netherlands.
X'00' Present an RM4SCC bar code symbol with a generated start bar, checksum character, and stop bar.
The start and stop bars identify the beginning and end of the bar code symbol and also the orientation
of the symbol.
X'01' Dutch KIX variation – Present an RM4SCC bar code symbol with no start bar, no checksum character,
and no stop bar.
Royal Mail RM4SCC and Dutch KIX [BCOCA-4-239]




Japan Postal Bar Code (modifier values X'00' and X'01')
Japan Postal Bar Code
Modifier X'00'
(encoding 15400233-16-4)
This is a bar code symbology defined by the Japanese Postal Service for use in bar coding postal code
information.
X'00' Present a Japan Postal Bar Code symbol with a generated start character, checksum character, and
stop character.
The generated bar code symbol will consist of a start code, a 7-digit new postal code, a 13-digit
address indication number, a check digit, and a stop code. The variable data to be encoded (BSA
bytes 5–n) will be used as follows:
1. The first few digits is the new postal code in either the form nnn-nnnn or the form nnnnnnn; the [BCOCA-4-240]
hyphen, if present, is ignored and the other 7 digits must be numeric. These 7 digits will be placed
in the new postal code field of the bar code symbol.
2. If the next character is a hyphen, it is ignored and is not used in generating the bar code symbol. [BCOCA-4-241]
3. The remainder of the BSA data is the address indication number that can contain numbers, [BCOCA-4-242]
hyphens, and alphabetic characters (A–Z). Each number and each hyphen represents one digit in
the bar code symbol; each alphabetic character is represented by a combination of a control code
(CC1, CC2, or CC3) and a numerical code and shall be handled as two digits in the bar code
symbol. 13 digits of this address indication number data will be placed in the address indication
number field of the bar code symbol.
• If less than 13 additional digits are present, the shortage shall be filled in with the bar code [BCOCA-4-243]
corresponding to control code CC4 up to the 13th digit.
• If more than 13 additional digits are present, the first 13 digits will be used and the remainder [BCOCA-4-244]
ignored with no exception condition reported. However, if the 13th digit is the control code for an
alphabetic (A–Z) character, only the control code is included and the numeric part is omitted.
X'01' Present a Japan Postal Bar Code symbol directly from the bar code data.
Each valid character in the BSA data field is converted into a bar/space pattern with no validity or
length checking. The printer will not generate start, stop, and check digits.
T o produce a valid bar code symbol, the bar code data must contain a start code, a 7-digit new postal
code, a 13-digit address indication number, a valid check digit, and a stop code. The new postal code
must consist of 7 numeric digits. The address indication number must consist of 13 characters that can
be numeric, hyphen, or control characters (CC1 through CC8). The following table lists the valid code
points for modifier X'01'.
Japan Postal Bar Code [BCOCA-4-245]




Table 15. Valid Code Points for Direct Input to a Japan Postal Bar Code
Character Code Point Character Code Point
start X'4C' 0 X'F0'
stop X'6E' 1 X'F1'
hyphen X'60' 2 X'F2'
CC1 X'5A' 3 X'F3'
CC2 X'7F' 4 X'F4'
CC3 X'7B' 5 X'F5'
CC4 X'E0' 6 X'F6'
CC5 X'6C' 7 X'F7'
CC6 X'50' 8 X'F8'
CC7 X'7D' 9 X'F9'
CC8 X'4D'
Implementation Note:
These code points are EBCDIC-based to match early Japan Postal Bar Code
implementations that used fonts instead of BCOCA; there is no known requirement for
ASCII-based code points.
Japan Postal Bar Code [BCOCA-4-246]




Data Matrix and GS1 DataMatrix (modifier values X'00' and X'01')
Data Matrix 2D Symbol
(encoding A1B2C3D4E5F6G7H8I9J0K1L2)
This is a two-dimensional matrix bar code symbology defined originally as an AIM International Symbol
Specification.
X'00' Present a Data Matrix Bar Code symbol using Error Checking and Correcting (ECC) algorithm 200.
The symbol must be one of the originally-defined Data Matrix symbols, which comprised 24 square
and 6 rectangular symbols.
X'01' Present a Data Matrix Bar Code symbol using ECC algorithm 200. The symbol must be either:
• One of the originally-defined 24 square and 6 rectangular symbols, or [BCOCA-4-247]
• One of the additional 18 rectangular symbols defined in the Extended Rectangular Data Matrix [BCOCA-4-248]
(DMRE) specification.
The bar code data is assumed to start with the default character encodation (ECI 000003 = ISO 8859-1). This
is an international Latin 1 code page that is equivalent to the IBM ASCII code page 819. T o change to a
different character encodation within the data, the ECI protocol as defined in the AIM International Symbology
Specification - Data Matrix, must be used. This means that whenever a byte value of X'5C' (an escape code) is
encountered in the bar code data, the next six characters must be decimal digits (byte values X'30' to X'39') or
the next character must be another X'5C'. When the X'5C' character is followed by six decimal digits, the six
decimal digits are interpreted as the ECI number that changes the interpretation of the characters that follow
the decimal digits. When the X'5C' character is followed by another X'5C' character, this is interpreted as one
X'5C' character (that is a backslash in the default character encodation); alternatively, the escape-sequence
handling flag (see page 107) can be used to treat X'5C' as a normal character.
Since the default character encodation for this bar code is ASCII, the EBCDIC-to-ASCII translation flag (see
page 107) can be used when all of the data for the bar code is EBCDIC. If the bar code data contains more
than one character encodation or if the data needs to be encoded within the bar code symbol in a form other
than the default character encodation (such as, in EBCDIC), the bar code data should begin in the default
encodation, the EBCDIC-to-ASCII translation flag should be set to B'0', and the ECI protocol should be used to
switch into the other encodation.
Note: The Data Matrix bar code is used for many applications and some of these applications have specific
rules that must be followed when specifying the parameters and data for the bar code object. For
example, some applications require a particular encodation scheme; therefore, an IPDS printer used to
print the symbol must support both Data Matrix and the encodation scheme option (STM property pair
X'1303'). Examples of Data Matrix applications with special rules include the following:
• The GS1 DataMatrix symbology is controlled by the GS1 standards organization and is described in [BCOCA-4-249]
the GS1 General Specifications.
• The Royal Mail Complex Mail Data Marks (CMDM) symbology is controlled by Royal Mail and is [BCOCA-4-250]
described in the Royal Mail Mailmark Definition Document. CMDM symbols use the C40 encodation
scheme.
Data Matrix




MaxiCode (modifier value X'00')
MaxiCode 2D Symbol
This is a two-dimensional matrix bar code symbology as defined in the AIM International Symbology
Specification – MaxiCode.
X'00' Present a MaxiCode bar code symbol.
The bar code data is assumed to start with the default character encodation (ECI 000003 = ISO 8859-1). This
is an international Latin 1 code page that is equivalent to the IBM ASCII code page 819. T o change to a
different character encodation within the data, the ECI protocol as defined in section 4.15.2 of the AIM
International Symbology Specification - MaxiCode, must be used. This means that whenever a byte value of
X'5C' (an escape code) is encountered in the bar code data, the next six characters must be decimal digits
(byte values X'30' to X'39') or the next character must be another X'5C'. When the X'5C' character is followed
by six decimal digits, the six decimal digits are interpreted as the ECI number that changes the interpretation of
the characters that follow the decimal digits. When the X'5C' character is followed by another X'5C' character,
this is interpreted as one X'5C' character (that is a backslash in the default character encodation); alternatively,
the escape-sequence handling flag (see page 120) can be used to treat X'5C' as a normal character. The X'5C'
character is allowed anywhere in the bar code data except for Modes 2 and 3 where it is not allowed in the
Primary Message portion of the data.
Since the default character encodation for this bar code is ASCII, the EBCDIC-to-ASCII translation flag (see
page 120) can be used when all of the data for the bar code is EBCDIC. If the bar code data contains more
than one character encodation or if the data needs to be encoded within the bar code symbol in a form other
than the default character encodation (such as, in EBCDIC), the bar code data should begin in the default
encodation, the EBCDIC-to-ASCII translation flag should be set to B'0', and the ECI protocol should be used to
switch into the other encodation.
Note: Care should be taken when using the End-of-Transmission (EOT) character; many MaxiCode examples
show EOT as the last character of the data. It has been reported that for MaxiCode symbols that will be
scanned by the United Parcel Service (the originator of MaxiCode), the EOT must not be followed by
additional characters. However, the MaxiCode symbology specification does not contain any special
rules for handling EOT characters or data found after an EOT . Because of this inconsistency, how data
after an EOT is handled is device specific; some BCOCA receivers encode all of the data, some ignore
data after EOT , and some provide a device-specific way to inform the BCOCA receiver how to handle
data after EOT .
MaxiCode




PDF417 (modifier values X'00' and X'01')
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
3 Data Symbol Characters [BCOCA-4-251]
per Row
3 Data Symbol Characters [BCOCA-4-252]
per Row
13 Rows
This is a two-dimensional stacked bar code symbology as defined in the AIM Uniform Symbology Specification
– PDF417.
X'00' Present a full PDF417 bar code symbol.
X'01' Present a truncated PDF417 bar code symbol, for use in a relatively clean environment in which
damage to the symbol is unlikely. This version omits the right row indicator and simplifies the stop
pattern into a single module width bar.
The bar code data is assumed to start with the default character encodation (GLI 0) as defined in Table 5 of the
Uniform Symbology Specification PDF417. T o change to another character encodation, the GLI (Global Label
Identifier) protocol, as defined in the Uniform Symbology Specification PDF417, must be used. This means that
whenever a byte value of X'5C' (an escape code) is encountered in the bar code data, the next three
characters must be decimal digits (byte values X'30' to X'39') or the next character must be another X'5C'
character. When the X'5C' character is followed by three decimal digits, this is called an escape sequence.
When the X'5C' character is followed by another X'5C' character, this is interpreted as one X'5C' character (that
is a backslash in the default character encodation); alternatively, the escape-sequence handling flag (see page
127) can be used to treat X'5C' as a normal character.
T o identify a new GLI, there must be two or three escape sequences in a row. The first escape sequence must
be “\925”, “\926”, or “\927” (as defined by GLI 0). If the first escape sequence is “\925” or “\927”, there must be
one other escape sequence following containing a value from “\000” to “\899”. If the first escape sequence is
“\926”, there must be two more escape sequences following with each escape sequence containing a value
from “\000” to “\899”. For example, to switch to GLI 1 (ISO 8859-1 that is equivalent to IBM ASCII code page
819), the bar code data would contain the character sequence “\927\001”. The “\927” escape sequence is used
for GLI values from 0 to 899. The “\926” escape sequence is used for GLI values from 900 to 810,899. The
“\925” escape sequence is used for GLI values from 810,900 to 811,799. For more information about how
these values are calculated refer to section 2.2.6 of the PDF417 symbology specification.
In addition to transmitting GLI numbers, the escape sequence is used to transmit other codewords for
additional purposes. The special codewords are given in Table 8 in Section 2.7 of the PDF417 symbology
specification. The special codewords “\903” to “\912” and “\914” to “\920” are reserved for future use. The
BCOCA receiver will accept these special escape sequences and add them to the bar code symbol, resuming
with normal encoding with the character following that escape sequence.
The special codeword “\921” instructs the bar code reader to interpret the data contained within the symbol for
reader initialization or programming. This escape sequence is only allowed at the beginning of the bar code
data.
The special codewords “\922”, “\923”, and “\928” are used for coding a Macro PDF417 Control Block as
defined in section G.2 of the PDF417 symbology specification. These codewords must not be used within the
BCOCA data; instead a Macro PDF417 Control Block can be specified in the special-function parameters.
Exception condition EC-2100 exists if one of these escape sequences is found in the bar code data.
PDF417




Since the default character encodation for this bar code is GLI 0 (an ASCII code page that is similar to IBM
code page 437), the EBCDIC-to-ASCII translation flag (see page 125) can be used when all of the data for the
bar code is EBCDIC. If the bar code data contains more than one character encodation, or if the data needs to
be encoded within the bar code symbol in a form other than the default character encodation (such as, in
EBCDIC), the bar code data should begin in the default encodation, the EBCDIC-to-ASCII translation flag
should be set to B'0', and the GLI protocol should be used to switch into the other encodation.
PDF417




Australia Post Bar Code (modifier values X'01' through X'08')
Australia Post Bar Code
Customer Barcode 2 using Table C
(encoding 56439111ABA 9)
This is a bar code symbology defined by Australia Post for use in Australian postal systems. There are several
formats of this bar code, that are identified by the modifier byte as follows:
Table 16. Australia Post Modifier Values
Modifier Type of Bar Code Valid Bar Code Data
X'01' Standard Customer Barcode
(format code = 11)
An 8 digit number representing the Sorting Code
X'02' Customer Barcode 2
using Table N
(format code = 59)
An 8 digit number representing the Sorting Code followed by
up to 8 numeric digits representing the Customer
Information
X'03' Customer Barcode 2
using Table C
(format code = 59)
An 8 digit number representing the Sorting Code followed by
up to 5 characters (A–Z, a–z, 0–9, space, #) representing
the Customer Information
X'04' Customer Barcode 2
using proprietary encoding
(format code = 59)
An 8 digit number representing the Sorting Code followed by
up to 16 numeric digits (0–3) representing the Customer
Information; each of the 16 digits specify one of the 4 types
of bar
X'05' Customer Barcode 3
using Table N
(format code = 62)
An 8 digit number representing the Sorting Code followed by
up to 15 numeric digits representing the Customer
Information
X'06' Customer Barcode 3
using Table C
(format code = 62)
An 8 digit number representing the Sorting Code followed by
up to 10 characters (A–Z, a–z, 0–9, space, #) representing
the Customer Information
X'07' Customer Barcode 3
using proprietary encoding
(format code = 62)
An 8 digit number representing the Sorting Code followed by
up to 31 numeric digits (0–3) representing the Customer
Information; each of the 31 digits specify one of the 4 types
of bar
X'08' Reply Paid Barcode
(format code = 45)
An 8 digit number representing the Sorting Code
The proprietary encoding allows the customer to specify the types of bars to be printed directly by using 0 for a
full bar, 1 for an ascending bar, 2 for a descending bar and 3 for a timing bar. If the customer does not specify
enough Customer Information to fill the field, the printer uses a filler bar to pad the field out to the correct
number of bars.
The printer will encode the data using the proper tables, generate the start and stop bars, generate any
needed filler bars, and generate the Reed Solomon ECC bars.
Human-readable interpretation (HRI) can be selected with this bar code type and should be printed above the
symbol. The format control code, Delivery Point Identifier, and customer information field (if any) appears in the
HRI, but the ECC does not.
Australia Post Bar Code [BCOCA-4-253]




QR Code (modifier values X'02' and X'12')
QR Code 2D Symbol
QR Code with Image 2D Symbol
(Image is part of the AFP Consortium logo)
This is a two-dimensional matrix bar code symbology defined as an AIM International T echnical Standard.
X'02' Present a Model 2 QR Code Bar Code symbol as defined in AIM International Symbology
Specification — QR Code.
X'12' Present a QR Code Bar Code symbol as in modifier X'02', but in addition, one or more images can be
placed in conjunction with the QR Code symbol.
The bar code data is assumed to start with the default character encodation (ECI 000020). This is a single–
byte code page representing the JIS8 and Shift JIS character sets; it is equivalent to the IBM ASCII code page
897. T o change to a different character encodation within the data, the ECI protocol as defined in the AIM [BCOCA-4-254]
International “Extended Channel Interpretation (ECI) Assignments”, must be used.
Since the default character encodation for this bar code is ASCII, the EBCDIC-to-ASCII translation flag (see
page 132) can be used in the following manner:
• When all of the input data for the bar code is single–byte EBCDIC using one of the supported code pages [BCOCA-4-255]
(500, 290, or 1027), set the EBCDIC-to-ASCII translation flag to B'1' and select the correct code page in the
conversion parameter.
• When all of the input data for the bar code is mixed-byte EBCDIC AFP Line Data using SO and SI controls [BCOCA-4-256]
(SOSI data), set the EBCDIC-to-ASCII translation flag to B'1' and select the desired conversion value in the
conversion parameter.
If the bar code data contains more than one character encodation or if the data needs to be encoded within the
bar code symbol in a form other than those previously mentioned (such as, in an EBCDIC code page not
supported by the EBCDIC-to-ASCII translation flag), the bar code data must begin in the default encodation,
the EBCDIC-to-ASCII translation flag must be set to B'0', and the ECI protocol must be used to switch into the
other encodation(s).
There must be a quiet zone around the symbol that is at least 4 modules wide on each of the four sides of the
symbol.
QR Code modifier X'12' – QR Code with Image
A QR Code with Image (modifier X'12') bar code produces some number of QR Code symbols in the same way
as a QR Code (modifier X'02') bar code. However, in addition, it can optionally place one or more images in
conjunction with each QR Code symbol.
QR Code




See “QR Code with Image Special-Function Parameters” for the details of how the images are
placed.
QR Code




Code 93 (modifier value X'00')
Code 93
(encoding 39OR93
yielding a 1.82 inch wide symbol)
39OR93
This is a linear bar code symbology similar to Code 39, but more compact than Code 39. Code 93 bar code
symbols are made up of a series of characters each of which is represented by 9 modules arranged into 3 bars
with their adjacent spaces. The bars and spaces vary between 1 module wide and 4 modules wide.
X'00' Present a Code 93 bar code symbol as defined in AIM Uniform Symbology Specification — Code 93.
The Code 93 character set contains 47 characters including numeric digits, upper-case alphabetics, four shift
characters (a,b,c,d), and seven special characters. The Code 93 Specification also provides a method of
encoding all 128 ASCII characters by using 2 bar code characters for those ASCII characters that are not in the
standard Code 93 character set. This is sometimes referred to as “Extended Code 93”. In this case, the 2 bar
code characters used to specify the “extended character” will be shown in the Human-Readable Interpretation
(as a
■ followed by the second character) and the bar code scanner will interpret the two-character
combination bar/space pattern appropriately.
The Human-Readable Interpretation of the Start and Stop characters is represented as an open box ( □) and
the shift characters (a,b,c,d) are represented as a filled box ( ■).
There must be a quiet zone preceding and following the symbol that is at least 10 modules wide.
Code 93




Intelligent Mail Barcode (modifier values X'00' through X'03')
Intelligent Mail Barcode
Modifier X’03’
(encoding 01 234 567094  987654321 01234567891)
The Intelligent Mail Barcode symbology 1 limits the symbol size; therefore BSD element height, height
multiplier, and wide-to-narrow ratio fields are not applicable to this symbology and are ignored by BCOCA
receivers. The module width field allows for two symbol sizes (small and optimal). The small symbol is
approximately 2.68 inches wide and 0.125 inches high. The optimal symbol is approximately 2.95 inches wide
and 0.145 inches high.
The input data is all numeric and consists of 5 data fields. The first four fields are essentially fixed length and
the 5th field can have one of four lengths; the bar code modifier is used to specify the length of the 5th field.
The total length of the input data can be 20, 25, 29, or 31 digits that is defined as follows:
• Barcode ID (2 digits) – assigned by USPS, the 2nd digit must be 0–4; thus, the valid values are: 00-04, 10– [BCOCA-4-257]
14, 20–24, 30–34, 40–44, 50–54, 60–64, 70–74, 80–84, and 90–94
• Service Type ID (3 digits) – assigned by USPS; valid values are 000–999 [BCOCA-4-258]
• Mailer ID fields; 15 digits in the range 000000000000000–999999999999999 [BCOCA-4-259]
– Mailer ID (6 or 9 digits) – assigned by USPS
– Sequence or serial number (9 or 6 digits) – assigned by the mailer
• Routing ZIP Code (0, 5, 9, or 11 digits) – refer to the modifier for valid values; also called Delivery Point ZIP [BCOCA-4-260]
Code
Intelligent Mail Barcode modifier values are defined as follows:
X'00' Present an Intelligent Mail Barcode symbol with no Routing ZIP Code. The input data for this bar code
symbol must be 20 numeric digits.
X'01' Present an Intelligent Mail Barcode symbol with a 5–digit Routing ZIP Code. The input data for this bar
code symbol must be 25 numeric digits; the valid values for the Routing ZIP Code are 00000–99999.
X'02' Present an Intelligent Mail Barcode symbol with a 9–digit Routing ZIP Code. The input data for this bar
code symbol must be 29 numeric digits; the valid values for the Routing ZIP Code are 000000000–
999999999.
X'03' Present an Intelligent Mail Barcode symbol with an 11–digit Routing ZIP Code. The input data for this
bar code symbol must be 31 numeric digits; the valid values for the Routing ZIP Code are
00000000000–99999999999.
Human-Readable Interpretation (HRI) can be printed with an Intelligent Mail Barcode symbol, but HRI is not
used with all types of special services. Refer to Introducing 4-state Customer Barcode for a description of when
HRI is appropriate.
There must be a quiet zone surrounding the symbol (all four sides) that is at least 0.04 inches above and below
and at least 0.125 inches on both sides of the symbol.
Intelligent Mail Barcode
1. The United States Postal Service (USPS) developed this symbology for use in the USPS mail stream and has named it the [BCOCA-4-261]
“Intelligent Mail Barcode”. Originally, BCOCA architecture used the name “USPS Four-State bar code” for this symbology. The bar
code is also known as the “OneCode SOLUTION Barcode” and the “4-state Customer Barcode” and has been abbreviated in several
ways: OneCode (4CB), OneCode (4-CB), 4CB, or 4-CB. [BCOCA-4-262]




Royal Mail RED TAG (deprecated), modifier value X'00'
Royal Mail RED TAG
(encoding 12345 67 2 2505 13 234567)
O O
Note: The RED TAG symbology has been retired by Royal Mail and has also been deprecated in the BCOCA
architecture. For a description of the replacement, refer to “Royal Mail Mailmark (modifier values X'00'
and X'01')”.
The RED TAG bar code symbology is defined and used by Royal Mail Group Ltd. for intelligent mail tracking
and reporting. The RED TAG bar code is a four-state symbol with exactly 51 bars that includes a RED TAG
indicator printed at each end of the symbol.
The Royal Mail RED TAG symbology limits the symbol size; therefore BSD element height, height multiplier,
and wide-to-narrow ratio fields are not applicable to this symbology and are ignored by BCOCA receivers. The
module width field allows for two symbol sizes (small and optimal); the small symbol is approximately 2.13
inches wide and the optimal symbol is approximately 2.22 inches wide.
The input data for the bar code is all numeric and consists of the fields shown in Table 17(in the specified
order). The value ranges are those defined within the first version of the RED TAG symbology specification, but
to allow for future expansion, BCOCA allows a larger range for each field. Values outside of the “RED TAG
Recommended Range” should not be used by the user. The RED TAG data is checked for validity (within the
BCOCA range) and exception condition EC-1202 exists if the data is invalid or insufficient. There must be
exactly 21 numeric digits; if needed, each field is padded on the left with zeroes to fill the field. For example,
“012345672250513234567” would be specified for the following RED TAG input fields:
Account ID = 12345
Product ID = 67
Class = 2
Day = 25
Month = 5
Consignment ID = 13
Item Unique ID = 234567
Table 17. Royal Mail RED TAG (deprecated) Data Field Ranges
External Field Name Source Field Size BCOCA Range RED TAG
Recommended
Range
Account ID Royal Mail 6 bytes 1–213,868 1–200,000
Product ID Royal Mail 2 bytes 0–99 1–99
Class Mailer 1 byte 0–3 1–3
Day Mailer 2 bytes 1–31 1–31
Month Mailer 2 bytes 1–12 1–12
Consignment ID Mailer 2 bytes 0–49 1–49
Item Unique ID Mailer 6 bytes 0–249,999 1–249,999
Royal Mail RED TAG




The Royal Mail RED TAG bar code type only uses one modifier value:
X'00' Present a Royal Mail RED TAG bar code symbol with a RED TAG indicator printed at each end of
the symbol. The RED TAG indicator is a capital “O” printed in Arial 20 point bold type.
Human-Readable Interpretation (HRI) is not used with the Royal Mail RED TAG symbol.
There must be a 5 mm quiet zone surrounding the symbol (all four sides); the RED TAG indicator is outside of
the quiet zone.
The origin of the bar code symbol is defined to be the top-left corner of an imaginary rectangle of minimum size
that bounds the bar and space pattern. Since the RED TAG indicator and the quiet zone are outside of the
imaginary rectangle, it is important to make sure that the symbol is positioned at least 10 mm from the left edge
of the bar code presentation space. If any part of the symbol or RED TAG indicator falls outside the bar code
presentation space, exception ID EC-1100 exists.
Royal Mail RED TAG




GS1 DataBar
GS1 DataBar is a family of bar codes that is designed for items for which traditional linear bar codes are too
large or are inconveniently shaped. The GS1 DataBar family has seven versions (selected with modifiers
X'00' – X'04' and X'11' – X'1B'):
The first group requires 14 numeric digits as input. There are four versions in this group that have identical
encodation rules and structure, but different shapes:
– GS1 DataBar Omnidirectional (modifier X'00')
– GS1 DataBar Truncated (modifier X'01')
– GS1 DataBar Stacked (modifier X'02')
– GS1 DataBar Stacked Omnidirectional (modifier X'03')
The second group, called GS1 DataBar Limited (modifier X'04'), is structurally different, has different
encodation rules, and requires 14 numeric digits as input (the first digit must be 0 or 1).
The third group, called GS1 DataBar Expanded, has yet another symbology structure and different
encodation rules. The format of the input data for GS1 DataBar Expanded is exactly the same as the input
data for a UCC/EAN 128 bar code. There are two versions of GS1 DataBar Expanded:
– GS1 DataBar Expanded (modifier X'11')
– GS1 DataBar Expanded Stacked (modifiers X'12' – X'1B')
The GS1 DataBar Omnidirectional, Stacked Omnidirectional, Expanded, and Expanded Stacked symbols can
be read in segments by omnidirectional scanners.
The height of the symbol is different for each version (modifier value). Because the first element of each bar
code symbol is a space, no quiet zone is needed for this bar code.
Human-Readable Interpretation (HRI) can be printed below a GS1 DataBar symbol. The content of the HRI
depends on the version of the symbol:
• For modifiers X'00' – X'04', the HRI consists of implied application ID 01 in parentheses followed by the 14 [BCOCA-4-263]
digit input data. The implied application ID is not part of the input data, nor is it included within the symbol. An
example of HRI for GS1 DataBar symbols is shown in each modifier description.
The input data consists of 14 digits with the last (14th) digit being an implied check digit; this check digit is not
validated and is not used in building the bar code symbol, however all 14 of the input digits appear in the
HRI.
• For modifiers X'11' – X'1B', the HRI consists of the input data with the application IDs surrounded by [BCOCA-4-264]
parentheses and the FNC1 characters suppressed.
Modifier X'00'
GS1 DataBar Omnidirectional
(encoding 20012345678909)
(01)20012345678909
Present a GS1 DataBar Omnidirectional bar code symbol. The height of the symbol must be
greater than or equal to 33 times the module width.
The input data for this bar code symbol is 14 numeric digits that conform to application
identifier 01. The bar code receiver will compact the data, create guard patterns, create data-
character patterns, calculate a checksum, create finder patterns, and generate a GS1 DataBar
Omnidirectional bar code symbol.
GS1 DataBar




Modifier X'01'
GS1 DataBar Truncated
(encoding 20012345678909)
(01)20012345678909
Present a GS1 DataBar Truncated bar code symbol. This is the same as the standard
Omnidirectional symbol except that its height is reduced to a minimum of 13 times the module
width.
The input data for this bar code symbol is 14 numeric digits that conform to application
identifier 01. The bar code receiver will compact the data, create guard patterns, create data-
character patterns, calculate a checksum, create finder patterns, and generate a GS1 DataBar
Truncated bar code symbol.
Modifier X'02'
GS1 DataBar Stacked
(encoding 20012345678909)
(01)20012345678909
Present a GS1 DataBar Stacked bar code symbol. This is the same as the standard
Omnidirectional symbol except that its height is fixed and it is presented in two stacked rows
with a separator pattern between the rows.
The input data for this bar code symbol is 14 numeric digits that conform to application
identifier 01. The bar code receiver will compact the data, create guard patterns, create data-
character patterns, calculate a checksum, create finder patterns, and generate a GS1 DataBar
Stacked bar code symbol.
Modifier X'03'
GS1 DataBar Stacked Omnidirectional
(encoding 20012345678909)
(01)20012345678909
Present a GS1 DataBar Stacked Omnidirectional bar code symbol. This is the same as the
standard Omnidirectional symbol except that it is presented in two stacked rows with a
separator pattern between the rows. Like the Omnidirectional symbol, the height of each of the
two rows must be greater than or equal to 33 times the module width.
The input data for this bar code symbol is 14 numeric digits that conform to application
identifier 01. The bar code receiver will compact the data, create guard patterns, create data-
character patterns, calculate a checksum, create finder patterns, and generate a GS1 DataBar
Stacked Omnidirectional bar code symbol.
GS1 DataBar




Modifier X'04'
GS1 DataBar Limited
( ncoding )e 15012345678907
(01)15012345678907
Present a GS1 DataBar Limited bar code symbol. The height of the symbol must be greater
than or equal to 10 times the module width.
The input data for this bar code symbol is 14 numeric digits that conform to application
identifier 01; however, the first digit must be 0 or 1. The bar code receiver will compact the
data, create guard patterns, create data-character patterns, calculate a checksum, create a
finder pattern, and generate a GS1 DataBar Limited bar code symbol.
Modifier X'11'
GS1 DataBar Expanded
(01)98898765432106(3202)012345(15)991231
(encoding  0198898765432106320201234515991231)
Present a GS1 DataBar Expanded bar code symbol. The height of the symbol must be greater
than or equal to 34 times the module width.
The format of the input data for this bar code symbol (up to 74 numeric digits or up to 41
alphabetic characters) is similar to that of a UCC/EAN 128 bar code; refer to the description on
page 57 for a description of UCC/EAN 128. The difference is that UCC/EAN 128 symbols
must begin with an FNC1 character. The data for GS1 DataBar Expanded bar code is of the
form:
ai, data, [m], [FNC1], ai, data, [m], [FNC1], ..., ai, data, [m]
The GS1 DataBar Expanded data is checked for validity and exception condition EC-1200
exists if one or more of the following conditions are encountered:
• Invalid application identifier (ai) value encountered [BCOCA-4-265]
• Data for an ai doesn't match the ai definition [BCOCA-4-266]
• Insufficient (or no) data following an ai [BCOCA-4-267]
• T oo much data for an ai [BCOCA-4-268]
• Invalid use of FNC1 character [BCOCA-4-269]
Note: Because the data for an Expanded symbol is similar to the data for a UCC/EAN 128
symbol, BCOCA receivers will tolerate FNC1 characters that precede the first ai by
ignoring them.
The bar code receiver will compact the data, pad the binary data with the B'00100' padding
string until sufficient symbol characters are built, create guard patterns, create data-character
patterns, calculate a check character, create finder patterns, and generate a GS1 DataBar
Expanded bar code symbol.
GS1 DataBar




Modifiers X'12' – X'1B'
GS1 DataBar Expanded Stacked
(01)98898765432106(3202)012345(15)991231
(encoding  0198898765432106320201234515991231)
Present a GS1 DataBar Expanded Stacked bar code symbol. This is the same as the standard
GS1 DataBar Expanded symbol except that it is presented in stacked rows with a separator
pattern between the rows. Expanded Stacked symbols are typically narrower than the
equivalent Expanded version because they allow the bar code to trade vertical space for
horizontal space. The specific modifier value provides control over symbol width by identifying
a requested number of symbol characters per row as shown in the following table:
Table 18. Modifier Values for a GS1 DataBar Expanded Stacked Bar Code
Modifier
Value
Requested Number of Symbol
Characters per Row
Width of Symbol in Modules
X'12' 2 per row 53 modules
X'13' 4 per row 102 modules
X'14' 6 per row 151 modules
X'15' 8 per row 200 modules
X'16' 10 per row 249 modules
X'17' 12 per row 298 modules
X'18' 14 per row 347 modules
X'19' 16 per row 396 modules
X'1A' 18 per row 445 modules
X'1B' 20 per row 494 modules
Note: T o determine the target width of the symbol in inches for a particular modifier value, multiply
(number of modules from the table) times (module width). For example, if modifier X'1A' is specified
and the module width is 10 mils, the target symbol width is 445 * 0.010 = 4.45 inches. If instead
modifier X'14' is specified, the target symbol width is 151 * 0.010 = 1.51 inches. The height of the
stacked symbol depends on how much data is encoded and how many rows were used, but in
general a wide symbol will have fewer rows and therefore be shorter than a narrow symbol.
The BCOCA receiver will encode the input data to determine how many symbol characters are
needed and will then attempt to create an Expanded Stacked symbol that contains the
requested number of symbol characters per row. The receiver must work within the constraints
defined by the GS1 DataBar symbology:
• There can be between two and eleven rows for an Expanded Stacked symbol; an Expanded [BCOCA-4-270]
symbol has one row.
• Each row, except for the bottom row, must have an even number of symbol characters. [BCOCA-4-271]
GS1 DataBar




• The bottom row must contain a minimum of two symbol characters (with padding added to [BCOCA-4-272]
the last symbol character if necessary).
The BCOCA receiver will attempt to create an Expanded Stacked symbol for which each row
contains the requested number of symbol characters. Depending on the number of actual
symbol characters generated, the bottom row might be shorter than the others or there might
be only one row (an Expanded symbol). When there is insufficient input data to generate the
minimum required number of symbol characters, the BCOCA receiver will continue to pad the
binary data with the B'00100' padding string until sufficient symbol characters are built (some
of these might consist only of pad bits). For example, there must be at least two symbol
characters in the bottom row and the encodation methods require at least four symbol
characters.
The height of each row is 34 times the module width and there is a 3 module high separator
pattern between each row. The total symbol height is a multiple of the module width, which is
34*(number of rows)+3*(number of separator patterns).
The format of the input data for this bar code symbol is exactly the same as for a GS1 DataBar
Expanded symbol. The bar code receiver will compact the data, pad the binary data with the
B'00100' padding string until sufficient symbol characters are built, create guard patterns,
create data-character patterns, calculate a checksum, create finder patterns, and generate a
GS1 DataBar Expanded Stacked bar code symbol (or an Expanded symbol if the requested
number of symbol characters is larger than the number of generated symbol characters).
Note: The GS1 DataBar symbology is controlled by the GS1 standards organization and is described in GS1
General Specifications.
GS1 DataBar




Royal Mail Mailmark (modifier values X'00' and X'01')
Royal Mail Mailmark
(bar code type C)
DAATATTTADTAATTFADDDDTTFTFDDDDFFDFDAFTADDTFFTDDATADTTFATTDAFDTFDDA
The Royal Mail Mailmark symbology is defined and used by Royal Mail Group Ltd. for intelligent mail tracking
and reporting. This bar code is a four-state symbol that has two variations, each with a fixed number of bars:
1. Barcode C (66 bars) – A variable content code that has a unique identifier (ID) and includes Postcode and [BCOCA-4-273]
Delivery Point information
2. Barcode L (78 bars) – A variable content code that has a unique ID and includes Postcode and Delivery [BCOCA-4-274]
Point information
The Royal Mail Mailmark symbology limits the symbol size; therefore BSD element height, height multiplier,
and wide-to-narrow ratio fields are not applicable to this symbology and are ignored by BCOCA receivers. The
module width field allows for two symbol sizes (small and optimal); the small symbol ranges from 2.8 inches
wide to 3.3 inches wide (depending on the symbol variation) and the optimal symbol ranges from 3.1 inches
wide to 3.7 inches wide. Refer to Table 12 for the specific dimensions.
The input data for the bar code is alphanumeric and consists of the fields shown in the Royal Mail Mailmark
Definition Document. The data is checked for validity and exception condition EC-1204 exists if there is invalid,
insufficient, or too much data.
Human-Readable Interpretation (HRI) is not used with the Royal Mail Mailmark symbol. There must be a 2 mm
quiet zone surrounding the symbol (all four sides).
The origin of the bar code symbol is defined to be the top-left corner of an imaginary rectangle of minimum size
that bounds the bar and space pattern. If any part of the symbol falls outside the bar code presentation space,
exception ID EC-1100 exists.
The Royal Mail Mailmark type uses the following modifier values:
X'00' Barcode C
Present a 66 bar, variable-content-code symbol that has a unique identifier (ID) and includes Postcode
and Delivery Point information.
X'01' Barcode L
Present a 78 bar, variable-content-code symbol that has a unique ID and includes Postcode and
Delivery Point information.
Notes:
1. The four-state Mailmark™ symbology replaces an earlier version called RED TAG. The RED TAG [BCOCA-4-275]
symbology has been retired by Royal Mail and has also been deprecated in the BCOCA architecture.
2. There is also a 2D variation of the Mailmark symbology, called Complex Mail Data Marks (CMDM), which is [BCOCA-4-276]
produced as a Data Matrix symbol. Refer to the Royal Mail Mailmark Definition Document for a description
of Complex Mail Data Marks.
Royal Mail Mailmark




Aztec Code (modifier values X'00' through X'03')
Aztec Code - Full-range
(encoding a 78-character string)
Aztec Code - Compact
(encoding “AFP Consortium”)
Aztec Code - Rune
(encoding X'7B')
This is a two-dimensional matrix bar code symbology defined in ISO/IEC 24778:2008.
An Aztec Code symbol consists of a core symbol in the center, surrounded by two-module-deep data layers of
codewords. As an example, the compact symbol shown above has an 11x11 module core symbol, surrounded
by two data layers (the outside four modules all around the outside of the symbol).
The core symbol is the “Aztec pyramid” in the center of the symbol and the one single layer of modules
surrounding the pyramid. The core symbol contains:
• The finder pattern—that is, the pyramid. [BCOCA-4-277]
• Orientation modules. These are on each corner of the finder pattern, in the surrounding module layer, and [BCOCA-4-278]
allow a scanner to determine which corner of the symbol is the upper-left, and also allow a scanner to
determine if the symbol is being read as a reflection or from behind.
• The mode message. These are modules in the layer surrounding the finder pattern that encode: [BCOCA-4-279]
– The number of two-module-deep layers of codewords surrounding the core—this then defines the exact
size of the symbol in terms of modules.
– The number of codewords used to contain the encoded data.
– Error-correction bits for the mode message.
Due to the fact the core symbol is in the middle of the Aztec Code symbol and defines exactly how many
modules surround the core, there is no need for a quiet zone. Note, however, that there is some belief that
there are scanners that require a quiet zone to reliably read an Aztec Code symbol.
The Aztec Code bar code type uses the following modifier values:
X'00' Full-range
Present a full-range Aztec Code symbol. Such a symbol contains a core symbol of size 15x15
modules, with between 1 and 32 data layers surrounding the core.
X'01' Compact
Present a compact Aztec Code symbol. Such a symbol contains a core symbol of size 11x11 modules,
with between 1 and 4 data layers surrounding the core.
X'02' Rune
Present an Aztec Code rune symbol. Such a symbol contains only a core symbol of size 11x11
modules, and can encode a single byte of data in the mode message layer.
X'03' Smallest compact or full-range
Present the smallest possible Aztec Code symbol that can encode the required information, whether
such a symbol is a compact or a full-range symbol.
The bar code data is assumed to start with the default character encodation (ECI 000003 = ISO 8859-1). This
is an international Latin 1 code page that is equivalent to the IBM ASCII code page 819. T o change to a
different character encodation within the data, the ECI protocol as defined in the AIM International “Extended
Aztec Code




Channel Interpretation (ECI) Assignments”, must be used. This means that whenever a byte value of X'5C' (an
escape code) is encountered in the bar code data, the next six characters must be decimal digits (byte values
X'30' to X'39') or the next character must be another X'5C'. When the X'5C' character is followed by six decimal
digits, the six decimal digits are interpreted as the ECI number that changes the interpretation of the characters
that follow the decimal digits. When the X'5C' character is followed by another X'5C' character, this is
interpreted as one X'5C' character (that is a backslash in the default character encodation); alternatively, the
escape-sequence handling flag (see page 101) can be used to treat X'5C' as a normal character.
Since the default character encodation for this bar code is ASCII, the EBCDIC-to-ASCII translation flag (see
page 101) can be used when all of the data for the bar code is EBCDIC. If the bar code data contains more
than one character encodation or if the data needs to be encoded within the bar code symbol in a form other
than the default character encodation (such as, in EBCDIC), the bar code data should begin in the default
encodation, the EBCDIC-to-ASCII translation flag should be set to B'0', and the ECI protocol should be used to
switch into the other encodation.
Aztec Code




Han Xin Code (modifier value X'00')
Han Xin Code
(encoding “AFP Consortium”)
This is a two-dimensional matrix bar code symbology defined in ISO/IEC 20830:2021.
X'00' Present a Han Xin Code bar code symbol.
The bar code data is assumed to start with the default character encodation (ECI 000003 = ISO 8859-1). This
is an international Latin 1 code page that is equivalent to the IBM ASCII code page 819. T o change to a
different character encodation within the data, such as the GB18030 encodation (ECI 000029) used by
Chinese characters in the Han Xin Code, the ECI protocol as defined in the AIM International “Extended
Channel Interpretation (ECI) Assignments”, must be used. This means that whenever a byte value of X'5C' (an
escape code) is encountered in the bar code data, the next six characters must be decimal digits (byte values
X'30' to X'39') or the next character must be another X'5C'. When the X'5C' character is followed by six decimal
digits, the six decimal digits are interpreted as the ECI number that changes the interpretation of the characters
that follow the decimal digits. When the X'5C' character is followed by another X'5C' character, this is
interpreted as one X'5C' character (that is a backslash in the default character encodation); alternatively, the
escape-sequence handling flag (see page 115) can be used to treat X'5C' as a normal character.
Since the default character encodation for this bar code is ASCII, the EBCDIC-to-ASCII translation flag (see
page 115) can be used when all of the data for the bar code is EBCDIC. If the bar code data contains more
than one character encodation or if the data needs to be encoded within the bar code symbol in a form other
than the default character encodation (such as, in EBCDIC), the bar code data should begin in the default
encodation, the EBCDIC-to-ASCII translation flag should be set to B'0', and the ECI protocol should be used to
switch into the other encodation.
An interesting aspect of the Han Xin Code is that it supports the use of many different modes in its encode
procedure, allowing the efficient encoding of four different categories of Chinese characters, of Unicode UTF-8
characters, and of characters making up a URI. In addition, more standard numeric, text, binary, and
ECI-based text modes exist, making this bar code very flexible in its support of essentially all possible
characters.
There must be a quiet zone around the symbol that is at least 3 modules wide on each of the four sides of the
symbol.
Han Xin Code




Check Digit Calculation Methods
Some bar code types and modifiers call for the calculation and presentation of check digits. Check digits are a
method of verifying data integrity during the bar coding reading process. Except for UPC/CGPC Version E, the
check digit is always presented in the bar code bar and space patterns, but is not always presented in the HRI.
The following table shows the check digit calculation methods for each bar code type and the presence or
absence of the check digit in the HRI. [BCOCA-4-280]
### Table 19. Check Digit Calculation Methods
| Type | Bar Code Symbology | Modifier | In HRI? | Check Digit Calculation |
| :--- | :--- | :--- | :---: | :--- |
| X'01' | Code 39 (3-of-9 Code), AIM USS-39 | X'02' | Yes | Modulo 43 of the sum of the data characters' numerical values as described in a Code 39 specification. The start and stop codes are not included in the calculation. [BCOCA-4-281]|
| X'02' | MSI (modified Plessey code) | X'02' – X'09' | No | **IBM Modulus 10 check digit:**<br>1. Multiply each digit of the original number by a weighting factor of 1 or 2 as follows: multiply the units digit by 2, the tens digit by 1, the hundreds digit by 2, the thousands digit by 1, and so forth.<br>2. Sum the digits of the products from step 1. This is not the same as summing the values of the products.<br>3. The check digit is described by the following equation where "sum" is the resulting value of step 2:<br>(10 - (sum modulo 10)) modulo 10<br><br>**IBM Modulus 11 check digit:**<br>1. Multiply each digit of the original number by a repeating weighting factor pattern of 2, 3, 4, 5, 6, 7 as follows: multiply the units digit by 2, the tens digit by 3, the hundreds digit by 4, the thousands digit by 5, and so forth.<br>2. Sum the products from step 1.<br>3. The check digit depends on the bar code modifier. The check digit as the remainder is described by the following equation where "sum" is the resulting value of step 2:<br>(sum modulo 11)<br>The check digit as 11 minus the remainder is described by the following equation:<br>(11 - (sum modulo 11)) modulo 11<br><br>**NCR Modulus 11 check digit:**<br>1. Multiply each digit of the original number by a repeating weighting factor pattern of 2, 3, 4, 5, 6, 7, 8, 9 as follows: multiply the units digit by 2, the tens digit by 3, the hundreds digit by 4, the thousands digit by 5, and so forth.<br>2. Sum the products from step 1.<br>3. The check digit depends on the bar code modifier. The check digit as the remainder is described by the following equation where "sum" is the resulting value of step 2:<br>(sum modulo 11)<br>The check digit as 11 minus the remainder is described by the following equation:<br>(11 - (sum modulo 11)) modulo 11 [BCOCA-4-282]|
| X'03' | UPC/CGPC Version A | X'00' | Yes | **UPC/EAN check digit calculation:**<br>1. Multiply each digit of the original number by a weighting factor of 1 or 3 as follows: multiply the units digit by 3, the tens digit by 1, the hundreds digit by 3, the thousands digit by 1, and so forth.<br>2. Sum the products from step 1.<br>3. The check digit is described by the following equation, where "sum" is the resulting value of step 2:<br>(10 - (sum modulo 10)) modulo 10 [BCOCA-4-283]|
| X'05' | UPC/CGPC Version E | X'00' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-284]|
| X'08' | EAN 8 (includes JAN-short) | X'00' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-285]|
| X'09' | EAN 13 (includes JAN-standard) | X'00' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-286]|
| X'0A' | Industrial 2-of-5 | X'02' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-287]|
| X'0B' | Matrix 2-of-5 | X'02' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-288]|
| X'0C' | Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 | X'02' – X'04' | Yes | See X'03' – UPC/CGPC Version A [BCOCA-4-289]|
| X'0D' | Codabar, 2-of-7, AIM USS-Codabar | X'02' | Varies by receiver | **Codabar check digit calculation:**<br>1. Sum of the data characters' numerical values as described in a Codabar specification. All data characters are used, including the start and stop characters.<br>2. The check digit is described by the following equation where "sum" is the resulting value of step 1:<br>(16 - (sum modulo 16)) modulo 16 [BCOCA-4-290]|
| X'11' | Code 128, GS1-128, Intelligent Mail Container Barcode, Intelligent Mail Package Barcode, UCC/EAN 128, AIM USS-128 | X'02' – X'06' | No | **Code 128 check digit calculation:**<br>1. Going left to right starting at the start character, sum the value of the start character and the weighted values of data and special characters. The weights are 1 for the first data or special character, 2 for the second, 3 for the third, and so forth. The stop character is not included in the calculation.<br>2. The check digit is modulo 103 of the resulting value of step 1. [BCOCA-4-291]|
| X'18' | POSTNET (deprecated) and PLANET (deprecated) | X'00' – X'04' | NA | The check digit is (10 - (sum modulo 10)) modulo 10, where sum is the sum of the user data from the BSA data field. [BCOCA-4-292]|
| X'1A' | RM4SCC and Dutch KIX | X'00' | NA | For RM4SCC, the checksum digit is calculated using an algorithm that weights each of the 4 bars within a character in relation to its position within the character. Dutch KIX (modifier X'01') does not use a checksum digit. [BCOCA-4-293]|
| X'1B' | Japan Postal Bar Code | X'00' | NA | **The Japan Postal Bar Code check digit calculation:**<br>1. Convert each character in the bar code data into decimal numbers. Numeric characters are converted to decimal, each hyphen character is converted to the number 10, each alphabetic character is converted to two numbers according to the symbology definition. For example, A becomes "11 and 0", B becomes "11 and 1", ..., J becomes "11 and 9", K becomes "12 and 0", L becomes "12 and 1", ..., T becomes "12 and 9", U becomes "13 and 0", V becomes "13 and 1", ..., and Z becomes "13 and 5".<br>2. Sum the resulting decimal numbers and calculate the remainder modulo 19.<br>3. The check digit is (19 minus the remainder) modulo 19. [BCOCA-4-294]|
| X'1C' | Data Matrix, GS1 DataMatrix (2D bar code) | X'00' – X'01' | NA | The Data Matrix symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-295]|
| X'1D' | MaxiCode (2D bar code) | X'00' | NA | The MaxiCode symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-296]|
| X'1E' | PDF417 (2D bar code) | X'00' – X'01' | NA | The PDF417 symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-297]|
| X'1F' | Australia Post Bar Code | X'01' – X'08' | No | The Australia Post Bar Code uses a Reed Solomon error correction code based on Galois Field 64. [BCOCA-4-298]|
| X'20' | QR Code, QR Code with Image (2D bar code) | X'02', X'12' | NA | The QR Code symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-299]|
| X'21' | Code 93 | X'00' | No | Both check digits (C and K) are calculated as Modulo 47 of the sum of the products of the data-character numerical values as described in the Code 93 specification and a weighting sequence. The start and stop codes are not included in the calculation. [BCOCA-4-300]|
| X'22' | Intelligent Mail Barcode | X'00' – X'03' | No | There is no check digit, but error detection and correction is added as part of the encoding process. Refer to United States Postal Service Specification USPS-B-3200, Barcode, 4-State Customer. [BCOCA-4-301]|
| X'23' | Royal Mail RED TAG (deprecated) | X'00' | No | There is no check digit, but error detection and correction is added as part of the encoding process. Refer to Royal Mail RED TAG Mailpiece Requirements. [BCOCA-4-302]|
| X'24' | GS1 DataBar | X'00' – X'04' and X'11' – X'1B' | No | There is no check digit, but an error detection checksum is calculated and is contained within the finder patterns. Refer to GS1 General Specifications. [BCOCA-4-303]|
| X'25' | Royal Mail Mailmark | X'00'–X'01' | No | There is no check digit, but error detection and correction is added as part of the encoding process to ensure at least 25% error correction. [BCOCA-4-304]|
| X'26' | Aztec Code (2D bar code) | X'00' – X'03' | NA | The Aztec Code symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-305]|
| X'27' | Han Xin Code (2D bar code) | X'00' | NA | The Han Xin Code symbology uses a Reed-Solomon Error Checking and Correcting (ECC) algorithm. [BCOCA-4-306]|




Bar Code Symbol Data (BSA)
The BSA data structure contains the parameters to position the bar code symbol within a bar code
presentation space and the data to be encoded. The data is encoded according to the parameters specified in
the Bar Code Symbol Descriptor (BSD) data structure.
The format of the BSA data structure follows: [BCOCA-4-307]
### Table 20. Bar Code Symbol Data (BSA) Data Structure
| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | BITS | Bar code flags [BCOCA-4-308]| | | | |
| | bit 0 | HRI | B'0'<br>B'1' | HRI is presented<br>HRI not presented | B'0'<br>B'1' | B'0'<br>B'1' [BCOCA-4-309]|
| | bits 1–2 | Position | B'00'<br>B'01'<br>B'10' | Default<br>HRI below<br>HRI above | B'00'<br>B'01'<br>B'10' | B'00'<br>B'01'<br>B'10' [BCOCA-4-310]|
| | bit 3 | SSCAST | B'0'<br>B'1' | Asterisk is not presented<br>Asterisk is presented | B'0'<br>B'1' | B'0'<br>B'1' [BCOCA-4-311]|
| | bit 4 | | B'0' | Retired item 21 | B'0' | B'0' [BCOCA-4-312]|
| | bit 5 | Suppress bar code symbol | B'0'<br>B'1' | Bar code suppression:<br>Present symbol<br>Suppress symbol | B'0' | B'0'<br>B'1' [BCOCA-4-313]|
| | bit 6 | Suppress blanks | B'0'<br>B'1' | Desired method of adjusting for trailing blanks:<br>Don't suppress<br>Suppress and adjust | B'0' | B'0' [BCOCA-4-314]|
| | bit 7 | | B'0' | Retired item 3 | B'0' | B'0' [BCOCA-4-315]|
| 1–2 | UBIN | $X$ offset | X'0001'–X'7FFF' | $X_{bc}$-coordinate of the symbol origin in the bar code presentation space | X'0001'–X'7FFF' (Refer to note) | X'0001'–X'7FFF' (Refer to note) [BCOCA-4-316]|
| 3–4 | UBIN | $Y$ offset | X'0001'–X'7FFF' | $Y_{bc}$-coordinate of the symbol origin in the bar code presentation space | X'0001'–X'7FFF' (Refer to note) | X'0001'–X'7FFF' (Refer to note) [BCOCA-4-317]|
| 5–n | | Special functions | See field description | Special-function information that is specific to the bar code type (Only used with: Aztec Code, Data Matrix, Han Xin Code, Intelligent Mail Package Barcode, MaxiCode, PDF417, QR Code, QR Code with Image) | Not supported in BCD1 | See field description [BCOCA-4-318]|
| n+1 to end | UNDF | Data | Any value defined for the bar code type selected by the BSD | Data to be encoded | Any value defined for the bar code type selected by the BSD | Any value defined for the bar code type selected by the BSD [BCOCA-4-319]|
Bar Code Symbol Data (BSA) [BCOCA-4-320]




Note: The BCD1 and BCD2 range for these fields has been specified assuming a unit of measure of 1/1440 of
an inch. Many receivers support the BCD1 or BCD2 subset plus additional function. If a receiver
supports additional units of measure, the BCOCA architecture requires the receiver to support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-unit Range Conversion Algorithm” on
page 21.
The following is a description of the fields defined in the BSA data structure and applicable exception
conditions. The standard action to be taken for all exception conditions is to report the exception condition,
terminate the bar code object processing, and continue processing with the next object.
Byte 0 Flags
The flags specify attributes specific to this bar code symbol.
The HRI and Position flags indicate the presence and the position of the human-readable
interpretation (HRI) of the encoded data. These flags are ignored for symbologies that do not
allow HRI; the symbologies for which the HRI flags are ignored are: Aztec Code, Data Matrix,
Han Xin Code,
Japan Postal Bar Code, MaxiCode, PDF417, POSTNET (deprecated), QR
Code, QR Code with Image, RM4SCC, Royal Mail RED TAG (deprecated), and Royal Mail
Mailmark.
Bit 0 HRI
If bit 0 is B'0', the HRI is presented.
If bit 0 is B'1', the HRI is not presented.
Bits
1–2
Position
The HRI position flags are used when a bar code symbol and HRI is to be presented.
If the bar-code-symbol-suppression flag (bit 5) is B'1', the HRI position flags are
ignored and should be set to B'00'.
B'00' The presentation device default is used for positioning the HRI.
B'01' The HRI is presented below the bar code symbol.
B'10' The HRI is presented above the bar code symbol.
B'11' Exception condition EC-1000 exists.
Notes:
1. HRI for GS1 DataBar, Intelligent Mail Container Barcode, and Intelligent Mail [BCOCA-4-321]
Package Barcode symbols must be positioned below the bar code symbol. The
position flags (bits 1–2) are ignored for these symbols. HRI for Australia Post Bar
Code should be positioned above the symbol.
2. For the UPC family only, some IPDS printers ignore the position settings and [BCOCA-4-322]
place the HRI as specified in the symbology specification. Specifically, the location
of the regular symbol HRI is specified to be below the bars and the supplement
symbol HRI above the bars. Other IPDS printers require the position bits to be set
according to the symbology specification.
3. If either the UPC or EAN Two-Digit and Five-Digit Supplemental bar code is [BCOCA-4-323]
selected in the BSD TYPE field (X'06' , X'07', X'16', or X'17' respectively) and if the
BSD MOD (modifier) field has a value other than X'00', the position bits cannot be
properly set to indicate the HRI locations for both the regular and supplemental
symbol. For these cases, the position bits must be set to the default value setting
(B'00').
Bar Code Symbol Data (BSA) [BCOCA-4-324]




Bit 3 SSCAST
This flag is used for Code 39 only and is ignored for all other symbologies.
If bit 3 is B'0', no asterisk is presented as the HRI for Code 39 bar code start and stop
characters.
If bit 3 is B'1', an asterisk is presented as the HRI for Code 39 bar code start and stop
characters.
Bit 4 Retired item 21
Bit 5 Bar code symbol suppression
This flag specifies whether or not the bar code symbol will be presented, as follows:
B'0' Present the bar code symbol
B'1' Suppress presentation of the bar code symbol. This can be used to print just
the HRI. If both bit 0 and bit 5 are B'1' or the bar code does not support HRI,
nothing will be presented for this bar code object.
When bit 5 = B'1', the X offset and Y offset parameters specify the character
reference point for the first character of the HRI.
Not all BCOCA receivers support suppression of the bar code symbol; receivers that
do not support this optional function ignore bit 5.
Bit 6 Desired method of adjusting for trailing blanks
This flag identifies the desired method of handling trailing blanks in the bar code data;
for some symbologies, the resulting data length is used to adjust the bar code type
and modifier to match the resulting data length.
Note: This flag is used by presentation systems that process AFP line data and may
be ignored by BCOCA printers and other presentation systems. AFP line data
supports fixed-length fields for bar code data; variable-length fields are not
supported. The PAGEDEF formatting-control object that is used with AFP Line
Data supports fixed-length fields for data that is to be bar encoded. Since some
bar codes allow variable-length data, these fixed-length fields often are padded
on the right with blanks; these blanks are often not intended to be included in
the BCOCA object, particularly for a bar code type that does not allow blanks.
This flag, when specified in a PAGEDEF object, identifies how these trailing
blanks should be handled when a BCOCA bar code object is built from the line
data and PAGEDEF information.
When AFP line data containing bar code data is processed, this flag is used as
follows:
B'0' Do not suppress trailing blanks in the bar code data.
B'1' Suppress all trailing blanks in the bar code data and adjust the bar code type
and modifier to match the resulting data length.
Bar Code Symbol Data (BSA) [BCOCA-4-325]




When the flag = B'1', the bar code data is first adjusted by suppressing trailing blanks
and then the bar code type and modifier is adjusted based on the resulting length as
follows:
If the user specified an EAN bar code type (X'08', X'09', X'16', or X'17'):
Truncate the data and set the bar code type and modifier based on the resulting
data length:
Resulting Data Length Bar Code Type Bar Code Modifier
2 X'16' – Two-Digit Supplemental X'00' [BCOCA-4-326]
5 X'17' – Five-Digit Supplemental X'00' [BCOCA-4-327]
7 X'08' – EAN-8 X'00' [BCOCA-4-328]
12 X'09' – EAN-13 X'00' [BCOCA-4-329]
14 X'16' – Two-Digit Supplemental X'01' [BCOCA-4-330]
17 X'17' – Five-Digit Supplemental X'01' [BCOCA-4-331]
any other value error
If the user specified a UPC bar code type (X'03', X'05', X'06', or X'07'):
Truncate the data and set the bar code type and modifier based on the resulting
data length:
Resulting Data Length Bar Code Type Bar Code Modifier
2 X'06' – Two-Digit Supplemental X'00' [BCOCA-4-332]
5 X'07' – Five-Digit Supplemental X'00' [BCOCA-4-333]
10 X'05' – UPC version E X'00' [BCOCA-4-334]
11 X'03' – UPC version A X'00' [BCOCA-4-335]
12 X'06' – Two-Digit Supplemental X'02' [BCOCA-4-336]
13 X'06' – Two-Digit Supplemental X'01' [BCOCA-4-337]
15 X'07' – Five-Digit Supplemental X'02' [BCOCA-4-338]
16 X'07' – Five-Digit Supplemental X'01' [BCOCA-4-339]
any other value error
If the user specified a POSTNET (deprecated) bar code type (X'18'):
Truncate the data and set the bar code type and modifier based on the resulting
data length:
Resulting Data Length Bar Code Type Bar Code Modifier
5 X'18' – POSTNET X'00' [BCOCA-4-340]
9 X'18' – POSTNET X'01' [BCOCA-4-341]
11 X'18' – POSTNET If X'02' or X'04' was [BCOCA-4-342]
specified, that value is used;
if any other modifier was
specified, X'02' is used.
any other value X'18' – POSTNET X'03'
Bar Code Symbol Data (BSA) [BCOCA-4-343]




If the user specified an Intelligent Mail Barcode type (X'22'):
Truncate the data and set the bar code type and modifier based on the resulting
data length:
Resulting Data Length Bar Code Type Bar Code Modifier
20 X'22' – Intelligent Mail Barcode X'00' [BCOCA-4-344]
25 X'22' – Intelligent Mail Barcode X'01' [BCOCA-4-345]
29 X'22' – Intelligent Mail Barcode X'02' [BCOCA-4-346]
31 X'22' – Intelligent Mail Barcode X'03' [BCOCA-4-347]
any other value error
If the user specified any other bar code type:
Use the user-specified bar code type and modifier.
Bit 7 Retired item 3
Bytes 1–2 X offset
This parameter specifies the origin of the bar code based on the bar code symbol suppression
flag (bit 5):
When a bar code symbol is to be presented (bit 5 = B'0'),
this parameter specifies the X bc-coordinate of the top-left corner of an
imaginary rectangle of minimum size that bounds the bar-space patterns (or
two-dimensional module patterns) of the symbol. It is referenced to the bar
code presentation space origin in the units of measure specified in the BSD
data structure.
When a bar code symbol is to be suppressed (bit 5 = B'1'),
this parameter specifies the X
bc-coordinate of the character reference point
for the first character of the HRI. It is referenced to the bar code presentation
space origin in the units of measure specified in the BSD data structure.
Exception condition EC-0A00 exists if the X offset value is invalid or unsupported.
Notes:
1. In most cases, the symbol origin is the top-left corner of the leftmost bar; however, this is [BCOCA-4-348]
not an appropriate origin for some bar code types, such as Dutch KIX, Intelligent Mail
Barcode, MaxiCode, and Royal Mail Mailmark. The original BCOCA symbol origin
definition was the “top-left corner of the leftmost bar”; therefore, some older
implementations might still use the original definition (this is not considered to be a
deviation from the architecture for these older implementations).
2. For MaxiCode symbols, use the top-left corner of an imaginary rectangle of minimum size [BCOCA-4-349]
that bounds the symbol.
3. For Royal Mail RED TAG (deprecated) symbols, use the top-left corner of the leftmost bar. [BCOCA-4-350]
4. For GS1 DataBar symbols, the origin of the bar code symbol is the top-left corner of the [BCOCA-4-351]
leftmost space (since GS1 DataBar symbols begin with a space).
Bar Code Symbol Data (BSA) [BCOCA-4-352]




Bytes 3–4 Y offset
This parameter specifies the origin of the bar code based on the bar code symbol suppression
flag (bit 5):
When a bar code symbol is to be presented (bit 5 = B'0'),
this parameter specifies the Y bc-coordinate of the top-left corner of an
imaginary rectangle of minimum size that bounds the bar-space patterns (or
two-dimensional module patterns) of the symbol. It is referenced to the bar
code presentation space origin in the units of measure specified in the BSD
data structure.
When a bar code symbol is to be suppressed (bit 5 = B'1'),
this parameter specifies the Y
bc-coordinate of the character reference point
for the first character of the HRI. It is referenced to the bar code presentation
space origin in the units of measure specified in the BSD data structure.
Exception condition EC-0A00 exists if the Y offset value is invalid or unsupported.
Bytes 5–n Special functions specific to the bar code type
The following special-function parameters are only used with the following bar code types,
refer to:
“Aztec Code Special-Function Parameters”
“Data Matrix Special-Function Parameters”
“Han Xin Code Special-Function Parameters
”
“Intelligent Mail Package Barcode Special-Function Parameters”
“MaxiCode Special-Function Parameters”
“PDF417 Special-Function Parameters”
“QR Code Special-Function Parameters”
“QR Code with Image Special-Function Parameters”
These special-function parameters must not be specified for any other bar code types.
Bytes n+1 to
end
Data
Contains the variable data to be encoded and, if required, generated as HRI text characters
above or below the bar code symbol. The length and type of data that can be encoded is
defined by the bar code symbology. For more information, refer to the appropriate bar code
symbology specification listed in Appendix A, “Bar Code Symbology Specification
References”,. Exception condition EC-2100 exists if an invalid or undefined
character, according to the rules of the bar code symbology specification, is encountered in
the bar code data field. Exception condition EC-0C00 exists if the length of the data plus any
bar code object processor generated check digit is invalid or unsupported. Refer to Table 35 for a description of the valid characters and data length for each symbology.
The data is specified as a series of single-byte code points from a specific code page. Some
symbologies limit the valid code points to just the ten numerals (0 through 9), other
symbologies allow a richer set of code points. The bar code symbol is produced from these
code points; the code points are also used, along with a particular type style, when producing
the HRI.
Table 34 lists, for each symbology, the valid code page from which characters are
chosen and the type style used when printing HRI in terms of an IBM registered CPGID and
FGID. More information about these values can be found in the documents listed in Table 5 on
page xiii.
Bar Code Symbol Data (BSA) [BCOCA-4-353]

Aztec Code Special-Function Parameters
Table 21. Aztec Code Special-Function Parameters [BCOCA-4-354]

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-355]|
| | | bit 0 | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>• Do not translate<br>• Convert data to ASCII [BCOCA-4-356]| | |
| | | bit 1 | B'0'<br>B'1' | Escape-sequence handling:<br>• Process escape sequences<br>• Ignore all escape sequences [BCOCA-4-357]| | |
| | | bit 2 | B'0'<br>B'1' | If too much data:<br>• Use a bigger Aztec Code symbol<br>• Exception EC-0F17 exists [BCOCA-4-358]| | |
| | | bits 3–7 | B'00000' | Reserved [BCOCA-4-359]| | |
| 6 | X'00' | Reserved | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-360]|
| 7 | UBIN | Desired number of layers | X'00'–X'20'<br>X'FF' | Number of layers (0 to 32)<br>Smallest symbol | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-361]|
| 8 | CODE | Error correction level | X'05'–X'5F'<br>X'FF' | Level of error correction (5% to 95%)<br>Use default | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-362]|
| 9 | BITS | Special-function flags | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-363]|
| | | bit 0 | B'0'<br>B'1' | Alternate data type identifier:<br>• User-defined symbol<br>• Symbol conforms to GS1 standards [BCOCA-4-364]| | |
| | | bit 1 | B'0'<br>B'1' | Alternate data type identifier:<br>• User-defined symbol<br>• Symbol conforms to industry standards [BCOCA-4-365]| | |
| | | bit 2 | B'0'<br>B'1' | Reader initialization symbol:<br>• Symbol encodes a data symbol<br>• Symbol encodes a reader initialization symbol [BCOCA-4-366]| | |
| | | bits 3–7 | B'00000' | Reserved [BCOCA-4-367]| | |
| 10 | CODE | Application indicator | See field description | Application indicator for Industry FNC1 | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-368]|
| 11 | UBIN | Sequence indicator | X'00'–X'1A' | Structured append sequence indicator | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-369]|
| 12 | UBIN | Total symbols | X'00' or X'02'–X'1A' | Total number of structured-append symbols | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-370]|
| 13 | UBIN | Append ID length | X'00'–X'FF' | Structured append ID length | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-371]|
| 14–m | CHAR | Append ID | | Structured append ID | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-372]|
| m+1 | UBIN | Addl parms length | X'00'–X'FF' | Length of additional parameter bytes that follow | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-373]|
| m+2 to end | | Addl parms | | Reserved; data without current architectural definition | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-374]|
Byte 5 Control flags
These flags control how the bar code data (bytes n+1 to end) is processed by the BCOCA
receiver; the receiver can be an IPDS printer or any other product that processes BCOCA
objects.
Bit 0 EBCDIC-to-ASCII translation
If this flag is B'0', the data is assumed to begin in the default character encodation
(ECI 000003, also known as ISO/IEC 8859–1) and no translation is done.
If this flag is B'1', the BCOCA receiver will convert each byte of the bar code data, as
well as each byte of the structured append ID if there is one, from EBCDIC code page
500 into ASCII code page 819 (equivalent to ECI 000003) before this data is used to [BCOCA-4-375]
build the bar code symbol.
Bit 1 Escape-sequence handling
If this flag is B'0', each X'5C' (backslash) within the bar code data is treated as an
escape character according to the Aztec Code symbology specification.
If this flag is B'1', each X'5C' (backslash) within the bar code data is treated as a
normal data character and therefore all escape sequences are ignored. In this case,
no ECI code page switching can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC backslash
characters (X'E0') will first be converted into X'5C' before the escape-sequence
handling flag is applied.
Bit 2 T oo much data
This flag specifies the behavior when both of the following two conditions exist:
• The desired-number-of-layers parameter (byte 7) is in the range X'01'–X'20'; that is, [BCOCA-4-376]
it requests a specific number of layers.
• The bar code data to be encoded, combined with the error correction level [BCOCA-4-377]
requested (byte 8), will not fit in an Aztec Code symbol using the requested number
of layers.
This flag is ignored otherwise.
If this flag is B'0', the Aztec Code will be made bigger to fit the data. Note, however,
that the bigger Aztec Code must be in the format requested by the modifier value
(BSD byte 13); if the data cannot be fit into a symbol of that format, exception EC-
0C00 exists.
If this flag is B'1', exception EC-0F17 exists. This is useful when the Aztec Code being
produced is required to be a specific size.
Bits
3–7
Reserved
Aztec Code Special-Function Parameters [BCOCA-4-378]




Byte 6 Reserved
Byte 7 Desired number of layers
This parameter specifies the desired size of the symbol in terms of the number of data layers
surrounding the Aztec Code core symbol.
Note: A desired number of layers is specified by this parameter, but the actual size of the
symbol depends on the data to be encoded and the error correction level. If not enough
data is supplied, the symbol will be padded with extra error correction codewords to
reach the requested symbol size. If too much data is supplied for the requested symbol
size, the behavior depends on the value of the too-much-data flag (bit 2) in the control
flags (byte 5):
• If B'0', the symbol will be bigger than requested and will be the smallest symbol, in the [BCOCA-4-379]
format corresponding to the modifier value (BSD byte 13), that can accommodate the
bar code data.
• If B'1', exception EC-0F17 exists. [BCOCA-4-380]
The potential values for this parameter are:
X'00'–X'20' Specifies the desired number of layers, from 0 to 32. Not all values are valid in
all cases; see Table 22.
X'FF' Specifies that an appropriate number of layers and Aztec Code format should
be used based on the amount of symbol data and the requested error
correction level; the smallest symbol that can accommodate the amount of
data and that is in the format corresponding to the modifier value (BSD byte
13) is produced:
Modifier X'00' The smallest possible full-range symbol is produced.
Modifier X'01' The smallest possible compact symbol is produced.
Modifier X'03' The smallest possible Aztec Code symbol, whether that is a
full-range or a compact symbol, is produced.
Note: In determining the smallest valid symbol, the reader-
init flag (bit 2 of byte 9) must be taken into account.
Thus, if the reader-init flag is B'1', a full-range symbol
might need to be produced even though a compact
symbol could have been produced if the flag had been
B'0'.
The valid number of layers varies depending on the format of Aztec Code requested—that is,
on the modifier value (BSD byte 13) for this Aztec Code—as well as on the reader-init flag (bit
2 of byte 9): [BCOCA-4-381]
Table 22. Supported Number of Layers for an Aztec Code Symbol [BCOCA-4-382]

| Aztec Code Format | Modifier Value | Reader-Init Flag | Valid Layer Range |
| :--- | :--- | :--- | :--- |
| Full-range | X'00' | B'0' | X'01'–X'20' (1–32), X'FF' [BCOCA-4-383]|
| | | B'1' | X'01'–X'16' (1–22), X'FF' [BCOCA-4-384]|
| Compact | X'01' | B'0' | X'01'–X'04' (1–4), X'FF' [BCOCA-4-385]|
| | | B'1' | X'01' (1), X'FF' [BCOCA-4-386]|
| Rune | X'02' | ignored | X'00' [BCOCA-4-387]|
| Smallest compact or full-range | X'03' | B'0' or B'1' | X'FF' [BCOCA-4-388]|
Notes:
1. Full-range Aztec Code symbols with 1–3 layers always take more space than the [BCOCA-4-389]
equivalent compact Aztec Code symbol encoding the same data. However, when
producing reader initialization symbols, 1–3 layer full-range symbols are sometimes
required.
2. Aztec Code rune symbols (modifier value X'02') will only be produced when explicitly [BCOCA-4-390]
requested and will never be produced when the smallest symbol is requested.
Exception condition EC-0F18 exists if an invalid desired-number-of-layers value is specified.
Byte 8 Level of error correction
This parameter specifies the minimum level of error correction to be used for the symbol, as a
percentage of the total number of codewords in the symbol.
The potential values for this parameter are:
X'05'–X'5F' Specifies the minimum percentage of the total number of codewords in the
symbol that are to be used as error-correction codewords. Percentage values
from 5% to 95% can be requested. Note that the symbology specification
states that an additional three codewords, on top of the number of codewords
calculated based on the percentage value requested, will also be used for
error correction.
X'FF' Specifies that the recommended error correction level will be used. The
symbology specification recommends that 23% of the codewords, plus three
additional codewords, be used.
As an example, if the recommended error correction percentage is specified for a 5-layer
symbol holding 120 codewords, at least 28 + 3 = 31 codewords would be used for error
correction, leaving 89 codewords for data. (The value 28 is the ceiling of (120 * 0.23) = 27.6.)
Note that the requested percentage is a minimum, since when Aztec Code symbols are
generated, any extra codewords are used as additional error correction codewords. In the
example from just above, if there were only 86 codewords of data, instead of 31 error-
correction codewords, there would be 34.
Exception condition EC-0F19 exists if an invalid error-correction-level value is specified.
When an Aztec Code rune symbol is being produced, this parameter is ignored and should be
set to X'FF'.
Aztec Code Special-Function Parameters [BCOCA-4-391]




Byte 9 Special-function flags
These flags specify special functions that can be used with a Aztec Code symbol.
Bit 0 GS1 FNC1 alternate data type identifier
If this flag is B'1', this Aztec Code symbol will indicate that it conforms to the GS1
application identifiers standard. In this case, the industry-FNC1 flag must be B'0'.
Exception condition EC-0F1A exists if an incompatible combination of these bits is
specified.
When an Aztec Code rune symbol is being produced, this flag is ignored and should
be set to B'0'.
Bit 1 Industry FNC1 alternate data type identifier
If this flag is B'1', this Aztec Code symbol will indicate that it conforms to a particular
industry standard format. In this case, the GS1-FNC1 flag must be B'0'. Exception
condition EC-0F1A exists if an incompatible combination of these bits is specified.
When this flag is B'1', an application indicator is specified in byte 10.
When an Aztec Code rune symbol is being produced, this flag is ignored and should
be set to B'0'.
Bit 2 Reader initialization symbol indicator
If this flag is B'1', this Aztec Code symbol will indicate that it encodes initialization or
configuration information for the bar code reader.
Due to the way this information is encoded in an Aztec Code symbol, only compact
symbols with 1 layer of codewords and full-range symbols with between 1 and 22
layers of codewords can be used. Exception condition EC-0F1E exists if the bar code
data to be encoded, combined with the error correction level requested (byte 8),
requires more layers than these limits.
When an Aztec Code rune symbol is being produced, this flag is ignored and should
be set to B'0'.
Bits
3–7
Reserved
Byte 10 Application indicator for Industry FNC1
When the Industry FNC1 flag is B'1', this parameter specifies an application indicator. The
application indicator is a one-byte value that is specified either as an alphabetic value (from
the ASCII set a-z, A-Z) plus 100 or as a two-digit decimal number (between 00 and 99)
represented as a hexadecimal value. For example:
for application indicator “a” (ASCII value X'61'), specify X'C5'
for application indicator “Z” (ASCII value X'5A'), specify X'BE'
for application indicator “00”, specify X'00'
for application indicator “01”, specify X'01'
for application indicator “99”, specify X'63'
When the Industry FNC1 flag is B'0', this parameter is ignored and should be set to X'00'.
Exception condition EC-0F1B exists if an invalid application-indicator value is specified.
Byte 11 Structured append sequence indicator
Multiple Aztec Code bar code symbols (called structured appends) can be logically linked
together to encode large amounts of data. The logically linked symbols can be presented on
the same or on different physical media, and are logically recombined after they are scanned.
From 2 to 26 Aztec Code symbols can be linked. This parameter specifies where this symbol
is logically linked (1-26) in a sequence of symbols.
Aztec Code Special-Function Parameters [BCOCA-4-392]




If X'00' is specified for this parameter, this symbol is not part of a structured append. Exception
condition EC-0F01 exists if an invalid sequence indicator value is specified. Exception
condition EC-0F02 exists if the sequence indicator is larger than the total number of symbols
(byte 12).
When an Aztec Code rune symbol is being produced, this parameter is ignored and should be
set to X'00'.
Byte 12 T otal number of structured-append symbols
This parameter specifies the total number of symbols (2-26) that are logically linked in a
sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. If this
symbol is not part of a structured append, bytes 11 and 12 must be X'00', or exception
condition EC-0F03 exists.
Exception condition EC-0F04 exists if an invalid number of symbols is specified.
When an Aztec Code rune symbol is being produced, this parameter is ignored and should be
set to X'00'.
Byte 13 Structured append ID length
This parameter specifies the length of the following structured append ID, not including this
length byte. The structured append ID is optional, so this length can be X'00' even if this
symbol is part of a structured append.
If this symbol is not part of a structured append, this parameter must be X'00', or exception
condition EC-0F1C exists.
Bytes 14–m Structured append ID
This parameter is a series of characters that make up the structured append ID. All the
symbols making up the overall appended symbol must use the same structured append ID. A
structured append ID is not required; thus, even if this symbol is part of a structured append,
there might be no structured append ID.
When the EBCDIC-to-ASCII translation flag is B'1', the BCOCA receiver must first convert
each byte of this structured append ID from EBCDIC code page 500 into ASCII code page 819
(equivalent to ECI 000003). When the EBCDIC-to-ASCII translation flag is B'0', the structured
append ID is assumed to already use the Aztec Code default ECI 000003 (ISO/IEC 8859-1)
character encodation.
The structured append ID cannot include the space character (X'20'). The X'5C' character
(backslash) is treated simply as the backslash character, so no ECI code page switching can
occur within the structured append ID. The symbology specification recommends using only
uppercase letters in order to use the least space in the encoded message.
Exception condition EC-0F1D exists if an invalid character is found.
Byte m+1 Additional parameters length
This parameter specifies the length of the following additional parameters, not including this
length byte.
Bytes m+2
to end
Additional parameters
This area is reserved for potential future use. The content of this area is not checked by
BCOCA receivers. BCOCA generators should not include anything in this area; that is, the
addl-parms-length field in byte m+1 should be X'00'.
Aztec Code Special-Function Parameters [BCOCA-4-393]




Data Matrix Special-Function Parameters [BCOCA-4-394]

Table 23. Data Matrix Special-Function Parameters [BCOCA-4-395]

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags [BCOCA-4-396]| | | | |
| | bit 0 | EBCDIC | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>Do not translate<br>Convert data to ASCII | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-397]|
| | bit 1 | Escape sequence handling | B'0'<br>B'1' | Escape-sequence handling:<br>Process escape sequences<br>Ignore all escape sequences | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-398]|
| | bit 2 | Too much data | B'0'<br>B'1' | If too much data:<br>Use a bigger Data Matrix symbol<br>Exception EC-0F20 exists | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-399]|
| | bits 3–7 | | B'00000' | Reserved | B'00000' | B'00000' [BCOCA-4-400]|
| 6–7 | UBIN | Desired row size | X'0000'<br>X'0001'–X'FFFF' | No size specified<br>Matrix row size as allowed by symbology; see field description | Not supported in BCD1 | X'0000'<br>All row sizes within Table 24 [BCOCA-4-401]|
| 8–9 | UBIN | Desired number of rows | X'0000'<br>X'0001'–X'FFFF' | No size specified<br>Number of rows as allowed by symbology; see field description | Not supported in BCD1 | X'0000'<br>All number-of-rows values within Table 24 [BCOCA-4-402]|
| 10 | UBIN | Sequence indicator | X'00'–X'10' | Structured append sequence indicator | Not supported in BCD1 | X'00'–X'10' [BCOCA-4-403]|
| 11 | UBIN | Total symbols | X'00' or X'02'–X'10' | Total number of structured-append symbols | Not supported in BCD1 | X'00' or X'02'–X'10' [BCOCA-4-404]|
| 12 | UBIN | File ID 1st byte | X'01'–X'FE' | High-order byte of a 2-byte unique file identification for a set of structured-append symbols | Not supported in BCD1 | X'01'–X'FE' [BCOCA-4-405]|
| 13 | UBIN | File ID 2nd byte | X'01'–X'FE' | Low-order byte of a 2-byte unique file identification for a set of structured-append symbols | Not supported in BCD1 | X'01'–X'FE' [BCOCA-4-406]|
| 14 | BITS | Special-function flags [BCOCA-4-407]| | | | |
| | bit 0 | GS1 FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to GS1 standards | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-408]|
| | bit 1 | Industry FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to industry standards | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-409]|
| | bit 2 | Reader programming | B'0'<br>B'1' | Reader programming symbol:<br>Symbol encodes a data symbol<br>Symbol encodes a message used to program the reader system | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-410]|
| | bits 3–4 | Hdr/Trl Macro | B'00'<br>B'01'<br>B'10'<br>B'11' | Header and trailer instructions to the bar code reader:<br>No header or trailer<br>Use the 05 Macro header/trailer<br>Use the 06 Macro header/trailer<br>No header or trailer | Not supported in BCD1 | B'00'<br>B'01'<br>B'10'<br>B'11' [BCOCA-4-411]|
| | bits 5–7 | Encodation scheme | B'000'<br>B'001'<br>B'010'<br>B'011'<br>B'100'<br>B'101'<br>B'110'<br>B'111' | Encodation scheme used to produce the bar code symbol:<br>Device default – usually Auto encoding<br>ASCII<br>C40<br>Text<br>X12<br>EDIFACT<br>Base 256<br>Auto encoding | B'000'<br>Other values not supported in BCD1 | B'000'<br>Other values not supported in BCD2 [BCOCA-4-412]|
Byte 5 Control flags
These flags control how the bar code data (bytes n+1 to end) is processed by the BCOCA
receiver; the receiver can be an IPDS printer or any other product that processes BCOCA
objects.
Bit 0 EBCDIC-to-ASCII translation
If this flag is B'0', the data is assumed to begin in the default character encodation and
no translation is done.
If this flag is B'1', the BCOCA receiver will convert each byte of the bar code data from
EBCDIC code page 500 into ASCII code page 819 before this data is used to build the
bar code symbol.
Bit 1 Escape-sequence handling
If this flag is B'0', each X'5C' (backslash) within the bar code data is treated as an
escape character according to the Data Matrix symbology specification.
If this flag is B'1', each X'5C' within the bar code data is treated as a normal data
character and therefore all escape sequences are ignored. In this case, no ECI code
page switching can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC backslash
characters (X'E0') will first be converted into X'5C' before the escape-sequence
handling flag is applied.
Data Matrix Special-Function Parameters [BCOCA-4-413]




Bit 2 T oo much data
This flag specifies the behavior when both of the following two conditions exist:
• At least one of the desired row size (bytes 6–7) or desired number of rows (bytes 8– [BCOCA-4-414]
9) parameters are specified with a value that is non-zero; that is, the parameters
request a symbol of a specific height and/or width.
• The number of bytes of data to be encoded will not fit in a Data Matrix symbol of the [BCOCA-4-415]
requested size.
This flag is ignored otherwise.
If this flag is B'0', the symbol will be made bigger to fit the data, but the aspect ratio will
be maintained as closely as possible. This was the behavior prior to the creation of
this flag.
If this flag is B'1', exception EC-0F20 exists. This value is useful when the Data Matrix
symbol to be produced is required to be a specific size. BCOCA receiver support for
this functionality depends on the modifier value (BSD byte 13) of the Data Matrix bar
code:
• For modifier X'01', the B'1' value of this flag is a mandatory function. [BCOCA-4-416]
• For modifier X'00', the B'1' value of this flag is an optional function that is not [BCOCA-4-417]
supported by all BCOCA receivers. IPDS printers indicate support for this function
for modifier X'00' with Sense Type and Model property pair X'1307'. Any BCOCA
receiver that supports both modifier values X'00' and X'01' is required to support the
B'1' value of this flag for both modifier values.
Bits
3–7
Reserved
Bytes 6–7 Desired row size
Note: A desired symbol size is specified in bytes 6–9, but the actual size of the symbol
depends on the amount of data to be encoded. If not enough data is supplied, the
symbol will be padded with null data to reach the requested symbol size. If too much
data is supplied for the requested symbol size, the behavior depends on the value of
the too much data flag (bit 2) in the control flags (byte 5):
• If B'0', the symbol will be bigger than requested, but the aspect ratio will be [BCOCA-4-418]
maintained as closely as possible.
• If B'1', exception EC-0F20 exists. [BCOCA-4-419]
For a Data Matrix symbol, this parameter specifies the desired number of modules in each row
including the finder pattern. There must be an even number of modules per row and an even
number of rows.
For modifier X'00' (BSD byte 13), there are 24 square symbols with sizes from 10x10 to
144x144, and 6 rectangular symbols with sizes from 8x18 to 16x48, not including quiet zones.
Table 24 lists the complete set of supported sizes.
For modifier X'01' (BSD byte 13), in addition to the symbols for modifier X'00' just above, there
are an additional 18 rectangular symbols with sizes from 8x48 to 26x64, again not including
quiet zones. Table 25 lists the complete set of supported sizes.
Exception condition EC-0F00 exists if an unsupported size value is specified.
If X'0000' is specified for this parameter, an appropriate row size will be used based on the
amount of symbol data.
Note: The X'0000' value should not be specified if the BCOCA generator has any preference
regarding the size and/or shape of the symbol. In all cases, the size selected by the
BCOCA receiver when X'0000' is received for either or both of bytes 6–7 and bytes 8–9
is device specific. In particular, the selected size could be either a square or rectangular
shape, although receivers are recommended to lean toward square sizes when both
Data Matrix Special-Function Parameters [BCOCA-4-420]




values are specified as X'0000'; note that this recommendation was added much later
than DataMatrix support was added to BCOCA, so many BCOCA receivers might not
follow this recommendation.
Table 24. Supported Sizes for a Modifier X'00' Data Matrix Symbol [BCOCA-4-421]

| Square Symbols | | | | Rectangular Symbols | | | |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Symbol Size** | | **Data Region** | | **Symbol Size** | | **Data Region** [BCOCA-4-422]| |
| **Number of Rows** | **Row Size** | **Size** | **Number** | **Number of Rows** | **Row Size** | **Size** | **Number** [BCOCA-4-423]|
| 10 | 10 | 8x8 | 1 | 8 | 18 | 6x16 | 1 [BCOCA-4-424]|
| 12 | 12 | 10x10 | 1 | 8 | 32 | 6x14 | 2 [BCOCA-4-425]|
| 14 | 14 | 12x12 | 1 | 12 | 26 | 10x24 | 1 [BCOCA-4-426]|
| 16 | 16 | 14x14 | 1 | 12 | 36 | 10x16 | 2 [BCOCA-4-427]|
| 18 | 18 | 16x16 | 1 | 16 | 36 | 14x16 | 2 [BCOCA-4-428]|
| 20 | 20 | 18x18 | 1 | 16 | 48 | 14x22 | 2 [BCOCA-4-429]|
| 22 | 22 | 20x20 | 1 [BCOCA-4-430]| | | | |
| 24 | 24 | 22x22 | 1 [BCOCA-4-431]| | | | |
| 26 | 26 | 24x24 | 1 [BCOCA-4-432]| | | | |
| 32 | 32 | 14x14 | 4 [BCOCA-4-433]| | | | |
| 36 | 36 | 16x16 | 4 [BCOCA-4-434]| | | | |
| 40 | 40 | 18x18 | 4 [BCOCA-4-435]| | | | |
| 44 | 44 | 20x20 | 4 [BCOCA-4-436]| | | | |
| 48 | 48 | 22x22 | 4 [BCOCA-4-437]| | | | |
| 52 | 52 | 24x24 | 4 [BCOCA-4-438]| | | | |
| 64 | 64 | 14x14 | 16 [BCOCA-4-439]| | | | |
| 72 | 72 | 16x16 | 16 [BCOCA-4-440]| | | | |
| 80 | 80 | 18x18 | 16 [BCOCA-4-441]| | | | |
| 88 | 88 | 20x20 | 16 [BCOCA-4-442]| | | | |
| 96 | 96 | 22x22 | 16 [BCOCA-4-443]| | | | |
| 104 | 104 | 24x24 | 16 [BCOCA-4-444]| | | | |
| 120 | 120 | 18x18 | 36 [BCOCA-4-445]| | | | |
| 132 | 132 | 20x20 | 36 [BCOCA-4-446]| | | | |
| 144 | 144 | 22x22 | 36 [BCOCA-4-447]| | | | |

Table 25. Supported Sizes for a Modifier X'01' Data Matrix Symbol [BCOCA-4-448]

| Square Symbols | | | | Rectangular Symbols | | | |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Symbol Size** | | **Data Region** | | **Symbol Size** | | **Data Region** [BCOCA-4-449]| |
| **Number of Rows** | **Row Size** | **Size** | **Number** | **Number of Rows** | **Row Size** | **Size** | **Number** [BCOCA-4-450]|
| All supported sizes for a modifier X'00' Data Matrix symbol (found in Table 24) are also supported for a modifier X'01' Data Matrix symbol (in this table); in addition, the sizes below are supported: [BCOCA-4-451]| | | | | | | |
| | | | | 8 | 48 | 6x22 | 2 [BCOCA-4-452]|
| | | | | 8 | 64 | 6x14 | 4 [BCOCA-4-453]|
| | | | | 8 | 80 | 6x18 | 4 [BCOCA-4-454]|
| | | | | 8 | 96 | 6x22 | 4 [BCOCA-4-455]|
| | | | | 8 | 120 | 6x18 | 6 [BCOCA-4-456]|
| | | | | 8 | 144 | 6x22 | 6 [BCOCA-4-457]|
| | | | | 12 | 64 | 10x14 | 4 [BCOCA-4-458]|
| | | | | 12 | 88 | 10x20 | 4 [BCOCA-4-459]|
| | | | | 16 | 64 | 14x14 | 4 [BCOCA-4-460]|
| | | | | 20 | 36 | 18x16 | 2 [BCOCA-4-461]|
| | | | | 20 | 44 | 18x20 | 2 [BCOCA-4-462]|
| | | | | 20 | 64 | 18x14 | 4 [BCOCA-4-463]|
| | | | | 22 | 48 | 20x22 | 2 [BCOCA-4-464]|
| | | | | 24 | 48 | 22x22 | 2 [BCOCA-4-465]|
| | | | | 24 | 64 | 22x14 | 4 [BCOCA-4-466]|
| | | | | 26 | 40 | 24x18 | 2 [BCOCA-4-467]|
| | | | | 26 | 48 | 24x22 | 2 [BCOCA-4-468]|
| | | | | 26 | 64 | 24x14 | 4 [BCOCA-4-469]|
Bytes 8–9 Desired number of rows
For a Data Matrix symbol, this parameter specifies the desired number of rows including the
finder pattern. Exception condition EC-0F00 exists if an unsupported size value is specified.
If X'0000' is specified for this parameter, an appropriate number of rows will be used based on
the amount of symbol data.
Note: The X'0000' value should not be specified if the BCOCA generator has any preference
regarding the size and/or shape of the symbol. In all cases, the size selected by the
BCOCA receiver when X'0000' is received for either or both of bytes 6–7 and bytes 8–9
is device specific. In particular, the selected size could be either a square or rectangular
shape, although receivers are recommended to lean toward square sizes when both
values are specified as X'0000'; note that this recommendation was added much later
than DataMatrix support was added to BCOCA, so many BCOCA receivers might not
follow this recommendation.
Data Matrix Special-Function Parameters [BCOCA-4-470]




Byte 10 Structured append sequence indicator
Multiple data matrix bar code symbols (called structured appends) can be logically linked
together to encode large amounts of data. The logically linked symbols can be presented on
the same or on different physical media, and are logically recombined after they are scanned.
From 2 to 16 Data Matrix symbols can be linked. This parameter specifies where this symbol
is logically linked (1–16) in a sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. Exception
condition EC-0F01 exists if an invalid sequence indicator value is specified. Exception
condition EC-0F02 exists if the sequence indicator is larger than the total number of symbols
(byte 11).
If this field is not X'00', the reader programming flag must be B'0' and the hdr/trl macro flags
must be either B'00' or B'11'. Exception condition EC-0F0A exists if an incompatible
combination of these parameters is specified.
Byte 11 T otal symbols in a structured append
This parameter specifies the total number of symbols (2–16) that is logically linked in a
sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. If this
symbol is not part of a structured append, both bytes 10 and 11 must be X'00', or exception
condition EC-0F03 exists.
Exception condition EC-0F04 exists if an invalid number of symbols is specified.
Byte 12 High-order byte of structured append file identification
This parameter specifies the high-order byte of a 2-byte unique file identification for a set of
structured-append symbols, that helps ensure that the symbols from two different structured
appends are not linked together. The low-order byte of the 2-byte field is specified in byte 13.
Each of the two bytes can contain a value in the range X'01'–X'FE'.
This parameter is ignored if this symbol is not part of a structured append.
If this symbol is part of a structured append, but byte 12 contains an invalid value (X'00' or
X'FF'), exception condition EC-0F0B exists.
Byte 13 Low-order byte of structured append file identification
This parameter specifies the low-order byte of a 2-byte unique file identification for a set of
structured-append symbols. The high-order byte of the 2-byte field is specified in byte 12.
Each of the two bytes can contain a value in the range X'01'–X'FE'.
This parameter is ignored if this symbol is not part of a structured append.
If this symbol is part of a structured append, but byte 13 contains an invalid value (X'00' or
X'FF'), exception condition EC-0F0B exists.
Byte 14 Special-function flags
These flags specify special functions that can be used with a Data Matrix symbol.
Bit 0 GS1 FNC1 alternate data type identifier
If this flag is B'1', an FNC1 shall be added in the first data position (or fifth position of a
structured append symbol) to indicate that this symbol conforms to the GS1
application identifier standard format. In this case, the industry FNC1 flag must be
B'0', the reader programming flag must be B'0', and the hdr/trl macro must be B'00' or
B'11'. Exception condition EC-0F0A exists if an incompatible combination of these
parameters is specified.
Bit 1 Industry FNC1 alternate data type identifier
Data Matrix Special-Function Parameters [BCOCA-4-471]




If this flag is B'1', an FNC1 shall be added in the second data position (or sixth position
of a structured append symbol) to indicate that this symbol conforms to a particular
industry standard format. In this case, the GS1 FNC1 flag must be B'0', the reader
programming flag must be B'0', and the hdr/trl macro must be B'00' or B'11'. Exception
condition EC-0F0A exists if an incompatible combination of these parameters is
specified.
Bit 2 Reader programming
If this flag is B'1', this symbol encodes a message used to program the reader system.
In this case, the structured append sequence indicator must be X'00', the GS1 FNC1
and industry FNC1 flags must both be B'0', and the hdr/trl macro flags must be either
B'00' or B'11'. Exception condition EC-0F0A exists if an incompatible combination of
these parameters is specified.
Bits
3–4
Header and trailer instructions to the bar code reader
This field provides a means of instructing the bar code reader to insert an industry
specific header and trailer around the symbol data.
If this field is B'00' or B'11', no header or trailer is inserted. If this field is B'01', the bar
code symbol will contain a 05 Macro codeword. If this field is B'10', the bar code
symbol will contain a 06 Macro codeword.
If these flags are B'01' or B'10', the structured append sequence indicator must be
X'00', the GS1 FNC1 and industry FNC1 flags must both be B'0', and the reader
programming flag must be B'0'. Exception condition EC-0F0A exists if an incompatible
combination of these parameters is specified.
Bits
5–7
Encodation scheme used to produce bar code symbol
This field provides a means of selecting the encodation scheme used to produce the
symbol. This is an optional special function that is not supported by all BCOCA
receivers. Receivers that do not support this function, ignore these flags and use a
device default method of choosing the encodation scheme. IPDS printers indicate
support for this function with Sense Type and Model property pair X'1303'.
The selected encodation scheme is used for all of the data within the bar code object
to produce a series of symbol data characters that are used to produce the bar code
symbol. Usually the scheme is selected to produce the smallest number of symbol
data characters, but the best scheme might not be the one that produces the fewest
bits per data character. Also, producing the fewest bits per data character might
require switching between encodation schemes that can cause the symbol size to
grow. The encodation schemes are described as follows:
Device default (B'000')
The BCOCA receiver uses a device-specific method of selecting and
switching among encodation schemes. This is the scheme used by
BCOCA receivers that ignore bits 5–7. Usually the device default is
the same as auto encoding. If you are unsure of the encodation
scheme to use, device default is a good choice.
ASCII (B'001')
This encodation scheme produces 4 bits per data character for
double digit numerics, 8 bits per data character for ASCII values 0–
127, and 16 bits per data character for Extended ASCII values 128–
255.
C40 (B'010')
This encodation scheme is used when the input data is primarily
upper-case alphanumeric and produces 5.33 bits per data character.
Data Matrix Special-Function Parameters [BCOCA-4-472]




Text (B'011')
This encodation scheme is used when the input data is primarily
lower-case alphanumeric and produces 5.33 bits per data character.
X12 (B'100')
This encodation scheme is used when the input data is defined with
the ANSI X12 EDI data set and produces 5.33 bits per data character.
EDIFACT (B'101')
This encodation scheme is used when the input data is ASCII values
32–94 and produces 6 bits per data character.
Base 256 (B'110')
This encodation scheme is used when the data is binary (for example
image or non-text data) and produces 8 bits per data character.
Auto encoding (B'111')
The BCOCA receiver starts with ASCII encodation and switches
between encodation schemes as needed to produce the minimum
symbol data characters. This algorithm is described in an Annex of
International Symbology Specification – Data Matrix.
The C40, Text, X12, and EDIFACT encodation schemes do not support all 256
possible input characters. Exception condition EC-1201 exists if one of these
encodation schemes is selected and an unsupported character is encountered in the
bar code data.
Data Matrix Special-Function Parameters [BCOCA-4-473]




Han Xin Code Special-Function Parameters
Table 26. Han Xin Code Special-Function Parameters [BCOCA-4-474]

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags [BCOCA-4-475]| | | | |
| | bit 0 | EBCDIC | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>Do not translate<br>Convert data to ASCII | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-476]|
| | bit 1 | Escape sequence handling | B'0'<br>B'1' | Escape-sequence handling:<br>Process escape sequences<br>Ignore all escape sequences | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-477]|
| | bit 2 | Too much data | B'0'<br>B'1' | If too much data:<br>Use a bigger Han Xin Code symbol<br>Exception EC-0F21 exists | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-478]|
| | bits 3–7 | | B'00000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-479]|
| 6 | | | X'00' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-480]|
| 7 | UBIN | Version | X'00'<br>X'01' – X'54' | Version of symbol:<br>Smallest symbol<br>Version number (1 to 84) | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-481]|
| 8 | CODE | Error correction level | X'01'<br>X'02'<br>X'03'<br>X'04' | Level of error correction:<br>Level 1 (8% recovery)<br>Level 2 (15% recovery)<br>Level 3 (23% recovery)<br>Level 4 (30% recovery) | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-482]|
| 9 | BITS | Special-function flags [BCOCA-4-483]| | | | |
| | bit 0 | GS1 FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to GS1 standards | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-484]|
| | bit 1 | Industry FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to industry standards | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-485]|
| | bits 2–7 | | B'000000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-486]|
| 10 | CODE | Application indicator | See field description | Application indicator for Industry FNC1 | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-487]|
| 11 | UBIN | Addl parms length | X'00' – X'FF' | Length of additional parameter bytes that follow | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-488]|
| 12 to end | | Addl parms | | Reserved; data without current architectural definition | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-489]|
Han Xin Code Special-Function Parameters [BCOCA-4-490]




Byte 5 Control flags
These flags control how the bar code data (bytes n+1 to end) is processed by the BCOCA
receiver; the receiver can be an IPDS printer or any other product that processes BCOCA
objects.
Bit 0 EBCDIC-to-ASCII translation
If this flag is B'0', the data is assumed to begin in the default character encodation
(ECI 000003, also known as ISO/IEC 8859–1) and no translation is done.
If this flag is B'1', the BCOCA receiver will convert each byte of the bar code data from
EBCDIC code page 500 into ASCII code page 819 (equivalent to ECI 000003) before
this data is used to build the bar code symbol.
Bit 1 Escape-sequence handling
If this flag is B'0', each X'5C' (backslash) within the bar code data is treated as an
escape character according to the Han Xin Code symbology specification.
If this flag is B'1', each X'5C' (backslash) within the bar code data is treated as a
normal data character and therefore all escape sequences are ignored. In this case,
no ECI code page switching can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC backslash
characters (X'E0') will first be converted into X'5C' before the escape-sequence
handling flag is applied.
Bit 2 T oo much data
This flag specifies the behavior when both of the following two conditions exist:
• The version parameter (byte 7) is in the range X'01'–X'54'; that is, it requests a [BCOCA-4-491]
specific version of the symbol.
• The bar code data to be encoded, combined with the error correction level [BCOCA-4-492]
requested (byte 8), will not fit in the Han Xin Code version specified by the version
parameter.
This flag is ignored otherwise.
If this flag is B'0', the version of the symbol will be made bigger to fit the data.
If this flag is B'1', exception EC-0F21 exists. This is useful when the Han Xin Code
being produced is required to be a specific size.
Bits
3–7
Reserved
Byte 6 Reserved
Byte 7 Version of symbol
Note: A desired symbol size is specified by the version parameter (byte 7), but the actual size
of the symbol depends on the amount of data to be encoded. If not enough data is
supplied, the symbol will be padded with null data to reach the requested symbol size. If
too much data is supplied for the requested symbol size, the behavior depends on the
value of the too much data flag (bit 2) in the control flags (byte 5):
• If B'0', the symbol will be bigger than requested and will be the smallest symbol that [BCOCA-4-493]
can accommodate that amount of data.
• If B'1', exception EC-0F21 exists. [BCOCA-4-494]
This parameter specifies the desired size of the symbol; each version specifies a particular
number of modules per row and column. The size of each square module is specified by the
module width parameter (byte 17 in the BSD). The following table lists the complete set of
supported versions. Exception condition EC-0F22 exists if an invalid version value is
specified.
Han Xin Code Special-Function Parameters [BCOCA-4-495]




Table 27. Supported Versions for a Han Xin Code Symbol [BCOCA-4-496]

| Version | Symbol Size | Version | Symbol Size | Version | Symbol Size | Version | Symbol Size |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0 (X'00') | smallest | 22 (X'16') | 65x65 | 44 (X'2C') | 109x109 | 66 (X'42') | 153x153 [BCOCA-4-497]|
| 1 (X'01') | 23x23 | 23 (X'17') | 67x67 | 45 (X'2D') | 111x111 | 67 (X'43') | 155x155 [BCOCA-4-498]|
| 2 (X'02') | 25x25 | 24 (X'18') | 69x69 | 46 (X'2E') | 113x113 | 68 (X'44') | 157x157 [BCOCA-4-499]|
| 3 (X'03') | 27x27 | 25 (X'19') | 71x71 | 47 (X'2F') | 115x115 | 69 (X'45') | 159x159 [BCOCA-4-500]|
| 4 (X'04') | 29x29 | 26 (X'1A') | 73x73 | 48 (X'30') | 117x117 | 70 (X'46') | 161x161 [BCOCA-4-501]|
| 5 (X'05') | 31x31 | 27 (X'1B') | 75x75 | 49 (X'31') | 119x119 | 71 (X'47') | 163x163 [BCOCA-4-502]|
| 6 (X'06') | 33x33 | 28 (X'1C') | 77x77 | 50 (X'32') | 121x121 | 72 (X'48') | 165x165 [BCOCA-4-503]|
| 7 (X'07') | 35x35 | 29 (X'1D') | 79x79 | 51 (X'33') | 123x123 | 73 (X'49') | 167x167 [BCOCA-4-504]|
| 8 (X'08') | 37x37 | 30 (X'1E') | 81x81 | 52 (X'34') | 125x125 | 74 (X'4A') | 169x169 [BCOCA-4-505]|
| 9 (X'09') | 39x39 | 31 (X'1F') | 83x83 | 53 (X'35') | 127x127 | 75 (X'4B') | 171x171 [BCOCA-4-506]|
| 10 (X'0A') | 41x41 | 32 (X'20') | 85x85 | 54 (X'36') | 129x129 | 76 (X'4C') | 173x173 [BCOCA-4-507]|
| 11 (X'0B') | 43x43 | 33 (X'21') | 87x87 | 55 (X'37') | 131x131 | 77 (X'4D') | 175x175 [BCOCA-4-508]|
| 12 (X'0C') | 45x45 | 34 (X'22') | 89x89 | 56 (X'38') | 133x133 | 78 (X'4E') | 177x177 [BCOCA-4-509]|
| 13 (X'0D') | 47x47 | 35 (X'23') | 91x91 | 57 (X'39') | 135x135 | 79 (X'4F') | 179x179 [BCOCA-4-510]|
| 14 (X'0E') | 49x49 | 36 (X'24') | 93x93 | 58 (X'3A') | 137x137 | 80 (X'50') | 181x181 [BCOCA-4-511]|
| 15 (X'0F') | 51x51 | 37 (X'25') | 95x95 | 59 (X'3B') | 139x139 | 81 (X'51') | 183x183 [BCOCA-4-512]|
| 16 (X'10') | 53x53 | 38 (X'26') | 97x97 | 60 (X'3C') | 141x141 | 82 (X'52') | 185x185 [BCOCA-4-513]|
| 17 (X'11') | 55x55 | 39 (X'27') | 99x99 | 61 (X'3D') | 143x143 | 83 (X'53') | 187x187 [BCOCA-4-514]|
| 18 (X'12') | 57x57 | 40 (X'28') | 101x101 | 62 (X'3E') | 145x145 | 84 (X'54') | 189x189 [BCOCA-4-515]|
| 19 (X'13') | 59x59 | 41 (X'29') | 103x103 | 63 (X'3F') | 147x147 [BCOCA-4-516]| | |
| 20 (X'14') | 61x61 | 42 (X'2A') | 105x105 | 64 (X'40') | 149x149 [BCOCA-4-517]| | |
| 21 (X'15') | 63x63 | 43 (X'2B') | 107x107 | 65 (X'41') | 151x151 [BCOCA-4-518]| | |
If X'00' is specified for this parameter, an appropriate row/column size will be used based on
the amount of symbol data; the smallest symbol that can accommodate the amount of data is
produced.
Byte 8 Level of error correction
This parameter specifies the level of error correction to be used for the symbol. Each higher
level of error correction causes more error correction codewords to be added to the symbol
and therefore leaves fewer codewords for symbol data. Refer to the Han Xin Code symbology
specification for more information about how many codewords are available for symbol data
for each version and error-correction level combination.
Four different levels of Reed-Solomon error correction can be selected:
Level 1 (X'01') allows recovery of 8% of symbol codewords
Level 2 (X'02') allows recovery of 15% of symbol codewords
Level 3 (X'03') allows recovery of 23% of symbol codewords
Level 4 (X'04') allows recovery of 30% of symbol codewords
Exception condition EC-0F23 exists if an invalid level-of-error-correction value is specified.
Han Xin Code Special-Function Parameters [BCOCA-4-519]




Byte 9 Special-function flags
These flags specify special functions that can be used with a Han Xin Code symbol.
Bit 0 GS1 FNC1 alternate data type identifier
If this flag is B'1', this Han Xin Code symbol will indicate that it conforms to the GS1
application identifiers standard. In this case, the industry-FNC1 flag must be B'0'.
Exception condition EC-0F24 exists if an incompatible combination of these bits is
specified.
Bit 1 Industry FNC1 alternate data type identifier
If this flag is B'1', this Han Xin Code symbol will indicate that it conforms to a particular
industry standard format. In this case, the GS1-FNC1 flag must be B'0'. Exception
condition EC-0F24 exists if an incompatible combination of these bits is specified.
When this flag is B'1', an application indicator is specified in byte 10.
Bits
2–7
Reserved
Byte 10 Application indicator for Industry FNC1
When the Industry FNC1 flag is B'1', this parameter specifies an application indicator. The
application indicator is a one-byte value that is specified either as an alphabetic value (from
the ASCII set a-z, A-Z) plus 100 or as a two-digit decimal number (between 00 and 99)
represented as a hexadecimal value. For example:
for application indicator “a” (ASCII value X'61'), specify X'C5'
for application indicator “Z” (ASCII value X'5A'), specify X'BE'
for application indicator “00”, specify X'00'
for application indicator “01”, specify X'01'
for application indicator “99”, specify X'63'
When the Industry FNC1 flag is B'0', this parameter is ignored and should be set to X'00'.
Exception condition EC-0F25 exists if an invalid application-indicator value is specified.
Byte 11 Additional parameters length
This parameter specifies the length of the following additional parameters, not including this
length byte.
Bytes 12
to end
Additional parameters
This area is reserved for potential future use. The content of this area is not checked by
BCOCA receivers. BCOCA generators should not include anything in this area; that is, the
addl-parms-length field in byte 11 should be X'00'.
Han Xin Code Special-Function Parameters [BCOCA-4-520]




Intelligent Mail Package Barcode Special-Function Parameters [BCOCA-4-521]
### Table 28. Intelligent Mail Package Barcode Special-Function Parameters

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | X'00' | Reserved | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-522]|
| 6 | BITS | Intelligent Mail Package Barcode flags [BCOCA-4-523]| | | | |
| | bit 0 | Banner | B'0'<br>B'1' | Suppress USPS Service Banner:<br>Do not suppress<br>Suppress | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-524]|
| | bit 1 | IDBars | B'0'<br>B'1' | Suppress Identification Bars:<br>Do not suppress<br>Suppress | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-525]|
| | bits 2–7 | | B'000000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-526]|
| 7 | X'00' | Reserved | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-527]|
| 8 | UBIN | Banner length | X'00'–X'FE', even values only | Length of USPS Service Banner string | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-528]|
| 9–n | CHAR | Banner string | | USPS Service Banner string | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-529]|
Byte 5 Reserved
Byte 6 Intelligent Mail Package Barcode flags
These flags control how the Intelligent Mail Package Barcode is printed.
Bit 0 Suppress USPS Service Banner
If this flag is B'0', the USPS Service Banner is printed, using the string in
bytes 9–n.
If this flag is B'1', the USPS Service Banner is suppressed; that is, not printed.
Since the Intelligent Mail Package Barcode symbology requires a Service
Banner to be presented, the assumption is that the user will use some other
method to print the Service Banner.
Bit 1 Suppress Identification Bars
If this flag is B'0', the Identification Bars are printed.
If this flag is B'1', the Identification Bars are suppressed; that is, not printed.
Since the Intelligent Mail Package Barcode symbology requires the
Identification Bars, the assumption is that the user will use some other
method to print the Identification Bars.
Bits 2–7 Reserved
Byte 7 Reserved
Byte 8 Length of USPS Service Banner that follows
This field specifies the length, in bytes, of the USPS Service Banner string that follows in bytes
9–n; this length does not contain the length field itself. If the length is not an even value,
exception condition EC-0F15 exists.
Intelligent Mail Package Barcode Special-Function Parameters [BCOCA-4-530]




If the Suppress USPS Service Banner flag is B'0' but this byte has value X'00'—that is, the
Service Banner is supposed to be printed, but the Service Banner string is empty—exception
condition EC-0F14 exists.
If the Suppress USPS Service Banner flag is B'1', this byte and bytes 9–n are ignored.
Bytes 9–n USPS Service Banner string
This field contains the string of characters to be displayed as the USPS Service Banner in the
Intelligent Mail Package Barcode.
The characters are encoded in UTF-16BE. Note that using UTF-16BE means that both the ™
and the ® symbols, which are recommended in USPS Publication 199, are supported. If the
characters contain invalid data, exception condition EC-0F13 exists.
The bar code symbology specifies that the USPS Service Banner “shall not exceed the total
combined length of the barcode and the minimum clear zones to left and right of the barcode.”
In the case that the generated Service Banner text is too long to follow that rule, exception
condition EC-0F13 exists. However, it is recommended, when possible, that an attempt first be
made to use a smaller font size; if the resulting text is still too long, the exception exists. Note
that when reducing the font size, care must be taken to avoid reducing below the minimum
character height specified in the symbology. Reduction of the font size of the Service Banner
should not also reduce the font size of the HRI printed below the symbol.
If the Suppress USPS Service Banner flag is B'1', byte 8 and bytes 9–n are ignored.
Intelligent Mail Package Barcode Special-Function Parameters [BCOCA-4-531]




MaxiCode Special-Function Parameters [BCOCA-4-532]
### Table 29. MaxiCode Special-Function Parameters

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags [BCOCA-4-533]| | | | |
| | bit 0 | EBCDIC | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>Do not translate<br>Convert data to ASCII | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-534]|
| | bit 1 | Escape sequence handling | B'0'<br>B'1' | Escape-sequence handling:<br>Process escape sequences<br>Ignore all escape sequences | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-535]|
| | bits 2–7 | | B'000000' | Reserved | B'000000' | B'000000' [BCOCA-4-536]|
| 6 | CODE | Symbol mode | X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06' | Mode 2<br>Mode 3<br>Mode 4<br>Mode 5<br>Mode 6 | Not supported in BCD1 | X'02', X'03', X'04', X'05', X'06' [BCOCA-4-537]|
| 7 | UBIN | Sequence indicator | X'00'–X'08' | Structured append sequence indicator | Not supported in BCD1 | X'00'–X'08' [BCOCA-4-538]|
| 8 | UBIN | Total symbols | X'00' or X'02'–X'08' | Total number of structured-append symbols | Not supported in BCD1 | X'00' or X'02'–X'08' [BCOCA-4-539]|
| 9 | BITS | Special-function flags [BCOCA-4-540]| | | | |
| | bit 0 | Zipper | B'0'<br>B'1' | No zipper pattern<br>Vertical zipper pattern on right | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-541]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' | B'0000000' [BCOCA-4-542]|
Byte 5 Control flags
These flags control how the bar code data (bytes n+1 to end) is processed by the BCOCA
receiver; the receiver can be an IPDS printer or any other product that processes BCOCA
objects.
Bit 0 EBCDIC-to-ASCII translation
If this flag is B'0', the data is assumed to begin in the default character encodation and
no translation is done.
If this flag is B'1', the BCOCA receiver will convert each byte of the bar code data from
EBCDIC code page 500 into ASCII code page 819 before this data is used to build the
bar code symbol.
Bit 1 Escape-sequence handling
If this flag is B'0', each X'5C' (backslash) within the bar code data is treated as an
escape character according to the MaxiCode symbology specification.
If this flag is B'1', each X'5C' within the bar code data is treated as a normal data
character and therefore all escape sequences are ignored. In this case, no ECI code
page switching can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC backslash
characters (X'E0') will first be converted into X'5C' before the escape-sequence
handling flag is applied.
Bits
2–7
Reserved
MaxiCode Special-Function Parameters [BCOCA-4-543]




Byte 6 Symbol mode
Note: The symbol modes are described using the default character encoding (ECI 000003;
ASCII code page 819). When the EBCDIC-to-ASCII translation flag is set to B'1', each
code point in the data must be specified in EBCDIC. The EBCDIC code point for the
“RS” character is X'1E' and the EBCDIC code point for the “GS” character is X'1D'.
Mode 2 Structured Carrier Message - numeric postal code
This mode is designed for use in the transport industry, encoding the postal
code, country code, and service class with the postal code being numeric.
The bar code data should be structured as described in B.2.1 and B.3.1 of the
AIM International Symbology Specification - MaxiCode. The postal code,
country code, and service class are placed in the primary message portion of
the MaxiCode symbol and the rest of the bar code data is placed in the
secondary message portion of the MaxiCode symbol. The first part of the bar
code data includes the postal code, country code and service class, in that
order, separated by the [GS] character (X'1D'). This information may be
preceded by the character sequence “[)>RS01GSyy”, where RS and GS are
single characters and yy are two decimal digits representing a year. This
character sequence represented in hex bytes is X'5B293E1E30311Dxxxx',
where each xx is a value from X'30' to X'39'. This sequence indicates that the
message conforms to particular open system standards. This first portion of
the bar code data must be encoded using the MaxiCode default character set
(ECI 000003 = ISO 8859-1). This first portion of the bar code data must not
contain the backslash escape character to change the ECI character set. The
postal code must be one to nine decimal digits with each digit represented by
the byte values from X'30' to X'39'. The country code must be one to three
decimal digits with each digit being a byte value from X'30' to X'39'. The
service code must also be one to three decimal digits, again with each digit
being a byte value from X'30' to X'39'. The primary message portion of the
MaxiCode symbol uses Enhanced Error Correction (EEC) and the secondary
message portion of the MaxiCode symbol uses Standard Error Correction
(SEC).
When the postal code portion of the Structured Carrier Message is numeric,
mode 2 should be used.
Mode 3 Structured Carrier Message - alphanumeric postal code
This mode is designed for use in the transport industry, encoding the postal
code, country code, and service class with the postal code being
alphanumeric. The bar code data should be structured as described in B.2.1
and B.3.1 of the AIM International Symbology Specification - MaxiCode. The
postal code, country code, and service class are placed in the primary
message portion of the MaxiCode symbol and the rest of the bar code data is
placed in the secondary message portion of the MaxiCode symbol. The first
part of the bar code data includes the postal code, country code and service
class, in that order, separated by the [GS] character (X'1D'). This information
may be preceded by the character sequence “[)>RS01GSyy”, where RS and
GS are single characters and yy are two decimal digits representing a year.
This character sequence represented in hex bytes is
X'5B293E1E30311Dxxxx', where each xx is a value from X'30' to X'39'. This
sequence indicates that the message conforms to particular open system
standards. This first portion of the bar code data must be encoded using the
MaxiCode default character set (ECI 000003 = ISO 8859-1). This first portion
of the bar code data must not contain the backslash escape character to
change the ECI character set. The postal code must be one to six
alphanumeric characters with each character being one of the printable
characters in MaxiCode Code Set A. Postal codes less than 6 characters will
MaxiCode Special-Function Parameters [BCOCA-4-544]




be padded with trailing spaces; postal codes longer than 6 characters will be
truncated. These characters include the letters A to Z (X'41' to X'5A'), the
space character (X'20'), the special characters (X'22' to X'2F'), the decimal
digits (X'30' to X'39'), and the colon (X'3A'). The country code must be one to
three decimal digits with each digit being a byte value from X'30' to X'39'. The
service code must also be one to three decimal digits, again with each digit
being a byte value from X'30' to X'39'. The primary message portion of the
MaxiCode symbol uses Enhanced Error Correction (EEC) and the secondary
message portion of the MaxiCode symbol uses Standard Error Correction
(SEC).
When the postal code portion of the Structured Carrier Message is
alphanumeric, mode 3 should be used.
Mode 4 Standard Symbol
The symbol employs EEC for the Primary Message and SEC for the
Secondary Message. The first nine codewords are placed in the Primary
Message and the rest of the codewords are placed in the Secondary
Message. This mode provides for a total of 93 codewords for data. If the bar
code data consists of only characters from MaxiCode Code Set A, the
number of codewords matches the number of bar code data characters.
However, if the bar code data contains other characters, the number of
codewords is greater than the number of bar code data characters due to the
overhead of switching to and from the different code sets. The Code Set A
consists of the byte values X'0D', X'1C' to X'1E', X'20', X'22' to X'3A', and
X'41' to X'5A'.
Mode 5 Full ECC Symbol
The symbol employs EEC for the Primary Message and EEC for the
Secondary Message. The first nine codewords are placed in the Primary
Message and the rest of the codewords are placed in the Secondary
Message. This mode provides for a total of 77 codewords for data. If the bar
code data consists of only characters from MaxiCode Code Set A, the
number of codewords matches the number of bar code data characters.
However, if the bar code data contains other characters, the number of
codewords is greater than the number of bar code data characters due to the
overhead of switching to and from the different code sets. The Code Set A
consists of the byte values X'0D', X'1C' to X'1E', X'20', X'22' to X'3A', and
X'41' to X'5A'.
Mode 6 Reader Program, SEC
The symbol employs EEC for the Primary Message and SEC for the
Secondary Message. The data in the symbol is used to program the bar code
reader system. The first nine codewords are placed in the Primary Message
and the rest of the codewords are placed in the Secondary Message. This
mode provides for a total of 93 codewords for data. If the bar code data
consists of only characters from MaxiCode Code Set A, the number of
codewords matches the number of bar code data characters. However, if the
bar code data contains other characters, the number of codewords is greater
than the number of bar code data characters due to the overhead of switching
to and from the different code sets. The Code Set A consists of the byte
values X'0D', X'1C' to X'1E', X'20', X'22' to X'3A', and X'41' to X'5A'.
Exception condition EC-0F05 exists if an invalid symbol-mode value is specified.
MaxiCode Special-Function Parameters [BCOCA-4-545]




Byte 7 Structured append sequence indicator
Multiple MaxiCode bar code symbols (called structured appends) can be logically linked
together to encode large amounts of data. The logically linked symbols can be presented on
the same or on different physical media, and are logically recombined after they are scanned.
From 2 to 8 MaxiCode symbols can be linked. This parameter specifies where this particular
symbol is logically linked (1–8) in a sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. Exception
condition EC-0F01 exists if an invalid sequence indicator value is specified. Exception
condition EC-0F02 exists if the sequence indicator is larger than the total number of symbols
(byte 8).
Byte 8 T otal symbols in a structured append
This parameter specifies the total number of symbols (2–8) that is logically linked in a
sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. If this
symbol is not part of a structured append, both bytes 6 and 7 must be X'00', or exception
condition EC-0F03 exists.
Exception condition EC-0F04 exists if an invalid number of symbols is specified.
Byte 9 Special-function flags
These flags specify special functions that can be used with a MaxiCode symbol.
Bit 0 Zipper pattern
If this flag is B'1', a vertical zipper-like test pattern and a contrast block is printed to the
right of the symbol. The zipper provides a quick visual check for printing distortions. If
the symbol presentation space is rotated, the zipper and contrast block are rotated
along with the symbol.
T o maintain consistency among printers, the zipper pattern and contrast block should
approximate the guideline dimensions shown in Figure 11. The zipper
pattern and contrast block is made up of several filled rectangles that should be
created such that each rectangle is as close to the specified dimensions as possible
for the particular device resolution, then the pattern is repeated to yield an evenly
spaced zipper pattern and contrast block.
Bits
1–7
Reserved
MaxiCode Special-Function Parameters [BCOCA-4-546]




Figure 11. Example of a MaxiCode Bar Code Symbol with Zipper and Contrast Block
. . .
. . .
This pattern repeats for a total of 9 bars,
with each bar 2/203 inch by 28/203 inch.
The space between each pair of bars is
1/203 inch.
The contrast block anchor point is 38/203
inch directly above the zipper anchor point.
This pattern repeats for approximately
one inch (a total of 40 of these zipper
teeth at 203 DPI).  Each of the zipper
teeth is made up of three 2/203 inch by
12/203 inch rectangles.
The space between each pair of teeth is
1/203 inch.
The zipper anchor point is 19/203 inch
right of the rightmost column of
hexagons that forms the MaxiCode
symbol and is aligned with the top of
the MaxiCode symbol.
Contrast block anchor point
Zipper pattern anchor point
Guideline Dimensions for the Zipper and Contrast Block
10/203 10/203
32/203
MaxiCode Special-Function Parameters [BCOCA-4-547]




PDF417 Special-Function Parameters
Table 30. PDF417 Special-Function Parameters [BCOCA-4-548]

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags | | | Not supported in BCD1 [BCOCA-4-549]| |
| | | bit 0 | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>• Do not translate<br>• Convert data to ASCII | | B'0'<br>B'1' [BCOCA-4-550]|
| | | bit 1 | B'0'<br>B'1' | Escape-sequence handling:<br>• Process escape sequences<br>• Ignore all escape sequences | | B'0'<br>B'1' [BCOCA-4-551]|
| | | bits 2–7 | B'000000' | Reserved | B'000000' | B'000000' [BCOCA-4-552]|
| 6 | UBIN | Data symbols | X'01'–X'1E' | Number of data symbol characters per row | Not supported in BCD1 | X'01'–X'1E' [BCOCA-4-553]|
| 7 | UBIN | Rows | X'03'–X'5A'<br>X'FF' | Desired number of rows<br>Minimum necessary rows | Not supported in BCD1 | X'03'–X'5A'<br>X'FF' [BCOCA-4-554]|
| 8 | UBIN | Security | X'00'–X'08' | Security level | Not supported in BCD1 | X'00'–X'08' [BCOCA-4-555]|
| 9–10 | UBIN | Macro length | X'0000'–X'7FED' | Length of Macro PDF417 Control Block that follows | Not supported in BCD1 | X'0000'–X'7FED' [BCOCA-4-556]|
| 11–n | UBIN | Macro data | Any value | Data for a Macro PDF417 Control Block | Not supported in BCD1 | Any value [BCOCA-4-557]|
Byte 5 Control flags
These flags control how the bar code data is processed by the BCOCA receiver; the receiver
can be an IPDS printer or any other product that processes BCOCA objects.
Bit 0 EBCDIC-to-ASCII translation (for bytes 11 to end)
If this flag is B'0', the data is assumed to begin in the default character encodation and
no translation is done.
If this flag is B'1', the BCOCA receiver will convert each byte of the bar code data
(bytes n+1 to end) and each byte of the Macro PDF417 Control Block data (bytes 11–
n) from a subset of EBCDIC code page 500 into the default character encodation (GLI
0) before this data is used to build the bar code symbol. This translation covers 181
code points that include alphanumerics and many symbols; the 75 code points that
are not covered by the translation do not occur in EBCDIC and are mapped to X'7F'
(127). Refer to Figure 12 for a picture showing the 181 EBCDIC code
points that can be translated.
The EBCDIC-to-ASCII translation flag should not be used if any of the 75 code points
that have no EBCDIC equivalent are needed for the bar code data or for the Macro
PDF417 Control Block data.
Table 5 in the Uniform Symbology Specification – PDF417 shows the full set of GLI 0
code points; from this set, the 75 code points that have no EBCDIC equivalent are as
follows:
158, 159, 169, 176–224, 226–229, 231–240, 242–245, 247, 249, 251–252, and
254.
PDF417 Special-Function Parameters [BCOCA-4-558]




The 75 EBCDIC code points that are not covered by the translation and are thus
mapped into X'7F' are as follows:
X'04', X'06', X'08'–X'0A', X'14'–X'15', X'17', X'1A'–X'1B', X'20'–X'24', X'28'–X'2C',
X'30'–X'31', X'33'–X'36', X'38'–X'3B', X'3E', X'46', X'62', X'64'–X'66', X'6A', X'70',
X'72'–X'78', X'80', X'8C'–X'8E', X'9D', X'9F', X'AC'–X'AF', X'B4'–X'B6', X'B9', X'BC'–
X'BF', X'CA', X'CF', X'DA', X'EB', X'ED'–X'EF', X'FA'–X'FB', X'FD'–X'FF'.
Figure 12. Subset of EBCDIC Code Page 500 That Can Be Translated To GLI 0
Hex Digits
1st
2nd
-0
-1
-3
-2
-4
-5
-6
0- 5- 6-1- 7-2- 8-3- 4- 9- A- B- C- D- E- F- [BCOCA-4-559]
SE030000
SE040000
SE100000
SE090000 SE240000
SE110000
SE190000
SE200000
SE350000
SE230000 LB010000 LB020000
LC010000 LC020000
LD010000 LD020000
LE010000 LE020000
LF010000 LF020000
LK010000 LK020000LS010000 LS020000
LL010000 LL020000LT010000 LT020000
LM010000 LM020000LU010000 LU020000
LN010000 LN020000LV010000 LV020000
LO010000 LO020000LW010000 LW020000 ND060000
ND050000
ND040000
ND030000
ND020000
ND010000
SC040000 ND100000
SP120000 LA010000 LA020000LJ010000 LJ020000
SP010000
(SP)
SM030000
& _ { }
SP100000 SM110000 SM140000 SM070000
SD190000SE020000 SE180000
SE010000 SE170000
\DLENUL
SOH
STX
ETX
HT
BS
VT
FF
CR
SO
SI
GS
RS
US
ENQ
ACK
BEL SUB
NAK
DC4
ESC
CAN
EM
EOT
ETB
LF
DC3
DC2
FS
SYN
DC1 / a Aj J
b B
c C
d D
e E
f F
k Ks S
l Lt T
m Mu U
n Nv V
o Ow W 6
5
4
3
2
1
0
-E
-F
SA020000
SA010000 SP140000 SA050000
SP020000 SP150000 SP040000
SA040000
? "
+ ; > =
-7
-8
-A
-9
-B
-C
-D
SE210000
SP110000 SC030000 SP080000
SM080000 SP130000
SD130000 LI010000 LI020000LR010000 LR020000LZ010000 LZ020000
ND021000
SM130000
SM660000
LU180000
ND090000
ND080000
ND070000LX010000
LH010000 LH020000LQ010000 LQ020000
SA030000 SM040000 SM020000
SP060000 SP070000 SP090000 SP050000
SM050000
SM010000
SE280000 LG010000 LG020000LP010000 LP020000 LX020000
LY010000 LY020000
SE120000
SE130000
SE140000
SE150000
SE360000
SE370000
SE380000SE160000
SE060000
SE070000
SE080000 SE270000
SE220000
SE250000
SE260000
SE050000
g
H
Gp
Q
P
Y
X
y
. $ , #
:
`
h q
i Ir Rz Z 9
8
7x
( ) _ '
< * % @
˜
SE330000
DEL
SP300000
LA150000
LA170000
LA110000
LA130000
LA270000 LI170000
LI150000
LI110000
LE130000
LE170000
LE150000
LE110000
â
ä
à
á
(RSP)
å
é
ë
ê
è
í
î
ï
LA180000
LA280000
LE120000
Ä
Å
É
SM190000 SM170000
° µ ¢
SC020000
SC050000
SD630000
NF040000
£
¥
·
¼
SA060000
÷
LC410000
SM060000
LN190000
ç
ñ
!
[
LI130000
LS610000
SD150000
ì
ß
]
^
LC420000
Ln200000
Ç
Ñ
SP170000
SP180000
«
»
±
SM210000
SM200000
LA510000
LA520000
ª -
° -
æ
Æ
SP030000
SP160000
¡
¿
NF010000
½
¬
|
LO150000
LO170000
LO130000
LO110000
ô
ö
ò
ó
LU150000
LU170000
LU130000
LU110000
LY170000
û
ü
ù
ú
ÿ
LO180000
²
Ö Ü
PDF417 Special-Function Parameters [BCOCA-4-560]




Bit 1 Escape-sequence handling (for bytes n+1 to end)
If this flag is B'0', each X'5C' (backslash) within the bar code data is treated as an
escape character according to the PDF417 symbology specification.
If this flag is B'1', each X'5C' within the bar code data is treated as a normal data
character and therefore all escape sequences are ignored. In this case, no GLI code
page switching and no reader programming can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC backslash
characters (X'E0') will first be converted into X'5C' before the escape-sequence
handling flag is applied.
Bits
2–7
Reserved
Byte 6 Data symbol characters per row
This parameter specifies the number of data symbol characters per row. Each row consists of
a start pattern, a left row indicator codeword, 1 to 30 data symbol characters, a right row
indicator codeword (omitted in a truncated symbol), and a stop pattern. The aspect ratio of the
bar code symbol is determined by the number of data symbol characters and the number of
rows.
Exception condition EC-0F06 exists if an invalid number of data symbol characters per row is
specified.
Because of the Error Checking and Correction (ECC) algorithm and the data compaction
method used by the printer when the symbol is built, the number of data symbol characters is
not necessarily the same as the number of characters in the bar code data.
Byte 7 Desired number of rows
This parameter specifies the desired number of rows in the bar code symbol. From 3 to 90
rows can be specified or X'FF' can be specified to instruct the printer to generate the minimum
number of rows necessary. The number of rows times the number of data symbol characters
per row cannot exceed 928. Exception condition EC-0F07 exists if an invalid number of rows
is specified.
The actual number of rows generated depends on the amount of data to be encoded and on
the security level selected. If more rows than necessary are specified, the symbol is padded to
fill the requested number of rows. If not enough rows are specified, enough extra rows will be
inserted by the printer to produce the symbol.
If too much data is specified to fit in the bar code symbol, exception condition EC-0F08 exists.
PDF417 Special-Function Parameters [BCOCA-4-561]




Byte 8 Security level
This parameter specifies the desired security level for the symbol as a value between 0 and 8.
Each higher security level causes more error correction codewords to be added to the symbol.
At a particular security level, a number of codewords can be missing or erased and the symbol
can still be recovered. Also, PDF417 can recover from misdecodes of codewords. The formula
is: Maximum Limit >= Erasures + 2*Misdecodes The relation of security level to error
correction capability is as follows:
Security level Maximum Limit of
Erasures + 2*Misdecodes
0 0
1 2
2 6
3 14
4 30
5 62
6 126
7 254
8 510
For example, at security level 6, a total of 126 codewords can be either missing or destroyed
and the entire symbol can still be completely recovered. The following table provides a
recommended security level for various amounts of data:
Number of Data Codewords Recommended Security Level
1–40 2
41–160 3
161–320 4
321–863 5
Exception condition EC-0F09 exists if an invalid security level value is specified.
PDF417 Special-Function Parameters [BCOCA-4-562]




Bytes 9–10 Length of Macro PDF417 Control Block that follows
This field specifies the length of a Macro PDF417 Control Block that follows in bytes 11–n; this
length does not contain the length field itself.
If X'0000' is specified, there is no Macro PDF417 Control Block specified as a special function
and this is the last field of the special-function parameters; what follows is the bar code data
itself.
If a value between X'0001' and X'7FED' is specified, the BCOCA receiver will build a Macro
PDF417 Control Block at the end of the bar code symbol using the data in bytes 11–n.
If an invalid length value is specified, exception condition EC-0F0C exists.
Bytes 11–n Macro PDF417 Control Block data
The special codewords “\922”, “\923”, and “\928” are used for coding a Macro PDF417 Control
Block as defined in section G.2 of the Uniform Symbology Specification PDF417, but these
codewords must not be used within the bar code data. Exception condition EC-2100 exists if
one of these escape sequences is found in the bar code data. If a Macro PDF417 Control
Block is needed, it is specified in bytes 11–n.
The data for this Macro PDF417 Control Block must adhere to the following format; exception
condition EC-0F0D exists if this format is not followed:
For the symbol in a Macro PDF417 that represents the last segment of the Macro PDF417,
the data must contain “\922”. For all symbols in a Macro PDF417, except the one
representing the last segment:
– A Macro PDF417 Control Block starts with a “\928” escape sequence.
– Followed by 1 to 5 numeric digits (bytes values X'30' to X'39'), representing a segment
index value from 1 to 99,999.
– Followed by a variable number of escape sequences containing values from “\000” to
“\899”, representing the file ID.
– Followed by zero or more optional fields, with the following layout:
◦ “\923” escape sequence, signaling an optional field
◦ Escape sequence containing the field designator with a value from “\000” to “\006”
◦ Followed by a variable number of text characters (for field designators “\000”, “\003”, and
“\004”) or a variable number of numeric digits (for field designators “\001”, “\002”, “\005”,
and “\006”). The field designators are defined in Table G1 of the Uniform Symbology
Specification. For text characters, the byte values must be X'09', X'0A', X'0D', or from
X'20' through X'7E'. These values represent the upper case letters A through Z, the
lower case letters a through z, and the digits 0 through 9, plus some punctuation and
special characters (for GLI 0). For the numeric digits, the byte values must be from X'30'
through X'39'.
• For field designator “\001”, the one to five numeric digits that follow represent the [BCOCA-4-563]
segment count. This value must be greater than or equal to the segment index value.
• For field designator “\002”, the one to eleven numeric digits that follow represent the [BCOCA-4-564]
time stamp on the source file expressed as the elapsed time in seconds since January
1, 1970 00:00 GMT .
• For field designator “\005”, one or more numeric digits must follow. [BCOCA-4-565]
• For field designator “\006”, the one to five numeric digits that follow represent the [BCOCA-4-566]
decimal value of the 16-bit CRC checksum over the entire source file. This checksum
value must be a decimal value from 0 through 65,535.
Note that the file name, segment count, time stamp, sender, addressee, file size, and
checksum are provided in the optional fields of the Macro PDF417 Control Block and the
PDF417 Special-Function Parameters [BCOCA-4-567]




BCOCA receiver makes no attempt to calculate or verify these values (other than the
previously stated restrictions). If the Macro PDF417 Control Block data does not follow these
rules, exception condition EC-0F0D exists. Note that the Uniform Symbology Specification
PDF417 has the following additional claims. The BCOCA receiver does not check for these
claims nor does it report any exceptions conditions if these claims are violated:
• If the optional Segment Count is given in the Macro PDF417 Control Block of one of the [BCOCA-4-568]
segments (symbols) of the macro, then it should be used in all of the segments (symbols) of
the macro.
• All optional fields, other than the Segment Count, only need to appear in one of the [BCOCA-4-569]
segments (symbols) of the macro.
• If an optional field with the same field designator appears in more than one segment [BCOCA-4-570]
(symbol) of the same macro, then it must appear identically in every segment (symbol).
PDF417 Special-Function Parameters [BCOCA-4-571]




QR Code Special-Function Parameters [BCOCA-4-572]
### Table 31. QR Code Special-Function Parameters

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | BITS | Control flags [BCOCA-4-573]| | | | |
| | bit 0 | EBCDIC | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>Do not translate<br>Convert data to ASCII | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-574]|
| | bit 1 | Escape sequence handling | B'0'<br>B'1' | Escape-sequence handling:<br>Process escape sequences<br>Ignore all escape sequences | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-575]|
| | bit 2 | Too much data | B'0'<br>B'1' | If too much data:<br>Use a bigger QR Code symbol version<br>Exception EC-0F16 exists | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-576]|
| | bits 3–7 | | B'00000' | Reserved | B'00000' | B'00000' [BCOCA-4-577]|
| 6 | CODE | Conversion | X'00'<br>X'01'–X'03'<br>X'04'–X'09' | No conversion specified<br>SBCS EBCDIC code page used to encode data:<br>X'01': Code page 500 (International #5)<br>X'02': Code page 290 (Japanese Katakana Ext.)<br>X'03': Code page 1027 (Japanese Latin Extended)<br>AFP Line Data SOSI-data conversion:<br>X'04': CCSID 1390 to CCSID 943<br>X'05': CCSID 1399 to CCSID 943<br>X'06': CCSID 1390 to CCSID 932<br>X'07': CCSID 1399 to CCSID 932<br>X'08': CCSID 1390 to CCSID 942<br>X'09': CCSID 1399 to CCSID 942 | Not supported in BCD1 | X'00', X'01', X'02', X'03' [BCOCA-4-578]|
| 7 | CODE | Version | X'00'<br>X'01'–X'28' | Version of symbol:<br>Smallest symbol<br>Version number (1 to 40) | Not supported in BCD1 | X'00', X'01'–X'28' [BCOCA-4-579]|
| 8 | CODE | Error correction level | X'00'<br>X'01'<br>X'02'<br>X'03' | Level of error correction:<br>Level L (7% recovery)<br>Level M (15% recovery)<br>Level Q (25% recovery)<br>Level H (30% recovery) | Not supported in BCD1 | X'00', X'01', X'02', X'03' [BCOCA-4-580]|
| 9 | UBIN | Sequence indicator | X'00'–X'10' | Structured append sequence indicator | Not supported in BCD1 | X'00'–X'10' [BCOCA-4-581]|
| 10 | UBIN | Total symbols | X'00' or X'02'–X'10' | Total number of structured-append symbols | Not supported in BCD1 | X'00' or X'02'–X'10' [BCOCA-4-582]|
| 11 | UBIN | Parity Data | X'00'–X'FF' | Structured append parity data | Not supported in BCD1 | X'00'–X'FF' [BCOCA-4-583]|
| 12 | BITS | Special-function flags [BCOCA-4-584]| | | | |
| | bit 0 | UCC/EAN FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to UCC/EAN standards | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-585]|
| | bit 1 | Industry FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to industry standards | Not supported in BCD1 | B'0'<br>B'1' [BCOCA-4-586]|
| | bits 2–7 | | B'000000' | Reserved | B'000000' | B'000000' [BCOCA-4-587]|
| 13 | CODE | Application indicator | See field description | Application indicator for Industry FNC1 | Not supported in BCD1 | All values listed in the field description [BCOCA-4-588]|
Byte 5 Control flags
These flags control how the bar code data (bytes n+1 to end) is processed by the BCOCA
receiver; the receiver can be an IPDS printer or any other product that processes BCOCA
objects.
Bit 0 EBCDIC-to-ASCII translation
If this flag is B'0', the data is assumed to begin in the default character encodation
(ECI 000020) and no translation is done.
If this flag is B'1' and a non-zero value is selected in byte 6, the EBCDIC input data will
be converted into the default character encodation, as follows:
• When the conversion parameter (byte 6) is X'01', X'02', or X'03', the BCOCA [BCOCA-4-589]
receiver will convert each byte of the bar code data from the EBCDIC single-byte
code page specified in byte 6 into ASCII code page 897 before this data is used to
build the bar code symbol. These conversion choices are supported by IPDS
printers.
• Conversion parameters X'04' – X'09' are defined for software products that build [BCOCA-4-590]
BCOCA bar codes from AFP Line Data (these values are not supported by IPDS
printers). The AFP Line Data software will convert the input line data from EBCDIC
SOSI data into mixed-byte ASCII as specified by the conversion parameter.
• When the conversion parameter (byte 6) is X'00', no translation is done. [BCOCA-4-591]
Bit 1 Escape-sequence handling
If this flag is B'0', each X'5C' (¥) within the bar code data is treated as an escape
character according to the QR Code symbology specification.
If this flag is B'1', each X'5C' (¥) within the bar code data is treated as a normal data
character and therefore all escape sequences are ignored. In this case, no ECI code
page switching can occur within the data.
Note: If the EBCDIC-to-ASCII translation flag is also set to B'1', all EBCDIC ¥
characters will first be converted into X'5C' before the escape-sequence
handling flag is applied.
QR Code Special-Function Parameters [BCOCA-4-592]




Bit 2 T oo much data
This flag specifies the behavior when both of the following two conditions exist:
• The version parameter (byte 7) is in the range X'01'–X'28'; that is, it requests a [BCOCA-4-593]
specific version of the symbol.
• The number of bytes of data to be encoded, combined with the error correction level [BCOCA-4-594]
requested (byte 8), will not fit in the QR Code version specified by the version
parameter.
This flag is ignored otherwise.
If this flag is B'0', the version of the symbol will be made bigger to fit the data. This was
the behavior prior to the creation of this flag.
If this flag is B'1', exception EC-0F16 exists. This value is useful when the QR Code
being produced is required to be a specific version. The B'1' value of this flag is an
optional function that is not supported by all BCOCA receivers. IPDS printers indicate
support for this function with Sense Type and Model property pair X'1306'.
Bits
3–7
Reserved
QR Code Special-Function Parameters [BCOCA-4-595]




Byte 6 Conversion
When the EBCDIC-to-ASCII translation flag is B'1', this parameter specifies the method used
to convert EBCDIC input data into the default character encodation. When the EBCDIC-to-
ASCII translation flag is B'0', this parameter is not used and should be set to X'00'.
For the first three values (used when the input data is encoded with a single-byte EBCDIC
code page), this parameter identifies the EBCDIC code page that encodes single-byte
EBCDIC bar code data. The following EBCDIC code pages are supported:
X'01' Code page 500 (International #5)
Only 128 of the characters within ECI 000020 can be specified in code page 500. The
code page 500 characters that can be translated are shown in Figure 13.
X'02' Code page 290 (Japanese Katakana Extended)
X'03' Code page 1027 (Japanese Latin Extended)
For the remaining values (used when the input data is SOSI), this parameter identifies the
desired conversion from EBCDIC SOSI input data to a specific mixed-byte ASCII encoding.
Note: The values X'04' through X'09' are defined for the Additional Bar Code Parameters
(X'7B') triplet used with AFP Line Data; these values are not valid within a BCOCA
object built for a non-line-data environment, such as MO:DCA and IPDS. Refer to the
Advanced Function Presentation: Programming Guide and Line Data Reference for a
description of the Additional Bar Code Parameters (X'7B') triplet.
The following choices are supported:
X'04' CCSID 1390 to CCSID 943
Convert from: CCSID 1390 – Extended Japanese Katakana-Kanji Host Mixed for
JIS X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 943 – Japanese PC Data Mixed for Open environment (Multi-
vendor code): 6878 JIS X 0208-1990 chars, 386 IBM selected DBCS
chars, 1880 UDC (X'F040' to X'F9FC')
X'05' CCSID 1399 to CCSID 943
Convert from: CCSID 1399 – Extended Japanese Latin-Kanji Host Mixed for JIS
X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 943 – Japanese PC Data Mixed for Open environment (Multi-
vendor code): 6878 JIS X 0208-1990 chars, 386 IBM selected DBCS
chars, 1880 UDC (X'F040' to X'F9FC')
X'06' CCSID 1390 to CCSID 932
Convert from: CCSID 1390 – Extended Japanese Katakana-Kanji Host Mixed for
JIS X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 932 – Japanese PC Data Mixed including 1880 UDC
X'07' CCSID 1399 to CCSID 932
Convert from: CCSID 1399 – Extended Japanese Latin-Kanji Host Mixed for JIS
X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 932 – Japanese PC Data Mixed including 1880 UDC
QR Code Special-Function Parameters [BCOCA-4-596]




X'08' CCSID 1390 to CCSID 942
Convert from: CCSID 1390 – Extended Japanese Katakana-Kanji Host Mixed for
JIS X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 942 – Japanese PC Data Mixed including 1880 UDC,
Extended SBCS
X'09' CCSID 1399 to CCSID 942
Convert from: CCSID 1399 – Extended Japanese Latin-Kanji Host Mixed for JIS
X0213 including 6205 UDC, Extended SBCS (includes SBCS &
DBCS euro)
Convert to: CCSID 942 – Japanese PC Data Mixed including 1880 UDC,
Extended SBCS
EBCDIC characters that are not defined within ECI 000020 are mapped to the substitute
character, X'7F' or X'FCFC'; exception condition EC-2100 exists when an undefined character
is encountered.
Exception condition EC-0F0E exists if an invalid or unsupported conversion value is specified.
Figure 13. Subset of EBCDIC Code Page 500 That Can Be Translated To ECI 000020
Hex Digits
1st
2nd
-0
-1
-3
-2
-4
-5
-6
0- 5- 6-1- 7-2- 8-3- 4- 9- A- B- C- D- E- F- [BCOCA-4-597]
SE030000
SE040000
SE100000
SE090000 SE240000
SE110000
SE190000
SE200000
SE350000
SE230000 LB010000 LB020000
LC010000 LC020000
LD010000 LD020000
LE010000 LE020000
LF010000 LF020000
LK010000 LK020000LS010000 LS020000
LL010000 LL020000LT010000 LT020000
LM010000 LM020000LU010000 LU020000
LN010000 LN020000LV010000 LV020000
LO010000 LO020000LW010000 LW020000 ND060000
ND050000
ND040000
ND030000
ND020000
ND010000
ND100000
SP120000 LA010000 LA020000LJ010000 LJ020000
SP010000
(SP)
SM030000
& _ { }
SP100000 SM110000 SM140000
SE020000 SE180000
SE010000 SE170000
DLENUL
SOH
STX
ETX
HT
BS
VT
FF
CR
SO
SI
GS
RS
US
ENQ
ACK
BEL SUB
NAK
DC4
ESC
CAN
EM
EOT
ETB
LF
DC3
DC2
FS
SYN
DC1 / a Aj J
b B
c C
d D
e E
f F
k Ks S
l Lt T
m Mu U
n Nv V
o Ow W 6
5
4
3
2
1
0
-E
-F
SA010000 SP140000 SA050000
SP020000 SP150000 SP040000
SA040000
? "
+ ; > =
-7
-8
-A
-9
-B
-C
-D
SE210000
SP110000 SC030000 SP080000
SM080000 SP130000
SD130000 LI010000 LI020000LR010000 LR020000LZ010000 LZ020000
SM130000
ND090000
ND080000
ND070000LX010000
LH010000 LH020000LQ010000 LQ020000
SA030000 SM040000 SM020000
SP060000 SP070000 SP090000 SP050000
SM050000
SM010000
SE280000 LG010000 LG020000LP010000 LP020000 LX020000
LY010000 LY020000
SE120000
SE130000
SE140000
SE150000
SE360000
SE370000
SE380000SE160000
SE060000
SE070000
SE080000 SE270000
SE220000
SE250000
SE260000
SE050000
g
H
Gp
Q
P
Y
X
y
. $ , #
:
`
h q
i Ir Rz Z 9
8
7x
( ) _ '
< * % @
SE330000
DEL
SC050000
¥
SM060000
!
[
SD150000
]
^
|
SM150000
_
QR Code Special-Function Parameters [BCOCA-4-598]




Byte 7 Version of symbol
Note: A desired symbol size is specified by the version parameter (byte 7), but the actual size
of the symbol depends on the amount of data to be encoded. If not enough data is
supplied, the symbol will be padded with null data to reach the requested symbol size. If
too much data is supplied for the requested symbol size, the behavior depends on the
value of the too much data flag (bit 2) in the control flags (byte 5):
• If B'0', the symbol will be bigger than requested and will be the smallest symbol that [BCOCA-4-599]
can accommodate that amount of data.
• If B'1', exception EC-0F16 exists. [BCOCA-4-600]
This parameter specifies the desired size of the symbol; each version specifies a particular
number of modules per row and column. The size of each square module is specified by the
module width parameter (byte 17 in the BSD). The following table lists the complete set of
supported versions. Exception condition EC-0F0F exists if an invalid version value is
specified.
### Table 32. Supported Versions for a QR Code Symbol

| Version | Symbol Size | Version | Symbol Size |
| :--- | :--- | :--- | :--- |
| 0 (X'00') | smallest | 21 (X'15') | 101x101 [BCOCA-4-601]|
| 1 (X'01') | 21x21 | 22 (X'16') | 105x105 [BCOCA-4-602]|
| 2 (X'02') | 25x25 | 23 (X'17') | 109x109 [BCOCA-4-603]|
| 3 (X'03') | 29x29 | 24 (X'18') | 113x113 [BCOCA-4-604]|
| 4 (X'04') | 33x33 | 25 (X'19') | 117x117 [BCOCA-4-605]|
| 5 (X'05') | 37x37 | 26 (X'1A') | 121x121 [BCOCA-4-606]|
| 6 (X'06') | 41x41 | 27 (X'1B') | 125x125 [BCOCA-4-607]|
| 7 (X'07') | 45x45 | 28 (X'1C') | 129x129 [BCOCA-4-608]|
| 8 (X'08') | 49x49 | 29 (X'1D') | 133x133 [BCOCA-4-609]|
| 9 (X'09') | 53x53 | 30 (X'1E') | 137x137 [BCOCA-4-610]|
| 10 (X'0A') | 57x57 | 31 (X'1F') | 141x141 [BCOCA-4-611]|
| 11 (X'0B') | 61x61 | 32 (X'20') | 145x145 [BCOCA-4-612]|
| 12 (X'0C') | 65x65 | 33 (X'21') | 149x149 [BCOCA-4-613]|
| 13 (X'0D') | 69x69 | 34 (X'22') | 153x153 [BCOCA-4-614]|
| 14 (X'0E') | 73x73 | 35 (X'23') | 157x157 [BCOCA-4-615]|
| 15 (X'0F') | 77x77 | 36 (X'24') | 161x161 [BCOCA-4-616]|
| 16 (X'10') | 81x81 | 37 (X'25') | 165x165 [BCOCA-4-617]|
| 17 (X'11') | 85x85 | 38 (X'26') | 169x169 [BCOCA-4-618]|
| 18 (X'12') | 89x89 | 39 (X'27') | 173x173 [BCOCA-4-619]|
| 19 (X'13') | 93x93 | 40 (X'28') | 177x177 [BCOCA-4-620]|
| 20 (X'14') | 97x97 [BCOCA-4-621]| | |
If X'00' is specified for this parameter, an appropriate row/column size will be used based on
the amount of symbol data; the smallest symbol that can accommodate the amount of data is
produced.
QR Code Special-Function Parameters [BCOCA-4-622]




Byte 8 Level of error correction
This parameter specifies the level of error correction to be used for the symbol. Each higher
level of error correction causes more error correction codewords to be added to the symbol
and therefore leaves fewer codewords for symbol data. Refer to the QR Code symbology
specification for more information about how many codewords are available for symbol data
for each version and error-correction level combination.
Four different levels of Reed-Solomon error correction can be selected:
Level L (X'00') allows recovery of 7% of symbol codewords
Level M (X'01') allows recovery of 15% of symbol codewords
Level Q (X'02') allows recovery of 25% of symbol codewords
Level H (X'03') allows recovery of 30% of symbol codewords
Exception condition EC-0F10 exists if an invalid level-of-error-correction value is specified.
Byte 9 Structured append sequence indicator
Multiple QR Code bar code symbols (called structured appends) can be logically linked
together to encode large amounts of data. The logically linked symbols can be presented on
the same or on different physical media, and are logically recombined after they are scanned.
From 2 to 16 QR Code symbols can be linked. This parameter specifies where this symbol is
logically linked (1-16) in a sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. Exception
condition EC-0F01 exists if an invalid sequence indicator value is specified. Exception
condition EC-0F02 exists if the sequence indicator is larger than the total number of symbols
(byte 10).
Byte 10 T otal number of structured-append symbols
This parameter specifies the total number of symbols (2-16) that is logically linked in a
sequence of symbols.
If X'00' is specified for this parameter, this symbol is not part of a structured append. If this
symbol is not part of a structured append, both bytes 9 and 10 must be X'00', or exception
condition EC-0F03 exists.
Exception condition EC-0F04 exists if an invalid number of symbols is specified.
Byte 11 Structured append parity data
This parameter specifies parity data for a structured append symbol. The parity-data value
must be calculated from the entire message that is broken into structured-append symbols;
the parity-data value should be the same in each of the structured-append symbols.
The parity-data value is obtained by XORing byte by byte the ASCII/JIS values of all the
original input data before division into structured-append symbols.
If this symbol is not a structured append, this parameter is ignored and should be set to X'00'.
QR Code Special-Function Parameters [BCOCA-4-623]




Byte 12 Special-function flags
These flags specify special functions that can be used with a QR Code symbol.
Bit 0 UCC/EAN FNC1 alternate data type identifier
If this flag is B'1', this QR Code symbol will indicate that it conforms to the UCC/EAN
application identifiers standard. In this case, the industry FNC1 flag must be B'0'.
Exception condition EC-0F11 exists if an incompatible combination of these bits is
specified.
Bit 1 Industry FNC1 alternate data type identifier
If this flag is B'1', this QR Code symbol will indicate that it conforms to the specific
industry or application specifications previously agreed with AIM International. In this
case, the UCC/EAN FNC1 flag must be B'0'. Exception condition EC-0F11 exists if an
incompatible combination of these bits is specified.
When this flag is B'1', an application indicator is specified in byte 13.
Bits
2–7
Reserved
Byte 13 Application indicator for Industry FNC1
When the Industry FNC1 flag is B'1', this parameter specifies an application indicator. The
application indicator is a one-byte value that is specified either as an alphabetic value (from
the ASCII set a-z, A-Z) plus 100 or as a two-digit decimal number (between 00 and 99)
represented as a hexadecimal value. For example:
for application indicator “a” (ASCII value X'61'), specify X'C5'
for application indicator “Z” (ASCII value X'5A'), specify X'BE'
for application indicator “00”, specify X'00'
for application indicator “01”, specify X'01'
for application indicator “99”, specify X'63'
When the Industry FNC1 flag is B'0', this parameter is ignored and should be set to X'00'.
Exception condition EC-0F12 exists if an invalid application-indicator value is specified.
QR Code Special-Function Parameters [BCOCA-4-624]




QR Code with Image Special-Function Parameters
The QR Code symbol produced in a QR Code with Image (type=X'20', modifier=X'12') bar code is produced in
the same way as a QR Code symbol produced in a QR Code (type=X'20', modifier=X'02') bar code.
However, in addition, for each QR Code symbol produced, the QR Code with Image bar code can optionally
place one or more images in conjunction with the symbol. The QR Code symbol can be produced either before
or after the images have been placed.
The information necessary to place the images is contained in Image Information Blocks in the QR Code with
Image special-function parameters defined in this section.
In BCOCA, the “image” in a QR Code with Image bar code can be an IO-Image object (IOCA) or an Object
Container presentation object (for example, TIFF , PDF , PNG). Both types of objects will simply be referred to
as “image objects” in this section, and the object area of the image object will be referred to as the “image
object area”, whether the image object is an IO-Image object or an Object Container object. The specific object
to be placed is referenced through a two-byte local ID in the special-function parameters. In the controlling
environment, this local ID is mapped to an image object. The controlling environment defines which types of
Object Container presentation objects can be mapped in this way.
The placement of an image in conjunction with a QR Code symbol is accomplished through a system very
similar to image placement in MO:DCA or IPDS, with presentation spaces, object areas, offsets, extents, a
mapping option, and a reference coordinate system. In this way, the placement and creation of the image
should be familiar to any AFP implementation, even though these concepts are not used in BCOCA itself, other
than this one situation. The Mixed Object Document Content Architecture (MO:DCA) Reference and the
Intelligent Printer Data Stream Reference will therefore be very useful as information on placing image objects.
However, although similar, the image placement is not exactly the same. The main differences:
• There exists a new coordinate system that is unique to the QR Code with Image bar code, the X [BCOCA-4-625]
qr,Yqr
coordinate system. The origin of this coordinate system is exactly the origin of the QR Code symbol, and the
orientation is the orientation of the X bc,Ybc coordinate system.
T o be clear, this is the origin of the QR Code symbol itself—not the bar code presentation space, or bar code
object area, but the actual symbol, as presented. Thus, (x
qr=0,yqr=0) is the position of the upper-left corner of
the presented QR Code symbol.
• The origin of the object area of the image to be placed in conjunction with the QR Code symbol is specified in [BCOCA-4-626]
the Xqr,Yqr coordinate system. In this way, the image’s object area is directly related to the location of the QR
Code symbol.
• The X qr,Yqr coordinate system has an aspect that other AFP coordinate systems do not have: it has, in [BCOCA-4-627]
addition to an origin at (0,0), a special point, called LR qr, which is the lower-right corner of the QR Code
symbol, as presented. Due to the unpredictability of the exact size of a presented QR Code symbol (due to
different pixel sizes, for example), the true LR
qr point is only known by the receiver of the BCOCA, once the
symbol has been built.
The LRqr point can be used when placing the image, by specifying either an offset or extent for the image
object area that is based on a percentage of the coordinates of LR qr. As an example, the image object area
can be defined to have an X oa extent that is 50% of the X coordinate of LR qr. A few interesting examples:
– Specifying an image object area offset of (40%,40%), an image object area extent of (20%,20%), and an
image object area orientation of 0 places the image exactly in the center of the QR Code symbol, at a width
and height of 20% of the width and height of the QR Code symbol.
– Specifying an image object area offset of (50%,0%), an image object area extent of (25%,50%), and an
image object area orientation of 0 places the image in the left half of the upper-right quadrant of the QR
Code symbol.
– Specifying an image object area offset of (–25%,–25%), an image object area extent of (150%,150%), and
an image object area orientation of 0 centers the image “around” the QR Code symbol, extending out in all
QR Code with Image Special-Function Parameters [BCOCA-4-628]




directions a distance of 25% of the width and height of the QR Code symbol. Presumably, if the image is
presented after the QR Code symbol, the image will incorporate some masking functionality to avoid
overwriting the entire QR Code symbol.
– Specifying an image object area offset of (60%,40%), an image object area extent of (20%,20%), and an
image object area orientation of 90, places the 90-degree rotated image exactly in the center of the QR
Code symbol, at a width and height of 20% of the width and height of the QR Code symbol. In other words,
the image would appear in exactly the same space as the first example above, but rotated 90 degrees.
It is important to realize that the existence of the LR
qr point does not mean that the image must be placed
within the confines of the QR Code symbol, as seen in the third example just above.
Also note that although the LR qr point can be used to specify offsets and extents in percentages, the offsets
and extents of the image object area can alternatively be specified in L-units, in which case, the LR qr point is
not considered.
Figures 14–17 illustrate the concepts involved in QR Code with Image. Figure 14 first shows the image that
will be placed in conjunction with the QR Code symbol in the other figures. Figure 15 shows
both the X
qr,Yqr coordinate system and the image object area, along with the specific bytes in the BSD, BSA,
and these QR Code with Image special-function parameters that define them. Figure 16 and
Figure 17 show the same QR Code and image used in Figure 15, but with the
image oriented at 90° and 45°, respectively; in order that the image still be centered on the QR Code symbol,
the image object area origin must be adjusted, as shown in the figures.
• Many of the parameters for presenting an image are contained in the QR Code with Image special-function [BCOCA-4-629]
parameters described in this section. However, not all image parameters specifiable in the controlling
environments are specifiable here. Such parameters, when they exist in the controlling environment, must be
used, with values from the image object in the controlling environment, when presenting the image object.
Note, however, that regarding color management, there are special mechanisms in the controlling
environment to associate specific, potentially different, color management resources (CMRs) to each image
presented in conjunction with the QR Code symbol. In addition, CMRs that have been associated to the QR
Code with Image bar code itself are also considered associated to all images presented in conjunction with
the QR Code symbol. The former method takes precedence over the latter.
Figure 14. For use in the figures following, this is the image to be placed in conjunction with the QR Code
symbol. The image presentation space size is defined in the image itself and is not affected by any of the fields
in the bar code object. Note that part of this specific image is a surrounding area of white; such a surrounding
area is not required in images used in a QR Code with Image bar code.
Image Presentation Space
Image Presentation Space
X Extent
Image Presentation Space
Y Extent
+X
+Y
QR Code with Image Special-Function Parameters [BCOCA-4-630]




Figure 15. The X qr,Yqr coordinate system and Image Object Area
Bar Code
Presentation
Space Origin
Bar Code Symbol Origin
(X Offset: BSA bytes 1-2 and BSD bytes 0, 2-3
Y Offset: BSA bytes 3-4 and BSD bytes 0, 4-5)
X Extent of Bar Codebc
Presentation Space
(BSD bytes 0, 2-3, 6-7)
Y Extent ofbc
Bar Code
Presentation
Space (BSD
bytes 0, 4-5,
8-9)
+Ybc
+Xbc
+Xqr-Xqr
+Yqr
-Yqr
LRqr
+Xqr-Xqr
+Yqr
-Yqr
LRqr
Image Object Area Origin = (42%,42%)
(Special-Function Parameters
bytes +5-+11)
Image Object Area
Image Object Area X Extent = 16%
(Special-Function Parameters
bytes +15-+17, +18-+19)
Image Object Area Y Extent = 16%
(Special-Function Parameters
bytes +15-+17, +20-+21)
(QR Code shown in grey in expanded view below
to allow easier display of image object area)
+Xoa
+Yoa
QR Code with Image Special-Function Parameters [BCOCA-4-631]




Figure 16. The same QR Code with Image, but with the image rotated 90° in relation to the QR Code symbol.
The image object area origin is adjusted to keep the image centered on the QR Code symbol.
+Xqr-Xqr
+Yqr
-Yqr
LRqr
Image Object Area Origin = (58%,42%)
(Special-Function Parameters
bytes +5-+11)
Image Object Area,
at 90 orientation°
(Special-Function Parameters
bytes +12-+13)
Image Object Area X Extent = 16%
(Special-Function Parameters
bytes +15-+17, +18-+19)
Image Object Area Y Extent = 16%
(Special-Function Parameters
bytes +15-+17, +20-+21)
+Xoa
+Yoa
Figure 17. The same QR Code with Image, but with the image rotated 45° in relation to the QR Code symbol.
The image object area origin is adjusted to keep the image centered on the QR Code symbol.
+Xqr-Xqr
+Yqr
-Yqr
LRqr
Image Object Area Origin = (50%,38.7%)
(Special-Function Parameters
bytes +5-+11)
Image Object Area,
at 45 orientation°
(Special-Function Parameters
bytes +12-+13)
Image Object Area X Extent = 16%
(Special-Function Parameters
bytes +15-+17, +18-+19)
Image Object Area Y Extent = 16%
(Special-Function Parameters
bytes +15-+17, +20-+21)
+Xoa+Yoa
QR Code with Image Special-Function Parameters [BCOCA-4-632]




### Table 33. QR Code with Image Special-Function Parameters

| Offset | Type | Name | Range | Meaning | BCD1 Range | BCD2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bytes 5–13 are the same as bytes 5–13 in the QR Code Special-Function Parameters, except for the “BCD2 Range” column, which in this table is always “Not supported in BCD2”** [BCOCA-4-633]| | | | | | |
| 5 | BITS | Control flags | | | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-634]|
| | bit 0 | EBCDIC | B'0'<br>B'1' | EBCDIC-to-ASCII translation:<br>Do not translate<br>Convert data to ASCII [BCOCA-4-635]| | |
| | bit 1 | Escape sequence handling | B'0'<br>B'1' | Escape-sequence handling:<br>Process escape sequences<br>Ignore all escape sequences [BCOCA-4-636]| | |
| | bit 2 | Too much data | B'0'<br>B'1' | If too much data:<br>Use a bigger QR Code symbol version<br>Exception EC-0F16 exists [BCOCA-4-637]| | |
| | bits 3–7 | | B'00000' | Reserved [BCOCA-4-638]| | |
| 6 | CODE | Conversion | X'00'<br>X'01'–X'03'<br>X'04'–X'09' | No conversion specified<br>SBCS EBCDIC code page used to encode data:<br>X'01': Code page 500 (International #5)<br>X'02': Code page 290 (Japanese Katakana Ext.)<br>X'03': Code page 1027 (Japanese Latin Extended)<br>AFP Line Data SOSI-data conversion:<br>X'04'–X'09' | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-639]|
| 7 | CODE | Version | X'00'<br>X'01'–X'28' | Version of symbol:<br>Smallest symbol<br>Version number (1 to 40) | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-640]|
| 8 | CODE | Error correction level | X'00'<br>X'01'<br>X'02'<br>X'03' | Level of error correction:<br>Level L (7% recovery)<br>Level M (15% recovery)<br>Level Q (25% recovery)<br>Level H (30% recovery) | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-641]|
| 9 | UBIN | Sequence indicator | X'00'–X'10' | Structured append sequence indicator | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-642]|
| 10 | UBIN | Total symbols | X'00' or X'02'–X'10' | Total number of structured-append symbols | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-643]|
| 11 | UBIN | Parity Data | X'00'–X'FF' | Structured append parity data | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-644]|
| 12 | BITS | Special-function flags [BCOCA-4-645]| | | | |
| | bit 0 | UCC/EAN FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to UCC/EAN standards | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-646]|
| | bit 1 | Industry FNC1 | B'0'<br>B'1' | Alternate data type identifier:<br>User-defined symbol<br>Symbol conforms to industry standards | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-647]|
| | bits 2–7 | | B'000000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-648]|
| 13 | CODE | Application indicator | See field description | Application indicator for Industry FNC1 | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-649]|
| 14 | BITS | QR Code with Image special-function flags [BCOCA-4-650]| | | | |
| | bit 0 | Presentation order | B'0'<br>B'1' | Present QR Code symbol first<br>Present images first | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-651]|
| | bit 1 | Present only images | B'0'<br>B'1' | Whether to present only the images:<br>Present both the QR Code symbol and the images<br>Present only the images | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-652]|
| | bits 2–7 | | B'000000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-653]|
| 15–16 | UBIN | RepGrps Length | X'0000'<br>X'0017'–X'7000' | Total length of all repeating groups that follow | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-654]|
| **Zero or more Image Information Blocks in the following format:** [BCOCA-4-655]| | | | | | |
| +0 | UBIN | ImgInfo Length | X'16'–X'FF' | Length of the image information that follows | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-656]|
| +1–2 | | | X'0000' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-657]|
| +3–4 | CODE | Image local ID | X'0000'–X'7FFF' | Local ID of the image object to be used | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-658]|
| +5 | CODE | Offset unit base | X'00'<br>X'01'<br>X'64' | Unit base for offset:<br>Ten inches<br>Ten centimeters<br>One percent | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-659]|
| +6–7 | UBIN | Offset UPUB | X'0001'–X'7FFF' | Units per unit base for offset | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-660]|
| +8–9 | SBIN | X offset | X'8000'–X'7FFF' | X coordinate of the image object area origin | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-661]|
| +10–11 | SBIN | Y offset | X'8000'–X'7FFF' | Y coordinate of the image object area origin | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-662]|
| +12–13 | CODE | Image object area orientation [BCOCA-4-663]| | | | |
| | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-664]|
| | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-665]|
| | bit 15 | | B'0' | Reserved | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-666]|
| +14 | CODE | Coordinate system | X'F0' | Reference coordinate system:<br>QR Code symbol $X_{qr}, Y_{qr}$ | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-667]|
| +15 | CODE | Extent unit base | X'00'<br>X'01'<br>X'64' | Unit base for extent:<br>Ten inches<br>Ten centimeters<br>One percent | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-668]|
| +16–17 | UBIN | Extent UPUB | X'0001'–X'7FFF' | Units per unit base for extent | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-669]|
| +18–19 | UBIN | X extent | X'0001'–X'7FFF' | X extent of the image object area | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-670]|
| +20–21 | UBIN | Y extent | X'0001'–X'7FFF' | Y extent of the image object area | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-671]|
| +22 | CODE | Mapping option | X'10'<br>X'20'<br>X'30'<br>X'60' | Mapping control option:<br>Scale to fit<br>Center and trim<br>Position and trim<br>Scale to fill | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-672]|
| +23 to end of block | | | | Data without current architectural definition | Not supported in BCD1 | Not supported in BCD2 [BCOCA-4-673]|



### Table 34. Valid Code Pages and Type Styles

| Type | Bar Code Symbology | CPGID | FGID (see note 1) [BCOCA-4-674]|
| :--- | :--- | :--- | :--- |
| X'01' | Code 39 (3-of-9 Code), AIM USS-39 | 500 | Device specific [BCOCA-4-675]|
| X'02' | MSI (modified Plessey code) | 500 | Device specific [BCOCA-4-676]|
| X'03' | UPC/CGPC – Version A | 893 | 3 (OCR-B) [BCOCA-4-677]|
| X'05' | UPC/CGPC – Version E | 893 | 3 (OCR-B) [BCOCA-4-678]|
| X'06' | UPC – Two-Digit Supplemental (Periodicals) | 893 | 3 (OCR-B) [BCOCA-4-679]|
| X'07' | UPC – Five-Digit Supplemental (Paperbacks) | 893 | 3 (OCR-B) [BCOCA-4-680]|
| X'08' | EAN-8 (includes JAN-short) | 893 | 3 (OCR-B) [BCOCA-4-681]|
| X'09' | EAN-13 (includes JAN-standard) | 893 | 3 (OCR-B) [BCOCA-4-682]|
| X'0A' | Industrial 2-of-5 | 500 | Device specific [BCOCA-4-683]|
| X'0B' | Matrix 2-of-5 | 500 | Device specific [BCOCA-4-684]|
| X'0C' | Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 | 500 | Device specific [BCOCA-4-685]|
| X'0D' | Codabar, 2-of-7, AIM USS-Codabar | 500 | Device specific [BCOCA-4-686]|
| X'11' | Code 128, GS1-128, Intelligent Mail Container Barcode, Intelligent Mail Package Barcode, UCC/EAN 128, AIM USS-128 | 1303 (see note 2) | Device specific [BCOCA-4-687]|
| X'16' | EAN Two-Digit Supplemental | 893 | 3 (OCR-B) [BCOCA-4-688]|
| X'17' | EAN Five-Digit Supplemental | 893 | 3 (OCR-B) [BCOCA-4-689]|
| X'18' | POSTNET (deprecated) and PLANET (deprecated) | 500 | None [BCOCA-4-690]|
| X'1A' | RM4SCC and Dutch KIX | 500 | None [BCOCA-4-691]|
| X'1B' | Japan Postal Bar Code | 500 | None [BCOCA-4-692]|
| X'1C' | Data Matrix, GS1 DataMatrix (2D bar code) | Default CPGID=819; code page is selectable within the symbol using ECI protocol | None [BCOCA-4-693]|
| X'1D' | MaxiCode (2D bar code) | Default CPGID=819; code page is selectable within the symbol using ECI protocol | None [BCOCA-4-694]|
| X'1E' | PDF417 (2D bar code) | Default CPGID=437; code page is selectable within the symbol using GLI protocol | None [BCOCA-4-695]|
| X'1F' | Australia Post Bar Code | 500 | Device specific [BCOCA-4-696]|
| X'20' | QR Code, QR Code with Image (2D bar code) | Default CPGID=897; code page is selectable within the symbol using ECI protocol | None [BCOCA-4-697]|
| X'21' | Code 93 | 500 | Device specific [BCOCA-4-698]|
| X'22' | Intelligent Mail Barcode | 500 | Device specific [BCOCA-4-699]|
| X'23' | Royal Mail RED TAG (deprecated) | 500 | None [BCOCA-4-700]|
| X'24' | GS1 DataBar | 1303 | Device specific [BCOCA-4-701]|
| X'25' | Royal Mail Mailmark | 500 | None [BCOCA-4-702]|
| X'26' | Aztec Code (2D bar code) | Default CPGID=819; code page is selectable within the symbol using ECI protocol | None [BCOCA-4-703]|
| X'27' | Han Xin Code (2D bar code) | Default CPGID=819; code page is selectable within the symbol using ECI protocol | None [BCOCA-4-704]|

**Notes:**
1. Some symbologies allow a variety of FGIDs, but individual printers restrict the choice; when “Device specific” is specified in the FGID column, refer to printer documentation for information about supported FGIDs. [BCOCA-4-705]
2. For the Intelligent Mail Package Barcode, while the data is encoded using CPGID 1303 as all other Code 128 bar codes, the characters for the USPS Service Banner are encoded using UTF-16BE. [BCOCA-4-706]




Valid Characters and Data Lengths
Table 35lists the valid characters for each symbology and specifies how many characters are allowed for a bar
code symbol. Some bar code symbologies have special rules that identify where in the symbol various
characters are allowed. For example, the UPC/CGPC Version E symbol limits the range of valid values for the
last 5 digits based on the value of the first 5 digits. Refer to the appropriate symbology specification for a full
description of the rules for laying out bar code data; the symbology specifications are listed in Appendix A, “Bar
Code Symbology Specification References”,.
**Table 35. Valid Characters and Data Lengths** [BCOCA-4-707]

| Type | Bar Code Symbology | Valid Characters | Valid Data Length |
| :--- | :--- | :--- | :--- |
| X'01' | Code 39 (3-of-9 Code), AIM USS-39 | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>-.$/+%<br>(space)<br>A total of 43 valid input characters | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-708]|
| X'02' | MSI (modified Plessey code) | 0123456789 | 3 to 15 characters for Modifier X'01'<br>2 to 14 characters for Modifier X'02'<br>1 to 13 characters for all other modifiers [BCOCA-4-709]|
| X'03' | UPC/CGPC - Version A | 0123456789 (see note 1) | 11 characters [BCOCA-4-710]|
| X'05' | UPC/CGPC - Version E | 0123456789 (see note 1) | 10 characters [BCOCA-4-711]|
| X'06' | UPC - Two-Digit Supplemental (Periodicals) | 0123456789 | 2 characters for Modifier X'00'<br>13 characters for Modifier X'01'<br>12 characters for Modifier X'02' [BCOCA-4-712]|
| X'07' | UPC - Five-Digit Supplemental (Paperbacks) | 0123456789 | 5 characters for Modifier X'00'<br>16 characters for Modifier X'01'<br>15 characters for Modifier X'02' [BCOCA-4-713]|
| X'08' | EAN-8 (includes JAN-short) | 0123456789 (see note 1) | 7 characters [BCOCA-4-714]|
| X'09' | EAN-13 (includes JAN-standard) | 0123456789 (see note 1) | 12 characters [BCOCA-4-715]|
| X'0A' | Industrial 2-of-5 | 0123456789 | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-716]|
| X'0B' | Matrix 2-of-5 | 0123456789 | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-717]|
| X'0C' | Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 | 0123456789 | Interleaved 2-of-5 symbology: unlimited<br>ITF-14 symbology: 13 digits<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-718]|
| X'0D' | Codabar, 2-of-7, AIM USS-Codabar | 0123456789<br>-$:/.+ABCD<br>16 characters plus 4 start/stop characters (ABCD) (see note 3) | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-719]|
| X'11' | Code 128, AIM USS-128 (modifier X'02') | All characters defined in the Code 128 code page (see page 160) | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-720]|
| | UCC/EAN 128 (modifier X'03') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>FNC1 (X'8F') | Maximum of 48 characters (see note 4) [BCOCA-4-721]|
| | UCC/EAN 128, GS1-128 (modifier X'04') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>FNC1 (X'8F') | Maximum of 48 characters (see note 4) [BCOCA-4-722]|
| | Intelligent Mail Container Barcode (modifier X'05') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>-<br>FNC1 (X'8F')<br>Some fields restrict the range of characters; refer to the modifier X'05' description in Table 14. | 22 characters [BCOCA-4-723]|
| | Intelligent Mail Package Barcode (modifier X'06') | 0123456789<br>FNC1 (X'8F') | 22, 26, 30, or 34 characters [BCOCA-4-724]|
| X'16' | EAN Two-Digit Supplemental | 0123456789 | 2 characters for Modifier X'00'<br>14 characters for Modifier X'01' [BCOCA-4-725]|
| X'17' | EAN Five-Digit Supplemental | 0123456789 | 5 characters for Modifier X'00'<br>17 characters for Modifier X'01' [BCOCA-4-726]|
| X'18' | POSTNET (deprecated) and PLANET (deprecated) | 0123456789 | 5 characters for Modifier X'00'<br>9 characters for Modifier X'01'<br>11 characters for Modifier X'02'<br>11 characters for Modifier X'04'<br>BCOCA range for Modifier X'03': 0 to 50 characters (see note 2) [BCOCA-4-727]|
| X'1A' | Royal Mail (RM4SCC, modifier X'00') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-728]|
| | Royal Mail (Dutch KIX variation, modifier X'01') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz | Symbology: maximum of 18 characters<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-729]|
| X'1B' | Japan Postal Bar Code (Modifier X'00') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>- | Symbology: 7 or more<br>BCOCA range: 7 to 50 characters (see note 2) [BCOCA-4-730]|
| | Japan Postal Bar Code (Modifier X'01') | 0123456789<br>CC1,CC2,CC3,CC4,<br>CC5,CC6,CC7,CC8<br>-<br>start, stop | No length checking done; refer to the modifier X'01' description [BCOCA-4-731]|
| X'1C' | Data Matrix, GS1 DataMatrix | Any one-byte character or binary data | Symbology: up to 3116 depending on whether the data is character or numeric; refer to the symbology specification<br>BCOCA range: 0 to 3116 characters (see note 2) [BCOCA-4-732]|
| X'1D' | MaxiCode | Any one-byte character allowed by the symbol mode; refer to the description of symbol modes | Symbology: up to 93 alphanumeric characters per symbol depending on encoding overhead or up to 138 numeric characters per symbol; refer to the symbology specification<br>BCOCA range: 0 to 138 characters [BCOCA-4-733]|
| X'1E' | PDF417 | Any one-byte character or binary data | Symbology: up to 1850 text characters, 2710 ASCII numeric digits, or 1108 bytes of binary data per symbol depending on the security level; refer to the symbology specification<br>BCOCA range: 0 to 2710 characters [BCOCA-4-734]|
| X'1F' | Australia Post Bar Code – refer to the modifier (byte 13) description in “Australia Post Bar Code (modifier values X'01' through X'08')” for information about valid characters in specific parts of the symbol [BCOCA-4-735]| | |
| | Modifier X'01' – Standard Customer Barcode | 0123456789 | 8 digits [BCOCA-4-736]|
| | Modifier X'02' – Customer Barcode 2 using Table N | 0123456789 | 8–16 digits [BCOCA-4-737]|
| | Modifier X'03' – Customer Barcode 2 using Table C | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>(space)<br># | 8–13 characters [BCOCA-4-738]|
| | Modifier X'04' – Customer Barcode 2 using proprietary encoding | 0123456789 for sorting code<br>0–3 for customer information | 8–24 digits [BCOCA-4-739]|
| | Modifier X'05' – Customer Barcode 3 using Table N | 0123456789 | 8–23 digits [BCOCA-4-740]|
| | Modifier X'06' – Customer Barcode 3 using Table C | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>(space)<br># | 8–18 characters [BCOCA-4-741]|
| | Modifier X'07' – Customer Barcode 3 using proprietary encoding | 0123456789 for sorting code<br>0–3 for customer information | 8–39 digits [BCOCA-4-742]|
| | Modifier X'08' – Reply Paid Barcode | 0123456789 | 8 digits [BCOCA-4-743]|
| X'20' | QR Code, QR Code with Image | Any one-byte character or binary data | Symbology: Up to 7,089 characters depending on the size and type of the data; refer to the symbology specification<br>BCOCA range: 0 to 7,089 characters [BCOCA-4-744]|
| X'21' | Code 93 | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>-.$/+%<br>(space)<br>a (representing Shift 1)<br>b (representing Shift 2)<br>c (representing Shift 3)<br>d (representing Shift 4)<br>A total of 47 valid input characters | Symbology: unlimited<br>BCOCA range: 0 to 50 characters (see note 2) [BCOCA-4-745]|
| X'22' | Intelligent Mail Barcode | 0123456789 | 20 digits for Modifier X'00'<br>25 digits for Modifier X'01'<br>29 digits for Modifier X'02'<br>31 digits for Modifier X'03' [BCOCA-4-746]|
| X'23' | Royal Mail RED TAG (deprecated) | 0123456789 | 21 digits [BCOCA-4-747]|
| X'24' | GS1 DataBar Omnidirectional (Modifier X'00') | 0123456789 | 14 digits [BCOCA-4-748]|
| | Truncated (Modifier X'01') | 0123456789 | 14 digits [BCOCA-4-749]|
| | Stacked (Modifier X'02') | 0123456789 | 14 digits [BCOCA-4-750]|
| | Stacked Omnidirectional (Modifier X'03') | 0123456789 | 14 digits [BCOCA-4-751]|
| | Limited (Modifier X'04') | 0123456789<br>The first digit must be 0 or 1. | 14 digits [BCOCA-4-752]|
| | Expanded (Modifier X'11') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>!"%&'()*+,-./:;<=>?_<br>FNC1 (X'8F') | up to 74 digits or up to 41 alphabetic characters [BCOCA-4-753]|
| | Expanded Stacked (Modifiers X'12' through X'1B') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>abcdefghijklm<br>nopqrstuvwxyz<br>!"%&'()*+,-./:;<=>?_<br>FNC1 (X'8F') | up to 74 digits or up to 41 alphabetic characters [BCOCA-4-754]|
| X'25' | Royal Mail Mailmark Barcode C (Modifier X'00') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>(space) | 22 characters [BCOCA-4-755]|
| | Barcode L (Modifier X'01') | 0123456789<br>ABCDEFGHIJKLM<br>NOPQRSTUVWXYZ<br>(space) | 26 characters [BCOCA-4-756]|
| X'26' | Aztec Code | Any one-byte character or binary data | Symbology: Up to approximately 3784 text characters, 4729 ASCII numeric digits, or 2360 bytes of binary data per symbol, using the 5% minimum error-correction level; refer to the symbology specification<br>BCOCA range: 0 to 4729 characters [BCOCA-4-757]|
| X'27' | Han Xin Code | Any character—including special processing for Chinese and Unicode characters—or binary data | Symbology: Up to 4350 ASCII characters, 7827 ASCII numeric digits, 2174 common Chinese characters, 1739 2-byte Chinese characters, 1044 4-byte Chinese characters, or 3261 bytes of binary data per symbol, using the 8% minimum error-correction level; refer to the symbology specification<br>BCOCA range: 0 to 7827 characters [BCOCA-4-758]|
Notes:
1. The data for the UPC and EAN symbologies is numeric and of a fixed length, but not all numbers of the [BCOCA-4-759]
appropriate length are valid. This is because the coding scheme is designed to uniquely identify both a
product and its manufacturer. The first part of the symbol represents the manufacturer and is defined in the
symbology specification (not all numbers are valid in this part of the symbol). The second part of the
Valid Characters and Data Lengths [BCOCA-4-760]




symbol represents a unique product identifier code assigned by the manufacturer. Refer to the description
of GS1 company prefixes in the GS1 General Specifications for more details.
2. All BCOCA receivers must support at least the BCOCA range. Some receivers support a larger data [BCOCA-4-761]
length.
3. Some descriptions of Codabar show the characters “T ,N,*,E” as stop characters (representing the stop [BCOCA-4-762]
characters “A,B,C,D”), but the Codabar symbology actually only allows “A,B,C,D” as start and stop
characters. This alternate representation (“T ,N,*,E”) is used only to distinguish between the start and stop
characters when describing a Codabar symbol; when coding a BCOCA Codabar symbol, start and stop
characters must be represented using A, B, C, or D.
4. A full description of the GS1-128 symbology is available in GS1 General Specifications. This document [BCOCA-4-763]
extends some of the Application Identifiers (AI) defined for UCC/EAN 128 to also allow 20 punctuation
characters – !"%&'()*+,-./:;<=>?_ – for GS1-128 symbols. The document also lists the following symbol size
characteristics for GS1-128 bar codes (but many BCOCA receivers that support modifiers X'03' and X'04'
do not check for or enforce these limits):
1. The maximum number of data characters in a single symbol is 48. [BCOCA-4-764]
2. The maximum physical length of a Code 128 symbol is 165 mm (6.5 inches) including quiet zones. [BCOCA-4-765]
Valid Characters and Data Lengths [BCOCA-4-766]




### Characters and Code Points

The following table (Table 36) is informational and is not a formal part of the BCOCA architecture. The table is intended as a convenient listing of some EBCDIC and ASCII codes points and is not intended to be complete or to show all possible EBCDIC or ASCII encodings for any particular code point. The specific code pages are listed, using CPGIDs, in Table 34. For a formal definition of these codes pages and CPGIDs, refer to the Character Data Representation Architecture listed in Table 5. Note that this table does not necessarily cover all of the code points used for 2D bar codes and does not contain all of the characters available with CPGID = 1303. [BCOCA-4-767]

Table 36. Characters and Code Points Commonly used in the BCOCA Symbologies (Not a Complete Listing) [BCOCA-4-768]

| Character | EBCDIC Code Point | ASCII Code Point |
| :--- | :--- | :--- |
| 0 | X'F0' | X'30' [BCOCA-4-769]|
| 1 | X'F1' | X'31' [BCOCA-4-770]|
| 2 | X'F2' | X'32' [BCOCA-4-771]|
| 3 | X'F3' | X'33' [BCOCA-4-772]|
| 4 | X'F4' | X'34' [BCOCA-4-773]|
| 5 | X'F5' | X'35' [BCOCA-4-774]|
| 6 | X'F6' | X'36' [BCOCA-4-775]|
| 7 | X'F7' | X'37' [BCOCA-4-776]|
| 8 | X'F8' | X'38' [BCOCA-4-777]|
| 9 | X'F9' | X'39' [BCOCA-4-778]|
| A | X'C1' | X'41' [BCOCA-4-779]|
| B | X'C2' | X'42' [BCOCA-4-780]|
| C | X'C3' | X'43' [BCOCA-4-781]|
| D | X'C4' | X'44' [BCOCA-4-782]|
| E | X'C5' | X'45' [BCOCA-4-783]|
| F | X'C6' | X'46' [BCOCA-4-784]|
| G | X'C7' | X'47' [BCOCA-4-785]|
| H | X'C8' | X'48' [BCOCA-4-786]|
| I | X'C9' | X'49' [BCOCA-4-787]|
| J | X'D1' | X'4A' [BCOCA-4-788]|
| K | X'D2' | X'4B' [BCOCA-4-789]|
| L | X'D3' | X'4C' [BCOCA-4-790]|
| M | X'D4' | X'4D' [BCOCA-4-791]|
| N | X'D5' | X'4E' [BCOCA-4-792]|
| O | X'D6' | X'4F' [BCOCA-4-793]|
| P | X'D7' | X'50' [BCOCA-4-794]|
| Q | X'D8' | X'51' [BCOCA-4-795]|
| R | X'D9' | X'52' [BCOCA-4-796]|
| S | X'E2' | X'53' [BCOCA-4-797]|
| T | X'E3' | X'54' [BCOCA-4-798]|
| U | X'E4' | X'55' [BCOCA-4-799]|
| V | X'E5' | X'56' [BCOCA-4-800]|
| W | X'E6' | X'57' [BCOCA-4-801]|
| X | X'E7' | X'58' [BCOCA-4-802]|
| Y | X'E8' | X'59' [BCOCA-4-803]|
| Z | X'E9' | X'5A' [BCOCA-4-804]|
| a | X'81' | X'61' [BCOCA-4-805]|
| b | X'82' | X'62' [BCOCA-4-806]|
| c | X'83' | X'63' [BCOCA-4-807]|
| d | X'84' | X'64' [BCOCA-4-808]|
| e | X'85' | X'65' [BCOCA-4-809]|
| f | X'86' | X'66' [BCOCA-4-810]|
| g | X'87' | X'67' [BCOCA-4-811]|
| h | X'88' | X'68' [BCOCA-4-812]|
| i | X'89' | X'69' [BCOCA-4-813]|
| j | X'91' | X'6A' [BCOCA-4-814]|
| k | X'92' | X'6B' [BCOCA-4-815]|
| l | X'93' | X'6C' [BCOCA-4-816]|
| m | X'94' | X'6D' [BCOCA-4-817]|
| n | X'95' | X'6E' [BCOCA-4-818]|
| o | X'96' | X'6F' [BCOCA-4-819]|
| p | X'97' | X'70' [BCOCA-4-820]|
| q | X'98' | X'71' [BCOCA-4-821]|
| r | X'99' | X'72' [BCOCA-4-822]|
| s | X'A2' | X'73' [BCOCA-4-823]|
| t | X'A3' | X'74' [BCOCA-4-824]|
| u | X'A4' | X'75' [BCOCA-4-825]|
| v | X'A5' | X'76' [BCOCA-4-826]|
| w | X'A6' | X'77' [BCOCA-4-827]|
| x | X'A7' | X'78' [BCOCA-4-828]|
| y | X'A8' | X'79' [BCOCA-4-829]|
| z | X'A9' | X'7A' [BCOCA-4-830]|
| - | X'60' | X'2D' [BCOCA-4-831]|
| # | X'7B' | X'23' [BCOCA-4-832]|
| . | X'4B' | X'2E' [BCOCA-4-833]|
| $ | X'5B' | X'24' [BCOCA-4-834]|
| / | X'61' | X'2F' [BCOCA-4-835]|
| + | X'4E' | X'2B' [BCOCA-4-836]|
| % | X'6C' | X'25' [BCOCA-4-837]|
| : | X'7A' | X'3A' [BCOCA-4-838]|
| ! | X'4F' for CPGID = 500<br>X'4F' for CPGID = 893<br>X'5A' for CPGID = 1303 | X'21' [BCOCA-4-839]|
| " | X'7F' | X'22' [BCOCA-4-840]|
| & | X'50' | X'26' [BCOCA-4-841]|
| ' | X'7D' | X'27' [BCOCA-4-842]|
| ( | X'4D' | X'28' [BCOCA-4-843]|
| ) | X'5D' | X'29' [BCOCA-4-844]|
| [ | X'4A' | X'5B' [BCOCA-4-845]|
| * | X'5C' | X'2A' [BCOCA-4-846]|
| , | X'6B' | X'2C' [BCOCA-4-847]|
| ; | X'5E' | X'3B' [BCOCA-4-848]|
| < | X'4C' | X'3C' [BCOCA-4-849]|
| = | X'7E' | X'3D' [BCOCA-4-850]|
| > | X'6E' | X'3E' [BCOCA-4-851]|
| ? | X'6F' | X'3F' [BCOCA-4-852]|
| _ | X'6D' | X'5F' [BCOCA-4-853]|
| Space | X'40' | X'20' [BCOCA-4-854]|
| FNC1 | X'8F' for CPGID = 1303 [BCOCA-4-855]| |
| RS (record separator) | X'1E' | X'1E' [BCOCA-4-856]|
| GS (group separator) | X'1D' | X'1D' [BCOCA-4-857]|
| US (unit separator) | X'1F' | X'1F' [BCOCA-4-858]|
| EOT (end of transmission) | X'37' | X'04' [BCOCA-4-859]|




Code 128 Code Page
The Code 128 code page (CPGID = 1303, GCSGID = 1454) is defined as shown in Figure 18. This code page
is used for all Code 128 symbols (Code 128, GS1-128, UCC/EAN 128, AIM USS-128, Intelligent Mail
Container Barcode, Intelligent Mail Package Barcode) and GS1 DataBar symbols.
Figure 18. Code 128 Code Page (CPGID = 1303, GCSGID = 1454)
Hex Digits
1st
2nd
-0
-1
-3
-2
-4
-5
-6
0- 5- 6-1- 7-2- 8-3- 4- 9- A- B- C- D- E- F- [BCOCA-4-860]
SE030000
SE040000
SE100000
SE090000 SE240000
SE110000
SE190000
SE200000
SE350000 SE230000 LB010000 LB020000
LC010000 LC020000
LD010000 LD020000
LE010000 LE020000
LF010000 LF020000
LK010000 LK020000LS010000 LS020000
LL010000 LL020000LT010000 LT020000
LM010000 LM020000LU010000 LU020000
LN010000 LN020000LV010000 LV020000
LO010000 LO020000LW010000 LW020000 ND060000
ND050000
ND040000
ND030000
ND020000
ND010000
SD150000 ND100000
SP120000 LA010000 LA020000LJ010000 LJ020000
SP010000
(SP)
SM030000
& _ ^ { }
SP100000 SM110000 SM140000 SM070000
SD190000SE020000 SE180000
SE010000 SE170000
\DLENUL
SOH
STX
ETX
HT
BS
VT
FF
CR
SO
SI
GS
RS
US
FN4
ENQ
ACK
BEL SUB FN1 DEL
NAK
DC4
ESC
CAN
EM
EOT
ETB
LF
DC3
DC2 FS SYN
DC1 / a Aj J
b B
c C
d D
Ee E
f F
k Ks S
l Lt T
m Mu U
Nn Nv V
o Ow W 6
5
4
3
2
1
0
-E
-F
SE390000 SE330000
SE420000SA010000 SP140000 SA050000
SO130000 SP150000 SP040000
SA040000
| ? " [BCOCA-4-861]
+ ; > =
-7
-8
-A
-9
-B
-C
-D
SE210000
SP110000 SC030000 SP080000
SP020000 SP130000
SD130000 LI010000 LI020000LR010000 LR020000LZ010000 LZ020000
SE400000
SM080000
SM060000 SE410000
ND090000
ND080000
ND070000LX010000
LH010000 LH020000LQ010000 LQ020000
SA030000 SM040000 SM020000
SP060000 SP070000 SP090000 SP050000
SM050000
SM010000
SE280000 LG010000 LG020000LP010000 LP020000 LX020000
LY010000 LY020000
SE120000
SE130000
SE140000
SE150000
SE360000
SE370000
SE380000SE160000
SE060000
SE070000
SE080000 SE270000
SE220000
SE250000
SE260000
SE050000
g
H
Gp
Q
P
Y
X
y
. $ , #
! :
`
h q
i Ir Rz Z
FN2 FN3
]
[
9
8
7x
( ) _ '
< * % @
˜
Note: All START , STOP , SHIFT , and CODE characters are generated by the printer to produce the shortest bar
code possible from the given data; these characters are not specified in the Bar Code Symbol Data. All
code points not listed in the table are undefined. The code points that do not have graphic character
shapes, such as X'00' (NUL) and X'8F' (FN1), are control codes defined within the Code 128 symbology;
in the HRI, control codes print in a device-dependent manner. The FN1, FN2, FN3, and FN4 characters
are also called FNC1, FNC2, FNC3, and FNC4 in the Code 128 Symbology Specification.
Code 128 Code Page



Copyright © AFP Consortium 1991, 2025 161
