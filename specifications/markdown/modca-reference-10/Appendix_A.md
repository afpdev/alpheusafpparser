# Appendix A. Color Resources
This appendix describes color resources that may be used in MO:DCA environments. For a discussion of font
resources, see Font Object Content Architecture Reference.
Standard OCA Color Value Table
The following table defines the valid color values used to specify named colors in PTOCA, IOCA, GOCA,
BCOCA, and IM Image objects. The table also specifies the RGB values that can be used for each named
color, assuming that each component is specified with 8 bits and that the component intensity range 0 to 1 is
mapped to the binary value range 0 to 255. Although all values in this table are syntactically valid in these
objects, some objects support only a subset of the colors. For a definition of the supported colors, see the
Object Content Architecture references for the individual objects. Note that this table defines the complete set
of colors supported by the GOCA Set Extended Color drawing order. The Color Specification (X'4E') triplet also
supports these colors for the Standard OCA color space; see “Color Specification Triplet X'4E'”.
Table 43. Color Values [MODCA-A-001]
| Value | Color | Red (R) | Green (G) | Blue (B) [MODCA-A-002]|
| --- | --- | --- | --- | --- [MODCA-A-003]|
| X'0000' or X'FF00' | Presentation-process default; see Note 1 [MODCA-A-004]| | | |
| X'0001' or X'FF01' | Blue | 0 | 0 | 255 [MODCA-A-005]|
| X'0002' or X'FF02' | Red | 255 | 0 | 0 [MODCA-A-006]|
| X'0003' or X'FF03' | Pink/Magenta | 255 | 0 | 255 [MODCA-A-007]|
| X'0004' or X'FF04' | Green | 0 | 255 | 0 [MODCA-A-008]|
| X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 [MODCA-A-009]|
| X'0006' or X'FF06' | Yellow | 255 | 255 | 0 [MODCA-A-010]|
| X'0007' | White; see Note 2 | 255 | 255 | 255 [MODCA-A-011]|
| X'0008' | Black | 0 | 0 | 0 [MODCA-A-012]|
| X'0009' | Dark blue | 0 | 0 | 170 [MODCA-A-013]|
| X'000A' | Orange | 255 | 128 | 0 [MODCA-A-014]|
| X'000B' | Purple | 170 | 0 | 170 [MODCA-A-015]|
| X'000C' | Dark green | 0 | 146 | 0 [MODCA-A-016]|
| X'000D' | Dark turquoise | 0 | 146 | 170 [MODCA-A-017]|
| X'000E' | Mustard | 196 | 160 | 32 [MODCA-A-018]|
| X'000F' | Gray | 131 | 131 | 131 [MODCA-A-019]|
| X'0010' | Brown | 144 | 48 | 0 [MODCA-A-020]|
| X'FF07' | Presentation-process default; see Note 3 | — | — | — [MODCA-A-021]|
| X'FF08' | Color of medium | — | — | — [MODCA-A-022]|
| All others | Reserved | — | — | — [MODCA-A-023]|
Notes:
1. The presentation-process default specified by X'0000' and X'FF00' is resolved based on data type as follows: [MODCA-A-024]
• For PTOCA text data, it is the presentation device default. [MODCA-A-025]
• For bilevel IOCA Image data (FS10), it is the presentation device default. [MODCA-A-026]
• For IM Image data, it is the presentation device default. [MODCA-A-027]
• For GOCA graphics data, it is the drawing order default defined in the Graphics Data Descriptor (GDD) structured [MODCA-A-028]
field.
• For BCOCA bar code data, it is the presentation device default. [MODCA-A-029]
2. The color rendered on presentation devices that do not support white is presentation-system dependent. For [MODCA-A-030]
example, some printers simulate with color of medium, which results in white if white media is used.
3. The presentation-process default specified by X'FF07' is resolved as the presentation device default. This color [MODCA-A-031]
value is also known in GOCA as neutral white for compatibility with display devices.
4. The value X'FFFF' is not defined in the Standard OCA Color Value T able but is used by some objects as a default [MODCA-A-032]
indicator as follows:
• For PTOCA text data, X'FFFF' may be specified in the Set T ext Color (STC) control sequence to indicate that the [MODCA-A-033]
PTOCA default hierarchy is used to generate the color value. Note that X'FFFF' is not supported in the Set
Extended T ext Color (SEC) control sequence.
• For IM image data in MO:DCA environments, X'FFFF' may be specified to indicate use of a presentation process [MODCA-A-034]
default color value. The value X'FFFF' is not valid for IM image in IPDS environments.
• For bilevel IOCA image data (FS10), X'FFFF' may be specified to indicate use of a presentation process default [MODCA-A-035]
color.
• For BCOCA data, X'FFFF' may be specified to indicate use of a presentation device default color. [MODCA-A-036]
5. While the RGB values in the table can be used to render the OCA named colors, many implementations are and [MODCA-A-037]
have been presentation-system dependent. Nevertheless, it is recommended that OCA Black (X'0008') be rendered
as C = M = Y = X'00', and K = X'FF'.
Color Resources


Converting Colors to Grayscale in MO:DCA Environments
Documents containing color specifications may be sent to bilevel devices such as black and white printers. If
the presentation process decides, based on user fidelity requirements or on defaults, that the document is to
be presented using grayscale substitution, the specified colors in the document should be simulated in a
consistent and predictable manner by varying the intensity of the available color. On black and white printers,
this means that colors are simulated with a grayscale where the intensity level of the output gray is determined
by the lightness (L) of the color being simulated. A lightness of 0 is defined to be black and a lightness of 100 is
defined to be white.
The following equations specify how the lightness (L) is derived from a color specified in one of the MO:DCA-
supported color spaces.
CIELab Color Space
L = L
assuming
0 <= L <= 100 [MODCA-A-038]
RGB Color Space
First the CIE luminance (Y) is generated:
Y = 0.212(R
2.2) + 0.701(G 2.2) + 0.087(B 2.2)
assuming
0 <= R,G,B <= 1 [MODCA-A-039]
Note: In this equation, R, G, B are the gamma-corrected (nonlinear) components of the source color.
The lightness (L) is calculated from the CIE luminance (Y) using the following equation:
L = 116(Y
1/3) - 16 for Y > 0.008856
L = 903.3Y for Y <= 0.008856
assuming
0 <= Y <= 1 [MODCA-A-040]
CMYK Color Space
First the CIE luminance (Y) is generated:
Y = 1 - min(1, 0.212C + 0.701M + 0.087Y + K)
assuming
0 <= C,M,Y,K <= 1 [MODCA-A-041]
where the function min(a,b) selects the smaller of (a,b).
The lightness (L) is calculated from the CIE luminance (Y) using the following equation:
L = 116(Y
1/3) - 16 for Y > 0.008856
L = 903.3Y for Y<= 0.008856
assuming
0 <= Y <= 1 [MODCA-A-042]
Color Resources


Standard OCA Color Space (Named Colors)
Named colors are first converted to RGB values using the mapping defined in the Standard OCA Color Value
T able; see“Standard OCA Color Value T able”. Once the named color is converted to an RGB
value, the equations for calculating lightness (L) from RGB are used.
Note: The Standard OCA color space also supports a value for color of medium. This color is not simulated
with a grayscale intensity.
Highlight Color Space
In the absence of a color mapping, each highlight color is simulated with black, and % coverage is applied.
The Color Mapping Table Resource
The Color Mapping T able (CMT) is used to map color values specified in a source color space to color values
specified in a target color space. This allows colors specified in one or moresource documents to be mapped
to colors more suitable to the selected presentation device without requiring changes to the applications that
generate the documents.
Color Mapping Table in MO:DCA Environments
The Color Mapping T able (CMT) is invoked when the print request to present a MO:DCA print file is issued.
The CMT specified in the print request may be located in the resource group associated with the print file, or it
may be located in a resource library, or it may be the presentation process default CMT . The scope of the CMT
in a MO:DCA presentation environment is the print file for which it is invoked. The invoked CMT remains active
until another CMT is invoked. If no CMT is active, or if the reset table is active, no color mapping takes place.
The Color Mapping T able is a non-presentation resource object that is carried in a MO:DCA object container
with the following structure:
Color Mapping Table Container
Figure 88. Color Mapping Table Container
Begin Object Container (BOC, D3A892)
[ (OCD, D3EE92) Object Container Data (S) ]
End Object Container (EOC, D3A992)
The table may be split on any byte boundary across any number of OCD structured fields. The mandatory
Object Classification (X'10') triplet on the BOC structured field specifies the following parameter values:
ObjClass X'30' (set-up file)
StrucFlgs X'DC00' (data is carried within a container, does not include an OEG, and is carried in OCD
structured fields)
RegObjId X'06072B120004010114'
Color Mapping Table in IPDS Environments
When a Color Mapping T able is sent to an IPDS printer in a non-presentation object container, it applies to all
selected presentation data that is printed from that time on until the CMT is replaced by another CMT or by the
reset table. The CMT is not applied to data in a resource object, such as an overlay or page segment, until that
Color Resources


resource object is included on a logical page. This means that if the CMT changes between includes of an
overlay, the overlay can be printed in different colors. However, this is not true for pages that are being
processed and saved as resources in the presentation device. For that case, the CMT that is active when the
page is saved is used to map colors in the page, not the CMT that is active when the saved page is included.
Note that if a color specified in the data stream is mapped with a CMT , the determination of color support is
based on the CMT output color value, not on the CMT input (data stream) color value. Therefore, if an
exception is detected because a color is not supported, the exception applies to the CMT output value, not to
the data stream value.
Color Mapping Table Definition
The table definition consists of a base part, followed by zero or more repeating groups. The base part specifies
the table to be a color mapping table or a reset color mapping table. If a reset color mapping table is specified,
the repeating groups are optional and no color mappings occur when this table is invoked. If a color mapping
table is specified, the base part is followed by two or more repeating groups. Each repeating group specifies a
color space and a set of color values. Additionally, each repeating group specifies whether the color values are
to be treated as sources, in which case it is a source repeating group, or as targets, in which case it is a target
repeating group. Source repeating groups also specify the type of source data the color values should be
associated with. The color mapping table must contain at least one source repeating group and one target
repeating group. One or moresource repeating groups can be associated with a single target repeating group
by matching the repeating group IDs. While there may be multiple source repeating groups with the same
repeating group ID, there cannot be more than one target repeating group with the same ID, and there must be
a target repeating group for every source repeating group. If there is more than one target repeating group with
the same ID, the first group is used and the rest are ignored. For example, if the table contains two source
repeating groups, each with ID X'01', and if it contains a target repeating group with ID X'01', then the color
values in both source repeating groups are mapped to the color values in the target repeating group for all
object data specified by the source repeating groups. Repeating groups must be ordered such that all source
repeating groups are specified first, sorted in ascending order of ID, followed by all target repeating groups
sorted in ascending order of ID. Any repeating group that has a lower ID than a previous repeating group and
is of the same type (source or target) is ignored, as is any source repeating group that follows a target
repeating group.
Once a source repeating group has been matched with a target repeating group, the color values in the source
repeating group are mapped sequentially to the color values in the target repeating group. That is, the first
color value in the source repeating group is mapped to the first color value in the associated target repeating
group, the second color value in the source repeating group is mapped to the second color value in the
associated target repeating group, and so on. If there are moresource color values than target color values,
the source color values that do not have targets are mapped to presentation process default color values. If
there are more target color values than source color values, the extra target color values are ignored. If the
same source color value is mapped to more than one target color value, the first-specified target color value is
used.
The presentation device uses the color mapping table to search the specified data objects for the source color
values, and to replace the source color values with the target color values when rendering the data.
Color Resources


Color Mapping Table Syntax [MODCA-A-043]
| Offset | Type | Name | Range | Meaning | M/O [MODCA-A-044]|
| --- | --- | --- | --- | --- | --- [MODCA-A-045]|
| 0–1 | UBIN | TBLlngth | | 6–65,535 T able length | M [MODCA-A-046]|
| 2–3 | CODE | TBLid | | 1–65,534 T able ID | M [MODCA-A-047]|
| 4 CODE TBLtpe X'01', X'81' T able type: | | | | | X'01' Color mapping table X'81' Reset color mapping table [MODCA-A-048]|
| 5 | Reserved; | should | | be zero | M For a color mapping table (TBLtpe = X'01'), at least one source and one target repeating group in the following format: Source Repeating Group [MODCA-A-049]|
| 0–1 | UBIN | RGLngth | | 30–(n+1) Repeating group length | M [MODCA-A-050]|
| 2 | UBIN | RGId | | 1–127 Repeating group ID | M [MODCA-A-051]|
| 3 CODE RGTpe X'01' Repeating group type: | | | | | X'01' Source color value repeating group All others Reserved [MODCA-A-052]|
| 4 CODE ColSpce X'06', X'40', X'50' Color space: | | | | | X'06' Highlight color space X'40' Standard OCA color space X'50' GOCA Pattern Fill space All others Reserved [MODCA-A-053]|
| 5–8 | Reserved; | should | | be zero | M [MODCA-A-054]|
| 9 UBIN ColSize1 X'08', X'10' Number of bits in component 1; see | | | | | color space definitions [MODCA-A-055]|
| 10 UBIN ColSize2 X'00', X'08' Number of bits in component 2; see | | | | | color space definitions [MODCA-A-056]|
| 11 UBIN ColSize3 X'00', X'08' Number of bits in component 3; see | | | | | color space definitions [MODCA-A-057]|
| 12 | Reserved; | should | | be zero | M Color Resources [MODCA-A-058]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-A-059]|
| --- | --- | --- | --- | --- | --- [MODCA-A-060]|
| 13 CODE ObjSel X'6B', X'7B', X'9B', | | | | | X'AF', X'BB', X'DF', X'EB', X'FB', X'FE', X'FF' Source object type selector: X'6B' Object area X'7B' IM Image data X'9B' PTOCA data X'AF' Page presentation space X'BB' GOCA data X'DF' Overlay presentation space X'EB' BCOCA data X'FB' Non-tiled bilevel IOCA image data X'FE' All PTOCA, GOCA, BCOCA, non-tiled bilevel IOCA, and IM Image object data X'FF' All objects, object areas, and presentation spaces All others Reserved [MODCA-A-061]|
| 14–29 | Reserved; | should | | be zero | M [MODCA-A-062]|
| 30–n Color Values Sequential list of color values to be | | | | | mapped O Target Repeating Group [MODCA-A-063]|
| 0–1 | UBIN | RGLngth | | 13–(m+1) Repeating group length | M [MODCA-A-064]|
| 2 | UBIN | RGId | | 1–127 Repeating group ID | M [MODCA-A-065]|
| 3 CODE RGTpe X'02' Repeating group type: | | | | | X'02' T arget color value repeating group All others Reserved [MODCA-A-066]|
| 4 CODE ColSpce X'01', X'04', X'06', | | | | | X'08' Color space: X'01' RGB X'04' CMYK X'06' Highlight color space X'08' CIELAB All others Reserved [MODCA-A-067]|
| 5–8 | Reserved; | should | | be zero | M [MODCA-A-068]|
| 9 UBIN ColSize1 X'01'–X'08', X'10' Number of bits in component 1; see | | | | | color space definitions [MODCA-A-069]|
| 10 UBIN ColSize2 X'00'–X'08' Number of bits in component 2; see | | | | | color space definitions [MODCA-A-070]|
| 11 UBIN ColSize3 X'00'–X'08' Number of bits in component 3; see | | | | | color space definitions [MODCA-A-071]|
| 12 UBIN ColSize4 X'00'–X'08' Number of bits in component 4; see | | | | | color space definitions [MODCA-A-072]|
| 13–m Color Values Sequential list of color values to be | | | | | mapped O Color Resources [MODCA-A-073]|


Color Mapping Table Semantics
TBLlngth Contains the length of the table, including this length field, in bytes.
TBLid Contains the identifier for the table.
TBLtpe Is a code that defines the type of table.
Value Description
X'01' Color Mapping T able. The table specifies mappings of source color values to
target color values.
X'81' Reset Color Mapping T able. The table resets all source-color-value to target-
color-value mappings. The remainder of the table is ignored.
RGlngth Contains the length of the repeating group, including this length field, in bytes. The limits n and
m, defined for source and target repeating groups respectively, are determined by the overall
mapping table length limitation, which is 65,535, and by the number of repeating groups and
their size.
RGid Contains the identifier for the repeating group. This identifier is used to match source color
value repeating groups with a target color value repeating group.
RGtpe Is a code that defines the type of repeating group.
Value Description
X'01' Source color value repeating group. The repeating group specifies a list of
color values that aresources of a color mapping.
X'02' T arget color value repeating group. The repeating group specifies a list of
color values that are targets of a color mapping.
ColSpce Is a code that defines the color space and the encoding for the color specification. Color
spaces are defined in the MO:DCA Color Specification (X'4E') triplet; see “Color Specification
Triplet X'4E'”. Only color spaces that are not defined in the X'4E' triplet, or color
spaces that have a special meaning when used in a CMT , are described here.
Value Description
X'06' Highlight color space. This is the same color space as that defined in the
Color Specification (X'4E') triplet. In addition, if this color space is specified in
a source repeating group, a value of X'FF' for the percent coverage
parameter indicates that all percentages of this parameter for the specified
highlight color are mapped to the target color.
Application Note: When the Highlight Color space is specified in a target
repeating group, the percent coverage parameter is normally only
supported for areas such as object areas and graphic fill areas. For
other data types this parameter is normally simulated with 100%
coverage.
Implementation Note: The percent shading parameter for highlight colors is
currently not supported in AFP environments.
X'40' Standard OCA color space. This is the same color space as that defined in
the Color Specification (X'4E') triplet. All syntactically valid color values
defined in the Standard OCA Color Value T able are supported for mapping.
For a list of all valid color values, see “Standard OCA Color Value T able”.
X'50' GOCA Pattern Fill space. Component 1 defines the GOCA pattern set local
ID as specified by the Set Pattern Set drawing order, and must be set to X'00'
to select the GOCA default pattern set. ColSize1 is set to X'08' and defines
the number of bits used to specify component 1. Component 2 defines a code
Color Resources


point, as specified by the Set Pattern Symbol drawing order, that selects a
specific pattern symbol from the default pattern set and is in the range X'00'–
X'10', X'40'. ColSize2 is set to X'08' and defines the number of bits used to
specify component 2. ColSize3 and ColSize4 are reserved and must be set to
zero. If this color space is specified in a source repeating group, the pattern fill
is replaced by the target color value independent of any color that may have
been specified for the pattern in the GOCA data. If the pattern fill is not to be
replaced by a color, this pattern should not be mapped. For a description of
graphics area fill, pattern sets, and pattern symbols, see the Graphics Object
Content Architecture for AFP Reference.
ColSize1–
Colsize4
For a definition of these parameters, see the description of the “Color Specification Triplet
X'4E'”.
ObjSel Is a code that defines the data type to which the color values specified in the source repeating
group apply.
Value Description
X'00' The parameter is not specified. This value must be used in target repeating
groups.
X'6B' The source color values apply to object areas.
X'7B' The source color values apply to data in IM Image objects.
X'9B' The source color values apply to data in PTOCA text objects.
X'AF' The source color values apply to page presentation spaces whose color is
specified with a Color Specification (X'4E') triplet.
X'BB' The source color values apply to data in GOCA graphics objects.
X'DF' The source color values apply to overlay presentation spaces whose color is
specified with a Color Specification (X'4E') triplet.
X'EB' The source color values apply to data in BCOCA bar code objects.
X'FB' The source color values apply to data in non-tiled bilevel IOCA image objects.
X'FE' The source color values apply to all PTOCA, GOCA, BCOCA, non-tiled
bilevel IOCA, and IM Image data objects.
X'FF' The source color values apply to all objects, object areas, and presentation
spaces.
Color Values Is a sequential list of color values in the defined format and encoding. For source repeating
groups, these values, when encountered in one of the specified source object types, are
mapped to target values. For target repeating groups, these are the values that are rendered
by the presentation device in place of the corresponding source color values.
Color Mapping Table Exception Condition Summary
An exception condition exists when the following is detected:
• The table is a color mapping table and does not contain at least one source repeating group and one target [MODCA-A-074]
repeating group
• The table is a color mapping table and contains a source repeating group that does not have a matching [MODCA-A-075]
target repeating group
• The table contains invalid data [MODCA-A-076]
Color Resources




