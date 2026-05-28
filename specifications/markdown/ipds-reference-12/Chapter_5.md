# Chapter 5. Text Command Set
The Text command set is composed of the IPDS commands for presenting text information in a page, a page
segment, or an overlay. This command set contains the following commands:
Table 37. Text Commands
Command Code Description In TX1 Subset?
LE X'D61D' “Load Equivalence” Yes
WTC X'D688' “Write Text Control” No
WT X'D62D' “Write Text” Yes
The Text Presentation Space
Since text can be specified in two different ways, it is important to distinguish text-major text from text within a
text object. Text-major text is mapped to and controlled by the logical page.
Text data for a text object is placed onto the logical page in much the same way as graphics, image, and bar
code data. Text data is mapped from an abstract presentation space to the text object area on the logical page.
Like image and bar code data, the entire text presentation space is mapped to the text object area; unlike
graphics, there is no concept of a presentation space window in this command set. The size of the text
presentation space is defined in the Text Data Descriptor (TDD) self-defining field of the WTC command. The
coordinate system for this presentation space is the X
t,Yt coordinate system.
In a text object, exception ID X'0201..03' exists if any character, rule, or underscore extends outside of the text
presentation space.
Figure 72. Text Presentation Space. This figure shows the complete text presentation space before mapping to
the text object area.
Text Presentation Space
Yt
Xt
I
C
t was a bright, but stormy day when Chris
found the eggnog in his sock. This was
particularly unexpected since he had worn
those socks five days in a row without
washing them. The sock smelled good; its
partner, however, didn’t smell so good.
Since Chris was careful to lock his door
each night before retiring to his bed, a
mystery was afoot.
hris thought long and hard as he
washed the eggnog off his right foot [IPDS-5-001]


The Text Object Area
The text object area is a rectangular area on the current logical page that the text presentation space is
mapped into. The text object area can be the same size as, larger than, or smaller than the text presentation
space. The coordinate system for the text object area is the X
oa,Yoa coordinate system.
The location and orientation of the text object area is specified in the Text Area Position (TAP) self-defining
field of the WTC command. The object area size is specified in the Text Output Control (TOC) self-defining field
of the WTC command.
The text object area can overlap text-major text or other object areas within the logical page (such as bar code,
image, graphics, or object container data) specified earlier for the same page or overlay. Also, the text object
area can be overlapped by subsequent objects or text-major text specified by other commands for the same
page or overlay.
Some printers allow the text object area to be colored before the text data is placed in the object area; coloring
is specified with triplets in the Text Output Control self-defining field. Support for this optional function is
indicated by the X'6201' property pair that is returned in the Device-Control command-set vector of the Sense
Type and Model command reply.
Figure 73. Locating, Sizing, and Orienting the Text Object Area
Logical Page
X
p
$Y_{p}$
I
B
Xoa
Yoa


Mapping the Text Presentation Space
The mapping of the text presentation space into the text object area is specified by the TOC self-defining field
in the WTC command. For a detailed description of text mapping, refer to the description of WTC-TOC
mapping options (byte 11).
With the position mapping option, the top-left corner of the text presentation space is offset from the origin of
the text object area, and the text presentation space is presented at the size specified by bytes 6–17 of the
TDD self-defining field. Text or rules within the text presentation space that fall outside the text object area
cause exception ID X'020A..06' to exist.
Interaction Between Text Objects and Text-Major Text
Text-major text within a page or overlay is completely independent of any text object that is included within the
page or overlay. Likewise, a text object does not affect text-major text.
For text-major text, all control sequence values are reset to the most-recently received LPD command “initial
text-major conditions” with each Begin Page (BP) command and Begin Overlay (BO) command. Control
sequences that are unspecified in the LPD command are set to PTOCA-defined defaults with each Begin Page
or Begin Overlay command. Control sequences embedded in Write Text commands that follow can change
these initial values as the page or overlay is built. Initial text-major conditions and any control sequences within
text-major text only affect text objects within the page or overlay when the text object uses a relative coordinate
system in the TAP .
For text objects, all control sequence values are reset to the latest “initial text conditions” specified in the Text
Data Descriptor (TDD) portion of the Write Text Control (WTC) command each time a WTC command is
processed. Control sequences that are unspecified in the TDD are set to PTOCA-defined defaults. Control
sequences embedded in Write Text commands within the text object can change these initial values as the text
object is built. Neither the initial text conditions nor any control sequences within a text object affect any text-
major text within a page, page segment, or overlay. For example, if a WT command within a page, page
segment, or overlay contains a SCFL control sequence that selects a font with LID=1, this font is ignored when
processing a text object (in this case, the TDD initial text conditions or a subsequent SCFL within the text
object selects the first font to be used within the text object). When the End command for the text object is
processed, the font identified by LID=1 is then used for any subsequent text within the page, page segment, or
overlay. Likewise, all other text-major settings are restored when the End command is processed so that any
text controls within the text object have no effect on subsequent text within the page, page segment, or overlay. [IPDS-5-002]


Load Equivalence
The Load Equivalence (LE) command permits text-suppression values embedded in text data to be referenced
externally using different values. For example, internal text-suppression values of X'06', X'07', and X'09' from a
Begin Suppression (BSU) control sequence can be mapped to an external value of X'02' from a Load Copy
Control (LCC) command if the printer previously has received an appropriate LE command. Thus, the printer
can use a single suppression ID for more than one suppression pair. Refer to the Presentation Text Object
Content Architecture Reference for more information about suppressions.
The LE command mapping remains in effect until the printer receives another LE command, when the values
in the new LE command replace those in the previous LE command.
```
Length X'D61D' Flag CID Data
```
The length of the LE command can be:
Without CID X'0007'–X'7FFF' in increments of 4
With CID X'0009'–X'7FFD' in increments of 4
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data in a Load Equivalence command consists of a two-byte mapping type, followed by zero or more
equivalence entries that are processed in the order that they appear in the command. Some printers cannot
accept more than 127 entries. If a syntax error is encountered in one of the entries, the LE command is
discarded and any previously active LE entries remain in effect. Exception ID X'02C8..02' exists in this
situation.

| Offset | Type | Name | Range | Meaning | TX1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Type | X'0100' | Mapping type:<br>Suppression equivalence | X'0100' [IPDS-5-003]|
| **Zero or more equivalence entries in the following format:** [IPDS-5-004]| | | | | |
| 2–3 | CODE | Internal | X'0001' – X'00FF' | Internal value | X'0001' – X'007F' [IPDS-5-005]|
| 4–5 | CODE | External | X'0001' – X'00FF' | External value | X'0001' – X'007F' [IPDS-5-006]|
Bytes 0–1 Mapping type
The only valid value is X'0100', that indicates suppression equivalence mapping.
The value X'0200' is retired as Retired item 52.
Exception ID X'02C6..02' exists if an invalid mapping type is specified.
Bytes 2–3 Internal value
These bytes contain the value of the stored parameter used in the BSU and ESU control
sequences. These are the first 2 bytes of a list entry.
This parameter must be unique within the LE list; each internal value may map to only one
external value.
Exception ID X'02C1..02' exists if an internal value is not unique in the LE command.
Exception ID X'02C8..02' exists if an invalid or unsupported internal value is specified.
Property pair X'2002' in the Text command-set vector of an STM reply indicates support for
text suppression IDs in the range X'0080'–X'00FF'. [IPDS-5-007]


Bytes 4–5 External value
These bytes contain the value of the stored parameter used in the LCC command. This value
references the internal value. These are the second 2 bytes of a list entry.
This parameter need not be unique within the LE list; several internal values can map to the
same external value.
Exception ID X'02C8..02' exists if an invalid or unsupported external value is specified.
Property pair X'2002' in the Text command-set vector of an STM reply indicates support for
text suppression IDs in the range X'0080'–X'00FF'.
Note: This command is not required to use the suppression function. If an LCC command refers to a
suppression ID that has not been specified as an external value in an LE command (because no LE
command has been received, for example), the ID maps only to itself, and the requested suppression is
considered to be a direct reference to an internal value suppression ID used in a BSU ...ESU pair. [IPDS-5-008]


Write Text Control
Length X'D688' Flag CID Data (TAP , TOC, TDD)
The length of the WTC command can be:
Without CID X'0024'–X'7FFF'
With CID X'0026'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The Write Text Control (WTC) command causes the printer to enter the appropriate text state. The parameters
of this command define the text presentation space, define the text object area, and define the mapping of the
text presentation space into the text object area. The WTC command is followed by zero or more Write Text
(WT) commands. Text-object processing ends when the printer receives the End command within text state. If
not enough data is specified in the WT commands, exception ID X'0205..01' exists. Subsequent text received
outside of a text object is controlled by the current logical page.
To associate metadata with the text object, one or more metadata objects can immediately follow the WTC
command, before any other commands. Each Write Metadata Control (WMC) command causes the printer to
enter metadata state, where exactly one metadata object is included. Metadata state ends when the printer
receives the End command, at which point the printer returns to the text state it was in when the WMC was
received.
Note: Control sequences within the text presentation space use the I and B axes defined by this command
(and possibly redefined within the text data).
The WTC data field consists of two or three consecutive self-defining fields in the following order:
1. Text Area Position (TAP) [IPDS-5-009]
2. Text Output Control (TOC), optional [IPDS-5-010]
3. Text Data Descriptor (TDD) [IPDS-5-011]
Each self-defining field contains a two-byte length field, then a two-byte self-defining field ID, and finally a data
field. If an invalid or unsupported command length is specified, exception ID X'0202..02' exists. If an invalid
self-defining field is specified, a self-defining field is out of order, a required self-defining field is not specified,
or one of the self-defining fields appears more than once, exception ID X'020B..05' exists.
Not all IPDS printers support the WTC command. Support for this optional command is indicated by the
X'2001' property pair that is returned in the Text command-set vector of the Sense Type and Model reply. [IPDS-5-012]


Text Area Position
The TAP is a mandatory self-defining field in the WTC command. It defines the position and orientation of the
text object area. The origin and orientation of the text object area are defined relative to the reference
coordinate system.
The format of the TAP is as follows: [IPDS-5-013]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of TAP | Length of TAP, including this length field | X'000B' to end of TAP [IPDS-5-014]|
| 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' [IPDS-5-015]|
| 4–5 | SBIN | X offset | X'8000' – X'7FFF' | Text object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF'<br>(Refer to the note following the table.) [IPDS-5-016]|
| 6–7 | SBIN | Y offset | X'8000' – X'7FFF' | Text object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF'<br>(Refer to the note following the table.) [IPDS-5-017]|
| 8–9 | CODE | Text object area orientation [IPDS-5-018]| | | |
| | bits 0–8 | Degrees | B'000000000' – B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' [IPDS-5-019]|
| | bits 9–14 | Minutes | B'000000' – B'111011' | Number of minutes (0–59) in the orientation | B'000000' [IPDS-5-020]|
| | bit 15 | | B'0' | Reserved | B'0' [IPDS-5-021]|
| 10 | CODE | Coordinate system | X'00', X'20', X'40', X'60', X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' [IPDS-5-022]|
11 to
end of
TAP
UNDF Data without architectural definition
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on [IPDS-5-023]

Bytes 0–1 Self-defining-field length, including itself. Bytes after byte 10 are ignored by the printer.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Text object area origin X offset in L-units
These bytes specify the text object area origin (top-left corner) as an $X_{p}$, I, or I-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay. [IPDS-5-024]


Bytes 6–7 Text object area origin Y offset in L-units
These bytes specify the text object area origin (top-left corner) as a $Y_{p}$, B, or B-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Note: The current text presentation coordinate ($I_{c}$, $B_{c}$) for text-major text is not changed by the
printing of this text object.
Bytes 8–9 Orientation of text object area
This two-byte parameter specifies the orientation of the text object area, that is, the Xoa axis of
the text object area, in terms of an angle measured clockwise from the $X_{p}$ or $I_{c}$oordinate axis.
This parameter rotates the text object area around the origin position specified in bytes 4–7
above. The text presented in the object area is aligned such that the positive Xt axis of the text
presentation space is parallel to, and in the same direction as, the positive Xoa axis of the
object area. The positive Yoa axis of the text object area is rotated 90 degrees clockwise
relative to the positive Xoa axis and is in the same direction as the positive Yt axis. This
parameter has no effect on the I-axis orientation or the B-axis orientation.
The object area orientation is specified in terms of a number of degrees and a number of
minutes.
The number of degrees in the orientation is given in bits 0–8 of this two-byte parameter.
Values from 0 (B'000000000') to 359 (B'101100111') degrees are valid. Exception ID
X'0203..05' exists if a value from 360 to 511 is received.
The number of minutes in the orientation is given in bits 9–14 of this two-byte parameter.
Values from 0 (B'000000') to 59 (B'111011') minutes are valid. Exception ID X'0203..05' exists
if a value from 60 to 63 is received.
Not all printers support orientation values other than 0 degrees; the X'A0nn' property pair in
the Text command-set vector in the STM reply reports the orientation support of the printer.
Exception ID X'0203..05' exists if the printer does not support the requested orientation value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-5-025]
90 degrees [IPDS-5-026]
180 degrees [IPDS-5-027]
270 degrees [IPDS-5-028]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system
The reference coordinate system determines the origin and orientation of the text object area,
using either the $X_{p}$,$Y_{p}$ or the inline-baseline (I,B) coordinate system.
An inline coordinate value specified as absolute means that the value in bytes 4 and 5 of the
TAP is an absolute inline coordinate location; that is, bytes 4 and 5 are offset from the I system
origin. A baseline coordinate value specified as absolute means that the value in TAP bytes 6
and 7 is an absolute baseline coordinate location; that is, bytes 6 and 7 are offset from the B
system origin.
An inline coordinate value specified as relative means that the value in TAP bytes 4 and 5 is
an offset from the current inline coordinate location. A baseline coordinate value specified as
relative means that the value in TAP bytes 6 and 7 is an offset from the current baseline
coordinate location. Therefore, the following applies:
• If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-5-029]
TAP bytes 4 and 5 specify the text inline coordinate; TAP bytes 6 and 7 specify the text
baseline coordinate.


• If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-5-030]
origin. TAP bytes 4 and 5 specify the text inline coordinate; TAP bytes 6 and 7 are added to
the current text baseline coordinate.
• If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the [IPDS-5-031]
origin. TAP bytes 4 and 5 are added to the current text inline coordinate. TAP bytes 6 and 7
specify the text baseline coordinate.
• If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. TAP [IPDS-5-032]
bytes 4 and 5 are added to the current text inline coordinate. TAP bytes 6 and 7 are added to
the current text baseline coordinate.
• If byte 10 equals X'A0', the current logical page X [IPDS-5-033]
p and $Y_{p}$ coordinates determine the origin.
When the text object is within a page, TAP bytes 4–7 specify the offset from the $X_{p}$-
coordinate and $Y_{p}$-coordinate origin specified in a previously received LPP command (or
from the printer default coordinates if no LPP command received). When the text object is
within an overlay that is invoked using an LCC command, TAP bytes 4–7 specify the offset
from the X
m-coordinate and Ym-coordinate origin. When the text object is within an overlay
that is invoked using an IO command, TAP bytes 4–7 specify the offset from the $X_{p}$-
coordinate and $Y_{p}$-coordinate origin specified in the IO command.
If an invalid value is specified, exception ID X'0204..05' exists.
Bytes 11 to
end of TAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-5-034]


Text Output Control
The Text Output Control (TOC) is a self-defining field that specifies the size of the text object area and a
mapping option for mapping the text presentation space into the text object area.
This self-defining field is optional and can be omitted from the WTC command. If the TOC field is omitted, the
printer uses the following:
• Text object area size equals the text presentation space size defined in the TDD self-defining field [IPDS-5-035]
• Mapping option X'00' (position) [IPDS-5-036]
• Xoa offset and Yoa offset equals 0 [IPDS-5-037]
• The text object area is not colored [IPDS-5-038]
• No object-level CMRs [IPDS-5-039]
• No object-level rendering intent [IPDS-5-040]
The format of the TOC is as follows: [IPDS-5-041]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010' to end of TOC | Length of TOC, including this length field | X'0010' to end of TOC [IPDS-5-042]|
| 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' [IPDS-5-043]|
| 4 | CODE | Unit base | X'00', X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-5-044]|
| 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-5-045]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF', X'FFFF' | $X_{oa}$ extent of text object area in L-units<br>Use the LPD value. | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-5-046]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF', X'FFFF' | $Y_{oa}$ extent of text object area in L-units<br>Use the LPD value. | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-5-047]|
| 11 | CODE | Mapping control option | X'00' | Mapping control option: Position | X'00' [IPDS-5-048]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units | X'0000'–X'7FFF'<br>(Refer to the note following the table.) [IPDS-5-049]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units | X'0000'–X'7FFF'<br>(Refer to the note following the table.) [IPDS-5-050]|
| 16 to end of TOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets | X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-5-051]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports [IPDS-5-052]


additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on [IPDS-5-053]

Bytes 0–1 Self-defining-field length, including itself.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Byte 4 Unit base
A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates that the
unit base is ten centimeters.
The value X'02' is retired as Retired item 53.
If an invalid or unsupported value is specified, exception ID X'0205..05' exists.
Bytes 5–6 Units per unit base
These bytes specify the number of units per unit base used when specifying the object area
extent or object area offset in either the X or the Y direction. For example, if the unit base is
ten inches and the units per unit base is X'3840', there are 1440 units per inch.
If an invalid or unsupported value is specified, exception ID X'0206..05' exists.
Bytes 7–8 X
oa extent of object area in L-units
These bytes specify the Xoa extent of the text object area in L-units using the units of measure
specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $X_{p}$ extent and the $X_{p}$
unit base and units per unit base of the LPD command that is current when this object is
printed in a page or overlay.
Note: For the duration of an overlay, the LPD associated with that overlay defines the current
logical page.
If an invalid value is specified, exception ID X'0207..05' exists.
Bytes 9–10 Yoa extent of object area in L-units
These bytes specify the Yoa extent of the text object area in L-units using the units of measure
specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $Y_{p}$ extent and the $Y_{p}$
unit base and units per unit base of the LPD command that is current when this object is
printed in a page or overlay.
If an invalid value is specified, exception ID X'0207..05' exists. [IPDS-5-054]


Byte 11 Mapping options
This byte specifies how the text presentation space is mapped to the text object area. Since
the entire text presentation space is mapped, white space within the presentation space is
included in the mapping; refer to Figure 74 for an example of how text and white space is
handled for the position mapping option.
The only supported mapping option is position.
The size of the text presentation space is defined in the TDD self-defining field. The size of the
text object area is defined in the TOC self-defining field.
If an invalid value is specified, exception ID X'0208..05' exists.
X'00' Position
The top-left corner of the text presentation space is mapped to the text object area
using the specified offset from the text object area origin. The size of the text is not
changed during this mapping. Text or rules within the text presentation space that fall
outside the text object area cause exception ID X'020A..06' to exist.
Figure 74. Example of Position Mapping for Text (Error Case)
I
C
t was a bright, but stormy day when Chris
found the eggnog in his sock. This was
particularly unexpected since he had worn
those socks five days in a row without
washing them. The sock smelled good; its
partner however didn’t smell so good.
Since Chris was careful to lock his door
each night before retiring to his bed, a
mystery was afoot.
hris thought long and hard as he
washed the eggnog off his right foot
Text Presentation Space
Position mapping specified in
the Text Output Control (TOC)
Logical Page
Y offset
specified
in TOC
X offset
specified
in TOC
Text ObjectArea
Note that because this text
extends outside of the text
object area, none of it will
actually print and exception
X‘020A..06 will be issued.’
I
C
t was a bright, but stormy day when Chris
found the eggnog in his sock. This was
particularly unexpected since he had worn
those socks five days in a row without
washing them. The sock smelled good; its
partner however didn’t smell so good.
Since Chris was careful to lock his door
each night before retiring to his bed, a
mystery was afoot.
hris thought long and hard as he
washed the eggnog off his right foot
Bytes 12–13 Xoa offset in L-units from object area origin
This value is the Xoa offset of the text presentation space from the origin of the text object area.
The units of measure used to interpret this offset are specified in bytes 4–6.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 Yoa offset in L-units from object area origin
This value is the Yoa offset of the text presentation space from the origin of the text object area.
The units of measure used to interpret this offset are specified in bytes 4–6. [IPDS-5-055]


If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of TOC
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Write Text Control triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet”
Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported.
The Color Specification (X'4E') triplet and the Presentation Space Reset Mixing (X'70') triplet allow control over
the color of the text object area before any text data is placed in the object area. The color of the text data is
specified by a PTOCA control.
Triplets that affect the color of the object area are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium.
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
The invoked CMRs and the specified PTOCA rendering intent are associated only with this text object, and are
used according to the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy” for a description of
the hierarchy. Invoke CMR (X'92') triplets on the WTC command are not used with text-major text.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet will be used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector in a STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the WTC command.
Note: Rendering Intent (X'95') triplets specified on the WTC command are not used for area coloring of an
overlay or page. In this case, the CMR-usage hierarchy starts with the LPD command for the logical
page.


Text Data Descriptor
The TDD is a mandatory self-defining field in the WTC command. It specifies parameters that define the text
presentation space size and initial text default conditions.
The format of the TDD is as follows: [IPDS-5-056]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0014' to end of TDD | Length of TDD, including this length field | X'0014' to end of TDD [IPDS-5-057]|
| 2–3 | CODE | SDF ID | X'A69B' | Self-defining-field ID | X'A69B' [IPDS-5-058]|
| 4–5 | X'0000' | Reserved | X'0000' [IPDS-5-059]| | |
| 6 | CODE | Unit base | X'00', X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-5-060]|
| 7 | X'00' | Reserved | X'00' [IPDS-5-061]| | |
| 8–9 | UBIN | $X_{t}$ units per unit base | X'0001'–X'7FFF' | $X_{t}$ units per unit base | X'3840' [IPDS-5-062]|
| 10–11 | UBIN | $Y_{t}$ units per unit base | X'0001'–X'7FFF' | $Y_{t}$ units per unit base, must be the same value as XUPUB | X'3840' [IPDS-5-063]|
| 12–14 | UBIN | $X_{t}$ extent | X'000001' – X'007FFF' | $X_{t}$ extent of the text presentation space in L-units | X'000001' – X'007FFF'<br>(Refer to the note following the table.) [IPDS-5-064]|
| 15–17 | UBIN | $Y_{t}$ extent | X'000001' – X'007FFF' | $Y_{t}$ extent of the text presentation space in L-units | X'000001' – X'007FFF'<br>(Refer to the note following the table.) [IPDS-5-065]|
| 18–19 | BITS | Text flags | B'00...00' | Reserved for text flags | B'00...00' [IPDS-5-066]|
| 20 to end of TDD | | Initial text conditions | Defined in PTOCA | Defined in PTOCA | Defined in PTOCA [IPDS-5-067]|
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on [IPDS-5-068]

Bytes 0–1 Length of the TDD self-defining field, including itself
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Data descriptor self-defining-field ID
Bytes 4–5 Reserved
Byte 6 Unit base
This byte specifies the measurement unit to be used. X'00' indicates the measurement unit for
the X and Y dimensions is ten inches; X'01' indicates the measurement unit is ten centimeters.
The value X'02' is retired as Retired item 54.
If an invalid or unsupported value is specified, exception ID X'0205..05' exists. [IPDS-5-069]


Byte 7 Reserved
Bytes 8–9 Xt units per unit base
These bytes specify the Xt units per unit base in the text presentation space. For example, if
the unit base is ten inches and this value is X'3840' (14,400), the text presentation space Xt
units of measure is 1440 units per inch.
If an invalid or unsupported value is specified, exception ID X'0206..05' exists.
Bytes 10–11 Yt units per unit base
These bytes specify the Yt units per unit base in the text presentation space. This field must
contain the same value as the Xt units per unit base field.
If the XUPUB and YUPUB values are not the same or if an invalid or unsupported value is
specified, exception ID X'0206..05' exists.
Note: Bytes 6 and 8–11 describe the units of measure for the text presentation space. These
units are used to specify the extent of the text presentation space and are used by
many of the PTOCA control sequences.
Bytes 12–14 Xt extent of text presentation space
These bytes specify the Xt dimension of the text presentation space in the L-units defined in
TDD bytes 6 and 8–9.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 15–17 Yt extent of text presentation space
These bytes specify the Yt dimension of the text presentation space in the L-units defined in
TDD bytes 6 and 10–11.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 18–19 Reserved for text flags
These bytes specify presentation text flags as defined by PTOCA; currently no flags are used.
Bytes 20 to
end of TDD
Initial text conditions
This portion of the Text Data Descriptor contains zero or more initial conditions for interpreting
text within the object. Refer to the Presentation Text Object Content Architecture Reference
for a description of initial text conditions in the Presentation Text Descriptor. When the TDD is
processed, the printer will first apply the PTOCA-defined default initial values and then apply
any initial text conditions found in the TDD.
If an invalid or unsupported value is specified, exception ID X'0200..01' exists. [IPDS-5-070]


Write Text
Length X'D62D' Flag CID Data (PTOCA control sequences and character data)
The length of the WT command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Text (WT) command sends from 0 to 32,762 bytes of character data and controls to the printer. This
data is part of a text object, page, page segment, or overlay, depending on the IPDS state of the printer. The
Write Text command carries PTOCA data, as defined by the PT1, PT2, PT3, and PT4 subsets; refer to
Presentation Text Object Content Architecture Reference for information about these subsets.
The WT command carries both text code points and PTOCA control sequences. For text-major text, the LPD
command specifies the initial control sequence settings for each page or overlay; that is, all control sequence
settings are reset to the latest LPD data with each Begin Page (BP) command and each time an overlay is
included. Text objects specify their own initial control sequence settings, which are independent of the initial
control sequence settings specified in the LPD command. Control sequences embedded in a WT command
can change these initial values as the page, page segment, overlay, or text object is processed.
Unless overridden by a Color Fidelity (X'75') triplet in a PFC command, printers that support the Set Extended
Text Color control sequence will simulate an unsupported color value that is specified with a supported color
value. This simulation capability is in addition to the optional simulation of Standard OCA color values in the
Set Text Color control sequence as reported in the Text command-set vector in an STM reply.
When an unsupported or unrecognized PTOCA control sequence is encountered, exception ID X'0200..01'
exists. There is no AEA for this exception, but the PCA allows the printer to skip all following WT data until
either a non-text object or LFE command is encountered or the end of the page is reached. For some printers,
even finer control can be specified with the Presentation Fidelity Control command to control whether or not
the error is reported and to control whether or not the printer can skip an unsupported control sequence and
continue processing the WT data.
Spanning
Presentation text data and control sequences can span multiple WT commands. That is, a control sequence or
a chain of control sequences can be started in the data sent by one WT command and can be completed in the
data sent by the WT commands that follow.
The data and embedded controls received can span multiple Write Text commands; a Write Text command
might end in the middle of an embedded control sequence or in the middle of a two-byte code point. In this
event, exception ID X'0205..01' exists if any commands other than Execute Order Anystate, No Operation, Set
Home State, or Sense Type and Model are received before the next Write Text command. [IPDS-5-071]


Unicode Support
PTOCA text data can be encoded in a variety of encoding schemes. Traditionally, the encoding scheme has
been fixed-length EBCDIC or ASCII, but with the introduction of TrueType/OpenType fonts within IPDS, text
can also be encoded as Unicode data. The encoding scheme is specified by the font activation in one of the
following ways:
• When a code page is associated with the font, the code page indicates the encoding scheme (typically [IPDS-5-072]
EBCDIC or ASCII).
• An Encoding Scheme ID (X'50') triplet can be used to indicate that the data is encoded as UTF-8. [IPDS-5-073]
• Otherwise, the encoding scheme used within the font applies. With a TrueType/OpenType font, the encoding [IPDS-5-074]
scheme is UTF-16BE.
With EBCDIC, ASCII, and simple Unicode data, each code point is mapped to a single glyph ID. Within the
PTOCA controls, the code points are specified either within a Transparent Data (TRN) control sequence, within
an Encrypted Data (ENC) control sequence, within a Set Encrypted Alternate (SEA) control sequence,
within a
Repeat String (RPS) control sequence, or are specified directly (outside of any control sequence). Since the
control sequence introducer within PTOCA begins with X'2BD3', a text string that contains the code point X'2B'
should be contained within a control sequence, such as a
TRN or RPS; this sequence is rare in EBCDIC and
ASCII, but might someday be assigned to a code value within Unicode (since it is in the range for
miscellaneous symbols and arrows).
Unicode Complex Text
Unicode text can be simple or complex. Simple text has the basic characteristic that each grapheme is
represented by a single glyph and each glyph is identified with a single code point. Complex text requires extra
processing and should be identified with a PTOCA UCT or GLC control sequence. IPDS printers do not layout
complex text directly, but require the layout to be provided using the PTOCA GLC control sequence.
Unicode-encoded text is considered to be complex if it has any of the following formatting characteristics:
• Alternate glyphs as used in CJK fonts [IPDS-5-075]
• Bidirectional rendering [IPDS-5-076]
• Contextual shaping [IPDS-5-077]
• Combined characters (for example, ligatures that are mandatory and have no equivalent Unicode code point) [IPDS-5-078]
• Specialized word break and justification rules [IPDS-5-079]
The major languages that use complex text are Arabic, Hindi, and Thai, but there are more. Complex text
cannot be rendered in the traditional one-code-point to one-glyph fashion; a layout engine is required that
examines runs of code points and maps these to runs of glyph indexes and their positions. The number of
glyphs rendered might not be the same as the number of code points in the original text. Text that does not
require such formatting is considered to be simple text. Latin languages - English, French, German, Italian,
Spanish, etc. - use ligatures (for example, German umlauts and French accents on vowels), but Unicode
provides code points for the combined characters, therefore this is not considered to be complex text. Note that
font kerning is an issue that is separate from combining characters; Unicode text with kerned characters is not
considered to be complex text.
PTOCA Unicode Complex Text (UCT) Control Sequence
The PTOCA Unicode Complex Text (UCT) control sequence is used to identify a string of Unicode text that is
intended to be processed using a layout engine. How this control sequence and the associated Unicode code
points are processed depends upon the printer implementation:
• Printers that return STM property pair X'4303' in the Text command-set vector handle the UCT differently [IPDS-5-080]
depending on its use: [IPDS-5-081]


– If the UCT terminates a GLC chain, the printer will skip over and ignore the UCT and all associated
Unicode code points. Exception ID X'029B..01' exists if an invalid complex text data length value
(CTLNGTH) is specified.
– If the UCT terminates any other PTOCA chain, or if it is not part of a PTOCA chain, exception ID
X'029C..05' exists. The Text Fidelity(X'86') triplet can be used to direct the printer to process the
associated Unicode code points in a one-code-point to one-glyph manner. The printer ignores all of the
controls within the UCT (such as, the bidi-layout processing control, the glyph processing control, the
alternate current inline position, and all of the other complex-text-processing-control flags). This produces
acceptable output in some cases. However, in other cases, this can make the formatting look incorrect
since all of the glyphs will be positioned from the current text baseline and no combining will be done.
• Printers that do not return X'4303' will detect exception ID X'0200..01' whenever a UCT control sequence is [IPDS-5-082]
encountered. How the printer continues depends upon the exception handling controls:
– Some IPDS printers support the Text Fidelity
(X'86') triplet that allows such a printer to suppress this
exception condition and skip over the UCT but process each of the Unicode code points in a one-code-
point to one-glyph manner.
– If Text Fidelity is not used (or not supported) and the PCA is taken, the printer will ignore the remainder of
the WT command data and skip to the next non-text command. If the PCA is not taken, the text does not
print and the page is terminated.
For proper layout of Unicode complex text, PTOCA glyph layout controls should be used.
PTOCA Glyph Layout Controls
Some IPDS printers support Unicode, EBCDIC, and ASCII text in PTOCA data using glyph layout controls;
refer to the PTOCA Reference for a more detailed description. These printers return STM property pair X'4303'
in the Text command-set vector. Glyph layout controls are contained within a PTOCA control sequence chain
and begin with a Glyph Layout Control (GLC). It is critical that the exact version of the font used by the
application program when creating the PTOCA glyph layout controls is also used by the printer. TrueType/
OpenType fonts have an object OID associated with them that uniquely identifies the font. To ensure that the
correct font is used, the printer will compare the OID of the currently selected font (selected by the SCFL
control sequence or Font Local ID from the current LPD command) to the OID that is specified within the GLC;
exception ID X'029C..00' exists if the OIDs don't match. Exception ID X'029C..00' also exists if the printer does
not have a font OID to use for the comparison.
Glyph layout controls can be used with TrueType/OpenType fonts, font collections, and linked fonts. When a
font collection is activated, a particular font within the collection is selected and glyphs from this font are used.
When font linking is used, the printer will compare the GLC OID to the base font OID and then to the OID of
each font that is linked to the current base font until a match is found or there are no more linked fonts.
However, when a linked font is within a collection, the OID is for the collection and therefore the printer also
uses the full font name from the GLC that specified the OID to search for a linked font within the collection. If an
OID match (and, in the case of a linked font within a collection, a full font name match) is not found, exception
ID X'029C..00' exists.
When the same font is used for more than one glyph string, a GLC can indicate that the OID of a previously
specified GLC is still in effect for the current glyph string by specifying an OID length of zero. In this case the
previously specified OID is used (or if the previous GLC also contains an OID length of zero, the printer will
continue looking backwards through the PTOCA control sequences until a GLC with an OID is found or the
beginning of the text object, page, or overlay is encountered). If no OID is found while searching for the
previously specified OID, exception ID X'029C..09' exists.
The Glyph Layout Control (GLC) uses supporting control sequences, the first of which contains a string of
glyph IDs taken directly from a single TrueType/OpenType font; thus freeing the printer from having to locate
each glyph by mapping code points to character names to glyph IDs. Glyph advances and any necessary
offsets are also provided to allow for advanced glyph layout. By allowing glyph IDs, glyph advances, and glyph [IPDS-5-083]


offsets to be sent directly to the printer, a print application can support many complex text functions (not just
Unicode complex text). This function provides support for proper complex-language layout, ligatures, kerning,
tracking, contextual shaping, position adjustments (useful when mixing double-byte and single-byte text), and
the use of alternate glyphs.
The glyph layout controls consist of the following PTOCA control sequences: Glyph Layout Control (GLC),
Glyph ID Run (GIR), Glyph Advance Run (GAR), Glyph Offset Run (GOR), and Unicode Complex Text (UCT).
These control sequences must be provided within a chain and be specified in the following order (the square
brackets indicate an optional control):
X'2BD3' GLC GIR GAR [GOR] [GIR GAR [GOR]] ...[GIR GAR [GOR]] [UCT]
Note that the GLC can be preceded with any chained PTOCA control sequence other than GLC, GIR, GAR,
or GOR.


Control Sequence Summary
In this chapter the control sequences are listed in alphabetical order. The following is a summary of those
control sequences. For a full description of chained and unchained control sequences refer to Presentation
Text Object Content Architecture Reference. [IPDS-5-084]
### Table 38. Summary of Control Sequences

| Function-Type Code | | Description | Subsets | | | |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Unchained** | **Chained** | | **PT1** | **PT2** | **PT3** | **PT4** [IPDS-5-085]|
| X'D2' | X'D3' | Absolute Move Baseline (AMB) | X | X | X | X [IPDS-5-086]|
| X'C6' | X'C7' | Absolute Move Inline (AMI) | X | X | X | X [IPDS-5-087]|
| X'D8' | X'D9' | Begin Line (BLN)—also known as Next Line | X | X | X | X [IPDS-5-088]|
| X'F2' | X'F3' | Begin Suppression (BSU) | X | X | X | X [IPDS-5-089]|
| X'E6' | X'E7' | Draw B-Axis Rule (DBR) | X | X | X | X [IPDS-5-090]|
| X'E4' | X'E5' | Draw I-Axis Rule (DIR) | X | X | X | X [IPDS-5-091]|
| X'98' | X'99' | Encrypted Data (ENC) [IPDS-5-092]| | | | |
| X'F4' | X'F5' | End Suppression (ESU) | X | X | X | X [IPDS-5-093]|
| X'8C' | X'8D' | Glyph Advance Run (GAR) | | | | X [IPDS-5-094]|
| | X'8B' | Glyph ID Run (GIR) | | | | X [IPDS-5-095]|
| | X'6D' | Glyph Layout Control (GLC) | | | | X [IPDS-5-096]|
| X'8E' | X'8F' | Glyph Offset Run (GOR) | | | | X [IPDS-5-097]|
| X'F8' | X'F9' | No Operation (NOP) | X | X | X | X [IPDS-5-098]|
| X'72' | X'73' | Overstrike (OVS) | | X | X | X [IPDS-5-099]|
| X'D4' | X'D5' | Relative Move Baseline (RMB) | X | X | X | X [IPDS-5-100]|
| X'C8' | X'C9' | Relative Move Inline (RMI) | X | X | X | X [IPDS-5-101]|
| X'EE' | X'EF' | Repeat String (RPS) | X | X | X | X [IPDS-5-102]|
| X'D0' | X'D1' | Set Baseline Increment (SBI) | X | X | X | X [IPDS-5-103]|
| X'F0' | X'F1' | Set Coded Font Local (SCFL) | X | X | X | X [IPDS-5-104]|
| X'9C' | X'9D' | Set Encrypted Alternate (SEA) [IPDS-5-105]| | | | |
| X'80' | X'81' | Set Extended Text Color (SEC) | | | X | X [IPDS-5-106]|
| X'C0' | X'C1' | Set Inline Margin (SIM) | X | X | X | X [IPDS-5-107]|
| X'C2' | X'C3' | Set Intercharacter Adjustment (SIA) | X | X | X | X [IPDS-5-108]|
| X'9A' | X'9B' | Set Key Information (SKI) [IPDS-5-109]| | | | |
| X'74' | X'75' | Set Text Color (STC) | X | X | X | X [IPDS-5-110]|
| X'F6' | X'F7' | Set Text Orientation (STO) | X | X | X | X [IPDS-5-111]|
| X'C4' | X'C5' | Set Variable-Space Character Increment (SVI) | X | X | X | X [IPDS-5-112]|
| X'78' | X'79' | Temporary Baseline Move (TBM) | | X | X | X [IPDS-5-113]|
| X'DA' | X'DB' | Transparent Data (TRN) | X | X | X | X [IPDS-5-114]|
| X'76' | X'77' | Underscore (USC) | | X | X | X [IPDS-5-115]|
| | X'6A' | Unicode Complex Text (UCT) | | | | X [IPDS-5-116]|
The following IPDS exception IDs exist when problems are found within PTOCA control sequences or the text
descriptor; refer to the Presentation Text Object Content Architecture Reference for more information about
these control sequences and exception conditions:
X'0200..01' – Text control-sequence code exception
X'0202..01' – End Suppression (ESU) control-sequence exception
X'0204..01' – EP command encountered before suppression ended
X'0206..01' – Invalid Begin Suppression (BSU)
X'020C..01' – Invalid or unsupported font local ID
X'020E..02' – Invalid or unsupported color space
X'020E..03' – Invalid or unsupported color value
X'020E..04' – Invalid percent value
X'020E..05' – Invalid or unsupported number of bits for a color component
X'020F..01' – Invalid or unsupported Set Text Orientation (STO)
X'0210..01' – Invalid or unsupported Set Inline Margin (SIM)
X'0211..01' – Invalid or unsupported Set Baseline Increment (SBI)
X'0212..01' – Invalid or unsupported intercharacter adjustment
X'0213..01' – Invalid or unsupported Absolute Move Baseline (AMB)
X'0214..01' – Invalid or unsupported Absolute Move Inline (AMI)
X'0214..03' – Unsupported baseline move
X'0215..01' – Invalid or unsupported Relative Move Inline (RMI)
X'0216..01' – Invalid or unsupported Relative Move Baseline (RMB)
X'0217..01' – Invalid or unsupported Set Variable-Space Character Increment (SVI)
X'0218..02' – Invalid, unsupported, or unavailable font ID
X'0219..01' – Invalid or unsupported value for Repeat String (RPS) repeat length
X'021A..01' – Text string length error
X'021A..03' – Invalid Unicode data
X'021B..01' – Repeat String (RPS) target-string length exception
X'021C..01' – Invalid escape sequence
X'021E..01' – Invalid text control-sequence length
X'021F..01' – Repeat String (RPS) length exception
X'023F..02' – STO-SCFL-LFE mismatch
X'0258..03' – Invalid or unsupported value for text color
X'0260..02' – Invalid or unsupported value for Logical Page Descriptor units per unit base ($X_{p}$ and I)
X'0261..02' – Invalid or unsupported value for Logical Page Descriptor units per unit base ($Y_{p}$ and B)
X'0264..02' – Invalid or unsupported value for Logical Page Descriptor unit base
X'0268..02' – Invalid or unsupported value for Logical Page Descriptor inline-sequence direction
X'0269..02' – Invalid baseline-sequence direction in the LPD command
X'026A..02' – Invalid or unsupported value for Logical Page Descriptor initial I print coordinate
X'026B..02' – Invalid or unsupported value for Logical Page Descriptor initial B print coordinate
X'0280..02' – Invalid or unsupported rule width
X'0282..02' – Invalid or unsupported rule length
X'0298..01' – Invalid or unsupported suppression ID
X'0298..03' – Invalid or unsupported value for Temporary Baseline Move control sequence
X'029A..01' – Invalid overstrike character increment [IPDS-5-117]


X'029B..01' – UCT parameter values for CTLNGTH, UCTVERS, BIDICT, or GLYPHCT are invalid
X'029C..00' – Font Mismatch for a GLC chain
X'029C..01' – Font format not valid for use with glyph layout control sequences
X'029C..02' – Invalid glyph ID in a GIR
X'029C..03' – Unexpected control sequence in a GLC chain
X'029C..06' – GIR, GAR, or GOR control sequence found outside of a GLC chain
X'029C..08' – Glyph Advance count mismatch in a GAR or GOR
X'029C..09' – Missing font OID for a GLC chain
X'029C..0A' – Count mismatch or invalid length in a GLC
X'029C..0B' – Full Font Name specified in a GLC without font OID
X'029D..01' – Text string decryption not available
X'029D..02' – Text string decryption failed
X'029D..03' – No text string encryption key information set [IPDS-5-118]


