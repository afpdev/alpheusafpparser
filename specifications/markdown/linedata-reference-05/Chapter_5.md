# Chapter 5. Structured Fields in a Page Definition and in Line
Data
This chapter defines the structured fields used in a Page Definition print control object. It also describes special
functions used with certain MO:DCA structured fields when they occur in line-mode or mixed-mode data. Refer
to the Mixed Object Document Content Architecture (MO:DCA) Reference for definitions of structured fields
not included in this manual. Any conflicts arising from definitions in this manual and the Mixed Object
Document Content Architecture (MO:DCA) Reference are resolved by the Mixed Object Document Content
Architecture (MO:DCA) Reference.
Structured Field Format
Structured fields used in Page Definitions are registered in the MO:DCA architecture and follow the MO:DCA
structured field syntax rules. A summary of the syntax rules for Page Definition structured fields follows.
Structured Field Introducer
Length (2B) SF Identifier (3B) Flags (1B) Reserved X'0000' Data
Length A two-byte count field that specifies the length of the structured field. The length value can
range from 8 to 32,767. The count includes the length field itself, the other structured field
introducer parameters, and the structured field data contents, including padding bytes when
present.
SF Identifier A three-byte value that identifies the type of structured field.
Flag A one-byte field that specifies the value of optional structured-field indicators.
Bits 0–3 and 5–7 are not used and should
be set to zero.
Bit 4 of this byte is a padding indicator. If this bit is on, the structured field data includes
padding bytes.
Reserved; X'0000'
The two-byte reserved field is used by some applications to specify a structured field
sequence number so that the structured field can be located more easily in a data stream.
These applications define the sequence-numbering conventions used. Print-services products
do not validate sequence numbering; therefore, applications can use any numbering
convention. This field is reserved in MO:DCA data streams and should be set to zero.
Data Not all structured fields include a data portion. If present, this portion contains specific orders,
parameters, and data appropriate for the given structured field. The length of the data field can
range from 0 to 32,759.
Padding If bit 4 in the flag byte (the padding indicator) is set to B'1', padding bytes follow the data field.
If padding is indicated, the length of the padding is specified in the following manner:
• For 1 or 2 bytes of padding, the length is specified in the last padding byte. [LINEDATA-5-001]
• For 256 to 32,759 bytes of padding, the length is contained in the last three bytes. The last [LINEDATA-5-002]
byte is X'00', and the two preceding bytes specify the padding length.
• For 3 to 255 bytes of padding, the length can be specified by either method. [LINEDATA-5-003]
Note: The length count of the padding data includes the length field itself. [LINEDATA-5-004]


Structured Field Descriptions
The description for each structured field contains the following information:
• Purpose [LINEDATA-5-005]
• Meaning and allowed values of variable parameters [LINEDATA-5-006]
• Contents of constant parameters [LINEDATA-5-007]
Notation Conventions
The bold-faced heading for each structured field contains the following information:
• The three-letter abbreviation [LINEDATA-5-008]
• The three-byte hexadecimal code [LINEDATA-5-009]
• The full name of the structured field [LINEDATA-5-010]
The following conventions apply in the descriptions of the structured fields:
• The byte position of each structured field parameter is given. The first byte of the data field is byte 0. [LINEDATA-5-011]
• If the parameter is a variable, the name and function of the parameter is given. [LINEDATA-5-012]
• Each parameter is specified as either optional (O) or mandatory (M). [LINEDATA-5-013]
• Any valid triplets (see “Structured Field Triplets” for a description) listed in the tables are specified [LINEDATA-5-014]
as either optional or mandatory. The definition of each triplet follows the description of the structured field in
which it appears.
• Any number not preceded by X (hexadecimal) or B (binary) is a decimal number. [LINEDATA-5-015]
• If the parameter is a constant or reserved parameter, only the parameter contents are given; for example, [LINEDATA-5-016]
X'0960' or B'001'. A reserved parameter is one that has no meaning at present, but might in the future.
Reserved bytes should be set to X'00' by data stream generators and should be ignored by data stream
receivers.
• If the same parameter occurs more than once but contains different values, the values in the last parameter [LINEDATA-5-017]
are used.
• The tables specify the type of parameter, where appropriate. The parameter type notations are: [LINEDATA-5-018]
UBIN Unsigned binary integer: a positive integer where the high-order bit may be used as a data
bit.
SBIN Signed binary integer: an integer where the high-order bit is the sign (plus or minus) of the
integer and cannot be used as a data bit.
BITS Bit String: a string consisting solely of bits; binary flags where each binary bit can be
assigned a specific meaning.
CHAR Character encoding: a string of one or more code points for alphanumeric characters. For
example, X'C1C2C3F1' in EBCDIC can represent ABC1.
CODE Architected value: a code assigned to a specific item.
Structured Fields


Structured Field Triplets
Several structured fields contain self-identifying parameters called triplets in their data field. A triplet contains
three components: the triplet length, the triplet identifier, and the triplet values. See Table 12. [LINEDATA-5-019]
### Table 12. Structured Field Triplet Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Triplet Length | 3–254 | Specifies the length of the triplet, including this byte | M [LINEDATA-5-020]|
| 1 | CODE | Triplet Identifier | | Identifies the triplet | M [LINEDATA-5-021]|
| 2–n | | Triplet Data | | Contains the data for this triplet | M [LINEDATA-5-022]|

External Resource Object Naming Conventions
MO:DCA objects can be named using one of the following two formats:
• Token name. This name is specified using a fixed-length 8-byte parameter on Begin, Invoke, Map, and [LINEDATA-5-023]
Include structured fields
• Fully qualified name. This name can be up to 250 bytes long and is specified using the Fully Qualified Name [LINEDATA-5-024]
(FQN) X'02' triplet on Begin, Map, and Include structured fields, as well as on object-processing structured
fields. For names, the FQNFmt parameter on this triplet is set to X'00' to specify a character string format and
the FQNType parameter specifies how the name is used. When a fully qualified name is specified using
FQNType X'01' on a Begin structured field, it overrides any token name that might
have been specified on
the structured field.
MO:DCA object names are encoded using the code page and character set specified in a Coded Graphic
Character Set Global ID (X'01') triplet, except in those cases where the name defines a fixed encoding. The
X'01' triplet can specify the encoding in two forms; use of the Coded Character Set Identifier (CCSID) form is
recommended. For a definition of the X'01' triplet,
see Mixed Object Document Content Architecture (MO:DCA)
Reference.
The X'01' triplet may be specified on most MO:DCA structured fields that contain character data such as an
object name. Careful specification of code page and character set is essential for interchange since the system
defaults for code page and character set might
vary from one system environment to another.
In AFP environments, print servers treat the object name—other than TrueType and OpenType full font name
—as an external resource library member name and attempt to process a resource library member with the
same name. This means that the external names are subject to the system imposed file naming rules.
To ensure portability across all AFP platforms, external object names other than TrueType and OpenType full
font names must be composed according to the following conventions:
• Names consist only of the following characters: A–Z, 0–9, $, #, @. When the object name is specified using [LINEDATA-5-025]
the fixed-length 8-byte token name parameter, a trailing space character (X'40' in the EBCDIC encoding) or a
trailing null code point (X'00') is assumed to terminate the name. [LINEDATA-5-026]


• To ensure portability across older versions of print servers that do not support encoding definitions in the [LINEDATA-5-027]
X'01' triplet, names should use only the recommended characters and be encoded in EBCDIC using code
page 500 and a character set that includes the above-mentioned characters. The preferred character set is
961, which includes only those characters, however character sets such as 697, which contain additional
characters, are also appropriate. With this encoding, the code points for the characters are:
A–I (code points X'C1'–X'C9')
J–R (code points X'D1'–X'D9')
S–Z (code points X'E2'–X'E9')
0–9 (code points X'F1'–X'F9')
$, #, and @ (code points X'5B', X'7B', and X'7C', respectively)
Note that such older print servers normally assume this EBCDIC encoding as the default encoding for the
document. This EBCDIC encoding can be identified with CCSID 500, which represents the combination of
code page 500 and character set 697.
TrueType and OpenType full font names specified in the MDR structured field are not restricted to these
characters and might
be encoded as required by the AFP-generating application. However, since these names
are used to search inline font containers and Resource Access Tables (RAT s) that use a fixed UTF-16BE
encoding for full font names, efficiency is gained if the full font names in the MDR are also encoded in UTF16BE. This avoids an encoding conversion. The UTF-16BE encoding can be identified with CCSID 1200. This
encoding needs to be specified with a X'01' triplet on the MDR that specifies the full font name.
Begin and End Structured Fields
Begin structured fields identify the beginning of an object in a print data stream or of a data stream resource.
All Begin structured fields are used in conjunction with corresponding End structured fields to delimit specific
objects in a document, page, or resource.
When a Begin data object structured field (BPT , BGR, BIM, or BBC) is encountered in a page, an overlay, or a
page segment, the initial data presentation conditions are set from values specified in the data descriptor
structured field for the object, prior to processing the object data. If no initial conditions are specified in the
object data descriptor or if null values are specified, then the initial conditions are set to the system default. If
no system default is defined, the device default is used.
Any triplet parameter encountered on any of the Begin structured fields that is not explicitly defined to be valid
in the structured field definition is ignored. The document presenter assumes that these fields are informational
although they might
have meaning in other applications. [LINEDATA-5-028]


## Begin Data Map (BDM)
The Begin Data Map structured field begins a Data Map resource object. [LINEDATA-5-029]
### BDM (X'D3A8CA') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A8CA', Flags (1B), Reserved X'0000' [LINEDATA-5-030]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DMName | | Name of the Data Map | M [LINEDATA-5-031]|
| 8 | CODE | DatFmt | X'00', X'01', X'02' | Data formatting specified by this Data Map: X'00': LNDs, X'01': RCDs, X'02': XMDs | O [LINEDATA-5-032]|
| 9–n | | Triplets | | See BDM Semantics for triplet applicability | M [LINEDATA-5-033]|

### BDM Semantics
DMName Token name of the Data Map
This is a mandatory parameter because an Invoke Data Map (IDM) structured field selects a
Data Map by specifying its token name.
DatFmt An optional parameter that determines how the Data Map is used to format line data
If this parameter is not specified, the architected default is X'00'.
Value Description
X'00' The Data Map contains LNDs and is used to format traditional line data that may
contain CCs and TRCs.
X'01' The Data Map contains RCDs and is used to format line data that contains record
format IDs and may also contain CCs.
X'02' The Data Map contains XMDs and is used to format XML data that contains start and
end tags.
Triplets Optional triplets
See the following for detailed information about the triplets permitted on the BDM structured
field:
“Encoding Scheme ID (X'50') Triplet”
“Page Count Control (X'7C') Triplet”
“Margin Definition (X'7F') Triplet”
Notes:
1. If a triplet is included on this structured field, the optional positional parameters become mandatory. [LINEDATA-5-034]


2. If one of the Data Maps in a PageDef contains LNDs, then all of the Data Maps in a PageDef must be LND [LINEDATA-5-035]
based.
3. If one of the Data Maps in a PageDef contains RCDs, then all of the Data Maps in a PageDef must be RCD [LINEDATA-5-036]
based and subpages are not used (the Data Map contains only one subpage).
4. If one of the Data Maps in a PageDef contains XMDs, then all of the Data Maps in a PageDef must be XMD [LINEDATA-5-037]
based and subpages are not used (the Data Map contains only one subpage). [LINEDATA-5-038]
### BDM Triplets
#### Encoding Scheme ID (X'50') Triplet
This triplet is an optional triplet when used with DatFmt X'00' (formatting with LNDs) and X'01' (formatting with
RCDs) but it is mandatory when used with DatFmt = X'02' (formatting with XMDs). It is used to specify the
encoding scheme associated with the user data, the XML Name (X'8A') triplet specified on XMDs, the Record
Descriptor ID field specified on RCDs, the Field Delimiter specified on RCDs and XMDs, the Fixed text
specified in the Fixed Data Text (FDX) structured field, and the Comparison String field of the Conditional
Processing Control (CCP) structured fields.
The values supported in the ESidUD field of the Encoding Scheme ID triplet when formatting data with a Page
Definition are:
• X'6100': EBCDIC Presentation SBCS [LINEDATA-5-039]
• X'2100': PC Data SBCS (ASCII) [LINEDATA-5-040]
• X'7807': UTF-8 [LINEDATA-5-041]
• X'7200': UTF-16 [LINEDATA-5-042]
When this triplet is specified for LND or RCD processing, it is used to determine if searching for the Byte Order
Mark (BOM) is necessary. If the triplet specifies UTF-8 or UTF-16, the first bytes (following any CC or TRC) of
the first line or record of the line data are examined to see if the BOM has been placed in the data. If the BOM
is in the data, it is removed so it is not considered part of the user data.
For XMD processing, this triplet is mandatory and the first bytes of the first line or record of the print file are
always examined to see if the BOM is in the data.
For more information about BOM processing, see “Unicode Line Data”.
The Encoding Scheme ID triplet is a MO:DCA triplet. For the formal definition of this triplet, see the Mixed
Object Document Content Architecture (MO:DCA) Reference.
Notes:
1. ESidUD is required for Data Maps that are to print XML data. [LINEDATA-5-043]
2. This triplet may occur once. If this triplet is specified more than once, only the first is used. [LINEDATA-5-044]
3. Each Encoding Scheme ID triplet specified on each BDM structured field of a single Page Definition must [LINEDATA-5-045]
specify the same encoding.
4. This Encoding Scheme ID triplet overrides the encoding specified in the XML data. [LINEDATA-5-046]
5. Except for certain situations in processing XML data with FOCA fonts (see “XML Data”), the [LINEDATA-5-047]
font selected to print the data must match the encoding of the user data specified in this triplet. [LINEDATA-5-048]


#### Page Count Control (X'7C') Triplet
This is an optional triplet that may occur once. If this triplet is specified more than once, only the first is used. It
is used only if DatFmt = X'01' (formatting with RCDs) or X'02' (formatting with XMDs). It is used to specify how
the page count is initialized and maintained for the active Data Map. If this triplet is specified on a Data Map
that contains LNDs, it is ignored. [LINEDATA-5-049]
### Triplet X'7C' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 7 | Length of the triplet, including Tlength | M [LINEDATA-5-050]|
| 1 | CODE | Tid | X'7C' | Identifies the Page Count Control triplet | M [LINEDATA-5-051]|
| 2–3 | CODE | PageNum | X'0001'–X'FFFF' | Initial page number | M [LINEDATA-5-052]|
| 4 | | | 0 | Reserved; should be zero | M [LINEDATA-5-053]|
| 5 | CODE | CountCtr | X'00', X'01', X'02', X'03' | Page count control for Data Map:<br>X'00': Stop<br>X'01': Resume<br>X'02': Continue<br>X'03': Reset | M [LINEDATA-5-054]|
| 6 | BITS | CountFlgs | | Bits that specify additional page count controls. See Triplet X'7C' Semantics for bit definitions. | M [LINEDATA-5-055]|
Triplet X'7C' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Page Count Control triplet
PageNum Initial page number
This field specifies the initial page number to be set into the page count when the page count
control specifies X'01' (Resume) or X'03' (Reset).
CountCtr Page count control
This field specifies how the page count is initialized and maintained for the active Data Map.
Value Description
X'00' Stop
When this Data Map is invoked, the page count is initialized to the last page number
used with the previous Data Map, which is the current page number. If there is no
current page number, it is initialized to the value specified by PageNum. Once the
page count is initialized, it is not incremented for the duration of this Data Map.
X'01' Resume
On the first invocation of this Data Map, the page count is initialized to the value
specified by PageNum. On each successive invocation of this Data Map, the page
count is initialized to the last page number used on the previous invocation of this
Data Map. Once the page count is initialized, it is incremented for every page
presented with this Data Map.
X'02' Continue
When this Data Map is invoked, the page count is initialized to the last page number
used with the previous Data Map, which is the current page number. If there is no [LINEDATA-5-056]


current page number, such as when this is the first Data Map invoked for the job, it is
initialized to the value specified by PageNum. Once the page count is initialized, it is
incremented for every page presented with this Data Map.
X'03' Reset
When this Data Map is invoked, the page count is initialized to the value specified by
PageNum. Once the page count is initialized, it is incremented for every page
presented with this Data Map.
All others
Reserved
CountFlgs This field specifies
additional page count controls.
Bit 0 Count control for MO:DCA pages
Value Description
B'0' Do not count MO:DCA pages that occur in mixed-mode data.
B'1' Count MO:DCA pages that occur in mixed-mode data.
Bit 1 Count control for constant pages; that is, pages that contain no variable data
Such pages are generated when:
• The Formdef specifies “constant form”. [LINEDATA-5-057]
• The Formdef specifies N-up and no variable data is allowed on the page. [LINEDATA-5-058]
Value Description
B'0' Do not count constant pages.
B'1' Count constant pages.
Bits 2–7
Reserved; all bits should be B'0'
If this triplet is not specified, the defaults are CountCtr = X'02' (Continue) and CountFlgs = B'00' (do not count
MO:DCA pages and constant pages that occur in mixed-mode data). [LINEDATA-5-059]


#### Margin Definition (X'7F') Triplet
This is an optional triplet that is used only if DatFmt = X'01' (formatting with RCDs) or X'02' (formatting with
XMDs). This triplet may occur once. If this triplet is specified more than once, only the first is used. It is used to
specify the page margins for the Data Map. These margins are used for logical page eject processing and for
graphics processing. If this triplet is specified on a Data Map that contains LNDs, it is ignored. [LINEDATA-5-060]
### Triplet X'7F' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 14 | Length of the triplet, including Tlength | M [LINEDATA-5-061]|
| 1 | CODE | Tid | X'7F' | Identifies the Margin Definition triplet | M [LINEDATA-5-062]|
| 2–5 | CODE | TxtOrent | X'0000 2D00', X'2D00 5A00', X'5A00 8700', X'8700 0000' | Text (I,B) Orientation:<br>X'0000 2D00': 0, 90 degrees<br>X'2D00 5A00': 90, 180 degrees<br>X'5A00 8700': 180, 270 degrees<br>X'8700 0000': 270, 360 degrees | M [LINEDATA-5-063]|
| 6–7 | UBIN | LeftMar | 0 to page extent minus 1 | Left Margin Offset from page edge | M [LINEDATA-5-064]|
| 8–9 | UBIN | TopMar | 0 to page extent minus 1 | Top Margin Offset from page edge | M [LINEDATA-5-065]|
| 10–11 | UBIN | RightMar | 0 to page extent minus 1 | Right Margin Offset from page edge | M [LINEDATA-5-066]|
| 12–13 | UBIN | BotMar | 0 to page extent minus 1 | Bottom Margin Offset from page edge | M [LINEDATA-5-067]|
Triplet X'7F' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Margin Definition triplet
TxtOrent Text Orientation
This field specifies the text orientation that is used to fix the page margins for this Data Map.
See Figure 29.
The margins are fixed for the Data Map; that is, they do not change when the text orientation
changes in the Data Map. For example, if this parameter specifies a (0,90) degree text
orientation, the top-left diagram in Figure 29 shows how the LeftMar, TopMar,
RightMar, and BotMar parameters define the left, top, right, and bottom margins, respectively.
Once specified, these margins define a bounding box for the Data Map that is indicated by the
dashed lines.
Note that if the text orientation is changed within the Data Map, the same bounding box
applies to the new orientation, but the name of the margins change in the new orientation. For
example, if the new text orientation is (90,180) degrees, as shown in the top-right diagram of
Figure 29, the left margin in the new orientation is actually defined by the TopMar
parameter, the bottom margin is defined by the LeftMar parameter, and so on. Therefore,
when a possible baseline overflow is evaluated in the new orientation, the print position is
actually checked against the LeftMar parameter, which is the bottom margin for that text
orientation.
Similar processing occurs for graphics that are generated by an RCD or XMD. If the text
orientation for the graphics is (90,180) degrees, the bottom margin is defined by the LeftMar
parameter. Therefore, active lines or boxes that are ended at the bottom margin are actually
ended at the margin defined by the LeftMar parameter, which is the bottom margin for that text
orientation.


LeftMar Left Margin
This field specifies the offset of the left margin along the i axis from the left edge of the page.
The left edge of the page is the zero position on the i axis.
TopMar Top Margin
This field specifies the offset of the top margin along the b axis from the top edge of the page.
The top edge of the page is the zero position on the b axis.
RightMar Right Margin
This field s
pecifies the offset of the right margin along the i axis from the right edge of the
page.
BotMar Bottom Margin
This field specifies the offset of the bottom margin along the b axis from the bottom edge of the
page.
If this triplet is not specified, the default is a text orientation of (0,90) degrees and all margin offsets set to
X'0000'.


Figure 29. Relationship of Margin Definition to Text Orientation
Top Margin
Bottom Margin
Left Margin Right Margin
0 , 90
o o
i
b
Top MarginBottom Margin
Left Margin
Right Margin
90 , 180
o o
i
b
Top Margin
Bottom Margin
Left Margin
Right Margin
180 , 270
o o i
b
Top Margin
Bottom Margin
Left Margin
Right Margin
270 , 360
o o
i
b


## Begin Data Map Transmission Subcase (BDX)
The Begin Data Map Transmission Subcase structured field begins a Data Map Transmission Subcase object,
which contains the structured fields used to map lines of data to the page. [LINEDATA-5-068]
### BDX (X'D3A8E3') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A8E3', Flags (1B), Reserved X'0000' [LINEDATA-5-069]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DMXName |  | Name of the Data Map Transmission Subcase | O [LINEDATA-5-070]|

### BDX Semantics
DMXame Token name of the Data Map Transmission Subcase
This is an optional parameter. [LINEDATA-5-071]

## Begin Page Map (BPM)
The Begin Page Map structured field begins a Page Map resource object, also called a Page Definition or
PageDef. A Page Definition is a print control resource object used to compose line data into pages for printing
on page printers.
### BPM (X'D3A8CB') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A8CB', Flags (1B), Reserved X'0000' [LINEDATA-5-072]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | PMName |  | Name of the Page Map | O [LINEDATA-5-073]|

### BPM Semantics
PMName Token name of the Page Map
This is an optional parameter. [LINEDATA-5-074]

## Conditional Processing Control (CCP)
The Conditional Processing Control structured field defines tests to be performed on selected input records in
line data and specifies the actions to take based on the test results. This optional structured field is selected
with LND, RCD,
or XMD structured fields in the Page Definition. An LND, RCD, or XMD can have a unique
CCP associated with it or it can reference a CCP that has already been used. In either case, the CCP is
referenced with the CCPID field of the LND, RCD,
or XMD. If a CCP structured field is included in a Page
Definition, it must appear before the Data Maps in the Page Definition. [LINEDATA-5-075]
### CCP (X'D3A7CA') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A7CA', Flags (1B), Reserved X'0000' [LINEDATA-5-076]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | CCPid | X'0001'–X'FFFF' | CCP Identifier | M [LINEDATA-5-077]|
| 2–3 | CODE | NxtCCPid | X'0001'–X'FFFF' | Next CCP Identifier | M [LINEDATA-5-078]|
| 4 | BITS | CCPFlgs |  | Conditional Processing Flags | M [LINEDATA-5-079]|
| 5 |  |  | X'00' | Reserved | M [LINEDATA-5-080]|
| 6–7 | UBIN | NumRGs | X'0001'–X'FFFF' | Number of repeating groups | M [LINEDATA-5-081]|
| 8–9 | UBIN | RGLgth | X'0015'–X'FFFF' | Length of each repeating group | M [LINEDATA-5-082]|
| 10–11 | UBIN | CSLgth | X'0000'–X'FFFF' | Length of comparison string | M [LINEDATA-5-083]|
| 12–n |  | Repeating groups |  | One or more repeating groups | M [LINEDATA-5-084]|

### CCP Semantics
CCPid CCP Identifier
CCPs can be chained to handle complex data within multiple CCP records. If this is the first or
only CCP , this field matches the CCP Identifier in CCPID field of the LND, RCD, or XMD.
Subsequent CCPs in a chain have unique identifiers.
NxtCCPid Next CCP Identifier
This field contains the identifier of the next CCP to be processed. A value of zero indicates that
this is the last or only CCP to process.
CCPFlgs Conditional Processing Flags
Bit 0 If B'1', this CCP requires action before a subpage boundary.
Bit 1 If B'1', this CCP requires action after a subpage boundary. [LINEDATA-5-085]


Bit 2 This field provides spacing action at the top of a page when the following conditions
are present:
• A new page is started by a true condition. [LINEDATA-5-086]
• Conditional processing is active. [LINEDATA-5-087]
• ANSI control characters are used. [LINEDATA-5-088]
If B'0', space according to the ANSI carriage control value. If B'1', suppress spacing;
print only.
Bits 3–7
Reserved; should be B'00000'
Note that when CCPs are used with RCDs to process record-format line data or XMDs to
process XML data, the whole page is the only sub-page, therefore timing actions that specify a
subpage are processed against the complete page.
NumRGs Number of Repeating Groups
This field indicates the number of repeating groups for this CCP . The value must be greater
than zero.
RGLgth Length of each repeating group
This field indicates the length of the repeating groups to follow. The length must be at least 21
bytes.
CSLgth Length of Comparison String
This field indicates the length of the text string in the Comparison String parameter within the
repeating group.
Each repeating group of the CCP contains action information. See Table 13for the definitions of the CCP
repeating groups.
### Table 13. CCP Repeating Group Structure

| Bytes | Parameter Name | Type | Description and Values |
| :--- | :--- | :--- | :--- |
| 0 | Timing of Action | UBIN | 0: Take default action (immediately before presenting current line)<br>1: Take action immediately before presenting current line<br>2: Take action before presenting current subpage<br>129: Take action immediately after presenting current line<br>130: Take action after presenting current subpage [LINEDATA-5-089]|
| 1 | Medium Map Action | UBIN | 0: Ignore<br>1: Continue using current medium map with page eject<br>2: Invoke named Medium Map<br>3: Invoke first Medium Map<br>4: Invoke next Medium Map [LINEDATA-5-090]|
| 2–9 | Medium Map Name | CHAR | Any 8-byte value [LINEDATA-5-091]|
| 10 | Data Map Action | UBIN | 0: Ignore<br>1: Continue using current Data Map with page eject<br>2: Invoke named Data Map<br>3: Invoke first Data Map<br>4: Invoke next Data Map [LINEDATA-5-092]|
| 11–18 | Data Map Name | CHAR | Any 8-byte value [LINEDATA-5-093]|
| 19 | Comparison | UBIN | 0: Any change<br>1: Equal to<br>2: Less than<br>3: Equal to or less than<br>4: Greater than<br>5: Equal to or greater than<br>6: Not equal<br>7: Take the action without comparison [LINEDATA-5-094]|
| 20–nnn | Comparison String | CHAR | 1 to nnn bytes, where nnn plus the total length of the fixed-length fields in the CCP is less than, or equal to, 32,759 bytes [LINEDATA-5-095]|

Byte 0 Timing of Action
This parameter indicates when the action specified for the CCP is to be taken. Only values of
0, 1, 2, 129 (X'81'), and 130 (X'82') are allowed.
Byte 1 Medium Map Action
This parameter indicates what action is to be taken for the Medium Map when the conditional
processing test for a comparison field is true.
Bytes 2–9 Medium Map Name
If the Medium Map Action parameter is 2 (Invoke named Medium Map), these 8 bytes contain
the name of the Medium Map to be used.
Byte 10 Data Map Action
This parameter indicates the action to be taken for the Data Map when the conditional
processing test for a comparison field is true.
Bytes 11–18 Data Map Name
If the Data Map Action parameter is 2 (Invoke Named Data Map), these 8 bytes contain the
name of the Data Map to be used.
Byte 19 Comparison
This one-byte parameter indicates the type of comparison between the input data and the
comparison string (bytes 20–nnn).
Value 0 (“any change”) specifies that the contents of the comparison field are to be compared
with the contents of the comparison field in the last preceding record that was checked using
the current CCP structured field. An “any change” comparison is true when the contents of the
comparison field have changed from one record being checked to the next. If the field lies
outside the boundary of the current input record, which might
occur with variable-length
records or with truncated trailing blanks, the comparison is false, and the current records are
not used in future comparisons.
An “any change” comparison is always false the first time the CCP structured field is used.
Whenever a new Data Map is invoked, all comparisons are reset, and comparison field values
in the previous Data Map are not retained.
Byte 20–nnn Comparison String
This variable-length parameter indicates the text string against which a comparison test is to
be performed, if the Comparison parameter has a value between 1 and 6.
The length of the text string is determined by a value contained in bytes 10–11 of the CCP
structured field.


Note: To be able to match this Comparison String to input data, the encoding of the text
specified in this parameter must match the encoding of the input data. [LINEDATA-5-096]


## Data Map Transmission Subcase Descriptor (DXD)
The Data Map Transmission Subcase Descriptor structured field is supported only for migration purposes. [LINEDATA-5-097]
### DXD (X'D3A6E3') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A6E3', Flags (1B), Reserved X'0000' [LINEDATA-5-098]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–3 |  | ConData | X'0001 00FF' | Constant data | M [LINEDATA-5-099]|

### DXD Semantics
ConData Constant data
This field must be set to X'0001 00FF', but is not checked. [LINEDATA-5-100]

## End Data Map (EDM)
The End Data Map structured field terminates the Data Map object initiated by a Begin Data Map structured
field.
### EDM (X'D3A9CA') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A9CA', Flags (1B), Reserved X'0000' [LINEDATA-5-101]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DMName |  | Name of the Data Map | O [LINEDATA-5-102]|

### EDM Semantics
DMName Token name of the Data Map being terminated
If a name is specified, it must match the name in the most recent Begin Data Map structured
field. If the first two bytes of this parameter contain the value X'FFFF', the name matches any
name specified on the corresponding Begin Data Map structured field. [LINEDATA-5-103]

## End Data Map Transmission Subcase (EDX)
The End Data Map Transmission Subcase structured field terminates the Data Map Transmission Subcase
initiated by a Begin Data Map Transmission Subcase structured field. [LINEDATA-5-104]
### EDX (X'D3A9E3') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A9E3', Flags (1B), Reserved X'0000' [LINEDATA-5-105]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DMXName |  | Name of the Data Map Transmission Subcase | O [LINEDATA-5-106]|

### EDX Semantics
DMXame Token name of the Data Map Transmission Subcase being terminated
If a name is specified, it must match the name in the most recent Begin Data Map
Transmission Subcase structured field. If the first two bytes of this parameter contain the value
X'FFFF', the name matches any name specified on the corresponding Begin Data Map
Transmission Subcase structured field. [LINEDATA-5-107]

## End Page Map (EPM)
The End Page Map structured field terminates the Page Map object initiated by a Begin Page Map structured
field.
### EPM (X'D3A9CB') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A9CB', Flags (1B), Reserved X'0000' [LINEDATA-5-108]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | PMName |  | Name of the Page Map | O [LINEDATA-5-109]|

### EPM Semantics
PMName Token name of the Page Map being terminated
If a name is specified, it must match the name in the most recent Begin Page Map structured
field. If the first two bytes of this parameter contain the value X'FFFF', the name matches any
name specified on the corresponding Begin Page Map structured field. [LINEDATA-5-110]

## Fixed Data Size (FDS)
The Fixed Data Size structured field specifies the number of bytes of text found in the following Fixed Data Text
(FDX) structured fields. [LINEDATA-5-111]
### FDS (X'D3AAEC') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3AAEC', Flags (1B), Reserved X'0000' [LINEDATA-5-112]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | TxtLgth | 1–65,535 | Number of data bytes in following FDX | M [LINEDATA-5-113]|

### FDS Semantics
TxtLgth Number of bytes of text in the FDX structured fields that immediately follow
If no fixed data text exists in this Data Map Transmission Subcase, the FDS and FDX
structured fields should not be specified. [LINEDATA-5-114]

## Fixed Data Text (FDX)
The Fixed Data Text structured field contains text that can be selected and presented with LND, RCD, or XMD
structured fields in the Page Definition. This text is used when flag bit 7 of the LND, RCD, or XMD is set to B'1'.
Any number of FDX structured fields can appear, but the total number of data bytes must match bytes 0–1 of
the Fixed Data Size (FDS) structured field. The output should fit on the page and the fit can be affected by the
size of the font used.
The DataStrt and DataLgth fields of the LND, RCD, or XMD specify the fixed data offset and length. [LINEDATA-5-115]
### FDX (X'D3EEEC') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3EEEC', Flags (1B), Reserved X'0000' [LINEDATA-5-116]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–n | CHAR | Text |  | Fixed text to be added | O [LINEDATA-5-117]|

### FDX Semantics
Text Code points of the fixed text to be added to the page
From 0 to 32,743 bytes may be specified. [LINEDATA-5-118]

## Invoke Data Map (IDM)
The Invoke Data Map structured field selects a new Data Map for printing line data and ends the current lineformat page. With LND Data Maps, processing begins with the first Line Descriptor (LND) structured field of the
invoked Data Map for the next line-format page. With RCD Data Maps, processing begins with the first Record
Descriptor (RCD) structured field that matches the Record ID of the current line-data record. With XMD Data
Maps, processing begins with the first XML Descriptor (XMD) structured field that matches the current
Qualified Tag.
### IDM (X'D3ABCA') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3ABCA', Flags (1B), Reserved X'0000' [LINEDATA-5-119]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DMName |  | Name of the new Data Map | M [LINEDATA-5-120]|

### IDM Semantics
DMName Token name of the new Data Map in the currently active Page Definition
This name must match the name on the Begin Data Map (BDM) structured field. If the name is
shorter than eight bytes, trailing blanks must be added. [LINEDATA-5-121]

## Include Object (IOB)
Notes:
1. The IOB is a MO:DCA structured field. The following description documents its use in line-mode and mixed [LINEDATA-5-122]
mode applications and introduces parameter values that are only valid in these environments. For the
formal definition of the IOB structured field, see the Mixed Object Document Content Architecture
(MO:DCA) Reference.
2. When processing XML data, the IOB may only be used in a Page Definition resource. [LINEDATA-5-123]
An Include Object structured field references an object and optionally contains parameters that identify the
object and that specify presentation parameters such as object position, size, orientation, mapping, and default
color. Where the presentation parameters conflict with parameters specified in the object's environment group
(OEG), the parameters in the Include Object structured field override. If the referenced object is a page
segment, the IOB parameters override the corresponding environment group parameters on all data objects in
the page segment.
### IOB (X'D3AFC3') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3AFC3', Flags (1B), Reserved X'0000' [LINEDATA-5-124]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | ObjName |  | Name of the object | M [LINEDATA-5-125]|
| 8 |  |  | 0 | Reserved | M [LINEDATA-5-126]|
| 9 | CODE | ObjType |  | Object type | M [LINEDATA-5-127]|
| 10–12 | SBIN | XoaOset | -32,768 – +32,767 | X-axis origin of the object area | M [LINEDATA-5-128]|
| 13–15 | SBIN | YoaOset | -32,768 – +32,767 | Y-axis origin of the object area | M [LINEDATA-5-129]|
| 16–17 | CODE | XoaOrent |  | X-axis rotation | M [LINEDATA-5-130]|
| 18–19 | CODE | YoaOrent |  | Y-axis rotation | M [LINEDATA-5-131]|
| 20–22 | SBIN | XocaOset |  | X-axis offset | M [LINEDATA-5-132]|
| 23–25 | SBIN | YocaOset |  | Y-axis offset | M [LINEDATA-5-133]|
| 26 | CODE | RefCSys | X'00', X'01' | Reference coordinate system | M [LINEDATA-5-134]|
| 27–n |  | Triplets |  | Triplets | M [LINEDATA-5-135]|

### IOB Semantics
For a complete definition of the IOB semantics, see the Mixed Object Document Content Architecture
(MO:DCA) Reference. The following describes parameter values that are unique to the IOB when used in linemode or mixed-mode environments.
XoaOset If RefCSys = X'01', this parameter specifies the offset along the X axis, X pg or Xol, of the
including page or overlay coordinate system to the origin of the X axis, X oa, of the object area
coordinate system. The value for this parameter is expressed in terms of the number of page
or overlay coordinate system X-axis measurement units. If the referenced object specifies an
Object Environment Group (OEG), this parameter overrides the corresponding parameter in
the Object Area Position (OBP) structured field of the OEG. If the object is a page segment,
this parameter overrides the corresponding OBP parameters in the environment groups of all
objects that comprise the page segment and specifies the object area offsets from the page or
overlay origin for all data objects in the page segment. A value of X'FFFFFF' indicates that the
X-axis offset specified in the object's OEG is to be used; therefore the offset value (-1) is
excluded from the allowed range. If the object does not specify the X-axis offset in an OEG,
the architected default is X'000000'.
If RefCSys = X'00', this parameter specifies the offset along the I axis to the I-position of the
current LND, RCD,
or XMD. The value for the parameter in this case is expressed in terms of
the number of I-axis measurement units.
YoaOset If RefCSys = X'01', this parameter specifies the offset along the Y axis, Y pg or Yol, of the
including page or overlay coordinate system to the origin of the Y axis, Y oa, of the object area
coordinate system. The value for this parameter is expressed in terms of the number of page
or overlay coordinate system Y-axis measurement units. If the referenced object specifies an
Object Environment Group (OEG), this parameter overrides the corresponding parameter in
the Object Area Position (OBP) structured field of the OEG. If the object is a page segment,
this parameter overrides the corresponding OBP parameters in the environment groups of all
objects that comprise the page segment and specifies the object area offsets from the page or
overlay origin for all data objects in the page segment. A value of X'FFFFFF' indicates that the
Y-axis offset specified in the object's OEG is to be used; therefore the offset value (-1) is
excluded from the allowed range. If the object does not specify the Y-axis offset in an OEG,
the architected default is X'000000'.
If RefCSys = X'00', specifies the offset along the B axis to the B-position of the current LND,
RCD, or XMD. The value for the parameter in this case is expressed in terms of the number of
B-axis measurement units.
XoaOrent If RefCSys = X'01', this parameter specifies the amount of clockwise rotation of the object
area's X axis, X
oa, about its defined origin relative to the X axis of the page or overlay
coordinate system. If the referenced object specifies an Object Environment Group (OEG),
this parameter overrides the corresponding parameter in the Object Area Position (OBP)
structured field of the OEG. If the object is a page segment, this parameter overrides the
corresponding OBP parameters in the environment groups of all objects that comprise the
page segment. A value of X'FFFF' indicates that the X-axis rotation specified in the object's
OEG is to be used. If the object does not specify the X-axis rotation in an OEG, the architected
default is X'0000' (0 degrees).
If RefCSys = X'00', this parameter specifies the amount of clockwise rotation of the object
area's X axis, X
oa, about its defined origin relative to the I axis of the current LND, RCD, or
XMD (I,B) coordinate system.
YoaOrent If RefCSys = X'01', this parameter specifies the amount of clockwise rotation of the object
area's Y axis, Y
oa, about its defined origin relative to the X axis of the page or overlay
coordinate system. The YoaOrent value must be 90 degrees greater than the XoaOrent value
or a X'01' exception condition exists. If the referenced object specifies an Object Environment
Group (OEG), this parameter overrides the corresponding parameter in the Object Area
Position (OBP) structured field of the OEG. If the object is a page segment, this parameter
overrides the corresponding OBP parameters in the environment groups of all objects that
comprise the page segment. A value of X'FFFF' indicates that the Y-axis rotation specified in
the object's OEG is to be used. If the object does not specify the Y-axis rotation in an OEG, the
architected default is X'2D00' (90 degrees).
If RefCSys = X'00', specifies the amount of clockwise rotation of the object area's Y axis, Y
oa,
about its defined origin relative to the I axis of the current LND, RCD, or XMD (I,B) coordinate
system.
Note: The following combinations of values are the only ones valid for the XoaOrent and
YoaOrent parameters:
XoaOrent YoaOrent Description
X'0000' X'2D00' 0 and 90 degrees respectively
X'2D00' X'5A00' 90 and 180 degrees respectively
X'5A00' X'8700' 180 and 270 degrees respectively
X'8700' X'0000' 270 and 0 degrees respectively
RefCSys Determines how the object is positioned and rotated on the page:
Value Description
X'00' The object area offset in the IOB is measured with respect to the current LND, RCD,
or XMD position using the current text (I,B) coordinate system. The object area
rotation in the IOB is measured with respect to the current text (I,B) coordinate system
I-axis. In this case, the object area offset and rotation parameters must be specified
explicitly, that is, a value of X'FF ...FF', which indicates that the value in the object's
OEG is to be used, is not supported for {XoaOset,YoaOset} and {XoaOrent,YoaOrent}
when RefCSys = X'00'.
X'01' The object area offset in the IOB is measured with respect to the page origin (X p=0,
Yp=0) using the page (X p,Yp) coordinate system. The object area rotation in the IOB is
measured with respect to the page (Xp,Yp) coordinate system X p-axis.
When line data with IOBs is transformed into MO:DCA data, the IOBs are generated in the MO:DCA data as
well. If an IOB specifies RefCSys=X'00', the position and orientation must be modified for the MO:DCA IOB to
specify the equivalent position and orientation based on the page (X
p,Yp) coordinate system. [LINEDATA-5-136]
### IOB Triplets
#### Extended Resource Local Identifier (X'22') Triplet
The Extended Resource Local Identifier (X'22') triplet is a MO:DCA triplet. For the formal definition of this
triplet, see the Mixed Object Document Content Architecture (MO:DCA) Reference.
This triplet is mandatory when the IOB structured field is specified in a PageDef, in which case it must occur
once. If this triplet is specified more than once, only the first is used. It specifies a local identifier for the IOB
that
is used to reference the IOB from one or more LND, RCD, or XMD structured fields. The ID specified for
each IOB must be unique within the PageDef. [LINEDATA-5-137]
### Triplet X'22' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 7 | Length of the triplet | M [LINEDATA-5-138]|
| 1 | CODE | Tid | X'22' | Identifies the Extended Resource Local Identifier triplet | M [LINEDATA-5-139]|
| 2 | CODE | ResType | X'30' | Resource type: IOB Reference | M [LINEDATA-5-140]|
| 3–6 | CODE | ResLID | X'00000000'–X'FFFFFFFF' | Specifies the extended resource local ID | M [LINEDATA-5-141]|

### Triplet X'22' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Extended Resource Local Identifier triplet
ResType Specifies the resource type associated with the extended local ID
Value Description
X'30' IOB Reference
The local identifier (LID) specified by this triplet is assigned to an IOB
structured field and is used by an LND, RCD, or XMD to reference the IOB
that specifies this ID. This value is only used when the triplet occurs in a Page
Definition in AFP line-data environments.
All others Reserved
ResLID Specifies a unique resource object Local ID
It may be in the range of X'00000000' to X'FFFFFFFF'. [LINEDATA-5-142]
## Include Page Overlay (IPO)
Note: The IPO is a MO:DCA structured field. The following description documents its use in line-mode and
mixed mode applications and introduces parameter values that are only valid in these environments. For
the formal definition of the IPO structured field, see the Mixed Object Document Content Architecture
(MO:DCA) Reference.
The Include Page Overlay structured field references an overlay resource object that is to be positioned on the
page. The overlay contains its own Active Environment Group. For line-mode and mixed-mode applications
only, a value of X'FFFFFF' may be used for either the X axis offset (bytes 8–10), the Y axis offset (bytes 11–
13), or both.
### IPO (X'D3AFD8') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3AFD8', Flags (1B), Reserved X'0000' [LINEDATA-5-143]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | OvlyName |  | Name of the overlay resource | M [LINEDATA-5-144]|
| 8–10 | SBIN | XolOset | -32,768 – +32,767 | X-axis origin for the page overlay | M [LINEDATA-5-145]|
| 11–13 | SBIN | YolOset | -32,768 – +32,767 | Y-axis origin for the page overlay | M [LINEDATA-5-146]|
| 14–15 | CODE | OvlyOrent |  | The overlay's X-axis rotation | O [LINEDATA-5-147]|
| 16–n |  | Triplets |  | Triplets | O [LINEDATA-5-148]|

### IPO Semantics
OvlyName Token name of the overlay being referenced
If the first two characters of the overlay name are O1 (capital letter O followed by the number
1), then bytes 0 and 1 must contain the characters O and 1, respectively.
XolOset Offset along the X p axis from the page origin where the origin of the overlay is placed
The value for this offset is expressed in terms of the measurement units currently in effect for
the active Data Map. A value of X'FFFFFF' indicates that the overlay is to be placed at the X p
axis point specified by the current LND or RCD; therefore the offset value (-1) is excluded from
the allowed range.
YolOset Offset along the Y
p axis from the page origin where the origin of the overlay is placed
The value for this offset is expressed in terms of the measurement units currently in effect for
the active Data Map. A value of X'FFFFFF' indicates that the overlay is to be placed at the Y p
axis point specified by the current LND or RCD; therefore the offset value (-1) is excluded from
the allowed range.
OvlyOrent Overlay orientation
Specifies the amount of rotation of the page overlay's X axis about the page overlay origin
relative to the X
p axis of the page. The page overlay X axis rotation is limited to 0, 90, 180, and
270 degrees. The page overlay Y axis rotation is always 90 degrees greater than the page [LINEDATA-5-149]
overlay X axis rotation.
If no value is specified for this parameter, the architected default is 0 degrees. Note that the
90°, 180°, 270° rotations of a page overlay are not supported in all AFP environments. Consult
the product documentation to see which rotations are supported. Also note that the MO:DCA
IS/1 and IS/2 interchange sets only support 0° rotation of a page overlay. [LINEDATA-5-150]
## Include Page Segment (IPS)
Note: The IPS is a MO:DCA structured field. The following description documents its use in line-mode and
mixed mode applications and introduces parameter values that are only valid in these environments. For
the formal definition of the IPS structured field, see the Mixed Object Document Content Architecture
(MO:DCA) Reference.
The Include Page Segment structured field references a page segment resource object that is to be positioned
on the page or overlay. For line-mode or mixed-mode applications only, a value of X'FFFFFF' may be used for
either the I-axis offset (bytes 8–10), the B-axis offset (bytes 11–13), or both. [LINEDATA-5-151]
### IPS (X'D3AF5F') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3AF5F', Flags (1B), Reserved X'0000' [LINEDATA-5-152]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | PsegName |  | Name of the page segment resource | M [LINEDATA-5-153]|
| 8–10 | SBIN | IpsOset | -32,768 – +32,767 | I-axis origin for the page segment | M [LINEDATA-5-154]|
| 11–13 | SBIN | BpsOset | -32,768 – +32,767 | B-axis origin for the page segment | M [LINEDATA-5-155]|
| 14–n |  | Triplets |  | Triplets | O [LINEDATA-5-156]|

### IPS Semantics
PsegName Token name of the page segment being referenced
All eight bytes of the name must be specified.
IpsOset Offset along the I axis from the current text coordinate system origin (I=0, B=0) to the origin of
the page segment
The value for this offset is expressed in terms of the measurement units currently in effect for
the active Data Map and is measured using the current text (I,B) coordinate system. A value of
X'FFFFFF' indicates that the page segment origin is to be placed at the I-axis point specified
by the current LND or RCD; therefore the offset value (-1) is excluded from the allowed range.
BpsOset Offset along the B axis from the current text coordinate system origin (I=0, B=0) to the origin of
the page segment
The value for this offset is expressed in terms of the measurement units currently in effect for
the active Data Map and is measured using the current text (I,B) coordinate system. A value of
X'FFFFFF' indicates that the page segment origin is to be placed at the B-axis point specified
by the current LND or RCD; therefore the offset value (-1) is excluded from the allowed range.
Note: The MO:DCA Line Data Object Position Migration (X'27') triplet may be specified on the IPS in MO:DCA
documents to capture the text orientation that was specified when the page segment referenced by the
IPS was included in line data. The information in this triplet allows the page segment and its objects to
be positioned and oriented correctly on the MO:DCA page. [LINEDATA-5-157]

## Line Descriptor Count (LNC)
The Line Descriptor Count structured field specifies the number of Line Descriptor (LND), Record Descriptor
(RCD) or XML Descriptor (XMD) structured fields in the Data Map Transmission Subcase. [LINEDATA-5-158]
### LNC (X'D3AAE7') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3AAE7', Flags (1B), Reserved X'0000' [LINEDATA-5-159]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | NumDSC | 1–65,535 | Number of LND, RCD, or XMD structured fields | M [LINEDATA-5-160]|

### LNC Semantics
NumDSC Number of LND, RCD, or XMD structured fields in the Data Map Transmission Subcase
Note: The LND, RCD, or XMD in a Data Map are numbered sequentially, starting with 1.
Values from 1 through the number of LND, RCD, or XMD are allowed. [LINEDATA-5-161]

## Line Descriptor (LND)
The Line Descriptor structured field contains information, such as line position, text orientation, font selection,
field selection, and conditional processing identification, used to format line data.
Note: The LNDs in a Data Map are numbered sequentially, starting with 1. Values from 1 through the number
of LNDs are allowed.
### LND (X'D3A6E7') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A6E7', Flags (1B), Reserved X'0000' [LINEDATA-5-162]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | BITS | LNDFlgs |  | LND flags | M [LINEDATA-5-163]|
| 2–3 | UBIN | IPos |  | Inline Position | M [LINEDATA-5-164]|
| 4–5 | UBIN/SBIN | BPos |  | Baseline position | M [LINEDATA-5-165]|
| 6–9 | CODE | TxtOrent |  | Text (I,B) Orientation | M [LINEDATA-5-166]|
| 10 | CODE | FntLID |  | Primary font local ID | M [LINEDATA-5-167]|
| 11 | CODE | ChnlCde |  | Channel code | M [LINEDATA-5-168]|
| 12–13 | UBIN | NLNDskp |  | Next LND if skipping | M [LINEDATA-5-169]|
| 14–15 | UBIN | NLNDsp |  | Next LND if spacing | M [LINEDATA-5-170]|
| 16–17 | UBIN | NLNDreu |  | Next LND if reusing data | M [LINEDATA-5-171]|
| 18–25 | CHAR | SupName |  | Suppression token name | M [LINEDATA-5-172]|
| 26 | CODE | SOLid |  | Shift-out font local ID | M [LINEDATA-5-173]|
| 27–30 | UBIN | DataStrt |  | Data start position | M [LINEDATA-5-174]|
| 31–32 | UBIN | DataLgth |  | Data length | M [LINEDATA-5-175]|
| 33–34 | CODE | TxtColor |  | Text color value | M [LINEDATA-5-176]|
| 35–36 | UBIN | NLNDccp |  | Next LND if conditional processing | M [LINEDATA-5-177]|
| 37 | CODE | SubpgID |  | Subpage ID | M [LINEDATA-5-178]|
| 38–39 | CODE | CCPID |  | CCP Identifier | M [LINEDATA-5-179]|
| 40–n |  | Triplets |  | Triplets | O [LINEDATA-5-180]|

LND Semantics. Text color value M
35–36 UBIN NLNDccp X'0000'–X'FFFF' Next LND if conditional processing M
37 CODE SubpgID X'00'–X'FF' Subpage ID M [LINEDATA-5-181]
38–39 CODE CCPID X'0000'–X'FFFF' CCP Identifier M
40–n Triplets See LND Semantics for triplet applicability. O
Architecture Note: Prior to the addition of color and conditional processing to the page definition, bytes 33
through 39 were not defined as part of the LND structured field. Older page definition generators
continued to generate the shorter version of the LND for some time after conditional processing and
color were added to the architecture. Some of these page definitions still exist and should be handled by
page definition processors. The valid length of the LND without the color and conditional processing
fields was 33 bytes. Processors of page definitions may safely assume the value of
each missing field is
zero. LND triplets are not allowed on the shorter version of the LND. [LINEDATA-5-182]
### LND Semantics
LNDFlgs LND flags
Bit 0 End Page if Skipping
This bit shows whether the page ends if the control character is set for skipping. This
flag is processed if the channel code of the LND does not match the skip-to channel
code.
Value Description
B'0' The current page does not end.
B'1' The next data should be on a new page at the print position specified by the
first LND with the indicated channel code. If the channel code is not found but
the End Page if Skipping is set, the search for the channel code continues
with the first LND in the Data Map.
Bit 1 End Page if Spacing
This bit shows whether the page ends if the control character is set for spacing.
Value Description
B'0' The current page does not end.
B'1' The next data should be on a new page at the print position indicated by the
first LND in the Data Map.
Bit 2 Generate Inline Position
This bit shows whether the data processed using this LND is placed on the page at
the inline position specified in bytes 2–3. This position becomes the new print position. [LINEDATA-5-183]


Value Description
B'0' The current inline position is used.
B'1' Bytes 2–3 are used for the new inline position.
Bit 3 Generate Baseline Position
This bit shows whether the data processed using this LND is placed on the page at
the baseline position specified in bytes 4–5. The baseline position may be an absolute
position or a relative position, as specified by bit 13. This position becomes the new
print position.
Value Description
B'0' T
he current baseline position is used.
B'1' Bytes 4–5 and bit 13 are used to generate either an absolute baseline
position with respect to the current text (I,B) coordinate system origin or a
relative baseline position with respect to a previously defined baseline
position.
Bit 4 Generate Font Change
This bit shows whether the data processed using this LND is printed using the Font
Local Identifier specified in byte 10.
Value Description
B'0' The following rules apply:
• If the record to be processed contains a TRC, use the font corresponding to [LINEDATA-5-184]
the TRC.
• If the current Data Map maps fonts with MCF or MDR structured fields, use [LINEDATA-5-185]
the first font in the font list.
• If the current Data Map does not map fonts, use the hardware default font. [LINEDATA-5-186]
B'1' T
he font specified in byte 10 is used.
Bit 5 Generate Suppression
This bit shows whether the data processed using this LND is suppressible text.
Value Description
B'0' The data is not suppressible text.
B'1' The data is suppressible text.
If this LND is used to present the selected data as a bar code, this flag is ignored.
Suppression is only supported for text.
Note: Refer to the Presentation Text Object Content Architecture Reference for more
information on text suppression.
Bit 6 Reuse Record
This bit shows whether the line data processed by this LND should be reused and
processed by the LND specified in bytes 16–17 (NLNDreu).
Value Description
B'0' The record is not reused.
B'1' The record is reused.
Bit 7 Use Fixed Data
This bit shows whether to present text from the Fixed Data Text (FDX) structured
fields.


Value Description
B'0' Fixed data is not presented.
B'1' Fixed data is presented.
Bytes 27–30 (DataStrt) specify the start of the text to be added, and bytes 31–32
(DataLgth) specify the number of data bytes.
Note: No data from the current record is placed within the page under the control of
this LND.
Bit 8 Reserved; should be zero
Bit 9 Use Compatibility TRC
This bit indicates whether the compatibility TRC should be used. The compatibility
TRC uses only the last 4 bits, but the noncompatibility TRC uses all 8 bits.
Value Description
B'0' The compatibility TRC is not used.
Valid TRC values are from 0 through 126.
If a TRC is specified that is beyond the number of fonts mapped, use the first
font in the MCF .
B'1' The compatibility TRC is used.
Only the first four fonts specified are used when this bit is set. If the TRC
value is greater than the number of fonts specified, use the first font.
Bit 10 Set Text Color
If this bit is B'1', it specifies that the line data processed by this LND is to be presented
in the color specified by the Color Specification (X'4E') triplet or by the value specified
in bytes 33–34. The X'4E' triplet, if specified, takes precedence over the value in bytes
33–34;
however, the value in bytes 33–34 may be used instead of the X'4E' color if the
presentation device does not support the PTOCA PT3 subset. If this bit is B'0', the line
data processed by this LND is presented in the presentation process default color.
Bit 11 Conditional Processing
This bit indicates whether conditional processing should be performed on the current
line data record.
Value Description
B'0' C
onditional processing is not performed.
B'1' Conditional processing is performed.
If this bit is B'1', the LND is referred to as a conditional processing LND and is only
used to specify data to be compared, not to place data on the page.
Bit 12 Resource Object Include
This bit indicates whether this LND specifies by name resource objects to be included
on the page at a specified position.
Value Description
B'0' Named resource objects are not included.
B'1' Named resource objects identified in the Resource Object Include (X'6C')
triplet on this LND are included.
Bit 13 Relative Baseline Position
This bit indicates whether the baseline position specified in bytes 4–5 of this LND is an
absolute position or a relative position. If an absolute baseline position is specified, it
is measured as a positive offset in the baseline direction from the current text (I,B) [LINEDATA-5-187]


coordinate system origin. If a relative position is specified, it is measured as a positive
or negative offset from a previous baseline position using the current text (I,B)
coordinate system, which is defined by the text orientation specified in bytes 6–9.
Value Description
B'0' The baseline position specified in bytes 4–5 is an absolute position.
B'1' The baseline position specified in bytes 4–5 is a relative position.
The following restriction applies to relative baseline positioning:
• The text orientation of an LND that specifies relative baseline positioning must be [LINEDATA-5-188]
the same as the text orientation of the LND that defines the baseline position from
which the relative offset is measured.
Bits 14–15
Reserved; should be zero
IPos Inline Position
This field specifies the inline position of data, specified as an offset from the current text (I,B)
coordinate system origin, which is defined by the text orientation specified in bytes 6–9
(TxtOrent). The offset is measured using the measurement units specified for the page in the
PGD. If bit 2 of byte 0 is B'1', it is used. This position becomes the new print position.
Note: Data must not exceed the boundaries of the page, which are defined in the Page
Descriptor (PGD) structured field. If the new print position is outside these boundaries,
printing of the page stops.
BPos Baseline Position
This field specifies the baseline position of data. If bit 13 specifies absolute baseline position,
these bytes specify a positive offset in the baseline direction from the current text (I,B)
coordinate system origin. If bit 13 specifies a relative baseline position, these bytes specify a
positive or negative offset from a previous baseline position using the current text (I,B)
coordinate system, which is defined by the text orientation in bytes 6–9. The baseline position
used as a reference for the relative offset depends on whether the LND that specifies relative
positioning is a base LND and on whether a page or subpage boundary was crossed since the
last LND was used to print. For a definition of base LNDs see “Field Formatting—LND
Processing”. The baseline position used as a reference for the relative offset is
determined as follows:
• For base LNDs, offsets are defined relative to the last base LND processed, that is, the last [LINEDATA-5-189]
LND used to print or the last LND processed for spacing. However, if a page or subpage
boundary was crossed after the last base LND was processed, offsets are defined relative to
the first LND for the page or subpage.
• For reuse LNDs other than base LNDs, the offset is defined relative to the last LND that was [LINEDATA-5-190]
processed for print (whether or not data prints).
• If the first LND of a Data Map specifies relative positioning, its offset is defined relative to the [LINEDATA-5-191]
current text coordinate system origin (I=0,B=0), using the current text (I,B) coordinate
system.
• If the first LND of a subpage specifies relative positioning, its offset is defined relative to the [LINEDATA-5-192]
last base LND used to print, using the current text (I,B) coordinate system. Note that when
skipping into a subpage, if the skipped-to LND specifies relative positioning, the relative
offset is measured with respect to the first LND of the subpage, which might
specify a
relative position as well. This function allows a subpage to “float” relative to the last print
position.
The offset is measured using the measurement units specified for the page in the PGD. If bit 3
of byte 0 is B'1', this position is used and becomes the new print position. [LINEDATA-5-193]


Application Note: When relative baseline positioning is used, the PageDef generator cannot
check for off-page errors, since the data normally determines, with skip-to-channel
carriage controls, when the relative baseline LNDs are invoked. AFP print servers
generate a page break if the active Data Map is about to position data past the page's
y-extent. This does not cause the generation of an error message. Note that the page's
y-extent is specified in the PGD of the Data Map.
TxtOrent Text (I,B) Orientation
The four valid text orientations are:
Orientation Value
0°,90° X'0000 2D00'
90°,180° X'2D00 5A00'
180°,270° X'5A00 8700'
270°,0° X'8700 0000'
Note that a change in text orientation means a change in the origin of the text (I,B) coordinate
system:
Orientation Value
0°,90° Top-left corner of page
90°,180° Top-right corner of page
180°,270° Bottom-right corner of page
270°,0° Bottom-left corner of page
If relative Baseline positioning is specified in bit 13, this text orientation must be the same as
the text orientation of the LND that defines the baseline position from which the relative offset
is measured.
FntLID Primary Font Local Identifier
If bit 4 of byte 0 is B'1', this is the local identifier of the font for text processed by this LND.
When in shift-out/shift-in (SOSI) processing mode, this is also the local identifier of the font to
be used following the implicit shift-in at the start of a record and, when using record-based
SOSI font selection, it is also the font Local identifier to be used following an explicit shift-in in
a record. A null value (X'FF') indicates that a presentation system default font is to be used.
Note: The Map Coded Font or Map Data Resource structured field in the AEG for the Data
Map must have mapped the local identifier to the name of a font.
ChnlCde Channel Code
This field specifies the channel code of this LND. The value X'00' indicates that this LND has
no channel code.
NLNDskp Next Line Descriptor if Skipping
If the record indicates skipping, this parameter points to the LND to use in scanning for the
channel code indicated by the control character of the record. If in following the Next Line
Descriptor if Skipping chain, the channel code is not found but the End Page if Skipping bit is
set, the search for the channel code continues with the first LND in the Data Map.
The LNDs in a Data Map are numbered sequentially, starting with 1. Values from 1 through the
number of LNDs are allowed.
NLNDsp Next Line Descriptor if Spacing
If the record indicates spacing, this parameter points to the LND to use next. A chain of Next
Line Descriptor if Spacing values is followed until the number of entries followed equals the
number of spaces desired.
The LNDs in a Data Map are numbered sequentially, starting with 1. Values from 1 through the
number of LNDs are allowed. [LINEDATA-5-194]


NLNDreu Next Line Descriptor if Reusing Data
This parameter points to the LND used to continue processing the record when reusing data.
If bit 6 of byte 0 = B'1', this parameter points to the next LND in a chain to process the same
data record. The end of the chain is indicated by bit 6 of byte 0 = B'0' and a value of X'0000' in
this parameter. At the end of the chain, control returns to the base LND. For example, if single
spacing is specified and the data does not contain a skip-to-channel carriage control,
processing resumes with the LND following the base LND.
When machine carriage controls or no carriage controls are specified, the LND that started the
reuse chain is used with the current data record to determine the next LND to use. When ANSI
carriage controls are specified, the LND that started the reuse chain is used with the next data
record to determine the next LND to use.
The LNDs in a Data Map are numbered sequentially, starting with 1. Values from 1 through the
number of LNDs are allowed. The chain of LNDs that is traversed when re-using a record
does not have to follow any numerical order.
SupName Suppression token name
This field specifies the suppression to be used with this LND. This value must match bytes 0–7
in one of the repeating groups in the Map Suppression (MSU) structured field of the Form
Definition. The value can be any 8-byte value except null.
This parameter is ignored if bit 5, byte 0 is B'0'.
Note that suppression is only supported for text. If the data selected by this LND is presented
as a bar code, this parameter is ignored. Note also that if text suppression is activated, only
the field or record processed by this LND is suppressed, not text data that may be included
using a Resource Object Include (X'6C') triplet.
SOLid Shift-out Font Local Identifier
If this byte is non-zero, it specifies the local identifier of the font to be used when a shift-out
control is encountered in the record processed by this LND. Use of this parameter signals
record-based font selection for SOSI processing. If this byte is zero, the parameter is not
specified.
Note: When processing line data in shift-out/shift-in (SOSI) mode, an implicit shift-in is
assumed at the start of every record.
DataS
trt Data Start Position
This field specifies the offset of the first data byte to be processed by this LND. If bit 7 of byte 0
is B'1', the data from the Fixed Data Text (FDX) structured field is used. Otherwise, the data
from the current record is used.
A value of 0 indicates that the first byte is to be used. No data is placed if this value is greater
than the length of the data source.
If conditional processing is to be performed (bit 11 of byte 1 is B'1'), this parameter defines the
start of the data to be compared, known as the Comparison field. The comparison is
determined by the Conditional Processing Control (CCP) structured field identified in bytes
38–39 (CCPID).
DataL
gth Data Length
This field specifies the number of bytes of data to be processed by this LND. If bit 7 of byte 0 is
B'1', the data from the Fixed Data Text (FDX) structured field is used. Otherwise, the data from
the current record is used.
If this value is X'FFFF', all the remaining data bytes are processed.
If this parameter causes data to be positioned outside the boundaries of the page, which are
defined in the Page Descriptor (PGD) structured field, the printing of the page stops. [LINEDATA-5-195]


If conditional processing is to be performed (bit 11 of byte 1 is B'1'), this parameter defines the
length of the comparison field. The comparison is determined by the Conditional Processing
Control (CCP) structured field identified in bytes 38–39 (CCPID).
TxtColor Text Color Value
The specified color is used to present text processed by this LND when LND byte 0, bit 10=
B'1' and a Color Specification (X'4E') triplet is not specified for this LND. This color may also
be used if the X'4E' triplet is specified but the PTOCA PT3 subset is not supported by the
presentation device. Color values are defined as shown in Table 14. They reflect
the range of values defined in the Standard OCA Color Value Table. For a definition of the
Standard OCA Color Value Table, see the Mixed Object Document Content Architecture
(MO:DCA) Reference. RGB values are also defined for each color, assuming that the intensity
range for each component is 0–255. [LINEDATA-5-196]
### Table 14. Color-Value Table

| Value | Color | Red (R) | Green (G) | Blue (B) |
| :--- | :--- | :--- | :--- | :--- |
| X'0000' or X'FF00' | Device default | — | — | — [LINEDATA-5-197]|
| X'0001' or X'FF01' | Blue | 0 | 0 | 255 [LINEDATA-5-198]|
| X'0002' or X'FF02' | Red | 255 | 0 | 0 [LINEDATA-5-199]|
| X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 [LINEDATA-5-200]|
| X'0004' or X'FF04' | Green | 0 | 255 | 0 [LINEDATA-5-201]|
| X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 [LINEDATA-5-202]|
| X'0006' or X'FF06' | Yellow | 255 | 255 | 0 [LINEDATA-5-203]|
| X'0007' | White; see note 1 | 255 | 255 | 255 [LINEDATA-5-204]|
| X'0008' | Black | 0 | 0 | 0 [LINEDATA-5-205]|
| X'0009' | Dark blue | 0 | 0 | 170 [LINEDATA-5-206]|
| X'000A' | Orange | 255 | 128 | 0 [LINEDATA-5-207]|
| X'000B' | Purple | 170 | 0 | 170 [LINEDATA-5-208]|
| X'000C' | Dark green | 0 | 146 | 0 [LINEDATA-5-209]|
| X'000D' | Dark turquoise | 0 | 146 | 170 [LINEDATA-5-210]|
| X'000E' | Mustard | 196 | 160 | 32 [LINEDATA-5-211]|
| X'000F' | Gray | 131 | 131 | 131 [LINEDATA-5-212]|
| X'0010' | Brown | 144 | 48 | 0 [LINEDATA-5-213]|
| X'FF07' | Device default | — | — | — [LINEDATA-5-214]|
| X'FF08' | Color of medium | — | — | — [LINEDATA-5-215]|
| All others | Reserved | — | — | — [LINEDATA-5-216]|

Notes:
1. The color rendered on presentation devices that do not support white is device-dependent. For [LINEDATA-5-217]
example, some printers simulate white with the color of the medium, which results in white if a
white medium is used.
2. The value X'FFFF' is supported for migration purposes only and specifies the presentation [LINEDATA-5-218]
process default.


NLNDccp Next Line Descriptor if Conditional Processing
A non-zero value in this parameter means that conditional processing is to be performed on
the current line data record. This parameter points to the LND used to perform conditional
processing on the current input line data record. The target LND, called a conditional
processing LND, must specify flag bit 11=B'1' and must point to a CCP structured field that
defines the conditional processing. The conditional processing LND defines the data to be
compared and does not place data on the page. Note that a conditional processing LND may
point to another conditional processing LND.
Subp
gID Subpage Identifier
For conditional processing, a page can be divided into subpages to specify boundaries on
which conditional processing actions can be taken. All LND structured fields of one subpage
should have the same value in their Subpage Identifier byte, differing from the value in the
LND structured fields of neighboring subpages. See Chapter 3, “Using a Page Definition to
Print Data” for more information on conditional processing and subpages.
The presentation services program
detects the beginning and end of subpages by checking
for a change in the value of byte 37 (SubpgID). If conditional processing is to be performed (bit
11 of byte 1 is B'1'), this parameter is used to identify subpages when the timing of the [LINEDATA-5-219]
conditional action is relative to the subpage.
CCPID CCP Identifier
If bit 11 of byte 1 is B'1' this parameter is used to locate the associated Conditional Processing
Control (CCP) structured field that describes the conditional processing to be performed for
this Page Definition.
Triplets Optional triplets
See the following for detailed information about the triplets permitted on the LND structured
field:
“Fully Qualified Name (X'02') Triplet”
“Extended Resource Local Identifier (X'22') Triplet”
“Color Specification (X'4E') Triplet”
“Bar Code Symbol Descriptor (X'69') Triplet”
“Resource Object Include (X'6C') Triplet”
“Additional Bar Code Parameters (X'7B') Triplet”
“Object Reference Qualifier (X'89') Triplet”
“Color Management Resource Descriptor (X'91') Triplet”
“Concatenate Bar Code Data (X'93') Triplet
”


### LND Triplets
Fully Qualified Name (X'02') Triplet
The Fully Qualified Name (X'02') triplet is a MO:DCA triplet. For the formal definition of this triplet, see the
Mixed Object Document Content Architecture (MO:DCA) Reference.
This triplet is optional and may occur one or more times when a Bar Code Symbol Descriptor (X'69') triplet is
specified on the LND, RCD, or XMD. The Fully Qualified Name type that may appear is X'DE'- Data Object
External Resource Reference. The FQN triplet specifies the external identifier of a Color Management
Resource (CMR) object that is used for the Bar Code object being generated. The identifier is used by the
presentation system to locate the resource object in the resource hierarchy. The identifier is a characterencoded name that
must be specified using FQNFmt = X'00'. The encoding for the external identifier of the
CMR must be UTF-16BE. [LINEDATA-5-220]
### Triplet X'02' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 5–254 | Length of the triplet | M [LINEDATA-5-221]|
| 1 | CODE | Tid | X'02' | Identifies the Fully Qualified Name triplet | M [LINEDATA-5-222]|
| 2 | CODE | FQNType |  | FQN type | M [LINEDATA-5-223]|
| 3 | CODE | FQNFmt | X'00' | GID format is character string | M [LINEDATA-5-224]|
| 4–n |  | FQName |  | GID of the Record Descriptor | M [LINEDATA-5-225]|

### Triplet X'02' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Fully Qualified Name triplet
FQNType Specifies how the fully qualified name is to be used
Value Description
X'DE' This GID specifies the name of a Color Management Resource to use when
generating the Bar Code object.
All others Reserved
FQNFmt Specifies the format of the Global Identifier
Value Description
X'00' The GID is a character-encoded name that means the data type is CHAR.
All others Reserved
FQName Contains the Global Identifier (GID) to be used as the name of the CMR
The encoding for the external identifier of the CMR must be UTF-16BE. [LINEDATA-5-226]


Extended Resource Local Identifier (X'22') Triplet
The Extended Resource Local Identifier (X'22') triplet is a MO:DCA triplet. For the formal definition of this
triplet, see the Mixed Object Document Content Architecture (MO:DCA) Reference.
This triplet is optional and may occur one or more times to reference an IOB structured field in the PageDef.
The reference consists of a local identifier that must match the local identifier on an IOB in the PageDef. When
an IOB with matching ID is found, the IOB is processed to present the object on the page. If an IOB with
matching ID is not found, an exception is generated. [LINEDATA-5-227]
### Triplet X'22' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 7 | Length of the triplet | M [LINEDATA-5-228]|
| 1 | CODE | Tid | X'22' | Identifies the Extended Resource Local Identifier triplet | M [LINEDATA-5-229]|
| 2 | CODE | ResType | X'30' | Resource type: IOB Reference | M [LINEDATA-5-230]|
| 3–6 | CODE | ResLID | X'00000000'–X'FFFFFFFF' | Specifies the extended resource local ID | M [LINEDATA-5-231]|

### Triplet X'22' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Extended Resource Local Identifier triplet
ResType Specifies the resource type associated with the extended local ID
Value Description
X'30' IOB Reference
The local identifier (LID) specified by this triplet is assigned to an IOB
structured field and is used by an LND, RCD, or XMD to reference the IOB
that specifies this ID. This value is only used when the triplet occurs in a Page
Definition in AFP line-data environments.
All others Reserved
ResLID Specifies a unique resource object Local ID
The range for the Local ID is
X'00000000' to X'FFFFFFFF'.
Note: RefCSys parameter in IOB byte 26 determines how the object included with the IOB is
positioned and rotated on the page:
Value Description
X'00' The object area offset in the IOB is measured with respect to the current LND,
RCD, or XMD position using the current text (I,B) coordinate system. The
object area rotation in the IOB is measured with respect to the current text (I,B)
coordinate system I-axis.
X'01' The object area offset in the IOB is measured with respect to the page origin
(X
p=0,Yp=0) using the page (X p,Yp) coordinate system. The object area rotation
in the IOB is measured with respect to page (X p,Yp) coordinate system X p-axis. [LINEDATA-5-232]


#### Color Specification (X'4E') Triplet
The Color Specification triplet is a MO:DCA triplet. For the formal definition of this triplet, see the Mixed Object
Document Content Architecture (MO:DCA) Reference. Support for this triplet is tied to PTOCA PT3 support.
This is an optional triplet that specifies a color for text processed by this LND when LND byte 0 bit 10=B'1' or
when the selected data is to be presented as a bar code symbol. One Color Specification triplet may be
specified on an LND. If this triplet is specified more than once, only the first is used. If this triplet is specified for
text when LND byte 0 bit 10=B'0', it is ignored.
With this triplet a color is specified by selecting a color space, an encoding for the components of the color
value in that space, and a color value. The color spaces supported are:
• RGB
• CMYK
• Highlight [LINEDATA-5-233]
• CIELAB [LINEDATA-5-234]
• Standard OCA color space [LINEDATA-5-235]
The selected encoding defines the number of bits used to specify each component. For example, with the RGB
color space, one supported encoding is 8 bits per component, which maps the component intensity range 0 to
1 to the binary values 0 to 255. The color value specifies the color. With the RGB color space and an 8 bit per [LINEDATA-5-236]
component encoding, the color value (255,255,255) specifies full intensity for each component, which defines
the color white.


#### Bar Code Symbol Descriptor (X'69') Triplet
Architecture Note: The Bar Code Symbol Descriptor triplet is registered in the MO:DCA architecture as a
private-use triplet since it is used only in the PageDef object, which is not a MO:DCA object.
This is an optional triplet and may occur once. If this triplet is specified more than once, only the first is used.
When present, it specifies that the data selected by LND, RCD, or XMD parameters DataStrt and DataLgth or
the data selected by the RCD or XMD parameter Fldno is to be presented as a bar code symbol. The data can
be from the current record or it can be fixed data. The origin of the bar code symbol or the character reference
point (see byte 4, bit 5, for details) on the page presentation space is specified by the LND, RCD, or XMD
position parameters IPos and BPos, and the orientation of the bar code symbol with respect to the page
presentation space X
p-axis is specified by the LND, RCD, or XMD text orientation parameter TxtOrent. Note
that the suppression function that may be specified on an LND, RCD, or XMD is not supported when the record
or field is presented as a bar code. Note also that when an LND that specifies bar code presentation is re-used
because the carriage control in the line data record specifies suppress spacing, only the last bar code
generated by this LND is presented. If this triplet is specified, LND, RCD, or XMD flag bit 5 — Generate
Suppression and the SupName parameter are ignored.
This triplet is not supported on conditional processing LNDs, RCDs, or XMDs that is LNDs, RCDs, or XMDs
that specify flag bit 11=B'1'; if present, it is ignored.
This triplet carries parameters defined by the BCOCA architecture, for more information see the Bar Code
Object Content Architecture Reference. Not all presentation services programs
support this triplet.
The data used for the bar code can be obtained from multiple fields using the Concatenate Bar Code Data
(X'93') triplet.
### Triplet X'69' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 4 or 18 | Length of the triplet | M [LINEDATA-5-237]|
| 1 | CODE | Tid | X'69' | Identifies the Bar Code Symbol Descriptor triplet | M [LINEDATA-5-238]|
| 2–3 | CODE | DescID | X'0001'–X'FFFE' | Identifies a bar code symbol descriptor | M [LINEDATA-5-239]|
| 4 | BITS | SymbFlgs |  | Symbol flags | O [LINEDATA-5-240]|
| 5–6 | UBIN | BCWdth |  | Desired symbol width | O [LINEDATA-5-241]|
| 7 | CODE | BCType |  | Bar code type | O [LINEDATA-5-242]|
| 8 | CODE | BCMod |  | Bar code modifier | O [LINEDATA-5-243]|
| 9 | CODE | FntLID | X'00'–X'FE', X'FF' | Font local identifier | O [LINEDATA-5-244]|
| 10–11 | CODE | Color |  | Bar code color | O [LINEDATA-5-245]|
| 12 | UBIN | ModWdth |  | Module width in mils | O [LINEDATA-5-246]|
| 13–14 | UBIN | ElmtHt |  | Element Height in L-units | O [LINEDATA-5-247]|
| 15 | UBIN | Mult |  | Height multiplier | O [LINEDATA-5-248]|
| 16–17 | UBIN | WE:NE |  | Wide-to-narrow ratio | O [LINEDATA-5-249]|

### Triplet X'69' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Bar Code Symbol Descriptor triplet
DescID Specifies the ID of a bar code symbol descriptor
The descriptor is defined by bytes 4–17 of this triplet. If the ID matches the ID of a bar code
symbol descriptor defined previously on an LND for this page, the previous descriptor is used
regardless of whether bytes 4–17 are specified in the current descriptor. If the ID does not
match a previously-defined ID, bytes 4–17 must be specified and define the bar code symbol
descriptor that is to be identified with this ID and that is to be used to generate this symbol.
The valid range for the ID is X'0001'–X'FFFE'.
For a given page, the presentation services program collects all bar code symbols that have
the same bar code symbol descriptor ID and the same bar code symbol orientation and
groups them into a single bar code object.
The origin for the bar code object presentation space that contains the symbols is one of the
four corners of the page as determined by the text orientation specified in LND bytes 6–9
(TxtOrent). The bar code presentation space origin is therefore coincident with the current text
coordinate system (I,B) origin.
For example, if the LND specifies a (90°,180°) text orientation, the origin of the bar code object
presentation space is the top-right corner of the page and bar code symbols in this object are
rotated 90° with respect to the page X
p axis.
The extents of the object presentation space are determined by the extents of the page
presentation space. For example, if the origin of the object presentation space is the top-right
corner of the page, the X-extent of the object presentation space is the Y-extent of the page
and the Y-extent of the object presentation space is the X-extent of the page. The symbol
origin offset from the object presentation space origin and from the current text (I,B)
coordinate system origin is specified by LND bytes 2–5 (IPOS,BPOS).
The units of measure for the bar code object presentation space, used for determining symbol
origin offsets, are the same as those defined on the page (X
p,Yp) presentation space in the
PGD structured field of the Active Environment Group (AEG) for the Data Map. The
presentation services program also defines an object area presentation space for the object
that is identical in size, position, and units of measure to the bar code presentation space, and
that has the same rotation about the page X
p-axis as the bar code symbols in the object. [LINEDATA-5-250]


SymbFlgs These flags specify additional controls.
Bits 0–3 These bits have the same syntax and semantics as the corresponding bits in
byte 0 of the Bar Code Symbol Data (BSA) structure defined by the BCOCA
architecture.
Bit 4 Reserved; should be zero
Bit 5 Bar code symbol suppression
This flag specifies whether or not the bar code symbol is presented, as
follows:
Value Description
B'0' The bar code symbol is presented.
B'1' Presentation of the bar code symbol is suppressed. This can be used
to print just the HRI. If both bit 0 and bit 5 are B'1' or the bar code
does not support HRI, nothing is presented for this bar code object.
When bit 5 = B'1', LND, RCD, or XMD parameters IPos and BPos
specify the character reference point for the first character of the HRI.
Not all BCOCA receivers support suppression of the bar code symbol;
receivers that do not support this optional function ignore bit 5.
Bit 6 This flag is defined for AFP Line Data. It identifies the desired method of
handling trailing blanks in the bar code data; for some symbologies, the
resulting data length is used to adjust the bar code type and modifier to match
the resulting data length. The PageDef supports fixed-length fields for data
that is to be bar encoded. Since some bar codes allow variable-length data,
these fixed-length fields often are padded on the right with blanks; these
blanks are often not intended to be included in the BCOCA object, particularly
for a bar code type that does not allow blanks. This flag identifies how these
trailing blanks should be handled when a BCOCA bar code object is built from
the line data and PageDef information.
It is used as follows:
Value Description
B'0' T
railing blanks in the bar code data are not suppressed.
B'1' All trailing blanks in the bar code data are suppressed and the bar
code type and modifier are adjusted to match the resulting data
length.
When the flag = B'1', the bar code data is first adjusted by suppressing trailing
blanks and then the bar code type and modifier is adjusted based on the
resulting length as follows:
• If the user specified an EAN bar code type (X'08', X'09', X'16', or X'17'), [LINEDATA-5-251]
truncate the data and set the bar code type and modifier based on the
resulting data length:
Resulting Data
Length
Bar Code Type Bar Code Modifier
2 X'16'—two-digit supplemental X'00' [LINEDATA-5-252]
5 X'17'—five-digit supplemental X'00' [LINEDATA-5-253]
7 X'08'—EAN-8 X'00' [LINEDATA-5-254]
12 X'09'—EAN-13 X'00' [LINEDATA-5-255]


Resulting Data
Length
Bar Code Type Bar Code Modifier
14 X'16'—two-digit supplemental X'01' [LINEDATA-5-256]
17 X'17'—five-digit supplemental X'01' [LINEDATA-5-257]
Any other value Error
• If the user specified a UPC bar code type (X'03', X'05', X'06', or X'07'), [LINEDATA-5-258]
truncate the data and set the bar code type and modifier based on the
resulting data length:
Resulting Data
Length
Bar Code Type Bar Code Modifier
2 X'06'—two-digit supplemental X'00' [LINEDATA-5-259]
5 X'07'—five-digit supplemental X'00' [LINEDATA-5-260]
10 X'05'—UPC version E X'00' [LINEDATA-5-261]
11 X'03'—UPC version A X'00' [LINEDATA-5-262]
12 X'06'—two-digit supplemental X'02' [LINEDATA-5-263]
13 X'06'—two-digit supplemental X'01' [LINEDATA-5-264]
15 X'07'—five-digit supplemental X'02' [LINEDATA-5-265]
16 X'07'—five-digit supplemental X'01' [LINEDATA-5-266]
Any other value Error
• If the user specified a POSTNET bar code type (X'18'), truncate the data [LINEDATA-5-267]
and set the bar code type and modifier based on the resulting data length:
Resulting Data
Length
Bar Code Type Bar Code Modifier
5 X'18'—POSTNET X'00' [LINEDATA-5-268]
9 X'18'—POSTNET X'01' [LINEDATA-5-269]
11 X'18'—POSTNET X'02' or X'04' [LINEDATA-5-270]
Any other value X'18'—POSTNET X'03'
Note: Since both the X'02' and X'04' modifiers have the same length, the
processor of the PageDef cannot always determine which modifier is
desired if the data is truncated to match. Therefore, the following rule
is used to set the modifier:
– If the original modifier requested in the triplet is not X'04' and the
resultant length of the field is 11 bytes, modifier X'02' is used as the
modifier for the generated bar code.
• If the user specified an Intelligent Mail [LINEDATA-5-271]
® Barcode type (X'22'), truncate the
data and set the bar code type and modifier based on the resulting data
length:
Resulting Data
Length
Bar Code Type Bar Code Modifier
20 X'22'—Intelligent Mail Barcode X'00' [LINEDATA-5-272]
25 X'22'—Intelligent Mail Barcode X'01' [LINEDATA-5-273]


Resulting Data
Length
Bar Code Type Bar Code Modifier
29 X'22'—Intelligent Mail Barcode X'02' [LINEDATA-5-274]
31 X'22'—Intelligent Mail Barcode X'03' [LINEDATA-5-275]
Any other value error
• If the user specified any other bar code type, use the user-specified bar [LINEDATA-5-276]
code type and modifier.
Bit 7 Reserved; should be zero
BCWdth Specifies the desired symbol width for the entire bar code symbol in L-units (not including the
quiet zone)
This parameter has the same syntax and semantics as bytes 10–11 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
Note: This is an optional parameter that is not supported by all BCOCA receivers; this
parameter is ignored by products that do not support this function.
BCtype Indicates the type of bar code symbol to be generated
This parameter has the same syntax and semantics as byte 12 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
BCMod Gives additional processing information about the bar code symbol to be generated
This parameter has the same syntax and semantics as byte 13 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
FntLID Specifies the local ID of a font used to render the HRI and to provide the code point to bar
code character mappings
This parameter has the same syntax and semantics as byte 14 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture. The value X'FF' specifies the BCOCA
default font. Any other value needs to be mapped with a Map Coded Font (MCF) or Map Data
Resource (MDR) structured field in the AEG of the Data Map.
Color Specifies the color in which the bars of the bar code symbol and the HRI are to be presented
when a Color Specification (X'4E') triplet is not specified
This color may also be used if the X'4E' triplet is specified but extended colors for BCOCA is
not supported by the presentation device. This parameter has the same syntax and semantics
as bytes 15–16 of the Bar Code Symbol Descriptor (BSD) defined by the BCOCA architecture.
ModWdth Specifies the width in mils (thousandths of an inch) of the smallest defined bar code element
This parameter has the same syntax and semantics as byte 17 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
ElmtHt Specifies the height in L-units along the Y
bc axis of the bar code presentation space
The units of measure for the L-units are defined in the PGD structured field of the Active
Environment Group of the Data Map. This parameter has the same syntax and semantics as
bytes 18–19 of the Bar Code Symbol Descriptor (BSD) defined by the BCOCA architecture.
Mult Specifies a value that, when multiplied by the element height, yields the total bar and space
height presented
This parameter has the same syntax and semantics as byte 20 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
WE:NE Specifies the ratio of the wide-element dimension to the narrow-element dimension when only
two different size elements exist, that is, for a two-level bar code [LINEDATA-5-277]


This parameter has the same syntax and semantics as bytes 21–22 of the Bar Code Symbol
Descriptor (BSD) defined by the BCOCA architecture.
Note: The last 14 bytes (bytes 4–17) in this triplet are optional as a group. That is, either they are all specified
or none are specified. If the descriptor ID is intended to match a previously-defined descriptor ID, these
bytes should not be specified. When present, byte 4 is identical in syntax and semantics to byte 0 of the
Bar Code Symbol Data (BSA) structure defined by the BCOCA architecture. Bytes 5–17 are identical in
syntax and semantics to bytes 10–22 of the Bar Code Symbol Descriptor (BSD) defined by the BCOCA
architecture, except for the font local ID parameter, which must be set to X'FF' in the triplet to specify the
device default font.


#### Resource Object Include (X'6C') Triplet
This is an optional triplet that identifies an overlay or page segment object to be presented on the page at a
specified position. Multiple Resource Object Include triplets may be specified on the same LND.
If the triplet identifies an overlay, the overlay name must be mapped with an MPO structured field in the AEG of
the Data Map. If the triplet identifies a page segment, the page segment may be mapped in the AEG with an
MPS structured field. If mapped, the page segment is downloaded and can
be used multiple times (this is
called a hard page segment). If it is not mapped, the page segment data is downloaded as part of the page
(this is called a soft page segment).
This triplet is not supported on a conditional processing LND, that is, an LND that specifies flag bit 11=B'1'. If
present, it is ignored.
Note: The Resource Object Include is a MO:DCA triplet. The following description documents its use in a Page
Definition and introduces parameter values that are only valid in this environment. For the formal
definition of this triplet in the MO:DCA architecture, see the Mixed Object Document Content
Architecture (MO:DCA) Reference. Note that when used on an LND, this triplet supports a negative
range for the object origin offset. Only a positive range is supported when the triplet is used in MO:DCA
data streams. Note also that when used on an LND, this triplet supports ObjType=X'5F' (page segment).
This value is not supported in MO:DCA data streams. Not all presentation services programs
support
this triplet.
### Triplet X'6C' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 17 or 19 | Length of the triplet | M [LINEDATA-5-278]|
| 1 | CODE | Tid | X'6C' | Identifies the Resource Object Include triplet | M [LINEDATA-5-279]|
| 2 | CODE | ObjType | X'DF', X'5F' | Object type | M [LINEDATA-5-280]|
| 3–10 | CHAR | ObjName |  | Name of the object | M [LINEDATA-5-281]|
| 11–13 | SBIN | IobjOset | -32,768 – +32,767 | Relative I-axis offset | M [LINEDATA-5-282]|
| 14–16 | SBIN | BobjOset | -32,768 – +32,767 | Relative B-axis offset | M [LINEDATA-5-283]|
| 17–18 | CODE | ObOrent |  | The overlay's X axis rotation | O [LINEDATA-5-284]|

### Triplet X'6C' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Resource Object Include triplet
ObjType Specifies the object type
Value Description
X'DF' Overlay object
X'5F' Page segment object
ObjName Specifies the object name [LINEDATA-5-285]


IobjOset Relative I-axis offset
Relative offset of the object origin along the I axis of the current text (I,B) coordinate system,
measured from the current LND position using the page measurement units specified in the
PGD.
BobjOset Relative B-axis offset
Relative offset of the object origin along the B axis of the current text (I,B) coordinate system,
measured from the current LND position using the page measurement units specified in the
PGD.
ObOrent Only supported for ObjType X'DF' = Overlay object
Specifies the amount of rotation, about the overlay origin, of the overlay's X
ol axis relative to
the Xpg axis of the page. Valid values are the following:
Value Description
X'0000' 0 degrees
X'2D00' 90 degrees
X'5A00' 180 degrees
X'8700' 270 degrees
All others Reserved
The overlay Y axis rotation is always 90 degrees greater than the overlay X axis rotation.
Notes:
1. If this parameter is omitted, the architected default value for the overlay rotation is X'0000', [LINEDATA-5-286]
zero degrees.
2. When a page segment is included with this triplet, the ObOrent parameter is ignored and [LINEDATA-5-287]
the rotation of objects in the page segment is summarized in Table 10. [LINEDATA-5-288]


#### Additional Bar Code Parameters (X'7B') Triplet
This is an optional triplet that specifies additional parameters for non-linear bar code symbologies (for
example, 2D bar codes). This triplet may occur one or more times when a Bar Code Symbol Descriptor (X'69')
triplet is specified. If this triplet is specified more than once, the data from each triplet is concatenated in the
order it is received. If a X'69' triplet is not specified, the X'7B' triplet is ignored. If a X'7B' triplet is specified and
the X'69' triplet selects a linear bar code symbol, the results are unpredictable.
Offset Type Name Range Meaning M/O
0 UBIN Tlength 4–254 Length of the triplet, including Tlength M [LINEDATA-5-289]
1 CODE Tid X'7B' Identifies the Additional Bar Code Parameters [LINEDATA-5-290]
triplet
M
2 Reserved; should be zero M [LINEDATA-5-291]
3–n CODE AddParm Additional parameters for non-linear bar code
symbols
M
Triplet X'7B' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Additional Bar Code Parameters triplet
AddParm Specifies additional parameters for non-linear bar code symbols
These parameters are specific to the particular symbology and may include parameters like
symbol size (rows/columns) and processing mode.
Note: The data carried by a Bar Code Symbol Descriptor (X'69') triplet, with the exception of the SymbFlgs
parameter, is used to build the Bar Code Data Descriptor (BDD) structured field for the resulting bar
code object. The data carried by the Additional Bar Code Parameters triplet, along with the SymbFlgs
parameter, the LND, RCD, or XMD position, and the LND, RCD, or XMD data, is used to build a Bar
Code Data (BDA) structured field for the resulting bar code object. For a description of the contents of
the Bar Code Data structured field, see the Bar Code Object Content Architecture Reference. [LINEDATA-5-292]


#### Object Reference Qualifier (X'89') Triplet
The Object Reference Qualifier (X'89') triplet is used to specify whether the name of an object is retrieved from
the input data or retrieved using normal methods. If the name is to be retrieved from the input data, that name
overrides any ObjName field and any Fully Qualified Name (type X'01') triplet that would normally be used to
select an object. This triplet may occur once on an LND structured field.
When this triplet is specified, the following rules apply:
• If this triplet is specified more than once, only the first is used. [LINEDATA-5-293]
• This triplet applies to the first Resource Object Include (X'6C') triplet or Extended Resource Local Identifier [LINEDATA-5-294]
(X'22') triplet that follows on the LND or RCD.
• If this triplet is not followed by either the Resource Object Include triplet or the Extended Resource Local [LINEDATA-5-295]
Identifier triplet, then this triplet is ignored.
• The LND or RCD DataStrt/DataLgth fields or the RCD Fldno is used to select the name of the object. The [LINEDATA-5-296]
object name retrieved from the input is not presented as data on the page.
– If this triplet is followed by the Resource Object Include triplet, the ObjName parameter of the Resource
Object Include triplet is ignored.
– If this triplet is followed by the Extended Resource Local Identifier triplet, the ObjName parameter and the
Fully Qualified Name (FQN) triplet using FQNType X'01' of the Include Object (IOB) structured field pointed
to by the Extended Resource Local Identifier triplet are ignored.
Note: If the field of data specified by the DataStrt/DataL
gth or Fldno parameters does not exist in the input
record, then this Object Reference Qualifier (X'89') triplet and the Resource Object Include triplet or
the Extended Resource Local Identifier triplet that it applies to are ignored.
This triplet is not supported on conditional processing LNDs or RCDs, that is LNDs or RCDs that specify flag bit
11=B'1' if present, it is ignored. [LINEDATA-5-297]
### Triplet X'89' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 4 | Length of the triplet | M [LINEDATA-5-298]|
| 1 | CODE | Tid | X'89' | Identifies the Object Reference Qualifier triplet | M [LINEDATA-5-299]|
| 2 |  |  | 0 | Reserved | M [LINEDATA-5-300]|
| 3 | BITS | QualFlg |  | Object reference qualifier flags | M [LINEDATA-5-301]|

### Triplet X'89' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Object Reference Qualifier triplet
QualFlg Specifies object reference qualifier flags, as follows:
Bit Description
0 Object reference qualifier flags [LINEDATA-5-302]
B'0' Do not use selected input data to override object name
B'1' Use selected data to override object name
1–7 Reserved


Notes:
1. If this triplet is omitted, the architected default for QualFlg bit 0 is B'0'. [LINEDATA-5-303]
2. When the QualFlag bit 0 is B'1', the encoding of the object name obtained from the input [LINEDATA-5-304]
data is platform depende nt. It is the responsibility of the user to ensure that the encoding
of the input data is correct for the platform being used.
Implementation Note: For interchange in AFP environments, the name retrieved from the
input data by the LND or RCD:
• Must be no more than 8 characters long; if longer, some AFP print servers use only [LINEDATA-5-305]
the first 8 characters.
• Must follow the naming conventions used in AFP environments; see External [LINEDATA-5-306]
Resource Naming Conventions in the Mixed Object Document Content Architecture
(MO:DCA) Reference for a description.
• Must not contain platform-dependent library names or path names. [LINEDATA-5-307]
• Must be encoded using EBCDIC if the resource is mapped in the active environment [LINEDATA-5-308]
group.
• Must not use double-byte encoding. [LINEDATA-5-309]
• Must not contain any shift-in or shift-out characters. [LINEDATA-5-310]


#### Color Management Resource Descriptor (X'91') Triplet
The Color Management Resource Descriptor (X'91') triplet is a MO:DCA triplet. For the formal definition of this
triplet, see the Mixed Object Document Content Architecture (MO:DCA) Reference.
The Color Management Resource Descriptor triplet specifies the processing mode and scope for a Color
Management Resource (CMR). This triplet is mandatory when the LND references a Color Management
Resource (CMR) with the FQN type X'DE' triplet, in which case this triplet specifies the processing mode for
the CMR and must occur once for each FQN type X'DE' specified. It is ignored in all other cases. This triplet
must immediately follow the FQN type X'DE' triplet that specifies the CMR name. [LINEDATA-5-311]
### Triplet X'91' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 5 | Length of the triplet | M [LINEDATA-5-312]|
| 1 | CODE | Tid | X'91' | Identifies the Color Management Descriptor triplet | M [LINEDATA-5-313]|
| 2 |  |  | 0 | Reserved | M [LINEDATA-5-314]|
| 3 | CODE | ProcMode |  | Processing mode for the CMR | M [LINEDATA-5-315]|
| 4 | CODE | CMRScpe | X'01' | Scope of CMR | M [LINEDATA-5-316]|

### Triplet X'91' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Color Management Descriptor triplet
ProcMode Specifies the processing mode for the CMR
Valid values are the following:
Value Description
X'01' This CMR describes processing that has been done to a document
component; process the CMR as an audit CMR.
X'02' This CMR describes processing that is to be done to a document component;
process the CMR as an instruction CMR.
X'03'
This CMR defines a direct color conversion from an input color space to a
device output color space; process the CMR as a link CMR. This processing
mode is only valid for Link DL CMRs.
All others Reserved
CMRScpe Specifies the scope of the CMR when used inside a document
Valid values are the following:
Value Description
X'01' The scope of the CMR is a data object in this page.
All others Reserved


#### Concatenate Bar Code Data (X'93') Triplet
Architecture Note: The Concatenate Bar Code Data triplet is registered in the MO:DCA architecture as a
private-use triplet since it is used only in the PageDef object, which is not a MO:DCA object.
This is an optional triplet and may occur once. If this triplet is specified more than once, only the first will be
used. This triplet is only valid if the LND, RCD, or XMD also specifies a Bar Code Symbol Descriptor (X'69')
triplet. If the X'69' triplet is not specified, this triplet is ignored. When this triplet is present, it specifies that the
field of data selected by the LND, RCD,
or XMD and the Bar Code Symbol Descriptor triplet is to be collected
as part of a concatenation of fields. When complete, this concatenation will be used to generate one bar code
symbol. Each field of a concatenation must specify this triplet. For RCD and XMD, completion of a given
concatenated bar code symbol is defined as the end of the page or an RCD or XMD with the Start New Symbol
flag (bit 0 of CBCFlgs) set in the X'93' triplet being reused. For LND,
completion of a given concatenated bar
code symbol is defined as the end of the page (the Start New Symbol flag (bit 0 of CBCFlgs) has no effect
since an LND cannot be reused on a page).
When this triplet is used, the position parameters IPos and BPos of the LND, RCD, or XMD that selects the first
segment of the concatenation will be used to position the concatenated symbol. The first segment is the
segment selected with the lowest non-zero SegOrder value, or it is the first segment received when SegOrder
is not specified. All other LND, RCD,
or XMD position parameters will be ignored for the remaining segments.
This triplet is not supported on conditional processing LNDs, RCDs or XMDs (LNDs, RCDs or XMDs that
specify flag bit 11=B'1'). If the triplet is present, it is ignored. [LINEDATA-5-317]
### Triplet X'93' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 7 | Length of the triplet | M [LINEDATA-5-318]|
| 1 | CODE | Tid | X'93' | Identifies the Concatenate Bar Code Data triplet | M [LINEDATA-5-319]|
| 2 | BITS | CBCFlgs |  | Concatenation flags | M [LINEDATA-5-320]|
| 3–4 | UBIN | SymbID | X'0001'–X'FFFF' | Identifies a bar code symbol concatenation | M [LINEDATA-5-321]|
| 5–6 | UBIN | SegOrder | X'0001'–X'FFFF', X'0000' | Order of concatenation | M [LINEDATA-5-322]|

### Triplet X'93' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Concatenate Bar Code Data triplet
CBCFlgs Specifies additional controls
Bit 0 Start New Symbol if reused (RCD and XMD only)
Value Description
B'0' Continue adding to symbol already started
B'1' Start a new symbol
Bits 1–7 Reserved; should be B'0000000'
Note: An RCD or XMD is reused when the same RCD or XMD is selected more than once on
a single page.


SymbID Specifies the ID of a bar code symbol
The symbol ID is used to identify multiple pieces of data to be collected, concatenated, and
presented as a single bar code symbol. The SymbID of this triplet, combined with the DescID
field of the Bar Code Symbol Descriptor (X'69') triplet and the orientation specified in the LND,
RCD,
or XMD are used to determine which fields of bar code data are to be concatenated. If
the DescID, orientation, color specification triplets, CMRs (order must match as well as names
and processing modes),
and SymbID matches a previously selected DescID, orientation, color
specification triplets, CMRs, and SymbID combination, then the data selected with this LND,
RCD, or XMD is to be concatenated with the data selected previously. If a match is not found,
a new symbol will be started. The valid range for the ID is X'0001'–X'FFFF'.
Note: When the Bar Code Symbol Descriptor (X'69') triplet requests that trailing blanks are to
be suppressed (byte 4 bit 6), the trailing blanks of each piece of data collected will have
its trailing blanks suppressed. The remaining data will then be added to the
concatenation. The bar code type and modifier will then be adjusted (if necessary)
according to the length of the entire concatenation of data.
SegOrder Specifies the order in the concatenation to place this segment of the bar code data
The segments will be concatenated in ascending order using the value specified in this
parameter. If more than one bar code data segment specifies the same SegOrder value, the
data will be concatenated in the order it is received.
A value of X'0000' in this parameter means the order is not specified. The segments will be
concatenated in the order they are received.
All segments in a concatenation must specify the same type of order. If one specifies X'0000',
all segments must specify X'0000'. If one specifies a value from X'0001' through X'FFFF', all
segments must specify a value from X'0001' through X'FFFF'. It is an error to mix segment
order types.


## Record Descriptor (RCD)
The Record Descriptor structured field contains information, such as record position, text orientation, font
selection, field selection, and conditional processing identification, used to format line data that consists of
records tagged with record identifiers.
Note: The RCDs in a Data Map are numbered sequentially, starting with 1. [LINEDATA-5-323]
### RCD (X'D3A68D') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A68D', Flags (1B), Reserved X'0000' [LINEDATA-5-324]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–9 | CHAR | RecID |  | Record descriptor ID | M [LINEDATA-5-325]|
| 10 | CODE | RecType | X'00'–X'03' | Record Type | M [LINEDATA-5-326]|
| 11–13 | BITS | RCDFlgs |  | RCD flags | M [LINEDATA-5-327]|
| 14 |  |  | 0 | Reserved | M [LINEDATA-5-328]|
| 15–16 | UBIN | IPos |  | Inline Position | M [LINEDATA-5-329]|
| 17–18 | UBIN/SBIN | BPos |  | Baseline position | M [LINEDATA-5-330]|
| 19–22 | CODE | TxtOrent |  | Text (I,B) Orientation | M [LINEDATA-5-331]|
| 23 | CODE | FntLID |  | Primary font local ID | M [LINEDATA-5-332]|
| 24–25 | UBIN | FLDrcd |  | Field RCD Pointer | M [LINEDATA-5-333]|
| 26–33 | CHAR | SupName |  | Suppression token name | M [LINEDATA-5-334]|
| 34 | CODE | SOLid |  | Shift-out font local ID | M [LINEDATA-5-335]|
| 35–38 | UBIN | DataStrt |  | Data start position | M [LINEDATA-5-336]|
| 39–40 | UBIN | DataLgth |  | Data length | M [LINEDATA-5-337]|
| 41–42 | UBIN | CONDrcd |  | Conditional Processing RCD Pointer | M [LINEDATA-5-338]|
| 43 | CODE | SubpgID |  | Subpage ID (always X'00' for RCDs) | M [LINEDATA-5-339]|
| 44–45 | CODE | CCPID |  | CCP Identifier | M [LINEDATA-5-340]|
| 46–47 | UBIN | Pgno |  | Starting page number | M [LINEDATA-5-341]|
| 48–49 | UBIN | ESpac |  | End Space | M [LINEDATA-5-342]|
| 50 | CODE | Align |  | Field Alignment | M [LINEDATA-5-343]|
| 51–52 | CODE | FldDelim |  | Field Delimiter | M [LINEDATA-5-344]|
| 53–54 | UBIN | Fldno |  | Field Number | M [LINEDATA-5-345]|
| 55–56 | UBIN | AdBIncr |  | Additional baseline increment | M [LINEDATA-5-346]|
| 57–69 |  |  | 0 | Reserved | M [LINEDATA-5-347]|
| 70–n |  | Triplets |  | Triplets | O [LINEDATA-5-348]|

RCD Semantics for triplet applicability. O [LINEDATA-5-349]
### RCD Semantics
The RCD uses many parameters that are defined for the LND. The definition of such parameters is deferred to
the LND. When such definitions are applied to the RCD, the term “LND” should be read as “RCD” and the byte
offsets of the parameters should be adjusted to the RCD.
There are three types of RCDs:
• Record RCDs, which define the processing for an input record or define a default page header or trailer [LINEDATA-5-350]
• Field RCDs, which define the processing for an input field or specify constant text or graphics [LINEDATA-5-351]
• Conditional Processing RCDs, which specify the Conditional Processing associated with an input record [LINEDATA-5-352]
The Field RCDs and Conditional Processing RCDs associated with a Record RCD are chained to that RCD
using RCD number pointers. An RCD is assumed to be a Record RCD if neither the Field RCD nor the
Conditional Processing RCD bits are on in the RCDFlgs byte.
RecID Record Descriptor ID
The identifier to be used with this RCD. This field in the RCD is used only if this RCD does not
contain a Fully Qualified Name (FQN) X'02' triplet type X'01'. The FQN type X'01' triplet is [LINEDATA-5-353]


used to extend the identifier to a range of 1 to 250 bytes instead of 10 bytes. When an input
record is processed with a Data Map containing RCDs, the first 1 to 250 data byte positions in
the input record are assumed to contain a Record Descriptor ID and the Data Map's Record
RCDs are searched to find a matching Record Descriptor ID. (A CC byte, if used with the input
data stream, is not considered part of the input record.) When a match is found, that RCD is
used to format the input record. If a matching RCD is not found, an error message is
generated. Except for default Page Headers, default Page Trailers, Field RCDs,
and
Conditional Processing RCDs, all of the RCDs in a Data Map have a unique Record
Descriptor ID. The Record Descriptor ID is set to all zeros for default Page Headers (one per
Data Map), default Page Trailers (one per Data Map), Field RCDs, and Conditional
Processing RCDs. If this RCD contains a FQN type X'01' triplet, this 10 byte Record
Descriptor ID field is set to X'FF ...FF'
.
Notes:
1. To be able to find a matching Record Descriptor ID, the encoding of the identifier specified [LINEDATA-5-354]
in this parameter or in the FQN type X'01' triplet must match the encoding of the input
data.
2. If the FQN type X'01' triplet is used, all record type RCD structured fields must use the [LINEDATA-5-355]
FQN X'01' triplet.
3. If the FQN type X'01' triplet is used, the names specified for all the FQN triplets must be [LINEDATA-5-356]
the same length. Blanks may be used to pad the name. The encoding used for the blanks
must match the encoding of the data used for the name.
RecType Record Type
This parameter is ignored on RCDs other than Record RCDs.
Value Description
X'00' Body RCD
This RCD does not have any special header or trailer properties associated with it and
is used to format any input records with a matching Record ID.
X'01' Page Header RCD
This RCD is used to automatically print a header (such as the current customer name)
at the beginning of each new page. The baseline position of this RCD can be
anywhere on a logical page and can be specified as relative. If an input record is
received with a matching Record Descriptor ID, that input record is not presented on
receipt but is saved as the active page header record. If a default Record Descriptor
ID is specified in a Page Header RCD, it is assumed to be a default Page Header
RCD (only one default Page Header RCD can be specified in a Data Map and no
input record data is processed with a default RCD). See “Logical Page Eject
Processing”
for page header and new page processing details.
Note: Once a Page Header RCD is processed, the header record is saved for the
duration of the job by the presentation services program. Whenever the same
Data Map is re-invoked for that job, this saved header record is always
presented with each page generated with the Data Map.
X'02' Page Trailer RCD
This RCD is used to automatically print a trailer (for example, a footnote or page
number) at the end of each page. The baseline position of this RCD can be anywhere
on a logical page and can be specified as relative. If an input record is received with a
matching Record Descriptor ID, that input record is not presented on receipt but is
saved as the active page trailer record. If a default Record Descriptor ID is specified in
a Page Trailer RCD, it is assumed to be a default Page Trailer RCD (only one default
Page Trailer RCD can be specified in a Data Map, and no input record data is [LINEDATA-5-357]


processed with a default RCD). See “Logical Page Eject Processing” for
page trailer and new page processing details.
Note: Once a Page Trailer RCD is processed, the trailer record is saved for the
duration of the job by the presentation services program. Whenever the same
Data Map is re-invoked for that job, this saved trailer record is always
presented with each page generated with the Data Map.
X'03' Group Header RCD
This RCD is used to automatically print a group header (for example, column
headings for a group of data records) on a page. Note that the group header is not
actually printed and causes no action until a Body RCD with Group Indicator
(RCDFlgs bit 19) set to B'1' is processed for the page. The baseline position of this
RCD can be specified as relative. If an input record is received with a matching
Record Descriptor ID, that input record is saved as the active group header record
and then presented. If that input record or RCD causes a page eject, that input record
is used as the active group header record for the new page. See “Logical Page Eject
Processing”
for active group header and new page processing details.
Note: Once a Group Header RCD is processed and is still active when leaving the
Data Map, the group header record is saved by the presentation services
program. Whenever the same Data Map is re-invoked, this saved group header
record is presented again if the first body record after re-invoking the Data Map
selects a Body RCD that has the Group Indicator on.
RCDFlgs Flag bits
Bits 0–5 For a definition of these flag bits see “Line Descriptor (LND)”. LND
flag bit 0–1 is reserved in the RCD.
Bit 6 Field RCD
Value Description
B'0' If this bit and bit 11 are both B'0', this is a Record RCD.
B'1' This is a Field RCD. If both this bit and bit 11 are on, it is an error.
Bits 7–15 For a definition of these flag bits see “Line Descriptor (LND)”. LND
flag bits 9–10 are reserved in the RCD. The color for the data presented by
this RCD is always the color specified by the Color Specification (X'4E')
triplet, if specified. If this triplet is not specified, the data is presented in the
presentation process default color.
Bit 16 New Page
Value Description
B'0' This bit value has no effect on this RCD.
B'1' A logical page eject should be executed before presenting any data
for this RCD. If this is a header or trailer RCD, the print position is
moved to the start of a new page before this header or trailer
becomes the active header or trailer. (See “Logical Page Eject
Processing”.) This bit is ignored on RCDs other than
Record RCDs.
Bit 17 Print Page Number
This flag indicates whether a page number should be generated on the
current page before the page eject is performed.
Value Description
B'0' This bit has no effect on this RCD.
B'1' A
page number is generated. [LINEDATA-5-358]


The (I,B) position of the RCD indicates the position of the page
number, which is maintained by the presentation process. The page
number is rendered with the font selected by the FntLID parameter.
This bit is ignored on RCDs other than Field RCDs.
Note: The FntLID parameter must be mapped to a font global
identifier with an MCF structured field in the AEG of the Data
Map. For the page formatter to generate the page number
code points without accessing the font object, it needs to know
the encoding scheme (EBCDIC, ASCII, or Unicode) for the font.
This information is specified on the MCF with an Encoding
Scheme ID (X'50') triplet. Only single-byte EBCDIC, doublebyte EBCDIC, single-byte ASCII, and Unicode (UTF-16)
encodings are supported for printing page numbers. The code
points used for the page numbers are X'F0'–X'F9', X'42F0'–
X'42F9', X'30'–X'39', and X'0030'–X'0039', respectively. If the
encoding scheme is not specified on the MCF , single-byte
EBCDIC encoding is assumed.
Bit 18 Reset Page Number
Value Description
B'0' This bit has no effect on this RCD.
B'1' The current page number is reset to the value specified in the Pgno
parameter. This bit is ignored on RCDs other than Field RCDs.
Bit 19 Group Indicator
Value Description
B'0' The input data associated with this RCD is not part of a group. If a
group header record is active, it is deactivated and any saved group
header input record is discarded.
B'1' This flag indicates that the existing group header should continue to
be saved and used for subsequent pages. This bit is ignored on
RCDs other than Record RCDs.
Bit 20 Field Delimiter Size
Value Description
B'0' Delimiter is 1 byte and is specified in the second byte of the field.
B'1' Delimiter is 2 bytes.
Bit 21 Use Record ID
This bit s
elects the Record ID as the data field to be used for presentation.
Value Description
B'0' Do not select the Record ID.
B'1' Select the Record ID.
This function is restricted to Field RCDs; it is ignored on all other RCDs.
Bits 22–23 Reserved; should be B'00'
IPos Inline Position
See “Line Descriptor (LND)”. [LINEDATA-5-359]


BPos Baseline Position
See “Line Descriptor (LND)”.
• Relative Baseline Position for Record and Field RCDs [LINEDATA-5-360]
If the baseline position is relative, the offset is measured as follows:
– For Page Header RCDs, the offset is relative to the top of the page.
– For Group Header and Body RCDs, the offset is relative to the last Group Header or Body
RCD processed; if there is none, it is relative to the top margin.
– For Field RCDs, it is relative to the last Field or Body RCD that was processed for print
(whether or not data is printed).
– For Page Trailer RCDs, it is relative to the last Record RCD processed; if there is none, it
is relative to the top margin.
Note that the actual location of “top” and “top margin” on a page is affected by the text
orientation; see “Margin Definition (X'7F') Triplet”.
• Overflow Processing for a Record RCD [LINEDATA-5-361]
If the specified baseline position is relative, this Record RCD and any Field RCDs that are
part of this Record RCD are scanned to determine the resulting absolute baseline position at
the end of processing this Record RCD. This computed baseline position is checked to see
if it overflows into the bottom margin area. If not, this Record RCD is processed with the
current input record. If it overflows into the bottom margin, a logical page eject is executed.
See “Logical Page Eject Processing”
for page eject processing details. If the
data in a single Record RCD (with its chained Field RCDs) is too large to fit within the top
and bottom margins on a page, an error is generated. Note that the actual location of “top
margin” and “bottom margin” on a page is affected by the text orientation; see “Margin
Definition (X'7F') Triplet”.
TxtOrent Text (I,B) Orientation
See “Line Descriptor (LND)”.
FntLID Primary Font Local Identifier
See “Line Descriptor (LND)”.
FLDrcd RCD number of a Field RCD
A non-zero value in this parameter on a Record RCD indicates that field processing is to be
performed on the current input data record. This parameter specifies the RCD number of a
Field RCD. Multiple Field RCDs can be chained to a Record RCD using this parameter. The
last Field RCD in the chain has a value of X'0000' in this parameter.
SupName Suppression token name
See “Line Descriptor (LND)”.
SOLid Shift-out Font Local Identifier
See “Line Descriptor (LND)”.
DataS
trt Data Start Position
See “Line Descriptor (LND)”.
DataL
gth Data Length
See “Line Descriptor (LND)”.
CONDrcd RCD number of a Conditional Processing RCD
A non-zero value in this parameter on a Record RCD means that conditional processing is to
be performed on the current input data record. This parameter specifies the relative RCD [LINEDATA-5-362]


number of a Conditional Processing RCD. Multiple Conditional Processing RCDs can be
chained to a Record RCD using this parameter. The last Conditional Processing RCD in the
chain has a value of X'0000' in this parameter. A Field RCD has a value of X'0000' in this
parameter.
See “Line Descriptor (LND)”.
SubpgID Subpage Identifier
See “Line Descriptor (LND)”.
CCPID CCP Identifier
See “Line Descriptor (LND)”.
Pgno Page Number
This parameter specifies the starting page number that is used when RCDFlgs specifies reset
page number. This parameter is ignored on RCDs other than Field RCDs.
ESpac End Space
Value Description
X'0000' No check is made for End Space.
All others A check is initiated to verify that the remaining body space on the page
(distance between the starting print position of this RCD and the bottom
margin area) is greater than or equal to the baseline value specified in this
parameter. If the remaining body space is less than the value specified, a
logical page eject is executed. This can be used, for example, on a Group
Header RCD (specifying space for the first data record of the group) to ensure
that a group header does not print at the end of a page without the first data
record of the group. This parameter is ignored on a Page Header or Page
Trailer RCD and on RCDs other than Record RCDs. Note that the actual
location of the bottom margin area is affected by the text orientation; see
“Margin Definition (X'7F') Triplet”.
Align Field Alignment
Value Description
X'00' The field is left aligned to the position specified in IPos.
X'01' The field is right aligned to the position specified in IPos. This parameter is ignored for
Field RCDs that specify a Bar Code Symbol Descriptor
(X'69') triplet or Graphics
Descriptor (X'7E') triplet and on RCDs other than Field RCDs.
FldDelim Field Delimiter
This is a 1-byte or a 2-byte parameter, depending on the encoding used for the field. RCDFlgs
bit 20 indicates the size of this parameter. A 1-byte parameter is specified in the second byte
of the field. A value of X'0000' indicates that this parameter is not specified. For Record RCDs,
this parameter specifies a 1-byte or 2-byte code that delimits all of the input record fields
(excluding the 10 byte Record Format ID) used with this RCD and any Field and Conditional
Processing RCDs chained to this RCD. The delimiter is specified at the end of the field it
delimits. A delimiter may be specified after the Record ID but is ignored. A Field Number
parameter is used rather than DataStrt
and DataLgth parameters to specify the location of
input fields on Conditional Processing and Field RCDs that are chained to this Record RCD.
DataStrt and DataLgth parameters are still used to select bytes in the field or in Fixed Data
Text. This parameter is ignored on all non-Record RCDs. For comparisons, any input record
fields used with a CCP are assumed to be padded on the right out to the CCP Comparison
String Length.


Fldno Field Number
Specifies the number of the field to be processed. The first field (field number 1) in the record
is followed by the first delimiter; the second field is followed by the second delimiter; and so
on. Any delimiter specified after the Record ID is ignored. The DataStrt and DataLgth
parameters are applied to the selected field to select specific bytes in the field for processing.
Specifying DataStrt = X'00000000' and DataLgth = X'FFFF' selects the whole field for
processing. This parameter is used only if the Record RCD that this Field or Conditional
Processing RCD is chained to specifies a Field Delimiter (other than X'00').
AdBIncr Additional baseline increment
Specifies the additional baseline increment that is to be added to the current baseline position
after a group header or data record is presented. This parameter is only processed for Group
Header RCDs, and for Body RCDs that are record RCDs. It is ignored on all other RCDs. Note
that this increment is not used when positioning MO:DCA objects with respect to the current
RCD in mixed mode.
Triplets See the following:
“Fully Qualified Name (X'02') Triplet” for FQN type X'01' (Replace First GID
Name)
“Fully Qualified Name (X'02') Triplet” for FQN type X'DE' (Data Object External
Resource Reference)
“Extended Resource Local Identifier (X'22') Triplet”
“Color Specification (X'4E') Triplet”
“Bar Code Symbol Descriptor (X'69') Triplet”
“Resource Object Include (X'6C') Triplet”
“Additional Bar Code Parameters (X'7B') Triplet”
“Graphics Descriptor (X'7E') Triplet”
“Object Reference Qualifier (X'89') Triplet”
“Color Management Resource Descriptor (X'91') Triplet”
“Concatenate Bar Code Data (X'93') Triplet ”
“Rendering Intent (X'95') Triplet” [LINEDATA-5-363]


Logical Page Eject Processing
A logical page eject can be caused by the following:
• Any Record RCD with a specification of New Page [LINEDATA-5-364]
• A relative baseline overflow [LINEDATA-5-365]
The overflow is caused by a Body or Group Header RCD with a relative baseline position value that when
processed against the current input record causes an overflow of the current print position into the bottom
margin (see Baseline Position). Note that the actual location of “bottom margin” is affected by
the text orientation; see “Margin Definition (X'7F') Triplet”.
• A Data Map change or Medium Map change, or, in mixed-mode, a Begin Document or Begin Page [LINEDATA-5-366]
structured field
When a logical page eject occurs, the following actions are taken in the following order.
For the current page:
1. If this is the start of a line data document (no previous page ejects, group header records, or body records [LINEDATA-5-367]
have been processed with this PageDef), no header or trailer processing is performed.
2. If an active page header record was in effect prior to this RCD, that record is presented on the current page [LINEDATA-5-368]
using the matching RCD. Otherwise, if the active Data Map contains a default Page Header RCD, that
RCD is used to present a page header.
3. If an active page trailer record was in effect prior to this RCD, that record is presented on the current page [LINEDATA-5-369]
using the matching RCD. Otherwise, if the active Data Map contains a default Page Trailer RCD, that RCD
is used to present a page trailer.
For the new page:
1. The current print position is moved to the top of the new page. If the Data Map is changed, the new Data [LINEDATA-5-370]
Map's Margin Definition and RCDs are used for subsequent processing.
2. The baseline position is offset from the top of the new page by the top margin. [LINEDATA-5-371]
3. If the RCD causing the page eject is a Page Header, Group Header, or Page Trailer RCD, the input record [LINEDATA-5-372]
causing the page eject is saved as the active page header, group header ,
or page trailer record.
4. If an active group header record exists for this Data Map, that record is presented on the new page using [LINEDATA-5-373]
the matching Record RCD. Note that the group header is not actually printed and causes no action until a
Body RCD with Group Indicator (RCDFlgs bit 19) set to B'1' is processed for the page. If the RCD specifies
relative positioning, the baseline position of the RCD is offset from the top of the page by the top margin
plus the RCD BPos value.
5. If the page eject was caused by a Body RCD, the input record causing the page eject is presented on the [LINEDATA-5-374]
new page using the RCD referenced by the record. If the RCD specifies relative positioning and is
preceded on the page by a group header, the baseline position is relative to the last printed line of the
group header. If the RCD specifies relative positioning and is not preceded on the page by a group header,
the baseline position of the RCD is offset from the top of the page (0 position on the B axis) by the top
margin plus the RCD BPos value.
Note that the actual location of “top of page” and “top margin” is affected by the text orientation; see “Margin
Definition (X'7F') Triplet”. [LINEDATA-5-375]


### RCD Triplets
Fully Qualified Name (X'02') Triplet
The Fully Qualified Name (X'02') triplet is a MO:DCA triplet. For the formal definition of this triplet, see the
Mixed Object Document Content Architecture (MO:DCA) Reference.
This triplet is optional and may occur once. If this triplet is specified more than once, only the first is used. The
Fully Qualified Name type that may appear is X'01' — Replace First GID Name. This GID overrides the Record
Descriptor's RecID field and is used as the Record Descriptor ID. [LINEDATA-5-376]
### Triplet X'02' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 5–254 | Length of the triplet | M [LINEDATA-5-377]|
| 1 | CODE | Tid | X'02' | Identifies the Fully Qualified Name triplet | M [LINEDATA-5-378]|
| 2 | CODE | FQNType |  | FQN type | M [LINEDATA-5-379]|
| 3 | CODE | FQNFmt | X'00' | GID format is character string | M [LINEDATA-5-380]|
| 4–n |  | FQName |  | GID of the Record Descriptor | M [LINEDATA-5-381]|

### Triplet X'02' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Fully Qualified Name triplet
FQNType Specifies how the fully qualified name is to be used
Value Description
X'01' This GID replaces the RecID parameter in the structured field.
Note: The GID (Global Identifier) that overrides the ten-byte RecID field has
the same semantics as the ten-byte RecID parameter.
All others Reserved
FQNFmt Specifies the format of the Global Identifier
Value Description
X'00' The GID is a character-encoded name, which means the data type is CHAR.
All others Reserved
FQName Contains the Global Identifier (GID) to be used to override the RecID parameter
Note: To be able to find a matching Record Descriptor ID, the encoding of the identifier
specified in this parameter must match the encoding of the input data. [LINEDATA-5-382]


Fully Qualified Name (X'02') Triplet
This triplet is optional and may occur one or more times when a Bar Code Symbol Descriptor (X'69') triplet or a
Graphics Descriptor (X'7E') triplet is specified on the RCD. The Fully Qualified Name type that may appear is
X'DE'—Data Object External Resource Reference. The FQN triplet specifies the external identifier of a Color
Management Resource (CMR) object that is used for the Bar Code object or Graphics object being generated.
The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The
identifier is a character-encoded name that
must be specified using FQNFmt = X'00'. The encoding for the
external identifier of the CMR must be UTF-16BE.
See “Fully Qualified Name (X'02') Triplet”. [LINEDATA-5-383]


Extended Resource Local Identifier (X'22') Triplet
This triplet is optional. It may occur one or more times to reference an IOB structured field in the PageDef. This
triplet is ignored if the Graphics Descriptor (X'7E') triplet is specified on the RCD.
See “Extended Resource Local Identifier (X'22') Triplet”. [LINEDATA-5-384]


Color Specification (X'4E') Triplet
This is an optional triplet that specifies the color for text processed by this RCD, for a bar code generated by
this RCD, and for graphics generated by this RCD. If this triplet is not specified, the record is presented in the
presentation process default color. On an RCD that processes text or generates a bar code, this triplet may
occur once. If this triplet is specified more than once, only the first is used. On a Field RCD with a Graphics
Descriptor (X'7E')
triplet, this color triplet can be specified once or twice.
• If it is specified once, the color applies to all graphics constructs. [LINEDATA-5-385]
• If it is specified twice, then: [LINEDATA-5-386]
– If the RCD generates a graphics area, such as a full arc or box, the first color applies to the fill pattern of
the graphics area and the second color applies to the boundary lines of the graphics area (if drawn).
– If the RCD generates a graphics line, the second color overrides the first color and applies to the graphics
line.
• If it is specified more than twice, only the first two are used. [LINEDATA-5-387]
If this RCD generates the start of a graphic primitive that is completed by a succeeding RCD, the colors
specified on the “start” RCD determine the color for the complete primitive.
With this triplet, a color is specified by selecting a color space, an encoding for the components of the color
value in that space, and a color value. The color spaces supported are:
• RGB
• CMYK
• Highlight [LINEDATA-5-388]
• CIELAB [LINEDATA-5-389]
• Standard OCA color space [LINEDATA-5-390]
The selected encoding defines the number of bits used to specify each component. For example, with the RGB
color space, one supported encoding is 8 bits per component, which maps the component intensity range 0 to
1 to the binary values 0 to 255. The color value specifies the color. With the RGB color space and an 8 bit per [LINEDATA-5-391]
component encoding, the color value (255,255,255) specifies full intensity for each component, which defines
the color white.
The Color Specification triplet is a MO:DCA triplet. For the formal definition of this triplet, see the Mixed Object
Document Content Architecture (MO:DCA) Reference. [LINEDATA-5-392]


Bar Code Symbol Descriptor (X'69') Triplet
This is an optional triplet. It may occur once. If this triplet is specified more than once, only the first is used. This
triplet is ignored if the Graphics Descriptor (X'7E') triplet is specified on the RCD.
See “Bar Code Symbol Descriptor (X'69') Triplet”. Note that the LND/RCD parameters used by
this triplet may be at different offsets in the LND and RCD. [LINEDATA-5-393]


Resource Object Include (X'6C') Triplet
This is an optional triplet that identifies an overlay or page segment object to be presented on the page at a
specified position. Multiple Resource Object Include triplets may be specified on the same RCD. This triplet is
ignored if the Graphics Descriptor (X'7E') triplet is specified on the RCD.
See “Resource Object Include (X'6C') Triplet”. [LINEDATA-5-394]


Additional Bar Code Parameters (X'7B') Triplet
This is an optional triplet that may occur one or more times when a Bar Code Symbol Descriptor (X'69') triplet
is specified. If this triplet is specified more than once, the data from each triplet is concatenated in the order it is
received.
See “Additional Bar Code Parameters (X'7B') Triplet”. [LINEDATA-5-395]


#### Graphics Descriptor (X'7E') Triplet
Architecture Note: The Graphics Descriptor triplet is registered in the MO:DCA architecture as a private-use
triplet because it is used only in the PageDef object, which is not a MO:DCA object.
This is an optional Field RCD triplet. It may occur once. If this triplet is specified more than once, only the first is
used. Text input and fixed data text are ignored on a Field RCD that specifies a Graphics Descriptor triplet.
When present, the Graphics Descriptor triplet specifies the generation of a graphics primitive. The triplet may
specify the complete primitive, or the start of the primitive, or the end of the primitive.
This triplet is ignored on RCDs other than Field RCDs. This triplet specifies primitives and their parameters as
defined by the AFP GOCA architecture. For more information, see the Graphics Object Content Architecture
for Advanced Function Presentation Reference
. Not all presentation services programs support this triplet. [LINEDATA-5-396]
### Triplet X'7E' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 20 or 35 | Length of the triplet | M [LINEDATA-5-397]|
| 1 | CODE | Tid | X'7E' | Identifies the Graphics Descriptor triplet | M [LINEDATA-5-398]|
| 2 | CODE | ParmSpc | X'01'–X'03' | Parameter specification | M [LINEDATA-5-399]|
| 3–4 | UBIN | Graphid | X'0000'–X'FFFE' | ID for matching Start/End graphic pairs | M [LINEDATA-5-400]|
| 5 | CODE | GrPrim | X'01'–X'05' | Graphics primitive | M [LINEDATA-5-401]|
| 6 |  |  | 0 | Reserved | M [LINEDATA-5-402]|
| 7 | BITS | GraFlgs |  | Graphics flags | M [LINEDATA-5-403]|
| 8–9 | UBIN | Iend |  | I-coordinate for primitive end point | M [LINEDATA-5-404]|
| 10–11 | UBIN | Bend |  | B-coordinate for primitive end point | M [LINEDATA-5-405]|
| 12–13 | UBIN | HAXIS | 0–32,767 | Rounded corner X-axis | M [LINEDATA-5-406]|
| 14–15 | UBIN | VAXIS | 0–32,767 | Rounded corner Y-axis | M [LINEDATA-5-407]|
| 16 | UBIN | MH | X'00'–X'FF' | Integer multiplier for radius | M [LINEDATA-5-408]|
| 17 | UBIN | MFR | X'00'–X'FF' | Fractional multiplier for radius | M [LINEDATA-5-409]|
| 18–19 | CODE | DescID | X'0001'–X'FFFE' | Identifies a graphics descriptor | M [LINEDATA-5-410]|
| 20 | CODE | FGMix | X'02' | Foreground mixing rule | O [LINEDATA-5-411]|
| 21 |  |  | 0 | Reserved | O [LINEDATA-5-412]|
| 22 | CODE | LineTpe |  | Line type | O [LINEDATA-5-413]|
| 23 | CODE | LineWMH |  | Line width integral multiplier | O [LINEDATA-5-414]|
| 24 | CODE | LineWMFR |  | Line width fractional multiplier | O [LINEDATA-5-415]|
| 25 | CODE | PattSet | X'00' | Pattern set | O [LINEDATA-5-416]|
| 26 | CODE | PattSymb |  | Pattern symbol | O [LINEDATA-5-417]|
| 27–28 | SBIN | XMAJ |  | I-coordinate of major axis end point | O [LINEDATA-5-418]|
| 29–30 | SBIN | YMIN |  | B-coordinate of minor axis end point | O [LINEDATA-5-419]|
| 31–32 | SBIN | XMIN |  | I-coordinate of minor axis end point | O [LINEDATA-5-420]|
| 33–34 | SBIN | YMAJ |  | B-coordinate of major axis end point | O [LINEDATA-5-421]|

### Triplet X'7E' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Graphics Descriptor triplet
ParmSpc Specifies whether the triplet defines a complete primitive or whether it defines only the starting
parameters or only the ending parameters
Value Description
X'01' All parameters
The triplet specifies all the parameters required to generate the primitive. For
horizontal lines, the RCD IPos/Bpos parameters specify the start point and
the Iend parameter specifies the ending inline position. For vertical lines, the
RCD IPos/Bpos parameters specify the start point and the Bend parameter
specifies the ending baseline position. For diagonal lines, the RCD IPos/Bpos
parameters specify the start point and the Iend/Bend parameters specify the
end point. For boxes, the RCD IPos/Bpos parameters specify the left top
corner of the box and the Iend/Bend parameters specify the right bottom
corner. For horizontal lines, the ParmSpc value is always assumed to be
X'01'.
X'02' Start parameters
For vertical lines, the RCD IPos/Bpos parameters specify the start point and
the ending baseline position (BPos) is specified by a Graphics Descriptor
triplet with ParmSpc = X'03' on an ensuing RCD with a matching Graphid. For
boxes, the RCD IPos/Bpos parameters specify the left edge of the box and
the Iend parameter specifies the right edge of the box. The ending BPos
parameter is specified by a Graphics Descriptor triplet with ParmSpc = X'03'
on an ensuing RCD with a matching Graphid.
For diagonal lines, both the start point and the ending inline position are
specified in the start parameters. The RCD IPos/Bpos parameters specify the [LINEDATA-5-422]


start point and the Iend value specifies the ending inline position. The ending
baseline position is specified by a Graphics Descriptor triplet with ParmSpc =
X'03' on an ensuing RCD with a matching Graphid.
If a logical page eject is processed while any lines or boxes are active (have
been started but not ended), these lines or boxes are ended. The bottom
margin is used as the ending baseline position. Note that the actual location
of “bottom margin” on a page is affected by the text orientation; see “Margin
Definition (X'7F') Triplet”.
X'03' End parameters
The triplet specifies the ending baseline position for lines or boxes started by
Graphics Descriptor (X'7E')
triplets on previous RCDs. The Graphid
parameter specifies the lines or boxes ended by this triplet. The RCD BPos
specifies the ending baseline position for lines and boxes.
All others Reserved
Graphid Specifies an identifier that is used to tie one or more start (ParmSpc = X'02') and end
(ParmSpc = X'03') Graphics Descriptor (X'7E')
triplets together
An ending triplet ends all of the active lines and boxes that were started with a matching
Graphid. Note that the start and end triplets must have the same orientation.
GrPrim Specifies the graphics primitive that is to be generated
The primitives and their parameters are specified based on definitions in the AFP GOCA
architecture. See the Graphics Object Content Architecture for Advanced Function
Presentation Reference
.
Value Description
X'01'–X'03' Line at current position
A straight line is generated between two points. The line is defined by the line
type (LineTpe) and line width (LineWMH, LineWMFR) parameters in the
descriptor. The color of the line is determined by the Color Specification
(X'4E') triplet on the RCD. If two X'4E' triplets are specified, it is determined by
the second triplet. If a X'4E' triplet is not specified, it is the presentation
process default color.
If the GrPrim value is X'01' (horizontal), the line is parallel to the I axis and
ParmSpc is assumed to be X'01'. If the GrPrim value is X'02' (vertical), the
line is perpendicular to the I axis. If the GrPrim value is X'03' (diagonal), the
line is diagonal to the I axis. See ParmSpc for a description of the parameters
used to draw the line.
X'04' Full Arc at current position
A circle or ellipse is generated with center at the current RCD position. The
ParmSpc parameter is ignored for this primitive.
The color of the boundary line for the arc, if drawn, is determined by the
second Color Specification (X'4E') triplet on the RCD or by the first Color
Specification triplet if only one is specified. If a X'4E' triplet is not specified, the
color of the boundary line is the presentation process default color. The color
of the fill pattern for the interior of the arc is determined by the first Color
Specification (X'4E') triplet on the RCD; if a X'4E' triplet is not specified, the
color of the fill pattern is the presentation process default color. [LINEDATA-5-423]


X'05' Box at current position
A box is either specified by a single RCD or by a begin/end pair of RCDs. The
box is generated with square corners or rounded corners, depending on the
value of the HAXIS and VAXIS parameters. If either parameter is zero, square
corners are generated. If they are non-zero but equal, the corners are
quadrants of a circle whose diameter is HAXIS. If they are non-zero and not
equal, the corners are quadrants of an ellipse whose full axes are HAXIS and
VAXIS.
The color of the boundary line for the box, if drawn, is determined by the
second Color Specification (X'4E') triplet on the RCD or by the first Color
Specification triplet if only one is specified. If a X'4E' triplet is not specified, the
color of the boundary line is the presentation process default color. The color
of the fill pattern for the interior of the box is determined by the first Color
Specification (X'4E') triplet on the RCD; if a X'4E' triplet is not specified, the
color of the fill pattern is the presentation process default color.
See ParmSpc for more detail on how the box is drawn.
All others Reserved
GraFlgs Flags that specify how the primitive is generated
GraFlgs bits have the following definitions:
Bit 0 Fill
This flag i
ndicates whether the interior of the primitive (the inside of the circle,
ellipse, or box) is to be filled with a colored pattern. The pattern is specified by
the pattern set (Patt Set) and pattern symbol (Patt Symb) parameters in the
descriptor and corresponds to one of the pattern symbols in the default
pattern set defined in the AFP GOCA architecture. The color of the fill pattern
is determined by the first Color Specification (X'4E') triplet on the RCD; if a
Color Specification triplet is not specified, it is the presentation process
default color.
This bit is ignored for the line primitive.
Value Description
B'0' Do not fill the interior of the primitive
B'1' Fill the interior of the primitive
Bit 1 Boundary
This flag i
ndicates whether the boundary of the primitive (circle, ellipse, or
box) is to be drawn with a line. The line is specified by the line type (LineTpe)
and line width (LineWMH, LineWMFR) parameters in the descriptor.
Value Description
B'0' Do not draw the boundary of the primitive
B'1' Draw the boundary of the primitive
If drawn, the color of the boundary is determined by the second Color
Specification (X'4E') triplet on the RCD or by the first Color Specification
triplet if only one is specified. If a X'4E' triplet is not specified, the color of the
boundary line is the presentation process default color.
This bit is ignored for the line primitive, which is always drawn.
Bits 2–7 Reserved; all bits should
be B'0'
Iend Specifies the I position of the end point for the primitive
This parameter is ignored if ParmSpc does not equal X'01' or X'02'. [LINEDATA-5-424]


Bend Specifies the B position of the end point for the primitive
This parameter is ignored if ParmSpc does not equal X'01'. This parameter specifies a relative
baseline position if the RCD specifies a relative baseline position.
HAXIS Used only for the Box primitive to specify the length of the ellipse X-axis (parallel to the I-axis)
VAXIS Used only for the Box primitive to specify the length of the ellipse Y-axis (parallel to the B-axis)
MH Specifies the integer portion of the scale factor that is applied to the circle or ellipse defined by
the XMAJ, XMIN, YMAJ, YMIN parameters in the descriptor
MFR Specifies the fractional portion of the scale factor that is applied to the circle or ellipse defined
by the XMAJ, XMIN, YMAJ, YMIN parameters in the descriptor
A decimal point is implied between MH and MFR. The fractional portion of the scale factor is
calculated by dividing MFR by 256. For example, if MFR=X'40', its decimal value is 64, which,
divided by 256 results in a fractional component for the scale factor of 1/4.
For a circle, the radius is (MH×R + MFR×R), where R is the radius of the circle defined by the
current arc parameters. For an ellipse, the major and minor axes are (MH×MAJ + MFR×MAJ)
and (MH×MIN + MFR×MIN), where MAJ and MIN are the major and minor axes of the ellipse.
DescID Specifies the ID of a graphics descriptor
The descriptor is defined by bytes 20–34 of this triplet. If the ID matches the ID of a graphics
descriptor defined previously on an RCD for this page, the previous descriptor is used
regardless of whether bytes 20–34 are specified in the current descriptor. If the ID does not
match a previously-defined ID, bytes 20–34 must be specified and define the graphics
descriptor that is to be identified with this ID and that is to be used to generate this primitive. If
this Graphics Descriptor (X'7E')
triplet specifies ParmS pc = X'03', this parameter and all bytes
in the graphics descriptor are ignored. The valid range for the ID is X'0001'—X'FFFE'.
For a given page, the presentation services program collects all graphics primitives that have
the same graphics descriptor ID and the same orientation and groups them into a single
graphics object.
The origin for the graphics object area is one of the four corners of the page as determined by
the text orientation specified in the RCD (TxtOrent) and is therefore coincident with the current
(I,B) origin. The rotation of the graphics object area about the page X
p-axis matches the
rotation of the current text (I,B) coordinate system. For example, with a (90°,180°) text
orientation, the object area rotation is 90°. The extents of the object area are determined by
the extents of the page.
The position of the graphics primitive in the (I,B) coordinate system therefore maps to the
same position in the object area (X
oa,Yoa) coordinate system. This position in turn is projected
to a graphics window whose upper left corner is at the graphics presentation space (GPS)
origin and whose extents match those of the object area. The upper left corner of the graphics
presentation space window is therefore also coincident with the current (I,B) origin. The
mapping between graphics window and object area is position and trim.
For example, if the RCD specifies a (90°,180°) text orientation, the upper left corner of the
graphics window is at the top-right corner of the page and graphics primitives in this object are
rotated 90° with respect to the page X
p axis. The X-extent of the graphics window is the Y pextent of the page and the Y-extent of the graphics window is the X p-extent of the page.
The units of measure for the graphics presentation space and for the graphics object area are
the same as those defined on the page (X p,Yp) presentation space in the PGD structured field
of the Active Environment Group (AEG) for the Data Map.
FGMix Specifies how the graphics primitive mixes with underlying data
The only mixing supported is X'02' (Overpaint). This parameter is specified in an AFP GOCA
object with the GDD Set Current Defaults instruction and the Set Mix drawing order. [LINEDATA-5-425]


LineTpe Specifies the type of line that is drawn
For supported values, see the Graphics Object Content Architecture for Advanced Function
Presentation Reference. This parameter is specified in an AFP GOCA object with the GDD
Set Current Defaults instruction and the Set Line Type drawing order.
LineWMH Specifies the width of the line that is drawn by defining the integer portion of the normal line
width multiplier
For supported values, see the Graphics Object Content Architecture for Advanced Function
Presentation Reference. This parameter is specified in an AFP GOCA object with the GDD
Set Current Defaults instruction and the Set Line Width drawing order.
LineWMFR Specifies the width of the line that is drawn by defining the fractional portion of the normal line
width multiplier
For supported values, see the Graphics Object Content Architecture for Advanced Function
Presentation Reference. This parameter is specified in an AFP GOCA object with the Set
Fractional Line Width drawing order.
PattSet Specifies the pattern set that contains the pattern symbols used to fill the interior of a primitive
The only value supported is X'00'—default pattern set. This parameter is specified in an AFP
GOCA object with the GDD Set Current Defaults instruction and the Set Pattern Set drawing
order.
Patt
Symb Specifies the pattern symbol within the current pattern set that is used to fill the interior of a
primitive
For supported values, see the Graphics Object Content Architecture for Advanced Function
Presentation Reference
. This parameter is specified in an AFP GOCA object with the GDD [LINEDATA-5-426]
Set Current Defaults instruction and the Set Pattern Symbol drawing order.
XMAJ, XMIN,
YMAJ, YMIN
Together, these parameters define a circle or ellipse on the (I,B) coordinate system
The center of the arc is at the (I,B) origin. When this triplet is used to generate a circle or
ellipse, the center is moved to the (I,B) position specified by the RCD and the arc is scaled by
the MH.MFR scale factor. Specifically, the four parameters specify the following:
XMAJ I coordinate of major axis endpoint
YMAJ B coordinate of major axis endpoint
XMIN I coordinate of minor axis endpoint
YMIN B coordinate of minor axis endpoint
These parameters are specified in an AFP GOCA object with the GDD Set Current Defaults
instruction and the Set Arc Parameters drawing order.
Notes:
1. The last 15 bytes (bytes 20–34) in this triplet are optional as a group. That is, either they are all specified or [LINEDATA-5-427]
none are specified. If the descriptor ID is intended to match a previously-defined descriptor ID, these bytes
should not be specified.
2. The X'22', X'69', and X'6C' triplets are ignored when this triplet is specified on an RCD. [LINEDATA-5-428]


Object Reference Qualifier (X'89') Triplet
The Object Reference Qualifier (X'89') triplet is used to specify whether the name of an object is retrieved from
the input data or retrieved using normal methods. If the name is to be retrieved from the input data, that name
overrides any ObjName field and any Fully Qualified Name (type X'01') triplet that would normally be used to
select an object. This triplet may occur once on an RCD structured field.
See “Object Reference Qualifier (X'89') Triplet”. [LINEDATA-5-429]


Color Management Resource Descriptor (X'91') Triplet
The Color Management Resource Descriptor triplet specifies the processing mode and scope for a Color
Management Resource (CMR). This triplet is mandatory when the RCD references a Color Management
Resource (CMR) with the FQN type X'DE' triplet, in which case this triplet specifies the processing mode for
the CMR and must occur once for each FQN type X'DE' specified. It is ignored in all other cases. This triplet
must immediately follow the FQN type X'DE' triplet that specifies the CMR name.
See “Color Management Resource Descriptor (X'91') Triplet”. [LINEDATA-5-430]


Concatenate Bar Code Data (X'93') Triplet
This is an optional triplet and may occur once. If this triplet is specified more than once, only the first will be
used.
In the RCD, c ompletion of a given concatenated bar code symbol is defined as the end of the page or an RCD
with the Start New Symbol flag (bit 0 of CBCFlgs) set in the X'93' triplet being reused.
See “Concatenate Bar Code Data (X'93') Triplet ”. [LINEDATA-5-431]


Rendering Intent (X'95') Triplet
The Rendering Intent (X'95') triplet is a MO:DCA triplet. For the formal definition of this triplet, see the Mixed
Object Document Content Architecture (MO:DCA) Reference.
The Rendering Intent triplet specifies the rendering intent parameter, which is used to modify the final
appearance of color data. This parameter is based on the rendering intents defined by the International Color
Consortium (ICC). This triplet is optional and may occur once when a Graphics Descriptor (X'7E') triplet is
specified on the RCD. If this triplet is specified more than once, only the first is used. This triplet specifies the
rendering intent that is to be used when presenting the Graphics object that is generated with this structured
field. Only the rendering intent that applies to the object type of the referenced object is used; the other
rendering intents are ignored. [LINEDATA-5-432]
### Triplet X'95' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 10 | Length of the triplet | M [LINEDATA-5-433]|
| 1 | CODE | Tid | X'95' | Identifies the Rendering Intent triplet | M [LINEDATA-5-434]|
| 2–3 |  |  | 0 | Reserved | M [LINEDATA-5-435]|
| 4–6 |  |  | X'FFFFFF' | Not used | M [LINEDATA-5-436]|
| 7 | CODE | GOCARI | X'00'–X'03', X'FF' | Rendering intent for AFP GOCA objects | M [LINEDATA-5-437]|
| 8–9 |  |  | 0 | Reserved | M [LINEDATA-5-438]|

### Triplet X'95' Semantics
Tlength Contains the length of the triplet
Tid Identifies the Rendering Intent triplet
GOCARI Specifies the rendering intent for AFP GOCA objects
Valid values are the following.
Value Description
X'00' Perceptual
Gamut mapping is vendor-specific and colors are adjusted to give a pleasing
appearance. This intent is typically used to render continuous-tone images.
X'01' Media-relative colorimetric
In-gamut colors are rendered accurately and out-of-gamut colors are mapped
to the nearest value within the gamut. Colors are rendered with respect to the
source white point and are adjusted for the media white point. Therefore
colors printed on two different media with different white points do not match
colorimetrically, but might match visually. This intent is typically used for
vector graphics.
X'02' Saturation


Gamut mapping is vendor-specific and colors are adjusted to emphasize
saturation. This intent results in vivid colors and is typically used for business
graphics.
X'03' ICC-absolute colorimetric
In-gamut colors are rendered accurately and out-of-gamut colors are mapped
to the nearest value within the gamut. Colors are rendered only with respect
to the source white point and are not adjusted for the media white point.
Therefore colors printed on two different media with different white points
should match colorimetrically, but might
not match visually. This intent is
typically used for logos.
X'FF' Rendering intent not specified
All others Reserved


## XML Descriptor (XMD)
The XML Descriptor structured field contains information, such as data position, text orientation, font selection,
field selection, and conditional processing identification, used to format XML data that consists of text delimited
by start and end tags.
Note: The XMDs in a Data Map are numbered sequentially, starting with 1. [LINEDATA-5-439]
### XMD (X'D3A68E') Syntax

**Structured Field Introducer:** SF Length (2B), ID = X'D3A68E', Flags (1B), Reserved X'0000' [LINEDATA-5-440]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ElmType | X'00'–X'03' | Element Type | M [LINEDATA-5-441]|
| 1–3 | BITS | XMDFlgs |  | XMD flags | M [LINEDATA-5-442]|
| 4 |  |  | 0 | Reserved | M [LINEDATA-5-443]|
| 5–6 | UBIN/SBIN | IPos |  | Inline position | M [LINEDATA-5-444]|
| 7–8 | UBIN/SBIN | BPos |  | Baseline position | M [LINEDATA-5-445]|
| 9–12 | CODE | TxtOrent |  | Text (I,B) Orientation | M [LINEDATA-5-446]|
| 13 | CODE | FntLID |  | Primary font local ID | M [LINEDATA-5-447]|
| 14–15 | UBIN | FLDxmd |  | Field XMD Pointer | M [LINEDATA-5-448]|
| 16–17 |  |  | 0 | Reserved | M [LINEDATA-5-449]|
| 18–25 | CHAR | SupName |  | Suppression token name | M [LINEDATA-5-450]|
| 26 |  |  | 0 | Reserved | M [LINEDATA-5-451]|
| 27–30 | UBIN | DataStrt |  | Data start position | M [LINEDATA-5-452]|
| 31–32 | UBIN | DataLgth |  | Data length | M [LINEDATA-5-453]|
| 33–34 | UBIN | CONDxmd |  | Conditional Processing XMD Pointer | M [LINEDATA-5-454]|
| 35 | CODE | SubpgID |  | Subpage ID (Always X'00' for XMDs) | M [LINEDATA-5-455]|
| 36–37 | CODE | CCPID |  | CCP Identifier | M [LINEDATA-5-456]|
| 38–39 | UBIN | Pgno |  | Starting page number | M [LINEDATA-5-457]|
| 40–41 | UBIN | ESpac |  | End Space | M [LINEDATA-5-458]|
| 42 | CODE | Align |  | Field Alignment | M [LINEDATA-5-459]|
| 43–44 | CODE | FldDelim |  | Field Delimiter | M [LINEDATA-5-460]|
| 45–46 | UBIN | Fldno |  | Field Number | M [LINEDATA-5-461]|
| 47–48 | UBIN | AdBIncr |  | Additional baseline increment | M [LINEDATA-5-462]|
| 49–61 |  |  | 0 | Reserved | M [LINEDATA-5-463]|
| 62–n |  | Triplets |  | Triplets | O [LINEDATA-5-464]|

XMD Semantics for triplet applicability. O [LINEDATA-5-465]
### XMD Semantics
The XMD uses many parameters that are defined for the LND or RCD. The definition of such parameters is
deferred to the LND or RCD. When such definitions are applied to the XMD, the term “LND” or “RCD” should
be read as “XMD” and the byte offsets of the parameters should be adjusted to the XMD.
There are four types of XMDs:
• Element XMDs, which define the processing of the data content of the XML element or define a default page [LINEDATA-5-466]
header or trailer.
• Field XMDs, which define the processing for an input field or specify constant text or graphics. [LINEDATA-5-467]
• Attribute XMDs, which define the processing for attributes specified in an XML start tag. Attribute XMDs are a [LINEDATA-5-468]
special type of Field XMD.
• Conditional Processing XMDs, which specify the Conditional Processing associated with an input element. [LINEDATA-5-469]
The Field XMDs, Attribute XMDs, and Conditional Processing XMDs associated with an Element XMD are
chained to that XMD using XMD number pointers. An XMD is assumed to be an Element XMD if neither the
Field XMD, Attribute XMD,
nor the Conditional Processing XMD bits are on in the XMDFlgs byte. [LINEDATA-5-470]


ElmType Element Type
This parameter is ignored on XMDs other than Element XMDs.
Value Description
X'00' Body Element
This XMD does not have any special header or trailer properties associated with it and
is used to format any input elements with a matching Qualified Tag.
X'01' Page Header Element
This XMD is used to automatically print a header (such as the current customer name)
at the beginning of each new page. The baseline position of this XMD can be
anywhere on a logical page and can be specified as relative. If an input element is
received with a matching Qualified Tag, that input element is not presented on receipt
but is saved as the active page header. If no Qualified Tag is specified for an XMD that
has the Page Header element type, it is assumed to be a default Page Header XMD.
Only one default Page Header XMD can be specified in a Data Map and no input
element data is processed with a default XMD. See “Logical Page Eject Processing”
for page header and new page processing details (note that page eject
processing for RCD and XMD is identical) .
Note: Once a Page Header XMD is processed, the header element is saved for the
duration of the document by the presentation services program. Whenever the
same Data Map is re-invoked for that document, this saved header element is
always presented with each page generated with the Data Map.
X'02' Page Trailer Element
This XMD is used to automatically print a trailer (for example, a footnote or page
number) at the end of each page. The baseline position of this XMD can be anywhere
on a logical page and can be specified as relative. If an input element is received with
a matching Qualified Tag, that input element is not presented on receipt but is saved
as the active page trailer. If no Qualified Tag is specified for an XMD that has the Page
Trailer element type, it is assumed to be a default Page Trailer XMD.
Only one default
Page Trailer XMD can be specified in a Data Map and no input element data is
processed with a default XMD. See “Logical Page Eject Processing”
for
page trailer and new page processing details (note that page eject processing for
RCD and XMD is identical) .
Note: Once a Page Trailer XMD is processed, the trailer element is saved for the
duration of the document by the presentation services program. Whenever the
same Data Map is re-invoked for that document, this saved trailer element is
always presented with each page generated with the Data Map.
X'03' Group Header Element
This XMD is used to automatically print a group header (for example, column
headings for a group of elements) on a page. Note that the group header is not
actually printed and causes no action until a Body Element XMD with Group Indicator
(XMDFlgs bit 19) set to B'1' is processed for the page. The baseline position of this
XMD can be specified as relative. If an input element is received with a matching
Qualified Tag, that input element is saved as the active group header and then
presented. If that input element or XMD causes a page eject, that input element is
used as the active group header for the new page. See “Logical Page Eject
Processing”
for active group header and new page processing details
(note that page eject processing for RCD and XMD is identical) .
Note: Once a Group Header XMD is processed and is still active when leaving the
Data Map, the group header element is saved by the presentation services
program. Whenever the same Data Map is re-invoked, this saved group header [LINEDATA-5-471]


element is presented again if the first body element after re-invoking the Data
Map selects a Body Element XMD that has the Group Indicator on.
Note: The formation of the Page Header, Group Header, or Page Trailer might require
element content from more than one element. This is accomplished by the use of
XMDFlgs bit 23 (Header/Trailer Continued). Refer to the description of the
Header/Trailer Continued flag for more information about continued headers and
trailers.
XMDFlgs Flag bits
Bits 0–5 For a definition of these flag bits see “Line Descriptor (LND)”. LND
flag bits 0–1 are reserved in the XMD.
Bit 6 Field XMD
Value Description
B'0' If this bit and bits 10 and 11 are all B'0', this is an Element XMD.
B'1' This is a Field XMD. If this bit and bit 11 is on, it is an error.
Bits 7–9 For a definition of these flag bits see “Line Descriptor (LND)”. LND
flag bit 9 is reserved in the XMD.
Bit 10 Attribute XMD
Value Description
B'0' If this bit and bits 6 and 11 are all B'0', this is an Element XMD.
B'1' This is an Attribute XMD. If this bit is on, bit 6 must also be on since
an Attribute XMD is a special type of Field XMD. If this bit and bit 11
are
on, it is an error.
When processing an attribute of the XML Start tag, the processor matches
the attribute name to the name specified in the XML Name (X'8A')
triplet that
was specified for this XMD. Once a match is found, the attribute value is used
as the data to be processed by this XMD.
Notes:
1. The attribute value is the quoted string not containing the quotation mark [LINEDATA-5-472]
used as a delimiter for that string.
2. The color for the data presented by this XMD is always the color specified [LINEDATA-5-473]
by the Color Specification (X'4E') triplet, if specified. If this triplet is not
specified, the data is presented in the presentation process default color.
Bit 11 For a definition of this flag bit see “Line Descriptor (LND)”.
Bit 12 Relative Inline Position
This bit i
ndicates whether the inline position specified in bytes 5–6 of this
XMD is an absolute position or a relative position. If an absolute inline position
is specified, it is measured as a positive offset in the inline direction from the
current text (I,B) coordinate system origin. If a relative position is specified, it
is measured as a positive or negative offset from the current inline position
using the current text (I,B) coordinate system, which is defined by the text
orientation specified in bytes 9–12.
Value Description
B'0' The inline position specified in bytes 5–6 is an absolute position.
B'1' The inline position specified in bytes 5–6 is a relative position.
The following restriction applies to relative inline positioning: [LINEDATA-5-474]


• The text orientation of an XMD that specifies relative inline positioning must [LINEDATA-5-475]
be the same as the text orientation of the XMD that defines the inline
position from which the relative offset is measured.
Bits 13–15 For a definition of these flag bits see “Line Descriptor (LND)”.
Bits 16–20 For a definition of these flag bits see “Record Descriptor (RCD)”.
Bit 21 Use Start Tag
This bit selects the Start tag (including the angle brackets '<' and '>') as the
data field to be used for presentation.
Value Description
B'0' Do not select the Start Tag
B'1' Select the Start Tag
This function is restricted to Field XMDs; it is ignored on all other XMDs.
Bit 22 Reserved; should be zero
Bit 23 Header/Trailer Continued
This bit indicates that this XMD is a continuation of a Page Header, Group
Header, or Page Trailer definition. The formation of the Page Header, Group
Header, or Page Trailer might require the element content from more than
one element. This is accomplished by specifying this continuation indicator
along with specifying the appropriate ElmType parameter to identify which
type of header or trailer that is to be continued. If the header or trailer has not
started, this header or trailer XMD starts the header or trailer regardless of the
setting of this flag.
For Page Headers and Page Trailers, the elements that are used to build the
continued header or trailer do not need to be contiguous in the XML data, but
do need to be on the same page. If a page break occurs before a continued
header or trailer is reached, the continued header or trailer acts as a new
header or trailer. If the continued header or trailer is on the same page, the
XMD structured fields used to print the various pieces of the continued header
or trailer are processed as though the elements were specified contiguously.
This means any relative positioning specified is relative to data placed using a
previous header or trailer XMD, if any.
For Group Headers, the elements that are used to build the continued header
do not need to be contiguous, but cannot have body elements placed
between the pieces of the Group Header. If body elements are placed
between the pieces of a continued Group Header, the Group Header is not
continued but acts as a new Group Header.
Value Description
B'0' Not a continuation XMD
B'1' Continued Header or Trailer definition
If the header or trailer has started, data continues to be collected to
form the header or trailer.
This function is ignored on Body Element XMDs. [LINEDATA-5-476]


IPos Inline Position
See “Line Descriptor (LND)”.
Relative Inline Position for Element, Field, and Attribute XMDs: If the inline position is relative,
the offset is relative to the current inline position. If there is no prior XMD, the relative inline
position is relative to the left margin.
Note: Data must not exceed the boundaries of the page, which are defined in the Page
Descriptor (PGD) structured field. If the new print position is outside these boundaries,
printing of the page stops.
Note that the actual location of the “left margin” on a page is affected by the text orientation;
see “Margin Definition (X'7F') Triplet”.
BPos Baseline Position
See “Record Descriptor (RCD)”.
TxtOrent Text (I,B) Orientation
See “Line Descriptor (LND)”.
FntLID Primary Font Local Identifier
See “Line Descriptor (LND)”.
FLDxmd XMD number of a Field XMD
See “Record Descriptor (RCD)”.
SupName Suppression token name
See “Line Descriptor (LND)”.
DataS
trt Data Start Position
See “Line Descriptor (LND)”.
DataL
gth Data Length
See “Line Descriptor (LND)”.
CONDxmd XMD number of a Conditional Processing XMD
See “Record Descriptor (RCD)”.
Subp
gID Subpage Identifier
See “Line Descriptor (LND)”.
CCPID CCP Identifier
See “Line Descriptor (LND)”.
Pgno Page Number
See “Record Descriptor (RCD)”.
ESpac End Space
See “Record Descriptor (RCD)”.
Align Field Alignment
See “Record Descriptor (RCD)”.
FldDelim Field Delimiter
See “Record Descriptor (RCD)”.
Fldno Field Number
See “Record Descriptor (RCD)”. [LINEDATA-5-477]


AdBIncr Additional baseline increment
See “Record Descriptor (RCD)”.
Triplets See the following:
“Fully Qualified Name (X'02') Triplet”
“Extended Resource Local Identifier (X'22') Triplet”
“Color Specification (X'4E') Triplet”
“Bar Code Symbol Descriptor (X'69') Triplet”
“Resource Object Include (X'6C') Triplet”
“Additional Bar Code Parameters (X'7B') Triplet”
“Graphics Descriptor (X'7E') Triplet”
“XML Name (X'8A') Triplet”
“Color Management Resource Descriptor (X'91') Triplet”
“Concatenate Bar Code Data (X'93') Triplet ”
“Rendering Intent (X'95') Triplet”
Logical Page Eject Processing
See “Logical Page Eject Processing”, which describes page eject processing with a Record
Descriptor (RCD); note that page eject processing for RCD and XMD is identical . [LINEDATA-5-478]


### XMD Triplets
Fully Qualified Name (X'02') Triplet
This triplet is optional and may occur one or more times when a Bar Code Symbol Descriptor (X'69') triplet or a
Graphics Descriptor (X'7E') triplet is specified on the XMD. The Fully Qualified Name type that may appear is
X'DE'- Data Object External Resource Reference. The FQN triplet specifies the external identifier of a Color
Management Resource (CMR) object that is used for the Bar Code object or Graphics object being generated.
The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The
identifier is a character-encoded name that
must be specified using FQNFmt = X'00'. The encoding for the
external identifier of the CMR must be UTF-16BE.
See “Fully Qualified Name (X'02') Triplet”. [LINEDATA-5-479]


Extended Resource Local Identifier (X'22') Triplet
This triplet is optional and may occur one or more times to reference an IOB structured field in the PageDef.
This triplet is ignored if the Graphics Descriptor (X'7E') triplet is specified on the XMD.
See “Extended Resource Local Identifier (X'22') Triplet”. [LINEDATA-5-480]


Color Specification (X'4E') Triplet
This is an optional triplet that specifies the color for text processed by this XMD, bar code generated by this
XMD, and for graphics generated by this XMD.
See “Color Specification (X'4E') Triplet”. [LINEDATA-5-481]


Bar Code Symbol Descriptor (X'69') Triplet
This is an optional triplet and may occur once. If this triplet is specified more than once, only the first is used.
This triplet specifies that the data selected by the descriptor is to be presented as a bar code symbol. This
triplet is ignored if the Graphics Descriptor (X'7E') triplet is specified on the XMD.
See “Bar Code Symbol Descriptor (X'69') Triplet”. Note that the LND/RCD/XMD parameters used
by this triplet may be at different offsets in the LND, RCD, and XMD. [LINEDATA-5-482]


Resource Object Include (X'6C') Triplet
This is an optional triplet that identifies an overlay or page segment object to be presented on the page at a
specified position. Multiple Resource Object Include triplets may be specified on the same XMD. This triplet is
ignored if the Graphics Descriptor (X'7E') triplet is specified on the XMD.
See “Resource Object Include (X'6C') Triplet”. [LINEDATA-5-483]


Additional Bar Code Parameters (X'7B') Triplet
This is an optional triplet that specifies additional parameters for non-linear bar code symbologies (for
example, 2D bar codes). This triplet may occur one or more times when a Bar Code Symbol Descriptor (X'69')
triplet is specified. This triplet is ignored if the Graphics Descriptor (X'7E') triplet is specified on the XMD.
See “Additional Bar Code Parameters (X'7B') Triplet”. [LINEDATA-5-484]


Graphics Descriptor (X'7E') Triplet
This is an optional Field XMD triplet and may occur once. If this triplet is specified more than once, only the first
is used. Text input and fixed data text are ignored on a Field XMD that specifies a Graphics Descriptor triplet.
When present, the Graphics Descriptor triplet specifies the generation of a graphics primitive. The triplet may
specify the complete primitive, or the start of the primitive, or the end of the primitive. This triplet is ignored on
XMDs other than Field XMDs.
See “Graphics Descriptor (X'7E') Triplet”. [LINEDATA-5-485]


#### XML Name (X'8A') Triplet
Architecture Note: The XML Name triplet is registered in MO:DCA as a private-use triplet since it is used only
in the PageDef object, which is not a MO:DCA object.
This triplet is used to build a Qualified Tag. A Qualified Tag is built by concatenating the names specified on
each XML Name triplet in the order the triplets are specified. Each XML Name used in the concatenation is
separated by a single space character.
Note: Multiple XML Name triplets do not have to be contiguous.
This triplet is mandatory on Body and Group Header Element XMDs and must occur at least once to build a
Qualified Tag.
This triplet is optional for Page Header and Page Trailer Element XMDs. If omitted, this Page Header or Page
Trailer XMD is the default Page Header or Page Trailer XMD.
This triplet is mandatory on Attribute XMDs and must occur once to identify the name of an attribute specified
on an XML start tag. If this triplet occurs more than once on an Attribute XMD, only the first occurrence is used.
This triplet is ignored on XMDs other than Element XMDs and Attribute XMDs.
The name specified in this triplet must be encoded using the encoding specified on the Encoding Scheme ID
(X'50') triplet of the BDM structured field. [LINEDATA-5-486]
### Triplet X'8A' Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Tlength | 5–254 | Length of the triplet | M [LINEDATA-5-487]|
| 1 | CODE | Tid | X'8A' | Identifies the XML Name triplet | M [LINEDATA-5-488]|
| 2–3 |  |  | 0 | Reserved | M [LINEDATA-5-489]|
| 4–n | CHAR | XMLName |  | Name of Start Tag or Attribute in XML data | M [LINEDATA-5-490]|

### Triplet X'8A' Semantics
Tlength Contains the length of the triplet
Tid Identifies the XML Name triplet
XMLName Specifies the name of the Start tag or the name of an attribute of a Start tag contained in the
XML data
This XMLName is used to build Qualified Tags when used on Element XMDs. [LINEDATA-5-491]


Color Management Resource Descriptor (X'91') Triplet
The Color Management Resource Descriptor triplet specifies the processing mode and scope for a Color
Management Resource (CMR). This triplet is mandatory when the XMD references a Color Management
Resource (CMR) with the FQN type X'DE' triplet, in which case this triplet specifies the processing mode for
the CMR and must occur once for each FQN type X'DE' specified. It is ignored in all other cases. This triplet
must immediately follow the FQN type X'DE' triplet that specifies the CMR name.
See “Color Management Resource Descriptor (X'91') Triplet”. [LINEDATA-5-492]


Concatenate Bar Code Data (X'93') Triplet
This is an optional triplet and may occur once. If this triplet is specified more than once, only the first will be
used.
In the XMD, c ompletion of a given concatenated bar code symbol is defined as the end of the page or an XMD
with the Start New Symbol flag (bit 0 of CBCFlgs) set in the X'93' triplet being reused.
See “Concatenate Bar Code Data (X'93') Triplet ”. [LINEDATA-5-493]


#### Rendering Intent (X'95') Triplet
The Rendering Intent triplet specifies the rendering intent parameter, which is used to modify the final
appearance of color data. This parameter is based on the rendering intents defined by the International Color
Consortium (ICC). This triplet is optional and may occur once when a Graphics Descriptor (X'7E') triplet is
specified on the XMD. If this triplet is specified more than once, only the first is used. This triplet specifies the
rendering intent that is to be used when presenting the Graphics object that is generated with this structured
field. Only the rendering intent that applies to the object type of the referenced object is used; the other
rendering intents are ignored.
See “Rendering Intent (X'95') Triplet”. [LINEDATA-5-494]


