# Chapter 8. Graphics Command Set
The Graphics command set contains the IPDS commands and data controls for presenting graphics pictures
on a page, a page segment, or an overlay. The Graphics command set comprises the following commands:
Table 46. Graphics Commands [IPDS-8-001]

| Command | Code | Description | In GR1 Subset? |
| :--- | :--- | :--- | :---: |
| WGC | X'D684' | “Write Graphics Control” | Yes [IPDS-8-002]|
| WG | X'D685' | “Write Graphics” | Yes [IPDS-8-003]|
Graphics is used to present line drawings in a graphics object area on the logical page. A sequence of drawing
orders is used by the printer to construct arcs, lines, fillets, character strings, markers, and other elements that
define the drawing. These drawing orders are grouped into one or more graphics segments.
The host sends a Write Graphics Control (WGC) command to the printer to establish the control parameters
and initial drawing conditions for presenting the picture data. The graphics segments are sent to the printer as
data in one or more Write Graphics (WG) commands.
To understand the relationship between the WG command and the WGC command, it is necessary to know
how the graphics picture is developed. The following pages explain the drawing-order coordinate system, the
graphics presentation space window, and the graphics object area. [IPDS-8-004]


Drawing-Order Coordinate System
To allow the repositioning of graphics on a logical page (without changing the drawing orders), the drawing
orders specify graphics primitives in an abstract space called the graphics presentation space. This space is
the application user's view of the graphics picture. The extent of the graphics presentation space is -32,768 to
+32,767 units. Negative values are specified in twos complement form.
The graphics presentation space contains a four-quadrant, Cartesian coordinate system, called the drawing-
order coordinate system. The drawing-order coordinates are specified as Graphics X and Y coordinates, or
simply $X_{g}$ and $Y_{g}$. The $X_{g}$ and $Y_{g}$ coordinates are not the same as the logical page ($X_{p}$,$Y_{p}$) or medium ($X_{m}$,$Y_{m}$)
coordinates; for instance, the origin ($X_{g}$=0,$Y_{g}$=0) is at the center of the drawing-order coordinate system, while
the origin ($X_{m}$=0, $Y_{m}$=0) of the medium coordinate system is at a corner of the medium presentation space.
However, graphics pictures presented in the graphics object area are always aligned so that the positive $X_{g}$
axis of the graphics presentation space is in the same direction as the positive $X_{oa}$ axis of the graphics object
area. The positive $Y_{oa}$ axis of the graphics object area is rotated 90 degrees clockwise relative to the positive
$X_{oa}$ axis and is in the same direction as the negative $Y_{g}$ axis. $X_{g}$ and $Y_{g}$ units, called drawing units, are
specified in bytes 4–9 of the graphics data descriptor (GDD) self-defining field. Figure 85 shows the $X_{g}$,$Y_{g}$
coordinate system within the graphics presentation space, and also identifies the Graphics Presentation Space
Window.
Figure 85. Graphics Presentation Space. This figure shows the graphics presentation space window within the
graphics presentation space and the graphics coordinate system.
Graphics Presentation Space
+Y Axisg
+X Axisg
-Y Axisg
-X Axisg
Y Top
Limit
g
Y Bottom
Limit
g
Graphics
Presentation
Space Window
X Left Limitg X Right Limitg [IPDS-8-005]


Graphics Presentation Space Window
The graphics presentation space contains the whole drawing, but often only a portion of the picture is to be
presented on a logical page. The section of the graphics presentation space to be presented is called the
graphics presentation space window and is specified in bytes 14–21 of the GDD self-defining field; refer to
“Graphics Data Descriptor”. All graphics outside the limits of the graphics presentation space
window are trimmed to the window boundaries.
Graphics Object Area
The graphics presentation space window is mapped, using one of the defined mapping options, into the
graphics object area, that is a rectangular area on the current logical page. The graphics object area can be
larger than, equal to, or smaller than the graphics presentation space window. The coordinate system for the
graphics object area is the X
oa,$Y_{oa}$ coordinate system.
The location and orientation of the graphics object area is specified in the graphics area position (GAP) self-
defining field of the WGC command; refer to “Graphics Area Position”. The size of the graphics
object area is specified in the graphics output control (GOC) self-defining field; refer to “Graphics Output
Control”.
The graphics object area can overlay other data, such as text or images, specified earlier for the same logical
page. Also, the graphics object area can be overlapped by subsequent data specified by other commands for
the same logical page. Refer to “IPDS Mixing Rules”  for a description of the results of overlapping
print data. Figure 86  shows that the graphics presentation space window is mapped to the
graphics object area.
Some printers allow the graphics object area to be colored before the graphics data is placed in the object
area; coloring is specified with triplets in the Graphics Output Control self-defining field. Support for this
optional function is indicated by the X'6201' property pair that is returned in the Device-Control command-set
vector of the Sense Type and Model command reply. [IPDS-8-006]


Positioning the Graphics Presentation Space Window
The graphics presentation space is an abstract space within which the graphics presentation space window is
defined; only the portion of the picture within the window can be presented. The graphics object area is a
rectangular area on the logical page. The mapping of the graphics presentation space window into the
graphics object area is specified by the GOC self-defining field.
With scale-to-fit mapping, the center of the graphics presentation space window is made coincident with the
center of the graphics object area, and the graphics presentation space window is uniformly scaled to fit within
the limits of the graphics object area.
Notes:
1. IPDS printers should scale the entire GOCA presentation space window, but for some printers, graphics [IPDS-8-007]
primitives defined in terms of device pels are not scaled by this mapping. The origin of these primitives is
affected by the scaling, but the size of the primitive is not changed. Any part of the primitive that extends
outside of the object area is trimmed at the object-area boundary. The non-scaled primitives include:
• Graphics images [IPDS-8-008]
• Markers [IPDS-8-009]
• Patterns [IPDS-8-010]
• Line widths [IPDS-8-011]
• Character strings [IPDS-8-012]
2. GOCA architecture states that “the line width should be scaled when the controlling environment specifies [IPDS-8-013]
a scaling mapping of the GPS window into the usable area (object area)”.
With scale-to-fill mapping, the center of the graphics presentation space window is made coincident with the
center of the graphics object area, and the graphics presentation space window is scaled independently in the
X and Y dimensions to fill the graphics object area. The aspect ratio is not necessarily preserved by the scale-
to-fill mapping.
Note: Not all printers support the scale-to-fill mapping option; the X'F301' property pair is returned in the
Graphics command-set vector of an STM reply by those printers that do support the mapping option.
With center-and-trim mapping, the center of the graphics presentation space window is made coincident with
the center of the graphics object area, and the graphics presentation space window is presented at the size
indicated by bytes 4–21 of the GDD self-defining field; refer to “Graphics Data Descriptor”. Any
portion of the graphics presentation space window that falls outside the limits of the graphics object area is
trimmed (not printed). This type of trimming does not cause an exception.
With position-and-trim mapping, the top-left corner of the graphics presentation space window is offset from
the origin of the graphics object area, and the graphics presentation space window is presented at the size
indicated by bytes 4–21 of the GDD self-defining field. Any portion of the graphics presentation space window
that falls outside the limits of the graphics object area is trimmed. This type of trimming does not cause an
exception. A detailed description of graphics mapping follows under “Mapping Control Options”. [IPDS-8-014]


Figure 86. Graphics Mapping. This figure shows that the graphics presentation space window is mapped into
the graphics object area.
Logical Page
Type of mapping
specified in the
Graphics Output
Graphics
Presentation
Space Window
Specified
in the Graphics
Data Descriptor
(GDD)
Graphics Presentation Space
+Y Axis
g
-Y Axisg
-X Axisg
Graphics ObjectArea
+X Axisg
$X_{oa}$
$Y_{oa}$


Write Graphics Control
Length X'D684' Flag CID Data (GAP , GOC, GDD)
The length of the WGC command can be:
Without CID X'002C'–X'7FFF'
With CID X'002E'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The Write Graphics Control (WGC) command causes the printer to enter the graphics state. The parameters of
this command define the graphics presentation space window, define the graphics object area, map the
graphics presentation space window into the graphics object area, and establish the initial conditions for
interpreting the graphics data. The WGC command is followed by zero or more Write Graphics (WG)
commands. Graphics data processing ends when the printer receives an End command in the graphics state.
If not enough data is specified, exception ID X'0205..01' exists.
To associate metadata with the graphics object, one or more metadata objects can immediately follow the
WGC command, before any other commands. Each Write Metadata Control (WMC) command causes the
printer to enter metadata state, where exactly one metadata object is included. Metadata state ends when the
printer receives the End command, at which point the printer returns to the graphics state it was in when the
WMC was received.
The WGC data field consists of two or three consecutive self-defining fields in the following order:
1. Graphics area position (GAP) [IPDS-8-015]
2. Graphics output control (GOC), optional [IPDS-8-016]
3. Graphics data descriptor (GDD) [IPDS-8-017]
Each self-defining field contains a two-byte length field, a two-byte self-defining field ID, and a data field.
If an invalid self-defining field is specified, a self-defining field is out of order, a required self-defining field is not
specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. [IPDS-8-018]


Graphics Area Position
The Graphics Area Position (GAP) self-defining field is the first self-defining field in the data portion of the
WGC command. This field defines the position and orientation of the graphics object area. The origin and the
orientation of the graphics object area is defined relative to the reference coordinate system.
Figure 87. Locating, Sizing, and Orienting the Graphics Object Area
Origin of Graphics Object
Area specified in Graphics
Graphics Object Area
Size of Graphics Object
Area specified in Graphics
Orientation of Graphics
Object Area specified in
Graphics Area Position
(GAP)
Logical PageXp
$Y_{p}$ $X_{oa}$
B
I
$Y_{oa}$


The format of the GAP is as follows: [IPDS-8-019]

| Offset | Type | Name | Range | Meaning | GR1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of GAP | Length of GAP, including this length field | X'000B' to end of GAP [IPDS-8-020]|
| 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' [IPDS-8-021]|
| 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Graphics object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-8-022]|
| 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Graphics object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-8-023]|
| 8–9 | CODE | Graphics object area orientation [IPDS-8-024]| | | |
| | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' [IPDS-8-025]|
| | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' [IPDS-8-026]|
| | bit 15 | | B'0' | Reserved | B'0' [IPDS-8-027]|
| 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' [IPDS-8-028]|
| 11 to end of GAP | UNDF | | | Data without architectural definition [IPDS-8-029]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length. Bytes after byte 10 are ignored by the printer.
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Graphics object area origin X offset in L-units
These bytes specify the graphics object area origin (top-left corner) as an X
p, I, or I-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer. [IPDS-8-030]


Bytes 6–7 Graphics object area origin Y offset in L-units
These bytes specify the graphics object area origin (top-left corner) as a $Y_{p}$, B, or B-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Note: The current text presentation coordinate (I c, $B_{c}$) is not changed by the printing of this
object.
Bytes 8–9 Orientation of graphics object area
This two-byte parameter specifies the orientation of the graphics object area, that is, the X
oa
axis of the graphics object area, in terms of an angle measured clockwise from the $X_{p}$ or I
coordinate axis. This parameter rotates the graphics object area around the origin position
specified in bytes 4–7. The graphics picture presented in the object area is aligned such that
the positive X
g axis of the graphics presentation space is parallel to, and in the same direction
as, the positive $X_{oa}$ axis of the graphics object area. The positive $Y_{oa}$ axis of the graphics
object area is rotated 90 degrees clockwise relative to the positive $X_{oa}$ axis and is in the same
direction as the negative $Y_{g}$ axis. This parameter has no effect on the I-axis orientation or the
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
the Graphics command-set vector in the STM reply reports the orientation support of the
printer. Exception ID X'0203..05' exists if the printer does not support the requested orientation
value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-8-031]
90 degrees [IPDS-8-032]
180 degrees [IPDS-8-033]
270 degrees [IPDS-8-034]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system
The reference coordinate system determines the origin and orientation of the graphics object
area, using either the $X_{p}$,$Y_{p}$ or the inline-baseline (I,B) coordinate system.
An inline coordinate value specified as absolute means that the value in bytes 4 and 5 of the
GAP is at an absolute inline coordinate location, that is, bytes 4 and 5 are offset from the I
system origin. A baseline coordinate value specified as absolute means that the value in bytes
6 and 7 is specified at an absolute baseline coordinate location, that is, bytes 6 and 7 are [IPDS-8-035]
offset from the B system origin.
An inline coordinate value specified as relative means that the value in bytes 4 and 5 is an
offset from the current inline coordinate location. A baseline coordinate value specified as
relative means that the value in bytes 6 and 7 is an offset from the current baseline coordinate
location.
Therefore, the following applies: [IPDS-8-036]


• If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-8-037]
Bytes 4 and 5 specify the text inline coordinate; bytes 6 and 7 specify the text baseline
coordinate.
• If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-8-038]
origin. Bytes 4 and 5 specify the text inline coordinate; bytes 6 and 7 are added to the
current text baseline coordinate.
• If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the [IPDS-8-039]
origin. Bytes 4 and 5 are added to the current text inline coordinate; bytes 6 and 7 specify
the text baseline coordinate.
• If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. [IPDS-8-040]
Bytes 4 and 5 are added to the current text inline coordinate; bytes 6 and 7 are added to the
current text baseline coordinate.
• If byte 10 equals X'A0', the current logical page X [IPDS-8-041]
p and $Y_{p}$ coordinates determine the origin.
When the graphics object is within a page, GAP bytes 4–7 specify the offset from the $X_{p}$-
coordinate and $Y_{p}$-coordinate origin specified in a previously received LPP command (or
from the printer default coordinates if no LPP command received). When the graphics object
is within an overlay that is invoked using an LCC command, GAP bytes 4–7 specify the
offset from the X
m-coordinate and $Y_{m}$-coordinate origin. When the graphics object is within
an overlay that is invoked using an IO command, GAP bytes 4–7 specify the offset from the
X
p-coordinate and $Y_{p}$-coordinate origin specified in the IO command.
Exception ID X'0204..05' exists if an invalid reference-coordinate-system value is specified.
Bytes 11 to
end of GAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-8-042]


Graphics Output Control
The Graphics Output Control (GOC) is the second self-defining field in the data portion of the WGC command.
This self-defining field specifies the size of the graphics object area in addition to the mapping option for the
graphics presentation space window.
This self-defining field is optional and can be omitted from the WGC command. If the GOC field is omitted, the
printer uses the following:
• Mapping option X'30' (position and trim). [IPDS-8-043]
• X
oa offset and $Y_{oa}$ offset equals 0.
• Graphics object area size equals the graphics presentation space window size defined in the GDD self- [IPDS-8-044]
defining field.
• No coloring. [IPDS-8-045]
• No object-level CMRs. [IPDS-8-046]
• No object-level rendering intent. [IPDS-8-047]
The format of the GOC is as follows: [IPDS-8-048]

| Offset | Type | Name | Range | Meaning | GR1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010', X'0012' to end of GOC | Length of GOC, including this length field | X'0010', X'0012' to end of GOC [IPDS-8-049]|
| 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' [IPDS-8-050]|
| 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-8-051]|
| 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-8-052]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units<br>Use the LPD value | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-8-053]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units<br>Use the LPD value | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-8-054]|
| 11 | CODE | Mapping control | X'10'<br>X'20'<br>X'30'<br>X'60' | Mapping control option:<br>Scale to fit<br>Center and trim<br>Position and trim<br>Scale to fill | X'10'<br>X'20'<br>X'30' [IPDS-8-055]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units; (for position and trim only) | X'0000'–X'7FFF' [IPDS-8-056]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units; (for position and trim only) | X'0000'–X'7FFF' [IPDS-8-057]|
| 16 to end of GOC | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-8-058]| |
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
unit base is ten centimeters.
The value X'02' is retired as Retired item 57.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified.
Bytes 5–6 $X_{oa}$ and $Y_{oa}$ units per unit base
These bytes specify the number of units per unit base used when specifying the object area
extent or object area offset in either the X or the Y direction. For example, if the unit base is
X'00' and this value is X'3840', there are 14,400 units per ten inches (1440 units per inch).
Exception ID X'0206..05' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 7–8 $X_{oa}$ extent of object area in L-units
These bytes specify the $X_{oa}$ extent of the graphics object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $X_{p}$ extent and
the $X_{p}$ unit base and units per unit base of the LPD command that is current when this object is
printed in a page or overlay.
Note: For the duration of an overlay, the LPD associated with that overlay defines the current
logical page.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 9–10 $Y_{oa}$ extent of object area in L-units
These bytes specify the $Y_{oa}$ extent of the graphics object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $Y_{p}$ extent and [IPDS-8-059]


the $Y_{p}$ unit base and units and units per unit base of the LPD command that is current when
this object is printed in a page or overlay.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Byte 11 Mapping control option. The option values are:
• X'10'—Scale to fit [IPDS-8-060]
• X'20'—Center and trim [IPDS-8-061]
• X'30'—Position and trim [IPDS-8-062]
• X'50'—Retired item 135 [IPDS-8-063]
• X'60'—Scale to fill [IPDS-8-064]
Exception ID X'0208..05' exists if an invalid or unsupported mapping option is specified.
Refer to “Mapping Control Options”  for more information.
Bytes 12–13 $X_{oa}$ offset in L-units from object area origin
The $X_{oa}$ offset field is ignored unless byte 11 contains X'30'. This value is the $X_{oa}$ offset of the
graphics presentation space window (top-left corner) from the origin of the graphics object
area. The units of measure used to interpret this offset are specified in bytes 4–6.
Property pair X'1208' in the Graphics command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 $Y_{oa}$ offset in L-units from object area origin
The $Y_{oa}$ offset field is ignored unless byte 11 contains X'30'. This value is the $Y_{oa}$ offset of the
graphics presentation space window (top-left corner) from the origin of the graphics object
area. The units of measure used to interpret this offset are specified in bytes 4–6.
Property pair X'1208' in the Graphics command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of GOC
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in an STM reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Write Graphics Control triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet” [IPDS-8-065]


Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported.
The Color Specification (X'4E') triplet and the Presentation Space Reset Mixing (X'70') triplet allow control over
the color of the graphics object area before any graphics data is placed in the object area. The color of the
graphics data is specified by one or more GOCA drawing orders in a WG command.
Triplets that affect the color of the object area are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium.
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
The invoked CMRs and the specified GOCA rendering intent are associated only with this GOCA object, and
are used according to the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy”  for a description
of the hierarchy.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet will be used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the WGC command. [IPDS-8-066]


Mapping Control Options
Graphics mapping control options are defined as follows:
Scale-to-Fit Mapping
The center of the graphics presentation space window is mapped to the center of the graphics object area.
Graphics data is uniformly scaled by the printer, so that the picture within the graphics presentation space
window fits entirely within the graphics object area at the maximum size. The scale factor chosen to generate
this maximum fit is applied equally along both dimensions of the picture so that the aspect ratio of the picture in
the graphics object area is the same as the aspect ratio of the picture in the graphics presentation space
window.
Notes:
1. IPDS printers should scale the entire GOCA presentation space window, but for some printers, graphics [IPDS-8-067]
primitives defined in terms of device pels are not scaled by this mapping. The origin of these primitives is
affected by the scaling, but the size of the primitive is not changed. Any part of the primitive that extends
outside of the object area is trimmed at the object-area boundary. The non-scaled primitives include:
• Graphics images [IPDS-8-068]
• Markers [IPDS-8-069]
• Patterns [IPDS-8-070]
• Line widths [IPDS-8-071]
• Character strings [IPDS-8-072]
2. GOCA architecture states that “the line width should be scaled when the controlling environment specifies [IPDS-8-073]
a scaling mapping of the GPS window into the usable area (object area)”.
Figure 88  shows the result of scale-to-fit mapping. In this example, the graphics object area is
larger than the graphics presentation space window; therefore, the graphics presentation space window is
proportionally enlarged to fit into the graphics object area. That is, the entire graphics drawing contained within
the graphics presentation space window is enlarged uniformly until one dimension matches that of the graphics
object area.
Parameters in the GAP self-defining field specify the location and orientation of the graphics object area on the
logical page.


Figure 88. An Example of Graphics Scale-to-Fit Mapping
Logical Page
Graphics ObjectArea
Graphics
Presentation
Space Window
Specified
in the Graphics
Data Descriptor
(GDD)
Graphics Presentation Space
Scale-to-fit
mapping specified
in the Graphics
+X Axis
g
+Y Axisg
-X Axisg
(x = +32,767, y = +32,767)g g
(x = -32,768, y = -32,768)g g
-Y Axisg
$X_{oa}$
$Y_{oa}$ Graphics
Presentation
Space Window


Scale-to-Fill Mapping
The center of the graphics presentation space window is mapped to the center of the graphics object area, and
the graphics presentation space window is scaled independently in the X and Y dimensions to fill the graphics
object area. The scale factor chosen to generate this maximum fit can be different in X and Y dimensions and
therefore the aspect ratio is not necessarily preserved by the scale-to-fill mapping.
Note: Not all printers support the scale-to-fill mapping option; the X'F301' property pair is returned in the
Graphics command-set vector of an STM reply by those printers that do support the mapping option.
Figure 89. Example of Graphics Scale-to-Fill Mapping
Logical Page
Graphics ObjectArea
Graphics
Presentation
Space Window
Specified
in the Graphics
Data Descriptor
(GDD)
Graphics Presentation Space
Scale-to-fill
mapping specified
in the Graphics
+X Axis
g
+Y Axisg
-X Axisg
(x = +32,767, y = +32,767)g g
-Y Axisg
$X_{oa}$
$Y_{oa}$
(x = -32,768, y = -32,768)g g [IPDS-8-074]


Center-and-Trim Mapping
The center of the graphics presentation space window is mapped to the center of the graphics object area. The
graphics data is presented at the size specified in the GDD self-defining field. As a result, the size and aspect
ratio of the picture in the graphics object area is the same as the size and aspect ratio of the picture in the
graphics presentation space window. Any portion of the graphics presentation space window that falls outside
the graphics object area is trimmed to the graphics object area boundaries. This type of trimming does not
cause an exception.
Figure 90 shows the result of center-and-trim mapping. In this example, the graphics object area is larger in
both dimensions than the graphics presentation space window; therefore, none of the graphics presentation
space window is trimmed. The center of the graphics presentation space coincides with the center of the
graphics object area, and the boundaries of the graphics object area determine the limits of the graphics
picture.
Parameters in the GDD self-defining field specify the size of the graphics presentation space window.
Parameters in the GAP self-defining field specify the location and orientation of the graphics object area on the
logical page.
Figure 90. Example of Graphics Center-and-Trim Mapping
Logical Page
Graphics ObjectArea
Graphics
Presentation
Space Window
specified
in the Graphics
Data Descriptor
(GDD)
Graphics Presentation Space
Center-and-trim
mapping specified
in the Graphics
+X Axis
g
+Y Axisg
-Y Axisg
(x = -32,768, y = -32,768)g g
(x = +32,767, y = +32,767)g g
-X Axisg
$X_{oa}$
$Y_{oa}$


Position-and-Trim Mapping
The top-left corner of the graphics presentation space window is mapped to the graphics object area, using the
specified offset from the graphics object area origin. It is presented at the size specified in the GDD self-
defining field. As a result, the size and aspect ratio of the picture in the graphics object area is the same as the
size and aspect ratio of the picture in the graphics presentation space window. Any portion of the graphics
presentation space window that falls outside the graphics object area is trimmed to the graphics object area
boundaries. This type of trimming does not cause an exception.
Figure 91 shows the result of position-and-trim mapping. In this example, the right and bottom edges of the
graphics presentation space window fall outside the graphics object area and, therefore, are trimmed. The top-
left corner of the graphics presentation space window is offset from the origin of the graphics object area by a
distance specified in the GOC self-defining field.
Parameters in the GDD self-defining field specify the size of the graphics presentation space window.
Parameters in the GAP self-defining field specify the location and orientation of the graphics object area on the
logical page.
Figure 91. Example of Graphics Position-and-Trim Mapping
Logical Page
Graphics ObjectArea
Graphics
Presentation
Space Window
specified
in the Graphics
Data Descriptor
(GDD)
Graphics Presentation Space
Position-and-trim
mapping specified
in the Graphics
+X Axis
g
+Y Axisg
-X Axisg
(x = +32,767, y = +32,767)g g
(x = -32,768, y = -32,768)g g
X offset
specified in GOC
oa
Y offset
specified
in GOC
oa-Y Axisg
$Y_{oa}$
$X_{oa}$


Graphics Data Descriptor
The Graphics Data Descriptor (GDD) is the last self-defining field in the data portion of the WGC command.
This self-defining field specifies the size and location of the graphics presentation space window in the
graphics presentation space and sets the drawing-orders default conditions. The boundaries of the graphics
presentation space window define the range of coordinate values that are mapped to the graphics object area.
Drawing orders can specify coordinates in the X'8000' to X'7FFF' range. The specified limits of the graphics
presentation space window select which part of the complete graphics presentation space picture is to be
mapped to the graphics object area. Figure 85  shows that the GDD parameters specify the size
and location of the graphics presentation space window in the graphics presentation space.
The format of the GDD is as follows: [IPDS-8-075]

| Offset | Type | Name | Range | Meaning | GR1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'001C' to end of GDD | Length of GDD, including this length field | X'001C' to end of GDD [IPDS-8-076]|
| 2–3 | CODE | SDF ID | X'A6BB' | Self-defining-field ID | X'A6BB' [IPDS-8-077]|
| 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-8-078]|
| 5 | X'00' | Reserved | | | X'00' [IPDS-8-079]|
| 6–7 | UBIN | XUPUB | X'0001'–X'7FFF' | $X_{g}$-units/unit base | X'3840' [IPDS-8-080]|
| 8–9 | UBIN | YUPUB | X'0001'–X'7FFF' | $Y_{g}$-units/unit base; must be the same value as XUPUB | X'3840' [IPDS-8-081]|
| 10–11 | UBIN | XIRES | X'0000'–X'7FFF' | Graphics image resolution in the X direction | X'0000' [IPDS-8-082]|
| 12–13 | UBIN | YIRES | X'0000'–X'7FFF' | Graphics image resolution in the Y direction | X'0000' [IPDS-8-083]|
| 14–15 | SBIN | $X_{g}$ left limit | X'8000'–X'7FFF' | $X_{g}$ left limit of graphics presentation space window | X'8000'–X'7FFF' [IPDS-8-084]|
| 16–17 | SBIN | $X_{g}$ right limit | X'8000'–X'7FFF' | $X_{g}$ right limit of graphics presentation space window | X'8000'–X'7FFF' [IPDS-8-085]|
| 18–19 | SBIN | $Y_{g}$ top limit | X'8000'–X'7FFF' | $Y_{g}$ top limit of graphics presentation space window | X'8000'–X'7FFF' [IPDS-8-086]|
| 20–21 | SBIN | $Y_{g}$ bottom limit | X'8000'–X'7FFF' | $Y_{g}$ bottom limit of graphics presentation space window | X'8000'–X'7FFF' [IPDS-8-087]|
| 22–27 | X'00...00' | Reserved | | | X'00...00' [IPDS-8-088]|
| 28 to end | | Defaults | See GOCA Reference | Initial graphics default conditions: self-describing instructions that set the drawing defaults for the picture | All defaults allowed by the supported GOCA subset [IPDS-8-089]|


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
unit base is ten centimeters.
The value X'02' is retired as Retired item 58.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified.
Byte 5 Reserved
Bytes 6–7 X
g-units per unit base
Exception ID X'0206..05' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 8–9 $Y_{g}$-units per unit base; must be the same as bytes 6 and 7
The values specified in bytes 4–9 are the units of measure for all positioning in the graphics
presentation space. These units of measure are used in positioning the graphics presentation
space window and are used in various GOCA drawing orders.
Exception ID X'0206..05' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 10–11 Image resolution in the X direction for all images in the graphics object
This field specifies the image points per unit base in the X direction for all images in the
graphics object. X'0000' indicates that no resolution value has been specified.
Image resolution values allow a printer to maintain the size of the image when scaling or when
resolution correcting the GOCA object. Not all IPDS printers use this resolution information. If
an image resolution is not specified or not supported, the image is resolution corrected by the
printer based on assumptions. Refer to the implementation note in the description of the Begin
Image orders within the GOCA Reference. Property pair X'1207' in the Graphics command-set
vector of an STM reply indicates that the printer uses this GDD image-resolution parameter.
If an invalid value is specified, exception ID X'0206..05' exists.
Bytes 12–13 Image resolution in the Y direction for all images in the graphics object
This field specifies the image points per unit base in the Y direction for all images in the
graphics object. X'0000' indicates that no resolution value has been specified.
Image resolution values allow a printer to maintain the size of the image when scaling or when
resolution correcting the GOCA object. Not all IPDS printers use this resolution information.
Property pair X'1207' in the Graphics command-set vector of an STM reply indicates that the
printer uses this GDD image-resolution parameter.
If an invalid value is specified, exception ID X'0206..05' exists. [IPDS-8-090]


Bytes 14–15 $X_{g}$ left limit of the graphics presentation space window
Bytes 16–17 $X_{g}$ right limit of the graphics presentation space window
Bytes 18–19 $Y_{g}$ top limit of the graphics presentation space window
Bytes 20–21 $Y_{g}$ bottom limit of the graphics presentation space window
Note: Exception ID X'0207..05' exists if an unsupported value is specified in bytes 14–15, 16–
17, 18–19, or 20–21. Also, if the graphics presentation space window values are ill
defined ($X_{g}$ left limit is equal to or to the right of $X_{g}$ right limit, or $Y_{g}$ bottom limit is equal
to or above $Y_{g}$ top limit), exception ID X'0207..05' exists.
Bytes 22–25 Retired item 59
Bytes 26–27 Retired item 60
Bytes 28 to
end of
command
Defaults
Self-describing instructions that set the current defaults for the picture; refer to the MO:DCA
appendix in the GOCA Reference for a description of the self-describing instructions.
Notes:
1. Default values for Normal Line Width (set-byte value X'11') and Process Color (set-byte [IPDS-8-091]
value X'10') are not supported by all IPDS printers and the set-byte values for these
attributes should not be specified for printers that do not support these default values.
Support for Normal Line Width is indicated by property pair X'4108' in the Graphics
command-set vector of an STM reply; support for Process Color is indicated by property
pair X'4109'. Exception ID X'0300..02' exists if the Set Current Defaults instruction
attempts to set an invalid or unsupported attribute in byte 2.
If a default color is specified by both the Drawing Attributes Set (X'00') and the Process
Color Set (X'10'), the last-specified color is used.
2. Line End and Line Join are not supported by all IPDS printers and the mask bits for these [IPDS-8-092]
attributes should not be set for printers that do not support these drawing orders. Support
for Line End is indicated by property pair X'4110' in the Graphics command-set vector of
an STM reply; support for Line Join is indicated by property pair X'4111'. Exception ID
X'0300..02' exists if the Set Current Defaults instruction attempts to set an invalid or
unsupported mask attribute in bytes 3 and 4. [IPDS-8-093]


Write Graphics
Length X'D685' Flag CID BSI and Drawing Orders
The length of the WG command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Graphics (WG) command transmits graphics data to the printer. The data consists of graphics
segments, that contain the drawing orders that define the picture in the graphics presentation space. Zero or
more WG commands follow the WGC command. The Write Graphics command carries the GOCA Begin
Segment Introducer and GOCA drawing orders; refer to the Graphics Object Content Architecture for
Advanced Function Presentation for a description of these structures.
Only immediate mode is supported, that means that drawing orders are processed in the picture as these
orders are received. The printer does not retain or store the segments. Receipt of the first segment starts the
drawing process.
There are no restrictions on how much or how little data is sent to the printer in a single WG command, except
for the 32K length limit of the command. A WG command, for example, can transmit partial segments, full
segments, multiple segments, or any combination of these. The only requirement is that the data itself is
ordered in the sequence that is expected for immediate processing and that the last WG command completes
the last segment.
Note: Only Anystate commands are valid between concatenated WG commands; refer to Figure 45  for a list of Anystate commands.
Unless overridden by a Color Fidelity (X'75') triplet in a PFC command, printers that support the Set Process
Color drawing order will simulate an unsupported color value with a supported color value. This simulation
capability is in addition to the optional simulation of Standard OCA color values in the Set Color and Set
Extended Color drawing orders as reported in the Graphics command-set vector in an STM reply.
Begin Segment Introducer
BSI Zero or more drawing orders
The Begin Segment Introducer (BSI) precedes all the drawing orders that are grouped together in a graphics
segment. Refer to the description of the Begin Segment command in Graphics Object Content Architecture for
Advanced Function Presentation for a description of this command. [IPDS-8-094]


Drawing Orders
Zero or more drawing orders follow each Begin Segment Introducer. These drawing orders either specify
graphics to be printed or assign drawing attributes. Drawing orders are encoded in one of three formats:
Fixed one-byte format (order code = X'00'):
Order Code
Fixed two-byte format (order code bit 0 = B'0', bit 4 = B'1'):
Order Code Parm.
Long format:
Order Code Length Parameters
The drawing order fields are described as follows:
Order code This byte identifies the drawing order and its format.
Length field A one-byte value that specifies the length of the drawing order parameters that follow this
byte; it does not include itself.
Parameters These bytes contain the specific parameters for the drawing order.
Table 47lists all the GOCA drawing orders within the DR/2V0 and GRS3 subsets.
Table 47. Summary of GOCA Drawing Orders [IPDS-8-095]

| Code | Drawing Order | Format | DR/2V0 Subset | GRS3 Subset |
| :--- | :--- | :--- | :---: | :---: |
| X'68' | Begin Area | Fixed 2 byte | X | X [IPDS-8-096]|
| X'D1' | Begin Image | Long | X | X [IPDS-8-097]|
| X'91' | Begin Image at Current Position | Long | X | X [IPDS-8-098]|
| X'C0' | Box | Long | | X [IPDS-8-099]|
| X'80' | Box at Current Position | Long | | X [IPDS-8-100]|
| X'C3' | Character String | Long | X | X [IPDS-8-101]|
| X'83' | Character String at Current Position | Long | X | X [IPDS-8-102]|
| X'01' | Comment | Long | X | X [IPDS-8-103]|
| X'60' | End Area | Long | X | X [IPDS-8-104]|
| X'93' | End Image | Long | X | X [IPDS-8-105]|
| X'3E' | End Prolog | Fixed 2 byte | X | X [IPDS-8-106]|
| X'C5' | Fillet | Long | X | X [IPDS-8-107]|
| X'85' | Fillet at Current Position | Long | X | X [IPDS-8-108]|
| X'C7' | Full Arc | Long | X | X [IPDS-8-109]|
| X'87' | Full Arc at Current Position | Long | X | X [IPDS-8-110]|
| X'92' | Image Data | Long | X | X [IPDS-8-111]|
| X'C1' | Line | Long | X | X [IPDS-8-112]|
| X'81' | Line at Current Position | Long | X | X [IPDS-8-113]|
| X'C2' | Marker | Long | X | X [IPDS-8-114]|
| X'82' | Marker at Current Position | Long | X | X [IPDS-8-115]|
| X'00' | No Operation | Fixed 1 byte | X | X [IPDS-8-116]|
| X'E3' | Partial Arc | Long | | X [IPDS-8-117]|
| X'A3' | Partial Arc at Current Position | Long | | X [IPDS-8-118]|
| X'E1' | Relative Line | Long | X | X [IPDS-8-119]|
| X'A1' | Relative Line at Current Position | Long | X | X [IPDS-8-120]|
| X'04' | Segment Characteristics | Long | X | X [IPDS-8-121]|
| X'22' | Set Arc Parameters | Long | X | X [IPDS-8-122]|
| X'0D' | Set Background Mix | Fixed 2 byte | X | X [IPDS-8-123]|
| X'34' | Set Character Angle | Long | X | X [IPDS-8-124]|
| X'33' | Set Character Cell | Long | X | X [IPDS-8-125]|
| X'3A' | Set Character Direction | Fixed 2 byte | X | X [IPDS-8-126]|
| X'39' | Set Character Precision | Fixed 2 byte | X | X [IPDS-8-127]|
| X'38' | Set Character Set | Fixed 2 byte | X | X [IPDS-8-128]|
| X'35' | Set Character Shear | Long | X | X [IPDS-8-129]|
| X'0A' | Set Color | Fixed 2 byte | X | X [IPDS-8-130]|
| X'21' | Set Current Position | Long | X | X [IPDS-8-131]|
| X'26' | Set Extended Color | Long | X | X [IPDS-8-132]|
| X'11' | Set Fractional Line Width | Long | | X [IPDS-8-133]|
| X'18' | Set Line Type | Fixed 2 byte | X | X [IPDS-8-134]|
| X'19' | Set Line Width | Fixed 2 byte | X | X [IPDS-8-135]|
| X'37' | Set Marker Cell | Long | X | X [IPDS-8-136]|
| X'3B' | Set Marker Precision | Fixed 2 byte | X | X [IPDS-8-137]|
| X'3C' | Set Marker Set | Fixed 2 byte | X | X [IPDS-8-138]|
| X'29' | Set Marker Symbol | Fixed 2 byte | X | X [IPDS-8-139]|
| X'0C' | Set Mix | Fixed 2 byte | X | X [IPDS-8-140]|
| X'08' | Set Pattern Set | Fixed 2 byte | X | X [IPDS-8-141]|
| X'28' | Set Pattern Symbol | Fixed 2 byte | X | X [IPDS-8-142]|
| X'B2' | Set Process Color | Long | | X [IPDS-8-143]|
In addition, some IPDS printers support additional, optional drawing orders and accept, as a no operation
(NOP), the long-format Set Pick Identifier (X'43') and the reserved fixed two-byte format drawing order End
Segment (X'71').
Table 48lists the additional drawing orders supported by some IPDS printers:
Table 48. Additional Drawing Orders Supported by Some Printers [IPDS-8-144]

| Code | Drawing Order | Format |
| :--- | :--- | :--- |
| X'DE' | Begin Custom Pattern | Long [IPDS-8-145]|
| X'E5' | Cubic Bézier Curve | Long [IPDS-8-146]|
| X'A5' | Cubic Bézier Curve at Current Position | Long [IPDS-8-147]|
| X'DF' | Delete Pattern | Long [IPDS-8-148]|
| X'5E' | End Custom Pattern | Fixed 2 byte [IPDS-8-149]|
| X'FEDC' | Linear Gradient | Extended [IPDS-8-150]|
| X'FEDD' | Radial Gradient | Extended [IPDS-8-151]|
| X'20' | Set Custom Line Type | Long [IPDS-8-152]|
| X'1A' | Set Line End | Fixed 2 byte [IPDS-8-153]|
| X'1B' | Set Line Join | Fixed 2 byte [IPDS-8-154]|
| X'A0' | Set Pattern Reference Point | Long [IPDS-8-155]|
IPDS exception IDs X'020E..02', X'020E..03', X'020E..04', X'020E..05', and exception IDs of the form
X'03nn..nn' exist when problems are found within GOCA drawing orders; refer to the Graphics Object Content
Architecture for Advanced Function Presentation Reference for more information about these drawing orders
and exception conditions. [IPDS-8-156]


