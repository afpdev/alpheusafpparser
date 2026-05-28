Appendix D. Cross-References
This appendix provides tables that list:
• AFP GOCA commands sorted by identifier [GOCA-D-001]
• AFP GOCA commands sorted by acronym [GOCA-D-002]
• AFP GOCA control instructions sorted by identifier [GOCA-D-003]
• AFP GOCA control instructions sorted by acronym [GOCA-D-004]
• AFP GOCA drawing orders sorted by identifier [GOCA-D-005]
• AFP GOCA drawing orders sorted by acronym [GOCA-D-006]
AFP GOCA Commands Sorted by Identifier
Table 16. Commands Sorted by ID
Identifier Command Name Acronym Page
X'70' Begin Segment BSI 75
AFP GOCA Commands Sorted by Acronym
Table 17. Commands Sorted by Acronym
Acronym Identifier Command Name Page
BSI X'70' Begin Segment 75
AFP GOCA Control Instructions Sorted by Identifier
Table 18. Control Instructions Sorted by ID
Identifier Instruction Name Acronym Page
X'21' Set Current Defaults SCD66
AFP GOCA Control Instructions Sorted by Acronym
Table 19. Control Instructions Sorted by Acronym
Acronym Identifier Instruction Name Page
SCD X'21' Set Current Defaults 66 [GOCA-D-007]

---

AFP GOCA Drawing Orders Sorted by Identifier
Table 20. Drawing Orders Sorted by ID
Identifier Drawing Order Name Acronym Page
X'00' No-Operation GNOP1 1 18
X'01' Comment GCOMT 95
X'04' Segment Characteristics GSGCH 129
X'08' Set Pattern Set GSPS 158
X'0A' Set Color GSCOL 142
X'0C' Set Mix GSMX156
X'0D' Set Background Mix GSBMX132
X'1 1' Set Fractional Line Width GSFL W 147
X'18' Set Line Type GSL T 150
X'19' Set Line Width GSL W 151
X'1A' Set Line End GSLE148
X'1B' Set Line Join GSLJ 149
X'20' Set Custom Line Type GSCL T 144
X'21' Set Current Position GSCP143
X'22' Set Arc Parameters GSAP130
X'26' Set Extended Color GSECOL 146
X'28' Set Pattern Symbol GSPT 159
X'29' Set Marker Symbol GSMT 155
X'33' Set Character Cell GSCC 134
X'34' Set Character Angle GSCA 133
X'35' Set Character Shear GSCH 141
X'37' Set Marker Cell GSMC 152
X'38' Set Character Set GSCS 140
X'39' Set Character Precision GSCR138
X'3A' Set Character Direction GSCD136
X'3B' Set Marker Precision (obsolete) GSMP196
X'3C' Set Marker Set GSMS 154
X'3E' End Prolog GEPROL 104
X'43' Set Pick Identifier GSPIK Note 1
X'5E' End Custom Pattern GECP102
X'60' End Area GEAR101
X'68' Begin Area GBAR82
X'71' End Segment Note 2
X'80' Box at Current Position GCBOX90 [GOCA-D-008]

---

Table 20 Drawing Orders Sorted by ID (cont'd.)
Identifier Drawing Order Name Acronym Page
X'81' Line at Current Position GCLINE1 10
X'82' Marker at Current Position GCMRK 1 16
X'83' Character String at Current Position GCCHST 92
X'85' Fillet at Current Position GCFL T 105
X'87' Full Arc at Current Position GCF ARC 107
X'91' Begin Image at Current Position GCBIMG 87
X'92' Image Data GIMD109
X'93' End Image GEIMG 103
X'A0' Set Pattern Reference Point GSPRP157
X'A1' Relative Line at Current Position GCRLINE127
X'A3' Partial Arc at Current Position GCP ARC 1 19
X'A5' Cubic Bezier Curve at Current Position GCCBEZ 97
X'B2' Set Process Color GSPCOL 161
X'C0' Box at Given Position GBOX90
X'C1' Line at Given Position GLINE1 10
X'C2' Marker at Given Position GMRK 1 16
X'C3' Character String at Given Position GCHST 92
X'C5' Fillet at Given Position GFL T 105
X'C7' Full Arc at Given Position GF ARC 107
X'D1' Begin Image at Given Position GBIMG 87
X'DE' Begin Custom Pattern GBCP84
X'DF' Delete Pattern GDPT 99
X'E1' Relative Line at Given Position GRLINE127
X'E3' Partial Arc at Given Position GP ARC 1 19
X'E5' Cubic Bezier Curve at Given Position GCBEZ 96
X'FEDC' Linear Gradient GLGD1 12
X'FEDD' Radial Gradient GRGD122
Notes:
1. The Set Pick Identifier (X'43') long-format drawing order is not formally part of AFP GOCA, but is accepted by some [GOCA-D-009]
AFP printers and treated as a No-Op.
2. The End Segment (X'71') fixed two-byte drawing order is not formally part of AFP GOCA, but is accepted by some [GOCA-D-010]
AFP printers and treated as a No-Op. [GOCA-D-011]

---

AFP GOCA Drawing Orders Sorted by Acronym
Table 21. Drawing Orders Sorted by Acronym
Acronym Identifier Drawing Order Name Page
GBAR X'68' Begin Area 82
GBCP X'DE' Begin Custom Pattern 84
GBIMG X'D1' Begin Image at Given Position 87
GBOX X'C0' Box at Given Position 90
GCBEZ X'E5' Cubic Bezier Curve at Given Position 96
GCBIMG X'91' Begin Image at Current Position 87
GCBOX X'80' Box at Current Position 90
GCCBEZ X'A5' Cubic Bezier Curve at Current Position 97
GCCHST X'83' Character String at Current Position 92
GCF ARC X'87' Full Arc at Current Position 107
GCFL T X'85' Fillet at Current Position 105
GCHST X'C3' Character String at Given Position 92
GCLINE X'81' Line at Current Position 1 10
GCMRK X'82' Marker at Current Position 1 16
GCOMT X'01' Comment 95
GCP ARC X'A3' Partial Arc at Current Position 1 19
GCRLINE X'A1' Relative Line at Current Position 127
GDPT X'DF' Delete Pattern 99
GEAR X'60' End Area 101
GECP X'5E' End Custom Pattern 102
GEIMG X'93' End Image 103
GEPROL X'3E' End Prolog 104
GF ARC X'C7' Full Arc at Given Position 107
GFL T X'C5' Fillet at Given Position 105
GIMD X'92' Image Data 109
GLGD X'FEDC' Linear Gradient 1 12
GLINE X'C1' Line at Given Position 1 10
GMRK X'C2' Marker at Given Position 1 16
GNOP1 X'00' No-Operation 1 18
GP ARC X'E3' Partial Arc at Given Position 1 19
GRGD X'FEDD' Radial Gradient 122
GRLINE X'E1' Relative Line at Given Position 127
GSAP X'22' Set Arc Parameters 130
GSBMX X'0D' Set Background Mix 132 [GOCA-D-012]

---

Table 21 Drawing Orders Sorted by Acronym (cont'd.)
Acronym Identifier Drawing Order Name Page
GSCA X'34' Set Character Angle 133
GSCC X'33' Set Character Cell 134
GSCD X'3A' Set Character Direction 136
GSCH X'35' Set Character Shear 141
GSCL T X'20' Set Custom Line Type 144
GSCOL X'0A' Set Color 142
GSCP X'21' Set Current Position 143
GSCR X'39' Set Character Precision 138
GSCS X'38' Set Character Set 140
GSECOL X'26' Set Extended Color 146
GSFL W X'1 1' Set Fractional Line Width 147
GSGCH X'04' Segment Characteristics 129
GSLE X'1A' Set Line End 148
GSLJ X'1B' Set Line Join 149
GSL T X'18' Set Line Type 150
GSL W X'19' Set Line Width 151
GSMC X'37' Set Marker Cell 152
GSMP X'3B' Set Marker Precision (obsolete) 196
GSMS X'3C' Set Marker Set 154
GSMT X'29' Set Marker Symbol 155
GSMX X'0C' Set Mix 156
GSPCOL X'B2' Set Process Color 161
GSPIK X'43' Set Pick Identifier Note 1
GSPRP X'A0' Set Pattern Reference Point 157
GSPS X'08' Set Pattern Set 158
GSPT X'28' Set Pattern Symbol 159
X'71' End Segment Note 2
Notes:
1. The Set Pick Identifier (X'43') long-format drawing order is not formally part of AFP GOCA, but is accepted by some [GOCA-D-013]
AFP printers and treated as a No-Op.
2. The End Segment (X'71') fixed two-byte drawing order is not formally part of AFP GOCA, but is accepted by some [GOCA-D-014]
AFP printers and treated as a No-Op. [GOCA-D-015]

---

Copyright © AFP Consortium 1997, 2017 205
The AFP Consortium or consortium member companies might have patents or pending patent applications covering subject matter described in this document. The furnishing of this document does not give you any license to these patents.
The following statement does not apply to the United Kingdom or any other country where such provisions are inconsistent with local law: AFP Consortium PROVIDES THIS PUBLICA TION "AS IS"
WITHOUT W ARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED
TO , THE IMPLIED W ARRANTIES OF NON-INFRINGEMENT , MERCHANT ABILITY OR FITNESS FOR A
P ARTICULAR PURPOSE. Some states do not allow disclaimer of express or implied warranties in certain transactions, therefore, this statement might not apply to you.
This publication could include technical inaccuracies or typographical errors. Changes are periodically made to the information herein; these changes will be incorporated in new editions of the publication. The AFP
Consortium might make improvements and/or changes in the architecture described in this publication at any time without notice.
Any references in this publication to We b sites are provided for convenience only and do not in any manner serve as an endorsement of those We b sites. The materials at those Web sites are not part of the materials for this architecture and use of those Web sites is at your own risk.
The AFP Consortium may use or distribute any information you supply in any way it believes appropriate without incurring any obligation to you.
This information contains examples of data and reports used in daily business operations. T o illustrate them in a complete manner , some examples include the names of individuals, companies, brands, or products. These names are fictitious and any similarity to the names and addresses used by an actual business enterprise is entirely coincidental. [GOCA-D-016]

---

T rademarks
PostScript is a registered trademark of Adobe Systems Incorporated in the United States, other countries, or both .
AFPC and AFP Consortium are trademarks of the AFP Consortium.
These terms are registered trademarks of the International Business Machines Corporation in the United
States, other countries, or both:
IBM
These terms are trademarks or registered trademarks of Ricoh Co., Ltd., in the United States, other countries, or both:
ACMA
Advanced Function Presentation
AFP
AFPCC
AFP Color Consortium
AFP Color Management Architecture
Bar Code Object Content Architecture
BCOCA
CMOCA
Color Management Object Content Architecture
InfoPrint
Intelligent Printer Data Stream
IPDS
Mixed Object Document Content Architecture
MO:DCA
Ricoh
Other company , product, or service names may be trademarks or service marks of others. [GOCA-D-017]

---

Copyright © AFP Consortium 1997, 2017 207
This glossary contains terms that apply to the
Advanced Function Presentation (AFP) Architecture and also terms that apply to other related presentation architectures.
Note: Only changes having to do with newly-added
GOCA functionality in this edition are marked in color with a colored revision bar to the left.
All other changes—terms or definitions that have been added, deleted, or reworded—are not marked.
If you do not find the term that you are looking for , please refer to the IBM Dictionary of Computing, document number ZC20-1699 or the InfoPrint
Dictionary of Printing.
The following definitions are provided as supporting information only , and are not intended to be used as a substitute for the semantics described in the body of this reference.
A absolute coordinate. One of the coordinates that identify the location of an addressable point with respect to the origin of a specified coordinate system. Contrast with relative coordinate. absolute move. A method used to designate a new presentation position by specifying the distance from the designated axes to the new presentation position. The reference for locating the new presentation position is a fixed position as opposed to the current presentation position. absolute positioning. The establishment of a position within a coordinate system as an offset from the coordinate system origin. Contrast with relative positioning. addressable position. A position in a presentation space or on a physical medium that can be identified by a coordinate from the coordinate system of the presentation space or physical medium. See also picture element.
Synonymous with position.
Advanced Function Presentation (AFP). An open architecture for the management of presentable information that is developed by the AFP Consortium (AFPC). AFP comprises a number of data stream and data object architectures:
• Mixed Object Document Content Architecture (MO:DCA); [GOCA-D-018]
formerly referred to as AFPDS
• Intelligent Printer Data Stream (IPDS) [GOCA-D-019]
• AFP Line Data Architecture [GOCA-D-020]
• Bar Code Object Content Architecture (BCOCA) [GOCA-D-021]
• Color Management Object Content Architecture (CMOCA) [GOCA-D-022]
• Font Object Content Architecture (FOCA) [GOCA-D-023]
• Graphics Object Content Architecture for AFP (AFP [GOCA-D-024]
GOCA)
• Image Object Content Architecture (IOCA) [GOCA-D-025]
• Metadata Object Content Architecture (MOCA) [GOCA-D-026]
• Presentation T ext Object Content Architecture (PTOCA) [GOCA-D-027]
AEA. See alternate exception action.
AFP . See Advanced Function Presentation.
AFP Consortium (AFPC). A formal open standards body that develops and maintains AFP architecture. Information about the consortium can be found at www.apfcinc.org.
AFP data stream. A presentation data stream that is processed in AFP environments. The MO:DCA architecture defines the strategic AFP interchange data stream. The IPDS architecture defines the strategic AFP printer data stream.
AFPDS. A term formerly used to identify the composed- page MO:DCA-based data stream interchanged in AFP environments. See also MO:DCA and AFP data stream.
AFP GOCA. See Graphics Object Content Architecture for Advanced Function Presentation.
AFP Line Data Architecture. An AFP architecture that controls formatting of unformatted text data, often called line data, using a Page Definition (PageDef). all points addressable (AP A). The capability to address, reference, and position data elements at any addressable position in a presentation space or on a physical medium.
Contrast with character cell addressing, in which the presentation space is divided into a fixed number of character-size rectangles in which characters can appear .
Only the cells are addressable. An example of all points addressability is the positioning of text, graphics, and images at any addressable point on the physical medium.
See also picture element.
alternate exception action (AEA). In the IPDS architecture, a defined action that a printer can take when a clearly defined, but unsupported, request is received.
Control over alternate exception actions is specified by an
Execute Order Anystate Exception-Handling Control command.
American National Standards Institute (ANSI). An organization consisting of producers, consumers, and general interest groups. ANSI establishes the procedures by which accredited organizations create and maintain [GOCA-D-028]

---

voluntary industry standards in the United States. It is the
United States constituent body of the International
Organization for Standardization (ISO).
anamorphic scaling. Scaling an object differently in the vertical and horizontal directions. See also scaling, horizontal font size, and vertical font size.
ANSI. See American National Standards Institute.
AP A. See all points addressable.
application. (1) The use to which an information system is put. (2) A collection of software components used to perform specific types of work on a computer . application program. A program written for or by a user that applies to the user's work. arc. A continuous portion of the curved line of a circle or ellipse. See also full arc. architected. Identifies data that is defined and controlled by an architecture. Contrast with unarchitected. arc parameters. V ariables that specify the curvature of an arc. area. A set of closed figures that can be filled with a pattern or a color . area filling. A method used to fill an area with a pattern or a color . aspect ratio. The ratio of the horizontal size of a picture to the vertical size of the picture. attribute. A property or characteristic of one or more constructs. See also character attribute, color attribute, current drawing attributes, default drawing attributes, line attributes, marker attributes, and pattern attributes.
B background. (1) The part of a presentation space that is not occupied with object data. Contrast with foreground. (2) In GOCA, that portion of a graphics primitive that is mixed into the presentation space under the control of the current values of the background mix and background color attributes. (3) In GOCA, that portion of a character cell that does not represent a character . background color . The color of a background. Contrast with foreground color . background mix. (1) An attribute that determines how the color of the background of a graphics primitive is combined with the existing color of the graphics presentation space. (2) An attribute that determines how the points in overlapping presentation space backgrounds are combined. Contrast with foreground mix.
Bar Code Object Content Architecture (BCOCA). An architected collection of constructs used to interchange and present bar code data. base-and-towers concept. A conceptual illustration of an architecture that shows the architecture as a base with optional towers. The base and the towers represent different degrees of function achieved by the architecture. baseline direction (B). The direction in which successive lines of text appear on a logical page. Synonymous with B direction. base support level. Within the base-and-towers concept, the smallest portion of architected function that is allowed to be implemented. This is represented by a base with no towers. Synonymous with mandatory support level.
BCOCA. See Bar Code Object Content Architecture.
B direction. Synonymous with baseline direction.
Begin Segment Introducer (BSI). An IPDS graphics self-defining field that precedes all of the drawing orders in a graphics segment. between-the-pels. The concept of pel positioning that establishes the location of a pel's reference point at the edge of the pel nearest to the preceding pel rather than through the center of the pel. bilevel custom pattern. A custom pattern that is uncolored at definition time, then has a single color assigned to it when it is used to fill an area. Contrast with full-color custom pattern.
BITS. A data type for architecture syntax, indicating one or more bytes to be interpreted as bit string information.
bounded character box. A conceptual rectangular box, with two sides parallel to the character baseline, that circumscribes a character and is just large enough to contain the character , that is, just touching the shape on all four sides.
BSI. See Begin Segment Introducer .
C
CCSID. See Coded Character Set Identifier .
CGCSGID. See Coded Graphic Character Set Global
Identifier .
CHAR. A data type for architecture syntax, indicating one or more bytes to be interpreted as character information.
character . A member of a set of elements used for the organization, control, or representation of data. A character can be either a graphic character or a control character .
See also graphic character and control character .
anamorphic scaling • character [GOCA-D-029]

---

character angle. The angle that is between the baseline of a character string and the horizontal axis of a presentation space or physical medium. character attribute. A characteristic that controls the appearance of a character or character string. character baseline. A conceptual reference line that is coincident with the X axis of the character coordinate system. character box. A conceptual rectangular box with two sides parallel to the character baseline. A character's shape is formed within a character box by a presentation process, and the character box is then positioned in a presentation space or on a physical medium. The character box can be rotated before it is positioned. character cell size. The size of a rectangle in a drawing space used to scale font symbols into the drawing space. character code. An element of a code page or a cell in a code table to which a character can be assigned. The element is associated with a binary value. The assignment of a character to an element of a code page determines the binary value that will be used to represent each occurrence of the character in a character string. character coordinate system. An orthogonal coordinate system that defines font and character measurement distances. The origin is the character reference point. The
X axis coincides with the character baseline.
character direction. In GOCA, an attribute controlling the direction in which a character string grows relative to the inline direction. Values are: left-to-right, right-to-left, top-to- bottom, and bottom-to-top. Synonymous with direction. character escapement point. The point where the next character reference point is usually positioned. See also character increment and presentation position. character identifier . The unique name for a graphic character . character increment. The distance from a character reference point to a character escapement point. For each character , the increment is the sum of a character's A space, B space, and C space. A character's character increment is the distance the inline coordinate is incremented when that character is placed in a presentation space or on a physical medium. Character increment is a property of each graphic character in a font and of the font's character rotation. character metrics. Measurement information that defines individual character values such as height, width, and space. Character metrics can be expressed in specific fixed units, such as pels, or in relative units that are independent of both the resolution and the size of the font.
Often included as part of the more general term font metrics. See also character set metrics and font metrics.
character origin. The point within the graphic pattern of a character that is to be aligned with the presentation position. See also character reference point. character pattern. The scan pattern for a graphic character of a particular size, style, and weight. character positioning. A method used to determine where a character is to appear in a presentation space or on a physical medium. character precision. The acceptable amount of variation in the appearance of a character on a physical medium from a specified ideal appearance, including no acceptable variation. Examples of appearance characteristics that can vary for a character are character shape and character position. character reference point. The origin of a character coordinate system. The X axis is the character baseline.
See also character origin.
character rotation. The alignment of a character with respect to its character baseline, measured in degrees in a clockwise direction. Examples are 0°, 90°, 180°, and 270°.
Zero-degree character rotation exists when a character is in its customary alignment with the baseline. Contrast with rotation. character set. A finite set of different graphic characters or control characters that is complete for a given purpose.
For example, the character set in ISO Standard 646, 7-Bit
Coded Character Set for Information Processing
Interchange.
character set attribute. An attribute used to specify a coded font.
character set metrics. The measurements used in a font. Examples are height, width, and character increment for each character of the font. See also character metrics and font metrics. character shape. The visual representation of a graphic character . character shear . The angle of slant of a character cell that is not perpendicular to a baseline. Synonymous with shear . character string. A sequence of characters.
CIELAB color space. Internationally accepted color model used as a standard to define color within the graphic arts industry , as well as other industries. L*, a*, and b* are plotted at right angles to one another . Equal distances in the space represent approximately equal color difference. clipping. Eliminating those parts of a picture that are outside of a clipping boundary such as a viewing window or presentation space. Synonymous with trimming. character angle • clipping [GOCA-D-030]

---

CMOCA. See Color Management Object Content
Architecture.
CMR. See color management resource.
CMYK color space. (1) The color model used in four- color printing. Cyan, magenta, and yellow , the subtractive primary colors, are used with black to effectiv ely create a multitude of other colors. (2) The primary colors used together in printing to effectively create a multitude of other colors: cyan, magenta, yellow , and black. Based on the subtractive color theory; the primary colors used in four- color printing processes.
CODE. A data type for architecture syntax that indicates an architected constant to be interpreted as defined by the architecture.
Coded Character Set Identifier (CCSID). A 16-bit number identifying a specific set consisting of an encoding scheme identifier , character set identifiers, code page identifiers, and other relevant information that uniquely identifies the coded graphic character representation used. coded font. (1) A resource containing elements of a code page and a font character set, used for presenting text, graphics character strings, and bar code HRI. See also code page and font character set. (2) In FOCA, a resource containing the resource names of a valid pair of font character set and code page resources. The graphic character set of the font character set must match the graphic character set of the code page for the coded font resource pair to be valid. (3) In the IPDS architecture, a raster font resource containing code points that are directly paired to font metrics and the raster representation of character shapes, for a specific graphic character set. (4)
In the IPDS architecture, a font resource containing descriptive information, a code page, font metrics, and a digital-technology representation of character shapes for a specific graphic character set. coded graphic character . A graphic character that has been assigned one or more code points within a code page. coded graphic character set. A set of graphic characters with their assigned code points.
Coded Graphic Character Set Global Identifier (CGCSGID). A four-byte binary or a ten-digit decimal identifier consisting of the concatenation of a GCSGID and a CPGID. The CGCSGID identifies the code point assignments in the code page for a specific graphic character set, from among all the graphic characters that are assigned in the code page. code page. (1) A resource object containing descriptive information, graphic character identifiers, and code points corresponding to a coded graphic character set. Graphic characters can be added over time; therefore, to specifically identify a code page, both a GCSGID and a
CPGID should be used. See also coded graphic character set. (2) A set of assignments, each of which assigns a code point to a character . Each code page has a unique name or identifier . Within a given code page, a code point is assigned to one character . More than one character set can be assigned code points from the same code page.
See also code point and section.
Code Page Global Identifier (CPGID). A unique code page identifier that can be expressed as either a two-byte binary or a five-digit decimal value. code point. A unique bit pattern that can serve as an element of a code page or a site in a code table, to which a character can be assigned. The element is associated with a binary value. The assignment of a character to an element of a code page determines the binary value that will be used to represent each occurrence of the character in a character string. Code points are one or more bytes long. See also code table and section. code table. A table showing the character allocated to each code point in a code. See also code page and code point. color attribute. An attribute that affects the color values provided in a graphics primitive, a text control sequence, or an IPDS command. Examples of color attributes are foreground color and background color . color image. Images whose image data elements are represented by multiple bits or whose image data element values are mapped to color values. Constructs that map image-data-element values to color values are look-up tables and image-data-element structure parameters.
Examples of color values are screen color values for displays and color toner values for printers.
color management. The technology to calibrate the color of input devices (such as scanners or digital cameras), display devices, and output devices (such as printers or offset presses).
Color Management Object Content Architecture (CMOCA). An architected collection of constructs used for the interchange and presentation of the color management information required to render a print file, document, group of pages or sheets, page, overlay , or data object with color fidelity . color management resource. An object that provides color management in presentation environments. color model. The method by which a color is specified.
For example, the RGB color space specifies color in terms of three intensities for red (R), green (G), and blue (B). Also referred to as color space. color of medium. The color of a presentation space before any data is added to it. Synonymous with reset color .
CMOCA • color of medium [GOCA-D-031]

---

GOCA for AFP Reference 21 1 color space. The method by which a color is specified.
For example, the RGB color space specifies color in terms of three intensities for red (R), green (G), and blue (B). Also referred to as color model. color table. A collection of color element sets. The table can also specify the method used to combine the intensity levels of each element in an element set to produce a specific color . Examples of methods used to combine intensity levels are the additive method and the subtractive method. See also color model. command. (1) In GOCA, a data-stream construct used to communicate from the controlling environment to the drawing process. The command introducer is environment dependent. (2) In the IPDS architecture, a structured field sent from a host to a printer . (3) A request for system action. construct. An architected set of data such as a structured field or a triplet. continuous-form media. Connected sheets. An example of connected sheets is sheets of paper connected by a perforated tear strip. Contrast with cut-sheet media. control character . (1) A character that denotes the start, modification, or end of a control function. A control character can be recorded for use in a subsequent action, and it can have a graphic representation. See also character . (2) A control function the coded representation of which consists of a single code point. control instruction. A data construct transmitted from the controlling environment and interpreted by the environment interface to control the operation of the graphics processor . controlling environment. The environment in which an object is embedded, for example, the IPDS and MO:DCA data streams. coordinates. A pair of values that specify a position in a coordinate space. See also absolute coordinate and relative coordinate. coordinate system. A Cartesian coordinate system. An example is the image coordinate system that uses the fourth quadrant with positive values for the Y axis. The origin is the upper left-hand corner of the fourth quadrant.
A pair of (x,y) values corresponds to one image point. Each image point is described by an image data element. See also character coordinate system.
CPGID. See Code Page Global Identifier .
current drawing attributes. The set of attributes used at the present time to direct a drawing process. Contrast with default drawing attributes. current drawing controls. The set of drawing controls used at the present time to direct a drawing process.
Contrast with default drawing controls.
current position. The position identified by the current presentation space coordinates. For example, the coordinate position reached after the execution of a drawing order . Contrast with given position. custom line type value. A user-defined line type, defined by a series of pairs of a dash/dot length followed by a move length. Contrast with standard line type value. custom pattern. A user-defined pattern, defined by the picture drawn by a series of drawing orders between a
Begin Custom Pattern drawing order and an End Custom
Pattern drawing order . Custom patterns can be either bilevel custom patterns or full-color custom patterns.
Contrast with patterns in the default pattern set.
custom pattern mode. A mode that is entered when a
Begin Custom Pattern drawing order is executed and exited when an End Custom Pattern drawing order is executed. While in this mode, drawing is done in a separate, temporary graphics presentation space rather than in the graphics presentation space of the current
GOCA object.
cut-sheet media. Unconnected sheets. Contrast with continuous-form media.
D data stream. A continuous stream of data that has a defined format. An example of a defined format is a structured field.
DBCS. See double-byte character set.
default. A value, attribute, or option that is assumed when none has been specified and one is needed to continue processing. See also default drawing attributes and default drawing controls. default drawing attributes. Synonymous with drawing defaults. default drawing controls. The set of drawing controls adopted at the start of a drawing process and usually at the start of each root segment that is processed. Contrast with current drawing controls. default indicator . A field whose bits are all B'1' indicating that a hierarchical default value is to be used. The value can be specified by an external parameter . See also external parameter . default pattern set. A set of predefined patterns, like solid, dots, or horizontal lines. Contrast with custom pattern. color space • default pattern set [GOCA-D-032]

---

deprecated. An architected construct is marked as
“deprecated” to indicate that it should no longer be used because it has been superseded by a newer construct.
Use or support of a deprecated construct is permitted but no longer recommended. Constructs are deprecated rather than immediately removed to provide backward compatibility . device dependent. Dependent upon one or more device characteristics. An example of device dependency is a font whose characteristics are specified in terms of addressable positions of specific devices. digital halftoning. A method used to simulate gray levels on a bilevel device. digital image. An image whose image data was sampled at regular intervals to produce a digital representation of the image. The digital representation is usually restricted to a specified set of values. direction. In GOCA, an attribute that controls the direction in which a character string grows relative to the inline direction. Values are: left-to-right, right-to-left, top-to- bottom, and bottom-to-top. Synonymous with character direction.
DOCS. See drawing order coordinate space.
document. (1) A machine-readable collection of one or more objects that represents a composition, a work, or a collection of data. (2) A publication or other written material. document content architecture. A family of architectures that define the syntax and semantics of the document component. document element. A self-identifying, variable-length, bounded record, that can have a content portion that provides control information, data, or both. An application or device does not have to understand control information or data to parse a data stream when all the records in the data stream are document elements. See also structured field. document fidelity . The degree to which a document presentation preserves the creator's intent. document formatting. A method used to determine where information is positioned in presentation spaces or on physical media. document presentation. A method used to produce a visible copy of formatted information on physical media. double-byte character set (DBCS). A character set that can contain up to 65,536 characters. double-byte coded font. A coded font in which the code points are two bytes long. drawing control. A control that determines how a picture is drawn. Examples of drawing controls are arc parameters, transforms, and the viewing window . drawing defaults. In GOCA, the set of attributes adopted at the start of each segment that is processed. These attributes are set either from standard defaults defined by the controlling environment or from the Set Current
Defaults instruction that is contained in the Graphics Data
Descriptor . Synonymous with default drawing attributes.
Contrast with current drawing attributes.
drawing order . In GOCA, a graphics construct that the controlling environment builds to instruct a drawing processor about what to draw and how to draw it. The order can specify , for example, that a graphics primitive be drawn, or a change to drawing attributes or drawing controls be effected. One or more graphics primitives can be used to draw a picture. Drawing orders can be included in a structured field. Synonymous with order . drawing order coordinate space (DOCS). A two- dimensional conceptual space in which graphics primitives are drawn, using drawing orders, to create pictures. drawing process control. A control used by the graphics processor that determines how a picture is drawn.
Examples of drawing process controls are arc parameters.
drawing processor . A graphics processor component that executes segments to draw a picture in a presentation space. See also segment and graphics presentation space. duplex printing. A method used to print data on both sides of a sheet. Normal-duplex printing occurs when the sheet is turned over the Y m axis. T umble-duplex printing occurs when the sheet is turned over the X m axis. Contrast with simplex printing.
E
EBCDIC. See Extended Binary-Coded Decimal
Interchange Code.
Em. In printing, a unit of linear measure referring to the baseline-to-baseline distance of a font, in the absence of any external leading.
Em square. A square layout space used for designing each of the characters of a font.
encoding scheme. A set of specific definitions that describe the philosophy used to represent character data.
The number of bits, the number of bytes, the allowable ranges of bytes, the maximum number of characters, and the meanings assigned to some generic and specific bit patterns, are some examples of specifications to be found in such a definition. deprecated • encoding scheme [GOCA-D-033]

---

Encoding Scheme Identifier (ESID). A 16-bit number assigned to uniquely identify a particular encoding scheme specification. See also encoding scheme. environment interface. The part of the graphics processor that interprets commands and instructions from the controlling environment. escapement direction. In FOCA, the direction from a character reference point to the character escapement point, that is, the font designer's intended direction for successive character shapes. See also character direction.
ESID. See Encoding Scheme Identifier .
exception. An invalid or unsupported data-stream construct.
exception action. Action taken when an exception is detected.
exception condition. The condition that exists when a product finds an invalid or unsupported construct.
exchange. The predictable interpretation of shared information by a family of system processes in an environment where the characteristics of each process must be known to all other processes. Contrast with interchange.
Extended Binary-Coded Decimal Interchange Code (EBCDIC). A coded character set that consists of eight-bit coded characters. external parameter . A parameter for which the current value can be provided by the controlling environment—for example, the data stream—or by the application itself.
Contrast with internal parameter .
F factoring. The movement of a parameter value from one state to a higher-level state. This permits the parameter value to apply to all of the lower-level states unless specifically overridden at the lower level.
FGID. See Font Typeface Global Identifier .
fillet. A curved line drawn tangential to a specified set of straight lines. An example of a fillet is the concave junction formed where two lines meet. final form data. Data that has been formatted for presentation.
FOCA. See Font Object Content Architecture.
font. A set of graphic characters that have a characteristic design, or a font designer's concept of how the graphic characters should appear . The characteristic design specifies the characteristics of its graphic characters.
Examples of characteristics are character shape, graphic pattern, style, size, weight, and increment. Examples of fonts are fully-described fonts, symbol sets, and their internal printer representations. See also coded font and symbol set. font character set. A FOCA resource containing descriptive information, font metrics, and the digital representation of character shapes for a specified graphic character set. font height (FH). (1) A characteristic value, perpendicular to the character baseline, that represents the size of all graphic characters in a font. Synonymous with vertical font size. (2) In a font character set, nominal font height is a font-designer defined value corresponding to the nominal distance between adjacent baselines when character rotation is zero degrees and no external leading is used.
This distance represents the baseline-to-baseline increment that includes the font's maximum baseline extent and the designer's recommendation for internal leading.
The font designer can also define a minimum and a maximum vertical font size to represent the limits of scaling. (3) In font referencing, the specified font height is the desired size of the font when the characters are presented. If this size is different from the nominal vertical font size specified in a font character set, the character shapes and character metrics might need to be scaled prior to presentation. font local identifier . A binary identifier that is mapped by the controlling environment to a named resource to identify a font. See also local identifier . font metrics. Measurement information that defines individual character values such as height, width, and space, as well as overall font values such as averages and maximums. Font metrics can be expressed in specific fixed units, such as pels, or in relative units that are independent of both the resolution and the size of the font. See also character metrics and character set metrics. font object. A resource object that contains some or all of the description of a font.
Font Object Content Architecture (FOCA). An architected collection of constructs used to describe fonts and to interchange those font descriptions. font referencing. A method used to identify or characterize a font. Examples of processes that use font referencing are document editing, document formatting, and document presentation.
Font Typeface Global Identifier (FGID). A unique font identifier that can be expressed as either a two-byte binary or a five-digit decimal value. The FGID is used to identify a type style and the following characteristics: posture, weight class, and width class. font width (FW). (1) A characteristic value, parallel to the character baseline, that represents the size of all graphic
Encoding Scheme Identifier (ESID) • font width (FW) [GOCA-D-034]

---

characters in a font. Synonymous with horizontal font size. (2) In a font character set, nominal font width is a font-designer defined value corresponding to the nominal character increment for a font character set. The value is generally the width of the space character and is defined differently for fonts with different spacing characteristics.
• For fixed-pitch, uniform character increment fonts: the fixed character increment, which is also the space character increment [GOCA-D-035]
• For PSM fonts: the width of the space character [GOCA-D-036]
• For typographic, proportionally-spaced fonts: one-third of the vertical font size, which is also the default size of the space character . [GOCA-D-037]
The font designer can also define a minimum and a maximum horizontal font size to represent the limits of scaling. (3) In font referencing, the specified font width is the desired size of the font when the characters are presented. If this size is different from the nominal horizontal font size specified in a font character set, the character shapes and character metrics might need to be scaled prior to presentation. foreground. (1) The part of a presentation space that is occupied with object data. (2) In GOCA, the portion of a graphics primitive that is mixed into the presentation space under the control of the current value of the mix and color attributes. See also pel. Contrast with background. foreground color . A color attribute used to specify the color of the foreground of a primitive. Contrast with background color . foreground mix. An attribute used to determine how the foreground color of data is combined with the existing color of a graphics presentation space. An example of data is a graphics primitive. Contrast with background mix. form. Synonymous with sheet. format. The arrangement or layout of data on a physical medium or in a presentation space. formatter . A process used to prepare a document for presentation. full arc. A complete circle or ellipse. See also arc. full-color custom pattern. A custom pattern that has its colors completely assigned during its definition, and can therefore contain any number of colors. Contrast with bilevel custom pattern. fully described font. In the IPDS architecture, an LF1- type raster-font resource containing font metrics, descriptive information, and the raster representation of character shapes, for a specific graphic character set. A fully described font can be downloaded to a printer using the Load Font Control and Load Font commands. An LF1- type coded font or coded-font section is the combination of one fully described font and one font index. function set. A collection of architecture constructs and associated values. Function sets can be defined across or within subsets.
FW . See font width.
G
GCGID. See Graphic Character Global Identifier .
GCSGID. See Graphic Character Set Global Identifier .
GCUID. See Graphic Character UCS Identifier .
GID. See global identifier .
given position. The coordinate position at which drawing is to begin. A given position is specified in a drawing order .
Contrast with current position.
Global Identifier (GID). One of the following:
• Coded Character Set Identifier (CCSID) [GOCA-D-038]
• Coded Graphic Character Set Global Identifier (CGCSGID) [GOCA-D-039]
• Code Page Global ID (CPGID) [GOCA-D-040]
• Font Typeface Global Identifier (FGID) [GOCA-D-041]
• Global Resource Identifier (GRID) [GOCA-D-042]
• Graphic Character Global Identifier (GCGID) [GOCA-D-043]
• Graphic Character Set Global Identifier (GCSGID) [GOCA-D-044]
• Graphic Character UCS Identifier (GCUID) [GOCA-D-045]
• An identifier used by a data object to reference a resource [GOCA-D-046]
• In the MO:DCA environment, an encoded graphic character string that provides a reference name for a document element. [GOCA-D-047]
Global Resource Identifier (GRID). An eight-byte identifier that identifies a coded font resource. A GRID contains the following fields in the order shown: 1. GCSGID of a minimum set of graphic characters required for presentation. It can be a character set that is associated with the code page, or with the font character set, or with both. 2. CPGID of the associated code page 3. FGID of the associated font character set 4. Font width in 1440ths of an inch. glyph. A member of a set of symbols that represent data.
Glyphs can be letters, digits, punctuation marks, or other symbols. Synonymous with graphic character . See also character .
GOCA. See Graphics Object Content Architecture.
foreground • GOCA

---

GPS. See graphics presentation space.
gradient. An area fill where one color gradually changes to another . A gradient is a type of pattern.
graphic character . A member of a set of symbols that represent data. Graphic characters can be letters, digits, punctuation marks, or other symbols. Synonymous with glyph. See also character .
Graphic Character Global Identifier (GCGID). An alphanumeric character string used to identify a specific graphic character . A GCGID can be from four bytes to eight bytes long. graphic character identifier . The unique name for a graphic character in a font or in a graphic character set.
See also character identifier .
Graphic Character Set Global Identifier (GCSGID). A unique graphic character set identifier that can be expressed as either a two-byte binary or a five-digit decimal value.
Graphic Character UCS Identifier (GCUID). An alphanumeric character string used to identify a specific graphic character . The GCUIDnaming scheme is used for additional characters and sets of characters that exist in
UNICODE; each GCUID begins with the letter U and ends with a UNICODE code point. The Unicode Standard is fully compatible with the earlier Universal Character Set (UCS)
Standard.
Graphics command set. In the IPDS architecture, a collection of commands used to present GOCA data in a page, page segment, or overlay . graphics data. Data containing lines, arcs, markers, and other constructs that describe a picture. graphics object. An object that contains graphics data.
See also object.
graphics object area. A rectangular area on a logical page into which a graphics presentation space window is mapped.
Graphics Object Content Architecture (GOCA). An architected collection of constructs used to interchange and present graphics data. GOCA was originally defined by
IBM; this architecture is no longer used in AFP . Instead, a subset of GOCA was defined for use in AFP environments, called Graphics Object Content Architecture for Advanced
Function Presentation (AFP GOCA). Usually when the term “GOCA” is used in AFP documentation, it means AFP
GOCA.
Graphics Object Content Architecture for Advanced
Function Presentation (AFP GOCA). A subset of the
GOCA architecture, originally defined by IBM, specifically designed for AFP environments. See Graphics Object
Content Architecture (GOCA).
graphics presentation space (GPS). A two-dimensional conceptual space in which the application user's view of the specified picture is generated. The picture can then be mapped onto an output medium. graphics presentation space window . The portion of a graphics presentation space that can be mapped to a graphics object area on a logical page. graphics primitive. A basic construct used by an output device to draw a picture. Examples of graphics primitives are arc, line, fillet, character string, and marker . graphics processor . The processing capability required to interpret a GOCA object, that is, to present the picture represented by the object. It includes the environment interface, which interprets commands and instructions, and the drawing processor , which interprets the drawing orders. graphics segment. A set of graphics drawing orders contained within a Begin Segment command. See also segment. grayscale image. Images whose image data elements are represented by multiple bits and whose image data element values are mapped to more than one level of brightness through an image data element structure parameter or a look-up table.
GRID. See Global Resource Identifier .
H hexadecimal. A number system with a base of sixteen.
The decimal digits 0 through 9 and characters A through F are used to represent hexadecimal digits. The hexadecimal digits A through F correspond to the decimal numbers 10 through 15, respectively . An example of a hexadecimal number is X'1B', which is equal to the decimal number 27. highlight color . A spot color that is used to accentuate or contrast monochromatic areas. See also spot color . highlighting. The emphasis of displayed or printed information. Examples are increased intensity of selected characters on a display screen and exception highlighting on an IPDS printer . hollow font. A font design in which the graphic character shapes include only the outer edges of the strokes. horizontal font size. (1) A characteristic value, parallel to the character baseline, that represents the size of all graphic characters in a font. Synonymous with font width. (2) In a font character set, nominal horizontal font size is a font-designer defined value corresponding to the nominal character increment for a font character set. The value is generally the width of the space character and is defined differently for fonts with different spacing characteristics.
GPS • horizontal font size [GOCA-D-048]

---

• For fixed-pitch, uniform character increment fonts: the fixed character increment, which is also the space character increment [GOCA-D-049]
• For PSM fonts: the width of the space character [GOCA-D-050]
• For typographic fonts and proportionally-spaced fonts: [GOCA-D-051]
one-third of the vertical font size, which is also the default size of the space character .
The font designer can also define a minimum and a maximum horizontal font size to represent the limits of scaling. (3) In font referencing, the specified horizontal font size is the desired size of the font when the characters are presented. If this size is different from the nominal horizontal font size specified in a font character set, the character shapes and character metrics might need to be scaled prior to presentation. horizontal scale factor . In outline-font referencing, the specified horizontal adjustment of the Em square. The horizontal scale factor is specified in 1440ths of an inch.
When the horizontal and vertical scale factors are different, anamorphic scaling occurs. See also vertical scale factor .
host. In the IPDS architecture, a computer that drives a printer .
I
ID. Identifier .
I direction. Synonymous with inline direction.
IEEE. Institute of Electrical and Electronics Engineers.
image. An electronic representation of a picture produced by means of sensing light, sound, electron radiation, or other emanations coming from the picture or reflected by the picture. An image can also be generated directly by software without reference to an existing picture. image content. Image data and its associated image data parameters. image coordinate system. An X,Y Cartesian coordinate system using only the fourth quadrant with positive values for the Y axis. The origin of an image coordinate system is its upper left hand corner . An X,Y coordinate specifies a presentation position that corresponds to one and only one image data element in the image content. image data. Rectangular arrays of raster information that define an image. image object. An object that contains image data. See also object.
Image Object Content Architecture (IOCA). An architected collection of constructs used to interchange and present images. image segment. Image content bracketed by Begin
Segment and End Segment self-defining fields. See also segment.
IM Image. A migration image object that is resolution dependent, bilevel, and cannot be compressed or scaled.
Contrast with IO Image.
immediate mode. The mode in which segments are executed as they are received and then discarded.
inline direction (I). (1) The direction in which successive characters appear in a line of text. (2) In GOCA, the direction specified by the character angle attribute.
Synonymous with I direction.
Intelligent Printer Data Stream (IPDS). An architected host-to-printer data stream that contains both data and controls defining how the data is to be presented. interchange. The predictable interpretation of shared information in an environment where the characteristics of each process need not be known to all other processes.
Contrast with exchange.
internal parameter . In PT OCA, a parameter whose current value is contained within the object. Contrast with external parameter .
International Organization for Standardization (ISO). An organization of national standards bodies from various countries established to promote development of standards to facilitate international exchange of goods and services, and develop cooperation in intellectual, scientific, technological, and economic activity . interoperability . The capability to communicate, execute programs, or transfer data among various functional units in a way that requires the user to have little or no knowledge of the unique characteristics of those units.
IOCA. See Image Object Content Architecture.
IO Image. An image object containing IOCA constructs.
Contrast with IM Image.
IPDS. See Intelligent Printer Data Stream.
ISO. See International Organization for Standardization.
italics. A typeface with characters that slant upward to the right. In FOCA, italics is the common name for the defined inclined typeface posture attribute or parameter .
K
Kanji. A graphic character set for symbols used in
Japanese ideographic alphabets.
kerning. The design of graphic characters so that their character boxes overlap, resulting in the reduction of space horizontal scale factor • kerning [GOCA-D-052]

---

between characters. This allows characters to be designed for cursive languages, ligatures, and proportionally-spaced fonts. An example of kerning is the printing of adjacent graphic characters so they overlap on the left or right side. keyword. A two-part self-defining parameter consisting of a one-byte identifier and a one-byte value.
L
LAN. See local area network.
landscape. A presentation orientation in which the X m axis is parallel to the long sides of a rectangular physical medium. Contrast with portrait. language. A set of symbols, conventions, and rules that is used for conveying information. See also pragmatics, semantics, and syntax.
LCID. See Local Character Set Identifier .
leading. A printer's term for the amount of space between lines of a printed page. Leading refers to the lead slug placed between lines of type in traditional typesetting.
LID. See local identifier .
ligature. A single glyph representing two or more characters. Examples of characters that can be presented as ligatures are ff and ffi. linear gradient. A gradient where the color change takes place along a line. Contrast with radial gradient. line attributes. Those attributes that pertain to straight and curved lines. Examples of line attributes are line type and line width. line type. A line attribute that controls the appearance of a line. The line type can either be a standard line type value or a custom line type value. Contrast with line width. line width. A line attribute that controls the appearance of a line. Contrast with line type. local area network (LAN). A data network located on a user's premises in which serial transmission is used for direct data communication among data stations.
Local Character Set Identifier (LCID). A local identifier used as a character , marker , or pattern set attribute.
local identifier (LID). An identifier that is mapped by the controlling environment to a named resource.
location. A site within a data stream. A location is specified in terms of an offset in the number of structured fields from the beginning of a data stream, or in the number of bytes from another location within the data stream. logical page. A presentation space. One or more object areas can be mapped to a logical page. A logical page has specifiable characteristics, such as size, shape, orientation, and offset. The shape of a logical page is the shape of a rectangle. Orientation and offset are specified relative to a medium coordinate system. logical unit. A unit of linear measurement expressed with a unit base and units per unit-base value. For example, in
MO:DCA and IPDS architectures, the following logical units are used:
• 1 logical unit = 1/1440 inch (unit base = 10 inches, units per unit base = 14,400) [GOCA-D-053]
• 1 logical unit = 1/240 inch (unit base = 10 inches, units per unit base = 2400) [GOCA-D-054]
Synonymous with L-unit.
look-up table (LUT). (1) A table used to map one or more input values to one or more output values. (2) A logical list of colors or intensities. The list has a name and can be referenced to select a color or intensity . See also color table. lowercase. Pertaining to small letters as distinguished from capital letters. Examples of small letters are a, b, and g. Contrast with uppercase.
L-unit. Synonymous with logical unit.
LUT . See look-up table.
M mandatory support level. Within the base-and-towers concept, the smallest portion of architected function that is allowed to be implemented. This is represented by a base with no towers. Synonymous with base support level. marker . A symbol with a recognizable appearance that is used to identify a particular location. An example of a marker is a symbol that is positioned by the center point of its cell. marker attributes. The characteristics that control the appearance of a marker . Examples of marker attributes are cell-size and color . marker cell. A conceptual rectangular box that can include a marker symbol and the space surrounding that symbol. marker precision. A method used to specify the degree of influence that marker attributes have on the appearance of a marker; this method has been made obsolete . marker set. In GOCA, a set of graphic symbols used to indicate a position. marker symbol. A symbol that is used for a marker . keyword • marker symbol [GOCA-D-055]

---

meaning. A table heading for architecture syntax. The entries under this heading convey the meaning or purpose of a construct. A meaning entry can be a long name, a description, or a brief statement of function. measurement base. A base unit of measure from which other units of measure are derived. media. Plural of medium. See also medium. medium. A two-dimensional conceptual space with a base coordinate system from which all other coordinate systems are either directly or indirectly derived. A medium is mapped onto a physical medium in a device-dependent manner . Synonymous with medium presentation space.
See also logical page, physical medium, and presentation space.
medium presentation space. A two-dimensional conceptual space with a base coordinate system from which all other coordinate systems are either directly or indirectly derived. A medium presentation space is mapped onto a physical medium in a device-dependent manner .
Synonymous with medium. See also logical page, physical medium, and presentation space.
metadata. Descriptive information that is associated with and augments other data.
Metadata Object Content Architecture (MOCA). A resource object architecture to carry metadata that serves to provide context or additional information about an AFP object or other AFP data. mil. 1/1000 inch. mix. A method used to determine how the color of a graphics primitive is combined with the existing color of a graphics presentation space. See also foreground mix and background mix.
Mixed Object Document Content Architecture (MO:DCA). An architected, device-independent data stream for interchanging documents.
MOCA. See Metadata Object Content Architecture.
MO:DCA. See Mixed Object Document Content
Architecture.
monospaced font. A font with graphic characters having a uniform character increment. The distance between reference points of adjacent graphic characters is constant in the escapement direction. The blank space between the graphic characters can vary . Synonymous with uniformly spaced font. Contrast with proportionally spaced font and typographic font. move order . A drawing order that specifies or implies movement from the current position to a given position.
See also current position and given position.
N name. A table heading for architecture syntax. The entries under this heading are short names that give a general indication of the contents of the construct. named color . A color that is specified with a descriptive name. An example of a named color is “green”. neutral white. A color attribute that gives a device- dependent default color , typically white on a screen and black on a printer . Note that neutral white and color of medium are two different colors. no operation (NOP). A construct whose execution causes a product to proceed to the next instruction to be processed without taking any other action.
NOP . See no operation.
N-up. The partitioning of a side of a sheet into a fixed number of equal size partitions. For example, 4-up divides each side of a sheet into four equal partitions.
O object. (1) A collection of structured fields. The first structured field provides a begin-object function, and the last structured field provides an end-object function. The object can contain one or more other structured fields whose content consists of one or more data elements of a particular data type. An object can be assigned a name that can be used to reference the object. Examples of objects are presentation text, font, graphics, and image objects. (2) Something that a user works with to perform a task. object area. A rectangular area in a presentation space into which a data object is mapped. The presentation space can be for a page or an overlay . Examples are a graphics object area, an image object area, and a bar code object area. object data. A collection of related data elements bundled together . Examples of object data include graphic characters, image data elements, and drawing orders. obsolete. Removed from the architecture, and thus ignored by receivers. offline. A device state in which the device is not under the direct control of a host. Contrast with online. offset. A table heading for architecture syntax. The entries under this heading indicate the numeric displacement into a construct. The offset is measured in meaning • offset [GOCA-D-056]

---

bytes and starts with byte zero. Individual bits can be expressed as displacements within bytes.
online. A device state in which the device is under the direct control of a host. Contrast with offli ne.
order . Synonymous with drawing order .
orientation. The angular distance a presentation space or object area is rotated in a specified coordinate system, expressed in degrees and minutes. For example, the orientation of printing on a physical medium, relative to the
X m axis of the X m ,Y m coordinate system. See also text orientation. origin. The point in a coordinate system where the axes intersect. Examples of origins are the addressable position in an X m ,Y m coordinate system where both coordinate values are zero and the character reference point in a character coordinate system. orthogonal. Intersecting at right angles. An example of orthogonal is the positional relationship between the axes of a Cartesian coordinate system. outline font. A shape technology in which the graphic character shapes are represented in digital form by a series of mathematical expressions that define the outer edges of the strokes. The resultant graphic character shapes can be either solid or hollow . overlay . (1) A resource object that can contains presentation data such as text, image, graphics, and bar code data. Overlays define their own environment and are often used as pre-defined pages or electronic forms. (2) The final representation of such an object on a physical medium. Contrast with page segment. overscore. A line parallel to the baseline and placed above the character . overstrike. In PTOCA, the presentation of a designated character as a string of characters in a specified text field.
The intended effect is to make the resulting presentation appear as though the text field, whether filled with characters or blanks, has been marked out with the overstriking character . overstriking. The method used to merge two or more graphic characters at the same addressable position in a presentation space or on a physical medium.
P page. (1) A data stream object delimited by a Begin Page structured field and an End Page structured field. A page can contain presentation data such as text, image, graphics, and bar code data. (2) The final representation of such an object on a physical medium. page segment. (1) In the IPDS architecture, a resource object that can contain text, image, graphics, and bar code data. Page segments do not define their own environment, but are processed in the existing environment. (2) In the
MO:DCA architecture, a resource object that can contain any mixture of bar code objects, graphics objects, and
IOCA image objects. A page segment does not contain an active environment group. The environment for a page segment is defined by the active environment group of the including page or overlay . (3) The final representation of such an object on a physical medium. Contrast with overlay . parameter . (1) A variable that is given a constant value for a specified application. (2) A variable used in conjunction with a command to affect its result. pattern. A graphic symbol used repeatedly to fill an area. pattern attributes. The characteristics that specify the appearance of a pattern. pattern reference point. A position in the graphics presentation space to be used as the origin of a custom pattern; the pattern is tiled in all directions from this position. pattern set. In GOCA, a set of graphic symbols used to fill the interior of an area. pattern symbol. A graphic symbol that is used for a pattern. pel. The smallest printable or displayable unit on a physical medium. In computer graphics, the smallest element of a physical medium that can be independently assigned color and intensity . Pels per inch is often used as a measurement of presentation granularity . Synonymous with picture element and pixel. physical medium. A physical entity on which information is presented. Examples of a physical medium are a sheet of paper , a roll of paper , an envelope, and a display screen.
See also medium presentation space and sheet.
physical printable area. A bounded area defined on a side of a sheet within which printing can take place. The physical printable area is an attribute of sheet size and printer capabilities, and cannot be altered by the host. The physical printable area is mapped to the medium presentation space, and is used in user printable area and valid printable area calculations. Contrast with user printable area and valid printable area. picture element. Synonymous with pel. pixel. Synonymous with pel. point. (1) A unit of measure used mainly for measuring typographical material. There are seventy-two points to an inch. (2) In GOCA, a parameter that specifies the position online • point [GOCA-D-057]

---

within the drawing order coordinate space. See also drawing order coordinate space.
polyline. A sequence of connected lines.
portrait. A presentation orientation in which the X m axis is parallel to the short sides of a rectangular physical medium. Contrast with landscape. position. A position in a presentation space or on a physical medium that can be identified by a coordinate from the coordinate system of the presentation space or physical medium. See also picture element. Synonymous with addressable position. posture. Inclination of a letter with respect to a vertical axis. Examples of inclination are upright and inclined. pragmatics. Information related to the usage of a construct. See also semantics and syntax. presentation device. A device that produces character shapes, graphics pictures, images, or bar code symbols on a physical medium. Examples of a physical medium are a display screen and a sheet of paper . presentation position. An addressable position that is coincident with a character reference point. See also addressable position and character reference point. presentation services. In printing, a software component that communicates with a printer using a printer data stream, such as the IPDS data stream, to print pages, download and manage print resources, and handle exceptions. presentation space. A conceptual address space with a specified coordinate system and a set of addressable positions. The coordinate system and addressable positions can coincide with those of a physical medium.
Examples of presentation spaces are medium, logical page, and object area. See also graphics presentation space, logical page, and medium presentation space. presentation text object. An object that contains presentation text data. See also object.
Presentation T ext Object Content Architecture (PTOCA). An architected collection of constructs used to interchange and present presentation text data. process color . A color that is specified as a combination of the components, or primaries, of a color space. A process color is rendered by mixing the specified amounts of the primaries. An example of a process color is C=0.1,
M=0.8, Y=0.2, K=0.1 in the cyan/magenta/yellow/black (CMYK) color space. Contrast with spot color . prolog. The first portion of a segment's data. Prologs are optional. They contain attribute settings and drawing controls. Synonymous with segment prolog. proportionally spaced font. A font with graphic characters that have varying character increments.
Proportional spacing can be used to provide the appearance of even spacing between presented characters and to eliminate excess blank space around narrow characters. An example of a narrow character is the letter i. Synonymous with typographic font. Contrast with monospaced font and uniformly spaced font.
Proportional Spacing Machine font (PSM font). A font originating with the electric typewriter and having character increment values that are integer multiples of the narrowest character width.
PSM font. See Proportional Spacing Machine font.
PT OCA. See Presentation T ext Object Content
Architecture.
R radial gradient. A gradient where the color change takes place between two full arcs. Contrast with linear gradient. range. A table heading for architecture syntax. The entries under this heading Givenumeric ranges applicable to a construct. The ranges can be expressed in binary , decimal, or hexadecimal. The range can consist of a single value. raster pattern. A rectangular array of pels arranged in rows called scan lines. relative coordinate. One of the coordinates that identify the location of an addressable point by means of a displacement from some other addressable point. Contrast with absolute coordinate. relative line. A straight line developed from a specified point by a given displacement. relative metrics. Graphic character measurements expressed as fractions of a square, called the Em square, whose sides correspond to the vertical size of the font.
Because the measurements are relative to the size of the
Em square, the same metrics can be used for different point sizes and different raster pattern resolutions. Relative metrics require defining the unit of measure for the Em square, the point size of the font, and, if applicable, the resolution of the raster pattern. relative positioning. The establishment of a position within a coordinate system as an offset from the current position. Contrast with absolute positioning. repeating group. A group of parameter specifications that can be repeated. reserved. Having no assigned meaning and put aside for future use. The content of reserved fields is not used by receivers, and should be set by generators to a specified polyline • reserved [GOCA-D-058]

---

value, if given, or to binary zeros. A reserved field or value can be assigned a meaning by an architecture at any time.
reset color . The color of a presentation space before any data is added to it. Synonymous with color of medium.
resolution. (1) A measure of the sharpness of an input or output device capability , as given by some measure relative to the distance between two points or lines that can just be distinguished. (2) The number of addressable pels per unit of length. resolution correction. A method used to present an image on a printer without changing the physical size or proportions of the image when the resolutions of the printer and the image are different. resource. An object that is referenced by a data stream or by another object to provide data or information.
Resource objects can be stored in libraries. In the MO:DCA architecture, resource objects can be contained within a resource group. Examples of resources are fonts, overlays, and page segments. retired. Set aside for a particular purpose, and not available for any other purpose. Retired fields and values are specified for compatibility with existing products and identify one of the following:
• Fields or values that have been used by a product in a manner not compliant with the architected definition [GOCA-D-059]
• Fields or values that have been removed from an architecture [GOCA-D-060]
RGB. Red, green and blue, the additive primary colors.
RGB color space. The basic additive color model used for color video display , as on a computer monitor .
rotating. In computer graphics, turning all or part of a picture about an axis perpendicular to the presentation space. rotation. The orientation of a presentation space with respect to the coordinate system of a containing presentation space. Rotation is measured in degrees in a clockwise direction. Zero-degree rotation exists when the angle between a presentation space's positive X axis and the containing presentation space's positive X axis is zero degrees. Contrast with character rotation. row . A subarray that consists of all elements that have an identical position within the high dimension of a regular two-dimensional array . rule. A solid line of any line width.
S
SBCS. See single-byte character set.
SBIN. A data type for architecture syntax that indicates that one or more bytes be interpreted as a signed binary number , with the sign bit in the high-order position of the leftmost byte. Positive numbers are represented in true binary notation with the sign bit set to B'0'. Negative numbers are represented in twos-complement binary notation with a B'1' in the sign-bit position. scaling. Making all or part of a picture smaller or larger by multiplying the coordinate values of the picture by a constant amount. If the same multiplier is applied along both dimensions, the scaling is uniform, and the proportions of the picture are unaffected. Otherwise, the scaling is anamorphic, and the proportions of the picture are changed. See also anamorphic scaling. scaling ratio. The ratio of an image-object-area size to its image-presentation-space size. scan line. A series of picture elements. Scan lines in raster patterns form images. See also picture element and raster pattern. section. A portion of a double-byte code page that consists of 256 consecutive entries. The first byte of a two- byte code point is the section identifier . A code-page section is also called a code-page ward in some environments. See also code page and code point. section identifier . A value that identifies a section.
Synonymous with section number .
section number . A value that identifies a section.
Synonymous with section identifier .
segment. (1) In GOCA, a set of graphics drawing orders contained within a Begin Segment command. See also graphics segment. (2) In IOCA, image content bracketed by Begin Segment and End Segment self-defining fields.
See also image segment.
segment chain. A string of segments that defines a picture.
segment exception condition. An architecture-provided classification of the errors that can occur in a segment.
Segment exception conditions are raised when a segment error is detected. Examples of segment errors are segment format, parameter content, and sequence errors. segment offset. A position within a segment, measured in bytes from the beginning of the segment. The beginning of a segment is always at offset zero. segment prolog. The first portion of a segment's data.
Prologs are optional. They contain attribute settings and drawing controls. Synonymous with prolog.
segment properties. The segment characteristics used by a drawing process. Examples of segment properties are segment name and segment length. reset color • segment properties [GOCA-D-061]

---

semantics. The meaning of the parameters of a construct. See also pragmatics and syntax.
shear . The angle of slant of a character cell that is not perpendicular to a baseline. Synonymous with character shear . sheet. A division of the physical medium; multiple sheets can exist on a physical medium. For example, a roll of paper might be divided by a printer into rectangular pieces of paper , each representing a sheet. Envelopes are an example of a physical medium that comprises only one sheet. The IPDS architecture defines four types of sheets: cut-sheet media, continuous-form media, envelopes, and computer output on microfilm. Each type of sheet has a top edge. A sheet has two sides, a front side and a back side.
Synonymous with form.
side. A physical surface of a sheet. A sheet has a front side and a back side. See also sheet.
simplex printing. A method used to print data on one side of a sheet; the other side is left blank. Contrast with duplex printing. single-byte character set (SBCS). A character set that can contain up to 256 characters. single-byte coded font. A coded font in which the code points are one byte long. slope. The posture, or incline, of the main strokes in the graphic characters of a font. Slope is specified in degrees by a font designer . spot color . A color that is specified with a unique identifier such as a number . A spot color is normally rendered with a custom colorant instead of with a combination of process color primaries. See also highlight color . Contrast with process color . standard action. The architecture-defined action to be taken on detecting an exception condition, when the controlling environment specifies that processing should continue. standard line type value. A predefined line type, like solid, invisible, or dash-dot. Contrast with custom line type value. stroke. A straight or curved line used to create the shape of a letter . structured field. A self-identifying, variable-length, bounded record, which can have a content portion that provides control information, data, or both. See also document element structured field introducer . In the MO:DCA architecture, the header component of a structured field that provides information that is common for all structured fields.
Examples of information that is common for all structured fields are length, function type, and category type.
Examples of structured field function types are begin, end, data, and descriptor . Examples of structured field category types are presentation text, image, graphics, and page. subset. Within the base-and-towers concept, a portion of architecture represented by a particular level in a tower or by a base. See also subsetting tower . subsetting tower . Within the base-and-towers concept, a tower representing an aspect of function achieved by an architecture. A tower is independent of any other towers. A tower can be subdivided into subsets. A subset contains all the function of any subsets below it in the tower . See also subset. symbol. (1) A visual representation of something by reason of relationship, association, or convention. (2) In
GOCA, the subpicture referenced as a character definition within a font character set and used as a character , marker , or fill pattern. A bitmap can also be referenced as a symbol for use as a fill pattern. symbol set. A coded font that is usually simpler in structure than a fully described font. Symbol sets are used where typographic quality is not required. Examples of devices that might not provide typographic quality are dot- matrix printers and displays. See also character set, marker set, and pattern set. syntax. The rules governing the structure of a construct.
See also pragmatics and semantics.
T text. A graphic representation of information. T ext can consist of alphanumeric characters and symbols arranged in paragraphs, tables, columns, and other shapes. An example of text is the data sent in an IPDS W rite T ext command. text orientation. A description of the appearance of text as a combination of inline direction and baseline direction.
See also baseline direction, inline direction, and orientation.
text presentation. The transformation of document graphic character content and its associated font information into a visible form. An example of a visible form of text is character shapes on a physical medium. toned. Containing marking agents such as toner or ink.
Contrast with untoned.
trimming. Eliminating those parts of a picture that are outside of a clipping boundary such as a viewing window or presentation space. Synonymous with clipping. triplet. A three-part self-defining variable-length parameter consisting of a length byte, an identifier byte, and parameter-value bytes. semantics • triplet [GOCA-D-062]

---

triplet identifier . A one-byte type identifier for a triplet.
truncation. Planned or unplanned end of a presentation space or data presentation. This can occur when the presentation space extends beyond one or more boundaries of its containing presentation space or when there is more data than can be contained in the presentation space. type. A table heading for architecture syntax. The entries under this heading indicate the types of data present in a construct. Examples include: BITS, CHAR, CODE, SBIN,
UBIN, and UNDF .
typeface. All characters of a single type family or style, weight class, width class, and posture, regardless of size.
For example, Helvetica Bold Condensed Italics, in any point size.
type family . All characters of a single design, regardless of attributes such as width, weight, posture, and size.
Examples are Courier and Gothic.
type structure. Attributes of characters other than type family or typeface. Examples are solid shape, hollow shape, and overstruck. type style. The form of characters within the same font, for example, Courier or Gothic. type weight. A parameter indicating the degree of boldness of a typeface. A character's stroke thickness determines its type weight. Examples are light, medium, and bold. Synonymous with weight class. type width. A parameter indicating a relative change from the font's normal width-to-height ratio. Examples are normal, condensed, and expanded. Synonymous with width class. typographic font. A font with graphic characters that have varying character increments. Proportional spacing can be used to provide the appearance of even spacing between presented characters and to eliminate excess blank space around narrow characters. An example of a narrow character is the letter i. Synonymous with proportionally spaced font. Contrast with monospaced font and uniformly spaced font.
U
UBIN. A data type for architecture syntax, indicating one or more bytes to be interpreted as an unsigned binary number . unarchitected. Identifies data that is neither defined nor controlled by an architecture. Contrast with architected. underscore. A method used to create an underline beneath the characters in a specified text field. An example of underscore is the line presented under one or more characters. Also a special graphic character used to implement the underscoring function.
UNDF . A data type for architecture syntax, indicating one or more bytes that are undefined by the architecture.
Unicode. A character encoding standard for information processing that includes all major scripts of the world.
Unicode defines a consistent way of encoding multilingual text. Unicode specifies a numeric value, a name, and other attributes—such as directionality—for each of its characters; for example, the name for $ is “dollar sign” and its numeric value is X'0024'. This Unicode value is called a
Unicode code point and is represented as U+nnnn.
Unicode provides for three encoding forms (UTF-8, UTF- 16, and UTF-32), described as follows:
UTF-8 A byte-oriented form that is designed for ease of use in traditional ASCII environments. Each UTF-8 code point contains from one to four bytes. All
Unicode code points can be encoded in
UTF-8 and all 7-bit ASCII characters can be encoded in one byte.
UTF-16 The default Unicode encoding. A fixed, two-byte Unicode encoding form that can contain surrogates and identifies the byte order of each UTF-16 code point via a Byte Order Mark in the first 2 bytes of the data. Surrogates are pairs of
Unicode code points that allow for the encoding of as many as 1 million additional characters without any use of escape codes.
UTF-16BE UTF-16 that uses big endian byte order;
this is the byte order for all multi-byte data within AFP data streams. The Byte
Order Mark is not necessary when the data is externally identified as UTF-16BE (or UTF-16LE).
UTF-16LE UTF-16 that uses little endian byte order .
UTF-32 A fixed, four-byte Unicode encoding form in which each UTF-32 code point is precisely identical to the Unicode code point.
UTF-32BE UTF-32 serialized as bytes in most- significant-byte-first order (big endian).
UTF-32BE is structurally the same as
UCS-4.
UTF-32LE UTF-32 serialized as bytes in least- significant-byte-first order (little endian). uniformly spaced font. A font with graphic characters having a uniform character increment. The distance between reference points of adjacent graphic characters is constant in the escapement direction. The blank space between the graphic characters can vary . Synonymous with monospaced font. Contrast with proportionally spaced font and typographic font. triplet identifier • uniformly spaced font [GOCA-D-063]

---

unit base. A one-byte code that represents the length of the measurement base. For example, X'00' might specify that the measurement base is ten inches. untoned. Unmarked portion of a physical medium.
Contrast with toned.
uppercase. Pertaining to capital letters. Examples of capital letters are A, B, and C. Contrast with lowercase.
user printable area (UP A). The portion of the physical printable area to which user-generated data is restricted.
See also logical page, physical printable area, and valid printable area.
V valid printable area (VP A). The intersection of a logical page with the area of the medium presentation space in which printing is allowed. If the logical page is a secure overlay , the area in which printing is allowed is the physical printable area. If the logical page is not a secure overlay and if a user printable area is defined, the area in which printing is allowed is the intersection of the physical printable area with the user printable area. If a user printable area is not defined, the area in which printing is allowed is the physical printable area. See also logical page, physical printable area, and user printable area. vector graphics. A vector has a defined starting point, a designated direction, and a specified distance. V ector graphics is line-based graphics data, where vectors determine how straight and curved lines are shaped between specific points. A picture consists of lines and colors to fill the areas enclosed by the lines. vertical font size. (1) A characteristic value, perpendicular to the character baseline, that represents the size of all graphic characters in a font. Synonymous with font height. (2) In a font character set, nominal vertical font size is a font-designer defined value corresponding to the nominal distance between adjacent baselines when character rotation is zero degrees and no external leading is used. This distance represents the baseline-to-baseline increment that includes the font's maximum baseline extent and the designer's recommendation for internal leading.
The font designer can also define a minimum and a maximum vertical font size to represent the limits of scaling. (3) In font referencing, the specified vertical font size is the desired size of the font when the characters are presented. If this size is different from the nominal vertical font size specified in a font character set, the character shapes and character metrics might need to be scaled prior to presentation. vertical scale factor . In outline-font referencing, the specified vertical adjustment of the Em square. The vertical scale factor is specified in 1440ths of an inch. When the horizontal and vertical scale factors are different, anamorphic scaling occurs. See also horizontal scale factor .
VP A. See valid printable area.
W weight class. A parameter indicating the degree of boldness of a typeface. A character's stroke thickness determines its weight class. Examples are light, medium, and bold. Synonymous with type weight. width class. A parameter indicating a relative change from the font's normal width-to-height ratio. Examples are normal, condensed, and expanded. Synonymous with type width. window . A predefined part of a graphics presentation space. See also graphics presentation space window .
X
X g ,Y g coordinate system. The graphics presentation space (GPS) coordinate system.
X m ,Y m coordinate system. (1) In the IPDS architecture, the medium presentation space coordinate system. (2) In the MO:DCA architecture, the medium coordinate system.
X oa ,Y oa coordinate system. The object area coordinate system.
X ol ,Y ol coordinate system. The overlay coordinate system.
X pg ,Y pg coordinate system. The coordinate system of a page presentation space. This coordinate system describes the size, position, and orientation of a page presentation space. Orientation of an X pg ,Y pg coordinate system is relative to an environment-specified coordinate system, for example, an X m ,Y m coordinate system. unit base • X pg ,Y pg coordinate system [GOCA-D-064]

---

Copyright © AFP Consortium 1997, 2017 225
A
AFPC . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . iii, vii, 4 arc parameters . . . . . . . . . . . . . . . . . . . . . . . 23, 130 arc primitives . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .24 areas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .36 areas primitive. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .36 valid definitions . . . . . . . . . . . . . . . . . . . . . . . . . .38 attributes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .12 character . . . . . . . . . . . . . . . . . . . . . . . . . . . . 13, 56 drawing . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .12 image . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .59 line . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 12, 29 marker . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 13, 58 obsolete . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195 pattern . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 13, 50 attributes, current . . . . . . . . . . . . . . . . . . . . . . . . . .70 attributes, primitives color . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .14 color tables . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .14 line end . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 32-33 line join . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 32, 34 line type . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .29 line width . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .32 mix . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .16 mix and background mix values . . . . . . .17
B background mix values . . . . . . . . . . . . . . . . . . . .17
Begin Segment command . . . . . . . . . . . . . . . .75 box primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .28 boxes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .28
C chained segments . . . . . . . . . . . . . . . . . . . . . . . . .61 character attributes . . . . . . . . . . . . . . . . . . . 13, 56 character precision character strings . . . . . . . . . . . . . . . . . . 53, 138 character precision attribute . . . . . . . . 51, 138 character strings . . . . . . . . . . . . . . . . . . . . . . . . . . .51 device-specific (character) precision . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .53 device-specific (string) precision . . . . . .56 coexistence functions . . . . . . . . . . . . . . . . . . . 195 color attribute . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .14 color table . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .14 commands . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1 1, 199
Begin Segment . . . . . . . . . . . . . . . . . . . . . . . . . .75 communication with the controlling environment . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .65 control instructions . . . . . . . . . . . . . . . . . . . . . .65 compliance . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 175 computer graphics . . . . . . . . . . . . . . . . . . . . . . . . . . 7 concepts of GOCA . . . . . . . . . . . . . . . . . . . . . . . . . . 7 control instructions . . . . . . . . . . . . . . 1 1, 65, 199 coordinate data . . . . . . . . . . . . . . . . . . . . . . . . . . . .79 drawing orders . . . . . . . . . . . . . . . . . . . . . . . . . . .79 offset data . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .79 coordinate space definitions
Drawing Order Coordinate Space (DOCS) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .13
Graphics Presentation Space (GPS) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .13
Usable Area (UA) . . . . . . . . . . . . . . . . . . . . . . .14 cross-references . . . . . . . . . . . . . . . . . . . . . . . . . 199 cubic bezier curve primitive . . . . . . . . . . . . . . .27 current attributes . . . . . . . . . . . . . . . . . . . . . . . . . . .70 current defaults . . . . . . . . . . . . . . . . . . . . . . . . . . . .79 current position . . . . . . . . . . . . . . . . . . . . . . . . . . . . .19 in drawing orders . . . . . . . . . . . . . . . . . . . . . . . .79 curved lines . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .23
Cubic Bezier Curve primitive . . . . . . . . . . .27
Fillet primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . .26
Full Arc primitive . . . . . . . . . . . . . . . . . . . . . . . . .24
Partial Arc primitive . . . . . . . . . . . . . . . . . . . . .25 custom patterns . . . . . . . . . . . . . . . . . . . . . . . 41-42 bilevel . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .42 full-color . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .42
D default pattern set . . . . . . . . . . . . . . . . . . . . . . . . . .39 default values, drawing orders . . . . . . . . . . .79 device-specific (character) precision . . . 53, 138 device-specific (string) precision . . . 56, 138
DR/2V0 subset . . . . . . . . . . . . . . . . . . . . . . . 17, 176 drawing attributes . . . . . . . . . . . . . . . . . . . . . . . . . .12 drawing control instructions
Set Current Defaults . . . . . . . . . . . . . . . . . . . .66 drawing defaults . . . . . . . . . . . . . . . . . . . . . . 70, 79 drawing order alignment . . . . . . . . . . . . . . . . . .79
Drawing Order Coordinate Space (DOCS) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .13
Drawing Order Subset parameter (retired) . . . . . . . . . . . . . . . . . . . . . . . . . . . . 181, 197 drawing orders . . . . . . . . 1 1, 61, 73, 200, 202
Begin Area (GBAR) order . . . . . . . . . . . . . .82
Begin Custom Pattern (GBCP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .84
Begin Image (GBIMG, GCBIMG) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .87
Box (GBOX, GCBOX) orders . . . . . . . . . .90
Character String (GCHST , GCCHST) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .92
Comment (GCOMT) order . . . . . . . . . . . . .95 coordinate data . . . . . . . . . . . . . . . . . . . . . . . . . .79 offset data . . . . . . . . . . . . . . . . . . . . . . . . . . . . .79
Cubic Bezier Curve (GCBEZ, GCCBEZ) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .96 current position . . . . . . . . . . . . . . . . . . . . . . . . . .79 default values . . . . . . . . . . . . . . . . . . . . . . . . . . . .79 definition of . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .12
Delete Pattern (GDPT) order . . . . . . . . . .99
End Area (GEAR) order . . . . . . . . . . . . . . 101
End Custom Pattern (GECP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 102
End Image (GEIMG) order . . . . . . . . . . . 103
End Prolog (GEPROL) order . . . . . . . . . 104 example of syntax . . . . . . . . . . . . . . . . . . . . . . . . v exception conditions . . . . . . . . . . . . . . . . . . 168
Fillet (GFL T , GCFL T) orders. . . . . . . . . . 105 formats extended . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .78 fixed 1-byte . . . . . . . . . . . . . . . . . . . . . . . . . . . .78 fixed 2-byte . . . . . . . . . . . . . . . . . . . . . . . . . . . .78 long . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .78 order alignment . . . . . . . . . . . . . . . . . . . . . . .79
Full Arc (GF ARC, GCF ARC) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 107
Image Data (GIMD) order . . . . . . . . . . . . 109
Line (GLINE, GCLINE) orders . . . . . . . 1 10
Linear Gradient (GLGD) order . . . . . . . 1 12
Marker (GMRK, GCMRK) orders . . . . 1 16
No-Operation (GNOP1) order . . . . . . . 1 18 obsolete . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 196
Partial Arc (GP ARC, GCP ARC) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1 19
Radial Gradient (GRGD) order . . . . . . 122
Relative Line (GRLINE, GCRLINE) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 127
Segment Characteristics (GSGCH) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 129
Set Arc Parameters (GSAP) . . . . . . . . . 130
Set Background Mix (GSBMX) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 132
Set Character Angle (GSCA) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 133
Set Character Cell (GSCC) order . . . 134
Set Character Direction (GSCD) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 136
Set Character Precision (GSCR) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 138
Set Character Set (GSCS) order . . . . 140
Set Character Shear (GSCH) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 141
Set Color (GSCOL) order . . . . . . . . . . . . 142
Set Current Position (GSCP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 143
Set Custom Line Type (GSCL T) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 144
Set Extended Color (GSECOL) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 146
Set Fractional Line Width (GSFL W) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 147
Set Line End (GSLE) order . . . . . . . . . . . 148
Set Line Join (GSLJ) order . . . . . . . . . . . 149
Set Line Type (GSL T) order . . . . . . . . . . 150
Set Line Width (GSL W) order . . . . . . . . 151
Set Marker Cell (GSMC) order . . . . . . . 152
Set Marker Precision (GSMP) order (obsolete) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 196
Set Marker Set (GSMS) order . . . . . . . 154
Set Marker Symbol (GSMT) order . . . 155
Set Mix (GSMX) order . . . . . . . . . . . . . . . . 156
Set Pattern Reference Point (GSPRP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 157
Set Pattern Set (GSPS) order . . . . . . . 158
Set Pattern Symbol (GSPT) order . . . 159
Set Process Color (GSPCOL) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 161 drawing process check . . . . . . . . . . . . . . . . . . 168 drawing process controls . . . . . . . . . . . . . . . . .70 current position . . . . . . . . . . . . . . . . . . . . . . . . . .19 drawing processing environment . . . . . . . . .61 drawing processor . . . . . . . . . . . . . . . . . . . . . . . . . 1 1 drawing processor facilities drawing process controls . . . . . . . . . . . . . . .70 [GOCA-D-065]

---

E environment interface . . . . . . . . . . . . . . . . . . . . . 1 1 exception conditions . . . . . . . . . . . . 17, 19, 168 for Begin Area (GBAR) order . . . . . . . . . .83 for Begin Custom Pattern (GBCP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .85 for Begin Image (GBIMG, GCBIMG) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .89 for Box (GBOX, GCBOX) orders . . . . . .91 for Character String (GCHST , GCCHST) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .93 for Comment (GCOMT) order. . . . . . . . . .95 for Cubic Bezier Curve (GCBEZ,
GCCBEZ) orders . . . . . . . . . . . . . . . . . . . . . . .98 for Delete Pattern (GDPT) order . . . . . . .99 for End Area (GEAR) order . . . . . . . . . . 101 for End Custom Pattern (GECP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 102 for End Image (GEIMG) order . . . . . . . 103 for End Prolog (GEPROL) order . . . . . 104 for Fillet (GFL T , GCFL T) orders . . . . . . 106 for Full Arc (GF ARC, GCF ARC) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 108 for Image Data (GIMD) order . . . . . . . . 109 for Line (GLINE, GCLINE) orders . . . 1 1 1 for Linear Gradient (GLGD) order . . . 1 15 for Marker (GMRK, GCMRK) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1 17 for No-Operation (GNOP1) order . . . . 1 18 for Partial Arc (GP ARC, GCP ARC) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 120 for Radial Gradient (GRGD) order . . . 126 for Relative Line (GRLINE, GCRLINE) orders . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 128 for Segment Characteristics (GSGCH) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 129 for Set Arc Parameters (GSAP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 131 for Set Background Mix (GSBMX) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 132 for Set Character Angle (GSCA) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 133 for Set Character Cell (GSCC) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 135 for Set Character Direction (GSCD) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 137 for Set Character Precision (GSCR) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 139 for Set Character Set (GSCS) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 140 for Set Character Shear (GSCH) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 141 for Set Color (GSCOL) order . . . . . . . . . 142 for Set Current Position (GSCP) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 143 for Set Custom Line Type (GSCL T) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145 for Set Extended Color (GSECOL) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 146 for Set Fractional Line Width (GSFL W) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 147 for Set Line End (GSLE) order . . . . . . . 148 for Set Line Join (GSLJ) order . . . . . . . 149 for Set Line Type (GSL T) order . . . . . . 150 for Set Line Width (GSL W) order . . . . 151 for Set Marker Cell (GSMC) order . . . 153 for Set Marker Precision (GSMP) order (obsolete) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 196 for Set Marker Set (GSMS) order. . . . 154 for Set Marker Symbol (GSMT) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 155 for Set Mix (GSMX) order . . . . . . . . . . . . 156 for Set Pattern Reference Point (GSPRP) order . . . . . . . . . . . . . . . . . . . . . . . 157 for Set Pattern Set (GSPS) order . . . . 158 for Set Pattern Symbol (GSPT) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 160 for Set Process Color (GSPCOL) order . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 164
IPDS . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 192 obsolete . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 197 retired . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 197 summary of . . . . . . . . . . . . . . . . . . . . . . . . . . . . 167 command process checks . . . . . . . . . 167 exceptions with standard actions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 171 exceptions without standard actions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 169 instruction process checks . . . . . . . . 167 extended order format . . . . . . . . . . . . . . . . . . . . .78
F facilities for drawing processor . . . . . . . . . . .70 drawing process controls . . . . . . . . . . . . . . .70 facilities for graphics processor attributes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .70 fillet primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .26 fixed 1-byte format . . . . . . . . . . . . . . . . . . . . . . . . .78 fixed 2-byte format . . . . . . . . . . . . . . . . . . . . . . . . .78 foreground mix values . . . . . . . . . . . . . . . . . . . . .17 full arc primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . .24 fully described fonts . . . . . . . . . . . . . . . . . . . . . . .21
G
GOCA overview . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 9 gradients . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .43 linear . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .44 radial . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .46 graphics . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 7 graphics commands
Begin Segment . . . . . . . . . . . . . . . . . . . . . . . . . .75 graphics coordinate spaces . . . . . . . . . . . . . .13 graphics in IPDS environment . . . . . . . . . . 189
Graphics Presentation Space (GPS) . . . .13 graphics process facilities attributes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .70 graphics processor model . . . . . . . . . . . . . . . . . 9
GRS2 subset . . . . . . . . . . . . . . . . . . . 17, 175-176
GRS3 subset . . . . . . . . . . . . . . . . . . . . . . . . . 17, 178
I image attributes . . . . . . . . . . . . . . . . . . . . . . . . . . . .59 image primitive image definition. . . . . . . . . . . . . . . . . . . . . . . . . .59 image resolution . . . . . . . . . . . . . . . . . . . . . 88, 182 images . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .59 immediate mode . . . . . . . . . . . . . . . . . . . . . . . . . . .61 introduction to GOCA . . . . . . . . . . . . . . . . . . . . . . . 7
IPDS environment . . . . . . . . . . . . . . . . . . . . . . . 189
IPDS exceptions . . . . . . . . . . . . . . . . . . . . . . . . . 192
IPDS Graphics Command Set
Write Graphics . . . . . . . . . . . . . . . . . . . . . . . . . 191
Write Graphics Control
Graphics Area Position . . . . . . . . . . . . 190
Graphics Data Descriptor . . . . . . . . . . 191
Graphics Output Control . . . . . . . . . . . 190
L levels of subset
Base (mandatory), V ersion 0 . . . . . . . . . 175 drawing order levels, V ersion 0 . . . . . 176, 178
Level 2 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 176
Level 3 (GRS3) . . . . . . . . . . . . . . . . . . . . . 178 line attributes . . . . . . . . . . . . . . . . . . . . . . . . . . 12, 29 line end attribute . . . . . . . . . . . . . . . . . . . . . . 32-33 line join attribute. . . . . . . . . . . . . . . . . . . . . . . 32, 34 line primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .22 line type attribute . . . . . . . . . . . . . . . . . . . . . . . . . . .29 line width attribute . . . . . . . . . . . . . . . . . . . . . . . . .32 lines boxes
Box primitive . . . . . . . . . . . . . . . . . . . . . . . . . .28 curved . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 23, 26
Cubic Bezier Curve primitive . . . . . . . .27
Fillet primitive . . . . . . . . . . . . . . . . . . . . . . . . .26
Full Arc primitive . . . . . . . . . . . . . . . . . . . . . .24
Partial Arc primitive . . . . . . . . . . . . . . . . . . .25 straight . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .22
Line primitive . . . . . . . . . . . . . . . . . . . . . . . . . .22
Relative Line primitive. . . . . . . . . . . . . . . .22 long format. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .78
M marker attributes . . . . . . . . . . . . . . . . . . . . . . 13, 58 markers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .57 precision . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195 migration and coexistence support . . . . 195 migration functions . . . . . . . . . . . . . . . . . . . . . . 195 mix and background mix attribute values . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .17 mix attribute . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .16
MO:DCA environment . . . . . . . . . . . . . . . . . . . 179 modes immediate . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61
Move Type orders . . . . . . . . . . . . . . . . . . . . . . . . . .30
N notation used in formulas . . . . . . . . . . . . . . . . . . . . . . . . . . vi notices . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
O obsolete attributes . . . . . . . . . . . . . . . . . . . . . . . 195 obsolete drawing orders . . . . . . . . . . . . . . . . 196 obsolete exceptions . . . . . . . . . . . . . . . . . . . . . 197 obsolete functions . . . . . . . . . . . . . . . . . . . . . . . 195 orders, summary of . . . . . . . . . . . . . . . . . . . . . . . .80 output control definitions
GPS window . . . . . . . . . . . . . . . . . . . . . . . . . . . 190 graphics object areas . . . . . . . . . . . . . . . . . 190 mapping control options . . . . . . . . . . . . . . 190 mapping defaults . . . . . . . . . . . . . . . . . . . . . . 190 [GOCA-D-066]

---

output primitives . . . . . . . . . . . . . . . . . . . . . . . . . . .19 areas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .36 valid definitions . . . . . . . . . . . . . . . . . . . . . . .38 attributes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .12 boxes
Box . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .28 curved lines . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .23
Cubic Bezier Curve . . . . . . . . . . . . . . . . . . .27
Fillet . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .26
Full Arc . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .24
Partial Arc . . . . . . . . . . . . . . . . . . . . . . . . . . . . .25 images. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .59 overflow of . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .60 straight lines . . . . . . . . . . . . . . . . . . . . . . . . . . . . .22
Line . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .22
Relative Line . . . . . . . . . . . . . . . . . . . . . . . . . .22 symbol . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .20 character strings . . . . . . . . . . . . . . . . . . . . . .51 markers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .57 patterns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .39 overflow of output primitives . . . . . . . . . . . . . .60
P parameter type . . . . . . . . . . . . . . . . . . . . . . . . . . . . .71 partial arc primitive . . . . . . . . . . . . . . . . . . . . . . . . .25 pattern attributes . . . . . . . . . . . . . . . . . . . . . . 13, 50 patterns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .39 custom. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 41-42 bilevel . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .42 full-color . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .42 default pattern set . . . . . . . . . . . . . . . . . . . . . . .39 gradients . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .43 linear . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .44 radial . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .46 precision value character character strings . . . . . . . . . . . . . . . . 53, 138 marker . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 196 string character strings . . . . . . . . . . . . . . . . 56, 138 stroke . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 138 presentation spaces . . . . . . . . . . . . . . . . . . . . . . .13 primitive attributes . . . . . . . . . . . . . . . . . . . . . . . . .12 primitives. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .19 process controls parameter type . . . . . . . . . . . . . . . . . . . . . . . . . .71 processing of segments current attributes . . . . . . . . . . . . . . . . . . . . . . . .70 segment prolog . . . . . . . . . . . . . . . . . . . . . . . . . .62 processor facilities . . . . . . . . . . . . . . . . . . . . . . . . .70 prolog segment semantics . . . . . . . . . . . . . . . .63 properties segments . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .62
R raster symbol sets . . . . . . . . . . . . . . . . . . . . . . . . .21 receiving data . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61 relative line primitive . . . . . . . . . . . . . . . . . . . . . . .22 reserved fields. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . v retired exceptions . . . . . . . . . . . . . . . . . . . . . . . . 197 retired functions . . . . . . . . . . . . . . . . . . . . 195, 197 retired parameters . . . . . . . . . . . . . . . . . . . . . . . 197
S scope of GOCA . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 7 segment chain. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61 segment commands
Begin Segment . . . . . . . . . . . . . . . . . . . . . . . . . .75 segment processing sequence . . . . . . . . . . .61 segment prolog . . . . . . . . . . . . . . . . . . . . . . . . . . . .62 segment types. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61 segments . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 17, 61 appended . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61 chained . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .61 current attributes . . . . . . . . . . . . . . . . . . . . . . . .70 properties . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .62 segment prolog . . . . . . . . . . . . . . . . . . . . . . . . . .62 sequence of segment processing . . . . . . . .61
Set Current Defaults instruction . . . . . . . . . .66 standard actions . . . . . . . . . . . . . . . . . . . . . . . . . 171 standard defaults . . . . . . . . . . . . . . . . . . . . . 70, 79 start and sweep angles . . . . . . . . . . . . . . . . . . . .25 straight lines . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .22
Line primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . .22
Relative Line primitive . . . . . . . . . . . . . . . . . .22 string precision character strings . . . . . . . . . . . . . . . . . . 56, 138 stroke precision character strings . . . . . . . . . . . . . . . . . . . . . . 138 subset levels
Base (mandatory) V ersion 0 . . . . . . . . . 175 drawing order levels, V ersion 0 . . . . . 176, 178
Level 2 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 176
Level 3 (GRS3) . . . . . . . . . . . . . . . . . . . . . 178 within IPDS . . . . . . . . . . . . . . . . . . . . . . . . . . . . 189 within the MO:DCA environment . . . . 188 subsetting . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .17 summary of exception conditions . . . . . . 167 those with standard actions . . . . . . . . . . 171 those without standard actions . . . . . . 169 symbol primitive . . . . . . . . . . . . . . . . . . . . . . . . . . . .20 character strings . . . . . . . . . . . . . . . . . . . . . . . .51 device-specific (character) precision . . . . . . . . . . . . . . . . . . . . . . . . . . . . .53 device-specific (string) precision . . . .56 markers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .57 patterns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .39 symbol sets fonts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .51 raster . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .21 syntax diagrams how to read . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . v
T trademarks . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 206
U
Usable Area (UA) . . . . . . . . . . . . . . . . . . . . . . . . . .14
V valid definitions of area primitive . . . . . . . . .38
V ersion 0 subset levels
Base (mandatory) . . . . . . . . . . . . . . . . . . . . . 175 drawing order levels . . . . . . . . . . . . 176, 178
Level 2 . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 176
Level 3 (GRS3) . . . . . . . . . . . . . . . . . . . . . 178
W
Write Graphics Control . . . . . . . . . . . . . . . . . . 189 [GOCA-D-067]

---

Advanced Function Presentation Consortium
Graphics Object Content Architecture for Advanced Function Presentation
ReferenceAFPC-0008-03 [GOCA-D-068]
