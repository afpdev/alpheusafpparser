# Chapter 6. IM-Image Command Set
The IM-Image command set contains the commands for presenting image raster data on a logical page, a
page segment, or an overlay. This command set contains the following commands:
Table 39. IM-Image Commands [IPDS-6-001]

| Command | Code | Description | In IM1 Subset? |
| :--- | :--- | :--- | :--- |
| WIC | X'D63D' | “Write Image Control” | Yes [IPDS-6-002]|
| WI | X'D64D' | “Write Image” | Yes [IPDS-6-003]|
Both the IM-Image and the IO-Image command sets contain commands that enter the printer into the
respective image state and allow the subsequent transmission of image data to the printer. The IO-Image
command-set commands provide a variety of functional additions over the IM-Image command-set commands.
A printer can implement both the IO-Image and the IM-Image command sets. The following table indicates the
major differences.
Table 40. IM-Image and IO-Image Comparison [IPDS-6-004]

| Functions or controls | IM Image | IO Image |
| :--- | :---: | :---: |
| Replicate and trim input to fill output | X | O [IPDS-6-005]|
| Bilevel image | X | X [IPDS-6-006]|
| Bilevel image with a color specification | X | O [IPDS-6-007]|
| Unpadded recording algorithm | X | O [IPDS-6-008]|
| Grayscale image | | X [IPDS-6-009]|
| Compression | | X [IPDS-6-010]|
| Resolution-independent data presentation | | X [IPDS-6-011]|
| Resolution correction to device resolution | | X [IPDS-6-012]|
| Scaling | | X [IPDS-6-013]|
| Position and trim | | X [IPDS-6-014]|
| Center and trim | | X [IPDS-6-015]|
| Scale to fill | | O [IPDS-6-016]|
| Full-color image | | O [IPDS-6-017]|
| Color Management Resources (CMRs); see note | | O [IPDS-6-018]|
| Image Banding | | O [IPDS-6-019]|
| Subsampling | | O [IPDS-6-020]|
| Relative resolution for a tile | | O [IPDS-6-021]|
| Tiling | | O [IPDS-6-022]|
| Transparency masks | | O [IPDS-6-023]|
| Bit allocation | | O [IPDS-6-024]|
| Area coloring | | O [IPDS-6-025]|
| IOCA tile resources | | O [IPDS-6-026]|
| Multiple image contents in an IOCA image segment | | O [IPDS-6-027]|

**Key:** X: supported, O: optional function (supported by some, but not all printers)
Note: Color Management Resources (CMRs) are associated with all print data using the CMR-usage hierarchy.
CMRs can be directly tied to an IO Image by specifying the Invoke CMR (X'92') triplet in the WIC2
command. CMRs cannot be directly tied to an IM Image and the rendering intent used for IM Image is
media-relative colorimetric; but IM-Image data can be processed with CMRs from another level of the
hierarchy.


Write Image Control
The Write Image Control (WIC) command causes the printer to enter the IM-Image state. The image command
sequence is directed to an IM Image object area on the current page, page segment, or overlay.
The parameters of the WIC command define the input and output size of the image array and the necessary
information for interpreting the input data. In addition, WIC parameters can specify that the input image is to be
replicated, trimmed, or magnified by 2 before being mapped to the IM-Image object area. The WIC command
is followed by one or more Write Image (WI) commands. IM-Image processing ends when the printer receives
the End command in the IM-Image state following receipt of at least one WI command.
Unlike IO Image, there is no resolution specified within an IM Image. The resolution used by the printer for all
IM Images is indicated in the XOH-OPC IM-Image and Coded-Font Resolution self-defining field.
```
Length X'D63D' Flag CID Data
```
The length of the WIC command can be:
Without CID X'001D' or X'001F'
With CID X'001F' or X'0021'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data field bytes have the following meaning for this command: [IPDS-6-028]

| Offset | Type | Name | Range | Meaning | IM1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | PPSL output | X'0001' – X'7FFF' | Pels per scan line in the output image | X'0001' – X'0FA0' [IPDS-6-029]|
| 2–3 | UBIN | NSL output | X'0001' – X'7FFF' | Number of scan lines in the output image | X'0001' – X'0FA0' [IPDS-6-030]|
| 4–5 | UBIN | PPSL input | X'0001' – X'7FFF' | Pels per scan line in the input image | X'0001' – X'0FA0' [IPDS-6-031]|
| 6–7 | UBIN | NSL input | X'0001' – X'7FFF' | Number of scan lines in the input image | X'0001' – X'0FA0' [IPDS-6-032]|
| 8 | CODE | Compress | X'00' | Uncompressed input image | X'00' [IPDS-6-033]|
| 9 | CODE | Bits per pel | X'00' | One bit per pel in the input image format | X'00' [IPDS-6-034]|
| 10 | UBIN | Pel mag. | X'01', X'02' | Pel magnification factor | X'01', X'02' [IPDS-6-035]|
| 11 | UBIN | Scan-line mag. | X'01', X'02' | Scan-line magnification factor; must equal the value in byte 10. | X'01', X'02' [IPDS-6-036]|
| 12–13 | CODE | SL direction | X'0000' X'2D00' X'5A00' X'8700' | Scan-line direction:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-6-037]|
| 14–15 | CODE | SLS direction | X'0000' X'2D00' X'5A00' X'8700' | Scan-line sequence direction:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'2D00' [IPDS-6-038]|
| 16 | CODE | RCS | X'00' X'20' X'40' X'60' X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>$X_{p}, Y_{p}$ | X'00' X'20' X'40' X'60' X'A0' [IPDS-6-039]|
| 17–19 | SBIN | X offset | X'FF8000'– X'007FFF' | $X_{p}$, $I$, or $I$-offset coordinate of the output image origin | X'000000'– X'007FFF' [IPDS-6-040]|
| 20 | | | X'00' | Reserved | X'00' [IPDS-6-041]|
| 21–23 | SBIN | Y offset | X'FF8000'– X'007FFF' | $Y_{p}$, $B$, or $B$-offset coordinate of the output image origin | X'000000'– X'007FFF' [IPDS-6-042]|
| 24–25 | CODE | Color | X'0000' – X'0010', X'FF00' – X'FF08' | Image color (same as graphics color values) | X'FF07' [IPDS-6-043]|

*Note:* Refer to the notes following this table for subset range and coordinate details.
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. [IPDS-6-044]


Image Size
If the IM-Image output image size is less than the input image size, the input image is truncated to fit the output
image. If the output image size is equal to the input image size, all input pels are presented in a one-to-one
mapping. If the output image size is greater than the input image size, the input image is replicated and then
truncated to fill the output image. Therefore, the output image size might be less than, equal to, or greater than
the input image size. Figure 75 and Figure 76 show some examples of output image
sizes related to input image sizes. The following bytes specify the output image size and the input image size.
Bytes 0–1 Pels per scan line in the output image
These bytes specify the number of pels in each scan line of the target image.
Exception ID X'0242..01' exists if the pels-per-scan-line value is less than X'0001'. Exception
ID X'0243..01' exists if the pels-per-scan-line value is greater than the maximum supported
value.
Bytes 2–3 Number of scan lines in the output image
These bytes specify the height of the target image rectangle, expressed as the number of
scan lines in the image.
Exception ID X'0244..01' exists if the number-of-scan-lines value is less than X'0001'.
Exception ID X'0245..01' exists if the number-of-scan-lines value is greater than the maximum
supported value.
Bytes 4–5 Pels per scan line in the input image
These bytes specify the number of pels in each scan line of the input source image before
magnification.
Exception ID X'0242..01' exists if the pels-per-scan-line value is less than X'0001'. Exception
ID X'0243..01' exists if the pels-per-scan-line value is greater than the maximum supported
value.
Note: Some IPDS printers support less than the IM1 range for this field when image
replication or image magnification is specified. IBM printers that limit the range in these
cases include 3820, 3825, 3827, 3828, 3829, 3831, 3835, and 3900-001. See your
printer documentation for information about a particular IPDS printer.
Bytes 6–7 Number of scan lines in the input image
These bytes specify the height of the source image rectangle, expressed as the number of
scan lines in the image before magnification.
Exception ID X'0244..01' exists if the number-of-scan-lines value is less than X'0001'.
Exception ID X'0245..01' exists if the number-of-scan-lines value is greater than the maximum
supported value.
Note: Some IPDS printers support less than the IM1 range for this field when image
replication or image magnification is specified. IBM printers that limit the range in these
cases include 3820, 3825, 3827, 3828, 3829, 3831, 3835, and 3900-001. See your
printer documentation for information about a particular IPDS printer. [IPDS-6-045]


Figure 75. IM Image Where the Output Size Is Less Than the Input Size
Input Image
Output Image
Legend: = Toned pel in the raster pattern
= Untoned pel in the raster pattern [IPDS-6-046]


Figure 76. IM Image Where the Output Size is Greater Than the Input Size
Input Image
Output Image
Legend: = Toned pel in the raster pattern
= Untoned pel in the raster pattern
Input Image Data Format
The following bytes specify the data format of the input image that the host transmits to the printer in
subsequent Write Image commands.
Byte 8 Uncompressed input image
This byte must be set to X'00', indicating that the source image data is uncompressed.
Exception ID X'0246..01' exists if an invalid value is specified.
Byte 9 One bit per pel in the source image format
This byte must be X'00', indicating that the image pel data is bilevel encoded. Bits with a value
of 1 identify a toned pel; bits with a value of 0 identify an untoned pel.
Exception ID X'0246..01' exists if an invalid value is specified. [IPDS-6-047]


Image Magnification
The input image can be magnified (scaled) by a factor of 2. Images are magnified before mapping to the output
image by:
• Repeating each pel on the scan line [IPDS-6-048]
• Repeating each scan line [IPDS-6-049]
Figure 77 shows an example of image magnification. Both the number of pels on a scan line and the number of
scan lines are repeated. The following bytes specify image magnification.
Note: The values in bytes 10 and 11 must be equal.
Byte 10 Pel-magnification factor
Two values are valid:
• X'01' indicates no magnification of pels. [IPDS-6-050]
• X'02' indicates a magnification factor of 2. Each pel on a scan line is repeated. [IPDS-6-051]
Exception ID X'0247..01' exists if an invalid value is specified.
Byte 11 Scan-line magnification factor
Two values are valid:
• X'01' indicates no magnification of scan lines. [IPDS-6-052]
• X'02' indicates a magnification factor of 2. Each scan line in the image is repeated. [IPDS-6-053]
Exception ID X'0247..01' exists if an invalid value is specified. [IPDS-6-054]


Figure 77. Example of IM-Image Magnification and Replication Where the Output Size Is Greater Than the
Input Size
Input Image
Output Image
Legend: = Toned pel in the raster pattern
= Untoned pel in the raster pattern [IPDS-6-055]


Output Image Orientation
The following bytes define the output orientation of the image in the logical page. Note that, unlike the
corresponding fields in the other object-data types, IM-Image orientation cannot be specified with respect to
the I,B axes. The format of the following two-byte parameters is analogous to that of the Set Text Orientation
control in the Write Text command, however they are independent. The specification of scan line direction has
no effect on the direction of the I or B axes.
Bytes 12–13 Scan-line direction
This is the direction in which pels are added to create an image scan line (the inline direction
equivalent for images). The four valid directions are: $+X_{p}$, $+Y_{p}$, $-X_{p}$, and $-Y_{p}$. The scan-line
direction is specified as a clockwise angle from the $X_{p}$-axis.
Valid scan-line-direction values are:
Inline Direction Value
0° rotation ($+X_{p}$ direction) X'0000'
90° rotation ($+Y_{p}$ direction) X'2D00'
180° rotation ($-X_{p}$ direction) X'5A00'
270° rotation ($-Y_{p}$ direction) X'8700'
Exception ID X'0248..01' exists if an invalid or unsupported scan-line-direction value is
specified.
Bytes 14–15 Scan-line-sequence direction
This parameter shows the direction in which scan lines are added to create an image
rectangle (the baseline direction equivalent for images). The four valid directions are: $+X_{p}$,
$+Y_{p}$, $-X_{p}$, and $-Y_{p}$. The scan-line-sequence direction is specified as a clockwise angle from the
$X_{p}$-axis.
Valid values for the scan-line-sequence direction are:
Baseline Direction Value
0° rotation ($+X_{p}$ direction) X'0000'
90° rotation ($+Y_{p}$ direction) X'2D00'
180° rotation ($-X_{p}$ direction) X'5A00'
270° rotation ($-Y_{p}$ direction) X'8700'
The scan-line-sequence direction must be +90° from the scan-line direction specified in bytes
12 and 13. [IPDS-6-056]
Exception ID X'0249..01' exists if an invalid or unsupported scan-line-sequence-direction
value is specified.


Output Image Location
The IM-Image origin (bytes 17–23) is the coordinate position of the top left corner of the output image on the
logical page. The output image origin may be represented by either an $X_{p}, Y_{p}$ coordinate position or an inline-
baseline ($I, B$) coordinate position, depending on the value specified in byte 16. The $X_{p}, Y_{p}$ position is defined
relative to the origin of the logical page. The $I, B$ position is based on the currently active inline and baseline
definition for text. Refer to “Coordinate Systems” for more information.
Byte 16 Reference coordinate system
The reference coordinate system determines the origin of the output image, using either the
$X_{p}, Y_{p}$ or the $I, B$ coordinate system.
Note: The Output Image Orientation (bytes 12–15) is not relative to this reference coordinate
system.
An inline coordinate value specified as absolute means that the values in bytes 17–19 are at
absolute inline coordinate locations. A baseline coordinate value specified as absolute means
that the values in bytes 21–23 are at absolute baseline coordinate locations.
An inline coordinate value specified as relative means that the value in bytes 17–19 is an
offset from the current inline coordinate location. A baseline coordinate value specified as
relative means that the value in bytes 21–23 is an offset from the current baseline coordinate
location. Therefore, the following applies:
• If byte 16 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-6-057]
Bytes 17–19 specify the text inline coordinate; bytes 21–23 specify the text baseline
coordinate.
• If byte 16 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-6-058]
origin. Bytes 17–19 specify the text inline coordinate; bytes 21–23 are added to the current
text baseline coordinate, as established in the last Write Text (WT) command.
• If byte 16 equals X'40', the relative inline and absolute baseline coordinates determine the [IPDS-6-059]
origin. Bytes 17–19 are added to the current text inline coordinate, as established in the last
WT command; bytes 21–23 specify the text baseline coordinate.
• If byte 16 equals X'60', the relative inline and baseline coordinates determine the origin. [IPDS-6-060]
Bytes 16–19 are added to the current text inline coordinate, as established in the last WT
command; bytes 21–23 are added to the current text baseline coordinate position, as
established in the last WT command.
• If byte 16 equals X'A0', the current logical page $X_{p}$ and $Y_{p}$ coordinates determine the origin. [IPDS-6-061]
When the output image is within a page, WIC bytes 17–19 and 21–23 specify the offset from
the $X_{p}$-coordinate and $Y_{p}$-coordinate origin specified in a previously received LPP command
(or from the printer default coordinates if no LPP command received). When the output
image is within an overlay that is invoked using an LCC command, WIC bytes 17–19 and
21–23 specify the offset from the $X_{m}$-coordinate and $Y_{m}$-coordinate origin. When the output
image is within an overlay that is invoked using an IO command, WIC bytes 17–19 and 21–
23 specify the offset from the $X_{p}$-coordinate and $Y_{p}$-coordinate origin specified in the IO [IPDS-6-062]
command.
Exception ID X'024A..01' exists if an invalid or unsupported value is specified.
Bytes 17–19 $X_{p}$, $I$, or $I$-offset coordinate of the output image origin
These bytes specify the $X_{p}$ coordinate, inline coordinate, or inline-coordinate offset of the
output image origin (first pel in the output image). The value in these bytes is either a location
on the $X_{p}$-axis, a location on the inline axis, or a location on the inline axis specified as an
offset from the current inline text coordinate ($I_{c}$). Byte 16 specifies which of the three types of
measurement is used.
Exception ID X'024A..01' exists if an invalid or unsupported value is specified. [IPDS-6-063]


Byte 20 Reserved
Bytes 21–23 $Y_{p}$, $B$, or $B$-offset coordinate of the output image origin
These bytes specify the $Y_{p}$ coordinate, baseline coordinate, or baseline-coordinate offset of
the output image origin (first pel in the output image.) The value in these bytes is either a
location on the $Y_{p}$-axis, a location on the baseline axis, or a location on the baseline axis
specified as an offset from the current baseline text coordinate ($B_{c}$). Byte 16 specifies which of
the three types of measurement is used.
Exception ID X'024A..01' exists if an invalid or unsupported value is specified.
Note: The current text presentation coordinate ($I_{c}, B_{c}$) is not changed by the printing of this
object.


Image Color
Bytes 24–25 Color ID (optional)
These bytes specify a two-byte ID of a single presentation color for the entire image. This field
is optional, and if bytes 24–25 are omitted, the printer default color (X'FF07') is used. Valid
values for specifying color are X'0000' through X'0010' and X'FF00' through X'FF08'. However,
some of these values are not supported by IPDS printers. Exception ID X'0253..01' exists if
either an invalid value or a valid but unsupported value is specified. Some printers simulate an
unsupported color without reporting an exception condition; this is indicated by a X'40nn'
property pair in the IM-Image command-set vector of an STM reply.
Standard OCA Color-Value Table [IPDS-6-064]

Table 41. Standard OCA Color-Value Table [IPDS-6-065]

| Value | Color | Red (R) | Green (G) | Blue (B) |
| :--- | :--- | :---: | :---: | :---: |
| X'0000' or X'FF00' | Current default; see note 1 [IPDS-6-066]| | | |
| X'0001' or X'FF01' | Blue | 0 | 0 | 255 [IPDS-6-067]|
| X'0002' or X'FF02' | Red | 255 | 0 | 0 [IPDS-6-068]|
| X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 [IPDS-6-069]|
| X'0004' or X'FF04' | Green | 0 | 255 | 0 [IPDS-6-070]|
| X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 [IPDS-6-071]|
| X'0006' or X'FF06' | Yellow | 255 | 255 | 0 [IPDS-6-072]|
| X'0007' | White, see note 2 | 255 | 255 | 255 [IPDS-6-073]|
| X'0008' | Black, see note 3 | 0 | 0 | 0 [IPDS-6-074]|
| X'0009' | Dark blue | 0 | 0 | 170 [IPDS-6-075]|
| X'000A' | Orange | 255 | 128 | 0 [IPDS-6-076]|
| X'000B' | Purple | 170 | 0 | 170 [IPDS-6-077]|
| X'000C' | Dark green | 0 | 146 | 0 [IPDS-6-078]|
| X'000D' | Dark turquoise | 0 | 146 | 170 [IPDS-6-079]|
| X'000E' | Mustard | 196 | 160 | 32 [IPDS-6-080]|
| X'000F' | Gray | 131 | 131 | 131 [IPDS-6-081]|
| X'0010' | Brown | 144 | 48 | 0 [IPDS-6-082]|
| X'FF07' | Printer default; see note 4 [IPDS-6-083]| | | |
| X'FF08' | Color of medium; also known as reset color [IPDS-6-084]| | | |
Note: The table specifies the RGB values for each named color; the actual printed color is device dependent.
Notes:
1. The definition of current default is dependent on the data type. [IPDS-6-085]
• For graphics data, the current default is the drawing order default defined in the GDD self-defining field of [IPDS-6-086]
the WGC command.
• For text, IM-Image, bilevel IO-Image, and bar code data, the current default is the printer default. [IPDS-6-087]
2. The color rendered on presentation devices that do not support white is device dependent. For example, [IPDS-6-088]
some printers simulate with color of medium, which results in white when white media is used.
3. It is recommended that OCA Black (X'0008') be rendered as C=M=Y= X'00' and K = X'FF'. [IPDS-6-089]
4. The printer default color specified by X'FF07' is also known in GOCA as neutral white for compatibility with [IPDS-6-090]
display devices.
5. The value X'FFFF' is not defined in the Standard OCA Color-Value Table, but is used by some objects as a [IPDS-6-091]
default indicator as follows:
• For PTOCA text data, X'FFFF' can be specified in the Set Text Color (STC) control sequence to indicate [IPDS-6-092]
that the PTOCA default hierarchy is used to generate the color value. Note that X'FFFF' is not supported
in the Set Extended Text Color (SEC) control sequence.
• For IM-Image data in MO:DCA environments, X'FFFF' can be specified to indicate use of a presentation [IPDS-6-093]
process default color value. The value X'FFFF' is not valid for IM-Image data in IPDS environments.
• For bilevel IOCA image data (FS10), X'FFFF' can be specified to indicate use of a printer default color. [IPDS-6-094]
• For BCOCA bar code data, X'FFFF' can be specified to indicate use of a printer default color. [IPDS-6-095]
Write Image
```
Length X'D64D' Flag CID Data
```
The length of the WI command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Image (WI) command transmits IM-Image data to the printer. One or more WI commands follow the
WIC command and are terminated with an End command. There is no restriction (other than the 32,767-byte
length limit of the command) on how much or how little data is contained in a single WI command.
Note: Only Anystate commands are valid between concatenated WI commands; refer to Figure 45 for a list of Anystate commands.
The WI commands transmit the image-bit string as a sequence of scan lines. Therefore, the total number of
image bits sent is the product of the following:
• Scan-line pel length (bytes 4 and 5 of the WIC command) [IPDS-6-096]
• Scan-line count (bytes 6 and 7 of the WIC command). [IPDS-6-097]
Exception ID X'026A..01' exists if the rounded-up quotient of this product divided by 8 is greater than the
rounded-up quotient of the number of bits received divided by 8. Exception ID X'026B..01' exists if the
rounded-up quotient of this product divided by 8 is less than the rounded-up quotient of the number of bits
received divided by 8. Exception ID X'0264..01' or X'02AF..01' exists if there is insufficient storage to contain
the image; the preferred exception ID is X'02AF..01'. [IPDS-6-098]




