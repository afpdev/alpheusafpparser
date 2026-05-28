Chapter 5. MO:DCA Structured Fields
This chapter:
* Briefly describes the purpose of each MO:DCA structured field [MODCA-5-001]
* Provides the syntax and semantics for each MO:DCA structured field [MODCA-5-002]
* Identifies each structured field's parameter set [MODCA-5-003]
* Identifies exception conditions [MODCA-5-004]
General Information
Chapter 3, “MO:DCA Overview”, provides a general discussion of the syntax and semantics of
MO:DCA structured fields. Detailed formats, syntaxes and semantics are provided here to enable product
developers to design and produce applications that can use MO:DCA data streams.
The syntax tables in this chapter describe the less restrictive requirements of the overall architecture. Thus,
these syntax tables may not agree exactly with a specific interchange set with regard to:
* Whether a data element is mandatory or optional [MODCA-5-005]
* The number of times a particular data element may validly occur [MODCA-5-006]
* The order in which the data elements must occur [MODCA-5-007]
In those cases where there is disagreement with an interchange set, the interchange set requirement governs.
The exception condition column of the syntax tables for these structured fields identifies only those exception
conditions that could occur for the individual parameters.
Structured fields that have triplets reflect an exception condition code of either X'10' or X'14' in this column for
the triplet entry. This reflects only the possibility that the structured field could include an invalid triplet, or that a
required triplet could be missing. Any exception conditions relating to a triplet's data elements are addressed in
Chapter 6, “MO:DCA Triplets”,.
Those exception conditions that may occur because of special conditions such as a mismatch between the
individual parameters of one or more structured fields are listed under the Semantics headings when only one
such exception condition is identified. When multiple exception conditions are identified, all are listed under the
“Exception Condition Summary” heading. A more detailed explanation may be provided under the “Semantics”
heading.
Architected defaults are identified in the semantic description of the individual parameters. When an
architected default exists for an entire structured field, the default is documented at the end of the semantic
description for that structured field.
The following structured field definitions are sorted in alphabetical order based on structured field acronym. [MODCA-5-008]


### Begin Active Environment Group (BAG)

The Begin Active Environment Group structured field begins an Active Environment Group, which establishes
the environment parameters for the page or overlay. The scope of the active environment group is the
containing page or overlay. [MODCA-5-009]

#### BAG (X'D3A8C9') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8C9' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-010]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **AEGName** | | Name of the active environment group | O | X'02' [MODCA-5-013] |
| 8–n | Triplets | | | See BAG Semantics for triplet applicability. | O | X'10' [MODCA-5-014] |

#### BAG Semantics

**AEGName** Is the name of the active environment group.

The page or overlay containing the Begin Active Environment Group structured field must also
contain a subsequent matching End Active Environment Group structured field, or a X'08'
exception condition exists.

**Triplets** Appear as follows: [MODCA-5-015]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-018] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-019] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-020]

#### BAG Exception Condition Summary

**X'08'** A subsequent matching End Active Environment Group structured field is not present in the page or overlay.


### Begin Bar Code Object (BBC)

The Begin Bar Code Object structured field begins a bar code data object, which becomes the current data
object.

#### BBC (X'D3A8EB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8EB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-021]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **BCdoName** | | Name of the bar code data object | O | X'02' [MODCA-5-024] |
| 8–n | Triplets | | | See BBC Semantics for triplet applicability. | O | X'10' [MODCA-5-025] |

#### BBC Semantics

**BCdoName** Is the name of the bar code data object.

The page, overlay, or resource group containing the Begin Bar Code Object structured field
must also contain a subsequent matching End Bar Code Object structured field, or a X'08'
exception condition exists.

**Triplets** Appear as follows: [MODCA-5-026]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-029] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Bar Code Object structured field name and is used as the name of the bar code data object. [MODCA-5-030] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-031] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-032] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-033] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-034]

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
*   **Line Data Object Position Migration (X'27') triplet**; see “Line Data Object Position Migration Triplet X'27'”. [MODCA-5-035]

#### BBC Exception Condition Summary

**X'08'** A subsequent matching End Bar Code Object structured field is not present in the page, overlay, or resource group. [MODCA-5-036]


### Bar Code Data (BDA)

The Bar Code Data structured field contains the data for a bar code object. [MODCA-5-037]

#### BDA (X'D3EEEB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3EEEB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-038]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **BCOCAdat** | | Up to 32,759 bytes of BCOCA-defined data | O | X'00' [MODCA-5-041] |

#### BDA Semantics

**BCOCAdat** Contains the BCOCA-defined data. See the MO:DCA environment appendix in the *Bar Code
Object Content Architecture Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-042]


### Bar Code Data Descriptor (BDD)

The Bar Code Data Descriptor structured field contains the descriptor data for a bar code data object. [MODCA-5-043]

#### BDD (X'D3A6EB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A6EB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-044]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **BCOCAdes** | | Up to 32,759 bytes of BCOCA-defined descriptor data | O | X'00' [MODCA-5-047] |

#### BDD Semantics

**BCOCAdes** Contains the BCOCA-defined descriptor data. See the MO:DCA environment appendix in the
*Bar Code Object Content Architecture Reference* for detailed information.

**Architecture Note:** The BCOCA-defined descriptor supports the Color Specification (X'4E')
triplet.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-048]


### Begin Document Environment Group (BDG)

The Begin Document Environment Group structured field begins a document environment group, which
establishes the environment parameters for the form map object. The scope of the document environment
group is the containing form map. [MODCA-5-049]

#### BDG (X'D3A8C4') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8C4' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-050]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **DEGName** | | Name of the document environment group | O | X'02' [MODCA-5-053] |
| 8–n | Triplets | | | See BDG Semantics for triplet applicability. | O | X'10' [MODCA-5-054] |

#### BDG Semantics

**DEGName** Is the name of the document environment group.

The form map containing the Begin Document Environment Group structured field must also
contain a subsequent matching End Document Environment Group structured field, or a X'08'
exception condition exists.

**Triplets** Appear as follows: [MODCA-5-055]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-058] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-059] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-060]

#### BDG Exception Condition Summary

**X'08'** A subsequent matching End Document Environment Group structured field is not present in the form map.


### Begin Document Index (BDI)

The Begin Document Index structured field begins the document index. [MODCA-5-061]

#### BDI (X'D3A8A7') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8A7' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-062]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **IndxName** | | Name of the document index | O | X'02' [MODCA-5-065] |
| 8–n | Triplets | | | See BDI Semantics for triplet applicability. | O | X'10' [MODCA-5-066] |

#### BDI Semantics

**IndxName** Is the name of the document index.

The print file containing the Begin Document Index structured field must also contain a
subsequent matching End Document Index structured field, or a X'08' exception condition
exists.

**Triplets** Appear as follows: [MODCA-5-067]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-070] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Document Index structured field name and is used as the name of the document index. [MODCA-5-071] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. The Fully Qualified Name type that may appear is X'83'—Begin Document Name. Specifies the name of the document that is indexed by this document index. See “Fully Qualified Name Triplet X'02'”. [MODCA-5-072] [MODCA-5-073] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-074] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-077] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-078] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-079]

#### BDI Exception Condition Summary

**X'08'** A subsequent matching End Document Index structured field is not present in the print file. [MODCA-5-080]


### Begin Document (BDT)

The Begin Document structured field names and begins the document. [MODCA-5-081]

#### BDT (X'D3A8A8') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8A8' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-082]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **DocName** | | Name of the document | M | X'06' [MODCA-5-085] |
| 8–9 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-086] |
| 10–n | Triplets | | | See BDT Semantics for triplet applicability. | M | X'14' [MODCA-5-087] |

#### BDT Semantics

**DocName** Is the name of the document described by the data stream. If a Fully Qualified Name type
X'01' (Replace First GID) triplet appears in this structured field, the name specified in this
parameter is ignored and the GID provided by the triplet is used instead.

**Architecture Note:** The semantic that stated “If the value of the first two bytes of DocName
are X'FFFF', the processing system provides the document name” is no longer
applicable and has been removed from the architecture. The document name on the
BDT is first specified by the application that creates the document, and may be modified
later by applications that process the document regardless of whether the first two bytes
of DocName are X'FFFF' or not.

**Triplets** Appear as follows: [MODCA-5-088]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Mandatory. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-091] <br><br> **Implementation Note:** Not all MO:DCA products have historically implemented this triplet as a mandatory triplet on the BDT; instead they have assumed that the encoding for parameters with CHAR data type in a MO:DCA document is EBCDIC-based. To accommodate this practice, the MO:DCA IS/3 interchange set defines this triplet as optional and does not include support for the inheritance of encoding scheme definition by lower-level document components. Furthermore, IS/3 specifies the default encoding for character strings with CHAR data type to be defined by CCSID 500 (corresponding to the combination of CPGID 500 and GCSGID 697). |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the Begin Document structured field name and is used as the name of the document. |
| X'02' | | **Fully Qualified Name** Optional. May occur once. The Fully Qualified Name type that may appear is X'0A'—Begin Resource Group Name. Specifies the name of a resource group that contains resources referenced in this document. See “Fully Qualified Name Triplet X'02'”. |
| X'02' | | **Fully Qualified Name** Optional. May occur once. The Fully Qualified Name type that may appear is X'98'—Begin Document Index Name. Specifies the name of a document index resource object that provides index information for this document. See “Fully Qualified Name Triplet X'02'”. |
| X'18' | | **MO:DCA Interchange Set** For interchange data streams, this triplet is mandatory and must occur once. For private or exchange data streams, this triplet is not permitted. See “MO:DCA Interchange Set Triplet X'18'”. |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |
| X'72' | | **Universal Date and Time Stamp** Optional. May occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. |
| X'8F' | | **MO:DCA Function Set** Mandatory if the MO:DCA Interchange Set (X'18') triplet is specified to indicate compliance with an interchange set and one or more function sets, in which case this triplet must occur at least once. If the MO:DCA Interchange Set triplet does not indicate compliance with an interchange set plus one or more function sets, or if that triplet is not specified, the MO:DCA Function Set triplet must not be specified. See “MO:DCA Function Set Triplet X'8F'”. |
The data stream containing the Begin Document structured field must also contain a subsequent matching End
Document structured field, or a X'08' exception condition exists.
**Architecture Note:**  In AFP environments, the following retired triplet is used on this structured field: [MODCA-5-092]
### Begin Document (BDT)


* Object Function Set Specification (X'21') triplet; see “Object Function Set Specification Triplet X'21'” [MODCA-5-093]
.
#### BDT Exception Condition Summary
X'01' This condition exists when:
* Multiple type X'01' (Replace First GID) Fully Qualified Name triplets appear. [MODCA-5-094]
* Multiple MO:DCA Interchange Set (X'18') triplets appear. [MODCA-5-095]
X'08' A subsequent matching End Document structured field is not present in the data stream. [MODCA-5-096]
### Begin Document (BDT)


### Begin Form Map (BFM)

The Begin Form Map structured field begins a form map object, also called a form definition or formdef. A form
map is a print control resource object that contains one or more medium map resource objects that are
invokable on document and page boundaries and that specify a complete set of presentation controls. It also
contains an optional document environment group (DEG) that defines the presentation environment for the
form map.

#### BFM (X'D3A8CD') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8CD' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-097]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **FMName** | | Name of the form map | O | X'02' [MODCA-5-100] |
| 8–n | Triplets | | | See BFM Semantics for triplet applicability. | O | X'10' [MODCA-5-101] |

#### BFM Semantics

**FMName** Is the name of the form map.

A form map resource object must be terminated with a subsequent matching End Form Map
structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-102]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-105] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-106] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-107] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-108] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-109]

#### BFM Exception Condition Summary

**X'08'** The form map is not terminated with a subsequent matching End Form Map structured field. [MODCA-5-110]


### Begin Graphics Object (BGR)

The Begin Graphics Object structured field begins a graphics data object which becomes the current data
object.

#### BGR (X'D3A8BB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8BB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-111]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **GdoName** | | Name of the graphics data object | O | X'02' [MODCA-5-114] |
| 8–n | Triplets | | | See BGR Semantics for triplet applicability. | O | X'10' [MODCA-5-115] |

#### BGR Semantics

**GdoName** Is the name of the graphics data object.

The page, overlay, or resource group containing the Begin Graphics Object structured field
must also contain a subsequent matching End Graphics Object structured field, or a X'08'
exception condition exists.

**Triplets** Appear as follows: [MODCA-5-116]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-119] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Graphics Object structured field name and is used as the name of the graphics data object. [MODCA-5-120] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-121] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-122] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-123] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-124]

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
*   **Line Data Object Position Migration (X'27') triplet**; see “Line Data Object Position Migration Triplet X'27'”. [MODCA-5-125]

#### BGR Exception Condition Summary

**X'08'** A subsequent matching End Graphics Object structured field is not present in the page, overlay, or resource group. [MODCA-5-126]


### Begin Image Object (BIM)

The Begin Image Object structured field begins an IOCA image data object, which becomes the current data
object.

**Architecture Note:** A migration form of the image object is supported in AFP environments and is defined as
the IM Image Object in “IM Image Object”. [MODCA-5-127]

#### BIM (X'D3A8FB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A8FB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-128]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **IdoName** | | Name of the image data object | O | X'02' [MODCA-5-131] |
| 8–n | Triplets | | | See BIM Semantics for triplet applicability. | O | X'10' [MODCA-5-132] |

#### BIM Semantics

**IdoName** Is the name of the IOCA image data object.

The page, overlay, or resource group containing the Begin Image Object structured field must
also contain a subsequent matching End Image Object structured field, or a X'08' exception
condition exists.

**Triplets** Appear as follows: [MODCA-5-133]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-136] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Image Object structured field name and is used as the identifier of the image data object. The identifier may be specified in one—and only one—of the following formats:<br>• If FQNFmt = X'00', the identifier is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. [MODCA-5-137] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-138] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-141] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-142] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory.

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
*   **Line Data Object Position Migration (X'27') triplet**; see “Line Data Object Position Migration Triplet X'27'”. [MODCA-5-143]

#### BIM Exception Condition Summary

**X'08'** A subsequent matching End Image Object structured field is not present in the page, overlay, or resource group.


### Begin Medium Map (BMM)

The Begin Medium Map structured field begins a medium map resource object. A medium map is a print control resource object that contains a complete set of controls for presenting pages on physical media such as sheets and for generating multiple copies of sheets with selectable modifications. These controls may be grouped into two categories:

*   Medium level controls [MODCA-5-144]
*   Page level controls [MODCA-5-145]

Medium level controls are controls that affect the medium, such as the specification of medium overlays, medium size, medium orientation, medium copies, simplex or duplex, medium finishing, media type, and media source and destination selection. These controls are defined by the Map Medium Overlay (MMO), Medium Descriptor (MDD), Medium Copy Count (MCC), Medium Finishing Control (MFC), Map Media Type (MMT), Map Media Destination (MMD), Presentation Environment Control (PEC), and Medium Modification Control (MMC) structured fields. Page level controls are controls that affect the pages that are placed on the medium, such as the specification of page modifications, page position, and page orientation. These controls are defined by the Map Page Overlay (MPO), Page Position (PGP), and Page Modification Control (PMC) structured fields.

#### BMM (X'D3A8CC') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8CC' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-146]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-147] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-148] |
| 0–7 | CHAR | MMName | | Name of the medium map | M | X'06' [MODCA-5-149] |
| 8–n | Triplets | | | See BMM Semantics for triplet applicability | O | X'10' [MODCA-5-150] |

#### BMM Semantics

**MMName** Is the name of the medium map.

A medium map resource object must be terminated with a subsequent matching End Medium Map structured field, or a X'08' exception condition exists. [MODCA-5-151]

**Triplets** Appear as follows: [MODCA-5-152]

| Triplet | Type | Usage [MODCA-5-153] |
| :--- | :--- | :--- [MODCA-5-154] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-155] |
| X'45' | | **Media Eject Control** Optional. May occur once. See “Media Eject Control Triplet X'45'”. Specifies the type of media eject that should be performed when this medium map is invoked and N-up partitioning is specified. This triplet is ignored when it occurs on the medium map that is activated at the beginning of a document regardless of whether this medium map is explicitly invoked or implicitly invoked as the default. [MODCA-5-156] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-157] |

**Note:** If the X'45' triplet is not present, the architected default for the EjCtrl parameter in the triplet is X'01'; that is, perform a sheet eject and activate all controls specified by the medium map.

#### BMM Exception Condition Summary

**X'01'** This exception condition exists when:

*   The Begin Medium Map structured field specifies a conditional eject to a front-side partition [MODCA-5-158] and the PGP in the medium map does not specify a front-side partition.
*   The Begin Medium Map structured field specifies a conditional eject to a back-side partition [MODCA-5-159] and the PGP in the medium map does not specify a back-side partition.

**X'08'** The medium map is not terminated with a subsequent matching End Medium Map structured field.

### Begin Overlay (BMO)

The Begin Overlay structured field begins an overlay. An overlay contains an active environment group to establish parameters such as the size of the overlay's presentation space and the fonts to be used by the data objects. It may also contain any mixture of:

*   Bar code objects [MODCA-5-160]
*   Graphics objects [MODCA-5-161]
*   Image objects [MODCA-5-162]
*   Object containers [MODCA-5-163]
*   Presentation text objects [MODCA-5-164]

#### BMO (X'D3A8DF') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8DF' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-165]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-166] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-167] |
| 0–7 | CHAR | OvlyName | | Name of the overlay | M | X'06' [MODCA-5-168] |
| 8–n | Triplets | | | See BMO Semantics for triplet applicability | O | X'10' [MODCA-5-169] |

#### BMO Semantics

**OvlyName** Is the name of the overlay. This name may not appear on more than one Begin Overlay within the same resource group or a X'01' exception condition exists.

The resource group containing the Begin Overlay structured field must also contain a subsequent matching End Overlay structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-170]

| Triplet | Type | Usage [MODCA-5-171] |
| :--- | :--- | :--- [MODCA-5-172] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-173] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is: X'01'—Replace First GID Name. This GID overrides the Begin Overlay structured field name and is used as the name of the overlay. [MODCA-5-174] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-175] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-179] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. Overlays reside in external resource libraries or in resource groups. See “Resource Groups” for details on locating resource objects within libraries and resource groups. [MODCA-5-180] |

**Application Note:** In environments that include an intermediate caching device such as Remote Print Manager (RPM) or Distributed Print Facility (DPF), time stamps on the BMO structured field must be specified using the X'62' triplet. [MODCA-5-176]

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory.

**Architecture Note:** In AFP environments, the following retired triplets are used on this structured field:

*   Object Checksum (X'63') triplet; see “Object Checksum Triplet X'63'” [MODCA-5-181]
*   Object Origin Identifier (X'64') triplet; see “Object Origin Identifier Triplet X'64'” [MODCA-5-182]

#### BMO Exception Condition Summary

**X'01'** Multiple Begin Overlay structured fields with the same name exist within the same resource group.

**X'08'** A subsequent matching End Overlay structured field is not present in the same resource group.


### Begin Named Page Group (BNG)

The Begin Named Page Group structured field begins a page group, which is a named, logical grouping of sequential pages. A page group may contain other nested page groups. All pages in the page group and all other page groups that are nested in the page group inherit the attributes that are assigned to the page group using TLE structured fields. [MODCA-5-183]

#### BNG (X'D3A8AD') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8AD' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-184]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-185] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-186] |
| 0–7 | CHAR | PGrpName | | Name of the page group | M | X'06' [MODCA-5-187] |
| 8–n | Triplets | | | See BNG Semantics for triplet applicability | O | X'10' [MODCA-5-188] |

#### BNG Semantics

**PGrpName** Is the name of the page group.

The document containing the Begin Named Page Group structured field must also contain a subsequent matching End Named Page Group structured field, or a X'08' exception condition exists.

If the Keep Group Together (X'9D') triplet is specified on the Begin Named Page Group structured field, the name of the page group must be unique in the document, and the same name must be specified on the corresponding End Named Page Group structured field, or a X'01' exception condition exists. That is, in this case, the value X'FFFF' cannot be specified for the page group name in the ENG structured field.

**Triplets** Appear in the Begin Named Page Group structured field as follows: [MODCA-5-189]

| Triplet | Type | Usage [MODCA-5-190] |
| :--- | :--- | :--- [MODCA-5-191] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-192] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the Begin Named Page Group structured field name and is used as the name of the page group. [MODCA-5-193] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'8D'—Begin Medium Map Reference. Specifies the name of the medium map that is active at the beginning of the page group. [MODCA-5-196] |
| X'56' | | **Medium Map Page Number** Optional. May occur once. Specifies the sequence number of the first page-group page in the set of sequential pages controlled by the medium map that is active at the beginning of the page group. The first page in the set has sequence number 1. See “Medium Map Page Number Triplet X'56'”. |
| X'5E' | | **Object Count** Optional. May occur once for each subordinate object type counted. Specifies how many subordinate objects of a particular type, such as a page, are contained within the page group. See “Object Count Triplet X'5E'”. |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |
| X'83' | | **Presentation Control** Optional. May occur once. Specifies whether the page group is intended to be indexed. If this triplet is not specified, the architected default is that the page group is intended to be indexed. This triplet is ignored for printing. See “Presentation Control Triplet X'83'”. |
| X'9D' | | **Keep Group Together** Optional. May occur once. Specifies that the page group should be kept together for the purpose indicated by the triplet. See “Keep Group Together Triplet X'9D'”. |

**Application Notes:**

1.  **FQN type X'8D' and Triplet X'56':** This triplet is typically specified on the BNG structured fields when the page group is to be archived with a specific form map. It allows the page group to be retrieved and viewed at a later time without “playing back” the whole document. This triplet is ignored by print servers. Note that similar functionality can be achieved by specifying the Page Position Information (X'81') triplet on the BPG for the pages in the page group.
2.  The FQN Begin Medium Map Reference (type X'8D') triplet and the Medium Map Page Number (X'56') [MODCA-5-197] triplet may be used by viewing applications to present the page group in standalone fashion as it would be presented within the context of the complete document. These triplets are ignored by print servers. [MODCA-5-198]
3.  Page groups are often processed in standalone fashion, that is, they are indexed, retrieved, and presented outside the context of the containing document. While the pages in the group are independent of each other and of any other pages in the document, their formatting on media depends on when the last medium map was invoked and on how many pages precede the BNG since this invocation. To make the media formatting of page groups self-contained, a medium map should be invoked at the beginning of the page group between the Begin Named Group (BNG) structured field and the first Begin Page (BPG) structured field. If this is not done, the presentation system may need to “play back” all pages between the invocation of the active medium map and the BNG to determine media formatting such as sheet-side and partition number for the first page in the group. It is therefore strongly recommended that in environments where standalone page group processing is required or anticipated, page groups are built with an Invoke Medium Map (IMM) structured field specified after the BNG and before the first BPG. [MODCA-5-199]
4.  Some AFP applications that generate page groups will support a user option that ensures that an IMM is [MODCA-5-200] specified after BNG and before the first BPG, and some AFP archive servers will expect an IMM there and may not present the page group correctly if none is found. However, note that this may cause the complete document to print differently.
5.  A newer method to specify how a page or page group should be formatted involves use of the Page [MODCA-5-201] Position Information (X'81') triplet. This triplet may be specified on a BPG and indicates the repeating group in the PGP structured field in the active medium map that should be used to format the page.
6.  When the Keep Group Together (X'9D') triplet on the BNG specifies GrpFnct = X'01' - Keep group together [MODCA-5-202] as a recovery unit, full operation of this function in an IPDS environment requires that this group starts on a sheet boundary. It is therefore strongly recommended that if this triplet is specified on the BNG, a sheet eject is generated by:
    *   immediately preceding the BNG by an Invoke Medium Map (IMM) that causes a sheet eject, or [MODCA-5-203]
    *   following the BNG with an IMM that causes a sheet eject, as long as that IMM occurs before the first [MODCA-5-204] Begin Page (BPG) in the page group
    If such an IMM is not specified, IPDS printers in general will not be able to treat the group as a logical unit for error recovery, but normally can still indicate when an error has occurred while printing such a group.
7.  Using cut-sheet emulation can affect the printer’s ability to keep recovery unit groups together because: [MODCA-5-205]
    *   the printer increments the page counters on a sheet basis rather than on a sheetlet basis [MODCA-5-206]
    *   the server cannot control when a group is on a sheet boundary [MODCA-5-207]
    When cut-sheet emulation is being used, if a “Keep Together” page group does not begin on a sheet boundary, the group operation is suspended and blank sheets might occur within that group; this results in a presentation-system exception (for example, IPDS exception ID X'4040..00' or X'0140..00' with action code X'2B'). For example, when the last pages of a group are on the left sheetlet and the first pages of another group are on the right sheetlet, the “Keep Together” group operation will be suspended for the second group.

**Architecture Notes:**

1.  **Keep Group Together recovery unit:** If this triplet specifies GrpFnct = X'01' - Keep group together as a recovery unit, full operation of this function at the IPDS level requires that the page group start on a sheet boundary.
2.  If page group level indexing is used for a document that contains page groups, it is recommended that the page group name, whether it is specified by an 8-byte token name or by a fully qualified name, be unique with respect to other page group names within the document.

**Nesting Rules for Keep Group Together recovery units**

When the data stream contains page groups defined by the Keep Group Together (X'9D') triplet on the BNG and also generates sheet groups based on functions specified in medium maps, such as medium finishing groups, care must be taken to avoid overlap of these two types of groups. In particular, the following rules must be followed:

*   A “Keep Together” page group must not be nested inside another “Keep Together” page group. [MODCA-5-208]
*   Sheet groups may be nested inside “Keep Together” page groups and vice versa, but the two group types [MODCA-5-209] must not overlap. That is, the two group types, if specified, must be properly nested. For example, if a “Keep Together” page group is started after a sheet group, it must be terminated before or at the point in the data stream where the sheet group is terminated. The same is true if a sheet group is started after a “Keep Together” page group: the sheet group must be terminated before or at the point in the data stream where the “Keep Together” page group is terminated. [MODCA-5-210]

If the above rules are not followed, a group may end up being terminated prematurely, and/or exceptions may be generated by the presentation system. [MODCA-5-211]

#### BNG Exception Condition Summary

**X'01'** The same subordinate object type, such as a page, is counted in more than one X'5E' triplet.

**X'08'** A subsequent matching End Named Page Group structured field is not present in the document.


### Begin Object Container (BOC)

The Begin Object Container structured field begins an object container, which may be used to envelop and carry object data. The object data may or may not be defined by an AFP architecture. [MODCA-5-212]

#### BOC (X'D3A892') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A892' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-213]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-214] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-215] |
| 0–7 | CHAR | ObjCName | | Name of the object container | M | X'06' [MODCA-5-216] |
| 8–n | Triplets | | | See BOC Semantics for triplet applicability | M | X'14' [MODCA-5-217] |

#### BOC Semantics

**ObjCName** Is the name of the object container.

The page, overlay, or resource group containing the Begin Object Container structured field must also contain a subsequent matching End Object Container structured field, or a X'08' exception condition exists.

**Triplets** Appear in the Begin Object Container structured field as follows: [MODCA-5-218]

| Triplet | Type | Usage [MODCA-5-219] |
| :--- | :--- | :--- [MODCA-5-220] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-221] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the Begin Object Container structured field name and is used as the identifier of the object container. The identifier may be specified in one—and only one—of the following formats:<br>* If FQNFmt = X'00', the identifier is a character-encoded name. [MODCA-5-222] See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. The character-encoded name on the BOC is optional if the container is in a print file level resource group and the name is already specified on the BRS that immediately precedes the BOC. If the object in the container is a TrueType/OpenType font (TTF), this version of the triplet may occur more than once, and each instance of the triplet is used to specify the full font name in a language used in the font naming table. The character encoding is UTF-16BE.<br>* If FQNFmt = X'10', the identifier is a ASN.1 OID encoded using [MODCA-5-223] the definite short form. This format provides a unique and system-independent method to identify a resource. It may be used to identify resources that are resident in, or have been captured by, the presentation device. Such an identifier is referred to as an object OID. Note that the object OID is associated with the resource content; it does not reflect the MO:DCA wrappers used to carry the content. If the BOC specifies an object OID and envelopes either a TTF/OTF, a TrueType collection file, a data object, or a CMR, the OID may be used to locate a printer-resident version of the object. It also makes the object a candidate for capture by the printer. In this case this version of the triplet may only occur once. |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. This triplet is optional on the BOC if the container is in a print file level resource group and the same triplet is already specified on the BRS that immediately precedes the BOC. The Fully Qualified Name type that may appear is X'41'—Color Management Resource (CMR) Reference. This triplet may be specified on a BOC to indicate the following:<br>• If the resource is a Color Conversion (CC) CMR, this triplet specifies the name of a Link LK CMR that is to be mapped to the CC CMR in the container.<br>• If the resource is a generic Halftone (HT) or Tone Transfer Curve (TTC) CMR, this triplet specifies the name of a device-specific CMR of the same type that is to replace the generic CMR.<br>The identifier may be specified in the following format.<br>• If FQNFmt = X'00', the identifier is a character-encoded name. The character string that identifies the CMR must be the CMR name specified in the CMR. The character encoding is UTF-16BE. [MODCA-5-227] |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. This triplet is optional on the BOC if the container is in a print file level resource group and the same triplet is already specified on the BRS that immediately precedes the BOC. The Fully Qualified Name type that may appear is X'6E'—Data-object Font Base Font Identifier. This triplet may be specified on a BOC to indicate the following:<br>• If the BOC envelopes a TrueType Collection (TTC) file, the FQN type X'6E' triplet specifies a base TrueType/OpenType font that is contained in the collection.<br>The identifier may be specified in the following format.<br>• If FQNFmt = X'00', the identifier is a character-encoded name. The character string that identifies the font must be the full font name specified in a name record in the mandatory Naming Table of the font file. This parameter is specified in a name record with Name ID 4. An example of a full font name is Times New Roman Bold. Each instance of the FQN type X'6E' triplet with FQNFmt = X'00' is used to specify the full font name of the base font in a language used in the font's Naming Table. The character encoding is UTF-16BE, which matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the Naming Table. The byte order is big endian. For example, if the font Naming Table contains two name records for the full font name (Name ID 4), one in English - United States (LCID = X'0409') and one in German - Standard (LCID = X'0407'), both in the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001'), each of these names, encoded in UTF-16BE, is carried in a FQN type X'6E' triplet on the BOC. [MODCA-5-229] |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. This triplet is optional on the BOC if the container is in a print file level resource group and the same triplet is already specified on the BRS that immediately precedes the BOC. The Fully Qualified Name type that may appear is X'7E'—Data-object Font Linked Font Identifier. This triplet may be specified on a BOC to indicate the following:<br>• If the BOC envelopes a TrueType/OpenType font (TTF/OTF) file, the FQN type X'7E' triplet specifies a linked font for the base font. The order in which the FQN type X'7E' triplets are specified determines the order in which the linked fonts are processed.<br>• If the BOC envelopes a TrueType Collection (TTC) file, the FQN type X'7E' triplet specifies a linked font for the base font that is identified with the immediately preceding FQN type X'6E' triplet. Note that if the base font is specified in multiple languages using multiple FQN type X'6E' triplets, each instance of the FQN type X'6E' triplet must be followed by the sequence of FQN type X'7E' triplets that identify the linked fonts for the base font.<br>The identifier may be specified in the following format.<br>• If FQNFmt = X'00', the identifier is a character-encoded name. The character string that identifies the font must be the full font name specified in a name record in the mandatory Naming Table of the font file. This parameter is specified in a name record with Name ID 4. An example of a full font name is Times New Roman Bold. The character encoding is UTF-16BE, which matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the Naming Table. The byte order is big endian. [MODCA-5-233] |
| X'10' | | **Object Classification** Mandatory. Must occur once. Specifies information used to classify and identify the enveloped object data. See “Object Classification Triplet X'10'”. [MODCA-5-234] |
| X'57' | | **Object Byte Extent** Optional. May occur once. Specifies the number of bytes contained in the object container. The byte extent is measured starting with the first byte of the Begin Object Container (BOC) structured field up to and including the last byte of the End Object Container (EOC) structured field. See “Object Byte Extent Triplet X'57'”. [MODCA-5-235] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-236] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-237] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-238] |

**Application Notes:**

1.  **Fixed encoding:** It is strongly recommended that the X'01' triplet is specified even if the parameter on the BOC defines a fixed encoding. For example, if the parameter defines a fixed UTF-16BE encoding, the triplet can be specified using the CCSID form with CCSID=1200 (X'04B0').
2.  To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified. That is, the encoding scheme used for the resource include, the resource map, and the resource wrapper should be the same. For TrueType/OpenType fonts, optimal performance can be achieved by using UTF-16BE as the encoding scheme.

**Architecture Note:** If the BOC is used to carry a TTF/OTF, a data object, or a CMR in a print file level resource group, the FQN type X'01' triplet on the mandatory BRS must specify the full font name, or the data object name, or the CMR name using FQNFmt = X'00'. The FQN type X'01' triplet on the BOC may then be used to specify the object OID for the object using FQNFmt = X'10'; this enables the server to use a printer-resident version of the object and also makes the object a candidate for capture by the printer. [MODCA-5-224]

#### BOC Exception Condition Summary

**X'01'** This condition exists when a BOC parameter that is also allowed on a BRS in a BRS/BOC...EOC/ERS resource envelope and that is used for processing conflicts with the corresponding BRS parameter. Examples are:

*   Object Classification (X'10') triplet [MODCA-5-239]
*   FQN type X'41' - Color Management Resource (CMR) Reference triplet [MODCA-5-240]
*   FQN type X'6E' - Data-object Font Base Font Identifier triplet [MODCA-5-241]
*   FQN type X'7E' - Data-object Font Linked Font Identifier triplet [MODCA-5-242]

Note that since some of these parameters are simply optional repetitions of the same parameter on the BRS, they may not be used for processing by some applications and therefore may not result in an exception if specified inconsistently.

**X'08'** A subsequent matching End Object Container structured field is not present in the page, overlay, or resource group. [MODCA-5-243]


### Begin Object Environment Group (BOG)

The Begin Object Environment Group structured field begins an Object Environment Group, which establishes the environment parameters for the object. The scope of an object environment group is its containing object. [MODCA-5-244]

#### BOG (X'D3A8C7') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8C7' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-245]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-246] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-247] |
| 0–7 | CHAR | OEGName | | Name of the object environment group | O | X'02' [MODCA-5-248] |
| 8–n | Triplets | | | See BOG Semantics for triplet applicability | O | X'10' [MODCA-5-249] |

#### BOG Semantics

**OEGName** Is the name of the object environment group.

The object containing the Begin Object Environment Group structured field must also contain a subsequent matching End Object Environment Group structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-250]

| Triplet | Type | Usage [MODCA-5-251] |
| :--- | :--- | :--- [MODCA-5-252] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-253] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-254] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-255]

#### BOG Exception Condition Summary

**X'08'** A subsequent matching End Object Environment Group structured field is not present in the object.

### Begin Print File (BPF)

The Begin Print File structured field names and begins the print file. [MODCA-5-256]

#### BPF (X'D3A8A5') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8A5' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-257]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-258] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-259] |
| 0–7 | CHAR | PFName | | Name of the print file | O | X'02' [MODCA-5-260] |
| 8–n | Triplets | | | See BPF Semantics for triplet applicability | O | X'10' [MODCA-5-261] |

#### BPF Semantics

**PFName** Is the name of the print file described by the data stream.

**Triplets** Appear as follows: [MODCA-5-262]

| Triplet | Type | Usage [MODCA-5-263] |
| :--- | :--- | :--- [MODCA-5-264] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies the encoding for structured field parameters defined with a CHAR data type. If this triplet is not specified, the architected default encoding is EBCDIC single-byte presentation, which is characterized with encoding scheme ID X'61nn', and which is identified with CCSID 500 (corresponding to the combination of CPGID 500 and GCSGID 697). See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-266] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the Begin Print File structured field name and is used as the name of the print file. [MODCA-5-267] |
| X'18' | | **MO:DCA Interchange Set** For interchange data streams, this triplet is mandatory and must occur once. For private or exchange data streams, this triplet is not permitted. See “MO:DCA Interchange Set Triplet X'18'”. [MODCA-5-268] |
| X'5E' | | **Object Count** Optional. May occur once with SubObj = X'AF' to specify the number of pages in this print file. See “Object Count Triplet X'5E'”. [MODCA-5-272] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |
| X'72' | | **Universal Date and Time Stamp** Optional. May occur once. Assigns a universal date and time stamp to the print file. See “Universal Date and Time Stamp Triplet X'72'”. |
| X'8F' | | **MO:DCA Function Set** Mandatory if the MO:DCA Interchange Set (X'18') triplet is specified to indicate compliance with an interchange set and one or more function sets, in which case this triplet must occur at least once. If the MO:DCA Interchange Set triplet does not indicate compliance with an interchange set plus one or more function sets, or if that triplet is not specified, the MO:DCA Function Set triplet must not be specified. See “MO:DCA Function Set Triplet X'8F'”. |

The data stream containing the Begin Print File structured field must also contain a subsequent matching End Print File structured field, or a X'08' exception condition exists.

**Note:** If a triplet is included on this structured field, the optional PFName positional parameter becomes mandatory.

**Application Notes:**

1.  **Interchange Set triplet:** The X'18' triplet is used by AFP generators to indicate that the print file is intended to be compliant with the specified MO:DCA interchange set. Compliance and certification tools and utilities may use this indicator to check a print file for compliance with the specified interchange set. The triplet may also be used as a debug aid when diagnosing system interoperability problems. However, in general, AFP receivers such as print servers and transforms are not expected to verify whether the content of the print file matches the interchange set specification in the X'18' triplet, nor is there an exception defined for the case where the print file content does not match the interchange set specification in the X'18' triplet. [MODCA-5-269]
2.  **Object Count triplet:** The number of pages in a print file is defined by the number of page objects bounded by BPG/EPG or included by IPG in the documents in that print file. Pages that are specified in a document resource that is carried in a print-file-level resource group are not counted. A blank page that is generated by the print server for a sheet-side that does not contain page data is not counted as a page since there is no corresponding BPG/EPG or IPG in the document. Similarly, if a Medium Map generates multiple copies of a sheet-side that contains BPG/EPGs or IPGs, the corresponding pages are only counted once.

#### BPF Exception Condition Summary

**X'08'** A subsequent matching End Print File structured field is not present in the data stream. [MODCA-5-273]


### Begin Page (BPG)

The Begin Page structured field begins a presentation page. A presentation page contains an active environment group to establish parameters such as the size of the page's presentation space and the fonts to be used by the data objects. It may also contain any mixture of:

*   Bar code objects [MODCA-5-274]
*   Graphics objects [MODCA-5-275]
*   Image objects [MODCA-5-276]
*   Object containers [MODCA-5-277]
*   Presentation text objects [MODCA-5-278]

#### BPG (X'D3A8AF') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8AF' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-279]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-280] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-281] |
| 0–7 | CHAR | PageName | | Name of the page | O | X'02' [MODCA-5-282] |
| 8–n | Triplets | | | See BPG Semantics for triplet applicability | O | X'10' [MODCA-5-283] |

#### BPG Semantics

**PageName** Is the name of the page.

The document containing the Begin Page structured field must also contain a subsequent matching End Page structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-284]

| Triplet | Type | Usage [MODCA-5-285] |
| :--- | :--- | :--- [MODCA-5-286] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-287] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Page structured field name and is used as the name of the page. [MODCA-5-288] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'8D'—Begin Medium Map Reference. Specifies the name of the medium map object that is active for presenting the page on a physical medium. [MODCA-5-291] |
| X'56' | | **Medium Map Page Number** Optional. May occur once. Specifies the sequence number of the page in the set of sequential pages controlled by the active medium map. The first page in the set has sequence number 1. See “Medium Map Page Number Triplet X'56'”. |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |
| X'81' | | **Page Position Information** Optional. May occur once. Specifies the PGP repeating group that is used to view the page and its PMC overlay data. The PGP is specified in the medium map referenced by a FQN type X'8D'—Begin Medium Map Reference triplet. If the X'81' triplet is specified, it overrides a Medium Map Page Number (X'56') triplet. This triplet is not used for printing and is ignored by print servers. See “Page Position Information Triplet X'81'”. |
| X'83' | | **Presentation Control** Optional. May occur once. Specified on a BPG to indicate whether the page is intended to be viewed. If this triplet is not specified, the architected default is that the page is intended to be viewed. If this triplet is also specified on an Index Element (IEL) that indexes the page, the IEL triplet overrides if there is a conflict. This triplet is ignored for printing. See “Presentation Control Triplet X'83'”. |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory.

**Application Notes:**

1.  **Page archiving/viewing:** The FQN Begin Medium Map Reference (type X'8D') triplet, the Medium Map Page Number (X'56') triplet, the Page Position Information (X'81') triplet, and the Presentation Control (X'83') triplet may be used by viewing applications to present the page in standalone fashion as it would be presented within the context of the complete document. These triplets are ignored by print servers. [MODCA-5-294] [MODCA-5-295]
2.  **Triplet X'56' and X'81':** Note that the Medium Map Page Number (X'56') triplet is not needed if a Page Position Information (X'81') triplet is specified, and is overridden by the latter.
3.  **Page naming:** If a page is to be indexed or if it is to be included in a resource document, a page name is required so that the page can be identified and referenced. It is therefore highly recommended that the BPG structured field always specify a page name. [MODCA-5-292]
4.  **Naming uniqueness:** If page level indexing is used for the document that contains this page, or if this page is part of a resource document, it is recommended that the page name, whether it is specified by an 8-byte token name or by a fully qualified name, be unique with respect to other page names within the document. [MODCA-5-293]

#### BPG Exception Condition Summary

**X'08'** A subsequent matching End Page structured field is not present in the document. [MODCA-5-296]

### Begin Page Segment (BPS)

The Begin Page Segment structured field begins a page segment. A page segment is a resource object that can be referenced from a page or overlay and that contains any mixture of:

*   Bar code objects (BCOCA) [MODCA-5-297]
*   Graphics objects (GOCA) [MODCA-5-298]
*   Image objects (IOCA) [MODCA-5-299]

Objects in a page segment must specify an object area offset of zero so that they are positioned either at the origin of the including page or overlay coordinate system or at a reference point that is defined on the including page or overlay coordinate system by the Include Page Segment (IPS) structured field.

A page segment does not contain an active environment group. The environment for a page segment is defined by the active environment group of the including page or overlay.

**Architecture Note:** A migration form of the page segment resource object is supported in AFP environments and is defined in “AFP Page Segment”. [MODCA-5-300]

#### BPS (X'D3A85F') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A85F' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-301]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-302] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-303] |
| 0–7 | CHAR | PsegName | | Name of the page segment | M | X'06' [MODCA-5-304] |
| 8–n | Triplets | | | See BPS Semantics for triplet applicability | O | X'10' [MODCA-5-305] |

#### BPS Semantics

**PsegName** Is the name of the page segment. This name may not appear on more than one Begin Page Segment within the same resource group or a X'01' exception condition exists.

A page segment resource definition must contain a subsequent matching End Page Segment structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-306]

| Triplet | Type | Usage [MODCA-5-307] |
| :--- | :--- | :--- [MODCA-5-308] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-309] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-310] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-314] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. Overlays reside in external resource libraries or in resource groups. See “Resource Groups” for details on locating resource objects within libraries or resource groups. [MODCA-5-315] |

**Application Notes:**

1.  **Intermediate caching devices:** In environments that include an intermediate caching device such as Remote Print Manager (RPM) or Distributed Print Facility (DPF), time stamps on the BPS structured field must be specified using the X'62' triplet. [MODCA-5-311]
2.  **Resource management:** For purposes of print server resource management, the OEGs for all objects in a page segment must not contain MCF or MDR structured fields when the page segment is referenced with an IOB or IPS structured field.

**Architecture Note:** In AFP environments, the following retired triplets are used on this structured field:

*   Object Checksum (X'63') triplet; see “Object Checksum Triplet X'63'” [MODCA-5-316]
*   Object Origin Identifier (X'64') triplet; see “Object Origin Identifier Triplet X'64'” [MODCA-5-317]

#### BPS Exception Condition Summary

**X'01'** Multiple Begin Page Segment structured fields with the same name exist within the same resource group.

**X'08'** The page segment resource definition is not terminated by a subsequent matching End Page Segment structured field. [MODCA-5-318]


### Begin Presentation Text Object (BPT)

The Begin Presentation Text Object structured field begins a presentation text object which becomes the current data object.

#### BPT (X'D3A89B') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A89B' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-319]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-320] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-321] |
| 0–7 | CHAR | PTdoName | | Name of the presentation text data object | O | X'02' [MODCA-5-322] |
| 8–n | Triplets | | | See BPT Semantics for triplet applicability | O | X'10' [MODCA-5-323] |

#### BPT Semantics

**PTdoName** Is the name of the presentation text data object.

The page, or overlay containing a Begin Presentation Text Object structured field must also contain a subsequent matching End Presentation Text Object structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-324]

| Triplet | Type | Usage [MODCA-5-325] |
| :--- | :--- | :--- [MODCA-5-326] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-327] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Presentation Text Object structured field name and is used as the name of the presentation text data object. [MODCA-5-328] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-329] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-330] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-331] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory.

**Application Note:** When the BPT structured field is processed, all initial text conditions specified in the Presentation Text Descriptor (PTD) structured field are set prior to processing the text object. In addition, AFP servers set the following default page level initial text conditions before the PTD initial text conditions are set:

**Table 16. Default BPT Page-Level Initial Text Conditions**

| Parameter | Value |
| :--- | :--- |
| Initial (I,B) Presentation Position | (0,0) |
| Text Orientation | 0°,90° |
| Font Local ID | X'FF' (default font) |
| Baseline Increment | 6 lpi |
| Inline Margin | 0 |
| Intercharacter Adjustment | 0 |
| Text Color | X'FFFF' (default color) |

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:

*   Line Data Object Position Migration (X'27') triplet; see “Line Data Object Position Migration Triplet [MODCA-5-332] X'27'”.

#### BPT Exception Condition Summary

**X'08'** A subsequent matching End Presentation Text Object structured field is not present in the page, or overlay.

### Begin Resource Group (BRG)

The Begin Resource Group structured field begins a resource group, which becomes the current resource group at the same level in the document hierarchy. [MODCA-5-333]

#### BRG (X'D3A8C6') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8C6' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-334]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-335] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-336] |
| 0–7 | CHAR | RGrpName | | Name of the resource group | O | X'02' [MODCA-5-337] |
| 8–n | Triplets | | | See BRG Semantics for triplet applicability | O | X'10' [MODCA-5-338] |

#### BRG Semantics

**RGrpName** Is the name of the resource group.

The print file, document, page, or data object containing the Begin Resource Group structured field must also contain a subsequent matching End Resource Group structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-339]

| Triplet | Type | Usage [MODCA-5-340] |
| :--- | :--- | :--- [MODCA-5-341] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-342] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Begin Resource Group structured field name and is used as the name of the resource group. [MODCA-5-343] |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. The Fully Qualified Name type that may appear is X'83'—Begin Document Name. Specifies the name of a document that references resources contained in this resource group. See “Fully Qualified Name Triplet X'02'”. [MODCA-5-344] |
| X'62' | | **Local Date and Time Stamp** Optional. This triplet or the Universal Date and Time Stamp (X'72') triplet may occur once. Assigns a date and time stamp to the object. See “Local Date and Time Stamp Triplet X'62'”. [MODCA-5-345] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. [MODCA-5-348] |
| X'72' | | **Universal Date and Time Stamp** Optional. This triplet or the Local Date and Time Stamp (X'62') triplet may occur once. Assigns a universal date and time stamp to the object. See “Universal Date and Time Stamp Triplet X'72'”. [MODCA-5-349] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-350]

#### BRG Exception Condition Summary

**X'08'** A subsequent matching End Resource Group structured field is not present in the print file, document, page, or data object. [MODCA-5-351]


### Begin Resource (BRS)

The Begin Resource structured field begins an envelope that is used to carry resource objects in print file level (external) resource groups. Resource references in the data stream are matched against the resource identifier specified by the Begin Resource structured field.

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified. That is, the encoding scheme used for the resource include, the resource map, and the resource wrapper should be the same. For TrueType/OpenType fonts, optimal performance can be achieved by using UTF-16BE as the encoding scheme.

#### BRS (X'D3A8CE') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8CE' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-352]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-353] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-354] |
| 0–7 | CHAR | RSName | | Identifier of the resource | M | X'02' [MODCA-5-355] |
| 8–9 | | | 0 | Reserved; should be zero | M | X'06' [MODCA-5-356] |
| 10–n | Triplets | | | See BRS Semantics for triplet applicability | M | X'14' [MODCA-5-357] |

#### BRS Semantics

**RSName** Is the identifier used to select the resource. This identifier is matched against the resource reference in the data stream.

The resource group containing the Begin Resource structured field must also contain a subsequent matching End Resource structured field, or a X'08' exception condition exists.

**Triplets** Appear in the Begin Resource structured field as follows: [MODCA-5-358]

| Triplet | Type | Usage [MODCA-5-359] |
| :--- | :--- | :--- [MODCA-5-360] |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-361] |
| X'02' | | **Fully Qualified Name** At least one occurrence of this triplet is mandatory if the BRS envelopes a TrueType Collection (TTC) file; may occur more than once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'6E'—Data-object Font Base Font Identifier. This triplet may be specified on a BRS to indicate the following:<br>* If the BRS envelopes a TrueType Collection (TTC) file, the FQN [MODCA-5-362] type X'6E' triplet specifies a base TrueType/OpenType font that is contained in the collection. The identifier may be specified in the following format:<br>* If FQNFmt = X'00', the identifier is a character-encoded name. [MODCA-5-363] The character string that identifies the font must be the full font name specified in a name record in the mandatory Naming Table of the font file. This parameter is specified in a name record with Name ID 4. An example of a full font name is Times New Roman Bold. Each instance of the FQN type X'6E' triplet with FQNFmt = X'00' is used to specify the full font name of the base font in a language used in the font's Naming Table. The character encoding is UTF-16BE, which matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the Naming Table. The byte order is big endian. For example, if the font Naming Table contains two name records for the full font name (Name ID 4), one in English - United States (LCID = X'0409') and one in German - Standard (LCID = X'0407'), both in the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001'), each of these names, encoded in UTF-16BE, is carried in a FQN type X'6E' triplet on the BRS. [MODCA-5-364] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This identifier overrides the Begin Resource structured field name and is used as the identifier of the resource. The identifier may be specified in one—and only one—of the following formats:<br>• If FQNFmt = X'00', the identifier is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. If the Resource Object Type (X'21') triplet specifies ObjType=X'92' - Object Container, and if the Object Classification Triplet indicates that the object in the container is a TrueType/OpenType font (TTF), the FQN type X'01' triplet, specified using FQNFmt = X'00', may occur more than once. In that case, each instance of the FQN type X'01' triplet is used to specify the full font name in a language used in the font naming table. The character encoding is UTF-16BE, which matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the font's Naming Table. For example, if the font Naming Table contains two name records for the full font name (Name ID 4), one in English - United States (LCID = X'0409') and one in German - Standard (LCID = X'0407'), both in the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001'), each of these names, encoded in UTF-16BE, is carried in a FQN type X'01' triplet on the BRS. If the Resource Object Type (X'21') triplet specifies ObjType=X'92' - Object Container, and if the Object Classification Triplet indicates that the object in the container is a Color Management Resource (CMR), the FQN type X'01' triplet, specified using FQNFmt = X'00', is mandatory and is used to specify the CMR name. The character encoding is UTF-16BE. [MODCA-5-368] |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'41'—Color Management Resource (CMR) Reference. This triplet may be specified on a BRS to indicate the following:<br>• If the resource is a Color Conversion (CC) CMR, this triplet specifies the name of a Link LK CMR that is to be mapped to the CC CMR in the container.<br>• If the resource is a generic Halftone (HT) or Tone Transfer Curve (TTC) CMR, this triplet specifies the name of a device-specific CMR of the same type that is to replace the generic CMR.<br>The identifier may be specified in the following format.<br>• If FQNFmt = X'00', the identifier is a character-encoded name. The character string that identifies the CMR must be the CMR name specified in the CMR. The character encoding is UTF-16BE. [MODCA-5-369] |
| X'02' | | **Fully Qualified Name** Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'7E'—Data-object Font Linked Font Identifier. This triplet may be specified on a BRS to indicate the following:<br>• If the BRS envelopes a TrueType/OpenType font (TTF/OTF) file, the FQN type X'7E' triplet specifies a linked font for the base font. The order in which the FQN type X'7E' triplets are specified determines the order in which the linked fonts are processed.<br>• If the BRS envelopes a TrueType Collection (TTC) file, the FQN type X'7E' triplet specifies a linked font for the base font that is identified with the immediately preceding FQN type X'6E' triplet. Note that if the base font is specified in multiple languages using multiple FQN type X'6E' triplets, each instance of the FQN type [MODCA-5-372] X'6E' triplet must be followed by the sequence of FQN type X'7E' triplets that identify the linked fonts for the base font.<br>The identifier may be specified in the following format.<br>• If FQNFmt = X'00', the identifier is a character-encoded name. The character string that identifies the font must be the full font name specified in a name record in the mandatory Naming Table of the font file. This parameter is specified in a name record with Name ID 4. An example of a full font name is Times New Roman Bold. The character encoding is UTF-16BE, which matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the Naming Table. The byte order is big endian. [MODCA-5-373] |
| X'10' | | **Object Classification** Mandatory if the Resource Object Type triplet specifies ObjType = [MODCA-5-374] X'92', Object Container, in which case it must occur once. Characterizes and identifies the object data carried in the object container. See “Object Classification Triplet X'10'”. [MODCA-5-375] |
| X'21' | | **Resource Object Type** In AFP environments, one occurrence of this triplet is mandatory to identify the type of resource object delimited by the BRS. See “Resource Object Type Triplet X'21'” [MODCA-5-376] |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |

**Application Notes:**

1.  **Implementation Note:** Not all AFP servers support the inheritance of encoding scheme from higher levels of the document hierarchy, therefore it is recommended that this triplet be specified directly on the BRS if required by a parameter such as the FQN type X'01' triplet.
2.  **Triplet X'01':** It is strongly recommended that this triplet is specified even if the parameter on the BRS defines a fixed encoding. For example, if the parameter defines a fixed UTF-16BE encoding, the triplet can be specified using the CCSID form with CCSID=1200 (X'04B0').

**Using the BRS to Envelop Inline TrueType/OpenType Resources**

TrueType/OpenType fonts (TTFs/OTFs), TrueType/OpenType fonts that are used as linked fonts, and TrueType/OpenType font collections (TTCs), may be carried in the resource group for a print file. This is called a print file level resource group, and these resources are said to be inline. When presentation servers search for a font that is referenced in the data stream, such a resource group is searched ahead of system level resource libraries, and if an inline font is found it must be used in place of the system level font. To support this hierarchy, presentation servers process a TrueType/OpenType font reference in an MDR for inline resources as follows:

1.  The resource group—if present—is searched for a font (TTF/OTF) container or a collection (TTC) container that specifies a matching full font name.
    *   A font container specifies the full font name using a FQN type X'01' triplet on the Begin Resource (BRS) structured field for the font container.
    *   A collection container specifies the full font name of a font in the collection using a Data Object Font Base Font Identifier (X'6E') triplet on the BRS of the collection container. [MODCA-5-377]

The first matching font container or collection container is used. If a collection containing the font is found, the complete TTC—if not already in the presentation device—is downloaded to the device, which must be able to index the required font in the collection. The font container or collection container may also specify one or more linked fonts for the referenced font.

*   On a font container, linked fonts for the base font are specified with Data-object Font Linked Font [MODCA-5-378] Identifier (FQN type X'7E') triplets, which carry the full font name of the linked fonts, on the BRS of the font container.
*   On a collection container, linked fonts are specified with Data-object Font Linked Font Identifier (FQN [MODCA-5-379] type X'7E') triplets that immediately follow the Data Object Font Base Font Identifier (X'6E') triplet for the base font on the BRS of the collection container. Note that if the base font is specified in multiple languages using multiple FQN type X'6E' triplets, each instance of the FQN type X'6E' triplet must be followed by the sequence of FQN type X'7E' triplets that identify the linked fonts for the base font.

The full font names for the linked fonts are used in turn to search the resource group for a font container or a collection container that carries a font that matches the full font name of the linked font. On a font container, the linked font name is matched against the FQN type X'01' triplet on the BRS; on a collection container it is matched against the FQN type X'6E' triplets on the BRS.

*   The first matching font container or collection container is used, and its font is processed as a linked font [MODCA-5-380] for the base font. Multiple linked fonts may be specified, and the order in which they are specified on the BRS of the font container or collection container determines the order in which they are processed. The base font is always processed first, followed by the first-specified linked font, followed by the next-specified linked font, and so on. The last linked font is processed last.
*   If a linked font cannot be found in either an inline font container or an inline collection container, the full [MODCA-5-381] font name of the linked font is used to index the RAT to locate the linked font in a resource library. If a specified linked font cannot be found in the resource group or in a resource library, a X'04' exception condition exists.

Only one level of linking is supported. That is, if a linked font specifies its own linked fonts, either with FQN type X'7E' triplets on its inline container or with linked font pointers in the RAT, these “secondary” linked fonts are not processed as linked fonts for the original base font.

2.  If a font matching the MDR reference is not found in an inline font container or in an inline collection [MODCA-5-382] container, the presentation server accesses the RAT with the full font name to locate the referenced font in a resource library. In this case, all linked fonts are specified in the RAT repeating group for the referenced font, and the order in which they are specified determines the order in which they are processed. Both inline linked fonts and library-based linked fonts are used, and the print file level resource group is always searched for linked fonts ahead of the resource library. The resource group search includes font containers, in which case the linked font name is matched against the FQN type X'01' triplet on the BRS of the font container, and collection containers, in which case the linked font name is matched against the FQN type X'6E' triplets on the BRS of the collection container.

**Using the BRS to Envelop Inline Color Management Resources**

CMRs may also be carried in the resource group for a print file, in which case they are called inline CMRs. The CMR must first be wrapped in a BOC/EOC object container, which in turn must be wrapped in a BRS/ERS resource envelope. The BRS specifies the CMR name, encoded in UTF-16BE, with a FQN type X'01' triplet. If the CMR in the container is a Color Conversion (CC) CMR, the BRS may also specify the names of Link LK CMRs, also encoded in UTF-16BE, that are mapped to the CMR using FQN type X'41' - Color Management Resource (CMR) Reference triplets. If the CMR in the container is a generic HT or TTC CMR, the BRS may also specify device-specific CMR replacements for the generic CMR using the FQN type X'41' triplets. When resolving a CMR reference in the data stream, the print server must always search the print file resource group—if one exists—first. The CMRname is matched against the CMRname that is specified on the BRS of the resource container. If no match is found, the search continues with the CMR RAT.

If a match is found, the inline CMR is processed as follows. [MODCA-5-383]

**Table 17. Print Server CMR Processing: Inline CMRs**

| CMR type | Processing mode | Device-specific or generic | Processing |
| :--- | :--- | :--- | :--- |
| Color conversion | Audit or instruction | Device-specific | The inline CMR is downloaded, if necessary, and activated. If the target device supports downloaded link CMRs, all Link LK CMRs that are mapped to the referenced CMR with a FQN type X'41' triplet on the BRS and that match the target device type and model are downloaded, if necessary, and activated. All other mapped CMRs are ignored. |
| Halftone | Audit | Device-specific | If the target device supports downloaded HT CMRs, the inline CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Audit | Generic | The inline CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Instruction | Device-specific | If the target device supports downloaded HT CMRs, the inline CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Halftone | Instruction | Generic | If the target device supports downloaded HT CMRs, and if the BRS references device-specific HT CMRs that match the device type and model of the target device, these CMRs are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the search continues with the CMR RAT. If a matching generic CMR RAT entry is found, and if the target device supports downloaded HT CMRs, all mapped device-specific CMRs that match the device type and model of the target device are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the inline generic CMR is downloaded, if necessary, activated, and replaced by the output device with a device-specific HT CMR. |
| Tone transfer curve | Audit | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Audit | Generic | The referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Tone transfer curve | Instruction | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Instruction | Generic | If the target device supports downloaded TTC CMRs, and if the BRS references device-specific TTC CMRs that match the device type and model of the target device, these CMRs are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the search continues with the CMR RAT. If a matching generic CMR RAT entry is found, and if the target device supports downloaded TTC CMRs, all mapped device-specific CMRs that match the device type and model of the target device are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the inline generic CMR is downloaded, if necessary, activated, and replaced by the output device with a device-specific TTC CMR. |
| Indexed | Audit | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Indexed | Instruction | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Link DL | Link | Device-specific | If the target device supports downloaded Link DL CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |

**Implementation Note:** It is not necessary for resource-collection applications to collect Link LK CMRs and place them in the inline resource group it builds. Such applications should provide different CMR resource collection options:

*   **Option 1:** the user wants all CMRs collected and specifies a device type and model. In this case all [MODCA-5-384] CMRs referenced in the datastream or via the Data Object RAT and CMR RAT (except Link LK CMRs) are collected. The character string specified for the device type and model is used to obtain any device specific CMRs substituted for generic instruction HT and TTC CMRs.
*   **Option 2:** the user wants all CMRs collected (except Link LK CMRs) but no device type and model [MODCA-5-385] information has been specified. In this case, CMRs for all device type and models mapped to a given generic instruction CMR should be collected. If there are duplicates, the first one found in the search order should be the one collected.
*   **Option 3:** the user just wants to collect any CMRs that are referenced explicitly in the datastream plus [MODCA-5-386] any non-device specific CMRs that are referenced by the Data Object RAT or CMR RAT. This will keep the output generated by the application from being device-specific unless the datastream explicitly referenced a device-specific CMR. The only CMRs referenced by the Data Object RAT or CMR RAT that should be collected are audit CC, HT, and TTC CMRs, generic instruction HT and TTC CMRs, and HT and TTC CMRs that are non-generic but have all '@' characters in the device type and model fields of the CMR name.

#### BRS Exception Condition Summary

**X'08'** The Begin Resource structured field is not followed by a subsequent End Resource structured field in the same resource group. [MODCA-5-387]

### Begin Resource Environment Group (BSG)

The Begin Resource Environment Group structured field begins a Resource Environment Group (REG), which defines a subset of the resources required for a document or for a group of pages in a document. The scope of the Resource Environment Group is the group of pages that follow, up to the next REG, which is a complete replacement for the current REG, or the end of the document, whichever occurs first.

**Note:** Resources that are mapped in a REG must still be mapped in the AEG for the page that uses the resources.

#### BSG (X'D3A8D9') Syntax

**Structured Field Introducer:** SF Length (2B) ID = X'D3A8D9' Flags (1B) Reserved; X'0000'

**Structured Field Data** [MODCA-5-388]

| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-5-389] |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-390] |
| 0–7 | CHAR | REGName | | Name of the resource environment group | O | X'02' [MODCA-5-391] |
| 8–n | Triplets | | | See BSG Semantics for triplet applicability | O | X'10' [MODCA-5-392] |

#### BSG Semantics

**REGName** Is the name of the resource environment group.

The document containing the Begin Resource Environment Group structured field must also contain a subsequent matching End Resource Environment Group structured field, or a X'08' exception condition exists.

**Triplets** Appear as follows:

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. |
| X'65' | | **Comment** Optional. May occur more than once. Carries unarchitected data. See “Comment Triplet X'65'”. |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-393]

#### BSG Exception Condition Summary

**X'08'** A subsequent matching End Resource Environment Group structured field is not present in the document.


### Container Data Descriptor (CDD)

The Container Data Descriptor structured field specifies control information for a presentation data object that
is carried in an object container. [MODCA-5-394]

#### CDD (X'D3A692') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A692' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-395]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–11 | | **Retired parameters** | | See “Retired Parameters” | M | X'06' [MODCA-5-398] |
| 12–n | Triplets | | | See CDD Semantics for triplet applicability. | O | X'10' [MODCA-5-399] |

#### CDD Semantics

**Triplets** Specify control information for object data. To be defined as required by the object data.
Triplets appear in the Container Data Descriptor structured field as follows: [MODCA-5-400]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'4E' | | **Color Specification** Optional. May occur once. Specifies the color that is to be used as the default color, or the initial color, for the object. Note that this color may in turn be overridden by a color that is specified inside the object. This triplet only specifies the color specified for the object presentation space; it does not affect colors assigned to the object's object area. This triplet only applies to image file formats, as defined in Appendix D, “MO:DCA Registry”, that specify a bilevel or grayscale image; it is ignored when the object is not a bilevel or grayscale image. Note that all 1-bit per pixel image objects are considered bilevel. When the image is grayscale, this triplet specifies the color that is to be grayscaled. If ColSpce = X'06' - Highlight color space, the % coverage and % shading parameters are ignored. See “Color Specification Triplet X'4E'”. [MODCA-5-403] [MODCA-5-404] |
| X'5A' | | **Object Offset** Optional. If this container is specified directly within a page or overlay and carries a file that contains multiple pages or paginated objects, may occur once with ObjTpe=X'AF' to specify that pages or paginated objects are the objects to be counted. The triplet is ignored in all other cases. Selects a single paginated object to be presented by specifying how many paginated objects in the file precede that object. The offset is measured from the beginning of the file, so that the first paginated object has offset 0, the second has offset 1, and the nth has offset (n-1). Only the selected object is presented. If this triplet is not specified on a container that is specified directly within a page or overlay and that contains a file with multiple paginated objects, the default is to present the first paginated object in the file. For more information on selecting paginated objects, see “Object Offset Triplet X'5A'”. [MODCA-5-405] |
| X'9A' | | **Image Resolution** Optional. May occur once. Specifies the resolution of the image for containers that carry a raster image object; ignored for all other object types. See “Image Resolution Triplet X'9A'”. This triplet overrides any resolution specified inside the image. If the resolution is not specified outside the image or inside the image, the default is to assume that the image resolution is the same as the output device resolution. This is not intended for containers that are not image formats but might have embedded images inside of them (such as PDF). [MODCA-5-406] |
| X'9C' | | **Object Container Presentation Space Size** Optional. May occur once for the following object types: <br> • PDF - all presentation object types <br> • AFPC SVG Subset <br> Specifies the presentation space size of the object container. For PDF object types, specifies how this size is determined. For SVG, specifies the actual size, and overrides any presentation space size specified within the SVG object. See “Object Container Presentation Space Size Triplet X'9C'”. [MODCA-5-407] [MODCA-5-408] |

The presentation space size is specified by various objects. If the presentation space size is not specified, the
architected default is the presentation space size of the including page or overlay.

This structured field is not applicable to non-presentation objects and may be ignored if it appears in the object
container for such objects. [MODCA-5-409]


### End Active Environment Group (EAG)

The End Active Environment Group structured field terminates the definition of an Active Environment Group
initiated by a Begin Active Environment Group structured field. [MODCA-5-410]

#### EAG (X'D3A9C9') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9C9' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-411]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **AEGName** | | Name of the active environment group | O | X'02' [MODCA-5-414] |

#### EAG Semantics

**AEGName** Is the name of the active environment group being terminated. If a name is specified, it must
match the name in the most recent Begin Active Environment Group structured field in the
page or a X'01' exception condition exists. If the first two bytes in AEGName contain the value
X'FFFF', the name matches any name specified on the Begin Active Environment Group
structured field that initiated the current definition.

A matching Begin Active Environment Group structured field must appear within the page at
some location preceding the End Active Environment Group structured field, or a X'20'
exception condition exists. [MODCA-5-415]

#### EAG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Active
Environment Group structured field.

**X'20'** Not preceded by a matching Begin Active Environment Group structured field. [MODCA-5-416]


### End Bar Code Object (EBC)

The End Bar Code Object structured field terminates the current bar code object initiated by a Begin Bar Code
Object structured field. [MODCA-5-417]

#### EBC (X'D3A9EB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9EB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-418]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **BCdoName** | | Name of the bar code data object | O | X'02' [MODCA-5-421] |
| 8–n | Triplets | | | See EBC Semantics for triplet applicability. | O | X'10' [MODCA-5-422] |

#### EBC Semantics

**BCdoName** Is the name of the bar code data object being terminated. If a name is specified, it must match
the name in the most recent Begin Bar Code Object structured field in the page, overlay, or
resource group, or a X'01' exception condition exists. If the first two bytes of BCdoName
contain the value X'FFFF', the name matches any name specified on the Begin Bar Code
Object structured field that initiated the current definition.

A matching Begin Bar Code Object structured field must appear within the containing structure
at some location preceding the End Bar Code Object structured field, or a X'20' exception
condition exists.

**Triplets** Appear as follows: [MODCA-5-423]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Bar Code Object structured field name and is used as the name of the bar code data object being terminated. [MODCA-5-426] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-427]

#### EBC Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Bar Code Object
structured field.

**X'20'** The End Bar Code Object structured field is not preceded by a matching Begin Bar Code
Object structured field. [MODCA-5-428]


### End Document Environment Group (EDG)

The End Document Environment Group structured field terminates the definition of a document environment
group initiated by a Begin Document Environment Group structured field. [MODCA-5-429]

#### EDG (X'D3A9C4') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9C4' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-430]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **DEGName** | | Name of the document environment group | O | X'02' [MODCA-5-433] |

#### EDG Semantics

**DEGName** Is the name of the document environment group being terminated. If a name is specified, it
must match the name in the most recent Begin Document Environment Group structured field
in the form map or a X'01' exception condition exists. If the first two bytes in DEGName
contain the value X'FFFF', the name matches any name specified on the Begin Document
Environment Group structured field that initiated the current definition.

A matching Begin Document Environment Group structured field must appear at some
location within the form map preceding the End Document Environment Group structured
field, or a X'20' exception condition exists. [MODCA-5-434]

#### EDG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Document
Environment Group structured field.

**X'20'** The End Document Environment Group structured field is not preceded by a matching Begin
Document Environment Group structured field. [MODCA-5-435]


### End Document Index (EDI)

The End Document Index structured field terminates the document index initiated by a Begin Document Index
structured field.

#### EDI (X'D3A9A7') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9A7' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-436]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **IndxName** | | Name of the document index | O | X'02' [MODCA-5-439] |
| 8–n | Triplets | | | See EDI Semantics for triplet applicability. | O | X'10' [MODCA-5-440] |

#### EDI Semantics

**IndxName** Is the name of the document index being terminated. If a name is specified, it must match the
name in the most recent Begin Document Index structured field in the print file or document, or
a X'01' exception condition exists. If the first two bytes of IndxName contain the value X'FFFF',
the name matches any name specified on the Begin Document Index structured field that
initiated the current definition.

A matching Begin Document Index structured field must appear within the print file or
document at some location preceding the End Document Index structured field, or a X'20'
exception condition exists.

**Triplets** Appear as follows: [MODCA-5-441]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Document Index structured field name and is used as the name of the document index being terminated. [MODCA-5-444] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-445]

#### EDI Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Document Index
structured field.

**X'20'** The End Document Index structured field is not preceded by a matching Begin Document
Index structured field. [MODCA-5-446]


### End Document (EDT)

The End Document structured field terminates the MO:DCA document data stream initiated by a Begin
Document structured field. [MODCA-5-447]

#### EDT (X'D3A9A8') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9A8' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-448]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **DocName** | | Name of the document | O | X'02' [MODCA-5-451] |
| 8–n | Triplets | | | See EDT Semantics for triplet applicability. | O | X'10' [MODCA-5-452] |

#### EDT Semantics

**DocName** Is the name of the document being terminated. If a name is specified, it must match the name
in the most recent Begin Document structured field in the data stream or a X'01' exception
condition exists. If the first two bytes of DocName contain the value X'FFFF', the name
matches any name specified on the Begin Document structured field that initiated the current
definition.

A matching Begin Document structured field must appear within the data stream at some
location preceding the End Document structured field, or a X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-453]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The only Fully Qualified Name type that may appear is X'01'— Replace First GID Name. This GID overrides the End Document structured field name and is used as the name of the document being terminated. [MODCA-5-456] |

**Note:** If a triplet is included on this structured field, the optional DocName positional parameter becomes
mandatory.

#### EDT Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Document
structured field.

**X'20'** The End Document structured field is not preceded by a matching Begin Document structured
field.


### End Form Map (EFM)

The End Form Map structured field terminates the form map object initiated by a Begin Form Map structured
field.

#### EFM (X'D3A9CD') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9CD' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-457]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **FMName** | | Name of the form map | O | X'02' [MODCA-5-460] |

#### EFM Semantics

**FMName** Is the name of the form map being terminated. If a name is specified, it must match the name
in the most recent Begin Form Map structured field or a X'01' exception condition exists. If the
first two bytes of FMName contain the value X'FFFF', the name matches any name specified
on the Begin Form Map structured field that initiated the current definition.

A matching Begin Form Map structured field must appear at some location preceding the End
Form Map structured field, or a X'20' exception condition exists. [MODCA-5-461]

#### EFM Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Form Map
structured field.

**X'20'** The End Form Map structured field is not preceded by a matching Begin Form Map structured
field.


### End Graphics Object (EGR)

The End Graphics Object structured field terminates the current graphics object initiated by a Begin Graphics
Object structured field. [MODCA-5-462]

#### EGR (X'D3A9BB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9BB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-463]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **GdoName** | | Name of the graphics data object | O | X'02' [MODCA-5-466] |
| 8–n | Triplets | | | See EGR Semantics for triplet applicability. | O | X'10' [MODCA-5-467] |

#### EGR Semantics

**GdoName** Is the name of the graphics data object being terminated. If a name is specified, it must match
the name in the most recent Begin Graphics Object structured field in the containing page,
overlay, or resource group, or a X'01' exception condition exists. If the first two bytes of
GdoName contain the value X'FFFF', the name matches any name specified on the Begin
Graphics Object structured field that initiated the current definition.

A matching Begin Graphics Object structured field must appear within the containing structure
at some location preceding the End Graphics Object structured field, or a X'20' exception
condition exists.

**Triplets** Appear as follows: [MODCA-5-468]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Graphics Object structured field name and is used as the name of the graphics data object being terminated. [MODCA-5-471] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-472]

#### EGR Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Graphics Object
structured field.

**X'20'** The End Graphics Object structured field is not preceded by a matching Begin Graphics
Object structured field. [MODCA-5-473]


### End Image Object (EIM)

The End Image Object structured field terminates the current image object initiated by a Begin Image Object
structured field.

#### EIM (X'D3A9FB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9FB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-474]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **IdoName** | | Name of the image data object | O | X'02' [MODCA-5-477] |
| 8–n | Triplets | | | See EIM Semantics for triplet applicability. | O | X'10' [MODCA-5-478] |

#### EIM Semantics

**IdoName** Is the name of the image data object being terminated. If a name is specified, it must match
the name in the most recent Begin Image Object structured field in the containing page,
overlay, or resource group, or a X'01' exception condition exists. If the first two bytes of
IdoName contain the value X'FFFF', the name matches any name specified on the Begin
Image Object structured field that initiated the current definition.

A matching Begin Image Object structured field must appear within the containing structure at
some location preceding the End Image Object structured field, or a X'20' exception condition
exists.

**Triplets** Appear as follows: [MODCA-5-479]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Image Object structured field name and is used as the name of the image data object being terminated. [MODCA-5-482] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-483]

#### EIM Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Image Object
structured field.

**X'20'** The End Image Object structured field is not preceded by a matching Begin Image Object
structured field.


### End Medium Map (EMM)

The End Medium Map structured field terminates the medium map object initiated by a Begin Medium Map
structured field.

#### EMM (X'D3A9CC') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9CC' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-484]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **MMName** | | Name of the medium map | O | X'02' [MODCA-5-487] |

#### EMM Semantics

**MMName** Is the name of the medium map being terminated. If a name is specified, it must match the
name in the most recent Begin Medium Map structured field or a X'01' exception condition
exists. If the first two bytes of MMName contain the value X'FFFF', the name matches any
name specified on the Begin Medium Map structured field that initiated the current definition.

A matching Begin Medium Map structured field must appear at some location preceding the
End Medium Map structured field, or a X'20' exception condition exists. [MODCA-5-488]

#### EMM Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Medium Map
structured field.

**X'20'** The End Medium Map structured field is not preceded by a matching Begin Medium Map
structured field.


### End Overlay (EMO)

The End Overlay structured field terminates the overlay resource object initiated by a Begin Overlay structured
field.

#### EMO (X'D3A9DF') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9DF' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-489]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **OvlyName** | | Name of the overlay | O | X'02' [MODCA-5-492] |
| 8–n | Triplets | | | See EMO Semantics for triplet applicability. | O | X'10' [MODCA-5-493] |

#### EMO Semantics

**OvlyName** Is the name of the overlay that is being terminated. If a name is specified, it must match the
name in the most recent Begin Overlay structured field in the resource group or a X'01'
exception condition exists. If the first two bytes of OvlyName contain the value X'FFFF', the
name matches any name specified on the Begin Overlay structured field that initiated the
current definition.

A matching Begin Overlay structured field must appear within the resource group at some
location preceding the End Overlay structured field, or a X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-494]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Overlay structured field name and is used as the name of the overlay being terminated. [MODCA-5-497] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-498]

#### EMO Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Overlay
structured field.

**X'20'** The End Overlay structured field is not preceded by a matching Begin Overlay structured field. [MODCA-5-499]


### End Named Page Group (ENG)

The End Named Page Group structured field terminates a page group that was initiated by a Begin Named
Page Group structured field. [MODCA-5-500]

#### ENG (X'D3A9AD') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9AD' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-501]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PGrpName** | | Name of the page group | O | X'02' [MODCA-5-504] |
| 8–n | Triplets | | | See ENG Semantics for triplet applicability. | O | X'10' [MODCA-5-505] |

#### ENG Semantics

**PGrpName** Is the name of the page group that is being terminated. If a name is specified, it must match
the name in the most recent Begin Named Page Group structured field in the document or a
X'01' exception condition exists. If the first two bytes of PGrpName contain the value X'FFFF',
the name matches any name specified on the Begin Named Page Group structured field that
initiated the current definition.

A matching Begin Named Page Group structured field must appear within the document at
some location preceding the End Named Page Group structured field, or a X'20' exception
condition exists.

If the Keep Group Together (X'9D') triplet is specified on the Begin Named Page Group
structured field that corresponds to this End Named Page Group structured field, the page
group name in the ENG must exactly match the page group name in the BNG, or a X'01'
exception condition exists. That is, in this case, the value X'FFFF' cannot be specified for the
page group name in the ENG structured field.

**Triplets** Appear in the End Named Page Group structured field as follows: [MODCA-5-506]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the End Named Page Group structured field name and is used as the name of the page group being terminated. [MODCA-5-509] |

**Note:** If a triplet is included on this structured field, the optional PGrpName positional parameter becomes
mandatory.

#### ENG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Named Page
Group structured field.

**X'20'** The End Named Page Group structured field is not preceded by a matching Begin Named
Page Group structured field. [MODCA-5-510]


### End Object Container (EOC)

The End Object Container structured field terminates an object container initiated by a Begin Object Container
structured field.

#### EOC (X'D3A992') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A992' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-511]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **ObjCName** | | Name of the object container | O | X'02' [MODCA-5-514] |
| 8–n | Triplets | | | See EOC Semantics for triplet applicability. | O | X'10' [MODCA-5-515] |

#### EOC Semantics

**ObjCName** Is the name of the object container that is being terminated. If a name is specified, it must
match the name in the most recent Begin Object Container structured field or a X'01'
exception condition exists. If the first two bytes of ObjCName contain the value X'FFFF', the
name matches any name specified on the Begin Object Container structured field that initiated
the current definition.

A matching Begin Object Container structured field must appear at some location preceding
the End Object Container structured field, or a X'20' exception condition exists.

**Triplets** Appear in the End Object Container structured field as follows: [MODCA-5-516]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID name. This GID overrides the End Object Container structured field name and is used as the name of the object container being terminated. [MODCA-5-519] |

**Note:** If a triplet is included on this structured field, the optional ObjCName positional parameter becomes
mandatory.

#### EOC Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Object Container
structured field.

**X'20'** The End Object Container structured field is not preceded by a matching Begin Object
Container structured field. [MODCA-5-520]


### End Object Environment Group (EOG)

The End Object Environment Group structured field terminates the definition of an Object Environment Group
initiated by a Begin Object Environment Group structured field. [MODCA-5-521]

#### EOG (X'D3A9C7') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9C7' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-522]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **OEGName** | | Name of the object environment group | O | X'02' [MODCA-5-525] |

#### EOG Semantics

**OEGName** Is the name of the object environment group that is being terminated. If a name is specified, it
must match the name in the most recent Begin Object Environment Group structured field in
the object or a X'01' exception condition exists. If the first two bytes of OEGName contain the
value X'FFFF', the name matches any name specified on the Begin Object Environment
Group structured field that initiated the current definition.

A matching Begin Object Environment Group structured field must appear within the object at
some location preceding the End Object Environment Group structured field, or a X'20'
exception condition exists. [MODCA-5-526]

#### EOG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Object
Environment Group structured field.

**X'20'** The End Object Environment Group structured field is not preceded by a matching Begin
Object Environment Group structured field. [MODCA-5-527]


### End Print File (EPF)

The End Print File structured field terminates the data stream initiated by a Begin Print File structured field. [MODCA-5-528]

#### EPF (X'D3A9A5') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9A5' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-529]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PFName** | | Name of the print file | O | X'02' [MODCA-5-532] |
| 8–n | Triplets | | | See EPF Semantics for triplet applicability. | O | X'10' [MODCA-5-533] |

#### EPF Semantics

**PFName** Is the name of the print file being terminated. If a name is specified, it must match the name in
the most recent Begin Print File structured field in the data stream or a X'01' exception
condition exists. If the first two bytes of PFName contain the value X'FFFF', the name matches
any name specified on the Begin Print File structured field that initiated the current definition.

A matching Begin Print File structured field must appear within the data stream at some
location preceding the End Print File structured field, or a X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-534]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The only Fully Qualified Name type that may appear is X'01'— Replace First GID name. This GID overrides the End Print File structured field name and is used as the name of the print file being terminated. [MODCA-5-537] |

**Note:** If a triplet is included on this structured field, the optional PFName positional parameter becomes
mandatory.

#### EPF Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Print File
structured field.

**X'20'** The End Print File structured field is not preceded by a matching Begin Print File structured
field.


### End Page (EPG)

The End Page structured field terminates the current presentation page definition initiated by a Begin Page
structured field.

#### EPG (X'D3A9AF') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9AF' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-538]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PageName** | | Name of the page | O | X'02' [MODCA-5-541] |
| 8–n | Triplets | | | See EPG Semantics for triplet applicability. | O | X'10' [MODCA-5-542] |

#### EPG Semantics

**PageName** Is the name of the page that is being terminated. If a name is specified, it must match the
name in the most recent Begin Page structured field in the document or a X'01' exception
condition exists. If the first two bytes of PageName contain the value X'FFFF', the name
matches any name specified on the Begin Page structured field that initiated the current
definition.

A matching Begin Page structured field must appear within the document at some location
preceding the End Page structured field, or a X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-543]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Page structured field name and is used as the name of the page being terminated. [MODCA-5-546] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-547]

#### EPG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Page structured
field.

**X'20'** The End Page structured field is not preceded by a matching Begin Page structured field. [MODCA-5-548]


### End Page Segment (EPS)

The End Page Segment structured field terminates the page segment resource object initiated by a Begin
Page Segment structured field. [MODCA-5-549]

#### EPS (X'D3A95F') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A95F' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-550]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PsegName** | | Name of the page segment | O | X'02' [MODCA-5-553] |

#### EPS Semantics

**PsegName** Is the name of the page segment that is being terminated. If a name is specified, it must match
the name in the most recent Begin Page Segment structured field or a X'01' exception
condition exists. If the first two bytes of PsegName contain the value X'FFFF', the name
matches any name specified on the Begin Page Segment structured field that initiated the
current definition.

A matching Begin Page Segment structured field must appear at some location preceding the
End Page Segment structured field, or a X'20' exception condition exists. [MODCA-5-554]

#### EPS Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Page Segment
structured field.

**X'20'** The End Page Segment structured field is not preceded by a matching Begin Page Segment
structured field.


### End Presentation Text Object (EPT)

The End Presentation Text Object structured field terminates the current presentation text object initiated by a
Begin Presentation Text Object structured field. [MODCA-5-555]

#### EPT (X'D3A99B') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A99B' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-556]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PTdoName** | | Name of the presentation text data object | O | X'02' [MODCA-5-559] |
| 8–n | Triplets | | | See EPT Semantics for triplet applicability. | O | X'10' [MODCA-5-560] |

#### EPT Semantics

**PTdoName** Is the name of the presentation text data object that is being terminated. If a name is specified,
it must match the name in the most recent Begin Presentation Text Object structured field in
the page, or overlay, or a X'01' exception condition exists. If the first two bytes of PTdoName
contain the value X'FFFF', the name matches any name specified on the Begin Presentation
Text Object structured field that initiated the current definition.

A matching Begin Presentation Text Object structured field must appear within the containing
structure at some location preceding the End Presentation Text Object structured field, or a
X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-561]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Presentation Text Object structured field name and is used as the name of the presentation text data object being terminated. [MODCA-5-564] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-565]

#### EPT Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Presentation Text
Object structured field.

**X'20'** The End Presentation Text Object structured field is not preceded by a matching Begin
Presentation Text Object structured field. [MODCA-5-566]


### End Resource Group (ERG)

The End Resource Group structured field terminates the definition of a resource group initiated by a Begin
Resource Group structured field. [MODCA-5-567]

#### ERG (X'D3A9C6') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9C6' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-568]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **RGrpName** | | Name of the resource group | O | X'02' [MODCA-5-571] |
| 8–n | Triplets | | | See ERG Semantics for triplet applicability. | O | X'10' [MODCA-5-572] |

#### ERG Semantics

**RGrpName** Is the name of the resource group that is being terminated. If a name is specified, it must
match the name in the most recent Begin Resource Group structured field in the print file,
document, page, or data object, or a X'01' exception condition exists. If the first two bytes of
RGrpName contain the value X'FFFF', the name matches any name specified on the Begin
Resource Group structured field that initiated the current definition.

A matching Begin Resource Group structured field must appear within the print file, document,
page, or data object at some location preceding the End Resource Group structured field, or a
X'20' exception condition exists.

**Triplets** Appear as follows: [MODCA-5-573]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the End Resource Group structured field name and is used as the name of the resource group being terminated. [MODCA-5-576] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory. [MODCA-5-577]

#### ERG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Resource Group
structured field.

**X'20'** The End Resource Group structured field is not preceded by a matching Begin Resource
Group structured field. [MODCA-5-578]


### End Resource (ERS)

The End Resource structured field terminates an envelope that is used to carry resource objects in external
(print file level) resource groups. The envelope is initiated by a Begin Resource (BRS) structured field. [MODCA-5-579]

#### ERS (X'D3A9CE') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9CE' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-580]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **RSName** | | Name of the resource | O | X'02' [MODCA-5-583] |

#### ERS Semantics

**RSName** Is the name of the resource being terminated. If a name is specified, it must match the name in
the most recent Begin Resource structured field. If the first two bytes in RSName contain the
value X'FFFF', the name matches any name specified on the Begin Resource structured field
that initiated the current definition.

A matching Begin Resource structured field must appear within the resource group at some
location preceding the End Resource structured field, or a X'20' exception condition exists. [MODCA-5-584]

#### ERS Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Resource
structured field.

**X'20'** The End Resource structured field is not preceded by a matching Begin Resource structured
field.


### End Resource Environment Group (ESG)

The End Resource Environment Group structured field terminates the definition of a Resource Environment
Group initiated by a Begin Resource Environment Group structured field. [MODCA-5-585]

#### ESG (X'D3A9D9') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A9D9' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-586]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **REGName** | | Name of the resource environment group | O | X'02' [MODCA-5-589] |

#### ESG Semantics

**REGName** Is the name of the resource environment group being terminated. If a name is specified, it
must match the name in the most recent Begin Resource Environment Group structured field
in the document or a X'01' exception condition exists. If the first two bytes in REGName
contain the value X'FFFF', the name matches any name specified on the Begin Resource
Environment Group structured field that initiated the current definition.

A matching Begin Resource Environment Group structured field must appear within the
document at some location preceding the End Resource Environment Group structured field,
or a X'20' exception condition exists. [MODCA-5-590]

#### ESG Exception Condition Summary

**X'01'** A name is specified that does not match the name on the most recent Begin Resource
Environment Group structured field.

**X'20'** The End Resource Environment Group structured field is not preceded by a matching Begin
Resource Environment Group structured field. [MODCA-5-591]


### Graphics Data (GAD)

The Graphics Data structured field contains the data for a graphics object. [MODCA-5-592]

#### GAD (X'D3EEBB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3EEBB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-593]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **GOCAdat** | | Up to 32,759 bytes of GOCA-defined data | O | X'00' [MODCA-5-596] |

#### GAD Semantics

**GOCAdat** Contains the GOCA-defined data. See the MO:DCA environment appendix in the *Graphics
Object Content Architecture for AFP Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-597]


### Graphics Data Descriptor (GDD)

The Graphics Data Descriptor structured field contains the descriptor data for a graphics object. [MODCA-5-598]

#### GDD (X'D3A6BB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A6BB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-599]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **GOCAdes** | | Up to 32,759 bytes of GOCA-defined descriptor data | O | X'00' [MODCA-5-602] |

#### GDD Semantics

**GOCAdes** Contains the GOCA-defined descriptor data. See the MO:DCA environment appendix in the
*Graphics Object Content Architecture for AFP Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-603]


### Image Data Descriptor (IDD)

The Image Data Descriptor structured field contains the descriptor data for an image data object. [MODCA-5-604]

#### IDD (X'D3A6FB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A6FB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-605]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **IOCAdes** | | Up to 32,759 bytes of IOCA-defined descriptor data | O | X'00' [MODCA-5-608] |

#### IDD Semantics

**IOCAdes** Contains the IOCA-defined descriptor data. See the MO:DCA environment appendix in the
*Image Object Content Architecture Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-609]


### Index Element (IEL)

The Index Element structured field identifies begin structured fields for use within a document index. [MODCA-5-610]

#### IEL (X'D3B2A7') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3B2A7' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-611]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | Triplets | | | See IEL Semantics for triplet applicability. | M | X'14' [MODCA-5-614] |

#### IEL Semantics

**Triplets** Appear in the Index Element structured field as follows: [MODCA-5-615]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-618] |
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'CA'—Index Element GID, which is used as the name of this Index Element structured field. [MODCA-5-620] |
| X'02' | | **Fully Qualified Name** Optional. One of the following Fully Qualified Name types may appear on the Index Element structured field: • X'0D'—Begin Page Group Name. Specifies the name of the page group indexed by the Index Element structured field. • X'87'—Begin Page Name. Specifies the name of the page indexed by the Index Element structured field. [MODCA-5-621] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. The Fully Qualified Name type that may appear is X'8D'—Begin Medium Map Name. For a page level IEL, specifies the name of the medium map that is active for presenting the indexed page on a physical medium. For a page group level IEL, specifies the name of the medium map that is active for presenting the first page in the indexed page group on a physical medium. [MODCA-5-622] |
| X'2D' | | **Object Byte Offset** Mandatory. Must occur once. Specifies the offset, in bytes, from the beginning of the document to the indexed object. See “Object Byte Offset Triplet X'2D'”. [MODCA-5-623] |
| X'56' | | **Medium Map Page Number** Optional. May occur once. For a page level IEL, specifies the sequence number of the indexed page in the set of sequential pages controlled by the active medium map. For a page group level IEL, specifies the sequence number of the first page-group page in the set of sequential pages controlled by the medium map that is active at the beginning of the indexed page group. See “Medium Map Page Number Triplet X'56'”. If the Page Position Information (X'81') triplet is also specified on this IEL, it overrides the Medium Map Page Number (X'56') triplet. [MODCA-5-626] |
| X'57' | | **Object Byte Extent** Optional. May occur once. Specifies the extent, in bytes, of the indexed object. See “Object Byte Extent Triplet X'57'”. [MODCA-5-627] |
| X'58' | | **Object Structured Field Offset** Optional. May occur once. Specifies the offset, in structured fields, from the beginning of the document to the indexed object. See “Object Structured Field Offset Triplet X'58'”. [MODCA-5-628] |
| X'59' | | **Object Structured Field Extent** Optional. May occur once. Specifies the extent, in structured fields, of the indexed object. See “Object Structured Field Extent Triplet X'59'”. [MODCA-5-630] |
| X'5A' | | **Object Offset** Optional. May occur once for each object type counted. Specifies how many objects of a particular type precede the indexed object in the document. See “Object Offset Triplet X'5A'”. [MODCA-5-631] |
| X'5E' | | **Object Count** Optional. May occur once for each subordinate object type counted. Specifies how many subordinate objects of a particular type are contained within the indexed object. See “Object Count Triplet X'5E'”. [MODCA-5-633] |
| X'81' | | **Page Position Information** Optional. May occur once. For a page level IEL, specifies the PGP repeating group that is used to view the page and its PMC overlay data. For a page group level IEL, specifies the PGP repeating group that is used to view the first page in the group. The PGP is specified in the medium map referenced by a FQN type X'8D'—Begin Medium Map Reference triplet. If the X'81' triplet is specified, it overrides a Medium Map Page Number (X'56') triplet. See “Page Position Information Triplet X'81'”. [MODCA-5-634] |
| X'83' | | **Presentation Control** Optional. May occur once. Specified on a page level IEL to indicate whether the page is intended to be viewed. If this triplet is not specified, the architected default is that the page is intended to be viewed. See “Presentation Control Triplet X'83'”. [MODCA-5-635] |

#### IEL Exception Condition Summary

**X'01'** This exception condition exists when:
*   Multiple type X'CA' (Index Element GID) Fully Qualified Name triplets appear. [MODCA-5-636]
*   The same object type is counted in more than one X'5A' triplet. [MODCA-5-637]
*   The same subordinate object type is counted in more than one X'5E' triplet. [MODCA-5-638]


### Invoke Medium Map (IMM)

The Invoke Medium Map structured field identifies the medium map that is to become active for the document.
An Invoke Medium Map structured field affects the document's current environment. The medium map's effect
on current environment parameter values lasts until a new medium map is invoked.

The processing system's form map is searched for the specified medium map unless the IMM directly follows
an internal medium map, in which case the IMM can reference and activate that internal medium map. An IMM
that does not follow an internal medium map cannot be used to reference an internal medium map elsewhere
in the document and is assumed to reference a medium map in the processing system's form map.

If a document does not invoke a medium map by name, and if it does not include an internal medium map, the
first medium map in the selected form map controls document presentation.

For a detailed description of IMM processing, particularly when contiguous IMMs are specified and when
constant forms control is used, see “Invoke Medium Map (IMM) Structured Field” in Chapter 4. [MODCA-5-639]

#### IMM (X'D3ABCC') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3ABCC' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-640]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **MMPName** | | Name of the medium map to be invoked | M | X'0E' [MODCA-5-643] |
| 8–n | Triplets | | | See IMM Semantics for triplet applicability. | O | X'10' [MODCA-5-644] |

#### IMM Semantics

**MMPName** Is the name of the medium map.

**Triplets** Appear as follows: [MODCA-5-645]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. |

**Effect On Parameter Values**

The parameter values contained in the structured fields within the invoked medium map replace those that were established previously by those structured fields. [MODCA-5-648]

**Parameter Conflict Resolution**

All conflicts with existing environment settings are resolved in favor of the medium map specified by the Invoke
Medium Map structured field.

**Application Notes:**

1.  Page groups are often processed in standalone fashion, that is, they are indexed, retrieved, and presented outside the context of the containing document. While the pages in the group are independent of each other and of any other pages in the document, their formatting on media depends on when the last medium map was invoked and on how many pages precede the BNG since this invocation. To make the media formatting of page groups self-contained, a medium map should be invoked at the beginning of the page group between the Begin Named Group (BNG) structured field and the first Begin Page (BPG) structured field. If this is not done, the presentation system may need to “play back” all pages between the invocation of the active medium map and the BNG to determine media formatting such as sheet-side and partition number for the first page in the group. It is therefore strongly recommended that in environments where standalone page group processing is required or anticipated, page groups are built with an Invoke Medium Map (IMM) structured field specified after the BNG and before the first BPG. [MODCA-5-649]
2.  Some AFP applications that generate page groups will support a user option that ensures that an IMM is specified after BNG and before the first BPG, and some AFP archive servers will expect an IMM there and may not present the page group correctly if none is found. However, note that this may cause the complete document to print differently. [MODCA-5-650]
3.  A newer method to specify how a page or page group should be formatted involves use of the Page Position Information (X'81') triplet. This triplet may be specified on a BPG and indicates the repeating group in the PGP structured field in the active medium map that should be used to format the page. [MODCA-5-651]

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
*   **IMM Insertion triplet X'73'**; see “IMM Insertion Triplet X'73'”. [MODCA-5-652]


### Include Object (IOB)

An Include Object structured field references an object on a page or overlay. It optionally contains parameters
that identify the object and that specify presentation parameters such as object position, size, orientation,
mapping, and default color. Where the presentation parameters conflict with parameters specified in the
object's environment group (OEG), the parameters in the Include Object structured field override. If the
referenced object is a page segment, the IOB parameters override the corresponding environment group
parameters on all data objects in the page segment. [MODCA-5-654]

#### IOB (X'D3AFC3') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AFC3' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-655]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **ObjName** | | Name of the object | M | X'06' [MODCA-5-658] |
| 8 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-659] |
| 9 | CODE | **ObjType** | X'5F', X'92', X'9B', X'BB', X'EB', X'FB' | Object type: <br> X'5F' Page Segment <br> X'92' Other object data <br> X'9B' Presentation Text (PTOCA) with OEG <br> X'BB' Graphics (GOCA) <br> X'EB' Bar Code (BCOCA) <br> X'FB' Image (IOCA) | M | X'06' [MODCA-5-660] |
| 10–12 | SBIN | **XoaOset** | -32,768–32,767 | X-axis origin of the object area <br> X'FFFFFF' Use the X-axis origin defined in the object | M | X'06' [MODCA-5-661] |
| 13–15 | SBIN | **YoaOset** | -32,768–32,767 | Y-axis origin of the object area <br> X'FFFFFF' Use the Y-axis origin defined in the object | M | X'06' [MODCA-5-662] |
| 16–17 | CODE | **XoaOrent** | | The object area's X-axis rotation from the X axis of the reference coordinate system, in degrees and minutes. Frequently used values: <br> X'0000' 0 degrees <br> X'2D00' 90 degrees <br> X'5A00' 180 degrees <br> X'8700' 270 degrees <br> X'FFFF' Use the X-axis rotation defined in the object | M | X'06' [MODCA-5-663] |
| | | | | **Bits 0–8 Degrees** B'000000000' – B'101100111' Degrees rotation (0–359) <br> B'111111111' Use the X-axis rotation defined in the object when all 16 bits in XoaOrent are B'1' | | |
| | | | | **Bits 9–14 Minutes** B'000000' – B'111011' Minutes rotation (0–59) <br> B'111111' Use the X-axis rotation defined in the object when all 16 bits in XoaOrent are B'1' | | |
| | | | | **Bit 15** B'0' Reserved <br> B'1' Use the X-axis rotation defined in the object when all 16 bits in XoaOrent are B'1' | | [MODCA-5-665] |
| 18–19 | CODE | **YoaOrent** | | The object area's Y-axis rotation from the X axis of the reference coordinate system, in degrees and minutes. Frequently used values: <br> X'0000' 0 degrees <br> X'2D00' 90 degrees <br> X'5A00' 180 degrees <br> X'8700' 270 degrees <br> X'FFFF' Use the X-axis rotation defined in the object | M | X'06' [MODCA-5-666] |
| | | | | **Bits 0–8 Degrees** B'000000000' – B'101100111' Degrees rotation (0–359) <br> B'111111111' Use the Y-axis rotation defined in the object when all 16 bits in YoaOrent are B'1' | | |
| | | | | **Bits 9–14 Minutes** B'000000' – B'111011' Minutes rotation (0–59) <br> B'111111' Use the Y-axis rotation defined in the object when all 16 bits in YoaOrent are B'1' | | |
| | | | | **Bit 15** B'0' Reserved <br> B'1' Use the Y-axis rotation defined in the object when all 16 bits in YoaOrent are B'1' | | |
| 20–22 | SBIN | **XocaOset** | -32,768–32,767 | X-axis origin for object content <br> X'FFFFFF' Use the X-axis origin defined in the object | M | X'06' [MODCA-5-667] |
| 23–25 | SBIN | **YocaOset** | -32,768–32,767 | Y-axis origin for object content <br> X'FFFFFF' Use the Y-axis origin defined in the object | M | X'06' [MODCA-5-668] |
| 26 | CODE | **RefCSys** | X'01' | Reference coordinate system: <br> X'01' Page or overlay coordinate system | M | X'06' [MODCA-5-669] |
| 27–n | Triplets | | | See **IOB Semantics** for triplet applicability. | M | X'14' [MODCA-5-670] |

#### IOB Semantics

**ObjName** Is the name of the object being referenced. This name may be a file name or any other
identifier associated with the object data.

**ObjType** Identifies the type of object being referenced.

**Value Description**

**X'5F'** Page segment object. The page segment must be a MO:DCA page segment.
AFP migration page segments are not supported in the IOB. For a definition
of MO:DCA page segments, see “Page Segment Objects”. For a
definition of AFP page segments, see “AFP Page Segment”.

**Application Notes:**

1.  A page segment included via IOB is always processed as a soft object. [MODCA-5-671]
    The OEGs for all objects in the page segment should only contain
    secondary resource mappings using MCFs to map FOCA fonts and
    MDRs to map data-object fonts (TrueType/OpenType fonts); these
    mappings must be factored up to the including page or overlay. All other
    secondary resource mappings in the OEGs, such as CMR references, are
    ignored and must be specified directly on the IOB.
2.  Page segments have traditionally been referenced with 8-byte names [MODCA-5-672]
    using a single-byte EBCDIC encoding, such as the encoding defined by
    code page 500 and character set 697. The 8-byte name limit is a formal
    restriction in the IPS and MPS structured fields, which do not support the
    FQN type X'01' triplet for extended name references. As a result, some
    AFP print servers only support 8-byte single-byte encoded page segment
    names, even when the page segment is referenced with an IOB which
    does support the FQN type X'01' triplet. It is therefore strongly
    recommended that page segment references in an IOB be limited to 8
    bytes and use a single-byte EBCDIC encoding.

**X'92'** Other object data. The object data to be included is a paginated presentation
object whose format may or may not be defined by an AFP architecture. The
object data is characterized and identified by a mandatory Object
Classification (X'10') triplet, which must specify the registered encoded
object-type OID for the object type and must characterize the object as being
a presentation object. This triplet also specifies whether the object data is
carried in a MO:DCA object container, whether it is unwrapped object data, or
whether the container structure of the object data is unknown.
**Application Note:**  If the object is installed in a resource library using a
Resource Access T able (RAT), it must not be wrapped with a MO:DCA
object container envelope, that is, it must be installed in its raw source
format.
This value is not used for OCA objects since they are referenced using object-
specific values for the ObjType parameter.
T o ensure proper presentation of the object, the encoded object-type OID
must be supported by the AFP system. This means that the encoded object-
type OID is supported by the presentation server, and that it is either
supported directly by the presentation device, or that it can be transformed by
the server into a format that is directly supported by the presentation device.
See “Non-OCA Object Types Supported by the IOB Structured Field”for a list of object types that may be included with an IOB in MO:DCA
data streams. T o see which encoded object-type OIDs are supported by the
presentation system, consult the product documentation. [MODCA-5-673]
### Include Object (IOB)


X'9B' Presentation T ext (PTOCA) object that contains an OEG, with MO:DCA
object syntax as defined in “T ext Objects”. If the text object does
not contain an OEG, exception condition X'01'exists.
**Application Note:**  A PTOCA object that contains an OEG is always
processed as a soft object. The OEG for the object should only contain
secondary resource mappings using MCFs to map FOCA fonts and
MDRs to map data-object fonts (TrueType/OpenType fonts); these
mappings must be factored up to the including page or overlay. All
other secondary resource mappings in the OEG, such as CMR
references, are ignored and must be specified directly on the IOB.
**X'BB'** Graphics (GOCA) object with MO:DCA object syntax as defined in “Graphics Objects”.

**Application Note:** A GOCA object included via IOB is always processed as a soft object. The OEG for the object should only contain secondary resource mappings using MCFs to map FOCA fonts and MDRs to map data-object fonts (TrueType/OpenType fonts); these mappings must be factored up to the including page or overlay. All other secondary resource mappings in the OEG, such as CMR references, are ignored and must be specified directly on the IOB.

**X'EB'** Bar code (BCOCA) object with MO:DCA object syntax as defined in “Bar Code Objects”.

**Application Note:** A BCOCA object included via IOB is always processed as a soft object. The OEG of a BCOCA object may contain several types of secondary resources and if it is a QR Code with Image bar code, tertiary resources. Refer to the Application Notes in “Bar Code Objects” for how to factor up resources mapped in the bar code OEG to the AEG of the page or overlay that is including the bar code. The following secondary and tertiary resources, if specified in the bar code OEG, must also be specified on the IOB:
*   Secondary presentation data objects (only when including QR Code [MODCA-5-674] with Image bar codes): Both the external name (FQN types X'CE', X'DE', or X'84') and, if specified in the OEG, the corresponding internal name (FQN type X'BE').
*   Tertiary non-presentation data objects (only when including QR Code [MODCA-5-675] with Image bar codes): As with secondary presentation data objects, both the external name and, if specified in the OEG, the corresponding internal name. However, in the case of a secondary image resource with a tertiary CMR resource, the external name of the CMR must instead be mapped using an FQN type X'EE', which must be paired with the internal name (FQN type X'BE') of the image; this is the internal name that is specified inside the QR Code with Image bar code object.
*   CMRs used directly by the bar code: The external name using FQN [MODCA-5-676] type X'DE'.

**X'FB'** Image (IOCA) object with MO:DCA object syntax as defined in “Image Objects”.

**Application Note:** Secondary resource mappings in the OEG of the IOCA object, such as CMR references, are ignored and must be specified directly on the IOB.

**All others** Reserved

**XoaOset** Specifies the offset along the X axis, Xpg or Xol, of the including page or overlay coordinate system to the origin of the X axis, Xoa, of the object area coordinate system. The value for this parameter is expressed in terms of the number of page or overlay coordinate system X-axis measurement units. [MODCA-5-677]

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment and specifies the object area offsets from the page or overlay origin for all data objects in the page segment.

A value of X'FFFFFF' indicates that the X-axis offset specified in the object's OEG is to be used. Therefore, the offset value (-1) is not included in the allowed range.

If the object does not specify the X-axis offset in an OEG, the architected default is X'000000'.

**YoaOset** Specifies the offset along the Y axis, Ypg or Yol, of the including page or overlay coordinate system to the origin of the Y axis, Yoa, of the object area coordinate system. The value for this parameter is expressed in terms of the number of page or overlay coordinate system Y-axis measurement units.

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment and specifies the object area offsets from the page or overlay origin for all data objects in the page segment.

A value of X'FFFFFF' indicates that the Y-axis offset specified in the object's OEG is to be used. Therefore, the offset value (-1) is not included in the allowed range.

If the object does not specify the Y-axis offset in an OEG, the architected default is X'000000'.

**XoaOrent** Specifies the amount of clockwise rotation of the object area's X axis, Xoa, about its defined origin relative to the X axis of the page or overlay coordinate system.

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment.

A value of B'1111111111111111' indicates that the X-axis rotation specified in the object's OEG is to be used.

If the object does not specify the X-axis rotation in an OEG, the architected default is B'0000000000000000' (0 degrees).

**YoaOrent** Specifies the amount of clockwise rotation of the object area's Y axis, Yoa, about its defined origin relative to the X axis of the page or overlay coordinate system. The YoaOrent value must be 90 degrees greater than the XoaOrent value or a X'01' exception condition exists.

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment.

A value of B'1111111111111111' indicates that the Y-axis rotation specified in the object's OEG is to be used.

If the object does not specify the Y-axis rotation in an OEG, the architected default is B'0010110100000000' (90 degrees). [MODCA-5-678]

**Note:** If the object area orientation is such that the sum of the object area origin offset and the object area extent exceeds the size of the including presentation space in either the X or Y direction, all of the object area will not fit in the including presentation space. The including presentation space in this case is the page or overlay presentation space. If an attempt is made to actually present data in the portion of the object area that falls outside the including presentation space, that portion of the data is not presented, and a X'01' exception condition exists.

**XocaOset** Used in position and position and trim mappings to specify the offset along the X axis of the object area coordinate system, Xoa, to the X origin of the object content. The value for this parameter is expressed in terms of the number of object area coordinate system X-axis measurement units.

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment.

A value of X'FFFFFF' indicates that the X-axis offset specified in the object's OEG is to be used. Therefore, the offset value (-1) is not included in the allowed range.

If the object does not specify the X-axis offset in an OEG, the architected default is X'000000'.

**YocaOset** Used in position and position and trim mappings to specify the offset along the Y axis of the object area coordinate system, Yoa, to the Y origin of the object content. The value for this parameter is expressed in terms of the number of object area coordinate system Y-axis measurement units.

If the referenced object specifies an object environment group (OEG), this parameter overrides the corresponding parameter in the Object Area Position (OBP) structured field of the OEG.

If the object is a page segment, this parameter overrides the corresponding OBP parameters in the environment groups of all objects that comprise the page segment.

A value of X'FFFFFF' indicates that the Y-axis offset specified in the object's OEG is to be used. Therefore, the offset value (-1) is not included in the allowed range.

If the object does not specify the Y-axis offset in an OEG, the architected default is X'000000'.

**RefCSys** Specifies the coordinate system used to position the object area.

**Value Description**

**X'00'** Retired for private use.

**Architecture Note:** This value is used in AFP line-data environments to position and rotate the object area with respect to the current text (I,B) coordinate system. For more information, see *Advanced Function Presentation: Programming Guide and Line Data Reference*.

**X'01'** Page or overlay coordinate system

**All others** Reserved

**Triplets** Appear in the Include Object structured field as follows: [MODCA-5-679]


| Triplet | Type | Usage [MODCA-5-680]|
| --- | --- | --- [MODCA-5-681]|
| X'01' | | Coded Graphic Character Set Global Identifier Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-682]|
**Implementation Note:**  Not all AFP servers support the inheritance
of encoding scheme from higher levels of the document hierarchy,
therefore it is recommended that this triplet be specified directly
on the IOB if required by a parameter such as the FQN type X'DE'
triplet.
X'02' Fully Qualified Name Optional. May occur once. See “Fully Qualified Name Triplet X'02'”
.
The Fully Qualified Name type that may appear is X'01'—Replace
First GID name.
This identifier overrides the Include Object structured field name and
is used as the identifier of the object. The identifier may be specified
in one—and only one—of the following formats:
* If FQNFmt = X'00', the identifier is a character-encoded name. See [MODCA-5-683]
“External Resource Naming Conventions” for a
description of the naming conventions used in AFP environments. [MODCA-5-684]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-685]|
| --- | --- | --- [MODCA-5-686]|
| X'02' | | Fully Qualified Name Optional. May occur more than once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'DE'—Data Object External Resource Reference. Specifies the external identifier of a resource object that is used by the object being included. The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The identifier may be specified in one of the following two formats, but not in both formats: • If FQNFmt = X'00', the identifier is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. • If FQNFmt = X'10', the identifier is an ASN.1 OID encoded using the definite short form. This format provides a unique and system- independent method to identify and reference an object. It may be used to select resources that are resident in the presentation device. Such an identifier is referred to as an object OID. [MODCA-5-687]|
**Architecture Note:**  The FQN type X'DE' triplet with FQNFmt =
X'10' (OID) is only used to reference the CMYK SWOP and
CMYK Euroscale resident color profiles registered in the
MO:DCA Registry; see “Resident Color Profile Identifiers”
.
If the resource is mapped with an MDR reference, the FQN type
X'DE' triplet must specify the same reference using the same FQN
format.
If the included object also references the resource with an internal
identifier, this identifier must be specified on the IOB with a FQN type
X'BE' triplet that immediately follows the FQN type X'DE' triplet. The
paired triplets map the internal identifier to the external identifier.
Resources that are used by data objects that may themselves be
processed as resources are called secondary resources. See
“Secondary Resource Objects”.
Note that, if the included object contains an OEG, the FQN type
X'DE'/X'BE' mappings on the IOB override any FQN type X'DE'/X'BE'
mappings on an MDR in the OEG; the mappings on the OEG MDR
are ignored when the object is included with an IOB. If the FQN type
X'DE' triplet on the IOB references a Color Management Resource
(CMR), the referenced CMR also overrides any other conflicting
CMR that is associated with that object, such as a CMR that is
associated with the object in the Data Object RAT . Note also that the
FQN type X'DE' triplet on the IOB cannot be used to reference a
data-object font (TrueType/OpenType font) for a GOCA or BCOCA
object (other than a QR Code with Image bar code)
; such a
reference causes an exception.
**Note:**  When a non-OCA object such as PDF or SVG references a
TTF/OTF as a secondary resource, the FQN type X'DE' triplet
on the IOB must specify the full font name of the font. This
font must also be mapped with an MDR reference that
specifies the same FFN.
X'02' Fully Qualified Name Optional. May occur more than once if the IOB also specifies FQN
type X'DE' triplets. See “Fully Qualified Name Triplet X'02'”.
The Fully Qualified Name type that may appear is X'BE'—Data
Object Internal Resource Reference. [MODCA-5-688]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-689]|
| --- | --- | --- Specifies the identifier of a resource object that is used by the object being included. The identifier is used internally by the included object to reference the resource. The identifier must be specified using FQNFmt X'00', which, for this FQN type, indicates that the data type is defined by the specific data object that generates the internal resource reference and is undefined (UNDF) at the MO:DCA data stream level. When specified, this triplet must immediately follow the FQN type [MODCA-5-690]|
| X'DE' | | triplet that specifies the external identifier of the resource, or a [MODCA-5-691]|
| X'04' | | exception condition exists. Resources that are used by data objects that may themselves be processed as resources are called secondary resources. See “Secondary Resource Objects”. Note that, if the included object contains an OEG, the FQN type [MODCA-5-692]|
| X'DE'/X'BE' | | mappings on the IOB override any FQN type X'DE'/X'BE' mappings on an MDR in the OEG; the mappings on the OEG MDR are ignored when the object is included with an IOB. [MODCA-5-693]|
| X'02' | | Fully Qualified Name Optional. May occur more than once if the included object is a QR Code with Image bar code. If the OEG of the bar code object has an MDR that references an object container from T able 48 , then this triplet is mandatory. See “Fully Qualified Name Triplet X'02'” The Fully Qualified Name type that may appear is X'CE'—Other Object Data Reference. Specifies the identifier of a resource object that is used by the object being included. The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The identifier must be specified using FQNFmt X'00', which is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. If the resource is mapped with an MDR reference, the FQN type [MODCA-5-694]|
| X'CE' | | triplet must specify the same reference using the same FQN format. If the included object also references the resource with an internal identifier, this identifier must be specified on the IOB with a FQN type [MODCA-5-695]|
| X'BE' | | triplet that immediately follows the FQN type X'CE' triplet. The paired triplets map the internal identifier to the external identifier. Resources that are used by data objects that may themselves be processed as resources are called secondary resources. See “Secondary Resource Objects”. [MODCA-5-696]|
**Architecture Note:**  For purposes of print server resource
management, each MDR that maps a presentation data object
resource in the bar code OEG must have a corresponding MDR
mapping the same resource in the AEG for the page or overlay,
without the FQN type X'BE' triplet. The same presentation data
object can be used as a primary resource on the page or overlay
and as a secondary resource in the bar code by using the FQN
type X'BE' triplet on the MDR of the barcode OEG. When both the
FQN type X'BE' triplet and the FQN type X'CE' triplet id are
specified on an MDR repeating group, they map the internal
resource identifier to the external resource identifier. [MODCA-5-697]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-698]|
| --- | --- | --- [MODCA-5-699]|
| X'02' | | Fully Qualified Name Optional. Must occur once for every FQN type X'CE' triplet specified on the IOB. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'BE'—Data Object Internal Resource Reference. Specifies the identifier that is used internally by the bar code to reference the resource whose external identifier is specified by the FQN type X'CE' triplet. The identifier must be specified using FQNFmt X'00', which, for this FQN type, indicates that the data type is defined by the specific data object that generates the internal resource reference and is undefined (UNDF) at the MO:DCA data stream level. When specified, this triplet must immediately follow the FQN type [MODCA-5-700]|
| X'CE' | | triplet that specifies the identifier of the Other Object Data resource, or a X'04' exception condition exists. Resources that are used by data objects that may themselves be processed as resources are called secondary resources. See “Secondary Resource Objects”. [MODCA-5-701]|
| X'02' | | Fully Qualified Name Optional. May occur more than once if the included object is a QR Code with Image bar code. If the OEG of the bar code object has an MDR that references an IOCA object then this triplet is mandatory. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'84'—Begin Resource Object Reference. Specifies the identifier of a resource object that is used by the object being included. The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The identifier must be specified using FQNFmt [MODCA-5-702]|
| X'00', | | which is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. If the resource is mapped with an MDR reference, the FQN type [MODCA-5-703]|
| X'84' | | triplet must specify the same reference using the same FQN format. If the included object also references the resource with an internal identifier, this identifier must be specified on the IOB with a FQN type [MODCA-5-704]|
| X'BE' | | triplet that immediately follows the FQN type X'84' triplet. The paired triplets map the internal identifier to the external identifier. Resources that are used by data objects that may themselves be processed as resources are called secondary resources. See “Secondary Resource Objects”. [MODCA-5-705]|
**Architecture Note:**  For purposes of print server resource
management, each MDR that maps an IOCA object resource in
the bar code OEG must have a corresponding MDR mapping the
same resource in the AEG for the page or overlay, without the
FQN type X'BE' triplet. The same IOCA object can be used as a
primary resource on the page or overlay and as a secondary
resource in the bar code by using the FQN type X'BE' triplet on the
MDR of the barcode OEG. When both the FQN type X'BE' triplet
and the FQN type X'84' triplet id are specified on an MDR
repeating group, they map the internal resource identifier to the
external resource identifier. [MODCA-5-706]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-707]|
| --- | --- | --- [MODCA-5-708]|
| X'02' | | Fully Qualified Name Optional. Must occur once for every FQN type X'84' triplet specified on the IOB. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'BE'—Data Object Internal Resource Reference. Specifies the identifier that is used internally by the bar code to reference the resource whose external identifier is specified by the FQN type X'84' triplet. The identifier must be specified using FQNFmt X'00', which, for this FQN type, indicates that the data type is defined by the specific data object that generates the internal resource reference and is undefined (UNDF) at the MO:DCA data stream level. When specified, this triplet must immediately follow the FQN type [MODCA-5-709]|
| X'84' | | triplet that specifies the identifier of the IOCA Image resource, or a X'04' exception condition exists. Resources that are used by data objects that may themselves be processed as resources are called secondary resources. See “Secondary Resource Objects”. [MODCA-5-710]|
| X'02' | | Fully Qualified Name Optional. May occur more than once if the included object is a QR Code with Image bar code. If the OEG of the bar code object has an MDR that references a secondary image resource that requires a tertiary CMR resource, then this triplet is mandatory. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'EE'—Tertiary Data Object External Resource Reference. Specifies the external name of the tertiary CMR associated with the secondary image resource. The identifier is used by the presentation system to locate the tertiary resource object in the resource hierarchy. The identifier must be specified using FQNFmt X'00', which is a character- encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. The FQN type X'EE' for the tertiary CMR must be followed by an FQN type X'BE' triplet identifying the local id of the secondary image resource that uses it. The paired triplets map the external CMR identifier with the internal secondary image resource identifier. Resources that are used by data objects that may themselves be processed as resources are called secondary resources and resources used by secondary resources are called tertiary resources. See “Secondary Resource Objects” and “T ertiary Resource Objects ”. [MODCA-5-711]|
**Architecture Note:**  For purposes of print server resource
management, each MDR that maps a CMR to a secondary image
resource in the bar code OEG must have a corresponding MDR
mapping the same resource in the AEG for the page or overlay. [MODCA-5-712]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-713]|
| --- | --- | --- [MODCA-5-714]|
| X'02' | | Fully Qualified Name Optional. Must occur once for every FQN type X'EE' triplet specified on the IOB. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'BE'—Data Object Internal Resource Reference. Specifies the identifier that is used internally by the bar code to reference the secondary image resource that is to be associated with the tertiary CMR whose external identifier is specified by the FQN type X'EE' triplet. The identifier must be specified using FQNFmt [MODCA-5-715]|
| X'00', | | which, for this FQN type, indicates that the data type is defined by the specific data object that generates the internal resource reference and is undefined (UNDF) at the MO:DCA data stream level. When specified, this triplet must immediately follow the FQN type [MODCA-5-716]|
| X'EE' | | triplet that specifies the identifier of the CMR external name, or a X'04' exception condition exists. Resources that are used by data objects that may themselves be processed as resources are called secondary resources and resources used by secondary resources are called tertiary resources. See “Secondary Resource Objects” and “T ertiary Resource Objects”. [MODCA-5-717]|
| X'04' | | Mapping Option Optional. May occur once. If present, defines the mapping of the object data to the object area. If the referenced object specifies an object environment group (OEG), this triplet overrides the corresponding triplet on the mapping structured field of the OEG. The specified mapping option must be valid for the object or a X'02' exception condition exists. If the referenced object is a page segment, this triplet overrides the corresponding triplet on the mapping structured field of the OEG in all objects that comprise the page segment. The specified mapping option must be valid for all objects in the page segment or a X'02' exception condition exists. See “Mapping Option Triplet X'04'”. If this triplet is omitted, the mapping option specified in the object's OEG is used. If the object does not specify the mapping option in an OEG, the architected default mapping for the object is used. Note that for objects referenced with ObjType = X'92', the architected default mapping is scale-to-fit. [MODCA-5-718]|
| X'10' | | Object Classification Mandatory for ObjType = X'92', other object data, in which case it must occur once. Specifies information used to characterize and identify the object data to be included. The included object must be a presentation object. See “Object Classification Triplet X'10'”. [MODCA-5-719]|
| X'4B' | | Measurement Units Mandatory if the IOB specifies an override for any of the following parameters: • XocaOset • YocaOset • XoaSize, specified in the Object Area Size (X'4C') triplet • YoaSize, specified in the Object Area Size (X'4C') triplet In this case, this triplet occurs once and defines the measurement units for the override values. See “Measurement Units Triplet X'4B'”. [MODCA-5-720]|
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-721]|
| --- | --- | --- [MODCA-5-722]|
| X'4C' | | Object Area Size Optional. May occur once. If present, specifies the size of the object area (XoaSize, YoaSize) into which the object data is mapped. If the referenced object specifies an Object Environment Group (OEG), this triplet overrides the corresponding triplet on the Object Area Descriptor (OBD) structured field of the OEG. If the referenced object is a page segment, this triplet overrides the corresponding triplet on the OBD structured field in all objects that comprise the page segment. If this triplet is omitted, the object area size specified in the object's OEG is used. If the object does not specify the object area size in an OEG, the architected default is to use the presentation space size of the including page or overlay. See “Object Area Size Triplet X'4C'”. [MODCA-5-723]|
**Note:**  For presentation objects, a presentation space size is required
for a scale-to-fit or scale-to-fill mapping of the object presentation
space to the object area. See “Object Type Identifiers”for information on how the presentation space size is
specified by various objects. If the object does not specify the
presentation space size, the architected default is the
presentation space size of the including page or overlay.
X'4E' Color Specification Optional. May occur once. Specifies the color that is to be used as
the default color, or the initial color, for the object. This triplet
overrides the default color specified in the data descriptor and in the
Data Object RAT , or sets the color if none is specified. Note that this
color may in turn be overridden by a color that is specified inside the
object. This triplet only overrides default colors specified for the
object presentation space; it does not affect colors assigned to the
object's object area. The IOB must specify one of the following object
types:
X'5F' Page segment
X'92' Other object data. Triplet is ignored if the object type is not
an image file format that specifies a bilevel or grayscale
image, as defined in Appendix D, “MO:DCA Registry”.
X'9B' Presentation T ext (PTOCA)
X'BB' Graphics (GOCA)
X'EB' Bar code (BCOCA)
X'FB' Image (IOCA); triplet is ignored if the image is not bilevel
When this triplet is applied to IOCA image, it only applies to bilevel
image; it is ignored when the image is not bilevel. When this triplet is
applied to non-OCA image file formats, it only applies to bilevel or
grayscale image; it is ignored when the image is not bilevel or
grayscale. Note that all 1-bit per pixel image objects are considered
bilevel. When the image is grayscale, this triplet specifies the color
that is to be grayscaled. The color space selected in the triplet must
be supported in the object‘s data descriptor structured field. For
example, if the triplet specifies a default color using ColSpce = X'08' -
CIELAB, the object‘s data descriptor must also support the CIELAB
color space. If ColSpce = X'06'- Highlight color space, the %
coverage and % shading parameters are ignored. If the above
conditions are not met, the triplet is ignored. See “Color Specification
Triplet X'4E'”.
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-724]|
| --- | --- | --- [MODCA-5-725]|
| X'5A' | | Object Offset Optional. If this IOB references a file with ObjType = X'92' that contains multiple pages or paginated objects, may occur once with ObjTpe=X'AF' to specify that pages or paginated objects are the objects to be counted. The triplet is ignored in all other cases. Selects a single paginated object to be included by specifying how many paginated objects in the referenced file precede that object. The offset is measured from the beginning of the file, so that the first paginated object has offset 0, the second has offset 1, and the nth has offset (n-1). Only the selected object is included. The IOB triplet overrides any Object Offset triplet specified on the CDD. If this triplet is not specified when the IOB references a file with ObjType = X'92' that contains multiple paginated objects, the default is to include the first paginated object in the file. For more information on selecting paginated objects, see “Object Offset Triplet X'5A'”. [MODCA-5-726]|
**Architecture Note:**  While only the selected paginated object in the
file is actually presented on the page or overlay, the file referenced
by the IOB can be processed by the presentation system as a
complete entity. This means that the complete file can be
downloaded to the presentation device and multiple paginated
objects in the file can be processed using the environment defined
by the file. For example, if the file is a multi-page PDF , pages
included from that file can be processed by the presentation
device with the same PDF RIP initialization.
X'70' Presentation Space Reset
Mixing
Optional. May occur once. This triplet may not appear on the Include
Object structured field with a Presentation Space Mixing Rule (X'71')
triplet. If present with BgMxFlag=1, specifies that both background
and foreground of the referenced object data presentation space
overpaint the area of the page or overlay presentation space that lies
beneath it. If the referenced object specifies an Object Environment
Group (OEG), this triplet overrides the corresponding triplet on the
OBD structured field of the OEG. If the referenced object is a page
segment, this triplet overrides the corresponding triplet on the OBD
structured field in all objects that comprise the page segment. If this
triplet is omitted, the triplet specified on the OBD of the object's OEG
is used. If the object does not specify this triplet on the OBD in an
OEG, the architected default is to use the default mixing rule, that is,
this triplet is ignored. For a definition of mixing rules see “Mixing
Rules”. See “Presentation Space Reset Mixing Triplet
X'70'”.
X'71' Presentation Space Mixing
Rules
Optional. May occur once. This triplet may not appear on the Include
Object structured field with a Presentation Space Reset Mixing
(X'70') triplet. If present, specifies the mixing rules for color mixing
foreground and background object data on the portion of the page or
overlay presentation space that lies beneath the object area. If the
referenced object specifies an Object Environment Group (OEG),
this triplet overrides the corresponding triplet on the OBD structured
field of the OEG. If the referenced object is a page segment, this
triplet overrides the corresponding triplet on the OBD structured field
in all objects that comprise the page segment. If this triplet is omitted,
the triplet specified on the OBD of the object's OEG is used. If the
object does not specify this triplet on the OBD in an OEG, the
architected default is to use the default mixing rule, that is, this triplet
is ignored. For a definition of mixing rules see “Mixing Rules”. See “Presentation Space Mixing Rules Triplet X'71'”.
**Implementation Note:**  The Presentation Space Mixing Rules (X'71')
triplet is currently not used in AFP environments. [MODCA-5-727]
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-728]|
| --- | --- | --- [MODCA-5-729]|
| X'91' | | Color Management Resource Descriptor Optional. May occur when the IOB references a Color Management Resource (CMR) with the FQN type X'DE' or type X'EE' triplet, in which case this triplet is mandatory and must occur once for each CMR reference. It is ignored in all other cases. Specifies the processing mode and scope for the CMR. The CMRScpe parameter in the triplet must be set to X'01' to indicate that the scope of the CMR is a data object. When specified with an FQN type X'DE' triplet , this triplet must immediately follow the FQN type X'DE' triplet that specifies the CMR name or a X'04' exception condition exists. When specified with an FQN type X'EE' triplet, this triplet must immediately follow the FQN type X'BE' triplet that follows the FQN type X'EE' triplet that specifies the CMR name or a X'04' exception condition exists. See “Color Management Resource Descriptor Triplet X'91'”. [MODCA-5-730]|
| X'95' | | Rendering Intent Optional. May occur once. See “Rendering Intent Triplet X'95'”. This triplet specifies the rendering intent that is to be used when presenting the object that is referenced with this structured field. Only the rendering intent that applies to the object type of the referenced object is used; the other rendering intents are ignored. This triplet overrides all rendering intents specified elsewhere for the object, such as in the object's OEG or in a Data Object RAT entry for the object. The triplet also overrides any rendering intent information embedded in the data object. The rendering intent in this triplet is downloaded to the presentation device but may not be used if a Link DL CMR is used for a color conversion in this object; in that case the rendering intent specified in the Link DL CMR is used for that color conversion. [MODCA-5-731]|
| X'9A' | | Image Resolution Optional. May occur once for non-IOCA raster image object types defined by ObjType = X'92' - “other object data”; ignored for IOCA image objects and all other object types. Specifies the resolution of the raster image object. See “Image Resolution Triplet X'9A'”. The IOB triplet overrides any image resolution specified in the Data Object RAT , on the CDD, or inside the image. If the resolution is not specified outside the image or inside the image, the default is to assume that the image resolution is the same as the output device resolution. [MODCA-5-732]|
### Include Object (IOB)


| Triplet | Type | Usage [MODCA-5-733]|
| --- | --- | --- [MODCA-5-734]|
| X'9C' | | Object Container Presentation Space Size Optional. May occur once for certain object types defined by ObjType = X'92' - “other object data”; ignored for IOCA image objects and all other object types. See “Object Container Presentation Space Size Triplet X'9C'”. May be specified for the following object types: • PDF - all presentation object types • AFPC SVG Subset Specifies the presentation space size of the object container. For PDF object types, specifies how this size is determined. For SVG, specifies the actual size, and overrides any presentation space size specified within the SVG object. The IOB triplet overrides any specification on object container presentation space size in the Data Object RAT or on the CDD. [MODCA-5-735]|
| X'FF' | | Triplet Extender Optional. May occur more than once in a contiguous sequence, but only in the following cases . It is ignored in all other cases. See “Triplet Extender Triplet X'FF'”. • The IOB must specify one of the following object types: [MODCA-5-736]|
| X'92' | | Other object data – The IOB references a secondary resource for other object data using an FQN type X'DE' triplet – The secondary resource is the generic non-OCA Resource object – The IOB associates an internal resource reference to the secondary resource with an FQN type X'BE' triplet – The triplet extenders must follow the FQN type X'BE' triplet and must occur in a contiguous sequence [MODCA-5-737]|
| X'EB' | | Bar code object – The IOB references a secondary resource for other object data using an FQN type X'CE' triplet – That secondary resource references a tertiary resource that is the generic non-OCA Resource object, using the FQN type X'DE' triplet – The IOB associates an internal resource reference to the tertiary resource referenced by the FQN type X'DE' with an FQN type X'BE' triplet – The triplet extenders must follow the FQN type X'BE' triplet and must occur in a contiguous sequence Specifies a portion of a secondary or tertiary resource reference that occurs internal to the data object referenced by the IOB. Use of the triplet extender allows the length of the internal resource reference to exceed the 250 byte capacity of the FQN type X'BE' triplet. [MODCA-5-738]|
**Note:**  The non-OCA Resource Object must be mapped with an MDR
reference that matches the FQN type X'DE' reference on the
IOB.
**Application Note:**  T o optimize print performance, it is strongly recommended that the same encoding scheme
be used for a resource reference wherever in a print file that resource reference is specified. That is, the
encoding scheme used for the resource include, the resource map, and the resource wrapper should be
the same.
### Include Object (IOB)


**Architecture Note:**  When the IOB structured field is used in a page definition object in AFP line-data
environments, an Extended Resource Local Identifier (X'22') triplet must be specified with ResType=
X'30'—IOB Reference. The same triplet is used on a Descriptor in the Page Definition to reference the
IOB and cause the specified object to be included. [MODCA-5-739]
#### IOB Exception Condition Summary
X'01' This exception condition exists when:
* The value specified for YoaOrent is not 90 degrees greater rotation than the value specified [MODCA-5-740]
for XoaOrent.
* An attempt is made to present data outside the presentation space of the containing [MODCA-5-741]
coordinate system.
* The mapping option is position and an attempt is made to present data outside the object [MODCA-5-742]
area presentation space.
* A Presentation Space Reset Mixing triplet and a Presentation Space Mixing Rules triplet are [MODCA-5-743]
specified.
* A Presentation T ext object is to be included, but it does not contain an OEG. [MODCA-5-744]
X'02' The mapping option specified in a Mapping Option triplet is not valid for one or more of the
referenced objects.
X'04' This exception condition exists when:
* An FQN type X'BE' triplet is specified but does not immediately follow an FQN type X'DE', [MODCA-5-745]
X'84', X'CE', or X'EE' triplet.
* A Color Management Resource Descriptor triplet is specified but does not either [MODCA-5-746]
immediately follow an FQN type X'DE' triplet that references a CMR, or immediately follow
the FQN type X'BE' triplet that follows the FQN type X'EE' triplet that references a CMR . [MODCA-5-747]
### Include Object (IOB)


### Image Picture Data (IPD)

The Image Picture Data structured field contains the data for an image data object. [MODCA-5-748]

#### IPD (X'D3EEFB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3EEFB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-749]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–n | UNDF | **IOCAdat** | | Up to 32,759 bytes of IOCA-defined data | O | X'00' [MODCA-5-752] |

#### IPD Semantics

**IOCAdat** Contains the IOCA-defined data. See the MO:DCA environment appendix in the *Image Object
Content Architecture Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-753]


### Include Page (IPG)

The Include Page structured field references a page that is to be included in the document. The Include Page
structured field may occur in document state, page-group state, or page state. In all three cases the referenced
page is positioned on the media using the (Xm, Ym) offsets specified in the PGP structured field in the active
medium map. The referenced page must not contain another Include Page structured field. [MODCA-5-754]

#### IPG (X'D3AFAF') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AFAF' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-755]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PgName** | | Name of the page | M | X'06' [MODCA-5-758] |
| 8–15 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-759] |
| 16 | BITS | **IPgFlgs** | | Specify control information for the included page. See IPG Semantics for bit definitions. | M | X'06' [MODCA-5-760] |
| 17–n | Triplets | | | See IPG Semantics for triplet applicability. | M | X'14' [MODCA-5-761] |

#### IPG Semantics

**PgName** Is the name of the page being referenced. The page name is qualified, using the Fully
Qualified Name (X'02') type X'83' triplet, with the name of the document that contains the
page.

**IPgFlgs** Specify control information for the included page.

**Bit Description**

**0** Format of included page, must be set to B'1'. [MODCA-5-762]
*   B'0' Reserved
*   B'1' The referenced page is carried in a document in a print file level resource group. Before this page can be included with the IPG, it must be processed with all required resources and saved in the presentation device. The processing includes the application of all text suppressions specified in the medium map that is active when the page is saved.

**1–7** Reserved; all bits should be B'0'.

**Triplets** Appear in the Include Page structured field as follows: [MODCA-5-763]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-766] |
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'83'—Begin Document Name. Specifies the name of the document that contains the referenced page. [MODCA-5-768] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Include Page structured field name and is used as the name of the page. [MODCA-5-769] |
| X'5A' | | **Object Offset** Optional. May occur once, with ObjTpe=X'AF' to specify that pages are the objects to be counted for the offset. Specifies how many pages in the referenced document precede the page to be included. The page offset is measured from the beginning of the referenced document, so that the first page has offset 0, the second page has offset 1, and the nth page has offset (n-1). When this triplet is specified, the page name, as specified by the PgName parameter or by the Fully Qualified Name type X'01' triplet, is ignored. See “Object Offset Triplet X'5A'”. [MODCA-5-770] |

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme
be used for a resource reference wherever in a print file that resource reference is specified. That is, the
encoding scheme used for the resource include, the resource map, and the resource wrapper should be
the same.

**Notes:**

1.  Care must be taken when activating text suppressions on pages to be saved. The document that contains [MODCA-5-771]
    the pages to be saved must be processed with the same form map as the document that references the
    saved pages. However, unless the two documents have the identical structure with respect to pages,
    Invoke Medium Map (IMM) structured fields, and internal (inline) medium maps, the medium map that is
    active when the page is saved may specify different text suppressions than the medium map that is active
    when the page is included, which may yield unexpected results.
2.  If the medium map specifies multiple copy subgroups with different text suppression activations, the [MODCA-5-772]
    presentation device must process and save a copy of the page for each set of text suppressions. When an
    IPG is processed for multiple copy subgroups, the presentation device uses the copy of the saved page
    whose text suppressions match those required by the current medium map.
3.  The following rules apply to overlays when a page is processed and saved by the presentation device: [MODCA-5-773]
    *   Page overlays are processed and saved with the page. [MODCA-5-774]
    *   PMC overlays are not processed and saved with the page. They are applied to the page when it is [MODCA-5-775]
        included with an IPG as specified by the medium map that is active during page presentation.
    *   Medium overlays are not processed and saved with the page. They are applied to the medium as [MODCA-5-776]
        specified by the medium map that is active during page presentation. [MODCA-5-777]
4.  Overlays that are included on the saved page may overflow the saved page presentation space. Such [MODCA-5-778]
    overflow areas need to be saved with the page since they only cause an exception at presentation time if
    they contain data that overflows the medium presentation space. If an attempt is made to present overlay
    data that overflows the medium presentation space, that portion of the data is not presented and a X'01'
    exception condition exists.
5.  The size of the page may exceed the size of the medium presentation space in either the Xm or Ym
    direction. If an attempt is made to present data in the portion of the page that overflows the medium
    presentation space, that portion of the data is not presented and a X'01' exception condition exists. [MODCA-5-779]
6.  A page that is included with an IPG may be indexed as follows: [MODCA-5-780]
    *   If the IPG occurs in document state or in page-group state, the included page may be indexed using an [MODCA-5-781]
        offset to the location of the IPG in the document.
    *   If the IPG occurs in page state, the included page becomes a part of the containing page, therefore only [MODCA-5-782]
        the containing page may be indexed using an offset to its location in the document. [MODCA-5-783]


### Include Page Overlay (IPO)

The Include Page Overlay structured field references an overlay resource definition that is to be positioned on
the page. A page overlay can be referenced at any time during the page state, but not during an object state.
The overlay contains its own active environment group definition.
The current environment of the page that included the overlay is restored when the Include Page Overlay has
been completed.

#### IPO (X'D3AFD8') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AFD8' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-784]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **OvlyName** | | Name of the overlay resource | M | X'06' [MODCA-5-787] |
| 8–10 | SBIN | **XolOset** | -32,768–32,767 | X-axis origin for the page overlay <br> X'FFFFFF' Retired value | M | X'06' [MODCA-5-788] |
| 11–13 | SBIN | **YolOset** | -32,768–32,767 | Y-axis origin for the page overlay <br> X'FFFFFF' Retired value | M | X'06' [MODCA-5-789] |
| 14–15 | CODE | **OvlyOrent** | | The overlay's X-axis rotation from the X axis of the including page coordinate system: <br> X'0000' 0 degrees <br> X'2D00' 90 degrees <br> X'5A00' 180 degrees <br> X'8700' 270 degrees | O | X'02' [MODCA-5-790] |
| 16–n | Triplets | | | See **IPO Semantics** for triplet applicability. | O | X'10' [MODCA-5-791] |

#### IPO Semantics

**OvlyName** Is the name of the overlay resource being referenced.

**XolOset** Specifies the offset along the X-axis of the including page coordinate system, Xpg, to the origin
of the X axis for the page overlay coordinate system, Xol. The value X'FFFFFF' is retired,
therefore the offset value (-1) is not included in the allowed range. See the architecture note
following the Triplets section. The value for this parameter is expressed in terms of the number
of page coordinate system X-axis measurement units.

**YolOset** Specifies the offset along the Y axis of the including page coordinate system, Ypg, to the origin
of the Y axis for the page overlay coordinate system, Yol. The value X'FFFFFF' is retired,
therefore the offset value (-1) is not included in the allowed range. See the architecture note
following the Triplets section. The value for this parameter is expressed in terms of the number
of page coordinate system Y-axis measurement units.

**OvlyOrent** Specifies the amount of rotation of the page overlay's X axis, Xol, about the page overlay origin
relative to the X axis, Xpg, of the including page coordinate system. The page overlay X axis
rotation is limited to 0, 90, 180, and 270 degrees. The page overlay Y-axis rotation is always
90 degrees greater than the page overlay X-axis rotation. [MODCA-5-792] [MODCA-5-793]

If no value is specified for this parameter, the architected default is 0 degrees.

**Note:** If the rotation is such that the sum of the page overlay origin offset and the page overlay
extent exceeds the size of the including presentation space in either the X or Y direction,
all of the object area will not fit on the including presentation space. The including
presentation space in this case is the medium presentation space. If an attempt is made
to actually present data in the portion of the page overlay that falls outside the including
presentation space, that portion of the data is not presented, and a X'01' exception
condition exists.

**Triplets** Appear in the Include Page Overlay structured field as follows: [MODCA-5-794]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-797] |
| X'02' | | **Fully Qualified Name** Optional. May occur once. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'01'—Replace First GID Name. This GID overrides the Include Overlay structured field name and is used as the name of the overlay. [MODCA-5-798] |

**Note:** If a triplet is included on this structured field, the optional positional parameter becomes mandatory.

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme
be used for a resource reference wherever in a print file that resource reference is specified. That is, the
encoding scheme used for the resource include, the resource map, and the resource wrapper should be
the same.

**Architecture Notes:**

1.  In AFP environments, the following retired triplets are used on this structured field: [MODCA-5-799]
    *   **Page Overlay Conditional Processing (X'46') triplet**, may occur zero or more times; see “Page Overlay Conditional Processing Triplet X'46'”. [MODCA-5-800]
    *   **Resource Usage Attribute (X'47') triplet**, may occur zero or once; see “Resource Usage Attribute Triplet X'47'”. [MODCA-5-801]
2.  In AFP line data environments, the value X'FFFFFF' is supported for the XolOset and YolOset parameters [MODCA-5-802]
    to indicate that the Xp or Yp position, respectively, defined by the current Line Descriptor (LND) in the page
    definition is to be used as the origin for the overlay. This value was also valid in pre-1992 AFP data streams
    to specify the current text print position and is supported by some print servers for migration of such data
    streams. However, this value is not valid in MO:DCA data streams and should not be generated by
    MO:DCA applications. To record support for this value by some AFP print servers and to limit any further
    use, this value is retired; see “Retired Parameters”. [MODCA-5-803]

#### IPO Exception Condition Summary

**X'01'** An attempt is made to present data outside the medium presentation space. See the note
under OvlyOrent for details. [MODCA-5-804]


### Include Page Segment (IPS)

The Include Page Segment structured field references a page segment resource object that is to be presented
on the page or overlay presentation space. The IPS specifies a reference point on the including page or
overlay coordinate system that may be used to position objects contained in the page segment. A page
segment can be referenced at any time during page or overlay state, but not during an object state. The page
segment inherits the active environment group definition of the including page or overlay. [MODCA-5-805]

#### IPS (X'D3AF5F') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AF5F' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-806]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **PsegName** | | Name of the page segment resource | M | X'06' [MODCA-5-809] |
| 8–10 | SBIN | **XpsOset** | -32,768–32,767 | X-axis origin for positioning objects <br> X'FFFFFF' Retired value | M | X'06' [MODCA-5-810] |
| 11–13 | SBIN | **YpsOset** | -32,768–32,767 | Y-axis origin for positioning objects <br> X'FFFFFF' Retired value | M | X'06' [MODCA-5-811] |
| 14–n | Triplets | | | See **IPS Semantics** for triplet applicability. | O | X'10' [MODCA-5-812] |

#### IPS Semantics

**PsegName** Is the name of the page segment resource object being referenced.

**XpsOset** Specifies the offset along the X axis of the including page coordinate system, Xpg, or the
including overlay coordinate system, Xol, to the reference point that may be used to position
objects in the page segment. The value X'FFFFFF' is retired, therefore the offset value (-1) is
not included in the allowed range. See the architecture note following the Triplets section. The
value for this parameter is expressed in terms of the number of page or overlay coordinate
system X-axis measurement units.

**YpsOset** Specifies the offset along the Y axis of the including page coordinate system, Ypg, or the
including overlay coordinate system, Yol, to the reference point that may be used to position
objects in the page segment. The value X'FFFFFF' is retired, therefore the offset value (-1) is
not included in the allowed range. See the architecture note following the Triplets section. The
value for this parameter is expressed in terms of the number of page or overlay coordinate
system Y-axis measurement units.

**Triplets** Appear as follows: [MODCA-5-813]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. |

**Application Notes:**

1.  A page segment included on a page or overlay with an IPS may optionally be mapped with an MPS in the AEG for that page or overlay. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment data is sent to the presentation device as part of the page or overlay and is called a soft page segment.
2.  For a hard page segment included via IPS, the OEGs for all objects in the page segment should not contain any secondary resource mappings, such as font mappings and CMR references using MCF and MDR structured fields; such mappings are ignored.
3.  For a soft page segment included via IPS, all secondary resource mappings in the OEGs for objects in the page segment, such as font mappings and CMR references using MCF and MDR structured fields, must be factored up to the including page or overlay.
4.  To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified. That is, the encoding scheme used for the resource include, the resource map, and the resource wrapper should be the same.

**Architecture Notes:**

1.  In AFP environments, the following retired triplet is used on this structured field:
    *   **Line Data Object Position Migration (X'27') triplet**; see “Line Data Object Position Migration Triplet X'27'”.
2.  In AFP line data environments, the value X'FFFFFF' is supported for the XpsOset and YpsOset parameters to indicate that the Xp or Yp position, respectively, defined by the current Line Descriptor (LND) in the Page Definition is to be used as the “origin” for the page segment. This value was also valid in pre-1992 AFP data streams to specify the current text print position and is supported by some print servers for migration of such data streams. However this value is not valid in MO:DCA data streams and should not be generated by MO:DCA applications. To record support for this value by some AFP print servers and to limit any further use, this value is retired; see “Retired Parameters”. [MODCA-5-816]

#### IPS Exception Condition Summary

**X'01'** An attempt is made to present data outside the medium presentation space. [MODCA-5-817]


### Link Logical Element (LLE)

A Link Logical Element structured field specifies the linkage from a source document component to a target
document component. The LLE identifies the source and target and indicates the purpose of the linkage by
specifying a link type. The link source and link target may be in the same document component or in different
document components, and they need not be of the same document component type. The linkage may involve
a complete document component, or it may be restricted to a rectangular area on the presentation space
associated with the document component. The Link Logical Element structured field can be embedded in the
document that contains the link source, in the document that contains the link target, in the document index for
either document, or in any combination of these structures. Link Logical Element parameters do not provide
any presentation specifications. [MODCA-5-818]

#### LLE (X'D3B490') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3B490' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-819]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0 | CODE | **LnkType** | X'01'–X'03' | Link type: [MODCA-5-820] | M | X'06' [MODCA-5-821] |
| | | | | X'01' Navigation link | | |
| | | | | X'02' Annotation link | | |
| | | | | X'03' Append link | | |
| 1 | | | | Reserved; should be zero | M | X'06' [MODCA-5-822] |
| | | | | Two or three repeating groups in the following format: | | [MODCA-5-823] |
| 0–1 | UBIN | **RGLength** | 3–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-824] |
| 2 | CODE | **RGFunct** | X'01'–X'03' | Repeating group function: [MODCA-5-825] | M | X'06' |
| | | | | X'01' Link attribute specification | | |
| | | | | X'02' Link source specification | | |
| | | | | X'03' Link target specification | | |
| 3–n | | Triplets | | See LLE Semantics for triplet applicability. | O | X'14' [MODCA-5-826] |

#### LLE Semantics

**LnkType** Specifies the purpose of the link.

**Value Description**
*   **X'01'** Navigation link. Specifies the linkage from a source document component to a contextually-related target document component. Navigation links may be used to support applications such as hypertext and hypermedia.
*   **X'02'** Annotation link. Specifies the linkage from a source document component to a target document component that contains an annotation for the source. [MODCA-5-827]
*   **X'03'** Append link. Specifies the linkage from a source document component to a target document component that contains information that is to be appended to the source.

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself. [MODCA-5-828]

**RGFunct** Specifies the function of the repeating group.

**Value Description**
*   **X'01'** Link attribute specification repeating group. Specifies attributes of the link.
*   **X'02'** Link source specification repeating group. Identifies the source of the link.
*   **X'03'** Link target specification repeating group. Identifies the target of the link.

**Triplets** Appear on the LLE repeating groups as follows:

**Link Attribute Repeating Group**
*   **Coded Graphic Character Set Global Identifier (X'01') triplet** [MODCA-5-829]
*   **Fully Qualified Name (X'02') triplet, type X'0C'—Process Element (LLE) Name** [MODCA-5-830]
*   **Parameter Value (X'82') triplet** [MODCA-5-831]

**Link Source Repeating Group**
*   **Coded Graphic Character Set Global Identifier (X'01') triplet** [MODCA-5-832]
*   **Fully Qualified Name (X'02') triplet, type X'09'—MO:DCA Resource Hierarchy Reference** [MODCA-5-833]
*   **Fully Qualified Name (X'02') triplet, type X'0A'—Begin Resource Group Reference** [MODCA-5-834]
*   **Fully Qualified Name (X'02') triplet, type X'0C'—Process Element (TLE) Name** [MODCA-5-835]
*   **Fully Qualified Name (X'02') triplet, type X'0D'—Begin Page Group Reference** [MODCA-5-836]
*   **Fully Qualified Name (X'02') triplet, type X'83'—Begin Document Reference** [MODCA-5-837]
*   **Fully Qualified Name (X'02') triplet, type X'87'—Begin Page Reference** [MODCA-5-838]
*   **Fully Qualified Name (X'02') triplet, type X'B0'—Begin Overlay Reference** [MODCA-5-839]
*   **Fully Qualified Name (X'02') triplet, type X'CE'—Other Object Data Reference** [MODCA-5-840]
*   **Object Classification (X'10') triplet** [MODCA-5-841]
*   **Measurement Units (X'4B') triplet** [MODCA-5-842]
*   **Area Definition (X'4D') triplet** [MODCA-5-843]

**Link Target Repeating Group**
*   **Coded Graphic Character Set Global Identifier (X'01') triplet** [MODCA-5-844]
*   **Fully Qualified Name (X'02') triplet, type X'09'—MO:DCA Resource Hierarchy Reference** [MODCA-5-845]
*   **Fully Qualified Name (X'02') triplet, type X'0A'—Begin Resource Group Reference** [MODCA-5-846]
*   **Fully Qualified Name (X'02') triplet, type X'0C'—Process Element (TLE) Name** [MODCA-5-847]
*   **Fully Qualified Name (X'02') triplet, type X'0D'—Begin Page Group Reference** [MODCA-5-848]
*   **Fully Qualified Name (X'02') triplet, type X'83'—Begin Document Reference** [MODCA-5-849]
*   **Fully Qualified Name (X'02') triplet, type X'87'—Begin Page Reference** [MODCA-5-850]
*   **Fully Qualified Name (X'02') triplet, type X'B0'—Begin Overlay Reference** [MODCA-5-851]
*   **Fully Qualified Name (X'02') triplet, type X'CE'—Other Object Data Reference** [MODCA-5-852]
*   **Object Classification (X'10') triplet** [MODCA-5-853]
*   **Measurement Units (X'4B') triplet** [MODCA-5-854]
*   **Area Definition (X'4D') triplet** [MODCA-5-855]

Note that by specifying FQNFmt = X'20' - URL for the FQN format of the target name, the LLE
can be used to link to resources on the Internet using a Uniform Resource Locator (URL).

Details on triplet semantics and on rules for including each triplet on the repeating groups are
as follows:

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** [MODCA-5-856]<br>Optional. May occur multiple times in each repeating group. If in a link attribute repeating group, specifies the code page and character set for all character data in all three LLE repeating groups, unless overridden by a **Coded Graphic Character Set Global Identifier** triplet in a source or target repeating group, in which case the latter triplet specifies the code page and character set for that repeating group. If in a link source or link target repeating group, specifies the code page and character set for that repeating group. By specifying this triplet multiple times in a link source or link target repeating group, you can specify a unique code page and character set for the character data in every triplet on that repeating group. [MODCA-5-857]<br>[MODCA-5-858] |
| X'02' | | **Fully Qualified Name** [MODCA-5-859]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'09'—MO:DCA Resource Hierarchy Reference. If in a link source repeating group, specifies that the link source object is located in the MO:DCA resource hierarchy. If in a link target repeating group, specifies that the link target object is located in the MO:DCA resource hierarchy. See “Resource Search Order”. |
| X'02' | | **Fully Qualified Name** [MODCA-5-860]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'0A'—Begin Resource Group Reference. If in a link source repeating group, specifies a resource group that contains the link source. If in a link target repeating group, specifies a resource group that contains the link target. |
| X'02' | | **Fully Qualified Name** [MODCA-5-861]<br>Optional. May occur once in each repeating group. The **Fully Qualified Name** type that may appear is X'0C'—Process Element Name. If in a link attribute repeating group, specifies the name of the Link Logical Element. If in a link source repeating group, specifies the name of a Tag Logical Element that is the link source. If in a link target repeating group, specifies the name of a Tag Logical Element that is the link target. |
| X'02' | | **Fully Qualified Name** [MODCA-5-862]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'0D'—Begin Page Group Reference. If in a link source repeating group, specifies a page group that is the link source or that contains the link source. If in a link target repeating group, specifies a page group that is the link target or that contains the link target. |
| X'02' | | **Fully Qualified Name** [MODCA-5-863]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'83'—Begin Document Reference. If in a link source repeating group, specifies a document that is the link source or that contains the link source. If in a link target repeating group, specifies a document that is the link target or that contains the link target. |
| X'02' | | **Fully Qualified Name** [MODCA-5-864]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'87'—Begin Page Reference. If in a link source repeating group, specifies a page that is the link source or that contains the link source. If in a link target repeating group, specifies a page that is the link target or that contains the link target. [MODCA-5-865]<br>[MODCA-5-866] |
| X'02' | | **Fully Qualified Name** [MODCA-5-867]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'B0'—Begin Overlay Reference. If in a link source repeating group, specifies an overlay that is the link source or that contains the link source. If in a link target repeating group, specifies an overlay that is the link target or that contains the link target. |
| X'02' | | **Fully Qualified Name** [MODCA-5-868]<br>Optional. May occur once in a link source repeating group and once in a link target repeating group. The **Fully Qualified Name** type that may appear is X'CE'—Other Object Data Reference. If in a link source repeating group, specifies other object data that is the link source or that contains the area that is the link source. If in a link target repeating group, specifies other object data that is the link target or that contains the area that is the link target. The object data being linked may or may not be defined by an AFP architecture. The object data is characterized and identified by a mandatory **Object Classification (X'10') triplet**, which also specifies whether the object data is carried in a MO:DCA object container, whether it is unwrapped object data, or whether the container structure of the object data is unknown. Note that if FQNFmt X'20' (URL) is used to specify a link source or target, the object type is defined by the URL itself and the **Object Classification (X'10') triplet** becomes optional. |
| X'10' | | **Object Classification** [MODCA-5-869]<br>Mandatory if the **Fully Qualified Name** type X'CE', Other Object Data Reference, appears in a link source or a link target repeating group, in which case it must occur once in that repeating group. Otherwise this triplet is not allowed in a repeating group. Specifies information used to characterize and identify other object data. Note however that if FQN type X'CE' with FQNFmt X'20' (URL) is used to specify the link source or target, the object type is defined by the URL itself and the **Object Classification (X'10') triplet** becomes optional. See “Object Classification Triplet X'10'”. |
| X'4B' | | **Measurement Units** [MODCA-5-870]<br>Optional if one or more **Area Definition (X'4D') triplets** are present in a link source or link target repeating group, in which case it may occur once in that repeating group. Specifies the units of measure to be used for positioning areas and for determining their size. If this triplet is omitted when an **Area Definition** triplet is present, the units of measure are specified by the document component on which the area is defined. See “Measurement Units Triplet X'4B'”. [MODCA-5-871]<br>[MODCA-5-872] |
| X'4D' | | **Area Definition** [MODCA-5-873]<br>Optional. May occur multiple times in a link source repeating group and multiple times in a link target repeating group. Defines a rectangular area on the presentation space of the lowest document component in the document hierarchy that is specified by the repeating group or that is inherited by the repeating group. If the repeating group does not explicitly specify an object, then the object specification is inherited from the document hierarchy. For example, if the LLE is located in a page, and if the repeating group does not specify any document component at the page level or at a lower level in the document hierarchy, then the area is defined on the presentation space for the page that contains the LLE. The units of measure for resolving the offset and size of the area are specified by a **Measurement Units** triplet, if present, or by the document component on which the presentation space is defined if the triplet is not present. When this triplet occurs multiple times on a link source repeating group, the logical union of the areas defines the link source. When this triplet occurs multiple times on a link target repeating group, the logical union of the areas defines the link target. See “Area Definition Triplet X'4D'”. |
| X'82' | | **Parameter Value** [MODCA-5-874]<br>Optional. May occur multiple times in a link attribute repeating group. Used to pass parameter values to the link target. See “Parameter Value Triplet X'82'”. |

#### LLE Exception Condition Summary

**X'04'** The **Area Definition** triplet is present in a repeating group but the **Measurement Units** triplet is absent and the lowest identified document component in the document hierarchy does not define units of measure. [MODCA-5-875]

### Map Bar Code Object (MBC)

The Map Bar Code Object structured field specifies how a bar code data object is to be mapped into its object area.

#### MBC (X'D3ABEB') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3ABEB' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-876]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| | | | | One repeating group in the following format: [MODCA-5-877] | | [MODCA-5-878] |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-879] |
| 2–4 | | Triplets | | Mapping Option triplet | M | X'14' [MODCA-5-880] |

#### MBC Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Bar Code Object structured field as follows: [MODCA-5-881]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'04' | | **Mapping Option** [MODCA-5-882]<br>Mandatory. Must occur once in each repeating group. See “Mapping Option Triplet X'04'”. The valid mapping options for the MBC structured field are: [MODCA-5-883]<br>**Value Description** [MODCA-5-884]<br>**X'00'** Position [MODCA-5-885]<br>All others Reserved |

**Note:** If this structured field is not present in the data stream, the architected default is position. [MODCA-5-886]

#### MBC Exception Condition Summary

**X'01'** The Map Bar Code Object structured field contains more than one repeating group.
**X'02'** A **Mapping Option (X'04')** triplet value other than X'00' is specified. [MODCA-5-887]

### Medium Copy Count (MCC)

The Medium Copy Count structured field specifies the number of copies of each medium, or sheet, to be
presented, and the modifications that apply to each copy. This specification is called a copy group. The MCC
contains repeating groups that specify copy subgroups, such that each copy subgroup may be specified
independently of any other copy subgroup. For each copy subgroup, the number of copies, as well as the
modifications to be applied to each copy, is specified by the repeating group. If the modifications for a copy
subgroup specify duplexing, that copy subgroup and all successive copy subgroups are paired such that the
first copy subgroup in the pair specifies the copy count as well as the modifications to be applied to the front
side of each copy, and the second copy subgroup in the pair specifies the same copy count as well as an
independent set of modifications to be applied to the back side of each copy. The pairing of copy subgroups
continues as long as duplexing is specified. [MODCA-5-888]

#### MCC (X'D3A288') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3A288' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-889]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| | | | | One to 128 repeating groups in the following format: [MODCA-5-890] | | [MODCA-5-891] |
| 0–1 | UBIN | **Startnum** | 1–32,386 | Starting copy number | M | X'06' [MODCA-5-892] |
| 2–3 | UBIN | **Stopnum** | 1–32,640 | Ending copy number | M | X'06' [MODCA-5-893] |
| 4 | | | | Reserved; should be zero | M | X'06' [MODCA-5-894] |
| 5 | CODE | **MMCid** | 0–127 | Medium Modification Control identifier | M | X'06' [MODCA-5-895] |

#### MCC Semantics

**Startnum** The number of the first copy of the sheet for this copy subgroup. For the first copy subgroup
this value must be 1. For other copy subgroups, this value must be one greater than the
ending copy number of the preceding copy subgroup, or a X'01' exception condition exists.

**Stopnum** The number of the last copy of the sheet for this copy subgroup. This value must be greater
than or equal to the value specified by **Startnum**, or a X'01' exception condition exists. The
number of copies requested by the copy subgroup, called the copy count, which is defined by
(**Stopnum**–**Startnum**) + 1, must be less than or equal to 255, or a X'02' exception condition
exists. The total number of copies for the copy group, which is the sum of the copy counts for
all copy subgroups, is equal to the value of **Stopnum** in the last copy subgroup.

**MMCid** Identifies a Medium Modification Control (MMC) structured field that specifies the
modifications to be applied to all copies for the copy subgroup. A value of 0 selects an
environment-specific set of default modifications. [MODCA-5-896]

#### MCC Exception Condition Summary

**X'01'** This exception condition exists when:
*   For all copy subgroups other than the first, the starting copy number in a copy subgroup is not 1 greater than the ending copy number in the preceding copy subgroup. [MODCA-5-897]
*   The ending copy number in a copy subgroup is not equal to or greater than the starting copy number in the same copy subgroup. [MODCA-5-898]

**X'02'** The copy count in a copy subgroup is greater than 255. [MODCA-5-899]

### Map Container Data (MCD)

The Map Container Data structured field specifies how a presentation data object that is carried within an
object container is mapped into its object area. [MODCA-5-900]

#### MCD (X'D3AB92') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AB92' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-901]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| | | | | One repeating group in the following format: [MODCA-5-902] | | [MODCA-5-903] |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-904] |
| 2–4 | | Triplets | | Mapping Option triplet | M | X'14' [MODCA-5-905] |

#### MCD Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength**
parameter itself.

**Triplets** Appear in the Map Container Data structured field as follows: [MODCA-5-906]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'04' | | **Mapping Option** [MODCA-5-907]<br>Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MCD structured field are: [MODCA-5-908]<br>**Value Description** [MODCA-5-909]<br>**X'00'** Position [MODCA-5-910]<br>**X'10'** Position and trim [MODCA-5-911]<br>**X'20'** Scale to fit [MODCA-5-912]<br>**X'30'** Center and trim [MODCA-5-913]<br>**X'60'** Scale to fill [MODCA-5-914]<br>**X'70'** UP3i Print Data mapping; valid only for the UP3i Print Data object type [MODCA-5-915]<br>All others Reserved |

**Notes:**
1.  If this structured field is not present in the data stream, the architected default for the mapping option is scale to fit.
2.  A presentation space size is required for a scale-to-fit or scale-to-fill mapping of the object presentation space to the object area. See “Object Type Identifiers” for information on how the presentation space size is specified by various objects. If the presentation space size is not specified by the object, the architected default is the presentation space size of the including page or overlay.
3.  This structured field is not applicable to non-presentation objects. It may be ignored if it appears in the [MODCA-5-916]
    object container for such objects.
4.  The UP3i Print Data mapping is only valid for the UP3i Print Data object type; if any other mapping option is [MODCA-5-917]
    specified for this object type a X'02' exception condition exists. [MODCA-5-918]

#### MCD Exception Condition Summary

**X'01'** The Map Container Data structured field contains more than one repeating group.
**X'02'** The mapping option X'70' is specified for an object type other than UP3i Print Data. [MODCA-5-919]

### Map Coded Font (MCF) Format 2

The Map Coded Font structured field maps a unique coded font resource local ID, which may be embedded
one or more times within an object's data and descriptor, to the identifier of a coded font resource object. This
identifier may be specified in one of the following formats:
*   A coded font Global Resource Identifier (GRID) [MODCA-5-920]
*   A coded font name [MODCA-5-921]
*   A combination of code page name and font character set name [MODCA-5-922]

Additionally, the Map Coded Font structured field specifies a set of resource attributes for the coded font. For a
description of coded fonts, see the *Font Object Content Architecture Reference*. [MODCA-5-923]

#### MCF (X'D3AB8A') Syntax

**Structured Field Introducer**

| SF Length (2B) | ID = X'D3AB8A' | Flags (1B) | Reserved (2B) |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-924]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| | | | | One to 254 repeating groups in the following format: [MODCA-5-925] | | [MODCA-5-926] |
| 0–1 | UBIN | **RGLength** | 3–255 | Total length of this repeating group | M | X'06' [MODCA-5-927] |
| 2–n | | Triplets | | See MCF Semantics for triplet applicability. | M | X'14' [MODCA-5-928] |

#### MCF Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength**
parameter itself. [MODCA-5-929]

**Triplets** Appear in the Map Coded Font structured field as follows: [MODCA-5-930]

| Triplet | Type | Usage |
| :--- | :--- | :--- |
| X'01' | | **Coded Graphic Character Set Global Identifier** [MODCA-5-931]<br>Optional. May occur more than once. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-932] |
| X'02' | | **Fully Qualified Name** [MODCA-5-933]<br>Mandatory. See “Fully Qualified Name Triplet X'02'”. The following **Fully Qualified Name** types are supported: [MODCA-5-934]<br>**Type Description** [MODCA-5-935]<br>**X'07'** Font Family Name [MODCA-5-936]<br>**X'08'** Font Typeface Name [MODCA-5-937]<br>**X'84'** Coded Font GRID Reference [MODCA-5-938]<br>**X'85'** Code Page Name Reference [MODCA-5-939]<br>**X'86'** Font Character Set Name Reference [MODCA-5-940]<br>**X'8E'** Coded Font Name Reference [MODCA-5-941] |
| X'24' | | **Resource Local Identifier** [MODCA-5-942]<br>Mandatory. See “Resource Local Identifier Triplet X'24'”. The only valid type for the MCF structured field is X'05', which specifies a Font Local ID (LID). [MODCA-5-943] |
| X'25' | | **Resource Section Number** [MODCA-5-944]<br>Optional. May occur once. See “Resource Section Number Triplet X'25'”. [MODCA-5-945] |
| X'26' | | **Character Rotation** [MODCA-5-946]<br>Optional. May occur once. See “Character Rotation Triplet X'26'”. [MODCA-5-947] |
| X'50' | | **Encoding Scheme ID** [MODCA-5-948]<br>Optional. May occur once. See “Encoding Scheme ID Triplet X'50'”. The ESidCP parameter specifies the encoding scheme associated with the code page in the referenced font. Additionally, the ESidUD parameter may be specified to indicate the encoding scheme for the user data to be rendered with the referenced font. When the two encoding schemes do not match, the presentation system may need to transform the user data to match the encoding in the code page. Not all presentation systems support such transforms. To see which transforms are supported, consult your product documentation. See Table 19 for the combinations of ESidCP and ESidUD that are valid for the MCF . [MODCA-5-949] |
| X'5D' | | **Font Horizontal Scale Factor** [MODCA-5-950]<br>Optional. May occur once in each repeating group. Specifies the horizontal scale factor for an outline technology coded font. When an outline font is scaled anamorphicly (differently in the horizontal and vertical directions), the graphic characters are stretched or compressed in the horizontal direction relative to the vertical direction by the ratio of font horizontal scale factor divided by the specified vertical font size. If this triplet is omitted, the font horizontal scale factor defaults to the specified vertical font size and the scaling is uniform. A coded font reference may not always specify a vertical font size, such as when the reference does not include a GRID or a Font Descriptor triplet. In that case, if a **Horizontal Scale Factor** triplet is specified on the coded font reference, it is ignored. The vertical font size in the font object is then used to scale an outline technology font in the vertical direction, and the horizontal scale factor in the font object, if supplied, is used for anamorphic scaling. If a horizontal scale factor is not supplied in the font object, scaling is uniform. See “Font Horizontal Scale Factor Triplet X'5D'”. [MODCA-5-951] |
| X'84' | | **Font Resolution and Metric Technology** [MODCA-5-952]<br>Optional. May occur once in each repeating group. Specifies metric information for a raster coded font. See page “Font Resolution and Metric Technology Triplet X'84'”. Note that the presence of this triplet indicates that the MCF references a raster-technology coded font. [MODCA-5-953] |

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
*   **Text Orientation (X'1D') triplet**. See “Text Orientation Triplet X'1D'”. [MODCA-5-954]

**MCF Usage Information**

Only a Map Coded Font structured field can map a resource local ID to a pair of code page/font character set
names.

The names of coded fonts, code pages, and font character sets can be specified in several ways. See the
appropriate interchange set definition, “MO:DCA Interchange Set 1”, for the correct syntax of
these names.

Multiple **Resource Local Identifier (X'24')** triplet values (LIDs) may be mapped to the same font, but the same
**Resource Local Identifier (X'24')** triplet value may not be mapped to more than one font within the same
structured field.

**Double-byte Font References**

The same **Resource Local Identifier (X'24')** triplet value may be mapped to different sections of the same
double-byte font. When this is done, the following rules apply:
*   All repeating groups associated with the double-byte font must be contiguous. [MODCA-5-955]
*   Each repeating group must either default the LID value or contain a **Resource Local Identifier (X'24')** triplet with the same value. [MODCA-5-956]
*   Each repeating group must contain a **Fully Qualified Name** type X'85' (Code Page Name Reference) and **Fully Qualified Name** type X'86' (Font Character Set Name Reference). [MODCA-5-957]
*   When the font uses the EBCDIC Presentation double-byte encoding scheme (encoding scheme ID X'62nn'), each repeating group must contain a **Resource Section Number (X'25')** triplet that specifies a valid double-byte section number in the range X'41' through X'FE'. [MODCA-5-958]
*   Each **Resource Section Number (X'25')** triplet value specified must be unique within the entire set of repeating groups associated with the double-byte font. [MODCA-5-959]
*   A **Character Rotation (X'26')** triplet may be specified in any of the repeating groups associated with the font and need only be specified in one of the repeating groups. However, if specified in more than one of the associated repeating groups, the value of all **Character Rotation (X'26')** triplets must be identical. [MODCA-5-960]
*   A **Encoding Scheme ID (X'50')** triplet may be specified in any of the repeating groups associated with the font and need only be specified in one of the repeating groups. However, if specified in more than one of the associated repeating groups, the value of all **Encoding Scheme ID (X'50')** triplets must be identical. [MODCA-5-961]
*   A **Font Horizontal Scale Factor (X'5D')** triplet may be specified in any of the repeating groups associated with the font and need only be specified in one of the repeating groups. However, if specified in more than one of the associated repeating groups, the value of all **Font Horizontal Scale Factor (X'5D')** triplets must be identical. [MODCA-5-962]
*   A **Font Resolution and Metric Technology (X'84')** triplet may be specified in any of the repeating groups associated with the font and need only be specified in one of the repeating groups. If specified in more than one of the associated repeating groups, the last specified **Font Resolution and Metric Technology (X'84')** triplet is used. [MODCA-5-963]

**Using the X'50' Triplet to Specify Encoding**

If the optional ESidUD parameter is included, the following ESidCP and ESidUD combinations are allowed in
the X'50' triplet when specified in an MCF repeating group:

**Table 19. Valid ESidCP/ESidUD Combinations for the MCF**

| ESidUD | ESidCP |
| :--- | :--- |
| X'7200'—UTF-16, including surrogates; byte order is big endian (UTF-16BE) | X'8200'—Unicode Presentation; byte order is big endian |

**Architecture Note:** The following additional ESidUD/ESidCP combinations are supported in the AFP Line
Data architecture when the X'50' triplet is specified on the MCF in a Page Definition. Note that for the combination ESidUD = X'7200' and ESidCP = X'2100', it is assumed that the user data only uses UTF-16 code points X'0020'–X'007F', since these are the only UTF-16 code points that transform to one-byte ASCII code points. Similarly, for the combination ESidUD = X'7807' and ESidCP = X'2100', it is assumed that the user data only uses UTF-8 code points X'20'–X'7F', since these are the only UTF-8 code points that transform to one-byte ASCII code points. [MODCA-5-964]

| ESidUD | ESidCP |
| :--- | :--- |
| X'7200'—UTF-16, including surrogates; byte order is big endian (UTF-16BE) | X'2100'—PC-Data SBCS (ASCII-based) |
| X'7807'—UTF-8 | X'2100'—PC-Data SBCS (ASCII-based) [MODCA-5-965] |

#### MCF Exception Condition Summary

**X'01'** The exception condition exists when any of the following conditions are encountered in any of the repeating groups: [MODCA-5-966]
*   A **Fully Qualified Name** type X'84' (Coded Font GRID Reference) and a **Fully Qualified Name** of either type X'85' (Code Page Name Reference) or type X'86' (Font Character Set Name Reference). [MODCA-5-967]
*   A **Fully Qualified Name** type X'8E' (Coded Font Name Reference) and a **Fully Qualified Name** of either type X'85' (Code Page Name Reference) or type X'86' (Font Character Set Name Reference). [MODCA-5-968]
*   A **Fully Qualified Name** type X'84' (Coded Font GRID Reference) and a **Fully Qualified Name** type X'8E' (Coded Font Name Reference). [MODCA-5-969]
*   A **Fully Qualified Name** type X'84' (Coded Font GRID Reference) that identifies a font encoded using the EBCDIC Presentation double-byte encoding scheme (encoding scheme ID X'62nn') or the EBCDIC Presentation single-byte encoding scheme (encoding scheme ID X'61nn'), and a **Resource Section Number** with a value other than X'00'. [MODCA-5-970]
*   A **Fully Qualified Name** type X'8E' (Coded Font Name Reference) that identifies a font encoded using the EBCDIC Presentation double-byte encoding scheme (encoding scheme ID X'62nn') or the EBCDIC Presentation single-byte encoding scheme (encoding scheme ID X'61nn'), and a **Resource Section Number** with a value other than X'00'. [MODCA-5-971]
*   Multiple **Fully Qualified Names** of the same type. [MODCA-5-972]
*   Multiple triplets of the same type, except **Fully Qualified Name (X'02')** triplet. [MODCA-5-973]
*   An **Encoding Scheme ID** where either the encoding scheme or the bytes-per-code-point indicator do not match the characteristics of the specified code page. [MODCA-5-974]

The exception condition also exists when any of the following conditions are encountered within the same Map Coded Font structured field: [MODCA-5-975]
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that do not map to the same double-byte font using a **Fully Qualified Name** type X'85' (Code Page Name Reference) and a **Fully Qualified Name** type X'86' (Font Character Set Name Reference). [MODCA-5-976]
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that are not contiguous. [MODCA-5-977]
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that do not each have a valid, unique **Resource Section Number** value. [MODCA-5-978]
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that have different **Character Rotation** values.
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that have different **Encoding Scheme ID** values.
*   The **Resource Local Identifier** value is repeated in two or more repeating groups that have different **Font Horizontal Scale Factor** values.

**X'02'** The exception condition exists when:
*   A **Fully Qualified Name (X'02')** triplet other than a type X'07' (Font Family Name), a type X'08' (Font Typeface Name), type X'84' (Coded Font GRID Reference), type X'85' (Code Page Name Reference), type X'86' (Font Character Set Name Reference), or a type X'8E' (Coded Font Name Reference) appears within any repeating group.
*   A **Resource Local Identifier (X'24')** triplet type other than X'05' appears within any repeating group.

**X'04'** The exception condition exists when any repeating group does not contain one of the
following:
*   A **Fully Qualified Name** type X'84' (Coded Font GRID Reference).
*   A **Fully Qualified Name** type X'85' (Code Page Name Reference) and a **Fully Qualified Name** type X'86' (Font Character Set Name Reference).
*   A **Fully Qualified Name** type X'8E' (Coded Font Name Reference).
### Map Coded Font (MCF)


### Medium Descriptor (MDD)

The Medium Descriptor structured field specifies the size and orientation of the medium presentation space for all sheets that are generated by the medium map that contains the Medium Descriptor structured field. [MODCA-5-979]

#### MDD (X'D3A688') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A688'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-980]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-981]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-982]|
| 0 | CODE | **XmBase** | X'00'–X'01' | Medium unit base for the X axis:<br>X'00' 10 inches<br>X'01' 10 centimeters | M | X'06' [MODCA-5-983]|
| 1 | CODE | **YmBase** | X'00'–X'01' | Medium unit base for the Y axis:<br>X'00' 10 inches<br>X'01' 10 centimeters | M | X'06' [MODCA-5-984]|
| 2–3 | UBIN | **XmUnits** | 1–32,767 | Medium units per unit base for the X axis | M | X'06' [MODCA-5-985]|
| 4–5 | UBIN | **YmUnits** | 1–32,767 | Medium units per unit base for the Y axis | M | X'06' [MODCA-5-986]|
| 6–8 | UBIN | **XmSize** | | 1–32,767 Medium extent for the X axis<br>X'000000' X-axis extent not specified<br>X'FFFFFF' Presentation process default | M | X'06' [MODCA-5-987]|
| 9–11 | UBIN | **YmSize** | | 1–32,767 Medium extent for the Y axis<br>X'000000' Y-axis extent not specified<br>X'FFFFFF' Presentation process default | M | X'06' [MODCA-5-988]|
| 12 | BITS | **MDDFlgs** | | Specify control information for the media. See MDD Semantics for bit definitions. | M | X'06' [MODCA-5-989]|
| 13–n | | **Triplets** | | See MDD Semantics for triplet applicability. | O | X'10' [MODCA-5-990]|

**Architecture Note:** Pre-1989 AFP Data Stream documentation defined a short MDD that ended with the YmUnits parameter at byte offset 4 - 5. To accommodate old AFP applications that generate such MDDs, MO:DCA receivers should tolerate MDDs whose data field ends after this parameter. The total structured field length in that case is X'000E'.

#### MDD Semantics

**XmBase** Specifies the unit base for the X axis of the medium coordinate system.

**YmBase** Specifies the unit base for the Y axis of the medium coordinate system.

**Note:** A X'01' exception condition exists if the XmBase and YmBase values are not identical. [MODCA-5-991]

**XmUnits** Specifies the number of units per unit base for the X axis of the medium coordinate system.

**YmUnits** Specifies the number of units per unit base for the Y axis of the medium coordinate system.

**XmSize** Specifies the extent of the medium presentation space along the X axis. This is also known as the medium's size in the X-direction. A value of X'000000' indicates that the extent along the X axis is not specified and the size in the X-direction of the currently loaded medium, as defined by presentation device sensors or presentation device operator input, is used. A value of X'FFFFFF' indicates that a presentation process default should be used for the X-axis extent.

**YmSize** Specifies the extent of the medium presentation space along the Y axis. This is also known as the medium's size in the Y-direction. A value of X'000000' indicates that the extent along the Y axis is not specified and the size in the Y-direction of the currently loaded medium, as defined by presentation device sensors or presentation device operator input, is used. A value of X'FFFFFF' indicates that a presentation process default should be used for the Y-axis extent.

**MDDFlgs** Specify control information for the media.

| **Bit** | **Description** |
| :--- | :--- |
| 0 | **Medium orientation enablement for cut-sheet printers.** [MODCA-5-992]<br>B'0' Do not pass the medium orientation specified on this structured field to cut-sheet printers; the medium orientation on such printers is always defined to be X'00' (portrait).<br>B'1' Pass the medium orientation specified on this structured field to cut-sheet printers.<br>If this parameter is not specified, the architected default for MDDFlgs bit 0 is B'0' (do not pass the medium orientation to cut-sheet printers). Note that the medium orientation is always passed to continuous-forms printers. It is always passed to cut-sheet printers when N-up presentation is active. Note also that a continuous-forms printer in cut-sheet emulation (CSE) mode is treated as a continuous-forms printer when processing the MDDFlgs parameter. |
| 1–7 | Reserved; all bits must be B'0'. |

**Triplets** Appear in the Medium Descriptor structured field as follows: [MODCA-5-993]

| **Triplet** | **Type** | **Usage** [MODCA-5-994]|
| :--- | :--- | :--- [MODCA-5-995]|
| X'68' | | **Medium Orientation** Optional. May occur once. Specifies the orientation of the medium presentation space on the physical medium. See “Medium Orientation Triplet X'68'”. If this triplet is not specified, the architected default for the medium orientation is X'00' (portrait). [MODCA-5-996]|

**Architecture Note:** In AFP environments, the following retired triplet is used on this structured field:
* MDD Two-up Triplet X'10'; see “Retired Triplets”. [MODCA-5-997]


### Map Data Resource (MDR)

The Map Data Resource structured field specifies resources that are required for presentation. Each resource reference is defined in a repeating group and is identified with a file name, the identifier of a begin structured field for the resource, or any other identifier associated with the resource. The MDR repeating group may additionally specify a local or internal identifier for the resource object. Such a local identifier may be embedded one or more times within an object's data.

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified. That is, the encoding scheme used for the resource include, the resource map, and the resource wrapper should be the same. For TrueType/OpenType fonts, optimal performance can be achieved by using UTF-16BE as the encoding scheme.

#### MDR (X'D3ABC3') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABC3'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-998]

One to 254 repeating groups in the following format: [MODCA-5-1000]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-999]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1001]|
| 0–1 | UBIN | **RGLength** | 14–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1002]|
| 2–n | | **Triplets** | | See MDR Semantics for triplet applicability. | M | X'14' [MODCA-5-1003]|

#### MDR Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Data Resource structured field repeating groups as follows. For examples of the triplet groups that can be specified for various types of MDR repeating groups, see **Figure 58.**

| **Triplet** | **Type** | **Usage** [MODCA-5-1004]|
| :--- | :--- | :--- [MODCA-5-1005]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1006]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. Specifies the reference to the resource object. The GID is used to locate the resource object in the resource hierarchy, which may include the presentation device, and must match the identifier for an object or a X'01' exception condition exists. See “Fully Qualified Name Triplet X'02'”.<br><br>The Fully Qualified Name types that may appear are:<br>* X'84'—Begin Resource Object Reference, which is used to map an IOCA image object. [MODCA-5-1006]<br>* X'CE'—Other Object Data Reference, which is used to map a data object whose format may or may not be defined by an AFP architecture. [MODCA-5-1007]<br>* X'DE'—Data Object External Resource Reference, which is used to map a resource object that is used by a data object. [MODCA-5-1008]<br>* X'EE'—Tertiary Data Object External Resource Reference, which is used to map a tertiary CMR resource object that is used by a secondary image resource object in a QR Code with Image bar code. [MODCA-5-1010]<br>* X'BE'—Data Object Internal Resource Reference. The identifier is used internally by the data object to reference the resource whose external identifier is specified by the FQN type X'DE' triplet. [MODCA-5-1015]<br>* X'85'—Code Page Name Reference. Only used when the MDR references a data-object font with the FQN type X'DE' triplet. [MODCA-5-1026]|
| X'10' | | **Object Classification** Mandatory if the repeating group specifies a Fully Qualified Name type X'CE'—Other Object Data Reference, a Fully Qualified Name type X'DE'—Data Object External Resource Reference, or a Fully Qualified Name type X'EE'—Tertiary Data Object External Resource Reference. [MODCA-5-1029]|
| X'20' | | **Font Coded Graphic Character Set Global Identifier** Optional. May occur once in each repeating group. Only used when the MDR references a data-object font with the FQN type X'DE' triplet. [MODCA-5-1031]|
| X'50' | | **Encoding Scheme ID** Optional. May occur once in each repeating group. Only used when the MDR references a data-object font and the encoding in the user data is different than the encoding in the referenced font. [MODCA-5-1032]|
| X'5A' | | **Object Offset** Optional. If this MDR references a CMR and is specified in the DEG of a Form Map, may occur once with ObjTpe=X'A8' to specify that documents are the objects to be counted. [MODCA-5-1037]|
| X'8B' | | **Data-Object Font Descriptor** Optional. May occur once in each repeating group. Only used when the MDR references a data-object font with the FQN type X'DE' triplet. [MODCA-5-1038]|
| X'8C' | | **Locale Selector** Optional. May occur once. Establishes the creation locale for the resource referenced by the MDR. [MODCA-5-1039]|
| X'91' | | **Color Management Resource Descriptor** Optional. May occur once. Only used when the MDR references a Color Management Resource (CMR) with the FQN type X'DE' or type X'EE' triplet. [MODCA-5-1040]|
| X'FF' | | **Triplet Extender** Optional. May occur more than once in a contiguous sequence. [MODCA-5-1042]|

**Implementation Note:** Not all AFP servers support the inheritance of encoding scheme from higher levels of the document hierarchy, therefore it is recommended that the X'01' triplet be specified directly on the MDR if required by a parameter such as the FQN type X'DE' triplet.

**Architecture Note:** The FQN type X'DE' triplet with FQNFmt = X'10' (OID) is only used to reference the CMYK SWOP and CMYK Euroscale resident color profiles registered in the MO:DCA Registry; see “Resident Color Profile Identifiers”.

When an FQN type X'DE' triplet with FQNFmt X'00' is used to reference a data-object font, the GID is a full font name that uniquely identifies the font. The encoding for this character string is specified by the X'01' triplet, which can be located either in this structured field or in the MO:DCA document hierarchy. See “Using the MDR to Map a TrueType/OpenType Font”.

**Application Note:** When a full font name is specified in a Resource Access Table (RAT), the encoding for the name is UTF-16BE. This encoding is characterized by CCSID 1200 (X'04B0'). A performance benefit may be achieved if the full font name specified on the MDR—which is used to index the RAT—already uses this encoding, thereby eliminating the need for an encoding conversion.

When an FQN type X'DE' or X'EE' triplet with FQNFmt X'00' is used to reference a Color Management Resource (CMR), the GID is a CMR name that matches the name specified in the header of the CMR and that uniquely identifies the CMR. The encoding for this character string is specified by the X'01' triplet, which can be located either in this structured field or in the MO:DCA document hierarchy. If an IOB is used to reference the mapped object, the IOB must specify the same reference, using the same FQNFmt, as the MDR.

**Architecture Note:** The Extended Resource Local Identifier Mandatory (X'22') triplet is mandatory on the MDR in MO:DCA-L data streams and must occur once in each repeating group when the MDR maps a resource with a FQN type X'84'—Begin Resource Object Reference triplet. See “Extended Resource Local Identifier Triplet X'22'”. The only resource type that may be specified in the X'22' triplet is Restype = X'10' - Image resource. Note that within the same MDR structured field, it is not permissible to map the same local ID to more than one image resource or a X'01' exception condition exists. However, two or more repeating groups within the same MDR structured field may be used to map different local IDs to the same image resource. Note that the MO:DCA-L format has been functionally capped and is no longer defined in the MO:DCA reference; for a definition of this format, see MO:DCA-L: The OS/2 PM Metafile (.met) Format. [MODCA-5-1044]

**Application Note:** A non-OCA data object or an IOCA image object that is included on a page or overlay with an IOB, if first mapped with an MDR in the AEG for that page or overlay, is processed as a hard object. In that case the object is sent to the presentation device once as a resource object and can then be presented multiple times using IOBs. If the object is not mapped, it is processed as a soft object and is sent to the presentation device as part of the page or overlay.

#### Using the X'50' Triplet to Specify Encoding

Table 20 shows the ESidCP and ESidUD combinations that are allowed in the X'50' triplet when the MDR references a TrueType/OpenType font with EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001'):

**Table 20. Valid ESidUD/ESidCP Combinations for the MDR**

| **ESidUD** | **ESidCP** |
| :--- | :--- |
| Not specified | X'2100'—PC-Data SBCS (ASCII) |
| Not specified | X'6100'—EBCDIC SBCS |
| Not specified | X'6200'—EBCDIC DBCS |
| X'7807'—UTF-8 | Ignored |

#### Using the MDR to Map a TrueType/OpenType Font

**Font Name**

When the MDR is used to map a data-object font resource that is a TrueType/OpenType font and specifies a FQN type X'DE' triplet with FQNFmt = X'00', the character string that identifies the font must be the full font name specified in a name record in the mandatory Naming Table of the font file. This parameter is specified in a name record with Name ID 4. An example of a full font name is Times New Roman Bold. Two characteristics of the full font name must be taken into account when using it to reference a TrueType/OpenType font: language and encoding.

* **Language.** The full font name may be specified in a number of languages. The language used for a given name record is specified with a language identifier (LCID). For example, English-United States is assigned LCID X'0409' (1033). The language used to specify the full font name in the FQN type X'DE' triplet may be any of the languages specified in a name record for the full font name with the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001'). [MODCA-5-1045]
* **Encoding.** The encoding used to specify the character string in the FQN type X'DE' triplet is defined by a Coded Graphic Character Set Global Identifier (X'01') triplet that precedes the FQN type X'DE' triplet. This triplet may be specified on the MDR or on a structured field that is higher in the document hierarchy than the MDR: for example on the BPG for the page that contains the MDR or on the BDT for the document. See “Coded Graphic Character Set Global Identifier Triplet X'01'” for a definition of the scoping rules for the X'01' triplet. Note that the encoding for the FQN type X'DE' triplet need not match the encoding for the full font name in the font Naming Table. [MODCA-5-1046]

**Implementation Note:** Not all AFP servers support the inheritance of encoding scheme from higher levels of the document hierarchy, therefore it is recommended that this triplet be specified directly on the MDR if required by a parameter such as the FQN type X'DE' triplet. [MODCA-5-1047]

**Font Install Program**

In general, the full font name does not provide sufficient information to find the font resource on a given platform. Additional information such as the file name is normally required to locate the font resource. The mapping from full font name to file name is provided for each platform that requires this by a font install program. This program builds a Resource Access Table (RAT) that must, at minimum, contain the following information:

* The full font name encoded in UTF-16BE. This full font name is specified multiple times in all languages used in the naming table. The UTF-16 encoding matches the encoding defined by EncEnv = Microsoft (X'0003') and EncID = Unicode (X'0001') in the Naming Table. [MODCA-5-1048]
* A mapping of the full font name—in each language—to the name of the file that contains the font. [MODCA-5-1049]
* If the font also has an object OID assigned and can therefore be resident in the printer, the mapping from full font name to font file name also includes the object OID for the font. This allows use of the resident version of the font and avoids a font download. [MODCA-5-1050]
* If the font is contained in a TrueType Collection file (TTC), the full font name must be mapped to the file name of the TTC. [MODCA-5-1051]
* If the font has linked fonts the RAT must link the full font name of the font to the full font names of the linked fonts. When a font has linked fonts, it is referred to as a base font to differentiate it from its linked fonts. Linked fonts are TTFs/OTFs that can be used to extend the character sets in a base font or to add user-defined characters (UDCs) to the base font. [MODCA-5-1052]

The Resource Access Table (RAT) used in AFP environments is defined in “The Resource Access Table (RAT)”.

**TrueType/OpenType Font Resources in a Resource Library**

When TrueType/OpenType fonts are installed in a resource library, they must not be wrapped with a MO:DCA object envelope such as BOC/EOC, that is, they must be installed in their raw source format. This allows the font resources to be used by all system components, particularly those that do not understand MO:DCA object envelopes such as BOC/EOC. Any of the necessary information that such an envelope normally provides, such as an object OID, is associated with the raw font resource by the Resource Access Table (RAT). The font install program must ensure that the TrueType/OpenType font resources are installed in this manner. BOC/EOC object containers for TrueType/OpenType font resources are only supported when such resources are placed into a print file resource group, in which case they are mandatory.

**Architecture Note:** In AFP environments, when a TrueType/OpenType font resource is carried in a BOC/EOC container in an external (print file level) resource group, the container must be wrapped with a BRS/ERS envelope.

**TrueType/OpenType Font Resources in an External (Print file level) Resource Group**

TrueType/OpenType fonts (TTFs/OTFs), TrueType/OpenType fonts that are used as linked fonts, and TrueType/OpenType font collections (TTCs), may be carried in the resource group for a print file. This is called a print file level resource group, and these resources are said to be inline. When presentation servers search for a font that is referenced in the data stream, such a resource group is searched ahead of system level resource libraries, and if an inline font is found it must be used in place of the system level font. To support this hierarchy, presentation servers process a TrueType/OpenType font reference in an MDR for inline resources as follows:

1. The resource group, if present, is searched for a font (TTF/OTF) container or a collection (TTC) container that specifies a matching full font name. [MODCA-5-1054]
   * A font container specifies the full font name using a FQN type X'01' triplet on the Begin Resource (BRS) structured field for the font container. [MODCA-5-1055]
   * A collection container specifies the full font name of a font in the collection using a FQN type X'6E'—Data Object Font Base Font Identifier triplet on the BRS of the collection container. [MODCA-5-1056]
   The first matching font container or collection container is used. If a collection containing the font is found, the complete TTC (if not already in the presentation device) is downloaded to the device, which must be able to index the required font in the collection. The font container or collection container may also specify one or more linked fonts for the referenced font.
   * On a font container, linked fonts for the base font are specified with FQN type X'7E'—Data-object Font Linked Font Identifier triplets, which carry the full font name of the linked fonts, on the BRS of the font container. [MODCA-5-1057]
   * On a collection container, linked fonts are specified with FQN type X'7E' triplets that immediately follow the FQN type X'6E' triplet for the base font on the BRS of the collection container. [MODCA-5-1058]
   The full font names for the linked fonts are used in turn to search the resource group for a font container or a collection container that carries a font that matches the full font name of the linked font. On a font container, the linked font name is matched against the FQN type X'01' triplet on the BRS; on a collection container it is matched against the FQN type X'6E' triplets on the BRS.
   * The first matching font container or collection container is used, and its font is processed as a linked font for the base font. [MODCA-5-1059]
   * If a linked font cannot be found in either an inline font container or an inline collection container, the full font name of the linked font is used to index the RAT to locate the linked font in a resource library. If a specified linked font cannot be found in the resource group or in a resource library, a X'04' exception condition exists. [MODCA-5-1061]
   Only one level of linking is supported. That is, if a linked font specifies its own linked fonts, either with FQN type X'7E' triplets on its inline container or with linked font pointers in the RAT, these “secondary” linked fonts are not processed as linked fonts for the original base font.
2. If a font matching the MDR reference is not found in an inline font container or in an inline collection container, the presentation server accesses the RAT with the full font name to locate the referenced font in a resource library. [MODCA-5-1062]

**Figure 58. Examples of MDR Repeating Groups**

MDR Repeating Group Mapping an IOCA Image in an AEG
* Fully Qualified Name (X'02') triplet, type X'84'—Begin Resource Object Reference [MODCA-5-1064]

MDR Repeating Group Mapping a PDF Object in an AEG
* Fully Qualified Name (X'02') triplet, type X'CE'—Other Object Data Reference [MODCA-5-1065]
* Object Classification (X'10') triplet [MODCA-5-1066]

MDR Repeating Group Mapping a PDF Resource in an AEG
* Fully Qualified Name (X'02') triplet, type X'DE'—Data Object External Resource Reference [MODCA-5-1067]
* Object Classification (X'10') triplet [MODCA-5-1068]

MDR Repeating Group Mapping an IOCA image for use as a secondary resource to a BCOCA QR Code with Image bar code, in the OEG of the bar code
* Fully Qualified Name (X'02') triplet, type X'84'—Begin Resource Object Reference [MODCA-5-1069]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1070]

MDR Repeating Group Mapping a TIFF image for use as a secondary resource to a BCOCA QR Code with Image bar code, in the OEG of the bar code
* Fully Qualified Name (X'02') triplet, type X'CE'—Other Object Data Reference [MODCA-5-1071]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1072]
* Object Classification (X'10') triplet [MODCA-5-1073]

MDR Repeating Group Mapping a CMR for use as a tertiary resource to a TIFF image being used as a secondary resource to a BCOCA QR Code with Image bar code, in the OEG of the bar code
* Fully Qualified Name (X'02') triplet, type X'EE'—Tertiary Data Object External Resource Reference [MODCA-5-1074]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1075]
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1076]
* Object Classification (X'10') triplet [MODCA-5-1077]
* Color Management Resource Descriptor (X'91') triplet [MODCA-5-1078]

MDR Repeating Group Mapping a TrueType/OpenType Font (user encoding = font encoding) in an AEG
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1079]
* Fully Qualified Name (X'02') triplet, type X'DE'—Data Object External Resource Reference [MODCA-5-1080]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1081]
* Object Classification (X'10') triplet [MODCA-5-1082]
* Data-Object Font Descriptor (X'8B') triplet [MODCA-5-1083]

MDR Repeating Group Mapping a TrueType/OpenType Font (user encoding = UTF-8) in an AEG
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1084]
* Fully Qualified Name (X'02') triplet, type X'DE'—Data Object External Resource Reference [MODCA-5-1085]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1086]
* Object Classification (X'10') triplet [MODCA-5-1087]
* Encoding Scheme ID (X'50') triplet [MODCA-5-1088]
* Data-Object Font Descriptor (X'8B') triplet [MODCA-5-1089]

MDR Repeating Group Mapping a TrueType/OpenType Font (user encoding defined by EBCDIC/ASCII code page) in an AEG
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1090]
* Fully Qualified Name (X'02') triplet, type X'DE'—Data Object External Resource Reference [MODCA-5-1091]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1092]
* Object Classification (X'10') triplet [MODCA-5-1093]
* Font Coded Graphic Character Set Global Identifier (X'20') triplet [MODCA-5-1094]
* Encoding Scheme ID (X'50') triplet [MODCA-5-1095]
* Data-Object Font Descriptor (X'8B') triplet [MODCA-5-1096]

#### Using the MDR to Map a Color Management Resource (CMR)

**CMR Name**

When the MDR is used to map a Color Management Resource (CMR) and specifies a FQN type X'DE' or X'EE' triplet with FQNFmt = X'00', the character string that identifies the CMR must be the CMRname specified in the CMR header of the CMR file. The CMR name has a fixed length of 73 characters (146 bytes if encoded in UTF-16BE). The encoding used to specify the character string in the FQN type X'DE' or X'EE' triplet is defined by a Coded Graphic Character Set Global Identifier (X'01') triplet that precedes the FQN type X'DE' or X'EE' triplet. This triplet may be specified on the MDR or on a structured field that is higher in the document hierarchy than the MDR, for example on the BPG for the page that contains the MDR or on the BDT for the document. See the X'01' triplet description for a definition of the scoping rules for the X'01' triplet. Note that the encoding for the FQN type X'DE' or X'EE' triplet need not match the UTF-16BE encoding for the CMR name in the CMR header.

**Implementation Note:** Not all AFP servers support the inheritance of encoding scheme from higher levels of the document hierarchy, therefore it is recommended that this triplet be specified directly on the MDR if required by a parameter such as the FQN type X'DE' or X'EE' triplet.

**Generic CMRs**

Halftone CMRs and Tone Transfer Curve CMRs can be specified in a generic sense and referenced as instruction CMRs to request an intended output appearance. When used in this manner, such CMRs are called generic CMRs. They are identified with a fixed character pattern of “generic” in the version field of the CMR name and with the absence of device-specific fields in the name. The CMR Architecture registers all valid generic CMR names for HT and TTC CMRs. Generic CMRs are never used directly by an output device, they are always replaced by device-specific CMRs that will provide the intended appearance. This replacement is done either by the print server based on processing inline CMRs or the CMR RAT, or by the output device. Generic audit HT and TTC CMRs are ignored by the output device.

Device support for downloaded CC CMRs and generic HT and TTC CMRs is mandatory. Device support for downloaded device-specific HT and TTC CMRs, and for IX CMRs is optional. If an optional CMR is referenced in a print file and is not supported by the output device, the print server recognizes an exception condition. The reporting of this exception condition and the continuation of print file processing are controlled by user-specified fidelity controls.

**Link CMRs**

Link color conversion CMRs provide look-up tables (LUTs) that convert directly from an input color space in the presentation data to the output color space of the presentation device. There are two subtypes of Link color conversion CMRs - Link LK CMRs and Link DL CMRs. Link LK CMRs are generated and processed internally in AFP systems and cannot be referenced in the data stream. Link LK CMRs can be important for presentation device performance, but device support for downloaded Link LK CMRs is optional; devices that do not support this function may generate Link LK CMRs internally. Link DL CMRs carry ICC DeviceLink Profiles. They are similar to Link LK CMRs in that they provide a direct conversion from an input color space to the output color space of the presentation device. However Link DL CMRs are exposed to the AFP application and the job submitter and are referenced in the data stream.

**CMR Install Program**

In general, the CMR name does not provide sufficient information to find the CMR on a given platform. Additional information such as the file name is normally required to locate the CMR. The mapping from CMR name to file name is provided for each platform that requires this by a CMR install program. This program builds a CMR Resource Access Table (RAT) entry that must, at minimum, contain the following information:

* The CMR name encoded in UTF-16BE. [MODCA-5-1097]
* A mapping of the CMR name to the name of the file that contains the CMR. [MODCA-5-1098]
* A mapping of the CMR name to the object OID for the CMR. [MODCA-5-1099]
* Optionally, mappings to other CMRs. [MODCA-5-1100]

**CMRs in a Resource Library**

When CMRs are installed in a resource library, they must not be wrapped with a MO:DCA object envelope such as BOC/EOC, that is, they must be installed in their raw source format. This allows the CMRs to be used by system components that do not understand MO:DCA object envelopes. Any of the necessary information that such an envelope normally provides, such as an object OID, is associated with the CMR by the CMR Resource Access Table (RAT). The install program must ensure that the CMRs are installed in this manner. When a presentation server accesses the CMR RAT with a CMR reference from an MDR, which can only occur after the print file level resource group has been accessed unsuccessfully with that CMR reference, the following algorithm is used.

The print server accesses the RAT entry with the CMR name, the processing mode—audit or instruction—and the device type and model of the target output device, and processes the CMR RAT entry as follows.

**Table 21. Print Server CMR Processing: CMRs in Resource Libraries**

| **CMR type** | **Processing mode** | **Device-specific or generic** | **Processing** |
| :--- | :--- | :--- | :--- |
| Color conversion | Audit or instruction | Device-specific | The referenced CMR is downloaded, if necessary, and activated. If the target device supports downloaded Link LK CMRs, all Link LK CMRs that are mapped to the referenced CMR and that match the target device type and model are downloaded, if necessary, and activated. All other mapped CMRs are ignored. |
| Halftone | Audit | Device-specific | If the target device supports downloaded HT CMRs, the referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Audit | Generic | The referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Instruction | Device-specific | If the target device supports downloaded HT CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Halftone | Instruction | Generic | If the target device supports downloaded HT CMRs, all mapped device-specific HT CMRs that match the device type and model of the target output device are downloaded, if necessary, and activated. These CMRs replace the referenced generic CMR. Otherwise, the generic CMR is downloaded, if necessary, activated, and replaced by the output device with a device-specific HT CMR. |
| Tone transfer curve | Audit | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Audit | Generic | The referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Tone transfer curve | Instruction | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Instruction | Generic | If the target device supports downloaded TTC CMRs, all mapped device-specific TTC CMRs that match the device type and model of the target output device are downloaded, if necessary, and activated. These CMRs replace the referenced generic CMR. Otherwise, the generic CMR is downloaded, if necessary, activated, and replaced by the output device with a device-specific TTC CMR. |
| Indexed | Audit | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Indexed | Instruction | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Link DL | Link | Device-specific | If the target device supports downloaded Link DL CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |

**CMRs in an External (Print file level) Resource Group**

CMRs may also be carried in the resource group for a print file, in which case they are called inline CMRs. The CMR must first be wrapped in a BOC/EOC object container, which in turn must be wrapped in a BRS/ERS resource envelope. The BRS specifies the CMR name, encoded in UTF-16BE, with a FQN type X'01' triplet. If the CMR in the container is a Color Conversion (CC) CMR, the BRS may also specify the names of Link LK CMRs, also encoded in UTF-16BE, that are mapped to the CMR using FQN type X'41' - Color Management Resource (CMR) Reference triplets. [MODCA-5-1101]

If a match is found, the inline CMR is processed as follows.

**Table 22. Print Server CMR Processing: Inline CMRs**

| **CMR type** | **Processing mode** | **Device-specific or generic** | **Processing** |
| :--- | :--- | :--- | :--- |
| Color conversion | Audit or instruction | Device-specific | The inline CMR is downloaded, if necessary, and activated. If the target device supports downloaded Link LK CMRs, all Link LK CMRs that are mapped to the referenced CMR with a FQN type X'41' triplet on the BRS and that match the target device type and model are downloaded, if necessary, and activated. All other mapped CMRs are ignored. |
| Halftone | Audit | Device-specific | If the target device supports downloaded HT CMRs, the inline CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Audit | Generic | The inline CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Halftone | Instruction | Device-specific | If the target device supports downloaded HT CMRs, the inline CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Halftone | Instruction | Generic | If the target device supports downloaded HT CMRs, and if the BRS references device-specific HT CMRs that match the device type and model of the target device, these CMRs are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the search continues with the CMR RAT. [MODCA-5-1102] |
| Tone transfer curve | Audit | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Audit | Generic | The referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Tone transfer curve | Instruction | Device-specific | If the target device supports downloaded TTC CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Tone transfer curve | Instruction | Generic | If the target device supports downloaded TTC CMRs, and if the BRS references device-specific TTC CMRs that match the device type and model of the target device, these CMRs are downloaded, if necessary, and activated. These CMRs replace the inline generic CMR. Otherwise, the search continues with the CMR RAT. |
| Indexed | Audit | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR can be downloaded and activated, but the target device ignores it. All mapped CMRs are also ignored. |
| Indexed | Instruction | Device-specific | If the target device supports downloaded IX CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |
| Link DL | Link | Device-specific | If the target device supports downloaded Link DL CMRs, the referenced CMR is downloaded, if necessary, and activated. All mapped CMRs are ignored. |

**Figure 59. Examples of MDR Repeating Groups**

MDR Repeating Group Mapping a CMR
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1103]
* Object Classification (X'10') triplet [MODCA-5-1104]
* Fully Qualified Name (X'02') triplet, type X'DE'—Data Object External Resource Reference [MODCA-5-1105]
* Color Management Resource Descriptor (X'91') triplet [MODCA-5-1106]

MDR Repeating Group Mapping a Tertiary CMR
* Coded Graphic Character Set Global Identifier (X'01') triplet [MODCA-5-1107]
* Object Classification (X'10') triplet [MODCA-5-1108]
* Fully Qualified Name (X'02') triplet, type X'EE'—Tertiary Data Object External Resource Reference [MODCA-5-1109]
* Fully Qualified Name (X'02') triplet, type X'BE'—Data Object Internal Resource Reference [MODCA-5-1110]
* Color Management Resource Descriptor (X'91') triplet [MODCA-5-1111]

#### Using the MDR to Map a Data Object Resource

Data Objects can also be installed with an install program and processed by the print server using a Resource Access Table (RAT), which in this case is called the Data Object RAT. A significant advantage of installing and processing with a RAT is that the data object reference in the data stream is not subject to any platform-specific file system naming conventions. That is, the object can be referenced using an unrestricted, natural, platform-independent name, and the RAT entry is then used to map this name to a platform-specific file name. Data objects can also be installed in any number of traditional methods and processed without a RAT. In general, if a data object reference on an MDR, PPO, or IOB is processed against a resource library that contains a Data Object RAT, the reference is first processed against the RAT.

If a data object is referenced using its natural name and not a file name, additional information is required to locate and process the object on a given platform. This information is provided in the Data Object RAT entry built by the install program when the object is installed. This entry must, at minimum, contain the following information:

* The object name encoded in UTF-16BE. [MODCA-5-1112]
* A mapping of the data object name to the name of the file that contains the object. [MODCA-5-1113]
* A mapping of the data object name to the object OID for the object. [MODCA-5-1114]
* Optionally, mappings to CMRs that are to be associated with the data object. [MODCA-5-1115]

When non-OCA data objects, such as EPS, PDF, GIF, TIFF, JFIF are installed in a resource library, they are not wrapped with a MO:DCA BOC/EOC envelope, that is, they are installed in their raw source format. [MODCA-5-1116]

#### MDR Exception Condition Summary

**X'01'** This exception condition exists when:
* A resource with the same identifier as that specified on the type X'84' (Begin Resource Object Reference) Fully Qualified Name triplet, or on the type X'CE' (Other Object Data Reference) Fully Qualified Name triplet, or on the type X'DE' (Data Object External Resource Reference) Fully Qualified Name triplet, or on the type X'EE' (Tertiary Data Object External Resource Reference) Fully Qualified Name triplet cannot be located. [MODCA-5-1117]
* The same repeating group contains an invalid number or combination of Fully Qualified Name triplets. [MODCA-5-1118]
* The same Resource LID is mapped to more than one resource object of the same type within the same structured field. [MODCA-5-1119]

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'84' (Begin Resource Object Reference), a type X'85' (Code Page Name Reference), a type X'CE' (Other Object Data Reference), a type X'DE' (Data Object External Resource Reference), a type X'EE' (Tertiary Data Object External Resource Reference), or a type X'BE' (Data Object Internal Resource Reference) appears within any repeating group. [MODCA-5-1120]
* The same resource reference is specified in more than one repeating group. [MODCA-5-1121]
### Medium Finishing Control (MFC)

The Medium Finishing Control structured field specifies the finishing requirements for physical media. Finishing can be specified for a media collection at the print file level or at the document level by placing the MFC in the document environment group (DEG) of the form map. Finishing can be specified for a media collection at the medium map level by placing the MFC in a medium map. Finishing can be specified for individual media, or sheets, at the medium map level by placing the MFC in a medium map.

* When the MFC is specified in the document environment group (DEG) of the form map, its scope is specified to be one of the following: [MODCA-5-1122]
  - The complete print file
  - Each individual document in the print file
  - A selected document in the print file

If the scope is the print file, the MFC defines print file level finishing, and all media in the print file are collected for finishing in a print file level media collection. The specified finishing operations are applied to the complete collection, that is, the complete print file. Note that the print file level media collection excludes other material that may accompany the print file, such as header pages, trailer pages, and message pages. Such material can be generated as a separate print file. Therefore, it may be collected in a separate print file level media collection and processed with separate finishing operations.

If the scope is each individual document in the print file, the MFC defines document level finishing, and all media in each document are collected for finishing in a document level media collection. The specified finishing operations are applied to each collection, that is each document, individually. Note that, in this case, the same finishing operations are applied to each document.

If the scope is a selected document in the print file, the MFC defines document level finishing, and all media in the selected document are collected for finishing in a document level media collection. The specified finishing operations are applied to this single collection. If the same document is selected multiple times, finishing operations are applied in the order specified. Note that, using this type of MFC, unique finishing operations may be specified for each document in the print file.

A single print file level MFC, a single document level MFC for all documents, or multiple document level MFCs for single documents can be specified in the DEG. If a print file level MFC and document level MFCs are specified in the same DEG, document level finishing is applied to the selected documents, and print file level finishing is applied to the complete print file.

If a document is selected for finishing using an MFC whose scope is each document in the print file, and if it is also selected by one or more MFCs whose scope is a single document, the finishing operations that apply to each document in the print file are applied before the finishing operations that apply to a single document.

* When the MFC is specified in a medium map, its scope is specified to be one of the following: [MODCA-5-1123]
  - Each medium, or sheet, generated by the medium map. When the scope is each medium in the medium map, the MFC defines medium map level sheet finishing, and the specified finishing operations are applied to each medium, not to the media collection.
  - The collection of media, or the collection of sheets generated by the medium map. In this case the MFC defines medium map level group finishing, and all media generated by the medium map are collected for finishing in a medium map level sheet finishing. The specified finishing operations are applied to this single collection.

When an MFC is specified both in a medium map and in the DEG, both sets of finishing operations are applied according to their scope, as long as the operations are compatible. For rules on how finishing operations are nested, see “Finishing Operation Nesting Rules”. Note that not all combinations of finishing operations are compatible. Compatible combinations of finishing operations are presentation-device specific. [MODCA-5-1124]

#### MFC (X'D3A088') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A088'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1125]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1126]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1127]|
| 0 | BITS | **MFCFlgs** | | See MFC Semantics for the MFCFlgs parameter bit definitions. | M | X'06' [MODCA-5-1128]|
| 1 | | **Reserved** | | Reserved; should be zero | M | X'06' [MODCA-5-1129]|
| 2 | CODE | **MedColl** | X'00'–X'02' | Boundary conditions for medium-map level sheet collection:<br>X'00' No sheet collection processed at the medium map level<br>X'01' Begin medium map level sheet collection<br>X'02' Continue medium map level sheet collection | M | X'06' [MODCA-5-1130]|
| 3 | CODE | **MFCScpe** | X'01'–X'05' | MFC Scope:<br>X'01' Print file level MFC<br>X'02' Document level MFC, all documents<br>X'03' Document level MFC, selected document<br>X'04' Medium map level MFC, each medium or sheet<br>X'05' Medium map level MFC, collection of media or sheets | M | X'06' [MODCA-5-1131]|
| 4–n | | **Triplets** | | See MFC Semantics for triplet applicability. | M | X'14' [MODCA-5-1132]|

#### MFC Semantics

**MFCFlgs** The following flags are defined:

| **Bit** | **Description** |
| :--- | :--- |
| 0 | **Activate Medium Finishing Control** [MODCA-5-1133]<br>B'0' Process this structured field as a NoOp.<br>B'1' Process this structured field as specified. |
| 1–7 | Reserved; all bits must be B'0'. |

**MedColl** is a parameter that defines the boundary conditions for the media collection generated by this medium map. This parameter is only processed if MFCScpe = X'05'—medium map level MFC, collection of sheets. It is ignored in all other cases.

| **Value** | **Scope** |
| :--- | :--- |
| X'00' | No sheet collection is to be processed at the medium map level. This value should be specified when MFCScpe is set to values other than X'05'—medium map level MFC, collection of sheets. If this value is specified when MFCScpe is set to X'05', a X'01' exception condition exists. |
| X'01' | **Begin medium map level sheet collection.**<br>This causes a sheet eject to be generated and starts a medium map level sheet collection for the finishing operation specified on this MFC. Note that if a collection for this same finishing operation is already in progress from a previous medium map, that collection is terminated and the specified finishing operation is applied. The sheet collection that is started by this MFC continues until:<br>1. A medium map is invoked that does not contain an MFC with MFCScpe= [MODCA-5-1134] X'05' and MedColl = X'02' (Continue) for this same operation.<br>2. A medium map level finishing operation with MFCScpe = X'05' that is [MODCA-5-1135] nested outside this operation is applied.<br>3. End of document is reached. [MODCA-5-1136]<br>When this sheet collection is terminated for any of the above reasons, the specified finishing operation is applied to the collection, and a sheet eject is generated. Note that when the collection is terminated due to end of document, the finishing operation is applied even if the last sheet generated is not the last sheet in the collection, that is, it is not the sheet that normally terminates the collection. |
| X'02' | **Continue medium map level sheet collection.**<br>This causes all sheets generated by this medium map to be included in the collection of sheets started by a previous MFC with MedColl = X'01' (Begin) for this same operation. |

**MFCScpe** Specifies the scope of the Medium Finishing Control.

| **Value** | **Scope** |
| :--- | :--- |
| X'01' | **Print file level MFC.** Specifies that finishing applies to the entire print file. [MODCA-5-1137] |
| X'02' | **Document level MFC, all documents.** Specifies that finishing applies to each individual document in the print file. [MODCA-5-1138] |
| X'03' | **Document level MFC, selected document.** Specifies that finishing applies to a single document in the print file. The document is selected using the Object Offset (X'5A') triplet. [MODCA-5-1139] |
| X'04' | **Medium map level MFC, each medium or sheet.** Specifies that finishing applies to each individual sheet generated by the current medium map. [MODCA-5-1140] |
| X'05' | **Medium map level MFC, collection of media or sheets.** Specifies that finishing applies to the collection of sheets generated by the current medium map. [MODCA-5-1141] |

**Triplets** Appear in the Medium Finishing Control structured field as follows: [MODCA-5-1142]

| **Triplet** | **Type** | **Usage** [MODCA-5-1143]|
| :--- | :--- | :--- [MODCA-5-1144]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1145]|
| X'5A' | | **Object Offset** Optional if MFCScpe = X'03', ignored in all other cases. Specifies how many documents in the print file precede the document to be selected for finishing. If this triplet is not specified when MFCScpe = X'03', the first document in the print file is selected. The offset is measured from the beginning of the print file, so that the first document has offset 0, the second document has offset 1, and the nth document has offset (n-1). See “Object Offset Triplet X'5A'”. [MODCA-5-1146]|
| X'85' | | **Finishing Operation** One occurrence of either this triplet or the UP3i Finishing Operation (X'8E') triplet is mandatory. May occur more than once. Specifies finishing operations to be applied to collected media. If this triplet is specified more than once, finishing operations are applied in the order in which the triplets are specified. See “Finishing Operation Triplet X'85'”. Multiple identical X'85' triplets are ignored. For rules on how finishing operations are nested, see “Finishing Operation Nesting Rules”. [MODCA-5-1147]|

The following finishing operations may be specified when the X'85' triplet is specified on the MFC in a DEG:

| **Value** | **Operation** |
| :--- | :--- |
| X'01' | Corner Staple [MODCA-5-1148] |
| X'02' | Saddle Stitch Out [MODCA-5-1149] |
| X'03' | Edge Stitch [MODCA-5-1150] |
| X'04' | Fold In [MODCA-5-1151] |
| X'08' | Center Fold In [MODCA-5-1152] |
| X'09' | Trim after center fold or saddle stitch [MODCA-5-1153] |
| X'0A' | Punch [MODCA-5-1154] |
| X'0C' | Perfect bind [MODCA-5-1155] |
| X'0D' | Ring bind [MODCA-5-1156] |
| X'0E' | C-fold In [MODCA-5-1157] |
| X'0F' | Accordion Fold In [MODCA-5-1158] |
| X'12' | Saddle Stitch In [MODCA-5-1159] |
| X'14' | Fold Out [MODCA-5-1160] |
| X'18' | Center Fold Out [MODCA-5-1161] |
| X'19' | Trim [MODCA-5-1162] |
| X'1E' | C-fold Out [MODCA-5-1163] |
| X'1F' | Accordion Fold Out [MODCA-5-1164] |
| X'22' | Single Gate Fold In [MODCA-5-1165] |
| X'32' | Single Gate Fold Out [MODCA-5-1166] |

The following finishing operations may be specified when the X'85' triplet is specified on the MFC in a medium map with MFCScpe = X'04':

| **Value** | **Operation** |
| :--- | :--- |
| X'04' | Fold In [MODCA-5-1167] |
| X'05' | Separation Cut [MODCA-5-1168] |
| X'06' | Perforation Cut [MODCA-5-1169] |
| X'07' | Z-fold [MODCA-5-1170] |
| X'08' | Center Fold In [MODCA-5-1171] |
| X'0A' | Punch [MODCA-5-1172] |
| X'0E' | C-fold In [MODCA-5-1173] |
| X'0F' | Accordion Fold In [MODCA-5-1174] |
| X'14' | Fold Out [MODCA-5-1175] |
| X'18' | Center Fold Out [MODCA-5-1176] |
| X'19' | Trim [MODCA-5-1177] |
| X'1E' | C-fold Out [MODCA-5-1178] |
| X'1F' | Accordion Fold Out [MODCA-5-1181] |
| X'20' | Double Parallel Fold In [MODCA-5-1182] |
| X'21' | Double Gate Fold In [MODCA-5-1183] |
| X'22' | Single Gate Fold In [MODCA-5-1184] |
| X'30' | Double Parallel Fold Out [MODCA-5-1185] |
| X'31' | Double Gate Fold Out [MODCA-5-1186] |
| X'32' | Single Gate Fold Out [MODCA-5-1187] |

The following finishing operations may be specified when the X'85' triplet is specified on the MFC in a medium map with MFCScpe = X'05':

| **Value** | **Operation** |
| :--- | :--- |
| X'01' | Corner Staple [MODCA-5-1188] |
| X'02' | Saddle Stitch Out [MODCA-5-1189] |
| X'03' | Edge Stitch [MODCA-5-1190] |
| X'04' | Fold In [MODCA-5-1191] |
| X'05' | Separation Cut [MODCA-5-1192] |
| X'06' | Perforation Cut [MODCA-5-1193] |
| X'08' | Center Fold In [MODCA-5-1194] |
| X'09' | Trim after center fold or saddle stitch [MODCA-5-1195] |
| X'0A' | Punch [MODCA-5-1196] |
| X'0C' | Perfect bind [MODCA-5-1197] |
| X'0D' | Ring bind [MODCA-5-1198] |
| X'0E' | C-fold In [MODCA-5-1199] |
| X'0F' | Accordion Fold In [MODCA-5-1200] |
| X'12' | Saddle Stitch In [MODCA-5-1201] |
| X'14' | Fold Out [MODCA-5-1202] |
| X'18' | Center Fold Out [MODCA-5-1203] |
| X'19' | Trim [MODCA-5-1204] |
| X'1E' | C-fold Out [MODCA-5-1205] |
| X'1F' | Accordion Fold Out [MODCA-5-1206] |
| X'22' | Single Gate Fold In [MODCA-5-1207] |
| X'32' | Single Gate Fold Out [MODCA-5-1208] |

| **Triplet** | **Type** | **Usage** |
| :--- | :--- | :--- |
| X'8E' | | **UP3i Finishing Operation** One occurrence of either this triplet or the Finishing Operation (X'85') triplet is mandatory. May occur more than once. Specifies finishing operations to be applied to collected media. If this triplet is specified more than once, finishing operations are applied in the order in which the triplets are specified. See the UP3i Finishing Operation triplet description. Multiple identical X'8E' triplets are ignored. See “UP3i Finishing Operation Triplet X'8E'”. For rules on how finishing operations are nested, see “Finishing Operation Nesting Rules”. The UP3i Finishing Operation triplet can be specified on the MFC either in a DEG or in a medium map with all architected values for the MFCScpe parameter. There is no architected restriction on which UP3i finishing operations may be specified with MFCScpe = X'04' or MFCScpe = X'05'. However, the UP3i Specification as well as UP3i equipment may limit the scope of UP3i finishing operations; for further information consult the current UP3i Specification. This specification is available at: www.afpcinc.org. [MODCA-5-1209] |

**Finishing Operation Nesting Rules**

When more than one finishing operation that involves a collection of media is specified for some portion of the print file, a nesting of the operations is defined first by the scope of the operation (print file, document, medium), and second by the order of the operation in the data stream. Finishing operations with an inherently broader scope, e.g. operations at the print file level, are nested outside of finishing operations with an inherently narrower scope, for example, operations at the medium map level. [MODCA-5-1210]

If more than one operation is specified with the same scope, for example, if two operations are specified at the medium map level, the order of the Finishing Operation (X'85') triplets and of the UP3i Finishing Operation (X'8E') triplets (whether specified on the same MFC or on different MFCs) defines the order of the nesting. In that case, the first finishing operation specified defines the outermost nesting, and the last finishing operation specified defines the innermost nesting.

The following defines how finishing operations are nested starting with the outermost nesting and ending with the innermost nesting.
1. Printfile level finishing (outermost level), MFCScpe = X'01'
2. Document level finishing: each document in the print file, MFCScpe = X'02'
3. Document level finishing: a selected document in the print file, MFCScpe = X'03'
4. Medium map level finishing: collection of sheets (innermost level), MFCScpe = X'05'.

Nesting may in turn affect the scope of a finishing operation. When a finishing operation is applied, all finishing operations nested inside this operation are also applied. Finishing operations that are nested outside this operation are not affected. Note that nesting does not apply to medium map level sheet finishing (MFCScpe = X'04'). Such finishing is applied to individual sheets and does not involve starting, continuing, and ending a collection of sheets. Each medium map that is to generate such finishing must specify the operation explicitly.

**Implementation Notes:**

1. AFP Environments limit the number of finishing operations that can be nested at the medium map level to sixteen. This limit does not apply to nesting at the document or print file level. For example, if two finishing operations are nested at the medium map level, and these operations are nested within one finishing operation at the document level, which in turn is nested within one finishing operation at the print file level, the level of nesting counted against the AFP nesting limit is two. [MODCA-5-1211]
2. In AFP environments, the nesting of identical finishing operations at the medium map level is not supported. Two finishing operations are considered identical if they are specified by the same triplet (either the Finishing Operation (X'85') triplet or the UP3i Finishing Operation (X'8E') triplet), and the triplet contents are identical. [MODCA-5-1212]

**Architecture Notes:**

1. For some printers, the offset stacking function (X'D1nn' keyword on the MMC structured field), when invoked inside a document or print file, cannot be combined with a finishing operation. In this case, the offset stacking request is ignored and the finishing operation is performed. [MODCA-5-1213]
2. Finishing operations may be applied to print files that contain a mixture of MO:DCA documents and non-MO:DCA data. The following rules specify how the scope of the finishing operations applies to a print file that contains line-data and mixed-data documents, with or without BDT/EDT, as well as composed documents. For more information on line data and mixed data, see the Advanced Function Presentation: Programming Guide and Line Data Reference. [MODCA-5-1214]
   * If the MFC specifies print file level finishing, all media in the print file is collected for finishing in a print file level media collection, and the finishing operations are applied to the complete collection, that is, the complete print file. [MODCA-5-1215]
   * If the MFC specifies document level finishing and selects all documents, the print file is processed as a set of documents as follows: [MODCA-5-1216]
     - Any document bounded by BDT/EDT is processed as a single document regardless of whether the data between BDT/EDT is line data, mixed data, or composed data. [MODCA-5-1217]
     - Line data and mixed data that is not bounded explicitly by BDT/EDT is processed as an implied document with implied BDT/EDT. When such data follows the resource group or an EDT, a BDT is implied, and the implied document lasts until a BDT is encountered or until the end of the print file is reached. In either case, the implied document is terminated with an implied EDT.
     The media in each document, whether implied or explicit, is collected for finishing in a document level media collection, and the finishing operations are applied to each collection, that is each document, individually.
   * If the MFC specifies document level finishing and selects a single document, the print file is processed as a set of documents in the same manner as when all documents are selected. The offset of the selected document is calculated by counting all documents, whether implied or explicit, and the selected document may itself be an implied document. The media in the selected document are collected for finishing, and the finishing operations are applied to the single collection, that is the single document. [MODCA-5-1218] [MODCA-5-1219]

#### MFC Exception Condition Summary

**X'01'** This exception condition exists when:
* The FOpCnt parameter in a Finishing Operation (X'85') triplet is non-zero but does not match the specified number of OpPos parameters. [MODCA-5-1220]
* The MedColl parameter is X'00' and the MFCScpe parameter is X'05'. [MODCA-5-1221]


### Map Graphics Object (MGO)


The Map Graphics Object structured field specifies how a graphics data object is mapped into its object area. [MODCA-5-1222]

#### MGO (X'D3ABBB') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABBB'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1223]

One repeating group in the following format: [MODCA-5-1225]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1224]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1226]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1227]|

#### MGO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Graphics Object structured field as follows: [MODCA-5-1228]

| **Triplet** | **Type** | **Usage** [MODCA-5-1229]|
| :--- | :--- | :--- [MODCA-5-1230]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MGO structured field are:<br>Value Description [MODCA-5-1231]<br>X'10' Position and trim [MODCA-5-1232]<br>X'20' Scale to fit [MODCA-5-1233]<br>X'30' Center and trim [MODCA-5-1234]<br>X'50' Retired mapping option; see “Retired Parameters”. [MODCA-5-1235]<br>X'60' Scale to fill<br>All others Reserved [MODCA-5-1236]|

**Note:** If this structured field is not present in the data stream, the architected default is scale to fit. [MODCA-5-1237]

#### MGO Exception Condition Summary

**X'01'** The Map Graphics Object structured field contains more than one repeating group.

**X'02'** A Mapping Option (X'04') triplet value of X'00' is specified. [MODCA-5-1238]


### Map Image Object (MIO)

The Map Image Object structured field specifies how an image data object is mapped into its object area. [MODCA-5-1239]

#### MIO (X'D3ABFB') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABFB'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1240]

One repeating group in the following format: [MODCA-5-1242]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1241]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1243]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1244]|

#### MIO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Image Object structured field as follows: [MODCA-5-1245]

| **Triplet** | **Type** | **Usage** [MODCA-5-1246]|
| :--- | :--- | :--- [MODCA-5-1247]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MIO structured field are:<br>Value Description [MODCA-5-1248]<br>X'10' Position and trim [MODCA-5-1249]<br>X'20' Scale to fit [MODCA-5-1250]<br>X'30' Center and trim [MODCA-5-1251]<br>X'41' Migration mapping option: Image point-to-pel. See “Coexistence Triplets” for a description. [MODCA-5-1252]<br>X'42' Migration mapping option: Image point-to-pel with double dot. See “Coexistence Triplets” for a description. [MODCA-5-1253]<br>X'50' Migration mapping option: Replicate and trim. See “Coexistence Triplets” for a description. [MODCA-5-1254]<br>X'60' Scale to fill<br>All others Reserved [MODCA-5-1255]|

**Note:** If this structured field is not present in the data stream, the architected default is scale to fit. [MODCA-5-1256]

#### MIO Exception Condition Summary

**X'01'** The Map Image Object structured field contains more than one repeating group.

**X'02'** A Mapping Option (X'04') triplet value of X'00' is specified. [MODCA-5-1257]


### Medium Modification Control (MMC)

The Medium Modification Control structured field specifies the medium modifications to be applied for a copy subgroup specified in the Medium Copy Count (MCC) structured field. [MODCA-5-1258]

#### MMC (X'D3A788') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A788'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1259]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1260]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1261]|
| 0 | CODE | **MMCid** | 1–127 | Medium Modification Control identifier | M | X'06' [MODCA-5-1262]|
| 1 | CODE | | X'FF' | Constant data | M | X'06' [MODCA-5-1263]|
| 2–n | CODE | | | Zero or more keywords in ascending order, in the format shown in the following table. When keywords occur in pairs, the ordering applies to the first keyword. | | [MODCA-5-1264]|

**MMC Keywords**

| **Keyword ID** | **Parameter Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :---: | :---: |
| X'0E' | X'01'–X'20', X'FF' | Horizontal print adjustment; retired for the IBM 3800 printer | O | X'02' |
| X'90' | X'01'–X'FF' | Media destination selector—high. **Note:** X'00' is not valid with keyword X'9100' | O | X'02' |
| X'91' | X'01'–X'FF' | Media destination selector—low. **Note:** X'00' is not valid with keyword X'9000' | O | X'02' |
| X'A0' | X'00'–X'FE', X'FF' | Fixed medium information: a local identifier for the particular fixed medium information selected. X'FF' selects all supported. | O | X'02' |
| X'A1' | X'00' | Fixed perforation cut. Apply a perforation cut at a fixed location on the physical medium. | O | X'02' |
| X'A2' | X'00' | Fixed separation cut. Apply a separation cut at a fixed location on the physical medium. | O | X'02' |
| X'B4' | X'00'–X'FF' | Presentation subsystem set-up ID: high-order byte | O | X'00' |
| X'B5' | X'00'–X'FF' | Presentation subsystem set-up ID: low-order byte | O | X'00' |
| X'D1' | X'00'–X'01' | Offset stack/edge mark change:<br>X'00' No offset stack or edge mark change<br>X'01' Apply offset stack or edge mark change | O | X'02' |
| X'D2' | X'01'–X'7F' | Medium Preprinted Form Overlay (M-PFO) local ID | O | X'02' |
| X'E0' | X'01'–X'02' | Media source selection format:<br>X'01' Media source selector in Format 1<br>X'02' Media source selector in Format 2 | O | X'02' |
| X'E1' | X'01'–X'04', X'41', X'64', X'01'–X'FF' | Media source selector: Format 1 (X'01'–X'04' source ID, X'41' envelope, X'64' manual), Format 2 (X'01'–X'FF') | O | X'02' |
| X'E8' | X'00'–X'FF' | Media type local ID: high-order byte | O | X'02' |
| X'E9' | X'00'–X'FF' | Media type local ID: low-order byte | O | X'02' |
| X'F1' | X'00'–X'01' | Forms flash; retired for the IBM 3800 printer | O | X'02' |
| X'F2' | X'01'–X'7F' | Medium overlay local identifier | O | X'02' |
| X'F3' | X'01'–X'7F' | Text suppression local identifier | O | X'02' |
| X'F4' | X'01'–X'03' | Duplex control:<br>X'01' Simplex<br>X'02' Normal duplex<br>X'03' Tumble duplex | O | X'02' |
| X'F8' | X'01'–X'FE', X'FF' | Print quality control:<br>X'01' Lowest quality level<br>X'FE' Highest quality level<br>X'FF' Printer default | O | X'02' |
| X'F9' | X'00'–X'01' | Constant forms control:<br>X'00' Inactive<br>X'01' Active | O | X'02' |
| X'FC' | X'01'–X'04' | N-up format control:<br>X'01' 1-up format<br>X'02' 2-up format<br>X'03' 3-up format<br>X'04' 4-up format | O | X'02' |

#### MMC Semantics

**MMCid** Medium Modification Control Identifier. The identifier for the modifications specified by this structured field. This identifier is specified in a repeating group in the Medium Copy Control (MCC) structured field.

**Keyword X'0Enn'** Retired keyword for the IBM 3800 printer. See “Retired Parameters” for a description.

**Keyword X'90nn'** Specifies the high-order portion of a two-byte media destination ID. The allowed range is X'00'—X'FF'. The value X'00' is not valid if keyword X'91' also specifies a value of X'00', that is, the media destination ID X'0000' is reserved. This keyword may appear once. If this keyword is not present, the high-order portion of the media destination ID is set to X'00'. If this [MODCA-5-1265] keyword is not present and the X'91' keyword is not present, the media destination is not specified and a presentation environment default is used.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media destination specified by this keyword must match the media destination specified for the other copy subgroup in the pair.

**Keyword X'91nn'** Specifies the low-order portion of a two-byte media destination ID. The allowed range is X'00'—X'FF'. The value X'00' is not valid if keyword X'90' also specifies a value of X'00', that is, the media destination ID X'0000' is reserved. This keyword may appear once. If this keyword is not present, the low-order portion of the media destination ID is set to X'00'. If this keyword is not present and the X'90' keyword is not present, the media destination is not specified and a presentation environment default is used.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media destination specified by this keyword must match the media destination specified for the other copy subgroup in the pair.

**Keyword X'A0nn'** Specifies the local ID of fixed medium information that a printer or a printer-attached device applies to a sheet-side. This application is independent of data provided through the data stream, and does not mix with the print data provided in the data stream. Fixed medium information is applied either before or after the data stream information is presented.

| **Value** | **Description** |
| :--- | :--- |
| X'00'—X'FE' | Select a particular local ID for fixed medium information to be applied to the sheet-side. |
| X'FF' | Select all currently-supported local IDs for fixed medium information to be applied to the sheet-side. |

This keyword may appear multiple times and specify multiple local IDs for fixed medium information.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same local IDs for fixed medium information.

**Keyword X'A100'** Specifies a perforation cut at a fixed location on the physical medium according to the current setup of the printer or printer-attached device.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same perforation cuts.

**Keyword X'A200'** Specifies a separation cut at a fixed location on the physical medium according to the current setup of the printer or printer-attached device.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same separation cuts.

**Keyword X'B4nn'** Specifies the high-order portion of a two-byte presentation subsystem set-up ID. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'B5nn' keyword that immediately follows it and that specifies the low-order portion of the two-byte presentation subsystem set-up ID. The X'B4nn'—X'B5nn' keyword pair may appear multiple times. If the keyword pair is not present, a presentation subsystem set-up ID is not specified. The set-up ID specified by the X'B4nn' and X'B5nn' keywords is compared against the set-up IDs generated by the presentation subsystem, which typically consists of the presentation device and pre/post processing devices. If a match is found, presentation is allowed to proceed. If there is no match, the required set-up is not active in the presentation subsystem and presentation is terminated.

**Note:** A set-up ID is not the same as a setup name, which is a user-created name for a set of specific settings on a presentation device. A presentation device can support setup names, or set-up IDs, or both (the two functions do not necessarily interact). [MODCA-5-1266]

**Keyword X'B5nn'** Specifies the low-order portion of a two-byte presentation subsystem set-up ID. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'B4nn' keyword that immediately precedes it and that specifies the high-order portion of the two-byte presentation subsystem set-up ID. The X'B4nn'—X'B5nn' keyword pair may appear multiple times. If the keyword pair is not present, a presentation subsystem set-up ID is not specified. The set-up ID specified by the X'B4nn' and X'B5nn' keywords is compared against the set-up IDs generated by the presentation subsystem, which typically consists of the presentation device and pre/post processing devices. If a match is found, presentation is allowed to proceed. If there is no match, the required set-up is not active in the presentation subsystem and presentation is terminated.

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same presentation subsystem set-up IDs.

**Application Notes:**

1. When presentation is terminated, the print file is put into a state where it can be [MODCA-5-1267] resubmitted when the presentation subsystem is reconfigured to generate the required set-up IDs.
2. Presentation Subsystem set-up IDs are intended to be specified for one or more [MODCA-5-1268] documents in a print file. It is therefore recommended that the same IDs are specified in all the medium maps in the form map.

**Keyword X'D1nn'** Specifies whether the sheets generated by the current medium map should be offset (jogged) from the sheets generated by the previous medium map or whether the edge marks applied to sheets generated by this medium map should be changed from the edge marks applied to sheets generated by the previous medium map. This keyword applies to all sheets generated by the current medium map and needs to be specified only once. If this keyword is omitted, the default is X'00' (no offset, no change in edge marks).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'00' | No offset (no jog), no change in edge marks |
| X'01' | Apply offset (jog) or change edge marks |

**Note:** When processing partition ejects with N-up presentation, multiple medium maps may be invoked while building a single sheet. In that case, only the first X'D1nn' keyword is processed for a sheet. All other X'D1nn' keywords specified in medium maps invoked for the same sheet are ignored.

**Implementation Note:** Print servers that automatically issue a jog command between jobs and between multiple copies of a job may ignore the X'D1nn' keyword in the medium map used for the first sheet of the user's print file.

Table 23 shows how the jog control specified by this keyword is processed with N-up presentation and conditional media ejects when an existing medium map (MM) is replaced by a new medium map. The “Result” column defines whether the sheet processed with the new medium map is jogged with respect to the previous sheet and what type of media eject (sheet or partition) occurs when the new medium map is invoked. Note that in AFP environments a jog is accomplished with the generation of an IPDS jog command when the medium map that specifies the jog is first invoked. [MODCA-5-1269]

**Table 23. Sheet Jogging and Conditional Ejects**

| **Jog Control in Existing MM** | **Jog Control in New MM** | **Eject Control in New MM** | **Result** |
| :--- | :--- | :--- | :--- |
| | | | **Eject** | **Jog** |
| No jog | Jog | Partition | New sheet | Jog |
| No jog | Jog | New sheet | New sheet | Jog |
| Jog | Jog | Partition | Partition | Jog |
| Jog | Jog | New sheet | New sheet | Jog |
| Jog | No jog | Partition | New sheet | No jog |
| Jog | No jog | New sheet | New sheet | No jog |
| No jog | No jog | Partition | Partition | No jog |
| No jog | No jog | New sheet | New sheet | No jog |

**Keyword X'D2nn'** Specifies the local identifier of a Medium Preprinted Form Overlay (M-PFO) that is to be applied to all sheet-sides generated by this copy subgroup. The M-PFO is applied last, after all other data has been applied to the sheet-side. The allowed ID range is X'01'—X'7F'. The X'D2nn' keyword may appear once. If this keyword is specified more than once, the additional occurrences are ignored. This limits the number of M-PFOs to one per sheet-side. The local ID must be mapped to the name of an M-PFO in a Map Medium Overlay (MMO) structured field.

**Keyword X'E0nn'** Specifies the format of the media source selector (X'E1') keyword. This keyword may appear once. If this keyword is omitted, the X'E1' keyword, if present, is specified in Format 1.

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | The X'E1' keyword is specified in Format 1. |
| X'02' | The X'E1' keyword is specified in Format 2. |

**Keyword X'E1nn'** Specifies the media source. This keyword is defined in several formats. The format is selected by a X'E0' keyword or is defaulted to Format 1 if the X'E0' keyword is omitted. This keyword may appear once. If this keyword is omitted, the media source is not specified and a presentation environment default is used.

**Notes:**

1. If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, [MODCA-5-1270] the media source specified by this keyword must match the media source specified for the other copy subgroup in the pair.
2. The selected media source may be an inserter bin. Inserter bins do not support printing [MODCA-5-1271] from the data stream, therefore printing is suppressed when pages, PMC overlays, and medium overlays are processed with media from an inserter bin. When a requested media source, which may be an inserter bin, is not available, the presentation systems uses a default bin and ensures that it is not an inserter bin, therefore pages and overlays that are associated with an inserter bin are printed if the inserter bin is not available.

**Application Notes:**

1. In AFP environments, the default media source is normally the first media source reported [MODCA-5-1272] by the printer in the IPDS XOH-OPC reply.
2. To cause the insertion of a single sheet from the inserter bin, the application generates a [MODCA-5-1273] data stream with one (simplex printing) or two (duplex printing) “placeholder” pages that are processed with the medium map that selects an inserter bin as the media source. If the inserter bin is available, a sheet is inserted but these pages will not be printed on the inserted sheet. However, if the inserter bin is not available, the presentation system will [MODCA-5-1274] use a default media source that is not an inserter bin and the placeholder pages will be printed. This method can be extended to inserting multiple sheets by specifying multiple placeholder pages in the data stream.
3. An application can also cause the insertion of one or more sheets without generating [MODCA-5-1275] placeholder pages. This is done by specifying two consecutive Invoke Medium Map (IMM) structured fields in the data stream, where the first invoked medium map selects an inserter bin and specifies the constant front (keyword X'F901') function and simplex printing, and the second invoked medium map resumes page printing from a non-inserter bin. Multiple inserted sheets can be generated in this manner by specifying a copy count that is greater than one.

**X'E1nn' Format 1**

Specifies a value that identifies either a presentation device media source ID or the characteristics associated with a presentation device media source. The keyword values in Format 1 are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Media source ID X'00' |
| X'02' | Media source ID X'01' |
| X'03' | Media source ID X'02' |
| X'04' | Media source ID X'03' |
| X'41' | Envelope media source |
| X'64' | Manual feed media source |

**X'E1nn' Format 2**

Specifies a value that identifies a presentation device media source ID. The keyword values in Format 2 can be in the range X'01' to X'FF' and specify media source IDs whose values are one less than the keyword values:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Media source ID X'00' |
| X'02' | Media source ID X'01' |
| ⋮ | ⋮ |
| X'FE' | Media source ID X'FD' |
| X'FF' | Media source ID X'FE' |

**Keyword X'E8nn'** Specifies the high-order portion of a two-byte local ID to select a media type. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'E9nn' keyword that immediately follows it and that specifies the low-order portion of the two-byte media type local ID. The X'E8nn'–X'E9nn' keyword pair may appear only once. The media type local ID is mapped to a media type name or media type OID in the Map Media Type (MMT) structured field. If it is mapped to both, the media type OID takes precedence. If this keyword pair is present, it overrides the media source specified with the X'E1nn' keyword unless the presentation device doesn't support media type selection, in which case a specified media source is used. If the keyword pair is not present, the media is selected from the media source specified with the X'E1nn' keyword. A registry of standard media types along with their OID is provided in “Media Type Identifiers”.

**Keyword X'E9nn'** Specifies the low-order portion of a two-byte local ID to select a media type. The allowed range is X'00'—X'FF'. This keyword must be paired with a X'E8nn' keyword that immediately precedes it and that specifies the high-order portion of the two-byte media type local ID. The X'E8nn'–X'E9nn' keyword pair may appear only once. The media type local ID is mapped to a media type name or media type OID in the Map Media Type (MMT) structured field. If it is mapped to both, the media type OID takes precedence. If this keyword pair is present, it overrides the media source specified with the X'E1nn' keyword unless the presentation device doesn't support media type selection, in which case a specified media source is used. If the keyword pair is not present, the media is selected from the media source specified with the X'E1nn' keyword. A registry of standard media types along with their OID is provided in “Media Type Identifiers”.

**Note:** If the copy subgroup that references this MMC belongs to a duplex copy-subgroup pair, the media type specified by this keyword must match the media type specified for the other copy subgroup in the pair.

**Implementation Note:** AFP print servers will attempt to select the media type requested by the X'E8'/X'E9' keyword pair using the following priority:

1. Attempt to find an available media source containing the media type that matches [MODCA-5-1276] the specified OID. The media source cannot be an inserter bin.
2. Attempt to find an available media source containing the media type that matches [MODCA-5-1277] the specified name. The media source cannot be an inserter bin.
3. Attempt to find an available media source whose ID matches the ID specified in a [MODCA-5-1278] X'E1' keyword on the MMC.
4. Use the presentation process defaults for finding an available media source. [MODCA-5-1279]

**Keyword X'F1nn'** Retired keyword for the IBM 3800 printer. See “Retired Parameters” for a description.

**Keyword X'F2nn'** Specifies the local identifier of a medium overlay that is to be applied to all sheet-sides generated by this copy subgroup. This keyword may appear a maximum of eight times in an MMC structured field. The allowed ID range is X'01'–X'7F'. The local ID must be mapped to the name of the medium overlay in a Map Medium Overlay (MMO) structured field.

**Keyword X'F3nn'** Specifies the local identifier of a text suppression that is to be applied to all sheet-sides generated by this copy subgroup. This keyword may appear a maximum of eight times in an MMC structured field. The allowed ID range is X'01'–X'7F'.

**Keyword X'F4nn'** Specifies whether data is generated on the front side of the sheet (simplex) or on both sides of the sheet (duplex). If duplex is specified, the first copy subgroup in a pair generates the front sheet-side, and the second copy subgroup in the pair generates the back sheet-side. This keyword may appear once. If this keyword is omitted, the default is X'01' (simplex).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Simplex |
| X'02' | Normal duplex. The media is turned around the Ym axis. |
| X'03' | Tumble duplex. The media is turned around the Xm axis. |

See **Figure 60** for a description of normal duplex and tumble duplex. [MODCA-5-1280]

<!-- Page XX -->

**Figure 60. Normal Duplex and Tumble Duplex Printing**

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same value for this keyword.

**Keyword X'F8nn'** Specifies the level of print quality to be used on all sheet-sides generated by this copy subgroup. The mapping of print quality levels to physical print quality is presentation-system-dependent. This keyword may appear once.

The allowed quality level range is X'01'–X'FF', and is defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | Lowest print quality level |
| X'FE' | Highest print quality level |
| X'FF' | Device default print quality |

**Keyword X'F9nn'** Specifies whether both variable page data and medium overlay data or only medium overlay data should be generated on all sheet-sides generated by this copy subgroup. This function [MODCA-5-1281] is known as constant forms control. Note that PMC overlays are considered variable page data for this keyword. This keyword may appear once. If this keyword is omitted, the default is X'00' (present both medium overlay data and variable page data).

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'00' | Present both medium overlay data and variable page data |
| X'01' | Present only medium overlay data. If no medium overlays are specified for this copy subgroup, no data is presented on the sheet-sides generated by this copy subgroup. |

**Keyword X'FCnn'** Specifies the number of pages to be placed on a physical medium using N-up partitioning. In N-up partitioning, each side of the physical medium is divided into a number of equal-size partitions, where the number of partitions is indicated by the number N in “N-up”. If duplex is specified, the same N-up partitioning is applied to the back side as is applied to the front side. With simplex N-up partitioning, N pages are placed on the physical medium, and with duplex N-up partitioning, 2N pages are placed on the physical medium. Pages placed into partitions may be blank pages generated by setting PgFlgs bit 0 = B'1' in the Page Position (PGP) structured field repeating group.

Pages are placed into partitions using either a default N-up page placement or an explicit N-up page placement, as specified in the Page Position (PGP) structured field. In default N-up page placement, consecutive pages in the data stream are placed into consecutively-numbered partitions. In explicit N-up page placement, consecutive pages in the data stream are processed using consecutive PGP repeating groups and are placed into explicitly-specified partitions. For more information on placement, see “Page Position (PGP) Format 2”.

Pages may be rotated within their partitions so that the page presentation space X axis is at a 0°, 90°, 180°, or 270° orientation with respect to the medium presentation space X axis. This rotation is specified in the Page Position structured field.

Pages are positioned within their partition relative to the partition origin using the offsets specified in the Page Position structured field. Modifications may be applied to pages before they are placed in their partition using the Page Modification Control (PMC) structured field.

**Figure 21** shows the partitioning for wide continuous-forms media, narrow continuous-forms media, and cut-sheet media. Partitioning is not used with envelope media. **Figure 61** through **Figure 72** show partition numbering for various media. This keyword may appear once.

The keyword values are defined as follows:

| **Value** | **Description** |
| :--- | :--- |
| X'01' | 1-up partitioning. The medium presentation space is divided into one partition. One page (simplex) or two pages (duplex) are presented on the physical medium. |
| X'02' | 2-up partitioning. The medium presentation space is divided into two partitions. Two pages (simplex) or four pages (duplex) are presented on the physical medium. |
| X'03' | 3-up partitioning. The medium presentation space is divided into three partitions. Three pages (simplex) or six pages (duplex) are presented on the physical medium. |
| X'04' | 4-up partitioning. The medium presentation space is divided into four partitions. Four pages (simplex) or eight pages (duplex) are presented on the physical medium. |

**Note:** All Medium Modification Control structured fields that are referenced by the same Medium Copy Count structured field must specify the same value for this keyword. [MODCA-5-1282]

**Application Note:** IPDS printers require that pages be contained within their partition if default N-up page placement is specified, otherwise an exception is generated. This restriction does not exist if explicit N-up page placement is specified. That is, pages may overflow their partition without necessarily causing an exception.

#### MMC Exception Condition Summary

**X'02'** An undefined keyword is encountered in an MMC structured field. [MODCA-5-1283]


### Map Media Destination (MMD)

The Map Media Destination structured field maps a media destination local ID to the name of a media destination. [MODCA-5-1284]

**Architecture Note:** A media destination local ID is specified with the X'90nn' + X'91nn' keyword pair on the MMC structured field.

#### MMD (X'D3ABCD') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABCD'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1285]

One or more repeating groups in the following format: [MODCA-5-1287]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1286]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 14–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1288]|
| 2–n | Triplets | | | See MMD Semantics for triplet applicability. | M | X'14' [MODCA-5-1289]|

#### MMD Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Media Destination structured field repeating groups as follows: [MODCA-5-1290]

| **Triplet** | **Type** | **Usage** [MODCA-5-1291]|
| :--- | :--- | :--- [MODCA-5-1292]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1293]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'12'—Media Destination Reference. The media destination reference may be specified in the following format:<br>• FQNFmt = X'00'; the reference is made with a character-encoded name. [MODCA-5-1294]|
| X'22' | | **Extended Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Extended Resource Local Identifier Triplet X'22'”. The only Extended Resource Local Identifier type that may appear is X'42'—Media Destination Resource. |

**Architecture Note:** In the UP3i architecture, the media destination name must be encoded using UTF-16BE; it is therefore recommended that the same encoding be used in the FQN type X'12' triplet when FQNFmt = X'00'.

**Architecture Note:** The local IDs used with resource type X'42' are specified with a X'90nn' + X'91nn' keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this resource type is restricted to 2-byte values.

Within the same medium map, you may not map the same media destination local ID to more than one media destination name or a X'01' exception condition exists. Within the same medium map, different media destination local IDs may be mapped to the same media destination name.

**Implementation Note:** AFP print servers will process the media destination name as follows. Note that, for UP3i devices, media destination names are reported as UP3i tupel names in the UP3i Tupel sdf in the IPDS XOH-OPC reply. The same UP3i Tupel sdf also specifies a 2-byte tupel ID.

* If a media destination local ID is specified in the MMC, the server checks for a mapping to a media [MODCA-5-1295] destination name in MMD structured fields in the Medium Map.
  – If a mapping is found, the server checks the UP3i Tupel sdfs in the IPDS XOH-OPC for a matching tupel name. If one is found, the server uses the tupel ID (which is also reported in the UP3i Tupel sdf) that corresponds to that name as a media destination ID to select the media destination.
  – If no mapping is found, or if a mapping is found but there is no matching tupel name, the server uses the MMC media destination local ID to select the media destination.
* If there is no media destination local ID specified in the MMC, the server selects a default media [MODCA-5-1296] destination.

#### MMD Exception Condition Summary

**X'01'** The same LID is mapped to more than one media destination within the same structured field.

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'12' (Media Destination Reference) [MODCA-5-1297] appears within any repeating group.
* An Extended Resource Local Identifier (X'22') triplet type other than X'42' appears within any [MODCA-5-1298] repeating group.


### Map Medium Overlay (MMO)

The Map Medium Overlay structured field maps one-byte medium overlay local identifiers that are specified by keywords in the Medium Modification Control (MMC) structured field to medium overlay names. [MODCA-5-1299]

#### MMO (X'D3B1DF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B1DF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1300]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1301]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1302]|
| 0 | UBIN | **RGLength** | | X'0C' Length of each repeating group | M | X'06' [MODCA-5-1303]|
| 1–3 | Reserved | | | Should be zero | M | X'06' |

Zero to 127 repeating groups in the following format: [MODCA-5-1304]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0 | UBIN | **OVLid** | | X'01'–X'7F' Medium overlay local identifier | M | X'06' [MODCA-5-1305]|
| 1 | BITS | **Flags** | | Bit 0: Raster indicator; retired for the IBM 3800 printer<br>Bits 1–7: Reserved; should be zero | M | X'06' [MODCA-5-1306]|
| 2–3 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1307]|
| 4–11 | CHAR | **OVLname** | | Name of medium overlay | M | X'06' [MODCA-5-1308]|

#### MMO Semantics

**RGLength** Length of each repeating group. Set to 12.

**OVLid** Medium overlay local identifier as specified by a keyword in an MMC structured field. The allowed range is X'01'–X'7F' and must be unique to each repeating group.

**Flags** Bit Description:
* **0** Retired parameter for the IBM 3800 printer. See “Retired Parameters” for a description. [MODCA-5-1309]
* **1–7** Reserved; should be zero.

**OVLname** External name of the medium overlay. [MODCA-5-1310]


### Map Media Type (MMT)

The Map Media Type structured field maps a media type local ID to the name or OID of a media type. See “Media Type Identifiers” for a list of media types registered by their name and their OID.

**Architecture Note:** A media type local ID is specified with the X'E8nn' + X'E9nn' keyword pair on the MMC structured field.

#### MMT (X'D3AB88') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AB88'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1311]

One or more repeating groups in the following format: [MODCA-5-1313]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1312]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 14–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1314]|
| 2–n | Triplets | | | See MMT Semantics for triplet applicability. | M | X'14' [MODCA-5-1315]|

#### MMT Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Media Type structured field repeating groups as follows: [MODCA-5-1316]

| **Triplet** | **Type** | **Usage** [MODCA-5-1317]|
| :--- | :--- | :--- [MODCA-5-1318]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1319]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. May occur twice in each repeating group if one occurrence uses FQNFmt X'00' (name), and the other occurrence uses FQNFmt X'10' (OID). See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'11'—Media Type Reference. The media type reference may be specified in one of two ways:<br>• If FQNFmt = X'00', the reference is made with a character-encoded name. [MODCA-5-1320] [MODCA-5-1321]<br>• If FQNFmt = X'10', the reference is made with an ASN.1 OID encoded using the definite short form. See “Media Type Identifiers”. [MODCA-5-1322]|
| X'22' | | **Extended Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Extended Resource Local Identifier Triplet X'22'”. The only Extended Resource Local Identifier type that may appear is X'40'—Media Type resource. |

**Architecture Note:** In the IPDS architecture, the media type name must be encoded using IBM code page 500, character set 640 (plus space character). It is strongly recommended that the same encoding be used in the FQN type X'11' triplet when FQNFmt = X'00', since not all print servers are able to process other encodings.

**Architecture Note:** The local IDs used with resource type X'40' are specified with a X'E8nn' + X'E9nn' keyword pair on the MMC that can only carry a 2-byte ID. Therefore, the range for this resource type is restricted to 2-byte values.

If the FQN type X'11' triplet is specified twice in a repeating group, the FQNFmt X'10'—OID reference, takes precedence.

Within the same medium map, you may not map the same Resource Local ID to more than one media type or a X'01' exception condition exists. The media type may be specified with an FQN type X'11' triplet using FQNFmt X'10' (OID reference), an FQN type X'11' triplet using FQNFmt X'00' (name reference), or both. Within the same medium map, different Resource Local IDs may be mapped to the same media type. [MODCA-5-1323]

**Implementation Note:** AFP print servers will attempt to select the requested media type using the following priority:

1. Attempt to find an available media source containing the media type that matches the specified OID. [MODCA-5-1324] The media source cannot be an inserter bin.
2. Attempt to find an available media source containing the media type that matches the specified [MODCA-5-1325] name. The media source cannot be an inserter bin.
3. Attempt to find an available media source whose ID matches the ID specified in a X'E1' keyword on [MODCA-5-1326] the MMC.
4. Use the presentation process defaults for finding an available media source. [MODCA-5-1327]

#### MMT Exception Condition Summary

**X'01'** The same LID is mapped to more than one media type within the same structured field.

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'11' (Media Type Reference) [MODCA-5-1328] appears within any repeating group.
* An Extended Resource Local Identifier (X'22') triplet type other than X'40' appears within any [MODCA-5-1329] repeating group.


### Map Page (MPG)

The Map Page structured field identifies a page that is to be merged with data specified for the current page by using an Include Page (IPG) structured field. [MODCA-5-1330]

#### MPG (X'D3ABAF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABAF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1331]

One repeating group in the following format: [MODCA-5-1333]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1332]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 12–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1334]|
| 2–n | Triplets | | | See MPG Semantics for triplet applicability. | M | X'14' [MODCA-5-1335]|

#### MPG Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Page structured field as follows: [MODCA-5-1336]

| **Triplet** | **Type** | **Usage** [MODCA-5-1337]|
| :--- | :--- | :--- [MODCA-5-1338]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once in each repeating group. Specifies encoding for character strings. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1339]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'83'—Begin Document Reference. Specifies the name of the document that contains the page. [MODCA-5-1340]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'87'—Begin Page Reference. Specifies the name of the page to be mapped. [MODCA-5-1343]|
| X'5A' | | **Object Offset** Optional. May occur once, with ObjTpe=X'AF', to specify that pages are the objects to be counted. Specifies the number of pages preceding the page to be mapped. When this triplet is specified, the page name in the FQN type X'87' triplet is ignored. See “Object Offset Triplet X'5A'”. [MODCA-5-1344]|

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified.

#### MPG Exception Condition Summary

**X'01'** This exception condition exists when:
* Multiple type X'87' (Begin Page Reference) Fully Qualified Name triplets appear within the [MODCA-5-1345] repeating group.
* Multiple type X'83' (Begin Document Reference) Fully Qualified Name triplets appear within [MODCA-5-1346] the repeating group.

**X'02'** A Fully Qualified Name (X'02') triplet other than a type X'87' (Begin Page Reference) or a type X'83' (Begin Document Reference) appears within the repeating group. [MODCA-5-1347]


### Map Page Overlay (MPO)

The Map Page Overlay structured field maps local identifiers to page overlay names. [MODCA-5-1348]

#### MPO (X'D3ABD8') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABD8'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1349]

One to 254 repeating groups in the following format: [MODCA-5-1351]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1350]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 11–(n)+1 | Total length of this repeating group | M | X'06' [MODCA-5-1352]|
| 2–n | Triplets | | | See MPO Semantics for triplet applicability. | M | X'14' [MODCA-5-1353]|

#### MPO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Page Overlay structured field as follows: [MODCA-5-1354]

| **Triplet** | **Type** | **Usage** [MODCA-5-1355]|
| :--- | :--- | :--- [MODCA-5-1356]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur more than once. Specifies encoding for character strings. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1357]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is X'84'—Begin Resource Object Reference which must match the name of an overlay resource. [MODCA-5-1358]|
| X'24' | | **Resource Local Identifier** Mandatory. Must occur once in each repeating group. See “Resource Local Identifier Triplet X'24'”. The only Resource Local Identifier type that may appear is X'02'—Page Overlay. [MODCA-5-1359]|

Within the same Map Page Overlay structured field, you may not map the same Resource Local ID to more than one page overlay resource or a X'01' exception condition exists. However, you may use two or more repeating groups within the same Map Page Overlay structured field to map different LIDs to the same page overlay resource. [MODCA-5-1360]

**Application Notes:**

1. The local identifier specified in the MPO structured field is not used to reference the page overlay when it is [MODCA-5-1361] included on a page with an IPO or PMC structured field. It may optionally be used in an application-dependent manner to manage the overlay resource.
2. To optimize print performance, it is strongly recommended that the same encoding scheme be used for a [MODCA-5-1362] resource reference wherever in a print file that resource reference is specified.

**Architecture Note:** In AFP environments, the following retired triplets are used on this structured field:
* **Page Overlay Conditional Processing (X'46') triplet**, may occur zero or more times; see “Page Overlay [MODCA-5-1363] Conditional Processing Triplet X'46'”.
* **Resource Usage Attribute (X'47') triplet**, may occur zero or once; see “Resource Usage Attribute [MODCA-5-1364] Triplet X'47'”.

#### MPO Exception Condition Summary

**X'01'** This exception condition exists when:
* An overlay with the same name as that specified on the FQN type X'84' triplet cannot be [MODCA-5-1365] located.
* Multiple FQN type X'84' triplets appear within the same repeating group. [MODCA-5-1366]
* Multiple type X'02' Resource Local Identifier (X'24') triplets appear within the same repeating [MODCA-5-1367] group.
* The same LID is mapped to more than one page overlay within the same structured field. [MODCA-5-1368]

**X'02'** This exception condition exists when:
* A Fully Qualified Name (X'02') triplet other than a type X'84' (Begin Resource Object [MODCA-5-1369] Reference) appears within any repeating group.
* A Resource Local Identifier (X'24') triplet type other than X'02' appears within any repeating [MODCA-5-1370] group.


### Map Page Segment (MPS)

The Map Page Segment structured field identifies page segments that are required to present a page on a physical medium.

#### MPS (X'D3B15F') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B15F'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1371]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1372]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1373]|
| 0 | UBIN | **RGLength** | | X'0C' Length of each repeating group | M | X'06' [MODCA-5-1374]|
| 1–3 | Reserved | | | Should be zero | M | X'06' |

Zero to 127 repeating groups in the following format: [MODCA-5-1375]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–3 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1376]|
| 4–11 | CHAR | **PsegName** | | Name of page segment | M | X'06' [MODCA-5-1377]|

#### MPS Semantics

**RGLength** Length of each repeating group. Set to 12.

**PsegName** External name of the page segment.

**Application Notes:**

1. A page segment included on a page or overlay with an IPS may optionally be mapped with an MPS in the [MODCA-5-1378] AEG for that page or overlay. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment data is sent to the presentation device as part of the page or overlay and is called a soft page segment.
2. To optimize print performance, it is strongly recommended that the same encoding scheme be used for a [MODCA-5-1379] resource reference wherever in a print file that resource reference is specified. [MODCA-5-1380]


### Map Presentation Text (MPT)

The Map Presentation Text structured field specifies how a presentation text object that contains an Object Environment Group (OEG) is mapped into its object area. [MODCA-5-1381]

#### MPT (X'D3AB9B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AB9B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1382]

One repeating group in the following format: [MODCA-5-1384]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1383]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 5 | Total length of this repeating group | M | X'06' [MODCA-5-1385]|
| 2–4 | Triplets | | | Mapping Option triplet | M | X'14' [MODCA-5-1386]|

#### MPT Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**Triplets** Appear in the Map Presentation Text structured field as follows: [MODCA-5-1387]

| **Triplet** | **Type** | **Usage** [MODCA-5-1388]|
| :--- | :--- | :--- [MODCA-5-1389]|
| X'04' | | **Mapping Option** Mandatory. Must occur once. See “Mapping Option Triplet X'04'”. The valid mapping options for the MPT structured field are:<br>Value Description [MODCA-5-1390]<br>X'00' Position<br>All others Reserved [MODCA-5-1391]|

**Note:** If this structured field is not present in the data stream, the architected default is position. [MODCA-5-1392]

#### MPT Exception Condition Summary

**X'01'** The Map Presentation Text structured field contains more than one repeating group. [MODCA-5-1393]


### Map Suppression (MSU)

The Map Suppression structured field maps one-byte text suppression local identifiers to text suppression names. Suppressible text is identified in presentation text objects with a local identifier and is bracketed with control sequences that specify the beginning and the end of the suppression. A text suppression is activated by specifying its local identifier in a Medium Modification Control (MMC) structured field in a medium map. [MODCA-5-1394]

#### MSU (X'D3ABEA') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ABEA'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1395]

Zero to 127 repeating groups in the following format: [MODCA-5-1397]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1396]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–7 | CHAR | **SUPname** | | Name of text suppression | M | X'06' [MODCA-5-1398]|
| 8 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1399]|
| 9 | CODE | **SUPid** | | X'01'–X'7F' Text suppression local identifier | M | X'06' [MODCA-5-1400]|

#### MSU Semantics

**SUPname** Name of the text suppression.

**SUPid** Text suppression local identifier, as specified by a keyword in an MMC structured field. The allowed range is X'01'—X'7F'.

**Note:** The local ID may be mapped to more than one text suppression name.

**Architecture Note:** When processing AFP line data with Page Definitions, the Descriptor structured fields can enable the text suppression function for a record, and, if so, assign an eight-byte name to the suppression function. This name is mapped to a local identifier using the MSU structured field. [MODCA-5-1401]


### No Operation (NOP)

The No Operation structured field performs no function. [MODCA-5-1402]

#### NOP (X'D3EEEE') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3EEEE'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1403]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1404]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1405]|
| 0–n | UNDF | **UndfData** | | Up to 32,759 bytes of data with no architectural definition | O | X'00' [MODCA-5-1406]|

#### NOP Semantics

**UndfData** Is data that has no architectural definition.

The No Operation structured field may be specified within any begin-end domain.

**Note:** The No Operation structured field may be used to carry comments or any other type of unarchitected data. Although this is not recommended, it may also be used to carry semantic data in private or exchange data streams. However, because receivers of interchange data streams should ignore the content of No Operation structured fields, and because receiver-generator products are not required to propagate No Operation structured fields, no semantics should be attached to the data carried by the No Operation structured field in interchange data streams. [MODCA-5-1407]


### Object Area Descriptor (OBD)

The Object Area Descriptor structured field specifies the size and attributes of an object area presentation space.

#### OBD (X'D3A66B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A66B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1408]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1409]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1410]|
| 0–n | Triplets | | | See OBD Semantics for triplet applicability. | M | X'14' [MODCA-5-1411]|

#### OBD Semantics

**Triplets** Appear in the Object Area Descriptor structured field as follows: [MODCA-5-1412]

| **Triplet** | **Type** | **Usage** [MODCA-5-1413]|
| :--- | :--- | :--- [MODCA-5-1414]|
| X'43' | | **Descriptor Position** Mandatory. Must occur once. See “Descriptor Position Triplet X'43'”. [MODCA-5-1415]|
| X'4B' | | **Measurement Units** Mandatory. Must occur once. See “Measurement Units Triplet X'4B'”. [MODCA-5-1416]|
| X'4C' | | **Object Area Size** Mandatory. Must occur once. See “Object Area Size Triplet X'4C'”. [MODCA-5-1417] [MODCA-5-1418]|
| X'4E' | | **Color Specification** Optional. May occur once. Specifies a color for the object area. When this triplet is specified on an object area, the complete object area becomes foreground data that is colored with the specified color before any object data is added to the area. If the default mixing rules are used, the object area overpaints (covers) any data that is underneath. See “Color Specification Triplet X'4E'”. [MODCA-5-1419]|
| X'70' | | **Presentation Space Reset Mixing** Optional. May occur once. If this triplet specifies a reset to the color of medium (BgMxFlag=B'1'), the reset takes place at the point in the data stream where the triplet occurs. This triplet may not appear with a Presentation Space Mixing Rules triplet. See “Presentation Space Reset Mixing Triplet X'70'”. [MODCA-5-1423]|
| X'71' | | **Presentation Space Mixing Rules** Optional. May occur once. This triplet may not appear with a Presentation Space Reset Mixing triplet. See “Presentation Space Mixing Rules Triplet X'71'”. [MODCA-5-1424]|

**Note:** The Color Specification (X'4E') triplet is not permitted on the OBD for presentation text that may optionally occur in the AEG for a page or overlay. [MODCA-5-1420]

**Implementation Note:** The Presentation Space Mixing Rules (X'71') triplet is currently not used in AFP environments.

**Architecture Note:** Triplets that affect the object area presentation space are processed in the order in which they occur on the OBD. For example, if a Presentation Space Reset Mixing (X'70') triplet on the OBD is followed by a Color Specification (X'4E') triplet, the object area is colored with the color specified in the X'4E' triplet and covers any data underneath it regardless of whether the X'70' triplet specified “reset to color of medium” or “do not reset to color of medium”. If a Color Specification (X'4E') triplet is followed by a X'70' triplet, and if the X'70' triplet specified “reset to color of medium”, the object area is colored with color of medium. If the X'70' triplet specified “do not reset to color of medium”, the X'70' triplet does not change the object area and it remains foreground data colored with the color specified by the X'4E' triplet.

#### OBD Exception Condition Summary

**X'01'** The OBD structured field contains both a Presentation Space Reset Mixing triplet and a Presentation Space Mixing Rules triplet. [MODCA-5-1425]


### Object Area Position (OBP)

The Object Area Position structured field specifies the origin and orientation of the object area, and the origin and orientation of the object content within the object area. [MODCA-5-1426]

#### OBP (X'D3AC6B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3AC6B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1427]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1428]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1429]|
| 0 | CODE | **OAPosID** | X'01'–X'7F' | The object area position identifier | M | X'06' [MODCA-5-1430]|

One repeating group in the following format: [MODCA-5-1430]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 23 | Total length of this repeating group | M | X'06' [MODCA-5-1431]|
| 2–4 | SBIN | **XoaOset** | -32,768–32,767 | X-axis origin of the object area | M | X'06' [MODCA-5-1432]|
| 5–7 | SBIN | **YoaOset** | -32,768–32,767 | Y-axis origin of the object area | M | X'06' [MODCA-5-1433]|
| 8–9 | CODE | **XoaOrent** | | The object area's X-axis rotation from the X axis of the reference coordinate system | M | X'06' [MODCA-5-1434]|
| 10–11 | CODE | **YoaOrent** | | The object area's Y axis rotation from the X axis of the reference coordinate system | M | X'06' [MODCA-5-1435]|
| 12 | Reserved | | | Should be zero | M | X'06' [MODCA-5-1438]|
| 13–15 | SBIN | **XocaOset** | -32,768–32,767 | X-axis origin for object content | M | X'06' [MODCA-5-1439]|
| 16–18 | SBIN | **YocaOset** | -32,768–32,767 | Y-axis origin for object content | M | X'06' [MODCA-5-1440]|
| 19–20 | CODE | **XocaOrent** | X'0000' | The object content's X-axis rotation from the X axis of the object area coordinate system | M | X'06' [MODCA-5-1441]|
| 21–22 | CODE | **YocaOrent** | X'2D00' | The object content's Y-axis rotation from the X axis of the object area coordinate system | M | X'06' [MODCA-5-1442]|
| 23 | CODE | **RefCSys** | X'00', X'01', X'05' | Reference coordinate system:<br>X'00' Page/overlay; origin by IPS<br>X'01' Page/overlay; standard origin<br>X'05' Retired value | M | X'06' [MODCA-5-1443]|

**Rotation Formats (XoaOrent, YoaOrent)**

| **Bits** | **Meaning** |
| :--- | :--- |
| 0–8 | Degrees rotation (0–359) |
| 9–14 | Minutes rotation (0–59) |
| 15 | Reserved |

#### OBP Semantics

**OAPosID** Specifies an identifier for this Object Area Position structured field that is unique within the environment group. It is used to associate the Object Area Position structured field with the Object Area Descriptor structured field.

**RGLength** Specifies the total length of the repeating group, including the length of the **RGLength** parameter itself.

**XoaOset** Specifies the offset along the X axis, Xpg or Xol, of the referenced coordinate system to the origin of the X axis, Xoa, for the object area coordinate system. The value is expressed in terms of the number of referenced coordinate system X-axis measurement units.

**YoaOset** Specifies the offset along the Y axis, Ypg or Yol, of the referenced coordinate system to the origin of the Y axis, Yoa, for the object area coordinate system. The value is expressed in terms of the number of referenced coordinate system Y-axis measurement units.

**XoaOrent** Specifies the amount of clockwise rotation of the object area's X axis, Xoa, about its defined origin relative to the X axis of the reference coordinate system.

**YoaOrent** Specifies the amount of clockwise rotation of the object area's Y axis, Yoa, about its defined origin relative to the X axis of the reference coordinate system. The **YoaOrent** value must be 90 degrees greater than the **XoaOrent** value or a X'01' exception condition exists. [MODCA-5-1444]

**Note:** If the object area orientation is such that the sum of the object area origin offset and the object area extent exceeds the size of the including presentation space in either the X or Y direction, all of the object area will not fit. If an attempt is made to present data in the portion that falls outside, that portion of the data is not presented, and a X'01' exception condition exists. [MODCA-5-1445]

**XocaOset** Specifies the offset along the X axis of the object area coordinate system, Xoa, to the X origin of the object content. The value is expressed in terms of the number of object area coordinate system X-axis measurement units.

**YocaOset** Specifies the offset along the Y axis of the object area coordinate system, Yoa, to the Y origin of the object content. The value is expressed in terms of the number of object area coordinate system Y-axis measurement units.

**Notes:**

1. The object content is developed in the data object presentation space; within the context [MODCA-5-1446] of this structured field the two terms are synonymous.
2. The **XocaOset** and **YocaOset** parameters are used only when a position or position and [MODCA-5-1447] trim mapping is specified. They are ignored for all other mappings.

**XocaOrent** Specifies the amount of rotation of the object content's X axis about its defined origin relative to the X axis of the object area coordinate system.

**YocaOrent** Specifies the amount of rotation of the object content's Y axis about its defined origin relative to the X axis of the object area coordinate system.

**Note:** If the object content orientation is such that the origin offset exceeds the size of the object area presentation space, the object data will not fit. If the mapping option is position (X'00') and an attempt is made to present data outside the object area, that portion is not presented and a X'01' exception condition exists.

**RefCSys** Specifies the coordinate system and origin used to position the object area.

| **Value** | **Description** |
| :--- | :--- |
| X'00' | Used only if the object is part of a page segment. The reference coordinate system is the including page or overlay coordinate system. Origin is defined by IPS. |
| X'01' | The reference coordinate system is the including page or overlay coordinate system. Standard origin (0,0). |
| X'05' | Retired value. See “Retired Parameters”. |

#### OBP Exception Condition Summary

**X'01'** This exception condition exists when:
* The value specified for **YoaOrent** is not 90 degrees greater rotation than the value specified [MODCA-5-1448] for **XoaOrent**.
* An attempt is made to present data outside the presentation space of the containing [MODCA-5-1449] coordinate system.
* The mapping option is position and an attempt is made to present data outside the object [MODCA-5-1450] area presentation space. [MODCA-5-1451]


### Object Container Data (OCD)

The Object Container Data structured field contains the data for an object carried in an object container. See “Object Type Identifiers” for the list of object types that may be carried in an object container. [MODCA-5-1452]

#### OCD (X'D3EE92') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3EE92'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1453]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1454]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1455]|
| 0–n | UNDF | **ObjCdat** | | Up to 32,759 bytes of object data | O | X'00' [MODCA-5-1456]|

#### OCD Semantics

**ObjCdat** Contains the object data.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-1457]
### Presentation Environment Control (PEC)


The Presentation Environment Control structured field specifies parameters that affect the rendering of presentation data, the appearance that is to be assumed by the presentation device, and the setup name to be used.

#### PEC (X'D3A7A8') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A7A8'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1458]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1459]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1460]|
| 0–1 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-1461]|
| 2–n | | **Triplets** | | See PEC Semantics for triplet applicability | O | X'10' [MODCA-5-1462]|

#### PEC Semantics

**Triplets** Appear in the Presentation Environment Control structured field as follows: [MODCA-5-1463]

| **Triplet** | **Type** | **Usage** [MODCA-5-1464]|
| :--- | :--- | :--- [MODCA-5-1465]|
| X'5A' | | **Object Offset** Optional. If this PEC specifies the Rendering Intent X'95' triplet and/or the Device Appearance X'97' triplet and is specified in the DEG of a form map, this triplet may occur once with ObjTpe=X'A8' to specify that documents are the objects to be counted. Specifies how many documents in the print file precede the document to be assigned this rendering intent and/or to be processed with this device appearance. The offset is measured from the beginning of the print file, so that the first document has offset 0, the second document has offset 1, and the nth document has offset (n-1). This triplet is ignored in all other cases. See “Object Offset Triplet X'5A'”. [MODCA-5-1466]|
| X'95' | | **Rendering Intent** Optional. May occur once. Specifies the rendering intent that is to be used when presenting the document component that this PEC applies to. See “Rendering Intent Triplet X'95'”. [MODCA-5-1467]|
| X'97' | | **Device Appearance** Optional. May occur once. Specifies the appearance that is to be assumed by the presentation device. See “Device Appearance Triplet X'97'”. [MODCA-5-1468]|
| X'9E' | | **Setup Name** Optional. May occur once. Specifies the setup name to be used by the presentation device. See “Setup Name Triplet X'9E'”. [MODCA-5-1469]|

**Notes:**

1.  The PEC can be used to specify a rendering intent with the **Rendering Intent (X'95')** triplet as follows:
    *   In the Document Environment Group (DEG) of a form map
    *   In a medium map, in which case it is considered to be a medium level control for purposes of n-up partition/sheet eject processing [MODCA-5-1470]
    *   In the Active Environment Group (AEG) of a page or overlay [MODCA-5-1471]
    *   In the Object Environment Group (OEG) of a PTOCA, GOCA, or IOCA object, or in the OEG of an Object Container [MODCA-5-1472]
    For more information, see the appropriate environment group structure definitions in Chapter 4, “MO:DCA Objects”.

2.  The PEC can be used to specify a device appearance with the **Device Appearance (X'97')** triplet as follows:
    *   In the Document Environment Group (DEG) of a form map [MODCA-5-1473] [MODCA-5-1474]
    *   In a medium map, in which case it is considered to be a medium level control for purposes of n-up partition/sheet eject processing [MODCA-5-1475]
    For more information, see the appropriate environment group and medium map structure definitions in Chapter 4, “MO:DCA Objects”.

3.  The PEC can be used to specify a setup name with the **Setup Name (X'9E')** triplet as follows:
    *   In the Document Environment Group (DEG) of a form map [MODCA-5-1476] [MODCA-5-1477]
    For more information, see the appropriate environment group structure definitions in Chapter 4, “MO:DCA Objects”.

**Note:** A setup name is not the same as a set-up ID (see “Medium Modification Control (MMC)”). A presentation device can support setup names, or set-up IDs, or both (the two functions do not necessarily interact). [MODCA-5-1478]

### Presentation Fidelity Control (PFC)

The Presentation Fidelity Control structured field specifies the user fidelity requirements for data presented on physical media and for operations performed on physical media. The scope of the Presentation Fidelity Control structured field is the document or print file controlled by the form map that contains this structured field. [MODCA-5-1479]

#### PFC (X'D3B288') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B288'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1480]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1481]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1482]|
| 0 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-1483]|
| 1 | BITS | **PFCFlgs** | | Flags | M | X'06' [MODCA-5-1484]|
| 2–3 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-1485]|
| 4–n | | **Triplets** | | See PFC Semantics for triplet applicability | O | X'10' [MODCA-5-1486]|

#### PFC Semantics

Triplets are used on the Presentation Fidelity Control structured field to define specific presentation fidelity requirements that are to be applied by the presentation process as data is presented on physical media. While triplets may be conceptually related, each triplet is processed independently of any other triplet. Therefore, it is the responsibility of the generator of the Presentation Fidelity Control structured field to ensure cross-triplet consistency. If a particular fidelity triplet is not specified on this structured field, or if this structured field is not specified, presentation process defaults are used to control the presentation fidelity.

**PFCFlgs** The following flags are defined:

*   **Bit 0: Fidelity Control Activation** [MODCA-5-1487]
    *   B'0' Reset all fidelity controls to their presentation process defaults, then apply fidelity controls specified by this PFC structured field
    *   B'1' Leave all fidelity controls at their current setting, and additionally apply fidelity controls specified by this PFC structured field. If there is a conflict between an existing fidelity control and a new fidelity control, the last-specified fidelity control takes precedence.
*   **Bits 1–7: Reserved**; all bits should be B'0'. [MODCA-5-1488]

**Triplets** Appear in the Presentation Fidelity Control structured field as follows: [MODCA-5-1489]

| **Triplet** | **Type** | **Usage** [MODCA-5-1490]|
| :--- | :--- | :--- [MODCA-5-1491]|
| X'74' | | **Toner Saver** Optional. May occur once. Used to activate and deactivate a toner saver mode for printing. See “Toner Saver Triplet X'74'”. [MODCA-5-1492] [MODCA-5-1493]|
| X'75' | | **Color Fidelity** Optional. May occur once. Specifies the actions to be taken by the presentation process when a color exception is detected while processing the data stream. See “Color Fidelity Triplet X'75'”. [MODCA-5-1494]|
| X'78' | | **Font Fidelity** May occur once. Specifies the actions to be taken by the presentation process when a font resolution exception is detected while processing the data stream. See “Font Fidelity Triplet X'78'”. [MODCA-5-1495]|
| X'86' | | **Text Fidelity** Optional. May occur once. Specifies the actions to be taken by the presentation process when a text exception is detected while processing the data stream. See “Text Fidelity Triplet X'86'”. [MODCA-5-1496]|
| X'87' | | **Media Fidelity** Optional. May occur once. Specifies the actions to be taken by the presentation process when a request for a specific media or a specific media bin cannot be satisfied. See “Media Fidelity Triplet X'87'”. [MODCA-5-1497]|
| X'88' | | **Finishing Fidelity** Optional. May occur once. Specifies the actions to be taken by the presentation process when a finishing exception is detected while processing the data stream. See “Finishing Fidelity Triplet X'88'”. [MODCA-5-1498]|
| X'96' | | **CMR Tag Fidelity** Optional. May occur once. Specifies the actions to be taken by the presentation process when a CMR tag exception is detected while processing the data stream. See “CMR Tag Fidelity Triplet X'96'”. [MODCA-5-1499]|

**Application Note:**  Some presentation platforms allow presentation fidelity parameters to be specified in the print request. For example, in the MVS™ environment, invalid character exceptions and positioning exceptions may be blocked with a data check parameter in the JCL. In the OS/400 ® environment, a print fidelity indicator may be used to specify whether absolute fidelity is required, so that the presentation process can determine how to continue following exceptions such as font not available, duplexing not available, media source not available, and data stream function not available. Print request fidelity specifications are outside the scope of the MO:DCA architecture. It is up to the print requestor to ensure that fidelity specifications in the form map are consistent and compatible with fidelity specifications in the print request. If there is a conflict between the fidelity specification in the form map and the fidelity specification in the print request, the presentation process may terminate processing of the print job. [MODCA-5-1500]


### Page Descriptor (PGD)

The Page Descriptor structured field specifies the size and attributes of a page or overlay presentation space. [MODCA-5-1501]

#### PGD (X'D3A6AF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A6AF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1502]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1503]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1504]|
| 0 | CODE | **XpgBase** | X'00'–X'01' | Page unit base for the X axis | M | X'07' [MODCA-5-1505]|
| 1 | CODE | **YpgBase** | X'00'–X'01' | Page unit base for the Y axis | M | X'07' [MODCA-5-1506]|
| 2–3 | UBIN | **XpgUnits** | 1–32,767 | Page units per unit base for the X axis | M | X'06' [MODCA-5-1507]|
| 4–5 | UBIN | **YpgUnits** | 1–32,767 | Page units per unit base for the Y axis | M | X'06' [MODCA-5-1508]|
| 6–8 | UBIN | **XpgSize** | 1–32,767 | Page extent for the X axis | M | X'06' [MODCA-5-1509]|
| 9–11 | UBIN | **YpgSize** | 1–32,767 | Page extent for the Y axis | M | X'06' [MODCA-5-1510]|
| 12–14 | | **Reserved** | | Should be binary zero | M | X'06' [MODCA-5-1511]|
| 15–n | | **Triplets** | | See PGD Semantics for triplet applicability | O | X'10' [MODCA-5-1512]|

#### PGD Semantics

**XpgBase** Specifies the unit base for the X axis of the page or overlay coordinate system:
*   X'00' 10 inches
*   X'01' 10 centimeters

**Note:** A X'01' exception condition exists if the **XpgBase** and **YpgBase** values are not identical.

**YpgBase** Specifies the unit base for the Y axis of the page or overlay coordinate system:
*   X'00' 10 inches
*   X'01' 10 centimeters

**XpgUnits** Specifies the number of units per unit base for the X axis of the page or overlay coordinate system.

**YpgUnits** Specifies the number of units per unit base for the Y axis of the page or overlay coordinate system.

**XpgSize** Specifies the extent of the X axis of the page or overlay coordinate system. This is also known as the page or overlay's X-axis size.

**YpgSize** Specifies the extent of the Y axis of the page or overlay coordinate system. This is also known as the page or overlay's Y-axis size.

**Note:** If the sum of the page or overlay origin offset and the page or overlay extent exceeds the size of the including presentation space in either the X or Y direction, all of the page or overlay will not fit on the including presentation space. The including presentation space in this case is the medium presentation space. If an attempt is made to actually present data in the portion of the page or overlay that falls outside the including presentation space, that portion of the data is not presented, and a X'01' exception condition exists. [MODCA-5-1513]

**Application Notes:**

1.  Some AFP print servers require that the measurement units in the PGD match the measurement units in the **Presentation Text Descriptor (PTD)** when the latter is included in the AEG for a page. It is therefore strongly recommended that whenever the PTD is included in the AEG, the same measurement units are specified in both the PTD and PGD. [MODCA-5-1514]
2.  The IS/1 interchange set definition limits the page size to 22.75 inches in the X and Y directions; the IS/3 interchange set definition does not. To specify a larger page size, 240 units per inch should be specified in the PGD for the page measurement units. Using a range of 1 to 32,767, this allows a maximum page size in the X and Y directions of 136.5 inches, is supported by all IPDS printers, and keeps the complete page presentation space within the range of 2-byte addressing parameters in the IPDS architecture. [MODCA-5-1515]

**Triplets** Appear in the Page Descriptor structured field as follows: [MODCA-5-1516]

| **Triplet** | **Type** | **Usage** [MODCA-5-1517]|
| :--- | :--- | :--- [MODCA-5-1518]|
| X'4E' | | **Color Specification** Optional. May occur once. Specifies a color for the page or overlay presentation space. The color specification defines a color space, the syntax for specifying color values in the color space, and the actual color value. When this triplet is specified on a page or overlay presentation space, the complete presentation space becomes foreground data that is colored with the specified color before any object data is added to the presentation space. If the default mixing rules are used, the page or overlay presentation space, when it becomes foreground data, overpaints (covers) any data that is underneath. See “Color Specification Triplet X'4E'”. [MODCA-5-1519]|
| X'70' | | **Presentation Space Reset Mixing** Optional. May occur once. If this triplet specifies a reset to the color of medium (BgMxFlag=B'1'), the reset takes place at the point in the data stream where the triplet occurs. This triplet may not appear in the Page Descriptor structured field with a Presentation Space Mixing Rules triplet. See “Presentation Space Reset Mixing Triplet X'70'”. [MODCA-5-1520]|
| X'71' | | **Presentation Space Mixing Rules** Optional. May occur once. This triplet may not appear in the Page Descriptor structured field with a Presentation Space Reset Mixing triplet. See “Presentation Space Mixing Rules Triplet X'71'”. [MODCA-5-1521]|

**Implementation Note:**  The Presentation Space Mixing Rules (X'71') triplet is currently not used in AFP environments.

**Architecture Note:**  Triplets that affect the page or overlay presentation space are processed in the order in which they occur on the PGD. For example, if a Presentation Space Reset Mixing (X'70') triplet on the PGD is followed by a Color Specification (X'4E') triplet, the presentation space is colored with the color specified in the X'4E' triplet and covers any data underneath it regardless of whether the X'70' triplet specified “reset to color of medium” or “do not reset to color of medium”. If a Color Specification (X'4E') triplet is followed by a X'70' triplet, and if the X'70' triplet specified “reset to color of medium”, the presentation space is colored with color of medium. If the X'70' triplet specified “do not reset to color of medium”, the X'70' triplet does not change the presentation space and it remains foreground data colored with the color specified by the X'4E' triplet. [MODCA-5-1522] [MODCA-5-1523]

#### PGD Exception Condition Summary

X'01' This exception condition exists when:
*   The **XpgBase** and **YpgBase** values are not identical. [MODCA-5-1524]
*   An attempt is made to present data outside the medium presentation space. See the note under **YpgSize** for details. [MODCA-5-1525]
*   The PGD structured field contains both a **Presentation Space Reset Mixing** triplet and a **Presentation Space Mixing Rules** triplet. [MODCA-5-1526] [MODCA-5-1527]


### Page Position (PGP)

#### Page Position (PGP) Format 2

The Page Position structured field specifies the position and orientation of a page's presentation space on the medium presentation space for the physical medium. The PGP may be located in a medium map or in the document environment group of a form map. When present in the active medium map, it overrides a PGP in the document environment group of the form map. If N-up partitioning is specified by the Medium Modification Control structured field in the active medium map, the medium presentation spaces on the front and back sides of a sheet are divided into N partitions; and the Page Position structured field specifies the partition into which each page is mapped and with respect to which the page presentation space is positioned and oriented. The N-up page-to-partition mapping can be specified in two mutually exclusive ways:

*   **Default N-up page placement.** Pages are processed in the order in which they appear in the data stream and are placed into consecutively-numbered partitions, that is, the first page is placed into partition 1, the second page is placed into partition 2, the third page is placed into partition 3, and the 4th page is placed into partition 4. Partition numbering for various media is shown in Figure 61 to Figure 72. [MODCA-5-1528]
*   **Explicit N-up page placement.** Pages are processed in the order in which they appear in the data stream and are placed into the partition that is explicitly specified by the repeating group for the page. Multiple pages may be placed into the same partition. If N-up simplex is specified, the Page Position structured field must contain N repeating groups, one for each page on the sheet-side. If N-up duplex is specified, the Page Position structured field must contain 2N repeating groups, one for each page on the sheet. [MODCA-5-1529] [MODCA-5-1530]

#### PGP (X'D3B1AF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B1AF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1531]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1532]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1533]|
| 0 | CODE | **Constant** | X'01' | Reserved constant; must be X'01' | M | X'06' [MODCA-5-1534]|

*One or more repeating groups in the following format:*

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** |
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0 | UBIN | **RGLength** | X'0A'–X'0C' | Length of each repeating group | M | X'06' [MODCA-5-1535]|
| 1–3 | SBIN | **XmOset** | -32,768–32,767 | Xm coordinate of page presentation space origin | M | X'06' [MODCA-5-1536]|
| 4–6 | SBIN | **YmOset** | -32,768–32,767 | Ym coordinate of page presentation space origin | M | X'06' [MODCA-5-1537]|
| 7–8 | CODE | **PGorient** | | The page presentation space X-axis rotation from the X axis of the medium presentation space | M | X'06' [MODCA-5-1538]|
| 9 | CODE | **SHside** | | Sheet side and partition selection | M | X'06' [MODCA-5-1541]|
| 10 | BITS | **PgFlgs** | | Flags | O | X'02' [MODCA-5-1542]|
| 11 | CODE | **PMCid** | 0–127, X'FF' | Page Modification Control identifier | O | X'02' [MODCA-5-1543]|

#### PGP Semantics

The Page Position structured field contains repeating groups that are used to map pages to the medium presentation space or to partitions on the medium presentation space. The number of repeating groups that may appear on the Page Position structured field is determined as follows:

*   If N-up is not specified by the Medium Modification Control structured field in the active medium map, the [MODCA-5-1544] Page Position structured field contains one repeating group for the front sheet-side for simplex printing, and two repeating groups, one for the front sheet-side and one for the back sheet-side for duplex printing. Each repeating group specifies the offset, orientation, and optional modifications for the page that is to be presented on the sheet-side. The page offset is measured with respect to the medium presentation space origin, and the page orientation is measured with respect to the medium presentation space X axis. Pages are processed sequentially as they appear in the data stream. For duplex printing, the front sheet-side is always processed before the back sheet-side, regardless of the order of the two repeating groups. [MODCA-5-1545]
*   If N-up is specified by the Medium Modification Control structured field in the active medium map and the [MODCA-5-1546] default N-up page placement is desired, the Page Position structured field contains one repeating group for the front sheet-side for simplex printing, and two repeating groups, one for the front sheet-side and one for the back sheet-side for duplex printing. Each repeating group must specify default N-up page placement, and the specified page offset, page orientation, and page modifications apply to all pages placed on the sheet-side. The page offset is measured with respect to the origin of the partition into which the page is placed, and the page orientation is measured with respect to the medium presentation space X axis. Pages are processed sequentially as they appear in the data stream. For duplex printing, the front sheet-side is always processed before the back sheet-side, regardless of the order of the two repeating groups.
*   If N-up is specified by the Medium Modification Control structured field in the active medium map and if [MODCA-5-1547] explicit N-up page placement is desired, the Page Position structured field contains N repeating groups for simplex printing, and 2N repeating groups for duplex printing. Pages are processed sequentially as they appear in the data stream using consecutive PGP repeating groups. The first page is processed using the first repeating group, the second page is processed using the second repeating group, and so on. Each repeating group must specify a sheet-side, a partition number in the range from 1 to N, a page offset, and a page orientation. Each repeating group may also specify optional modifications to be applied to the page. Multiple repeating groups may specify the same partition number. The page offset is measured with respect to the origin of the partition specified by the repeating group. The page orientation is measured with respect to the medium presentation space X axis.

**Notes:**

1.  The processing of PGP repeating groups is driven by pages in the data stream. If page n is the last page in [MODCA-5-1548] a document, the repeating group used to present page n is the last repeating group that is processed. Similarly, if page n is followed by an IMM, the repeating group used to present page n is the last repeating group processed before the new medium map is invoked. As a result, if a PGP repeating group is to present a PMC overlay without any page data, placing it before the last repeating group that presents page data will ensure that this repeating group is processed and the PMC overlay is presented.
2.  Pages can be placed in the partitions that correspond to default page placement but still be individually [MODCA-5-1549] offset, oriented, and modified by specifying explicit page placement and sequential partition numbers in the repeating groups. For example, for 2-up duplex, the first repeating group specifies **SHside** = X'10', the second repeating group specifies **SHside** = X'20', the third repeating group specifies **SHside** = X'11', and the fourth repeating group specifies **SHside** = X'21'.

**RGLength** Length of each repeating group. Set to 10, 11, or 12.

**XmOset** Offset of the page's presentation space origin along the Xm axis of the medium presentation space using the measurement units specified in the Medium Descriptor structured field. If N-up partitioning is specified by the Medium Modification Control structured field in the active medium map, the offset is measured from the partition origin.

**YmOset** Offset of the page's presentation space origin along the Ym axis of the medium presentation space using the measurement units specified in the Medium Descriptor structured field. If N-up partitioning is specified by the Medium Modification Control structured field in the active medium map, the offset is measured from the partition origin.

**PGorient** Specifies the amount of clockwise rotation of the page presentation space X axis, Xp, about the page presentation space origin, relative to the Xm axis of the medium presentation space. The rotation of the Y axis of the page presentation space is always 90° greater than the rotation of the X axis. The allowed rotations are:

| **Value** | **Description** |
| :--- | :--- |
| X'0000' | 0° rotation |
| X'2D00' | 90° rotation |
| X'5A00' | 180° rotation |
| X'8700' | 270° rotation [MODCA-5-1550] |

**Note:**  If the page rotation is such that the sum of the page origin offset and the page extent exceeds the size of the including medium presentation space in either the Xm or Ym direction, all of the page presentation space will not fit on the medium presentation space. If an attempt is made to actually present data in the portion of the page presentation space that falls outside the medium presentation space, that portion of the data is not presented, and a X'01' exception condition exists.

**SHside** Specifies the sheet side to which the repeating group applies and the manner in which pages are placed on the sheet side. If N-up partitioning is specified by the Medium Modification Control structured field in the active medium map, this parameter specifies the N-up page placement. It may specify the default N-up page placement, where pages are placed into consecutive partitions, or it may specify explicit N-up page placement, where pages are placed into explicitly-specified partitions.
Value Description
X'00' Single page placed on front sheet-side if no N-up specified, default page
placement on front sheet-side if N-up specified.
X'01' Single page placed on back sheet-side if no N-up specified, default page
placement on back sheet-side if N-up specified.
**Note:**  If default N-up page placement is specified for the front sheet-side, it
must also be specified for the back sheet-side. With default N-up page
placement, one repeating group (simplex) or two repeating groups
(duplex) are specified, and the specified offset and orientation apply to
all pages mapped to the sheet-side.
X'10' Explicit N-up page placement; page is mapped to partition 1, front sheet-side.
X'11' Explicit N-up page placement; page is mapped to partition 1, back sheet-side.
X'20' Explicit N-up page placement; page is mapped to partition 2, front sheet-side.
X'21' Explicit N-up page placement; page is mapped to partition 2, back sheet-side.
X'30' Explicit N-up page placement; page is mapped to partition 3, front sheet-side.
X'31' Explicit N-up page placement; page is mapped to partition 3, back sheet-side.
X'40' Explicit N-up page placement; page is mapped to partition 4, front sheet-side.
X'41' Explicit N-up page placement; page is mapped to partition 4, back sheet-side.
**Application Note:**  IPDS printers require that pages be contained within their partition if
default N-up page placement is specified, otherwise an exception is generated. This
restriction does not exist if explicit N-up page placement is specified, that is, pages may
overflow their partition without necessarily causing an exception.
PgFlgs Specify additional presentation controls for the partition. Bits 0–2 of this parameter are used
only if N-up is specified by the Medium Modification Control structured field in the active
**PgFlgs** Flags that specify how variable page data and PMC overlays are placed into a partition. For default N-up page placement, bits 0–2 are ignored, and the architected default for **PgFlgs** bits 0–2 is B'000' (present variable page data, present PMC overlays, position PMC overlays with respect to the page origin).

*   **Bit 0: Variable page data** [MODCA-5-1551]
    *   B'0' Present variable page data in the partition
    *   B'1' Do not present variable page data in the partition. This causes a blank page to be presented in the partition.
*   **Bit 1: PMC overlays** [MODCA-5-1552]
    *   B'0' Present PMC overlays in partition
    *   B'1' Do not present PMC overlays in partition [MODCA-5-1553]
*   **Bit 2: PMC overlay position** [MODCA-5-1554]
    *   B'0' The offset specified for PMC overlays is measured with respect to the page origin using the measurement units specified in the PMC structured field. If no measurement units are specified in the PMC, the measurement units specified in the **MDD** structured field are used.
    *   B'1' The offset specified for PMC overlays is measured with respect to the partition origin using the measurement units specified in the PMC structured field. If no measurement units are specified in the PMC, the measurement units specified in the **MDD** structured field are used. The measurement of the PMC overlay offset is done with the page in the 0° rotation. This fixes the position of the overlay origin with respect to the page origin along the Xpg and Ypg axes, or along extensions of the Xpg and Ypg axes in the negative direction. If a non-zero degree page rotation is specified, each PMC overlay is positioned by rotating the page coordinate system, extending the Xpg and Ypg axes in the negative direction, and placing the PMC overlay origin in the extended (Xpg,Ypg) coordinate system at the same position, relative to the page, that it occupied in the 0° page rotation.
*   **Bit 3: Page view control** [MODCA-5-1555]
    *   B'0' The data presented by this repeating group is intended for viewing. This is the architected default if the **PgFlgs** parameter is not specified.
    *   B'1' The data presented by this repeating group is not intended for viewing.
*   **Bits 4–7: Reserved**; all bits should be B'0'.

**Notes:**

1.  If this optional parameter is omitted, the **PMCid** parameter must be omitted as well and the [MODCA-5-1556] architected default for **PgFlgs** bits 0–3 is B'0000', that is, present variable page data in the partition, present all PMC overlays in the active medium map in the partition, position PMC overlays with respect to the page origin, and view the data presented by this repeating group.
2.  PMC overlays are page overlays whether they are positioned with respect to the page [MODCA-5-1557] origin or the partition origin. PMC overlays rotate with the page if a non-zero page rotation is specified by the **PGorient** parameter. Media level controls, such as the **Constant Forms Control (X'F9')** keyword in the **MMC**, treat PMC overlays as variable page data.
3.  The functions enabled at the page level by bits 0–1 of this parameter are analogous to the [MODCA-5-1558] functions provided by the **Constant Forms Control (X'F9')** keyword and the **Medium Overlay Local ID (X'F2')** keyword in the **MMC** at the medium level. When the **PgFlgs** parameter, the X'F9' keyword, and the X'F2' keyword are present, they interact as follows:
    *   The **Constant Forms Control (X'F9')** keyword is not supported with N-up explicit page [MODCA-5-1559] placement and is ignored if it occurs. Similar functionality can be achieved for a sheet side by explicitly including the medium overlay as a PMC overlay on a partition without any variable page data.
    *   When N-up with default page placement is specified, this keyword controls the application of variable page data that may include PMC overlays to a sheet side, while the **PgFlgs** parameter controls the application of variable page data and PMC overlays to a partition.
    *   When the X'F9' keyword specifies that no variable page data is to be applied to the sheet side, it overrides the page level specification in the **PgFlgs** parameter for that sheet side. The resulting effect is the same as if the PGP repeating group for partitions on that sheet side specified bits 0,1 = B'11' (do not present variable page data in the partitions and do not present PMC overlays in the partitions). In that case, the medium overlay is applied to the sheet side but neither variable page data nor PMC overlays are applied to any partition on the sheet side. [MODCA-5-1560]
    *   When the X'F9' keyword specifies that variable page data including PMC overlays can be applied to the sheet side, the **PgFlgs** parameter determines whether variable page data and PMC overlay data is placed into partitions on that sheet side.
    *   With default N-up page placement, if a sheet-side contains only constant data (MMC **Constant Forms Control (X'F9')** keyword is specified or PGP **PgFlgs** bit 0 = B'1'), it is built as long as: [MODCA-5-1561]
        – At least a single page is placed anywhere on that sheet; or
        – The other sheet-side also contains only constant data
    *   The **Medium Overlay Local ID (X'F2')** keyword controls the application of medium [MODCA-5-1562] overlays to the sheet side, while the **PgFlgs** parameter controls the application of PMC overlays to the page in a partition. These two overlay types are included or omitted independently.
    *   Note that medium overlays are only guaranteed to be presented on a sheet side if a page, which could be a blank page generated by setting **PgFlgs** bit 0 = B'1', is also presented on the sheet side, or if the **Constant Forms Control (X'F9')** keyword specifies X'01' (present only medium overlay data) for that sheet side.
    *   For example, if the PGP specifies explicit page placement but does not contain a repeating group for a back-side partition, and if the **MMC** for the back side copy subgroup calls out a medium overlay with the X'F2' keyword, this medium overlay will not be presented.
    *   In general, if the **Constant Forms Control (X'F9')** keyword is not specified for a sheet-side, any medium overlays specified for that sheet-side are only presented if at least a single page is placed on the same sheet-side. Note that this page could be a page with variable data, a blank page with only PMC overlays, or even a blank page without PMC overlays, as determined by the setting of the **PgFlgs** parameter. [MODCA-5-1563]

**Application Note:**  Bits 0–1 of the **PgFlgs** parameter can be used to place a blank page into a partition or to fill a partition with constant data specified in a PMC overlay.

**PMCid** Identifies a Page Modification Control (PMC) structured field in the active medium map that specifies modifications to be applied to the page before it is placed in the partition. If this parameter is not specified on a repeating group, or if the parameter specifies X'FF', all modifications specified by all PMCs in the active medium map are applied to the page. If this parameter is specified on a repeating group, only the modifications included by the selected PMC are applied to the page. If the medium map does not contain a PMC with the specified ID, no PMC modifications are applied. This parameter is used only if N-up is specified by the **Medium Modification Control** structured field in the active medium map. If N-up is not specified and this parameter is present, it is ignored, and all modifications specified by all PMCs in the active medium map are applied to the page. PMC structured fields in the active medium map may specify Preprinted Form overlays (PMC-PFOs). Only one PMC-PFO may be included on the page processed by this PGP repeating group; if more than one PMC-PFO is referenced, the additional PMC-PFOs are ignored. If a specific PMC ID is not selected for a page, and therefore all PMCs in the active medium map are applied to the page, only the first PMC-PFO is applied, all additional PMC-PFOs are ignored. Note that if the active medium map specifies a Medium PFO (M-PFO) for a sheet-side, all PMC-PFOs for pages on that sheet-side are ignored.

**Notes:**

1.  If the **PMCid** parameter is included in a repeating group, the optional **PgFlgs** positional parameter is mandatory for that repeating group. [MODCA-5-1564]
2.  All PMC overlays that are not PMC-PFOs are presented on the page presentation space [MODCA-5-1565] before any variable page data is presented. If a PMC-PFO is included, it is presented on the page presentation space after all other data has been presented, using the special mixing rules defined for PMC-PFOs. See “Mixing Rules”. [MODCA-5-1566]
3.  All PMC overlays included by a PGP repeating group must be mapped with an **MPO** structured field. [MODCA-5-1567]

**Application Note:**  The N-up function provided by the PGP structured field provides powerful and flexible functionality for placing multiple pages on a single sheet. Not all of this functionality maps easily to a viewing environment, which is normally page-based. When creating N-up applications that are to be both printed and viewed, you should follow these guidelines:

*   Do not use medium overlays. Medium overlays are tied to a sheet-side, not to a page, and should be [MODCA-5-1568] replaced with PMC overlays, which can be tied to a page. If medium overlays are used, the page and PMC overlay position and rotation with respect to the medium origin must be preserved. This may generate blank space on the display screen and may even cause the page and PMC overlays to position or rotate off the screen. To avoid these problems, some viewing applications may not support medium overlays when presenting N-up data.
*   Generate the PGP so that all data that must be displayed with a particular page is referenced by the [MODCA-5-1569] PGP repeating group that is used to process the page.
*   Avoid creating special effects by overlapping two or more pages since these effects will not be [MODCA-5-1570] displayed by a page-based N-up viewing system.
*   Avoid splitting page content across more than one page, since this would require a multi-page viewing [MODCA-5-1571] capability.

#### PGP Exception Condition Summary

X'01' This exception condition exists when:
*   One repeating group specifies default N-up page placement and another repeating group [MODCA-5-1572] specifies explicit N-up page placement.
*   The Page Position structured field contains an invalid number of repeating groups for the [MODCA-5-1573] given N-up and simplex/duplex specification.
*   Explicit N-up page placement is specified, but the active medium map does not specify N-up [MODCA-5-1574] partitioning.
*   A repeating group specifies invalid data, such as a back sheet-side partition when the active [MODCA-5-1575] medium map specifies simplex, or partition #3 when the active medium map specifies 2-up.
Partition Numbering for N-up
Partition numbering for various media is shown in Figure 61 to Figure 72. The
numbering depends on whether 1-up, 2-up, 3-up, or 4-up is specified, and on how the medium presentation
space is oriented on the physical medium. The medium presentation space orientation is specified by the
Medium Orientation (X'68') triplet on the Medium Descriptor structured field to be Portrait (X'00'), Landscape
(X'01'), Reverse Portrait (X'02'), Reverse Landscape (X'03'), Portrait 90 (X'04'), or Landscape 90 (X'05'). Note
that when duplexing, the location of the partitions on the back sheet-side relative to the location of the
partitions on the front sheet-side is dependent on whether normal duplexing (turning the media around the Y
m
axis) or tumble duplexing (turning the media around the X m axis) is specified.
Legend: The small circles in Figure 61 to Figure 72 represent holes punched
through the sheets and are intended to show how the sheets were flipped from front-side to back-side.
All sheets have three holes punched along one of the long sides and one hole punched along the other
long side. The small square indicates the medium origin, and the arrow indicates the direction of the
medium X
m axis.
**Figure 61.**  1-up Partition Numbering, Front Sheet-Side [MODCA-5-1576]
### Page Position (PGP)


**Figure 62.**  2-up Partition Numbering, Front Sheet-Side
**Figure 63.**  3-up Partition Numbering, Front Sheet-Side [MODCA-5-1577]
### Page Position (PGP)


**Figure 64.**  4-up Partition Numbering, Front Sheet-Side
**Figure 65.**  1-up Partition Numbering, Back Sheet-Side, Normal Duplex [MODCA-5-1578]
### Page Position (PGP)


**Figure 66.**  2-up Partition Numbering, Back Sheet-Side, Normal Duplex
**Figure 67.**  3-up Partition Numbering, Back Sheet-Side, Normal Duplex [MODCA-5-1579]
### Page Position (PGP)


**Figure 68.**  4-up Partition Numbering, Back Sheet-Side, Normal Duplex
**Figure 69.**  1-up Partition Numbering, Back Sheet-Side, Tumble Duplex [MODCA-5-1580]
### Page Position (PGP)


**Figure 70.**  2-up Partition Numbering, Back Sheet-Side, Tumble Duplex
**Figure 71.**  3-up Partition Numbering, Back Sheet-Side, Tumble Duplex [MODCA-5-1581]
### Page Position (PGP)


**Figure 72.**  4-up Partition Numbering, Back Sheet-Side, Tumble Duplex [MODCA-5-1582]
### Page Position (PGP)


### Page Modification Control (PMC)

The Page Modification Control structured field specifies modifications to be applied to a page presented on a physical medium.

If the ID of a specific PMC is selected in the PGP structured field of the active medium map in N-up mode, only the modifications specified by that PMC are applied to pages placed on the medium. If a specific PMC is not selected in N-up mode, all modifications specified by all PMCs in the active medium map are applied to pages placed on the medium.

A PMC structured field may specify only one Preprinted Form Overlay (PMC-PFO); if it specifies more than one, the additional PMC-PFOs are ignored. If a specific PMC ID is not selected for a page, and therefore all PMCs in the active medium map are applied to the page, only the first PMC-PFO is applied; all additional PMC-PFOs are ignored. Note that if the active medium map specifies a Medium PFO (M-PFO) for a sheet-side, all PMC-PFOs for pages on that sheet-side are ignored. [MODCA-5-1583]

#### PMC (X'D3A7AF') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A7AF'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1584]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1585]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1586]|
| 0 | CODE | **PMCid** | 0–127 | Page Modification Control identifier | M | X'06' [MODCA-5-1587]|
| 1 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-1588]|
| 2–n | | **Triplets** | | See PMC Semantics for triplet applicability | O | X'10' [MODCA-5-1589]|

#### PMC Semantics

**PMCid** Page Modification Control Identifier. The identifier for the modifications specified by this structured field.

**Triplets** Appear in the Page Modification Control structured field as follows: [MODCA-5-1590]

| **Triplet** | **Type** | **Usage** [MODCA-5-1591]|
| :--- | :--- | :--- [MODCA-5-1592]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur multiple times. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1593] [MODCA-5-1594]|
| X'4B' | | **Measurement Units** Optional. May occur once. Specifies the units of measure to be used for positioning included objects on the page. See “Measurement Units Triplet X'4B'”. If this triplet is omitted, the units of measure specified in the Medium Descriptor (MDD) that is in the same medium map as the PMC are used to position included objects on the page. [MODCA-5-1595]|
| X'6C' | | **Resource Object Include** Optional. May occur more than once, but only one occurrence can specify object type X'DC' - Preprinted Form Overlay (PFO). If this triplet is specified more than once with object type X'DC', the additional occurrences are ignored. Identifies an object to be included on the page at a specified position. See “Resource Object Include Triplet X'6C'”. [MODCA-5-1596] [MODCA-5-1597]|

**Note:** Overlays that are included on a page using the PMC structured field are called PMC overlays. If the overlay is a Preprinted Form (PFO) overlay, it is called a PMC-PFO. Each overlay included on a page with a PMC must first be mapped to a local ID with an **MPO** in the medium map containing the PMC. [MODCA-5-1598]




### Preprocess Presentation Object (PPO)

The Preprocess Presentation Object structured field specifies presentation parameters for a data object that has been mapped as a resource. These parameters allow the presentation device to preprocess and cache the object so that it is in presentation-ready format when it is included with a subsequent include structured field in the document. Such preprocessing may involve a rasterization or RIP of the object, but is not limited to that. The resource is identified with a file name, the identifier of a begin structured field for the resource, or any other identifier associated with the resource. The referenced resource and all required secondary resources must previously have been mapped with an MDR or an MPO in the same environment group.

Preprocessing is not supported for objects that are included with structures that are outside the document. Examples of such objects are medium overlays and PMC overlays, both of which are included with structures in the form map.

#### PPO (X'D3ADC3') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3ADC3'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1599]

*One to 254 repeating groups in the following format:* [MODCA-5-1601]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1600]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: |
| 0–1 | UBIN | **RGLength** | 18–(n+1) | Total length of this repeating group | M | X'06' [MODCA-5-1602]|
| 2 | CODE | **ObjType** | X'92', X'DF', X'FB' | Object type | M | X'06' [MODCA-5-1603]|
| 3–4 | | **Reserved** | | Should be zero | M | X'06' [MODCA-5-1604]|
| 5 | BITS | **ProcFlgs** | | Processing flags; see PPO Semantics for bit definitions | M | X'06' [MODCA-5-1605]|
| 6–8 | SBIN | **XocaOset** | -32,768–32,767, X'FFFFFF' | X axis origin for object content | M | X'06' [MODCA-5-1606]|
| 9–11 | SBIN | **YocaOset** | -32,768–32,767, X'FFFFFF' | Y axis origin for object content | M | X'06' [MODCA-5-1607]|
| 12–n | | **Triplets** | | See PPO Semantics for triplet applicability | M | X'14' [MODCA-5-1608]|

#### PPO Semantics

**RGLength** Specifies the total length of the repeating group, including the length of the RGLength parameter itself.

**ObjType** Identifies the type of object being referenced. [MODCA-5-1609]
*   X'92' **Other object data.** The object data to be preprocessed is a non-OCA paginated presentation object. The object data is characterized and identified by a mandatory **Object Classification (X'10')** triplet, which must specify the registered OID for the object type and must characterize the object as being a presentation object. See “Non-OCA Object Types Supported by the IOB Structured Field” for a list of object types that may be included in MO:DCA data streams. To see which encoded object-type OIDs are supported by the presentation system, consult the product documentation.

    **Application Note:** If the object is installed in a resource library using a Resource Access Table (RAT), it must not be wrapped with a MO:DCA object container envelope, that is, it must be installed in its raw source format.
*   X'DF' Overlay object.
*   X'FB' Image (IOCA) object with MO:DCA object syntax as defined in “Image Objects”.
*   All others Reserved

**ProcFlgs** Specify additional processing information for the PPO structured field.

*   **Bits 0–3: Object Orientation**
    Specify one or more orientations, measured in a clockwise direction, of the X-axis of the object with respect to the leading edge of the media.

    **Application Note:**  Many factors, such as media selection, media side, media loading, media orientation, page rotation, and object area rotation affect the orientation of an object with respect to the media leading edge. Proper specification of this parameter may require visual inspection of physical output.

    *   **Bit 0: 0 degrees** [MODCA-5-1610]
        *   B'0' Do not preprocess the object at 0 degree orientation.
        *   B'1' Preprocess and cache the object at 0 degree orientation with respect to the leading edge of the media.
    *   **Bit 1: 90 degrees** [MODCA-5-1611]
        *   B'0' Do not preprocess the object at 90 degree orientation.
        *   B'1' Preprocess and cache the object at 90 degree orientation with respect to the leading edge of the media.
    *   **Bit 2: 180 degrees** [MODCA-5-1612]
        *   B'0' Do not preprocess the object at 180 degree orientation.
        *   B'1' Preprocess and cache the object at 180 degree orientation with respect to the leading edge of the media.
    *   **Bit 3: 270 degrees** [MODCA-5-1613]
        *   B'0' Do not preprocess the object at 270 degree orientation.
        *   B'1' Preprocess and cache the object at 270 degree orientation with respect to the leading edge of the media.
    If no orientations are specified, the object is preprocessed at a 0 degree orientation with respect to the leading edge of the media.

*   **Bit 4: Preprocess all objects** [MODCA-5-1614]
    If this PPO references a file with **ObjType** = X'92' that contains multiple pages or paginated objects, specifies whether only the selected paginated object or all paginated objects in the file should be preprocessed. This bit is ignored in all other cases.
    *   B'0' Preprocess only the selected paginated object.
    *   B'1' Preprocess all paginated objects in the file.
*   **Bits 5–7: Reserved**; all bits must be B'0'.

**XocaOset** Used in position and position and trim mappings to specify the offset along the X axis of the object area coordinate system, Xoa, to the X origin of the object content. The measurement units for this parameter are specified with a **Measurement Units (X'4B')** triplet. A value of X'FFFFFF' indicates that the X axis offset is not specified, therefore the offset value (-1) is not included in the allowed range. This parameter is ignored for **ObjType** = X'DF'—Overlay.

**YocaOset** Used in position and position and trim mappings to specify the offset along the Y axis of the object area coordinate system, Yoa, to the Y origin of the object content. The measurement units for this parameter are specified with a **Measurement Units (X'4B')** triplet. A value of X'FFFFFF' indicates that the Y axis offset is not specified, therefore the offset value (-1) is not included in the allowed range. This parameter is ignored for **ObjType** = X'DF'—Overlay.

**Notes:**

1.  The object content is developed in the data object presentation space; within the context [MODCA-5-1615] of this structured field the two terms are synonymous.
2.  The **XocaOset** and **YocaOset** parameters are treated as a pair. If one is assigned the [MODCA-5-1616] value X'FFFFFF' (not specified), the other is treated that way as well, regardless of its assigned value.
Triplets Appear in the Preprocess Presentation Object structured field repeating groups as follows: [MODCA-5-1617]



| Triplet | Type | Usage [MODCA-5-1618]|
| --- | --- | --- [MODCA-5-1619]|
| X'01' | | Coded Graphic Character Set Global Identifier Optional. May occur more than once in each repeating group. Specifies encoding for structured field parameters defined with a CHAR data type. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1620]|
**Implementation Note:**  Not all AFP servers support the
inheritance of encoding scheme from higher levels of the
document hierarchy, therefore it is recommended that
this triplet be specified directly on the PPO if required by
a parameter such as the FQN type X'DE' triplet.
X'02' Fully Qualified Name Mandatory. Must occur once in each repeating group.
Specifies the reference to the resource object to be
preprocessed. See “Fully Qualified Name Triplet X'02'”.
The Fully Qualified Name types that may appear are:
X'84'—Begin Resource Object Reference, which is used
to preprocess an overlay or an IOCA image object. The
GID is used to locate the resource object in the resource
hierarchy, which may include the presentation device,
and must match the identifier for an object or a X'01'
exception condition exists. This FQN type is used with
ObjType = X'DF'—Overlay, and with ObjType = X'FB'—
IOCA image.
X'CE'—Other Object Data Reference, which is used to
preprocess a data object whose format may or may not
be defined by an AFP architecture. The GID is used to
locate the object in the resource hierarchy, which may
include the presentation device, and must match the
identifier for an object or a X'01' exception condition
exists. This FQN type is used with ObjType = X'92'—
other object data.
The reference in the above FQN triplets may be specified
in one—and only one—of the following formats:
If FQNFmt = X'00', the reference is made with a
character-encoded name. See “External Resource
Naming Conventions” for a description of the
naming conventions used in AFP environments.
The object reference must be specified in the same
manner, using the same FQNFmt, as the MDR or MPO that
maps the object as a resource. [MODCA-5-1621]



| Triplet | Type | Usage [MODCA-5-1622]|
| --- | --- | --- [MODCA-5-1623]|
| X'02' | | Fully Qualified Name Optional. May occur more than once in each repeating group. See “Fully Qualified Name Triplet X'02'”. The Fully Qualified Name type that may appear is: [MODCA-5-1624]|
| X'DE'—Data | | Object External Resource Reference. Specifies the external identifier of a resource object that is used by the object to be preprocessed. The identifier is used by the presentation system to locate the resource object in the resource hierarchy. The identifier may be specified in one of the following two formats, but not in both formats: If FQNFmt = X'00', the identifier is a character-encoded name. See “External Resource Naming Conventions” for a description of the naming conventions used in AFP environments. If FQNFmt = X'10', the identifier is an ASN.1 OID encoded using the definite short form. This format provides a unique and system-independent method to identify and reference an object. It may be used to select resources that are resident in the presentation device. Such an identifier is referred to as an object OID. [MODCA-5-1625]|
**Architecture Note:**  The FQN type X'DE' triplet with
FQNFmt = X'10' (OID) is only used to reference the
CMYK SWOP and CMYK Euroscale resident color
profiles registered in the MO:DCA Registry; see
“Resident Color Profile Identifiers”.
If the data object that requires this resource is also
processed as a resource, the term secondary resource is
applied to the resource used by the data object. See
“Secondary Resource Objects”. The secondary
resource reference must be specified in the same manner,
using the same FQNFmt, as the MDR that maps the
secondary resource.
If the object to be preprocessed also references the
secondary resource with an internal identifier, this identifier
must be specified on the PPO with a FQN type X'BE' triplet
that immediately follows the FQN type X'DE' triplet. The
paired triplets map the internal identifier to the external
identifier.
**Note:**  When a non-OCA object such as PDF or SVG
references a TTF/OTF as a secondary resource, the
FQN type X'DE' triplet on the PPO must specify the full
font name of the font. This font must also be mapped
with an MDR reference that specifies the same full font
name.
X'02' Fully Qualified Name Optional. May occur more than once in the repeating group
if the PPO also specifies FQN type X'DE' triplets. See
“Fully Qualified Name Triplet X'02'”.
The Fully Qualified Name type that may appear is:
X'BE'—Data Object Internal Resource Reference.
Specifies the identifier of a resource object that is used
by the object being preprocessed. The identifier is used
internally by the object to be preprocessed to reference [MODCA-5-1626]



| Triplet | Type | Usage [MODCA-5-1627]|
| --- | --- | --- the secondary resource. The identifier must be specified using FQNFmt X'00', which, for this FQN type, indicates that the data type is defined by the specific data object that generates the internal resource reference and is undefined (UNDF) at the MO:DCA data stream level. If the data object that requires this resource is also processed as a resource, the term secondary resource is applied to the resource used by the data object. See “Secondary Resource Objects”. When specified, this triplet must immediately follow the FQN type X'DE' triplet that specifies the external identifier of the secondary resource, or a X'04' exception condition exists. [MODCA-5-1628]|
| X'04' | | Mapping Option Optional. May occur once in each repeating group. This triplet is ignored for ObjType = X'DF'—Overlay. If present, defines the mapping of the object presentation space to the object area. The specified mapping option must be valid for the object or a X'02' exception condition exists. See “Mapping Option Triplet X'04'”. [MODCA-5-1629]|
| X'10' | | Object Classification Mandatory if the repeating group specifies a Fully Qualified Name type X'CE'—Other Object Data Reference, in which case it must occur once in the repeating group and identifies the object type to be preprocessed. See “Object Classification Triplet X'10'”. [MODCA-5-1630]|
| X'4B' | | Measurement Units Mandatory if the PPO specifies any of the following parameters: • XocaOset • YocaOset • XoaSize, specified in the Object Area Size (X'4C') triplet • YoaSize, specified in the Object Area Size (X'4C') triplet In which case this triplet must occur once in the repeating group and defines the measurement units for the parameter values. This triplet is ignored for ObjType = [MODCA-5-1631]|
| X'DF'—Overlay. | | See “Measurement Units Triplet X'4B'”. [MODCA-5-1632]|
**Application Note:**  When the units of measure values
specified on the PPO are different than the values
specified on a subsequent IOB that includes the
preprocessed object, the presentation device might
calculate the sizes and offsets differently when
processing the two structured fields, and—due to round-
off errors—might not use the preprocessed version of
the object. T o avoid such problems, matching units of
measure values should be specified on the PPO and the
corresponding IOB.
X'4C' Object Area Size Optional. May occur once in each repeating group. This
triplet is ignored for ObjType = X'DF'—Overlay. If present,
specifies the size of the object area (XoaSize, YoaSize)
into which the object data is mapped. See “Object Area
Size Triplet X'4C'”.



| Triplet | Type | Usage [MODCA-5-1633]|
| --- | --- | --- [MODCA-5-1634]|
| X'4E' | | Color Specification Optional. May occur once. Specifies the color that is to be used as the default color, or the initial color, for the object. This triplet overrides the color specified in the object's data descriptor and in the Data Object RAT , or sets the color if none is specified. Note that this color may in turn be overridden by a color that is specified inside the object. This triplet only overrides the color specified for the object presentation space; it does not affect colors assigned to the object's object area. The PPO must specify one of the following object types: [MODCA-5-1635]|
| X'92' | | Other object data. Triplet is ignored if the object type is not an image file format that specifies a bilevel or grayscale image, as defined in Appendix D, “MO:DCA Registry”,. [MODCA-5-1636]|
| X'FB' | | Image (IOCA); triplet is ignored if the image is not bilevel. When this triplet is applied to IOCA image, it only applies to bilevel image; it is ignored when the image is not bilevel. When this triplet is applied to non-OCA image file formats, it only applies to bilevel or grayscale image; it is ignored when the image is not bilevel or grayscale. Note that all 1- bit per pixel image objects are considered bilevel. When the image is grayscale, this triplet specifies the color that is to be grayscaled. The color space selected in the triplet must be supported in the object’s data descriptor structured field. For example, if the triplet specifies a default color using ColSpce =X'08' - CIELAB, the object’s data descriptor must also support the CIELAB color space. If ColSpce =X'06' - Highlight color space, the % coverage and % shading parameters are ignored. If the above conditions are not met, the triplet is ignored. See “Color Specification Triplet X'4E'”. [MODCA-5-1637]|
| X'5A' | | Object Offset Optional. May occur once in each repeating group. If this PPO references a file with ObjType = X'92' that contains multiple pages or paginated objects, may occur once with ObjTpe=X'AF' to specify that pages or paginated objects are the objects to be counted. The triplet is ignored in all other cases. Selects a single paginated object to be preprocessed by specifying how many paginated objects in the referenced file precede that object. The offset is measured from the beginning of the file, so that the first paginated object has offset 0, the second has offset 1, and the nth has offset (n-1). Only the selected object is preprocessed. The PPO triplet overrides any Object Offset triplet specified on the CDD. If this triplet is not specified when the PPO references a file with ObjType = X'92' that contains multiple paginated objects, the default is to preprocess the first paginated object in the file. For more information on selecting paginated objects, see “Object Offset Triplet X'5A'”. [MODCA-5-1638]|
**Architecture Note:**  While only the selected paginated
object in the file is actually presented on the page or
overlay, the file referenced by the PPO can be
processed by the presentation system as a complete
entity. This means that the complete file can be
downloaded to the presentation device and multiple
paginated objects in the file can be processed using the
environment defined by the file. For example, if the file is
a multi-page PDF , pages included from that file can be [MODCA-5-1639]



| Triplet | Type | Usage [MODCA-5-1640]|
| --- | --- | --- processed by the presentation device with the same PDF RIP initialization. [MODCA-5-1641]|
| X'91' | | Color Management Resource Descriptor Mandatory when the PPO references a Color Management Resource (CMR) with the FQN type X'DE' triplet, in which case this triplet must occur once in the repeating group. It is ignored in all other cases. Specifies the processing mode and scope for the CMR. The CMRScpe parameter in the triplet must be set to X'01' - data object, when the PPO references a data object, and to X'02' - page/overlay, when the PPO references an overlay. When specified, this triplet must immediately follow the FQN type X'DE' triplet that specifies the CMR name, or a X'04' exception condition exists. See “Color Management Resource Descriptor Triplet X'91'”. [MODCA-5-1642]|
| X'95' | | Rendering Intent Optional. May occur once in each repeating group. See “Rendering Intent Triplet X'95'”. This triplet specifies the rendering intent that is to be used when presenting the object that is referenced with this structured field. When the PPO references a data object, only the rendering intent that applies to the object type of the referenced object is used; the other rendering intents are ignored. The triplet overrides any rendering intent information embedded in the data object. When the PPO references an overlay, all the rendering intents that apply to the object types in the overlay are used; the other rendering intents are ignored. The rendering intent in this triplet is not used if a Link DL CMR is used for a color conversion in this object; in that case the rendering intent specified in the Link DL CMR is used for that color conversion. [MODCA-5-1643]|
| X'9A' | | Image Resolution Optional. May occur once in each repeating group for non- IOCA raster image object types defined by ObjType = X'92' - “other object data”; ignored for IOCA image objects and all other object types. Specifies the resolution of the raster image object. See “Image Resolution Triplet X'9A'”. The PPO triplet overrides any image resolution specified in the data object RAT , on the CDD, or inside the image. If the resolution is not specified outside the image or inside the image, the default is to assume that the image resolution is the same as the output device resolution. [MODCA-5-1644]|



| **Triplet** | **Type** | **Usage** [MODCA-5-1645]|
| :--- | :--- | :--- [MODCA-5-1646]|
| X'9C' | | **Object Container Presentation Space Size** Optional. May occur once in each repeating group for certain object types defined by **ObjType** = X'92' - “other object data”; ignored for IOCA image objects and all other object types. May be specified for the following object types:<br>• PDF - all presentation object types<br>• AFPC SVG Subset<br>Specifies the presentation space size of the object container. For PDF object types, specifies how this size is determined. For SVG, specifies the actual size, and overrides any presentation space size specified within the SVG object. The PPO triplet overrides any specification of object container presentation space size in the Data Object RAT or on the CDD. See “Object Container Presentation Space Size Triplet X'9C'”. [MODCA-5-1647]|
| X'FF' | | **Triplet Extender** Optional. May occur more than once in a contiguous sequence, but only in the following case. It is ignored in all other cases:<br>• The PPO must specify one of the following object types: [MODCA-5-1648]<br>• X'92'—Other object data<br>• The PPO references a secondary resource for the other object data using an FQN type X'DE' triplet<br>• The secondary resource is the generic non-OCA Resource object<br>• The PPO associates an internal resource reference to the secondary resource with an FQN type X'BE' triplet<br>• The triplet extenders must follow the FQN type X'BE' triplet and must occur in a contiguous sequence.<br>Specifies a portion of a secondary resource reference that occurs internal to the data object referenced by the PPO. Use of the triplet extender allows the length of the internal resource reference to exceed the 250 byte capacity of the FQN type X'BE' triplet. [MODCA-5-1649]|

**Architecture Notes:**

1.  The FQN type X'DE' triplet with **FQNFmt** = X'10' (OID) is only used to reference the CMYK SWOP and CMYK Euroscale resident color profiles registered in the MO:DCA Registry; see “Resident Color Profile Identifiers”.
2.  The non-OCA Resource Object must be mapped with an **MDR** reference that matches the FQN type X'DE' reference on the PPO. See “Triplet Extender Triplet X'FF'”.
3.  If the data object that requires this resource is also processed as a resource, the term secondary resource is applied to the resource used by the data object. See “Secondary Resource Objects”. The secondary resource reference must be specified in the same manner, using the same **FQNFmt**, as the **MDR** that maps the secondary resource.
4.  If the object to be preprocessed also references the secondary resource with an internal identifier, this identifier must be specified on the PPO with a FQN type X'BE' triplet that immediately follows the FQN type X'DE' triplet. The paired triplets map the internal identifier to the external identifier.
5.  When a non-OCA object such as PDF or SVG references a TTF/OTF as a secondary resource, the FQN type X'DE' triplet on the PPO must specify the full font name of the font. This font must also be mapped with an **MDR** reference that specifies the same full font name.

**Application Note:**  Objects referenced by a PPO are always processed as hard objects. If the referenced object contains an OEG, secondary resource mappings in the OEG, such as CMR references, are ignored and must be specified directly on the PPO.

#### Processing Rules

The purpose of the PPO is to improve system printing throughput by allowing the printer to preprocess and cache resource objects that are preloaded. If the resource is subsequently included using an **IOB** or **IPO**, a presentation-ready bit map is available. The following considerations need to be taken into account when selecting an object for preprocessing. Note that the efficiency of preprocessing is presentation-system and presentation-environment dependent. [MODCA-5-1650]

**Preprocessing overlays**

Only the orientation parameter is required; all other presentation parameters, if specified, are ignored. If a subsequent include specifies one of the preprocessed orientations, the cached version of the overlay is used. The preprocessed and cached version of an overlay might not be used if any portion of the overlay exceeds the printable area when it is included.

**Preprocessing data objects**

A mapping that specifies how the object presentation space is mapped to the object area is required for preprocessing. For preprocessing, the mapping may be specified on the PPO with a **Mapping Option (X'04')** triplet. If this triplet is omitted, the mapping specified in the object's OEG is used. If the object does not specify the mapping in an OEG, the architected default mapping for the object is used. Note that for objects referenced with **ObjType** = X'92' and **ObjType** = X'FB', the architected default mapping is scale to fit. Only the following mapping options are supported for preprocessing.

*   **Scale-to-fit or scale-to-fill**
    If the mapping is scale-to-fit or scale-to-fill, the object is preprocessed into an object area size (which is required for these mappings) and cached. For preprocessing, the object area size may be specified on the PPO with an **Object Area Size (X'4C')** triplet. If this triplet is omitted, the object area size specified in the object's OEG is used. If the object does not specify the object area size in an OEG, the presentation space size of the object is used. If a subsequent include specifies the same mapping, one of the preprocessed orientations, and the same object area size, the cached version of the object is used. See “Object Type Identifiers” for information on how the object presentation space size is specified by various non-OCA objects.
*   **Position, position-and-trim, or center-and-trim**
    If the mapping is position, position-and-trim, or center-and-trim, the object is first preprocessed at the size of the object presentation space.

    If a presentation window is specified by the PPO—which is defined by an object area size for center-and-trim and both an object area size and object content offset for position and position-and-trim—the preprocessed object is positioned, trimmed if required, and cached. No caching occurs if the mapping is position and there is an overflow of the object area. If a subsequent include specifies the same mapping, one of the preprocessed orientations, and the same window, the cached version of the object is used.

    If a window is not specified by the PPO, the preprocessed object is cached at its presentation space size. If a subsequent include specifies any of these three mappings, one of the preprocessed orientations, and a presentation window, the cached version of the object is processed at print time—with a potential performance penalty—and trimmed if required. If the mapping is position, an exception is detected if there is an overflow of the object area.

**Limitations**

The PPO supports most presentation parameters that may be in effect when the preprocessed object is actually presented. However there are presentation parameters that may be in effect at presentation time that were not taken into account when the object was preprocessed. In such cases the preprocessed and cached object is not used for presentation and the system throughput improvement is not realized. Examples of such presentation parameters are:

*   Specification of an unsupported preprocessing mapping, such as a migration image mapping, on the include [MODCA-5-1651] structured field
*   Specification of a color override on the include structured field, such as use of the **Color Specification (X'4E')** [MODCA-5-1652] triplet to override a default OCA color
*   Invocation of a non-reset **Color Mapping Table** [MODCA-5-1653]
*   Specification of a non-default print quality (objects are always preprocessed at default print quality) [MODCA-5-1654]
*   Activation of a text suppression for overlays (overlays are always preprocessed without text suppressions) [MODCA-5-1655]

#### PPO Exception Condition Summary

X'01' This exception condition exists when:
*   A resource with the same identifier as that specified on the type X'84' (**Coded Font Reference**), **Fully Qualified Name** triplet, or on the type X'CE' (**Other Object Data Reference**) **Fully Qualified Name** triplet, or on the type X'DE' (**Data Object External Resource Reference**) **Fully Qualified Name** triplet was not previously mapped in the same resource group or could not be located. [MODCA-5-1656]
*   The same repeating group contains an invalid number or combination of **Fully Qualified Name** triplets. [MODCA-5-1657]

X'02' This exception condition exists when:
*   A **Fully Qualified Name (X'02')** triplet other than a type X'84' (**Coded Font Reference**), a type X'BE' (**Data Object Internal Resource Reference**), type X'CE' (**Other Object Data Reference**), or a type X'DE' (**Data Object External Resource Reference**) appears within any repeating group. [MODCA-5-1658]
*   The resource reference is specified using **FQNFmt** X'10' (object OID), but the object either is [MODCA-5-1659] not carried in a valid MO:DCA structure or is carried in a valid MO:DCA structure but does not have a matching object OID.

X'04' This exception condition exists when:
*   A FQN type X'BE' triplet is specified but does not immediately follow a FQN type X'DE' [MODCA-5-1660] triplet.
*   A **Color Management Resource Descriptor** triplet is specified but does not immediately [MODCA-5-1661] follow a FQN type X'DE' triplet that references a CMR. [MODCA-5-1662]


### Presentation Text Data Descriptor (PTD)

#### Presentation Text Data Descriptor (PTD) Format 2

The Presentation Text Data Descriptor structured field contains the descriptor data for a presentation text data object.

#### PTD (X'D3B19B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3B19B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1663]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1664]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1665]|
| 0–n | UNDF | **PTOCAdes** | | Up to 32,759 bytes of PTOCA-defined descriptor data | O | X'00' [MODCA-5-1666]|

#### PTD Semantics

**PTOCAdes** Contains the PTOCA-defined text descriptor. See the MO:DCA environment appendix in the *Presentation Text Object Content Architecture Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set.

**Application Note:** When the **PTD** is included in the AEG for a page, some AFP print servers require that the measurement units in the **PTD** match the measurement units in the Page Descriptor (**PGD**). It is therefore strongly recommended that whenever the **PTD** is included in the AEG, the same measurement units are specified in both the **PTD** and **PGD**. [MODCA-5-1667]

### Presentation Text Data (PTX)

The Presentation Text Data structured field contains the data for a presentation text data object. [MODCA-5-1668]

#### PTX (X'D3EE9B') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3EE9B'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1669]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1670]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1671]|
| 0–n | UNDF | **PTOCAdat** | | Up to 32,759 bytes of PTOCA-defined data | O | X'00' [MODCA-5-1672]|

#### PTX Semantics

**PTOCAdat** Contains the PTOCA-defined text data. See the MO:DCA environment appendix in the *Presentation Text Object Content Architecture Reference* for detailed information.

**Note:** The number of data bytes allowed in this structured field may be restricted by an interchange set. [MODCA-5-1673]

### Tag Logical Element (TLE)

A Tag Logical Element structured field assigns an attribute name and an attribute value to a page or page group. The Tag Logical Element structured field may be embedded directly in the page or page group, or it may reference the page or page group from a document index.

When a Tag Logical Element structured field references a page or is embedded in a page following the active environment group, it is associated with the page. When a Tag Logical Element structured field references a page group or is embedded in a page group following the Begin Named Page Group structured field, it is associated with the page group. When a Tag Logical Element structured field is associated with a page group, the parameters of the Tag Logical Element structured field are inherited by all pages in the page group and by all other page groups that are nested in the page group.

The scope of a Tag Logical Element is determined by its position with respect to other TLEs that reference, or are embedded in, the same page or page group. The Tag Logical Element structured field does not provide any presentation specifications and therefore has no effect on the appearance of a document when it is presented.

#### TLE (X'D3A090') Syntax

**Structured Field Introducer**

| **SF Length (2B)** | **ID = X'D3A090'** | **Flags (1B)** | **Reserved (2B)** |
| :--- | :--- | :--- | :--- |
| | | | X'0000' |

**Structured Field Data** [MODCA-5-1674]

| **Offset** | **Type** | **Name** | **Range** | **Meaning** | **M/O** | **Exc** [MODCA-5-1675]|
| :--- | :--- | :--- | :--- | :--- | :---: | :---: [MODCA-5-1676]|
| 0–n | | **Triplets** | | See TLE Semantics for triplet applicability | M | X'14' [MODCA-5-1677]|

#### TLE Semantics

**Triplets** Appear in the Tag Logical Element structured field as follows: [MODCA-5-1678]

| **Triplet** | **Type** | **Usage** [MODCA-5-1679]|
| :--- | :--- | :--- [MODCA-5-1680]|
| X'01' | | **Coded Graphic Character Set Global Identifier** Optional. May occur multiple times. If present, specifies the code page and character set for interpretation of subsequent character strings in the TLE. If not present, the including object specifies the code page and character set for interpretation of character strings in the TLE. By including the triplet multiple times, you can specify a unique code page and character set for the character data in every triplet on the TLE. See “Coded Graphic Character Set Global Identifier Triplet X'01'”. [MODCA-5-1681]|
| X'02' | | **Fully Qualified Name** Mandatory. Must occur once. The Fully Qualified Name type that may appear is X'0B'—Attribute Name. Specifies the attribute name of the tag logical element. See “Fully Qualified Name Triplet X'02'”. [MODCA-5-1682]|
| X'02' | | **Fully Qualified Name** Optional. One of the following Fully Qualified Name types may appear once if the Tag Logical Element structured field references a page or page group from a document index:<br>• X'87'—Begin Page Name. Specifies the name of the page that is referenced by the tag logical element.<br>• X'0D'—Begin Page Group Name. Specifies the name of the page group that is referenced by the tag logical element.<br>See “Fully Qualified Name Triplet X'02'”. [MODCA-5-1683] [MODCA-5-1684] [MODCA-5-1685]|
| X'02' | | **Fully Qualified Name** Optional. May occur once. The Fully Qualified Name type that may appear is X'0C'—Process Element Name. Specifies the name of the tag logical element. See “Fully Qualified Name Triplet X'02'”. [MODCA-5-1686]|
| X'36' | | **Attribute Value** Mandatory. Must occur once. Specifies the attribute value of the tag logical element. See “Attribute Value Triplet X'36'”. [MODCA-5-1687] [MODCA-5-1688]|
| X'80' | | **Attribute Qualifier** Optional. May occur once. Specifies an attribute qualifier for the tag logical element. See “Attribute Qualifier Triplet X'80'”. [MODCA-5-1689] [MODCA-5-1690]|




