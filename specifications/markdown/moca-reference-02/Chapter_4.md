# Chapter 4. Metadata Object (MO)

An MO consists of a header followed by MO data. [MOCA-4-001]

## MO Syntax

This is the syntax of an MO. [MOCA-4-002]

Data contained in fixed-length fields that are encoded as UTF-16BE is left-aligned. If the number of bytes used by the characters in these fields is smaller than the field length, the remaining bytes will be padded with “@” (X'0040'). [MOCA-4-003]

### Table 4. MO Syntax

| Length | Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 4 | 0–3 | UBIN | MOLength | X'00000032' - X'FFFFFFFF' | MO length, including this MOLength field | M [MOCA-4-004]|
| **MO header starts here** [MOCA-4-005]| | | | | | |
| 2 | 4–5 | UBIN | HeaderLength | X'002E' - end of header | MO header length, including this HeaderLength field | M [MOCA-4-006]|
| 6 | 6–11 | UTF16 | MOType | DES (X'0044 0045 0053') | Descriptive | M [MOCA-4-007]|
| 8 | 12–19 | UTF16 | MOFormat | AFPT (X'0041 0046 0050 0054') | AFP Tagging | M [MOCA-4-008]|
| | | | | XMP (X'0058 004D 0050 0040') | Extensible Metadata Platform (XMP) [MOCA-4-009]| |
| 20 | 20–39 | UTF16 | MOCompression | NONE (X'004E 004F 004E 0045 0040 0040 0040 0040 0040 0040') | Uncompressed | M [MOCA-4-010]|
| | | | | GZIP (X'0047 005A 0049 0050 0040 0040 0040 0040 0040 0040') | “Gzip” text compression [MOCA-4-011]| |
| | | | | EXI (X'0045 0058 0049 0040 0040 0040 0040 0040 0040 0040') | Efficient XML Interchange (EXI) compression [MOCA-4-012]| |
| 8 | 40–47 | | | X'0000000000000000' | Reserved - should be set to zero | M [MOCA-4-013]|
| 2 | 48–49 | UBIN | MONameLength | X'0000' - X'00FA', even values only | Length, in bytes, of the MOName field that follows | M [MOCA-4-014]|
| 0–250 | 50–end of name | UTF16 | MOName | Any valid UTF-16BE characters (thus an even number of bytes) | A human-readable MO name in UTF-16BE | O [MOCA-4-015]|
| End of name–end of header | | UNDF | | | Reserved for future use; receivers should accept but ignore; generators should not specify | O [MOCA-4-016]|
| **MO header ends here** [MOCA-4-017]| | | | | | |
| | | | MOData | | Any MO Data | O [MOCA-4-018]|

*M/O: Mandatory or Optional field* [MOCA-4-019]

## MO Semantics

* **MOLength**: The length of the complete MO, including the MOLength parameter. MOLength, in bytes, may be 50 (X'00000032') to X'FFFFFFFF'. If an invalid value is found in this field, the optional exception is EC-0100. [MOCA-4-020]
* **HeaderLength**: The length of the MO header, including the HeaderLength parameter. HeaderLength, in bytes, may be any value greater than or equal to 46 (X'002E'). If an invalid value is found in this field, the optional exception is EC-0200. [MOCA-4-021]
* **MOType**: One MOType is defined in the Metadata Object Content Architecture. The defined MOType is DES. See “MOType”. If an invalid or unsupported value is found in this field, the optional exception is EC-0220. [MOCA-4-022]
* **MOFormat**: MOFormat is defined in the Metadata Object Content Architecture to indicate the format of MO data. See “MOFormat”. If an invalid or unsupported value is found in this field, the optional exception is EC-0230. [MOCA-4-023]
* **MOCompression**: MOCompression is defined in the Metadata Object Content Architecture to indicate the type of compression applied to MO data. See “MOCompression”. If an invalid or unsupported value is found in this field, the optional exception is EC-0240. [MOCA-4-024]
* **MONameLength**: The length of the MOName field. MONameLength, in bytes, may be any even value from 0 (X'0000') to 250 (X'00FA'). If an invalid value is found in this field, the optional exception is EC-0250. [MOCA-4-025]
* **MOName**: A user-defined string containing an optional human-readable MO name. This field can contain up to 250 bytes; therefore, if the UTF-16BE string contains no surrogates, the MO name can contain up to 125 characters. If the environment containing the MO has a method of referencing an MO by name, this field is to be used as the name. If an invalid value is found in this field, the optional exception is EC-0210. [MOCA-4-026]
* **MOData**: MO data. The format of the metadata is determined by the value of the MOFormat parameter. If an invalid value is found in this field, the optional exception is EC-0300. [MOCA-4-027]

## MO Exception Conditions

Both recognition and reporting of exception conditions is optional. Exception conditions have a format of EC-xxxx. [MOCA-4-028]

The exception conditions are as follows: [MOCA-4-029]

* **EC-0100 Invalid Length Value**: The specified MOLength is invalid. [MOCA-4-030]
* **EC-0200 Invalid Field Value**: The specified HeaderLength is invalid. [MOCA-4-031]
* **EC-0210 Invalid Field Value**: The specified MOName is not valid UTF-16BE. [MOCA-4-032]
* **EC-0220 Invalid or Unsupported Field Value**: The specified MOType is invalid or unsupported. [MOCA-4-033]
* **EC-0230 Invalid or Unsupported Field Value**: The specified MOFormat is invalid or unsupported. [MOCA-4-034]
* **EC-0240 Invalid or Unsupported Field Value**: The specified MOCompression is invalid or unsupported. [MOCA-4-035]
* **EC-0250 Invalid Field Value**: The specified MONameLength is invalid. [MOCA-4-036]
* **EC-0300 Invalid MOData**: The specified MOData does not meet the specification associated with the indicated MOFormat. [MOCA-4-037]

In the IPDS environment, MOCA exception conditions are mapped to IPDS exceptions and reported. To map a MOCA exception condition to an IPDS exception, the rule is simply to add X'06' on the front of the four digits of the MOCA exception condition. For example, MOCA exception condition EC-0220 becomes IPDS exception X'0602..20'. [MOCA-4-038]
