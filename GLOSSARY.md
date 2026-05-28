# Glossary

This glossary contains abbreviations and terms used throughout the Advanced Function Presentation (AFP) architecture and related documentation.

| Term | Full Name | Description |
| :--- | :--- | :--- |
| AEG | Active Environment Group | A group of structured fields that defines the environment for a page, overlay, or page segment. |
| AFP | Advanced Function Presentation | An open architecture for the management of presentable information that is developed by the AFP Consortium (AFPC). AFP comprises a number of data stream and data object architectures including MO:DCA, IPDS, BCOCA, CMOCA, FOCA, GOCA, IOCA, MOCA, and PTOCA. |
| AFPC | AFP Consortium | A formal open standards body that develops and maintains AFP architecture. |
| AFP GOCA | Graphics Object Content Architecture for Advanced Function Presentation | A version of GOCA that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition. |
| ASCII | American Standard Code for Information Interchange | A standard code used for information exchange among data processing systems, data communication systems, and associated equipment. ASCII uses a coded character set consisting of 7-bit coded characters. |
| BCOCA | Bar Code Object Content Architecture | A data architecture for describing bar code objects, using a number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition. |
| CCSID | Coded Character Set Identifier | A 16-bit number identifying a specific set consisting of an encoding scheme identifier, character set identifiers, code page identifiers, and other relevant information that uniquely identifies the coded graphic character representation used. |
| CMOCA | Color Management Object Content Architecture | A resource architecture used to carry the color management information required to render presentation data. |
| CMR | Color Management Resource | An architected object that provides color management in presentation environments. |
| CPGID | Code Page Global Identifier | A unique code page identifier that can be expressed as either a two-byte binary or a five-digit decimal value. |
| DCA | Document Content Architecture | A family of architectures that define the syntax and semantics of the document component. |
| EBCDIC | Extended Binary-Coded Decimal Interchange Code | A coded character set that consists of eight-bit coded characters. |
| FGID | Font Typeface Global Identifier | A unique font identifier that can be expressed as either a two-byte binary or a five-digit decimal value. The FGID is used to identify a type style and characteristics such as posture, weight class, and width class. |
| FOCA | Font Object Content Architecture | A resource architecture for describing the structure and content of fonts referenced by presentation data objects in the document. |
| FQN | Fully Qualified Name | A triplet used to specify a name or identifier for a resource or other document component. |
| GCSGID | Graphic Character Set Global Identifier | A unique graphic character set identifier that can be expressed as either a two-byte binary or a five-digit decimal value. |
| GOCA | Graphics Object Content Architecture | An architected collection of constructs used to interchange and present graphics data. See also AFP GOCA. |
| ICC | International Color Consortium | A group of companies chartered to develop, use, and promote cross-platform standards so that applications and devices can exchange color data without ambiguity. |
| IDE | Image Data Element | A basic unit of image information. An image data element expresses the intensity of a signal at a corresponding image point. |
| IOCA | Image Object Content Architecture | A data architecture for describing resolution-independent image objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition. |
| IPDS | Intelligent Printer Data Stream | An architected host-to-printer data stream that contains both data and controls defining how the data is to be presented. |
| ISO | International Organization for Standardization | An organization of national standards bodies from various countries established to promote development of standards to facilitate international exchange of goods and services. |
| MO:DCA | Mixed Object Document Content Architecture | An architected, presentation-system-independent data stream for interchanging documents. |
| MOCA | Metadata Object Content Architecture | A resource architecture used to carry metadata in an AFP environment. |
| OCA | Object Content Architecture | A family of architectures that define the structure and content of objects that can be embedded in a document or a printer data stream. |
| OEG | Object Environment Group | A group of structured fields that defines the environment for a data object. |
| OID | Object Identifier | A unique identifier for an object, based on a fixed sequence of nodes in the ISO OID naming tree. |
| PTOCA | Presentation Text Object Content Architecture | A data architecture for describing text objects that have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition. |
| SBIN | Signed Binary | A data type for architecture syntax that indicates that one or more bytes be interpreted as a signed binary number. |
| UBIN | Unsigned Binary | A data type for architecture syntax, indicating one or more bytes to be interpreted as an unsigned binary number. |
| UTF | Unicode Transformation Format | A byte-oriented form for encoding Unicode code points, such as UTF-8, UTF-16, or UTF-32. |
| XML | Extensible Markup Language | A set of rules for encoding documents in a format that is both human-readable and machine-readable. |

## Mnemonics
This section contains mnemonics for structured fields, control sequences, and drawing orders used in AFP.

| Mnemonic | Full Name | Description |
| :--- | :--- | :--- |
| AMB | Absolute Move Baseline | A PTOCA control sequence that establishes the baseline at a new B-axis coordinate. |
| AMI | Absolute Move Inline | A PTOCA control sequence that establishes the presentation position at a new I-axis coordinate. |
| BAG | Begin Active Environment Group | A MO:DCA structured field that begins an Active Environment Group. |
| BBC | Begin Bar Code Object | A MO:DCA structured field that begins a bar code data object. |
| BDA | Bar Code Data | A MO:DCA structured field that contains the data for a bar code object. |
| BDD | Bar Code Data Descriptor | A MO:DCA structured field that contains the descriptor data for a bar code data object. |
| BDG | Begin Document Environment Group | A MO:DCA structured field that begins a document environment group. |
| BDI | Begin Document Index | A MO:DCA structured field that begins the document index. |
| BDT | Begin Document | A MO:DCA structured field that names and begins the document. |
| BFM | Begin Form Map | A MO:DCA structured field that begins a form map. |
| BGR | Begin Graphics Object | A MO:DCA structured field that begins a graphics data object. |
| BIM | Begin Image Object | A MO:DCA structured field that begins an image data object. |
| BLN | Begin Line | A PTOCA control sequence that moves the presentation position to the beginning of the next line. |
| BMM | Begin Medium Map | A MO:DCA structured field that begins a medium map. |
| BMO | Begin Overlay | A MO:DCA structured field that begins an overlay. |
| BNG | Begin Named Page Group | A MO:DCA structured field that begins a named page group. |
| BOC | Begin Object Container | A MO:DCA structured field that begins an object container. |
| BOG | Begin Object Environment Group | A MO:DCA structured field that begins an object environment group. |
| BPF | Begin Print File | A MO:DCA structured field that begins a print file. |
| BPG | Begin Page | A MO:DCA structured field that begins a page. |
| BPS | Begin Page Segment | A MO:DCA structured field that begins a page segment. |
| BPT | Begin Presentation Text Object | A MO:DCA structured field that begins a presentation text object. |
| BRG | Begin Resource Group | A MO:DCA structured field that begins a resource group. |
| BRS | Begin Resource | A MO:DCA structured field that begins a resource. |
| BSG | Begin Resource Environment Group | A MO:DCA structured field that begins a resource environment group. |
| BSU | Begin Suppression | A PTOCA control sequence that marks the beginning of a field of suppressed text. |
| CDD | Container Data Descriptor | A MO:DCA structured field that contains descriptor data for an object container. |
| COORD | Coordinate type | A GOCA mnemonic for the coordinate type parameter in environment controls. |
| DBR | Draw B-axis Rule | A PTOCA control sequence that draws a line in the B-direction. |
| DIR | Draw I-axis Rule | A PTOCA control sequence that draws a line in the I-direction. |
| EAG | End Active Environment Group | A MO:DCA structured field that terminates an Active Environment Group. |
| EBC | End Bar Code Object | A MO:DCA structured field that terminates a bar code data object. |
| EDG | End Document Environment Group | A MO:DCA structured field that terminates a document environment group. |
| EDI | End Document Index | A MO:DCA structured field that terminates a document index. |
| EDT | End Document | A MO:DCA structured field that terminates a document. |
| EFM | End Form Map | A MO:DCA structured field that terminates a form map. |
| EGR | End Graphics Object | A MO:DCA structured field that terminates a graphics data object. |
| EIM | End Image Object | A MO:DCA structured field that terminates an image data object. |
| EMM | End Medium Map | A MO:DCA structured field that terminates a medium map. |
| EMO | End Overlay | A MO:DCA structured field that terminates an overlay. |
| ENC | Encrypted Data | A PTOCA control sequence that specifies a sequence of encrypted bytes. |
| ENG | End Named Page Group | A MO:DCA structured field that terminates a named page group. |
| EOC | End Object Container | A MO:DCA structured field that terminates an object container. |
| EOG | End Object Environment Group | A MO:DCA structured field that terminates an object environment group. |
| EPF | End Print File | A MO:DCA structured field that terminates a print file. |
| EPG | End Page | A MO:DCA structured field that terminates a page. |
| EPS | End Page Segment | A MO:DCA structured field that terminates a page segment. |
| EPT | End Presentation Text Object | A MO:DCA structured field that terminates a presentation text object. |
| ERG | End Resource Group | A MO:DCA structured field that terminates a resource group. |
| ERS | End Resource | A MO:DCA structured field that terminates a resource. |
| ESG | End Resource Environment Group | A MO:DCA structured field that terminates a resource environment group. |
| ESU | End Suppression | A PTOCA control sequence that marks the end of a field of suppressed text. |
| GAD | Graphics Data | A MO:DCA structured field that contains the data for a graphics object. |
| GAR | Glyph Advance Run | A PTOCA control sequence that specifies the displacement of glyphs along the baseline. |
| GBAR | Begin Area | A GOCA order that begins an area. |
| GBCP | Begin Custom Pattern | A GOCA order that begins a custom pattern definition. |
| GBIMG | Begin Image at Given Position | A GOCA order that begins an image at a specified position. |
| GBOX | Box at Given Position | A GOCA order that draws a box at a specified position. |
| GCBEZ | Cubic Bezier Curve at Given Position | A GOCA order that draws a cubic Bezier curve at a specified position. |
| GCBIMG | Begin Image at Current Position | A GOCA order that begins an image at the current position. |
| GCBOX | Box at Current Position | A GOCA order that draws a box at the current position. |
| GCCBEZ | Cubic Bezier Curve at Current Position | A GOCA order that draws a cubic Bezier curve at the current position. |
| GCCHST | Character String at Current Position | A GOCA order that draws a character string at the current position. |
| GCFARC | Full Arc at Current Position | A GOCA order that draws a full arc at the current position. |
| GCFLT | Fillet at Current Position | A GOCA order that draws a fillet at the current position. |
| GCLINE | Line at Current Position | A GOCA order that draws a line at the current position. |
| GCMRK | Marker at Current Position | A GOCA order that draws a marker at the current position. |
| GCOMT | Comment | A GOCA order used to carry unarchitected data. |
| GCRLINE | Relative Line at Current Position | A GOCA order that draws a relative line at the current position. |
| GDD | Graphics Data Descriptor | A MO:DCA structured field that contains descriptor data for a graphics object. |
| GDPT | Delete Pattern | A GOCA order that deletes a custom pattern definition. |
| GEAR | End Area | A GOCA order that ends an area. |
| GEIMG | End Image | A GOCA order that ends an image. |
| GEOM | Geometric parameter format | A GOCA mnemonic for the geometric parameter format in environment controls. |
| GEPROL | End Prolog | A GOCA order that marks the end of a segment prolog. |
| GFARC | Full Arc at Given Position | A GOCA order that draws a full arc at a specified position. |
| GFLT | Fillet at Given Position | A GOCA order that draws a fillet at a specified position. |
| GIMD | Image Data | A GOCA order that contains image data. |
| GIR | Glyph ID Run | A PTOCA control sequence that specifies the IDs of glyphs to be placed along the baseline. |
| GLC | Glyph Layout Control | A PTOCA control sequence that specifies control information for glyph runs. |
| GLGD | Linear Gradient | A GOCA order that draws a linear gradient. |
| GLINE | Line at Given Position | A GOCA order that draws a line at a specified position. |
| GMRK | Marker at Given Position | A GOCA order that draws a marker at a specified position. |
| GNOP1 | No-Operation | A 1-byte GOCA order that performs no action. |
| GOR | Glyph Offset Run | A PTOCA control sequence that specifies offsets of glyphs from the baseline. |
| GPARC | Partial Arc at Given Position | A GOCA order that draws a partial arc at a specified position. |
| GRGD | Radial Gradient | A GOCA order that draws a radial gradient. |
| GRLINE | Relative Line at Given Position | A GOCA order that draws a relative line at a specified position. |
| GSAP | Set Arc Parameters | A GOCA order that sets the arc parameters. |
| GSBMX | Set Background Mix | A GOCA order that sets the background mix. |
| GSCA | Set Character Angle | A GOCA order that sets the character angle. |
| GSCC | Set Character Cell | A GOCA order that sets the character cell size. |
| GSCD | Set Character Direction | A GOCA order that sets the character direction. |
| GSCLT | Set Custom Line Type | A GOCA order that sets a custom line type. |
| GSCOL | Set Color | A GOCA order that sets the current color. |
| GSCP | Set Current Position | A GOCA order that sets the current position. |
| GSCR | Set Character Precision | A GOCA order that sets the character precision. |
| GSCS | Set Character Set | A GOCA order that sets the character set. |
| GSECOL | Set Extended Color | A GOCA order that sets the extended color. |
| GSFLW | Set Fractional Line Width | A GOCA order that sets the fractional line width. |
| GSGCH | Segment Characteristics | A GOCA order that sets segment characteristics. |
| GSLE | Set Line End | A GOCA order that sets the line end style. |
| GSLJ | Set Line Join | A GOCA order that sets the line join style. |
| GSLT | Set Line Type | A GOCA order that sets the line type. |
| GSLW | Set Line Width | A GOCA order that sets the line width. |
| GSMC | Set Marker Cell | A GOCA order that sets the marker cell size. |
| GSMP | Set Marker Precision | A GOCA order that sets the marker precision. |
| GSMS | Set Marker Set | A GOCA order that sets the marker set. |
| GSMT | Set Marker Symbol | A GOCA order that sets the marker symbol. |
| GSMX | Set Mix | A GOCA order that sets the current mix. |
| GSPS | Set Pattern Set | A GOCA order that sets the pattern set. |
| GSPT | Set Pattern Symbol | A GOCA order that sets the pattern symbol. |
| GSPCOL | Set Process Color | A GOCA order that sets the process color. |
| GSPRP | Set Pattern Reference Point | A GOCA order that sets the pattern reference point. |
| IDD | Image Data Descriptor | A MO:DCA structured field that contains descriptor data for an image data object. |
| IEL | Index Element | A MO:DCA structured field that identifies begin structured fields for use within an index. |
| IMM | Invoke Medium Map | A MO:DCA structured field that invokes a medium map. |
| IOB | Include Object | A MO:DCA structured field that includes a data object in a page or overlay. |
| IPD | Image Picture Data | A MO:DCA structured field that contains the data for an image data object. |
| IPG | Include Page | A MO:DCA structured field that includes a page from another document. |
| IPO | Include Page Overlay | A MO:DCA structured field that includes a page overlay. |
| IPS | Include Page Segment | A MO:DCA structured field that includes a page segment. |
| LLE | Link Logical Element | A MO:DCA structured field that links a logical element to a document component. |
| MBC | Map Bar Code Object | A MO:DCA structured field that maps a bar code object to its object area. |
| MCC | Medium Copy Count | A MO:DCA structured field that specifies the number of copies. |
| MCD | Map Container Data | A MO:DCA structured field that maps object container data into its object area. |
| MCF | Map Coded Font | A MO:DCA structured field that maps local identifiers to font resources. |
| MDD | Medium Descriptor | A MO:DCA structured field that specifies the size and attributes of a medium. |
| MDR | Map Data Resource | A MO:DCA structured field used to map a data resource to a local identifier. |
| MFC | Medium Finishing Control | A MO:DCA structured field that specifies finishing operations for the medium. |
| MGO | Map Graphics Object | A MO:DCA structured field that maps a graphics object to its object area. |
| MIO | Map Image Object | A MO:DCA structured field that maps an image object to its object area. |
| MMC | Medium Modification Control | A MO:DCA structured field that specifies modifications to the medium. |
| MMD | Map Media Destination | A MO:DCA structured field that maps a medium to a destination. |
| MMO | Map Medium Overlay | A MO:DCA structured field that maps a local identifier to a medium overlay. |
| MMT | Map Media Type | A MO:DCA structured field that maps a medium to a media type. |
| MPG | Map Page | A MO:DCA structured field that maps a page into its object area. |
| MPO | Map Page Overlay | A MO:DCA structured field that maps local identifiers to page overlay names. |
| MPS | Map Page Segment | A MO:DCA structured field that maps a page segment into its object area. |
| MPT | Map Presentation Text | A MO:DCA structured field that maps presentation text into its object area. |
| MSU | Map Suppression | A MO:DCA structured field that maps local identifiers to suppression names. |
| NOP | No Operation | A mnemonic used for a no-operation structured field (MO:DCA) or control sequence (PTOCA). |
| OBD | Object Area Descriptor | A MO:DCA structured field that specifies the size and attributes of an object area. |
| OBP | Object Area Position | A MO:DCA structured field that specifies the origin and orientation of an object area. |
| OCD | Object Container Data | A MO:DCA structured field that contains the data for an object container. |
| OVS | Overstrike | A PTOCA control sequence that specifies a text field to be overstruck. |
| PEC | Presentation Environment Control | A MO:DCA structured field that specifies the presentation environment. |
| PFC | Presentation Fidelity Control | A MO:DCA structured field that specifies presentation fidelity requirements. |
| PGD | Page Descriptor | A MO:DCA structured field that specifies the size and attributes of a page or overlay. |
| PGP | Page Position | A MO:DCA structured field that specifies the position of pages on the medium. |
| PMC | Page Modification Control | A MO:DCA structured field that specifies modifications to the page. |
| PPO | Preprocess Presentation Object | A MO:DCA structured field that specifies preprocessing for objects. |
| PTD | Presentation Text Data Descriptor | A MO:DCA structured field that contains descriptor data for text. |
| PTX | Presentation Text Data | A MO:DCA structured field that contains the data for a presentation text object. |
| RMB | Relative Move Baseline | A PTOCA control sequence that moves the baseline relative to the current position. |
| RMI | Relative Move Inline | A PTOCA control sequence that moves the presentation position relative to current. |
| RPS | Repeat String | A PTOCA control sequence that specifies a string of characters to be repeated. |
| SBI | Set Baseline Increment | A PTOCA control sequence that specifies the value of the baseline increment. |
| SCD | Set Current Defaults | A GOCA mnemonic for the Set Current Defaults control instruction. |
| SCFL | Set Coded Font Local | A PTOCA control sequence that specifies a local identifier for the active font. |
| SEA | Set Encrypted Alternate | A PTOCA control sequence that specifies alternate text if decryption fails. |
| SEC | Set Extended Text Color | A PTOCA control sequence that specifies a color value and color space. |
| SIA | Set Intercharacter Adjustment | A PTOCA control sequence that specifies intercharacter adjustment. |
| SIM | Set Inline Margin | A PTOCA control sequence that specifies the value for the inline margin. |
| SKI | Set Key Information | A PTOCA control sequence that specifies encryption key information. |
| STC | Set Text Color | A PTOCA control sequence that specifies a named color value for text. |
| STO | Set Text Orientation | A PTOCA control sequence that establishes text orientation. |
| SVI | Set Variable Space Character Increment | A PTOCA control sequence that sets the space character increment. |
| TBM | Temporary Baseline Move | A PTOCA control sequence that specifies a temporary baseline movement. |
| TLE | Tag Logical Element | A MO:DCA structured field used to assign a name and value to a component. |
| TRN | Transparent Data | A PTOCA control sequence that specifies a string of characters presented as-is. |
| UCT | Unicode Complex Text | A PTOCA control sequence that identifies a sequence of Unicode complex text. |
| USC | Underscore | A PTOCA control sequence that specifies a text field to be underscored. |
