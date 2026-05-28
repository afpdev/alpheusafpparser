# Chapter 12. Overlay Command Set
The Overlay command set allows frequently accessed user data, in the form of an overlay resource, to be
downloaded and temporarily stored in the printer. An overlay is defined within a logical page presentation
space. Overlay logical pages are either merged with a page's logical page on the medium presentation space
(page overlay), or merged directly onto the medium presentation space (medium overlay). In addition, some
IPDS printers support preprinted form overlays that are used to simulate a preprinted form; support for preprinted form overlays is indicated by the X'1600' property pair in the Overlay command-set vector of an STM reply. Refer to “Using an Overlay as a Preprinted Form” for a description of preprinted form overlays.
Color Management Resources (CMRs) can be associated directly with an overlay by specifying Invoke CMR
(X'92') triplets in the LPD command that is saved as part of the overlay environment. To ensure that the
presentation data within an overlay is managed in the same manner each time the overlay is printed, all
appropriate CMRs should be specified in the overlay's LPD command.
The following commands are used in the Overlay command set.
**Table 53. Overlay Commands** [IPDS-12-001]

| Command | Code | Description | In OL1 Subset? |
| :--- | :---: | :--- | :---: |
| BO | X'D6DF' | Begin Overlay | Yes [IPDS-12-002]|
| DO | X'D6EF' | Deactivate Overlay | Yes [IPDS-12-003]|
| IO | X'D67D' | Include Overlay | Yes [IPDS-12-004]|
Overlay Command-Set Commands
This command set contains the commands the printer uses to download, deactivate, and present overlays.
These commands are independent of any specific data types used within the overlay.
An overlay contains the same type of presentation commands used in a page; however, overlays are
independent of the page environment. The key distinction between overlays and pages is that overlays are
stored prior to printing, but pages are scheduled for printing immediately.
A stored overlay can be merged with the logical page of another overlay or of a page by means of the Include
Overlay command. Medium overlays are selected with the LCC command and are merged directly into the
medium presentation space. Preprinted form overlays can be invoked with the LCC or IO command and are
used to simulate a preprinted form as if the overlay data had been preprinted on the paper.
Overlays and page segments are macro-like constructs of IPDS data. However, unlike page segments, an
overlay definition may contain Include Overlay or Include Page Segment commands. In addition, overlays,
unlike page segments, capture the environment at the time of their definition and incorporate this environment
as part of their definition. Thus, the Logical Page Descriptor, Load Font Equivalence, and Load Equivalence
values that exist at the time the Begin Overlay command is received become part of the definition of an
overlay. The definition of an overlay is terminated by an End Page command.
Medium overlays are oriented relative to the medium presentation space and cannot be rotated. For example,
the $X_{p}$ axis of a medium overlay is parallel to and in the same direction as the $X_{m}$ axis; the $Y_{p}$ axis of a medium overlay is parallel to and in the same direction as the $Y_{m}$ axis.
Some IPDS printers allow page overlays to be rotated by specifying an orientation value in the IO command.
Support for page-overlay rotation is indicated by a X'A004' property pair in the Overlay command-set vector of [IPDS-12-005]


an STM reply. For printers that do not support page-overlay rotation, the $X_{p}$ axis of the page overlay is parallel to and in the same direction as the $X_{p}$ axis of the including logical page; and the same applies for the $Y_{p}$ axis.
Text suppression that is delimited by the PTOCA Begin Suppression (BS) and End Suppression (ES) control
sequences does not cross overlay boundaries. Overlay boundaries are opaque to the suppression function.
Data within an overlay is not affected by BS and ES pairs outside the overlay. Suppressions that are active
during the time an overlay is included are reactivated afterward. Within an overlay, the suppression function
operates exactly as it does on a page. [IPDS-12-006]


Begin Overlay
The Begin Overlay (BO) command causes the printer to leave home state and enter overlay state. The
command sequence that follows defines the data that the printer saves as an overlay resource. The current
Logical Page Descriptor, Load Font Equivalence, and Load Equivalence settings, if any, are also saved as part
of the overlay definition, so that the overlay is printed in the same way each time it is used. A stored overlay is
later merged with a page by means of either an Include Overlay command or a Load Copy Control command.
Exception ID X'8002..00' exists if an overlay definition sequence deviates from the sequence defined in Figure 45. While an overlay is being defined, the level of exception detection is printer defined. Refer to
your printer documentation for details.
To associate metadata with an overlay resource, one or more metadata objects can immediately follow the BO
command, before any other commands. Each Write Metadata Control (WMC) command causes the printer to
enter metadata state, where exactly one metadata object is included. Metadata state ends when the printer
receives the End command, at which point the printer returns to overlay state.
An overlay definition may contain zero or more Include Overlay or Include Page Segment commands. These
included resources must be activated before the overlay in which they are contained can be merged with a
logical page or with the medium presentation space. The depth of included overlay nesting is printer-defined
and is identified by the X'15nn' property pair in the Overlay command-set vector of an STM reply.
The End Page (EP) command terminates the definition of the overlay. The overlay is contained between the
BO and the EP commands. Any intervening Execute Order Anystate commands are processed as they are
received; they are not saved as part of the overlay.
```
Length X'D6DF' Flag CID Data
```
For basic support, the length of the BO command can be:
Without CID X'0006'
With CID X'0008'
For extended support, the length of the BO command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-12-007]
## Begin Overlay (BO)


All IPDS printers that support overlays allow up to 254 overlays to be activated at one time (basic support).
Some IPDS printers support even more overlays, up to 32,511 at a time (extended support). The data for the
Begin Overlay command is specified differently for the two types of support, as follows:
Basic support: Supported by all printers that support overlays. [IPDS-12-008]

| Offset | Type | Name | Range | Meaning | OL1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Overlay ID | X'01'–X'FE' | Overlay ID | X'01'–X'FE' [IPDS-12-009]|

Byte 0 Overlay ID
Exception ID X'0290..01' exists if an invalid overlay ID (X'00' or X'FF') is specified. Exception
ID X'0291..01' exists if this field contains an overlay ID for an overlay that is already activated
in the printer.
Extended support: Optional support identified by the X'1102' property pair in the Overlay command-set vector of an STM reply. [IPDS-12-010]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Overlay HAID | X'0001'–X'7EFF' | Overlay HAID | X'0001'–X'7EFF' [IPDS-12-011]|

Bytes 0–1 Overlay HAID
All values in the range X'0001' – X'7EFF' are supported by the printer. Exception ID
X'0290..01' exists if an invalid overlay HAID is specified. Exception ID X'0291..01' exists if this
field contains an overlay HAID for an overlay that is already activated in the printer.
Printers that provide extended overlay support can accept either of the two forms of this
command interchangeably. Exception ID X'0202..02' exists if the extended form of this
command is sent to a printer that provides only basic support. [IPDS-12-012]
## Begin Overlay (BO)


Deactivate Overlay
The Deactivate Overlay (DO) command, previously known as Delete Overlay, deactivates either a single
overlay or all activated overlays. When overlays are deactivated, they are no longer available for merging. The
host can immediately reuse the identification numbers of deactivated overlays.
Completed buffered sheets are committed for printing before the DO command is processed. Exception ID
X'0292..01' exists if the host attempts to deactivate overlays on an incomplete sheet (one side of a duplex
sheet, for example). This exception need not be detected or reported synchronously with this command.
When an overlay is deactivated, any activation information for that overlay created by a previous BO or AR
command is also deleted. AR entries for unactivated overlays are not affected by the Deactivate Overlay
command.
```
Length X'D6EF' Flag CID Data
```
For basic support, the length of the DO command can be:
Without CID X'0006'
With CID X'0008'
For extended support, the length of the DO command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
All IPDS printers that support overlays allow up to 254 overlays to be activated at one time (basic support).
Some IPDS printers support even more overlays, up to 32,511 at a time (extended support). The data for the
Deactivate Overlay command is specified differently for the two types of support, as follows:
Basic support: Supported by all printers that support overlays. [IPDS-12-013]

| Offset | Type | Name | Range | Meaning | OL1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Overlay ID | X'00'<br>X'01'–X'FE' | Deactivate All indicator<br>Overlay ID | X'00'<br>X'01'–X'FE' [IPDS-12-014]|

Byte 0 Overlay ID or deactivate all indicator
This field either specifies a specific overlay to be deactivated or specifies the deactivation of all
overlays. Exception ID X'0285..01' exists if an invalid overlay ID (X'FF') is specified. Exception
ID X'0292..01' exists if the overlay specified is not currently activated.
Extended support: Optional support identified by the X'1102' property pair in the Overlay command-set vector of an STM reply. [IPDS-12-015]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Overlay HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate all indicator<br>Overlay HAID | X'0000'<br>X'0001'–X'7EFF' [IPDS-12-016]|

Bytes 0–1 Overlay HAID or deactivate all indicator
This field either specifies a specific overlay to be deactivated or specifies the deactivation of all
overlays. The value X'0000' and all values in the range X'0001' – X'7EFF' are supported by the [IPDS-12-017]
## Deactivate Overlay (DO)


printer. Exception ID X'0285..01' exists if an invalid overlay HAID is specified. Exception ID
X'0292..01' exists if the overlay specified is not currently activated.
Printers that provide extended overlay support can accept either of the two forms of this
command interchangeably. Exception ID X'0202..02' exists if the extended form of this
command is sent to a printer that provides only basic support. [IPDS-12-018]
## Deactivate Overlay (DO)


Include Overlay
The Include Overlay (IO) command causes a previously activated overlay to be presented on the current
logical page at the specified presentation position. All exceptions for a secure overlay specified by an Include
Overlay command must be reported before reporting any exceptions detected in commands sent after the
Include Overlay command.
The overlay origin is positioned as an offset from the $X_{p}, Y_{p}$ origin of the logical page in which it is contained,
using the L-unit definitions in effect when the IO command is received. The LPD data stored with the overlay is
used while including the overlay on the current logical page. Thus, an overlay included in a page can be
presented partially or entirely outside of the page's logical page.
Some IPDS printers allow page overlays to be rotated by specifying an orientation value in the IO command.
Support for page-overlay rotation is indicated by a X'A004' property pair in the Overlay command-set vector of an STM reply. For printers that do not support page-overlay rotation, the $X_{p}$ axis of the page overlay is parallel to and in the same direction as the $X_{p}$ axis of the including logical page; and the same applies for the $Y_{p}$ axis.
Some IPDS printers support preprinted form overlays (PFO) that are used to simulate a preprinted form. If a
PFO was not already invoked via the LCC command, there can be one preprinted form overlay for each page
on a sheet side. In this case, the overlay is specified in an Include Overlay command that must occur between
the Begin Page command and the End Page command. The PFO is not merged with the page data until the
End Page command has been received and syntax checked; the PFO is merged before returning to home
state and updating page and copy counters. When explicit page placement is used, it is possible for a PFO for
a page to overlap other PFOs or to overlap data for other pages; the resulting merging of the overlapping areas
is determined by the order of the PFOs found within the data stream. It is up to the user to make sure that
overlapping PFOs provide the desired result. Support for preprinted form overlays is indicated by the X'1600'
property pair in the Overlay command-set vector of an STM reply.
After the including and processing of an overlay, the current logical page environment remains as it was prior to
the overlay processing. All logical page description values, font and suppression equivalences, and control
sequence values are restored to the values that existed before the overlay was processed.
Text suppression that is delimited by the Begin Suppression (BS) and End Suppression (ES) control
sequences does not cross overlay boundaries. Overlay boundaries are opaque to the suppression function.
Data within an overlay is not affected by BS and ES pairs outside the overlay. Suppressions that are active
during the time an overlay is included are reactivated afterward. Within an overlay, the suppression function
operates exactly as it does on a page.
Some IPDS printers allow overlays to be nested by including an overlay that also contains an Include Overlay
command. Support for nested overlays is indicated, in the Overlay command-set vector of the reply to the
Sense Type and Model command, by property pair X'15nn'; where X'nn' specifies the number of nesting levels
supported. If the number of nesting levels is exceeded, exception ID X'0297..01' exists. Preprinted form
overlays cannot be nested within other overlays; if an IO command for a PFO is found within an overlay,
exception ID X'0293..00' exists.
Recursive overlay inclusion is not valid; for example, an overlay cannot include itself. If an IO command
specifies an overlay ID that has already been included in the current nested-overlay chain, exception ID
X'0293..01' exists.
To associate metadata with an overlay, the metadata must be passed just after the Begin Overlay (BO)
command when the overlay was defined. Here in the IO command, additional metadata cannot be associated
specifically with the overlay. [IPDS-12-019]
## Include Overlay (IO)


To improve print performance, if a previous Rasterize Presentation Object (RPO) command had preprocessed
and cached an appropriate variation of the overlay to be included, the printer can simply use the cached
variation rather than rasterizing the overlay at include time.
```
Length X'D67D' Flag CID Data
```
The length of the IO command can be:
Without CID X'000F'
With CID X'0011'
When optional page-overlay rotation is supported, the length of the IO command can also be:
Without CID X'0011'
With CID X'0013'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The format of the IO command data is as follows: [IPDS-12-020]

| Offset | Type | Name | Range | Meaning | OL1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001'–X'7EFF' | Overlay Host-Assigned ID | X'0001'–X'00FE' [IPDS-12-021]|
| 2 | CODE | Type | X'00'<br>X'01' | Overlay type:<br>X'00' Nonsecure overlay<br>X'01' Secure overlay | X'00' [IPDS-12-022]|
| 3–5 | SBIN | $X_{p}$ offset | X'FF8000'–X'007FFF'<br>X'FFFFFF' | $X_{p}$ offset from the logical-page origin. Special value: X'FFFFFF' (Use the current position) | X'FF8000'–X'007FFF' (Refer to the note following the table.) [IPDS-12-023]|
| 6 | CODE | Overlay use | X'00'<br>X'01' | Intended use for this overlay:<br>X'00' Page overlay<br>X'01' Preprinted form overlay | X'00' [IPDS-12-024]|
| 7–9 | SBIN | $Y_{p}$ offset | X'FF8000'–X'007FFF'<br>X'FFFFFF' | $Y_{p}$ offset from the logical-page origin. Special value: X'FFFFFF' (Use the current position) | X'FF8000'–X'007FFF' (Refer to the note following the table.) [IPDS-12-025]|
| **Optional page-overlay rotation; only allowed if X'A004' property pair returned in STM reply** [IPDS-12-026]| | | | | |
| 10–11 | CODE | Orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Page-overlay orientation:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | Not supported in OL1 [IPDS-12-027]|
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. [IPDS-12-028]
## Include Overlay (IO)


Bytes 0–1 Overlay Host-Assigned ID
These bytes identify the overlay to be included. The value must have been previously
specified in a Begin Overlay command either as a two-byte HAID or as a one-byte overlay ID
preceded by X'00'. Exception ID X'0290..01' exists if an invalid or unsupported overlay Host-
Assigned ID value is specified. Exception ID X'0292..01' exists if the overlay specified by this
parameter has not been activated.
All IPDS printers that support overlays can have up to 254 overlays activated at one time.
Some IPDS printers support even more overlays, up to 32,511 at a time; this support is
specified by the X'1102' property pair in the Overlay command-set vector of an STM reply.
Byte 2 Overlay type
A value of X'00' specifies that the user's VPA is to be used when printing this overlay. A value
of X'01' specifies that the overlay is to be considered secure; it can be printed anywhere within
the physical printable area. Exception ID X'02AE..01' exists if an invalid or unsupported
overlay-type value is specified.
Data within a secure overlay can be printed anywhere within the overlay's VPA (even outside
of the user's VPA). This allows a presentation services program to print information on the
physical media that cannot be overwritten, omitted, or changed by the print job submitter
without an exception occurring. A presentation services program can use a secure overlay
and a smaller user printable area to print a security label at the top and bottom of each side of
a sheet.
Not all IPDS printers support secure overlays. Support for secure overlays is indicated by
property pair X'70CE' in the Device-Control command-set vector of an STM reply.
Bytes 3–5 $X_{p}$ Offset
This three-byte parameter defines the $X_{p}$ position of the overlay as an offset from the origin of the containing logical page. This parameter is expressed in L-units (defined by the LPD data). A value of X'FFFFFF' causes this coordinate to default to the $X_{p}$ value of the current text coordinate ($I_{c}, B_{c}$); to interpret X'FFFFFF', the current text position ($I_{c}, B_{c}$) must be first converted to an ($X_{p}, Y_{p}$) coordinate value. Exception ID X'02AE..01' exists if an invalid or unsupported $X_{p}$-offset value is specified. [IPDS-12-029]

Note: Since X'FFFFFF' has been used as a default indicator, it is not available for use as an offset value. Therefore you cannot position an overlay at any of the points (x,-1) and (-1, y). Support for the value X'FFFFFF' is optional, but printers typically do support the value for both $X_{p}$ Offset and $Y_{p}$ Offset.
Byte 6 Intended use for this overlay
All IPDS printers support page overlays, but support for preprinted form overlays is optional
and is indicated by the X'1600' property pair in the Overlay command-set vector of an STM
reply. Printers that do not return property pair X'1600' will ignore the value in byte 6, will use
the overlay as a page overlay, and will not issue a negative acknowledgement related to this
parameter.
Included overlays can be used in the following ways as determined by this field:
X'00' Use as a page overlay that is merged with other data using the default mixing rules.
X'01' Use as a preprinted form overlay that is merged with other data using the formblend
mixing rule.
When a PFO was not already invoked via the LCC command, there can be one
preprinted form overlay for each page on a sheet side. In this case, the overlay is
specified in an Include Overlay command that must occur between the Begin Page
command and the End Page command. The PFO is not merged with the page data
until the End Page command has been received and syntax checked; the PFO is
merged before returning to home state and updating page and copy counters. If a [IPDS-12-030]
## Include Overlay (IO)


PFO has already been invoked via the LCC command for a sheet side or if more than
one Include Overlay command within a page specifies X'01', exception ID X'0293..03'
exists.
When explicit page placement is used, it is possible for a PFO for a page to overlap
other PFOs or to overlap data for other pages; the resulting merging of the
overlapping areas is determined by the order of the PFOs found within the data
stream. It is up to the user to make sure that overlapping PFOs provide the desired
result.
When a partial page is created because of exception handling done for an error found
within a page, the included PFO (if any) is merged after the partial page has been
completed. Include Overlay commands that are in the command stream after the
command in error are discarded as part of the error handling and will not be
processed.
Note: In the MO:DCA architecture, this type of overlay is invoked as a Page
Modification Control (PMC) overlay.
Implementation Note: The value X'01' is not supported by all IPDS printers and if
that value is sent to a nonsupporting printer, no exception ID exists. Therefore,
when a print job attempts to use a PFO with a printer that does not support
PFOs, it is recommended that the presentation services program not continue
printing the job without alerting the job submitter in some manner (perhaps with
a message).
Exception ID X'0293..04' exists if an invalid value is specified.
Bytes 7–9 $Y_{p}$ Offset
This three-byte parameter defines the $Y_{p}$ position of the overlay as an offset from the origin of the containing logical page. This parameter is expressed in L-units (defined by the LPD data). A value of X'FFFFFF' causes this coordinate to default to the $Y_{p}$ value of the current text coordinate ($I_{c}, B_{c}$); to interpret X'FFFFFF', the current text position ($I_{c}, B_{c}$) must be first converted to an ($X_{p}, Y_{p}$) coordinate value. Exception ID X'02AE..01' exists if an invalid or unsupported $Y_{p}$-offset value is specified.
Bytes 10–11 Orientation (optional, only allowed when page-overlay rotation is supported)
This parameter specifies the orientation of the page-overlay presentation space in the
including logical page. The page overlay's $X_{p}$ axis is oriented in terms of an angle measured clockwise from the including logical page's $X_{p}$ axis. The page overlay's positive $Y_{p}$ axis is rotated 90° clockwise relative to the page overlay's positive $X_{p}$ axis. This parameter effectively
rotates the page overlay around the overlay origin; it is important to take this rotation into
account when specifying the $X_{p}$ offset and $Y_{p}$ offset values, and when calculating the overlay's
valid printable area. Exception ID X'0293..02' exists when an invalid orientation value is
specified.
If this optional parameter is not specified, the $X_{p}$ axis of the page overlay is parallel to and in the same direction as the $X_{p}$ axis of the including logical page; and the same applies for the $Y_{p}$ axis.
Not all IPDS printers allow page overlays to be rotated; support for page-overlay rotation is
indicated by a X'A004' property pair in the Overlay command-set vector of an STM reply. For
printers that do not support page-overlay rotation, this parameter (bytes 10–11) must not be
specified. If bytes 10–11 is specified for a printer that does not support page-overlay rotation,
exception ID X'0202..02' exists. [IPDS-12-031]
## Include Overlay (IO)


