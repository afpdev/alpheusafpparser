# Chapter 7. IO-Image Command Set
The IO-Image command set contains the commands for presenting IOCA image data in a page, a page
segment, or an overlay; these commands can also be used to download an IO Image as a resource. This
command set contains the following commands:
Table 42. IO-Image Commands [IPDS-7-001]

| Command | Code | Description | In IO1 Subset? |
| :--- | :--- | :--- | :--- |
| WIC2 | X'D63E' | “Write Image Control 2” | Yes [IPDS-7-002]|
| WI2 | X'D64E' | “Write Image 2” | Yes [IPDS-7-003]|
Both the IO-Image and the IM-Image command sets contain commands that enter the printer into the
respective image state, and allow the subsequent transmission of image data to the printer. The IO-Image
command-set commands provide a variety of functional additions, such as image compression and arbitrary
scaling, over the IM-Image command-set commands. A printer can implement both the IO-Image and the IM-
Image command sets. The following table indicates the major differences.
Table 43. IM-Image and IO-Image Comparison [IPDS-7-004]

| Functions or Controls | IM Image | IO Image |
| :--- | :---: | :---: |
| Replicate and trim input to fill output | X | O [IPDS-7-005]|
| Bilevel image | X | X [IPDS-7-006]|
| Bilevel image with a color specification | X | O [IPDS-7-007]|
| Unpadded recording algorithm | X | O [IPDS-7-008]|
| Grayscale image | | X [IPDS-7-009]|
| Compression | | X [IPDS-7-010]|
| Resolution-independent data presentation | | X [IPDS-7-011]|
| Resolution correction to device resolution | | X [IPDS-7-012]|
| Scaling | | X [IPDS-7-013]|
| Position and trim | | X [IPDS-7-014]|
| Center and trim | | X [IPDS-7-015]|
| Scale to fill | | O [IPDS-7-016]|
| Full-color image | | O [IPDS-7-017]|
| Color Management Resources (CMRs); see note | | O [IPDS-7-018]|
| Image Banding | | O [IPDS-7-019]|
| Subsampling | | O [IPDS-7-020]|
| Relative resolution for a tile | | O [IPDS-7-021]|
| Tiling | | O [IPDS-7-022]|
| Transparency masks | | O [IPDS-7-023]|
| Bit allocation | | O [IPDS-7-024]|
| Area coloring | | O [IPDS-7-025]|
| IOCA tile resources | | O [IPDS-7-026]|
| Multiple image contents in an IOCA image segment | | O [IPDS-7-027]|

**Key:** X: supported, O: optional function (supported by some, but not all printers)
Note: Color Management Resources (CMRs) are associated with all print data using the CMR-usage hierarchy.
CMRs can be directly tied to an IO Image by specifying the Invoke CMR (X'92') triplet in the WIC2
command. CMRs cannot be directly tied to an IM Image and the rendering intent used for IM Image is
media-relative colorimetric; but IM-Image data can be processed with CMRs from another level of the
hierarchy.
IO-Image data can be generated at any arbitrary resolution. It can even be generated at resolutions that are
different in the two dimensions. Printers that accept IO Image are capable of correcting these arbitrary
resolutions to their own device-specific pel resolutions in order to present the image at the size dictated by the
IO-Image commands.


The IO-Image Presentation Space
IO-Image data is placed onto the logical page in much the same way as graphics data; refer to Chapter 8,
“Graphics Command Set”. Like the graphics data and bar code data, IO-Image data is mapped from an
abstract presentation space to the image object area on the logical page. The coordinate system for this
presentation space is the $X_{io}$,$Y_{io}$ coordinate system. Unlike graphics, the entire image presentation space is
mapped to the IO-Image object area. There is no concept of a presentation space window in this command
set. The size of the image presentation space is defined in the Image Data Descriptor (IDD) self-defining field
of the WIC2 command. One image point of an IO-Image segment is mapped to one image point of the image
presentation space.
Figure 78. IO-Image Presentation Space. This figure shows the complete image presentation space before
mapping to the image object area.
IO Image Presentation Space
$Y_{io}$
$X_{io}$


The IO-Image Object Area
The IO-Image object area is a rectangular area on the current logical page that the image presentation space
is mapped into. The IO-Image object area can be the same size, larger, or smaller than the image presentation
space. The coordinate system for the IO-Image object area is the $X_{oa}, Y_{oa}$ coordinate system.
The location and orientation of the IO-Image object area is specified in the Image Area Position (IAP) self-
defining field of the WIC2 command. The IO-Image object area size is specified in the Image Output Control
(IOC) self-defining field of the WIC2 command.
The IO-Image object area can overlap text or other object areas, such as for bar code or graphics data,
specified earlier for the same page. Also, the IO-Image object area can be overlapped by subsequent object
areas or text specified by other commands for the same page.
Some printers allow the IO-Image object area to be colored before the image data is placed in the object area;
coloring is specified with triplets in the Image Output Control self-defining field. Support for this optional
function is indicated by the X'6201' property pair that is returned in the Device-Control command-set vector of
the Sense Type and Model command reply.
Mapping the IO-Image Presentation Space
The mapping of the IO-Image presentation space into the IO-Image object area is specified by the IOC self-
defining field.
With scale-to-fit mapping, the center of the image presentation space is made coincident with the center of the
image object area, and the image presentation space is uniformly scaled to fit within the limits of the image
object area. Thus, the aspect ratio is preserved by the scale-to-fit mapping.
With center-and-trim mapping, the center of the image presentation space is made coincident with the center
of the image object area, and the image presentation space is presented at the size specified by bytes 6–14 of
the IDD self-defining field. Any portion of the image presentation space that falls outside the limits of the image
object area is trimmed (not printed). This type of trimming does not cause an exception.
With position-and-trim mapping, the top-left corner of the image presentation space is offset from the origin of
the image object area, and the image presentation space is presented at the size specified by bytes 6–14 of
the IDD self-defining field. Any portion of the image presentation space that falls outside the limits of the image
object area is trimmed. This type of trimming does not cause an exception. For a detailed description of image
mapping, refer to “Image Output Control”.
With replicate-and-trim mapping, the origin of the image presentation space is positioned coincident with the
origin of the image object area, and the image presentation space is presented at the size specified in bytes 6–
14 of the IDD self-defining field. The image presentation space is then replicated in the X and Y directions of [IPDS-7-028]
the image object area until the object area is filled. Each new replicate of the image presentation space in the X
direction is precisely aligned with the image presentation space previously placed in the X direction. Each new
replicate of the image presentation space in the Y direction is precisely aligned with the image presentation
space previously placed in the Y direction. If the last image presentation space in either the X or Y direction fits
only partially into the image object area, the portion of the image presentation space that falls outside the
image object area is trimmed (not printed). This type of trimming does not cause an exception. All data that
falls within the image object area extents is presented, but data that falls outside of the image object area is not
presented.
Note: Not all printers support the replicate-and-trim mapping option; the X'F300' property pair is returned in the
IO-Image command-set vector of an STM reply by those printers that do support the mapping option. [IPDS-7-029]


This mapping option is used for migration from IM Images to IOCA FS10 images and is not supported
with any other IOCA function set.
With scale-to-fill mapping, the center of the image presentation space is made coincident with the center of the
image object area, and the image presentation space is scaled independently in the X and Y dimensions to fill
the image object area. The aspect ratio is not necessarily preserved by the scale-to-fill mapping.
Note: Not all printers support the scale-to-fill mapping option; the X'F301' property pair is returned in the IO-
Image command-set vector of an STM reply by those printers that do support the mapping option.
Using IO Image as a Resource
Some IPDS printers allow an IO Image to be downloaded in home state as a data object resource. An IO-
Image resource can then be included in a page or overlay using the Include Data Object (IDO) command.
Support for this function is indicated by property pair X'1202' in the IO-Image command-set vector of an STM
reply.
Exactly the same commands and parameters are used to specify an IO Image in home state as are used in
page, page segment, or overlay states except that, in home state, a Host-Assigned ID (HAID) is specified in
the IDD of the WIC2 command. Printers do not syntax check the data within the WI2 commands at download
time; the syntax checking is done later when the image is included with an IDO command.
When an IO Image is downloaded as a resource, no color mapping is done. However, when the resource is
later included in a page or overlay via an IDO command, the color mapping table (if any) that is in effect at
include time is applied to the image. [IPDS-7-030]


Write Image Control 2
Length X'D63E' Flag CID Data (IAP , IOC, IDD)
The length of the WIC2 command can be:
Without CID X'001F'–X'7FFF'
With CID X'0021'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The Write Image Control 2 (WIC2) command causes the printer to enter the IO-Image state. The parameters of
this command define the image presentation space, define the image object area, and define the mapping of
the image presentation space into the image object area. The WIC2 command is followed by zero or more
Write Image 2 (WI2) commands. Image data processing ends when the printer receives the End command in
IO-Image state. If not enough data is specified, exception ID X'0205..01' exists.
To associate metadata with the image object, one or more metadata objects can immediately follow the WIC2
command, before any other commands. Each Write Metadata Control (WMC) command causes the printer to
enter metadata state, where exactly one metadata object is included. Metadata state ends when the printer
receives the End command, at which point the printer returns to the IO-Image state it was in when the WMC
was received.
The WIC2 data field consists of two or three consecutive self-defining fields in the following order:
1. Image Area Position (IAP) [IPDS-7-031]
2. Image Output Control (IOC), optional [IPDS-7-032]
3. Image Data Descriptor (IDD) [IPDS-7-033]
Each self-defining field contains a two-byte length field, then a two-byte self-defining field ID, and finally a data
field.
If an invalid self-defining field is specified, a self-defining field is out of order, a required self-defining field is not
specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. [IPDS-7-034]


Figure 79. Locating, Sizing, and Orienting the Image Object Area
Origin of IO-Image Object
Area specified in Image
Size of IO-Image Object
Area specified in Image
Orientation of IO-Image
Object Area specified in
IO-Image Object Area
Logical Page
$X_{p}$
$Y_{p}$
$Y_{oa}$
$X_{oa}$
I
B


Image Area Position
The IAP is a mandatory self-defining field in the WIC2 command. It defines the position and orientation of the
image object area. The origin and orientation of the image object area are defined relative to the reference
coordinate system.
The format of the IAP is as follows: [IPDS-7-035]

| Offset | Type | Name | Range | Meaning | IO1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of IAP | Length of IAP, including this length field | X'000B' to end of IAP [IPDS-7-036]|
| 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' [IPDS-7-037]|
| 4–5 | SBIN | X offset | X'8000' – X'7FFF' | Image object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-7-038]|
| 6–7 | SBIN | Y offset | X'8000' – X'7FFF' | Image object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' [IPDS-7-039]|
| 8–9 | CODE | Image object area orientation [IPDS-7-040]| | | |
| | bits 0–8 | Degrees | B'000000000' – B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' [IPDS-7-041]|
| | bits 9–14 | Minutes | B'000000' – B'111011' | Number of minutes (0–59) in the orientation | B'000000' [IPDS-7-042]|
| | bit 15 | | B'0' | Reserved | B'0' [IPDS-7-043]|
| 10 | CODE | Coordinate system | X'00' X'20' X'40' X'60' X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00' X'20' X'40' X'60' X'A0' [IPDS-7-044]|
| 11 to end of IAP | UNDF | | | Data without architectural definition [IPDS-7-045]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length. Bytes after byte 10 are ignored by the printer.
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 IO-Image object area origin X offset in L-units
These bytes specify the image object area origin (top-left corner) as an $X_{p}, I$, or $I$-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay. [IPDS-7-046]


Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Bytes 6–7 IO-Image object area origin Y offset in L-units
These bytes specify the image object area origin (top-left corner) as a $Y_{p}$, B, or B-offset
coordinate position. The units of measure used to interpret this L-unit value are specified in the
LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Note: The current text presentation coordinate ($I_{c}$, $B_{c}$) is not changed by the printing of this
object.
Bytes 8–9 Orientation of image object area
This two-byte parameter specifies the orientation of the image object area, that is, the $X_{oa}$ axis
of the image object area, in terms of an angle measured clockwise from the $X_{p}$ or $I_{c}$ coordinate
axis. This parameter rotates the image object area around the origin position specified in bytes
4–7 above. The image presented in the object area is aligned such that the positive $X_{io}$ axis of
the image presentation space is parallel to, and in the same direction as, the positive $X_{oa}$ axis
of the object area. The positive $Y_{oa}$ axis of the image object area is rotated 90 degrees
clockwise relative to the positive $X_{oa}$ axis and is in the same direction as the positive $Y_{io}$ axis.
This parameter has no effect on the I-axis orientation or the B-axis orientation.
The object area orientation is specified in terms of a number of degrees and a number of
minutes.
The number of degrees in the orientation is given in bits 0–8 of this two-byte parameter.
Values from 0 (B'000000000') to 359 (B'101100111') degrees are valid. Exception ID
X'0203..05' exists if a value from 360 to 511 is received.
The number of minutes in the orientation is given in bits 9–14 of this two-byte parameter.
Values from 0 (B'000000') to 59 (B'111011') minutes are valid. Exception ID X'0203..05' exists
if a value from 60 to 63 is received.
Not all printers support orientation values other than 0 degrees; the X'A0nn' property pair in
the IO-Image command-set vector in the STM reply reports the orientation support of the
printer. Exception ID X'0203..05' exists if the printer does not support the requested orientation
value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-7-047]
90 degrees [IPDS-7-048]
180 degrees [IPDS-7-049]
270 degrees [IPDS-7-050]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system.
The reference coordinate system determines the origin and orientation of the image object
area, using either the $X_{p}$,$Y_{p}$ or the inline-baseline (I,B) coordinate system.
An inline coordinate value specified as absolute means that the value in bytes 4 and 5 of the
IAP is an absolute inline coordinate location, that is, bytes 4 and 5 are offset from the I system
origin. A baseline coordinate value specified as absolute means that the value in IAP bytes 6
and 7 is an absolute baseline coordinate location, that is, bytes 6 and 7 are offset from the B
system origin.
An inline coordinate value specified as relative means that the value in IAP bytes 4 and 5 is an
offset from the current inline coordinate location. A baseline coordinate value specified as
relative means that the value in IAP bytes 6 and 7 is an offset from the current baseline
coordinate location. Therefore, the following applies: [IPDS-7-051]


• If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-7-052]
IAP bytes 4 and 5 specify the text inline coordinate; IAP bytes 6 and 7 specify the text
baseline coordinate.
• If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-7-053]
origin. IAP bytes 4 and 5 specify the text inline coordinate; IAP bytes 6 and 7 are added to
the current text baseline coordinate.
• If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the [IPDS-7-054]
origin. IAP bytes 4 and 5 are added to the current text inline coordinate. IAP bytes 6 and 7
specify the text baseline coordinate.
• If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. IAP [IPDS-7-055]
bytes 4 and 5 are added to the current text inline coordinate. IAP bytes 6 and 7 are added to
the current text baseline coordinate.
• If byte 10 equals X'A0', the current logical page $X_{p}$ and $Y_{p}$ coordinates determine the origin. [IPDS-7-056]
When the image is within a page, IAP bytes 4–7 specify the offset from the $X_{p}$-coordinate
and $Y_{p}$-coordinate origin specified in a previously received LPP command (or from the
printer default coordinates if no LPP command received). When the image is within an
overlay that is invoked using an LCC command, IAP bytes 4–7 specify the offset from the
$X_{m}$-coordinate and $Y_{m}$-coordinate origin. When the image is within an overlay that is invoked
using an IO command, IAP bytes 4–7 specify the offset from the $X_{p}$-coordinate and $Y_{p}$-
coordinate origin specified in the IO command.
Exception ID X'0204..05' exists if an invalid reference-coordinate-system value is specified.
Bytes 11 to
end of IAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-7-057]


Image Output Control
The Image Output Control is a self-defining field that specifies the size of the image object area and the option
for mapping the image presentation space into the image object area.
This self-defining field is optional and can be omitted from the WIC2 command. If the IOC field is omitted, the
printer uses the following:
• Mapping option X'30' (position and trim). [IPDS-7-058]
• $X_{oa}$ offset and $Y_{oa}$ offset equals 0. [IPDS-7-059]
• Image object area size equals the image presentation space size defined in the IDD self-defining field. [IPDS-7-060]
• No coloring. [IPDS-7-061]
• No object-level CMRs. [IPDS-7-062]
• No object-level rendering intent. [IPDS-7-063]
The format of the IOC is as follows: [IPDS-7-064]

| Offset | Type | Name | Range | Meaning | IO1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010', X'0012' to end of IOC | Length of IOC, including this length field | X'0010', X'0012' to end of IOC [IPDS-7-065]|
| 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' [IPDS-7-066]|
| 4 | CODE | Unit base | X'00' X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-7-067]|
| 5–6 | UBIN | UPUB | X'0001' – X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-7-068]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001' – X'7FFF' X'FFFF' | $X_{oa}$ extent of IO-Image object area in L-units<br>X'FFFF' = Use the LPD value. | See byte description. [IPDS-7-069]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001' – X'7FFF' X'FFFF' | $Y_{oa}$ extent of IO-Image object area in L-units<br>X'FFFF' = Use the LPD value. | See byte description. [IPDS-7-070]|
| 11 | CODE | Mapping control option | X'10' X'20' X'30' X'41' X'42' X'50' X'60' | Mapping control option:<br>X'10' = Scale to fit<br>X'20' = Center and trim<br>X'30' = Position and trim<br>X'41' = Point to pel<br>X'42' = Point to pel w/ double dot<br>X'50' = Replicate & trim (FS10 only)<br>X'60' = Scale to fill | X'10' X'20' X'30' X'41' X'42' [IPDS-7-071]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000' – X'7FFF' | $X_{oa}$ offset in L-units (for position-and-trim only) | X'0000' – X'7FFF' [IPDS-7-072]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000' – X'7FFF' | $Y_{oa}$ offset in L-units (for position-and-trim only) | X'0000' – X'7FFF' [IPDS-7-073]|
| 16– | Triplets | | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-7-074]| |
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
The value X'02' is retired as Retired item 55.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified.
Bytes 5–6 Units per unit base
These bytes specify the number of units per unit base used when specifying the object area
extent or object area offset in either the X or the Y direction. For example, if the unit base is
ten inches and the units per unit base is X'3840', there are 1440 units per inch.
Exception ID X'0206..05' exists if an invalid or unsupported units-per-unit-base value is
specified.
Note: Bytes 4–6 describe the resolution of the IO-Image object area; they do not describe the
resolution of the IOCA data.
Bytes 7–8 $X_{oa}$ extent of object area in L-units
These bytes specify the $X_{oa}$ extent of the IO-Image object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $X_{p}$ extent and
the $X_{p}$ unit base and units per unit base of the LPD command that is current when this object is
printed in a page or overlay.
Note: For the duration of an overlay, the LPD associated with that overlay defines the current
logical page.
The printer must support all values in the range X'0001'–X'7FFF'; refer to “L-Unit Range
Conversion Algorithm”. The printer must support X'FFFF' for pages and overlays. [IPDS-7-075]


The printer may optionally support X'FFFF' for page segments. Property pair X'1206' in the IO-
Image command-set vector of an STM reply indicates that the value X'FFFF' (use LPD value)
is supported within page segments.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 9–10 $Y_{oa}$ extent of object area in L-units
These bytes specify the $Y_{oa}$ extent of the IO-Image object area in L-units using the units of
measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $Y_{p}$ extent and
the $Y_{p}$ unit base and units and units per unit base of the LPD command that is current when
this object is printed in a page or overlay.
The printer must support all values in the range X'0001'–X'7FFF'; refer to “L-Unit Range
Conversion Algorithm”. The printer must support X'FFFF' for pages and overlays.
The printer may optionally support X'FFFF' for page segments. Property pair X'1206' in the IO-
Image command-set vector of an STM reply indicates that the value X'FFFF' (use LPD value)
is supported within page segments.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Byte 11 Mapping options
This byte specifies how the image presentation space is mapped to the image object area.
The option values are:
X'10'—Scale to fit
X'20'—Center and trim
X'30'—Position and trim
X'41'—Image point-to-pel
X'42'—Image point-to-pel with double dot.
X'50'—Replicate and trim (FS10 images only)
X'60'—Scale to fill
The size of the image presentation space and the resolution of the image points in the image
presentation space are defined in the IDD self-defining field.
Resolution correction occurs in mapping options X'10', X'20', X'30', X'50', and X'60' whenever
the resolution of the image points in the image presentation space in one or both dimensions
is different from the device resolution.
The size of the image object area is defined in the IOC self-defining field using the units of
measure specified in bytes 4–6 of the IOC.
Exception ID X'0208..05' exists if an invalid or unsupported mapping option is specified. [IPDS-7-076]


X'10' Scale to fit
The center of the image presentation space is mapped to the center of the image
object area. The image presentation space is uniformly scaled so that it fits entirely
within the image object area at the maximum size. The scale factor chosen to
generate this maximum fit is applied equally along both dimensions of the image so
that the aspect ratio of the image in the image object area is the same as the aspect
ratio of the image in the image presentation space.
This option ensures that all of the data in the image presentation space is presented in
the image object area at the largest size possible without image distortion.
Figure 80. Example of Scale-to-Fit Mapping
IO-Image ObjectArea
IO-Image Presentation Space
Scale-to-fit
mapping specified
in the Image
Logical Page


X'20' Center and Trim
The center of the image presentation space is mapped to the center of the image
object area. The image data is presented at the size specified in the IDD self-defining
field. As a result, the size and aspect ratio of the image in the image object area is the
same as the size and aspect ratio of the image in the image presentation space. Any
portion of the image presentation space that falls outside the image object area is
trimmed at the object area boundaries.
Figure 81. Example of Center-and-Trim Mapping
IO Image ObjectArea
IO Image Presentation Space
Logical Page
Center-and-trim
mapping specified
in the Image Output


X'30' Position and Trim
The top-left corner of the image presentation space is mapped to the image object
area using the specified offset from the image object area origin. The image data is
presented at the size specified in the IDD self-defining field. As a result, the size and
aspect ratio of the image in the image object area is the same as the size and aspect
ratio of the image in the image presentation space. Any portion of the image
presentation space that falls outside the image object area is trimmed at the object
area boundaries.
Figure 82. Example of Position-and-Trim Mapping
IO-Image Presentation Space
Logical Page
Y offset
specified
in IOC
IO-Image ObjectArea
X offset specified
in IOC
Position-and-trim
mapping specified
in the Image Output


X'41' Image point-to-pel
The origin of the image presentation space is mapped to the origin of the image object
area. Each image point in the image presentation space is mapped to a pel in the
image object area. Any portion of the image presentation space that falls outside the
image object area is trimmed at the object area boundaries.
Note: No resolution correction is required, therefore the size of the image in the image
object area is dependent on the device resolution.
X'42' Image point-to-pel with double dot
The origin of the image presentation space is mapped to the origin of the image object
area. Each image point in the image presentation space is mapped to four pels in the
image object area by doubling the image point in both dimensions. Any portion of the
image presentation space that falls outside the image object area is trimmed at the
object area boundaries.
Note: No resolution correction is required, therefore the size of the image in the image
object area is dependent on the device resolution. [IPDS-7-077]


X'50' Replicate and trim
This mapping option is used for migration from IM Images to IOCA FS10 images and
is not supported with any other IOCA function set.
The origin of the image presentation space is positioned coincident with the origin of
the image object area, and the image presentation space is presented at the size
specified in bytes 6–14 of the IDD self-defining field. The image presentation space is
then replicated in the X and Y directions of the image object area until the object area
is filled. Each new replicate of the image presentation space in the X direction is
precisely aligned with the image presentation space previously placed in the X
direction. Each new replicate of the image presentation space in the Y direction is
precisely aligned with the image presentation space previously placed in the Y
direction. If the last image presentation space in either the X or Y direction fits only
partially into the image object area, the portion of the image presentation space that
falls outside the image object area is trimmed (not printed). This type of trimming does
not cause an exception. All data that falls within the image object area extents is
presented, but data that falls outside of the image object area is not presented.
Note: Not all printers support the replicate-and-trim mapping option; the X'F300'
property pair is returned in the IO-Image command-set vector of an STM reply
by those printers that do support the mapping option.
Figure 83. Example of Replicate-and-Trim Mapping
Replicate-and-trim
mapping specified in
the Image Output
IO-Image Presentation Space
Logical Page
IO-Image Object Area


X'60' Scale to fill
The center of the image presentation space is mapped to the center of the image
object area, and the image presentation space is scaled independently in the X and Y
dimensions to fill the image object area. The scale factor chosen to generate this
maximum fit can be different in X and Y dimensions and therefore the aspect ratio is
not necessarily preserved by the scale-to-fill mapping.
Note: Not all printers support the scale-to-fill mapping option; the X'F301' property
pair is returned in the IO-Image command-set vector of an STM reply by those
printers that do support the mapping option.
Figure 84. Example of IO-Image Scale-to-Fill Mapping
IO-Image ObjectArea
IO-Image Presentation Space
Scale-to-fill
mapping specified
in the Image
Logical Page


Bytes 12–13 $X_{oa}$ offset in L-units from object area origin
The $X_{oa}$ offset field is ignored unless byte 11 contains X'30'. This value is the $X_{oa}$ offset of the
image presentation space (first image point) from the origin of the IO-Image object area. The
units of measure used to interpret this offset are specified in bytes 4–6.
Property pair X'1208' in the IO-Image command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 $Y_{oa}$ offset in L-units from object area origin
The $Y_{oa}$ offset field is ignored unless byte 11 contains X'30'. This value is the $Y_{oa}$ offset of the
image presentation space (first image point) from the origin of the IO-Image object area. The
units of measure used to interpret this offset are specified in bytes 4–6.
Property pair X'1208' in the IO-Image command-set vector of an STM reply indicates support
for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of IOC
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Write Image Control 2 triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet”
Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported.
The Color Specification (X'4E') triplet and the Presentation Space Reset Mixing (X'70') triplet allow control over
the color of the image object area before any image data is placed in the object area. The WIC2-IOC does not
specify the color of the image data; refer to Table 8 for more information about specifying IO-Image
color.
Triplets that affect the color of the object area are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium. [IPDS-7-078]


Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
The invoked CMRs and the specified IOCA rendering intent are associated only with this IOCA image, and are
used according to the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy” for a description of
the hierarchy. Invoke CMR (X'92') triplets on the WIC2 command are not used with IOCA images that are
included with an IDO command; therefore, these triplets need not be kept with an IOCA image that is
downloaded as a resource in home state (whether or not the resource is captured). Because Invoke CMR
(X'92') triplets contain a HAID, these triplets should not be stored with a captured resource.
When preRIPping IOCA resources using the RPO command, CMRs can be invoked and a rendering intent can
be specified with triplets on the RPO command or by use of the ICMR and SPE commands. A preRIPped
presentation object will be printed only if the CMRs and rendering intent used during preRIP match those
selected while processing the IDO command. Refer to the RPO command description for a list of attributes
used to find an appropriate preRIPped object.
When printing an IOCA image in conjunction with a QR Code with Image bar code, data-object-level CMRs
can be invoked for the IOCA image using the Invoke Tertiary Resource (X'A2') triplet on the WBCC command
for the bar code. In addition, CMRs invoked via the Invoke CMR (X'92') triplet on the WBCC are also invoked at
the data-object-level for such IOCA images—that is, the CMRs invoked for the bar code itself are also invoked
for any secondary resource image objects of the bar code. Data-object-level CMRs invoked via the Invoke
Tertiary Resource (X'A2') triplet take precedence over data-object-level CMRs invoked via the Invoke CMR
(X'92') triplet on the WBCC. A rendering intent specified using the Rendering Intent (X'95') triplet on the WIC2
is used as the data-object-level rendering intent for IOCA images printed in conjunction with a QR Code with
Image bar code.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet will be used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the WIC2 command. The X'F212' property pair in
the Device-Control command-set vector of an STM reply indicates support for Invoke Tertiary Resource (X'A2')
triplets in the WBCC command. [IPDS-7-079]


Image Data Descriptor
The IDD is a mandatory self-defining field in the WIC2 command. It specifies parameters that define the image
presentation space size and resolution.
The format of the IDD is as follows: [IPDS-7-080]

| Offset | Type | Name | Range | Meaning | IO1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000F' to end of IDD | Length of IDD, including this length field | X'000F' to end of IDD [IPDS-7-081]|
| 2–3 | CODE | SDF ID | X'A6FB' | Self-defining-field ID | X'A6FB' [IPDS-7-082]|
| 4–5 | CODE | HAID | X'0000' X'0001' – X'7EFF' | No value assigned<br>Data object resource Host-Assigned ID | X'0000' [IPDS-7-083]|
| 6 | CODE | Unit base | X'00' X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-7-084]|
| 7–8 | UBIN | $X_{io}$ resolution | X'0001' – X'7FFF' | $X_{io}$ image points per unit base | X'0001' – X'7FFF' [IPDS-7-085]|
| 9–10 | UBIN | $Y_{io}$ resolution | X'0001' – X'7FFF' | $Y_{io}$ image points per unit base | X'0001' – X'7FFF' [IPDS-7-086]|
| 11–12 | UBIN | $X_{io}$ extent | X'0001' – X'7FFF' | $X_{io}$ extent of the image presentation space in image points | X'0001' – X'7FFF' [IPDS-7-087]|
| 13–14 | UBIN | $Y_{io}$ extent | X'0001' – X'7FFF' | $Y_{io}$ extent of the image presentation space in image points | X'0001' – X'7FFF' [IPDS-7-088]|
| 15– | | | | Zero or more of the following IOCA self-defining fields:<br>X'F4' Set Extended Bilevel Image Color<br>X'F6' Set Bilevel Image Color [IPDS-7-089]| |
Bytes 0–1 Length of the IDD self-defining field
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Data descriptor self-defining-field ID
Bytes 4–5 Data object resource Host-Assigned ID
For a page, page segment, or overlay state IO Image, this field is not used and is ignored by
the printer. X'0000' should be specified in this case.
For a home state IO Image, this field species the Host-Assigned ID for this IO-Image data
object resource. Printers that support IO Image as a resource support all values in the range
X'0001'–X'7EFF' and return property pair X'1202' in the IO-Image command-set vector of an
STM reply. If an invalid Host-Assigned ID value is specified, exception ID X'020D..11' exists. If
the Host-Assigned ID is already in use for another data object resource, exception ID
X'020D..16' exists.
Byte 6 Unit base
This byte specifies the measurement unit to be used. X'00' indicates the measurement unit for
the X and Y dimensions is ten inches; X'01' indicates the measurement unit is ten centimeters.
The value X'02' is retired as Retired item 56.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified. [IPDS-7-090]


Bytes 7–8 $X_{io}$ image points per unit base
These bytes specify the $X_{io}$ image points per unit base in the image presentation space. For
example, if the unit base is ten inches and this value is 2000, the image presentation space $X_{io}$
resolution is 200 image points per inch.
Exception ID X'0206..05' exists if an invalid or unsupported image-points-per-unit-base value
is specified.
Note: Bytes 6–10 describe the resolution of the IO-Image presentation space; they do not
describe the resolution of the IOCA image. The resolution specified in this self-defining
field is used by the printer instead of the resolution specified for the IOCA image.
Bytes 9–10 $Y_{io}$ image points per unit base
These bytes specify the $Y_{io}$ image points per unit base in the image presentation space.
Exception ID X'0206..05' exists if an invalid or unsupported image-points-per-unit-base value
is specified.
Bytes 11–12 $X_{io}$ extent of image presentation space
These bytes specify the $X_{io}$ dimension of the image presentation space in image points.
Exception ID X'0207..05' exists if an invalid extent value is specified.
Bytes 13–14 $Y_{io}$ extent of image presentation space
These bytes specify the $Y_{io}$ dimension of the image presentation space in image points.
Exception ID X'0207..05' exists if an invalid extent value is specified.
Note: Some printers encounter storage limitations when scaling image data for a very large
image presentation space or image object area; for example, larger than 136 inches by
136 inches. Refer to your printer documentation for specific information. [IPDS-7-091]
Bytes 15 to
end of IDD
IOCA self-defining fields
This field contains zero or more IOCA self-defining fields that specify additional descriptive
information about the image. IOCA self-defining fields that are not described here have no
presentation semantics and are ignored by the printer. For more information about IOCA self-
defining fields refer to the Image Object Content Architecture Reference.
There are two ways to specify a color value for bilevel IOCA images (either with X'F4' or with
X'F6'); however, only one of these IOCA self-defining fields will be used. When multiple X'F4'
and X'F6' IOCA self-defining fields are specified, the last supported one specified will be used
and all others will be ignored.
Set Extended Bilevel Image Color IOCA self-defining field (X'F4')
This IOCA self-defining field applies only to bilevel images, and is ignored for all other
images. Printers that do not support extended bilevel image color also ignore this IOCA self-
defining field; property pair X'4401' in the STM IO-Image command-set vector specifies
whether or not the printer supports extended bilevel image color.
If an invalid value is encountered in any field within the IOCA self-defining field, either
exception ID X'0500..03' or exception ID X'05F4..10' exists. Refer to the Image Object
Content Architecture Reference for detailed information about the X'F4' IOCA self-defining
field.
Set Bilevel Image Color IOCA self-defining field (X'F6')
This IOCA self-defining field applies only to bilevel images, and is ignored for all other
images. Printers that do not support bilevel image color also ignore this IOCA self-defining
field, and print the image in the device-default color. Presence of the X'40nn' property pair in
the IO-Image command-set vector of an STM reply with any defined nn bit set to B'1' [IPDS-7-092]


indicates that the printer supports the Set Bilevel Image Color (X'F6') IOCA self-defining
field.
If an invalid or unsupported value is encountered in the length, applicability area, or named-
color fields, the entire IOCA self-defining field is ignored and the image is printed in the
device-default color. If multiple Set Bilevel Image Color SDFs with the same area value are
encountered, the last one encountered is used and the others are ignored. [IPDS-7-093]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | IOCA SDF ID | X'F6' | Set Bilevel Image Color SDF ID | X'F6' [IPDS-7-094]|
| 1 | UBIN | Length | X'04' | Length of the parameters that follow | X'04' [IPDS-7-095]|
| 2 | CODE | Area | X'00' | Applicability area:<br>X'00' = Foreground IDEs | X'00' [IPDS-7-096]|
| 3 | | | X'00' | Reserved | X'00' [IPDS-7-097]|
| 4–5 | CODE | Named color | | Named-color value for each of the image data elements in the applicability area. The following values are defined, all other values are reserved:<br>X'0000' Printer default<br>X'0001' Blue<br>X'0002' Red<br>X'0003' Pink/magenta<br>X'0004' Green<br>X'0005' Turquoise/cyan<br>X'0006' Yellow<br>X'0007' White, see note<br>X'0008' Black<br>X'0009' Dark blue<br>X'000A' Orange<br>X'000B' Purple<br>X'000C' Dark green<br>X'000D' Dark turquoise<br>X'000E' Mustard<br>X'000F' Gray<br>X'0010' Brown<br>X'FF00' Printer default<br>X'FF01' Blue<br>X'FF02' Red<br>X'FF03' Pink/magenta<br>X'FF04' Green<br>X'FF05' Turquoise/cyan<br>X'FF06' Yellow<br>X'FF07' Printer default<br>X'FF08' Color of medium<br>X'FFFF' Printer default | X'FF07' [IPDS-7-098]|
Note: The color rendered on presentation devices that do not support white is device-
dependent. For example, some printers simulate with color of medium that results in
white when white media is used. [IPDS-7-099]


Write Image 2
```
Length X'D64E' Flag CID Data
```
The length of the WI2 command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Image 2 (WI2) command transmits IOCA image data and the parameters that define the image data
to the printer. Zero or more WI2 commands follow the WIC2 command. The Write Image 2 command carries
IOCA data from one of the supported IOCA function sets (FS10, FS11, FS14,
FS40, FS42, FS45, or FS48 );
refer to the Image Object Content Architecture Reference for a description of these function sets.
One or more consecutive WI2 commands carry IOCA data within a sequence of IOCA self-defining fields. The
IOCA self-defining fields are: [IPDS-7-100]

Table 44. IOCA Self-Defining Fields [IPDS-7-101]

| CODE | Name | Function Set |
| :--- | :--- | :--- |
| X'70' | Begin Segment | All [IPDS-7-102]|
| X'71' | End Segment | All [IPDS-7-103]|
| X'8C' | Begin Tile Parameter | FS40, FS42, FS45, FS48 [IPDS-7-104]|
| X'8D' | End Tile Parameter | FS40, FS42, FS45, FS48 [IPDS-7-105]|
| X'8E' | Begin Transparency Mask Parameter | FS14, FS45, FS48 [IPDS-7-106]|
| X'8F' | End Transparency Mask Parameter | FS14, FS45, FS48 [IPDS-7-107]|
| X'91' | Begin Image Content | All [IPDS-7-108]|
| X'93' | End Image Content | All [IPDS-7-109]|
| X'94' | Image Size Parameter | FS10, FS11, FS14, FS45, FS48 [IPDS-7-110]|
| X'95' | Image Encoding Parameter | All [IPDS-7-111]|
| X'96' | Image Data Element Size Parameter | All [IPDS-7-112]|
| X'97' | Image Look Up Table ID Parameter | FS10, FS11 [IPDS-7-113]|
| X'98' | Band Image Parameter | FS11, FS14, FS42, FS45, FS48 [IPDS-7-114]|
| X'9B' | Image Data Element Structure Parameter | FS11, FS14, FS40, FS42, FS45, FS48 [IPDS-7-115]|
| X'9F' | External Algorithm Specification Parameter | FS11 [IPDS-7-116]|
| X'B5' | Tile Position Parameter | FS40, FS42, FS45, FS48 [IPDS-7-117]|
| X'B6' | Tile Size Parameter | FS40, FS42, FS45, FS48 [IPDS-7-118]|
| X'B7' | Tile Set Color Parameter | FS42, FS45, FS48 [IPDS-7-119]|
| X'FE92' | Image Data (one or more) | All [IPDS-7-120]|
| X'FE9C' | Band Image Data (one or more) | FS11, FS14, FS42, FS45, FS48 [IPDS-7-121]|
| X'FEB3' | nColor Names | All [IPDS-7-122]|
| X'FEB8' | Include Tile Parameter | FS45, FS48 [IPDS-7-123]|
| X'FEBB' | Tile TOC Parameter | FS40, FS42, FS45, FS48 [IPDS-7-124]|
| X'FECE' | Image Subsampling Parameter | FS11, FS14 [IPDS-7-125]|
Note: The following additional IOCA code values can exist in the WIC2-IDD:
• X'F4' – Set Extended Bilevel Image Color [IPDS-7-126]
• X'F6' – Set Bilevel Image Color [IPDS-7-127]
• X'F7' – IOCA Function Set identification (allowed in the MO:DCA IDD; ignored by IPDS receivers) [IPDS-7-128]
Only one IOCA segment is allowed in each WIC2... END command stream. An IOCA segment can span two
or more consecutive WI2 commands. There is no restriction on how much or how little data is sent to the
printer in a single WI2 command, except for the length limit of the command.
All image segments are executed in immediate mode. That is, segments are processed as they are received
by the printer and are not retained or stored as named segments.
The Look Up Table ID supported by IPDS printer implementations is X'00', the default Look Up Table ID. This
ID specifies a Look Up Table with the following characteristics:
• If the number of bits per image point of the Image Data Element size parameter is X'01', this Look-Up Table [IPDS-7-129]
specifies a bilevel image with B'1' representing significant bits, image points representing toned pels in the
printer, and B'0' representing insignificant bits, image points representing untoned pels in the printer.
• If the number of bits per image point of the Image Data Element size parameter is greater than X'01', this [IPDS-7-130]
Look-Up Table specifies a grayscale image. Each Image Data Element is a positive binary number that
represents a grayscale value for an image point. A value of X'00' indicates maximum density, for example,
black. Greater values represent increasingly lighter shades. Exception ID X'0596..11' exists if an Image Data
Element Size other than X'01' bit per image point is specified for an IBM MMR, G4 MMR, or RL4 compressed
image.
The Image Encoding Parameter IOCA self-defining field supports an optional field describing bit ordering.
Support for this optional IOCA parameter is indicated by property pair X'5101' in the IO-Image command-set
vector of an STM reply.
Some IPDS printers support IOCA tile resources with IOCA images; support for these resource objects is
indicated by an object-type OID value in the Object-Container Type Support self-defining field in an XOH-OPC
reply. To invoke one of these resource objects, the resource must first be activated using either an AR
command or a download sequence (WOCC, WOC,..., WOC, End) and secondly a DORE or DORE2
command must contain an entry that maps the resource object's HAID to an internal resource ID specified
within the IOCA image. IOCA defines the internal resource ID as a 4-byte local identifier value specified within
an Include Tile Parameter; the DORE or DORE2
entry must also specify this 4-byte local identifier value as the
internal resource ID value.
Unless overridden by a Color Fidelity (X'75') triplet in a PFC command, printers that support IOCA image color
will simulate an unsupported color value with a supported color value. This simulation capability is in addition to
the optional simulation of Standard OCA color values in the Set Bilevel Image Color IOCA self-defining field (or
the Set Extended Bilevel Image Color IOCA self-defining field) as reported in the IO-Image command-set
vector in an STM reply. [IPDS-7-131]


Unsupported IOCA function in an IPDS Environment
Not all IOCA printers support the full range of IOCA function; these printers will return an appropriate NACK if
unsupported IOCA self-defining fields or values are included in an image. For example, if an IOCA FS11,
FS14,
FS40, FS42, FS45, or FS48 image is sent to an IPDS printer that only supports IOCA FS10, the printer
will encounter a data stream error and will return one or more exception conditions such as X'0500..01' (invalid
or unsupported IOCA self-defining field code) or X'0595..10' (unsupported compression algorithm).
An X in the following table indicates that the exception ID should be supported by an implementation that
supports the given function set: [IPDS-7-132]

Table 45. Exception IDs for IOCA Function Sets [IPDS-7-133]

| Exception ID | FS10 | FS11 | FS14 | FS40 | FS42 | FS45 | FS48 |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| X'0500..01' | X | X | X | X | X | X | X [IPDS-7-134]|
| X'0500..03' | X | X | X | X | X | X | X [IPDS-7-135]|
| X'0500..04' | X | X | X | X | X | X | X [IPDS-7-136]|
| X'0570..0F' | X | X | X | X | X | X | X [IPDS-7-137]|
| X'0571..0F' | X | X | X | X | X | X | X [IPDS-7-138]|
| X'058C..0F' | | | | X | X | X | X [IPDS-7-139]|
| X'058D..0F' | | | | X | X | X | X [IPDS-7-140]|
| X'058E..0F' | | | | | | X | X [IPDS-7-141]|
| X'058F..0F' | | | | | | X | X [IPDS-7-142]|
| X'0591..0F' | X | X | X | X | X | X | X [IPDS-7-143]|
| X'0592..01' | | X | X | X | X | X [IPDS-7-144]| |
| X'0592..0F' | X | X | X | X | X | X | X [IPDS-7-145]|
| X'0593..0F' | X | X | X | X | X | X | X [IPDS-7-146]|
| X'0594..01' | | X | X | X | X | X [IPDS-7-147]| |
| X'0594..0F' | | X | X | X | X | X [IPDS-7-148]| |
| X'0594..10' | | X | X | X | X | X [IPDS-7-149]| |
| X'0594..11' | | X | X | X | X | X [IPDS-7-150]| |
| X'0595..0F' | X | X | X | X | X | X | X [IPDS-7-151]|
| X'0595..10' | X | X | X | X | X | X | X [IPDS-7-152]|
| X'0595..11' | X | X | X | X | X | X | X [IPDS-7-153]|
| X'0596..0F' | X | X | X | X | X | X | X [IPDS-7-154]|
| X'0596..10' | X | X | X | X | X | X | X [IPDS-7-155]|
| X'0596..11' | X | X | X | X | X | X | X [IPDS-7-156]|
| X'0597..0F' | X | X [IPDS-7-157]| | | | | |
| X'0597..10' | X | X [IPDS-7-158]| | | | | |
| X'0598..01' | | X | X [IPDS-7-159]| | | | |
| X'0598..0F' | | X | X | | | X | X [IPDS-7-160]|
| X'0598..10' | | X | X | | | X | X [IPDS-7-161]|
| X'0598..14' | | X | X | | | X | X [IPDS-7-162]|
| X'0598..15' | | X | X | | | X | X [IPDS-7-163]|
| X'059B..0F' | | X | X | X | X | X | X [IPDS-7-164]|
| X'059B..10' | | X | X | X | X | X | X [IPDS-7-165]|
| X'059B..18' | | X | X | X | X | X | X [IPDS-7-166]|
| X'059C..01' | | X | X | | | X | X [IPDS-7-167]|
| X'059C..0F' | | X | X | | | X | X [IPDS-7-168]|
| X'059C..17' | | X | X | | | X | X [IPDS-7-169]|
| X'059F..01' | | X [IPDS-7-170]| | | | | |
| X'059F..0F' | | X [IPDS-7-171]| | | | | |
| X'059F..10' | | X [IPDS-7-172]| | | | | |
| X'059F..11' | | X [IPDS-7-173]| | | | | |
| X'05A9..02' | X | X | X | X | X | X | X [IPDS-7-174]|
| X'05B3..0F' [IPDS-7-175]| | | | | | | |
| X'05B3..10' [IPDS-7-176]| | | | | | | |
| X'05B3..11' [IPDS-7-177]| | | | | | | |
| X'05B5..0F' | | | | X | X | X | X [IPDS-7-178]|
| X'05B5..10' | | | | X | X | X | X [IPDS-7-179]|
| X'05B5..11' | | | | X | X | X | X [IPDS-7-180]|
| X'05B6..0F' | | | | X | X | X | X [IPDS-7-181]|
| X'05B6..10' | | | | X | X | X | X [IPDS-7-182]|
| X'05B6..11' | | | | X | X | X | X [IPDS-7-183]|
| X'05B7..0F' | | | | | X | X | X [IPDS-7-184]|
| X'05B7..10' | | | | | X | X | X [IPDS-7-185]|
| X'05B7..11' | | | | | X | X | X [IPDS-7-186]|
| X'05B8..0F' | | | | | | X | X [IPDS-7-187]|
| X'05B8..11' | | | | | | X | X [IPDS-7-188]|
| X'05BB..0F' | | | | X | X | X | X [IPDS-7-189]|
| X'05BB..10' | | | | X | X | X | X [IPDS-7-190]|
| X'05BB..11' | | | | X | X | X | X [IPDS-7-191]|
| X'05CE..01' | | X | X [IPDS-7-192]| | | | |
| X'05CE..0F' | | X | X [IPDS-7-193]| | | | |
| X'05CE..10' | | X | X [IPDS-7-194]| | | | |
| X'05F4..10' [IPDS-7-195]| | | | | | | |


