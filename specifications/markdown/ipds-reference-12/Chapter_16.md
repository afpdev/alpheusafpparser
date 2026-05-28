# Chapter 16. Exception Reporting
This chapter provides additional information about the Acknowledge Reply, that is used by IPDS products for
exception reporting. The chapter begins with general information, that is followed by a complete description of
the exception-reporting codes. The chapter concludes with a section on page-counter and copy-counter
adjustments.
The exception codes listed in this chapter include those that are valid in the IPDS architecture. No printer has
implemented all the exception codes. For information and a list of specific exception codes supported by an
individual printer, consult the documentation for that printer.
The Acknowledge Reply is used by IPDS printers to return both positive and negative replies to the host.
Positive replies are called ACKs. Negative replies are called NACKs and contain sense-byte information in the
special data area. For more information about acknowledgments, refer to “Acknowledge Reply” .
General Rules for Exceptions
All of the exception reporting rules are intended to accomplish a simple result: the printer and the
communications network (including direct-attachment protocols such as channel) will work together to ensure
that the next IPDS command processed subsequent to a given NACK, is the beginning of the host's response
to that NACK.
The following general rules apply to NACKs:
• The printer reports exceptions with a NACK. Only one exception can be returned in each NACK. However, [IPDS-16-001]
several instances of a specific data-stream exception can be reported in one NACK, using the count field
(bytes 6 and 7).
• The IPDS architecture does not specify the number of NACKs that a printer must queue. Some printers [IPDS-16-002]
queue only a single NACK. A printer with queued NACKs will return one NACK at a time until its queue is
emptied.
• There is no prioritization or required order of reporting of synchronous data stream or resource storage [IPDS-16-003]
exceptions applicable to the same page, page segment, or overlay. These exceptions are reported after the
printer has returned to home state (refer to the description of the XOA Exception Handling Control
command).
• Exceptions contain either three or twenty–four bytes of sense data containing details about the exception. In [IPDS-16-004]
some cases, additional information might be available for an exception; for example, while processing a
PostScript object container that causes an exception, the PostScript rasterizer might have returned error text
about the exception. If additional information is available for an exception, a printer can advertise that
availability using the additional-information flag in the Acknowledge Reply reporting the exception. The host
can then retrieve the additional exception information using the XOA Obtain Additional Exception Information
(OAEI) command.
• Other than the case discussed in the next bullet, once a printer with queued NACKs begins returning its [IPDS-16-005]
NACKs, a NACK will be returned at each line turnaround opportunity provided by the communication
protocol, until the printer’s NACK queue is empty. Thus, any IPDS command received while the NACK queue
is being emptied will not be processed. Hosts can assure that the NACK queue is empty and that the printer
is ready to continue processing IPDS commands by repeatedly issuing a command with an ARQ, until a
positive Acknowledge Reply is received.
• The OAEI command is the one exception to the rule that IPDS commands are not processed while the [IPDS-16-006]
printer is emptying its NACK queue. When a printer returns a NACK, if the next command received is
anything other than an OAEI command with an ARQ, the general rule described above is followed: while
there are NACKs remaining, the command is not processed and the next NACK is returned. If, however, the [IPDS-16-007]


next command is an OAEI command with an ARQ, the OAEI command is processed normally. Such
processing will result in an OAEI reply being returned to the host, and this reply might necessitate a series of
Acknowledge Replies using the ACK continuation method. Once the OAEI reply is fully sent and the host
sends another command, or if the host sends a command that cuts short the ACK continuation
communication, the printer reverts to the general rule and continues emptying its NACK queue, even if the
command sent is another OAEI.
• A printer can make additional exception information available for a given exception—and thus the additional- [IPDS-16-008]
information flag in the Acknowledge Reply can be set—only if the printer is ready to respond to an immediate
OAEI command to retrieve that information.
• The printer can send an ACK or a NACK in response to an Acknowledgment Request (ARQ) flag. If an [IPDS-16-009]
exception occurs, the printer can send a NACK without receiving a command with an ARQ.
• If the printer receives a command requesting an acknowledgment, it expects the host to wait for the [IPDS-16-010]
acknowledgment before sending more commands. If the printer receives additional commands from the host
before the acknowledgment is sent, all such commands are discarded.
• When an exception is reported, all upstream data is discarded. [IPDS-16-011]
Upstream data is defined to be all IPDS commands that exist in a logical path from a specific point in a
printer back to, but not including, host presentation services. Upstream data includes all IPDS commands
that the printer has not yet processed and all buffered page data for pages that have not yet passed the
specific point in the logical path. The buffered page data is constructed by the printer from all IPDS
commands that affect the page.
Most IPDS commands are executed immediately upon receipt and therefore are not buffered. For example,
an LPP command causes the printer to position the next received page and all subsequent pages at a
specific location on the medium presentation space. The LPP command is executed immediately in the
sense that the printer remembers the position to be applied to all subsequently received pages. When
upstream data is discarded, all upstream page data is discarded including the logical page position
information for those pages. However, since the last-received LPP command was processed immediately
upon receipt, the printer continues to remember the LPP positioning information. All pages received after the
upstream data has been discarded continue to be positioned using this saved LPP information.
Note: The host can determine which pages have been discarded by examining the page and copy counters
in an ACK of a synchronizing command. Any pages not reflected in the counters have been discarded.
Refer to “Page and Copy Counter Adjustments” .
• All synchronous exceptions for a given page must be reported to the host before any exceptions on [IPDS-16-012]
subsequent pages may be reported.
• When the printer has one or more asynchronous exceptions to report (exceptions with an action code other [IPDS-16-013]
than X'01', X'06', X'0C', or X'1F'), it discards any synchronous exceptions and reports just the asynchronous
exceptions.
If the number of synchronous exceptions detected exceeds the number of NACKs the printer can queue
before home state is entered, the printer continues processing and discards all subsequent synchronous
exceptions. When the printer enters home state, all queued NACKs are reported and the printer may
optionally report exception ID X'02FF ..02' to indicate that one or more NACKs were discarded.
• An ACK indicates that the data stream up to the command with the Acknowledgment Request (ARQ) has [IPDS-16-014]
been syntax-checked, except for the following cases:
– When IPDS data is being saved for future use, as in page segments and overlays.
– When multiple copy subgroups are specified, all copies of pages on a sheet might not be completely
syntax-checked until the last page on the sheet is acknowledged. Thus, when multiple copy subgroups are
specified, an acknowledgment of a page only guarantees that the copy (or copies) produced from the first
copy subgroup specified in the LCC that applies to the page has been syntax-checked for synchronous
data-stream exceptions. The acknowledgment for the last page of a sheet indicates that all copies of all
pages on the sheet have been syntax-checked for synchronous data-stream exceptions. [IPDS-16-015]


– When an exception in a page segment or an overlay is reported according to the XOA-EHC command with
the page on which it is included. An exception in a copy subgroup may be reported after the EP command
for the page to which the copy subgroup applies (but no later than on the next IPDS command).
– Some IPDS printers process multiple pages in parallel to improve performance. In this case, the printer
replies to some Acknowledgment Requests before syntax checking all of the previously received
commands and reports any later found data stream errors with an asynchronous NACK, such as
X'0111..00' with action code X'1A'. This causes the host to reposition to the page in error and resend that
page so that the previously detected data stream error can be redetected and reported synchronously.
– Asynchronous exceptions can occur at any time.
• The Exception-Handling Control (EHC) order of the Execute Order Anystate (XOA) command is used to tell [IPDS-16-016]
the printer how to handle exceptions. More information about the XOA-EHC command follows in the next
section. For complete information about the XOA-EHC command, refer to “XOA Exception-Handling Control”
.
• If a command-reject exception is detected by the printer, no portion of the command is accepted by the [IPDS-16-017]
printer; that is, the entire command is discarded. [IPDS-16-018]


Exception-Handling Control
The XOA-EHC command allows you to control several exception-handling functions. Brief descriptions of the
functions follow.
Exception Reporting
The reporting or suppressing of three types of exceptions can be controlled: undefined-character
checks, page-position checks, and other exceptions with AEAs.
In addition, some printers allow certain NACKs that cause the printer to discard buffered data to be
suppressed. This is called operator-directed recovery.
Position-Check Highlighting
Position-check exception highlighting on the logical page can be controlled. When a Page-
Continuation Action is taken for a position-check exception, the exception is always highlighted.
Support for position-check highlighting is optional; refer to your printer documentation for information
about what is supported in a particular printer implementation.
Alternate Exception Actions
The printer can take Alternate Exception Actions (AEAs), that prescribe actions to take when the
printer is given an IPDS command or parameter that is unsupported. AEAs are defined when such an
action is not likely to compromise the integrity of the data. Not all exceptions have AEAs.
Exception-Presentation Processing
Page Continuation
This function has two possible implementations; refer to your printer documentation to
determine which implementation your printer has selected:
• Skip and Continue Actions. The printer can skip the data object containing the command [IPDS-16-019]
with the exception and try to resume printing.
• Page Continuation Actions. The printer can terminate or continue processing a page that [IPDS-16-020]
has an exception.
Exception Page Print
The printer can print or discard a page that has been terminated because of a data-stream
exception.
For some printers, even finer control can be specified with the Presentation Fidelity Control command for
certain presentation functions that a device is incapable of performing, such as color, finishing, and
unsupported text controls. For these functions, the Presentation Fidelity Control command can be used to
specify:
• Whether or not printing should continue when an exception is detected [IPDS-16-021]
• Whether or not an exception should be reported [IPDS-16-022]
• For color exceptions, what type of color substitution is permitted [IPDS-16-023]
The exception-handling control flowchart, Figure 60 , shows the relationship between the PFC
command and the XOA-EHC command. [IPDS-16-024]


Exception Reporting
Use the exception reporting byte (byte 2) to control the reporting of exceptions that have defined AEAs:
• Undefined characters [IPDS-16-025]
• Position exceptions [IPDS-16-026]
• All other exceptions that have AEAs [IPDS-16-027]
When you tell the printer to report a given type of exception, it always reports such exceptions. If you tell the
printer not to report a given type of exception, the printer will still report such exceptions if the printer was told
not to take the AEA.
When the printer has one or more asynchronous exceptions to report (exceptions with an action code other
than X'01', X'06', X'1F', or X'0C'), it discards any synchronous exceptions and reports just the asynchronous
exceptions.
If the number of synchronous exceptions detected exceeds the number of NACKs the printer can queue before
home state is reached, the printer queues as many NACKs as possible, and then continues processing
discarding all subsequent synchronous exceptions until it has entered home state and reported all of the
previously queued NACKs. When the printer enters home state, all queued NACKs are reported and the
printer may optionally report exception ID X'02FF ..02' to indicate that one or more NACKs were discarded.
For complete information about the exception-reporting byte, refer to “XOA Exception-Handling Control”.
Alternate Exception Actions
Use the AEA byte (byte 3) to tell the printer whether or not to take an AEA. This specification is independent of
whether or not the printer reports the exception. However, if there is no AEA for an exception or if the printer is
told not to take the AEA, the exception is always reported.
When the printer has been instructed to report an exception and take the AEA, it reports the exception the next
time home state is entered or when the printer processes a command with the ARQ bit set to B'1'. Thus,
exceptions that occur within a page are queued but not reported until the printer has processed the EP
command, an XOA command that changes the printer state to home state, or a command with the ARQ bit set
to B'1'. The exception sense data has a count field for recording multiple occurrences of a given exception to
minimize reporting of many occurrences of the same data-stream exception.
For complete information about alternate exception actions, refer to “XOA Exception-Handling Control”. [IPDS-16-028]


Exception-Presentation Processing
Page Continuation
Use the exception-presentation processing byte (byte 4) to tell the printer whether or not to continue
processing commands when it encounters certain exceptions while processing a page, a page segment, or an
overlay.
There are two possible implementations: skip and continue action (SCA) and page continuation action (PCA).
Your printer will use only one of these implementations. Refer to your printer documentation to determine
which one applies.
Exceptions that do not have a defined AEA tend to require severe recovery. If AEAs are disabled, even the less
serious exceptions that possess AEAs are reported, and the defined SCA or PCA is taken.
When an SCA or a PCA is taken, the printer queues the exception and continues processing. Both an SCA and
a PCA might involve ignoring a command and skipping some succeeding commands. Some PCAs resemble
the AEA for the exception.
When the printer has been instructed to take the SCA or PCA, it reports the exception the next time it enters
home state or processes a command with the ARQ bit set to B'1'. Thus, exceptions that occur within a page
are queued but not reported until the printer has processed the EP command, an XOA command that changes
the printer state to home state, or a command with the ARQ bit set to B'1'. To minimize reporting many
occurrences of the same data-stream exception, the exception sense data has a count field for recording
multiple occurrences of a given exception.
Note: An exception in a copy subgroup may be reported after the EP command for the page to which the copy
subgroup applies (but no later than on the next IPDS command).
If an overlay or page segment is terminated while it is being downloaded, the printer discards the partial
overlay or page segment and returns to home state.
If the exception occurs while multiple copies are being generated at EP time, sheets associated with previously
processed, exception-free copy subgroups are printed. The copy subgroups, that are specified as part of the
LCC command, tell the printer how to modify a page before printing the specified number of copies.
For complete information about the exception-presentation processing byte, refer to “XOA Exception-Handling
Control” .
Exception Page Print
The exception-page-print bit under certain circumstances determines whether a page is partially printed or
discarded for pages on which an exception is detected and reported to the host. When a page is to be printed,
the printer performs the equivalent of an End Page command and prints the partial page within the limits of the
print process.
For complete information about the exception-page-print function, refer to “XOA Exception-Handling Control”
.


Classes of Exceptions
Exceptions are returned in either 3 or 24 bytes of detailed sense information in the special data area of the
NACK. The exception identifier comprises three bytes: 0, 1, and 19 for printers that return 24 bytes; and 0, 1,
and 2 for printers that return 3 bytes. Byte 0 specifies the exception class; the other two bytes identify the
particular exception. The reply from a Sense Type and Model command specifies whether the printer supports
3 bytes of sense data or 24 bytes of sense data. [IPDS-16-029]
The classes of exceptions are:
• Command reject [IPDS-16-030]
• Equipment check with intervention required [IPDS-16-031]
• Intervention required [IPDS-16-032]
• Equipment check [IPDS-16-033]
• Data check [IPDS-16-034]
• Metadata specification check [IPDS-16-035]
• IO-Image specification check [IPDS-16-036]
• Bar Code specification check [IPDS-16-037]
• Graphics specification check [IPDS-16-038]
• General specification check [IPDS-16-039]
• Conditions requiring host notification [IPDS-16-040]


## Sense Byte Information
The following describes the information in each sense byte. Some printers return only bytes 0–2. Figure 117 shows the layout of sense bytes within a Negative Acknowledge Reply (NACK).
Byte 0 The first byte of each three-byte exception ID, that defines the exception class for the specific
exception.
Byte 1 The second byte of each three-byte exception ID, that together with sense byte 2 or sense
byte 19, defines the specific exception within an exception class.
Byte 2 For printers that return 24 bytes of sense, contains the host exception-recovery action code
that specifies the suggested recovery action for the exception.
For printers that return 3 bytes of sense, contains the third byte of the three-byte exception ID;
together with sense byte 1, defines the specific exception within an exception class.
Byte 3 Retired item 125
Byte 5 Specifies the general format of the remaining sense bytes.
Bytes 4, 6–18 Describe the specific cause in one of several possible formats. Formats 1 and 7 are both used
for positioning exceptions, with some printers using format 1 and others using format 7. Refer
to your printer documentation for details.
Format 0 Provides details about all data-stream exceptions except positioning
exceptions.
Format 1 Provides details about positioning exceptions for some printers.
Format 2 Provides details about device exceptions, including intervention-required
exceptions, equipment-check exceptions, and conditions requiring host
notification.
Format 3 Retired item 80
Format 4 Retired item 81
Format 5 Retired item 82
Format 7 Provides details about positioning exceptions for some printers.
Format 8 Provides details about UP
3I-specific exceptions.
For more information about formats, refer to “Formats for Sense Bytes 4-18 and 20-23”.
Byte 19 For printers that return 24 bytes of sense data, contains the third byte of the three-byte
exception ID; together with sense byte 1, defines the specific exception within an exception
class.
Bytes 20–23 Contains the page identifier for the page that has the exception (except for format 2). [IPDS-16-041]


Figure 117. Layout of a Negative Acknowledge Reply (NACK)
Length
X’D6FF’ if CID flag
is B’1’
X’80’
X’C0’
Two counters
Nine counters
Code Flags CID Type Page & Copy Counters Sense Data
24 sense bytes [IPDS-16-042]
3-byte Exception ID
Format
ID
0       1       2       3       4       5       6       7       8       9      10     11     12     13     14     15     16     17     18     19     20     21     22     23 [IPDS-16-043]
Action
Code
X’DE’ Count of
Occurrences
This example shows
format X’00’ sense data
HAID of
overlay
HAID of
page
segment
Command
Code
HAID of Font
or
Data Object
Bonus
information
Type
of
object
For bytes 14-15
and bytes 20-23
Page
Identifier
Host-Assigned IDs
Action code examples:
X’01’ Data stream exception (received page counter)
X’08’ Physical media jam (jam-recovery counters)
X’0A’ Pre-processor or post-processor exception (jam-recovery counters)
X’0D’ Printer restart (all counters reset to X’0000’)
X’1D’ Printer characteristics changed (committed counters)
Exception specific:
* Undefined code point [IPDS-16-044]
* IOCA self-defining field in error [IPDS-16-045]
* Data object error code [IPDS-16-046]
* Inconsistent media ID [IPDS-16-047]
* Bad Unicode value [IPDS-16-048]
For printers that return 24 sense bytes:
For printers that return 3 sense bytes:
Length
X’D6FF’ if CID flag
is B’1’
X’80’
X’C0’
Two counters
Nine counters
Code Flags CID Type Page & Copy Counters Sense Data
3-byte exception ID


Formats for Sense Bytes 4-18 and 20-23
These formats apply only to printers that return 24 bytes of sense data.
Sense bytes 4–18 and 20–23 describe the cause of each exception. Sense byte 19 is the third byte of the
three-byte exception ID. These descriptions are presented in one of the following formats:
Format 0
This format applies to all data-check, specification-check, and command-reject exceptions except exception
IDs X'08C1..00', X'08C2..00', X'08C3..00', X'0411..00', X'020A..05', and X'027E..00'.
Byte 4 Data Exception, X'DE'
Byte 5 Format Identifier, X'00'
Bytes 6–7 Count of occurrences of the exception; this value indicates how many instances of this
exception occurred on the logical page. Some printers issue a separate NACK for each
occurrence; other printers make use of this field to minimize the number of NACKs issued.
Bytes 8–9 ID of overlay that has the exception
Bytes 10–11 ID of page segment that has the exception
Bytes 12–13 Command in process when exception found
Bytes 14–15 ID of other object (identified in sense byte 18, bits 0–3):
– For a font object, this is the HAID from an AR, DF , LCPC, LFC, LFCSC, LFI, or LSS
command
– For a data-object-font component, this is the HAID from an AR, DDOFC, or WOCC
command
– For a data object resource, this is the HAID from an AR, DDOR, DORE, DORE2, ICMR,
IDO, LPD, RPO, WBCC, WGC, WIC2, or WOCC command
Bytes 16–17 Exception-ID-specific information; see note 3  for a list of exception IDs that
provide this information. A value of X'0000' in this field means that no exception-ID-specific
information has been provided.
Byte 18 This field indicates the type of object identified by the ID field in sense bytes 14–15 and 20–
23:
Bits 0–3 Resource identified in sense bytes 14–15
B'0000' Font object
B'0001' Data object resource
Bits 4–7 Page identified in sense bytes 20–23
B'0000' Page identifier from Begin Page command
B'0001' Page sequence number associated with a saved page
Bytes 20–23 Page identifier, the content of this field depends on the following:
– If printing and not saving a page, and the exception is associated with a particular page,
this is the page ID from the Begin Page command. If the exception is not associated with a
particular page, this field contains X'00000000'.
– If saving a page and the exception is associated with a particular page, this is the page
sequence number that is associated with the page to be saved. If the exception is not
associated with a particular page, this field contains X'00000000'.
Notes:
1. The fields in sense bytes 8–17 contains binary zeroes when information is not appropriate or available for [IPDS-16-049]
these fields.
2. For exception ID X'0237..04', sense bytes 12–13 contain the command code for a BP , EP , or LCC [IPDS-16-050]
command, sense byte 14 is reserved and should contain X'00', sense byte 15 contains a media-source ID,
and sense bytes 16–17 contain the media-destination ID that is inconsistent with the media-source ID. [IPDS-16-051]


3. Sense bytes 16–17 contain additional useful information that is specific to the particular NACK, as follows: [IPDS-16-052]
Table 63. Exception ID Specific Information [IPDS-16-053]

| Exception ID | Information in Sense Bytes 16–17 |
| :--- | :--- |
| X'0821..00'<br>X'0829..00'<br>X'028F..50' | Bytes 16–17 contain the code point that caused the error. For double-byte fonts, byte 16 contains the section ID and byte 17 contains the 2nd byte of the code point. For single-byte fonts, byte 17 contains the code point. [IPDS-16-054]|
| X'0500..01'<br>X'0500..03'<br>X'0500..04' | Bytes 16–17 contain the IOCA self-defining field code that caused the error. For one-byte codes, byte 16 contains X'00' and byte 17 contains the code. [IPDS-16-055]|
| X'0406..11' | Bytes 16–17 contain the size of the smallest valid symbol width in twips. [IPDS-16-056]|
| X'0412..00' | Bytes 16–17 contain the application ID (ai) value for the bad data. The ai value is read as four decimal digits, with leading zeroes if necessary. For example, ai = 01 is shown as X'0001' in bytes 16–17 and ai = 8005 is shown as X'8005' in bytes 16–17. [IPDS-16-057]|
| X'03C3..03'<br>X'021A..03' | Bytes 16–17 contain the Unicode code value in error, as follows:<br>• When a high-order surrogate code value was not immediately followed by a low-order surrogate code value, bytes 16–17 contain the high-order surrogate code value.<br>• When a low-order surrogate code value was not immediately preceded by a high-order surrogate code value, bytes 16–17 contain the low-order surrogate code value.<br>• When an illegal UTF-8 code value sequence was specified, bytes 16–17 contain the first two bytes of the UTF-8 code value sequence. [IPDS-16-058]|
| X'0200..01' | Bytes 16–17 contain the unsupported or unrecognized PTOCA control sequence function type that caused this error. Byte 16 contains X'00' and byte 17 contains the function-type value. [IPDS-16-059]|
| X'020D..01'<br>X'020D..05'<br>X'0115..00' | Bytes 16–17 contain an object-specific error code. Refer to “Error Codes for Other Data Objects” for a list of object-specific error codes. [IPDS-16-060]|
| X'0237..04' | Bytes 16–17 contain the inconsistent media destination ID. [IPDS-16-061]|
| X'025D..ee' | Bytes 16–17 contain a CMR TagID value as defined in the Color Management Object Content Architecture Reference. [IPDS-16-062]|
| X'029C..02' | Bytes 16–17 contain the glyph ID that caused the error. [IPDS-16-063]|
Note: For sense format 0 exceptions (not listed in the table) whose explanation lists multiple causes, sense bytes
16–17 contains the number of the specific cause; printers that do not provide this bonus information return
X'0000' in sense bytes 16–17. [IPDS-16-064]


Format 1
For some printers, format 1 provides detailed information for all data-stream positioning exceptions, that is,
X'08C1..00', X'08C2..00', X'08C3..00', X'0411..00', and X'020A..05'. Property pair X'FF01' in the Device-
Control command-set vector of an STM reply indicates whether Sense Format 1 or Sense Format 7 is
supported for positioning exceptions.
Byte 4 Data Exception, X'DE'
Byte 5 Format Identifier, X'01'
Bytes 6–7 Count of occurrences of the exception; this value indicates how many instances of this
exception occurred on the logical page. Some printers issue a separate NACK for each
occurrence; other printers make use of this field to minimize the number of NACKs issued.
Bytes 8–9 ID of overlay that has the exception
Bytes 10–11 ID of page segment that has the exception
Bytes 12–13 Command in process when exception found
Byte 14 Text position exception count (maximum 255, no wrap)
Byte 15 Image position exception count (maximum 255, no wrap)
Byte 16 Rule position or bar-code-symbol bar position exception count (maximum 255, no wrap)
Byte 17 Graphics position exception count (maximum 255, no wrap)
Byte 18 This field indicates the type of object identified by the HAID field in sense bytes 20–23:
Bits 0–3 Reserved
Bits 4–7 Page identified in sense bytes 20–23
B'0000' Page identifier from Begin Page command
B'0001' Page sequence number associated with a saved page
Bytes 20–23 Page identifier, the content of this field depends on the following:
– If printing and not saving a page, and the exception is associated with a particular page,
this is the page ID from the Begin Page command. If the exception is not associated with a
particular page, this field contains X'00000000'.
– If saving a page and the exception is associated with a particular page, this is the page
sequence number that is associated with the page to be saved. If the exception is not
associated with a particular page, this field contains X'00000000'.
Format 2
This format applies to most intervention-required exceptions, equipment-check exceptions, and conditions
requiring host notification.
Byte 4 Printer-specific sense detail
Byte 5 Format Identifier, X'02'
Bytes 6–18 Printer-specific sense detail
Bytes 20–23 Printer-specific sense detail
Format 3 Retired item 80
Format 4 Retired item 81
Format 5 Retired item 82 [IPDS-16-065]


Format 7
For some printers, format 7 provides detailed information for data-stream positioning exceptions, that is,
X'08C1..00', X'08C2..00', X'08C3..00', X'0411..00', and X'020A..05'. Property pair X'FF01' in the Device-
Control command-set vector of an STM reply indicates whether Sense Format 1 or Sense Format 7 is
supported for positioning exceptions.
Byte 4 Data exception, X'DE'
Byte 5 Format Identifier, X'07'
Bytes 6–7 Count of occurrences of the exception; this value indicates how many instances of this
exception occurred on the logical page. Some printers issue a separate NACK for each
occurrence; other printers make use of this field to minimize the number of NACKs issued.
Bytes 8–17 Reserved
Byte 18 This field indicates the type of object identified by the HAID field in sense bytes 20–23:
Bits 0–3 Reserved
Bits 4–7 Page identified in sense bytes 20–23
B'0000' Page identifier from Begin Page command
B'0001' Page sequence number associated with a saved page
Bytes 20–23 Page identifier, the content of this field depends on the following:
– If printing and not saving a page, and the exception is associated with a particular page,
this is the page ID from the Begin Page command. If the exception is not associated with a
particular page, this field contains X'00000000'.
– If saving a page and the exception is associated with a particular page, this is the page
sequence number that is associated with the page to be saved. If the exception is not
associated with a particular page, this field contains X'00000000'.
Format 8
Format 8 provides detailed information for UP
3I-specific exceptions. Exception IDs that use format 8
include: X'507E..00', X'407E..00', X'107E..00', X'027E..00', and X'017E..00'.
Byte 4 Data Exception, X'DE'
Byte 5 Format Identifier, X'08'; this byte indicates that the sense data uses the UP 3I sense-data
format.
Bytes 6–7 IPDS command in process when the exception was found; if the exception is not associated
with any particular command, this field contains X'0000'.
Bytes 8–9 UP3I-specific error code. Refer to the UP 3I specification for a description of the error codes.
Byte 10 UP3I-specific information; paper sequence ID of the device that caused the exception.
Bytes 11–18 UP3I-specific information. Refer to the UP 3I specification for a description of the content of
these bytes.
Bytes 20–23 Page identifier, the content of this field depends on the following:
– If printing and not saving a page, and the exception is associated with a particular page,
this is the page ID from the Begin Page command. If the exception is not associated with a
particular page, this field contains X'00000000'.
– If saving a page and the exception is associated with a particular page, this is the page
sequence number that is associated with the page to be saved. If the exception is not
associated with a particular page, this field contains X'00000000'. [IPDS-16-066]


Action Codes (Sense Byte 2 for Printers That Return 24 Sense Bytes)
Action codes classify the exception to assist host-exception recovery and allow printing to continue. Action
codes are included only if the printer returns 24 bytes of sense data.
Each exception is defined relative to a specific point in the logical paper path and is described as occurring
relative to a specific page counter. These counters are adjusted as described in “Page and Copy Counter
Adjustments” . The logical paper path and the page counters are shown in Figure 118.
Figure 118. Logical Paper Path and Page Counters
Received page counter
Committed page counter
Jam recovery page counter
Stacked page counter
PrinterHost
Direction of page and paper flow
Optional
post
processor
The following action codes are valid; however, a specific printer returns only some of the codes. Refer to your
printer documentation for the list of action codes used by your printer.
For each action code, a suggested host recovery action is provided. For the description of a particular host-
program implementation, refer to your host-program documentation.
Table 64. Action Codes [IPDS-16-067]

| Action Code | Exception Recovery Action |
| :--- | :--- |
| X'01' | **Data-Stream Exception**<br>A syntax error has been found in an IPDS command. The host recovery action depends on the specific exception and on host-support requirements. Data-stream exceptions are discovered while the printer is accepting and syntax checking IPDS commands. For commands containing page data, the page in which the error occurred is either the page just before the Received Page Counter or the page at the Received Page Counter depending upon the current XOA-EHC command and whether multiple pages are to be presented on the current sheet. Refer to “Load Copy Control” for further details. [IPDS-16-068]|
| X'05' | **End IPDS dialog**<br>The printer has received a request to print from another session and asks the presentation services program to end the current IPDS dialog (or the current carrying-protocol session) as soon as possible, such as at the end of the current print unit. If the printer is currently receiving a page or resource, the partial page or resource is discarded so that the host and printer are synchronized to the beginning of the page or resource. This condition does not affect the page or copy counters. [IPDS-16-069]|
| X'06' | **Function no longer achievable**<br>The printer detected that a previously requested function can no longer be performed. The host recovery depends on the specific exception and on host-support requirements. This condition does not adjust the page or copy counters. [IPDS-16-070]|
| X'08' | **Physical Media Jam**<br>The printer has detected a physical media jam. The printer has discarded all buffered pages and modified the page and copy counters. Retransmit all pages that have not passed the printer-defined jam-recovery point and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Physical media jams occur on the next sheet that would have reached the Jam-Recovery Page Counter. [IPDS-16-071]|
| X'09' | **Data-Related Print Exception**<br>A sheet cannot be printed because of something within the data stream; for example, the data might be too complex, or too dense, or the media source selected might be incompatible with the media destination selected. The printer has discarded all buffered pages and modified the page and copy counters. Recovery depends on host-support requirements. Data-related print exceptions occur on the next sheet that would have reached the Committed Page Counter. [IPDS-16-072]|
| X'0A' | **Pre-processor or post-processor exception**<br>The printer has detected a condition in a pre-processor or post-processor device that has caused all pages that would have reached the jam-recovery station to be discarded. The printer has discarded all buffered pages and modified the page and copy counters. Host recovery depends on the specific exception and on host-support requirements. Post-processor exceptions cause all pages that would have reached the Jam-Recovery Page Counter to be discarded. [IPDS-16-073]|
| X'0C' | **Resource Storage Exception**<br>The printer cannot accept a page or resource (overlay, page segment, font, or data object resource) because the storage area is full; the printer has discarded the partial page or resource. If the exception occurred while saving a page, that page is discarded, but previously saved pages are kept. When an out-of-storage exception causes the first page of a group to be discarded, the group is terminated and information concerning the group is discarded. Deactivate unused resources and retry; if this action fails, the recovery action depends on host-support requirements. Resource storage exceptions occur on the next page that would have reached the Received Page Counter. [IPDS-16-074]|
| X'0D' | **Printer Restart**<br>The printer has discarded all pages and downloaded resources (overlays, page segments, fonts, and data object resources) because of operator intervention or because of a hardware failure. All saved page groups are deactivated and might also be removed. All page and copy counters have been reset to zero. Recovery depends on host-support requirements. [IPDS-16-075]|
| X'15' | **Cancel**<br>The printer operator has requested that the current print data be canceled. The printer has discarded all buffered pages and modified the page and copy counters. If the Committed Copy Counter is zero, cancel the print data containing the page at the Committed Page Counter. If the Committed Copy Counter is not zero, cancel the print data containing the page that will next reach the Committed Page Counter. [IPDS-16-076]|
| X'16' | **Hardware-Related Print Exception**<br>The printer has discarded all buffered pages because of a condition detected at the printer. Retransmit all pages that have not been committed for printing and any associated resources that are not already in the printer. Hardware-related print exceptions occur on the next sheet that would have reached the Committed Page Counter. [IPDS-16-077]|
| X'17' | **Printer Mechanism Unusable**<br>A printer mechanism, such as the offset stacker, a duplex media path, or an input media source, has become unusable. Printing might still be possible if the unusable mechanism is bypassed. The printer has discarded all buffered pages and modified the page and copy counters. Host software should take appropriate action. [IPDS-16-078]|
| X'19' | **Asynchronous Data-Stream Exception**<br>An attempt was made to print outside the valid printable area or to print an undefined text, bar code HRI, or graphics character. The printer has discarded all buffered pages and modified the page and copy counters. The appropriate recovery action depends on host-support requirements. Asynchronous data-stream exceptions occur on a page that is between the Received and Committed Page Counters. The host must issue an XOH-PBD command to ensure that the page and copy counters are accurately adjusted. After the XOH-PBD command has successfully completed, the page in error is either one of the pages on the last sheet just before the Committed Page Counter or the page at the Committed Page Counter, depending on the appropriate XOA-EHC command. [IPDS-16-079]|
| X'1A' | **Redrive Buffered Pages**<br>The printer has discarded buffered pages due to a printer operator action or a hardware problem. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Redrive-buffered-pages exceptions occur on the next sheet that would have reached the Committed Page Counter. [IPDS-16-080]|
| X'1B' | **Recovery-Unit Group Exception**<br>The printer has detected a condition that has caused all pages that have not yet reached the jam-recovery point to be discarded while a recovery-unit group operation was active. The printer has discarded all pages that have not yet reached the jam-recovery point and has modified the page and copy counters. Retransmit pages from the jam recovery page counter plus one and reload any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Recovery-unit group exceptions cause all pages that have not yet reached the jam-recovery point to be discarded so that host recovery can reposition to a group boundary. [IPDS-16-081]|
| X'1D' | **Printer Characteristics Changed**<br>At least one of the printer characteristics that is reported in the reply to an XOH-OPC command has changed. The printer has discarded all buffered pages and modified the page and copy counters. The host should issue an XOH-OPC command to obtain the new printer characteristics. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Printer-characteristics-changed exceptions occur on the next sheet that would have reached the Committed Page Counter. [IPDS-16-082]|
| X'1E' | **Asynchronous Out-of-Storage Exception**<br>A resource or a page that is not currently being received at the Received Page station caused an out-of-storage exception. The printer has discarded all buffered pages and reset the page and copy counters. If the exception occurred on a sheet, the sheet will not print and will be discarded. The host must issue an XOH-PBD command to ensure that the page and copy counters are accurately adjusted. Deactivate all resources not necessary to continue printing and retransmit the next page after the one at the Received Page Counter. If this action fails, the recovery action depends on host-support requirements. [IPDS-16-083]|
| X'1F' | **Data-Stream Exception in a Secure Overlay**<br>A syntax error has been found in the IPDS data stream of a Secure Overlay. This action code is used to report Data-Stream Exceptions (action code X'01') that occur within a secure overlay, so that the host can perform special recovery for these exceptions. Action code X'1F' is used in place of action code X'01' when:<br>• A data-stream exception is detected while processing a Secure Overlay that was specified by an Include Overlay command in page state, or<br>• An Overlay ID Outside Valid Range exception (X'0290..01') or Overlay ID Not Activated (X'0292..01') is detected while processing an Include Overlay command for a Secure Overlay in page state. If one of these exceptions is detected while processing an Include Overlay command for a non-secure overlay, action code X'01' is used.<br>Note: Printers that completely syntax-check images during download (such as, the IBM 3825 printer) do not return action code X'1F' for data-stream exceptions in an image. [IPDS-16-084]|
| X'22' | **Printer Inoperative**<br>A printer condition, such as a permanent hardware exception or an uncleared operator-intervention condition, has occurred from which the printer cannot recover. The host should terminate communication with the printer. Note: An Action Code other than X'22' (such as X'1A') should be used for intervention-required conditions that require host-software recovery. [IPDS-16-085]|
| X'23' | **Temporary Hardware Exception**<br>A temporary hardware exception has occurred. The printer has discarded all buffered pages and modified the page and copy counters. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Temporary hardware exceptions occur on the next sheet that would have reached the Committed Page Counter. [IPDS-16-086]|
| X'2B' | **Suspended Recovery-Unit Group Exception**<br>The printer has detected a condition that has caused all pages that have not yet reached the jam-recovery point to be discarded while a suspended recovery-unit group operation was active. The printer has discarded all pages that have not yet reached the jam-recovery point and has modified the page and copy counters. Retransmit pages from the jam recovery page counter plus one and reload any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Because a suspended recovery-unit group did not start on a sheet boundary, the host cannot reposition to a group boundary; therefore, the entire group is printed, but with blank sheets within the group. [IPDS-16-087]|


Exception Reporting Codes
The following sections describe all printer exception IDs and action codes that are returned to the host in the
special data area of a NACK.
Each exception ID is identified by three bytes: byte 0, byte 1, and either byte 2 or byte 19. This section lists the
names of the exception classes that are returned in byte 0. The exception classes are listed in the order in
which individual classes appear in the chapter.
Each exception is classified as being either mandatory or optional. Mandatory exceptions must be generated
by all printers in accordance with the following rules:
• Mandatory exceptions must be generated by a printer only if the printer supports the function or command to [IPDS-16-088]
which the exception applies.
• Mandatory exceptions that can be caused by multiple conditions must be generated by a printer under all the [IPDS-16-089]
conditions that are applicable to the functions and commands supported by the printer.
• A mandatory exception can be presented with any of the action codes registered for the exception ID. [IPDS-16-090]
• Wherever an OCA-defined exception is classified as mandatory, the IPDS architecture requires that the [IPDS-16-091]
exception be generated regardless of whether the OCA specifies the exception to be mandatory or optional.
For all other OCA-defined exceptions, the IPDS architecture defers the mandatory/optional specification to
the appropriate OCA.
Optional exceptions need not be generated by a printer. If the exception is mandatory, the exception must be
generated by a printer in accordance with the rules for IPDS mandatory exceptions.
The reporting of all exceptions, whether classified as mandatory, optional, or OCA-specified is determined by
the XOA-EHC command.
The subsequent sections provide detailed information about each of the classes listed. [IPDS-16-092]


Exception Classes
Sense Byte 0 Exception Class and Description
X'80' Command Reject: an IPDS command has been rejected at the printer without the data within
the command being examined.
X'50' Equipment Check with Intervention Required: the printer has detected a condition that was
caused by hardware failure or by hardware limitations and manual intervention at the printer is
required.
X'40' Intervention Required: the printer has detected a condition that requires manual intervention at
the printer, such as “out of paper”.
X'20' Retired item 92 (Bus-Out Parity Check)
X'10' Equipment Check: the printer has detected a condition that was caused by hardware failure or
by hardware limitations.
X'08' Data Check: the printer has detected a condition that was caused by a positioning error or
undefined character.
X'06' Metadata Specification Check: the IPDS receiver has detected an invalid or unsupported data
value in a metadata command.
X'05' IO-Image Specification Check: the printer has detected an invalid or unsupported data value in
an IO-Image command.
X'04' Bar Code Specification Check: the printer has detected an invalid or unsupported data value
in a bar code command.
X'03' Graphics Specification Check: the printer has detected an invalid or unsupported data value in
a graphics command.
X'02' General Specification Check: the printer has detected a general specification check. This
exception class contains specification checks that are common to all IPDS data types.
Some general specification checks are also defined for text data and are identified accordingly
in the subsequent sections.
X'01' Conditions requiring host notification: the printer has detected a condition that is not an error,
but about which the host should be notified.
Printer Exception IDs
The specific exception IDs are arranged by exception class. The three-byte exception ID is listed in the form
XXYY..ZZ; where XX represents byte 0 of the sense bytes, YY represents byte 1 of the sense bytes, and ZZ
represents either byte 19 (for twenty-four byte sense data) or byte 2 (for three-byte sense data). The action
code is listed followed by an explanation of the exception code. Note that the action code is provided in sense
byte two for printers that return twenty-four bytes of sense data. The alternate exception action and page
continuation action is listed followed by an indication of whether or not the exception must be supported by
printers.
Within each exception class, the exceptions are listed in ascending numeric order. [IPDS-16-093]


Command-Reject Exceptions
A command-reject exception indicates that an IPDS
command has been rejected at the printer without the
data within the command being examined. There are
no AEAs for this class of exception.
Format 0 is used for command-reject exceptions.
8001..00 Invalid or unsupported IPDS command [IPDS-16-094]
code
Action code: X'01'
Explanation: The command code is not a valid or
supported value. An invalid length on a previous command
might have caused the current data to be mistaken for a
command.
Alternate Exception Action: None
Page Continuation Action: If X'D6' is the first byte of the
command code received, ignore the command. Otherwise
there is no Page Continuation Action.
Support: Mandatory
8002..00 Invalid or unsupported IPDS command [IPDS-16-095]
sequence
Action code: X'01'
Explanation: The printer state is invalid or is unsupported
for the received command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
8004..00 Data received after ARQ [IPDS-16-096]
Action code: X'01'
Explanation: IPDS commands were received after an
acknowledgment was requested, but before it was sent.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
80E0..00 Invalid IPDS command length
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The length of an IPDS command is not within the [IPDS-16-097]
allowed range.
2. The length of the data within a WGC, WIC2, or WBCC [IPDS-16-098]
command is not equal to the sum of the lengths of the
self-defining fields that are in the command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Notes:
1. Some printers report this exception as X'0202..02' or [IPDS-16-099]
X'0203..02'. The preferred Exception ID is X'0203..02'
when the IPDS command header length is too small
and X'0202..02' for other IPDS command length
exceptions.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-100]
number of a specific cause for the error.
8001..00 • 80E0..00 [IPDS-16-101]


Equipment Check with Intervention Required Exceptions
An Equipment Check with Intervention Required
exception indicates that the printer has detected a
condition that is caused by hardware failure or by
hardware limitations, and manual intervention at the
printer is required. There are no AEAs for this class
of exception.
Format 2 is used for most equipment-check-with-
intervention-required exceptions; format 8 is used for
X'507E..00'.
5010..00 Printer-Hardware Exception [IPDS-16-102]
Action code: X'16'
Explanation: A printer-hardware exception has been
detected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
5010..00 Printer-Hardware Exception [IPDS-16-103]
Action code: X'22'
Explanation: The printer has detected a printer-hardware
error condition, and it has not been corrected by the
operator after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
507E..00 Intervention required because of an
equipment check on a UP
3I-controlled
device
Action code: X'08', X'09', X'16', or X'22'
Explanation: A UP3I-controlled pre-processing or post-
processing device attached to the printer has reported an
equipment check error that is also an intervention required
condition. The specific error is identified in the sense bytes
8–9.
For action code X'09', the host should end the print unit at
the committed-page station plus 1.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Note: For more information about the error code in sense
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
50F2..00 Print Overrun
Action code: X'09'
Explanation: A print request attempted to position print
data on the physical medium after the print position had
passed the point in the printer where this print position can
no longer be changed. This exception can occur because
the processing of the data in the printer takes too long.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F2..00 Print Overrun
Action code: X'22'
Explanation: A print request attempted to position print
data on the physical medium after the print position had
passed the point in the printer where this print position can
no longer be changed. This problem has not been
corrected by the operator within a specified time. This
exception can occur because the processing of the data in
the printer takes too long.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F5..00 Image Generator Exception
Action code: X'16'
Explanation: A hardware failure has occurred with the
Image Generator.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F5..00 Image Generator Exception
Action code: X'22'
Explanation: A hardware failure has occurred with the
Image Generator, and it has not been corrected by the
operator within a specified time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F6..00 Offset Stacker Exception
Action code: X'17'
5010..00 • 50F6..00 [IPDS-16-104]


Explanation: The Offset Stacker is not available (has
been disabled).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F7..00 Duplex Media Path Exception
Action code: X'17'
Explanation: Duplex capability is not available (has been
disabled).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F8..nn Media-Source Exception
Action code: X'17'
Explanation: Media Source X'nn' is not available (has
been disabled).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F9..00 MICR printing exception
Action code: X'17'
Explanation: MICR printing is not available (has been
disabled).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50FA..00 Text decryption exception
Action code: X'17'
Explanation: PTOCA text decryption capability is not
available (has been disabled). This exception is used when
the decryption feature becomes unavailable.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
50F7..00 • 50FA..00


Intervention-Required Exceptions
An intervention-required exception indicates that the
printer has detected a condition that requires manual
intervention. There are no AEAs for this class of
exception.
Format 2 is used for most intervention-required
exceptions; format 8 is used for X'407E..00'.
4000..00 Printer not ready [IPDS-16-105]
Action code: X'1A'
Explanation: The printer is in a not-ready state.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4000..00 Printer not ready [IPDS-16-106]
Action code: X'22'
Explanation: One or more of the following conditions
exists:
• The printer has been not ready for a specified amount of [IPDS-16-107]
time.
• A printer door is open and has not been closed by the [IPDS-16-108]
operator after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4001..00 Out of paper [IPDS-16-109]
Action code: X'1A'
Explanation: The printer is out of paper or the bin cover is
open and buffered pages have been deleted.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4001..00 Out of paper [IPDS-16-110]
Action code: X'22'
Explanation: The printer is out of paper or the bin cover is
open, and no paper has been added by the operator after a
specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4002..00 Media destination is full [IPDS-16-111]
Action code: X'1A'
Explanation: The media destination (stacker) is full.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4002..00 Media destination is full [IPDS-16-112]
Action code: X'22'
Explanation: The media destination (stacker) is full and
has not been emptied by the operator after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4004..00 Out of toner [IPDS-16-113]
Action code: X'1A'
Explanation: The printer is out of toner.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4004..00 Out of toner [IPDS-16-114]
Action code: X'22'
Explanation: The printer is out of toner, and no toner has
been added by the operator after a specified amount of
time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4005..00 Empty fuser oil supply [IPDS-16-115]
Action code: X'22'
4000..00 • 4005..00 [IPDS-16-116]


Explanation: The fuser oil supply is empty, and no fuser
oil has been added by the operator after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4006..00 Invalid physical media [IPDS-16-117]
Action code: X'22'
Explanation: An invalid physical media specification was
received. Nonduplexable media was selected for duplex
printing, and the operator has not corrected this problem
after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4010..00 Paper adjustment check [IPDS-16-118]
Action code: X'1A'
Explanation: A paper adjustment check occurred.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4011..00 Suppressed jam recovery [IPDS-16-119]
Action code: X'22'
Explanation: Host recovery for physical media jams has
been disabled at the printer; a jam has occurred; and it has
not been corrected by the operator after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4012..00 An attempt to print an undefined character [IPDS-16-120]
or to print outside sheet boundaries has
occurred that requires operator
intervention
Action code: X'22'
Explanation: One or more of the following conditions
exists:
• An operator intervention condition has occurred because [IPDS-16-121]
of an attempt to print outside sheet boundaries, and it
has not been corrected by the operator after a specified
amount of time.
• An operator intervention condition has occurred because [IPDS-16-122]
of an attempt to print an undefined character, and it has
not been corrected by the operator after a specified
amount of time.
• The operator intervention condition might have been [IPDS-16-123]
caused by a pre-processing or post-processing device
attached to the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4013..00 Continuous-forms media needs to be torn [IPDS-16-124]
off
Action code: X'1A'
Explanation: The currently loaded continuous-forms
media needs to be torn off so that media from another
source can be used.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4014..00 Asynchronous decompression error [IPDS-16-125]
Action code: X'09'
Explanation: The printer has detected a data-related
decompression error on a page that is between the
received page station and the committed page station.
Incorrectly compressed JPEG image data within a data
object or an IOCA image can cause this exception.
The printer must finish committing prior sheets (if any),
discard the pages of the error sheet, and discard all
upstream data before reporting this NACK.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: This exception ID is appropriate when the printer
cannot automatically remove blank or error pages
and operator intervention is required. In other cases
(where operator intervention is not necessary),
X'0114..00' should be used.
4016..00 Data validation error [IPDS-16-126]
Action code: X'1A'
Explanation: A data-validation device connected to the
printer has detected unreadable or incorrectly printed data.
The committed page counter identifies the error page.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4017..00 Ribbon Fault [IPDS-16-127]
4006..00 • 4017..00 [IPDS-16-128]


Action code: X'1A'
Explanation: A problem with the printer ribbon has
occurred that requires operator intervention.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4020..00 Incorrect Form Module selection [IPDS-16-129]
Action code: X'1A'
Explanation: An incorrect form module selection was
detected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4031..00 Paper-Length Check [IPDS-16-130]
Action code: X'1A'
Explanation: The printer has detected a paper-length
check.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
4031..00 Paper-Length Check [IPDS-16-131]
Action code: X'22'
Explanation: The printer has detected a paper-length
check, and it has not been corrected by the operator after a
specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4033..00 Paper-Width Check [IPDS-16-132]
Action code: X'1A'
Explanation: The printer has detected a paper-width
check.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4033..00 Paper-Width Check [IPDS-16-133]
Action code: X'22'
Explanation: The printer has detected a paper-width
check, and it has not been corrected by the operator after a
specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4035..00 Printer-detected FORMs mismatch [IPDS-16-134]
Action code: X'0A'
Explanation: The actual media loaded does not match
the FORMID that was selected at the printer console.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4040..00 Printer emitted blank sheets in the middle [IPDS-16-135]
of a recovery-unit group
Action code: X'1B'
Explanation: The Keep-Group-Together-as-a-Recovery-
Unit operation was active for a group of pages and the
printer emitted blank sheets in the middle of the group. In
this case, pages of the group were committed but had not
yet reached the jam-recovery point. The printer has
discarded all pages that had not yet reached the jam-
recovery point; this allows the presentation services
program to reposition to the beginning of a group.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Keep-Group-Together-as-a-
Recovery-Unit operation is supported.
4040..00 Printer emitted blank sheets in the middle [IPDS-16-136]
of a suspended recovery-unit group
Action code: X'2B'
Explanation: The Keep-Group-Together-as-a-Recovery-
Unit operation was requested, but suspended for a group
of pages and the printer emitted blank sheets in the middle
of the group. In this case, pages of the group were
committed and some might have reached the jam-recovery
point. The printer has discarded all pages that had not yet
reached the jam-recovery point; this allows the
presentation services program to reposition to the jam
recovery station plus one, but since the group was
suspended, this might not be the beginning of a group.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Keep-Group-Together-as-a-
Recovery-Unit operation is supported.
4020..00 • 4040..00 [IPDS-16-137]


4050..00 Fuser oil supply empty [IPDS-16-138]
Action code: X'1A'
Explanation: The fuser oil supply is empty.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4050..00 Fuser oil supply empty [IPDS-16-139]
Action code: X'22'
Explanation: The fuser oil supply is empty, and no fuser
oil has been added by the operator after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4051..00 Developer mix needs changing [IPDS-16-140]
Action code: X'1A'
Explanation: The developer mix needs to be changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4051..00 Developer mix needs changing [IPDS-16-141]
Action code: X'22'
Explanation: The developer mix needs to be changed,
and the operator has not responded after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4052..00 Oiler felt needs changing [IPDS-16-142]
Action code: X'1A'
Explanation: The oiler felt needs to be changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4052..00 Oiler felt needs changing [IPDS-16-143]
Action code: X'22'
Explanation: The oiler felt needs to be changed, and the
operator has not responded after a specified amount of
time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4053..00 Toner collector full [IPDS-16-144]
Action code: X'1A'
Explanation: The toner collector is full and needs to be
replaced.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4053..00 Toner collector full [IPDS-16-145]
Action code: X'22'
Explanation: The toner collector is full and needs to be
replaced, and the operator has not responded after a
specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4054..00 Fine filter needs changing [IPDS-16-146]
Action code: X'1A'
Explanation: The fine filter needs to be changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
4054..00 Fine filter needs changing [IPDS-16-147]
Action code: X'22'
Explanation: The fine filter needs to be changed, and the
operator has not responded after a specified amount of
time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..00 Out of staples
Action code: X'1A'
Explanation: The printer is out of staples and a staple
operation has been received.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
407C..00 Out of staples
Action code: X'22'
4050..00 • 407C..00 [IPDS-16-148]


Explanation: The printer has been out of staples for a
specified amount of time, and the host should terminate
communication with the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..01 Staple jam
Action code: X'08'
Explanation: The staple mechanism on a printer or post-
processor has jammed or has caused a physical-media
jam. Upstream data has been discarded from the jam-
recovery point back to the host.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
407C..01 Staple jam
Action code: X'0A'
Explanation: The staple mechanism on a printer or post-
processor has jammed or has caused a physical-media
jam. Upstream data has been discarded from the jam-
recovery point back to the host. The host should retransmit
all pages that have not passed the printer-defined jam-
recovery point and any associated resources (overlays,
page segments, fonts, saved page groups, and data object
resources) that are not already in the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..01 Staple jam
Action code: X'1A'
Explanation: The staple mechanism on a printer or post-
processor has jammed or has caused a physical-media
jam. Upstream data has been discarded from the
committed-page point back to the host.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
407C..01 Staple jam
Action code: X'22'
Explanation: The staple mechanism on a printer or post-
processor has been jammed for a specified amount of
time, and the host should terminate communication with
the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..02 Too many sheets for a finishing operation
Action code: X'0A'
Explanation: A finishing operation was requested for a
collection of sheets, but the number of sheets was too
large for the operation. This exception was detected
asynchronously while sheets were being processed in the
finishing device; the error occurred on the sheet at the jam-
recovery station.
The host should end the print unit at the jam-recovery
station.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..02 Too many sheets for a finishing operation
Action code: X'22'
Explanation: A finishing operation was requested for a
collection of sheets, but the number of sheets was too
large for the operation. The condition has not been cleared
after a specified amount of time, and the host should
terminate communication with the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..03 Punch waste bin full
Action code: X'1A'
Explanation: The printer supports the punch operation
and the punch waste bin has become full.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407C..03 Punch waste bin full
Action code: X'22'
Explanation: The printer supports the punch operation,
the punch waste bin has become full, and the operator has
not emptied the waste bin after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407D..00 Post-processor has discarded pages
407C..01 • 407D..00


Action code: X'0A'
Explanation: The printer has detected a condition in a
post-processor that has caused all pages that would have
reached the jam-recovery station to be discarded. The host
should retransmit all pages that have not passed the
printer-defined jam-recovery point and any associated
resources (overlays, page segments, fonts, saved page
groups, and data object resources) that are not already in
the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407D..00 Post-processor has discarded pages
Action code: X'22'
Explanation: The printer has detected a condition in a
post-processor that has caused all pages that would have
reached the jam-recovery station to be discarded. The
condition has not been cleared after a specified amount of
time, and the host should terminate communication with
the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407D..01 Finishing Mechanism Exception
Action code: X'0A'
Explanation: A finishing mechanism exception occurred.
If a finishing operation was in progress, the operation was
not applied, and the Finishing Operation (X'85') triplet has
been discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407D..01 Finishing Mechanism Exception
Action code: X'22'
Explanation: A mechanism exception occurred that
prevents a finishing operation from being applied. The
condition has not been cleared after a specified amount of
time, and the host should terminate communication with
the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407E..00 Intervention required on a UP
3I-controlled
device
Action code: X'08', X'0A', X'1A', or X'22'
Explanation: A UP3I-controlled pre-processing or post-
processing device attached to the printer has reported an
intervention required condition. The specific error is
identified in the sense bytes 8–9.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Note: For more information about the error code in sense
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
40C0..00 Continuous Forms Separator Jam
Action code: X'08'
Explanation: A hardware failure occurred in the printer's
continuous-forms separator mechanism. The continuous
forms might not have been completely separated. The
printer will not resume printing until the jam has been
cleared.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
40C0..00 Continuous Forms Separator Jam
Action code: X'22'
Explanation: A hardware failure occurred in the printer's
continuous-forms separator mechanism, and it has not
been corrected by the operator within a specified time. The
continuous forms might not have been completely
separated. The printer will not resume printing until the jam
has been cleared.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E0..00 Physical media jam not cleared (used
incorrectly by 4224, 4234; software should
treat this exception the same as
X'40E5..00')
Action code: X'08'
Explanation: A physical media jam has occurred and has
not been cleared by the operator after a specified amount
of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
407D..00 • 40E0..00


Notes:
1. Some printers report this error as exception ID [IPDS-16-149]
X'40E5..00'. The preferred exception ID is X'40E5..00'.
2. For some printers, reporting of this exception is [IPDS-16-150]
controlled by the operator-directed recovery bit in the
XOA Exception-Handling Control command.
40E0..00 Physical media jam not cleared
Action code: X'22'
Explanation: A physical media jam has occurred and has
not been cleared by the operator after a specified amount
of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E1..00 Out of paper (secondary input)
Action code: X'22'
Explanation: The secondary paper supply is out of paper
and no paper has been added by the operator after a
specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E2..00 Transport requires corrective action
Action code: X'22'
Explanation: The transport mechanism requires
corrective action and no action has been taken by the
operator after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E3..00 Fuser requires corrective action
Action code: X'22'
Explanation: The fuser requires corrective action and no
action has been taken by the operator after a specified
amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E4..00 Cancel key pressed:
Action code: X'09'
Explanation: The Cancel key on the printer operator
panel was pressed while the printer was receiving data.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'01E4..00'.
The preferred Exception ID is X'01E4..00'.
40E4..00 Cancel key pressed (used by 4224)
Action code: X'15'
Explanation: The Cancel key on the printer operator
panel was pressed while the printer was receiving data.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'01E4..00'.
The preferred Exception ID is X'01E4..00'.
40E5..00 Jam recovery needed
Action code: X'08'
Explanation: A physical media jam has occurred, and the
lost pages must be resent.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Notes:
1. Some printers report this error as exception ID [IPDS-16-151]
X'40E0..00'. The preferred exception ID is X'40E5..00'.
2. For some printers, reporting of this exception is [IPDS-16-152]
controlled by the operator-directed recovery bit in the
XOA Exception-Handling Control command.
40E5..00 Jam recovery needed
Action code: X'0A'
Explanation: A physical media jam has occurred while
accumulating sheets for a finishing operation. Pages that
would have reached the jam-recovery station have been
discarded. The host should retransmit all pages that have
not passed the printer-defined jam-recovery point and any
associated resources (overlays, page segments, fonts,
saved page groups, and data object resources) that are not
already in the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E5..00 Jam recovery needed
Action code: X'22'
Explanation: A physical media jam has occurred. This
problem has not been corrected by the operator within a
specified time.
Alternate Exception Action: None
40E0..00 • 40E5..00


Page Continuation Action: None
Support: Optional
40E6..00 Door open
Action code: X'1A'
Explanation: The printer has detected a door-open
condition, and has discarded all buffered pages.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E6..00 Door open
Action code: X'22'
Explanation: The printer has detected a door-open
condition, and it has not been corrected by the operator
after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E7..00 Paper-specification check
Action code: X'22'
Explanation: The printer has detected a paper-
specification check, and it has not been corrected by the
operator after a specified amount of time.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E8..nn Supported but not installed Media Source
ID specified
Action code: X'1A'
Explanation: A supported media source ID of X'nn' was
specified, but the input media source associated with that
ID is not currently installed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
40E9..00 Post-processor not ready
Action code: X'1A'
Explanation: A post-processor attached to the printer is in
a not-ready state.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
40E9..00 Post-processor not ready
Action code: X'22'
Explanation: A post-processor attached to the printer has
been in a not ready state for a specified amount of time,
and the host should terminate communication with the
printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
40E6..00 • 40E9..00


Equipment-Check Exceptions
An equipment-check exception indicates that the
printer has detected an equipment malfunction or a
hardware failure. There are no AEAs for this class of
exception.
Format 2 is used for most equipment-check
exceptions; format 8 is used for X'107E..00'.
107E..00 Equipment check on a UP 3I-controlled
device
Action code: X'09', X'22', or X'23'
Explanation: A UP3I-controlled pre-processing or post-
processing device attached to the printer has reported an
equipment check error that cannot be corrected by an
operator. The specific error is identified in the sense bytes
8–9.
For action code X'09', the host should end the print unit at
the committed-page station plus 1.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Note: For more information about the error code in sense
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
10F1..00 Permanent hardware exception
Action code: X'22'
Explanation: One or more of the following conditions
exists:
• A permanent hardware failure exists. [IPDS-16-153]
• The printer has detected either a logic exception from [IPDS-16-154]
which the printer cannot recover or a condition that
should not have occurred. The session and conversation
should be terminated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
10F2..00 Print overrun
Action code: X'09'
Explanation: A print request attempted to position print
data on the physical medium after the print position had
passed the point in the printer where this print position can
no longer be changed. The committed page-counter
station is the place in the printer where pages can no
longer be discarded by the XOA Discard Buffered Data
command. For some printers, this is the print mechanism.
This exception can occur because processing of the data
in the printer takes too long.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
10F3..00 Magnet setting changed
Action code: X'23'
Explanation: The magnet setting on the media cassette
changed after a page was processed but before the page
was printed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
10F4..00 Serializer parity exception
Action code: X'23'
Explanation: A parity exception has occurred within the
image generator (first occurrence).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
10F5..00 Image generator exception
Action code: X'23'
Explanation: A hardware failure has occurred within the
image generator.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the operator-directed recovery bit in
the XOA Exception-Handling Control command.
107E..00 • 10F5..00


Data-Check Exceptions
A data-check exception indicates that the printer has
detected an undefined character or position check.
Some data-check exceptions correspond to an
exception code specified by the BCOCA and PTOCA
architectures, and are identified accordingly. Refer to
the architecture reference manual for these object-
content architectures for an object-specific
description of the exception.
Format 0 is used for all data-check exceptions,
except for exception IDs X'08C1..00', X'08C2..00',
and X'08C3..00', that use either format 1 or format 7.
0821..00 Undefined character [IPDS-16-155]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• An invalid or undefined character has been detected in [IPDS-16-156]
WBC command bar code data.
• An undefined character has been detected in the font [IPDS-16-157]
specified for text or bar code HRI data.
• A character has been detected in Write Text command [IPDS-16-158]
data that is undefined at the quality level specified by the
XOA-PQC command.
Alternate Exception Action: For an undefined character
in bar code data, there is no alternate exception action. For
an undefined character within an LF1-type or LF3-type
coded font, the character is processed as if it had been a
defined character; for a double-byte code point in an LF1-
type restricted section (sections X'45' through X'FE'), the
print position is incremented using the default variable
space increment. For an undefined character within a
symbol-set coded font, a printer-defined default character
is used; there can be a different default character for each
data type. For an undefined character within a data-object
font, a special character (represented by glyph index 0 for
a TrueType or OpenType font) is used. When a code page
that contains a default character is used with a data object
font and when the printer supports a default character in a
code page (indicated by property pair X'B004' in the
Loaded-Font command-set vector of an STM reply), the
default character is used instead of glyph index 0.
Page Continuation Action: For an undefined character in
bar code data, ignore the command and continue with the
next command. For an undefined character within an LF1-
type or LF3-type coded font, the character is processed as
if it had been a defined character; for a double-byte code
point in an LF1-type restricted section (sections X'45'
through X'FE'), the print position is incremented using the
default variable space increment. For an undefined
character within a symbol-set coded font, a printer-defined
default character is used; there can be a different default
character for each data type. For an undefined character
within a data-object font, a special character (represented
by glyph index 0 for a TrueType or OpenType font) is used.
When a code page that contains a default character is
used with a data object font and when the printer supports
a default character in a code page (indicated by property
pair X'B004' in the Loaded-Font command-set vector of an
STM reply), the default character is used instead of glyph
index 0.
Support: Mandatory
Notes:
1. Reporting of this exception for an undefined character [IPDS-16-159]
within a font is controlled by the Report Undefined
Character Check bit in the XOA Exception-Handling
Control command.
2. This corresponds to an exception code defined by [IPDS-16-160]
BCOCA and PTOCA.
3. Undefined characters in GOCA character string data [IPDS-16-161]
are reported with exception ID X'03C3..01'.
4. Sense bytes 16–17 contain the undefined character's [IPDS-16-162]
code point. For double-byte fonts, sense byte 16
contains the section ID and sense byte 17 contains the
2nd byte of the code point. For single-byte fonts, sense
byte 17 contains the code point.
0821..00 Asynchronous undefined character [IPDS-16-163]
Action code: X'19'
Explanation: This exception was discovered after the
page had passed the Received Page Counter station. One
or more of the following conditions exists:
• An invalid or undefined character has been detected in [IPDS-16-164]
WBC command bar code data.
• An undefined character has been detected in the font [IPDS-16-165]
specified for text or bar code HRI data.
• A character has been detected in Write Text command [IPDS-16-166]
data that is undefined at the quality level specified by the
XOA-PQC command.
Alternate Exception Action: For an undefined character
in bar code data, there is no alternate exception action. For
an undefined character within an LF1-type or LF3-type
coded font, the character is processed as if it had been a
defined character; for a double-byte code point in an LF1-
type restricted section (sections X'45' through X'FE'), the
print position is incremented using the default variable
space increment. For an undefined character within a
symbol-set coded font, a printer-defined default character
is used; there can be a different default character for each
data type. For an undefined character within a data-object
font, a special character (represented by glyph index 0 for
a TrueType or OpenType font) is used. When a code page
that contains a default character is used with a data object
font and when the printer supports a default character in a
0821..00 • 0821..00 [IPDS-16-167]


code page (indicated by property pair X'B004' in the
Loaded-Font command-set vector of an STM reply), the
default character is used instead of glyph index 0.
Page Continuation Action: For an undefined character in
bar code data, ignore the command and continue with the
next command. For an undefined character within an LF1-
type or LF3-type coded font, the character is processed as
if it had been a defined character; for a double-byte code
point in an LF1-type restricted section (sections X'45'
through X'FE'), the print position is incremented using the
default variable space increment. For an undefined
character within a symbol-set coded font, a printer-defined
default character is used; there can be a different default
character for each data type. For an undefined character
within a data-object font, a special character (represented
by glyph index 0 for a TrueType or OpenType font) is used.
When a code page that contains a default character is
used with a data object font and when the printer supports
a default character in a code page (indicated by property
pair X'B004' in the Loaded-Font command-set vector of an
STM reply), the default character is used instead of glyph
index 0.
Support: Optional
Notes:
1. Reporting of this exception for an undefined character [IPDS-16-168]
within a font is controlled by the Report Undefined
Character Check bit in the XOA Exception-Handling
Control command.
2. This corresponds to an exception code defined by [IPDS-16-169]
BCOCA and PTOCA.
3. Undefined characters in GOCA character string data [IPDS-16-170]
are reported with exception ID X'03C3..01'.
4. Sense bytes 16–17 contain the undefined character's [IPDS-16-171]
code point. For double-byte fonts, sense byte 16
contains the section ID and sense byte 17 contains the
2nd byte of the code point. For single-byte fonts, sense
byte 17 contains the code point.
0829..00 Double-byte coded font section is not [IPDS-16-172]
activated or is invalid
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• The double-byte coded font section specified in a code [IPDS-16-173]
point is not activated.
• The double-byte coded font section ID specified in a [IPDS-16-174]
code point is invalid.
Alternate Exception Action: The print position is
incremented using the default variable space increment.
Page Continuation Action: The print position is
incremented using the default variable space increment.
Support: Mandatory
Notes:
1. Reporting of this exception is controlled by the Report [IPDS-16-175]
Undefined Character Check bit in the XOA Exception-
Handling Control command.
2. Sense bytes 16–17 should contain the code point that [IPDS-16-176]
caused the error. Some older IPDS printers do not
include this code point in the sense data and place
X'0000' in this field.
0860..00 Numeric representation precision check [IPDS-16-177]
Action code: X'01' or X'1F'
Explanation: The print position cannot be represented by
the printer.
Alternate Exception Action: None
Page Continuation Action: Ignore the command and
skip to the next END command.
Support: Mandatory
Notes:
1. Some printers report this exception as X'0215..01' or [IPDS-16-178]
X'0216..01'. The preferred Exception ID is X'0860..00'.
2. Some printers report this exception when an invalid [IPDS-16-179]
units per unit base value (X'0000') is found in a BCDD
structure. The preferred exception, in this case, is
X'0206..05'.
08C1..00 Position check
Action code: X'01' or X'1F'
Explanation: An attempt was made to print outside the
valid printable area.
Alternate Exception Action: All printing outside the valid
printable area is suppressed. All data and controls
continue processing. The printer continues to print within
the valid printable area to the greatest possible extent. For
text, this might mean truncating text lines at the character
boundary closest to the edge of intersection or,
alternatively, printing part of a graphic character. For
graphics, this might mean truncating graphics pictures at
the pel closest to the boundary. For image, this might mean
truncating image scan lines at the pel closest to the
boundary or, alternatively, not printing any of the image if
any part of the image falls outside the valid printable area.
Page Continuation Action: All printing outside the valid
printable area is suppressed. All data and controls
continue processing. The printer continues to print within
the valid printable area to the greatest possible extent. If
there is not enough valid printable area for the printer to
highlight the exception ID on the logical page, the
highlighted exception ID is not printed.
Support: Mandatory
Notes:
1. When the data to be printed outside of the VPA is [IPDS-16-180]
blank (no toned pels), some printers suppress this
0829..00 • 08C1..00 [IPDS-16-181]


exception ID, and other printers generate it. The
preferred action is to suppress the exception ID.
2. Some printers report this exception as X'0411..00' for [IPDS-16-182]
bar code VPA errors and X'020A..05' for VPA errors in
other data types; other printers report this exception as
X'020A..05' for VPA errors in all data types. The
preferred Exception ID is X'0411..00' for bar code VPA
errors and X'08C1..00' for VPA errors in all other data
types.
3. Reporting of this exception is controlled by the Report [IPDS-16-183]
Page Position Check bit in the XOA Exception-
Handling Control command.
4. This corresponds to an exception code defined by [IPDS-16-184]
PTOCA.
08C1..00 Asynchronous Position check
Action code: X'19'
Explanation: An attempt was made to print outside the
valid printable area. This exception was discovered after
the page had passed the Received Page Counter station.
Alternate Exception Action: All printing outside the valid
printable area is suppressed. All data and controls
continue processing. The printer continues to print within
the valid printable area to the greatest possible extent. For
text, this might mean truncating text lines at the character
boundary closest to the edge of intersection or,
alternatively, printing part of a graphic character. For
graphics, this might mean truncating graphics pictures at
the pel closest to the boundary. For image, this might mean
truncating image scan lines at the pel closest to the
boundary or, alternatively, not printing any of the image if
any part of the image falls outside the valid printable area.
Page Continuation Action: All printing outside the valid
printable area is suppressed. All data and controls
continue processing. The printer continues to print within
the valid printable area to the greatest possible extent. If
there is not enough valid printable area for the printer to
highlight the exception ID on the logical page, the
highlighted exception ID is not printed.
Support: Optional
Notes:
1. When the data to be printed outside of the VPA is [IPDS-16-185]
blank (no toned pels), some printers suppress this
exception ID, and other printers generate it. The
preferred action is to suppress the exception ID.
2. Some printers report this exception as X'0411..00' for [IPDS-16-186]
bar code VPA errors and X'020A..05' for VPA errors in
other data types; other printers report this exception as
X'020A..05' for VPA errors in all data types. The
preferred Exception ID is X'0411..00' for bar code VPA
errors and X'08C1..00' for VPA errors in all other data
types.
3. Reporting of this exception is controlled by the Report [IPDS-16-187]
Page Position Check bit in the XOA Exception-
Handling Control command.
4. This corresponds to an exception code defined by [IPDS-16-188]
PTOCA.
08C2..00 Included page position check
Action code: X'01'
Explanation: One or more of the following conditions
exists:
• A portion of a saved page included with an Include [IPDS-16-189]
Saved Page command extends outside of the physical
printable area.
• A portion of an overlay saved with a page and included [IPDS-16-190]
with an Include Saved Page command, extends outside
of the physical printable area.
• A user-printable area has been specified and a portion of [IPDS-16-191]
a saved page or an overlay saved with the page extends
outside of the user-printable area.
Alternate Exception Action: The saved page or page
overlay is trimmed to fit within the appropriate printable
area.
Page Continuation Action: The saved page or page
overlay is trimmed to fit within the appropriate printable
area.
Support: Mandatory
Notes:
1. The page ID from the Begin Page command of the [IPDS-16-192]
page that contains the ISP command is returned in
sense bytes 20–23.
2. Reporting of this exception is controlled by the Report [IPDS-16-193]
Page Position Check bit in the XOA Exception-
Handling Control command.
08C3..00 Saved page position check
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• A portion of a page to be saved extends outside of the [IPDS-16-194]
current logical page.
• A portion of an overlay to be saved with a page extends [IPDS-16-195]
outside of the overlay's current logical page.
Alternate Exception Action: The saved page or page
overlay is trimmed to fit within the appropriate current
logical page.
Page Continuation Action: The saved page or page
overlay is trimmed to fit within the appropriate current
logical page.
Support: Mandatory
Notes:
1. The page sequence number associated with the saved [IPDS-16-196]
page is returned in sense bytes 20–23.
08C1..00 • 08C3..00


2. Reporting of this exception is controlled by the Report [IPDS-16-197]
Page Position Check bit in the XOA Exception-
Handling Control command.
08C3..00 • 08C3..00


Specification Checks—Metadata Exceptions
A specification check—metadata exception indicates
the IPDS receiver has received a metadata
command with an invalid or unsupported data
parameter or value.
Each of these exceptions correspond to an exception
code specified by the MOCA architecture.
Format 0 is used for all metadata specification check
exceptions.
0601..00 Invalid metadata length [IPDS-16-198]
Action code: X'01' or X'1F'
Explanation: The length of the entire metadata object is
invalid.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..00 Invalid metadata header length [IPDS-16-199]
Action code: X'01' or X'1F'
Explanation: The length of the header of the metadata
object is invalid.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..10 Invalid metadata name [IPDS-16-200]
Action code: X'01' or X'1F'
Explanation: The name of the metadata object specified
in the MO header is not a valid UTF-16BE-encoded
character string.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..20 Invalid or unsupported metadata type [IPDS-16-201]
Action code: X'01' or X'1F'
Explanation: The metadata type specified in the MO
header is invalid or unsupported.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..30 Invalid or unsupported metadata format [IPDS-16-202]
Action code: X'01' or X'1F'
Explanation: The metadata format specified in the MO
header is invalid or unsupported.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..40 Invalid or unsupported metadata [IPDS-16-203]
compression
Action code: X'01' or X'1F'
Explanation: The compression type specified in the MO
header is invalid or unsupported.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0602..50 Invalid metadata name length [IPDS-16-204]
Action code: X'01' or X'1F'
Explanation: The length of the metadata name in the MO
header is invalid.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Refer to Metadata Object Content Architecture
Reference.
0603..00 Invalid metadata [IPDS-16-205]
Action code: X'01' or X'1F'
Explanation: The metadata carried in the MOData field of
the MO is not valid metadata, in regards to the specification
in the MO header. One of the following conditions exists:
1. The metadata is not of the specified type. [IPDS-16-206]
2. The metadata is not of the specified format. [IPDS-16-207]
3. The metadata is not compressed using the specified [IPDS-16-208]
compression format; for example, decompression has
failed.
4. The metadata is invalid, unsupported, or missing. This [IPDS-16-209]
corresponds to an error in the actual data itself; for
example, mostly correct XMP metadata that has some
specific error inside the XMP structures.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
0601..00 • 0603..00 [IPDS-16-210]


Support: Refer to Metadata Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0603..00 • 0603..00 [IPDS-16-211]


Specification Checks—IO-Image Exceptions
A specification check—IO-Image exception indicates
the printer has received an IO-Image command with
an invalid or unsupported data parameter or value.
Each of these exceptions correspond to an exception
code specified by the IOCA architecture.
Format 0 is used for all IO-Image specification check
exceptions.
0500..01 Invalid or unsupported IO-Image self- [IPDS-16-212]
defining field code
Action code: X'01' or X'1F'
Explanation: An invalid or unsupported IOCA self-
defining field code was encountered within an IO-Image
segment.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 contain the IOCA self-defining
field code that caused the error. For one-byte codes,
sense byte 16 contains X'00' and sense byte 17
contains the code.
0500..03 Invalid or unsupported IO-Image self- [IPDS-16-213]
defining field length
Action code: X'01' or X'1F'
Explanation: The length field of one of the following is
invalid or unsupported:
Band Image Data self-defining field
Band Image Parameter
Begin Image Content self-defining field
Begin Segment self-defining field
Begin Tile Parameter
Begin Transparency Mask Parameter
End Image Content self-defining field
End Segment self-defining field
End Tile Parameter
End Transparency Mask Parameter
External Algorithm Specification Parameter
IDE Structure Parameter
Image Data Element Size Parameter
Image Data self-defining field
Image Encoding Parameter
Image Look Up Table ID Parameter
Image Size Parameter
Image Subsampling Parameter
Include Tile Parameter
nColor Names Parameter
Set Extended Bilevel Image Color (X'F4')
self-defining field
Tile Position Parameter
Tile Set Color Parameter
Tile Size Parameter
Tile TOC Parameter
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 contain the IOCA self-defining
field code that caused the error. For one-byte codes,
sense byte 16 contains X'00' and sense byte 17
contains the code.
0500..04 Invalid IO-Image self-defining field value [IPDS-16-214]
Action code: X'01' or X'1F'
Explanation: A field value of one of the following is
invalid:
Band Image Data self-defining field
Band Image Parameter
Begin Image Content self-defining field
Image Data Element Size Parameter
Image Size Parameter
Image Subsampling Parameter
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Notes:
1. When an appropriate X'05xx..10' exception is available [IPDS-16-215]
for the condition being reported, it is recommended to
report that X'05xx..10' exception rather than this
X'0500..04' exception.
2. Sense bytes 16–17 contain the IOCA self-defining field [IPDS-16-216]
code that caused the error. For one-byte codes, sense
byte 16 contains X'00' and sense byte 17 contains the
code.
0570..0F IO-Image Begin Segment out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. Either a Begin
Segment was missing, was encountered out of sequence,
or appeared more than once. A Begin Segment must be
the first IOCA self-defining field in the series of WI2
commands.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
0500..01 • 0570..0F [IPDS-16-217]


Support: Refer to Image Object Content Architecture
Reference.
0571..0F IO-Image End Segment out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. Either an End
Segment was missing or was encountered out of
sequence. An End Segment must follow an End Image
Content.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
058C..0F Begin Tile Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A Begin Tile
Parameter was encountered out of sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
058D..0F End Tile Parameter missing or out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An End Tile
Parameter is missing after a Begin Tile has been
encountered or was encountered out of sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
058E..0F Begin Transparency Mask Parameter out
of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A Begin
Transparency Mask Parameter was encountered out of
sequence or appeared more than once in an image or tile.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
058F ..0F End Transparency Mask Parameter
missing or out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An End
Transparency Mask Parameter is missing after a Begin
Transparency Mask has been encountered or was
encountered out of sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0591..0F IO-Image Begin Image Content out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. Either a Begin
Image Content was missing, was encountered out of
sequence, or (for a printer that does not support multiple
image contents) appeared more than once. A Begin Image
Content must follow a Begin Segment.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0592..01 IO-Image Image Data self-defining field [IPDS-16-218]
invalid
Action code: X'01' or X'1F'
Explanation: An Image Data self-defining field was
specified that should not be present because the Band
Image Parameter was specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0592..0F IO-Image Image Data self-defining field out
of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Image Data
self-defining field was encountered out of sequence. Image
Data self-defining fields must appear after image data
parameters and before End Image Content.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0593..0F IO-Image End Image Content out of
sequence
Action code: X'01' or X'1F'
0571..0F • 0593..0F


Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. Either an End
Image Content was missing, was encountered out of
sequence, or (for a printer that does not support multiple
image contents) appeared more than once. An End Image
Content must follow an Image Data self-defining field, a
Band Image Data self-defining field, or an End Tile self-
defining field.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0594..01 Inconsistent Image Size Parameter value [IPDS-16-219]
and Image Data
Action code: X'01' or X'1F'
Explanation: The size detected from the image data is
different from the horizontal or vertical size in the Image
Size Parameter.
Alternate Exception Action: Use the detected horizontal
or vertical size as the source image size and not the
value(s) of the Image Size Parameter.
Page Continuation Action: Use the detected horizontal
or vertical size as the source image size and not the
value(s) of the Image Size parameter.
Support: Refer to Image Object Content Architecture
Reference.
0594..0F IO-Image Image Size Parameter missing or
out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. Either an
Image Size Parameter did not appear after a Begin Image
Content and before the image data, or it appeared more
than once between the two self-defining fields.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0594..10 IO-Image Image Size Parameter value [IPDS-16-220]
unsupported
Action code: X'01' or X'1F'
Explanation: A value within an Image Size Parameter is
unsupported. An unsupported unit base value was
specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0594..11 IO-Image Image Size cannot be determined [IPDS-16-221]
Action code: X'01' or X'1F'
Explanation: One of the following exists:
1. The horizontal size (bytes 7,8) or vertical size (bytes [IPDS-16-222]
9,10) of the Image Size Parameter is zero, but the size
of the image in that direction is not detectable from the
image data.
2. An Image Size Parameter within a transparency mask [IPDS-16-223]
does not match the dimensions of the containing tile.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0595..0F IO-Image Image Encoding Parameter out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Image
Encoding Parameter was encountered out of sequence or
appeared more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0595..10 IO-Image Image Encoding Parameter value [IPDS-16-224]
unsupported
Action code: X'01' or X'1F'
Explanation: A value within an Image Encoding
Parameter is unsupported. An unsupported compression
algorithm, recording algorithm, or bit ordering was
specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0595..11 IO-Image decompression error [IPDS-16-225]
Action code: X'01' or X'1F'
Explanation: An error was encountered while
decompressing IO-Image data under the following
conditions:
1. The image data was not encoded according to the [IPDS-16-226]
compression or recording algorithm specified in the
Image Encoding Parameter.
2. The image data could not be decoded successfully [IPDS-16-227]
using the size values specified in the Image Size
Parameter. This condition applies to compression or
0594..01 • 0595..11 [IPDS-16-228]


recording algorithms that do not permit the image size
to be encoded in the image data.
3. The image data was not in complete accordance with [IPDS-16-229]
the compression algorithm specified in the Image
Encoding Parameter.
4. The image data is encoded using the algorithm [IPDS-16-230]
specified in the Image Encoding Parameter, but uses a
function of the algorithm that is unsupported by the
receiver.
Alternate Exception Action: Printers should attempt to
present or make use of all successfully decompressed
image data. The resulting partial image might differ from
the original image.
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0596..0F IO-Image Image Data Element Size
Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Image Data
Element Size Parameter was encountered out of sequence
or appeared more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0596..10 IO-Image Image Data Element Size [IPDS-16-231]
Parameter value unsupported
Action code: X'01' or X'1F'
Explanation: A value within an Image Data Element Size
Parameter is unsupported. An unsupported Number of Bits
per Image Point value was specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0596..11 IO-Image Image Data Element Size [IPDS-16-232]
Parameter and Image Encoding Parameter
inconsistent
Action code: X'01' or X'1F'
Explanation: An image compression algorithm was
specified in the Image Encoding Parameter (byte 2
contained a value other than X'03'), but the Image Data
Element Size Parameter specified a value that is not
supported with that compression algorithm.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0597..0F IO-Image Image Look Up Table ID
Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Image Look
Up Table ID Parameter was encountered out of sequence
or appeared more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0597..10 IO-Image Image Look Up Table ID [IPDS-16-233]
Parameter value unsupported
Action code: X'01' or X'1F'
Explanation: A value in an Image Look Up Table ID
Parameter is unsupported. An unsupported Look Up Table
ID value was specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0598..01 Inconsistent Band Image Parameter and [IPDS-16-234]
Image Subsampling Parameter
Action code: X'01' or X'1F'
Explanation: A Band Image Parameter was specified in
an IO Image and a subsequent Image Subsampling
Parameter was also found. A Band Image Parameter and
an Image Subsampling Parameter cannot coexist in the
same image.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0598..0F IO-Image Band Image Parameter out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A Band Image
Parameter was encountered out of sequence or appeared
more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0596..0F • 0598..0F


0598..10 IO-Image Band Image Parameter value [IPDS-16-235]
invalid or unsupported
Action code: X'01' or X'1F'
Explanation: A value within a Band Image Parameter is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0598..14 IO-Image Band Image Parameter values [IPDS-16-236]
inconsistent
Action code: X'01' or X'1F'
Explanation: The number of BITCNT parameters in a
Band Image Parameter is not equal to the BCOUNT value.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
0598..15 IO-Image Band Image Parameter [IPDS-16-237]
inconsistent with IDE Size Parameter
Action code: X'01' or X'1F'
Explanation: The IDE size determined by the Band Image
Parameter (after subtracting padding bits) does not match
the IDE Size Parameter.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059B..0F IO-Image IDE Structure Parameter out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An IDE
Structure Parameter was required but missing, was
encountered out of sequence, or appeared more than
once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059B..10 IO-Image IDE Structure Parameter value
invalid or unsupported
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in an IDE Structure Parameter:
1. The value of ASFLAG is invalid or unsupported. [IPDS-16-238]
2. The value of GRAYCODE is invalid or unsupported. [IPDS-16-239]
3. The value of FORMAT is invalid or unsupported. [IPDS-16-240]
4. The value of a SIZE field is invalid or unsupported. [IPDS-16-241]
This case is reported in bytes 16–17 in a special way;
see the note below.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error. To report a
problem with field SIZEn, bytes 16–17 should
contain the value 3+n; for example, for the SIZE2
field, bytes 16–17 would be set to X'0005'.
059B..18 IO-Image IDE Structure Parameter
inconsistent with IDE Size Parameter
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in an IDE Structure Parameter:
1. The sum of the SIZE [IPDS-16-242]
values in an IDE Structure
Parameter does not match the IDE size specified in
the IDE Size Parameter.
2. The color space is CMYK and SIZE4 is missing. [IPDS-16-243]
3. SIZE4 is present and the color space is not CMYK or [IPDS-16-244]
nColor.
4. More than four SIZE parameters are present and the [IPDS-16-245]
color space is not nColor.
5. The color space is nColor and the number of SIZE [IPDS-16-246]
parameters is not equal to the second half of the
FORMAT byte.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
059C..01 IO-Image Band Image Data self-defining
field invalid
Action code: X'01' or X'1F'
Explanation: A Band Image Data self-defining field was
specified that should not be present because the Band
Image Parameter was not specified.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
0598..10 • 059C..01 [IPDS-16-247]


Support: Refer to Image Object Content Architecture
Reference.
059C..0F IO-Image Band Image Data self-defining
field missing or out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A Band Image
Data self-defining field was missing or encountered out of
sequence. When a Band Image Parameter is specified,
one or more Band Image Data self-defining fields must
appear after image data parameters and before End Image
Content.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059C..17 Invalid number or sequence of Band Image
Data self-defining fields
Action code: X'01' or X'1F'
Explanation: The number or sequence of Band Image
Data self-defining fields within a series of WI2 commands
is invalid. Either some of the bands specified in the Band
Image Parameter were missing in the Band Image Data or
the Band Image Data self-defining fields were not in the
sequential order of the band numbers.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059F ..01 Inconsistent External Algorithm
Specification Parameter and Image
Encoding Parameter
Action code: X'01' or X'1F'
Explanation: One of the following exists:
1. An External Algorithm Specification Parameter is [IPDS-16-248]
specified in an IO Image, but a corresponding Image
Encoding Parameter is not found.
2. An Image Encoding Parameter is specified in an IO [IPDS-16-249]
Image that requires an External Algorithm
Specification Parameter, but no External Algorithm
Specification Parameter is found.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
059F ..0F IO-Image External Algorithm Specification
Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An External
Algorithm Specification Parameter has been encountered
out of sequence or appears more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059F ..10 IO-Image External Algorithm Specification
Parameter value invalid or unsupported
Action code: X'01' or X'1F'
Explanation: A value within an External Algorithm
Specification Parameter is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
059F ..11 IO-Image External Algorithm Specification
Parameter not needed
Action code: X'01' or X'1F'
Explanation: An External Algorithm Specification
Parameter is specified in the image, but the Image
Encoding Parameter does not require it.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05A9..02 IO-Image data outside of the Image
Presentation Space
Action code: X'01' or X'1F'
Explanation: A portion of the IO-Image data is specified
outside of the Image Presentation Space.
Alternate Exception Action: The part of the IO Image
extending outside of the Image Presentation Space is
discarded. Portions of the Image Presentation Space that
contain no image data are filled with zeros, then the image
is mapped to the Image object area.
Page Continuation Action: The part of the IO Image
extending outside of the Image Presentation Space is
discarded. Portions of the Image Presentation Space that
contain no image data are filled with zeros, then the image
is mapped to the Image object area.
Support: Refer to Image Object Content Architecture
Reference.
05B3..0F nColor Names Parameter out of sequence
Action code: X'01' or X'1F'
059C..0F • 05B3..0F


Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An nColor
Names Parameter has been encountered out of sequence
or appears more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B3..10 Invalid nColor Names Parameter value
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in an nColor Names Parameter:
1. A NAMELEN value is an odd value, or is greater than [IPDS-16-250]
X'FA'.
2. A color name is not a valid UTF-16BE character string. [IPDS-16-251]
3. A color name appears more than once. [IPDS-16-252]
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
05B3..11 Inconsistent nColor Names Parameter
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in an nColor Names Parameter:
1. The nColor Names Parameter should not be present [IPDS-16-253]
because the nColor color model was not specified in
the IDE Structure Parameter.
2. The number of color names specified in the nColor [IPDS-16-254]
Names Parameter does not match the number of
colors in the nColor color model specified in the IDE
Structure Parameter.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
05B5..0F Tile Position Parameter missing or out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A required Tile
Position Parameter is missing or has been encountered out
of sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B5..10 Invalid Tile Position Parameter value
Action code: X'01' or X'1F'
Explanation: A XOFFSET or YOFFSET value within a Tile
Position Parameter is invalid or is outside of the image
presentation space.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B5..11 Inconsistent Tile Position Parameter
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists in a
Tile Position Parameter:
1. Tiles are specified out of order. This exception can [IPDS-16-255]
occur only if the Tile TOC Parameter does not contain
any TOC entries. If the Tile TOC Parameter does
contain TOC entries, the tiles themselves can be
specified in any order.
2. The Tile TOC Parameter does contain the table of [IPDS-16-256]
contents, but the XOFFSET or YOFFSET given for this
tile in the table of contents does not match the
corresponding value specified in the Tile Position
Parameter.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
05B6..0F Tile Size Parameter missing or out of
sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A required Tile
Size Parameter is missing or has been encountered out of
sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B6..10 Invalid or unsupported Tile Size Parameter
value
Action code: X'01' or X'1F'
05B3..10 • 05B6..10


Explanation: A value within a Tile Size Parameter is
invalid or unsupported. Some IOCA function sets restrict
support for some values of the relative resolution
(RELRES) field.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B6..11 Inconsistent Tile Size Parameter
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a Tile Size Parameter:
1. The tile overlaps a previously specified tile. [IPDS-16-257]
2. The RELRES value specified in the table of contents [IPDS-16-258]
does not match the corresponding RELRES value in
the Tile Size Parameter.
3. The THSIZE or TVSIZE specified in the table of [IPDS-16-259]
contents does not match the corresponding value in
the Tile Size Parameter.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
05B7..0F Tile Set Color Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. A Tile Set Color
Parameter has been encountered out of sequence or
appears more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B7..10 Invalid Tile Set Color Parameter value
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a Tile Set Color Parameter:
1. An invalid color space (CSPACE) value is specified. [IPDS-16-260]
2. An invalid size value is specified. [IPDS-16-261]
3. An invalid color value is specified. [IPDS-16-262]
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Notes:
1. For printers that support color fidelity control, reporting [IPDS-16-263]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-264]
number of a specific cause for the error.
05B7..11 Inconsistent Tile Set Color Parameter
Action code: X'01' or X'1F'
Explanation: A Tile Set Color Parameter is specified once
in the correct sequence, but the tile is not a bilevel tile. The
IDE Size Parameter, if specified, must specify an IDE size
of 1 bit per IDE. The IDE Structure Parameter, if specified,
must specify a color space of either YCrCb or YCbCr.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05B8..0F Include Tile Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Include Tile
Parameter has been encountered out of sequence or
appears more than once. An Include Tile Parameter must
appear immediately after a Tile Position Parameter.
Alternate Exception Action: None
Page Continuation Action: Ignore the Include Tile
Parameter and continue processing the image.
Support: Refer to Image Object Content Architecture
Reference.
05B8..11 Inconsistent Include Tile Parameter
Action code: X'01' or X'1F'
Explanation: An Include Tile parameter is specified within
an IOCA tile resource. Nested references are not allowed.
Alternate Exception Action: None
Page Continuation Action: Ignore the Include Tile
parameter and continue processing the image.
Support: Refer to Image Object Content Architecture
Reference.
05BB..0F Tile TOC Parameter out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. In a tiled
image, the Tile TOC Parameter does not appear
immediately after Begin Image Content or appears more
than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
05B6..11 • 05BB..0F


Support: Refer to Image Object Content Architecture
Reference.
05BB..10 Invalid Tile TOC Parameter value
Action code: X'01' or X'1F'
Explanation: A value within a Tile TOC Parameter is
invalid.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05BB..11 Inconsistent Tile TOC Parameter
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a Tile TOC Parameter:
1. Not all tiles are listed in the table of contents, even [IPDS-16-265]
though the table of contents contains at least one tile.
2. The table of contents lists a non-existent tile. [IPDS-16-266]
3. Invalid tile order; two or more tiles in the table of [IPDS-16-267]
contents have sort keys (primary: YOFFSET ,
secondary: XOFFSET) that are identical or are out of
sequence.
4. The specified offset for one or more tiles does not point [IPDS-16-268]
to a position where a Begin Tile Parameter starts.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
05CE..01 Inconsistent Image Subsampling
Parameter and Band Image Parameter
Action code: X'01' or X'1F'
Explanation: An Image Subsampling Parameter is
specified in an IO Image and a subsequent Band Image
Parameter is also found. A Band Image Parameter and an
Image Subsampling Parameter cannot coexist in the same
image.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05CE..0F IO-Image Image Subsampling Parameter
out of sequence
Action code: X'01' or X'1F'
Explanation: The sequence of IOCA self-defining fields
within a series of WI2 commands is invalid. An Image
Subsampling Parameter has been encountered out of
sequence or appears more than once.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05CE..10 IO-Image Image Subsampling Parameter
value invalid or unsupported
Action code: X'01' or X'1F'
Explanation: A value within an Image Subsampling
Parameter is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Image Object Content Architecture
Reference.
05F4..10 Invalid Set Extended Bilevel Image Color
value
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a Set Extended Bilevel Image Color (X'F4') IOCA
self-defining field:
1. An invalid color space value was specified. [IPDS-16-269]
2. An invalid size value was specified. [IPDS-16-270]
3. An invalid color value was specified. [IPDS-16-271]
Alternate Exception Action: Ignore the IOCA self-
defining field
Page Continuation Action: Ignore the IOCA self-defining
field
Support: Optional
Notes:
1. For printers that support color fidelity control, reporting [IPDS-16-272]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-273]
number of a specific cause for the error.
05BB..10 • 05F4..10


Specification Checks—Bar Code Exceptions
A specification check—bar code exception indicates
the printer has received a bar code command with an
invalid or unsupported data parameter or value.
Each of these exceptions correspond to an exception
code specified by the BCOCA architecture.
Format 0 is used for all bar code specification check
exceptions, except for exception ID X'0411..00', that
uses either format 1 or format 7.
0403..00 Invalid or unsupported bar code type [IPDS-16-274]
Action code: X'01' or X'1F'
Explanation: The bar code type specified in the bar code
data-descriptor self-defining field is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Bar Code Object Content Architecture
Reference
0404..00 Unsupported font local ID or font not [IPDS-16-275]
available
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A font local ID specified in the bar code data descriptor [IPDS-16-276]
self-defining field is unsupported.
2. A font local ID specified in the bar code data descriptor [IPDS-16-277]
self-defining field has not been mapped to a font using
the LFE command.
3. A font local ID specified in the bar code data descriptor [IPDS-16-278]
self-defining field has been mapped in the current LFE,
but the coded font or data-object font is not activated.
4. For those symbologies that require a specific type [IPDS-16-279]
style or code page for HRI, the BCOCA receiver
cannot determine the type style or code page of the
specified coded font or data-object font.
Alternate Exception Action: If the exception occurs
because a font defined within the current LFE command is
not activated in the printer when needed, the printer can try
to make an appropriate font substitution that preserves as
many characteristics as possible of the originally requested
font while still preserving the original code page. Some bar
code symbologies specify a set of type styles to be used
for HRI data; font substitution for HRI data must follow the
bar code symbology being used. If an appropriate font
substitution cannot be made or if the exception occurs for
any other reason, there is no AEA.
Page Continuation Action: If the exception occurs
because a font defined within the current LFE command is
not activated in the printer when needed, the printer tries to
make an appropriate font substitution that preserves as
many characteristics as possible of the font originally
requested while still preserving the original code page.
Some bar code symbologies specify a set of type styles to
be used for HRI data; font substitution for HRI data must
follow the bar code symbology being used. If the exception
occurs for any other reason, there is no PCA.
Support: Refer to Bar Code Object Content Architecture
Reference
Notes:
1. Some printers report this exception as X'0218..02'. [IPDS-16-280]
The preferred Exception ID is X'0404..00' when a font
to be used with bar code data is unavailable.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-281]
number of a specific cause for the error.
0405..00 Invalid or unsupported bar code color [IPDS-16-282]
Action code: X'01' or X'1F'
Explanation: The color specified in the bar code data-
descriptor self-defining field is invalid or unsupported.
Alternate Exception Action: Use a highlight color if one
is available, otherwise use the printer default color.
Page Continuation Action: Use a highlight color if one is
available, otherwise use the printer default color.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
0406..00 Invalid or unsupported module width [IPDS-16-283]
Action code: X'01' or X'1F'
Explanation: The module width specified in the bar code
data-descriptor self-defining field is invalid or unsupported.
Alternate Exception Action: The printer uses the closest
smaller width, but if this smaller value is less than the
smallest supported width or zero, the printer uses the
smallest supported value. For those printers having only a
single (printer default) width, use the printer default value.
Page Continuation Action: The printer uses the closest
smaller width, but, if this smaller value is less than the
smallest supported width or zero, the printer uses the
smallest supported value. For those printers having only a
single (printer default) width, use the printer default value.
Support: Refer to Bar Code Object Content Architecture
Reference
0406..10 Invalid desired-symbol-width parameter [IPDS-16-284]
value
0403..00 • 0406..10 [IPDS-16-285]


Action code: X'01' or X'1F'
Explanation: The desired-symbol-width value specified in
the bar code data-descriptor self-defining field is invalid.
Alternate Exception Action: Proceed as if X'0000' had
been specified
Page Continuation Action: Proceed as if X'0000' had
been specified
Support: Mandatory if the desired-symbol-width
parameter is supported.
0406..11 Bar code symbol cannot fit within the [IPDS-16-286]
specified width
Action code: X'01' or X'1F'
Explanation: A desired-symbol-width value was specified
in the bar code data-descriptor self-defining field, but a bar
code symbol cannot be generated that fits within the
specified width.
Alternate Exception Action: Proceed as if X'0000' had
been specified; the resulting symbol is larger than the
desired symbol width.
Page Continuation Action: Proceed as if X'0000' had
been specified; the resulting symbol is larger than the
desired symbol width.
Support: Mandatory if the desired-symbol-width
parameter is supported.
Note: Sense bytes 16–17 contain the size of the smallest
valid symbol width in twips.
0407..00 Invalid or unsupported element height [IPDS-16-287]
Action code: X'01' or X'1F'
Explanation: The element height specified in the bar code
data-descriptor self-defining field is invalid or unsupported.
Alternate Exception Action: The printer uses the closest
smaller height, but if this smaller value is less than the
smallest supported element height or zero, the printer uses
the smallest supported value. For those printers having
only a single (printer default) height, use the printer default
value.
Page Continuation Action: The printer uses the closest
smaller height, but, if this smaller value is less than the
smallest supported element height or zero, the printer uses
the smallest supported value. For those printers having
only a single (printer default) height, use the printer default
value.
Support: Refer to Bar Code Object Content Architecture
Reference
0408..00 Invalid height multiplier [IPDS-16-288]
Action code: X'01' or X'1F'
Explanation: The height multiplier specified in the bar
code data-descriptor self-defining field is invalid.
Alternate Exception Action: The printer uses a multiplier
of X'01'.
Page Continuation Action: The printer uses a multiplier
of X'01'.
Support: Refer to Bar Code Object Content Architecture
Reference
0408..05 Invalid height values for GS1 DataBar bar [IPDS-16-289]
code modifier
Action code: X'01' or X'1F'
Explanation: The combination of element height and
height multiplier values specified for a GS1 DataBar bar
code in the Bar Code Data Descriptor self-defining field is
invalid for the specified modifier.
Alternate Exception Action: The printer uses the
smallest height defined for the GS1 DataBar modifier
value.
Page Continuation Action: The printer uses the smallest
height defined for the GS1 DataBar modifier value.
Support: Refer to Bar Code Object Content Architecture
Reference
0409..00 Invalid or unsupported wide-to-narrow [IPDS-16-290]
ratio
Action code: X'01' or X'1F'
Explanation: The wide-to-narrow ratio specified in the bar
code data-descriptor self-defining field is invalid or
unsupported.
Alternate Exception Action: The printer uses the default
wide-to-narrow ratio. The default ratio must be in the range
of 2.25 through 3.00. For the MSI code only, however, the
default wide-to-narrow ratio should be 2.00.
Page Continuation Action: The printer uses the default
wide-to-narrow ratio. The default ratio must be in the range
of 2.25 through 3.00.
Support: Refer to Bar Code Object Content Architecture
Reference
040A..00 Invalid or unsupported bar code origin
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a WBC command:
1. The Xoffset value is invalid or unsupported. [IPDS-16-291]
2. The Yoffset value is invalid or unsupported. [IPDS-16-292]
Alternate Exception Action: None
Page Continuation Action: Ignore the command and
continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040B..00 Invalid or unsupported bar code modifier
Action code: X'01' or X'1F'
0406..11 • 040B..00 [IPDS-16-293]


Explanation: The bar code modifier in byte 17 of the bar
code data-descriptor self-defining field is invalid or
unsupported for the bar code type specified in byte 16 of
the same self-defining field.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Bar Code Object Content Architecture
Reference
040C..00 Invalid or unsupported bar code data
length
Action code: X'01' or X'1F'
Explanation: The length of the variable data (as given in
bytes 5 to end of a WBC command) to be processed, plus
any printer-generated check digits to be processed, is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the command and
continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040E..00 Check-digit calculation exception
Action code: X'01' or X'1F'
Explanation: The first check-digit calculation resulting in a
value of 10 is defined as an exception in some of the
modifier options (byte 17 of the bar code data-descriptor
self-defining field) for an MSI bar code.
Alternate Exception Action: None
Page Continuation Action: Ignore the command and
continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..00 Unsupported 2D bar code size
Action code: X'01' or X'1F'
Explanation: Either the matrix row size value or the
number of rows value specified in the BSA data structure in
a Write Bar Code command is unsupported. Both of these
values must be within the range of supported sizes for the
symbology.
Alternate Exception Action: The printer uses X'0000' for
the unsupported value so that an appropriate size is used
based on the amount of symbol data.
Page Continuation Action: The printer uses X'0000' for
the unsupported value so that an appropriate size is used
based on the amount of symbol data.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..01 Invalid structured append sequence
indicator
Action code: X'01' or X'1F'
Explanation: An invalid structured append sequence
indicator was specified in the BSA data structure in a Write
Bar Code command. For a Data Matrix or QR Code
symbol, the sequence indicator must be between 1 and 16
inclusive. For a MaxiCode symbol, the sequence indicator
must be between 1 and 8 inclusive. For an Aztec Code
symbol, the sequence indicator must be between 1 and 26,
inclusive.
Alternate Exception Action: The bar code symbol is
printed without structured append information.
Page Continuation Action: The bar code symbol is
printed without structured append information.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..02 Structured append sequence indicator too
large
Action code: X'01' or X'1F'
Explanation: A structured append sequence indicator
specified in the BSA data structure in a Write Bar Code
command is larger than the total number of structured
append symbols.
Alternate Exception Action: The bar code symbol is
printed without structured append information.
Page Continuation Action: The bar code symbol is
printed without structured append information.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..03 Mismatched structured append
information
Action code: X'01' or X'1F'
Explanation: Mismatched structured append information
was specified in the BSA data structure in a Write Bar
Code command. One of the sequence-indicator and total-
number-of-symbols parameters was X'00', but the other
was not X'00'.
Alternate Exception Action: The bar code symbol is
printed without structured append information.
Page Continuation Action: The bar code symbol is
printed without structured append information.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..04 Invalid number of structured append
symbols
Action code: X'01' or X'1F'
Explanation: An invalid number of structured append
symbols was specified in the BSA data structure in a Write
Bar Code command. For a Data Matrix or QR Code
symbol, the total number of symbols must be between 2
and 16 inclusive. For a MaxiCode symbol, the total number
of symbols must be between 2 and 8 inclusive. For an
Aztec Code symbol, the total number of symbols must be
between 2 and 26, inclusive.
040C..00 • 040F ..04


Alternate Exception Action: The bar code symbol is
printed without structured append information.
Page Continuation Action: The bar code symbol is
printed without structured append information.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..05 Invalid symbol mode value
Action code: X'01' or X'1F'
Explanation: An invalid symbol mode value was specified
in the BSA data structure in a Write Bar Code command.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..06 Invalid data symbol characters per row
value
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, an invalid data
symbol characters per row value was specified in the BSA
data structure in a Write Bar Code command.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..07 Invalid desired number of rows value
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, one of the following
conditions exists in the BSA data structure in a Write Bar
Code command:
1. An invalid desired number of rows value is specified. [IPDS-16-294]
2. The number of rows times the number of data symbol [IPDS-16-295]
characters is greater than 928.
Alternate Exception Action: Proceed as if X'FF' was
specified.
Page Continuation Action: Proceed as if X'FF' was
specified.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..08 Too much data for a PDF417 bar code
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, too much data is
specified in the BSA data structure in a Write Bar Code
command.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..09 Invalid security level value
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, an invalid security
level value is specified in the BSA data structure in a Write
Bar Code command.
Alternate Exception Action: Proceed as if security level
8 was specified. [IPDS-16-296]
Page Continuation Action: Proceed as if security level 8
was specified.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..0A Incompatible combination of Data Matrix
parameters
Action code: X'01' or X'1F'
Explanation: An incompatible combination of Data Matrix
parameters is specified in the WBC command. One or
more of the following conditions exists:
1. A structured append is specified, but either the reader [IPDS-16-297]
programming flag is set to B'1' or a hdr/trl macro is
specified.
2. The GS1 FNC1 flag is set to B'1', but either the [IPDS-16-298]
industry FNC1 flag is set to B'1', the reader
programming flag is set to B'1', or a hdr/trl macro is
specified.
3. The industry FNC1 flag is set to B'1', but either the [IPDS-16-299]
GS1 FNC1 flag is set to B'1', the reader programming
flag is set to B'1', or a hdr/trl macro is specified.
4. The reader programming flag is set to B'1', but either a [IPDS-16-300]
structured append is specified, one of the FNC1 flags
is set to B'1', or a hdr/trl macro is specified.
5. A hdr/trl macro is specified, but either a structured [IPDS-16-301]
append is specified, one of the FNC1 flags is set to
B'1', or the reader programming flag is set to B'1'.
Alternate Exception Action: None
Page Continuation Action: Ignore the command and
continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..0B Invalid structured append file identification
value
Action code: X'01' or X'1F'
040F ..05 • 040F ..0B [IPDS-16-302]


Explanation: An invalid structured append file
identification value is specified in the WBC command.
Each byte of the 2-byte file identification value must be in
the range X'01'–X'FE'.
Alternate Exception Action: The bar code symbol is
printed without structured append information.
Page Continuation Action: The bar code symbol is
printed without structured append information.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..0C Invalid Macro PDF417 Control Block length
value
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, one or more of the
following exists:
1. An invalid Macro PDF417 Control Block length value is [IPDS-16-303]
specified in the BSA data structure in a Write Bar Code
command.
2. The length of the Macro PDF417 Control Block is too [IPDS-16-304]
large to fit in the Write Bar Code command.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..0D Invalid Macro PDF417 Control Block data
Action code: X'01' or X'1F'
Explanation: For a PDF417 symbol, an error occurred
within the data for a Macro PDF417 Control Block specified
in the BSA data structure in a Write Bar Code command.
Alternate Exception Action: The bar code symbol is
printed without a Macro PDF417 Control Block.
Page Continuation Action: The bar code symbol is
printed without a Macro PDF417 Control Block.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..0E Invalid or unsupported QR Code
conversion value
Action code: X'01' or X'1F'
Explanation: The conversion parameter value specified in
the BSA data structure in a Write Bar Code command is
invalid or unsupported. IPDS printers support the values
X'00'–X'03'
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..0F Invalid QR Code version value
Action code: X'01' or X'1F'
Explanation: The version parameter value specified in the
BSA data structure in a Write Bar Code command is
invalid.
Alternate Exception Action: The printer uses X'00' for
the version parameter so that an appropriate size is used
based on the amount of symbol data.
Page Continuation Action: The printer uses X'00' for the
version parameter so that an appropriate size is used
based on the amount of symbol data.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..10 Invalid QR Code error-correction-level
value
Action code: X'01' or X'1F'
Explanation: The error-correction-level parameter value
specified in the BSA data structure in a Write Bar Code
command is invalid.
Alternate Exception Action: The printer uses X'03' for
the error-correction-level parameter so that the maximum
error correction level is used.
Page Continuation Action: The printer uses X'03' for the
error-correction-level parameter so that the maximum error
correction level is used.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..11 Incompatible combination of QR Code
special-function flags
Action code: X'01' or X'1F'
Explanation: An incompatible combination of special-
function flags is specified in the BSA data structure in a
Write Bar Code command. Only one of the FNC1 flags can
be B'1'.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..12 Invalid QR Code application-indicator
value
Action code: X'01' or X'1F'
Explanation: The application-indicator value specified in
the BSA data structure in a Write Bar Code command is
invalid.
Alternate Exception Action: None
040F ..0C • 040F ..12 [IPDS-16-305]


Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
040F ..13 Invalid Intelligent Mail Package Barcode
banner string data
Action code: X'01' or X'1F'
Explanation: For an Intelligent Mail Package Barcode
symbol, an error occurred within the data for the Service
Banner string specified in the BSA data structure in a Write
Bar Code command. One of the following conditions exists:
1. The data was invalid. [IPDS-16-306]
2. The data resulted in a USPS Service Banner that was [IPDS-16-307]
too long to fit in the prescribed width of the symbol.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Intelligent Mail Package
Barcode is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..14 Missing Intelligent Mail Package Barcode
banner string data
Action code: X'01' or X'1F'
Explanation: For an Intelligent Mail Package Barcode
symbol, the USPS Service Banner is not suppressed yet
the Service Banner string provided has length 0.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Intelligent Mail Package
Barcode is supported.
040F ..15 Invalid length of Intelligent Mail Package
Barcode banner string data
Action code: X'01' or X'1F'
Explanation: For an Intelligent Mail Package Barcode
symbol, the length of the USPS Service Banner is not an
even value.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Intelligent Mail Package
Barcode is supported.
040F ..16 Too much data for a QR Code
Action code: X'01' or X'1F'
Explanation: Too much data is specified in the BSA data
structure in a Write Bar Code command, and the too-much-
data flag in the BSA forbids making the QR Code version
bigger to fit the data.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..17 Too much data for an Aztec Code
Action code: X'01' or X'1F'
Explanation: Too much data is specified in the BSA data
structure in a Write Bar Code command, and the too-much-
data flag in the BSA forbids making the Aztec Code version
bigger to fit the data.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..18 Invalid Aztec Code desired-number-of-
layers value
Action code: X'01' or X'1F'
Explanation: The desired-number-of-layers specified in
the BSA data structure in a Write Bar Code command is
invalid.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..19 Invalid Aztec Code error-correction-level
value
Action code: X'01' or X'1F'
Explanation: The error-correction-level value specified in
the BSA data structure in a Write Bar Code command is
invalid.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..1A Incompatible combination of Aztec Code
special-function flags
Action code: X'01' or X'1F'
Explanation: An incompatible combination of special-
function flags is specified in the BSA data structure in a
Write Bar Code command. Only one of the FNC1 flags can
be B'1'.
040F ..13 • 040F ..1A [IPDS-16-308]


Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..1B Invalid Aztec Code application-indicator
value
Action code: X'01' or X'1F'
Explanation: The application-indicator value specified in
the BSA data structure in a Write Bar Code command is
invalid.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..1C Invalid Aztec Code structured-append-ID-
length value
Action code: X'01' or X'1F'
Explanation: The structured-append-ID-length value
specified in the BSA data structure in a Write Bar Code
command is invalid. The value must be X'00' when the
symbol is not part of a structured append.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..1D Invalid Aztec Code structure-append-ID
value
Action code: X'01' or X'1F'
Explanation: The structured-append-ID value specified in
the BSA data structure in a Write Bar Code command
contains an invalid character.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..1E Too much data for an Aztec Code reader
initialization symbol
Action code: X'01' or X'1F'
Explanation: The data specified in the BSA data structure
in a Write Bar Code command cannot be fit into an Aztec
Code reader initialization symbol.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Aztec Code bar code is
supported.
040F ..20 Too much data for a Data Matrix bar code
Action code: X'01' or X'1F'
Explanation: Too much data is specified in the BSA data
structure in a Write Bar Code command, and the too-much-
data flag in the BSA forbids making the Data Matrix symbol
bigger to fit the data.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the too-much-data flag in the Data
Matrix Special-Function Parameters is supported.
040F ..30 Invalid QR Code with Image length of
image information
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists for the image information length in the BSA data
structure in a Write Bar Code command:
1. The length is invalid. [IPDS-16-309]
2. The length is too large to fit into the repeating groups [IPDS-16-310]
total length.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..31 QR Code with Image image local ID outside
valid range
Action code: X'01' or X'1F'
Explanation: The image-local-ID parameter value in the
BSA data structure in a Write Bar Code command is
outside the valid range.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..32 Invalid or unsupported QR Code with
Image unit-base value for offset
Action code: X'01' or X'1F'
Explanation: The unit-base parameter value for the
image-object-area offset, specified in the BSA data
structure in a Write Bar Code command, is invalid or
unsupported.
Alternate Exception Action: None
040F ..1B • 040F ..32 [IPDS-16-311]


Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..33 Invalid or unsupported QR Code with
Image units-per-unit-base value for offset
Action code: X'01' or X'1F'
Explanation: The units-per-unit-base parameter value for
the image-object-area offset, specified in the BSA data
structure in a Write Bar Code command, is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..34 Invalid or unsupported QR Code with
Image image-object-area origin
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in the BSA data structure in a Write Bar Code
command:
1. The X offset value for the image-object-area origin is [IPDS-16-312]
invalid or unsupported.
2. The Y offset value for the image-object-area origin is [IPDS-16-313]
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..35 Invalid or unsupported QR Code with
Image image-object-area orientation
Action code: X'01' or X'1F'
Explanation: The image-object-area orientation
parameter value specified in the BSA data structure in a
Write Bar Code command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..36 Invalid or unsupported QR Code with
Image image-object-area reference
coordinate system
Action code: X'01' or X'1F'
Explanation: The image-object-area reference-
coordinate-system parameter value specified in the BSA
data structure in a Write Bar Code command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..37 Invalid or unsupported QR Code with
Image unit-base value for extent
Action code: X'01' or X'1F'
Explanation: The unit-base parameter value for the
image-object-area extent, specified in the BSA data
structure in a Write Bar Code command, is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..38 Invalid or unsupported QR Code with
Image units-per-unit-base value for extent
Action code: X'01' or X'1F'
Explanation: The units-per-unit-base parameter value for
the image-object-area extent, specified in the BSA data
structure in a Write Bar Code command, is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..39 Invalid or unsupported QR Code with
Image image-object-area extent
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in the BSA data structure in a Write Bar Code
command:
1. The X extent value for the image object area is invalid [IPDS-16-314]
or unsupported.
2. The Y extent value for the image object area is invalid [IPDS-16-315]
or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..33 • 040F ..39 [IPDS-16-316]


Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
040F ..3A Invalid or unsupported QR Code with
Image mapping option
Action code: X'01' or X'1F'
Explanation: The mapping-option parameter value
specified in the BSA data structure in a Write Bar Code
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
040F ..3B Invalid QR Code with Image repeating
groups total length
Action code: X'01' or X'1F'
Explanation: The repeating-groups-total-length
parameter value in the BSA data structure in a Write Bar
Code command is invalid.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the QR Code with Image bar code
is supported.
0410..00 Invalid or unsupported human-readable [IPDS-16-317]
interpretation location
Action code: X'01' or X'1F'
Explanation: The human-readable interpretation location
specified in the flags byte of a WBC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Bar Code Object Content Architecture
Reference
0411..00 Attempt to print portion of bar code [IPDS-16-318]
symbol outside object area or VPA
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• A portion of the bar code symbol or HRI extends beyond [IPDS-16-319]
the bar code presentation space.
• A portion of a bar code symbol or HRI extends outside [IPDS-16-320]
the VPA.
• A portion of a bar code symbol or HRI extends beyond [IPDS-16-321]
the intersection of the mapped presentation space and
the bar code object area.
• For printers that cannot detect bar code symbol position [IPDS-16-322]
with respect to bar code object area boundaries, a
portion of the bar code presentation space, as mapped
into the object area, extends outside the bar code object
area boundaries.
Alternate Exception Action: None
Page Continuation Action: Print a partial bar code.
Overprint a pattern to destroy readability of a partial bar
code. Continue with the next command.
Support: Mandatory
Notes:
1. When the data to be printed outside of the VPA is [IPDS-16-323]
blank (no toned pels), some printers suppress this
exception ID, and other printers generate it. The
preferred action is to suppress the exception ID.
2. Some printers report this as exception ID X'020A..05', [IPDS-16-324]
the preferred exception ID is X'0411..00'.
3. Since this exception ID does not have an AEA, [IPDS-16-325]
reporting cannot be blocked with the XOA-EHC
command. Reporting of this exception is not controlled
by the Report Page Position Check bit in the XOA
Exception-Handling Control command.
0411..00 Asynchronous attempt to print portion of [IPDS-16-326]
bar code symbol outside object area or
VPA
Action code: X'19'
Explanation: This exception is discovered after the page
containing the bar code object has passed the Received
Page Counter station. One or more of the following
conditions exists:
• A portion of the bar code symbol or HRI extends beyond [IPDS-16-327]
the bar code presentation space.
• A portion of a bar code symbol or HRI extends outside [IPDS-16-328]
the VPA.
• A portion of a bar code symbol or HRI extends beyond [IPDS-16-329]
the intersection of the mapped presentation space and
the bar code object area.
• For printers that cannot detect bar code symbol position [IPDS-16-330]
with respect to bar code object area boundaries, a
portion of the bar code presentation space, as mapped
into the object area, extends outside the bar code object
area boundaries.
Alternate Exception Action: None
Page Continuation Action: Print a partial bar code.
Overprint a pattern to destroy readability of a partial bar
code. Continue with the next command.
Support: Optional
Notes:
1. When the data to be printed outside of the VPA is [IPDS-16-331]
blank (no toned pels), some printers suppress this
040F ..3A • 0411..00


exception ID, and other printers generate it. The
preferred action is to suppress the exception ID.
2. Some printers report this as exception ID X'020A..05', [IPDS-16-332]
the preferred exception ID is X'0411..00'.
3. Since this exception ID does not have an AEA, [IPDS-16-333]
reporting cannot be blocked with the XOA-EHC
command. Reporting of this exception is not controlled
by the Report Page Position Check bit in the XOA
Exception-Handling Control command.
0412..00 Invalid data in a GS1 DataBar Expanded, [IPDS-16-334]
GS1 DataBar Expanded Stacked, UCC/EAN
128, or GS1-128 bar code
Action code: X'01' or X'1F'
Explanation: Data for a GS1 DataBar Expanded, GS1
DataBar Expanded Stacked, UCC/EAN 128, or GS1-128
bar code in the BSA data structure in a Write Bar Code
command is invalid. One or more of the following
conditions exists:
• FNC1 is not the first data character (for UCC/EAN 128 [IPDS-16-335]
and GS1-128 symbols only)
• Invalid application identifier (ai) value encountered [IPDS-16-336]
• Data for an ai doesn't match the ai definition [IPDS-16-337]
• Insufficient (or no) data following an ai [IPDS-16-338]
• Too much data for an ai [IPDS-16-339]
• Invalid use of FNC1 character [IPDS-16-340]
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Refer to Bar Code Object Content Architecture
Reference
Note: Sense bytes 16–17 contain the application ID (ai)
value for the bad data. The ai value is read as four
decimal digits, with leading zeroes if necessary. For
example, ai = 01 is shown as X'0001' in sense bytes
16–17 and ai = 8005 is shown as X'8005' in sense
bytes 16–17.
0412..01 Invalid data for a Data Matrix encodation [IPDS-16-341]
scheme
Action code: X'01' or X'1F'
Explanation: Within a Data Matrix bar code object, a C40,
Text, X12, or EDIFACT encodation scheme was selected
and a character was encountered within the bar code data
that is not valid for that encodation scheme. These
encodation schemes do not support all 256 possible input
characters.
Alternate Exception Action: Produce the bar code
symbol using the auto-encoding encodation scheme.
Page Continuation Action: Produce the bar code symbol
using the auto-encoding encodation scheme.
Support: Mandatory if Data Matrix encodation schemes
are supported (property pair X'1303' in the Bar Code
command-set vector of an STM reply)
0412..02 Invalid or insufficient data for a RED TAG [IPDS-16-342]
bar code
Action code: X'01' or X'1F'
Explanation: Invalid or insufficient data was encountered
in a Royal Mail Red Tag bar code object. There must be
exactly 21 numeric digits in the input data. The printer
validity checks each field of the input data using the
BCOCA range.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Royal Mail RED TAG bar code
is supported.
0412..03 Invalid or insufficient data for an Intelligent [IPDS-16-343]
Mail Container Barcode
Action code: X'01' or X'1F'
Explanation: Invalid or insufficient data was encountered
in an Intelligent Mail Container Barcode object. One or
more of the following conditions has been encountered:
1. Too many or too few characters were specified; there [IPDS-16-344]
must be exactly 22 characters in the input data.
2. An invalid character was encountered in the data; [IPDS-16-345]
specific ranges are defined for each field defined for
this symbology.
3. Data was encountered that would cause subset A to [IPDS-16-346]
be used; only subsets B and C are allowed for this
symbology.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Intelligent Mail Container
Barcode is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0412..04 Invalid, insufficient, or too much data was [IPDS-16-347]
encountered in a Royal Mail Mailmark bar
code
Action code: X'01' or X'1F'
Explanation: Invalid, insufficient, or too much data was
encountered in a Royal Mail Mailmark bar code. The valid
data for the parameters of each modifier (X'00'–X'01') is
defined within the Royal Mail Mailmark™ Definition
Document.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Royal Mail Mailmark bar code
is supported.
0412..00 • 0412..04 [IPDS-16-348]


0412..05 Invalid or insufficient data for an Intelligent [IPDS-16-349]
Mail Package Barcode
Action code: X'01' or X'1F'
Explanation: Invalid or insufficient data was encountered
in an Intelligent Mail Package Barcode object. One or more
of the following conditions has been encountered:
1. Too many or too few characters were specified; there [IPDS-16-350]
must be exactly 22, 26, 30, or 34 characters in the
input data.
2. An invalid character was encountered in the data. [IPDS-16-351]
3. Data was encountered that would cause subset A or B [IPDS-16-352]
to be used; only subset C is allowed for this
symbology.
Alternate Exception Action: None
Page Continuation Action: Ignore the WBC command
and continue with the next command.
Support: Mandatory if the Intelligent Mail Package
Barcode is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0412..05 • 0412..05 [IPDS-16-353]


Specification Checks—Graphics Data Exceptions
A specification check—graphics exception indicates
the printer has received a graphics command with an
invalid or unsupported data parameter or value.
Each of these exceptions correspond to an exception
code specified by the GOCA architecture.
Format 0 is used for all graphics specification check
exceptions.
0300..01 Unallocated or unsupported graphics [IPDS-16-354]
order or command code
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. An attempt is made to process an unallocated or [IPDS-16-355]
unsupported order code or command code that is
reserved for future use.
2. In a Write Graphics command, a Begin Segment [IPDS-16-356]
Introducer identifier is expected but is not
encountered.
3. A Self-Describing Instruction identifier in a Graphics [IPDS-16-357]
Data Descriptor is not X'21'.
Alternate Exception Action: None
Page Continuation Action: If a set Current Defaults
identifier is not X'21' or a Begin Segment Introducer
identifier is not X'70', skip to END command. If a drawing
order identifier is unallocated or unsupported, the drawing
order is ignored; skip to the next drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0300..02 Reserved field exception or invalid [IPDS-16-358]
attribute set
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. Retired (a reserved bit or byte in a graphics drawing [IPDS-16-359]
order is not set to zero).
2. The Set Current Defaults instruction in the data- [IPDS-16-360]
descriptor self-defining field of a WGC command
attempts to set an invalid or unsupported attribute in
byte 2.
3. The Set Current Defaults instruction has a length of [IPDS-16-361]
X'04' and the flags byte is not X'0F'; the reserved bits
are not correct.
4. The Set Current Defaults instruction has a length [IPDS-16-362]
greater than X'04' and the flags byte is not X'8F'; the
reserved bits are not correct.
5. The Set Current Defaults instruction attempts to set an [IPDS-16-363]
invalid or unsupported mask attribute in bytes 3 and 4.
Alternate Exception Action: None
Page Continuation Action: If the error occurs in the Set
Current Defaults instruction, skip to END command. If the
error occurs in a drawing order, the nonzero reserved bytes
are ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Notes:
1. Sense bytes 16–17 should contain the explanation [IPDS-16-364]
number of a specific cause for the error.
2. Bullet 1 in the explanation corresponds to AFP GOCA [IPDS-16-365]
exception condition EC-0002, which has been retired
in the AFP GOCA architecture.
3. For bullets 3 and 4 in the explanation, if the problem is [IPDS-16-366]
an invalid flags byte (the last 7 bits are not B'0001111'),
this is the correct exception. If instead the problem is
that the length does not match a valid flags byte, the
correct exception is X'0300..03', although some IPDS
receivers incorrectly use this exception instead.
4. Older AFP GOCA implementations might issue this [IPDS-16-367]
exception for previously reserved fields that have since
been used for new function.
0300..03 Incorrect drawing order length [IPDS-16-368]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The flags byte (byte 5) of the Set Current Defaults [IPDS-16-369]
instruction contains X'8F' but the Length field (byte 1)
does not contain the value implied by the Mask field
(bytes 3–4).
2. The segment length (bytes 8, 9) in a Begin Segment [IPDS-16-370]
Introducer is X'0000' for a new segment (segment
flags set to B'00').
3. An invalid length is specified in a drawing order. [IPDS-16-371]
4. The flags byte (byte 5) of the Set Current Defaults [IPDS-16-372]
instruction contains X'0F' but the Length field (byte 1)
is not X'04'.
5. The flags byte (byte 5) of the Set Current Defaults [IPDS-16-373]
instruction contains X'8F' and the Length field (byte 1)
contains the value implied by the Mask field (bytes 3–
0300..01 • 0300..03 [IPDS-16-374]


4) but the amount of immediate data (bytes 6–n) does
not match that specified in the Length field.
Alternate Exception Action: None
Page Continuation Action: If the number of bytes in a
self-defining instruction is not equal to that requested by
the mask byte, skip to the END command; else processing
continues with the next new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0300..04 Invalid attribute value [IPDS-16-375]
Action code: X'01' or X'1F'
Explanation: An attribute value for a graphics drawing
order is invalid.
Alternate Exception Action: Use the standard default
value.
Page Continuation Action: Use the standard default
value.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: For printers that support color fidelity control,
reporting of this exception for invalid color values
can be controlled by the Color Fidelity (X'75') triplet
in the PFC command.
0300..08 Truncated order exception [IPDS-16-376]
Action code: X'01' or X'1F'
Explanation: A drawing order has been requested that is
not complete. This order is either:
1. A fixed two-byte order, and the second byte is not in [IPDS-16-377]
the segment
2. A long format order, and the length byte is not in the [IPDS-16-378]
segment
3. A long or extended format order, and the number of [IPDS-16-379]
bytes following the field containing the length count to
the end of the segment is less than the value of the
length count
4. An extended format order, and the qualifier byte is not [IPDS-16-380]
in the segment
5. An extended format order, and one or both of the [IPDS-16-381]
length bytes are not in the segment
Alternate Exception Action: None
Page Continuation Action: Any remaining data in the
segment is ignored; processing continues with the next
new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0300..0C Segment prolog exception
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A supported order has been encountered that is not [IPDS-16-382]
valid in a prolog.
2. The end of a segment has been reached without an [IPDS-16-383]
End Prolog drawing order.
Alternate Exception Action: None
Page Continuation Action: If an end segment is reached
without an End Prolog, processing continues with the next
new segment. If an order is not valid in a prolog, it is
ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0300..0D Virtual graphics presentation space
overflow
Action code: X'01' or X'1F'
Explanation: A drawing order has been received that
attempts to draw outside the graphics presentation space
(GPS). The Current Position at the start and end of the
graphics primitive are within GPS, but some portion of the
graphics primitive has gone outside of GPS.
Alternate Exception Action: All drawing outside the
graphics presentation space is suppressed. The printer
continues to draw within the graphics presentation space.
Page Continuation Action: All drawing outside the
graphics presentation space is suppressed. The printer
continues to draw within the graphics presentation space.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0300..0E Unsupported attribute value
Action code: X'01' or X'1F'
Explanation: An attribute value for a graphics order is not
supported.
Alternate Exception Action: For an unsupported color
exception, a device-dependent simulated color is used. For
non-color exceptions, the standard default attribute value is
used.
Page Continuation Action: For an unsupported color
exception, a device-dependent simulated color is used. For
non-color exceptions, the standard default attribute value is
used.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0300..04 • 0300..0E [IPDS-16-384]


Notes:
1. For printers that support color fidelity control, reporting [IPDS-16-385]
of this exception for unsupported color values can be
controlled by the Color Fidelity (X'75') triplet in the PFC
command.
2. The printer identifies specific support for OCA color [IPDS-16-386]
values with property pair X'40nn' in the Graphics
command-set vector of an STM reply. The preferred
simulation for white is color of medium, which results in
white when white media is used.
0300..21 Invalid or unsupported default [IPDS-16-387]
Action code: X'01' or X'1F'
Explanation: The Set Current Defaults instruction in the
data-descriptor self-defining field of a WGC command has
set an invalid or unsupported default for an attribute.
Alternate Exception Action: None
Page Continuation Action: If attempting to set the
current default for character angle, the current default is set
to the closest supported angle. In all other cases, the
current default is set to the printer default value.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: For printers that support color fidelity control,
reporting of this exception for unsupported color
values can be controlled by the Color Fidelity (X'75')
triplet in the PFC command.
0304..00 Invalid Segment Characteristics drawing [IPDS-16-388]
order
Action code: X'01' or X'1F'
Explanation: The Segment Characteristics drawing order
is detected outside of a segment prolog.
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0334..00 Character angle value not supported [IPDS-16-389]
Action code: X'01' or X'1F'
Explanation: The character angle specified in the Set
Character Angle drawing order is not supported.
Alternate Exception Action: Use the closest supported
angle.
Page Continuation Action: Use the closest supported
angle.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
033E..00 Invalid End Prolog
Action code: X'01' or X'1F'
Explanation: An End Prolog drawing order occurred
outside the prolog section of a segment.
Alternate Exception Action: None
Page Continuation Action: The END PROLOG is
ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
035E..00 Custom pattern bracket exception
Action code: X'01' or X'1F'
Explanation: An End Custom Pattern drawing order has
been received without a Begin Custom Pattern drawing
order.
Alternate Exception Action: The End Custom Pattern
drawing order is ignored.
Page Continuation Action: The End Custom Pattern
drawing order is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0360..00 Area bracket exception [IPDS-16-390]
Action code: X'01' or X'1F'
Explanation: An End Area drawing order has been
received without a Begin Area drawing order.
Alternate Exception Action: None
Page Continuation Action: The END AREA is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..00 Begin Area received incorrectly [IPDS-16-391]
Action code: X'01' or X'1F'
Explanation: A Begin Area drawing order has been
received while another Begin Area drawing order is already
in progress.
Alternate Exception Action: None
Page Continuation Action: The present area is closed
and filled. Any following drawing orders until END AREA
are used to define a new area.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..01 Area truncated exception [IPDS-16-392]
Action code: X'01' or X'1F'
Explanation: A Begin Area drawing order has been
processed in a segment and the end of the segment has
been reached without an End Area drawing order having
been received. The results of area-fill implementation are
printer dependent.
Alternate Exception Action: None
Page Continuation Action: The area is closed and filled.
Processing continues with the next segment.
0300..21 • 0368..01 [IPDS-16-393]


Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..02 Supported order invalid in area [IPDS-16-394]
Action code: X'01' or X'1F'
Explanation: A supported drawing order that is not valid
within an area has been detected in an area.
Alternate Exception Action: None
Page Continuation Action: The drawing order is ignored.
Processing continues with the next drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..03 Pattern Set not supported [IPDS-16-395]
Action code: X'01' or X'1F'
Explanation: A Begin Area drawing order is encountered
but the pattern set requested by the Set Pattern Set
drawing order is not supported.
Alternate Exception Action: Use the standard default
pattern symbol.
Page Continuation Action: Use the standard default
pattern symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..04 Undefined pattern symbol [IPDS-16-396]
Action code: X'01' or X'1F'
Explanation: A Begin Area drawing order is encountered
but the current pattern symbol is undefined in the current
pattern set.
Alternate Exception Action: Use the standard default
pattern symbol.
Page Continuation Action: Use the standard default
pattern symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..05 Temporary-storage overflow while drawing [IPDS-16-397]
an area
Action code: X'01' or X'1F'
Explanation: For an area within a graphics segment,
temporary storage is sometimes required. The drawing
orders within the area require more temporary storage than
is available.
Alternate Exception Action: Draw and fill as much of the
area as possible.
Page Continuation Action: Draw and fill as much of the
area as possible.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0368..06 Invalid pattern use inside custom pattern [IPDS-16-398]
Action code: X'01' or X'1F'
Explanation: While inside the definition of a custom
pattern, a Begin Area order has been executed but the
current pattern symbol identifies a custom pattern.
Alternate Exception Action: Use the standard default
pattern symbol.
Page Continuation Action: Use the standard default
pattern symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0370..01 Unsupported Begin Segment Introducer [IPDS-16-399]
segment flag
Action code: X'01' or X'1F'
Explanation: The segment flag (byte 7, bits 5, 6) in the
Begin Segment Introducer has a value of B'10'.
Alternate Exception Action: None
Page Continuation Action: Any data in the segment is
ignored. Processing continues with the next new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0370..82 Invalid Begin Segment Introducer segment [IPDS-16-400]
flag
Action code: X'01' or X'1F'
Explanation: The segment flag (byte 7, bits 5, 6) in the
Begin Segment Introducer has a value of B'01'.
Alternate Exception Action: None
Page Continuation Action: Any data in the segment is
ignored. Processing continues with the next new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0370..C1 Invalid Begin Segment Introducer length
Action code: X'01' or X'1F'
Explanation: The Begin Segment Introducer length
parameter (byte 1) is invalid.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0370..C5 Insufficient segment data
Action code: X'01' or X'1F'
Explanation: The amount of data received with a
segment is less than that specified in the begin segment
SEGL field.
Alternate Exception Action: None
Page Continuation Action: Processing continues with
the next segment, or IPDS command if the END command
is received.
0368..02 • 0370..C5 [IPDS-16-401]


Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0392..00 Graphics image order sequence exception [IPDS-16-402]
Action code: X'01' or X'1F'
Explanation: A Begin Image drawing order has not been
processed before the Image Data drawing order in the
segment.
Alternate Exception Action: None
Page Continuation Action: The drawing order is ignored.
The image data is discarded.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0392..01 Image data discrepancy [IPDS-16-403]
Action code: X'01' or X'1F'
Explanation: The Image Data drawing order contains
either not enough or too many bytes of data.
Alternate Exception Action: None
Page Continuation Action: The Image Data order is
ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0393..00 Graphics image bracket exception [IPDS-16-404]
Action code: X'01' or X'1F'
Explanation: An End Image drawing order has been
received without a Begin Image drawing order having been
received.
Alternate Exception Action: None
Page Continuation Action: The END Image order is
ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
0393..01 Incorrect number of Image Data drawing [IPDS-16-405]
orders
Action code: X'01' or X'1F'
Explanation: The number of Image Data drawing orders
between the Begin Image and End Image drawing orders
is not equal to the number of rows in the image (as given
by the height value in the Begin Image drawing order).
Alternate Exception Action: None
Page Continuation Action: If there is not enough data,
pad remaining bytes with X'00'; else ignore the extra
orders.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C0..00 Box corner too large
Action code: X'01' or X'1F'
Explanation: In a Box drawing order, the HAXIS or VAXIS
parameter is too large to fit the indicated corner into the
size of the box.
Alternate Exception Action: Draw corners with the
largest axis that fit the box.
Page Continuation Action: Draw corners with the largest
axis that fit the box.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C0..01 Box corner parameter outside range
Action code: X'01' or X'1F'
Explanation: In a Box drawing order, either the HAXIS or
VAXIS parameter is outside the valid range.
Alternate Exception Action: Draw square corners.
Page Continuation Action: Draw square corners.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C2..00 Marker Set not supported
Action code: X'01' or X'1F'
Explanation: A Marker drawing order is encountered but
the marker set requested by the Set Marker Set drawing
order is not supported.
Alternate Exception Action: Use the standard default
marker symbol.
Page Continuation Action: Use the standard default
marker symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C2..01 Undefined marker code
Action code: X'01' or X'1F'
Explanation: A Marker drawing order is encountered, but
the current marker symbol is undefined in the current
marker set.
Alternate Exception Action: Use the standard default
marker symbol.
Page Continuation Action: Use the standard default
marker symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C2..02 Mismatched marker set exception
Action code: X'01' or X'1F'
Explanation: The current marker-set-attribute value
identifies a marker set that cannot support the functions
implied by the current marker-precision attribute.
Alternate Exception Action: Use the marker set
identified by the current marker set attribute, with the
lowest value of precision the marker set can support.
0392..00 • 03C2..02 [IPDS-16-406]


Page Continuation Action: Use the marker set identified
by the current marker set attribute, with the lowest value of
precision the marker set can support.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C3..00 Font not available
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A Character String drawing order is encountered, but [IPDS-16-407]
the specified font local ID is not mapped to a font using
the LFE command.
2. A character set specified either in a Set Character Set [IPDS-16-408]
drawing order or as a current default, and then later
used by a Character String drawing order, is mapped
in the current LFE, but the font is not loaded in the
printer.
3. A character set specified either in a Set Character Set [IPDS-16-409]
drawing order or as a current default, and then later
used by a Character String drawing order, is mapped
in the current LFE and is loaded in the printer, but the
quality level of the font (established by an XOA-PQC
command) is not supported.
Alternate Exception Action: Use the standard default
character set.
Page Continuation Action: If the exception occurs
because a coded font (defined within the current LFE
command) is not present in the printer when needed, the
printer tries to make an appropriate font substitution that
preserves as many characteristics as possible of the
originally requested font while still preserving the original
code page. If the exception occurs for any other reason,
there is no PCA.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
03C3..01 Undefined graphics character
Action code: X'01' or X'1F'
Explanation: A code in a character string identified in a
Character String drawing order is undefined or points to an
unavailable character pattern.
Alternate Exception Action: Use the standard default
character symbol.
Page Continuation Action: Use the standard default
character symbol.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Reporting of this exception is controlled by the
Report Undefined Character Check bit in the XOA
Exception-Handling Control command.
03C3..01 Asynchronously detected undefined
graphics character
Action code: X'19'
Explanation: This exception is detected after the page
has passed the Received Page Counter station.
A code in a character string identified in a Character String
drawing order is undefined or points to an unavailable
character pattern.
Alternate Exception Action: Use the standard default
character symbol.
Page Continuation Action: Use the standard default
character symbol.
Support: Optional
Note: Reporting of this exception is controlled by the
Report Undefined Character Check bit in the XOA
Exception-Handling Control command.
03C3..02 Mismatched character set exception
Action code: X'01' or X'1F'
Explanation: The current character-set-attribute value
identifies a character set that cannot support the functions
implied by the current character-precision attribute.
Alternate Exception Action: Use the character set
identified by the current character set attribute, with the
lowest value of precision the character set can support.
Page Continuation Action: Use the character set
identified by the current character set attribute, with the
lowest value of precision the character set can support.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03C3..03 Invalid Unicode data
Action code: X'01' or X'1F'
Explanation: One of the following errors exists in GOCA
character string data within a Write Graphics command:
• A high-order surrogate code value is not immediately [IPDS-16-410]
followed by a low-order surrogate code value. The high-
order surrogate range is U+D800 through U+DBFF .
• A low-order surrogate code value is not immediately [IPDS-16-411]
preceded by a high-order surrogate code value. The low-
order surrogate range is U+DC00 through U+DFFF .
• An illegal UTF-8 byte sequence, as defined in the [IPDS-16-412]
Unicode 3.2 Specification, is specified.
Note: For a formal definition of UTF-8, consult the
Unicode 3.2 Specification. The error cases can be
summarized as follows:
– The value in the first byte of the UTF-8
sequence is not in the legal UTF-8 range (X'00'–
X'7F' and X'C2'–X'F4').
– The value in the second byte of the UTF-8
sequence is not in the legal UTF-8 range
03C3..00 • 03C3..03


allowed by the value in the first byte. The valid
ranges for the second byte are as follows:
1st byte 2nd byte
X'C2'–X'DF' X'80'–X'BF'
X'E0' X'A0'–X'BF'
X'E1'–X'EC' X'80'–X'BF'
X'ED' X'80'–X'9F'
X'EE'–X'EF' X'80'–X'BF'
X'F0' X'90'–X'BF'
X'F1'–X'F3' X'80'–X'BF'
X'F4' X'80'–X'8F'
– The value in the third or fourth byte of the UTF-8
sequence is not in the legal UTF-8 range for that
byte (X'80'–X'BF').
Alternate Exception Action: Skip to the end of the
Character String drawing order and continue processing.
Page Continuation Action: Skip to the end of the
Character String drawing order and continue processing.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 contain the Unicode code value
in error; see note 3  for more information.
03C6..01 Arc drawing check
Action code: X'01' or X'1F'
Explanation: The drawing processor has detected an
exception that might prevent the drawing of the arc within
the normal limits of pel accuracy.
Alternate Exception Action: Draw the arc with possible
reduced accuracy, which might result in straight lines.
Page Continuation Action: Draw the arc with possible
reduced accuracy, which might result in straight lines.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03D1..00 Truncated graphics image exception
Action code: X'01' or X'1F'
Explanation: A Begin Image drawing order has been
received in a segment and the end of the segment has
been reached without an End Image drawing order having
been processed.
Alternate Exception Action: None
Page Continuation Action: The received image data is
printed. Processing continues with the next segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03D1..01 Invalid order in graphics image
Action code: X'01' or X'1F'
Explanation: A Begin Image drawing order has been
processed in a segment, and an order other than
Comment, Image Data, No Operation, or End Image has
been encountered.
Alternate Exception Action: None
Page Continuation Action: Any remaining data in the
segment is ignored; processing continues with the next
new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03D1..02 Graphics image format not supported
Action code: X'01' or X'1F'
Explanation: The value specified for the graphics image
FORMAT parameter is not supported.
Alternate Exception Action: None
Page Continuation Action: Any remaining data in the
segment is ignored; processing continues with the next
new segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03D1..03 Image width greater than maximum
supported
Action code: X'01' or X'1F'
Explanation: The width value specified in the Begin
Image drawing order exceeds the maximum supported
image width.
Alternate Exception Action: Truncate the image width at
the maximum width supported.
Page Continuation Action: Truncate the image width at
the maximum width supported.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03D1..04 Image height greater than maximum
supported
Action code: X'01' or X'1F'
Explanation: The height value specified in a Begin Image
drawing order exceeds the maximum supported image
height.
Alternate Exception Action: Truncate the image height
at the maximum height supported.
Page Continuation Action: Truncate the image height at
the maximum height supported.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..00 Invalid pattern set in a Linear Gradient
Action code: X'01' or X'1F'
Explanation: A Linear Gradient drawing order has been
received that specifies a pattern set that is invalid as a
gradient pattern set.
03C6..01 • 03DC..00


Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..01 Invalid pattern symbol in a Linear Gradient
Action code: X'01' or X'1F'
Explanation: A Linear Gradient drawing order has been
received that specifies a pattern symbol that is invalid for
gradients.
Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..02 Pattern already exists
Action code: X'01' or X'1F'
Explanation: A Linear Gradient drawing order has been
received that specifies pattern set and pattern symbol
values that correspond to an already-existing pattern.
Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..03 Temporary-storage overflow while defining
a gradient
Action code: X'01' or X'1F'
Explanation: Processing and storage of a linear gradient
can require temporary storage. A Linear Gradient order
has been received that requires more temporary storage
than is available. Note that storage can be freed up by
deleting unneeded patterns with the Delete Pattern
drawing order.
Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..04 Invalid value for an outside parameter
Action code: X'01' or X'1F'
Explanation: An outside parameter value in a Linear
Gradient drawing order is invalid.
Alternate Exception Action: Use the value X'00' – None.
Page Continuation Action: Use the value X'00' – None.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..05 Invalid color stop offset
Action code: X'01' or X'1F'
Explanation: An offset value in a Linear Gradient color
stop is invalid or is less than a previous color stop offset.
Alternate Exception Action: The color stop is ignored.
Page Continuation Action: The color stop is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..06 Invalid color specification length
Action code: X'01' or X'1F'
Explanation: The color specification in a Linear Gradient
order contains an invalid length field.
Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DC..07 Color settings not allowed for a gradient
Action code: X'01' or X'1F'
Explanation: A Linear Gradient drawing order has been
received and one or more of the following conditions exists:
1. The color space specified is the Standard OCA color [IPDS-16-413]
space.
2. The color space specified is the Highlight color space, [IPDS-16-414]
but the color values specified in the order do not all
resolve to Indexed CMR Color Palette tags.
Alternate Exception Action: Ignore the Linear Gradient
order.
Page Continuation Action: Ignore the Linear Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
03DD..00 Invalid pattern set in a Radial Gradient
Action code: X'01' or X'1F'
Explanation: A Radial Gradient drawing order has been
received that specifies a pattern set that is invalid as a
gradient pattern set.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
03DC..01 • 03DD..00


Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..01 Invalid pattern symbol in a Radial Gradient
Action code: X'01' or X'1F'
Explanation: A Radial Gradient drawing order has been
received that specifies a pattern symbol that is invalid for
gradients.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..02 Pattern already exists
Action code: X'01' or X'1F'
Explanation: A Radial Gradient drawing order has been
received that specifies pattern set and pattern symbol
values that correspond to an already-existing pattern.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..03 Temporary-storage overflow while defining
a gradient
Action code: X'01' or X'1F'
Explanation: Processing and storage of a radial gradient
can require temporary storage. A Radial Gradient order
has been received that requires more temporary storage
than is available. Note that storage can be freed up by
deleting unneeded patterns with the Delete Pattern
drawing order.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..04 Invalid value for an outside parameter
Action code: X'01' or X'1F'
Explanation: An outside parameter value in a Radial
Gradient drawing order is invalid.
Alternate Exception Action: Use the value X'00' – None.
Page Continuation Action: Use the value X'00' – None.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..05 Invalid color stop offset
Action code: X'01' or X'1F'
Explanation: An offset value in a Radial Gradient color
stop is invalid or is less than a previous color stop offset.
Alternate Exception Action: The color stop is ignored.
Page Continuation Action: The color stop is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..06 Invalid color specification length
Action code: X'01' or X'1F'
Explanation: The color specification in a Radial Gradient
order contains an invalid length field.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DD..07 Color settings not allowed for a gradient
Action code: X'01' or X'1F'
Explanation: A Radial Gradient drawing order has been
received and one or more of the following conditions exists:
1. The color space specified is the Standard OCA color [IPDS-16-415]
space.
2. The color space specified is the Highlight color space, [IPDS-16-416]
but the color values specified in the order do not all
resolve to Indexed CMR Color Palette tags.
Alternate Exception Action: Ignore the Radial Gradient
order.
Page Continuation Action: Ignore the Radial Gradient
order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
03DE..00 Begin Custom Pattern received incorrectly
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been received while another Begin Custom Pattern
drawing order is already in progress.
Alternate Exception Action: The in-progress custom
pattern is closed and saved, as if an End Custom Pattern
drawing order had been received. Processing continues
with the new Begin Custom Pattern drawing order.
Page Continuation Action: The in-progress custom
pattern is closed and saved, as if an End Custom Pattern
03DD..01 • 03DE..00


drawing order had been received. Processing continues
with the new Begin Custom Pattern drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..01 Custom pattern truncated exception
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been processed in a segment and the end of the segment
has been reached without an End Custom Pattern drawing
order having been received.
Alternate Exception Action: The custom pattern is
closed and saved, as if an End Custom Pattern drawing
order had been received. Processing continues with the
next segment.
Page Continuation Action: The custom pattern is closed
and saved, as if an End Custom Pattern drawing order had
been received. Processing continues with the next
segment.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..02 Invalid pattern set in a Begin Custom
Pattern
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been received that specifies a pattern set that is invalid for
custom pattern sets.
Alternate Exception Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Page Continuation Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..03 Invalid pattern symbol in a Begin Custom
Pattern
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been received that specifies a pattern symbol that is invalid
for custom pattern sets.
Alternate Exception Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Page Continuation Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..04 Pattern window ill-defined
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been received that specifies an ill-defined pattern window;
one or more of the following conditions exists:
1. The X position of the left side of the pattern window is [IPDS-16-417]
equal to or greater than the X position of the right side.
2. The Y position of the bottom side of the pattern window [IPDS-16-418]
is equal to or greater than the Y position of the top
side.
Alternate Exception Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Page Continuation Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
03DE..05 Pattern already exists
Action code: X'01' or X'1F'
Explanation: A Begin Custom Pattern drawing order has
been received that specifies pattern set and pattern symbol
values that correspond to an already-existing pattern.
Alternate Exception Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Page Continuation Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..06 Temporary-storage overflow while defining
a custom pattern
Action code: X'01' or X'1F'
Explanation: Custom patterns within graphics segments
require temporary storage to save the pattern until it is later
used. The drawing orders within the custom patterns
require more temporary storage than is available. Note that
storage can be freed up by deleting unneeded patterns
with the Delete Pattern drawing order.
Alternate Exception Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Page Continuation Action: Skip to the End Custom
Pattern drawing order; the custom pattern is not saved.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DE..07 Supported order invalid in custom pattern
Action code: X'01' or X'1F'
Explanation: A supported drawing order that is not valid
within a custom pattern definition has been detected in a
custom pattern definition.
Alternate Exception Action: Ignore the invalid order.
Page Continuation Action: Ignore the invalid order.
03DE..01 • 03DE..07


Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DF ..00 Pattern does not exist
Action code: X'01' or X'1F'
Explanation: A Delete Pattern drawing order has been
received that specifies to delete a pattern with a given
pattern set and pattern symbol, but no such pattern exists.
Alternate Exception Action: Ignore the Delete Pattern
drawing order.
Page Continuation Action: Ignore the Delete Pattern
drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DF ..01 Invalid pattern set in a Delete Pattern
Action code: X'01' or X'1F'
Explanation: A Delete Pattern drawing order has been
received that specifies a pattern set that is invalid.
Alternate Exception Action: Ignore the Delete Pattern
drawing order.
Page Continuation Action: Ignore the Delete Pattern
drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DF ..02 Invalid pattern symbol in a Delete Pattern
Action code: X'01' or X'1F'
Explanation: A Delete Pattern drawing order has been
received that specifies a pattern symbol that is invalid.
Alternate Exception Action: Ignore the Delete Pattern
drawing order.
Page Continuation Action: Ignore the Delete Pattern
drawing order.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03E1..00 Relative line outside coordinate space
Action code: X'01' or X'1F'
Explanation: A relative line starts inside the graphics
presentation space, but then goes outside the space.
Alternate Exception Action: None
Page Continuation Action: The movement that would
cause the error is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03E3..00 Partial Arc ends outside graphics
presentation space
Action code: X'01' or X'1F'
Explanation: In a Partial Arc drawing order, the calculated
new current position for the endpoint of the arc is outside
the limits of the graphics presentation space.
Alternate Exception Action: None
Page Continuation Action: The movement that would
cause the error is ignored.
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03E3..02 Negative sweep angle
Action code: X'01' or X'1F'
Explanation: In a Partial Arc drawing order, the SWEEP
angle is invalid (negative).
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03E3..03 Negative start angle
Action code: X'01' or X'1F'
Explanation: In a Partial Arc drawing order, the START
angle is invalid (negative).
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Graphics Object Content Architecture
for Advanced Function Presentation Reference.
03DF ..00 • 03E3..03


Specification Checks—General Exceptions
A specification check—general exception indicates
that the printer has received a command with an
invalid or unsupported parameter or value. This
exception class contains specification checks that
are common to all IPDS data types.
Some general specification checks correspond to an
exception code specified by the BCOCA, CMOCA,
GOCA, and PTOCA architectures, and are identified
accordingly. Refer to the architecture reference
manual for these object-content architectures for an
object-specific description of the exception.
Format 0 is used for all general specification check
exceptions, except for the following:
• X'020A..05' uses either format 1 or format 7 [IPDS-16-419]
• X'027E..00' uses format 8 [IPDS-16-420]
0200..01 Text control-sequence code exception [IPDS-16-421]
Action code: X'01' or X'1F'
Explanation: An undefined or unsupported control-
sequence code is found in the data of a Write Text
command or in the initial text conditions of a Write Text
Control command.
Alternate Exception Action: None
Page Continuation Action: Skip to the next DORE,
DORE2, END, EP , IDO, IO, IPS, ISP , LFE, WBCC, WGC,
WIC, WIC2, WOCC, or WTC command.
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-422]
PTOCA.
2. For printers that support text fidelity control, reporting [IPDS-16-423]
of this exception can be controlled by the Text Fidelity
(X'86') triplet in the PFC command.
3. Sense bytes 16–17 contain the unsupported or [IPDS-16-424]
unrecognized PTOCA control sequence function type
that causes this error. Sense byte 16 contains X'00'
and sense byte 17 contains the function-type value.
Some IPDS printers do not identify the function-type
value and, in this case, sense bytes 16–17 contain
X'0000'.
0201..03 Text data extends outside of the text [IPDS-16-425]
presentation space
Action code: X'01' or X'1F'
Explanation: A portion of the text presentation data (a
character box, rule, or underscore) extends outside of the
text presentation space. Note that this exception ID is not
used for text-major text (where X'08C1..00' is issued when
text extends outside of the logical page).
Alternate Exception Action: All text outside of the text
presentation space is suppressed. This may mean
truncating text or rules at the character boundary closest to
the edge of the text presentation space or, alternatively,
printing part of a graphic character.
Page Continuation Action: All text outside of the text
presentation space is suppressed. This may mean
truncating text or rules at the character boundary closest to
the edge of the text presentation space or, alternatively,
printing part of a graphic character.
Support: Mandatory if WTC is supported
Note: When the data to be printed outside of the text
presentation space is blank (no toned pels), this
exception ID is suppressed.
0202..01 End Suppression (ESU) control-sequence [IPDS-16-426]
exception
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The active Begin Suppression (BSU) ID within the [IPDS-16-427]
current page, page segment, or overlay is not the
same as that specified in the ESU control sequence.
2. There is no active suppression ID when an ESU [IPDS-16-428]
control sequence is received.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-429]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-430]
number of a specific cause for the error.
0202..02 Invalid or unsupported IPDS command [IPDS-16-431]
length
Action code: X'01'
0200..01 • 0202..02 [IPDS-16-432]


Explanation: One or more of the following conditions
exists:
1. The length value of a command is less than X'05' (or [IPDS-16-433]
less than X'07' if a correlation ID is included).
2. The length of a command is greater than X'7FFF'. [IPDS-16-434]
3. The command length is not valid or is unsupported for [IPDS-16-435]
the particular command.
4. The length of the data within an IDO, WBCC, WGC, [IPDS-16-436]
WIC2, WMC, WOCC, or WTC command is not equal
to the sum of the lengths of the self-defining fields
within it.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. Some printers report this exception as X'0203..02' or [IPDS-16-437]
X'80E0..00'. The preferred Exception ID is X'0203..02'
when the IPDS command header length is too small
and X'0202..02' for other IPDS command length
exceptions.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-438]
number of a specific cause for the error.
0202..05 Invalid self-defining-field length [IPDS-16-439]
Action code: X'01' or X'1F'
Explanation: A self-defining field that is less than the
minimum allowable length has been received in an IDO,
WBCC, WGC, WIC2, WMC, WOCC, or WTC command.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
0203..02 IPDS command header length too small [IPDS-16-440]
Action code: X'01'
Explanation: The length value of a command is less than
X'05' (or less than X'07' if a correlation ID is included).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'0202..02' or
X'80E0..00'. The preferred Exception ID is
X'0203..02' when the IPDS command header length
is too small and X'0202..02' for other IPDS
command length exceptions.
0203..05 Invalid or unsupported object area [IPDS-16-441]
orientation
Action code: X'01' or X'1F'
Explanation: An object area orientation value specified in
a self-defining field of an IDO, WBCC, WGC, WIC2,
WOCC, or WTC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
0204..01 EP or END command encountered before [IPDS-16-442]
suppression ended
Action code: X'01' or X'1F'
Explanation: An EP or END command is encountered
before a text suppression ends.
Alternate Exception Action: Terminate suppression as if
an End Suppression had been received.
Page Continuation Action: Terminate suppression as if
an End Suppression had been received.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0204..02 Invalid use of Acknowledgment- [IPDS-16-443]
Continuation Bit
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The Acknowledgment-Continuation bit in the flag byte [IPDS-16-444]
of a command is on when the printer has no
continuation data available.
2. The Acknowledgment-Continuation bit in the flag byte [IPDS-16-445]
of a command is B'1', but the ARQ bit is B'0'.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0204..05 Invalid or unsupported value for area- [IPDS-16-446]
position reference system
Action code: X'01' or X'1F'
Explanation: The reference system specified in the area-
position self-defining field for an IDO, WBCC, WGC, WIC2,
WOCC, or WTC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
0205..01 Invalid spanning sequence [IPDS-16-447]
0202..05 • 0205..01 [IPDS-16-448]


Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A WT command is required to complete a partial [IPDS-16-449]
control sequence, or two-byte code point. A command
other than a WT or an Anystate command is received.
2. A WI2 command is required to complete a partial IO- [IPDS-16-450]
Image self-defining field. A command other than WI2
or an Anystate command is received.
3. A WG command is required to complete a partial [IPDS-16-451]
graphics Begin Segment Introducer or drawing order.
A command other than a WG or an Anystate command
is received.
4. A WOC command is required to complete the data for [IPDS-16-452]
a partial object container. A command other than a
WOC or Anystate command is received.
5. A WM command is required to complete the data for a [IPDS-16-453]
partial metadata object. A command other than a WM
or Anystate command is received.
Alternate Exception Action: None
Page Continuation Action: Skip to the next END or END
PAGE command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0205..02 Invalid setting of the LPD ordered page [IPDS-16-454]
flag
Action code: X'01' or X'1F'
Explanation: The LPD ordered page flag indicates an
ordered page, but the page data requires a print
mechanism movement not in accordance with the ordered
page definition.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Some printers report this exception as X'0214..03'.
The preferred exception is X'0205..02'.
0205..05 Invalid or unsupported self-defining-field [IPDS-16-455]
unit base
Action code: X'01' or X'1F'
Explanation: The measurement unit specified in the
output-control or the data-descriptor self-defining field of an
IDO, WBCC, WGC, WIC2, WOCC, or WTC command is an
invalid or unsupported value.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
Note: For the data-descriptor self-defining field, this
corresponds to an exception code defined by
BCOCA and PTOCA. This IPDS exception ID is also
used for GOCA and IOCA objects, but these
architectures have not identified an object-specific
exception code.
0206..01 Invalid Begin Suppression (BSU) [IPDS-16-456]
Action code: X'01' or X'1F'
Explanation: A text BSU control sequence is encountered
in a page, page segment, overlay, or text object before a
previous suppression ends.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0206..05 Invalid or unsupported units per unit base [IPDS-16-457]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The units per unit base value specified in either the [IPDS-16-458]
output-control or the data-descriptor self-defining field
of an IDO, WBCC, WGC, WIC2, WOCC, or WTC
command is invalid or unsupported.
2. The units per unit base specified for the Y coordinate [IPDS-16-459]
in the data-descriptor self-defining field of a WBCC,
WGC, or WTC command is not equal to the units per
unit base specified for the X coordinate.
3. In a WGC-GDD self-defining field, an invalid value is [IPDS-16-460]
specified in either the XIRES field, the YIRES field, or
both.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory for the first two bullets, optional for
the third bullet
Notes:
1. Some printers report this exception as X'0860..00'. [IPDS-16-461]
The preferred exception ID is X'0206..05'.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-462]
number of a specific cause for the error.
3. For the data-descriptor self-defining field, this [IPDS-16-463]
corresponds to an exception code defined by BCOCA
and PTOCA. This IPDS exception ID is also used for
GOCA and IOCA objects, but these architectures have
not identified an object-specific exception code.
0207..05 Invalid or unsupported self-defining-field [IPDS-16-464]
extents
0205..02 • 0207..05 [IPDS-16-465]


Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The extents (Xg and Yg limits for WGC-GDD) specified [IPDS-16-466]
in either the output-control or the data-descriptor self-
defining field of an IDO, WBCC, WGC, WIC2, WOCC,
or WTC command are invalid or unsupported.
2. The graphics presentation space window specified in [IPDS-16-467]
the WGC-GDD is ill defined (Xg left limit is equal to or
to the right of Xg right limit, or Yg bottom limit is equal to
or above Yg top limit).
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
Notes:
1. Sense bytes 16–17 should contain the explanation [IPDS-16-468]
number of a specific cause for the error.
2. For the data-descriptor self-defining field, this [IPDS-16-469]
corresponds to an exception code defined by BCOCA
and PTOCA. This IPDS exception ID is also used for
GOCA and IOCA objects, but these architectures have
not identified an object-specific exception code.
0208..05 Invalid or unsupported mapping option [IPDS-16-470]
Action code: X'01' or X'1F'
Explanation: A mapping option value specified in the
output-control self-defining field of an IDO, WBCC, WGC,
WIC2, WOCC, or WTC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
0209..05 Unsupported axis offsets [IPDS-16-471]
Action code: X'01' or X'1F'
Explanation: The axis offset values specified in the
output-control self-defining field of an IDO, WBCC, WGC,
WIC2, WOCC, or WTC command are unsupported.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
020A..05 Data within an object area might be
outside the VPA
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• A portion of the graphics presentation space window, the [IPDS-16-472]
image presentation space, or the bar code presentation
space, as mapped to the respective object area, falls
outside the VPA.
• A portion of the graphics, IO-Image, or bar code object [IPDS-16-473]
area falls outside the VPA.
Alternate Exception Action: Continue processing, but
suppress printing outside of the valid printable area. Note
that only complete bar code symbols can be printed; a
partial bar code symbol is not printed.
Page Continuation Action: Continue processing, but
suppress printing outside of the valid printable area. If a
partial bar code symbol is printed, a pattern is printed over
the symbol to destroy readability.
Support: Optional
Notes:
1. This exception is used by printers that cannot detect [IPDS-16-474]
an attempt to print outside the VPA, but can detect that
one of the conditions above apply. Printers that can
detect an attempt to print outside the VPA report this
exception as X'08C1..00' or X'0411..00'. The preferred
Exception ID is X'0411..00' for bar code VPA errors
and X'08C1..00' for VPA errors in all other data types.
2. This exception ID has been deprecated and should not [IPDS-16-475]
be used by new implementations.
3. Reporting of this exception is controlled by the Report [IPDS-16-476]
Page Position Check bit in the XOA Exception-
Handling Control command.
020A..05 Data within an object area might be
outside the VPA (asynchronously
detected)
Action code: X'19'
Explanation: This exception is detected after the page
passes the Received Page Counter station. One or more of
the following conditions exists:
• A portion of the graphics presentation space window, the [IPDS-16-477]
image presentation space, or the bar code presentation
space, as mapped to the respective object area, falls
outside the VPA.
• A portion of the graphics, IO-Image, or bar code object [IPDS-16-478]
area falls outside the VPA.
Alternate Exception Action: Continue processing, but
suppress printing outside of the valid printable area. Note
that only complete bar code symbols can be printed; a
partial bar code symbol is not printed.
Page Continuation Action: Continue processing, but
suppress printing outside of the valid printable area. If a
partial bar code symbol is printed, a pattern is printed over
the symbol to destroy readability.
Support: Optional
0208..05 • 020A..05 [IPDS-16-479]


Notes:
1. This exception is used by printers that cannot detect [IPDS-16-480]
an attempt to print outside the VPA, but can detect that
one of the conditions above apply. Printers that can
detect an attempt to print outside the VPA report this
exception as X'08C1..00' or X'0411..00'. The preferred
Exception ID is X'0411..00' for bar code VPA errors
and X'08C1..00' for VPA errors in all other data types.
2. This exception ID has been deprecated and should not [IPDS-16-481]
be used by new implementations.
3. Reporting of this exception is controlled by the Report [IPDS-16-482]
Page Position Check bit in the XOA Exception-
Handling Control command.
020A..06 Text data extends outside of object area
Action code: X'01' or X'1F'
Explanation: A portion of the text presentation data
extends beyond the intersection of the mapped text
presentation space and text object area.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if WTC is supported
020B..05 Invalid or missing self-defining-field
identifier
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A two-byte self-defining-field identifier in an IDO, [IPDS-16-483]
WBCC, WGC, WIC2, WMC,
WOCC, or WTC
command is invalid or out of sequence.
2. A required self-defining-field identifier in an IDO, [IPDS-16-484]
WBCC, WGC, WIC2, WMC, WOCC, or WTC
command is missing.
3. One of the self-defining fields appears more than once [IPDS-16-485]
in an IDO, WBCC, WGC, WIC2, WMC, WOCC, or
WTC command.
Alternate Exception Action: None
Page Continuation Action: For errors in an IDO
command, skip the IDO command. For errors in other
commands, skip to the next END command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
020C..01 Invalid or unsupported font local ID
Action code: X'01' or X'1F'
Explanation: A font local ID in a text Set Coded-Font
Local (SCFL) control sequence or in an LPD or WTC
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Presentation Text Object Content
Architecture Reference.
Note: This corresponds to an exception code defined by
PTOCA.
020C..05 Unsupported object-area-origin-offset
value specified in an IDO command
Action code: X'01' or X'1F'
Explanation: An invalid object-area-origin-offset value is
specified in an Include Data Object (IDO) command.
Alternate Exception Action: None
Page Continuation Action: Skip the IDO command
Support: Mandatory, if the printer does not support the full
range of values for each supported unit of measure.
020D..01 Invalid or unsupported data within a non-
presentation object container
Action code: X'01' or X'09' or X'1F'
Explanation: The data within a WOC command for a non-
presentation object container is invalid, unsupported, or
missing. The registered object-type ID in the WOCC
command indicates whether the object is a presentation or
a non-presentation object container.
Alternate Exception Action: None
Page Continuation Action: If in home state, none. If in a
page or overlay, skip to the End command of the non-
presentation object and if the printer cannot continue with
the presentation object that invoked the non-presentation
object, the printer also skips to the End of the presentation
object.
Support: Mandatory
Notes:
1. Sense bytes 16–17 can contain an object-specific [IPDS-16-486]
error code; refer to “Error Codes for Other Data
Objects”  for a list of object-specific error
codes. X'0000' in sense bytes 16–17 indicates that no
object-specific error code has been provided.
2. This exception ID is used when a TrueType/OpenType [IPDS-16-487]
font or a font within a collection does not contain a
required cmap subtable.
3. This exception ID is used when a non-presentation [IPDS-16-488]
object container requires some data, but no WOC data
was provided.
4. This exception ID might indicate that the version of the [IPDS-16-489]
object container is not supported. Some printers return
information about version support in the XOH-OPC
Object-Container-Version-Support self-defining field.
020D..02 Unsupported value for registered object-
type OID
Action code: X'01' or X'1F'
020A..06 • 020D..02


Explanation: One or more of the following conditions
exists:
1. The registered object-type OID specified in the WOCC [IPDS-16-490]
is unsupported.
2. The registered object-type OID specified in the WOCC [IPDS-16-491]
is supported but is not valid within this state. For
example, setup files are not supported in page or
overlay state.
Alternate Exception Action: None
Page Continuation Action: Skip to End command
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
020D..05 Invalid or unsupported data within a
presentation object container
Action code: X'01' or X'09' or X'1F'
Explanation: The data within a WOC command for a
presentation object container is invalid, unsupported, or
missing. The registered object-type OID in the WOCC
command indicates whether the object is a presentation or
a non-presentation object container.
Alternate Exception Action: None
Page Continuation Action: Skip to End command
Support: Mandatory
Notes:
1. Sense bytes 16–17 can contain an object-specific [IPDS-16-492]
error code; refer to “Error Codes for Other Data
Objects”  for a list of object-specific error
codes. X'0000' in sense bytes 16–17 indicates that no
object-specific error code has been provided.
2. This exception ID is used when a presentation object [IPDS-16-493]
container requires some data, but no WOC data was
provided.
3. This exception ID might indicate that the version of the [IPDS-16-494]
object container is not supported. Some printers return
information about version support in the XOH-OPC
Object-Container-Version-Support self-defining field.
020D..06 Object container presentation data
extends outside object area
Action code: X'01' or X'1F'
Explanation: A portion of the object container
presentation data extends beyond the intersection of the
mapped presentation space and object container object
area.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
020D..07 Image resolution required but not supplied
Action code: X'01' or X'1F'
Explanation: The image resolution for a presentation
object (such as, a TIFF , GIF , JPEG, or JPEG2000 image)
is needed to process the object, but the resolution was not
provided within the presentation object and was not
provided by an Image Resolution (X'9A') triplet.
Alternate Exception Action: Assume that the image
resolution is the same as the device resolution.
Page Continuation Action: Assume that the image
resolution is the same as the device resolution.
Support: Mandatory if the Image Resolution (X'9A') triplet
is supported.
020D..0F Extender entry has nothing to extend
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists for an extender entry (an entry with HAID=X'E47D')
in a DORE command:
1. The extender entry is the first entry in the command. [IPDS-16-495]
2. The extender entry immediately follows a HAID-only [IPDS-16-496]
entry.
Alternate Exception Action: None
Page Continuation Action: Ignore the extender entry and
all subsequent entries in the command.
Support: Mandatory if extender entries are supported in
the DORE.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
020D..10 Data object resource not found
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists with a
secondary resource while printing a data object:
1. A presentation data object references a data object [IPDS-16-497]
resource by an internal resource ID, but there is no
active DORE or DORE2
equivalence entry containing
the referenced internal resource ID.
2. A presentation data object references a data object [IPDS-16-498]
resource by an internal resource ID, a DORE or
DORE2 equivalence entry with that internal resource
ID is found, but the resource identified by the
corresponding HAID is not activated.
3. The resource identified by a DORE or DORE2 [IPDS-16-499]
equivalence entry with only a HAID is not activated
when the resource is required to present a data object.
4. An Invoke Tertiary Resource (X'A2') triplet references [IPDS-16-500]
a data object resource by an internal resource ID, but
there is no active DORE or DORE2 equivalence entry
containing the referenced internal resource ID.
5. An Invoke Tertiary Resource (X'A2') triplet references [IPDS-16-501]
a data object resource by an internal resource ID, a
DORE or DORE2
equivalence entry with that internal
020D..05 • 020D..10


resource ID is found, but the resource identified by the
corresponding HAID is not activated.
Alternate Exception Action: None
Page Continuation Action: If possible, continue
processing the presentation data object without the
resource
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
020D..11 Invalid HAID value specified
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. The Host-Assigned ID value specified in a DDOFC, [IPDS-16-502]
DDOR, DORE, DORE2,
ICMR, IDO, home-state
WIC2, or home-state WOCC command is invalid.
2. A Host-Assigned ID value of X'0000' is specified in an [IPDS-16-503]
IDO command, in a home-state WIC2 command, or in
a home-state WOCC command for a non-setup file
resource.
Alternate Exception Action: None
Page Continuation Action: For DDOFC, DDOR, WIC2,
and WOCC, none. For DORE, DORE2, and ICMR, ignore
the entry and all subsequent entries in the command. For
IDO, ignore the IDO command.
Support: Mandatory
Notes:
1. The invalid HAID value is specified in sense bytes 14– [IPDS-16-504]
15.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-505]
number of a specific cause for the error.
020D..12 Invalid DORE or DORE2
equivalence entry
length value
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. An invalid equivalence entry length value is specified [IPDS-16-506]
in a Data Object Resource Equivalence (DORE) or
Data Object Resource Equivalence 2 (DORE2)
command.
2. A DORE or DORE2 equivalence entry is too long to fit [IPDS-16-507]
into the DORE or DORE2 command.
Alternate Exception Action: None
Page Continuation Action: Ignore the invalid
equivalence entry and all subsequent entries in the DORE
or DORE2 command.
Support: Mandatory
Sense bytes 16–17 should contain the explanation number
of a specific cause for the error.
020D..13 Invalid data object type for an IDO
command
Action code: X'01' or X'1F'
Explanation: The data object resource identified by an
IDO command HAID parameter cannot be included with an
IDO command. The data object types that can be included
with an IDO command are:
• EPS (Encapsulated PostScript) with transparency [IPDS-16-508]
• EPS without transparency [IPDS-16-509]
• GIF (Graphics Interchange Format) [IPDS-16-510]
• IOCA (Image Object Content Architecture) image [IPDS-16-511]
• JPEG (Joint Photographic Experts Group) AFPC JPEG [IPDS-16-512]
Subset
• JP2 (JPEG2000 File Format) [IPDS-16-513]
• PCL (Printer Command Language) page object [IPDS-16-514]
• PDF (Portable Document Format) multiple-page file with [IPDS-16-515]
transparency
• PDF multiple-page file without transparency [IPDS-16-516]
• PDF single page with transparency [IPDS-16-517]
• PDF single page without transparency [IPDS-16-518]
• PNG (Portable Network Graphics) AFPC PNG Subset [IPDS-16-519]
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-16-520]
• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-16-521]
• TIFF with transparency [IPDS-16-522]
• TIFF without transparency [IPDS-16-523]
• TIFF multiple-image file with transparency [IPDS-16-524]
• TIFF multiple-image file without transparency [IPDS-16-525]
• UP
3I Print Data
Alternate Exception Action: None
Page Continuation Action: Ignore the IDO command
Support: Mandatory
020D..14 Data object resource specified on a
DDOFC or DDOR command not activated
Action code: X'01'
Explanation: The data object resource identified by the
HAID parameter in a DDOFC or DDOR command is not
currently activated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
020D..15 Data object resource requested by an IDO
command not activated
Action code: X'01' or X'1F'
Explanation: The data object resource identified by the
HAID parameter in the DODD self-defining field of an IDO
command has not been activated or has been deactivated
before its current use.
Alternate Exception Action: None
Page Continuation Action: Ignore the IDO command
Support: Mandatory
020D..11 • 020D..15


020D..16 Data object resource Host-Assigned ID
already assigned
Action code: X'01'
Explanation: The data-object-resource HAID specified in
a home-state WIC2 command or home-state WOCC
command has already been used in a previously received
AR, WIC2, or WOCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: The HAID value is specified in sense bytes 14–15.
020D..17 Inappropriate secondary resource invoked
for a data object
Action code: X'01' or X'1F'
Explanation: A data-object-resource is invoked for a
presentation data object, but is not a valid object type for
that data object.
Alternate Exception Action: None
Page Continuation Action: If possible, continue
processing the presentation data object without the
secondary resource.
Support: Mandatory
020D..18 Unable to deactivate a component of an
activated data-object font
Action code: X'01'
Explanation: An attempt has been made to deactivate a
component of a currently activated data-object font. Before
deactivating a data-object-font component or a code page,
all data-object fonts that use these components must first
be deactivated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
020D..19 Invalid HAID pool
Action code: X'01' or X'1F'
Explanation: The HAID-pool parameter value in a
DORE2 command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Search the data-object-
resource HAID pool
Support: Mandatory if the Data Object Resource
Equivalence 2 (DORE2) command is supported
020D..20 Invoked CMR not activated
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. The Host-Assigned ID value specified in an ICMR [IPDS-16-526]
command entry or in an Invoke CMR (X'92') triplet is
not currently activated.
2. The Host-Assigned ID value specified in an ICMR [IPDS-16-527]
command entry or in an Invoke CMR (X'92') triplet is
activated, but is not a Color Management Resource
(CMR).
Invoke CMR (X'92') triplets can occur on IDO, LPD, RPO,
WBCC, WGC, WIC2, WOCC, and WTC commands.
Alternate Exception Action: None
Page Continuation Action: Ignore the CMR.
Support: Mandatory, if color management is supported
Notes:
1. The HAID value is specified in sense bytes 14–15. [IPDS-16-528]
2. Sense bytes 16–17 should contain the explanation [IPDS-16-529]
number of a specific cause for the error.
020D..30 Resource to be removed is currently
activated
Action code: X'01'
Explanation: A Remove Resident Resource (RRR)
command specified the OID of a resource that is currently
activated. Only deactivated resident resources can be
removed from printer storage.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Remove Resident Resource
(RRR) command is supported
Note: Sense bytes 14–15 contain the HAID of the currently
activated resource.
020D..31 Resource cannot be removed
Action code: X'01'
Explanation: A Remove Resident Resource (RRR)
command specified the OID of a resource that the printer
cannot remove. Printer-default resources cannot be
removed; also, some printers do not allow certain resident
resources to be removed. Note that this exception ID is not
used when the RRR command requests the removal of all
deactivated resident resources.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Remove Resident Resource
(RRR) command is supported
020D..32 Invalid object OID
Action code: X'01'
Explanation: A Remove Resident Resource (RRR)
command specified an invalid OID. The value specified in
020D..16 • 020D..32


the object-OID field is not a valid ASN.1 definite-short-form
OID.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Remove Resident Resource
(RRR) command is supported
020E..01 Invalid area coloring triplet length
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a X'4E' or X'70' triplet [IPDS-16-530]
contains an invalid value.
2. A X'4E' or X'70' triplet is too long to fit in its containing [IPDS-16-531]
command.
The triplet occurs in an IDO, LPD, RPO, WBCC, WGC,
WIC2, WOCC, or WTC command.
Alternate Exception Action: None
Page Continuation Action: Ignore the triplet in error and
all subsequent triplets in the command.
Support: Mandatory when logical page and object area
coloring is supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
020E..02 Invalid or unsupported color space
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. The color space field in a Color Specification (X'4E') [IPDS-16-532]
triplet contains an invalid or unsupported value. The
triplet occurs in either an IDO, LPD, RPO, WBCC,
WGC, WIC2, WOCC, or WTC command.
2. The color space field in a PTOCA Set Extended Text [IPDS-16-533]
Color (SEC) control sequence contains an invalid or
unsupported value. The control sequence occurs in a
Write Text command.
3. The color space field in a GOCA Set Process Color [IPDS-16-534]
(GSPCOL), Linear Gradient (GLGD), or Radial
Gradient (GRGD) drawing order contains an invalid or
unsupported value. The drawing order occurs in a
Write Graphics command.
Alternate Exception Action: For SEC and GSPCOL,
ignore the specified color value and use a highlight color if
one is supported, else use the printer default color. For the
Color Specification (X'4E') triplet, ignore the specified color
value and use a highlight color if one is supported, else use
color of medium. For GLGD and GRGD, ignore the
drawing order.
Page Continuation Action: For SEC and GSPCOL,
ignore the specified color value and use a highlight color if
one is supported, else use the printer default color. For the
Color Specification (X'4E') triplet, ignore the specified color
value and use a highlight color if one is supported, else use
color of medium. For GLGD and GRGD, ignore the
drawing order.
Support: Mandatory when logical page and object area
coloring, PTOCA PT3, GOCA gradients, or GOCA
GSPCOL is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-535]
GOCA and PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-536]
number of a specific cause for the error.
020E..03 Invalid or unsupported color value
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. The color value field in a Color Specification (X'4E') [IPDS-16-537]
triplet contains an invalid or unsupported value. The
triplet occurs in either an IDO, LPD, RPO, WBCC,
WGC, WIC2, WOCC, or WTC command.
2. The color value field in a PTOCA Set Extended Text [IPDS-16-538]
Color (SEC) control sequence contains an invalid or
unsupported value. The control sequence occurs in a
Write Text command.
3. The color value field in a GOCA Set Process Color [IPDS-16-539]
(GSPCOL), Linear Gradient (GLGD), or Radial
Gradient (GRGD) drawing order contains an invalid or
unsupported value. The drawing order occurs in a
Write Graphics command.
4. A specified highlight color number is in the range [IPDS-16-540]
X'0100'–X'FFFF', but a host-invoked indexed CMR is
not found.
5. An indexed CMR is selected for use with a highlight [IPDS-16-541]
color value, but the color value is not found in the
indexed CMR. Note that only the selected indexed
CMR is searched for this color value.
Alternate Exception Action: For SEC and GSPCOL, use
a highlight color if one is supported, else use the printer
default color. For the Color Specification (X'4E') triplet, use
a highlight color if one is supported, else use color of
medium. For GLGD and GRGD, ignore the drawing order.
Page Continuation Action: For SEC and GSPCOL, use
a highlight color if one is supported, else use the printer
default color. For the Color Specification (X'4E') triplet, use
a highlight color if one is supported, else use color of
medium. For GLGD and GRGD, ignore the drawing order.
Support: Mandatory when logical page and object area
coloring, PTOCA PT3, GOCA gradients, or GOCA
GSPCOL is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-542]
GOCA and PTOCA.
020E..01 • 020E..03


2. For printers that support color fidelity control, reporting [IPDS-16-543]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
3. Sense bytes 16–17 should contain the explanation [IPDS-16-544]
number of a specific cause for the error.
020E..04 Invalid percent value
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. Either the coverage field, the shading field, or both in a [IPDS-16-545]
Color Specification (X'4E') triplet for a highlight color
contains an invalid value. The triplet occurs in either an
IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, or
WTC command.
2. Either the coverage field, the shading field, or both in a [IPDS-16-546]
PTOCA Set Extended Text Color (SEC) control
sequence for a highlight color contains an invalid
value. The control sequence occurs in a Write Text
command.
3. Either the coverage field, the shading field, or both in a [IPDS-16-547]
GOCA Set Process Color (GSPCOL), Linear Gradient
(GLGD), or Radial Gradient (GRGD) drawing order for
a highlight color contains an invalid value. The drawing
order occurs in a Write Graphics command.
Alternate Exception Action: Use the maximum
percentage for the invalidly specified value.
Page Continuation Action: Use the maximum
percentage for the invalidly specified value.
Support: Mandatory when logical page and object area
coloring, PTOCA PT3, GOCA gradients, or GOCA
GSPCOL is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-548]
GOCA and PTOCA.
2. For printers that support color fidelity control, reporting [IPDS-16-549]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
3. Sense bytes 16–17 should contain the explanation [IPDS-16-550]
number of a specific cause for the error.
020E..05 Invalid or unsupported number of bits for a
color component
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. An invalid or unsupported value is specified in the [IPDS-16-551]
Colsize1, Colsize2, Colsize3, or Colsize4 field of a
Color Specification (X'4E') triplet. The triplet occurs in
either an IDO, LPD, RPO, WBCC, WGC, WIC2,
WOCC, or WTC command.
2. The Colsize fields in a Color Specification (X'4E') triplet [IPDS-16-552]
specify length values that are inconsistent with the
triplet length value.
3. An invalid or unsupported value is specified in the [IPDS-16-553]
Colsize1, Colsize2, Colsize3, or Colsize4 field of a
PTOCA Set Extended Text Color (SEC) control
sequence. The control sequence occurs in a Write
Text command.
4. The Colsize fields in a PTOCA Set Extended Text [IPDS-16-554]
Color (SEC) control sequence specify length values
that are inconsistent with the control sequence length
value.
5. An invalid or unsupported value is specified in the [IPDS-16-555]
Colsize1, Colsize2, Colsize3, or Colsize4 field of a
GOCA Set Process Color (GSPCOL), Linear Gradient
(GLGD), or Radial Gradient (GRGD) drawing order.
The drawing order occurs in a Write Graphics
command.
6. The Colsize fields in a GOCA Set Process Color [IPDS-16-556]
(GSPCOL) drawing order specify length values that
are inconsistent with the drawing order length value.
7. The Colsize fields in a GOCA Linear Gradient (GLGD) [IPDS-16-557]
or Radial Gradient (GRGD) drawing order specify
length values that are inconsistent with the length
value in the color specification for the start color.
Alternate Exception Action: For SEC and GSPCOL,
ignore the specified color value and use a highlight color if
one is supported, else use the printer default color. For the
Color Specification (X'4E') triplet, ignore the specified color
value and use a highlight color if one is supported, else use
color of medium. For GLGD and GRGD, ignore the
drawing order.
Page Continuation Action: For SEC and GSPCOL,
ignore the specified color value and use a highlight color if
one is supported, else use the printer default color. For the
Color Specification (X'4E') triplet, ignore the specified color
value and use a highlight color if one is supported, else use
color of medium. For GLGD and GRGD, ignore the
drawing order.
Support: Mandatory when logical page and object area
coloring, PTOCA PT3, GOCA gradients, or GOCA
GSPCOL is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-558]
GOCA and PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-559]
number of a specific cause for the error.
020F ..01 Invalid or unsupported Set Text [IPDS-16-560]
## Orientation (STO)
Action code: X'01' or X'1F'
020E..04 • 020F ..01


Explanation: One or more of the following conditions
exists:
1. The inline or baseline orientation specified in a text [IPDS-16-561]
STO control sequence is an invalid or unsupported
value.
2. The combination of the baseline and inline orientations [IPDS-16-562]
is invalid or unsupported.
Alternate Exception Action: Use an inline orientation of
0° and a baseline orientation of 90°.
Page Continuation Action: Use an inline orientation of 0°
and a baseline orientation of 90°.
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-563]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-564]
number of a specific cause for the error.
0210..01 Invalid or unsupported Set Inline Margin [IPDS-16-565]
(SIM)
Action code: X'01' or X'1F'
Explanation: The inline margin value specified in a text
Set Inline Margin control sequence or in an LPD or WTC
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence. LPD and WTC errors have no PCA.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0211..01 Invalid or unsupported Set Baseline [IPDS-16-566]
## Increment (SBI)
Action code: X'01' or X'1F'
Explanation: The baseline increment value specified in a
text Set Baseline Increment control sequence or in an LPD
or WTC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence. LPD and WTC errors have no PCA.
Support: Mandatory
0212..01 Invalid or unsupported intercharacter [IPDS-16-567]
adjustment
Action code: X'01' or X'1F'
Explanation: The intercharacter adjustment value
specified in a text Set Intercharacter Adjustment control
sequence or in an LPD or WTC command is invalid or
unsupported.
Alternate Exception Action: If the intercharacter
adjustment value is invalid, none. If the intercharacter
adjustment value is valid but not supported, the printer
uses the next smallest supported value. If the direction
value is invalid or not supported, the printer uses X'00'
(increment).
Page Continuation Action: Ignore the Write Text control
sequence. LPD and WTC errors have no PCA.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0212..02 Font storage is full [IPDS-16-568]
Action code: X'0C'
Explanation: One or more of the following conditions
exists:
1. Either pattern storage or auxiliary storage is [IPDS-16-569]
insufficient to store the font transmitted with an LF
command.
2. Insufficient storage exists to load the data transmitted [IPDS-16-570]
with an LSS command.
3. Insufficient storage exists to activate the font specified [IPDS-16-571]
in an LFE command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Notes:
1. Some printers report this exception as X'023A..02' or [IPDS-16-572]
X'02AF ..01'. The preferred exception ID is X'02AF ..01'.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-573]
number of a specific cause for the error.
0213..01 Invalid or unsupported Absolute Move [IPDS-16-574]
## Baseline (AMB)
Action code: X'01' or X'1F'
Explanation: The position value specified in a text
Absolute Move Baseline control sequence is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0214..01 Invalid or unsupported Absolute Move [IPDS-16-575]
## Inline (AMI)
Action code: X'01' or X'1F'
0210..01 • 0214..01 [IPDS-16-576]


Explanation: The position value specified in a text
Absolute Move Inline control sequence is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0214..02 The font resource to be deactivated was [IPDS-16-577]
not found
Action code: X'01'
Explanation: The data-object font, coded font, double-
byte coded-font section, font index, code page, or font
character set specified by a DF command has not been
previously activated via an AR, LCPC, LFC, LFCSC, LFE,
LFI, or LSS command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0214..03 Unsupported baseline move [IPDS-16-578]
Action code: X'01' or X'1F'
Explanation: Unsupported advancement of the baseline
coordinate toward the I-axis.
Alternate Exception Action: None
Page Continuation Action: None
Support: Refer to Presentation Text Object Content
Architecture Reference.
Notes:
1. Some printers report this exception as X'0205..02'. [IPDS-16-579]
The preferred exception is X'0205..02'.
2. This corresponds to an exception code defined by [IPDS-16-580]
PTOCA.
0215..01 Invalid or unsupported Relative Move [IPDS-16-581]
## Inline (RMI)
Action code: X'01' or X'1F'
Explanation: The displacement value specified in a text
Relative Move Inline control sequence is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Refer to Presentation Text Object Content
Architecture Reference.
Notes:
1. Some printers report this exception as X'0860..00'. [IPDS-16-582]
The preferred exception ID is X'0860..00'.
2. This corresponds to an exception code defined by [IPDS-16-583]
PTOCA.
0215..02 Invalid or unsupported DF command font [IPDS-16-584]
or font-section ID
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The font Host-Assigned ID field is required in the DF [IPDS-16-585]
command; however, it is not present or has an invalid
or unsupported value.
2. The double-byte coded font-section-ID field is required [IPDS-16-586]
in the DF command; however, it is not present or has
an invalid or unsupported value.
3. The font-inline-sequence field is required; but is not [IPDS-16-587]
present in the DF command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0216..01 Invalid or unsupported Relative Move [IPDS-16-588]
## Baseline (RMB)
Action code: X'01' or X'1F'
Explanation: The displacement value specified in a text
Relative Move Baseline control sequence is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Refer to Presentation Text Object Content
Architecture Reference.
Notes:
1. Some printers report this exception as X'0860..00'. [IPDS-16-589]
The preferred exception ID is X'0860..00'.
2. This corresponds to an exception code defined by [IPDS-16-590]
PTOCA.
0217..01 Invalid or unsupported Set Variable-Space [IPDS-16-591]
## Character Increment (SVI)
Action code: X'01' or X'1F'
Explanation: The increment value specified in a text Set
Variable-Space Character Increment control sequence is
invalid or unsupported.
0214..02 • 0217..01 [IPDS-16-592]


Alternate Exception Action: If invalid, none. If
unsupported, the printer uses the next-smallest supported
value.
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0217..02 Invalid or unsupported value for DF [IPDS-16-593]
command deactivation type
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The deactivation type in a DF command is invalid or [IPDS-16-594]
unsupported.
2. The font Host-Assigned ID value identifies a double- [IPDS-16-595]
byte coded font, but the font-deactivation type
specifies a single-byte coded font.
3. The font Host-Assigned ID value identifies a single- [IPDS-16-596]
byte coded font, but the font-deactivation type
specifies a double-byte coded font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0218..02 Invalid, unsupported, or unavailable font [IPDS-16-597]
ID
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The font Host-Assigned ID in an LSS, LFC, LFI, or LFE [IPDS-16-598]
command is invalid.
2. The font Host-Assigned ID in an LFI command does [IPDS-16-599]
not match that of any previously received LFC.
3. The font local ID in an LFE command is invalid or [IPDS-16-600]
unsupported.
4. A font is referenced in a Set Coded-Font Local (SCFL) [IPDS-16-601]
control sequence or in an LPD, LSS, WBCC, or WTC
command, but the font has not been previously
identified by the LFE command.
5. The font referenced in an LPD, WBCC, WT , or WTC [IPDS-16-602]
command is defined within the current LFE command
but is not activated.
Alternate Exception Action: If the exception occurs
because a font defined within the current LFE command is
not activated in the printer when needed, the printer can try
to make an appropriate font substitution that preserves as
many characteristics as possible of the originally requested
font while still preserving the original code page. If an
appropriate font substitution cannot be made or if the
exception occurs for any other reason, there is no AEA.
Page Continuation Action: If the exception occurs
because a coded font defined within the current LFE
command is not activated in the printer when needed or
because the local ID is not present in the current LFE
command, the printer can substitute a printer default coded
font if one exists. If the desired coded font is double-byte
and the printer default coded font is single byte, the data
will not print correctly. If a font substitution is not made or if
the exception occurs for any other reason, there is no PCA.
Support: Mandatory
Notes:
1. Some printers report this exception as X'0404..00' [IPDS-16-603]
when a font to be used with bar code data is
unavailable. The preferred exception in this case is
X'0404..00'.
2. This corresponds to an exception code defined by [IPDS-16-604]
PTOCA.
3. Sense bytes 16–17 should contain the explanation [IPDS-16-605]
number of a specific cause for the error.
0219..01 Invalid or unsupported value for Repeat [IPDS-16-606]
String (RPS) repeat length
Action code: X'01' or X'1F'
Explanation: A text Repeat String control sequence target
count is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0219..02 Multiple occurrences of the same LFE font [IPDS-16-607]
local ID
Action code: X'01' or X'1F'
Explanation: The font local ID in an LFE command has
been used more than once, making the font Host-Assigned
ID ambiguous.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
021A..01 Text string length error
Action code: X'01' or X'1F'
Explanation: An odd number of data bytes was specified
for a PTOCA Repeat String (RPS), Transparent Data
(TRN), or Unicode Complex Text (UCT) control sequence,
but the font specified double-byte code points. This
exception can also occur in two cases when dealing with
encrypted text and the font specified double-byte code
0217..02 • 021A..01 [IPDS-16-608]


points: either after decryption, there was an odd number of
decrypted bytes for a PTOCA Encrypted Data (ENC)
string, or if decryption fails, an odd number of bytes was
specified for a PTOCA Set Encrypted Alternate (SEA)
string that was used as alternate text.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
021A..03 Invalid Unicode data
Action code: X'01' or X'1F'
Explanation: One of the following errors exists in PTOCA
text data within a Write Text command:
• A high-order surrogate code value is not immediately [IPDS-16-609]
followed by a low-order surrogate code value. The high-
order surrogate range is U+D800 through U+DBFF .
• A low-order surrogate code value is not immediately [IPDS-16-610]
preceded by a high-order surrogate code value. The low-
order surrogate range is U+DC00 through U+DFFF .
• An illegal UTF-8 byte sequence, as defined in the [IPDS-16-611]
Unicode 3.2 Specification, is specified.
Note: For a formal definition of UTF-8 consult the
Unicode 3.2 Specification. The error cases can be
summarized as follows:
– The value in the first byte of the UTF-8
sequence is not in the legal UTF-8 range (X'00'–
X'7F' and X'C2'–X'F4').
– The value in the second byte of the UTF-8
sequence is not in the legal UTF-8 range
allowed by the value in the first byte. The valid
ranges for the second byte are as follows:
1st byte 2nd byte
X'C2'–X'DF' X'80'–X'BF'
X'E0' X'A0'–X'BF'
X'E1'–X'EC' X'80'–X'BF'
X'ED' X'80'–X'9F'
X'EE'–X'EF' X'80'–X'BF'
X'F0' X'90'–X'BF'
X'F1'–X'F3' X'80'–X'BF'
X'F4' X'80'–X'8F'
– The value in the third or fourth byte of the UTF-8
sequence is not in the legal UTF-8 range for that
byte (X'80'–X'BF').
Alternate Exception Action: If the invalid Unicode data
was within an ENC,
RPS, SEA, TRN, or UCT control
sequence, skip to the end of that control sequence
(including UCT code points) and continue processing. If the
invalid Unicode data was not within a control sequence,
skip to the next control sequence or non-WT command; if
an anystate command is encountered, process it and then
keep skipping.
Page Continuation Action: If the invalid Unicode data
was within an ENC,
RPS, SEA, TRN, or UCT control
sequence, skip to the end of that control sequence
(including UCT code points) and continue processing. If the
invalid Unicode data was not within a control sequence,
skip to the next control sequence or non-WT command; if
an anystate command is encountered, process it and then
keep skipping.
Support: Mandatory if Unicode data is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-612]
PTOCA.
2. Sense bytes 16–17 contain the Unicode code value in [IPDS-16-613]
error; see note 3  for more information.
021B..01 Repeat String (RPS) target-string length
exception
Action code: X'01' or X'1F'
Explanation: The target-string length for a text Repeat
String control sequence must be an even number for
double-byte coded fonts.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
021B..02 Invalid or unsupported unit base for L-
units value in Load Font Control
Action code: X'01'
Explanation: The unit base for L-units value in an LFC
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
021C..01 Invalid escape sequence
Action code: X'01' or X'1F'
Explanation: In a WT command, the second byte of what
should be an escape sequence (X'2BD3') is not X'D3'.
Alternate Exception Action: None
Page Continuation Action: Skip to the next DORE,
DORE2,
END, EP , IDO, IO, IPS, ISP , LFE, WBCC, WGC,
WIC, WIC2, WOCC, or WTC command.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
021C..02 Invalid LFC command byte-count value
Action code: X'01'
021A..03 • 021C..02


Explanation: An invalid value is specified in the byte-
count field (bytes 18–20) of a Load Font Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
021D..02 Invalid or unsupported value for the Load
Font Equivalence GRID
Action code: X'01' or X'1F'
Explanation: One or more of the parameters in the GRID
field (bytes 5–12) of the LFE command are invalid,
unsupported, or inconsistent.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
021E..01 Invalid text control-sequence length
Action code: X'01' or X'1F'
Explanation: The length of a text control sequence in a
Write Text command is invalid.
Alternate Exception Action: None
Page Continuation Action: Skip to the next DORE,
DORE2,
END, EP , IDO, IO, IPS, ISP , LFE, WBCC, WGC,
WIC, WIC2, WOCC, or WTC command.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
021E..02 Mismatch between coded font and the XOA
Print Quality Control (PQC) command
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The combination of parameters specified in the LFE [IPDS-16-614]
command is unsupported when used with the quality
level indicated by the XOA-PQC command.
2. The FGID value specified in the LFE command is [IPDS-16-615]
invalid or unsupported or is invalid with the other font
parameters.
Alternate Exception Action: Use the best fit font that is
available in the requested quality. This means that a font
with at minimum, the requested code page and a quality
level as close to the requested quality level as possible is
used. In addition, the printer should preserve as many
other of the requested font characteristics as possible.
Page Continuation Action: Use the best fit font that is
available in the requested quality. This means that a font is
used that has at least the requested code page and the
closest quality level to the one requested. In addition, the
printer should preserve as many other of the requested
font characteristics as possible.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
021F ..01 Repeat String (RPS) length exception
Action code: X'01' or X'1F'
Explanation: A text Repeat String control sequence in a
WT command has a nonzero fill count, but a zero string
length.
Alternate Exception Action: None
Page Continuation Action: Ignore the Write Text control
sequence.
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
021F ..02 LFE command font Host-Assigned ID
already assigned
Action code: X'01' or X'1F'
Explanation: The HAID in an LFE command has already
been assigned to a previously activated coded font that
differs from the GRID in the current LFE command, or has
already been assigned to a data-object font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0220..01 Double-byte MICR font section mismatch [IPDS-16-616]
Action code: X'01'
Explanation: MICR printing is specified for one section of
a double-byte coded font, but not for all sections of that
coded font. If the intended-use flags for one section of a
double-byte coded font specify MICR printing, all sections
of that coded font must specify MICR printing.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0220..02 Invalid LFC reserved byte [IPDS-16-617]
Action code: X'01'
Explanation: The value of reserved byte 36 in a Load
Font Control command is not X'01'.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0221..02 Invalid or unsupported value for Load Font [IPDS-16-618]
Control font-index format
Action code: X'01'
021D..02 • 0221..02


Explanation: The font-control record and font-index table-
format value (byte 3) in an LFC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
0222..02 Invalid or unsupported Load Font Control [IPDS-16-619]
data pattern format
Action code: X'01'
Explanation: The pattern-data format value (byte 4)
specified in an LFC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0223..02 Invalid or unsupported value for Load Font [IPDS-16-620]
Control font-type bits
Action code: X'01'
Explanation: The font-type bits in an LFC command are
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0226..02 Invalid or unsupported LSS or LFC X-box [IPDS-16-621]
size
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The character X-box size (specified in byte 6 in an LSS [IPDS-16-622]
command, in bytes 6 and 7 in an LFC command, or in
bytes 0 and 1 of a character-pattern descriptor in an
LFC command) is invalid or unsupported, or is
incompatible with the specified font.
2. The character X-box size (specified in bytes 6 and 7 in [IPDS-16-623]
an LFC command, or in bytes 0 and 1 of a character-
pattern descriptor in an LFC command) is greater than
the maximum X-box size.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0227..02 Invalid or unsupported LSS or LFC Y-box [IPDS-16-624]
size
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The character Y-box size (specified in byte 7 in an LSS [IPDS-16-625]
command, in bytes 8 and 9 in an LFC command, or in
bytes 2 and 3 of a character-pattern descriptor in an
LFC command) is invalid or unsupported, or is
incompatible with the specified font.
2. The character Y-box size (specified in bytes 8 and 9 in [IPDS-16-626]
an LFC command, or in bytes 2 and 3 of a character-
pattern descriptor in an LFC command) is greater than
the maximum Y-box size.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0228..02 The LSS pattern download format is either [IPDS-16-627]
reserved or unsupported
Action code: X'01'
Explanation: The specified pattern download format in an
LSS command is either reserved or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0229..02 Invalid or unsupported value for LSS [IPDS-16-628]
additional parameter byte length
Action code: X'01'
Explanation: The additional parameter byte length
specified in an LSS command is outside the range X'0D'
through X'FF' or is unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: The 3800 printer defines this exception ID as “The
constant X
p and Yp box size values are not equal”.
022A..02 Invalid or unsupported value for Load Font
Control units per unit base in the X
direction
Action code: X'01'
Explanation: The units per unit base for L-units in the X
direction value in an LFC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0222..02 • 022A..02 [IPDS-16-629]


022B..02 Invalid or unsupported value for Load Font
Control units per unit base in the Y
direction
Action code: X'01'
Explanation: The units per unit base for L-units in the Y
direction value in an LFC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
022D..02 Invalid or unsupported value for Load Font
Control pattern-data alignment
Action code: X'01'
Explanation: The pattern-data alignment value in an LFC
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
022E..02 Insufficient font data received
Action code: X'01'
Explanation: The font data received is less than that
specified in the byte-count field in an LFC or LFCSC
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0231..01 Invalid or unsupported value for Load [IPDS-16-630]
Copy Control number of copies
Action code: X'01'
Explanation: The number of copies specified in an LCC
command is invalid or unsupported.
Alternate Exception Action: Proceed as though the
number-of-copies field were set to 1.
Page Continuation Action: None
Support: Mandatory
0232..01 Invalid or unsupported Load Copy Control [IPDS-16-631]
Keyword in copy-subgroup entry
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An LCC command keyword control ID is invalid or [IPDS-16-632]
unsupported.
2. An LCC command suppression keyword is invalid or [IPDS-16-633]
unsupported.
3. An LCC command overlay keyword is invalid or [IPDS-16-634]
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. Some printers report X'0232..01' when an LCC [IPDS-16-635]
command simplex/duplex keyword is invalid or
unsupported. The preferred Exception ID in this case
is X'0236..01'.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-636]
number of a specific cause for the error.
0232..02 Excess font data received [IPDS-16-637]
Action code: X'01'
Explanation: The font data received exceeds that
specified in the byte-count field in an LFC or LFCSC
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0233..02 Invalid or unsupported value for Load Font [IPDS-16-638]
Index maximum baseline extent
Action code: X'01'
Explanation: A maximum baseline extent value in an LFI
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'023C..02'.
The preferred exception ID is X'023C..02'.
0234..01 Invalid or unsupported value for Load [IPDS-16-639]
Copy Control entry-byte count
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The number of bytes in the LCC command copy [IPDS-16-640]
022B..02 • 0234..01


subgroup is not a multiple of two-byte pairs or is invalid
or unsupported.
2. The number of bytes in the LCC command copy [IPDS-16-641]
subgroup causes the copy subgroup to extend beyond
the total length of the LCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. If a printer limits the number of overlay keywords in a [IPDS-16-642]
copy subgroup and this number is exceeded,
exception ID X'0238..01' exists. If a printer limits the
number of suppression keywords in a copy subgroup
and this number is exceeded, exception ID X'0239..01'
exists.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-643]
number of a specific cause for the error.
0236..01 Invalid or unsupported Load Copy Control [IPDS-16-644]
simplex/duplex parameter
Action code: X'01'
Explanation: The LCC command simplex/duplex
parameter is invalid or unsupported.
Alternate Exception Action: If invalid, none. If
unsupported, the printer prints simplex.
An even number of copy subgroups must be specified for a
duplex operation. When the AEA causes the printer to
substitute simplex printing for duplex printing, each input
page is processed against both the frontside page
specification and the backside page specification. This
yields twice as many sheets for each input page as would
be printed if the printer could print duplex.
Page Continuation Action: None
Support: Mandatory
Note: Some printers report this exception as X'0232..01'
when an LCC command simplex/duplex keyword is
invalid or unsupported. The preferred exception ID in
this case is X'0236..01'.
0237..01 Invalid or unsupported Load Copy Control [IPDS-16-645]
N-up parameter
Action code: X'01'
Explanation: An N-up modification keyword parameter in
a Load Copy Control command is invalid or unsupported.
Alternate Exception Action: Print as if 1-up was
specified.
Page Continuation Action: None
Support: Mandatory
Note: Some printers use this exception ID when an invalid
or unsupported CSE keyword value is specified in an
LCC command; other printers ignore the invalid CSE
keyword pair. Ignoring the keyword in this case is
recommended.
0237..03 Invalid or unsupported Load Copy Control [IPDS-16-646]
media-destination parameter
Action code: X'01'
Explanation: The printer supports selection of a media
destination in the Load Copy Control command, but the
value specified is invalid or unsupported.
Alternate Exception Action: Use the printer-default
media destination.
Page Continuation Action: None
Support: Mandatory
0237..04 Incompatible media source and media [IPDS-16-647]
destination
Action code: X'06'
Explanation: The media source specified in a Load Copy
Control command or an XOH Select Input Media Source
command cannot be used with the media destination
specified in a Load Copy Control command. This exception
is detected while processing a Begin Page command.
Alternate Exception Action: Select an installed and
available media source.
Page Continuation Action: None
Support: Optional
Note: Sense bytes 12–13 contain the command code for a
BP command, sense byte 14 is reserved and should
contain X'00', sense byte 15 contains a media-
source ID, and sense bytes 16–17 contain the
media-destination ID that is incompatible with the
media-source ID.
0237..04 Incompatible media source and media [IPDS-16-648]
destination
Action code: X'09'
Explanation: The media source specified in a Load Copy
Control command or an XOH Select Input Media Source
command cannot be used with the media destination
specified in a Load Copy Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Sense bytes 12–13 contain the command code for
an EP or LCC command, sense byte 14 is reserved
and should contain X'00', sense byte 15 contains a
media-source ID, and sense bytes 16–17 contain
the media-destination ID that is incompatible with
the media-source ID.
0237..05 Mixture of media-source IDs or media- [IPDS-16-649]
destination IDs in a duplex copy-subgroup
pair
0236..01 • 0237..05 [IPDS-16-650]


Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. In a Load Copy Control command, duplex is specified, [IPDS-16-651]
but the media-source ID specified in the first LCC entry
of a copy-subgroup pair does not match the media-
source ID specified in the second LCC entry.
2. In a Load Copy Control command, duplex is specified, [IPDS-16-652]
but the media-destination ID specified in the first LCC
entry of a copy-subgroup pair does not match the
media-destination ID specified in the second LCC
entry.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0238..01 Maximum supported number of overlays [IPDS-16-653]
per LCC copy subgroup exceeded
Action code: X'01'
Explanation: The number of overlays specified for
inclusion in an LCC command copy subgroup exceeds the
maximum number supported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0238..03 Missing medium overlay HAID keyword [IPDS-16-654]
Action code: X'01'
Explanation: One of the following conditions exists in an
LCC command:
1. A X'E4' keyword is encountered but the next [IPDS-16-655]
sequential keyword is not a X'E5' keyword.
2. A X'E5' keyword is encountered without an [IPDS-16-656]
immediately preceding X'E4' keyword.
The medium overlay keywords (X'E4' and X'E5') must be
specified as a sequential pair of keywords.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0238..04 Missing preprinted form overlay HAID [IPDS-16-657]
keyword
Action code: X'01'
Explanation: One of the following conditions exists in an
LCC command:
1. A X'E6' keyword is encountered but the next [IPDS-16-658]
sequential keyword is not a X'E7' keyword.
2. A X'E7' keyword is encountered without an [IPDS-16-659]
immediately preceding X'E6' keyword.
The preprinted form overlay keywords (X'E6' and X'E7')
must be specified as a sequential pair of keywords.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if extended overlays and preprinted
form overlays are supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0238..10 Invalid invocation of a preprinted form [IPDS-16-660]
overlay with the LCC command
Action code: X'01'
Explanation: A preprinted form overlay was invoked in a
Load Copy Control (LCC) command, but a preprinted form
overlay has already been invoked for the copy subgroup;
only one X'D2nn' keyword (or one X'E6', X'E7' keyword
pair) is allowed for each copy subgroup.
Alternate Exception Action: Ignore the X'D2nn' keyword
or X'E6', X'E7' keyword pair.
Page Continuation Action: None
Support: Mandatory if preprinted form overlays are
supported
0238..11 Invalid preprinted form overlay keyword [IPDS-16-661]
value
Action code: X'01'
Explanation: A preprinted form overlay was invoked with
a X'D2nn' keyword in an LCC command, but an invalid
keyword value (nn) was specified; the keyword value must
be in the range X'01'–X'FE'.
Alternate Exception Action: Ignore the X'D2nn' keyword.
Page Continuation Action: None
Support: Mandatory if preprinted form overlays are
supported
0239..01 Maximum supported number of [IPDS-16-662]
suppressions per LCC copy subgroup
exceeded
Action code: X'01'
Explanation: The number of suppressions specified for
inclusion in an LCC command copy subgroup exceeds the
maximum number supported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0238..01 • 0239..01 [IPDS-16-663]


0239..02 LFC font Host-Assigned Resource ID [IPDS-16-664]
already assigned
Action code: X'01'
Explanation: One or more of the following conditions
exists in a Load Font Control command:
1. The single-byte fully described font HAID specified in [IPDS-16-665]
the LFC command has already been used in a
previously received AR, LFE, or LFC command to
activate a coded font.
2. The section ID specified in the LFC command for this [IPDS-16-666]
HAID has already been used in a previously received
AR, LFE, or LFC command to activate a coded font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
023A..02 Maximum number of activated font
components exceeded
Action code: X'01'
Explanation: An attempt is made to activate more coded-
font components than the printer can support. Some
printers limit the number of resident and downloaded
coded-font components that can be activated at a time.
This exception can be detected while processing an AR,
LFE, LCPC, LFCSC, LFC, or LSS command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'0212..02' or
X'02AF ..01'. In the case where font storage has been
exceeded, the preferred exception ID is X'02AF ..01'.
023B..01 IPDS command sequence interrupted
Action code: X'01'
Explanation: A sequence of IPDS spanning commands,
such as WT , WI, WI2, WG, WBC, and LF , that is normally
ended with and END command, and that should only be
interrupted by Anystate commands, is interrupted by a
carrying communications protocol function that requires the
printer to return data to the host. This is an error at the
carrying communications protocol level.
For example, a Read CCW or Sense Extended CCW is
issued between LF commands while the IPDS data is
being carried in a System/390 ® CCW chain.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
023B..02 Invalid LFI command double-byte
character flags
Action code: X'01'
Explanation: A character with a character-flag-bits entry
of B'001' (defined, printing, nonincrementing), B'100'
(undefined, printing, incrementing), or B'101' (undefined,
printing, nonincrementing) is received within a font index
for sections X'45' through X'FE' of a double-byte coded
font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
023C..02 Invalid or unsupported value in a Load
Font Index command
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The uniform or maximum baseline offset value in an [IPDS-16-667]
LFI command is invalid or unsupported.
2. The uniform or maximum character increment value in [IPDS-16-668]
an LFI command is invalid or unsupported.
3. The maximum baseline extent value in an LFI [IPDS-16-669]
command is invalid or unsupported.
4. The uniform or minimum A-space value in an LFI [IPDS-16-670]
command is invalid or unsupported.
5. The variable-space increment value in an LFI [IPDS-16-671]
command is invalid or unsupported.
6. A pattern-index value in an LFI command refers to a [IPDS-16-672]
nonexistent pattern.
7. A character-increment value in an LFI command is [IPDS-16-673]
invalid or unsupported.
8. An A-space value in an LFI command is invalid or [IPDS-16-674]
unsupported.
9. A baseline-offset value in an LFI command is invalid or [IPDS-16-675]
unsupported.
10. A parameter value specified for an individual character [IPDS-16-676]
is greater than or less than the respective maximum or
minimum value specified for that parameter.
11. The combination of baseline offset and pattern size for [IPDS-16-677]
a character is incompatible with the baseline-extent
value specified.
12. The underscore width value in an LFI command is [IPDS-16-678]
invalid or unsupported.
13. One or more orientation flags for a section between [IPDS-16-679]
X'45' and X'FE' inclusive is not uniform. These
sections must have a uniform A-space, uniform
baseline offset, and uniform character increment.
Alternate Exception Action: None
Page Continuation Action: None
0239..02 • 023C..02 [IPDS-16-680]


Support: Mandatory for all parameters used by the
printer. Some IPDS printers can properly present character
data without using some of the information in the LFI
command. These printers ignore the unneeded value and
do not syntax check the unused parameter.
Notes:
1. Some printers generate exception X'0233..02' for an [IPDS-16-681]
invalid or unsupported maximum baseline extent. The
preferred exception ID in this case is X'023C..02'.
2. The maximum baseline extent value is provided in the [IPDS-16-682]
LFI command to assist with VPA checking, and is not
needed to properly present character data. Some
IPDS printers do not use the maximum baseline extent
value from the LFI command; these printers also
ignore the value in this field. Because of
documentation errors, some double-byte LF1-type font
objects were built containing an invalid maximum
baseline extent value.
3. Some double-byte raster fonts were built with incorrect [IPDS-16-683]
LFI orientation flag settings for sections X'45'–X'FE'
(these flags should be B'1' for each of these sections).
Because the font can be used successfully even with
the incorrect flag setting, some IPDS printers do not
check these flags for those sections.
4. Sense bytes 16–17 should contain the explanation [IPDS-16-684]
number of a specific cause for the error.
023E..02 Invalid LFC character-pattern address
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The character-pattern addresses in the Character [IPDS-16-685]
Pattern-Descriptor List of an LFC command are not
ascending.
2. A character-pattern address in the Character Pattern- [IPDS-16-686]
Descriptor List of an LFC command points past the
end of the raster data.
3. A character-pattern address in the Character Pattern- [IPDS-16-687]
Descriptor List of an LFC command points to a
character pattern that extends beyond the end of the
raster data.
4. A character-pattern address in the Character Pattern- [IPDS-16-688]
Descriptor List of an LFC command points into a
previously defined character pattern.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
023F ..02 STO-SCFL-LFE mismatch
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A fully described font or font index required as a result [IPDS-16-689]
of combining a Set Coded-Font Local (SCFL) control
sequence or an LPD or WTC command with a Set Text
Orientation (STO) control sequence and an LFE
command does not exist within the printer when
needed.
2. The printer does not support the requested [IPDS-16-690]
combination of Set Text Orientation and Font Inline
Sequence for the requested symbol set coded font.
3. The font-inline-sequence field of an LFE command is [IPDS-16-691]
invalid, unsupported, or is unsupported within the
current text orientation.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. Some printers report X'023F ..02' when an invalid or [IPDS-16-692]
unsupported font-inline-sequence value is specified in
an LFE command. The preferred exception ID for this
situation is X'0247..02'.
2. This corresponds to an exception code defined by [IPDS-16-693]
PTOCA.
3. Sense bytes 16–17 should contain the explanation [IPDS-16-694]
number of a specific cause for the error.
0240..02 Invalid or unsupported value for font inline [IPDS-16-695]
sequence
Action code: X'01'
Explanation: A font-inline sequence specified in an LFI or
DF command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0242..01 WIC command pel count is less than the [IPDS-16-696]
minimum required
Action code: X'01' or X'1F'
Explanation: The pels-per-scan-line value in a Write
Image Control command for either the input or output
image is less than X'0001'.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
0243..01 WIC command pel count is greater than [IPDS-16-697]
the maximum supported value
Action code: X'01' or X'1F'
023E..02 • 0243..01


Explanation: The pels-per-scan-line value in a Write
Image Control command for either the input or output
image is greater than the valid or supported maximum.
Alternate Exception Action: None
Page Continuation Action: Skip to the END command.
Support: Mandatory
0243..02 Invalid double-byte coded font section [IPDS-16-698]
identifier
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The section-identifier value in an LFC or LFI command [IPDS-16-699]
is nonzero for a single-byte coded font.
2. The section-identifier value in an LFC or LFI command [IPDS-16-700]
is not in the range X'41' through X'FE' for a double-
byte coded font.
3. The section-identifier value in an LFI command does [IPDS-16-701]
not match that of any previously received LFC
command for that font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0244..01 WIC command scan-line count is less than [IPDS-16-702]
the minimum required
Action code: X'01' or X'1F'
Explanation: The number-of-scan-lines value in a Write
Image Control command for either the input or the output
image is less than X'0001'.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
0244..02 Nonmatching double-byte coded font [IPDS-16-703]
sections
Action code: X'01'
Explanation: LFC or LFI parameters are not the same for
all sections as required by the command definition. Refer
to the introductory paragraphs for the LFC and LFI
commands for a description of the parameters that must be
identical for certain double-byte font sections.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0245..01 WIC command scan-line count is greater [IPDS-16-704]
than the maximum supported value
Action code: X'01' or X'1F'
Explanation: The number-of-scan-lines value in a Write
Image Control command for either the input or the output
image is greater than the valid or supported maximum.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
0246..01 Invalid WIC input image format [IPDS-16-705]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. Byte 8 of a WIC command is not X'00'. [IPDS-16-706]
2. The image-format value (byte 9) of a WIC command is [IPDS-16-707]
not X'00'.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0246..02 Invalid parameter in an LFI command [IPDS-16-708]
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. A short-form LFI is loaded when a long-form LFI is [IPDS-16-709]
expected.
2. A long-form LFI is loaded when a short-form LFI is [IPDS-16-710]
expected.
3. The font-inline sequence (bytes 4 and 5) matches that [IPDS-16-711]
of a currently loaded font index for the fully described
font or section.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0246..03 Invalid GRID value in an LFC command [IPDS-16-712]
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The GRID contains an invalid (out of range) value in [IPDS-16-713]
one or more of its components.
2. The printer requires a valid GRID, but one is not [IPDS-16-714]
supplied.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
0243..02 • 0246..03 [IPDS-16-715]


Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0247..01 Invalid or unsupported value for Write [IPDS-16-716]
Image Control magnification factor
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The pel-magnification factor in a WIC command is [IPDS-16-717]
invalid or unsupported.
2. The scan-line magnification factor in a WIC command [IPDS-16-718]
does not equal the pel-magnification factor.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0247..02 Invalid or unsupported value for Load Font [IPDS-16-719]
Equivalence font-inline sequence
Action code: X'01' or X'1F'
Explanation: The font-inline-sequence parameter in an
LFE command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: LFE when not in home state:
ignore remainder of command; entries received prior to
error take effect.
Support: Mandatory
Note: Some printers report X'023F ..02' when an invalid or
unsupported font-inline-sequence value is specified
in an LFE command. The preferred exception ID for
this situation is X'0247..02'.
0248..01 Invalid or unsupported value for Write [IPDS-16-720]
Image Control scan-line direction
Action code: X'01' or X'1F'
Explanation: The scan-line-direction parameter (bytes
12–13) in a Write Image Control command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to the END command.
Support: Mandatory
0248..02 Invalid or unsupported value for Load [IPDS-16-721]
Symbol Set section identifier
Action code: X'01'
Explanation: The section ID value specified in the LSS
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0249..01 Invalid scan-line-sequence direction in a [IPDS-16-722]
WIC command
Action code: X'01' or X'1F'
Explanation: The scan-line sequence-direction value
(bytes 14–15) specified in a Write Image Control command
is not +90° from the scan-line-direction value.
Alternate Exception Action: None
Page Continuation Action: Skip to the END command.
Support: Mandatory
0249..02 Invalid or unsupported value for Load [IPDS-16-723]
Symbol Set starting code point
Action code: X'01'
Explanation: The starting code point in the LSS
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
024A..01 Invalid or unsupported value for Write
Image Control output image location
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The reference coordinate system (byte 16) in a WIC [IPDS-16-724]
command is invalid or unsupported.
2. The first pel location X [IPDS-16-725]
p or I value (bytes 17–19) in a
WIC command is invalid or unsupported.
3. The first pel location Yp or B value (bytes 21–23) in a [IPDS-16-726]
WIC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
024A..02 Invalid or unsupported value for Load
Symbol Set ending code point
Action code: X'01'
Explanation: The ending code point specified in the LSS
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
024B..02 Invalid or unsupported bit value for Load
Symbol Set flag bytes
0247..01 • 024B..02 [IPDS-16-727]


Action code: X'01'
Explanation: One or more of the bits in the two flag bytes
of the LSS command are invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
024C..02 Invalid or unsupported value for Load
Symbol Set data length
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The length of the LSS additional parameter byte does [IPDS-16-728]
not correlate with the length of the LSS data.
2. The LSS self-identifying field length is an invalid or [IPDS-16-729]
unsupported value, or it does not correlate with the
LSS data length.
3. The amount of raster data in the LSS command does [IPDS-16-730]
not correspond with the number of code points, the
box size, and the pattern-download format.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
024D..02 Insufficient storage for font-control and
font-index records
Action code: X'0C'
Explanation: Insufficient storage to load the data
transmitted with the LFI and LFC commands.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
0253..01 Invalid or unsupported value for Write [IPDS-16-731]
Image Control image color
Action code: X'01' or X'1F'
Explanation: The image color in a WIC command is
invalid or unsupported.
Alternate Exception Action: Use a highlight color if one
is available, otherwise use the printer default color.
Page Continuation Action: Use a highlight color if one is
available, otherwise use the printer default color.
Support: Mandatory
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
0254..01 Invalid Color Fidelity (X'75') triplet length [IPDS-16-732]
value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a Color Fidelity (X'75') triplet [IPDS-16-733]
contains an invalid value.
2. A Color Fidelity (X'75') triplet is too long to fit in the [IPDS-16-734]
containing command.
The triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Color Fidelity (X'75') triplet
is supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0254..02 Invalid Color Fidelity (X'75') triplet [IPDS-16-735]
continue value
Action code: X'01'
Explanation: The continue field in a Color Fidelity (X'75')
triplet contains an invalid value. The triplet is contained in a
Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Color Fidelity (X'75') triplet
is supported
0254..03 Invalid Color Fidelity (X'75') triplet report [IPDS-16-736]
value
Action code: X'01'
Explanation: The report field in a Color Fidelity (X'75')
triplet contains an invalid value. The triplet is contained in a
Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Color Fidelity (X'75') triplet
is supported
0254..04 Invalid Color Fidelity (X'75') triplet [IPDS-16-737]
substitute value
Action code: X'01'
Explanation: The substitute field in a Color Fidelity (X'75')
triplet contains an invalid value. The triplet is contained in a
Presentation Fidelity Control command.
Alternate Exception Action: None
024C..02 • 0254..04


Page Continuation Action: None
Support: Mandatory when the Color Fidelity (X'75') triplet
is supported
0254..05 Invalid triplet information in a PFC [IPDS-16-738]
command
Action code: X'01'
Explanation: In a Presentation Fidelity Control command,
byte 4 or the first byte after a triplet is X'00' or X'01' (an
invalid triplet length).
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0254..31 Invalid Toner Saver (X'74') triplet length [IPDS-16-739]
value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a Toner Saver (X'74') triplet [IPDS-16-740]
contains an invalid value.
2. A Toner Saver (X'74') triplet is too long to fit in the [IPDS-16-741]
containing command.
The triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Toner Saver (X'74') triplet
is supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0254..32 Mismatched toner saver value for a saved [IPDS-16-742]
page
Action code: X'01'
Explanation: For performance reasons, some printers
that support saved pages rasterize each page before the
page is saved; in this case, when a Toner Saver (X'74')
triplet is in effect at save-page time, that toner saver setting
is used during the rasterizing process. Later, when a
previously saved page is included with an Include Saved
Page command, the Toner Saver (X'74') triplet in effect at
that time must contain the same setting; to print the saved
page correctly, the page must be rasterized using the toner
saver settings in effect at the time of presentation.
Alternate Exception Action: None
Page Continuation Action: Print the saved page
Support: Mandatory when the Toner Saver (X'74') triplet
is supported
0254..33 Invalid Toner Saver (X'74') triplet control [IPDS-16-743]
value
Action code: X'01'
Explanation: The toner saver control field in a Toner
Saver (X'74') triplet contains an invalid value. The triplet is
contains in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Toner Saver (X'74') triplet
is supported
0254..41 Invalid Finishing Fidelity (X'88') triplet [IPDS-16-744]
length value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a Finishing Fidelity (X'88') [IPDS-16-745]
triplet contains an invalid value.
2. A Finishing Fidelity (X'88') triplet is too long to fit in the [IPDS-16-746]
containing command.
The triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Finishing Fidelity (X'88')
triplet is supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0254..42 Invalid Finishing Fidelity (X'88') triplet [IPDS-16-747]
continue value
Action code: X'01'
Explanation: The continue field (byte 2) in a Finishing
Fidelity (X'88') triplet contains an invalid value. The triplet is
contained in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Finishing Fidelity (X'88')
triplet is supported
0254..43 Invalid Finishing Fidelity (X'88') triplet [IPDS-16-748]
report value
Action code: X'01'
Explanation: The report field (byte 4) in a Finishing
Fidelity (X'88') triplet contains an invalid value. The triplet is
contained in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Finishing Fidelity (X'88')
triplet is supported
0254..51 Invalid Text Fidelity (X'86') triplet length [IPDS-16-749]
value
0254..05 • 0254..51 [IPDS-16-750]


Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a Text Fidelity (X'86') triplet [IPDS-16-751]
contains an invalid value.
2. A Text Fidelity (X'86') triplet is too long to fit in the [IPDS-16-752]
containing command.
The triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Text Fidelity (X'86') triplet is
supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0254..52 Invalid Text Fidelity (X'86') triplet continue [IPDS-16-753]
value
Action code: X'01'
Explanation: The continuation-rule field (byte 2) in a Text
Fidelity (X'86') triplet contains an invalid value. The triplet is
contained in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Text Fidelity (X'86') triplet is
supported
0254..53 Invalid Text Fidelity (X'86') triplet report [IPDS-16-754]
value
Action code: X'01'
Explanation: The report field (byte 4) in a Text Fidelity
(X'86') triplet contains an invalid value. The triplet is
contained in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when the Text Fidelity (X'86') triplet is
supported
0254..71 Invalid CMR Tag Fidelity (X'96') triplet [IPDS-16-755]
length value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The triplet-length field in a CMR Tag Fidelity (X'96') [IPDS-16-756]
triplet contains an invalid value.
2. A CMR Tag Fidelity (X'96') triplet is too long to fit in the [IPDS-16-757]
containing command.
The triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, when the CMR Tag Fidelity (X'96')
triplet is supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0254..72 Invalid CMR Tag Fidelity (X'96') triplet [IPDS-16-758]
continue value
Action code: X'01'
Explanation: The continuation-rule field (byte 2) in a CMR
Tag Fidelity (X'96') triplet contains an invalid value. The
triplet is contained in a Presentation Fidelity Control
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, when the CMR Tag Fidelity (X'96')
triplet is supported
0254..73 Invalid CMR Tag Fidelity (X'96') triplet [IPDS-16-759]
report value
Action code: X'01'
Explanation: The report field (byte 4) in a CMR Tag
Fidelity (X'96') triplet contains an invalid value. The triplet is
contained in a Presentation Fidelity Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, when the CMR Tag Fidelity (X'96')
triplet is supported
0255..00 Page group already saved [IPDS-16-760]
Action code: X'01'
Explanation: A XOH-DGB command specifies a group
level that a previously received XOH-SGO command has
identified as a group to be saved. However, the printer
already has a saved group with that variable-length group
ID.
Alternate Exception Action: None
Page Continuation Action: The group is not saved.
Support: Mandatory
0255..01 Included page not previously saved [IPDS-16-761]
Action code: X'01'
Explanation: An Include Saved Page command attempts
to include a page that has not been previously saved. The
group is saved, but the group does not contain a page with
the page sequence number requested.
Alternate Exception Action: None
Page Continuation Action: The ISP command is
skipped.
Support: Mandatory
0254..52 • 0255..01 [IPDS-16-762]


0255..02 Invalid page sequence number in an ISP [IPDS-16-763]
command
Action code: X'01'
Explanation: The page sequence number field in an
Include Saved Page command contains an invalid value.
Alternate Exception Action: None
Page Continuation Action: The ISP command is
skipped.
Support: Mandatory
0255..03 Saved page group not found [IPDS-16-764]
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An Include Saved Page command attempts to include [IPDS-16-765]
a page from a group that has not been previously
saved.
2. An Include Saved Page command does not contain [IPDS-16-766]
the required Group ID (X'00') triplet.
Alternate Exception Action: None
Page Continuation Action: The ISP command is
skipped.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0255..04 Multiple ISP commands encountered [IPDS-16-767]
Action code: X'01'
Explanation: More than one Include Saved Page
command is specified in a page to be printed.
Alternate Exception Action: None
Page Continuation Action: Ignore all but the first Include
Saved Page command in the page to be printed.
Support: Mandatory
0255..05 Nested ISP command encountered [IPDS-16-768]
Action code: X'01'
Explanation: An Include Saved Page command is
encountered in a page to be saved. The page to be saved
is found in a group to which the Save Pages group
operation applies.
Alternate Exception Action: None
Page Continuation Action: Ignore the ISP command.
Support: Mandatory
0255..06 Included page not previously saved with [IPDS-16-769]
the specified text suppressions
Action code: X'01'
Explanation: An Include Saved Page command attempts
to include a page that has not been previously saved with
the appropriate text suppressions. The current LCC
command specifies a combination of text suppressions for
which a copy of the page has not been previously saved.
Alternate Exception Action: None
Page Continuation Action: The ISP command is
skipped.
Support: Mandatory
0255..07 Saved page group to be deleted was not [IPDS-16-770]
found
Action code: X'01'
Explanation: A XOH-DSPG command specifies the
Group ID of a saved page group to be deleted. However,
the group is not found.
Alternate Exception Action: Ignore the Group ID (X'00')
triplet and continue processing the XOH-DSPG command.
Page Continuation Action: None
Support: Mandatory
0255..08 Invalid triplet information in an XOH-DSPG [IPDS-16-771]
command
Action code: X'01'
Explanation: One or more of the following conditions
exists in the triplets field of an XOH Deactivate Saved Page
Group command:
1. Byte 2 or the first byte after a valid triplet is X'00' or [IPDS-16-772]
X'01' (an invalid triplet length).
2. A triplet other than a Group ID (X'00') triplet is [IPDS-16-773]
specified.
3. A Group ID (X'00') triplet without a variable-length [IPDS-16-774]
group ID is specified.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0255..09 Page too large to save [IPDS-16-775]
Action code: X'01'
Explanation: The logical-page-extent values specified in
the most recently received LPD command are too large for
a page to be saved. This exception is detected when
processing the Begin Page command for a page to be
saved.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0255..02 • 0255..09 [IPDS-16-776]


0255..0A Invalid triplet information in an XOH-RSPG
command
Action code: X'01'
Explanation: One or more of the following conditions
exists in the triplets field of an XOH Remove Saved Page
Group command:
1. Byte 2 or the first byte after a valid triplet is X'00' or [IPDS-16-777]
X'01' (an invalid triplet length).
2. A triplet other than a Group ID (X'00') triplet is [IPDS-16-778]
specified.
3. A Group ID (X'00') triplet without a variable-length [IPDS-16-779]
group ID is specified.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0255..0B Saved page group no longer usable
Action code: X'01'
Explanation: An Include Saved Page command
attempted to include a page, but printer characteristics
have changed such that they are inconsistent with the
characteristics used to save the page. One or more of the
following conditions exists:
1. Media-specific CMRs changed [IPDS-16-780]
2. The device resolution changed [IPDS-16-781]
3. Colorants used when the page was saved are no [IPDS-16-782]
longer available
Alternate Exception Action: None
Page Continuation Action: The ISP command is
skipped.
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0256..01 Invalid CPGID value in a CGCSGID (X'01') [IPDS-16-783]
triplet
Action code: X'01'
Explanation: An invalid Code Page Global ID (CPGID)
value is specified in a Coded Graphic Character Set Global
ID (X'01') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the CGCSGID (X'01') triplet is
supported
0256..11 Invalid TTC-font-index value in a Linked [IPDS-16-784]
Font (X'8D') triplet
Action code: X'01'
Explanation: An index value is specified in a Linked Font
(X'8D') triplet for a TrueType/OpenType Collection, but
there is no TrueType/OpenType Font in the collection for
this index value. The triplet is specified on an AR
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
Note: The HAID value returned in sense bytes 14–15 is the
HAID of the linked font with the invalid TTC-font-
index value.
0256..12 Invalid HAID value in a Linked Font (X'8D') [IPDS-16-785]
triplet
Action code: X'01'
Explanation: An invalid HAID value is specified in a
Linked Font (X'8D') triplet. The triplet is specified on an AR
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
0256..13 Invalid font-ID-type value in a Linked Font [IPDS-16-786]
(X'8D') triplet
Action code: X'01'
Explanation: An invalid font-ID-type value is specified in a
Linked Font (X'8D') triplet. The triplet is specified on an AR
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
0256..14 Invalid full-font-name value in a Linked [IPDS-16-787]
Font (X'8D') triplet
Action code: X'01'
Explanation: A full-font-name value is specified in a
Linked Font (X'8D') triplet for a TrueType/OpenType
Collection, but there is no TrueType/OpenType Font in the
collection for this full font name. The triplet is specified on
an AR command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
Note: The HAID value returned in sense bytes 14–15 is the
HAID of the linked font with the invalid full-font-name
value.
0255..0A • 0256..14


0256..21 Invalid FQN type value in a Fully Qualified [IPDS-16-788]
Name (X'02') triplet
Action code: X'01'
Explanation: An invalid FQN type value is specified in a
Fully Qualified Name (X'02') triplet. The triplet is specified
on an AR or a WOCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
0256..22 Invalid FQN format value in a Fully [IPDS-16-789]
Qualified Name (X'02') triplet
Action code: X'01'
Explanation: An invalid FQN format value is specified in a
Fully Qualified Name (X'02') triplet. The triplet is specified
on an AR or a WOCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
0256..23 Mismatched object OIDs found while [IPDS-16-790]
attempting to capture a resource
Action code: X'01'
Explanation: When a resource is being captured using an
OID, two object OIDs can be specified. The first is specified
in the AR command that authorized the capture. The
second OID can optionally be specified in the command
that downloads the resource. These two object OIDs must
be identical. Resources that can be captured using an OID
include:
1. CMR objects, whose OID is specified in a Fully [IPDS-16-791]
Qualified Name (X'02') triplet (with FQN Type X'41')
within the WOCC command that downloads the object.
2. TrueType/OpenType objects, whose OID is specified [IPDS-16-792]
in a Fully Qualified Name (X'02') triplet (with FQN Type
X'DE') within the WOCC command that downloads the
object.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if resource capture via OID is
supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0256..24 Invalid FQN in a Fully Qualified Name [IPDS-16-793]
(X'02') triplet
Action code: X'01'
Explanation: One of the following conditions exists in a
Fully Qualified Name (X'02') triplet:
1. The FQN Format value is X'00' and the FQN value is [IPDS-16-794]
not a valid character-encoded string. When the FQN is
for a TrueType/OpenType font, the string must be
encoded with UTF-16BE. When the FQN is for an
object name, the string must be encoded with UTF-
16BE unless there is a preceding Coded Graphic
Character Set Global Identifier (X'01') triplet.
2. The FQN Format value is X'10' and the FQN value is [IPDS-16-795]
not a valid ASN.1 definite-short-form OID.
The triplet is specified on an AR or a WOCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Bytes 16–17 should contain the explanation number
of a specific cause for the error.
0256..31 Invalid or unsupported encoding-scheme- [IPDS-16-796]
ID value in an Encoding Scheme ID (X'50')
triplet
Action code: X'01'
Explanation: An invalid or unsupported encoding-
scheme-ID value is specified in an Encoding Scheme ID
(X'50') triplet. The triplet is specified on an AR command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
0256..51 Invalid processing-mode value in a Color [IPDS-16-797]
Management Resource Descriptor (X'91')
triplet
Action code: X'01'
Explanation: An invalid processing-mode value is
specified in a Color Management Resource Descriptor
(X'91') triplet. The triplet is specified on an AR command or
on a home-state WOCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if CMRs are supported
0256..61 Invalid HAID value in an Invoke CMR (X'92') [IPDS-16-798]
triplet
Action code: X'01' or X'1F'
Explanation: An Invalid HAID value is specified in an
Invoke CMR (X'92') triplet. The triplet is specified on an
IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, or WTC
command.
Alternate Exception Action: None
Page Continuation Action: None
0256..21 • 0256..61 [IPDS-16-799]


Support: Mandatory, if CMRs are supported
0256..71 Invalid rendering-intent value in a [IPDS-16-800]
Rendering Intent (X'95') triplet
Action code: X'01' or X'1F'
Explanation: An invalid rendering-intent value is specified
in a Rendering Intent (X'95') triplet. The triplet is specified
on an IDO, LPD, RPO, SPE, WGC, WIC2, WOCC, or WTC
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if CMRs are supported
0256..81 Invalid or unsupported appearance value [IPDS-16-801]
in a Device Appearance (X'97') triplet
Action code: X'01'
Explanation: An invalid or unsupported appearance value
is specified in a Device Appearance (X'97') triplet. The
triplet is specified on a Set Presentation Environment
(SPE) command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the Device Appearance (X'97')
triplet is supported
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
0256..91 Invalid or unsupported unit base value in [IPDS-16-802]
an Image Resolution (X'9A') triplet
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The X unit base field in an Image Resolution (X'9A') [IPDS-16-803]
triplet contained an invalid or unsupported value.
2. The Y unit base field in an Image Resolution (X'9A') [IPDS-16-804]
triplet contained an invalid or unsupported value.
3. The Y unit base in an Image Resolution (X'9A') triplet [IPDS-16-805]
was not identical to the X unit base.
The Image Resolution (X'9A') triplet was specified in an
IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0256..92 Invalid units per unit base value in an [IPDS-16-806]
Image Resolution (X'9A') triplet
Action code: X'01' or X'1F'
Explanation: The X units per unit base or Y units per unit
base field in an Image Resolution (X'9A') triplet contained
an invalid value.
The Image Resolution (X'9A') triplet was specified in an
IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Optional
0256..A1 Invalid object-type value in an Object
Offset (X'5A') triplet
Action code: X'01' or X'1F'
Explanation: The object-type field in an Object Offset
(X'5A') triplet contained an invalid value. The Object Offset
(X'5A') triplet was specified in an IDO, RPO, or WOCC
command.
Alternate Exception Action: Proceed as if X'AF' had
been specified
Page Continuation Action: Proceed as if X'AF' had been
specified
Support: Mandatory if the Object Offset (X'5A') triplet is
supported.
0256..A2 Selected paginated object not in file
Action code: X'01' or X'1F'
Explanation: An Object Offset (X'5A') triplet specified an
object offset, but there is no paginated object in the multi-
page file at that offset. The Object Offset (X'5A') triplet was
specified in an IDO, RPO, or WOCC command.
Alternate Exception Action: Skip this IDO entry, RPO
entry, or WOCC/WOC/END command sequence.
Page Continuation Action: Skip this IDO entry, RPO
entry, or WOCC/WOC/END command sequence.
Support: Mandatory if the Object Offset (X'5A') triplet is
supported.
0256..B1 Invalid PDF-presentation-space-size value
in an Object Container Presentation Space
Size (X'9C') triplet
Action code: X'01' or X'1F'
Explanation: The PDF-presentation-space-size field in an
Object Container Presentation Space Size (X'9C') triplet
contains an invalid value.
The Object Container Presentation Space Size (X'9C')
triplet was specified in an IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Mandatory if the Object Container Presentation
Space Size (X'9C') triplet is supported for PDF objects.
0256..71 • 0256..B1 [IPDS-16-807]


0256..B2 Invalid or unsupported unit base value in
an Object Container Presentation Space
Size (X'9C') triplet
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The X unit base field in an Object Container [IPDS-16-808]
Presentation Space Size (X'9C') triplet contained an
invalid or unsupported value.
2. The Y unit base field in an Object Container [IPDS-16-809]
Presentation Space Size (X'9C') triplet contained an
invalid or unsupported value.
3. The Y unit base in an Object Container Presentation [IPDS-16-810]
Space Size (X'9C') triplet was not identical to the X unit
base.
The Object Container Presentation Space Size (X'9C')
triplet was specified in an IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Mandatory if the Object Container Presentation
Space Size (X'9C') triplet is supported for SVG objects.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0256..B3 Invalid units per unit base value in an
Object Container Presentation Space Size
(X'9C') triplet
Action code: X'01' or X'1F'
Explanation: The X units per unit base or Y units per unit
base field in an Object Container Presentation Space Size
(X'9C') triplet contained an invalid value.
The Object Container Presentation Space Size (X'9C')
triplet was specified in an IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Mandatory if the Object Container Presentation
Space Size (X'9C') triplet is supported for SVG objects.
0256..B4 Invalid or unsupported extent value in an
Object Container Presentation Space Size
(X'9C') triplet
Action code: X'01' or X'1F'
Explanation: The X
oc extent field or Yoc extent field in an
Object Container Presentation Space Size (X'9C') triplet
contained an invalid or unsupported value.
The Object Container Presentation Space Size (X'9C')
triplet was specified in an IDO, RPO, or WOCC command.
Alternate Exception Action: Ignore the triplet
Page Continuation Action: Ignore the triplet
Support: Mandatory if the Object Container Presentation
Space Size (X'9C') triplet is supported for SVG objects.
0256..C1 Invalid TRType value in an Invoke Tertiary
Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: An invalid or unsupported tertiary resource
type value is specified in an Invoke Tertiary Resource
(X'A2') triplet. The triplet is specified on a WBCC
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
0256..C2 Invalid HAID value in an Invoke Tertiary
Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: An invalid HAID value is specified in an
Invoke Tertiary Resource (X'A2') triplet. The triplet is
specified on a WBCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
0256..C3 Invalid tertiary resource invoked in an
Invoke Tertiary Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. The Host-Assigned ID specified in an Invoke Tertiary [IPDS-16-811]
Resource (X'A2') triplet is not currently activated.
2. The Host-Assigned ID specified in an Invoke Tertiary [IPDS-16-812]
Resource (X'A2') triplet is activated, but is not of the type
named in the TRType field of the triplet.
The triplet is specified on a WBCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0256..C4 Invalid IDType value in an Invoke Tertiary
Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: An invalid or unsupported internal resource
ID type value is specified in an Invoke Tertiary Resource
(X'A2') triplet. The triplet is specified on a WBCC
command.
Alternate Exception Action: None
0256..B2 • 0256..C4


Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
0256..C5 Invalid internal resource ID length in an
Invoke Tertiary Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: The internal resource ID specified in an
Invoke Tertiary Resource (X'A2') triplet is not the length
defined by the IDType field of the triplet. The triplet is
specified on a WBCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
0256..C6 Invalid secondary resource mapped in an
Invoke Tertiary Resource (X'A2') triplet
Action code: X'01' or X'1F'
Explanation: The secondary resource object mapped by
the internal resource ID specified in an Invoke Tertiary
Resource (X'A2') triplet is not a valid type for the current
context. The triplet is specified on a WBCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Invoke Tertiary Resource
(X'A2') triplet is supported.
0257..01 Invalid RPO entry-length value [IPDS-16-813]
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An invalid entry-length value is specified in a Rasterize [IPDS-16-814]
Presentation Object (RPO) command.
2. A RPO entry is too long to fit into the RPO command. [IPDS-16-815]
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0257..02 Invalid RPO resource type [IPDS-16-816]
Action code: X'01'
Explanation: One of the following conditions exists:
1. An invalid resource type value is specified in a [IPDS-16-817]
Rasterize Presentation Object (RPO) command.
2. The RPO entry specified a data object resource, but [IPDS-16-818]
the object-type OID in the resource is not valid for the
RPO command.
The RPO command can be used to rasterize any of
the following presentation objects:
• EPS (Encapsulated PostScript) with transparency [IPDS-16-819]
• EPS without transparency [IPDS-16-820]
• GIF (Graphics Interchange Format) [IPDS-16-821]
• IOCA (Image Object Content Architecture) image [IPDS-16-822]
• JPEG (Joint Photographic Experts Group) AFPC [IPDS-16-823]
JPEG Subset
• JP2 (JPEG2000 File Format) [IPDS-16-824]
• Overlay [IPDS-16-825]
• PCL (Printer Command Language) page object [IPDS-16-826]
• PDF (Portable Document Format) multiple-page file [IPDS-16-827]
with transparency
• PDF multiple-page file without transparency [IPDS-16-828]
• PDF single page with transparency [IPDS-16-829]
• PDF single page without transparency [IPDS-16-830]
• PNG (Portable Network Graphics) AFPC PNG [IPDS-16-831]
Subset
• SVG (Scalable Vector Graphics) AFPC SVG Subset [IPDS-16-832]
• TIFF (Tag Image File Format) AFPC TIFF Subset [IPDS-16-833]
• TIFF with transparency [IPDS-16-834]
• TIFF without transparency [IPDS-16-835]
• TIFF multiple-image file with transparency [IPDS-16-836]
• TIFF multiple-image file without transparency [IPDS-16-837]
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0257..03 Invalid or unsupported RPO HAID value [IPDS-16-838]
Action code: X'01'
Explanation: One of the following conditions exists:
1. An invalid HAID value is specified in a Rasterize [IPDS-16-839]
Presentation Object (RPO) command.
2. An overlay HAID value in the range X'00FF'–X'7EFF' is [IPDS-16-840]
specified in a Rasterize Presentation Object (RPO)
command, but the printer does not provide extended
overlay support.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0257..04 Resource not activated for RPO command [IPDS-16-841]
Action code: X'01'
Explanation: The data object resource or overlay
identified by the HAID parameter of an RPO command has
not been activated or has been deactivated before its
current use.
Alternate Exception Action: None
0256..C5 • 0257..04


Page Continuation Action: None
Support: Mandatory
0257..05 Invalid or unsupported RPO unit-base [IPDS-16-842]
value
Action code: X'01'
Explanation: An invalid or unsupported unit-base value is
specified in a Rasterize Presentation Object (RPO)
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0257..06 Invalid or unsupported RPO units-per-unit- [IPDS-16-843]
base value
Action code: X'01'
Explanation: An invalid or unsupported units-per-unit-
base value is specified in a Rasterize Presentation Object
(RPO) command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0257..07 Invalid or unsupported RPO object-area- [IPDS-16-844]
extent value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An invalid or unsupported X [IPDS-16-845]
oa-extent value is specified
in a Rasterize Presentation Object (RPO) command.
2. An invalid or unsupported Yoa-extent value is specified [IPDS-16-846]
in a Rasterize Presentation Object (RPO) command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0257..08 Invalid or unsupported RPO mapping- [IPDS-16-847]
control-option value
Action code: X'01'
Explanation: One of the following conditions exists:
1. An invalid or unsupported mapping-control-option [IPDS-16-848]
value is specified in a Rasterize Presentation Object
(RPO) command.
2. The position mapping is specified for an IOCA object. [IPDS-16-849]
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0257..09 Unsupported RPO object-area-offset value [IPDS-16-850]
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. A negative X [IPDS-16-851]
oa-offset or Yoa-offset value is specified in
a Rasterize Presentation Object (RPO) command, but
the printer does not support negative offsets.
2. A valid Xoa-offset or Yoa-offset value is specified in a [IPDS-16-852]
Rasterize Presentation Object (RPO) command, but
the printer does not support that value with the
specified units of measure.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0258..03 Invalid or unsupported value for text color [IPDS-16-853]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The color field in the Set Text Color (STC) control [IPDS-16-854]
sequence is invalid or is unsupported.
2. The text-color field in an LPD or WTC command is [IPDS-16-855]
invalid or unsupported.
3. The precision field in the Set Text Color (STC) control [IPDS-16-856]
sequence is invalid or is unsupported.
Alternate Exception Action: For an invalid or
unsupported color value, use a highlight color if one is
available, otherwise use the printer default color. For an
invalid or unsupported precision value, use precision X'00'.
Page Continuation Action: For an invalid or unsupported
color value, use a highlight color if one is available,
otherwise use the printer default color. For an invalid or
unsupported precision value, use precision X'00'.
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-857]
PTOCA.
2. For printers that support color fidelity control, reporting [IPDS-16-858]
of this exception for invalid or unsupported color
values can be controlled by the Color Fidelity (X'75')
triplet in the PFC command.
3. The STC precision parameter has been retired in the [IPDS-16-859]
PTOCA Architecture and should be ignored by all
IPDS printers.
4. Sense bytes 16–17 should contain the explanation [IPDS-16-860]
number of a specific cause for the error.
0257..05 • 0258..03 [IPDS-16-861]


025B..01 Invalid type value in a MID command
Action code: X'01'
Explanation: The type parameter in a Manage IPDS
Dialog command contains an invalid value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
025C..02 Invalid or unsupported parameter in a DUA
command
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The Reset parameter or the X [IPDS-16-862]
m or Ym extent of the
UPA parameter is invalid.
2. The Unit Base parameter, the Units per Unit Base [IPDS-16-863]
parameter, or the Xm or Ym coordinate of the UPA
Origin parameter is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
025D..ee Color Management Resource object error
Action code: X'01' or X'1F'
Explanation: An error has been detected within a Color
Management Resource (CMR). The specific error code
(X'ee') is defined within the Color Management Object
Content Architecture Reference; X'ee' can be one of the
following:
X'03' Invalid Length value in a CMR header
X'04' Unsupported TagID value in a CMR tag
X'05' Invalid Count value in a CMR tag
X'06' Invalid FieldType value in a CMR tag
X'0E' Missing required CMR tag
X'0F' Invalid sequence of CMR tags
X'10' Invalid or unsupported field value in a CMR
header or CMR tag
X'11' Inconsistent CMR tag contents
X'12' Incorrect order of repeating groups
X'13' Duplicate value
Alternate Exception Action: None
Page Continuation Action: For audit, instruction, and
ICC DeviceLink CMRs, use a device-default CMR in place
of the CMR in error. For link color-conversion (subset “LK”)
CMRs, rebuild the needed link LUT .
Support: Mandatory, if CMRs are supported
Notes:
1. Sense bytes 16–17 contain the CMR TagID value for [IPDS-16-864]
the tag that caused the error.
2. For printers that support color fidelity control, reporting [IPDS-16-865]
of this exception (except for X'025D..04') is controlled
by the Color Fidelity (X'75') triplet in the PFC
command.
3. For printers that support CMR tag fidelity control, [IPDS-16-866]
reporting of X'025D..04' is controlled by the CMR Tag
Fidelity (X'96') triplet in the PFC command.
4. This corresponds to an exception code defined by [IPDS-16-867]
CMOCA as identified by the X'ee' value.
025E..00 Color Management Resource not available
Action code: X'01' or X'1F'
Explanation: A CMR is needed to process specific print
data, but is unavailable because the printer does not have
a default CMR for that type of color data and a host-
invoked CMR is also not supplied. The device-default
CMRs defined in the Color Management Object Content
Architecture Reference must be supported by the printer,
but defaults for other color spaces are optional and host-
invoked CMR must be supplied.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if CMRs are supported
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
025E..01 Required Color Management Resource
Descriptor (X'91') triplet missing
Action code: X'01'
Explanation: An AR or WOCC command to activate a
Color Management Resource is encountered, but a
required triplet is not specified in the activation command.
Both commands require a Color Management Resource
Descriptor (X'91') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if CMRs are supported
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
025E..02 Inappropriate processing mode for a CMR
Action code: X'01'
Explanation: An AR or WOCC command to activate a
Color Management Resource (CMR) contains a Color
Management Resource Descriptor (X'91') triplet that
specified an inappropriate processing mode for that CMR.
Refer to the Color Management Object Content
025B..01 • 025E..02


Architecture Reference for a description of appropriate
processing modes for the various types of CMRs.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if CMRs are supported
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
025E..03 Invoked, media-specific CMR does not
match selected media
Action code: X'01' or X'1F'
Explanation: An invoked, media-specific instruction or
ICC DeviceLink CMR is selected but does not match the
currently selected media. Refer to the Color Management
Object Content Architecture Reference for matching rules.
Alternate Exception Action: Use an appropriate printer-
default CMR.
Page Continuation Action: Use an appropriate printer-
default CMR.
Support: Mandatory, if CMRs are supported
Note: For printers that support color fidelity control,
reporting of this exception can be controlled by the
Color Fidelity (X'75') triplet in the PFC command.
025E..04 Generic CMR not supported
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A generic CMR is selected, but the printer does not [IPDS-16-868]
have an appropriate device-specific CMR to use in
place of the generic CMR.
2. A generic CMR is downloaded that the printer is [IPDS-16-869]
unable to map to a device-specific CMR.
Alternate Exception Action: Use an appropriate printer-
default CMR.
Page Continuation Action: Use an appropriate printer-
default CMR.
Support: Mandatory, if CMRs are supported
Notes:
1. For printers that support color fidelity control, reporting [IPDS-16-870]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-871]
number of a specific cause for the error.
025E..05 Invoked, selected CMR was not used
Action code: X'01' or X'1F'
Explanation: A CMR was invoked and selected for use,
but cannot be used. The selected CMR is valid, but is not
appropriate for one of the following reasons:
1. A printer-operator control has been invoked to achieve [IPDS-16-872]
a specific look that overrides a user-specified tone-
transfer-curve CMR.
2. A selected halftone CMR is not appropriate for the [IPDS-16-873]
printer; for example, a threshold-array halftone is
invoked and selected for use with an ink jet printer or
an error-diffusion halftone is invoked and selected for
use with a laser printer.
3. A halftone (or tone-transfer-curve) CMR has been [IPDS-16-874]
applied to a portion of a sheet, but a subsequent
halftone (or tone-transfer-curve) CMR is not supported
because the printer can only allow one halftone (or
tone-transfer-curve) CMR per sheet. For instance, this
can occur on printers that apply the halftone (or tone-
transfer-curve) CMR in hardware or in software after a
complete sheet is created.
4. A limited number of halftone or tone-transfer-curve [IPDS-16-875]
CMRs are supported on a sheet. For example, when
data overlaps only one halftone can be applied to the
overlapping area.
Alternate Exception Action: Use an appropriate printer-
default CMR.
Page Continuation Action: Use an appropriate printer-
default CMR.
Support: Mandatory, if CMRs are supported
Notes:
1. For printers that support color fidelity control, reporting [IPDS-16-876]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-877]
number of a specific cause for the error.
025F ..01 Invalid trace-function value
Action code: X'01'
Explanation: An invalid trace-function value is specified in
an XOH Trace command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the XOH Trace command is
supported
025F ..02 Invalid trace-option value
Action code: X'01'
Explanation: An invalid trace-option value is specified in
an XOH Trace command.
Alternate Exception Action: None
Page Continuation Action: None
025E..03 • 025F ..02


Support: Mandatory, if the XOH Trace command is
supported
0260..02 Invalid or unsupported value for Logical [IPDS-16-878]
Page Descriptor units per unit base (Xp
and I)
Action code: X'01'
Explanation: In an LPD command, the units-per-unit base
value (Xp or I direction) is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0261..02 Invalid or unsupported value for Logical [IPDS-16-879]
Page Descriptor units per unit base (Yp and
B)
Action code: X'01'
Explanation: In an LPD command, the units-per-unit base
value (Yp or B direction) does not match the value in the X
direction.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0262..02 Invalid or unsupported value for LPD Xp [IPDS-16-880]
extent or XOH-SMS Xm extent
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The X
p extent in a Logical Page Descriptor command
is invalid or unsupported.
2. The Xm extent in an XOH Set Media Size command is [IPDS-16-881]
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. X'0272..02' is used by some printers for this exception [IPDS-16-882]
in an XOH-SMS command. The preferred Exception
ID in this case is X'0272..02'.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-883]
number of a specific cause for the error.
0263..01 Insufficient pattern storage [IPDS-16-884]
Action code: X'0C'
Explanation: There is insufficient pattern storage to hold
the data transmitted with a WI, WI2, or WG command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
0263..02 Invalid or unsupported value for LPD Y [IPDS-16-885]
p
extent or XOH-SMS Ym extent
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The Yp extent in a Logical Page Descriptor command [IPDS-16-886]
is invalid or unsupported.
2. The Ym extent in an XOH Set Media Size command is [IPDS-16-887]
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. X'0273..02' is used by some printers for this exception [IPDS-16-888]
in an XOH-SMS command. The preferred Exception
ID in this case is X'0273..02'.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-889]
number of a specific cause for the error.
0264..01 Insufficient control storage [IPDS-16-890]
Action code: X'0C'
Explanation: Insufficient control storage to hold the data
transmitted with a WI, WI2, WG, or WGC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
0264..02 Invalid or unsupported value for Logical [IPDS-16-891]
Page Descriptor unit base
Action code: X'01'
Explanation: The unit-base field in an LPD command is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0260..02 • 0264..02 [IPDS-16-892]


0268..02 Invalid or unsupported value for LPD or [IPDS-16-893]
WTC inline-sequence direction
Action code: X'01'
Explanation: The inline-sequence-direction value in an
LPD or WTC command is invalid or unsupported.
Alternate Exception Action: An inline-sequence
direction of 0° (X'0000') and a baseline-sequence direction
of 90° (X'2D00') are used.
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
0269..02 Invalid baseline-sequence direction in the [IPDS-16-894]
LPD or WTC command
Action code: X'01'
Explanation: The baseline-sequence-direction value in an
LPD or WTC command is not valid when taken in
combination with the inline-sequence-direction value.
Alternate Exception Action: An inline-sequence
direction of 0° (X'0000') and a baseline-sequence direction
of 90° (X'2D00') are used.
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
026A..01 Insufficient input image data
Action code: X'01' or X'1F'
Explanation: The number of input image bytes received is
less than the number implied in a WIC command.
Alternate Exception Action: None
Page Continuation Action: Continue processing; print all
of the image that is received; bit fill the rest with zeros.
Support: Mandatory
026A..02 Invalid or unsupported value for LPD or
WTC initial I print coordinate
Action code: X'01'
Explanation: The initial inline-coordinate value in an LPD
or WTC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
026B..01 Excess input image data received
Action code: X'01' or X'1F'
Explanation: The number of input image bytes received is
greater than the number implied in a WIC command.
Alternate Exception Action: None
Page Continuation Action: Skip to END command.
Support: Mandatory
026B..02 Invalid or unsupported value for LPD or
WTC initial B print coordinate
Action code: X'01'
Explanation: The initial baseline-coordinate value in an
LPD or WTC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: This corresponds to an exception code defined by
PTOCA.
026E..01 Invalid or unsupported value in an XOH-
SMM command
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An entry length value specified in an XOH-SMM [IPDS-16-895]
command is invalid or unsupported.
2. An entry type value specified in an XOH-SMM [IPDS-16-896]
command is invalid.
3. A medium modification ID value specified in an XOH- [IPDS-16-897]
SMM command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
026F ..02 Invalid media-origin parameter specified in
an XOH-SMO command
Action code: X'01'
Explanation: The media-origin parameter specified in an
XOH Set Media Origin command is invalid.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0270..02 Invalid or unsupported value for XOH Set [IPDS-16-898]
Media Size units per unit base
Action code: X'01'
Explanation: The units-per-unit-base value in an XOH-
SMS command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
0268..02 • 0270..02 [IPDS-16-899]


Support: Mandatory
0272..02 Invalid or unsupported value for XOH Set [IPDS-16-900]
Media Size Xm extent
Action code: X'01'
Explanation: In an XOH-SMS command, the Xm extent is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: X'0262..02' is used by some printers for this
exception. The preferred Exception ID is X'0272..02'.
0273..02 Invalid or unsupported value for XOH Set [IPDS-16-901]
Media Size Ym extent
Action code: X'01'
Explanation: In an XOH-SMS command, the Ym extent is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: X'0263..02' is used by some printers for this
exception. The preferred Exception ID is X'0273..02'.
0274..02 Invalid or unsupported value for XOH Set [IPDS-16-902]
Media Size unit base
Action code: X'01'
Explanation: In an XOH-SMS command, the unit-base
value is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0277..01 Group termination exception [IPDS-16-903]
Action code: X'01'
Explanation: A XOH-DGB command is received that
attempts to terminate a group that is not yet initiated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0278..01 Invalid or unsupported order type [IPDS-16-904]
Action code: X'01'
Explanation: The order type (byte 2) specified in an XOH
Define Group Boundary command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
027A..01 Invalid triplet length value
Action code: X'01'
Explanation: The length specified in a triplet is invalid.
The triplet is one of the following:
1. A Group ID (X'00') triplet in an ISP , XOH-DGB, XOH- [IPDS-16-905]
DSPG, or XOA-RRL command
2. A Coded Graphic Character Set Global Identifier [IPDS-16-906]
(X'01') triplet in an AR or XOH-DGB command
3. A Fully Qualified Name (X'02') triplet in an AR or [IPDS-16-907]
WOCC command
4. An Encoding Scheme ID (X'50') triplet in an AR [IPDS-16-908]
command
5. An Object Offset (X'5A') triplet in an IDO, RPO, or [IPDS-16-909]
WOCC command
6. A Group Information (X'6E') triplet in an XOH-DGB [IPDS-16-910]
command
7. A Finishing Operation (X'85') triplet or a UP [IPDS-16-911]
3I Finishing
Operation (X'8E') triplet in an AFO or XOH-DGB
command
8. A Data Object Font Descriptor (X'8B') triplet in an AR [IPDS-16-912]
command
9. A Linked Font (X'8D') triplet in an AR command [IPDS-16-913]
10. A Color Management Resource Descriptor (X'91') [IPDS-16-914]
triplet in an AR or WOCC command
11. An Invoke CMR (X'92') triplet in an IDO, LPD, RPO, [IPDS-16-915]
WBCC, WGC, WIC2, WOCC, or WTC command
12. A Rendering Intent (X'95') triplet in an IDO, LPD, RPO, [IPDS-16-916]
SPE, WGC, WIC2, WOCC, or WTC command
13. A Device Appearance (X'97') triplet in an SPE [IPDS-16-917]
command
14. An Image Resolution (X'9A') triplet in an IDO, RPO, or [IPDS-16-918]
WOCC command
15. An Object Container Presentation Space Size (X'9C') [IPDS-16-919]
triplet in an IDO, RPO, or WOCC command
16. A Setup Name (X'9E') triplet in an ASN or XOA-RSNL [IPDS-16-920]
command
17. An Invoke Tertiary Resource (X'A2') triplet in a WBCC [IPDS-16-921]
command
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
027B..01 Incorrect number of triplet data bytes
Action code: X'01'
Explanation: One of the following conditions exists:
0272..02 • 027B..01 [IPDS-16-922]


• The number of data bytes specified in a triplet length field [IPDS-16-923]
is greater than the number of bytes remaining in the
command.
• The number of data bytes specified in a triplet length field [IPDS-16-924]
is different from the number of bytes in the Resource ID
field (value in Length field minus 3) of an XOA-RRL
command.
The triplet is one of the following:
1. A Group ID (X'00') triplet in an ISP , XOH-DGB, XOH- [IPDS-16-925]
DSPG, or XOA-RRL command
2. A Coded Graphic Character Set Global Identifier [IPDS-16-926]
(X'01') triplet in an AR or XOH-DGB command
3. A Fully Qualified Name (X'02') triplet in an AR or [IPDS-16-927]
WOCC command
4. An Encoding Scheme ID (X'50') triplet in an AR [IPDS-16-928]
command
5. An Object Offset (X'5A') triplet in an IDO, RPO, or [IPDS-16-929]
WOCC command
6. A Group Information (X'6E') triplet in an XOH-DGB [IPDS-16-930]
command
7. A Finishing Operation (X'85') triplet or a UP 3I Finishing [IPDS-16-931]
Operation (X'8E') triplet in an AFO or XOH-DGB
command
8. A Data Object Font Descriptor (X'8B') triplet in an AR [IPDS-16-932]
command
9. A Linked Font (X'8D') triplet in an AR command [IPDS-16-933]
10. A Color Management Resource Descriptor (X'91') [IPDS-16-934]
triplet in an AR or WOCC command
11. An Invoke CMR (X'92') triplet in an IDO, LPD, RPO, [IPDS-16-935]
WBCC, WGC, WIC2, WOCC, or WTC command
12. A Rendering Intent (X'95') triplet in an IDO, LPD, RPO, [IPDS-16-936]
SPE, WGC, WIC2, WOCC, or WTC command
13. A Device Appearance (X'97') triplet in an SPE [IPDS-16-937]
command
14. An Image Resolution (X'9A') triplet in an IDO, RPO, or [IPDS-16-938]
WOCC command
15. An Object Container Presentation Space Size (X'9C') [IPDS-16-939]
triplet in an IDO, RPO, or WOCC command
16. A Setup Name (X'9E') triplet in an ASN or XOA-RSNL [IPDS-16-940]
command
17. An Invoke Tertiary Resource (X'A2') triplet in a WBCC [IPDS-16-941]
command
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
027C..01 Incompatible finishing operations
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. An AFO or XOH-DGB command contains two or more [IPDS-16-942]
Finishing Operation (X'85') triplets that specify
incompatible finishing operations.
2. A pair of nested XOH-DGB commands contains two or [IPDS-16-943]
more Finishing Operation (X'85') triplets that specify
incompatible finishing operations.
The last received command containing the incompatible
triplet is discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. For printers that support finishing fidelity control, [IPDS-16-944]
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-945]
number of a specific cause for the error.
027C..01 Incompatible finishing operations
Action code: X'06'
Explanation: One or more of the following conditions
exists:
1. A Finishing Operation (X'85') triplet specified on an [IPDS-16-946]
AFO command is incompatible with a group finishing
triplet specified on a previous XOH-DGB command.
2. A Finishing Operation (X'85') triplet specified on an [IPDS-16-947]
XOH-DGB command is incompatible with a Finishing
Operation (X'85') triplet specified on a previous AFO
command.
3. An AFO or XOH-DGB command contains two or more [IPDS-16-948]
Finishing Operation (X'85') triplets that specify
incompatible finishing operations.
4. A pair of nested XOH-DGB commands contains two or [IPDS-16-949]
more Finishing Operation (X'85') triplets that specify
incompatible finishing operations.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The last received (incompatible) finishing
operation is not applied to the sheet and the Finishing
Operation (X'85') triplet is discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Notes:
1. For printers that support finishing fidelity control, [IPDS-16-950]
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..01 • 027C..01


2. Sense bytes 16–17 should contain the explanation [IPDS-16-951]
number of a specific cause for the error.
027C..02 Too many or too few sheets for a finishing
operation
Action code: X'06'
Explanation: A finishing operation is requested for a
collection of sheets, but the number of sheets is too large
or too small for the operation. This exception is not
reported for a group that has no printed pages (an empty
group). This exception is detected while processing the
XOH-DGB command to terminate the corresponding page
group. The group is not terminated, the Finishing
Operation (X'85') triplet is discarded, and the finishing
operation is not applied (or the finishing operation is
incompletely applied).
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..02 Too many sheets for a finishing operation
Action code: X'09'
Explanation: A finishing operation is requested for a
collection of sheets, but the number of sheets is too large
for the operation. This exception is detected
asynchronously when an IPDS command that ends a
sheet to be finished is processed. The pages for previous
sheets are committed and the pages for the error sheet are
discarded. The Finishing Operation (X'85') triplet is
discarded and the finishing operation is not applied (or the
finishing operation is incompletely applied).
The host should end the print unit at the committed-page
station plus 1.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
027C..03 Invalid or unsupported finishing operation
type
Action code: X'01'
Explanation: The operation type field (byte 2) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..03 Invalid or unsupported finishing operation
type
Action code: X'06'
Explanation: The operation type field (byte 2) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'85') triplet is
discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..04 Invalid or unsupported finishing operation
reference corner and edge
Action code: X'01'
Explanation: The reference corner and edge field (byte 5)
in a Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..04 Invalid or unsupported finishing operation
reference corner and edge
Action code: X'06'
Explanation: The reference corner and edge field (byte 5)
in a Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'85') triplet is
discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..02 • 027C..04


027C..05 Unsupported finishing operation count
Action code: X'01'
Explanation: The finish operation count field (byte 6) in a
Finishing Operation (X'85') triplet contains an unsupported
value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..05 Unsupported finishing operation count
Action code: X'06'
Explanation: The finish operation count field (byte 6) in a
Finishing Operation (X'85') triplet contains an unsupported
value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'85') triplet is
discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..06 Invalid or unsupported finishing operation
axis offset
Action code: X'01'
Explanation: The axis offset field (bytes 7–8) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..06 Invalid or unsupported finishing operation
axis offset
Action code: X'06'
Explanation: The axis offset field (bytes 7–8) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'85') triplet is
discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..07 Invalid or unsupported number of finishing
positions
Action code: X'01'
Explanation: In a Finishing Operation (X'85') triplet, either
the number of position values specified does not match the
count (byte 6), or the supported number of positions has
been exceeded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..08 Invalid or unsupported finishing operation
position
Action code: X'01'
Explanation: A finishing operation position field (bytes +
0–1) in a Finishing Operation (X'85') triplet contains an
invalid or unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..08 Invalid or unsupported finishing operation
position
Action code: X'06'
Explanation: A finishing operation position field (bytes +
0–1) in a Finishing Operation (X'85') triplet contains an
invalid or unsupported value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'85') triplet is
discarded.
027C..05 • 027C..08


Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..09 Finishing operation incompatible with
physical media or media destination
Action code: X'06'
Explanation: A finishing operation specified in a Finishing
Operation (X'85' or X'8E') triplet is incompatible with the
physical media or media destination used. Some printers
don't support all sizes of media or mixed-size media when
finishing.
This exception is detected after processing an End Page
command for a page to be printed. The End Page
command is processed, the received page counter is
incremented,
the Finishing Operation (X'85' or X'8E') triplet
is discarded, and the finishing operation is not applied (or
the finishing operation is incompletely applied).
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..0A Finishing operation incompatible with
change in media destination
Action code: X'06'
Explanation: An LCC command changed the media
destination within a group to be finished. For some
finishing operations, such as stapling, the physical media
cannot be split among media destinations. This exception
is detected after processing an End Page command for a
page to be printed. The End Page command is processed,
the received page counter is incremented, the Finishing
Operation (X'85') triplet is discarded, and the finishing
operation is not applied (or the finishing operation is
incompletely applied).
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..0B Media to be finished cannot be sent to the
selected media destination
Action code: X'09'
Explanation: The selected physical media cannot be sent
to the selected media destination. This exception is
detected asynchronously when an IPDS command that
ends a sheet to be finished is processed. The pages for
previous sheets are committed and the pages for the error
sheet are discarded. The Finishing Operation (X'85') triplet
is discarded and the finishing operation is not applied (or
the finishing operation is incompletely applied).
The host should end the print unit at the committed-page
station plus 1.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
027C..0C Invalidly mixed paper sizes while finishing
Action code: X'09'
Explanation: A finishing operation is requested for a
collection of sheets of mixed sizes, but the device requires
all of the sheets in the collection to be the same size. This
exception is detected asynchronously when an IPDS
command that ends a sheet to be finished is processed.
The pages for previous sheets are committed and the
pages for the error sheet are discarded. The Finishing
Operation (X'85') triplet is discarded and the finishing
operation is not applied (or the finishing operation is
incompletely applied).
The host should end the print unit at the committed-page
station plus 1.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
027C..0D Invalid or unsupported finishing option
Action code: X'01'
Explanation: The finishing option field (byte 3) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the finishing
option field
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027C..0D Invalid or unsupported finishing option
Action code: X'06'
Explanation: The finishing option field (byte 3) in a
Finishing Operation (X'85') triplet contains an invalid or
unsupported value.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
027C..09 • 027C..0D


sheet and the Finishing Operation (X'85') triplet is
discarded.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the finishing
option field
Note: For printers that support finishing fidelity control,
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
027E..00 Invalid or unsupported parameter
specification for a UP
3I-controlled device
Action code: X'01'
Explanation: A specification error is detected for a UP 3I-
controlled pre-processing or post-processing device. The
specific error is identified in the sense bytes 8–9.
The UP
3I Finishing Operation (X'8E') triplet is used to
specify finishing operations for UP 3I pre-processing or
post-processing devices attached to the printer; this error
occurred in the X'8E' triplet.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Notes:
1. For printers that support finishing fidelity control, [IPDS-16-952]
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
2. For more information about the error code in sense [IPDS-16-953]
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
027E..00 Invalid or unsupported parameter
specification for a UP 3I-controlled device
Action code: X'06'
Explanation: A specification error is detected for a UP 3I-
controlled pre-processing or post-processing device. The
specific error is identified in the sense bytes 8–9.
The UP3I Finishing Operation (X'8E') triplet is used to
specify finishing operations for UP 3I pre-processing or
post-processing devices attached to the printer; this error
occurred in the X'8E' triplet.
This exception is reported after processing an End Page
command for a sheet to be finished; the End Page
command is processed and the received page counter is
incremented. The finishing operation is not applied to the
sheet and the Finishing Operation (X'8E') triplet is
discarded.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Notes:
1. For printers that support finishing fidelity control, [IPDS-16-954]
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
2. For more information about the error code in sense [IPDS-16-955]
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP 3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
027E..00 Invalid or unsupported parameter
specification for a UP 3I-controlled device
Action code: X'09'
Explanation: A specification error is detected for a UP 3I-
controlled pre-processing or post-processing device. The
specific error is identified in the sense bytes 8–9.
The UP3I Finishing Operation (X'8E') triplet is used to
specify finishing operations for UP 3I pre-processing or
post-processing devices attached to the printer; this error
occurred in the X'8E' triplet.
For action code X'09', the host should end the print unit at
the committed-page station plus 1.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Note: For more information about the error code in sense
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
027E..00 Invalid or unsupported parameter
specification for a UP 3I-controlled device
Action code: X'0A'
Explanation: A specification error is detected for a UP 3I-
controlled pre-processing or post-processing device. The
specific error is identified in the sense bytes 8–9.
The UP
3I Finishing Operation (X'8E') triplet is used to
specify finishing operations for UP 3I pre-processing or
post-processing devices attached to the printer; this error
occurred in the X'8E' triplet.
Action code X'0A' is used for this exception ID when the
UP3I Post-Processing device detects and reports a syntax
or position-check error and when all pages that have not
yet reached the Jam-Recovery Station have been
discarded or marked as waste without any human
027E..00 • 027E..00


interaction. When operator intervention is required for post-
processor-detected syntax or position-check errors,
exception ID X'407E..00' is used.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Notes:
1. For printers that support finishing fidelity control, [IPDS-16-956]
reporting of this exception can be controlled by the
Finishing Fidelity (X'88') triplet in the PFC command.
2. For more information about the error code in sense [IPDS-16-957]
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP
3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
0280..02 Invalid or unsupported rule width [IPDS-16-958]
Action code: X'01' or X'1F'
Explanation: The rule width for a text Draw I-Axis Rule
(DIR) or Draw B-Axis Rule (DBR) control sequence is an
invalid or unsupported value.
Alternate Exception Action: Use the closest supported
nonzero rule-width value.
Page Continuation Action: Use the closest supported
nonzero rule-width value.
Support: Refer to Presentation Text Object Content
Architecture Reference.
Note: This corresponds to an exception code defined by
PTOCA.
0281..01 Insufficient storage for a page segment or [IPDS-16-959]
overlay
Action code: X'0C'
Explanation: The existing page buffer space is insufficient
to process a page segment or an overlay.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
0282..02 Invalid or unsupported rule length [IPDS-16-960]
Action code: X'01' or X'1F'
Explanation: The rule length for a text Draw Inline Rule
(DIR) or Draw Baseline Rule (DBR) control sequence is
invalid or unsupported.
Alternate Exception Action: Use the closest supported
nonzero rule-length value.
Page Continuation Action: Use the closest supported
nonzero rule-length value.
Support: Refer to Presentation Text Object Content
Architecture Reference.
Note: This corresponds to an exception code defined by
PTOCA.
0285..01 Invalid overlay ID or overlay HAID value in [IPDS-16-961]
a Deactivate Overlay command
Action code: X'01'
Explanation: The overlay ID or overlay HAID value in a
DO command is invalid.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0287..02 Invalid or unsupported value for Load Font [IPDS-16-962]
Control unit base for Pel-units
Action code: X'01'
Explanation: The unit base for Pel-units value in an LFC
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0288..02 Invalid or unsupported value for Load Font [IPDS-16-963]
Control Pel-units per unit base in the X
direction
Action code: X'01'
Explanation: The Pel-units per unit base in the X-
direction value in an LFC command is invalid or
unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0289..02 Invalid or unsupported value for Load Font [IPDS-16-964]
Control Pel-units per unit base in the Y
direction
Action code: X'01'
Explanation: The Pel-units per unit base in the Y-direction
value in an LFC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
028A..01 Invalid or unsupported value for
Deactivate Page Segment command page
segment Host-Assigned ID
Action code: X'01'
0280..02 • 028A..01 [IPDS-16-965]


Explanation: The page segment Host-Assigned ID in a
DPS command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
028A..02 Invalid or unsupported value for Load Font
Control Relative-Metric Multiplying Factor
Action code: X'01'
Explanation: The Relative-Metric Multiplying Factor value
in an LFC command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
028F ..01 Invalid or unsupported AR command
parameter value
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. The length of an AR entry is invalid or unsupported. [IPDS-16-966]
2. The Host-Assigned ID in an AR entry is invalid. [IPDS-16-967]
3. The section ID in an AR entry is invalid. [IPDS-16-968]
4. The font inline sequence in an AR entry is invalid. [IPDS-16-969]
5. The resource type in an AR entry is invalid. [IPDS-16-970]
6. The resource ID format in an AR entry is invalid. [IPDS-16-971]
7. The resource type in an AR entry is not valid with or is [IPDS-16-972]
unsupported with the resource ID format specified.
8. The resource ID in an AR entry is invalid. [IPDS-16-973]
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..02 AR command activation failed
Action code: X'01'
Explanation: An AR command activation failed because
the requested resource is not found.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the activation failed NACK bit in the AR
command.
028F ..03 Invalid Resource ID triplet length
Action code: X'01'
Explanation: In an Activate Resource command, one or
more of the following conditions exists:
1. The first byte after the fixed portion of a resource ID or [IPDS-16-974]
the first byte after a triplet is X'00' or X'01' (an invalid
triplet length).
2. The triplet-length field in a Resource ID triplet contains [IPDS-16-975]
an invalid value.
3. A Resource ID triplet is too long to fit in the containing [IPDS-16-976]
AR command.
The triplet is either a Local Date and Time Stamp (X'62')
triplet, a Metric Adjustment (X'79') triplet, or a Font
Resolution and Metric Technology (X'84') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when resource ID triplets are
supported in the AR command
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..04 Invalid or unsupported resolution or
metric-technology value
Action code: X'01'
Explanation: In a Font Resolution and Metric Technology
(X'84') triplet in an Activate Resource command, one or
more of the following conditions exists:
1. The metric technology field (byte 2) contains an invalid [IPDS-16-977]
value.
2. The unit base field (byte 3) contains an invalid value. [IPDS-16-978]
3. The X units per unit base field (bytes 4–5) contains an [IPDS-16-979]
invalid or unsupported value.
4. The Y units per unit base field (bytes 6–7) contains an [IPDS-16-980]
invalid or unsupported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..10 Invalid or unsupported value in a Metric
Adjustment (X'79') triplet
Action code: X'01'
Explanation: In a Metric Adjustment (X'79') triplet in an
Activate Resource command, one or more of the following
conditions exists:
1. The unit base field (byte 2) contains an invalid value. [IPDS-16-981]
2. The XUPUB or YUPUB field (bytes 3–4 or bytes 5–6) [IPDS-16-982]
contains an invalid or unsupported value.
3. The XUPUB value is not equal to the YUPUB value. [IPDS-16-983]
028A..02 • 028F ..10


Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if Metric Adjustment (X'79') triplets
are supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..11 Baseline adjustment value too large or too
small
Action code: X'01'
Explanation: In a Metric Adjustment (X'79') triplet in an
Activate Resource command, the combination of the
baseline offset adjustment value and a character's baseline
offset value created another internal value that is beyond
the range the printer can handle.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if Metric Adjustment (X'79') triplets
are supported
028F ..20 Required Data Object Font Descriptor
(X'8B') triplet missing
Action code: X'01'
Explanation: An AR command to activate a data-object
font is encountered, but the required Data Object Font
Descriptor (X'8B') triplet is not specified in the activation
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..21 Invalid font-technology value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, one of the following conditions exists:
1. An invalid font-technology value is specified in a Data [IPDS-16-984]
Object Font Descriptor (X'8B') triplet.
2. The font technology specified in a Data Object Font [IPDS-16-985]
Descriptor (X'8B') triplet does not match the actual
technology of the font.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..22 Invalid vertical-font-size value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, an invalid vertical-font-size value is specified in a Data
Object Font Descriptor (X'8B') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..23 Invalid horizontal-scale-factor value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, an invalid horizontal-scale-factor value is specified in
a Data Object Font Descriptor (X'8B') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..24 Invalid character-rotation value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, an invalid character-rotation value is specified in a
Data Object Font Descriptor (X'8B') triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..25 Invalid encoding environment value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, one of the following conditions exists:
1. An invalid encoding environment value is specified in a [IPDS-16-986]
Data Object Font Descriptor (X'8B') triplet.
2. The encoding environment value specified in a Data [IPDS-16-987]
Object Font Descriptor (X'8B') triplet cannot be used
with the specified font technology.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..26 Invalid environment-specific encoding
identifier value
Action code: X'01'
Explanation: In an AR command to activate a data-object
font, an invalid environment-specific encoding identifier
028F ..11 • 028F ..26 [IPDS-16-988]


value is specified in a Data Object Font Descriptor (X'8B')
triplet.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..30 Code page used with a data-object font is
not active
Action code: X'01'
Explanation: The AR command that activated a data-
object font specified a code page HAID, but the code page
is not currently activated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
028F ..31 Linked font is not activated or is not a valid
linked object
Action code: X'01'
Explanation: One of the following conditions exists:
1. An AR command to activate a data-object font [IPDS-16-989]
specified the HAID of an object in a Linked Font (X'8D')
triplet, but that object is not currently activated.
2. An AR command to activate a data-object font [IPDS-16-990]
specified an object in a Linked Font (X'8D') triplet, but
the specified object is not a TrueType/OpenType Font
or TrueType/OpenType Collection.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports data-object
fonts
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
028F ..50 Unknown character ID (GCGID) value
Action code: X'01'
Explanation: An unknown Graphic Character Global ID
(GCGID) value is found within a code page used with a
data-object font. Only GCGIDs that are used in IBM-
supplied code pages are supported within some printers.
However, other printers support extended (Unicode
mapping) code pages that can be used to provide
additional GCGID-to-Unicode mappings. The printer did
not find a Unicode value for the specified GCGID in the
code page nor in the internal printer table.
Alternate Exception Action: The code point is skipped
and glyph index 0 is used in its place.
Page Continuation Action: The code point is skipped
and glyph index 0 is used in its place.
Support: Mandatory, if the printer supports data-object
fonts
Notes:
1. Reporting of this exception is controlled by the Report [IPDS-16-991]
Undefined Character Check bit in the XOA Exception-
Handling Control command.
2. The HAID value returned in sense bytes 14–15 is the [IPDS-16-992]
HAID of the data object font associated with the code
page that contains the unknown character-ID value.
3. Sense bytes 16–17 contain the unknown character's [IPDS-16-993]
code point. For single-byte code pages, sense byte 16
contains X'00' and sense byte 17 contains the code
point.
0290..01 Invalid or unsupported overlay ID or [IPDS-16-994]
overlay HAID value in a BO, IO, or LCC
command
Action code: X'01' or X'1F'
Explanation: One of the following conditions exists:
1. Either an overlay ID or overlay HAID value in a BO [IPDS-16-995]
command is invalid.
2. An overlay HAID value in an IO command is invalid or [IPDS-16-996]
unsupported.
3. An overlay ID or overlay HAID value in an LCC [IPDS-16-997]
command is invalid.
Alternate Exception Action: None
Page Continuation Action: If the error occurred in an IO
command, ignore the command. If the error occurred in a
BO or LCC command, there is no PCA.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0291..01 Overlay already activated [IPDS-16-998]
Action code: X'01'
Explanation: The host attempts to download an overlay
whose overlay ID or overlay HAID has already been
activated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0291..02 Invalid or unsupported value for XOA [IPDS-16-999]
Request Resource List entry
Action code: X'01'
028F ..30 • 0291..02


Explanation: One or more of the following conditions
exists:
1. The length of a Request Resource List entry is invalid [IPDS-16-1000]
or unsupported.
2. A nonzero value is specified in the entry-continuation [IPDS-16-1001]
indicator field (bytes 3–4), but there is no XOA-RRL
information to return, or there is no previous XOA-RRL
command with a X'0000' in the entry-continuation
indicator field.
3. A Request Resource List command has multiple [IPDS-16-1002]
entries and this function is either not supported for the
query type or is supported for the query type but is not
supported by the printer.
4. The query type parameter of a Request Resource List [IPDS-16-1003]
order is invalid or unsupported.
5. The resource type in a Request Resource List entry is [IPDS-16-1004]
invalid.
6. A Request Resource List entry of query type X'05', [IPDS-16-1005]
activation query, has requested a list of resources.
7. The resource ID format in a Request Resource List [IPDS-16-1006]
entry is invalid.
Alternate Exception Action: None
Page Continuation Action: XOA-RRL command when
not in home or font state: ignore the command.
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0292..01 Overlay not activated [IPDS-16-1007]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. An overlay identified by the overlay ID or overlay HAID [IPDS-16-1008]
value in an IO, a DO, or an LCC command has not
been activated or has been deactivated prior to its
attempted use.
2. An overlay identified by the overlay ID or overlay HAID [IPDS-16-1009]
value in a DO command is used on at least one page
of an incomplete sheet (the first page of a duplex sheet
for example) and is being deactivated before the
remaining pages have been received. The overlay is
deactivated.
Alternate Exception Action: None
Page Continuation Action: If the error occurred in an IO
command, ignore the command. If the error occurred in a
DO or LCC command, there is no PCA.
Support: Mandatory for the 1st condition, optional for the
2nd condition
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0292..02 Invalid XOA Print-Quality Control (PQC) [IPDS-16-1010]
parameter
Action code: X'01'
Explanation: In an XOA-PQC command, the quality-level
value is X'00', that is an invalid value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0293..00 Invalid nesting of a preprinted form overlay [IPDS-16-1011]
Action code: X'01' or X'1F'
Explanation: An Include Overlay command for a
preprinted form overlay (PFO) was found within an overlay.
Preprinted form overlays cannot be nested within other
overlays.
Alternate Exception Action: Ignore the overlay-use
value (byte 6 of the IO command) and present the overlay
as a page overlay.
Page Continuation Action: None
Support: Mandatory if preprinted form overlays are
supported
0293..01 Recursive overlay invocation [IPDS-16-1012]
Action code: X'01' or X'1F'
Explanation: A recursive nesting loop has occurred with
an IO command (for example, an overlay has included
itself).
Alternate Exception Action: None
Page Continuation Action: Ignore the command.
Support: Mandatory
0293..02 Invalid orientation value in an IO command [IPDS-16-1013]
Action code: X'01' or X'1F'
Explanation: An invalid value is specified for the
orientation field in an Include Overlay command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when page-overlay rotation is
supported
0293..03 Invalid invocation of a preprinted form [IPDS-16-1014]
overlay with the IO command
Action code: X'01' or X'1F'
Explanation: A preprinted form overlay was invoked with
an Include Overlay command, but one or more of the
following conditions exists:
1. More than one Include Overlay command specified a [IPDS-16-1015]
0292..01 • 0293..03 [IPDS-16-1016]


preprinted form overlay; there can be only one
preprinted form overlay for a page.
2. A PFO has already been invoked via an LCC [IPDS-16-1017]
command; when a medium-level PFO is invoked,
page-level PFOs must not be specified.
Alternate Exception Action: Ignore the overlay-use
value (byte 6 of the IO command) and present the overlay
as a page overlay.
Page Continuation Action: Ignore the overlay-use value
(byte 6 of the IO command) and present the overlay as a
page overlay.
Support: Mandatory if preprinted form overlays are
supported
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0293..04 Invalid overlay-use value in an IO [IPDS-16-1018]
command
Action code: X'01' or X'1F'
Explanation: An invalid value was specified in the
overlay-use field of an Include Overlay command.
Alternate Exception Action: Ignore the overlay-use
value (byte 6 of the IO command) and present the overlay
as a page overlay.
Page Continuation Action: Ignore the overlay-use value
(byte 6 of the IO command) and present the overlay as a
page overlay.
Support: Mandatory if preprinted form overlays are
supported
0294..01 Invalid or unsupported value for page [IPDS-16-1019]
segment Host-Assigned ID
Action code: X'01' or X'1F'
Explanation: The page segment Host-Assigned ID in a
BPS or an IPS command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: IPS: ignore the command.
BPS has no PCA.
Support: Mandatory
0295..01 Page segment Host-Assigned ID already [IPDS-16-1020]
activated
Action code: X'01'
Explanation: The host has attempted to download a page
segment whose HAID has already been activated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0295..02 Invalid or unsupported value for XOH Page [IPDS-16-1021]
Counters Control command page-counter
update
Action code: X'01'
Explanation: The value specified in the page-counter-
update field of an XOH Page Counters Control command is
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0296..01 Page segment not activated [IPDS-16-1022]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The page segment identified by the page segment [IPDS-16-1023]
Host-Assigned ID in an IPS or DPS command has not
been activated or has been deactivated before its
attempted use.
2. The page segment identified by the page segment [IPDS-16-1024]
Host-Assigned ID in a DPS command has been used
on an incomplete sheet (for example, the first side of a
duplex sheet) and is being deactivated before the
pages for the remainder of the sheet have been
received. The page segment has been deactivated.
Alternate Exception Action: None
Page Continuation Action: IPS: ignore the command.
DPS has no PCA.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0297..01 Overlay nesting limit exceeded [IPDS-16-1025]
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. While processing an overlay the printer is unable to [IPDS-16-1026]
include a nested overlay because the nesting limit of
the printer has been exceeded.
2. While processing an overlay, the printer is unable to [IPDS-16-1027]
include a page segment because the nesting limit of
the printer has been exceeded.
Alternate Exception Action: None
Page Continuation Action: Ignore the Include command.
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
0298..01 Invalid or unsupported suppression ID [IPDS-16-1028]
Action code: X'01' or X'1F'
0293..04 • 0298..01 [IPDS-16-1029]


Explanation: One of the following conditions exists:
1. The suppression ID in an LCC command is invalid or [IPDS-16-1030]
unsupported.
2. The Begin Suppression (BSU) suppression ID in a WT [IPDS-16-1031]
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action:
• LCC: none [IPDS-16-1032]
• WT : ignore the control sequence [IPDS-16-1033]
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1034]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-1035]
number of a specific cause for the error.
0298..03 Invalid or unsupported value for [IPDS-16-1036]
Temporary Baseline Move control
sequence
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The TBM increment, move direction, or precision value [IPDS-16-1037]
is invalid or unsupported.
2. Unsupported multiple-offset TBM. [IPDS-16-1038]
3. Unsupported substitution character in the TBM field. [IPDS-16-1039]
4. Unable to support TBM by printing full-size characters. [IPDS-16-1040]
Alternate Exception Action: For the exception described
by the last bullet, print device-defined characters to
simulate the function. For the other exceptions, there is no
AEA.
Page Continuation Action: Ignore the control sequence.
Support: Mandatory
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1041]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-1042]
number of a specific cause for the error.
0299..02 Invalid Edge Mark Parameter [IPDS-16-1043]
Action code: X'01'
Explanation: An invalid edge-mark value (byte 2) is
specified in an XOA Control Edge Marks command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
029A..01 Invalid overstrike character increment
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists in a text Overstrike control sequence within a Write
Text command:
1. (Mandatory) The character increment of the selected [IPDS-16-1044]
overstrike character is less than or equal to zero.
2. (Optional) The character increment of the selected [IPDS-16-1045]
overstrike character is less than the character-box X
size.
3. (Mandatory) The overstrike character is not a printable [IPDS-16-1046]
character.
Alternate Exception Action: None
Page Continuation Action: Ignore the OVS control
sequence in the Write Text command.
Support: Specific to the condition as shown in the
explanation
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1047]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-1048]
number of a specific cause for the error.
029B..01 Invalid Unicode Complex Text (UCT)
parameter value
Action code: X'01' or X'1F'
Explanation: An invalid parameter value was specified in
a PTOCA UCT control sequence within a Write Text
command.
Alternate Exception Action: None
Page Continuation Action: Skip to the next non-WT
command; if an anystate command is encountered,
process it and then keep skipping.
Support: Mandatory if UCT is supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1049]
PTOCA.
2. IPDS printers that return property pair X'4303' in the [IPDS-16-1050]
Text command-set vector of an STM reply only check
for an invalid complex-text data length value
(CTLNGTH). Since the UCT version level, bidi layout
processing control, glyph processing control, and
alternate current inline position are ignored by these
IPDS printers, these parameters are not checked for
validity.
029C..00 Wrong font used with a Glyph Layout [IPDS-16-1051]
## Control (GLC)
Action code: X'01' or X'1F'
0298..03 • 029C..00 [IPDS-16-1052]


Explanation: One of the following conditions exists:
1. The object OID specified in the GLC control sequence [IPDS-16-1053]
does not match the object OID of the current font or
any font linked to the current font.
2. The OIDs do match for a font collection, however the [IPDS-16-1054]
full font name from the GLC that specified the OID
does not match any font within the collection.
3. The printer does not have a font OID at the time a [IPDS-16-1055]
glyph run is processed.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1056]
PTOCA.
2. Sense bytes 14–15 should contain the HAID of the [IPDS-16-1057]
font.
3. Sense bytes 16–17 should contain the explanation [IPDS-16-1058]
number of a specific cause for the error.
029C..01 Font used with a GLC chain is not a
TrueType/OpenType font
Action code: X'01' or X'1F'
Explanation: A PTOCA Glyph Layout Control (GLC)
control sequence was encountered and the current font is
not a TrueType/OpenType font.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029C..02 Glyph ID specified in GIR control
sequence, but not found in font
Action code: X'01' or X'1F'
Explanation: A PTOCA Glyph ID Run (GIR) control
sequence contained a glyph ID that was not found in the
current font.
Alternate Exception Action: Skip the glyph ID
Page Continuation Action: Skip the glyph ID
Support: Mandatory if PTOCA glyph layout controls are
supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1059]
PTOCA.
2. Sense bytes 16–17 contain the glyph ID that caused [IPDS-16-1060]
the error.
029C..03 Missing or invalid control sequence found
in a GLC chain
Action code: X'01' or X'1F'
Explanation: An unexpected control sequence was
encountered within a GLC chain. A PTOCA Glyph Layout
Control (GLC) control sequence must be followed by a
series of Glyph ID Run (GIR), Glyph Advance Run (GAR),
Glyph Offset Run (GOR), and Unicode Complex Text
(UCT) control sequences. These control sequences must
be provided within a chain and be specified in the following
order (the square brackets indicate an optional control):
X'2BD3' GLC GIR GAR [GOR] [GIR GAR [GOR]]
... [GIR GAR [GOR]] [UCT] [IPDS-16-1061]
Note that the GLC can be preceded with any chained
PTOCA control sequence other than GLC, GIR, GAR, or
GOR.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029C..05 Unsupported UCT control sequence found
outside of a GLC chain
Action code: X'01' or X'1F'
Explanation: A Unicode Complex Text (UCT) control
sequence was found outside of a GLC chain. Printers that
support glyph layout controls will ignore UCTs that are
chained to a GLC chain and will recognize but not support
other UCT control sequences.
Alternate Exception Action: None
Page Continuation Action: Skip to the next IO, IPS, LFE,
WGC, WIC, WIC2, WBCC, or EP command.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: Reporting of this exception can be controlled by the
Text Fidelity (X'86') triplet in the PFC command. The
continuation rule is to process the Unicode code
points associated with the UCT in a one-code-point
to one-glyph manner.
029C..06 GIR, GAR, or GOR control sequence found
outside of a GLC chain
Action code: X'01' or X'1F'
029C..01 • 029C..06


Explanation: A Glyph ID Run (GIR), Glyph Advance Run
(GAR), or Glyph Offset Run (GOR) control sequence was
encountered outside of a Glyph Layout Control (GLC)
chain.
Alternate Exception Action: Skip the unexpected GIR,
GAR, or GOR control sequence
Page Continuation Action: Skip the unexpected GIR,
GAR, or GOR control sequence
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029C..08 Too many or too few entries in a GAR or
GOR
Action code: X'01' or X'1F'
Explanation: The number of entries in a GAR or GOR
control sequence must match the number of entries in the
immediately preceding GIR control sequence.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029C..09 OID not provided for a GLC chain
Action code: X'01' or X'1F'
Explanation: A PTOCA Glyph Layout Control (GLC)
control sequence specified an OID length of zero
(indicating that the previously specified OID is still in
effect), but there was no previously specified OID found
within the text object, page, or overlay.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029C..0A Length mismatch within a GLC control
sequence
Action code: X'01' or X'1F'
Explanation: A PTOCA Glyph Layout Control (GLC)
control sequence contains an incorrect length field. One or
more of the following conditions occurred:
1. The OID-length field or the full-font-name-length field [IPDS-16-1062]
contains a value that is not consistent with the overall
control sequence length.
2. The OID-length field contains an invalid value. [IPDS-16-1063]
3. The full-font-name-length field contains an invalid [IPDS-16-1064]
value.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1065]
PTOCA.
2. Sense bytes 16–17 should contain the explanation [IPDS-16-1066]
number of a specific cause for the error.
029C..0B Full Font Name specified in a GLC control
sequence without a font OID
Action code: X'01' or X'1F'
Explanation: A PTOCA Glyph Layout Control (GLC)
control sequence contains a value in the full-font-name
field and a non-zero value in the full-font-name-length field,
but no font OID was specified. The PTOCA architecture
forbids specification of a full font name without also
specifying a font OID.
Alternate Exception Action: None
Page Continuation Action: Skip to the end of the GLC
chain and, if a UCT is encountered, skip all code points
associated with the UCT ; if an anystate command is
encountered, process it and then keep skipping.
Support: Mandatory if PTOCA glyph layout controls are
supported
Note: This corresponds to an exception code defined by
PTOCA.
029D..01 Text string decryption not available
Action code: X'01' or X'1F'
Explanation: A PTOCA Set Key Information (SKI) or
Encrypted Data (ENC) control sequence was encountered,
but the device currently does not support decryption.
Alternate Exception Action: For the SKI, skip the SKI
control sequence. For the ENC, use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
029C..08 • 029D..01


Page Continuation Action: For the SKI, skip the SKI
control sequence. For the ENC, use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
Support: Mandatory if the PTOCA SKI and ENC control
sequences are supported
Notes:
1. This corresponds to an exception code defined by [IPDS-16-1067]
PTOCA.
2. For printers that support text fidelity control, reporting [IPDS-16-1068]
of this exception can be controlled by the Text Fidelity
(X'86') triplet in the PFC command.
029D..02 Text string decryption failed
Action code: X'01' or X'1F'
Explanation: A PTOCA Encrypted Data (ENC) control
sequence was encountered, but the attempt to decrypt the
data in the control sequence failed.
Alternate Exception Action: Use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
Page Continuation Action: Use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
Support: Mandatory if text decryption is supported
Note: This corresponds to an exception code defined by
PTOCA.
029D..03 No text string encryption key information
set
Action code: X'01' or X'1F'
Explanation: A PTOCA Encrypted Data (ENC) control
sequence was encountered, but no previous Set Key
Information (SKI) was encountered. Decryption of the
encrypted text cannot be done without information about
the encryption key to use.
Alternate Exception Action: Use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
Page Continuation Action: Use the alternate text from
the PTOCA Set Encrypted Alternate (SEA) control
sequence, if any; otherwise, skip the ENC control
sequence.
Support: Mandatory if the PTOCA SKI and ENC control
sequences are supported
Note: This corresponds to an exception code defined by
PTOCA.
02A1..00 Unsupported setup name in an Activate
Setup Name command
Action code: X'01'
Explanation: An Activate Setup Name (ASN) command
requested a setup name that is not supported by the
printer. Setup names and their meanings are assigned by
the user of the printer and must be known to the printer
before the ASN command is issued.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the ASN command is supported
02A1..01 Unable to activate a setup name requested
by an ASN command
Action code: X'01'
Explanation: An Activate Setup Name (ASN) command
requested a supported setup name, but the printer cannot
accomplish the activation of the setup name without
operator intervention.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the ASN command is supported
02A2..00 Invalid query type in an XOA RSNL
command
Action code: X'01'
Explanation: An invalid query-type value was specified in
an XOA Request Setup Name List command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the XOA RSNL command is
supported
02A4..01 Logical-page boundary in the X-direction
cannot be represented in the printer
Action code: X'01' or X'1F'
Explanation: The sum of the Xp-extent value in the LPD
command or the Xp-coordinate value of an IO command
and the Xm-coordinate value in the LPP command exceed
the maximum supported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
02A4..02 User printable area boundary in the X-
direction cannot be represented in the
printer
Action code: X'01'
Explanation: The sum of the Xm-coordinate of the user
printable area origin and the Xm-extent of the user printable
area specified in a Define User Area command exceed the
maximum value that can be represented in the printer.
Alternate Exception Action: None
029D..02 • 02A4..02


Page Continuation Action: None
Support: Mandatory
Note: Checking for this condition can be done when a
Begin Page command is processed rather than
when the DUA command is received. Therefore,
when this exception is found, the user printable area
in effect is the last one that is received, that is, the
one containing the exception, and the exception can
recur for every subsequent page. Also, the page
might not be printed until a valid DUA command is
received or the printer is restarted.
02A5..01 Logical-page boundary in the Y-direction
cannot be represented in the printer
Action code: X'01' or X'1F'
Explanation: The sum of the Yp-extent value in the LPD
command or the Yp-coordinate value of an IO command
and the Ym-coordinate value in the LPP command exceed
the maximum supported value.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
02A5..02 User printable area boundary in the Y-
direction cannot be represented in the
printer
Action code: X'01'
Explanation: The sum of the Ym-coordinate of the user
printable area origin and the Ym-extent of the user printable
area specified in a Define User Area command exceeded
the maximum value that can be represented in the printer.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Checking for this condition can be done when a
Begin Page command is processed rather than
when the DUA command is received. Therefore,
when this exception is found, the user printable area
in effect is the last one that is received, that is, the
one containing the exception, and the exception can
recur for every subsequent page. Also, the page
might not be printed until a valid DUA command is
received or the printer is restarted.
02AB..01 Insufficient page-buffer storage to print the
sheet
Action code: X'0C'
Explanation: Either the page is too large for page-buffer
space or both sides of a duplexed sheet are collectively too
large for available page-buffer space. Deactivating unused
resources and retransmitting the page might correct the
problem.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
02AC..01 Insufficient main storage to print the sheet
Action code: X'09'
Explanation: Either the page is too large for main storage
or both sides of a duplexed sheet are collectively too large
for available main storage. Deactivating unused resources
does not affect the situation.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
02AC..01 Insufficient main storage to print the sheet
Action code: X'0C'
Explanation: Either the page is too large for main storage
or both sides of a duplexed sheet are collectively too large
for available main storage.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Some printers report this exception as X'02AF ..01'.
The preferred exception ID is X'02AF ..01'.
02AD..01 Invalid or unsupported offset value in an
LPP command
Action code: X'01'
Explanation: The X
m coordinate or Ym coordinate in an
LPP command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02AD..02 Invalid or unsupported page-placement
value in an LPP command
Action code: X'01'
Explanation: The page-placement value specified in a
Logical Page Position command is invalid or unsupported.
The page-placement value in the LPP command must be
valid for the simplex/duplex value and N-up value specified
in the most recently received LCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when explicit page placement and
orientation is supported
02AD..03 Invalid or unsupported orientation value in
an LPP command
Action code: X'01'
02A5..01 • 02AD..03


Explanation: The orientation value specified in a Logical
Page Position command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory when explicit page placement and
orientation is supported
02AE..01 Invalid or unsupported parameter in an IO
command
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The Xp coordinate or Yp coordinate in an IO command [IPDS-16-1069]
is invalid or unsupported.
2. The Overlay Type parameter in an IO command is [IPDS-16-1070]
invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02AF ..01 Insufficient storage to continue processing
Action code: X'0C'
Explanation: There is insufficient storage to continue
processing. If this exception occurs while downloading an
overlay, page segment, font, or data object resource, the
partial resource is discarded. If this exception occurs while
saving a page, that page is discarded, but previously saved
pages are kept. When an out-of-storage exception causes
the first page of a group to be discarded, the group is
terminated and information concerning the group is
discarded.
Deactivating unused overlays, page segments, fonts, data
object resources, and saved page groups might correct the
problem.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Some printers report this exception as X'0212..02',
X'023A..02', X'024D..02', X'0263..01', X'0264..01',
X'0281..01', X'02AB..01', or X'02AC..01'. The
preferred exception ID is X'02AF ..01'.
02AF ..01 Asynchronously detected insufficient
storage to continue processing
Action code: X'1E'
Explanation: There is insufficient storage to continue
processing. This exception is detected after the page
passed the Received Page Counter station. Deactivating
unused fonts, overlays, and page segments might correct
the problem.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
02B0..00 Code page Host-Assigned ID already
assigned
Action code: X'01'
Explanation: The code page HAID specified in a Load
Code Page Control command has already been used in a
previously received AR or LCPC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..01 Invalid code page Host-Assigned ID in an
LCPC command
Action code: X'01'
Explanation: The code page HAID specified in a Load
Code Page Control command is invalid.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..02 Invalid or unsupported encoding-scheme
value in a code page
Action code: X'01' or X'1F'
Explanation: The encoding-scheme value specified in a
Load Code Page Control command or in a resident code
page is invalid or unsupported.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..03 Invalid CPGID value in a code page
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The CPGID value specified in a Load Code Page [IPDS-16-1071]
Control command or in a resident code page contains
an invalid (out of range) value.
2. The printer requires a valid CPGID, but one is not [IPDS-16-1072]
supplied.
This exception ID can be reported either when the
resource is being activated, or when a printer default code
page is selected.
Alternate Exception Action: None
02AE..01 • 02B0..03


Page Continuation Action: None
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02B0..04 Too much or too little code page data
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The amount of code page data received in a series of [IPDS-16-1073]
Load Code Page commands or in a resident code
page does not match the value in the byte-count field
of the Load Code Page Control command.
2. The last entry in a code page is incomplete. [IPDS-16-1074]
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02B0..05 Invalid or unsupported byte-count value in
a code page
Action code: X'01' or X'1F'
Explanation: The byte-count value specified in a Load
Code Page Control command or in a resident code page is
invalid or unsupported.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..07 Code points out of order in a code page
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The entries in a series of LCP commands or in a [IPDS-16-1075]
resident code page are not specified in ascending
code-point order.
2. A code point appeared more than once. [IPDS-16-1076]
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02B0..0A Font character set Host-Assigned ID
already assigned
Action code: X'01'
Explanation: The font-character-set HAID specified in a
Load Font Character Set Control command has already
been used in a previously received AR or LFCSC
command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..0B Invalid Host-Assigned ID in an LFCSC
command
Action code: X'01'
Explanation: The font character set HAID specified in a
Load Font Character Set Control command is invalid.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..0C Invalid or unsupported pattern-technology
ID in a font character set
Action code: X'01' or X'1F'
Explanation: The pattern-technology ID value specified in
a Load Font Character Set Control command or in a
resident font character set is invalid or unsupported.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..0D Invalid FGID value in a font character set
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. The FGID value specified in a Load Font Character [IPDS-16-1077]
Set Control command or in a resident font character
set contains an invalid (out of range) value.
2. The printer requires a valid FGID, but one is not [IPDS-16-1078]
supplied.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
02B0..04 • 02B0..0D


Page Continuation Action: None
Support: Optional
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02B0..0E Invalid or unsupported Load-Font count
value in a font character set
Action code: X'01' or X'1F'
Explanation: The Load-Font count value specified in a
Load Font Character Set Control command or in a resident
font character set is invalid or unsupported.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B0..0F Invalid or unsupported map-size value in a
font character set
Action code: X'01' or X'1F'
Explanation: An invalid or unsupported map-size value is
specified in a Load Font Character Set command or in a
resident font character set.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..01 Invalid or unsupported character ID format
in a font character set
Action code: X'01' or X'1F'
Explanation: An invalid or unsupported character-ID-
format value is specified in the IBM-format field or in the
technology-specific-format field in a character ID map. The
character ID map is either in an LF3-type Load Font
command or in a resident font character set.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..02 Invalid technology-specific ID offset in a
font character set
Action code: X'01' or X'1F'
Explanation: An invalid technology-specific ID offset
value is specified in the character ID map in an LF3-type
Load Font command or in a resident font character set.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..03 Invalid technology-specific ID length in a
font character set
Action code: X'01' or X'1F'
Explanation: An invalid technology-specific ID length
value is specified in the character ID map in an LF3-type
Load Font command or in a resident font character set.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..04 GCGIDs out of order in a font character set
Action code: X'01' or X'1F'
Explanation: One or more of the GCGIDs in the character
ID map in an LF3-type Load Font command or in a resident
font character set is out of order. The GCGIDs must occur
in ascending EBCDIC order.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..08 Invalid technology-specific object length in
a font character set
Action code: X'01' or X'1F'
Explanation: An invalid length value is specified in a
technology-specific object found either in an LF3-type Load
Font command or in a resident font character set.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..09 Checksum mismatch in a font character
set
02B0..0E • 02B1..09


Action code: X'01' or X'1F'
Explanation: The checksum specified in a technology-
specific object within an LF3-type Load Font command or
within a resident font character set does not match the
checksum calculated by the printer.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..0A Invalid technology-specific-object length
value in a font character set
Action code: X'01' or X'1F'
Explanation: Either the identifier length value (bytes 8–9)
or the descriptor length value (bytes n+1 to n+2) specified
in a technology-specific object within an LF3-type Load
Font command or within a resident font character set is not
valid.
This exception ID can be reported either when the
resource is being activated, or when a printer default font is
selected.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B1..0B Invalid or missing data within an LF3-type
technology-specific object
Action code: X'01' or X'1F'
Explanation: One or more of the technology-specific
objects in an LF3-type font character set contains invalid or
missing data. This exception also occurs if a needed
technology-specific object is missing. The font character
set is unusable. This exception ID can be reported either
when the coded font is being activated or when a character
within the font is selected for printing.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B2..01 Parent font character set not activated
Action code: X'01'
Explanation: The extension flag is set to B'1' in a Load
Font Character Set Control command, but the parent font
identified by the HAID has not been previously activated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B2..02 Font character set extension not valid with
pattern technology
Action code: X'01'
Explanation: The extension flag is set to B'1' in a Load
Font Character Set Control command, but the specified
pattern technology does not support extensions.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B2..03 Mismatched character-ID format in an LF
command
Action code: X'01'
Explanation: Either the IBM character-ID-format value or
the technology-specific character-ID-format value in the
character-ID map of a font character set extension does
not match the equivalent value in the parent font character
set.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B2..04 Mismatched MICR printing flag in an
LFCSC command
Action code: X'01'
Explanation: The Intended for MICR Printing flag value
(byte 6, bit 0) in a font character set extension does not
match the corresponding flag value in the parent font
character set.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02B3..01 MICR text string cannot be printed
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
1. A string of text within a WT or WG command is [IPDS-16-1079]
encountered that is to be printed with a MICR font, but
MICR printing is not available for this text string.
2. Some printers can print MICR text on one side of the [IPDS-16-1080]
media, but not on the other side; in this case, text data
to be printed with a MICR font that is placed on the
non-MICR side of the media causes this exception to
occur.
3. Some printers can print MICR text only in specific [IPDS-16-1081]
colors, usually black or a specific highlight color.
4. Some printers allow MICR printing to be disabled [IPDS-16-1082]
(even after a MICR font has been activated).
Alternate Exception Action: None
02B1..0A • 02B3..01


Page Continuation Action: None
Support: Mandatory for MICR-supporting printers for
which this exception condition can occur
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02C0..01 Mixture of Xm-axis duplex and Ym-axis
duplex copy subgroups
Action code: X'01'
Explanation: A copy-subgroup pair in an LCC command
contains a mixture of Xm-axis duplex and Ym-axis duplex
copy subgroups.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C0..02 Mixture of N-up copy subgroups in an LCC
command
Action code: X'01'
Explanation: Two different N-up keywords are specified in
two copy subgroups of a Load Copy Control command.
When an N-up keyword is specified in a copy subgroup, all
other copy subgroups must specify the same N-up
keyword.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C0..03 More than one N-up keyword specified in a
copy subgroup
Action code: X'01'
Explanation: In a Load Copy Control command, more
than one N-up keyword is specified in a copy subgroup.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Some printers use this exception ID when more than
one CSE keyword is specified in an LCC command
copy subgroup; the correct behavior is to ignore all
but the first CSE keyword.
02C0..04 Duplexing and N-up not supported
together
Action code: X'01'
Explanation: In a Load Copy Control command, both an
N-up keyword and a duplex keyword are specified, but the
printer supports N-up only when simplexing.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C0..05 N-up partitioning not supported with
envelope media
Action code: X'01'
Explanation: In a Load Copy Control command, an N-up
keyword is specified while envelope media is selected. N-
up partitioning is not used with envelope media.
Alternate Exception Action: Print as if the N-up keyword
had not been specified.
Page Continuation Action: None
Support: Mandatory
02C1..01 Multiple simplex/duplex keywords in an
LCC command
Action code: X'01'
Explanation: More than one simplex or duplex operation
keyword is specified in an LCC command copy subgroup.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C1..02 Internal value not unique in an LE
command
Action code: X'01'
Explanation: The internal-suppression number of two or
more list entries in an LE command is not unique.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C2..01 Odd number of duplex copy subgroups in
an LCC command
Action code: X'01'
Explanation: An LCC command has an odd number of
copy subgroups when duplex is specified.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C2..02 More than one media-source or media-
destination keyword specified in a copy
subgroup
Action code: X'01'
Explanation: One or more of the following conditions
exists:
1. In a Load Copy Control command, more than one [IPDS-16-1083]
media-source keyword is specified in a copy subgroup.
2. In a Load Copy Control command, more than one [IPDS-16-1084]
media-destination keyword is specified in a copy
02C0..01 • 02C2..02


subgroup. Either more than one X'90' keyword or more
than one X'91' keyword is specified.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Sense bytes 16–17 should contain the explanation
number of a specific cause for the error.
02C3..01 Mixture of simplex and duplex parameters
in an LCC command
Action code: X'01'
Explanation: A mixture of simplex and duplex copy
subgroups is specified in an LCC command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C4..01 Unequal copy counts in an LCC command
Action code: X'01'
Explanation: Unequal copy counts are specified for a
copy-subgroup pair in a Load Copy Control command.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C5..01 Unable to deactivate resource
Action code: X'01'
Explanation: A coded font, fully described font section, or
font index for which a deactivation is requested has not
been deactivated because it is needed to print a page on
an incomplete sheet; not all of the pages to be printed on
the sheet have yet been received.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C5..02 Physical media not compatible with duplex
printing
Action code: X'01'
Explanation: Non-duplexable physical media selected for
duplex prints.
Alternate Exception Action: Print simplex on the
designated physical media by processing each input page
against both the front-side copy subgroups and the
backside copy subgroups. This yields twice as many
sheets for each input page as would be printed if the printer
could print duplex.
Page Continuation Action: None
Support: Optional
02C6..01 Unable to deactivate a component of an
activated coded font
Action code: X'01'
Explanation: A Deactivate Font command has attempted
to deactivate a font character set or code page that is
currently being used in an activated coded font. Before
deactivating a font character set or code page, all coded
fonts that use these components must first be deactivated.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C6..02 Invalid mapping type in an LE command
Action code: X'01'
Explanation: The mapping type in a Load Equivalence
command is not valid.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02C8..01 Unsupported media-source ID
Action code: X'01'
Explanation: An unsupported media-source ID is
specified in an XOH Select Input Media Source command
or in a Load Copy Control command.
Alternate Exception Action: Select an installed and
available media source.
Page Continuation Action: None
Support: Mandatory
02C8..02 Invalid or unsupported internal or external
value in a Load Equivalence command
Action code: X'01'
Explanation: The internal or external value in an LE
command is invalid or unsupported.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
02D0..00 No metadata at given level to delete
Action code: X'01'
Explanation: A DHM is received and specifies to delete
metadata at a specific level, but there is no existing
metadata at that level.
Alternate Exception Action: Skip the command.
Page Continuation Action: Skip the command.
Support: Mandatory when metadata objects are
supported
02C3..01 • 02D0..00


02D0..01 Invalid metadata level specified
Action code: X'01'
Explanation: A WMC is received in home state but the
MDLevel is specified as X'0000'. This value is only used
when deleting home-state metadata.
Alternate Exception Action: Skip to END command.
Page Continuation Action: Skip to END command.
Support: Mandatory when metadata objects are
supported
02FF ..02 Synchronous exceptions not queued
Action code: X'01'
Explanation: So many synchronous exceptions have
been detected that the printer has run out of storage space
to save and return all of them to the host.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
02D0..01 • 02FF ..02


Conditions Requiring Host Notification
A condition requiring host notification indicates that
the printer has detected a condition that, while not an
error, should be reported to the host. There are no
AEAs for this class of exception.
Format 2 is used for most conditions requiring host
notification; format 0 is used for X'010A..00' and
X'0115..00', and format 8 is used for X'017E..00'.
0100..00 Normal Printer Restart [IPDS-16-1085]
Action code: X'0D'
Explanation: One or more of the following conditions
exists:
• The printer is IMLed in a normal manner. [IPDS-16-1086]
• The printer is switched from Offline to Online State. [IPDS-16-1087]
• The channel issues a System_Reset (applies only to [IPDS-16-1088]
channel-attached printers).
• The channel issues a Selective_Reset (applies only to [IPDS-16-1089]
channel-attached printers).
• An IPDS dialog ends and the printer resets the IPDS [IPDS-16-1090]
state machine and deletes all resources. This occurs
when an IPDS dialog ends and the printer needs to use
some of the resources, such as storage, that have been
allocated to the IPDS dialog.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
0101..00 Media-source characteristics changed [IPDS-16-1091]
Action code: X'1D'
Explanation: One or more of the following conditions
exists that change the Printable-Area self-defining field
within an XOH-OPC reply:
• The media source ID of one or more of the installed [IPDS-16-1092]
media sources is changed.
• The size of the medium presentation space in one or [IPDS-16-1093]
more of the installed media sources is changed.
• The size or offset of the physical printable area in one or [IPDS-16-1094]
more of the installed media sources is changed.
• One or more of the media-source-characteristics flags for [IPDS-16-1095]
one or more of the installed media sources is changed.
• The media identification of one or more of the installed [IPDS-16-1096]
media sources is changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0102..00 MICR printing status changed [IPDS-16-1097]
Action code: X'1D'
Explanation: MICR printing is now available.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0103..00 Burster-trimmer-stacker/cutter-trimmer- [IPDS-16-1098]
stacker status changed
Action code: X'1D'
Explanation: Burster-trimmer-stacker or cutter-trimmer-
stacker status has changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0104..00 Medium modification availability has [IPDS-16-1099]
changed
Action code: X'1D'
Explanation: The availability of one or more medium
modification functions has changed, and all medium
modifications have been inhibited.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: When a post-processing device is involved, the post-
processing device does the inhibiting.
0105..00 Media-destination status changed [IPDS-16-1100]
Action code: X'1D'
Explanation: The status of at least one media-destination
has changed. Either a media-destination ID has become
available (supported) or has become unavailable (not
supported).
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
0106..00 Device resolution changed [IPDS-16-1101]
Action code: X'1D'
Explanation: The device resolution has changed.
Previously activated resources might not print correctly.
Alternate Exception Action: None
Page Continuation Action: None
0100..00 • 0106..00 [IPDS-16-1102]


Support: Mandatory
0108..00 Printer setup changed [IPDS-16-1103]
Action code: X'1D'
Explanation: One or more printer or post-processor
setups have changed.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if setups are reported in the XOH-
OPC reply.
0109..00 Supported finishing operations changed [IPDS-16-1104]
Action code: X'1D'
Explanation: One or more finishing operations or finishing
options have been enabled or disabled. Currently
supported operations are listed in the XOH-OPC Finishing
Operations self-defining field (X'0018') and currently
supported options are listed in the XOH-OPC Finishing
Options self-defining field (X'0028'). In addition, when the
UP
3I interface is enabled in the printer, the XOH-OPC reply
will contain UP3I Tupel self-defining fields (X'0019') and
UP3I Paper Input Media self-defining fields (X'001A') that
describe UP3I devices that can also support finishing
operations.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
010A..00 Active setup name changed
Action code: X'1D'
Explanation: The active setup name has changed, in one
of the following ways:
1. A change from one active setup name to another [IPDS-16-1105]
active setup name
2. A change from no active setup name to an active [IPDS-16-1106]
setup name
3. A change from an active setup name to no active [IPDS-16-1107]
setup name
4. A change from an active setup name to the same [IPDS-16-1108]
active setup name but the settings corresponding to
the setup name have been modified, for example if an
operator edits the active setup name’s definition
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the ASN command is supported
Notes:
1. This exception ID uses sense format 0 so that sense [IPDS-16-1109]
bytes 16–17 can contain the explanation number of
the specific case of the active-setup-name change.
2. Some of the format 0 sense byte information that is [IPDS-16-1110]
typically returned with synchronous NACKs is not
available when the error is detected asynchronously.
010D..00 Text decryption status changed
Action code: X'1D'
Explanation: PTOCA text decryption capability has
changed. This capability is specified in the Available
Features self-defining field of the XOH-OPC reply.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if text decryption is supported
0110..00 Print Position Adjustment [IPDS-16-1111]
Action code: X'1A'
Explanation: An operator adjustment is taking place or
has just taken place.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: For some printers, reporting of this exception is
controlled by the Operator-Directed Recovery bit in
the XOA Exception-Handling Control command.
0111..00 Buffered pages discarded [IPDS-16-1112]
Action code: X'1A'
Explanation: The printer has discarded buffered pages
due to an internal printer operation.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
0113..00 IPDS Trace full [IPDS-16-1113]
Action code: X'1A'
Explanation: All of the available storage for an IPDS trace
has been used and a Trace Full entry has been added as
the last trace entry.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
0114..00 Asynchronous decompression error [IPDS-16-1114]
Action code: X'09'
Explanation: The printer has detected a data-related
decompression error on a page that is between the
received page station and the committed page station.
Incorrectly compressed JPEG image data within a data
object or an IOCA image can cause this exception.
0108..00 • 0114..00 [IPDS-16-1115]


The printer must finish committing prior sheets (if any),
discard the pages of the error sheet, and discard all
upstream data before reporting this NACK.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: This exception ID is appropriate when the printer can
automatically remove blank or error pages and
operator intervention is not required. In other cases
(where operator intervention is necessary),
X'4014..00' should be used.
0115..00 Asynchronous color-related error [IPDS-16-1116]
Action code: X'09'
Explanation: The printer has detected a color-related
error on a page that is between the received page station
and the committed page station. The error is not detected
when the IPDS commands for a presentation object were
parsed, but is detected by the object processor before the
page is committed. For example, a color value might have
been requested within the object data that is not available
to the printer.
The printer must finish committing prior sheets (if any),
discard the pages of the error sheet, and discard all
upstream data before reporting this NACK.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Notes:
1. This exception ID uses sense format 0 so that sense [IPDS-16-1117]
bytes 16–17 can contain an object-specific error code;
refer to “Error Codes for Other Data Objects” for a list of object-specific error codes. X'0000' in
sense bytes 16–17 indicates that no object-specific
error code has been provided.
2. Some of the format 0 sense byte information that is [IPDS-16-1118]
typically returned with synchronous NACKs is not
available when the error is detected asynchronously.
For exception ID X'0115..00', when the Overlay ID,
Page Segment ID, Command ID, or ID of other object
is not available, X'0000' is returned in that field.
3. For printers that support color fidelity control, reporting [IPDS-16-1119]
of this exception can be controlled by the Color Fidelity
(X'75') triplet in the PFC command.
0120..00 Colorant information changed [IPDS-16-1120]
Action code: X'1D'
Explanation: One or more colorants has become
available, has become unavailable, or has changed a
colorant-availability flag.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if indexed CMRs are supported.
0140..00 Printer paused in the middle of a recovery- [IPDS-16-1121]
unit group
Action code: X'1B'
Explanation: The Keep-Group-Together-as-a-Recovery-
Unit operation was active for a group of pages and the
printer paused in the middle of the group such that blank
sheets have occurred. The printer has discarded all pages
that had not yet reached the jam-recovery point; this allows
the presentation services program to reposition to the
beginning of a group.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Keep-Group-Together-as-a-
Recovery-Unit operation is supported.
0140..00 Printer paused in the middle of a [IPDS-16-1122]
suspended recovery-unit group
Action code: X'2B'
Explanation: The Keep-Group-Together-as-a-Recovery-
Unit operation was requested, but suspended for a group
of pages and the printer paused in the middle of the group
such that blank sheets have occurred. The printer has
discarded all pages that had not yet reached the jam-
recovery point; this allows the presentation services
program to reposition to the jam recovery station plus one,
but since the group was suspended, this might not be the
beginning of a group.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Keep-Group-Together-as-a-
Recovery-Unit operation is supported.
0141..00 Too many pages were sent for a recovery- [IPDS-16-1123]
unit group
Action code: X'06'
Explanation: The Keep-Group-Together-as-a-Recovery-
Unit operation was active for a group of pages and the
printer received more pages than the printer can keep
together as a recovery unit. In this case, the group will
continue, but the operation will be suspended for this group
and blank sheets might occur within this group in which
case exception ID X'4040..00' or X'0140..00' exists. The
operation will resume with subsequent recovery-unit
groups that do begin on a sheet boundary. The OPC reply
Keep-Group-Together-as-a-Recovery-Unit self-defining
field identifies the maximum number of sheets allowed
within a recovery-unit group; these sheets include sheets
containing pages and copies of such sheets.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory if the Keep-Group-Together-as-a-
Recovery-Unit operation is supported.
0115..00 • 0141..00 [IPDS-16-1124]


017E..00 Condition requiring host notification on a
UP3I-controlled device
Action code: X'09', X'0A', X'1A', or X'1D'
Explanation: A UP3I-controlled pre-processing or post-
processing device attached to the printer has reported a
condition requiring host notification. The specific error is
identified in the sense bytes 8–9.
For action code X'09', the host should end the print unit at
the committed-page station plus 1.
This exception ID uses sense-byte format 8.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory, if the printer supports the UP 3I
interface
Note: For more information about the error code in sense
bytes 8–9, refer to the description of the device-
specific-Error-ID field in the State Description (UP 3I
value X'05') triplet in the UP 3I Specification that is
available at www.afpcinc.org.
0180..00 Request to end IPDS dialog [IPDS-16-1125]
Action code: X'05'
Explanation: The printer has received a request to print
from another session and asks the presentation services
program to end the current IPDS dialog as soon as
possible, such as at the end of the current print unit.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: Although the printer is asking that the IPDS dialog be
ended (and presumably the session not ended), the
presentation services program can also end the
carrying-protocol session if appropriate.
018F ..00 Error Printer Restart
Action code: X'0D'
Explanation: The printer has just been IMLed as a result
of a printer-detected error.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
01E4..00 Cancel key pressed
Action code: X'15'
Explanation: The Cancel key on the printer operator
panel is pressed while the printer is receiving data.
Alternate Exception Action: None
Page Continuation Action: None
Support: Mandatory
Note: Some printers report this exception as X'40E4..00'.
The preferred Exception ID is X'01E4..00'.
01E8..00 Pre/post-processor Device Overrun
Action code: X'1A'
Explanation: A device that has been externally connected
to the printer to allow pre-processing or post-processing of
the media has been overloaded with requests or with input.
Alternate Exception Action: None
Page Continuation Action: None
Support: Optional
Note: For some printers, reporting of this exception is
controlled by the Operator-Directed Recovery bit in
the XOA Exception-Handling Control command.
017E..00 • 01E8..00


Data Object Error Codes
Error Codes for Anacomp and AnaStack Objects
None
Error Codes for Color Mapping Table and Color Profile Objects
None
Error Codes for IO-Image Objects and IOCA Tile Resources
None: all errors for IOCA images and IOCA secondary resources are reported with sense data as described
in “Specification Checks—IO-Image Exceptions” .
Error Codes for Other Data Objects
The error codes in the following table are contained in sense bytes 16–17 of exception IDs X'020D..01',
X'020D..05', and X'0115..00'.
Table 65. Error Codes for Data Objects [IPDS-16-1126]

| Error Number | Explanation |
| :--- | :--- |
| 0 X'0000' | No error code provided [IPDS-16-1127]|
| **PostScript Object Errors** [IPDS-16-1128]| |
| 30 X'001E' | PostScript Object Error: Setpagedevice or setdevparams request cannot be satisfied [IPDS-16-1129]|
| 31 X'001F' | PostScript Object Error: Dictionary has no more room in it to store entry [IPDS-16-1130]|
| 32 X'0020' | PostScript Object Error: Too many begin operators detected [IPDS-16-1131]|
| 33 X'0021' | PostScript Object Error: Too many end operators detected [IPDS-16-1132]|
| 34 X'0022' | PostScript Object Error: Executive stack nesting too deep [IPDS-16-1133]|
| 35 X'0023' | PostScript Object Error: External interrupt request detected [IPDS-16-1134]|
| 36 X'0024' | PostScript Object Error: Attempt to violate access attribute [IPDS-16-1135]|
| 37 X'0025' | PostScript Object Error: Operator exit was not found in loop context [IPDS-16-1136]|
| 38 X'0026' | PostScript Object Error: Unacceptable access string [IPDS-16-1137]|
| 39 X'0027' | PostScript Object Error: Invalid font resource name or font or CIDFont dictionary [IPDS-16-1138]|
| 40 X'0028' | PostScript Object Error: Improper restore has been detected [IPDS-16-1139]|
| 41 X'0029' | PostScript Object Error: Input/output error has been detected [IPDS-16-1140]|
| 42 X'002A' | PostScript Object Error: Implementation limit has been exceeded [IPDS-16-1141]|
| 43 X'002B' | PostScript Object Error: The current point undefined [IPDS-16-1142]|
| 44 X'002C' | PostScript Object Error: An operator's operand is out of bounds [IPDS-16-1143]|
| 45 X'002D' | PostScript Object Error: An operand stack overflow has been detected [IPDS-16-1144]|
| 46 X'002E' | PostScript Object Error: An operand stack underflow has been detected [IPDS-16-1145]|
| 47 X'002F' | PostScript Object Error: A PostScript language syntax error has been detected [IPDS-16-1146]|
| 48 X'0030' | PostScript Object Error: Object processing time limit has been exceeded [IPDS-16-1147]|
| 49 X'0031' | PostScript Object Error: An operator's operand has been detected as the wrong type [IPDS-16-1148]|
| 50 X'0032' | PostScript Object Error: A name used in object is not known to the interpreter [IPDS-16-1149]|
| 51 X'0033' | PostScript Object Error: Filename used in object was not found by the interpreter [IPDS-16-1150]|
| 52 X'0034' | PostScript Object Error: Resource used in object was not found by the interpreter [IPDS-16-1151]|
| 53 X'0035' | PostScript Object Error: An overflow, underflow, or meaningless result occurred [IPDS-16-1152]|
| 54 X'0036' | PostScript Object Error: Interpreter expected a mark on the stack; none was found [IPDS-16-1153]|
| 55 X'0037' | PostScript Object Error: Internal error occurred within the interpreter [IPDS-16-1154]|
| 56 X'0038' | PostScript Object Error: The interpreter's virtual memory has been exhausted [IPDS-16-1155]|
| 57 X'0039' | PostScript Object Error: No output generated from EPS/PDF; check input data [IPDS-16-1156]|
| **PDF Object Errors** [IPDS-16-1157]| |
| 80 X'0050' | PDF Object Error: Failure to open a secure PDF document [IPDS-16-1158]|
| 81 X'0051' | PDF Object Error: General failure to convert PDF to PostScript code [IPDS-16-1159]|
| 82 X'0052' | PDF Object Error: Failure to enumerate fonts contained in PDF document [IPDS-16-1160]|
| 83 X'0053' | PDF Object Error: Failure to open PDF document [IPDS-16-1161]|
| **TrueType/OpenType Object Errors** | Error codes in the range X'0100'–X'01FF' can apply to either TrueType/OpenType fonts or TrueType/OpenType collections. For these codes, the rightmost 4 bits contains the collection index value plus one. A value of X'0' indicates either that the object was a font or indicates that the collection index is not provided (because it is greater than 14). [IPDS-16-1162]|
| 256–271 X'0100'–X'010F' | TrueType/OpenType Object Error: File read failure detected by the font rasterizer [IPDS-16-1163]|
| 272–287 X'0110'–X'011F' | TrueType/OpenType Object Error: Font rasterizer was unable to understand (parse) the data in a font [IPDS-16-1164]|
| 288–303 X'0120'–X'012F' | TrueType/OpenType Object Error: Font rasterizer failed to gather a font's font-level metrics [IPDS-16-1165]|
| 304–319 X'0130'–X'013F' | TrueType/OpenType Object Error: The first four bytes of a font are invalid [IPDS-16-1166]|
| 320–335 X'0140'–X'014F' | TrueType/OpenType Object Error: The printer control unit failed to read a portion of a font that should be present [IPDS-16-1167]|
| 336–351 X'0150'–X'015F' | TrueType/OpenType Object Error: The printer control unit unexpectedly reached end of file while reading a font [IPDS-16-1168]|
| 352–367 X'0160'–X'016F' | TrueType/OpenType Object Error: The first four bytes of a collection are invalid [IPDS-16-1169]|
| 368–383 X'0170'–X'017F' | TrueType/OpenType Object Error: The required format 4 cmap is missing from a font [IPDS-16-1170]|
| 384–399 X'0180'–X'018F' | TrueType/OpenType Object Error: An error that the printer control unit is not currently prepared to handle has occurred [IPDS-16-1171]|
| **TIFF Object Errors (X'0200'–X'02FF')** [IPDS-16-1172]| |
| 512 X'0200' | TIFF Object Error: An internal error was encountered while processing the image [IPDS-16-1173]|
| 528 X'0210' | TIFF Object Error: Object contains invalid controls; see note 1 [IPDS-16-1174]|
| 544 X'0220' | TIFF Object Error: Object contains invalid image data; see note 2 [IPDS-16-1175]|
| 560 X'0230' | TIFF Object Error: Object contains unsupported image [IPDS-16-1176]|
| 576 X'0240' | TIFF Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1177]|
| **JPEG Object Errors (X'0300'–X'03FF')** [IPDS-16-1178]| |
| 768 X'0300' | JPEG Object Error: An internal error was encountered while processing the image [IPDS-16-1179]|
| 784 X'0310' | JPEG Object Error: Object contains invalid controls; see note 1 [IPDS-16-1180]|
| 800 X'0320' | JPEG Object Error: Object contains invalid image data; see note 2 [IPDS-16-1181]|
| 816 X'0330' | JPEG Object Error: Object contains unsupported image [IPDS-16-1182]|
| 832 X'0340' | JPEG Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1183]|
| **JPEG2000 Object Errors (X'0400'–X'04FF')** [IPDS-16-1184]| |
| 1024 X'0400' | JPEG2000 Object Error: An internal error was encountered while processing the image [IPDS-16-1185]|
| 1040 X'0410' | JPEG2000 Object Error: Object contains invalid controls; see note 1 [IPDS-16-1186]|
| 1056 X'0420' | JPEG2000 Object Error: Object contains invalid image data; see note 2 [IPDS-16-1187]|
| 1072 X'0430' | JPEG2000 Object Error: Object contains unsupported image [IPDS-16-1188]|
| 1088 X'0440' | JPEG2000 Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1189]|
| **GIF Object Errors (X'0500'–X'05FF')** [IPDS-16-1190]| |
| 1280 X'0500' | GIF Object Error: An internal error was encountered while processing the image [IPDS-16-1191]|
| 1296 X'0510' | GIF Object Error: Object contains invalid controls; see note 1 [IPDS-16-1192]|
| 1312 X'0520' | GIF Object Error: Object contains invalid image data; see note 2 [IPDS-16-1193]|
| 1328 X'0530' | GIF Object Error: Object contains unsupported image [IPDS-16-1194]|
| 1344 X'0540' | GIF Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1195]|
| **PNG Object Errors (X'0600'–X'06FF')** [IPDS-16-1196]| |
| 1536 X'0600' | PNG Object Error: An internal error was encountered while processing the image [IPDS-16-1197]|
| 1552 X'0610' | PNG Object Error: Object contains invalid controls; see note 1 [IPDS-16-1198]|
| 1568 X'0620' | PNG Object Error: Object contains invalid image data; see note 2 [IPDS-16-1199]|
| 1584 X'0630' | PNG Object Error: Object contains unsupported image [IPDS-16-1200]|
| 1600 X'0640' | PNG Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1201]|
| **AFP SVG Subset Object Errors (X'0700'–X'07FF')** [IPDS-16-1202]| |
| 1792 X'0700' | SVG Object Error: An internal error was encountered while processing the vector image [IPDS-16-1203]|
| 1808 X'0710' | SVG Object Error: Error decompressing object [IPDS-16-1204]|
| 1824 X'0720' | SVG Object Error: Content does not conform to the XML specification [IPDS-16-1205]|
| 1840 X'0730' | SVG Object Error: Element or attribute encountered that is not part of the SVG DTD and that is not properly identified as being part of another namespace [IPDS-16-1206]|
| 1856 X'0740' | SVG Object Error: Element has an attribute or property value that is not permissible according to the SVG specification [IPDS-16-1207]|
| 1872 X'0750' | SVG Object Error: Situations that are described as being “in error” in the SVG specification [IPDS-16-1208]|
| 1888 X'0760' | SVG Object Error: Object contains unsupported vector image [IPDS-16-1209]|
| 1904 X'0770' | SVG Object Error: Vector image in the object exceeds the capabilities of the receiver; see note 3 [IPDS-16-1210]|
Notes:
1. Examples of invalid controls include: [IPDS-16-1211]
• Missing TIFF tag [IPDS-16-1212]
• Invalid or inconsistent TIFF tags [IPDS-16-1213]
• Invalid TIFF signature [IPDS-16-1214]
• Could not process data, or have to make assumptions [IPDS-16-1215]
• Required JPEG marker is missing [IPDS-16-1216]
• JPEG marker is invalid [IPDS-16-1217]
• JPEG markers are inconsistent [IPDS-16-1218]
• Invalid GIF signature [IPDS-16-1219]
• Invalid or missing GIF image descriptor [IPDS-16-1220]
• Invalid PNG signature [IPDS-16-1221]
• Missing PNG critical chunk [IPDS-16-1222]
2. Examples of invalid image data include: [IPDS-16-1223]
• Decompression failure [IPDS-16-1224]
• Data outside of range [IPDS-16-1225]
3. Examples of capability-exceeded errors include: [IPDS-16-1226]
• Image too large [IPDS-16-1227]
• Processing requires too much memory [IPDS-16-1228]


Page and Copy Counter Adjustments
To identify which pages to send to the printer after an exception occurs, the host must determine the position of
pages and copies of pages in the logical paper path. Various stations in the logical paper path are identified by
the page and copy counters.
Page and copy counter fields in an Acknowledge Reply identify the state of the logical paper path that includes
counts of the number of pages received, the number of pages committed for printing, the number of pages that
have passed the operator viewing point, the number of pages that have passed the jam-recovery point, and the
number of pages stacked. In addition, the committed, operator viewing, jam recovery, and stacked page
counters have matching copy counters.
All counters represent complete pages and copies and are always adjusted on page or copy boundaries. The
received page counter represents the last received page. The other page counters identify the last page
placed on the sheet at that counter station.
Table 66. Method of Adjusting the Counters [IPDS-16-1229]

| Condition | Counters | Counter Adjustments |
| :--- | :--- | :--- |
| Action codes X'01', X'19', X'1F' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Refer to “Page and Copy Counter Adjustments When a Data-Stream Exception Occurs”. [IPDS-16-1230]|
| Action codes X'05', X'06' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change [IPDS-16-1231]|
| Action code X'08' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Jam Recovery Page Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>No change<br>No change<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter [IPDS-16-1232]|
| Action codes X'09', X'15', X'16', X'17', X'1A', X'1D', X'23' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change [IPDS-16-1233]|
| Action code X'0A'<br>X'1B'<br>X'2B' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Jam Recovery Page Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>No change<br>No change<br>No change<br>No change [IPDS-16-1234]|
| Action code X'0C' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | No change (Should not be incremented for page in error; that is, no partial page should be created.)<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change [IPDS-16-1235]|
| Action code X'0D' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0 [IPDS-16-1236]|
| Action code X'1E' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter unless duplexing is active for the page in which the exception occurs and the page that caused the exception is on the back side of a duplex sheet. In this case, the back-side pages are discarded and the Received Page Counter is set to the Committed Page Counter plus the number of pages on the front side. The host must issue an XOH-PBD command to ensure that the counters are accurately adjusted.<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change [IPDS-16-1237]|
| Action code X'22' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer [IPDS-16-1238]|
| XOA Discard Buffered Data command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change [IPDS-16-1239]|
| XOA Discard Unstacked Pages command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Stacked Page Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>No change<br>No change [IPDS-16-1240]|
| Normal counter wrap (on a per-counter basis) | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0 [IPDS-16-1241]|
| XOH Page Counters Control command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Refer to “XOH Page Counters Control”. [IPDS-16-1242]|


Page and Copy Counter Adjustments When a Data-Stream Exception
Occurs
Table 67. Method of Adjusting the Counters When a Data-Stream Exception Occurs [IPDS-16-1243]

| Condition | Counters | Counter Adjustments |
| :--- | :--- | :--- |
| Action code X'01', X'1F', or X'19' and the page is printed.<br><br>For action code X'19', the host must issue an XOH-PBD command to ensure that the counters are accurately adjusted. | Received Page Counter | Reflects the last page received from the host, unless the error occurred on the last page on a sheet. The received page counter is incremented for the last page on a sheet after all copy subgroups are processed for all pages on the sheet. [IPDS-16-1244]|
| | Committed Page Counter | No Change [IPDS-16-1245]|
| | Committed Copy Counter | Reflects any committed copies resulting from prior copy subgroups. If the error occurred in the last page on the sheet, reflects committed copies from the copy subgroup in error. Since no copies have been discarded, additional copies might also be buffered between the received page station and the committed page station. [IPDS-16-1246]|
| | Operator Viewing Page Counter | No Change [IPDS-16-1247]|
| | Operator Viewing Copy Counter | No Change [IPDS-16-1248]|
| | Jam Recovery Page Counter | No Change [IPDS-16-1249]|
| | Jam Recovery Copy Counter | No Change [IPDS-16-1250]|
| | Stacked Page Counter | No Change [IPDS-16-1251]|
| | Stacked Copy Counter | No Change [IPDS-16-1252]|
| Action code X'01', or X'1F', and the page is not printed. | Received Page Counter | If a synchronous data stream exception occurred in the first copy subgroup (or, if duplexing, the second copy subgroup), the received page counter includes all received pages prior to the error page. If a synchronous data stream exception occurred in a subsequent copy subgroup, the received page counter includes all but the last page on the sheet. [IPDS-16-1253]|
| | Committed Page Counter | No Change [IPDS-16-1254]|
| | Committed Copy Counter | Reflects any committed copies resulting from prior error-free copy subgroups. Since prior error-free copies have not been discarded, additional copies might also be buffered between the received page station and the committed page station. [IPDS-16-1255]|
| | Operator Viewing Page Counter | No Change [IPDS-16-1256]|
| | Operator Viewing Copy Counter | No Change [IPDS-16-1257]|
| | Jam Recovery Page Counter | No Change [IPDS-16-1258]|
| | Jam Recovery Copy Counter | No Change [IPDS-16-1259]|
| | Stacked Page Counter | No Change [IPDS-16-1260]|
| | Stacked Copy Counter | No Change [IPDS-16-1261]|
| Action code X'19' and the page is not printed.<br><br>For action code X'19', the host must issue an XOH-PBD command to ensure that the counters are accurately adjusted. | Received Page Counter | If an asynchronous data stream exception occurred and if there were any error free copy subgroups committed, the received page counter reflects all of the pages on the sheet. If there were no previous error free copy subgroups, it reflects none of the pages on the sheet. [IPDS-16-1262]|
| | Committed Page Counter | No Change [IPDS-16-1263]|
| | Committed Copy Counter | Reflects any committed copies resulting from prior error-free copy subgroups. Since prior error-free copies have not been discarded, additional copies might also be buffered between the received page station and the committed page station. [IPDS-16-1264]|
| | Operator Viewing Page Counter | No Change [IPDS-16-1265]|
| | Operator Viewing Copy Counter | No Change [IPDS-16-1266]|
| | Jam Recovery Page Counter | No Change [IPDS-16-1267]|
| | Jam Recovery Copy Counter | No Change [IPDS-16-1268]|
| | Stacked Page Counter | No Change [IPDS-16-1269]|
| | Stacked Copy Counter | No Change [IPDS-16-1270]|


Page Counter Scenarios
Introduction
The following page-counter scenarios reflect page-counter implementation on a hypothetical IPDS printer and
are not meant to resemble any IPDS printer or to be rules of IPDS page-counter implementation. For simplicity,
the operator viewing and jam recovery page counters are not included in these scenarios.
Scenario 1
The host sends down an LCC specifying four copy subgroups, one copy of each page, and simplex printing.
The host then sends one page (BP–EP sequence) with no errors of any kind.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
0 0 0 0 0 [IPDS-16-1271]
Process page 1 through copy
subgroup 2
0 0 0 0 0 [IPDS-16-1272]
Process page 1 through copy
subgroup 3
0 0 0 0 0 [IPDS-16-1273]
Process page 1 through copy
subgroup 4
1 0 0 0 0 [IPDS-16-1274]
Receive status that page 1
through copy subgroup 1 has
been committed
1 0 1 0 0 [IPDS-16-1275]
Receive status that page 1
through copy subgroup 2 has
been committed
1 0 2 0 0 [IPDS-16-1276]
Receive status that page 1
through copy subgroup 3 has
been committed
1 0 3 0 0 [IPDS-16-1277]
Receive status that page 1
through copy subgroup 4 has
been committed
1 1 0 0 0 [IPDS-16-1278]
Receive status that page 1
through copy subgroup 1 has
been stacked
1 1 0 0 1 [IPDS-16-1279]
Receive status that page 1
through copy subgroup 2 has
been stacked
1 1 0 0 2 [IPDS-16-1280]
Receive status that page 1
through copy subgroup 3 has
been stacked
1 1 0 0 3 [IPDS-16-1281]
Receive status that page 1
through copy subgroup 4 has
been stacked
1 1 0 1 0 [IPDS-16-1282]


Scenario 2
The host sends down an LCC specifying two copy subgroups, one copy of each page, and duplex printing. The
host then sends two pages (BP–EP sequences) with no errors of any kind on any of the pages.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1283]
Process page 2 through copy
subgroup 2
2 0 0 0 0 [IPDS-16-1284]
Receive status that page 1
through copy subgroup 1 has
been committed
2 0 0 0 0 [IPDS-16-1285]
Receive status that page 2
through copy subgroup 2 has
been committed
2 2 0 0 0 [IPDS-16-1286]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1287]
2 have been stacked [IPDS-16-1288]
2 2 0 2 0 [IPDS-16-1289]
Scenario 3
The host sends down an LCC specifying two copy subgroups, one copy of each page, and duplex printing. The
host then sends two pages (BP–EP sequences). There is a synchronous (action code X'01') error on the front
side of the duplex sheet. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1 and get
synchronous (action code X'01')
error
0 0 0 0 0 [IPDS-16-1290]
Discard all data associated with
this and all subsequent copy
subgroups, enter home state,
discard all upstream data, and
return NACK
0 0 0 0 0 [IPDS-16-1291]
After processing XOH PBD with
ARQ
0 0 0 0 0 [IPDS-16-1292]
After processing XOA DBD with
ARQ
0 0 0 0 0 [IPDS-16-1293]


Scenario 4
The host sends down an LCC specifying two copy subgroups, one copy of each page, and duplex printing. The
host then sends two pages (BP–EP sequences). There is a synchronous (action code X'01') error on the back
side of the duplex sheet. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1294]
Process page 2 through copy
subgroup 2 and get
synchronous (action code X'01')
error
1 0 0 0 0 [IPDS-16-1295]
Discard all data associated with
this and all subsequent copy
subgroups, enter home state,
discard all upstream data, and
return NACK
1 0 0 0 0 [IPDS-16-1296]
After processing XOH PBD with
ARQ
1 0 0 0 0 [IPDS-16-1297]
Receive error free replacement
page 2 and process through
copy subgroup 2
2 0 0 0 0 [IPDS-16-1298]
Receive status that page 1
through copy subgroup 1 and
replacement page 2 through
copy subgroup 2 have been
committed
2 2 0 0 0 [IPDS-16-1299]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1300]
2 have been stacked [IPDS-16-1301]
2 2 0 2 0 [IPDS-16-1302]


Scenario 5
The host sends down an LCC specifying four copy subgroups, one copy of each page, and duplex printing.
The host then sends two pages (BP–EP sequences). There is a synchronous (action code X'01') error in copy
subgroup 4. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1303]
Process page 2 through copy
subgroup 2
1 0 0 0 0 [IPDS-16-1304]
Process page 1 through copy
subgroup 3
1 0 0 0 0 [IPDS-16-1305]
Process page 2 through copy
subgroup 4, get synchronous
(action code X'01') error
1 0 0 0 0 [IPDS-16-1306]
Discard all data associated with
this and all subsequent copy
subgroups, enter home state,
discard all upstream data, and
return NACK
1 0 0 0 0 [IPDS-16-1307]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1308]
2 have been committed [IPDS-16-1309]
1 0 2 0 0 [IPDS-16-1310]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1311]
2 have been stacked [IPDS-16-1312]
1 0 2 0 2 [IPDS-16-1313]
After processing XOH PBD with
ARQ
1 0 2 0 2 [IPDS-16-1314]
Receive error free replacement
page 2 for copy subgroup 4 and
process
2 0 2 0 2 [IPDS-16-1315]
Receive status that pages 1 and
2 through copy subgroups 3 and [IPDS-16-1316]
4 have been committed [IPDS-16-1317]
2 2 0 0 2 [IPDS-16-1318]
Receive status that pages 1 and
2 through copy subgroups 3 and [IPDS-16-1319]
4 have been stacked [IPDS-16-1320]
2 2 0 2 0 [IPDS-16-1321]


Scenario 6
The host sends down an LCC specifying two copy subgroups, one copy of each page, and duplex printing. The
host then sends two pages (BP–EP sequences). There is an asynchronous (action code X'19') error on the
front side of the duplex sheet. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1322]
Process page 2 through copy
subgroup 2
2 0 0 0 0 [IPDS-16-1323]
Receive status of asynchronous
(action code X'19') error, discard all data
associated with this and all
subsequent copy subgroups,
enter home state, discard all
upstream data, and return
NACK
0 0 0 0 0 [IPDS-16-1324]
After processing XOH PBD with
ARQ
0 0 0 0 0 [IPDS-16-1325]
After processing XOA DBD with
ARQ
0 0 0 0 0 [IPDS-16-1326]
Scenario 7
The host sends down an LCC specifying two copy subgroups, one copy of each page, and duplex printing. The
host then sends two pages (BP–EP sequences). There is an asynchronous (action code X'19') error on the
back side of the duplex sheet. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1327]
Process page 2 through copy
subgroup 2
2 0 0 0 0 [IPDS-16-1328]
Receive status of asynchronous
(action code X'19') error, discard all data
associated with this and all
subsequent copy subgroups,
enter home state, discard all
upstream data, and return
NACK
1 0 0 0 0 [IPDS-16-1329]
After processing XOH PBD with
ARQ
1 0 0 0 0 [IPDS-16-1330]
After processing XOA DBD with
ARQ
0 0 0 0 0 [IPDS-16-1331]


Scenario 8
The host sends down an LCC specifying four copy subgroups, one copy of each page, and duplex printing.
The host then sends two pages (BP–EP sequences). There is an asynchronous (action code X'19') error in
copy subgroup 4. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1332]
Process page 2 through copy
subgroup 2
1 0 0 0 0 [IPDS-16-1333]
Process page 1 through copy
subgroup 3
1 0 0 0 0 [IPDS-16-1334]
Process page 2 through copy
subgroup 4
2 0 0 0 0 [IPDS-16-1335]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1336]
2 have been committed [IPDS-16-1337]
2 0 2 0 0 [IPDS-16-1338]
Receive status of asynchronous
(action code X'19') error through copy subgroup
4, discard all data associated
with this and all subsequent
copy subgroups, enter home
state, discard all upstream data,
and return NACK
1 0 2 0 0 [IPDS-16-1339]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1340]
2 have been stacked [IPDS-16-1341]
1 0 2 0 2 [IPDS-16-1342]
After processing XOH PBD with
ARQ
1 0 2 0 2 [IPDS-16-1343]
After processing XOA DBD with
ARQ
0 0 2 0 2 [IPDS-16-1344]
After processing XOH PCC with
X'01' specified in byte 2 and
ARQ
2 2 0 2 0 [IPDS-16-1345]


Scenario 9
The host sends down an LCC specifying four copy subgroups, one copy of each page, and simplex printing.
The host then sends one page (BP–EP sequence). There is a synchronous (action code X'01') error in copy
subgroup 4. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
0 0 0 0 0 [IPDS-16-1346]
Process page 1 through copy
subgroup 2
0 0 0 0 0 [IPDS-16-1347]
Process page 1 through copy
subgroup 3
0 0 0 0 0 [IPDS-16-1348]
Process page 1 through copy
subgroup 4, get synchronous
(action code X'01') error
0 0 0 0 0 [IPDS-16-1349]
Discard all data associated with
this and all subsequent copy
subgroups, enter home state,
discard all upstream data, and
return NACK
0 0 0 0 0 [IPDS-16-1350]
Receive status that page 1
through copy subgroup 1 has
been committed
0 0 1 0 0 [IPDS-16-1351]
Receive status that page 1
through copy subgroup 2 has
been committed
0 0 2 0 0 [IPDS-16-1352]
Receive status that page 1
through copy subgroup 3 has
been committed
0 0 3 0 0 [IPDS-16-1353]
Receive status that page 1
through copy subgroup 1 has
been stacked
0 0 3 0 1 [IPDS-16-1354]
Receive status that page 1
through copy subgroup 2 has
been stacked
0 0 3 0 2 [IPDS-16-1355]
Receive status that page 1
through copy subgroup 3 has
been stacked
0 0 3 0 3 [IPDS-16-1356]
After processing XOH PBD with
ARQ
0 0 3 0 3 [IPDS-16-1357]
Receive error-free replacement
page 1 for copy subgroup 4 and
process
1 0 3 0 3 [IPDS-16-1358]
Receive status that page 1
through copy subgroup 4 has
been committed
1 1 0 0 3 [IPDS-16-1359]
Receive status that page 1
through copy subgroup 4 has
been stacked
1 1 0 1 0 [IPDS-16-1360]


Scenario 10
The host sends down an LCC specifying four copy subgroups, one copy of each page, and simplex printing.
The host then sends one page (BP–EP sequence). There is an asynchronous (action code X'19') error in copy
subgroup 4. EHC is set to discard the page. The host does not desire to continue with the current LCC.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
0 0 0 0 0 [IPDS-16-1361]
Process page 1 through copy
subgroup 2
0 0 0 0 0 [IPDS-16-1362]
Process page 1 through copy
subgroup 3
0 0 0 0 0 [IPDS-16-1363]
Process page 1 through copy
subgroup 4
1 0 0 0 0 [IPDS-16-1364]
Receive status that page 1
through copy subgroup 1 has
been committed
1 0 1 0 0 [IPDS-16-1365]
Receive status that page 1
through copy subgroup 2 has
been committed
1 0 2 0 0 [IPDS-16-1366]
Receive status that page 1
through copy subgroup 3 has
been committed
1 0 3 0 0 [IPDS-16-1367]
Receive status that page 1
through copy subgroup 1 has
been stacked
1 0 3 0 1 [IPDS-16-1368]
Receive status of asynchronous
(action code X'19') error on copy
4, discard all data associated
with this and all subsequent
copy subgroups, enter home
state, discard upstream data,
and return NACK
0 0 3 0 1 [IPDS-16-1369]
Receive status that page 1
through copy subgroup 2 has
been stacked
0 0 3 0 2 [IPDS-16-1370]
Receive status that page 1
through copy subgroup 3 has
been stacked
0 0 3 0 3 [IPDS-16-1371]
After processing XOH PBD with
ARQ
0 0 3 0 3 [IPDS-16-1372]
After processing XOA DBD with
ARQ
0 0 3 0 3 [IPDS-16-1373]
After processing XOH PCC with
X'01' specified in byte 2 and
ARQ
1 1 0 1 0 [IPDS-16-1374]


Scenario 11
The host sends down an LCC specifying four copy subgroups, one copy of each page, and duplex printing.
The host then sends two pages (BP–EP sequences). There is an asynchronous (action code X'19') error in
page 2, copy subgroup 2. EHC is set to print the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1375]
Process page 2 through copy
subgroup 2
1 0 0 0 0 [IPDS-16-1376]
Process page 1 through copy
subgroup 3
1 0 0 0 0 [IPDS-16-1377]
Process page 2 through copy
subgroup 4
2 0 0 0 0 [IPDS-16-1378]
Receive status of asynchronous
(action code X'19') error, copy subgroup 2.
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1379]
2 have been committed, discard [IPDS-16-1380]
upstream data, and return
NACK
1 0 2 0 0 [IPDS-16-1381]
Receive status that pages 1 and
2 through copy subgroups 3 and [IPDS-16-1382]
4 have been committed [IPDS-16-1383]
2 2 0 0 0 [IPDS-16-1384]
After processing XOH PBD with
ARQ
2 2 0 0 0 [IPDS-16-1385]


Scenario 12
The host sends down an LCC specifying four copy subgroups, one copy of each page, and duplex printing.
The host then sends two pages (BP–EP sequences). There is an asynchronous (action code X'19') error in
page 1, copy subgroup 3. EHC is set to discard the page.
Action Received
Page
Committed
Page
Committed
Copy
Stacked Page Stacked Copy
Power on 0 0 0 0 0
Process page 1 through copy
subgroup 1
1 0 0 0 0 [IPDS-16-1386]
Process page 2 through copy
subgroup 2
1 0 0 0 0 [IPDS-16-1387]
Process page 1 through copy
subgroup 3
1 0 0 0 0 [IPDS-16-1388]
Process page 2 through copy
subgroup 4
2 0 0 0 0 [IPDS-16-1389]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1390]
2 have been committed [IPDS-16-1391]
2 0 2 0 0 [IPDS-16-1392]
Receive status of asynchronous
(action code X'19') error for
page 1, copy subgroup 3,
discard all data associated with
this and all subsequent copy
subgroups, enter home state,
discard upstream data, and
return NACK
0 0 2 0 0 [IPDS-16-1393]
Receive status that pages 1 and
2 through copy subgroups 1 and [IPDS-16-1394]
2 have been stacked [IPDS-16-1395]
0 0 2 0 2 [IPDS-16-1396]
After processing XOH PBD with
ARQ
0 0 2 0 2 [IPDS-16-1397]
After processing XOH DBD with
ARQ
0 0 2 0 2 [IPDS-16-1398]


Non-IPDS Sense Data
While most sense data is returned by printers within an IPDS Negative Acknowledge Reply (NACK), there is
additional sense data returned at other levels of the communications protocol. For example, when an IPDS
printer is attached to the host computer via a TCP/IP or channel connection, sense data is returned at the TCP/
IP or channel link level. Also, sense data was defined for the IBM 3800 printer that is not currently used within
IPDS printers; but since much of the design for the IPDS architecture is based on the 3800 printer, these sense
data codes are reserved for possible future use.
To ensure that each exception ID is unique for all types of printer sense data, the link level sense codes are
defined in the same way as is IPDS sense data and are listed as retired codes so that they are not
inadvertently used as IPDS sense data. This section lists all of the defined printer sense data that is not used
within IPDS NACKs.
Note: Most exception ID and action code combinations in this section are retired in IPDS for use by
transmission protocols (such as TCP/IP and channel) that carry IPDS commands; they are not used
within IPDS NACKs. These are identified by a reference to the carrying protocols; other reasons to retire
sense data are identified in the description of each exception ID.
Non-IPDS Action Codes
Action codes classify the exception to assist host-exception recovery and allow printing to continue.
The following action codes are valid; however, a specific printer will return only some of the codes. Refer to
your printer documentation for the list of non-IPDS action codes used by your printer.
For each action code, a suggested host recovery action is provided. For the description of a particular host-
program implementation, refer to your host-program documentation.
Table 68. Retired Non-IPDS Action Codes [IPDS-16-1399]

| Action Code | Exception Recovery Action |
| :--- | :--- |
| X'00' | **No error outstanding**<br>Retired item 83; used in channel-attached printers<br>Software should redrive the channel with the failing CCW. This action code is specified if the printer operator clears a temporary intervention required condition and readies the printer between the time a Unit Check is posted and the Basic Sense command is received by the host. The printer was in the ready state when this action code was generated. [IPDS-16-1400]|
| X'02' | **Operator intervention with OBR record**<br>Retired item 84; used in channel-attached and TCP/IP-attached printers<br>An operator intervention condition has occurred that requires an OBR record. Supply a system operator message indicating that operator intervention is required and generate an OBR record. The printer was in the not ready state when this action code was generated. After the printer is made ready, restart the channel program with the failing CCW (or restart the flow of TCP/IP data). [IPDS-16-1401]|
| X'03' | **Operator intervention without OBR record**<br>Retired item 85; used in channel-attached and TCP/IP-attached printers<br>An operator intervention condition has occurred that does not require an OBR record. Supply a system operator message indicating that operator intervention is required. The printer was in the not ready state when this action code was generated. After the printer is made ready, restart the channel program with the failing CCW (or restart the flow of TCP/IP data). [IPDS-16-1402]|
| X'04' | **Channel error**<br>Retired item 86; used in channel-attached printers<br>A channel error has occurred. Generate an OBR record and retry the operation at least once. If retry fails, provide a system operator message that shows an unrecoverable error has occurred and notify presentation software. The printer might be in either the ready or not ready state when this action code is received. [IPDS-16-1403]|
| X'07' | **Retry Error Log full**<br>Retired item 99; used in 3800-3,6,8 printers<br>Software should retrieve the retry error log entries with a Sense Error Log CCW, generate an MDR record to save the retry error log information, and restart the channel program with the failing CCW. [IPDS-16-1404]|
| X'0B' | **Process power error**<br>Retired item 100; used in 3800-3,6,8 printers<br>Software should generate an OBR record, issue the XOA-DBD command, provide an operator message that indicates an error has occurred, and redrive on the page at the jam recovery counter plus one. [IPDS-16-1405]|
| X'0E' | **Not Enough Storage, page printed using the accumulator feature**<br>Retired item 101; used in 3800-3,6,8 printers<br>Delete overlays, page segments, or fonts that are not required for the job. [IPDS-16-1406]|
| X'0F' | **Accumulator read check**<br>Retired item 102; used in 3800-3,6,8 printers<br>Software should generate an OBR record, issue the XOA-DBD command, and redrive on the page at the committed counter plus one. If an electronic overlay was lost, retransmit the electronic overlay and then retransmit the lost pages. [IPDS-16-1407]|
| X'10' | **Reload Electronic Overlay or Base Page**<br>Retired item 103; used in 3800-3,6,8 printers<br>Terminate the print job. [IPDS-16-1408]|
| X'11' | **Count continuous-forms stacker fold wrong errors**<br>Retired item 104; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 6, and restart the channel program with the failing CCW. [IPDS-16-1409]|
| X'12' | **Count burster input checks**<br>Retired item 105; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 7, and restart the channel program with the failing CCW. [IPDS-16-1410]|
| X'13' | **Count no burst checks**<br>Retired item 106; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 8, and restart the channel program with the failing CCW. [IPDS-16-1411]|
| X'14' | **Count burster-trimmer-stacker stacker/trimmer checks**<br>Retired item 107; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 9, and restart the channel program with the failing CCW. [IPDS-16-1412]|
| X'18' | **Transparent error**<br>Retired item 87; used in channel-attached printers [IPDS-16-1413]|
| X'1C' | **Sense Extended CCW required**<br>Retired item 88; used in channel-attached printers<br>An exception has occurred that requires an IPDS NACK to be sent to host software. The IPDS NACK is obtained when the host software issues a Sense Extended CCW. Purge the channel program, obtain and send the Acknowledge Reply to presentation software. The printer might either be in the ready or not ready state when this action code is received. [IPDS-16-1414]|
| X'24' | **Printer not assigned**<br>Retired item 89; used in Serial-Channel-attached printers<br>The printer has not been assigned to the host. Reissue this CCW following the successful execution of an Assign CCW. The printer might either be in the ready or not ready state when this action code is received. [IPDS-16-1415]|
| X'25' | **Printer assigned elsewhere**<br>Retired item 90; used in Serial-Channel-attached printers<br>The printer is assigned to another host. Issue an Assign CCW at a later time or when the printer is known to be available. The printer might either be in the ready or not ready state when this action code is received. [IPDS-16-1416]|
| X'4D' | **Resetting event**<br>Retired item 91; used in Serial-Channel-attached printers<br>A resetting event has occurred at the printer. Reestablish the path group ID and path mode and reissue the failing CCW. The printer might either be in the ready or not ready state when this action code is received. [IPDS-16-1417]|


Command-Reject Exceptions
A command-reject exception indicates that a
command was rejected at the printer without the data
within the command being examined. There are no
AEAs for this class of exception.
An IBM 3800 printer-specific format is used for
X'8003..00'; format 3 is used for X'8005..00'; format 5
is used for X'8006..00'. Refer to Reference Manual
for the IBM 3800 Printing Subsystem Model 8 for a
description of IBM 3800 sense-data formats.
8003..00 Retired (abnormal end of data [IPDS-16-1418]
transmission on fast buffer release)
Action code: X'01'
Explanation: An abnormal end of data transmission
occurred during a fast-buffer-release operation. The printer
has accepted the data that was sent before the ending
point.
Reason for IPDS Retirement: Used in IBM 3800 printers
8005..00 Retired (invalid channel command) [IPDS-16-1419]
Action code: X'04'
Explanation: An invalid channel command was received.
Reason for IPDS Retirement: Used in channel-attached
printers
8005..00 Retired (invalid channel command [IPDS-16-1420]
sequence)
Action code: X'1C'
Explanation: An invalid channel command sequence was
detected. This can occur when a “sense Extended” was
expected by the printer but not received.
Reason for IPDS Retirement: Used in channel-attached
printers
8006..00 Retired (printer not assigned) [IPDS-16-1421]
Action code: X'24'
Explanation: The printer is not assigned.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
8003..00 • 8006..00 [IPDS-16-1422]


Equipment Check with Intervention Required Exceptions
An Equipment Check with Intervention Required
exception indicates that the printer has detected a
condition that was caused by hardware failure or by
hardware limitations, and manual intervention at the
printer is required. There are no AEAs for this class
of exception.
Format 2 is used for X'5010..00'
5010..00 Retired (printer hardware exception) [IPDS-16-1423]
Action code: X'02'
Explanation: A printer hardware exception has been
detected (also called print engine failure).
Reason for IPDS Retirement: Used in channel-attached
printers
5010..00 • 5010..00 [IPDS-16-1424]


Intervention-Required Exceptions
An intervention-required exception indicates that the
printer has detected a condition that requires manual
intervention. There are no AEAs for this class of
exception.
Format 2 is used for X'4051..00', X'4052..00',
X'4053..00', X'4054..00', X'407C..00', X'407C..03',
and X'40E9..00'.
Format 4 is used for X'4000..00', X'4001..00',
X'4002..00', X'4004..00', X'4005..00', X'4011..00',
X'4012..00', X'4031..00', X'4033..00', X'4050..00',
X'40E1..00', X'40E2..00', X'40E3..00', X'40E6..00',
and X'40E7..00'.
Format 8 is used for X'407E..00'.
All other intervention-required exceptions are retired
for the IBM 3800 printer and use an IBM 3800
printer-specific format. Refer to Reference Manual
for the IBM 3800 Printing Subsystem Model 8 for a
description of IBM 3800 sense-data formats.
For some of these exception IDs, byte 19 is shown
as “nn”. The IBM 3800 printer used byte 19 for
device-specific information; for non-3800 printers, nn
= 00.
4000..nn Retired (printer not ready)
Action code: X'03'
Explanation: The printer is not ready.
Reason for IPDS Retirement: Used in channel-attached
printers
4000..nn Retired (printer not ready)
Action code: X'0D'
Explanation: The IBM 3800 printer returned this
exception ID for the first sense data following and IML.
Reason for IPDS Retirement: Used in IBM 3800 printers
4001..nn Retired (out of paper)
Action code: X'03'
Explanation: The printer has run out of paper in the
primary input bin (end of forms for a continuous-forms
printer).
Reason for IPDS Retirement: Used in channel-attached
printers
4002..nn Retired (stacker full)
Action code: X'03'
Explanation: The media destination (stacker) is full.
Reason for IPDS Retirement: Used in channel-attached
printers
4003..nn Retired (burster/stacker not empty)
Action code: X'03'
Explanation: The burster/stacker is not empty.
Reason for IPDS Retirement: Used in IBM 3800 printers
4004..nn Retired (out of toner)
Action code: X'03'
Explanation: The toner supply is empty.
Reason for IPDS Retirement: Used in channel-attached
printers
4005..nn Retired (toner collector full)
Action code: X'03'
Explanation: The toner collector is full.
Reason for IPDS Retirement: Used in IBM 3800 printers
4005..00 Retired (empty fuser oil supply) [IPDS-16-1425]
Action code: X'03'
Explanation: The fuser oil supply is empty.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4006..nn Retired (replace developer mix)
Action code: X'03'
Explanation: The developer mix should be replaced.
Reason for IPDS Retirement: Used in IBM 3800 printers
4007..nn Retired (transfer check)
Action code: X'03'
Explanation: A transfer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4008..nn Retired (fuser check)
Action code: X'03'
Explanation: A fuser check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4009..nn Retired (continuous-forms stacker not
ready)
Action code: X'03'
Explanation: The continuous-forms stacker is not ready.
4000..nn • 4009..nn


Reason for IPDS Retirement: Used in IBM 3800 printers
400A..nn Retired (forms overlay check)
Action code: X'03'
Explanation: A forms overlay check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
400B..nn Retired (photoconductor advance allowed)
Action code: X'03'
Explanation: A photoconductor advance allowed
condition has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
400C..nn Retired (developer check)
Action code: X'03'
Explanation: A developer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
400D..nn Retired (fixed length early termination)
Action code: X'03'
Explanation: A fixed length early termination has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
400E..nn Retired (burster/trimmer check)
Action code: X'03'
Explanation: A burster/trimmer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
400F ..nn Retired (data streaming exception)
Action code: X'03'
Explanation: A data streaming exception has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4011..00 Retired (suppressed jam recovery) [IPDS-16-1426]
Action code: X'02'
Explanation: Host recovery for physical media jams has
been disabled at the printer and a jam has occurred. This
channel-level sense data is issued to alert the operator to
the need for operator intervention to clear the jam.
Reason for IPDS Retirement: Used in channel-attached
printers
4012..00 Retired (attempt to print an undefined [IPDS-16-1427]
character or to print outside sheet
boundaries has occurred that requires
operator intervention)
Action code: X'03'
Explanation: One or more of the following conditions
exists:
• An operator intervention condition has occurred because [IPDS-16-1428]
of an attempt to print outside sheet boundaries, and it
has not been corrected by the operator after a specified
amount of time.
• An operator intervention condition has occurred because [IPDS-16-1429]
of an attempt to print an undefined character, and it has
not been corrected by the operator after a specified
amount of time.
• The operator intervention condition might have been [IPDS-16-1430]
caused by a pre-processing or post-processing device
attached to the printer (used by printers attached to a
4005 Hi-Lite Color Post-Processor Device). [IPDS-16-1431]
Reason for IPDS Retirement: Used in channel-attached
printers
4015..nn Retired (transfer registration check)
Action code: X'02'
Explanation: A transfer registration check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
401C..nn Retired (transfer tractor cover check)
Action code: X'02'
Explanation: A transfer tractor cover check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
401E..nn Retired (transfer to fuser-paper tension
check)
Action code: X'08'
Explanation: A transfer to fuser-paper tension check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4025..nn Retired (one-half inch print alignment
check)
Action code: X'02'
Explanation: A one-half inch print alignment check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4028..nn Retired (fuser paper skew check)
Action code: X'02'
Explanation: A fuser paper skew check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
402E..nn Retired (fuser roll paper wrap)
Action code: X'08'
Explanation: A fuser roll paper wrap has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4030..nn Retired (forms length selector check)
Action code: X'02'
400A..nn • 4030..nn


Explanation: A forms length selector check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4031..nn Retired (paper length check)
Action code: X'02'
Explanation: A paper length check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4031..00 Retired (paper length check) [IPDS-16-1432]
Action code: X'03'
Explanation: A paper length check has occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
4032..nn Retired (continuous-forms stacker fold
wrong)
Action code: X'11'
Explanation: Continuous-forms stacker fold is wrong.
Reason for IPDS Retirement: Used in IBM 3800 printers
4033..nn Retired (paper width check)
Action code: X'02'
Explanation: A paper width check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4033..00 Retired (paper width check) [IPDS-16-1433]
Action code: X'03'
Explanation: A paper width check has occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
4034..nn Retired (fuser output-paper break or long
loop)
Action code: X'02'
Explanation: A paper break or long loop has occurred at
the fuser output.
Reason for IPDS Retirement: Used in IBM 3800 printers
403B..nn Retired (transfer/fuser page counter
check)
Action code: X'03'
Explanation: A transfer/fuser page counter check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4040..nn Retired (burster input check)
Action code: X'12'
Explanation: A burster input check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4041..nn Retired (no burst check)
Action code: X'13'
Explanation: A no burst check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4042..nn Retired (burster-trimmer-stacker stacker or
trimmer check)
Action code: X'14'
Explanation: A stacker or trimmer check has occurred at
the burster-trimmer-stacker.
Reason for IPDS Retirement: Used in IBM 3800 printers
4043..nn Retired (early burst check)
Action code: X'02'
Explanation: An early burst check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
4050..00 Retired (fuser oil supply empty) [IPDS-16-1434]
Action code: X'02'
Explanation: The fuser oil supply is empty.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4051..00 Retired (developer mix needs changing) [IPDS-16-1435]
Action code: X'02'
Explanation: The developer mix needs changing.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4052..00 Retired (oiler belt needs changing) [IPDS-16-1436]
Action code: X'02'
Explanation: The oiler belt needs changing.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4053..00 Retired (toner collector full) [IPDS-16-1437]
Action code: X'02'
Explanation: The toner collector is full.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4054..00 Retired (fine filter needs changing) [IPDS-16-1438]
Action code: X'02'
Explanation: The fine filter needs changing.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
4063..nn Retired (vacuum system check)
4031..nn • 4063..nn


Action code: X'02'
Explanation: A vacuum system check has occurred
(maximum retry count value = 4).
Reason for IPDS Retirement: Used in IBM 3800 printers
407C..00 Retired (out of staples)
Action code: X'03'
Explanation: The printer is out of staples and a staple
operation has been received.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
407C..03 Retired (punch waste bin full)
Action code: X'03'
Explanation: The punch waste bin is full.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
407E..00 Retired (intervention required on a UP 3I-
controlled device)
Action code: X'03'
Explanation: Operator intervention is required on a UP 3I-
controlled pre-processor or post-processor device.
Reason for IPDS Retirement: Used in UP3I.
40E1..00 Retired (out of paper in the secondary
input bin)
Action code: X'03'
Explanation: There is no paper in the secondary input
bin.
Reason for IPDS Retirement: Used in channel-attached
printers
40E2..00 Retired (paper transport requires
corrective action)
Action code: X'03'
Explanation: The paper transport requires corrective
action.
Reason for IPDS Retirement: Used in channel-attached
printers
40E3..00 Retired (fuser requires corrective action)
Action code: X'03'
Explanation: The fuser requires corrective action.
Reason for IPDS Retirement: Used in channel-attached
printers
40E6..00 Retired (door open)
Action code: X'03'
Explanation: A door is open.
Reason for IPDS Retirement: Used in channel-attached
printers
40E7..00 Retired (paper specification check)
Action code: X'03'
Explanation: The printer has detected a paper-
specification check.
Reason for IPDS Retirement: Used in channel-attached
printers
40E9..00 Retired (post-processor not ready)
Action code: X'03'
Explanation: A post-processor attached to the printer is
needed, but not ready.
Reason for IPDS Retirement: Used in transmission
protocols (such as TCP/IP and channel)
40F0..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F0' has been displayed.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F1..00 Retired (thread forms into the continuous-
forms stacker)
Action code: X'03'
Explanation: An operator panel message code of X'F1'
has been displayed; that means “thread forms into the
continuous-forms stacker”.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F2..00 Retired (thread forms into the burster-
trimmer-stacker feature)
Action code: X'03'
Explanation: An operator panel message code of X'F2'
has been displayed; that means “thread forms into the
burster-trimmer-stacker feature”.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F3..00 Retired (change the forms overlay)
Action code: X'03'
Explanation: An operator panel message code of X'F3'
has been displayed; that means “change the forms
overlay”.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F4..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F4' has been displayed.
407C..00 • 40F4..00


Reason for IPDS Retirement: Used in IBM 3800 printers
40F5..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F5' has been displayed.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F6..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F6' has been displayed.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F7..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F7' has been displayed.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F8..00 Retired (check system console message)
Action code: X'03'
Explanation: An operator panel message code of X'F8'
has been displayed; that means “check system console
message”.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F9..00 Retired (user specified operator panel
message code)
Action code: X'03'
Explanation: A user specified operator panel message
code of X'F9' has been displayed.
Reason for IPDS Retirement: Used in IBM 3800 printers
40FE..nn Retired (initial-microcode-load operation is
taking place)
Action code: N/A
Explanation: The printer is being reinitialized.
Reason for IPDS Retirement: Used in IBM 3800 printers
40F5..00 • 40FE..nn


Reserved for Bus-Out Parity Check Exceptions
This class of exceptions is reserved for compatibility
with channel-attached printers (IBM 3800 printers,
IPDS). There are no AEAs for this class of exception.
Format 5 is used for X'2001..01', X'2001..02',
X'2002..01', and X'2002..02'; format 3 is used for
X'2011..00' and X'2012..00'.
2001..01 Retired (link adapter A device-level error) [IPDS-16-1439]
Action code: X'04'
Explanation: A link adapter A device-level error has
occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
2001..02 Retired (link adapter B device-level error) [IPDS-16-1440]
Action code: X'04'
Explanation: A link adapter B device-level error has
occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
2002..01 Retired (link adapter A link-level error) [IPDS-16-1441]
Action code: X'04'
Explanation: A link adapter A link-level error has
occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
2002..02 Retired (link adapter B link-level error) [IPDS-16-1442]
Action code: X'04'
Explanation: A link adapter B link-level error has
occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
2011..00 Retired (channel command parity error) [IPDS-16-1443]
Action code: X'04'
Explanation: A channel command parity error has
occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
2012..00 Retired (channel data parity error) [IPDS-16-1444]
Action code: X'04'
Explanation: A channel data parity error has occurred;
this was called “command byte parity error” for the IBM
3800 printer. [IPDS-16-1445]
Reason for IPDS Retirement: Used in channel-attached
printers
2001..01 • 2012..00 [IPDS-16-1446]


Equipment-Check Exceptions
An equipment-check exception indicates that the
printer has detected an equipment malfunction or a
hardware failure. There are no AEAs for this class of
exception.
Format 2 is used for X'10F1..00', X'10F2..00',
X'10F5..00', and X'10FA..00'.
Format 3 is used for X'10E0..00' and X'10E1..00'.
Format 5 is used for X'10E2..01' and X'10E2..02'.
All other equipment-check exceptions are retired for
the IBM 3800 printer and use an IBM 3800 printer-
specific format. Refer to Reference Manual for the
IBM 3800 Printing Subsystem Model 8 for a
description of IBM 3800 sense-data formats.
1011..nn Retired (transfer not in detent position)
Action code: X'02'
Explanation: The transfer is not in the detent position.
Reason for IPDS Retirement: Used in IBM 3800 printers
1014..nn Retired (transfer start-stop check)
Action code: X'02'
Explanation: A transfer start-stop check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1016..nn Retired (transfer encoder check)
Action code: X'02'
Explanation: A transfer encoder check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1017..nn Retired (transfer motor overload)
Action code: X'02'
Explanation: A transfer motor overload has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1018..nn Retired (transfer print position check)
Action code: X'02'
Explanation: A transfer print position check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1021..nn Retired (fuser hot roll check; below
temperature)
Action code: X'02'
Explanation: A fuser hot roll check has occurred; the
fuser hot roll is too cool.
Reason for IPDS Retirement: Used in IBM 3800 printers
1022..nn Retired (fuser platen check; below
temperature)
Action code: X'02'
Explanation: A fuser platen check has occurred; the fuser
platen is too cool.
Reason for IPDS Retirement: Used in IBM 3800 printers
1023..nn Retired (fuser backup roll closed check)
Action code: X'02'
Explanation: A fuser backup roll closed check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1024..nn Retired (fuser backup roll open check)
Action code: X'02'
Explanation: A fuser backup roll open check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1026..nn Retired (fuser width check/transfer width
invalid)
Action code: X'02'
Explanation: A fuser width check has occurred; the
transfer width is invalid.
Reason for IPDS Retirement: Used in IBM 3800 printers
1027..nn Retired (fuser motor overload)
Action code: X'02'
Explanation: The fuser motor is overloaded.
Reason for IPDS Retirement: Used in IBM 3800 printers
102A..nn Retired (transfer to fuser; short loop
check)
Action code: X'02'
Explanation: A short loop check has occurred in the
transfer to fuser.
Reason for IPDS Retirement: Used in IBM 3800 printers
102B..nn Retired (transfer to fuser; long loop check)
Action code: X'02'
Explanation: A long loop check has occurred in the
transfer to fuser.
Reason for IPDS Retirement: Used in IBM 3800 printers
1011..nn • 102B..nn


104B..nn Retired (burster-trimmer-stacker loop
check)
Action code: X'02'
Explanation: A burster-trimmer-stacker loop check has
occurred on the burster/trimmer/stacker.
Reason for IPDS Retirement: Used in IBM 3800 printers
1051..nn Retired (missing forms overlay flash)
Action code: X'02'
Explanation: A forms overlay flash is missing.
Reason for IPDS Retirement: Used in IBM 3800 printers
1052..nn Retired (extra forms overlay flash)
Action code: X'02'
Explanation: There is an extra forms overlay flash.
Reason for IPDS Retirement: Used in IBM 3800 printers
1062..nn Retired (print contrast low or more-than-
one-half-inch print alignment check)
Action code: X'02'
Explanation: The print contrast is low or a more-than-
one-half-inch print alignment check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1064..nn Retired (optical mark sensor stepper limit
check)
Action code: X'02'
Explanation: An optical mark sensor stepper limit check
has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1065..nn Retired (cleaner brush check)
Action code: X'02'
Explanation: A cleaner brush check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1066..nn Retired (erase lamp check)
Action code: X'02'
Explanation: An erase lamp check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1067..nn Retired (optical mark sensor voltage
check)
Action code: X'02'
Explanation: An optical mark sensor voltage check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1068..nn Retired (drum speed low)
Action code: X'02'
Explanation: The drum speed is low.
Reason for IPDS Retirement: Used in IBM 3800 printers
1069..nn Retired (drum speed high)
Action code: X'02'
Explanation: The drum speed is high.
Reason for IPDS Retirement: Used in IBM 3800 printers
106A..nn Retired (drum motor overload)
Action code: X'02'
Explanation: The drum motor is overloaded.
Reason for IPDS Retirement: Used in IBM 3800 printers
106C..nn Retired (toner overfeed check)
Action code: X'02'
Explanation: A toner overfeed check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
106D..nn Retired (toner loop open check)
Action code: X'02'
Explanation: A toner loop open check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1070..nn Retired (CGEN-IEU attachment check)
Action code: X'02'
Explanation: A CGEN-IEU attachment check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1071..nn Retired (CGEN control check)
Action code: X'02'
Explanation: A CGEN control check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1072..nn Retired (raster pattern generator check)
Action code: X'02'
Explanation: A raster pattern generator check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1073..nn Retired (shifter check)
Action code: X'02'
Explanation: A shifter check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1074..nn Retired (strip buffer check)
Action code: X'02'
104B..nn • 1074..nn


Explanation: A strip buffer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1075..nn Retired (character table cycle-steal check)
Action code: X'02'
Explanation: A character table cycle-steal check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1076..nn Retired (laser power check)
Action code: X'02'
Explanation: A laser power check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1077..nn Retired (mirror speed check)
Action code: X'02'
Explanation: A mirror speed check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1078..nn Retired (serializer check)
Action code: X'02'
Explanation: A serializer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1079..nn Retired (serializer-CGEN attachment
check)
Action code: X'02'
Explanation: A serializer-CGEN attachment check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
107A..nn Retired (mirror rotation check)
Action code: X'02'
Explanation: A mirror rotation check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
107C..nn Retired (serializer-to-process sync check)
Action code: X'02'
Explanation: A serializer-to-process sync check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
107D..nn Retired (strip buffer overrun or
accumulator storage error)
Action code: X'09'
Explanation: One or more of the following conditions has
occurred:
• Strip buffer overrun [IPDS-16-1447]
• Not enough accumulator storage [IPDS-16-1448]
• No accumulator feature installed [IPDS-16-1449]
Reason for IPDS Retirement: Used in IBM 3800 printers
107E..nn Retired (CGEN RPS cycle-steal start
check)
Action code: X'02'
Explanation: A CGEN RPS cycle-steal start check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
107F ..nn Retired (CGEN RPS cycle-steal end status
check or missing interrupt from CGEN)
Action code: X'02'
Explanation: One or more of the following conditions has
occurred:
• CGEN RPS cycle-steal end status check [IPDS-16-1450]
• Missing interrupt from CGEN [IPDS-16-1451]
Reason for IPDS Retirement: Used in IBM 3800 printers
1080..nn Retired (retry error log full)
Action code: X'07'
Explanation: The retry error log is full.
Reason for IPDS Retirement: Used in IBM 3800 printers
1081..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'81' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1082..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'82' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1083..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'83' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1084..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'84' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1075..nn • 1084..nn


1085..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'85' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1086..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'86' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1087..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'87' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1088..nn Retired (temporary lEU parity check)
Action code: N/A
Explanation: A temporary lEU parity check X'88' has
occurred; this error does not cause an equipment check
(the printer remains ready).
Reason for IPDS Retirement: Used in IBM 3800 printers
1089..nn Retired (permanent lEU parity check)
Action code: X'0D'
Explanation: A permanent lEU parity check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
108B..nn Retired (subsystem clock or sync check)
Action code: X'02'
Explanation: A subsystem clock or sync check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
108D..nn Retired (microcode clock stop with
channel selective reset)
Action code: X'0D'
Explanation: A microcode clock stop with channel
selective reset has occurred; the printer was correctly
restarted.
Reason for IPDS Retirement: Used in IBM 3800 printers
108E..nn Retired (subsystem run reset)
Action code: X'08'
Explanation: A subsystem run reset has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
108F ..00 Retired (hardware clock stop with channel
selective reset)
Action code: X'0D'
Explanation: A hardware clock stop with channel
selective reset has occurred; the printer was correctly
restarted.
Reason for IPDS Retirement: Used in IBM 3800 printers
1090..nn Retired (process clock check)
Action code: X'02'
Explanation: A process clock check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1091..nn Retired (charge corona check)
Action code: X'02'
Explanation: A charge corona check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1092..nn Retired (transfer corona check)
Action code: X'02'
Explanation: A transfer corona check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1093..nn Retired (preclean corona or cleaner brush
check)
Action code: X'02'
Explanation: A preclean corona or cleaner brush check
has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1094..nn Retired (magnetic brush bias check)
Action code: X'02'
Explanation: A magnetic brush bias check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1095..nn Retired (post transfer corona check)
Action code: X'02'
Explanation: A post transfer corona check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1096..nn Retired (laser power supply check)
Action code: X'02'
Explanation: A laser power supply check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1097..nn Retired (mirror drive check)
1085..nn • 1097..nn


Action code: X'02'
Explanation: A mirror drive check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
1098..nn Retired (digital voltmeter (DVM) check)
Action code: X'02'
Explanation: A digital voltmeter (DVM) check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A0..nn Retired (process power not ready)
Action code: X'0B'
Explanation: The process power is not ready.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A2..nn Retired (process voltage CP open)
Action code: X'0B'
Explanation: The process voltage CP is open.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A3..nn Retired (logic voltage CP open)
Action code: X'0B'
Explanation: The logic voltage CP is open.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A4..nn Retired (mirror motor thermal switch
check)
Action code: X'0B'
Explanation: A mirror motor thermal switch check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A5..nn Retired (drum cooler or air check)
Action code: X'0B'
Explanation: A drum cooler or air check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A6..nn Retired (cyclone blower motor thermal
switch check)
Action code: X'0B'
Explanation: A cyclone blower motor thermal switch
check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A7..nn Retired (developer motor thermal switch
check)
Action code: X'0B'
Explanation: A developer motor thermal switch check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A8..nn Retired (cleaner brush motor thermal
switch check)
Action code: X'0B'
Explanation: A cleaner brush motor thermal switch check
has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10A9..nn Retired (control assembly thermal switch
check)
Action code: X'0B'
Explanation: A control assembly thermal switch check
has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10AB..nn Retired (fuser scuff roll motor thermal
switch check)
Action code: X'0B'
Explanation: A fuser scuff roll motor thermal switch check
has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10AC..nn Retired (continuous-forms-stacker
elevator motor thermal switch check)
Action code: X'0B'
Explanation: A continuous-forms-stacker elevator motor
thermal switch check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10AD..nn Retired (continuous-forms-stacker or
burster-trimmer-stacker motor thermal
switch check)
Action code: X'0B'
Explanation: A continuous-forms-stacker or burster-
trimmer-stacker motor thermal switch check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10AE..nn Retired (multiple thermal switch check)
Action code: X'0B'
Explanation: A multiple thermal switch check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10AF ..nn Retired (fuser thermal switch check)
Action code: X'0B'
Explanation: A fuser thermal switch check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B1..nn Retired (burster-trimmer-stacker cam
motor thermal switch check)
1098..nn • 10B1..nn


Action code: X'0B'
Explanation: A burster-trimmer-stacker cam motor
thermal switch check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B2..nn Retired (fuser hot roll check; above
temperature)
Action code: X'02'
Explanation: A fuser hot roll check has occurred; the hot
roll is too hot.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B3..nn Retired (fuser platen check; above
temperature)
Action code: X'02'
Explanation: A fuser platen check has occurred; the fuser
platen is too hot.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B4..nn Retired (platen thermistor open or multiple
readings are more than the permissible
delta of 5 degrees)
Action code: X'02'
Explanation: The platen thermistor is open or multiple
readings show that the platen is more than the permissible
delta of 5 degrees.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B5..nn Retired (fuser hot roll thermistor open or
multiple readings are more than the
permissible delta of 5 degrees)
Action code: X'02'
Explanation: The fuser hot roll thermistor is open or
multiple readings show that the hot roll is more than the
permissible delta of 5 degrees.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B6..nn Retired (fuser current check)
Action code: X'02'
Explanation: A fuser current check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B7..nn Retired (thermal switch check)
Action code: X'02'
Explanation: A thermal switch check has occurred; the
switch is not specified.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B8..nn Retired (NVS check)
Action code: X'02'
Explanation: An NVS check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10B9..nn Retired (process-paper line channel check)
Action code: X'08'
Explanation: A process-paper line channel check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10BA..nn Retired (system channel check)
Action code: X'02'
Explanation: A system channel check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10BB..nn Retired (diskette check)
Action code: X'02'
Explanation: A diskette check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10BC..nn Retired (diskette read check)
Action code: X'02'
Explanation: A diskette read check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10BD..nn Retired (diskette write check)
Action code: X'02'
Explanation: A diskette write check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10BF ..nn Retired (diskette damaged or changed
without an IML)
Action code: X'02'
Explanation: The diskette is damaged or was changed
without an IML.
Reason for IPDS Retirement: Used in IBM 3800 printers
10D0..nn Retired (EXGRF-CGEN parity check)
Action code: X'02'
Explanation: An EXGRF-CGEN parity check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10D1..nn Retired (EXGRF character residue check)
Action code: X'09'
Explanation: An EXGRF character residue check has
occurred. This is caused by a hardware failure or by invalid
double-byte font data.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10B2..nn • 10D1..nn


10D2..nn Retired (EXGRF buffer check)
Action code: X'02'
Explanation: An EXGRF buffer check has occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10D3..nn Retired (EXGRF decompression check)
Action code: X'02'
Explanation: An EXGRF decompression check has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10D4..nn Retired (CPS error during decompression)
Action code: X'02'
Explanation: A CPS error during decompression has
occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10D8..nn Retired (accumulator feature check; erase)
Action code: X'02'
Explanation: An accumulator feature check (erase)
occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10D9..nn Retired (accumulator feature storage
check; write)
Action code: X'02'
Explanation: An accumulator feature storage check
(write) occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10DA..nn Retired (accumulator feature or strip buffer
check; read)
Action code: X'0F'
Explanation: An accumulator feature or strip buffer check
(read) occurred.
Reason for IPDS Retirement: Used in IBM 3800 printers
10DB..nn Retired (accumulator feature error)
Action code: X'02'
Explanation: One or more of the following conditions has
occurred with the accumulator feature:
• No response timeout [IPDS-16-1452]
• Unexpected accumulator feature interrupt [IPDS-16-1453]
Reason for IPDS Retirement: Used in IBM 3800 printers
10DD..nn Retired (CPS read or write error)
Action code: X'02'
Explanation: A CPS read or write error has occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10DF ..nn Retired (EXGRF decompressor check
during font load)
Action code: X'02'
Explanation: An EXGRF decompressor check during font
load has occurred.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
10E0..00 Retired (channel adapter check)
Action code: X'04'
Explanation: A channel adapter check occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
10E1..00 Retired (two-channel switch adapter
check)
Action code: X'04'
Explanation: A two-channel switch adapter check
occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
10E2..01 Retired (link adapter A check)
Action code: X'04'
Explanation: A link adapter A check occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
10E2..02 Retired (link adapter B check)
Action code: X'04'
Explanation: A link adapter B check occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
10F1..00 Retired (log only condition)
Action code: X'18'
Explanation: A log only condition occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
10F2..00 Retired (transparent print overrun;
successful retry)
Action code: X'18'
Explanation: A transparent print overrun occurred with a
successful retry.
Reason for IPDS Retirement: Used in channel-attached
printers
10D2..nn • 10F2..00


10F5..00 Retired (transparent image generator
error; successful retry)
Action code: X'18'
Explanation: A transparent image generator error
occurred with a successful retry.
Reason for IPDS Retirement: Used in channel-attached
printers
10FA..00 Retired (media size sensor error)
Action code: X'1D'
Explanation: A hardware failure occurred in the printer's
media-size sensors. The printer cannot detect the size of
the media loaded in a media source. The printer-default
media size is used for that media source. The Printable-
Area self-defining field of the XOH-OPC reply (byte 22, bit
4) will indicate:
• The physical-page size and printable-area size might be [IPDS-16-1454]
invalid
• The default physical-page size and printable-area size [IPDS-16-1455]
that are being used by the printer
• Data truncation might occur without being reported. [IPDS-16-1456]
Reason for IPDS Retirement: Retired since it was not
used.
10FF ..nn Retired (lEU clock stop)
Action code: N/A
Explanation: The IEU clock stopped; this error is not
presented to the host system's program.
Reason for IPDS Retirement: Used in IBM 3800 printers
10F5..00 • 10FF ..nn


Data-Check Exceptions
A data-check exception indicates that the printer has
detected an undefined character or position check.
All data-check exceptions are retired for the IBM
3800 printer and use an IBM 3800 printer-specific [IPDS-16-1457]
format. Refer to Reference Manual for the IBM 3800
Printing Subsystem Model 8 for a description of IBM
3800 sense-data formats. [IPDS-16-1458]
0824..00 Retired (position check; print position is [IPDS-16-1459]
outside the logical page in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; the print
position is outside the logical page in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0825..00 Retired (position check; print position is [IPDS-16-1460]
outside the logical page in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; the print
position is outside the logical page in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0826..00 Retired (position check; print position is [IPDS-16-1461]
outside the logical page in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; the print
position is outside the logical page in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0827..00 Retired (position check; print position is [IPDS-16-1462]
outside the logical page in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; the print
position is outside the logical page in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082A..00 Retired (position check; first pel for the
character pattern is outside the physical
page in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; the first pel
for the character pattern is outside the physical page in the
-X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082B..00 Retired (position check; first pel for the
character pattern is outside the physical
page in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; the first pel
for the character pattern is outside the physical page in the
-Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082C..00 Retired (position check; character extends
along the row beyond the print window in
the +X direction)
Action code: X'01'
Explanation: A position check has occurred; a character
extends along the row beyond the print window in the +X
direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082D..00 Retired (position check; character extends
along the row beyond the printable area in
the +Y direction)
Action code: X'01'
Explanation: A position check has occurred; a character
extends along the row beyond the printable area in the +Y
direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082E..00 Retired (position check; character extends
along the row beyond the print window in
the -X direction)
Action code: X'01'
Explanation: A position check has occurred; a character
extends along the row beyond the print window in the -X
direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
082F ..00 Retired (position check; character extends
along the row beyond the printable area in
the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; a character
extends along the row beyond the printable area in the -Y
direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0830..00 Retired (position check; print position is [IPDS-16-1463]
outside the print window for an inline rule)
Action code: X'01'
0824..00 • 0830..00 [IPDS-16-1464]


Explanation: A position check has occurred; the print
position is outside the print window for an inline rule.
Reason for IPDS Retirement: Used in IBM 3800 printers
0831..00 Retired (position check; print position is [IPDS-16-1465]
outside the print window for a baseline
rule)
Action code: X'01'
Explanation: A position check has occurred; the print
position is outside the print window for a baseline rule.
Reason for IPDS Retirement: Used in IBM 3800 printers
0834..00 Retired (position check; baseline extent for [IPDS-16-1466]
a character row extends beyond the print
window in the +X direction)
Action code: X'01'
Explanation: A position check has occurred; the baseline
extent for a character row extends beyond the print window
in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0835..00 Retired (position check; baseline extent for [IPDS-16-1467]
a character row extends beyond the print
window in the +Y direction)
Action code: X'01'
Explanation: A position check has occurred; the baseline
extent for a character row extends beyond the print window
in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0836..00 Retired (position check; baseline extent for [IPDS-16-1468]
a character row extends beyond the print
window in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; the baseline
extent for a character row extends beyond the print window
in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0837..00 Retired (position check; baseline extent for [IPDS-16-1469]
a character row extends beyond the print
window in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; the baseline
extent for a character row extends beyond the print window
in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0838..00 Retired (position check; rule width extends [IPDS-16-1470]
beyond the print window in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; the rule
width extends beyond the print window in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0839..00 Retired (position check; rule width extends [IPDS-16-1471]
beyond the print window in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; the rule
width extends beyond the print window in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
083A..00 Retired (position check; rule width extends
beyond the print window in the -X
direction)
Action code: X'01'
Explanation: A position check has occurred; the rule
width extends beyond the print window in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
083B..00 Retired (position check; rule width extends
beyond the print window in the -Y
direction)
Action code: X'01'
Explanation: A position check has occurred; the rule
width extends beyond the print window in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
083C..00 Retired (position check; rule length
extends beyond the print window in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the print window in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
083D..00 Retired (position check; rule length
extends beyond the print window in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the print window in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
083E..00 Retired (position check; rule length
extends beyond the print window in the -X
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the print window in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0831..00 • 083E..00 [IPDS-16-1472]


083F ..00 Retired (position check; rule length
extends beyond the print window in the -Y
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the print window in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0842..00 Retired (position check; image extends [IPDS-16-1473]
beyond the print window in the -X
direction)
Action code: X'01'
Explanation: A position check occurred; an image
extends beyond the print window in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0843..00 Retired (position check; image extends [IPDS-16-1474]
beyond the print window in the +X
direction)
Action code: X'01'
Explanation: A position check occurred; an image
extends beyond the print window in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0846..00 Retired (position check; image extends [IPDS-16-1475]
beyond the print window in the -Y
direction)
Action code: X'01'
Explanation: A position check occurred; an image
extends beyond the print window in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0847..00 Retired (position check; image extends [IPDS-16-1476]
beyond the print window in the +Y
direction)
Action code: X'01'
Explanation: A position check occurred; an image
extends beyond the print window in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
084C..00 Retired (position check; image extends
beyond the logical page in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; an image
extends beyond the logical page in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
084D..00 Retired (position check; image extends
beyond the logical page in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; an image
extends beyond the logical page in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
084E..00 Retired (position check; image extends
beyond the logical page in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; an image
extends beyond the logical page in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
084F ..00 Retired (position check; image extends
beyond the logical page in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; an image
extends beyond the logical page in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0850..00 Retired (accumulator check; paper or page [IPDS-16-1477]
size is not compatible with accumulator
data stored earlier)
Action code: X'10'
Explanation: An accumulator check has occurred; the
paper or page size is not compatible with accumulator data
stored earlier (sensed when the data is read from
accumulator storage).
Reason for IPDS Retirement: Used in IBM 3800 printers
0851..00 Retired (accumulator check; paper or page [IPDS-16-1478]
size is greater than the accumulator
storage capacity)
Action code: X'10'
Explanation: An accumulator check has occurred; paper
or page size is greater than the accumulator storage
capacity (sensed when the data is stored in accumulator
storage).
Reason for IPDS Retirement: Used in IBM 3800 printers
0852..00 Retired (accumulator check; base page [IPDS-16-1479]
was moved to accumulator storage and
must be reloaded)
Action code: X'10'
Explanation: An accumulator check has occurred; the
base page was moved to accumulator storage and must be
reloaded.
Reason for IPDS Retirement: Used in IBM 3800 printers
0853..00 Retired (accumulator check; raster overlay [IPDS-16-1480]
in accumulator storage must be reloaded)
Action code: X'10'
Explanation: An accumulator check has occurred; the
raster overlay in accumulator storage must be reloaded.
083F ..00 • 0853..00


Reason for IPDS Retirement: Used in IBM 3800 printers
0854..00 Retired (position check; rule width extends [IPDS-16-1481]
beyond the logical page in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule width
extends beyond the logical page in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0855..00 Retired (position check; rule width extends [IPDS-16-1482]
beyond the logical page in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule width
extends beyond the logical page in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0856..00 Retired (position check; rule width extends [IPDS-16-1483]
beyond the logical page in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; a rule width
extends beyond the logical page in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0857..00 Retired (position check; rule width extends [IPDS-16-1484]
beyond the logical page in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; a rule width
extends beyond the logical page in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0858..00 Retired (position check; rule length [IPDS-16-1485]
extends beyond the logical page in the +X
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the logical page in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0859..00 Retired (position check; rule length [IPDS-16-1486]
extends beyond the logical page in the +Y
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the logical page in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
085A..00 Retired (position check; rule length
extends beyond the logical page in the -X
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the logical page in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
085B..00 Retired (position check; rule length
extends beyond the logical page in the -Y
direction)
Action code: X'01'
Explanation: A position check has occurred; a rule length
extends beyond the logical page in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
085C..00 Retired (horizontal adjustment value for
raster overlay is greater than horizontal
adjustment value for the page)
Action code: X'10'
Explanation: The horizontal adjustment value for the
raster overlay is greater than the horizontal adjustment
value for the page it is printed on. It must be equal to or
less than the page value.
Reason for IPDS Retirement: Used in IBM 3800 printers
0864..00 Retired (position check; addressability [IPDS-16-1487]
limit exceeded in the +X direction)
Action code: X'01'
Explanation: A position check has occurred; the
addressability limit was exceeded in the +X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0865..00 Retired (position check; addressability [IPDS-16-1488]
limit exceeded in the +Y direction)
Action code: X'01'
Explanation: A position check has occurred; the
addressability limit was exceeded in the +Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0866..00 Retired (position check; addressability [IPDS-16-1489]
limit exceeded in the -X direction)
Action code: X'01'
Explanation: A position check has occurred; the
addressability limit was exceeded in the -X direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0867..00 Retired (position check; addressability [IPDS-16-1490]
limit exceeded in the -Y direction)
Action code: X'01'
Explanation: A position check has occurred; the
addressability limit was exceeded in the -Y direction.
Reason for IPDS Retirement: Used in IBM 3800 printers
0868..00 Retired (too little control store while [IPDS-16-1491]
printing position error markers)
0854..00 • 0868..00 [IPDS-16-1492]


Action code: X'01'
Explanation: There is not enough control store is
available while printing position error markers (PEM's) and
an accumulator feature is not installed.
Reason for IPDS Retirement: Used in IBM 3800 printers
0868..00 • 0868..00 [IPDS-16-1493]


Specification Checks—IO-Image Exceptions
A specification check—IO-Image exception indicates
the printer has received an IO-Image command with
an invalid or unsupported data parameter or value.
Format 0 is used for X'0500..02'.
0500..02 Retired (reserved bits or bytes are not [IPDS-16-1494]
zeros)
Action code: X'01' or X'1F'
Explanation: Reserved bits or bytes within the Image
Content are not equal to zero.
Reason for IPDS Retirement: Used in IOCA
0500..02 • 0500..02 [IPDS-16-1495]


Specification Checks—Bar Code Exceptions
A specification check—bar code exception indicates
the printer has received a bar code command with an
invalid or unsupported data parameter or value.
Format 0 is used for X'0402..00' and X'040D..00';
format 3 is used for X'0401..00'; format 5 is used for
X'0401..01' and X'0401..02'.
0401..00 Retired (channel overrun) [IPDS-16-1496]
Action code: X'04'
Explanation: A channel overrun has occurred.
Reason for IPDS Retirement: Used in channel-attached
printers
0401..01 Retired (link adapter A overrun) [IPDS-16-1497]
Action code: X'04'
Explanation: A link adapter A overrun has occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
0401..02 Retired (link adapter B overrun) [IPDS-16-1498]
Action code: X'04'
Explanation: A link adapter B overrun has occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
0402..00 Retired (attempt to print symbol or HRI [IPDS-16-1499]
character out of block)
Action code: X'01'
Explanation: An attempt is made to print a bar code
symbol feature or HRI character outside the bar code block
presentation space.
Reason for IPDS Retirement: Used in IBM 4224 and
4234 printers [IPDS-16-1500]
040D..00 Retired (reference point outside logical
page)
Action code: X'01'
Explanation: The symbol reference point lies outside the
logical page.
Reason for IPDS Retirement: Used in IBM 4224 and
4234 printers [IPDS-16-1501]
0401..00 • 040D..00 [IPDS-16-1502]


Specification Checks—Graphics Data Exceptions
A specification check—graphics exception indicates
the printer has received a graphics command with an
invalid or unsupported data parameter or value.
Format 0 is used for all graphics specification check
exceptions.
0300..05 Retired (segment call stack full) [IPDS-16-1503]
Action code: X'01' or X'1F'
Explanation: The segment call stack is full. The data
could not be pushed onto the stack.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0300..06 Retired (homogeneous coordinate [IPDS-16-1504]
overflow error)
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• A draw operation with Homogeneous specified is [IPDS-16-1505]
requested and the w coordinate is found to be zero.
• A Set W Coordinate order is requested that sets the w [IPDS-16-1506]
coordinate to zero.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0300..0B Retired (segment call stack empty)
Action code: X'01' or X'1F'
Explanation: One or more of the following conditions
exists:
• The segment call stack has no values above the return [IPDS-16-1507]
address when in a called segment.
• The segment call stack is empty when in a chained [IPDS-16-1508]
segment.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0307..00 Retired (called segment not found) [IPDS-16-1509]
Action code: X'01' or X'1F'
Explanation: The segment identified in the Call Segment
order cannot be found in the entity storage pool.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0324..00 Retired (model transform matrix element [IPDS-16-1510]
overflow)
Action code: X'01' or X'1F'
Explanation: A Set Model Transform order specifies or
causes the value of a matrix element to exceed the value
expressible by its particular number format.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0327..00 Retired (invalid viewing window) [IPDS-16-1511]
Action code: X'01' or X'1F'
Explanation: Segments are being processed in 2D, but
ZF and/or ZN parameters are specified in a Set Viewing
Window order.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0327..01 Retired (invalid viewing window) [IPDS-16-1512]
Action code: X'01' or X'1F'
Explanation: The WW values are inconsistent; for
example, the value of XL is larger than the value of XR.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0327..02 Retired (invalid viewing window) [IPDS-16-1513]
Action code: X'01' or X'1F'
Explanation: A Set Viewing Window order has been
found outside a root segment.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0331..00 Retired (viewing transform matrix element [IPDS-16-1514]
overflow)
Action code: X'01' or X'1F'
Explanation: A Set Viewing Transform order specifies or
causes the value of a matrix element to exceed the value
expressible by its particular number format.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0331..02 Retired (invalid viewing transform) [IPDS-16-1515]
Action code: X'01' or X'1F'
Explanation: A Set Viewing Transform order has been
found outside a root segment.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0332..00 Retired (invalid segment boundary) [IPDS-16-1516]
Action code: X'01' or X'1F'
Explanation: Segments are being processed in 2D, but
ZF and/or ZN parameters are specified in a Set Segment
Boundary order.
0300..05 • 0332..00 [IPDS-16-1517]


Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0332..02 Retired (invalid segment boundary) [IPDS-16-1518]
Action code: X'01' or X'1F'
Explanation: A Set Segment Boundary order has been
found outside a root segment.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0335..00 Retired (invalid character shear) [IPDS-16-1519]
Action code: X'01' or X'1F'
Explanation: Invalid attribute value in Set Character
Shear order.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0360..01 Retired (area nested call error) [IPDS-16-1520]
Action code: X'01' or X'1F'
Explanation: An End Area order has been executed in a
segment that has been called by another segment and the
associate Begin Area order was in the calling segment.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0370..81 Retired (invalid segment length) [IPDS-16-1521]
Action code: X'01' or X'1F'
Explanation: The segment length resulting from a Begin
Segment New or Append operation exceeds the maximum
permitted.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0370..83 Retired (invalid segment length for [IPDS-16-1522]
Replace option)
Action code: X'01' or X'1F'
Explanation: The Begin Segment Seglength exceeds the
existing segment length in a Replace operation.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0370..84 Retired (invalid segment identifier for [IPDS-16-1523]
Replace or Append)
Action code: X'01' or X'1F'
Explanation: The segment specified in Begin Segment for
a Replace or Append operation does not exist.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0370..C4 Retired (insufficient segment storage)
Action code: X'01' or X'1F'
Explanation: There is insufficient segment storage to hold
the segment specified with a New or Append operation.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
03C6..00 Retired (arc ending error)
Action code: X'01' or X'1F'
Explanation: The start and end points of the arc are
identical.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
03C6..02 Retired (arc parameter check)
Action code: X'01' or X'1F'
Explanation: The arc parameters define a degenerate
ellipse when a three point arc order is executed.
Reason for IPDS Retirement: Used in the GOCA subset
DR/3
0332..02 • 03C6..02 [IPDS-16-1524]


Specification Check—General Exceptions
A specification check—general exception indicates
that the printer has received a command with an
invalid or unsupported parameter or value. This
exception class contains specification checks that
are common to all IPDS data types.
Format 0 is used for X'0200..03', X'0204..03',
X'0220..03', X'022F ..02', X'0230..01', X'024C..03',
X'0254..00', X'0254..10', X'0259..01', X'0259..02',
X'025A..01', X'025A..02', X'0266..02' (on the IBM
3820 printer), X'0290..03', X'0291..03', X'02EA..01', [IPDS-16-1525]
X'02EB..00', X'02EC..01', and X'02EC..02'.
All other general specification-check exceptions
(including X'0266..02' on the IBM 3800 printer) are
retired for the IBM 3800 printer and use an IBM 3800
printer-specific format. Refer to Reference Manual
for the IBM 3800 Printing Subsystem Model 8 for a
description of IBM 3800 sense-data formats.
0200..02 Retired (not enough raster-pattern storage [IPDS-16-1526]
to load non-stageable font)
Action code: X'0C'
Explanation: There is not enough raster-pattern storage
to load the non-stageable font.
Reason for IPDS Retirement: Used in IBM 3800 printers
0200..03 Retired (character exceeds presentation [IPDS-16-1527]
text object space)
Action code: X'01'
Explanation: A character has been positioned so that a
portion of its character box will exceed the space in the i-
direction or the b-direction.
Reason for IPDS Retirement: Used in IBM 4224 and
4234 printers [IPDS-16-1528]
0201..01 Retired (Set Text Orientation without Set [IPDS-16-1529]
Coded Font Local)
Action code: X'01'
Explanation: For single-byte fonts only, a Set Text
Orientation text control was received and no Set Coded
Font Local text control was specified.
Reason for IPDS Retirement: Used in IBM 3800 printers
0201..02 Retired (not enough control storage) [IPDS-16-1530]
Action code: X'0C'
Explanation: There is not enough control storage and no
Accumulator feature is installed.
Reason for IPDS Retirement: Used in IBM 3800 printers
0203..01 Retired (factored text control location [IPDS-16-1531]
found in control field)
Action code: X'01'
Explanation: A factored text control location was found in
a control field.
Reason for IPDS Retirement: Used in IBM 3800 printers
0204..03 Retired (for PTOCA) [IPDS-16-1532]
Action code: X'01'
Explanation: This code was removed from the PTOCA
and IPDS architectures in 1991, but might have been used
in early AFP products.
Reason for IPDS Retirement: Used in PTOCA at one
time
0207..01 Retired (Begin Suppression found within [IPDS-16-1533]
an overlay)
Action code: X'01'
Explanation: A Begin Suppression text control was found
within an overlay.
Reason for IPDS Retirement: Used in IBM 3800 printers
0209..01 Retired (factored text control location [IPDS-16-1534]
found in a double-byte font character
code)
Action code: X'01'
Explanation: A factored text control location was found in
a double-byte font character code.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
020A..01 Retired (font not valid for current text
orientation)
Action code: X'01'
Explanation: The font is not valid for the current text
orientation.
Reason for IPDS Retirement: Used in IBM 3800 printers
0211..02 Retired (X [IPDS-16-1535]
p density units not valid)
Action code: X'01'
Explanation: The Xp density units are not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0213..02 Retired (font not loaded) [IPDS-16-1536]
Action code: X'01'
Explanation: A font equivalence table exists, but the font
is not loaded.
0200..02 • 0213..02 [IPDS-16-1537]


Reason for IPDS Retirement: Used in IBM 3800 printers
0216..02 Retired (invalid Delete Font command) [IPDS-16-1538]
Action code: X'01'
Explanation: The Delete Font command is not valid (the
font is associated with an overlay).
Reason for IPDS Retirement: Used in IBM 3800 printers
0218..01 Retired (source string length not valid) [IPDS-16-1539]
Action code: X'01'
Explanation: The source string length is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
021A..02 Retired (attempt to delete a resident font)
Action code: X'01'
Explanation: The Delete Font command is not valid. The
ID of the font specified for deletion is the ID of a resident
font that cannot be deleted.
Reason for IPDS Retirement: Used in IBM 3800 printers
021D..01 Retired (invalid Transparent Data text
control length field value)
Action code: X'01'
Explanation: A Transparent Data text control length field
value is odd, but a double-byte font has been selected.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0220..03 Retired (invalid VSP code) [IPDS-16-1540]
Action code: X'01' or X'1F'
Explanation: The Variable Space code has been
assigned the same code as the control-sequence prefix.
Reason for IPDS Retirement: Used in PTOCA; also
retired in PTOCA
0223..01 Retired (not enough RPS) [IPDS-16-1541]
Action code: X'0E'
Explanation: There is not enough raster-pattern storage
(RPS); page printed using the Accumulator feature.
Reason for IPDS Retirement: Used in IBM 3800 printers
0224..01 Retired (not enough control storage) [IPDS-16-1542]
Action code: X'0E'
Explanation: There is not enough control storage; page
printed using the Accumulator feature.
Reason for IPDS Retirement: Used in IBM 3800 printers
0224..02 Retired (one or both inline offset values [IPDS-16-1543]
not equal to zero for a double-byte font)
Action code: X'01'
Explanation: One or both inline offset values are not
equal to zero for a double-byte font.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0225..01 Retired (not enough raster-pattern storage [IPDS-16-1544]
for requested font)
Action code: X'0C'
Explanation: There is not enough raster-pattern storage
for the requested font.
Reason for IPDS Retirement: Used in IBM 3800 printers
0225..02 Retired (invalid inline offset value) [IPDS-16-1545]
Action code: X'01'
Explanation: The inline offset value is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0226..01 Retired (not enough raster-pattern storage [IPDS-16-1546]
for default font)
Action code: X'0C'
Explanation: These is not enough raster-pattern storage
for the default font.
Reason for IPDS Retirement: Used in IBM 3800 printers
0227..01 Retired (not enough control storage for [IPDS-16-1547]
character processing)
Action code: X'0C'
Explanation: There is not enough control storage for
character processing.
Reason for IPDS Retirement: Used in IBM 3800 printers
0228..01 Retired (not enough control storage for [IPDS-16-1548]
suppression string)
Action code: X'0C'
Explanation: There is not enough control storage for the
suppression string.
Reason for IPDS Retirement: Used in IBM 3800 printers
022C..01 Retired (not enough control storage for
font index table)
Action code: X'0C'
Explanation: There is not enough control storage for the
requested font index table.
Reason for IPDS Retirement: Used in IBM 3800 printers
022C..02 Retired (font index byte count not valid)
Action code: X'01'
Explanation: The font index byte count is not valid (must
be 2048).
Reason for IPDS Retirement: Used in IBM 3800 printers
0216..02 • 022C..02 [IPDS-16-1549]


022D..01 Retired (not enough control storage for
character table expansion at print time.)
Action code: X'0C'
Explanation: There was not enough control storage for
character table expansion at print time.
Reason for IPDS Retirement: Used in IBM 3800 printers
022E..01 Retired (additional Write Text command
needed to complete a double-byte
character code)
Action code: X'01'
Explanation: A non-Write-Text command was received
and an additional Write Text command is needed to
complete a double-byte character code.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
022F ..02 Retired (too many font qualities)
Action code: X'01'
Explanation: An attempt was made to use a loaded font
ID in multiple qualities on the same page.
Reason for IPDS Retirement: Used in IBM 4224 printer
0230..01 Retired (not enough control storage for [IPDS-16-1550]
copy control)
Action code: X'0C'
Explanation: There was not enough control storage for
copy control.
Reason for IPDS Retirement: Used in IBM 3820 printers
0230..02 Retired (invalid font constant character [IPDS-16-1551]
increment)
Action code: X'01'
Explanation: The font constant character increment is not
valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0231..02 Retired (invalid font orientation count) [IPDS-16-1552]
Action code: X'01'
Explanation: The font orientation count is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0235..01 Retired (invalid forms flash value) [IPDS-16-1553]
Action code: X'01'
Explanation: The forms flash value is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0235..02 Retired (invalid font orientation) [IPDS-16-1554]
Action code: X'01'
Explanation: The font orientation is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0236..02 Retired (invalid font baseline offset) [IPDS-16-1555]
Action code: X'01'
Explanation: The font baseline offset is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0237..02 Retired (invalid font orientation flags) [IPDS-16-1556]
Action code: X'01'
Explanation: The font orientation flags are not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0238..02 Retired (double-byte fonts not supported ) [IPDS-16-1557]
Action code: X'01'
Explanation: Double-byte fonts are not supported.
Double-byte fonts are used only in the 3800 Model 8.
Reason for IPDS Retirement: Used in IBM 3800 printers
023A..01 Retired (maximum number of forms flash
entries exceeded)
Action code: X'01'
Explanation: The maximum number of forms flash entries
were exceeded.
Reason for IPDS Retirement: Used in IBM 3800 printers
023D..02 Retired (invalid CCW sequence during font
loading)
Action code: X'01'
Explanation: The CCW sequence during font loading was
not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0241..02 Retired (double-byte font section ID too [IPDS-16-1558]
small)
Action code: X'01'
Explanation: The double-byte font section ID is smaller
than the font section ID stored earlier.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0242..02 Retired (unmatched font control values) [IPDS-16-1559]
Action code: X'01'
Explanation: There was no match of font control values
between the existing and earlier font sections.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
024B..01 Retired (I or X value too large in a Write
Image Control command)
Action code: X'01'
022D..01 • 024B..01


Explanation: In a Write Image Control command, the first
pel location value and L-units value produce too large an I
or X value.
Reason for IPDS Retirement: Used in IBM 3800 printers
024C..01 Retired (B or Y value too large in a Write
Image Control command)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location value and L-units value generate too large a B
or Y value.
Reason for IPDS Retirement: Used in IBM 3800 printers
024C..03 Retired (for PTOCA)
Action code: X'01'
Explanation: This code was removed from the PTOCA
and IPDS architectures in 1991, but might have been used
in early AFP products.
Reason for IPDS Retirement: Used in PTOCA at one
time
024D..01 Retired (first pel location absolute X
coordinate value outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location absolute X coordinate is outside the valid
range.
Reason for IPDS Retirement: Used in IBM 3800 printers
024E..01 Retired (first pel location absolute Y
coordinate outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location absolute Y coordinate is outside the valid
range.
Reason for IPDS Retirement: Used in IBM 3800 printers
024F ..01 Retired (first pel location absolute I
coordinate outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location absolute I coordinate is outside the valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
024F ..02 Retired (double-byte font count not a
multiple of four bytes)
Action code: X'01'
Explanation: The double-byte font count is not a multiple
of four bytes.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0250..01 Retired (first pel location absolute B [IPDS-16-1560]
coordinate outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location absolute B coordinate is outside the valid
range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0251..01 Retired (first pel location relative I value [IPDS-16-1561]
outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location relative I value is outside the valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0252..01 Retired (first pel location relative B value [IPDS-16-1562]
outside valid range)
Action code: X'01'
Explanation: In a Write Image Control command, the first
pel location relative B value is outside the valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0252..02 Retired (too many Load Font Index [IPDS-16-1563]
commands)
Action code: X'01'
Explanation: Two Load Font Index commands received,
but the specification is for one.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0253..02 Retired (Load Font Index command CCW [IPDS-16-1564]
count greater than 2 for EXGRF font
selection)
Action code: X'01'
Explanation: The Load Font Index command CCW count
is greater than 2 for EXGRF font selection.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
0254..00 Retired (Y [IPDS-16-1565]
p orientation in Presentation
Space Orientation Initial Text Condition
parameter invalid or unsupported)
Action code: X'01'
Explanation: The Yp orientation specified in the
Presentation Space Orientation Initial Text Condition
parameter is invalid or unsupported.
Reason for IPDS Retirement: Used in PTOCA; also
retired in PTOCA
0254..10 Retired (control-sequence prefix code [IPDS-16-1566]
invalid or unsupported)
Action code: X'01'
024C..01 • 0254..10


Explanation: A control-sequence prefix code is invalid or
unsupported.
Reason for IPDS Retirement: Used in PTOCA; also
retired in PTOCA
0259..01 Retired (invalid or unsupported resource [IPDS-16-1567]
type in a LHL command)
Action code: X'01'
Explanation: In a Load HAID List (LHL) command, a
nesting type or nested type value is invalid or unsupported.
Reason for IPDS Retirement: Used in use with the
retired LHL command
0259..02 Retired (invalid HAID in a LHL command) [IPDS-16-1568]
Action code: X'01'
Explanation: In a Load HAID List (LHL) command, a
nesting HAID or nested HAID value is invalid.
Reason for IPDS Retirement: Used in the retired LHL
command
025A..01 Retired (HAID missing from a master HAID
list)
Action code: X'01'
Explanation: When an attempt was made to activate a
nesting resource, an Include Overlay, Include Page
Segment, or Load Font Equivalence command contained a
HAID that did not have a corresponding nested-HAID entry
in the master HAID list.
Reason for IPDS Retirement: Used in the retired LHL
command
025A..02 Retired (too many or too few nested HAIDs
in a replacement HAID list)
Action code: X'01'
Explanation: When an attempt was made to activate a
nesting resource, the number of nested-HAID entries in the
replacement HAID list for the nesting resource did not
match the number of entries in a previously received
master HAID list for the same resource.
Reason for IPDS Retirement: Used in the retired LHL
command
0266..02 Retired (invalid Load Page Description [IPDS-16-1569]
command control byte)
Action code: X'01'
Explanation: In a Load Page Description command, the
control byte is not valid.
Reason for IPDS Retirement: Used in IBM 3800 and
3820 printers [IPDS-16-1570]
0267..02 Retired (invalid Load Page Description [IPDS-16-1571]
command data check byte)
Action code: X'01'
Explanation: In a Load Page Description command, the
allow data check byte is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0271..02 Retired (invalid Load Page Description [IPDS-16-1572]
command escape code)
Action code: X'01'
Explanation: In a Load Page Description command, the
escape code is not valid.
Reason for IPDS Retirement: Used in IBM 3800 printers
0272..01 Retired (raster overlay was specified [IPDS-16-1573]
without an accumulator feature)
Action code: X'01'
Explanation: A raster overlay is specified, but no
accumulator feature is installed.
Reason for IPDS Retirement: Used in IBM 3800 printers
0273..01 Retired (raster overlay already loaded) [IPDS-16-1574]
Action code: X'01'
Explanation: A raster overlay was already loaded.
Reason for IPDS Retirement: Used in IBM 3800 printers
0274..01 Retired (fonts named in font equivalence [IPDS-16-1575]
table not available)
Action code: X'01'
Explanation: The fonts named in a font equivalence table
were not available when the Begin Overlay command was
received.
Reason for IPDS Retirement: Used in IBM 3800 printers
0275..01 Retired (too little control storage available) [IPDS-16-1576]
Action code: X'0C'
Explanation: Not enough control storage is available to
start a raster overlay.
Reason for IPDS Retirement: Used in IBM 3800 printers
0278..02 Retired (factored text control record ended [IPDS-16-1577]
in the middle of a text control sequence)
Action code: X'01'
Explanation: A factored text control record ended in the
middle of a text control sequence.
Reason for IPDS Retirement: Used in IBM 3800 printers
0279..02 Retired (factored text control location [IPDS-16-1578]
number smaller than the last location
number)
Action code: X'01'
Explanation: A factored text control location number is
smaller than the last location number.
Reason for IPDS Retirement: Used in IBM 3800 printers
0259..01 • 0279..02 [IPDS-16-1579]


0281..02 Retired (inline rule thickness less than one [IPDS-16-1580]
pel)
Action code: X'01'
Explanation: The inline rule thickness is less than one
pel.
Reason for IPDS Retirement: Used in IBM 3800 printers
0283..02 Retired (baseline rule thickness outside [IPDS-16-1581]
valid range)
Action code: X'01'
Explanation: The baseline rule thickness is outside the
valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0284..02 Retired (baseline rule thickness less than [IPDS-16-1582]
one pel)
Action code: X'01'
Explanation: The baseline rule thickness is less than one
pel.
Reason for IPDS Retirement: Used in IBM 3800 printers
0285..02 Retired (baseline rule length outside valid [IPDS-16-1583]
range)
Action code: X'01'
Explanation: The baseline rule length is outside the valid
range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0290..02 Retired (Xp adjustment range value outside [IPDS-16-1584]
valid range)
Action code: X'01'
Explanation: The Xp adjustment range value is outside
the valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
0290..03 Retired (invalid or unsupported value for [IPDS-16-1585]
overstrike text control)
Action code: X'01'
Explanation: This code was removed from the PTOCA
and IPDS architectures in 1991, but might have been used
in early AFP products.
Reason for IPDS Retirement: Used in PTOCA at one
time
0291..03 Retired (for PTOCA) [IPDS-16-1586]
Action code: X'01'
Explanation: This code was removed from the PTOCA
and IPDS architectures in 1991, but might have been used
in early AFP products.
Reason for IPDS Retirement: Used in PTOCA at one
time
02A0..01 Retired (page origin X coordinate outside
valid range)
Action code: X'01'
Explanation: The page origin X coordinate is outside the
valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
02A1..01 Retired (page origin Y coordinate outside
valid range)
Action code: X'01'
Explanation: The page origin Y coordinate is outside the
valid range.
Reason for IPDS Retirement: Used in IBM 3800 printers
02A2..01 Retired (page rotation not valid or reserved
bytes contain nonzero values)
Action code: X'01'
Explanation: The page rotation is not valid or reserved
bytes contain nonzero values.
Reason for IPDS Retirement: Used in IBM 3800 printers
02A8..01 Retired (insufficient code-page storage for
double-byte font)
Action code: X'0C'
Explanation: There is not enough code-page storage for
the double-byte font.
Reason for IPDS Retirement: Used in IBM 3800 model 8
printers
02A9..01 Retired (insufficient control storage to
start page)
Action code: X'0C'
Explanation: Not enough control storage to start the
page.
Reason for IPDS Retirement: Used in IBM 3800 printers
02EA..01 Retired (print data remains in buffer)
Action code: X'01'
Explanation: This is a proprietary code.
Reason for IPDS Retirement: Used in some Océ printers
02EB..00 Retired (invalid or unsupported proprietary
cut-sheet-emulation parameter)
Action code: X'01'
Explanation: This was used for an early implementation
of cut-sheet emulation.
Reason for IPDS Retirement: Used in some Océ printers
02EC..01 Retired (unsupported XOH Configurations
ID)
0281..02 • 02EC..01 [IPDS-16-1587]


Action code: X'01'
Explanation: This is a proprietary code.
Reason for IPDS Retirement: Used in some Océ printers
02EC..02 Retired (invalid or unsupported parameter
value in an XOH Configurations ID order)
Action code: X'01'
Explanation: This is a proprietary code.
Reason for IPDS Retirement: Used in some Océ printers
02EC..02 • 02EC..02


Conditions Requiring Host Notification
A condition requiring host notification indicates that
the printer has detected a condition that, while not an
error, should be reported to the host. There are no
AEAs for this class of exception.
Format 5 is used for X'01A0..00', X'01A1..00',
X'01A2..00', and X'01A3..00'.
01A0..00 Retired (printer assigned elsewhere)
Action code: X'25'
Explanation: The printer is assigned elsewhere.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
01A1..00 Retired (sense reset due to reset
allegiance)
Action code: X'04'
Explanation: A sense reset occurred due to reset
allegiance.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
01A2..00 Retired (operation terminated due to reset
allegiance)
Action code: X'04'
Explanation: An operation was terminated due to reset
allegiance.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
01A3..00 Retired (resetting event)
Action code: X'4D'
Explanation: A resetting event has occurred.
Reason for IPDS Retirement: Used in serial-channel-
attached printers
01A0..00 • 01A3..00


