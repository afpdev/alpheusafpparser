# Appendix B. Resource Access Table (RAT)
Font Interchange Information
This appendix formerly contained information on acceptable values that may be used in the Map Coded Font
(MCF) structured field to identify a particular Font Object Content Architecture (FOCA) font. It is no longer
practical to maintain this material in an appendix. For detailed information on the FOCA fonts that may be
referenced with a MCF structured field in a MO:DCA data stream, please see the font publications listed in
“Related Publications”.
Note: The referenced documents use the term character set as a short form of the qualified term font character
set. The latter form is used throughout this book. In this context, the two forms are equivalent.
The Resource Access Table (RAT)
The Resource Access T able (RAT) is used to map a resource name specified in the MO:DCA data stream to
information used to find and process the resource on a given system. The following resources can be
processed via a RAT :
• TrueType fonts (TTFs) and OpenType fonts (OTFs); the resource name is a full font name [MODCA-B-001]
• Color Management Resources (CMRs); the resource name is a CMR name [MODCA-B-002]
• Data objects; the resource name is the object name [MODCA-B-003]
Resource Access Table in MO:DCA Environments
The Resource Access T able (RAT) is installed on a given system by an application program. It is updated
whenever new resources that need to be accessed through a RAT are installed on that system, or whenever
such resources are updated, such as when a new version of a resource replaces an existing version. The
installed RAT remains active until it is updated or replaced. If no RAT is active, resources which require a RAT
to be accessed cannot be processed.
The RAT resides in the directory that it represents. There can be multiple RAT s in a system, one for each
directory. The file names in the RAT do not contain path information.
Implementation Notes:
1. In AFP systems, the file name for the various RAT s is hard-coded, as follows: [MODCA-B-004]
• TrueType/OpenType Font RAT : IBM_DataObjectFont.rat [MODCA-B-005]
• Color Management Resource RAT : AFP_ColorManagementResource.rat [MODCA-B-006]
• Data Object RAT : AFP_DataObjectResource.rat [MODCA-B-007]
2. Data objects may be installed in AFP resource libraries with or without a Data Object RAT . Print servers [MODCA-B-008]
should maintain the functionality of legacy applications that reference data objects that were not installed
with a RAT . However, if a library does contain a Data Object RAT , the RAT should be searched first to
ensure that the RAT information is used for any object in the library that was installed with the RAT .
Resource Access Table in IPDS Environments
The Resource Access T able is not used at the IPDS level. [MODCA-B-009]


Resource Access Table Definition
The table definition consists of a table header followed by zero or more variable-length repeating groups. The
table header specifies information that applies to the whole table including an identifier for the table, the length
of the table, and a table creation/update time stamp. A repeating group consists of a header followed by zero
or more variable-length table vectors. Each repeating group specifies the information needed to access and
process a specific resource. The repeating group content is defined by the resource object type, which is
identified by the resource encoded object-type OID. Repeating groups for a specific resource object type, such
as repeating groups for TTFs or OTFs, have the same syntax. Only a single repeating group is allowed for a
specific resource object. That is, a single resource object may only be defined and indexed once in the RAT .
Repeating groups must be compact. This means that for a table vector that can be repeated, all occurrences of
the vector must specify valid content, that is, the vectors cannot be empty unless there is only one occurrence
of that vector.
Resource Access Table [MODCA-B-010]


Resource Access Table Syntax [MODCA-B-011]
| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-012]|
| --- | --- | --- | --- | --- | --- Resource Access Table Header [MODCA-B-013]|
| 0–3 | UBIN | Tlength | | 18–4,294,967,295 T able length | M [MODCA-B-014]|
| 4–5 | CODE | TBLid | | 1–65,534 T able ID | M [MODCA-B-015]|
| 6 CODE TBLtpe X'02', X'03', X'04' T able type: | | | | | X'02' TTF/OTF Resource Access T able X'03' CMR Resource Access T able X'04' Data Object Resource Access T able [MODCA-B-016]|
| 7–16 | CODE | UTStmp | | Universal Date and Time Stamp | M [MODCA-B-017]|
| 17 CODE InstInf X'00', X'01' Installer information: | | | | | X'00' Installer information not specified; this parameter ends the table header X'01' Installer information specified in bytes 18–57 [MODCA-B-018]|
| 40 bytes of Installer information that are only specified if InstInf = X'01'; these bytes are optional as a unit. [MODCA-B-019]| | | | | |
| 18–49 | CHAR | InstNme | | Name of Installer application | O [MODCA-B-020]|
| 50 UBIN InstVrs Version number of Installer | | | | | application O [MODCA-B-021]|
| 51 | UBIN | InstRel | | Release level of Installer application | O [MODCA-B-022]|
| 52 UBIN InstMod Modification level of Installer | | | | | application O [MODCA-B-023]|
| 53 | UBIN | InstSrv | | Service level of Installer application | O [MODCA-B-024]|
| 54–57 | Reserved; | should | | be zero | O Zero or more variable-length repeating groups [MODCA-B-025]|
| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-026]|
| --- | --- | --- | --- | --- | --- Repeating Group Structure [MODCA-B-027]|
| 0–1 | UBIN | RGLngth | | 22–65,535 Repeating group length | M [MODCA-B-028]|
| 2 | Reserved; | should | | be zero | M [MODCA-B-029]|
| 3 CODE RGTpe X'10' Repeating group type: | | | | | X'10' Resource access table repeating group All others Reserved [MODCA-B-030]|
| 4–5 BITS RGFlgs Repeating group flags; semantics | | | | | defined by resource object-type [MODCA-B-031]|
| 6–21 CODE ObjTpe Encoded object-type OID for | | | | | resource being accessed Zero or more variable-length table vectors in fixed order. The table vector semantics and their order in the repeating group are defined by the resource object type Resource Access Table [MODCA-B-032]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-033]|
| --- | --- | --- | --- | --- | --- Table Vector Structure [MODCA-B-034]|
| 0 UBIN TVLngth 2–252 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-035]|
| 1 | CODE | TVid | | T able vector identifier | M [MODCA-B-036]|
| 2–251 | TVData | T | | able vector data | O Resource Access Table Semantics TBLlngth Contains the length of the table, including this length field, in bytes. TBLid Contains the identifier for the table. TBLtpe Is a code that defines the type of table. Value Description X'02' TrueType/OpenType Font (TTF/OTF) Resource Access T able. The table specifies information needed to access and process a TTF/OTF resource. X'03' Color Management Resource (CMR) Resource Access T able. The table specifies information needed to access and process a CMR. X'04' Data Object (DO) Resource Access T able. The table specifies information needed to access and process a data object that is referenced in the data stream as a resource object. UTStmp Contains the time stamp that specifies when the table was created or when it was last updated. The time stamp is specified with 10 bytes using the syntax specified in bytes 3-12 of the Universal Date and Time Stamp (X'72') triplet, see “Universal Date and Time Stamp Triplet X'72'”. InstInf Is a code that defines whether the table header contains information about the Installer application that generated this RAT . Value Description X'00' No additional Installer information is specified. This parameter terminates the table header. No additional RAT header bytes are allowed and will cause a RAT processing error if specified. X'01' 40 additional bytes of Installer information are specified in bytes 18 - 57 of the RAT header. InstNme Is a character string that identifies the Installer application, encoded in UTF-16BE. The name is left-justified and padded with blanks (space character = X'0020'). Architecture Note: The InfoPrint Font Installer Application is identified as “IBM FI”. The InfoPrint Resource Installer Application is identified as “IBM RI”. InstVrs Version number of the Installer application. For example, version 1 is identified with InstVrs = X'01'. InstRel Release level of the Installer application. For example, release level 2 is identified with InstRel = X'02'. InstMod Modification level of the Installer application. For example, modification level 3 is identified with InstMod = X'03'. InstSrv Service level of the Installer application. For example, service level 4 is identified with InstSrv = X'04'. Resource Access Table [MODCA-B-037]|


RGlngth Contains the length of the repeating group, including this length field, in bytes.
RGtpe Is a code that defines the type of repeating group.
Value Description
X'10' Resource Access T able repeating group. The repeating group specifies
information needed to access and process a resource.
RGFlgs Specifies processing flags for the resource. The flag semantics are defined by the resource
object type.
ObjTpe Specifies the encoded object-type OID for the resource that is accessed and processed with
this repeating group. The encoded object-type OID for resource objects supported in MO:DCA
environments is registered in “Object Type Identifiers”. The OID is left-justified
and padded with zeros. For example, the encoded object-type OID for TrueType font objects is
X'06072B120004010133'. This OID is specified in the ObjTpe parameter as
X'06072B12000401013300000000000000'. The encoded object-type OID for CMRs is
X'06072B120004010139'. This OID is specified in the ObjTpe parameter as
X'06072B12000401013900000000000000'. The encoded object-type OIDs for data objects
installed using the Data Object Resource Access T able are summarized in T able 44.
Resource Access Table Exception Condition Summary
An exception condition exists when the following is detected:
• The RAT header does not specify a valid TBLtpe parameter value [MODCA-B-038]
• A RAT repeating group header does not specify RGTpe = X'10' [MODCA-B-039]
• The ObjTpe parameter does not specify a supported encoded object-type OID [MODCA-B-040]
• The table contains invalid data [MODCA-B-041]
Repeating Group Definition for TrueType and OpenType Font Resources
TrueType and OpenType font resources are identified by encoded object-type OID = X'06072B120004010133'.
They are referenced in the MO:DCA data stream using Map Data Resource (MDR) structured fields. They can
also be referenced from a Begin Resource (BRS) structured field. The reference specifies a full font name that
is also specified by the font manufacturer in the font naming table. The full font name in the font may be
specified in multiple languages; the supported encoding is UTF-16. The full font name from the font reference
is used to index the RAT repeating groups, which specify the full font name of a TrueType/OpenType font in all
supported languages using the UTF-16 encoding. Within a repeating group the full font names in all languages
must be sorted so that the UTF-16 code point sequences for the names are in ascending numerical order. The
repeating groups are then sorted so that the UTF-16 code point sequences for the first full font names in each
repeating group are in ascending order. Note that in MO:DCA environments, all UTF-16 data is considered to
be in big-endian format (UTF-16BE).
Resource Access Table [MODCA-B-042]


Repeating Group Flag Definitions for TrueType and OpenType Font
Resources
Following are the flag definitions for TrueType and OpenType font resources.
RGFlgs Provide additional information for accessing and processing the TrueType/OpenType font
resource. RGFlgs bits have the following descriptions:
Bit Description
0 TrueType Collection (TTC) [MODCA-B-043]
B'0' The font is not packaged in a TTC. If this bit is set to B'0', the TTF/TTC File
Name vector (TVid = X'04') references a TrueType/OpenType font (TTF/
OTF), and the TTF/TTC Object OID vector (TVid = X'08'), if not empty,
specifies an object OID for the font. The TTC Font Index vector (TVid = X'1A')
should be empty and is ignored.
B'1' The font is packaged in a TTC. If this bit is set to B'1', the TTF/TTC File Name
vector (TVid = X'04') references a TrueType Collection (TTC), and the TTF/
TTC Object OID vector (TVid = X'08'), if not empty, specifies an object OID for
the collection. The TTC Font Index vector (TVid = X'1A') must specify a valid
index, and the collection must contain and index a version of the referenced
font that is logically equivalent to the font.
1 Linked Fonts [MODCA-B-044]
B'0' The font does not have any linked fonts. If this bit is set to B'0', the Linked
TTF/OTF Full Font Name vector (TVid = X'24') should be empty and is
ignored.
B'1' The font has linked fonts. A linked font is a complete TTF/OTF that is
processed as a logical extension of the base font. If this bit is set to B'1', the
Linked TTF/OTF Full Font Name vector (TVid = X'24') and any additional
Linked TTF/OTF Full Font Name vectors must specify valid full font names for
TTFs/OTFs. Note that linked fonts can be packaged in a TTC. Note also that
only one level of linking is supported. That is, if a linked font specifies its own
linked fonts, these “secondary” linked fonts are not processed as linked fonts
for the original base font.
2 Private [MODCA-B-045]
B'0' The installer considers this font or the TTC that contains this font to be a
public resource. A public resource is a candidate for resource capture by a
printer. A public resource may also be resident in the printer, and this version
can be used if the object OID matches the object OID associated with the
resource reference.
B'1' The installer considers this font or the TTC that contains this font to be a
private resource. A private resource is not a candidate for resource capture
by printers. A private resource is always downloaded to the printer; if an
object OID has been generated for the resource, it is ignored.
3 Embed
B'0' The installer does not allow this font or the TTC that contains this font to be
embedded inline into a print file level resource group.
B'1' The installer allows this font or the TTC that contains this font to be
embedded inline into a print file level resource group.
4 Capture [MODCA-B-046]
B'0' The installer does not allow this font or the TTC that contains this font to be
captured.
B'1' The installer allows this font or the TTC that contains this font to be captured.
A number of requirements must be met before the presentation system will
actually let resource capture take place:
Resource Access Table [MODCA-B-047]


• The font or collection must be identified as “public” (RGFlgs bit 2 set to B'0') [MODCA-B-048]
by the installer
• The font or collection must have an object OID associated with it [MODCA-B-049]
• The font or collection must be in a location that the presentation system [MODCA-B-050]
considers secure
5–15 Reserved; all bits must be B'0'.
Architecture Note:
1. The setting of RGFlgs bits 2-4 reflect not only the intent of the person running the install process, but also [MODCA-B-051]
the processing of the font permission bits (fsType parameter in the OS/2 T able of the TTF file) by the install
program. For example, if RGFlgs bit 2 = B'0' (font is public), this means (i) the intent of the person running
the install process is to install the font as a public font, and (ii) the font permission bits allow the font to be
treated as a public font.
2. If the RAT repeating group maps a full font name to the file name of a collection, the installer needs to [MODCA-B-052]
ensure that RGFlgs bits 2-4 apply to all fonts in the collection. For example, if RGFlgs bit 4 = B'1' (capture
allowed), then this needs to reflect all fonts in the collection, since the complete collection may end up
being captured.
Table Vector Definitions for TrueType and OpenType Font Resources
Following are the table vectors defined for TrueType and OpenType font resources. The table vectors must
appear in the order shown. Unless indicated otherwise, each table vector must occur once, regardless of
whether its data parameter is specified or not. If a table vector contains no data, its length must be set to X'02'
to indicate that the table vector data is not specified. This is also referred to as an empty table vector. T able
vectors within a RAT repeating group must be compact. This means that for a table vector that can be
repeated, all occurrences of the vector must specify valid content, that is, the vectors cannot be empty unless
there is only one occurrence of that vector. [MODCA-B-053]
| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-054]|
| --- | --- | --- | --- | --- | --- TrueType/OpenType Font (TTF/OTF) Full Font Name; table vector may be repeated to specify the full font name in all supported languages [MODCA-B-055]|
| 0 UBIN TVLngth 4–252; even values | | | | | only T able vector length M [MODCA-B-056]|
| 1 | CODE | TVid | | X'01' T able vector identifier | M [MODCA-B-057]|
| 2–251 CHAR FFName Full font name of the base font. This | | | | | parameter must be specified. TrueType/OpenType Font or TrueType/OpenType Collection (TTC) File Name; table vector must be specified only once [MODCA-B-058]|
| 0 UBIN TVLngth 4–252; even values | | | | | only T able vector length M [MODCA-B-059]|
| 1 | CODE | TVid | | X'04' T able vector identifier | M Resource Access Table [MODCA-B-060]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-061]|
| --- | --- | --- | --- | --- | --- [MODCA-B-062]|
| 2–251 CHAR FileNme File name with which the font or the | | | | | collection that contains the font has been stored in the presentation system's resource library. RGFlgs bit [MODCA-B-063]|
| 0 = B'0' indicates that the file name | | | | | references a TrueType/OpenType font (TTF/OTF). RGFlgs bit 0 = B'1' indicates that the file name references a TrueType Collection (TTC). The file name does not include path information. This parameter must be specified. TrueType/OpenType Font or TrueType/OpenType Collection Object OID; table vector must be specified only once [MODCA-B-064]|
| 0 UBIN TVLngth 2–131 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-065]|
| 1 | CODE | TVid | | X'08' T able vector identifier | M [MODCA-B-066]|
| 2–130 CODE ObjOID The object OID that is assigned to | | | | | the font or to the collection that contains the font. RGFlgs bit 0 = B'0' indicates that the object OID is associated with a TrueType/ OpenType font (TTF/OTF). RGFlgs bit 0 = B'1' indicates that the object OID is associated with a TrueType Collection (TTC). The length of this parameter must reflect the length of the actual OID; padding bytes are not allowed. The object OID enables the font or the collection to be captured and made resident in the printer. O TrueType/OpenType Collection Font Index; table vector must be specified only once [MODCA-B-067]|
| 0 UBIN TVLngth 2, 4 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-068]|
| 1 | CODE | TVid | | X'1A' T able vector identifier | M [MODCA-B-069]|
| 2–3 UBIN FntIndx The index used to locate the TTF/ | | | | | OTF in the TTC. This is an index into the array of offsets that comprise the [MODCA-B-070]|
| 4th parameter in the TTC Header. | | | | | Each offset points to the directory of a specific TTF/OTF in the TTC. An index value of X'0000' selects the first offset, a value of X'0001' selects the second offset, a value of (n-1) selects the nth offset. This index must be specified if RGFlgs bit 0 = B'1'. This vector should be empty and is ignored if RGFlgs bit 0 = B'0'. O Linked TrueType/OpenType Font Full Font Name; table vector must be specified at least once and may be repeated to specify multiple linked fonts Resource Access Table [MODCA-B-071]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-072]|
| --- | --- | --- | --- | --- | --- [MODCA-B-073]|
| 0 UBIN TVLngth 2–252; even values | | | | | only T able vector length; a length of 2 indicates the table vector data is not specified [MODCA-B-074]|
| 1 | CODE | TVid | | X'24' T able vector identifier | M [MODCA-B-075]|
| 2–251 CHAR LFFName Full font name of the linked font. This | | | | | parameter must be specified if RGFlgs bit 1 = B'1'. O Language Code Information for Full Font Names; table vector is optional and may be specified once [MODCA-B-076]|
| 0 UBIN TVLngth 2–252; even values | | | | | only T able vector length; a length of 2 indicates the table vector data is not specified [MODCA-B-077]|
| 1 | CODE | TVid | | X'30' T able vector identifier | M [MODCA-B-078]|
| 2–251 CODE LCIDs An ordered sequence of two-byte | | | | | Language Code IDs (LCIDs) that correspond in one-to-one fashion to the ordered sequence of full font name table vectors (TV ID = X'01') in this repeating group. OTable Notes: [MODCA-B-079]|
| 1. All character data in the table vectors is encoded in UTF-16BE. This encoding is characterized by the | | | | | following parameters: Encoding scheme ID - as carried in the Encoding Scheme ID (X'50') triplet: X'7200' CCSID - as carried in the Coded Graphic Character Set Global Identifier (X'01') triplet (CCSID form): [MODCA-B-080]|
| 1200 (X'04B0'). | | | | | Note that in MO:DCA environments, all UTF-16 data is considered to be in big-endian format (UTF-16BE). [MODCA-B-081]|
| 2. If multiple TrueType/OpenType Font (TTF/OTF) Full Font Name table vectors are specified, each vector | | | | | must specify a valid full font name. [MODCA-B-082]|
| 3. If multiple Linked TrueType/OpenType Font (TTF/OTF) Full Font Name table vectors are specified, each | | | | | vector must specify a valid full font name. [MODCA-B-083]|
| 4. The order in which multiple Linked TrueType/OpenType Font (TTF/OTF) Full Font Name table vectors are | | | | | specified in the repeating group determines the order in which the linked fonts are processed by the presentation system: • The base font is processed first, followed by the first linked font in the repeating group, followed by the next linked font in the repeating group, and so on; the last linked font in the repeating group is processed last. • If an external (print file level) resource group is specified for the print file, this resource group is searched first for a specified linked font. If the specified linked font is not found in the resource group, the RAT is accessed to locate the linked font in a library. Note that linked fonts can be packaged in a TTC. • Only one level of linking is supported. That is, if a linked font specifies its own linked fonts, these “secondary” linked fonts are not processed as linked fonts for the original base font. [MODCA-B-084]|
| 5. A specific linked font should only be specified once in a given repeating group. [MODCA-B-085]| | | | | |
| 6. LCIDs specify language and locale information for a character string that specifies a full font name and are | | | | | defined in the TrueType Font Files T echnical Specification available on the Microsoft web site. Examples of LCIDs are X'0409': Primary Language = English, Locale Name = American; X'0807': Primary Language = German, Locale Name = Swiss. A given LCID applies to the full font name that is in the same ordered Resource Access Table [MODCA-B-086]|


position in the repeating group. The first LCID applies to the first name, the second LCID applies to the
second name, and so on. The total number of LCIDs should match the total number of full font names. For
example, if the RAT RG for a given font contains two full font names, the first in English-US and the second
in German-Switzerland, table vector X'30' could optionally be specified once with data = X'04090807'.
7. When TrueType/OpenType fonts are installed in a resource library, they must not be wrapped with a [MODCA-B-087]
MO:DCA object envelope such as BOC/EOC, that is, they must be installed in their raw source format.
8. The minimum length of a TTF/OTF font OID or of a TTF/OTF font collection OID, assuming that the MD5 [MODCA-B-088]
checksum is a value less than X'7F' preceded by all zeros and can therefore be represented by 1 byte, has
been calculated to be 13 bytes. The maximum length is 129 bytes.
Repeating Group Definition for Color Management Resources (CMRs)
CMRs are identified by encoded object-type OID = X'06072B120004010139'. They are referenced in the
MO:DCA data stream using Map Data Resource (MDR), Include Object (IOB), and Preprocess Presentation
Object (PPO) structured fields. They can also be referenced from a Begin Resource (BRS) structured field,
and from a Data Object RAT . The reference specifies a CMR name that is also specified by the CMR generator
in the CMR header. The encoding of the CMR name in the CMR header and in the CMR RAT entry is UTF-
16BE. The CMR name from the CMR reference is used to index the RAT repeating groups, which specify CMR
names using the UTF-16BE encoding. Repeating groups are sorted so that the UTF-16BE code point
sequences for the CMR names are in ascending numerical order. Note that in MO:DCA environments, all UTF-
16 data is considered to be in big-endian format (UTF-16BE). [MODCA-B-089]
Repeating Group Flag Definitions for Color Management Resources
Following are the flag definitions for CMRs.
RGFlgs Provide additional information for accessing and processing the CMR. RGFlgs bits have the
following descriptions:
Bit Description
0 Reserved; must be B'0'. [MODCA-B-090]
1 Mapped CMRs. [MODCA-B-091]
B'0' There are no Link LK CMRs or device-specific CMRs in this repeating group
that are mapped to the referenced CMR. The Mapped Device CMR TV (TVid
= X'24') should be empty and is ignored.
B'1' The repeating group contains Link LK CMRs or device-specific CMRs that are
mapped to the referenced CMR. If this bit is set to B'1', the Mapped Device
CMR TV (TVid = X'24') and any additional Mapped Device CMR TVs must
specify valid CMR names.
2 Private [MODCA-B-092]
B'0' The installer considers this CMR to be a public resource. A public resource is
a candidate for resource capture by a printer. A public resource may also be
resident in the printer, and this version can be used if the object OID matches
the object OID associated with the resource reference.
B'1' The installer considers this CMR to be a private resource. A private resource
is not a candidate for resource capture by printers. A private resource is
always downloaded to the printer.
3 Embed
B'0' The installer does not allow this CMR to be embedded inline into a print file
level resource group.
B'1' The installer allows this CMR to be embedded inline into a print file level
resource group.
Resource Access Table [MODCA-B-093]


4 Capture [MODCA-B-094]
B'0' The installer does not allow this CMR to be captured.
B'1' The installer allows this CMR to be captured. A number of requirements must
be met before the presentation system will actually let resource capture take
place:
• The CMR must be identified as “public” (RGFlgs bit 2 set to B'0') by the [MODCA-B-095]
installer
• The CMR must have an object OID associated with it [MODCA-B-096]
• The CMR must be in a location that the presentation system considers [MODCA-B-097]
secure
5 Copied/extracted Profile [MODCA-B-098]
B'0' The referenced CMR is not a Color Conversion CMR that was generated from
an ICC profile that was copied or extracted from a data object.
B'1' The referenced CMR is a Color Conversion CMR that was generated from an
ICC profile that was copied or extracted from a data object.
6 Reserved; must be B'0'. [MODCA-B-099]
7 CMR normal use Indicator - Audit or Instruction [MODCA-B-100]
B'0' The referenced CMR is normally intended to be used as an instruction CMR.
If the CMR is a Color Conversion CMR, this setting allows a CMR Installer to
generate Link LK CMRs that link the referenced CMR to all Color Conversion
CMRs that are normally intended to be used as audit CMRs.
B'1' The referenced CMR is normally intended to be used as an audit CMR. If the
CMR is a Color Conversion CMR, this setting allows a CMR Installer to
generate Link LK CMRs that link the referenced CMR to all Color Conversion
CMRs that are normally intended to be used as instruction CMRs.
8 CMR normal use Indicator - Audit and Instruction [MODCA-B-101]
B'0' RGFlgs bit 7 is to used to determine how the referenced CMR is normally
intended to be used.
B'1' RGFlgs bit 7 is ignored. The referenced CMR is normally intended to be used
as both an audit CMR and an instruction CMR. If the CMR is a Color
Conversion (CC) CMR, this setting allows the installer to generate Link LK
CMRs between the referenced CMR and all CC CMRs that are normally
intended to be used as either audit, instruction, or both audit and instruction
CMRs. That is, an installer can generate the following Link LK CMRs:
• From the referenced CMR to each CC CMR that is intended to be used as [MODCA-B-102]
an instruction CMR and map these Link LK CMRs to the referenced CMR.
• From each CC CMR that is intended to be used as an audit CMR to the [MODCA-B-103]
referenced CMR and map each Link LK CMR to the audit CMR.
• From the referenced CMR to each CC CMR that is intended to be used as [MODCA-B-104]
both an audit and an instruction CMR and map these Link LK CMRs to the
referenced CMR.
• From each CC CMR that is intended to be used as an audit and an [MODCA-B-105]
instruction CMR to the referenced CMR and map each Link LK CMR to the
audit/instruction CMR.
9–15 Reserved; all bits must be B'0'.
Table Vector Definitions for Color Management Resources
Following are the table vectors defined for CMRs. The table vectors must appear in the order shown. Unless
indicated otherwise, each table vector must occur once, regardless of whether its data parameter is specified
or not. If a table vector contains no data, its length must be set to X'02' to indicate that the table vector data is
Resource Access Table [MODCA-B-106]


not specified. This is also referred to as an empty table vector. T able vectors within a RAT repeating group
must be compact. This means that for a table vector that can be repeated, all occurrences of the vector must
specify valid content, that is, the vectors cannot be empty unless there is only one occurrence of that vector. [MODCA-B-107]
| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-108]|
| --- | --- | --- | --- | --- | --- CMR Name; table vector must be specified only once [MODCA-B-109]|
| 0 | UBIN | TVLngth | | 148 T able vector length | M [MODCA-B-110]|
| 1 | CODE | TVid | | X'01' T able vector identifier | M [MODCA-B-111]|
| 2–147 CHAR CMRName Name of the CMR. This parameter | | | | | must be specified. CMR File Name; table vector must be specified only once [MODCA-B-112]|
| 0 UBIN TVLngth 4–252; even values | | | | | only T able vector length M [MODCA-B-113]|
| 1 | CODE | TVid | | X'04' T able vector identifier | M [MODCA-B-114]|
| 2–251 CHAR FileNme File name with which the CMR has | | | | | been stored in the presentation system's resource library. The file name does not include path information. This parameter must be specified. CMR Object OID; table vector must be specified only once [MODCA-B-115]|
| 0 | UBIN | TVLngth | | 12–131 T able vector length | M [MODCA-B-116]|
| 1 | CODE | TVid | | X'08' T able vector identifier | M [MODCA-B-117]|
| 2–(n-1) CODE ObjOID The object OID that is assigned to | | | | | the CMR. The length of this parameter must reflect the length of the actual OID; padding bytes are not allowed. The object OID enables the CMR to be captured and made resident in the printer. For CC CMRs, the object OID also allows the printer to search for Link LK CMRs. Mapped CMR Name; table vector must be specified at least once and may be repeated to specify multiple mapped CMRs [MODCA-B-118]|
| 0 UBIN TVLngth 2, | | | | | T able vector length; a length of 2 indicates the table vector data is not specified [MODCA-B-119]|
| 1 | CODE | TVid | | X'24' T able vector identifier | M [MODCA-B-120]|
| 2–147 CHAR CMRName Name of the mapped device-specific | | | | | CMR. This parameter must be specified if RGFlgs bit 1 = B'1'. O ICC Profile OID; table vector is optional and may be specified once for a CC CMR; ignored if specified for other CMR types [MODCA-B-121]|
| 0 UBIN TVLngth 2, 12–131 T able vector length; a length of 2 | | | | | indicates that the table vector data is not specified Resource Access Table [MODCA-B-122]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-123]|
| --- | --- | --- | --- | --- | --- [MODCA-B-124]|
| 1 | CODE | TVid | | X'18' T able vector identifier | M [MODCA-B-125]|
| 2–(n-1) CODE ObjOID The object OID for the ICC profile | | | | | that is carried by this CC CMR. The length of this parameter must reflect the length of the actual OID; padding bytes are not allowed. The object OID enables the unique identification of ICC profiles in CC CMRs. O Table Notes: [MODCA-B-126]|
| 1. All character data in the table vectors is encoded in UTF-16BE. This encoding is characterized by the | | | | | following parameters: Encoding scheme ID - as carried in the Encoding Scheme ID (X'50') triplet: X'7200' CCSID - as carried in the Coded Graphic Character Set Global Identifier (X'01') triplet (CCSID form): [MODCA-B-127]|
| 1200 (X'04B0') | | | | | Note that in MO:DCA environments, all UTF-16 data is considered to be in big-endian format (UTF-16BE). [MODCA-B-128]|
| 2. The Mapped CMR TV must be specified at least once, and can occur multiple times. If there are no | | | | | mapped CMRs, this TV must be specified once as an empty TV (TVLngth = 2). The order in which multiple Mapped Device CMRs are specified in the repeating group is not significant. This TV is used to: • map a Link LK CMR to this Color Conversion CMR if it is normally referenced as an audit CMR • map a device-specific Halftone or T one Transfer Curve CMR to this generic Halftone or T one Transfer Curve CMR. [MODCA-B-129]|
| 3. The minimum length of a CMR object OID, assuming that the MD5 checksum is a value less than X'7F' | | | | | preceded by all zeros and can therefore be represented by 1 byte, has been calculated to be 10 bytes. The maximum length is 129 bytes. [MODCA-B-130]|
| 4. See the Color Management Object Content Architecture Reference for a definition of the CMR header and | | | | | the CMR name syntax. [MODCA-B-131]|
| 5. When CMRs are installed in a resource library, they must not be wrapped with a MO:DCA object envelope | | | | | such as BOC/EOC, that is, they must be installed in their raw source format. [MODCA-B-132]|
| 6. Link LK CMRs and Link DL CMRs are distinguished in the CMR RAT by the “LK” and “DL” Link CMR sub- | | | | | type designations in the CMRType field of the CMR name; this drives the different processing that is associated with each Link CMR sub-type. Repeating Group Definition for Data Object Resources The following data objects can be processed with this RAT repeating group type: Table 44. Data Object Resources Processed with RAT RG Component ID Object Type Encoded Object-type OID [MODCA-B-133]|
| 05 IOCA FS10 X'06072B120004010105' [MODCA-B-134]| | | | | |
| 11 IOCA FS11 X'06072B12000401010B' [MODCA-B-135]| | | | | |
| 12 IOCA FS45 X'06072B12000401010C' [MODCA-B-136]| | | | | |
| 13 EPS X'06072B12000401010D' [MODCA-B-137]| | | | | |
| 14 TIFF X'06072B12000401010E' | | | | | Resource Access Table [MODCA-B-138]|


Table 44 Data Object Resources Processed with RAT RG (cont'd.)
Component ID Object Type Encoded Object-type OID
22 GIF X'06072B120004010116' [MODCA-B-139]
23 AFPC JPEG [MODCA-B-140]
Note: This object type was formerly referred to
as JFIF (JPEG).
X'06072B120004010117'
25 PDF Single-page Object X'06072B120004010119' [MODCA-B-141]
34 PCL Page Object X'06072B120004010122' [MODCA-B-142]
45 IOCA FS42 X'06072B12000401012D' [MODCA-B-143]
48 EPS with Transparency X'06072B120004010130' [MODCA-B-144]
49 PDF with Transparency X'06072B120004010131' [MODCA-B-145]
55 IOCA FS40 X'06072B120004010137' [MODCA-B-146]
58 JPEG2000 (JP2) X'06072B12000401013A' [MODCA-B-147]
60 TIFF without Transparency X'06072B12000401013C' [MODCA-B-148]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-B-149]
62 TIFF Multiple Image - without Transparency - [MODCA-B-150]
File
X'06072B12000401013E'
63 PDF Multiple Page File X'06072B12000401013F' [MODCA-B-151]
64 PDF Multiple Page - with Transparency - File X'06072B120004010140' [MODCA-B-152]
65 AFPC PNG Subset X'06072B120004010141' [MODCA-B-153]
66 AFPC TIFF Subset X'06072B120004010142' [MODCA-B-154]
68 AFPC SVG Subset X'06072B120004010144' [MODCA-B-155]
70 IOCA FS48 X'06072B120004010146' [MODCA-B-156]
71 IOCA FS14 X'06072B120004010147' [MODCA-B-157]
These data object resources are referenced in the MO:DCA data stream using Map Data Resource (MDR),
Include Object (IOB), and Preprocess Presentation Object (PPO) structured fields. The data object name from
the reference is used to index the RAT repeating groups, which specify data object names using the UTF-16BE
encoding. Repeating groups are sorted so that the UTF-16BE code point sequences for the data object names
are in ascending numerical order. Note that in MO:DCA environments, all UTF-16 data is considered to be in
big-endian format (UTF-16BE).
Repeating Group Flag Definitions for Data Object Resources
Following are the flag definitions for data object resources.
RGFlgs Provide additional information for accessing and processing the data object resource. RGFlgs
bits have the following descriptions:
Bit Description
0 Reserved; must be B'0'. [MODCA-B-158]
1 Color Management Resources (CMRs). [MODCA-B-159]
B'0' There are no CMRs that are to be associated with the referenced data object.
The CMR Name TV (TVid = X'24') and the CMR Descriptor TV (TVid = X'28')
should be empty and are ignored.
B'1' The repeating group specifies CMRs that are to be associated with the
referenced data object. If this bit is set to B'1', the TV pairs consisting of a
Resource Access Table [MODCA-B-160]


CMR Name TV (TVid = X'24') and a CMR Descriptor TV (TVid = X'28') must
specify a valid CMR name and a valid CMR processing mode.
2 Private [MODCA-B-161]
B'0' The installer considers this data object resource to be a public resource. A
public resource is a candidate for resource capture by a printer. A public
resource may also be resident in the printer, and this version can be used if
the object OID matches the object OID associated with the resource
reference.
B'1' The installer considers this data object resource to be a private resource. A
private resource is not a candidate for resource capture by printers. A private
resource is always downloaded to the printer.
3 Embed
B'0' The installer does not allow this data object resource to be embedded inline
into a print file level resource group.
B'1' The installer allows this data object resource to be embedded inline into a
print file level resource group.
4 Capture [MODCA-B-162]
B'0' The installer does not allow this data object resource to be captured.
B'1' The installer allows this data object resource to be captured. A number of
requirements must be met before the presentation system will actually let
resource capture take place:
• The data object resource must be identified as “public” (RGFlgs bit 2 set to [MODCA-B-163]
B'0') by the installer
• The data object resource must have an object OID associated with it [MODCA-B-164]
• The data object resource must be in a location that the presentation system [MODCA-B-165]
considers secure
5 Compacted Object [MODCA-B-166]
B'0' A compacted object has not been generated from the data object. If this bit is
set to B'0', the TV pair consisting of a Compacted Object File Name TV (TVid
= X'14') and a Compacted Object OID TV (TVid = X'18') should be empty and
are ignored.
B'1' A compacted object has been generated by extracting the embedded ICC
profile from the referenced data object. If this bit is set to B'1', the TV pair
consisting of a Compacted Object File Name TV (TVid = X'14') and a
Compacted Object OID TV (TVid = X'18') must not be empty and must specify
valid data.
Implementation Note: T o differentiate the file name of the compacted object
from the file name of the referenced object, it is recommended that the
file name of the compacted object, encoded in UTF-16BE, be formed
by prepending the file name of the referenced data object with the
character string “iccr_”. For example, if the file name of the referenced
object is “image.jpeg”, the file name of the compacted object would be
“iccr_image.jpeg”.
6–15 Reserved; all bits must be B'0'.
Table Vector Definitions for Data Object Resources
Following are the table vectors defined for data object resources. The table vectors must appear in the order
shown. Unless indicated otherwise, each table vector must occur once, regardless of whether its data
parameter is specified or not. If a table vector contains no data, its length must be set to X'02' to indicate that
the table vector data is not specified. This is also referred to as an empty table vector. T able vectors within a
RAT repeating group must be compact. This means that for a table vector that can be repeated, all
Resource Access Table [MODCA-B-167]


occurrences of the vector must specify valid content, that is, the vectors cannot be empty unless there is only
one occurrence of that vector. [MODCA-B-168]
| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-169]|
| --- | --- | --- | --- | --- | --- Data Object Resource Name; table vector must be specified only once [MODCA-B-170]|
| 0 UBIN TVLngth 4–252; even values | | | | | only T able vector length M [MODCA-B-171]|
| 1 | CODE | TVid | | X'01' T able vector identifier | M [MODCA-B-172]|
| 2–251 CHAR DORName Name of the data object resource. | | | | | This parameter must be specified. Data Object Resource File Name; table vector must be specified only once [MODCA-B-173]|
| 0 UBIN TVLngth 4–252; even values | | | | | only T able vector length M [MODCA-B-174]|
| 1 | CODE | TVid | | X'04' T able vector identifier | M [MODCA-B-175]|
| 2–251 CHAR FileNme File name with which the data object | | | | | resource has been stored in the presentation system's resource library. The file name does not include path information. This parameter must be specified. Data Object Resource Object OID; table vector must be specified only once [MODCA-B-176]|
| 0 UBIN TVLngth 2–131 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-177]|
| 1 | CODE | TVid | | X'08' T able vector identifier | M [MODCA-B-178]|
| 2–(n-1) CODE ObjOID The object OID that is assigned to | | | | | the data object resource. The length of this parameter must reflect the length of the actual OID; padding bytes are not allowed. The object OID enables the data object resource to be captured and made resident in the printer. O Compacted Object File Name; table vector must be specified only once [MODCA-B-179]|
| 0 UBIN TVLngth 2–252; even values | | | | | only; T able vector length; a length of 2 indicates the table vector data is not specified [MODCA-B-180]|
| 1 | CODE | TVid | | X'14' T able vector identifier | M Resource Access Table [MODCA-B-181]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-182]|
| --- | --- | --- | --- | --- | --- [MODCA-B-183]|
| 2–251 CHAR FileNme File name with which the compacted | | | | | object has been stored in the presentation system's resource library. The file name does not include path information. This parameter is optional and is ignored if RGFlgs bit 5 = B'0'. This parameter must be specified if RGFlgs bit 5 = B'1'. Implementation Note: It is recommended that the file name of the compacted object, encoded in UTF-16BE, be formed by prepending the file name of the referenced data object with the character string “iccr_”. O Compacted Object OID; table vector must be specified only once [MODCA-B-184]|
| 0 UBIN TVLngth 2–131 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-185]|
| 1 | CODE | TVid | | X'18' T able vector identifier | M [MODCA-B-186]|
| 2–(n-1) CODE ObjOID The object OID that is assigned to | | | | | the compacted object. The length of this parameter must reflect the length of the actual OID; padding bytes are not allowed. The object OID enables the compacted object to be captured and made resident in the printer. This parameter is optional and is ignored if RGFlgs bit [MODCA-B-187]|
| 5 = B'0'. | | | | | O Data Object Rendering Intent; table vector must be specified only once [MODCA-B-188]|
| 0 UBIN TVLngth 2, 10 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-189]|
| 1 | CODE | TVid | | X'1C' T able vector identifier | M [MODCA-B-190]|
| 2–3 | Reserved; | should | | be zero | O [MODCA-B-191]|
| 4 CODE IOCARI X'00'-X'03', X'FF' Rendering intent for IOCA objects: | | | | | X'00' perceptual X'01' media-relative colorimetric X'02' saturation X'03' ICC-absolute colorimetric X'FF' not specified O [MODCA-B-192]|
| 5 CODE OCARI X'00'-X'03', X'FF' Rendering intent for container (non- | | | | | OCA) objects; code definitions same as for IOCARI O [MODCA-B-193]|
| 6–7 | Reserved; | should | | be zero | O [MODCA-B-194]|
| 8–9 | Reserved; | should | | be zero | O CMR Name; table vector must be specified at least once and must be followed by a CMR Descriptor TV (TVid = X'28'); the TV pair may be repeated to specify multiple {CMR name + CMR processing mode} combinations Resource Access Table [MODCA-B-195]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-196]|
| --- | --- | --- | --- | --- | --- [MODCA-B-197]|
| 0 UBIN TVLngth 2, | | | | | T able vector length; a length of 2 indicates the table vector data is not specified [MODCA-B-198]|
| 1 | CODE | TVid | | X'24' T able vector identifier | M [MODCA-B-199]|
| 2–147 CHAR CMRName Name of the CMR. This parameter | | | | | must be specified if RGFlgs bit 1 = B'1'. O CMR Descriptor; table vector must be specified at least once and must follow the CMR Name TV (TVid = X'24'); the TV pair may be repeated to specify multiple {CMR name + CMR processing mode} combinations [MODCA-B-200]|
| 0 UBIN TVLngth 2, 4 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-201]|
| 1 | CODE | TVid | | X'28' T able vector identifier | M [MODCA-B-202]|
| 2 CODE ProcMode X'01', X'02' CMR processing mode. This | | | | | parameter must be specified if RGFlgs bit 1 = B'1'. Value Meaning X'01' process as audit CMR X'02' process as instruction CMR O [MODCA-B-203]|
| 3 Reserved; should be zero. This | | | | | parameter must be specified if RGFlgs bit 1 = B'1'. O Image Resolution; table vector is optional and may be specified once for non-IOCA raster image objects; ignored if specified for other objects [MODCA-B-204]|
| 0 UBIN TVLngth 2, 10 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-205]|
| 1 | CODE | TVid | | X'30' T able vector identifier | M [MODCA-B-206]|
| 2–3 | Reserved; | should | | be zero | O [MODCA-B-207]|
| 4 CODE XBase X'00'-X'01' Unit base for image resolution in the | | | | | X direction: X'00' 10 inches X'01' 10 centimeters O [MODCA-B-208]|
| 5 CODE YBase X'00'-X'01' Unit base for image resolution in the | | | | | Y direction; must be the same as XBase: X'00' 10 inches X'01' 10 centimeters O [MODCA-B-209]|
| 6-7 UBIN XResol 1-32,767 Number of image points in X | | | | | direction per X unit base O [MODCA-B-210]|
| 8-9 UBIN YResol 1-32,767 Number of image points in Y | | | | | direction per Y unit base O Object Container Presentation Space Size; table vector is optional and may be specified once for PDF and SVG presentation container objects; ignored if specified for other object types; bytes 5–16 are optional as a unit; either all are specified or none are specified [MODCA-B-211]|
| 0 UBIN TVLngth 2, 5, 17 T able vector length; a length of 2 | | | | | indicates the table vector data is not specified Resource Access Table [MODCA-B-212]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-213]|
| --- | --- | --- | --- | --- | --- [MODCA-B-214]|
| 1 | CODE | TVid | | X'32' T able vector identifier | M [MODCA-B-215]|
| 2–3 | Reserved; | should | | be zero | O [MODCA-B-216]|
| 4 CODE PDFSize X'01'-X'05' Parameter used to determine the | | | | | PDF presentation space size: X'01' MediaBox X'02' CropBox X'03' BleedBox X'04' TrimBox X'05' ArtBox O [MODCA-B-217]|
| 5 CODE XocBase X'00'–X'01' Presentation space size unit base for | | | | | the width (along X axis): X'00' 10 inches X'01' 10 centimeters O [MODCA-B-218]|
| 6 CODE YocBase X'00'–X'01' Presentation space size unit base for | | | | | the height (along Y axis): X'00' 10 inches X'01' 10 centimeters O [MODCA-B-219]|
| 7–8 UBIN XocUnits 1–32,767 Presentation space size units per | | | | | unit base for the width (along X axis) O [MODCA-B-220]|
| 9–10 UBIN YocUnits 1–32,767 Presentation space size units per | | | | | unit base for the height (along Y axis) O [MODCA-B-221]|
| 11–13 UBIN XpsSize 1–32,767 Presentation space width (extent | | | | | along X axis) O [MODCA-B-222]|
| 14–16 UBIN YpsSize 1–32,767 Presentation space height (extent | | | | | along Y axis) O Color Specification; table vector is optional and may be specified once for IOCA bilevel image or non-OCA bilevel and grayscale image; ignored if specified for other object types [MODCA-B-223]|
| 0 UBIN TVLngth 2, n T able vector length; a length of 2 | | | | | indicates the table vector data is not specified [MODCA-B-224]|
| 1 | CODE | TVid | | X'34' T able vector identifier | M [MODCA-B-225]|
| 2 | Reserved; | should | | be zero | O [MODCA-B-226]|
| 3 CODE ColSpce X'01', X'04', X'06', | | | | | X'08', X'40' Color space: X'01' RGB X'04' CMYK X'06' Highlight color space X'08' CIELAB X'40' Standard OCA color space O [MODCA-B-227]|
| 4-7 | Reserved; | should | | be zero | O [MODCA-B-228]|
| 8 UBIN ColSize1 X'01'–X'08', X'10' Number of bits in component 1; see | | | | | color space definitions O [MODCA-B-229]|
| 9 UBIN ColSize2 X'00'–X'08' Number of bits in component 2; see | | | | | color space definitions O [MODCA-B-230]|
| 10 UBIN ColSize3 X'00'–X'08' Number of bits in component 3; see | | | | | color space definitions O Resource Access Table [MODCA-B-231]|


| Offset | Type | Name | Range | Meaning | M/O [MODCA-B-232]|
| --- | --- | --- | --- | --- | --- [MODCA-B-233]|
| 11 UBIN ColSize4 X'00'–X'08' Number of bits in component 4; see | | | | | color space definitions O [MODCA-B-234]|
| 12–n | Color | Color | | specification | O Table Notes: [MODCA-B-235]|
| 1. All character data in the table vectors is encoded in UTF-16BE. This encoding is characterized by the | | | | | following parameters: Encoding scheme ID - as carried in the Encoding Scheme ID (X'50') triplet: X'7200' CCSID - as carried in the Coded Graphic Character Set Global Identifier (X'01') triplet (CCSID form): [MODCA-B-236]|
| 1200 (X'04B0'). | | | | | Note that in MO:DCA environments, all UTF-16 data is considered to be in big-endian format (UTF-16BE). [MODCA-B-237]|
| 2. When non-OCA objects such as EPS, PDF , GIF , TIFF , JFIF are installed in a resource library, they must not | | | | | be wrapped with a MO:DCA object envelope such as BOC/EOC, that is, they must be installed in their raw source format. [MODCA-B-238]|
| 3. The data content (bytes 2 - 9) of the Data Object Rendering Intent TV (TVid = X'1C') is optional as a unit; | | | | | that is bytes 2 - 9 are either all specified or none are specified. [MODCA-B-239]|
| 4. The rendering intent specified in the Data Object Rendering Intent TV overrides the rendering intent | | | | | specified in the OEG of the data object, and any rendering intent information embedded in the data object. The rendering intent specified in this table vector is downloaded to the presentation device but may not be used if a Link DL CMR is associated with the data object; in that case the rendering intent specified in the Link DL CMR is used to render the object [MODCA-B-240]|
| 5. CMRs that are mapped to a data object in the RAT become secondary resources of that data object and | | | | | override any conflicting CMRs specified in the OEG of the data object. In order for these secondary resources to be processed, the data object must itself be mapped as a resource in the AEG of the page or overlay that includes the data object. This allows the print server to process the data object RAT entry while processing the AEG and thereby ensure that secondary resources, such as mapped CMRs, are downloaded to the presentation device before the device enters the page-build state. Data objects that are mapped as resources before being included on a page or overlay are sometimes called hard objects. Data objects that are not mapped as resources before being included on a page or overlay are sometimes called soft objects. Therefore, using that terminology, CMRs that are mapped to a data object in the RAT will only be processed for hard objects. [MODCA-B-241]|
| 6. The minimum length of a data object OID, assuming that the MD5 checksum is a value less than X'7F' | | | | | preceded by all zeros and can therefore be represented by 1 byte, has been calculated to be 10 bytes. The maximum length is 129 bytes. [MODCA-B-242]|
| 7. The resolution specified in the Image Resolution TV overrides any raster image resolution specified on the | | | | | CDD in the OEG of the image object or inside the image. [MODCA-B-243]|
| 8. The size specified in the Object Container Presentation Space Size TV overrides any presentation space | | | | | size specified on the CDD in the OEG of the container object. [MODCA-B-244]|
| 9. The Object Container Presentation Space Size TV may be specified for PDF object types and for the | | | | | AFPC SVG Subset object type. For PDF , if this TV is not specified and if the Object Container Presentation Space Size (X'9C') triplet is not specified for the object, the architected default is X'01' - MediaBox, which is a mandatory parameter in PDF . If the Object Container Presentation Space Size TV or the Object Container Presentation Space Size (X'9C') triplet is specified, but the selected size parameter is not specified in the PDF object, the PDF default mechanism is used to select the presentation space size. For SVG, this TV specifies the size of the SVG presentation space. Bytes 5–16 are optional as a unit; either all are specified or none are specified. Resource Access Table [MODCA-B-245]|


10. The definition of bytes 2-n in the Color Specification (ID X'34') table vector matches the definition for the [MODCA-B-246]
corresponding bytes in the Color Specification (X'4E') triplet. These bytes are optional as a unit, that is,
bytes 2-n are either all specified or none are specified.
11. The Color Specification (ID X'34') table vector specifies the color that is to be used as the default color, or [MODCA-B-247]
the initial color, for an image object. This vector only specifies the color for the object presentation space; it
does not affect colors assigned to the object's object area. This table vector may be specified for IOCA
image, in which case it only applies to bilevel image; it is ignored when the image is not bilevel. It may also
be specified for non-OCA image file formats, as defined in the MO:DCA Object Type Registry appendix, in
which case it only applies to bilevel or grayscale image; it is ignored when the object is not a bilevel or
grayscale image. Note that all 1-bit per pixel image objects are considered bilevel. When the image is
grayscale, this table vector specifies the color that is to be grayscaled. The color space selected in the
table vector must be supported in the object’s data descriptor structured field. For example, if the table
vector specifies a default color using ColSpce =X'08' - CIELAB, the object’s data descriptor must also
support the CIELAB color space. If ColSpce =X'06' - Highlight color space, the % coverage and % shading
parameters are ignored. If the above conditions are not met, the table vector is ignored.
Resource Access Table [MODCA-B-248]




