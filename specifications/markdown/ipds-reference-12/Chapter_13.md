# Chapter 13. Page-Segment Command Set
The Page-Segment command set allows frequently accessed user data, in the form of a page-segment
resource, to be downloaded and temporarily stored in the printer. Page segments are merged with the pages
during printing.
The following commands are used in the Page-Segment command set.
**Table 54. Page Segment Commands** [IPDS-13-001]

| Command | Code | Description | In PS1 Subset? |
| :--- | :---: | :--- | :---: |
| BPS | X'D65F' | Begin Page Segment | Yes [IPDS-13-002]|
| DPS | X'D66F' | Deactivate Page Segment | Yes [IPDS-13-003]|
| IPS | X'D67F' | Include Page Segment | Yes [IPDS-13-004]|
Page-Segment Command-Set Commands
This command set contains the commands the printer uses to download, deactivate, and present page
segments. These commands are independent of any specific data types that define the page segment.
A page segment, like an overlay, contains the same type of presentation commands used in a page. Unlike
overlays, however, page segments are not independent of the page environment. Page segments are merged
with the logical page of a page or overlay and assume the currently active environment. Page segments
cannot contain Include Overlay or Include Page Segment commands.
A host either sends page segments to a printer as data to be stored (for example, as an IPDS page segment)
or expands and includes the page segment data directly in each containing page or overlay.
Print Services Facility™ (PSF), for example, can perform this page segment expansion function. PSF
documentation refers to expanded page segments as soft page segments. This is a software-specific
distinction. Soft page segments do not exist at the IPDS level. [IPDS-13-005]


Begin Page Segment
The Begin Page Segment (BPS) command causes the printer to leave home state and enter page segment
state. The command sequence that follows defines data that the printer saves as a page segment resource
and schedules for printing later. A page segment is included later in a page or overlay by means of an Include
Page Segment (IPS) command.
Exception ID X'8002..00' exists if a page segment definition sequence deviates from the sequence defined in Figure 45. While a page segment is being defined, the level of exception detection is printer
defined. Refer to your printer documentation for details.
The End Page (EP) command terminates the definition of a page segment. The page segment is contained
between the BPS and the EP commands. Any intervening Execute Order Anystate commands are processed
as they are received; they are not saved as part of the page segment. The page segment must not contain any
IO or IPS commands.
```
Length X'D65F' Flag CID Data
```
The length of the BPS command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The BPS command data field is as follows: [IPDS-13-006]

| Offset | Type | Name | Range | Meaning | PS1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001'–X'7EFF' | Page Segment Host-Assigned ID | X'0001'–X'007F' [IPDS-13-007]|
Bytes 0–1 Page Segment Host-Assigned ID
These bytes specify a binary value that identifies the page segment. Exception ID X'0295..01'
exists if a page segment with the same identifier is already activated.
All IPDS printers that support page segments can have up to 127 page segments activated at
one time. Some IPDS printers support even more page segments, up to 32,511 at a time; this
support is specified by the X'1101' property pair in the Page Segment command-set vector of
an STM reply. If an invalid or unsupported page segment HAID is specified, exception ID
X'0294..01' exists.
## Begin Page Segment (BPS)


Deactivate Page Segment
The Deactivate Page Segment (DPS) command, previously known as Delete Page Segment, deactivates
either a single page segment or all page segments. The host can immediately reuse the identification numbers
of deactivated page segments.
Completed buffered sheets are committed for printing before the DPS command is processed. Exception ID
X'0296..01' exists if the host attempts to deactivate page segments on an incomplete sheet (one side of a
duplex sheet, for example). This exception need not be detected or reported synchronously with this
command.
When a page segment is deactivated, any activation information for that page segment created by a previous
BPS or AR command is also deleted. AR entries for unactivated page segments are not affected by the
Deactivate Page Segment command.
```
Length X'D66F' Flag CID Data
```
The length of the DPS command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The DPS command data field is as follows: [IPDS-13-008]

| Offset | Type | Name | Range | Meaning | PS1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Page Segment Host-Assigned ID | X'0000'<br>X'0001'–X'007F' [IPDS-13-009]|
Bytes 0–1 Page Segment Host-Assigned ID or deactivate all indicator
These bytes specify either the page segment to be deactivated or the deactivation of all page
segments. Nonzero values identify the page segment to be deactivated and correspond to the
Page Segment Host-Assigned ID of a Begin Page Segment command. Exception ID
X'0296..01' exists if the page segment specified is not currently activated.
All IPDS printers that support page segments can have up to 127 page segments activated at
one time. Some IPDS printers support even more page segments, up to 32,511 at a time; this
support is specified by the X'1101' property pair in the Page Segment command-set vector of
an STM reply. If an invalid or unsupported page segment HAID is specified, exception ID
X'028A..01' exists.
## Deactivate Page Segment (DPS)


Include Page Segment
The Include Page Segment (IPS) command causes a previously stored page segment resource to be
processed in the input data stream as though its commands had just been received from the host. When the printer includes a page segment, the current print position ($I_{c}, B_{c}$) is inherited by the page segment and can be
changed by text control sequences within the page segment.
```
Length X'D67F' Flag CID Data
```
The length of the IPS command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The IPS command data field is as follows: [IPDS-13-010]

| Offset | Type | Name | Range | Meaning | PS1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001'–X'7EFF' | Page Segment Host-Assigned ID | X'0001'–X'007F' [IPDS-13-011]|
Bytes 0–1 Page Segment Host-Assigned ID
These bytes specify a binary value that identifies the page segment. This value corresponds
to the page segment Host-Assigned ID of a Begin Page Segment command. Exception ID
X'0296..01' exists if the page segment specified has not been activated.
All IPDS printers that support page segments can have up to 127 page segments activated at
one time. Some IPDS printers support even more page segments, up to 32,511 at a time; this
support is specified by the X'1101' property pair in the Page Segment command-set vector of
an STM reply. If an invalid or unsupported page segment HAID is specified, exception ID
X'0294..01' exists.
## Include Page Segment (IPS)


