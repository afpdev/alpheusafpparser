Chapter 7. MO:DCA Interchange Sets
This chapter:
• Describes compliance in terms of interchange sets [MODCA-7-001]
• Outlines MO:DCA compliance rules [MODCA-7-002]
• Provides complete syntactic and semantic descriptions of [MODCA-7-003]
– MO:DCA Presentation Interchange Set 1
– MO:DCA Presentation Interchange Set 3
• References the MO:DCA AFP Archive (AFP/A) interchange set, which is defined in the ISO 18565:2015 [MODCA-7-004]
“Document management – AFP/Archive” standard. Refer to this standard for a complete definition of AFP/A.
Interchange Sets
An interchange set is a constrained version of the general MO:DCA architecture usually aimed at both
progression and interoperability. A new interchange set definition is typically warranted based on a major new
feature or when enough overall new function has been added to the architecture that a new compliance target
is needed to align implementations. Note that a new interchange set definition may also deprecate architecture
that has been superseded or which has never materialized in terms of implementation or, in practice, ended up
the subject of a very narrow market segment.
Function sets provide a means for incrementally modifying an interchange set, often in the interim. A function
set may address a specific market or business need or may represent a universal alteration while there is
insufficient motivation for a new interchange set; see Chapter 8, “MO:DCA Function Sets”,.
This edition of the Mixed Object Document Content Architecture (MO:DCA) Reference contains detailed
descriptions of two MO:DCA interchange sets: MO:DCA IS/1 and MO:DCA IS/3. Note that MO:DCA
Interchange Set 2 (MO:DCA IS/2) has been retired; see “Retired Interchange Set”. Note also that
the MO:DCA AFP Archive (AFP/A) interchange set is not defined in this Reference. See the ISO 18565:2015
“Document management – AFP/Archive” standard for a definition of this interchange set.
While an interchange set cannot be defined that violates the overall MO:DCA architecture, the interchange set
definition can include restrictions that are not part of the overall architecture. These restrictions may limit:
• What structured fields may or must appear [MODCA-7-005]
• Where the structured fields may or must appear [MODCA-7-006]
• The order in which the structured fields may or must appear [MODCA-7-007]
• What structured field parameters may or must appear [MODCA-7-008]
• The order in which the structured field parameters may or must appear [MODCA-7-009]
• What structured field parameter values may or must appear [MODCA-7-010]


Interchange Set Compliance Requirements
Two general classes of products may claim compliance with an interchange set, as follows:
Generator Any product that is capable of producing print files containing a valid subset of the interchange
set. A valid subset of an interchange set is one in which all generated structured fields belong
to the interchange set and comply with all of its ordering and pairing requirements, and all
parameter values fall within the ranges specified by the interchange set.
Receiver Any product that is capable of properly interpreting all MO:DCA structured fields in print files
that are compliant with the interchange set.
Note that products, such as transforms, may act as both a generator and a receiver.
All products should identify, within their product documentation, which interchange set(s) they support. If a
product supports one or more function sets in addition to an interchange set, those function sets should also be
identified in the product documentation.
Specific interchange sets may have additional compliance rules. See the specific interchange set definition for
more information.
Note: The primary intent of the MO:DCA architecture is the interchange of data among products that support
one or more defined interchange sets. However, products may also use MO:DCA data streams for their
own private use or for data exchange with other known products.
Compliance Requirements [MODCA-7-011]


### MO:DCA Interchange Set 1
This section defines the MO:DCA Interchange Set 1 (MO:DCA IS/1) used for presentation documents.
For information on the level of function required for the OCAs included in this interchange set, refer to the
MO:DCA environment appendix in the following AFP documents:
GOCA Graphics Object Content Architecture for AFP Reference
IOCA Image Object Content Architecture Reference
PTOCA Presentation Text Object Content Architecture Reference
Data Stream Syntax Structure
The groupings of MO:DCA structured fields that follow identify those structured fields which appear within each
begin-end structured field pair or state. This section specifies the structured fields allowed within a MO:DCA
Presentation Interchange Set 1 data stream. It shows the MO:DCA state hierarchy and the validity of
structured fields within each state.
If a structured field that is not identified as being part of this interchange set appears anywhere within the data
stream, a X'40' exception condition exists. If a structured field appears within any state where it is not
permitted, or if it appears out of the stated order or more than the permitted number of times, a X'20' exception
condition exists. If a structured field that is identified as required does not appear within a specific state, a X'08'
exception condition exists.
The conventions used in these structured field groupings are:
( ) The structured field acronym and identifier are shown in parentheses. The presence of dots or periods
in the identifier indicates that the item is not a structured field, but instead is a structure, for example a
page. The structure is composed of an assortment of structured fields, and is defined separately.
[ ] Brackets indicate optional structured fields. When a structured field is shown without brackets, it must
appear between the begin and end structured fields.
+ Plus signs indicate structured fields may appear in any order relative to those that precede or succeed
it except when the preceding or succeeding structured field does not have a plus (+) sign. Then the
order is as listed.
(S) The enclosed (S) indicates that the structured field may be repeated. When it is present on a required
structured field, at least one occurrence of the structured field is required, but multiple instances of it
may occur.
F2 An F2 indicates that the structured field is a format two structured field. See “Structured Field Formats”
for further details.
Notes:
1. The Begin Document and End Document structured fields are required in a MO:DCA data stream. [MODCA-7-012]
2. The No Operation structured field may appear within any begin-end domain and thus is not listed in the [MODCA-7-013]
structured field groupings.
3. The architecture that owns and controls the content of each of the data and resource objects carried in a [MODCA-7-014]
MO:DCA data stream is identified in the following structured field groupings. Please refer to the referenced
documentation for further details.
4. The Flag byte (byte 5) in the Structured Field Introducer (SFI) must be set to X'00'. MO:DCA IS/1 does not [MODCA-7-015]
support SFI extension, structured field segmentation, or structured field padding.
MO:DCA IS/1


Document
Figure 79. MO:DCA IS/1: Document Structure
Begin Document (BDT, D3A8A8)
+ [ (IMM, D3ABCC) Invoke Medium Map (S) ]
+ [ ( D3..AF) Page (S) ]
End Document (EDT, D3A9A8)
Page
Figure 80. MO:DCA IS/1: Page Structure
Begin Page (BPG, D3A8AF)
( D3..C9) Active Environment Group
+ [ ( D3..BB) Graphics Object (S) ]
+ [ ( D3..FB) Image Object (S) ]
+ [ (IPO, D3AFD8) Include Page Overlay (S) ]
+ [ ( D3..9B) Presentation Text Object (S) ]
End Page (EPG, D3A9AF)
Active Environment Group (AEG)
Figure 81. MO:DCA IS/1: Active Environment Group Structure
Begin Active Environment Group (BAG, D3A8C9)
[ (MCF, D3AB8A) Map Coded Font F2 (S) ] 2
[ (MPO, D3ABD8) Map Page Overlay (S) ] 1
(PGD, D3A6AF) Page Descriptor
[ (OBD, D3A66B) Object Area Descriptor ] 3
[ (OBP, D3AC6B) Object Area Position ] 3
(PTD, D3B19B) Presentation Text Data Descriptor F2 4
End Active Environment Group (EAG, D3A9C9)
Notes:
1. For purposes of print server resource management, each overlay included on a page with an IPO must first [MODCA-7-016]
be mapped to a local ID with an MPO in the AEG for that page. Note that the MPO is only specified in the
AEG for a page; it is not allowed in the AEG for an overlay.
2. For purposes of print server resource management, an MCF mapping the same font must be specified in [MODCA-7-017]
the AEG whenever an MCF is specified in a graphics OEG. The local ID used in the page or overlay AEG
need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG
solely due to their presence in an object's OEG.
3. Used for presentation text objects only and is optional. For graphics and image objects, the OBD and OBP [MODCA-7-018]
must be specified in the OEG associated with the graphic or image object.
4. Required only when the associated page contains one or more presentation text objects. [MODCA-7-019]
MO:DCA IS/1


Graphics Object (GOCA DR/2V0)
Figure 82. MO:DCA IS/1: Graphics Object Structure
Begin Graphics Object (BGR, D3A8BB)
( D3..C7) Object Environment Group
[ (GAD, D3EEBB) Graphics Data (S) ]
End Graphics Object (EGR, D3A9BB)
Note: Refer to the Graphics Object Content Architecture for AFP Reference for a full description of the GOCA
DR/2V0 content, syntax, and semantics for MO:DCA IS/1.
Object Environment Group (OEG) for Graphics Object
Figure 83. MO:DCA IS/1: Object Environment Group for Graphics Object Structure
Begin Object Environment Group (BOG, D3A8C7)
(OBD, D3A66B) Object Area Descriptor
(OBP, D3AC6B) Object Area Position
[ (MGO, D3ABBB) Map Graphics Object ]
[ (MCF, D3AB8A) Map Coded Font F2 ] 1
(GDD, D3A6BB) Graphics Data Descriptor
End Object Environment Group (EOG, D3A9C7)
Notes:
1. For purposes of print server resource management, an MCF mapping the same font must be specified in [MODCA-7-020]
the AEG whenever an MCF is specified in a graphics OEG. The local ID used in the page or overlay AEG
need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG
solely due to their presence in an object's OEG.
Image Object (IOCA FS10)
Figure 84. MO:DCA IS/1: Image Object Structure
Begin Image Object (BIM, D3A8FB)
( D3..C7) Object Environment Group
[ (IPD, D3EEFB) Image Picture Data (S) ]
End Image Object (EIM, D3A9FB)
Note: Refer to the Image Object Content Architecture Reference for a full description of the IOCA FS10
content, syntax, and semantics for MO:DCA IS/1.
Object Environment Group (OEG) for Image Object
Figure 85. MO:DCA IS/1: Object Environment Group for Image Object Structure
Begin Object Environment Group (BOG, D3A8C7)
(OBD, D3A66B) Object Area Descriptor
(OBP, D3AC6B) Object Area Position
[ (MIO, D3ABFB) Map Image Object ]
(IDD, D3A6FB) Image Data Descriptor
End Object Environment Group (EOG, D3A9C7)
MO:DCA IS/1


Presentation Text Object (PTOCA PT1)
Figure 86. MO:DCA IS/1: Presentation Text Object Structure
Begin Presentation Text Object (BPT, D3A89B)
[ (PTX, D3EE9B) Presentation Text Data (S) ]
End Presentation Text Object (EPT, D3A99B)
Note: Refer to the Presentation Text Object Content Architecture Reference for a full description of the PTOCA
PT1 content, syntax, and semantics for MO:DCA IS/1.
Resource Syntax Structure
The following groupings of MO:DCA structured fields identify those structured fields which may/must appear
within the Begin/End structured field pair for each supported resource object. The same conventions used for
the data stream syntax structure apply.
Note: Only those resources that may be included from within the data stream are described here.
Overlay
Figure 87. MO:DCA IS/1: Overlay Structure
Begin Overlay (BMO, D3A8DF)
( D3..C9) Active Environment Group
+ [ ( D3..BB) Graphics Object (S) ]
+ [ ( D3..FB) Image Object (S) ]
+ [ ( D3..9B) Presentation Text Object (S) ]
End Overlay (EMO, D3A9DF)
Permitted Structured Fields
This section describes the parameters and ranges of values supported for each of the structured fields
contained in this interchange set.
The structured fields are listed alphabetically and described using tables. The table heading for each
structured field contains the structured field's acronym, its three-byte hexadecimal identifier, and its full name.
Also included is the page number in the document where a detailed description of the structured field can be
found.
Structured Field Parameters
In general, the structured field tables contain the following information for each parameter:
1. The offset from the beginning of the data portion of the structured field or from the beginning of the triplet. [MODCA-7-021]
2. Values and description: [MODCA-7-022]
• When a specific parameter value is required, the specific value or the range of acceptable values is [MODCA-7-023]
specified, followed by “→” and an explanation or description of the parameter.
• When no specific value is required, or when a choice of values is required, the parameter name or a [MODCA-7-024]
MO:DCA IS/1


description of the parameter is given. If a choice of values is required, the choices are identified in the
table.
3. For those parameters defined and owned by the MO:DCA architecture, occurrence is specified either as a [MODCA-7-025]
lowercase n indicating that the occurrence is unlimited by the interchange set, or as a number representing
the maximum number of times the parameter may appear within the containing structured field, repeating
group, or triplet.
4. For those parameters defined and owned by the MO:DCA architecture, optionally is specified as: [MODCA-7-026]
O Optional. The parameter may or may not appear.
M Mandatory. The parameter must always appear.
C Conditional. The parameter is mandatory under certain conditions, but is optional or not
allowed under other conditions.
Unless a specific order is required, self-identifying parameters are listed in alphanumeric sequence by
identifier and include the page number in the document where a detailed description of the parameter is
located.
In general, no exception conditions are identified within the interchange set definition for the structured fields or
their parameters. The page numbers provided for each structured field and each triplet provide the source for
determining what exception conditions may be anticipated. However, the following general rules apply:
• For those structured fields where a parameter order is stated, if a parameter appears outside that stated [MODCA-7-027]
order, a X'01' exception condition exists.
• If a parameter value appears that is outside the range specified for that parameter, a X'02' exception [MODCA-7-028]
condition exists.
• If a parameter that is identified as mandatory does not appear on a specific structured field, a X'04' exception [MODCA-7-029]
condition exists.
• Unless otherwise stated, if any unrecognized parameter or triplet appears on any structured field, a X'10' [MODCA-7-030]
exception condition exists.
Notes:
1. Any triplet encountered on any of the Begin structured fields listed below that is not explicitly defined as [MODCA-7-031]
being valid for that structured field should be ignored and should not cause an exception condition.
2. If specified, the name contained in the name parameter on an End structured field must match that [MODCA-7-032]
specified in the name parameter on its matching Begin structured field, or a X'01' exception condition
exists.
Begin Active Environment Group
BAG X'D3A8C9' Begin Active Environment Group (See “Begin Active Environment Group (BAG)”)
0–7 Active Environment Group name (8 characters) 1 O
Begin Document
BDT X'D3A8A8' Begin Document (See “Begin Document (BDT)”)
0–7 Document name (8 characters) 1 M
8–9 X'0000' → Reserved; must be binary zero 1 M
10–n The following triplets, in any order:
Coded Graphic Character Set Global Identifier Triplet (See “Coded Graphic Character
Set Global Identifier Triplet X'01'”)
1 M
MO:DCA IS/1


BDT X'D3A8A8' Begin Document (See “Begin Document (BDT)”)
0–1 X'0601' → Triplet length and identifier 1 M
2–5 Character set and code page identification 1 M
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 O
0–1 X'nn02' → Triplet length and identifier 1 M
2–3 X'0100' → FQN type and format. Replace first GID Name. 1 M
4–n Name of the document. It may be 1 to 250 bytes in length. 1 M
MO:DCA Interchange Set Triplet (See “MO:DCA Interchange Set Triplet X'18'”)
1 M
0–1 X'0518' → Triplet length and identifier 1 M
2 X'01' → Interchange set type, presentation 1 M [MODCA-7-033]
3–4 X'0900' → Interchange set identifier (MO:DCA IS/1) 1 M
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'” on
page 374)
1 C
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'02' → Object type, presentation text 1 M [MODCA-7-034]
3 X'00' → Architecture version 1 M [MODCA-7-035]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'0000' → Presentation text function set definition (PT/1) 1 M
8–n Reserved, not checked 1 O
Note: One and only one instance of this triplet is mandatory when the data stream contains a presentation text object. If
the data stream does not contain a presentation text object, this triplet should not appear.
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'” on
page 374)
1 C
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'03' → Object type, graphics 1 M [MODCA-7-036]
3 X'00' → Architecture version 1 M [MODCA-7-037]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'4000' → Graphics function set definition (DR/2V0) 1 M
8–n Reserved, not checked 1 O
Note: One and only one instance of this triplet is mandatory: when the data stream contains a graphics object. If the data
stream does not contain a graphics object, this triplet should not appear.
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'” on
page 374)
1 C
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'06' → Object type, image 1 M [MODCA-7-038]
3 X'00' → Architecture version 1 M [MODCA-7-039]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'8000' → Image function set definition (FS10) 1 M
MO:DCA IS/1


BDT X'D3A8A8' Begin Document (See “Begin Document (BDT)”)
8–n Reserved, not checked 1 O
Note: One and only one instance of this triplet is mandatory when the data stream contains an image object. If the data
stream does not contain an image object, this triplet should not appear.
Begin Graphics Object
BGR X'D3A8BB' Begin Graphics Object (See “Begin Graphics Object (BGR)”)
0–7 Graphics Object name (8 characters) 1 O
Begin Image Object
BIM X'D3A8FB' Begin Image Object (See “Begin Image Object (BIM)”)
0–7 Image Object name (8 characters) 1 O
Begin Object Environment Group
BOG X'D3A8C7' Begin Object Environment Group (See “Begin Object Environment Group (BOG)”)
0–7 Object Environment Group name (8 characters) 1 O
Begin Overlay
BMO X'D3A8DF' Begin Overlay (See “Begin Overlay (BMO)”)
0–7 Overlay name (8 characters) 1 M
Begin Page
BPG X'D3A8AF' Begin Page (See “Begin Page (BPG)”)
0–7 Page name (8 characters) 1 O
Begin Presentation Text Object
BPT X'D3A89B' Begin Presentation Text Object (See “Begin Presentation Text Object (BPT)”)
0–7 Presentation T ext Object name (8 characters) 1 O
End Active Environment Group
EAG X'D3A9C9' End Active Environment Group (See “End Active Environment Group (EAG)”)
0–7 Active Environment Group name (8 characters) 1 O
MO:DCA IS/1


End Document
EDT X'D3A9A8' End Document (See “End Document (EDT)”)
0–7 Document name (8 characters) 1 O
End Graphics Object
EGR X'D3A9BB' End Graphics Object (See “End Graphics Object (EGR)”)
0–7 Graphics Object name (8 characters) 1 O
End Image Object
EIM X'D3A9FB' End Image Object (See “End Image Object (EIM)”)
0–7 Image Object name (8 characters) 1 O
End Object Environment Group
EOG X'D3A9C7' End Object Environment Group (See “End Object Environment Group (EOG)”)
0–7 Object Environment Group name (8 characters) 1 O
End Overlay
EMO X'D3A9DF' End Overlay (See “End Overlay (EMO)”)
0–7 Overlay name (8 characters) 1 O
End Page
EPG X'D3A9AF' End Page (See “End Page (EPG)”)
0–7 Page name (8 characters) 1 O
End Presentation Text Object
EPT X'D3A99B' End Presentation Text Object (See “End Presentation Text Object (EPT)”)
0–7 Presentation T ext Object name (8 characters) 1 O
Graphics Data
GAD X'D3EEBB' Graphics Data (See “Graphics Data (GAD)”)
0–n Up to 8,192 bytes of graphics data as defined by GOCA DR/2V0
MO:DCA IS/1


Graphics Data Descriptor
GDD X'D3A6BB' Graphics Data Descriptor (See “Graphics Data Descriptor (GDD)”)
0–n Graphics descriptor data as defined by GOCA
Image Data Descriptor
IDD X'D3A6FB' Image Data Descriptor (See “Image Data Descriptor (IDD)”)
0–n Image descriptor data as defined by IOCA FS10
Image Picture Data
IPD X'D3EEFB' Image Picture Data (See “Image Picture Data (IPD)”)
0–n Up to 8,192 bytes of image segment data as defined by IOCA FS10
Include Page Overlay
IPO X'D3AFD8' Include Page Overlay (See “Include Page Overlay (IPO)”)
0–7 Page overlay reference name. 1 M
8–10 Page overlay origin, X-coordinate. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units
per inch for the page X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440
units per inch for the page X measurement
units
1 M
11–13 Page overlay origin, Y-coordinate. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units
per inch for the page Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440
units per inch for the page Y measurement
units
1 M
Invoke Medium Map
IMM X'D3ABCC' Invoke Medium Map (See “Invoke Medium Map (IMM)”)
0–7 External name of the medium map to be invoked (8 characters) 1 M
Map Coded Font, Format 2
MCF X'D3AB8A' Map Coded Font (See “Map Coded Font (MCF) Format 2”)
0–1 X'00nn' → Length of this repeating group 254 M
2–n The following triplets, in any order:
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”)
Note: See “MCF Font Names” for details.
2 M
MO:DCA IS/1


MCF X'D3AB8A' Map Coded Font (See “Map Coded Font (MCF) Format 2”)
0–1 X'0C02' → Triplet length and identifier 1 M
2 The FQN type. It must be one of the following: [MODCA-7-040]
X'84' → Coded Font Reference
X'85' → Code Page Reference
X'86' → Font Character Set Reference
1 M
3 X'00' → FQN format 1 M [MODCA-7-041]
4–11 External name of the coded font, code page, or font character set. 1 M
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 O
0–1 X'nn02' → Triplet length and identifier 1 M
2–3 X'0800' → FQN type and format, Font Typeface Name 1 M
4–n External name of the font typeface. It may be 1 to 32 bytes in length. 1 M
Font Descriptor Specification Triplet (See “Font Descriptor Specification Triplet X'1F'”
)
1 O
0–1 X'141F' → Triplet length and identifier 1 M
2 X'01'–X'09' → Font Weight Class. It must be in the range of 1 to 9. 1 M [MODCA-7-042]
3 X'01'–X'09' → Font Width Class. It must be in the range of 1 to 9. 1 M [MODCA-7-043]
4–5 X'0000'–X'7FFF' → Font Height. It must be in the range of 0 to 32,767 1440ths
of an inch.
1 M
6–7 X'0000'–X'7FFF' → Font Width. It must be in the range of 0 to 32,767 1440ths
of an inch.
1 M
8 Font Descriptor Flags, as follows: [MODCA-7-044]
Bit Description
0 Italics [MODCA-7-045]
1 Underscored [MODCA-7-046]
2 Reserved, must be B'0' [MODCA-7-047]
3 Hollow [MODCA-7-048]
4 Overstruck [MODCA-7-049]
5 Proportional [MODCA-7-050]
6 Kerned characters (pairwise) [MODCA-7-051]
7 Reserved, must be B'0' [MODCA-7-052]
1 M
9–19 Reserved 1 M
Font Coded Graphic Character Set Global Identifier Triplet (See “Font Coded Graphic
Character Set Global Identifier Triplet X'20'”)
1 O
0–1 X'0620' → Triplet length and identifier 1 M
2–5 The GCSGID and CPGID for the font 1 M
Resource Local Identifier Triplet (See “Resource Local Identifier Triplet X'24'”)
1 M
0–1 X'0424' → Architecture version 1 M
2 X'05' → Resource type, coded font 1 M [MODCA-7-053]
3 Resource Local Identifier. It must be one of the following: [MODCA-7-054]
X'01'–X'7F' → It must be in the range of 1 to 127 when
used for mapping a font.
X'FE' → It must be 254 when used for resource
management purposes in the AEG.
1 M
MO:DCA IS/1


MCF X'D3AB8A' Map Coded Font (See “Map Coded Font (MCF) Format 2”)
Resource Section Number Triplet (See “Resource Section Number Triplet X'25'” on
page 379)
1 O
0–1 X'0325' → Triplet length and identifier 1 M
2 Resource Section Number. It must be one of the following: [MODCA-7-055]
X'00' → It must be 0 when referencing an EBCDIC Presentation
single-byte coded font (encoding scheme ID X'61xx') or all
sections of an EBCDIC Presentation double-byte coded font
(encoding scheme ID X'62xx').
X'41'–X'FE' → It must be in the range of 65 to 254 when referencing a
specific section of an EBCDIC Presentation double-byte
coded font (encoding scheme ID X'62xx').
1 M
Character Rotation Triplet (See “Character Rotation Triplet X'26'”) 1 O
0–1 X'0426' → Triplet length and identifier 1 M
2–3 Character Rotation. It must be one of the following:
X'0000' → 0-degree character rotation
X'2D00' → 90-degree character rotation
X'5A00' → 180-degree character rotation
X'8700' → 270-degree character rotation
1 M
MCF Font Names
The MCF must have one of the following:
• A type X'84' (Coded Font Reference) Fully Qualified Name (X'02') triplet. T o support existing products, the [MODCA-7-056]
coded font name must be specified as a global resource identifier (GRID). For a definition of the GRID, see
“Global Resource Identifier (GRID) Definition”.
• Both a type X'85' (Code Page Name Reference) and a type X'86' (Font Character Set Name Reference) [MODCA-7-057]
Fully Qualified Name (X'02') triplet. T o support existing products, the names of the code page and font
character set must be eight characters in length and must match the external names of these objects in their
respective resource libraries.
Map Graphics Object
MGO X'D3ABBB' Map Graphics Object (See “Map Graphics Object (MGO)”)
0–1 X'0005' →Length of this repeating group is 5 bytes 1 M
2–4 The following triplet:
Mapping Option Triplet (See “Mapping Option Triplet X'04'”) 1 M
0–1 X'0304' →Triplet length and identifier 1 M
2 Output Option. It must be one of the following: [MODCA-7-058]
X'10' → Position and trim
X'20' → Scale to fit
X'30' → Center and trim
1 M
Note: If this structured field is not specified, the architected default is scale to fit.
MO:DCA IS/1


Map Image Object
MIO X'D3ABFB' Map Image Object (See “Map Image Object (MIO)”)
0–1 X'0005' →Length of this repeating group is 5 bytes 1 M
2–4 The following triplet:
Mapping Option Triplet (See “Mapping Option Triplet X'04'”) 1 M
0–1 X'0304' →Triplet length and identifier 1 M
2 Output Option. It must be one of the following: [MODCA-7-059]
X'10' → Position and trim
X'20' → Scale to fit
X'30' → Center and trim
1 M
Note: If this structured field is not specified, the architected default is scale to fit.
Map Page Overlay
MPO X'D3ABD8' Map Page Overlay (See “Map Page Overlay (MPO)”)
0–1 X'0012' →Length of this repeating group is 18 bytes 127 M
2–17 The following triplet:
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 M
0–1 X'0C02' →Triplet length and identifier 1 M
2–3 X'8400' →FQN type and format, reference to overlay 1 M
4–11 External name of the overlay. 1 M
Resource Local Identifier Triplet (See “Resource Local Identifier Triplet X'24'” on
page 378)
1 M
0–1 X'0424' →Triplet length and identifier 1 M
2 X'02' →Resource type, page overlay 1 M [MODCA-7-060]
3 X'01'–X'7F' →Resource Local Identifier. It must be in the range of 1 to 127. 1 M [MODCA-7-061]
No Operation
NOP X'D3EEEE' No Operation (See “No Operation (NOP)”)
0–n Up to 32,759 bytes of data.
Object Area Descriptor
OBD X'D3A66B' Object Area Descriptor (See “Object Area Descriptor (OBD)”)
0–n The following triplets, in any order:
Descriptor Position Triplet (See “Descriptor Position Triplet X'43'”) 1 M
0–1 X'0343' →Triplet length and identifier 1 M
2 X'01'–X'7F' →Descriptor position ID. It must be in the range of 1 to 127. 1 M [MODCA-7-062]
MO:DCA IS/1


OBD X'D3A66B' Object Area Descriptor (See “Object Area Descriptor (OBD)”)
Measurement Units Triplet (See “Measurement Units Triplet X'4B'”) 1 M
0–1 X'084B' →Triplet length and identifier 1 M
2–3 X'0000' →Object area measurement units base for X and Y 1 M
4–5 Object area measurement units value for X. It must be one of the following:
X'0960' → 2400 units per unit base (240 units per inch)
X'3840' → 14400 units per unit base (1440 units per inch)
6–7 Object area measurement units value for Y . It must be identical to bytes 4–
5.
1 M
Object Area Size Triplet (See “Object Area Size Triplet X'4C'”) 1 M
0–1 X'094C' →Triplet length and identifier 1 M
2 X'02' →Type, actual object area size 1 M [MODCA-7-063]
3–5 Object area size in the X direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using
240 units per inch for the object area X [MODCA-7-064]
measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using
1440 units per inch for the object area [MODCA-7-065]
X measurement units
1 M
6–8 Object area size in the Y direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using
240 units per inch for the object area Y [MODCA-7-066]
measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using
1440 units per inch for the object area [MODCA-7-067]
Y measurement units
1 M
Note: If the presentation text Object Area Descriptor structured field appears in the AEG, the measurement
units and extents specified on it must match those specified on the Page Descriptor structured field, or a
X'01' exception condition exists. If the presentation text Object Area Descriptor structured field is omitted,
the architected default is to use the measurement units and extents specified on the Page Descriptor
structured field for the presentation text object area. Thus, the presentation text object area and the
page are always the same size and points within their respective coordinate systems are always
coincident.
Object Area Position
OBP X'D3AC6B' Object Area Position (See “Object Area Position (OBP)”)
0 X'01'–X'7F' →Object Area Position ID. It must be in the range of 1 to 127. 1 M [MODCA-7-068]
1 X'17' →Length of this repeating group is 23 bytes 1 M [MODCA-7-069]
2–4 Object area origin for X. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay X measurement
units
1 M
MO:DCA IS/1


OBP X'D3AC6B' Object Area Position (See “Object Area Position (OBP)”)
5–7 Object area origin for Y . It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay Y measurement
units
1 M
8–11 Object Area orientation, X and Y coordinates. It must be one of the following:
X'0000 2D00' → X=0 degrees, Y=90 degrees
X'2D00 5A00' → X=90 degrees, Y=180 degrees
X'5A00 8700' → X=180 degrees, Y=270 degrees
X'8700 0000' → X=27 degrees, Y=0 degrees
1 M
12 X'00' →Reserved; must be binary zero 1 M [MODCA-7-070]
13–15 Object content origin for X. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the object area X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the object area X measurement units
1 M
16–18 Object content origin for Y . It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the object area Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the object area Y measurement units
1 M
19–20 X'0000' →Object content orientation, X (0 degrees) 1 M
21–22 X'2D00' →Object content orientation, Y (90 degrees) 1 M
23 Referenced coordinate system. It must be one of the following: [MODCA-7-071]
X'00' → Current coordinate system
X'01' → Page or overlay coordinate system
1 M
Notes:
1. If the presentation text Object Area Position structured field appears in the AEG, the X and Y values for the [MODCA-7-072]
object area origin and the object content origin must be set to zero, or a X'01' exception condition exists. If
the presentation text Object Area Position structured field is omitted, the architected default is to set the X
and Y values for the object area origin and the object content origin to zero. For presentation text, the data
object presentation space origin is positioned coincident with the object content origin. Thus, the
presentation text object presentation space, the presentation text object area, and the page always have
the same origin.
2. If the presentation text OBP appears in the AEG, the object area orientation must be set to X'0000 2D00' [MODCA-7-073]
(0°,90°). If it is omitted, the architected default is to set the object area orientation to X'0000 2D00' (0°,90°).
3. For this interchange set, the values X'00' and X'01' in byte 23 specify the same function since positioning [MODCA-7-074]
with respect to a page segment offset is not part of the interchange set definition. That is, both values
specify that the object area is to be positioned with respect to the including page or overlay coordinate
system.
MO:DCA IS/1


Page Descriptor
PGD X'D3A6AF' Page Descriptor (See “Page Descriptor (PGD)”)
0–1 X'0000' →Page measurement units base for X and Y 1 M
2–3 Page measurement units value for X. It must be one of the following:
X'0960' → 2400 units per unit base (240 units per inch)
X'3840' → 14400 units per unit base (1440 units per inch)
1 M
4–5 Page measurement units value for Y . It must be identical to bytes 2–3. 1 M
6–8 Page size in the X direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using 240 units per
inch for the page X measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using 1440 units
per inch for the page X measurement units
1 M
9–11 Page size in the Y direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using 240 units per
inch for the page Y measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using 1440 units
per inch for the page Y measurement units
1 M
12–14 X'000000' →Reserved; must be binary zero 1 M
Application Note: The IS/1 and IS/2 interchange set definitions limit the page size to 22.75 inches in the X
and Y directions. T o specify a larger page size, 240 units per inch should be specified in the PGD for the
page measurement units. Using a range of 1 to 32,767, this will allow a maximum page size in the X and
Y directions of 136.5 inches, is supported by all IPDS printers, and keeps the complete page
presentation space within the range of two-byte addressing parameters in the IPDS and PTOCA
architectures.
Presentation Text Data
PTX X'D3EE9B' Presentation Text Data (See “Presentation Text Data (PTX)”)
0–n Up to 8,192 bytes of presentation text data as defined by PTOCA PT1
Presentation Text Data Descriptor, Format 2
PTD X'D3B19B' Presentation Text Data Descriptor (See “Presentation Text Data Descriptor (PTD) Format 2” on
page 340)
0–n Presentation text descriptor data as defined by PTOCA
Note: When the PTD is included in the AEG for a page, some AFP print servers require that the measurement
units in the PTD match the measurement units in the Page Descriptor (PGD). It is therefore strongly
recommended that whenever the PTD is included in the AEG, the same measurement units are
specified in both the PTD and PGD.
MO:DCA IS/1


MO:DCA Presentation Interchange Set 2 (IS/2)
The MO:DCA Interchange Set 2 (MO:DCA IS/2) has been retired for products that implemented this set before
2012; see “Retired Interchange Set”. This interchange set is no longer part of the MO:DCA
interchange set hierarchy.
MO:DCA IS/2


### MO:DCA Interchange Set 3 (IS/3)
This section defines the MO:DCA Interchange Set 3 (MO:DCA IS/3) used for presentation documents.
MO:DCA IS/3 is based on MO:DCA Presentation Interchange Set 1 (MO:DCA IS/1) and contains most of the
functions added to the MO:DCA architecture since the IS/1 interchange set was defined in 1991. IS/3 does not
include functions in IS/1 that have a strategic successor and that may eventually be formally retired from the
MO:DCA architecture. A primary example is the support for FOCA font technology, which is not included in IS/3
because the MO:DCA architecture now supports the more modern industry-standard TrueType/OpenType font
technology.
For details on the level of function required for the objects that are defined by AFP Object Content
Architectures (OCAs) and that are included in this interchange set, refer to the following documents:
BCOCA Bar Code Object Content Architecture Reference
CMOCA Color Management Object Content Architecture Reference
GOCA Graphics Object Content Architecture for AFP Reference
IOCA Image Object Content Architecture Reference
PTOCA Presentation Text Object Content Architecture Reference
FOCA Font Object Content Architecture Reference
The AFP Consortium (AFPC) has defined subsets for several industry-standard presentation object containers
that are also included in IS/3. These subsets are:
• AFPC TIFF [MODCA-7-075]
• AFPC JPEG [MODCA-7-076]
Both are formally defined in Presentation Object Subsets for AFP, available from the AFP Consortium.
Reference.
1.0 Functional Subsets [MODCA-7-077]
The MO:DCA IS/3 interchange set comprises the following major MO:DCA functional subsets above and
beyond the functional subsets contained in MO:DCA IS/1:
• Page and page group level indexing using TLEs [MODCA-7-078]
• Document component and area linking using LLEs [MODCA-7-079]
• N-up presentation [MODCA-7-080]
• Process color [MODCA-7-081]
• AFP finishing [MODCA-7-082]
• TrueType/OpenType font support [MODCA-7-083]
• Color management [MODCA-7-084]
2.0 Compliance [MODCA-7-085]
General compliance with MO:DCA interchange sets is defined in “Interchange Set Compliance Requirements”
. The MO:DCA architecture definition of compliance with the IS/3 interchange set is limited to what [MODCA-7-086]
compliance means for MO:DCA print files, it does not include definitions of IS/3 compliance for product
compliance classes, e.g. generators and receivers. That is, the architecture defines the content of IS/3-
compliant print files in terms of what is permitted (may), what is recommended (should), what is mandatory
(must), and what is prohibited (must not). The definition of what constitutes an IS/3-compliant product must
be provided in documentation that is outside the scope of the MO:DCA architecture.
MO:DCA IS/3


A MO:DCA print file is compliant with the IS/3 interchange set definition if all the following conditions are met:
• all objects and their content must be in IS/3 and must comply with the IS/3 object structure definitions [MODCA-7-087]
• all structured fields must be in IS/3 and must comply with the IS/3 parameter and triplet definitions [MODCA-7-088]
• all structured field triplets must be in IS/3 and must comply with applicable IS/3 restrictions [MODCA-7-089]
• all parameter values must fall within the ranges defined by IS/3 [MODCA-7-090]
• the print file must not include any migration functions (as defined in Appendix C - MO:DCA Migration [MODCA-7-091]
Functions), unless they are explicitly allowed in IS/3 (see “7.0 Migration Functions included in IS/3”)
• the maximum structured field length must be limited to X'7FF0' = 32,752 [MODCA-7-092]
• all Begin Document (BDT) structured fields must specify the MO:DCA Interchange Set (X'18') triplet with ISid [MODCA-7-093]
= X'0D00' (MO:DCA IS/3)
• the print file must be enveloped with the Begin Print File (BPF) and End Print File (EPF) structured fields and [MODCA-7-094]
the Begin Print File (BPF) structured field must specify the MO:DCA Interchange Set (X'18') triplet with ISid =
X'0D00' (MO:DCA IS/3)
2.1 Migration Functions (as defined in Appendix C - MO:DCA Migration Functions) [MODCA-7-095]
In general, MO:DCA IS/3 does not include any obsolete, retired, or coexistence parameters, triplets, structured
fields, or objects. For exceptions, see “7.0 Migration Functions included in IS/3”.
2.2 Structured Field Introducer [MODCA-7-096]
The Flag byte (byte 5) in the Structured Field Introducer (SFI) must be set to X'00'. MO:DCA IS/3 does not
include support for the following functions:
• SFI extension [MODCA-7-097]
• Structured field segmentation [MODCA-7-098]
• Structured field padding [MODCA-7-099]
The maximum structured field length in IS/3 is limited to X'7FF0' = 32,752.
Application Note: This restriction avoids problems on platforms that include structured fields into a larger
“record” by adding several bytes (such as the X'5A' character) resulting in a record length greater than
X'7FFF'. Such a record length can be misinterpreted as a negative number if the length is treated as
SBIN. Note that the maximum structured field length in IS/1 is X'2000' = 8,192
2.3 Exception Conditions [MODCA-7-100]
In general, no exception conditions are defined within the IS/3 definition for the structured fields or their
parameters above and beyond what the general MO:DCA architecture defines. The following general rules
apply:
• Exception conditions should not be generated solely due to noncompliance with IS/3. When a valid print file [MODCA-7-101]
is noncompliant with IS/3, it should always be processed to the best of a receiver's capabilities. That is, any
object, object content, structured field, or structured field triplet that is valid in the general architecture but
that is not included in the IS/3 definition should be processed to the best of a receiver's capability. For
example, a receiver may generate an exception because it detected an error while processing an MCF-2
structured field, but not because the print file claimed to be IS/3 compliant and the MCF-2 structured field is
not part of IS/3.
MO:DCA IS/3


3.0 Data Stream Object Structure [MODCA-7-102]
This section defines the objects that make up an IS/3 data stream.
Notes:
1. The Begin Print File and End Print File structured fields are required in a MO:DCA IS/3 data stream. [MODCA-7-103]
2. The Begin Document and End Document structured fields are required in a MO:DCA IS/3 data stream. [MODCA-7-104]
3. The No Operation structured field may appear within any begin-end domain and thus is not listed in the [MODCA-7-105]
structured field groupings.
4. Object content must not include functions that are not in IS/3. That is, a print file is not IS/3-compliant if it [MODCA-7-106]
includes such content.
5. T able 27contains summaries of the IS/3 object structure. All syntax, semantics, and notes in the object [MODCA-7-107]
structure definitions in Chapter 4, “MO:DCA Objects”, apply, unless explicitly specified
otherwise.
Table 27. IS/3 Objects
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Print File Begin Print File (BPF)
X'D3A8A5' - End Print
File (EPF) X'D3A9A5'
The print file:
• must be enveloped by the Begin Print File (BPF) and End Print File [MODCA-7-108]
(EPF) structured fields
• must specify the MO:DCA Interchange Set X'18' triplet on the BPF and [MODCA-7-109]
must indicate ISid = X'0D00' = MO:DCA IS/3.
The print file contains only the following structured fields and objects, as
defined in the general architecture subject to all applicable IS/3
restrictions.
Print File
(BPF, D3A8A5)
[ (Resource Grp) ]
(Index + Doc ) (S)
(EPF, D3A9A5)
Index + Document
[ (Index ) ]
(Document ) (S)
Note: IS/3 compliant consumers must consider a physical file, which is an
operating system file that, when it contains AFP data, is printed with a
single Form Definition, as a single MO:DCA (AFP) print file that contains
at most one BPF/EPF pair and at most one print file level resource
group. Such consumers should generate a presentation-system-specific
exception if the physical file contains more than one BPF/EPF pair.
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Resource Group
( print file)
Begin Resource
Group (BRG)
X'D3A8C6' - End
Resource Group
(ERG) X'D3A9C6'
The resource group may only contain the following structured fields and
resource objects, as defined in the general architecture subject to all
applicable IS/3 restrictions.
(BRG, D3A8C6)
+ [ (Overlay ) (S) ]
+ [ (MO:DCA Pseg ) (S) ]
+ [ (Form Map ) (S) ]
+ [ (BCOCA ) (S) ]
+ [ (GOCA ) (S) ]
+ [ (IOCA ) (S) ]
+ [ (Object Cont ) (S) ]
+ [ (FOCA Object ) (S) ]
(ERG, D3A9C6)
The only FOCA objects that may be included are:
• FOCA code page object [MODCA-7-110]
• FOCA Unicode-extended code page object [MODCA-7-111]
IS/3 may limit the function in the resource objects; for details see the
individual IS/3 object definitions in this table.
Resource Object
(in print file
resource group)
Begin Resource
(BRS) X'D3A8CE' -
End Resource (ERS)
X'D3A9CE'
The resource object must be enveloped by the Begin Resource (BRS) and
End Resource (ERS) structured fields:
(BRS, D3A8CE)
(Res Object )
(ERS, D3A9CE)
Document Index Begin Document
Index (BDI)
X'D3A8A7' - End
Document Index (EDI)
X'D3A9A7'
The document index contains only the following structured fields, as
defined in the general architecture subject to all applicable IS/3
restrictions.
(BDI, D3A8A7)
+ (IEL, D3B2A7) (S)
+ [ (LLE, D3B490) (S) ]
+ [ (TLE, D3A090) (S) ]
(EDI, D3A9A7)
Document Begin Document
(BDT) X'D3A8A8' -
End Document (EDT)
X'D3A9A8'
The document contains only the following structured fields and objects, as
defined in the general architecture subject to all applicable IS/3
restrictions.
(BDT, D3A8A8)
+ [ (IMM, D3ABCC) (S) ]
+ [ (LLE, D3B490) (S) ]
+ [ (Medium Map ) (S) ]
+ [ (REG ) (S) ]
+ [ (Page ) (S) ]
+ [ (Page Group ) (S) ]
(EDT, D3A9A8)
Resource
Environment
Group (REG)
Begin Resource
Environment Group
(BSG) X'D3A8D9' -
End Resource
Environment Group
(ESG) X'D3A9D9'
The Resource Environment Group contains only the following structured
fields, as defined in the general architecture subject to all applicable IS/3
restrictions.
(BSG, D3A8D9)
[ (MDR, D3ABC3) (S) ]
[ (MPO, D3ABD8) (S) ]
[ (PPO, D3ADC3) (S) ]
(ESG, D3A9D9)
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Page Begin Page (BPG)
X'D3A8AF' - End
Page (EPG)
X'D3A9AF'
The page contains only the following structured fields and objects, as
defined in the general architecture subject to all applicable IS/3
restrictions.
Page
(BPG, D3A8AF)
(AEG )
+ [ (IOB, D3AFC3) (S) ]
+ [ (IPO, D3AFD8) (S) ]
+ [ (IPS, D3AF5F) (S) ]
+ [ (LLE, D3B490) (S) ]
+ [ (TLE, D3A090) (S) ]
+ [ (BCOCA ) (S) ]
+ [ (GOCA ) (S) ]
+ [ (IOCA ) (S) ]
+ [ (PTOCA ) (S) ]
+ [ (Object Cont ) (S) ]
(EPG, D3A9AF)
AEG
(BAG, D3A8C9)
[ (PEC, D3A7A8) ]
[ (MDR, D3ABC3) (S) ]
[ (MPO, D3ABD8) (S) ]
[ (MPS, D3B15F) (S) ]
(PGD, D3A6AF)
[ (OBD, D3A66B) ]
[ (OBP, D3AC6B) ]
(PTD, D3B19B) F2
(EAG, D3A9C9)
Notes:
1. The OBD is only used for PTOCA objects without an OEG, and if [MODCA-7-112]
specified:
• the measurement units must match the PGD units [MODCA-7-113]
• the extents must match the PGD extents [MODCA-7-114]
These are the architected defaults if the OBD is not specified, and
cause the text object area to have the same units and extents as the
page.
2. The OBP is only used for PTOCA objects without an OEG, and if [MODCA-7-115]
specified:
• the object area origin must be set to zero [MODCA-7-116]
• the object content origin must be set to zero [MODCA-7-117]
• the object area orientation must be set to (0°,90°) [MODCA-7-118]
These are the architected defaults if the OBP is not specified, and
cause the text object area to be positioned coincident with the page.
3. The PTD is only mandatory if the page contains one or more PTOCA [MODCA-7-119]
objects without an OEG. It is strongly recommended that the
measurement units in the PTD match the PGD units.
IS/3 may limit the function in the data objects; for details see the individual
IS/3 object definitions in this table.
Page Group Begin Named Page
Group (BNG)
X'D3A8AD' - End
Named Page Group
(ENG) X'D3A9AD'
The page group contains only the following structured fields and objects,
as defined in the general architecture subject to all applicable IS/3
restrictions.
(BNG, D3A8AD)
[ (TLE, D3A090) (S) ]
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
+ [ (IMM, D3ABCC) (S) ]
+ [ (LLE, D3B490) (S) ]
+ [ (Medium Map ) (S) ]
+ [ (REG ) (S) ]
+ [ (Page ) (S) ]
+ [ (Page Group ) (S) ]
(ENG, D3A9AD)
Overlay Begin Overlay (BMO)
X'D3A8DF' - End
Overlay (EMO)
X'D3A9DF'
The overlay contains only the following structured fields and objects, as
defined in the general architecture subject to all applicable IS/3
restrictions.
Overlay
(BMO, D3A8DF)
(AEG )
+ [ (IOB, D3AFC3) (S) ]
+ [ (IPS, D3AF5F) (S) ]
+ [ (LLE, D3B490) (S) ]
+ [ (TLE, D3A090) (S) ]
+ [ (BCOCA ) (S) ]
+ [ (GOCA ) (S) ]
+ [ (IOCA ) (S) ]
+ [ (PTOCA ) (S) ]
+ [ (Object Cont ) (S) ]
(EMO, D3A9DF)
AEG
(BAG, D3A8C9)
[ (PEC, D3A7A8) ]
[ (MDR, D3ABC3) (S) ]
[ (MPS, D3B15F) (S) ]
(PGD, D3A6AF)
[ (OBD, D3A66B) ]
[ (OBP, D3AC6B) ]
(PTD, D3B19B) F2
(EAG, D3A9C9)
Notes:
1. The OBD is only used for PTOCA objects without an OEG, and if [MODCA-7-120]
specified:
• the measurement units must match the PGD units [MODCA-7-121]
• the extents must match the PGD extents [MODCA-7-122]
These are the architected defaults if the OBD is not specified, and
cause the text object area to have the same units and extents as the
overlay.
2. The OBP is only used for PTOCA objects without an OEG, and if [MODCA-7-123]
specified:
• the object area origin must be set to zero [MODCA-7-124]
• the object content origin must be set to zero [MODCA-7-125]
• the object area orientation must be set to (0°,90°) [MODCA-7-126]
These are the architected defaults if the OBP is not specified, and
cause the text object area to be positioned coincident with the overlay.
3. The PTD is only mandatory if the overlay contains one or more [MODCA-7-127]
PTOCA objects without an OEG. It is strongly recommended that the
measurement units in the PTD match the PGD units.
IS/3 may limit the function in the data objects; for details see the individual
IS/3 object definitions in this table.
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Page Segment Begin Page Segment
(BPS) X'D3A85F' -
End Page Segment
(EPS) X'D3A95F'
The page segment must be a MO:DCA page segment and contains only
the following structured fields and objects, as defined in the general
architecture subject to all applicable IS/3 restrictions.
Page Segment
(BPS, D3A85F)
+ [ (BCOCA ) (S) ]
+ [ (GOCA ) (S) ]
+ [ (IOCA ) (S) ]
(EPS, D3A95F)
IS/3 may limit the function in the data objects; for details see the individual
IS/3 object definitions in this table.
Bar Code Object Begin Bar Code
Object (BBC)
X'D3A8EB' - End Bar
Code Object (EBC)
X'D3A9EB'
The object content must comply with the BCOCA BCD2 subset definition.
The bar code object contains only the following structured fields, as
defined in the general architecture subject to all applicable IS/3
restrictions.
Bar Code Object
(BBC, D3A8EB)
(OEG )
[ (BDA, D3EEEB) (S) ]
(EBC, D3A9EB)
OEG
(BOG, D3A8C7)
(OBD, D3A66B)
(OBP, D3AC6B)
[ (MBC, D3ABEB) ]
[ (MDR, D3ABC3) (S) ]
(BDD, D3A6EB)
(EOG, D3A9C7)
Graphics Object Begin Graphics Object
(BGR) X'D3A8BB' -
End Graphics Object
(EGR) X'D3A9BB'
The object content must comply with the AFP GOCA GRS3 subset
definition. The graphics object contains only the following structured fields,
as defined in the general architecture subject to all applicable IS/3
restrictions.
Graphics Object
(BGR, D3A8BB)
(OEG )
[ (GAD, D3EEBB) (S) ]
(EGR, D3A9BB)
OEG
(BOG, D3A8C7)
[ (PEC, D3A7A8) ]
(OBD, D3A66B)
(OBP, D3AC6B)
[ (MGO, D3ABBB) ]
[ (MDR, D3ABC3) (S) ]
(GDD, D3A6BB)
(EOG, D3A9C7)
Note: If the boundary for an area is to be drawn but is not properly closed,
IS/3 receivers should not draw a line to close the figure.
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Image Object Begin Image Object
(BIM) X'D3A8FB' -
End Image Object
(EIM) X'D3A9FB'
The object content must comply with the IOCA FS10 or FS45 subset
definitions. Note that compliance with IOCA FS45 includes compliance
with IOCA FS40 and FS42. The image object contains only the following
structured fields, as defined in the general architecture subject to all
applicable IS/3 restrictions.
Image Object
(BIM, D3A8FB)
(OEG )
[ (IPD, D3EEFB) (S) ]
(EIM, D3A9FB)
OEG
(BOG, D3A8C7)
[ (PEC, D3A7A8) ]
(OBD, D3A66B)
(OBP, D3AC6B)
[ (MIO, D3ABFB) ]
[ (MDR, D3ABC3) (S) ]
(IDD, D3A6FB)
(EOG, D3A9C7)
The object content includes support for the following additional IOCA
functions:
• IDD Set Extended Bilevel Image Color self-defining field [MODCA-7-128]
• All MO:DCA color spaces for bilevel tiles on the Tile Set Color parameter [MODCA-7-129]
Presentation T ext
Object
Begin Presentation
T ext Object (BPT)
X'D3A89B' - End
Presentation T ext
(EPT) X'D3A99B'
The object content must comply with the PTOCA PT3 subset definition,
with support for the following additional PTOCA functions:
• Set T ext Color (STC) control sequence “Precision” parameter (byte 6) is [MODCA-7-130]
retired
• New Exception id EC-1A03: Invalid Unicode Data [MODCA-7-131]
• Highlight Color Space, range X'0100' - X'FFFF', for Indexed CMRs [MODCA-7-132]
• Support for the full range of color values, as defined in the “Standard [MODCA-7-133]
OCA Color Value T able”, in the STC control sequence
• Support for the full PTOCA parameter ranges in the DBR, DIR, SIA, and [MODCA-7-134]
SVI control sequences
The text object contains only the following structured fields, as defined in
the general architecture subject to all applicable IS/3 restrictions.
Text Object
(BPT, D3A89B)
[ (PTX, D3EE9B) (S) ]
(EPT, D3A99B)
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Object Container
- Presentation [MODCA-7-135]
Object
Begin Object
Container (BOC)
X'D3A892' - End
Object Container
(EOC) X'D3A992'
See T able 28 for the presentation object containers included
in IS/3. The object container contains only the following structured fields,
as defined in the general architecture subject to all applicable IS/3
restrictions.
Presentation Object Container
(BOC, D3A892)
[ (OEG ) ]
[ (OCD, D3EE92) (S) ]
(EOC, D3A992)
OEG
(BOG, D3A8C7)
[ (PEC, D3A7A8) ]
[ (OBD, D3A66B) ]
[ (OBP, D3AC6B) ]
[ (MCD, D3AB92) ]
[ (MDR, D3ABC3) (S) ]
[ (CDD, D3A692) ]
(EOG, D3A9C7)
• If included directly on a page/overlay, BOC/EOC is mandatory, OEG with [MODCA-7-136]
OBD, OBP , CDD is mandatory, and all object data must be carried in
OCDs.
• If included with an IOB and located in the resource group, BOC/EOC is [MODCA-7-137]
mandatory and all object data must be carried in OCDs; OEG is optional.
• If included with an IOB and located in a resource library, it can be [MODCA-7-138]
wrapped: with a BOC/EOC wrapper, all object data in OCDs, and an
optional OEG; or unwrapped: where the data is unaltered in its original
form. If installed with a RAT , the object must not be wrapped.
See T able 30 for the IS/3 presentation object containers that
can be referenced with an IOB structured field and that can be processed
with a Data Object Resource (DOR) RAT (Resource Access T able).
Object Container
- Non-
Presentation
Object
Begin Object
Container (BOC)
X'D3A892' - End
Object Container
(EOC) X'D3A992'
See T able 29 for the non-presentation object containers
included in IS/3. The object container contains only the following
structured fields, as defined in the general architecture subject to all
applicable IS/3 restrictions.
Non-presentation Object Container
(BOC, D3A892)
[ (OCD, D3EE92) (S) ]
(EOC, D3A992)
• If located in the resource group, BOC/EOC is mandatory and all object [MODCA-7-139]
data must be carried in OCDs.
• If located in a resource library: [MODCA-7-140]
– a CMT must be wrapped: BOC/EOC wrapper and all data in OCDs
– an IOCA tile resource can be wrapped or unwrapped
– TTF/OTFs, TTF collections, and CMRs, since always installed with a
RAT , must not be wrapped
FOCA Objects Only a FOCA code
page can occur in the
print file resource
group within following
container: Begin
Resource (BRS)
X'D3A8CE' - End
The following objects are supported and may be referenced with an MDR
structured field that specifies the name of the code page:
• FOCA code pages: [MODCA-7-141]
– Single-byte and double-byte
– Single-byte and double-byte with Unicode values
MO:DCA IS/3


Table 27 IS/3 Objects (cont'd.)
IS/3 Data Stream Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general MO:DCA
Architecture noted
Resource (ERS)
X'D3A9CE'
Table 28. IS/3 Containers - Presentation Objects
Component
ID
Object Type Encoded Object-type OID
14 TIFF X'06072B12000401010E' [MODCA-7-142]
22 GIF X'06072B120004010116' [MODCA-7-143]
23 AFPC JPEG [MODCA-7-144]
Note: This object type was formerly referred to as JFIF (JPEG).
X'06072B120004010117'
60 TIFF without Transparency X'06072B12000401013C' [MODCA-7-145]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-7-146]
62 TIFF Multiple Image - without Transparency - File X'06072B12000401013E' [MODCA-7-147]
66 AFPC TIFF X'06072B120004010142' [MODCA-7-148]
Table 29. IS/3 Containers - Non-Presentation Objects
Component
ID
Object Type Encoded Object-type OID
20 Color Mapping T able (CMT) X'06072B120004010114' [MODCA-7-149]
47 IOCA Tile Resource X'06072B12000401012F' [MODCA-7-150]
51 TrueType/OpenType Font: [MODCA-7-151]
• TrueType shapes (Unicode Cmap) [MODCA-7-152]
• CFF Type 1 shapes (Unicode Cmap) [MODCA-7-153]
• CFF CID shapes (Unicode Cmap) [MODCA-7-154]
X'06072B120004010133'
53 TrueType/OpenType Font Collection X'06072B120004010135' [MODCA-7-155]
57 Color Management Resource (CMR) [MODCA-7-156]
Baseline support as defined in the CMOCA reference, plus support
for Indexed (IX) CMRs. Therefore the following CMR types are
supported:
• Color Conversion (CC) CMRs [MODCA-7-157]
• Generic Halftone (HT) CMRs [MODCA-7-158]
• Generic T one Transfer Curves (TTC) CMRs [MODCA-7-159]
• Indexed (IX) CMRs [MODCA-7-160]
Support for the CMYK passthru function.
X'06072B120004010139'
T able 30 lists the IS/3 presentation object containers that can be referenced for presentation by
the Include Object (IOB) structured field with ObjType = X'92'— other object data. This is also the list of IS/3
presentation object containers that can be processed with a Data Object Resource (DOR) RAT .
MO:DCA IS/3


Table 30. IS/3 IOB and DOR RAT Presentation Object Containers
Component
ID
Object Type Encoded Object-type OID
14 TIFF X'06072B12000401010E' [MODCA-7-161]
22 GIF X'06072B120004010116' [MODCA-7-162]
23 AFPC JPEG [MODCA-7-163]
Note: This object type was formerly referred to as JFIF (JPEG).
X'06072B120004010117'
60 TIFF without Transparency X'06072B12000401013C' [MODCA-7-164]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-7-165]
62 TIFF Multiple Image - without Transparency - File X'06072B12000401013E' [MODCA-7-166]
66 AFPC TIFF X'06072B120004010142' [MODCA-7-167]
T able 31 lists the secondary resources that are supported by various IS/3 data object resources.
Table 31. IS/3 Data Objects and Secondary Resources
Data Object Resource Secondary Resource Internal Resource Identifier
IOCA Image IOCA Tile Resource
Color Management Resource
4-byte local ID
None
PTOCA T ext; see Note TrueType/OpenType Font 1-byte local ID
AFP GOCA; see Note TrueType/OpenType Font
Color Management Resource
1-byte local ID
None
BCOCA T ext; see Note TrueType/OpenType Font
Color Management Resource
1-byte local ID
None
TIFF - all formats Color Management Resource None
GIF Color Management Resource None
AFPC JPEG
Note: This object type was formerly
referred to as JFIF (JPEG).
Color Management Resource None
Note: These table entries are not formally primary resource with secondary resource pairs since PTOCA, AFP GOCA,
and BCOCA objects currently cannot be processed as resource objects. However, the resources for these objects are
processed like other secondary resources.
MO:DCA IS/3


4.0 Print Control Object Structure [MODCA-7-168]
This section defines the objects that are used to control the presentation of an IS/3 data stream.
Table 32. IS/3 Print Control Objects
IS/3 Print Control Object Structure
Object Name Object Envelope
Summary of IS/3 object structure; differences from general
MO:DCA Architecture noted
Form Map Begin Form Map
(BFM) X'D3A8CD' -
End Form Map (EFM)
X'D3A9CD'
The form map contains only the following objects, as defined in the
general architecture subject to all applicable IS/3 restrictions.
(BFM, D3A8CD)
[ (DEG ) ]
(Medium Map ) (S)
(EFM, D3A9CD)
Document
Environment Group
Begin Document
Environment Group
(BDG) X'D3A8C4' -
End Document
Environment Group
(EDG) X'D3A9C4'
The Document Environment Group (DEG) contains only the following
structured fields, as defined in the general architecture subject to all
applicable IS/3 restrictions.
(BDG, D3A8C4)
[ (PFC, D3B288) (S) ]
[ (PEC, D3A7A8) (S) ]
[ (MMO, D3B1DF) ]
[ (MSU, D3ABEA) ]
(PGP, D3B1AF) F2
(MDD, D3A688)
[ (MFC, D3A088) (S) ]
[ (MDR, D3ABC3) (S) ]
(EDG, D3A9C4)
Notes:
1. The PGP and MDD are mandatory in either the DEG or the [MODCA-7-169]
Medium Map.
2. When the same structured field is specified in both the DEG and [MODCA-7-170]
the Medium Map, the Medium Map overrides.
3. IS/3 does not include support for UP3i finishing operations. [MODCA-7-171]
Medium Map Begin Medium Map
(BMM) X'D3A8CC' -
End Medium Map
(EMM) X'D3A9CC'
The Medium Map contains only the following structured fields, as
defined in the general architecture subject to all applicable IS/3
restrictions.
(BMM, D3A8CC)
[ (MMO, D3B1DF) ]
[ (MPO, D3ABD8) (S) ]
[ (MMT, D3AB88) (S) ]
[ (MDR, D3ABC3) (S) ]
(PGP, D3B1AF) F2
(MDD, D3A688)
(MCC, D3A288)
[ (MMC, D3A788) (S) ]
[ (PMC, D3A7AF) (S) ]
[ (MFC, D3A088) (S) ]
[ (PEC, D3A7A8) ]
(EMM, D3A9CC)
Notes:
1. The PGP and MDD are mandatory in either the DEG or the [MODCA-7-172]
Medium Map.
2. When the same structured field is specified in both the DEG and [MODCA-7-173]
the Medium Map, the Medium Map overrides.
3. IS/3 does not include support for UP3i finishing operations. [MODCA-7-174]
MO:DCA IS/3


5.0 Structured Fields and Triplets [MODCA-7-175]
This section lists the IS/3 structured fields and their supported triplets. Triplets that are not listed but that are
allowed in the general architecture must not be specified in an IS/3-compliant print file. Unless otherwise
noted, all non-migration structured field positional parameters are supported in IS/3. Also, unless otherwise
noted, the complete architected parameter range is supported in IS/3 for all structured field positional
parameters and triplets. In general, IS/3 does not include any obsolete, retired, or coexistence parameters or
triplets as defined in Appendix C, “MO:DCA Migration Functions”,; for exceptions, see “7.0
Migration Functions included in IS/3”. For brevity the tables in this section are only intended to
summarize the triplets that are allowed on a structured field; for a complete definition of how these triplets are
used on a structured field and what restrictions may apply, the general architecture must be consulted. Note
that if a triplet is allowed to have 0 occurrences, it is an optional triplet. If it is allowed to have 1 or 1 or more
occurrences but not 0 occurrences, it is a mandatory triplet.
The following rules apply to all IS/3 structured fields:
• The Local Date and Time Stamp (X'62') triplet is not included in IS/3 and must not be specified; it is replaced [MODCA-7-176]
by the ISO-based Universal Date and Time Stamp (X'72') triplet.
• The Presentation Space Mixing Rules (X'71') triplet is not included in IS/3 and must not be specified. [MODCA-7-177]
• The Coded Graphic Character Set Global ID (X'01') triplet, while allowed on most structured fields in the [MODCA-7-178]
general architecture, is only used in IS/3 on the BOC, BRS, IOB, MDR, PPO, and TLE structured fields, as
noted explicitly in the following tables. Support for the inheritance of encoding scheme, as specified with the
X'01' triplet on Begin structured fields, to lower levels of the MO:DCA hierarchy is not included in IS/3. While
the X'01' triplet is mandatory on the BDT in the general architecture, it is optional on the BDT in IS/3, and if
specified, must be ignored. It must not be specified on any other structured field. The architected default
encoding for the IS/3 print file or document is EBCDIC single-byte presentation, which is characterized with
encoding scheme ID X'61nn', and which is identified with CCSID 500 (corresponding to the combination of
CPGID 500 and GCSGID 697). This default can be overridden on the BOC, BRS, IOB, MDR, PPO, and TLE
structured fields. The inheritance of the encoding scheme to lower levels of the MO:DCA hierarchy using the
X'01' triplet is not included in IS/3.
5.1 Begin Structured Fields [MODCA-7-179]
The following rules apply to all Begin structured fields in IS/3:
• The matching of names using the FQN type X'01' triplets on Begin/End structured fields is not part of IS/3. [MODCA-7-180]
MO:DCA IS/3 generators can use matching 8-byte token names on Begin/End structured fields, or they can
use the X'FFFF' wild card on End structured fields which matches any name on the corresponding Begin
structured field. The FQN type X'01' triplet on End structured fields, while allowed in the general architecture
on most End structured fields, must not be specified on End structured fields in an IS/3 print file.
Table 33. IS/3 Begin Structured Fields
IS/3 Begin Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Begin Active Environment
Group (BAG)
X'D3A8C9' X'65' 0 or more
Begin Bar Code Object
(BBC)
X'D3A8EB' X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Document
Environment Group (BDG)
X'D3A8C4' X'65' 0 or more
MO:DCA IS/3


Table 33 IS/3 Begin Structured Fields (cont'd.)
IS/3 Begin Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Begin Document Index
(BDI)
X'D3A8A7' X'02' Tpe X'01' 0 or 1
X'02' Tpe X'83' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Document (BDT) X'D3A8A8' X'18' 1 occurrence; must specify ISID = X'0D00' - MO:DCA
IS/3
X'01' 0 or more. Must be ignored.
X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
IS/3 does not include support for the inheritance by lower-level document
components of the encoding scheme specified in the CGCSGID (X'01')
triplet on the BDT . While this triplet is mandatory on the BDT in the general
architecture, it is optional on the BDT in IS/3, and if specified, must be
ignored. The architected default encoding for the document is EBCDIC
single-byte presentation, which is characterized with encoding scheme ID
X'61nn', and which is identified with CCSID 500 (corresponding to the
combination of CPGID 500 and GCSGID 697). This default can be
overridden on those structured fields where the X'01' triplet is supported in
IS/3 (BOC, BRS, IOB, MDR, PPO, TLE).
Note: A document can be made compliant both with the IS/3 encoding
rules and with encoding scheme inheritance if the CGCSGID (X'01')
triplet is specified last on the BDT , and if it specifies CCSID 500
(corresponding to the combination of CPGID 500 and GCSGID 697).
Begin Form Map (BFM) X'D3A8CD' X'65' 0 or more
X'72' 0 or 1
Begin Graphics Object
(BGR)
X'D3A8BB' X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Image Object (BIM) X'D3A8FB' X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Medium Map (BMM) X'D3A8CC' X'45' 0 or 1
X'65' 0 or more
Begin Overlay (BMO) X'D3A8DF' X'02' Tpe X'01' 0 or 1. The overlay name must be less than or equal to 8
characters (bytes) in length.
X'65' 0 or more
X'72' 0 or 1
Begin Named Page Group
(BNG)
X'D3A8AD' X'02' Tpe X'01' 0 or 1
X'02' Tpe X'8D' 0 or 1
X'56' 0 or 1
X'5E' 0 or 1 occurrence for pages counted
X'65' 0 or more
X'83' 0 or 1
MO:DCA IS/3


Table 33 IS/3 Begin Structured Fields (cont'd.)
IS/3 Begin Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Begin Object Container
(BOC)
X'D3A892' X'10' 1
X'01' 0 or more
Notes:
1. IS/3 requires full support of the CGCSGID (X'01') [MODCA-7-181]
triplet on the BOC.
2. It is strongly recommended that this triplet is [MODCA-7-182]
specified even if the parameter on the BOC defines
a fixed encoding. For example, if the parameter
defines a fixed UTF-16BE encoding, the triplet can
be specified using the CCSID form with CCSID=
1200 (X'04B0'). [MODCA-7-183]
X'02' Tpe X'01' 0 or 1
X'02' Tpe X'41' 0 or more
X'02' Tpe X'6E' 0 or more
X'02' Tpe X'7E' 0 or more
X'57' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Object Environment
Group (BOG)
X'D3A8C7' X'65' 0 or more
Begin Print File (BPF) X'D3A8A5' X'18' 1 occurrence; must specify ISID = X'0D00' - MO:DCA
IS/3
X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
IS/3 does not include support for the inheritance by lower-level document
components of the encoding scheme specified in the CGCSGID (X'01')
triplet on the BPF . The architected default encoding for the print file is
EBCDIC single-byte presentation, which is characterized with encoding
scheme ID X'61nn', and which is identified with CCSID 500 (corresponding
to the combination of CPGID 500 and GCSGID 697). This default can be
overridden on those structured fields where the X'01' triplet is supported in
IS/3 (BOC, BRS, IOB, MDR, PPO, TLE).
Begin Page (BPG) X'D3A8AF' X'02' Tpe X'01' 0 or 1
X'02' Tpe X'8D' 0 or 1
X'56' 0 or 1
X'65' 0 or more
X'81' 0 or 1
X'83' 0 or 1
Begin Page Segment
(BPS)
X'D3A85F' The page segment must be a MO:DCA page segment; see T able 27 on
page 491.
X'65' 0 or more
X'72' 0 or 1
Begin Presentation T ext
Object (BPT)
X'D3A89B' X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
MO:DCA IS/3


Table 33 IS/3 Begin Structured Fields (cont'd.)
IS/3 Begin Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Begin Resource Group
(BRG)
X'D3A8C6' X'02' Tpe X'01' 0 or 1
X'65' 0 or more
X'72' 0 or 1
Begin Resource (BRS) X'D3A8CE' X'02' Tpe X'6E' 1 or more if resource is a TTC; otherwise should not be
specified
X'10' 1 occurrence if ObjType = X'92' - Object Container;
otherwise should not be specified
X'21' 1
Note: This is the Resource Object Type triplet that was
formally retired but is now part of the general
architecture.
X'01' 0 or more
Notes:
1. IS/3 requires full support of the CGCSGID (X'01') [MODCA-7-184]
triplet on the BRS.
2. It is strongly recommended that this triplet is [MODCA-7-185]
specified even if the parameter on the BRS defines
a fixed encoding. For example, if the parameter
defines a fixed UTF-16BE encoding, the triplet can
be specified using the CCSID form with CCSID=
1200 (X'04B0'). [MODCA-7-186]
X'02' Tpe X'01' 0 or more; 1 occurrence mandatory if resource is a CMR
X'02' Tpe X'41' 0 or more if resource is a CMR; otherwise should not be
specified
X'02' Tpe X'7E' 0 or more if resource is a TTF/TTC; otherwise should
not be specified
X'65' 0 or more
Begin Resource
Environment Group (BSG)
X'D3A8D9' X'65' 0 or more
5.2 End Structured Fields [MODCA-7-187]
The following rules apply to all End structured fields in IS/3:
• The matching of names using the FQN type X'01' triplets on Begin/End structured fields is not part of IS/3. [MODCA-7-188]
MO:DCA IS/3 generators can use matching 8-byte token names on Begin/End structured fields, or they can
use the X'FFFF' wild card on End structured fields which matches any name on the corresponding Begin
structured field. The FQN type X'01' triplet on End structured fields, while allowed in the general architecture
on most End structured fields, must not be specified on End structured fields in an IS/3 print file.
Table 34. IS/3 End Structured Fields
IS/3 End Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
End Active Environment
Group (EAG)
X'D3A9C9'
End Bar Code Object
(EBC)
X'D3A9EB'
MO:DCA IS/3


Table 34 IS/3 End Structured Fields (cont'd.)
IS/3 End Structured Fields
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
End Document
Environment Group (EDG)
X'D3A9C4'
End Document Index (EDI) X'D3A9A7'
End Document (EDT) X'D3A9A8'
End Form Map (EFM) X'D3A9CD'
End Graphics Object
(EGR)
X'D3A9BB'
End Image Object (EIM) X'D3A9FB'
End Medium Map (EMM) X'D3A9CC'
End Overlay (EMO) X'D3A9DF'
End Named Page Group
(ENG)
X'D3A9AD'
End Object Container
(EOC)
X'D3A992'
End Object Environment
Group (EOG)
X'D3A9C7'
End Print File (EPF) X'D3A9A5'
End Page (EPG) X'D3A9AF'
End Page Segment (EPS) X'D3A95F' The page segment must be a MO:DCA page segment; see T able 27 on
page 491.
End Presentation T ext
Object (EPT)
X'D3A99B'
End Resource Group
(ERG)
X'D3A9C6'
End Resource (ERS) X'D3A9CE'
End Resource
Environment Group (ESG)
X'D3A9D9'
5.3 Structured Fields without Triplets [MODCA-7-189]
The following IS/3 structured fields do not support any triplets.
Table 35. IS/3 Structured Fields without Triplets
IS/3 Structured Fields without Triplets
Structured Field Name
Structured
Field ID Differences from general MO:DCA Architecture
Bar Code Data (BDA) X'D3EEEB'
MO:DCA IS/3


Table 35 IS/3 Structured Fields without Triplets (cont'd.)
IS/3 Structured Fields without Triplets
Structured Field Name
Structured
Field ID Differences from general MO:DCA Architecture
Graphics Data (GAD) X'D3EEBB' The GAD content must comply with the AFP GOCA GRS3 subset
definition.
Note: If the boundary for an area is to be drawn but is not properly closed,
IS/3 receivers should not draw a line to close the figure.
Graphics Data Descriptor
(GDD)
X'D3A6BB' GDD content as defined by the AFP GOCA GRS3 subset definition.
Measurement unit restrictions:
• unit base = 10 inches [MODCA-7-190]
• X units per unit base = Y units per unit base [MODCA-7-191]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-192]
Image Data Descriptor
(IDD)
X'D3A6FB' IDD content as defined for MO:DCA data streams by IOCA, with the
following optional self-defining fields (listed by ID) and their allowed
occurrences:
X'F4' 0 or more
X'F6' 0 or more
X'F7' 0 or 1
Measurement unit restrictions:
• unit base = 10 inches [MODCA-7-193]
• X units per unit base and Y units per unit base can be different [MODCA-7-194]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-195]
Image Picture Data (IPD) X'D3EEFB' The content must comply with the IOCA FS10 or FS45 subset definitions.
Note that compliance with IOCA FS45 includes compliance with IOCA
FS40 and FS42.
Medium Copy Count
(MCC)
X'D3A288'
Medium Modification
Control (MMC)
X'D3A788' The following keywords, with allowed occurrences:
X'90nn' 0 or 1
X'91nn' 0 or 1
X'B4nn' 0 or more; must be paired with X'B5nn'
X'B5nn' 0 or more; must be paired with X'B4nn'
X'D1nn' 0 or 1
X'E0nn' 0 or 1
X'E1nn' 0 or 1
X'E8nn' 0 or 1; must be paired with X'E9nn'
X'E9nn' 0 or 1; must be paired with X'E8nn'
X'F2nn' 0 or more, up to a maximum of 8
X'F3nn' 0 or more, up to a maximum of 8
X'F4nn' 0 or 1
X'F9nn' 0 or 1
X'FCnn' 0 or 1
Map Medium Overlay
(MMO)
X'D3B1DF'
Map Page Segment (MPS) X'D3B15F' The page segment must be a MO:DCA page segment subject to all
applicable IS/3 restrictions; see T able 27.
Map Suppression (MSU) X'D3ABEA'
No Operation (NOP) X'D3EEEE'
MO:DCA IS/3


Table 35 IS/3 Structured Fields without Triplets (cont'd.)
IS/3 Structured Fields without Triplets
Structured Field Name
Structured
Field ID Differences from general MO:DCA Architecture
Object Area Position
(OBP)
X'D3AC6B' The object area position in IS/3 only supports the 4 orthogonal
orientations: 0 degrees, 90 degrees, 180 degrees, and 270 degrees.
Note: IS/3 does not impose any restrictions on object area position or
object content position as was done in IS/1. Since IS/3 also supports
MO:DCA page segments, it includes support for positioning objects in a
page segment at the IPS reference point using RefCSys = X'00', which
IS/1 did not support.
Object Container Data
(OCD)
X'D3EE92' Content as defined by the object types listed in T able 28 for
presentation object containers and T able 29 for non-
presentation object containers.
Page Position Format 2
(PGP)
X'D3B1AF'
Presentation T ext Data
Descriptor Format 2 (PTD)
X'D3B19B' PTD content as defined for MO:DCA data streams by PTOCA, with the
following optional control sequences. Each can have 0 or more
occurrences:
• AMB, AMI, SBI, SCFL, SEC, SIA, SIM, STC, STO [MODCA-7-196]
Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-197]
• X units per unit base = Y units per unit base [MODCA-7-198]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-199]
Presentation T ext Data
(PTX)
X'D3EE9B' The content must comply with the PTOCA PT3 subset definition.
5.4 Structured Fields with Triplets [MODCA-7-200]
The following IS/3 structured fields support triplets.
Table 36. IS/3 Structured Fields with Triplets
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Bar Code Data Descriptor
(BDD)
X'D3A6EB' BDD content as defined by the BCOCA BCD2 subset definition.
X'4E' 0 or 1
Measurement unit restrictions:
• unit base = 10 inches [MODCA-7-201]
• X units per unit base = Y units per unit base [MODCA-7-202]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-203]
Container Data Descriptor
(CDD)
X'D3A692' X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the container contains
one of the multi-page TIFF object types supported in IS/3 (see
T able 28); otherwise should not be specified.
X'9A' 0 or 1 occurrences if the container contains one of the object
types listed in T able 28; otherwise should not be
specified. Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-204]
• X units per unit base and Y units per unit base can be different [MODCA-7-205]
• range for X units per unit base and Y units per unit base is 1- [MODCA-7-206]
32,767
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Index Element (IEL) X'D3B2A7' X'02' Tpe X'CA' 1
X'2D' 1
X'02' 0 or 1 occurrence of one of the following:
• Type X'0D' [MODCA-7-207]
• Type X'87' [MODCA-7-208]
X'02' Tpe X'8D' 0 or 1
X'56' 0 or 1
X'57' 0 or 1
X'58' 0 or 1
X'59' 0 or 1
X'5A' 0 or 1 occurrences for each object type counted
X'5E' 0 or 1 occurrences for pages counted
X'81' 0 or 1
X'83' 0 or 1
Invoke Medium Map (IMM) X'D3ABCC'
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Include Object (IOB) X'D3AFC3' The include object in IS/3 only supports the 4 orthogonal orientations: 0
degrees, 90 degrees, 180 degrees, and 270 degrees.
X'10' 1 occurrence if ObjType = X'92' - Other object data;
otherwise should not be specified
X'4B' 1 occurrence if the IOB specifies an override for any of
the following:
• XocaOset [MODCA-7-209]
• YocaOset [MODCA-7-210]
• XoaSize [MODCA-7-211]
• YoaSize [MODCA-7-212]
Otherwise should not be specified. Measurement unit
restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-213]
• X units per unit base = Y units per unit base [MODCA-7-214]
• range for X units per unit base and Y units per unit [MODCA-7-215]
base is 1-32,767
X'01' 0 or more
Note: IS/3 requires full support of the CGCSGID (X'01')
triplet on the IOB.
X'02' Tpe X'01' 0 or 1
X'02' Tpe X'DE' 0 or more
X'02' Tpe X'BE' 0 or more
X'04' 0 or 1
X'4C' 0 or 1
X'4E' 0 or 1
X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the IOB
includes one of the multi-page TIFF object types
supported in IS/3 (see T able 28); otherwise
should not be specified.
X'70' 0 or 1
X'91' 1 occurrence for each FQN type X'DE' that references a
CMR; otherwise should not be specified.
X'95' 0 or 1
X'9A' 0 or 1 occurrences if the container contains one of the
object types listed in T able 28; otherwise
should not be specified. Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-216]
• X units per unit base and Y units per unit base can be [MODCA-7-217]
different
• range for X units per unit base and Y units per unit [MODCA-7-218]
base is 1-32,767
Include Page Overlay
(IPO)
X'D3AFD8' X'02' Tpe X'01' 0 or 1. The overlay name must be less than or equal to 8
characters (bytes) in length.
Note: IS/3 does not impose any restrictions on the page overlay
orientation and origin, as was done in IS/1.
Include Page Segment
(IPS)
X'D3AF5F' The page segment must be a MO:DCA page segment subject to all
applicable IS/3 restrictions; see T able 27.
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Link Logical Element (LLE) X'D3B490' X'02' Tpe X'09' 0 or 1 in source and target RG
X'02' Tpe X'0A' 0 or 1 in source and target RG
X'02' Tpe X'0C' 0 or 1 in each RG
X'02' Tpe X'0D' 0 or 1 in source and target RG
X'02' Tpe X'83' 0 or 1 in source and target RG
X'02' Tpe X'87' 0 or 1 in source and target RG
X'02' Tpe X'B0' 0 or 1 in source and target RG
X'02' Tpe X'CE' 0 or 1 in source and target RG
X'10' 1 occurrence in source and target RG that specifies
FQN Type X'CE'; otherwise must not be specified.
X'4B' 0 or 1 occurrences in source or target RG that specifies
X'4D' triplet; otherwise should not be specified.
Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-219]
• X units per unit base = Y units per unit base [MODCA-7-220]
• range for X units per unit base and Y units per unit [MODCA-7-221]
base is 1-32,767
X'4D' 0 or more in source and target RG
X'82' 0 or more in attribute RG
Map Bar Code Object
(MBC)
X'D3ABEB' X'04' 1. Mapping options:
X'00' Position
Map Container Data
(MCD)
X'D3AB92' X'04' 1. Mapping options:
X'00' Position
X'10' Position and trim
X'20' Scale to fit
X'30' Center and trim
X'60' Scale to fill
Medium Descriptor (MDD) X'D3A688' X'68' 0 or 1
Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-222]
• X units per unit base = Y units per unit base [MODCA-7-223]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-224]
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Map Data Resource
(MDR)
X'D3ABC3' X'02' 1 occurrence in each RG of one of the following:
• Type X'84' [MODCA-7-225]
• Type X'CE' [MODCA-7-226]
• Type X'DE' [MODCA-7-227]
X'10' 1 occurrence if RG specifies FQN Type X'CE' or X'DE';
otherwise should not be specified.
X'01' 0 or more.
Note: IS/3 requires full support of the CGCSGID (X'01')
triplet on the MDR.
X'02' Tpe X'BE' 0 or 1 occurrences in each RG that specifies FQN Type
X'DE'; otherwise should not be specified.
X'02' Tpe X'85' 0 or 1 occurrences in each RG that references a TTF/
OTF with FQN Type X'DE'; otherwise should not be
specified.
X'50' 0 or 1 occurrences in each RG that references a TTF/
OTF with FQN Type X'DE'; otherwise should not be
specified.
X'5A' 0 or 1 occurrences with ObjTpe=X'A8' in each RG that
references a CMR with FQN Type X'DE'; otherwise
should not be specified.
X'8B' 1 occurrence in each RG that references a TTF/OTF
with FQN Type X'DE'; otherwise should not be
specified.
X'91' 1 occurrence in each RG that references a CMR with
FQN Type X'DE'; otherwise should not be specified.
IS/3 does not include the FOCA code page reference using the
combination of CPGID/GCSGID specified with the Font Coded Graphic
Character Set Global Identifier X'20' triplet. IS/3 does include the FOCA
code page reference using the code page name specified with the FQN
type X'85' triplet.
Medium Finishing Control
(MFC)
X'D3A088' X'85' 1 or more
X'5A' 0 or 1 occurrences with ObjTpe=X'A8' when MFC
specified in DEG; otherwise should not be specified.
Map Graphics Object
(MGO)
X'D3ABBB' X'04' 1. Mapping options:
X'10' Position and trim
X'20' Scale to fit
X'30' Center and trim
Note that the Scale to fill mapping option is not included in IS/3.
Map Image Object (MIO) X'D3ABFB' X'04' 1. Mapping options:
X'10' Position and trim
X'20' Scale to fit
X'30' Center and trim
X'60' Scale to fill
Map Media type (MMT) X'D3AB88' X'02' Tpe X'11' 1 in each RG; may occur twice in each RG if specified
using FQN formats X'00' and X'10'.
X'22' 1 in each RG
Map Page Overlay (MPO) X'D3ABD8' X'02' Tpe X'84' 1 in each RG. The overlay name must be less than or
equal to 8 characters (bytes) in length.
X'24' 1 in each RG. The LID range is limited to X'01' - X'7F'.
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Object Area Descriptor
(OBD)
X'D3A66B' X'43' 1
X'4B' 1 occurrence. Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-228]
• X units per unit base = Y units per unit base [MODCA-7-229]
• range for X units per unit base and Y units per unit [MODCA-7-230]
base is 1-32,767
X'4C' 1
X'4E' 0 or 1
X'70' 0 or 1
Presentation Environment
Control (PEC)
X'D3A7A8' X'5A' 0 or 1 occurrences with ObjTpe=X'A8' when PEC
specified in DEG; otherwise should not be specified.
X'95' 0 or 1
X'97' 0 or 1 occurrences. Only the following value is
supported: Dev App = X'0000'.
Presentation Fidelity
Control (PFC)
X'D3B288' X'75' 0 or 1
X'86' 0 or 1
X'87' 0 or 1
X'88' 0 or 1
X'96' 0 or 1
Page Descriptor (PGD) X'D3A6AF' X'4E' 0 or 1
X'70' 0 or 1
Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-231]
• X units per unit base = Y units per unit base [MODCA-7-232]
• range for X units per unit base and Y units per unit base is 1-32,767 [MODCA-7-233]
Page Modification Control
(PMC)
X'D3A7AF' X'4B' 0 or 1 occurrences. Measurement unit restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-234]
• X units per unit base = Y units per unit base [MODCA-7-235]
• range for X units per unit base and Y units per unit [MODCA-7-236]
base is 1-32,767
X'6C' 0 or more
MO:DCA IS/3


Table 36 IS/3 Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from general MO:DCA Architecture noted
Preprocess Presentation
Object (PPO)
X'D3ADC3' X'02' 1 in each RG of one of the following:
• Type X'84' [MODCA-7-237]
• Type X'CE' [MODCA-7-238]
X'10' 1 occurrence in the RG if ObjType = X'92' - Other object
data; otherwise should not be specified.
X'4B' 1 occurrence if the RG specifies any of the following:
• XocaOset [MODCA-7-239]
• YocaOset [MODCA-7-240]
• XoaSize [MODCA-7-241]
• YoaSize [MODCA-7-242]
Otherwise should not be specified. Measurement unit
restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-243]
• X units per unit base = Y units per unit base [MODCA-7-244]
• range for X units per unit base and Y units per unit [MODCA-7-245]
base is 1-32,767
X'01' 0 or more in each RG
Note: IS/3 requires full support of the CGCSGID (X'01')
triplet on the PPO.
X'02' Tpe X'DE' 0 or more in each RG
X'02' Tpe X'BE' 0 or more occurrences in each RG that also specifies a
FQN type X'DE'.
X'04' 0 or 1 in each RG
X'4C' 0 or 1 in each RG
X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the RG
processes one of the multi-page TIFF object types
supported in IS/3 (see T able 28); otherwise
should not be specified.
X'91' 1 occurrence for each FQN type X'DE' in the RG that
references a CMR; otherwise should not be specified.
X'95' 0 or 1 in each RG
X'9A' 0 or 1 occurrences in the RG if the container contains
one of the object types listed in T able 28;
otherwise should not be specified. Measurement unit
restrictions:
• X unit base = Y unit base = 10 inches [MODCA-7-246]
• X units per unit base and Y units per unit base can be [MODCA-7-247]
different
• range for X units per unit base and Y units per unit [MODCA-7-248]
base is 1-32,767
T ag Logical Element (TLE) X'D3A090' X'01' 0 or more
Note: IS/3 requires full support of the CGCSGID X'01'
triplet on the TLE.
X'02' Tpe X'0B' 1
X'36' 1
X'02' 0 or 1 occurrence of one of the following:
• Type X'0D' [MODCA-7-249]
• Type X'87' [MODCA-7-250]
X'02' Tpe X'0C' 0 or 1
X'80' 0 or 1
MO:DCA IS/3


6.0 Architected Tables [MODCA-7-251]
The following tables are part of the IS/3 definition.
6.1 Standard OCA Color Value Table [MODCA-7-252]
All color values.
6.2 Color Mapping Table (CMT) [MODCA-7-253]
All parameters.
6.3 Resource Access Tables (RATs) [MODCA-7-254]
The following repeating group types.
TrueType/OpenType Font (TTF/OTF) Repeating Group
• Flag bits 0-4 [MODCA-7-255]
• The following table vectors, listed by ID and showing their allowed occurrences: [MODCA-7-256]
X'01' 1 or more
X'04' 1
X'08' 1
X'1A' 1
X'24' 1 or more
X'30' 0 or 1
Color Management Resource (CMR) Repeating Group
• Flag bits 1-5, 7, 8 [MODCA-7-257]
• The following table vectors, listed by ID and showing their allowed occurrences: [MODCA-7-258]
X'01' 1
X'04' 1
X'08' 1
X'18' 0 or 1
X'24' 1 or more
Data Object Resource (DOR) Repeating Group
All objects defined in T able 37.
• Flag bits 1-5 [MODCA-7-259]
• The following table vectors, listed by ID and showing their allowed occurrences: [MODCA-7-260]
X'01' 1
X'04' 1
X'08' 1
X'14' 1
X'18' 1
X'1C' 1
X'24' 1 or more; must be paired with TV X'28'
X'28' 1 or more; must be paired with TV X'24'
X'30' 0 or 1 for one of the non-IOCA object types listed in T able 37, with the following
restrictions:
MO:DCA IS/3


– X unit base = Y unit base = 10 inches
– X units per unit base and Y units per unit base can be different
– range for X units per unit base and Y units per unit base is 1-32,767
Table 37. Presentation Objects Processed with Data Object Resource (DOR) RAT
Component
ID
Object Type Encoded Object-type OID
05 IOCA FS10 X'06072B120004010105' [MODCA-7-261]
12 IOCA FS45 X'06072B12000401010C' [MODCA-7-262]
14 TIFF X'06072B12000401010E' [MODCA-7-263]
22 GIF X'06072B120004010116' [MODCA-7-264]
23 AFPC JPEG [MODCA-7-265]
Note: This object type was formerly referred to as JFIF (JPEG).
X'06072B120004010117'
60 TIFF without Transparency X'06072B12000401013C' [MODCA-7-266]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-7-267]
62 TIFF Multiple Image - without Transparency - File X'06072B12000401013E' [MODCA-7-268]
66 AFPC TIFF X'06072B120004010142' [MODCA-7-269]
7.0 Migration Functions included in IS/3 [MODCA-7-270]
MO:DCA migration functions are defined in Appendix C, “MO:DCA Migration Functions”,.
7.1 Obsolete Functions [MODCA-7-271]
No obsolete parameters, triplets, structured fields or objects are included in the IS/3 definition.
7.2 Retired Functions [MODCA-7-272]
No retired parameters, triplets, structured fields or objects are included in the IS/3 definition.
7.3 Coexistence Functions [MODCA-7-273]
No coexistence parameters, triplets, structured fields or objects are included in the IS/3 definition.
MO:DCA IS/3


MO:DCA AFP Archive Interchange Set (AFP/A)
The MO:DCA AFP Archive (AFP/A) interchange set is defined in the ISO 18565:2015 “Document management
– AFP/Archive” standard. Refer to this standard for a complete definition of AFP/A.
MO:DCA AFP/A


