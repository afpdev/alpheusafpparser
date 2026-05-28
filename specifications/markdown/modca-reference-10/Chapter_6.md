Chapter 6. MO:DCA Triplets
This chapter:
• Describes the format, syntax, and semantics for each MO:DCA triplet [MODCA-6-001]
• Describes the purpose of each MO:DCA triplet parameter [MODCA-6-002]
• Identifies values that can be given to triplet parameters [MODCA-6-003]
General Information
Triplets appear after all fixed parameters in a structured field. Some structured fields may contain repeating
groups of triplets. Each repeating group contains a length parameter followed by one or more triplets. An
optional triplet may not appear at all, in which case a default value is used when a value is needed.
In general, when a triplet description refers to the structured field in which it appears, it refers to it as the
structured field. When the description refers to a structured field other than the one in which it appears, it refers
to that structured field by its proper name, such as Begin Document structured field.
Triplet Format
A triplet is a self-identifying parameter that contains three components: the length of the triplet, an ID
identifying the triplet, and the associated parameters. The general format for the triplet data structure is shown
below.


Off-
set
Type Name Range Meaning M/O Exc
0 UBIN Tlength 3–254 Length of the triplet, including the length of [MODCA-6-004]
Tlength
M X'06'
1 CODE Tid X'01'–X'02', X'04', [MODCA-6-005]
X'10', X'18', X'1F',
X'20'–X'22', X'24'–
X'26', X'2D', X'36',
X'43', X'45', X'4B'–
X'4E', X'50', X'56'–
X'5A', X'5D'–X'5E',
X'62', X'65', X'68',
X'6C', X'70'–X'72',
X'74'–X'75', X'78',
X'80'–X'88', X'8B',
X'8C', X'8E', X'8F',
X'91', X'95'–X'97',
X'9A', X'9C', X'9D',
X'9E', X'FF'
Identifies the triplet:
X'01' Coded Graphic Character Set
Global Identifier
X'02' Fully Qualified Name
X'04' Mapping Option
X'10' Object Classification
X'18' MO:DCA Interchange Set
X'1F' Font Descriptor Specification
X'20' Font Coded Graphic Character Set
Global Identifier
X'21' Resource Object Type
X'22' Extended Resource Local Identifier
X'24' Resource Local Identifier
X'25' Resource Section Number
X'26' Character Rotation
X'2D' Object Byte Offset
X'36' Attribute Value
X'43' Descriptor Position
X'45' Media Eject Control
X'4B' Measurement Units
X'4C' Object Area Size
X'4D' Area Definition
X'4E' Color Specification
X'50' Encoding Scheme ID
X'56' Medium Map Page Number
X'57' Object Byte Extent
X'58' Object Structured Field Offset
X'59' Object Structured Field Extent
X'5A' Object Offset
X'5D' Font Horizontal Scale Factor
X'5E' Object Count
X'62' Local Date and Time Stamp
X'65' Comment
X'68' Medium Orientation
X'6C' Resource Object Include
X'70' Presentation Space Reset Mixing
X'71' Presentation Space Mixing Rules
X'72' Universal Date and Time Stamp
X'74' T oner Saver
X'75' Color Fidelity
X'78' Font Fidelity
X'80' Attribute Qualifier
X'81' Page Position Information
X'82' Parameter Value
X'83' Presentation Control
X'84' Font Resolution and Metric
T echnology
X'85' Finishing Operation
X'86' T ext Fidelity
X'87' Media Fidelity
X'88' Finishing Fidelity
X'8B' Data-Object Font Descriptor
X'8C' Locale Selector
M X'10'
Triplet Format


Off-
set
Type Name Range Meaning M/O Exc
X'8E' UP3i Finishing Operation
X'8F' MO:DCA Function Set
X'91' Color Management Resource
Descriptor
X'95' Rendering Intent
X'96' CMR T ag Fidelity
X'97' Device Appearance
X'9A' Image Resolution
X'9C' Object Container Presentation
Space Size
X'9D' Keep Group T ogether
X'9E' Setup Name
X'FF' Triplet Extender
2–n Con-
tents
Contents of the triplet as identified by the
MO:DCA architecture
M X'06'
Triplet Syntax
The syntax for triplet data is the same as for structured field data. Refer to “How to Read the Syntax Diagrams”
for a description of this syntax.
Triplet Semantics
Tlength Specifies the total length of the triplet, including the one-byte Tlength field. It contains a
numeric value of UBIN type that ranges from 3 to 254, expressed in bytes.
Tid Identifies the triplet identifier. Permitted values are listed in the syntax table. If the value of Tid
is not one of those listed in the Range column, a X'10' exception condition exists.
Contents Contains the triplet data elements. The number of data elements and the length of each is
dependent on the triplet identifier.
Architected defaults are identified in the semantic description of the individual parameters. When an
architected default exists for an entire triplet, the default is documented at the end of the semantic description
for that triplet.
Triplet Format


### Coded Graphic Character Set Global Identifier Triplet X'01'
Certain structured fields within the data stream carry parameters that consist of a character string, such as a
name. These parameters are defined to have a CHAR data type. For example the name parameter on the
Include Page Overlay structured field can be used as an identifier for a component, and as a viewable identifier
to be recorded whenever the processor of the data stream associates an exception condition with the
component.
The Coded Graphic Character Set Global Identifier (CGCSGID) triplet is used to establish the values of the
code page and character set for interpretation of all structured field parameters having a CHAR data type, such
as name parameters, except where such parameters define a fixed encoding. An example of a parameter that
defines its own encoding is the character string specified with a Fully Qualified Name (X'02') triplet using
FQNFmt = X'20' - URL, which is encoded using the US-ASCII coded character set.
The character set is specified with a Graphic Character Set Global ID (GCSGID), and the code page is
specified with a Code Page Global ID (CPGID). Alternatively, the Coded Graphic Character Set Global
Identifier triplet may be used to identify a Coded Character Set Identifier (CCSID) as defined and registered by
the Character Data Representation Architecture (CDRA). The CCSID can be resolved to identify the value of
the code page and character set for interpretation of parameters with a CHAR data type. See the Character
Data Representation Architecture Reference and Registry , SC09-2190, for detailed information.
The scope of the Coded Graphic Character Set Global Identifier triplet is defined as follows:
• The most recent occurrence of a X'01' triplet on a structured field establishes the code page and character [MODCA-6-006]
set used to interpret all subsequent parameters within that structured field with a CHAR data type.
• If the structured field syntax allows parameters with a CHAR data type to be positioned before the allowed [MODCA-6-007]
triplets, then the first occurrence of a X'01' triplet on that structured field establishes the code page and
character set to be used to interpret such parameters.
• If X'01' triplets appear on a Begin structured field, the last X'01' triplet specified establishes the code page [MODCA-6-008]
and character set used to interpret all parameters with CHAR data type on all structured fields that lie
between the Begin structured field and its corresponding End structured field, unless specifically overridden
by a X'01' triplet on an enveloped structured field. Object names on an End structured field are always
interpreted with the same code page and character set used for the object name on the corresponding Begin
structured field.
#### Triplet X'01' Syntax: GCSGID/CPGID Form
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-009]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-010]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-011]|
| 1 CODE Tid X'01' Identifies the Coded Graphic | | | | | | Character Set Global Identifier triplet M X'00' [MODCA-6-012]|
| 2–3 CODE GCSGID X'0001'–X'FFFE' Specifies the Graphic Character | | | | | | Set Global Identifier M X'06' X'FFFF' Specifies the character set consisting of all characters in the code page [MODCA-6-013]|
| 4–5 CODE CPGID X'0001'–X'FFFE' Specifies the Code Page Global | | | | | | Identifier M X'06' Triplet X'01' [MODCA-6-014]|


#### Triplet X'01' Syntax: CCSID Form
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-015]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-016]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-017]|
| 1 CODE Tid X'01' Identifies the Coded Graphic | | | | | | Character Set Global Identifier triplet M X'00' [MODCA-6-018]|
| 2–3 CODE X'0000' Must be set to X'0000' to identify | | | | | | the CCSID form of the triplet M X'06' [MODCA-6-019]|
| 4–5 CODE CCSID X'0000'–X'FFFF' Coded Character Set Identifier | | | | | | defined by CDRA M X'06' [MODCA-6-020]|
#### Triplet X'01' Semantics
GCSGID/CPGID Form
Tlength Contains the length of the triplet.
Tid Identifies the Coded Graphic Character Set Global Identifier triplet.
GCSGID Specifies the Graphic Character Set Global Identifier of the character set to be used in
conjunction with the Code Page Global Identifier to identify the graphic characters that are
represented by code points in any parameter with a data type of CHAR. The GCSGID may
identify a subset or the maximal set of all of the graphic characters supported for the
associated code page. Valid values for Graphic Character Set Global Identifiers are 1 through
65,534. A value of 65,535 (X'FFFF') indicates that a character set consisting of all characters
that have assigned code points in the associated code page is to be used.
CPGID Specifies the Code Page Global Identifier of the code page to be used in conjunction with the
character set to identify the graphic characters that are represented by code points in any
parameter with a data type of CHAR. Valid values for Code Page Global Identifiers are 1
through 65,534.
Note: The concatenation of the GCSGID and CPGID is currently referred to as the Coded Graphic Character
Set Global Identifier (CGCSGID). In the past, it was also known as the Global Character Set Identifier
(GCID).
CCSID Form
Bytes 2–3 Must be X'0000'. Identifies the CCSID form of the triplet.
CCSID Coded Character Set Identifier. Defined by the Character Data Representation Architecture.
Can be resolved to specify the code page and character set for interpretation of parameters
with CHAR data type. See the Character Data Representation Architecture Reference and
Registry, SC09-2190, for detailed information.
Application Notes:
1. Most MO:DCA character strings are carried in Fully Qualified Name (FQN) triplets. This triplet limits the [MODCA-6-021]
length of the data to 250 bytes. When such a character string is converted from one character encoding
(such as single-byte EBCDIC) to another character encoding (such as double-byte UTF-16) the string may
increase in length. When the new length exceeds the 250 byte triplet limit, AFP servers generate an
exception. Such encoding conversions are commonly used to compare object names that are specified in
Triplet X'01'


different encodings, therefore it is strongly recommended that object names that are specified using a
single-byte encoding are limited to 125 characters or fewer.
2. There is better system support for encoding conversions using a CCSID instead of a CPGID + GCSGID [MODCA-6-022]
combination to define the encoding of a character string, therefore it is recommended that the CCSID form
of this triplet is used whenever possible.
3. It is strongly recommended that this triplet is properly specified even if the parameter on a structured field [MODCA-6-023]
defines a fixed encoding. For example, if the parameter defines a fixed UTF-16BE encoding, the triplet can
be specified using the CCSID form with CCSID=1200 (X'04B0'). [MODCA-6-024]
### Structured Fields Using Triplet X'01'
• “Begin Active Environment Group (BAG)” [MODCA-6-025]
• “Begin Bar Code Object (BBC)” [MODCA-6-026]
• “Begin Document (BDT)” [MODCA-6-027]
• “Begin Document Environment Group (BDG)” [MODCA-6-028]
• “Begin Document Index (BDI)” [MODCA-6-029]
• “Begin Form Map (BFM)” [MODCA-6-030]
• “Begin Graphics Object (BGR)” [MODCA-6-031]
• “Begin Image Object (BIM)” [MODCA-6-032]
• “Begin Medium Map (BMM)” [MODCA-6-033]
• “Begin Object Container (BOC)” [MODCA-6-034]
• “Begin Object Environment Group (BOG)” [MODCA-6-035]
• “Begin Overlay (BMO)” [MODCA-6-036]
• “Begin Print File (BPF)” [MODCA-6-037]
• “Begin Page (BPG)” [MODCA-6-038]
• “Begin Named Page Group (BNG)” [MODCA-6-039]
• “Begin Page Segment (BPS)” [MODCA-6-040]
• “Begin Presentation T ext Object (BPT)” [MODCA-6-041]
• “Begin Resource Group (BRG)” [MODCA-6-042]
• “Begin Resource (BRS)” [MODCA-6-043]
• “Begin Resource Environment Group (BSG)” [MODCA-6-044]
• “Include Object (IOB)” [MODCA-6-045]
• “Include Page (IPG)” [MODCA-6-046]
• “Include Page Overlay (IPO)” [MODCA-6-047]
• “Include Page Segment (IPS)” [MODCA-6-048]
• “Index Element (IEL)” [MODCA-6-049]
• “Invoke Medium Map (IMM)” [MODCA-6-050]
• “Link Logical Element (LLE)” [MODCA-6-051]
• “Map Coded Font (MCF) Format 2” [MODCA-6-052]
• “Map Data Resource (MDR)” [MODCA-6-053]
• “Map Media Destination (MMD)” [MODCA-6-054]
• “Map Media Type (MMT)” [MODCA-6-055]
• “Map Page (MPG)” [MODCA-6-056]
• “Map Page Overlay (MPO)” [MODCA-6-057]
• “Page Modification Control (PMC)” [MODCA-6-058]
• “Preprocess Presentation Object (PPO)” [MODCA-6-059]
• “T ag Logical Element (TLE)” [MODCA-6-060]
Triplet X'01'


### Fully Qualified Name Triplet X'02'
The Fully Qualified Name triplet enables the identification and referencing of objects using Global Identifiers
(GIDs). A GID can be one of the following:
• A Coded Graphic Character Set Global Identifier (CGCSGID) [MODCA-6-061]
• A Code Page Global ID (CPGID) [MODCA-6-062]
• A Font Typeface Global Identifier (FGID) [MODCA-6-063]
• A Graphic Character Set Global Identifier (GCSGID) [MODCA-6-064]
• A Global Resource Identifier (GRID) [MODCA-6-065]
• An ASN.1 object identifier (OID), as defined in ISO/IEC 8824:1990(E) [MODCA-6-066]
• An encoded graphic character string that, when qualified by the associated CGCSGID, specifies a reference [MODCA-6-067]
name
• An identifier used by a data object to reference a resource [MODCA-6-068]
• A Uniform Resource Locator (URL), as defined in RFC 1738, Internet Engineering T ask Force (IETF), [MODCA-6-069]
December, 1994
Application Note: Most MO:DCA character strings are carried in Fully Qualified Name (FQN) triplets. This
triplet limits the length of the data to 250 bytes. When such a character string is converted from one
character encoding (such as single-byte EBCDIC) to another character encoding (such as double-byte
UTF-16), the string may increase in length. When the new length exceeds the 250 byte triplet limit, AFP
servers generate an exception. Such encoding conversions are commonly used to compare object
names that are specified in different encodings, therefore it is strongly recommended that object names
that are specified using a single-byte encoding are limited to 125 characters or fewer. [MODCA-6-070]
#### Triplet X'02' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-071]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-072]|
| 0 UBIN Tlength 5–254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-073]|
| 1 CODE Tid X'02' Identifies the Fully Qualified | | | | | | Name triplet M X'00' Triplet X'02' [MODCA-6-074]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-075]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-076]|
| 2 CODE FQNType X'01', X'07'– | | | | | | X'0D', X'11', X'12', X'41', X'6E', X'7E', X'83'–X'87', X'8D'–X'8E', X'98', X'B0', X'BE', X'CA', X'CE', X'DE', X'EE' Specifies how the GID will be used: X'01' Replace First GID name X'07' Font Family Name X'08' Font Typeface Name X'09' MO:DCA Resource Hierarchy Reference X'0A' Begin Resource Group Reference X'0B' Attribute GID X'0C' Process Element GID X'0D' Begin Page Group Reference X'11' Media Type Reference X'12' Media Destination Reference X'41' Color Management [MODCA-6-077]|
### Resource (CMR)
Reference
X'6E' Data-object Font Base
Font Identifier
X'7E' Data-object Font Linked
Font Identifier
X'83' Begin Document
Reference
X'84' Resource Object
Reference
X'85' Code Page Name
Reference
X'86' Font Character Set
Name Reference
X'87' Begin Page Reference
X'8D' Begin Medium Map
Reference
X'8E' Coded Font Name
Reference
X'98' Begin Document Index
Reference
X'B0' Begin Overlay
Reference
X'BE' Data Object Internal
Resource Reference
X'CA' Index Element GID
X'CE' Other Object Data
Reference
X'DE' Data Object External
Resource Reference
X'EE'
T ertiary Data Object
External Resource
Reference
M X'06'
3 CODE FQNFmt X'00', X'10', X'20' Specifies the GID format: [MODCA-6-078]
X'00' Character string
X'10' OID
X'20' URL
M X'06'
4–n FQName GID of the MO:DCA construct.
Can be up to 250 bytes in length.
The data type is format-
M X'04'
Triplet X'02'


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-079]|
| --- | --- | --- | --- | --- | --- | --- dependent. See the semantic description of the FQNFmt parameter. [MODCA-6-080]|
#### Triplet X'02' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Fully Qualified Name triplet.
FQNType Specifies how the fully qualified name is to be used.
FQNType Description
X'01' This GID replaces the first parameter in the structured field that contains a
GID name.
Note: Global Identifiers that override eight-byte positional GID names have
the same semantics as the eight-byte name parameter.
X'07' The triplet contains the name of the font family. This identifier corresponds to
the family name of the font design. For example, Times New Roman is the
family name for the Monotype Times New Roman Expanded font design. The
family name is a character string that normally also appears as a substring in
the typeface name as specified in the Fully Qualified Name type X'08'. Font
Typeface Name triplet.
Implementation Note: Font family names are not consistently identified in
the industry, therefore it may be necessary for implementations to
define a synonym table for mapping names. For example, the name
TimesNewRoman may need to be mapped to Times New Roman.
X'08' This triplet contains the name of the font typeface. This identifier corresponds
to the full name of the typeface as specified by the font supplier. This is the
user interface name which, for example, may be used for specification or
selection of the font design. It is possible that it does not correspond exactly
to the font resource name, character content or supported sizes, such as in
the case of ITC Italic Bold Garamond or Monotype Times New Roman
Expanded.
X'09' The triplet specifies a reference to the MO:DCA resource hierarchy. The
normal MO:DCA resource search order should be used for resolving a
resource object reference when this triplet is specified. See “Resource
Groups”.
X'0A' The triplet contains a GID reference to a Begin Resource Group structured
field.
X'0B' The triplet contains the GID of a document attribute.
X'0C' The triplet contains the GID of a process element.
X'0D' The triplet contains a GID reference to a Begin Named Page Group
structured field.
X'11' The triplet contains a GID reference to a media type.
X'12' The triplet contains a GID reference to a media destination.
X'41' The triplet contains a GID reference to a Color Management Resource
(CMR). CMRs specify color management information that is used to render a
document component. The GID is the CMR name that is specified in the CMR
Triplet X'02'


header for the resource. CMRs are defined in the Color Management Object
Content Architecture Reference.
Architecture Note: This triplet is used on the BRS of a CMR container to
• specify a Link LK Color Conversion CMR that is mapped to the CMR [MODCA-6-081]
in the container, or
• specify a device-specific HT or TTC CMR replacement for a generic [MODCA-6-082]
HT or TTC CMR
X'6E' The triplet contains a GID reference to a data-object font file that defines a
base font. In font linking, the base font is the font that is referenced in the data
stream and that is processed first. The GID is a full font name that has been
assigned to the font.
Architecture Note: This triplet is used on a TrueType Collection (TTC)
container in a print file level resource group to specify a base
TrueType/OpenType font (TTF/OTF) that is contained in the collection.
Although the triplet may be specified on both the Begin Resource
(BRS) and the Begin Object Container (BOC) structured fields of the
collection container, AFP servers always search for the triplet on the
BRS.
X'7E' The triplet contains a GID reference to a data-object font file that defines a
linked font. In font linking, a linked font is not referenced in the data stream
and is processed in the order in which it is linked to the base font. The GID is
a full font name that has been assigned to the font.
Architecture Note: This triplet is used on a TrueType/OpenType font (TTF/
OTF) container or a TrueType Collection (TTC) container in a print file
level resource group to specify a linked font that is to be associated
with a base font in the container. Although the triplet may be specified
on both the Begin Resource (BRS) and the Begin Object Container
(BOC) structured fields of the container, AFP servers always use the
triplet on the BRS, as follows:
• If the BRS envelopes a TTF/OTF container, the FQN type X'7E' triplet [MODCA-6-083]
specifies a linked TTF/OTF for the font in the container.
• If the BRS envelopes a TTC container, the FQN type X'7E' triplet [MODCA-6-084]
specifies a linked TTF/OTF for the base font that is defined by the
immediately preceding FQN type X'6E' triplet.
X'83' The triplet contains a GID reference to a Begin Document structured field.
X'84' The triplet contains a GID name reference to a begin structured field or other
identifier associated with a resource; or it contains a GRID (Global Resource
Identifier). For a description of the GRID, see “Global Resource Identifier
(GRID) Definition”.
Architecture Note: This triplet is used in MO:DCA-L data streams on an
MCF-2 structured field to reference a coded font, and on an MDR
structured field to reference an image object. Note that the MO:DCA-L
format has been functionally capped and is no longer defined in the
MO:DCA reference; for a definition of this format, see MO:DCA-L: The
OS/2 PM Metafile (.met) Format.
X'85' The triplet contains a GID name reference to a code page that specifies the
code points and graphic character names for a coded font.
Application Note: In AFP environments, the name consists of 8 characters
and follows the naming conventions for AFP code pages. For a
definition of these naming conventions, see the font publications
Triplet X'02'


referenced in “Related Publications”. An example of a code
page name is T1V10500. The name is encoded in EBCDIC using code
page 500 and a character set that includes the characters allowed for
the name, such as character set 697. The allowed characters are A–Z,
0–9, $, #, @. For more information on the AFP naming conventions,
see “External Resource Naming Conventions”.
X'86' The triplet contains a GID name reference to a font character set that
specifies a set of graphic characters.
Application Note: In AFP environments, the name consists of 8 characters
and follows the naming conventions for AFP font character sets. For a
definition of these naming conventions, see the font publications
referenced in “Related Publications”. An example of a font
character set name is C0H40080. The name is encoded in EBCDIC
using code page 500 and a character set that includes the characters
allowed for the name, such as character set 697. The allowed
characters are A–Z, 0–9, $, #, @. For more information on the AFP
naming conventions, see “External Resource Naming Conventions” on
page 89.
X'87' The triplet contains a GID reference to a Begin Page structured field.
X'8D' The triplet contains a GID reference to a Begin Medium Map structured field.
X'8E' The triplet contains a GID name reference to a coded font, which identifies a
specific code page and a specific font character set.
Application Note: In AFP environments, the name consists of 8 characters
and follows the naming conventions for AFP coded fonts. For a
definition of these naming conventions, see the font publications
referenced in “Related Publications”. An example of a
coded font name is X0H4108C, which identifies a Helvetica Roman
Bold 8 point typeface for the Latin 1 language group. The code page is
T1V10500, and the font character set is C0H40080. The name is
encoded in EBCDIC using code page 500 and a character set that
includes the characters allowed for the name, such as character set
697. The allowed characters are A–Z, 0–9, $, #, @. For more [MODCA-6-085]
information on the AFP naming conventions, see “External Resource
Naming Conventions”.
X'98' The triplet contains a GID reference to a Begin Document Index structured
field.
X'B0' The triplet contains a GID reference to a Begin Overlay structured field.
X'BE' The triplet contains a GID reference to a resource used by a data object. The
GID is the identifier that is used internally by the data object to reference the
resource, therefore it is called an internal resource reference. The data type
of the identifier is defined by the specific data object. Therefore, it is undefined
(UNDF) at the MO:DCA data stream level. The data object that uses this
resource may or may not be defined by an AFP architecture.
Note: If the data object that requires this resource is also processed as a
resource, the term secondary resource is applied to the resource used
by the data object.
Architecture Note: The identifier specified by the FQN type X'BE' triplet is
the identifier used within the data object to reference the resource
object. It is analogous to the local ID that is used, for example, within
PTOCA and GOCA objects to reference a font.
X'CA' This triplet contains the GID of an Index Element structured field.
Triplet X'02'


X'CE' The triplet contains a GID reference to other object data, which may or may
not be defined by an AFP architecture. The GID may be a file name or any
other identifier associated with the object data.
X'DE' The triplet contains a GID reference to a resource used by a data object. The
GID may be a file name or any other identifier associated with the resource
and is used to locate the resource object in the resource hierarchy. The data
object that uses this resource may or may not be defined by an AFP
architecture.
Note: If the data object that requires this resource is also processed as a
resource, the term secondary resource is applied to the resource used
by the data object.
Architecture Note: The GID specified by the FQN type X'DE' triplet is the
identifier used to find the resource object in the presentation system. In
that sense, it is analogous, for example, to the name of a coded font
that is used to find the font in a font library, or the GRID used to find a
resident printer font.
X'EE'
The triplet contains a GID reference to a tertiary resource used by a data
object that is being used as a secondary resource. The GID may be a file
name or any other identifier associated with the resource and is used to
locate the resource object in the resource hierarchy. The data object that uses
this resource may or may not be defined by an AFP architecture.
Architecture Note: The GID specified by the FQN type X'EE' triplet is the
identifier used to find the resource object in the presentation system. In
that sense, it is analogous, for example, to the name of a coded font
that is used to find the font in a font library, or the GRID used to find a
resident printer font.
All others Reserved
FQNFmt Specifies the format of the Global Identifier:
FQNFmt Description
X'00' The GID is either a character-encoded name, in which case the data type is
CHAR, or a binary identifier, in which case the data type is CODE. The GID is
a binary identifier when the FQN type X'84' specifies a GRID reference to a
coded font. See “Global Resource Identifier (GRID) Definition”.
In the case of FQN type X'BE'—Other Object Internal Resource Reference,
the data type of the GID reference is undefined (UNDF) at the MO:DCA data
stream level; it is not character (CHAR) data. In that case the data type is
defined internally by the data object that generates the reference.
X'10' The GID is an ASN.1 Object Identifier (OID), defined in ISO/IEC 8824:1990
(E). The data type is CODE. The OID is encoded using the Basic Encoding
Rules for ASN.1 specified in ISO/IEC 8825:1990(E). The encoding is in the
definite short form and has the following syntax:
Byte Description
0 Identifier byte, set to X'06' to indicate an OID encoding. [MODCA-6-086]
1 Length of content bytes that follow. Bit 0 of the length byte must be [MODCA-6-087]
set to zero, which limits the number of content bytes to X'7F' = 127.
2–n Content bytes that encode the OID component identifiers.
See “Constructing Object Identifiers (OIDs)”.
X'20' The GID is a Uniform Resource Locator (URL), defined in RFC 1738, Internet
Engineering T ask Force (IETF), December, 1994. The data type is CHAR.
Triplet X'02'


The URL is encoded using the US-ASCII coded character set, which is
defined in Coded Character Set—7-bit American Standard Code for
Information Interchange, ANSI X3.4 (1986).
Architecture Note: Use of this GID is limited to the LLE structured field. See
“Link Logical Element (LLE)”.
All others Reserved
FQName Contains the Global Identifier (GID) of a MO:DCA construct or the GID reference to a MO:DCA
construct. The format and data type of the identifier is defined by the FQNFmt parameter.
Constructing Object Identifiers (OIDs)
The construction of OIDs is shown in the following examples. Given an OID consisting of a sequence of
component Identifiers, for example the OID {2.100.3} consisting of component identifiers {2, 100, 3}, the
content bytes for the encoding are generated as follows.
• Each component identifier, except for the first two which are treated as a special case, is represented as a [MODCA-6-088]
series of one or more bytes. Bit 0 of each byte is reserved to indicate whether the byte is the last in the
series:
Bit 0 = 1 The byte is not the last byte.
Bit 0 = 0 The byte is the last byte.
Bits 1–7 of each byte in the series are concatenated to carry the encoding of the component identifier as an
unsigned binary number. The component identifier is encoded in the fewest possible bytes, that is, the
leading byte of the encoding cannot have the value X'80'. Encoding starts by placing the least significant bit
of the component identifier into the least significant bit of the encoded bytes.
Example 1:
component identifier = 200
= X'C8'
= B'1100 1000'
Because this number has 8 significant bits, two bytes are needed to encode it:
B'1 000 0001 0 100 1000' = X'8148'.
Example 2:
component identifier = 3
= X'03'
= B'0000 0011'
Because this number has 2 significant bits, only one byte is needed to encode it:
B'0 000 0011' = X'03'.
• The first two component identifiers, represented by x and y in the OID (x.y.z.....), are combined into a single [MODCA-6-089]
number using the equation
(x × 40) + y
The resulting number is then encoded into the first series of content bytes using the previously defined
algorithm. Therefore, the nth component identifier in the OID (n>2) is represented by the (n–1)'th series of
bytes in the content.
Example 3:
OID {2.100.3}
Encoded OID = X'06 03 813403'
Example 4:
OID {1.3.18.0.4.1.1.14}
Encoded OID = X'06 07 2B12000401010E'
Application Note: The purpose of supporting ISO object identifiers in the FQN triplet is to provide a means for
generating MO:DCA object identifiers that are guaranteed to be unique across all environments that
Triplet X'02'


generate these identifiers in accordance with the ISO standard. When OIDs are used in a MO:DCA data
stream to identify and reference objects, the presentation system assumes that the OIDs have been
generated properly and have been uniquely assigned to objects. That is, the MO:DCA presentation
system assumes that:
• If an object is assigned an OID, no other object can be assigned the same OID [MODCA-6-090]
• If the object definition is changed, the object must be assigned a new and different OID [MODCA-6-091]
This allows the presentation system to manage objects by their OIDs in a manner that is independent of
time, location, and platform. Any violation of these rules will result in unpredictable and incorrect
presentation.
Global Resource Identifier (GRID) Definition
The global resource identifier (GRID) is an eight-byte binary identifier used to reference a coded font. It
consists of a concatenation of the following four binary items:
Byte Content
0–1 The two-byte binary Graphic Character Set Global Identifier (GCSGID). The character set
defined by the GCSGID is associated with the coded font and identifies a minimum set of
coded font graphic characters required for presentation. It may be a character set that is
associated with the code page, or with the font character set, or with both. Valid values are 1–
65,534. A value of 65,535 (X'FFFF') indicates that a character set consisting of all characters
that have assigned code points in the associated code page is to be used.
2–3 The two-byte binary Code Page Global Identifier (CPGID) assigned to the code page. Valid
values are 1–65,534.
4–5 The two-byte binary Font Typeface Global ID (FGID) assigned to the font design. Valid values
are 1–65,534.
6–7 A two-byte binary number that represents the font width (specified horizontal font size) in
1440ths of an inch (see the Font Object Content Architecture Reference for a description of
the horizontal font size parameter). Valid values are 1–32,767. A value of 0 indicates that the
font width is not specified. The value X'FFFF' is retired; see “Retired Parameters”.
For a list of GCSGIDs and CPGIDs, see the Character Data Representation Architecture Reference and
Registry. For a list of FGIDs, see the AFPC Font Typeface Registry (FGIDs).
The font width may be used to generate the specified vertical font size, which is used to scale outline
technology fonts to the desired point size, as follows:
• For typographic, proportionally-spaced fonts, the vertical font size is three times the font width. [MODCA-6-092]
• For fixed-pitch, uniform character increment fonts, including Proportional Spacing Machine (PSM) fonts, the [MODCA-6-093]
vertical font size is calculated as follows:
1000 × font width [MODCA-6-094]
vertical font size = -------------------------
space character increment
(in relative units)
If the generated vertical font size conflicts with the nominal vertical font size in the font object, the generated
vertical font size overrides.
Implementation Notes:
1. For IBM Core Interchange Courier fonts, and for IBM Expanded Core fonts with FGID values less than [MODCA-6-095]
750 and with FGID values between 3,840 and 4,095 inclusive (fixed pitch, uniform character increment, [MODCA-6-096]
and PSM fonts), a value of 600 relative units can be used for the space character increment.
Triplet X'02'


2. Code page objects and font character set objects may each be associated with multiple character sets. [MODCA-6-097]
Because the GRID only specifies a single character set, the presentation server that resolves the GRID
reference must understand subset/superset relationships between the character set specified in the
GRID and the character sets associated with the referenced code page and font character set. All
graphic characters in the specified character set must also belong to a character set associated with the
code page and a character set associated with the font character set. T o optimize coded font selection,
generators of the GRID should specify the smallest character set that is a subset of both a character set
associated with the code page and a character set associated with the font character set. [MODCA-6-098]
### Structured Fields Using Triplet X'02'
• “Begin Bar Code Object (BBC)” [MODCA-6-099]
• “Begin Document (BDT)” [MODCA-6-100]
• “Begin Document Index (BDI)” [MODCA-6-101]
• “Begin Graphics Object (BGR)” [MODCA-6-102]
• “Begin Image Object (BIM)” [MODCA-6-103]
• “Begin Overlay (BMO)” [MODCA-6-104]
• “Begin Named Page Group (BNG)” [MODCA-6-105]
• “Begin Print File (BPF)” [MODCA-6-106]
• “Begin Page (BPG)” [MODCA-6-107]
• “Begin Object Container (BOC)” [MODCA-6-108]
• “Begin Presentation T ext Object (BPT)” [MODCA-6-109]
• “Begin Resource Group (BRG)” [MODCA-6-110]
• “Begin Resource (BRS)” [MODCA-6-111]
• “End Bar Code Object (EBC)” [MODCA-6-112]
• “End Document (EDT)” [MODCA-6-113]
• “End Document Index (EDI)” [MODCA-6-114]
• “End Graphics Object (EGR)” [MODCA-6-115]
• “End Image Object (EIM)” [MODCA-6-116]
• “End Overlay (EMO)” [MODCA-6-117]
• “End Object Container (EOC)” [MODCA-6-118]
• “End Page (EPG)” [MODCA-6-119]
• “End Named Page Group (ENG)” [MODCA-6-120]
• “End Print File (EPF)” [MODCA-6-121]
• “End Presentation T ext Object (EPT)” [MODCA-6-122]
• “End Resource Group (ERG)” [MODCA-6-123]
• “Index Element (IEL)” [MODCA-6-124]
• “Include Object (IOB)” [MODCA-6-125]
• “Include Page (IPG)” [MODCA-6-126]
• “Include Page Overlay (IPO)” [MODCA-6-127]
• “Link Logical Element (LLE)” [MODCA-6-128]
• “Map Coded Font (MCF) Format 2” [MODCA-6-129]
• “Map Data Resource (MDR)” [MODCA-6-130]
• “Map Media Destination (MMD)” [MODCA-6-131]
• “Map Media Type (MMT)” [MODCA-6-132]
• “Map Page (MPG)” [MODCA-6-133]
• “Map Page Overlay (MPO)” [MODCA-6-134]
• “Preprocess Presentation Object (PPO)” [MODCA-6-135]
• “T ag Logical Element (TLE)” [MODCA-6-136]
Triplet X'02'


### Mapping Option Triplet X'04'
The Mapping Option is used to specify the mapping of a data object presentation space to an object area. [MODCA-6-137]
#### Triplet X'04' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-138]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-139]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-140]|
| 1 CODE Tid X'04' Identifies the Mapping Option | | | | | | triplet M X'00' [MODCA-6-141]|
| 2 CODE MapValue X'00', X'10', X'20', | | | | | | X'30', X'41', X'42', X'50', X'60', X'70' Data object mapping option: X'00' Position X'10' Position and trim X'20' Scale to fit X'30' Center and trim X'41' Migration mapping X'42' Migration mapping X'50' Migration mapping X'60' Scale to fill X'70' UP3i Print Data mapping M X'06' [MODCA-6-142]|
#### Triplet X'04' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Mapping Option triplet.
MapValue Specifies the mapping option to be used for the data object referenced by the structured field.
Note: Not all mapping options are supported for all data objects; see the Map structured field
for each data object to see which options are supported.
Value Description
X'00' Position. The upper left corner of the data object's presentation space or
window is positioned coincident with the data object's content origin specified
in the XocaOset and YocaOset parameters in the Object Area Position
structured field. All data must be presented within the object area extents, or a
X'01' exception condition exists.
X'10' Position and trim. The upper left corner of the data object's presentation
space or window is positioned coincident with the data object's content origin
specified in the XocaOset and YocaOset parameters in the Object Area
Position structured field. All data that falls within the object area extents is
presented, but data that falls outside of the object area is not presented.
Note
: The Position and trim mapping option has value X'30' in IPDS.
X'20' Scale to fit. The center of the data object's presentation space or window is
mapped to the center of the object area defined by the associated Object
Area Descriptor structured field. The data object is symmetrically scaled up or
down while preserving the aspect ratio so that, at its maximum data size, it is
totally contained in the object area.
Triplet X'04'


When this option is specified, the data object's content origin specified in the
XocaOset and YocaOset parameters in the Object Area Position structured
field is ignored.
Notes:
1. For presentation objects, a presentation space size is required for a [MODCA-6-143]
scale-to-fit mapping of the object presentation space to the object area. If
the size of the presentation space is not specified by the object data
descriptor, the object data itself may specify the size. See “Object Type
Identifiers” for information on how the presentation space
size is specified by various objects. If the presentation space size is not
specified in the data descriptor, and if it is also not specified by the object,
the architected default is the presentation space size of the including
page or overlay.
2. The Scale to fit mapping option has value X'10' in IPDS and in BCOCA. [MODCA-6-144]
X'30' Center and trim. The center of the data object's presentation space or window
is mapped to the center of the object area defined by the associated Object
Area Descriptor structured field. All data that falls within the object area is
presented, but data that falls outside of the object area is not presented.
When this option is specified, the data object's content origin specified in the
XocaOset and YocaOset parameters in the Object Area Position structured
field is ignored.
Note
: The Center and trim mapping option has value X'20' in IPDS.
X'41' Migration mapping. See “Coexistence Parameters” for a
description.
X'42' Migration mapping. See “Coexistence Parameters” for a
description.
X'50' Migration mapping. See “Coexistence Parameters” for a
description.
X'60' Scale to fill. The center of the data object's presentation space or window is
mapped to the center of the object area defined by the associated Object
Area Descriptor structured field. The data object is scaled up or down so that
it totally fills the object area in both the X and Y directions. This may require
that the object presentation space be asymmetrically scaled by different scale
factors in the X and Y directions. Therefore, this mapping does not, in
general, preserve the aspect ratio of the data object.
When this option is specified, the data object's content origin specified in the
XocaOset and YocaOset parameters in the Object Area Position structured
field is ignored.
Note: For presentation objects, a presentation space size is required for a
scale-to-fill mapping of the object presentation space to the object area.
If the size of the presentation space is not specified by the object data
descriptor, the object data itself may specify the size. See “Object Type
Identifiers” for information on how the presentation space
size is specified by various objects. If the presentation space size is not
specified in the data descriptor, and if it is also not specified by the
object, the architected default is the presentation space size of the
including page or overlay.
X'70' UP3i Print Data mapping. This mapping is only used to map UP3i Print Data
objects. The specific mapping function is defined by the UP3i Print Data
format, which is identified by the Print Data Format ID that is specified in the
Triplet X'04'


first 4 bytes of the UP3i Print Data object. For a definition of UP3i Print Data
formats, see the UP3i specification available at the UP3i web site at:
www.afpcinc.org.
All others Reserved
### Structured Fields Using Triplet X'04'
• “Include Object (IOB)” [MODCA-6-145]
• “Map Bar Code Object (MBC)” [MODCA-6-146]
• “Map Container Data (MCD)” [MODCA-6-147]
• “Map Graphics Object (MGO)” [MODCA-6-148]
• “Map Image Object (MIO)” [MODCA-6-149]
• “Map Presentation T ext (MPT)” [MODCA-6-150]
• “Preprocess Presentation Object (PPO)” [MODCA-6-151]
Triplet X'04'


### Object Classification Triplet X'10'
The Object Classification is used to classify and identify object data. The object data may or may not be
defined by an AFP architecture. [MODCA-6-152]
#### Triplet X'10' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-153]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-154]|
| 0 UBIN Tlength 24–96 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-155]|
| 1 CODE Tid X'10' Identifies the Object | | | | | | Classification triplet M X'00' [MODCA-6-156]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-157]|
| 3 CODE ObjClass X'01', X'10', X'20', | | | | | | X'30', X'40', X'41', X'50' Specifies the object class: X'01' Time-invariant paginated presentation object X'10' Time-variant presentation object X'20' Executable program (non-presentation object) X'30' Set-up file (non- presentation object); document level X'40' Secondary Resource X'41' Data-object font X'50' Metadata Object (non- presentation object) M X'06' [MODCA-6-158]|
| 4–5 | Reserved; | should | | be zero | M | X'06' [MODCA-6-159]|
| 6–7 BITS StrucFlgs Provides information on the | | | | | | structure of the object container. See “Triplet X'10' Semantics” for StrucFlgs bit definitions. M X'06' [MODCA-6-160]|
| 8–23 CODE RegObjId MO:DCA-registered ASN.1 | | | | | | object identifier (OID) for object type. M X'06' [MODCA-6-161]|
| 24–55 | CHAR | ObjTpName | | Name of the object type | O | X'00' [MODCA-6-162]|
| 56–63 CHAR ObjLev Release level or version number | | | | | | of the object type O X'00' [MODCA-6-163]|
| 64–95 CHAR CompName Name of company or | | | | | | organization that owns object definition O X'00' [MODCA-6-164]|
#### Triplet X'10' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Classification triplet.
Triplet X'10'


ObjClass Specifies the object class based on differentiators such as temporal characteristics and
presentation form.
Value Description
X'01' Time-invariant paginated presentation object. If included for presentation, the
scope of the object is the including page or overlay , or if used as a secondary
resource to a QR Code with Image bar code object, the scope of the
presentation object is the bar code object that uses the presentation object
.
X'10' Time-variant presentation object. The scope of the object is not defined.
X'20' Executable program such as an object handler. This is not a presentation
object, that is, it is not a specification of final-form paginated object data. The
scope of the object is not defined.
X'30' Set-up information file, document level. This is not a presentation object, that
is, it is not a specification of final-form paginated object data. The scope of the
object is the document or documents for which the set-up file is invoked.
X'40' Secondary or tertiary
resource. This is a resource used by a presentation
object that may itself be a resource object. The resource itself is not a
standalone page level presentation object. The scope of the resource is the
object that uses the resource.
X'41' Data-object font. This is a non-FOCA font resource used to present text in a
data object. Examples of data-object fonts are TrueType fonts and OpenType
fonts. This object class includes collections of data-object fonts, such as
TrueType Collections (TTCs). The resource itself is not a standalone page
level presentation object. The scope of the resource is the data object that
uses the resource. If the data object that uses this font is also a resource, the
font resource becomes a secondary resource.
X'50' Metadata object. This is not a presentation object. The object is used to
specify metadata that may be associated with MO:DCA print file components
at various levels of the MO:DCA hierarchy (see “Metadata Objects in AFP” on
page 56).
All others Reserved
StrucFlgs Flags that characterize the structure of the object data. StrucFlgs bits have the following
definitions:
Bits Description
0–1 Object Container (BOC/EOC)
B'00' Reserved
B'01' The object data is not carried in a MO:DCA object container.
B'10' The container structure of the object data is unknown.
B'11' The object data is carried in a MO:DCA object container.
Notes:
1. These bits must be set to B'11' when the triplet appears on a Begin Object [MODCA-6-165]
Container (BOC) structured field.
2. When bits 0–1 are set to B'11', bits 4–5 must also be set to B'11'. [MODCA-6-166]
3. It is not advisable to set the bits to B'11' when the triplet appears on a structured [MODCA-6-167]
field that references the object such as an Include Object (IOB), since the
reference would become invalid if the object data is eventually carried in a
MO:DCA object container.
Triplet X'10'


2–3 Object environment group (OEG)
B'00' Reserved
B'01' Object container does not include an OEG.
B'10' It is not known whether the object structure includes an OEG.
B'11' Object container includes an OEG for the object data.
Notes:
1. When bits 2–3 are set to B'11', bits 0–1 must be set to B'11', and bits 4–5 must be [MODCA-6-168]
set to B'11'.
2. It is not advisable to set the bits to B'01' when the triplet appears on a structured [MODCA-6-169]
field that references the object such as an Include Object (IOB), since the
reference would become invalid if an OEG is eventually added.
4–5 Object Container Data (OCD) structured fields
B'00' Reserved
B'01' Object data is not carried in OCD structured fields.
B'10' It is not known whether the object data is carried in OCD structured fields.
B'11' Object data is carried in OCD structured fields.
Notes:
1. When bits 4–5 are set to B'11', bits 0–1 must also be set to B'11'. Conversely, [MODCA-6-170]
when bits 0–1 are set to B'11', bits 4–5 must also be set to B'11'.
2. It is not advisable to set the bits to B'01' when the triplet appears on a structured [MODCA-6-171]
field that references the object such as an Include Object (IOB), since the
reference would become invalid if the object data is eventually carried in OCD
structured fields.
6–15 Reserved; all bits must be B'0'.
RegObjId Specifies a unique numeric identifier for the object type carried in the object container. The
numeric identifier is an ASN.1 Object Identifier (OID), defined in ISO/IEC 8824:1990(E),
whose last component identifier is registered in the MO:DCA architecture. The complete OID
is encoded using the Basic Encoding Rules for ASN.1 specified in ISO/IEC 8825:1990(E). A
table of the registered component identifiers and the encoded OIDs is provided in “Object
Type Identifiers”. The OID is left justified and padded with zeros. This identifier is
mandatory.
ObjTpName Specifies the generic name used to refer to the object type. The name is left-justified and
padded with blanks. A value of all blanks, encoded using the active code page and character
set, indicates that the name is not specified.
ObjLev Specifies the release level or version number of the object type. The level is left-justified and
padded with blanks. A value of all blanks, encoded using the active code page and character
set, indicates that the level is not specified.
CompName Specifies the name of the company or organization that owns the syntactic and semantic
definition of the object type. The name is left-justified and padded with blanks. If the object
type is defined by a standards organization, specifies the name of that standards organization.
A value of all blanks, encoded using the active code page and character set, indicates that the
name is not specified.
Note: If an optional positional parameter is included on this triplet, all preceding optional positional parameters
become mandatory.
Triplet X'10'


Application Note: The following illustrates how the parameters in this triplet can be used to identify and
classify non-OCA object data:
• Encapsulated PostScript object that is carried in a MO:DCA object container: [MODCA-6-172]
Parameter Value
ObjClass X'01'
StrucFlgs X'EC00'
ObjId X'06072B12000401010D'
ObjTpName Encapsulated PostScript
ObjLev 2.0
CompName Adobe
• TIFF single-page image object whose container structure is not known: [MODCA-6-173]
Parameter Value
ObjClass X'01'
StrucFlgs X'A800'
ObjId X'06072B12000401010E'
ObjTpName TIFF
ObjLev 6.0
CompName Aldus
Architecture Note: A similar triplet, the MDD Two-up triplet, which also uses triplet ID X'10', is retired but is
still used on the MDD structured field in some implementations; see “MDD Two-up Triplet X'10'”Structured Fields Using Triplet X'10'
• “Begin Object Container (BOC)” [MODCA-6-174]
• “Begin Resource (BRS)” [MODCA-6-175]
• “Include Object (IOB)” [MODCA-6-176]
• “Link Logical Element (LLE)” [MODCA-6-177]
• “Map Data Resource (MDR)” [MODCA-6-178]
• “Preprocess Presentation Object (PPO)” [MODCA-6-179]
Triplet X'10'


MO:DCA Interchange Set Triplet X'18'
The MO:DCA Interchange Set triplet identifies the interchange set and the data stream type. [MODCA-6-180]
#### Triplet X'18' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-181]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-182]|
| 0 UBIN Tlength 5 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-183]|
| 1 CODE Tid X'18' Identifies the MO:DCA | | | | | | Interchange Set triplet M X'00' [MODCA-6-184]|
| 2 CODE IStype X'01', X'05' Specifies the type of interchange | | | | | | set: X'01' Presentation X'05' Archive/Presentation M X'06' [MODCA-6-185]|
| 3–4 CODE ISid X'0001', X'0900', | | | | | | X'0980', X'0C00', X'0D00', X'0D01', X'0D80' Interchange set identifier: For IStype X'01': X'0900' MO:DCA IS/1 X'0980' MO:DCA IS/1 + Function Set(s) X'0C00' Retired value X'0D00' MO:DCA IS/3 X'0D80' MO:DCA IS/3 + Function Set(s) See the Architecture notes of the Semantics section. For IStype X'05': X'0001' MO:DCA AFP/A X'0D01' MO:DCA AFP/A, MO:DCA IS/3 M X'06' Triplet X'18'Semantics Tlength Contains the length of the triplet. Tid Identifies the MO:DCA Interchange Set triplet. ISType Specifies the interchange set type. The valid interchange set type codes are: Value Description X'01' Presentation Document X'05' Archive/Presentation All others Reserved Architecture Note: ISType X'03' is reserved and is only used in MO:DCA-L data streams to indicate a Resource (MO:DCA-L) interchange set. Note that the MO:DCA-L format has been functionally capped and is no longer defined in the MO:DCA reference; for a definition of this format, see MO:DCA-L: The OS/2 PM Metafile (.met) Format. Triplet X'18' [MODCA-6-186]|


ISid Specifies the interchange set identifier.
The code assignments for a presentation document interchange set, type X'01', are:
Value Description
X'0900' MO:DCA IS/1
X'0980' MO:DCA IS/1 + Function Set(s)
X'0C00' Retired for MO:DCA IS/2; see “Retired Parameters”.
X'0D00' MO:DCA IS/3. See “MO:DCA Interchange Set 3 (IS/3)”.
X'0D80' MO:DCA IS/3 + Function Set(s)
All others Reserved
The code assignments for an archive/presentation interchange set, type X'05', are:
Value Description
X'0001' MO:DCA AFP/A. See “MO:DCA AFP Archive Interchange Set (AFP/A)” on
page 516.
X'0D01' MO:DCA AFP/A, MO:DCA IS/3
All others Reserved
Architecture Notes:
1. ISid X'0C00' is used in MO:DCA-L data streams with ISType X'03' to indicate a Resource ( [MODCA-6-187]
MO:DCA-L) interchange set. Note that the MO:DCA-L format has been functionally
capped and is no longer defined in the MO:DCA reference.
2. For ISType X'01', the ISid two-byte code is treated architecturally as two fields of one byte [MODCA-6-188]
each, where the first byte identifies the interchange set and the second byte specifies
additional information. For ISType X'05', the ISid two-byte code is treated as one field.
Note: Data streams that do not comply completely with an interchange set, such as those intended for private
use or exchange purposes, must ensure that this triplet is not specified on the BPF and BDT structured
fields.
### Structured Fields Using Triplet X'18'
• “Begin Document (BDT)” [MODCA-6-189]
• “Begin Print File (BPF)” [MODCA-6-190]
Triplet X'18'


### Font Descriptor Specification Triplet X'1F'
The Font Descriptor Specification triplet specifies the attributes of the desired font in a coded font reference. [MODCA-6-191]
#### Triplet X'1F' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-192]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-193]|
| 0 UBIN Tlength 9–20 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-194]|
| 1 CODE Tid X'1F' Identifies the Font Descriptor | | | | | | Specification triplet M X'00' [MODCA-6-195]|
| 2 CODE FtWtClass X'00'–X'09' Specifies character stroke | | | | | | thickness: X'00' Not specified X'01' Ultra-light X'02' Extra-light X'03' Light X'04' Semi-light X'05' Medium (normal) X'06' Semi-bold X'07' Bold X'08' Extra-bold X'09' Ultra-bold M X'06' [MODCA-6-196]|
| 3 CODE FtWdClass X'00'–X'09' Specifies character width-to- | | | | | | height ratio: X'00' Not specified X'01' Ultra-condensed X'02' Extra-condensed X'03' Condensed X'04' Semi-condensed X'05' Medium (normal) X'06' Semi-expanded X'07' Expanded X'08' Extra-expanded X'09' Ultra-expanded M X'06' [MODCA-6-197]|
| 4–5 UBIN FtHeight 0–32,767 Specifies vertical font size in [MODCA-6-198]| | | | | | |
| 1440ths of an inch (20ths of a | | | | | | point) M X'06' [MODCA-6-199]|
| 6–7 UBIN FtWidth 0–32,767 Specifies horizontal font size in [MODCA-6-200]| | | | | | |
| 1440ths of an inch (20ths of a | | | | | | point) M X'06' [MODCA-6-201]|
| 8 BITS FtDsFlags Qualifies the type of font | | | | | | characters. See “Triplet X'1F' Semantics” for FtDsFlags bit definitions. M X'06' [MODCA-6-202]|
| 9–18 | Reserved; | not | | checked | O | X'00' [MODCA-6-203]|
| 19 BITS FtUsFlags Describes the font environment. | | | | | | See “Triplet X'1F' Semantics” for FtUsFlags bit definitions. O X'02' Triplet X'1F' [MODCA-6-204]|


#### Triplet X'1F' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Font Descriptor Specification triplet.
FtWtClass Is a code that describes the thickness of strokes of the characters as one of the following
values:
Value Description
X'00' Not specified
X'01' Ultra-light
X'02' Extra-light
X'03' Light
X'04' Semi-light
X'05' Medium (normal)
X'06' Semi-bold
X'07' Bold
X'08' Extra-bold
X'09' Ultra-bold
All others Reserved
FtWdClass Is a code that describes the relative width-to-height ratio of the characters as one of the
following values:
Value Description
X'00' Not specified
X'01' Ultra-condensed
X'02' Extra-condensed
X'03' Condensed
X'04' Semi-condensed
X'05' Medium (normal)
X'06' Semi-expanded
X'07' Expanded
X'08' Extra-expanded
X'09' Ultra-expanded
All others Reserved
FtHeight Specifies the vertical size of the font character set in 1440ths of an inch (20ths of a point). See
the Font Object Content Architecture Reference for a description of the Vertical Font Size
parameter. The specified vertical font size is used to select a raster font or to scale an outline
technology font to the desired point size. A value of zero indicates that the vertical font size is
not specified. If the specified vertical font size conflicts with the nominal vertical font size in the
font object, the specified vertical font size overrides.
FtWidth Specifies the horizontal size of the font character set in 1440ths of an inch (20ths of a point).
See the Font Object Content Architecture Reference for a description of the Horizontal Font
Size parameter. A value of zero indicates that the horizontal font size is not specified.
Architecture Note: When the X'1F' triplet is specified on an MCF structured field in
MO:DCA-L data streams, the vertical font size and the horizontal font size are specified
in world coordinate values. Note that the MO:DCA-L format has been functionally
capped and is no longer defined in the MO:DCA reference; for a definition of this format,
see MO:DCA-L: The OS/2 PM Metafile (.met) Format.
Note: The specified horizontal font size may be used to generate the vertical font size, which
is used to select a raster font or to scale an outline technology font to the desired point
size, as follows:
Triplet X'1F'


• For typographic, proportionally-spaced fonts, the vertical font size is three times the [MODCA-6-205]
horizontal font size.
• For fixed-pitch, uniform character increment fonts, including Proportional Spacing [MODCA-6-206]
Machine (PSM) fonts, the vertical font size is calculated as follows:
1000 × font width [MODCA-6-207]
vertical font size = -------------------------
space character increment
(in relative units)
If the generated vertical font size conflicts with the specified vertical font size, the
specified vertical font size takes precedence.
Implementation Note: For IBM Core Interchange Courier fonts, and for IBM
Expanded Core fonts with FGID values less than 750 and with FGID values
between 3,840 and 4,095 inclusive (fixed pitch, uniform character increment,
and PSM fonts), a value of 600 relative units can be used for the space
character increment.
FtDsFlags Qualify the type of font characters. Flag bit 7 defines the meaning of this parameter when all
other flag bits have the value B'0'. FtDsFlags bits have the following descriptions:
Bit Description
0 Italic characters: [MODCA-6-208]
B'0' Font contains no italic characters.
B'1' Font contains italic characters.
1 Underscored characters: [MODCA-6-209]
B'0' Font contains no underscored characters.
B'1' Font contains underscored characters.
2 Reserved; must be B'0' [MODCA-6-210]
3 Hollow characters: [MODCA-6-211]
B'0' Font contains no hollow characters.
B'1' Font contains hollow characters.
4 Overstruck characters: [MODCA-6-212]
B'0' Font contains no overstruck characters.
B'1' Font contains overstruck characters.
5 Proportionally spaced characters: [MODCA-6-213]
B'0' Font contains uniformly spaced characters.
B'1' Font contains proportionally spaced characters.
6 Pairwise kerned characters: [MODCA-6-214]
B'0' Font contains no pairwise kerned characters.
B'1' Font contains pairwise kerned characters.
7 Definition of FtDsFlags parameter when bits 0–6 = B'0000000': [MODCA-6-215]
B'0' Parameter is not specified.
B'1' Parameter is specified; each flag bit carries its assigned meaning.
FtUsFlags Describe the font environment.
Bit Description
0 Reserved; must be B'0' [MODCA-6-216]
1 Font type: [MODCA-6-217]
B'0' Bitmapped font
B'1' Outline or vector font
2 Transform font: [MODCA-6-218]
Triplet X'1F'


B'0' Font will not be transformed.
B'1' Font may be transformed, that is, scaled, rotated, or sheared.
3–7 Reserved; all bits must be B'0'. [MODCA-6-219]
### Structured Field Using Triplet X'1F'
• “Map Coded Font (MCF) Format 2” [MODCA-6-220]
Triplet X'1F'


### Font Coded Graphic Character Set Global Identifier Triplet X'20'
The Font Coded Graphic Character Set Global Identifier triplet is used to specify the code page and character
set for a coded font. [MODCA-6-221]
#### Triplet X'20' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-222]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-223]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-224]|
| 1 CODE Tid X'20' Identifies the Font Coded | | | | | | Graphic Character Set Global Identifier triplet M X'00' [MODCA-6-225]|
| 2–3 CODE GCSGID X'0001'–X'FFFE' Specifies the Graphic Character | | | | | | Set Global Identifier M X'06' X'FFFF' Specifies the character set consisting of all characters in the code page [MODCA-6-226]|
| 4–5 CODE CPGID X'0001'–X'FFFE' Specifies the Code Page Global | | | | | | Identifier M X'06' [MODCA-6-227]|
#### Triplet X'20' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Font Coded Graphic Character Set Global Identifier triplet.
GCSGID Specifies the two-byte binary Graphic Character Set Global Identifier (GCSGID). The
character set defined by the GCSGID is associated with the coded font and identifies a
minimum set of coded font graphic characters required for presentation. It may be a character
set that is associated with the code page, or with the font character set, or with both. Valid
values for Graphic Character Set Global Identifiers are 1 through 65,534. A value of 65,535
(X'FFFF') indicates that a character set consisting of all characters that have assigned code
points in the associated code page is to be used.
CPGID Specifies the two-byte binary Code Page Global Identifier (CPGID) assigned to the code page
associated with the coded font. Valid values for Code Page Global Identifiers are 1 through
65,534.
Note: The concatenation of the GCSGID and CPGID is currently referred to as the Coded Graphic Character
Set Global Identifier (CGCSGID). In the past, it was also known as the Global Character Set Identifier
(GCID).
### Structured Fields Using Triplet X'20'
• “Map Coded Font (MCF) Format 2” [MODCA-6-228]
• “Map Data Resource (MDR)” [MODCA-6-229]
Triplet X'20'


### Resource Object Type Triplet X'21'
The Resource Object Type triplet identifies the type of object enveloped by the Begin Resource (BRS) and End
Resource (ERS) structured fields.
Architecture Note: A similar triplet, the Object Function Set Specification triplet, that unfortunately also uses
triplet ID X'21', is retired but is still used on the BDT structured field; see “Object Function Set
Specification Triplet X'21'”. [MODCA-6-230]
#### Triplet X'21' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-231]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-232]|
| 0 UBIN Tlength 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-233]|
| 1 CODE Tid X'21' Identifies the Resource Object | | | | | | Type triplet M X'00' [MODCA-6-234]|
| 2 CODE ObjType X'03', X'05'–X'06', | | | | | | X'40'–X'42', X'92', X'9B', X'A8', X'FB'–X'FE' Specifies the object type: X'03' Graphics (GOCA) object X'05' Bar Code (BCOCA) object X'06' Image (IOCA) object X'40' Font Character Set object X'41' Code Page object X'42' Coded Font object X'92' Object Container X'9B' Presentation T ext (PTOCA) object with OEG X'A8' Document object X'FB' Page Segment object X'FC' Overlay object X'FD' Reserved; see Triplet X'21' Semantics X'FE' Form Map object M X'06' [MODCA-6-235]|
| 3–9 | CODE | ConData | | Constant data | M | X'06' [MODCA-6-236]|
#### Triplet X'21' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Resource Object Type triplet.
ObjType Specifies the object type.
Value Description
X'03' Graphics (GOCA) object
X'05' Bar Code (BCOCA) object
X'06' Image (IOCA) object
X'40' Font Character Set object
X'41' Code Page object
Triplet X'21'


X'42' Coded Font object
X'92' Object Container
X'9B' Presentation T ext (PTOCA) object with OEG
X'A8' Document object
X'FB' Page Segment object
X'FC' Overlay object
X'FD' Reserved. This value is used in AFP Line Data environments to identify a
Page Map, also called Page Definition or PageDef object. For a description of
Page Maps, see the Advanced Function Presentation: Programming Guide
and Line Data Reference.
X'FE' Form Map object
All others Reserved
ConData Constant data. Must be set to X'0000 0000 0000 00'.
Structured Field Using Triplet (X'21')
• “Begin Resource (BRS)” [MODCA-6-237]
Triplet X'21'


### Extended Resource Local Identifier Triplet X'22'
The Extended Resource Local Identifier triplet specifies a resource type and a four-byte local identifier or LID.
The LID usually is associated with a specific resource name by a map structured field, such as a Map Media
Type structured field. [MODCA-6-238]
#### Triplet X'22' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-239]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-240]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-241]|
| 1 CODE Tid X'22' Identifies the Extended Resource | | | | | | Local Identifier triplet M X'00' [MODCA-6-242]|
| 2 CODE ResType X'30', X'40', X'42' Specifies the resource type: | | | | | | X'30' Retired value X'40' Media Type resource X'42' Media Destination resource M X'06' [MODCA-6-243]|
| 3–6 CODE ResLID X'00000000'– | | | | | | X'FFFFFFFF' Specifies the extended resource local ID: X'00000000'–X'0000FFFF' Resource type X'40' X'00000001'–X'0000FFFF' Resource type X'42' X'00000000'–X'FFFFFFFF' Resource types other than X'40' and X'42' M X'06' [MODCA-6-244]|
#### Triplet X'22' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Extended Resource Local Identifier triplet.
ResType Specifies the resource type associated with the extended local ID.
Value Description
X'30' Retired for private use. See “Retired Parameters”.
Architecture Note: This value is used in AFP line-data environments in a
Page Definition object to denote an IOB Reference. It matches an
Include Object (IOB) structured field to a Descriptor. For more
information see Advanced Function Presentation: Programming Guide
and Line Data Reference.
X'40' Media Type resource
X'42' Media Destination resource
All others Reserved
Architecture Note: The value ResType X'10' = Image Resource is reserved and is only used
when this triplet is specified on an MDR in MO:DCA-L data streams. Note that the
MO:DCA-L format has been functionally capped and is no longer defined in the
MO:DCA reference; for a definition of this format, see MO:DCA-L: The OS/2 PM
Metafile (.met) Format.
Triplet X'22'


ResLID Specifies a unique resource object Local ID. It may be in the range of X'00000000' to
X'FFFFFFFF' for all resource types other than X'40' and X'42'. For resource type X'40' (Media
Type), the range is restricted to X'00000000' to X'0000FFFF'. For resource type X'42' (Media
Destination), the range is restricted to X'00000001' to X'0000FFFF'.
Architecture Notes:
• The local IDs used with resource type X'40' are specified with a X'E8nn' + X'E9nn' [MODCA-6-245]
keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this
resource type is restricted to 2-byte values.
• The local IDs used with resource type X'42' are specified with a X'90nn' + X'91nn' [MODCA-6-246]
keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this
resource type is restricted to 2-byte values. [MODCA-6-247]
### Structured Fields Using Triplet X'22'
• “Map Media Destination (MMD)” [MODCA-6-248]
• “Map Media Type (MMT)” [MODCA-6-249]
Triplet X'22'


### Resource Local Identifier Triplet X'24'
The Resource Local Identifier triplet may be used to specify a resource type and a one-byte local identifier or
LID. The LID usually is associated with a specific resource name by a map structured field, such as a Map
Coded Font structured field. [MODCA-6-250]
#### Triplet X'24' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-251]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-252]|
| 0 UBIN Tlength 4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-253]|
| 1 CODE Tid X'24' Identifies the Resource Local | | | | | | Identifier triplet M X'00' [MODCA-6-254]|
| 2 CODE ResType X'00', X'02', X'05' Specifies the resource type: | | | | | | X'00' Usage-dependent X'02' Page Overlay X'05' Coded Font M X'06' [MODCA-6-255]|
| 3 | CODE | ResLID | | X'00'–X'FE' Specifies the resource local ID | M | X'06' [MODCA-6-256]|
#### Triplet X'24' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Resource Local Identifier triplet.
ResType Specifies the resource type associated with the local ID.
Value Description
X'00' Usage-dependent. The resource type is implied by the context of the
structured field in which this triplet parameter occurs. A X'01' exception
condition exists if more than one resource local ID occurs within a given
structured field and this value is specified.
X'02' Page Overlay resource
X'05' Coded Font resource
All others Reserved
Architecture Note: The value ResType X'07' = Color Attribute T able is reserved and is only
used when this triplet is specified on a Map Color Attribute T able (MCA) structured field
in MO:DCA-L data streams. Note that the MO:DCA-L format has been functionally
capped and is no longer defined in the MO:DCA reference; for a definition of this format,
see MO:DCA-L: The OS/2 PM Metafile (.met) Format.
ResLID Specifies a unique resource object local ID. It may be in the range of X'00' to X'FE'.
Application Note: Most AFP print servers only support the LID range that is defined in the MO:DCA IS/1 and
IS/3 interchange set definitions, which is X'01' to X'7F', and also the value X'FE'. [MODCA-6-257]
### Structured Fields Using Triplet X'24'
• “Map Coded Font (MCF) Format 2” [MODCA-6-258]
• “Map Page Overlay (MPO)” [MODCA-6-259]
Triplet X'24'


### Resource Section Number Triplet X'25'
The Resource Section Number triplet specifies a coded font section number. It may be used to select a single
section of a double-byte coded font if less than the entire double-byte coded font is required for processing. For
a description of coded fonts see the Font Object Content Architecture Reference. [MODCA-6-260]
#### Triplet X'25' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-261]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-262]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-263]|
| 1 CODE Tid X'25' Identifies the Resource Section | | | | | | Number triplet M X'00' [MODCA-6-264]|
| 2 CODE ResSNum X'00'–X'FF' Specifies the resource section | | | | | | number M X'06' [MODCA-6-265]|
#### Triplet X'25' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Resource Section Number triplet.
ResSNum Specifies the resource section number. The valid resource section number values are
determined by the encoding scheme used for the font. For fonts encoded using the EBCDIC
Presentation double-byte encoding scheme (encoding scheme ID X'62nn') or the EBCDIC
Presentation single-byte encoding scheme (encoding scheme ID X'61nn'), the valid resource
section numbers are:
Value Comments
X'00' Must be used when this triplet references a single-byte coded font. Specifies
all sections when this triplet references a double-byte coded font.
X'41'–X'FE' Used only for double-byte coded fonts to select a specific font section
All others Reserved
Notes:
1. If this triplet is omitted, the architected default value for the resource section number is X'00'. [MODCA-6-266]
2. The encoding scheme is specified by the Encoding Scheme ID triplet; see “Encoding Scheme ID Triplet [MODCA-6-267]
X'50'”.
### Structured Field Using Triplet X'25'
• “Map Coded Font (MCF) Format 2” [MODCA-6-268]
Triplet X'25'


### Character Rotation Triplet X'26'
The Character Rotation triplet is used to specify character rotation relative to the character coordinate system.
See the Font Object Content Architecture Reference for further information. [MODCA-6-269]
#### Triplet X'26' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-270]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-271]|
| 0 UBIN Tlength 4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-272]|
| 1 CODE Tid X'26' Identifies the Character Rotation | | | | | | triplet M X'00' [MODCA-6-273]|
| 2–3 CODE CharRot X'0000', X'2D00', | | | | | | X'5A00', X'8700' Specifies the clockwise character rotation: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' [MODCA-6-274]|
#### Triplet X'26' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Character Rotation triplet.
CharRot Specifies the clockwise character rotation relative to the character coordinate system. Valid
values are the following:
Value Character Rotation
X'0000' 0 degrees
X'2D00' 90 degrees
X'5A00' 180 degrees
X'8700' 270 degrees
All others Reserved
Note: If this triplet is omitted, the architected default value for the character rotation is X'0000',
zero degrees.
### Structured Field Using Triplet X'26'
• “Map Coded Font (MCF) Format 2” [MODCA-6-275]
Triplet X'26'


### Object Byte Offset Triplet X'2D'
The Object Byte Offset triplet is used to specify the byte offset of an indexed object within a document. [MODCA-6-276]
#### Triplet X'2D' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-277]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-278]|
| 0 UBIN Tlength 6, 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-279]|
| 1 CODE Tid X'2D' Identifies the Object Byte Offset | | | | | | triplet M X'00' [MODCA-6-280]|
| 2–5 UBIN DirByOff X'00000000'– | | | | | | X'FFFFFFFE' Byte offset M X'06' X'FFFFFFFF' If bytes 6–9 are not specified, object is outside document [MODCA-6-281]|
| 6–9 UBIN DirByHi X'00000000'– | | | | | | X'FFFFFFFF' Byte offset, high-order bytes O X'00' [MODCA-6-282]|
#### Triplet X'2D' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Byte Offset triplet.
DirByOff Specifies the offset, in bytes, of an indexed object from the beginning of the document. The
Begin Document (BDT) structured field begins the document object and has an offset of 0.
The first byte in the BDT is counted as byte 1 of the offset to objects that follow, so that if the
BDT consists of n bytes, the offset to a Begin Object structured field that immediately follows
the BDT is n. The byte offset has a range of X'00000000' to X'FFFFFFFE'. A value of
X'FFFFFFFF' signifies that the indexed object is outside the document.
DirByHi If specified, indicates that this triplet specifies the byte offset as an 8-byte parameter, where
DirByOff specifies the low-order 4 bytes and DirByHi specifies the high-order 4 bytes. In that
case, the value DirByOff = X'FFFFFFFF' is a real offset value and does not signify that the
indexed object is outside the document. [MODCA-6-283]
### Structured Field Using Triplet X'2D'
• “Index Element (IEL)” [MODCA-6-284]
Triplet X'2D'


### Attribute Value Triplet X'36'
The Attribute Value triplet is used to specify a value for a document attribute. [MODCA-6-285]
#### Triplet X'36' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-286]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-287]|
| 0 UBIN Tlength 4–254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-288]|
| 1 CODE Tid X'36' Identifies the Attribute Value | | | | | | triplet M X'00' [MODCA-6-289]|
| 2–3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-290]|
| 4–n | CHAR | AttVal | | Attribute Value | O | X'00' [MODCA-6-291]|
#### Triplet X'36' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Attribute Value triplet.
AttVal Is a character string which specifies the value of a document attribute. If this parameter is
omitted, the value of the document attribute is specified to be null, that is, no value is assigned
to the attribute.
### Structured Field Using Triplet X'36'
• “T ag Logical Element (TLE)” [MODCA-6-292]
Triplet X'36'


### Descriptor Position Triplet X'43'
The Descriptor Position triplet is used to associate an Object Area Position structured field with an Object Area
Descriptor structured field. [MODCA-6-293]
#### Triplet X'43' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-294]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-295]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-296]|
| 1 CODE Tid X'43' Identifies the Descriptor Position | | | | | | triplet M X'00' [MODCA-6-297]|
| 2 CODE DesPosID X'01'–X'7F' Specifies the associated Object | | | | | | Area Position structured field M X'06' [MODCA-6-298]|
#### Triplet X'43' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Descriptor Position triplet.
DesPosID Specifies the identifier of the Object Area Position structured field that is associated with the
descriptor for this object area. [MODCA-6-299]
### Structured Field Using Triplet X'43'
• “Object Area Descriptor (OBD)” [MODCA-6-300]
Triplet X'43'


### Media Eject Control Triplet X'45'
The Media Eject Control triplet is used to specify the type of media eject that is performed and the type of
controls that are activated when a new medium map is invoked and N-up partitioning is specified. [MODCA-6-301]
#### Triplet X'45' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-302]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-303]|
| 0 UBIN Tlength 4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-304]|
| 1 CODE Tid X'45' Identifies the Media Eject Control | | | | | | triplet M X'00' [MODCA-6-305]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-306]|
| 3 CODE EjCtrl X'01'–X'04' Media eject controls: | | | | | | X'01' Eject to new sheet X'02' Conditional eject to next partition X'03' Conditional eject to next front-side partition X'04' Conditional eject to next back-side partition M X'06' [MODCA-6-307]|
#### Triplet X'45' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Media Eject Control triplet.
EjCtrl Is a code that identifies the type of media eject that should be performed and the type of
controls that should be activated when the medium map containing this triplet is invoked and
N-up partitioning is specified. This triplet is ignored when it occurs on the medium map that is
activated at the beginning of a document regardless of whether this medium map is explicitly
invoked or implicitly invoked as the default. The following types of media eject can be
specified:
• Eject to new sheet [MODCA-6-308]
• Conditional eject to next partition [MODCA-6-309]
• Conditional eject to next front-side partition [MODCA-6-310]
• Conditional eject to next back-side partition [MODCA-6-311]
The two types of controls that may be activated are medium level controls and page level
controls. Media level controls are controls that affect the medium, such as the specification of
medium overlays, medium size, medium orientation, medium copies, N-up, simplex or duplex,
medium finishing, media type, and media source and destination selection. They are defined
by the Map Medium Overlay (MMO), Medium Descriptor (MDD), Medium Copy Count (MCC),
Medium Finishing Control (MFC), Map Media Type (MMT), Map Media Destination (MMD),
Presentation Environment Control (PEC), and Medium Modification Control (MMC) structured
fields. Page level controls are controls that affect the pages that are placed on the medium,
such as the specification of page modifications, page position, and page orientation. They are
defined by the Map Page Overlay (MPO), Page Position (PGP), and Page Modification
Control (PMC) structured fields.
In the following descriptions, the term “existing PGP” refers to the Page Position (PGP)
structured field that was active with the existing medium map, and the term “new PGP” refers
to the PGP that is activated with the new medium map. The media level controls in the new
Triplet X'45'


and existing medium maps are considered to be identical if and only if all of the following
conditions are met:
• Any MMO, MDD, MCC, MFC with MFCScpe = X'04' (medium map level MFC, each sheet), [MODCA-6-312]
MMD, PEC, MMT , and MMC structured field that appears in the existing medium map must
also appear in the new medium map.
• The MMO, MDD, MCC, MFC with MFCScpe = X'04' (medium map level MFC, each sheet), [MODCA-6-313]
MMD, PEC, MMT , and MMC structured fields that appear in both the new and existing
medium maps must not only have the same functional content but also must have the same
form. For example, if both medium maps contain an MMO structured field, the MMO
repeating groups must map the same overlay names to the same local IDs, and the
repeating groups must appear in the same order. Similarly, if both medium maps contain an
MMC structured field, the MMC keywords must be the same, must specify the same values,
and must appear in the same order.
Note that MFCs that start and continue medium map level sheet collections for finishing
(MFCScpe = X'05') are excluded from the media level controls compare. These structured
fields are processed and may cause a sheet eject based on their own processing rules. If
processing such MFCs does not cause a sheet eject, the media level control compare
determines whether or not a sheet eject is performed. Note also that a sheet eject is always
generated after a finishing operation is applied to a collection of media or sheets.
The following values are supported for the EjCtrl parameter:
Value Description
X'01' Eject to new sheet. The new medium map is a complete replacement for the
existing medium map and specifies the medium level controls and page level
controls to be used to process the new sheet.
X'02' Conditional eject to next partition. This control is used with N-up partitioning. If
N-up is not specified, or if the presentation device does not support N-up, this
control is processed as X'01' (eject to new sheet). If the medium level controls
in the new medium map are not identical to the medium level controls in the
existing medium map, or if the page level controls in the new medium map
specify a different page placement than the page level controls in the existing
medium map, this control is processed as X'01' (eject to new sheet). If the
medium level controls in the new medium map are identical to the medium
level controls in the existing medium map, and if both medium maps specify
default page placement or both specify explicit page placement, the page
level controls in the new medium map are activated and an eject to the next
partition is performed. The location of the next partition is determined as
follows:
• Default page placement: The next partition is the next sequential partition [MODCA-6-314]
on the current sheet-side. If all partitions on the current sheet-side have
been used, it is the first partition on the next sheet-side, which for simplex
printing is always the front side of the next sheet, and for duplex printing is
either the back side of the current sheet (if currently on a front side) or the
front side of the next sheet (if currently on a back side).
• Explicit page placement: The next partition is defined by the repeating [MODCA-6-315]
group in the new PGP that corresponds to the next repeating group that
was to be processed in the existing PGP . If all PGP repeating groups have
been processed, an implicit sheet eject is performed and processing
continues with the first repeating group in the new PGP . For example, if the
first repeating group in the existing PGP was last used to place a page,
processing continues with the second repeating group in the new PGP .
Triplet X'45'


Note: The new PGP should place pages into the same partitions as the
existing PGP . Otherwise, previously placed pages may be
overwritten.
X'03' Conditional eject to next front-side partition. This control is used with N-up
partitioning. If N-up is not specified, or if the presentation device does not
support N-up, this control is processed as X'01' (eject to new sheet). If the
medium level controls in the new medium map are not identical to the medium
level controls in the existing medium map, or if the page level controls in the
new medium map specify a different page placement than the page level
controls in the existing medium map, this control is processed as X'01' (eject
to new sheet). If the medium level controls in the new medium map are
identical to the medium level controls in the existing medium map, and if both
medium maps specify default page placement or both specify explicit page
placement, the page level controls in the new medium map are activated and
an eject to the next front-side partition is performed. The location of the next
front-side partition is determined as follows:
• Default page placement: If currently placing pages on the front sheet side, [MODCA-6-316]
the next front-side partition is the next sequential partition. If all partitions on
the front sheet-side have been used, an implicit sheet eject is performed
and processing continues with the first partition on the front side of the next
sheet. If currently placing pages on the back sheet side, an implicit sheet
eject is performed and processing continues with the first partition on the
front side of the next sheet.
• Explicit page placement: The next front-side partition is defined by the [MODCA-6-317]
repeating group in the new PGP that corresponds to the next repeating
group specifying front sheet-side that was to be processed in the existing
PGP . If all PGP repeating groups that specify front sheet-side have been
processed, an implicit sheet eject is performed and processing continues
with the first repeating group in the new PGP that specifies front sheet-side.
For example, if the first repeating group in the existing PGP was last used to
place a page, and if the second repeating group specifies a back-side
partition and the third repeating group specifies a front-side partition,
processing continues with the third repeating group in the new PGP .
Note: The new PGP should place pages into the same partitions as the
existing PGP , otherwise previously-placed pages may be overwritten.
X'04' Conditional eject to next back-side partition. This control is used with N-up
partitioning. If N-up is not specified, or if the presentation device does not
support N-up, this control is processed as X'01' (eject to new sheet). If the
medium level controls in the new medium map are not identical to the medium
level controls in the existing medium map, or if the page level controls in the
new medium map specify a different page placement than the page level
controls in the existing medium map, this control is processed as X'01' (eject
to new sheet). If the medium level controls in the new medium map are
identical to the medium level controls in the existing medium map, and if both
medium maps specify default page placement or both specify explicit page
placement, the page level controls in the new medium map are activated and
an eject to the next back-side partition is performed. The location of the next
back-side partition is determined as follows:
• Default page placement: If currently placing pages on the back sheet side, [MODCA-6-318]
the next back-side partition is the next sequential partition. If all partitions on
the back sheet-side have been used, an implicit sheet eject is performed
and processing continues with the first partition on the back side of the next
Triplet X'45'


sheet. If currently placing pages on the front sheet-side, processing
continues with the first partition on the back sheet-side.
• Explicit page placement: The next back-side partition is defined by the [MODCA-6-319]
repeating group in the new PGP that corresponds to the next repeating
group specifying back sheet-side that was to be processed in the existing
PGP . If all PGP repeating groups that specify back sheet-side have been
processed, an implicit sheet eject is performed and processing continues
with the first repeating group in the new PGP that specifies back sheet-side.
For example, if the first repeating group in the existing PGP was last used to
place a page, and if the second and third repeating groups specify front-
side partitions and the fourth repeating group specifies a back-side partition,
processing continues with the fourth repeating group in the new PGP .
Note: The new PGP should place pages into the same partitions as the
existing PGP , otherwise previously-placed pages may be overwritten.
All others Reserved
Note: If this triplet is not specified, the architected default for the EjCtrl parameter is X'01'; that is, perform a
sheet eject and activate all controls specified by the invoked medium map. [MODCA-6-320]
### Structured Field Using Triplet X'45'
• “Begin Medium Map (BMM)” [MODCA-6-321]
Triplet X'45'


### Measurement Units Triplet X'4B'
The Measurement Units triplet is used to specify the units of measure for a presentation space. [MODCA-6-322]
#### Triplet X'4B' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-323]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-324]|
| 0 UBIN Tlength 8 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-325]|
| 1 CODE Tid X'4B' Identifies the Measurement Units | | | | | | triplet M X'00' [MODCA-6-326]|
| 2 CODE XoaBase X'00'–X'01' Presentation space unit base for | | | | | | the X axis: X'00' 10 inches X'01' 10 centimeters M X'06' [MODCA-6-327]|
| 3 CODE YoaBase X'00'–X'01' Presentation space unit base for | | | | | | the Y axis: X'00' 10 inches X'01' 10 centimeters M X'06' [MODCA-6-328]|
| 4–5 UBIN XoaUnits 1–32,767 Presentation space units per unit | | | | | | base for the X axis M X'06' [MODCA-6-329]|
| 6–7 UBIN YoaUnits 1–32,767 Presentation space units per unit | | | | | | base for the Y axis M X'06' [MODCA-6-330]|
#### Triplet X'4B' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Measurement Units triplet.
XoaBase Specifies the unit base for the X axis of the presentation space coordinate system.
YoaBase Specifies the unit base for the Y axis of the presentation space coordinate system.
Note: A X'01' exception condition exists if the XoaBase and YoaBase values are not identical.
XoaUnits Specifies the number of units per unit base for the X axis of the presentation space coordinate
system.
YoaUnits Specifies the number of units per unit base for the Y axis of the presentation space coordinate
system.
### Structured Fields Using Triplet X'4B'
• “Include Object (IOB)” [MODCA-6-331]
• “Link Logical Element (LLE)” [MODCA-6-332]
• “Object Area Descriptor (OBD)” [MODCA-6-333]
• “Page Modification Control (PMC)” [MODCA-6-334]
• “Preprocess Presentation Object (PPO)” [MODCA-6-335]
Triplet X'4B'


### Object Area Size Triplet X'4C'
The Object Area Size triplet is used to specify the extent of an object area in the X and Y directions. [MODCA-6-336]
#### Triplet X'4C' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-337]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-338]|
| 0 UBIN Tlength 9 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-339]|
| 1 CODE Tid X'4C' Identifies the Object Area Size | | | | | | triplet M X'00' [MODCA-6-340]|
| 2 CODE SizeType X'02' Specifies the actual object area | | | | | | size to be used M X'06' [MODCA-6-341]|
| 3–5 | UBIN | XoaSize | | 1–32,767 Object area extent for the X axis | M | X'06' [MODCA-6-342]|
| 6–8 | UBIN | YoaSize | | 1–32,767 Object area extent for the Y axis | M | X'06' [MODCA-6-343]|
#### Triplet X'4C' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Area Size triplet.
SizeType Specifies the object area size type.
Value Description
X'02' Object Area Size
All others Reserved
XoaSize Specifies the extent of the X axis of the object area coordinate system. This is also known as
the object area's X axis size.
YoaSize Specifies the extent of the Y axis of the object area coordinate system. This is also known as
the object area's Y axis size. [MODCA-6-344]
### Structured Fields Using Triplet X'4C'
• “Include Object (IOB)” [MODCA-6-345]
• “Object Area Descriptor (OBD)” [MODCA-6-346]
• “Preprocess Presentation Object (PPO)” [MODCA-6-347]
Triplet X'4C'


### Area Definition Triplet X'4D'
The Area Definition triplet is used to define the position and size of a rectangular area on a document
component presentation space. The document component may be a page or overlay, in which case the area is
defined on the page or overlay presentation space, or it may be a data object, in which case the area is defined
on the object area presentation space. [MODCA-6-348]
#### Triplet X'4D' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-349]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-350]|
| 0 UBIN Tlength 15 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-351]|
| 1 CODE Tid X'4D' Identifies the Area Definition | | | | | | triplet M X'00' [MODCA-6-352]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-353]|
| 3–5 | SBIN | XarOset | | 0–32,767 X-axis origin of the area | M | X'06' [MODCA-6-354]|
| 6–8 | SBIN | YarOset | | 0–32,767 Y-axis origin of the area | M | X'06' [MODCA-6-355]|
| 9–11 | UBIN | XarSize | | 1–32,767 Area extent for the X axis | M | X'06' [MODCA-6-356]|
| 12–14 | UBIN | YarSize | | 1–32,767 Area extent for the Y axis | M | X'06' [MODCA-6-357]|
#### Triplet X'4D' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Area Definition triplet
XarOset Specifies the offset along the X axis of the presentation space coordinate system to the origin
of the area.
YarOset Specifies the offset along the Y axis of the presentation space coordinate system to the origin
of the area.
XarSize Specifies the extent of the area along the X axis of the presentation space coordinate system.
YarSize Specifies the extent of the area along the Y axis of the presentation space coordinate system. [MODCA-6-358]
### Structured Field Using Triplet X'4D'
• “Link Logical Element (LLE)” [MODCA-6-359]
Triplet X'4D'


### Color Specification Triplet X'4E'
The Color Specification triplet is used to specify a color value and defines the color space and encoding for that
value.
#### Triplet X'4E' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-360]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-361]|
| 0 UBIN Tlength 14–16 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-362]|
| 1 CODE Tid X'4E' Identifies the Color Specification | | | | | | triplet M X'00' [MODCA-6-363]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-364]|
| 3 CODE ColSpce X'01', X'04', X'06', | | | | | | X'08', X'40' Color space: X'01' RGB X'04' CMYK X'06' Highlight color space X'08' CIELAB X'40' Standard OCA color space M X'06' [MODCA-6-365]|
| 4–7 | Reserved; | should | | be zero | M | X'06' [MODCA-6-366]|
| 8 UBIN ColSize1 X'01'–X'08', X'10' Number of bits in component 1; | | | | | | see color space definitions M X'06' [MODCA-6-367]|
| 9 UBIN ColSize2 X'00'–X'08' Number of bits in component 2; | | | | | | see color space definitions M X'06' [MODCA-6-368]|
| 10 UBIN ColSize3 X'00'–X'08' Number of bits in component 3; | | | | | | see color space definitions M X'06' [MODCA-6-369]|
| 11 UBIN ColSize4 X'00'–X'08' Number of bits in component 4; | | | | | | see color space definitions M X'06' [MODCA-6-370]|
| 12–n Color Color specification; see “Triplet | | | | | | X'4E' Semantics” for details M X'06' [MODCA-6-371]|
#### Triplet X'4E' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Color Specification triplet.
ColSpce Is a code that defines the color space and the encoding for the color specification.
Value Description
X'01' RGB color space. The color value is specified with three components.
Components 1, 2, and 3 are unsigned binary numbers that specify the red,
green, and blue intensity values, in that order. ColSize1, ColSize2, and
ColSize3 are non-zero and define the number of bits used to specify each
component. ColSize4 is reserved and should be set to zero. The intensity
range for the R,G,B components is 0 to 1, which is mapped to the binary
value range 0 to (2
ColSizeN - 1), where N=1,2,3.
Triplet X'4E'


Architecture Note: The reference white point and the chromaticity
coordinates for RGB are defined in SMPTE RP 145-1987, entitled
Color Monitor Colorimetry, and in RP 37-1969, entitled Color
Temperature for Color Television Studio Monitors, respectively. The
reference white point is commonly known as Illuminant D6500 or simply
D65. The R,G,B components are assumed to be gamma-corrected
(nonlinear) with a gamma of 2.2.
X'04' CMYK color space. The color value is specified with four components.
Components 1, 2, 3, and 4 are unsigned binary numbers that specify the
cyan, magenta, yellow, and black intensity values, in that order. ColSize1,
ColSize2, ColSize3, and ColSize4 are non-zero and define the number of bits
used to specify each component. The intensity range for the C,M,Y ,K
components is 0 to 1, which is mapped to the binary value range 0 to (2
ColSizeN
- 1), where N=1,2,3,4. This is a presentation-system-dependent color space. [MODCA-6-372]
X'06' Highlight color space. This color space defines a request for the presentation
device to generate a highlight color. The color value is specified with one to
three components.
Component 1 is a two-byte unsigned binary number that specifies the
highlight color number. The first highlight color is assigned X'0001', the
second highlight color is assigned X'0002', and so on. The value X'0000'
specifies the presentation device default color. ColSize1 = X'10' and defines
the number of bits used to specify component 1.
Component 2 is an optional one-byte unsigned binary number that specifies a
percent coverage for the specified color. Percent coverage can be any value
from 0% to 100% (X'00'–X'64'). The number of distinct values supported is
presentation-system dependent. If the coverage is less than 100%, the
remaining coverage is achieved with color of medium. ColSize2 = X'00' or
X'08' and defines the number of bits used to specify component 2. A value of
X'00' indicates that component 2 is not specified in the color value, in which
case the architected default for percent coverage is 100%. A value of X'08'
indicates that component 2 is specified in the color value.
Component 3 is an optional one-byte unsigned binary number that specifies a
percent shading, which is a percentage of black that is to be added to the
specified color. Percent shading can be any value from 0% to 100% (X'00'–
X'64'). The number of distinct values supported is presentation-system
dependent. If percent coverage and percent shading are specified, the
effective range for percent shading is 0% to (100-coverage)%. If the sum of
percent coverage plus percent shading is less than 100%, the remaining
coverage is achieved with color of medium. ColSize3 = X'00' or X'08' and
defines the number of bits used to specify component 3. A value of X'00'
indicates that component 3 is not specified in the color value, in which case
the architected default for percent shading is 0%. A value of X'08' indicates
that component 3 is specified in the color value.
Implementation Note: The percent shading parameter is currently not
supported in AFP environments.
ColSize4 is reserved and should be set to zero. This is a presentation-
system-dependent color space.
Architecture Notes:
1. The color that is rendered when a highlight color is specified is [MODCA-6-373]
presentation-system dependent. For presentation devices that support
colors other than black, highlight color values in the range X'0001' to
Triplet X'4E'


X'FFFF' may be mapped to any color. For bilevel devices, the color may
be simulated with a graphic pattern.
2. If the specified highlight color is “presentation device default”, devices [MODCA-6-374]
whose default color is black use the percent coverage parameter, which is
specified in component 2, to render a percent shading.
3. On printing devices, the color of medium is normally white, in which case [MODCA-6-375]
a coverage of n% results in adding (100-n)% white to the specified color,
or tinting the color with (100-n)% white. Display devices may assume the
color of medium to always be white and use this algorithm to render the
specified coverage.
4. The highlight color space can also specify indexed colors when used in [MODCA-6-376]
conjunction with a Color Mapping T able (CMT) or an Indexed (IX) Color
Management Resource (CMR). In that case, component 1 specifies a
two-byte value that is the index into the CMT or the IX CMR, and
components 2 and 3 are ignored. Note that when both a CMT and
Indexed CMRs are used, the CMT is always accessed first. T o preserve
compatibility with existing highlight color devices, indexed color values
X'0000' – X'00FF' are reserved for existing highlight color applications and
devices. That is, indexed colors values in the range X'0000' – X'00FF',
assuming they are not mapped to a different color space in a CMT , are
mapped directly to highlight colors. Indexed color values in the range
X'0100' – X'FFFF', assuming they are not mapped to a different color
space in a CMT , are used to access Indexed CMRs. For a description of
the CMT , see“The Color Mapping T able Resource”.
X'08' CIELAB color space. The color value is specified with three components.
Components 1, 2, and 3 are binary numbers that specify the L, a, b values, in
that order, where L is the luminance and a and b are the chrominance
differences. Component 1 specifies the L value as an unsigned binary
number; components 2 and 3 specify the a and b values as signed binary
numbers. ColSize1, ColSize2, and ColSize3 are non-zero and define the
number of bits used to specify each component. ColSize4 is reserved and
should be set to zero. The range for the L component is 0 to 100, which is
mapped to the binary value range 0 to (2
ColSize1 - 1). The range for the a and b
components is -127 to +127, which is mapped to the binary range -(2 ColSizeN–1
- 1) to +(2 ColSizeN–1 - 1). [MODCA-6-377]
For color fidelity, 8-bit encoding should be used for each component, that is,
ColSize1, ColSize2, and ColSize3 are set to X'08'. When the recommended
8-bit encoding is used for the a and b components, the range is extended to
include -128, which is mapped to the value X'80'. If the encoding is less than 8
bits, treatment of the most negative binary endpoint for the a and b
components is presentation-system dependent, and tends to be insignificant
because of the quantization error.
Architecture Note: The reference white point for CIELAB is known as D50
and is defined in CIE publication 15-2 entitled Colorimetry.
X'40' Standard OCA color space. The color value is specified with one component.
Component 1 is an unsigned binary number that specifies a named color
using a two-byte value from the Standard OCA Color Value T able. For a
complete description of the Standard OCA Color Value T able, see “Standard
OCA Color Value T able”. ColSize1 = X'10' and defines the
number of bits used to specify component 1. ColSize2, ColSize3, ColSize4
are reserved and should be set to zero. This is a presentation-system-
dependent color space.
All others Reserved
Triplet X'4E'


ColSize1 Defines the number of bits used to specify the first color component. The color component is
right-aligned and padded with zeros on the left to the nearest byte boundary. For example, if
ColSize1 = X'06', the first color component has two padding bits.
ColSize2 Defines the number of bits used to specify the second color component. The color component
is right-aligned and padded with zeros on the left to the nearest byte boundary.
ColSize3 Defines the number of bits used to specify the third color component. The color component is
right-aligned and padded with zeros on the left to the nearest byte boundary.
ColSize4 Defines the number of bits used to specify the fourth color component. The color component is
right-aligned and padded with zeros on the left to the nearest byte boundary.
Color Specifies the color value in the defined format and encoding. Note that the number of bytes
specified for this parameter depends on the color space. For example, when using 8 bits per
component, an RGB color value is specified with 3 bytes, while a CMYK color value is
specified with 4 bytes. If extra bytes are specified, they are ignored as long as the triplet length
is valid.
Architecture Note: For a description of color spaces and their relationships, see R. Hunt, The Reproduction of
Colour in Photography, Printing, and Television (Fifth Edition, Fountain Press, 1995). [MODCA-6-378]
### Structured Fields Using Triplet X'4E'
• “Container Data Descriptor (CDD)” [MODCA-6-379]
• “Include Object (IOB)” [MODCA-6-380]
• “Object Area Descriptor (OBD)” [MODCA-6-381]
• “Page Descriptor (PGD)” [MODCA-6-382]
• “Preprocess Presentation Object (PPO)” [MODCA-6-383]
Triplet X'4E'


### Encoding Scheme ID Triplet X'50'
The Encoding Scheme ID triplet is used to specify the encoding scheme associated with a code page. It may
optionally also specify the encoding scheme for the user data. [MODCA-6-384]
#### Triplet X'50' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-385]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-386]|
| 0 UBIN Tlength 4, 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-387]|
| 1 CODE Tid X'50' Identifies the Encoding Scheme | | | | | | ID triplet M X'00' [MODCA-6-388]|
| 2–3 CODE ESidCP See “Triplet X'50' | | | | | | Semantics” Encoding Scheme Identifier for Code Page M X'06' [MODCA-6-389]|
| 4–5 CODE ESidUD See “Triplet X'50' | | | | | | Semantics” Encoding Scheme Identifier for User Data O X'00' [MODCA-6-390]|
#### Triplet X'50' Semantics
Architecture Note: The encoding scheme defined in this triplet is based on the encoding scheme identifier
defined by the IBM Character Data Representation Architecture (CDRA). However, only those values
applicable to MO:DCA environments are exposed. The remainder of the values are reserved at this time.
Note also that the bit definitions for the ESidCP and ESidUD parameters are informational; the codes
defined in T able 24, T able 25, and T able 26 should be used as
the valid parameter values. See the Character Data Representation Architecture Reference and
Registry, SC09-2190, for detailed information on the encoding scheme identifier.
Tlength Contains the length of the triplet.
Tid Identifies the Encoding Scheme ID triplet.
ESidCP Specifies the encoding scheme used for a code page.
Note: See the appropriate structured field descriptions for definitions of the default code page
encoding if this triplet is omitted.
Bit Description
0–3 Basic Encoding Structure
X'0' Encoding structure not specified. Defaults to presentation
environment encoding structure.
X'2' IBM-PC Data; an extension of the ISO 646 (ASCII-based) 7-bit
encoding to an 8-bit encoding.
X'3' IBM-PC Display; an extension of the ISO 646 (ASCII-based) 7-bit
encoding to an 8-bit encoding.
Implementation Note: The IBM-PC Display encoding scheme is not
used in AFP FOCA fonts.
X'6' EBCDIC Presentation; all code points assigned to graphic
characters.
Triplet X'50'


X'7' UTF-16, including surrogates.
Architecture Note: The UTF-16 character encoding is defined in the
Unicode Standard, which is available from the Unicode
Consortium at:
www.unicode.org.
X'8' Unicode Presentation; a subset of UTF-16 that contains only 2-byte
code points that can be directly mapped to a single glyph. The byte
order is big endian.
Implementation Note: The Unicode Presentation encoding scheme
is only used in the AFP FOCA Unicode Migration fonts.
All others Reserved
4–7 Number of Bytes per Code Point
X'0' Reserved for use with zero value for the basic encoding structure
X'1' Fixed single-byte
X'2' Fixed double-byte
All others Reserved
8–15 Code Extension Method
X'00' No extensions are specified
ESidUD Specifies the encoding scheme for the user data that is to be rendered with the referenced
font.
Note: See the appropriate structured field descriptions for definitions of the default user data
encoding if this parameter in the X'50' triplet is omitted or if the complete X'50' triplet is
omitted.
Bit Description
0–3 Basic Encoding Structure
X'7' UTF-16, including surrogates. The byte order is big endian (UTF-
16BE).
All others Reserved
4–7 Number of Bytes per Code Point
X'2' Fixed double-byte
X'8' UTF-n variable number of bytes, self describing
All others Reserved
8–15 Code Extension Method
X'00' No extensions are specified
X'07' UTF-8 Universal Transformation Format
All others Reserved
Architecture Note: The UTF-16 character encoding is defined in the Unicode
Standard, which is available from the Unicode Consortium at:
www.unicode.org.
T able 24 and T able 25 list the complete ESidCP and ESidUD values that are
supported.
Triplet X'50'


Table 24. Supported ESidCP Values
ESidCP Definition
X'0000' ESidCP not specified; use presentation environment default encoding
X'0100' Presentation environment default SBCS encoding
X'0200' Presentation environment default DBCS encoding
X'2100' PC-Data SBCS (ASCII-based)
X'3100' PC-Display SBCS (ASCII-based)
X'6100' EBCDIC Presentation SBCS
X'6200' EBCDIC Presentation DBCS
X'7200' UTF-16, including surrogates
X'8200' Unicode Presentation; byte order is big endian
Table 25. Supported ESidUD Values
ESidUD Definition
X'7200' UTF-16, including surrogates; byte order is big endian (UTF-16BE)
X'7807' UTF-8
Application Note: When ESidUD does not match ESidCP , the presentation system may need to transform the
user data to match the encoding in the code page. Not all presentation systems support such transforms.
T o see which transforms are supported, consult your product documentation.
Architecture Note: The following additional ESidUD values are allowed in AFP Line Data when the X'50'
triplet is specified on the Begin Data Map (BDM) structured field in a Page Definition.
Table 26. Additional ESidUD Values in AFP Line Data
ESidUD Definition
X'2100' PC-Data SBCS (ASCII-based)
X'6100' EBCDIC Presentation SBCS [MODCA-6-391]
### Structured Fields Using Triplet X'50'
• “Map Coded Font (MCF) Format 2” [MODCA-6-392]
• “Map Data Resource (MDR)” [MODCA-6-393]
Triplet X'50'


### Medium Map Page Number Triplet X'56'
The Medium Map Page Number triplet is used to specify the sequence number of the page in the set of
sequential pages whose presentation is controlled by the most recently activated medium map. [MODCA-6-394]
#### Triplet X'56' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-395]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-396]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-397]|
| 1 CODE Tid X'56' Identifies the Medium Map Page | | | | | | Number triplet M X'00' [MODCA-6-398]|
| 2–5 UBIN PageNum X'00000001'– | | | | | | X'7FFFFFFF' Sequence Number of Page M X'06' [MODCA-6-399]|
#### Triplet X'56' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Medium Map Page Number triplet.
PageNum Specifies the sequence number of the page in the set of sequential pages whose presentation
is controlled by the active medium map. The first page in this set has sequence number 1. [MODCA-6-400]
### Structured Fields Using Triplet X'56'
• “Begin Named Page Group (BNG)” [MODCA-6-401]
• “Begin Page (BPG)” [MODCA-6-402]
• “Index Element (IEL)” [MODCA-6-403]
Triplet X'56'


### Object Byte Extent Triplet X'57'
The Object Byte Extent triplet is used to specify the number of bytes contained in an object. [MODCA-6-404]
#### Triplet X'57' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-405]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-406]|
| 0 UBIN Tlength 6, 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-407]|
| 1 CODE Tid X'57' Identifies the Object Byte Extent | | | | | | triplet M X'00' [MODCA-6-408]|
| 2–5 UBIN ByteExt X'00000000'– | | | | | | X'FFFFFFFF' Byte Extent of Object M X'06' [MODCA-6-409]|
| 6–9 UBIN BytExtHi X'00000000'– | | | | | | X'FFFFFFFF' Byte extent of object, high-order bytes O X'00' [MODCA-6-410]|
#### Triplet X'57' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Byte Extent triplet.
ByteExt Specifies the number of bytes contained in the object. The first byte of the Begin Object
structured field is counted as the first byte in the object, and the last byte in the End Object
structured field is counted as the last byte of the object. Objects that are bounded by Begin/
End structured fields have a minimum byte extent of X'00000010'. When this triplet is used to
specify the byte extent of object data that is not bounded by Begin/End structured fields, the
minimum byte extent is X'00000000'.
BytExtHi If specified, indicates that this triplet specifies the byte extent as an 8-byte parameter, where
ByteExt specifies the low-order 4 bytes and BytExtHi specifies the high-order 4 bytes. [MODCA-6-411]
### Structured Fields Using Triplet X'57'
• “Begin Object Container (BOC)” [MODCA-6-412]
• “Index Element (IEL)” [MODCA-6-413]
Triplet X'57'


### Object Structured Field Offset Triplet X'58'
The Object Structured Field Offset triplet is used to specify the structured field offset of an indexed object from
the beginning of the document. [MODCA-6-414]
#### Triplet X'58' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-415]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-416]|
| 0 UBIN Tlength 6, 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-417]|
| 1 CODE Tid X'58' Identifies the Object Structured | | | | | | Field Offset triplet M X'00' [MODCA-6-418]|
| 2–5 UBIN SFOff X'00000000'– | | | | | | X'FFFFFFFE' Structured field offset M X'06' X'FFFFFFFF' If bytes 6–9 are not specified, object is outside document [MODCA-6-419]|
| 6–9 UBIN SFOffHi X'00000000'– | | | | | | X'FFFFFFFF' Structured field offset, high-order bytes O X'00' [MODCA-6-420]|
#### Triplet X'58' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Structured Field Offset triplet.
SFOff Specifies the offset, in structured fields, of the Begin structured field of an indexed object from
the beginning of the document. The first structured field in the document, which is the Begin
Document (BDT) structured field, has an offset of 0. The second structured field, which
immediately follows the BDT , has an offset of 1, and the nth structured field in the document
has an offset of (n-1). The structured field offset has a range of X'00000000' to X'FFFFFFFE'.
A value of X'FFFFFFFF' signifies that the indexed object is outside the document.
SFOffHi If specified, indicates that this triplet specifies the structured field offset as an 8-byte
parameter, where SFOff specifies the low-order 4 bytes and SFOffHi specifies the high-order 4
bytes. In that case, the value SFOff = X'FFFFFFFF' is a real offset value and does not signify
that the indexed object is outside the document. [MODCA-6-421]
### Structured Field Using Triplet X'58'
• “Index Element (IEL)” [MODCA-6-422]
Triplet X'58'


### Object Structured Field Extent Triplet X'59'
The Object Structured Field Extent triplet is used to specify the number of structured fields contained in an
object, starting with the Begin Object structured field and ending with the End Object structured field. [MODCA-6-423]
#### Triplet X'59' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-424]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-425]|
| 0 UBIN Tlength 6, 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-426]|
| 1 CODE Tid X'59' Identifies the Object Structured | | | | | | Field Extent triplet M X'00' [MODCA-6-427]|
| 2–5 UBIN SFExt X'00000002'– | | | | | | X'FFFFFFFF' Number of structured fields in Object M X'06' [MODCA-6-428]|
| 6–9 UBIN SFExtHi X'00000000'– | | | | | | X'FFFFFFFF' Number of structured fields in object, high-order bytes O X'00' [MODCA-6-429]|
#### Triplet X'59' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Structured Field Extent triplet.
SFExt Specifies the number of structured fields contained in the object. The Begin Object structured
field is counted as the first structured field in the object, and the End Object structured field is
counted as the last structured field of the object.
SFExtHi If specified, indicates that this triplet specifies the structured field extent as an 8-byte
parameter, where SFExt specifies the low-order 4 bytes and SFExtHi specifies the high-order
4 bytes. [MODCA-6-430]
### Structured Field Using Triplet X'59'
• “Index Element (IEL)” [MODCA-6-431]
Triplet X'59'


### Object Offset Triplet X'5A'
The Object Offset triplet specifies the number of objects of a particular type that precede a selected object in
the document. If the object being counted is a document, this triplet specifies the number of documents that
precede the selected object in the print file. [MODCA-6-432]
#### Triplet X'5A' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-433]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-434]|
| 0 UBIN Tlength 8, 12 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-435]|
| 1 | CODE | Tid | | X'5A' Identifies the Object Offset triplet | M | X'00' [MODCA-6-436]|
| 2 CODE ObjTpe X'A8', X'AF' Object type to be counted: | | | | | | X'A8' Document X'AF' Page or paginated object M X'06' [MODCA-6-437]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-438]|
| 4–7 UBIN ObjOset X'00000000'– | | | | | | X'FFFFFFFF' Number of objects that precede the selected object in the document or print file M X'06' [MODCA-6-439]|
| 8–11 UBIN ObjOstHi X'00000000'– | | | | | | X'FFFFFFFF' Number of objects that precede the selected object, high-order bytes O X'00' [MODCA-6-440]|
#### Triplet X'5A' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Offset triplet.
ObjTpe Specifies the object type to be counted. An object may occur at multiple levels. For instance, a
page object may occur directly in a document, which would be considered a first-level
occurrence of the page object, or it may occur in a page group in the document, which would
be considered a second-level occurrence of the page object, and so on.
Value Description
X'A8' The object is a document. The ObjOset and optional ObjOstHi parameters
specify the number of documents that precede the selected object in the print
file.
X'AF' The object is a page or paginated object. The ObjOset and optional ObjOstHi
parameters specify the number of pages or paginated objects that precede
the selected object in the document or file.
Note: If a page is included with an Include Page (IPG) structured field in
document state or page-group state, it is counted as a page object. If
the IPG occurs in page state, the included page becomes part of the
containing page, therefore only the containing page is counted as a
page object.
Architecture Note: A paginated object is a data object that can be rendered
on a single page and that can be treated as a single page. An example
of a paginated object is a single image in a multi-image TIFF file. Note
Triplet X'5A'


that in TIFF files, image-like structures such as thumbnails and image
masks are considered to be a part of the paginated image object but
are not themselves considered paginated objects. Another example is
a single page object in a PDF file. Such a page object is selected for
presentation by its page number; other identifiers such as object
numbers in the PDF file are not used for selection.
Implementation Note: The ordering of paginated image objects in a TIFF file
may be defined explicitly with page numbers, or implicitly based on the
position of the image object in the file. The page offset specified by this
triplet can be applied to either ordering, but the explicit page numbering,
if specified, always has higher priority.
All others Reserved
ObjOset Specifies the number of objects, whose type is identified by ObjTpe, that precede the selected
object. Only complete objects, that is, objects bounded by a Begin and an End, are counted.
For example, if this triplet occurs on the BNG of a nested page group Gn, the page group
containing Gn is not counted since its End structured field does not precede Gn. For a given
object type being counted, the offset to the nth occurrence of that object type is (n-1). For
example, if pages are being counted, the page offset of the first page in the document is 0, the
page offset of the second page is 1, and the page offset of the nth page is (n-1). A page
included with an IPG is also counted, but only when the IPG occurs in document state or
page-group state, not when it occurs in page state. Unless otherwise specified, all complete
object occurrences at all levels are counted.
ObjOstHi If specified, indicates that this triplet specifies the number of preceding objects as an 8-byte
parameter, where ObjOset specifies the low-order 4 bytes and ObjOstHi specifies the high-
order 4 bytes.
### Structured Fields Using Triplet X'5A'
• “Container Data Descriptor (CDD)” [MODCA-6-441]
• “Index Element (IEL)” [MODCA-6-442]
• “Include Object (IOB)” [MODCA-6-443]
• “Include Page (IPG)” [MODCA-6-444]
• “Map Data Resource (MDR)” [MODCA-6-445]
• “Medium Finishing Control (MFC)” [MODCA-6-446]
• “Map Page (MPG)” [MODCA-6-447]
• “Presentation Environment Control (PEC)” [MODCA-6-448]
• “Preprocess Presentation Object (PPO)” [MODCA-6-449]
Triplet X'5A'


### Font Horizontal Scale Factor Triplet X'5D'
The Font Horizontal Scale Factor triplet is used to carry information to support anamorphic scaling of an outline
technology font.
#### Triplet X'5D' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-450]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-451]|
| 0 UBIN Tlength 4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-452]|
| 1 CODE Tid X'5D' Identifies the Font Horizontal | | | | | | Scale Factor triplet M X'00' [MODCA-6-453]|
| 2–3 UBIN Hscale 1–32,767 Specifies the horizontal scale | | | | | | factor in 1440ths of an inch (20ths of a point) M X'06' [MODCA-6-454]|
#### Triplet X'5D' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Font Horizontal Scale Factor triplet.
Hscale Specifies the horizontal scale factor that is to be applied to the horizontal font dimension when
scaling an outline technology font. This scale factor is specified in 1440ths of an inch (20ths of
a point). If the font horizontal scale factor is the same as the specified vertical font size, the
font scaling is uniform. If the font horizontal scale factor is not the same as the specified
vertical font size, the font scaling is anamorphic, and the graphic characters are stretched or
compressed in the horizontal direction relative to the vertical direction by the ratio of font
horizontal scale factor divided by the specified vertical font size. [MODCA-6-455]
### Structured Field Using Triplet X'5D'
• “Map Coded Font (MCF) Format 2” [MODCA-6-456]
Triplet X'5D'


### Object Count Triplet X'5E'
The Object Count triplet specifies the number of subordinate objects of a particular type contained in an object. [MODCA-6-457]
#### Triplet X'5E' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-458]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-459]|
| 0 UBIN Tlength 8, 12 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-460]|
| 1 | CODE | Tid | | X'5E' Identifies the Object Count triplet | M | X'00' [MODCA-6-461]|
| 2 CODE SubObj X'AF' Subordinate object type: | | | | | | X'AF' Page M X'04' [MODCA-6-462]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-463]|
| 4–7 UBIN SObjNum X'00000000'– | | | | | | X'FFFFFFFF' Number of subordinate objects contained in this object M X'06' [MODCA-6-464]|
| 8–11 UBIN SObjNmHi X'00000000'– | | | | | | X'FFFFFFFF' Number of subordinate objects, high-order bytes O X'00' [MODCA-6-465]|
#### Triplet X'5E' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Count triplet.
SubObj Specifies the subordinate object type. A subordinate object may occur at multiple levels within
an object. For instance, a page object may occur directly in a page group, which would be
considered a first-level occurrence of the subordinate object, or it may occur in a page group
that is nested in the first page group, which would be considered a second-level occurrence of
the subordinate object, and so on.
Value Description
X'AF' The subordinate object is a page. The SObjNum and optional SObjNmHi
parameters specify the number of pages contained in the object.
Note: If a page is included with an Include Page (IPG) structured field in
document state or page group state, it is counted as a page object. If
the IPG occurs in page state, the included page becomes part of the
containing page, therefore only the containing page is counted as a
page object.
All others Reserved
SObjNum Specifies the number of subordinate objects, whose type is identified by SubObj, that are
contained in this object. Unless otherwise specified, all subordinate-object occurrences at all
levels are counted.
SObjNmHi If specified, indicates that this triplet specifies the count of subordinate objects as an 8-byte
parameter, where SObjNum specifies the low-order 4 bytes and SObjNmHi specifies the high-
order 4 bytes.
### Structured Fields Using Triplet X'5E'
• “Begin Named Page Group (BNG)” [MODCA-6-466]
Triplet X'5E'


• “Begin Print File (BPF)” [MODCA-6-467]
• “Index Element (IEL)” [MODCA-6-468]
Triplet X'5E'


### Local Date and Time Stamp Triplet X'62'
The Local Date and Time Stamp triplet specifies a date and time stamp to be associated with an object. [MODCA-6-469]
#### Triplet X'62' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-470]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-471]|
| 0 UBIN Tlength 17 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-472]|
| 1 CODE Tid X'62' Identifies the Local Date and | | | | | | Time Stamp triplet M X'00' [MODCA-6-473]|
| 2 CODE StampType X'00'–X'01', X'03' Specifies the date and time | | | | | | stamp type: X'00' Creation X'01' Retired value X'03' Revision M X'06' [MODCA-6-474]|
| 3 CODE THunYear X'40', X'F0'–X'F9' Hundreds position and implied | | | | | | thousands position of year AD: X'40' 19xx X'F0'– X'F9' 20xx–29xx M X'06' [MODCA-6-475]|
| 4–5 CODE T enYear X'F0F0'–X'F9F9' T ens and units position of year | | | | | | AD M X'06' [MODCA-6-476]|
| 6–8 CODE Day X'F0F0F1'– | | | | | | X'F3F6F6' Day of year M X'06' [MODCA-6-477]|
| 9–10 | CODE | Hour | | X'F0F0'–X'F2F3' Hour of day | M | X'06' [MODCA-6-478]|
| 11–12 | CODE | Minute | | X'F0F0'–X'F5F9' Minute of hour | M | X'06' [MODCA-6-479]|
| 13–14 | CODE | Second | | X'F0F0'–X'F5F9' Second of minute | M | X'06' [MODCA-6-480]|
| 15–16 | CODE | HundSec | | X'F0F0'–X'F9F9' Hundredth of second | M | X'06' [MODCA-6-481]|
#### Triplet X'62' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Local Date and Time Stamp triplet.
StampType Specifies the type of date and time stamp.
Value Description
X'00' Object creation date and time stamp
X'01' Retired date and time stamp type. See “Retired Parameters”.
X'03' Object revision date and time stamp
All others Reserved
THunYear Implies the thousands position (the millennium) of the year AD and specifies the hundreds
position, using the Gregorian calendar. The 20xxs are encoded as X'F0', the 21xxs as X'F1',
the 22xxs as X'F2', and so on. T o differentiate the 19xxs (9xxs in the second millennium AD)
from the 29xxs (9xxs in the third millennium AD), the 19xxs are encoded as X'40'. This
parameter therefore generates the CC component of a date in the format CCYYDDD as
defined in ISO 8601:1988(E), Data elements and interchange formats—Information
Interchange—Representation of dates and times.
Triplet X'62'


TenYear Specifies the tens position and the units position of the year AD, using the Gregorian calendar.
Forms the YY component of a date in the format CCYYDDD.
This parameter, together with the ThunYear parameter, specifies the year AD. For example,
the year 1999 AD is encoded as X'40F9F9', the year 2000 AD is encoded as X'F0F0F0', and
the year 2001 AD is encoded as X'F0F0F1'.
Day Specifies the day of the year, using the Gregorian calendar. Forms the DDD component of a
date in the format CCYYDDD.
As an example, the date February 1, 1972 is restructured as “72032” and encoded as
X'40F7F2F0F3F2', the date December 31, 1999 is restructured as “99365” and encoded as
X'40F9F9F3F6F5', the date January 1, 2000 is restructured as “000001” and encoded as
X'F0F0F0F0F0F1', and the date February 3, 2072 is restructured as “072034” and encoded as
X'F0F7F2F0F3F4'.
Hour Specifies the hour of the day. Forms the HH component of a timestamp in the format
HHMMSShh.
Minute Specifies the minute of the hour. Forms the MM component of a timestamp in the format
HHMMSShh.
Second Specifies the second of the minute. Forms the SS component of a timestamp in the format
HHMMSShh.
HundSec Specifies hundredth of a second. Forms the hh component of a timestamp in the format
HHMMSShh.
As an example, the time 4:35:21.56 PM is encoded as X'F1F6F3F5F2F1F5F6'.
Architecture Notes:
1. This triplet specifies an EBCDIC encoding for numbers used to record date and time. This encoding [MODCA-6-482]
represents a number in the range 0–9 with a code point X'Fn', where n is the number. [MODCA-6-483]
### Structured Fields Using Triplet X'62'
Either this triplet or the Universal Date and Time Stamp (X'72') triplet may occur once.
• “Begin Bar Code Object (BBC)” [MODCA-6-484]
• “Begin Document Index (BDI)” [MODCA-6-485]
• “Begin Form Map (BFM)” [MODCA-6-486]
• “Begin Graphics Object (BGR)” [MODCA-6-487]
• “Begin Image Object (BIM)” [MODCA-6-488]
• “Begin Overlay (BMO)” [MODCA-6-489]
• “Begin Object Container (BOC)” [MODCA-6-490]
• “Begin Page Segment (BPS)” [MODCA-6-491]
• “Begin Presentation T ext Object (BPT)” [MODCA-6-492]
• “Begin Resource Group (BRG)” [MODCA-6-493]
Triplet X'62'


### Comment Triplet X'65'
The Comment triplet is used to include comments for documentation purposes within a structured field. [MODCA-6-494]
#### Triplet X'65' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-495]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-496]|
| 0 UBIN Tlength 3–254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-497]|
| 1 | CODE | Tid | | X'65' Identifies the Comment triplet | M | X'00' [MODCA-6-498]|
| 2–n | CHAR | Comment | | T ext of the comment | M | X'06' [MODCA-6-499]|
#### Triplet X'65' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Comment triplet.
Comment Is a character string which has meaning only to the generator of this MO:DCA document.
There can be no semantics associated with this character string. Therefore, the content of the
triplet may be ignored by receivers of the MO:DCA document. [MODCA-6-500]
### Structured Fields Using Triplet X'65'
• “Begin Active Environment Group (BAG)” [MODCA-6-501]
• “Begin Bar Code Object (BBC)” [MODCA-6-502]
• “Begin Document Environment Group (BDG)” [MODCA-6-503]
• “Begin Document Index (BDI)” [MODCA-6-504]
• “Begin Document (BDT)” [MODCA-6-505]
• “Begin Form Map (BFM)” [MODCA-6-506]
• “Begin Graphics Object (BGR)” [MODCA-6-507]
• “Begin Image Object (BIM)” [MODCA-6-508]
• “Begin Medium Map (BMM)” [MODCA-6-509]
• “Begin Overlay (BMO)” [MODCA-6-510]
• “Begin Named Page Group (BNG)” [MODCA-6-511]
• “Begin Object Container (BOC)” [MODCA-6-512]
• “Begin Object Environment Group (BOG)” [MODCA-6-513]
• “Begin Print File (BPF)” [MODCA-6-514]
• “Begin Page (BPG)” [MODCA-6-515]
• “Begin Page Segment (BPS)” [MODCA-6-516]
• “Begin Presentation T ext Object (BPT)” [MODCA-6-517]
• “Begin Resource (BRS)” [MODCA-6-518]
• “Begin Resource Group (BRG)” [MODCA-6-519]
• “Begin Resource Environment Group (BSG)” [MODCA-6-520]
Triplet X'65'


### Medium Orientation Triplet X'68'
The Medium Orientation triplet may be used to specify the orientation of the medium presentation space on the
physical medium.
#### Triplet X'68' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-521]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-522]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-523]|
| 1 CODE Tid X'68' Identifies the Medium Orientation | | | | | | triplet M X'00' [MODCA-6-524]|
| 2 CODE MedOrient X'00'–X'05' Orientation of the medium | | | | | | presentation space: X'00' Portrait X'01' Landscape X'02' Reverse Portrait X'03' Reverse Landscape X'04' Portrait 90 X'05' Landscape 90 M X'06' [MODCA-6-525]|
#### Triplet X'68' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Medium Orientation triplet.
MedOrient Specifies the position and orientation of the medium presentation space on the physical
medium.
Value Description
X'00' Portrait. The origin of the medium presentation space is positioned such that
the top of the presentation space (X m axis) is parallel to a short side of the
physical medium as shown in the Portrait column of Figure 73.
X'01' Landscape. The origin of the medium presentation space is positioned such
that the top of the presentation space (X m axis) is parallel to a long side of the
physical medium as shown in the Landscape column of Figure 73.
X'02' Reverse Portrait. The origin of the medium presentation space is positioned
such that the top of the presentation space (X m axis) is parallel to a short side
of the physical medium as shown in the Reverse Portrait column of Figure 73
.
X'03' Reverse Landscape. The origin of the medium presentation space is
positioned such that the top of the presentation space (X m axis) is parallel to a
long side of the physical medium as shown in the Reverse Landscape column
of Figure 73.
X'04' Portrait 90. The origin of the medium presentation space is positioned such
that the top of the presentation space (X m axis) is parallel to a long side of the
physical medium as shown in the Portrait 90 column of Figure 73.
Triplet X'68'


X'05' Landscape 90. The origin of the medium presentation space is positioned
such that the top of the presentation space (X m axis) is parallel to a short side
of the physical medium as shown in the Landscape 90 column of Figure 73 on
page 411.
Figure 73. Landscape and Portrait Orientation and Layout
Note: In Figure 73, the text “AFP”, “Page 1”, and “Page 2” is printed in the 0° text orientation for
the Portrait, Landscape, Reverse Portrait, and Reverse Landscape medium orientations, and in the 90°
text orientation for the Portrait 90 and Landscape 90 medium orientations.
See Figure 61 to Figure 72 for a complete description of medium orientations
with N-up presentation. [MODCA-6-526]
### Structured Field Using Triplet X'68'
• “Medium Descriptor (MDD)” [MODCA-6-527]
Triplet X'68'


### Resource Object Include Triplet X'6C'
The Resource Object Include triplet identifies an object to be included on a presentation space at a specified
position.
#### Triplet X'6C' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-528]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-529]|
| 0 UBIN Tlength 17, 19 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-530]|
| 1 CODE Tid X'6C' Identifies the Resource Object | | | | | | Include triplet M X'00' [MODCA-6-531]|
| 2 CODE ObjType X'DC', X'DF', | | | | | | X'5F' Specifies the object type: X'DC' Preprinted Form Overlay (PFO) object X'DF' Overlay object X'5F' Retired for private use M X'06' [MODCA-6-532]|
| 3–10 | CHAR | ObjName | | Name of the object | M | X'06' [MODCA-6-533]|
| 11–13 | SBIN | XobjOset | | -32,768 – 32,767 X axis origin for the object | M | X'06' [MODCA-6-534]|
| 14–16 | SBIN | YobjOset | | -32,768 – 32,767 Y axis origin for the object | M | X'06' [MODCA-6-535]|
| 17–18 CODE ObOrent X'0000', X'2D00', | | | | | | X'5A00', X'8700' The overlay's X-axis rotation from the X axis of the including presentation system X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees O X'00' [MODCA-6-536]|
#### Triplet X'6C' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Resource Object Include triplet.
ObjType Specifies the object type.
Value Description
X'DC' Preprinted Form Overlay (PFO) object
X'DF' Overlay object
X'5F' Retired for private use
All others Reserved
ObjName Specifies the object name.
XobjOset Specifies the offset along the X axis of the including presentation space coordinate system to
the origin of the X axis for the object.
YobjOset Specifies the offset along the Y axis of the including presentation space coordinate system to
the origin of the Y axis for the object.
ObOrent This is an optional parameter that is only supported for ObjType X'DF' = Overlay object; the
parameter is ignored for other object types. Specifies the amount of rotation of the overlay's X
axis, X
ol, about the overlay origin relative to the X axis of the including presentation space.
Triplet X'6C'


Note that if this triplet is specified on a Page Modification Control (PMC) structured field, the
including presentation space is a page, and the rotation is measured with respect to the X p
axis of the page coordinate system. Valid values are the following:
Value Character Rotation
X'0000' 0 degrees
X'2D00' 90 degrees
X'5A00' 180 degrees
X'8700' 270 degrees
All others Reserved
The overlay Y axis rotation is always 90 degrees greater than the overlay X axis rotation.
Note: If this parameter is omitted, the architected default value for the overlay rotation is
X'0000', zero degrees.
Architecture Note: This triplet is used in AFP line-data environments on an LND structured field in a Page
Definition object to position overlays (ObjType = X'DF') and page segments (ObjType = X'5F') with
respect to line data. For a description of the Page Definition object and the processing of line data in
AFP environments, see the Advanced Function Presentation: Programming Guide and Line Data
Reference.
### Structured Field Using Triplet X'6C'
• “Page Modification Control (PMC)” [MODCA-6-537]
Triplet X'6C'


### Presentation Space Reset Mixing Triplet X'70'
This triplet is used to specify the resulting appearance when data in a new presentation space is merged with
data in an existing presentation space. [MODCA-6-538]
#### Triplet X'70' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-539]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-540]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-541]|
| 1 CODE Tid X'70' Identifies the Presentation Space | | | | | | Reset Mixing triplet M X'00' [MODCA-6-542]|
| 2 BITS BgMxFlag See Triplet X'70' | | | | | | Semantics for details. Background mixing flags M X'04' [MODCA-6-543]|
#### Triplet X'70' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Presentation Space Reset Mixing triplet.
BgMxFlag Specifies the type of presentation space mixing as follows:
Bit Description
0 Reset Flag [MODCA-6-544]
B'0' Do not reset to the color of the medium prior to placing data into this MO:DCA
presentation space. This results in the new presentation space mixing with
the existing presentation space in accordance with the default MO:DCA
mixing rule. Specifically, the background of the new presentation space
underpaints both the background and the foreground of the existing
presentation space, and the foreground of the new presentation space
overpaints the background and the foreground of the existing presentation
space.
B'1' Reset to the color of the medium prior to placing data into this MO:DCA
presentation space. The presentation space becomes foreground data that is
colored with the color of medium before any data is placed into this space.
This results in the new presentation space mixing with the existing
presentation space in an opaque manner. Specifically, the new presentation
space, which is all foreground data, overpaints the background and
foreground of the existing presentation space.
All
others
Reserved
Note: If this triplet is omitted, the architected default value for the Reset Flag is B'0'—do not reset to color of
medium.
### Structured Fields Using Triplet X'70'
• “Include Object (IOB)” [MODCA-6-545]
• “Object Area Descriptor (OBD)” [MODCA-6-546]
Triplet X'70'


• “Page Descriptor (PGD)” [MODCA-6-547]
Triplet X'70'


### Presentation Space Mixing Rules Triplet X'71'
This triplet is used to specify the rules for establishing the color attribute of areas formed by the intersection of
two presentation spaces. It is specified on structured fields associated with a presentation space that is to be
merged onto an existing presentation space. [MODCA-6-548]
#### Triplet X'71' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-549]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-550]|
| 0 UBIN Tlength 4–10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-551]|
| 1 CODE Tid X'71' Identifies the Presentation Space | | | | | | Mixing Rules triplet M X'00' [MODCA-6-552]|
| 2–n CODE One or more occurrences of the keywords in the following table, in ascending order | | | | | | Keyword ID Parameter Range Meaning M/O Exc X'70' X'01'–X'03', X'FF' Mixing rule for background-on- background mixing O X'02' X'71' X'01'–X'03', X'FF' Mixing rule for background-on- foreground mixing O X'02' X'72' X'01'–X'03', X'FF' Mixing rule for foreground-on- background mixing O X'02' X'73' X'01'–X'03', X'FF' Mixing rule for foreground-on- foreground mixing O X'02' [MODCA-6-553]|
#### Triplet X'71' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Presentation Space Mixing Rules triplet.
Keywords One or more keywords that specify the rules for presentation space mixing. Each keyword
may appear once and specifies one of the four mixing types along with the mixing rule for that
mixing type. In the definitions that follow, the existing presentation space is identified by the
subscript e, the new presentation space that is merged with the existing presentation space
and that contains the Presentation Space Mixing Rules triplet is identified by the subscript n,
the letter “B” stands for “Background”, and the letter “F” stands for “Foreground”. The
Presentation Space Mixing Rules triplet appears on structures associated with the new
presentation space. T o completely specify the mixing of two presentation spaces, this triplet
must contain four mixing rule keywords, one for each mixing type. If no keyword is specified
for a particular mixing type, the MO:DCA default mixing rule is applied to this mixing type.
Keyword X'70nn' May occur once. Specifies the mixing rule for B
n on Be
(background on background) mixing.
Keyword X'71nn' May occur once. Specifies the mixing rule for B n on Fe
(background on foreground) mixing.
Keyword X'72nn' May occur once. Specifies the mixing rule for F n on Be
(foreground on background) mixing.
Keyword X'73nn' May occur once. Specifies the mixing rule for F n on Fe
(foreground on foreground) mixing.
Triplet X'71'


The following mixing rule specifications are supported in the data bytes for keywords X'70'–X'73'. For a
definition of these mixing rules, see “Mixing Rules”.
Value Definition
X'01' Overpaint
X'02' Underpaint
X'03' Blend
X'FF' MO:DCA default mixing rule
All others Reserved
Note: If this triplet is not supported by a receiver, the architected default is to use the default mixing rule when
mixing the new presentation space with the existing presentation space.
Implementation Note: The Presentation Space Mixing Rules (X'71') triplet is currently not used in AFP
environments.
### Structured Fields Using Triplet X'71'
• “Include Object (IOB)” [MODCA-6-554]
• “Object Area Descriptor (OBD)” [MODCA-6-555]
• “Page Descriptor (PGD)” [MODCA-6-556]
Triplet X'71'


### Universal Date and Time Stamp Triplet X'72'
The Universal Date and Time Stamp triplet specifies a date and time in accordance with the format defined in
ISO 8601:1988 (E).
#### Triplet X'72' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-557]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-558]|
| 0 UBIN Tlength 13 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-559]|
| 1 CODE Tid X'72' Identifies the Universal Date and | | | | | | Time Stamp triplet M X'00' [MODCA-6-560]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-561]|
| 3–4 UBIN YearAD 0–65,535 Year AD using Gregorian | | | | | | calendar M X'06' [MODCA-6-562]|
| 5 | UBIN | Month | | 1–12 Month of the year | M | X'06' [MODCA-6-563]|
| 6 | UBIN | Day | | 1–31 Day of the month | M | X'06' [MODCA-6-564]|
| 7 | UBIN | Hour | | 0–23 Hour of the day in 24-hour format | M | X'06' [MODCA-6-565]|
| 8 | UBIN | Minute | | 0–59 Minute of the hour | M | X'06' [MODCA-6-566]|
| 9 | UBIN | Second | | 0–59 Second of the minute | M | X'06' [MODCA-6-567]|
| 10 CODE TimeZone X'00'–X'02' Relationship of time to UTC: | | | | | | X'00' Coordinated Universal [MODCA-6-568]|
### Time (UTC)
X'01' Ahead of UTC
X'02' Behind UTC
M X'06'
11 UBIN UTCDiffH 0–23 Hours ahead of or behind UTC M X'06' [MODCA-6-569]
12 UBIN UTCDiffM 0–59 Minutes ahead of or behind UTC M X'06' [MODCA-6-570]
#### Triplet X'72' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Universal Date and Time Stamp triplet.
YearAD Specifies the year AD using the Gregorian calendar. For example, the year 1999 is specified
as X'07CF', the year 2000 as X'07D0', and the year 2001 asX'07D1'. Represents the YYYY
component of a date in the format YYYYMMDD.
Month Specifies the month of the year. January is specified as X'01', and subsequent months are
numbered in ascending order. Represents the MM component of a date in the format
YYYYMMDD.
Day Specifies the day of the month. The first day of any month is specified as X'01', and
subsequent days are numbered in ascending order. Represents the DD component of a date
in the format YYYYMMDD. For example, the date December 31, 1999 is specified as
X'07CF0C1F', and January 1, 2000 is specified as X'07D00101'.
Hour Specifies the hour of the day in 24-hour format. Represents the hh component of a time in the
format hhmmss.
Triplet X'72'


Minute Specifies the minute of the hour. Represents the mm component of a time in the format
hhmmss.
Second Specifies the second of the minute. Represents the ss component of a time in the format
hhmmss. For example, the time 4:35:21 PM is specified as X'102315'.
TimeZone Defines the relation of the specified time with respect to Coordinated Universal Time (UTC).
This parameter, along with the UTCDiffH and UTCDiffM parameters, is used to accommodate
differences between a specified local time and UTC because of time zones and daylight
savings programs. For example, Mountain Time in the US is seven hours behind UTC when
daylight savings is inactive, and six hours behind UTC when daylight savings is active.
Value Description
X'00' Time is specified in Coordinated Universal Time (UTC). With this value, the
UTCDiffH and UTCDiffM parameters should be set to X'00'. When this time is
displayed or printed, the equivalence with UTC time is normally indicated with
a Z suffix, that is, hhmmssZ.
X'01' Specified time is ahead of UTC. The number of hours ahead of UTC is
specified by the UTCDiffH parameter; and the number of minutes ahead of
UTC is specified by the UTCDiffM parameter. When this time is displayed or
printed, the relationship with UTC time is normally indicated with a +
character, followed by the actual time difference in hours and minutes, that is
hhmmss+hhmm.
X'02' Specified time is behind UTC. The number of hours behind UTC is specified
by the UTCDiffH parameter; and the number of minutes behind UTC is
specified by the UTCDiffM parameter. When this time is displayed or printed,
the relationship with UTC time is normally indicated with a - character,
followed by the actual time difference in hours and minutes, that is hhmmss-
hhmm.
All others Reserved
UTCDiffH Indicates how many hours the specified time is ahead of UTC or behind UTC. If the TimeZone
parameter is X'00', this value is ignored.
UTCDiffM Indicates how many minutes the specified time is ahead of UTC or behind UTC. If the
TimeZone parameter is X'00', this value is ignored. [MODCA-6-571]
### Structured Fields Using Triplet X'72'
Either this triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Only the Universal Date and
Time Stamp (X'72') triplet is allowed on the BDT .
• “Begin Bar Code Object (BBC)” [MODCA-6-572]
• “Begin Document Index (BDI)” [MODCA-6-573]
• “Begin Document (BDT)” [MODCA-6-574]
• “Begin Form Map (BFM)” [MODCA-6-575]
• “Begin Graphics Object (BGR)” [MODCA-6-576]
• “Begin Image Object (BIM)” [MODCA-6-577]
• “Begin Overlay (BMO)” [MODCA-6-578]
• “Begin Object Container (BOC)” [MODCA-6-579]
• “Begin Print File (BPF)” [MODCA-6-580]
• “Begin Page Segment (BPS)” [MODCA-6-581]
• “Begin Presentation T ext Object (BPT)” [MODCA-6-582]
• “Begin Resource Group (BRG)” [MODCA-6-583]
Triplet X'72'


### Toner Saver Triplet X'74'
The T oner Saver triplet activates a toner saver mode for printing. The toner saver control specified by this
triplet overrides any other toner saver controls that may be active in the printer. [MODCA-6-584]
#### Triplet X'74' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-585]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-586]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-587]|
| 1 | CODE | Tid | | X'74' Identifies the T oner Saver triplet | M | X'00' [MODCA-6-588]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-589]|
| 3 CODE TSvCtrl X'00'–X'01', X'FF' Specifies controls for the toner | | | | | | saver function: X'00' Deactivate toner saver X'01' Activate toner saver X'FF' Use device default toner saver setting M X'06' [MODCA-6-590]|
| 4–5 | Reserved; | should | | be zero | M | X'06' [MODCA-6-591]|
#### Triplet X'74' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the T oner Saver triplet.
TSvCtrl Specifies how the toner saver function is to be applied to data in the presentation device. Valid
values are the following:
Value Description
X'00' Deactivate the toner saver function.
X'01' Activate the toner saver function. T oner saver is applied to presentation data in a
presentation-system-dependent manner. In general, this may degrade print quality,
and may also impact performance.
X'FF' Use the printer default toner saver setting. Some printers allow a default for toner
saving (activate or deactivate) to be set by the operator at the printer console.
If this triplet is not specified, the architected default is TSvCtrl = X'FF' (use the device default toner saver
setting).
Application Note: T oner Saver for color printers is a function that is based on the principle that equal amounts
of cyan, magenta, and yellow generate a monochromatic gray level. This leads to procedures where,
given a CMY color that has some percentage of equal amounts of CMY , a percentage of CMY toner is
removed (“undercolor removal”) and replaced with a percentage of K (“gray replacement”). In practice,
such procedures may result in poorer color quality and may incur a performance hit. [MODCA-6-592]
### Structured Field Using Triplet X'74'
• “Presentation Fidelity Control (PFC)” [MODCA-6-593]
Triplet X'74'


### Color Fidelity Triplet X'75'
The Color Fidelity triplet is used to specify the exception continuation and reporting rules for color exceptions,
which consist of the following types:
• Invalid or unsupported color-value exceptions. A color-value exception is detected when the color [MODCA-6-594]
specification in the data stream cannot be rendered as specified by the presentation process.
• Color Management Resource (CMR) exceptions. This does not include unsupported CMR tag exceptions, [MODCA-6-595]
which are covered separately by the CMR T ag Fidelity (X'96') triplet. A CMR exception is detected when a
CMR that has been referenced in the data stream (which includes FormDefs and Medium Maps) or a data
object RAT cannot be processed as specified. This does not include CMRs that are mapped to referenced
CMRs but that are themselves not directly referenced in the data stream or a data object RAT :
– Link LK CMRs that are mapped to color conversion CMRs in a CMR RAT or on the BRS of an inline CMR
– Device-specific halftone and tone transfer curve CMRs that are mapped to generic CMRs in a CMR RAT or
on the BRS of an inline CMR
The processing of such mapped CMRs is not governed by the Color Fidelity triplet; if a device does not
support the download of such a mapped CMR, it does not cause a CMR exception and the mapped CMR is
ignored.
• Device Appearance exceptions. A Device Appearance exception is detected when a requested appearance [MODCA-6-596]
is not supported by the presentation device.
This triplet also specifies a substitution rule to be used by the presentation process when continuing after such
exceptions.
#### Triplet X'75' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-597]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-598]|
| 0 UBIN Tlength 8 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-599]|
| 1 | CODE | Tid | | X'75' Identifies the Color Fidelity triplet | M | X'00' [MODCA-6-600]|
| 2 CODE StpCoEx X'01'–X'02' Color exception continuation | | | | | | rule: X'01' Stop presentation at point of first color exception and report exception X'02' Do not stop presentation because of color exceptions M X'06' [MODCA-6-601]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-602]|
| 4 CODE RepCoEx X'01'–X'02' Color exception reporting rule if | | | | | | exception does not stop presentation: X'01' Report color exception X'02' Do not report color exception M X'06' [MODCA-6-603]|
| 5 | Reserved; | should | | be zero | M | X'06' Triplet X'75' [MODCA-6-604]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-605]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-606]|
| 6 CODE ColSub X'01' Substitution rule if exception | | | | | | does not stop presentation X'01' For color-value exceptions, any color substitution is permitted; for CMR exceptions, use presentation system defaults M X'06' [MODCA-6-607]|
| 7 | Reserved; | should | | be zero | M | X'06' [MODCA-6-608]|
#### Triplet X'75' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Color Fidelity triplet.
StpCoEx Is a parameter that specifies whether presentation should be continued when a color
exception is detected. Valid values are:
Value Description
X'01' Stop presentation at the point of the first color exception. A color exception
that stops presentation must be reported.
X'02' Do not stop presentation because of color exceptions.
All others Reserved
RepCoEx Is a parameter that specifies whether color exceptions should be reported if they do not stop
presentation. Valid values are:
Value Description
X'01' Report color exceptions that do not stop presentation.
X'02' Do not report color exceptions that do not stop presentation.
All others Reserved
ColSUb Is a parameter that specifies color substitutions that the presentation process may use in order
to continue presentation following a color exception. Valid values are:
Value Description
X'01' For color-value exceptions, any color or grayscale may be substituted for a
color that cannot be rendered by the presentation process. For CMR
exceptions, use the presentation system default for that CMR type.
All others Reserved
Implementation Note: The following rules describe how AFP servers process the color fidelity triplet:
• If the Color Fidelity triplet is specified and is supported by the printer, the triplet is sent to the printer. [MODCA-6-609]
• If the Color Fidelity triplet is specified and is not supported by the printer, then [MODCA-6-610]
– If StpCoEx = X'01' (stop and report), the server issues an error message and the job will not be
printed.
– If StpCoEx = X'02' (do not stop), the job will be printed.
• If the Color Fidelity triplet is not specified but is supported by the printer, the printer is instructed to [MODCA-6-611]
reset color fidelity controls to defaults.
Triplet X'75'


• If the Color Fidelity triplet is not specified and is also not supported by the printer, presentation system [MODCA-6-612]
defaults determine how color exceptions are handled. [MODCA-6-613]
### Structured Field Using Triplet X'75'
• “Presentation Fidelity Control (PFC)” [MODCA-6-614]
Triplet X'75'


### Font Fidelity Triplet X'78'
The Font Fidelity triplet is used to specify the exception continuation rules for font resolution exceptions. Font
resolution exceptions are generated when either:
• the font referenced in an MCF structured field is not available to the presentation system at the resolution [MODCA-6-615]
specified in a Font Resolution and Metric T echnology (X'84') triplet, or
• the resolution of the font selected by the presentation server does not match the resolution of the [MODCA-6-616]
presentation device.
#### Triplet X'78' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-617]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-618]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-619]|
| 1 | CODE | Tid | | X'78' Identifies the Font Fidelity triplet | M | X'00' [MODCA-6-620]|
| 2 CODE StpFntEx X'01'–X'02' Font resolution exception | | | | | | continuation rule: X'01' Stop presentation at point of first font resolution exception and report exception X'02' Do not stop presentation because of font resolution exceptions M X'06' [MODCA-6-621]|
| 3–6 | Reserved; | should | | be zero | M | X'04' [MODCA-6-622]|
#### Triplet X'78' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Font Fidelity triplet.
StpFntEx Is a parameter that specifies whether presentation should be continued when a font resolution
exception is detected. Valid values are:
Value Description
X'01' Stop presentation at the point of the first font resolution exception. A font
resolution exception that stops presentation must be reported.
X'02' Do not stop presentation because of font resolution exceptions. Presentation
continues either with the font at a different resolution, which may require the
presentation device to apply resolution correction, or with an outline-
technology version of the font.
All others Reserved
### Structured Field Using Triplet X'78'
• “Presentation Fidelity Control (PFC)” [MODCA-6-623]
Triplet X'78'


### Attribute Qualifier Triplet X'80'
The Attribute Qualifier triplet is used to specify a qualifier for a document attribute. [MODCA-6-624]
#### Triplet X'80' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-625]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-626]|
| 0 UBIN Tlength 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-627]|
| 1 CODE Tid X'80' Identifies the Attribute Qualifier | | | | | | triplet M X'00' [MODCA-6-628]|
| 2–5 UBIN SeqNum X'00000000'– | | | | | | X'7FFFFFFF' Sequence Number M X'06' [MODCA-6-629]|
| 6–9 UBIN LevNum X'00000000'– | | | | | | X'7FFFFFFF' Level Number M X'06' [MODCA-6-630]|
#### Triplet X'80' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Attribute Qualifier triplet.
SeqNum Is a number used to distinguish multiple instances of the same attribute.
LevNum Is a number used to maintain a hierarchical relationship between groups of attributes. [MODCA-6-631]
### Structured Field Using Triplet X'80'
• “T ag Logical Element (TLE)” [MODCA-6-632]
Triplet X'80'


### Page Position Information Triplet X'81'
The Page Position Information triplet is used to tag a page with the Page Position (PGP) structured field
repeating group information that is used to present the page. The PGP is specified in the medium map
referenced by the FQN type X'8D'—Begin Medium Map Reference triplet. This information is used for viewing
the page with a particular form map, which is normally the form map that the document containing this page
was archived with.
This triplet is not used for printing and is ignored by print servers. [MODCA-6-633]
#### Triplet X'81' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-634]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-635]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-636]|
| 1 CODE Tid X'81' Identifies the Page Position | | | | | | Information triplet M X'00' [MODCA-6-637]|
| 2 | UBIN | PGPRG | | 1–8 PGP repeating group number | M | X'06' [MODCA-6-638]|
#### Triplet X'81' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Page Position Information triplet.
PGPRG Identifies the PGP repeating group that is used to view the page. The PGP is specified in the
medium map referenced by the FQN type X'8D' triplet. PGP repeating groups are numbered
sequentially from 1 to a maximum of 8, where the first repeating group is number 1. [MODCA-6-639]
### Structured Fields Using Triplet X'81'
• “Begin Page (BPG)” [MODCA-6-640]
• “Index Element (IEL)” [MODCA-6-641]
Triplet X'81'


### Parameter Value Triplet X'82'
The Parameter Value triplet is used to pass parameter values to an executable program such as an object
handler or a system command interpreter. [MODCA-6-642]
#### Triplet X'82' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-643]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-644]|
| 0 UBIN Tlength 4–(n+1) Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-645]|
| 1 CODE Tid X'82' Identifies the Parameter Value | | | | | | triplet M X'00' [MODCA-6-646]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-647]|
| 3 CODE ParmSyn X'00'–X'06' Parameter syntax: | | | | | | X'00' Undefined X'01' Unsigned binary number X'02' Signed binary number X'03' Bit string X'04' Defined constant X'05' Character string X'06' Name M X'06' [MODCA-6-648]|
| 4–n | ParmVal | Parameter | | value passed | O | X'00' [MODCA-6-649]|
#### Triplet X'82' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Parameter Value triplet.
ParmSyn Specifies the syntax of the parameter whose value is to be passed.
Value Description
X'00' Syntax is undefined, data type is UNDF
X'01' Unsigned binary number, data type is UBIN
X'02' Signed binary number, data type is SBIN
X'03' Bit string, where each bit can be individually and independently assigned a
value, data type is BITS
X'04' Defined or architected constant, data type is CODE
X'05' Encoded character data, data type is CHAR
X'06' Name, data type is CHAR
All others Reserved
ParmVal Specifies the parameter value that is passed. If omitted, the value of the parameter is specified
to be null; that is, no value is passed. [MODCA-6-650]
### Structured Field Using Triplet X'82'
• “Link Logical Element (LLE)” [MODCA-6-651]
Triplet X'82'


### Presentation Control Triplet X'83'
The Presentation Control triplet specifies flags that control the presentation of an object. [MODCA-6-652]
#### Triplet X'83' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-653]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-654]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-655]|
| 1 CODE Tid X'83' Identifies the Presentation | | | | | | Control triplet M X'00' [MODCA-6-656]|
| 2 BITS PRSFlg See “Triplet X'83' | | | | | | Semantics” for bit definitions Flags that control the presentation of an object M X'06' [MODCA-6-657]|
#### Triplet X'83' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Presentation Control triplet
PRSFlg Specifies presentation control flags as follows:
Bit Description
0 Object view control. [MODCA-6-658]
B'0' The specified object is intended for viewing. This is the architected default if
the triplet is omitted.
B'1' The specified object is not intended for viewing.
1 Object indexing control. [MODCA-6-659]
B'0' The specified object is intended to be indexed. This is the architected default
if the triplet is omitted.
B'1' The specified object is not intended to be indexed.
2–7 Reserved
### Structured Fields Using Triplet X'83'
• “Begin Named Page Group (BNG)” [MODCA-6-660]
• “Begin Page (BPG)” [MODCA-6-661]
• “Index Element (IEL)” [MODCA-6-662]
Triplet X'83'


### Font Resolution and Metric Technology Triplet X'84'
The Font Resolution and Metric T echnology specifies certain metric characteristics of a FOCA raster-
technology font character set which may have affected the formatting of the document with this font. This
information, as carried by the X'84' triplet, may be used by presentation servers and presentation devices to
select the best-matching coded font for presentation. [MODCA-6-663]
#### Triplet X'84' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-664]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-665]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-666]|
| 1 CODE Tid X'84' Identifies the Font Resolution | | | | | | and Metric T echnology triplet M X'00' [MODCA-6-667]|
| 2 CODE MetT ech X'01'–X'02' Metric T echnology: | | | | | | X'01' Fixed-metric technology X'02' Relative-metric technology M X'06' [MODCA-6-668]|
| 3 CODE RPuBase X'00' Raster-pattern resolution unit | | | | | | base: X'00' 10 inches M X'06' [MODCA-6-669]|
| 4–5 UBIN RPUnits X'0960', X'0BB8' Raster-pattern resolution units | | | | | | per unit base M X'06' [MODCA-6-670]|
#### Triplet X'84' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Font Resolution and Metric T echnology triplet.
MetTech Specifies the metric technology used by this raster font. For a description of fixed-metric and
relative-metric technologies, see the Intelligent Printer Data Stream (IPDS) Reference and the
Font Object Content Architecture Reference.
RPuBase Specifies the unit base for the raster font's resolution.
RPUnits Specifies the number of pels per unit base of the font's raster-pattern shape data.
X'0960' 2400
X'0BB8' 3000
Implementation Note: While 240-pel and 300-pel resolutions are the only fixed resolutions defined for AFP
FOCA raster fonts, some AFP products support additional resolutions such as 480 pel and 600 pel. In
particular, many IPDS printers will accept raster fonts at any pel resolution and automatically convert
them to the device resolution (support for “all resolutions in the range X'0001'-X'7FFF'”is indicated in the
printer's OPC reply). [MODCA-6-671]
### Structured Field Using Triplet X'84'
• “Map Coded Font (MCF) Format 2” [MODCA-6-672]
Triplet X'84'


### Finishing Operation Triplet X'85'
The Finishing Operation triplet is used to specify finishing operations that are to be applied to media.
Architecture Note: The format for specifying finishing operations and their associated parameters is based on
the Document Printing Application (DPA) ISO/IEC DLS 10175:1991 draft standard. The definition of an
operation or parameter in this triplet does not guarantee its support in an AFP system. T o see which
operations and parameters are supported by AFP printers, consult the appropriate product
documentation.
#### Triplet X'85' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-673]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-674]|
| 0 UBIN Tlength 9–253 Length of the triplet, | | | | | | including Tlength M X'02' [MODCA-6-675]|
| 1 CODE Tid X'85' Identifies the Finishing | | | | | | Operation triplet M X'00' [MODCA-6-676]|
| 2 CODE FOpType X'01'–X'0A', X'0C'–X'0F', | | | | | | X'12', X'14', X'18', X'19' , X'1E'–X'22', X'30'–X'32' Finishing operation type: X'01' Corner staple X'02' Saddle stitch out X'03' Edge stitch X'04' Fold in X'05' Separation cut X'06' Perforation cut X'07' Z-fold X'08' Center fold in X'09' Trim after center fold or saddle stitch X'0A' Punch X'0C' Perfect bind X'0D' Ring bind X'0E' C-fold in X'0F' Accordion fold in X'12' Saddle stitch in X'14' Fold out X'18' Center fold out X'19' Trim X'1E' C-fold out X'1F' Accordion fold out X'20' Double parallel fold in X'21' Double gate fold in X'22' Single gate fold in X'30' Double parallel fold out X'31' Double gate fold out X'32' Single gate fold out M X'06' [MODCA-6-677]|
| 3 CODE FOpOpt X'00', X'01' Finishing operation option: | | | | | | X'00' No finishing option X'01' Crease M X'06' Triplet X'85' [MODCA-6-678]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-679]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-680]|
| 4 | Reserved; | should | | be zero | M | X'06' [MODCA-6-681]|
| 5 CODE RefEdge X'00'–X'03', X'FF' Finishing operation | | | | | | reference corner or edge: X'00' Bottom-right corner, bottom edge X'01' T op-right corner, right edge X'02' T op-left corner, top edge X'03' Bottom-left corner, left edge X'FF' Device default reference corner or edge M X'06' [MODCA-6-682]|
| 6 UBIN FOpCnt X'00'–X'7A' Finishing operation count: | | | | | | X'00' Not specified; use OpPos parameters or device default X'01'–X'7A' Number of operations to apply; must match number of OpPos parameters if they are specified M X'06' [MODCA-6-683]|
| 7–8 UBIN AxOffst 0–32,767 Finishing operation axis | | | | | | offset in millimeters M X'06' X'FFFF' Device default axis offset Zero or more occurrences of the following parameters: [MODCA-6-684]|
| 0–1 UBIN OpPos 0–32,767 Operation position on | | | | | | finishing operation axis in millimeters O X'02' [MODCA-6-685]|
#### Triplet X'85' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Finishing Operation triplet.
FOpType Is a parameter that specifies the type of finishing operation. In most cases, the operation is
applied either on a reference corner or along a finishing operation axis that is offset from a
reference edge.
Value Operation Type
X'01' Corner staple. A staple is driven into the media at the reference corner. The
offset of the staple from the corner and the staple angle are presentation-
system-dependent. The AxOffset, FOPCnt, FOpOpt, and OpPOS parameters
are ignored for this operation. This operation is applied to collected media, not
to individual media.
Triplet X'85'


X'02' Saddle stitch out. One or more staples are driven into the media along the
finishing operation axis, which is positioned at the center of the media parallel
to the reference edge. The AxOffset and the FOpOpt parameters are ignored
for this operation. This operation also includes a fold of the media outward
along the finishing operation axis so that the front-side of the first sheet in the
collection is on the outside of the media collection. This operation is applied to
collected media, not to individual media. Note that the pages in the
datastream must already be properly ordered for this operation.
X'03' Edge stitch. One or more staples are driven into the media along the finishing
operation axis. This operation is applied to collected media, not to individual
media. The FOpOpt parameter is ignored for this operation.
X'04' Fold in. The media is folded inward on the front sheet-side. If applied to a
collection of media, the collection is folded inward on the front sheet-side of
the first sheet, and at the end of this operation the back side of the last sheet
of the collection is on the outside. The folding is performed along the finishing
operation axis. The FOpCnt and OpPos parameters are ignored for this
operation. Note that if applied to a collection of media, the pages in the
datastream must already be properly ordered for this operation. This type of
fold is also known as single fold.
X'05' Separation cut. A separation cut is applied to the media along the finishing
operation axis. The FOPCnt, FOpOpt, and OpPOS parameters are ignored
for this operation.
X'06' Perforation cut. A perforation cut is applied to the media along the finishing
operation axis. The FOPCnt, FOpOpt, and OpPOS parameters are ignored
for this operation.
X'07' Z-fold. A Z-fold is applied to each medium, or sheet. The medium is first
folded in half inwards along a line parallel to the reference edge. The half of
the medium furthest from the reference edge is then again folded in half
outwards along a line parallel to the reference edge. When applied to an
11×17-inch sheet with the reference edge specified as the top edge, the result
is an 8.5×11-inch fold-out.
Note: If additional finishing operations are applied to the Z-folded sheet, the
original reference edge becomes the left edge of the Z-folded sheet. In
the example above, the reference edge for the Z-fold was the top (11-
inch) edge. After Z-folding is applied, the sheet is reoriented so that this
reference edge now becomes the left edge for additional finishing
operations. Therefore if the Z-folded sheets are to be stapled to the left
edge of 8.5×11–inch sheets, the stapling reference edge for both sets
of sheets is the left edge.
Architecture Note: There is an exception to the rule for reorientation after Z-
fold. If the media is sized such that the reference edge is less than half
the size of the other sheet dimension, the reorientation causes the
reference edge to become the new top edge for additional finishing
operations instead of the new left edge.
The FOpCnt, AxOffst, and OpPos parameters are ignored for this operation.
Note that the Z-fold is applied to each individual medium, not to the collected
media. This type of fold is also known as engineering fold.
X'08' Center fold in. The media is folded inward on the front sheet-side. If applied to
a collection of media, the collection is folded inward on the front sheet-side of
the first sheet, and at the end of this operation the back side of the last sheet
of the collection is on the outside. The folding is performed along the center
line that is parallel to the finishing operation axis. The FOpCnt, AxOffst, and
Triplet X'85'


OpPos parameters are ignored for this operation. Note that if applied to a
collection of media, the pages in the datastream must already be properly
ordered for this operation. This type of fold is also known as bi-fold, half fold,
and 2-fold.
X'09' Trim after center fold or saddle stitch. This operation is intended to
accompany either a center-fold operation or a saddle-stitch operation. The
FOpOpt parameter is ignored for this operation:
• If this operation is specified immediately after a finishing operation that [MODCA-6-686]
causes a center fold (either saddle-stitch or center-fold), the edges opposite
the center fold are trimmed by the amount specified in the AxOffst
parameter measured from the edges of the innermost sheet that are
opposite the center fold.
• If this operation is specified, but is not immediately after a center-fold or [MODCA-6-687]
saddle-stitch operation, the trim operation is ignored.
X'0A' Punch. One or more holes are punched or drilled into the media along the
finishing operation axis. This operation is applied to collected media, not to
individual media. The FOpOpt parameter is ignored for this operation.
X'0C' Perfect bind. This operation is a type of book binding in which the sheets of
the group are glued together at the reference edge (spine). The device may
optionally include a cover sheet which was pre-loaded in the binding machine,
is wrapped around the front, spine, and back, and is attached at or near the
spine. The FOpOpt parameter is ignored for this operation.
X'0D' Ring bind. This operation is a type of book binding in which the sheets of the
group are loosely connected at the reference edge (spine) by first drilling or
punching a set of holes along the reference edge and then inserting a wire
pattern through the holes. This allows the sheets of a document to be flexibly
turned and laid flat against a surface without breaking the spine. When the
wire pattern is a wire helix, this operation is also called a spiral bind or coil
bind. The device may optionally include front and back cover sheets which
were pre-loaded in the binding machine. The FOpOpt parameter is ignored
for this operation.
X'0E' C-fold in. The media is folded inward on the front sheet-side. If applied to a
collection of media, the collection is folded inward on the front sheet-side of
the first sheet, and at the end of this operation the back side of the last sheet
of the collection is on the outside. The folding is performed along two lines
parallel to the finishing operation axis. T o allow the panels to nest inside each
other properly, the folded in bottom panel is usually 1/32" to 1/8" narrower
than the other two panels. This bottom panel is the bottom part of the sheet,
seen from the top edge. C-fold in is used for letters when envelopes without
address windows are used. The FOpCnt, AxOffst, and OpPos parameters are
ignored for this operation. Note that if applied to a collection of media, the
pages in the datastream must already be properly ordered for this operation.
This type of fold is also known as business-letter fold, letter fold, roll fold,
spiral fold, and tri fold.
X'0F' Accordion fold in. The media is folded inward (top fold) and outward (2nd fold)
on the front sheet-side. If applied to a collection of media, at the end of this
operation the lower panel of the front side of the first sheet of the collection
and the upper panel of the back side of the last sheet of the collection will be
visible on the outside. The folding is performed along two lines parallel to the
finishing operation axis. The sheet is folded into a Z-like shape of three
panels. The middle panel is usually slightly larger than the two outer panels.
The FOpCnt, AxOffst, and OpPos parameters are ignored for this operation.
Triplet X'85'


Note that if applied to a collection of media, the pages in the datastream must
already be properly ordered for this operation. This type of fold is also known
as concertina fold, letter fold, tri fold, and zig-zag fold.
X'12' Saddle stitch in. One or more staples are driven into the media along the
finishing operation axis, which is positioned at the center of the media parallel
to the reference edge. The AxOffset and the FOpOpt parameters are ignored
for this operation. This operation also includes a fold of the media inward
along the finishing operation axis so that the front-side page of the first sheet
in the collection is on the inside of the media collection. This operation is
applied to collected media, not to individual media. Note that the pages in the
datastream must already be properly ordered for this operation.
X'14' Fold out. The media is folded outward on the front sheet-side. If applied to a
collection of media, the collection is folded outward on the front sheet-side of
the first sheet, and at the end of this operation the back side of the last sheet
of the collection is on the inside. The folding is performed along the finishing
operation axis. The FOpCnt and OpPos parameters are ignored for this
operation. Note that if applied to a collection of media, the pages in the
datastream must already be properly ordered for this operation.
X'18' Center fold out. The media is folded outward on the front sheet-side. If applied
to a collection of media, the collection is folded outward on the front sheet-
side of the first sheet, and at the end of this operation the front side of the first
sheet of the collection is on the outside. The folding is performed along the
center line that is parallel to the finishing operation axis. Center fold out is
often used to fold A4 letters for an A5 envelope so that the address on the first
page will then show through the window of the envelope. The FOpCnt,
AxOffst, and OpPos parameters are ignored for this operation. Note that if
applied to a collection of media, the pages in the datastream must already be
properly ordered for this operation. This type of fold is also known as bi-fold,
half fold, and 2-fold.
X'19'
Trim. This operation causes the media to be trimmed along the reference
edge. The edge is trimmed by the amount specified in the AxOffst parameter
measured from the edge of the media. Once cut, the part of the media that is
adjacent to the reference edge is discarded. The FOpOpt, FOpCnt, and
OpPos parameters are ignored for this operation.
X'1E' C-fold out. The media is folded outward on the front sheet-side. If applied to a
collection of media, the collection is folded outward on the front sheet-side of
the first sheet, and at the end of this operation the front side of the first sheet
of the collection is on the outside. The folding is performed along two lines
parallel to the finishing operation axis. T o allow the panels to nest inside each
other properly, the folded in bottom panel is usually 1/32" to 1/8" narrower
than the other two panels. This bottom panel is the bottom part of the sheet,
seen from the top edge. C-fold out is often used for letters when envelopes
with windows are used so that the address on the first page will then show
through the window of the envelope. The FOpCnt, AxOffst, and OpPos
parameters are ignored for this operation. Note that if applied to a collection of
media, the pages in the datastream must already be properly ordered for this
operation. This type of fold is also known as business-letter fold, letter fold,
roll fold, spiral fold, and tri fold.
X'1F' Accordion fold out. The media is folded outward (top fold) and inward (2nd
fold) on the front sheet-side. If applied to a collection of media, at the end of
this operation the upper panel of the front side of the first sheet of the
collection and the lower panel of the back side of the last sheet of the
collection will be visible on the outside. The folding is performed along two
Triplet X'85'


lines parallel to the finishing operation axis. The sheet is folded into a Z-like
shape of three panels. The middle panel is usually slightly larger than the two
outer panels. Accordion fold out is often used for letters instead of C-fold out.
The FOpCnt, AxOffst, and OpPos parameters are ignored for this operation.
Note that if applied to a collection of media, the pages in the datastream must
already be properly ordered for this operation. This type of fold is also known
as concertina fold, letter fold, tri fold, and zig-zag fold.
X'20' Double parallel fold in. This fold is applied to each medium, or sheet, and
causes the sheet to be folded inwards, first in the middle and then the folded
sheet is folded once again so that four panels of roughly equal size are
formed. The folding is performed parallel to the finishing operation axis. The
front of the sheet is inside. The two inside folded panels are 1/32" to 1/8"
smaller than the two inner panels to allow for proper nesting. The FOpCnt,
AxOffst, and OpPos parameters are ignored for this operation. Note that this
fold is applied to each individual medium, not the collected media. This type of
fold is also known as double fold, parallel fold, and quarter fold.
X'21' Double gate fold in. This fold is applied to each medium, or sheet, and causes
the sheet to be folded into four panels of roughly equal size. First the two
outer panels are folded inwards so that the top and the bottom edges of the
sheet meet. The folded sheet is then folded inward again in the middle so that
the top and bottom panels are inside. The back of the second and third panel
will be visible on the outside. The front of the sheet will be inside the folded
product. The two outer panels are usually 1/32" to 1/8" smaller than the two
inner panels to allow for proper folding and nesting. The double gate fold is
sometimes used for large magazine centerfolds. The RefEdge, FOpCnt,
AxOffst, and OpPos parameters are ignored for this operation. Note that this
fold is applied to each individual medium, not the collected media. This type of
fold is also known as closed-gate fold, and gate fold.
X'22' Single gate fold in. The media is folded inward on the front sheet-side. If
applied to a collection of media, the collection is folded inward on the front
sheet-side of the first sheet, and at the end of this operation the back side of
the last sheet of the collection will be visible on the outside and the front of the
first sheet will be inside the folded product. The sheet or collection is folded
into three panels with two outer panels and a larger middle panel. The two
outer panels are folded inwards so that the top and the bottom edges of the
sheet meet. This fold is sometimes used for menus and brochures. The
RefEdge, FOpCnt, AxOffst, and OpPos parameters are ignored for this
operation. Note that if applied to a collection of media, the pages in the
datastream must already be properly ordered for this operation. This type of
fold is also known as gate fold, simple gate fold, and window fold.
X'30' Double parallel fold out. This fold is applied to each medium, or sheet, and
causes the sheet to be folded outwards, first in the middle and then the folded
sheet is folded once again so that four panels of roughly equal size are
formed. The folding is performed parallel to the finishing operation axis. The
top and the second panel of the front of the sheet is visible on the outside.
The two inside folded panels are 1/32" to 1/8" smaller than the two inner
panels to allow for proper nesting. The FOpCnt, AxOffst, and OpPos
parameters are ignored for this operation. Note that this fold is applied to each
individual medium, not the collected media. This type of fold is also known as
double fold, parallel fold, and quarter fold.
X'31' Double gate fold out. This fold is applied to each medium, or sheet, and
causes the sheet to be folded into four panels of roughly equal size. First the
two outer panels are folded outwards so that the top and the bottom edges of
the sheet meet. The folded sheet is then folded outward again in the middle
Triplet X'85'


so that the top and bottom panels are inside. The front sides of the two middle
panels will be visible on the outside. The two outer panels are usually 1/32" to
1/8" smaller than the two inner panels to allow for proper folding and nesting.
The RefEdge, FOpCnt, AxOffst, and OpPos parameters are ignored for this
operation. Note that this fold is applied to each individual medium, not the
collected media. This type of fold is also known as closed-gate fold, and gate
fold.
X'32' Single gate fold out. The media is folded outward on the front sheet-side. If
applied to a collection of media, the collection is folded outward on the front
sheet-side of the first sheet, and at the end of this operation the front side of
the first sheet of the collection will be visible on the outside and the back of
the last sheet will be inside the folded product. The sheet or collection is
folded into three panels with two outer panels and a larger middle panel. The
two outer panels are folded outwards so that the top and the bottom edges of
the sheet meet. The RefEdge, FOpCnt, AxOffst, and OpPos parameters are
ignored for this operation. Note that if applied to a collection of media, the
pages in the datastream must already be properly ordered for this operation.
This type of fold is also known as gate fold, simple gate fold, and window fold.
All others Reserved
Figure 74, Figure 76, and Figure 75 show examples of
these finishing operations.
Triplet X'85'


Figure 74. Examples of Finishing Operations
Triplet X'85'


Figure 75. Examples of Additional Finishing Operations
Perfect Bind
Note: This example shows a
perfect bind on the
left edge with a cover.
Left edge
Front side of
cover sheet
First sheet
of group
Trim after Center-Fold In
Center fold
Ring Bind
Note: This example shows
a ring bind on the
left edge.
Le
ft e
dge
First sheet
of group
Before trim
After trim
Axis offset
Axis offset = 0 will trim nothing
The appropriate axis offset value
depends on the number of sheets
in the group.
Cuts
Note: This example shows a sheet
that has been cut using 2
different finishing operations.
Separation cut
Perforation cut
Top edge
Bottom edge
ax
is offset
from top edge
axis offset from
bottom edge
Trim
Trim (cut)
Reference edge
Axis offset
from reference
edge
Discarded
Triplet X'85'


Figure 76. More Examples of Additional Finishing Operations
Triplet X'85'


FOpOpt Is a parameter that specifies the finishing option that modifies the existing finishing operation
selected. Valid values are:
Value Description
X'00' No finishing options specified.
X'01' Crease finishing option.
Crease is very similar to a fold, except that instead of doing a full fold, it
simply creates a crease line in the paper. For an accordion fold, c-fold, center-
fold, double-gate-fold, double-parallel fold, fold, single-gate-fold, or Z-fold
operation, this value specifies that a crease operation is performed instead of
each fold defined for the operation. For example, for an accordion fold, rather
than an inward fold and outward fold, an inward crease and outward crease
are made, at the same locations the two folds would have been performed.
For a corner staple, saddle stitch, edge stitch, separation cut, perforation cut,
trim,
trim after center fold or saddle stitch, punch, perfect bind, or ring bind
operation, this value is ignored.
The Crease option does not change the scope of finishing operations.
All others Reserved
RefEdge Is a parameter that selects the medium reference corner and the medium reference edge for
finishing operations. Edge and corner definitions for cut-sheet and continuous-forms media
are shown in Figure 77. Valid values are:
Value Description
X'00' Bottom-right corner, bottom edge
X'01' T op-right corner, right edge
X'02' T op-left corner, top edge
X'03' Bottom-left corner, left edge
X'FF' Presentation device default reference corner or edge
All others Reserved
Note: For all types of media shown in Figure 77, the top-left corner is defined to
be the default media origin of the front side. A change in the orientation of the medium
presentation space does not change the finishing corners or edges. For continuous-
forms media, the carrier strips are not considered to be part of the physical media.
Figure 77. Media Reference Edge and Corner Definitions
FOpCnt Is a parameter that specifies the number of discrete finishing operations that are to be applied
by this operation type along the finishing operation axis. For example, if the operation type is
edge-stitch, the FOpCnt parameter specifies how many staples are to be applied along the
finishing operation axis. Valid values are:
Triplet X'85'


Value Description
X'00' Count not specified. Use the count implied by the number of OpPos
parameters if they are specified or use the presentation device default count if
OpPos parameters are not specified.
X'01'–X'7A' Apply the specified number of finishing operations. This count must match the
number of OpPos parameters if OpPos parameters are specified; if OpPos
parameters are not specified, presentation device default positions are used.
All others Reserved
AxOffst Is a parameter that specifies the offset of the finishing operation axis from the reference edge.
The offset is measured in millimeters from the reference edge toward the center of the
medium. A value of X'FFFF' indicates that the presentation device default finishing operation
axis offset is to be used.
OpPos Is a parameter that specifies the offset of the finishing operation along the finishing operation
axis. The offset is measured in millimeters from the point where the finishing operation axis
intersects either the bottom edge or the left edge of the medium, toward the center of the
medium. Each consecutive OpPos parameter is used to position a single finishing operation
centered on the specified point on the finishing operation axis. This continues until the last
OpPos parameter has been processed. [MODCA-6-688]
### Structured Field Using Triplet X'85'
• “Medium Finishing Control (MFC)” [MODCA-6-689]
Triplet X'85'


### Text Fidelity Triplet X'86'
The T ext Fidelity triplet is used to specify the exception continuation and reporting rules for text exceptions. A
text exception is detected when an unrecognized or unsupported text control sequence is encountered in a
PTOCA text object.
#### Triplet X'86' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-690]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-691]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-692]|
| 1 | CODE | Tid | | X'86' Identifies the T ext Fidelity triplet | M | X'00' [MODCA-6-693]|
| 2 CODE StpTxtEx X'01'–X'02' T ext exception continuation rule: | | | | | | X'01' Stop presentation at point of first text exception and report exception X'02' Do not stop presentation because of text exceptions M X'06' [MODCA-6-694]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-695]|
| 4 CODE RepTxtEx X'01'–X'02' T ext exception reporting rule if | | | | | | exception does not stop presentation: X'01' Report text exception X'02' Do not report text exception M X'06' [MODCA-6-696]|
| 5–6 | Reserved; | should | | be zero | M | X'06' [MODCA-6-697]|
#### Triplet X'86' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the T ext Fidelity triplet.
StpTxtEx Is a parameter that specifies whether presentation should be continued when a text exception
is detected. Valid values are:
Value Description
X'01' Stop presentation at the point of the first text exception. A text exception that
stops presentation must be reported.
Application Note: When presentation is terminated, the print file is put into a
state where it can be resubmitted when the text can be rendered
without exceptions.
X'02' Do not stop presentation because of text exceptions.
All others Reserved
RepTxtEx Is a parameter that specifies whether text exceptions should be reported if they do not stop
presentation. Valid values are:
Triplet X'86'


Value Description
X'01' Report text exceptions that do not stop presentation.
X'02' Do not report text exceptions that do not stop presentation.
All others Reserved
Implementation Note: The following rules describe how AFP servers process the T ext Fidelity triplet:
• If the T ext Fidelity triplet is specified and is supported by the printer, the triplet is sent to the printer and [MODCA-6-698]
processed by both server and printer. If StpTxtEx = X'02' and a text exception is detected, the text
control sequence that generated the exception is skipped or processed in non-optimal fashion and
printing continues with the next text control sequence.
• If the T ext Fidelity triplet is specified and is not supported by the printer, the triplet is processed by the [MODCA-6-699]
server. T ext exceptions will flow from the printer to the server. If StpTxtEx = X'02' and a text exception
is detected, printing continues after the remainder of the text object—which could encompass the
whole page—is skipped.
• If the T ext Fidelity triplet is not specified, presentation system defaults determine how text exceptions [MODCA-6-700]
are handled.
### Structured Field Using Triplet X'86'
• “Presentation Fidelity Control (PFC)” [MODCA-6-701]
Triplet X'86'


### Media Fidelity Triplet X'87'
The Media Fidelity triplet is used to specify the continuation rule if a request for a specific media or a specific
media bin cannot be satisfied. [MODCA-6-702]
#### Triplet X'87' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-703]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-704]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-705]|
| 1 | CODE | Tid | | X'87' Identifies the Media Fidelity triplet | M | X'00' [MODCA-6-706]|
| 2 CODE StpMedEx X'01'–X'02' Media exception continuation | | | | | | rule: X'01' T erminate job and hold X'02' Continue with defaults M X'06' [MODCA-6-707]|
| 3–6 | Reserved; | should | | be zero | M | X'06' [MODCA-6-708]|
#### Triplet X'87' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Media Fidelity triplet.
StpMedEx Is a parameter that specifies the continuation rule for the presentation system if the requested
media or the requested media bin is not available in the presentation device. Valid values are:
Value Description
X'01' T erminate presentation
Application Note: When presentation is terminated, the print file is put into a
state where it can be resubmitted when the proper media is loaded or
when the proper media source is made available.
X'02' Continue with the presentation system defaults
All others Reserved
Implementation Note: AFP print servers will attempt to select the media using the following priority:
1. Attempt to find an available media source containing the media type that matches the specified [MODCA-6-709]
media OID. The media source cannot be an inserter bin.
2. Attempt to find an available media source containing the media type that matches the specified [MODCA-6-710]
media name. The media source cannot be an inserter bin.
3. Attempt to find an available media source whose ID matches the specified ID. [MODCA-6-711]
4. If the continuation rule is X'02' (continue with defaults), use the presentation process defaults for [MODCA-6-712]
finding an available media source. If the continuation rule is X'01', presentation is terminated. [MODCA-6-713]
### Structured Field Using Triplet X'87'
• “Presentation Fidelity Control (PFC)” [MODCA-6-714]
Triplet X'87'


### Finishing Fidelity Triplet X'88'
The Finishing Fidelity triplet is used to specify the exception continuation and reporting rules for finishing
exceptions. A finishing exception is detected when the specified finishing operation cannot be satisfied. [MODCA-6-715]
#### Triplet X'88' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-716]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-717]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-718]|
| 1 CODE Tid X'88' Identifies the Finishing Fidelity | | | | | | triplet M X'00' [MODCA-6-719]|
| 2 CODE StpFinEx X'01'–X'02' Finishing exception continuation | | | | | | rule: X'01' Stop presentation at point of first finishing exception and report exception X'02' Do not stop presentation due to finishing exceptions M X'06' [MODCA-6-720]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-721]|
| 4 CODE RepFinEx X'01'–X'02' Finishing exception reporting rule | | | | | | if exception does not stop presentation: X'01' Report finishing exception X'02' Do not report finishing exception M X'06' [MODCA-6-722]|
| 5–6 | Reserved; | should | | be zero | M | X'06' [MODCA-6-723]|
#### Triplet X'88' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Finishing Fidelity triplet.
StpFinEx Is a parameter that specifies whether presentation should be continued when a finishing
exception is detected. Valid values are:
Value Description
X'01' Stop presentation at point of first finishing exception. A finishing exception
that stops presentation must be reported.
Application Note: When presentation is terminated, the print file is put into a
state where it can be resubmitted when the finishing operation can be
performed.
X'02' Do not stop presentation due to finishing exceptions. Presentation continues
without applying the finishing operation that cannot be satisfied. Note,
however, that if a device supports a different finishing operation that is
reasonably equivalent to the requested operation, the supported operation
may be applied in place of the requested operation. For example, C-fold out
Triplet X'88'


and Accordion fold out are often interchangeable when the output is to be
inserted into a window envelope; if the device supports Accordion fold out
(and not C-fold out), but the triplet requests a C-fold out operation, a device
can use Accordion fold out when applying the continuation rule.
All others Reserved
RepFinEx Is a parameter that specifies whether finishing exceptions should be reported if they do not
stop presentation. Valid values are:
Value Description
X'01' Report finishing exceptions that do not stop presentation.
X'02' Do not report finishing exceptions that do not stop presentation.
All others Reserved
Note: This triplet covers finishing operations that the printer is incapable of processing such as a stapling
operation on a device that does not have a stapler attached. It does not cover temporary exceptions
such as out-of-finishing-supplies conditions, which result in a printer intervention condition that is
cleared as soon as supplies are added.
Implementation Note: The following rules describe how AFP servers process the Finishing Fidelity triplet:
• If the Finishing Fidelity triplet is specified and is supported by the printer, the triplet is sent to the printer [MODCA-6-724]
and processed by both server and printer.
• If the Finishing Fidelity triplet is specified and is not supported by the printer, the triplet is processed by [MODCA-6-725]
the server. Finishing exceptions will flow from the printer to the server; this may cause a performance
degradation. If StpFinEx = X'02' and RepFinEx = X'02', the server will suppress the finishing error
messages.
• If the Finishing Fidelity triplet is not specified, the job is printed and the finishing operations that cannot [MODCA-6-726]
be satisfied are not applied. Finishing exceptions are reported. [MODCA-6-727]
### Structured Field Using Triplet X'88'
• “Presentation Fidelity Control (PFC)” [MODCA-6-728]
Triplet X'88'


Data-Object Font Descriptor Triplet X'8B'
The Data-Object Font Descriptor triplet is used to specify the parameters needed to render a data-object font.
Data-object fonts are non-FOCA font resources, such as TrueType and OpenType fonts. An MDR structured
field is used to map a data-object font as a resource. [MODCA-6-729]
#### Triplet X'8B' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-730]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-731]|
| 0 UBIN Tlength 16 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-732]|
| 1 CODE Tid X'8B' Identifies the Data-Object Font | | | | | | Descriptor triplet M X'00' [MODCA-6-733]|
| 2 BITS DOFtFlgs See “Triplet X'8B' | | | | | | Semantics” for bit definitions Flags that specify additional font information M X'06' [MODCA-6-734]|
| 3 CODE FontT ech X'20' Font technology: | | | | | | X'20' TrueType/ OpenType M X'06' [MODCA-6-735]|
| 4–5 | UBIN | VFS | | 1–32,767 Specified vertical font size | M | X'06' [MODCA-6-736]|
| 6–7 | UBIN | HFS | | 1–32,767 Horizontal scale factor | M | X'06' X'0000' Not specified [MODCA-6-737]|
| 8–9 CODE CharRot X'0000', X'2D00', | | | | | | X'5A00', X'8700' Clockwise character rotation in degrees X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' [MODCA-6-738]|
| 10–11 CODE EncEnv X'0003' Encoding environment | | | | | | X'0003' Microsoft M X'06' [MODCA-6-739]|
| 12–13 CODE EncID X'0001' Environment-specific encoding | | | | | | identifier X'0001' Unicode M X'06' [MODCA-6-740]|
| 14–15 | Reserved; | should | | be zero | M | X'06' [MODCA-6-741]|
#### Triplet X'8B' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Data-Object Font Descriptor triplet.
DOFtFlgs provide additional information for the parameters in this triplet. Valid values are:
Bits Description
0 MICR print. Defines whether the font is to be used for Magnetic Ink Character [MODCA-6-742]
Recognition (MICR) printing. If MICR printing is requested, the font needs to be
designed for use in MICR applications. MICR text is normally printed using a toner
that is mixed with a magnetic material.
B'0' The font is to be used for non-MICR printing.
Triplet X'8B'


B'1' The font is to be used for MICR printing.
1 Location of font in resource hierarchy. May specify that the font and all associated [MODCA-6-743]
linked fonts are in a print file resource group and that the search for this font and all
associated linked fonts must be limited to the resource group.
B'0' The font and all associated linked fonts can be located anywhere in the
MO:DCA resource hierarchy.
B'1' The font and all associated linked fonts are located in the resource group for
the print file; that is, they are “inline”. The search for this font and all
associated linked fonts must be limited to this resource group. If the font or an
associated linked font is not found in the print file resource group, the search
is not extended to resource libraries and exception condition X'04' is
recognized.
2–7 Reserved; should be zero
Application Note:
AFP support for complex text uses glyph runs. Since this support uses glyph IDs
instead of code points to identify a glyph to be rendered, and since glyph IDs can
change even with a minor re-versioning of the font, it is critical that the composition
application that generates the PTOCA complex text uses precisely the same font that is
used by the presentation device that renders the text. This is ensured by requiring the
font OID to be specified for each glyph run. The presentation device compares the font
OID in the text object to the OID of the active font or a font linked to the active font, and
if the OIDs do not match, an error is generated and presentation does not occur.
If such fonts are placed in resource libraries, it is relatively easy to lose the correct
version of the font due to library updates, etc. This can result in a presentation device
error due to a font OID mismatch. Moreover, this error cannot be corrected easily if the
required version of the font is lost.
It is therefore strongly recommended that all TrueType/OpenType fonts that are used
for complex text rendering be placed in the print file resource group. This ensures that
the formatting application and the presentation device both work with the exact same
version of the same font. T o ensure that only a font from the print file resource group is
used by the presentation system, it is strongly recommended that DOFtFlgs bit one be
set to B'1' for such fonts.
FontTech Identifies the font technology of the font. Valid values are:
Value Description
X'20' TrueType/OpenType
All
others
Reserved
VFS Specifies the vertical font size in 1440ths of an inch. The specified vertical font size is the
desired distance between adjacent character baselines when character rotation is zero
degrees and no external leading is used. The desired vertical size of the font is often called
“point size” because formatting programs typically specify this size in point units (1/72 inch); in
this case, the vertical font size can be calculated by multiplying the desired point size by 20.
HSF Specifies the horizontal scale factor in 1440ths of an inch. The horizontal scale factor specifies
the numerator of a scale factor for the horizontal direction in 1440ths of an inch. The character
shapes and metrics are stretched or compressed in the horizontal direction by the ratio of
HSF/VFS. When the vertical font size and the horizontal scale factor are identical or when
HSF=X'0000' is specified, a uniform scaling occurs; when these two parameters are different,
an anamorphic scaling occurs.
Triplet X'8B'


CharRot Specifies the clockwise character rotation in degrees. This parameter specifies a clockwise
rotation of a character pattern (glyph) from the character baseline. For a description of
character rotation, see the Font Object Content Architecture (FOCA) Reference. The four
allowed character rotations provide for different writing modes (left-to-right, top-to-bottom,
right-to-left, and bottom-to-top). A normal (right-side-up) character has a character rotation of
0 degrees; an upside down character has a character rotation of 180 degrees. A character [MODCA-6-744]
rotation of 270 degrees is normally used for vertical writing. The valid character rotation values
are:
X'0000' 0 degrees (left-to-right writing)
X'2D00' 90 degrees (bottom-to-top writing)
X'5A00' 180 degrees (right-to-left writing)
X'8700' 270 degrees (top-to-bottom writing)
Figure 78 shows the placement of characters based on the character rotation
value and the PTOCA inline and baseline direction values.
Figure 78. Character Placement Based on Character Rotation and Inline and Baseline
Direction
TrueType fonts provide two sets of metrics to allow character placement for different writing
modes. The metrics for horizontal writing are used when the character rotation is 0 degrees,
and a modified version of the horizontal metrics is used for a 180 degree character rotation.
Likewise, the metrics for vertical writing are used when the character rotation is 270 degrees,
and a modified version of the vertical metrics is used for a 90 degree character rotation.
Architecture Notes:
1. The character rotation parameter is used in PTOCA text objects along with the current [MODCA-6-745]
inline and baseline directions to determine the character orientation with respect to the
page (X
p, Yp) coordinate system.
2. The character-rotation parameter applies only to characters used in PTOCA text objects or [MODCA-6-746]
BCOCA bar code objects. For GOCA graphics objects, the Set Character Angle drawing
order provides analogous function.
Triplet X'8B'


EncEnv Specifies the environment for the encoding in the font.
Architecture Note: In TrueType/OpenType font files, this parameter is called the Platform ID.
Value Description
X'0003' Microsoft
All others Reserved
This parameter, along with the EncID parameter, identifies a character encoding within the font
that is used to map code points to glyphs and metrics. Note that different font technologies use
different methods to achieve this purpose:
• The TrueType/OpenType font technology uses an internal cmap table for this purpose; most [MODCA-6-747]
TrueType fonts contain a Unicode cmap subtable and some TrueType fonts also contain
additional cmap subtables to allow the font to be used with a variety of character encoding
schemes. The cmap subtable is indexed with the EncEnv and EncID parameters.
Application Note: A TrueType/OpenType font can also be used with user data that is
encoded to be rendered with a traditional AFP FOCA font. Such FOCA fonts use an
IBM code page to map code points to graphic character identifiers. T o support the
presentation of such data with TrueType/OpenType fonts, the user data encoding and
the corresponding code page are specified on the MDR that is used to reference the
TrueType/OpenType font. A mapping function in the presentation system is invoked to
map the IBM graphic character identifiers to Unicode code points in the TrueType/
OpenType font's cmap subtable defined by EncEnv = Microsoft (X'0003') and EncID =
Unicode (X'0001').
The valid encoding-environment values for each font technology are:
Font Technology Encoding Environment
TrueType/OpenType Microsoft (X'0003')
EncID Specifies the character encoding that is to be used to interpret the meaning of each code
point.
Architecture Note: In TrueType/OpenType font files, this parameter is called the Encoding ID.
The values that are valid for the encoding identifier depend on the specified encoding
environment parameter. For the Microsoft encoding environment (EncEnv = X'0003'), the
following encoding identifiers are supported:
Value Description
X'0001' Unicode
### Structured Field Using Triplet X'8B'
• “Map Data Resource (MDR)” [MODCA-6-748]
Triplet X'8B'


### Locale Selector Triplet X'8C'
The Locale Selector triplet is used to identify the end-user community for presentation text data. The locale
information consists of an ISO-639 based language code, an ISO-15924 based script code, an ISO-3166
based region code, and an application-specific variant code. The encoding for all four parameters is UTF-
16BE. Additional information on these parameters can be found at the following URLs:
• The definition of language codes can be found at: [MODCA-6-749]
http://lcweb.loc.gov/standards/iso639-2/iso639jac.html
• The definition of script codes can be found at: [MODCA-6-750]
www.unicode.org/reports/tr24
• The definition of region codes can be found at: [MODCA-6-751]
www.iso.org/iso-3166-country-codes.html
The locale may be specified at job submission time. In that case the locale reflects the intent of the job
submitter and is called a submission locale. The locale may also be specified directly in the document or print
file, such as on an MDR structured field that identifies a font to be used for rendering a specific text string. In
that case the locale reflects the intent of the document creator and is called a creation locale. The submission
locale establishes the locale for all objects and components in the document or print file that do not specify a
creation locale. Where the submission locale and creation locale conflict, the creation locale overrides. If no
submission locale is specified, the presentation system default locale is applied as the default submission
locale. Note that in this case different locales may exist in various parts of the system and inconsistent results
may be generated.
The scope of the Locale Selector triplet, when it is used to specify a creation locale, is defined as follows:
• If a X'8C' triplet appears on an MDR structured field that references a data-object font, its scope is the text [MODCA-6-752]
string that is rendered with that font.
Architecture Note: The locale information carried in this triplet is based on the definition established by the
International Components for Unicode (ICU) project, which is jointly managed by a group of companies
and individual volunteers throughout the world. [MODCA-6-753]
#### Triplet X'8C' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-754]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-755]|
| 0 UBIN Tlength 36 – 254; even | | | | | | values Length of the triplet, including Tlength M X'02' [MODCA-6-756]|
| 1 CODE Tid X'8C' Identifies the Locale Selector | | | | | | triplet M X'00' [MODCA-6-757]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-758]|
| 3 BITS LocFlgs See “Triplet X'8C' | | | | | | Semantics” for bit definitions Flags that specify additional syntax information M X'06' [MODCA-6-759]|
| 4–11 CHAR LangCode Language code as registered in | | | | | | ISO-639; encoding is UTF-16BE M X'06' [MODCA-6-760]|
| 12–19 CHAR ScrptCde Script code as registered in ISO- [MODCA-6-761]| | | | | | |
| 15924; encoding is UTF-16BE | | | | | | M X'06' [MODCA-6-762]|
| 20–27 CHAR RegCde Region code as registered in | | | | | | ISO-3166; encoding is UTF- 16BE M X'06' Triplet X'8C' [MODCA-6-763]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-764]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-765]|
| 28–35 | Reserved; | should | | be zero | M | X'06' [MODCA-6-766]|
| 36–n CHAR VarCde Variant code; encoding is UTF- | | | | | | 16BE O X'00' [MODCA-6-767]|
#### Triplet X'8C' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Locale Selector triplet.
LocFlgs provide syntax information for the parameters in this triplet. Valid values are:
Bits Description
1–3 Language code syntax
Note: ISO-639 is the international standard for the representation of names of
languages.
B'000' Language code is not specified; the parameter should be ignored.
B'010' The language code is specified using a two-character language
identifier (ISO 639 Alpha-2 code) defined in ISO 639-1.
B'011' The language code is specified using a three-character language
identifier (ISO 639-2/B bibliographic code) defined in ISO 639-2.
All others Reserved
4 Script code [MODCA-6-768]
Note: ISO-15924 is the international standard for the representation of names of
scripts.
B'0' Script code is not specified; the parameter should be ignored.
B'1' The script code is specified using a four-character script identifier defined in
ISO 15924.
5–7 Region code syntax
Note: ISO-3166 is the international standard for the representation of names of
regions.
B'000' Region code is not specified; the parameter should be ignored.
B'010' The region code is specified using a two-character region identifier (ISO 3166
Alpha-2 code) defined in ISO 3166-1.
B'011' The region code is specified using a three-character region identifier (ISO
3166 Alpha-3 code) defined in ISO 3166-1. [MODCA-6-769]
All
others
Reserved
LangCde identifies the language. The language code is left-justified and padded on the right with the
null (U+0000) character. The encoding is UTF-16BE. Sample language codes are:
Code Language
chi Chinese
eng English
fre French
Triplet X'8C'


ger German
jpn Japanese
kor Korean
vie Vietnamese
ScrptCde identifies the script. The encoding is UTF-16BE. Sample script codes are:
Code Script
Latn Latin
Cyrl Cyrillic
Armn Armenian
Hebr Hebrew
Arab Arabic
RegCde identifies the region. The region code is left-justified and padded on the right with the null (U
+0000) character. The encoding is UTF-16BE. Sample region codes are:
Code Region
CHN China
DEU Germany
JPN Japan
PRK Korea, Democratic People's Republic of
KOR Korea, Republic of
USA United States
VNM Vietnam
VarCde specifies an optional application-specific variant code. The encoding is UTF-16BE. The variant
code is an additional qualifier that can be added to the language code and region code to
further identify the locale. An example of a variant code is 'EURO' to specify support of the
Euro currency in the locale. [MODCA-6-770]
### Structured Field Using Triplet X'8C'
• “Map Data Resource (MDR)” [MODCA-6-771]
Triplet X'8C'


UP3i Finishing Operation Triplet X'8E'
The UP3i Finishing Operation triplet is used to specify finishing operations that are to be applied to media.
More specifically, this triplet is a carrier for finishing operations and parameters that are defined by the UP3i
consortium in the UP3i Specification. [MODCA-6-772]
#### Triplet X'8E' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-773]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-774]|
| 0 UBIN Tlength 13–254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-775]|
| 1 CODE Tid X'8E' Identifies the UP3i Finishing | | | | | | Operation triplet M X'00' [MODCA-6-776]|
| 2 | UBIN | Seqnum | | X'00'-X'FF' Sequence number | M | X'06' [MODCA-6-777]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-778]|
| 4–n UP3iDat Finishing operation data as | | | | | | defined in the UP3i Specification; this parameter contains bytes 4– end of the UP3i Form Finishing Operating (X'03') triplet M X'06' [MODCA-6-779]|
#### Triplet X'8E' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the UP3i Finishing Operation triplet.
SeqNum Specifies the sequence number of this triplet. This parameter is used to distinguish otherwise
identical X'8E' triplets.
UP3iDat Specifies finishing operations and parameters defined by the UP3i consortium. This parameter
contains bytes 4–end of the UP3i Form Finishing Operating (X'03') triplet. At least bytes 4–12
of the UP3i Form Finishing Operating (X'03') triplet are mandatory and must be specified for
the UP3iDat parameter; additional bytes are optional. The semantics of the bytes are defined
by the UP3i Specification. For a definition of the UP3i Form Finishing Operating (X'03') triplet,
see the current UP3i Specification. This specification is available at:
www.afpcinc.org.
### Structured Field Using Triplet X'8E'
• “Medium Finishing Control (MFC)” [MODCA-6-780]
Triplet X'8E'


MO:DCA Function Set Triplet X'8F'
The MO:DCA Function Set triplet is used to specify the registered value of a MO:DCA Function Set. [MODCA-6-781]
#### Triplet X'8F' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-782]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-783]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-784]|
| 1 CODE Tid X'8F' Identifies the MO:DCA Function | | | | | | Set triplet M X'00' [MODCA-6-785]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-786]|
| 4–5 CODE FctSetID X'0001' Specifies the MO:DCA Function | | | | | | Set ID: X'0001' MO:DCA GA M X'06' [MODCA-6-787]|
#### Triplet X'8F' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the MO:DCA Function Set triplet.
FctSetID Is a code which specifies the registered value of a MO:DCA Function Set. For a list and
description of the registered function set values see Chapter 8, “MO:DCA Function Sets”, on
page 517.
X'0001' MO:DCA GA
### Structured Fields Using Triplet X'8F'
• “Begin Document (BDT)” [MODCA-6-788]
• “Begin Print File (BPF)” [MODCA-6-789]
Triplet X'8F'


### Color Management Resource Descriptor Triplet X'91'
The Color Management Resource Descriptor triplet specifies the processing mode and scope for a Color
Management Resource (CMR). [MODCA-6-790]
#### Triplet X'91' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-791]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-792]|
| 0 UBIN Tlength 5 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-793]|
| 1 CODE Tid X'91' Identifies the Color Management | | | | | | Descriptor triplet M X'00' [MODCA-6-794]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-795]|
| 3 CODE ProcMode X'01'–X'03' Specifies the processing mode | | | | | | for the CMR: X'01' Process the CMR as an audit CMR X'02' Process the CMR as an instruction CMR X'03' Process the CMR as a link CMR; valid only for Link DL CMRs M X'06' [MODCA-6-796]|
| 4 CODE CMRScpe X'01'–X'05' Specifies the scope of the CMR: | | | | | | X'01' Scope of CMR is a data object X'02' Scope of CMR is a page or overlay X'03' Scope of CMR is a document X'04' Scope of CMR is a print file X'05' Scope of CMR is a page/sheet group M X'06' [MODCA-6-797]|
#### Triplet X'91' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Color Management Resource Descriptor triplet.
ProcMode Specifies the processing mode for the CMR. Valid values are the following:
Value Description
X'01' This CMR describes processing that has been done to a document
component; process the CMR as an audit CMR.
X'02' This CMR describes processing that needs to be done to a document
component; process the CMR as an instruction CMR.
X'03' This CMR defines a direct color conversion from an input color space to a
device output color space; process the CMR as a link CMR. This processing
mode is only valid for Link DL CMRs.
All others Reserved
Triplet X'91'


CMRScpe Specifies the scope of the CMR when used inside a document. Valid values are the following:
Value Description
X'01' The scope of the CMR is a data object.
X'02' The scope of the CMR is a page or overlay.
X'03' The scope of the CMR is a document.
X'04' The scope of the CMR is a print file.
X'05' The scope of the CMR is a page/sheet group.
All others Reserved
### Structured Fields Using Triplet X'91'
• “Include Object (IOB)” [MODCA-6-798]
• “Map Data Resource (MDR)” [MODCA-6-799]
• “Preprocess Presentation Object (PPO)” [MODCA-6-800]
Triplet X'91'


### Rendering Intent Triplet X'95'
The Rendering Intent triplet specifies the rendering intent parameter, which is used to modify the final
appearance of color data. This parameter is based on the rendering intents defined by the International Color
Consortium (ICC). For more information on rendering intents, see ISO 15076-1:2010 “Image technology colour
management – Architecture, profile format and data structure – Part 1: Based on ICC.1:2010”. [MODCA-6-801]
#### Triplet X'95' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-802]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-803]|
| 0 UBIN Tlength 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-804]|
| 1 CODE Tid X'95' Identifies the Rendering Intent | | | | | | triplet M X'00' [MODCA-6-805]|
| 2 | - | 3 | | Reserved; should be zero | M | X'06' [MODCA-6-806]|
| 4 CODE IOCARI X'00'–X'03', X'FF' Rendering intent for IOCA | | | | | | objects: X'00' perceptual X'01' media-relative colorimetric X'02' saturation X'03' ICC-absolute colorimetric X'FF' not specified M X'06' [MODCA-6-807]|
| 5 CODE OCRI X'00'–X'03', X'FF' Rendering intent for container | | | | | | (non–OCA) objects: X'00' perceptual X'01' media-relative colorimetric X'02' saturation X'03' ICC-absolute colorimetric X'FF' not specified M X'06' [MODCA-6-808]|
| 6 CODE PTOCARI X'00'–X'03', X'FF' Rendering intent for PTOCA | | | | | | texts: X'00' perceptual X'01' media-relative colorimetric X'02' saturation X'03' ICC-absolute colorimetric X'FF' not specified M X'06' [MODCA-6-809]|
| 7 CODE GOCARI X'00'–X'03', X'FF' Rendering intent for AFP GOCA | | | | | | objects: X'00' perceptual X'01' media-relative colorimetric X'02' saturation X'03' ICC-absolute colorimetric X'FF' not specified M X'06' [MODCA-6-810]|
| 8 | - | 9 | | Reserved; should be zero | M | X'06' Triplet X'95' [MODCA-6-811]|


#### Triplet X'95' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Rendering Intent triplet.
IOCARI Specifies the rendering intent for IOCA objects. Valid values are the following. The same
values also apply to the OCRI, PTOCARI, and GOCARI parameters.
Value Description
X'00' Perceptual. Gamut mapping is vendor-specific, and colors are adjusted to
give a pleasing appearance. This intent is typically used to render continuous-
tone images.
X'01' Media-relative colorimetric. In-gamut colors are rendered accurately, and
out-of-gamut colors are mapped to the nearest value within the gamut. Colors
are rendered with respect to the source white point and are adjusted for the
media white point. Therefore colors printed on two different media with
different white points won't match colorimetrically, but may match visually.
This intent is typically used for vector graphics.
X'02' Saturation. Gamut mapping is vendor-specific, and colors are adjusted to
emphasize saturation. This intent results in vivid colors and is typically used
for business graphics.
X'03' ICC-absolute colorimetric. In-gamut colors are rendered accurately, and
out-of-gamut colors are mapped to the nearest value within the gamut. Colors
are rendered only with respect to the source white point, and are not adjusted
for the media white point. Therefore colors printed on two different media with
different white points should match colorimetrically, but may not match
visually. This intent is typically used for logos.
X'FF' The rendering intent is not specified.
All others Reserved
OCRI Specifies the rendering intent for non-OCA objects that are carried in an object container or
that are referenced as object containers. The same rendering intent values that are defined for
IOCARI apply.
PTOCARI Specifies the rendering intent for PTOCA text. The same rendering intent values that are
defined for IOCARI apply.
GOCARI Specifies the rendering intent for AFP GOCA objects. The same rendering intent values that
are defined for IOCARI apply.
If a rendering intent is not specified for a document component, a rendering intent specified at a higher level in
the MO:DCA document hierarchy is applied in accordance with normal MO:DCA hierarchy rules. For example,
if a rendering intent is not specified at the data object level, the next higher level, which is the page/overlay
level, is searched, and so on. Note that the rendering intent at the data object level includes rendering intent
information embedded in the data object, although such embedded information is overridden by a X'95' triplet
at this level or a Rendering Intent table vector in the data object RAT . If a rendering intent has not been
specified anywhere in the document hierarchy, the “preferred” rendering intent specified in the active
instruction Color Conversion CMR, which is the same as the default rendering intent specified in the
corresponding active Link Color Conversion CMR, is used.
Architecture Notes:
1. The rendering intent for bar code (BCOCA) objects and for IM-image objects cannot be specified with the [MODCA-6-812]
Rendering Intent triplet and is fixed as media-relative colorimetric.
Triplet X'95'


2. The rendering intent for object area coloring is determined by the rendering intent of the data object that is [MODCA-6-813]
defined on that presentation space. The rendering intent for page/overlay presentation space coloring is
determined by the PTOCA rendering intent for the page/overlay. [MODCA-6-814]
### Structured Fields Using Triplet X'95'
• “Include Object (IOB)” [MODCA-6-815]
• “Presentation Environment Control (PEC)” [MODCA-6-816]
• “Preprocess Presentation Object (PPO)” [MODCA-6-817]
Triplet X'95'


### CMR Tag Fidelity Triplet X'96'
The CMR T ag Fidelity triplet is used to specify the exception continuation and reporting rules for Color
Management Resource (CMR) tag exceptions. A CMR tag exception is detected when an unsupported CMR
tag is encountered in a Color Management Resource (CMR).
Architecture Note: The purpose of the CMR T ag Fidelity triplet is to allow the CMR architecture to be
extended with additional tags in the future without necessarily having these new tags cause exceptions
in printers that do not support the new tags. [MODCA-6-818]
#### Triplet X'96' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-819]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-820]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-821]|
| 1 CODE Tid X'96' Identifies the CMR T ag Fidelity | | | | | | triplet M X'00' [MODCA-6-822]|
| 2 CODE StpCMREx X'01'–X'02' CMR tag exception continuation | | | | | | rule: X'01' Stop presentation at point of first CMR tag exception and report exception X'02' Do not stop presentation because of CMR tag exceptions; ignore tag and continue processing CMR tags M X'06' [MODCA-6-823]|
| 3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-824]|
| 4 CODE RepCMREx X'01'–X'02' CMR tag exception reporting rule | | | | | | if exception does not stop presentation: X'01' Report CMR tag exception X'02' Do not report CMR tag exception M X'06' [MODCA-6-825]|
| 5–6 | Reserved; | should | | be zero | M | X'06' [MODCA-6-826]|
#### Triplet X'96' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the T ext Fidelity triplet.
StpCMREx Is a parameter that specifies whether presentation should be continued when a CMR tag
exception is detected. Valid values are:
Value Description
X'01' Stop presentation at the point of the first CMR tag exception. A CMR tag
exception that stops presentation must be reported.
Triplet X'96'


Application Note: When presentation is terminated, the print file is put into a
state where it can be resubmitted when the CMR can be processed
without exceptions.
X'02' Do not stop presentation because of CMR tag exceptions; ignore tag and
continue processing CMR tags.
All others Reserved
RepCMREx Is a parameter that specifies whether CMR tag exceptions should be reported if they do not
stop presentation. Valid values are:
Value Description
X'01' Report CMR tag exceptions that do not stop presentation.
X'02' Do not report CMR tag exceptions that do not stop presentation.
All others Reserved
Implementation Note: The following rules describe how AFP servers process the CMR T ag Fidelity triplet with
printers that support CMRs but that may or may not support this triplet. Note that a printer that does not
support CMRs will not generate a CMR tag exception and therefore will not cause this triplet to be
processed:
• If the CMR T ag Fidelity triplet is specified and is supported by the printer, the triplet is sent to the [MODCA-6-827]
printer and processed by both server and printer. If StpCMREx = X'02' and a CMR tag exception is
detected, the CMR tag that generated the exception is skipped or processed in non-optimal fashion
and processing continues with the next CMR tag.
• If the CMR T ag Fidelity triplet is specified and is not supported by the printer, the triplet is processed by [MODCA-6-828]
the server. CMR tag exceptions will flow from the printer to the server. If StpCMREx = X'02' and a
CMR tag exception is detected, printing continues after the printer chooses an appropriate substitute
CMR in place of the CMR that caused the CMR tag exception.
• If the CMR T ag Fidelity triplet is not specified, presentation system defaults determine how CMR tag [MODCA-6-829]
exceptions are handled. [MODCA-6-830]
### Structured Field Using Triplet X'96'
• “Presentation Fidelity Control (PFC)” [MODCA-6-831]
Triplet X'96'


### Device Appearance Triplet X'97'
The Device Appearance triplet specifies one of a set of architected appearances to be assumed by the
presentation device.
#### Triplet X'97' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-832]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-833]|
| 0 UBIN Tlength 7 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-834]|
| 1 CODE Tid X'97' Identifies the Device Appearance | | | | | | triplet M X'00' [MODCA-6-835]|
| 2 | Reserved; | should | | be zero | M | X'06' [MODCA-6-836]|
| 3 - 4 CODE DevApp X'0000'–X'0001' Specifies the appearance to be | | | | | | assumed by the device: X'0000' Device default appearance X'0001' Device default monochrome appearance M X'06' [MODCA-6-837]|
| 5 | - | 6 | | Reserved; should be zero | M | X'06' [MODCA-6-838]|
#### Triplet X'97' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Device Appearance triplet.
DevApp Specifies the output appearance to be generated by the presentation device. Valid values are
the following:
Value Description
X'0000' Device default appearance. The device assumes its normal appearance. For
example, a process-color printer generates full color output.
X'0001' Device default monochrome appearance. The device assumes a
monochrome appearance such that the device's default color is used for
presentation. The device can simulate color values with grayscale using the
default color, or it can simulate color values by simply substituting the default
color, or it can use some combination of the two.
All others Reserved
Architecture Note: The IPDS architecture defines the minimal set of functions that must be supported by a
printer for AFP color management. Support for the Device Appearance (X'97') triplet with DevApp =
X'0000' specifies the resolution of a raster image(device default appearance) is part of this set; however
support for additional device appearances is optional. [MODCA-6-839]
### Structured Field Using Triplet X'97'
• “Presentation Environment Control (PEC)” [MODCA-6-840]
Triplet X'97'


### Image Resolution Triplet X'9A'
The Image Resolution triplet specifies the resolution of a raster image. [MODCA-6-841]
#### Triplet X'9A' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-842]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-843]|
| 0 UBIN Tlength 10 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-844]|
| 1 CODE Tid X'9A' Identifies the Image Resolution | | | | | | triplet M X'00' [MODCA-6-845]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-846]|
| 4 CODE XBase X'00'–X'01' Unit base for image resolution in | | | | | | the X direction: X'00' 10 inches X'01' 10 centimeters M X'06' [MODCA-6-847]|
| 5 CODE YBase X'00'–X'01' Unit base for image resolution in | | | | | | the Y direction: X'00' 10 inches X'01' 10 centimeters M X'06' [MODCA-6-848]|
| 6–7 UBIN XResol 1–32,767 Number of image points in X | | | | | | direction per X unit base M X'06' [MODCA-6-849]|
| 8–9 UBIN YResol 1–32,767 Number of image points in Y | | | | | | direction per Y unit base M X'06' [MODCA-6-850]|
#### Triplet X'9A' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Image Resolution triplet.
XBase Specifies the unit base for the image resolution in the X direction.
YBase Specifies the unit base for the image resolution in the Y direction.
Note: A X'01' exception condition exists if the XBase and YBase values are not identical.
XResol Specifies the resolution of the image in the X direction in number of image points per X-
direction unit base.
YResol Specifies the resolution of the image in the Y direction in number of image points per Y-
direction unit base.
Architecture Note: The presentation space size of a raster image, such as an image in TIFF format, is
determined by two parameters - (1) the pixel count in the x and y directions, and (2) the resolution of the
pixels in the x and y directions. The use of these two parameters when presenting an image depends on
the mapping option that is in effect:
• When the mapping option is scale-to-fill, the pixel counts are sufficient since the intent is to scale the [MODCA-6-851]
complete raster into the object area.
• When the mapping option is scale-to-fit, if the resolution in the x and y directions is the same, the pixel [MODCA-6-852]
counts are also sufficient since again the intent is to scale the complete raster into the object area and
thus the physical dimensions of the image are unimportant. However, if the resolution in the x and y
Triplet X'9A'


directions are different, then both the pixel counts and the resolutions are needed to define the
physical dimensions of the image that will be scaled.
• When the mapping option is position, position-and-trim, or center-and-trim, both the pixel counts and [MODCA-6-853]
the resolutions are needed to define the physical dimensions of the image, since the intent is to render
a portion of the image at its native size into the object area. [MODCA-6-854]
### Structured Fields Using Triplet X'9A'
• “Container Data Descriptor (CDD)” [MODCA-6-855]
• “Include Object (IOB)” [MODCA-6-856]
• “Preprocess Presentation Object (PPO)” [MODCA-6-857]
Triplet X'9A'


### Object Container Presentation Space Size Triplet X'9C'
The Object Container Presentation Space Size triplet specifies the presentation space size, or how such a size
is determined, for certain container object types. [MODCA-6-858]
#### Triplet X'9C' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-859]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-860]|
| 0 UBIN Tlength 5, 17 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-861]|
| 1 CODE Tid X'9C' Identifies the Object Container | | | | | | Presentation Space Size triplet M X'00' [MODCA-6-862]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-863]|
| 4 CODE PDFSize X'01'–X'05' Parameter used to determine the | | | | | | PDF presentation space size: X'01' MediaBox X'02' CropBox X'03' BleedBox X'04' TrimBox X'05' ArtBox M X'06' [MODCA-6-864]|
| 5 CODE XocBase X'00'–X'01' Presentation space size unit | | | | | | base for the width (along X axis): X'00' 10 inches X'01' 10 centimeters O X'02' [MODCA-6-865]|
| 6 CODE YocBase X'00'–X'01' Presentation space size unit | | | | | | base for the height (along Y axis): X'00' 10 inches X'01' 10 centimeters O X'02' [MODCA-6-866]|
| 7–8 UBIN XocUnits 1–32,767 Presentation space size units per | | | | | | unit base for the width (along X axis) O X'02' [MODCA-6-867]|
| 9–10 UBIN YocUnits 1–32,767 Presentation space size units per | | | | | | unit base for the height (along Y axis) O X'02' [MODCA-6-868]|
| 11–13 UBIN XpsSize 1–32,767 Presentation space width (extent | | | | | | along X axis) O X'02' [MODCA-6-869]|
| 14–16 UBIN YpsSize 1–32,767 Presentation space height | | | | | | (extent along Y axis) O X'02' Bytes 5–16 are optional as a unit; that is, either all are specified or none are specified. [MODCA-6-870]|
#### Triplet X'9C' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Object Container Presentation Space Size triplet.
PDFSize Specifies how the presentation space size is determined for all PDF presentation object types:
• Portable Document Format (PDF) single page [MODCA-6-871]
• Portable Document Format (PDF) single page with transparency [MODCA-6-872]
Triplet X'9C'


• PDF Multiple Page File [MODCA-6-873]
• PDF Multiple Page - with Transparency - File [MODCA-6-874]
It is ignored for all other object types. The following values are defined:
X'01' Use the size specified by the MediaBox parameter
X'02' Use the size specified by the CropBox parameter
X'03' Use the size specified by the BleedBox parameter
X'04' Use the size specified by the TrimBox parameter
X'05' Use the size specified by the ArtBox parameter
If this triplet is not specified and if the Object Container Presentation Space Size TV is not
specified in the RAT for the object, the architected default is X'01' - MediaBox. This is a
mandatory parameter in PDF . If this triplet is specified or if the Object Container Presentation
Space Size TV is specified, but the selected size parameter is not specified in the PDF object,
the PDF default mechanism is used to select the presentation space size.
Architecture Notes:
1. In addition to specifying the presentation space size, this parameter also indicates the [MODCA-6-875]
PDF box that the PDF should be rendered to.
2. As specified in the PDF specification, if the CropBox, BleedBox, TrimBox, or ArtBox [MODCA-6-876]
parameters extend beyond the boundaries of the MediaBox, their definition is redefined to
be their intersection with the MediaBox. Thus the presentation space size is reduced to the
size of the intersection, and the reduced box is what is rendered to.
XocBase Specifies the unit base for determining the width of the presentation space (along the X axis).
YocBase Specifies the unit base for determining the height of the presentation space (along the Y axis).
Note: A X'01' exception condition exists if the XocBase and YocBase values are not identical.
XocUnits Specifies the number of units per unit base for determining the width of the presentation space
(along the X axis).
YocUnits Specifies the number of units per unit base for determining the height of the presentation
space (along the Y axis).
XpsSize Specifies the width (extent along the X axis) of the presentation space.
YpsSize Specifies the height (extent along the Y axis) of the presentation space.
The XocBase, YocBase, XocUnits, YocUnits, XpsSize, and YpsSize parameters are optional as a unit, that is,
either all are specified or none are specified. These parameters are ignored for all PDF presentation object
types.
### Structured Fields Using Triplet X'9C'
• “Container Data Descriptor (CDD)” [MODCA-6-877]
• “Include Object (IOB)” [MODCA-6-878]
• “Preprocess Presentation Object (PPO)” [MODCA-6-879]
Triplet X'9C'


### Keep Group Together Triplet X'9D'
The Keep Group T ogether triplet indicates that a group of pages is a complete logical entity that should be
processed as a unit for the purpose indicated. [MODCA-6-880]
#### Triplet X'9D' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-881]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-882]|
| 0 UBIN Tlength 5 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-883]|
| 1 CODE Tid X'9D' Identifies the Keep Group | | | | | | T ogether triplet M X'00' [MODCA-6-884]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-885]|
| 4 CODE GrpFnct X'01' Specifies the purpose of the | | | | | | grouping: X'01' Keep group together as a recovery unit M X'06' [MODCA-6-886]|
#### Triplet X'9D' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Keep Group T ogether triplet.
GrpFcnt Specifies the purpose of the grouping. The following values are defined:
X'01' Keep group together as a recovery unit. Process the page group as a unit for
error recovery purposes, such as in cases of a printer recovery from an error
that occurs in the middle of the group.
All others Reserved
### Structured Field Using Triplet X'9D'
• “Begin Named Page Group (BNG)” [MODCA-6-887]
Triplet X'9D'


### Setup Name Triplet X'9E'
The Setup Name triplet specifies a setup name that encompasses some number of settings on a device. The
name is created by a user of the device and must be coordinated with the presentation systems that use the
device; the specific implementation of setup names might differ between devices. [MODCA-6-888]
#### Triplet X'9E' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-889]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-890]|
| 0 UBIN Tlength 6-204 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-891]|
| 1 | CODE | Tid | | X'9E' Identifies the Setup Name triplet | M | X'00' [MODCA-6-892]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-893]|
| 4–n CHAR SetupName Setup name; encoding is UTF- | | | | | | 16BE M X'00' [MODCA-6-894]|
#### Triplet X'9E' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Setup Name triplet.
SetupName Specifies a setup name specified as a UTF-16BE character string.
The name is not syntax checked for proper UTF-16 data; however, an ill-formed name is
unlikely to match any device-supported setup name. [MODCA-6-895]
### Structured Field Using Triplet X'9E'
• “Presentation Environment Control (PEC)” [MODCA-6-896]
Triplet X'9D'


### Triplet Extender Triplet X'FF'
The Triplet Extender triplet is used to extend the data portion of the preceding triplet. [MODCA-6-897]
#### Triplet X'FF' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-6-898]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-6-899]|
| 0 UBIN Tlength 5-254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-6-900]|
| 1 CODE Tid X'FF' Identifies the Triplet Extender | | | | | | triplet M X'00' [MODCA-6-901]|
| 2-3 | Reserved; | should | | be zero | M | X'06' [MODCA-6-902]|
| 4–n CODE DatExt Data bytes that extend the data | | | | | | field of the previous triplet M X'06' [MODCA-6-903]|
#### Triplet X'FF' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Triplet Extender triplet.
DatExt Specifies bytes to be appended directly to the data field of the preceding triplet. [MODCA-6-904]
### Structured Fields Using Triplet X'FF'
• “Include Object (IOB)” [MODCA-6-905]
• “Map Data Resource (MDR)” [MODCA-6-906]
• “Preprocess Presentation Object (PPO)” [MODCA-6-907]
Triplet X'FF'


