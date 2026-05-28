# Chapter 15. Triplets
Triplets are variable-length substructures that can be used within one or more IPDS commands to provide
additional information for the command. A triplet is a three-part self-defining variable-length parameter
consisting of a length byte, an identifier byte, and parameter-value bytes.
The following triplets are used within IPDS commands: [IPDS-15-001]
### Table 57. Triplets Summary

| Triplet ID | Triplet Name | Carrying Commands |
| :--- | :--- | :--- |
| X'00' | Group ID | ISP, XOA RRL, XOH DGB, XOH DSPG, XOH RSPG [IPDS-15-002]|
| X'01' | Coded Graphic Character Set Global Identifier | AR, XOH DGB [IPDS-15-003]|
| X'02' | Fully Qualified Name | AR, WOCC [IPDS-15-004]|
| X'4E' | Color Specification | IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, WTC [IPDS-15-005]|
| X'50' | Encoding Scheme ID | AR [IPDS-15-006]|
| X'5A' | Object Offset | IDO, RPO, WOCC [IPDS-15-007]|
| X'62' | Local Date and Time Stamp | AR [IPDS-15-008]|
| X'6E' | Group Information | XOH DGB [IPDS-15-009]|
| X'70' | Presentation Space Reset Mixing | IDO, LPD, WBCC, WGC, WIC2, WOCC, WTC [IPDS-15-010]|
| X'74' | Toner Saver | PFC [IPDS-15-011]|
| X'75' | Color Fidelity | PFC [IPDS-15-012]|
| X'79' | Metric Adjustment | AR [IPDS-15-013]|
| X'84' | Font Resolution and Metric Technology | AR [IPDS-15-014]|
| X'85' | Finishing Operation | AFO, XOH DGB [IPDS-15-015]|
| X'86' | Text Fidelity | PFC [IPDS-15-016]|
| X'88' | Finishing Fidelity | PFC [IPDS-15-017]|
| X'8B' | Data Object Font Descriptor | AR [IPDS-15-018]|
| X'8D' | Linked Font | AR [IPDS-15-019]|
| X'8E' | UP3I Finishing Operation | AFO, XOH DGB [IPDS-15-020]|
| X'91' | Color Management Resource Descriptor | AR, home-state WOCC [IPDS-15-021]|
| X'92' | Invoke CMR | IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, WTC [IPDS-15-022]|
| X'95' | Rendering Intent | IDO, LPD, RPO, SPE, WGC, WIC2, WOCC, WTC [IPDS-15-023]|
| X'96' | CMR Tag Fidelity | PFC [IPDS-15-024]|
| X'97' | Device Appearance | SPE [IPDS-15-025]|
| X'9A' | Image Resolution | IDO, RPO, WOCC [IPDS-15-026]|
| X'9C' | Object Container Presentation Space Size | IDO, RPO, WOCC [IPDS-15-027]|
| X'9E' | Setup Name | ASN, XOA RSNL [IPDS-15-028]|
| X'A2' | Invoke Tertiary Resource | WBCC [IPDS-15-029]|
Note: Triplet IDs are registered in the MO:DCA architecture and where triplets are used in other architectures,
they are defined consistently among the using architectures. Some triplet IDs are registered for use only
in a particular architecture and in these cases, there is no MO:DCA equivalent. The triplet format for
MO:DCA-defined triplets restricts the triplet length field to the range 3–254. However, some triplets that
are defined for use only in IPDS allow a larger range (2–255); IPDS triplets that allow this larger range
include:
Group ID (X'00') triplet
Group Information (X'6E') triplet
Linked Font (X'8D') triplet
Invoke Tertiary Resource (X'A2') triplet [IPDS-15-030]


### Group ID (X'00') Triplet

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'02'–X'FF' | Length of the triplet, including this length field | X'02'–X'FF' [IPDS-15-031]|
| 1 | CODE | TID | X'00' | Group ID triplet | X'00' [IPDS-15-032]|
| 2 | CODE | Format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'08'<br>X'13' | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>AIX and Windows print-data<br>Variable-length Group ID format<br>Extended OS/400 print-data format | See byte description [IPDS-15-033]|
| 3 to end | | Data | | Data bytes | See byte description [IPDS-15-034]|
Byte 0 Triplet length
This byte contains the length of this triplet, including itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists.
If a group triplet is too big to fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This byte indicates the type of field being defined. The only valid value is X'00' and defines the
following triplet field type:
• X'00'—Group ID [IPDS-15-035]
If present, the Group ID (X'00') triplet contained in the terminate group command (XOH-DGB
order type = X'01') should match that in the associated begin group command.
Byte 2 Format
This byte identifies the format of the data portion of this triplet. The “Recognized Group ID Formats Self-Defining Field” lists the group ID formats that are recognized by the
printer. The printer must accept all formats (but unrecognized formats are ignored and don't
need to be supplied); this self-defining field can help a host program to determine which Group
ID formats to supply.
When the printer supports saved pages, as indicated by the Supported Group Operations self-
defining field in an XOH-OPC reply, the variable-length Group ID format (X'08') must be
supported for use with ISP, XOA-RRL, XOH-DGB, XOH-DSPG, and XOH-RSPG commands.
Bytes 3 to end
of triplet
Data bytes
These bytes, if present, contain parameter data in one of several possible formats; the format
is identified in triplet byte 2.
Note: The character data in the following bytes is encoded as single-byte data. The single-
byte EBCDIC character data is encoded with code page 500 and character set 103.
The single-byte ASCII character data is encoded with code page 850 and the subset of
character set 697 covered by character set 103.
Triplet X'00'—Group ID [IPDS-15-036]


Valid triplet data-byte formats for the defined XOH-SGO operations are as follows:
• Keep group together as a print unit [IPDS-15-037]

**MVS and VSE (Format X'01')** [IPDS-15-038]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'01'—MVS and VSE print-data format identifier [IPDS-15-039]|
| 3 | CHAR | CLASS | The one-character EBCDIC job CLASS parameter [IPDS-15-040]|
| 4–11 | CHAR | Job Name | The eight-character EBCDIC job name parameter [IPDS-15-041]|
| 12–19 | CHAR | Job ID | The eight-character EBCDIC job identification number [IPDS-15-042]|
| 20–27 | CHAR | FORMS | The eight-character EBCDIC job FORMS parameter [IPDS-15-043]|

**VM (Format X'02')** [IPDS-15-044]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'02'—VM print-data format identifier [IPDS-15-045]|
| 3 | CHAR | Class | The one-character EBCDIC spool class value [IPDS-15-046]|
| 4–11 | CHAR | Filename | The eight-character EBCDIC filename of the spool print file [IPDS-15-047]|
| 12–19 | CHAR | User ID | The eight-character EBCDIC userid of the print file originator [IPDS-15-048]|
| 20–27 | CHAR | Formname | The eight-character EBCDIC spool formname value [IPDS-15-049]|
| 28–31 | CHAR | Spool ID | The four-character EBCDIC spool identification number (spoolid) [IPDS-15-050]|
For OS/400 there are two formats defined because the V5R1 level of the OS/400 operating
system increased the maximum number of spooled files that can be created per job from
9999 to 999999. The first format (X'03') provides a 4 character spooled file number. The [IPDS-15-051]
second format (X'13') provides a 6 character spooled file number.
**OS/400 (Format X'03')** [IPDS-15-052]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'03', OS/400 print-data format identifier [IPDS-15-053]|
| 3–12 | CHAR | Library | The ten-character EBCDIC library name [IPDS-15-054]|
| 13–22 | CHAR | Output Queue | The ten-character EBCDIC output queue name [IPDS-15-055]|
| 23–32 | CHAR | Spooled File | The ten-character EBCDIC spooled file name [IPDS-15-056]|
| 33–36 | CHAR | Spool Number | The four-character EBCDIC spooled file number [IPDS-15-057]|
| 37–46 | CHAR | Job Name | The ten-character EBCDIC job name [IPDS-15-058]|
| 47–56 | CHAR | User Name | The ten-character EBCDIC user name [IPDS-15-059]|
| 57–62 | CHAR | Job Number | The six-character EBCDIC job number [IPDS-15-060]|
| 63–72 | CHAR | Forms Name | The ten-character EBCDIC forms name parameter [IPDS-15-061]|
**Extended OS/400 (Format X'13')** [IPDS-15-062]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'13', Extended OS/400 print-data format identifier [IPDS-15-063]|
| 3–12 | CHAR | Library | The ten-character EBCDIC library name [IPDS-15-064]|
| 13–22 | CHAR | Output Queue | The ten-character EBCDIC output queue name [IPDS-15-065]|
| 23–32 | CHAR | Spooled File | The ten-character EBCDIC spooled file name [IPDS-15-066]|
| 33–38 | CHAR | Spool Number | The six-character EBCDIC spooled file number [IPDS-15-067]|
| 39–48 | CHAR | Job Name | The ten-character EBCDIC job name [IPDS-15-068]|
| 49–58 | CHAR | User Name | The ten-character EBCDIC user name [IPDS-15-069]|
| 59–64 | CHAR | Job Number | The six-character EBCDIC job number [IPDS-15-070]|
| 65–74 | CHAR | Forms Name | The ten-character EBCDIC forms name parameter [IPDS-15-071]|
**AIX and Windows (Format X'06')** [IPDS-15-072]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'06', AIX and Windows print-data format identifier [IPDS-15-073]|
| 3–253 | CHAR | Job Name | The 1–251 character ASCII name associated with the job [IPDS-15-074]|
Triplets contained in the terminate group command (XOH-DGB order type = X'01') are
ignored. If present, they should match those in the associated begin group command.
• Keep group together for microfilm output [IPDS-15-075]
**MVS and VSE COM-data (Format X'04')** [IPDS-15-076]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'04'—MVS and VSE COM-data format identifier [IPDS-15-077]|
| 3 | CODE | Type | Type of print file:<br>X'80' Job header<br>X'40' Data set header<br>X'20' User data set<br>X'10' Message data set<br>X'04' Job trailer<br>X'00' Type not specified [IPDS-15-078]|
| 4 | CHAR | Class | The one-character EBCDIC job class [IPDS-15-079]|
| 5–12 | CHAR | Job Name | The eight-character EBCDIC job name [IPDS-15-080]|
| 13–20 | CHAR | Job ID | The eight-character EBCDIC job identification number [IPDS-15-081]|
| 21–28 | CHAR | Job Form | The eight-character EBCDIC job form [IPDS-15-082]|
| 29–88 | CHAR | Programmer | The 60-character EBCDIC programmer name [IPDS-15-083]|
| 89–148 | CHAR | Room Number | The 60-character EBCDIC room number [IPDS-15-084]|
| 149–159 | CHAR | Date | The 11-character EBCDIC submission date parameter [IPDS-15-085]|
| 160–170 | CHAR | Time | The 11-character EBCDIC submission time parameter [IPDS-15-086]|
Triplet X'00'—Group ID [IPDS-15-087]


**AIX and OS/2 COM-data (Format X'05')** [IPDS-15-088]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'05'—AIX and OS/2 COM-data format identifier [IPDS-15-089]|
| 3 | CODE | Type | Type of print file:<br>X'80' Job header<br>X'40' Copy separator<br>X'20' User print file<br>X'10' Message file<br>X'08' User exit page<br>X'04' Job trailer<br>X'00' Type not specified [IPDS-15-090]|
| 4–254 | CHAR | File Name | The 1–251 character ASCII file name [IPDS-15-091]|
• Save Pages [IPDS-15-092]

**Variable-length Group ID (Format X'08')** [IPDS-15-093]

| Byte | Type | Name | Meaning |
| :--- | :--- | :--- | :--- |
| 2 | CODE | Format | X'08'—Variable-length group ID [IPDS-15-094]|
| 3–246 | UNDF | Group ID | A 1 to 244 byte long group ID. Binary data unless preceded by X'01' triplet. [IPDS-15-095]|
Note: To successfully save a group of pages, the XOH-DGB command that begins the
group must contain a Group ID (X'00') triplet with a variable-length group ID.
Triplets contained in the terminate group command (XOH-DGB order type X'01') are
ignored.
IPDS commands that use this triplet:
Triplet X'00'—Group ID [IPDS-15-096]


Coded Graphic Character Set Global Identifier (X'01') Triplet
The Coded Graphic Character Set Global Identifier (CGCSGID) (X'01') triplet specifies the code page and
character set used to interpret character data.
When a CGCSGID (X'01') triplet is specified in an IPDS command, the triplet specifies the code page and
character set used to interpret character data within subsequent triplets. The CGCSGID (X'01') triplet stays in
effect until either it is replaced by another CGCSGID (X'01') triplet or the end of the containing command or
command entry is reached. The command description might contain additional considerations for the scope of
a CGCSGID (X'01') triplet within that command.
The character set is specified with a Graphic Character Set Global ID (GCSGID) and the code page is
specified with a Code Page Global ID (CPGID). Alternatively, these two values can be specified in a shorthand
form called the Coded Character Set Identifier (CCSID). These three types of identifiers are formally defined by
the Character Data Representation Architecture (CDRA) and are fully described in the Character Data
Representation Architecture Reference and Registry .
The two alternate forms of the triplet are as follows:
**GCSGID/CPGID form** [IPDS-15-097]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' [IPDS-15-098]|
| 1 | CODE | TID | X'01' | Identifies the CGCSGID triplet | X'01' [IPDS-15-099]|
| 2–3 | CODE | GCSGID | X'0001'–X'FFFE'<br>X'FFFF' | Graphic Character Set Global Identifier<br>Full character set | X'0001'–X'FFFE'<br>X'FFFF' [IPDS-15-100]|
| 4–5 | CODE | CPGID | X'0001'–X'FFFE' | Code Page Global Identifier | X'0001'–X'FFFE' [IPDS-15-101]|

**CCSID form**

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' [IPDS-15-102]|
| 1 | CODE | TID | X'01' | Identifies the CGCSGID triplet | X'01' [IPDS-15-103]|
| 2–3 | CODE | | X'0000' | Identifies this as the CCSID form of the triplet | X'0000' [IPDS-15-104]|
| 4–5 | CODE | CCSID | X'0000'–X'FFFF' | Coded Character Set Identifier | X'0000'–X'FFFF' [IPDS-15-105]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified, exception ID X'027A..01' exists. If the triplet is too long to fit in the containing
command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as a CGCSGID (X'01') triplet.
Bytes 2–3 GCSGID or CCSID-form indicator
For X'0000', this field specifies the CCSID form of the triplet and bytes 4–5 specify a CCSID.
For values between X'0001' and X'FFFE', this field specifies the Graphic Character Set Global
ID (GCSGID) of the character set to be used to interpret character data.
Triplet X'01'—CGCSGID [IPDS-15-106]


For X'FFFF', this field specifies that a character set consisting of all characters that have
assigned code points in the associated code page is to be used.
Bytes 4–5 CPGID or CCSID
If bytes 2–3 contain X'0000', bytes 4–5 contain a Coded Character Set Identifier (CCSID). The
meaning of this value can be found in the Character Data Representation Architecture
Reference and Registry.
If bytes 2–3 are not X'0000', bytes 4–5 contain the Code Page Global Identifier (CPGID) of the
code page to be used to interpret character data.
If an invalid value is specified in this field, exception ID X'0256..01' exists.
Note: This triplet is identical to the corresponding MO:DCA Coded Graphic Character Set Global Identifier
(X'01') triplet.
IPDS commands that use this triplet:
Triplet X'01'—CGCSGID [IPDS-15-107]


### Fully Qualified Name (X'02') Triplet

The Fully Qualified Name (X'02') triplet is used to specify a fully qualified name (FQN) and how that name is to be used. The FQN Type field specifies how the FQN is to be used and the FQN format field specifies how the FQN is encoded. [IPDS-15-108]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06'–X'FE' | Length of the triplet, including this length field | See byte description [IPDS-15-109]|
| 1 | CODE | TID | X'02' | Fully Qualified Name triplet | X'02' [IPDS-15-110]|
| 2 | CODE | FQN type | X'41'<br>X'DE' | Specifies how the FQN is used:<br>Color Management Resource reference<br>Data-object external resource reference | See byte description [IPDS-15-111]|
| 3 | CODE | FQN format | X'00'<br>X'10' | Format of the FQN:<br>Character-encoded name<br>Object ID (OID) | See byte description [IPDS-15-112]|
| 4 to end | | FQN | Any value | Fully qualified name | Any value [IPDS-15-113]|
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
When this triplet contains a UTF-16BE name, the length of the triplet must be even and in the
range X'06'–X'FE'. When this triplet contains an Object OID, the length of the triplet must be in
the range X'06'–X'81'.
Byte 1 Triplet ID
This parameter identifies this as a Fully Qualified Name (X'02') triplet.
Byte 2 FQN Type
This parameter specifies how the fully qualified name is to be used. The only types currently
used in the IPDS data stream are:
X'41' Color Management Resource reference
This type is used to provide the object OID for a CMR resource. For this use, the FQN
(X'02') triplet appears on the WOCC command when the CMR is downloaded.
X'DE' Data-object external resource reference
This type is used in the following ways:
• In the AR command, this type is used to reference a TrueType/OpenType font within [IPDS-15-114]
a TrueType/OpenType collection. For this use, the FQN (X'02') triplet appears on the
AR command that activates the collection. The FQN must be a character string that
contains a full font name that is encoded as UTF-16BE (a fixed, two-byte Unicode
encoding form that can contain surrogates and the byte order of each code point is
Big Endian). The FQN format must be X'00' (character-encoded name).
• In the AR command, this type is used to provide an optional object name for the [IPDS-15-115]
resource. The FQN format must be X'00' (character-encoded name). The character
encoding can be identified with a preceding X'01' triplet; however, if there is no
preceding X'01' triplet, the name must be a character string that is encoded as UTF-
16BE.
Triplet X'02'—Fully Qualified Name [IPDS-15-116]


Providing an object name when a printer returns property pair X'F211' in the Device-
Control command-set vector of an STM reply is recommended because the name is
useful to that printer. For example, the printer operator might benefit from seeing the
object name on the printer console whenever the OID of an object is displayed. The
(optional) object name may be supplied as a convenient alias, but is not intended as
a reliable reference when managing captured resources. Object names might not
be unique within a printer; for example, the name “Arial” might be used for a
TrueType font and also for a TIFF image that is a picture of a dog named Arial; it is
also possible that Arial has been used as the name for more than one font.
• In the WOCC-OCDD command, this type is used to specify the object OID of a [IPDS-15-117]
TrueType/OpenType font or collection. This OID is used when processing a PTOCA
Glyph Layout Control (GLC) control sequence. The FQN format must be X'10'
(object ID).
If an invalid FQN type value is specified, exception ID X'0256..21' exists.
Byte 3 FQN Format
This parameter specifies how the fully qualified name can be interpreted. The only formats
currently used in the IPDS data stream are:
X'00' Character-encoded name; used with FQN Type X'DE'
X'10' Object ID (OID); used with FQN Types X'41' and X'DE'
More information about OIDs can be found. When a CMR or TrueType/
OpenType object is being captured, the object OID specified in this triplet in the WOCC
command must match the object OID specified in the AR command that authorized the
capture. Exception ID X'0256..23' exists if the two OIDs are not identical.
If an invalid FQN format value is specified, exception ID X'0256..22' exists.
Bytes 4 to end Fully qualified name
This parameter specifies the name itself. If an invalid fully qualified name is specified,
exception ID X'0256..24' exists.
Note: This triplet is identical to the corresponding MO:DCA Fully Qualified Name (X'02') triplet. However, the
MO:DCA architecture defines additional FQN types and additional FQN formats.
IPDS commands that use this triplet:
Triplet X'02'—Fully Qualified Name [IPDS-15-118]


Color Specification (X'4E') Triplet
The Color Specification (X'4E') triplet is used to specify a color value by defining a color space and an
encoding for that value. When this triplet is specified for a logical page or object area, the area becomes
foreground data in the specified color before any object data is added to the area. The triplet can also be used
for bar code color, bilevel IO-Image color, and bilevel and grayscale image color in an object container. When
this triplet is specified in the DODD of an IDO command for a Standard OCA color value, the triplet overrides
the Set Bilevel Image Color IOCA self-defining field (or the Set Extended Bilevel Image Color IOCA self-
defining field) in an IO Image object. [IPDS-15-119]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0E'–X'10' | Length of the triplet, including this length field | X'0E'–X'10' [IPDS-15-120]|
| 1 | CODE | TID | X'4E' | Color Specification triplet | X'4E' [IPDS-15-121]|
| 2 | | X'00' | Reserved | | X'00' [IPDS-15-122]|
| 3 | CODE | Color space | X'01'<br>X'04'<br>X'06'<br>X'08'<br>X'40' | RGB color space<br>CMYK color space<br>Highlight color space<br>CIELAB color space<br>Standard OCA color space | X'01'<br>X'04'<br>X'06'<br>X'08'<br>X'40' [IPDS-15-123]|
| 4–7 | | X'00000000' | Reserved | | X'00000000' [IPDS-15-124]|
| 8 | UBIN | ColSize1 | X'01'–X'08',<br>X'10' | Number of bits in component 1; the range depends on the color space | See color-space description [IPDS-15-125]|
| 9 | UBIN | ColSize2 | X'00'–X'08' | Number of bits in component 2; the range depends on the color space | See color-space description [IPDS-15-126]|
| 10 | UBIN | ColSize3 | X'00'–X'08' | Number of bits in component 3; the range depends on the color space | See color-space description [IPDS-15-127]|
| 11 | UBIN | ColSize4 | X'00'–X'08' | Number of bits in component 4; the range depends on the color space | See color-space description [IPDS-15-128]|
| 12 to end | | Color value | All values | Color specification | See byte description [IPDS-15-129]|
Byte 0 Triplet length
This byte contains the length of this triplet, including the length field itself. The triplet length
value depends on the specified color space. If an invalid length is specified or if the triplet is
too long to fit in the containing command, exception ID X'020E..01' exists.
Byte 1 Triplet ID
This byte indicates the type of triplet; in this case Color Specification (X'4E') triplet.
Byte 2 Reserved
Triplet X'4E'—Color Specification [IPDS-15-130]


Byte 3 Color space
This field defines the color space and the encoding for the color value specification. If an
invalid or unsupported color space is specified, exception ID X'020E..02' exists.
X'01' RGB color space
Each color value is treated as a set of red, green, and blue intensity values, in that
order. ColSize1, ColSize2, and ColSize3 define the number of bits used to specify
each intensity; the required range is X'01'–X'08'. ColSize4 is ignored. Each
component is specified as an unsigned binary number (data type UBIN).
The intensity range for the R, G, and B components is 0 to 1, that is mapped to the
binary value range 0 to (2
ColSizeN - 1), where N=1,2,3.
Note: The reference white point and the chromaticity coordinates for the RGB color
space are defined in SMPTE RP 145-1987 entitled Color Monitor Colorimetry
and RP 37-1969 entitled Color Temperature for Color Television Studio
Monitors, respectively. The reference white point is commonly known as
Illuminant D
6500 or simply D65: [IPDS-15-131]
Red chromaticity x=0.630, y=0.340
Green chromaticity x=0.310, y=0.595
Blue chromaticity x=0.155, y=0.070
White point x=0.313, y=0.329
The R,G,B components are assumed to be gamma-corrected (non-linear) with
a gamma of 2.2.
X'04' CMYK color space
Each color value is treated as a set of cyan, magenta, yellow, and black intensity
values, in that order. ColSize1, ColSize2, ColSize3, and ColSize4 define the number
of bits used to specify each intensity; the required range is X'01'–X'08'. Each
component is specified as an unsigned binary number (data type UBIN).
The intensity range for the C, M, Y , and K components is 0 to 1, that is mapped to the
binary value range 0 to (2
ColSizeN - 1), where N=1,2,3,4.
This is a device-dependent color space.
Triplet X'4E'—Color Specification [IPDS-15-132]


X'06' Highlight color space
This color space defines a request for the printer to generate a device-dependent
highlight color. The actual color depends on what is loaded in the printer at print time.
The color value is specified with one to three components.
Component 1 Highlight color number
The highlight color number is specified with component 1 as a two-
byte, unsigned binary number and ColSize1 = X'10'. The first
highlight color is assigned X'0001', the second highlight color is
assigned X'0002', and so forth. The value X'0000' specifies the
printer default color (usually black).
This is a device dependent color space. The color that is rendered
when the highlight color space is specified is completely printer
dependent. If an unsupported highlight color number is specified, the
printer will select a device-specific color: either a supported highlight
color, a graphic pattern, or color of medium. For example, if X'0002' is
sent to a single-highlight-color printer, the supported highlight color
might be used. For printers that support colors other than black, the
color can be any color. For single-color printers, the color may be
simulated with a graphic pattern.
Component 2 Percent coverage (optional)
Percent coverage is specified with component 2 as a one-byte,
unsigned binary number and ColSize2 = X'08'. Percent coverage can
be any value between 0% and 100% inclusive (X'00'–X'64'). The
number of distinct percent coverage values supported is printer
specific. This component can be omitted by setting ColSize2 = X'00';
in this case, percent coverage defaults to 100%.
This component specifies the percent coverage with the specified
highlight color and is used in conjunction with component 1 (color
number) and component 3 (shading). If the sum of the coverage
value and the shading value is less than 100%, the remaining
coverage is achieved with color of medium.
The color of medium is normally white, in which case a coverage of n
% with a shading of m% results in adding (100-n-m)% white to the
specified color; this is called tinting.
If an invalid percent value is specified, exception ID X'020E..04'
exists.
Component 3 Percent shading (optional)
Percent shading is specified with component 3 as a one-byte,
unsigned binary number and ColSize3 = X'08'. Shading can be any
value between 0% and 100% inclusive (X'00'–X'64'). The number of
distinct percent shading values supported is printer specific. This
component can be omitted by setting ColSize3 = X'00'; in this case,
percent shading defaults to 0%.
This component specifies a percentage of black that is to be added to
the specified color. The effective range of shading is 0% to (100-
coverage)%; if a larger value is specified, the printer will produce
maximum available shading by using 100-coverage.
If an invalid percent value is specified, exception ID X'020E..04'
exists.
Triplet X'4E'—Color Specification [IPDS-15-133]


Implementation Note: The percent shading parameter is currently
not supported in AFP environments.
Component 4 Not used
ColSize4 is not applicable and is ignored.
Note: The highlight color space can also specify indexed colors when used in
conjunction with a Color Mapping Table (CMT) or an indexed Color
Management Resource (CMR). When used with an indexed CMR, component
1 specifies a two-byte value that is an index into the CMR and components 2 [IPDS-15-134]
and 3 are ignored. Note that when both a CMT and an indexed CMR are used,
the CMT is always accessed first. To preserve compatibility with existing
highlight color devices, indexed color values X'0000'–X'00FF' are reserved for
existing highlight color applications and devices. That is, indexed color values
in the range X'0000'–X'00FF', assuming they are not mapped to a different
color space in a CMT , are mapped directly to highlight colors. Indexed color
values in the range X'0100'–X'FFFF' are used to access a host-invoked
indexed CMR; exception ID X'020E..03' exists if a host-invoked indexed CMR
is not found.
X'08' CIELAB color space
Each color value is treated as a set of L, a, and b values, in that order; where a and b
are the chrominance differences and L is the luminance. ColSize1, ColSize2, and
ColSize3 define the number of bits used to specify each component; the required
range is X'01'–X'08'. ColSize4 is ignored. The L component is specified as an
unsigned binary number (data type UBIN). The a and b components are specified as
signed binary numbers (data type SBIN).
The range for the L component is 0 to 100, that is mapped to the binary value range 0
to (2
ColSize1 - 1). The range for the a and b components is -127 to +127, that is mapped
to the binary range -(2 ColSizeN-1-1) to +(2ColSizeN-1-1), where N=2,3.
For color fidelity, 8-bit encoding should be used for each component, that is, ColSize1,
ColSize2, and ColSize3 are set to X'08'. When the recommended 8-bit encoding is
used for the a and b components, the range is extended to include -128, that is
mapped to the value X'80'. If the encoding is less than 8 bits, treatment of the most
negative binary endpoint for the a and b components is device-dependent, and tends
to be insignificant due to the quantization error.
Note: The reference white point for CIELAB is known as D50 and is defined in CIE
publication 15-2 entitled Colorimetry.
Triplet X'4E'—Color Specification [IPDS-15-135]


X'40' Standard OCA color space
The color is specified with component 1 using a two-byte value from the Standard
OCA Color-Value table:
Table 58. Standard OCA Color-Value Table [IPDS-15-136]

| Value | Color | Red (R) | Green (G) | Blue (B) |
| :--- | :--- | :--- | :--- | :--- |
| X'0000' or X'FF00' | Current default (printer default) [IPDS-15-137]| | | |
| X'0001' or X'FF01' | Blue | 0 | 0 | 255 [IPDS-15-138]|
| X'0002' or X'FF02' | Red | 255 | 0 | 0 [IPDS-15-139]|
| X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 [IPDS-15-140]|
| X'0004' or X'FF04' | Green | 0 | 255 | 0 [IPDS-15-141]|
| X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 [IPDS-15-142]|
| X'0006' or X'FF06' | Yellow | 255 | 255 | 0 [IPDS-15-143]|
| X'0007' | White; see note 2 | 255 | 255 | 255 [IPDS-15-144]|
| X'0008' | Black | 0 | 0 | 0 [IPDS-15-145]|
| X'0009' | Dark blue | 0 | 0 | 170 [IPDS-15-146]|
| X'000A' | Orange | 255 | 128 | 0 [IPDS-15-147]|
| X'000B' | Purple | 170 | 0 | 170 [IPDS-15-148]|
| X'000C' | Dark green | 0 | 146 | 0 [IPDS-15-149]|
| X'000D' | Dark turquoise | 0 | 146 | 170 [IPDS-15-150]|
| X'000E' | Mustard | 196 | 160 | 32 [IPDS-15-151]|
| X'000F' | Gray | 131 | 131 | 131 [IPDS-15-152]|
| X'0010' | Brown | 144 | 48 | 0 [IPDS-15-153]|
| X'FF07' | Printer default [IPDS-15-154]| | | |
| X'FF08' | Color of medium; also known as reset color [IPDS-15-155]| | | |
Notes:
1. The table specifies the RGB values for each named color; the actual printed color is device dependent. [IPDS-15-156]
2. The color rendered on presentation devices that do not support white is device-dependent. For example, some [IPDS-15-157]
printers simulate with color of medium that results in white when white media is used.
ColSize1 = X'10'; ColSize2, ColSize3, and ColSize4 are not applicable and are
ignored.
Bytes 4–7 Reserved
Byte 8 ColSize1
This field specifies the number of bits used to specify the first color component. The color
component is right-aligned and padded with binary zeros on the left to the nearest byte
boundary. For example, if ColSize1 = X'06', the first color component would have 2 padding
bits.
If an invalid or unsupported Colsize1 value is specified, exception ID X'020E..05' exists.
Triplet X'4E'—Color Specification [IPDS-15-158]


Byte 9 ColSize2
This field specifies the number of bits used to specify the second color component. The color
component is right-aligned and padded with binary zeros on the left to the nearest byte
boundary.
If an invalid or unsupported Colsize2 value is specified, exception ID X'020E..05' exists.
Byte 10 ColSize3
This field specifies the number of bits used to specify the third color component. The color
component is right-aligned and padded with binary zeros on the left to the nearest byte
boundary.
If an invalid or unsupported Colsize3 value is specified, exception ID X'020E..05' exists.
Byte 11 ColSize4
This field specifies the number of bits used to specify the fourth color component. The color
component is right-aligned and padded with binary zeros on the left to the nearest byte
boundary.
If an invalid or unsupported Colsize4 value is specified, exception ID X'020E..05' exists.
Bytes 12 to
end
Color value
This field specifies the color value in the defined color space and encoding. If an invalid or
unsupported color value is specified, exception ID X'020E..03' exists. Unless overridden by a
Color Fidelity (X'75') triplet in a PFC command, the exception is not reported and printers will
simulate an unsupported color value that is specified with a supported color value.
To illustrate the syntax for the Color Specification (X'4E') triplet, the following table shows examples of various
ways that a light-green color can be specified. Note that the light-green color value is approximated in each of
the color spaces.
Table 59. Color Space Examples [IPDS-15-159]

| Color Space | ColSize1 | ColSize2 | ColSize3 | ColSize4 | Color Value |
| :--- | :--- | :--- | :--- | :--- | :--- |
| RGB | X'08' | X'08' | X'08' | N/A | X'00B761' [IPDS-15-160]|
| RGB | X'05' | X'05' | X'05' | N/A | X'00160B' [IPDS-15-161]|
| CMYK | X'08' | X'08' | X'08' | X'08' | X'FF489E00' [IPDS-15-162]|
| CMYK | X'01' | X'02' | X'04' | X'08' | X'01010900' [IPDS-15-163]|
| Highlight (see note) | X'10' | X'08' | X'00' | N/A | X'000264' [IPDS-15-164]|
| CIELAB | X'08' | X'08' | X'08' | N/A | X'A8CC21' [IPDS-15-165]|
| Standard OCA | X'10' | N/A | N/A | N/A | X'0004' [IPDS-15-166]|
Note: This example assumes that the light-green color is loaded in the printer as highlight color X'0002'.
Notes:
1. If extra bytes are specified in the color value field, they are ignored as long as the triplet length is valid. [IPDS-15-167]
2. This triplet is identical to the corresponding MO:DCA Color Specification (X'4E') triplet. [IPDS-15-168]
Triplet X'4E'—Color Specification [IPDS-15-169]


IPDS commands that use this triplet:
Triplet X'4E'—Color Specification [IPDS-15-170]


Encoding Scheme ID (X'50') Triplet
The Encoding Scheme ID (X'50') triplet is used to specify the encoding scheme used for character data to be
printed. Property pair X'F204' in the Device-Control command-set vector of an STM reply indicates support for
the Encoding Scheme ID (X'50') triplet. [IPDS-15-171]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' [IPDS-15-172]|
| 1 | CODE | TID | X'50' | Encoding Scheme ID triplet | X'50' [IPDS-15-173]|
| 2–3 | | X'0000' | Reserved | | X'0000' [IPDS-15-174]|
| 4–5 | CODE | Data ESID | X'7807' | Encoding scheme ID for the data:<br>UTF-8 | X'7807' [IPDS-15-175]|
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as an Encoding Scheme ID (X'50') triplet.
Bytes 2–3 Reserved
Bytes 4–5 Encoding scheme ID for the data to be printed
This field uses the encoding scheme syntax defined by the Character Data Representation
Architecture (CDRA) to specify the encoding scheme used to encode the data to be printed.
Only one value is supported in this triplet:
X'7807' UTF-8
This encoding ID is used when the character data is encoded using the UTF-8
Unicode Transformation Format, as defined in the Unicode 3.2 Specification.
UTF-8 code points are variable length (from 1 to 4 bytes long); the high order
bits of each byte identify whether or not additional bytes follow. This format is
useful for 7-bit ASCII data (code points X'00'–X'7F') because, in the UTF-8
encoding, these characters are all encoded as one byte.
The printer converts UTF-8 data into Unicode code points and then uses the
Unicode code points to map to appropriate values in an internal table within a
font.
Implementation Note: The Unicode 3.2 Specification (or a later version)
should be used when implementing this conversion because the
description has changed from the Unicode 3.0 Specification. To
understand this change, you will need to read the UTF-8 description in
all three Specifications (3.0, 3.1, and 3.2); all Unicode Specifications
can be found at www.unicode.org.
If an invalid or unsupported encoding scheme ID value is specified, exception
ID X'0256..31' exists.
Triplet X'50'—Encoding Scheme ID [IPDS-15-176]


Note: This triplet is identical to the corresponding MO:DCA Encoding Scheme ID (X'50') triplet. However, the
MO:DCA architecture also uses bytes 2–3 to specify the encoding scheme ID used within the font; the
content of bytes 2–3 are ignored at the IPDS level.
IPDS commands that use this triplet:
Triplet X'50'—Encoding Scheme ID [IPDS-15-177]


Object Offset (X'5A') Triplet
The Object Offset (X'5A') triplet selects a paginated object within a multi-page resource object (such as a multi-
page PDF file or multi-image TIFF file).
Printer support for the Object Offset (X'5A') triplet is indicated by the presence of at least one multi-page-file or
multi-image-file resource object OID in the Object-Container Type Support self-defining field of an XOH-OPC
reply.
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'08' or X'0C' | Length of the triplet, including this length field | X'08' or X'0C' [IPDS-15-178]|
| 1 | CODE | TID | X'5A' | Object Offset triplet | X'5A' [IPDS-15-179]|
| 2 | CODE | Object type | X'AF' | Object type:<br>Page or paginated object | X'AF' [IPDS-15-180]|
| 3 | | X'00' | Reserved | | X'00' [IPDS-15-181]|
| 4–7 | UBIN | Object offset | X'0000..00' –<br>X'FFFF ..FF' | Number of objects that precede the selected object in the file | X'0000..00' –<br>X'FFFF ..FF' [IPDS-15-182]|
| 8–11 | | X'0000..00' | Reserved; not used in IPDS | | X'0000..00' [IPDS-15-183]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as an Object Offset (X'5A') triplet.
Byte 2 Object type
This field specifies the type of object to be selected. The only value used within IPDS is X'AF'
that indicates that this triplet is selecting a page or paginated object. The MO:DCA
architecture defines additional values that are not used within the IPDS architecture.
Note: A paginated object is a data object that can be rendered on a single page or overlay. An
example of a paginated object is a single image in a multi-image TIFF file. Note that in
TIFF files, image-like structures such as thumbnails and image masks are considered
to be a part of the paginated image object but are not themselves considered paginated
objects.
If an invalid value is specified, exception ID X'0256..A1' exists.
Byte 3 Reserved
Bytes 4–7 Object offset
This parameter specifies the offset of the selected paginated object within the file. The offset is
measured from the beginning of the file, so that the first paginated object has offset 0, the
second has offset 1, and the nth has offset (n-1).
Note: The ordering of paginated image objects in a TIFF file can be defined explicitly with
page numbers, or implicitly based on the position of the image object in the file. The
page offset specified by this triplet can be applied to either ordering, but the explicit
page numbering, if specified, always has higher priority.
If there is no paginated object in the file at the specified offset, exception ID X'0256..A2' exists.
Triplet X'5A'—Object Offset [IPDS-15-184]


Bytes 8–11 Retired item 140. Reason for IPDS retirement: intended for a high-order extension to the
object offset (but not used).
This parameter is not used within IPDS data streams, but is defined in the MO:DCA
architecture to provide a means to allow a larger object offset value to be specified. IPDS
printers ignore the value in this field.
Note: This triplet is identical to the corresponding MO:DCA Object Offset (X'5A') triplet.
IPDS commands that use this triplet:
Triplet X'5A'—Object Offset [IPDS-15-185]


Local Date and Time Stamp (X'62') Triplet
The last supported Local Date and Time Stamp (X'62') triplet encountered is used to find the resource to be
activated; all other Local Date and Time Stamp (X'62') triplets are ignored. Property pair X'F200' in the Device-
Control command-set vector of an STM reply indicates support for the Local Date and Time Stamp (X'62')
triplet.
The time stamp specified by the Local Date and Time Stamp (X'62') triplet is unique only with respect to time
stamps specified in the same locality or time zone, and its relationship with Coordinated Universal Time is
unknown.
When a resident resource activation is attempted, the printer first attempts to match the fixed portion of the
resource ID to a resident resource. If a match is found, the Local Date and Time Stamp (X'62') triplet is then
used as follows:
• If the AR command has an Local Date and Time Stamp (X'62') triplet, activation takes place only if the [IPDS-15-186]
resident resource has a matching Local Date and Time Stamp (X'62') triplet.
• If the AR command does not have an Local Date and Time Stamp (X'62') triplet, activation takes place. [IPDS-15-187]
Note: If a date and time stamp is not supplied in the AR entry, the printer will not activate a captured LF1, LF3,
or LF4 font resource. In this case, only resources that were shipped with or installed directly in the
printer are candidates for activation.
The Local Date and Time Stamp (X'62') triplet can be used with the following resource types:
X'06'—Code page
X'07'—Font character set
If specified with any other resource type, the Local Date and Time Stamp (X'62') triplet is ignored. The contents
of the triplet are not checked by the printer for validity.
Triplet X'62'—Time Stamp [IPDS-15-188]


The Local Date and Time Stamp (X'62') triplet is defined as follows: [IPDS-15-189]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'11' | Length of the triplet, including this length field | X'11' [IPDS-15-190]|
| 1 | CODE | TID | X'62' | Identifies the Local Date and Time Stamp Triplet | X'62' [IPDS-15-191]|
| 2 | CODE | StampType | X'00'<br>X'03' | Type of date and time stamp:<br>Creation<br>Revision | X'00'<br>X'03' [IPDS-15-192]|
| 3 | CODE | Year (part 1) | X'40',<br>X'F0'–X'F9' | Thousands and hundreds position of year:<br>19xx<br>20xx through 29xx | X'40',<br>X'F0'–X'F9' [IPDS-15-193]|
| 4–5 | CODE | Year (part 2) | X'F0F0' –<br>X'F9F9' | Tens and units position of the year | X'F0F0' –<br>X'F9F9' [IPDS-15-194]|
| 6–8 | CODE | Day | X'F0F0F1' –<br>X'F3F6F6' | Day of year | X'F0F0F1' –<br>X'F3F6F6' [IPDS-15-195]|
| 9–10 | CODE | Hour | X'F0F0' –<br>X'F2F3' | Hour of day | X'F0F0' –<br>X'F2F3' [IPDS-15-196]|
| 11–12 | CODE | Minute | X'F0F0' –<br>X'F5F9' | Minute of hour | X'F0F0' –<br>X'F5F9' [IPDS-15-197]|
| 13–14 | CODE | Second | X'F0F0' –<br>X'F5F9' | Second of minute | X'F0F0' –<br>X'F5F9' [IPDS-15-198]|
| 15–16 | CODE | Hundredth | X'F0F0' –<br>X'F9F9' | Hundredth of second | X'F0F0' –<br>X'F9F9' [IPDS-15-199]|
Note: The range values for bytes 3–16 are actually a character representation, so the digits progress from
X'F0' to X'F1' to X'F2' to X'F3' to X'F4' to X'F5' to X'F6' to X'F7' to X'F8' to X'F9'.
Byte 0 Triplet length
This field contains the length of this triplet, including itself. If an invalid length is specified or if
the triplet is too long to fit in the containing command, exception ID X'028F ..03' exists.
Byte 1 Triplet ID
This field identifies this as an Local Date and Time Stamp (X'62') triplet.
Byte 2 StampType
This field specifies the type of date and time stamp:
X'00'—Object creation
X'01' is retired as Retired item 129.
X'03'—Object revision
A X'62' triplet with any other Stamp Type is ignored.
Byte 3 Thousands and hundreds position of the year
This field identifies the first two digits of the year AD, using the Gregorian calendar. The 1900s
are encoded as X'40', the 2000s are encoded as X'F0', the 2100s as X'F1', the 2200s are
encoded as X'F2', and so on.
Triplet X'62'—Time Stamp [IPDS-15-200]


Bytes 4–5 Tens and units position of the year
This field specifies the last two digits of the year AD, using the Gregorian calendar.
Bytes 6–8 Day
This field specifies the day of the year, using the Gregorian calendar.
Table 60. Examples of the Date Fields [IPDS-15-201]

| Date | Restructured as | Encoded as |
| :--- | :--- | :--- |
| February 1, 1972 | 72032 | X'40F7F2F0F3F2' [IPDS-15-202]|
| December 31, 1999 | 99365 | X'40F9F9F3F6F5' [IPDS-15-203]|
| January 1, 2000 | 000001 | X'F0F0F0F0F0F1' [IPDS-15-204]|
| February 3, 2072 | 072034 | X'F0F7F2F0F3F4' [IPDS-15-205]|
Bytes 9–10 Hour
This field specifies the hour of the day and forms the HH component of a time stamp in the
format HHMMSShh.
Bytes 11–12 Minute
This field specifies the minute of the hour and forms the MM component of a time stamp in the
format HHMMSShh.
Bytes 13–14 Second
This field specifies the second of the minute and forms the SS component of a time stamp in
the format HHMMSShh.
Bytes 15–16 Hundredth
This field specifies hundredth of a second and forms the hh component of a time stamp in the
format HHMMSShh.
As an example, the time 4:35:21.56 PM is encoded as X'F1F6F3F5F2F1F5F6'.
Note: This triplet is identical to the corresponding MO:DCA Local Date and Time Stamp (X'62') triplet.
IPDS commands that use this triplet:
Triplet X'62'—Time Stamp [IPDS-15-206]


Group Information (X'6E') Triplet
The Group Information (X'6E') triplet is used to provide information about a group of pages; there are a variety
of formats defined for specific group operations. With the exception of the microfilm save/restore format, the
content of the triplet is informational and there are no architected semantics for what a receiver does with the
information. The microfilm save/restore format indicates how microfilm information should be handled by a
microfilm device. Unrecognized formats should be ignored. [IPDS-15-207]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'02'–X'FF' | Length of the triplet, including this length field | X'02'–X'FF' [IPDS-15-208]|
| 1 | CODE | TID | X'6E' | Group Information triplet | X'6E' [IPDS-15-209]|
| 2 | CODE | Format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'82' | Microfilm save/restore format<br>Copy set number format<br>Group name format<br>Additional information format<br>Page count format<br>Extended copy set number format | See byte description [IPDS-15-210]|
| 3 to end of triplet | | Data | X'00'–X'FF' | Group Information Data Bytes | X'00'–X'FF' [IPDS-15-211]|
Byte 0 Triplet length
This byte contains the length of this triplet, including itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists.
If a group triplet is too big to fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This byte indicates the type of field being defined. The only valid value is X'6E' and defines the
following triplet field type:
• X'6E'—Group Information [IPDS-15-212]
The Group Information (X'6E') triplet, if present, contained in the terminate group command
(XOH-DGB order type = X'01') does not necessarily match that in the associated begin
group command.
Byte 2 Format
This byte identifies the format of the data portion of this triplet.
When the printer supports the identify-named-group group operation, as indicated by the
Supported Group Operations self-defining field in an XOH-OPC reply, the group name format
(X'03') must be supported in this triplet. When the printer supports the keep-group-together-
for-microfilm-output group operation, as indicated by the Supported Group Operations self-
defining field in an XOH-OPC reply, the microfilm save/restore format (X'01') must be
supported in this triplet.
Triplet X'6E'—Group Information [IPDS-15-213]


Bytes 3 to end of triplet
Data bytes
These bytes, if present, contain parameter data in one of several possible formats; the format
is identified in triplet byte 2. Valid triplet data-byte formats for the defined XOH-SGO
operations are as follows:
• Keep group together as a print unit [IPDS-15-214]
For the copy set number format:
Byte 2 X'02', copy set number format identifier
Bytes 3–4 This value identifies the number of this copy set in a sequence of copies of
this group. This information is useful when multiple copies of a group are to
be printed.
X'0000' Copy set number not provided
X'0001' First copy of a set of copies
X'0002'–
X'FFFE'
Subsequent copy of a set of copies
X'FFFF' Indicates a copy set number larger than 65,534
For the extended copy set number format:
Printer support for the extended format is indicated by property pair X'F209' in the Device-
Control command-set vector of an STM reply. When a copy set number is to be provided
within this triplet, the extended format (format X'82') should be used when the printer
supports this format, otherwise format X'02' should be used.
Byte 2 X'82', extended copy set number format identifier
Bytes 3–6 This value identifies the number of this copy set in a sequence of copies of
this group. This information is useful when multiple copies of a group are to
be printed.
X'00000000' Copy set number not provided
X'00000001' First copy of a set of copies
X'00000002'–
X'FFFFFFFE'
Subsequent copy of a set of copies
X'FFFFFFFF' Indicates a copy set number larger than 4,294,967,294
Bytes 7–10 This value identifies the total number of copies of this group.
X'00000000' Total number of copies not provided
X'00000001'–
X'FFFFFFFE'
Total number of copies of this group.
X'FFFFFFFF' Indicates a number larger than 4,294,967,294
Note: The printer does not verify that the copy set number and total number
of copies values are consistent. The Presentation Services Program
should consistently provide the total number of copies (or specify
X'0...0') for each copy and also insure that the copy set number is not
larger than the total number of copies. The printer should not assume
that the copies are sent in any particular order.
For the page count format:
Byte 2 X'05', page count format identifier
Bytes 3–10 Number of pages in this group; copies of pages are not counted here. This
field contains an 8-byte unsigned binary value.
Implementation note: The page count is informational only. Typically, the page count is a
count of the number of pages in a print file (or other group) that is specified before the group
is printed; for example, the value might represent the number of BPG/EPG-pairs within a
MO:DCA print file. It is intended to be a ball-park figure that can help operators estimate time
Triplet X'6E'—Group Information [IPDS-15-215]


to completion or job progress; there is no requirement that the page count be completely
accurate in all circumstances. Caveats:
– The page count value is not necessarily a count of IPDS pages, but it is probably very
close to the actual IPDS pages that are printed for an error-free job. The page count is
typically a count of MO:DCA pages.
– The page count value is not a sheet count, it is not an impression count, it is not a copy
count, it does not necessarily include all print-server-generated pages (such as error
message pages), it does not necessarily include constant fronts or constant backs (that is,
blank sides with no MO:DCA pages), it does not include printer-generated sheets or sides
(such as NPRO, blank sides when a finishing operation ends in the middle of a sheet,
etc.). When looking at printed output, it is not necessarily easy to identify the IPDS pages
that the printer processed (for example, a blank back side might or might not be an IPDS
page); therefore, a user view of pages is not necessarily the same as the printer view of
pages.
– For better accuracy, the print server could adjust the count to include header and trailer
pages before sending the page count value to the printer. Printer implementations should
not assume that the host software has included non-user pages; the printer
implementation should also not assume that the host software has not included such
pages.
– The page count value assumes an error free job; reprints or skipped pages due to
operator repositions and paper jam recovery are not included in the page count.
– Both the print server and the printer can choose whether or not to provide or use a page
count for nested groups.
• Keep group together for microfilm output [IPDS-15-216]
Byte 2 A one-byte field indicating the format of the remaining bytes. The only valid
value is X'01'. If any other value is specified, the triplet is skipped and an
exception is not reported.
Byte 3 This byte indicates how the microfilm information should be handled by the
microfilm device:
X'80' Save microfilm information associated with the group identified by
the Group ID (X'00') triplet in this command. This setting is only
meaningful on a terminate group command (XOH-DGB order type
= X'01').
X'40' Restore microfilm information associated with the group identified
by the Group ID (X'00') triplet in this command. This setting is only
meaningful on a begin group command (XOH-DGB order type =
X'00').
Triplet X'6E'—Group Information [IPDS-15-217]


• Identify Named Group and [IPDS-15-218]
Keep Group Together as a Recovery Unit
For the group name format:
Byte 2 X'03', group name format; should have one group-name-format triplet
Bytes 3 to end A 1–250 byte long group name. The name is considered to be binary data,
unless there was a preceding CGCSGID (X'01') triplet in the XOH-DGB
command.
For the additional information format:
Byte 2 X'04', additional information format; can have multiple additional-
information-format triplets
Bytes 3 to end Additional information associated with this group. The information is
considered to be binary data, unless there was a preceding CGCSGID
(X'01') triplet in the XOH-DGB command.
IPDS commands that use this triplet:
Triplet X'6E'—Group Information [IPDS-15-219]


Presentation Space Reset Mixing (X'70') Triplet
The Presentation Space Reset Mixing (X'70') triplet is used to specify whether or not a presentation space is
reset to the color of the medium prior to placing object data into the presentation space. Property pair X'6201'
in the Device-Control command-set vector of an STM reply indicates support for the Presentation Space Reset
Mixing (X'70') triplet. [IPDS-15-220]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'03' | Length of the triplet, including this length field | X'03' [IPDS-15-221]|
| 1 | CODE | TID | X'70' | Presentation Space Reset Mixing triplet | X'70' [IPDS-15-222]|
| 2 | BITS | Mixing flags [IPDS-15-223]| | | |
| | bit 0 | Reset flag | B'0'<br>B'1' | Do not reset the color of the presentation space to color of medium. This value makes this triplet an effective NOP.<br>Reset the color of the presentation space to color of medium prior to placing object data into the presentation space. | B'0'<br>B'1' [IPDS-15-224]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' [IPDS-15-225]|
Byte 0 Triplet length
This byte contains the length of this triplet, including the length field itself. If an invalid length is
specified or if the triplet is too long to fit in the containing command, exception ID X'020E..01'
exists.
Byte 1 Triplet ID
This byte indicates the type of triplet; in this case Presentation Space Reset Mixing (X'70')
triplet.
Byte 2 Mixing flags
Bit 0 Reset flag
B'0' Do not reset the color of the presentation space to color of medium.
This value makes this triplet an effective NOP .
B'1' Reset the color of the presentation space to color of medium prior to
placing object data into the presentation space. This effectively
erases all data beneath this presentation space.
Bits 1–7 Reserved
Note: This triplet is identical to the corresponding MO:DCA Presentation Space Reset Mixing (X'70') triplet.
IPDS commands that use this triplet:
Triplet X'70'—Reset Mixing [IPDS-15-226]


Toner Saver (X'74') Triplet
The Toner Saver (X'74') triplet is used to activate a toner saver mode for color printing. If the printer has not
received a Toner Saver (X'74') triplet, or if the activate flag in a PFC command is B'0', or if the printer issues an
IML NACK, the default is to use the printer default setting (X'FF'). Support for the Toner Saver (X'74') triplet is
indicated by the PFC Triplets Supported self-defining field returned in the XOH-OPC reply.
Some IPDS printers use the XOA-PQC command to control toner saving; if a printer supports both XOA-PQC
command and the Toner Saver (X'74') triplet, and if the printer receives both, the Toner Saver (X'74') triplet is
used and the XOA-PQC command is ignored for toner saving purposes. [IPDS-15-227]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' [IPDS-15-228]|
| 1 | CODE | TID | X'74' | Identifies the Toner Saver triplet | X'74' [IPDS-15-229]|
| 2 | | X'00' | Reserved | | X'00' [IPDS-15-230]|
| 3 | CODE | Control | X'00'<br>X'01'<br>X'FF' | Toner saver control:<br>Deactivate toner saver<br>Activate toner saver<br>Use printer default setting | X'00'<br>X'01'<br>X'FF' [IPDS-15-231]|
| 4–5 | | X'0000' | Reserved | | X'0000' [IPDS-15-232]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length
value is specified or if the triplet is too long to fit in the PFC command, exception ID
X'0254..31' exists.
Byte 1 Triplet ID
This field identifies this as a Toner Saver (X'74') triplet.
Byte 2 Reserved
Byte 3 Toner saver control
This control directs the printer to either deactivate or activate the toner saver function. The
valid values are:
X'00' Deactivate the toner saver function.
X'01' Activate the toner saver function. A toner saver algorithm is applied to color data in a
device-dependent manner. In general, this might degrade color quality, and might also
impact performance.
X'FF' Use the printer default toner-saver setting
Some printers allow a default for toner-saving (activate or deactivate) to be set by the
operator at the printer console.
If an invalid control value is specified, exception ID X'0254..33' exists.
Bytes 4–5 Reserved
Notes:
1. The toner saver setting (activated or deactivated) that is in effect when data is printed controls whether or [IPDS-15-233]
not the toner saving algorithm is applied to that data.
Triplet X'74'—Toner Saver [IPDS-15-234]


2. The toner saver function is not applied to IO Image tiles (IOCA FS4x) that specify CMYK colors. Other tiles [IPDS-15-235]
within a tiled image that don't specify CMYK colors have toner saving applied when toner saver is
activated.
3. For resources, toner saver is applied based on the setting that is active at include (presentation) time, not [IPDS-15-236]
at resource activation time. This includes the following resources:
• Data objects: [IPDS-15-237]
– EPS (Encapsulated PostScript) with transparency
– EPS without transparency
– GIF (Graphics Interchange Format)
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
• IO Images [IPDS-15-238]
• Overlays [IPDS-15-239]
• Page segments [IPDS-15-240]
• Saved page groups (see also note 4) [IPDS-15-241]
4. With saved pages, some printers apply toner saver when the pages are saved. In that case, if the toner [IPDS-15-242]
saver attribute at save time is different than the toner saver attribute at include time, exception ID
X'0254..32' exists.
5. No toner saving is applied when a data object resource is captured. [IPDS-15-243]
Note: This triplet is identical to the corresponding MO:DCA Toner Saver (X'74') triplet.
IPDS commands that use this triplet:
Triplet X'74'—Toner Saver [IPDS-15-244]


Color Fidelity (X'75') Triplet
The Color Fidelity (X'75') triplet is used to specify the exception continuation and reporting rules for color
exceptions. This triplet also specifies a color substitution rule to be used when continuing after a color
exception. Support for the Color Fidelity (X'75') triplet is indicated by the PFC Triplets Supported self-defining
field returned in the XOH-OPC reply.
The following lists the applicable color exception IDs:
X'05B7..10'
X'05F4..10'
X'0405..00'
X'0300..04' (For color-value fields in the Set Color, Set Extended Color, and Set Process Color drawing
orders only)
X'0300..0E' (For color-value fields in the Set Color, Set Extended Color, and Set Process Color drawing
orders only)
X'0300..21' (For color-value fields only)
X'020E..03'
X'020E..04'
X'0253..01'
X'0256..81'
X'0258..03' (For color-value fields in the Set Text Color control sequence or Logical Page Descriptor
command only)
X'025D..ee' (Except for X'025D..04', that is controlled with a CMR Tag Fidelity (X'96') triplet)
X'025E..00'
X'025E..01'
X'025E..02'
X'025E..03'
X'025E..04'
X'025E..05'
X'0115..00'
Refer to Figure 60 for a description of exception handling when a presentation fidelity control is
being used.
The default color-fidelity action if a PFC Color Fidelity (X'75') triplet is not received by the printer, if the activate
flag in a PFC command is B'0', or if the printer issues an IML NACK is as follows:
• If the printer supports color simulation, simulate valid but unsupported standard-OCA color values. [IPDS-15-245]
• Follow the XOA-EHC settings for unsupported color values that are not simulated, as follows: [IPDS-15-246]
– XOA-EHC byte 3, bit 7 (NoAEA) or byte 4, bit 6 (page continuation) specifies the continuation rule.
– XOA-EHC byte 2, bit 7 (report others) specifies the reporting rule.
– The color substitution rule is specified by the AEA or PCA for the exception.
Some printers provide a limited-simulated color support such that unsupported standard-OCA color values can
be simulated without reporting a NACK; this function is indicated by the X'40nn' property pair in a Text, IM-
Image, IO-Image, Graphics, or Bar Code command-set vector of an STM reply. When at least one Color
Fidelity (X'75') triplet has been received by a printer, all simulated-color support is overridden by the triplet until
either a PFC command that specifies reset is received or the printer issues an IML NACK.
Note: It is preferable that a printer use the same algorithm for both limited-simulated colors and for substitution
rule X'01' in the Color Fidelity (X'75') triplet. describes how
color values are simulated by different kinds of IPDS printers.
Triplet X'75'—Color Fidelity [IPDS-15-247]


| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'08' | Length of the triplet, including this length field | X'08' [IPDS-15-248]|
| 1 | CODE | TID | X'75' | Identifies the Color Fidelity triplet | X'75' [IPDS-15-249]|
| 2 | CODE | Continue | X'01'<br>X'02' | Color exception continuation rule:<br>Stop at point of first color exception and report exception<br>Do not stop at color exception | X'01'<br>X'02' [IPDS-15-250]|
| 3 | | X'00' | Reserved | | X'00' [IPDS-15-251]|
| 4 | CODE | Report | X'01'<br>X'02' | Reporting rule if the presentation process was not stopped:<br>Report color exception<br>Do not report color exception | X'01'<br>X'02' [IPDS-15-252]|
| 5 | | X'00' | Reserved | | X'00' [IPDS-15-253]|
| 6 | CODE | Substitute | X'01' | Substitution rule if the presentation process was not stopped:<br>Any color substitution is permitted | X'01' [IPDS-15-254]|
| 7 | | X'00' | Reserved | | X'00' [IPDS-15-255]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified or if the triplet is too long to fit in the PFC command, exception ID X'0254..01' exists.
Byte 1 Triplet ID
This field identifies this as a Color Fidelity (X'75') triplet.
Byte 2 Continue
This field specifies whether or not the presentation should be continued when a color
exception is detected. If an invalid continue value is specified, exception ID X'0254..02' exists.
The valid values are:
X'01' Stop printing at the point of the first color exception. In this case, a color exception
must be reported. How much of the current page is printed (partial or none) is
dependent on the XOA-EHC Exception Page Print flag.
X'02' Do not stop printing due to color exceptions.
Note: The set of supported color values is printer-specific; refer to your printer documentation
for a description of color values that the printer claims to support. For example, some
single-color printers support two color values: black and color of medium. Some
process-color printers claim support for all valid color values and achieve this by
mapping each color value received into the printer's gamut.
Some printers provide different color support on the front side from that supported on
the back side and sometimes this selective support is relative to specific media or to
specific places on the media. For example, a printer might use two different print
engines so as to support black on the front side and highlight color on the back side.
Another example is a printer that has multiple print heads some of which can only reach
a limited portion of the media. Refer to your printer documentation for specific
information about a printer's color capabilities.
Byte 3 Reserved
Triplet X'75'—Color Fidelity [IPDS-15-256]


Byte 4 Report
This field specifies whether or not color exceptions are reported when the presentation was
not stopped. If byte 2 of this triplet caused the presentation to stop, the exception must be
reported. If an invalid report value is specified, exception ID X'0254..03' exists. The valid
values are:
X'01' Report color exceptions.
X'02' Do not report color exceptions.
Byte 5 Reserved
Byte 6 Substitution rule
This field specifies the color substitution rule if the presentation was not stopped. If an invalid
substitution rule value is specified, exception ID X'0254..04' exists. The valid value is:
X'01' For color-value exceptions, any supported color value or grayscale intensity value can
be substituted for a color that cannot be rendered. For CMR exceptions, use an
appropriate printer default CMR.
Byte 7 Reserved
Note: This triplet is identical to the corresponding MO:DCA Color Fidelity (X'75') triplet.
IPDS commands that use this triplet:
Triplet X'75'—Color Fidelity [IPDS-15-257]


Metric Adjustment (X'79') Triplet
This triplet supplies metric values that can be used to adjust some of the metrics in an outline coded font. If
more than one Metric Adjustment (X'79') triplet is specified, the values from each Metric Adjustment (X'79')
triplet completely replace the adjustment values from any previous Metric Adjustment (X'79') triplet. Property
pair X'F203' in the Device-Control command-set vector of an STM reply indicates support for the Metric
Adjustment (X'79') triplet.
The Metric Adjustment (X'79') triplet can be used with the following resource type, resource ID format
combinations:
RT = X'10' (coded font), RIDF = X'07' (coded-font format)
RT = X'10' (coded font), RIDF = X'03' (GRID-parts format)
If specified with any other resource type, resource ID format combination, the Metric Adjustment (X'79') triplet
is ignored. The contents of ignored triplets are not checked by the printer for validity.
Note: A coded font activation (RT = X'10') can result in either an outline font being activated or a raster font
being activated. When the result is a raster font, the metric adjustments are not applied. When the result
is an outline font, the appropriate metrics in the activated font are adjusted using the values specified in
this triplet. The units of measure in the triplet might be different from the units of measure in the font
object.
This triplet is defined as follows: [IPDS-15-258]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0F' | Length of the triplet, including this length field | X'0F' [IPDS-15-259]|
| 1 | CODE | TID | X'79' | Identifies the Metric Adjustment triplet | X'79' [IPDS-15-260]|
| 2 | CODE | Unit base | X'00' | Metric technology unit base:<br>Fixed metrics, 10 inches | X'00' [IPDS-15-261]|
| 3–4 | UBIN | XUPUB | X'0001' – X'7FFF' | Units per unit base in the X direction | See byte description [IPDS-15-262]|
| 5–6 | UBIN | YUPUB | X'0001' – X'7FFF' | Units per unit base in the Y direction | See byte description [IPDS-15-263]|
| 7–8 | SBIN | H uniform increment | X'8000' – X'7FFF' | Uniform character increment value for horizontal writing | X'8000' – X'7FFF' [IPDS-15-264]|
| 9–10 | SBIN | V uniform increment | X'8000' – X'7FFF' | Uniform character increment value for vertical writing | X'8000' – X'7FFF' [IPDS-15-265]|
| 11–12 | SBIN | H baseline adjustment | X'8000' – X'7FFF' | Baseline offset adjustment value for horizontal writing | X'8000' – X'7FFF' [IPDS-15-266]|
| 13–14 | SBIN | V baseline adjustment | X'8000' – X'7FFF' | Baseline offset adjustment value for vertical writing | X'8000' – X'7FFF' [IPDS-15-267]|
Byte 0 Triplet length
This field contains the length of this triplet, including itself. If an invalid length is specified or if
the triplet is too long to fit in the containing command, exception ID X'028F ..03' exists.
Byte 1 Triplet ID
This field identifies this as a Metric Adjustment (X'79') triplet.
Triplet X'79'—Metric Adjustment [IPDS-15-268]


Byte 2 Metric technology unit base
If an invalid value is specified in this field, exception ID X'028F ..10' exists.
Bytes 3–4 Units per unit base in the X direction
The printer must support an equivalent value for each resolution specified in the XOH-OPC
IM-Image and Coded-Font Resolution self-defining field.
If an invalid or unsupported value is specified in this field, exception ID X'028F ..10' exists.
Bytes 5–6 Units per unit base in the Y direction
Bytes 3–4 and 5–6 must contain the same value.
The printer must support an equivalent value for each resolution specified in the XOH-OPC
IM-Image and Coded-Font Resolution self-defining field.
If an invalid or unsupported value is specified in this field, or if this field is not the same as the
XUPUB field (bytes 3–4), exception ID X'028F ..10' exists.
Bytes 7–8 Uniform character increment value for horizontal writing
This value is used only with horizontal writing (FIS = 0° or 180°), and is ignored with vertical
writing (FIS = 90° or 270°).
This field specifies a uniform character increment value using the units of measure specified in
bytes 2–6.
If this value is not X'0000', the font will be treated as a uniform font and this value will be used
as the uniform character increment. For each character, the A-space and B-space is not
changed and the C-space is increased (or decreased) to achieve the specified character
increment.
If this value is X'0000', the character increment values from the font are used.
Bytes 9–10 Uniform character increment value for vertical writing
This value is used only with vertical writing (FIS = 90° or 270°), and is ignored with horizontal
writing (FIS = 0° or 180°).
This field specifies a uniform character increment value using the units of measure specified in
bytes 2–6.
If this value is not X'0000', the font will be treated as a uniform font and this value will be used
as the uniform character increment. For each character, the A-space and B-space is not
changed and the C-space is increased (or decreased) to achieve the specified character
increment.
If this value is X'0000', the character increment values from the font are used.
Bytes 11–12 Baseline adjustment for horizontal writing
This value is used only with horizontal writing (FIS = 0° or 180°), and is ignored with vertical
writing (FIS = 90° or 270°).
This field specifies a baseline offset adjustment value using the units of measure specified in
bytes 2–6. For FIS = 0°, the value will be added to the baseline offset for each character in the
font. For FIS = 180°, the value will be subtracted from the baseline offset for each character in
the font.
If the combination of the adjustment value and a character's baseline offset value creates
another internal value that is beyond the range the printer can handle, exception ID X'028F ..11'
exists.
Bytes 13–14 Baseline adjustment for vertical writing
This value is used only with vertical writing (FIS = 90° or 270°), and is ignored with horizontal
writing (FIS = 0° or 180°).
Triplet X'79'—Metric Adjustment [IPDS-15-269]


This field specifies a baseline offset adjustment value using the units of measure specified in
bytes 2–6. For FIS = 90°, the value will be added to the baseline offset for each character in
the font. For FIS = 270°, the value will be subtracted from the baseline offset for each
character in the font.
If the combination of the adjustment value and a character's baseline offset value creates
another internal value that is beyond the range the printer can handle, exception ID X'028F ..11'
exists.
Note: FOCA host coded fonts also use this triplet to provide adjustment information.
IPDS commands that use this triplet:
Triplet X'79'—Metric Adjustment [IPDS-15-270]


Font Resolution and Metric Technology (X'84') Triplet
The last supported Font Resolution and Metric Technology (X'84') triplet encountered will be used to find the
raster-font resource to be activated; all other Font Resolution and Metric Technology (X'84') triplets are
ignored. Property pair X'F202' in the Device-Control command-set vector of an STM reply indicates support for
the Font Resolution and Metric Technology (X'84') triplet.
When a resident resource activation is attempted, the printer first attempts to match the fixed portion of the
resource ID to a resident resource. If a match is found, the Font Resolution and Metric Technology (X'84')
triplet is then used as follows:
• If the AR command contains a Font Resolution and Metric Technology (X'84') triplet, activation takes place [IPDS-15-271]
only if the resident resource has a matching resolution and metric technology.
• If the AR command does not contain a Font Resolution and Metric Technology (X'84') triplet, activation can [IPDS-15-272]
take place.
The Font Resolution and Metric Technology (X'84') triplet can be used with the following resource types:
X'01' Single-byte LF1-type coded font (AR and XOA-RRL commands)
X'02' Double-byte LF1-type coded font (XOA-RRL commands)
X'03' Double-byte LF1-type coded-font section (AR and XOA-RRL commands)
If specified with any other resource type, the Font Resolution and Metric Technology (X'84') triplet is ignored.
The contents of ignored triplets are not checked by the printer for validity.
This triplet is defined as follows: [IPDS-15-273]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06' or X'08' | Length of the triplet, including this length field | X'06' or X'08' [IPDS-15-274]|
| 1 | CODE | TID | X'84' | Identifies the Font Resolution and Metric Technology triplet | X'84' [IPDS-15-275]|
| 2 | CODE | Metric technology | X'01'<br>X'02' | Fixed-metric technology<br>Relative-metric technology | X'01'<br>X'02' [IPDS-15-276]|
| 3 | CODE | Unit base | X'00' | Raster-pattern resolution unit base:<br>Ten inches | X'00' [IPDS-15-277]|
| 4–5 | UBIN | X units per unit base | X'0001' – X'7FFF' | Raster-pattern resolution units per unit base in the X direction:<br>X'0960' = 240 pels per inch<br>X'0BB8' = 300 pels per inch | X'0960'<br>X'0BB8' [IPDS-15-278]|
| 6–7 | UBIN | Y units per unit base | X'0001' – X'7FFF' | Optional raster-pattern resolution units per unit base in the Y direction:<br>X'0960' = 240 pels per inch<br>X'0BB8' = 300 pels per inch<br>This optional field can be omitted if the X and Y resolutions are equal. | X'0960'<br>X'0BB8' [IPDS-15-279]|
Byte 0 Triplet length
This field contains the length of this triplet, including itself. If an invalid length is specified or if
the triplet is too long to fit in the containing command, exception ID X'028F ..03' exists.
Byte 1 Triplet ID
This field identifies this as a Font Resolution and Metric Technology (X'84') triplet.
Triplet X'84'—Font Resolution [IPDS-15-280]


Byte 2 Metric technology
This field specifies the metric technology used by this raster font. If an invalid value is
specified in this field, exception ID X'028F ..04' exists.
Byte 3 Raster-pattern resolution unit base
This field specifies the unit base for the raster font's resolution. The raster-pattern information
is contained in the font's Load Font (LF) commands.
If an invalid value is specified in this field, exception ID X'028F ..04' exists.
Bytes 4–5 Raster-pattern resolution units per unit base in the X direction
These bytes specify the number of pels per unit base of the font's raster-pattern shape data in
the X direction (or in both the X and Y directions, if optional bytes 6–7 are omitted).
If an invalid or unsupported value is specified in this field, exception ID X'028F ..04' exists.
Bytes 6–7
(optional)
Raster-pattern resolution units per unit base in the Y direction
These bytes specify the number of pels per unit base of the font's raster-pattern shape data in
the Y direction. This optional field can be omitted if the X and Y resolutions are equal.
If an invalid or unsupported value is specified in this field, exception ID X'028F ..04' exists.
Note: MO:DCA data streams can also use this triplet to tag raster font mappings with font-resolution and
metric-technology information.
IPDS commands that use this triplet:
Triplet X'84'—Font Resolution [IPDS-15-281]


Finishing Operation (X'85') Triplet
The Finishing Operation (X'85') triplet specifies a specific finishing operation to be applied to either a sheet or
to a collection of sheets, depending on the command containing the triplet:
• If specified on an AFO command, the operation applies to the current sheet and each copy of that sheet. [IPDS-15-282]
• If specified on an XOH-DGB command, the operation applies to a collection of sheets (the sheets within a [IPDS-15-283]
group).
Some printers support two different finishing triplets (X'85' and X'8E'); the X'8E' triplet is intended for UP3I-
controlled devices and the X'85' triplet is intended for other devices. However, these two triplets can coexist in
the same data stream and wherever an operation (and all parameters) can be specified in either triplet, the two
triplets are interchangeable.
• If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the [IPDS-15-284]
printer will convert to the other triplet if necessary.
• If an operation can only be fully specified in one of the triplets, that triplet must be used. [IPDS-15-285]
Multiple finishing operations can be applied by including multiple Finishing Operation triplets (either X'85' or
X'8E'). In this case, the operations are applied in the order received and duplicate identical Finishing Operation
triplets are ignored (the first is used and the duplicates are ignored). Figure 62 shows an example
of how multiple finishing operations can be specified.
Not all combinations of finishing operations are compatible; for example, two Z-fold operations along different
reference edges might not be compatible. Compatible combinations of finishing operations are device specific.
If incompatible finishing operations are specified, exception ID X'027C..01' exists.
For some printers, finishing operations can only be done when the output is routed to specific media
destinations. In this case, when finishing is selected and an incompatible media destination is selected or
defaulted to, exception ID X'027C..09' exists.
Printer support for the X'85' triplet is indicated by presence of the XOH-OPC Finishing Operations self-defining
field.
Triplet X'85'—Finishing Operation [IPDS-15-286]


This triplet is defined as follows: [IPDS-15-287]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'09'–X'FD' odd values | Length of the triplet, including this length field | X'09' [IPDS-15-288]|
| 1 | CODE | TID | X'85' | Identifies the Finishing Operation triplet | X'85' [IPDS-15-289]|
| 2 | CODE | Operation type | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'0A'<br>X'0C'<br>X'0D'<br>X'0E'<br>X'0F'<br>X'12'<br>X'14'<br>X'18'<br>X'19'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'30'<br>X'31'<br>X'32' | Corner staple<br>Saddle-stitch out<br>Edge stitch<br>Fold in<br>Separation cut<br>Perforation cut<br>Z fold<br>Center-fold in<br>Trim after center fold or saddle stitch<br>Punch<br>Perfect bind<br>Ring bind<br>C-fold in<br>Accordion-fold in<br>Saddle-stitch in<br>Fold out<br>Center-fold out<br>Trim<br>C-fold out<br>Accordion-fold out<br>Double parallel-fold in<br>Double gate-fold in<br>Single gate-fold in<br>Double parallel-fold out<br>Double gate-fold out<br>Single gate-fold out | At least one value [IPDS-15-290]|
| 3 | CODE | Finishing option | X'00'<br>X'01' | Finishing option, for certain finishing operations:<br>No finishing option<br>Crease (for folding operations) | X'00' [IPDS-15-291]|
| 4 | X'00' | Reserved | X'00' [IPDS-15-292]| | |
| 5 | CODE | Reference | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Reference corner and edge:<br>Bottom-right corner; bottom edge<br>Top-right corner; right edge<br>Top-left corner; top edge<br>Bottom-left corner; left edge<br>Default corner; default edge | X'FF' [IPDS-15-293]|
| 6 | UBIN | Count | X'00'<br>X'01'–X'7A' | Not specified<br>Number of operations to apply | X'00' [IPDS-15-294]|
| 7–8 | UBIN | Axis offset | X'0000' – X'7FFF'<br>X'FFFF' | Axis offset in millimeters<br>Device default axis offset | X'FFFF' [IPDS-15-295]|
| **Zero or more finishing operation positions in the following format:** [IPDS-15-296]| | | | | |
| + 0–1 | UBIN | Position | X'0000' – X'7FFF' | Operation position on axis in millimeters [IPDS-15-297]| |
Triplet X'85'—Finishing Operation [IPDS-15-298]


Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified, exception ID X'027A..01' exists. If the triplet is too long to fit in the containing
command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as a Finishing Operation (X'85') triplet.
Byte 2 Type of finishing operation
This field specifies the type of the finishing operation; examples are shown in Figure 114. If an invalid or unsupported value is specified in this field, exception ID X'027C..03'
exists.
Some operations can be applied to an individual sheet and some can be applied to a group of
sheets, as shown in the following table:
Table 61. Sheet and Group Operations
Sheet Operations (AFO) Group Operations (XOH DGB)
Accordion-fold in
Accordion-fold out
Center-fold in
Center-fold out
C-fold in
C-fold out
Double gate-fold in
Double gate-fold out
Double parallel-fold in
Double parallel-fold out
Fold in
Fold out
Perforation cut
Punch
Separation cut
Single gate-fold in
Single gate-fold out
Trim
Z fold
Accordion-fold in
Accordion-fold out
Center-fold in
Center-fold out
C-fold in
C-fold out
Corner staple
Edge stitch
Fold in
Fold out
Perfect bind
Perforation cut
Punch
Ring bind
Saddle-stitch in
Saddle-stitch out
Separation cut
Single gate-fold in
Single gate-fold out
Trim
Trim after center fold or saddle stitch
Note: If a finishing operation that is supported only as a sheet operation is specified on an XOH-DGB
command, exception ID X'027C..03' exists. Likewise, if a finishing operation that is supported only
as a group operation is specified on an AFO command, exception ID X'027C..03' exists.
When a printer supports a finishing operation listed in the group operation column and the printer
supports the XOH-DGB command, the printer supports that finishing operation as a group operation.
When a printer supports a finishing operation listed in the sheet operation column and the printer
supports the AFO command, the printer supports that finishing operation as a sheet operation.
Accordion-fold in
This operation causes a single-sheet or a collection of sheets to be folded
inward (top fold) and outward (2nd fold) along two lines parallel to the
finishing axis. The sheet or sheets are folded into a Z-like shape of three
panels. The middle panel is usually slightly larger than the two outer panels.
The lower panel of the front of the first sheet and the upper panel of the back
of the last sheet will be visible on the outside. Accordion-fold in is similar to Z
fold (operation type X'07'); however, Z fold has a first panel that is twice the
size of the other two.
Triplet X'85'—Finishing Operation [IPDS-15-299]


Also known as: concertina fold, letter fold, tri fold, and zig-zag fold
Accordion-fold out
This operation causes a single-sheet or a collection of sheets to be folded
outward (top fold) and inward (2nd fold) along two lines parallel to the
finishing axis. The sheet is folded into a Z-like shape of three panels. The
middle panel is usually slightly larger than the two outer panels. The upper
panel of the front of the first sheet and the lower panel of the back of the last
sheet will be visible on the outside. Accordion-fold out is often used for letters
instead of c-fold out.
Also known as: concertina fold, letter fold, tri fold, and zig-zag fold
C-fold in
This operation causes a single-sheet or a collection of sheets to be folded
inward along two lines parallel to the finishing axis. After a c-fold-in operation,
the front of the single sheet (or the first sheet of the group) is on the inside of
the folded set. The back of the last sheet in the group will be visible on the
outside of the folded set. To allow the panels to nest inside each other
properly, the folded in bottom panel is usually 1/32" to 1/8" narrower than the
other two panels. This bottom panel is the bottom part of the sheet, seen from
the top edge. C-fold in is used for letters when envelopes without address
windows are used.
Also known as: business-letter fold, letter fold, roll fold, spiral fold, and tri fold
C-fold out
This operation causes a single-sheet or a collection of sheets to be folded
outward along two lines parallel to the finishing axis. After a c-fold-out
operation, the front of the single sheet (or the first sheet of the group) is visible
on the outside of the folded set. The back of the last sheet in the group will be
on the inside of the folded set. To allow the panels to nest inside each other
properly, the folded in bottom panel is usually 1/32" to 1/8" narrower than the
other two panels. This bottom panel is the bottom of the sheet, seen from the
top edge. This folding type is often used for letters when envelopes with
windows are used so that the address on the first page will then show through
the window of the envelope.
Also known as: business-letter fold, letter fold, roll fold, spiral fold, and tri fold
Center-fold in
This operation causes a single-sheet or a collection of sheets to be folded
inward along the center line that is parallel to the finishing operation axis.
After a center-fold-in operation, the back of the single sheet (or last sheet of
the group) is on the outside of the folded booklet. The folding is the same as
with saddle-stitch in, but without the stitch.
Also known as: bi-fold, half fold, and 2-fold
Center-fold out
This operation causes a single-sheet or a collection of sheets to be folded
outward along the center line that is parallel to the finishing operation axis.
After a center-fold-out operation, the front side of the single sheet (or the first
sheet of the group) is on the outside of the folded set. Center-fold out is often
used to fold A4 letters for an A5 envelope so that the address on the first page
will then show through the window of the envelope. The folding is the same as
with saddle-stitch out, but without the stitch.
Also known as: bi-fold, half fold, and 2-fold
Corner staple
Triplet X'85'—Finishing Operation [IPDS-15-300]


Corner stapling a collection of sheets is normally done by driving a staple into
one of the corners.
Double gate-fold in
This operation causes a single sheet to be folded into four panels of roughly
equal size. First the two outer panels are folded inwards so that the top and
the bottom edges of the sheet meet. The folded sheet is then folded again in
the middle so that the top and bottom panels are inside. The back of the
second and third panel will be visible on the outside. The front of the sheet will
be inside the folded product. The two outer panels are usually 1/32" to 1/8"
smaller than the two inner panels to allow for proper folding and nesting. The
double gate fold is sometimes used for large magazine centerfolds.
Also known as: closed-gate fold and gate fold
Double gate-fold out
This operation causes a single sheet to be folded into four panels of roughly
equal size. First the two outer panels are folded outwards so that the top and
the bottom edges of the sheet meet. The folded sheet is then folded again in
the middle so that the top and bottom panels are inside. The fronts of the two
middle panes will be visible on the outside. The outer panels are usually 1/32"
to 1/8" smaller than the inner panels to allow for proper folding and nesting.
Also known as: closed-gate fold and gate fold
Double parallel-fold in
This operation causes a single sheet to be folded inwards, first in the middle
and then the folded sheet is folded once again so that four panels are formed.
The folds are parallel to the finishing axis. The front of the sheet is inside. To
allow for proper nesting the two inside folded panels are 1/32" to 1/8" smaller
than the two outer panels.
Also known as: double fold, parallel fold, and quarter fold
Double parallel-fold out
This operation causes a single sheet to be folded outwards, first in the middle
and then the folded sheet is folded once again so that four panels are formed.
The folds are parallel to the finishing axis. The top and the second panel of
the front of the sheet will be visible on the outside. To allow for proper nesting
the two inside folded panels are 1/32" to 1/8" smaller than the two outer
panels.
Also known as: double fold, parallel fold, and quarter fold
Fold in
Folding a single sheet or a collection of sheets is done along the finishing
operation axis. The sheet or collection of sheets is folded inward on the front
sheet side. After a fold-in operation, the back of the single sheet (or last sheet
of the group) is on the outside of the folded sheets.
Also known as: Single fold
Fold out
Folding a single sheet or a collection of sheets is done along the finishing
operation axis. The sheet or collection of sheets is folded outward on the front
sheet side. After a fold-out operation, the front of the single sheet (or first
sheet of the group) is on the outside of the folded sheets.
Also known as: Single fold
Perfect bind
Perfect binding is a type of book binding in which the sheets of the group are
glued together at the reference edge (spine). The device may optionally
Triplet X'85'—Finishing Operation [IPDS-15-301]


include a cover sheet that is pre-loaded in the binding machine, is wrapped
around the front, spine, and back, and is attached at or near the spine.
Punch
This operation causes one or more holes to be punched or drilled into a single
sheet or into each sheet of a group. The shape of the holes is device specific
and cannot be controlled with this triplet.
Ring bind
Ring binding is a type of book binding in which the sheets of the group are
loosely connected at the reference edge (spine) by first drilling or punching a
set of holes along the reference edge and then inserting a wire pattern
through the holes. This allows the sheets of a document to be flexibly turned
and laid flat against a surface without breaking the spine. When the wire
pattern is a wire helix, this operation is also called a spiral bind or coil bind.
The device may optionally include front and back cover sheets that were pre-
loaded in the binding machine.
Saddle stitch and edge stitch
Stitching is a method of binding using one or more staples; saddle stitching
binds along a center fold, edge stitching binds along one edge. Note that
saddle stitching also center folds the sheets either inward or outward
depending on the operation type. An inward fold causes the front side of the
first sheet of the group to be on the inside of the booklet; an outward fold
causes the front side of the first sheet of the group to be on the outside of the
booklet; refer to Figure 114 for a picture of these two folds.
Separation cut and perforation cut
Cutting is done along the finishing operation axis. A separation cut separates
each sheet of media into two pieces. A perforation cut leaves each sheet
intact, but provides a means to easily tear off part of the sheet.
Single gate-fold in
This operation causes a single-sheet or a collection of sheets to be folded into
three panels with two outer panels and a larger middle panel. The two outer
panels are folded inwards so that the top and the bottom edges of the sheet
meet. The back of the panels of the single sheet (or of the last sheet of the
group) will be visible on the outside. The front of the single sheet (or all other
sides of the group of sheets) will be inside the folded product. This fold is
sometimes used for menus and brochures.
Also known as: gate fold, simple gate fold, and window fold
Single gate-fold out
This operation causes a single-sheet or a collection of sheets to be folded into
three panels with two outer panels and a larger middle panel. The two outer
panels are folded outwards so that the top and the bottom edges of the sheet
meet. The front of the panels of the single sheet (or of the first sheet of the
group) will be visible on the outside. The back of the single sheet (or all other
sides of the group of sheets) will be inside the folded product.
Also known as: gate fold, simple gate fold, and window fold
Trim
This operation causes a single sheet or the sheets of a group to be trimmed
(cut) along the finishing operation axis. Once cut, the part of the sheet (or
sheets) that is adjacent to the reference edge is discarded.
Trim after center fold or saddle stitch
Trim after center fold or saddle stitch is intended to accompany either a
center-fold operation or a saddle-stitch operation.
Triplet X'85'—Finishing Operation [IPDS-15-302]


• If, within a single XOH Define Group Boundary command, a Finishing [IPDS-15-303]
Operation (X'85') triplet for the trim-after-center-fold-or-saddle-stitch
operation is specified immediately after a finishing operation that causes a
center fold (either saddle stitch or center fold), the edges opposite the
center fold are trimmed by the amount specified in the axis offset
parameter. The offset is measured from the edges of the innermost sheet
that are opposite the center fold.
• If this operation is specified, but is not immediately after a center-fold or [IPDS-15-304]
saddle-stitch operation, the trim operation is ignored.
Z fold
Z folding causes the current sheet to be first folded in half inwards (so the
front side of the sheet is now inside the fold) along a line parallel to the
reference edge. The half of the sheet furthest from the reference edge is
again folded in half outwards along a line parallel to the reference edge. For
example, when applied to an 11"×17" sheet with the reference edge along the
top (a short side), the result is an 8.5"×11" foldout.
Note that if additional finishing operations are applied to the Z-folded sheet,
the sheet is reoriented so that the original reference edge becomes either the
left or top edge for the additional finishing operations. This reorientation is
done such that the new top edge is a short edge. For most media, the
reorientation causes the reference edge to become the new left edge;
however, when the reference edge is less than half the size of the other sheet
dimension, the reorientation causes the reference edge to become the new
top edge. In the previous example, the reference edge for the Z fold was the
top (11"). After Z folding is applied, the sheet is reoriented so that this
reference edge now becomes the left edge for additional finishing operations.
Therefore, if the Z-folded sheets are to be stapled to some number of
8.5"×11" sheets, the stapling reference edge for both sets of sheets is
specified to be the left edge.
Also known as: Engineering fold
Note: Finishing operations are inherently device specific; for example, not all stapling systems
have the same capabilities in terms of positioning, thickness that can be stapled, and
mechanism controls. The stapler might also work only with specific media destinations
or specific kinds of media. The Finishing Operations self-defining field in the XOH-OPC
reply indicates the supported finishing operations.
If the selected finishing operation is incompatible with the selected media, or media
destination, either exception ID X'027C..09' or X'027C..0B' exists. If an LCC command that
changes the media destination is received within a group to be finished and the finishing
operation cannot be performed, exception ID X'027C..0A' exists. If the printer runs out of
staples during a staple or stitch operation, exception ID X'407C..00' exists. If the staple
mechanism jams or causes a physical-media jam, exception ID X'407C..01' exists. If the
punch waste bin becomes full, exception ID X'407C..03' exists.
Byte 3 Finishing option
This field specifies an option to be applied for certain finishing operations. If an invalid or
unsupported value is specified in this field, exception X'027C..0D' exists. The valid values are:
X'00' No finishing options specified
X'01' Crease finishing option
For an accordion fold, c-fold, center-fold, double-gate-fold, double-parallel
fold, fold, single-gate-fold, or Z-fold operation, this value specifies that a
crease operation is performed instead of each fold defined for the operation.
For example, for an accordion fold, rather than an inward fold and outward
Triplet X'85'—Finishing Operation [IPDS-15-305]


fold, an inward crease and outward crease are made, at the same locations
the two folds would have been performed.
For a corner staple, saddle stitch, edge stitch, separation cut, perforation cut,
trim, trim after center fold or saddle stitch, punch, perfect bind, or ring bind
operation, this value is ignored.
Printer support for a given finishing option is indicated in the XOH-OPC Finishing Options self-
defining field.
Byte 4 Reserved
Triplet X'85'—Finishing Operation [IPDS-15-306]


Figure 114. Examples of Finishing Operations (spans three pages)
(Part 1 of figure)
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
Triplet X'85'—Finishing Operation [IPDS-15-307]


(Part 2 of figure)
Front
of
sheet
Top-right corner (X'01')
Bottom-right corner (X'00')Bottom-left corner (X'03')
Top-left corner (X'02')
oblique stapling
parallel stapling
Corner Staple
Note: The offset and angle of the staple from
the selected corner is device dependent.
Front
of
sheet
Edge Stitch
Note: This example shows an
edge stitch with 2 staples
parallel to the left edge.
Left edge
axis offset from left edge
Z Fold
Front side
of 11" x 17
"
sheet after
Z fold
Second fold is outward
First fold is inward
Finishing operation axis
Front side of
the first sheet
of the group
Fold
is
outward
Saddle-Stitch Out
Note: This example shows a
saddle stitch with 3 staples
parallel to the top edge.
The first sheet is on the
outside of the booklet.
Top edge
Front side of
the first sheet
of the group
Fold
is
inward
Saddle-Stitch In
Note: This example shows a
saddle stitch with 2 staples
parallel to the top edge.
The first sheet is on the
inside of the booklet.
Top edge
Front
of
sheet
Punch
Note: This example shows a
3 hole punch operation [IPDS-15-308]
parallel to the left edge.
Left edge
axis offset from left edge
Front side of
the first sheet
of the group
Front side of
the first sheet
of the group
Front side of
the first sheet
of the group
Fold
is
inward
Center-Fold In
Note: Center fold in is the same
as saddle stitch in without
the staples.
Top edge
Front side of
the first sheet
of the group
Fold In
Front side of sheet
is inside the fold.
Finishing operation a
xis
Note: This example shows a sheet
folded inward at an offset of
4.4 inches from the top. [IPDS-15-309]
Fold Out
Note: This example shows a sheet
folded outward at an offset of
4.4 inches from the top. [IPDS-15-310]
Finishing operation axis
Front side of sheet
is outside the fold.
Triplet X'85'—Finishing Operation [IPDS-15-311]


(Part 3 of figure)
Original Fold-In Operation Fold-Out Operation
Center-fold in Center-fold out
C-fold in C-fold out
Center-fold in Center-fold outGroup of pages
more tmore text
Accordion-fold in Accordion-fold out
Double parallel-fold in Double parallel-fold out
Double gate-fold in Double gate-fold out
Single gate-fold in Single gate-fold out
more te
Triplet X'85'—Finishing Operation [IPDS-15-312]


Byte 5 Reference corner and edge
For a corner-staple operation, this field specifies the corner to be stapled. The offset and angle
of the staple from the corner is device dependent.
Note: For all types of media shown in Figure 115, the top-left corner is defined to be the
default media origin of the front side. The XOH Set Media Origin command does not
change the finishing corners or edges. For continuous-forms media, the carrier strips
are not considered to be part of the physical media.
For the following finishing operations, this field specifies the reference edge that is used to
position the finishing operation axis:
accordion fold
C fold
center fold
double parallel fold
edge stitch
fold
perforation cut
punch
saddle stitch
separation cut
trim
Z fold
For a perfect-bind or ring-bind operation, this field specifies the binding edge.
For a double-gate-fold, single-gate-fold, or trim-after-center-fold-or-saddle-stitch operation,
this field is ignored.
If an invalid or unsupported value is specified in this field, exception ID X'027C..04' exists.
Figure 115. Reference Edges for Various Kinds of Media
Top Top
Top
Bottom Bottom
Bottom
Right Right
Right
Left
Left
Left
Cut-sheet media Wide continuous-forms
media
Narrow continuous-forms
media
= Default media origin
Byte 6 Finishing operation count
For a corner-staple operation, this field is ignored; a single staple is used.
For stitching and punch operations, this field specifies the desired number of staples or holes
along the finishing operation axis. The count value is used in conjunction with any specified
finishing operation position values, in the following manner:
• If no position values are specified, the count value specifies the number of staples or holes [IPDS-15-313]
to use at printer-determined locations along the finishing operation axis. To select the device
default number and positions, specify a count of X'00' and don't specify any position values.
• If any position values are specified, the count must either be X'00' or match the number of [IPDS-15-314]
specified positions. In this case there is one position value for each staple or hole. Exception
ID X'027C..07' exists when the number of positions does not match the count or when the
supported number of positions is exceeded.
Triplet X'85'—Finishing Operation [IPDS-15-315]


For an accordion-fold, c-fold, center-fold, double-gate-fold, double-parallel-fold, fold, perfect-
bind, perforation-cut, ring-bind, separation-cut, single-gate-fold, trim, trim-after-center-fold-or-
saddle-stitch, or Z-fold operation, this field is ignored.
If an unsupported value is specified in this field, exception ID X'027C..05' exists.
Bytes 7–8 Finishing operation axis offset
For an edge-stitch, fold, separation-cut, perforation-cut, trim,
or punch operation, this field
specifies the offset in millimeters of a positioning axis from the selected reference edge. The
finishing operation is done along this axis. To select the device default axis offset, specify
X'FFFF'.
For a trim-after-center-fold-or-saddle-stitch operation, this field specifies the offset in
millimeters of a positioning axis from the edges opposite the center fold. The offset is
measured from the edges of the inner-most sheet that are opposite the center fold; the
accuracy of this measurement depends on how accurately the trimmer hardware has been
adjusted. The trim operation will be done along this axis. An offset of X'0000' will trim nothing;
to select the device default axis offset, specify X'FFFF'.
For an accordion-fold, c-fold, corner-staple, double-gate-fold, double-parallel-fold, perfect-
bind, ring-bind, single-gate-fold, or Z-fold operation, this field is ignored.
For a center-fold or saddle-stitch operation, this field is ignored. The finishing operation axis is
placed at the center of the sheet and is parallel to the reference edge.
If an invalid or unsupported value is specified in this field, exception ID X'027C..06' exists.
Zero or more
finishing
operation
positions
The operation type determines how the following position fields, if any, are used. Each
consecutive position field is used to position a single finishing operation on the finishing
operation axis. This continues until the end of the triplet is reached, or the maximum number
of finishing operations that the device can handle is reached.
If the count value is not X'00', the count value must match the number of position values.
Exception ID X'027C..07' exists if the number of positions does not match the count or if the
supported number of positions is exceeded.
Bytes + 0 to 1 Finishing operation position
For stitching and punch operations, this field specifies a position in millimeters along the
finishing operation axis. The origin of the finishing operation axis is the point where the
finishing operation axis intersects, at a right angle, either the bottom or the left edge of the
physical medium. A single staple or hole is placed at each position, centered on the
positioning point.
For an accordion-fold, c-fold, center-fold, corner-staple, double-gate-fold, double-parallel-fold,
fold, perfect-bind, perforation-cut, ring-bind, separation-cut, single-gate-fold, trim,
trim-after-
center-fold-or-saddle-stitch, or Z-fold operation, this field is ignored.
If an invalid or unsupported value is specified in this field, exception ID X'027C..08' exists.
Note: This triplet is identical to the corresponding MO:DCA Finishing Operation (X'85') triplet.
IPDS commands that use this triplet:
Triplet X'85'—Finishing Operation [IPDS-15-316]


Text Fidelity (X'86') Triplet
The Text Fidelity (X'86') triplet is used to specify the exception continuation and reporting rules when an
unrecognized or unsupported text control sequence is encountered. The applicable exception IDs are
X'0200..01',
X'029C..05', and X'029D..01' .
Support for the Text Fidelity (X'86') triplet is indicated by the PFC Triplets Supported self-defining field returned
in the XOH-OPC reply.
Refer to Figure 60 for a description of exception handling when a presentation fidelity control is
being used.
The default text-fidelity action if a PFC Text Fidelity (X'86') triplet is not received by the printer, if the activate
flag in a PFC command is B'0', or if the printer issues an IML NACK is to report the error and follow the
directions of the currently active XOA-EHC command. [IPDS-15-317]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' [IPDS-15-318]|
| 1 | CODE | TID | X'86' | Identifies the Text Fidelity triplet | X'86' [IPDS-15-319]|
| 2 | CODE | Continue | X'01'<br>X'02' | Text exception continuation rule:<br>Stop processing WT data<br>Continue processing WT data | X'01'<br>X'02' [IPDS-15-320]|
| 3 | X'00' | Reserved | X'00' [IPDS-15-321]| | |
| 4 | CODE | Report | X'01'<br>X'02' | Text exception reporting rule:<br>Report text exceptions<br>Do not report text exceptions | X'01'<br>X'02' [IPDS-15-322]|
| 5–6 | X'0000' | Reserved | X'0000' [IPDS-15-323]| | |
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified or if the triplet is too long to fit in the PFC command, exception ID X'0254..51' exists.
Byte 1 Triplet ID
This field identifies this as a Text Fidelity (X'86') triplet.
Byte 2 Continuation rule for a text exception
This field specifies whether or not the printer should continue processing page, overlay, or
page segment data after a text exception occurs. If an invalid continuation-rule value is
specified, exception ID X'0254..52' exists.
The valid values are:
X'01' Stop printing at the first text exception.
In this case, the exception must be reported. When this value is specified, the printer
ignores triplet byte 4 and reports the exception. Also, the printer stops processing
further data; refer to Figure 60 for a description of exception handling for
this situation.
X'02' Continue processing WT data by skipping the unrecognized or unsupported control
sequence and continuing with the next control sequence or text code point. This
skipping through unrecognized control sequences might cause unexpected results if
the unrecognized data is actually not a control sequence or if it is a valid (but
unsupported) control sequence that is needed to properly process the text data.
Triplet X'86'—Text Fidelity [IPDS-15-324]


Note: If the printer recognizes the control sequence as Unicode Complex Text (UCT)
but doesn't support UCT , the UCT code points should be processed as if they
had been within a TRN control sequence.
Byte 3 Reserved
Byte 4 Reporting rule for a text exception
This field specifies whether or not text exceptions are reported by the printer; however, when
the continuation rule (byte 2) is set to X'01' (stop), the exception must be reported. If an invalid
report value is specified, exception ID X'0254..53' exists. The valid values are:
X'01' Report text exceptions.
X'02' Do not report text exceptions.
Bytes 5–6 Reserved
Note: This triplet is identical to the corresponding MO:DCA Text Fidelity (X'86') triplet.
IPDS commands that use this triplet:
Triplet X'86'—Text Fidelity [IPDS-15-325]


Finishing Fidelity (X'88') Triplet
The Finishing Fidelity (X'88') triplet is used to specify the exception continuation and reporting rules for
finishing exceptions. Support for the Finishing Fidelity (X'88') triplet is indicated by the PFC Triplets Supported
self-defining field returned in the XOH-OPC reply.
This fidelity control applies when a request for a specific finishing operation cannot be satisfied. The following
lists the applicable finishing exception IDs that cause the printer to apply the current finishing fidelity control:
X'027C..01' Incompatible finishing operations
X'027C..02' Too many or too few sheets for a finishing operation (action code X'06')
X'027C..03' Invalid or unsupported finishing operation type
X'027C..04' Invalid or unsupported finishing operation reference corner and edge
X'027C..05' Unsupported finishing operation count
X'027C..06' Invalid or unsupported finishing operation axis offset
X'027C..07' Invalid or unsupported number of finishing positions
X'027C..08' Invalid or unsupported finishing operation position
X'027C..09' Finishing operation incompatible with physical media or media destination
X'027C..0A' Finishing operation incompatible with change in media destination
X'027C..0D' Invalid or unsupported finishing option
X'027E..00' Invalid or unsupported parameter specification for a UP
3I-controlled device (action code X'01'
or X'06' or X'0A')
Refer to Figure 60 for a description of exception handling when a presentation fidelity control is
being used.
The default finishing-fidelity action if a PFC Finishing Fidelity (X'88') triplet is not received by the printer, if the
activate flag in a PFC command is B'0', or if the printer issues an IML NACK is to report the error and not apply
the finishing operation. [IPDS-15-326]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' [IPDS-15-327]|
| 1 | CODE | TID | X'88' | Identifies the Finishing Fidelity triplet | X'88' [IPDS-15-328]|
| 2 | CODE | Continue | X'01'<br>X'02' | Finishing exception continuation rule:<br>Stop at first finishing exception<br>Continue without the finishing operation | X'01'<br>X'02' [IPDS-15-329]|
| 3 | X'00' | Reserved | X'00' [IPDS-15-330]| | |
| 4 | CODE | Report | X'01'<br>X'02' | Finishing exception reporting:<br>Report finishing exceptions<br>Do not report finishing exceptions | X'01'<br>X'02' [IPDS-15-331]|
| 5–6 | X'0000' | Reserved | X'0000' [IPDS-15-332]| | |
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified or if the triplet is too long to fit in the PFC command, exception ID X'0254..41' exists.
Triplet X'88'—Finishing Fidelity [IPDS-15-333]


Byte 1 Triplet ID
This field identifies this as a Finishing Fidelity (X'88') triplet.
Byte 2 Continuation rule for a finishing exception
This field specifies whether or not the host program should continue processing a print file
after a finishing exception occurs; the print file can continue without the finishing operation or it
can be terminated and put in a state where it can be resubmitted for later printing. If an invalid
continue value is specified, exception ID X'0254..42' exists.
The valid values are:
X'01' Stop printing at the point of the first finishing exception.
In this case, a finishing exception must be reported. When this value is specified, the
printer ignores triplet byte 4 and reports the finishing exception; in this case, this triplet
is effectively a NOP .
X'02' Continue, but do not apply the finishing operation. If the device supports a different
finishing operation that is reasonably equivalent to the requested operation, the
supported operation may be applied in place of the requested operation. For example,
c-fold out and accordion-fold out are often interchangeable when the output is to be
inserted into a window envelope; if the device supports accordion-fold out (and not c-
fold out), but the triplet requests a c-fold out operation, the device can use accordion-
fold out when applying the continuation rule.
Byte 3 Reserved
Byte 4 Reporting rule for a finishing exception
This field specifies whether or not certain finishing exceptions are reported by the printer;
however, when the continuation rule (byte 2) is set to X'01' (stop), the exception must be
reported. The specific finishing exception IDs controlled by this field are listed at the beginning
of the triplet description. If an invalid report value is specified, exception ID X'0254..43' exists.
The valid values are:
X'01' Report finishing exceptions.
X'02' Do not report finishing exceptions.
Bytes 5–6 Reserved
Note: This triplet is identical to the corresponding MO:DCA Finishing Fidelity (X'88') triplet; however, when the
continuation rule is “stop” (X'01'), the host controls finishing fidelity, the printer reports all finishing
exceptions, and, except for checking the triplet for syntax errors, the printer essentially treats the triplet
as a NOP .
IPDS commands that use this triplet:
Triplet X'88'—Finishing Fidelity [IPDS-15-334]


Data Object Font Descriptor (X'8B') Triplet
The Data Object Font Descriptor (X'8B') triplet is used to specify the parameters needed to activate and use a
data-object font:
• Vertical font size [IPDS-15-335]
• Horizontal scale factor [IPDS-15-336]
• Character rotation [IPDS-15-337]
• Character encoding scheme [IPDS-15-338]
Data-object fonts can use either the TrueType font technology or the OpenType font technology. Property pair
X'F204' in the Device-Control command-set vector of an STM reply indicates support for the Data Object Font
Descriptor (X'8B') triplet.
Before using a data-object font, three steps must be successfully completed:
1. A data-object-font component (a TrueType/OpenType font, or a TrueType/OpenType collection) must be [IPDS-15-339]
activated either by downloading with object container commands or by activating a resident resource with
the AR command; both activation methods assign a data-object-font-component HAID to the data-object-
font component. This is called a base font.
If one or more TrueType/OpenType objects are to be linked to the base font, those data-object-font
components must also be activated.
In addition, if a code page is to be used with the font, that code page must also be activated.
2. The component parts (a font or font collection, an optional code page, and optional linked TrueType/ [IPDS-15-340]
OpenType objects) must be combined into a data-object font with an AR command; a data-object font
HAID is assigned by this activation. This triplet (X'8B') is specified in the AR command as part of the data-
object font activation.
3. A Load Font Equivalence command must be sent to the printer to establish the LID to data-object font [IPDS-15-341]
HAID mapping.
Step 3 and the combination of steps 1 and 2 can be done in either order as long as they are both complete
before the first character to be printed is encountered.
Note: A TrueType/OpenType font can be used in two quite different ways: 1) as a component of a data-object
font, or 2) as a secondary resource in a presentation data object such as a PDF or SVG object. This
triplet, and the description of how to set up a data-object font just above, is specific to when the
TrueType/OpenType font is used as a component of a data-object font. For information about TrueType/
OpenType fonts as secondary resources, see “Data Object Resource Equivalence Entries”.
The character shapes provided by the font are scaled to the size specified by the vertical font size and
horizontal scale factor. These values are specified in 1440ths of an inch (20 times the point size) and represent
a scaling of the Em square. When the vertical font size and the horizontal scale factor are identical, a uniform
scaling occurs; when these two parameters are different, an anamorphic scaling occurs. The character rotation
parameter specifies how individual characters are rotated.
The method of accessing characters within the font is specified in the encoding environment and encoding ID
parameters.
A TrueType or OpenType font can contain multiple code-point-to-glyph encodings (in the form of cmap
subtables) to accommodate a variety of encoding schemes; the encoding parameters in this triplet identify
the encoding scheme required for this instance of the font. Only the Microcode-Unicode encoding is
supported in this triplet.
Triplet X'8B'—Data Object Font Descriptor [IPDS-15-342]


Three different methods are provided to identify the scheme used to encode the character data to be printed;
you can select either:
1. A code page [IPDS-15-343]
If a code page is used with this font, the code page must first be activated and its HAID must be specified in
the AR command that activates the data-object font. A code page can be used with any outline font (either
a coded font or a data-object font). If a code page HAID is specified in the AR entry, this method is used
and all Encoding Scheme ID (X'50') triplets are ignored.
2. A Unicode transformation (UTF-8) [IPDS-15-344]
This method is specified by an Encoding Scheme ID (X'50') triplet.
3. Default to the encoding scheme specified in the Encoding Environment and Encoding ID parameters of this [IPDS-15-345]
X'8B' triplet. For TrueType and OpenType fonts, this is UTF-16BE.
If a data-object font is activated via an AR command, but a Data Object Font Descriptor (X'8B') triplet is not
provided, exception ID X'028F ..20' exists. If more than one Data Object Font Descriptor (X'8B') triplet is
specified in the AR command, the first triplet is used and all subsequent Data Object Font Descriptor (X'8B')
triplets are ignored. [IPDS-15-346]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'10' | Length of the triplet, including this length field | X'10' [IPDS-15-347]|
| 1 | CODE | TID | X'8B' | Data Object Font Descriptor triplet | X'8B' [IPDS-15-348]|
| 2 | BITS | Font flags [IPDS-15-349]| | | |
| | bit 0 | MICR | B'0'<br>B'1' | MICR print flag:<br>Normal printing<br>MICR printing | B'0'<br>B'1' [IPDS-15-350]|
| | bit 1 | Location of font | B'0'<br>B'1' | Location of font:<br>Font can be located anywhere in the MO:DCA resource hierarchy<br>Font is located in the resource group for the print file<br>Note: This flag is ignored by IPDS printers. | B'0'<br>B'1' [IPDS-15-351]|
| | bits 2–7 | | B'0000000' | Reserved | B'0000000' [IPDS-15-352]|
| 3 | CODE | Font technology | X'20' | Font technology: TrueType/OpenType | X'20' [IPDS-15-353]|
| 4–5 | UBIN | VFS | X'0001' – X'7FFF' | Specified vertical font size in 1440ths of an inch | X'0001' – X'7FFF' [IPDS-15-354]|
| 6–7 | UBIN | HSF | X'0000'<br>X'0001' – X'7FFF' | No value supplied<br>Horizontal scale factor in 1440ths of an inch | X'0000'<br>X'0001' – X'7FFF' [IPDS-15-355]|
| 8–9 | SBIN | Character rotation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Clockwise rotation of a character pattern (glyph):<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' [IPDS-15-356]|
| 10–11 | CODE | Encoding environment | X'0003' | Encoding environment: Microsoft | X'0003' [IPDS-15-357]|
| 12–13 | CODE | Encoding ID | X'0001' | Environment-specific encoding: Microsoft—Unicode | X'0001' [IPDS-15-358]|
| 14–15 | X'0000' | Reserved | X'0000' [IPDS-15-359]| | |
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as a Data Object Font Descriptor (X'8B') triplet.
Byte 2 Font flags
Bit 0 MICR print
Defines whether normal or Magnetic Ink Character Recognition (MICR) printing is to
be done with the font. If MICR printing is requested, the font needs to be designed for
use in MICR applications. MICR text is normally printed using a toner that is mixed
with a magnetic material.
Exception ID X'02B3..01' exists if a string of text within a WT or WG command was
encountered that was to be printed with a MICR font, but MICR printing is not
available for this text string. Some printers can print MICR text on one side of the
media, but not on the other side; in this case, text data to be printed with a MICR font
that is placed on the non-MICR side of the media will cause this exception to occur.
Bit 1 Location of font
This flag is used by MO:DCA products, but is ignored by IPDS printers.
Bits 2–7
Reserved
Byte 3 Font technology
This parameter identifies the font technology of the font being activated.
Exception ID X'028F ..21' exists if an invalid font technology value is specified or if the
technology specified in this triplet does not match the actual technology of the font.
Bytes 4–5 Specified vertical font size (VFS)
This parameter specifies the desired font size for the vertical direction in 1440ths of an inch.
The specified vertical font size, is the desired distance between adjacent character baselines
when character rotation is zero degrees and no external leading is used. The desired vertical
size of the font is often called point size because formatting programs typically specify this size
in point units (1/72 inch); in this case, the vertical font size can be calculated by multiplying the
desired point size by 20.
If an invalid vertical-font-size value is specified, exception ID X'028F ..22' exists.
Bytes 6–7 Horizontal scale factor (HSF)
This parameter specifies the numerator of a scale factor for the horizontal direction in 1440ths
of an inch. The character shapes and metrics are stretched or compressed in the horizontal
direction by the ratio of HSF/VFS. When the vertical font size and the horizontal scale factor
are identical or when HSF=X'0000' is specified, a uniform scaling occurs; when these two
parameters are different, an anamorphic scaling occurs.
If an invalid horizontal-scale-factor value is specified, exception ID X'028F ..23' exists.
Triplet X'8B'—Data Object Font Descriptor [IPDS-15-360]


Bytes 8–9 Clockwise character rotation in degrees
This parameter specifies a clockwise rotation of a character pattern (glyph) from the character
baseline; refer to the Font Object Content Architecture Reference for a description of
character rotation. The four allowed character rotations provide for different writing modes
(left-to-right, top-to-bottom, right-to-left, and bottom-to-top). A normal (right-side-up) character
has a character rotation of 0°; an upside-down character has a character rotation of 180°. A
character rotation of 270° is normally used for vertical writing.
The valid character rotation values are:
For 0° (left-to-right writing), specify X'0000'
For 90° (bottom-to-top writing), specify X'2D00'
For 180° (right-to-left writing), specify X'5A00'
For 270° (top-to-bottom writing), specify X'8700'
If an invalid character-rotation value is specified, exception ID X'028F ..24' exists.
This field is used along with the current inline and baseline directions to determine the
character orientation with respect to the $X_{p}, Y_{p}$ coordinate system.
The character-rotation parameter applies only to characters used in text or bar code data. For
graphics data, the Set Character Angle drawing order provides analogous function.
Figure 116 shows the relationship between the character rotation value and the PTOCA inline
and baseline direction values.
Figure 116. Character Placement Based on Character Rotation, Inline Direction, and Baseline Direction
Allowable Inline/Baseline Direction Combinations(specified with PTOCA STO control sequence)
Character Rotation
(specified with Data-Object Font Descriptor (X 8B ) triplet)' '
Inline
Direction
Baseline
Direction
90
o
or
270
o
180
o
or
0
o
270
o
or
90
o
0
o
or
180
o
270
o
90
o
0
o
180
o
0
o
90
o
180
o
270
o
The arrows show the inline direction; the baseline (an imaginary line on which the characters appear to
rest) is shown as a lightweight line.
top
top
top
top
t op
top
top
top
top
top
top
top
pot
pot
potpot
TrueType fonts can provide two sets of metrics to allow character placement for different
writing modes. The metrics for horizontal writing are used when the character rotation is 0°,
and a modified version of the horizontal metrics is used for a 180° character rotation. Likewise,
the metrics for vertical writing are used when the character rotation is 270°, and a modified
version of the vertical metrics is used for a 90° character rotation. Refer to for a more
detailed description of these concepts.
Triplet X'8B'—Data Object Font Descriptor [IPDS-15-361]


Bytes 10–11 Encoding environment
A data-object font can be accessed via one of the available encoding schemes, by specifying
a particular encoding environment and specifying a specific environment ID within this
environment. These schemes identify a character encoding that is used to map code points to
glyphs and metrics.
The TrueType/OpenType font technology uses an internal cmap table to map code points to
characters. Most TrueType/OpenType fonts contain a Unicode cmap subtable and some
TrueType/OpenType fonts also contain additional cmap subtables to allow the font to be used
with a variety of character encoding schemes. This triplet's encoding environment parameter
(called platform ID in the TrueType technology) and encoding ID parameter (called platform-
specific encoding ID in the TrueType technology) are used to select the appropriate cmap
subtable.
The following table lists the valid encoding environment values for each font technology:
Font Technology Valid Encoding Environments
TrueType/OpenType (X'20') Microsoft (X'0003')
If an invalid encoding environment value is specified or if a specified encoding environment
value cannot be used with the specified font technology, exception ID X'028F ..25' exists.
Note: The character data that is to be printed can also be specified in a different encoding
than the font encoding. In this case, the printer first converts the data code points into
the encoding used by the font. If the data to be printed is encoded in UTF-8 (as
specified by an Encoding Scheme ID (X'50') triplet), the printer transforms the data from
UTF-8 into Unicode code points. Or, if the font is to be used with a code page (that
maps code points to IBM character IDs (GCGIDs)), the printer uses an internal mapping
table to convert each GCGID to a Unicode code point that is then used to access an
encoding native to the font.
Bytes 12–13 Environment-specific encoding identifier
This parameter specifies the character encoding that is to be used to find character data within
the font. If an invalid environment-specific encoding identifier value is specified, exception ID
X'028F ..26' exists.
The values that are valid for the environment-specific encoding identifier depend on the
specified encoding environment (bytes 10–11). Refer to TrueType or OpenType
documentation for more details about the encoding IDs within these font technologies.
• For the Microsoft environment (bytes 10–11 = X'0003'), the defined encoding identifiers [IPDS-15-362]
include the following:
Encoding ID Meaning
X'0001' Unicode
X'0001' Unicode
This encoding ID is used when the character data is encoded as UTF-16
with Big Endian byte order. The font must include a Format 4 cmap
subtable; this subtable does not support the surrogate area and if this
subtable is used, code points in the range X'D800'–X'DFFF' are treated as
undefined characters. The font might also include a Format 12 cmap
subtable that allows addressing valid UTF-16 code points in the surrogate
area.
If a TrueType/OpenType font or a font within a collection does not contain a
required cmap subtable, exception ID X'020D..01' exists.
Triplet X'8B'—Data Object Font Descriptor [IPDS-15-363]


Bytes 14–15 Reserved
Note: This triplet is identical to the corresponding MO:DCA Data Object Font Descriptor (X'8B') triplet.
IPDS commands that use this triplet:
Triplet X'8B'—Data Object Font Descriptor [IPDS-15-364]


Linked Font (X'8D') Triplet
The Linked Font (X'8D') triplet is used to identify a TrueType/OpenType object to be linked to a base font. The
linked object must either be a TrueType/OpenType font or a TrueType/OpenType Collection (with either a valid
index value or full font name). Property pair X'F204' in the Device-Control command-set vector of an STM reply
indicates support for the Linked Font (X'8D') triplet.
If there are multiple objects to be linked to the base font, one X'8D' triplet is specified for each linked font and
the order of the triplets determines the order of processing. When a glyph is needed, the base font is searched
first and if the glyph is not found there, the font identified by the 1st Linked Font (X'8D') triplet is searched, if not
found there the font identified by the 2nd Linked Font (X'8D') triplet is searched, and so forth. If the glyph is not
found in any of the fonts, exception ID X'0821..00' exists and when an AEA or PCA is taken, a special
character (represented by glyph index 0 for a TrueType or OpenType font) is used from the base font. [IPDS-15-365]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'05'–X'FF' odd values | Length of the triplet, including this length field | X'05'–X'FF' odd values [IPDS-15-366]|
| 1 | CODE | TID | X'8D' | Linked Font triplet | X'8D' [IPDS-15-367]|
| 2–3 | CODE | HAID | X'0001' – X'7EFF' | Host-assigned ID of previously activated linked font object | X'0001' – X'7EFF' [IPDS-15-368]|
| 4 | CODE | Font ID type | X'00'<br>X'01'<br>X'02' | Type of data in font ID field:<br>No font ID provided<br>TTC font index<br>Full font name | X'00'<br>X'01'<br>X'02' [IPDS-15-369]|
| 5–254 | | Font ID | Any value | Identification of a TrueType/OpenType font within a collection | Any value [IPDS-15-370]|
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as a Linked Font (X'8D') triplet.
Bytes 2–3 Host-Assigned ID
This parameter specifies the Host-Assigned ID of a previously activated Data-Object Font
component that can be one of the following:
• TrueType/OpenType font [IPDS-15-371]
• TrueType/OpenType collection [IPDS-15-372]
If the HAID value is invalid, exception ID X'0256..12' exists.
Exception ID X'028F ..31' exists if the Data-Object Font component is not activated when it is
needed, or if the object is activated but is not a TrueType/OpenType object.
Triplet X'8D'—Linked Font [IPDS-15-373]


Byte 4 Font ID type
This parameter identifies the type of data in the font ID field (bytes 5–254) and can be one of
the following:
• X'00'—No font ID provided; this value must be used when this triplet is linking a TrueType/ [IPDS-15-374]
OpenType font.
In this case, the length of the triplet must be X'05'.
• X'01'—TTC font index; this value must be used when this triplet is linking a TrueType/ [IPDS-15-375]
OpenType collection and an index value is used to identify a particular TrueType/OpenType
font within the collection.
In this case, the length of the triplet must be X'07' and the Font ID parameter must contain
an index value between X'0000' and X'FFFF'.
• X'02'—Full font name; this value must be used when this triplet is linking a TrueType/ [IPDS-15-376]
OpenType collection and a full font name is used to identify a particular TrueType/OpenType
font within the collection.
In this case, the length of the triplet must be an odd number of bytes between X'07' and
X'FF'.
If the Font-ID-type value is invalid, exception ID X'0256..13' exists.
If an invalid triplet length is specified for the Font ID type selected, exception ID X'027A..01'
exists.
Bytes 5–254 Font ID
This parameter selects a particular TrueType/OpenType font within a TrueType/OpenType
collection.
• When the Font ID type is X'01', this parameter contains a TTC font index value. The index [IPDS-15-377]
value is a zero-based index into the TableDirectory array of Directory offsets that comprise
the 4th parameter in a TTC header (refer to the OpenType Specification description of
TrueType collections). An index value of X'0000' selects the first font in the directory array,
an index value of X'0001' selects the second font in the directory array, and so forth.
Exception ID X'0256..11' exists if there is no TrueType/OpenType font in the collection for
this index value.
• When the Font ID type is X'02', this parameter contains a full font name. The full font name [IPDS-15-378]
is a character string that is encoded as UTF-16BE (a fixed, two-byte Unicode encoding form
that can contain surrogates and the byte order of each code point is Big Endian). Exception
ID X'0256..14' exists if there is no TrueType/OpenType font in the collection for this full font
name.
IPDS commands that use this triplet:
Triplet X'8D'—Linked Font [IPDS-15-379]


UP3I Finishing Operation (X'8E') Triplet
The UP3I Finishing Operation (X'8E') triplet specifies a specific finishing operation to be applied to either a
sheet or to a collection of sheets, depending on the command containing the triplet:
• If specified on an AFO command, the operation applies to the current sheet and each copy of that sheet. [IPDS-15-380]
• If specified on an XOH-DGB command, the operation applies to a collection of sheets (the sheets within a [IPDS-15-381]
group).
Support for the UP3I Finishing Operation (X'8E') triplet is indicated by property pair X'F101' in the Device-
Control command-set vector of an STM reply.
Some printers support two different finishing triplets (X'85' and X'8E'); the X'8E' triplet is intended for UP3I-
controlled devices and the X'85' triplet is intended for other devices. However, these two triplets can coexist in
the same data stream and wherever an operation (and all parameters) can be specified in either triplet, the two
triplets are interchangeable.
• If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the [IPDS-15-382]
printer converts to the other triplet if necessary.
• If an operation can only be fully specified in one of the triplets, that triplet must be used. [IPDS-15-383]
Multiple finishing operations can be applied by including multiple Finishing Operation triplets (either X'85' or
X'8E'). In this case, the operations are applied in the order received and duplicate identical Finishing Operation
triplets are ignored (the first is used and the duplicates are ignored). Figure 62 shows an example
of how multiple finishing operations can be specified.
Not all combinations of finishing operations are compatible; for example, two Z-fold operations along different
reference edges might not be compatible. Compatible combinations of finishing operations are device specific.
If incompatible finishing operations are specified, exception ID X'027C..01' exists.
For some printers, finishing operations can only be done when the output is routed to specific media
destinations. In this case, when finishing is selected and an incompatible media destination is selected or
defaulted to, exception ID X'027C..09' exists.
This triplet is defined as follows: [IPDS-15-384]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0D'–X'FE' | Length of the triplet, including this length field | X'0D'–X'FE' [IPDS-15-385]|
| 1 | CODE | TID | X'8E' | Identifies the UP3I Finishing Operation triplet | X'8E' [IPDS-15-386]|
| 2 | UBIN | Sequence number | X'00'–X'FF' | Sequence number of this triplet | X'00'–X'FF' [IPDS-15-387]|
| 3 | X'00' | Reserved | X'00' [IPDS-15-388]| | |
| 4 to end | | Data | | Finishing operation data as defined in the UP3I Specification; this field contains bytes 4 to end of the UP3I Form Finishing Operating (X'03') triplet; extra bytes beyond the UP3I-defined bytes are ignored. [IPDS-15-389]| |
Triplet X'8E'—UP³I Finishing Operation [IPDS-15-390]


Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified, exception ID X'027A..01' exists. If the triplet is too long to fit in the containing
command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as a UP3I Finishing Operation (X'8E') triplet
Byte 2 Sequence number
This field contains a sequence number that is used to distinguish otherwise identical triplets.
The sequence number is informational and is not used by the printer.
Byte 3 Reserved
Bytes 4 to end UP
3I finishing operation data
Finishing operation data as defined in the UP3I Specification; this field contains bytes 4 to end
of the UP3I Form Finishing Operating (X'03') triplet; extra bytes beyond the UP3I-defined bytes
are ignored.
For a definition of these data bytes, see the section titled “Extension for the Intelligent Printer
Data Stream (IPDS)” in the current UP3I Specification, that is available at www.afpcinc.org.
Several UP3I-specific exception IDs are defined for specification errors within this triplet and for error
conditions caused by this triplet; a specific UP3I-defined error code is identified in sense bytes 8–9. UP3I-
specific exception IDs include the following:
X'507E..00' Intervention required because of an equipment check on a UP3I-controlled device
X'407E..00' Intervention required on a UP3I-controlled device
X'107E..00' Equipment check on a UP3I-controlled device
X'027E..00' Invalid or unsupported specification for a UP3I-controlled device
X'017E..00' Condition requiring host notification on a UP3I-controlled device
If the selected finishing operation is incompatible with the selected media, or media destination, either
exception ID X'027C..09' or X'027C..0B' exists. If an LCC command that changes the media destination is
received within a group to be finished and the finishing operation cannot be performed, exception ID
X'027C..0A' exists. If the device requires all of the sheets of a group to be the same size, but the collection
contains a mixture of sizes, exception ID X'027C..0C' exists.
If the group contains more or fewer sheets than the printer is capable of finishing, exception ID X'027C..02' or
X'407C..02' exists.
Exception ID X'0109..00' exists when a finishing operation is enabled or disabled.
Note: This triplet is identical to the corresponding MO:DCA UP
3I Finishing Operation (X'8E') triplet.
IPDS commands that use this triplet:
Triplet X'8E'—UP³I Finishing Operation [IPDS-15-391]


Color Management Resource Descriptor (X'91') Triplet
The Color Management Resource Descriptor (X'91') triplet specifies the processing mode for a Color
Management Resource. In particular, it identifies whether the CMR is to be processed as an audit CMR, as an
instruction CMR, or as a link CMR. The Color Management Object Content Architecture Reference defines
which processing modes (audit, instruction, or link) are appropriate for each specific kind of CMR; when the
printer detects an inappropriate processing mode, exception ID X'025E..02' exists. The following table shows
the valid use of processing modes for each type of CMR.
Table 62. CMR Processing Modes
CMR Type
Processing Mode
Non-Generic CMR Generic CMR
Audit Instruction Link Audit Instruction Link
Color Conversion (CC) valid valid invalid,
error
invalid, error
Tone Transfer Curve (TTC) valid valid invalid,
error
valid,
ignored
valid invalid,
error
Halftone (HT) valid,
ignored
valid invalid,
error
valid,
ignored
valid invalid,
error
Indexed (IX) valid,
ignored
valid invalid,
error
invalid, error
Link Color Conversion
subsets “LK” and “DL”
invalid,
error
invalid, error valid invalid, error
Notes:
1. A color-conversion CMR can be defined as a pass-through audit color-conversion CMR by specifying the character [IPDS-15-392]
string “pasthru” in the CMRversion field of the CMR name. If such a color-conversion CMR is referenced as an
instruction CMR, it is ignored. If a pass-through CMR is referenced as a link CMR, or if any other CMR type is
designated as a pass-through CMR, exception ID X'025E..02' exists.
2. Some IPDS printers support instruction tone-transfer-curve CMRs, but ignore audit tone-transfer-curve CMRs. [IPDS-15-393]
When a highlight color space is used with indexed color values (X'0100'–X'FFFF'), the printer uses an indexed
CMR (with an instruction processing mode) for color-conversion purposes (instead of using color-conversion
CMRs); the printer ignores audit indexed CMRs. For all other color spaces, color-conversion CMRs (audit,
instruction, and link) are used for converting specified colors to device colors. Tone-transfer-curve CMRs and
halftone CMRs can be used with all color spaces.
When non-highlight-color presentation data is processed, the printer selects an appropriate set of CMRs using
the CMR-usage hierarchy. Refer to for more information about audit
CMRs, instruction CMRs, and the hierarchy.
Link color-conversion (subset “LK”) CMRs are special resources that combine a specific audit color-conversion
CMR and a specific instruction color-conversion CMR to help improve performance. Each link color-conversion
(subset “LK”) CMR and each ICC DeviceLink (subset “DL”) CMR must be identified as a link CMR; all other
CMR types must not be identified as link CMRs.
This triplet is specified when a CMR is activated with either an AR command or a home-state WOCC
command. If a CMR is captured, this triplet is not saved with the captured resource.
Triplet X'91'—Color Management Resource Descriptor [IPDS-15-394]


Printer support for the X'91' triplet is indicated by presence of the X'F205' property pair in the Device-Control
command-set vector of an STM reply. [IPDS-15-395]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'05' | Length of the triplet, including this length field | X'05' [IPDS-15-396]|
| 1 | CODE | TID | X'91' | Color Management Resource Descriptor triplet | X'91' [IPDS-15-397]|
| 2 | X'00' | Reserved | X'00' [IPDS-15-398]| | |
| 3 | CODE | Mode | X'01'<br>X'02'<br>X'03' | Processing mode:<br>Process as an audit CMR<br>Process as an instruction CMR<br>Process as a link CMR | X'01'<br>X'02'<br>X'03' [IPDS-15-399]|
| 4 | X'01'–X'05' | Reserved | | Ignored [IPDS-15-400]| |
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as a Color Management Resource Descriptor (X'91') triplet.
Byte 2 Reserved
Byte 3 Processing mode
This parameter specifies how a CMR is to be used, as follows:
X'01' Process this CMR as an audit CMR
Audit CMRs reflect the processing that has been done to presentation data. For
example, an audit color-conversion CMR might specify a mapping from the input color
space of digital-camera data to a Profile Connection Space, such as the CIELab color
space. This identifies the input color data.
X'02' Process this CMR as an instruction CMR
Instruction CMRs specify processing that is to be done when presentation data is
printed.
For example, an instruction tone-transfer-curve CMR might specify that a highlight
mid-tones look be applied to the presentation data.
X'03' Process this CMR as a link CMR
Link CMRs can be one of the following:
• Link color-conversion (subset “LK”) CMR that is used to provide for more efficient [IPDS-15-401]
processing
• ICC DeviceLink (subset “DL”) CMR that is used to customize the conversion [IPDS-15-402]
between input and output color spaces
For example, a link color-conversion CMR might specify a mapping directly from a
specific digital-camera's RGB data to a specific printer's CMYK data.
If the processing mode value is invalid, exception ID X'0256..51' exists.
Triplet X'91'—Color Management Resource Descriptor [IPDS-15-403]


Byte 4 Reserved
This field is used by the MO:DCA architecture, but is ignored when this triplet is sent to an
IPDS printer.
Note: This triplet is identical to the corresponding MO:DCA Color Management Resource Descriptor (X'91')
triplet.
IPDS commands that use this triplet:
Triplet X'91'—Color Management Resource Descriptor [IPDS-15-404]


Invoke CMR (X'92') Triplet
The Invoke CMR (X'92') triplet identifies a Color Management Resource to be invoked.
This triplet is used to invoke audit and instruction CMRs. Link CMRs must be activated, but do not need to be
invoked. Invoking a link CMR is not an error, but it performs no function.
When presentation data is processed, the printer selects from the set of activated and invoked CMRs
appropriate audit, instruction, and ICC DeviceLink CMRs using the CMR-usage hierarchy. The instruction
CMR specifies how the presentation data is to be converted into the printer's device-specific color space; the
audit CMR specifies how the data was generated; the ICC DeviceLink CMR combines the input and output
spaces in a customized way. Refer to for more information about audit
CMRs, instruction CMRs, and ICC DeviceLink CMRs, and about the hierarchy.
For pages and overlays, the triplet is specified on the LPD command. For data objects, the triplet is specified
on the RPO, WBCC, WGC, WIC2, WOCC, or WTC command. When a presentation object-container or IOCA
resource is included in a page or overlay, data-object-level CMRs can be invoked by specifying this triplet (one
or more times) on the Include Data Object (IDO) command.
Printer support for the X'92' triplet is indicated by presence of the X'F205' property pair in the Device-Control
command-set vector of an STM reply. [IPDS-15-405]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'04' | Length of the triplet, including this length field | X'04' [IPDS-15-406]|
| 1 | CODE | TID | X'92' | Invoke CMR triplet | X'92' [IPDS-15-407]|
| 2–3 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID of CMR | X'0001' – X'7EFF' [IPDS-15-408]|
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as an Invoke CMR (X'92') triplet.
Bytes 2–3 Host-Assigned ID of CMR
This field specifies the data object resource HAID of a Color Management Resource to be
used with subsequent presentation data. If an invalid value is specified in this field, exception
ID X'0256..61' exists.
The data object resource must be a CMR and must have been previously activated by an AR
command or by a home-state WOCC command. Exception ID X'020D..20' exists if the
resource identified by the HAID is not a CMR or is not currently activated.
Later, when this CMR is selected for use with presentation data, the CMR must still be
activated. Note that when a CMR is deactivated, all invocations of that HAID are also
removed.
IPDS commands that use this triplet:
Triplet X'92'—Invoke CMR
Triplet X'92'—Invoke CMR [IPDS-15-409]


Rendering Intent (X'95') Triplet
The Rendering Intent (X'95') triplet specifies up to four rendering intents to be used when processing
presentation data; there is a rendering intent for IO Image objects, for presentation object containers, for
PTOCA text data, and for GOCA graphics objects. When a link color-conversion (subset “LK”) CMR is used,
the selected rendering intent identifies which lookup table within the link CMR to use. Note that rendering intent
is not used with ICC DeviceLink (subset “DL”) CMRs because this special type of CMR is built for a single
rendering intent.
A different rendering intent can be specified for PTOCA, GOCA, IOCA, and object-container data. The
rendering intent for BCOCA data and object area coloring and for IM-Image data is fixed at media-relative
colorimetric. The rendering intent for object area coloring is always the same as the rendering intent for the
object itself. The PTOCA rendering intent is used for logical page coloring within a page or overlay.
Rendering intent is an International Color Consortium (ICC) concept that defines a gamut-mapping algorithm
to allow some control over out-of-gamut color values and differences in white points. The defined rendering
intents are:
• Perceptual: gamut mapping is vendor-specific, and colors are adjusted to give a pleasing appearance. This [IPDS-15-410]
intent is typically used to render continuous-tone images.
• Media-relative colorimetric: in-gamut colors are rendered accurately, and out-of-gamut colors are mapped to [IPDS-15-411]
the nearest value within the gamut. Colors are rendered with respect to the source white point and are
adjusted for the media white point. Therefore colors printed on two different media with different white points
won't match colorimetrically, but might match visually. This intent is typically used for vector graphics.
• Saturation: gamut mapping is vendor-specific, and colors are adjusted to emphasize saturation. This intent [IPDS-15-412]
results in vivid colors and is typically used for business graphics.
• ICC-absolute colorimetric: in-gamut colors are rendered accurately, and out-of-gamut colors are mapped to [IPDS-15-413]
the nearest value within the gamut. Colors are rendered only with respect to the source white point, and are
not adjusted for the media white point. Therefore colors printed on two different media with different white
points should match colorimetrically, but might not match visually. This intent is typically used for logos.
Link-color-conversion (subset “LK”) CMRs provide four lookup tables, one for each possible rendering intent.
The Rendering Intent (X'95') triplet allows for rendering-intent selection at each level of the hierarchy. While
searching the hierarchy, if a X'95' triplet is found that specifies a rendering intent for the current type of
presentation object, that rendering intent is used to select the appropriate lookup table in the link-color-
conversion CMR (note that the value X'FF' means that a rendering intent has not been specified and that the
hierarchy search should continue). Note that for presentation object containers, an extra level essentially exists
in the hierarchy: if no X'95' triplet is found that specifies the object container rendering intent at the data-object
level of the hierarchy, and if the object contains internal rendering intent information, the internal rendering
intent information is used. If after searching the hierarchy no rendering intent has been specified, the printer
defaults to the rendering intent specified in the instruction color-conversion CMR (that is the same as the
default rendering intent in the selected link-color-conversion CMR). Note that the hierarchy search for
rendering intent and the hierarchy search for CMRs are independent of each other; it is possible that the
rendering intent and the selected CMRs are found on different levels of the hierarchy. Refer to for more information about CMRs, rendering intent, and the hierarchy.
For pages and overlays, the triplet is specified on the LPD command. For data objects, the triplet is specified
on the RPO, WGC, WIC2, WOCC, or WTC command. When a presentation object-container or IOCA resource
is included in a page or overlay, data-object-level rendering intent can be overridden by specifying this triplet
on the Include Data Object (IDO) command.
To establish a rendering intent for multiple pages (such as for a MO:DCA document or print file) a Rendering
Intent (X'95') triplet can be specified on a Set Presentation Environment (SPE) command.
Triplet X'95'—Rendering Intent [IPDS-15-414]


Printer support for the X'95' triplet is indicated by presence of the X'F205' property pair in the Device-Control
command-set vector of an STM reply. [IPDS-15-415]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0A' | Length of the triplet, including this length field | X'0A' [IPDS-15-416]|
| 1 | CODE | TID | X'95' | Rendering Intent triplet | X'95' [IPDS-15-417]|
| 2–3 | X'0000' | Reserved | X'0000' [IPDS-15-418]| | |
| 4 | CODE | IOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for IO-Image objects:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' [IPDS-15-419]|
| 5 | CODE | Object container | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for presentation object containers:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' [IPDS-15-420]|
| 6 | CODE | PTOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for PTOCA text:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' [IPDS-15-421]|
| 7 | CODE | GOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for GOCA graphics objects:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' [IPDS-15-422]|
| 8–9 | X'0000' | Reserved | X'0000' [IPDS-15-423]| | |
Byte 0 Triplet length
This parameter contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This parameter identifies this as a Rendering Intent (X'95') triplet.
Bytes 2–3 Reserved
Byte 4 Desired IOCA rendering intent
This field specifies the desired rendering intent for IO-Image objects or, when X'FF' is
specified, indicates that this triplet does not specify a rendering intent for IO-Image objects.
When this triplet is in a WIC2 command or in an IDO command for an IO-Image object, the
IOCA rendering intent applies to that specific IO-Image object and the other rendering intent
values in this triplet are ignored. Otherwise, the IOCA rendering intent is obtained from
another level of the CMR-usage hierarchy (such as from an LPD or SPE command).
Triplet X'95'—Rendering Intent [IPDS-15-424]


If an invalid value is specified in this field, exception ID X'0256..71' exists.
Byte 5 Desired object-container rendering intent
This field specifies the desired rendering intent for presentation object containers or, when
X'FF' is specified, indicates that this triplet does not specify a rendering intent for presentation
object containers.
When this triplet is in a WOCC command or in an IDO command for an object container, the
object-container rendering intent applies to that specific presentation object and the other
rendering intent values in this triplet are ignored. Otherwise, the object-container rendering
intent is obtained from another level of the CMR-usage hierarchy (such as from internal
rendering intent information or from an LPD or SPE command).
If an invalid value is specified in this field, exception ID X'0256..71' exists.
Byte 6 Desired PTOCA rendering intent
This field specifies the desired rendering intent for PTOCA text data or, when X'FF' is
specified, indicates that this triplet does not specify a rendering intent for PTOCA text data.
When this triplet is in an LPD command, the PTOCA rendering intent applies to all text-major
text data in the pages and overlays that use that LPD command and the other rendering intent
values in this triplet are ignored. Otherwise, the PTOCA rendering intent is obtained from
another level of the CMR-usage hierarchy (such as from an SPE command).
When this triplet is in a WTC command, the PTOCA rendering intent applies to all text data
within the text object and the other rendering intent values in this triplet are ignored.
Otherwise, the PTOCA rendering intent is obtained from another level of the CMR-usage
hierarchy (such as from an SPE command).
If an invalid value is specified in this field, exception ID X'0256..71' exists.
Byte 7 Desired GOCA rendering intent
This field specifies the desired rendering intent for GOCA graphics objects or, when X'FF' is
specified, indicates that this triplet does not specify a rendering intent for GOCA objects.
When this triplet is in a WGC command, the GOCA rendering intent applies to that specific
GOCA object and the other rendering intent values in this triplet are ignored. Otherwise, the
GOCA rendering intent is obtained from another level of the CMR-usage hierarchy (such as
from an LPD or SPE command).
If an invalid value is specified in this field, exception ID X'0256..71' exists.
Bytes 8–9 Reserved
Note: This triplet is identical to the corresponding MO:DCA Rendering Intent (X'95') triplet.
IPDS commands that use this triplet:
Triplet X'95'—Rendering Intent [IPDS-15-425]


CMR Tag Fidelity (X'96') Triplet
The CMR Tag Fidelity (X'96') triplet specifies the exception continuation and reporting rules for Color
Management Resource (CMR) tag exceptions. A CMR tag exception is detected when an unsupported CMR
tag is encountered in a Color Management Resource. The applicable exception ID is X'025D..04'.
Refer to Figure 60 for a description of exception handling when a presentation fidelity control is
being used.
The default fidelity action if a CMR Tag Fidelity (X'96') triplet is not received by the printer, or if the activate flag
in a PFC command is B'0' is to report the error and follow the directions of the currently active XOA-EHC
command. An IML NACK also resets the CMR Tag Fidelity to the default.
Printer support for the CMR Tag Fidelity (X'96') triplet is indicated by the PFC Triplets Supported self-defining
field in the XOH-OPC reply. [IPDS-15-426]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' [IPDS-15-427]|
| 1 | CODE | TID | X'96' | CMR Tag Fidelity triplet | X'96' [IPDS-15-428]|
| 2 | CODE | Continue | X'01'<br>X'02' | Exception continuation rule:<br>Stop on X'025D..04'<br>Continue processing CMR data | X'01'<br>X'02' [IPDS-15-429]|
| 3 | X'00' | Reserved | X'00' [IPDS-15-430]| | |
| 4 | CODE | Report | X'01'<br>X'02' | Exception reporting rule:<br>Report X'025D..04'<br>Do not report X'025D..04' | X'01'<br>X'02' [IPDS-15-431]|
| 5–6 | X'0000' | Reserved | X'0000' [IPDS-15-432]| | |
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself. If an invalid length is
specified or if the triplet is too long to fit in the PFC command, exception ID X'0254..71' exists.
Byte 1 Triplet ID
This field identifies this as a CMR Tag Fidelity (X'96') triplet.
Byte 2 Continuation rule for a CMR tag exception
This field specifies whether or not the printer should continue processing when a CMR tag
exception X'025D..04' is detected. If an invalid continuation-rule value is specified, exception
ID X'0254..72' exists.
The valid values are:
X'01' Stop processing the CMR object at the first X'025D..04' exception.
In this case, the exception must be reported. When this value is specified, the printer
ignores triplet byte 4 and reports the exception. Also, the printer stops processing
further CMR data; refer to Figure 60 for a description of exception
handling for this situation.
X'02' Continue processing CMR data by skipping the unrecognized or unsupported CMR
tag and continuing with the next CMR tag.
Byte 3 Reserved
Triplet X'96'—CMR Tag Fidelity [IPDS-15-433]


Byte 4 Reporting rule for a CMR tag exception
This field specifies whether or not X'025D..04' exceptions are reported by the printer; however,
when the continuation rule (byte 2) is set to X'01' (stop), the exception must be reported. If an
invalid report value is specified, exception ID X'0254..73' exists. The valid values are:
X'01' Report X'025D..04' exceptions.
X'02' Do not report X'025D..04' exceptions.
Bytes 5–6 Reserved
Note: This triplet is identical to the corresponding MO:DCA CMR Tag Fidelity (X'96') triplet.
IPDS commands that use this triplet:
Triplet X'96'—CMR Tag Fidelity [IPDS-15-434]


Device Appearance (X'97') Triplet
The Device Appearance (X'97') triplet selects one of a set of architected appearances for the device to
temporarily assume. The assumed appearance stays in affect until another Set Presentation Environment
(SPE) command is received that changes the appearance or until the printer issues an IML NACK.
The presentation environment default if a Device Appearance (X'97') triplet has not been received by the
printer or if the printer issues an IML NACK is to assume the printer default appearance, that is normally to
assume the full presentation capabilities of the printer.
Note: This triplet can request a device appearance change whenever the printer is in home state (that is,
between pages). For example, when cut-sheet emulation is being used and Device Appearance is
changed at the beginning of a print job, it is likely that this change will be requested while constructing a
sheet. Some appearance changes can be assumed while constructing a sheet and others require a
sheet boundary; if a sheet boundary is required but was requested within a sheet, the appearance
change will occur after the current sheet is completed. An appearance change might also temporarily
degrade performance or cause paper to be wasted. Refer to your printer documentation for details about
the affect of selecting a device appearance.
The X'F206' property pair in the Device-Control command-set vector of an STM reply indicates support for
Device Appearance (X'97') triplets in the SPE command. [IPDS-15-435]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' [IPDS-15-436]|
| 1 | CODE | TID | X'97' | Device Appearance triplet | X'97' [IPDS-15-437]|
| 2 | X'00' | Reserved | X'00' [IPDS-15-438]| | |
| 3–4 | CODE | Appearance | X'0000'<br>X'0001' | Device appearance to assume:<br>Assume device-default appearance<br>Assume device-default monochrome appearance | X'0000' [IPDS-15-439]|
| 5–6 | X'0000' | Reserved | X'0000' [IPDS-15-440]| | |
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as a Device Appearance (X'97') triplet.
Byte 2 Reserved
Triplet X'97'—Device Appearance [IPDS-15-441]


Bytes 3–4 Device appearance to assume
This field specifies the device appearance to assume; defined values are as follows:
X'0000' Assume the device-default appearance.
X'0001' Assume a monochrome appearance such that the printer's default color is
used for printing. The printer can simulate color values with grayscale (using
the default color), or can simulate color values by simply substituting the
default color, or can use some combination of the two. Property pair X'F005'
in the Device-Control command-set vector of an STM reply indicates that the
printer simulates with grayscale.
The device-default appearance (X'0000') must be supported, but support for other
appearances is optional. Support for optional appearances is indicated by the XOH-OPC
Device-Appearance self-defining field.
If an invalid or unsupported appearance value is specified, exception ID X'0256..81' exists.
The Color Fidelity (X'75') triplet can be used to control whether printing stops or continues and
whether or not this exception is reported.
Bytes 5–6 Reserved
Note: This triplet is identical to the corresponding MO:DCA Device Appearance (X'97') triplet.
IPDS commands that use this triplet:
Triplet X'97'—Device Appearance [IPDS-15-442]


Image Resolution (X'9A') Triplet
The Image Resolution (X'9A') triplet specifies the resolution of a raster image. The triplet can be used on an
IDO, RPO, or WOCC command to specify the image resolution for a presentation object (such as TIFF , GIF ,
JPEG, and JPEG2000) for which an image resolution is needed. If a resolution is specified within the
presentation object, this triplet is not needed; but if specified will override the resolution within the presentation
object.
Note: The presentation space size of a raster image, such as an image in TIFF format, is determined by two
parameters: (1) the pixel count in the x and y directions, and (2) the resolution of the pixels in the x and y
directions. The use of these two parameters when presenting an image depends on the mapping option
that is in effect:
• When the mapping option is [IPDS-15-443]
scale to fill, the pixel counts are sufficient since the intent is to scale the
complete raster into the object area.
• When the mapping option is scale to fit, if the resolution in the x and y directions is the same, the pixel [IPDS-15-444]
counts are also sufficient since again the intent is to scale the complete raster into the object area and
thus the physical dimensions of the image are unimportant. However, if the resolution in the x and y
directions are different, then both the pixel counts and the resolutions are needed to define the
physical dimensions of the image that will be scaled.
• When the mapping option is position, position and trim, or center and trim, both the pixel counts and [IPDS-15-445]
the resolutions are needed to define the physical dimensions of the image, since the intent is to render
a portion of the image at its native size into the object area.
Printer support for the X'9A' triplet is indicated by presence of the X'5800' property pair in the STM Object
Container command-set vector. [IPDS-15-446]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0A' | Length of the triplet, including this length field | X'0A' [IPDS-15-447]|
| 1 | CODE | TID | X'9A' | Image Resolution triplet | X'9A' [IPDS-15-448]|
| 2–3 | X'0000' | Reserved | X'0000' [IPDS-15-449]| | |
| 4 | CODE | X unit base | X'00'<br>X'01' | Unit base for image resolution in the X direction:<br>10 inches<br>10 centimeters | X'00' [IPDS-15-450]|
| 5 | CODE | Y unit base | X'00'<br>X'01' | Unit base for image resolution in the Y direction (must be the same as X unit base):<br>10 inches<br>10 centimeters | X'00' [IPDS-15-451]|
| 6–7 | UBIN | XUPUB | X'0001' – X'7FFF' | Number of image points in the X-direction per X unit base | X'0001' – X'7FFF' [IPDS-15-452]|
| 8–9 | UBIN | YUPUB | X'0001' – X'7FFF' | Number of image points in the Y-direction per Y unit base | X'0001' – X'7FFF' [IPDS-15-453]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as an Image Resolution (X'9A') triplet.
Triplet X'9A'—Image Resolution [IPDS-15-454]


Bytes 2–3 Reserved
Byte 4 X unit base
This field specifies the unit base to be used to interpret triplet bytes 6–7. A value of X'00'
indicates that the unit base in the X direction is ten inches. A value of X'01' indicates that the
unit base in the X direction is ten centimeters.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
If an invalid or unsupported value is specified, exception ID X'0256..91' exists.
Byte 5 Y unit base (must be the same as the X unit base)
This field specifies the unit base to be used to interpret triplet bytes 8–9. A value of X'00'
indicates that the unit base in the Y direction is ten inches. A value of X'01' indicates that the
unit base in the Y direction is ten centimeters.
The Y unit base must be identical to the X unit base. If an invalid or unsupported value is
specified or if the value in byte 5 is not identical to the value in byte 4, exception ID X'0256..91'
exists.
Bytes 6–7 X units per unit base
This parameter specifies the number of image points per X unit base in the X direction.
If an invalid value is specified, exception ID X'0256..92' exists.
Bytes 8–9 Y units per unit base
This parameter specifies the number of image points per Y unit base in the Y direction.
If an invalid value is specified, exception ID X'0256..92' exists.
Note: This triplet is identical to the corresponding MO:DCA Image Resolution (X'9A') triplet.
IPDS commands that use this triplet:
Triplet X'9A'—Image Resolution [IPDS-15-455]


Object Container Presentation Space Size (X'9C') Triplet
The Object Container Presentation Space Size (X'9C') triplet specifies the presentation space size for an
object container. An Object Container Presentation Space Size (X'9C') triplet can be specified in the WOCC
command for a specific object and can be overridden by a corresponding triplet specified in an IDO command
that includes that object. In addition, when an object is preRIPped with the RPO command, the presentation
space size to be used on a later IDO command should be specified on the RPO command.
Printer support for the PDF Presentation space size field (byte 4) is indicated by presence of the X'1203'
property pair in the STM Object Container command-set vector. Printer support for bytes 5–16 for SVG objects
is indicated by presence of the X'1209' property pair in the STM Object Container command-set vector. When
sending this triplet for a PDF object, the 5-byte version of this triplet must be sent to a printer that does not
return the X'1209' property pair. [IPDS-15-456]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'05', X'11' | Length of the triplet, including this length field | X'05' [IPDS-15-457]|
| 1 | CODE | TID | X'9C' | Object Container Presentation Space Size triplet | X'9C' [IPDS-15-458]|
| 2–3 | X'0000' | Reserved | X'0000' [IPDS-15-459]| | |
| 4 | CODE | PDF Presentation space size | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05' | Choice of presentation-space size:<br>Use the MediaBox (default)<br>Use the CropBox<br>Use the BleedBox<br>Use the TrimBox<br>Use the ArtBox | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05' [IPDS-15-460]|
| 5 | CODE | X unit base | X'00'<br>X'01' | Unit base for presentation-space size in the X direction:<br>10 inches<br>10 centimeters | X'00' [IPDS-15-461]|
| 6 | CODE | Y unit base | X'00'<br>X'01' | Unit base for presentation-space size in the Y direction (must be the same as the X unit base):<br>10 inches<br>10 centimeters | X'00' [IPDS-15-462]|
| 7–8 | UBIN | XUPUB | X'0001' – X'7FFF' | $X_{oc}$ units per unit base | X'0001' – X'7FFF' [IPDS-15-463]|
| 9–10 | UBIN | YUPUB | X'0001' – X'7FFF' | $Y_{oc}$ units per unit base | X'0001' – X'7FFF' [IPDS-15-464]|
| 11–13 | UBIN | $X_{oc}$ extent | X'000001' – X'007FFF' | $X_{oc}$ extent of the presentation-space size<br>(Refer to the note following the table.) | X'000001' – X'007FFF' [IPDS-15-465]|
| 14–16 | UBIN | $Y_{oc}$ extent | X'000001' – X'007FFF' | $Y_{oc}$ extent of the presentation-space size<br>(Refer to the note following the table.) | X'000001' – X'007FFF' [IPDS-15-466]|
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
Triplet X'9C'—Object Container Presentation Space Size [IPDS-15-467]


supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as an Object Container Presentation Space Size (X'9C') triplet.
Bytes 2–3 Reserved
Byte 4 PDF presentation space size
This field selects the presentation space size for the following PDF object types:
• Portable Document Format (PDF) single page [IPDS-15-468]
• Portable Document Format (PDF) single page with transparency [IPDS-15-469]
• PDF Multiple Page File [IPDS-15-470]
• PDF Multiple Page - with Transparency - File [IPDS-15-471]
It is ignored for all other object types.
Notes:
1. In addition to specifying the presentation space size, this parameter also indicates the [IPDS-15-472]
PDF box that the PDF should be rendered to.
2. As specified in the PDF specification, if the CropBox, BleedBox, TrimBox, or ArtBox [IPDS-15-473]
parameters extend beyond the boundaries of the MediaBox, their definition is redefined to
be their intersection with the MediaBox. Thus the presentation space size is reduced to the
size of the intersection, and the reduced box is what is rendered to.
Refer to the “PDF Reference” from Adobe Systems Incorporated for a description of the
various box-size parameters.
The default if no Object Container Presentation Space Size (X'9C') triplet is specified is to use
the MediaBox size as the PDF-object presentation-space size. If a valid value is specified in
the triplet but the PDF object does not contain an entry for that box-size value, the PDF default
hierarchy is used to determine the presentation space used to present the object. If an invalid
PDF-presentation-space-size value is specified, exception ID X'0256..B1' exists.
It is strongly recommended that if this triplet is being used for a PDF object, bytes 5–16 are
omitted. Otherwise, exception X'027A..01' might be reported by older implementations that do
not support bytes 5–16.
Bytes 5–16 Presentation space size
These fields select the presentation space size for the following object types:
• AFPC SVG Subset [IPDS-15-474]
They are ignored for all other object types.
The default if no Object Container Presentation Space Size (X'9C') triplet is specified is to use
the SVG viewport within the SVG object, when it specifies absolute width and height values. If
such a viewport is not specified, the default presentation space size is defined to be the size of
the including page or overlay.
Byte 5 Unit base in the X direction
This field specifies the unit base to be used to interpret bytes 7–8 and bytes
11–13. A value of X'00' indicates that the unit base is ten inches. A value of
X'01' indicates that the unit base is ten centimeters.
Triplet X'9C'—Object Container Presentation Space Size [IPDS-15-475]


If an invalid or unsupported value is specified, exception ID X'0256..B2'
exists.
Byte 6 Unit base in the Y direction
This field specifies the unit base to be used to interpret bytes 9–10 and bytes
14–16. A value of X'00' indicates that the unit base is ten inches. A value of
X'01' indicates that the unit base is ten centimeters.
If an invalid or unsupported value is specified, or if this field does not have the
same value as the X unit base field, exception ID X'0256..B2' exists.
Byte 7–8 X
oc units per unit base
This field specifies the number of units per unit base to be used to interpret
bytes 11–13. For example, if the unit base is X'00' and this value is X'3840',
there are 14,400 units per ten inches (1440 units per inch).
If an invalid value is specified, exception ID X'0256..B3' exists.
Byte 9–10 Yoc units per unit base
This field specifies the number of units per unit base to be used to interpret
bytes 14–16.
If an invalid value is specified, exception ID X'0256..B3' exists.
Byte 11–13 Xoc extent of the presentation-space size
This value is in terms of the units specified in bytes 7–8.
If an invalid or unsupported value is specified, exception ID X'0256..B4'
exists.
Byte 14–16 Y
oc extent of the presentation-space size
This value is in terms of the units specified in bytes 9–10.
If an invalid or unsupported value is specified, exception ID X'0256..B4'
exists.
Note: This triplet is identical to the corresponding MO:DCA Object Container Presentation Space Size (X'9C')
triplet.
IPDS commands that use this triplet:
Triplet X'9C'—Object Container Presentation Space Size [IPDS-15-476]


Setup Name (X'9E') Triplet
The Setup Name triplet specifies a setup name that encompasses some number of settings on a device. The
name is created by a user of the device and must be coordinated with the presentation systems that use the
device; the specific implementation of setup names might differ between devices.
Printer support for the X'9E' triplet is indicated by presence of the X'700A' property pair in the Device-Control
command-set vector of an STM reply. [IPDS-15-477]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'06'–X'CC' even values | Length of the triplet, including this length field | X'06'–X'CC' even values [IPDS-15-478]|
| 1 | CODE | TID | X'9E' | Setup Name triplet | X'9E' [IPDS-15-479]|
| 2–3 | X'0000' | Reserved | X'0000' [IPDS-15-480]| | |
| 4 to end | CHAR | Setup name | any UTF-16BE character | Setup name specified as a UTF-16BE character string | any UTF-16BE character [IPDS-15-481]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as a Setup Name (X'9E') triplet.
Bytes 2–3 Reserved
Bytes 4 to end Setup name
This field contains a setup name specified as a UTF-16BE character string.
The name is not syntax checked for proper UTF-16 data; however, an ill-formed name is
unlikely to match any device-supported setup name.
Note: This triplet is identical to the corresponding MO:DCA Setup Name (X'9E') triplet.
IPDS commands that use this triplet:
Triplet X'9E'—Setup Name [IPDS-15-482]


Invoke Tertiary Resource (X'A2') Triplet
The Invoke Tertiary Resource (X'A2') triplet identifies a resource to be invoked as a tertiary resource—that is,
as an invocation for use by a secondary resource.
Not all tertiary resources are invoked using this triplet. In fact, most tertiary resources are invoked using the
same mechanism as that for secondary resources, using the DORE or DORE2 command to map the internal
name of the tertiary resource to the HAID of the tertiary resource. For example, when a tertiary IOCA Tile
Resource is being used by a secondary IOCA image resource, the 4-byte local ID used by the secondary IOCA
image resource to request the tertiary IOCA Tile Resource is mapped using the DORE or DORE2
command to
the HAID of the IOCA Tile Resource. However, not all tertiary resource invocations can be done using the
DORE or DORE2
command—CMRs, for instance, cannot be invoked through the DORE or DORE2 command,
so invocation of tertiary CMRs is done using this triplet. [IPDS-15-483]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0A'–X'FF' | Length of the triplet, including this length field | X'0A'–X'FF' [IPDS-15-484]|
| 1 | CODE | TID | X'A2' | Invoke Tertiary Resource triplet | X'A2' [IPDS-15-485]|
| 2 | CODE | TRType | X'01' | Tertiary resource type:<br>CMR | X'01' [IPDS-15-486]|
| 3–4 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID of tertiary resource | X'0001' – X'7EFF' [IPDS-15-487]|
| 5–8 | | X'00000000' | Reserved | | X'00000000' [IPDS-15-488]|
| 9 | CODE | IDType | X'01' | Internal resource ID type:<br>Image local ID | X'01' [IPDS-15-489]|
| 10 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource for which the tertiary resource is being invoked | Any value [IPDS-15-490]|
Byte 0 Triplet length
This field contains the length of this triplet, including the length field itself.
If an invalid triplet length is specified, exception ID X'027A..01' exists. If the triplet is too big to
fit in the containing command, exception ID X'027B..01' exists.
Byte 1 Triplet ID
This field identifies this as an Invoke Tertiary Resource (X'A2') triplet.
Byte 2 Tertiary resource type
This field specifies the type of tertiary resource being invoked. If an invalid or unsupported
value is specified in this field, exception ID X'0256..C1' exists.
X'01' CMR
The tertiary resource is the HAID of a CMR to be invoked as a tertiary CMR.
This triplet is used to invoke audit and instruction CMRs. Link CMRs must be
activated, but do not need to be invoked. Invoking a link CMR is not an error, but it
performs no function.
When presentation data is processed, the printer selects from the set of activated and
invoked CMRs appropriate audit, instruction, and ICC DeviceLink CMRs using the
CMR-usage hierarchy. The instruction CMR specifies how the presentation data is to
be converted into the printer's device-specific color space; the audit CMR specifies
how the data was generated; the ICC DeviceLink CMR combines the input and output
Triplet X'A2'—Invoke Tertiary Resource [IPDS-15-491]


spaces in a customized way. Refer to for more
information about audit CMRs, instruction CMRs, and ICC DeviceLink CMRs, and
about the hierarchy.
Bytes 3–4 Host-Assigned ID of tertiary resource
This field specifies the data object resource HAID of a tertiary resource to be invoked. If an
invalid value is specified in this field, exception ID X'0256..C2' exists.
Exception ID X'0256..C3' exists if the resource identified by the HAID is not activated, or if the
object is activated but is not of the type named in the TRType field.
Bytes 5–8 Reserved
Byte 9 Internal resource ID type
This field specifies the type of internal resource ID being used. If an invalid or unsupported
value is specified in this field, exception ID X'0256..C4' exists.
X'01' Image local ID
The internal resource ID is a two-byte image local ID.
When used with TRType=X'01' on a WBCC command:
This triplet is invoking a tertiary CMR for a secondary resource image object to be
printed in conjunction with a QR Code with Image bar code. The triplet is
specified on the WBCC command for the QR Code with Image bar code.
The images printed in conjunction with a QR Code with Image bar code are
secondary resources to that bar code object, and some types of such images can
also use CMRs as secondary resources; a CMR used in such a way is termed a
tertiary CMR and must be invoked using this triplet.
The internal resource ID is a two-byte image local ID specified in the special-
function parameters within the BSA for the QR Code with Image bar code object.
The image local ID must be mapped using the DORE or DORE2
command to the
HAID of one of the presentation data object resources listed in Table 51 that can use CMRs as secondary resources, or exception ID X'0256..C6'
exists.
The CMR identified by the HAID in bytes 3–4 is invoked for use at the data-
object-level by the image corresponding to the image local ID in this field.
Printer support for the X'A2' triplet for this purpose is indicated by the presence of
the X'F212' property pair in the Device-Control command-set vector of an STM
reply.
Bytes 10 to
end
Internal resource ID
This field specifies the internal resource ID of the secondary resource for which the tertiary
resource is being invoked. The tertiary resource identified by the HAID in bytes 3–4 is invoked
for use by the secondary resource corresponding to the internal resource ID in this field.
If the length of this field does not match the length defined by the IDType field, exception ID
X'0256..C5' exists.
This internal resource ID must be mapped using the DORE or DORE2
command to the HAID
of a data object resource. If no data-object-resource-equivalence entry is in effect mapping
this internal resource id, or if the object mapped by the HAID has not been activated,
exception ID X'020D..10' exists. If the object mapped by the HAID is not a valid type based on
the context defined by the TRType and IDType values, exception ID X'0256..C6' exists.
IPDS commands that use this triplet:
Triplet X'A2'—Invoke Tertiary Resource [IPDS-15-492]


