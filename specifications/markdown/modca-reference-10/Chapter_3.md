# Chapter 3. MO:DCA Overview

This chapter:
*   Describes the general syntax and semantics for MO:DCA structured fields [MODCA-3-001]
*   Describes state, as defined by the MO:DCA architecture [MODCA-3-002]
*   Describes the types and categories of MO:DCA parameters [MODCA-3-003]
*   Describes conventions used in the MO:DCA architecture for coordinate systems, measurement units, and rotation units [MODCA-3-004]
*   Describes MO:DCA mixing rules [MODCA-3-005]
*   Describes MO:DCA color management [MODCA-3-006]
*   Describes MO:DCA metadata objects [MODCA-3-007]
*   Describes font technologies used in MO:DCA documents [MODCA-3-008]
*   Describes MO:DCA document indexing [MODCA-3-009]
*   Describes other aspects of MO:DCA document presentation [MODCA-3-010]
*   Describes and defines the MO:DCA exception conditions [MODCA-3-011]

## MO:DCA Data Structures

Each component of a mixed object document is explicitly defined and delimited in the data stream that transmits it. This is accomplished through the use of MO:DCA data structures, called structured fields, that reside in the data stream. Structured fields are used to envelop document components and to provide commands and information to applications using the data stream. Structured fields may contain one or more parameters. Each parameter provides one value from a set of values defined by the architecture. [MODCA-3-012]

## Notation Conventions

In addition to the information provided in “How to Read the Syntax Diagrams”, the following notation conventions apply throughout this document:
*   Bytes are numbered from left to right beginning with byte zero, which is considered the high order (most significant) byte position. This is referred to as big-endian byte order. For example, a three-byte field would consist of byte zero, byte one, and byte two. [MODCA-3-013]
*   Each byte is composed of eight bits. [MODCA-3-014]
*   Bits in a single byte are numbered from left to right beginning with bit zero, the most significant bit, and continuing through bit seven, the least significant bit. This is referred to as big-endian bit order. [MODCA-3-015]
*   When bits from multiple consecutive bytes are considered together, the first byte always contains bits zero to seven and the bits of the additional bytes are numbered eight to n, where n is equal to one less than the total number of bytes multiplied by eight. For example, a two-byte field would consist of bits zero to fifteen and a four-byte field would consist of bits zero to thirty-one. [MODCA-3-016]
*   Negative numbers are expressed in two's-complement form. See “Number” for details. [MODCA-3-017]
*   Field values are expressed in hexadecimal or binary notation: [MODCA-3-018]
    *   B'01111110' = X'7E' = +126
    *   X'7FFF' = +32,767
    *   X'8000' = -32,768 (when signed binary is used)
    *   X'8000' = +32,768 (when unsigned binary is used) [MODCA-3-019]

## MO:DCA Structured Field Syntax

MO:DCA structured fields consist of two parts: an introducer that identifies the length and type of the structured field, and data that provides the structured field's effect. The data is contained in a set of parameters, which can consist of other data structures and data elements. The maximum length of a structured field is 32,767 bytes. The general format for a structured field is as follows:

*   **Structured Field Introducer**:
    *   Length (2B)
    *   Identifier (3B)
    *   Flags (1B)
    *   Reserved (2B); X'0000'
*   **Structured Field Data**:
    *   Extension Data
    *   Padding

### Structured Field Introducer

The MO:DCA Structured Field Introducer (SFI) introduces a structured field, and identifies its type and its length. SFIs have the following format:

**Table 6. Structured Field Introducer (SFI)**

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :--- |
| 0–1 | UBIN | SFLength | 8–32,767 | Total length of the structured field including the length of the introducer | M | X'82' |
| 2–4 | CODE | SFTypeID | | A three-byte code that uniquely identifies the structured field. See “SFI Semantics” for a description. | M | X'78' |
| 5 | BITS | FlagByte | | Used to indicate whether an extension, segmentation, or padding is in use: [MODCA-3-020]<br>Bit 0 ExtFlag: B'0' No SFI extension exists; B'1' SFI extension is present<br>Bit 1 Reserved; should be zero<br>Bit 2 SegFlag: B'0' Data is not segmented; B'1' Data is segmented<br>Bit 3 Reserved; should be zero<br>Bit 4 PadFlag: B'0' No padding data exists; B'1' Padding data is present<br>Bits 5–7 Reserved; should be zero | M | X'82' |
| 6–7 | | Reserved | | should be zero | M | X'82' |

The following optional extension appears only if bit 0 of FlagByte is B'1': [MODCA-3-021]

| Offset | Type | Name | Range | Meaning | M/O | Exc |
| :--- | :--- | :--- | :--- | :--- | :---: | :--- |
| 8 | UBIN | ExtLength | 1–255 | Length of the extension including the length of ExtLength itself | O | X'82' [MODCA-3-022] |
| 9–n | ExtData | Data | | Application-defined extension data | O | X'00' [MODCA-3-023] |

### SFI Semantics

*   **SFLength**: Defines the length of the structured field, including itself.
    *   *Application Note*: Some platforms include structured fields in a larger platform-specific record by surrounding the structured field with additional bytes (such as the X'5A' prefix). This can result in a record length greater than X'7FFF' if the structured field length is X'7FFF'. Such a record length can be misinterpreted as a negative number if the length is treated as SBIN. To ensure portability of MO:DCA print files, it is strongly recommended that the maximum structured field length be limited to X'7FF0' = 32,752, which avoids such record length issues on the known platforms. Note that MO:DCA interchange sets have traditionally limited the maximum structured field length. MO:DCA IS/3 limits the length to X'7FF0' = 32,752, MO:DCA IS/1 and IS/2 limit the length to X'2000' = 8,192.
*   **SFTypeID**: A three-byte field that uniquely identifies the structured field. It has the form D3TTCC, where:
    *   **D3**: The structured field class code that has been assigned to the MO:DCA architecture.
    *   **TT**: The structured field type code. The type code identifies the function of the structured field, such as begin, end, descriptor, or data. See “Type Codes” for a description of type codes.
    *   **CC**: The structured field category code. It identifies the lowest level component that can be constructed using the structured field, such as document, active environment group, page, or object. The same category code point assigned to a component's begin structured field also is assigned to that component's end structured field. These code points identify and delimit an entire component within a data stream or an encompassing component. See “Category Codes” for a description of category codes.
*   **FlagByte**: Specifies the value of the optional indicators. Indicator bits are defined as follows:
    *   **Bit 0 ExtFlag**: The SFI extension flag. See “Structured Field Introducer Extension” [MODCA-3-024] for details.
        *   B'0' No SFI extension exists.
        *   B'1' This structured field has an SFI extension.
    *   **Bit 2 SegFlag**: The segmentation flag. See “Structured Field Segmentation” [MODCA-3-025] for details.
        *   B'0' No segmentation in effect.
        *   B'1' The data for this structured field has been segmented.
    *   **Bit 4 PadFlag**: The padding flag. See “Structured Field Padding” [MODCA-3-026] for details.
        *   B'0' No padding data appended.
        *   B'1' Padding data has been appended to the end of this structured field. [MODCA-3-027]
    *   **All others**: Reserved; should be binary zero.
*   **Bytes 6–7**: Reserved; should be zero.
    *   *Application Note*: In AFP environments, some applications use bytes 6–7 of the Structured Field Introducer to specify a sequence number for the structured field. This is an unarchitected use of these bytes and should be avoided.
*   **ExtLength**: Specifies the length of the SFI extension, including the length of ExtLength itself. For ExtLength to be valid, bit 0 of FlagByte must be B'1'.
*   **ExtData**: Contains up to 254 bytes of application-defined SFI extension data. For ExtData to be valid, bit 0 of FlagByte must be B'1'. [MODCA-3-028]

### Type Codes

The following type codes have been defined. All other type codes are reserved.

**Table 7. Type Codes**

| Type Code | Function | Description |
| :--- | :--- | :--- |
| X'A0' | Attribute | An attribute structured field defines an attribute with parameters such as name and value. |
| X'A2' | Copy Count | A copy count structured field specifies groups of sheet copies, called copy subgroups, that are to be generated, and identifies modification control structured fields that specify modifications to be applied to each group. |
| X'A6' | Descriptor | A descriptor structured field defines the initial characteristics and, optionally, the formatting directives for all objects, object areas, and pages. Depending on the specific descriptor structured field type, it may contain some set of parameters that identify:<br>* The size of the page or object [MODCA-3-029]<br>* Measurement units [MODCA-3-030]<br>* Initial presentation conditions [MODCA-3-031] |
| X'A7' | Control | A control structured field specifies the type of modifications that are to be applied to a group of sheet copies, or a copy subgroup. |
| X'A8' | Begin | A begin structured field introduces and identifies a document component. In general, a begin structured field may contain a parameter that identifies the name of the component. |
| X'A9' | End | An end structured field identifies the end of a document component. In general, an end structured field may contain a parameter that identifies the name of the component. |
| X'AB' | Map | A map structured field provides the following functions in the MO:DCA architecture:<br>* All occurrences of a variable embedded in structured field parameter data can be given a new value by changing only one reference in the mapping, rather than having to physically change each occurrence. [MODCA-3-032]<br>* The presence of the map structured field in a MO:DCA environment group indicates use of the named resource within the scope of the environment group. [MODCA-3-033] |
| X'AC' | Position | A position structured field specifies the coordinate offset value and orientation for presentation spaces. [MODCA-3-034] |
| X'AD' | Process | A process structured field specifies processing to be performed on an object. |
| X'AF' | Include | An include structured field selects a named resource which is to be embedded in the including data stream as if it appeared inline. External resource object names on the begin structured field may or may not coincide with the library name of that object, as library name resolution is outside the scope of the MO:DCA architecture. |
| X'B0' | Reserved | See MO:DCA-L: The OS/2 PM Metafile (.met) Format. |
| X'B1' | Migration | A migration structured field is used to distinguish the MO:DCA structured field from a structured field with the same acronym from an earlier data-stream architecture. The earlier version is called Format 1. The MO:DCA version is called Format 2. |
| X'B2' | Variable | A variable structured field defines or contains variable information. |
| X'B4' | Link | A link structured field defines a logical connection, or linkage, between two document components. |
| X'EE' | Data | A data structured field consists of data whose meaning and interpretation is governed by the object architecture for the particular data object type. |

### Category Codes

The following category codes have been defined. All other category codes are reserved.

| Category Code | Description |
| :---: | :--- |
| X'5F' | Page Segment |
| X'6B' | Object Area |
| X'77' | Reserved. See MO:DCA-L: The OS/2 PM Metafile (.met) Format. |
| X'7B' | IM Image |
| X'88' | Medium |
| X'8A' | Coded Font |
| X'90' | Process Element |
| X'92' | Object Container |
| X'9B' | Presentation Text |
| X'A5' | Print File |
| X'A7' | Index |
| X'A8' | Document |
| X'AD' | Page Group |
| X'AF' | Page |
| X'BB' | Graphics |
| X'C3' | Data Resource |
| X'C4' | Document Environment Group (DEG) |
| X'C6' | Resource Group |
| X'C7' | Object Environment Group (OEG) |
| X'C9' | Active Environment Group (AEG) |
| X'CC' | Medium Map |
| X'CD' | Form Map |
| X'CE' | Name Resource [MODCA-3-035] |
| X'D8' | Page Overlay |
| X'D9' | Resource Environment Group (REG) |
| X'DC' | Preprinted Form Overlay |
| X'DF' | Overlay |
| X'EA' | Data Suppression |
| X'EB' | Bar Code |
| X'EE' | No Operation |
| X'FB' | Image |

### Structured Field Data

The structured field's data is contained in a parameter set that immediately follows the structured field's introducer. The syntax and semantics for each MO:DCA structured field parameter set is given in Chapter 5, “MO:DCA Structured Fields”. Depending on the structured field and its purpose, the parameter set may contain zero or more parameters. If parameters are present, they contain specific information appropriate for the structured field. The data occupies as many bytes as needed, up to a maximum of 32,759 bytes.

### Structured Field Introducer Extension

A structured field introducer may be extended by up to 255 bytes. The presence of an SFI extension is indicated by a value of B'1' in bit 0 of the SFI flag byte. If an extension is present, the introducer is at least 8 bytes, but not more than 263 bytes, in length. The first byte of the extension specifies its length. If an extension to the structured field introducer is present, the structured field's data can occupy up to 32,759 bytes, less the length of the extension.

### Structured Field Segmentation

When the total length of the introducer and the data portion of a structured field exceeds 32,767 bytes, the data must be split into two or more fragments and specified on multiple consecutive structured fields. This is known as segmenting a structured field. Segmenting normally only occurs for those structured fields that contain OCA data.

When a structured field is segmented, the OCA may require that the data be split on specific data element boundaries. The MO:DCA architecture permits other structured fields to be interspersed between the segmented structured fields. However, for those cases where it is undesirable to split the data at a specific boundary or to permit other structured fields to appear between the segmented structured fields, the MO:DCA architecture provides a segmentation flag. This flag indicates that the segmented structured fields are all part of a single, uninterrupted parameter string. When bit 2 of the SFI flag byte is set to B'1', the parameter data may be split at any byte boundary and no other structured fields are permitted to appear between the segmented structured fields. The segmentation flag value for the last structured field in a sequence of structured fields containing a segmented parameter string must be B'0'.

### Structured Field Padding

Padding bytes may be used by an application to extend the physical length of a structured field beyond what is required by its introducer and parameter set. This could be done, for example, to make all structured fields the same length or to make each structured field's length a multiple of some number. The use of padding is indicated by a value of B'1' in bit 4 of the SFI flag byte.

If padding is indicated, the length of the padding is specified in the following manner:
*   For 1 or 2 bytes of padding, the length is specified in the last padding byte. [MODCA-3-036]
*   For 256 to 32,759 bytes of padding, the length is specified in the last three bytes of the padding data. The last byte must be X'00' and the two preceding bytes specify the padding length. [MODCA-3-037]
*   For 3 to 255 bytes of padding, the length can be specified by either method. [MODCA-3-039]

When padding is indicated:
*   The structured field length value specifies the total length of the structured field, including the padding data. [MODCA-3-040]
*   The padding length value specifies the total length of the padding data, including the padding length byte(s). [MODCA-3-041] [MODCA-3-038]

### Structured Field Formats

The MO:DCA architecture has evolved from several previous IBM data streams, namely the Composed Page Data Stream (CPDS), the Composite Document Presentation Data Stream (CDPDS), and the Advanced Function Print Data Stream (AFPDS). Because of this, the MO:DCA architecture uses many of the same structured fields originally defined for these architectures. However, in some cases new structured fields have been defined that have the same name, acronym, and usage as these older structured fields. This has only been done for those cases where it became necessary to expand the function of the structured field, but the definition of the original structured field did not lend itself to expansion.

These new structured fields are always assigned a structured field identifier closely resembling the old one. Although the structured field identifiers clearly differentiate between the two versions of the same structured field, when referring to them by name or by acronym, the older version is known as Format 1 and the newer MO:DCA version is known as Format 2. Two such structured fields are the Map Coded Font structured field and the Presentation Text Data Descriptor structured field.

### Data Stream Format

The MO:DCA architecture does not dictate the physical format of the data stream or how it is transported. The data stream may be carried within a communication protocol or it may be stored on a tape or disk. It may be one continuous string of bytes or it may be broken up into multiple records. When broken into multiple records, the records may be fixed length or variable length. Each record may contain an individual structured field, a portion of a structured field, or any number of contiguous structured fields. The receiver must be capable of receiving the data stream and parsing or processing it sequentially from start to finish. While receivers may impose reasonable limits on blocking factors for buffer management purposes, they should not be designed to process only one type of data stream format.

### MO:DCA Data Stream States

The MO:DCA architecture defines a state to be a domain within the data stream, bounded by a begin-end structured field pair, within which certain structured fields are permitted. This is also called a MO:DCA Object (see Chapter 4, “MO:DCA Objects”, for more information). The processor of a MO:DCA data stream is required to check the validity of the structured field sequence received. A valid structured field sequence is one in which each structured field that is processed belongs to the set of permissible structured fields for the begin-end envelope in which it is found. If a structured field other than one belonging to the set of permissible structured fields is detected, a violation of the state has occurred, and the processor is required to raise an exception condition.

See “MO:DCA Interchange Set 1” and “MO:DCA Interchange Set 3 (IS/3)” for details of the structured fields that may be encountered in each state in MO:DCA, MO:DCA IS/1, and MO:DCA IS/3 data streams respectively.

### Environment Hierarchies

The Active Environment Group and Object Environment Group are also hierarchically related. Parameters specified in the OEG override like parameters specified in the AEG, while like parameters specified within the same environment—whenever this is allowed—replace the previous specification. To illustrate this point, consider the following example. Note that the same LID mapping rules apply when a resource object is mapped with a Map Data Resource (MDR) structured field.

*   A page contains an AEG with the following two Map Coded Font structured fields: [MODCA-3-042]
    *   An MCF that maps LID 1 to font A and LID 2 to font B
    *   An MCF that maps LID 3 to font D
*   A graphics data object on that same page contains an OEG with the following two Map Coded Font structured fields: [MODCA-3-043]
    *   An MCF that maps LID 3 to font E and LID 4 to font F
    *   An MCF that maps LID 5 to font H

For objects on that page that do not specify their own MCFs within their own OEGs, the LIDs and their associated fonts would be:
*   LID 1 = font A, from AEG MCF #1 [MODCA-3-044]
*   LID 2 = font B, from AEG MCF #1 [MODCA-3-045]
*   LID 3 = font D, from AEG MCF #2 [MODCA-3-046]

The LIDs and their associated fonts available within the graphics object would be:
*   LID 1 = font A, from AEG MCF #1 [MODCA-3-047]
*   LID 2 = font B, from AEG MCF #1 [MODCA-3-048]
*   LID 3 = font E, from OEG MCF #1 [MODCA-3-049]
*   LID 4 = font F, from OEG MCF #1 [MODCA-3-050]
*   LID 5 = font H, from OEG MCF #2 [MODCA-3-051]

In this case, fonts A and B were made available from the MCFs contained in the AEG which was higher in the environment hierarchy. However, font D was overridden when the first MCF in the OEG mapped LID 3 to font E.

Similarly, if a Presentation Space Reset Mixing triplet were specified on both the Page Descriptor structured field and one or more Object Area Descriptor structured fields within a particular overlay within a resource group, the PGD would control the presentation space mixing for the entire overlay presentation space and the OBDs would control the presentation space mixing for their individual object area presentation spaces.

Resource Environment Groups (REGs) are optional and do not affect AEGs and OEGs. Identifying a resource in a REG does not remove the need to map that resource in the environment groups of the pages and objects that use the resource.

### Processing Order

Unless otherwise specified in a structured field's definition, all structured fields are processed in the order in which they appear in the data stream. For example, if a presentation data stream contains a page with a text object, an Include Page Overlay, a graphic object, a second Include Page Overlay, and an image object, in that order, the objects are presented (imaged) on the page in that same order. That is, the text object is presented first, the first overlay is presented second, the graphic object is presented third, the second overlay is presented fourth, and the image object is presented last.

Likewise, unless otherwise specified in the structured field or triplet definition, structured field and triplet parameters are also processed in the order in which they appear in the structured field or triplet.

### Resource Search Order

Resources used by a MO:DCA document may be located in resource groups that are internal to the document (such resource groups are only supported in the retired MO:DCA IS/2 interchange set), in resource groups that are external to the document (print file level resource groups), or in resource libraries.

The general search order for MO:DCA resources is as follows:
1.  Internal (page level) resource groups (only supported in the retired MO:DCA IS/2 interchange set). [MODCA-3-052]
2.  External (print file level) resource groups. [MODCA-3-053]
3.  External resource libraries. [MODCA-3-054]

For the formal definition of resource groups in MO:DCA data streams, see “Resource Groups”.

## Structured Field Parameters

A structured field is composed of a set of parameters that provides data and control information to processors of the data stream. The MO:DCA architecture has established a length, a set of permissible values and a functional definition for each structured field parameter. [MODCA-3-055]

### Mandatory and Optional Parameters

A parameter can be mandatory or optional. Chapter 5 provides a description of each structured field's parameters. The description indicates whether each parameter is mandatory or optional.

#### Mandatory Parameters
A mandatory parameter appears in a structured field because the function of the parameter is required and a value is essential for proper interpretation of the data stream. A value must be specified for a mandatory parameter. The value specified either must be within the range of permissible parameter values, or it must designate that an existing default value is to be used. A mandatory parameter requires that a suitable value for the parameter must appear somewhere in the hierarchy of structured fields in the data stream.

#### Optional Parameters
An optional parameter can be omitted from a structured field if the function of that parameter is not required, or if, although the function is required, a default value is acceptable. An optional parameter cannot be omitted if the function is required and the default value is not acceptable.

### Parameter Categories

A parameter's category refers to its structure. A parameter can consist of a single data element or it can be a data structure composed of several data elements. Parameters that are data structures can have either a fixed length or a variable length. In the MO:DCA architecture two types of parameters are used: fixed and self-identifying.

#### Fixed Parameters
A parameter consisting of a single data element is called a fixed parameter. A fixed parameter has a constant size in terms of bits and bytes and it always appears at the same location within its structured field. Fixed parameters also are called positional parameters.

#### Self-identifying Parameters
Self-identifying parameters are data structures that consist of three or more data elements, one of which is used to identify the purpose of the parameter. The self-identifying parameter in the MO:DCA architecture is known as a triplet.

A triplet can have a variable length of up to 254 bytes. A triplet must consist of at least three data elements: a length data element, an identifier data element, and one or more data elements for its contents. It can occupy any location after any fixed parameters that occur in the structured field.

#### Repeating Groups
The MO:DCA architecture also supports another category of parameters known as a repeating group. A repeating group consists of specific fixed or self-identifying parameters that have been combined into a defined group. This group then becomes a data structure that may be specified multiple times.

When the repeating group contains self-identifying parameters, the first parameter in the repeating group is a length parameter that indicates how many bytes comprise that repeating group. This length parameter is called the RGLength parameter and the value specified always includes the length of the RGLength parameter itself, which is usually two bytes.

When the repeating group contains only fixed parameters, the MO:DCA architecture may or may not specify that the repeating group contains a RGLength parameter. When it does, the value specified for the RGLength parameter always includes the length of the RGLength parameter itself.

*   **Note**: Frequently, a structured field may contain both positional and self-identifying parameters. When this occurs, the positional parameters always occur before any self-identifying parameters. At times, some or all of the positional parameters may be defined as optional. Optional parameters may only occur at the end, after all mandatory parameters. When optional self-identifying parameters such as triplets are added to a structured field that has optional positional parameters defined, all of the positional parameters are considered mandatory and must appear before the first self-identifying parameter. See “Include Page Overlay (IPO)” for an example of this type of structured field.

### Parameter Values

A parameter's value can be specified directly, or it can be obtained indirectly through the use of defaults.

#### Specified Values
The values to be given to a parameter must be consistent with its length and data type. Additional constraints on values may eliminate one or more values that otherwise could be assigned to a parameter.

#### Default Values
The use of defaults enables the sender of data-stream documents to omit the values for defaulted parameters, permitting the receiving application to use predetermined values. A default value can be given to a parameter by omitting any value for it, or by entering a value, defined by the architecture, requesting use of the default. The source of the default value used for a parameter may be an environment group higher in the document component hierarchy, or it may be an architected default established by the MO:DCA architecture. [MODCA-3-056]

#### Hierarchical Defaults
Parameter values established by an environment group at a higher level in the document component hierarchy will be the default for a subordinate level unless a value is specified at the subordinate level. The scope of a parameter is the same as the scope of the structured field that contains it. Thus the parameters established in an active environment group for the current page will be in effect for the duration of the page, and will be the default parameters for all objects contained in the page. If an object in the page has an associated object environment group that specifies new values, the new parameter values will be in effect for the duration of the object. If the parameters for a subsequent object in the page are unspecified, or if they specify that the default value is to be used, the values from the current page's active environment group will be used. The placement of parameter values at a higher level in the document hierarchy, for the purpose of enabling lower levels to inherit these values as defaults, is known as factoring.

#### Architected Defaults
Certain parameters may be given default values by the MO:DCA architecture. Parameters that have been given defaults are identified in the structured field descriptions in Chapter 5, “MO:DCA Structured Fields”. If a default is not listed for a parameter, no architected default exists.

#### Default Indicator
One of the values that usually can be given to a parameter is the default indicator. Use of the default indicator for a parameter's value specifies that the current default value for the parameter is to be used. In the MO:DCA architecture the default indicator has the value X'F…F'. The default indicator specifies that either a hierarchical default value or an architected default value is to be used for the parameter. A default indicator is implied when a fixed parameter has been omitted at the end of a structured field. A fixed parameter cannot be omitted if any subsequent, optional, positional parameter is present, or if any triplet is present.

Any parameter for which the default indicator is valid must have a default value assigned. This value, which must be valid for the parameter, is used when the default indicator is specified or implied. A structured field whose parameter values are all default indicators has no effect and can be omitted from the data stream.

### Parameter Occurrence

Parameters may be single-occurrence or multiple-occurrence. The syntax tables in Chapter 5, “MO:DCA Structured Fields”, identify which parameters are single-occurrence and which are multiple-occurrence.

#### Single-Occurrence Parameters
Single-occurrence parameters can occur only once in a structured field. Single-occurrence parameters can be fixed parameters or triplets. If a value is specified for a single-occurrence parameter, it will be in effect for the scope of its structured field. If the value of a single-occurrence parameter is omitted or if the default indicator is specified, then normal default value inheritance will apply.

#### Multiple-Occurrence Parameters
Multiple-occurrence parameters are parameters that can appear more than once in a structured field. Multiple-occurrence parameters can be triplets or repeating groups. A repeating group may consist of fixed parameters, triplets, or a combination of fixed parameters and triplets. The following rules apply to multiple-occurrence parameters:
*   Triplets will not inherit values from higher levels of the document component hierarchy. [MODCA-3-057]
    *   If some triplets are omitted from a structured field at a lower level, default values will not be used. The result will be that no values will exist for the omitted parameters for the scope of the structured field.
    *   If all triplets are omitted from a structured field, architected default values will be used for those parameters that have them. The result will be that only those parameters having architected defaults will have effect for the scope of the structured field.
*   Fixed parameters will inherit values from higher levels of the document component hierarchy. If repeating groups of fixed parameters are specified at more than one level within the document component hierarchy and semantic conflicts occur, then the conflicts are resolved in favor of the lowest level for the scope of the structured field. [MODCA-3-058]

### Parameter Types

The term parameter type refers to a parameter's function rather than to the data type of the parameter's data. For a listing of the six basic data types used by the MO:DCA architecture, see “How to Read the Syntax Diagrams”. A parameter's function may be closely related to a data type, for example, in the case of a bit string parameter and the BITS data type. A MO:DCA parameter may be a bit string, character string, code, global identifier, local identifier, name, number, or an undefined type. [MODCA-3-059]

One of the most important functions for certain types of parameters is their use in referencing other document components. A reference is the use of an identifier to refer to a component, structured field, or repeating parameter group. References are usually found in structured fields that map component identifiers to local identifiers, and that invoke or include components at specific data-stream locations. The effect is the same as if the component appeared at the location in the data stream that contains the structured field that invokes or includes it. Components that are referenced by include structured fields provide resource definitions or object definitions. Components that are referenced by invoke structured fields provide format information, such as that contained in environment groups.

#### Bit String
A bit string is a string of binary elements and corresponds to the BITS data type. Each bit of a bit string has a value of either B'1' or B'0', which represents on or off respectively. Each bit usually is independent of the others. Some combinations of bits may be invalid depending on what has been defined for the data element by the MO:DCA architecture. The convention used for addressing bits within a bit string is that the leftmost bit is bit 0.

#### Character String
A character string corresponds closely to the CHAR data type. It is used for identifiers composed of a string of one or more graphic characters. Character strings are compared on the basis of the identifiers of the graphic characters that are presented for the corresponding code points. In the MO:DCA data stream, this is governed by the Coded Graphic Character Set Global Identifier (CGCSGID).

#### Code
A code is a value assigned by the MO:DCA architecture that relates to a particular meaning. The code parameter type relates to the CODE data type. In general, parameters having a code type are given hexadecimal values or value ranges to distinguish them from parameters with a number type.

#### Global Identifier
A global identifier (GID) is a string of bytes that is from 1 to 250 bytes in length. It is usually a coded graphic character string with a data type of CHAR, but it can also be a number or a code. A global identifier has either an alphanumeric character value that is a global name, such as the name of a document, or a numeric value that is unique in the interchange environment. If an identifier is to be used where uniqueness is required, for example to reference a component by name, the same name or value cannot be used more than once within the scope of its reference. For example, the same name must not be given to two different resource definitions of the same type in the same resource group.

#### Local Identifier
A local identifier (LID) is used within the data stream to reference a resource, such as a font, from within a structured field or an OCA. The application creating the data stream is responsible for establishing the cross references or mapping between the resources and their LIDs. The use of LIDs and mapping enables an application to make one change in the mapping to effect multiple changes for the scope of an LID, rather than having to make a change at each location where the LID appears.

Once established, an LID has meaning only within the context of the data stream that contains it. An LID has a data type of CODE and its meaning is independent of where the data stream is created, filed, transmitted, or presented.

Whenever a local identifier parameter type is used to relate structured fields present in the data stream, the scope of reference for the LID is the begin-end pair enveloping the referenced resource. Thus both the referenced resource and the referencing structured field must reside in the same begin-end envelope. Structured fields, known as map structured fields, that specify a global to local mapping follow the normal MO:DCA environment hierarchy rules. [MODCA-3-060]

#### Name
A name is an identifier composed of alphanumeric characters, and is closely related to the CHAR data type. A name parameter type can relate either to a global or a local identifier. Names are compared on the basis of the identifiers of the graphic characters that are presented for the corresponding code points. When comparing names of unequal length, the shorter name is padded with space characters until it is the same length as the longer name.

Generally, names of begin structured fields within a MO:DCA data stream are required to be unique only if their names will be referenced and they reside in the same containing envelope with another begin structured field of the same type.

Name parameters for end structured fields, if used, must match the name parameter for corresponding begin structured fields. However if the first two bytes of the name parameter for an end structured field have the value X'FFFF', it will, by default, match any name on the corresponding begin structured field.

*   **Architecture Note**: The semantic that stated “A value of X'0…0' for any positional parameter having a name type indicates that a Fully Qualified Name (FQN) triplet exists in the structured field. The Fully Qualified Name triplet contains a name that is used to replace the positional name parameter value” is outdated and has been removed from the architecture. Each structured field that specifies a positional name parameter (a “token name”) and that supports an override of this parameter using an FQN triplet, already clearly states that such an FQN triplet (normally FQN type X'01') overrides and replaces the positional name parameter. In fact, the FQN type X'01' triplet is defined as “Replace First GID name”, and, by definition, replaces the token name regardless of whether the token name specifies a value of X'0…0' or not.

The scope of any name reference is limited to the scope of the document component where the name is specified. Thus a name appearing in an Active Environment Group has a scope that is limited to the page or page overlay containing the Active Environment Group, and a name appearing in an Object Environment Group has a scope that is limited to the object containing the Object Environment Group.

#### Number
A number or arithmetic value implies count or magnitude. All numbers used within the MO:DCA architecture are either signed or unsigned integers as indicated in the syntax tables by the SBIN and UBIN data types respectively.

In an unsigned number, all bits are used to express the absolute value of the number. For signed numbers, the leftmost, or high order bit represents the sign, which is followed by the integer field.

Positive numbers are represented in true binary notation with the sign bit set to zero. Negative numbers are represented in two's-complement binary notation with the sign bit set to one. Specifically, a negative number is represented by the two's complement of the positive number. The two's-complement of a number is obtained by inverting each bit of the number and adding a one to the low-order bit position.

Since the MO:DCA architecture defines X'F…F' as a default indicator, the arithmetic value -1 generally is not permitted. However, in the case where a parameter cannot be defaulted, the value which normally is the default indicator is interpreted as -1. Chapter 5, “MO:DCA Structured Fields”, and Chapter 6, “MO:DCA Triplets”, identify parameters that cannot be defaulted. The maximum absolute values for numbers that can be assigned to data elements that also can be assigned the default indicator are listed in Table 8. [MODCA-3-061]

**Table 8. Maximum Absolute Values of Numbers in the MO:DCA Architecture**

| Number of Bytes | Data Type | Absolute Values (Hexadecimal) | Absolute Values (Decimal) |
| :---: | :---: | :--- | :--- |
| 1 | SBIN | X'7F' | 127 [MODCA-3-062] |
| 1 | UBIN | X'FE' | 254 [MODCA-3-063] |
| 2 | SBIN | X'7FFF' | 32,767 [MODCA-3-064] |
| 2 | UBIN | X'FFFE' | 65,534 [MODCA-3-065] |
| 3 | SBIN | X'7FFFFF' | 8,388,607 [MODCA-3-066] |
| 3 | UBIN | X'FFFFFE' | 16,777,214 [MODCA-3-067] |
| 4 | SBIN | X'7FFFFFFF' | 2,147,483,647 [MODCA-3-068] |
| 4 | UBIN | X'FFFFFFFE' | 4,294,967,294 [MODCA-3-069] |

Unique syntax is used for the expression of values that pertain to units of measurement and to rotation. See “Measurement Units” and “Rotation Units” for details of this syntax. [MODCA-3-070]

## Coordinate Systems

The MO:DCA architecture defines a multi-level coordinate system hierarchy that allows a large degree of flexibility in presenting data on a physical medium. A MO:DCA coordinate system is an orthogonal coordinate system based on the fourth quadrant of a standard Cartesian coordinate system. Both the X axis and the Y axis specify positive values, which is a difference from the Cartesian system where the Y axis in the fourth quadrant specifies negative values.

Wherever negative offsets are supported, such as in the positioning of a page presentation space on the medium presentation space, the negative X axis is generated by extending the X axis left of the origin, and the negative Y axis is generated by extending the Y axis above the origin. Negative numbers are expressed in two's complement notation.

Each individual coordinate system is associated with a specific presentation space. The MO:DCA architecture defines the following presentation spaces:

*   **Medium Presentation Space**: The presentation space for the physical medium. This is the base presentation space onto which all other presentation spaces are merged.
*   **Page Presentation Space**: The presentation space for the page, also called a logical page.
*   **Overlay Presentation Space**: The presentation space for an overlay.
*   **Object Area Presentation Space**: The presentation space for an object area.
*   **Data Object Presentation Space**: The presentation space for a data object. This presentation space is defined by the corresponding data object architecture. For details on data object presentation spaces, refer to the reference manual for each specific data object architecture.

The coordinate systems that correspond to the MO:DCA presentation spaces are listed in Table 9. Each coordinate system defines its own coordinate axes, measurement units, and extents.

**Table 9. MO:DCA Coordinate Systems**

| Coordinate System | Notation for Axes: x direction | Notation for Axes: y direction |
| :--- | :---: | :---: |
| Medium | Xm | Ym |
| Page | Xpg | Ypg |
| Overlay | Xol | Yol |
| Object Area | Xoa | Yoa |

The origin of all MO:DCA coordinate systems is the point (0,0) where X equals zero and Y equals zero. The X and Y axes form the top and left edges, respectively, of the presentation space, as shown in Figure 5.

The presentation space associated with the MO:DCA page can be specified to exist on either side of a sheet, and multiple page presentation spaces can exist on the same side of a sheet. [MODCA-3-071]

**Figure 5. A MO:DCA Presentation Space Coordinate System [MODCA-3-072]**

## Measurement and Rotation

Measurement and rotation conventions are essential to the specification and interpretation of layout information for data-stream documents. MO:DCA's conventions for measurement include data element formats and definitions for units, extent, and position. Its conventions for rotation include data element formats and definitions for units.

### Measurement

The distance of a point from an origin is known as its absolute position. The distance of a point from another point is known as its relative position. Distances are measured in addressable positions, and can mean Xm, Ym units, Xpg, Ypg units, Xol, Yol units, or Xoa, Yoa units, depending on the extent or offset being measured.

#### Measurement Units
Measurement units are used throughout the MO:DCA architecture to identify the units of measure to be used for such things as extents and offsets along the X and Y axes of a coordinate system.

Each individual measurement unit is specified as two separate values:
*   **Unit base**: This value represents the length of the measurement base. It is specified as a one-byte coded value. The valid codes and their associated meanings are as follows:
    *   X'00' Ten inches
    *   X'01' Ten centimeters
*   **Units per unit base**: This value represents the number of units in the measurement base. It is specified as a two-byte numeric value between 1 and 32,767.

The term *units of measure* is defined as the measurement base value divided by the units per unit base value. [MODCA-3-073]

For example, if the measurement base is 10 inches and the units per unit base is 5000, then the units of measure is 10 inches / 5000 or one five-hundredth of an inch.

The base measurement units for each axis is specified as part of the definition of a presentation space. Each MO:DCA coordinate system may specify base measurement units independent from other coordinate systems appearing on the same medium. Although the overall architecture design permits each axis to have a different unit base, current implementations require that both unit bases be identical.

#### Measurement Unit Formats
The format used to resolve addressable positions into a unit of measure is a set of four parameters that specify the X and Y units of length used for measurements in the X and Y direction, respectively.

| Parameter | Description |
| :--- | :--- |
| X unit base | A one-byte code |
| Y unit base | A one-byte code |
| X units per unit base | A two-byte binary number from 1 through 32,767 in units of the X unit base |
| Y units per unit base | A two-byte binary number from 1 through 32,767 in units of the Y unit base |

Since presentation devices can be built to support different units of measure along different axes, the units of measure to which the presentation spaces have been designed can be specified in the data stream. The target presentation device may determine if it can accept the specified length unit, if it can convert from the specified addressable positions to one of its own, or if it recognizes a problem and possibly rejects that portion of the data stream. The origins of coordinate systems can be established at any addressable position that exists within a presentation space.

#### Extent
Each presentation space has two extents: the X extent, which parallels the X axis as it currently is oriented, and the Y extent, which parallels the Y axis as it currently is oriented. Extents start at the origin of a presentation space and end at a point determined by summing the extent value and the origin value. Negative extent values are not permitted since the area enclosed by a MO:DCA coordinate system always starts at the origin and proceeds in positive X and Y directions within its current orientation. In Figure 6 the X extent of the presentation area is represented by line segment 0R and the Y extent by line segment 0D. [MODCA-3-074]

**Figure 6. Presentation Space Extents**

The bottom edge of a presentation space is a line parallel to the X axis of the presentation space that intercepts the Y axis at the end point of the Y extent. The right edge of a presentation space is a line parallel to the Y axis of the presentation space that intercepts the X axis at the end point of the X extent.

The two extents specify the size of the presentation space. Using the example of a measurement base of 10 inches and a units per unit base of 5000, if the X extent were specified as 4250 and the Y extent as 5500, the presentation space size would be 8.5 by 11 inches.

#### Offset
The origin of any MO:DCA coordinate system is expressed as an offset from the origin of another coordinate system. The offset values for the X and Y axes can be positive or negative. Negative offset values are expressed in two's complement notation. Any MO:DCA coordinate system that is offset from a reference coordinate system need not be contained within that reference coordinate's extents.

The medium coordinate system is the base coordinate system from which all the other coordinate systems are directly or indirectly offset. A coordinate system for a document component that is placed within a superior document component references the coordinate system of the superior document component. For example, the coordinate system of an object or a page overlay that is placed on a page references the page's coordinate system. Since each MO:DCA coordinate system can be expressed in different base measurement units, the offset of the origin of a subordinate coordinate system, relative to the origin of the reference coordinate system, is always measured in the reference system's base measurement units. This permits the reference system to influence the placement of the contained system.

The offset coordinate system inherits the orientation of the reference coordinate system. In Figure 7 the origin for coordinate system B is offset ten X units and ten Y units from the reference coordinate system A. Coordinate system B's origin is specified as the intersection of the lines drawn perpendicular to the X and Y axes at the specified X and Y offset values from coordinate system A. [MODCA-3-075]

**Figure 7. Offset of a Coordinate System**

Any portion of a coordinate system may be overlapped by one or more peer coordinate systems. For example, two different object areas could be defined with the same origin so that one completely overlapped the other, or their origins could be specified such that only a portion of the object areas overlapped.

### Rotation

Rotation is used to change the presentation orientation of a document component with respect to that of the superior document component that contains it.

Orientation refers to the rotation of a document component and its coordinate system with respect to the coordinate system that contains it. After a MO:DCA coordinate system's origin and X and Y extents have been established, the orientation value of the coordinate definition may cause the defined space to rotate in a clockwise direction around its origin. Orientation is expressed in degrees and minutes, with the Y axis orientation value being 90 degrees greater than the X axis orientation value.

Figure 8 shows the effect of rotating one coordinate system, shown as a series of rectangles, within a containing coordinate system. Note how the X and Y extents, and thus the rectangle formed by these extents, rotate around the contained coordinate system's origin point of 3 and 4 units from the origin of the containing coordinate system. [MODCA-3-076]

**Figure 8. Examples of Coordinate System Orientation**

**Figure 9. Inheritance of Coordinate System Orientation**

The orientation characteristics possessed by a MO:DCA coordinate system do not have to be the same as those of its reference coordinate system. Any MO:DCA coordinate system may possess orientation characteristics that are the same as, or different from, their reference coordinate system or any other MO:DCA coordinate system. Figure 9 shows the effect of offsetting a page from a medium, then rotating it 90 degrees and then offsetting an object area from the page and rotating it 90 degrees. The object area inherited the 90 degree page rotation which, when added to its 90 degrees rotation, produced a cumulative orientation value of 180 degrees. [MODCA-3-077]

#### Rotation Units
The rotation of the X and Y axes of an object area are specified in terms of rotation units. Rotation unit values are expressed in degrees and minutes using two-byte, three-part binary numbers as shown in Table 10.

**Table 10. Format for Numbers Expressed in Rotation Units**

| Bit Position | Name | Meaning |
| :--- | :--- | :--- |
| Bit 0–Bit 8 | Degrees | Used to represent 0 through 359 degrees. Values from 360 through 511 are invalid. |
| Bit 9–Bit 14 | Minutes | Used to represent 0 through 59 minutes. Values from 60 through 63 are invalid. |
| Bit 15 | Reserved | Value must be zero. [MODCA-3-078] |

A rotation value of zero specifies no rotation with respect to the X axis of the presentation space in which the origin of the page, page overlay, object area, or object is located. Increasing values indicate increasing clockwise rotation. The four major orientations, plus-X, plus-Y, minus-X, and minus-Y, have values of 0 degrees, 90 degrees, 180 degrees, 270 degrees respectively. They are encoded as X'0000', X'2D00', X'5A00', and X'8700'. Most structured fields limit rotation to one of these four orientations. See Figure 10.

**Figure 10. Rotation of the X and Y Axes**

In addition, the data object area is subject to the full range of rotation. To obtain the rotation values one must take into careful consideration the multi-part bit-expanded derivation of the 2–byte CODE. For example, 123 degrees, 30 minutes rotation is represented as degrees (B'001111011') and minutes (B'011110') with the last bit (B'0') reserved. See Figure 11. [MODCA-3-078]

**Figure 11. Rotation Units for the Data Object Area — Arbitrary Orientation**

Overlays for a page are always positioned relative to the current orientation of the page coordinate system. However, their X and Y extent values remain constant regardless of the orientation. Figure 12 shows this graphically.

#### Shape
The X and Y axes are perpendicular to each other, and the rotation of the Y axis is exactly 90 degrees more than the rotation specified for the X axis. All MO:DCA presentation spaces must be rectangles. The shape of the data object is not defined by the MO:DCA architecture and can take on any visual appearance.

**Figure 12. A Page Overlay Applied to a Page in Two Different Orientations**

## Presentation Space Mixing

### Foreground and Background

MO:DCA presentation spaces such as the medium, page, overlay, and data object presentation spaces consist of two parts: foreground and background. Foreground is the part of the presentation space that is occupied with object data. This data can be pure object data such as text, or mixed object data such as image overlaying text. Background is the part of the presentation space that is not occupied with object data. For data object presentation spaces, the data object defines foreground and background, and may specify a color attribute for both. For each data object type, foreground, background, and color attributes are defined by the architecture that defines the object content.

For example, in a text presentation space, characters and rules are foreground, everything else is background. Foreground is assigned a color attribute using the “Set Extended Text Color” control sequence. Background cannot be assigned a color and is therefore implicitly assigned the color of the medium. When no color is specified for the background of a presentation space, the background is implicitly assigned the color of the medium. The medium, page, and overlay presentation spaces are initially empty. Empty MO:DCA presentation spaces contain only background, which is assigned the color of the medium.

Table 11 summarizes the definition of foreground and background in AFP OCA-based object presentation spaces:

**Table 11. Foreground/Background in Data Object Presentation Spaces**

| Data Type | Foreground | Background |
| :--- | :--- | :--- |
| PTOCA Text | * Stroked and filled portion of text characters<br>* Stroked area of text rules [MODCA-3-079]<br>* Stroked area of underscores [MODCA-3-080] | Everything else |
| IM image | B'1' image points | B'0' image points |
| IOCA bilevel image IOCA bilevel tiled image | Significant image points, except image points for which a transparency mask specifies B'0' | * Insignificant image points [MODCA-3-081]<br>* Image points for which a transparency mask specifies B'0' [MODCA-3-082]<br>* All portions of the presentation space not covered by image or tiles [MODCA-3-083] |
| IOCA grayscale or color image | Entire image, except image points for which a transparency mask specifies B'0' | * Image points for which a transparency mask specifies B'0' [MODCA-3-084]<br>* All portions of the presentation space not covered by image points [MODCA-3-085] |
| IOCA grayscale or color tiled image | Entire tile, except image points for which a transparency mask specifies B'0' | * Image points for which a transparency mask specifies B'0' [MODCA-3-086]<br>* All portions of the presentation space not covered by tiles [MODCA-3-087] |
| GOCA Graphics | * Stroked area of lines (including arcs)<br>* Stroked and filled portion of pattern symbols [MODCA-3-088]<br>* Stroked and filled portion of marker symbols [MODCA-3-089]<br>* Stroked and filled portion of graphic characters [MODCA-3-090]<br>* B'1' image points [MODCA-3-091]<br>* Entire area with solid fill [MODCA-3-092] | Everything else |
| BCOCA Bar Code | * Bars and 2D modules<br>* Stroked and filled portions of HRI characters [MODCA-3-093]<br>* Stroked and filled portion of all other toned constructs in the symbol (for example, Bearer Bars) [MODCA-3-094] | Everything else |
| Colored object area, page, or overlay presentation space | Complete presentation space | None |
| Empty object area, page, or overlay presentation space | None | Complete presentation space |
| Non-OCA Presentation Objects | See “Object Type Identifiers” | See “Object Type Identifiers” |

### Merging Presentation Spaces

Presentation spaces in a MO:DCA document are merged in the order in which the document components that define these presentation spaces appear in the data stream, as follows:

*   **Medium presentation space**: This is the base MO:DCA presentation space upon which all other presentation spaces are merged. [MODCA-3-095]
    *   **Medium overlay presentation space**: Merged on the medium presentation space with a keyword on the Medium Modification Control (MMC) structured field in a Medium Map. Medium overlays are merged on the medium presentation space before any pages are merged. Multiple medium overlay presentation spaces are merged in the order in which their keywords appear on the MMC structured field.
    *   **Page presentation space**: Merged on the medium presentation space in the order in which the corresponding page appears in the document, in accordance with the specifications in the active Medium Map.
        *   **Object area presentation space**: Merged on the page presentation space in the order in which the corresponding data object is included on the page.
*   **Data object presentation space**: Merged on the corresponding object area presentation space. [MODCA-3-096]
    *   **Page overlay presentation space**: If the page overlay is included via an IPO, it is merged on the page presentation space in the order in which the overlay is included on the page. If the page overlay is included via a PMC in a Medium Map, it is merged on the page presentation space before any data objects or overlays included via an IPO are merged.
*   **Object area presentation space**: Merged on the overlay presentation space in the order in which the corresponding data object is included on the overlay. [MODCA-3-097]
*   **Data object presentation space**: Merged on the corresponding object area presentation space. [MODCA-3-098]

The MO:DCA presentation space merge-order is shown in Figure 13.

**Figure 13. Merging Presentation Spaces**

**Figure Notes**:
1.  Merged first on the medium presentation space as specified in a Medium Map print control object. [MODCA-3-099] Multiple medium overlays are merged in the order in which they occur. If the overlay is a Medium Preprinted Form Overlay (M-PFO), one such overlay may be specified and is merged last onto the medium presentation space, after all other data for that medium presentation space has been merged.
2.  Merged first on the page presentation space as specified in a Medium Map print control object. [MODCA-3-100] Multiple overlays are merged in the order in which they occur in the data stream. If the overlay is a PMC Preprinted Form Overlay (PMC-PFO), one such overlay may be specified and is merged last onto the page presentation space, after all other data for that page presentation space has been merged.
3.  May occur multiple times and is merged in the order in which it occurs in the data stream. [MODCA-3-101]

### Mixing Rules

When multiple MO:DCA presentation spaces are merged, the background and foreground of the presentation spaces mix. The resultant foreground is the union of all presentation space foregrounds, that is, once an area is defined to be foreground, it remains foreground even if its color attribute is changed due to an “underpaint” mixing rule. The resultant background is everything else. The color of the resultant foreground and background is determined by the mixing rules specified in the MO:DCA architecture.

When a new presentation space Pn is merged onto an existing presentation space Pe, four types of mixing must be considered. Let Fe and Be denote the Pe foreground and background, respectively, and let Fn and Bn denote the Pn foreground and background, respectively, then the mixing types can be characterized as follows:

| Mixing Type | Description |
| :--- | :--- |
| Bn on Be | Background on background |
| Bn on Fe | Background on foreground |
| Fn on Be | Foreground on background |
| Fn on Fe | Foreground on foreground |

For each type of mixing, the resultant color is determined by the mixing rule that is specified. The following mixing rules are defined for presentation space mixing:

| Mixing Rule | Definition |
| :--- | :--- |
| Overpaint | When part of Pn overpaints part of Pe, the intersection is assigned the color attribute of Pn. This is also referred to as opaque or knock-out mixing. |
| Underpaint | When part of Pn underpaints part of Pe, the intersection keeps the color attribute of Pe. This is also referred to as transparent mixing or leave alone mixing. |
| Blend | When part of Pn blends with part of Pe, the intersection assumes a new color attribute which represents a color-mixing of the color attribute of Pn with the color attribute of Pe. For example, if Pn has foreground color attribute blue and Pe has foreground color attribute yellow, the area where the two foregrounds intersect would assume a color attribute of green. |

#### Default Mixing Rule
When no presentation space mixing rule is specified, the following default MO:DCA mixing rule applies: When a new presentation space Pn is merged onto an existing presentation space Pe, the background of Pn underpaints the background and foreground of Pe, and the foreground of Pn overpaints the background and foreground of Pe.

This default mixing rule can be summarized as follows:

**Table 12. Default Color Mixing Rules**

| Mixing Type | Default Mixing Rule |
| :--- | :--- |
| Bn on Be | Underpaint |
| Bn on Fe | Underpaint |
| Fn on Be | Overpaint |
| Fn on Fe | Overpaint |

#### Preprinted Form Overlay (PFO) Mixing
Preprinted Form Overlays (PFOs) are designed to enable proper simulation of preprinted forms, particularly their appearance when color data is presented on the form. This requires the definition of a special mixing rule, called Formblend, which is the mixing rule for foreground on foreground mixing when page data and other overlay data is merged with the PFO data. The Formblend mixing rule makes use of the fact that the PFO, whether it is an M-PFO or an PMC-PFO, is always merged last on the presentation space with which it is associated.

*   **Formblend**: This mixing rule is only used when a simulated preprinted form, which is simulated as either a Medium Preprinted Form overlay (M-PFO) or a PMC Preprinted Form overlay (PMC-PFO), is merged as a new presentation space Pn, onto an existing presentation space Pe. The intersection of Pn and Pe is assigned the following color attribute:
    *   Wherever the color attribute of Pe is either the color of medium, or the color white (CMYK = X'00000000' or RGB = X'FFFFFF'), the intersection is assigned the color attribute of Pn. [MODCA-3-102]
    *   Wherever the color attribute of Pe is not the color of medium and not the color white, the intersection assumes a new color attribute that is generated in a device-specific manner to simulate how the Pe color attribute would mix onto a preprinted form that has the color attribute of Pn. In general, this mixing is a blending of the color attributes of Pn and Pe that is determined by the two color attributes and by the print media and the print technology. [MODCA-3-103]

*Implementation Note*: Since the result of merging one color with an existing color on a sheet is, in general, a darker color, it is recommended that the mixing rule used to implement Formblend simulates this behavior. A mixing rule with this property is defined as “Multiply” in PDF 1.7. Use of this rule is recommended, particularly for non-printing devices such as viewers.

The complete mixing rules for PFOs are defined as follows. Since, by definition, a PFO presentation space is always merged last on its corresponding presentation space, Pn corresponds to the PFO presentation space in this table:

**Table 13. Color Mixing Rules for PFOs**

| Mixing Type | Mixing Rule |
| :--- | :--- |
| Bn (PFO) on Be | Underpaint |
| Bn (PFO) on Fe | Underpaint |
| Fn (PFO) on Be | Overpaint |
| Fn (PFO) on Fe | Formblend |

#### UP3i Print Data Mixing
Special mixing rules are defined for mixing the UP3i Print Data object type with other data on a page or overlay. In that case, since the print data is presented by a UP3i device after (or possibly before) the complete page or overlay is rendered by the printer, the presentation container cannot mix with the remainder of the page data according to the default MO:DCA mixing rules. A new type of mixing is therefore architected for UP3i Print Data:

*   The object area of the presentation container mixes in accordance with the default MO:DCA mixing rules. An empty object area is transparent. If a Presentation Space Reset (X'70') Mixing triplet is specified on the OBD, it can reset the space under the object area to color of medium. If a Color Specification (X'4E') triplet is specified on the OBD, it can color the object area. Any object on the page that is specified after the Print Data object can overpaint the object area with other data. [MODCA-3-104]
*   The UP3i Print Data object is processed in its own presentation space by the UP3i device in accordance with the Print Data format, as identified with the Print Data Format ID in the first 4 bytes of the object. It mixes with the remainder of the page data in a manner that is defined by the Print Data format. [MODCA-3-105]

## Color Management

The AFP Color Management Architecture (ACMA) is based on the concept of a color management resource (CMR). A CMR is an architected resource that is used to carry all of the color management information required to render a print file, document, group of pages or sheets, page, or data object with color fidelity. CMRs are defined in the Color Management Object Content Architecture (CMOCA).

### CMR Names
A CMR is identified with a fixed-length name that is specified in the CMR header and that is generated based on an architected naming scheme to ensure uniqueness.

### CMR Types
Each CMR carries a single type of color management resource. The following CMR types are defined:

*   **Color conversions (CCs)**: CMRs that carry ICC profiles which tie a device-specific color to or from the profile connection space (PCS).
*   **Halftones (HTs)**: CMRs that are applied to multi-bit data.
*   **Indexed (IX) CMRs**: CMRs that map indexed colors in the data to output device colors or colorant combinations. The device colors can be one of the following:
    *   A fractional mixture of one or more specific device colorants. [MODCA-3-106]
    *   A presentation-system-dependent process color (CMYK for printers, RGB for displays). [MODCA-3-107]
    *   A gray value. [MODCA-3-108]
    *   A CIELAB value. [MODCA-3-109]
*   **Link color conversions**: CMRS that provide look-up tables (LUTs) that convert directly from an input color space to the output color space of the presentation device.
    *   **Link LK CMRs**: Generated and processed internally in AFP systems; not exposed to the application. [MODCA-3-110]
    *   **Link DL CMRs**: Carry ICC DeviceLink Profiles; exposed to the application and referenced in the data stream.
*   **Tone transfer curves (TTCs)**: CMRs that are used to modify the values of a particular color component.

### Processing Modes
The attributes that dictate how the CMR is processed are referred to as processing modes:

*   **Audit**: Reflects processing that has been done on a document component.
*   **Instruction**: Specifies processing that is to be done to a document component.
*   **Link**: Links an input color space to the output color space of the presentation device. Only Link color conversion CMRs can be processed as link CMRs.

### Generic CMRs
Halftone CMRs and tone transfer curve CMRs can be specified in a generic sense and referenced as instruction CMRs to request an intended output appearance. Such CMRs are called generic CMRs. They are never used directly by an output device; they are always replaced by device-specific CMRs.

### Passthrough CMRs
Color Conversion CMRs can be generated to force a passthrough of the colors in a presentation device without being subject to color management. This is done by specifying the character string “pasthru” in the version field of the CMR name. [MODCA-3-111]

**Table 14. CMR Type: Processing Mode and Generic Capability**

| CMR type | Non-generic CMR: Audit | Non-generic CMR: Instruction | Non-generic CMR: Link | Generic CMR: Audit | Generic CMR: Instruction | Generic CMR: Link |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| Color conversion (CC) | Valid | Valid | Invalid: error | Invalid: error | Invalid: error | |
| Tone transfer curve (TTC) | Valid | Valid | | Valid: ignored | Valid | |
| Halftone (HT) | Valid: ignored | Valid | | Valid: ignored | Valid | |
| Indexed (IX) | Valid: ignored | Valid | | | | Invalid: error |
| Link (LK and DL) | Invalid: error | Valid | | | | Invalid: error |

*Server Considerations*:
1.  Servers should download all valid combinations of CMR type and processing mode, even if the device ignores them. [MODCA-3-112]
2.  Servers should not download invalid combinations of CMR type and processing mode. [MODCA-3-113]

### CMR Installation
CMRs in resource libraries are accessed using a CMR Resource Access Table (RAT). For generic CMRs, the install program automatically builds a CMR RAT entry for each architected generic CMR name. [MODCA-3-114]

### Associating CMRs with Document Components
CMRs are associated with document components in the following manner:

*   **Print file**: Referenced in the Document Environment Group (DEG) of the form definition.
*   **Document**: Referenced for the print file and targeting a specific document.
*   **Group of pages or sheets**: Referenced in the medium map.
*   **Page or overlay**: Referenced in the Active Environment Group (AEG).
*   **Data object**: Associated in multiple ways:
    *   Installed with a data object Resource Access Table (RAT). [MODCA-3-115]
    *   Included on a page/overlay with an IOB, specifying the CMR name as an external resource reference. [MODCA-3-116]
    *   Specified directly on the page/overlay, referencing the CMR in its OEG. [MODCA-3-117]
    *   Associated with an image object in a QR Code with Image bar code. [MODCA-3-118], [MODCA-3-119], [MODCA-3-120]
    *   Embedded CMR-like information (e.g., ICC profile in a TIFF object). [MODCA-3-121]

### Rendering Intent
Rendering intent is used to modify the appearance of color data. The ICC defines four rendering intents:
*   Perceptual [MODCA-3-122]
*   Saturation [MODCA-3-123]
*   Media-relative colorimetric [MODCA-3-124]
*   ICC-absolute colorimetric [MODCA-3-125]

Rendering intents can be specified independently for each major AFP color object type category:
*   IOCA objects [MODCA-3-126]
*   Object containers (EPS, PDF, TIFF, etc.) [MODCA-3-127]
*   PTOCA text [MODCA-3-128]
*   GOCA graphics objects [MODCA-3-129]

Rendering intents may be associated with a MO:DCA document component:
*   Print file [MODCA-3-130]
*   Document [MODCA-3-131]
*   Group of pages or sheets [MODCA-3-132]
*   Page or overlay [MODCA-3-133]
*   Data object [MODCA-3-134], [MODCA-3-135]

### CMRs and Print Media
CMRs may be tuned to specific media; indicated by one of the following four media attributes:
*   Media brightness [MODCA-3-136]
*   Media color [MODCA-3-137]
*   Media finish [MODCA-3-138]
*   Media weight [MODCA-3-139]

The selection algorithm follows:
*   If none are specified, the printer uses it. [MODCA-3-140]
*   If one or more are invalid, exception processing mode is entered. [MODCA-3-141]
*   If all are specified and valid, it is used if all match; otherwise, search the hierarchy. [MODCA-3-142]
*   If some are specified and valid, it searches for a better match. [MODCA-3-143]

### CMR Processing

#### CMR association and scope
CMRs are associated with a document component implicitly. [MODCA-3-144]
*   Print file level: MDR in DEG. [MODCA-3-145]
*   Document level: MDR in DEG, pointing to specific document. [MODCA-3-146]
*   Group of pages level: MDR in medium map. [MODCA-3-147]
*   Page or overlay level: MDR in AEG. [MODCA-3-148]
*   Data object level: RAT, IOB, PPO, or MDR in OEG. [MODCA-3-149]

#### CMR processing mode
*   With a CMR Descriptor triplet on the MDR in the OEG. [MODCA-3-150]
*   With a CMR Descriptor triplet on the IOB. [MODCA-3-151]
*   With a CMR Descriptor triplet on the PPO. [MODCA-3-152]
*   With a CMR Descriptor table vector in the data object RAT. [MODCA-3-153]

#### Generic CMR processing
*   A server checks whether device-specific CMRs have been mapped. [MODCA-3-154]
*   If no matching device-specific CMR is mapped, server downloads the generic CMR. [MODCA-3-155]
*   The printer processes the CMR hierarchy; substituting a default if the active is generic. [MODCA-3-156]

#### CMR exception processing
*   Link LK CMRs mapped to color conversion CMRs. [MODCA-3-157]
*   Device-specific HT/TTC CMRs mapped to generic CMRs. [MODCA-3-158]

#### CMRs in Print file level Resource Groups
*   Using the MDR to Map a Color Management Resource (CMR). [MODCA-3-159]

## Metadata Objects in AFP

A Metadata Object (MO) is an architected object used to carry descriptive metadata. MOs are defined in the Metadata Object Content Architecture (MOCA).

## Font Technologies

The MO:DCA architecture supports two classes of font technologies:
*   **FOCA fonts**: Defined by FOCA; referenced via MCF.
*   **Non-FOCA fonts**: Also called data-object fonts (e.g., TrueType/OpenType); referenced via MDR.

### Relationship Between FOCA Character Metrics and TrueType Character Metrics

**Figure 14. Horizontal Metrics: TrueType/OpenType Fonts and FOCA Fonts**

**Figure 15. Vertical Metrics: TrueType/OpenType Fonts and FOCA Fonts**

## Document Indexing

The document index is delimited by a Begin Document Index (BDI) and End Document Index (EDI). MO:DCA elements within a document index include:
*   **Index Element (IEL)**: Supports indexing of pages and page groups.
*   **Link Logical Element (LLE)**: Supports logical connections.
*   **Tag Logical Element (TLE)**: Supports content-based tagging.

### Index Elements
The IEL structured field provides the following information:
*   Direct byte offset from the start of the Begin Document. [MODCA-3-160]
*   Byte extent of the indexed object. [MODCA-3-161]
*   Structured field offset. [MODCA-3-162]
*   Structured field extent. [MODCA-3-163]
*   Object offset using a specified object type. [MODCA-3-164]
*   Object extent using a subordinate object type. [MODCA-3-165]
*   If the indexed object is a page: [MODCA-3-166]
    *   Medium map object name
    *   Number of the indexed page
    *   PGP repeating group used
*   If the indexed object is a page group: [MODCA-3-167]
    *   Number of pages preceding/contained
    *   Active medium map name
    *   PGP repeating group used

**Figure 16. Page level IEL: Offset and Extent**

**Figure 17. Page group level IEL: Offset and Extent [MODCA-3-168]**

**Figure 18. Page level IEL: Use of Medium Map Information**

### Tag Logical Elements
The element to be tagged may be identified using an FQN triplet on the TLE:
*   FQN type X'87' triplet for a page [MODCA-3-169]
*   FQN type X'0D' triplet for a page group [MODCA-3-170]

The TLE structured field tags the element with:
*   Name of the attribute [MODCA-3-171]
*   Value of the attribute [MODCA-3-172]
*   Sequence number of the attribute [MODCA-3-173]
*   Level number of the attribute [MODCA-3-174]

**Figure 19. A Document with Logical Tags [MODCA-3-175]**

## Document Links

Document links are supported with Link Logical Element (LLE) structured fields.

**Figure 20. Document Annotation using the LLE**

## N-up Presentation

N-up is a format where multiple pages are presented on a single physical medium. Each side is divided into "N" equal-size partitions. [MODCA-3-176]

**Figure 21. N-up Partitions for Various Physical Media [MODCA-3-177]**

## Cut-sheet Emulation (CSE) Print Mode

IPDS printers may provide a cut-sheet emulation mode on continuous-forms media. The two portions are called sheetlets.

**Figure 22. Logical Division of Continuous Forms for Cut-sheet Emulation [MODCA-3-178]**

## Simulation of Preprinted Forms

Preprinted forms are simulated with preprinted form overlays (PFOs):
*   **Medium Preprinted Form Overlay (M-PFO)**: Used on a sheet-side. [MODCA-3-179]
*   **PMC Preprinted Form Overlay (PMC-PFO)**: Used on a page. [MODCA-3-180]

PFOs use the Formblend mixing rule:
1.  Color of preprinted forms cannot be knocked out. [MODCA-3-181]
2.  Blending occurs when non-white color is applied. [MODCA-3-182]

When PFO data is merged:
*   If underlying color is medium/white, result is PFO color. [MODCA-3-183]
*   If underlying color is not medium/white, result is a device-specific blending. [MODCA-3-184] [MODCA-3-185]

## Document Finishing

Finishing operations (stapling, folding) have defined scope:
*   Print file level finishing. [MODCA-3-186]
*   Document level finishing, all documents. [MODCA-3-187]
*   Document level finishing, selected document. [MODCA-3-188]
*   Medium map level finishing, group of sheets. [MODCA-3-189]
*   Medium map level finishing, each sheet. [MODCA-3-190]

Triplets supported:
*   Finishing Operation (X'85') triplet. [MODCA-3-191]
*   UP3i Finishing Operation (X'8E') triplet. [MODCA-3-192]

Nesting rules apply based on scope and order. [MODCA-3-193], [MODCA-3-194]

## Exception Conditions

Architecture violations are handled as exception conditions.

### Classifications
Exception conditions can be classified as:
*   **Syntactic**: [MODCA-3-195]
    *   Invalid/unknown SFI. [MODCA-3-197]
    *   Invalid/unknown parameter. [MODCA-3-198]
    *   Invalid parameter value. [MODCA-3-199]
    *   Component/Structured field/Parameter missing or invalid location. [MODCA-3-200], [MODCA-3-201], [MODCA-3-202]
*   **Semantic**: [MODCA-3-196]
    *   Inconsistent/contradictory specifications. [MODCA-3-203]
    *   Invalid relationships. [MODCA-3-204]

### Detection
The MO:DCA architecture defines eight categories of exception conditions:

*   **Invalid structured field identifier**
*   **Unrecognized identifier code**
*   **Data stream state violation**:
    *   Repetition not permitted. [MODCA-3-205]
    *   Appearance where not permitted. [MODCA-3-206]
    *   Appearance outside specified order. [MODCA-3-207]
*   **Unrecognized structured field or triplet**:
    *   SFI containing invalid category code. [MODCA-3-208]
    *   Triplet containing invalid identifier. [MODCA-3-209]
*   **Required structured field missing**
*   **Required parameter missing**: [MODCA-3-210]
*   **Unacceptable parameter value**
*   **Inconsistent parameter values**

**Table 15. Bit Representation of MO:DCA Exception Condition Categories**

| Bit Position | Exception Condition Category | Binary Code | Hexadecimal Code |
| :---: | :--- | :---: | :---: |
| Bit 0 | Invalid structured field identifier | B'10000000' | X'80' |
| Bit 1 | Unrecognized identifier code | B'01000000' | X'40' |
| Bit 2 | Data stream state violation | B'00100000' | X'20' |
| Bit 3 | Unrecognized structured field or triplet | B'00010000' | X'10' |
| Bit 4 | Required structured field missing | B'00001000' | X'08' |
| Bit 5 | Required parameter missing | B'00000100' | X'04' |
| Bit 6 | Unacceptable parameter value | B'00000010' | X'02' |
| Bit 7 | Inconsistent parameter values | B'00000001' | X'01' |
| None | None | B'00000000' | X'00' |

Exception action is presentation-system dependent. [MODCA-3-211]
