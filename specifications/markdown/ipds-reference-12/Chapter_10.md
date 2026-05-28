# Chapter 10. Object Container Command Set
The Object Container command set contains IPDS commands for downloading setup files and presentation
object containers and for downloading, managing, deactivating, and including data object resources and data-
object-font components. The Object Container command set consists of the following commands:
**Table 50**. Object Container Commands [IPDS-10-001]

| Command | Code | Description | In OC1 Subset? |
| :--- | :---: | :--- | :---: |
| DORE | X'D66C' | Data Object Resource Equivalence | No [IPDS-10-002]|
| DORE2 | X'D66E' | Data Object Resource Equivalence 2 | No [IPDS-10-003]|
| DDOFC | X'D65B' | Deactivate Data-Object-Font Component | No [IPDS-10-004]|
| DDOR | X'D65C' | Deactivate Data Object Resource | No [IPDS-10-005]|
| IDO | X'D67C' | Include Data Object | No [IPDS-10-006]|
| RRR | X'D65A' | Remove Resident Resource | No [IPDS-10-007]|
| RRRL | X'D659' | Request Resident Resource List | No [IPDS-10-008]|
| WOCC | X'D63C' | Write Object Container Control | Yes [IPDS-10-009]|
| WOC | X'D64C' | Write Object Container | Yes [IPDS-10-010]|
Object containers are used to carry data whose syntactic and semantic definitions are not controlled by an
AFPC presentation architecture. The content of such data objects is not constrained to be that of traditional
text, image, or graphics objects. Some object containers are used to carry data that is to be presented on a
page, while others carry non-presentation data.
For object containers that carry presentation data, the object must have a well-defined processing semantic
resulting in a fixed, deterministic presentation when processed by a receiver capable of presenting the object.
Object containers with presentation data cannot span pages; however, presentation objects within medium
overlays and UP3I PrintData objects that are presented by a pre-processing or post-processing device can
appear to span pages or extend outside of logical page boundaries.
A multi-page resource object is a type of object container consisting of a file that contains multiple pages or
paginated objects for presentation. Such an object is appropriately characterized in the MO:DCA Object
Registry. That is, it is registered with an object-type OID that identifies it as a file that might contain multiple
pages or paginated objects. When a multi-page file is referenced in the data stream with an Include Data
Object (IDO) command, the reference selects a single paginated object within the file for presentation.
Similarly, when a multi-page file is carried in WOC commands specified directly within a page or overlay, the
WOCC command selects a single paginated object within the file for presentation. For information on which
multi-page resource objects can use secondary resources, refer to Table 51. CMRs are used with
multi-page resource objects. Examples of multi-page resource objects are PDF files that contain multiple
pages and TIFF files that contain multiple paginated image objects.
If an object container contains non-presentation data, the host sends a Write Object Container Control
(WOCC) command in home state, followed by one or more Write Object Container (WOC) commands. The
data in an object container is in effect when the commands are successfully received and syntax checked for
data stream exceptions.
If an object container contains presentation data, the host sends a Write Object Container Control (WOCC)
command and one or more Write Object Container (WOC) commands. If the WOCC is sent in home state, the
object container is saved as a data object resource to be presented later via an IDO command. If the WOCC is [IPDS-10-011]


sent in page or overlay state, the object container is presented in the current page or overlay. Presentation
object containers can reference activated printer-resident data object resources or data object resources that
were previously downloaded; these secondary resources are identified in a Data Object Resource Equivalence
(DORE) or Data Object Resource Equivalence 2 (DORE2) command.
Color Management Resources (CMRs) are used to produce accurate colors or grayscale and to provide color-
tuning capability. CMRs can be invoked with an Invoke CMR (X'92') triplet on the IDO or WOCC command for
a presentation object container, with an Invoke Tertiary Resource (X'A2') triplet on the WBCC command for
image presentation object containers to be printed in conjunction with a QR Code with Image bar code,
or can
be invoked at other layers in the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy” for a
description of the hierarchy.
The XOH-OPC reply lists the object containers supported by the printer and for each type of object indicates
whether the object is supported in home state, in page or overlay state, or in all three states. The object-type
OIDs listed in the XOH-OPC reply are also defined to be either presentation or non-presentation; refer to the
tables in “Data Object Resources, Data-Object-Font Components, and Setup Files” for a list of the
defined object-type OIDs. If the host sends a WOCC command for an object type that is not supported in the
current state, either exception ID X'020D..02' or X'8002..00' exists.
The following pages explain the object container presentation space and the object container object area for
object containers that contain presentation data.
Object Container Presentation Space
Object container presentation data is placed in a presentation space in much the same way as image,
graphics, and bar code data. The coordinate system for this presentation space is the $X_{oc}$ and the $Y_{oc}$ coordinate system. The size of the object container presentation space and how the data is placed in the presentation space is defined by the specific object; refer to the Object-Type Identifiers registry in the Mixed Object Document Content Architecture Reference for a description of the presentation space size for each object.
Object Container Object Area
The object container presentation space is mapped, using one of the defined mapping options, into the object container object area, that is a rectangular area on the current logical page. The object container object area can be larger than, equal to, or smaller than the object container presentation space. The coordinate system for the object container object area is the $X_{oa}, Y_{oa}$ coordinate system.
The location and orientation of the object container object area is specified in the object container area position (OCAP) self-defining field of the WOCC command. The size of the object container object area is specified in the object container output control (OCOC) self-defining field. Figure 95 shows how the object container object area is placed on the logical page.
The object container object area can overlay other data, such as text or images, specified earlier for the same
logical page. Also, the object container object area can be overlapped by subsequent data specified by other
commands for the same logical page. Refer to “IPDS Mixing Rules” for a description of the results
of overlapping print data. UP3I Print Data objects are presented by a pre-processing or post-processing device
and do not directly mix with other IPDS data; this type of object uses the UP3I-Print-Data mapping control
option, refer to “UP3I-Print-Data Mapping”.
Some printers allow the object area to be colored before the object data is placed in the object area; coloring is
specified with triplets in the Object Container Output Control self-defining field. Support for this optional
function is indicated by the X'6201' property pair that is returned in the Device-Control command-set vector of [IPDS-10-012]


the Sense Type and Model command reply. Object area coloring for UP3I Print Data objects is handled by the
IPDS printer in the same manner as for all other object containers.
Figure 95. Locating, Sizing, and Orienting the Object Container Object Area
Logical Page
Origin of Object Container
Object Area specified in
Object Container Area [IPDS-10-013]

Object Container
Object Area
Size of Object Container
Object Area specified in
Object Container Output [IPDS-10-014]

Orientation of Object
Container Object
Area specified in
Object Container

I, B, $, $, {oa}$, {oa}$
Data Object Resource Equivalence Entries
The Data Object Resource Equivalence (DORE) and Data Object Resource Equivalence 2 (DORE2)
commands are home state, page state, page segment state, or overlay state commands used to identify
secondary resources to be used with a subsequent presentation data object and to provide a mapping
between a HAID and an internal resource ID for a secondary resource. The mapping between HAID and internal resource ID is called an equivalence and each equivalence established through a DORE or DORE2 command is called an equivalence entry. Not all secondary resources need an internal resource ID, for
example Resident Color Profile resource objects have no internal resource ID and are simply processed before
a presentation data object is processed.
The concept of a tertiary resource was introduced with the addition of the QR Code with Image bar code. Such
a bar code can have image presentation data objects printed in conjunction with the QR Code symbol—to print
the image exactly centered on the symbol, for example. Each QR Code symbol can have multiple such image
objects printed in conjunction with it, and each of those image objects are secondary resources of the bar
code. Note, though, that the image objects can themselves have secondary resources—these secondary
resources of secondary resources are called tertiary resources. In general, tertiary resources work the same
as secondary resources, and whenever words in this Reference discuss “secondary resources”, those words
apply equally to tertiary resources, unless otherwise noted. [IPDS-10-015]


The following table shows the valid secondary resources for each presentation data object: [IPDS-10-016]

**Table 51**. Secondary Resource Usage [IPDS-10-017]

| Presentation Data Object | Secondary Resource | Internal Resource ID | HAID pool* |
| :--- | :--- | :--- | :---: |
| BCOCA bar code (QR Code with Image) | Presentation data object resource (see notes 2 and 3) | Yes, 2-byte image local ID | DOR [IPDS-10-018]|
| Encapsulated PostScript (EPS) (with or without transparency) | Resident Color Profile (see note 3) | No | DOR [IPDS-10-019]|
| IOCA image | IOCA Tile Resource | Yes, 4-byte local ID | DOR [IPDS-10-020]|
| PDF single page or multi page (with or without transparency) | PDF Resource Object | Yes, PostScript name or string | DOR [IPDS-10-021]|
| | Non-OCA Resource object | Yes, PostScript name or string | DOR [IPDS-10-022]|
| | TrueType/OpenType Font (see note 4) | Yes, PostScript name or string | DOFC [IPDS-10-023]|
| | Resident Color Profile (see note 3) | No | DOR [IPDS-10-024]|
| AFPC SVG Subset | Non-OCA Resource object | Yes, SVG name or string | DOR [IPDS-10-025]|
| | TrueType/OpenType Font (see note 4) | Yes, SVG name or string | DOFC [IPDS-10-026]|
* DOR=Data-object-resource HAID pool; DOFC=Data-object-font-component HAID pool [IPDS-10-027]
Notes:
1. When an attempt to use a secondary resource is made, if the specific combination of presentation data object type [IPDS-10-028]
and secondary resource type is not listed in this table, exception condition X'020D..17' exists.
2. The potential secondary-resource object types used by a BCOCA QR Code with Image bar code are the [IPDS-10-029]
presentation data object resources listed in “Data Object Resources, Data-Object-Font Components, and Setup
Files”. Note that if such a presentation data object resource is a multi-page resource object (such as a
multi-page PDF or a multi-image TIFF), only the first paginated object within the resource is printed in conjunction
with the QR Code symbol; all other paginated objects within the multi-page resource object are not accessible when
used as a secondary resource.
3. Note that all object types in the first column above, other than BCOCA bar code, are potential secondary-resource [IPDS-10-030]
object types used by a BCOCA QR Code with Image bar code, meaning that the EPS, IOCA, PDF , and AFPC SVG
object types can both use secondary resources and be secondary resources themselves; in the latter case, the
entries in the second column—other than Resident Color Profile—become tertiary resources. Resident Color
Profiles are not supported as tertiary resources.
4. Because TrueType/OpenType Font secondary resources use the data-object-font-component HAID pool, the [IPDS-10-031]
DORE2 command must be used to establish equivalence entries for them. [IPDS-10-032]


Note: Color Management Resources (CMRs) are another type of resource that is used with presentation data
objects (and all other presentation objects) that contain color data. The DORE and DORE2 commands
are not used to invoke Color Management Resources (CMRs). CMRs can be invoked with an Invoke
CMR (X'92') triplet on the IDO or WOCC command for a presentation object container, with an Invoke
Tertiary Resource (X'A2') triplet on the WBCC command for image presentation object containers to be
printed in conjunction with a QR Code with Image bar code, or can be invoked at other layers in the
CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy” for a description of the hierarchy
and of Color Management Resources.
Even though the DORE and DORE2 commands are not used to invoke CMRs, they are used in the
process of invoking tertiary CMRs through the Invoke Tertiary Resource (X'A2') triplet. When printing an
image object in conjunction with a QR Code with Image bar code, the image object is a secondary
resource to the bar code; any CMRs invoked for that image object are considered tertiary resources.
The Invoke Tertiary Resource (X'A2') triplet provides the HAID of a CMR and also identifies an internal
image local ID from within the bar code; the two pieces of information establish for which secondary
resource image object the tertiary CMR is being invoked. To determine the HAID of the image, the
DORE
or DORE2 equivalence entries are searched for the internal image local ID.
A Resident Color Profile and Color Management Resources can be specified for a presentation data
object. If an audit, object-level, color-conversion CMR is invoked at the object level for a specific
presentation object, it takes precedence over any Resident Color Profile used as a secondary resource
for the presentation object.
A DORE or DORE2 command replaces all equivalence entries from any previous DORE or DORE2 command; that is, there is only one (possibly empty) set of equivalence entries at any one time, which can be set by either the DORE or DORE2 command. Also, all equivalence entries are reset at the end of each page and each
downloaded overlay. A DORE or DORE2 command with no equivalence entries removes all previous
equivalence entries; this provides a reset function.
Note: The DORE and DORE2 commands are processed differently with included overlays than with included
page segments.
When a DORE or DORE2 command is in effect and an Include Overlay command is encountered, that
DORE or DORE2 command is not used within the included overlay. Once the Include Overlay command
has been processed, the DORE or DORE2 command is active again. DORE or DORE2 commands
within the overlay are used when processing the overlay.
When a DORE or DORE2 command is in effect and an Include Page Segment command is
encountered, the DORE or DORE2 command remains in effect until the end of the including page or
overlay unless another DORE or DORE2 command is encountered within the page segment, page, or
overlay. The active DORE or DORE2 command is not reset after the Include Page Segment command
has been processed.
DORE or DORE2 equivalence entries that provide a HAID and an internal resource ID are not actually used
until a subsequent data object that requires the secondary resource is presented in a page, page segment, or
overlay, using a method specific to that data object’s type ; when the printer is printing the data object and the
secondary resource is needed, the printer will search for a matching internal resource ID in the current set of equivalence entries and will use the corresponding HAID to locate the secondary resource. If multiple
equivalence entries are specified with the same internal resource ID, the first one is used and the others are
ignored.
Before a data object is printed in a page, page segment, or overlay, the printer checks all equivalence entries
that have a zero length internal resource ID—known as HAID-only equivalence entries— and uses the object-
type OID of the referenced secondary resource to determine the appropriate use for that resource. Only one [IPDS-10-033]


Resident Color Profile resource can be used with a data object; therefore, if multiple Resident Color Profile
resources are specified, the first is used and the others are ignored. Also, if multiple HAID-only equivalence
entries are specified with the same HAID, the first one is used and the others are ignored. Note that Resident
Color Profiles are not used with data objects to be printed in conjunction with a QR Code with Image bar code ;
any HAID-only Resident Color Profiles in the equivalence entries are ignored when processing data objects
that are secondary resources of a QR Code with Image bar code.
If an appropriate equivalence entry is not found or if the secondary resource identified in the entry has not been
activated, exception ID X'020D..10' exists. When a secondary resource is invoked, if the resource identified by
the HAID is not supported with the data object, exception ID X'020D..17' exists.
Property pair X'1201' in the STM Object Container command-set vector indicates support for the DORE
command in page, page segment, and overlay states. That property pair in conjunction with property pair
X'707B' in the STM Device-Control command-set vector indicates support for the DORE command in home
state.
Property pair X'120E' in the STM Object Container command-set vector indicates support for the DORE2
command in page, page segment, and overlay states. That property pair in conjunction with property pair
X'707B' in the STM Device-Control command-set vector indicates support for the DORE2 command in home
state.


Data Object Resource Equivalence
The Data Object Resource Equivalence (DORE) command is one of two commands used to establish data-
object-resource equivalence entries. See “Data Object Resource Equivalence Entries ” for more
information on equivalence entries and the DORE and DORE2 commands.
```
Length X'D66C' Flag CID Data
```
The length of the DORE command can be:
Without CID X'0005' or X'0008'–X'7FFF'
With CID X'0007' or X'000A'–X'7FFF'
However, each entry length must also be valid. Exception ID X'0202..02' exists if the command length is invalid
or unsupported.
The data in a DORE command consists of zero or more equivalence entries. If a syntax error is encountered in
one of the entries, that entry and all following entries in the DORE command are discarded; preceding entries
remain in effect. The DORE command data field is defined as follows: [IPDS-10-034]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Zero or more equivalence entries in the following format:** [IPDS-10-035]| | | | | |
| 0 | UBIN | Length | X'03'–X'FD' | Entry length, including this length field | X'03'–X'FD' [IPDS-10-036]|
| 1–2 | CODE | HAID | X'0001'–X'7EFF'<br>X'E47D' | Host-Assigned ID of secondary resource<br>Extender entry | X'0001'–X'7EFF' [IPDS-10-037]|
| 3 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource; this field is omitted if there is no internal ID for this resource | Any value [IPDS-10-038]|
Byte 0 Entry length
This field contains the length of a DORE equivalence entry, including the length field itself. If
an invalid value is specified in this field or if the entry is too long to fit into the DORE command,
exception ID X'020D..12' exists.
Bytes 1–2 Host-Assigned ID of secondary resource
This field specifies the data object resource HAID of a secondary resource to be used with a
subsequent data object in the page, page segment, or overlay. The data-object-resource HAID
pool is searched for this HAID. If an invalid value is specified in this field, exception ID
X'020D..11' exists.
HAID value X'E47D'—normally an invalid value for a HAID in the IPDS architecture—indicates
that this DORE entry is extending the Internal resource ID of the previous entry. In this case,
the bytes in the Internal resource ID field of this entry are added to the end of the bytes in the
Internal resource ID field of the previous entry, forming an Internal resource ID that can be
much longer than 250 bytes long. This extension process can be done as many times as
desired, with any number of X'E47D' entries adding more and more bytes to the Internal
resource ID. For example, if an equivalence entry with HAID=X'14' and a 600-byte internal
resource ID was desired, it could be done with three entries: a first entry could specify HAID=
X'14' and contain the first 250 bytes of the internal resource ID, the next entry could specify
HAID=X'E47D' and contain the next 250 bytes of the internal resource ID, and the next entry
could specify HAID=X'E47D' and contain the last 100 bytes of the internal resource ID. [IPDS-10-039]

A non-X'E47D' entry followed by any number of X'E47D' entries make up one single
equivalence entry. For example, if a syntax error occurs in an extender entry, that entry and all
entries back to the last non-X'E47D' entry are discarded.
If the very first entry contains HAID=X'E47D'—that is, it is an extender entry but there is no
previous entry to extend—exception ID X'020D..0F' exists. If a HAID-only entry is followed by
a HAID=X'E47D' entry, exception ID X'020D..0F' also exists.
Not all implementations support the extension mechanism using the X'E47D' HAID value.
Property pair X'120A' in the STM Object Container command-set vector indicates support for
the X'E47D' HAID value.
Bytes 3 to end of entry
1–250 byte long internal resource ID
At the IPDS level, the internal resource ID is considered to be a binary string that uniquely
identifies a secondary resource within the scope of the data object that invokes the secondary
resource.

Data Object Resource Equivalence 2
The Data Object Resource Equivalence 2 (DORE2) command is one of two commands used to establish data-
object-resource equivalence entries. See “Data Object Resource Equivalence Entries ” for more
information on equivalence entries and the DORE and DORE2 commands.
```
Length X'D66E' Flag CID Data
```
The length of the DORE2 command can be:
Without CID X'0005' or X'000C'–X'7FFF'
With CID X'0007' or X'000E'–X'7FFF'
However, each entry length must also be valid. Exception ID X'0202..02' exists if the command length is invalid
or unsupported.
The data in a DORE2 command consists of zero or more equivalence entries. If a syntax error is encountered
in one of the entries, that entry and all following entries in the DORE2 command are discarded; preceding
entries remain in effect. The DORE2 command data field is defined as follows: [IPDS-10-040]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Zero or more equivalence entries in the following format:** [IPDS-10-041]| | | | | |
| 0–1 | UBIN | Length | X'0007'–X'7FFA' | Entry length, including this length field | X'0007'–X'7FFA' [IPDS-10-042]|
| 2–3 | CODE | HAID | X'0001'–X'7EFF' | Host-Assigned ID of secondary resource | X'0001'–X'7EFF' [IPDS-10-043]|
| 4 | CODE | HAID pool | X'01'<br>X'02' | HAID pool to search:<br>X'01' Data-object resource<br>X'02' Data-object-font component | X'01'<br>X'02' [IPDS-10-044]|
| 5–6 | X'0000' | Reserved | X'0000' | Reserved | X'0000' [IPDS-10-045]|
| 7 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource; this field is omitted if there is no internal ID for this resource | Any value [IPDS-10-046]|
Bytes 0–1 Entry length
This field contains the length of the equivalence entry, including the length field itself. If an
invalid value is specified in this field or if the entry is too long to fit into the DORE2 command,
exception ID X'020D..12' exists.
Bytes 2–3 Host-Assigned ID of secondary resource
This field specifies the HAID of a secondary resource to be used with a subsequent data
object in the page, page segment, or overlay. If an invalid value is specified in this field,
exception ID X'020D..11' exists.
Byte 4 HAID pool to search
This field specifies the HAID pool to be searched for the HAID in bytes 2–3. If an invalid value
is specified in this field, exception ID X'020D..19' exists.
X'01' The data-object-resource HAID pool is searched.
X'02' The data-object-font-component HAID pool is searched.
Bytes 5–6 Reserved
Data Object Resource Equivalence 2 (DORE2) [IPDS-10-047]


Bytes 7 to end of entry
1–32755 byte long internal resource ID
At the IPDS level, the internal resource ID is considered to be a binary string that uniquely
identifies a secondary resource within the scope of the data object that invokes the secondary
resource.
Data Object Resource Equivalence 2 (DORE2) [IPDS-10-048]


Deactivate Data-Object-Font Component
The Deactivate Data-Object-Font Component (DDOFC) command directs the printer to deactivate one or more
previously activated data-object-font components. Support for this optional command is indicated by the
X'F204' property pair in the Device-Control command-set vector of an STM reply.
When a data-object-font component is deactivated, any activation information for that resource created by a
previous WOCC, or AR command is also deleted. AR entries for unactivated data-object-font components are
not affected by the Deactivate Data-Object-Font Component command.
The XOA-RRL command can be used to find out whether or not a data-object-font component is present in the
printer or is currently activated.
```
Length X'D65B' Flag CID Data
```
The length of the DDOFC command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The DDOFC command data field is as follows: [IPDS-10-049]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Data-object-font component Host-Assigned ID | X'0000'<br>X'0001'–X'7EFF' [IPDS-10-050]|

Bytes 0–1 Data-object-font-component Host-Assigned ID or deactivate all indicator
These bytes specify either an individual data-object-font component to be deactivated or
specify the deactivation of all data-object-font components. Nonzero values identify a specific
data-object-font component to be deactivated and correspond to the data-object-font-
component Host-Assigned ID of an AR or home-state WOCC command. If an invalid value is
specified in this field, exception ID X'020D..11' exists. Exception ID X'020D..14' exists if the
data-object-font component specified is not currently activated.
Data-object-font components, such as TrueType/OpenType fonts and TrueType/OpenType
collections, are deactivated with the DDOFC command but can also be components of one or
more data-object fonts. Exception ID X'020D..18' exists if an attempt is made to deactivate a
component of a currently activated data-object font. Before deactivating a data-object-font
component or a code page, all data-object fonts that use these components must first be
deactivated with the Deactivate Font command or the XOH Erase Residual Font Data
command.
A deactivate-all command when there are no active data-object-font components is effectively
a NOP.
Deactivate Data-Object-Font Component (DDOFC) [IPDS-10-051]


Deactivate Data Object Resource
The Deactivate Data Object Resource (DDOR) command directs the printer to deactivate one or more
previously activated data object resources. Support for this optional command is indicated by the X'1201'
property pair in the Object Container command-set vector of an STM reply.
When a data object resource is deactivated, any activation information for that resource created by a previous
WIC2, WOCC, or AR command is also deleted. AR entries for unactivated data object resources are not
affected by the Deactivate Data Object Resource command.
The XOA-RRL command can be used to find out whether or not a data object resource is present in the printer
or is currently activated.
Note: Presentation Services Programs should take care when deactivating CMRs that are currently invoked.
When a CMR is deactivated, all invocations of that CMR are also removed.
```
Length X'D65C' Flag CID Data
```
The length of the DDOR command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The DDOR command data field is as follows: [IPDS-10-052]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Data object resource Host-Assigned ID | X'0000'<br>X'0001'–X'7EFF' [IPDS-10-053]|

Bytes 0–1 Data object resource Host-Assigned ID or deactivate all indicator
These bytes specify either an individual data object resource to be deactivated or specify the
deactivation of all data object resources. Nonzero values identify a specific data object
resource to be deactivated and correspond to the data object resource Host-Assigned ID of an
AR, home-state WIC2, or home-state WOCC command. If an invalid value is specified in this
field, exception ID X'020D..11' exists. Exception ID X'020D..14' exists if the data object
resource specified is not currently activated.
A deactivate-all command when there are no active data object resources is effectively a NOP. [IPDS-10-054]

Include Data Object
The Include Data Object (IDO) command causes a previously activated data object resource to be presented
in the current page or overlay. The data object is processed as if it had been included directly in the page or
overlay via object-container or IO-Image commands. The IDO command can also be used to override most of
the control information from the object's WOCC or WIC2 command. The data objects that can be included with
an IDO command are:
• EPS (Encapsulated PostScript) with transparency [IPDS-10-055]
• EPS without transparency [IPDS-10-056]
• GIF (Graphics Interchange Format) [IPDS-10-057]
• IOCA (Image Object Content Architecture) image [IPDS-10-058]
• JPEG (Joint Photographic Experts Group) AFPC JPEG Subset [IPDS-10-059]
• JP2 (JPEG2000 File Format) [IPDS-10-060]
• PCL (Printer Command Language) page object [IPDS-10-061]
• PDF (Portable Document Format) multiple-page file with transparency [IPDS-10-062]
• PDF multiple-page file without transparency [IPDS-10-063]
• PDF single page with transparency [IPDS-10-064]
• PDF single page without transparency [IPDS-10-065]
• PNG (Portable Network Graphics) AFPC PNG Subset [IPDS-10-066]
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-10-067]
• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-10-068]
• TIFF with transparency [IPDS-10-069]
• TIFF without transparency [IPDS-10-070]
• TIFF multiple-image file with transparency [IPDS-10-071]
• TIFF multiple-image file without transparency [IPDS-10-072]
• UP3I print data [IPDS-10-073]
Note: Data object resources that are supported in home state, page state, and overlay state (all three) should
also be supported on the IDO command.
Support for this optional command is indicated by the X'1201' property pair in the Object Container command-
set vector of an STM reply.
Length X'D67C' Flag CID Data (DOAP, DOOC, DODD)
The length of the IDO command can be:
Without CID X'001B' or X'001D'–X'7FFF'
With CID X'001D' or X'001F'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The parameters of this command identify the data object to be included and provide overrides for the object
area, presentation-space-to-object-area mapping, presentation space reset mixing, color management
resources, image resolution, and default bilevel and grayscale image color. The IDO data field consists of one,
two, or three consecutive self-defining fields in the following order:
1. Data Object Area Position (DOAP), optional [IPDS-10-074]
2. Data Object Output Control (DOOC), optional [IPDS-10-075]
3. Data Object Data Descriptor (DODD), mandatory [IPDS-10-076]

Note: It is recommended that the IDO command used to include a presentation object always provide
overrides to ensure that the desired controls are used; if overrides are not supplied on the IDO
command, the OCAP and OCOC values (or defaults) from the WOCC command (or the IAP and IOC
from the WIC2 command) are used. If a presentation object is to be captured by the printer, it is also
recommended that the presentation services program omit the OCAP and OCOC from the WOCC
command so that the object is captured with known defaults for each of these controls. Since the
application program cannot know what controls were saved when the resource was captured, the
application program cannot know for certain what these WOCC (or WIC2) values might be at print time.
To associate metadata with a data object resource, the metadata must be passed just after the WOCC
command (or the WIC2 command for IOCA images) when the data object resource was downloaded. Here in
the IDO command, additional metadata cannot be associated specifically with the data object resource.
If an invalid self-defining field is specified, a self-defining field is out of order, a required self-defining field is not
specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists.
The HAID parameter in the Data Object Data Descriptor identifies the data object to be included. All necessary
secondary resources must be identified in a prior DORE or DORE2 command within the page or overlay.
To improve print performance, if a previous Rasterize Presentation Object (RPO) command had preprocessed
and cached an appropriate variation of the data object resource to be included, the printer can simply use the
cached variation rather than rasterizing the object at include time. [IPDS-10-077]

Data Object Area Position
The Data Object Area Position (DOAP) self-defining field, if present, is the first self-defining field in the data
portion of the IDO command. This field provides overrides for the position and orientation of the data object's
object area. The origin and the orientation of the data object's object area is defined relative to the logical page
coordinate system of the underlying page or overlay.
This self-defining field is optional and can be omitted from the IDO command. If the DOAP is not specified, the
OCAP from the WOCC command (or the IAP from the WIC2 command) for the included data object is used. If
the optional OCAP on the WOCC command is also omitted, the defaults specified in the OCAP description are
used.
The format of the DOAP is as follows: [IPDS-10-078]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of DOAP | Length of DOAP, including this length field | X'000B' to end of DOAP [IPDS-10-079]|
| 2–3 | CODE | SDF ID | X'ACC3' | Self-defining-field ID | X'ACC3' [IPDS-10-080]|
| 4–5 | SBIN | X offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for object area origin; an I, I-offset, or $ coordinate position in L-units. Special value: X'FFFF' (Use X offset from object) | X'8000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-081]|
| 6–7 | SBIN | Y offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for object area origin; a B, B-offset, or $Y_{p}$ coordinate position in L-units. Special value: X'FFFF' (Use Y offset from object) | X'8000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-082]|
| 8–9 | CODE | Override for object area orientation [IPDS-10-083]| | | |
| | bits 0–8 | Degrees | B'000000000'–B'101100111'<br>B'111111111' | Number of degrees (0–359) in the orientation. Special value: B'111111111' (see byte description) | B'000000000'<br>B'001011010'<br>B'010110100'<br>B'100001110'<br>B'111111111' [IPDS-10-084]|
| | bits 9–14 | Minutes | B'000000'–B'111011'<br>B'111111' | Number of minutes (0–59) in the orientation. Special value: B'111111' (see byte description) | B'000000'<br>B'111111' [IPDS-10-085]|
| | bit 15 | | B'0'<br>B'1' | Reserved. Special value: B'1' (see byte description) | B'0'<br>B'1' [IPDS-10-086]|
| 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>X'00' Absolute $I$, absolute $B$<br>X'20' Absolute $I$, relative $B$<br>X'40' Relative $I$, absolute $B$<br>X'60' Relative $I$, relative $B$<br>X'A0' Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' [IPDS-10-087]|
| 11 to end of DOAP | UNDF | | | Data without architectural definition [IPDS-10-088]| |

Note: The required range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the specified range in 1440ths plus an equivalent range for
additional units of measure. If a receiver supports additional units of measure, the IPDS architecture
requires the receiver to at least support a range equivalent to the specified range relative to each
supported unit of measure. More information about supported-range requirements is provided in the
section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length. Bytes after byte 10 are ignored by the printer.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Override for the object area origin offset in L-units
This field specifies an override for the object area origin as an I, I-offset, or X
p coordinate
position, depending on the reference coordinate system specified in byte 10. The units of
measure used to interpret this L-unit value are specified in the LPD command that is current
when this object is printed in a page or overlay. If an unsupported value is specified in this
field, exception ID X'020C..05' exists. Exception ID X'0860..00' exists if the position cannot be
represented by the printer.
X'FFFF' is a special value that specifies that the X offset from the OCAP of the object's WOCC
command (or the IAP of the object's WIC2 command) is used; if the optional OCAP on the
WOCC command is omitted, an offset of 0 is used.
Note: If an object-area-origin override value is specified and the other override value is
X'FFFF', the printer first converts the origin-offset values in the object to the same
reference coordinate system specified in the IDO before applying the override.
Bytes 6–7 Override for the object area origin offset in L-units
This field specifies an override for the object area origin as a B, B-offset, or Y
p coordinate
position, depending on the reference coordinate system specified in byte 10. The units of
measure used to interpret this L-unit value are specified in the LPD command that is current
when this object is printed in a page or overlay. If an unsupported value is specified in this
field, exception ID X'020C..05' exists. Exception ID X'0860..00' exists if the position cannot be
represented by the printer.
X'FFFF' is a special value that specifies that the Y offset from the OCAP of the object's WOCC
command (or the IAP of the object's WIC2 command) is used; if the optional OCAP on the
WOCC command is omitted, an offset of 0 is used.
Note: If an object-area-origin override value is specified and the other override value is
X'FFFF', the printer first converts the origin-offset values in the object to the same
reference coordinate system specified in the IDO before applying the override. [IPDS-10-089]

Bytes 8–9 Override for the object area orientation
This field specifies an override for the object area orientation; refer to the description in the
WOCC-OCAP or the WIC2-IAP for more details. X'FFFF'—that is, all 16 bits are set to B'1'—
is a special value that specifies that the orientation from the OCAP of the object's WOCC
command (or the IAP of the object's WIC2 command) is used; if the optional OCAP on the
WOCC command is omitted, 0 degrees is used.
Not all printers support orientation values other than 0, 90, 180, and 270 degrees; the X'A0nn'
property pair in the Object Container command-set vector in the STM reply reports the
orientation support of the printer. Exception ID X'0203..05' exists if the printer does not support
the requested orientation value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-10-090]
90 degrees [IPDS-10-091]
180 degrees [IPDS-10-092]
270 degrees [IPDS-10-093]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system
This field specifies the reference coordinate system used for the IDO command. Refer to the
OCAP or IAP description for more details about the reference coordinate system.
If an invalid value is specified, exception ID X'0204..05' exists.
Bytes 11 to
end of DOAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-10-094]

Data Object Output Control
The Data Object Output Control (DOOC), if present, is the first or second self-defining field in the data portion
of the IDO command; if the DOAP is specified, the DOOC is the second self-defining field. This self-defining
field specifies overrides for the size of the object area, for the presentation-space-to-object-area mapping
option, for Presentation Space Reset Mixing, for data-object-level rendering intent, and for data-object-level
Color Management Resources.
This self-defining field is optional and can be omitted from the IDO command. If the DOOC is not specified, the
OCOC from the WOCC command (or the IOC from the WIC2 command) for the included data object is used. If
the OCOC on a WOCC command (or IOC on a WIC2 command) is also omitted, the defaults specified in the
OCOC description (or IOC description) are used.
The format of the DOOC is as follows: [IPDS-10-095]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010', X'0012' to end of DOOC | Length of DOOC, including this length field | X'0010', X'0012' to end of DOOC [IPDS-10-096]|
| 2–3 | CODE | SDF ID | X'ABC3' | Self-defining-field ID | X'ABC3' [IPDS-10-097]|
| 4 | CODE | Unit base | X'00'<br>X'01' | X'00' Ten inches<br>X'01' Ten centimeters | X'00' [IPDS-10-098]|
| 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-10-099]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use $X_{oa}$ extent from object) | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-100]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use $Y_{oa}$ extent from object) | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-101]|
| 11 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'41'<br>X'42'<br>X'50'<br>X'60'<br>X'FF' | Override for mapping control option:<br>X'00' Position (not valid for IO Image)<br>X'10' Scale to fit<br>X'20' Center and trim<br>X'30' Position and trim<br>X'41' Point to pel (IO Image only)<br>X'42' Point to pel w/double dot (IO Image only)<br>X'50' Replicate and trim (IO Image only)<br>X'60' Scale to fill<br>X'FF' Use object's mapping option | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'FF' [IPDS-10-102]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ offset in L-units; (for the position and position-and-trim mappings only). Special value: X'FFFF' (Use $X_{oa}$ offset from object) | X'0000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-103]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ offset in L-units; (for the position and position-and-trim mappings only). Special value: X'FFFF' (Use $Y_{oa}$ offset from object) | X'0000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' [IPDS-10-104]|
| 16 to end of DOOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets | X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-10-105]| |
Note: The required range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the specified range in 1440ths plus an equivalent range for
additional units of measure. If a receiver supports additional units of measure, the IPDS architecture
requires the receiver to at least support a range equivalent to the specified range relative to each
supported unit of measure. More information about supported-range requirements is provided in the
section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Byte 4 Unit base
This field specifies the unit base to be used to interpret bytes 7–10 and bytes 12–15 of the
DOOC. A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates
that the unit base is ten centimeters.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
If an invalid or unsupported value is specified, exception ID X'0205..05' exists.
Bytes 5–6 X
oa and {oa}$ units per unit base
This field specifies the units per unit base to be used to interpret bytes 7–10 and bytes 12–15
of the DOOC. For example, if the unit base is X'00' and this value is X'3840', there are 14,400
units per ten inches (1440 units per inch); in this case, the measurement units are called twips.
If an invalid or unsupported value is specified, exception ID X'0206..05' exists.
Bytes 7–8 Override for X
oa extent of the object area
This field specifies an override for the {oa}$ extent of the object area in L-units using the units of
measure specified in bytes 4–6.
X'FFFF' is a special value that specifies that the {oa}$ extent and the unit base and units per unit
base from the OCOC or IOC in the object are used for this parameter. If the optional OCOC on
the WOCC command (or the optional IOC on the WIC2 command) is omitted, the object's
presentation space X extent is used. The IDD specifies the presentation space size for an IO
Image; the object itself normally specifies the presentation space for an object container, or if
the object does not specify a presentation space size, the architected default is the
presentation space size of the underlying page or overlay.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists. [IPDS-10-106]

Bytes 9–10 Override for {oa}$ extent of the object area
This field specifies an override for the {oa}$ extent of the object area in L-units using the units of
measure specified in bytes 4–6.
X'FFFF' is a special value that specifies that the {oa}$ extent and the unit base and units per unit
base from the OCOC or IOC in the object are used for this parameter. If the optional OCOC on
the WOCC command (or the optional IOC on the WIC2 command) is omitted, the object's
presentation space Y extent is used. The IDD specifies the presentation space size for an IO
Image; the object itself normally specifies the presentation space for an object container, or if
the object does not specify a presentation space size, the architected default is the
presentation space size of the underlying page or overlay.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Byte 11 Override for mapping control option
This field specifies an override for the mapping control option that selects how the object's
presentation space is mapped to the output area. Resolution correction occurs whenever the
resolution of the object is different in one or both dimensions from the device resolution.
Note: For a UP3I Print Data object, the mapping option cannot be overridden in the IDO
command; this field is ignored by the printer when including a UP3I Print Data object.
Specify X'FF' for a UP3I Print Data object.
X'FF' is a special value that specifies that the mapping option from the OCOC or IOC in the
object is used. If the optional OCOC on the WOCC command (or the optional IOC on the
WIC2 command) is omitted, the architected default mapping option is one of the following:
• Scale to fit for an object container [IPDS-10-107]
• Position and trim for an IO Image [IPDS-10-108]
If an invalid or unsupported value is specified, exception ID X'0208..05' exists.
The option values supported for all data objects include:
• X'10'—Scale to fit [IPDS-10-109]
• X'20'—Center and trim [IPDS-10-110]
• X'30'—Position and trim [IPDS-10-111]
• X'60'—Scale to fill [IPDS-10-112]
Refer to “Mapping Control Options” or “Mapping the IO-Image Presentation
Space” for a description of these mapping control options.
The position mapping option (X'00') is supported for object containers, but not for IO Images;
refer to “Position Mapping” for a description of the position mapping option.
The option values supported only for IO Images include:
• X'41'—Point to pel [IPDS-10-113]
• X'42'—Point to pel with double dot [IPDS-10-114]
• X'50'—Replicate and trim [IPDS-10-115]
 for a description of these mapping control options. [IPDS-10-116]

Support within the IDO command for the following mapping control options is indicated as
follows:
• Point to pel (X'41') and point to pel with double dot (X'42') is indicated by property pair [IPDS-10-117]
X'1202' in the IO-Image command-set vector of an STM reply.
• Replicate and trim (X'50') is indicated by property pair X'F300' in the IO-Image command-set [IPDS-10-118]
vector of an STM reply.
• Scale to fill (X'60') is indicated by property pair X'F301' in the IO-Image command-set vector [IPDS-10-119]
of an STM reply or in the Object Container command-set vector of an STM reply.
Bytes 12–13 Override for {oa}$ offset from object area origin
This field specifies an override in L-units for the {oa}$ offset from the object area origin. The
units of measure used to interpret this offset are specified in bytes 4–6. The {oa}$ offset field is
ignored when the actual mapping option used is not position or position and trim.
Property pair X'1208' in the Object Container command-set vector of an STM reply indicates
support for negative object-area-offset values.
X'FFFF' is a special value that specifies that the {oa}$ offset and the unit base and units per unit
base from the OCOC or IOC in the object are used for this parameter. If the optional OCOC on
the WOCC command (or the optional IOC on the WIC2 command) is omitted and the position
or position-and-trim mapping option is actually used, the architected default X
oa offset is 0.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 Override for {oa}$ offset from object area origin
This field specifies an override in L-units for the {oa}$ offset from the object area origin. The units
of measure used to interpret this offset are specified in bytes 4–6. The {oa}$ offset field is
ignored when the actual mapping option used is not position or position and trim.
Property pair X'1208' in the Object Container command-set vector of an STM reply indicates
support for negative object-area-offset values.
X'FFFF' is a special value that specifies that the {oa}$ offset and the unit base and units per unit
base from the OCOC or IOC in the object are used for this parameter. If the optional OCOC on
the WOCC command (or the optional IOC on the WIC2 command) is omitted and the position
or position-and-trim mapping option is actually used, the architected default Y
oa offset is 0.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of DOOC
Optional override triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Include Data Object triplets are fully described in the triplets chapter:
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet” [IPDS-10-120]

Override for Presentation Space Reset Mixing (X'70') triplet
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'70' triplet is supported.
If one or more Presentation Space Reset Mixing (X'70') triplets are specified in the DOOC, the last of these
triplets overrides the last X'70' triplet in the object, and all other X'70' triplets in the DOOC are ignored. If there
is no X'70' triplet in the object, the override triplet is ignored.
Note: The Color Specification (X'4E') triplet has no meaning in the DOOC and is ignored if present.
Color Management Resources
All object-level CMRs specified on the home state WIC2 or WOCC command for this presentation data object
are ignored and the IDO CMRs, if any, are used as object-level CMRs. Also, if a Rendering Intent (X'95') triplet
is specified on the IDO command, it is used as the object-level rendering intent; this provides an override for a
Rendering Intent (X'95') triplet specified on the WIC2 or WOCC command.
When preRIPping IOCA or presentation data object resources using the RPO command, CMRs can be
invoked and a rendering intent can be specified with triplets on the RPO command or by use of the ICMR and
SPE commands. A preRIPped presentation object will be printed only if the CMRs and rendering intent used
during preRIP match those selected while processing the IDO command. Refer to the RPO command
description for a list of attributes used to find an appropriate preRIPped object.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet will be used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the IDO command. [IPDS-10-121]

Data Object Data Descriptor
The Data Object Data Descriptor (DODD) is the last self-defining field in the data portion of the IDO command.
This self-defining field specifies the HAID of a previously activated data object to be included in the current
page or overlay. In addition, triplets can be specified to provide overrides for the object's data descriptor.
The format of the DODD is as follows: [IPDS-10-122]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0016', X'0018' to end of DODD | Length of DODD, including this length field | X'0016', X'0018' to end of DODD [IPDS-10-123]|
| 2–3 | CODE | SDF ID | X'A6C3' | Self-defining-field ID | X'A6C3' [IPDS-10-124]|
| 4–19 | | Reserved | X'00...00' | Reserved | X'00...00' [IPDS-10-125]|
| 20–21 | CODE | HAID | X'0001'–X'7EFF' | Data object's Host-Assigned ID | X'0001'–X'7EFF' [IPDS-10-126]|
| 22 to end of DODD | Triplets | | Zero or more of the following triplets: | X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet [IPDS-10-127]| |
Bytes 0–1 Self-defining-field length
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Bytes 4–19 Reserved
Bytes 20–21 Host-Assigned ID
This field specifies the Host-Assigned ID (HAID) of the data object to be included in the current
page or overlay. Exception ID X'020D..11' exists if an invalid Host-Assigned ID value is
specified.
The data object must have been previously activated by an AR command, a home-state
WOCC command, or a home-state WIC2 command. Exception ID X'020D..15' exists if the
data object identified by the HAID has not been activated.
Exception ID X'020D..13' exists if the data object type is not valid for the IDO command. The
data object types that can be included with an IDO command are:
• EPS (Encapsulated PostScript) with transparency [IPDS-10-128]
• EPS without transparency [IPDS-10-129]
• GIF (Graphics Interchange Format) [IPDS-10-130]
• IOCA (Image Object Content Architecture) image [IPDS-10-131]
• JPEG (Joint Photographic Experts Group) AFPC JPEG Subset [IPDS-10-132]
• JP2 (JPEG2000 File Format) [IPDS-10-133]
• PCL (Printer Command Language) page object [IPDS-10-134]
• PDF (Portable Document Format) multiple-page file with transparency [IPDS-10-135]
• PDF multiple-page file without transparency [IPDS-10-136]
• PDF single page with transparency [IPDS-10-137]
• PDF single page without transparency [IPDS-10-138]
• PNG (Portable Network Graphics) AFPC PNG Subset [IPDS-10-139]
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-10-140]

• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-10-141]
• TIFF with transparency [IPDS-10-142]
• TIFF without transparency [IPDS-10-143]
• TIFF multiple-image file with transparency [IPDS-10-144]
• TIFF multiple-image file without transparency [IPDS-10-145]
• UP3I print data [IPDS-10-146]
For UP3I Print Data objects, if a post-processing printer cannot be found that supports the
Print Data Format ID (bytes 3–6 of the UP3I Print Data (UP3I value X'04') triplet) specified in a
UP3I Print Data object, exception ID X'027E..00' exists.
Note: All necessary secondary resources must also have been previously activated and must
be identified in a prior DORE or DORE2 command within the page or overlay.
Bytes 22 to
end of DODD
Optional override triplets
This portion of the DODD contains zero or more triplets that contain override information for
the object's data descriptor.
Printers ignore any triplet that is not supported and no exception is reported. If byte 22 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Include Data Object triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Object Offset (X'5A') Triplet”
“Image Resolution (X'9A') Triplet”
“Object Container Presentation Space Size (X'9C') Triplet”
Override for Color Specification (X'4E') Triplet
The Color Specification (X'4E') triplet specifies an override color value for an included bilevel or grayscale
image object. This triplet is used only for bilevel IO-Image objects and for object-container objects that contain
bilevel or grayscale image, and is ignored for all other object types. The specific object types that can contain
bilevel or grayscale image are identified in the MO:DCA object-type OID registry.
Only one Color Specification (X'4E') triplet should be specified in the command. If multiple Color Specification
(X'4E') triplets are specified, the last one is used and the others are ignored. If a Color Specification (X'4E')
triplet is not specified on the command, the printer uses the color specification, if any, from the WOCC or WIC2
command. It is recommended that a Color Specification (X'4E') triplet be provided on the command since the
application program might not know what controls were saved with a downloaded or captured resource.
• For bilevel IO-Image objects: [IPDS-10-147]
– Printers that support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use the Color
Specification (X'4E') triplet to override either the Set Bilevel Image Color (X'F6') IOCA self-defining field or
the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field in an IO-Image object, or to set the
image color if neither IOCA self-defining field is specified in the object. Property pair X'4401' in the IO-
Image command-set vector of an STM reply indicates that the Set Extended Bilevel Image Color (X'F4')
IOCA self-defining field is supported.
– Printers that do not support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use a
restricted form of the X'4E' triplet to override the Set Bilevel Image Color (X'F6') IOCA self-defining field in
an IO-Image object, or to set the image color if no X'F6' IOCA self-defining field is specified in the object. In
this case, the triplet must specify the Standard OCA color space (X'40') or the triplet is ignored.
• The Color Specification (X'4E') triplet is not used with grayscale IO-Image objects. [IPDS-10-148]
• For object-container objects that contain bilevel image but do not specify an internal color value, the Color [IPDS-10-149]
Specification (X'4E') triplet specifies the color of the bilevel image. Note that 1-bit indexed color is considered
to be bilevel. Some presentation object containers can specify color by constructs within the object and, if [IPDS-10-150]

present, these color specifications are used instead of the Color Specification (X'4E') triplet. The color
specified in the Color Specification (X'4E') triplet is a default color and does not override a non-default color
specified within the object data.
• For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a [IPDS-10-151]
color that is used in place of black when rendering the image. For example, when the color is brown the
image is produced in “brownscale” rather than grayscale (this is commonly called sepia tone in photography
and produces a softer look than would grayscale). The intensity of the color for each image point is
determined by the color space and the grayscale level in the image, as follows:
RGB 0% = color of medium
100% = specified RGB value
CMYK 0% = color of medium
100% = specified CMYK value
Highlight 0% = color of medium
100% = specified highlight value
ignore the percent-coverage and percent-shading parameters
CIELAB 0% = color of medium
100% = specified CIELAB value
Standard OCA 0% = color of medium
100% = OCA color (or RGB value from table)
Property pair X'5801' in the Object Container command-set vector of an STM reply indicates that the printer
provides bilevel and grayscale image color support for object containers.
Support for IO-Image color on the IDO-DODD command is indicated by the following STM property pairs (must
have X'1201', X'1202', and at least one of the X'4xxx' property pairs listed):
• Property pair X'1201' in the Object Container command-set vector indicates support for the IDO command [IPDS-10-152]
(and for the X'4E' triplet in the IDO-DODD)
• Property pair X'1202' in the IO-Image command-set vector indicates support for IO-Image objects as [IPDS-10-153]
resources
• Property pair X'40nn' in the IO-Image command-set vector with any defined nn bit set to B'1' indicates [IPDS-10-154]
support for the Set Bilevel Image Color (X'F6') IOCA self-defining field
• Property pair X'4401' in the IO-Image command-set vector indicates support for the Set Extended Bilevel [IPDS-10-155]
Image Color (X'F4') IOCA self-defining field
Notes:
1. When specified in the DODD, the X'4E' triplet is used to override the default color of a bilevel or grayscale [IPDS-10-156]
image; it is not used to specify an area color.
2. Color Specification (X'4E') triplets that are ignored may be syntax checked before they are ignored. [IPDS-10-157]

Override for Object Offset (X'5A') Triplet
When processing a multi-page resource object (such as a multi-page PDF file or multi-image TIFF file), the
printer uses an Object Offset (X'5A') triplet, to select a single paginated object within the file. The Object Offset
(X'5A') triplet is only used with multi-page objects; the triplet is ignored if specified with any other object type.
An Object Offset (X'5A') triplet specified in the IDO-DODD command overrides Object Offset (X'5A') triplets in
the WOCC-OCDD command. If more than one Object Offset (X'5A') triplet is specified in the IDO command,
the last one is used and the others are ignored. If there are no Object Offset (X'5A') triplets specified in the IDO
command, the last specified Object Offset (X'5A') triplet in the WOCC command is used. However, if no X'5A'
triplets have been specified in either command, the first paginated object within the file is selected.
Printer support for the Object Offset (X'5A') triplet is indicated by the presence of at least one multi-page-file or
multi-image-file resource object OID in the Object-Container Type Support self-defining field of an XOH-OPC
reply.
Override for Image Resolution (X'9A') Triplet
An Image Resolution (X'9A') triplet can be specified in the IDO-DODD self-defining field to specify an image
resolution for an included presentation object; this triplet overrides all Image Resolution (X'9A') triplets
specified in the WOCC-OCDD for the presentation object. Property pair X'5800' in the Object Container
command-set vector of an STM reply indicates that the printer supports the Image Resolution (X'9A') triplet.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates support for all
architected units of measure in the Image Resolution (X'9A') triplet.
An Image Resolution (X'9A') triplet can be used for some object types (such as TIFF , GIF , JPEG, and
JPEG2000) to identify the raster image resolution used within the object. This triplet is not used with IOCA
images and, if specified for an IOCA image, the triplet is ignored. If more than one Image Resolution (X'9A')
triplet is specified, the last one is used and the others are ignored.
If an image resolution is needed to process the object and the triplet is specified, the resolution specified within
the triplet is used and overrides the resolution inside the image, if any. However, if an image resolution is not
needed to process the object, the triplet is ignored. If an image resolution is needed to process the object, but
is not provided at all, the printer assumes that the image resolution is the same as the device resolution and
exception ID X'020D..07' exists.
If no Image Resolution (X'9A') triplets are specified on the IDO command, the printer uses the X'9A' triplet, if
any, from the WOCC command. [IPDS-10-158]

Override for Object Container Presentation Space Size (X'9C') Triplet
An Object Container Presentation Space Size (X'9C') triplet can be specified in the IDO-DODD self-defining
field to specify the presentation space size for an included PDF or SVG object; this triplet overrides the
corresponding Object Container Presentation Space Size (X'9C') triplet (if any) specified in the WOCC-OCDD
for the presentation object.
Only one Object Container Presentation Space Size (X'9C') triplet should be specified for a PDF or SVG
object. This triplet is not used with other object types and, if specified for an object type other than PDF or
SVG, the triplet is ignored. If multiple Object Container Presentation Space Size (X'9C') triplets are specified,
the last one is used and the others are ignored.
If an Object Container Presentation Space Size (X'9C') triplet is not specified on the IDO command, the printer
uses the X'9C' triplet, if any, from the WOCC command.
Property pair X'1203' in the Object Container command-set vector of an STM reply indicates that the printer
supports the Object Container Presentation Space Size (X'9C') triplet for a PDF object. STM property pair
X'1209' in the Object Container command-set vector indicates that the printer supports the Object Container
Presentation Space Size (X'9C') triplet for an SVG object. [IPDS-10-159]

Remove Resident Resource
The Remove Resident Resource (RRR) command is valid only in home state and causes the printer to remove
a deactivated resident resource from printer storage. Resident resources can be installed and removed
manually by an operator when the printer is offline to the host. Also, resource management by print-server
software can be done using the capture function of the AR command to install resources and the Remove
Resident Resource command to remove unneeded resident resources. The following resource types can be
removed with this command:
• Data object resource [IPDS-10-160]
• Data-object-font component [IPDS-10-161]
Note: These resource types are listed in Table 16 and Table 17 and cover all resident
resources that are identified by OID including IO-Image resources and object-container resources (note
that setup files are not resources).
Once a resident resource has been listed in an XOA-RRL reply, the resource cannot be removed during the
IPDS session without the use of the Remove Resident Resource (RRR) command. Therefore, after issuing an
RRR command, a presentation services program that uses a general list of these resources obtained from an
XOA-RRL reply should either update the list or obtain a new XOA-RRL reply.
The command can request the removal of all resident resources by simply not providing any data; in this case,
the command length is either 5 or 7 depending on whether a correlation ID is used. The printer will remove all
non-activated resources that can be removed; the printer implementation can choose whether or not each
resource is removable.
Note: Some resident resources cannot be removed with this command; for example, resources that are used
as printer-default resources cannot be removed. Also, some printers do not allow certain resident
resources to be removed. Exception ID X'020D..31' exists if the command contains an object OID, but
the requested resource cannot be removed.
This is an optional command that is not supported by all IPDS printers. Support for the Remove Resident
Resource command is indicated with property pair X'1204' in the STM Object Container command-set vector.
```
Length X'D65A' Flag CID Data
```
The length of the RRR command can be:
Without CID X'0005' or X'0007'–X'0086'
With CID X'0007' or X'0009'–X'0088'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The format of the data field for this command is as follows: [IPDS-10-162]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Zero or one Object OIDs in the following format:** [IPDS-10-163]| | | | | |
| 0 | CODE | Identifier | X'06' | This is a definite-short-form OID | X'06' [IPDS-10-164]|
| 1 | UBIN | Length | X'00'–X'7F' | Length of the following content bytes | X'00'–X'7F' [IPDS-10-165]|
| 2 to end of OID | Content | | any value | Content bytes that provide a unique ID for this object | any value [IPDS-10-166]|
Remove Resident Resource [IPDS-10-167]


Bytes 0 to
end of OID
Object OID
This parameter contains the unique resource ID (object OID) for the resource to be removed.
The format of an Object OID is described. If the object OID is invalid, exception
ID X'020D..32' exists.
If the requested resource is not found, this command is treated as a NOP. If the requested
resource is currently activated, exception ID X'020D..30' exists.
Remove Resident Resource [IPDS-10-168]


Request Resident Resource List
Length X'D659' Flag CID
The length of the RRRL command can be:
Without CID X'0005'
With CID X'0007'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Request Resident Resource List (RRRL) command is valid only in home state and causes the printer to
return a list of resident resources containing additional information about these resources that is useful for
managing printer-resident resources. When the Acknowledgment Required (ARQ) flag is B'1', the printer
returns this information to the host in the special data area of one or more Acknowledge Replies to the RRRL
command; however, when the Acknowledgment Required (ARQ) flag is B'0', the command is treated as a No
Operation (NOP) command. No data is transmitted with this command.
The list of resources that is returned consists of an entry for each resident resource that is identified with an
object OID. There is no set order for the entries and this can be a long list. The following resource types are
listed:
• Data object resource [IPDS-10-169]
• Data-object-font component [IPDS-10-170]
Notes:
1. These resource types are listed in Table 16 and Table 17 and cover all resident [IPDS-10-171]
resources that are identified by OID including IO-Image resources and object-container resources (note
that setup files are not resources).
2. Unlike the XOA-RRL command, deactivated resident resources that are listed in an RRRL reply can be [IPDS-10-172]
removed by the printer at any time (perhaps to make room to capture a new resource). There are three
actors that can remove resident resources: the printer itself, the console (possibly remote console), and the
print server; since these are independent of each other, the RRRL reply is only a snapshot in time.
This is an optional command that is not supported by all IPDS printers. Support for the Request Resident
Resource List command is indicated with property pair X'1205' in the STM Object Container command-set
vector.
Request Resident Resource List [IPDS-10-173]


Acknowledge Reply for Request Resident Resource List
Length X'D6FF' Flag CID Type—Page and Copy Counters—Special Data Area
The format of the Special Data Area for the reply is as follows: [IPDS-10-174]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| **Zero or more RRRL-reply entries in the following format:** [IPDS-10-175]| | | | |
| 0–1 | UBIN | Entry Length | X'0010'–X'0193' | Length of RRRL-reply entry, including this length field [IPDS-10-176]|
| **Object OID** [IPDS-10-177]| | | | |
| 2 | CODE | Identifier | X'06' | This is a definite-short-form OID [IPDS-10-178]|
| 3 | UBIN | OID length | X'00'–X'7F' | Length of the following content bytes [IPDS-10-179]|
| 4 to end of OID | Content | | any value | Content bytes that provide a unique ID for this object [IPDS-10-180]|
| **Object information** [IPDS-10-181]| | | | |
| +0–7 | UBIN | Object type | X'00...00'–X'FF...FF' | Component ID of a registered object-type OID [IPDS-10-182]|
| +8–9 | BITS | Object information flags [IPDS-10-183]| | |
| | bit 0 | Removable | B'1'<br>B'0' | B'1' The object is removable<br>B'0' The object is not removable [IPDS-10-184]|
| | bits 1–15 | | B'0...0' | Reserved [IPDS-10-185]|
| +10–11 | | Reserved | X'0000' | Reserved [IPDS-10-186]|
| **Zero, one, or two human-readable name triplets** [IPDS-10-187]| | | | |
| +12 to end of entry | Triplets | | See byte description | If triplets are available there can either be a X'01' triplet followed by a X'02' triplet, or a single X'02' triplet. The X'02' triplet must have FQN type X'DE' with a character-encoded name.<br>X'01' CGCSGID triplet<br>X'02' Fully Qualified Name triplet [IPDS-10-188]|
Bytes 0–1 Entry length
This parameter contains the length of the RRRL-reply entry, including the length field itself.
Bytes 2 to
end of OID
Object OID
This parameter contains the unique resource ID (object OID) for a resident resource. The
format of an Object OID is described.
Bytes + 0–7 Object type
This parameter contains the component ID of a registered object-type OID. The component ID
is the last 8 bytes of a registered object-type OID listed in Table 17 or it is the value
X'FFFFFFFFFFFFFFFF' to indicate an IO Image object.
Request Resident Resource List [IPDS-10-189]


Bytes + 8–9 Object information flags
This parameter contains object information flags:
Bit 0 Removable flag
A value of B'1' indicates that this object can be removed with the Remove
Resident Resource (RRR) command. A value of B'0' indicates that this object
is not removable.
When this flag is B'0', an attempt to remove this object with the RRR
command will fail with exception ID X'020D..31'.
Bits 1–15 Reserved
Bytes + 10–11 Reserved
Bytes + 12 to
end of entry
Triplets
This parameter contains a human-readable name for the object (carried in triplets); there are
three possibilities:
1. A Fully Qualified Name (X'02') triplet with FQN type X'DE' and with a character-encoded [IPDS-10-190]
name
2. Two triplets: a Coded Graphic Character Set Global Identifier (X'01') triplet to identify an [IPDS-10-191]
encoding scheme followed by a Fully Qualified Name (X'02') triplet with FQN type X'DE'
and with a character-encoded name
3. No triplets (used when there is no human-readable name available) [IPDS-10-192]
A Coded Graphic Character Set Global Identifier (X'01') triplet applies only to the entry in
which it is specified. When an entry contains a Fully Qualified Name (X'02') triplet, but no X'01'
triplet, the FQN is a UTF-16BE character string.
Request Resident Resource List [IPDS-10-193]


Write Object Container Control
Length X'D63C' Flag CID Data (OCAP, OCOC, OCDD)
The length of the WOCC command can be:
Without CID X'001B' or X'001D'–X'7FFF'
With CID X'001D' or X'001F'–X'7FFF'
However, each self-defining-field length and triplet length must also be valid. Exception ID X'0202..02' exists if
the command length is invalid or unsupported.
The Write Object Container Control (WOCC) command causes the printer to enter object-container-resource ,
page-object-container, or overlay-object-container state. The type of object is specified as a registered object-
type OID in the OCDD portion of the command. For presentation objects, the parameters of this command
define, position, and orient the object container object area, map the object container presentation space into
the object container object area, and establish the initial conditions for interpreting the object container data. If
not enough data is specified, exception ID X'0205..01' exists.
The WOCC command is followed by zero or more Write Object Container (WOC) commands. Object container
data processing ends when the printer receives an End command in the object container state. An empty
object container consists of a WOCC command followed by an End command with no intervening WOC
commands; an empty object container can be used to color or shade a rectangular area (the object area)
without presenting any data within the area. However, if the object contains some WOC commands all of which
are empty and this type of object requires some data, the object container is not considered to be empty and
exception ID X'020D..01' or X'020D..05' exists.
To associate metadata with the object container, one or more metadata objects can immediately follow the
WOCC command, before any other commands. Each Write Metadata Control (WMC) command causes the
printer to enter metadata state, where exactly one metadata object is included. Metadata state ends when the
printer receives the End command, at which point the printer returns to the object container state it was in
when the WMC was received.
The WOCC data field consists of one, two, or three consecutive self-defining fields in the following order:
1. Object container Area Position (OCAP), optional [IPDS-10-194]
2. Object container Output Control (OCOC), optional [IPDS-10-195]
3. Object container Data Descriptor (OCDD) [IPDS-10-196]
Note: If a presentation object is to be captured by the printer, it is recommended that the presentation services
program omit the OCAP and OCOC from the WOCC command so that the object is captured with
known defaults for each of these controls. It is also recommended that the IDO command used to
include the object always provide overrides to ensure that the desired controls are used; if overrides are
not supplied on the IDO command, the OCAP and OCOC values (or defaults) from the WOCC
command are used. Since the application program cannot know what controls were saved when the
resource was captured, the application program cannot know for certain what these WOCC (or WIC2)
values might be at print time.
Each self-defining field contains a two-byte length field, a two-byte self-defining field ID, and a data field.
If an invalid self-defining field is specified, a self-defining field is out of order, a required self-defining field is not
specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists.
Each self-defining field contains a two-byte length field, a two-byte self-defining field ID, and a data field. [IPDS-10-197]

Object Container Area Position
The Object Container Area Position (OCAP) self-defining field, if present, is the first self-defining field in the
data portion of the WOCC command. This field defines the position and orientation of the object container
object area. The origin and the orientation of the object container object area is defined relative to the
reference coordinate system.
This self-defining field is optional and can be omitted from the WOCC command. It is ignored for non-
presentation object containers. For presentation object containers, if the OCAP field is omitted, the default
values are as follows:
Object area origin X
p = 0, $ = 0
Object area orientation 0°
Reference coordinate system Page $,$
The format of the OCAP is as follows: [IPDS-10-198]

| Offset | Type | Name | Range | Meaning | OC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'000B' to end of OCAP | Length of OCAP, including this length field | X'000B' to end of OCAP [IPDS-10-199]|
| 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' [IPDS-10-200]|
| 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Object container object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' (Refer to the note following the table.) [IPDS-10-201]|
| 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Object container object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' (Refer to the note following the table.) [IPDS-10-202]|
| 8–9 | CODE | Object Container object area orientation [IPDS-10-203]| | | |
| | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' [IPDS-10-204]|
| | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' [IPDS-10-205]|
| | bit 15 | | B'0' | Reserved | B'0' [IPDS-10-206]|
| 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>X'00' Absolute $I$, absolute $B$<br>X'20' Absolute $I$, relative $B$<br>X'40' Relative $I$, absolute $B$<br>X'60' Relative $I$, relative $B$<br>X'A0' Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' [IPDS-10-207]|
| 11 to end of OCAP | UNDF | | | Data without architectural definition [IPDS-10-208]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. [IPDS-10-209]

Bytes 0–1 Self-defining-field length. Bytes after byte 10 are ignored by the printer.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Object container object area origin X offset in L-units
These bytes specify the object container object area origin (top-left corner) as an X
p, I, or I-
offset coordinate position. The units of measure used to interpret this L-unit value are
specified in the LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Bytes 6–7 Object container object area origin Y offset in L-units
These bytes specify the object container object area origin (top-left corner) as a Y
p, B, or B-
offset coordinate position. The units of measure used to interpret this L-unit value are
specified in the LPD command that is current when this object is printed in a page or overlay.
Exception ID X'0860..00' exists if the position cannot be represented by the printer.
Note: The current text presentation coordinate (I
c, $) is not changed by the printing of this
object.
Bytes 8–9 Orientation of object container object area
This two-byte parameter specifies the orientation of the object container object area, that is,
the {oa}$ axis of the object container object area, in terms of an angle measured clockwise from
the $ or I coordinate axis. This parameter rotates the object container object area around the
origin specified in bytes 4–7. The object container picture presented in the object area is
aligned such that the positive X
oc axis of the object container presentation space is parallel to,
and in the same direction as, the positive {oa}$ axis of the object container object area. The
positive {oa}$ axis of the object container object area is rotated 90 degrees clockwise relative to
the positive {oa}$ axis and is in the same direction as the positive {oc}$ axis. This parameter has
no effect on the I-axis orientation or the B-axis orientation.
The object area orientation is specified in terms of a number of degrees and a number of
minutes.
The number of degrees in the orientation is given in bits 0–8 of this two-byte parameter.
Values from 0 (B'000000000') to 359 (B'101100111') degrees are valid. Exception ID
X'0203..05' exists if a value from 360 to 511 is received.
The number of minutes in the orientation is given in bits 9–14 of this two-byte parameter.
Values from 0 (B'000000') to 59 (B'111011') minutes are valid. Exception ID X'0203..05' exists
if a value from 60 to 63 is received.
Not all printers support orientation values other than 0 degrees; the X'A0nn' property pair in
the Object Container command-set vector in the STM reply reports the orientation support of
the printer. Exception ID X'0203..05' exists if the printer does not support the requested
orientation value.
For reference, the four basic orientation values correspond to the following hexadecimal and
binary values of these two bytes:
0 degrees [IPDS-10-210]
90 degrees [IPDS-10-211]
180 degrees [IPDS-10-212]
270 degrees [IPDS-10-213]
X'0000'
X'2D00'
X'5A00'
X'8700'
B'000000000 000000 0'
B'001011010 000000 0'
B'010110100 000000 0'
B'100001110 000000 0'
Byte 10 Reference coordinate system
The reference coordinate system determines the origin and orientation of the object container
object area, using either the $,$ or the inline-baseline (I,B) coordinate system. [IPDS-10-214]

An inline coordinate value specified as absolute means that the value in bytes 4–5 of the
OCAP is at an absolute inline coordinate location; that is, bytes 4–5 are offset from the I
system origin. A baseline coordinate value specified as absolute means that the value in bytes
6–7 is specified at an absolute baseline coordinate location; that is, bytes 6–7 are offset from
the B system origin.
An inline coordinate value specified as relative means that the value in bytes 4–5 is an offset
from the current inline coordinate location. A baseline coordinate value specified as relative
means that the value in bytes 6–7 is an offset from the current baseline coordinate location.
Therefore, the following rules apply:
• If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. [IPDS-10-215]
Bytes 4–5 specify the text inline coordinate; bytes 6–7 specify the text baseline coordinate.
• If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the [IPDS-10-216]
origin. Bytes 4–5 specify the text inline coordinate; bytes 6–7 are added to the current text
baseline coordinate.
• If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the [IPDS-10-217]
origin. Bytes 4–5 are added to the current text inline coordinate; bytes 6–7 specify the text
baseline coordinate.
• If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. [IPDS-10-218]
Bytes 4–5 are added to the current text inline coordinate; bytes 6–7 are added to the current
text baseline coordinate.
• If byte 10 equals X'A0', the current logical page X [IPDS-10-219]
p and $ coordinates determine the origin.
When the object area is within a page, OCAP bytes 4–7 specify the offset from the $-
coordinate and $-coordinate origin specified in a previously received LPP command (or
from the printer default coordinates if no LPP command was received). When the object
area is within an overlay that is invoked using an LCC command, OCAP bytes 4–7 specify
the offset from the X
m-coordinate and $-coordinate origin. When the object area is within
an overlay that is invoked using an IO command, OCAP bytes 4–7 specify the offset from
the X
p-coordinate and $-coordinate origin specified in the IO command.
If an invalid or unsupported value is specified, exception ID X'0204..05' exists.
Bytes 11 to
end of OCAP
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-10-220]

Object Container Output Control
The Object Container Output Control (OCOC), if present, is the next self-defining field in the data portion of the
WOCC command. This self-defining field specifies the size of the object container object area, in addition to
the mapping option for the object container presentation space.
This self-defining field is optional and can be omitted from the WOCC command. It is ignored for non-
presentation object containers. For presentation object containers, if the OCOC field is omitted, the default
values are as follows:
• The object area extent equals the size of the logical page; [IPDS-10-221]
X
oa extent = $ extent, {oa}$ extent = $ extent.
• The scale-to-fit mapping control is used for all objects except for the following: [IPDS-10-222]
– For UP3I Print Data objects, the UP3I-Print-Data mapping control is used.
• The object area is not colored. [IPDS-10-223]
• No object-level CMRs. [IPDS-10-224]
• No object-level rendering intent. [IPDS-10-225]
The format of the OCOC is as follows: [IPDS-10-226]

| Offset | Type | Name | Range | Meaning | OC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0010', X'0012' to end of OCOC | Length of OCOC, including this length field | X'0010', X'0012' to end of OCOC [IPDS-10-227]|
| 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' [IPDS-10-228]|
| 4 | CODE | Unit base | X'00'<br>X'01' | X'00' Ten inches<br>X'01' Ten centimeters | X'00' [IPDS-10-229]|
| 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' [IPDS-10-230]|
| 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use the LPD value) | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [IPDS-10-231]|
| 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use the LPD value) | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [IPDS-10-232]|
| 11 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'60'<br>X'70' | Mapping control option:<br>X'00' Position<br>X'10' Scale to fit<br>X'20' Center and trim<br>X'30' Position and trim<br>X'60' Scale to fill<br>X'70' UP3I Print Data | X'00'<br>X'10'<br>X'20'<br>X'30' [IPDS-10-233]|
| 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units; (for the position and position-and-trim mappings only) | X'0000'–X'7FFF' (Refer to the note following the table.) [IPDS-10-234]|
| 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units; (for the position and position-and-trim mappings only) | X'0000'–X'7FFF' (Refer to the note following the table.) [IPDS-10-235]|
| 16 to end of OCOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets: | X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-10-236]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Self-defining-field length.
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Byte 4 Unit base
A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates that the
unit base is ten centimeters.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
If an invalid or unsupported value is specified, exception ID X'0205..05' exists.
Bytes 5–6 {oa}$ and {oa}$ units per unit base
These bytes specify the number of units per unit base used when specifying the object area
extent or object area offset in either the X or the Y direction. For example, if the unit base is
X'00' and this value is X'3840', there are 14,400 units per ten inches (1440 units per inch).
If an invalid or unsupported value is specified, exception ID X'0206..05' exists.
Bytes 7–8 X
oa extent of object area in L-units
These bytes specify the {oa}$ extent of the object container object area in L-units using the units
of measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $ extent
and the $ unit base and units per unit base of the LPD command that is current when this
object is printed in a page or overlay.
Note: For the duration of an overlay, the LPD associated with that overlay defines the current
logical page.
If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Bytes 9–10 {oa}$ extent of object area in L-units
These bytes specify the {oa}$ extent of the object container object area in L-units using the units
of measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $ extent
and the $ unit base and units and units per unit base of the LPD command that is current
when this object is printed in a page or overlay. [IPDS-10-237]

If an invalid or unsupported value is specified, exception ID X'0207..05' exists.
Byte 11 Mapping control option
This byte specifies how the object container presentation space is mapped to the object
container output area. Resolution correction occurs whenever the resolution of the object
container is different in one or both dimensions from the device resolution. The option values
are:
• X'00'—Position [IPDS-10-238]
• X'10'—Scale to fit [IPDS-10-239]
• X'20'—Center and trim [IPDS-10-240]
• X'30'—Position and trim [IPDS-10-241]
• X'60'—Scale to fill [IPDS-10-242]
• X'70'—UP3I print data [IPDS-10-243]
Refer to “Mapping Control Options” for a description of the mapping control
options.
If an invalid or unsupported value is specified, exception ID X'0208..05' exists.
Bytes 12–13 {oa}$ offset in L-units from object area origin
The {oa}$ offset field is ignored unless byte 11 contains X'00' or X'30'. This value is the {oa}$ offset
of the object container presentation space (top-left corner) from the origin of the object
container object area. The units of measure used to interpret this offset are specified in bytes
4–6.
Property pair X'1208' in the Object Container command-set vector of an STM reply indicates
support for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 14–15 Y
oa offset in L-units from object area origin
The {oa}$ offset field is ignored unless byte 11 contains X'00' or X'30'. This value is the {oa}$ offset
of the object container presentation space (top-left corner) from the origin of the object
container object area. The units of measure used to interpret this offset are specified in bytes
4–6.
Property pair X'1208' in the Object Container command-set vector of an STM reply indicates
support for negative object-area-offset values.
If an unsupported value is specified, exception ID X'0209..05' exists.
Bytes 16 to
end of OCOC
Optional triplets
This field can contain zero or more triplets. Support for each triplet is indicated by a property
pair that is returned in a Sense Type and Model command reply.
Printers ignore any triplet that is not supported and no exception is reported. If byte 16 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
The Write Object Container Control triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet” [IPDS-10-244]

Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported.
The Color Specification (X'4E') triplet and the Presentation Space Reset Mixing (X'70') triplet allow control over
the color of the object container object area before any object data is placed in the object area. The color of the
object data can be specified by constructs within the object or within the WOCC-OCDD.
Triplets that affect the color of the object area are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium.
UP3I Print Data objects are presented by a pre-processing or post-processing device and do not directly mix
with other IPDS data; this type of object uses the UP3I-Print-Data mapping control option, that is described in
“UP3I-Print-Data Mapping”. However, object area coloring for UP3I Print Data objects is handled
by the IPDS printer in the same manner as for all other object containers.
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
The invoked CMRs and the specified object-container rendering intent are associated only with this
presentation object container and are used according to the CMR-usage hierarchy. Refer to “CMR-Usage
Hierarchy” for a description of the hierarchy. Invoke CMR (X'92') triplets on the WOCC command
are not used with data object resources that are included with an IDO command; therefore, these triplets need
not be kept with a presentation object that is downloaded as a resource in home state (whether or not the
resource is captured). Because Invoke CMR (X'92')
triplets contain a HAID, these triplets should not be stored
with a captured resource.
When preRIPping presentation data object resources using the RPO command, CMRs can be invoked and a
rendering intent can be specified with triplets on the RPO command or by use of the ICMR and SPE
commands. A preRIPped presentation object will be printed only if the CMRs and rendering intent used during
preRIP match those selected while processing the IDO command. Refer to the RPO command description for
a list of attributes used to find an appropriate preRIPped object.
When printing a presentation object-container image object in conjunction with a QR Code with Image bar
code, data-object-level CMRs can be invoked for the image object using the Invoke Tertiary Resource (X'A2')
triplet on the WBCC command for the bar code. In addition, CMRs invoked via the Invoke CMR (X'92') triplet
on the WBCC are also invoked at the data-object-level for such image objects—that is, the CMRs invoked for
the bar code itself are also invoked for any secondary resource image objects of the bar code. Data-object-
level CMRs invoked via the Invoke Tertiary Resource (X'A2') triplet take precedence over data-object-level
CMRs invoked via the Invoke CMR (X'92') triplet on the WBCC. A rendering intent specified using the
Rendering Intent (X'95') triplet on the WOCC is used as the data-object-level rendering intent for presentation
object-container image objects printed in conjunction with a QR Code with Image bar code.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet will be used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the WOCC command. The X'F212' property pair in
the Device-Control command-set vector of an STM reply indicates support for Invoke Tertiary Resource (X'A2')
triplets in the WBCC command. [IPDS-10-245]

Mapping Control Options
Object container mapping control options are defined as follows:
Scale-to-Fit Mapping
The center of the object container presentation space is mapped to the center of the object container object
area. Object Container data is uniformly scaled by the printer, so that the picture within the object container
presentation space fits entirely within the object container object area at the maximum size. The scale factor
chosen to generate this maximum fit is applied equally along both dimensions of the picture so that the aspect
ratio of the picture in the object container object area is the same as the aspect ratio of the picture in the object
container presentation space.
This option ensures that all of the data in the object container presentation space is presented in the object
container object area at the largest size possible without picture distortion.
Figure 96 shows the result of scale-to-fit mapping. In this example, the object container object area is smaller
than the object container presentation space; therefore, the object container presentation space is
proportionally condensed to fit into the object container object area. That is, the entire object container data
contained within the object container presentation space is condensed uniformly until one dimension matches
that of the object container object area.
Figure 96. An Example of Object Container Scale-to-Fit Mapping
Object Container Presentation Space
Scale-to-fit
mapping specified
in the Object Container [IPDS-10-246]

Object Container Object Area
Logical Page

Center-and-Trim Mapping
The center of the object container presentation space is mapped to the center of the object container object
area. The object container data is presented at the size specified in the OCDD self-defining field. As a result,
the size and aspect ratio of the picture in the object container object area is the same as the size and aspect
ratio of the picture in the object container presentation space. Any portion of the object container presentation
space that falls outside the object container object area is trimmed to the object container object area
boundaries. This type of trimming does not cause an exception.
Figure 97 shows the result of center-and-trim mapping. In this example, the object container object area is
smaller in both dimensions than the object container presentation space; therefore, the object container
presentation space is trimmed. The center of the object container presentation space coincides with the center
of the object container object area.
Figure 97. Example of Object Container Center-and-Trim Mapping
Object Container Presentation Space
Center-and-trim
mapping specified
in the Object Container [IPDS-10-247]

Object Container Object Area
Logical Page

Position-and-Trim Mapping
The top-left corner of the object container presentation space is mapped to the object container object area,
using the specified offset from the object container object area origin. It is presented at the size specified in the
OCDD self-defining field. As a result, the size and aspect ratio of the picture in the object container object area
is the same as the size and aspect ratio of the picture in the object container presentation space. Any portion of
the object container presentation space that falls outside the object container object area is trimmed to the
object container object area boundaries. This type of trimming does not cause an exception.
Figure 98 shows the result of position-and-trim mapping. In this example, the right and bottom edges of the
object container presentation space fall outside the object area and, therefore, are trimmed. The top-left corner
of the object container presentation space is offset from the origin of the object container object area by a
distance specified in the OCOC self-defining field.
Figure 98. Example of Object Container Position-and-Trim Mapping
Object Container Presentation Space
Position-and-trim
mapping specified
in the Object Container [IPDS-10-248]

Object Container Object Area
Logical Page
Y offset specified in OCOC
X offset specified in OCOC [IPDS-10-249]

Position Mapping
This mapping option is identical to the position-and-trim mapping option except that any data within the object
container presentation space that falls outside the object container object area causes exception ID
X'020D..06' to exist.
Scale-to-Fill Mapping
The center of the object container presentation space is mapped to the center of the object container object
area, and the object container presentation space is scaled independently in the X and Y dimensions to fill the
object container object area. The scale factor chosen to generate this maximum fit can be different in X and Y
dimensions and therefore the aspect ratio is not necessarily preserved by the scale-to-fill mapping.
Note: Not all printers support the scale-to-fill mapping option; the X'F301' property pair is returned in the Object
Container command-set vector of an STM reply by those printers that do support the mapping option.
Figure 99. Example of Object Container Scale-to-Fill Mapping
Object Container Presentation Space
Logical Page
Scale-to-fill
mapping specified
in the Object Container [IPDS-10-250]

Object Container Object Area [IPDS-10-251]

UP3I-Print-Data Mapping
This mapping option is only used for UP3I Print Data objects; if specified for any other type of data object,
exception ID X'0208..05' exists.
UP3I Print Data objects are presented by a pre-processing or post-processing device and do not directly mix
with other IPDS data. The data printed by the IPDS printer is mixed as defined by the IPDS architecture; the
data printed by the UP3I post-processor device is merged with the output of the IPDS printer in the manner
defined by UP3I. The object data is not necessarily examined by the IPDS printer and therefore some error
checking is done by the pre-processing or post-processing device. Syntax and position-check errors detected
(by either the printer or post-processor) within a UP3I Print Data object are normally reported with exception ID
X'027E..00'. However, when the post-processor reports the error, one of the following applies:
• If all pages that have not yet reached the Jam-Recovery station can be discarded or marked as waste [IPDS-10-252]
without requiring human interaction, the printer can report exception ID X'027E..00' with action code of X'0A'.
• If the recovery requires operator intervention, IPDS exception ID X'407E..00' is used when this error is [IPDS-10-253]
reported to the host.
For a description of the appearance of this object type when rendered and for a description of the various UP3I
Print Data object formats, refer to the UP3I Specification that is available at www.afpcinc.org.
Figure 100. Example of Object Container UP3I-Print-Data Mapping
AFP Object View
UP I Object View
3
Logical page or overlay
coordinate system $
$
Object area coordinate system {oa}$
{oa}$
Object area
Object area
orientation
Object area
offset
UP I coordinate system
3
{oc}$
{oc}$
Object presentation space
Object
UP I-defined mapping3
Processed by printer:
* AFP mixing rules applied [IPDS-10-254]
* Area coloring applied [IPDS-10-255]
* Object area origin and size translated [IPDS-10-256]
to UP I coordinates
3
* Object area rotation translated relative [IPDS-10-257]
to leading edge of sheet
Processed by post-processor printer:
* No mixing with AFP data [IPDS-10-258]
* Appearance of object is defined [IPDS-10-259]
by the data within the
UP I Print Data (UP I value X'04') triplet
3 3
The IPDS printer does not print UP3I Print Data objects; these objects are passed to a pre-processing or post-
processing device to be printed. Appropriate UP3I constructs, such as frames and triplets, are built and sent
across the UP3I interface to the target device. The printer ensures that the UP3I Print Data object is sent for [IPDS-10-260]

each sheet and for each copy of a sheet that includes this object. When the UP3I Print Data object is found
within a medium overlay, the printer can associate the object with any page on the sheet.
Note: Not all printers support the UP3I-Print-Data mapping option; support for this mapping option is indicated
by support for the UP3I Print Data object in the XOH-OPC Object-Container Type Support self-defining
field.

Object Container Data Descriptor
The Object Container Data Descriptor (OCDD) is the last self-defining field in the data portion of the WOCC
command. This self-defining field specifies a Host-Assigned ID, a Registered Object-Type OID, and data
object resource or data-object-font component information.
The format of the OCDD is as follows: [IPDS-10-261]

| Offset | Type | Name | Range | Meaning | OC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0016', X'0018' to end of OCDD | Length of OCDD, including this length field | X'0016', X'0018' to end of OCDD [IPDS-10-262]|
| 2–3 | CODE | SDF ID | X'A692' | Self-defining-field ID | X'A692' [IPDS-10-263]|
| 4–19 | CODE | Object type OID | See byte description. | Registered object-type OID | See byte description. [IPDS-10-264]|
| 20–21 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | X'0000' No value specified<br>X'0001'–X'7EFF' Data object resource or data-object-font component Host-Assigned ID | X'0000' [IPDS-10-265]|
| 22 to end of OCDD | Triplets | | Zero or more of the following triplets: | X'02' Fully Qualified Name triplet<br>X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'91' Color Management Resource Descriptor triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet [IPDS-10-266]| |
Bytes 0–1 Self-defining-field length
If an invalid value is specified, exception ID X'0202..05' exists.
Bytes 2–3 Self-defining-field ID
Bytes 4–19 Registered object-type OID
The registered object-type OID is an ASN.1 object identifier (OID) defined in ISO/IEC
8824:1990(E), whose last component ID is registered in the MO:DCA architecture. The OID is
left-justified and padded with zeroes.
If an unsupported registered object-type OID value is specified, exception ID X'020D..02'
exists.
For UP3I Print Data objects used in page-state or overlay-state, if a post-processing printer
cannot be found that supports the Print Data Format ID (bytes 3–6 of the UP3I Print Data (UP3I
value X'04') triplet) specified in a UP3I Print Data object, exception ID X'027E..00' exists. When
a UP3I Print Data object is downloaded in home state as a resource, the Print Data Format ID
is not checked until a subsequent IDO command includes the resource in a page or overlay.
The type of objects supported by a printer are indicated in the XOH-OPC Object-Container
Type Support self-defining field. The currently defined objects for the IPDS environment are
listed in the overview section entitled “Data Object Resources, Data-Object-Font Components,
and Setup Files”.

Bytes 20–21 Host-Assigned ID
This field specifies a Host-Assigned ID (HAID) for a data object resource or data-object-font
component. Exception ID X'020D..11' exists if an invalid Host-Assigned ID value is specified.
The use of the HAID value depends on the current IPDS state:
• For a home-state WOCC command that downloads a data object resource (other than a [IPDS-10-267]
setup file) or data-object-font component, the HAID is assigned to the data object resource
or data-object-font component being downloaded. Printers indicate support for home-state
WOCC commands with property pair X'1201' in the Object Container command-set vector of
an STM reply. If the HAID is already in use, exception ID X'020D..16' exists. Since a HAID is
required in this case, exception ID X'020D..11' exists if this field contains X'0000'.
Note: Setup files are not treated as resources; when a setup file is downloaded, the HAID
value in the WOCC command is ignored. In this case, X'0000' should be specified in
this field. Setup files take effect immediately and cannot be deactivated or queried.
The DORE, DORE2,
and IDO commands are not used with a setup file.
• For a page-state or overlay-state WOCC command, the HAID is not used and is ignored. In [IPDS-10-268]
this case, X'0000' should be specified in this field.
Note: For a page-state or overlay-state WOCC command, all necessary secondary
resources must be identified in a prior DORE or DORE2 command within the page or
overlay and these secondary resources must be activated before they are used.
Bytes 22 to
end OCDD
Triplets
This portion of the OCDD contains zero or more triplets that contain information about the
object.
Printers ignore any triplet that is not supported and no exception is reported. If byte 22 or the
first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the printer ignores the
remaining data within the optional triplets field.
Specific triplets are fully described in the triplets chapter:
“Fully Qualified Name (X'02') Triplet”
“Color Specification (X'4E') Triplet”
“Object Offset (X'5A') Triplet”
“Color Management Resource Descriptor (X'91') Triplet”
“Image Resolution (X'9A') Triplet”
“Object Container Presentation Space Size (X'9C') Triplet”
Color Management Triplet Considerations
One Color Management Resource Descriptor (X'91') triplet and optionally one Fully Qualified Name (X'02')
triplet (with FQN Type X'41') is specified for a home-state WOCC command that is downloading a CMR object.
The X'91' triplet carries control information for the activation; the triplet identifies whether the CMR is to be
processed as an audit CMR, as an instruction CMR, or as a link CMR. The X'02' triplet carries the object OID
for the CMR object. If a Color Management Resource (CMR) is activated via a home-state WOCC command,
but the required triplet is not provided, exception ID X'025E..01' exists. If more than one Color Management
Resource Descriptor (X'91') triplet or more than one Fully Qualified Name (X'02') triplet (with FQN Type X'41')
is specified, the last is used and the others are ignored.
An object OID should be provided for all color-conversion CMRs that will be used as an audit or instruction
CMR; failure to provide an object OID for this type of CMR can cause degraded performance. For other types
of CMRs, the object OID is optional.
The X'91' triplet is not used with other data-object-resource types and, if specified, is ignored. [IPDS-10-269]

Color Specification (X'4E') Triplet Considerations
The Color Specification (X'4E') triplet provides a color value for an included bilevel or grayscale image object.
This triplet is used only for objects that contain bilevel or grayscale image, and is ignored for all other object
types. The specific object types that can contain bilevel or grayscale image are identified in the MO:DCA
object-type OID registry.
Only one Color Specification (X'4E') triplet should be specified in the WOCC-OCDD. If multiple Color
Specification (X'4E') triplets are specified, the last one is used and the others are ignored. If a Color
Specification (X'4E') triplet is not specified in the WOCC-OCDD, the color specified within the object or, if none,
the printer default color is used.
• For object-container objects that contain bilevel image but do not specify an internal color value, the Color [IPDS-10-270]
Specification (X'4E') triplet specifies the color of the bilevel image. Note that 1-bit indexed color is considered
to be bilevel. Some presentation object containers can specify color by constructs within the object and, if
present, these color specifications are used instead of the Color Specification (X'4E') triplet. The color
specified in the Color Specification (X'4E') triplet is a default color and does not override a non-default color
specified within the object data.
• For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a [IPDS-10-271]
color that is used in place of black when rendering the image. For example, when the color is brown the
image is produced in “brownscale” rather than grayscale (this is commonly called sepia tone in photography
and produces a softer look than would grayscale). The intensity of the color for each image point is
determined by the color space and the grayscale level in the image, as follows:
RGB 0% = color of medium
100% = specified RGB value
CMYK 0% = color of medium
100% = specified CMYK value
Highlight 0% = color of medium
100% = specified highlight value
ignore the percent-coverage and percent-shading parameters
CIELAB 0% = color of medium
100% = specified CIELAB value
Standard OCA 0% = color of medium
100% = OCA color (or RGB value from table)
Property pair X'5801' in the Object Container command-set vector of an STM reply indicates that the printer
provides bilevel and grayscale image color support for object containers.
Image Resolution (X'9A') Triplet Considerations
An Image Resolution (X'9A') triplet can be specified in the WOCC-OCDD self-defining field to specify an image
resolution for a presentation object. Property pair X'5800' in the Object Container command-set vector of an
STM reply indicates that the printer supports the Image Resolution (X'9A') triplet. Property pair X'FB00' in the
Device-Control command-set vector of an STM reply indicates support for all architected units of measure in
the Image Resolution (X'9A') triplet.
An Image Resolution (X'9A') triplet can be used for some object types (such as TIFF , GIF , JPEG, and
JPEG2000) to identify the raster image resolution used within the object. This triplet is only used with
presentation objects that contain raster image data; if specified for any other type of object container, the triplet
is ignored. If more than one Image Resolution (X'9A') triplet is specified, the last one is used and the others are
ignored.
If an image resolution is needed to process the object and the triplet is specified, the resolution specified within
the triplet is used and overrides the resolution inside the image, if any. However, if an image resolution is not [IPDS-10-272]

needed to process the object, the triplet is ignored. If an image resolution is needed to process the object, but
is not provided at all, the printer assumes that the image resolution is the same as the device resolution and
exception ID X'020D..07' exists.
Object Offset (X'5A') Triplet Considerations
When processing a multi-page resource object (such as a multi-page PDF file or multi-image TIFF file), the
printer uses an Object Offset (X'5A') triplet, to select a single paginated object within the file. When the WOCC
command is sent in page or overlay state, the selected paginated object is presented in the page or overlay.
When the WOCC command is sent in home state, the multi-page object is a resource and is not presented until
it is included with an IDO command; in this case, it is not necessary to put an Object Offset (X'5A') triplet in the
WOCC command. The Object Offset (X'5A') triplet is only used with multi-page objects; the triplet is ignored if
specified with any other object type.
If more than one Object Offset (X'5A') triplet is specified in the WOCC command, the last one is used and the
others are ignored. If there are no Object Offset (X'5A') triplets specified in a page-state or overlay-state
WOCC command, the first paginated object within the file is selected.
Printer support for the Object Offset (X'5A') triplet is indicated by the presence of at least one multi-page-file or
multi-image-file resource object OID in the Object-Container Type Support self-defining field of an XOH-OPC
reply.
Object Container Presentation Space Size (X'9C') Triplet Considerations
An Object Container Presentation Space Size (X'9C') triplet can be specified in the WOCC-OCDD self-defining
field to specify the presentation space size for a PDF or SVG object.
For a PDF or SVG object, only one Object Container Presentation Space Size (X'9C') triplet should be
specified. This triplet is not used with other object types and, if specified for an object type other than PDF or
SVG, the triplet is ignored. If multiple Object Container Presentation Space Size (X'9C') triplets are specified,
the last one is used and the others are ignored.
Property pair X'1203' in the Object Container command-set vector of an STM reply indicates that the printer
supports the Object Container Presentation Space Size (X'9C') triplet for a PDF object. STM property pair
X'1209' in the Object Container command-set vector indicates that the printer supports the Object Container
Presentation Space Size (X'9C') triplet for an SVG object.
TrueType/OpenType Font Triplet Considerations
Optionally one Fully Qualified Name (X'02') triplet (with FQN Type X'DE' and format X'10') can be specified for
a home-state WOCC command that is downloading a TrueType/OpenType font or collection. This triplet carries
the object OID for the font or collection. If more than one such X'02' triplet is specified, the last is used and the
others are ignored.
It is recommended that an OID be provided for a downloaded TrueType/OpenType font. A font OID is needed
when processing PTOCA glyph runs; printers can calculate the OID if it is not supplied in the WOCC-OCDD
command, however not all printers do this calculation. Exception ID X'029C..00' exists if the printer does not
have a font OID at the time a glyph run is processed. [IPDS-10-273]

Write Object Container
```
Length X'D64C' Flag CID Data
```
The length of the WOC command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Object Container (WOC) command transmits object container data to the printer. The type of data in
the Write Object Container command is specified in the Write Object Container Control (WOCC) command.
Zero or more WOC commands follow the WOCC command.
If invalid data is specified within the object itself, one of the following exception IDs exists:
• X'020D..01' for a non-presentation object [IPDS-10-274]
• X'020D..05' for a presentation object [IPDS-10-275]
• X'025D..ee' for a Color Management Resource (CMR) object [IPDS-10-276]
Sense bytes 16–17 for these exceptions can contain an object-specific error code. Refer to “Error Codes for
Other Data Objects” for a list of object-specific error codes.
There are no restrictions on how much or how little data is sent to the printer in a single WOC command,
except for the 32K length limit of the command.
Note: Only Anystate commands are valid between concatenated WOC commands; refer to Figure 45 for a list of Anystate commands. [IPDS-10-277]
