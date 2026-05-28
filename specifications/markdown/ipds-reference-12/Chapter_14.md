# Chapter 14. Loaded-Font Command Set
This chapter describes the Loaded-Font command set. It also explains graphic character placement in a logical
page and describes commands used to download and manage font information at the printer.
Both coded fonts and data-object fonts are supported within the IPDS architecture in a similar fashion. This
chapter describes the commands used to download coded font components; Chapter 10, “Object Container
Command Set” describes the commands used to download data-object-font components, that
are used as component parts of a data-object font. For a general description of font concepts, refer to the Font
Object Content Architecture Reference. For a detailed description of TrueType fonts, refer to the TrueType
Font Files Technical Specification from Microsoft Corporation or the TrueType Reference Manual from Apple
Computer, Inc. For a detailed description of OpenType fonts, refer to the OpenType Specification from
Microsoft Corporation. For a detailed description of Adobe Type 1 fonts, refer to the Adobe Type 1 Font Format
from Adobe Corporation. For more information about Adobe CID-keyed fonts, refer to the CID-Keyed Font
Technology Overview from Adobe Corporation.
The following commands are contained in the Loaded-Font command set:
Table 55. Loaded-Font Commands [IPDS-14-001]

| Command | Code | Description | Subset |
| :--- | :--- | :--- | :--- |
| LCP | X'D61B' | Load Code Page | LF3, LF4 [IPDS-14-002]|
| LCPC | X'D61A' | Load Code Page Control | LF3, LF4 [IPDS-14-003]|
| LF | X'D62F' | Load Font | LF1, LF3 [IPDS-14-004]|
| LFCSC | X'D619' | Load Font Character Set Control | LF3 [IPDS-14-005]|
| LFC | X'D61F' | Load Font Control | LF1 [IPDS-14-006]|
| LFI | X'D60F' | Load Font Index | LF1 [IPDS-14-007]|
| LSS | X'D61E' | Load Symbol Set | LF2 [IPDS-14-008]|
All graphic characters, whether invoked in text, graphics, or bar code human-readable data, are defined within
a coded font resource. The coded font contains general information about the font as a whole and contains
descriptive, metric, and shape data for each graphic character in the font.
The component parts of a coded font can be downloaded to local printer storage, or they can be resident in
printer storage. To make up a coded font, these component parts must be combined in one of the following
configurations:
• A LF1-type coded font, that consists of a fully described font plus font indexes, or of several fully described [IPDS-14-009]
font sections plus font indexes for each section
• A LF2-type coded font, that consists of a symbol set [IPDS-14-010]
• A LF3-type coded font, that consists of a code page plus a font character set [IPDS-14-011]
To see which types of coded font are supported by your printer, refer to your printer documentation. [IPDS-14-012]


Graphic Character Placement Fundamentals
Note: Many of the concepts described below are illustrated in “Font Parameter Relationships”.
When a printer places a graphic character in a logical page, a character reference point coincides with the
current print position (i c, bc) on the printing baseline. The character reference point is an offset from one of the
character box reference edges.
The character-box reference edges are the top, bottom, right, and left edges that form the boundary of a
character box when the character is viewed upright. A character box is the rectangular area that contains the
character raster pattern.
The A-space value determines the inline position of the character box from the character reference point. The
A-space value is the number of L-units that immediately precede the character box when the printer places a
character raster pattern on a page.
The character reference point is offset by the number of L-units (specified as A-space) from the intersection of
the baseline and one of the following:
• The left-character edge for a 0° font inline sequence [IPDS-14-013]
• The right-character edge for a 180° font inline sequence [IPDS-14-014]
• The top-character edge for a 90° font inline sequence [IPDS-14-015]
• The bottom-character edge for a 270° font inline sequence [IPDS-14-016]
The baseline offset is the distance from the print coordinate of the baseline to a reference edge of the
character box, as described in Table 56. The baseline offset is used to locate the baseline
component of the character reference point, that coincides with the print position when the character is printed. [IPDS-14-017]


Font Inline Sequences
Font inline sequence (FIS) is the relationship between the inline direction and the rotation of character patterns
with respect to the inline direction. This relationship is as follows:
FIS = 0° The character is not rotated relative to the inline direction before it is placed on the page.
FIS = 90° The character is rotated 270° clockwise relative to the inline direction before it is placed on the
page.
FIS = 180° The character is rotated 180° clockwise relative to the inline direction before it is placed on the
page.
FIS = 270° The character is rotated 90° clockwise relative to the inline direction before it is placed on the
page.
Figure 101 shows that the same character orientation, with respect to the logical page, can be produced from
several combinations of the font inline sequence and inline direction.
The printer needs a font index table to describe the character for each font inline sequence. The table is
identified by the font inline-sequence value.
Figure 101. Example of Various Font Inline Sequences Producing the Same Character Orientation
X 0  inline direction
0  font inline sequence [IPDS-14-018]
180  font inline sequence [IPDS-14-019]
180  inline direction [IPDS-14-020]
X
90  font inline sequence [IPDS-14-021]
270  font inline sequence [IPDS-14-022]
90  inline direction [IPDS-14-023]
270  inline direction [IPDS-14-024]
X
X
Legend:  X = current print position (i , b )
= inline direction
c c


Figure 102 shows a string of three sequential characters, ABC, in all four possible font inline sequences.
Compare this to Figure 103, that shows the same string of characters in all combinations of inline
and baseline direction. Note the four possible character rotations for each inline direction specified by the font
inline sequence.
Combining the four possible rotations (shown in Figure 102) with the eight possible combinations of inline and
baseline direction (shown in Figure 103) demonstrates that a string of characters can be
presented a total of 32 different ways.
Figure 102. Rotation of Character Patterns. Rotation is with respect to the logical page, and arrows show the
inline direction. The baseline (an imaginary line on which the characters appear to rest) is shown as a
lightweight line.
Font Inline Sequence (LFI bytes 4-5)
Allowable Inline/Baseline Direction Combinations
(S
TO
 Con
trol Sequence)
0° 90° 180° 270°
0°
90°
180°
270°
90°
or
270°
180°
or
0°
Inline
Direction
Baseline
Direction
270°
or
90°
0°
or
180°
ABC
ABC
ABC
ABC
A
B
C
A B C
A
B
C
C
B
A
CBA
CBA
CBA
CBA
A
B
C
C B A
C
B
AC
B
A


Figure 103. The 32 Ways to Print Text
A B C
D E F
D AE BF C
F E D C B AF E D
C B AC F
B E
A D
C F
B E
A D C F B EA D
C F
B E
A D
F E D
C B A
F E D
C B A
C B A F E D
C B A
F E D
F C
E B
D A
F C
E B
D A
F
C
E BD AF C
E B
D A
C B A
F E D
C B A
F E D
D A
E B
F C
A D
B E
C F
A DB E C F
A D
B E
C F
A D
B E
C F
D A
E B
F C
D A
E B
F C
D E F
A B C
D E F
A B C
D E F
A B C
D E FA B C
A B C
D E F
A B C
D E F
A B C D E F
I = 0°,  B = 90°        I = 90°,  B = 180°
I = 0°,  B = 90°        I = 90°,  B = 180°
I = 0°,  B = 90°        I = 90°,  B = 180°
I = 0°,  B = 90°        I = 90°,  B = 180°
I = 90°,  B = 0°        I = 180°,  B = 90°
I = 90°,  B = 0°        I = 180°,  B = 90°
I = 90°,  B = 0°        I = 180°,  B = 90°
I = 90°,  B = 0°        I = 180°,  B = 90°
I = 270°,  B = 0°        I = 180°,  B = 270°
I = 270°,  B = 0°        I = 180°,  B = 270°
I = 270°,  B = 0°        I = 180°,  B = 270°
I = 270°,  B = 0°        I = 180°,  B = 270°
Font Inline Sequence = 0°
Font Inline Sequence = 90°
Font Inline Sequence = 180°
Font Inline Sequence = 270°
I = 0°,  B = 270°        I = 270°,  B = 180°
I = 0°,  B = 270°        I = 270°,  B = 180°
I = 0°,  B = 270°        I = 270°,  B = 180°
I = 0°,  B = 270°        I = 270°,  B = 180°
I           B
I           B
I           B
I           B
I           B
I           B
I           B
I           B
B           I
B           I
B           I
B           I
B           I
B           I
B           I
B           I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
B


The font inline sequence determines which reference edge the printer uses to determine the baseline offset as
shown in Table 56.Figure 104 shows the relationship between the printing baseline, the character
reference point, and the character box for the 0° font inline sequence.
The character baseline always passes through the character reference point. In horizontally read languages,
such as English, the character reference point is usually positioned so that the body of the character sits on the
baseline. Lowercase characters have descenders below the baseline. In vertically read languages, such as
traditional Kanji, the character reference point is usually positioned so that the baseline passes vertically
through the center of the character.
Table 56. Identifying the Baseline Offset Value [IPDS-14-025]

| Font Inline Sequence | Relationship between the Printing Baseline and the Character Box Reference Edges | Baseline Offset Value |
| :--- | :--- | :--- |
| 0° | Parallel to the top and bottom character box reference edges. | The number of L-units from the character reference point to the top character box reference edge. [IPDS-14-026]|
| 90° | Parallel to the left and right character box reference edges. | The number of L-units from the character reference point to the right character box reference edge. [IPDS-14-027]|
| 180° | Parallel to the top and bottom character box reference edges. | The number of L-units from the character reference point to the bottom character box reference edge. [IPDS-14-028]|
| 270° | Parallel to the left and right character box reference edges. | The number of L-units from the character reference point to the left character box reference edge. [IPDS-14-029]|


Font Parameter Relationships
The following figures show how to apply font parameters to character boxes in the four font inline sequences.
The characters are shown without character pattern rotation with respect to the logical page. In all the figures,
parameter values are given in L-units.
Characters Printed in the 0° Font Inline Sequence
Figure 104 shows how font parameters relate to character boxes and character raster patterns for the 0° font
inline sequence.
Figure 104. Font Inline Sequence of 0°
Current Print Position (i , b ) and the Character Reference Pointc c
Toned pel in the character raster pattern
Untoned pel in the character raster pattern
Inline Direction
Legend:
Character Increment Character Increment
Baseline Descender
for the “h” character is 0
Character Reference Box
Top Edges
Baseline
Extent
Baseline
Offset
Baseline
Descender
Baseline
Extent
Baseline
Offset A-Space A-Space [IPDS-14-030]


Characters Printed in the 90° Font Inline Sequence
Figure 105 shows how font parameters relate to character boxes and character raster patterns for the 90° font
inline sequence. There is no rotation with respect to the logical page. The inline direction for Figure 105 is 90°.
Figure 105. Font Inline Sequence of 90°
Current Print Position (i , b ) and the Character Reference Pointc c
Toned pel in the character raster pattern
Untoned pel in the character raster pattern
Inline Direction
Legend:
Baseline Extent
Baseline Descender Baseline Offset
A-Space
Character
Increment
A-Space
Character
Increment
Baseline Extent
Baseline Descender Baseline Offset
Character
Reference Box
Right Edges


Characters Printed in the 180° Font Inline Sequence
Figure 106 shows how font parameters relate to character boxes and character raster patterns for the 180° font
inline sequence. There is no rotation with respect to the logical page. The inline direction for Figure 106
is 180°.
Figure 106. Font Inline Sequence of 180°
Baseline Offset
for the “h”
character is 0
Legend: Current Print Position (i , b ) and the Character Reference Pointc c
Toned pel in the character raster pattern
Untoned pel in the character raster pattern
Inline Direction
Baseline
Extent
Baseline
Descender
Baseline
Offset
A-Space
Character Increment
Baseline
Extent
Baseline
DescenderA-Space
Character Increment
Character
Reference Box
Bottom Edges


Characters Printed in the 270° Font Inline Sequence
Figure 107 shows how font parameters relate to character boxes and character raster patterns printed in the
270° font inline sequence. There is no rotation with respect to the logical page. The inline direction for Figure
107 is 270°. [IPDS-14-031]
Figure 107. Font Inline Sequence of 270°
Length: Current Print Position (i , b ) and the Character Reference Pointc c
Toned pel in the character raster pattern
Untoned pel in the character raster pattern
Inline Direction
Baseline Extent
Baseline Offset Baseline Descender
Character
Increment
A-SpaceCharacter
Reference Box
Left Edge
Baseline Extent
Baseline Offset Baseline Descender
Character
Increment
A-Space


Printing a Kerned Character
Figure 108 shows a character that is both left-kerned and right-kerned as it is printed in the 0° font inline
sequence.
Figure 108. Left-Kerned and Right-Kerned Character
Legend: Current Print Position (i , b ) and the Character Reference Pointc c
Toned pel in the character raster pattern
Untoned pel in the character raster pattern
Inline Direction
Baseline
Extent
Baseline
Offset
Baseline
Descender
A-Space value is -3
Character
Increment
Printing an Underscore Character
Figure 109 shows an underscore character that is printed in the 0° font inline sequence. The complete
character box is below the printing baseline.
Figure 109. Underscore Character
Baseline
Descender
and
Baseline
Extent
Baseline Offset
A-Space
Baseline Offset
value is -3
Character Increment
Legend: Current Print Position (i , b ) and the Character Reference Pointc c
Untoned pel representing the pel positions from baseline to raster pattern
Toned pel in the character raster pattern
Inline Direction


Printing an Underscore with PTOCA PT2
Figure 110 shows several examples of using the PTOCA Underscore control sequence to print underscores.
These examples demonstrate that font selection, font inline sequence selection, and text orientation selection
affect the appearance and location of the underscore. The figure also shows the underscore position and
underscore width parameters.
Figure 110. Examples of Underscores Created by the PTOCA Underscore Control Sequence
Notes:
Underscore
position
(in L-units)
The dashed lines represent character baselines.
The solid lines represent underscores.
The underscore is always parallel to the character
baseline. Positive underscore positions are placed
under the character baseline in the direction of
baseline progression; negative underscore positions
are placed above the character baseline in the
opposite direction. A positive underscore position
is normally used for Latin languages. A negative
underscore position is normally used for Eastern
writing systems.
The font changes between "c" and "d"; text orientation
and font inline sequence do not change.
Text orientation changes between "e" and "f", from
0, 90 degrees to 270, 0 degrees. The I-axis
coordinate and the B-axis coordinate change. The
font and the font inline sequence do not change.
The font inline sequence changes between "h" and
"j", from 0 to 180 degrees, requiring a font change.
Text orientation changes to 270, 180 degrees.
The "m n o" characters are presented at a text
orientation of 90, 0 degrees, with a font inline
sequence of 90 degrees. The "u v w" characters are
presented at a text orientation of 270, 180 degrees,
with a font inline sequence of 90 degrees. Again,
the font has been changed.
In all cases, position and width of the underscore
is determined by the active coded font.
Underscore width
(in L-units)
m
n
ow
v
u
2.
3.
1.
4.
5.
6.
7.
A
a b c d e f g h l k j [IPDS-14-032]


LF1-Type Coded-Font Command Summary
A LF1-type coded font has two components:
• A fully described font, downloaded using the LFC, LF , and End commands. For a double-byte coded font, [IPDS-14-033]
this component is called a fully described font section. There can be several sections in an LF1-type double-
byte coded font.
• A font index, downloaded using the LFI command. There can be 1 to 4 font indexes for each fully described [IPDS-14-034]
font and for each section of an LF1-type double-byte coded font.
The LFC command sends a font control record to the printer. The font control record contains control
information that defines the fully described font and provides font metrics that apply to the font as a whole. This
control information is followed by zero or more character pattern descriptors that identify the size and
placement of the raster patterns that follow. Each descriptor is eight bytes long. The descriptor specifies each
character box size in L-units and identifies an offset value that permits the printer to find the beginning of the
character raster pattern that is provided by the LF command.
The LF command sends character raster pattern data to the printer. The character raster pattern data is the
binary representation of the toned and untoned pels for each graphic character in the font. The host program
might need several Load Font commands to send the complete character raster pattern data for a fully
described font.
The LFI command sends a font index record to the printer in one of two formats: long or short.
The DF command is used to deactivate a coded-font resource, a font index resource, or a set of double-byte
coded font sections. This command can also deactivate all single-byte coded fonts or all double-byte coded-
font sections.


The Long Format LFI
The LFI command contains 32 bytes of control information, followed by 256 font index entries. Each font index
entry is 16 bytes long and describes one graphic character in the coded font.
The control information includes a font inline sequence field, that the printer uses to distinguish each of four
possible font index tables for a given fully described font. The host program selects a particular font index table
for each coded font in the Load Font Equivalence record. The font inline sequence value identifies the rotation
of character patterns with respect to the inline direction.
The host program can send up to four font index records for each single-byte fully described font or fully
described font section. These records describe the four possible relationships between the inline direction and
the rotation of character patterns.
Each font index entry specifies a value that is an offset into the list of character pattern descriptors. The offset
value specifies which descriptor (and, therefore, which character pattern) is to be associated with the font
index entry and the code point that it describes.
For single-byte fully described fonts and double-byte fully described font sections X'41' to X'44', all LFI
commands must be long format. For all other double-byte fully described font sections, the first LFI command
activated must be long format, and any subsequent LFI commands for that double-byte fully described font
section must specify short format.
The position of each font index entry in the 256-entry list implicitly identifies the code point for that graphic
character. There is no field in the entry to specify the code point value.
For example, the first entry describes the graphic character printed for code point X'00', and the last entry
describes the graphic character printed for code point X'FF'.
The Short Format LFI
The LFI command contains 32 bytes of control information with no font index entries.
Short-format font-index records can only be specified for double-byte fully described font sections between
X'45' and X'FE'. The printer uses the 32 bytes of control information from the short-format font-index record
and the font-index entries from a previously activated long-format font-index record for the same double-byte
fully described font section as it prints graphic characters from that font. [IPDS-14-035]


Parts of an LF1-Type Coded Font
Figure 111 shows the parts of the IPDS records that are built from coded-font information. In the figure, some
field values are identified to show the relationships between the parts. In the LFC font control record example,
the values of the character pattern offset field show that each character pattern descriptor must be listed in the
same order as the character pattern data that it describes. In the long format font index record example, the
value of the font inline sequence field specifies a font inline sequence of X'0000'.
The LFI font index entries show:
• The order in which the entries are listed. This order defines the code point that specifies the character pattern [IPDS-14-036]
in print data.
• That no mandatory correlation exists between the index entry order and the order of the character pattern [IPDS-14-037]
descriptors that they reference.
Figure 111. Overview of Sample Double-Byte Coded Font Section Records
40 bytes of font control information [IPDS-14-038]
Font Control Record
Font HAID = X’nnnn’
offset = 0
offset = 1
offset = 2
offset = n
First character pattern descriptor
Character pattern offset = 00000000
Second character pattern descriptor
Character pattern offset = 00000100
Third character pattern descriptor
Character pattern offset = 00000200
Last character pattern descriptor
Character pattern offset = nnnnnnnn
32 bytes of font control information [IPDS-14-039]
Font HAID = X’nnnn’
Font inline sequence = 0000
code point = X’00’
First font index entry
Character pattern descriptor offset = 1
Long-Format Index Record
code point = X’01’
Second font index entry
Character pattern descriptor offset = 0
code point = X’02’
Third font index entry
Character pattern descriptor offset = 1
code point = X’FF’
256 font index entry [IPDS-14-040]
th
Character pattern descriptor offset = 2
First character raster pattern
Character Raster Pattern Data
Third character raster pattern
Last character raster pattern
Short-Format Font Index Record
Second character raster pattern
00000000
00000200
nnnnnnnn
00000100
32 bytes of font control [IPDS-14-041]
information
Font Inline Sequence = 2D00 [IPDS-14-042]


LF2-Type Coded-Font Command Summary
A LF2-type coded font has only one component, a symbol set.
The LSS command is used to download an entire symbol set or to download specific characters to a symbol
set already in the printer.
The DF command is used to deactivate a symbol set coded font resource.
LF3-Type Coded-Font Command Summary
A LF3-type coded font has two components:
• A font character set, downloaded using the LFCSC, LF , and END commands. [IPDS-14-043]
• A code page, downloaded using the LCPC, LCP , and END commands. [IPDS-14-044]
The LFCSC command contains control information that applies to a complete font character set or font
character set extension. One or more subsequent LF commands carry the actual font character set
information, that is in the form defined for a specific pattern technology such as the Type 1 PFB outline-font
technology. The LF commands provide a GCGID to technology-specific character ID mapping and provide the
shape and metrics information for each graphic character in the font.
Note: The shape and metrics information for the Type 1 PFB technology is the Adobe Printer Font Binary
(PFB) file. The shape and metrics information for the CID-keyed technology is a hierarchical series of
files, some of which are PFB files.
Font character set extensions are supported for some pattern-technology types, such as the CID-keyed
technology. This is especially useful with double-byte coded fonts where new graphic characters need to be
periodically added to the font. To use a font character set extension, a font character set (sometimes called a
parent font character set) must first be activated and then the extension (sometimes called a child font
character set) downloaded. The printer will temporarily merge the extension with the parent font character set.
When the parent font character set is later deactivated, the extension information is deleted.
The LCPC command contains control information that applies to a complete code page. One or more
subsequent LCP commands carry the actual code page information, that includes a code point to GCGID
mapping and processing flags that identify whether or not the code point is defined, printing, and incrementing.
A LF3-type coded font is a combination of a font character set and a code page. The AR or LFE command is
used to combine these two resources and activate an LF3-type coded font at a particular size and font inline
sequence. When a coded font is activated, there might be characters in the code page that are not present in
the font character set. In this case, if these code points are invoked, they are treated as if they were undefined,
nonprinting, and incrementing and, if printed when processing an AEA or PCA, use the variable-space code
point.
Note: When activating a coded font with a GRID, because there is only one GCSGID carried in a GRID, the
GCSGID in the GRID will be used to activate both the resident font character set and the resident code
page. Therefore, the GCSGID placed in a GRID should be carefully chosen based on which font
character set and code page GCSGIDs are supersets or subsets of one another. The generator of the
GRID should pick the subset.
The LFE command is used to map a local ID, specified within text, graphics, or bar code data, to the HAID and
font inline sequence of a coded font. [IPDS-14-045]


The DF command is used to deactivate font character sets, code pages, and coded fonts when they are no
longer needed, and to remove these resources if they were downloaded.
LF4-Type Code-Page Command Summary
This subset is used when code pages are supported with TrueType/OpenType fonts, but LF3 is not supported.
A code page is downloaded using the LCPC, LCP , and END commands.
The DF command is used to deactivate a code page.
Invoking a Coded Font
Within text, coded fonts are selected by means of the local ID parameter specified in a Set Coded Font Local
(SCFL) control sequence. Within graphics, coded fonts are selected by means of the local ID parameter in a
Set Character Set drawing order. Within bar code data, coded fonts for human-readable interpretation are
selected with the font local ID specified in a Bar Code Data Descriptor (BCDD) self-defining field.
However, before the host program can use a coded font, the host must send the Load Font Equivalence (LFE)
command to the printer. The LFE command associates local IDs with coded font Host-Assigned IDs. The
entries in the font equivalence record allow the printer to do the following:
• Equate a local ID (specified in SCFL control sequences, Set Character Set drawing orders, or BCDD self- [IPDS-14-046]
defining fields) to a coded font Host-Assigned ID.
• Specify the font-index table to use for character processing by specifying the font inline sequence. [IPDS-14-047]
In addition, the coded font must be activated with the same Host-Assigned ID as was specified in the LFE
command. A coded font can be activated by:
• Downloading coded font components. [IPDS-14-048]
For a single-byte LF1-type coded font, a fully described font and a font index must be downloaded and given
the same HAID.
For a double-byte LF1-type coded font, one or more fully described font sections, each with a font index,
must be downloaded and given the same HAID.
For an LF2-type coded font, a symbol set must be downloaded and given the same HAID.
For an LF3-type coded font, a font character set and a code page must be downloaded (these resources
have their own HAIDs), then a coded font must be activated with the Activate Resource command and given
the same HAID.
• Activating a resident coded font with an Activate Resource command or a Load Font Equivalence command. [IPDS-14-049]
• Combining appropriate downloaded and resident components and giving them the same HAID. [IPDS-14-050]
Characters within the coded font are accessed each time a code point within text, graphics, or bar code data is
processed. Double-byte code points within an LF1-type coded font use the first byte to select a font section,
and use the second byte as a code point within that section. [IPDS-14-051]


Load Code Page
The Load Code Page (LCP) command carries the data that assigns each code point of a code page to a
specific Graphic Character Global ID (GCGID). One or more LCP commands follow the Load Code Page
Control (LCPC) command.
A consecutive sequence of Load Code Page (LCP) commands transmits an entire code page. There are no
restrictions (other than the 32,767 byte limit on the length of the command) on how much or how little data is
transmitted in a Load Code Page command. The first LCP command that contains data in the series begins an
LCP entry. Entries can then be split across LCP commands at any byte boundary. The sequence of LCP
commands is terminated by an End command.
Each code-point entry in the LCP command specifies a code point to GCGID association. The entries must be
in ascending code-point order; however, the code page does not have to be fully populated. All code points
that do not have a code-point entry are assigned the default GCGID, if one is present in the LCPC command. If
a default GCGID is not present, a code point without a code-point entry is assigned the undefined, nonprinting,
and incrementing processing flags.
Specified code points that are nonprinting and nonincrementing do not require a valid GCGID since it is not
used; in this case, X'0000000000000000' can be specified in the GCGID field.
Exception ID X'02B0..04' exists if the total number of bytes received for a given code page does not match the
value specified in the code page byte-count parameter (bytes 4–7) of the Load Code Page Control command.
Note: Only Anystate commands are valid between concatenated LCP commands. Refer to Figure 45 for a list of Anystate commands.
```
Length X'D61B' Flag CID Data
```
The length of the LCP command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
However, the length of each code point entry must also be valid. Exception ID X'0202..02' exists if the
command length is invalid or unsupported.
The data for the LCP command consists of LCP entries in the following format: [IPDS-14-052]

| Offset | Type | Name | Range | Meaning | LF3, LF4 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Zero or more code point entries in the following format:** [IPDS-14-053]| | | | | |
| 0–7 | CHAR | GCGID | Any value | Graphic Character Global ID | Any value [IPDS-14-054]|
| 8 | BITS | Processing flags [IPDS-14-055]| | | |
| | bit 0 | Undefined | B'0', B'1' | Undefined | B'0', B'1' [IPDS-14-056]|
| | bit 1 | Noprint | B'0', B'1' | Nonprinting | B'0', B'1' [IPDS-14-057]|
| | bit 2 | Noincr | B'0', B'1' | Nonincrementing | B'0', B'1' [IPDS-14-058]|
| | bits 3–7 | | B'00000' | Reserved | B'00000' [IPDS-14-059]|
| 9 or 9–10 | CODE | Code point | Any value | Code point | Any value [IPDS-14-060]|
| **Zero or one Unicode-mapping-entry in the following format:** [IPDS-14-061]| | | | | |
| + 0 | UBIN | Count | X'00'–X'FF' | Number of Unicode scalar values | See byte description [IPDS-14-062]|
| **Zero or more Unicode-scalar-value entries (depending on count value) in the following format:** [IPDS-14-063]| | | | | |
| ++ 0–3 | UBIN | Unicode scalar value | X'00000000' – X'FFFFFFFF' (excluding surrogates) | Unicode scalar value to be mapped to the GCGID value and code point value | See byte description [IPDS-14-064]|
Bytes 0–7 Graphic Character Global ID
This field, if present, contains the GCGID assigned to this code point. The Technical
Reference for IBM Expanded Core Fonts specifies many commonly used GCGIDs.
Byte 8 Processing flags
Each code point in the code page has processing flags that tell the printer how to process the
code point. The flag combinations have the following meaning:
B'000' Defined, printing, incrementing
This value indicates a typical character. This code causes a character pattern
to print and the current inline print position to increment by both the character
increment and any applicable intercharacter increment.
Implementation Note: For Type 1 and CID-keyed outline fonts, the Adobe
Type Manager product will provide the character increment (called
Delta by Adobe) for each character.
B'001' Defined, printing, nonincrementing
This value indicates a character that can be used for overstriking. This code
causes a character pattern to print, but does not cause the inline print position
to be moved.
B'010' Defined, nonprinting, incrementing
This value indicates a space character. This code does not cause any
printing, but causes the inline print position to be incremented by both the
character increment and any applicable intercharacter increment.
B'011' Defined, nonprinting, nonincrementing
This value indicates a null character. This code causes no printing and no
movement of the print position.
B'100' Undefined, printing, incrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control;
refer to “XOA Exception-Handling Control”. If the data-check
exception is blocked, this entry is treated as a typical character (B'000').
B'101' Undefined, printing, nonincrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If
the data-check exception is blocked, this entry can be used for overstrikes
(B'001').
B'110' Undefined, nonprinting, incrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If [IPDS-14-065]
## Load Code Page (LCP)


the data-check exception is blocked, this entry can be used as a space
character (B'010').
B'111' Undefined, nonprinting, nonincrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If
the data-check exception is blocked, this entry can be used for a null
character (B'011').
Byte 9 or 9–10 Code point
The code point for this entry. The length of this field depends on the encoding scheme
specified in bytes 2–3 of the LCPC command. For example, the code point field is one byte
long for the fixed single-byte encoding scheme and the code point field is two bytes long for
the fixed double-byte encoding scheme.
The entries in the series of LCP commands must be specified in ascending code-point order;
exception ID X'02B0..07' exists if a code point is specified out of order or appears more than
once.
The code page does not have to be fully populated. All code points that do not have a code-
point entry are assigned the default GCGID, if one is present in the LCPC command. If a
default GCGID is not present, a code point without a code-point entry is assigned the
undefined, nonprinting, and incrementing processing flags and, if printed when processing an
AEA or PCA, uses the variable-space code point.
When a coded font is activated, there might be characters in the code page that are not
present in the font character set. If a code point without a corresponding character in the font
character set is invoked, one of the following occurs:
• If a default GCGID is specified in the LCPC command, the default GCGID and its processing [IPDS-14-066]
flags is substituted for the requested GCGID.
• If the default GCGID from the code page is used, but does not have a corresponding [IPDS-14-067]
character in the font character set, the code point is treated as undefined, nonprinting, and
incrementing and, if printed when processing an AEA or PCA, the printer prints the highlight
mark that is also used to mark PCA errors.
• If the LCPC command does not contain a default GCGID, the code point is treated as [IPDS-14-068]
undefined, nonprinting, and incrementing and, if printed when processing an AEA or PCA,
the variable-space code point is used.
Optional Unicode mapping entry
To allow code pages that contain user-defined characters (that is, those characters that have
not been registered with IBM and assigned a GCGID value) to be used with TrueType/
OpenType fonts, each code point can be mapped to one or more Unicode scalar values. This
function is selected by setting, to B'1', the Unicode-entries-provided flag in byte 8 of the LCPC
command. In this case, each repeating group entry must contain a count value in byte +0 that
specifies the number of Unicode scalar values (bytes ++ 0–3) to follow. To allow for combining
characters, each code point in the code page can be mapped to a different number of Unicode
scalar values.
Printer support for extended (Unicode mapping) code pages is indicated by property pair
X'B005' in the Loaded-Font command-set vector of an STM reply; if the printer does not
indicate support, Unicode-mapping-entry values must not be placed in the LCP commands.
+ 0 Count
This parameter specifies the number of Unicode scalar values to follow. A value of
X'00' indicates that there are no Unicode scalar values mapped to this code point. [IPDS-14-069]
## Load Code Page (LCP)


++ 0–3 Unicode scalar value
A Unicode scalar value is any Unicode code point except high-surrogate and low-
surrogate code points. In other words, the ranges of values from X'00000000' to
X'0000D7FF' and from X'0000E000' to X'0010FFFF' inclusive. Any other value is an
ill-formed Unicode value; in the future, values above X'0010FFFF' might be added to
the valid Unicode range.
The Unicode scalar value(s) should match the GCGID and should be in all TrueType/
OpenType fonts used with this code page.
Note: When multiple Unicode scalar values are specified for a code point, some
presentation devices will present the multiple characters in a device-defined
manner. When the device supports the combining character capability of
Unicode, combining characters are presented correctly; but for simpler devices,
only the first Unicode scalar value might be presented or the combining
characters might be presented one after the other or all positioned at a single
place (but not necessarily aligned correctly).
For example, suppose code point X'DC' (an EBCDIC ü character) is mapped to
two Unicode scalar values: X'0075' (u) and X'0308' (combining diaeresis).
Some devices will correctly present these two Unicode scalar values as ü;
however, other devices present characters in a one-to-one fashion and might
present the code point as u¨. In some cases, this problem can be avoided by
mapping to a single, already-combined Unicode scalar value (if such a Unicode
value is registered), such as X'00FC' (ü). [IPDS-14-070]
## Load Code Page (LCP)


Load Code Page Control
The Load Code Page Control (LCPC) command defines the descriptive information for a code page resource.
The LCPC command is followed by one or more Load Code Page (LCP) commands that specify the code page
data.
The LCPC command is valid only in home state and causes a transition to code page state. Code page state
ends when the printer receives the End command following receipt of at least one LCP command. Exception
ID X'023A..02' exists when an attempt is made to activate more coded-font components than the printer can
support; reporting of this exception is optional.
```
Length X'D61A' Flag CID Data
```
The length of the LCPC command can be:
Without CID X'0010', X'0011', X'0014', X'0015', X'001D', X'001E', X'0021', or X'0022'
With CID X'0012', X'0013', X'0016', X'0017', X'001F', X'0020', X'0023', or X'0024'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data for the LCPC command contains the following information: [IPDS-14-071]

| Offset | Type | Name | Range | Meaning | LF3, LF4 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001' – X'7EFF' | Code Page Host-Assigned ID | X'0001' – X'7EFF' [IPDS-14-072]|
| 2–3 | BITS | Encoding Scheme [IPDS-14-073]| | | |
| | bits 0–3 | | B'0000' | Reserved | B'0000' [IPDS-14-074]|
| | bits 4–7 | Code point size | B'0001', B'0010' | Number of bytes indicator:<br>Fixed single byte<br>Fixed double byte | B'0001' [IPDS-14-075]|
| | bits 8–15 | | B'00000000' | Reserved | B'00000000' [IPDS-14-076]|
| 4–7 | UBIN | Byte count | X'0000000A' – X'FFFFFFFF' | Number of data bytes in subsequent LCP commands | X'0000000A' – X'00000A00' [IPDS-14-077]|
| 8 | BITS | Extension flags [IPDS-14-078]| | | |
| | bit 0 | Unicode | B'0', B'1' | Unicode entries provided:<br>No<br>Yes | B'0', B'1' [IPDS-14-079]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' [IPDS-14-080]|
| 9 | X'00' | | | Reserved | X'00' [IPDS-14-081]|
| 10–n | CODE | Space | Any value | Variable-space code point | Any value [IPDS-14-082]|
| **GRID information if required (see byte description):** [IPDS-14-083]| | | | | |
| n+1 to n+2 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description [IPDS-14-084]|
| n+3 to n+4 | CODE | CPGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Code Page Global ID | See byte description [IPDS-14-085]|
| **Default-character information (see byte description):** [IPDS-14-086]| | | | | |
| n+5 to n+12 | CODE | Default GCGID | Any value | Default Graphic Character Global ID | Any value [IPDS-14-087]|
| n+13 | BITS | Processing flags for the default GCGID [IPDS-14-088]| | | |
| | bit 0 | Undefined | B'0', B'1' | Undefined | B'0', B'1' [IPDS-14-089]|
| | bit 1 | Noprint | B'0', B'1' | Nonprinting | B'0', B'1' [IPDS-14-090]|
| | bit 2 | Noincr | B'0', B'1' | Nonincrementing | B'0', B'1' [IPDS-14-091]|
| | bits 3–7 | | B'00000' | Reserved; bit 4 retired | B'00000' [IPDS-14-092]|
| **Zero or one Unicode scalar value for the default GCGID (only present when the Unicode flag (byte 8) is B'1')** [IPDS-14-093]| | | | | |
| + 0–3 | UBIN | Unicode scalar value | X'00000000' – X'FFFFFFFF' (excluding surrogates) | Unicode scalar value to be mapped to the default GCGID value | See byte description [IPDS-14-094]|
Bytes 0–1 Code Page Host-Assigned ID
A value that is assigned by the host to identify this code page. The HAIDs used for code pages
come from a pool of IDs that is separate from the pool used for other resources such as font
character sets, coded fonts, overlays, and page segments.
Exception ID X'02B0..00' exists if a code page with the same identifier already exists in the
printer. Exception ID X'02B0..01' exists if an invalid HAID is specified.
Bytes 2–3 Encoding scheme
This field uses the Encoding Scheme syntax defined by the Character Data Representation
Architecture (CDRA). However, the only portion of the encoding scheme value that is used by
the printer is the number-of-bytes indicator that specifies the size of each code point. A code
page can use either single-byte code points or double-byte code points. Property pair X'B001'
in a Loaded-Font command-set vector of an STM reply indicates support for double-byte code
pages.
Note: The code-point size value is a code, not a number.
Exception ID X'02B0..02' exists if an invalid or unsupported encoding-scheme value is
specified.
Bytes 4–7 Byte count
This field specifies the number of data bytes to be transmitted for this code page in
subsequent LCP commands.
Exception ID X'02B0..04' exists if the total number of bytes transmitted in the following LCP
commands does not match this value. Exception ID X'02B0..05' exists if an invalid or
unsupported byte-count value is specified. [IPDS-14-095]
## Load Code Page Control (LCPC)


Byte 8 Extension flags
These flags indicate that additional information is supplied within this code page.
Attention: Not all IPDS printers support this additional information and it is important that
extra (unsupported) information is not sent to a non-supporting printer.
Bit 0 Unicode entries provided in LCP commands
When this flag is B'1', extra information is placed in each code point entry in
the LCP commands. The extra information consists of a count field and zero
or more Unicode scalar values that represent the code point and GCGID
values for the code point entry.
This extension is used for code pages that contain character names that are
not registered in the IBM GCGID registry (such as for user-defined
characters). In this case, each code point is mapped to both a GCGID and
zero or more Unicode scalar values so that the code page can be used with
TrueType/OpenType fonts as well as with FOCA fonts. When used with a
TrueType/OpenType font, the printer first searches the code page for Unicode
scalar values and, if none are provided, then searches the internal printer
table.
Printer support for extended (Unicode mapping) code pages is indicated by
property pair X'B005' in the Loaded-Font command-set vector of an STM
reply; if the printer does not indicate support, these optional fields must not be
placed in the LCP commands.
Bits 1–7 Reserved
Byte 9 Reserved
Bytes 10–n Variable-space code point
This field specifies the code point used within PTOCA text for spacing. The length of the field
depends on the code-point size (specified in bytes 2–3). If a Set Variable Space Character
Increment (SVI) control sequence is specified within the PTOCA text, the SVI-supplied
increment value is used whenever the variable-space code point is encountered. If, however,
no SVI is specified or the SVI specified a default increment, the font-supplied increment value
for the variable-space code point or a default value is used. The character processing flags
(undefined, nonprinting, nonincrementing) in the LCP command are ignored for the variable-
space code point.
If the code point specified is not contained within the code page, or the GCGID associated
with the code point is not contained within the associated font character set, the printer uses a
character increment of 333 relative units for a typographic, proportionally-spaced font and 600
relative units for a fixed-pitch, uniform-character-increment font.
Note: When a code page is used with a data-object font, this parameter specifies the variable-
space code point. When no code page is specified for a data-object font, both Unicode
value X'0020' (space) and Unicode value X'00A0' (no-break space) are used for
variable space. For UTF-8 data, the code points are X'20' and X'C2A0', respectively.
For UTF-16 data, the code points are X'0020' and X'00A0', respectively.
Bytes n+1 to n+2
Graphic Character Set Global ID (GCSGID)
If GRID-parts information in the LCPC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Graphic Character Set Global Identifier. GCSGIDs are defined in
Corporate Standard: C-S 3-3220-050 (IBM Registry, Graphic Character Sets and Code
Pages). The printer must support all values in the range X'0000'–X'FFFF'. The value X'0000'
means that no GCSGID information is supplied. [IPDS-14-096]
## Load Code Page Control (LCPC)


If the printer specified property pair X'B003', the GCSGID and CPGID fields can either both be
present or both be absent. A valid GCSGID in this field might increase the chances for a
successful coded font activation using this code page.
If the printer did not specify property pair X'B003', the GCSGID and CPGID fields must not be
specified or exception ID X'0202..02' exists.
Bytes n+3 to n+4
Code Page Global ID (CPGID)
If GRID-parts information in the LCPC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Code Page Global Identifier. CPGIDs are defined in Corporate
Standard: C-S 3-3220-050 (IBM Registry, Graphic Character Sets and Code Pages). The
printer must support all values in the range X'0001'–X'FFFE'. Printers that support the optional
default-character-information fields (bytes n+5 to n+13) must also support the value X'0000',
that means that no CPGID information is supplied.
If the printer specifies property pair X'B003' in the Loaded-Font command-set vector of an
STM reply, the GCSGID and CPGID fields can either both be present or both be absent. A
valid CPGID in this field might increase the chances for a successful coded font activation
using this code page.
If the printer does not specify property pair X'B003', the GCSGID and CPGID fields must not
be specified or exception ID X'0202..02' exists.
Some printers that return property pair X'B003' require a valid CPGID value in this field, other
printers make use of the GCSGID and CPGID values if they are present, and still other
printers allow these fields, but ignore them. Exception ID X'02B0..03' exists in either of these
cases:
• The printer requires a valid CPGID value and one isn't supplied. [IPDS-14-097]
• The printer uses the CPGID value, but an invalid value is specified. [IPDS-14-098]
Note: The GCSGID and CPGID associated with a captured code page comes from the AR
command not the LCPC command.
Optional default-character parameters (bytes n+5 to end):
Bytes n+5 to n+12
Default Graphic Character Global ID (GCGID)
The default GCGID is used in the following circumstances:
• When no code-point entry has been specified for a code point [IPDS-14-099]
• When a code point without a corresponding character in the font character set has been [IPDS-14-100]
specified
If the LCPC command does not contain a default GCGID, missing code points and missing
characters are treated as undefined, nonprinting, and incrementing and, if printed when
processing an AEA or PCA, the variable-space code point is used.
If the default GCGID from the code page is used, but does not have a corresponding character
in the font character set, the code point is treated as undefined, nonprinting, and incrementing
and, if printed when processing an AEA or PCA, the printer prints the highlight mark that is
also used to mark PCA errors.
Note: When this code page is used with a CID-keyed font that has an empty character ID map
(that is, a font that is not character-ID-map linked), match the default GCGID with the
corresponding code point in the code page; then index into the font character set with
that code point. Use the default GCGID processing flags with this character and do not
treat this as a variable-space code point. [IPDS-14-101]
## Load Code Page Control (LCPC)


Not all IPDS printers support the default character parameters; support for these parameters
is indicated by property pair X'B004' in the Loaded-Font command-set vector of an STM reply.
If the printer did not specify property pair X'B004', bytes n+5 to n+13 must not be specified or
exception ID X'0202..02' exists.
If the printer supports the default character parameters, these two parameters must either
both be present or both be absent. If the default character parameters are specified, the
preceding GCSGID and CPGID parameters must also be specified.
Byte n+13
Processing flags for the default GCGID
The flag combinations have the following meaning:
B'000' Defined, printing, incrementing
This value indicates a typical character. This code causes a character pattern
to print and the current inline print position to increment by both the character
increment and any applicable intercharacter increment.
B'001' Defined, printing, nonincrementing
This value indicates a character that can be used for overstriking. This code
causes a character pattern to print, but does not cause the inline print position
to be moved.
B'010' Defined, nonprinting, incrementing
This value indicates a space character. This code does not cause any
printing, but causes the inline print position to be incremented by both the
character increment and any applicable intercharacter increment.
B'011' Defined, nonprinting, nonincrementing
This value indicates a null character. This code causes no printing and no
movement of the print position.
B'100' Undefined, printing, incrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control;
refer to “XOA Exception-Handling Control”. If the data-check
exception is blocked, this entry is treated as a typical character (B'000').
B'101' Undefined, printing, nonincrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If
the data-check exception is blocked, this entry can be used for overstrikes
(B'001').
B'110' Undefined, nonprinting, incrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If
the data-check exception is blocked, this entry can be used as a space
character (B'010').
B'111' Undefined, nonprinting, nonincrementing
This value produces a data-check exception unless reporting of undefined
character checks is blocked through the XOA Exception-Handling Control. If
the data-check exception is blocked, this entry can be used for a null
character (B'011').
Bit 4 of the Processing flags is retired as Retired item 133. [IPDS-14-102]
## Load Code Page Control (LCPC)


Optional Unicode mapping entry
To allow code pages that contain user-defined characters (that is, those characters that have
not been registered with IBM and assigned a GCGID value) to be used with TrueType/
OpenType fonts, the default GCGID can be mapped to a Unicode scalar value. This function is
selected by setting, to B'1', the Unicode-entries-provided flag in byte 8 of the LCPC command.
Printer support for extended (Unicode mapping) code pages is indicated by property pair
X'B005' in the Loaded-Font command-set vector of an STM reply; if the printer does not
indicate support, Unicode-mapping-entry values must not be placed in the LCPC command.
+ 0–3 Unicode scalar value
A Unicode scalar value is any Unicode code point except high-surrogate and low-
surrogate code points. In other words, the ranges of values from X'00000000' to
X'0000D7FF' and from X'0000E000' to X'0010FFFF' inclusive. Any other value is an
ill-formed Unicode value; in the future, values above X'0010FFFF' might be added to
the valid Unicode range.
The Unicode scalar value should match the default-character GCGID and should be
in all TrueType/OpenType fonts used with this code page. [IPDS-14-103]
## Load Code Page Control (LCPC)


Load Font
The Load Font (LF) command transmits either character raster patterns or outline information to a printer. The
LF command is not used for symbol sets. One or more LF commands follow either of the following control
commands:
• Load Font Control (LFC) [IPDS-14-104]
• Load Font Character Set Control (LFCSC) [IPDS-14-105]
Note: The shape and metrics information for the Type 1 PFB technology is the Adobe Printer Font Binary
(PFB) file. The shape and metrics information for the CID-keyed technology is a hierarchical series of
files, some of which are PFB files.
There are no restrictions (other than the 32,767 byte limit on the length of the command) on how much or how
little data is transmitted in a Load Font command. The data for either the LF1 format or LF3 format can be split
between two consecutive LF commands at any byte boundary. The sequence of LF commands is terminated
by an End command.
The following statements apply to LF1-type coded fonts only:
• A consecutive sequence of Load Font (LF) commands transmits the character raster patterns for both single- [IPDS-14-106]
byte coded fonts and double-byte coded font sections to the printer.
• Font character raster patterns are received as a string of bits representing the character. B'1' indicates a [IPDS-14-107]
toned pel and B'0' indicates an untoned pel. The bits are organized as a sequence of scan lines. Each scan
line is byte aligned and contains a number of bits equal to the character pattern X size determined in bytes
0–1 of the character-pattern descriptor in the Load Font Control (LFC) command. Refer to the description of
bytes 40 to end of command (character-pattern descriptors). In addition, each scan line
contains the minimum number of bits needed to pad the scan line to an integral number of bytes. Padding
bits are assumed to be B'0'. The number of scan lines per character is equal to the character pattern Y size.
• The first scan received is the top reference edge of the character box. The last scan line received is the [IPDS-14-108]
bottom reference edge of the character box. The first received pels of each scan line are the left reference
edge of the character box. The last received pels without padding of each scan line are the right reference
edge of each character box.
Exception ID X'022E..02' exists if the total number of bytes received for a given font is less than the value
specified in the byte-count parameter in the LFC or LFCSC command. Exception ID X'0232..02' exists if the
total number of bytes received for a given font is more than the value specified in the byte-count parameter in
the LFC or LFCSC command.
Note: Only Anystate commands are valid between concatenated LF commands. Refer to Figure 45 for a list of Anystate commands.
An End command is valid only after all of the font data has been transmitted. [IPDS-14-109]
## Load Font (LF)


```
Length X'D62F' Flag CID Data
```
The length of the LF command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
For LF3 format, the length of the character ID map and each technology-specific object must also be
valid. Exception ID X'0202..02' exists if the command length is invalid or unsupported.
**LF1 format** When downloading a fully described font (LF1 format), the LF data consists of a series of character raster-pattern bit strings. The data for the LF command contains the following information: [IPDS-14-110]

| Offset | Type | Name | Range | Meaning | LF1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 to end | UNDF | Font data | Any value | Character raster-pattern bit string | Any value [IPDS-14-111]|

**LF3 format** When downloading a font character set (LF3 format), the LF data consists of a character ID map followed by zero or more technology-specific objects. [IPDS-14-112]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 to n | Character ID map | | See detail description | Character ID map | See detail description [IPDS-14-113]|
| **Zero or more technology-specific objects in the following format:** [IPDS-14-114]| | | | | |
| n+1 to end | Tech object | | See detail description | Technology-specific object | See detail description [IPDS-14-115]|
Character ID map
The character ID map is used to map IBM character IDs (GCGIDs), as are found in code page objects, to the
technology-specific IDs used by this font character set.
Note: The character ID map is identical to the Font Name Map (FNN) as defined for FOCA system fonts.
The character ID map consist of three parts. The first part identifies the types of character IDs contained in the
map. The second part contains a series of entries that map GCGIDs to an offset in the third part of the map.
The third part contains the variable-length technology-specific IDs.
The number of GCGIDs in the map and the total length of the character ID map is specified in the LFCSC
command (bytes 11–16). The GCGIDs must occur in ascending-EBCDIC order; if a GCGID is out of order,
exception ID X'02B1..04' exists. [IPDS-14-116]
## Load Font (LF)


The character ID map contains the following information: [IPDS-14-117]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | IBM format | X'02' | IBM character ID format, IBM Registered EBCDIC GCGID | X'02' [IPDS-14-118]|
| 1 | CODE | Technology format | X'03', X'05' | Technology-specific character ID format:<br>X'03' Font-specific ASCII character name, used with Type 1 PFB fonts<br>X'05' CMAP binary code point, used with CID-keyed fonts | At least one value [IPDS-14-119]|
| **Zero or more entries in the following format:** [IPDS-14-120]| | | | | |
| +0–7 | CHAR | GCGID | Any value | IBM character ID as used in code pages | Any value [IPDS-14-121]|
| +8–11 | UBIN | Offset | Any value | Offset from the beginning of the character ID map (byte 0), into the following list of technology-specific ID entries; each GCGID maps to exactly one technology-specific ID | Any value [IPDS-14-122]|
| **Zero or more technology-specific ID entries in the following format:** [IPDS-14-123]| | | | | |
| ++0 | UBIN | Length | X'02' – X'80' | Length of technology-specific ID entry, including this length field | X'02' – X'80' [IPDS-14-124]|
| ++ 1–n | UNDF | Tech ID | Any value | Technology-specific ID | Any value [IPDS-14-125]|
Note: When using a CMap binary code point technology format (X'05'), multiple CMap or Rearranged objects
can be present in the font character set. The code points specified in the character ID map correspond
to the code points in the CMap or Rearranged object that is linked to the character ID map. Refer to the
description, beginning with byte 10, of the technology-specific object.
First part of Character ID map
Byte 0 IBM format
This field specifies that the 2nd part of the map contains IBM character IDs (GCGIDs). If an
invalid value is specified in this field, exception ID X'02B1..01' exists.
Byte 1 Technology format
This field specifies the technology-specific character ID format that is contained in the 3rd part
of the map. If an invalid or unsupported technology format value is specified in this field,
exception ID X'02B1..01' exists.
Property pair X'C0nn' in the Loaded-Font command-set vector of an STM reply identifies a
supported technology format. [IPDS-14-126]
## Load Font (LF)


Second part of Character ID map
Bytes +0–7 GCGID
This field specifies an IBM character ID (GCGID) that will be mapped to a technology-specific
ID.
Bytes +8–11 Offset
This field specifies an offset from the beginning of the character ID map (byte 0), into the list of
technology-specific ID entries (3rd part of the Character ID map). Each GCGID maps to
exactly one technology-specific ID.
If the offset value does not point to the beginning of a technology-specific ID entry that is in the
third part of the Character ID map, exception ID X'02B1..02' exists.
Third part of Character ID map
Byte ++0 Length of technology-specific ID entry
This field specifies the length of the technology-specific ID entry; the length value includes the
length field plus the technology-specific ID (bytes 0–n). If an invalid length value is specified in
this field, exception ID X'02B1..03' exists.
Bytes ++1 to n Technology-specific ID
This field specifies a technology-specific ID in the form specified by the technology format
(byte 1).
## Load Font (LF)


Technology-specific objects
For Type 1 PFB fonts, there is just one technology-specific object (a PFB file); bytes n+1 to m are not present
in a Type 1 PFB font. For CID-keyed fonts, there can be several technology-specific objects; each of which
describes itself in bytes n+1 to m.
Each of the technology-specific objects contain the following information: [IPDS-14-127]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–3 | UBIN | Length | X'0000000A' – X'FFFFFFFF' | Length of this technology-specific object, including this length field | X'0000000A' – X'FFFFFFFF' [IPDS-14-128]|
| 4–7 | UBIN | Checksum | Any value | Checksum value | Any value [IPDS-14-129]|
| 8–9 | UBIN | Identifier length | X'0002' – X'FFFF' | Length of the object identifier (bytes 8–n) | X'0002' – X'FFFF' [IPDS-14-130]|
| 10–n | CHAR | Identifier | Any value | Object identifier for this technology-specific object | Any value [IPDS-14-131]|
| **Bytes n+1 to m are only present for CID-keyed fonts. These bytes are omitted for Type 1 PFB fonts.** [IPDS-14-132]| | | | | |
| n+1 to n+2 | UBIN | Descriptor length | X'0002' – X'FFFF' | Length of the object descriptor (bytes n+1 to m) | X'0002' – X'FFFF' [IPDS-14-133]|
| n+3 | CODE | Object type | X'00', X'01', X'05', X'06', X'07', X'08' | Object type:<br>X'00' No object type specified<br>X'01' CMap file<br>X'05' CID file<br>X'06' PFB file<br>X'07' AFM file<br>X'08' Filename Map file (for example, ATMDATA.DAT) | X'00', X'01', X'05', X'06', X'07', X'08' [IPDS-14-134]|
| n+4 to m | | Object-type specific information | See byte description | Object-type specific information | See byte description [IPDS-14-135]|
| m+1 to end | UBIN | Object data | Any value | Object data as defined for the specific technology | Any value [IPDS-14-136]|
Bytes 0–3 Length
This value specifies the length of the technology-specific object, including this field. Exception
ID X'02B1..08' exists if an invalid length is specified.
Bytes 4–7 Checksum
The checksum applies only to the object data portion (bytes m+1 to end) of the technology-
specific object. To calculate the checksum, all of the bytes of the object data, that might be
spread across multiple LF commands, are considered as a continuous sequence of bytes. The
object data is then mapped to an array containing four unsigned bytes.
The first four bytes of object data are placed into the array as follows:
• The 1st byte of object data becomes byte 0 of the array (most significant byte). [IPDS-14-137]
• The 2nd byte of object data becomes byte 1 of the array. [IPDS-14-138]
• The 3rd byte of object data becomes byte 2 of the array. [IPDS-14-139]
• The 4th byte of object data becomes byte 3 of the array (least significant byte). [IPDS-14-140]
The remaining bytes of the object data are added on a byte-by-byte basis to the values
contained in the array; all carry bits are ignored. The mapping of the remaining object data is
done such that the 5th byte is added to the value in array position 0, the 6th byte to array [IPDS-14-141]
## Load Font (LF)


position 1, the 7th byte to array position 2, the 8th byte to array position 3, the 9th byte to array
position 0, and so forth, until all data has been processed. When all object data has been
processed, the checksum is the unsigned, 32-bit integer created from the 4-byte array.
Note: The following code fragment is shown to illustrate the algorithm:
uchar checksum_partial[4]={0,0,0,0};
short index=0;
ulong checksum;
uchar singlebyte;
while (1)
{
singlebyte=fgetc(pfb data file);
if (end of pfb data file) break;
checksum_partial[index] = checksum_partial[index]
+ singlebyte;
index = index + 1;
if (index == 4) index = 0;
}
checksum = *(ulong *)&checksum_partial[0];
Some printers compute the checksum when the technology-specific object is downloaded and
compare it with the value provided in the object; if these values aren't equal exception ID
X'02B1..09' exists.
Bytes 8–9 Identifier length
This value specifies the length of the object identifier; the length includes bytes 8–n. Exception
ID X'02B1..0A' exists if an invalid value is specified in this field.
Bytes 10–n Identifier
This is the character string name for this technology-specific object as defined by the
technology owner; refer to the description of the pattern technology ID (byte 4 in the LFCSC
command) for more information. It is used by the printer when a font character set contains
several technology-specific objects.
Bytes n+1 to m are only present for CID-keyed fonts. These bytes are omitted for Type 1
PFB fonts. The pattern technology is specified in byte 4 of the LFCSC command.
Bytes n+1
to n+2
Descriptor length
This value specifies the length of the object descriptor; the length includes bytes n+1 to m.
Exception ID X'02B1..0A' exists if an invalid value for this object type is specified in this field.
Byte n+3 Object type
This is the type of the technology-specific object. Technology-specific objects are most often
non-AFPC data objects that are architected by other companies or organizations. Specific
descriptions of technology-specific objects can be obtained from the defining source.
Unrecognized values are treated as if X'00' had been specified. [IPDS-14-142]
## Load Font (LF)


Bytes n+4
to m
Object-type specific information
The content of this field depends on the object type, as follows:
• For types X'00' and X'06'–X'08', this field is not defined by architecture and is ignored. [IPDS-14-143]
• For type X'01' (CMap file), five fields in the following sequence: [IPDS-14-144]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| n+4 | CODE | Precedence | X'00', X'01' | X'00' Primary<br>X'01' Auxiliary | X'00', X'01' [IPDS-14-145]|
| n+5 | CODE | Linkage | X'00', X'01' | X'00' Character ID map linked<br>X'01' Not character ID map linked | X'00', X'01' [IPDS-14-146]|
| n+6 | CODE | Writing direction | X'00', X'01', X'02', X'03' | X'00' No writing direction specified<br>X'01' Horizontal<br>X'02' Vertical<br>X'03' Both horizontal and vertical | X'00', X'01', X'02', X'03' [IPDS-14-147]|
| n+7 to n+8 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | X'0000', X'0001' – X'FFFE', X'FFFF' [IPDS-14-148]|
| n+9 to n+10 | CODE | CPGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Code Page Global ID<br>Use default value | X'0000', X'0001' – X'FFFE', X'FFFF' [IPDS-14-149]|
Byte n+4 Precedence
X'00' Primary
X'01' Auxiliary
This value specifies whether this technology-specific object is the primary
object of its type in the resource, or is an auxiliary (alternate) object of its
type in the resource. Auxiliary objects are ignored unless referenced within
another technology-specific object. If any value other than X'00' or X'01' is
specified, this technology-specific object is treated as if X'01' had been
specified.
Byte n+5 Linkage
X'00' Character ID map linked
X'01' Not character ID map linked
This value specifies whether or not this technology-specific object is linked
to the character ID map. CMAP files that are not linked to the character ID
map should only be used with the code page identified in bytes n+7 to n+10.
If any value other than X'00' or X'01' is specified, this technology-specific
object is treated as if X'01' had been specified.
Byte n+6 Writing direction
X'00' No writing direction specified
X'01' Horizontal
X'02' Vertical
X'03' Both horizontal and vertical [IPDS-14-150]
## Load Font (LF)


The writing direction specifies the nominal direction in which characters of
the font are written or read by the end user. Unrecognized values are
treated as if X'00' had been specified.
Bytes n+7
to n+8
IBM Graphic Character Set Global ID of the CMap
Bytes n+9
to n+10
IBM Code Page Global ID of the CMap
When a font character set contains multiple CMap objects, more efficient
processing can be achieved by using the GCSGID/CPGID pair to select the
CMap that corresponds to the code page being used. If there isn't an exact
match between the GCSGID/CPGID pair of the CMap and of the codepage,
the character ID map and the first encountered CMap that is linked to the
character ID map is used.
Note: The CMap GCSGID/CPGID pair is not used in determining how to
activate the code page; therefore, depending on the activation
method used, the code page selected for activation might not have a
corresponding CMap in the font character set.
Any additional bytes are ignored.
• For type X'05' (CID file), three fields in the following sequence: [IPDS-14-151]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| n+4 | CODE | Precedence | X'00', X'01' | X'00' Primary<br>X'01' Auxiliary | X'00', X'01' [IPDS-14-152]|
| n+5 to n+6 | UBIN | Maximum V(y) | Any value | Maximum V(y) value for all characters in the CID font | Any value [IPDS-14-153]|
| n+7 to n+8 | UBIN | Maximum W(y) | Any value | Maximum W(y) value for all characters in the CID font | Any value [IPDS-14-154]|
Byte n+4 Precedence
X'00' Primary
X'01' Auxiliary
This value specifies whether this technology-specific object is the primary
object of its type in the resource, or is an auxiliary (alternate) object of its
type in the resource. Auxiliary objects are ignored unless referenced within
another technology-specific object. If any value other than X'00' or X'01' is
specified, this technology-specific object is treated as if X'01' had been
specified.
Bytes n+5 to
n+6
Maximum V(y) value for all characters in the CID font
This is the maximum of all the Adobe ATM V(y) values for the characters in
this font character set. Each V(y) value is the y coordinate of the distance
from the character origin to the character positioning point. For horizontal
writing modes, the character origin and the character positioning point are
normally coincident.
Bytes n+7 to
n+8
Maximum W(y) value for all characters in the CID font
This is the maximum of all the Adobe ATM W(y) values for the characters in
this font character set. Each W(y) value is the y coordinate of the distance
from the character positioning point to the character escapement point. For
horizontal writing modes, the character positioning point and the character
escapement point are normally on the same horizontal line.
Any additional bytes are ignored. [IPDS-14-155]
## Load Font (LF)


Figure 112. Example of the V(y) and W(y) Values
Vertical
Character
Positioning
Point
Wy
Character Increment
Character
Origin
Vertical
Character
Escapement
Point
B-space
Vy
A-space
C-space
Bytes m+1 to
end of object
Object data
This field contains the shape and metrics information for the font character set. For a Type 1
PFB technology font, this is an Adobe Printer Font Binary (PFB) file. For a CID-keyed
technology font, this is one of a hierarchical series of files, some of which are PFB files. If the
printer detects an error in the technology-specific object data or if a needed technology-
specific object is missing, exception ID X'02B1..0B' exists. [IPDS-14-156]
## Load Font (LF)


Load Font Character Set Control
The Load Font Character Set Control (LFCSC) command provides control information for each font character
set that the host downloads to the printer. The LFCSC command is not used for LF1-type or LF2-type coded
fonts. The LFCSC command is followed by one or more Load Font (LF) commands that contain the actual font
character set information. This command is valid only in home state and causes a transition to font state. Font
state ends when the printer receives the End command following receipt of at least one LF command.
Exception ID X'023A..02' exists when an attempt is made to activate more coded-font components than the
printer can support; reporting of this exception is optional.
Font character set extensions are supported for some pattern-technology types, such as the CID-keyed
technology. This is especially useful with double-byte coded fonts where new graphic characters need to be
periodically added to the font. To use a font character set extension, a font character set (sometimes called a
parent font character set) must first be activated and then the extension (sometimes called a child font
character set) downloaded. The printer will temporarily merge the extension with the parent font character set.
When the parent font character set is later deactivated, the extension information is deleted.
```
Length X'D619' Flag CID Data
```
The length of the LFCSC command can be:
Without CID X'0016' or X'001A'
With CID X'0018' or X'001C'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data for the LFCSC command contains the following information: [IPDS-14-157]

| Offset | Type | Name | Range | Meaning | LF3 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font character set Host-Assigned ID | X'0001' – X'7EFF' [IPDS-14-158]|
| 2–3 | X'0000' | | | Reserved | X'0000' [IPDS-14-159]|
| 4 | CODE | Pattern technology | X'1E', X'1F' | Pattern Technology ID:<br>X'1E' CID-keyed technology<br>X'1F' Type 1 PFB technology | At least one value [IPDS-14-160]|
| 5 | X'00' | | | Reserved | X'00' [IPDS-14-161]|
| 6 | BITS | Intended-use flags [IPDS-14-162]| | | |
| | bit 0 | MICR | B'0', B'1' | Intended for MICR printing | B'0' [IPDS-14-163]|
| | bit 1 | Extension | B'0', B'1' | This is a FCS extension | B'0' [IPDS-14-164]|
| | bits 2–7 | | B'000000' | Reserved | B'000000' [IPDS-14-165]|
| 7–10 | UBIN | Load Font count | X'00000002' – X'FFFFFFFF' | Number of data bytes carried in subsequent Load Font commands | X'00000002' [IPDS-14-166]|
| 11–14 | UBIN | Map size | X'00000002' – X'FFFFFFFF' | Number of bytes in the character ID map | X'00000002' [IPDS-14-167]|
| 15–16 | UBIN | Character ID count | Any value | Number of GCGIDs in the character ID map | Any value [IPDS-14-168]|
| **GRID information if required (see byte description):** [IPDS-14-169]| | | | | |
| 17–18 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description. [IPDS-14-170]|
| 19–20 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID | See byte description. [IPDS-14-171]|
Bytes 0–1 Font character set Host-Assigned ID
A value that is assigned by the host to identify this font character set. The HAIDs used for font
character sets come from a pool of IDs that is separate from the pool used for other resources
such as code pages, coded fonts, overlays, and page segments.
Exception ID X'02B0..0A' exists if a font character set with the same identifier already exists in
the printer. Exception ID X'02B0..0B' exists if an invalid HAID is specified.
Bytes 2–3 Reserved
Byte 4 Pattern technology ID
This value indicates which pattern technology is needed to interpret this font character set.
Property pair X'C0nn' in the Loaded-Font command-set vector of an STM reply identifies a
supported pattern technology.
The Type 1 PFB and CID-keyed technologies are defined by Adobe Systems Incorporated.
The Type 1 PFB technology is described in Adobe Type 1 Font Format published by Adobe
Systems Incorporated. The CID-keyed technology is described in Adobe CMap and CIDFont
Files Specification published by Adobe Systems Incorporated.
Exception ID X'02B0..0C' exists if an invalid or unsupported pattern-technology-ID value is
specified.
Byte 5 Retired item 122
Reason for IPDS retirement: originally intended for “version of pattern technology”, but not
used.
Byte 6 Intended-use flags
These flags indicate the font creator's intended use for this font. IPDS printers ignore
intended-use flags that do not apply; for example, the MICR flag is ignored by printers that do
not support MICR.
Bit 0 Intended for MICR printing
This font was created for printing Magnetic Ink Character Recognition (MICR) text.
MICR text is normally printed using a toner that is impregnated with a magnetic
material. Support for MICR is indicated by the X'0800' feature ID in the Installed
Features self-defining field and Available Features self-defining field in an XOH-OPC
reply.
Note: Some printers that report support for MICR print only MICR, but others print
MICR and also print with non-MICR material (for example, MICR might be
supported for the front side, but not for the back side). It is up to the
Presentation Services Program to use a MICR font only with a printer that is
currently enabled for MICR printing. It is up to the application program to
ensure that MICR data is printed only in paper locations on which the printer
can use MICR material. AFP Setup Verification can be used on some printers
to ensure that a printer is properly set up for MICR printing; refer to “Printer
Setup Self-Defining Field”.
Load Font Character Set Control (LFCSC) [IPDS-14-172]


Exception ID X'02B3..01' exists if a string of text within a WT or WG command
was encountered that was to be printed with a MICR font, but MICR printing is
not available for this text string. Some printers can print MICR text on one side
of the media, but not on the other side; in this case, text data to be printed with
a MICR font that is placed on the non-MICR side of the media will cause this
exception to occur.
Bit 1 FCS extension
This is an extension of the parent font identified by this command's HAID. If the parent
font has not been previously activated with this HAID, exception ID X'02B2..01' exists.
FCS extensions are valid only with CID-keyed technology font character sets. If the
pattern technology ID in either the extension or in the parent font character set is not
X'1E', exception ID X'02B2..02' exists.
FCS extensions must specify the same value for the intended for MICR printing flag
as was specified in the parent FCS. If the flags do not match, exception ID X'02B2..04'
exists.
The printer temporarily merges the character ID map in the extension with the parent
character ID map by inserting new GCGID entries and by replacing entries with the
same GCGID in both the extension and the parent. If either the IBM character ID
format or the technology-specific character ID format in the extension does not match
the format value in the parent, exception ID X'02B2..03' exists.
The printer temporarily merges the technology-specific objects in the extension into
the parent by inserting objects with new object identifiers and by replacing objects that
have the same object identifiers.
Multiple extensions can be made to a parent font character set. Each successive
extension is made to the previously extended font character set.
When the parent font character set is deactivated, the extension information for this
parent (the one with the same HAID as the extension) is deleted.
Bits
2–7
Reserved
Bytes 7–10 Load-Font count
This parameter specifies the number of data bytes for this font character set contained in
subsequent Load Font commands. The series of Load Font commands is ended by an End
command.
Exception ID X'022E..02' exists if the total number of bytes in the series of LF commands is
less than the value specified in this field. Exception ID X'0232..02' exists if the total number of
bytes in the series of LF commands is more than the value specified in this field. Exception ID
X'02B0..0E' exists if an invalid or unsupported Load-Font-count value is specified.
Bytes 11–14 Map size
The field specifies the size of the character ID map carried by the series of LF commands that
follow. Exception ID X'02B0..0F' exists if the map-size value is invalid or unsupported.
Exception ID X'022E..02' exists if the map-size value is greater than the Load-Font-count
value.
Load Font Character Set Control (LFCSC) [IPDS-14-173]


Bytes 15–16 Character ID count
This field specifies the number of GCGIDs in the character ID map. There must be enough
space in the character ID map for all of the characters. Exception ID X'02B0..0F' exists if the
size of the map is too small for the number of characters specified by the character-ID-count
value.
Bytes 17–18 Graphic Character Set Global ID (GCSGID)
If GRID-parts information in the LFCSC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Graphic Character Set Global Identifier. GCSGIDs are defined in
Corporate Standard: C-S 3-3220-050 (IBM Registry, Graphic Character Sets and Code
Pages). The printer must support all values in the range X'0000'–X'FFFF'. The value X'0000'
means that no GCSGID information is supplied.
If the printer specifies property pair X'B003', the GCSGID and FGID fields can either both be
present or both be absent.
If the printer does not specify property pair X'B003', the GCSGID and FGID fields must not be
specified or exception ID X'0202..02' exists.
Bytes 19–20 Font Typeface Global ID (FGID)
If GRID-parts information in the LFCSC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Font Typeface Global Identifier. The printer must support all values
in the range X'0001'–X'FFFE'.
If the printer specifies property pair X'B003', the GCSGID and FGID fields can either both be
present or both be absent.
If the printer does not specify property pair X'B003', the GCSGID and FGID fields must not be
specified or exception ID X'0202..02' exists.
Some printers that return property pair X'B003' require a valid FGID value in this field, other
printers make use of the GCSGID and FGID values if they are present, and still other printers
allow these fields, but ignore them. Exception ID X'02B0..0D' exists in either of these cases:
• The printer requires a valid FGID value and one isn't supplied. [IPDS-14-174]
• The printer uses the FGID value, but an invalid value is specified. [IPDS-14-175]
Note: The GCSGID and FGID associated with a captured font character set come from the
AR command, not the LFCSC command.
Load Font Character Set Control (LFCSC) [IPDS-14-176]


Load Font Control
The Load Font Control (LFC) command provides control information for a fully described font or a fully
described font section. The LFC command is not used for LF2-type or LF3-type coded fonts.
This command is valid only in home state and causes a transition to font state. Font state ends when the
printer receives the End command following receipt of at least one LF command. Exception ID X'023A..02'
exists when an attempt is made to activate more coded-font components than the printer can support;
reporting of this exception is optional.
The control information consists of a 40-byte header that specifies a font Host-Assigned ID, as well as other
parameters that apply to the whole font. This control information is followed by zero or more eight-byte
character-pattern descriptors, that provide the necessary information to parse the raster patterns received in
subsequent Load Font (LF) commands.
For a double-byte font, many of the parameters in the LFC command must be identical for all sections between
X'45' and X'FE' inclusive. If an LFC command for a double-byte font section in this range contains one or more
of these parameters that is different than a previously received section in this range of the same font, exception
ID X'0244..02' exists. The parameters that must be the same are:
Uniform character box flag
Uniform or maximum character box X size
Uniform or maximum character box Y size
L-unit unit base
Units per unit base
```
Length X'D61F' Flag CID Data
```
The length of the LFC command can be:
Without CID X'002D'–X'7FFD' in increments of 8
With CID X'002F'–X'7FFF' in increments of 8
Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-14-177]
## Load Font Control (LFC)


The data for the LFC command contains the following information: [IPDS-14-178]

| Offset | Type | Name | Range | Meaning | LF1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' [IPDS-14-179]|
| 2 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single-byte<br>Double-byte section ID | X'00' [IPDS-14-180]|
| 3 | CODE | LFC, LFI format | X'00' | Font control record and font index table format | X'00' [IPDS-14-181]|
| 4 | CODE | Pattern format | X'05' | Pattern data format (bounded box) | X'05' [IPDS-14-182]|
| 5 | BITS | Font type flags [IPDS-14-183]| | | |
| | bits 0–1 | | B'00' | Reserved | B'00' [IPDS-14-184]|
| | bits 2–3 | Font type | B'01', B'10' | Single-byte coded font<br>Double-byte coded font | B'01' [IPDS-14-185]|
| | bits 4–5 | | B'00' | Reserved | B'00' [IPDS-14-186]|
| | bit 6 | Uniform character box | B'1', B'0' | B'1': Font has a uniform character-box size, specified in bytes 6 and 7<br>B'0': Box size for each character is expressed in the character-pattern descriptor for that character (bytes 40 to end of command) | B'1', B'0' [IPDS-14-187]|
| | bit 7 | | B'0' | Reserved | B'0' [IPDS-14-188]|
| 6–7 | UBIN | X size | X'0000' – X'7FFF' | Uniform or maximum character-box X size | See byte description. [IPDS-14-189]|
| 8–9 | UBIN | Y size | X'0000' – X'7FFF' | Uniform or maximum character-box Y size | See byte description. [IPDS-14-190]|
| 10 | CODE | L-unit unit base | X'00', X'01', X'02' | Unit base for L-units:<br>Ten inches (fixed-metric technology)<br>Ten centimeters (fixed-metric technology)<br>Relative units (relative-metric technology) | See byte description. [IPDS-14-191]|
| 11 | X'00' | Reserved | X'00' [IPDS-14-192]| | |
| 12–13 | UBIN | X UPUB | X'0001' – X'7FFF' | Units per unit base in the X direction for L-units | See byte description. [IPDS-14-193]|
| 14–15 | UBIN | Y UPUB | X'0001' – X'7FFF' | Units per unit base in the Y direction for L-units (same as bytes 12–13 for relative metrics) | See byte description. [IPDS-14-194]|
| 16–17 | X'0000' | Reserved | X'0000' [IPDS-14-195]| | |
| 18–20 | UBIN | Byte count | X'000001' – X'7FFFFF' | Font byte count | X'000001' – X'7FFFFF' [IPDS-14-196]|
| 21 | UBIN | Data alignment | X'01'–X'FF' | Pattern data alignment value | X'01', X'04', X'08' [IPDS-14-197]|
| 22–23 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description. [IPDS-14-198]|
| 24–25 | CODE | CPGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Code Page Global ID | See byte description. [IPDS-14-199]|
| 26 | CODE | Pel-unit unit base | X'00', X'01' | Unit base for pel units:<br>ten inches<br>ten centimeters | See byte description. [IPDS-14-200]|
| 27 | X'00' | Reserved | X'00' [IPDS-14-201]| | |
| 28–29 | UBIN | X pel units | X'0000' – X'7FFF' | Pel units per unit base in the X direction | See byte description. [IPDS-14-202]|
| 30–31 | UBIN | Y pel units | X'0000' – X'7FFF' | Pel units per unit base in the Y direction | See byte description. [IPDS-14-203]|
| 32–33 | UBIN | RMMF | X'0001' – X'7FFF' | Relative-metric multiplying factor | See byte description. [IPDS-14-204]|
| 34–35 | CODE | FGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Font Typeface Global ID | See byte description. [IPDS-14-205]|
| 36 | X'01' | Reserved | X'01' [IPDS-14-206]| | |
| 37 | BITS | Intended-use flags [IPDS-14-207]| | | |
| | bit 0 | MICR | B'0', B'1' | Intended for MICR printing | B'0' [IPDS-14-208]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' [IPDS-14-209]|
| 38–39 | CODE | FW | X'0000', X'0001' – X'7FFF' | No value supplied<br>Font Width (FW) | See byte description. [IPDS-14-210]|
| **Zero or more character-pattern descriptors in the following format:** [IPDS-14-211]| | | | | |
| + 0–1 | UBIN | X size | X'0000' – X'7FFF' | Character-box X size | See byte description. [IPDS-14-212]|
| + 2–3 | UBIN | Y size | X'0000' – X'7FFF' | Character-box Y size | See byte description. [IPDS-14-213]|
| + 4–7 | UBIN | Address | X'00000000' – X'007FFFFE' | Character-pattern address | X'00000000' – X'007FFFFE' [IPDS-14-214]|
## Load Font Control (LFC)


Bytes 0–1 Font Host-Assigned ID
A previously unassigned value that is assigned by the host to identify this coded font.
Exception ID X'0239..02' exists if a font with the same identifier is already activated. Exception
ID X'0218..02' exists if an invalid HAID value is specified.
Byte 2 Section identifier
Font sections apply only to LF1-type double-byte coded fonts. Valid values are X'41' to X'FE'.
Use X'00' for LF1-type single-byte coded fonts. Property pair X'B001' in a Loaded-Font
command-set vector of an STM reply indicates support for double-byte coded fonts.
Exception ID X'0239..02' exists if a font section with the same HAID and same section
identifier is already activated. Exception ID X'0243..02' exists if an invalid section ID value is
specified.
Byte 3 Font control record and font index table format
This byte specifies the format of the data contained in both the LFC and LFI commands. There
is only one format currently defined.
Exception ID X'0221..02' exists if an invalid format value is specified.
Byte 4 Pattern data format
This byte must be X'05', that identifies a bounded-box font. A bounded-box font is a font
having characters constructed so that toned pels touch four all sides of each character box.
Exception ID X'0222..02' exists if an invalid pattern-data-format value is specified.
Byte 5 Font type
This byte contains flags that indicate the type of the fully described font.
Bits 2–3 specify whether the font is single byte or double byte. Property pair X'B001' in a
Loaded-Font command-set vector of an STM reply indicates support for double-byte coded
fonts. Bit 6 specifies whether each character in the font has the same (uniform) character-box
size or whether the box size for each character is specified in the character-pattern descriptor
for that character (bytes 40 to end of command).
Exception ID X'0223..02' exists if an invalid or unsupported font-type-flag value is specified.
Bytes 6–7 Uniform or maximum character-box X size
If fixed-metric technology is specified in byte 10, this field specifies a value one less than the
actual dimension in L-units and the printer must support all values in the range X'0000'
through X'00FF' in this field. If relative-metric technology is specified in byte 10, this field
specifies the number of bits in a Load-Font-command scan line and the printer must support
all values in the range X'0001' through X'7FFF' in this field. If an invalid or unsupported value
is specified in this field, exception ID X'0226..02' exists.
If the character box size for each character in this font is the same (byte 5, bit 6 equals B'1'),
these bytes specify the uniform box size. If the character box sizes for all characters of this
font are not the same, the box size for each character is specified in its character pattern
descriptor, and these bytes contain the maximum box size used in the font.
Fixed-metric technology was originally defined to be used with 240 pel/inch printers; for these
printers, L-units are effectively the same as pels. The character box is measured in L-units in
both directions. The first L-unit in each dimension is 0, so that the value of this parameter is
one less than the actual box size. For example, a value of 7 indicates 8 L-units, 15 indicates
16 L-units, and 31 indicates 32 L-units. [IPDS-14-215]
The X size specifies the X dimension of the box used by the printer and includes no padding.
The raster patterns are sent by LF commands and are padded so that each scan line, with
padding, is an integral number of bytes. [IPDS-14-216]
## Load Font Control (LFC)


Bytes 8–9 Uniform or maximum character box Y size
If fixed-metric technology is specified in byte 10, this field specifies a value one less than the
actual dimension in L-units and the printer must support all values in the range X'0000'
through X'00FF' in this field. If relative-metric technology is specified in byte 10, this field
specifies the number of scan lines per character carried in a Load Font command and the
printer must support all values in the range X'0001' through X'7FFF' in this field. If an invalid or
unsupported value is specified in this field, exception ID X'0227..02' exists.
If the character box size for each character in this font is the same (byte 5, bit 6 equals B'1'),
these bytes specify the uniform box size. If the character box sizes for all characters of this
font are not the same, the box size for each character is specified in its character pattern
descriptor, and these bytes contain the maximum box size used in the font.
Fixed-metric technology was originally defined to be used with 240 pel/inch printers; for these
printers, L-units are effectively the same as pels. The character box is measured in L-units in
both directions. The first L-unit in each dimension is 0, so that the value of this parameter is
one less than the actual box size. For example, a value of 7 indicates 8 L-units, 15 indicates
16 L-units, and 31 indicates 32 L-units. [IPDS-14-217]
The Y size specifies the Y dimension of the box used by the printer and includes no padding.
Byte 10 Unit base for L-units
This field specifies the unit base to be used for all fields specified in L-units in the LFC and LFI
commands. The following values are valid:
• X'00' specifies ten inches (fixed-metric technology). [IPDS-14-218]
• X'01' specifies ten centimeters (fixed-metric technology). [IPDS-14-219]
• X'02' specifies relative units (relative-metric technology). [IPDS-14-220]
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
The metric technology supported by the printer is indicated by property pair X'C1nn' in the
Loaded-Font command-set vector of an STM reply. If the printer supports fixed-metric
technology, the printer must at least support X'00' in this field. If the printer supports relative-
metric technology, the printer must support X'02' in this field. If an invalid or unsupported value
is specified in this field, exception ID X'021B..02' exists.
When X'02' is specified in this field, all fields specified in L-units in the LFC and LFI commands
are relative to the Relative-Metric Multiplying Factor specified in bytes 32–33.
Byte 11 Reserved
Bytes 12–13 Units per unit base in the X direction for L-units.
These bytes specify the number of units per unit base, used for LFC and LFI fields specified in
L-units, in specifying distance in the X direction.
If fixed-metric technology is supported, the printer must support a fixed-metric L-unit resolution
for each resolution specified in the XOH-OPC IM-Image and Coded-Font Resolution self-
defining field. If relative-metric technology is supported, the printer must support X'03E8' in
this field. If an invalid or unsupported value is specified in this field, exception ID X'022A..02'
exists.
Bytes 14–15 Units per unit base in the Y direction for L-units
These bytes specify the number of units per unit base, used for LFC and LFI fields specified in
L-units, in specifying distance in the Y direction.
If fixed-metric technology is supported, the printer must support a fixed-metric L-unit resolution
for each resolution specified in the XOH-OPC IM-Image and Coded-Font Resolution self-
defining field. If relative-metric technology is supported, the printer must support X'03E8' in [IPDS-14-221]
## Load Font Control (LFC)


this field and bytes 14–15 must be the same as bytes 12–13. If an invalid or unsupported
value is specified in this field, exception ID X'022B..02' exists.
Bytes 16–17 Reserved
Bytes 18–20 Font byte count
This parameter specifies the number of bytes to be transmitted, in subsequent LF commands,
for this font or font section. If an invalid value is specified, exception ID X'021C..02' exists. This
exception ID is not issued by all IPDS printers.
Byte 21 Pattern data alignment value
This parameter indicates the boundary alignment for the raster pattern data. This value is
multiplied by the character pattern address in each character-pattern descriptor to determine
the byte offset into the concatenated LF data records.
For example, X'04' indicates a font with character patterns having starting addresses of four-
byte aligned; X'08' indicates a font with character patterns having starting addresses of eight-
byte aligned, and so on.
Exception ID X'022D..02' exists if an invalid or unsupported pattern-data-alignment value is
specified.
Bytes 22–23 Graphic Character Set Global ID (GCSGID)
If GRID-parts information in the LFC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Graphic Character Set Global Identifier. GCSGIDs are defined in
Corporate Standard: C-S 3-3220-050 (IBM Registry, Graphic Character Sets and Code
Pages). The printer must support at least the value X'0000'.
Note: The GRID associated with a captured fully described font or font section comes from the
AR command not the LFC command.
Bytes 24–25 Code Page Global ID (CPGID)
If GRID-parts information in the LFC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Code Page Global Identifier. CPGIDs are defined in Corporate
Standard: C-S 3-3220-050 (IBM Registry, Graphic Character Sets and Code Pages). The
printer must support at least the value X'0000'.
Some printers require a valid CPGID value in this field, other printers make use of the CPGID
value if it is present, and still other printers allow this field, but ignore its contents. Exception ID
X'0246..03' exists in either of these cases:
• The printer requires a valid CPGID value and one isn't supplied. [IPDS-14-222]
• The printer uses the CPGID value, but an invalid value is specified. [IPDS-14-223]
Byte 26 Unit base for pel-units
Pel-units specify the resolution of the raster pattern information loaded via subsequent Load
Font (LF) commands. Pel is an acronym for Print ELement or Pattern ELement. Printers use
this field only to verify that the font-pattern resolution specified in bytes 28–31 matches exactly
the device resolution or can be converted by the printer to a supported resolution.
A value of X'00' specifies that the measurement unit for pel-units is ten inches. A value of X'01'
specifies that the measurement unit for pel-units is ten centimeters.
The value X'02' is retired as Retired item 65.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure. [IPDS-14-224]
## Load Font Control (LFC)


If fixed-metric technology is specified in byte 10, the printer ignores this field. If relative-metric
technology is specified in byte 10, the printer must support X'00' in this field.
Exception ID X'0287..02' exists if an invalid or unsupported unit base value is specified.
Byte 27 Reserved
Bytes 28–29 Pel-units per unit base in the X direction
These bytes specify the number of pels per unit base, sometimes called the density, in the X
direction of the font shape data being downloaded in the Load Font command.
If fixed-metric technology is specified in byte 10, this field is ignored. If relative-metric
technology is specified in byte 10, the printer must support at least the printer's device
resolution in this field, as specified in the XOH-OPC IM-Image and Coded-Font Resolution
self-defining field.
Exception ID X'0288..02' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 30–31 Pel-units per unit base in the Y direction
These bytes specify the number of pels per unit base, sometimes called the density, in the Y
direction of the font shape data being downloaded in the Load Font command.
If fixed-metric technology is specified in byte 10, this field is ignored. If relative-metric
technology is specified in byte 10, the printer must support at least the printer's device
resolution in this field, as specified in the XOH-OPC IM-Image and Coded-Font Resolution
self-defining field.
Exception ID X'0289..02' exists if an invalid or unsupported units-per-unit-base value is
specified.
Bytes 32–33 Relative-Metric Multiplying Factor
These bytes specify a multiplying factor that allows relative font-metric values to be converted
to fixed-metric values.
Exception ID X'028A..02' exists if an invalid or unsupported relative-metric-multiplying-factor
value is specified.
If fixed-metric technology is specified in byte 10, this field is ignored. If relative-metric
technology is specified in byte 10, the printer must support all values in the range X'0001'
through X'7FFF'.
The value in this field depends on how the relative font-metric values are derived. Each font-
metric value is specified in L-units, but the numeric value used is derived from a host-library
font. Host-library fonts normally contain either font-metric values specified in pels or font-
metric values specified as dimensionless values, that is, previously converted to or created in
relative units. For example:
1. If the host-library font-metric values are specified as dimensionless values, the IPDS [IPDS-14-225]
relative values are set to the host-library values and the Relative-Metric Multiplying Factor
is set to the desired vertical font size (in 1440ths of an inch/em).
2. If the host-library font-metric values are specified in pels, the IPDS relative values are set [IPDS-14-226]
to the host-library values and the Relative-Metric Multiplying Factor is set to a number
whose units are in:
L-units X 1440ths of an inch
----------------------------
pels X em
## Load Font Control (LFC)


The following formula, in which the label of each value is in parenthesis following the
value, shows how the RMMF is calculated in this case:
units per unit base (L-units/em) X 1440 (1440ths of an inch/inch)
-----------------------------------------------------------------
host-font resolution (pels/inch)
Several of the values specified in LFC and LFI commands are specified in L-units, but are
converted by the printer to other units. Relative-metric values are converted (in the printer) to
fixed-metric values using the following algorithms:
1. The relative-metric value (in L-units) is multiplied by the Relative-Metric Multiplying Factor [IPDS-14-227]
(RMMF) and is then divided by the number of L-units per relative unit. This yields a fixed-
metric value in 1440ths of an inch.
This can be demonstrated with the following formulas that depend on the method used by
the host to derive the relative-metric value (in L-units).
If the host uses dimensionless values for the relative-metric values:
fixed-metric value (1440ths of an inch) =
relative-metric value (L-units) X RMMF (1440ths of an inch/em)
--------------------------------------------------------------
units per unit base (L-units/em)
If the host uses pel values (instead of dimensionless values) for the relative-metric values:
fixed-metric value (1440ths of an inch) =
L-units X 1440ths of an inch
relative-metric value(pels) X RMMF (----------------------------)
pels X em
----------------------------------------------------------------
units per unit base (L-units/em)
2. The fixed-metric value in 1440ths of an inch can then be converted to a fixed-metric value [IPDS-14-228]
in inches, by dividing by 1440.
3. The fixed-metric value in inches can then be converted to a fixed-metric value in pels, by [IPDS-14-229]
multiplying the fixed-metric value in inches by the device resolution in pels/inch.
When converting relative units to pels, fractional pels are truncated. However, for
character increments, the fractional pels are accumulated and when the sum of the
fractions for a string of characters reaches a pel, an extra pel of space is added before the
next character.
For example, given that:
• The device resolution is 300 pels per inch [IPDS-14-230]
• The unit base for L-units is relative (one em) [IPDS-14-231]
• The units per unit base in the X and Y direction is 1000 (X'03E8') [IPDS-14-232]
• The desired fontsize is 240 1440ths of an inch/em (12-point font) [IPDS-14-233]
• The host-font resolution is 240 pels per inch [IPDS-14-234]
• A particular host-font character increment is 20 pels [IPDS-14-235]
## Load Font Control (LFC)


1. If the host chooses to use dimensionless units, that is, the character increment was [IPDS-14-236]
previously converted from 20 pels to 500 L-units, the relative-metric character increment is
converted to pels by the printer according to the following formula:
500 X 240 1 300 [IPDS-14-237]
fixed-metric value = --------- X ---- X --- = 25 pels
1000 1440 1 [IPDS-14-238]
2. If the host chooses to use pels units (because the character increment was not previously [IPDS-14-239]
converted to relative units), the host would calculate the Relative-Metric Multiplying Factor
(RMMF) as follows:
1000 X 1440 [IPDS-14-240]
RMMF = ----------- = 6000
240
The relative-metric character increment is converted to pels by the printer as follows:
20 X 6000 1 300 [IPDS-14-241]
fixed-metric value = --------- X ---- X --- = 25 pels
1000 1440 1 [IPDS-14-242]
3. For implementation reasons, a printer can do this calculation in two stages. The first stage, [IPDS-14-243]
done at download time, is to convert each incoming relative-metric value into a fixed-
metric value in some internal units. One convenient unit, for this example, is 1,440,000ths
of an inch. The first stage calculation is done as follows:
Internal value = 500 X 240 = 120 000
OR
Internal value = 20 X 6000 = 120 000
The remaining conversion (to pels) is done at print time:
120 000 1 300 [IPDS-14-244]
fixed-metric value = ------- X ---- X --- = 25 pels
1000 1440 1 [IPDS-14-245]
Bytes 34–35 Font Typeface Global ID (FGID)
If GRID-parts information in the LFC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes should
contain an IBM-registered Font Typeface Global Identifier. The printer must support at least
the value X'0000'.
Some printers require a valid FGID value in this field, other printers make use of the FGID
value if is present, and still other printers allow this field, but ignore its contents. Exception ID
X'0246..03' exists in either of these cases:
• The printer requires a valid FGID value and one isn't supplied. [IPDS-14-246]
• The printer uses the FGID value, but an invalid value is specified. [IPDS-14-247]
Byte 36 Retired item 120, must be X'01'
Exception ID X'0220..02' exists if an invalid value is specified. [IPDS-14-248]
## Load Font Control (LFC)


Byte 37 Intended-use flags
These flags indicate the font creator's intended use for this font. IPDS printers ignore
intended-use flags that do not apply; for example, the MICR flag is ignored by printers that do
not support MICR.
Bit 0 Intended for MICR printing
This font was created for printing Magnetic Ink Character Recognition (MICR) text.
MICR text is normally printed using a toner that is impregnated with a magnetic
material. Support for MICR is indicated by the X'0800' feature ID in the Installed
Features self-defining field and Available Features self-defining field in an XOH-OPC
reply.
Exception ID X'0220..01' exists if MICR printing is specified for one section of a
double-byte coded font, but not for all sections of that coded font.
Note: Some printers that report support for MICR print only MICR, but others print
MICR and also print with non-MICR material (for example, MICR might be
supported for the front side, but not for the back side). It is up to the
Presentation Services Program to use a MICR font only with a printer that is
currently enabled for MICR printing. It is up to the application program to
ensure that MICR data is printed only in paper locations on which the printer
can use MICR material. AFP Setup Verification can be used on some printers
to ensure that a printer is properly set up for MICR printing; refer to “Printer
Setup Self-Defining Field”.
Exception ID X'02B3..01' exists if a string of text within a WT or WG command
was encountered that was to be printed with a MICR font, but MICR printing is
not available for this text string. Some printers can print MICR text on one side
of the media, but not on the other side; in this case, text data to be printed with
a MICR font that is placed on the non-MICR side of the media causes this
exception to occur.
Bits
1–7
Reserved
Bytes 38–39 Font Width (FW)
If GRID-parts information in the LFC command is allowed (as specified by the X'B003'
property pair in the Loaded-Font command-set vector of an STM reply), these bytes contain
the width (in 1440ths of an inch) of the font's space character. This additional qualifier in the
GRID selects a point size within a particular FGID. The printer must support at least the value
X'0000'.
Exception ID X'0246..03' exists in either of these cases:
• The printer requires a valid FW and one isn't supplied. [IPDS-14-249]
• The printer uses the FW value, but an invalid value is specified. [IPDS-14-250]
## Load Font Control (LFC)


Bytes 40 to
end of
command
Character-pattern descriptors
Each character that has a raster pattern also has a character-pattern descriptor, that contains
the information required to parse the LF commands that follow. A printer must accept at least
1000 character pattern descriptors. Each descriptor is 8 bytes in length and contains the [IPDS-14-251]
following fields:
Offset Type Name Range Meaning LF1 Range
0–1 UBIN X size X'0000' –
X'7FFF'
Character-box X size See byte
description.
2–3 UBIN Y size X'0000' –
X'7FFF'
Character-box Y size See byte
description.
4–7 UBIN Address X'00000000' –
X'007FFFFE'
Character-pattern address X'00000000' –
X'007FFFFE'
Bytes 0–1 Character-box X size
If fixed-metric technology is specified in byte 10, this field specifies a value
one less than the actual dimension in L-units and the printer must support all
values in the range X'0000' through X'00FF' in this field. If relative-metric
technology is specified in byte 10, this field specifies the number of bits in a
Load-Font-command scan line for this character and the printer must support
all values in the range X'0001' through X'7FFF' in this field. If an invalid or
unsupported value is specified in this field, exception ID X'0226..02' exists.
The character-box X size is a binary parameter specifying the number of bits
in a Load-Font-command scan line for this character. If the uniform character-
box X size parameter (bytes 6 and 7) is in effect, this field is ignored.
Fixed-metric technology was originally defined to be used with 240 pel/inch
printers; for these printers, L-units are effectively the same as pels. The
character box is measured in L-units in both directions. The first L-unit in each
dimension is 0, so that the value of this parameter is one less than the actual
box size. For example, a value of 7 indicates 8 L-units, 15 indicates 16 L-
units, and 31 indicates 32 L-units. The X size specifies the X dimension of the
box used by the printer and includes no padding.
Bytes 2–3 Character-box Y size
If fixed-metric technology is specified in byte 10, this field specifies a value
one less than the actual dimension in L-units and the printer must support all
values in the range X'0000' through X'00FF' in this field. If relative-metric
technology is specified in byte 10, this field specifies the number of scan lines
for this character carried in a Load Font command and the printer must
support all values in the range X'0001' through X'7FFF' in this field. If an
invalid or unsupported value is specified in this field, exception ID X'0227..02'
exists.
The character-box Y size is a binary parameter specifying the number of scan
lines for this character carried in a Load Font command. If the uniform
character-box Y size parameter (bytes 8 and 9) is in effect, this field is
ignored.
Fixed-metric technology was originally defined to be used with 240 pel/inch
printers; for these printers, L-units are effectively the same as pels. The
character box is measured in L-units in both directions. The first L-unit in each
dimension is 0, so that the value of this parameter is one less than the actual
box size. For example, a value of 7 indicates 8 L-units, 15 indicates 16 L-
units, and 31 indicates 32 L-units. The Y size specifies the Y dimension of the
box used by the printer and includes no padding. [IPDS-14-252]
## Load Font Control (LFC)


Bytes 4–7 Character-pattern address
This four-byte parameter specifies an aligned offset into the concatenated LF
command data records. This value is multiplied by the character data
alignment (byte 21) to locate the first byte of the character pattern for this
character. The first LF data byte is byte 0. The character pattern addresses
must be ascending.
Although the largest possible character-pattern address is X'007FFFFE', the
total number of raster-pattern bytes is limited to X'7FFFFF' by the Font byte
count parameter (bytes 18–20). Therefore, assuming that the character data
alignment (byte 21) is one and the last character-pattern address is
X'007FFFFE', there is only one byte available for the raster-pattern data.
Exception ID X'023E..02' exists if an invalid character-pattern-address value
is specified.
## Load Font Control (LFC)


Load Font Index
The Load Font Index (LFI) command transmits font index information for a single-byte raster LF1-type coded
font or a double-byte raster LF1-type coded-font section to the printer. The LFI command is not used for LF2-
type or LF3-type coded fonts. This command is valid only in home state and transmits one complete font index
table to the printer.
Note: The HAID specified in the LFI command must match the HAID of a previously activated fully described
font or fully described font section.
Each single-byte coded font or double-byte coded font section can have up to four font index tables associated
with it. Each of the four index tables contains a unique font-inline-sequence value.
The font-inline-sequence value specifies the rotation of the characters with respect to the inline direction. If this
character rotation does not change with respect to the baseline, a new font index table is not required (even if
text orientation changes). For example, a page might be printed in both the portrait and landscape orientations,
using the same font and font index table.
A font index table can be in one of two formats: long format and short format.
• The long format consists of a 32-byte header plus 256 sixteen-byte index entries. [IPDS-14-253]
• The short format consists of a 32-byte header. [IPDS-14-254]
The LFI command for single-byte coded fonts must always be in the long format. The LFI command for double-
byte coded font sections X'41' to X'44' must also always be in the long format. For double-byte coded font
sections X'45' to X'FE', the first LFI command activated must be in the long format, but all subsequent LFI
commands with a different font inline sequence for the same double-byte coded font section must be in the
short format. In the latter case (sections X'45' to X'FE'), the font section must have a uniform character
increment, a uniform A-space, and a uniform baseline offset defined in the header (or exception ID X'023C..02'
exists). The metrics specified in the mandatory long-format LFI command are assigned to the equivalent code
points in any subsequent short-format LFI commands.
Many of the sixteen-byte index entries in the LFI command point to the raster image to be printed when the
corresponding code point is received as input data. The remaining entries are marked as undefined
characters, nulls, or blanks of specified widths.
When the font is used with PTOCA text, the font index table entry for the control-sequence escape code (X'2B')
is ignored, except when this escape code occurs in an Encrypted Data (ENC),
Repeat String (RPS), Set
Encrypted Alternate (SEA), or Transparent Data (TRN) control sequence. Similarly, if the variable space (VSP)
function is enabled, the font index entry corresponding to the font VSP code point is ignored. VSP codes within
an ENC, RPS, SEA, or TRN control sequence are treated as if they had occurred directly in text data. [IPDS-14-255]
## Load Font Index (LFI)


For a double-byte font index, many of the parameters in the LFI command must be identical for all sections
with the same font inline sequence, or must be identical for those sections in the range X'45' through X'FE'
inclusive. If an LFI command for a double-byte font section contains one or more of these parameters that
violate these rules, exception ID X'0244..02' exists.
The parameters that must be the same for all sections are:
Variable-space enable flag
Variable-space code point
The parameter that must be the same for all sections with the same FIS is:
Default variable-space increment
The parameters that must be the same for all sections in the range X'45' through X'FE' inclusive with the
same FIS are:
Uniform baseline offset
Uniform character increment
Maximum baseline extent
All orientation flags
Uniform A-space
Note: The underscore width and underscore position should be the same in all sections, but these values
are not checked for consistency. The printer uses the underscore width and position values from one
of the activated sections.
```
Length X'D60F' Flag CID Data
```
The length of the LFI command can be:
Without CID X'0025' or X'1025'
With CID X'0027' or X'1027'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data for the LFI command contains the following information: [IPDS-14-256]

| Offset | Type | Name | Range | Meaning | LF1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' [IPDS-14-257]|
| 2 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single byte<br>Double-byte section | X'00' [IPDS-14-258]|
| 3 | BITS | Space flags [IPDS-14-259]| | | |
| | bit 0 | | B'1', B'0' | Variable space enable:<br>Enabled<br>Disabled | B'1', B'0' [IPDS-14-260]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' [IPDS-14-261]|
| 4–5 | CODE | FIS | X'0000', X'2D00', X'5A00', X'8700' | Font inline sequence:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-14-262]|
| 6–7 | X'0000' | Reserved | X'0000' [IPDS-14-263]| | |
| 8–9 | SBIN | Baseline offset | X'8000' – X'7FFF' | Uniform or maximum baseline offset | See byte description. [IPDS-14-264]|
| 10–11 | SBIN | Character increment | X'8000' – X'7FFF' | Uniform or maximum character increment | See byte description. [IPDS-14-265]|
| 12–13 | X'0000' | Reserved | X'0000' [IPDS-14-266]| | |
| 14–15 | SBIN | Max extent | X'0000' – X'7FFF' | Maximum baseline extent | See byte description. [IPDS-14-267]|
| 16 | BITS | Orientation flags [IPDS-14-268]| | | |
| | bits 0–4 | | B'00000' | Reserved | B'00000' [IPDS-14-269]|
| | bit 5 | Uniform A-space | B'1', B'0' | B'1': The uniform A-space value is in bytes 18–19.<br>B'0': The A-space of each character is in the character index entry and bytes 18–19 specify the minimum value for this font index. | B'1', B'0' [IPDS-14-270]|
| | bit 6 | Uniform baseline offset | B'1', B'0' | B'1': The uniform baseline offset is in bytes 8–9.<br>B'0': The baseline offset of each character is in the character index entry and bytes 8–9 specify the maximum value for this font index. | B'1', B'0' [IPDS-14-271]|
| | bit 7 | Uniform character increment | B'1', B'0' | B'1': The uniform character increment is in bytes 10–11.<br>B'0': The increment of each character is in the character index entry and bytes 10–11 specify the maximum value for this font index. | B'1', B'0' [IPDS-14-272]|
| 17 | X'00' | Reserved | X'00' [IPDS-14-273]| | |
| 18–19 | SBIN | A-space | X'8000' – X'7FFF' | Uniform or minimum A-space | See byte description. [IPDS-14-274]|
| 20–21 | CODE | VSP | X'0000' – X'FFFF' | Variable-space code point (VSP) | X'0000' – X'FFFF' [IPDS-14-275]|
| 22–23 | SBIN | Default VSI | X'8000' – X'7FFF' | Default variable-space increment | See byte description. [IPDS-14-276]|
| 24–25 | UBIN | Underscore width | X'0000', X'0001' – X'7FFF' | Recommended width:<br>No recommendation provided<br>Underscore width in L-units | X'0000' [IPDS-14-277]|
| 26–27 | SBIN | Underscore position | X'8000' – X'7FFF' | Recommended position of underscore in L-units | X'0000' [IPDS-14-278]|
| 28–31 | X'00000000' | Reserved | X'00000000' [IPDS-14-279]| | |
| **Zero or 256 character-index entries in the following format:** [IPDS-14-280]| | | | | |
| + 0–1 | BITS | Character flags | B'000' – B'111' | Character flags | B'000' – B'111' [IPDS-14-281]|
| + 2–3 | UBIN | Pattern index | X'0000' – one less than the number of character patterns in the font | Pattern index | X'0000' – one less than the number of character patterns in the font [IPDS-14-282]|
| + 4–5 | SBIN | Character increment | X'8000' – X'7FFF' | Character increment | See byte description. [IPDS-14-283]|
| + 6–7 | SBIN | A-space | X'8000' – X'7FFF' | A-space | See byte description. [IPDS-14-284]|
| + 8–13 | X'00...00' | Reserved | X'00...00' [IPDS-14-285]| | |
| + 14–15 | SBIN | Baseline offset | X'8000' – X'7FFF' | Baseline offset | See byte description. [IPDS-14-286]|
Bytes 0–1 Font Host-Assigned ID
This identifier must match the HAID of a previously activated fully described font or fully
described font section.
Exception ID X'0218..02' exists if an invalid HAID value is specified.
Byte 2 Section identifier
Font sections apply only to double-byte coded fonts. This value should be a X'00' for single-
byte coded fonts. Double-byte coded fonts have section identifiers ranging from X'41'through
X'FE'. Property pair X'B001' in a Loaded-Font command-set vector of an STM reply indicates
support for double-byte coded fonts.
Exception ID X'0243..02' exists if an invalid section ID value is specified.
Byte 3 Flags (bit mapped)
Bit 0 Variable-space enable (VSP) bit. If bit 0 equals B'1', the variable-space
function is enabled for this font. When the printer finds the VSP code point in
text data, the inline position (i
c) is incremented by the amount of the current
variable space increment. The VSP code point is defined in bytes 20 and 21
of the current LFI command. If no variable-space increment has been
specified in the text data, the printer uses the default variable-space
increment. The default variable-space increment is defined in bytes 22 and 23
of the current LFI command.
If bit 0 equals B'0', the VSP function is disabled for this font. The VSP code
point is interpreted like any other character, and its attributes are defined in its
font index table entry.
Bits 1–7 Reserved
Bytes 4–5 Font-inline sequence
This parameter specifies one of the four font indexes that can be associated with a fully
described font or fully described font section. Property pair X'A0nn' in a Loaded-Font
command-set vector of an STM reply identifies the supported font-inline sequence values.
Exception ID X'0240..02' exists if an invalid or unsupported FIS value is specified.
This parameter defines the relationship between the inline direction and the rotation of
character patterns with respect to the inline direction. The font-inline-sequence parameter is
also necessary for:
• Locating the character reference point [IPDS-14-287]
• Locating the character-box leading edge (used to position font patterns on a baseline) [IPDS-14-288]
A vector drawn in the font-inline-sequence direction from the leading edge of the character
box points into the character box. When the pattern is printed, it is rotated so that the vector
points in the same direction as the inline sequence direction specified by the most recent Set
Text Orientation (STO) control sequence in a Write Text (WT) command. The font-inline-
sequence direction is measured in degrees, as follows: [IPDS-14-289]
## Load Font Index (LFI)


0° Specifies that the baseline proceeds from the left reference edge (leading edge) of the
character box to the right reference edge
90° Specifies that the baseline proceeds from the top reference edge (leading edge) of the
character box to the bottom reference edge
180° Specifies that the baseline proceeds from the right reference edge (leading edge) of
the character box to the left reference edge
270° Specifies that the baseline proceeds from the bottom reference edge (leading edge)
of the character box to the top reference edge.
The font-inline sequence, in conjunction with the I-axis orientation specified by the most recent
STO control sequence, determines the rotation of characters with respect to the X
p,Yp
coordinate system. Figure 113 shows this relationship; Figure 102 shows the
same relationship graphically.
Exception ID X'0246..02' exists if a font index received for a given fully described font has the
same inline sequence as a previous index received for the same font.
Figure 113. Character Rotation with Respect to the Logical Page Coordinate System
Font-Inline Sequence
I-A
xis Orientation
0° 90°
180°
270°
0° 90° 180° 270°
90°
0°
180°
0°
270°
90°
180° 270°
270°
0°
90°
180°
180°
270°
0°
90°
Bytes 6–7 Reserved
Bytes 8–9 Uniform or maximum baseline offset
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'FF00' through X'0100' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
If byte 16, bit 6 equals B'1', this parameter specifies the number of L-units to be used as the
uniform baseline offset for all characters in the font index. If byte 16, bit 6 equals B'0', this
parameter is the maximum baseline offset for the font index, and the baseline offset for each
character is defined in the index entry.
The baseline offset is used to locate the baseline component of the character reference point
that coincides with the print position when the character is printed. Characters with a font inline
sequence of 0° or 180° have a baseline that is parallel to the top and bottom character
reference edges, as follows:
• If the font inline sequence is 0°, the baseline offset is the number of L-units from the [IPDS-14-290]
character reference point to the top character-box reference edge.
• If the font inline sequence is 180°, the baseline offset is the number of L-units from the [IPDS-14-291]
character reference point to the bottom character box reference edge. [IPDS-14-292]
## Load Font Index (LFI)


Characters with a font inline sequence of 90° or 270° have a baseline that is parallel to the left
and right character reference edges, as follows:
• If the font inline sequence is 90°, the baseline offset is the number of L-units from the [IPDS-14-293]
character reference point to the right character box reference edge.
• If the font inline sequence is 270°, the baseline offset is the number of L-units from the [IPDS-14-294]
character reference point to the left character box reference edge.
Bytes 10–11 Uniform or maximum character increment
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'0000' through X'00FF' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
If byte 16, bit 7 equals B'1', this parameter specifies the distance in L-units that the print
position is moved along the baseline in the +I direction after each character in this font and
font inline sequence is printed.
If byte 16, bit 7 equals B'0', the character increment is taken from the index entry for each
character and bytes 10–11 specify the maximum character increment for this font index.
Bytes 12–13 Reserved
Bytes 14–15 Maximum baseline extent
The maximum baseline extent is a measurement perpendicular to the character baseline that
is defined to be one of the following:
• If all of the characters in the font are above the character baseline, the maximum baseline [IPDS-14-295]
extent is the maximum ascender height.
• If all of the characters in the font are below the character baseline, the maximum baseline [IPDS-14-296]
extent is the maximum descender depth.
• Otherwise, the maximum baseline extent is the sum of the uniform or maximum baseline [IPDS-14-297]
offset (LFI bytes 8–9) and the maximum baseline descender of all characters in the font.
Characters with a font inline sequence of 0° or 180° have a baseline that is parallel to the top
and bottom character reference edges, as follows:
• If the font inline sequence is 0°, the baseline descender is the number of L-units from the [IPDS-14-298]
character baseline to the bottom character-box reference edge.
• If the font inline sequence is 180°, the baseline descender is the number of L-units from the [IPDS-14-299]
character baseline to the top character-box reference edge.
Characters with a font inline sequence of 90° or 270° have a baseline that is parallel to the left
and right character reference edges, as follows:
• If the font inline sequence is 90°, the baseline descender is the number of L-units from the [IPDS-14-300]
character baseline to the left character-box reference edge.
• If the a font inline sequence is 270°, the baseline descender is the number of L-units from [IPDS-14-301]
the character baseline to the right character-box reference edge.
This field specifies a dimension in L-units that can be used as a quick check to determine if a
row of characters is completely contained in the current valid printable area. If the maximum
baseline extent value indicates that one of the characters in the font might extend outside of
the current valid printable area, further checking must be done.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'0000' through X'0100' in this field. If relative-metric technology is [IPDS-14-302]
## Load Font Index (LFI)


specified in byte 10 of the LFC command, the printer must support all values in the range
X'0000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
Byte 16 Orientation flags
This parameter contains flag bits that specify whether the A-space, baseline offset, and
character increment values are uniform for each character or are specified separately for each
character in the character-index entries.
Exception ID X'023C..02' exists if one or more of the orientation flags for a section between
X'45' and X'FE' is not uniform.
Byte 17 Reserved
Bytes 18–19 Uniform or minimum A-space
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'FF01' through X'00FF' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
If byte 16, bit 5 equals B'1', this field specifies the uniform A-space for this font index. If byte
16, bit 5 equals B'0', this field specifies the minimum A-space for this font index.
Bytes 20–21 Variable-space code point (VSP)
This field specifies the VSP code point for the font index. This field is used only if the variable-
space function is enabled for the font index (byte 3, bit 0 equals B'1'). If the variable-space
function is disabled (byte 3, bit 0 equals B'0'), this field is ignored.
For single-byte coded fonts, only byte 20 (the high-order byte) is used; byte 21 is ignored. For
double-byte coded fonts, the two-byte value specifies the single code point for the VSP
character. If the code point specified is not contained within the font section, the code point is a
valid VSP code point only if the section specified for the same font inline sequence is not
loaded when the code point is encountered in text data.
The value of the variable-space increment is set by the Set Variable-Space Character
Increment (SVI) control sequence in a WT command. If this control sequence has not been
issued, or if it specifies the use of the default variable-space increment, bytes 22–23 define the
increment that the printer uses.
Bytes 22–23 Default variable-space increment
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'0000' through X'00FF' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
This field specifies the default variable-space increment for the font in the font inline
sequence. The SVI control sequence defines the value of the variable-space increment. The
default variable-space increment is used only if the control sequence specifies the use of a
default or if the control sequence has not been issued.
For single-byte coded fonts, the printer ignores this field when the VSP function is disabled
(byte 3, bit 0 equals B'0'). For double-byte coded fonts, this field is used as the increment for
double-byte code points that reference a font section that is not stored in the printer. [IPDS-14-303]
## Load Font Index (LFI)


Bytes 24–25 Underscore width
These bytes specify the font-designer recommended width (thickness), in L-units, for
underscores produced by the PTOCA Underscore (USC) control sequence. A value of X'0000'
specifies that a recommendation is not provided for underscore width and underscore
position.
The underscore width parameter is a recommendation only; some printers use a printer-
defined underscore width. Property pair X'B002' in a Loaded-Font command-set vector of an
STM reply indicates that the printer uses the underscore width value. This parameter is
ignored when using the coded font with PTOCA PT1, Graphics, and bar code data.
Exception ID X'023C..02' exists if an invalid or unsupported underscore width value is
specified.
Bytes 26–27 Underscore position
These bytes specify the font-designer recommended distance, in L-units, from the baseline to
the top of the underscore, for underscores produced by the PTOCA Underscore (USC) control
sequence. A positive value specifies that the top of the underscore is below the baseline. A
negative value specifies that the top of the underscore is above the baseline. Note that a value
of X'0000' in the underscore width field (bytes 24–25) specifies that both underscore width and
underscore position are not provided; if underscore width is not X'0000', an underscore
position of X'0000' specifies that the top edge of the underscore is coincident with the
baseline.
Underscores produced by USC are parallel to the baseline and begin at the coordinate
determined by the current print position and the underscore position. The length of the
underscore is defined by PTOCA in the description of the USC control sequence.
The underscore position parameter is a recommendation only; some printers use a printer-
defined underscore position. Property pair X'B002' in a Loaded-Font command-set vector of
an STM reply indicates that the printer uses the underscore position value. This parameter is
ignored when using the coded font with PTOCA PT1, Graphics, and bar code data.
Bytes 28–31 Reserved
Bytes 32–4127 Index entries
Each of the 256 index entries is 16 bytes long. The position of each index entry in the 256
entries identifies the code point that it describes. For example, index entry 1 describes code
point X'00', and index entry 256 describes code point X'FF'.
Offset Type Name Range Meaning LF1 Range
0–1 BITS Character flags
bit 0 Undefined B'0'–B'1' Undefined character flag B'0'–B'1'
bit 1 Nonprinting B'0'–B'1' Nonprinting character flag B'0'–B'1'
bit 2 Non-
incrementing
B'0'–B'1' Nonincrementing character flag B'0'–B'1'
bits 3–15 B'0...0' Reserved B'0...0'
2–3 UBIN Pattern index X'0000' – one
less than the
number of
character
patterns in
the font
Pattern index X'0000' – one
less than the
number of
character
patterns in the
font
4–5 SBIN Character
increment
X'8000' –
X'7FFF'
Character increment See byte
description.
## Load Font Index (LFI)


Offset Type Name Range Meaning LF1 Range
6–7 SBIN A-space X'8000' –
X'7FFF'
A-space See byte
description.
8–13 X'00...00' Reserved X'00...00'
14–15 SBIN Baseline offset X'8000' –
X'7FFF'
Baseline offset See byte
description.
Bytes 0–1 Character flags
Bits 0–2 Flags
Flags for the corresponding character indicating that it is:
1. Defined or undefined [IPDS-14-304]
2. To be printed or not to be printed [IPDS-14-305]
3. Incrementing or nonincrementing [IPDS-14-306]
The values of these three flags are as follows:
B'000' Defined, printing, incrementing
This value indicates a typical character. This combination causes a
character pattern to print and the current inline print position to
increment by both the character increment and any applicable
intercharacter increment.
B'001' Defined, printing, nonincrementing
This value indicates a character that can be used for overstriking.
This combination causes a character pattern to print, but does not
cause the print position to be moved.
B'010' Defined, nonprinting, incrementing
This value indicates a space character. This combination does not
cause any printing, but causes the i
c to be incremented by both the
character increment and any applicable intercharacter increment.
B'011' Defined, nonprinting, nonincrementing
This value indicates a null character. This combination causes no
printing and no movement of the print position.
B'100' Undefined, printing, incrementing
This value produces a data-check exception unless reporting of
undefined character checks is blocked through the XOA Exception-
Handling Control; refer to “XOA Exception-Handling Control”
277. If the data-check exception is blocked, this combination is [IPDS-14-307]
treated as if it indicated a typical character (B'000').
B'101' Undefined, printing, nonincrementing
This value produces a data-check exception unless reporting of
undefined character checks is blocked through the XOA Exception-
Handling Control. If the data-check exception is blocked, this
combination can be used for overstrikes (B'001').
B'110' Undefined, nonprinting, incrementing
This value produces a data-check exception unless reporting of
undefined character checks is blocked through the XOA Exception-
Handling Control. If the data-check exception is blocked, this
combination can be used as a space character (B'010'). [IPDS-14-308]
## Load Font Index (LFI)


Double-byte coded font characters that are part of an undefined
double-byte coded font section are treated as if they had this flag-bit
selection (except for the variable-space code point).
B'111' Undefined, nonprinting, nonincrementing
This value produces a data-check exception unless reporting of
undefined character checks is blocked through the XOA Exception-
Handling Control. If the data-check exception is blocked, this
combination can be used for a null character (B'011').
Note: B'001', B'100', and B'101' are invalid for double-byte sections in the
range X'45'–X'FE'. Exception ID X'023B..02' exists if an invalid
character-flags value is specified.
Bits 3–15 Reserved
Bytes 2–3 Pattern index
These bytes locate the character raster pattern to be printed. The pattern index is a 0-based
integer index into the character pattern descriptor list received with the Load Font Control
command. A code point has a pattern index value of X'0000' if the code point indicates the first
character in the character pattern descriptor list and the first raster pattern in the font patterns.
The pattern index bytes 2–3 must point to a valid raster pattern for this font.
Exception ID X'023C..02' exists if the pattern-index value refers to a nonexistent pattern.
Bytes 4–5 Character increment
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'0000' through X'00FF' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
These bytes specify the number of L-units by which the inline coordinate of the current print
position increments when this character occurs in text data. If a uniform character increment is
in effect, this field is ignored.
Bytes 6–7 A-space
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'FF01' through X'00FF' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists.
These bytes indicate how much empty space precedes the character pattern in the inline
direction. If a uniform A-space is in effect, this field is ignored.
Bytes 8–13 Reserved
Bytes 14–15 Baseline offset
This field specifies a dimension in L-units.
If fixed-metric technology is specified in byte 10 of the LFC command, the printer must support
all values in the range X'FF00' through X'0100' in this field. If relative-metric technology is
specified in byte 10 of the LFC command, the printer must support all values in the range
X'8000' through X'7FFF' in this field. If an invalid or unsupported value is specified in this field,
exception ID X'023C..02' exists. [IPDS-14-309]
## Load Font Index (LFI)


These bytes are used to locate the baseline component of the character reference point, that
coincides with the print position when the character is printed.
If a uniform baseline offset is in effect, this field is ignored. [IPDS-14-310]
## Load Font Index (LFI)


Load Symbol Set
Implementation note: The Load Symbol Set (LSS) command is obsolete and shown here because it was
used in a few very early IPDS printers. Symbol sets are defined by an old technology that was used in
the early IBM graphics architecture (GOCA) mainly for displays. Some IPDS impact printers contain
resident fonts using the symbol set technology; but these fonts are rarely downloaded to any IPDS
printer.
The Load Symbol Set (LSS) command provides control and raster pattern information for the code points of a
symbol set coded font. This command is valid only in home state and does not result in a state transition. It is
not applicable to printers that do not support symbol sets. Exception ID X'023A..02' exists when an attempt is
made to activate more coded-font components than the printer can support; reporting of this exception is
optional.
Symbols set coded fonts consist of a set of characters and corresponding code points whose parameters are
simpler than those of a fully described font. Symbol sets are used when typographic quality is not required (dot
matrix printers and displays, for example).
The structure and function of this command parallels the 3270 Load Programmed Symbol (LPS) command and
the Graphics Object Content Architecture's (GOCA) Load Symbol Set (LSS) command. (The LPS command
and the IBM 3270 architecture are described in IBM 3270 Information Display System Data Stream
Programmer's Reference, GA23-0059. GOCA's LSS command is described in the Graphics Object Content
Architecture (GOCA) Reference. Common function is intended to be syntactically and semantically identical
between the IPDS architecture and both of these architectures. The 3270 architecture fields that either have no
significance for IPDS printers or are already addressed in other parts of the Loaded-Font command set are
marked here as “Retired item nn”. IPDS printers may thus ignore the contents of these retired fields.
The LSS data loaded with this command overwrites the font data for any matching code points in an existing
symbol set with the same font Host-Assigned ID. The font Host-Assigned ID is associated with the symbol set
through an LFE or AR command. Only two cases exist:
• No previous symbol set with a font Host-Assigned ID matching bytes 15–16 is activated. Therefore, this [IPDS-14-311]
command establishes a new symbol set.
• A previous symbol set with a font Host-Assigned ID matching bytes 15–16 is activated. Therefore, the [IPDS-14-312]
information transmitted by this command replaces some or all of the existing control information and all of the
existing raster information for the matching code points. This replacement happens even when the existing
information was loaded by a previous LSS command. The degree to which existing data is replaced is
explained separately for each of the parameters below.
The LSS command transmits 4 + E + T + R bytes of data where:
E is an arbitrary number of bytes defined by an extension parameter.
T is an arbitrary number of bytes defined by one or more triplets.
R is the number of bytes of raster data. Refer to the formulas. [IPDS-14-313]
## Load Symbol Set (LSS)


```
Length X'D61E' Flag CID Data
```
The length of the LSS command can be:
Without CID X'0017'–X'7FFF'
With CID X'0019'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data field for the LSS command follows: [IPDS-14-314]

| Offset | Type | Name | Range | Meaning | LF2 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | BITS | Flags1 | X'90', X'91' | Flags; bit mapped | X'90' [IPDS-14-315]|
| 1 | X'00' | | | Retired item 69 | X'00' [IPDS-14-316]|
| 2 | CODE | SCODE | X'00'–X'FF' | Starting code point | X'01'–X'FF' [IPDS-14-317]|
| 3 | X'00' | | | Retired item 70 | X'00' [IPDS-14-318]|
| 4 | UBIN | Length | X'0D'–X'FF' | Additional parameter byte length. See byte description. | X'0D' [IPDS-14-319]|
| 5 | BITS | Flags2 | X'60', X'61' | Flags; bit mapped | X'60', X'61' [IPDS-14-320]|
| 6 | UBIN | X box size | X'01'–X'FF' | Uniform character-box size X dimension, measured in pels | Per XOH OPC [IPDS-14-321]|
| 7 | UBIN | Y box size | X'01'–X'FF' | Uniform character-box size Y dimension, measured in pels | Per XOH OPC [IPDS-14-322]|
| 8 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single-byte<br>Double-byte section ID | X'00' [IPDS-14-323]|
| 9–10 | X'0000' | | | Retired items 74 and 75 | X'0000' [IPDS-14-324]|
| 11 | CODE | ECODE | Starting code point–X'FF' | Ending code point | Starting code point–X'FF' [IPDS-14-325]|
| 12–14 | X'000000' | | | Retired items 76, 77, 78 | X'000000' [IPDS-14-326]|
| 15–16 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' [IPDS-14-327]|
| 17–i | X'00...00' | | | Retired item 79 | X'00...00' [IPDS-14-328]|
| j–k | Triplets | | See byte description. | One or more triplets | X'02FF' [IPDS-14-329]|
| (k+1) to end of cmnd | UNDF | Raster | See byte description. | Character raster patterns | Any value [IPDS-14-330]|
## Load Symbol Set (LSS)


Byte 0 Flags1
This byte is bit mapped as follows:
Bit 0 Retired item 66 (must be B'1')
Bit 1 Retired item 67 (must be B'0')
Bit 2 Retired item 68 (must be B'0')
Bits 3–7 Pattern download format
• A value of B'10000' indicates that the character patterns contained in bytes [IPDS-14-331]
k+1 through the end of the command will be downloaded in a vertical
format.
This format has the bits organized as a sequence of vertical cell slices.
Each slice contains a number of bits equal to the uniform character-box Y
size (byte 7). Slices are contiguous in the raster pattern data. The last slice
of each character is padded with the minimum number of bits needed to
reach a byte boundary. Padded bits are always set to B'0'. The number of
vertical slices per character is equal to the uniform character-box X size
(byte 6).
The first vertical slice received by the printer is the left reference edge of the
character box. The last slice received is the right reference edge of the
character box. The first received pel of each slice is the top reference edge
of each character box. The last received pel of each slice that is not padded
is the bottom reference edge of each character box.
• A value of B'10001' indicates that the character patterns contained in bytes [IPDS-14-332]
k+1 through the end of the command will be downloaded in a horizontal
format.
This format is identical to the format for coded-font characters. The bits are
organized as a sequence of scan lines. Each scan line contains a number
of bits equal to the uniform character-box X size (byte 6), plus the minimum
number of bits needed to pad the scan line to an integral number of bytes.
Padding bits are assumed to be B'0'. The number of scan lines per
character is equal to the uniform character-box Y size (byte 7).
The first scan received is the top reference edge of the character box. The
last scan line received is the bottom reference edge of the character box.
The first received pel of each scan line is the left reference edge of the
character box. The last received pel of each scan line that is not padded is
the right reference edge of each character box.
Exception ID X'0228..02' exists if the pattern download format is reserved or unsupported.
Exception ID X'024B..02' exists if an invalid bit value is specified for bits 0, 1, or 2.
Byte 1 Retired item 69
Byte 2 Starting code point
This byte is the first code point in an increasing sequence of points. The ending code point is
specified in byte 11.
Exception ID X'0249..02' exists if an invalid or unsupported starting-code-point value is
specified.
Byte 3 Retired item 70 [IPDS-14-333]
## Load Symbol Set (LSS)


Byte 4 Length of additional parameter bytes
This value is the number of additional parameter bytes including byte 4 before the start of the
triplets (bytes j through k), or before the start of the character raster data if no triplets exist;
refer to byte 5, bit 7.
Exception ID X'0229..02' exists if an invalid or unsupported additional-parameter-length value
is specified.
Byte 5 Flags2
Bits 0–2 Retired item 71 (must be B'011')
Bit 3 Retired item 72
Bit 4 Retired item 73
Bits 5–6 Reserved
Bit 7 Triplets are present
A value of B'1' indicates that one or more triplets are present in bytes j
through k. A value of B'0' indicates that no triplets are present and that the
character raster data begins with byte i+1.
Exception ID X'024B..02' exists if an invalid or unsupported bit value is specified.
Byte 6 Uniform character-box X size
This byte specifies the character-box X dimension measured in pels. Exception ID X'0226..02'
exists if the host specifies a character-box dimension of X'00'. The X size specifies the X
dimension of the box used by the printer and does not include padding. The supported
character-box size (or sizes) is indicated in the Symbol-Set Support self-defining field in an
XOH-OPC reply.
If the font Host-Assigned ID in bytes 15–16 matches an existing HAID, and if one or more of
the code points specified by bytes 2 and 11 overlap previously specified code points, this
value replaces the uniform character-box X size for any matching code points.
Byte 7 Uniform character-box Y size
This byte specifies the character-box Y dimension measured in pels. Exception ID X'0227..02'
exists if the host specifies a character-box dimension of X'00'. The Y size specifies the Y
dimension of the box used by the printer and includes no padding. The supported character-
box size (or sizes) is indicated in the Symbol-Set Support self-defining field in an XOH-OPC
reply.
If the font Host-Assigned ID in bytes 15–16 matches an existing HAID, and if one or more of
the code points specified by bytes 2 and 11 overlap previously specified code points, this
value replaces the uniform character-box Y size for any matching code points.
Byte 8 Section identifier
This byte specifies the section identifier. Sections apply only to double-byte coded fonts.
Property pair X'B001' in a Loaded-Font command-set vector of an STM reply indicates support
for double-byte coded fonts. This value should be X'00' for single-byte coded fonts. Double-
byte coded fonts have section identifiers ranging from X'41' through X'FE'.
Exception ID X'0248..02' exists if an invalid or unsupported section ID value is specified.
Byte 9 Retired item 74
Byte 10 Retired item 75 [IPDS-14-334]
## Load Symbol Set (LSS)


Byte 11 Ending code point
This byte specifies the ending code point, the final value of a sequence of successive values
starting with that specified in byte 2. Exception ID X'024A..02' exists if the value specified is
not greater than or equal to the value in byte 2.
Byte 12 Retired item 76
Byte 13 Retired item 77
Byte 14 Retired item 78
Bytes 15–16 Font Host-Assigned ID
This identifier is used to map the font code points that follow to a local ID through the Load
Font Equivalence (LFE) command. Exception ID X'0218..02' exists if the LFE command entry
for this font Host-Assigned ID does not exist.
Bytes 17–i Retired item 79
These are an arbitrary number of reserved bytes derived from the value in byte 4.
Bytes j–k Triplets
Length Type Data
If byte 5, bit 7 equals B'1', these bytes contain one or more contiguous triplets.
If there are any triplets, the last one must have a length of X'02' and a type of X'FF'. These
values tell the printer that character raster patterns follow immediately.
The printer ignores all of these triplets other than to skip over them based on their length
bytes.
Bytes k+1
through end
of command
Character raster patterns
Font character raster patterns are received as a string of bits representing the character. B'1'
indicates a toned pel and B'0' indicates an untoned pel.
Exception ID X'024C..02' exists if the number of bytes received for the character raster
patterns does not match the value resulting from the following formula:
B = ¬((UBX × UBY) ÷ 8) × M (vertical download format)
or
B = (¬(UBY ÷ 8)) × (UBX) × M (horizontal download format)
where:
¬(N) = The ceiling of N, that is, the rounded-up value of N
UBX = Uniform box X-size
UBY = Uniform box Y-size
M = Number of code points
× = The symbol for multiplication
÷ = The symbol for division
If the font Host-Assigned ID in bytes 15–16 matches an existing HAID and if one or more of
the code points specified in byte 2 and byte 11 overlap previously specified code points, the
character patterns of the previously specified code points are replaced. [IPDS-14-335]
## Load Symbol Set (LSS)


