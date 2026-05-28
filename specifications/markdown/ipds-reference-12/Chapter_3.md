# Chapter 3. IPDS Overview
This chapter describes key concepts of the IPDS presentation environment. It also describes how IPDS
commands position data on a page, how IPDS commands position pages on a sheet, how IPDS commands
are processed, and how printer operating states are defined. In addition, this chapter includes a summary of
IPDS commands, operating states, resource management, and rules for handling exceptions and defaults. A
set of examples that show typical IPDS command sequences can be found in Appendix B, “Examples of IPDS
Command Sequences”.
The IPDS Presentation Environment
The IPDS presentation environment creates mixed-data pages within a hierarchy of presentation spaces.
These presentation spaces are medium presentation space, logical page presentation space, object area
presentation space, text presentation space, IO-Image presentation space, graphics presentation space, bar
code presentation space, and object container presentation space. The latter five presentation spaces contain
the data types specified by their name; they are eventually mapped into object areas on the logical page
presentation space. Refer to Figure 9 for details of this mapping.
Physical medium
The physical entity on which information is placed. An example of a physical medium is roll-fed paper.
Sheet
A division of the physical medium; there can be multiple sheets on a physical medium. For example, a
roll of paper could be divided by the printer into rectangular pieces of paper, each representing a
sheet. Envelopes are an example of a physical medium that is comprised of only one sheet.
The IPDS architecture defines four types of sheets: cut sheets, continuous forms, envelopes, and
computer output on microfilm. Each type of sheet has a top edge.
A sheet has two sides, a front side and a back side. Simplex printing uses the front side; duplex
printing uses both sides.
The physical printable area is that portion of a side in which the printer can print.
Medium presentation space
The finite address space that is mapped by the printer to a side of a sheet; there is only one medium
presentation space on a side of a sheet. The relationship (whether parallel or perpendicular) between
the top of the medium presentation space and the top edge of the sheet is defined for each type of
sheet. See Figure 18 through Figure 23 for a description of the top of the
medium presentation space with respect to the top edge of the sheet for each type of sheet.
The medium presentation space has a width and a length. for a description of the width
and length of the medium presentation space.
A page's logical page, as well as all pertinent overlay logical pages, are merged into the medium
presentation space before this space is mapped by the printer to a side of a sheet. Some IPDS printers
allow more than one page to be merged into the medium presentation space.
Page
An object that is delimited by a Begin Page command and an End Page command. Page data is
merged into the page's logical-page presentation space, that is then merged into the medium
presentation space, and then presented on a physical medium by a presentation device. A page can
contain text, image, graphics, bar code, and object container data.
Logical page
The presentation space of a page or an overlay. Throughout this document the terms logical page and
logical-page presentation space are synonymous. The logical page might or might not be the same
size as the medium presentation space into which it is merged. [IPDS-3-001]


Note: Printing can only occur where the current logical page intersects the physical printable area as it
is reflected in the medium presentation space. “The Valid Printable Area” further
describes this printing area.
Text data can be positioned anywhere on the logical page in two different ways:
1. All IPDS printers allow text to be placed directly within a logical page using the Write Text [IPDS-3-002]
command. The logical page can also contain other presentation objects specified with other IPDS
commands either before or after a Write Text command. With this method, called “text major”,
there is no text object area, and text may be printed anywhere within the valid printable area. For
text-major text, the text presentation space is the logical page. Furthermore, object areas for other
objects may be positioned with respect to the text.
2. Some IPDS printers support text objects (in addition to the text-major concept). In this case, the [IPDS-3-003]
Write Text Control command defines a presentation space, object area, and mapping option. The
text data is carried within one or more Write Text commands.
Object areas
Rectangular areas positioned on the logical page containing one of the following types of data:
• Text data contains lines of character information and text rules, which the printer places at specified [IPDS-3-004]
positions and orientations on the logical page. Refer to Chapter 5, “Text Command Set” in this reference and the Presentation Text Object Content Architecture Reference for details.
• Image data contains raster information. Two types of image data, IM-Image and IO-Image, are [IPDS-3-005]
described in separate chapters of this book. Refer to Chapter 6, “IM-Image Command Set” and Chapter 7, “IO-Image Command Set” in this reference and the Image Object
Content Architecture Reference for details.
• Graphics data contains lines, curves, areas, and other drawing elements. Refer to Chapter 8, [IPDS-3-006]
“Graphics Command Set” in this reference and the Graphics Object Content
Architecture for Advanced Function Presentation for details.
• Bar Code data contains bar-coded, machine-readable characters and can also contain human- [IPDS-3-007]
readable characters. Refer to Chapter 9, “Bar Code Command Set” in this reference
and the Bar Code Object Content Architecture Reference for details.
• Object containers contain presentation data or non-presentation data whose syntactic and semantic [IPDS-3-008]
definitions are not controlled by an AFPC presentation architecture. Refer to Chapter 10, “Object
Container Command Set” for details.
A logical page can be empty or can consist of any of the following objects:
text-major text within Write Text commands
object areas containing text, image, graphics, bar code, or object container data
page overlays
page segments
Data presentation spaces
Each type of data has a presentation space in which the data is described. The IOCA, GOCA, and
BCOCA architectures define the presentation space for their respective data types. [IPDS-3-009]


Figure 9. IPDS Presentation Spaces [IPDS-3-010]

The IPDS architecture defines a hierarchical relationship between a medium presentation space, a logical
page, and object areas. Note that, in Figure 10, the logical page boundaries do not correspond with the
medium presentation space boundaries and therefore also do not correspond with the physical medium
boundaries.
Figure 10. Object Areas in a Logical Page
Medium Presentation Space
Logical Page
Text
Text
Text
Data Object
Data Object
Independent object areas
containing text, image,
graphics, bar code, or
presentation object
container data
One of the strengths of the IPDS architecture is that independent applications can create source data for each
type of data. The output of these independent applications is merged at the printer to create an integrated
mixed-data page. For example, text data can be produced by a formatter such as Document Composition
Facility; image data can be the output of a scanner such as the IBM 3117 Scanner; and graphics data can be
produced by applications such as Interactive Chart Utility. The IPDS architecture lets you integrate application
output rather than make you use integrated applications. [IPDS-3-011]


Some IPDS printers allow multiple pages to be placed on a medium presentation space as shown in Figure 11.
The position of a page is determined by a combination of the Load Copy Control, Logical Page Position, and
XOH Set Media Origin commands.
Figure 11. Examples of Multiple Pages on a Medium Presentation Space [IPDS-3-012]

Some IPDS printers allow pages to be independently placed in a fixed partition on either the front-side medium
presentation space or the back-side medium presentation space. These printers also allow the page to be
rotated into one of four possible orientations. This allows multiple pages per sheet, some of which can be
placed on top of others. See Figure 57 for an example of this ability. [IPDS-3-013]


Cut-Sheet Emulation Mode
Some IPDS printers provide a cut-sheet emulation mode that can be used to print on continuous-forms media
that, once slit and collated, emulates two sheets of cut-sheet output. In this mode, the printer logically divides
the continuous-forms media in half parallel to the carrier strips and controls the placement of pages on either
the left side or the right side of the physical media as defined by a printer configuration option. The two portions
of the physical media are called sheetlets and are treated as if they were two separate pieces of cut-sheet
media. This logical division of the continuous-forms media is shown in Figure 12. Note that the top of each
sheetlet is a narrow edge, and the default sheetlet origin is the top-left corner of the sheetlet.
Figure 12. Logical Division of Continuous Forms for Cut-Sheet Emulation [IPDS-3-014]

The printer operator configures the printer for cut-sheet emulation mode while the printer is disconnected from
the presentation services program. Property pair X'F902' in the Device-Control command-set vector of an STM
reply indicates that cut-sheet emulation mode can be used and that the X'C3nn' keyword is supported in the
LCC command. The X'C3nn' keyword in an LCC command is used to enable cut-sheet emulation; absence of
the keyword disables the function. Once in cut-sheet emulation mode, the printer emulates cut-sheet output,
two sheetlets per sheet, until one of the following occurs:
• An LCC command without the X'C3nn' keyword is encountered. [IPDS-3-015]
• More than one copy is specified in any copy subgroup. [IPDS-3-016]
When a subsequent LCC command is received that contains the X'C3nn' keyword and specifies only one copy
in each copy subgroup, the printer returns to the emulation mode.
In addition, the X'C300' keyword provides the following function:
X'C300' Enter cut-sheet emulation mode, eject to next sheetlet, and do not allow N-up. If N-up is
specified while X'C300' is in effect, the printer exits cut-sheet emulation mode. [IPDS-3-017]


When in this mode, the printer logically divides the physical media into two equal-sized sheetlets. For the
following functions, the printer treats each sheetlet as if it were a separate sheet of cut-sheet media:
– Finishing operations specified by either the X'85' or X'8E' triplet
– Alternate offset stacking specified by the XOA-AOS command
If the post-processor does not handle left/right offset stacking, the XOA-AOS commands are combined
so that if either sheetlet is to be jogged, the entire sheet is jogged.
– Mark form specified by the XOA-MF command
– Media orientation specified in the XOH-SMO command
– Medium modifications specified in the XOH-SMM command
– Default sheetlet origin is the top-left corner of each sheetlet; the top edge is a narrow edge
– Medium overlays specified in the LCC command
– Preprinted form overlays specified in the LCC command
– Page positioning specified in the LPP command
– Suppression specified in the LCC command
– VPA and UPA checking
All other medium controls apply to the sheet (not the sheetlet).
Cut-sheet emulation mode is only supported by continuous-forms printers that support no more than one
media source and one media destination. [IPDS-3-018]


Overlays and Page Segments
The IPDS architecture provides the ability to download and temporarily store overlay and page segment
resources in the printer for later use. Once downloaded, overlays and page segments can be merged with
page data or other overlay data before a page is printed. Overlays can also be printed on a sheet side before
the page data for that side is printed; this use of an overlay is called a medium overlay.
Note that some IPDS intermediate devices, such as Remote PrintManager™ and Distributed Print Facility,
support resident and captured overlays and page segments. IPDS printers do not support resident or captured
overlays and do not support resident or captured page segments.
Overlay A predefined page that the host processor loads and sends to the printer's storage. Overlays
are often used as electronic forms. An overlay can consist of any combination of text, image,
graphics, bar code, and object container data. An overlay contains the same type of
presentation commands used in a page; however, overlays are independent of the page
environment. The key distinction between overlays and pages is that overlays are stored until
deactivated, but pages, if stored, are stored only until printed.
A stored overlay is merged with a page or another overlay by means of the Include Overlay
(IO) command. The Load Copy Control (LCC) command can be used to merge an overlay
directly on the medium presentation space independent of any page data. If the overlay is
merged by means of the IO command, the overlay logical page is mapped to the page's logical
page, or including overlay's logical page at the time that the IO command is processed. If the
overlay is merged with the LCC command, the overlay logical page is mapped to the medium
presentation space before any other logical pages are mapped to the medium presentation
space. If the LCC command specifies multiple overlays, the overlay logical pages are mapped
to the medium presentation space before any other logical pages in the order in which they
appear in the LCC command.
Page Segment A portion of a page or overlay that the host processor loads and sends to the printer's storage.
A page segment can consist of any combination of text, image, graphics, or bar code data.
Unlike overlays, page segments are not independent of the logical page environment. Page
segments are merged with a page or overlay and assume the currently active environment
provided by the containing page or overlay.
A stored page segment is merged with a page or overlay by means of the Include Page
Segment (IPS) command. The page segment data is mapped to the current logical page at the
time that the IPS command is processed.
Figure 13 shows an example of a page containing text and an object area directly, text and an
object area within an overlay, and an object area within a page segment. [IPDS-3-019]


Figure 13. A Sample Page with an Overlay and Page Segment [IPDS-3-020]

Using an Overlay as a Preprinted Form
Preprinted forms are commonly used to provide a special appearance for a document or portion of a
document; for example, a company logo on each sheet or a watermark. Colored paper is also used to
distinguish certain pages or to provide a special appearance. However, preprinted forms can be expensive and
logistically hard to manage and it is more difficult to use colored paper with continuous-forms printers
(particularly when only a few of the pages require the colored media). It is also desirable to be able to produce
a complete document starting with blank, white paper as it minimizes operator setup and does not require
special media. A preprinted form or a colored sheet can be simulated with an AFP overlay; however, using a
normal overlay can be problematic. Default AFP mixing rules require that new data is merged with earlier data
using 1) the overpaint rule for new foreground data and 2) requiring earlier data to show through elsewhere.
This can cause the unwanted hiding of some data or the unwanted presence of other data (particularly when
color of medium is used within document data). Also, unwanted results can occur because of the mix of the
media, the print technology (ink-jet vs. EP), and the halftoning technology.
Therefore, AFP provides the ability to identify an overlay as a preprinted form overlay (PFO) so that the printer
can more accurately simulate a preprinted form (or colored paper). This directs the printer to treat the overlay
data as if it were already printed on the paper before any other data is printed. A preprinted form overlay can
be invoked in two different ways:
1. When the PFO is intended for an entire sheet side, it is invoked via the LCC command in a manner similar [IPDS-3-021]
to how a medium overlay is invoked.
2. When the PFO is intended for a page (such as a MO:DCA PMC-PFO), it is invoked via the IO command [IPDS-3-022]
and there can be only one PFO for each page on the sheet.
When a medium-level PFO is invoked for a sheet side, page-level PFOs are not used for that sheet side.
The formblend mixing rule allows the printer to blend data to achieve an appearance similar to a real preprinted
form. When user data specifies color of media or when the device produces “white” (CMYK = X'00000000' for a
printer, RGB = X'FFFFFF' for an RGB display), PFO data specified at the same location shows through that
area.
A common requirement for an existing print application is to replace preprinted forms or colored paper without
changing the original application data; this can be done using a preprinted form overlay invoked with the LCC
command. Another common requirement with continuous-forms printers is to simulate cut-sheet output using
cut-sheet emulation (CSE). When cut-sheet emulation is used, there are two sheetlets on each physical sheet
and all presentation data for a sheetlet is confined to that sheetlet; data within a sheetlet does not interact or
overlap with data for any other sheetlet. The presentation data includes all pages, page overlays, medium
overlays, and preprinted form overlays.
Implementation Note: Downloading or preRIPping a PFO between the pages of a sheet can cause
performance problems since the printer might have to allocate more memory to process the overlay so
as to merge it with other data already built for the sheet. It is recommended that PFOs be downloaded
before starting the sheet on which they are used. Using the RPO command to preRIP a PFO might also
improve performance.


IPDS Mixing Rules
Foreground and Background
All IPDS presentation spaces consist of two parts: foreground and background. Foreground is the part of the
presentation space that is occupied with object data. Background is the part of the presentation space that is
not occupied with object data.
The medium and logical page presentation spaces are empty until other presentation spaces containing data
are merged with them. Empty presentation spaces contain only background, that is assigned the color of
medium.
For data-object presentation spaces, the architecture for the data object defines foreground and background,
and can specify a color attribute for both. When no color is specified for the background of a presentation
space, the background is implicitly assigned the color of medium. The following table summarizes the definition
of foreground and background in the data-object presentation spaces:
Table 5. Foreground and Background
Data Type Foreground Background
PTOCA Text Stroked and filled portion of text characters
Stroked area of text rules
Stroked area of underscores
Everything else
IM Image B'1' image bits B'0' image bits
IOCA bilevel image
IOCA bilevel tiled image
Significant image points, except for image
points for which a transparency mask has
specified B'0'
Insignificant image points
Image points for which a
transparency mask has specified
B'0'
All portions of the presentation
space not covered by image or
tiles
IOCA grayscale or color image Entire image, except for image points for which
a transparency mask has specified B'0'
All portions of the presentation
space not covered by image
Image points for which a
transparency mask has specified
B'0'
IOCA grayscale or color tiled
image
Entire tile, except for image points for which a
transparency mask has specified B'0'
All portions of the presentation
space not covered by tiles
Image points for which a
transparency mask has specified
B'0'
GOCA Graphics Stroked and filled portion of graphics characters
B'1' image points
Stroked area of lines (including arcs)
Stroked and filled portion of markers
Stroked and filled portion of pattern symbols
Entire area with solid area fill
Everything else
BCOCA Bar Code Bars and 2D modules
Stroked and filled portion of HRI characters
Stroked and filled portion of all other toned
constructs in the symbol (for example, Bearer
Bars)
Everything else


Table 5 Foreground and Background (cont'd.)
Data Type Foreground Background
Colored object area or logical
page
All portions None
Presentation data objects Refer to the Object Type Identifiers registry in the Mixed Object Document Content
Architecture Reference.
Merging Presentation Spaces
All presentation spaces are merged with previous data in the order in which the IPDS commands that define
these presentation spaces appear in the data stream. A special merging is done in the following situations:
• A special merging occurs when using a preprinted form overlay (PFO). In this case, the printer treats the [IPDS-3-023]
overlay data as if it were already printed on the paper before any other data is printed. Data within the
medium presentation space is effectively placed on top of the PFO data, but when non-PFO data specifies
color of media or when the device produces “white” (CMYK = X'00000000' for a printer, RGB = X'FFFFFF' for
an RGB display), PFO data specified at the same location shows through that area. The formblend mixing
rule allows the printer to blend data to achieve a look similar to a real preprinted form.
• A special merging occurs for UP3I Print Data objects; these objects are printed by a pre-processing or post- [IPDS-3-024]
processing device and do not directly mix with the data printed by the main IPDS printer. However, when
object area coloring is used with a UP3I Print Data object, the object area presentation space mixes in the
same fashion as all other object area presentation spaces.
The presentation space merge order for all other presentation data is shown by Figure 14, and is
described as follows:
• Medium presentation space – This is the base IPDS presentation space into which all other presentation [IPDS-3-025]
spaces are merged.
• Medium overlay presentation space – This presentation space is often called the medium overlay's logical [IPDS-3-026]
page, and is merged into the medium presentation space with a keyword in the Load Copy Control
command. All medium overlays are merged into the medium presentation space before any pages or page
overlays are merged. Multiple medium overlay presentation spaces are merged in the order in which their
keywords appear in the LCC command.
• Page presentation space – This presentation space is often called the page's logical page, and is merged [IPDS-3-027]
into the medium presentation space in the order in which the corresponding page appears in the data
stream.
• Page overlay presentation space – This presentation space is often called the page overlay's logical page, [IPDS-3-028]
and is merged into the medium presentation space in the order in which the Include Overlay command
occurs in the data stream. The containing page's origin is used for positioning the page overlay, and portions
of the page overlay presentation space can extend outside of the page presentation space.
Page overlays can also be included within an overlay; in this case the included overlay's presentation space
is positioned from the origin of the including overlay's presentation space.
• Object area presentation space – This presentation space is provided for each text object, IO Image [IPDS-3-029]
object, graphics object, bar code object, and presentation object-container object, and is merged into the
page or overlay presentation space in the order in which the corresponding data object is included.
• Data object presentation space – This presentation space is provided for each PTOCA, IOCA, GOCA, [IPDS-3-030]
BCOCA, and presentation object-container object, and is merged, using the specified mapping control
option, into the corresponding object area presentation space. [IPDS-3-031]


Figure 14. Merging Presentation Spaces [IPDS-3-032]

General Mixing Rules
When a new presentation space P new is merged into an existing presentation space P existing, four types of
mixing must be considered. Let F existing and Bexisting denote the P existing foreground and background,
respectively; and let F new and Bnew denote the P new foreground and background, respectively. The mixing types
can be characterized as follows:
Mixing Type Description
Bnew on Bexisting Background on background
Bnew on Fexisting Background on foreground
Fnew on Bexisting Foreground on background
Fnew on Fexisting Foreground on foreground
For each type of mixing, the resultant color is determined by the mixing rule that is specified. The following
mixing rules are defined for presentation space mixing:
Mixing Rule Definition
Overpaint When part of P new overpaints part of P existing, the intersection is assigned the color
attribute of P new. This is also referred to as opaque mixing or knockout mixing.
Underpaint When part of P new underpaints part of P existing, the intersection keeps the color attribute of
Pexisting. This is also referred to as transparent mixing or leave alone mixing.
When multiple presentation spaces are merged, the background and foreground of all of the presentation
spaces mix in the order they are found in the data stream. The resultant foreground is the union of all
presentation space foregrounds; that is, once an area is defined to be foreground, it remains foreground even if
its color attribute is changed due to an underpaint mixing rule. The resultant background is everything else.
The color of the resultant foreground and background is determined by the IPDS default mixing rule.
Formblend Mixing Rule
Preprinted Form Overlays (PFOs) are designed to enable proper simulation of preprinted forms, particularly
their appearance when color data is presented on the form. This requires a special mixing rule, called
formblend, that is the mixing rule used when page data and other overlay data is merged with the PFO data.
When a PFO is invoked with the LCC command, the PFO is merged with all data on a sheet side. When the
PFO is invoked with the Include Overlay command, the PFO is merged after the End Page command has been
received and syntax checked; the PFO is merged before returning to home state and updating page and copy
counters.
Before a PFO can be merged with other presentation data, all objects within the PFO are merged with each
other using the default IPDS mixing rule.
The formblend mixing rule is defined as follows:
Formblend This mixing rule is only used when a preprinted form overlay (PFO) is merged as presentation
space P
PFO with other presentation data (presentation space P data). The intersection of P PFO
and Pdata is assigned the following color attribute:
• Wherever the color attribute of P PFO is either color of medium, or “white” (CMYK = [IPDS-3-033]
X'00000000' for a printer, RGB = X'FFFFFF' for an RGB display), the intersection is
assigned the color attribute of P
data. Likewise, wherever the color attribute of P data is either
color of medium, or “white” (CMYK = X'00000000' for a printer, RGB = X'FFFFFF' for an
RGB display), the intersection is assigned the color attribute of P
PFO.
• With other overlapping color values, the intersection assumes a new color attribute that is [IPDS-3-034]
generated in a device-specific manner to simulate how the P data color attribute would mix
onto a preprinted form that has the color attribute of P PFO. In general, this mixing is a [IPDS-3-035]


blending of the color attributes of P data and PPFO that is determined by the two color
attributes and by the print media and the print technology.
Implementation note: Refer to the “Preprinted Form Overlay (PFO) Mixing” description in
the MO:DCA Reference for more information about formblend mixing.
IPDS Default Mixing Rule
When a new presentation space P new is merged into an existing presentation space P existing, the background of
Pnew underpaints the background and foreground of P existing, and the foreground of P new overpaints the
background and foreground of P existing.
This default mixing rule can be characterized as follows:
Table 6. Default Mixing Rules
Mixing Type Default Mixing Rule
Bnew on Bexisting Underpaint
Bnew on Fexisting Underpaint
Fnew on Bexisting Overpaint
Fnew on Fexisting Overpaint
Notes:
1. Color of medium (X'FF08') is a valid color in all respects and the mixing rules apply to this color. Thus, [IPDS-3-036]
foreground pels in color of medium cover up pels of any other color that are underneath.
2. Some printers might print different data types or different elements within the same data type at different [IPDS-3-037]
resolutions. For example, text characters might be printed at a different resolution than text rules or
graphics. Pels at different physical resolutions cannot actually mix as described above, although there
might be physical mixing on the medium.
3. Some printers simulate unsupported colors using device-dependent color simulation. Pels whose color is [IPDS-3-038]
simulated in such a manner are subject to the same mixing rules as pels whose color is not simulated. [IPDS-3-039]


Logical Page and Object Area Coloring
Some printers are able to shade, tint, or color a logical page or object area before any presentation data is
presented within the area. The effect depends on the color capabilities of the printer. Figure 15 shows an
example with several object areas that contain various shades of black.
Figure 15. Examples of Shaded Areas [IPDS-3-040]



Specifying Color
Color is specified within an IPDS data stream in many ways that are summarized in the following tables.
Table 7. Area Coloring. (used to color the background of an object area or logical page)
Command Function
STM
Property
Pair IPDS Control
LPD Logical page area coloring X'6201' Color Specification (X'4E') triplet
Presentation Space Reset Mixing (X'70') triplet
WBCC-BCOC
WGC-GOC
WIC2-IOC
WOCC-OCOC
WTC-TOC
Object area coloring X'6201' Color Specification (X'4E') triplet
Presentation Space Reset Mixing (X'70') triplet
IDO-DOOC Override for object area
presentation space mixing
X'6201' Presentation Space Reset Mixing (X'70') triplet
Table 8. Object Coloring. (used to color the foreground of a presentation object)
Command Function
STM
Property
Pair IPDS Control
Text
LPD Initial text-major text color Basic
PTOCA
support
Color parameter in LPD
WTC-TDD Initial text-object text color X'2001' Initial text condition in TDD
WT-PTOCA
controls
Text color Basic
PTOCA
support
None
IO Image
WIC2-IDD Bilevel IO-Image color Basic
IOCA
support
X'F6' IOCA self-defining field
WIC2-IDD Extended bilevel IO-Image color X'4401' X'F4' IOCA self-defining field
IDO-DODD Override for bilevel IO-Image
color
X'1201' Color Specification (X'4E') triplet
RPO Override for bilevel IO-Image
color
X'4403' Color Specification (X'4E') triplet
WI2-IOCA controls Full-color image or tile, or bilevel
tile using Tile Set Color
Basic
IOCA
support
None
WI2-IOCA controls Extended Tile Set Color X'4402' None [IPDS-3-041]


Table 8 Object Coloring (cont'd.)
Command Function
STM
Property
Pair IPDS Control
Graphics
WGC-GDD Default graphics color Basic
GOCA
support
plus
X'4109'
GOCA self-describing instruction in GDD
WG-GOCA
controls
Graphics color Basic
GOCA
support
None
Bar Code
WBCC-BCDD Bar code color Basic
BCOCA
support
Color parameter in BCDD
WBCC-BCDD Extended bar code color X'4400' Color Specification (X'4E') triplet
Object Containers
WOCC-OCDD Default bilevel and grayscale
image color
X'5801' Color Specification (X'4E') triplet
IDO-DODD Override for bilevel and grayscale
image color
X'5801' Color Specification (X'4E') triplet
RPO Override for bilevel and grayscale
image color
X'5801' Color Specification (X'4E') triplet
WOC-object
controls
Object color Basic
object-
container
support
None


Color Management
Basic Concepts
When there is more than one color-capable device in a system it is important to have color-management
capability so that colors appear the same no matter which device is used to print or display colored data.
Colors can be managed by either specifying device-independent color values using a universally known color
standard (such as CIELAB) or by providing a management resource along with device-dependent color data
that allows the receiving device to transform color values into its own device-dependent color space.
In addition to producing accurate colors, color management allows tuning controls so that colors can be
darkened or lightened, colors can be adjusted to look like they were produced on a different device, and colors
can be calibrated to adjust for different paper, toner, or other device characteristics. Also, when printing color
data on single-color printers, color management can be used to produce pleasing grayscales and provide
grayscale-tuning capability.
The controls for managing color are contained within a Color Management Resource (CMR) whose goal is to
provide:
• Consistent output across different devices [IPDS-3-042]
• Accurate output, to the best of the device's capability, with a wide variety of inputs [IPDS-3-043]
• Consistent output across different types of data created by a variety of applications or devices [IPDS-3-044]
• Flexible controls that enable the tuning of output to exact specifications [IPDS-3-045]
Color Management Resources are defined within the Color Management Object Content Architecture
Reference.
In an IPDS data stream, Color Management Resources (CMRs) are handled as non-presentation, object-
container resources. CMRs can be downloaded in home state using a WOCC/WOC/END command sequence;
however, audit, instruction, and ICC DeviceLink CMRs must also be invoked by other IPDS commands before
they can be used. CMRs can also be printer resident or captured. In this case, they are activated by the
Activate Resource (AR) command; but, just like the download case, another IPDS command is required to
invoke an audit, instruction, or ICC DeviceLink CMR. An object OID is used to identify a resident (or captured)
resource.
When a CMR is activated, by either an AR command or a download command sequence, a processing mode
is specified (in a Color Management Resource Descriptor (X'91') triplet) and an OID can be specified to
uniquely identify the CMR object. The OIDs specified with audit and instruction color-conversion CMRs are
later used to locate an appropriate link color-conversion (subset “LK”) CMR.
CMRs are deactivated by the Deactivate Data Object Resource (DDOR) or XOH Erase Residual Print Data
command. When a CMR is deactivated, all invocations of that CMR are also removed.
The printer also provides default audit and instruction CMRs for most CMR types and for many color spaces.
Default CMRs provide a default level of color management when no host-invoked CMR of a needed type is
provided. Default CMRs do not require activation. The device-default CMRs defined in the Color Management
Object Content Architecture Reference must be supported; exception ID X'025E..00' exists if a CMR is needed
to process specific color data, but is unavailable because the printer does not have a default CMR for that color
data and a host-invoked CMR was also not supplied.
CMR-Usage Hierarchy
A CMR-enabled IPDS printer uses the following CMR-usage hierarchy to determine which CMRs should be
used when processing print data. All print data has color information associated with it (often in an implicit
manner); when an explicit color is not specified, the data is assigned the printer-default color (normally black).
CMRs can be supported by single-color printers, highlight color printers, and full-process color printers. It is
important to know which color space is being used for the current print data before the hierarchy is searched [IPDS-3-046]


for the appropriate CMRs. Refer to the Color Management Object Content Architecture Reference for a
definition of the relationship between each color space and each CMR type.
When a highlight color space is used with indexed color values (X'0100'–X'FFFF'), the printer uses an indexed
CMR for color-conversion purposes (instead of using color-conversion CMRs). Color management is not used
for highlight color values in the range X'0000'–X'00FF'. For all other color spaces, color-conversion CMRs are
used for converting specified colors to device colors. Tone-transfer-curve CMRs and halftone CMRs can be
used with all color spaces. Refer to Figure 17 for a full description of the CMR-selection process for
color conversion.
Before color data can be presented, the printer must select an appropriate set of color-management controls
using the CMR-usage hierarchy (refer to “CMR-Usage Hierarchy Summary” for a summary of what
is selected). In general, the set includes exactly one audit CMR and one instruction CMR for each of the
halftone, tone-transfer-curve, and color-conversion types (for a total of six CMRs) and exactly one rendering
intent. A slightly different set is selected when an ICC DeviceLink CMR is used or when a highlight color space
is used with indexed color values (in these cases, only one color-conversion CMR is selected). The printer
might also use a link color-conversion (subset “LK”) CMR to improve performance. For example, when
processing a presentation data object, if an instruction color-conversion CMR is specified for the data object, it
is used; otherwise, the printer looks at the next level in the hierarchy for an instruction CMR of that type (the
same search is done for an audit CMR). The printer searches each level of the hierarchy until an appropriate
pair of CMRs are found for each CMR type or until the last level has been reached; when an appropriate CMR
is found at a particular level, the printer does not search any farther in the hierarchy. If the data stream did not
invoke an instruction CMR or audit CMR for each appropriate CMR type, the printer uses a default CMR (the
last level of the hierarchy).
There can be multiple CMRs invoked at each level in the hierarchy and the printer keeps track of the order in
which the CMRs are invoked. When searching the hierarchy and more than one CMR is found at a particular
hierarchy level that meets the selection criteria, the printer uses the most recently invoked CMR. For example,
if three CMRs are invoked at the home-state level (CMR1, CMR2, and CMR3 in that order) and both CMR1
and CMR3 are appropriate for use with RGB data, CMR3 is selected. The printer searches, at each hierarchy
level, from last invoked to first invoked.
Figure 16. CMR-Usage Hierarchy
Data-object level
Page-overlay levelMedium-overlay level
Page level
Home-state level
Default level
The start of the hierarchy depends on where the data is
specified in the data stream;
start at 1
1
11
1


The hierarchy levels are defined as follows.
Data-Object-Level CMRs
Data-object-level CMRs are invoked with an Invoke CMR (X'92') triplet on the command that begins a data
object; for example, WTC for a text object, WIC2 for an IOCA image object, WGC for a graphics object, WBCC
for a bar code object, and WOCC for an object-container object. Note that CMRs are not directly invocable for
an IM-Image or for a page segment; but IM-Image data and page-segment data can be processed with CMRs
from another level of the hierarchy.
Note that while CMRs can be invoked directly for page-state and overlay-state presentation objects, CMR
invocations are not stored with IOCA or object-container objects that are downloaded in home state as
resources. CMRs for IOCA and object-container resources are invoked by specifying an Invoke CMR (X'92')
triplet on the Include Data Object (IDO) command.
When preRIPping presentation data objects, data-object-level CMRs can be invoked with an Invoke CMR
(X'92') triplet on the RPO command.
Data-object-level CMRs can also be invoked with an Invoke Tertiary Resource (X'A2') triplet on a WBCC
command for a QR Code with Image bar code. Such invocations are for the image objects printed in
conjunction with the bar code, not for the bar code itself. Note, however, that any data-object-level CMRs
invoked for the bar code, using the Invoke CMR (X'92') triplet on the WBCC, are also invoked at the data-
object level for all image objects printed in conjunction with the bar code. The data-object-level CMRs invoked
via the Invoke Tertiary Resource (X'A2') triplet take precedence over those invoked via the Invoke CMR (X'92')
triplet.
The scope of a data-object-level CMR is the data object; when an End command is processed the scope ends
for all data-object-level CMRs.
Notes:
1. Some presentation data objects contain internal color management information; internal color [IPDS-3-047]
management information can also be supplied in a secondary resource for a presentation data object. For
internal ICC-profile-like information, an object-level audit CMR can override this internal information, but if
an object-level audit CMR is not selected for this object, the internal information is used in place of an audit
CMR and a link CMR is not necessarily used with this object. In the same way, for internal rendering intent
information, an object-level Rendering Intent (X'95') triplet can override the internal rendering intent
information, but if an object-level Rendering Intent (X'95') triplet is not selected for this object, the internal
rendering intent information is used. All internal instruction-like color management information is ignored.
Refer to the Color Management Object Content Architecture Reference for processing rules.
It is important to realize that such internal color management information can end the hierarchy search for
an audit CMR or for a rendering intent. For example, a Rendering Intent (X'95') triplet specified at the page
level will never be used for presentation data objects on the page that happen to contain their own internal
rendering intent information. Thus, if there is a desire to use the same audit CMR or rendering intent for an
entire page, that audit CMR or rendering intent must be specified not only at the page level, but also at the
data-object level for any object that might contain internal color management information.
2. For EPS and PDF page objects only, when there is no object-level audit color-conversion CMR invoked, [IPDS-3-048]
the printer uses a resident color profile (if an appropriate one has been established as a secondary
resource for the EPS or PDF page object by a DORE or DORE2
command). Currently, resident color
profiles are defined only for CMYK data. For EPS and PDF page objects, internal color management
information is used only when no applicable resident color profile and no applicable object-level audit color-
conversion CMR has been selected. Refer to the Color Management Object Content Architecture
Reference for a description of situations in which audit color-conversion CMRs are used. [IPDS-3-049]


For EPS and PDF objects that are printed in conjunction with a QR Code with Image bar code, resident
color profiles are not used; any resident color profiles established by the DORE or DORE2 command as
secondary resources for such EPS or PDF objects are ignored.
To establish a rendering intent (perceptual, media-relative colorimetric, saturation, or ICC-absolute
colorimetric) for the object, a Rendering Intent (X'95') triplet can also be specified on the command that begins
the object (WGC, WIC2, WOCC, or WTC), can be specified on the RPO command when preRIPping the
object, and can be specified on the IDO command when including the object. For data-object-level rendering
intent, image objects to be printed in conjunction with a QR Code with Image bar code use the Rendering
Intent (X'95') triplet, if any, specified on the WIC2 or WOCC when they were downloaded.
The rendering intent
specified within the triplet for this object type is used and the rendering intents for all other object types are
ignored. For presentation object containers, if no data-object level Rendering Intent (X'95') triplet that specifies
the object container rendering intent exists, and if the object contains internal rendering intent information, the
internal rendering intent information is used.
Medium-Overlay-Level CMRs
Medium-overlay-level CMRs are invoked when an LPD command for the overlay is processed. This level of the
hierarchy is used only for medium overlays and preprinted form overlays (invoked with an LCC command) and
does not apply when processing page data. When the overlay is downloaded, overlay-level CMRs are
specified by an Invoke CMR (X'92') triplet on the overlay's Logical Page Descriptor (LPD) command.
When traversing the hierarchy, the next level after the medium-overlay level is the home-state level.
The scope of a medium-overlay-level CMR is the overlay; when the overlay's End Page command is
processed, the scope ends for that overlay.
To establish rendering intents for the medium overlay, a Rendering Intent (X'95') triplet can also be specified on
the LPD command.
Page-Overlay-Level CMRs
Page-overlay-level CMRs are invoked when an LPD command for the overlay is processed. This level of the
hierarchy is used only for page overlays and preprinted form overlays (invoked with an IO command) and does
not apply when processing page data. When the overlay is downloaded, overlay-level CMRs are specified by
an Invoke CMR (X'92') triplet on the overlay's Logical Page Descriptor (LPD) command.
When traversing the hierarchy, the next level after the page-overlay level is the home-state level; CMRs for the
including page (or overlay) are not used. The original page overlay uses the same CMRs regardless of where it
appears in a print file.
The scope of a page-overlay-level CMR is the overlay; when the overlay's End Page command is processed,
the scope ends for that overlay.
To establish rendering intents for the page overlay, a Rendering Intent (X'95') triplet can also be specified on
the LPD command.
Page-Level CMRs
Page-level CMRs are invoked by an Invoke CMR (X'92') triplet on the page's Logical Page Descriptor (LPD)
command.
The scope of a page-level CMR is the page; when the page's End Page command is processed, the scope
ends for that page.
To establish rendering intents for the page, a Rendering Intent (X'95') triplet can also be specified on the LPD
command.


Home-State-Level CMRs
Home-state-level CMRs are invoked or reset by an Invoke CMR (ICMR) command. CMRs invoked with the
ICMR command are used whenever there are no data-object level, page level, or overlay level CMRs in effect.
Home-state-level CMRs remain invoked until they are reset by another ICMR command or until the printer is
reinitialized (returns an IML NACK).
To establish rendering intents for multiple pages, a Rendering Intent (X'95') triplet can be specified on a Set
Presentation Environment (SPE) command.
Default CMRs
Default CMRs are provided by the printer for all CMR types supported by that printer; some of these defaults
are defined in the Color Management Object Content Architecture Reference. Default CMRs provide a default-
level of color management when no host-invoked CMR of a needed type is provided. Default CMRs do not
require activation.
CMR-Usage Hierarchy Processing
Instruction and ICC DeviceLink CMRs can be made media-specific by including media information within the
CMR header. Refer to the Color Management Object Content Architecture Reference for a description of
media-specific CMR processing. If an applicable instruction CMR is not found in the hierarchy, the printer
selects an appropriate printer-default CMR whose media information matches the currently selected media or
if such a match is not found, the printer selects one of the existing printer defaults (and no exception ID is
reported). If an invoked, media-specific instruction or ICC DeviceLink CMR is selected but does not match the
currently selected media, exception ID X'025E..03' exists. The Color Fidelity (X'75') triplet can be used to
control whether printing stops or continues and whether or not this exception is reported.
Instruction halftone and tone-transfer-curve CMRs can also be generic (this is identified in the CMR Version
field in the CMR header). When the printer selects a generic instruction CMR, it substitutes an appropriate
version of a device-specific default CMR. All generic CMRs are defined in the Color Management Object
Content Architecture Reference. Exception ID X'025E..04' exists if a generic CMR is selected, but the printer
does not have an appropriate device-specific CMR to use in place of the generic CMR.
In many cases, a prebuilt link color-conversion CMR (with either a LUT or Identity subset) is available to the
printer that performs the same function as the selected audit color-conversion CMR and instruction color-
conversion CMR, but provides for more efficient processing. Link color-conversion (subset “LK”) CMRs can
either be built by the printer as needed or can be activated as link CMRs; link CMRs of this type do not need to
be invoked. Printers indicate by property pair X'E001' in the Device-Control command-set vector of an STM
reply whether or not host-activated link color-conversion (subset “LK”) CMRs are used
1; link-color-conversion
CMRs should not be downloaded to a printer that does not use them. Printers also indicate by an STM property
pair X'E000' whether or not capture is supported for CMRs. Refer to the Color Management Object Content
Architecture Reference for a description of link-color-conversion CMRs.
Link-color-conversion (subset “LK”) CMRs provide four lookup tables, one for each possible rendering intent.
The Rendering Intent (X'95') triplet allows for rendering-intent selection at each level of the hierarchy. While
searching the hierarchy, when a X'95' triplet is found that specifies a rendering intent for the current type of
presentation object, that rendering intent is used to select the appropriate lookup table in the link-color-
conversion (subset “LK”) CMR (note that the value X'FF' means that a rendering intent has not been specified
and that the hierarchy search should continue). Note that for presentation object containers, an extra level
essentially exists in the hierarchy: if no X'95' triplet is found that specifies the object container rendering intent
at the data-object level of the hierarchy, and if the object contains internal rendering intent information, the
1. Some IPDS printers do not use link color-conversion (subset “LK”) CMRs, but instead use the audit color-conversion and instruction [IPDS-3-050]
color-conversion CMRs directly. In this case, a rendering intent is selected from the hierarchy and is used when processing the audit
and instruction color-conversion CMRs. [IPDS-3-051]


internal rendering intent information is used. If after searching the hierarchy no rendering intent has been
specified, the printer defaults to the rendering intent specified in the instruction color-conversion CMR (that is
the same as the default rendering intent in the selected link-color-conversion CMR). Note that the hierarchy
search for rendering intent and the hierarchy search for CMRs are independent of each other; it is possible that
the rendering intent and the selected CMRs are found on different levels of the hierarchy.
Selecting Color-Conversion CMRs
Each time a color space is used or the presentation of a new object is begun the printer uses the CMR-usage
hierarchy to select an appropriate set of CMRs. For halftone and tone-transfer-curve types, the selection is
relatively simple—exactly one audit CMR and exactly one instruction CMR of each type is selected. In addition,
the audit halftone CMR is ignored, and some IPDS printers also ignore the audit tone-transfer-curve CMR.
However, the selection is more involved for color conversion.
The color space being used is the first thing that determines the selection. If a highlight color space is used
with indexed color values (X'0100'–X'FFFF'), the printer uses an indexed CMR for color-conversion purposes
(instead of using color-conversion CMRs). Color management is not used for highlight color values in the
range X'0000'–X'00FF'. For all other color spaces, color-conversion CMRs are used for converting specified
colors to device colors. Either a single ICC DeviceLink CMR or a pair of color-conversion CMRs (an audit and
an instruction) will be selected. Figure 17 shows how the hierarchy is used to select appropriate color-
conversion CMRs. The selection process is further described in the following text.
Figure 17. Selecting Appropriate Color-Conversion CMRs
Data-Object
Level ICC
DeviceLink
CMR
Inside Data
Object
Page/Overlay
Level
Home-State
Level
Default
Level
Note: ICC DeviceLink Profiles are not embedded within data objects.
Rendering intent is not used to select ICC DeviceLink CMRs.
CMR-Usage Hierarchy for color conversion CMRs
ICC
DeviceLink
CMR
ICC
DeviceLink
CMR
If ICC DeviceLink
is supported, this
path is followed.
If ICC DeviceLink
is not supported, this
path is followed.
Audit CC
CMR
Audit CC
CMR
Audit CC
CMR
ICC Profile
Audit CC
CMR


The printer uses the color space and the following search method to find appropriate color-conversion CMRs:
1. Search at the data-object level [IPDS-3-052]
a. Look for an ICC DeviceLink CMR. If found, stop and use it.
b. Else look for an audit color-conversion CMR. If found, stop, find an instruction color-conversion
CMR by searching all levels. Use the selected pair of CMRs.
2. If an ICC Profile exists within the data object (for example, and ICC profile within a TIFF image), use it [IPDS-3-053]
and find an instruction color-conversion CMR by searching all levels. Note that ICC DeviceLink
Profiles are not embedded within data objects.
3. Else search at the page or overlay level: [IPDS-3-054]
a. Look for an ICC DeviceLink CMR. If found, stop and use it.
b. Else look for an audit color-conversion CMR. If found, stop, find an instruction color-conversion
CMR by searching all levels. Use the selected pair of CMRs.
4. Else search at the home-state level: [IPDS-3-055]
a. Look for an ICC DeviceLink CMR. If found, stop and use it.
b. Else look for an audit color-conversion CMR. If found, stop, find an instruction color-conversion
CMR by searching all levels. Use the selected pair of CMRs.
5. Else use a default audit color-conversion CMR. Find an instruction color-conversion CMR by [IPDS-3-056]
searching all levels. Use the selected pair of CMRs.
CMR media attributes of the ICC DeviceLink CMR must match the media attributes of the device. This
means that the process defined in the CMOCA Reference for “Matching Media Type of CMR with Media
Type of Device” should be followed. If some of the attributes do not match and the hierarchy search
continues, the hierarchy search will continue normally as if the CMR that did not match the currently
loaded media was never found.
When an audit and instruction pair of color-conversion CMRs are selected, the hierarchy is also searched
for a rendering intent. However, rendering intent is not used when an ICC DeviceLink CMR is selected for
use. The ICC DeviceLink represents exactly one rendering intent, that is specified in the header of the ICC
Profile. If an ICC DeviceLink CMR is selected for use during a hierarchical search, it is used regardless of
whether or not the currently active rendering intent matches the rendering intent of the profile.
Pass-Through Audit Color-Conversion CMRs
Color-conversion CMRs can be generated to force a pass through of color values without being subject to color
management. This is done by specifying the character string “pasthru” in the CMRversion field of the CMR
name. CMRs identified in this manner must be color-conversion CMRs or exception ID X'025E..02' exists.
CMYK pass through is particularly useful for text and bar code data; it enables pure black text to be specified
as CMYK = X'00 00 00 FF'.
The Prop4 field in the CMR name should be specified to indicate the color space to be passed through to the
printer. A pass-through CMR contains no data. When a pass-through CMR is found in the hierarchy as an audit
color-conversion CMR to be used for rendering data, if the color space specified in Prop4 matches the color
space of the presentation device the color values in the data will be rendered without going through a color
conversion. If the color space in the pass-through CMR is not the same as the device color space or if Prop4 is
not specified, the CMR is ignored and not used for any color conversions. A pass-through color-conversion
CMR is treated like other audit color-conversion CMRs in terms of selecting an audit color-conversion CMR
from the hierarchy.


When used with IOCA images that use the nColor color space, if pass-through is desired, the nColor Names
parameter should list the n color names and the Prop4 field should be set to one of the “CLR” values; for
example, for 7 colors, Prop4 is set to “7CLR” and for 12 colors, it is set to “CCLR”.
Printer support for pass-through audit color-conversion CMRs is indicated by the presence of property pair
X'E102' in the Device-Control command-set vector of an STM reply.
CMR-Usage Hierarchy Summary
Before color data can be presented, the printer must select an appropriate set of color-management controls
using the CMR-usage hierarchy. An appropriate set must be selected each time a color space is used or the
presentation of a new object is begun. The required color-management controls consist of the following:
• Color conversion: [IPDS-3-057]
For indexed color values (X'0100'–X'FFFF'),
the printer selects an indexed CMR. If the printer is not capable of handling the color value specified
in the indexed CMR, the substitution LAB value is used. In this case, the LAB value is converted into
a color presentable by the printer; this is not an exception condition.
For all other color values that use CMRs,
the color-conversion hierarchy shown in Figure 17 is used to select one of the following:
either One ICC DeviceLink CMR
or The following set of controls:
– One audit color-conversion CMR that identifies the input color space; note that audit
color-conversion CMRs do not apply to standard OCA color values.
– One instruction color-conversion CMR that identifies the output color space.
– A rendering intent.
– A link color-conversion CMR can be used.
• Halftoning: [IPDS-3-058]
– One audit halftone CMR to identify halftoning that has been done to the data (because audit halftone
CMRs are ignored, this is the device default, that indicates that halftoning should not be undone)
– One instruction halftone CMR used to halftone the output colored data
• Color calibration: [IPDS-3-059]
– One audit tone-transfer-curve CMR to identify calibration that was done to the input color before halftoning
(this is normally the device default, that indicates that no color calibration has been applied)
– One instruction tone-transfer-curve CMR to identify calibration to be done to the output color before
halftoning


Color Management Compliance
IPDS printers that support color management must return appropriate information in STM and XOH-OPC
replies to indicate the required and optional functions supported.
Required Color-Management Function
Property pair X'F205' is the basic property pair to identify support for color management and indicates that the
printer complies with the mandatory items defined in the compliance appendix of the Color Management
Object Content Architecture Reference (for example, support for CC CMRs and generic HT and TTC CMRs is
mandatory). This property pair also indicates support for the following CMR-triplets:
• Color Management Resource Descriptor (X'91') triplet [IPDS-3-060]
• Invoke CMR (X'92') triplet [IPDS-3-061]
• Rendering Intent (X'95') triplet [IPDS-3-062]
• Fully Qualified Name (X'02') triplet (with FQN Type X'41') [IPDS-3-063]
In addition, printers that support Color Management Resources must return the following STM and XOH-OPC
information:
• Invoke CMR command support (property pair X'706B') [IPDS-3-064]
• Set Presentation Environment-command support (property pair X'7008') [IPDS-3-065]
• Device Appearance (X'97') triplet support (property pair X'F206'); within the triplet, the printer-default [IPDS-3-066]
appearance (X'0000') must be supported
• Data-object-resource support (property pair X'1201') [IPDS-3-067]
• CMR object-type OID (in the XOH-OPC reply) [IPDS-3-068]
• XOH-OPC Product Identifier self-defining field with parameter ID X'0001' [IPDS-3-069]
Note: It is strongly recommended that IPDS printers also support the Presentation Fidelity Control (PFC)
command and the following triplets:
• Color Fidelity (X'75') triplet [IPDS-3-070]
• CMR Tag Fidelity (X'96') triplet [IPDS-3-071]
Optional Color-Management Function
The following property pairs indicate support for optional function:
• Support for CMR capture (property pair X'E000') [IPDS-3-072]
• Host-activated CMR support: [IPDS-3-073]
– Link color-conversion (subset “LK”) CMRs (property pair X'E001')
– Non-generic halftone CMRs (property pair X'E002')
– Non-generic tone-transfer-curve CMRs (property pair X'E003')
– Indexed CMRs (property pair X'E004')
– ICC DeviceLink (subset “DL”) CMRs supported (property pair X'E006')
• CMRs can be reliably applied to all EPS/PDF objects (property pair X'E100') [IPDS-3-074]
• Pass-through audit color-conversion CMRs (property pair X'E102') [IPDS-3-075]
• Extended bar code color support (property pair X'4400') [IPDS-3-076]
• Extended IOCA bilevel color support (property pair X'4401') [IPDS-3-077]
• Extended IOCA tile-set-color support (property pair X'4402') [IPDS-3-078]
• Bilevel IO-Image color support on the RPO command (property pair X'4403') [IPDS-3-079]
• Bilevel and grayscale image color support for object containers (property pair X'5801') [IPDS-3-080]
• IPDS Trace support (property pair X'90F2') [IPDS-3-081]
• Long-ACK support (property pair X'F003') [IPDS-3-082]


Color Resource Relationships
In addition to CMRs, some printers support color mapping tables and resident color profiles. Most IPDS
printers also support the standard OCA color-value table.
Color mapping tables
are used to map color values specified in a source color space to color values specified in a
target color space. This allows colors specified in print data to be mapped (at print time) to
colors more suitable to the specific printer and allows, for example, a highlight color value or a
standard OCA color value (specified in the print data) to be mapped to a target color space
(such as RGB, CMYK, or CIELAB). Refer to the Mixed Object Document Content Architecture
Reference for a description of the color mapping table resource.
When color data is encountered, the printer first applies the mapping from the current color
mapping table (if any). The CMR-usage hierarchy is then used to select appropriate CMRs
based on the target color space from the Color Mapping Table.
Resident color profiles
are used to identify a specific predefined type of CMYK data (such as CMYK SWOP and
CMYK Euroscale). Device-specific CMYK data can also be identified using a color
management resource (CMR). If an audit, object-level, color-conversion CMR is invoked for
an object, it takes precedence over the resident color profile.
Resident color profiles are only used with certain presentation objects, such as EPS and PDF
page objects. Refer to Table 51
for a complete list of presentation data objects
that use resident color profiles. Note that resident color profiles are not used with presentation
objects that are printed in conjunction with a QR Code with Image bar code.
The standard OCA color-value table
describes named colors that are inherently device-dependent; named colors should not be
used when an exact color is required. In the standard OCA color-value table, an RGB value is
provided for each named color. It is recommended that IPDS printers interpret these RGB
values as SMPTE-C values and map the SMPTE-C RGB values to the printer's CMYK.
Note that audit color-conversion CMRs do not apply to named colors. [IPDS-3-083]


Ordered Data
Some printers are able to print with improved performance if the data on a page is ordered sequentially in a
manner consistent with the natural movements of the print mechanism.
Ordered page A page that does not contain any page segments or overlays, and in which all text data as well
as all image, graphics, and bar code objects are ordered such that physical pel locations on
the physical media are accessed by the printer in a sequential left-to-right and top-to-bottom
manner, where these directions are relative to the top edge of the sheet (see Figure 18 through Figure 23). Once a physical pel location has been accessed by
the printer, the page data does not require the printer to re-access that same physical pel
location.
When a page is ordered, there is no requirement to overpaint any pels on that page; therefore, the IPDS mixing
rules are not needed for an ordered page.
If a printer that supports ordered data receives a page marked as ordered, but the page data violates the
ordered-page definition, exception ID X'0205..02' exists.
If a printer that supports ordered data receives a page marked as ordered, but the page contains a data type
that the printer does not support in an ordered page, the printer processes that data type as if it was in an
unordered page. However, since the data type was in an ordered page, it must not require a print mechanism
movement that is in violation of the ordered page definition. If it does, exception ID X'0205..02' exists.
If ordered data is indicated for an overlay, it may be ignored. It also may be ignored if medium overlays or
multiple pages per side are specified.
Fixed Medium Information
Some printers are able to present information on sheets of physical media that is independent of and not
provided through the data stream. Data of this type, called fixed medium information, does not mix with the
data provided by the data stream and is presented on a physical medium either before or after the text, image,
graphics, bar code, and object container data provided within the data stream.
Fixed Medium Information can be used to create preprinted forms, or other types of printing, such as colored
logos or letterheads, that cannot be created conveniently within the data stream. This type of function can be
provided by a printer, or by a pre-processing device or a post-processing device that is attached to the printer.
Fonts
The IPDS architecture provides the ability to manage and use a wide variety of font resources. These font
resources can be classified into two major categories: coded font components and data-object-font
components. Coded fonts and their component parts are defined within the Font Object Content Architecture
(FOCA); data-object-font components are defined elsewhere. Currently, TrueType/OpenType fonts and
TrueType/OpenType collections are the only font technologies supported with data-object fonts. TrueType and
OpenType font objects are defined within Technical Specifications maintained by the Microsoft
® and Apple
Corporations.
Fonts are used to present character data found in text, graphics, and bar code objects. Within each of these
presentation objects, a font is identified before the character data begins, and the font style, size, technology,
and characteristics can be changed as needed. For example, a page might contain a paragraph of EBCDIC
data presented with a Times New Roman coded font and also contain an example containing UNICODE data
presented with an Arial TrueType font.
To see which types of font resources are supported by your printer, refer to your printer documentation. [IPDS-3-084]


Coded-Font Components
The component parts of a coded font can be downloaded to local printer storage, or they can be resident in
printer storage. Downloaded-font components are also called loaded-font components and are described in
Chapter 14, “Loaded-Font Command Set”. To activate a coded font, these component parts must
be combined in one of the following configurations:
• LF1-type coded font, that consists of a fully described font plus font indexes, or that consists of several fully [IPDS-3-085]
described font sections plus font indexes for each section.
• LF2-type coded font, that consists of a symbol set. [IPDS-3-086]
• LF3-type coded font, that consists of a code page plus a font character set. In addition, specific activation [IPDS-3-087]
parameters, such as font inline sequence and desired size must be supplied when activating an LF3-type
coded font.
Data-Object-Font Components
TrueType/OpenType fonts and TrueType/OpenType collections are the font technologies supported as
components of a data-object font. These data-object-font components can be used with an internal TrueType
encoding or can be used with a code page.
A component of a data-object font can be downloaded to an IPDS printer as an object container or can be
resident in the printer as a data-object-font component. Object containers and data-object-font components are
further described in Chapter 10, “Object Container Command Set”.
The TrueType font technology is further described in the following documents available from the Microsoft and
Apple web sites:
TrueType Font Files Technical Specification (Microsoft Corporation)
TrueType Reference Manual (Apple Computer, Inc.)
The OpenType font format is an extension of the TrueType font format that allows better support for
international character sets and broader multi-platform support. The OpenType font format, that was
developed jointly by the Adobe and Microsoft Corporations, is further described in the following document
available from the Microsoft web site:
OpenType Specification (Microsoft Corporation)
TrueType/OpenType fonts can be used with EBCDIC or ASCII text by specifying a code page to be used with
the font. These fonts provide even more benefits when used with Unicode text; refer to “Unicode Support” for a description of IPDS Unicode support. Support for code pages with TrueType/OpenType fonts is
indicated in the Loaded-Font command set vector of an STM reply with one (or more) of the following Loaded-
Font subset IDs: LF3 or LF4. [IPDS-3-088]


Expressing Linear Measurements
In general usage, linear measurements are expressed as a specific number followed by a unit called the
measurement base. The measurement base is typically a well known unit such as an inch or a centimeter. For
example, in the measurement 12 inches, the measurement base is inches; in the measurement 12
centimeters, the measurement base is centimeters. Since we know the length of one inch or one centimeter, it
is easy to measure 12 of these units.
In the IPDS architecture, linear measurements are expressed as numbers called logical units (L-units). When a
number is expressed in terms of L-units, an appropriate measurement base must be used to interpret the value
of the number. The measurement base is separately supplied in a control command. For example, the
measurement base used for specifying text placement on a page is provided in a Logical Page Descriptor
command; the actual text measurements, such as character increment or A-space, are provided in the Load
Font Index command.
Measurement bases used within IPDS data streams are expressed using a unit base field and a units per unit
base field:
Unit base A one-byte code that represents the length of the measurement base. A value of X'00'
specifies that the length of the measurement base is ten inches. A value of X'01' specifies that
the length of the measurement base is ten centimeters. A value of X'02' specifies that the
length of the measurement base is relative to yet another value.
Units per
unit base
A two-byte field that contains the number of units in the measurement base. The previous
general-usage examples had a unit base of one inch or one centimeter and a units per unit
base of one. In most cases, the units per unit base can be any value between X'0001' and
X'7FFF', but each printer must at least support X'3840' (14,400) units per ten inches. Most
IPDS printers also support X'0960' (2400) units per ten inches.
For example, within an IPDS command, the X and Y extents of a logical page might be expressed as X'07F8'
L-units in the X-direction and X'0A50' L-units in the Y-direction. For a unit base of X'00' (ten inches) with 2400
units per unit base, this describes an 8 1/2 inch by 11 inch logical page.
Units of measure is the length of the measurement base, specified by the unit base field, divided by the value
of units per unit base. For example, the units of measure for a graphics object area might be expressed as 1/
240 of an inch; there are 240 units in one inch. Resolution is the reciprocal of units of measure. For example, [IPDS-3-089]
the resolution of the graphics object area would be expressed as 240 units per inch. The term L-unit is
sometimes used as a synonym for unit of measure. [IPDS-3-090]


Coordinate Systems
The IPDS architecture uses orthogonal coordinate systems to define any point on a presentation space.
Distances within these coordinate systems are measured in L-units, rather than in physical pels.
Each presentation space has a coordinate system. Units of measure can be selected for each coordinate
system to enable the location of distinct points and the measurement of distances within these coordinate
systems. The Xm,Ym coordinate system is the medium presentation space coordinate system. The Xp,Yp
coordinate system and the I,B coordinate systems are the logical page presentation space coordinate
systems. The X
t,Yt coordinate system is the text presentation space coordinate system. The Xg,Yg coordinate
system is the graphics presentation space coordinate system. The Xio,Yio coordinate system is the IO-Image
presentation space coordinate system. The Xbc,Ybc coordinate system is the bar code presentation space
coordinate system. The Xoc,Yoc coordinate system is the object container presentation space coordinate
system.
In addition, the object areas into which text, graphics, IO-Image, bar code, and presentation-form object
container presentation spaces are mapped are measured using the Xoa,Yoa coordinate system.
Hereafter, the coordinate pair (X,Y) will be used to denote a generic IPDS coordinate pair where no further
qualification is required or appropriate. The term X coordinate denotes the first coordinate in the pair and can
represent a specific IPDS coordinate such as X
m, Xp, Xt, Xg, Xio, Xbc, Xoc, Xoa, or I. The term Y coordinate
denotes the second coordinate in the pair and can represent a specific IPDS coordinate such as Ym, Yp, Yt, Yg,
Yio, Ybc, Yoc, Yoa, or B.
In all IPDS coordinate systems except the Xg,Yg coordinate system, the positive Y-coordinate axis is rotated 90
degrees clockwise from the positive X-coordinate axis. In the Xg,Yg coordinate system, the positive Yg-
coordinate axis is rotated 90 degrees counterclockwise from the positive Xg-coordinate axis.
Xm,Ym Coordinate System (Medium)
The Xm,Ym coordinate system is the medium presentation space coordinate system. The origin of this system
(Xm=0, Ym=0) can be set by an IPDS command, XOH Set Media Origin, to any of the four corners of the
medium presentation space. If this command is not sent to a printer or if the printer does not support the
command, the origin is located at the top-left corner of the medium presentation space, where the viewpoint is
at the center of the physical medium. This is called the printer default media origin. In this case, the X
m axis
corresponds (is parallel) to the top edge of the sheet and positive Xm values begin at the origin and increase
from left to right. The Ym axis corresponds to the left edge of the sheet and positive Ym values begin at the
origin and increase from top to bottom.
Figure 18 through Figure 23 show the Xm,Ym coordinate system for the various
combinations of physical media.
For printers using continuous-forms media that support the XOH-SMO command, the top edge of the sheet is
the short side whose left corner is closest to the leading edge of the physical medium as it moves through the
printer. The case where both sides of the physical medium are of equal length is treated the same as the case
where the sides are of unequal length and the wide side corresponds to the leading edge. [IPDS-3-091]


Figure 18. Xm,Ym Coordinate System: Recommended Default Media Origins
Continuous-Forms Printer that Supports XOH-SMO
Recommended Cut-Sheet Printer Media Origins
Viewpoint
Default Origin
(X = 0, Y = 0)m m
Default Origin
(X = 0, Y = 0)
m m
Default Origin
(X = 0, Y = 0)m m
Default Origin
(X = 0, Y = 0)m m
Top Edge
Viewpoint
Top Edge
Top Edge
Top Edge
Viewpoint
Viewpoint
Leading Edge
Leading EdgeLeading Edge
Leading Edge
+X Directionm+Y Directionm
+Y Directionm
+X Directionm
+X Directionm
+X Directionm
+Y Directionm
+Y Directionm
Note: For a continuous-forms printer that does not support XOH-SMO,
the printer defines the top edge of the physical media.
For printers using continuous-forms media that do not support the XOH-SMO command, the printer defines the
top edge of the sheet.
For printers using cut-sheet media, the top edge of the sheet is a short side as defined by the printer. This short
side can be the one whose left corner is closest to either the leading or trailing edge of the physical medium as
it moves through the printer. Printers using cut-sheet media should use the left corner closest to the leading
edge of the physical medium as it moves through the printer. [IPDS-3-092]


Figure 19. Xm,Ym Coordinate System - Other Allowed Default Media Origins
Leading Edge
Leading Edge
Other Allowed Cut-Sheet Default Media Origins
Viewpoint
Viewpoint
Top Edge
Default Origin
(X =0, Y =0)m m
Default Origin
(X =0, Y =0)m m
Top Edge
+X Directionm
+X Directionm
+Y D irectionm
+Y Directionm
For printers using envelope media, the top edge of the sheet is in relationship to the edge to which the flap is
attached. Figure 20 illustrates the top edge of the sheet for envelopes. For the envelope type that
has equal length and width dimensions, the top edge of the sheet is the same as the envelope type with the
flap on the long edge.
For printers using computer output on microfilm (COM), a sheet is a data frame and the top edge of the sheet
is a short side of a frame. The arrangement of the frames on the microfilm is defined by the printer. Figure 21, Figure 22, and Figure 23 illustrates the top edge of the sheet for COM for
various frame arrangements. The case where both sides of the frame are of equal length is treated the same
as the case where the wide side is parallel to the title edge.
The width and length of the medium presentation space is reported by the printer in the XOH-OPC command
reply. The medium presentation space width does not necessarily correspond to the Xm extent.
For a printer using cut-sheet media, the medium presentation space width is parallel to the top edge of the
sheet. For the default media origin, the width corresponds to the Xm extent.
For a printer using continuous-forms media, the medium presentation space width is parallel to the leading
edge of the physical media as it moves through the printer and does not include the width of the carrier strips.
Thus, when the top edge is perpendicular to the leading edge, the medium presentation space width
corresponds to the Y
m extent (for the default media origin).
Envelopes and computer output on microfilm (COM) could be either cut-sheet or continuous-forms media. For
COM, the width and length of the medium presentation space is the width and length prior to reduction. For
105 mm microfilm and CINE representation on 16 mm microfilm, the title edge is equivalent to the leading edge [IPDS-3-093]
for the purposes of determining which side of the medium presentation space corresponds to the width. For
COMIC representation on 16 mm microfilm, the width of the medium presentation space corresponds to the
edge that is perpendicular to the title edge. The XOH-OPC reply indicates whether cut-sheet or continuous-
forms media is being used.
For a description of the top edge of the sheet for the various types of sheets, refer to Figure 18
through Figure 23.
The location and size of the physical printable area with respect to the medium presentation space is reported
by the printer in the XOH-OPC command reply. The location is specified as an offset from the X
m,Ym
coordinate system origin and the size is specified in Xm,Ym coordinates. [IPDS-3-094]


The XOH-SMO command is used to move the origin of the Xm,Ym coordinate system to any one of the four
corners of the medium presentation space. When this is done, the physical printable area size reported in the
XOH-OPC command reply can change.
Figure 20. Xm,Ym Coordinate System for Envelopes
Media Origin for Envelope Media
Top Edge
Front Side
Default Origin
(x = 0, y = 0)m m
+X Directionm
+Y D irectionm
+X Direction
m
+Y Directionm
+Y Directionm
+X Directionm
Top Edge
Top Edge
Front Side
Default Origin
(x = 0, y = 0)m m
Back Side
Default Origin
(x = 0 , y = 0)m m
+X Direction
m
+Y Directionm
Top Edge
Back Side
Default Origin
(x = 0, y = 0)m m


Figure 21. Default Media Origin for Computer Output on 105 mm Microfilm (Shown Cut into Microfiche)
Title
Top
Default Origin
(X =0, Y =0)m m
Default Origin
(X =0, Y =0)
m m Xm
Xm
Ym
Ym
Top
Title
Figure 22. Default Media Origin for Computer Output on 16 mm Microfilm (CINE Representation)
Xm
Ym
Top
Le
ading
Titling
Top
Xm
Ym
LeadingTitling
Default Origin
(X =0, Y =0)
m m
Default Origin
(X =0, Y =0)
m m
Figure 23. Default Media Origin for Computer Output on 16 mm Microfilm (COMIC Representation)
Leading
Titling
Top
Xm
Ym
Leading
Titling
Default Origin
(X =0, Y =0)
m m
Default Origin
(X =0, Y =0)
m m
Xm
Ym
Top
Note: Normally the printer determines the size of the medium presentation space, however the XOH Set
Media Size command can be used by a host presentation-services program to specify the Xm and Ym
extents of the medium presentation space. These values are used along with any printer-defined valid [IPDS-3-095]


sensor or operator input to establish the Xm and Ym extents that are required for printable area
calculations. The XOH-SMS command is described in “XOH Set Media Size”.
Xp,Yp Coordinate System (Logical Page)
The Xp,Yp coordinate system is the logical-page presentation space coordinate system. The origin of this
system (xp=0, yp=0) is specified in the Logical Page Position command. It can also be specified by default.
Logical pages can be positioned anywhere on the medium presentation space.
Some IPDS printers allow pages to be independently placed in a fixed partition on either the front-side medium
presentation space or the back-side medium presentation space. These printers also allow the page to be
rotated into one of four possible orientations. For printers that do not support this function, IPDS commands
cannot change the orientation of the X
p,Yp coordinate system; it is always parallel to, but offset from, the Xm,Ym
coordinate system. Support for this optional function is indicated by property pair X'6101' in the Device-Control
command-set vector of an STM reply.
Note: The X
p,Yp coordinate system is the coordinate system for a page's logical page as well as for an
overlay's logical page.
The size of the logical page in the Xp dimension is called the Xp extent. The size of the logical page in the Yp
dimension is called the Yp extent. The sizes are set by default or by the Logical Page Descriptor command.
The Xp,Yp coordinate system is used to position object areas on the logical page. Object areas and text can be
presented at different orientations on the page.
Figure 24 shows an example of the relationship between the X
m,Ym coordinate system, the Xp,Yp coordinate
system, and the origin of an object area.
Figure 24. Locating Data by Xm,Ym and Xp,Yp Coordinates
Medium Presentation Space
Object Area
- Bar code data [IPDS-3-096]
- Graphics data [IPDS-3-097]
- IO Image data [IPDS-3-098]
- Object Container data [IPDS-3-099]
- Text data [IPDS-3-100]
Logical Page
+X or +Xm p
+Y or +Ym p
(X = 0 , Y = 0)m m
(X = 2 , Y = 2)m m
(X = 0 , Y = 0)p p
(X = 5 , Y = 5)m m
(X = 3 , Y = 3)p p


Positioning of an object area in the Xp,Yp coordinate system consists of two operations:
• Location of the object area origin in the Xp,Yp coordinate system [IPDS-3-101]
• Rotation of the object area with respect to the Xp axis in the plane of the logical page, also referred to as [IPDS-3-102]
object area orientation with respect to the Xp,Yp coordinate system
Object area orientation is specified by an angle measured clockwise from the Xp axis to the Xoa axis of the
object area. Object area orientation has no effect on the relationship between the Xoa axis and the Yoa axis of
the object area. The Yoa axis of the object area remains at a 90 degree clockwise rotation with respect to the
Xoa axis for all object area orientations.
Some object area orientations with respect to the Xp,Yp coordinate system are shown in Figure 25.
Figure 25. Object Area Rotation in Xp,Yp Coordinate System
(0,0)
Xp
Yp
(0,0)
Xp
Yp
(4,5)
45°Orientation 90°Orientation
(4,5)
(4,5)
180°Orientation
(4,5)
270°Orientation
(4,5)
0°Orientation 15°Orientation
(4,5)
Yoa
Xoa
Yoa
Xoa
(0,0)
Xp
Yp
(0,0)
Xp
Yp
Xoa
Yoa
Xoa
Yoa
Yoa
Xoa
(0,0)
Xp
Yp
(0,0)
Xp
Yp
Xoa
Yoa


I,B Coordinate System (Text)
The Inline, Baseline (I,B) coordinate system describes the placement and orientation of text characters and
object areas on the logical page. The printer places characters along the I-axis to form a line of text. The printer
places lines of text along the B-axis on the logical page. IPDS commands can change both the origin and the
orientation of the inline and baseline axes.
Character Development
As characters are developed on a page by the printer, the inline coordinate is incremented in the positive inline
(or +I) direction. As lines are developed on a page by the printer, the baseline coordinate is incremented in the
positive baseline (or +B) direction.
Note: Characters are normally developed on a page in the sequence they are read, for example, left to right.
The printer can actually place characters or lines on a page in various directions as in bidirectional
printing.
The distance the inline coordinate is incremented as characters are developed is the character increment or
inline increment. The distance the baseline coordinate is incremented as lines are developed is the baseline
increment.
The coordinates of the first text position on the logical page are called the initial inline text coordinate (i
i) and
the initial baseline text coordinate (bi). The coordinates of the current position on the logical page are called the
current inline text coordinate (ic) and the current baseline text coordinate (bc). Figure 26 shows the various I
and B coordinates on the logical page.
Figure 26. The I,B Coordinate System on the Logical Page
Logical Page
(i , b )i i
Initial Text Position
(i , b )c c
Current Text Position
+I Direction
+B Direction


I,B Orientation
The +I and +B directions are specified independently in terms of an angle from the +Xp direction. This
orientation can be set either in the Logical Page Descriptor command, by means of embedded controls in the
Write Text command, or by printer default. Setting the orientations of the +I and +B axes also sets the I,B origin
at one of the four corners of the logical page. Eight orthogonal I,B text orientations exist out of the many
theoretical I,B text orientations.
Figure 27 shows all eight usable orientations.
Figure 27. The Usable I,B Text Orientations
ABC
DEF
DEF
ABC
ABC
DEF
ABC
DEF
ABCDEF
DEF
ABC
DEF
ABC
DEF ABC
I=0 , B=90 ° ° I=90 , B=180 ° ° I=90 , B=0 ° ° I=180 , B=90 ° °
I=270 , B=0 ° ° I=180 , B=270 ° ° I=0 , B=270 ° ° I=270 , B=180 ° °
I B IB
B BII
I IBB
B BI I
0° Character Rotation
Note: Figure 27 assumes a character rotation of 0 degrees with respect to the I axis. See Figure 102 for more information on character rotation.
Object areas can be positioned on a page using the I,B coordinate system. Positioning of an object area in the
I,B coordinate system consists of two operations:
• Location of the object area origin in the I,B coordinate system [IPDS-3-103]
• Rotation of the object area with respect to the I axis in the plane of the logical page, also referred to as object [IPDS-3-104]
area orientation with respect to the I,B coordinate system
Object area orientation is specified by an angle measured clockwise from the I axis to the X
oa axis of the object
area. Since the I axis can itself be rotated relative to the Xp axis, the object area orientation with respect to the
Xp,Yp coordinate system (the logical page) is determined by the object area orientation with respect to the I
axis in conjunction with the I-axis orientation with respect to the Xp axis. Object area orientation has no effect
on the relationship between the Xoa axis and the Yoa axis of the object area. The Yoa axis of the object area
remains at a 90 degree clockwise rotation with respect to the Xoa axis for all object area orientations with
respect to the I axis.
Given an object area orientation (O oa) with respect to the I axis, and given an I-axis orientation (O i) with respect
to the Xp axis, the equivalent object area rotation with respect to the Xp,Yp coordinate system and the logical
page is given by the sum (O oa + Oi) modulo 360; that is, the remainder when (O oa + Oi) is divided by 360. [IPDS-3-105]


The Four Basic Object Orientations
This section will discuss the possibilities when using one of the four basic object orientation values: 0 degrees,
90 degrees, 180 degrees, or 270 degrees. [IPDS-3-106]
Given eight orthogonal I,B orientations and four object area orientations with respect to the I,B coordinate
system, there exist thirty-two ways of positioning an object area on a logical page with respect to all I,B
orientations.
Figure 28. Object Area Rotation in I,B Coordinate System, Part 1
I
B
I
BI
B
I
B
I B
I B
B I
B I
I
B
I
BI
B
I
B
I B
I B
B I
B I
I=0°, B=90° I=90°, B=180°
I=180°, B=270°I=270°, B=0°
I=90°, B=0°
I=0°, B=270° I=270°, B=180°
I=180°, B=90°
I=0°, B=90°
I=270°, B=0° I=180°, B=270°
I=90°, B=180° I=90°, B=0°
I=0°, B=270° I=270°, B=180°
I=180°, B=90
0° ObjectArea Orientation
90° ObjectArea Orientation
(4,5)
Yoa
Xoa
Yoa
Xoa
(4,5)
Yoa
Xoa
(4,5)
Yoa
Xoa (4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5) Xoa
Yoa(4,5)
Xoa
Yoa
(4,5) Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa(4,5)
Xoa
Yoa(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5)
Figure 28 and Figure 29 show that the thirty-two ways of positioning an object area with respect to
the I,B coordinate system are developed by choosing one of the four basic object area rotations that are
defined with respect to the Xp,Yp coordinate system and then locating the object area origin in the given I,B
orientation.
For example, if a printer supports the 0 degree object area orientation for I-axis orientations of 0 degrees, 90
degrees, 180 degrees, and 270 degrees, it actually supports all four object area orientations with respect to the
X
p,Yp coordinate system and the logical page. [IPDS-3-107]


Figure 29. Object Area Rotation in I,B Coordinate System, Part 2
(4,5)
(4,5)
(4,5)
I
B
I
BI
B
I
B
I B
I B
B I
B I
I
B
I
BI
B
I
B
I B
I B
B I
B I
(4,5)
(4,5)
(4,5)
(4,5)
(4,5)
(4,5)
(4,5)
(4,5)
(4,5)
I=0°, B=90° I=90°, B=180°
I=180°, B=270°I=270°, B=0°
I=90°, B=0°
I=0°, B=270° I=270°, B=180°
I=180°, B=90°
I=0°, B=90°
I=270°, B=0° I=180°, B=270°
I=90°, B=180° I=90°, B=0°
I=0°, B=270° I=270°, B=180°
I=180°, B=90°
180° ObjectArea Orientation
270° ObjectArea Orientation
(4,5)
(4,5)
(4,5)
(4,5)
Yoa
Xoa (4,5)
Xoa
Xoa
Xoa
Xoa
Xoa
Xoa
Xoa
Yoa
Yoa
Yoa
Yoa
Yoa
Yoa
Yoa
Xoa
Yoa
Xoa
Xoa
Xoa
Xoa
Xoa
Xoa
Xoa
Yoa
Yoa Yoa
Yoa
Yoa
Yoa
Yoa
Similarly, if a printer supports a 0 degree object area orientation with respect to the Xp,Yp coordinate system, it
also supports the following object area orientations with respect to the I,B coordinate system:
• 0 degree object area orientation with respect to I,B = 0,90 [IPDS-3-108]
• 0 degree object area orientation with respect to I,B = 0,270 [IPDS-3-109]
• 90 degree object area orientation with respect to I,B = 270,0 [IPDS-3-110]
• 90 degree object area orientation with respect to I,B = 270,180 [IPDS-3-111]
• 180 degree object area orientation with respect to I,B = 180,270 [IPDS-3-112]
• 180 degree object area orientation with respect to I,B = 180,90 [IPDS-3-113]
• 270 degree object area orientation with respect to I,B = 90,180 [IPDS-3-114]
• 270 degree object area orientation with respect to I,B = 90,0 [IPDS-3-115]


Other Object Orientations
Some IPDS printers support all possible values for object area orientation—that is, they do not restrict the
orientation to certain multiples of 90 degrees. Such printers have many thousands of ways of positioning an
object area with respect to the I,B coordinate system, rather than the thirty-two ways shown in Figure 28 and Figure 29. For example, the eight possibilities for an object area orientation of 15
degrees, 0 minutes, are shown in Figure 30.
Figure 30. 15 Degree Object Area Rotation in I,B Coordinate System
I
B
I
BI
B
I
B
I B
I B
B I
B I
I=0°, B=90° I=90°, B=180°
I=180°, B=270°I=270°, B=0°
I=90°, B=0°
I=0°, B=270° I=270°, B=180°
I=180°, B=90°
15° ObjectArea Orientation
(4,5)
Yoa
Xoa
Xoa
Yoa (4,5)
Xoa
Yoa(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa (4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa
(4,5)
Xoa
Yoa(4,5)


Xt,Yt Coordinate System (Text)
The Xt,Yt coordinate system describes the placement of text data within an abstract space called the text
presentation space. Refer to Chapter 5, “Text Command Set” for a description of this coordinate
system and how this space is mapped to the logical page.
Xg,Yg Coordinate System (Graphics)
The Xg,Yg coordinate system describes the placement of graphics data within an abstract space called the
graphics presentation space. Refer to Chapter 8, “Graphics Command Set” for a description of
this coordinate system and how this space is mapped to the logical page.
Xio,Yio Coordinate System (IO Image)
The Xio,Yio coordinate system describes the placement of IO-Image data within the IO-Image presentation
space. Refer to Chapter 7, “IO-Image Command Set” for a description of this coordinate system
and how this space is mapped to the logical page.
Xbc,Ybc Coordinate System (Bar Code)
The Xbc,Ybc coordinate system describes the placement of bar code data within the bar code presentation
space. Refer to Chapter 9, “Bar Code Command Set” for a description of this coordinate system
and how this space is mapped to the logical page.
Xoc,Yoc Coordinate System (Object Container)
The Xoc,Yoc coordinate system describes the placement of presentation-form object container data within the
object container presentation space. Refer to Chapter 10, “Object Container Command Set” for a
description of this coordinate system and how this space is mapped to the logical page.
Xoa,Yoa Coordinate System (Object Area)
The Xoa,Yoa coordinate system describes the placement of IO-Image, graphics, bar code, and object container
presentation spaces within object areas that are then positioned on a logical page. [IPDS-3-116]


Coordinate System Relationships
All the coordinate systems can be used to locate any point in relation to the logical page and the medium
presentation space. For example, the current position (coordinates) can be represented as shown in Figure 31.
Figure 31. Calculating the Current Text Position
+X or +Xm p
+Y or +Ym p
(x = 0, y = 0)m m
(x = 2, y = 2)m m
(x = 0, y = 0)p p (I = 0, B = 0)
Medium Presentation Space
Logical Page
+ B
+ I
Current Text
Position
(i = 9, b = 4)c c
(x = 9, y = 9)p p
(x = 11, y = 11)m m
As shown in Figure 31, the current I,B text coordinates are calculated in L-units by measuring in the +I and +B
direction starting at the I,B system origin. In this figure, the text orientation is 90°, 180°; therefore, the I,B
system origin is at the top-right corner of the logical page. The current text coordinates are i
c=9 and bc=4.
As shown in Figure 31, the current Xp,Yp coordinate position is calculated as an offset from the origin of the
logical page (xp=0, yp=0). The current Xm,Ym coordinate position is calculated as an offset from the origin of the
medium presentation space (x m=0, ym=0).
IPDS commands allow data objects to be positioned on the page using either Xp,Yp or I,B coordinate positions.
More complicated relationships exist on printers that support optional function such as N-up, duplex, and page
orientation. See Figure 57 for an example of this situation. [IPDS-3-117]


The Valid Printable Area
The valid printable area (VPA) is the intersection of the current logical page with the area of the medium
presentation space that is allowed to contain data to be printed. The area of the medium presentation space
that is allowed to contain data to be printed is one of the following:
• The area that corresponds to the physical printable area if working with a secure overlay or if there is no [IPDS-3-118]
user-printable area defined; see Figure 32
• The intersection of the area corresponding to the physical printable area with the user-printable area; see [IPDS-3-119]
Figure 33
The physical printable area is defined to the host through the Printable-Area self-defining field of the XOH
Obtain Printer Characteristics command. The logical page is defined by the host through the Logical Page
Descriptor (LPD) command. Note that overlays have an associated LPD. For the duration of the overlay, this
LPD specifies the current logical page used in defining the VPA.
Printing cannot take place outside of the VPA. Positioning outside of the VPA is valid; however, if data to be
printed is outside of the VPA, a positioning exception exists. When the data to be printed is blank (no toned
pels), the exception may be suppressed. Positioning exceptions include X'08C1..00', X'08C2..00', X'08C3..00',
X'0411..00', and X'020A..05'; the Exception-Handling Control determines whether or not these exceptions are
reported.
Figure 32. Example of the Valid Printable Area. This example applies if there is no user-printable area defined
or if a secure overlay is being used.
Medium Presentation Space
Physical Printable Area
Current Logical Page
Valid Printable Area


The User-Printable Area
The IPDS architecture allows a presentation services program to define a user-printable area with the Define
User Area command. The user-printable area can be used to specify the portion of the medium presentation
space to which user-generated data is restricted. Only data specified within a secure overlay can be printed
outside of the user-printable area. The XOA Control Edge Marks and XOA Mark Form commands can also
cause printing outside the user-printable area.
The user's VPA is the intersection of the user-printable area, the area that corresponds to the physical printable
area, and the current logical page. The user-printable area is defined by the host through the Define User Area
(DUA) command. The physical printable area is described to the host by the Printable-Area self-defining field
of the XOH Obtain Printer Characteristics reply. The current logical page is defined by the host through the
Logical Page Descriptor (LPD) command.
Generally, positioning outside of the user's VPA is valid; however, if portions of a page or a nonsecure overlay
that are to be printed are outside of the user's VPA, a positioning exception exists. Positioning exceptions
include X'08C1..00', X'08C2..00', X'08C3..00', X'0411..00', and X'020A..05'. The Exception Handling Control
determines if these exceptions are reported. Figure 33 shows the user's VPA.
The VPA for a secure overlay is the intersection of the secure overlay's logical page and the physical printable
area. Thus, a secure overlay can print outside of the user's VPA. An overlay is specified to be a secure overlay
when the overlay is included by an Include Overlay command. This allows a presentation-services program to
print information on the media that cannot be overwritten, omitted, or changed by the print job submitter without
an exception occurring. A presentation-services program can use a secure overlay and a user-printable area to
print a security label on each side of a sheet. [IPDS-3-120]


Figure 33. Example of the User’s Valid Printable Area. This example applies when the Define User Area
command is used.
Medium Presentation Space
Physical Printable Area
User’s Current Logical Page
Secure Overlay Current Logical Page
User Printable Area
User’s Valid Printable Area
Security label could be placed here
(it cannot be changed by the user)
User data can be placed here
Security label could be placed here
(it cannot be changed by the user)
Position Exceptions for Presentation Objects
Table 9. Position Exceptions for Presentation Objects
Type of Exception Bar Code
Object
Graphics
Object
IO Image
Object
Object
Container
Text
Object
Data outside of presentation space X'0411..00' X'0300..0D'
X'03E1..00'
X'03E3..00'
X'05A9..02'
X'05B5..10'
X'020D..05'
1 X'0201..03' [IPDS-3-121]
Data outside of object area after mapping
(position mapping only)
X'0411..00' not
applicable
not
applicable
X'020D..06' X'020A..06'
Data outside of valid printable area X'0411..00'
2 X'08C1..00' 2 X'08C1..00' 2 X'08C1..00' X'08C1..00' [IPDS-3-122]
Notes:
1. If a presentation object type has the ability to generate data outside its presentation space, and if that is considered [IPDS-3-123]
an error by the definition of that object type, X'020D..05' is the exception ID to report.
2. Some older printers used X'020A..05' instead of X'08C1..00' or X'0411..00'. [IPDS-3-124]


Logical Positioning and Physical Pels
Logical positions can point between pels. In this case, the pel associated with a given logical position is the pel
that follows the logical position in the positive inline and the positive baseline directions. This type of logical
positioning permits the printing of n characters, each having a fixed character increment of i, on a logical page
whose inline extent is ni. Refer to Figure 34.
For example, if n is 100 characters and i is 0.1 inch (10-pitch), all 100 characters fit on a logical page with an
inline extent of 10 inches. In this case, the 100th character just fits within the page boundary, and the final
character increment places the current text position (i
c) at the logical page boundary. As long as no attempt is
made to print off the logical page, an exception does not exist.
Figure 34. Logical Position and Next Pel to Be Printed for Four I,B Orientations
+B
+I +B
+I
+B
+I +B
+I
+B
+I +B
+I
+B
Legend:
= logical position
= physical pel
= next printed pel
+B
+I
+I+
+
+
++
++
++


Processing IPDS Commands
The IPDS structured-field format allows commands to be sent to the printer in a continuous stream. Each
command is self-describing: the command length, identifier, flag byte, and data (that is not always present) are
all part of each command. The printer-host conversation is carried on as if IPDS commands were processed in
sequential order by the printer.
Every IPDS command contains a flag byte. Setting the acknowledgment required bit on in this flag byte
indicates to the printer the end of a command sequence. The printer then sends an Acknowledge Reply to the
host. Figure 35 shows an example of this process.
Figure 35. An Example of IPDS Command Processing
IPDS Command with
Acknowledgment Request
To Printer
IPDS Command
To Printer
IPDS Command
To Printer
Acknowledge Reply
From Printer
To Host
Notes:
1. Presentation-services programs should use the STM property pairs and XOH-OPC self-defining fields to [IPDS-3-125]
identify an IPDS printer's functional capabilities. For example, the X'B001' property pair identifies double-
byte support for fonts and code pages; the X'000D' self-defining field identifies the medium modifications
supported (cut, perforation, fixed medium information).
2. Presentation-services programs should use the information in the XOH-OPC Product Identifier self- [IPDS-3-126]
defining field, if present, in messages that identify the printer. This self-defining field must contain the real
device type and model number that is imprinted on the printer's serial-number plate.
3. Presentation-services programs should use the STM type and model information to determine whether or [IPDS-3-127]
not to code around granted deviations. Some printers use these fields to identify an older printer that is
being emulated or mimicked.
When an emulated type and model is returned in STM, the printer must emulate the granted deviations
accurately and return the same information as the emulated printer in the STM and XOA-RRL replies. The
XOH-OPC reply must also be identical, except that the Product Identifier self-defining field can be present
in the XOH-OPC reply; if present, it must contain the real device type and model number that is imprinted
on the printer's serial-number plate.
When a printer is being mimicked, the granted deviations that affect host software should be mimicked
accurately, deviations that don't affect software should be fixed. The functions supported should be
identical to those supported by the mimicked printer. A printer that is mimicking can also support additional
function as long as it is appropriately identified in the STM or XOH-OPC replies. [IPDS-3-128]


Notation Conventions
In the command-set chapters that follow, these conventions apply to the command descriptions:
• Each byte contains eight bits. [IPDS-3-129]
• Bytes of an IPDS structure are numbered from left to right beginning with byte 0 with the leftmost byte as [IPDS-3-130]
most significant; this is called Big Endian. For example, if a structure is three bytes long and has two fields, a
two-byte field followed by a one-byte field, the bytes are numbered as follows:
Bytes 0-1 Field 1
Byte 2 Field 2
Byte 0 is the leftmost, high-order byte for the first field.
• Bit strings are numbered, from left to right, beginning with 0. For example, a one-byte bit string contains bit 0, [IPDS-3-131]
bit 1, ..., bit 7.
• For numerical binary data, bit 0 is the most significant bit. For example, decimal 13 is equivalent to binary [IPDS-3-132]
B'00001101'.
• Negative values are in twos-complement form. [IPDS-3-133]
• Field values are expressed in hexadecimal or binary notation: [IPDS-3-134]
B'01111110' = X'7E' = +126
X'7FFF' = +32,767
X'8000' = -32,768 (when signed binary is used)
X'8000' = +32,768 (when unsigned binary is used)
• Some bits or bytes are labeled reserved. The content of reserved fields are not checked by printers. [IPDS-3-135]
However, IPDS generators should set reserved fields to the specified value, if one is given, or to zero.
• Some fields or values are labeled Retired item n, where n is an identifying number. These fields or values are [IPDS-3-136]
reserved for a particular purpose and must not be used for any other purpose. Refer to Appendix D, “Retired
Items” for a description of the individual retired items.
• Values not explicitly defined in the range column of a field are reserved. [IPDS-3-137]
• On the following pages, commands are described in tables, with additional information about specific fields [IPDS-3-138]
listed after each table. These command tables use the following special terms:
Range The complete set of architecturally valid values for a given field.
Subset Range (DC1, TX1, IM1, IO1, GR1, BC1, OC1, MO1,
PS1, OL1, LF1, LF2, LF3, or LF4)
The field range that must be supported by any printer that supports a command-set subset.
The printer can support values outside the subset range; although these values must stay
within the architecturally valid range. Refer to your printer documentation for supported
values.
Required The field range that must be supported by any printer that supports the command. The
printer can support values outside the required range; although these values must stay
within the architecturally valid range.
Printer default The value assigned to a field when the printer successfully concludes initialization, for
example, an initial microcode load (IML). The IPDS architecture specifies printer default
values for some fields. Refer to your printer documentation for the printer-defined default
values.


L-Unit Range Conversion Algorithm
Some field values within IPDS data structures are specified assuming a unit of measure of 1/1440 of an inch.
These fields are designated as such with a reference to this algorithm. If an IPDS receiver supports additional
units of measure, the IPDS architecture requires the receiver to at least support a range equivalent to the
specified range relative to each supported unit of measure. Table 10 lists the equivalent field
ranges for the most commonly used units of measure.
The values required to be supported when 14,400 units per 10 inches is specified for a field are listed in the
command. If additional units of measure are supported, the field values that the IPDS architecture requires a
printer to support for these alternate units of measure are calculated using the following algorithm:
1. Calculate the number of printer-supported units per inch as follows: [IPDS-3-139]
• If the length of the measurement base for a field is 10 inches, divide the number of printer-supported [IPDS-3-140]
units that applies to the desired field by 10.
• If the length of the measurement base for a field is 10 centimeters, multiply the number of printer- [IPDS-3-141]
supported units per 10 centimeters (one decimeter) that applies to the desired field by 0.254, the
approximate number of decimeters per inch.
2. Calculate the number of printer-supported units per IPDS unit as follows: [IPDS-3-142]
• Divide the number of printer-supported units per inch calculated in the previous step by 1440 (the [IPDS-3-143]
number of IPDS units per inch).
3. Calculate the required value in the printer-supported unit of measure as follows: [IPDS-3-144]
• Multiply the IPDS-specified subset range values for the desired field, after converting to base 10, by the [IPDS-3-145]
printer-supported units per IPDS-specified unit calculated in the previous step.
• Round off the product to the nearest integer; for example, 2.5 becomes 3 and 2.4 becomes 2. [IPDS-3-146]
• Adjust the new range so that it is a subset of the IPDS-specified range. [IPDS-3-147]


For example, suppose that the specified range is X'8000'–X'7FFF' when using 14,400 units per 10 inches. The
equivalent range at a unit of measure of 1/240 of an inch is calculated as follows:
1. Printer-supported units per inch = 2400 ÷ 10 = 240 [IPDS-3-148]
2. Printer-supported units per IPDS unit = 240 ÷ 1440 = 1/6 [IPDS-3-149]
3. Range at 2400 units per 10 inches: [IPDS-3-150]
a. X'8000' = -32,768 (converted to base 10)
-32,768 × 1/6 = -5461.3333
b. X'7FFF' = 32,767 (converted to base 10)
32,767 × 1/6 = 5461.1667
Therefore, the equivalent range at 2400 units per 10 inches is -5461 to 5461 that in hexadecimal is X'EAAB' to
X'1555'. Table 10shows the IPDS required ranges for several commonly supported measurement bases.
Table 10. Field Ranges for Commonly-Supported Measurement Bases
14,400 units per 10 inches 5670 units per 10
centimeters
2400 units per 10 inches 945 units per 10 [IPDS-3-151]
centimeters
X'8000'–X'7FFF' X'8000'–X'7FFF' X'EAAB'–X'1555' X'EAAB'–X'1555'
X'FF00'–X'0100' X'FF00'–X'0100' X'FFD5'–X'002B' X'FFD5'–X'002B'
X'FF01'–X'00FF' X'FF01'–X'00FF' X'FFD5'–X'002B' X'FFD5'–X'002B'
X'00'–X'FF' X'00'–X'FF' X'00'–X'2B' X'00'–X'2B'
X'0000'–X'1555' X'0000'–X'1555' X'0000'–X'038E' X'0000'–X'038E'
X'0000'–X'7FFF' X'0000'–X'7FFF' X'0000'–X'1555' X'0000'–X'1555'
X'0001'–X'7FFF' X'0001'–X'7FFF' X'0001'–X'1555' X'0001'–X'1555'
X'000A'–X'2FD0' X'000A'–X'2FD0' X'0002'–X'07F8' X'0002'–X'07F8'
X'000A'–X'4EC0' X'000A'–X'4EC0' X'0002'–X'0D20' X'0002'–X'0D20'
The IPDS architecture requires all printers to support 1440ths in all commands that specify units of measure;
other units of measure are optionally supported. Using 1440ths, however, limits addressability to slightly more
than 22 3/4 inches. The IPDS architecture requires printers that support presentation spaces larger than can
be addressed with 1440ths to also support 240ths in these commands. [IPDS-3-152]


The IPDS Command Format
All IPDS commands are encoded in the following format:
```
Length Command Flag CID Data
```
The following table describes the fields within an IPDS command:
Offset Type Name Range Meaning
0–1 UBIN Length X'0005' –
X'7FFF'
Length of the command
The length includes the length field itself, the command code field,
the flag byte, and the optional correlation ID (CID) and data fields.
2–3 CODE Command
code
X'D600' –
X'D6FF'
IPDS command code
The command field is a two-byte General Data Stream (GDS)
registered structured field ID (registered by SNA Raleigh in
Systems Network Architecture: Formats, GA27-3136).
4 BITS IPDS command flags [IPDS-3-153]
bit 0 ARQ flag B'0', B'1' Acknowledgment Required (ARQ) flag
If this bit is B'1', the host requests the printer to send an
Acknowledge Reply.
bit 1 CID flag B'0', B'1' Correlation ID flag
If this bit is B'1', a two-byte correlation ID follows the flag byte. If
this bit is B'0', the optional correlation ID is not present, and the
following byte or bytes, if any, contain the data field.
bit 2 ACK
continuation
flag
B'0', B'1' Acknowledgment continuation flag
If this bit is B'1', the host is requesting continuation of the current
Acknowledge Reply. If this bit is B'0', the host is not requesting
continuation.
bit 3 Long ACK B'0', B'1' Long Acknowledge Reply flag
If this bit is B'1', the host can accept long Acknowledge Replies
(up to 65,535 bytes long). If this bit is B'0', the host can only
accept short Acknowledge Replies (up to 256 bytes long).
bits 4–7 B'0000' Reserved
5–6 UBIN CID X'0000' –
X'FFFF'
Correlation ID (optional)
The CID is an optional field and is present only if bit 1 of the flag
byte is B'1'. A presentation services program can use any value
between X'0000' and X'FFFF' for this ID.
The printer sends a negative Acknowledge Reply (NACK) to the
host if an exception occurs. If the printer reports a synchronous
exception and the command responsible for the NACK included a
CID, the printer returns the CID for that command in the NACK.
7 to
end of
cmnd
Data See specific
commands
Command data
The data field is not present for all commands. If present, this field
contains specific orders, parameters, and data appropriate for the
given command. If the CID is present, the length of the data field
can range from 0 to 32,760 bytes. If the CID is not present, the
length of the data field can range from 0 to 32,762 bytes.
IPDS Command Format


Exception ID X'8001..00' exists if an invalid or unsupported IPDS command code is specified. Exception ID
X'0202..02', X'0203..02', or X'80E0..00' exists if an invalid IPDS command length is specified. Exception ID
X'0204..02' exists if the acknowledgment-continuation flag is used incorrectly. Exception ID X'8004..00' exists if
an IPDS command is received after an acknowledgment is requested, but before the reply is sent.
Host Acknowledgment Requests
The host requests an acknowledgment from the printer by setting the Acknowledgment Required (ARQ) flag
on in an IPDS command. This request is made under the following conditions:
• The host wants a positive acknowledgment that the printer has received, accepted, and syntax-checked the [IPDS-3-154]
command sequence for processing and that the command is the last in the sequence. Refer to the first bullet
listed under “General Rules for the Acknowledge Reply”.
• The command is sent by the host to request the return of printer information. [IPDS-3-155]
Printer Acknowledge Replies
The printer uses the Acknowledge Reply to return Correlation ID, page and copy counters, sense information,
and any additionally requested information to the host. The host uses the acknowledgment data to maintain
control of the printing operation and to initiate exception-recovery actions when necessary.
General rules for Acknowledge Replies are listed in “General Rules for the Acknowledge Reply”.
All of the reporting rules are intended to accomplish a simple result: the printer and the communications
network, including direct attachment protocols such as channel, work together to ensure that the next IPDS
command processed subsequent to a given NACK, is the beginning of the host's response to that NACK.
The printer sends an Acknowledge Reply to the host to:
• Indicate that a received command or command sequence requesting acknowledgment has been accepted [IPDS-3-156]
for processing and has been syntax-checked
• Return requested printer information [IPDS-3-157]
• Report exceptions [IPDS-3-158]
The printer sends a negative Acknowledge Reply (NACK) to the host to indicate that an exception has
occurred. Exceptions are returned in either three or twenty-four bytes of detailed sense information in the
special data area of the NACK. The printer can send a NACK without first having received an ARQ. The printer
sends a positive Acknowledge Reply (ACK) only in response to ARQs. [IPDS-3-159]


IPDS Operating States
IPDS commands are defined within the context of printer operating states. The printer moves between these
operating states during command processing. The states are used to enforce the validity of command
sequences received from the host. If an invalid command for the current operating state is received, exception
ID X'8002..00' exists. IPDS printers are thus state machines with the following operating states:
• Home state [IPDS-3-160]
• Presentation-object [IPDS-3-161]
states
– Text state
– IO-Image state
– IM-Image state
– Graphics state
– Bar Code state
– Object Container state (presentation form)
• Page state [IPDS-3-162]
• Overlay state [IPDS-3-163]
• Page segment state [IPDS-3-164]
• Object- [IPDS-3-165]
Container Resource state (setup file, data object resource, or data-object-font component)
• Font state [IPDS-3-166]
• Code page state [IPDS-3-167]
• IO-Image resource state [IPDS-3-168]
• Metadata state [IPDS-3-169]
• Anystate [IPDS-3-170]
Home State
Home state is the initial IPDS operating state. Home state is entered when the printer is powered on, or after
initialization, for example, an Initial Microcode Load (IML) of the IPDS printer process. The printer returns to
home state at the end of each downloaded page, page segment, overlay, font resource, and IO-Image
resource. The printer also returns to home state at the end of each metadata or object container object
encountered in home state.
In addition, the printer returns to home state upon receiving a Set Home State
command, an XOA command that changes the printer state to home state, or upon reporting an exception.
While in home state, the printer receives control commands and initialization commands to prepare for a print
operation. In home state, the printer can also receive commands that delete resources or request the return of
printer information to the host presentation services program. [IPDS-3-171]


Presentation-Object States
In a presentation-object state, the printer establishes the initial processing conditions for a presentation object
and then places the object data on the page, page segment, or overlay. Presentation-object states are as
follows:
• Text state [IPDS-3-172]
• IO-Image state [IPDS-3-173]
• IM-Image state [IPDS-3-174]
• Graphics state [IPDS-3-175]
• Bar Code state [IPDS-3-176]
• Object Container state (presentation form) [IPDS-3-177]
The printer cannot enter a presentation-object state directly from home state; the printer must be in either page
state, page segment state, or overlay state. The printer enters a presentation-object state when it receives one
of the following commands:
Write Text Control causes the printer to enter the text state from page or overlay state.
Write Image Control causes the printer to enter the IM-Image state from page, page segment, or overlay
state.
Write Image Control 2 causes the printer to enter the IO-Image state from page, page segment, or overlay
state.
Write Graphics Control causes the printer to enter the graphics state from page, page segment, or overlay
state.
Write Bar Code Control causes the printer to enter the bar code state from page, page segment, or overlay
state.
Write Object Container Control causes the printer to enter the object container state from page or overlay
state.
Immediately upon entering any of these presentation-object states (other than IM-Image state), the printer can
receive metadata associated with the presentation object.
Receiving a valid End command in any presentation-object state terminates that state and returns processing
to the previous state. The End command is valid at any time in text state, IO-Image state, graphics state, bar
code state, and object container state. The End command is valid in the IM-Image state only after at least one
Write Image (WI) command has been received.
Figure 36 shows the relationship between presentation-object states (other than IM-Image state) and metadata
state.
See Figure 37, Figure 38, and Figure 39 for examples of the
relationship between presentation-object states and other printer states.
Figure 36. Relationship between Presentation-Object States (other than IM-Image State) and Metadata State
Write Metadata Control
End
Metadata State
Presentation-Object
State
M The command that begins the presentation-object
state can be followed by one or more metadata
objects. Each metadata object begins with a Write
Metadata Control command and ends with an End
command as shown. It is invalid to specify a Write
Metadata Control command sequence after the
first non-metadata command within the containing
state.
Metadata
M


Page State
Page state is the operating state for printing a page. The printer enters page state from home state upon
receiving a Begin Page command and exits upon receiving an End Page command.
Immediately upon entering page state, a printer can receive metadata associated with the page.
In page state, the printer can receive commands that merge previously activated overlays and page segments
with the current page information. The printer also can receive Write Text commands that position text on the
page, can enter a presentation-object state to write text, image, graphics, bar code, and object container
objects, can receive metadata that is associated with the page, and can update font equivalences and
establish data object resource equivalences.
Some IPDS printers allow a group of pages to be saved. The saved pages can later be included in a page to
be printed.
Some IPDS printers allow data objects to be loaded into the printer as resources and later included in a page
to be printed.
Figure 37 shows the relationship between home state, page state, and the presentation-object states, and
between page state and metadata state .
Figure 37. Relationship between Home State, Page State, and the Presentation-Object States, and between
Page State and Metadata State
Begin Page End Page
Include Saved PageInclude Data Object
Include Page Segment
Data Object Resource Equivalence
Load Font Equivalence
Write Text
Home State
Page State
Write Image
Control
Write Graphics
Control
Write Bar Code
Control
Write Object
Container Control
End End End End
Write Image
Control 2
End
IO-Image
State
IM-Image
State
Graphics
State
Bar Code
State
Object
Container
State
Write Text
Control
End
Text
State
Include Overlay
M
A Begin Page command can be followed by one or more metadata objects. Each metadata
object begins with a Write Metadata Control command and ends with an End command
as shown below. It is invalid to specify a Write Metadata Control command sequence
after the first non-metadata command within the page.
Metadata
StateEnd
Write Metadata Control
Metadata
M
Data Object Resource Equivalence 2 [IPDS-3-178]


Overlay State
Overlay state is the operating state that allows overlay data to be downloaded to the printer. The printer enters
overlay state from home state upon receiving a Begin Overlay command and exits upon receiving an End
Page command.
Immediately upon entering overlay state, a printer can receive metadata associated with the overlay.
A parameter in the Begin Overlay command provides an ID for subsequent references to this overlay. When
the Begin Overlay command is issued, the current environment, that consists of the Logical Page Descriptor
values, the Load Font Equivalence values, and the Load Equivalence values, is saved as a part of the stored
overlay definition.
In overlay state, the printer can receive commands that merge previously activated overlays and page
segments with the current overlay information. The printer also can receive Write Text commands that position
text on the overlay, can enter a presentation-object
state to write text, image, graphics, bar code, and object
container data, can receive metadata that is associated with the overlay, and can update font equivalences and
establish data object resource equivalences.
Some IPDS printers allow data objects to be loaded into the printer as resources and later included in an
overlay to be printed.
Figure 38 shows the relationship between home state, overlay state, and the presentation-object states, and
between overlay state and metadata state .
Figure 38. Relationship between Home State, Overlay State, and the Presentation-Object States, and between
Overlay State and Metadata State
Begin Overlay End Page
Include Overlay
Include Data Object Include Page Segment
Data Object Resource Equivalence 2 Load Font Equivalence
Write Text
Home State
Overlay State
Write Image
Control
Write Graphics
Control
Write Bar Code
Control
Write Object
Container Control
End End End End
Write Image
Control 2
End
IO-Image
State
IM-Image
State
Graphics
State
Bar Code
State
Object
Container
State
Write Text
Control
End
Text
State
M
A Begin Overlay command can be followed by one or more metadata objects. Each
metadata object begins with a Write Metadata Control command and ends with an End
command as shown below. It is invalid to specify a Write Metadata Control command
sequence after the first non-metadata command within the overlay.
Metadata
StateEnd
Write Metadata Control
Metadata
M
Data Object Resource Equivalence [IPDS-3-179]


Page Segment State
Page Segment state is the operating state that allows page segment data to be downloaded to the printer. The
printer enters page segment state from home state upon receiving a Begin Page Segment command and exits
upon receiving an End Page command. A parameter in the Begin Page Segment command provides an ID for
subsequent references to this segment.
Unlike overlays, page segments assume the active environment at the time they are included.
While in page segment state, the printer can receive Write Text commands that position text on the logical
page, can enter a presentation-object
state to write image, bar code, and graphics data, and can update font
equivalences and establish data object resource equivalences.
Figure 39 shows the relationship between home state, page segment state, and the presentation-object states.
Figure 39. Relationship between Home State, Page Segment State, and the Presentation-Object States
Write Image
Control 2
Write Image
Control
Write Graphics
Control
Write Bar Code
Control
End End End End
IO-Image
State
IM-Image
State
Graphics
State
Bar Code
State
Begin Page Segment End Page
Load Font Equivalence Write Text
Home State
Page Segment State
Data Object Resource Equivalence 2
Data Object Resource Equivalence [IPDS-3-180]


Font State
Font state is the operating state that allows the printer to receive fully described font data or font character set
data. The printer enters font state from home state upon receiving either a Load Font Control (LFC) command
or a Load Font Character Set Control (LFCSC) command.
While the printer is in font state, the Load Font (LF) command can send font data, such as character-raster
pattern data or outline data, to the printer. Receiving a valid End command in font state terminates font state
and returns processing to home state. The End command is valid in font state only after at least one LF
command has been received.
Note: Unlike fully described fonts and font character sets, symbol sets are loaded entirely in home state.
Figure 40 shows the relationship between home state and font state.
Figure 40. Relationship between Home State and Font State
Load Font Control
or
Load Font Character Set Control
Home State
Font State
Load Font
End


Code Page State
Code page state is the operating state that allows the printer to receive code page data. The printer enters
code page state from home state upon receiving a Load Code Page Control (LCPC) command.
While the printer is in code page state, the Load Code Page (LCP) command sends code-point and GCGID
data to the printer. Receiving a valid End command in code page state terminates code page state and returns
processing to home state. The End command is valid in code page state only after at least one LCP command
has been received.
Figure 41 shows the relationship between home state and code page state.
Figure 41. Relationship between Home State and Code Page State
Load Code Page Control
End
Home State
Code Page State
Load Code Page


IO-Image Resource State
IO-Image resource state is the operating state that allows the printer to receive an IO Image as a resource. The
printer enters this state from home state upon receiving a Write Image Control 2 (WIC2) command.
Immediately upon entering IO-Image resource state, a printer can receive metadata associated with the IO-
Image resource.
While the printer is in IO-Image resource state, one or more Write Image 2 (WI2) commands can carry exactly
one IOCA image segment. An empty image can also be specified by not having any WI2 commands or by
having empty WI2 commands. Empty images can be used to color or shade a rectangular area using just the
image object area. An End command terminates IO-Image r
esource state and returns processing to home
state.
Since the image is not being presented in this state, minimal syntax checking is done.
Figure 42 shows the relationship between home state and IO-Image r
esource state, and between IO-Image
resource state and metadata state .
Figure 42. Relationship between Home State and IO-Image Resource State, and between IO-Image Resource
State and Metadata State
Write Image Control 2
End
Home State
IO-Image Resource State
Write Image 2
M
A Write Image Control 2 command can be followed by one or more metadata objects.
Each metadata object begins with a Write Metadata Control command and ends with an
End command as shown below. It is invalid to specify a Write Metadata Control
command sequence after the first non-metadata command within the image.
Metadata
StateEnd
Write Metadata Control
Metadata
M


Object-Container Resource State
Object-container resource state is the operating state that allows the printer to receive a setup file or receive an
object container as a resource. The printer enters this state from home state upon receiving a Write Object
Container Control (WOCC) command.
Immediately upon entering object-container state, a printer can receive metadata associated with the object
container.
While the printer is in object-container resource state, one or more Write Object Container (WOC) commands
carry a setup file, data object resource, or data-object-font component or if there are no WOC commands, it is
an empty object container. Empty object containers can be used to color or shade a rectangular area using just
the object container object area. An End command terminates o
bject-container resource state and returns
processing to home state.
Setup files are processed as they are received and are not treated as resources; other objects downloaded in
this state are saved as data object resources or data-object-font components to be used later while processing
a page or overlay. For saved resources, since the data object resource or data-object-font component is not
being used for presentation in home state, minimal syntax checking is done during resource download.
Figure 43 shows the relationship between home state and object-container resource state, and between
object-container resource state and metadata state.
Figure 43. Relationship between Home State and Object-Container Resource State, and between Object-
Container Resource State and Metadata State
Write Object Container Control
End
Home State
Object-Container
StateResource
Write Object Container
M
A Write Object Container Control command can be followed by one or more metadata
objects. Each metadata object begins with a Write Metadata Control command and ends
with an End command as shown below. It is invalid to specify a Write Metadata Control
command sequence after the first non-metadata command within the object container.
Metadata
StateEnd
Write Metadata Control
Metadata
M


Metadata State
Metadata state is the operating state that allows the printer to receive metadata that is associated with an
object, or objects, in the IPDS data stream. The printer enters this state from any of the following states upon
receiving a Write Metadata Control (WMC) command:
• Home state [IPDS-3-181]
• Page state [IPDS-3-182]
• Overlay state [IPDS-3-183]
• IO-Image Resource state [IPDS-3-184]
• Object-Container Resource [IPDS-3-185]
state
• Page Text state [IPDS-3-186]
• Page Graphics state [IPDS-3-187]
• Page IO-Image state [IPDS-3-188]
• Page Bar Code state [IPDS-3-189]
• Page Object Container state [IPDS-3-190]
• Page Segment Graphics state [IPDS-3-191]
• Page Segment IO-Image state [IPDS-3-192]
• Page Segment Bar Code state [IPDS-3-193]
• Overlay Text state [IPDS-3-194]
• Overlay Graphics state [IPDS-3-195]
• Overlay IO-Image state [IPDS-3-196]
• Overlay Bar Code state [IPDS-3-197]
• Overlay Object Container state [IPDS-3-198]
While the printer is in metadata state, one or more Write Metadata (WM) commands carry the metadata.
Receiving a valid End command terminates metadata state and returns processing to the previous state.
For all states other than home state, the state in which the WMC command is received determines the object
the metadata is associated with. For example, if the WMC is received in page state, the metadata in the WM
command(s) is associated with the page whose Begin Page command caused page state to be entered.
WMCs received in a non-home state must always be received as the first commands after the non-home state
is entered. Special rules exist for WMC commands received in home state. See “Metadata
” for
details.
Figure 44 shows the relationship between other states and metadata state.
Figure 44. Relationship between Other States and Metadata State
Write Metadata Control
End
Many Possible States
Metadata State
Write Metadata
M The command that begins one of the possible
states can be followed by one or more metadata
objects. Each metadata object begins with a Write
Metadata Control command and ends with an End
command as shown. It is invalid to specify a Write
Metadata Control command sequence after the
first non-metadata command within the containing
state.
Metadata
M


Anystate
Some IPDS commands can be received in any IPDS operating state. These commands do not change the
IPDS operating state, except for XOA Discard Buffered Data and XOA Discard Unstacked Pages that return to
Home state after completion. [IPDS-3-199]


Summary of the IPDS States and Commands
Table 11shows the IPDS commands by command set and the initial state and ending state for each command.
The printer must be in the initial state shown for each command to be valid. The ending state is the state the
printer enters after a valid command has been processed.
Table 11. IPDS Command Code Summary
Command Command
Code
Initial State Ending State
Device-Control Command Set
Activate Resource (AR) X'D62E' Home Home
Activate Setup Name (ASN) X'D60A' Home Home
Apply Finishing Operations (AFO) X'D602' Home Home
Begin Page (BP) X'D6AF' Home Page
Deactivate Font (DF) X'D64F' Home Home
Define User Area (DUA) X'D6CE' Home Home
End (END) X'D65D' See Note 1 See Note 1
End Page (EP) X'D6BF' Page
Page Segment
Overlay
Home
Execute Order Anystate (XOA) X'D633' Any No change
see Note 2
Execute Order Home State (XOH) X'D68F' Home Home
Include Saved Page (ISP) X'D67E' Page Page
Invoke CMR (ICMR) X'D66B' Home Home
Load Copy Control (LCC) X'D69F' Home Home
Load Font Equivalence (LFE) X'D63F' Home
Page
Page Segment
Overlay
No change
Logical Page Descriptor (LPD) X'D6CF' Home Home
Logical Page Position (LPP) X'D66D' Home Home
Manage IPDS Dialog (MID) X'D601' Home Home
No Operation (NOP) X'D603' Any No change
Presentation Fidelity Control (PFC) X'D634' Home Home
Rasterize Presentation Object (RPO) X'D67B' Home Home
Sense Type and Model (STM) X'D6E4' Any No change
Set Home State (SHS) X'D697' Any Home
Set Presentation Environment (SPE) X'D608' Home Home
Text Command Set
Load Equivalence (LE) X'D61D' Home Home [IPDS-3-200]


Table 11 IPDS Command Code Summary (cont'd.)
Command Command
Code
Initial State Ending State
Write Text Control (WTC) X'D688' Page
Overlay
Page Text
Overlay Text
Write Text (WT) X'D62D' Page
Page Segment
Overlay
Page Text
Overlay Text
No change
IM-Image Command Set
Write Image Control (WIC) X'D63D' Page
Page Segment
Overlay
Page IM-Image
Page Segment IM-Image
Overlay IM-Image
Write Image (WI) X'D64D' Page IM-Image
Page Segment
IM-Image
Overlay IM-Image
No change
IO-Image Command Set
Write Image Control 2 (WIC2) X'D63E' Home
Page
Page Segment
Overlay
IO-Image Resource
Page IO-Image
Page Segment IO-Image
Overlay IO-Image
Write Image 2 (WI2) X'D64E' IO-Image Resource
Page IO-Image
Page Segment
IO-Image
Overlay IO-Image
No change
Graphics Command Set
Write Graphics Control (WGC) X'D684' Page
Page Segment
Overlay
Page Graphics
Page Segment Graphics
Overlay Graphics
Write Graphics (WG) X'D685' Page Graphics
Page Segment
Graphics
Overlay Graphics
No change
Bar Code Command Set
Write Bar Code Control (WBCC) X'D680' Page
Page Segment
Overlay
Page Bar Code
Page Segment Bar Code
Overlay Bar Code
Write Bar Code (WBC) X'D681' Page Bar Code
Page Segment
Bar Code
Overlay Bar Code
No change
Object-Container Command Set
Data Object Resource Equivalence (DORE) X'D66C' Home
Page
Page Segment
Overlay
No change


Table 11 IPDS Command Code Summary (cont'd.)
Command Command
Code
Initial State Ending State
Data Object Resource Equivalence 2
(DORE2)
X'D66E' Home
Page
Page Segment
Overlay
No change
Deactivate Data-Object-Font Component
(DDOFC)
X'D65B' Home Home
Deactivate Data Object Resource (DDOR) X'D65C' Home Home
Include Data Object (IDO) X'D67C' Page
Overlay
No change
Remove Resident Resource (RRR) X'D65A' Home Home
Request Resident Resource List (RRRL) X'D659' Home Home
Write Object Container Control (WOCC) X'D63C' Home
Page
Overlay
Object-Container Resource
Page Object Container
Overlay Object Container
Write Object Container (WOC) X'D64C' Object-
Container
Resource
Page Object Container
Overlay Object
Container
No change
Metadata Command Set
Delete Home-State Metadata (DHM) X'D658' Home Home
Write Metadata Control (WMC) X'D68A' Home
Page
Overlay
IO-Image Resource
Object-Container
Resource
Page Text
Page Graphics
Page IO-Image
Page Bar Code
Page Object Container
Page Segment Graphics
Page Segment IO-Image
Page Segment Bar
Code
Overlay Text
Overlay Graphics
Overlay IO-Image
Overlay Bar Code
Overlay Object
Container
Metadata
Write Metadata (WM) X'D68B' Metadata No change
Overlay Command Set
Begin Overlay (BO) X'D6DF' Home Overlay
Deactivate Overlay (DO) X'D6EF' Home Home
Include Overlay (IO) X'D67D' Page
Overlay
No change


Table 11 IPDS Command Code Summary (cont'd.)
Command Command
Code
Initial State Ending State
Page-Segment Command Set
Begin Page Segment (BPS) X'D65F' Home Page Segment
Deactivate Page Segment (DPS) X'D66F' Home Home
Include Page Segment (IPS) X'D67F' Page
Overlay
No change
Loaded-Font Command Set
Load Code Page Control (LCPC) X'D61A' Home Code Page
Load Code Page (LCP) X'D61B' Code Page Code Page
Load Font Character Set Control (LFCSC) X'D619' Home Font
Load Font Control (LFC) X'D61F' Home Font
Load Font Index (LFI) X'D60F' Home Home
Load Font (LF) X'D62F' Font Font
Load Symbol Set (LSS) X'D61E' Home Home
Retired Commands
Retired item 93 X'D600'
Retired item 94 X'D604'
Retired item 95 X'D60D'
Retired item 96 X'D614'
Retired item 134 X'D61C'
Retired item 97 X'D624'
Notes:
1. The End command is valid at any time in the text, IO-Image, graphics, bar code, and object-container [IPDS-3-201]
presentation-object states. The End command is valid in the IM-Image state only after at least one Write
Image (WI) command has been received. This command causes the printer to return to the current page,
page segment, or overlay state.
The End command is valid at any time in the IO-Image resource, object-container resource, and metadata
states. The End command is valid in the font state only after at least one Load Font (LF) command has
been received. The End command is valid in the code page state only after at least one Load Code Page
(LCP) command has been received. This command causes the printer to return to home state.
2. The XOA Discard Buffered Data and XOA Discard Unstacked Pages commands cause the printer to enter [IPDS-3-202]
home state.
Figure 45 shows all the IPDS states and IPDS commands. Note that some commands can occur
only in a specific state, some commands can occur in more than one state, and some commands can occur in
any state. Also note that page state, page segment state, overlay state, font state, code page state, IO-Image
resource state, and object-container
resource state can be entered only from home state.
Note: A state transition occurs after the rules for Acknowledge Reply have been satisfied for the command
associated with the state transition. [IPDS-3-203]


Figure 45. The Complete IPDS State Diagram
Page GraphicsEndWGC WG
Page IM ImageEndWIC WI
BP
EP Page IO ImageEndWIC2 WI2
Presentation-Object
StatesPage StateHome State
Page Bar Code
WBCC WBC End
WOCC Page Object
ContainerWOC End
BPS
EP
Page Segment
State
BO
EP
Presentation-Object
States
Overlay
State
Font State
Code Page
State
End
End
LF
LCP
Anystate
NOP
SHS
STM
XOA
LFCSC
LFC
LCPC
IO-Image Resource
State
End WI2
WIC2
Object-Container
StateResource
End WOC
WOCC
AFO
AR
ASN
DDOFC
DDOR
DF
DHM
DO
DORE
DORE2
DPS
DUA
ICMR
LCC
LE
LFE
LFI
LPD
LPP
LSS
MID
PFC
RPO
RRR
RRRL
SPE
XOH
Page TextEndWTC WT
Overlay GraphicsEndWGC WG
Overlay IM ImageEndWIC WI
Overlay IO ImageEndWIC2 WI2
Overlay Bar CodeWBCC WBC End
WOCC Overlay Object
ContainerWOC End
Overlay TextEndWTC WT
Presentation-Object
States Page Segment
Graphics
EndWGC WG
Page Segment
IM Image
EndWIC WI
Page Segment
IO Image
EndWIC2 WI2
Page Segment
Bar Code
WBCC WBC End
Metadata State
End WM
WMC
When a command is followed by the      symbol,
that command can be followed by one or more
metadata objects. Each metadata object begins
with a Write Metadata Control command and
ends with an End command as shown below. It is
invalid to specify a Write Metadata Control
command sequence after the first non-metadata
command within the object.
Metadata State
End WM
WMC
M
M
M
M
M
M
M
M
M
M
M
M
M
M
M
M
M
M
Metadata
DORE
DORE2
LFE
WT
DORE
DORE2
IDO
IO
IPS
ISP
LFE
WT
DORE
DORE2
IDO
IO
IPS
LFE
WT


IPDS Resources
Resources are data-stream objects that are identified by an assigned ID and that are referenced by that ID.
Resources can be complex in nature and can require significant transmission time when they are sent over a
transmission link as well as significant storage space when they are stored in a device. Management of
resources can have a substantial impact on overall system configuration and throughput. A number of system
components are affected:
• The presentation services program in the host is responsible for the overall management of resources in the [IPDS-3-204]
printer.
• The printer is responsible for the management of stored resources that are not directly controlled by the host. [IPDS-3-205]
• A resource-caching intermediate device, if present, assumes part of the management responsibility for those [IPDS-3-206]
resources that are stored in its cache.
• A data-stream-spooling intermediate device, if present, takes on the role of host, and its presentation [IPDS-3-207]
services program assumes most of the resource management responsibilities for resources in the printer.
The following objects are IPDS resources:
• Coded fonts [IPDS-3-208]
– LF1-type coded font components
◦ Single-byte fully described fonts
◦ Double-byte fully described font sections
◦ Font indexes
– LF2-type coded font components
◦ Symbol set coded fonts
– LF3-type coded font components
◦ Font character sets
◦ Code pages
• Data object fonts [IPDS-3-209]
• Data-object-font components [IPDS-3-210]
• Data object resources [IPDS-3-211]
• Page segments [IPDS-3-212]
• Overlays [IPDS-3-213]
• Saved page groups [IPDS-3-214]
Note: The categories data object resource and data-object-font component provide a way to manage a variety
of presentation, non-presentation, color, and font resources. For a complete list of this type of resource,
refer to “Data Object Resources, Data-Object-Font Components, and Setup Files”. [IPDS-3-215]


Resource IDs
A resource ID must be unique in the environment in which the resource is to be used for the duration of its
intended use. The IPDS architecture defines a number of different naming conventions that provide
uniqueness in the various IPDS environments:
A one-byte ID used to reference a coded font in text data or in graphics and bar code data
objects. A LID must be mapped to a Host-Assigned ID before the corresponding resource can
be used.
Host-assigned ID (HAID)
A two-byte ID assigned dynamically to a resource by the host presentation services program.
One-byte overlay IDs are a special form of HAID in that the overlay ID is prefixed by X'00' to
form the two-byte HAID. HAIDs are valid only for the duration of a particular host-to-printer
session.
Host-assigned resource ID (HARID)
A five-byte ID for a coded font resource consisting of host-assigned ID, font section ID, and
font inline sequence. HARIDs are valid only for the duration of a particular host-to-printer
session.
An eight-byte ID for a resident coded font resource consisting of four subfields:
• Graphic character set global ID (GCSGID) [IPDS-3-216]
• Code page global ID (CPGID) [IPDS-3-217]
• Font typeface global ID (FGID) [IPDS-3-218]
• Font width (FW) [IPDS-3-219]
The first three fields contain IBM registered IDs and are unique in IBM environments. GRIDs
are valid for the duration of the resource's physical presence in the printer. For a definition of
the GRID, see the description of the LFE command.
A multi-byte ID that includes date and time information as well as host information. The
following formats are defined and range in size from 82 bytes to 172 bytes:
• Remote PrintManager MVS™ format (82 or 164 bytes per resource) [IPDS-3-220]
• Extended remote PrintManager MVS format (86 bytes per resource) [IPDS-3-221]
• MVS host unalterable remote font format (172 bytes per font) [IPDS-3-222]
GRNs are valid for the duration of the resource's physical presence in the printer or
intermediate device. For a definition of GRNs, see the description starting.
Coded-font format
An attribute-based ID for a coded-font resource consisting of a font character set host-
assigned ID, a code page host-assigned ID, the four components of the GRID, a pattern-
technology ID, a vertical scale factor, and a horizontal scale factor. Coded-font format IDs are
valid only for the duration of a particular host-to-printer session.
Data-object-font format
An attribute-based ID for a data-object font consisting of a data-object-font-component host-
assigned ID (either a TrueType/OpenType font or a TrueType/OpenType collection), an
optional code page host-assigned ID, either an index value or a full font name to identify a font
within a collection, optional linked TrueType/OpenType objects, desired size, character
rotation, and encoding. Data-object-font format IDs are valid only for the duration of a
particular host-to-printer session. [IPDS-3-223]


Variable-length group ID
A 1 to 244 byte long binary ID that identifies a group of saved pages. Variable-length group
IDs are valid only for the duration of the resource's physical presence in the printer or
intermediate device.
A variable length (2 bytes long to 129 bytes long) binary ID that uniquely identifies an object.
OIDs use the ASN.1 definite-short-form object identifier format defined in the ISO/IEC
8824:1990(E) international standard and described in the MO:DCA Registry Appendix of the
Mixed Object Document Content Architecture Reference. An OID consists of a one-byte
identifier (X'06'), followed by a one-byte length (between X'00' and X'7F'), followed by 0–127
content bytes.


HAID Pools
The HAID is the primary resource ID used to manage IPDS resources and is used in the following
commands: AR, BO, BPS, DDOFC, DDOR, DF , DO, DORE, DORE2, DPS, ICMR, IDO, IO, IPS, LCC, LCPC,
LFC, LFCSC, LFE, LFI, LPD, LSS, RPO, XOA RRL, WBCC, WGC, WIC2, WOCC, and WTC. HAIDs are
assigned by the presentation services program when each resource is activated. Each type of resource has its
own pool of HAIDs that consists of all values between X'0001' and X'7EFF'. Individual HAID pools exist for
each of the following resource types:
• Complete fonts, including the following: [IPDS-3-224]
– Coded fonts (LF1, LF2, LF3)
– Data-object fonts
• Code pages [IPDS-3-225]
• Data-object resources (including IOCA tile resources, PDF resources, Non-OCA Resource objects, color [IPDS-3-226]
management resources, resident color profiles, and non-OCA presentation objects; setup files do not use
HAIDs)
Note: When presentation data objects are downloaded in home state as resources, they become data object
resources and use the data object resource HAID pool. These objects include:
– EPS (Encapsulated PostScript) with transparency
– EPS without transparency
– GIF (Graphics Interchange Format)
– IOCA (Image Object Content Architecture) image
– JPEG (Joint Photographic Experts Group) AFPC JPEG Subset
– JP2 (JPEG2000 File Format)
– PCL (Printer Command Language) page object
– PDF (Portable Document Format) multiple-page file with transparency
– PDF multiple-page file without transparency
– PDF single page with transparency
– PDF single page without transparency
– PNG (Portable Network Graphics) AFPC PNG Subset
– SVG (Scalable Vector Graphics) AFPC SVG Subset
– TIFF (Tag Image File Format) AFPC TIFF Subset
– TIFF with transparency
– TIFF without transparency
– TIFF multiple-image file with transparency
– TIFF multiple-image file without transparency
– UP
3I print data
• Data-object-font components (including TrueType/OpenType fonts and TrueType/OpenType collections) [IPDS-3-227]
• Font character sets [IPDS-3-228]
• Overlays [IPDS-3-229]
• Page segments [IPDS-3-230]


Resource Management
Resource management in the IPDS architecture is based on three classes of function:
• Function related to the physical presence of resources in the printer [IPDS-3-231]
• Function related to the availability of resources in the printer [IPDS-3-232]
• Function related to the invocation of resources in the printer. [IPDS-3-233]
The following sections describe resource management in the IPDS architecture in terms of these three classes
of function.
Physical Presence of Resources
A resource must be physically present in the printer if it is to be used for presentation. There are two types of
resources that can be physically present in a printer, resident resources and downloaded resources. If an
intermediate device that supports resource caching is present between the host and the printer, resident
resources can also be physically present in the intermediate device.
Resident resources can be installed and removed manually by an operator when the printer or intermediate
device is offline to the host. Resident resources can also be installed directly by the printer or intermediate
device without operator intervention using a process called resource capture. The physical presence of
resident resources in the printer or intermediate device is ensured from the time the resource is installed until
the time that it is removed.
Resident resources are assigned one of the defined resource IDs in accordance with a convention that is
known to the printer, to the intermediate device if present, and to the presentation services program. Some
resident resources are assigned the same ID whenever they are installed in any device. For resident coded
font components installed in a printer, the resource ID often used is a GRID. For resident coded font
components, page segments, and overlays installed in an intermediate device, the resource ID normally used
is a GRN. The GRID or GRN assigned to a resident resource is valid for the duration of the resource's
presence in the printer or intermediate device. When a resident resource is to be used in a host-to-printer
session, the assigned resource ID is mapped to a HAID or HARID that is used by the host presentation
services program to refer to that resource and that is valid for the duration of the session.
Downloaded resources are installed in the printer under control of the presentation services program and are
removed when the resource is deactivated. Resource transmission occurs on the link between the
presentation services program and the printer. If the resource is cached in a resource-caching intermediate
device, resource transmission occurs on the link between the intermediate device and the printer. The
existence of an intermediate device in the path between the host and the printer is transparent to the printer.
Downloaded resources remain physically present in the printer until they are removed under control of the host
presentation services program. Downloaded resources are also removed when the printer is reset or taken
offline. When they are installed, downloaded resources are assigned a HAID or HARID that is valid for the
duration of the current host-to-printer session.
A resource can be captured as a resident resource by a printer or a resource-caching intermediate device.
Such a resource is assigned one of the defined resource IDs in accordance with a convention that is known to
the device capturing the resource and to the presentation services program. When a resource is captured and
made resident it is truly a resident resource and assumes the characteristics of a resident resource.
IPDS intermediate devices that provide a resource caching function capture resources and make them
resident if the resources are intended for public use and are designated as capturable by the host presentation
services program. Such resources are downloaded resources in the printer, and all reloading of these
resources is performed out of the cache of the intermediate device over a local link to the printer.
Printers can also have the capability of capturing resources and making them resident resources. As in the
case of an intermediate device with a resource caching function, the resource to be captured and made [IPDS-3-234]


resident must be designated as capturable by a host presentation services program and must be assigned a
resource ID.
The decision as to whether a resource is capturable or not is made by the owner of the resource and by the
presentation services software. This attribute is conveyed to the intermediate device or printer using the don't
capture flag in an AR command.
Table 12summarizes the IPDS commands used by presentation services programs to install and remove
downloaded resources.
Table 12. Installation and Removal of Downloaded Resources
Resources Install Commands Remove Commands
Fully described fonts and font sections LFC, LF DF , XOH ERFD
Font indexes LFI DF , XOH ERFD
Font character sets LFCSC, LF DF , XOH ERFD
Code pages LCPC, LCP DF , XOH ERFD
Symbol-set coded fonts LSS DF , XOH ERFD
Page segments BPS DPS, XOH ERPD
Overlays BO DO, XOH ERPD
Saved page groups XOH DGB with a previous
XOH SGO (Save Pages)
XOH RSPG
Data object resources Home state WOCC plus WOC
Home state WIC2 plus WI2
DDOR, RRR, XOH ERPD
Data-object-font components Home state WOCC plus WOC DDOFC, RRR, XOH ERPD
Since the physical presence of resident resources is, in general, not controlled by the host presentation
services program, and since even the removal of downloaded resources might at times not be controlled by the
presentation services program, the IPDS architecture provides commands that are used to verify the physical
presence of a resource. The XOA Request Resource List (XOA RRL) command with query type X'FF' is a
query for the physical presence of a resource. The parameters sent to the printer in this query are resource
type, resource ID format, and resource ID. The printer generates a response that indicates whether the
resource of specified type with the ID in the specified format is physically present or not. If the ID is left out, the
printer responds with a list of physically present resources of the specified type with IDs in the specified format.
Since not all printers support all resource types and all resource ID formats, the XOA-RRL RT & RIDF Support
self-defining field is defined in the XOH-OPC reply to allow a printer to specify which resource types and
resource ID formats are supported in an XOA-RRL command.
Some IPDS printers support the Request Resident Resource List (RRRL) command; this command returns a
list containing additional information about all resident resources that is useful for managing printer-resident
resources. Support for the Request Resident Resource List command is indicated with property pair X'1205' in
the STM Object Container command-set vector. Remember that for a resource capture to occur, the don't
capture flag must be B'0', but the printer (or IPDS intermediate device) has the final decision about whether or
not to capture a resource. The Request Resident Resource List command can be used to determine whether
or not a particular resource has been captured, but since the list can be very large, the command should not be
used every time a resource capture is authorized (that is, not for each AR entry that specifies B'0' for the don't
capture flag).


Availability of Resources
The process of making a resource available for presentation is called resource activation. Resources are
activated under control of the host presentation services program using IPDS commands. When a resource
has been activated, it is available for use by the host presentation services program. The activation process
consists of two steps:
1. Ensuring that the resource is physically present in a usable form in the printer [IPDS-3-235]
2. Assigning a HAID, HARID, or variable-length group ID to the resource [IPDS-3-236]
The format in which resources are stored in a device and the manner in which resources are made usable is
device dependent and resource dependent. Ensuring that a physically present resource is in a usable form
might involve device-dependent functions such as loading the resource from a hard-disk media or
decompressing the resource data. It might also involve resource-dependent functions such as the rasterization
of outlines in the case of outline coded fonts.
The deactivation process is the inverse of the activation process. The deactivation process changes the state
of a resource to “not activated”. When a resource is not activated, it is either not physically present and usable
in the device, or it does not have a HAID/HARID assigned, or both. A resource that is not activated is not
available for use by host presentation services programs. Resources are deactivated under control of the host
presentation services program using IPDS commands. In addition, all resources are deactivated when the
printer or intermediate device that contains them is reset or taken offline.
Since downloaded resources are assigned a HAID or HARID when they are installed, their activation is
accomplished by the same set of commands as their installation. Similarly, since downloaded resources are
deactivated when they are removed, their deactivation is accomplished by the same set of commands as their
removal. As a result, downloaded resources are activated for the duration of their physical presence in the
printer. Table 13specifies the commands that can be used by presentation services programs to activate and
deactivate a downloaded resource.
Table 13. Activation and Deactivation of Downloaded Resources
Downloaded Resources Activation Commands Deactivation Commands
Fully described fonts and font sections LFC, LF DF , XOH ERFD
Font indexes LFI DF , XOH ERFD
Font character sets LFCSC, LF DF , XOH ERFD
Code pages LCPC, LCP DF , XOH ERFD
Symbol-set coded fonts LSS DF , XOH ERFD
Page segments BPS DPS, XOH ERPD
Overlays BO DO, XOH ERPD
Saved page groups XOH DGB with a previous
XOH SGO (Save Pages)
XOH DSPG
XOH ERPD
XOH RSPG
Data object resources Home state WOCC
Home state WIC2
DDOR, XOH ERPD
Data-object-font components Home state WOCC DDOFC, XOH ERPD
Table 14 specifies the commands that can be used by presentation services programs to request
an activation or a deactivation of a resident resource. An LF3-type coded font is neither downloaded nor
resident and is activated by combining its components, a code page and a font character set, as shown in
Table 14.


Table 14. Activation and Deactivation of Resident Resources, Coded Fonts, and Data-Object Fonts
Resources Activation Commands Deactivation Commands
LF1-type coded fonts LFE DF , LFE Homestate
AR DF , XOH ERFD
AR + XOA RRL DF , XOH ERFD
LF2-type coded fonts LFE DF , LFE Homestate
AR DF , XOH ERFD
AR + XOA RRL DF , XOH ERFD
LF3-type coded fonts LFE DF , LFE Homestate
AR DF , XOH ERFD
AR + XOA RRL DF , XOH ERFD
Font character sets AR DF , XOH ERFD
AR + XOA RRL DF , XOH ERFD
Code pages AR DF , XOH ERFD
AR + XOA RRL DF , XOH ERFD
Saved page groups ISP XOH DSPG
XOH ERPD
XOH RSPG
Data object resources AR DDOR, XOH ERPD
Data-object-font components AR DDOFC, XOH ERPD
Data-object fonts AR DF , XOH ERFD
Table 14shows two methods of activating resident resources using the AR command. The first method
involves activation based solely on the AR command, while the second method is a two-step process involving
both the AR command and the XOA-RRL command. In the latter case the resource activation is not completed
until the device generates a resource activated reply to the XOA-RRL command. Table 14also shows that
resident coded fonts that were activated with an LFE command cannot be deactivated with an XOH-ERFD
command, while resident coded fonts that were activated with an AR command or an AR + XOA-RRL
command sequence are deactivated with an XOH-ERFD command. In addition, an LFE command issued in
home state deactivates all resident coded fonts that were activated by LFE commands, but does not deactivate
resident coded fonts that were activated by AR commands.
All resource activations are requested by the host presentation services program using the specified activation
commands. For all activation commands other than the AR + XOA-RRL command sequence, the resource
activation takes place when the command is accepted and executed without any error indication by the printer
or intermediate device. If the resource cannot be activated immediately, as in the case where it is not physically
present in the printer or intermediate device, the activation request, also called an AR entry, remains pending
at the device until the resource can be activated or until the AR entry is reset. In the case of a resource
activation using the AR + XOA-RRL command sequence, the error-free acceptance and execution of the AR
command is only the first step of the activation process and indicates that an activation request is pending at
the printer or at the intermediate device. The activation is not completed until the second step is completed,
that is the error-free acceptance and execution of the XOA-RRL command by the printer or intermediate
device with the generation of an XOA-RRL reply that indicates resource activated.
Resource activation using only the AR command is used with devices that can activate resources immediately,
such as printers. Resource activation using the AR + XOA-RRL command sequence is used with devices that [IPDS-3-237]


require processing time to activate resources, such as intermediate caching devices that must retrieve a
resource from a storage device and load it into the printer in order to satisfy the activation request.
The IPDS architecture defines a special XOA-RRL query type that can be used when the XOA-RRL command
is issued as part of an AR + XOA-RRL activation sequence. The XOA-RRL command with query type X'05' is a
query for the activation status of a specified resource or resources. This is an optional query type that must be
supported by devices that support the AR command and that activate resources using the AR command or the
AR + XOA-RRL command sequence. The parameters sent to the printer in this query are resource type,
resource ID format, and resource ID. The printer generates a response that indicates whether the resource of
specified type with the ID in the specified format is activated or not. Multiple queries of type X'05' in a single
XOA-RRL command can be sent to a device if supported by the device, however each query must be for an
individual resource and not for a list of resources of a specified type and ID format.
Since not all printers support all resource types and all resource ID formats for resources activated by an AR
command or an AR + XOA-RRL command sequence, the Activate Resource RT & RIDF Support self-defining
field is defined in the XOH-OPC reply to allow a printer or intermediate device to specify which resource types
and resource ID formats are supported in an AR command or an AR + XOA-RRL command sequence. [IPDS-3-238]


Invocation of Resources
The process of including a resource object in another object such as a page is called resource invocation. A
resource object is invoked by its resource ID. The resource must be activated before it is invoked so that the
resource ID can be resolved to the actual resource. The invocation process does not differentiate between
resident and downloaded resources.
In the case of a coded font or data-object font, the command used to invoke the resource depends on whether
the resource is being invoked in text data, in a graphics object, or in a bar code object. However, in all three
cases the resource is referenced by a local ID (LID) and the LID-to-HAID or LID-to-HARID mapping, also
called a font equivalence, must be established by an LFE command. The font equivalence can be established
before the resource is activated or after it is activated. In the case of an LF1-type or LF3-type coded font, the
font equivalence can even be established before the coded font components are physically present in the
printer. In the case of a downloaded symbol-set coded font, the font equivalence must be established before
the symbol set is downloaded.
In the case of an overlay resource, the method of invocation depends on whether the resource is being
invoked as a page overlay, in which case the overlay origin can be positioned anywhere on the current logical
page, or a medium overlay, in which case the overlay origin is positioned at the origin of the medium
presentation space. A preprinted form overlay invoked with the LCC command is positioned like a medium
overlay; a preprinted form overlay invoked with the IO command is positioned like a page overlay.
Table 15 shows the commands that are used to invoke the various resource objects as well as the
IDs used in the invocation. [IPDS-3-239]


Table 15. Invocation of Resources
Resources Invocation Commands Resource ID
Coded fonts: text SCFL Control Sequence LID
Coded fonts: graphics GSCS Drawing Order LID
Coded fonts: IM-Image None None
Coded fonts: IO-Image None None
Coded fonts: bar codes WBCC/BCDD LID
Data-object fonts SCFL (for text)
GSCS (for graphics)
WBCC/BCDD (for bar codes)
LID
Page segments IPS HAID
Overlays: page and PFO IO HAID
Overlays: medium and PFO LCC Overlay ID
Page from a saved page group ISP Variable-length group ID and page
sequence number
Data object resource Data object:
IDO command
HAID on an IDO command
Secondary resource with an
internal resource ID:
Object-specific commands within a
presentation data object
HAID-to-internal-resource-ID equivalence
on a DORE or DORE2
command
Secondary resource with no internal
resource ID:
• IDO
• Page or overlay state WIC2 [IPDS-3-240]
• Page or overlay state WOCC [IPDS-3-241]
HAID on a DORE or DORE2
command
CMR:
• ICMR (home state) [IPDS-3-242]
• IDO (included object) [IPDS-3-243]
• LPD (page) [IPDS-3-244]
• LPD (page overlay) [IPDS-3-245]
• LPD (medium overlay) [IPDS-3-246]
• LPD (preprinted form overlay) [IPDS-3-247]
• RPO (preRIPped object) [IPDS-3-248]
• WBCC (bar code object) [IPDS-3-249]
• WGC (graphics object) [IPDS-3-250]
• WIC2 (IOCA image object) [IPDS-3-251]
• WOCC (data object) [IPDS-3-252]
• WTC (text object) [IPDS-3-253]
HAID from Invoke CMR (X'92') triplet on
an IDO, LPD, RPO, WBCC, WGC, WIC2,
WOCC, or WTC command
HAID from Invoke Tertiary Resource
(X'A2') triplet on a WBCC command
HAID from an Invoke CMR command [IPDS-3-254]


Data Object Resources, Data-Object-Font Components, and Setup Files
The following tables list the currently defined data object resources, data-object-font components, and setup
files for the IPDS environment.
Note: IPDS products do not necessarily support all of the object types listed; refer to the “Object-Container
Type Support Self-Defining Field” for a description of object-support information returned
by IPDS printers.
IO Images used as resources
Table 16. IO Images used as Resources
Registered
Object-Type OID
Object Description Internal Resource
ID
Object
Download State
Object Usage
None IOCA Image
Presentation
Not applicable Home state Page data
Object containers
Refer to the object-type identifiers registry in the Mixed Object Document Content Architecture Reference for
more information about the following registered object-type OIDs.
Notes:
1. Setup files are not treated as resources; when a setup file is downloaded, the HAID value in the WOCC [IPDS-3-255]
command is ignored. Setup files take effect immediately and cannot be deactivated or queried. The
DORE, DORE2, and IDO commands are not used with a setup file.
2. Metadata objects are treated as object containers in MO:DCA, but are not valid object containers in [IPDS-3-256]
IPDS. The Metadata Command Set is used in IPDS to associate metadata objects with other IPDS
objects; see Chapter 11, “Metadata Command Set ”.
Table 17. Object Containers Used in the IPDS Environment
Registered
Object-Type OID
Object Description Internal Resource
ID
Object
Download State
Object Usage
X'0607 2B12
0004 0101
0F00 0000
0000 0000'
Anacomp COM Setup File
Non-presentation
Not applicable Home state Setup file
X'0607 2B12
0004 0101
1000 0000
0000 0000'
Anacomp COM Tape Label
Setup File
Non-presentation
Not applicable Home state Setup file
X'0607 2B12
0004 0101
1800 0000
0000 0000'
AnaStack Record Setup File
Non-presentation
Not applicable Home state Setup file
X'0607 2B12
0004 0101
3900 0000
0000 0000'
Color Management
Non-presentation
Not applicable Home state Color management [IPDS-3-257]


Table 17 Object Containers Used in the IPDS Environment (cont'd.)
Registered
Object-Type OID
Object Description Internal Resource
ID
Object
Download State
Object Usage
X'0607 2B12
0004 0101
1400 0000
0000 0000'
Color Mapping Table Setup File
Non-presentation
Not applicable Home state Setup file
X'0607 2B12
0004 0101
0D00 0000
0000 0000'
EPS (Encapsulated PostScript)
without transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-258]
Home state
Page data
X'0607 2B12
0004 0101
3000 0000
0000 0000'
EPS (Encapsulated PostScript)
with transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-259]
Home state
Page data
X'0607 2B12
0004 0101
1600 0000
0000 0000'
GIF (Graphics Interchange
Format)
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-260]
Home state
Page data
X'0607 2B12
0004 0101
2F00 0000
0000 0000'
IOCA Tile Resource
Non-presentation
From DORE or
DORE2
command
Home state IOCA
resource
X'0607 2B12
0004 0101
1700 0000
0000 0000'
JPEG (Joint Photographic Experts
Group) AFPC JPEG Subset
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-261]
Home state
Page data
X'0607 2B12
0004 0101
3A00 0000
0000 0000'
JP2 (JPEG2000 File Format)
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-262]
Home state
Page data
X'0607 2B12
0004 0101
4500 0000
0000 0000'
Non-OCA Resource object
Non-presentation
From DORE or
DORE2
command
Home state Non-OCA resource
X'0607 2B12
0004 0101
2200 0000
0000 0000'
PCL page object
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-263]
Home state
Page data
X'0607 2B12
0004 0101
3F00 0000
0000 0000'
PDF multiple-page file
without transparency
Presentation
From Object Offset
(X'5A') triplet
Page state
Overlay state
----- or ----- [IPDS-3-264]
Home state
Page data
X'0607 2B12
0004 0101
4000 0000
0000 0000'
PDF multiple-page file
with transparency
Presentation
From Object Offset
(X'5A') triplet
Page state
Overlay state
----- or ----- [IPDS-3-265]
Home state
Page data
X'0607 2B12
0004 0101
1900 0000
0000 0000'
PDF single page
without transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-266]
Home state
Page data


Table 17 Object Containers Used in the IPDS Environment (cont'd.)
Registered
Object-Type OID
Object Description Internal Resource
ID
Object
Download State
Object Usage
X'0607 2B12
0004 0101
3100 0000
0000 0000'
PDF single page
with transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-267]
Home state
Page data
X'0607 2B12
0004 0101
1A00 0000
0000 0000'
PDF (Portable Document Format)
resource object
Non-presentation
From DORE or
DORE2
command
Home state PDF resource
X'0607 2B12
0004 0101
4100 0000
0000 0000'
PNG (Portable Network Graphics)
AFPC PNG Subset
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-268]
Home state
Page data
X'0607 2B12
0004 0101
2E00 0000
0000 0000'
Resident color profile
Non-presentation
Not applicable Home state Color
management
X'0607 2B12
0004 0101
4400 0000
0000 0000'
SVG (Scalable Vector Graphics)
AFPC SVG Subset
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-269]
Home state
Page data
X'0607 2B12
0004 0101
4200 0000
0000 0000'
TIFF (Tag Image File Format)
AFPC TIFF Subset
Presentation
From Object Offset
(X'5A') triplet
Page state
Overlay state
----- or ----- [IPDS-3-270]
Home state
Page data
X'0607 2B12
0004 0101
0E00 0000
0000 0000'
TIFF (Tag Image File Format)
with transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-271]
Home state
Page data
X'0607 2B12
0004 0101
3C00 0000
0000 0000'
TIFF (Tag Image File Format)
without transparency
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-272]
Home state
Page data
X'0607 2B12
0004 0101
3D00 0000
0000 0000'
TIFF multiple-image file
with transparency
Presentation
From Object Offset
(X'5A') triplet
Page state
Overlay state
----- or ----- [IPDS-3-273]
Home state
Page data
X'0607 2B12
0004 0101
3E00 0000
0000 0000'
TIFF multiple-image file
without transparency
Presentation
From Object Offset
(X'5A') triplet
Page state
Overlay state
----- or ----- [IPDS-3-274]
Home state
Page data
X'0607 2B12
0004 0101
3300 0000
0000 0000'
TrueType/OpenType Font
Non-presentation
Not applicable Home state Font resource
X'0607 2B12
0004 0101
3500 0000
0000 0000'
TrueType/OpenType Collection
Non-presentation
Not applicable Home state Font resource [IPDS-3-275]


Table 17 Object Containers Used in the IPDS Environment (cont'd.)
Registered
Object-Type OID
Object Description Internal Resource
ID
Object
Download State
Object Usage
X'0607 2B12
0004 0101
3800 0000
0000 0000'
UP3I Print Data
Presentation
Not applicable Page state
Overlay state
----- or ----- [IPDS-3-276]
Home state
Pre-processor or
post-processor
printing
Note: The last non-zero bytes of the registered object-type OID uniquely identifies the object type. Refer to the object-
type identifiers registry in the Mixed Object Document Content Architecture Reference for more information about
these registered object-type OIDs.
Retired Object-Type OIDs
X'0607 2B12
0004 0101
2500 0000
0000 0000'
Retired item 136
X'0607 2B12
0004 0101
2400 0000
0000 0000'
Retired item 137
X'0607 2B12
0004 0101
2600 0000
0000 0000'
Retired item 138


Multi-Page Resource Objects
A multi-page resource object is a type of object container consisting of a file that contains multiple pages or
paginated objects for presentation. Such an object is appropriately characterized in the MO:DCA Object
Registry. That is, it is registered with an object-type OID that identifies it as a file that may contain multiple
pages or paginated objects.
When a multi-page file is referenced in the data stream with an Include Data Object (IDO), the reference
selects a single paginated object within the file for presentation. Similarly, when a multi-page file is carried in
WOC commands specified directly within a page or overlay, the WOCC command selects a single paginated
object within the file for presentation. Examples of multi-page resource objects are PDF files that contain
multiple pages and TIFF files that contain multiple paginated image objects.
For information on which multi-page resource objects can use secondary resources, refer to Table 51.
Multi-page resource objects can be preRIPed using the Rasterize Presentation Object (RPO) command. A
single page or image in the multi-page resource object can be selected for preRIP or all of the pages or images
in the file can be preRIPed.
Note: A printer implementation can choose to support multi-page objects in home state or in page-or-overlay
state (or in all three states). Refer to the “Object-Container Type Support Self-Defining Field” in the XOH-OPC reply for more information. [IPDS-3-277]


Pre-Rasterizing and Caching Presentation Objects
Some IPDS printers support the Rasterize Presentation Object (RPO) command, that is a home state
command that requests that a previously activated presentation data object resource or overlay be rasterized
and cached using the specific orientation and presentation-object overrides specified in the command. The
presentation object is processed as if it had been included directly in a page or overlay by means of an IDO or
IO command; however, part of the environment is specified within the RPO command and the result is cached
but not printed. This command is used to improve print performance by rasterizing one or more variations of a
presentation object before those variations are actually included in a page or overlay. This is especially useful
when the presentation object is complex or is included multiple times in a print job.
The RPO command can be used to rasterize any of the following types of presentation object:
• EPS (Encapsulated PostScript) with transparency [IPDS-3-278]
• EPS without transparency [IPDS-3-279]
• GIF (Graphics Interchange Format) [IPDS-3-280]
• IOCA (Image Object Content Architecture) image [IPDS-3-281]
• JPEG (Joint Photographic Experts Group) AFPC JPEG Subset [IPDS-3-282]
• JP2 (JPEG2000 File Format) [IPDS-3-283]
• Overlay [IPDS-3-284]
• PCL (Printer Command Language) page object [IPDS-3-285]
• PDF (Portable Document Format) multiple-page file with transparency [IPDS-3-286]
• PDF multiple-page file without transparency [IPDS-3-287]
• PDF single page with transparency [IPDS-3-288]
• PDF single page without transparency [IPDS-3-289]
• PNG (Portable Network Graphics) AFPC PNG Subset [IPDS-3-290]
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-3-291]
• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-3-292]
• TIFF with transparency [IPDS-3-293]
• TIFF without transparency [IPDS-3-294]
• TIFF multiple-image file with transparency [IPDS-3-295]
• TIFF multiple-image file without transparency [IPDS-3-296]


Saving and Including Pages
Some IPDS printers allow a group of pages to be saved; each saved page can later be included in a page to be
printed. This function is useful when printing multiple, collated copies of a document. By including a saved
page within a page to be printed, the printed page can add information to the saved page. Often there is also a
performance improvement when printing multiple copies of a document.
Figure 46 contains a sequence of IPDS commands that demonstrate command interactions when
pages are being saved and included. The figure shows both valid and error situations and does not necessarily
demonstrate an optimal data stream. [IPDS-3-297]


Figure 46. Examples of IPDS Commands Involved with Saving and Including Pages
XOH Specify Group Operation command
- operation = Save Pages [IPDS-3-298]
- group level = X'08' [IPDS-3-299]
XOH Define Group Boundary command
XOH Define Group Boundary command
- initiate group [IPDS-3-300]
- terminate group [IPDS-3-301]
- group level = X'08' [IPDS-3-302]
- group level = X'08' [IPDS-3-303]
- variable length group ID = document one [IPDS-3-304]
- variable length group ID = document one [IPDS-3-305]
Logical Page Descriptor command
Load Copy Control command
Logical Page Position command
Logical Page Descriptor command
Load Copy Control command
Logical Page Position command
Begin Page command
Begin Page command
Begin Page command
End Page command
End Page command
End Page command
..
.
..
.
page data
.
.
.
..
.
page data
.
.
.
..
.
page data
.
.
.
..
.
page data
.
.
.
..
.
page data
.
.
.
..
.
page data
}
}
}
this page is saved and
assigned sequence number
X'00000001'
this page is saved
and assigned
sequence number
X'00000002'
this page is printed
using the most recently
received LPD, LCC,
and LPP commands
Include Saved Page command  }
ERROR:  this command is
invalid in home state
(X'8002..00')
}
}
this command isn't used
while saving a page
this command isn't used
while saving a pageInclude Saved Page
Include Saved Page command
Include Saved Page command
}
}
}
ERROR:  this
command is not
allowed while
saving pages
(X'0255..05')
saved page
X'00000001'
is included here
ERROR:  only one
ISP command is
allowed in a page
(X'0255..04')
}
this group of
pages is saved
-group id = document one
-sequence number = X'00000001' [IPDS-3-306]


Relationship Between FOCA Character Metrics and TrueType Character
Metrics
The IPDS Architecture supports multiple font formats and technologies and it is important to have consistent
printing results regardless of the font technology used. The FOCA architecture defines the basic concepts and
provides a rich set of font and character metrics; these FOCA concepts lay out the presentation goals. The
PTOCA architecture provides the capability to present strings of text at various orientations as shown in Figure
116. This overview section describes the relationship between various TrueType metrics and the [IPDS-3-307]
corresponding FOCA-defined metrics and provides recommendations for simulating metrics that are needed
for presentation but are not directly provided in some TrueType fonts.
Horizontal Metrics
When a TrueType rasterizer RIPs the outline descriptions into character bitmaps, TrueType metrics are
provided for positioning the bitmaps horizontally within a line of text. These metric values provide enough
information to calculate the metrics defined by FOCA for the 0° character rotation. This information includes
the width and depth of the bitmap, the distance from the character origin to a corner of the bitmap, and the
distance to the origin of the next character.
Figure 47 compares the parameters commonly used with TrueType fonts to the horizontal (0°) metrics
provided by a FOCA font. In practice, many TrueType fonts are built so that there is no top indent or left indent;
in this case, the bitmap is a tight box around the character and the indent values are zero.
Note: The ideographic character shown in Figure 47 and Figure 48 means “beauty”.
Figure 47. TrueType Font Horizontal Metrics
TrueType Horizontal Metrics
0  character rotation [IPDS-3-308]
o
Y Origin
Character Origin
Escapement
X Origin
Black Width
Bitmap Width
Left Indent
Black Depth
Top Indent
Character
Bitmap
Baseline Extent
Character
Origin
Baseline Offset
Ascender
Descender
B-space
Character Increment
A-space
C-space
FOCA Horizontal Metrics
0  inline direction, 0  character rotation [IPDS-3-309]
o o


Based on this illustration, the key FOCA horizontal metrics can be calculated as follows:
Character increment (HCI) = Escapement
A-space (HAS) = Left indent - X origin
B-space (HBS) = Black width
C-space (HCS) = Escapement - A-space - B-space
Baseline extent (HBE) = Black depth
Baseline offset (HBO) = Y origin - Top indent
Character descender (HCD) = Top indent + Black depth - Y origin
The FOCA metrics for 180° (upside down) rotation have a simple relationship to those for 0° rotation. The A-
space and the C-space metrics are reversed, as are the baseline offset and character descender metrics. The
character increment, B-space, and baseline extent metrics are identical.
Note: In practice, font rasterizers don't provide all of the parameters shown in the picture, but do provide other
parameters. For example, the font rasterizer can return the offset (xorigin, yorigin) from the character
origin of the top-left corner of the bitmap. This information can be related to the metrics formulas; for
example:
A-space (HAS) = Left indent - X origin = Left indent + xorigin
Baseline Offset (HBO) = Y origin - Top indent = yorigin - Top indent
Vertical Metrics
Character rotations of 90° and 270° are used to support vertical forms of writing. In addition to the metrics
mentioned earlier, vertical positioning and character increment metrics are needed to place characters in these
rotations. Some TrueType fonts provide metrics for vertical writing in a structure called a vtmx table, but others
don't provide these metrics. The TrueType advance height corresponds to the FOCA vertical character
increment (VCI) and the TrueType top side bearing corresponds to the FOCA vertical A-space (VAS), but there
is no TrueType metric that corresponds to the FOCA baseline offset.
When the vtmx metrics are available they can be used to calculate the equivalent FOCA vertical metrics. But,
when the font designer omitted them or when they can't be obtained from the TrueType rasterizer, a method is
needed to estimate appropriate FOCA equivalent values. [IPDS-3-310]


Simulating Vertical Metrics
Figure 48 shows again the TrueType horizontal metrics and some additional TrueType metrics that can be
obtained to describe the em-square. The figure also shows the target FOCA vertical metrics and a method for
simulating 270° FOCA vertical metrics from TrueType horizontal metrics.
Figure 48. TrueType Font Vertical Metrics
FOCA Vertical Metrics
90  inline direction, 270  character rotation [IPDS-3-311]
o o
Vertical Character
Positioning Point
B-space
Character Increment
C-space
A-space
Horizontal
Character
Origin
Baseline Extent
Baseline OffsetDescender Ascender
Character Increment  =  em
A-space  =
B-space  =  Black Depth
C-space  =  em - A-space - B-space
Baseline Extent  =  Black Width
Baseline Offset  =  Left Indent - X Origin +
Black Width - int(Escapement/2)
Character Descender  =    X Origin -
int((em - (urY - llY))/2) + urY - Y Origin
Left Indent +
int(Escapement/2)
TrueType Horizontal Metrics
0  character rotation [IPDS-3-312]
o
Y Origin
Character Origin
Escapement
X Origin
Black Width
Bitmap Width
Left Indent
Black Depth
Top Indent
Character
Bitmap
Character
Origin
em
Bounding box that approximates
the union of the individual
character black boxes
urY
llY
ll
ur
em
TrueType em-Square
Note: The equation for vertical A-space was derived
from the following formulas that are similar to
those used for Adobe Type 1 and CID-Keyed
fonts:
V AS = Vy - Y Origin
Vy = int((em - maxHBE)/2) + maxHBO
Method for Simulating Vertical Metrics
Any approach taken to approximate these metrics is well served to consider the scripts in which vertical writing
is most popular: East Asian scripts that use ideographic characters. These full width characters have
properties that can be utilized to make these estimations. First, they typically have an equal, or fixed,
increment. Second, they are designed on a square grid, so their width and height are equal. Third, they are
usually are the largest characters in the font. [IPDS-3-313]


For these reasons, using a fixed vertical character increment (VCI) equal to the largest horizontal increment is
quite satisfactory for vertical writing. Generally, the maximum values for many basic metrics, such as character
increment, descender, and baseline offset can be obtained from the font file. Alternatively, the properties listed
previously make it reasonable to set VCI to the Em-Space Increment. The Em-space is defined such that one
em equals the height of the design space. Scalable font metrics are expressed as fractions of this unit-Em.
These alternatives can be summarized mathematically as:
Character Increment (VCIestimated) = max(Escapement)
— or —
Character Increment (VCIestimated) = 1 em
Techniques to estimate appropriate values for VAS must keep two goals in mind. First, it should result in the
bitmaps of ideographic characters being placed within the vertical increment. Second, the vertical position of
the bitmap should reflect the relative horizontal baseline offset of the character. For example, the bitmap widths
for the BLACK LENTICULAR BRACKETS, U+3010 and U+3011, are small compared to their increment and
are designed to be positioned close to the character they enclose. This property must be preserved for vertical
writing.
To accomplish these goals, first compute a constant value (Vy) to place the horizontal character origin relative
to the vertical character positioning point, using the TrueType em-square metrics and the following equation
(note that max(HBE) = urY + llY and max(HBO) = urY):
Vy(est) = int((em - max(HBE))/2) + max(HBO)
The first component of this equation, int((em - max(HBE))/2), is designed to position all of the character
bitmaps of the font within the vertical increment. The second component, max(HBO), calibrates the V Origin
metric to the highest character(s) within the font. With this reference, then calculate VAS for individual
characters with the equation:
VASestimated = Vy(est) - Y Origin
and achieve the design goals.
For fonts that are not based on ideographic characters, a different method of constructing a vertical character
increment and A-space could be used. For example, a fixed percentage (20%) of extra space, based on the
desired pointsize, could be added to the black depth to yield the VCIestimated. The extra space could be
divided evenly between the vertical A-space and vertical C-space. For characters without any black depth
(space characters), the pointsize could be used as VCIestimated.
The last task to address is estimating the horizontal position of the character bitmap. For vertical rotations, this
is reflected in the baseline offset (VBO) and character descender (VCD) metrics. Similar to the goal for vertical
positions, this metric should reflect the character's horizontal position within its horizontal increment.
Therefore, the metric calculations should essentially center the character's horizontal increment on the
baseline and preserve its horizontal position with respect to the increment. This is achieved with the equations:
Baseline Offset (VBO) = Left Indent - X Origin + Black Width - int(Escapement/2)
Character Descender (VCD) = X Origin - Left Indent + int(Escapement/2)
The remaining metrics for 270° character rotation can be calculated from the horizontal bitmap metrics and
those derived previously:
Baseline Extent (VBE) = Black Width
B-space (VBS) = Black Depth
C-space (VCS) = VCI - VAS - Black Depth
The vertical metrics for 90° character rotation can be directly deduced from the 270° metrics, in the same
manner used to convert 0° metrics to 180° metrics. [IPDS-3-314]


Resource Management Summary
The following table summarizes the important resource management attributes of various IPDS resources:
Table 18. Resource Management Summary
Resource Identification
Physical
Presence Activation Deactivation Invocation
Fully Described Font HAID, GRID, or GRN Resident,
downloaded,
captured
AR, LFC DF ,
XOH ERFD
Not invoked
Font Index HAID, GRID, or
GRN
plus section ID
Resident,
downloaded,
captured
AR, LFC, LFI DF ,
XOH ERFD
Not invoked
Symbol Set HAID or GRID Resident,
downloaded
AR, LFE,
LSS
DF ,
XOH ERFD
SCFL, GSCS,
WBCC-BCDD
Font Character Set HAID or GRID
plus
time stamp triplet
Resident,
downloaded,
captured
AR, LFCSC DF ,
XOH ERFD
Not invoked
Code Page HAID or GRID
plus
time stamp triplet
Resident,
downloaded,
captured
AR, LCPC DF ,
XOH ERFD
Not invoked
Coded Font HAID or GRID
plus coded font
format
Not a physical
object
AR, LFE DF ,
XOH ERFD
SCFL, GSCS,
WBCC-BCDD
Data-Object Font HAID plus
data-object-font
format
Not a physical
object
AR DF ,
XOH ERFD
SCFL, GSCS,
WBCC-BCDD
Page Segment HAID Downloaded BPS DPS,
XOH ERPD
IPS
Overlay HAID Downloaded BO DO,
XOH ERPD
IO, LCC
Saved Page Group Variable-length
group ID
Resident,
downloaded
XOH DGB
plus
XOH SGO,
ISP
XOH DSPG,
XOH ERPD,
XOH RSPG
ISP
Data Object Resource
(including IO-Image)
HAID or
OID for resident or
captured resources
Resident,
downloaded,
captured
AR
Home state
WIC2
Home state
WOCC
DDOR,
XOH ERPD
See Table 15
Data-Object-Font
Component
HAID or
OID for resident or
captured resources
Resident,
downloaded,
captured
AR
Home state
WOCC
DDOFC,
XOH ERPD
Not invoked


Metadata
Metadata is data associated with one or more objects in the IPDS data stream. That is, it is data about objects
in the data stream, not an essential part of the data stream. As such, metadata does not affect the actual
presentation of objects on printed pages. Printers, then, might not be interested in metadata and might not
support it. Transforming applications, however, that take IPDS as input and transform to some other output
format, might be interested in including the metadata into the transformed output.
Each piece of metadata in the IPDS data stream is a metadata object (MO), defined by the Metadata Object
Content Architecture Reference.
The location of the metadata in the data stream determines which object or objects it is associated with. The
following are the cases, listing the IPDS state in which the Write Metadata Control (WMC) command is
received:
Home state When received in home state, the metadata is associated with all pages that follow, all pages
included within those pages using the Include Saved Page command (but not any objects or
overlays that were part of the included pages), all overlays (including PFOs) included within
the pages, all medium overlays (including PFOs) used with the pages, all non-IM-Image
presentation objects contained in page segments included within the pages and overlays, and
all non-IM-Image presentation objects contained or included in the pages and overlays.
However, when received, the metadata is assigned a level in the WMC that passes the
metadata, and this level value can be used to delete the metadata, using the Delete Home-
State Metadata (DHM) command. Once deleted, the metadata will no longer be associated
with any IPDS objects.
The WMC command for the metadata can be received in home state at any time. Home-state
metadata cannot be deleted when the IPDS receiver is in some other state.
All home-state metadata is deleted if the printer is reinitialized (returns an IML NACK).
All metadata objects received in home state are equal, in that the current “collection” of home-
state metadata is simply all MOs received (and not yet deleted) in home state, no matter the
level they were assigned when they were received, or the order in which they were received.
Page state When received in page state, the metadata is associated with the entire page, including any
page included within the page using the Include Saved Page command (but not any objects or
overlays that were part of the included page), all overlays (including PFOs) included within the
page, all non-IM-Image presentation objects contained in page segments included within the
page and overlays, and all non-IM-Image presentation objects contained or included in the
page and overlays. The metadata is not associated with medium overlays (including PFOs)
used with the page.
The WMC command for the first metadata object must be received immediately following the
Begin Page (BP) command that causes the IPDS receiver to enter page state. Multiple
WMC ... End sequences can be received immediately following the BP , with one metadata
object received with each WMC, but once another non-Anystate command is received, no
more WMCs can be received to cause metadata to be associated with the page.
For such metadata, all associations are lost when the End Page (EP) command is received for
the page or when the printer is reinitialized (returns an IML NACK).
Overlay state When received in overlay state, the metadata is associated with the entire overlay, including
all overlays (including PFOs) included within the overlay, all non-IM-Image presentation
objects contained in page segments included within any of the overlays, and all non-IM-Image
presentation objects contained or included in any of the overlays.
The WMC command for the first metadata object must be received immediately following the
Begin Overlay (BO) command that causes the IPDS receiver to enter overlay state. Multiple
WMC ... End sequences can be received immediately following the BO, with one metadata [IPDS-3-315]


object received with each WMC, but once another non-Anystate command is received, no
more WMCs can be received to cause metadata to be associated with the overlay.
For such metadata, all associations are lost when the End Page (EP) command is received for
the overlay or when the printer is reinitialized (returns an IML NACK).
Other states All other states in which metadata can be received correspond to states where a single object
is being processed, such as a graphics object. These are presentation-object states and
related states like IO-Image resource state and object-container resource
state.
When received in such a state, the metadata is associated only with the object corresponding
to the state. For example, when received in graphics state, the metadata is associated with the
graphics object.
The WMC command for the first metadata object must be received immediately following the
“begin” command for the object. Multiple WMC ... End sequences can be received
immediately following the begin command, with one metadata object passed with each WMC,
but once another non-Anystate command is received, no more WMCs can be received to
cause metadata to be associated with the object. The following are the begin commands for
each state:
State Begin Command
(Page or Overlay) Text state Write Text Control (WTC)
(Page, Page Segment, or Overlay) Graphics state Write Graphics Control (WGC)
(Page, Page Segment, or Overlay) IO-Image state Write Image Control 2 (WIC2)
(Page, Page Segment, or Overlay) Bar Code state Write Bar Code Control (WBCC)
(Page or Overlay) Object Container state Write Object Container Control (WOCC)
IO-Image Resource state Write Image Control 2 (WIC2)
Object-Container Resource state Write Object Container Control (WOCC)
For such metadata, all associations are lost when the End command is received for the object
or when the printer is reinitialized (returns an IML NACK).
Looking at metadata associations from the perspective of the objects:
Page When the Begin Page (BP) is processed for a page, the following is the metadata
associated with the page:
• All metadata immediately after the BP [IPDS-3-316]
• All current home-state metadata [IPDS-3-317]
All metadata associations for the page are lost when the End Page (EP) for the page
is processed.
Overlay Between the Begin Overlay (BO) and End Page (EP) commands, which delimit the
definition of the overlay, any WMCs are saved with the overlay definition.
When the overlay is later included on a page, the following is the metadata associated
with the overlay:
• All metadata immediately after the BO in the overlay definition [IPDS-3-318]
• All current home-state metadata [IPDS-3-319]
• All metadata associated with the page on which the overlay is being included [IPDS-3-320]
• If the overlay is included in another overlay, all metadata associated with the [IPDS-3-321]
including overlay


All metadata associations for the overlay are lost when the End Page (EP) in the
overlay definition is processed.
Presentation objects
as resources
This item is discussing presentation objects that are received in either a Write Object
Container Control (WOCC) or Write Image Control 2 (WIC2) in home state. Such
objects have their definitions saved, and any WMCs between the WOCC/WIC2 and
the End command are saved with the object definition.
When the object is later included on a page, the following is the metadata associated
with the object:
• All metadata immediately after the WOCC/WIC2 in the object definition [IPDS-3-322]
• All current home-state metadata [IPDS-3-323]
• All metadata associated with the page on which the object is being included [IPDS-3-324]
• If the object is included in an overlay, all metadata associated with the including [IPDS-3-325]
overlay
All metadata associations for the object are lost when the End in the object definition
is processed.
Presentation objects
in overlays or page
segments
This item is discussing presentation objects that are received in overlay state or page
segment state. The commands that make up the object, including any WMCs, are
saved in the definition of the overlay or page segment.
When the overlay or page segment is later included on a page, the following is the
metadata associated with the object:
• All metadata immediately after the “begin” command in the object definition [IPDS-3-326]
• All current home-state metadata [IPDS-3-327]
• All metadata associated with the page on which the object is being included [IPDS-3-328]
• If included in an overlay, all metadata associated with the including overlay [IPDS-3-329]
All metadata associations for the object are lost when the End in the object definition
is processed.
Presentation objects
in pages
This item is discussing presentation objects that are received in page state.
When the “begin” command for the object is processed, the following is the metadata
associated with the object:
• All metadata immediately after the begin command for the object [IPDS-3-330]
• All current home-state metadata [IPDS-3-331]
• All metadata associated with the page [IPDS-3-332]
All metadata associations for the object are lost when the End is processed.
There is no implied hierarchy to metadata objects; for example, an MO received in object state does not
“override” an MO received in home state. Instead, all MOs are additive only: each new MO only adds to the
metadata associated with some object or objects. For example, if an MO called MO1 is received in home state,
then another MO called MO2 is received in graphics state, then both MO1 and MO2 are associated with the
graphics object in question, with no implied order or precedence between the two MOs. Similarly, if MO3 is
received in page state then MO4 is received in page state for the same page, then both MO3 and MO4 are
associated with the page, with no implied order or precedence between the two MOs.
To avoid unwanted metadata associations, it is recommended that metadata objects be located at the most
appropriate place in the data stream. For example, metadata specific to an IO-image object should be passed
while in IO-Image state for the object, rather than, for example, in Page state for the page that contains the
object.


Exception Handling
Using IPDS commands, the host can control the level of exception handling. For example, the host can request
the printer to produce pages exactly-as-requested. Another level of exception handling that the host can
request is the level that produces the best-possible output.
Pages Exactly-As-Requested
If the host requests this level of exception handling, the printer attempts to prevent any page with a data-
stream exception from printing. This level of exception handling is desirable, for example, when stock
certificates or checks are being printed.
Best-Possible Output
If the host requests this level of exception handling and an exception occurs, the printer attempts to continue
printing as much valid data as possible by either skipping or clearly marking invalid data.
This level of exception handling is desirable, for example, when a draft of a document is needed immediately
and there is no time to verify that all the document resources are available. You can also use this level of
exception handling to debug documents. [IPDS-3-333]


Exception-Handling Combinations
The two most common exception-handling scenarios are 1) where the user wants the pages printed exactly as
requested and 2) where the user accepts the printer's best-possible output. The IPDS architecture provides a
variety of other possibilities, some of which are shown in Figure 49. Refer to “XOA Exception-Handling Control” for a description of the terms used in the IPDS view.
Figure 49. Some Exception-Handling Combinations
User View
* Pages exactly [IPDS-3-334]
as requested
* Report [IPDS-3-335]
exceptions
* Best
possible
output
* Do not report [IPDS-3-336]
exceptions
for which
alternate
exception
actions have
been taken
* Best
possible
output
* Report [IPDS-3-337]
exceptions
* No AEA [IPDS-3-338]
* EPP off [IPDS-3-339]
* No
continuation
* AEA
* EPP on [IPDS-3-340]
* Continuation [IPDS-3-341]
* Do not report [IPDS-3-342]
exceptions
* AEA
* EPP on [IPDS-3-343]
* Continuation [IPDS-3-344]
* Report [IPDS-3-345]
exceptions
IPDS View


Exception-Handling Control
A data-stream exception exists when the printer detects an invalid or unsupported command, control, or
parameter value in the data stream received from the host. The IPDS architecture assigns a unique exception
code to each exception. The printer sends these codes to the host, as sense bytes, in the negative
Acknowledge Reply (NACK). For a description of exception codes, refer to Chapter 16, “Exception Reporting”.
The host controls how the printer responds to exceptions. The Exception-Handling Control order of the
Execute Order Anystate (XOA) command permits independent control over these exception-handling
functions:
• The reporting or suppressing of three types of exceptions: undefined-character checks, page-position [IPDS-3-346]
checks, and other exceptions with AEAs.
• The implementation of an Alternate Exception Action (AEA) when a valid request is received but not [IPDS-3-347]
supported by the printer
• The termination or continuation procedure to follow if the AEA is not to be taken, or if no AEA is associated [IPDS-3-348]
with the exception
• For some printers, the highlighting of position-check exceptions when a Page-Continuation Action (PCA) is [IPDS-3-349]
not taken
• For some printers, the use of Exception Page Print (EPP) to control printing of page information when the [IPDS-3-350]
printer detects a data-stream exception.
The host presentation services can use the XOA Exception-Handling Control command to accomplish many
specific control capabilities necessary in data-printing environments. For example, through the proper
selection of settings, it is possible to do the following:
• Control printing of page information when the printer detects a data-stream exception [IPDS-3-351]
• Enable automatic skipping of data types not supported by the printer [IPDS-3-352]
• Suppress the return of exception reports to the host when alternate actions are acceptable to the user and no [IPDS-3-353]
end-user messages are required
The host can issue the XOA Exception-Handling Control command in any printer state. [IPDS-3-354]


Presentation Fidelity Control
Even finer control can be specified for certain presentation functions that a device is incapable of performing,
such as color, finishing, and unsupported text controls. For these functions, the Presentation Fidelity Control
command can be used to specify:
• Whether printing should continue when an exception is detected [IPDS-3-355]
• Whether an exception should be reported [IPDS-3-356]
• For color exceptions, what type of color substitution is permitted [IPDS-3-357]
Some color printers provide an option to save color toner by substituting some black toner for equal amounts of
cyan, magenta, and yellow toner. This function can save the cost of color toner at the expense of a degraded
color and degraded performance. The Toner Saver (X'74') triplet can be used to activate or deactivate the toner
saver function.


Color Simulation Guidelines
The IPDS architecture provides methods to select from a rich set of color values, but not all colors can actually
be printed. For example, each color printer has a specific range of colors that can be produced (called the
printer's color gamut); black-only printers can print only black. When an unsupported color value is specified
and color fidelity directs the printer to continue, the IPDS printer simulates the requested color with a supported
color; simulation is inherently device dependent. To help maintain consistency among IPDS printers, the
following guidelines describe how IPDS printers should simulate unsupported color values.
Printers that support Color Management Resources (CMRs) use the CMR-usage hierarchy instead of the color
simulation guidelines that follow.
In the following description, the terms “black-only printer” and “grayscale” are used because that is the most
common case with single-color printers. However, the same simulation description applies to single-color
printers where the single color is something other than black. For example, if the printer supports only a blue
ink, the following paragraphs can be changed to read blue wherever black is mentioned, bluescale wherever
grayscale is mentioned, and blue wherever gray is mentioned.
Simulating Out-of-Gamut Colors
Color printers simulate out-of-gamut color values by mapping the requested color to a similar color that is in the
printer's color gamut. The range of colors that can be specified and fall within the printer's gamut can vary by
color space.
Simulating Colors on a Black-Only Printer without Grayscale Capability
Some black-only printers simulate valid but unsupported color values by either substituting black, substituting
color of medium (an erase color), or by creating a pattern that varies by color value. This technique is used by
older IPDS printers and may be supported as a migration mode by new devices.
For example, when an entire object area or logical page is to be colored, color of medium is substituted for a
requested (but unsupported) color.
GOCA areas can be filled with a predefined pattern to which a color is applied; unsupported color values are
simulated by varying the pattern so that each pattern/color combination produces a slightly different look.
When a GOCA pattern is solid fill, color simulation looks a lot like grayscale.
All other types of data are simulated with black. For example, a colored line, arc, character, underscore,
overstrike, text rule, bilevel image, marker, or bar code symbol is printed as if black had been specified.
Simulating Colors with Grayscale
Some black-only printers support grayscale and can produce a gray value to simulate an unsupported color
value. All new black-only IPDS printers are required to support this form of color simulation. Property pair
X'F004' in the Device-Control command-set vector of an STM reply indicates that the printer supports
grayscale simulation.
GOCA markers and BCOCA bar code symbols and HRI are simulated with black. Data types that are
simulated with grayscale include:
• Bilevel IOCA (Image Object Content Architecture) image [IPDS-3-358]
• EPS (Encapsulated PostScript) with transparency [IPDS-3-359]
• EPS without transparency [IPDS-3-360]
• GIF (Graphics Interchange Format) [IPDS-3-361]
• Graphics (characters, lines, arcs, image, solid-area fill, pattern fill) [IPDS-3-362]


• IM Image [IPDS-3-363]
• JPEG (Joint Photographic Experts Group) AFPC JPEG Subset [IPDS-3-364]
• JPEG2000 File Format (JP2) [IPDS-3-365]
• Logical-page colors [IPDS-3-366]
• Object-area colors [IPDS-3-367]
• PCL (Printer Command Language) page object [IPDS-3-368]
• PDF (Portable Document Format) multiple-page file with transparency [IPDS-3-369]
• PDF multiple-page file without transparency [IPDS-3-370]
• PDF single page with transparency [IPDS-3-371]
• PDF single page without transparency [IPDS-3-372]
• PNG (Portable Network Graphics) AFPC PNG Subset [IPDS-3-373]
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-3-374]
• Text (characters, underscores, text rules) [IPDS-3-375]
• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-3-376]
• TIFF with transparency [IPDS-3-377]
• TIFF without transparency [IPDS-3-378]
• TIFF multiple-image file with transparency [IPDS-3-379]
• TIFF multiple-image file without transparency [IPDS-3-380]
Note that full-color image is usually not supported by IPDS black-only printers because the transformation to
grayscale can be costly to performance. Grayscale image also might not be supported for performance
reasons. These types of data cause an IPDS NACK to be reported by a printer that does not support them.
If no color mapping table is used, highlight colors are simulated with black and the percent-coverage value is
applied to produce a gray level. However, a color mapping table can be used to map highlight colors to process
colors so that a grayscale-supporting printer can simulate highlight colors in a controlled manner.
Note: Some printers that can simulate colors with grayscale also provide a configuration option that allows the
printer to emulate a black-only printer without grayscale capability. [IPDS-3-381]


Default Handling
Defaults are values used as control parameters when no other values are specified in the current command.
IPDS defaults are invoked through omission or through values transmitted in the data-field portion of
commands. The IPDS defaulting structure is usually hierarchical. Specific IPDS defaulting rules are contained
in the command chapters in this book; general IPDS defaulting rules are as follows:
• If power has been interrupted or if the printer has been reinitialized (returned an IML NACK), printer- [IPDS-3-382]
established default values are used until specific IPDS default values are received.
• Initial logical-page values are established when the printer receives the Logical Page Descriptor command. [IPDS-3-383]
Printer or PTOCA default values are used when either:
– No Logical Page Descriptor command has been received
– A field in the Logical Page Descriptor contains X'FFFF' or X'FF'.
PTOCA default values are defined for the initial text conditions (LPD bytes 24–42); refer to Presentation Text
Object Content Architecture Reference for a description of these defaults. Printer default values are used for
all other LPD fields; refer to your printer documentation for a description of these defaults.
• Initial data object values are established when the printer receives a Write Text Control, Write Image Control, [IPDS-3-384]
Write Image Control 2, Write Graphics Control, Write Bar Code Control, or Write Object Container Control
command. These values remain in effect until data controls override them or until the printer receives an End
command that terminates the object.
• All IPDS printers provide a printer default font that can be invoked within the data stream. The default font is [IPDS-3-385]
not necessarily the same for all data types and some printers allow this default font to be dynamically
selected by the printer operator. Therefore, the characteristics of the printer default font cannot always be
predicted when an IPDS data stream is generated. If knowing the characteristics of a font is important, select
a specific coded font. [IPDS-3-386]




