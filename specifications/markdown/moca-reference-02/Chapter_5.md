# Chapter 5. MO Header Attributes

Attribute fields for MO type, format, and compression are carried in the MO header. Each of these fields is described below. [MOCA-5-001]

## MOType

The following MO types are defined:
* **DES** [MOCA-5-002]

Each value for MOType is described in more detail below. [MOCA-5-003]

### DES
**MOType = DES (X'004400450053')** [MOCA-5-004]

#### Description
MOType DES refers to descriptive metadata used to label, tag, or otherwise describe elements of a print file. Common descriptive metadata are attributes such as Title, Date, and Author. Descriptive metadata is distinct from the primary content of a document and does not affect the architected rendering of data. [MOCA-5-005]

## MOFormat

The following MO formats are defined:
* **AFP Tagging** [MOCA-5-006]
* **XMP** [MOCA-5-007]

Each value for MOFormat is described in more detail below. [MOCA-5-008]

### AFP Tagging
**MOFormat = AFPT (X'0041004600500054')** [MOCA-5-009]

#### Description
MOFormat AFPT refers to a metadata object using the AFP Tagging definition, which defines a schema in XML format for both identifying some semantics and tagging the data in the AFP that correspond to those semantics. For example, AFP Tagging metadata can be used to state that some set of bytes in the AFP correspond to a figure, or a paragraph, or a hyperlink; this type of information could be used in a screen reader, to enable universal accessibility. [MOCA-5-010]

See the *Metadata Guide for AFP* for the definition of the syntax of AFP Tagging metadata. [MOCA-5-011]

### XMP
**MOFormat = XMP (X'0058004D00500040')** [MOCA-5-012]

#### Description
MOFormat XMP refers to a metadata object using the Extensible Metadata Platform (XMP) data model, serialization, and core properties. [MOCA-5-013]

See the *XMP Specification dated September 2005* for a complete specification of XMP . [MOCA-5-014]

## MOCompression

The following MO compression formats are defined:
* **NONE** [MOCA-5-015]
* **GZIP** [MOCA-5-016]
* **EXI** [MOCA-5-017]

Each value for MOCompression is described in more detail below. [MOCA-5-018]

### NONE
**MOCompression = NONE (X'004E004F004E0045004000400040004000400040')** [MOCA-5-019]

#### Description
The MO data is uncompressed. [MOCA-5-020]

### GZIP
**MOCompression = GZIP (X'0047005A00490050004000400040004000400040')** [MOCA-5-021]

#### Description
The MO data is compressed as text using GZIP. [MOCA-5-022]

See *RFC 1952 - GZIP file format specification version 4.3*. [MOCA-5-023]

### EXI
**MOCompression = EXI (X'0045005800490040004000400040004000400040')** [MOCA-5-024]

#### Description
The MO data is compressed as XML using Efficient XML Interchange. [MOCA-5-025]

See *Efficient XML Interchange (EXI) Format 1.0*. [MOCA-5-026]
