# Chapter 9. Bar Code Command Set
The Bar Code command set contains the commands and controls for presenting bar-coded information on a
page, a page segment, or an overlay. The IPDS printer is able to print bar code symbols from user data created
with the commands presented in this chapter.
This command set contains the following commands:
Table 49. Bar Code Commands [IPDS-9-001]

| Command | Code | Description | In BC1 Subset? |
| :--- | :--- | :--- | :---: |
| WBCC | X'D680' | “Write Bar Code Control” | Yes [IPDS-9-002]|
| WBC | X'D681' | “Write Bar Code” | Yes [IPDS-9-003]|
Bar code is a data type the printer uses to present machine-readable symbols on a page; however, most types
of bar code data also include a human-readable interpretation, along with the machine-readable code. The
host sends a Write Bar Code Control (WBCC) command to the printer to establish control parameters and
initial conditions for interpreting bar code data. The host sends Write Bar Code commands to the printer to
transmit bar code data and human-readable interpretation data.
See Figure 63  for a list of bar code types and bar code functions supported by the commands
within the bar code command set.
Bar Code Presentation Space
The bar code data is placed onto the logical page in much the same way as graphics data; refer to Chapter 8,
“Graphics Command Set”. Like the graphics data and IO-Image data, bar code symbols are developed within
an abstract presentation space before they are mapped to the bar code object area on the logical page. The
coordinate system for this presentation space is the X
bc,$Y_{bc}$ coordinate system. Unlike graphics, the entire bar
code presentation space must be mapped to the bar code object area in its original size and scale. The size of
the bar code presentation space is defined in the Bar Code Data Descriptor (BCDD) self-defining field of the
WBCC command.


Bar Code Object Area
The bar code object area is a rectangular area on the current logical page that the bar code presentation space
is mapped into. The bar code object area can be the same size, larger, or smaller than the bar code
presentation space. The coordinate system for the bar code object area is the X
oa,$Y_{oa}$ coordinate system. Refer
to “Positioning the Bar Code Presentation Space”  for more details.
The location and orientation of the bar code object area is specified in the Bar Code Area Position (BCAP) self-
defining field of the WBCC command. The bar code object area size is specified in the Bar Code Output
Control (BCOC) self-defining field of the WBCC command.
The bar code object area can overlap other output (such as text or images) specified earlier for the same
logical page. Also, the bar code object area can be overlapped by subsequent output specified by other
commands for the same logical page. Refer to “IPDS Mixing Rules”  for a description of the results
of overlapping bar code object areas.
Some printers allow the bar code object area to be colored before the bar code data is placed in the object
area; coloring is specified with triplets in the Bar Code Output Control self-defining field. The coloring appears
around and between the bars of a bar code symbol, and might make the symbol unscannable. Support for this
optional function is indicated by the X'6201' property pair that is returned in the Device-Control command-set
vector of the Sense Type and Model command reply. [IPDS-9-004]


Positioning the Bar Code Presentation Space
The bar code presentation space is mapped into the bar code object area on the logical page. It can be at an
offset from the bar code object area origin. The only mapping option defined for the Bar Code command set is
position. Unlike the other data types, bar-coded data will not be trimmed as a result of a mapping. Exception ID
X'0411..00' exists if an attempt is made to print any bar-coded data outside the bar code object area. To avoid
this exception, all symbols and human-readable interpretation printed under control of the same WBCC
command should be entirely contained within the bar code presentation space, and the entire presentation
space, after being mapped (positioned) into the object area, should fall within the object area boundaries. To
avoid the exception, the bar code symbol and human-readable interpretation must be entirely contained within
the intersection of the Bar Code presentation space and the object area. Your printer documentation describes
the extent that the position of the bar code symbol and human-readable interpretation within the Bar Code
presentation space is known by the printer.
Bar code mapping is specified in bytes 11–15 of the BCOC self-defining field. Refer to “Bar Code Output
Control”  for specific details on mapping. If the BCOC self-defining field is omitted, the size of the
bar code object area is equal to and coincident with the bar code presentation space. Figure 92 shows the bar
code presentation space mapped into the bar code object area.
Figure 92. Example of the Bar Code Presentation Space Mapped into the Bar Code Object Area
Bar Code Object Area
Bar Code
Presentation Space
Logical Page
Bar Code
Presentation Space
Position mapping as
specified in the
Bar Code Output
X
oa
$X_{bc}$
$Y_{bc}$
$X_{bc}$
$Y_{bc}$
$Y_{oa}$


Write Bar Code Control
Length X'D680' Flag CID Data (BCAP , BCOC, BCDD)
The length of the WBCC command can be:
Without CID X'002B'–X'7FFF'
With CID X'002D'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The Write Bar Code Control (WBCC) command causes the printer to enter the bar code state from the current
page, page-segment, or overlay state. The parameters of this command define the bar code presentation
space, define the bar code object area, map the bar code presentation space into the bar code object area,
and establish the initial conditions for printing the bar code data. The WBCC command is followed by zero or
more Write Bar Code (WBC) commands. Processing of bar code data ends when the printer receives the End
command in bar code state.
To associate metadata with the bar code object, one or more metadata objects can immediately follow the
WBCC command, before any other commands. Each Write Metadata Control (WMC) command causes the
printer to enter metadata state, where exactly one metadata object is included. Metadata state ends when the
printer receives the End command, at which point the printer returns to the bar code state it was in when the
WMC was received.
The WBCC commands the printer to process all Write Bar Code (WBC) commands that follow. A new WBCC
... END string is required whenever: [IPDS-9-005]
• A new bar code object area is started. [IPDS-9-006]
• The type of bar code symbol is changed (refer to byte 16 of the BCDD self-defining field). [IPDS-9-007]
• One of the parameters changes in bytes 17–26 of the Bar Code Data Descriptor self-defining field. [IPDS-9-008]
Self-Defining Fields within the Write Bar Code Control
The WBCC command contains two or three consecutive self-defining fields in the following order:
1. Bar Code Area Position (BCAP) [IPDS-9-009]
2. Bar Code Output Control (BCOC), optional [IPDS-9-010]
3. Bar Code Data Descriptor (BCDD) [IPDS-9-011]
BCOC may be omitted under certain circumstances. Each self-defining field contains a two-byte length field,
then a two-byte self-defining field ID, and finally data.
If an invalid self-defining field is specified, a self-defining field is out of order, a required self-defining field is not
specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. [IPDS-9-012]


Bar Code Area Position
The Bar Code Area Position (BCAP) is the first self-defining field in the data portion of the WBCC command.
This self-defining field defines the position and orientation of the bar code object area. Figure 93 shows the
origin and orientation of the bar code object area, as specified by the BCAP self-defining field.
Figure 93. Locating the Bar Code Object Area
Origin of Bar Code Object
Area specified in Bar Code
Bar Code Object Area
Size of Bar Code Object
Area specified in Bar Code
Orientation of Bar Code
Object Area specified in
Bar Code Area Position
(BCAP)
Logical Page
X
p
$Y_{p}$
I
B
$X_{oa}$
$Y_{oa}$


The format of the BCAP is as follows: [IPDS-9-013]

| Offset | Type | Name | Range | Meaning | BC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of BCAP | Length of BCAP, including this length field | X'000B' to end of BCAP [IPDS-9-014]|
| 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' [IPDS-9-015]|
| 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Bar code object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-9-016]|
| 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Bar code object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-9-017]|
| 8–9 | CODE | Bar code object area orientation [IPDS-9-018]| | | |
| | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' [IPDS-9-019]|
| | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' [IPDS-9-020]|
| | bit 15 | | B'0' | Reserved | B'0' [IPDS-9-021]|
| 10 | CODE | Reference system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' [IPDS-9-022]|
| 11 to end of BCAP | UNDF | | | Data without architectural definition [IPDS-9-023]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length. Bytes after byte 10 are ignored by the printer.
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Bar code object area origin X offset in L-units
These bytes specify the bar code object area origin (top-left corner) as an X
p, I, or I-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Note: Byte 10 specifies whether the bar code object area origin is measured using the $X_{p}$,$Y_{p}$
coordinate system or the I,B coordinate system.
Bytes 6–7 Bar code object area origin Y offset in L-units [IPDS-9-024]


These bytes specify the bar code object area origin (top-left corner) as a $Y_{p}$, B, or B-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Note: The current text presentation coordinate (I c, $B_{c}$) is not changed by the printing of this
object.
Bytes 8–9 Bar code object area orientation
This two-byte parameter specifies the orientation of the bar code object area, that is, the X
oa
axis of the bar code object area, in terms of an angle measured clockwise from the $X_{p}$ or I
coordinate axis. This parameter can rotate the bar code object area around the origin position
specified in bytes 4–7. Bar code symbols presented in the object area are aligned so that the
positive $X_{bc}$ axis of the bar code presentation space is parallel to, and in the same direction as,
the positive $X_{oa}$ axis of the bar code object area. The positive $Y_{oa}$ axis of the bar code object
area is rotated 90 degrees clockwise relative to the positive $X_{oa}$ axis and is in the same
direction as the positive $Y_{bc}$ axis. This parameter has no effect on the I-axis orientation or the
B-axis orientation.
The object area orientation is specified in terms of a number of degrees and a number of
minutes.
The number of degrees in the orientation is given in bits 0–8 of this two-byte parameter.
Values from 0 (B'000000000') to 359 (B'101100111') degrees are valid. Exception ID
X'0203..05' exists if a value from 360 to 511 is received.
The number of minutes in the orientation is given in bits 9–14 of this two-byte parameter.
Values from 0 (B'000000') to 59 (B'111011') minutes are valid. Exception ID X'0203..05' exists
if a value from 60 to 63 is received.
Not all printers support orientation values other than 0 degrees; the X'A0nn' property pair in
the Bar Code command-set vector in the STM reply reports the orientation support of the
printer. Exception ID X'0203..05' exists if the printer does not support the requested orientation
value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-9-025]
90 degrees [IPDS-9-026]
180 degrees [IPDS-9-027]
270 degrees [IPDS-9-028]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system
The reference coordinate system determines the orientation and origin of the bar code object
area, using either the $X_{p}$,$Y_{p}$ or the inline, baseline (I,B) coordinate system.
An inline coordinate value specified as absolute means that the value in BCAP bytes 4 and 5
forms an absolute inline coordinate location, that is, bytes 4 and 5 are offset from the I system
origin. A baseline coordinate value specified as absolute means that the value in BCAP bytes
6 and 7 forms an absolute baseline coordinate location, that is, bytes 6 and 7 are offset from [IPDS-9-029]
the B system origin.
An inline coordinate value specified as relative means that the value in BCAP bytes 4 and 5 is
an offset from the current inline coordinate location. A baseline coordinate value specified as
relative means that the value in BCAP bytes 6 and 7 is an offset from the current baseline
coordinate location. Therefore, the following applies:
• If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-9-030]
BCAP bytes 4 and 5 specify the text inline coordinate; BCAP bytes 6 and 7 specify the text
baseline coordinate.


• If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-9-031]
origin. BCAP bytes 4 and 5 specify the text inline coordinate; BCAP bytes 6 and 7 are added
to the current text baseline coordinate.
• If byte 10 equals X'40', the relative I and absolute B coordinates determine the origin. BCAP [IPDS-9-032]
bytes 4 and 5 are added to the current text inline coordinate. BCAP bytes 6 and 7 specify the
text baseline coordinate.
• If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. [IPDS-9-033]
BCAP bytes 4 and 5 are added to the current text inline coordinate. BCAP bytes 6 and 7 are
added to the current text baseline coordinate.
• If byte 10 equals X'A0', the current logical page X [IPDS-9-034]
p and $Y_{p}$ coordinates determine the origin.
When the bar code object is within a page, BCAP bytes 4–7 specify the offset from the $X_{p}$-
coordinate and $Y_{p}$-coordinate origin specified in a previously received LPP command (or
from the printer default coordinates if no LPP command received). When the bar code object
is within an overlay that is invoked using an LCC command, BCAP bytes 4–7 specify the
offset from the X
m-coordinate and $Y_{m}$-coordinate origin. When the bar code object is within
an overlay that is invoked using an IO command, BCAP bytes 4–7 specify the offset from the
X
p-coordinate and $Y_{p}$-coordinate origin specified in the IO command.
Exception ID X'0204..05' exists if an invalid reference-coordinate-system value is specified.
Bytes 11 to
end of BCAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-9-035]


Bar Code Output Control
The Bar Code Output Control (BCOC) is the second self-defining field included in the data portion of the
WBCC command. This field specifies the size of the bar code object area and the mapping of the bar code
presentation space into the bar code object area. This self-defining field is optional and can be omitted from
the WBCC command. If the BCOC field is omitted, the printer uses the following:
• The presentation space origin is located at the same point as the bar code object area origin. [IPDS-9-036]
• Bar code object area size equals the bar code presentation space size defined in the BCDD self-defining [IPDS-9-037]
field.
• No coloring. [IPDS-9-038]
• No object-level CMRs. [IPDS-9-039]
Figure 93  shows that the BCOC self-defining field specifies the size of the bar code object area.
The format of the BCOC is as follows: [IPDS-9-040]

| Offset | Type | Name | Range | Meaning | BC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010', X'0012' to end of BCOC | Length of BCOC, including this length field | X'0010', X'0012' to end of BCOC [IPDS-9-041]|
| 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' [IPDS-9-042]|
| 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-9-043]|
| 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-9-044]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units<br>Use LPD value | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-9-045]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units<br>Use LPD value | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-9-046]|
| 11 | CODE | Option | X'30' | Mapping option (position) | X'30' [IPDS-9-047]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units | X'0000'–X'7FFF' [IPDS-9-048]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units | X'0000'–X'7FFF' [IPDS-9-049]|
| 16 to end of BCOC | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'A2' Invoke Tertiary Resource triplet [IPDS-9-050]| |


Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Byte 4 Unit base
A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates that the
unit base is ten centimeters. If the BCOC self-defining field is omitted, the unit base is found in
byte 4 of the BCDD self-defining field.
The value X'02' is retired as Retired item 61.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified.
Bytes 5–6 X
oa and $Y_{oa}$ units per unit base
These bytes specify the number of units per unit base used when specifying the object area
extent or object area offset in either the X or the Y direction. For example, if the unit base is
X'00' and this value is X'3840', there are 14,400 units per ten inches (1440 units per inch). If
the BCOC self-defining field is omitted, the units per unit base are found in bytes 6–9 of the
BCDD self-defining field.
Exception ID X'0206..05' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 7–8 X
oa extent of object area in L-units
These bytes specify the $X_{oa}$ extent of the bar code object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $X_{p}$ extent and
the $X_{p}$ unit base and units per unit base of the LPD command that is current when this object is
printed in a page or overlay.
Note: For the duration of an overlay, the LPD associated with that overlay defines the current
logical page.
If the BCOC field is omitted, the $X_{oa}$ extent is specified by the $X_{bc}$ extent and the $X_{bc}$ unit base
and units per unit base in the BCDD.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 9–10 $Y_{oa}$ extent of object area in L-units
These bytes specify the $Y_{oa}$ extent of the bar code object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $Y_{p}$ extent and
the $Y_{p}$ unit base and units and units per unit base of the LPD command that is current when
this object is printed in a page or overlay.
If the BCOC field is omitted, the $Y_{oa}$ extent is specified by the $Y_{bc}$ extent and the $Y_{bc}$ unit base
and units per unit base in the BCDD.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists. [IPDS-9-051]


Byte 11 Mapping Option—(Position)
A value of X'30' indicates that the presentation space origin is offset from the object area origin
by the amounts specified in bytes 12–13 and bytes 14–15.
Any other value is invalid. The four bytes that follow specify the offset from the bar code object
area origin to the presentation space origin.
Exception ID X'0208..05' exists if an invalid mapping option is specified.
Note: For more information about mapping, refer to “Positioning the Bar Code Presentation
Space”.
Bytes 12–13 $X_{oa}$ offset in L-units from object area origin
This value is the $X_{oa}$ offset of the bar code presentation space origin (top-left corner) from the
origin of the bar code object area. The units of measure used to interpret this offset are
specified in bytes 4–6.
Property pair X'1208' in the Bar Code command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 $Y_{oa}$ offset in L-units from object area origin
This value is the $Y_{oa}$ offset of the bar code presentation space origin (top-left corner) from the
origin of the bar code object area. The units of measure used to interpret this offset are
specified in bytes 4–6.
Property pair X'1208' in the Bar Code command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of BCOC
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Write Bar Code Control triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Invoke Tertiary Resource (X'A2') Triplet” [IPDS-9-052]


Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported.
The Color Specification (X'4E') triplet and the Presentation Space Reset Mixing (X'70') triplet allow control over
the color of the bar code object area before any bar code symbols are placed in the object area. The color of
the bar code bars and HRI is specified by the color parameter in the Bar Code Data Descriptor.
Triplets that affect the color of the object area are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium.
Invoke CMR (X'92') and Invoke Tertiary Resource X'A2' Triplet Considerations
The CMRs invoked using the Invoke CMR (X'92') triplet are associated with this Bar Code object, and are used
according to the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy”  for a description of the
hierarchy. The rendering intent used for BCOCA objects is media-relative colorimetric.
The CMRs invoked using the Invoke CMR (X'92') triplet are also associated at the data-object level with any
secondary resource image objects printed in conjunction with a QR Code with Image bar code object. In
addition, specific CMRs can be invoked for each image object using the Invoke Tertiary Resource (X'A2')
triplet; such data-object-level CMRs invoked via the Invoke Tertiary Resource (X'A2') triplet take precedence
over CMRs invoked via the Invoke CMR (X'92') triplet on this BCOC. A rendering intent specified using the
Rendering Intent (X'95') triplet on the WIC2 or WOCC is used as the data-object-level rendering intent for any
such secondary resource image objects.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for the
Invoke CMR (X'92') triplet in the WBCC command. The X'F212' property pair in the Device-Control command-
set vector of an STM reply indicates support for the Invoke Tertiary Resource (X'A2') triplet in the WBCC
command.


Bar Code Data Descriptor
The Bar Code Data Descriptor (BCDD) is the last self-defining field included in the data portion of the WBCC
command. This self-defining field defines the size of the bar code presentation space, the bar code to be used,
the bar code variation (if applicable), and several other parameters and attributes of the bar code symbols
contained in the bar code presentation space. Figure 94 shows that the BCDD field specifies the bar code
presentation space and shows bar code symbols within the presentation space.
Figure 94. Bar Code Symbols within the Bar Code Presentation Space
Bar Code
Presentation
Space Origin X Exten t of Bar Code
Presentation Space
bc
Y Extent
of Bar Code
Presentation
Space
bc
+ $X_{bc}$
+ $Y_{bc}$
Bar Code Presentation Space
Each separate symbol in a given bar code presentation space is printed
under the control of its own individual Write Bar Code command and the
common Bar Code Data Descriptor of the Write Bar Code Control
command.  Each symbol is positioned within the bar code presentation
space by its symbol origin specified in bytes 1- 4 of a Write Bar Code
Control command.
Note:


The format of the BCDD is as follows: [IPDS-9-053]

| Offset | Type | Name | Range | Meaning | BC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'001B', X'001D' to end of BCDD | Length of BCDD, including this length field | X'001B', X'001D' to end of BCDD [IPDS-9-054]|
| 2–3 | CODE | SDF ID | X'A6EB' | Self-defining-field ID | X'A6EB' [IPDS-9-055]|
| 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | See byte description [IPDS-9-056]|
| 5 | X'00' | Reserved | | | X'00' [IPDS-9-057]|
| 6–7 | UBIN | X UPUB | X'0001'–X'7FFF' | Units per unit base for $X_{bc}$ | See byte description [IPDS-9-058]|
| 8–9 | UBIN | Y UPUB | X'0001'–X'7FFF' | Units per unit base for $Y_{bc}$; must be the same as X UPUB | See byte description [IPDS-9-059]|
| 10–11 | UBIN | $X_{bc}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{bc}$ extent of presentation space<br>Use WBCC BCOC value. If BCOC is absent use logical page X extent. | See byte description [IPDS-9-060]|
| 12–13 | UBIN | $Y_{bc}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{bc}$ extent of presentation space<br>Use WBCC BCOC value. If BCOC is absent use logical page Y extent. | See byte description [IPDS-9-061]|
| 14–15 | UBIN | Symbol width | X'0000'<br>X'0001'–X'7FFF' | Desired symbol width:<br>X'0000' = Not specified (use module width)<br>Desired width of bar code symbol in L-units | X'0000' [IPDS-9-062]|
| 16 | CODE | Type | See byte description | Bar code type | See byte description [IPDS-9-063]|
| 17 | CODE | Modifier | See byte description | Bar code modifier | See byte description [IPDS-9-064]|
| 18 | CODE | LID | X'00'–X'FE'<br>X'FF' | Font Local ID<br>X'FF' = Printer default | See byte description [IPDS-9-065]|
| 19–20 | CODE | Color | See byte description | Color | See byte description [IPDS-9-066]|
| 21 | UBIN | Module width | X'01'–X'FE'<br>X'FF' | Module width in mils<br>X'FF' = Printer default | See byte description [IPDS-9-067]|
| 22–23 | UBIN | Height | X'0001'–X'7FFF'<br>X'FFFF' | Element height in L-units<br>X'FFFF' = Printer default | See byte description [IPDS-9-068]|
| 24 | UBIN | Multiplier | X'01'–X'FF' | Height multiplier | See byte description [IPDS-9-069]|
| 25–26 | UBIN | W/N ratio | X'0000'<br>X'0001'–X'7FFF'<br>X'FFFF' | Wide-to-narrow ratio:<br>X'0000' = Bar Code does not use W/N ratio<br>Wide-to-narrow ratio<br>X'FFFF' = Printer default | See byte description [IPDS-9-070]|
| 27 to end of BCDD | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet [IPDS-9-071]| |
Bytes 0–1 Self-defining-field length. Bytes after byte 26 are ignored by printers that don't provide
Extended Bar Code Color Support (property pair X'4400' in the Bar Code command-set vector
of an STM reply).
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Bytes 4-26 Bar code symbol descriptor
These bytes specify the size of the bar code presentation space, the type of bar code to be
generated, and the parameters used to generate the bar code symbol. Refer to the Bar Code
Object Content Architecture Reference for a description of the bar code symbol descriptor.
The value X'02' in byte 4 is retired as Retired item 62.
Support for optional function that is specified in these fields is indicated with the following
property pairs in an STM reply:
• X'1302' in the Bar Code command-set vector – The desired symbol-width parameter is [IPDS-9-072]
supported in the bar code symbol descriptor.
• X'1304' in the Bar Code command-set vector – The full range of font local IDs is supported. [IPDS-9-073]
• X'FB00' in the Device-Control command-set vector – All architected units of measure are [IPDS-9-074]
supported.
IPDS exception IDs X'0205..05', X'0206..05', X'0207..05', and exception IDs of the form
X'04nn..nn' exist when problems are found within the BCOCA bar code symbol descriptor;
refer to the Bar Code Object Content Architecture Reference for more information about the
symbol descriptor and exception conditions.
Bytes 27 to
end
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The supported triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
Color Specification (X'4E') Triplet Considerations
The X'4400' property pair (Extended bar code color support) in the Bar Code command-set vector of an STM
reply indicates that the X'4E' triplet can be used to specify the color for the bar code symbol and HRI. Presence
of a supported X'4E' triplet overrides the color value specified in bytes 19–20 of the WBCC-BCDD. If multiple
X'4E' triplets are specified, the last one specified is used and the others are ignored. [IPDS-9-075]


Write Bar Code
```
Length X'D681' Flag CID Data
```
The length of the WBC command can be:
Without CID X'000A'–X'7FFF'
With CID X'000C'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Bar Code (WBC) command transmits BCOCA bar code symbol data for a single bar code symbol
including parameters that locate the bar code symbol origin within the bar code object area and specify the
human-readable interpretation location (if human-readable interpretation is to be presented). The printer must
support at least the symbol data defined by the BCOCA BCD1 subset. Refer to the Bar Code Object Content
Architecture Reference for a description of BCOCA bar code symbol data and the BCD1 subset. Zero or more
WBC commands follow the WBCC command.
Two flags within the bar code data have been retired by the BCOCA architecture; these flags are described in
retired items 63 and 64.
Support for optional function that is specified in the Write Bar Code command is indicated with the following
property pairs in the Bar Code command-set vector of an STM reply:
• X'1300' - Small symbol support [IPDS-9-076]
• X'1303' - Data Matrix encodation scheme support [IPDS-9-077]
• X'1305' - Bar code suppression support [IPDS-9-078]
• X'1306' - QR Code Special-Function Parameters too-much-data flag support [IPDS-9-079]
• X'1307' - Data Matrix Special-Function Parameters too-much-data flag support [IPDS-9-080]
One WBCC command can apply to many successive WBC commands. The host must send a separate WBC
command for each bar code symbol that is printed. The printer stops processing bar-coded data when the host
sends an End command to terminate bar code state.
IPDS exception IDs of the form X'04nn..nn' exist when problems are found within BCOCA bar code symbol
data; refer to the Bar Code Object Content Architecture Reference for more information about symbol data and
exception conditions. [IPDS-9-081]


