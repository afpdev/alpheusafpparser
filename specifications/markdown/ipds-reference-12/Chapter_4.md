# Chapter 4. Device-Control Command Set
The Device-Control command set is composed of commands and an acknowledge protocol. The commands
are used to set up the logical page environment, to manage resources, and to communicate device controls.
The acknowledge protocol is used to transmit printer characteristics, status, resource information, and error
information to the presentation services program. This command set contains the following commands:
**Table 19**. Device Control Commands [IPDS-4-001]

| Command | Code | Description | In DC1 Subset? |
| :--- | :---: | :--- | :---: |
| AR | X'D62E' | Activate Resource | No [IPDS-4-002]|
| ASN | X'D60A' | Activate Setup Name | No [IPDS-4-003]|
| AFO | X'D602' | Apply Finishing Operations | No [IPDS-4-004]|
| BP | X'D6AF' | Begin Page | Yes [IPDS-4-005]|
| DF | X'D64F' | Deactivate Font | Yes [IPDS-4-006]|
| DUA | X'D6CE' | Define User Area | No [IPDS-4-007]|
| END | X'D65D' | End | Yes [IPDS-4-008]|
| EP | X'D6BF' | End Page | Yes [IPDS-4-009]|
| ISP | X'D67E' | Include Saved Page | No [IPDS-4-010]|
| ICMR | X'D66B' | Invoke CMR | No [IPDS-4-011]|
| LCC | X'D69F' | Load Copy Control | Yes [IPDS-4-012]|
| LFE | X'D63F' | Load Font Equivalence | Yes [IPDS-4-013]|
| LPD | X'D6CF' | Logical Page Descriptor | Yes [IPDS-4-014]|
| LPP | X'D66D' | Logical Page Position | Yes [IPDS-4-015]|
| MID | X'D601' | Manage IPDS Dialog | No [IPDS-4-016]|
| NOP | X'D603' | No Operation | Yes [IPDS-4-017]|
| PFC | X'D634' | Presentation Fidelity Control | No [IPDS-4-018]|
| RPO | X'D67B' | Rasterize Presentation Object | No [IPDS-4-019]|
| STM | X'D6E4' | Sense Type and Model | Yes [IPDS-4-020]|
| SHS | X'D697' | Set Home State | Yes [IPDS-4-021]|
| SPE | X'D608' | Set Presentation Environment | No [IPDS-4-022]|
| XOA | X'D633' | Execute Order Anystate | See command description [IPDS-4-023]|
| XOH | X'D68F' | Execute Order Home State | See command description [IPDS-4-024]|
| | X'D61C' | Retired Command Code | No [IPDS-4-025]|

**Table 20**. Acknowledge Protocol [IPDS-4-026]

| Reply | Code | Description | In DC1 Subset? |
| :--- | :---: | :--- | :---: |
| ACK or NACK | X'D6FF' | Acknowledge Reply | Yes [IPDS-4-027]|


The printer uses the Acknowledge Reply to return information such as page counters, copy counters, sense
data, and any requested information to the presentation services program. The presentation services program
uses the acknowledge data to maintain control over the printing process and to initiate exception-recovery
procedures when necessary.
The printer sends an acknowledgment at the following times:
• When the acknowledgment required (ARQ) bit in the flag byte of a received IPDS command is set to B'1' [IPDS-4-028]
• When the printer detects an exception and sends a negative response to the presentation services program [IPDS-4-029]
A negative Acknowledge Reply (NACK) has priority over a positive Acknowledge Reply (ACK) and causes the
ACK to be discarded. Although the Acknowledge Reply goes from the printer to the presentation services
program, its format is identical to the format used by IPDS commands. Bit 0 and bits 3–5 of the flag byte
(byte 4) are reserved and should be set to zero.
Note: If a CID has been supplied for a command that results in a NACK and if the printer can identify this
command, the NACK CID present bit (flag bit 1) must be set to B'1' and the NACK must contain the
matching CID in the two bytes following the flag byte. Otherwise, the CID-present bit for the NACK is set
to B'0', and the NACK data field immediately follows the flag byte.
Both the ACK and the NACK are returned using the same format. Even though the replies are sent from the
printer to the presentation services program, they use the same format as the IPDS commands:
Length X'D6FF' Flag Data
or
```
Length X'D6FF' Flag CID Data
```
The length field is the total length of the ACK or NACK reply, including the length field itself. All IPDS devices
must support short Acknowledge Replies (up to 256 bytes long); some IPDS devices can also return longer
Acknowledge Replies (up to 65,535 bytes long). Property pair X'F003' is returned in the Device-Control
command-set vector of an STM reply by printers that support long ACKs. The host can control whether or not
the long ACK method is used by setting the long ACK flag (byte 4, bit 3) in any command that requests an ACK
(ARQ flag is B'1').
Acknowledge Reply


The flag field is a one-byte field that specifies the flags. Bits 1, 2, 6, and 7 are the only bits used in this byte. All
other bits are reserved and should be set to B'0'.
• If bit 1 of the flag byte is B'1', a correlation ID is present. If bit 1 of the flag byte is B'0', no correlation ID is [IPDS-4-030]
present and the data field (if any) immediately follows the flag byte.
• If bit 2 of the flag byte is B'1', this response can be continued in a subsequent Acknowledge Reply; refer to [IPDS-4-031]
the following note. If bit 2 of the flag byte is B'0', this response is complete in this Acknowledge Reply.
• Bit 6 of the flag byte can only be set to B'1' for NACKs, and indicates that additional information is available [IPDS-4-032]
for the exception reported by the NACK. This additional information can be obtained by sending the XOA
Obtain Additional Exception Information (OAEI) command. A printer can make additional exception
information available—and thus this bit can be set to B'1'—only if the printer is ready to respond to an
immediate OAEI command to retrieve that information. If bit 6 of the flag byte is B'0', no additional
information is available for the exception. This bit is ignored for ACKs.
• Bit 7 is the Persistent NACK bit for DSC Mode NACKs, but this bit has no meaning in other attachment [IPDS-4-033]
environments.
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 4 | BITS | IPDS Acknowledge Reply flags [IPDS-4-034]| | |
| | bit 0 | | B'0' | Reserved [IPDS-4-035]|
| | bit 1 | CID flag | B'0', B'1' | Correlation ID flag. If this bit is B'1', a two-byte correlation ID follows the flag byte. If this bit is B'0', the optional correlation ID is not present, and the following byte or bytes, if any, contain the data field. [IPDS-4-036]|
| | bit 2 | ACK continuation flag | B'0', B'1' | Acknowledgment continuation flag. If this bit is B'1', this response can be continued in a subsequent Acknowledge Reply. If this bit is B'0', this response is complete in this Acknowledge Reply. [IPDS-4-037]|
| | bits 3–5 | | B'000' | Reserved [IPDS-4-038]|
| | bit 6 | Additional information flag | B'0', B'1' | Additional exception information available flag. If this bit is B'1', this indicates that additional information is available for the exception reported by this NACK. This additional information can be obtained by sending the XOA Obtain Additional Exception Information (OAEI) command. A printer can make additional exception information available—and thus this bit can be B'1'—only if the printer is ready to respond to an immediate OAEI command to retrieve that information. If this bit is B'0', no additional information is available. This bit is only valid for NACKs and is ignored for ACKs. [IPDS-4-039]|
| | bit 7 | Persistent NACK flag | B'0', B'1' | Persistent NACK flag. This flag has meaning only in DSC-Mode NACKs; the flag has no meaning in all other attachment environments. [IPDS-4-040]|
Note: If the printer determines that the complete response exceeds the maximum size of the Special Data
Area, the Acknowledgment Continuation bit is set to B'1'. To obtain the next segment of the response
from the printer, the presentation services program must issue a command (any command) with both the
Acknowledgment Continuation bit and the ARQ bit set to B'1'. In this case, the command code is ignored
by the printer and the next segment of the response is sent to the presentation services program.
If the printer returns a correlation ID, it is the correlation ID provided in the command requesting the
acknowledgment. The informational response continues starting with the first byte in the Special Data
Acknowledge Reply


Area of the next segment of the Acknowledge Reply; the Acktype and Counters fields are present in
each segment of the Acknowledge Reply. The Acknowledge Reply length is used to determine how
much data is available in each Special Data Area. The total length of the data returned by the printer can
be determined by adding the lengths of each of the Special Data Areas in the sequence. The
Acknowledgment Continuation bit is B'0' in the last segment of the response sent to the presentation
services program.
The printer ends an Acknowledge Reply sequence when either the specific information is completely
transmitted, or the presentation services program sends a command with the Acknowledgment
Continuation bit set to B'0', or an exception occurs that must be reported. If the presentation services
program ends the Acknowledge Reply sequence by specifying B'0' in the Acknowledgment Continuation
bit, the printer processes the command after discarding any unsent Acknowledge Reply data.
This method of returning the segments of a long Acknowledge Reply is called the ACK-continuation
method. The XOA-RRL command provides an alternate method of controlling large XOA-RRL replies
using an entry-continuation indicator; this alternate method is called the RRL-continuation method.
Printers that can return XOA-RRL replies larger than 256 bytes in length must support both methods,
although some older IPDS printers only support the RRL-continuation method and therefore do not set
the acknowledgment continuation flag to B'1' in the Acknowledge Replies.
If the acknowledgment continuation flag is set to B'1' in an XOA-RRL Acknowledge Reply, the
presentation services program can use either method to obtain the next reply in the sequence. The
presentation services program should not switch between the two methods within a sequence of replies.
For the XOA-RRL command only, if the ARQ bit is B'1', and if either the acknowledgment-continuation
flag in the next command is set to B'1' (ACK-continuation method) or the next command is an XOA-RRL
command and the entry-continuation indicator is set to a non-zero value (RRL-continuation method), the
printer returns the next portion of the resource list. If both methods are used simultaneously, the
acknowledgment-continuation flag in the XOA-RRL command is ignored and the RRL-continuation
method is used by the printer; an XOA-RRL reply sequence is ended with a reply-list entry with a length
of X'01'.
The CID field is a two-byte field that contains the correlation identifier from a previously received command. If
the printer receives a command that requires an acknowledgment, and if that command contains a CID, the
printer includes the same CID in the corresponding field of the ACK. Also, if a synchronous exception is
reported and the printer can identify the command in error, the CID of the command that caused the exception
is returned in the NACK.
The data field follows either the CID field or the flag byte. The data field contains the acknowledge type, page
and copy counters, and can also contain a special data area.
Acknowledge Reply


Acknowledge Reply Data Format [IPDS-4-041]

The Acknowledge Reply data field contains the acknowledge type, the page and copy counters, and the special data area. The two formats of the Acknowledge Reply data field are as follows: [IPDS-4-042]

**Four-Byte Page and Copy Counter Format** [IPDS-4-043]
| Type (Byte 0) | Page and Copy Counters (Bytes 1–4) | Special Data Area (Byte 5 to end) |
| :---: | :---: | :--- |

**Eighteen-Byte Page and Copy Counter Format** [IPDS-4-044]
| Type (Byte 0) | Page and Copy Counters (Bytes 1–18) | Special Data Area (Byte 19 to end) |
| :---: | :---: | :--- |

The complete Acknowledge Reply is limited to either 256 bytes or 65,535 bytes depending on whether or not the printer supports long ACKs (indicated by property pair X'F003' in the Device-Control command-set vector of an STM reply). The maximum size of the special data area depends on the format used. Although an Acknowledge Reply is limited to a maximum of 256 bytes (or 65,535 bytes) successive Acknowledge Replies, each less than or equal to 256 bytes (or 65,535 bytes) in length, can be used to communicate responses longer than the Special Data Area in one Acknowledge Reply. The Acknowledgment Continuation bit is used to signal availability of more reply data. [IPDS-4-045]

| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Acktype | See byte description | Acknowledge type. Determines the content of the remaining fields; see byte 0 description. | Either all of the X'00', X'01', X'04', X'06', and X'80' values or all of the X'40', X'41', X'44', X'46', and X'C0' values; see byte 0 description. [IPDS-4-046]|
| 1–4 -or- 1–18 | UBIN | Counters | See byte description | Page and copy counters. See byte description. [IPDS-4-047]| |
| 5 to end -or- 19 to end | SDA | SDA | Depends on acktype (byte 0) | Special data area. Depends on acktype (byte 0). [IPDS-4-048]| |
Acknowledge Reply


Byte 0 Acknowledge type
This one-byte field identifies the acknowledge type, the contents of the page and copy
counters, and the special data area. The values that can be returned in this field are as
follows:
**Table 21**. Acknowledge Types [IPDS-4-049]

| Value | Meaning | Page and Copy Counter Format | Special Data Area |
| :---: | :--- | :--- | :--- |
| X'00' | No data | Four-byte | None [IPDS-4-050]|
| X'40' | No data | Eighteen-byte | None [IPDS-4-051]|
| X'01' | STM reply | Four-byte | Sense Type and Model reply [IPDS-4-052]|
| X'41' | STM reply | Eighteen-byte | Sense Type and Model reply [IPDS-4-053]|
| X'02' | Trace reply | Four-byte | Trace reply [IPDS-4-054]|
| X'42' | Trace reply | Eighteen-byte | Trace reply [IPDS-4-055]|
| X'03' | RRRL reply | Four-byte | Request Resident Resource List reply [IPDS-4-056]|
| X'43' | RRRL reply | Eighteen-byte | Request Resident Resource List reply [IPDS-4-057]|
| X'04' | RRL reply | Four-byte | Request Resource List reply [IPDS-4-058]|
| X'44' | RRL reply | Eighteen-byte | Request Resource List reply [IPDS-4-059]|
| X'05' | OAEI reply | Four-byte | Obtain Additional Exception Information reply [IPDS-4-060]|
| X'45' | OAEI reply | Eighteen-byte | Obtain Additional Exception Information reply [IPDS-4-061]|
| X'06' | OPC reply | Four-byte | Obtain Printer Characteristics reply [IPDS-4-062]|
| X'46' | OPC reply | Eighteen-byte | Obtain Printer Characteristics reply [IPDS-4-063]|
| X'07' | ASN reply | Four-byte | Activate Setup Name reply [IPDS-4-064]|
| X'47' | ASN reply | Eighteen-byte | Activate Setup Name reply [IPDS-4-065]|
| X'08' | RSNL reply | Four-byte | Request Setup Name List reply [IPDS-4-066]|
| X'48' | RSNL reply | Eighteen-byte | Request Setup Name List reply [IPDS-4-067]|
| X'80' | Sense data | Four-byte | Sense bytes [IPDS-4-068]|
| X'C0' | Sense data | Eighteen-byte | Sense bytes [IPDS-4-069]|
| X'FF' | Null ACK | No counters provided | This acknowledgment is returned if the presentation services program, using the communications protocol that carries IPDS commands, attempts to obtain a positive Acknowledge Reply (ACK) without first sending an IPDS command with the ARQ bit set to B'1'. This is considered to be an error at the IPDS level. [IPDS-4-070]|
Bytes 1–4 or
1–18
Page and Copy Counters
These fields contain counter values that specify the number of pages and copies of pages that
have passed various printer stations. These counters allow presentation services program
software to track the movement of pages and copies of pages through the printer for queue
management and exception recovery. The received page counter is incremented by one each
time a page is received, accepted, and syntax checked by the printer. All other counters are
incremented when a sheet passes the applicable printer station by the number of pages or
copies of pages on the sheet. When duplexing is active, the applicable page or copy counter,
excluding the Received Page Counter, is incremented by the number of pages or copies of
pages on both the front and back of a sheet when the sheet passes the printer station.
Note: The format of the counters is modulo-64K. These counters are further described in
“Page and Copy Counter Adjustments”.
Acknowledge Reply


Some printers use 4 bytes for the page and copy counters; others use 18 bytes. The specific
format can be determined from the acknowledge type in byte 0 and from your printer
documentation.
Four-Byte Counter Format
The four-byte format uses two counters to define a single printer station where pages have
been completely processed by the printer. A page consists of a series of commands received
from the presentation services program that starts with a Begin Page command and ends with
an End Page command. The specific counters returned are as follows:
Bytes 1–2 Stacked page counter
This counter contains the number of pages that were successfully stacked.
This counter increments by the number of pages on a sheet when the last
copy of the sheet is successfully stacked.
Bytes 3–4 Stacked copy counter
This counter contains the number of copies of all pages on successfully
stacked sheets that have not yet been accounted for by the stacked page
counter in bytes 1–2. For all but the last sheet in the set of copies, this counter
increments by the number of pages on a sheet when the sheet is successfully
stacked. This counter resets to zero when the stacked page counter is
incremented.
Eighteen-Byte Counter Format
The eighteen-byte format uses nine counters to define a generic paper path with five printer
stations. A page consists of a series of commands received from the presentation services
program that starts with a Begin Page command and ends with an End Page command. The
specific counters returned are as follows:
Bytes 1–2 Received page counter
This counter contains the number of pages that were received, accepted, and
syntax-checked. Refer to “General Rules for the Acknowledge Reply”. This counter increments after an End Page command is
processed. This counter is set equal to the committed page counter, bytes 3–
4, after processing the XOA Discard Buffered Data command. This counter is
set equal to the stacked page counter, bytes 15–16, after processing the XOA
Discard Unstacked Pages command.
Bytes 3–4 Committed page counter
This counter contains the number of pages that were committed for printing. A
page is committed for printing when it can no longer be discarded by the XOA
Discard Buffered Data command. This counter increments by the number of
pages on a sheet when the last copy of the sheet is committed for printing.
This point represents where print data should be canceled by the presentation
services program when the printer Cancel key is pressed.
Bytes 5–6 Committed copy counter
This counter contains the number of copies of all pages on successfully
committed sheets that have not yet been accounted for by the committed
page counter in bytes 3–4. For all but the last sheet in the set of copies, this
counter increments by the number of pages on a sheet when the sheet is
committed for printing. This counter resets to zero when the committed page
counter increments.
Acknowledge Reply


Bytes 7–8 Operator viewing page counter
This counter contains the number of pages that have passed the view of the
printer operator. This counter increments by the number of pages on a sheet
when the last copy of the sheet passes the view of the printer operator.
Bytes 9–10 Operator viewing copy counter
This counter contains the number of copies of all pages on sheets that have
passed the view of the printer operator and have not yet been accounted for
by the operator viewing page counter in bytes 7–8. For all but the last sheet in
the set of copies, this counter increments by the number of pages on a sheet
when the sheet passes the view of the printer operator. This counter resets to
zero when the operator viewing page counter is incremented.
Bytes 11–12 Jam recovery page counter
This counter contains the number of pages that have passed the printer-
defined jam recovery point. Such pages need not be retransmitted to the
printer in the event of a jam. This counter increments by the number of pages
on a sheet when the last copy of the sheet passes the jam-recovery point.
Bytes 13–14 Jam recovery copy counter
This counter contains the number of copies of all pages on sheets that have
passed the jam recovery point but have not yet been accounted for by the jam
recovery page counter in bytes 11–12. For all but the last sheet in the set of
copies, this counter increments by the number of pages on a sheet when the
sheet passes the jam-recovery point. This counter resets to zero when the
jam recovery page counter increments.
Bytes 15–16 Stacked page counter
This counter contains the number of pages that were successfully stacked.
This counter increments by the number of pages on a sheet when the last
copy of the sheet is successfully stacked.
Bytes 17–18 Stacked copy counter
This counter contains the number of copies of all pages on successfully
stacked sheets that have not yet been accounted for by the stacked page
counter in bytes 15–16. For all but the last sheet in the set of copies, this
counter increments by the number of pages on a sheet when the sheet is
successfully stacked. This counter resets to zero when the stacked page
counter is incremented.
Bytes 5 to end of Acknowledge Reply
Special data area
or
Bytes 19 to end of Acknowledge Reply
Special data area
The special data area contains additional data generated as a result of either a request from
the presentation services program or a printer-detected exception. The specific contents are
defined by the acknowledge type in byte 0.
The special data area is loaded with the appropriate data when an exception is to be reported
or when an ARQ is received in any of the following query commands:
• Activate Setup Name [IPDS-4-071]
• Request Resident Resource List [IPDS-4-072]
• Sense Type and Model [IPDS-4-073]
• XOA Obtain Additional Exception Information [IPDS-4-074]
Acknowledge Reply


• XOA Request Resource List [IPDS-4-075]
• XOA Request Setup Name List [IPDS-4-076]
• XOH Obtain Printer Characteristics [IPDS-4-077]
• XOH Trace [IPDS-4-078]
Note: The query commands are treated as No Operation (NOP) commands unless the acknowledgment
required (ARQ) bit is on. This bit is bit 0 of the flag byte in the command header.
The contents of the special data area are determined by the acknowledge types defined in byte 0 of the
Acknowledge Reply, as described below:
• Positive responses with no special data area (Type X'00', X'40') [IPDS-4-079]
For positive responses to an IPDS command with no special data area present, only the acknowledge type
and the page and copy counters are returned in the Acknowledge Reply.
• Sense Type and Model (Type X'01', X'41') [IPDS-4-080]
For positive responses to a Sense Type and Model command, the special data area contains the machine
identification record as described in “Sense Type and Model”.
• Trace (Type X'02', X'42') [IPDS-4-081]
For positive responses to an XOH Trace command, the special data area contains trace entries as described
in “Acknowledge Reply for the XOH Trace Command”.
• Request Resident Resource List (Type X'03', X'43') [IPDS-4-082]
For positive responses to a Request Resident Resource List command, the special data area contains a list
of resources as described in “Request Resident Resource List”.
• Request Resource List (Type X'04', X'44') [IPDS-4-083]
For positive responses following an XOA Request Resource List command, the special data area contains
the resource list data as described in “Resource List Reply”.
• Obtain Additional Exception Information (Type X'05', X'45') [IPDS-4-084]
For positive responses following an XOA Obtain Additional Exception Information command, the special data
area contains the data as described in “XOA Obtain Additional Exception Information”.
• Obtain Printer Characteristics (Type X'06', X'46') [IPDS-4-085]
For positive responses following an XOH Obtain Printer Characteristics command, the special data area
contains the data as described in “XOH Obtain Printer Characteristics”.
• Activate Setup Name (Type X'07', X'47') [IPDS-4-086]
For positive responses to an Activate Setup Name command, the special data area contains the active setup
name, if any, as described in “Activate Setup Name
”.
• Request Setup Name List (Type X'08', X'48') [IPDS-4-087]
For positive responses to an XOA Request Setup Name List command, the special data area contains the
setup name list data as described in “XOA Request Setup Name List
”.
• Negative Acknowledgment (Type X'80', X'C0') [IPDS-4-088]
For all negative replies, the special data area of the NACK contains either 3 bytes or 24 bytes of detailed
sense information. For information about the sense bytes, refer to Chapter 16, “Exception Reporting”,. The reply from a Sense Type and Model command indicates whether 3 bytes of sense data or 24
bytes of sense data are returned by a printer in the special data area of each NACK.
Acknowledge Reply


General Rules for the Acknowledge Reply
• An ACK indicates that the data stream up to and including the command with the Acknowledgment Request [IPDS-4-089]
(ARQ) has been syntax-checked except for the following conditions:
– Synchronous data-stream exceptions that exist in page segments or overlays
Synchronous data-stream exceptions that exist in a page segment or an overlay may be detected when the
resource is included on a page instead of when the printer receives the resource. Some printers report
these exceptions when the resource is included on a page by the Load Copy Control, Include Overlay, or
Include Page Segment command. Other printers report these exceptions after the End Page command has
been processed and before the next command is accepted.
– Some IPDS printers process multiple pages in parallel to improve performance. In this case, the printer
replies to some Acknowledgment Requests before syntax checking all of the previously received
commands and reports any later found data stream errors with an asynchronous NACK, such as
X'0111..00' with action code X'1A'. This causes the host to reposition to the page in error and resend that
page so that the previously detected data stream error can be redetected and reported synchronously.
– Asynchronous exceptions
– Multiple copy subgroups
When multiple copy subgroups are specified, all copies of pages on a sheet might not be completely
syntax-checked until the last page on the sheet is acknowledged. Thus, when multiple copy subgroups are
specified, an acknowledgment of a page only guarantees that the copy (or copies) produced from the first
copy subgroup specified in the LCC that applies to the page has been syntax-checked for synchronous
data-stream exceptions. The acknowledgment for the last page of a sheet indicates that all copies of all
pages on the sheet have been syntax-checked for synchronous data-stream exceptions.
• If the printer receives a command requesting acknowledgment, and if this command also requests specific [IPDS-4-090]
printer information, the printer sends an ACK that contains page and copy counters and the requested
information in the data-field portion of the ACK.
If the printer receives a command requesting acknowledgment, and if this command does not request
specific printer information, the printer sends an ACK that contains only page and copy counters in the data-
field portion of the ACK.
• If the printer generates the Acknowledge Reply as a result of detecting an exception, the printer sends a [IPDS-4-091]
NACK. This exception information is stored in the special data area of the NACK. Page and copy counters
are always returned in the data-field portion of the NACK.  for more information.
• The Exception-Handling Control (EHC) used for a given exception is the one most recently received at the [IPDS-4-092]
printer; however, an asynchronous data-stream exception might have been reported out of sequence. The
EHC that applies is the one that would have applied had the exception been reported in sequence, that is, as
a synchronous data-stream exception. For more information about the XOA Exception-Handling Control,
refer to “XOA Exception-Handling Control”.
Note: The printer reports only one exception per NACK; however, multiple occurrences of a data-stream
exception on a page may be included in the same NACK (using a count field).
• If the printer receives a command requesting an acknowledgment, the printer expects the presentation [IPDS-4-093]
services program to wait for the acknowledgment before sending further commands. If the printer receives
additional commands from the presentation services program before the ACK or NACK is sent, all such
commands are discarded.
• The IPDS architecture does not specify the number of NACKs that a printer must queue. Some printers [IPDS-4-094]
queue only a single NACK. A printer with queued NACKs returns one NACK at a time until its queue is
emptied.
• When an exception is reported, any upstream data is discarded. Refer to “Page and Copy Counter [IPDS-4-095]
Adjustments” for a description of what is discarded.
Acknowledge Reply


• Positive acknowledgment of page segments or overlays and the data they contain means that the command sequence is a valid IPDS command sequence and has been accepted for processing; see Figure 45. This acknowledgment does not necessarily mean that the commands have been syntax-checked. The [IPDS-4-096]
syntax exceptions might be detected when the object is included on a logical page.
• Logical lockouts that occur as the result of a presentation services program failing to adhere to the rules [IPDS-4-097]
described above are cleared by the protocols of the underlying communications system.
Acknowledge Reply


The Activate Resource (AR) command, previously known as Load Resource Equivalence (LRE), requests the
activation of resident resources in the printer or intermediate device.
The AR command can also be used to activate a complete font such as an LF3-type coded font or a data-
object font. This type of activation combines various font components, that can be either resident or
downloaded, together with specific activation parameters. Once each of the component parts of a complete
font have been activated, the following complete-font combinations can be activated with an AR command:
• LF3-type coded font: A font character set and a code page; activated at a particular size and a particular font [IPDS-4-098]
inline sequence
• Data-object font: A TrueType/OpenType font, an optional code page, and optional linked TrueType/ [IPDS-4-099]
OpenType objects; activated at a particular size, character rotation, and encoding.
• Data-object font: A TrueType/OpenType collection, either an index value or a full font name to identify the [IPDS-4-100]
desired font within the collection, an optional code page, and optional linked TrueType/OpenType objects;
activated at a particular size, character rotation, and encoding.
This optional command maps Host-Assigned Resource IDs (HARIDs, refer to the note), a form of
which is used in BO, BPS, DDOFC, DDOR, DF , DO, DORE, DORE2,
DPS, ICMR, IDO, IO, IPS, LCC, LCPC,
LFC, LFCSC, LFE, LFI, LPD, LSS, XOA RRL, WIC2, and WOCC commands, to resource IDs of another
format. The format for the resource ID is identified by a Resource Type and Resource ID Format combination.
If the printer has a resource that matches the specified resource ID, that resource is a candidate for use by
host software. Assigning a HARID to a resource and making it available for use by host software is called
activation. When the host subsequently queries the printer, with XOA-RRL query type X'05', about a resource
that was identified in a previous AR command, a reply indicating the presence of the queried resource allows
the host to use that resource.
Activating a multi-page resource object (such as a PDF multi-page file or TIFF multi-image file) activates the
complete file, an activation of single pages or single images within the file is not possible. Like most other IPDS
resources, multi-page files can be captured or be resident in the printer.
A device that supports the AR command also supports the XOA-RRL command with query type X'05',
activation query. This support is implied by the AR Command Supported STM property-pair. If resource
activation is required by the printer, the printer must have performed any necessary resource activation prior to
responding to the XOA-RRL command, query type X'05'. A reply to this query type in this environment
indicating “not activated” means that the resource was either not available for remote activation, or that the
activation process failed; in either case the resource is not usable by the host at this time.
Some IPDS printers provide an additional diagnostic aid in the form of exception ID X'028F..02', that can be
returned whenever an activation fails because the requested resource was not found. A flag in the AR
command provides control over whether or not this exception ID is used. Support for this diagnostic aid is
specified by property pair X'F201' in the Device-Control command-set vector of an STM reply.
The activation request specified in the AR command is called an AR entry. Some printers activate resources
immediately upon processing an AR entry, while others wait until a subsequent XOA-RRL command with query
type X'05' is processed. Once activation takes place, the activation request has been fulfilled and therefore the
AR entry no longer remains in effect; but the printer keeps activation information in the form of the HARID-to-
resource-ID mapping for resource management purposes. If activation fails, the activation request specified by
the AR entry remains in effect. [IPDS-4-101]


Each AR entry remains in effect until one of the following occurs:
• The resource is successfully activated. [IPDS-4-102]
• An AR entry is encountered with the Reset Bit set; refer to byte 11. [IPDS-4-103]
• Another AR entry is encountered with the same Host-Assigned Resource ID; its values replace the values in [IPDS-4-104]
the old entry.
Note that if a DDOFC, DDOR, DF , DO, DPS, or XOH-ERFD command is received before activation is
completed, AR entries for the resource are not affected.
The HARID-to-resource-ID mapping established by the AR command remains in effect until the resource is
deactivated by a deactivate command (Deactivate Data Object Resource, Deactivate Font, Deactivate
Overlay, or Deactivate Page Segment) or by an erase command (XOH Erase Residual Font Data or XOH
Erase Residual Print Data).
Resources can be classified as capturable or not-capturable by the AR command. If the printer or intermediate
device does not have a resource that matches the specified resource ID, and if the resource is classified as
capturable, the printer or intermediate device is allowed to capture the resource the next time it is downloaded.
Some devices capture all such resources, some devices capture certain resources, and other devices don't
capture any resources; there is no method to force a capture to occur.
The AR command can be used to activate resident coded fonts using a HARID-to-resource-ID mapping before
the LFE command establishes a font equivalence between the HARID and a font local ID, or after such a font
equivalence is established by the LFE command.
The resource activation requested by an AR command is processed as follows:
• If a date and time stamp is not supplied in the AR entry, the printer does not activate a captured LF1 or LF3 [IPDS-4-105]
font resource. In this case, only resources that were shipped with or installed directly in the printer are
candidates for activation.
Note: Since a date and time stamp is required to activate a captured LF1 or LF3 font resource, printers
should not capture LF1 or LF3 font resources that don't have a date and time stamp (either in the form
of a triplet, or within a GRN).
• If the HARID is already activated for this resource type by a previous download command, by a previous AR [IPDS-4-106]
entry or command, or by a previous LFE command, the requested activation fails.
• For a coded font activation, the same HAID can be used with more than one FIS for a given resource ID. The [IPDS-4-107]
activation fails, however, when the HAID was previously activated with a different resource ID. A separate
coded font activation must be done for each desired FIS.
• If the HAID has not been used in a previous activation for this resource type and if the components of the [IPDS-4-108]
resident resource exist in the device, the activation succeeds.
• If the resident resource identified by the resource ID was already activated by means of a different HARID, it [IPDS-4-109]
remains activated, and its resource ID is mapped to both HARIDs as long as the resource type is compatible
in both activations. The following resource types are compatible:
– Identical resource types
– Single-byte LF1-type coded fonts and single-byte font indexes
– Double-byte LF1-type coded fonts and double-byte font indexes
The host can reference the resource independently by means of either HARID. If a deactivate command is
issued against one of the HARIDs, the corresponding HARID-to-resource-ID mapping is removed, but the
resident resource remains activated by means of the other HARID. If the resource type in the requested
activation is incompatible with the resource type in a previous activation, the requested activation fails.
• When the requested activation fails, a subsequent XOA-RRL activation query normally receives a reply [IPDS-4-110]
indicating that the resource specified by the HARID is not activated. However, if the activation fails because
the HARID is already in use, the XOA-RRL reply indicates that the resource originally activated with that
HARID is activated.


Note: Under certain circumstances, a raster-font activation (RT = X'01' or RT = X'03') can partially succeed.
The fully described font part might successfully activate, but the index activation (specified in AR
command bytes 7–8) might fail. In this case, a subsequent XOA-RRL query shows a successful
activation, and there is no activation-failed NACK (X'028F..02'). It is safest to follow this type of activation
with a separate AR entry for the index (RT = X'08' or RT = X'09').
There are no default resource activations.
```
Length X'D62E' Flag CID Data
```
The length of the AR command can be:
Without CID X'0005', X'0007', X'0009', X'000B', X'000D', X'000F', or X'0011'–X'7FFF'
With CID X'0007', X'0009', X'000B', X'000D', X'000F', X'0011', or X'0013'–X'7FFF'
However, the length for each AR entry and each triplet must also be valid. Exception ID X'0202..02' exists if the
command length is invalid or unsupported.
The data in an Activate Resource command consists of zero or more AR entries that are processed in the
order that they appear in the command. If a syntax error is encountered in one of the entries, that entry and all
following entries in the AR command are discarded; preceding entries remain in effect. Exception ID
X'028F..01' exists in this situation, but reporting of this exception is optional. Exception ID X'023A..02' exists
when an attempt is made to activate more coded-font components than the printer can support; reporting of
this exception is optional.
Zero or more AR entries in the following format: [IPDS-4-111]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :---: | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | | Entry length, including this length field | See byte description [IPDS-4-112]|
| 2 | CODE | RT | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'40'<br>X'41'<br>X'42' | Resource Type (RT):<br>No value supplied<br>Single-byte LF1-type or LF2-type coded font<br>Reserved<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Code page<br>Font character set<br>Single-byte coded-font index<br>Double-byte coded-font index<br>Coded font<br>Data object resource<br>Data-object font<br>Data-object-font component | X'00' and at least one Resource Type [IPDS-4-113]|
| 3–4 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Host-Assigned ID (see note following table) | X'0000'<br>X'0001'–X'7EFF' [IPDS-4-114]|
| 5 | CODE | Section ID | X'00'<br>X'41'–X'FE' | Section Identifier: No value supplied or Double-byte font section ID (see note following table) | See byte description [IPDS-4-115]|
| 6 | CODE | RIDF | X'00'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'09'<br>X'0A' | Resource ID Format (RIDF):<br>No value supplied<br>GRID-parts format<br>Remote PrintManager MVS format<br>Extended Remote PrintManager MVS format<br>MVS Host Unalterable Remote Font Environment<br>Coded-font format<br>Object-OID format<br>Data-object-font format | X'00' and at least one Resource ID Format [IPDS-4-116]|
| 7–8 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font Inline Sequence (see note following table):<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-4-117]|
| 9–10 | X'0000' | Reserved | X'0000' | Reserved | X'0000' [IPDS-4-118]|
| 11 | BITS | Resource class flags | bit 0<br><br>bit 1<br>bit 2<br><br>bit 3<br><br>bit 4<br><br>bit 5<br><br>bits 6–7 | bit 0: Don't capture (B'0' May be captured, B'1' Must not be captured)<br>bit 1: Reserved (B'0')<br>bit 2: Reset (B'0' No Reset, B'1' Reset)<br>bit 3: Activation failed NACK (B'0' No NACK, B'1' NACK if fails)<br>bit 4: Substitution (B'0' Not allowed, B'1' Outline-font allowed)<br>bit 5: Private object name (B'0' Tied to object, B'1' Private)<br>bits 6–7: Reserved (B'00') | bit 0: B'0', B'1'<br>bit 1: B'0'<br>bit 2: B'0', B'1'<br>bit 3: B'0'<br>bit 4: B'0'<br>bit 5: B'0', B'1'<br>bits 6–7: B'00' [IPDS-4-119]|
| 12 to end | | Resource ID | | Resource ID of the resource to be activated. If the printer supports resource ID triplets, the fixed portion of the resource ID can be followed by one or more of the following triplets:<br>X'01' Coded Graphic Character Set Global Identifier triplet<br>X'02' Fully Qualified Name triplet<br>X'50' Encoding Scheme ID triplet<br>X'62' Local Date and Time Stamp triplet<br>X'79' Metric Adjustment triplet<br>X'84' Font Resolution and Metric Technology triplet<br>X'8B' Data Object Font Descriptor triplet<br>X'8D' Linked Font triplet<br>X'91' Color Management Resource Descriptor triplet [IPDS-4-120]| |


Note: The Host-Assigned ID is assigned dynamically by the presentation services program. This ID in
conjunction with the Section Identifier and/or the Font Inline Sequence fields serves to uniquely identify
a specific resource. Some Resource Types do not require the Section Identifier or the Font Inline
Sequence fields, in which case, these fields are ignored by the printer and should contain binary zeros.
The combination of the Host-Assigned ID, the Section Identifier, and the Font Inline Sequence fields is
called the Host-Assigned Resource ID.
Bytes 0–1 Entry length
The length of this AR entry. This parameter includes the length of this field. Entry lengths must
be one of the following:
• X'0002' (null entry), [IPDS-4-121]
• X'000C' (used to reset without specifying an equivalence) [IPDS-4-122]
• X'000C' plus the length of the resource ID (bytes 12 to end of entry) [IPDS-4-123]
Printers that support AR must support entry lengths of X'0002', X'000C', and the valid length
for each RT , RIDF combination supported. The valid entry lengths for RT , RIDF combinations
follow:
**Table 22**. Valid RID Entry Lengths [IPDS-4-124]

| RT | RIDF | Valid Entry Length |
| :--- | :---: | :--- |
| X'01', X'03', X'08', X'09', or X'10' | X'03' | X'0014' plus length of triplets [IPDS-4-125]|
| X'06' | X'03' | X'000E', X'0010', or X'0010' plus length of triplets [IPDS-4-126]|
| X'07' | X'03' | X'0010' or X'0010' plus length of triplets [IPDS-4-127]|
| X'01', X'03', X'08', or X'09' | X'04' | X'00B0' [IPDS-4-128]|
| X'04' or X'05' | X'04' | X'005E' [IPDS-4-129]|
| X'04' | X'05' | X'0062' [IPDS-4-130]|
| X'01', X'03', X'08', or X'09' | X'06' | X'00B8' plus length of triplets [IPDS-4-131]|
| X'10' | X'07' | X'001E' plus length of triplets [IPDS-4-132]|
| X'40' | X'09' | X'000E'–X'008D' plus length of triplets [IPDS-4-133]|
| X'41' | X'0A' | X'0012' plus length of triplets [IPDS-4-134]|
| X'42' | X'09' | X'000E'–X'008D' plus length of triplets [IPDS-4-135]|


Byte 2 Resource Type (RT)
This parameter specifies one of the following resource types:
X'00'—no resource type specified; valid only when the AR entry length is X'000C'
X'01'—single-byte LF1-type or LF2-type coded font
X'02'—retired item 9
X'03'—double-byte LF1-type coded-font section
X'04'—page segment
X'05'—overlay
X'06'—code page
X'07'—font character set
X'08'—single-byte coded-font index (LF1-type coded font only)
X'09'—double-byte coded-font index (LF1-type coded font only).
X'10'—coded font
X'40'—data object resource
X'41'—data-object font
X'42'—data-object-font component
If the Resource Type is unsupported, the AR entry is ignored. An exception is not generated.
Bytes 3–4 Host-Assigned ID
This parameter specifies the Host-Assigned ID. The Host-Assigned ID is assigned
dynamically by the presentation services program. This ID in conjunction with the Section
Identifier and the Font Inline Sequence fields serves to uniquely identify a specific resource.
Some Resource Types do not require the Section Identifier and the Font Inline Sequence
fields, in which case, these fields are ignored by the printer and should contain binary zeros.
The combination of the Host-Assigned ID, the Section Identifier, and the Font Inline Sequence
fields is called the Host-Assigned Resource ID.
The value of this parameter depends on the Resource Type (RT):
• RT = X'01'—Host-Assigned ID of the single-byte LF1-type or LF2-type coded font [IPDS-4-136]
• RT = X'03'—Host-Assigned ID of the double-byte LF1-type coded font section [IPDS-4-137]
• RT = X'04'—Host-Assigned ID of the Page Segment [IPDS-4-138]
• RT = X'05'—Host-Assigned ID of the Overlay [IPDS-4-139]
• RT = X'06'—Host-Assigned ID of the code page [IPDS-4-140]
• RT = X'07'—Host-Assigned ID of the font character set [IPDS-4-141]
• RT = X'08'—Host-Assigned ID of the single-byte LF1-type coded font [IPDS-4-142]
• RT = X'09'—Host-Assigned ID of the double-byte LF1-type coded font [IPDS-4-143]
• RT = X'10'—Host-Assigned ID of the coded font [IPDS-4-144]
• RT = X'40'—Host-Assigned ID of the data object resource [IPDS-4-145]
• RT = X'41'—Host-Assigned ID of the data-object font [IPDS-4-146]
• RT = X'42'—Host-Assigned ID of the data-object-font component [IPDS-4-147]
A HAID value of X'0000' is valid only when the AR entry length is X'000C'.
Byte 5 Section Identifier
This parameter specifies a qualifier of the Host-Assigned ID. Some Resource Types do not
require a Section Identifier, in which case this field is ignored by the printer and should contain
binary zeros. Printers that support resource type X'03' or resource type X'09' must support
values in the range X'41' through X'FE' in this field.
The value of this parameter depends on the Resource Type (RT):
• RT = X'01'—Section Identifier field is ignored [IPDS-4-148]
• RT = X'03'—Section Identifier of the double-byte LF1-type coded-font section [IPDS-4-149]
• RT = X'04'—Section Identifier field is ignored [IPDS-4-150]
• RT = X'05'—Section Identifier field is ignored [IPDS-4-151]


• RT = X'06'—Section Identifier field is ignored [IPDS-4-152]
• RT = X'07'—Section Identifier field is ignored [IPDS-4-153]
• RT = X'08'—Section Identifier field is ignored [IPDS-4-154]
• RT = X'09'—Section Identifier of the double-byte LF1-type coded-font section [IPDS-4-155]
• RT = X'10'—Section Identifier field is ignored [IPDS-4-156]
• RT = X'40'—Section Identifier field is ignored [IPDS-4-157]
• RT = X'41'—Section Identifier field is ignored [IPDS-4-158]
• RT = X'42'—Section Identifier field is ignored [IPDS-4-159]
Byte 6 Resource ID Format (RIDF)
This parameter describes the format of the resource ID (bytes 12 to end) for the Resource
Type (byte 2).
• X'00'—no format specified; valid only when the AR entry length is X'000C' [IPDS-4-160]
• X'03'—GRID-parts format [IPDS-4-161]
• X'04'—Remote PrintManager MVS format [IPDS-4-162]
• X'05'—Extended Remote PrintManager MVS format [IPDS-4-163]
• X'06'—MVS Host Unalterable Remote Font environment [IPDS-4-164]
• X'07'—Coded-font format [IPDS-4-165]
• X'09'—Object-OID format [IPDS-4-166]
• X'0A'—Data-object-font format [IPDS-4-167]
If the Resource ID Format is unsupported, the AR entry is ignored. An exception is not
generated.
X'03' GRID-parts format
This naming format is used for code pages, font character sets, double-byte LF1-type
coded-font sections, font indexes, and coded fonts. Refer to bytes 5–12 of the LFE
command for an explanation of the components of the GRID. Each of the
first three subfields of the GRID range from X'0000' through X'FFFF'; the font width
subfield is specified in 1440ths of an inch and can range from X'0000' through X'7FFF'
and X'FFFF'. The content of the resource ID field (bytes 12 to end of entry) consists of
the following subfields:
For code pages
Use either two bytes or four bytes to select a code page:
• For a specific code page, specify a two-byte GCSGID followed by a two-byte [IPDS-4-168]
CPGID.
Resource ID triplets can be added after the 4-byte ID to specify a particular version
of the code page.
• For a device version of the code page, specify a two-byte CPGID. A device version [IPDS-4-169]
of a code page contains all of the characters that were registered for the CPGID at
the time the printer was developed; more characters might have been added to the
registry for that CPGID since that time.
Resource ID triplets cannot be used with the device version of a code page.
If an exact GCSGID match is not found, the printer may look for a version of the
resource that has a larger character set. However, when a Local Date and Time
Stamp (X'62') triplet is specified in the AR command, the resource with the larger
character set must also have the same date and time stamp.
For font character sets
Two-byte GCSGID
Followed by two-byte FGID [IPDS-4-170]


Resource ID triplets can be added after the 4-byte ID to specify a particular version of
the font character set.
If an exact GCSGID match is not found, the printer may look for a version of the
resource that has a larger character set. However, when a Local Date and Time
Stamp (X'62') triplet is specified in the AR command, the resource with the larger
character set must also have the same date and time stamp.
For font indexes
Two-byte GCSGID
Followed by two-byte CPGID
Followed by two-byte FGID
Followed by two-byte font width
For single-byte LF1-type coded-fonts
Two-byte GCSGID
Followed by two-byte CPGID
Followed by two-byte FGID
Followed by two-byte font width
If a Font Resolution and Metric Technology (X'84') triplet is present and supported, it is
also used to locate the resource.
For LF2-type coded-fonts
Two-byte GCSGID
Followed by two-byte CPGID
Followed by two-byte FGID
Followed by two-byte font width
For double-byte LF1-type coded-font sections
Two-byte GCSGID
Followed by two-byte CPGID
Followed by two-byte FGID
Followed by two-byte font width
The section ID (byte 5 of the AR command) is also used for this resource type. If a
Font Resolution and Metric Technology (X'84') triplet is present and supported, it is
also used to locate the resource. [IPDS-4-171]


For coded fonts
Two-byte GCSGID
Followed by two-byte CPGID
Followed by two-byte FGID
Followed by two-byte font width
When this format is used with RT = X'10' (coded font), the printer uses the information
provided in the GRID to locate the component parts of a coded font using the following
hierarchical method:
1. If the LF3 command subset is supported, the printer attempts to find a font [IPDS-4-172]
character set and a code page.
The GCSGID and CPGID values are used to find the code page. If a CPGID value
is not supplied, the search fails. If a GCSGID value is not supplied, the first (or
largest) code page that matches the CPGID is used.
The GCSGID and FGID values are used to find the font character set. If an FGID
is not supplied, the search fails. If a GCSGID is not supplied, the GCSGID used
for the code page is used.
If in either case an exact match is not found, the printer may look for a version of
the resource that has a larger character set.
If the components are found, steps 2 and 3 are skipped. Note that the characters
in the code page do not necessarily have to intersect with the characters in the
font character set. If not enough information was provided or if either of the
components was not found, step 2 in the hierarchy is used.
2. If LF1-type coded fonts are supported, the printer attempts to find the components [IPDS-4-173]
of either a single-byte or double-byte coded font. The printer first attempts to find a
fully described font and a font index. The GCSGID, CPGID, FGID, and font width
values together with the font inline sequence value (AR bytes 7–8) are used to
find these components. If a fully described font is found that is a section of a
double-byte coded font, the printer continues searching to find all available fully
described font sections for this coded font. If the components are found, step 3 is
skipped. If not enough information is provided or if some of the components are
not found, step 3 in the hierarchy is used.
3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set [IPDS-4-174]
coded font using the GCSGID, CPGID, FGID, and font width values.
In addition, the character shapes and metrics of an outline coded font must be scaled
to a specific size; the font width value is used to derive the scale factors, as follows:
• For fonts with FGID values less than 750 and with FGID values between 3840 and [IPDS-4-175]
4095 inclusive (fixed-pitch, uniform-character-increment, and PSM fonts), both the [IPDS-4-176]
horizontal scale factor and the vertical scale factor are derived from the following
algorithm:
1000 X font width [IPDS-4-177]
scale factor = ---------------------------------------------
space character increment (in relative units)
Any fractional value resulting from the division is truncated.
• FGID values between 750 and 2303 inclusive are invalid and the activation fails. [IPDS-4-178]
• For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and [IPDS-4-179]
53,247 inclusive, and between 61,440 and 65,534 inclusive (typographic,
proportionally spaced fonts), both the horizontal scale factor and the vertical scale
factor are three times the font width. [IPDS-4-180]


• For fonts with FGID values between 53,248 and 61,439 inclusive, both the [IPDS-4-181]
horizontal scale factor and the vertical scale factor are equal to the font width.
To convert the scale factor values from 1440ths to points, the scale factor value is
divided by 20 and rounded off to the nearest integer. If the result is zero, it is changed
to 1.
Note: To avoid undefined character data-check exceptions when printing with an LF3-
type coded font, the components of the GRID should be carefully matched to
ensure that all of the GCGIDs used in the code page are also available in the
font character set.
X'04' Remote PrintManager MVS format (82 or 164 bytes/resource)
This format is composed of the following one or two 82-byte subfield groups. Page
segment and overlay ARs use one group; all font ARs use two groups. The two font-
subfield groups identify the code page and font character set. The code page is
defined in the first subfield group.
Note: This RIDF is used by Remote PrintManager and Distributed Print Facility
products that are IPDS intermediate devices. IPDS printers do not support
resident or captured overlays and do not support resident or captured page
segments.
The printer can use the Date and Time fields to determine if the resource has been
updated since it was last used. If so, the printer must respond to any XOA-RRL query
of that resource by indicating that the resource is not present.
The fields within a subfield group have been defined by the Remote PrintManager
product and consist of the following:
• Cyclic Redundancy Check: 2 bytes. [IPDS-4-182]
• MVS Host System ID: 8 bytes. [IPDS-4-183]
• VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the [IPDS-4-184]
right.
• DSNAME of Host Library containing the resource: 44 bytes padded with blanks to [IPDS-4-185]
the right.
• Date Stamp: 6 bytes. [IPDS-4-186]
This is the date that the Resource was last updated on the MVS host.
Date: The current date in the form CYYDDD as 6 characters, a character
representing the thousands and hundreds position of the year (blank =19,
0=20, 1=21, etc.), followed by the year (YY=00-99), followed by the day of
the year (DDD=001-366); for example:
February 1, 1972 is recorded as “ 72032”
January 1, 2000 is recorded as “000001”
February 3, 2072 is recorded as “072034”
• Time Stamp: 8 bytes. [IPDS-4-187]
Time: This is the time that the Resource was last updated on the MVS host. The
current time in the form HHMMSShh as characters. Two characters for the
hour (HH=00-24), two characters for the minute (MM=00-59), two for the
second (SS=00-59) and two for the hundredths of a second (hh=00-99).
• Member Name from Host Library containing the resource: 8 bytes padded with [IPDS-4-188]
blanks to the right.
Note: The printer uses as many of these fields as is necessary to find the requested
resource. The IPDS Architecture does not require a printer to use all of the
subfields of the resource ID. [IPDS-4-189]


X'05' Extended Remote PrintManager MVS format (86 bytes/resource)
This format is composed of the following 86-byte subfield group. The format is used
for page segments only.
Note: This RIDF is used by Remote PrintManager and Distributed Print Facility
products that are IPDS intermediate devices. IPDS printers do not support
resident or captured page segments.
The printer can use the Date and Time fields to determine if the resource has been
updated since it was last used. If so, the printer must respond to any XOA-RRL query
of that resource by indicating that the resource is not present.
The fields within a subfield group have been defined by the Remote PrintManager
product and consist of the following:
• Cyclic Redundancy Check: 2 bytes. [IPDS-4-190]
• MVS Host System ID: 8 bytes. [IPDS-4-191]
• VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the [IPDS-4-192]
right.
• DSNAME of Host Library containing the resource: 44 bytes padded with blanks to [IPDS-4-193]
the right.
• Date Stamp: 6 bytes. [IPDS-4-194]
This is the date that the Resource was last updated on the MVS host.
Date: The current date in the form CYYDDD as 6 characters, a character
representing the thousands and hundreds position of the year (blank =19,
0=20, 1=21, etc.), followed by the year (YY=00-99), followed by the day of
the year (DDD=001-366); for example:
February 1, 1972 is recorded as “ 72032”
January 1, 2000 is recorded as “000001”
February 3, 2072 is recorded as “072034”
• Time Stamp: 8 bytes. [IPDS-4-195]
Time: This is the time that the Resource was last updated on the MVS host. The
current time in the form HHMMSShh as characters. Two characters for the
hour (HH=00-24), two characters for the minute (MM=00-59), two for the
second (SS=00-59) and two for the hundredths of a second (hh=00-99).
• Member Name from Host Library containing the resource: 8 bytes padded with [IPDS-4-196]
blanks to the right.
• External Unit Base Specification: 1 byte [IPDS-4-197]
• Reserved: 1 byte [IPDS-4-198]
• External Units Per Unit Base Specification: 2 bytes [IPDS-4-199]
Note: The printer uses as many of these fields as is necessary to find the requested
resource. The IPDS Architecture does not require a printer to use all of the
subfields of the resource ID. [IPDS-4-200]


X'06' MVS Host Unalterable Remote Font environment (172 bytes/font)
This format is composed of the following two 86-byte subfield groups. The two font
subfield groups identify the code page and font character set. The code page is
defined in the first subfield group.
The printer can use the Date and Time fields to determine if they differ from the EC-
level dates of the remote version (if any) of that font. If the dates are different, the
printer must respond to any XOA-RRL query of that resource by indicating that the
resource is not present. If a Font Resolution and Metric Technology (X'84') triplet is
present and supported, it is also used to locate the resource.
The fields within a subfield group consist of the following:
• Cyclic Redundancy Check: 2 bytes. [IPDS-4-201]
• MVS Host System ID: 8 bytes. [IPDS-4-202]
• VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the [IPDS-4-203]
right.
• DSNAME of Host Library containing the resource: 44 bytes padded with blanks to [IPDS-4-204]
the right.
• Date Stamp: 6 bytes. [IPDS-4-205]
This is the date that the Resource was last updated on the MVS host.
Date: The current date in the form CYYDDD as 6 characters, a character
representing the thousands and hundreds position of the year (blank =19,
0=20, 1=21, etc.), followed by the year (YY=00-99), followed by the day of
the year (DDD=001-366); for example:
February 1, 1972 is recorded as “ 72032”
January 1, 2000 is recorded as “000001”
February 3, 2072 is recorded as “072034”
• Time Stamp: 8 bytes. [IPDS-4-206]
Time: This is the time that the Resource was last updated on the MVS host. The
current time in the form HHMMSShh as characters. Two characters for the
hour (HH=00-24), two characters for the minute (MM=00-59), two for the
second (SS=00-59) and two for the hundredths of a second (hh=00-99).
Note: If a date and time stamp is not supplied in the AR entry, the printer does not
activate a captured resource. In this case, only resources that were shipped
with or installed directly in the printer are candidates for activation.
• Member Name from Host Library containing the resource: 8 bytes padded with [IPDS-4-207]
blanks to the right.
• GRID half: 4 bytes. For the code page subfield group, this is the Graphic Character [IPDS-4-208]
Set Global ID (2 bytes) followed by the Code Page Global ID (2 bytes). For the font
character set subfield group, this is the Font Typeface Global ID (2 bytes) followed
by the Font Width (2 bytes). For a description of these fields, refer to GRID (bytes 5–
12 of the LFE command), that is described. [IPDS-4-209]
Note: The printer uses as many of these fields as is necessary to find the requested
resource. The IPDS Architecture does not require a printer to use all of the
subfields of the resource ID. [IPDS-4-210]


X'07' Coded-font format
This naming format is used to activate a coded font from its component parts. The
resource ID field consists of the information needed to find the component parts (AR
bytes 12–25) and the scale factors used by an outline coded font (AR bytes 26–29).
The resource ID field contains the following information:
Offset Type Name Range Meaning
12–13 CODE FCS HAID X'0000'
X'0001' –
X'7EFF'
No value supplied
Font character set HAID
14–15 CODE CP HAID X'0000'
X'0001' –
X'7EFF'
No value supplied
Code page HAID
16–17 CODE GCSGID X'0000'
X'0001' –
X'FFFE'
X'FFFF'
No value supplied
Graphic Character Set Global ID (GCSGID)
All characters that have been assigned code points
18–19 CODE CPGID X'0000'
X'0001' –
X'FFFE'
X'FFFF'
No value supplied
Code Page Global ID (CPGID)
Printer default code page
20–21 CODE FGID X'0000'
X'0001' –
X'FFFE'
X'FFFF'
No value supplied
Printer default FGID
22–23 CODE FW X'0000'
X'0001' –
X'7FFF'
X'FFFF'
No value supplied
Font width in 1440ths of an inch
Printer default font width
24 CODE Pattern [IPDS-4-211]
technology ID
X'00'
X'1E'
X'1F'
No value supplied
CID-keyed technology
Type 1 PFB technology
25 X'00' Reserved [IPDS-4-212]
26–27 UBIN VSF X'0000'
X'0001' –
X'7FFF'
No value supplied
Vertical scale factor in 1440ths of an inch
28–29 UBIN HSF X'0000'
X'0001' –
X'7FFF'
No value supplied
Horizontal scale factor in 1440ths of an inch
The vertical scale factor, also known as the specified vertical font size, is the desired
distance between adjacent character baselines when character rotation is zero [IPDS-4-213]


degrees and no external leading is used. The horizontal scale factor can be used for
anamorphic scaling.
The printer uses the information provided in the resource ID field to locate the
component parts of the coded font in the following hierarchical method:
1. If the LF3 command subset is supported, the printer attempts to find a font [IPDS-4-214]
character set and a code page. If a HAID is supplied for either the code page or
font character set, that HAID is used.
If a CP HAID is not supplied, the GCSGID and CPGID values are used to find the
code page. If a CPGID value is not supplied, the search fails. If a GCSGID value is
not supplied, the first (or largest) code page that matches the CPGID is used.
If an FCS HAID is not supplied, the GCSGID, FGID, and pattern technology ID
values are used to find the font character set. If an FGID is not supplied, the
search fails. If a GCSGID is not supplied, the GCSGID used for the code page is
used.
If in either case, an exact match is not found, the printer may look for a version of
the resource that has a larger character set.
If both components are found, steps 2 and 3 are skipped. If not enough
information was provided or if either of the components was not found, step 2 in
the hierarchy is used. Note that the characters in the code page do not necessarily
have to intersect with the characters in the font character set.
2. If LF1-type coded fonts are supported, the printer attempts to find the components [IPDS-4-215]
of either a single-byte or double-byte coded font. The printer first attempts to find a
fully described font and a font index. The GCSGID, CPGID, FGID, and font width
values together with the font inline sequence value (AR bytes 7–8) are used to
find these components. If a fully described font is found that is a section of a
double-byte coded font, the printer continues searching to find all available fully
described font sections for this coded font. If the components are found, step 3 is
skipped. If not enough information is provided or if some of the components are
not found, step 3 in the hierarchy is used.
3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set [IPDS-4-216]
coded font using the GCSGID, CPGID, FGID, and font width values.
In addition, the character shapes and metrics of an outline coded font must be scaled
to a specific size. The primary scale factor is the vertical scale factor; if this value is
not provided, the printer derives it from the font width value, as follows:
• For fonts with FGID values less than 750 and with FGID values between 3840 and [IPDS-4-217]
4095 inclusive (fixed-pitch, uniform-character-increment, and PSM fonts), the [IPDS-4-218]
vertical scale factor is derived from the following algorithm:
vertical 1000 X font width
scale factor = ---------------------------------------------
space character increment (in relative units)
Any fractional value resulting from the division is truncated.
• FGID values between 750 and 2303 inclusive are invalid and the activation fails. [IPDS-4-219]
• For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and [IPDS-4-220]
53,247 inclusive, and between 61,440 and 65,534 inclusive (typographic,
proportionally spaced fonts), the vertical scale factor is three times the font width.
• For fonts with FGID values between 53,248 and 61,439 inclusive, the vertical scale [IPDS-4-221]
factor is equal to the font width.
• If an FGID was not supplied in the AR command but a font character set was found, [IPDS-4-222]
the printer may obtain the FGID from the font character set. [IPDS-4-223]


To convert the scale factor values from 1440ths to points, the scale factor value is
divided by 20. If the scale factor was provided in the VSF or HSF field, this value is
used without rounding. However, if the scale factor was derived from the font-width
value, the derived value in points is then rounded off to the nearest positive integer; if
the result is zero, it is changed to 1.
If a horizontal scale factor is provided, the character shapes and metrics of the coded
font can be scaled anamorphically. Otherwise, the font is scaled uniformly using the
vertical scale factor value for both scale factors. If the horizontal scale factor value
specified is not equal to the vertical scale factor, the character shapes and metrics are
stretched or compressed in the horizontal direction by the ratio of HSF/VSF.
X'09' Object-OID format
This naming format is used to uniquely identify resident data object resources and
resident data-object-font components. An OID is a variable length (2 bytes long to 129
bytes long) binary ID that uniquely identifies an object. OIDs use the ASN.1 definite-
short-form object identifier format defined in the ISO/IEC 8824:1990(E) international
standard and described in the Mixed Object Document Content Architecture
Reference. The syntax of an OID is as follows:
Offset Type Name Range Meaning
0 CODE Identifier X'06' This is a definite-short-form OID [IPDS-4-224]
1 UBIN Length X'00'–X'7F' Length of the following content bytes [IPDS-4-225]
2 to end Content Any value Content bytes that provide a unique ID for this object [IPDS-4-226]


X'0A' Data-object-font format
This naming format is used to activate a data-object font from its component parts.
Three different methods are provided to identify the scheme used to encode the
character data to be printed; you can select either:
1. A code page [IPDS-4-227]
2. A Unicode transformation (UTF-8); this method is specified with an Encoding [IPDS-4-228]
Scheme ID (X'50') triplet.
3. Default to the encoding scheme indicated in the Data Object Font Descriptor [IPDS-4-229]
(X'8B') triplet. For TrueType and OpenType fonts, this is UTF-16BE.
The resource ID field contains the following information:
Offset Type Name Range Meaning
12–13 CODE Base font HAID X'0001' –
X'7EFF'
HAID of one of the following data-object-font components:
TrueType/OpenType font
TrueType/OpenType collection
14–15 CODE CP HAID X'0000'
X'0001' –
X'7EFF'
No value supplied
Code page HAID
16–17 UBIN TTC font index X'0000' –
X'FFFF'
Identification of a TrueType/OpenType font within a collection;
for the base font
18 to
end of
entry
Triplets One or more of the following triplets:
X'02' Fully Qualified Name triplet
X'50' Encoding Scheme ID triplet
X'8B' Data Object Font Descriptor triplet
X'8D' Linked Font triplet
Bytes 12–13 Base font HAID
This parameter specifies the HAID of a previously activated data-
object-font component; the following are the valid resource types for
this HAID:
TrueType/OpenType font
TrueType/OpenType collection
If an invalid HAID value is specified, or if the data-object-font
component was not previously activated, or if the resource type is not
valid, the data-object font activation fails.
Bytes 14–15 Code page HAID
This parameter specifies the HAID of a previously activated code
page. X'0000' indicates that this activation does not involve a code
page.
If a non-zero HAID value is specified but the code page was not
previously activated, exception ID X'028F..30' exists. If an invalid
HAID value is specified, the activation fails.
This parameter is used when the character data to be printed is
encoded using a code page. The code page can use either fixed
single-byte code points or fixed double-byte code points.
The code page is used to map code points to IBM GCGIDs, and then
the printer maps GCGIDs to appropriate values using an internal
table. Any IPDS code page can be used (either fixed single byte or [IPDS-4-230]


fixed double byte); however, only those GCGIDs that are used in
IBM-supplied code pages are supported within the printer.
Some IPDS printers support an extended code page that includes a
mapping from GCGIDs to Unicode values; this mapping can be used
to support user-defined characters that are not in the printer-resident
internal table. Printer support for extended code pages is indicated by
property pair X'B005' in the Loaded-Font command-set vector of an
STM reply.
When an extended code page is used, the printer first searches the
code page for a Unicode value and, if one is not found, then searches
the internal printer table. Exception ID X'028F..50' exists if an
unknown GCGID value is encountered within the code page.
The code point processing flags within the code page (undefined,
nonprinting, nonincrementing) are used as described in the LCP
command description.
Bytes 16–17 TTC font index for the base font
This parameter identifies a specific TrueType/OpenType font within a
TrueType/OpenType collection. The index value is a zero-based
index into the TableDirectory array of Directory offsets that comprise
the 4th parameter in a TTC header (refer to the OpenType
Specification description of TrueType collections). An index value of
X'0000' selects the first font in the directory array, an index value of
X'0001' selects the second font in the directory array, and so forth. If
an index value is specified that is invalid for the TableDirectory, the
activation fails.
An alternate method of identifying a specific TrueType/OpenType font
within a TrueType/OpenType collection is to specify a full font name
in a Fully Qualified Name (X'02') triplet. When a Fully Qualified Name
(X'02') triplet is specified, the triplet overrides the TTC-font-index
parameter. Specifying a TTC font index is the preferred method.
This parameter is ignored when the base font HAID (bytes 12–13) is
not for a TrueType/OpenType collection; in this case, bytes 16–17
should contain X'0000'.
Bytes 18– AR
entry
Triplets
This portion of the data-object-font format contains one or more
triplets that provide additional information for the activation.
Unsupported or unneeded triplets are ignored; for those triplets that
should be specified once, extras are also ignored.
Fully Qualified Name (X'02') triplet (FQN type X'DE')
This triplet can be specified once when the base font
specified in bytes 12–13 is a TrueType/OpenType collection;
it should not be specified in other cases. The triplet specifies
a Full Font Name that is used to locate a specific TrueType/
OpenType font within a TrueType/OpenType collection. The
name must be a character string that is encoded as UTF-
16BE. If present, this triplet overrides the TTC-font-index
parameter (bytes 16–17).
Encoding Scheme ID (X'50') triplet
This triplet must be specified once when the data to be
printed is encoded in a form different from the font's encoding
(as specified in the X'8B' triplet) and is not encoded with a [IPDS-4-231]


code page. For example, if the data to be printed is encoded
with UTF-8, this triplet is required.
Data Object Font Descriptor (X'8B') triplet
This triplet must be specified once to provide activation
parameters for this specific activation.
Linked Font (X'8D') triplet
This triplet can be specified to link a TrueType/OpenType
object with a base font.
If there are multiple objects to be linked to the base font, one
X'8D' triplet is specified for each linked font and the order of
the triplets determines the order of processing. When a glyph
is needed, the base font is searched first and if the glyph is
not found there, the font identified by the first Linked Font
(X'8D') triplet is searched, if not found there the font identified
by the second Linked Font (X'8D') triplet is searched, and so
forth. If the glyph is not found in any of the fonts, exception ID
X'0821..00' exists and when an AEA or PCA is taken, a
special character (represented by glyph index 0 for a
TrueType or OpenType font) is used from the base font.
Exception ID X'028F..31' exists if the TrueType/OpenType
object is not activated when it is needed, or if the object is
activated but is not a TrueType/OpenType object.
These triplets are fully described in the triplets chapter:
“Fully Qualified Name (X'02') Triplet”
“Encoding Scheme ID (X'50') Triplet”
“Data Object Font Descriptor (X'8B') Triplet”
“Linked Font (X'8D') Triplet” [IPDS-4-232]


Bytes 7–8 Font Inline Sequence
This parameter specifies a qualifier of the Host-Assigned ID. Some Resource Types do not
require a Font Inline Sequence, in which case this field is ignored by the printer and should
contain binary zeros.
The value of this parameter depends on the Resource Type (RT):
• RT = X'01'—Font Inline Sequence of a single-byte LF1-type coded-font index; ignored for [IPDS-4-233]
LF2-type coded fonts
• RT = X'03'—Font Inline Sequence of a double-byte LF1-type coded-font index; ignored for [IPDS-4-234]
LF2-type coded fonts
• RT = X'04'—Font Inline Sequence field is ignored [IPDS-4-235]
• RT = X'05'—Font Inline Sequence field is ignored [IPDS-4-236]
• RT = X'06'—Font Inline Sequence field is ignored [IPDS-4-237]
• RT = X'07'—Font Inline Sequence field is ignored [IPDS-4-238]
• RT = X'08'—Font Inline Sequence of the single-byte LF1-type coded-font index [IPDS-4-239]
• RT = X'09'—Font Inline Sequence of the double-byte LF1-type coded-font section index [IPDS-4-240]
• RT = X'10'—Desired Font Inline Sequence for a coded font [IPDS-4-241]
• RT = X'40'—Font Inline Sequence field is ignored [IPDS-4-242]
• RT = X'41'—Font Inline Sequence field is ignored [IPDS-4-243]
• RT = X'42'—Font Inline Sequence field is ignored [IPDS-4-244]
For outline coded fonts this parameter is used to select the character metrics for a specific
writing mode; refer to Figure 102. A separate activation must be done for each
desired font inline sequence.
For data-object fonts, this parameter is ignored and the font inline sequence is specified (in the
form of a character rotation) within a Data Object Font Descriptor (X'8B') triplet. A separate
activation must be done for each desired character rotation.
Note: Type 1 technology fonts only provide character metrics for FIS = X'0000'. CID-keyed
technology fonts can provide metrics for both FIS = X'0000' and FIS = X'2D00'. The
printer obtains the metrics for FIS = X'0000' from the Adobe portion of the FCS and can
then derive the character increment (and other needed metrics) for the other FIS values
when needed.
Bytes 9–10 Reserved
Byte 11 Resource class flags (bit mapped)
Bit 0 Don't capture flag
If this bit is B'0', the resource may be captured from the data stream and retained in a
resource library remote from the host. If this bit is B'1', the resource must not be
captured. This bit has no implication on host management (activation, deactivation) of
resources within a printer.
Note: TrueType/OpenType objects contain bits that identify “font embedding licensing
rights” for the font. These bits are in the fsType entry of the OS/2 table. IPDS
printers should not capture a TrueType/OpenType object when any of the
following bits are set to B'1': Restricted License embedding (bit 1 numbering
from right-to-left), Preview & Print embedding (bit 2), and Editable embedding
(bit 3).
When a resource is captured, the resource ID value specified in this AR command
entry is saved with the resource. All resource ID triplets are also saved with the [IPDS-4-245]


resource. A date and time stamp must be supplied for an LF1 or LF3 font resource to
be captured.
The physical presence column in the “Resource Management Summary”
identifies which resource types can be captured and which are not eligible for capture.
Bit 1 Retired item 14
Bit 2 Reset flag
If this bit is B'1' in any AR entry, all previously received AR entries are discarded,
including previous entries within this AR command. The discarded entries include
activation requests that have previously failed and activation requests that have not
yet been attempted. Once a reset has been accomplished, the remaining entries in
the AR command, including the entry with the reset bit set, are processed. To reset all
AR entries for unactivated resources without creating a new equivalence, specify an
AR entry of length X'000C' with the reset bit set.
If this bit is B'0' in an AR entry, the entry is processed without a reset taking place.
Note: Some printers activate resources immediately upon processing an AR entry.
For these printers, the reset flag does not have any effect.
Bit 3 Activation failed NACK flag
If this bit is B'0' in an AR entry, the printer does not return a NACK if the requested
activation fails.
If this bit is B'1' in an AR entry, the printer is allowed to return exception ID X'028F..02'
when the requested activation fails because the requested resource was not found. In
this case, the AR entry remains in effect and the X'028F..02' NACK must be returned
before the next IPDS command is processed.
Note: Not all IPDS printers support X'028F..02'; printers that do not support the
exception ID ignore this flag. Support for this diagnostic aid is specified by
property pair X'F201' in the Device-Control command-set vector of an STM
reply.
Bit 4 Outline-font substitution flag
This flag is used only for raster font activations (RT = X'01' and RT = X'03') and is
ignored for other resource types. IPDS printers that do not support resident outline
fonts ignore this flag. Also, for a font substitution to take place, the printer must either
support only one font resolution and metric technology or a Font Resolution and
Metric Technology (X'84') triplet must be present in the AR entry.
If this bit is B'0', no font substitution is allowed.
If this bit is B'1', an outline font that is selected by using the Resource ID can be
substituted for the requested raster font. If the Resource ID contains date and time
stamp fields that are not binary zeroes, the printer must first search for an outline font
that matches the specified date and time; if that search fails the printer may ignore the
date and time stamp fields and continue the search. All Resource ID triplets in the AR
entry are ignored during the matching process.
If a substitute outline font has been selected and there is a Font Resolution and Metric
Technology (X'84') triplet on the AR entry that specifies fixed metrics, the outline
character increments are adjusted using the triplet information to simulate raster
character increments. Each character increment is rounded off to the nearest pel
using the resolution specified in the triplet.
If an outline font is substituted because this flag was B'1', a subsequent XOA-RRL
activation query for this resource indicates a successful activation regardless of
whether the XOA-RRL query is for a raster resource type or for a coded-font resource
type.


Note: To avoid problems with half-width characters and other double-byte font
complications, font substitution is not done in the following situations:
• When a double-byte coded-font section (RT = X'03') is being activated [IPDS-4-246]
• When a CID-keyed outline font is the only substitution candidate [IPDS-4-247]
Bit 5 Private object name flag
This optional flag indicates the type of name specified in all Fully Qualified Name
(X'02') triplets (with FQN type = X'DE' and a character-encoded name) for this AR
entry. Private names are not optimal for interchange, however sometimes a private
name is the only name available or is the best choice for a specific customer
environment.
B'0' indicates that the name is tied to the object itself regardless of the
environment in which the object is used. For objects that define their name
internally, such as TTFs and CMRs, the internal name is used. Objects that
don't define their name internally might have a name that was assigned by the
object creator that is carried with or linked to the object, in which case that
name is used.
B'1' indicates that the name is private and not necessarily appropriate when the
object is used in a different environment. For example, a file name that was
created by the person who placed the object into a library (when this same
object is placed into a different library, a different name might be used).
Note: The private object name flag is optional; property pair X'F211' in the Device-
Control command-set vector of an STM reply indicates support for this flag.
IPDS printers that do not return X'F211' will ignore the flag. The private object
name flag is also ignored for AR entries that do not contain a Fully Qualified
Name (X'02') triplet (with FQN type = X'DE' and a character-encoded name).
Bits
6–7
Reserved
Bytes
12– entry
Resource ID
If present, this parameter must contain the Resource ID of the resource whose type is
specified in byte 2 and whose format is specified in byte 6. This parameter need not be
present when the Reset flag (in byte 11) is set to B'1'.
Activate Resource Triplet Considerations
If a Resource ID is present in this field and the printer supports Resource ID triplets, the fixed portion of the
Resource ID can be followed by one or more resource ID triplets. Not all printers support Resource ID triplets;
support for specific triplets is indicated by X'F2xx' property pairs in the Device-Control command-set vector of
an STM reply. Unsupported triplets are ignored. If only one triplet of a particular type is needed, but multiple
triplets of that type are specified, the last triplet of that type is used and the extra triplets are ignored; however,
refer to each triplet description for information about multiple triplets of the same type (for example, if there are
multiple X'8B' triplets, the first is used).
The Activate Resource triplets are fully described in the triplets chapter:
“Coded Graphic Character Set Global Identifier (X'01') Triplet”
“Fully Qualified Name (X'02') Triplet”
“Encoding Scheme ID (X'50') Triplet”
“Local Date and Time Stamp (X'62') Triplet”
“Metric Adjustment (X'79') Triplet”
“Font Resolution and Metric Technology (X'84') Triplet”
“Data Object Font Descriptor (X'8B') Triplet” [IPDS-4-248]


“Linked Font (X'8D') Triplet”
“Color Management Resource Descriptor (X'91') Triplet”
The following table specifies the triplets that can be used with each RT/RIDF combination:
**Table 23**. RT/RIDF Triplet Combinations [IPDS-4-249]

| Combination | Triplet | Required? |
| :--- | :--- | :--- |
| **For RT = X'01'** (single-byte LF1-type or LF2-type coded font) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'06'** (MVS Host Unalterable Remote Font environment) | Font Resolution and Metric Technology (X'84') triplet<br>-- used to find a single-byte LF1-type coded font | Optional [IPDS-4-250]|
| **For RT = X'03'** (double-byte LF1-type coded-font section) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'06'** (MVS Host Unalterable Remote Font environment) | Font Resolution and Metric Technology (X'84') triplet<br>-- used to find a double-byte LF1-type coded-font section | Optional [IPDS-4-251]|
| **For RT = X'06'** (code page) with **RIDF = X'03'** (GRID-parts format) | Local Date and Time Stamp (X'62') triplet<br>-- used to identify a particular version of a specific code page | Optional [IPDS-4-252]|
| **For RT = X'07'** (font character set) with **RIDF = X'03'** (GRID-parts format) | Local Date and Time Stamp (X'62') triplet<br>-- used to identify a particular version of a font character set | Optional [IPDS-4-253]|
| **For RT = X'10'** (coded font) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'07'** (coded-font format) | Metric Adjustment (X'79') triplet<br>-- used to adjust metrics in an outline coded font | Optional [IPDS-4-254]|
| **For RT = X'40'** (data object resource) with **RIDF = X'09'** (object-OID format) | Color Management Resource Descriptor (X'91') triplet<br>-- used to specify control information for color management resources | Required for a CMR, ignored for other data-object-resource types [IPDS-4-255]|
| | Coded Graphic Character Set Global Identifier (X'01') triplet<br>-- used to identify the encoding of any following AR-command triplets that contain character-encoded data | Optional [IPDS-4-256]|
| | Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name)<br>-- used to provide an optional object name for the resource (character encoding can be identified with a preceding X'01' triplet; however, if there is no preceding X'01' triplet, the name must be a character string that is encoded as UTF-16BE) | Optional [IPDS-4-257]|
| **For RT = X'41'** (data-object font) with **RIDF = X'0A'** (data-object-font format) | Fully Qualified Name (X'02') triplet (FQN type = X'DE')<br>-- used to specify a Full Font Name for a TrueType/OpenType collection; for the base font | Optional [IPDS-4-258]|
| | Encoding Scheme ID (X'50') triplet<br>-- used to specify how the data to be printed is encoded | Optional [IPDS-4-259]|
| | Data Object Font Descriptor (X'8B') triplet<br>-- used to specify activation parameters for a data-object font | Required [IPDS-4-260]|
| | Linked Font (X'8D') triplet<br>-- used to specify a linked TrueType/OpenType object | Optional [IPDS-4-261]|
| **For RT = X'42'** (data-object-font component) with **RIDF = X'09'** (object-OID format) | Coded Graphic Character Set Global Identifier (X'01') triplet<br>-- used to identify the encoding of any following AR-command triplets that contain character-encoded data | Optional [IPDS-4-262]|
| | Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name)<br>-- used to provide an optional object name for the resource (character encoding can be identified with a preceding X'01' triplet; however, if there is no preceding X'01' triplet, the name must be a character string that is encoded as UTF-16BE) | Optional [IPDS-4-263]|
If a data-object font is activated by means of an AR command, but a Data Object Font Descriptor (X'8B') triplet
is not provided, exception ID X'028F..20' exists. If a Color Management Resource (CMR) is activated by means
of an AR command, but a Color Management Resource Descriptor (X'91') triplet is not provided, exception ID
X'025E..01' exists.

The Activate Setup Name (ASN) command is valid only in home state and causes the printer to activate a
setup name. The setup name is identified with a name that is assigned by the user, known to the printer, and
associated on the printer with some set of configuration settings. The printer attempts to configure itself to
honor the setup name being requested.
There is at most one setup name active at a time on the printer; it is also allowed for there to be no setup name
active. When the ASN command is successfully executed, it replaces the previously active setup name, if any.
If the Acknowledgement Required (ARQ) flag is set, the ASN command will return a reply that contains the
setup name that is active after the ASN processing. An ASN command with the ARQ flag set and with no
Setup Name (X'9E') triplet can therefore be used to obtain the active setup name, if any. When no Setup Name
(X'9E') triplet is provided, the ASN command is not a synchronizing command. Note that an ASN command
without the ARQ flag set and with no Setup Name (X'9E') triplet is treated as a No Operation (NOP) command.
If the setup name being requested is already the active setup name on the printer, the ASN command will
succeed immediately, and thus, in this case, the ASN command is not a synchronizing command.
In the case that a supported setup name is provided, and it is not already the active setup name on the printer,
the printer will eject to a new sheet and stack all received pages; that is, perform a Stack Received Pages
command. Note that this can cause a blank sheetlet if CSE is being used, a blank side if duplex is being used,
or a blank partition if N-up is being used. It is likely that most setup name activation requests will come at the
beginning of what the presentation services program considers a print job, but it is possible in IPDS for setup
name activations to occur any time the printer is in home state.
If the printer does not support the setup name, exception ID X'02A1..00' exists. If the printer supports the setup
name, but cannot accomplish the activation of the setup name without operator intervention, X'02A1..01'
exists. Activation of setup names that require operator intervention is not possible using the ASN command.
It is important to realize that the activation of a setup name will often involve making printer configuration
changes that will cause exception IDs—for example, the action code X'1D' exception IDs in “Conditions
Requiring Host Notification”—to exist. Thus, a successful activation using the ASN might
nonetheless result in one or more NACKs being received by the sender of the ASN. After the NACKs are
processed, an ASN command with no setup name provided can be used to determine whether the activation
succeeded. Note, though, that exception ID X'010A..00', to report a changed active setup name, is not
reported for active setup name changes due to the ASN command; in addition, for such active setup name
changes, no other exceptions should be reported if they are only reporting the change to the active setup name
(as opposed to reporting other configuration changes).
Support for the Activate Setup Name command is indicated with property pair X'700A' in the STM Device-
Control command-set vector.
Note: In the case that a Setup Name (X'9E') triplet is provided and the setup name in the triplet is supported
and is not already activated, the Activate Setup Name command is a synchronizing command. Any
command following a synchronizing command is not processed until all preceding commands have
been completely processed. In addition, the ACK of the ASN is not returned until ASN processing is
complete.
```
Length X'D60A' Flag CID Data
```
The length of the ASN command can be:
Without CID X'0007' or X'000D'–X'00D3' odd values
With CID X'0009' or X'000F'–X'00D5' odd values [IPDS-4-264]


Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The format of the data field for this command is as follows: [IPDS-4-265]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | | Reserved | X'0000' | Reserved | X'0000' [IPDS-4-266]|
| 2 to end of ASN | | Triplet | | Zero or one Setup Name (X'9E') triplet [IPDS-4-267]| |
Bytes 0–1 Reserved
Bytes 2 to end Triplet
This field can be omitted to do a simple query for the active setup name or can contain a Setup
Name (X'9E') triplet to specify the setup name to be activated. If the setup name specified
does not match a setup name on the printer, exception ID X'02A1..00' exists.
If the initial data in this field does not correspond to a Setup Name (X'9E') triplet, the entire
field is ignored as if no triplet had been specified.
The triplet is fully described in the triplets chapter:
“Setup Name (X'9E') Triplet ”
ASN Reply
If the ARQ flag is set on the ASN command, the ASN reply returns the active setup name after the ASN has
been processed, in the form of a Setup Name (X'9E') triplet. If there is no active setup name after ASN
processing, no triplet is returned.
The special data area of an ASN reply has the following format: [IPDS-4-268]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | | Reserved | X'0000' | Reserved [IPDS-4-269]|
| 2 to end of ASN reply | | Triplet | | Zero or one Setup Name (X'9E') triplet [IPDS-4-270]|
Bytes 0–1 Reserved
Bytes 2 to end This field contains a Setup Name (X'9E') triplet containing the name of the active setup name
on the printer, after the processing of the ASN is complete. If there is no active setup name,
this field is omitted.
A printer must ensure that the active setup name being reported here is truly active on the
printer. For example, if a setup name has been activated, but then any setting that is
encompassed by that setup name is changed, in any way and for whatever reason, the setup
name must be considered no longer activated.
If the active setup name changes, exception ID X'010A..00' exists; examples of changes that
would cause X'010A..00':
• A change from one active setup name to another active setup name [IPDS-4-271]
• A change from no active setup name to an active setup name [IPDS-4-272]
• A change from an active setup name to no active setup name [IPDS-4-273]


• A change from an active setup name to the same active setup name but the settings [IPDS-4-274]
corresponding to the setup name have been modified, for example if an operator edits the
active setup name’s definition
Note that exception ID X'010A..00' is not reported for changes due to the ASN command.
The triplet is fully described in the triplets chapter:
“Setup Name (X'9E') Triplet ” [IPDS-4-275]


Apply Finishing Operations
The Apply Finishing Operations (AFO) command is valid only in home state and directs the printer to apply
zero or more finishing operations to the current sheet and each copy of that sheet. The current sheet is the
sheet on which the first copy of the next received page is printed. The operations are not applied to sheets
after the copies of the current sheet.
An AFO command completely replaces any previously sent AFO command for the current sheet.
Specific finishing operations are specified in Finishing Operation (X'85') triplets or in UP
3I Finishing Operation
(X'8E') triplets. If no triplets are specified, this command completely replaces any previously sent AFO
command for the current sheet and is then treated as if it were a No Operation (NOP) command; this provides
a reset function.
```
Length X'D602' Flag CID Data
```
The length of the AFO command can be:
Without CID X'0005' or X'0007'–X'7FFF'
With CID X'0007' or X'0009'–X'7FFF'
However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported.
The format of the data field for this command is as follows: [IPDS-4-276]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 to end of AFO | | Triplets | | Zero or more triplets:<br>X'85' Finishing Operation triplet<br>X'8E' UP3I Finishing Operation triplet [IPDS-4-277]| |
Bytes 0 to end of command
Zero or more triplets
The AFO command contains zero or more Finishing Operation triplets that specify which
finishing operations to apply. If no triplets are specified, this command is treated as if it were a
No Operation (NOP) command.
Printers ignore any triplet that is not supported on this command and no exception is reported.
If byte 0 or the first byte after a triplet is X'00' or X'01' (an invalid triplet length), exception ID
X'027A..01' exists.
The Apply Finishing Operation triplets are fully described in the triplets chapter:
“Finishing Operation (X'85') Triplet”
“UP
3I Finishing Operation (X'8E') Triplet” [IPDS-4-278]


Apply Finishing Operations Triplet Considerations
The two finishing triplets (X'85' and X'8E') can coexist. For example, a hole-punch operation identified by a
X'8E' triplet on an AFO command might be followed by a Z-fold operation identified by a X'85' triplet.
• If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the [IPDS-4-279]
printer converts to the other triplet if necessary.
• If an operation can only be fully specified in one of the triplets, that triplet must be used. [IPDS-4-280]
Hierarchical conflicts between triplets are resolved by standard IPDS nesting rules.
• IPDS nesting rules apply equally to both triplets (for identical finishing operations the triplets are [IPDS-4-281]
interchangeable).
• Compatible nesting combinations are determined by the printing system; UP3I operation-compatibility rules [IPDS-4-282]
apply.
When multiple UP3I devices are connected together to form a specific paper path, the combination of devices
is called a tupel. A UP3I Tupel self-defining field is provided in the XOH-OPC reply for each possible paper path
combination in the line of UP3I devices. Each tupel has an ID in the range X'0001'–X'FFFF' and an optional
Tupel Name. Also, in the XOH-OPC reply is a Media-Destinations self-defining field that lists media destination
IDs supported by the printer (also in the range X'0001'–X'FFFF'). The UP3I Tupel IDs are included in the range
of media-destination IDs.
UP3I finishing operations are applied in the order received using the first device in the tupel that is capable of
performing the finishing operation. If there is more than one finishing operation to be performed on a sheet, a
specific device order within the tupel is required; since paper does not move backwards within the tupel, each
subsequent operation for a sheet must be performed by the same device or a device that is later in the tupel. A
tupel can contain multiple instances of the same type of device; for example, a tupel might contain a cutter that
performs a perforation cut at a location selected by the operator and that device might be followed by another
cutter that also performs a perforation cut at a location selected by the operator. This scenario might be used to
cut a horizontal perforation with the first cutter and a vertical perforation with the second cutter and the finishing
operations would be specified in two finishing operation triplets. Because duplicate finishing operation triplets
are ignored, a sequence number must be used to ensure that the triplets are not identical. [IPDS-4-283]


Begin Page
The Begin Page (BP) command is valid only in home state and causes the printer to enter page state. This
command identifies the beginning of a page. The End Page command ends the page.
Exception ID X'0255..09' exists if the printer has been asked to save pages, but this page is too large to save.
```
Length X'D6AF' Flag CID Data
```
The length of the BP command can be:
Without CID X'0009'
With CID X'000B'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The BP command transfers four bytes of data to the printer. The format of the data field for this command is as
follows:

| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–3 | UNDF | Page ID | Any binary value | Page ID | Any binary value [IPDS-4-284]|
Bytes 0–3 Page ID
These bytes can be returned in the sense bytes of the NACK if an exception is reported to the
host. Refer to “Sense Byte Information”.
The Page ID value should be between X'00000001' and X'FFFFFFFF'. The printer returns the
value X'00000000' for exceptions that occur outside the scope of a page. [IPDS-4-285]


Deactivate Font
The Deactivate Font (DF) command, previously known as Delete Font, transmits one to six bytes of data and
provides a means for the host to deactivate one or more coded fonts, data-object fonts, font indexes, font
character sets, or code pages. In the case of downloaded font resources, the resources are deactivated and
then physically removed from the printer. In the case of LF3 coded fonts, data-object fonts, and resident font
resources, the resources are just deactivated.
Note: Data-object-font components are deactivated using the Deactivate Data-Object-Font Component
(DDOFC) command.
Some printers must keep all fonts that are used on a sheet activated until all of the pages on the sheet have
been received. For these printers, exception ID X'02C5..01' exists if an incomplete buffered sheet (one side of
a duplex sheet, for instance) requires all or part of a coded font that is to be deactivated.
Exception ID X'02C6..01' exists if a font character set or code page to be deactivated is being used in an
activated coded font. Before deactivating a font character set or code page, all coded fonts that use these
components must first be deactivated.
Exception ID X'020D..18' exists if an attempt is made to deactivate a component of a currently activated data-
object font. Before deactivating a data-object-font component or a code page, all data-object fonts that use
these components must first be deactivated. When using deactivation type X'5F' (deactivate all coded fonts
and all associated components), code pages that are used with both a coded font and a data-object font are
not deactivated. When deactivating all font objects, it is recommended that deactivation type X'6E' (deactivate
all data-object fonts) be used first, followed by deactivation type X'5F' (deactivate all coded fonts and all
associated components), followed by the deactivation of all individual components.
Exception ID X'0214..02' exists if the host tries to deactivate any individual coded font, data-object font, coded-
font section, font index, font character set, or code page not currently activated. The deactivate all function of
this command does not cause an exception even if there are no such resources currently in the printer.
When a previously activated resource is deactivated, any activation information for that resource created by a
previous LCPC, LFC, LFI, LFCSC, LSS, LFE, or AR command is deleted. AR command entries for unactivated
resources are not affected by the Deactivate Font command.
The DF command has no effect on the LID-to-HAID mappings specified in the Load Font Equivalence (LFE)
command. In addition, changing the LID-to-HAID mapping with an LFE command does not cause the coded
font or data-object font to be deactivated.
```
Length X'D64F' Flag CID Data
```
The length of the DF command can be:
Without CID X'0006', X'0008', X'0009', or X'000B'
With CID X'0008', X'000A', X'000B', or X'000D'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data for the DF command contains from 1 to 6 bytes. The description of byte 0 identifies the fields that
must be provided for each deactivation type. Data that is provided in bytes 1–5 but is not needed for the
specified deactivation type is ignored. [IPDS-4-286]


| Offset | Type | Name | Range | Meaning | Loaded-Font Subset Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Deactivation type | X'11'<br>X'12'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'2F'<br>X'30'<br>X'3F'<br>X'40'<br>X'4F'<br>X'50'<br>X'51'<br>X'5D'<br>X'5E'<br>X'5F'<br>X'60'<br>X'6E' | Deactivate a single-byte LF1-type or LF2-type coded font and all indexes<br>Deactivate a single-byte font index<br>Deactivate all single-byte LF1-type or LF2-type coded fonts and all indexes<br>Deactivate all single-byte LF1-type or LF2-type coded fonts and all indexes; identical to X'1E'<br>Deactivate double-byte LF1-type coded font section and all indexes<br>Deactivate a double-byte LF1-type coded font section, all higher sections, and all indexes<br>Deactivate a font index for a double-byte coded font section<br>Deactivate all double-byte LF1-type coded fonts and all indexes<br>Deactivate a code page<br>Deactivate all code pages<br>Deactivate a font character set<br>Deactivate all font character sets<br>Deactivate a coded font<br>Deactivate a coded font and all associated components<br>Deactivate all resident coded fonts and all associated components<br>Deactivate all coded fonts<br>Deactivate all coded fonts and all associated components<br>Deactivate a data-object font<br>Deactivate all data-object fonts | Refer to the note following the table. [IPDS-4-287]|
| 1–2 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID; needed for deactivation types X'11', X'12', X'20', X'21', X'22', X'30', X'40', X'50', X'51', and X'60' | X'0001' – X'7EFF' [IPDS-4-288]|
| 3 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier; needed for deactivation types X'20', X'21', and X'22' | X'00' [IPDS-4-289]|
| 4–5 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font inline sequence; needed for deactivation types X'12' and X'22':<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-4-290]|


Note: The deactivation types required for the LF1, LF2, LF3, and LF4 subsets, and those that are optional
are listed in the following table: [IPDS-4-291]

**Table 24**. Required and Optional Deactivation Types [IPDS-4-292]

| Supported Capability | Required Deactivation Types | Optional Deactivation Types |
| :--- | :--- | :--- |
| LF1 | X'11', X'12', X'1E', X'1F' | X'22', X'50', X'51', X'5D', X'5E', X'5F' [IPDS-4-293]|
| LF2 | X'11', X'1E', X'1F' | X'50', X'51', X'5D', X'5E', X'5F' [IPDS-4-294]|
| LF3 | X'30', X'3F', X'40', X'4F', X'50', X'51', X'5D', X'5E', X'5F' | None [IPDS-4-295]|
| LF4 | X'30', X'3F' | None [IPDS-4-296]|
| Double-byte coded fonts (in addition to the supported LFn subset(s)) STM property pair X'B001' | X'20', X'21', X'2F' | None [IPDS-4-297]|
| Data-object fonts STM property pair X'F204' | X'60', X'6E' | None [IPDS-4-298]|
Support for optional deactivation types is indicated in the DF Deactivation Types Supported self-defining field
in an XOH-OPC reply.
Byte 0 Deactivation Type
The deactivation-type parameter identifies the type of resource to be deactivated, and each
deactivation type corresponds to a particular set of resource types. For example, with
deactivation type X'1E', the printer deactivates all resources that were activated as single-byte
LF1-type or LF2-type coded fonts and any associated LF1-type font indexes; X'1E' does not
deactivate any LF3-type coded fonts. On the other hand, deactivation type X'5E' does
deactivate all coded fonts (LF1, LF2, or LF3) regardless of how they were activated.
Exception ID X'0217..02' exists if an invalid or unsupported deactivation-type value is
specified.
When a coded font is activated with the AR command using either the GRID-parts resource ID
format or the coded font format, or when it is activated with the LFE command, the printer
might activate either an LF3-type, LF1-type, or LF2-type coded font. In this case, deactivation
type X'50', X'51', X'5D', X'5E', or X'5F' is best to use.
X'11' This value deactivates the LF1-type or LF2-type single-byte coded font specified in
bytes 1–2 and any associated LF1-type font indexes (must provide bytes 0–2).
X'12' For the single-byte LF1-type coded font specified in bytes 1–2, this value deactivates
the LF1-type font index that matches the font inline sequence specified in bytes 4–5
(must provide bytes 0–5).
X'1E' This value deactivates all single-byte LF1-type and LF2-type coded fonts and any
associated LF1-type font indexes (must provide byte 0).
X'1F' This value deactivates all single-byte LF1-type and LF2-type coded fonts and any
associated LF1-type font indexes. Identical in function to X'1E' (must provide byte 0).
X'20' This value deactivates the double-byte LF1-type coded-font section specified in bytes
1–3 and all associated LF1-type font indexes (must provide bytes 0–3).
X'21' This value deactivates the specified LF1-type coded-font section, sections with
numbers higher than that specified, and all associated LF1-type font indexes of the
specified double-byte coded font. Bytes 1–2 contain the coded-font identifier, and byte
3 contains the starting section number. [IPDS-4-299]


The specified starting section need not be present. However, exception ID X'0214..02'
exists if this deactivation type is specified for a double-byte coded font with no
sections currently activated. Any other use of this deactivation type is valid. For
example, if the specified starting section has a higher number than all activated
sections, no operation is performed. This deactivation type can be used to deactivate
an entire double-byte LF1-type coded font by specifying section X'41' (must provide
bytes 0–3).
X'22' For the double-byte LF1-type coded font section specified in bytes 1–2 and byte 3,
this value deactivates the LF1-type font index that matches the font inline sequence
specified in bytes 4–5 (must provide bytes 0–5).
X'2F' This value deactivates all double-byte LF1-type coded fonts and all associated LF1-
type font indexes (must provide byte 0).
X'30' This value deactivates the code page specified in bytes 1–2 (must provide bytes 0–2).
X'3F' This value deactivates all code pages (must provide byte 0).
X'40' This value deactivates the font character set specified in bytes 1–2 (must provide
bytes 0–2).
X'4F' This value deactivates all font character sets (must provide byte 0).
X'50' This value deactivates the coded font specified in bytes 1-2 (must provide bytes 0–2).
For an LF1-type coded font, this deactivation type is identical in function to X'51'; the
fully described font (or font sections) and all associated font indexes are deactivated.
For an LF3-type coded font, this deactivation type just deactivates the coded font and
does not deactivate the associated components (font character set and code page).
X'51' This value deactivates the coded font specified in bytes 1–2 and all associated
components (must provide bytes 0–2). The associated components were identified by
an AR or LFE command when the coded font was activated. For an LF1-type coded
font, the components consist of a fully described font, or several fully described font
sections, and all associated font indexes. For an LF3-type coded font, the
components consist of a font character set and a code page.
X'5D' This value deactivates all resident coded fonts and all associated components (must
provide byte 0). This deactivation type does not affect downloaded coded-font
components.
X'5E' This value deactivates all coded fonts (must provide byte 0).
X'5F' This value deactivates all coded fonts and all associated components (must provide
byte 0).
Code pages and font character sets that are not associated with a coded font are not
deactivated by X'5F'. To deactivate all font parts, issue three Deactivate Font
commands with deactivation types X'5F', X'3F', and X'4F'.
X'60' This value deactivates the data-object font specified in bytes 1–2 (must provide bytes
0–2).
This deactivation type does not deactivate any of the component parts of the data-
object fonts. Use the DF command to deactivate code pages and the DDOFC
command to deactivate data-object-font components.
X'6E' This value deactivates all data-object fonts (must provide byte 0).
This deactivation type does not deactivate any of the component parts of the data-
object fonts. Use the DF command to deactivate code pages and the DDOFC
command to deactivate data-object-font components. [IPDS-4-300]


Bytes 1–2 Host-Assigned ID
This field is required only for deactivation types X'11', X'12', X'20', X'21', X'22', X'30', X'40',
X'50', X'51', and X'60'. It is ignored if specified for other deactivation types. Exception ID
X'0215..02' exists if this parameter is required, but not supplied; exception ID X'0215..02' also
exists if an invalid or unsupported value is specified.
Byte 3 Section Identifier
This field is required only for deactivation types X'20', X'21', and X'22'. It is ignored if specified
for other deactivation types. Exception ID X'0215..02' exists if this parameter is required, but
not supplied; exception ID X'0215..02' also exists if an invalid or unsupported value is
specified.
Bytes 4–5 Font inline sequence
This parameter specifies the specific LF1-type font index to be deactivated. This parameter is
required only for deactivation types X'12' and X'22'; it is ignored if specified for other
deactivation types. Exception ID X'0215..02' exists if this parameter is required, but not
supplied. Exception ID X'0240..02' exists if an invalid or unsupported value is specified. [IPDS-4-301]


Define User Area
The Define User Area (DUA) command establishes the position and size of an area in the medium
presentation space called the user printable area (UPA) that can be used to specify the portion of the medium
presentation space to which user generated data is restricted. Only data specified within a secure overlay can
be printed outside of the user printable area. The XOA Control Edge Marks and XOA Mark Form commands
can also cause printing outside the user printable area.
The user's valid printable area (VPA) is the intersection of the user printable area, the area that corresponds to
the physical printable area, and the current logical page. The user printable area is defined by the host through
the DUA command. The physical printable area is described to the host by the Printable-Area self-defining field
of the XOH Obtain Printer Characteristics reply. The current logical page is defined by the host through the
Logical Page Descriptor (LPD) command.
Printing defined by a page or a nonsecure overlay cannot take place outside of the user's VPA. Generally,
positioning outside of the user's VPA is valid; however, attempts to merge portions of a page or nonsecure
overlay outside of the user's VPA cause an exception. The Exception Handling Control determines whether or
not these exceptions are reported.
Note: When the DUA command is sent to a printer, the printer allows printing of pages and nonsecure overlays
only within the user's VPA.
The user printable area is ignored when processing a secure overlay.
The DUA command is an optional command that is valid only in home state and does not change the state.
The user printable area specified by a DUA command remains in effect until it is replaced by another DUA
command. If no DUA command has been received by a printer, the default user printable area is the physical
printable area.
Exception ID X'025C..02' exists if one or more of the DUA parameters contain an invalid or unsupported value.
```
Length X'D6CE' Flag CID Data
```
The length of the DUA command can be:
Without CID X'0015'
With CID X'0017'
Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-302]


The format of the data field for the DUA command is as follows: [IPDS-4-303]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Reset | X'00'<br>X'01' | Reset user printable area:<br>X'00' A new UPA is being defined<br>X'01' Reset the UPA to the physical printable area | X'00'<br>X'01' [IPDS-4-304]|
| 1 | CODE | Unit base | X'00'<br>X'01' | Unit base for this command:<br>X'00' Ten inches<br>X'01' Ten centimeters | X'00' [IPDS-4-305]|
| 2–3 | UBIN | UPUB | X'0001' – X'7FFF' | Xm and Ym units per unit base | X'3840' [IPDS-4-306]|
| 4–6 | SBIN | $X_m$ offset | X'FF8000'– X'007FFF' | Xm coordinate of the UPA origin specified in L-units | X'000000'– X'007FFF' [IPDS-4-307]|
| 7–9 | SBIN | $Y_m$ offset | X'FF8000'– X'007FFF' | Ym coordinate of the UPA origin specified in L-units | X'000000'– X'007FFF' [IPDS-4-308]|
| 10–12 | UBIN | Xm extent | X'000001'– X'007FFF' | Xm extent of the UPA specified in L-units | X'000001'– X'007FFF' [IPDS-4-309]|
| 13–15 | UBIN | Ym extent | X'000001'– X'007FFF' | Ym extent of the UPA specified in L-units | X'000001'– X'007FFF' [IPDS-4-310]|
Byte 0 Reset user printable area
A value of X'00' specifies that a new user printable area is being defined. A value of X'01'
specifies that the user printable area is being reset to the physical printable area. If this
parameter contains X'01', the following DUA parameters are ignored.
Byte 1 Unit base for this command
A value of X'00' specifies that the measurement unit is ten inches. A value of X'01' specifies
that the measurement unit is ten centimeters. The value X'02' is retired as Retired item 2.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Bytes 2–3 X
m and Ym units per unit base for this command
This parameter specifies the number of units per unit base in both the Xm and $Y_m$ directions.
Bytes 4–6 Xm coordinate of the user-printable-area origin
This parameter specifies the offset in the $X_m$ direction of the user printable area origin from the
current medium presentation space origin as specified in L-units.
Bytes 7–9 Ym coordinate of the user-printable-area origin
This parameter specifies the offset in the $Y_m$ direction of the user printable area origin from the
current medium presentation space origin as specified in L-units.
Bytes 10–12 Xm extent of the user printable area in L-units
Exception ID X'02A4..02' exists if the user printable area boundary in the X-direction cannot
be represented in the printer.
Bytes 13–15 Y
m extent of the user printable area in L-units
Exception ID X'02A5..02' exists if the user printable area boundary in the Y-direction cannot be
represented in the printer. [IPDS-4-311]



```
Length X'D65D' Flag CID Binary data
```
The length of the END command can be: [IPDS-4-312]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0005'–X'7FFF' [IPDS-4-313]|
| With CID | X'0007'–X'7FFF' [IPDS-4-314]|

Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-315]

The End (END) command is the ending control for a series of Write Image, Write Image 2, Write Graphics,
Write Bar Code, Write Object Container, Write Metadata, Load Code Page, or Load Font commands and
marks either the end of an image object, a graphics object, a bar code object, an object container object, or a
metadata object, or the end of a downloaded font sequence. For text objects, the End command is the ending
control for a series of Write Text commands; note that the End command is not used with text-major text in a
logical page. Zero or more data bytes can be transmitted but are ignored. [IPDS-4-316]

Some objects require at least one command between the control command that begins the object and the End
command; exception ID X'8002..00' exists in the following cases:
• When an LCPC command is not followed by at least one LCP command [IPDS-4-317]
• When an LFC command is not followed by at least one LF command [IPDS-4-318]
• When an LFCSC command is not followed by at least one LF command [IPDS-4-319]
• When a WIC command is not followed by at least one WI command [IPDS-4-320]



```
Length X'D6BF' Flag CID Binary data
```
The length of the EP command can be: [IPDS-4-321]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0005'–X'7FFF' [IPDS-4-322]|
| With CID | X'0007'–X'7FFF' [IPDS-4-323]|

Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-324]

The End Page (EP) command causes the printer to return to home state from page state, page segment state,
or overlay state and thus marks the end of a page, a page segment, or an overlay. The EP command is an
implicit command to schedule that page for printing if the command is being used to exit page state; all data for
that page is available to the printer. Zero or more data bytes can be transmitted but are ignored. [IPDS-4-325]


Include Saved Page
The Include Saved Page (ISP) command is a page state command that causes a previously saved page to be
presented at the origin of the current page presentation space. If page overlays were also saved with the
saved page, the overlays are also presented. Only one ISP command is allowed in a page to be printed; if
more than one ISP command is encountered, exception ID X'0255..04' exists.
Nesting of saved pages is not allowed. If an ISP command is specified within a page that is being saved,
exception ID X'0255..05' exists. Refer to “Saving and Including Pages” for an example of how
various IPDS commands are used for saving and including pages.
If any portion of the saved page, including page overlays saved with the page, extends outside of the physical
printable area, exception ID X'08C2..00' exists. All data within the saved page and within overlays saved with
the page must also stay within the user printable area, if one exists.
For the purposes of VPA checking, an included page is treated just like an included overlay. That is, a page
included with an ISP is positioned at the origin of the including page, but the included page can extend outside
of the including page.
If text suppressions were specified when the page was saved, a separate copy of the page was saved for each
combination of text suppressions. When including a saved page for printing, the appropriate copy of the saved
page is used. If the current LCC command specifies a text suppression combination that was not previously
saved, exception ID X'0255..06' exists.
When processing saved pages, all appropriate CMRs are applied when the page is saved. When a saved page
is later included with an ISP command, no additional color management or color simulation is done by the
IPDS receiver.
Certain printer characteristics can be changed during the period after a group of pages has been saved and
before an attempt to print one of the saved pages occurs. If the saved page group is no longer usable because
of changed printer characteristics, exception ID X'0255..0B' exists.
Implementation note : Host recovery can be accomplished by one of the following methods:
• Remove the saved page group (so that the group name can be reused) and re-save the group with the [IPDS-4-326]
current printer characteristics
• Direct the operator to restore the printer characteristics to those originally used when the group was [IPDS-4-327]
saved
Data stream NACKs might have been reported earlier, when the page was saved; these NACKs do not reoccur
when the ISP command is processed.
Note: When using saved pages, a preprinted form overlay can be invoked for a page with either of the
following sequences of commands:
• BP , IO-with PFO parameter, ISP , EP [IPDS-4-328]
• BP , ISP , IO-with PFO parameter, EP [IPDS-4-329]

```
Length X'D67E' Flag CID Data
```

The length of the ISP command can be: [IPDS-4-330]

| Condition | Length |
| :--- | :--- |
| Without CID | X'000B'–X'7FFF' [IPDS-4-331]|
| With CID | X'000D'–X'7FFF' [IPDS-4-332]|

However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported. [IPDS-4-333]

The format of the data field for this command is as follows: [IPDS-4-334]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–3 | UBIN | Page sequence number | X'00000001'–X'FFFFFFFF' | Page sequence number for the page to be included | X'00000001'–X'FFFFFFFF' [IPDS-4-335]|
| 4 to end of ISP | | Triplets | | One or more ISP triplets:<br>X'00' Group ID triplet with variable-length group ID [IPDS-4-336]| |

**Bytes 0–3 Page sequence number**
The sequence number of the page in the selected saved page group. If the requested page
had not been previously saved, exception ID X'0255..01' exists. If an invalid value is specified,
exception ID X'0255..02' exists. [IPDS-4-337]

**Bytes 4 to end of ISP Triplets**
One or more triplets can be placed at the end of the ISP command (bytes 4 to end).
Printers ignore any triplet that is not supported and no exception is reported. If byte 4 or the
first byte after a triplet is X'00' or X'01' (an invalid triplet length), exception ID X'027A..01'
exists.

The Include Saved Page triplets are fully described in the triplets chapter:
“Group ID (X'00') Triplet” [IPDS-4-338]

**Group ID (X'00') Triplet Considerations**
The Group ID (X'00') triplet with a variable-length group ID is mandatory and identifies the group of saved
pages. If more than one Group ID (X'00') triplet with a variable-length group ID is present in the ISP command,
the last one is used and the others are ignored. If a group of saved pages cannot be found, or if this triplet is
absent, exception ID X'0255..03' exists. [IPDS-4-339]



Invoke CMR (ICMR) is a home state command used to invoke (or reset) one or more CMRs at the home-state
level of the CMR-usage hierarchy; refer to “CMR-Usage Hierarchy” for a description of the
hierarchy and the home-state level. [IPDS-4-340]

A CMR invoked with the ICMR command is used whenever there is no applicable data-object-level CMR,
internal color management information, Resident Color Profile (for EPS and PDF page objects), page-level
CMR, or overlay-level CMR in effect. Home-state-level CMRs remain invoked until they are reset by another
ICMR command or until the printer is reinitialized (returns an IML NACK). [IPDS-4-341]

When an ICMR command is processed by an IPDS printer, the printer first performs the reset (if any) specified
in the invocation flags (byte 0). Then each ICMR entry is processed in sequence; an entry identifies a specific
CMR for a specific CMR type. There can be any number of CMRs invoked at the home-state level, but when
the printer is processing print data, the printer selects for use a specific set of CMRs as described in the “CMR-
Usage Hierarchy”. For example, there can be an audit color-conversion CMR for CMYK data and
an audit color-conversion CMR for RGB data invoked at the home-state level (both have the same CMR type).
If following the hierarchy results in the first encountered color-conversion CMR being at the home-state level,
the printer selects the first CMR when processing CMYK data and the second CMR when processing RGB
data.

This command is used to invoke audit, instruction, and ICC DeviceLink CMRs. Link color-conversion (subset
“LK”) CMRs must be activated, but do not need to be invoked. Invoking a link color-conversion (subset “LK”)
CMR is not an error, but it performs no function. Refer to the CMR-Processing-Modes table, Table 62, for a description of which processing mode is appropriate for each type of CMR. [IPDS-4-342]

Support for this optional command is indicated by the X'706B' property pair in the Device-Control command-set
vector of an STM reply. [IPDS-4-343]

```
Length X'D66B' Flag CID Data
```

The length of the ICMR command can be: [IPDS-4-344]

| Condition | Length |
| :--- | :--- |
| Without CID | X'000A'–X'7FFE' even values [IPDS-4-345]|
| With CID | X'000C'–X'7FFE' even values [IPDS-4-346]|

Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-347]

The data in an ICMR command is defined as follows: [IPDS-4-348]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | BITS | Invocation flags | | bit 0: Reset<br>B'0' Don't reset<br>B'1' Reset to printer defaults<br>bits 1–7: Reserved (B'0000000') | B'0' or B'1' [IPDS-4-349]|
| 1–4 | | Reserved | X'00000000' | Reserved | X'00000000' [IPDS-4-350]|
| 5 to end | | ICMR entries | | Zero or more entries in the format below [IPDS-4-351]| |

**ICMR Entry Format:** [IPDS-4-352]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | HAID | X'0001'–X'7EFF' | Host-Assigned ID of previously activated CMR | X'0001'–X'7EFF' [IPDS-4-353]|

**Byte 0 Invocation flags**
This byte is bit mapped; bit values are as follows:
- **Bit 0 Reset flag**: When B'1', all previous home-state-level CMRs are reset so that there are no [IPDS-4-354]
invoked CMRs at the home-state level. When B'0', this step is skipped (no resets occur). [IPDS-4-355]

After the reset flag has been processed, each ICMR entry is processed in
sequence to invoke additional home-state-level CMRs.
It is not an error to have multiple CMR entries in an ICMR command that have
the same CMR type or apply to the same type of color data, but when
processing print data, the printer follows the CMR-usage hierarchy and
selects only one invoked audit CMR and one invoked instruction CMR of each
CMR type. At each level in the hierarchy, the invoked CMRs are searched in a
LIFO manner (last-invoked to first-invoked). [IPDS-4-356]

**Bits 1–7 Reserved** [IPDS-4-357]

**Bytes 1–4 Reserved** [IPDS-4-358]

**Bytes 5 to end ICMR entries** [IPDS-4-359]

**Entry bytes 0–1 Host-Assigned ID**
This field specifies the Host-Assigned ID (HAID) of a CMR data object
resource to be invoked. Exception ID X'020D..11' exists if an invalid Host-
Assigned ID value is specified.
The data object resource must be a CMR and must have been previously
activated by an AR command or by a home-state WOCC command.
Exception ID X'020D..20' exists if the resource identified by the HAID is not a
CMR or is not currently activated.
Later, when this CMR is selected for use with presentation data, the CMR
must still be activated. Note that when a CMR is deactivated, all invocations
of that HAID are also removed. [IPDS-4-360]


Load Copy Control
The Load Copy Control (LCC) command resets the printer to a new sheet and controls the production of
printed output from subsequently received page data. This command transmits two or more bytes of data to
the printer and is valid only when the printer is in home state.
Note: Some IPDS printers provide a cut-sheet emulation mode that can be used to print on continuous-forms
media that, once slit and collated, emulates two sheets of cut-sheet output. The X'C3nn' keyword
enables this function.
The LCC command contains a sequence of one or more copy-subgroup definitions. Each copy-subgroup
definition contains the controls for producing printed output sides from input pages. Also, each copy-subgroup
definition can include suppression IDs for suppressing text data and overlay IDs for medium and preprinted
form overlays. Some printers also accept a media-source selection and a media-destination selection within a
copy subgroup. Each copy subgroup produces a set of copies of a sheet.
The LCC command allows the host to specify simplex or duplex printing:
Simplex printing
Creates one printed output side per sheet.
Duplex printing
Creates two printed output sides per sheet.
An even number of copy subgroups must be specified for duplex operation. The first copy subgroup in
each pair contains the controls for the copies of the front of a duplex sheet. The second copy subgroup
in each pair contains the controls for the copies of the back of a duplex sheet.
The front side of a duplex sheet consists of front-side pages; the back side of a duplex sheet consists
of back-side pages.
The LCC command can also specify a means of dividing each medium presentation space into a number of
equal-size partitions, using the N-up keyword. When an N-up keyword is specified, the printer divides each
side into equal-sized partitions and prints subsequent pages in these partitions. Each partition has its own
origin from which a page is positioned using the most recently received LPP command; the first partition
always uses the current medium origin as its origin. This allows multiple pages to be positioned in the medium
presentation space.
The number of pages to be printed on a sheet is determined by the value of the N-up keyword and the simplex/
duplex keyword. If simplex is specified, the number of pages on the sheet is the same as the N-up value (1, 2,
3, or 4); if N-up is not specified, there is 1 page on the sheet. If duplex is specified, there are twice that many
pages on the sheet. The LPP command specifies where on the sheet each page is positioned. For example, if
duplex and 3-up is specified, there are 6 pages on the sheet; with proper use of the LPP command, all six of
the pages could be placed on the back side (some of the pages might overlay other pages in this case).
If an XOH Eject to Front Facing, XOH Erase Residual Font Data, XOH Erase Residual Print Data, XOH Page
Counters Control, or XOH Stack Received Pages command is received before all pages of a sheet have been
received, the sheet is printed with only those pages that have been received and the next received page is
treated as if it was the first page of a sheet. This occurs whether or not cut-sheet emulation mode is in effect.
When not in cut-sheet emulation mode, if a new Load Copy Control command for simplex or duplex is received
before all pages of a sheet have been received, the sheet is printed with only those pages that have been
received and the next received page is treated as if it was the first page of a sheet. Once in cut-sheet emulation
mode, the printer emulates cut-sheet output, two sheetlets per sheet, until one of the following occurs:
• An LCC command without the X'C3nn' keyword (CSE) is encountered. [IPDS-4-361]
• An LCC command with the X'C2nn' keyword (N-up) is encountered. [IPDS-4-362]
• More than one copy is specified in any copy subgroup. [IPDS-4-363]


When a subsequent LCC command is received that contains the X'C3nn' keyword and specifies only one copy
in each copy subgroup, the printer returns to the emulation mode.
If an XOA Discard Buffered Data command or an XOA Discard Unstacked Pages command is received before
all pages of a sheet have been received, all the pages of the sheet are discarded along with earlier-received
pages that are not yet committed for printing.
If any other commands that set up the environment for a page are received before all pages of a sheet have
been received, the new environment specified by these commands apply only to the subsequently received
pages.
Each page received is processed once for each simplex copy subgroup defined or once for each pair of duplex
copy subgroups defined; refer to note 1. A given copy subgroup produces one or more identical sides of
output. The number of identical sides is determined by the number of identical copies parameter (byte 1)
specified with each copy subgroup definition. The number of output sides produced for a page is, therefore, the
sum of the number of identical copies parameters from the copy subgroups associated with that page.
If the LCC specified simplex printing, the number of sheets produced is equal to the sum of the “number of
identical copies” parameter from all the copy subgroups specified in the LCC command.
If the LCC specified duplex printing, the number of sheets produced is the sum of the “number of identical
copies” parameter from all copy subgroups divided by two unless an XOA-DBD command or an XOA-DUP
command is received before all pages of a sheet have been received.
The number of sheets produced is also dependent upon whether a page contained exceptions. For data-
stream exceptions, the current XOA Exception Handling Control indicates whether the page should be printed.
Refer to the description of the Exception Page Print bit in the XOA-EHC command for more
information.
Notes:
1. Mixing simplex and duplex copy subgroups causes exception ID X'02C3..01' to exist. All copy subgroups [IPDS-4-364]
defined by the LCC command are either simplex or duplex, as defined by the copy modification keyword
list (bytes 2 to end). Mixing different N-up copy subgroups causes exception ID X'02C0..02' to exist.
2. If duplex is specified and the number of identical copies (byte 1) is not the same for both copy subgroups of [IPDS-4-365]
a copy-subgroup pair, exception ID X'02C4..01' exists. Exception ID X'02C0..01' exists if the type of duplex
(X
$X_m$-axis or $Y_m$-axis) is not the same for both copy subgroups of a copy-subgroup pair. Exception ID
X'0237..05' exists if the media-source ID or the media-destination ID is not the same for both copy
subgroups of a copy-subgroup pair. Exception ID X'02C5..02' exists if duplex is specified and the physical
media is not compatible with duplex printing.
The copy-subgroup definitions specified in the LCC command apply to all subsequent pages received from the
host until another LCC command replaces these definitions. If no LCC command is received, the default
values apply. Each LCC command initializes to the default values; these values specify:
• Eject to new sheet [IPDS-4-366]
• One copy of each sheet [IPDS-4-367]
• Simplex printing [IPDS-4-368]
• One page per side [IPDS-4-369]
• No medium or preprinted form overlays [IPDS-4-370]
• No text suppressions [IPDS-4-371]
• Media selected from the media source specified by an XOH-SIMS command or, if no XOH-SIMS command [IPDS-4-372]
has been received, from the printer-default media source [IPDS-4-373]


• Media stacked in the printer-default media destination [IPDS-4-374]
```
Length X'D69F' Flag CID Data
```
The length of the LCC command can be:
Without CID X'0007'–X'7FFF' odd values
With CID X'0009'–X'7FFF' odd values
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The data in a Load Copy Control command consists of one or more copy subgroup entries that are processed
in the order that they appear in the command. If a syntax error is encountered in one of the entries, the LCC
command is discarded and the previously received LCC command remains in effect. [IPDS-4-375]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Count | X'02'–X'FE' (even values) | Copy-subgroup byte count, including this count field | X'02' [IPDS-4-376]|
| 1 | UBIN | Copies | X'01'–X'FF' | Number of identical copies | X'01'; default value if no LCC is received [IPDS-4-377]|
| 2 to end of copy subgroup | CODE | Keywords | | Zero or more copy modification keyword pairs. The first byte is the keyword ID; the second byte is the parameter. [IPDS-4-378]| |

**Table 25. LCC Keywords** [IPDS-4-379]

| Keyword ID | Parameter Range | Meaning | DC1 Parameter Range |
| :---: | :--- | :--- | :--- |
| X'80' | X'00'–X'FF' | Media source ID | Not in DC1 [IPDS-4-380]|
| X'90' | X'00'–X'FF' | High-order byte of media-destination ID | Not in DC1 [IPDS-4-381]|
| X'91' | X'00'–X'FF' | Low-order byte of media-destination ID | Not in DC1 [IPDS-4-382]|
| X'C1' | X'00' | Simplex (the default) | X'00' [IPDS-4-383]|
| | X'01' | $Y_m$-axis duplex [IPDS-4-384]| |
| | X'02' | $X_m$-axis duplex [IPDS-4-385]| |
| X'C2' | X'01'–X'04' | Number of partitions on each side (N-up) | Not in DC1 [IPDS-4-386]|
| X'C3' | X'00' | Enable cut-sheet emulation, eject to next sheetlet, and do not allow N-up. | Not in DC1 [IPDS-4-387]|
| X'D1' | X'01'–X'FF' | Suppression ID | X'01'–X'7F' [IPDS-4-388]|
| X'D2' | X'01'–X'FE' | Preprinted form overlay ID | Not in DC1 [IPDS-4-389]|
| X'E1' | X'01'–X'FE' | Medium overlay ID | X'01'–X'FE' [IPDS-4-390]|
| X'E4' | X'00'–X'7E' | High-order byte of a medium overlay HAID | Not in DC1 [IPDS-4-391]|
| X'E5' | X'00'–X'FF' | Low-order byte of a medium overlay HAID | Not in DC1 [IPDS-4-392]|
| X'E6' | X'00'–X'7E' | High-order byte of a preprinted form overlay HAID | Not in DC1 [IPDS-4-393]|
| X'E7' | X'00'–X'FF' | Low-order byte of a preprinted form overlay HAID | Not in DC1 [IPDS-4-394]|


Byte 0 Entry byte count
This byte indicates the number of bytes in this copy-subgroup definition entry. The value
includes the count byte itself. Exception ID X'0234..01' exists if an invalid or unsupported
count value is specified.
Byte 1 Number of identical copies
This byte indicates the number of identical copies printed for this copy subgroup. Property pair
X'6001' in the Device-Control command-set vector of an STM reply indicates that the printer
supports up to 255 identical copies and also supports multiple copy subgroups.
If duplex is specified, the number of identical copies must be the same for both copy
subgroups of a copy-subgroup pair; if not, exception ID X'02C4..01' exists. Exception ID
X'0231..01' exists if an invalid or unsupported copies value is specified.
Bytes 2 to end of copy subgroup
Zero or more copy modification keyword pairs
These modification parameters apply to all copies printed for this copy subgroup. The list
consists of two-byte controls:
• The first byte is the control ID. [IPDS-4-395]
• The second byte is the associated parameter for this control ID. [IPDS-4-396]
Notes:
1. These two-byte controls may appear in any order within the list. [IPDS-4-397]
2. Unspecified controls are set to the printer defaults. [IPDS-4-398]
3. Exception ID X'0232..01' exists if an invalid or unsupported keyword ID is specified. [IPDS-4-399]
Valid values for copy modification are as follows:
X'80nn' Media-source ID
This identifier selects the media source from which all sheets for this copy
subgroup are selected. This value overrides the media-source ID specified by
an XOH Select Input Media Source command. If this control ID is not
specified for a copy subgroup, media is selected from the media source
specified by the previously received XOH-SIMS command or, if no XOH-
SIMS command has been received, from the printer-default media source.
Support for this keyword is optional; the media-source support property pair
(X'6002') is returned in the Device-Control command-set vector of an STM
reply if media-source selection on a copy-subgroup level is supported by the
printer. The XOH-OPC Printable-Area self-defining field identifies each
media-source ID supported by the printer.
Exception ID X'02C8..01' exists if the media-source ID specified is not
supported by the printer. Exception ID X'40E8..nn' exists if a supported input
media source ID is specified, but the input media source is not installed; the
X'nn' portion contains the media source ID that is not installed. Exception ID
X'50F8..nn' exists if a supported media source is not available; the X'nn'
portion contains the media source ID that is unavailable.
The media-source keyword pair may be specified only once per copy
subgroup; exception ID X'02C2..02' exists if more than one such keyword pair
is specified in a copy subgroup. [IPDS-4-400]


Note: The following example shows how the LCC and XOH-SIMS commands
work together:
**Table 26. Media Source Commands** [IPDS-4-401]

| IPDS commands | Media source used by the printer |
| :--- | :--- |
| LCC 2, ,3 | 2,D,3 where D is the default media source [IPDS-4-402]|
| LCC 5,7 | 5,7 [IPDS-4-403]|
| SIMS 9 | 9,9 [IPDS-4-404]|
| LCC 2,3 | 2,3 [IPDS-4-405]|
| LCC , ,2 | 9,9,2 [IPDS-4-406]|
| LCC 7 | 7 [IPDS-4-407]|
| SIMS 10 | 10 [IPDS-4-408]|
| LCC 12, | 12,10 [IPDS-4-409]|
| LCC , , , | 10,10,10,10 [IPDS-4-410]|
| SIMS 3 | 3,3,3,3 [IPDS-4-411]|
Note: “LCC 2, ,3” means that there are 3 copy subgroups and that the media-source selection is ID=2 for the first copy
subgroup, no selection for the second copy subgroup, and ID=3 for the third copy subgroup.
X'90nn' High-order byte of the media-destination ID
This keyword specifies the high-order byte of a two-byte media-destination
ID; the low-order byte is specified in the X'91' keyword. The resulting two-byte
ID selects the media destination to which all sheets for this copy subgroup are
routed.
The value X'0000' cannot be used for the two-byte media-destination ID; if it is
specified, exception ID X'0237..03' exists.
Support for this keyword is optional; the media-destination support property
pair (X'6003') is returned in the Device-Control command-set vector of an
STM reply if media-destination selection on a copy-subgroup level is
supported by the printer. The XOH-OPC Media-Destinations self-defining field
specifies the range of media-destination IDs supported by the printer.
Exception ID X'0237..03' exists if the media-destination ID specified is not
supported by the printer. [IPDS-4-412]


X'91nn' Low-order byte of the media-destination ID
This keyword specifies the low-order byte of a two-byte media-destination ID;
the high-order byte is specified in the X'90' keyword. The resulting two-byte ID
selects the media destination to which all sheets for this copy subgroup are
routed.
The value X'0000' cannot be used for the two-byte media-destination ID; if it is
specified, exception ID X'0237..03' exists.
Support for this keyword is optional; the media-destination support property
pair (X'6003') is returned in the Device-Control command-set vector of an
STM reply if media-destination selection on a copy-subgroup level is
supported by the printer. The XOH-OPC Media-Destinations self-defining field
specifies the range of media-destination IDs supported by the printer.
Exception ID X'0237..03' exists if the media-destination ID specified is not
supported by the printer.
Notes:
1. If neither X'90' nor X'91' is specified, all sheets for this copy subgroup are [IPDS-4-413]
routed to the printer's default media destination.
2. If only one of the X'90' and X'91' keywords are specified, the other [IPDS-4-414]
(unspecified) portion of the media-destination ID is X'00'.
3. There can only be one stacker active at a time; when a new media [IPDS-4-415]
destination is selected, the printer must switch to a new stacker if
necessary. The stacked page counter and the stacked copy counter
represent the total number of pages and copies of pages that have been
stacked in all stackers.
4. Exception ID X'0237..04' exists if the media-source ID specified cannot be [IPDS-4-416]
used with the media-destination ID specified.
5. Exception ID X'0105..00' exists if a media-destination ID becomes [IPDS-4-417]
available (supported) or unavailable (not supported). At least one media-
destination ID must be available at all times.
6. The X'90' keyword and the X'91' keyword may be specified only once per [IPDS-4-418]
copy subgroup; exception ID X'02C2..02' exists if more than one such
keyword is specified in a copy subgroup.
7. For some printers, finishing operations can only be done when the output [IPDS-4-419]
is routed to specific media destinations. In this case, when finishing is
selected and an incompatible media destination is selected or defaulted
to, exception ID X'027C..09' exists. [IPDS-4-420]


X'C1nn' Simplex and duplex printing-control parameters:
X'00' specifies simplex printing. Simplex is the default if the printer has not
received an LCC command.
X'01' specifies $Y_m$-axis duplex printing. This type of duplex printing causes a
sheet to print in a normal duplex orientation. See Figure 50. The
medium presentation space for the back side is oriented as if it had been
physically turned around the edge that corresponds to the front-side $Y_m$ axis.
X'02' specifies $X_m$-axis duplex printing. This type of duplex printing causes a
sheet to print in a tumble duplex orientation. See Figure 50. The
medium presentation space for the back side is oriented as if it had been
physically turned around the edge that corresponds to the front-side $X_m$ axis.
Note: The LCC N-up keyword pair together with the simplex/duplex keyword
pair indicates how many pages are to be placed on the sheet. The LPP
command specifies where on the sheet each page is positioned. If
simplex is specified, the number of pages on the sheet is the same as
the N-up value (1, 2, 3, or 4); if N-up is not specified, there is 1 page on
the sheet. If duplex is specified, there are twice that many pages on the
sheet. For example, if duplex and 4-up is specified, there are 8 pages
on the sheet. If another Load Copy Control command is received
before all pages of a sheet have been received, the printer
automatically ejects to the next sheet and process the next received
page as the first page of the new sheet.
The simplex-or-duplex keyword pair may be specified only once per copy
subgroup; exception ID X'02C1..01' exists if more than one such keyword pair
is specified in a copy subgroup. Mixing simplex and duplex copy subgroups
within an LCC command causes exception ID X'02C3..01' to exist. Exception
ID X'02C0..01' exists if the type of duplex (X
$X_m$-axis or $Y_m$-axis) is not the same
for both copy subgroups of a copy-subgroup pair. Exception ID X'02C2..01'
exists if an odd number of duplex copy subgroups is specified. Exception ID
X'0236..01' exists if an invalid or unsupported simplex/duplex parameter is
specified.


**Figure 50. $X_m$-Axis and $Y_m$-Axis for Duplex Printing**
I
B
ii
bi
Xm
Ym
I
B
I
B
I
B
$Y_m$-Axis Duplex (Normal)
$X_m$-Axis Duplex (Tumble)
Front
Logical Page 1
Back
Logical Page 2
Front
Logical Page 1
Back
Logical Page 2
Note: The shaded circles in the illustration represent holes
punched through the sheets of the two examples.
ii
bi
Xm
Ym
ii
bi
Xm
Ym
ii
bi
Xm
Ym


X'C2nn' N-up control
This keyword specifies the number of partitions into which the medium
presentation space on the front side is to be divided. If duplex is also
specified, the same number of partitions are used on the back side. However,
printers that support N-up only with simplex generates exception ID
X'02C0..04' if both duplex and N-up keywords are specified. Refer to Figure
11 for examples of how the N-up keyword can be used. [IPDS-4-421]
The printer partitions each medium presentation space into the specified
number of equal-sized areas in a manner determined by the current physical
media. Figure 51 shows the N-up partition layout for wide continuous-forms
media, narrow continuous forms media, and cut-sheet media; partitioning is
not used with envelope media. Partitioning the data frames for computer
output on microfilm (COM) is identical to the partitioning for cut-sheet media.
If the N-up control is specified for envelope media, exception ID X'02C0..05'
exists.
Not all printers support N-up partitioning; those that do return property pair
X'F7nn' or X'F8nn' in the Device-Control command-set vector of an STM
reply. If an N-up keyword is sent to a printer that does not support N-up
partitioning, exception ID X'0232..01' exists.
The N-up keyword pair may be specified only once per copy subgroup;
exception ID X'02C0..03' exists if more than one such keyword pair is
specified in a copy subgroup. Mixing different N-up copy subgroups in an
LCC command causes exception ID X'02C0..02' to exist.
**Figure 51**. N-up Partitions for Various Physical Media
1 Up
2 Up
3 Up
4 Up


If an invalid N-up parameter value is specified, exception ID X'0237..01'
exists. Valid parameters for the N-up control are:
X'01' Specifies 1-up partitioning. The printer does not divide the medium
presentation space at all.
This is the default if the printer has not received an LCC command or
if this keyword pair is not specified.
X'02' Specifies 2-up partitioning. The printer divides the medium
presentation space into two equal partitions, as determined by the
current physical medium.
If the media type, medium presentation space width, and medium
presentation space length values (as specified in the XOH-OPC
reply) indicates that the current media is wide continuous-forms
media, the side is divided in half perpendicular to the medium
presentation space width (leading edge). If the current media is
narrow continuous-forms media or cut-sheet media, the side is
divided in half parallel to the medium presentation space width.
X'03' Specifies 3-up partitioning. The printer divides the medium
presentation space into three equal partitions, as determined by the
current physical medium.
If the media type, medium presentation space width, and medium
presentation space length values (as specified in the XOH-OPC
reply) indicates that the current media is wide continuous-forms
media, the side is divided in thirds perpendicular to the medium
presentation space width (leading edge). If the current media is
narrow continuous-forms media or cut-sheet media, the side is
divided in thirds parallel to the medium presentation space width.
X'04' Specifies 4-up partitioning. The printer divides the medium
presentation space into four equal partitions, as determined by the
current physical medium.
The side is divided in half perpendicular to the medium presentation
space width (leading edge) and again in half parallel to the medium
presentation space width.
The XOH Set Media Origin command can change the medium origin to any
corner of the medium presentation space and thereby allow a variety of page
layouts on the physical medium. Subsequently received pages are positioned
as specified by the LPP command. Figure 52 through Figure 55
 show how the partitions are laid out on a side for the various
media orientations. The numbers in the figures identify the partitions as used
by the LPP command. Since a different LPP command can be provided for
each page, positioning a particular page in a partition is independent of the
positioning of other pages in other partitions.
Note that when duplexing, the location of the partitions on the back side of a
sheet relative to the location of the partitions on the front side is dependent on
whether normal duplexing or tumble duplexing is specified.
In some cases, the portion of the physical printable area that lies within a
partition is used as the physical printable area of the partition for VPA
calculations. In other cases, a page positioned from a particular partition
origin can overlap into other partitions. Refer to the description of page
placement in the LPP command for a description of this
situation.


**Figure 52**. N-up Partition Layouts with SMO = X'00'
Medium Presentation Space Origin
Partition Origin
1
2
1
2 2 Up
1 Up
3 Up
4 Up
1
1 1
1
2
1
2
3 1
2
3
1
2
3
1     2
3     4
1     2
3     4
1     2
3     4
**Figure 53**. N-up Partition Layouts with SMO = X'01'
2 Up
1 Up
3 Up
4 Up
1
1
1
1    2    3 [IPDS-4-422]
1        2
3        4
Medium Presentation Space Origin
Partition Origin
2 1
1 2
2 11    2    3 [IPDS-4-423]
1    2    3 [IPDS-4-424]
1       2
3       4
1       2
3       4


**Figure 54**. N-up Partition Layouts with SMO = X'02'
2 Up
1 Up
3 Up
4 Up
1 1
1 2 3
1
2
3
1     23     4 [IPDS-4-425]
Medium Presentation Space Origin
Partition Origin
1
2
1 2
1
1
2
1
2
3
1     2
3     4
1     2
3     4
**Figure 55**. N-up Partition Layouts with SMO = X'03'
2 Up
1 Up
3 Up
4 Up
11 1
Medium Presentation Space Origin
Partition Origin
1       2
1       2 1       2 [IPDS-4-426]
1    2    3 [IPDS-4-427]
1    2    3 1    2    3 [IPDS-4-428]
1        23        4 [IPDS-4-429]
1        2
3        4
1        2 3        4 [IPDS-4-430]


X'C3nn' Enable cut-sheet emulation mode and proceed as specified in the keyword
parameter. If an invalid (non-zero) keyword value is specified, some printers
ignore the invalid CSE keyword pair, but other printers report exception ID
X'0237..01'; ignoring the keyword is recommended.
This keyword is ignored if any of the following occurs in any copy subgroup of
this LCC command:
– More than one identical copy is specified
– When X'C300' is specified and an N-up keyword is specified
While in the emulation mode, if a simplex/duplex keyword value changes in
the middle of a sheet, a sheet eject occurs.
If the X'C3nn' keyword is specified more than once in a copy subgroup, the
first keyword is used and the extra keywords are ignored.
Support for this keyword is optional; a cut-sheet emulation mode support
property pair X'F902' is returned in the Device-Control command-set vector of
an STM reply when this function is supported by the printer. Exception ID
X'0232..01' exists if this keyword is sent to a printer that does not support the
emulation mode.
To take effect, the X'C3nn' keyword must be specified in the first copy
subgroup of an LCC command. It can be specified in subsequent copy
subgroups, but these extra keywords are ignored.
When cut-sheet emulation is used, there are two sheetlets on each physical
sheet and all presentation data for a sheetlet is confined to that sheetlet; data
within a sheetlet does not interact or overlap with data for any other sheetlet.
The presentation data includes all pages, page overlays, medium overlays,
and preprinted form overlays.
X'00' Specifies that the printer should enter cut-sheet emulation mode (if
not already in the mode), eject to the next sheetlet, and not allow N-
up. If N-up is specified while X'C300' is in effect, the printer exits cut-
sheet emulation mode.
X'D1nn' Suppression control
X'01'–X'FF' specifies a suppression ID. This identifier allows the later
suppression of text data and activates one or more sets of Begin Suppression
and End Suppression pairs within the Write Text data. This value activates
text suppressions for all text within all medium overlays, preprinted form
overlays, pages, page segments, and page overlays on the sheet side. Refer
to the Presentation Text Object Content Architecture Reference for a
description of the suppression function. Exception ID X'0298..01' exists if an
invalid or unsupported suppression ID parameter is specified. Exception ID
X'0239..01' exists if the number of suppressions is greater than that
supported by the printer.
Property pair X'2002' in the Text command-set vector of an STM reply
indicates support for text suppression IDs in the range X'80'–X'FF' in the LCC
X'D1nn' keyword.


X'D2nn' Preprinted form overlay control
X'01'–X'FE' specifies an overlay ID. This parameter contains the overlay
identifier from a Begin Overlay command and specifies that the overlay is to
be treated as a preprinted form overlay. Note that the overlay ID is the 2nd
byte of a HAID (the first byte is assumed to be X'00'); the print server must
activate the overlay using a HAID of X'00nn'. The host is not required to
activate the specified overlay prior to sending an LCC command, but
exception ID X'0292..01' exists if the printer has not yet received the specified
overlay by the time it is to be merged on the medium. If an invalid keyword
value (X'D200' or X'D2FF') is specified, exception ID X'0238..11' exists.
Note: For printers that provide extended overlay support and preprinted form
overlay support, a PFO can be selected either with the X'D2' keyword
or with a pair of X'E6' and X'E7' keywords. Extended overlay support is
indicated by the X'1102' property pair in the Overlay command-set
vector of an STM reply. Preprinted form overlay support is indicated by
the X'1600' property pair in the Overlay command-set vector of an STM
reply.
The origin of the overlay logical page is positioned at the origin of the medium
presentation space, and the overlay is merged using the formblend mixing
rule after all other print data for the sheet side has been RIPped. The media
origin is located at coordinates x
m=0, ym=0.
Only one X'D2nn' keyword (or one pair of X'E6', X'E7' keywords) is allowed
for each copy subgroup. If more than one is specified for a copy subgroup,
exception ID X'0238..10' exists. The printer does not present the preprinted
form overlay on a sheet side unless there is at least one page (BP... EP)
presented on the side.
Appropriate CMRs are applied when a preprinted form overlay is merged into
the medium presentation space. Refer to “CMR-Usage Hierarchy”
for further information about appropriate CMRs for preprinted form overlays.
Note that when duplexing or using multiple copy subgroups, there can be a
different PFO used for each copy subgroup of a sheet side. This allows for
multipart, simulated preprinted forms.
Note: In the MO:DCA architecture, this type of overlay is invoked as a
medium preprinted form overlay.
X'E1nn' Medium overlay control
X'01'–X'FE' specifies an overlay ID. This identifier allows the later merging of
specified overlays. This parameter contains the overlay identifier from a Begin
Overlay command. Refer to “Begin Overlay” for a description of
the overlay function.
Note: For printers that provide extended overlay support, a medium overlay
can be selected either with the X'E1' keyword or with a pair of X'E4' and
X'E5' keywords. Extended overlay support is indicated by the X'1102'
property pair in the Overlay command-set vector of an STM reply.
When an LCC command is issued to merge a medium overlay on a side, the
origin of the overlay logical page is positioned at the origin of the medium
presentation space, and the overlay logical page is mapped to the medium
presentation space before any other logical pages are mapped to the medium
presentation space. Medium overlays are independent of any partitioning
done by the N-up keyword. If the LCC command specifies multiple overlays,
the overlay logical pages are mapped to the medium before any other logical
pages in the order in which they appear in the LCC command. The media [IPDS-4-431]


origin is located at coordinates x m=0, ym=0. The host is not required to load
the specified overlays prior to sending an LCC command, but exception ID
X'0292..01' exists if the printer has not yet received the specified overlays by
the time they are to be merged on the medium.
The printer does not present medium overlays on a sheet side unless there is
at least one page (BP... EP) presented on the side.
Appropriate CMRs are applied when a medium overlay is merged into the
medium presentation space. Refer to “CMR-Usage Hierarchy” for
further information about appropriate CMRs for medium overlays.
Exception ID X'0290..01' exists if an invalid medium-overlay-control
parameter is specified. Exception ID X'0238..01' exists if the number of
overlays is greater than that supported by the printer.
X'E4nn' High-order byte of a medium overlay HAID
This keyword specifies the high-order byte of a two-byte medium overlay
HAID; the low-order byte is specified in the next X'E5' keyword. For example,
to select medium overlay HAID X'0100', specify X'E401' and X'E500'.
If a X'E4' keyword is specified, the next keyword in the LCC command must
be a X'E5' keyword or exception ID X'0238..03' exists. The combined value
from the X'E4' and X'E5' keywords must be in the range X'0001'–X'7EFF';
exception ID X'0290..01' exists if an invalid overlay HAID value is specified.
Support for the X'E4' and X'E5' keywords is optional; the extended overlay
support property pair (X'1102') is returned in the Overlay command-set vector
of an STM reply if the printer provides extended overlay support. Exception ID
X'0232..01' exists if this keyword is specified but is not supported by the
printer.
Note: For printers that provide extended overlay support, a medium overlay
can be selected either with the X'E1' keyword or with a pair of X'E4' and
X'E5' keywords. Multiple medium overlays can be selected in an LCC
command.
Exception ID X'0238..01' exists if the number of overlays is greater than that
supported by the printer.
X'E5nn' Low-order byte of a medium overlay HAID
This keyword specifies the low-order byte of a two-byte medium overlay
HAID; the high-order byte is specified in the immediately preceding X'E4'
keyword. If a X'E5' keyword is specified, but there is no preceding X'E4'
keyword in the LCC command, exception ID X'0238..03' exists. Exception ID
X'0232..01' exists if this keyword is specified but is not supported by the
printer.
Exception ID X'0238..01' exists if the number of overlays is greater than that
supported by the printer.
X'E6nn' High-order byte of a preprinted form overlay HAID
This keyword specifies the high-order byte of a two-byte overlay HAID to be
treated as a preprinted form overlay; the low-order byte is specified in the next
X'E7' keyword. For example, to select preprinted form overlay HAID X'0E2F',
specify X'E60E' and X'E72F'.
If the X'E6' keyword is specified, the next keyword in the LCC command must
be a X'E7' keyword or exception ID X'0238..04' exists. The combined value
from the X'E6' and X'E7' keywords must be in the range X'0001'–X'7EFF';
exception ID X'0290..01' exists if an invalid overlay HAID value is specified. [IPDS-4-432]


Support for the X'E6' and X'E7' keywords is indicated by having both the
extended-overlay-support property pair (X'1102') and the preprinted-form-
overlay-support property pair (X'1600') in the Overlay command-set vector of
an STM reply. Exception ID X'0232..01' exists if this keyword is specified but
is not supported by the printer.
Note: For printers that provide extended overlay support and preprinted form
overlay support, a preprinted form overlay can be selected either with
the X'D2' keyword or with a pair of X'E6' and X'E7' keywords.
X'E7nn' Low-order byte of a preprinted form overlay HAID
This keyword specifies the low-order byte of a two-byte overlay HAID to be
treated as a preprinted form overlay; the high-order byte is specified in the
immediately preceding X'E6' keyword. If the X'E7' keyword is specified, but
there is no preceding X'E6' keyword in the LCC command, exception ID
X'0238..04' exists. Exception ID X'0232..01' exists if this keyword is specified
but is not supported by the printer.
A printer may limit the maximum number of keywords and copy subgroups supported. Refer to your printer
documentation for the maximum number of keywords and copy subgroups supported by that implementation.
Property pair X'6004' in the Device-Control command-set vector of an STM reply indicates support for as many
LCC copy subgroups and keywords as will fit into the LCC command.
Note: Early IPDS printers did not support full-length LCC commands (length field values up to X'7FFF'). Since
additional keywords have been added and customers are using longer, more complicated LCC
commands, new IPDS printers must support full-length LCC commands. [IPDS-4-433]


Rules for Copy Subgroup Exception Processing
The following rules for copy subgroup processing apply when the printer completes processing a page and
detects at least one reportable data-stream exception on the page. If the printer:
• Supports Independent Exception Page Print, the Exception Page Print bit from the most recently processed [IPDS-4-434]
XOA-EHC command specifies whether or not to print the page.
• Does not support Independent Exception Page Print but has completed processing a page because the [IPDS-4-435]
XOA-EHC command specifies Page Continuation of B'0', the Exception Page Print bit specifies whether or
not to print the page.
• Does not support Independent Exception Page Print but has completed processing a page after successfully [IPDS-4-436]
reaching End Page, the printer prints the page.
The term copy subgroup refers to one of the entries in an LCC command. When an exception within a page is
detected, pages from some of the copy subgroups within the LCC command might be committed for print. The
printer must save the data for each page (BP... EP) on the sheet until all copies of the sheet are committed for
print. When multiple pages per sheet are specified, because each copy subgroup can specify additional
medium overlays, preprinted form overlays, and suppressions, a printer can quickly run out of available
storage. Therefore, the printer syntax checks and processes only the first copy subgroup of each page as it is
received, and then continues with the second and subsequent copy subgroups.
When the printer detects either synchronous or asynchronous data-stream exceptions on a page, its recovery
depends on whether or not the page is to be printed. The following rules describe the various possibilities:
When The Page Is To Be Printed:
The printer reports the exception and continues processing the copy subgroups. The printer:
1. Buffers copies of the sheet resulting from copy subgroups prior to the one in which the exception occurred. [IPDS-4-437]
2. Buffers N partial copies of the page in which the exception occurred (where N is the number of copies [IPDS-4-438]
specified for the copy subgroup in which the exception occurred).
3. Discards upstream data. [IPDS-4-439]
4. Adjusts page and copy counters as follows: [IPDS-4-440]
The Received Page Counter includes the last page received from the host, unless the exception occurred
on the last page of a sheet. The received page counter is incremented for the last page of a sheet after all
copy subgroups are processed for all pages of the sheet.
The Committed Copy Counter might include copies resulting from prior copy subgroups. Refer to “Page
and Copy Counter Adjustments When a Data-Stream Exception Occurs” for a description of
page and copy counter adjustments.
5. Reports any queued NACKs. [IPDS-4-441]
6. Continues processing the copy subgroup in which the exception occurred against the remaining pages of [IPDS-4-442]
the sheet, as if the exception had not occurred.
7. If the data stream exception was synchronous, processes all subsequent copy subgroups using the LCC [IPDS-4-443]
command associated with the page in which the exception occurred.
If the data stream exception was asynchronous, processes the next page, starting with the first copy
subgroup, using the most recently-received LCC command.
8. Enters home state. [IPDS-4-444]


When The Page Is Not To Be Printed:
The printer reports the exception, remembers all pages that have committed copies, and waits for direction
from the host. The printer:
1. Terminates processing for the copy subgroup in which the exception occurs and for all subsequent copy [IPDS-4-445]
subgroups.
2. Discards the copy of the page for the exception copy subgroup. [IPDS-4-446]
3. Saves copies resulting from the previous copy subgroups. [IPDS-4-447]
4. Discards the page with the exception and any subsequent pages that have been received for the sheet. [IPDS-4-448]
5. Enters home state. [IPDS-4-449]
6. Discards upstream data. [IPDS-4-450]
7. Adjusts page and copy counters as follows: [IPDS-4-451]
If a synchronous data stream exception occurred in the first copy subgroup (or, if duplexing, the second
copy subgroup), the received page counter includes all received pages prior to the exception. If a
synchronous data stream exception occurred in a subsequent copy subgroup, the received page counter
includes all but the last page on the sheet.
If an asynchronous data stream exception occurred and if there were any error-free copy subgroups
committed, the received page counter reflects all of the pages on the sheet. If there were no previous error-
free copy subgroups, it reflects none of the pages on the sheet.
The Committed Copy Counter might include copies resulting from prior error-free copy subgroups. Refer to
“Page and Copy Counter Adjustments When a Data-Stream Exception Occurs” for a
description of page and copy counter adjustments.
8. Reports any queued NACKs. [IPDS-4-452]
9. Continues to process as determined by the next command received from the host, as shown in the [IPDS-4-453]
following table:


**Table 27. Exception Continuation Rules** [IPDS-4-454]

| If the data-stream exception is: | And the next command received is: | The printer will: |
| :--- | :--- | :--- |
| Synchronous | Not any of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages</li><li>Load Copy Control.</li></ul> | Continue the copy subgroup processing with the copy subgroup in which the exception occurred. The host must resend the page that caused the exception and all subsequent pages for the sheet. [IPDS-4-455]|
| Asynchronous | Not any of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages</li><li>Load Copy Control.</li></ul> | Process the next page received from the host starting with copy subgroup one, against the most recently-received LCC command. [IPDS-4-456]|
| Synchronous or Asynchronous | One of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages.</li></ul> | For all commands except XOA-DBD and XOA-DUP, buffer the remaining copy subgroups without the exception or any subsequent pages. Process the next page received from the host starting with copy subgroup one, against the LCC command associated with the page in which the exception occurred. [IPDS-4-457]|
| Synchronous or Asynchronous | Load Copy Control | Buffer the remaining copy subgroups without the exception page or any subsequent pages. Process the next page received from the host starting with copy subgroup one, against the most recently-received LCC command. [IPDS-4-458]|
Notes:
1. Multiple data-stream exceptions can be detected on a page if the Page Continuation bit that applies to the [IPDS-4-459]
first exception is B'1'. The XOA-EHC command used for a given synchronous data-stream exception is the
one most recently processed prior to detection of the exception. The adjustments to the NACK counters for
the exception are specified in “Page and Copy Counter Adjustments When a Data-Stream Exception
Occurs”.


2. If an out of storage exception is detected on a page, the copy subgroup rules that apply when the page is [IPDS-4-460]
not to be printed are used.
3. If an asynchronous non-data-stream exception is detected, the host must ensure that the copy counters [IPDS-4-461]
are synchronized as part of the NACK recovery. Whenever the printer is in the middle of copies or of copy
subgroups and an asynchronous non-data-stream exception occurs that does not cause the copy counters
to be adjusted, the XOH-PCC command can be issued to clear the copy counters. Another method is to
issue a new LCC command to reflect the remaining copies and then resend the pages of the partially
finished sheet.
The next page received from the host is considered to be the first page of the next sheet and is processed
against the most recently received LCC command beginning with copy subgroup one. [IPDS-4-462]


Load Font Equivalence
Mapping a font local identifier to a HAID is called establishing a font equivalence. The LFE command is used to
establish font equivalences for both coded fonts and for data-object fonts. The pool of font local IDs for a page
or data object is shared among these two kinds of fonts. Likewise, the complete-font HAID pool is shared
among the two kinds of fonts.
Note: For a TrueType/OpenType font that is to be used as a secondary resource in a presentation data object
such as a PDF or SVG object, the LFE command is not used. Instead, a Data Object Resource
Equivalence 2 (DORE2) command is used to establish a mapping from an internal resource ID to the
HAID of the font. (Note that the Data Object Resource Equivalence (DORE) command cannot be used
for this purpose, since in IPDS, a TrueType/OpenType font used as a secondary resource uses a HAID
in the data-object-font-component HAID pool, but the HAIDs in the DORE command are only searched
for in the data-object-resource HAID pool.)
The Load Font Equivalence (LFE) command maps font local identifiers, specified within text, graphics, or bar
code data, to coded font Host-Assigned ID (HAID) and font inline sequence values and optionally activates
coded fonts by supplying a non-zero GRID. If not activating a coded font, the coded font represented by the
HAID and font inline sequence values does not have to exist in the printer when the printer receives this
command. The details of activation, such as when to move shape data into storage, are left to the printer
implementation; this allows resource management flexibility and efficiency.
If a Global Resource ID (GRID, bytes 5–12) is specified in an LFE entry, the entry is also requesting the
activation of a coded font and assigning a HAID and FIS value to that coded font.
The printer uses the information provided in the GRID to locate the component parts of the coded font using
the following hierarchical method:
1. If the LF3 command subset is supported, the printer attempts to find a font character set and a code page. [IPDS-4-463]
The GCSGID and CPGID values are used to find the code page. If a CPGID value is not supplied, the
search fails. If a GCSGID value is not supplied, the first (or largest) code page that matches the CPGID is
used.
The GCSGID and FGID values are used to find the font character set. If an FGID is not supplied, the
search fails. If a GCSGID is not supplied, the GCSGID used for the code page is used.
If in either case an exact match is not found, the printer may look for a version of the resource that has a
larger character set.
If the components are found, steps 2 and 3 are skipped. Note that the characters in the code page do not
necessarily have to intersect with the characters in the font character set. If not enough information was
provided or if either of the components was not found, step 2 in the hierarchy is used.
2. If LF1-type coded fonts are supported, the printer attempts to find the components of either a single-byte or [IPDS-4-464]
double-byte coded font. The printer first attempts to find a fully described font and a font index. The
GCSGID, CPGID, FGID, and font width values together with the font inline sequence value (LFE bytes 3–
4) are used to find these components. If a fully described font is found that is a section of a double-byte
coded font, the printer continues searching to find all available fully described font sections for this coded
font. If the components are found, step 3 is skipped. If not enough information was provided or if some of
the components were not found, step 3 in the hierarchy is used.
3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set coded font using the [IPDS-4-465]
GCSGID, CPGID, FGID, and font width values. [IPDS-4-466]


In addition, the character shapes and metrics of an outline coded font must be scaled to a specific size; the font
width value is used to derive the scale factors, as follows:
• For fonts with FGID values less than 750 and with FGID values between 3840 and 4095 inclusive (fixed- [IPDS-4-467]
pitch, uniform-character-increment, and PSM fonts), both the horizontal scale factor and the vertical scale
factor are derived from the following algorithm:
1000 X font width [IPDS-4-468]
scale factor = ---------------------------------------------
space character increment (in relative units)
Any fractional value resulting from the division is truncated.
• FGID values between 750 and 2303 inclusive are invalid and the activation fails. [IPDS-4-469]
• For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and 53,247 inclusive, and [IPDS-4-470]
between 61,440 and 65,534 inclusive (typographic, proportionally spaced fonts), both the horizontal scale
factor and the vertical scale factor are three times the font width.
• For fonts with FGID values between 53,248 and 61,439 inclusive, both the horizontal scale factor and the [IPDS-4-471]
vertical scale factor are equal to the font width.
To convert the scale factor values from 1440ths to points, the scale factor value is divided by 20 and rounded
off to the nearest integer. If the result is zero, it is changed to 1.
If a resident coded font activation is not requested by an LFE command, the HAID and FIS values specified by
the LFE command in a font equivalence can still be used in an Activate Resource (AR) command to activate a
resident coded font. This activation can be performed either after the LFE command that specifies the HAID
and FIS values is processed or before that LFE command is processed.
When an LFE command is received in home state, it establishes a new set of font equivalences and resets all
font equivalences established by previous LFE commands. In addition, an LFE command received in home
state resets all HARID-to-GRID mappings that were previously established by LFE commands and deactivates
all resident coded fonts that were previously activated by LFE commands. The activation state of resident
coded fonts that were activated by AR commands is not affected by an LFE command received in home state.
When an LFE command is received in page, page segment, or overlay state, its font equivalences are added
to any font equivalences established by previous LFE commands. If the new font equivalences map font local
IDs that were already mapped by previous LFE commands, the new font equivalences take precedence and
replace the existing font equivalences. If an LFE command received in page, page segment, or overlay state
contains a non-zero GRID, the resident resource activation proceeds as follows:
• If the HARID was already activated for this resource type by a previous download command or by a previous [IPDS-4-472]
AR command, the requested activation fails. If the HARID was already mapped to a different GRID by a
previous LFE entry or command, the requested activation fails and the printer generates exception ID
X'021F..02'.
• The same HAID can be used with more than one FIS for a given GRID. The activation fails, however, if the [IPDS-4-473]
HAID was previously activated with a different GRID. A separate coded font activation must be done for each
desired FIS.
• If the HAID has not been used in a previous coded font activation and if the components of the resident [IPDS-4-474]
resource exist in the device, the activation succeeds.
• If the resident coded font identified by the GRID was already activated by means of a different HARID, it [IPDS-4-475]
remains activated and its resource ID is mapped to both HARIDs. The host can reference the resource
independently by means of either HARID. If a deactivate command is issued against one of the HARIDs, the
corresponding HARID-to-resource-ID mapping is removed, but the resident coded font remains activated by
means of the other HARID.
• When the requested activation fails, a subsequent XOA-RRL activation query normally receives a reply [IPDS-4-476]
indicating that the resource specified by the HARID is not activated. However, if the activation failed because [IPDS-4-477]


the HARID was already in use, the XOA-RRL reply indicates that the resource originally activated with that
HARID is activated.
The font equivalences for a page are those in effect when the Begin Page command is processed, plus those
added in page state and those added by included page segments. If overlays are included on the page, only
font equivalences stored with the overlay are used for processing the overlay. Once overlay processing is
completed, the page font equivalences that existed before the overlay was processed are reestablished.
The font equivalences for an overlay are those in effect when the Begin Overlay command is processed, plus
those added in overlay state. When an overlay is merged with the page by means of the Include Overlay
command, its associated font equivalences can be different from the current page font equivalences. At the
conclusion of an overlay, the previously active font equivalences are restored. Similarly, overlays that are
nested in other overlays can have unique font equivalences.
The font equivalences for a page segment are those in effect when the Include Page Segment command is
processed, plus those added in page segment state and saved with the page segment. At the conclusion of a
page segment, the font equivalences remain the same as they were within the page segment. The effect of
including a page segment is identical to embedding the same string of commands directly.
There are no specified default font equivalences.
```
Length X'D63F' Flag CID Data
```
The length of the LFE command can be:
Without CID X'0005' –X'7FF5' in increments of 16
With CID X'0007' –X'7FF7' in increments of 16
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
An empty LFE command sent in home state can be used to reset all previously established LID-to-HAID
mappings. Property pair X'6009' in the Device-Control command-set vector of an STM reply indicates support
for empty LFE commands.
Note: Some IPDS printers require at least one LFE entry. These printers generate exception ID X'0202..02' if
an empty LFE command is received. [IPDS-4-478]


The data in a Load Font Equivalence command consists of zero or more font equivalence entries that are
processed in the order that they appear in the command. If a syntax error is encountered in one of the entries,
that entry and all following entries in the LFE command are discarded; preceding entries remain in effect. [IPDS-4-479]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | LID | X'00'–X'FE' | Font local ID | X'00'–X'7F' [IPDS-4-480]|
| 1–2 | CODE | HAID | X'0001'–X'7EFF' | Complete font Host-Assigned ID | X'0001'–X'7EFF' [IPDS-4-481]|
| 3–4 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font inline sequence:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-4-482]|
| 5–6 | CODE | GCSGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Graphic Character Set Global ID (GCSGID)<br>Use default value<br>See note 2 [IPDS-4-483]| |
| 7–8 | CODE | CPGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Code Page Global ID (CPGID)<br>Use default value<br>See note 2 [IPDS-4-484]| |
| 9–10 | CODE | FGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Font Typeface Global ID (FGID)<br>Use default value<br>See note 2 [IPDS-4-485]| |
| 11–12 | CODE | FW | X'0000'<br>X'0001'–X'7FFF'<br>X'FFFF' | No value supplied; see note 1<br>Font Width (FW)<br>Use default value<br>See note 2 [IPDS-4-486]| |
| 13 | X'00' | Reserved | X'00' [IPDS-4-487]| | |
| 14 | BITS | LFE flags [IPDS-4-488]| | | |
| | bit 0 | | B'0', B'1' | Symbol Set present in printer | B'0' [IPDS-4-489]|
| | bits 1–2 | | B'00' | Reserved | B'00' [IPDS-4-490]|
| | bit 3 | | B'0', B'1' | Double high | B'0' [IPDS-4-491]|
| | bit 4 | | B'0', B'1' | Italics | B'0' [IPDS-4-492]|
| | bit 5 | | B'0', B'1' | Double strike | B'0' [IPDS-4-493]|
| | bit 6 | | B'0', B'1' | Bold | B'0' [IPDS-4-494]|
| | bit 7 | | B'0', B'1' | Double wide | B'0' [IPDS-4-495]|
| 15 | X'00' | Reserved | X'00' [IPDS-4-496]| | |


Notes:
1. If bytes 5–12 contain zeros, the LFE command is not requesting the activation of a resident, raster fully [IPDS-4-497]
described coded font. Bytes 5–12 are called the Global Resource ID (GRID).
2. These values are printer-specific. Refer to your printer documentation for available values. [IPDS-4-498]
3. Exception ID X'023A..02' exists if the maximum number of activated font components supported by the [IPDS-4-499]
printer is exceeded.
Byte 0 Font local ID
This parameter specifies a font local identifier that can be used within text, graphics, or bar
code data to select a particular font. This value is unique within the equivalence list, so each
font local identifier is mapped to only one font object. Exception ID X'0218..02' exists if an
invalid or unsupported font local ID value is specified. Exception ID X'0219..02' exists if a font
local ID is mapped to more than one Host-assigned ID, font inline sequence, flags
combination.
The value X'FF' is retired as Retired item 3. Exception ID X'0218..02' exists if X'FF' is
specified.
Note that some BCOCA bar code objects use EBCDIC-encoded data; in this case, an
EBCDIC-based font can be selected for the Human-Readable-Interpretation part of the bar
code in one of the following ways:
• Select the default font (the preferred method) [IPDS-4-500]
• Select an EBCDIC coded font [IPDS-4-501]
• Select a data-object font using an EBCDIC code page [IPDS-4-502]
Property pair X'6005' in the Device-Control command-set vector of an STM reply indicates that
the full range of font local IDs is supported.
Bytes 1–2 Complete font Host-Assigned ID
This parameter specifies the host-assigned identifier for either the coded font or data-object
font that is mapped to the font local ID. When text, graphics, or bar code data selects a local ID
that is mapped to this HAID, the font that has been assigned this HAID is selected.
A font Host-Assigned ID can be mapped to more than one set of local identifiers within the
equivalence list. For example, several local identifiers can be mapped to a single font Host-
Assigned ID. Or, a coded font Host-Assigned ID can be mapped to more than one set of font
inline-sequence values or font attribute values. Note that the LFE font inline sequence
parameter, LFE GRID parameters, and the font-modification flags are not used for data-object
fonts.
For LF1-type coded fonts whose components are downloaded, the coded font Host-Assigned
ID is also specified in bytes 0–1 of a Load Font Control command and in bytes 0-1 of a Load
Font Index command. For LF2-type coded fonts that are downloaded, the coded font Host-
Assigned ID is also specified in bytes 15–16 of a Load Symbol Set command. For LF3-type
coded fonts whose components are downloaded, the coded font Host-Assigned ID is also
specified in bytes 3–4 of an Activate Resource command. For data-object fonts and for printer-
resident LF1 and LF2 coded fonts, the font Host-Assigned ID is also specified in bytes 3–4 of
an Activate Resource command. Any value in the range X'0001' through X'7EFF' is valid.
Exception ID X'0218..02' exists if an invalid HAID is specified. Exception ID X'021F..02' exists
if an LFE activation is attempted (that is, if the LFE command GRID fields are not zero) and
the HAID value is already in use. [IPDS-4-503]


Bytes 3–4 Font inline sequence
Note: The LFE font inline sequence parameter is used for coded fonts and is ignored for data-
object fonts.
The font inline sequence is the counter-clockwise rotation of a character pattern relative to the
current inline direction. The font inline sequence value is not used by and is ignored by symbol
set printers that support only one font inline sequence value per text orientation.
When the LFE command is activating an LF3-type coded font, these bytes are used to select
the metrics for a specific writing mode.
For LF1-type coded fonts, these bytes specify the font index table used for character
processing. This table is identified by the font inline sequence in bytes 4 and 5 of the Load
Font Index command; refer to “Load Font Index”. The values in this field are as
follows:
X'0000' = 0°
X'2D00' = 90°
X'5A00' = 180°
X'8700' = 270°
This field combines with the current text inline direction to determine the character rotation
with respect to the X
p,Yp coordinate system. This value need not be verified until there is an
attempt to print using the font.
The font inline sequence applies only to characters used in text or bar code data. For graphics
data, the Set Character Angle drawing order provides analogous function.
Exception ID X'0247..02' exists if an invalid or unsupported font inline sequence value is
specified.
Bytes 5–12 Global resource ID (GRID)
The GRID parameters are used to activate a coded font and must be set to zeros for a data-
object font. If all of the fields of the GRID are set to zero, the LFE entry is not requesting an
activation of coded font. The following books specify many commonly used global IDs that can
be used within a GRID:
Advanced Function Presentation: Printer Information
Technical Reference for Code Pages
Technical Reference for IBM Expanded Core Fonts
Mixed Object Document Content Architecture Reference.
The individual bytes in this eight-byte ID are defined as follows:
Bytes 5–6 Graphic Character Set Global ID (GCSGID)
These bytes contain an IBM-registered Graphic Character Set Global
Identifier. GCSGID's are defined in Corporate Standard: C-S 3-3220-050
(IBM Registry™, Graphic Character Sets and Code Pages). The character
set defined by the GCSGID is associated with the coded font and identifies a
minimum set of coded-font graphic characters required for printing. It can be a
character set that is associated with the code page, with the font character
set, or with both. A value of X'FFFF', the default value, indicates that a
character set consisting of all characters that have assigned code points in
the associated code page is to be used. Printers that support only
downloaded fonts ignore this field.
Note: Code pages and font character sets can each be associated with
multiple character sets. Since the GRID only specifies a single
character set, all graphic characters in the specified character set [IPDS-4-504]


should also belong to a character set associated with the code page
and to a character set associated with the font character set. To
optimize coded-font selection, generators of the GRID should specify
the smallest character set that is a subset of both a character set
associated with the code page and a character set associated with the
font character set.
Bytes 7–8 Code Page Global ID (CPGID)
These bytes contain an IBM-registered Code Page Global Identifier. CPGID's
are defined in Corporate Standard: C-S 3-3220-050 (IBM Registry, Graphic
Character Sets and Code Pages). A value of X'FFFF' indicates that the
printer-default code page is used. Printers that support only downloaded fonts
ignore this field.
Exception ID X'021D..02' exists if the code page is not supported with the
graphic character set.
Note: Exception ID X'021D..02' could be used to report unsupported or
inconsistent values in GCSGID, FGID, and FW fields; however, IPDS
printers typically support all values in these fields and do not check for
inconsistencies.
Bytes 9–10 Font Typeface Global ID (FGID)
These bytes contain an IBM-registered Font Typeface Global Identifier. A
value of X'FFFF' indicates that the printer-default font is used. Printers that
support only downloaded fonts ignore this field.
Bytes 11–12 Font Width (FW)
This two-byte value specifies the width (in 1440ths of an inch) of the font's
space character. This additional qualifier in the GRID selects a point size
within a particular FGID.
A value of X'FFFF' indicates that the printer-default font width is used. Printers
that support only downloaded fonts ignore this field.
Byte 13 Reserved
Byte 14 Flags
Note: The LFE flags are used for coded fonts and are ignored for data-object fonts.
This byte is bit mapped, and the bit values are as follows:
Bit 0 Symbol set present in printer
This bit is used only by printers that support symbol-set coded fonts; it is ignored when
the LFE command references an LF1-type or LF3-type coded font. The bit indicates
whether the symbol set mapped by this entry is to be downloaded by the host or
whether it is already in the printer.
Note: Some symbol-set printers, such as the IBM 4234, require bit 0 to be B'1' before
a resident symbol set identified in bytes 5–12 is activated. Other symbol set
printers ignore bit 0 when bytes 5–12 contain a value other than binary zeroes.
It is, therefore, good practice to always set bit 0 to B'1' for entries that refer to
resident fonts.
A value of B'0' indicates that the host must download a symbol set with the font Host-
Assigned ID of this entry. An LFE entry must be received by the printer before a
symbol set with the same HAID can be downloaded with an LSS command.
A value of B'1' indicates that the symbol set referred to in this LFE entry is not
downloaded because it is currently present in the printer; for example, it is resident or
it has been previously downloaded. [IPDS-4-505]


Bits 1–2 Reserved
Bits 3–7 Font-modification flags
The font-modification flags apply to resident font character sets that have
been activated with a GRID. The font could have been activated with an LFE
command, with a previous AR command using the GRID-parts format, or with
a previous AR command using the coded-font format (with no FCS HAID and
no CP HAID). Some printers apply the font-modifications in other situations as
well, such as with downloaded fonts.
The font-modification flags are not supported by all IPDS printers; printers
that do not support them, ignore these bits. Property pair X'6006' in the
Device-Control command-set vector of an STM reply indicates that the font-
modification flags are supported.
Note: Bits 3–7 of byte 14 may result in a modification of the font by
mechanical or other means. These bits should not be set for fonts that
cannot be modified for legal or other reasons.
Bit 3 Double high
If this bit is set to B'1', each character printed with this font is made
double high, using a device-dependent mechanism. The double-high
mechanism is independent of other character attributes (bold or
double wide, for example) and applies equally to all characters in the
font.
Bit 4 Italics
If this bit is set to B'1', each character printed with this font is
italicized, using a device-dependent italicizing mechanism. This
mechanism is independent of other character attributes (bold, for
example) and applies equally to all characters in the font.
Bit 5 Double strike
If this bit is set to B'1', each character printed with this font is
emphasized, using a device-dependent, double-strike mechanism.
This mechanism is independent of other character attributes (bold, for
example) and applies equally to all characters in the font.
Bit 6 Bold
If this bit is set to B'1', each character printed with this font is printed
in bold, using a device-dependent mechanism. This technique does
not include underscoring or any similar changes to the area near the
character. The intent is to make the text stand out in relation to the
surrounding text or material. The method usually associated with bold
is creating characters that appear to have greater density or
brightness and thick lines compared to surrounding text. The bold
mechanism is independent of other character attributes (italics, for
example) and applies equally to all characters in the font.
Bit 7 Double wide
If this bit is set to B'1', each character printed with this font is made
double wide, using a device-dependent mechanism. This mechanism
is independent of other character attributes (bold, for example) and
applies equally to all characters in the font.
Byte 15 Reserved


Logical Page Descriptor
The Logical Page Descriptor (LPD) command, previously known as the Load Page Descriptor command,
establishes the characteristics of the current logical page for a subsequently sent page or overlay. These
characteristics include the units in which the logical page size is specified, the units in which the offset of the
logical page on the medium presentation space is specified (the offset is specified in the LPP command), and
the units in which the positioning of object areas on the logical page is specified.
Some printers allow a logical page to be colored before any presentation data is placed in the logical page;
coloring is specified with LPD triplets. Support for this optional function is indicated by property pair X'6201' in
the Device-Control command-set vector of an STM reply.
Note: Either or both of the logical page dimensions may range from X'0001' through X'7FFF' L-units. However,
attempts to print outside the valid printable area cause exception ID X'08C1..00' to exist. Refer to “The
Valid Printable Area” for more information.
The initialization values for text-major text data, such as margin settings and line spacing, are also specified by
the LPD command. These values remain in effect until the next LPD command is received, unless superseded
for a given logical page by explicit controls in other commands (for example, the Write Text command control
sequences). If no LPD command is received, the LPD parameters are set through PTOCA-defined defaults or
printer defaults. PTOCA default values are defined for the initial text conditions (bytes 24–42); refer to
Presentation Text Object Content Architecture Reference for a description of these defaults. Printer default
values are used for all other LPD fields; refer to your printer documentation for a description of these defaults.
Note: Text objects specify their own initialization values. Refer to “Interaction Between Text Objects and Text-
Major Text” for more information about the two methods of specifying text.
The LPD that is current when a Begin Overlay command is received becomes part of the overlay. When this
overlay is included on a page, the LPD values stored with the overlay redefine the current logical page for the
extent of the overlay.
The LPD command is valid only when the printer is in home state.
```
Length X'D6CF' Flag CID Data
```
The length of the LPD command can be:
Without CID X'001D', X'0021', X'0025', X'0027', X'0029', X'002B', X'002D', X'002E', X'0030', or X'0032'–
X'7FFF'
With CID X'001F', X'0023', X'0027', X'0029', X'002B', X'002D', X'002F', X'0030', X'0032', or X'0034'–
X'7FFF'
However, the rules for short LPD commands must be followed and each triplet length must be valid. Exception
ID X'0202..02' exists if the command length is invalid or unsupported.
The LPD command contains a variable amount of data; the following combinations are valid:
• Support of a 43 byte long data field is mandatory. [IPDS-4-506]
• It is permissible for a printer to support shorter LPD commands that provide some, but not all of the initial text [IPDS-4-507]
condition parameters. A short LPD command can contain a 24, 28, 32, 34, 36, 38, 40, or 41 byte long data
field.
Note: Not all IPDS printers support short LPD commands. Property pair X'6007' in the Device-Control
command-set vector of an STM reply indicates that the printer will accept short LPD commands.
• Optional triplets can be placed at the end of the LPD command (bytes 43 to end) if support for these triplets [IPDS-4-508]
is indicated by property pairs in the Device-Control command-set vector of an STM reply. [IPDS-4-509]


If a short LPD command is sent to a printer that does not support short LPD commands, or if a triplet is sent to
a printer that does not support LPD triplets, exception ID X'0202..02' exists.
The format of the data field for the LPD command is as follows: [IPDS-4-510]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' [IPDS-4-511]|
| 1 | X'00' | Reserved | X'00' [IPDS-4-512]| | |
| 2–3 | UBIN | XUPUB | X'0001'–X'7FFF' | $X_m, X_p,$ and $I$ units per unit base | X'3840' [IPDS-4-513]|
| 4–5 | UBIN | YUPUB | X'0001'–X'7FFF' | $Y_m, Y_p,$ and $B$ units per unit base; must equal the value in bytes 2–3 | X'3840' [IPDS-4-514]|
| 6 | X'00' | Reserved | X'00' [IPDS-4-515]| | |
| 7–9 | UBIN | $X_p$ extent | X'000001'–X'007FFF' | $X_p$ extent of the logical page | X'000001'–X'007FFF' (Refer to the note following the table.) [IPDS-4-516]|
| 10 | X'00' | Reserved | X'00' [IPDS-4-517]| | |
| 11–13 | UBIN | $Y_p$ extent | X'000001'–X'007FFF' | $Y_p$ extent of the logical page | X'000001'–X'007FFF' (Refer to the note following the table.) [IPDS-4-518]|
| 14 | X'00' | Reserved | X'00' [IPDS-4-519]| | |
| 15 | BITS | Ordered data flags [IPDS-4-520]| | | |
| | bit 0 | | B'0', B'1' | Ordered page | B'0' [IPDS-4-521]|
| | bits 1–7 | | B'0000000' | Reserved | B'0000000' [IPDS-4-522]|
| 16–23 | X'00..00' | Reserved | X'00..00' [IPDS-4-523]| | |
| **Initial text-major conditions:** [IPDS-4-524]| | | | | |
| 24–25 | CODE | $I$-axis orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700'<br>X'FFFF' | 0 degrees<br>90 degrees<br>180 degrees<br>270 degrees<br>Printer default | X'0000'<br>X'FFFF' [IPDS-4-525]|
| 26–27 | CODE | $B$-axis orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700'<br>X'FFFF' | 0 degrees<br>90 degrees<br>180 degrees<br>270 degrees<br>Printer default | X'2D00'<br>X'FFFF' [IPDS-4-526]|
| 28–29 | SBIN | Initial $I$ | X'0000'–X'7FFF' | Initial $I$ print coordinate | X'0000'–X'7FFF' (Refer to the note following the table.) [IPDS-4-527]|
| 30–31 | SBIN | Initial $B$ | X'0000'–X'7FFF' | Initial $B$ print coordinate | X'0000'–X'7FFF' (Refer to the note following the table.) [IPDS-4-528]|
| 32–33 | UBIN | Inline margin | X'0000'–X'7FFF'<br>X'FFFF' | Inline margin<br>Printer default | X'0000'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [IPDS-4-529]|
| 34–35 | UBIN | Interchar. adjustment | X'0000'–X'7FFF'<br>X'FFFF' | Intercharacter adjustment<br>Printer default | X'0000'–X'00FF' (Refer to the note following the table.)<br>X'FFFF' [IPDS-4-530]|
| 36–37 | X'0000' | Reserved | X'0000' [IPDS-4-531]| | |
| 38–39 | UBIN | Baseline increment | X'0000'–X'7FFF'<br>X'FFFF' | Baseline increment<br>Printer default | X'0000'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' [IPDS-4-532]|
| 40 | CODE | LID | X'00'–X'FE'<br>X'FF' | Font local ID<br>Printer default | X'00'–X'7F'<br>X'FF' [IPDS-4-533]|
| 41–42 | CODE | Color | See byte description<br>X'FFFF' | Text color<br>Printer default | X'FF07' [IPDS-4-534]|
| **End of initial text-major conditions** [IPDS-4-535]| | | | | |
| 43 to end of LPD | Triplets | | | Zero or more optional LPD triplets; not all IPDS printers support LPD triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet [IPDS-4-536]| |
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. [IPDS-4-537]


Byte 0 Unit base
A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates that the
unit base is ten centimeters.
The value X'02' is retired as Retired item 4.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0264..02' exists if an invalid or unsupported unit base value is specified.
Byte 1 Retired item 5
Bytes 2–3 Xm, Xp, and I units per unit base
This parameter specifies the number of units per unit base in the $X_m$ direction for positioning
the logical page within the medium coordinate system using the LPP command.
It also specifies the number of units per unit base in both the Xp and I directions for positioning
data objects and text. The PTOCA control sequences that use these units of measure
include: AMB, AMI, DBR, DIR, RMB, RMI, SBI, SIM, SIA, SVI, and TBM.
Exception ID X'0260..02' exists if an invalid or unsupported units per unit base value is
specified.
Bytes 4–5 Ym, Yp, and B units per unit base
This parameter specifies the number of units per unit base in the $Y_m$ direction for positioning
the logical page within the medium coordinate system using the LPP command.
It also specifies the number of units per unit base in both the Yp and B directions for
positioning data objects and text. The PTOCA control sequences that use these units of
measure include: AMB, AMI, DBR, DIR, RMB, RMI, SBI, SIM, SIA, SVI, and TBM.
The value in bytes 4–5 must equal the value in bytes 2–3.
Exception ID X'0261..02' exists if an invalid or unsupported units per unit base value is
specified.
Byte 6 Reserved
Bytes 7–9 $X_p$ extent of the logical page
This parameter is specified in L-units.
The logical page size is independent of the extents defined for the medium presentation space
on which the logical page is positioned by the LPP , IO, or LCC command. The logical page
can be larger, smaller, or equal to the medium presentation space. However, attempts to
merge data outside of the valid printable area cause exception ID X'08C1..00' to exist.
Exception ID X'0262..02' exists if an invalid or unsupported X
p extent value is specified.
Byte 10 Reserved
Bytes 11–13 $Y_p$ extent of the logical page
This parameter is specified in L-units.
Exception ID X'0263..02' exists if an invalid or unsupported $Y_p$ extent value is specified.
Byte 14 Reserved


Byte 15 Ordered data flags
These flags indicate the degree of ordering (sequentially) in the data. This information allows
some printers to improve performance by not buffering sequential data; other printers ignore
these flags. Refer to your printer documentation for more information.
This byte is bit mapped; bit values are as follows:
Bit 0 Ordered page flag
A value of B'1' indicates that the page is ordered. A value of B'0' indicates that
the page is not ordered. A page is ordered if it does not contain any page
segments or overlays, and if all text data and all data objects in the page are
ordered such that physical pel locations on the physical media are accessed
by the printer in a sequential left-to-right and top-to-bottom manner, where
these directions are relative to the top edge of the sheet. Once a physical pel
location has been accessed by the printer, the page data does not require the
printer to re-access that same physical pel location.
If the LPD command is for an overlay, the ordered page flag may be ignored.
It also may be ignored if medium overlays or multiple pages per side are
specified for the side of the sheet on which this page is presented.
Bit 1 Retired item 117
Bit 2 Retired item 118
Bits 3–7 Reserved
Bytes 16–17 Reserved
Byte 18 Retired item 6
Byte 19 Reserved
Byte 20 Retired item 7
Byte 21 Retired item 8
Bytes 22–23 Reserved
Bytes 24–42 Initial Text Conditions
Values specified in these bytes are initial control sequence settings for each page or overlay.
In other words, all control sequence values are reset to the latest LPD data with each Begin
Page (BP) command and Begin Overlay (BO) command. Control sequences that are
unspecified in the LPD command are set to PTOCA-defined defaults with each Begin Page or
Begin Overlay command. Control sequences embedded in the Write Text can change these
initial values as the page or overlay is built.
Since the direction parameter of the Set Intercharacter Adjustment control sequence cannot
be specified in the LPD command, this value defaults to X'00'.
For more information about these initial conditions refer to the description of initial text
conditions in Presentation Text Object Content Architecture Reference.
If an invalid or unsupported initial-text-condition value is specified, one or more of the following
exception IDs exist: X'020C..01', X'020E..02', X'020E..03', X'020E..04', X'020E..05',
X'0210..01', X'0211..01', X'0212..01', X'0218..02', X'023F..02', X'0258..03', X'0268..02',
X'0269..02', X'026A..02', or X'026B..02'.
Property pair X'6005' in the Device-Control command-set vector of an STM reply indicates that
the full range of font local IDs is supported in LPD byte 40.
Bytes 43 to
end
LPD triplets
Optional triplets can be placed at the end of the LPD command (bytes 43 to end of LPD) if
support for these triplets is indicated by property pairs returned in the Device-Control [IPDS-4-538]


command-set vector of an STM reply. If triplets are sent to a printer that does not support LPD
triplets, exception ID X'0202..02' exists.
Printers that support LPD triplets ignore any triplet that is not supported and no exception is
reported. If the first byte of the LPD triplets field or the first byte after a triplet is X'00' or X'01'
(an invalid triplet length), the printer ignores the remaining data within the LPD triplets field.
The Logical Page Descriptor triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Presentation Space Reset Mixing (X'70') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet”
Area Coloring Triplet Considerations
The X'6201' property pair (logical page and object area coloring support) in the Device-Control command-set
vector of an STM reply indicates that the X'4E' and X'70' triplets are supported. These triplets can also be used
to color an object area by specifying them on the WBCC-BCOC, WGC-GOC, WIC2-IOC, WOCC-OCOC, or
WTC-TOC command. If neither triplet is specified, the logical page or object area is transparent and previously
presented data shows through.
Triplets that affect the area's presentation space are processed in the order that they occur. An instance of a
particular triplet overrides all previous instances of that triplet. For example, if a Presentation Space Reset
Mixing (X'70') triplet is followed by a Color Specification (X'4E') triplet specifying blue followed by another Color
Specification (X'4E') triplet specifying red, the area is colored red and the first two triplets are ignored. Also, if a
Color Specification (X'4E') triplet specifying green is followed by a Presentation Space Reset Mixing (X'70')
triplet, the resulting color of the area depends on the reset flag. If the reset flag is B'0' (do not reset), the area is
colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium.
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
The invoked CMRs and the specified rendering intents are associated with all pages and overlays that use this
particular LPD command, and are used according to the CMR-usage hierarchy. Refer to “CMR-Usage
Hierarchy” for a description of the hierarchy and the levels that involve the LPD command. The
scope for CMRs and the rendering intents specified on the LPD command begins and ends for each page and
each overlay that uses that LPD command.
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet is used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the LPD command. [IPDS-4-539]


Logical Page Position
The Logical Page Position (LPP) command, previously known as the Load Page Position command, positions
the logical page origin of a page with respect to the origin of the medium presentation space, or when N-up is
selected in the LCC command, with respect to one of the N-up partition origins.  for a
description of N-up. Some printers also allow a page orientation to be specified in the LPP command. Page
orientation and N-up are optional functions; support for these functions is indicated in the STM command reply.
The location of the logical page origin in the medium presentation space is expressed in L-units using the units
of measure specified in the most recently received LPD command. If the printer has not received any LPD
commands, the printer default units of measure are used. The LPP offset coordinates can be expressed in
positive or negative values; however, printing can occur only within the valid printable area. Refer to “The Valid
Printable Area” for more information.
Note: The relationship between the LPD units of measure and the LPP offset values have been interpreted
differently by different IPDS printers. When LPD commands with different units of measure surround an
LPP command, this difference can cause positioning problems. An IPDS printer uses one of the
following methods:
• Some IPDS printers interpret the LPP offset values when each LPP command is processed, using the [IPDS-4-540]
most recently received LPD units of measure. This is the correct method.
• Some IPDS printers interpret the LPP offset values when each BP command is processed, using the [IPDS-4-541]
most recently received LPD units of measure.
Because of these different interpretations, it is recommended that IPDS presentation services programs
precede each LPP command with the appropriate LPD command and avoid issuing additional LPD
commands between the LPP and BP commands.
The LPP command is valid only when the printer is in home state. The values established by an LPP command
remain in effect until they are replaced by another LPP command. If no LPP command is received by the
printer, the default is to position at a printer assigned location; refer to your printer documentation for details.
**Figure 56** shows how the LPP command positions a logical page on the medium presentation space when
there is one page per side. Similar positioning is done within each partition when there is more than one page
per side.
**Figure 56**. Using the LPP Command to Position the Logical Page When There Is One Page per Side
Medium Presentation Space Origin
Specified offset
Medium Presentation Space
Logical Page
(X = 0, Y = 0)m m
(X = 0, Y = 0)p p
Logical Page Origin


Subsequently received pages are positioned as specified by the most recently received LCC and LPP
commands. The LCC command specifies whether simplexing or duplexing is in effect and specifies how many
pages are placed on the sheet. The LPP command specifies the origin in the medium coordinate system of a
logical page and specifies the orientation of the logical page relative to the medium coordinate system.
**Figure 57** shows an example of some of the ways to position and orient multiple pages on a sheet. In this
example, 2-up tumble-duplex printing is specified in the LCC command, the medium presentation space origin
is changed to the upper-right corner with the XOH-SMO command, and a different LPP command is used for
each of the four pages on the sheet. Notice that each of the pages has been oriented differently. Page 1 has
been placed on the back side in partition 2, but it also overlaps into partition 1; this is accomplished with
negative positioning. Page 3 was placed on top of page 2; pages are placed on the sheet in the order that they
are received in the data stream.
**Figure 57**. Page Positioning and Orientation Examples
Front side Back side
Partition 1
Partition 1
Partition 2
Partition 2
This is
page 1
This is
page 2
This is
page 3
This is
page 4
$X_m$
Ym
Xm
Ym
Xp
Xp
Yp
Yp
Key:
tumble duplex, 2-up partitioning
X’01’
back side, partition 2, offset = (-2”, 2”), 0 degree orientation
front side, partition 1, offset = (2.5”, 2”), 0 degree orientation
front side, partition 1, offset = (8.5”, 9.5”), 90 degree orientation
front side, partition 2, offset = (2”, 13”), 270 degree orientation
Holes punched through the sheet to assist visualization
Partition origin
Some of the IPDS commands used in this example:
LCC:
XOH-SMO:
LPP for page 1:
LPP for page 2:
LPP for page 3:
LPP for page 4:
X
p
Yp
Yp
Xp


**Figure 58** shows a more useful example in which the four pages have been placed on continuous-forms media.
After printing, the media can be burst, trimmed, slit, and collated so that the resulting sheets look as if they had
been printed by a duplex, cut-sheet printer. This is accomplished using some of the defaulting capabilities of
the LPP command; in this case, the pages cannot overlap into other partitions. Notice that the LPP command
for page 1 used default page placement; pages 2, 3, and 4 were explicitly placed in their partitions.
**Figure 58**. Continuous-Forms, Duplex, 2-Up Example
Key:
normal duplex, 2-up partitioning
X’03’
no partition specified, offset = (1”, 2”), 0 degree orientation
back side, partition 2, offset = (1”, 2”), 0 degree orientation
front side, partition 2, offset = (1”, 2”), 0 degree orientation
back side, partition 1, offset = (1”, 2”), 0 degree orientation
Holes punched through the sheet to assist visualization
Partition origin
Some of the IPDS commands used in this example:
LCC:
XOH-SMO:
LPP for page 1:
LPP for page 2:
LPP for page 3:
LPP for page 4:
Front side
Back side
Xm
Ym
Partition 1 Partition 2
This is
page 1
This is
page 2
This is
page 3
This is
page 4
Leading edge
Leading edge
$X_m$
Ym
Partition 1 Partition 2 [IPDS-4-542]


```
Length X'D66D' Flag CID Data
```
The length of the LPP command can be:
Without CID X'000F'
With CID X'0011'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The format of the data field for the LPP command is as follows: [IPDS-4-543]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | X'00' | Reserved | X'00' [IPDS-4-544]| | |
| 1–3 | SBIN | $X_m$ page offset | X'FF8000'–X'007FFF' | $X_m$ offset for the logical page origin specified in L-units | X'000000'–X'001555' (Refer to the note following the table.) [IPDS-4-545]|
| 4 | CODE | Placement | X'00'<br>X'10'<br>X'11'<br>X'20'<br>X'21'<br>X'30'<br>X'31'<br>X'40'<br>X'41' | Page placement:<br>Default placement<br>Partition 1, front side<br>Partition 1, back side<br>Partition 2, front side<br>Partition 2, back side<br>Partition 3, front side<br>Partition 3, back side<br>Partition 4, front side<br>Partition 4, back side | X'00' [IPDS-4-546]|
| 5–7 | SBIN | $Y_m$ page offset | X'FF8000'–X'007FFF' | $Y_m$ offset for the logical page origin specified in L-units | X'000000'–X'001555' (Refer to the note following the table.) [IPDS-4-547]|
| 8–9 | CODE | Orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Page orientation:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' [IPDS-4-548]|
Note: The subset range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports
additional units of measure, the IPDS architecture requires the receiver to at least support a range
equivalent to the subset range relative to each supported unit of measure. More information about
supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”.
Byte 0 Reserved
Bytes 1–3 X
m page offset
This parameter specifies the offset in the $X_m$ direction of a logical page origin specified in L-
units using the units of measure specified in the LPD command. If the printer has not received
an LPD command, the printer default units of measure are used. This is an offset from either
the current media origin or, if N-up is in effect, from the current partition origin. Together, the
X
m and $Y_m$ offset values position the origin of a page's logical page presentation space on one
side of a sheet.


Exception ID X'02AD..01' exists if an invalid or unsupported offset value is specified.
Exception ID X'02A4..01' exists if the $X_m$-offset value specified together with the logical page
extent or IO offset value exceeds the maximum supported by the printer.
Property pair X'6008' in the Device-Control command-set vector of an STM reply indicates that
the printer supports the full range of logical-page-offset values.
Byte 4 Page placement
This parameter specifies where pages are placed relative to an N-up partition origin. The N-up
keyword is specified in the most recently received LCC command; 1-up is the default if no N-
up keyword is specified or if the printer has not received any LCC commands. Each page in
the data stream is placed as specified in the most recently received LPP command.
Two different types of page placement can be specified: default page placement and explicit
page placement. Each page on a sheet can be placed independently from the other pages;
the two page placement types can be mixed on a sheet. The valid printable area is calculated
by the printer differently for the two types of page placement.
Not all IPDS printers support explicit page placement, support for this function is indicated by
property pair X'6101' in the Device-Control command-set vector of an STM reply. Printers that
do not support this function ignore this parameter and perform default page placement. For
printers that do support this function, exception ID X'02AD..02' exists when an invalid or
unsupported placement value is specified.
Default page placement:
When default page placement (X'00') is specified, the page is placed in a manner relative to
page-order sequence. For example, assume that 2-up and duplex was specified in the most
recently received LCC command. If the next received page is the first page to be placed on the
sheet, it is be placed in the first partition on the front side. If, instead, the next received page is
the second page to be placed on the sheet, it is placed in the second partition on the front
side. Likewise, if the next received page is the third page to be placed on the sheet, it is placed
in the first partition on the back side.
When default page placement is specified for a page, the printer uses the portion of the
physical printable area that lies within the selected partition for VPA calculations. Default page
placement does not allow a page to overlap into another partition.
Explicit page placement:
The next received page is placed in the partition (1, 2, 3, or 4) specified in the first half of this
byte, on the side specified in the second half of this byte. Figure 52 through
**Figure 55** show the location of each partition for all possible XOH-SMO values.
For this type of page placement, pages can overlap into other partitions.
When explicit page placement is selected, it is important to send the LCC command to the
printer before any LPP commands for the sheet are sent.
Bytes 5–7 Y
m page offset
This parameter specifies the offset in the $Y_m$ direction of a logical page origin specified in L-
units using the units of measure specified in the LPD command. If the printer has not received
an LPD command, the printer default units of measure are used. This is an offset from either
the current media origin or, if N-up is in effect, from the current partition origin. Together, the
$X_m$ and $Y_m$ offset values position the origin of a page's logical page presentation space on one
side of a sheet.
Exception ID X'02AD..01' exists if an invalid or unsupported offset value is specified.
Exception ID X'02A5..01' exists if the $Y_m$-offset value specified together with the logical page
extent or IO offset value exceeds the maximum supported by the printer.
Property pair X'6008' in the Device-Control command-set vector of an STM reply indicates that
the printer supports the full range of logical-page-offset values. [IPDS-4-549]


Bytes 8–9 Page orientation
This parameter specifies the orientation of the logical page presentation space in the medium
presentation space. The $X_p$ axis is oriented in terms of an angle measured clockwise from the
$X_m$ axis. The positive $Y_p$ axis is rotated 90° clockwise relative to the positive $X_p$ axis. This
parameter effectively rotates the logical page around the logical page origin; it is important to
take this rotation into account when specifying the placement and offset of the logical page.
Not all IPDS printers support page orientation, support for this function is indicated by property
pair X'6101' in the Device-Control command-set vector of an STM reply. Printers that do not
support this function ignore this parameter and orient the logical page at 0°. For printers that
do support this function, exception ID X'02AD..03' exists when an invalid or unsupported
orientation value is specified. [IPDS-4-550]


The Manage IPDS Dialog (MID) command is valid only in home state and causes the printer to either start or
end an IPDS dialog.
Any IPDS command can be used to start an IPDS dialog. If an IPDS dialog has been started and a subsequent
MID command with a Start IPDS Dialog value is received, the MID command is treated like a NOP command.
Likewise, if a MID command with an End IPDS Dialog value is received as the first command of an IPDS
dialog, the MID command is treated like a NOP command.
If the ARQ flag in the MID command is set to B'1', the IPDS dialog does not end until a positive Acknowledge
Reply has been sent. If a NACK is sent in response to a MID command, the state of the IPDS dialog is not
changed.
When an IPDS dialog is ended, but the carrying-protocol session remains active, the printer normally maintains
unchanged the IPDS state machine and all IPDS resources so that when a subsequent IPDS command is
received, the IPDS dialog can continue as if it had not been interrupted at all. If the printer does change any
portion of the IPDS state machine or resource information after an IPDS dialog is ended, the printer must issue
an appropriate action code X'1D' NACK or exception ID X'0100..00' (normal printer restart) when the next IPDS
command is received.
A printer can request the presentation services program to end the current IPDS dialog by issuing exception ID
X'0180..00'.
Note: To avoid problems with older IPDS implementations, some printers do not issue exception ID X'0180..00'
unless a MID command has been received. Therefore, if the printer reports support for the MID
command and the printer can be shared with other host programs, the MID command should be sent to
the printer to signal the start of a dialog.
```
Length X'D601' Flag CID Data
```
The length of the MID command can be: [IPDS-4-551]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0006' [IPDS-4-552]|
| With CID | X'0008' [IPDS-4-553]|

Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-554]

The format of the data field for this command is as follows: [IPDS-4-555]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | Type | X'00'<br>X'01' | Start IPDS dialog<br>End IPDS dialog | X'00'<br>X'01' [IPDS-4-556]|

**Bytes 0 Type**
This byte specifies either to start or to stop an IPDS dialog. If an invalid value is specified,
exception ID X'025B..01' exists. [IPDS-4-557]


```
Length X'D603' Flag CID Binary Data
```

The length of the NOP command can be: [IPDS-4-558]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0005'–X'7FFF' [IPDS-4-559]|
| With CID | X'0007'–X'7FFF' [IPDS-4-560]|
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The No Operation (NOP) command has no effect on presentation. Zero or more data bytes may be present but
are ignored. This command is valid in any printer state. [IPDS-4-561]

The Presentation Fidelity Control (PFC) command is valid only in home state and specifies the fidelity
requirements for certain presentation functions. The desired fidelity for each supported presentation function
can be specified with a triplet on the PFC command. The activate flag can be used to reset all fidelity controls
to their default settings before activating the settings specified in the PFC triplets. A PFC command with no
triplets and with the activate flag set to B'0' resets all fidelity controls to their default settings.
The exception-handling control flowchart, Figure 60, shows the relationship between the PFC
command and the XOA-EHC command.
The Presentation Fidelity Control command applies when data is being printed and not when resources are
activated or are being downloaded.
```
Length X'D634' Flag CID Data
```
The length of the PFC command can be: [IPDS-4-562]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0009' or X'000B'–X'7FFF' [IPDS-4-563]|
| With CID | X'000B' or X'000D'–X'7FFF' [IPDS-4-564]|

However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported. [IPDS-4-565]

The format of the data field for this command is as follows: [IPDS-4-566]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | X'00' | Reserved | X'00' | Reserved | X'00' [IPDS-4-567]|
| 1 | BITS | Fidelity control flags | | bit 0: Activate<br>B'0' Reset to default fidelity controls and activate PFC triplets<br>B'1' Just activate PFC triplets<br>bits 1–7: Reserved (B'0000000') | B'0'<br>B'1' [IPDS-4-568]|
| 2–3 | X'0000' | Reserved | X'0000' | Reserved | X'0000' [IPDS-4-569]|
| 4 to end of PFC | | Triplets | | Zero or more optional PFC triplets:<br>X'74' Toner Saver triplet<br>X'75' Color Fidelity triplet<br>X'86' Text Fidelity triplet<br>X'88' Finishing Fidelity triplet<br>X'96' CMR Tag Fidelity triplet [IPDS-4-570]| |
**Byte 0 Reserved**

**Byte 1 Flags**
If the activate flag is set to B'0', all fidelity controls are reset to their default settings, then the
PFC triplets are processed.
If the activate flag is set to B'1', the supported triplets are used to set specific fidelity controls;
unsupported triplets are ignored. In this case, all other fidelity controls remain at their current
settings.

**Bytes 2–3 Reserved** [IPDS-4-571]


Bytes 4 to end
of PFC
Triplets
Zero or more triplets can be placed at the end of the PFC command (bytes 4 to end). Support
for these triplets is indicated by the PFC Triplets Supported self-defining field returned in the
XOH-OPC reply.
If the activate flag is B'0', all fidelity controls are first set to their default values; if the activate
flag is B'1', this step is skipped. Then the triplets are processed in the order that they occur in
the command; an instance of a particular triplet overrides all previous instances of that triplet.
Printers ignore any triplet that is not supported and no exception is reported. If byte 4 or the
first byte after a triplet is X'00' or X'01' (an invalid triplet length), exception ID X'0254..05'
exists.
The Presentation Fidelity Control triplets are fully described in the triplets chapter:
“Toner Saver (X'74') Triplet”
“Color Fidelity (X'75') Triplet”
“Text Fidelity (X'86') Triplet”
“Finishing Fidelity (X'88') Triplet”
“CMR Tag Fidelity (X'96') Triplet” [IPDS-4-572]


Rasterize Presentation Object
The Rasterize Presentation Object (RPO) command is a home state command that requests that a previously
activated presentation data object resource or overlay be rasterized and cached using the specific orientation
and presentation-object overrides specified in the command. The presentation object is processed as if it had
been included directly in a page or overlay by means of an IDO or IO command; however, part of the
environment is specified within the RPO command and the result is cached but not printed. This command is
used to improve print performance by rasterizing one or more variations of a presentation object before those
variations are actually included in a page or overlay. This is especially useful when the presentation object is
complex or is included multiple times in a print job.
The RPO command can be used to rasterize any of the following types of presentation object: [IPDS-4-573]

**Table 26. RPO Object-Type IDs** [IPDS-4-574]

| Object Type | Description |
| :--- | :--- |
| EPS | Encapsulated PostScript with transparency [IPDS-4-575]|
| EPS | Encapsulated PostScript without transparency [IPDS-4-576]|
| GIF | Graphics Interchange Format [IPDS-4-577]|
| IOCA | Image Object Content Architecture image [IPDS-4-578]|
| JPEG | Joint Photographic Experts Group (AFPC JPEG Subset) [IPDS-4-579]|
| JP2 | JPEG2000 File Format [IPDS-4-580]|
| Overlay | IPDS Overlay [IPDS-4-581]|
| PCL | Printer Command Language page object [IPDS-4-582]|
| PDF | Portable Document Format multiple-page file with transparency [IPDS-4-583]|
| PDF | Portable Document Format multiple-page file without transparency [IPDS-4-584]|
| PDF | Portable Document Format single page with transparency [IPDS-4-585]|
| PDF | Portable Document Format single page without transparency [IPDS-4-586]|
| PNG | Portable Network Graphics (AFPC PNG Subset) [IPDS-4-587]|
| SVG | Scalable Vector Graphics (AFPC SVG Subset) [IPDS-4-588]|
| TIFF | Tag Image File Format (AFPC TIFF Subset) [IPDS-4-589]|
| TIFF | Tag Image File Format with transparency [IPDS-4-590]|
| TIFF | Tag Image File Format without transparency [IPDS-4-591]|
| TIFF | Tag Image File Format multiple-image file with transparency [IPDS-4-592]|
| TIFF | Tag Image File Format multiple-image file without transparency [IPDS-4-593]|
Support for this optional command is indicated by the X'707B' property pair in the Device-Control command-set
vector of an STM reply.
```
Length X'D67B' Flag CID Data
```
The length of the RPO command can be:
Without CID X'0005', X'000C', X'0013', X'0018', or X'001A'–X'7FFF'
With CID X'0007', X'000E', X'0015', X'001A', or X'001C'–X'7FFF'
However, each entry length and each triplet length must also be valid. Exception ID X'0202..02' exists if the
command length is invalid or unsupported.
The parameters of this command identify zero or more activated presentation objects (data object resources or
overlays) to be rasterized and cached. For each object, parameters are specified to describe a desired
orientation relative to the sheet's leading edge. For each data object resource, additional parameters specify
overrides for the object area size and presentation-space-to-object-area mapping.
The current fidelity information (such as a Color Fidelity (X'75') triplet or a Toner Saver (X'74') triplet) is used
while rasterizing the presentation object. Therefore, host software must activate appropriate fidelity information
before issuing the RPO command. [IPDS-4-594]


Some presentation objects require secondary resources; in this case, each secondary resource must be
activated and identified in a DORE or DORE2 command before the RPO command. When an RPO command
is processed, the current DORE or DORE2 command applies to all RPO entries within the RPO command;
therefore, multiple RPO entries should only be used for presentation objects that do not require secondary
resources or for objects that use the same secondary resources.
Color Management Resources (CMRs) are applied when the RPO command is processed. It is important to
invoke the appropriate CMRs before the RPO command is processed, since if CMRs later change when the
preRIPed object is printed, the printer has to reRIP (potentially degrading performance).
When preRIPping a presentation object, Color Management Resources (CMRs) are selected using the CMR-
usage hierarchy and are applied when the RPO command is processed. It is important to invoke the
appropriate CMRs and specify the desired rendering intent before the RPO command is processed, since if
different CMRs and rendering intent are specified later when the presentation object is included in a page or
overlay, the printer will have to reRIP (potentially degrading performance). CMRs can be invoked and a
rendering intent can be specified with triplets on the RPO command or by use of the ICMR and SPE
commands; property pair X'F205' in the Device-Control command-set vector of an STM reply indicates that the
printer supports these triplets on the RPO command.
Once a presentation object selected by an RPO entry is successfully rasterized and cached, it remains in the
cache for a printer-determined amount of time. The cache might be cleared when the printer is powered off or
reinitialized (returns an IML NACK). Also, all cached versions of a resource might be removed from the cache
when the resource is deactivated. Each time an IO or IDO command is encountered, the printer searches the
cache for an appropriate preRIPped object. Attributes used to find an appropriate preRIPped object include:
* Fidelity information (toner saver, color fidelity control) [IPDS-4-595]
* CMRs and rendering intent [IPDS-4-596]
* Device appearance [IPDS-4-597]
* Orientation of the object after taking into consideration media orientation, page or overlay orientation, media source, cut-sheet emulation, sheet side, duplex setting, N-up, paper-feed direction, finishing-operation induced rotation, and the orientation specified in the IO or IDO command [IPDS-4-598]
* RPO and EHC reporting flags [IPDS-4-599]
* Mapping control option [IPDS-4-600]
* Side-sensitive attributes; some attributes, such as highlight color values, can be used on one side of a sheet but not on the other side. For example, highlight color is sometimes provided by a post-processor that can print only on one side. [IPDS-4-601]
* Image Resolution (X'9A') triplet (if the triplet was specified on the IDO command) [IPDS-4-602]
* For multi-page resource objects, Object Offset (X'5A') triplet [IPDS-4-603]
* Object Container Presentation Space Size (X'9C') triplet (if the triplet will be specified on the IDO command) [IPDS-4-604]
* Color Specification (X'4E') triplet (if the triplet was specified on the IDO-DODD command). This triplet is used for bilevel IO-Image objects and for object-container objects that contain bilevel or grayscale image, and is ignored for all other object types. The specific object types that can contain bilevel and grayscale image are identified in the MO:DCA object-type OID registry. [IPDS-4-605]
When printing a previously cached object, the XOA-EHC command in effect at print time controls error
handling.
Implementation Note: A good technique for controlling the cache within the printer is to use a least-recently-
used algorithm when the cache becomes full so as to make room for new cache entries. To allow the
host some idea of how long a cached object remains in the cache when a least-recently-used algorithm
is used, the printer considers an object to be in use each time an object is included (by means of IDO or
IO) and each time the object is selected in an RPO command (but is already in the cache). [IPDS-4-606]


An empty RPO command (that is, one with no entries) is treated as a NOP. If the parameters for an RPO entry
match an already cached version of the resource, the RPO entry does not cause the object to be cached
again. If a syntax error is encountered in one of the entries, that entry and all following entries in the RPO
command are discarded; preceding entries remain in effect.
The purpose of the RPO command is to improve system printing throughput by allowing the printer to
preprocess and cache presentation resource objects before they are actually needed on a page or overlay. If
the resource is subsequently included using an IO or IDO command, a presentation-ready bit map is available.
The efficiency of preprocessing is printer-dependent. The following considerations need to be taken into
account:
Preprocessing Overlays
* The data-object-resource override parameters (bytes 7 to end) are not needed for an overlay resource; if [IPDS-4-607]
these parameters are specified, they are ignored.
* The preprocessed and cached version of an overlay might not be used at include time if any portion of the [IPDS-4-608]
overlay exceeds the physical printable area.
* An overlay that has been preRIPped with the RPO command can be used as a preprinted form overlay [IPDS-4-609]
(PFO).
Preprocessing Data Object Resources
A mapping control that specifies how the object presentation space is mapped to the object area is required for
preprocessing. The mapping control used for preprocessing must be compatible with the mapping control later
used to include the object; if it is not, the preprocessed and cached object is not used for the include.
The mapping control can be specified in byte 14 of the RPO command; if it is not specified there, the mapping
control from byte 11 of the WOCC-OCOC self-defining field (or from byte 11 of the WIC2-IOC self-defining
field) is used; if the optional output control self-defining field was not provided with the object, the default
mapping is used (position and trim for IOCA images and scale to fit for all other data object resources).
* If the mapping control is scale to fit or scale to fill, the object is preprocessed into the object area. [IPDS-4-610]
  - If both $X_{oa}$ extent and $Y_{oa}$ extent values are specified in the RPO command, the preprocessed object is scaled using the object area size specified in the RPO command. [IPDS-4-611]
  - If either of the extent values is not specified in the RPO command, the extent is obtained from the object itself. However, if the optional output control self-defining field is not found in the object, the presentation space size of the object is used. [IPDS-4-612]
  If a subsequent IDO command selects the same mapping option, one of the preprocessed orientations, and the same object area size, the cached version of the object can be used.
* If the mapping control is center-and-trim, the object is first preprocessed into the object presentation space. [IPDS-4-613]
  - If both $X_{oa}$ extent and $Y_{oa}$ extent values are specified in the RPO command, the preprocessed object is then trimmed to fit into the object area. If a subsequent IDO command selects center-and-trim, one of the preprocessed orientations, and the same object area size, the cached version of the object can be used. [IPDS-4-614]
  - If either of the extent values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. [IPDS-4-615]
* If the mapping control is position-and-trim, the object is first preprocessed into the object presentation space. [IPDS-4-616]
  - If $X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, and $Y_{oa}$ offset values are specified in the RPO command, the preprocessed presentation space is positioned in the RPO-specified object area, trimmed to fit the object area, and cached. If a subsequent IDO command selects position-and-trim, one of the preprocessed orientations, and the same object area size, the cached version of the object can be used. [IPDS-4-617]
  - If any one of the four object area values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. [IPDS-4-618]
* If the mapping control is position, the object is first preprocessed into the object presentation space. Note that the position mapping option is not valid for an IO Image. [IPDS-4-619]
  - If $X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, and $Y_{oa}$ offset values are specified in the RPO command, the preprocessed presentation space is positioned in the RPO-specified object area, and as long as there is no overflow of the object area, is cached. If there is an overflow, no caching occurs. If a subsequent IDO command selects the position mapping option, one of the preprocessed orientations, and the same offset, the cached version of the object can be used. [IPDS-4-620]
  - If any one of the four object area values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. [IPDS-4-621]
* If the mapping control is one of the migration mapping options (point-to-pel, point-to-pel-with-double-dot, [IPDS-4-622]
or replicate-and-trim), the object is not preprocessed and is not cached.
The RPO command supports most presentation parameters that can be in effect when the preprocessed
object is actually presented. However there are circumstances in which the preprocessed object cannot be
used for presentation and therefore the system throughput improvement is not realized.
* Color mapping tables are not used when the RPO command is processed. When a non-reset color mapping [IPDS-4-623]
table is active, the printer ignores all RPO entries. When a non-reset color mapping table is active, the printer
ignores all RPO cached objects when processing IO and IDO commands.
Implementation Note: The RPO command was designed for use with impositioning jobs that are
constructed from PostScript data and for full-process color image jobs that can benefit from early
rasterization. Jobs that use a color mapping table do not benefit from a printer's preRIP capability; to
gain the preRIP benefit jobs can be modified to call out specific colors or to use multiple variations of
an image instead of using a migration aid such as the color mapping table. If it ever becomes
important to extend RPO support to make use of color mapping tables, the printer might have to find a
way to distinguish among color mapping tables (for example, a bit-by-bit compare or using a
checksum); this level of support is considered to be too complex and not of sufficient benefit to be
provided in the initial RPO support.
* When a Color Specification (X'4E') triplet is specified in an IDO-DODD command to override bilevel and [IPDS-4-624]
grayscale image color in an object container or the color in the Set Bilevel Image Color IOCA self-defining
field (or the Set Extended Bilevel Image Color IOCA self-defining field) in an IO-Image object, the printer
might not be able to use any previously cached variations of this object. [IPDS-4-625]


* When an image-migration mapping control is specified in an IDO command for an IO Image, the printer might [IPDS-4-626]
not be able to use any previously cached variations of this object. Migration mapping controls include: point-
to-pel (X'41') mapping, point-to-pel-with-double-dot (X'42') mapping, and replicate-and-trim (X'50') mapping.
* If a synchronous exception is detected while rasterizing an object for an RPO command and if the exception [IPDS-4-627]
reporting flags or Color Fidelity (X'75') triplet specify that an error is to be reported or if the error does not
have an AEA: the rasterization process ends, the object is not cached, and the exception is not reported.
Also, if the Color Fidelity (X'75') triplet continuation rule is stop: the rasterization process ends, the object is
not cached, and the exception is not reported. Also, while processing an RPO entry, if any XOA Exception
Handling Control commands are encountered within the object: the rasterization process ends, the object is
not cached, and the exception is not reported.
* Some printers support finishing operations, such as edge stitching and corner stapling, that are allowed on [IPDS-4-628]
all four edges or corners. These printers cannot physically perform the operation on some of the edges or
corners and therefore automatically rotate presentation data to effectively reposition the requested edge to a
position where the operation can be performed. This automatic rotation must be accounted for when
specifying the desired orientation in the RPO command.
* Text suppressions within an overlay are not activated during RPO processing and therefore the overlay is [IPDS-4-629]
cached with no text suppression.
* The XOA-PQC command is ignored during RPO processing and the printer's default print-quality level is [IPDS-4-630]
assumed.
The format of the RPO data is as follows: [IPDS-4-631]

**Table 25. RPO Entry Syntax** [IPDS-4-632]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0007' or<br>X'0013' to end of entry | Length of RPO entry, including this length field | X'0007' or<br>X'0013' to end of entry [IPDS-4-633]|
| 2 | CODE | RT | X'05'<br>X'40' | Resource type:<br>Overlay<br>Data Object Resource | X'05'<br>X'40' [IPDS-4-634]|
| 3–4 | CODE | HAID | X'0001'–X'7EFF' | Presentation object's Host-Assigned ID | X'0001'–X'7EFF' [IPDS-4-635]|
| | | **Desired presentation environment** [IPDS-4-636]| | | |
| 5 | BITS | Exception reporting flags | | bit 0: Undefined character<br>bit 1: Page position<br>bits 2–5: Reserved (B'0000')<br>bit 6: Highlight<br>bit 7: Others [IPDS-4-637]| |
| 6 | BITS | Additional processing information flags | | bit 0: 0 degrees (Orient 0 degrees)<br>bit 1: 90 degrees (Orient 90 degrees)<br>bit 2: 180 degrees (Orient 180 degrees)<br>bit 3: 270 degrees (Orient 270 degrees)<br>bit 4: All objects (Process all objects of a multi-page resource file)<br>bits 5–7: Reserved (B'000') [IPDS-4-638]| |
| | | **Data-object-resource override parameters** [IPDS-4-639]| | | |
| 7 | CODE | Unit base | X'00'<br>X'01'<br>X'FF' | Ten inches<br>Ten centimeters<br>Not specified | X'00'<br>X'FF' [IPDS-4-640]|
| 8–9 | UBIN | UPUB | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ and $Y_{oa}$ units per unit base<br>Not specified | X'3840'<br>X'FFFF' [IPDS-4-641]|
| 10–11 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ extent of object area in L-units<br>Not specified | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-4-642]|
| 12–13 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ extent of object area in L-units<br>Not specified | X'0001'–X'7FFF'<br>X'FFFF' [IPDS-4-643]|
| 14 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'60'<br>X'FF' | Override for mapping control option:<br>Position (not valid for IO Image)<br>Scale to fit<br>Center and trim<br>Position and trim<br>Scale to fill<br>Not specified | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'FF' [IPDS-4-644]|
| 15–16 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ offset in L-units; (for the position-and-trim and position mappings only)<br>Not specified | X'0000'–X'7FFF'<br>X'FFFF' [IPDS-4-645]|
| 17–18 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ offset in L-units; (for the position-and-trim and position mappings only)<br>Not specified | X'0000'–X'7FFF'<br>X'FFFF' [IPDS-4-646]|
| 19 to end of entry | | Triplets | | Zero or more triplets:<br>X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet [IPDS-4-647]| |


Note: The required range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the specified range in 1440ths plus an equivalent range for
additional units of measure. If a receiver supports additional units of measure, the IPDS architecture
requires the receiver to at least support a range equivalent to the specified range relative to each
supported unit of measure. More information about supported-range requirements is provided in the
section titled “L-Unit Range Conversion Algorithm”.
Bytes 0–1 Entry length
This field contains the length of this RPO entry; the value includes the length field itself. If an
invalid value is specified in this field or if the entry is too long to fit into the RPO command,
exception ID X'0257..01' exists.
For an overlay, the length field should contain X'0007'; the data-object-resource override
parameters (bytes 7 to end) are not used for an overlay and if specified, are ignored.
For a data object resource, the length field should contain either X'0007', X'0013', or a value of
X'0015' or larger in which each triplet length is valid.
Byte 2 Resource type
This field specifies the type of resource for this RPO entry. If an invalid value is specified in this
field, exception ID X'0257..02' exists.
Bytes 3–4 Host-Assigned ID
This field specifies the Host-Assigned ID (HAID) of the resource to be rasterized and cached.
For data object resources, the printer supports HAIDs in the range X'0001'–X'7EFF'. The
printer also supports this range for overlays when extended overlay support is provided;
however, the range is X'0001'–X'00FE' for printers that do not provide extended overlay
support. Exception ID X'0257..03' exists if an invalid or unsupported Host-Assigned ID value is
specified.
The resource must have been previously activated by an AR command, a home-state WOCC
command, a home-state WIC2 command, or a BO command. Exception ID X'0257..04' exists
if the resource identified by the resource-type and HAID fields has not been activated.
Exception ID X'0257..02' exists if the object-type OID in the resource is not valid for the RPO
command. Valid resources for the RPO command are listed in Table 26. [IPDS-4-648]


Note: All necessary secondary resources must also have been previously activated and must
be identified in a prior DORE or DORE2 command. If an appropriate DORE or DORE2
equivalence entry is not found or if the secondary resource has not been activated,
exception ID X'020D..10' exists.
Byte 5 Exception reporting flags
The purpose of the RPO command is to rasterize and cache error-free presentation objects.
However, the XOA-EHC command allows some errors to be ignored as long as an Alternate
Exception Action (AEA) exists and is applied. The RPO command flags in byte 5 specify three
classes of synchronous errors that have defined AEAs and that can be ignored while
processing the RPO entry:
* Undefined-character checks (applies only to overlays) [IPDS-4-649]
* Page-position checks (applies only to overlays) [IPDS-4-650]
* All other exceptions with AEAs (applies to overlays and data object resources) [IPDS-4-651]
Bit 6 also specifies whether or not a position-check exception should be highlighted within the
resulting bit map. Support for position-check highlighting is optional; support for this function is
indicated by property pair X'F601' in the Device-Control command-set vector of an STM reply.
This bit has no meaning for a printer that does not support position-check highlighting.
These flags are identical to the exception reporting bits in the XOA-EHC command (byte 2); a
full description of each of these flags can be found. After the RPO command is
processed, the XOA-EHC command settings from before the RPO command remain in effect.
When processing an RPO entry, the current XOA-EHC command is ignored and the
processing is done assuming that AEAs are to be taken and that the RPO exception reporting
flags are in effect. If an exception is encountered for which an AEA is defined and the
appropriate reporting flag is set to B'1': the rasterization process ends, the object is not
cached, and the exception is not reported. If an exception is encountered for which an AEA is
defined and the appropriate reporting flag is set to B'0': the AEA is applied, the printer
remembers that an exception of this type was encountered, and the rasterization process
continues.
Later, when processing an IO or IDO command, the printer uses the reporting flags from the
RPO command (among other things) to determine whether or not a cached variation of the
object can be used. If there were no exceptions detected within the cached object or if an
exception was detected and the appropriate reporting flag in the RPO and in the current XOH-
EHC command is B'0', the cached object is a candidate to be used. Also, if the current XOH-
EHC command specifies that AEAs are not to be taken or if the position-check highlight flag in
the XOA-EHC and RPO commands do not match, then only error free cached objects can be
used.


Byte 6 Process flags
Bits 0–3 Desired orientation(s)
This 4-bit field specifies one or more desired orientations, measured in a clockwise direction, of the $X$ axis of the object with respect to the leading edge of the media. Each bit that is set to B'1' causes a variation of the object to be rasterized and cached. For example, X'A0' yields a 0 degree variation and a 180 degree variation. If all 4 flags are B'0', this entry is processed at 0 degrees. [IPDS-4-652]

**Table 27. RPO Orientation Flags** [IPDS-4-653]

| Bit | Orientation |
| :--- | :--- |
| bit 0 | 0 degrees [IPDS-4-654]|
| bit 1 | 90 degrees [IPDS-4-655]|
| bit 2 | 180 degrees [IPDS-4-656]|
| bit 3 | 270 degrees [IPDS-4-657]|

Bit 4 Preprocess all objects within a multi-page resource object
This flag is used only with multi-page resource objects and is ignored for all
other object types.
B'0' Preprocess only the selected paginated object. A single paginated
object can be selected with an Object Offset (X'5A') triplet.
B'1' Preprocess all paginated objects in the file. When this flag is set, all
Object Offset (X'5A') triplets are ignored.
Bits 5-7 Reserved
Bytes 7–18 Data-object-resource override parameters
The following parameters are used only for data object resources and are ignored if specified
for an overlay. Refer to “Processing Rules” for a description of how these
parameters are used to process the RPO entry.
Note: To ensure that a cached object is used for a subsequent IDO command, the data-
object-resource override parameters specified in the RPO command should match
those of the IDO command. When the units of measure values are different in the RPO
and IDO commands, the printer might calculate the sizes and offsets differently when
processing the two commands and, due to round-off errors, might not use the cached
object when processing the IDO command. Also, the printer might not be able to use
the cached object if the IDO command specifies an offset value of X'FFFF' (use the
offset value from the object), so it is best to specify the appropriate offset values in both
the IDO and RPO commands.
Byte 7 Unit base
This field specifies the unit base to be used to interpret bytes 10–13 and bytes
15–18 of the RPO entry. A value of X'00' indicates that the unit base is ten
inches. A value of X'01' indicates that the unit base is ten centimeters.
Property pair X'FB00' in the Device-Control command-set vector of an STM
reply indicates support for all architected units of measure.
X'FF' is a special value that indicates that this parameter has not been
specified. Since a unit base value is required to interpret bytes 10–13 and
bytes 15–18, a value of X'FF' in byte 7 causes the printer to ignore bytes 10–
13 and bytes 15–18 and these bytes are also treated as unspecified. [IPDS-4-658]
If an invalid or unsupported value is specified, exception ID X'0257..05' exists.
Bytes 8–9 $X_{oa}$ and $Y_{oa}$ units per unit base
This field specifies the units per unit base to be used to interpret bytes 10–13 and bytes 15–18 of the RPO entry. For example, if the unit base is X'00' and this value is X'3840', there are 14,400 units per ten inches (1440 units per inch); in this case, the measurement units are called twips. X'FFFF' is a special value that indicates that this parameter has not been specified. Since a units-per-unit-base value is required to interpret bytes 10–13 and bytes 15–18, a value of X'FFFF' in bytes 8–9 causes the printer to ignore bytes 10–13 and bytes 15–18 and these bytes are also treated as unspecified.
If an invalid or unsupported value is specified, exception ID X'0257..06' exists.
Bytes 10–13 Override for $X_{oa}$ and $Y_{oa}$ extents of the object area
These fields specify overrides for the $X_{oa}$ and $Y_{oa}$ extents of the object area in L-units using the units of measure specified in bytes 7–9. X'FFFF' is a special value that indicates that the parameter has not been specified. For these parameters to be used, both the $X_{oa}$ extent and the $Y_{oa}$ extent must be specified in the RPO entry. [IPDS-4-659]

**Table 28. RPO Object-Area Size Overrides** [IPDS-4-660]

| Bytes | Parameter | Range | Meaning |
| :--- | :--- | :--- | :--- |
| 10–11 | $X_{oa}$ extent | X'0001'–X'7FFF' | Override for $X_{oa}$ extent of object area [IPDS-4-661]|
| | | X'FFFF' | Not specified [IPDS-4-662]|
| 12–13 | $Y_{oa}$ extent | X'0001'–X'7FFF' | Override for $Y_{oa}$ extent of object area [IPDS-4-663]|
| | | X'FFFF' | Not specified [IPDS-4-664]|

If an invalid or unsupported value is specified, exception ID X'0257..07' exists.
Byte 14 Override for mapping control option
This field specifies an override for the mapping control option that selects how the object's presentation space is mapped to the output area. Resolution correction occurs whenever the resolution of the object is different in one or both dimensions from the device resolution. X'FF' is a special value that indicates that this parameter has not been specified. [IPDS-4-665]

**Table 29. RPO Mapping-Control Overrides** [IPDS-4-666]

| Code | Meaning |
| :---: | :--- |
| X'00' | Position (not valid for IO Image) [IPDS-4-667]|
| X'10' | Scale to fit [IPDS-4-668]|
| X'20' | Center and trim [IPDS-4-669]|
| X'30' | Position and trim [IPDS-4-670]|
| X'60' | Scale to fill [IPDS-4-671]|
| X'FF' | Not specified [IPDS-4-672]|

If an invalid or unsupported value is specified, exception ID X'0257..08' exists.
Refer to “Mapping Control Options” or “Mapping the IO-Image
Presentation Space” for a description of these mapping control
options.
The scale to fill option is supported in the RPO command only when it is
supported for the object type; support for scale to fill is indicated by property
pair X'F301' in the IO-Image and Object Container command-set vector of an
STM reply.
Bytes 15–18 Override for $X_{oa}$ and $Y_{oa}$ offsets from object area origin
These fields specify overrides in L-units for the $X_{oa}$ and $Y_{oa}$ offsets from the object area origin. The units of measure used to interpret these offsets are specified in bytes 7–9. The offset fields are ignored when the actual mapping option used is not position or position and trim. X'FFFF' is a special value that indicates that this parameter has not been specified; an offset of -1 cannot be specified. For these parameters to be used, all four of the object area parameters ($X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, $Y_{oa}$ offset) must be specified in the RPO entry. [IPDS-4-673]

| Bytes | Parameter | Range | Meaning |
| :--- | :--- | :--- | :--- |
| 15–16 | $X_{oa}$ offset | X'8000'–X'7FFF' | Override for $X_{oa}$ offset [IPDS-4-674]|
| | | X'FFFF' | Not specified [IPDS-4-675]|
| 17–18 | $Y_{oa}$ offset | X'8000'–X'7FFF' | Override for $Y_{oa}$ offset [IPDS-4-676]|
| | | X'FFFF' | Not specified [IPDS-4-677]|
If an unsupported value is specified, exception ID X'0257..09' exists.
Bytes 19 to
end of entry
RPO Triplets
This portion of the RPO command contains zero or more triplets that contain information about
the object.
Printers ignore any triplet that is not supported or not applicable and no exception is reported.
If byte 19 or the first byte after a valid triplet is X'00' or X'01' (an invalid triplet length), the
printer ignores the remaining data within the optional triplets field.
Specific triplets are fully described in the triplets chapter:
“Color Specification (X'4E') Triplet”
“Object Offset (X'5A') Triplet”
“Invoke CMR (X'92') Triplet”
“Rendering Intent (X'95') Triplet”
“Image Resolution (X'9A') Triplet”
“Object Container Presentation Space Size (X'9C') Triplet”
Color Specification (X'4E') Triplet Considerations
The Color Specification (X'4E') triplet specifies an override color value for a bilevel or grayscale image object to
be preRIPped; this triplet should be specified on the RPO command to match the corresponding triplet (if any)
on the IDO-DODD command. The triplet is used only for bilevel IO-Image objects and for object-container
objects that contain bilevel or grayscale image, and is ignored for all other object types. The specific object
types that can contain bilevel or grayscale image are identified in the MO:DCA object-type OID registry.
Only one Color Specification (X'4E') triplet should be specified in the RPO command. If multiple Color
Specification (X'4E') triplets are specified, the last one is used and the others are ignored. If a Color
Specification (X'4E') triplet is not specified on the RPO command, the printer uses the color specification, if
any, from the WOCC or WIC2 command.
* For bilevel IO-Image objects: [IPDS-4-678]
  - Printers that support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use the Color [IPDS-4-679]
Specification (X'4E') triplet to override either the Set Bilevel Image Color (X'F6') IOCA self-defining field or
the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field in an IO-Image object, or to set the
image color if neither self-defining field is specified in the object. Property pair X'4401' in the STM IO-Image
command-set vector indicates that the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field is
supported.
  - Printers that do not support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use a [IPDS-4-680]
restricted form of the X'4E' triplet to override the Set Bilevel Image Color (X'F6') IOCA self-defining field in
an IO-Image object, or to set the image color if no X'F6' IOCA self-defining field is specified in the object. In
this case, the triplet must specify the Standard OCA color space (X'40') or the triplet is ignored. [IPDS-4-681]


* The Color Specification (X'4E') triplet is not used with grayscale IO-Image objects. [IPDS-4-682]
* For object-container objects that contain bilevel image but do not specify an internal color value, the Color [IPDS-4-683]
Specification (X'4E') triplet specifies the color of the bilevel image. Note that 1-bit indexed color is considered
to be bilevel. Some presentation object containers can specify color by constructs within the object and, if
present, these color specifications are used instead of the Color Specification (X'4E') triplet. The color
specified in the Color Specification (X'4E') triplet is a default color and does not override a non-default color
specified within the object data.
* For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a [IPDS-4-684]
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
Property pair X'4403' in the STM IO-Image command-set vector indicates that the printer provides bilevel color
support on the RPO command for IO Images. Property pair X'5801' in the STM Object Container command-set
vector indicates that the printer provides bilevel and grayscale image color support for object containers.
Object Offset (X'5A') Triplet Considerations
When processing a multi-page resource object (such as a multi-page PDF file or multi-image TIFF file), the
printer uses the preprocess-all-objects flag (byte 6, bit 4) to determine which part of the multi-part object to
process. If the flag is B'1', all paginated objects within the file are preRIPed and the printer keeps track of the
order of these objects within the file (in this case all X'5A' triplets are ignored). If the flag is B'0', the printer uses
an Object Offset (X'5A') triplet, to identify a single paginated object within the file to preRIP. The Object Offset
(X'5A') triplet is only used with multi-page objects; the triplet is ignored if specified with any other object type.
An Object Offset (X'5A') triplet specified in the RPO command overrides Object Offset (X'5A') triplets in the
WOCC command. If more than one Object Offset (X'5A') triplet is specified in the RPO command, the last one
is used and the others are ignored. If there are no Object Offset (X'5A') triplets specified in the RPO command,
the last specified Object Offset (X'5A') triplet in the WOCC command is used. However, if no X'5A' triplets have
been specified in either command, the first paginated object within the file is preRIPed.
Printer support for the Object Offset (X'5A') triplet is indicated by the presence of at least one multi-page-file or
multi-image-file resource object OID in the Object-Container Type Support self-defining field of an XOH-OPC
reply.
Invoke CMR (X'92') and Rendering Intent (X'95') Triplet Considerations
Since data-object-level CMR invocations and rendering intent are not saved with a data object resource, they
can be provided on the IDO and RPO commands for a presentation data object. However, because overlay-
level CMRs and rendering intent are saved with the LPD command for an overlay, there is no need to specify
them on the RPO command; all X'92' and X'95' triplets specified on an RPO command for an overlay are [IPDS-4-685]


ignored. To ensure that an overlay is rendered consistently regardless of how and where the overlay is
included, appropriate CMRs and rendering intents should be contained within the overlay or in the overlay's
LPD command. Note that home-state-level CMRs and rendering intent (or device defaults) are used when
preRIPping an overlay if none are specified on the overlay's LPD command and none are specified on the data
objects within the overlay.
The Invoke CMR (X'92') and Rendering Intent (X'95') triplets are associated only with the presentation object to
be preRIPped and are used according to the CMR-usage hierarchy. Refer to “CMR-Usage Hierarchy”
35 for a description of the hierarchy. [IPDS-4-686]
Multiple Invoke CMR (X'92') triplets can be specified. However, only the last specified Rendering Intent (X'95')
triplet is used and additional X'95' triplets are ignored.
The X'F205' property pair in the Device-Control command-set vector of an STM reply indicates support for
Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the RPO command.
Image Resolution (X'9A') Triplet Considerations
Property pair X'5800' in the Object Container command-set vector of an STM reply indicates that the printer
supports the Image Resolution (X'9A') triplet. Property pair X'FB00' in the Device-Control command-set vector
of an STM reply indicates support for all architected units of measure in the Image Resolution (X'9A') triplet.
One Image Resolution (X'9A') triplet can be specified for some object types (such as TIFF , GIF , JPEG, and
JPEG2000) to identify the raster image resolution used within the object. This triplet is not used with IOCA
images or overlays and, if specified for an IOCA image or overlay, the triplet is ignored. If more than one Image
Resolution (X'9A') triplet is specified, the last one is used and the others are ignored.
If an image resolution is needed to process the object and the triplet is specified, the resolution specified within
the triplet is used and overrides the resolution inside the image, if any. However, if an image resolution is not
needed to process the object, the triplet is ignored. If an image resolution is needed to process the object, but
is not provided at all, the printer assumes that the image resolution is the same as the device resolution and
exception ID X'020D..07' exists.
If no Image Resolution (X'9A') triplets are specified on the RPO command, the printer uses the X'9A' triplet, if
any, from the WOCC command.
Object Container Presentation Space Size (X'9C') Triplet Considerations
The Object Container Presentation Space Size (X'9C') triplet specifies the presentation space size for an
included PDF or SVG object; this triplet should be specified on the RPO command to match the corresponding
triplet (if any) on the IDO command.
Only one Object Container Presentation Space Size (X'9C') triplet should be specified for a PDF or SVG
object. This triplet is not used with other object types and, if specified for an object type other than PDF or
SVG, the triplet is ignored. If multiple Object Container Presentation Space Size (X'9C') triplets are specified,
the last one is used and the others are ignored.
If an Object Container Presentation Space Size (X'9C') triplet is not specified on the RPO command, the printer
uses the X'9C' triplet, if any, from the WOCC command.
Property pair X'1203' in the Object Container command-set vector of an STM reply indicates that the printer
supports the Object Container Presentation Space Size (X'9C') triplet for a PDF object. STM property pair
X'1209' in the Object Container command-set vector indicates that the printer supports the Object Container
Presentation Space Size (X'9C') triplet for an SVG object. [IPDS-4-687]


Sense Type and Model
Length X'D6E4' Flag CID
The length of the STM command can be:
Without CID X'0005'
With CID X'0007'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Sense Type and Model (STM) command requests the printer to respond with device-dependent
information that identifies the printer and its capabilities. The printer returns this information to the host in the
special data area of one or more Acknowledge Replies to the STM command. No data is transmitted with this
command.
The host-to-printer configuration might include IPDS intermediate devices. These devices modify the printer's
STM reply in order to indicate their presence and functional characteristics to the host.
This command is valid in any printer state and performs no operation if the ARQ bit in the flag byte is B'0'.
Acknowledge Reply for Sense Type and Model
The STM data returned in the special data area of one or more Acknowledge Replies contains six bytes of
general information, followed by one or more command-set vectors. These command-set vectors identify the
IPDS command sets and subsets supported by the printer. For example, the STM data might consist of a
Device-Control vector, a Text vector, a Loaded-Font vector for the LF1 subset, and a Loaded-Font vector for
the LF3 subset.
If a command or property is supported by a printer, the support must be indicated in the command-set vectors.
The support for commands and properties as indicated in the STM reply does not change while the printer is
online. The host assumes that the printer supports only commands and properties identified by command-set
vectors.
The command-set vectors are contained in bytes 6 to end of the STM reply data. The first six bytes of each
vector contain the length, the command-set ID, and the data-level ID or subset ID of a command set.
Subsequent bytes contain two-byte pairs that specify individual properties. The first byte of each pair is the
property ID; the second byte contains the property data. The command-set vectors and property pairs may be
entered in any order.
Note: Byte numbering is relative; the actual byte number depends on the arbitrarily-assigned position of the
command-set vector within the special data area of the ACK.
Command-set vectors must include a minimum of six bytes. A command-set vector implies that the printer
supports all mandatory commands and orders for a subset of that command-set. Unrecognized property IDs
may be ignored.
A printer may return multiple property IDs within a command-set vector. If duplicate property IDs are
encountered whose data bytes are bit mapped, the last encountered property ID and data pair specifies the
property. Also, a printer may return more than one instance of a particular command-set vector. Property ID
and data pairs within subsequent instances of these command-set vectors should be interpreted as if they
were found at the end of the preceding instance of that command-set vector. [IPDS-4-688]


All intermediate devices in the host-to-printer configuration must individually indicate their presence by
generating the “intermediate device present” property pair in the Device-Control command-set vector and may
optionally provide more detailed identification information by generating the IPDS Intermediate Device
Identifier parameter in the XOH-OPC reply.
Length X'D6FF' Flag CID Type—Page and Copy Counters—Special Data Area
The following table shows the STM reply format contained in the special data area of the Acknowledge Reply: [IPDS-4-689]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | | X'FF' | System/370 convention [IPDS-4-690]|
| 1–2 | CODE | Type | | Device type of the printer, or of the printer that is being emulated or mimicked. For example, X'3820' for the 3820 page printer. [IPDS-4-691]|
| 3 | CODE | Model | | Model number; see your printer documentation [IPDS-4-692]|
| 4–5 | | Reserved | X'0000' | Reserved [IPDS-4-693]|

One or more command-set vectors in the following format. Refer to individual command-set vectors on the
following pages.

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | | Length of the command-set vector, including this length field [IPDS-4-694]|
| 8–9 | CODE | Subset ID or command set ID | | For data command sets, the subset ID of a command set. For other command sets, the command set ID. [IPDS-4-695]|
| 10–11 | CODE | Level or subset ID | | For data command sets, the level ID of a data tower. For other command sets, the subset ID of a command set. [IPDS-4-696]|
| 12 to end | CODE | Property pairs | | Zero or more command-set property ID and data pairs. [IPDS-4-697]|
The following command-set vectors can be returned in the STM Acknowledge Reply: [IPDS-4-698]


## Device-Control Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector (even values) | Length of the Device-Control command-set vector, including this length field [IPDS-4-699]|
| 8–9 | CODE | Command set ID | X'C4C3' | Device-Control command-set ID [IPDS-4-700]|
| 10–11 | CODE | Subset ID | X'FF10' | DC1 subset ID [IPDS-4-701]|
| 12 to end of vector | CODE | Command specific property pairs | X'6001'<br>X'6002'<br>X'6003'<br>X'6004'<br>X'6005'<br>X'6006'<br>X'6007'<br>X'6008'<br>X'6009'<br>X'6101'<br>X'6201' | Multiple copy & copy-subgroup support in LCC<br>Media-source-selection support in LCC; see note 1<br>Media-destination-selection support in LCC; see note 1<br>Full-length LCC commands supported; see note 2<br>Full range of font local IDs supported in LFE and LPD commands; see note 3<br>Font-modification flags supported in LFE commands (byte 14, bits 3–7)<br>Short LPD commands supported; see note 4<br>Full range of logical-page-offset values supported in LPP commands; see note 5<br>Empty LFE commands supported; see note 6<br>Explicit page placement and orientation support in the LPP command<br>Logical page and object area coloring support; see note 7 [IPDS-4-702]|


| Offset | Type | Name | Range | Meaning [IPDS-4-703]|
| :--- | :--- | :--- | :--- | :--- |
| | | Optional command property pairs | X'7001'<br>X'7002'<br>X'7008'<br>X'700A'<br>X'701C'<br>X'702E'<br>X'7034'<br>X'706B'<br>X'707B'<br>X'707E'<br>X'70CE' | Manage IPDS Dialog (MID) command support<br>Apply Finishing Operations (AFO) command<br>Set Presentation Environment (SPE) command support; see note 8<br>Activate Setup Name (ASN) command support; see note 9<br>Retired item 134<br>Activate Resource command support: indicates that the printer supports the AR command as well as XOA-RRL queries of query type X'05', activation query<br>Presentation Fidelity Control command support<br>Invoke CMR (ICMR) command support; see note 8<br>Rasterize Presentation Object (RPO) command support; see note 10<br>Include Saved Page (ISP) command support<br>DUA command-support property ID. If the DUA command and the Overlay command set is supported, secure overlays are also supported in the IO command. [IPDS-4-704]|
| | | XOA property pairs | X'8000'<br>X'8001'<br>X'8002'<br>X'8006'<br>X'8008'<br>X'800A'<br>X'800C'<br>X'8010'<br>X'80F1'<br>X'80F2'<br>X'80F3'<br>X'80F4'<br>X'80F5'<br>X'80F6'<br>X'80F8'<br>X'80F9'<br>X'80FA' | Retired item 108<br>Retired item 109<br>Retired item 110<br>Retired item 111<br>Mark Form<br>Alternate Offset Stacker<br>Control Edge Marks<br>Activate Printer Alarm<br>Retired item 112<br>Discard Buffered Data; see note 11<br>Retired item 113<br>Request Resource List; see note 11<br>Discard Unstacked Pages<br>Exception-Handling Control; see note 11<br>Print-Quality Control<br>Obtain Additional Exception Information<br>Request Setup Name List; see note 9 [IPDS-4-705]|


| | | XOH property pairs | X'9000'<br>X'9001'<br>X'9002'<br>X'9003'<br>X'9004'<br>X'9005'<br>X'9007'<br>X'9009'<br>X'900A'<br>X'900B'<br>X'900D'<br>X'900E'<br>X'9013'<br>X'9015'<br>X'9016'<br>X'9017'<br>X'90D0'<br>X'90F2'<br>X'90F3'<br>X'90F4'<br>X'90F5' | Retired item 114<br>Print Buffered Data; see note 11<br>Deactivate Saved Page Group<br>Specify Group Operation<br>Define Group Boundary<br>Erase Residual Print Data<br>Erase Residual Font Data<br>Separate Continuous Forms<br>Remove Saved Page Group<br>Retired item 115<br>Stack Received Pages<br>Select Medium Modifications<br>Eject to Front Facing<br>Select Input Media Source<br>Set Media Origin<br>Set Media Size<br>Retired item 126<br>Trace<br>Obtain Printer Characteristics; see note 11<br>Retired item 116<br>Page Counters Control [IPDS-4-706]|
| | | CMR property pairs | X'E000'<br>X'E001'<br>X'E002'<br>X'E003'<br>X'E004'<br>X'E006'<br>X'E100'<br>X'E102' | CMRs can be captured<br>Host-activated link color-conversion (subset “LK”) CMRs supported; see note 12<br>Host-activated, non-generic halftone CMRs supported; see note 13<br>Host-activated, non-generic tone-transfer-curve CMRs supported; see note 14<br>Host-activated indexed CMRs supported; see note 15<br>Host-activated ICC DeviceLink (subset “DL”) CMRs supported; see note 12<br>CMRs can be reliably applied to all EPS/PDF objects; see note 16<br>Pass-through audit color-conversion CMRs supported [IPDS-4-707]|
| | | Miscellaneous property pairs | X'F001'<br>X'F002'<br>X'F003'<br>X'F004'<br>X'F005'<br>X'F100' | End Persistent NACK without leaving IPDS mode; see note 17<br>Blank sheets are emitted when paper movement is stopped; see note 18<br>Long ACK support (up to 65,535 byte long Acknowledge Replies)<br>Grayscale simulation supported; see note 19<br>Grayscale simulation supported for device-default-monochrome device appearance; see note 20<br>An IPDS intermediate device is present. An instance of this property pair must be generated by each intermediate device in the configuration. [IPDS-4-708]|
| | | | X'F101'<br>X'F102'<br>X'F200'<br>X'F201'<br>X'F202'<br>X'F203'<br>X'F204'<br>X'F205'<br>X'F206'<br>X'F209'<br>X'F211'<br>X'F212'<br>X'F401'<br>X'F402'<br>X'F403'<br>X'F601'<br>X'F602'<br>X'F603'<br>X'F604'<br>X'F605'<br>X'F7nn'<br>X'F8nn'<br>X'F902' | UP3I finishing supported; see note 21<br>Media feed direction returned in the XOH-OPC reply Printable-Area self-defining field; see note 22<br>Local Date and Time Stamp (X'62') triplets supported in AR commands<br>Activation-failed NACK support<br>Font Resolution and Metric Technology (X'84') triplets supported in AR commands<br>Metric Adjustment (X'79') triplets supported in AR commands<br>Data-object font support; see note 23<br>Color Management triplet support in IDO, LPD, RPO, SPE, WBCC, WGC, WIC2, WOCC, and WTC commands; see note 8<br>Device Appearance (X'97') triplet support; see note 8<br>Extended copy set number format supported in the Group Information (X'6E') triplet<br>Character-encoded object names in AR commands; see note 24<br>QR Code with Image tertiary resource support; see note 25<br>XOA-RRL Multiple Entry Query Support; the printer supports multiple-entry queries of query type X'05', activation query<br>Retired (for “XOA-RRL query type X'FE' supported”)<br>Detailed settings support in XOA RSNL<br>Position-Check Highlighting Support in XOA EHC<br>Independent Exception Page-Print in XOA EHC; see note 26<br>Support for operator-directed recovery in XOA EHC; see note 27<br>Support for Page-Continuation Actions; see note 28<br>Support for Skip-and-Continue Actions; see note 28<br>Simplex N-up supported in the LCC command; see note 29<br>Simplex and duplex N-up supported in the LCC command; see note 29<br>Basic cut-sheet emulation mode supported; see note 30 [IPDS-4-709]|
| | | | X'FA00'<br>X'FB00'<br>X'FC00'<br>X'FC01'<br>X'FF01'<br>X'FF02'<br>X'FF03' | XOH PCC X'02' counter update support; property pair X'90F5' must also be specified<br>All architected units of measure supported; see note 31<br>All function listed for IS/3 is supported; see description<br>All function listed for MO:DCA GA is supported; see “Additional required support for the MO:DCA GA (Graphic Arts) Function Set”; property pair X'FC00' must also be specified<br>Positioning Exception Sense Format Supported. Presence indicates support for Sense Format 1. Absence indicates support for Sense Format 7.<br>Three-Byte Sense Data Support; see note 32<br>Internal rendering intent support in XOH Trace; see note 33 [IPDS-4-710]|
Notes:
1. Printers that support either X'6002' (media-source selection in LCC) or X'6003' (media-destination [IPDS-4-711]
selection in LCC) must also support X'900D' (XOH Stack Received Pages command).
2. Property pair X'6004' indicates that the printer supports as many LCC copy subgroups and keywords as [IPDS-4-712]
will fit into the LCC command (maximum command length is X'7FFF'). Some printers limit the number of
copy subgroups and the number of keywords supported in LCC commands.
3. Property pair X'6005' indicates support for font local IDs in the range X'80'–X'FE' in LFE and LPD [IPDS-4-713]
commands. Note that support for values in the range X'00'–X'7F' is required by the DC1 subset.
4. Property pair X'6007' indicates that the printer will accept short LPD commands that do not provide all of [IPDS-4-714]
the initial text condition parameters.
5. Property pair X'6008' indicates that the printer supports the full range of logical-page-offset values [IPDS-4-715]
(X'FF8000'–X'007FFF') in LPP commands.
6. Property pair X'6009' indicates that the printer supports empty LFE commands, which can be used to reset [IPDS-4-716]
previously established mappings. Some printers require at least one font-equivalence entry.
7. Logical page and object area coloring includes support for the Color Specification (X'4E') triplet and the [IPDS-4-717]
Presentation Space Reset Mixing (X'70') triplet on the LPD, WBCC-BCOC, WGC-GOC, WIC2-IOC,
WOCC-OCOC, and WTC-TOC commands. The Presentation Space Reset Mixing (X'70') triplet is also
supported on the IDO-DOOC command.
8. Property pair X'F205' is the basic property pair to identify support for color management (as defined in the [IPDS-4-718]
compliance appendix in the Color Management Object Content Architecture Reference). In addition,
printers that support Color Management Resources must return the following STM and XOH-OPC
information:
• Property pair X'7008' (SPE command support) in the STM Device-Control command-set vector. [IPDS-4-719]
• Property pair X'706B' (ICMR command support) in the STM Device-Control command-set vector. [IPDS-4-720]
• Property pair X'F205' (Color Management triplet support) in the STM Device-Control command-set [IPDS-4-721]
vector, including support for the Color Management Resource Descriptor (X'91') triplet, the Invoke CMR [IPDS-4-722]


(X'92') triplet, the Rendering Intent (X'95') triplet, and the Fully Qualified Name (X'02') triplet (with FQN
Type X'41').
• Property pair X'F206' (Device Appearance (X'97') triplet support) in the STM Device-Control command- [IPDS-4-723]
set vector. Within the triplet, the device-default appearance (X'0000') must be supported; support for
other appearances is optional.
• Property pair X'1201' (Data-object-resource support) in the STM Object Container command-set vector. [IPDS-4-724]
• The CMR object-type OID in the home state list of the XOH-OPC Object-Container Type Support self- [IPDS-4-725]
defining field. This indicates that the printer supports all CMR object types that are required in the Color
Management Object Content Architecture Reference (for example, CC CMRs and generic HT and TTC
CMRs); refer to the compliance appendix in the Color Management Resource (CMR) Architecture
Reference for information about compliance requirements. Support for optional CMR types is indicated
by other property pairs (for example, X'E001').
• The XOH-OPC Product Identifier self-defining field with parameter ID X'0001'. [IPDS-4-726]
9. Property pairs X'700A' and X'80FA' must always be reported together and indicate support for the Activate [IPDS-4-727]
Setup Name (ASN) and XOA Request Setup Name List (RSNL) commands, as well as support for the
Setup Name (X'9E') triplet in both commands.
An IPDS printer can support setup names (with the ASN and XOA-RSNL commands), or setup IDs (with
the Printer Setup self-defining field of the XOH-OPC), or both; the two functions do not necessarily interact.
The XOH-OPC Printer Setup self-defining field is not used for setup names.
10. Property pair X'707B' indicates support for the RPO command and also indicates 1) that if the DORE [IPDS-4-728]
command is supported, DORE is supported in home state as well as in page, page segment, and overlay
states, and 2) that if the DORE2 command is supported, DORE2 is supported in home state as well as in
page, page segment, and overlay states
.
11. These property pairs are implied by support of this command set and therefore need not be returned by a [IPDS-4-729]
printer. All other property pairs that describe a supported property, must be returned by the printer.
12. Presence of X'E001' indicates that the printer uses host-activated link color-conversion (subset “LK”) [IPDS-4-730]
CMRs as well as printer-generated link color-conversion (subset “LK”) CMRs; absence of X'E001' indicates
that the printer ignores all downloaded and host-activated link color-conversion (subset “LK”) CMRs. When
a CMR is ignored, the printer must accept it but does not error check the contents and does not select that
CMR while processing the CMR-Usage hierarchy; no exception exists when the CMR is activated,
deactivated, or invoked. Link color-conversion CMRs of type “LK” (CMR subset ID X'01' or X'02') should
not be downloaded unless this property pair is present.
Presence of X'E006' indicates that the printer uses host-activated ICC DeviceLink (subset “DL”) CMRs;
absence of X'E006' indicates that the printer ignores all downloaded and host-activated ICC DeviceLink
(subset “DL”) CMRs. When a CMR is ignored, the printer must accept it but does not error check the
contents and does not select that CMR while processing the CMR-Usage hierarchy; no exception exists
when the CMR is activated, deactivated, or invoked. Link color-conversion CMRs of type “DL” (CMR
subset ID X'03') should not be downloaded unless this property pair is present.
13. Presence of X'E002' indicates that the printer uses host-activated halftone CMRs; absence of X'E002' [IPDS-4-731]
indicates that the printer ignores all non-generic, downloaded, and host-activated halftone CMRs. When a
CMR is ignored, the printer must accept it but does not error check the contents and does not select that
CMR while processing the CMR-Usage hierarchy; no exception exists when the CMR is activated,
deactivated, or invoked. The Color Management Object Content Architecture Reference also specifies that
audit halftone CMRs are informational and can be ignored by printers. Non-generic halftone CMRs should
not be downloaded, activated, or invoked unless this property pair is present. Support for generic halftone
CMRs is indicated by support for the CMR object-type OID.
Some IPDS printers do not support host-activated CMRs under all circumstances. If a CMR is invoked and
selected for use, but the printer does not use the CMR, exception ID X'025E..05' exists. Reporting of this
exception ID can be controlled with the Color Fidelity (X'75') triplet in the PFC command. [IPDS-4-732]


14. Presence of X'E003' indicates that the printer uses host-activated tone-transfer-curve CMRs; absence of [IPDS-4-733]
X'E003' indicates that the printer ignores all non-generic, downloaded, and host-activated tone-transfer-
curve CMRs. When a CMR is ignored, the printer must accept it but does not error check the contents and
does not select that CMR while processing the CMR-Usage hierarchy; no exception exists when the CMR
is activated, deactivated, or invoked. Non-generic tone-transfer-curve CMRs should not be downloaded,
activated, or invoked unless this property pair is present. Support for generic tone-transfer-curve CMRs is
indicated by support for the CMR object-type OID.
Some IPDS printers do not support host-activated CMRs under all circumstances. If a CMR is invoked and
selected for use, but the printer does not use the CMR, exception ID X'025E..05' exists. Reporting of this
exception ID can be controlled with the Color Fidelity (X'75') triplet in the PFC command.
15. Presence of X'E004' indicates that the printer uses host-activated indexed CMRs; absence of X'E004' [IPDS-4-734]
indicates that the printer ignores all downloaded and host-activated indexed CMRs and does not perform
color management for highlight colors. When a CMR is ignored, the printer must accept it but does not
error check the contents and does not select that CMR while processing the CMR-Usage hierarchy; no
exception exists when the CMR is activated, deactivated, or invoked. Indexed CMRs should not be
downloaded, activated, or invoked unless this property pair is present.
16. Presence of X'E100' indicates that the printer reliably applies CMRs to EPS and PDF page objects. [IPDS-4-735]
Printers normally require tight control over the PostScript RIP to guarantee reliable CMR processing with
EPS and PDF page objects.
If this property pair is not returned, there are some situations where the PostScript data is constructed such
that it overrides the intent of a CMR. In this case, it is recommended that the PostScript data be converted
into another type of data (such as IOCA FS45) before it is sent to the printer.
17. “End Persistent NACK without leaving IPDS mode” indicates that the printer stops persisting with a given [IPDS-4-736]
NACK upon receipt of an Only-In-Chain IPDS command of length less than 256 bytes with ARQ bit set.
Persistent NACKs are an NDS attachment, DSC Mode only function.
18. This printer cannot immediately stop paper movement on a sheet boundary and emits a few blank sheets [IPDS-4-737]
each time the paper path is stopped.
To minimize unwanted blank sheets within a print job, the presentation services software should activate
any large or complex resources needed by the job before sending any of the pages for that job (including
header pages). In addition, the presentation services software should avoid long pauses or stops within
groups of pages that should be kept together. The Keep-Group-Together-as-a-Recovery-Unit group
operation can be used with some IPDS printers to identify such page groups to the printer so that the
printer can use the group boundaries as an appropriate stopping point or recovery point.
When cut-sheet emulation mode is in effect and no further jobs are waiting to print, to avoid blank sheets
before the last sheet of a job, the presentation services program should force a sheet eject after the last
page has been sent to the printer. This can be done with an XOH Stack Received Pages command or an
XOH Eject to Front Facing command. A sheet eject can also be forced with an LCC command that either
disables CSE (X'C300' not present), specifies N-up (X'C2nn'), or specifies more than one identical copy,
followed by a page (such as a trailer page).
19. Property pair X'F004' indicates that the printer is using a single color for printing, but will simulate color [IPDS-4-738]
values with an appropriate grayscale value. Single-color printers that convert all color values to 100% of
the supported color do not return this property pair.
20. Property pair X'F005' indicates that when a full-color printer is using a single color for printing because an [IPDS-4-739]
SPE command has selected a device appearance of device-default monochrome appearance, color
values will be simulated with an appropriate grayscale value. A single-color printer might return property
pair X'F004' (but not X'F005'); a full-color printer might return property pair X'F005' if it also supports the
device-default-monochrome device appearance (but will not return property pair X'F004').
21. Property pair X'F101' indicates that the UP [IPDS-4-740]
3I Finishing Operation (X'8E') triplet, the XOA Discard
Unstacked Pages command, sense-data format 8, and UP3I-specific exception IDs (X'nn7E..00') are
supported. In addition, when the UP3I interface is enabled in the printer, the XOH-OPC reply contains UP3I
Tupel self-defining fields (X'0019') and UP3I Paper Input Media self-defining fields (X'001A'). [IPDS-4-741]


Support for the UP3I Print Data object (to be printed by a pre-processing or post-processing device) is
indicated in the XOH-OPC Object-Container Type Support self-defining field.
22. Presence of X'F102' indicates that bit 10 of OPC bytes 22–23 provides the media-source feed direction [IPDS-4-742]
within each Printable-Area self-defining field. For example, cut-sheet media can be either short-edge fed or
long-edge fed; wide continuous-forms media is long-edge fed and narrow continuous-forms media is short-
edge fed. Note that leading edge does not necessarily identify the top edge of the media. Refer to the
diagrams in “$X_m$,Ym Coordinate System (Medium)” for a description of the various media
orientations and feed directions.
23. The data-object font support property pair (X'F204') indicates printer support for data-object fonts; this [IPDS-4-743]
support includes XOA-RRL data-object font queries and AR command activation using the Fully Qualified
Name (X'02') triplet (with FQN type X'DE'), the Encoding Scheme ID (X'50') triplet, the Data Object Font
Descriptor (X'8B') triplet, and the Linked Font (X'8D') triplet.
This property pair also indicates support for data-object-font components, including XOA-RRL data-object-
font-component queries, data-object-font-component download using object container commands, resident
data-object-font-component activation using the AR command, and data-object-font-component
deactivation using the DDOFC and XOH-ERPD commands.
This property pair also indicates support for the following Deactivate Font command types: deactivate a
data-object font (X'60') and deactivate all data-object fonts (X'6E').
24. Property pair X'F211' indicates that, when capturing data object resources and data-object-font [IPDS-4-744]
components, the printer can take advantage of an optional object name in the AR command. For example,
the printer operator might benefit from seeing the object name on the printer console whenever the OID of
an object is displayed. The (optional) object name may be supplied as a convenient alias, but is not
intended as a reliable reference when managing captured resources. Object names might not be unique
within a printer; for example, the name “Arial” might be used for a TrueType font and also for a TIFF image
that is a picture of a dog named Arial; it is also possible that Arial has been used as the name for more than
one font. Object names are specified using the following Activate Resource triplets:
• Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name) [IPDS-4-745]
• Coded Graphic Character Set Global Identifier (X'01') triplet [IPDS-4-746]
The private object name flag in byte 11 of an AR command indicates whether or not the object name is tied
to the object itself regardless of the environment in which the object is used. Object names should be
chosen such that they are as unique as is reasonably practical.
Printers that store the optional human-readable name when capturing a resource do not compare this
name to the object name in any other AR entry. For example, if an AR command contains an object name
and allows capture, the printer might capture that resource and remember the human-readable name
along with the OID. If another AR entry is specified later that uses the same OID, the resident (captured)
resource will be activated using the OID; however, a human-readable name in the second AR entry is
ignored since this later entry does not cause a capture.
25. Presence of X'F212' indicates support for the QR Code with Image bar code type, for the Invoke Tertiary [IPDS-4-747]
Resource (X'A2') triplet with TRType=X'01' and IDType=X'01' on the WBCC-BCOC, and for the ability to
ignore Resident Color Profiles when processing secondary resource image objects of a QR Code with
Image bar code.
26. If the printer allows the host to control printing or discarding of pages that contain a reported exception [IPDS-4-748]
independently from the other XOA-EHC functions, the printer must return the “Independent Exception
Page-Print in XOA EHC” property pair. For further information, refer to the XOA-EHC command Byte 4, Bit
7 description. [IPDS-4-749]
27. The operator-directed recovery function allows the host to control reporting or suppressing of certain [IPDS-4-750]
NACKs that cause the printer to discard buffered data. For further information, refer to the XOA-EHC
command Byte 3, Bit 6.
28. Property pairs X'F604' (Page-Continuation Actions) and X'F605' (Skip-and-Continue Actions) indicate the [IPDS-4-751]
supported method of continuation after an exception condition when an AEA is not taken. Only one of [IPDS-4-752]


these property pairs should be returned by the printer; if neither is returned the printer has not identified the
supported continuation method.
29. Absence of both the X'F7nn' and the X'F8nn' property pairs indicates that only one page can be placed on [IPDS-4-753]
the front of each sheet and, if duplexing, only one page can be placed on the back of each sheet.
Presence of X'F7nn' indicates that, when simplexing, up to nn pages can be placed on each front side. If
duplexing, specifying the N-up keyword in an LCC command causes exception ID X'02C0..04' to exist. For
example, X'F704' means that the printer can print 1, 2, 3, or 4 pages on each front side.
Presence of X'F8nn' indicates that, when simplexing, up to nn pages can be placed on each front side. If
duplexing, up to two times nn pages can be placed on the sheet; when explicit page placement is also
supported (property pair X'6101') each page can be placed at any position on either the front or the back of
the sheet. However when explicit page placement is not supported, the maximum number of pages on the
front is the same as the maximum number of pages on the back. For example, X'F803' without explicit
page placement support means that the printer can print 1, 2, or 3 pages on each front side and, if
duplexing, a 4th, 5th, and 6th page on the back side.
30. Basic cut-sheet emulation mode provides support for the X'C300' keyword in the LCC command. Refer to [IPDS-4-754]
“Cut-Sheet Emulation Mode” for a description of this function.
31. Presence of X'FB00' indicates that all architected units per unit base (X'0001'–X'7FFF') and all architected [IPDS-4-755]
unit bases (X'00' and X'01') are supported in commands that supply units of measure. Commands that
supply units of measure include: DUA, IDO (DOOC, DODD), LFC, LPD, RPO, XOH SMS, WBCC (BCOC,
BCDD), WGC (GOC, GDD), WIC2 (IOC, IDD), WOCC (OCOC, OCDD), and WTC (TOC, TDD). Support for
multiple raster-font resolutions is indicated in the XOH-OPC IM-Image and Coded-Font Resolution self-
defining field.
32. A printer must return the “Three-Byte Sense Data Support” property pair if the printer returns three bytes of [IPDS-4-756]
sense data in each NACK. If this property pair is not returned, the printer must return twenty-four bytes of
sense data in each NACK.
33. Support for internal rendering intent in the XOH Trace command includes support for the X'FD' and X'FE' [IPDS-4-757]
values (when applicable) of the rendering-intent field, and the X'06' value of the RI-hierarchy field, in the
CMRs-Used trace entry. [IPDS-4-758]


## Text Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Text command-set vector, including this length field [IPDS-4-759]|
| 8–9 | CODE | Subset ID | X'D7E3' | TX1 subset of the Text command set [IPDS-4-760]|
| 10–11 | CODE | Level ID | X'FF10'<br>X'FF20'<br>X'FF30'<br>X'FF40' | PTOCA PT1 data<br>PTOCA PT2 data<br>PTOCA PT3 data<br>PTOCA PT4 data [IPDS-4-761]|
| 12 to end of vector | CODE | Property pairs | X'1000'<br>X'1001'<br>X'2001'<br>X'2002'<br>X'40nn' | Optimum performance if text data is in an ordered page<br>Unordered text supported<br>Text object support; includes support for the WTC command<br>Full range of text suppression IDs supported in LCC and LE commands; see note 1<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bit 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for text data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”. This property overrides the precision parameter in all STC control sequences.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for text data<br>**Bit 7**: Multiple-color support. Multiple-color support for text data; see note 2. [IPDS-4-762]|
| | | | X'4303'<br>X'4304'<br>X'50nn' | Support for PTOCA glyph layout controls; see note 3<br>Support of encrypted text string control sequences; see note 4<br>Multiple text-orientation support for all supported media origins, where nn is a bit-mapped byte:<br>**Bit 0**: I = 270°, B = 180°<br>**Bit 1**: I = 180°, B = 90°<br>**Bit 2**: I = 90°, B = 0°<br>**Bit 3**: I = 0°, B = 270°<br>**Bit 4**: I = 270°, B = 0°<br>**Bit 5**: I = 180°, B = 270°<br>**Bit 6**: I = 90°, B = 180°<br>**Bit 7**: I = 0°, B = 90° [IPDS-4-763]|
| | | | X'A0nn' | WTC-TAP object area orientation support property ID (see notes 5 and 6), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-764]|


Notes:
1. Property pair X'2002' indicates support for text suppression IDs in the range X'80'–X'FF' in the LCC [IPDS-4-765]
X'D1nn' keyword and text suppression IDs in the range X'0080'–X'00FF' in the Load Equivalence (LE)
command. Note that support for values in the range X'01'–X'7F' is required by the PTOCA PT1 subset.
This property pair also indicates that values in the range X'00' and X'80'–X'FF' are supported in PTOCA
BSU and ESU control sequences.
Support of suppression invocation via LCC is implied by support of PT1.
2. Multiple-color support for text data means that from the table that follows, color value X'FF07' (printer [IPDS-4-766]
default) and at least two additional color values corresponding to two distinct colors are supported for text
data and result in a reasonable rendition of the specified colors.
X'0001' or X'FF01' Blue
X'0002' or X'FF02' Red
X'0003' or X'FF03' Pink/magenta
X'0004' or X'FF04' Green
X'0005' or X'FF05' Turquoise/cyan
X'0006' or X'FF06' Yellow
X'0008' Black
X'0010' Brown
X'FF07' Printer default
3. Presence of property pair X'4303' indicates support for all of the glyph layout controls defined within [IPDS-4-767]
PTOCA including the GLC, GIR, GAR, and GOR control sequences. This property pair also indicates that
the printer will recognize and tolerate UCT control sequences as described in “PTOCA Unicode Complex
Text (UCT) Control Sequence”.
IPDS printers that support glyph layout controls are also required to support the Text Fidelity(X'86') triplet.
4. Presence of property pair X'4304' indicates that the implementation understands PTOCA encrypted text [IPDS-4-768]
strings and will perform correct exception condition processing, based on the current settings from the
EHC and Text Fidelity (X'86') triplet. The current status of decryption support on the printer can be found in
the Installed Features and Available Features self-defining fields of the XOH Obtain Printer Characteristics
(OPC) reply.
IPDS printers that report the X'4304' property pair are required to support the PTOCA SKI, SEA, and ENC
control sequences, as well as the Text Fidelity (X'86') triplet.
5. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area [IPDS-4-769]
orientation support with respect to supported I-axis orientations. In particular, for a given I-axis orientation,
O
i, that is supported by the printer, bits 0–3 carry the following implications:
• Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-770]
modulo 360 is equal to 0 degrees.
• Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-771]
modulo 360 is equal to 90 degrees.
• Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-772]
modulo 360 is equal to 180 degrees.
• Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-773]
modulo 360 is equal to 270 degrees.
6. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum [IPDS-4-774]
number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. [IPDS-4-775]


## IM-Image Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the IM-Image command-set vector, including this length field [IPDS-4-776]|
| 8–9 | CODE | Subset ID | X'C9D4' | IM1 subset of the IM-Image command set [IPDS-4-777]|
| 10–11 | CODE | Level ID | X'FF10' | IMD1 data [IPDS-4-778]|
| 12 to end of vector | CODE | Property pairs | X'1000'<br>X'1001'<br>X'40nn' | Optimum performance when IM Image is in an ordered page<br>IM-Image objects may be sent in any order<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for IM-Image data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for IM-Image data<br>**Bit 7**: Multiple-color support. Multiple-color support for IM-Image data; see note. [IPDS-4-779]|
| | | | X'A0nn' | Orientation-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree scan-line direction and 90 degree scan-line sequence direction supported in the WIC command<br>**Bit 1**: 90 degree scan-line direction and 180 degree scan-line sequence direction supported in the WIC command<br>**Bit 2**: 180 degree scan-line direction and 270 degree sequence direction supported in the WIC command<br>**Bit 3**: 270 degree scan-line direction and 0 degree scan-line sequence direction supported in the WIC command<br>**Bit 4**: Reserved<br>**Bit 5**: All four scan-line direction/scan-line sequence direction combinations supported in the WIC command<br>**Bits 6–7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-780]|


Note: Multiple-color support for IM-Image data means that from the table that follows, color value X'FF07'
(printer default) and at least two additional color values corresponding to two distinct colors are
supported for IM-Image data and result in a reasonable rendition of the specified colors.
X'0001' or X'FF01' Blue
X'0002' or X'FF02' Red
X'0003' or X'FF03' Pink/magenta
X'0004' or X'FF04' Green
X'0005' or X'FF05' Turquoise/cyan
X'0006' or X'FF06' Yellow
X'0008' Black
X'0010' Brown
X'FF07' Printer default [IPDS-4-781]


## IO-Image Command-Set Vector
A separate command-set vector is returned for each supported function set, except that a separate vector is
not required for proper subsets. For example, if a printer supports FS10 and FS45, two vectors would be
returned (one for FS10 and one for FS45). [IPDS-4-782]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the IO-Image command-set vector, including this length field [IPDS-4-783]|
| 8–9 | CODE | Subset ID | X'C9D6' | IO1 subset of the IO-Image command set [IPDS-4-784]|
| 10–11 | CODE | Level ID | X'FF10'<br>X'FF11'<br>X'FF14'<br>X'FF40'<br>X'FF42'<br>X'FF45'<br>X'FF48'<br>X'0010'<br>X'0011' | IOCA FS10 data; see note 1<br>IOCA FS11 data; implies FS10 also supported<br>IOCA FS14 data; implies FS11 also supported<br>IOCA FS40 data<br>IOCA FS42 data; implies FS40 also supported<br>IOCA FS45 data; implies FS42 also supported<br>IOCA FS48 data; implies FS45 also supported<br>Subset of IOCA FS10 data<br>Subset of IOCA FS11 data [IPDS-4-785]|
| 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1202'<br>X'1206'<br>X'1208'<br>X'20nn'<br>X'30nn'<br>X'40nn' | IO-Image objects may be sent in any order<br>IO-Image objects can be downloaded in home state as resources<br>IO-Image support for LPD extents; see note 2<br>Negative object-area positioning; see note 3<br>Retired item 15<br>Retired item 16<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for bilevel IO Images are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support for bilevel IO Images. Color of medium (also known as reset color) supported for bilevel IO Images; this color has the effect of erasing any data that is underneath the significant image points.<br>**Bit 7**: Multiple-color support for bilevel IO Images. Multiple-color support for IO-Image data; see note 4. [IPDS-4-786]|
| | | | X'4401'<br>X'4402'<br>X'4403' | Extended IOCA bilevel color support; when this property pair is present, the printer supports Set Extended Bilevel Image Color (X'F4') IOCA self-defining field on the WIC2-IDD<br>Extended IOCA Tile-Set-Color support; see note 5<br>Bilevel IO-Image color support on the RPO command [IPDS-4-787]|
| | | | X'5001'<br>X'5003'<br>X'5006'<br>X'5008'<br>X'500A'<br>X'500D'<br>X'500E'<br>X'5020'<br>X'5080'<br>X'5081'<br>X'5082'<br>X'5083'<br>X'5084' | Compression algorithm-support property IDs; see note 1:<br>X'5001': Modified ITU-TSS Modified READ Algorithm (IBM MMR)<br>X'5003': Uncompressed image<br>X'5006': Run-Length 4 Compression Algorithm<br>X'5008': ABIC (bilevel Q-coder) Compression Algorithm (ABIC)<br>X'500A': Concatenated ABIC<br>X'500D': TIFF LZW<br>X'500E': TIFF LZW with Differencing Predictor<br>X'5020': Solid Fill Rectangle<br>X'5080': ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one dimensional)<br>X'5081': ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two dimensional)<br>X'5082': ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR)<br>X'5083': ISO/ITU-TSS JPEG algorithms<br>X'5084': JBIG2 Compression Algorithm [IPDS-4-788]|
| | | | X'5101'<br>X'5204'<br>X'5308'<br>X'5501'<br>X'5505'<br>X'5506' | Bit ordering supported in the IOCA Image Encoding Parameter<br>Unpadded RIDIC recording algorithm<br>IDE size = 8 supported<br>Transparency masks<br>Multiple image content support; see note 6<br>nColor Names parameter supported [IPDS-4-789]|
| | | | X'A0nn' | WIC2–IAP object area orientation support property ID (see notes 7 and 8); where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported; see note 9.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-790]|
| | | | X'F300'<br>X'F301' | Replicate-and-trim mapping supported; see note 10.<br>Scale-to-fill mapping supported [IPDS-4-791]|


Notes:
1. When the level ID is X'0010', all of the first level (IOCA FS10) is supported except for some of the [IPDS-4-792]
compression algorithms. In this case, supported compression algorithms are identified by compression
property pairs.
When the level ID is X'FF10', all of IOCA FS10 is supported. In this case, support for uncompressed image,
IBM MMR compressed image, G4 MMR compressed image, and the RIDIC image-recording algorithm is
implied and property pairs for these algorithms are not necessary and can be omitted.
When the level ID = X'0011', all of IOCA FS11 is supported except for one or more of the following
functions:
• Compression algorithms [IPDS-4-793]
• Bit ordering [IPDS-4-794]
• Grayscale (IDE size = 4 or 8) [IPDS-4-795]
• Process color (IDE size = 24) [IPDS-4-796]
Those functions from the previous list that are supported are identified by appropriate property pairs.
When the level ID = X'FF11', all of IOCA FS11 is supported and additional property pairs itemizing
individual FS11 functions are not necessary and can be omitted.
When the level ID = X'FF14', all of IOCA FS14 is supported and additional property pairs itemizing
individual FS14 functions are not necessary and can be omitted.
When the level ID = X'FF45', all of IOCA FS45 is supported and additional property pairs itemizing
individual FS45 functions are not necessary and can be omitted.
When the level ID = X'FF48', all of IOCA FS48 is supported and additional property pairs itemizing
individual FS48 functions are not necessary and can be omitted.
2. Property pair X'1206' indicates that the value X'FFFF' (use LPD value) is supported within page segments [IPDS-4-797]
for WIC2-IOC object-area-extent fields.
3. Property pair X'1208' indicates support for negative object-area-offset values in WIC2-IOC self-defining [IPDS-4-798]
fields.
4. Multiple-color support for bilevel IO Images indicates that from the table that follows, color value X'FF07' [IPDS-4-799]
(printer default) and at least two additional color values corresponding to two distinct colors are supported
for bilevel IO-Image data and result in a reasonable rendition of the specified colors.
X'0001' or X'FF01' Blue
X'0002' or X'FF02' Red
X'0003' or X'FF03' Pink/magenta
X'0004' or X'FF04' Green
X'0005' or X'FF05' Turquoise/cyan
X'0006' or X'FF06' Yellow
X'0008' Black
X'0010' Brown
X'FF07' Printer default
5. Property pair X'4402' indicates that the printer supports the optional color spaces (RGB, highlight colors, [IPDS-4-800]
and Standard OCA colors) in the IOCA Tile Set Color Parameter (that is carried in the IPDS WI2
command). This function is not part of any function set and the property pair is only specified in the IO-
Image command-set vector for tiled function sets (FS40, FS42, FS45, and FS48
); the property pair is not
specified for other function sets (FS10, FS11, or FS14).
6. Property pair X'5505' indicates that the printer supports multiple image contents within an IOCA image [IPDS-4-801]
segment for all IOCA images. When a printer supports multiple image contents, this property pair must be
included in each returned IO-Image command-set vector. For example, a printer that supports FS10, FS42,
and FS45 would return at least two IOCA vectors: one vector for FS10 that includes the X'5505' property
pair, and one vector for FS45 that includes the X'5505' property pair. [IPDS-4-802]


7. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area [IPDS-4-803]
orientation support with respect to supported I-axis orientations. In particular, for a given I-axis orientation,
Oi, that is supported by the printer, bits 0–3 carry the following implications:
• Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-804]
modulo 360 is equal to 0 degrees.
• Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-805]
modulo 360 is equal to 90 degrees.
• Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-806]
modulo 360 is equal to 180 degrees.
• Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-807]
modulo 360 is equal to 270 degrees.
8. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum [IPDS-4-808]
number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set.
9. Bit 6 of property pair X'A0nn' indicates support for all values of degrees and minutes for IO-Image object [IPDS-4-809]
area orientation, for IO-Images presented in a page, page segment, or overlay. In addition, if a printer
supports downloading IO-Image objects in home state as resources (as reported by property pair X'1202'),
bit 6 also indicates support in the Include Data Object (IDO) command for all values of degrees and
minutes when IO-Image resources are included.
10. Property pair X'F300' indicates that the printer supports the replicate-and-trim mapping option for FS10 [IPDS-4-810]
images. This mapping option is used for migration from IM Images to IOCA FS10 images. X'F300' must not
be specified for any other IOCA function set. [IPDS-4-811]


## Graphics Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Graphics command-set vector, including this length field [IPDS-4-812]|
| 8–9 | CODE | Subset ID | X'E5C7' | GR1 subset of the Graphics command set [IPDS-4-813]|
| 10–11 | CODE | Level ID | X'FF20'<br>X'FF30' | GOCA DR/2V0 data<br>GOCA GRS3 data [IPDS-4-814]|
| 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1207'<br>X'1208'<br>X'40nn' | Graphics objects may be sent in any order<br>Support for GOCA image resolution in the WGC-GDD<br>Negative object-area positioning; see note 1<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: Reserved<br>**Bit 1**: Simulated area fill color support as an AEA. Simulated color support for all valid but unsupported color values as an AEA for graphics area fill. The simulation technique is device dependent and generates distinguishable simulated colors. This bit and bit 2 cannot both be set to B'1'. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for graphics data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. This bit and bit 1 cannot both be set to B'1'. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for graphics data<br>**Bit 7**: Multiple-color support. Multiple-color support for graphics; see note 2 [IPDS-4-815]|
| | | | X'4100'<br>X'4101'<br>X'4102'<br>X'4106'<br>X'4107'<br>X'4108'<br>X'4109'<br>X'4110'<br>X'4111'<br>X'4112'<br>X'4113'<br>X'4114'<br>X'4115'<br>X'4116'<br>X'4117'<br>X'4130'<br>X'4131'<br>X'4132' | Set Process Color drawing order supported<br>Box drawing orders supported<br>Partial Arc drawing orders supported<br>Set Fractional Line Width drawing order supported (including support between Begin Area and End Area drawing orders and in segment prologs)<br>Cubic Bézier Curve drawing orders<br>Set default support in GDD for Normal Line Width<br>Set default support in GDD for Process Color<br>Set Line End drawing order supported<br>Set Line Join drawing order supported<br>Clockwise full and partial arcs supported<br>Nonzero Winding mode supported; see note 3<br>Clockwise boxes supported<br>Custom line types supported<br>Font positioning method used for GOCA character positioning; see note 4<br>Cell positioning method used for GOCA character positioning; see note 4<br>Custom patterns supported; see note 5<br>Gradients supported for area fill; see note 6<br>Marker size supported; see note 7 [IPDS-4-816]|
| | | | X'A0nn' | WGC–GAP object area orientation support property ID (see notes 8 and 9), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-817]|
| | | | X'F300'<br>X'F301' | Retired item 135 (replicate-and-trim mapping)<br>Scale-to-fill mapping supported [IPDS-4-818]|
Notes:
1. Property pair X'1208' indicates support for negative object-area-offset values in WGC-GOC self-defining [IPDS-4-819]
fields.
2. Multiple-color support for graphics data means that from the table that follows, color value X'FF07' (printer [IPDS-4-820]
default) and at least two additional color values corresponding to two distinct colors are supported for
graphics data and result in a reasonable rendition of the specified colors.
X'0001' or X'FF01' Blue
X'0002' or X'FF02' Red [IPDS-4-821]


X'0003' or X'FF03' Pink/magenta
X'0004' or X'FF04' Green
X'0005' or X'FF05' Turquoise/cyan
X'0006' or X'FF06' Yellow
X'0008' Black
X'0010' Brown
X'FF07' Printer default
3. Property pair X'4113' indicates that the printer supports Nonzero Winding mode. Such printers must also [IPDS-4-822]
support clockwise arcs and clockwise boxes, and must therefore return property pairs X'4112' and X'4114'
in addition to X'4113'.
4. Property pairs X'4116' and X'4117' are mutually exclusive; only one of the two property pairs can be [IPDS-4-823]
reported.
5. Support for custom patterns (property pair X'4130') includes support for the Begin Custom Pattern, End [IPDS-4-824]
Custom Pattern, Delete Pattern, and Set Pattern Reference Point drawing orders.
6. Support for gradients (property pair X'4131') includes support for the Linear Gradient, Radial Gradient, and [IPDS-4-825]
Delete Pattern drawing orders.
7. Support for marker size (property pair X'4132') includes: [IPDS-4-826]
• Varying the size of markers based on the marker cell-size attribute [IPDS-4-827]
• Not processing the Set Marker Cell (GSMC) drawing order as a No-Op [IPDS-4-828]
• Treating the marker precision attribute and Set Marker Precision (GSMP) drawing order as obsolete [IPDS-4-829]
• Following the recommendation for standard default marker cell-size [IPDS-4-830]
8. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area [IPDS-4-831]
orientation support with respect to supported I-axis orientations. In particular, for a given I-axis orientation,
Oi, that is supported by the printer, bits 0–3 carry the following implications:
• Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-832]
modulo 360 is equal to 0 degrees.
• Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-833]
modulo 360 is equal to 90 degrees.
• Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-834]
modulo 360 is equal to 180 degrees.
• Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-835]
modulo 360 is equal to 270 degrees.
9. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum [IPDS-4-836]
number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. [IPDS-4-837]


## Bar Code Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Bar Code command-set vector, including this length field [IPDS-4-838]|
| 8–9 | CODE | Subset ID | X'C2C3' | BC1 subset of the Bar Code command set [IPDS-4-839]|
| 10–11 | CODE | Level ID | X'FF10'<br>X'FF20' | BCOCA BCD1 data<br>BCOCA BCD2 data [IPDS-4-840]|
| 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1208'<br>X'1300'<br>X'1301'<br>X'1302'<br>X'1303'<br>X'1304'<br>X'1305'<br>X'1306'<br>X'1307' | Bar code objects may be sent in any order<br>Negative object-area positioning; see note 1<br>Small-symbol support; see note 2<br>Retired item 139<br>Desired-symbol-width parameter supported in the Bar Code Symbol Descriptor<br>Data Matrix encodation scheme support<br>Full range of font local IDs supported in WBCC-BCDD; see note 3<br>Support for bar code suppression; see note 4<br>Support for the too-much-data flag in the QR Code Special-Function Parameters<br>Support for the too-much-data flag in the Data Matrix Special-Function Parameters; see note 5 [IPDS-4-841]|
| | | | X'40nn' | Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for bar code data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3-5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for bar code data<br>**Bit 7**: Multiple-color support for bar code data; see note 6. [IPDS-4-842]|
| | | | X'4400'<br>X'A0nn' | Extended bar code color support; when this property pair is present, the printer supports the Color Specification (X'4E') triplet on the WBCC-BCDD.<br>WBCC–BCAP object area orientation support property ID (see note 7 and note 8), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-843]|
Notes:
1. Property pair X'1208' indicates support for negative object-area-offset values in WBCC-BCOC self-defining [IPDS-4-844]
fields.
2. Printers that provide small-symbol support can produce the smallest bar code symbol defined for the fixed- [IPDS-4-845]
size bar codes; refer to the module-width parameter description in the Bar Code Object Content
Architecture Reference for more details.
3. Property pair X'1304' indicates support for font local ID X'00' and font local IDs in the range X'80'–X'FE' in [IPDS-4-846]
WBCC-BCDD commands. This property pair is used by printers that support BCD1 plus the full range of
font local IDs in the BCOCA BSD; note that BCD2 requires support for the full range (and therefore this
property pair is not needed for BCD2 or higher subsets).
4. Property pair X'1305' indicates support for the suppress-bar-code-symbol flag in the BCOCA BSA data [IPDS-4-847]
structure (carried in a WBC command). This property pair is used by printers that support BCD1 plus bar
code suppression in the BCOCA BSA; note that BCD2 requires support for the flag (and therefore this
property pair is not needed for BCD2 or higher subsets).
5. Property pair X'1307' is reported in two cases: [IPDS-4-848]
• The printer supports only Data Matrix bar codes with bar code modifier X'00' and supports the too-much- [IPDS-4-849]
data flag for such bar codes. Such support is optional.
• The printer supports Data Matrix bar codes with bar code modifiers X'00' and X'01', in which case [IPDS-4-850]
support of the too-much-data flag is mandatory for both modifiers. However, so that reporting of this
property pair can be relied on when the flag is supported for modifier X'00', the flag is reported in this
second case as well.
6. Multiple-color support for bar code data means that from the table that follows, color value X'FF07' (printer [IPDS-4-851]
default) and at least two additional color values corresponding to two distinct colors are supported for
graphics data and result in a reasonable rendition of the specified colors.
X'0001' or X'FF01' Blue
X'0002' or X'FF02' Red [IPDS-4-852]


X'0003' or X'FF03' Pink/magenta
X'0004' or X'FF04' Green
X'0005' or X'FF05' Turquoise/cyan
X'0006' or X'FF06' Yellow
X'0008' Black
X'0010' Brown
X'FF07' Printer default
7. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area [IPDS-4-853]
orientation support with respect to supported I-axis orientations. In particular, for a given I-axis orientation,
Oi, that is supported by the printer, bits 0–3 carry the following implications:
• Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-854]
modulo 360 is equal to 0 degrees.
• Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-855]
modulo 360 is equal to 90 degrees.
• Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-856]
modulo 360 is equal to 180 degrees.
• Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-857]
modulo 360 is equal to 270 degrees.
8. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum [IPDS-4-858]
number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set.
9. Printers that implement the common bar code types and modifiers that are listed in Table 34 [IPDS-4-859]
and implement one or more additional bar code types and associated modifiers that are listed in the
Common Bar Code Type/Modifier self-defining field respond to the XOH-OPC command with the
appropriately encoded Common Bar Code Type/Modifier Self-Defining Field. [IPDS-4-860]


## Object Container Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Object Container command-set vector, including this length field [IPDS-4-861]|
| 8–9 | CODE | Subset ID | X'D6C3' | OC1 subset of the Object Container command set [IPDS-4-862]|
| 10–11 | CODE | Level ID | X'0000' | No levels defined [IPDS-4-863]|
| 12 to end of vector | CODE | Property pairs | X'1201'<br>X'1203'<br>X'1204'<br>X'1205'<br>X'1208'<br>X'1209'<br>X'120A'<br>X'120B'<br>X'120D'<br>X'120E'<br>X'5800'<br>X'5801' | Data-object-resource support; see note 1<br>Object Container Presentation Space Size (X'9C') triplet supported for PDF objects in IDO, RPO, and WOCC commands<br>Remove Resident Resource (RRR) command support<br>Request Resident Resource List (RRRL) command support<br>Negative object-area positioning; see note 2<br>Object Container Presentation Space Size (X'9C') triplet supported for SVG objects in IDO, RPO, and WOCC commands<br>Extension entries supported in the DORE command; see note 3<br>Retired item 149 ; see note 4<br>TrueType/OpenType Fonts supported as secondary resources in the DORE2 command; see note 4<br>Data Object Resource Equivalence 2 (DORE2) command support; see note 5<br>Image Resolution (X'9A') triplet supported in IDO, RPO, and WOCC commands<br>Bilevel and grayscale image color support for object containers; see note 6 [IPDS-4-864]|
| | | | X'A0nn' | WOCC-OCAP and IDO-DOAP object area orientation support property ID (see notes 7, 8, and 9), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I, B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported in both the WOCC-OCAP and the IDO-DOAP.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-865]|
| | | | X'F301' | Scale-to-fill mapping supported [IPDS-4-866]|


Notes:
1. Property pair X'1201' indicates support for data object resources and includes support for the DDOR, [IPDS-4-867]
DORE, IDO, and home-state WOCC commands. The DORE command is supported in page, page
segment, and overlay states; if the RPO command is also supported (indicated by property pair X'707B' in
the Device-Control command-set vector), the DORE command is also supported in home state. Support
for data-object-font components is indicated in the Device-Control command-set vector by property pair
X'F204'.
2. Property pair X'1208' indicates support for negative object-area-offset values in IDO-DOOC and WOCC- [IPDS-4-868]
OCOC self-defining fields.
3. Property pair X'120A' indicates support for the HAID value X'E47D' in the DORE command, for all states in [IPDS-4-869]
which the DORE command is supported.
4. Property pair X'120D' [IPDS-4-870]
indicates support for mapping TrueType/OpenType Fonts in the DORE2 command,
for all presentation objects supported by the printer and for which TrueType/OpenType Fonts are valid
secondary resources.
Note: The retired property pair X'120B' (retired item 149) reported support for TrueType/OpenType fonts
as secondary resources in the DORE command; however, the DORE command cannot be used for
this purpose, since in IPDS, a TrueType/OpenType font used as a secondary resource uses a HAID
in the data-object-font-component HAID pool, but the HAIDs in the DORE command are only
searched for in the data-object-resource HAID pool. Instead, the DORE2 command must be used
for this purpose, and that functionality is reported with the X'120D' property pair.
5. Property pair X'120E' indicates support for the DORE2 command in page, page segment, and overlay [IPDS-4-871]
states; if the RPO command is also supported (indicated by property pair X'707B' in the Device-Control
command-set vector), the DORE2 command is also supported in home state.
6. Property pair X'5801' indicates support for the Color Specification (X'4E') triplet in IDO-DODD, RPO, and [IPDS-4-872]
WOCC-OCDD commands to provide a color value for object-container objects that contain bilevel or
grayscale image. The specific object types that can contain bilevel or grayscale image are identified in the
MO:DCA object-type OID registry.
7. Object area orientation with respect to the X [IPDS-4-873]
p,Yp coordinate system also implies a level of object area
orientation support with respect to supported I-axis orientations. In particular, for a given I-axis orientation,
Oi, that is supported by the printer, bits 0–3 carry the following implications:
• Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-874]
modulo 360 is equal to 0 degrees.
• Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-875]
modulo 360 is equal to 90 degrees.
• Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-876]
modulo 360 is equal to 180 degrees.
• Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) [IPDS-4-877]
modulo 360 is equal to 270 degrees.
8. Bits 0–3 and 5 report the object area orientation support for the WOCC-OCAP , since the only orientation [IPDS-4-878]
whose support is required in that self-defining field is 0 degrees. In contrast, the IDO-DOAP self-defining
field requires support of 0, 90, 180, and 270 degrees, so there is no need for a printer to report support for
those orientation values for the IDO-DOAP. Bit 6 is valid for both the WOCC-OCAP and IDO-DOAP;
printers reporting this bit must support unrestricted object area orientation for both self-defining fields.
Note, however, that bit 6 does not report support of unrestricted object area orientation for IO-Image
resources included using the IDO command; such support is instead reported using bit 6 of the X'A0nn'
property pair of the IO-Image command-set vector.
9. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum [IPDS-4-879]
number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. [IPDS-4-880]


## Metadata Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Metadata command-set vector, including this length field [IPDS-4-881]|
| 8–9 | CODE | Subset ID | X'D4C4' | MO1 subset of the Metadata command set [IPDS-4-882]|
| 10–11 | CODE | Level ID | X'FF10' | MOCA MS1 data [IPDS-4-883]|
| 12 to end of vector | CODE | Property pairs | X'D001' | Support for the AFP Tagging format [IPDS-4-884]|
## Overlay Command-Set Vector

| Offset | Type | Name | Range | Meaning [IPDS-4-885]|
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Overlay command-set vector, including this length field [IPDS-4-886]|
| 8–9 | CODE | Command set ID | X'D6D3' | Overlay command-set ID [IPDS-4-887]|
| 10–11 | CODE | Subset ID | X'FF10' | OL1 subset ID [IPDS-4-888]|
| 12 to end of vector | CODE | Property pairs | X'1102'<br>X'15nn' | Extended overlay support; up to 32,511 overlays can be activated at one time.<br>Overlay nesting support; if not reported, two levels of nesting (X'02') are assumed; see note 1. Where nn is:<br>X'01': No overlay nesting is supported. Overlays may not include other overlays.<br>X'mm': Overlay nesting up to mm levels is supported. Valid values for mm are X'02'–X'FE'.<br>X'FF': 255 or more levels of overlay nesting supported. [IPDS-4-889]|
| | | | X'1600'<br>X'A004' | Preprinted form overlay support in LCC and IO commands<br>Page-overlay-rotation support; all 4 orientations supported in the IO command [IPDS-4-890]|
Notes:
1. For example, if X'1503' is returned, the host can invoke an overlay by means of an IO or LCC command [IPDS-4-891]
that contains an IO command, resulting in two levels of nesting. The included overlay might also contain an
IO command, resulting in three levels of nesting. If this last included overlay contains an IO command, the
printer issues a X'0297..01' exception.
A printer may support more levels of overlay nesting than are reported, or assumed, in this command-set
vector.
2. Support of overlay invocation via LCC is implied by support of OL1. [IPDS-4-892]


## Page-Segment Command-Set Vector

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Page-Segment command-set vector, including this length field [IPDS-4-893]|
| 8–9 | CODE | Command set ID | X'D7E2' | Page-Segment command-set ID [IPDS-4-894]|
| 10–11 | CODE | Subset ID | X'FF10' | PS1 subset ID [IPDS-4-895]|
| 12 to end of vector | CODE | Property pairs | X'1101' | Extended page segment support; up to 32,511 page segments can be activated at one time. [IPDS-4-896]|
## Loaded-Font Command-Set Vector

| Offset | Type | Name | Range | Meaning [IPDS-4-897]|
| :--- | :--- | :--- | :--- | :--- |
| 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Loaded-Font command-set vector, including this length field [IPDS-4-898]|
| 8–9 | CODE | Command set ID | X'C3C6' | Loaded-Font command-set ID [IPDS-4-899]|
| 10–11 | CODE | Subset ID | X'FF10'<br>X'FF20'<br>X'FF30'<br>X'FF40' | LF1 subset ID; fully described font plus font index<br>LF2 subset ID; symbol set coded font<br>LF3 subset ID; code page plus font character set<br>LF4 subset ID; code page<br>There is no subset or superset relationship between the loaded-font subsets. [IPDS-4-900]|
| 12 to end of vector | CODE | Property pairs | X'A0nn' | Orientation-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 1**: 90 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 2**: 180 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 3**: 270 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 4**: Reserved<br>**Bit 5**: All four Font Inline Sequences supported for all supported text orientations in the LFI command<br>**Bits 6–7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. [IPDS-4-901]|
| | | | X'B001'<br>X'B002'<br>X'B003'<br>X'B004'<br>X'B005'<br>X'C0nn'<br>X'C1nn' | Double-byte coded fonts and code pages<br>Underscore width and position parameters in the LFI command are used by printer<br>GRID-parts fields allowed in the LFC, LFCSC, and LCPC commands<br>Default character parameters supported in the LCPC command (implies that X'B003' is also supported)<br>Extended (Unicode mapping) code page support; see note 1.<br>Coded-font pattern-technology ID (see note 2), nn is:<br>X'05': Bounded-box raster-font technology<br>X'1E': CID-keyed outline-font technology<br>X'1F': Type 1 PFB outline-font technology [IPDS-4-902]|
The Type 1 PFB and CID-keyed technologies are defined by
Adobe Systems Incorporated. The Type 1 PFB technology is
described in Adobe Type 1 Font Format published by Adobe
Systems Incorporated. The CID-keyed technology is
described in Adobe CMap and CIDFont Files Specification
published by Adobe Systems Incorporated.
Coded-font metric-technology ID, nn is:
X'00'—Fixed metrics; default value if this property pair
is not returned
X'01'—Relative metrics
See note 3.
Notes:
1. X'B005' is independent from X'B003' and X'B004'; as a couple of examples, a printer might return X'B005' [IPDS-4-903]
without the other two property pairs, or might return X'B003' and X'B005'. When X'B005' is returned,
Unicode scalar values can be specified in the LCP command. However, the default-character Unicode
scalar value in the LCPC command can only be specified when the printer returns all three property pairs.
The printer can tell which optional LCPC fields have been specified from the command length.
2. The range of values X'06'–X'FF' in property pair X'C0nn' has been set aside for outline-font pattern- [IPDS-4-904]
technology IDs. This is retired item 17; note that the values X'1E' and X'1F' have been unretired and
assigned a specific meaning.
3. The LF1 command subset supports either fixed-metric technology or relative-metric technology (or both). [IPDS-4-905]
The LF2 command subset supports only fixed-metric technology. The LF3 command subset supports
either technology, but the relative-metric technology is used with outline fonts.
The printer need not return the X'C1nn' property pair for LF2 or LF3 command subsets. [IPDS-4-906]



```
Length X'D697' Flag CID
```

The length of the SHS command can be: [IPDS-4-907]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0005' [IPDS-4-908]|
| With CID | X'0007' [IPDS-4-909]|
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Set Home State (SHS) command is valid in any printer state. When the printer receives the SHS
command in page state or any derivative of page state, the current page ends, the complete or partially
complete page continues through the print process, and the printer returns to home state. If this command is
syntactically correct, no exceptions can result from its execution.
If the printer is in any resource state, such as page-segment state, overlay state, font state, or any derivative of
these states, the partial resource is deleted before the printer returns to home state. In home state, an SHS
command is treated as a No Operation (NOP) command. [IPDS-4-910]

Set Presentation Environment (SPE) is a home state command used to set specific presentation attributes for
use on the pages that follow. For each specified triplet, the specified presentation attributes completely replace
those presentation attributes; all other previously specified presentation attributes (from other triplets) stay in
effect.
When page data is printed or when a medium overlay or preprinted form overlay is printed, the most recently
received SPE command is used as shown in the following example. Suppose that the following command
sequence is received:
SPE1
BO1 (Overlay)... EP
SPE2
LCC with medium overlays and 2-up simplex
SPE3
BP1... EP
SPE4
BP2... EP
In this example, SPE1 and SPE2 are not used. SPE1 is not saved as part of the environment stored with
overlay 1. Since medium overlays are not printed until the first page on a sheet is printed, SPE2 is not used
with the medium overlays. When page 1 is printed, SPE3 is used for all medium overlays specified within the
preceding LCC command and for all data (including any included overlays). When page 2 is printed,
SPE4 is used for all data (including any included overlays). However, note that some IPDS printers
can change device appearance only at a sheet boundary; in this case SPE3 would be used for page 2.
Support for this optional command is indicated by the X'7008' property pair in the Device-Control command-set
vector of an STM reply.
```
Length X'D608' Flag CID Data
```
The length of the SPE command can be: [IPDS-4-911]

| Condition | Length |
| :--- | :--- |
| Without CID | X'0007' or X'0009'–X'7FFF' [IPDS-4-912]|
| With CID | X'0009' or X'000B'–X'7FFF' [IPDS-4-913]|

However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported. [IPDS-4-914]

The data in an SPE command is defined as follows: [IPDS-4-915]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | X'0000' | Reserved | X'0000' | Reserved | X'0000' [IPDS-4-916]|
| 2 to end of SPE | | Triplets | | Zero or more triplets:<br>X'95' Rendering Intent triplet<br>X'97' Device Appearance triplet [IPDS-4-917]| |

**Bytes 0–1 Reserved** [IPDS-4-918]

**Bytes 2 to end of command**
**Zero or more triplets** [IPDS-4-919]


The SPE command contains zero or more triplets used to set specific presentation attributes
for use on the pages that follow. This command can be used in two different ways:
1. If no triplets are specified in the SPE command, all presentation attributes are set to the [IPDS-4-920]
printer default.
2. If one or more triplets are specified in the SPE command, each specified triplet sets the [IPDS-4-921]
presentation attributes defined by that triplet. All other previously specified presentation
attributes (from other triplets) stay in effect.
Printers ignore any triplet that is not supported on this command and no exception is reported.
If byte 0 or the first byte after a triplet is X'00' or X'01' (an invalid triplet length), exception ID
X'027A..01' exists. If the triplet is too big to fit in the containing command, exception ID
X'027B..01' exists.
Presentation Environment Triplet Considerations
The triplets are independent of each other and only one triplet of each type (as indicated by triplet ID) is used. If
multiple triplets with the same triplet ID are specified, the last one specified is used and the others are ignored.
Support for individual presentation environment triplets is indicated by the following STM property pairs in the
Device-Control command-set vector:
• X'F205'—Rendering Intent (X'95') triplet support [IPDS-4-922]
Used to establish a rendering intent for multiple pages (such as for a MO:DCA document or print file).
• X'F206'—Device Appearance (X'97') triplet support [IPDS-4-923]
Used to establish a temporary device appearance.
The Presentation Environment triplets are fully described in the triplets chapter:
“Rendering Intent (X'95') Triplet”
“Device Appearance (X'97') Triplet” [IPDS-4-924]


Execute Order Anystate
```
Length X'D633' Flag CID Data
```
The Execute Order Anystate (XOA) command identifies a set of orders that take effect immediately, regardless
of the current command state of the printer. This command is valid in any printer state.
Each XOA data field contains a two-byte order code, followed by zero or more parameter bytes for that order.
Each XOA command can contain only one order. Orders have the following format:
Code Parameters (if any)
In alphabetic sequence, the orders are: [IPDS-4-925]

**Table 28. XOA Order Summary** [IPDS-4-926]

| Code | Order | In DC1 subset? |
| :--- | :--- | :--- |
| X'1000' | XOA Activate Printer Alarm | No [IPDS-4-927]|
| X'0A00' | XOA Alternate Offset Stacker | No [IPDS-4-928]|
| X'0C00' | XOA Control Edge Marks | No [IPDS-4-929]|
| X'F200' | XOA Discard Buffered Data | Yes [IPDS-4-930]|
| X'F500' | XOA Discard Unstacked Pages | No [IPDS-4-931]|
| X'F600' | XOA Exception-Handling Control | Yes [IPDS-4-932]|
| X'0800' | XOA Mark Form | No [IPDS-4-933]|
| X'F900' | XOA Obtain Additional Exception Information | No [IPDS-4-934]|
| X'F800' | XOA Print-Quality Control | No [IPDS-4-935]|
| X'F400' | XOA Request Resource List | Yes [IPDS-4-936]|
| X'FA00' | XOA Request Setup Name List | No [IPDS-4-937]|
| **Retired Order Codes** [IPDS-4-938]| | |
| X'0000' | Retired item 18 | No [IPDS-4-939]|
| X'0001' | Retired item 19 | No [IPDS-4-940]|
| X'0002' | Retired item 20 | No [IPDS-4-941]|
| X'0200' | Retired item 21 | No [IPDS-4-942]|
| X'0600' | Retired item 22 | No [IPDS-4-943]|
| X'0700' | Retired item 141 | No [IPDS-4-944]|
| X'0900' | Retired item 142 | No [IPDS-4-945]|
| X'7BF5' | Retired item 23 | No [IPDS-4-946]|
| X'CACA' | Retired item 24 | No [IPDS-4-947]|
| X'CE00' | Retired item 143 | No [IPDS-4-948]|
| X'F100' | Retired item 25 | No [IPDS-4-949]|
| X'F300' | Retired item 26 | No [IPDS-4-950]|
Unknown or unsupported orders are treated as No Operation (NOP) commands. [IPDS-4-951]


XOA Activate Printer Alarm
The XOA Activate Printer Alarm (APA) command signals the printer to activate its alarm mechanism (for
example, a beep, bell, or light) for a device specific amount of time.
Implementation Note: This command is similar to the BEL command in ASCII and SNA Character String
(SCS) data streams.
The length of the XOA-APA command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported. [IPDS-4-952]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'1000' | Activate Printer Alarm (APA) order code | X'1000' [IPDS-4-953]|


XOA Alternate Offset Stacker
The XOA Alternate Offset Stacker (AOS) command signals the printer to jog the current sheet. If copies of the
current sheet are stacked in more than one media destination, the jogging occurs in each selected media
destination.
The length of the XOA-AOS command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Jogging causes these sheets be stacked in the stacker with a printer-determined offset from the previously
stacked sheets. The function is cumulative. Therefore, for a two-position jogger, an even number of AOS
orders for the same sheet has the same effect as no AOS order. Also, for a two-position jogger, an odd number
of AOS orders has the same effect as a single AOS order.
After a sheet and all of its copies has been jogged, subsequent sheets are stacked at the same (jogged)
position until either another XOA-AOS command is received or the printer is reinitialized (returns an IML
NACK).
If the printer is receiving a page, that is, the printer is in page state or any derivative of page state, the current
sheet is the sheet on which the first copy of the page being received is printed. If the printer is in any other
state, the current sheet is the sheet on which the first copy of the next received page is printed.
Some printers support offset stacking on some, but not all media destinations. If a media destination that does
not support offset stacking is selected, all XOA-AOS commands are ignored for that media destination.
For some printers, alternate offset stacking cannot be combined with a finishing operation. In this case, if the
XOA-AOS command conflicts with the finishing operation, the XOA-AOS command is ignored and the finishing
operation is performed. [IPDS-4-954]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0A00' | Alternate Offset Stacker (AOS) order code | X'0A00' [IPDS-4-955]|


XOA Control Edge Marks
The XOA Control Edge Marks (CEM) command causes a printer that is using continuous-forms media to mark
the front side of the current sheet and the front side of the next sheet with edge marks. The mark can optionally
be placed on the back side of the sheet.
The length of the XOA-CEM command can be:
Without CID X'0008'
With CID X'000A'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Edge marks consist of one, two, or three narrow bars as defined by the printer. These bars are printed
perpendicular to the media perforation and entirely within the left carrier strip ending at the trailing edge of the
specified sheet. A corresponding number of edge marks are also printed perpendicular to the media
perforation and entirely within the left carrier strip beginning at the leading edge of the next sheet. Refer to
**Figure 59**.
**Figure 59**. Example Showing Three Edge Marks
three edge marks
Page 45
trailing edge media perforati
Page 47
left carrier strip right carrier strip
Continuous-forms Media
This command is treated as a No Operation (NOP) command by a printer that is using either cut-sheet media
or continuous-forms media that does not have carrier strips. The XOA Control Edge Marks command can
cause printing to occur outside the user printable area.
If the printer is in page state or any derivative of page state when the CEM order is received, the edge mark is
printed on the current sheet; the current sheet is the one on which the first copy of the page being received is
printed. If the printer is in any other state, the edge mark is printed on the sheet on which the first copy of the
next received page is printed. A CEM order remains in effect for all subsequent sheets until either another
CEM order is received or the printer is reinitialized (returns an IML NACK).
In general, the number of edge marks at the top of a sheet is equal to the number of edge marks at the bottom
of the previous sheet. However, in the case where multiple identical copies is specified, the number of edge
marks at the top and bottom of each copy is identical (as if this sheet were taken to a copier and the number of
copies requested made). For example, if one edge mark is at the top of the sheet and two edge marks are at
the bottom of the sheet, all identical copies of this sheet have one edge mark at the top and two edge marks at [IPDS-4-956]


the bottom. On the other hand, in the case of multiple copy subgroups, the rule applies that the number of edge
marks at the top of a sheet must match the number of edge marks at the bottom of the previous sheet. For
example, the sheet that results from applying the first copy subgroup has one edge mark at the top of the sheet
and two edge marks at the bottom. Any sheets resulting from subsequent copy subgroups have two edge
marks at both the top and bottom of the sheet.
In the case of an XOA Discard Buffered Data command, an XOA Discard Unstacked Pages command, or a
discard due to exception recovery, the number of edge marks at the top of the next sheet to print after the
XOA-DBD command, XOA-DUP command, or exception is the same as the number of edge marks at the
bottom of the last sheet that was committed for printing.
Any blank sheets generated internally by the printer due to an XOH Eject to Front Facing command do not
contain any edge marks. [IPDS-4-957]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0C00' | Control Edge Marks (CEM) order code | X'0C00' [IPDS-4-958]|
| 2 | CODE | Edgemark | | Edge Mark:<br>X'00' Inhibit (default)<br>X'01' Continue<br>X'F1' One edge mark<br>X'F2' Two edge marks<br>X'F3' Three edge marks<br>X'FE' Alternate [IPDS-4-959]| |

**Bytes 0–1 Control Edge Marks order code** [IPDS-4-960]

**Byte 2 Edge Mark**
X'00' Inhibit—stop printing edge marks.
If edge marks are currently inhibited, this parameter has no effect. If this command
has not been received by the printer, inhibit is used.
X'01' Continue
If the edge marks have previously been inhibited, edge mark printing is resumed using
the number of edge marks that were being printed before the inhibit order became
effective. If no previous XOA-CEM command has been received, one edge mark is
printed on all subsequent sheets until another XOA-CEM command is received. If
edge marks are currently being printed, this parameter has no effect.
X'F1' One edge mark
Start printing one edge mark regardless of how edge marks are currently being
controlled. Print one edge mark on all subsequent sheets until another XOA-CEM
command is received.
X'F2' Two edge marks
Start printing two edge marks regardless of how edge marks are currently being
controlled. Print two edge marks on all subsequent sheets until another XOA-CEM
command is received.
X'F3' Three edge marks
Start printing three edge marks regardless of how edge marks are currently being
controlled. Print three edge marks on all subsequent sheets until another XOA-CEM
command is received.


X'FE' Alternate
Change the number of edge marks from one to two, or from two to three, or from three
to one. If edge marks are currently inhibited, this parameter has no effect.
Multiple XOA-CEM commands that apply to the same sheet can cause different
results on different printers; some printers apply each XOA-CEM command as it is
received and the sheet is printed with the resultant edge mark, other printers apply the
last received XOA-CEM command to the sheet. For example, if the printer is currently
using three edge marks and then receives an edgemark parameter of X'F1' (one edge
mark), followed by a X'FE' (alternate) followed by yet another X'FE' (alternate) all for
the same sheet, some printers print three edge marks and others print one edge
mark. For another example, if an edgemark parameter of X'F1' (one edge mark) is
followed by a X'F3' (three edge marks) both for the same sheet, all printers print three
edge marks.
The recommended method of handling multiple XOA-CEM commands that apply to
the same sheet is to process each one as it is received and use the resultant edge
mark.
Exception ID X'0299..02' exists if an invalid edge mark value is specified. [IPDS-4-961]


XOA Discard Buffered Data
The XOA Discard Buffered Data (DBD) command, sometimes called Discard Buffered Pages, deletes all
buffered data from the printer storage and returns the printer to home state. Any data currently being received
is deleted. If this order is syntactically correct, no exceptions can result from its execution. The DBD order does
not affect completely received resources, such as fonts, page segments, and overlays; however, if the printer
is in any resource state, the printer deletes the partial resource before returning to home state.
The length of the XOA-DBD command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The printer adjusts the counters by setting the received page counter to the committed page counter. None of
the other page or copy counters are affected by this command.
If the first page of a group is discarded, the grouping associated with that group is discarded. If a middle or last
page of a group is discarded but the first page of the group is not discarded, the grouping is not complete and
therefore, the next page received from the host is considered part of the group.
Note: The Discard Buffered Data order is a synchronizing command. Any command following a synchronizing
command is not processed until all preceding commands have been completely processed. Also, the
ACK of the DBD order is not returned until DBD processing is complete. [IPDS-4-962]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F200' | Discard Buffered Data (DBD) order code | X'F200' [IPDS-4-963]|


XOA Discard Unstacked Pages
The XOA Discard Unstacked Pages (DUP) command deletes all buffered data from the printer storage (just
like DBD), discards all printed but unstacked pages, and returns the printer to home state. Any data currently
being received is deleted. If this order is syntactically correct, no exceptions can result from its execution. The
DUP order does not affect completely received resources, such as fonts, page segments, and overlays;
however, if the printer is in any resource state, the printer deletes the partial resource before returning to home
state.
The length of the XOA-DUP command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
This order is useful in a UP
3I environment where there is a means to remove partially completed sets of
sheets.
The printer adjusts the counters by setting all page and copy counters to the stacked page and copy counters.
If the first page of a group is discarded, the grouping associated with that group is discarded. If a middle or last
page of a group is discarded but the first page of the group is not discarded, the grouping is not complete and
therefore, the next page received from the host is considered part of the group.
Note: The XOA Discard Unstacked Pages command is a synchronizing command. Any command following a
synchronizing command is not processed until all preceding commands have been completely
processed. Also, the ACK of the DUP order is not returned until DUP processing is complete. [IPDS-4-964]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F500' | Discard Unstacked Pages (DUP) order code | X'F500' [IPDS-4-965]|


XOA Exception-Handling Control
The XOA Exception-Handling Control (EHC) command allows the host to control how the printer reports and
processes exceptions. A data-stream exception exists when the printer detects an invalid or unsupported
command, control, or parameter value in the data stream received from the host. The IPDS architecture
defines Alternate Exception Actions (AEAs) when a printer receives a valid request that is not supported by the
printer.
The length of the XOA-EHC command can be:
Without CID X'000A'
With CID X'000C'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The EHC used for a given exception is the one most recently received at the printer; however, an
asynchronous data-stream exception might have been reported out of sequence. The EHC that applies is the
one that would have applied had the exception been reported in sequence, that is, as a synchronous data-
stream exception. The EHC used for a given exception on a copy of a page is the one that would have applied
had the exception been detected on the page; that is, the printer must remember the location of the EHC with
respect to the commands comprising the page for all copies of the page.
The EHC used for a medium overlay, invoked with the LCC command, is the current EHC for the first Begin
Page command of a sheet. Medium overlays are printed when the printer processes the Begin Page command
of the first page on the sheet. The printer is in page state when medium overlay processing begins.
The EHC used for a preprinted form overlay invoked with the LCC command is the current EHC for the first
Begin Page command of a sheet. The EHC used for a preprinted form overlay invoked with the IO command is
the most recently received EHC when the IO command is processed.
When a data-stream exception is encountered, the EHC provides control over the following:
• Which exceptions with AEAs are reported to the host. [IPDS-4-966]
• Whether the AEA, if one exists, is to be taken. [IPDS-4-967]
• How processing continues if the AEA is not taken. [IPDS-4-968]
• For some printers, the highlighting of position-check exceptions when a Page-Continuation Action is not [IPDS-4-969]
taken. This is called position-check highlighting support.
• For some printers, the use of Exception Page Print to control printing of page information when the printer [IPDS-4-970]
detects a data-stream exception. This is called independent-exception-page-print support.
• For some printers, the reporting or suppressing of certain NACKs that cause the printer to discard buffered [IPDS-4-971]
data. This is called operator-directed recovery. This function allows a presentation services program that
does not have repositioning capability, to continue printing after, for example, a physical media jam has
occurred at the printer. In this case the NACK is suppressed, buffered data is not discarded, but some pages
might be lost due to jammed media, operator actions, or inability to print all of the page in error. Any lost
pages could be reprinted at a later time.
When data-stream exceptions are encountered and reported while downloading a page segment or overlay
within commands saved with the object, the page segment or overlay is discarded by the printer before the
exceptions are reported to the host. A data-stream exception detected in an Anystate command does not
affect whether or not the page segment or overlay is discarded.
When data-stream exceptions are encountered while saving a group of pages using the XOH-SGO operation
Save Pages, the XOA-EHC command functions as if the pages were being printed. Thus partial pages or full
pages can be saved by the printer, with appropriate exception highlighting. When partial pages are discarded
because of an XOA-EHC setting, the page is not saved by the printer and the page sequence number is not
incremented. Therefore, errors that exist within a group of pages to be saved can cause only some of the
pages to be saved.
XOA Exception-Handling Control (EHC) [IPDS-4-972]


While saving a page due to the XOH-SGO operation Save Pages, the printer does not increment the received
page counter nor does it adjust the counters when processing a synchronous data-stream exception.
When a synchronous data-stream exception is reported for a page to be saved the page sequence number
that the page would be assigned were it actually saved, is returned in bytes 20–23 of the format 0, 1, or 7
sense data. Sense byte 18 identifies whether or not the page was intended to be saved.
For some printers, even finer control can be specified with the Presentation Fidelity Control command for
certain presentation functions that a device is incapable of performing, such as color, finishing, and
unsupported text controls. For these functions, the Presentation Fidelity Control command can be used to
specify:
• Whether or not printing should continue when an exception is detected [IPDS-4-973]
• Whether or not an exception should be reported [IPDS-4-974]
• For color exceptions, what type of color substitution is permitted [IPDS-4-975]
The exception-handling control flowchart, Figure 60, shows the relationship between the PFC
command and the XOA-EHC command.
The format of the Exception-Handling Control order is as follows:
Offset Type Name Range Meaning DC1 Range
0–1 CODE Order code X'F600' Exception-Handling Control (EHC) order code X'F600'
2 BITS Exception reporting flags [IPDS-4-976]
bit 0 Undefined
character
B'0', B'1' Report undefined-character checks B'0', B'1'
bit 1 Page position B'0', B'1' Report page-position checks B'0', B'1'
bits 2–5 B'0000' Reserved B'0000'
bit 6 Highlight B'0', B'1' Position-check highlight (optional) B'0'
bit 7 Others B'0', B'1' Report all other exceptions with AEAs B'0', B'1'
3 BITS Automatic Recovery flags [IPDS-4-977]
bits 0–5 B'000000' Reserved B'000000'
bit 6 Operator
directed
B'0', B'1' Operator-directed recovery (optional) B'0'
bit 7 NoAEA B'0', B'1' Do not take Alternate Exception Action B'0', B'1'
4 BITS Exception-presentation processing flags [IPDS-4-978]
bits 0–5 B'000000' Reserved B'000000'
bit 6 Cont B'0', B'1' Page continuation B'0', B'1'
bit 7 EPP B'0', B'1' Exception print B'0', B'1'
Bytes 0–1 EHC order code
Byte 2 Exception reporting
This byte defines those exceptions (with AEAs) that are reported to the host, and allows
control over position-check highlighting. An exception is always reported, regardless of the
value of this byte, if no AEA exists or the printer has been instructed not to take the AEA (bit 7
of byte 3). Position-check highlighting is independent of Alternate Exception Actions.
Exceptions are reported with a negative Acknowledge Reply (NACK). Whenever the printer
reports an exception, it discards all upstream data that it has received, before readying itself to
receive another command.
XOA Exception-Handling Control (EHC) [IPDS-4-979]


Note: The host can determine which pages have been discarded by examining the page and
copy counters in an ACK of a synchronizing command. Any pages not reflected in the
counters have been discarded. Refer to “Page and Copy Counter Adjustments”.
This byte is bit mapped; bit values are as follows:
Bit 0 Report undefined-character checks
An undefined character within text, graphics, or human-readable
interpretation bar-code data is a code point that does not map to a defined
character shape. For LF1-type coded fonts, such a character is specified as
undefined in its font-index entry. If this bit is set to B'1', attempting to print with
an undefined character causes the printer to report an exception to the host. If
this bit is set to B'0' and the AEA is taken, an exception is not reported.
The report undefined character-checks flag applies to the following exception
IDs: X'0821..00', X'0829..00', X'03C3..01', and X'028F..50'.
Bit 1 Report page-position checks
An exception exists if the host attempts to print outside the valid printable
area (text characters or rules or positioning of object areas so that any portion
of the object data is outside the valid printable area). If this bit is set to B'1',
the printer reports this exception to the host. If this bit is set to B'0' and the
AEA is taken, an exception is not reported.
Bits 2–5 Reserved
Bit 6 Position-check highlight
The Position-Check Highlight bit determines if the printer highlights a position-
check exception (X'08C1..00', X'0411..00', or X'020A..05') on a page.
However, when a Page-Continuation Action is taken for a position-check
exception, highlighting occurs regardless of the setting of the Position-Check
Highlight bit. When this bit is set to B'1', each unique occurrence of a position-
check exception on a page must be highlighted. The appearance of the
highlighting on a page is determined by the printer.
If bit 6 is set to B'1', and the printer is in page state or a derivative of page
state, the printer highlights a position-check exception within the current Valid
Printable Area at the approximate exception location on the page. If a Page-
Continuation Action is taken for a position-check exception, the printer
highlights a position-check exception within the current Valid Printable Area at
the approximate exception location on the page. Otherwise, no highlighting
takes place for a position-check exception.
Note: Support for position-check highlighting is optional and is reported by
property pair X'F601' in the Device-Control command-set vector of an
STM reply. This bit has no meaning for a printer that does not specify
position-check highlighting support in the Sense Type and Model reply.
Bit 7 Report all other exceptions with AEAs
If this bit is set to B'1', the printer reports to the host all exceptions with AEAs
other than those defined by bits 0 and 1. If this bit is set to B'0' and the AEA is
taken, the exceptions with AEAs are not reported.
XOA Exception-Handling Control (EHC) [IPDS-4-980]


Byte 3 Automatic recovery
This byte is bit-mapped; bit values are as follows:
Bits 0–5 Reserved
Bit 6 Operator-directed recovery
This bit controls whether the printer operator or the host presentation services
program directs the recovery of certain NACKs that cause the printer to
discard buffered data.
If this bit is B'1', the printer suppresses the exception handling for exception
IDs with the following action codes:
X'08'—Physical media jam
X'1A'—Redrive buffered pages
X'23'—Temporary hardware exception
These exceptions normally cause the printer to discard buffered data and
adjust the station counters. When suppressed, however, no buffered data is
discarded. For physical media jam exceptions, the stacked page and copy
counters are set to the equivalent jam recovery page and copy counters, but
no other counter adjustments are done. The printer operator must then
correct the problem that caused the exception, before printing can continue.
Some data might be lost due to jammed media, operator actions, or inability
to print all of the page in error.
If an XOH-SRP command is being processed when one of these exceptions
occurs, the printer must handle the exception condition and then completely
stack all received pages before accepting another IPDS command from the
presentation services program.
Notes:
1. Support for this bit is optional and is reported in the reply to a Sense Type [IPDS-4-981]
and Model command. This bit is ignored by printers that do not specify the
X'F603' property pair in the Device-Control command-set vector of an
STM reply.
2. There are other action codes that can cause page data to be discarded by [IPDS-4-982]
the printer, such as:
X'09'—Data-related print exception
X'0D'—Printer restart
X'15'—Cancel
X'16'—Hardware related print error
X'17'—Printer mechanism unusable
X'19'—Asynchronous data-stream exception
X'1D'—Printer characteristics changed
X'1E'—Asynchronous out-of-storage exception
X'22'—Printer inoperative
Exception IDs with these action codes are not affected by this XOA-EHC
bit; that is, X'09', X'0D', X'15', X'16', X'17', X'19', X'1D', X'1E', and X'22'
NACKs are reported regardless of the setting of this bit.
Bit 7 No AEA
This bit defines how the printer is to continue processing after encountering a
data-stream exception. If bit 7 is set to B'0', the printer takes the AEA (if one is
defined) and continues processing. If an AEA has not been defined for the
exception, the printer proceeds as though this bit contained a B'1'.
XOA Exception-Handling Control (EHC) [IPDS-4-983]


If bit 7 is set to B'1', the printer reports the exception, regardless of the value
in byte 2 (exception reporting). The printer continues processing as defined in
byte 4 (exception-presentation processing).
Byte 4 Exception-presentation processing
This byte defines how the printer continues processing after encountering data-stream
exceptions where an AEA is not taken or not defined in any state except home state and font
state. Exceptions that occur in home and font states cause the printer to discard any font, font
section, or control command and to return to home state.
Bit 7 of the byte is also used by printers that provide Independent Exception Page Print
support to determine whether or not the page is printed.
This byte is bit mapped; bit values are as follows:
Bits 0–5 Reserved
Bit 6 Page continuation
If bit 6 is set to B'0', the printer terminates processing of the page, overlay, or
page segment and enters home state. The printer discards partial overlays
and page segments. The exception print bit (bit 7) specifies the printer
action for partial pages.
If bit 6 is set to B'1' and the printer provides Independent Exception Page
Print support, the printer also uses the Exception Page Print bit (bit 7) at the
end of the page after taking either a Skip and Continue Action or a Page
Continuation Action. If the printer supports Page Continuation Actions and a
Page Continuation Action for this exception is not defined, the printer
responds as if bit 6 were set to B'0'.
If bit 6 is set to B'1' and the printer does not provide Independent Exception
Page Print support, the printer ignores bit 7 at the end of the page after taking
either a Skip and Continue Action or a Page Continuation Action. If the printer
supports Page Continuation Actions and a Page Continuation Action for this
exception is not defined, the printer responds as if bit 6 were set to B'0'.
Property pairs X'F604' and X'F605' in the Device-Control command-set vector
of an STM reply indicate the supported method of page continuation. Only
one of these property pairs should be returned by the printer; if neither is
returned the printer has not identified the supported continuation method and
you should consult your printer documentation to determine the supported
method.
1. Skip and Continue Action [IPDS-4-984]
Skip and Continue Actions are attempts by the printer to skip the
remainder of the IPDS object containing the exception and to print
subsequent objects or text on the page (if any).
The printer treats all subsequent commands other than Anystate
commands (NOP , SHS, STM, and XOA) as No Operation (NOP)
commands until it encounters either the next valid command or a
terminating condition. The printer remains in page, page segment, or
overlay state. If the printer is in a presentation-object
state, the End
command is the next valid command and returns the printer to page, page
segment, or overlay state. When the next valid command is encountered,
the printer begins normal processing again. The exception that caused
the skip and continue action is reported when the end of the page is
reached, or when an XOA command sets home state, or when a
command with an ARQ is received. A partial page is printed if the printer
supports Independent Exception Page Print and if the ExceptiOA Exception-Handling Control (EHC) [IPDS-4-985]


Print bit is set to B'1'. The following conditions determine the next valid
command:
• If the exception occurs in a text-major Write Text command within a [IPDS-4-986]
page, page segment, or overlay, in a Load Font Equivalence command,
or in an Include Page Segment command, the next valid command is
End Page, Set Home State, XOA Discard Buffered Data, or XOA
Discard Unstacked Pages.
• If the exception occurs in an Anystate command, the next valid [IPDS-4-987]
command is the command that follows.
• If the exception occurs in a presentation-object [IPDS-4-988]
state, IO-Image
resource state, object-container resource state, or metadata state, the
next valid command is End.
• If the exception occurs in any other command, the next valid command [IPDS-4-989]
is one of the following (if supported):
– Write Text
– Include Overlay
– Include Page Segment
– Write Text Control
– Write Image Control
– Write Image Control 2
– Write Bar Code Control
– Write Graphics Control
– End Page
– Set Home State
– XOA Discard Buffered Data
– XOA Discard Unstacked Pages
– Load Font Equivalence
If the following terminating conditions occur, the printer returns to home
state and reports the indicated exception:
• An asynchronous exception occurs such that the printer cannot recover [IPDS-4-990]
without host intervention. The asynchronous exception is reported and
the exception that caused the skip and continue action is discarded.
• A command is received with the ARQ flag set on. The exception that [IPDS-4-991]
caused the skip and continue action is reported.
• A command is received with a length outside the valid IPDS range. The [IPDS-4-992]
exception that caused the skip and continue action is reported.
• A command is received that violates the IPDS state diagram. The [IPDS-4-993]
exception that caused the skip and continue action is reported.
During skip and continue actions, Anystate commands are treated as
follows:
• SHS, XOA DBD, and XOA DUP are next valid commands in all cases. [IPDS-4-994]
They are processed, the skip and continue action processing is
terminated, the exception is reported, and normal command processing
resumes.
• Anystate commands with the ARQ bit set on are terminating conditions. [IPDS-4-995]
They are not processed; they terminate skip and continue processing,
the exception is reported, and the printer returns to home state.
• All other Anystate commands are processed as normal. However, [IPDS-4-996]
subsequent non-Anystate, non-next-valid commands are skipped.
XOA Exception-Handling Control (EHC) [IPDS-4-997]


Exceptions detected in included overlays or page segments are treated
as though the commands were received as part of the page.
A printer may choose to consider all included overlays as independent of
the page on which they are included, so that an exception in one overlay
does not affect the processing of commands for another.
Notes:
1. The order of exception discovery is device dependent. For example, [IPDS-4-998]
some printers use parallel processing where the order of exception
processing is different from linear processing.
2. For a printer that uses parallel processing, if bit 6 is set to B'0', the [IPDS-4-999]
printer is not guaranteed to terminate processing at the first exception
encountered as described in the preceding note. The printer
terminates processing as soon as possible, but more than one
exception might be returned to the host.
2. Page Continuation Action [IPDS-4-1000]
Page Continuation Actions are defined for the printer to provide a “best
can do” function. Page Continuation Actions allow the printer to continue
processing data on a page after an exception has occurred. They are
taken instead of skipping the data containing the exception (Skip and
Continue Actions) or discarding the entire page (Exception Page Print bit=
B'0'). Page Continuation Actions might involve ignoring one or more
commands. Page Continuation Actions are defined to allow the printer to
continue processing yet:
• Reduce the possibility of presenting incorrect data [IPDS-4-1001]
• Reduce the possibility that incorrect data can be mistaken for correct [IPDS-4-1002]
data
Not all exceptions have Page Continuation Actions. For exceptions with
defined page continuation actions that occur in page state or any
derivative of page state, the printer identifies and highlights the exception
and approximate exception location within the current Valid Printable Area
of the page. Each unique occurrence of an exception on a page must be
highlighted. The appearance of the highlighting on a page is determined
by the printer. For a specific position-check exception that was previously
highlighted because the position-check highlight flag was B'1', the printer
need not highlight a second time. If a Page Continuation Action is defined
for an exception and if byte 4, bit 6=B'1', the printer continues processing
with a printer-defined Page Continuation Action until the end of the page
is reached or a terminating condition occurs. If the following terminating
conditions occur, the printer returns to home state and reports the
exception:
• A hardware exception occurs when the printer cannot recover without [IPDS-4-1003]
host intervention.
• A command is received with the ARQ flag set to B'1'. [IPDS-4-1004]
• A command is received with a length outside the valid IPDS range. [IPDS-4-1005]
• A command is received that violates the IPDS state diagram. [IPDS-4-1006]
During Page Continuation Actions that involve skipping, Anystate
commands are treated as follows:
• SHS, XOA DBD, and XOA DUP are next valid commands in all cases. [IPDS-4-1007]
They are processed, the Page Continuation Action processing is
XOA Exception-Handling Control (EHC) [IPDS-4-1008]


terminated, the exception is reported, and normal command processing
resumes.
• Anystate commands with the ARQ bit set on are terminating conditions. [IPDS-4-1009]
They are not processed; they terminate Page Continuation Action
processing, the exception is reported, and the printer returns to home
state.
• All other Anystate commands are processed as normal. However, [IPDS-4-1010]
subsequent non-Anystate, non-next-valid commands are skipped.
Note that if the PCA is to skip to the End command, any partial data that
has been processed as part of the current object may be printed as part of
the page (assuming that the page is printed).
Notes:
1. The order of exception discovery is device dependent. For example, [IPDS-4-1011]
some printers use parallel processing where the order of exception
processing is different from linear processing.
2. For a printer that uses parallel processing, if bit 6 is set to B'0', the [IPDS-4-1012]
printer is not guaranteed to terminate processing at the first exception
encountered as described in the preceding note. The printer
terminates processing as soon as possible, but more than one
exception may be returned to the host.
Bit 7 Exception Page Print
This bit is checked by the printer in two situations:
• If the printer supports Independent Exception Page Print as specified by [IPDS-4-1013]
property pair X'F602' in the Device-Control command-set vector of an STM
reply, and at least one exception has been queued for reporting later.
• If the printer does not support Independent Exception Page Print support, [IPDS-4-1014]
an AEA was not taken, the printer is processing a page, and either of the
following is true:
– The Page Continuation bit is B'0'.
– The Page Continuation bit is B'1', the printer supports Page Continuation
Actions, and a Page Continuation Action is not defined for this exception.
A value of B'0' specifies that, within the limits of the printing process, the
printer is to discard the page containing the exception. When logical pages
overlap on a sheet and one of the logical pages is discarded because EPP
was set to B'0', the overlapped portion of previous data (pages, page
overlays, or medium overlays) might also be discarded. A value of B'1'
specifies that, within the limits of the print process, the printer is to perform the
equivalent of an End Page command and print as much of the page, up to the
point of the exception, as possible. Printers can, optionally, highlight
undefined character checks that are reported to the host. The appearance of
the highlighting on a page is determined by the printer; for example, an
undefined character check might be highlighted by printing a printer default
character in place of the undefined character.
**Figure 60** is a 4–page flowchart that depicts the exception-handling decision path.
XOA Exception-Handling Control (EHC) [IPDS-4-1015]


**Figure 60**. Exception-Handling Control (spans four pages)
(Part 1 of figure)
Exception
occurs
Action code
X'01', X'06', X'19', or
X'1F' ?
Operator-directed
Recovery in effect?
Refer to the note.
Storage error
while saving a page
(AC = X'0C')
?
Queue
NACK
for later
reporting
Discard all
partially saved
page groups
A
No NoNo
No
No
No
No
No
Yes YesYes
Yes Yes
Yes
Yes
Yes
B
Some printers allow the host to
control the reporting or suppressing of
NACKs with action code X'08', X'1A', or
X'23'. These NACKs are suppressed only
when the printer supports this function and
when XOA EHC byte 3, bit 6 is B'1'.
Note:
Is this
a position-check
exception AND is position-
check highlighting supported AND
is the "Position-Check
Highlight" flag
B'1'?
Page
state or
derivative
?
Highlight
position check

F
Is a
Presentation
Fidelity Control
active for this
exception
?
Is
"Stop
Presentation"
specified in PFC
triplet
?
Apply the substitution
rule, if any,  specified
in the PFC triplet
E
Queue
NACK
for later
reporting
B
Is
"Report
Exception" specified
in the PFC
triplet
?
D
Queue
NACK
for later
reporting
Presentation Fidelity Control command processing
Discard the page,
but keep previously
saved pages
XOA Exception-Handling Control (EHC) [IPDS-4-1016]


(Part 2 of figure)
AEA
defined
?
No No
No
No
No
No
No
Yes Yes
Yes
Yes
Yes
Yes
Yes
"No
AEA" flag
= B'0'
?
Queue
NACK
for later
reporting
Take AEA
B
Is
the "Report
Exception Flag"
for this exception
= B'1'
?
Queue
NACK
for later
reporting
D
Did the
exception occur in
page state or a derivative
state
?
A
Is
"Page
Continuation"
flag = B'1'
?
E
D
PCA or SCA
See part 4
of figure.
Continue processing until the end of the
page, or a command that changes the
printer to homestate is encountered, or a
command with an ARQ is encountered.
Additional XOA-EHC commands can be
received and processed during this time.
E
Independent
EPP support
?
C
Did the
exception occur
in a page
?
A
F
XOA Exception-Handling Control (EHC) [IPDS-4-1017]


(Part 3 of figure)
Printers may delay discarding partial
resources until an End or End Page
command is received.  If the only data-
stream exceptions detected in a page
segment or overlay were found within
Anystate commands, then the page
segment or overlay is kept.
Discard all
upstream data that
has been received
by the printer
Is
the EPP
flag = B'1'
?
Discard partial resource (if any)
Set home state
(if not already
in home state)
Did the
exception occur
in a page
?
Discard
Partial
Page
Create Partial Page
The partial page is created within the limits of
the print process.  For undefined character
checks that will be reported to the host, a
printer may highlight the exception on the page.
The appearance of the highlighting on a page is
determined by the printer; for instance, an
undefined character check might be highlighted
by printing a printer default character in place
of the undefined character.
Discard all upstream
data that has been
received by the
printer Adjust
page and copy
counters
Report
queued
NACKs
Adjust page and
copy counters
Report queued NACKs
E A
C
C
No
Yes
No
Yes
Continue
copy group
processing
Copy
group
processing
complete
? Continue processing
with the next
received command
BNo
Yes
Set home state
Continue processing
with the next
received command
XOA Exception-Handling Control (EHC) [IPDS-4-1018]


(Part 4 of figure)
Skip-and-Continue Actions Page-Continuation Actions
Yes
Get next
command
Next valid
command
?
Terminating
condition
?
Terminating
condition
?
Yes
No
No
A
Yes
Yes
Yes
No
No
No
Is
the PCA
“None”
?
Terminating
condition
?
Page
state or
derivative
?
Highlight exception
 (if not
already highlighted)
Take
PCA
Yes A
No
E
A
XOA Exception-Handling Control (EHC) [IPDS-4-1019]


XOA Mark Form
The XOA Mark Form (MF) command causes the printer to mark the current or the next sheet with a device-
specific form. This form is analogous to an overlay that is permanently stored in the printer; however, it is
invoked by the MF order. The printer defines the origin of the permanently-stored overlay (mark form) and the
host cannot change it; that is, it is not affected by changes to the media origin. The host might add more data to
the marked form, for example, job number or user ID, with a normal IPDS command sequence. The MF order
can be used to print job-separator sheets or exception-summary sheets.
The length of the XOA-MF command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
If the printer is in page state or any derivative of page state when the MF order is received, the form is printed
on the current side of the current sheet and on all copies of the current sheet generated with the current LCC;
the current side of the current sheet is the one on which the first copy of the page being received is printed. If
the printer is in any other state, the form is printed on the side of the sheet that the next received page is
printed on (assuming default positioning), and on all copies of this sheet generated with the current LCC. The
XOA Mark Form command can cause printing to occur outside the user printable area.
Note: A printer may optionally mark the current (or next) sheet and also the following sheet to provide for better
visibility. The marks are not made on sheets created by any IPDS command that causes an Eject to
Front Facing or on sheets created by a hardware nonprocess runout (NPRO). [IPDS-4-1020]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0800' | Mark Form (MF) order code | X'0800' [IPDS-4-1021]|


XOA Obtain Additional Exception Information
The XOA Obtain Additional Exception Information (OAEI) command requests that the printer return additional
information about an exception that the printer has just reported. The printer responds by placing available
information, if any, in the special data area of a subsequent Acknowledge Reply (or in a series of replies). If the
OAEI command is not sent with the Acknowledgement Required (ARQ) flag set, the command is treated as a
No Operation (NOP) command.
The length of the XOA-OAEI command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
For a given exception, the printer indicates that additional information is available by setting the additional-
information flag in the Acknowledge Reply reporting the exception. Exceptions without this bit set have no
additional information available. Also, additional exception information is not available for exceptions that are
not reported.
This command will return non-empty additional exception information only
if it is the first command issued by
the host after receiving a NACK from the printer that has the additional-information flag set. That is, when a
host receives such a NACK, it must immediately, before sending any other command to the printer, send the
OAEI command, if the host wishes to retrieve the additional exception information the printer has said is
available. If any other command is the next command sent to the printer, or if an asynchronous exception is
reported during the OAEI processing,
the printer discards the additional exception information.
A printer can make additional exception information available for a given exception—and thus the additional-
information flag in the Acknowledge Reply can be set—only if the printer is ready to respond to an immediate
OAEI command to retrieve that information.
Support for this optional command is indicated by the X'80F9' property pair in the Device-Control command-set
vector in an STM reply. [IPDS-4-1022]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F900' | Obtain Additional Exception Information (OAEI) order code | X'F900' [IPDS-4-1023]|


OAEI Reply
An OAEI reply returns additional exception information for one exception. The reply can contain more than one
piece of additional information for that exception.
If the additional exception information does not fit into one single Acknowledge Reply, a sequence of
Acknowledge Replies can be obtained using the ACK-continuation method. In this case, the special data area
of each individual Acknowledge Reply continues where the previous Acknowledge Reply left off—no fields of
the OAEI reply are repeated. Each Acknowledge Reply will have the additional-information flag set to B'0'.
Such Acknowledge Replies can be split at any byte boundary.
The special data area of an OAEI reply has the following format: [IPDS-4-1024]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–3 | UBIN | Length | X'00000006'<br>X'0000000B'–X'FFFFFFFF' | No additional exception information available<br>Length of data, including this length field [IPDS-4-1025]|
| 4–5 | | Reserved | X'0000' | Reserved [IPDS-4-1026]|

Zero or more entries in the following format: [IPDS-4-1027]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–3 | UBIN | Entry Length | X'00000005'–X'FFFFFFFA' | Length of the entry, including this length field [IPDS-4-1028]|
| + 4 | CODE | Format | X'01' | Format of the additional information: Unarchitected text [IPDS-4-1029]|
| + 5 to end of entry | | Additional information | See format descriptions below | Additional exception information. The syntax for this field is specific to the format code; refer to the byte descriptions below for specific syntax. [IPDS-4-1030]|
Bytes 0–3 Length
This field contains the total length of the data, including the length field itself. This length might
be more than can be contained in the special data area of one Acknowledge Reply, in which
case the additional bytes are obtained using the ACK-continuation method. A length of
X'00000006' indicates that there is no additional exception information available.
Note: A well-behaved host would never expect to receive a reply with this length field equal to
X'00000006', since that indicates the OAEI command was issued at an inappropriate
time.
Bytes 4–5 Reserved
Bytes 6 to end Additional exception information entries in the following format:
Entry bytes 0–3 Entry length
This field contains the length of this entry, including this length field.
Entry byte 4 Format
This field identifies the format that the additional information in the following
bytes will take.
X'01' Unarchitected text [IPDS-4-1031]


Entry bytes 5 to end Additional exception information
This field contains the additional exception information, in the format specified
in entry byte 4. The possible formats are described just below.
Additional Exception Information Format X'01'
Format X'01' is used to return additional exception information as unarchitected text. [IPDS-4-1032]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 5 | CODE | Encoding | X'01' | The encoding used in the text-information field: UTF-16BE [IPDS-4-1033]|
| + 6–7 | | Reserved | X'0000' | Reserved [IPDS-4-1034]|
| + 8 to end of entry | | Text information | Any valid text | Unarchitected text containing the additional exception information [IPDS-4-1035]|


XOA Print-Quality Control
The XOA Print-Quality Control (PQC) command transfers three bytes that indicate the level of quality with
which the following data is to be printed. For text data, this order specifies the level of quality the printer
achieves without changing fonts.
The length of the XOA-PQC command can be:
Without CID X'0008'
With CID X'000A'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
If a printer has only one print quality, this order does not apply.
If the printer is receiving a page (page state or any derivative of page state) when the PQC order is received,
the current page, and all copies of the current page generated by means of the current LCC, and all
subsequent pages (Begin Page...End Page pairs received from the presentation services program) are printed
in the level of quality specified. If the printer is in any other state, the next page received, and all copies of that
page generated by means of the current LCC, and all subsequent pages (Begin Page...End Page pairs
received from the presentation services program) are printed in the level of quality specified.
Exception ID X'021E..02' exists if the printer supports multiple levels of text quality but cannot change to the
requested quality in the current font. In this case, the AEA is to change to a device-determined quality or font
without changing the code page. Refer to “XOA Exception-Handling Control” for a description of
AEAs.
This order affects only the presentation of page data; it has no effect on the downloading of resources such as
fonts, overlays, or page segments. The format of the PQC order is as follows: [IPDS-4-1036]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F800' | Print-Quality Control (PQC) order code | X'F800' [IPDS-4-1037]|
| 2 | UBIN | Quality level | X'01'–X'FF' | Print-quality level | X'01'–X'FF' [IPDS-4-1038]|

**Bytes 0–1 PQC order code**
Byte 2 Print-quality level
Quality levels are specified on a relative scale as follows:
X'01' = lowest quality level
X'FE' = highest quality level
X'FF' = printer default
The range of quality levels supported is indicated within the Print-Quality Support self-defining
field in an XOH-OPC reply.
The printer uses a device-defined algorithm to map its supported quality levels to the specified
PQC quality levels. This mapping can vary for different data types, such as text, image,
graphics, and bar code. For example, if a printer supports three levels of quality for text, it
could map levels 1 through 85 to its first text level, 86 through 170 to its second text level, and
171 through 254 to its third text level. If only one quality level is supported for image data, [IPDS-4-1039]
image data is printed at this quality level for any specified value from 1 through 255.
Exception ID X'0292..02' exists if a quality-level value of X'00' is specified.
XOA Print-Quality Control (PQC) [IPDS-4-1040]


XOA Request Resource List
The XOA Request Resource List (RRL) command requests information about the printer's current resources.
The printer responds by placing the requested information in the special data area of a subsequent
Acknowledge Reply (or in a series of replies).
The length of the XOA-RRL command can be:
Without CID X'000D'–X'7FFF'
With CID X'000F'–X'7FFF'
However, each entry length must also be valid. Exception ID X'0202..02' exists if the command length is invalid
or unsupported.
This order helps the host manage the printer resources (fonts, page segments, overlays, saved page groups,
data-object-font components, and data object resources) by providing a general query mechanism. The
response to this order is formulated and returned only after all previous commands and orders that could affect
the presence or absence of a queried resource have been processed. If the host has not sent an
Acknowledgment Request (ARQ), this command is treated as a No Operation (NOP) command.
RRL has a query format that is sent in the data portion of the RRL order, and it has a reply format that is
returned to the host in the special data area of the ACK(s). The query format is shown in “Resource List Query”
. The reply format is shown in “Resource List Reply”. [IPDS-4-1041]


Resource List Query
The data in an XOA Request Resource List command consists of 5 bytes of control information followed by
one or more resource query entries that are processed in the order that they appear in the command. If a
syntax error is encountered in one of the entries, that entry and all following entries in the XOA-RRL command
are discarded; preceding entries remain in effect. Exception ID X'0291..02' exists in this situation, but reporting
of this exception is optional.
Offset Type Name Range Meaning DC1 Range
0–1 CODE Order code X'F400' Request Resource List order code X'F400' [IPDS-4-1042]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F400' | Request Resource List order code | X'F400' [IPDS-4-1043]|
| 2 | CODE | Query type | X'05'<br>X'FF', X'00' | Activation query, multiple entries optional<br>General query, single entry only X'FF' [IPDS-4-1044]| |
| 3–4 | CODE | Continue | Any value | Entry-continuation indicator | Any value [IPDS-4-1045]|

One or more resource query entries in the following format: [IPDS-4-1046]

| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 5 | UBIN | Length | X'03' to end of entry | Entry length of Bytes 5–n | X'03' [IPDS-4-1047]|
| 6 | CODE | RT | | Resource Type: [IPDS-4-1048]| |
| | | | X'01' | Single-byte LF1-type or LF2-type coded font [IPDS-4-1049]| |
| | | | X'02' | Double-byte LF1-type coded font [IPDS-4-1050]| |
| | | | X'03' | Double-byte LF1-type coded-font section [IPDS-4-1051]| |
| | | | X'04' | Page segment [IPDS-4-1052]| |
| | | | X'05' | Overlay [IPDS-4-1053]| |
| | | | X'06' | Device-version code page [IPDS-4-1054]| |
| | | | X'07' | Font character set [IPDS-4-1055]| |
| | | | X'08' | Single-byte raster, single-byte outline, or double-byte outline coded-font FIS [IPDS-4-1056]| |
| | | | X'09' | Double-byte coded-font section index [IPDS-4-1057]| |
| | | | X'10' | Coded font [IPDS-4-1058]| |
| | | | X'11' | Graphic character set supported in a font character set [IPDS-4-1059]| |
| | | | X'12' | Specific code page [IPDS-4-1060]| |
| | | | X'20' | Saved page group [IPDS-4-1061]| |
| | | | X'40' | Data object resource [IPDS-4-1062]| |
| | | | X'41' | Data-object font [IPDS-4-1063]| |
| | | | X'42' | Data-object-font component [IPDS-4-1064]| |
| | | | X'FF' | All resources [IPDS-4-1065]| |
| 7 | CODE | RIDF | | Resource ID format: [IPDS-4-1066]| |
| | | | X'00' | Host-assigned resource ID [IPDS-4-1067]| |
| | | | X'03' | GRID-parts format [IPDS-4-1068]| |
| | | | X'08' | Variable-length Group ID (X'00') triplet [IPDS-4-1069]| |
| | | | X'09' | Object-OID format [IPDS-4-1070]| |
| 8 to end of entry | | Resource ID | | Resource ID | Any value [IPDS-4-1071]|
Bytes 0–1 RRL order code
Byte 2 Query type
X'05' A value of X'05' identifies this as a query for the activation status of the specified
resource or resources. This is an optional query type; it is supported by those devices
that activate resources with an AR command or an AR + XOA-RRL command
sequence and that indicate support of the AR command by property pair X'702E'
in
the Device-Control command-set vector of an STM reply. This query type can be
issued following an AR command to interrogate the device on the activation status of [IPDS-4-1072]


the resources that the AR command attempted to activate. A reply to this query
specifying “activated” indicates that the resource was activated. A reply to this query
specifying “not activated” indicates that the resource was not activated, and might be
either present or not present.
Multiple entries of query type X'05' can be sent to a device if supported. A device
indicates multiple entry query support with property pair X'F401' in the Device-Control
command-set vector of an STM reply.
Query type X'05' can only be a query for an individual resource and cannot be a query
for a list of resources of a specified type and ID format.
X'FE' Retired item 131
X'FF' A value of X'FF' identifies this as a general query for the availability of the specified
resource or resources. Neither the query nor the query reply carry any implications
regarding the activation state of the resource or resources. A reply to this query
specifying “present” indicates that the resource is present in the device and might be
either activated or not activated. A reply to this query specifying “not present”
indicates that the resource is not present in the device and is also not activated.
Only a single entry of query type X'FF' may be sent to a device. This entry may not be
intermixed with entries of query type X'05' in an XOA-RRL command.
Query type X'FF' can be a query for an individual resource or a query for a list of
resources of the specified type and format.
Note: Some printers also accept X'00' in this field; X'00' has the same meaning as
X'FF' for these printers.
Exception ID X'0291..02' exists if an invalid or unsupported query-type value is specified.
Bytes 3–4 Entry-continuation indicator
This field is used to request a continuation of a resource list when using the RRL-continuation
method. On an initial query, these bytes must be set to X'0000'. If the entire resource list reply
does not fit in the special data area of the subsequent ACK, additional XOA-RRL commands,
identical except for this field, can be used to request the remainder of the list. A nonzero value
in this field indicates that this query is a request to return the next portion of the resource list. If
there is no next portion, or if there was no previous XOA-RRL command with a X'0000' in this
field, exception ID X'0291..02' exists.
An alternative method of controlling large XOA-RRL replies, called the ACK-continuation
method, is also provided. Printers that can return XOA-RRL replies larger than 256 bytes in
length must support both methods, although some older IPDS printers only support the RRL-
continuation method and therefore do not set the acknowledgment continuation flag to B'1' in
the Acknowledge Replies.
If the acknowledgment continuation flag is set to B'1' in an XOA-RRL Acknowledge Reply, the
presentation services program can use either method to obtain the next reply in the sequence.
The presentation services program should not switch between the two methods within a
sequence of replies.
For the XOA-RRL command only, if the ARQ bit is B'1', and if either the acknowledgment-
continuation flag in the next command is set to B'1' (ACK-continuation method) or the next
command is an XOA-RRL command and the entry-continuation indicator is set to a non-zero
value (RRL-continuation method), the printer returns the next portion of the resource list. If
both methods are used simultaneously, the acknowledgment-continuation flag in the XOA-
RRL command is ignored and the RRL-continuation method is used by the printer; an XOA-
RRL reply sequence is ended with a reply-list entry with a length of X'01'. [IPDS-4-1073]


Byte 5 Entry length
This byte specifies the length of this entry, that includes itself. Therefore, this byte indicates
either the location of the end of the command or the location of the entry length for the next
entry. If there are multiple entries, these entries must be queries of individual resources rather
than list queries and must be of query type X'05'.
**Table 29** lists the currently valid resource ID lengths. Additional RT/RIDF
combinations might be added in the future and therefore all values in the range X'03'–X'FF'
should be accepted for this byte; unrecognized RT/RIDF combinations are identified as
“resource not present” or “resource not activated” in the RRL reply.
If a printer does not support multiple-entry queries, this byte must indicate the location of the
end of the command. Otherwise, exception ID X'0291..02' exists.
Byte 6 Resource type
This byte specifies one of the following resource types:
X'01' Single-byte LF1-type or LF2-type coded font (required for LF1)
X'02' Double-byte LF1-type coded font
X'03' Double-byte LF1-type coded-font section
X'04' Page segment (required for PS1)
X'05' Overlay (required for OL1)
X'06' Device-version code page (required for LF3 and LF4); when used with RIDF=HAID,
this is a generic code page query and is the same as X'12'.
X'07' Font character set (required for LF3)
X'08' Single-byte raster, single-byte outline, or double-byte outline coded-font FIS
X'09' Double-byte fully described coded-font section index
X'10' Coded font (required for LF3)
X'11' Graphic character set supported in a font character set
X'12' Specific code page (required for LF3 and LF4)
X'20' Saved page group
X'40' Data object resource
X'41' Data-object font
X'42' Data-object-font component
X'FF' All resources (known by the Resource ID format specified in byte 7).
Exception ID X'0291..02' exists if an invalid resource-type value is specified.
If LF1 or LF2 is supported, a resource type of X'01' with a resource ID format of X'00'
(specified in byte 7) must be supported; supported means the printer returns a non-zero
resource type in the RRL reply. If double-byte coded fonts are supported, a resource type of
X'02' with a resource ID format of X'00' must be supported.
If LF3 is supported, a resource type of X'06', X'07', X'10', and X'12' with a resource ID format
of X'00' must be supported. If the Page-Segment command set is supported, a resource type
of X'04' with a resource ID format of X'00' must be supported. If the Overlay command set is
supported, a resource type of X'05' with a resource ID format of X'00' must be supported.
Usually, a query of any resource type is valid, but restrictions apply for some printers. Refer to
your printer documentation for restrictions. If the resource type value is an unknown or
unsupported value, the reply sets the resource type to zero, echoes all other values, and
indicates that the resource is not present and cannot be activated by setting the resource size
byte (byte 4) in the Request Resource List reply to zero. [IPDS-4-1074]


The printer identifies supported queries in the XOA-RRL RT & RIDF Support self-defining field
in an XOH-OPC reply. Table 29shows the architecturally-valid RT and RIDF combinations:
**Table 29. Architecturally-Valid RT and RIDF Query Combinations** [IPDS-4-1075]

| RT | RIDF | Individual Query Resource | ID Length |
| :--- | :--- | :--- | :--- |
| X'01' | Single-byte LF1-type or LF2-type coded-font | X'00' HAID format | X'02' [IPDS-4-1076]|
| | | X'03' GRID-parts format | X'08' [IPDS-4-1077]|
| X'02' | Double-byte LF1-type coded-font | X'00' HAID format | X'02' [IPDS-4-1078]|
| | | X'03' GRID-parts format | X'08' [IPDS-4-1079]|
| X'03' | Double-byte LF1-type coded-font section | X'00' HAID format | X'03' [IPDS-4-1080]|
| | | X'03' GRID-parts format | X'09' [IPDS-4-1081]|
| X'04' | Page segment | X'00' HAID format | X'02' [IPDS-4-1082]|
| X'05' | Overlay | X'00' HAID format | X'02' [IPDS-4-1083]|
| X'06' | Device-version code page | X'00' HAID format | X'02' [IPDS-4-1084]|
| | | X'03' GRID-parts format | X'02' [IPDS-4-1085]|
| X'07' | Font character set | X'00' HAID format | X'02' [IPDS-4-1086]|
| | | X'03' GRID-parts format | X'06' [IPDS-4-1087]|
| X'08' | Single-byte raster, single-byte outline, or double-byte outline coded-font FIS | X'00' HAID format | X'02' or X'04' [IPDS-4-1088]|
| X'09' | Double-byte coded-font section index | X'00' HAID format | X'03' or X'05' [IPDS-4-1089]|
| X'10' | Coded font | X'00' HAID format | X'02' [IPDS-4-1090]|
| | | X'03' GRID-parts format | X'08' [IPDS-4-1091]|
| X'11' | Graphic character set supported in a font character set | X'03' GRID-parts format | X'02' [IPDS-4-1092]|
| X'12' | Specific code page | X'00' HAID format | X'02' [IPDS-4-1093]|
| | | X'03' GRID-parts format | X'04' [IPDS-4-1094]|
| X'20' | Saved page group | X'08' Variable-length Group ID (X'00') triplet format | X'02'–X'F7' [IPDS-4-1095]|
| X'40' | Data object resource | X'00' HAID format | X'02' [IPDS-4-1096]|
| | | X'09' Object-OID format | X'02'–X'81' [IPDS-4-1097]|
| X'41' | Data-object font | X'00' HAID format | X'02' [IPDS-4-1098]|
| X'42' | Data-object-font component | X'00' HAID format | X'02' [IPDS-4-1099]|
| | | X'09' Object-OID format | X'02'–X'81' [IPDS-4-1100]|
| X'FF' | All resources | X'00' HAID format | N/A [IPDS-4-1101]|


Byte 7 Resource ID format
This byte describes the format of the Resource ID (bytes 8 through end of entry) for the
Resource Type (byte 6). Exception ID X'0291..02' exists if an invalid resource-ID-format value
is specified.
RIDF = X'00' Host-assigned Resource ID
• Single-byte LF1-type or LF2-type coded font (RT = X'01') [IPDS-4-1102]
Two-byte coded font Host-Assigned ID from one of the following:
– An Activate Resource command
– A Load Font Control command
– A Load Font Equivalence command
– A Load Symbol Set command
• Double-byte LF1-type coded font (RT = X'02') [IPDS-4-1103]
Two-byte coded font Host-Assigned ID from a Load Font Control command.
• Double-byte LF1-type coded-font section (RT = X'03') [IPDS-4-1104]
Three-byte ID consisting of a two-byte coded font Host-Assigned ID
followed by a one-byte coded font section ID from one of the following:
– An Activate Resource command
– A Load Font Control command
• Page segment (RT = X'04') [IPDS-4-1105]
Two-byte page segment Host-Assigned ID from one of the following:
– An Activate Resource command
– A Begin Page Segment command
• Overlay (RT = X'05') [IPDS-4-1106]
Two-byte overlay Host-Assigned ID from one of the following:
– An Activate Resource command
– A Begin Overlay command (overlay HAID or overlay ID preceded by
X'00')
• Code page (RT = X'06' or X'12') [IPDS-4-1107]
Two-byte code page Host-Assigned ID from one of the following:
– An Activate Resource command
– A Load Code Page Control command
• Font character set (RT = X'07') [IPDS-4-1108]
Two-byte font character set Host-Assigned ID from one of the following:
– An Activate Resource command
– A Load Font Character Set Control command
• Single-byte raster, single-byte outline, or double-byte outline coded-font FIS [IPDS-4-1109]
(RT = X'08')
Four-byte ID consisting of a two-byte coded font Host-Assigned ID followed
by the two-byte coded font inline sequence value from one of the following:
– An Activate Resource command
– A Load Font Index command [IPDS-4-1110]


• All FISes of a particular single-byte raster, single-byte outline, or double- [IPDS-4-1111]
byte outline coded font (RT = X'08')
Two-byte coded font Host-Assigned ID from one of the following:
– An Activate Resource command
– A Load Font Control command
Notes:
1. The reply to this query consists of zero or more entries, each entry [IPDS-4-1112]
describing a particular font inline sequence.
2. For a list query, the reply contains a list of all present single-byte raster [IPDS-4-1113]
indexes for all present single-byte fonts; the list does not contain
information about any outline FISes.
• Double-byte coded-font index (RT = X'09') [IPDS-4-1114]
Five-byte ID consisting of a two-byte coded font Host-Assigned ID followed
by a one-byte coded font section ID followed by the two-byte coded font
inline sequence value of the particular index from one of the following:
– An Activate Resource command
– A Load Font Index command
• All the indexes of a particular double-byte coded font (RT = X'09') [IPDS-4-1115]
Three-byte ID consisting of a two-byte coded font Host-Assigned ID
followed by a one-byte coded font section ID from one of the following:
– An Activate Resource command
– A Load Font Control command
Note: The reply to this query consists of zero or more entries, each entry
describing a particular double-byte coded font section index as if that
individual font index had been queried. For a list query, a list of all
present indexes for all present double-byte font sections is returned.
• Coded font (RT = X'10') [IPDS-4-1116]
Two-byte coded font Host-Assigned ID from one of the following:
– An Activate Resource command
– A Load Font Equivalence command
– A Load Font Control command
– A Load Symbol Set command
• Data object resource (RT = X'40') [IPDS-4-1117]
Two-byte data object resource Host-Assigned ID from one of the following:
– An Activate Resource command
– A home state Write Image Control 2 command
– A home state Write Object Container Control command
• Data-object font (RT = X'41') [IPDS-4-1118]
Two-byte data-object font Host-Assigned ID from an Activate Resource
command
• Data-object-font component (RT = X'42') [IPDS-4-1119]
Two-byte data-object-font-component Host-Assigned ID from one of the
following:
– An Activate Resource command
– A home state Write Object Container Control command [IPDS-4-1120]


RIDF = X'01',
X'02'
Retired items 28 and 29
Implementation Note: The IBM 3820 printer allows only the value X'00' for
the RIDF in an XOA-RRL request, but returns one of the following
values in the reply:
X'00' Downloaded resource; not present in the printer
X'01' Downloaded resource; in control storage
X'02' Downloaded resource; in pattern storage.
RIDF = X'03' GRID-parts format
Refer to bytes 5–12 of the LFE command for an explanation of
the acronyms used in the following description. This naming convention must
be supported for all resident (not downloaded) code pages and font character
sets, and for coded fonts.
• Use either two bytes or four bytes to select a code page: [IPDS-4-1121]
– For a specific code page (RT = X'12'), specify a two-byte GCSGID
followed by a two-byte CPGID.
– For a device version of the code page (RT = X'06'), specify a two-byte
CPGID. A device version of a code page contains all of the characters
that were registered for the CPGID at the time the printer was developed;
more characters might have been added to the registry for that CPGID
since that time.
• Use six bytes to select a font character set (RT = X'07'), [IPDS-4-1122]
GCSGID+FGID+FW.
For general queries, a font character set extension does not affect the reply.
However, an activation query (type X'05') indicates that a parent FCS is not
activated whenever the parent has been temporarily extended.
• Use eight bytes to select a coded font [IPDS-4-1123]
(RT = X'01', X'02', X'10'), GCSGID+CPGID+FGID+FW.
• Use nine bytes to select a double-byte LF1-type coded-font section (RT = [IPDS-4-1124]
X'03'), GCSGID+CPGID+FGID+FW + section ID.
• Use two bytes to select a graphic character set (RT = X'11') supported in a [IPDS-4-1125]
resident font character set. A list query for a graphic character set using the
GRID-parts format returns a series of XOA-RRL reply entries, each of which
contains a GCSGID that is supported in a resident font character set
followed by all GCSGIDs that are supported as proper subsets of the first
GCSGID. If the list of subsets is too large to fit into one RRL reply entry, the
list is returned in multiple entries, each one containing the original GCSGID
followed by the GCSGIDs of additional proper subsets.
Refer to the description of the GRID for the meaning and valid
ranges for these values.
The resource size byte (byte 4) in the Request Resource List reply is set to
zero if the resource type value does not indicate a code page, font character
set, or coded font selection.
RIDF = X'06' Retired item 30
RIDF = X'08' Variable-length Group ID (X'00') triplet
This triplet is described in “Group ID (X'00') Triplet”. [IPDS-4-1126]


RIDF = X'09' Object-OID format
This naming format is used to uniquely identify resident data object resources
and resident data-object-font components. An OID is a variable length (2 to
129 bytes) binary ID that uniquely identifies an object. OIDs use the ASN.1 [IPDS-4-1127]
definite-short-form object identifier format defined in the ISO/IEC 8824:1990
(E) international standard and described in the MO:DCA Registry Appendix of
the Mixed Object Document Content Architecture Reference. The syntax of
an OID is as follows:
Offset Type Name Range Meaning
0 CODE Identifier X'06' This is a definite-short-form OID [IPDS-4-1128]
1 UBIN Length X'00'–X'7F' Length of the following content bytes [IPDS-4-1129]
2 to
end
Content Any value Content bytes that provide a unique ID for this object
If LF1 or LF2 is supported, a resource ID format of X'00' with a resource type of X'01'
(specified in byte 6) must be supported; supported means the printer returns a non-zero
resource type in the RRL reply. If double-byte coded fonts are supported, a resource ID format
of X'00' with a resource type of X'02' must be supported. If LF3 is supported, a resource ID
format of X'00' with a resource type of X'06', X'07', and X'10' must be supported. If the Page-
Segment command set is supported, a resource ID format of X'00' with a resource type of
X'04' must be supported. If the Overlay command set is supported, a resource ID format of
X'00' with a resource type of X'05' must be supported.
Usually, a query of any resource ID format is valid, but restrictions apply for some printers.
Refer to your printer documentation for restrictions. If the resource ID format is unknown or
unsupported, the reply sets the resource type to zero, echoes all other values, and indicates
that the resource is not present and cannot be activated by setting byte 4 of the RRL reply to
zero.
Bytes 8 to end
of entry
Resource ID
These bytes are mandatory for query type X'05' and optional for all other query types. If this
field is present, it must contain the ID of the resource type specified in byte 6 in the format
specified in byte 7. The absence of this field is a request for a list of all resources of the
specified type and ID format. Query type X'05', activation query, can only be used to query an
individual resource, not a list of resources of a specified type and ID format.
In general, resident resources that are not activated can be removed by the printer at any
time; however, activated resources cannot be removed during the IPDS session. Also, once a
resource has been listed in an XOA-RRL reply, the resource can only be removed during the
IPDS session with the Remove Resident Resource (RRR) command.
A resident resource can be installed at any time, either through resource capture or through an
install process at the printer. There is no notification to the presentation services program
when a resource is installed.
Usually, a query of any resource ID is valid, but restrictions apply for some printers. Refer to
your printer documentation for restrictions. If the resource ID is inconsistent with what the
printer expects based on the resource type (byte 6) and resource ID format (byte 7), the reply
sets the resource type to zero, echoes all other values, and indicates that the resource is not
present and cannot be activated by setting the resource size byte to zero. [IPDS-4-1130]


Resource List Reply
Resource list replies are categorized as follows:
Query type X'05'
• Reply to a query of an individual resource. There is one reply for each query entry: [IPDS-4-1131]
– If the query is understood and the resource is activated, the reply sets the resource type, the resource ID
format, and the resource ID, and it sets the resource size to a nonzero value.
– If the query is understood and supported but the resource is not activated, the reply entry echoes the
resource type, the resource ID format, and the resource ID, and it sets the resource size to zero.
– If the query is not understood or not supported, the reply sets the resource type to zero, echoes the other
values, and it sets the resource size to zero.
Other query types
• Reply to a query of an individual resource: [IPDS-4-1132]
– If the query is understood and the resource is present, the reply sets the resource type, the resource ID
format, and the resource ID, and it sets the resource size to a nonzero value.
– If the query is understood and supported but the resource is not present, the reply entry echoes the
resource type, the resource ID format, and the resource ID, and it sets the resource size to zero.
– If the query is not understood or not supported, the reply sets the resource type to zero, echoes the other
values, and it sets the resource size to zero.
• Reply to a query of a list of resources: [IPDS-4-1133]
– If the query is understood and one or more of the resources in the list are present, the reply is a sequence
of entries with one entry for each resource present. Each entry is as if an individual resource has been
queried. The completion of a list reply is indicated by a final entry having an entry length of 1.
Note: The reply to a list query includes only permanent resident resources. Transient resources are not
returned in the reply to a list query.
– If the query is understood but none of the resources in the list is present, the reply is a single entry that
echoes the resource type, the resource ID format (and for a query of all the indexes of a particular coded
font, the resource ID) and sets the resource size to zero.
– If the query is not understood, the reply is a single entry that sets the resource type to zero, echoes other
values, and sets the resource size to zero.
– If the query is understood but a list query of this resource type is not supported, the reply is a single entry
that sets the resource type to X'FF', echoes other values, and sets the resource size to zero.
Note: More than one version of a resident resource can exist in a printer or intermediate device, for example:
• Multiple versions of a font character set or code page with different date and time stamps [IPDS-4-1134]
• Multiple versions of a raster font with different resolutions or different date and time stamps [IPDS-4-1135]
A RRL query for an individual resource using the GRID-parts format returns an RRL reply for only one of
the multiple versions. To single out a specific one of multiple versions, use the AR command to identify
the specific version of the resource to be activated and then query using the XOA-RRL command with a
Host-assigned resource ID format. [IPDS-4-1136]


When the reply data is larger than fits in one Acknowledge Reply (that is, when there are more than 256 bytes
of reply data), a sequence of Acknowledge Replies can be obtained using one of two methods:
1. RRL-continuation method [IPDS-4-1137]
In this case, the special data area of each individual Acknowledge Reply begins with an ordering field (byte
0); the first in the sequence then continues with the first resource list reply entry. The special data area of
each subsequent Acknowledge Reply in the sequence starts with an ordering field and then continues
where the previous Acknowledge Reply left off. A resource list reply entry (bytes 1 to end of entry) can be
split between Acknowledge Replies at any byte boundary.
2. ACK-continuation method [IPDS-4-1138]
In this case, the special data area of each individual Acknowledge Reply continues where the previous
Acknowledge Reply left off. The ordering field is present in the first reply of the sequence, but is not
repeated in each subsequent reply. A resource list reply entry (bytes 1 to end of entry) can be split between
Acknowledge Replies at any byte boundary.
The first reply in the series has the following format:
Offset Type Name Range Meaning DC1 Range
0 CODE Ordering [IPDS-4-1139]
X'FF'
Ordering sequence of entries that follow:
Printer-defined sequence X'FF'
One or more resource list reply entries in the following format:
1 UBIN Length X'01' [IPDS-4-1140]
X'04' to end of
entry
End of list indicator
Length of this entry, including this length field
X'01'
X'04'–X'06'
2 CODE RT X'00'–X'FF' Resource type of this entry Any type queried [IPDS-4-1141]
3 CODE RIDF X'00'–X'FF' Resource ID format for this entry Any ID format [IPDS-4-1142]
queried
4 CODE Size [IPDS-4-1143]
X'00'
X'01'–X'FF'
Resource size indicator of this entry:
Query type X'05': Resource not activated
Other query types: Resource not present
Query type X'05': Resource activated
Other query types: Resource present
X'00'
At least one
nonzero value
5 to
end of
entry
Resource ID Any value Resource ID for this entry Any ID queried
Byte 0 Ordering sequence
This field identifies the ordering sequence for the entries in the RRL reply. Most printers return
the entries in the same sequence that they appeared in the query. Entries returned for a list
query are returned in a printer-defined sequence.
Byte 1 Entry length
This byte specifies the length of this entry, including itself. Therefore, this byte indicates the
location of either the end of the reply or the entry length of the next entry. A length of X'01'
indicates the end of the list; this value is only returned for a list query and is not used with
single-resource queries.
**Table 29** lists the currently valid resource ID lengths. Additional RT/RIDF
combinations might be added in the future and therefore all values in the range X'01' and
X'04'–X'FF' should be accepted for this byte. [IPDS-4-1144]


Byte 2 Resource type
This byte specifies the following:
X'00' The query is unsupported or inconsistent
X'01' Single-byte LF1-type or LF2-type coded-font components
X'02' Double-byte LF1-type coded-font components
X'03' Double-byte LF1-type coded-font-section components
X'04' Page segment
X'05' Overlay
X'06' Device-version code page
X'07' Font character set
X'08' Single-byte raster, single-byte outline, or double-byte outline coded-font FIS
X'09' Double-byte coded-font section index
X'10' Coded font
X'11' Graphic character sets supported in a font character set
X'12' Specific code page
X'20' Saved page group
X'40' Data object resource
X'41' Data-object font
X'42' Data-object-font component
X'81'–X'89'
Retired item 128
X'FF' Resource size=0 reply because the list query (although understood) is not supported.
Query of this resource type and resource ID format (one at a time) is supported.
Byte 3 Resource ID format
If the Resource ID format in the query was X'00', the query is understood, and the resource is
present; the Resource ID format in the reply is either X'01' or X'02'.
If the Resource ID format in the query was X'01' or X'02', the Resource ID format in the reply
echoes the query and the resource size=X'00'.
If the Resource ID format in the query was between X'03' through X'FF', the Resource ID
format in the reply echoes the query.
In all cases, when the query is unsupported, the resource type is set to X'00', the Resource ID
format echoes the query, and the resource size is set to X'00'.
If the query is understood and supported but the resource is not present, the reply entry
echoes the resource ID format from the query.
Byte 4 Resource size
A value of 0 indicates the resource is not present in the printer. A non-zero value is meant to
indicate the printer's best estimate of the relative size of the resource.
Relative size means that, for the duration of a given host session, larger values always
indicate larger resources (within the granularity of the printer's sizing scheme). For example, a
resource with a resource size = 2 is larger, but not necessarily twice as large as a resource
with a resource size = 1.
If no sizing is done by the printer, the contents of this byte should always be the same, non-
zero value.
Bytes 5 to end
of entry
Resource ID
This field contains the resource ID of a queried resource of the type specified in byte 2 and in
the format specified in byte 3. [IPDS-4-1145]


For an outline coded font or outline font character set identified by a GRID-parts RIDF , X'0000'
is returned for the font-width component to indicate that the coded font is scalable to any font
width.
For a GRID-parts query that specifies X'FFFF' (default) for one or more of the GRID parts,
X'FFFF' may be echoed in the RRL reply or the current printer-default GRID-part value may be
returned.
For a saved page group, the resource ID in the reply consists of the variable-length Group ID
(X'00') triplet followed by the sequence number of the last page that was saved in the group. A
saved page group exists when an XOH-DGB command with a Variable-length group ID has
been processed for a group to be saved. If a query is done before the first page has been
saved, a sequence number of X'00000000' is returned in the XOA-RRL reply indicating an
empty group.


XOA Request Setup Name List
The XOA Request Setup Name List (RSNL) command requests that the printer return information about the
setup names supported by the printer. The printer responds by placing available information, if any, in the
special data area of a subsequent Acknowledge Reply (or in a series of replies). If the RSNL command is not
sent with the Acknowledgement Required (ARQ) flag bit set, the command is treated as a No Operation (NOP)
command.
The length of the XOA-RSNL command can be:
Without CID X'000B' or X'0011'–X'FFFF' odd values
With CID X'000D' or X'0013'–X'FFFF' odd values
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
It is important to realize that the returned setup name information is true only as of the time it is returned. At
any time, new setup names can be added by an operator, or existing setup names can be deleted, or any
information about the setup names can change. The only information in this reply that cannot change without a
NACK is the state and identity of the active setup name; if that information changes, exception ID X'010A..00'
exists.
Support for this optional command is indicated by the X'80FA' property pair in the Device-Control command-set
vector in an STM reply. Support for returning detailed settings about a setup name is indicated by the X'F403'
property pair in the Device-Control command-set vector in an STM reply. [IPDS-4-1146]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'FA00' | Request Setup Name List (RSNL) order code | X'FA00' [IPDS-4-1147]|
| 2 | CODE | Query type | X'00'<br>X'01' | Active setup name only<br>Setup name list | X'00'<br>X'01' [IPDS-4-1148]|
| 3 | BITS | Setup name information requested [IPDS-4-1149]| | | |
| | bit 0 | Activation possibility | B'0', B'1' | Return whether activation of the setup name is possible with the ASN command | B'0', B'1' [IPDS-4-1150]|
| | bit 1 | Detailed settings | B'0', B'1' | Return detailed settings for each setup name | B'0', B'1' [IPDS-4-1151]|
| | bits 2–7 | | B'000000' | Reserved | B'000000' [IPDS-4-1152]|
| 4–5 | | Reserved | X'0000' | Reserved | X'0000' [IPDS-4-1153]|
| 6 to end of RSNL [IPDS-4-1154]| | | | | |
end of
RSNL
Triplets Zero or more triplets:
X'9E' Setup Name triplet
Bytes 0–1 RSNL order code
Byte 2 Query type
This field specifies the type of query being made, in terms of the setup names that should be
reported in the reply.
X'00' The reply will only report the active setup name. In this case, bytes 3–5 should be
specified as X'000000', and bytes 3 through the end of the RSNL are ignored by the
printer.
X'01' The reply will report the active setup name but will also include a list of setup names,
tailored to the contents of bytes 3 through the end of the RSNL.
If an invalid value is specified, exception ID X'02A2..00' exists. [IPDS-4-1155]


Byte 3 Setup name information requested
This byte contains flags that define the information that is being requested about each setup
name returned. If no flags are set, only a Setup Name (X'9E') triplet is returned for each setup
name.
Bit 0 Return activation possibility
If set to B'1', this flag requests that the printer return a prediction as to
whether the setup name could be activated at this time by the ASN command,
based on the current configuration of the printer.
Bit 1 Return detailed settings
If set to B'1', this flag requests that the printer return information about the
detailed settings that are encompassed by the setup name.
Some printers do not support returning detailed settings information; such
printers will ignore this bit. Support for returning detailed settings is indicated
by the X'F403' property pair in the Device-Control command-set vector in an
STM reply.
Bits 2–7 Reserved
Bytes 4–5 Reserved
Bytes 6 to end Triplets
Any number of Setup Name (X'9E') triplets can be specified. If zero such triplets are specified,
this indicates a request for the printer to return all setup names supported by the printer. If one
or more triplets are specified, only the setup names in the specified triplets will be returned,
and only if they are supported by the printer; such a command is thus a query for the
existence, and possibly corresponding information, about the setup names included in the
specified triplets.
If any triplets other than the Setup Name (X'9E') triplet are found, they should be ignored and
no exception is reported. If byte 6 or the first byte after a valid triplet is X'00' or X'01' (an invalid
triplet length), the printer ignores the remaining data within the optional triplets field.
The triplet is fully described in the triplets chapter:
“Setup Name (X'9E') Triplet
”


RSNL Reply
An RSNL reply returns information about the setup names defined on the printer. This information is tailored to
the request and can thus vary considerably in format from one reply to another.
If the information does not fit into one single acknowledge reply, a sequence of acknowledge replies can be
obtained using the ACK-continuation method. In this case, the special data area of each individual
acknowledge reply continues where the previous acknowledge reply left off—no fields of the RSNL reply are
repeated. Such acknowledge replies can be split at any byte boundary.
The special data area of an RSNL reply has the following format: [IPDS-4-1156]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–3 | UBIN | Length | X'00000007'<br>X'0000000D'<br>X'0000000F'<br>X'00000011' – X'FFFFFFFF' | Length of reply data, including this field [IPDS-4-1157]|
| 4–5 | | Reserved | X'0000' | Reserved [IPDS-4-1158]|
| 6 | BITS | Setup name information returned [IPDS-4-1159]| | |
bit 0 Active setup
name exists
B'0'
B'1'
No active setup name exists
An active setup name exists
bit 1
Activation
possible returned
B'0'
B'1'
No activation possibility information is returned
Activation possibility information is returned
bit 2
Detailed settings
returned
B'0'
B'1'
No detailed settings are returned
Detailed settings are returned
bits 3–7
B'00000' Reserved
7 to end [IPDS-4-1160]
of triplet
Triplet Zero or one Setup Name (X'9E') triplet
Zero or more Setup Name Information entries in the following format:
+0 to m Triplet One Setup Name (X'9E') triplet
+ m+1 CODE Activation
possible
X'00'
X'01'
X'02'
No
Yes
Unknown
+ m+2–
m+5
UBIN DetailsLength See byte
description
Length of detailed settings
+ m+6–n Details See byte
description
Detailed settings encompassed by the setup name
+ n+1–
n+2
UBIN AddlInfoLength X'0002' Length of additional info, including this field
+ n+3 to
end of
addl info
AddlInfo See byte
description
Additional information about the setup name (reserved
for future use)
Bytes 0–3 Length
This field contains the total length of the data, including the length field itself. This length might
be more than can be contained in the special data area of one Acknowledge Reply, in which
case the additional bytes are obtained using the ACK-continuation method.
Bytes 4–5 Reserved


Byte 6 Setup name information returned
This byte contains flags that define the information that is being returned in the RSNL reply.
Because many fields are optional, this byte is required to be able to correctly read the reply.
Bit 0 Active setup name exists
B'0' There is no active setup name on the printer. In this case, there must
be no triplet returned in bytes 7–n, and the Setup Name Information
entries starting in byte +0, if any, will directly follow this byte.
B'1' The active setup name is returned in bytes 7–n.
Bit 1 Activation possibility is returned
B'0' No activation possibility information was requested with the
activation-possibility flag in the RSNL command and therefore no
such information is being returned in this reply. In this case, the
activation-possible field in byte +m+1 is omitted in every Setup Name
Information entry.
B'1' Activation possibility information is returned in the activation-possible
field of each Setup Name Information entry.
Bit 2 Detailed settings are returned
B'0' No detailed settings are being returned in this reply. This could be
because no detailed settings information was requested with the
detailed-settings flag in the RSNL command or could be because the
printer does not support returning detailed settings. In this case, the
DetailsLength field in bytes +m+2–m+5 and Details field in bytes +m
+6–n are omitted in every Setup Name Information entry.
B'1' Detailed settings are returned in the DetailsLength and Details fields
of each Setup Name Information entry.
Bits 3–7 Reserved
Bytes 7 to end
of triplet
One Setup Name (X'9E') triplet is returned to indicate the active setup name.
In the case that there is no active setup name, this field is omitted and bit 0 of byte 6 must be
B'0'.
A printer must ensure that the active setup name being reported here is truly active on the
printer. For example, if a setup name has been activated, but then any setting that is
encompassed by that setup name is changed, in any way and for whatever reason, the setup
name must be considered no longer activated.
If the active setup name changes, exception ID X'010A..00' exists; examples of changes that
would cause X'010A..00':
• A change from one active setup name to another active setup name [IPDS-4-1161]
• A change from no active setup name to an active setup name [IPDS-4-1162]
• A change from an active setup name to no active setup name [IPDS-4-1163]
• A change from an active setup name to the same active setup name but the settings [IPDS-4-1164]
corresponding to the setup name have been modified, for example if an operator edits the
active setup name’s definition
Note that exception ID X'010A..00' is not reported for changes due to the ASN command.
The triplet is fully described in the triplets chapter:
“Setup Name (X'9E') Triplet
”


Setup Name Information entry
Zero or more Setup Name Information entries return the setup name information requested in the RSNL
command.
If the RSNL command was a query for active setup name only, no Setup Name Information entries will be
returned.
If the RSNL command was a query for a setup name list and included no Setup Name (X'9E') triplets, this reply
will include one Setup Name Information entry for each supported setup name on the printer. If instead such a
query included one or more Setup Name (X'9E') triplets, this reply will only include a Setup Name Information
entry for each of the provided setup names that are supported by the printer.
Only the first field (bytes +0–m) and Additional information length field (bytes +n+1–n+2) of the Setup Name
Information entry are always returned; byte 6 of this reply, as well as the length fields in the Setup Name
Information entry, report on whether the other, optional fields are returned in the Setup Name Information
entries in this reply.
Bytes+0–m One Setup Name (X'9E') triplet is returned to indicate a supported setup name.
The triplet is fully described in the triplets chapter:
“Setup Name (X'9E') Triplet
”
Byte +m+1 Activation possible
This field states whether the setup name from bytes +0–m could be activated at this time using
the ASN command. Note the important words “at this time”: the ASN command will work as
described by this field only it if is called on a printer that has had no consequential
configuration changes between the start of the RSNL processing and the time the ASN
command is processed.
If no activation possibility information was requested using the activation-possibility flag in the
RSNL command, this field is omitted and bit 1 of byte 6 must be B'0'.
X'00' The setup name would require operator intervention and thus cannot be activated with
the ASN command.
X'01' Calling the ASN command to activate the setup name would succeed.
X'02' It is unknown whether the ASN could successfully activate this setup name. Some
printers might not have the ability to predict with accuracy whether a given setup
name activation would require operator intervention, or might only have that ability for
certain setup names, such as setup names that represent settings that are very
different from or very similar to the current configuration.
Bytes +m+2–
m+5
Detailed settings length
This field contains the length of the detailed settings data, including the length field itself. It is
possible that certain setup names might have no detailed settings data, in which case this
length will be X'00000004'.
If detailed settings were not requested using the detailed-settings flag in the RSNL command,
or if the printer does not support returning detailed settings, this field is omitted and bit 2 of
byte 6 must be B'0'.
Support for returning detailed settings is indicated by the X'F403' property pair in the Device-
Control command-set vector in an STM reply. [IPDS-4-1165]


Bytes +m+6–n Detailed settings
This field contains the detailed settings data for the setup name from bytes +0–m.
If detailed settings were not requested using the detailed-settings flag in the RSNL command,
or if the printer does not support returning detailed settings, this field is omitted and bit 2 of
byte 6 must be B'0'.
The detailed settings data is printer specific, but is structured such that the data can be
displayed to the user of any presentation services program. The data is any number of key/
value pairs, where each of the keys and each of the values are UTF-16BE character strings.
The key corresponds to the name of a setting, while the value corresponds to the value of that
setting. An example: key is “Resolution”, value is “600dpi”.
A key name must be at least one character long, but the value string can be size zero.
The format of the detailed settings information is as follows:
Offset Type Name Range Meaning
Zero or more Detailed Settings entries in the following format:
++ 0–1 UBIN Key length X'0004' –
X'FFFF'
Length of the key, including this
length field
++ 2–k CHAR Key name any UTF-
16BE
character
Key name as a UTF-16BE
character string
++ k+1–k+2
UBIN Value length X'0002' –
X'FFFF'
Length of the value, including
this length field
++ k+3 to end
of value
CHAR Value any UTF-
16BE
character
Value as a UTF-16BE character
string
Bytes +n+1–
n+2
Additional information length
This field contains the length of the additional information about the setup name, including the
length field itself. Because the additional information field is reserved for future use, this length
field should be specified as X'0002', indicating there is no additional information.
Bytes +n+3 to
end of addl
info
Additional information
This is a reserved field that might be used for future expansion. Receivers of this reply should
accept, but ignore this field; senders of this reply should not specify this field. [IPDS-4-1166]


Execute Order Home State
```
Length X'D68F' Flag CID Data
```
The Execute Order Home State (XOH) command identifies a set of orders that may be received only when the
printer is in home state.
Each XOH command consists of a two-byte order code followed by 0 or more parameter bytes. Each XOH
command can contain only one order. The format for each command is:
Code Parameters (if any)
In alphabetic sequence, the orders are: [IPDS-4-1167]

**Table 30. XOH Order Summary** [IPDS-4-1168]

| Code | Order | In DC1 subset? |
| :--- | :--- | :--- |
| X'0200' | XOH Deactivate Saved Page Group | No [IPDS-4-1169]|
| X'0400' | XOH Define Group Boundary | No [IPDS-4-1170]|
| X'1300' | XOH Eject to Front Facing | No [IPDS-4-1171]|
| X'0700' | XOH Erase Residual Font Data | No [IPDS-4-1172]|
| X'0500' | XOH Erase Residual Print Data | No [IPDS-4-1173]|
| X'F300' | XOH Obtain Printer Characteristics | Yes [IPDS-4-1174]|
| X'F500' | XOH Page Counters Control | No [IPDS-4-1175]|
| X'0100' | XOH Print Buffered Data | Yes [IPDS-4-1176]|
| X'0A00' | XOH Remove Saved Page Group | No [IPDS-4-1177]|
| X'1500' | XOH Select Input Media Source | No [IPDS-4-1178]|
| X'0E00' | XOH Select Medium Modifications | No [IPDS-4-1179]|
| X'0900' | XOH Separate Continuous Forms | No [IPDS-4-1180]|
| X'1600' | XOH Set Media Origin | No [IPDS-4-1181]|
| X'1700' | XOH Set Media Size | No [IPDS-4-1182]|
| X'0300' | XOH Specify Group Operation | No [IPDS-4-1183]|
| X'0D00' | XOH Stack Received Pages | No [IPDS-4-1184]|
| X'F200' | XOH Trace | No [IPDS-4-1185]|
| **Retired Order Codes** [IPDS-4-1186]| | |
| X'0000' | Retired item 31 | No [IPDS-4-1187]|
| X'0B00' | Retired item 32 | No [IPDS-4-1188]|
| X'1C00' | Retired item 144 | No [IPDS-4-1189]|
| X'1D00' | Retired item 145 | No [IPDS-4-1190]|
| X'4C00' | Retired item 146 | No [IPDS-4-1191]|
| X'4D00' | Retired item 147 | No [IPDS-4-1192]|
| X'4E00' | Retired item 148 | No [IPDS-4-1193]|
| X'D000' | Retired item 127 | No [IPDS-4-1194]|
| X'F400' | Retired item 33 | No [IPDS-4-1195]|
Unknown or unsupported orders are treated as No Operation (NOP) commands. [IPDS-4-1196]


XOH Deactivate Saved Page Group
The XOH Deactivate Saved Page Group (DSPG) command directs the printer to deactivate one or more
previously saved page groups.
The length of the XOH-DSPG command can be:
Without CID X'0007' or X'0009'–X'7FFF'
With CID X'0009' or X'000B'–X'7FFF'
However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported.
The groups to be deactivated are identified by Group ID (X'00') triplets containing a variable-length group ID. If
no triplets are specified, all open saved page groups are terminated and all currently active saved page groups
are deactivated; this is a deactivate all function. A deactivate-all command when there are no active saved
page groups is effectively a NOP.
Deactivating a saved page group also terminates the DGB group (if it was not already terminated) and
terminates all DGB groups with lesser group levels that are nested within the group to be deactivated.
Only saved page groups specified in the XOH-DSPG command are deactivated; other saved page groups,
including those created by DGB nesting, are not automatically deactivated.
This command directs the printer to deactivate one or more saved page groups, but does not directly cause the
group to be removed. The printer can either remove the group immediately or keep it around until space is
needed. In the second case, the printer must keep track of page includes from this group, the first time a page
from the group is called for in an ISP command, the group is activated and can no longer be removed. The
XOA-RRL command can be used to find out what saved page groups the printer currently has. Using the XOA-
RRL command to query a saved page group does not activate the group, but it does alert the printer that the
group is likely to be activated soon. [IPDS-4-1197]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0200' | Deactivate Saved Page Group (DSPG) order code | X'0200' [IPDS-4-1198]|
| 2 to end | Triplets | | | Zero or more Group ID (X'00') triplets:<ul><li>X'00' Group ID triplet with variable-length group ID</li></ul> [IPDS-4-1199]| |
The Deactivate Saved Page Group triplets are fully described in the triplets chapter:
“Group ID (X'00') Triplet” [IPDS-4-1200]


Group ID (X'00') Triplet Considerations
This portion of the XOH-DSPG command contains zero or more Group ID (X'00') triplets that specify which
saved page groups to deactivate. If no triplets are specified, all open saved page groups are terminated and all
saved page groups are deactivated; this is a deactivate all function. A deactivate-all command when there are
no active saved page groups is effectively a NOP.
The groups to be deactivated are identified by Group ID (X'00') triplets containing a variable-length group ID. If
the printer does not find the saved page group identified by a Group ID (X'00') triplet, exception ID X'0255..07'
exists.
Exception ID X'0255..08' exists if any of the following occurs in the triplets field:
• Byte 2 or the first byte after a valid triplet was X'00' or X'01' (an invalid triplet length). [IPDS-4-1201]
• A triplet other than a Group ID (X'00') triplet was specified. [IPDS-4-1202]
• A Group ID (X'00') triplet without a variable-length group ID was specified. [IPDS-4-1203]


XOH Define Group Boundary
The XOH Define Group Boundary (DGB) command initiates or terminates a grouping of pages. When a
grouping is initiated, the sheet containing the first copy of the next-received page, that is, the page that next
increments the received page counter, is the first sheet in the designated group. When a grouping is
terminated, the sheet containing the last copy of the last-received page is the last sheet in the designated
group.
The length of the XOH-DGB command can be:
Without CID X'0009' or X'000B'–X'7FFF'
With CID X'000B' or X'000D'–X'7FFF'
However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported.
If the first page of a group is discarded by any means, the group is terminated and information concerning the
group is discarded. If a middle or last page of a group is discarded but the first page of the group is kept, the
grouping is not complete and therefore, all subsequently received pages are part of the group until the group is
terminated. Thus, if the last page of a group is discarded by any means, the previously received terminate
command for this group is also discarded. After the printer has discarded data, the host cannot necessarily tell
whether or not the printer has processed a DGB-terminate command. Therefore when any NACK is reported, if
the received page counter or committed copy counter reflects the last page of a group and no copies of a
subsequent group have been created, the grouping is not considered to be complete and any previously
received DGB-terminate command for this group is discarded. In this case, all previously received DGB
commands after the discarded DGB-terminate command are also discarded. However, if the printer is saving
pages and a synchronous NACK is reported for an error that would not end the saving of pages, all groups that
are currently initiated remain initiated and no DGB-terminates are discarded.
Finishing operations can be applied when the DGB-terminate command is processed. In this case, if a NACK
is reported later that reopens the group, the finishing operation is not applied a second time when the group is
closed again.
Note: Empty groups are not kept by the printer. When a DGB-terminate command is received for a group with
no pages; information concerning the empty group is immediately discarded.
This is an optional command that not all printers support. If this command is not sent to a printer or if the printer
does not support the command, no grouping is defined.
Operations on groups can be specified by the XOH Specify Group Operation (XOH SGO) command. However,
the grouping is ignored when no group operation for the group has been specified. The reply to an STM
command indicates whether or not the printer supports these two XOH orders. The Supported Group
Operations self-defining field in the reply to an XOH-OPC command specifies which group operations are
supported.


In most cases, group operations apply to all of the pages of a group including those pages within nested
groups; however some group operations are incompatible with each other. In this case, the operation on the
inner group is ignored. The various combinations are shown in the following table:
**Table 31. Group Operation Nesting** [IPDS-4-1204]

| Inner Group Operation \ Outer Group Operation | Keep Group Together as a Print Unit | Keep Group Together as a Recovery Unit | Keep Group Together for Microfilm Output | Save Pages | Finish | Identify Named Group |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| Keep Group Together as a Print Unit | OK | OK | Ignored | OK | OK | OK [IPDS-4-1205]|
| Keep Group Together as a Recovery Unit | Ignored | Ignored | Ignored | OK | OK | OK [IPDS-4-1206]|
| Keep Group Together for Microfilm Output | Ignored | OK | OK | OK | Ignored | OK [IPDS-4-1207]|
| Save Pages | Ignored | Ignored | Ignored | OK | Ignored | OK [IPDS-4-1208]|
| Finish | OK | OK | Ignored | OK | OK | OK [IPDS-4-1209]|
| Identify Named Group | OK | OK | OK | OK | OK | OK [IPDS-4-1210]|
Multiple operations can also be applied to a single group level by specifying multiple XOH-SGO commands;
**Figure 61** shows an example of this with group level = X'20'. In addition, some operations (such
as finishing) can be applied to a group multiple times by specifying multiple group-operation triplets on the
XOH-DGB command. For the purpose of determining how these group operations interact, the group is
considered to be nested within itself and the order of the XOH-SGO commands and group-operation triplets
determines the nesting. The previous table shows how inner and outer groups are treated in this case.
Triplets specified on the XOH-DGB command provide additional information for carrying out a group operation.
In some cases, absence of a required triplet causes the group operation to be ignored; for example, a finishing
group requires a finishing triplet. [IPDS-4-1211]


**Figure 61**. Examples of Groups and Group Operations
SGO  Level =  X'80',  Operation =  X'01'  (Keep group together as a print unit)
SGO  Level =  X'60',  Operation =  X'03'  (Save pages)
SGO  Level =  X'40',  Operation =  X'03'  (Save pages)
SGO  Level =  X'20',  Operation =  X'01'  (Keep group together as a print unit)
SGO  Level =  X'20',  Operation =  X'04'  (Finish)
DGB  Initiate,  Level =  X'80',  Group ID (X'00') triplet =  [Format =  X'02'  (VM print-data format)]
Page1
DGB  Initiate,  Level =  X'60',
Group ID (X'00') triplet =   [Format =  X'08', Variable-length group ID =  "Outer_Document"]
Page2
Page3
DGB  Initiate,  Level =  X'40',
Group ID (X'00') triplet =   [Format =  X'08', Variable-length group ID =  "Inner_Document"]
Page4
Page5
Page6
DGB Terminate,  Level =  X'40'
Page7
Page8
DGB Terminate,  Level =  X'60'
Page9
DGB  Initiate,  Level =  X'20',  Group ID (X'00') triplet =  [Format =  X'02' (VM print-data format)],
Finishing Operation (X'85') triplet =  [Corner staple, Top-left corner]
Page10
Page11
Page12
DGB Terminate,  Level =  X'20'
Page13
Page14
Page15
DGB Terminate,  Level =  X'80'
Two groups of pages are kept together as a print unit:
1) Page1 - Page15
2) Page10 - Page12
Only Page1 and Page9 - Page15 are actually printed.
Two groups of pages are saved:
1) Page2 - Page8  (Outer_Document)
2) Page4 - Page6  (Inner_Document)
One group of pages is stapled in the top-left corner:
1) Page10 - Page12
Results:


**Figure 62**. Examples of Nested Finishing Operations
SGO  Level =  X'90',  Operation =  X'04'  (Finish)
DGB  Initiate,  Level =  X'90',
Finishing Operation (X'85') triplet = [Corner staple, Top-left corner], Finishing Operation (X'85') triplet  = [Punch]
Page1
SGO Level = X’8E’, Operation = X’04’ (Finish)
DGB  Initiate,  Level =  X'8E',
UP3I Finishing Operation (X'8E') triplet  = [Fold, F8-2 4x1]
Page2
Page3
SGO Level = X’8C’, Operation = X’04’ (Finish)
DGB  Initiate,  Level =  X'8C',
UP3I Finishing Operation (X'8E') triplet = [Cut, Perforation cut]
Page4
Page5
Page6
DGB Terminate,  Level =  X'8C'
Page7
Page8
DGB Terminate,  Level =  X'8E'
Page9
Page10
Page11
Page12
Page13
Page14
Page15
DGB Terminate,  Level =  X'90'
Each sheet of the collection is  punched and the entire collection is stapled
together in the top left corner:   Page1 - Page15
Some of the pages are folded using a z-fold:   Page2 - Page8
A perforation is cut into some of the pages:  Page4 - Page6
Each time a new nesting level is needed, an XOH SGO command is specified.
Two operations are specified in the first XOH DGB command using two
Finishing Operation (X'85') triplets.
Results:


The format of the XOH-DGB command is as follows: [IPDS-4-1212]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0400' | Define Group Boundary (DGB) order code | X'0400' [IPDS-4-1213]|
| 2 | CODE | Order type | X'00' or X'01' | DGB order type:<ul><li>X'00' Initiate group</li><li>X'01' Terminate group</li></ul> | X'00' or X'01' [IPDS-4-1214]|
| 3 | UBIN | Group level | X'00'–X'FF' | Group level | X'00'–X'FF' [IPDS-4-1215]|
| 4 to end | Triplets | | | Zero or more triplets:<ul><li>X'00' Group ID triplet</li><li>X'01' CGCSGID triplet</li><li>X'6E' Group Information triplet</li><li>X'85' Finishing Operation triplet</li><li>X'8E' UP3I Finishing Operation triplet</li></ul> [IPDS-4-1216]| |
Bytes 0-1 DGB order code
Byte 2 DGB order type
This byte identifies the type of boundary being defined. Valid values are X'00' and X'01'. X'00'
initiates a group and X'01' terminates a group.
Exception ID X'0277..01' exists if an XOH-DGB command is received that attempts to
terminate a group that is not initiated. Exception ID X'0278..01' exists if an invalid DGB order
type is specified.
Byte 3 Group Level
This byte identifies the group being defined. If a supported operation on the group level has
not been activated by a previously-received XOH Specify Group Operation command, the
boundary groupings are ignored. In this case, exception ID X'0277..01' is optional.
A precise hierarchical order defines the group levels such that a group can contain groups of
lesser group levels but cannot contain groups of equal or greater group levels. For example, a
group of level X'80' can contain a group (or groups) of level X'60' but cannot contain groups of
level X'80' or X'A0'. In addition, the XOH-DGB command that terminates a group level is
optional and if an XOH-DGB command is received that attempts either to initiate or terminate
a group, all initiated but not yet terminated groups of lesser or equal group levels are
terminated. Exception ID X'0277..01' exists if an XOH-DGB command is received that
attempts to terminate a group and no already initiated but not yet terminated groups of lesser
or equal group levels exist.
Note: Even though the XOH-DGB command to terminate a group is optional, it is
recommended that all groups be explicitly terminated. When a group is left open for a
long period of time, some printers interpret this as a possible host problem (abnormal
termination or hang) and automatically issue an Error Printer Restart NACK
(X'018F..00') at the next communication opportunity.
A XOH-DGB command can identify a group to be saved (using the save pages operation); in
this case, if the variable-length group ID (in a Group ID (X'00') triplet) is the same as that of a
previously saved group, exception ID X'0255..00' exists.
Bytes 4 to end
of command
Zero or more triplets
This portion of the XOH-DGB command contains zero or more triplets that contain information
about the operation specified by a preceding XOH-SGO command. The operation in effect for
a group determines the relationship among the triplets in an XOH-DGB command that initiates
a group boundary to those in the XOH-DGB command that terminates the group boundary.
Each group operation defines the relationship among the triplets. [IPDS-4-1217]


**Table 32. Triplets Used With Each Group Operation** [IPDS-4-1218]

| Group Operation | Triplets Used | Triplet Formats |
| :--- | :--- | :--- |
| Keep group together as a print unit | Group ID (X'00') triplet<br>Group Information (X'6E') triplet | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>Extended OS/400 print-data format<br>AIX® and Windows® print-data format<br>Copy set number format<br>Extended copy set number format<br>Page count format [IPDS-4-1219]|
| Keep Group Together as a Recovery Unit | Group Information (X'6E') triplet | Group name format (one)<br>Additional information format (zero or more) [IPDS-4-1220]|
| Keep group together for microfilm output | Group ID (X'00') triplet<br>Group Information (X'6E') triplet | MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>Microfilm save/restore format [IPDS-4-1221]|
| Save pages | Group ID (X'00') triplet<br>CGCSGID (X'01') triplet | Variable-length group ID format<br>GCSGID/CPGID format<br>CCSID format [IPDS-4-1222]|
| Finish | Finishing operation (X'85') triplet<br>UP3I Finishing operation (X'8E') triplet | AFP finishing format (triplet X'85')<br>UP3I finishing format (triplet X'8E') [IPDS-4-1223]|
| Identify named group | Group Information (X'6E') triplet<br>CGCSGID (X'01') triplet | Group name format (one)<br>Additional information format (zero or more)<br>GCSGID/CPGID format<br>CCSID format [IPDS-4-1224]|
Printers ignore any triplet that is not supported and no exception is reported. If byte 4 or the
first byte after a triplet is X'00' or X'01' (an invalid triplet length), exception ID X'027A..01'
exists.
Within an XOH-DGB command, an instance of a Group ID (X'00') triplet or CGCSGID (X'01')
triplet overrides all previous instances of that triplet; however, multiple Finishing Operation
(X'85') triplets can occur and are processed individually. For example, if the XOH-DGB
command contains two Group ID (X'00') triplets, the first one is ignored and the second one
identifies the group.
For the keep-group-together-as-a-print-unit operation, there can be multiple Group Information
(X'6E') triplets with different formats; however, only the last triplet of each format applies and
the others are ignored. For the identify-named-group and keep-group-together-as-a-recovery-
unit operations, there can be one Group Information (X'6E') triplet using the group-name
format (the last one in the XOH-DGB command is used) and there can be zero or more Group
Information (X'6E') triplets using the additional-information format. For the keep-group-
together-for-microfilm-output operation, only the last Group Information (X'6E') triplet applies
and previous ones (if present) are ignored.
The Define Group Boundary triplets are fully described in the triplets chapter:
“Group ID (X'00') Triplet”
“Coded Graphic Character Set Global Identifier (X'01') Triplet”
“Group Information (X'6E') Triplet”
“Finishing Operation (X'85') Triplet”
“UP
3I Finishing Operation (X'8E') Triplet” [IPDS-4-1225]


Coded Graphic Character Set Global Identifier (X'01') Triplet Considerations
When a CGCSGID (X'01') triplet is specified in an XOH-DGB command, it specifies the code page and
character set used to interpret character data within subsequent DGB triplets. The CGCSGID (X'01') triplet
stays in effect until either it is replaced by another CGCSGID (X'01') triplet or the end of the XOH-DGB
command is reached.
The CGCSGID (X'01') triplet applies to the following character data:
• Variable-length group ID format in the Group ID (X'00') triplet [IPDS-4-1226]
• Group name format in the Group Information (X'6E') triplet [IPDS-4-1227]
Some character data within the Group ID (X'00') triplet is predefined to be either EBCDIC or ASCII; the
CGCSGID (X'01') triplet does not apply to this data.
This triplet is supported by printers and intermediate devices that support the Identify Named Group operation.
Finishing Operation (X'85') Triplet Considerations
The Finishing Operation (X'85') triplet specifies a specific finishing operation to be applied to a collection of
sheets. Multiple finishing operations can be applied to the collection by including multiple Finishing Operation
(X'85') triplets on an XOH-DGB command. Not all combinations of finishing operations are compatible; for
example, two corner staples in the same corner might not be compatible. Compatible combinations of finishing
operations are device specific. If incompatible finishing operations are specified, exception ID X'027C..01'
exists.
The finishing operations are performed on the collection of sheets accumulated for the group. Before the
finishing operation is applied, the printer ends the last sheet of the group, if necessary, so that the next
received page begins a new sheet. If the group contains more or fewer sheets than the printer is capable of
finishing, exception ID X'027C..02' or X'407C..02' exists.
The specific finishing operation parameters are specified in Finishing Operation (X'85') triplets contained in the
XOH-DGB command that either initiates or terminates the group. If multiple Finishing Operation (X'85') triplets
are specified, the operations are applied in the order received and duplicate identical Finishing Operation
(X'85') triplets are ignored (the first is used and the duplicates are ignored). If no Finishing Operation (X'85')
triplets are specified in either XOH-DGB command, no finishing operation is applied.
**Figure 62** shows an example of how multiple finishing operations can be specified.
Notes:
1. Some printers must know about a finishing operation before the first page of a group is received. In this [IPDS-4-1228]
case, the printer ignores Finishing Operation (X'85') triplets on the XOH-DGB command that terminates the
group. Therefore, it is good practice to place Finishing Operation (X'85') triplets on the XOH-DGB
command that initiates the group.
2. If an XOH Stack Received Pages command is received within a group to be finished, all received pages [IPDS-4-1229]
are stacked and the group is unaffected. However, for finishing operations that are applied at the end of the
group, the prematurely stacked pages might or might not have the finishing operation applied.
3. For some printers, alternate offset stacking cannot be combined with a finishing operation. In this case, if [IPDS-4-1230]
the XOA-AOS command conflicts with the finishing operation, the XOA-AOS command is ignored and the
finishing operation is performed. [IPDS-4-1231]


UP3I Finishing Operation (X'8E') Triplet Considerations
The UP3I Finishing Operations (X'8E') triplet is used to specify finishing operations. Support for this triplet is
indicated by property pair X'F101' in the Device-Control command-set vector of an STM reply.
The two finishing triplets (X'85' and X'8E') can coexist. For example, a hole-punch operation identified by a
X'8E' triplet on a DGB command might be followed by a corner staple operation identified by a X'85' triplet.
• If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the [IPDS-4-1232]
printer converts to the other triplet if necessary.
• If an operation can only be fully specified in one of the triplets, that triplet must be used. [IPDS-4-1233]
Hierarchical conflicts between triplets are resolved by standard IPDS nesting rules.
• IPDS nesting rules apply equally to both triplets (for identical finishing operations the triplets are [IPDS-4-1234]
interchangeable).
• Compatible nesting combinations are determined by the printing system; UP3I operation-compatibility rules [IPDS-4-1235]
apply.
The finishing operations are performed on the collection of sheets accumulated for the group. Before the
finishing operation is applied, the printer ends the last sheet of the group, if necessary, so that the next
received page begins a new sheet.
When multiple UP
3I devices are connected together to form a specific paper path, the combination of devices
is called a tupel. A UP3I Tupel self-defining field is provided in the XOH-OPC reply for each possible paper path
combination in the line of UP3I devices. Each tupel has an ID in the range X'0001'–X'FFFF' and an optional
Tupel Name. Also, in the XOH-OPC reply is a Media-Destinations self-defining field that lists media destination
IDs supported by the printer (also in the range X'0001'–X'FFFF'). The UP3I Tupel IDs are included in the range
of media-destination IDs.
UP3I finishing operations are applied in the order received using the first device in the tupel that is capable of
performing the finishing operation. If there is more than one finishing operation to be performed on a sheet, a
specific device order within the tupel is required; since paper does not move backwards within the tupel, each
subsequent operation for a sheet must be performed by the same device or a device that is later in the tupel. A
tupel can contain multiple instances of the same type of device; for example, a tupel might contain a cutter that
performs a perforation cut at a location selected by the operator and that device might be followed by another
cutter that also performs a perforation cut at a location selected by the operator. This scenario might be used to
cut a horizontal perforation with the first cutter and a vertical perforation with the second cutter and the finishing
operations would be specified in two Finishing Operation (X'85') triplets. Because duplicate Finishing
Operation (X'85') triplets are ignored, a sequence number must be used to ensure that the triplets are not
identical.


XOH Eject to Front Facing
The XOH Eject to Front Facing (EFF) command performs one of two functions, depending on whether the
current media is cut sheet (sheets that are not connected) or continuous form (sheets that are connected,
usually by a perforated tear strip).
For cut-sheet media, this order causes the next received page to be printed as the first page of the next sheet.
For continuous-forms media, this order causes the next received page to be printed as the first page of the
next front-facing sheet. A front-facing sheet is one whose leading-edge perforation is on the inside fold and
whose trailing-edge perforation is on the outside fold when the continuous forms are folded. If the printer is at a
front-facing sheet, this command is treated as a No Operation (NOP) command; if the printer is not already at a
front-facing sheet, a blank sheet occurs. This occurs whether or not cut-sheet emulation mode is in effect.
Roll-fed continuous-forms media is treated the same as pre-folded continuous-forms media and the printer
must keep track of the front-facing sheets. Some printers provide a way for an operator to partially disable the
eject-to-front-facing function with roll-fed media (so that no blank sheets occur when this command is used).
The length of the XOH-EFF command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
This order is not cumulative; consecutive EFF orders produce the same effect as a single order. [IPDS-4-1236]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'1300' | Eject to Front Facing (EFF) order code | X'1300' [IPDS-4-1237]|


XOH Erase Residual Font Data
The XOH Erase Residual Font Data (ERFD) order is a data security and privacy order that prohibits access to
residual downloaded font data. This order causes the printer to do the following in the specified order:
1. Eject to the next sheet if not already on a new sheet. The next received page is the first page on the new [IPDS-4-1238]
sheet. This occurs whether or not cut-sheet emulation mode is in effect.
2. Perform an XOH Print Buffered Data. [IPDS-4-1239]
3. For downloaded fonts, clear all font information from printer storage by setting all bits to the same value or [IPDS-4-1240]
to random values. Font information that is cleared includes shape information, metrics information, HARID-
to-global-name mappings created by AR commands, and control blocks created for LFCSC, LCPC, LCP ,
LFC, LFI, LF , and LSS commands.
4. For resident fonts activated by an AR command, clear HARID-to-global-name mappings. [IPDS-4-1241]
Notes:
1. Information about activated coded fonts and activated data-object fonts is erased by this command. [IPDS-4-1242]
However, data-object-font components are not erased by this command. To erase activation information for
data-object-font components, use either the XOH Erase Residual Print Data command or the Deactivate
Data-Object-Font Component command.
2. TrueType/OpenType fonts used as secondary resources in presentation data objects such as a PDF or [IPDS-4-1243]
SVG are not affected by this command.
The length of the XOH-ERFD command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Note that deactivating fonts, using the Deactivate Font command, does not necessarily clear the printer
storage. Also note that the XOH Erase Residual Font Data command has no effect on AR entries, on LFE
activation information or local-ID-to-HARID mappings, on DORE or DORE2
mappings, or on resident fonts
activated by the LFE command.
Note: The XOH-ERFD command is a synchronizing command. Any command following a synchronizing
command is not processed until all preceding commands have been completely processed. Also, the
ACK of the XOH-ERFD command is not returned until the command's processing is complete. [IPDS-4-1244]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0700' | Erase Residual Font Data (ERFD) order code | X'0700' [IPDS-4-1245]|


XOH Erase Residual Print Data
The XOH Erase Residual Print Data (ERPD) order is a data security and privacy order that prohibits access to
residual print data. This order causes the printer to do the following in the specified order:
1. Eject to the next sheet if not already on a new sheet. The next received page is the first page on the new [IPDS-4-1246]
sheet. This occurs whether or not cut-sheet emulation mode is in effect.
2. Perform an XOH Print Buffered Data. [IPDS-4-1247]
3. Terminate all open groups, deactivate all saved page groups, and remove all previously received group [IPDS-4-1248]
operations. Any outstanding operations on the terminated groups are performed at this time.
4. Deactivate all page segments, overlays, saved page groups, data object resources, and data-object-font [IPDS-4-1249]
components.
5. For downloaded page segments, overlays, data object resources, data-object-font components, and [IPDS-4-1250]
buffered page data (text, image, graphics, bar code, and object container data), clear all print information
from printer storage by setting all bits to the same value or to random values. For resident page segments,
overlays, data object resources, and data-object-font components activated by an AR command, clear
HAID-to-global-name mappings.
The length of the XOH-ERPD command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Note that, unlike the XOH-ERPD command, deactivating overlays, page segments, data object resources, or
data-object-font components (using the DO, DPS, DDOR, or DDOFC commands respectively) does not
necessarily clear the printer storage. Also note that the XOH Erase Residual Print Data command has no effect
on AR entries or on setup files.
Note: The XOH-ERPD command is a synchronizing command. Any command following a synchronizing
command is not processed until all preceding commands have been completely processed. Also, the
ACK of the XOH-ERPD command is not returned until the command's processing is complete. [IPDS-4-1251]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0500' | Erase Residual Print Data (ERPD) order code | X'0500' [IPDS-4-1252]|


XOH Obtain Printer Characteristics
OPC Command
The XOH Obtain Printer Characteristics (OPC) command, previously known as XOH Request Printer
Information, causes a set of self-identifying fields that describe characteristics of the printer to be placed in the
special data area of the Acknowledge Reply (replies). If the acknowledgment required flag is not set in the
XOH command containing this order, this order is equivalent to a No Operation (NOP) command.
The length of the XOH-OPC command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The host-to-printer configuration might include IPDS intermediate devices. These devices modify the printer's
XOH-OPC reply to indicate their identity and characteristics.
One of the following exception IDs, with action code X'1D', is issued by the printer whenever a characteristic
reported in the XOH-OPC reply is changed: X'0101..00', X'0102..00', X'0103..00', X'0104..00', X'0105..00',
X'0106..00', X'0108..00', X'0109..00', X'010A..00',
X'010D..00', X'0120..00', or X'017E..00'.
Offset Type Name Range Meaning DC1 Range
0–1 CODE Order code X'F300' Obtain Printer Characteristics (OPC) order
code
X'F300'


OPC Reply
Printers return as many of the self-defining fields as are applicable. The self-defining fields can be in any order,
but all fields that apply must be returned.
A printer may return more than one instance of a particular self-defining field. For self-defining fields whose ID
is X'0001', the last encountered self-defining field for each input media source specifies the printable-area
characteristics for that input media source. For self-defining fields whose IDs are either X'0002', X'0003',
X'0010', X'0024', X'0026', X'0029',
or X'002A', the last encountered instance of the self-defining field specifies
the characteristic. For all other self-defining fields, the parameters within a subsequent instance of a self-
defining field should be interpreted as if they were found at the end of the preceding instance of that self-
defining field.
The self-defining fields returned by printers are as follows:
**Table 33. OPC Self-Defining Field Summary** [IPDS-4-1253]

| SDF ID | Self-Defining Field |
| :--- | :--- |
| X'0001' | “Printable-Area Self-Defining Field” [IPDS-4-1254]|
| X'0002' | “Symbol-Set Support Self-Defining Field” [IPDS-4-1255]|
| X'0003' | “IM-Image and Coded-Font Resolution Self-Defining Field” [IPDS-4-1256]|
| X'0004' | “Storage Pools Self-Defining Field” [IPDS-4-1257]|
| X'0005' | “Retired Item 130 (Standard OCA Color Value Support Self-Defining Field)” [IPDS-4-1258]|
| X'0006' | “Installed Features Self-Defining Field” [IPDS-4-1259]|
| X'0007' | “Available Features Self-Defining Field” [IPDS-4-1260]|
| X'0008' | “Resident Symbol-Set Support Self-Defining Field” [IPDS-4-1261]|
| X'0009' | “Print-Quality Support Self-Defining Field” [IPDS-4-1262]|
| X'000A' | “XOA-RRL RT & RIDF Support Self-Defining Field” [IPDS-4-1263]|
| X'000B' | “Activate Resource RT & RIDF Support Self-Defining Field” [IPDS-4-1264]|
| X'000C' | Retired item 134 [IPDS-4-1265]|
| X'000D' | “Medium Modification IDs Supported Self-Defining Field” [IPDS-4-1266]|
| X'000E' | “Deprecated (Common Bar Code Type/Modifier Self-Defining Field)” [IPDS-4-1267]|
| X'000F' | “Bar Code Type/Modifier Self-Defining Field” [IPDS-4-1268]|
| X'0010' | “Media-Destinations Self-Defining Field” [IPDS-4-1269]|
| X'0012' | “Supported Group Operations Self-Defining Field” [IPDS-4-1270]|
| X'0013' | “Product Identifier Self-Defining Field” [IPDS-4-1271]|
| X'0014' | “Object-Container Type Support Self-Defining Field” [IPDS-4-1272]|
| X'0015' | “DF Deactivation Types Supported Self-Defining Field” [IPDS-4-1273]|
| X'0016' | “PFC Triplets Supported Self-Defining Field” [IPDS-4-1274]|
| X'0017' | “Printer Setup Self-Defining Field” [IPDS-4-1275]|
| X'0018' | “Finishing Operations Self-Defining Field” [IPDS-4-1276]|
| X'0019' | “UP3I Tupel Self-Defining Field” [IPDS-4-1277]|
| X'001A' | “UP3I Paper Input Media Self-Defining Field” [IPDS-4-1278]|
| X'0021' | “Colorant-Identification Self-Defining Field” [IPDS-4-1279]|
| X'0022' | “Device-Appearance Self-Defining Field” [IPDS-4-1280]|
| X'0024' | “Keep-Group-Together-as-a-Recovery-Unit Self-Defining Field” [IPDS-4-1281]|
| X'0025' | “Recognized Group ID Formats Self-Defining Field” [IPDS-4-1282]|
| X'0026' | “Supported Device Resolutions Self-Defining Field” [IPDS-4-1283]|
| X'0027' | “Object-Container Version Support Self-Defining Field” [IPDS-4-1284]|
| X'0028' | “Finishing Options Self-Defining Field” [IPDS-4-1285]|
| X'0029' | “Printer Speed Self-Defining Field ” [IPDS-4-1286]|
| X'002A' | “Active Setup Name Self-Defining Field ” [IPDS-4-1287]|


Printable-Area Self-Defining Field
The Printable-Area self-defining field returns information about the printer's physical media sources, hereafter
referred to simply as media sources or input media sources. This information includes sheet-source
identification (bin), physical media identification and medium presentation space size, and physical-printable-
area location (offset) and size. This self-defining field repeats for every installed media source.
Note: Some printers permit printing on any part of the physical media; for these printers, the size of the
physical printable area is equal to the size of the medium presentation space.
The IPDS architecture does not define a default media source ID, but most host programs use the first
available media source reported in the XOH-OPC reply as the default. Printers should order the Printable-Area
self-defining fields so that a reasonable default media source is listed first; for example, the largest-capacity,
cut-sheet, duplex source or the default media source selected by the printer operator.
Some printers provide a means of linking two or more media sources to increase capacity; when the currently
selected media source becomes empty, media is then selected automatically from one of the other linked
media sources. It is important to have the same size media in all linked media sources. Only one Printable-
Area self-defined field should be returned to describe a set of linked media sources.
Some printers allow a media source to be identified by several media source IDs, effectively providing an alias
capability. In this case, a Printable-Area self-defining field is returned for each of the supported media source
IDs. The XOH-SIMS or LCC command can select this media source by using any of the printer-defined media
source IDs (aliases). [IPDS-4-1288]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0018' plus length of 0, 1, or 2 media ID entries | Length of this self-defining field, including this length field; maximum length X'0115' [IPDS-4-1289]|
| 2–3 | CODE | SDF ID | X'0001' | Printable-Area self-defining field ID [IPDS-4-1290]|
| 4 | CODE | Media source ID | X'00'–X'FF' | Media-source ID: This ID can be selected by either the XOH-SIMS command or the LCC command. The STM reply specifies which method, or methods, of media-source selection the printer supports. [IPDS-4-1291]|
| 5 | | | X'00' | Reserved [IPDS-4-1292]|
| 6 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Unit base for this self-defining field:<br>Ten inches<br>Ten centimeters<br>Retired item 34 [IPDS-4-1293]|
| 7 | | | X'00' | Reserved [IPDS-4-1294]|
| 8–9 | UBIN | UPUB | X'0001' – X'7FFF' | Units per unit base value for this self-defining field [IPDS-4-1295]|
| 10–11 | UBIN | Actual medium presentation space width | X'0001' – X'7FFF' | Actual width of the medium presentation space in L-units. For a printer using cut-sheet media, the width is along the top edge of the sheet. For a printer using continuous-forms media, the width is along the leading edge of the physical media as it moves through the printer and does not include the width of the carrier strips. For a printer using envelope media, the width is along the top edge of the envelope. When the medium presentation space origin corresponds to the printer default media origin, this parameter determines the $X_m$ extent of the medium presentation space in all cases but one. In the case of continuous-forms printers that define the top edge of the sheet to be perpendicular to the leading edge, this parameter determines the $Y_m$ extent of the medium presentation space.<br>This parameter specifies the actual width of the medium presentation space, not necessarily the width used for VPA calculations and N-up partitioning. Refer to “XOH Set Media Size” for details. [IPDS-4-1296]|
| 12–13 | UBIN | Actual medium presentation space length | X'0001' – X'7FFF' | Actual length of the medium presentation space in L-units. When the medium presentation space origin corresponds to the printer default media origin, this parameter determines the $Y_m$ extent of the medium presentation space in all cases but one. In the case of continuous-forms printers that define the top edge of the sheet to be perpendicular to the leading edge, this parameter determines the $X_m$ extent of the medium presentation space.<br>This parameter specifies the actual length of the medium presentation space, not necessarily the length used for VPA calculations and N-up partitioning. Refer to “XOH Set Media Size” for details. For continuous-forms media, the XOH-SMS command can be used to change the actual length of the medium presentation space that results in a corresponding adjustment to the length of the sheet and the physical printable area. [IPDS-4-1297]|
| 14–15 | UBIN | $X_m$ PPAoffset | X'0000' – X'7FFF' | $X_m$ offset of the physical printable area in L-units [IPDS-4-1298]|
| 16–17 | UBIN | $Y_m$ PPAoffset | X'0000' – X'7FFF' | $Y_m$ offset of the physical printable area in L-units [IPDS-4-1299]|
| 18–19 | UBIN | $X_m$ PPAextent | X'0001' – X'7FFF' | $X_m$ extent of the physical printable area in L-units [IPDS-4-1300]|
| 20–21 | UBIN | $Y_m$ PPAextent | X'0001' – X'7FFF' | $Y_m$ extent of the physical printable area in L-units [IPDS-4-1301]|
| 22–23 | BITS | Input media source characteristic flags | | All combinations of all the characteristics are architecturally valid. [IPDS-4-1302]|

| Bit | Name | Value | Meaning [IPDS-4-1303]|
| :--- | :--- | :--- | :--- |
| bit 0 | Duplex | B'1'<br>B'0' | The media source (bin) is currently capable of duplexing, but it does not imply that there currently is duplexable physical media in the bin.<br>The media source is not currently capable of duplexing. [IPDS-4-1304]|
| bits 1–2 | Primary media characteristic | B'01'<br>B'10' | Continuous forms<br>Cut sheet<br>This characteristic determines how the printer interprets certain commands such as XOH EFF, XOH SCF, and XOA CEM, as well as informing the host of the location of the top edge of the sheet. See the following envelope and COM bits for a description of the top edge of the sheet. [IPDS-4-1305]|
| bit 3 | Available | B'1'<br>B'0' | Media source available<br>Media source not available; bytes 6–21 of the Printable-Area self-defining field might contain inaccurate information. [IPDS-4-1306]|
| bit 4 | | B'0' | Retired item 119 [IPDS-4-1307]|
| bit 5 | Envelope | B'1'<br>B'0' | Envelope media; the media source is currently set up for envelopes; either envelopes are in the media source, or the media source is empty. Envelopes are either continuous forms or cut sheet. However, the top edge of the sheet is as described in Figure 20.<br>Not envelope media. [IPDS-4-1308]|
| bit 6 | Manual | B'1'<br>B'0' | Manual media feed<br>Automatic media feed [IPDS-4-1309]|
| bit 7 | Computer Output on Microfilm (COM) | B'1'<br>B'0' | Computer output on microfilm media. COM is either continuous forms or cut-sheet. However, the top edge of the sheet is as described in Figure 21, Figure 22, and **Figure 23**.<br>Not computer output microfilm media [IPDS-4-1310]|
| bit 8 | No carrier strips | B'1'<br>B'0' | Continuous forms media without carrier strips; this flag ignored for cut sheet media.<br>Continuous forms media with carrier strips [IPDS-4-1311]|
| bit 9 | Inserter bin | B'1'<br>B'0' | The physical media in this bin is tracked with the page and copy counters, but no printing is done. Medium overlays and preprinted form overlays are suppressed on physical media selected from this bin. Edge marks and mark forms are also suppressed.<br>Not an inserter bin<br>Note: If the printer can duplex, the inserter bin should also be marked as duplex capable. [IPDS-4-1312]|
| bit 10 | Media feed direction | B'0'<br>B'1' | Media feed direction:<br>Long-edge fed<br>Short-edge fed<br>Note: Not all IPDS devices set this flag; support is indicated by the X'F102' property pair in the Device-Control command-set vector of an STM reply. [IPDS-4-1313]|
| bits 11–15 | | B'00000' | Reserved [IPDS-4-1314]|

Zero, one, or two media ID entries in the following format; the media ID type value must be different for each entry: [IPDS-4-1315]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 24–25 | UBIN | Media ID length | X'0004' to end of entry | Length of input media identification (bytes 24 to end)<br>Note: This value is limited by the maximum length of data in a MO:DCA triplet (250 bytes). [IPDS-4-1316]|
| 26 | CODE | Media ID type | X'00'<br>X'01'<br>X'10' | Type of input media identification. This is a registered code that identifies the naming scheme used; registered values include:<br>**User defined** (entry length range is X'0004'–X'00FD')<br>The input media ID (in bytes 27 to end) contains characters from IBM character set 640 using the code points assigned in IBM code page 500. The space character (X'40') is also allowed.<br>**Retired item 132**<br>**MO:DCA media type OID** (entry length range is X'000C'–X'000F')<br>The input media ID (in bytes 27 to end) contains an ASN.1 OID encoded using the definite short form (also called encoded form). For example, bytes 27–35 would contain X'06072B120004030101' to indicate ISO A4 colored media.<br>The registry of standard media types along with their OID is provided in the Media-Type-Identifiers section of the MO:DCA Registry Appendix in the Mixed Object Document Content Architecture Reference, SC31-6802. [IPDS-4-1317]|
| 27 to end | UNDF | Input media ID | Any value | Input media identification [IPDS-4-1318]|
Bytes 24 to end are optional and are not returned by all printers.
The input media ID is data whose meaning is printer specific. [IPDS-4-1319]


Symbol-Set Support Self-Defining Field
The Symbol-Set Support self-defining field specifies the limits of support for the Load Symbol Set command. [IPDS-4-1320]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000C' – X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1321]|
| 2–3 | CODE | SDF ID | X'0002' | Symbol-Set Support self-defining field ID [IPDS-4-1322]|
| 4 to end of SDF | | Value entries | See following tables | Fixed-box size and variable-box size value entries as shown in the next two tables. [IPDS-4-1323]|

#### Fixed-Box Size Values
This value entry defines the acceptable character-box size for downloaded, monospaced symbol sets. The font identifiers in bytes 6 to end are the same as the font identifiers in bytes 9 and 10 of the Load Font Equivalence command. The symbol-set font identified has a uniform box X-size and box Y-size. Refer to “Load Symbol Set” for more information. This value entry has the following format: [IPDS-4-1324]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Value entry length | X'08'–X'FE' | Length of this value entry, including this length field [IPDS-4-1325]|
| 1 | CODE | Value entry ID | X'01' | Fixed-box size value entry ID [IPDS-4-1326]|
| 2 | UBIN | X box size | X'01'–X'FF' | Character-box X size in pels [IPDS-4-1327]|
| 3 | UBIN | Y box size | X'01'–X'FF' | Character-box Y size in pels [IPDS-4-1328]|
| 4 | | | X'00' | Reserved [IPDS-4-1329]|
| 5 | UBIN | Entry length | X'02' | Length of each repeating group entry [IPDS-4-1330]|

One to 124 entries in the following format: [IPDS-4-1331]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID (FGID) supporting the box size [IPDS-4-1332]|

#### Variable-Box Size Values
This value entry defines the acceptable character-box size for any proportional symbol-set identifiers that can be downloaded. The font identifiers in bytes 10 to end are the same as the font identifiers in bytes 9 and 10 of the Load Font Equivalence command. The symbol-set font identified has a uniform box Y-size and has a variable box X-size that serves as the character width. This value entry is formatted as follows: [IPDS-4-1333]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Value entry length | X'0C'–X'FA' | Length of this value entry, including this length field [IPDS-4-1334]|
| 1 | CODE | Value entry ID | X'02' | Variable-box size value entry ID [IPDS-4-1335]|
| 2 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Ten-inch increments<br>Ten-centimeter increments<br>Retired item 35 [IPDS-4-1336]|
| 3 | | | X'00' | Reserved [IPDS-4-1337]|
| 4–5 | UBIN | PPUB | X'0001' – X'7FFF' | Pels per unit base [IPDS-4-1338]|
| 6 | UBIN | Maximum size | X'01'–X'FF' | Maximum character-box X size in pels [IPDS-4-1339]|
| 7 | UBIN | Uniform size | X'01'–X'FF' | Uniform character-box Y size in pels [IPDS-4-1340]|
| 8 | | | X'00' | Reserved [IPDS-4-1341]|
| 9 | UBIN | Entry length | X'02' | Length of each repeating group entry [IPDS-4-1342]|

One to 120 entries in the following format: [IPDS-4-1343]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID (FGID) supporting this box size [IPDS-4-1344]|


IM-Image and Coded-Font Resolution Self-Defining Field
The IM-Image and Coded-Font Resolution self-defining field specifies the supported resolutions in pels per unit base for IM Image and downloaded LF1-type and LF2-type coded-font pattern data. Most other data is resolution independent. For example, a resolution can be specified for GOCA image; however, if an image resolution is not specified, the image is resolution corrected by the printer based on assumptions. Refer to the implementation note in the description of the Begin Image orders within the GOCA Reference. [IPDS-4-1345]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000A' | Length of this self-defining field, including this length field [IPDS-4-1346]|
| 2–3 | CODE | SDF ID | X'0003' | IM-Image and Coded-Font Resolution self-defining field ID [IPDS-4-1347]|
| 4 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Unit base for this self-defining field:<br>Ten-inch increments<br>Ten-centimeter increments<br>Retired item 36 [IPDS-4-1348]|
| 5 | CODE | Font resolutions | X'00'<br>X'FF' | LF1 raster-pattern resolutions supported:<br>Only the resolution specified in bytes 6–9<br>All resolutions in the range X'0001' – X'7FFF' (in this case, bytes 6–9 contain the highest device resolution) [IPDS-4-1349]|
| 6–7 | UBIN | X pels | X'0001' – X'7FFF' | X pels per unit base [IPDS-4-1350]|
| 8–9 | UBIN | Y pels | X'0001' – X'7FFF' | Y pels per unit base [IPDS-4-1351]|
Notes:
1. If all raster-pattern resolutions are supported (byte 5 = X'FF'), the printer must also support the Font [IPDS-4-1352]
Resolution and Metric Technology (X'84') triplet.
2. The resolution values specified in bytes 6–7 and 8–9 are not necessarily the same as the current device [IPDS-4-1353]
resolution; if necessary, the printer will resolution correct all data to match the current device resolution.
The “Supported Device Resolutions Self-Defining Field” lists the current device resolutions. [IPDS-4-1354]


Storage Pools Self-Defining Field
The Storage Pools self-defining field specifies storage pools within the printer. Each storage pool is defined with an entry that specifies total storage and the objects that are stored within the pool. [IPDS-4-1355]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0004' plus length of zero or more storage-pool entries | Length of this self-defining field, including this length field; maximum length X'7FFF' [IPDS-4-1356]|
| 2–3 | CODE | SDF ID | X'0004' | Storage Pools self-defining field ID [IPDS-4-1357]|

Zero or more storage-pool entries in the following format: [IPDS-4-1358]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | UBIN | Entry length | X'0B'–X'FF' (odd values) | Length of the entry, including this length field [IPDS-4-1359]|
| + 1 | CODE | Entry ID | X'01' | Entry ID [IPDS-4-1360]|
| + 2 | CODE | Storage pool ID | X'00'–X'FF' | Storage pool ID [IPDS-4-1361]|
| + 3–6 | UBIN | Empty size | X'00000000' – X'FFFFFFFF' | Size of the storage pool, in bytes, when empty [IPDS-4-1362]|
| + 7–10 | | | X'00000000' | Reserved [IPDS-4-1363]|

Zero or more object ID entries in the following format: [IPDS-4-1364]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| ++ 0–1 | CODE | Object ID | | The ID of an object that is stored in this storage pool. If no object IDs are present, all supported objects that are not specified in other storage pool entries are stored in this pool. Only one of the various storage pools reported may use this default reporting format. [IPDS-4-1365]|
| | | | X'0007' | Symbol sets [IPDS-4-1366]|
| | | | X'0011' | Page graphics data [IPDS-4-1367]|
| | | | X'0012' | Page image data [IPDS-4-1368]|
| | | | X'0013' | Page text data [IPDS-4-1369]|
| | | | X'0014' | Page bar code data [IPDS-4-1370]|
| | | | X'0021' | Overlay graphics data [IPDS-4-1371]|
| | | | X'0022' | Overlay image data [IPDS-4-1372]|
| | | | X'0023' | Overlay text data [IPDS-4-1373]|
| | | | X'0024' | Overlay bar code data [IPDS-4-1374]|
| | | | X'0031' | Page segment graphics data [IPDS-4-1375]|
| | | | X'0032' | Page segment image data [IPDS-4-1376]|
| | | | X'0033' | Page segment text data [IPDS-4-1377]|
| | | | X'0034' | Page segment bar code data [IPDS-4-1378]|
| | | | X'0040' | Single-byte coded-font index tables [IPDS-4-1379]|
| | | | X'0041' | Single-byte coded-font descriptors [IPDS-4-1380]|
| | | | X'0042' | Single-byte coded-font patterns [IPDS-4-1381]|
| | | | X'0048' | Double-byte coded-font index tables [IPDS-4-1382]|
| | | | X'0049' | Double-byte coded-font descriptors [IPDS-4-1383]|
| | | | X'004A' | Double-byte coded-font patterns [IPDS-4-1384]|
| | | | X'0050' | Code pages [IPDS-4-1385]|
| | | | X'0060' | Font character sets [IPDS-4-1386]|
| | | | X'0070' | Coded fonts [IPDS-4-1387]|


Retired Item 130 (Standard OCA Color Value Support Self-Defining Field)
The Standard OCA Color Value Support self-defining field specifies the set of Standard OCA color values that
are supported by the printer. This self-defining field has no meaning for other color spaces.
Note: This self-defining field has been retired from the IPDS architecture and should not be returned by new
implementations. It is described here for informational purposes because, before it was retired, all IPDS
implementations returned this self-defining field.
Offset Type Name Range Meaning
0–1 UBIN SDF length X'0006' –
X'7FFE'
Length of this self-defining field, including this length field
2–3 CODE SDF ID X'0005' Standard OCA Color Value Support self-defining field ID
One or more color-table values in the following format:
+0–1 CODE Color value
X'0001'
X'0002'
X'0003'
X'0004'
X'0005'
X'0006'
X'0007'
X'0008'
X'0009'
X'000A'
X'000B'
X'000C'
X'000D'
X'000E'
X'000F'
X'0010'
True colors supported by the printer:
Blue
Red
Pink/magenta
Green
Turquoise/cyan
Yellow
White (some printers use color of medium)
Black
Dark blue
Orange
Purple
Dark green
Dark turquoise
Mustard
Gray
Brown


Installed Features Self-Defining Field
The Installed Features self-defining field specifies features installed in the device. If a feature is installed, any
commands and properties associated with this feature must be specified in the STM reply as being supported.
Conversely, if a feature is not installed, any commands and properties associated with this feature must not be
specified in the STM reply as being supported. The installation or removal of a feature that requires an STM
change can only take place while the device is offline so that the level of support indicated in the STM reply is
valid and constant while the device is online. Features that are installed are not necessarily available at the
time of the XOH-OPC response; refer to the “Available Features Self-Defining Field”.
The transition from the offline state to the online state after installing or removing a feature, must be
communicated to the host either at the IPDS level by returning an action code X'0D' NACK or at the carrying
communications-protocol level. A change to the Installed Features self-defining field without an online to offline
transition, must be communicated to the host by returning an action code X'1D' NACK. [IPDS-4-1388]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006' – X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1389]|
| 2–3 | CODE | SDF ID | X'0006' | Installed Features self-defining field ID [IPDS-4-1390]|

One or more feature IDs in the following format: [IPDS-4-1391]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | Feature ID | X'0100'<br>X'0200'<br>X'0201'<br>X'0202'<br>X'0300'<br>X'0400'<br>X'0600'<br>X'0700'<br>X'0800'<br>X'0900'<br>X'0B00'<br>X'0C00'<br>X'0D00' | **Features that are currently installed in the printer:**<br>Duplex<br>Manual two-channel switch<br>Tightly coupled two-channel switch<br>Retired item 39<br>Cut-sheet output<br>Retired item 40<br>Offset stacker<br>Envelopes<br>MICR: capable of printing toned pels that are impregnated with a magnetic material<br>Burster-trimmer-stacker or cutter-trimmer-stacker<br>Continuous-forms output<br>Continuous-forms separation capability<br>PTOCA text decryption capability [IPDS-4-1392]|

Note: The absence of both X'0300' and X'0B00' specifies that continuous-forms output is installed. [IPDS-4-1393]

Available Features Self-Defining Field
The Available Features self-defining field specifies features immediately available in the device. If a feature is
specified as being available, it must also be specified in the Installed Features self-defining field as being
installed, and any commands and properties associated with this feature must be specified in the STM reply as
being supported.

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006' – X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1394]|
| 2–3 | CODE | SDF ID | X'0007' | Available Features self-defining field ID [IPDS-4-1395]|

One or more feature IDs in the following format: [IPDS-4-1396]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | Feature ID | X'0100'<br>X'0200'<br>X'0201'<br>X'0202'<br>X'0300'<br>X'0400'<br>X'0600'<br>X'0700'<br>X'0800'<br>X'0900'<br>X'0B00'<br>X'0C00'<br>X'0D00' | **Features that are currently available in the printer:**<br>Duplex available from at least one media source<br>Manual two-channel switch<br>Tightly coupled two-channel switch<br>Retired item 43<br>Cut-sheet output<br>Retired item 44<br>Offset stacker<br>Envelopes available from at least one installed media source<br>MICR: capable of printing toned pels that are impregnated with a magnetic material<br>Burster-trimmer-stacker or cutter-trimmer-stacker<br>Continuous-forms output<br>Continuous-forms separation capability<br>PTOCA text decryption capability [IPDS-4-1397]|
Notes:
1. If duplex is designated as available in the available-features self-defining field, there must be at least one [IPDS-4-1398]
bin that has the duplex bit (bit 0 of the input-media source characteristics field) set to B'1' in the Printable-
Area self-defining field. If duplex is not designated as available in the available-features self-defining field,
the duplex bit in each Printable-Area self-defining field must be set to B'0'.
2. The absence of both X'0300' and X'0B00' specifies that continuous-forms output is available. [IPDS-4-1399]
3. If offset stacker is designated as available, there is at least one media destination that can jog. If a media [IPDS-4-1400]
destination that does not support offset stacking is selected, all XOA-AOS commands are ignored for that
media destination.
4. Feature ID X'0800' indicates that the printer is currently enabled to print using a MICR material. Some [IPDS-4-1401]
printers that report this feature ID print only MICR, but others print MICR and also print with non-MICR
material (for example, MICR might be supported for the front side, but not for the back side). It is up to the
presentation services program to use a MICR font only with a printer that is currently enabled for MICR
printing. It is up to the application program to ensure that MICR data is printed only in paper locations on
which the printer can use MICR material. AFP Setup Verification can be used on some printers to ensure
that a printer is properly set up for MICR printing; refer to “Printer Setup Self-Defining Field”.
Exception ID X'02B3..01' exists if a string of text within a WT or WG command was encountered that was to
be printed with a MICR font, but MICR printing is not available for this text string. Some printers can print
MICR text on one side of the media, but not on the other side; in this case, text data to be printed with a
MICR font that is placed on the non-MICR side of the media causes this exception to occur.
Note: Some of the features are defined to apply to the printer as a whole as opposed to applying to an
individual media source or media destination; these features include: manual two-channel switch, tightly [IPDS-4-1402]


coupled two-channel switch, cut-sheet output, offset stacker, MICR, burster-trimmer-stacker/cutter-
trimmer-stacker, continuous-forms output, continuous-forms separation capability. [IPDS-4-1403]


Resident Symbol-Set Support Self-Defining Field
The Resident Symbol-Set Support self-defining field specifies that symbol sets are resident in the printer. [IPDS-4-1404]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000E' to end of SDF | Length of this self-defining field, including this length field; maximum length X'7FFE' [IPDS-4-1405]|
| 2–3 | CODE | SDF ID | X'0008' | Resident Symbol-Set Support Self-Defining Field ID [IPDS-4-1406]|

One or more Resident Symbol-Set Repeating Group Lists in the following format: [IPDS-4-1407]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | UBIN | Length | X'0A'–X'FE' | Total Length of Code Page/Font ID Repeating Group List, including this length field [IPDS-4-1408]|
| + 1 | CODE | Code page ID | X'01' | Code Page Support ID [IPDS-4-1409]|
| + 2 | UBIN | Code page list length | X'04'–X'F8' | Length of Code Page List, including this length field [IPDS-4-1410]|
| + 3 | UBIN | Entry length | X'02' | Length of Code Page Repeating Group Entry [IPDS-4-1411]|

One or more Code Page Global IDs (CPGIDs) in the following format: [IPDS-4-1412]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| ++ 0–1 | CODE | CPGID | X'0001' – X'FFFE' | A Code Page Global Id. This list specifies all of the resident code pages that are available in each of the fonts that are specified in the following Font ID List [IPDS-4-1413]|

One matching Font ID List for each Code Page List in the following format: [IPDS-4-1414]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | UBIN | Font ID list length | X'04'–X'F8' | Length of Font ID List, including this length field [IPDS-4-1415]|
| + 1 | UBIN | Entry length | X'02' | Length of Font ID Repeating Group Entry [IPDS-4-1416]|

One or more Font Typeface Global IDs (FGIDs) in the following format: [IPDS-4-1417]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| ++ 0–1 | CODE | FGID | X'0001' – X'FFFE' | A Font Typeface Global Id. This list specifies all of those fonts in which each of the preceding code pages is supported. [IPDS-4-1418]|
Resident Symbol Set Repeating Group Lists:
These lists consist of pairs of Code Page Lists and Font ID Lists. The code page list contains repeating groups
of all code pages that are supported in each font specified in the corresponding Font ID list. The Code Page
List and Font ID List each have their own length (these need not be the same). These Code Page/Font ID List
pairs are themselves repeating groups. Additional Code Page/Font ID List pairs specify those code pages that
are available in other (different) fonts, or perhaps available only in a subset of the fonts for a preceding group
entry.


Print-Quality Support Self-Defining Field
The Print-Quality Support self-defining field specifies the minimum values for print quality supported by the printer. This field need not be returned by printers that have only one print quality. [IPDS-4-1419]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005'–X'0102' | Length of this self-defining field, including this length field [IPDS-4-1420]|
| 2–3 | CODE | SDF ID | X'0009' | Print-Quality Support self-defining field ID [IPDS-4-1421]|

One or more print quality boundaries in the following format: [IPDS-4-1422]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | UBIN | Boundary | X'01'–X'FE' | The lower boundary of an implemented print quality, as specified by the Print-Quality Control order in the Execute Order Anystate command. See “XOA Print-Quality Control”. [IPDS-4-1423]|


XOA-RRL RT & RIDF Support Self-Defining Field
The Execute Order Anystate RRL RT & RIDF Support self-defining field specifies the combinations of resource types and resource ID formats that the printer supports in an XOA-RRL command. [IPDS-4-1424]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1425]|
| 2–3 | CODE | SDF ID | X'000A' | XOA-RRL RT & RIDF Support Self-Defining Field ID [IPDS-4-1426]|

One or more entries in the following format: The list of entries identifies those RRL query combinations to which the printer responds with a nonzero Resource Type reply. A Resource Type reply of zero means that the queried Resource Type, Resource ID Format, or Resource ID are unknown, unsupported, or inconsistent. [IPDS-4-1427]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | RT | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'11'<br>X'12'<br>X'20'<br>X'40'<br>X'41'<br>X'42'<br>X'FF' | **A supported resource type:**<br>Single-byte LF1-type and LF2-type coded font<br>Double-byte LF1-type coded font<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Device-version code page<br>Font character set<br>Single-byte coded font index<br>Double-byte coded font section index<br>Coded font<br>Graphic character set supported in a font character set<br>Specific code page<br>Saved page group<br>Data object resource<br>Data-object font<br>Data-object-font components<br>All-resources resource type [IPDS-4-1428]|
| + 1 | CODE | RIDF | X'00'<br>X'03'<br>X'08'<br>X'09' | **A supported resource ID format:**<br>Host-Assigned Resource ID<br>GRID-parts format<br>Variable-length Group ID (X'00') triplet<br>Object-OID format [IPDS-4-1429]|
The following two-byte RT/RIDF pairs are implicit in other command-set vectors and thus need not be (but can
be) returned in this self-defining field:
• X'0100'—single-byte LF1-type or LF2-type coded font queried by Host-Assigned Resource ID; implicit in [IPDS-4-1430]
support of LF1 or LF2
• X'0200'—double-byte LF1-type coded font queried by Host-Assigned Resource ID; implicit in support of [IPDS-4-1431]
double-byte LF1-type coded fonts
• X'0400'—page segment queried by Host-Assigned Resource ID; implicit in support of PS1 [IPDS-4-1432]
• X'0500'—overlay queried by Host-Assigned Resource ID; implicit in support of OL1 [IPDS-4-1433]
• X'0600'—device-version code page queried by Host-Assigned ID; implicit in support of the LF3 and LF4 [IPDS-4-1434]
subsets
• X'0700'—font character set queried by Host-Assigned ID; implicit in support of the LF3 subset [IPDS-4-1435]
• X'1000'—coded font queried by Host-Assigned ID; implicit in support of the LF3 subset [IPDS-4-1436]
• X'1200'—specific code page queried by Host-Assigned ID; implicit in support of the LF3 and LF4 subsets [IPDS-4-1437]


Activate Resource RT & RIDF Support Self-Defining Field
This self-defining field specifies the combinations of Resource Types and Resource ID Formats supported by the printer, within the Activate Resource command. If this self-defining field is returned, the printer must also return the AR-supported vector in the Sense Type and Model reply. [IPDS-4-1438]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1439]|
| 2–3 | CODE | SDF ID | X'000B' | Activate Resource RT & RIDF Support Self-Defining Field ID [IPDS-4-1440]|

One or more entries in the following format: These entries specify available AR command support. The first byte of each entry identifies a resource type; the second byte of each entry identifies a resource ID format. [IPDS-4-1441]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | RT | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'40'<br>X'41'<br>X'42' | **A supported resource type:**<br>Single-byte LF1-type and LF2-type coded font<br>Retired item 45<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Code page<br>Font character set<br>Single-byte LF1-type coded-font index<br>Double-byte LF1-type coded-font section index<br>Coded font<br>Data object resource<br>Data-object font<br>Data-object-font components [IPDS-4-1442]|
| + 1 | CODE | RIDF | X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'09'<br>X'0A' | **A supported resource ID format:**<br>GRID-parts format<br>Remote PrintManager MVS naming format<br>Extended Remote PrintManager MVS naming format<br>MVS Host Unalterable Remote Font Environment<br>Coded-font format<br>Object-OID format<br>Data-object-font format [IPDS-4-1443]|


Medium Modification IDs Supported Self-Defining Field
This self-defining field lists the medium modification IDs that are currently supported by the XOH-SMM command. If this self-defining field is returned, the printer must also return the Select-Medium-Modifications-support property ID (X'900E') in the Sense Type and Model reply. [IPDS-4-1444]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1445]|
| 2–3 | CODE | SDF ID | X'000D' | Medium Modifications ID Support Self-Defining Field ID [IPDS-4-1446]|

One or more entries in the following format: [IPDS-4-1447]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | Medium modification ID | Any ID that is valid in the XOH-SMM command | ID of a currently-supported medium modification [IPDS-4-1448]|

Note: Medium modification ID X'A0FF' should not be returned in the XOH-OPC reply unless there is at least one other supported fixed medium information modification ID. X'A0FF' is used by the host only if it is returned in the XOH-OPC reply. [IPDS-4-1449]


Deprecated (Common Bar Code Type/Modifier Self-Defining Field)
Note: This self-defining field has been deprecated and replaced with the Bar Code Type/Modifier self-defining
field (X'000F'). The deprecated self-defining field is based on an IPDS-defined subset (called the
common set) that lists the set of bar codes commonly used in the 1980s. Since that time, two of the bar
codes, Industrial 2-of-5 and Matrix 2-of-5, have become less popular and are both essentially obsolete
today. The Interleaved 2-of-5 bar code can be used instead of the obsolete bar code types because it
supports the same input data and also produces a smaller bar code symbol.
Many older IPDS printers return X'000E'; new implementations must use X'000F'. Printers can report
both X'000E' and X'000F' so as to be compatible with older presentation services programs that only
recognize X'000E'. Refer to Figure 63 for a diagram of the bar code subsets and to Table
35 for a comparison of the two self-defining fields. [IPDS-4-1450]
The Common Bar Code Type/Modifier self-defining field lists those bar codes that are supported by the printer,
but are not in the set of common bar codes listed in Table 34. Presence of the Common Bar Code
Type/Modifier self-defining field implies support of all of the common bar code type and modifier combinations
plus the additional bar code type and modifier combinations listed in the self-defining field. [IPDS-4-1451]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1452]|
| 2–3 | CODE | SDF ID | X'000E' | Common Bar Code Type/Modifier Self-Defining Field ID [IPDS-4-1453]|

One or more entries in the following format: [IPDS-4-1454]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | Combination | X'0D'<br>X'11'<br>X'18'<br>X'1A'<br>X'1B'<br>X'1C'<br>X'1D'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'23'<br>X'24'<br>X'86'<br>X'87'<br>X'8C'<br>X'91'<br>X'92'<br>X'93'<br>X'96'<br>X'97'<br>X'98'<br>X'9A' | **Bar code type/modifier combinations that are not in the common set:**<br>Codabar, modifier-byte options X'01' and X'02'<br>Code 128, modifier-byte option X'02'<br>POSTNET (deprecated), modifier-byte options X'00' through X'03'<br>RM4SCC, modifier-byte option X'00'<br>Japan Postal Bar Code, modifier-byte options X'00' and X'01'<br>Data Matrix, modifier-byte option X'00'<br>MaxiCode, modifier-byte option X'00'<br>PDF417, modifier-byte options X'00' and X'01'<br>Australia Post Bar Code, modifier-byte options X'01'–X'08'<br>QR Code, modifier-byte option X'02'<br>Code 93, modifier-byte option X'00'<br>Intelligent Mail Barcode, modifier-byte options X'00' through X'03'<br>Royal Mail RED TAG (deprecated), modifier-byte option X'00'<br>GS1 DataBar, modifier-byte options X'00'–X'04' and X'11'–X'1B'<br>UPC–Two-digit Supplemental, modifier-byte options X'01' and X'02'<br>UPC–Five-digit Supplemental, modifier-byte options X'01' and X'02'<br>Interleaved 2-of-5, modifier-byte options X'03' and X'04'<br>Code 128, modifier-byte option X'03'<br>Code 128, modifier-byte option X'04'<br>Code 128 (Intelligent Mail Container Barcode), modifier-byte option X'05'<br>EAN Two-digit Supplemental, modifier-byte option X'01'<br>EAN Five-digit Supplemental, modifier-byte option X'01'<br>POSTNET (PLANET, deprecated), modifier-byte option X'04'<br>RM4SCC, modifier-byte option X'01' [IPDS-4-1455]|


**Table 34**. Common Values for Bar Code Types and Modifiers [IPDS-4-1456]

| Type | Description | Modifier values |
| :--- | :--- | :--- |
| X'01' | 3-of-9 code | X'01' and X'02' [IPDS-4-1457]|
| X'02' | MSI | X'01' through X'09' [IPDS-4-1458]|
| X'03' | UPC/CGPC, Version A | X'00' [IPDS-4-1459]|
| X'05' | UPC/CGPC, Version E | X'00' [IPDS-4-1460]|
| X'06' | UPC–Two-digit Supplemental | X'00' [IPDS-4-1461]|
| X'07' | UPC–Five-digit Supplemental | X'00' [IPDS-4-1462]|
| X'08' | EAN 8 (includes JAN short) | X'00' [IPDS-4-1463]|
| X'09' | EAN 13 (includes JAN standard) | X'00' [IPDS-4-1464]|
| X'0A' | Industrial 2-of-5 | X'01' and X'02' [IPDS-4-1465]|
| X'0B' | Matrix 2-of-5 | X'01' and X'02' [IPDS-4-1466]|
| X'0C' | Interleaved 2-of-5 | X'01' and X'02' [IPDS-4-1467]|
| X'16' | EAN Two-digit Supplemental | X'00' [IPDS-4-1468]|
| X'17' | EAN Five-digit Supplemental | X'00' [IPDS-4-1469]|

Note: The BCOCA BCD1 subset requires support of a subset of the common set that consists of the common
set minus Industrial 2-of-5 and Matrix 2-of-5. Refer to the *Bar Code Object Content Architecture
Reference* for a description of the BCOCA bar code types and modifiers. [IPDS-4-1470]


Bar Code Type/Modifier Self-Defining Field
The Bar Code Type/Modifier self-defining field lists the optional bar codes that are supported by the printer in
addition to those required by the listed BCOCA subset (either BCD1 or BCD2). Type/Modifier combinations
that are required by the listed subset (either BCD1 or BCD2) should not appear in this self-defining field.
The STM reply for BCOCA can contain one vector for the highest level subset supported or can contain
multiple vectors (one for each supported subset). Providing multiple subsets in the STM reply allows the printer
to indicate optional property pairs supported by each subset (which can be different for each subset). Multiple
vectors are also used to ensure that BCOCA will be supported by older print server software that does not
recognize the new subset (but does support the older one). To minimize the size of the OPC reply, it is
recommended that the highest supported BCOCA subset be listed in the X'000F' self-defining field.
BCOCA subset support is indicated in the STM reply and is shown in Figure 63.
Note: The Common Bar Code Type/Modifier self-defining field (X'000E') has been deprecated; new
implementations must use X'000F'. Printers can report both X'000E' and X'000F' so as to be compatible
with older presentation services programs that only recognize X'000E'. [IPDS-4-1471]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1472]|
| 2–3 | CODE | SDF ID | X'000F' | Bar Code Type/Modifier Self-Defining Field ID [IPDS-4-1473]|
| 4–5 | CODE | BCOCA subset | X'FF10'<br>X'FF20' | **BCOCA subset used as base for this SDF:**<br>BCD1<br>BCD2 [IPDS-4-1474]|

Zero or more two-byte entries in the following format: [IPDS-4-1475]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | Type | X'06'<br>X'07'<br>X'0A'<br>X'0B'<br>X'0C'<br>X'0D'<br>X'11'<br>X'16'<br>X'17'<br>X'18'<br>X'1A'<br>X'1B'<br>X'1C'<br>X'1D'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'23'<br>X'24'<br>X'25'<br>X'26' | **Bar code type:**<br>UPC - Two-digit Supplemental<br>UPC - Five-digit Supplemental<br>Industrial 2-of-5<br>Matrix 2-of-5<br>Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5<br>Codabar, 2-of-7 and AIM USS-Codabar<br>Code 128 – GS1-128, UCC/EAN 128, Intelligent Mail Container Barcode, Intelligent Mail Package Barcode, and AIM USS-128<br>EAN Two-digit Supplemental<br>EAN Five-digit Supplemental<br>POSTNET and PLANET (deprecated)<br>RM4SCC and Dutch KIX<br>Japan Postal Bar Code<br>Data Matrix (2D bar code)<br>MaxiCode (2D bar code)<br>PDF417 (2D bar code)<br>Australia Post Bar Code<br>QR Code, QR Code with Image (2D bar code)<br>Code 93<br>Intelligent Mail Barcode<br>Royal Mail RED TAG (deprecated)<br>GS1 DataBar<br>Royal Mail Mailmark<br>Aztec Code [IPDS-4-1476]|
| + 1 | CODE | Code value for modifiers | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'12'<br>X'80'<br>X'81'<br>X'82'<br>X'90'<br>X'91'<br>X'A0' | **Bar code modifier(s):**<br>**X'00' for the following types:**<br>Code 93, Data Matrix (2D bar code), MaxiCode (2D bar code), RM4SCC, Royal Mail RED TAG (deprecated)<br>**X'01' for the following types:**<br>Data Matrix (2D bar code), Dutch KIX, EAN Five-digit Supplemental, EAN Two-digit Supplemental<br>**X'02' for the following types:**<br>Code 128 – AIM USS-128, QR Code (2D bar code)<br>**X'03' for the following type:**<br>Code 128 – UCC/EAN 128<br>**X'04' for the following types:**<br>Code 128 – GS1-128 and UCC/EAN 128, PLANET (deprecated)<br>**X'05' for the following type:**<br>Intelligent Mail Container Barcode<br>**X'06' for the following type:**<br>Intelligent Mail Package Barcode<br>**X'12' for the following type:**<br>QR Code with Image (2D bar code)<br>**X'80' (X'00' and X'01') for the following types:**<br>Japan Postal Bar Code, PDF417 (2D bar code), Royal Mail Mailmark<br>**X'81' (X'00' through X'03') for the following types:**<br>Aztec Code, Intelligent Mail Barcode, POSTNET (deprecated)<br>**X'82' (X'00' through X'04' and X'11' through X'1B') for the following type:**<br>GS1 DataBar<br>**X'90' (X'01' and X'02') for the following types:**<br>Codabar, 2-of-7 and AIM USS-Codabar, Industrial 2-of-5, Matrix 2-of-5, UPC - Five-digit Supplemental, UPC - Two-digit Supplemental<br>**X'91' (X'01' through X'08') for the following type:**<br>Australia Post Bar Code<br>**X'A0' (X'03' and X'04') for the following type:**<br>Interleaved 2-of-5, ITF-14 and AIM USS-I 2/5 (to show Bearer Bars) [IPDS-4-1477]|


**Figure 63**. BCOCA Bar Code Subsets
BCOCAKey:
BCD2
BCD1
Common Set
Common Set (Deprecated)
Bar Code Types and Modifiers:
Industrial 2-of-5
Matrix 2-of-5
BCD2
BCD1
BCOCA
Additional Function:
Zero-degree object-area orientation support
Additional Function:
Extended bar code color support
Full range for font local IDs
Full range for units per unit base
Symbol suppression
Additional Function:
0 , 90 , 180 , and 270 object-area orientation support [IPDS-4-1478]
o o o o
All values of degrees and minutes for object-area orientation
Bar code objects may be sent in any order
Desired method of adjusting for trailing blanks
Desired symbol width
Small-symbol support
Standard OCA color support
User control of Data Matrix encodation scheme
Bar Code Types and Modifiers:
Aztec Code (2D bar code)
Bearer Bars - Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 - modifiers X'03' and X'04'
Code 128 - GS1-128, UCC/EAN 128 - modifier X'04'
Code 128 - Intelligent Mail Container Barcode - modifier X'05'
Code 128 - Intelligent Mail Package Barcode - modifier X'06'
Data Matrix, GS1 DataMatrix (2D bar code) - modifier X'01'
EAN Five-digit Supplemental - modifier X'01'
EAN Two-digit Supplemental - modifier X'01'
GS1 DataBar
POSTNET (deprecated) - modifiers X'00' through X'03'
POSTNET (deprecated) - PLANET (deprecated) - modifier X'04'
QR Code with Image (2D bar code) - modifier X'12'
Royal Mail Mailmark
Royal Mail RED TAG (deprecated)
UPC Five-digit Supplemental - modifiers X'01' and X'02'
UPC Two-digit Supplemental - modifiers X'01' and X'02'
Bar Code Types and Modifiers:
Australia Post Bar Code
Codabar, 2-of-7, AIM USS-Codabar
Code 93
Code 128 - AIM USS-128 - modifier X'02'
Code 128 - UCC/EAN 128 - modifier X'03'
Data Matrix, GS1 DataMatrix (2D bar code) - modifier X'00'
Intelligent Mail Barcode
Japan Postal Bar Code
MaxiCode (2D bar code)
PDF417 (2D bar code)
QR Code (2D bar code) - modifier X'02'
RM4SCC - modifier X'00'
RM4SCC - Dutch KIX - modifier X'01'
Bar Code Types and Modifiers:
Code 39 (3-of-9 Code), AIM USS-39
EAN 8 (includes JAN-short) and EAN 13 (includes JAN-standard)
EAN Five-digit Supplemental - modifier X'00'
EAN Two-digit Supplemental - modifier X'00'
Interleaved 2-of-5, ITF-14, AIM USS-I 2/5 - modifiers X'01' and X'02'
MSI (modified Plessey code)
UPC/CGPC Version A and UPC/CGPC Version E
UPC Five-digit Supplemental - modifier X'00'
UPC Two-digit Supplemental - modifier X'00' [IPDS-4-1479]


The following cross-reference table shows how the bar code type/modifier combinations are specified with the
two OPC SDFs:

- X'000E' – Deprecated (Common Bar Code Type/Modifier self-defining field) [IPDS-4-1480]
- X'000F' – Bar Code Type/Modifier self-defining field [IPDS-4-1481]

**Table 35**. Relationship Between SDF X'000E' and X'000F' [IPDS-4-1482]

| Type/Modifier Combinations | | Code Value Used in OPC Reply | |
| :--- | :--- | :--- | :--- |
| **Type** | **Modifier** | **For SDF X'000E'** | **For SDF X'000F'** [IPDS-4-1483]|
| Code 39 (3-of-9 Code) and AIM USS-39 | X'01' and X'02' | in common set | in BCD1 [IPDS-4-1484]|
| MSI (modified Plessey code) | X'01' through X'09' | in common set | in BCD1 [IPDS-4-1485]|
| UPC/CGPC – Version A | X'00' | in common set | in BCD1 [IPDS-4-1486]|
| UPC/CGPC – Version E | X'00' | in common set | in BCD1 [IPDS-4-1487]|
| UPC - Two-digit Supplemental (Periodicals) | X'00' | in common set | in BCD1 [IPDS-4-1488]|
| | X'01' and X'02' | X'86' | X'0690' [IPDS-4-1489]|
| UPC - Five-digit Supplemental (Paperbacks) | X'00' | in common set | in BCD1 [IPDS-4-1490]|
| | X'01' and X'02' | X'87' | X'0790' [IPDS-4-1491]|
| EAN-8 (includes JAN-short) | X'00' | in common set | in BCD1 [IPDS-4-1492]|
| EAN-13 (includes JAN-standard) | X'00' | in common set | in BCD1 [IPDS-4-1493]|
| Industrial 2-of-5 | X'01' and X'02' | in common set | X'0A90' [IPDS-4-1494]|
| Matrix 2-of-5 | X'01' and X'02' | in common set | X'0B90' [IPDS-4-1495]|
| Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5 | X'01' and X'02' | in common set | in BCD1 [IPDS-4-1496]|
| Bearer Bars – Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5 | X'03' and X'04' | X'8C' | X'0CA0' [IPDS-4-1497]|
| Codabar, 2-of-7 and AIM USS-Codabar | X'01' and X'02' | X'0D' | X'0D90' [IPDS-4-1498]|
| Code 128 | X'02' (AIM USS-128) | X'11' | X'1102' [IPDS-4-1499]|
| | X'03' (UCC/EAN 128) | X'91' | X'1103' [IPDS-4-1500]|
| | X'04' (GS1-128 and UCC/EAN 128) | X'92' | X'1104' [IPDS-4-1501]|
| | X'05' (Intelligent Mail Container Barcode) | X'93' | X'1105' [IPDS-4-1502]|
| | X'06' (Intelligent Mail Package Barcode) | not supported | X'1106' [IPDS-4-1503]|
| EAN - Two-digit Supplemental | X'00' | in common set | in BCD1 [IPDS-4-1504]|
| | X'01' | X'96' | X'1601' [IPDS-4-1505]|
| EAN - Five-digit Supplemental | X'00' | in common set | in BCD1 [IPDS-4-1506]|
| | X'01' | X'97' | X'1701' [IPDS-4-1507]|
| POSTNET (deprecated) | X'00' through X'03' | X'18' | X'1881' [IPDS-4-1508]|
| | X'04' (PLANET, deprecated) | X'98' | X'1804' [IPDS-4-1509]|
| RM4SCC | X'00' | X'1A' | X'1A00' [IPDS-4-1510]|
| | X'01' (Dutch KIX) | X'9A' | X'1A01' [IPDS-4-1511]|
| Japan Postal Bar Code | X'00' and X'01' | X'1B' | X'1B80' [IPDS-4-1512]|
| Data Matrix and GS1 DataMatrix (2D bar code) | X'00' | X'1C' | X'1C00' [IPDS-4-1513]|
| | X'01' (including DMRE) | not supported | X'1C01' [IPDS-4-1514]|
| MaxiCode (2D bar code) | X'00' | X'1D' | X'1D00' [IPDS-4-1515]|
| PDF417 (2D bar code) | X'00' and X'01' | X'1E' | X'1E80' [IPDS-4-1516]|
| Australia Post Bar Code | X'01' through X'08' | X'1F' | X'1F91' [IPDS-4-1517]|
| QR Code (2D bar code) | X'02' | X'20' | X'2002' [IPDS-4-1518]|
| | X'12' (QR Code with Image) | not supported | X'2012' [IPDS-4-1519]|
| Code 93 | X'00' | X'21' | X'2100' [IPDS-4-1520]|
| Intelligent Mail Barcode | X'00' through X'03' | X'22' | X'2281' [IPDS-4-1521]|
| Royal Mail RED TAG (deprecated) | X'00' | X'23' | X'2300' [IPDS-4-1522]|
| GS1 DataBar | X'00' through X'04' and X'11' through X'1B' | X'24' | X'2482' [IPDS-4-1523]|
| Royal Mail Mailmark | X'00' and X'01' | not supported | X'2580' [IPDS-4-1524]|
| Aztec Code (2D bar code) | X'00' through X'03' | not supported | X'2681' [IPDS-4-1525]|


Media-Destinations Self-Defining Field
This self-defining field specifies the available media-destination IDs that can be selected by a Load Copy
Control command. It contains non-overlapping ranges of contiguous media-destination IDs in ascending order.
At least one media-destination ID must be available at all times.
The UP3I Tupel self-defining field provides information about all UP3I tupels attached to the printer. Each tupel
has an ID and this ID is also included as a media-destination ID. Printers should continue to report normal
printer media-destination values if these destinations are still available when a UP3I tupel is active; printers
should also not reuse printer media-destination values for UP3I tupels (unless a single printer media-
destination is replaced by a UP3I tupel). [IPDS-4-1526]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000A'–X'7FFE' | Length of this self-defining field, including this length field; must be in increments of 4 [IPDS-4-1527]|
| 2–3 | CODE | SDF ID | X'0010' | Media-Destinations self-defining field ID [IPDS-4-1528]|
| 4–5 | CODE | Default | X'0001'–X'FFFF' | Default media-destination ID [IPDS-4-1529]|

One or more entries in the following format: [IPDS-4-1530]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | First | X'0001'–X'FFFF' | First number in a range of available, contiguous media-destination IDs [IPDS-4-1531]|
| + 2–3 | CODE | Last | X'0001'–X'FFFF' | Last number in a range of available, contiguous media-destination IDs; this ID must be greater than or equal to the value specified in bytes +0–1 for this set [IPDS-4-1532]|


Supported Group Operations Self-Defining Field
This self-defining field specifies the group operations supported by a printer, pre-processor, or post-processor in the XOH Specify Group Operation command. If this self-defining field is returned, the printer must also return the XOH-DGB-supported property pair (X'9004') and the XOH-SGO-supported property pair (X'9003') in the Device-Control command-set vector of an STM reply. [IPDS-4-1533]

Support for a group operation also implies support for all triplets defined for that group operation. The relationship between group operations and triplets is shown in Table 32. [IPDS-4-1534]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1535]|
| 2–3 | CODE | SDF ID | X'0012' | Supported Group Operations Self-Defining Field ID [IPDS-4-1536]|

One or more entries in the following format: [IPDS-4-1537]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | Operation | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06' | **Group Operation supported in the XOH-SGO command:**<br>Keep group together as a print unit<br>Keep group together for microfilm output<br>Save pages<br>Finish<br>Identify named group<br>Keep group together as a recovery unit [IPDS-4-1538]|
Notes:
1. Exception ID X'0100..00' (normal printer restart) exists when a group operation is enabled or disabled. [IPDS-4-1539]
2. The XOH-OPC Finishing Operations self-defining field (X'0018') lists the currently supported finishing [IPDS-4-1540]
operations. Because some finishing operations can be enabled or disabled while the printer is online with
the host, it is possible that a Finishing Operations self-defining field is not present in the XOH-OPC reply
while the Finish group operation is still supported.
UP
3I Tupel self-defining fields (X'0019') and UP3I Paper Input Media self-defining fields (X'001A') provide
information about connected UP3I devices that can also support finishing operations.
3. The Keep-Group-Together-as-a-Recovery-Unit self-defining field identifies the maximum number of sheets [IPDS-4-1541]
allowed within a recovery-unit group; these sheets include sheets containing pages and copies of such
sheets. Printers that do not support this group operation or have no maximum do not return this self-
defining field.


Product Identifier Self-Defining Field
The Product Identifier self-defining field is an optional field that specifies parameters that contain product-
identification data. Each parameter is defined with a product-identifier parameter ID that specifies what the
subsequent product identifier describes. [IPDS-4-1542]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0007' to end of SDF | Length of this self-defining field, including this length field; maximum length X'7FFF' [IPDS-4-1543]|
| 2–3 | CODE | SDF ID | X'0013' | Product Identifier self-defining field ID [IPDS-4-1544]|

One or more self-defining product-identifier parameters in the following format: [IPDS-4-1545]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | UBIN | Parameter length | X'03' to end of parameter | Length of this Product-identifier parameter, including this length field; refer to each parameter description for information about valid lengths [IPDS-4-1546]|
| + 1–2 | CODE | Parameter ID | X'0000'<br>X'0001'<br>X'0002'<br>X'0003'<br>X'0004' | **Product-identifier parameter ID:**<br>Retired item 50<br>Unique Product Identifier<br>This product identifier parameter ID indicates that bytes 3 to end contain information that can be used to uniquely identify the printer.<br>IPDS Intermediate Device Identifier<br>This product identifier parameter ID indicates that bytes 3 to end contain information that can be used to uniquely identify an IPDS intermediate device.<br>Printer name<br>Subsystem information [IPDS-4-1547]|
| +3 to end | | Parameter value | Depends on parameter ID [IPDS-4-1548]| |

For all character fields in the following Parameter ID syntax tables, the only EBCDIC characters allowed in
these fields are EBCDIC 0 –9, A –Z, a –z, period, space, and null (X'00'). Refer to code page CPGID 500 for an
appropriate code point to character association. [IPDS-4-1549]

The format of the parameter value field (bytes + 3 to end) depends on the value of the parameter ID field (bytes
+ 1–2), as follows:

Parameter ID = X'0000'
There is no parameter value for this parameter ID. The parameter length must be X'03'. [IPDS-4-1550]


Parameter ID = X'0001'
The parameter length must be in the range X'28'–X'38'. The parameter value contains a unique product
identifier in the following format: [IPDS-4-1551]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 3–8 | CHAR | Device type | | Device type of the printer in the form of six (6) EBCDIC characters that correspond to the device type imprinted on the serial number plate that is physically attached to the printer. This field is right-justified and padded with X'F0' if necessary. [IPDS-4-1552]|
| + 9–11 | CHAR | Model number | | Model number of the printer in the form of three (3) EBCDIC characters that correspond to the model number imprinted on the serial number plate that is physically attached to the printer. This field is right-justified and padded with X'F0' if necessary. [IPDS-4-1553]|
| + 12–14 | CHAR | Manufacturer | | Name of the manufacturer in the form of three (3) EBCDIC characters. If this information is not available, bytes 12–14 contain binary zeros. A registry of manufacturer values (AFPC Company Abbreviation Registry) is kept on the AFP Consortium website www.afpcinc.org. [IPDS-4-1554]|
| + 15–16 | CHAR | Plant of manufacture | | Plant of manufacture in the form of two (2) EBCDIC characters. If this information is not available, bytes 15–16 contain binary zeros. [IPDS-4-1555]|
| + 17–28 | CHAR | Sequence number | | Sequence number of the printer in the form of twelve (12) EBCDIC characters. This field is right-justified and padded with X'F0' if necessary. The sequence number along with the plant of manufacture make up the serial number imprinted on the serial number plate that is physically attached to the printer. If this information is not available, bytes 17–28 contain binary zeros. [IPDS-4-1556]|
| + 29–30 | UNDF | Tag | | Used to differentiate between devices whose IDs specified in bytes 3–28 are otherwise identical, as in the case of two print mechanisms on the same printer control unit. This field is set to X'0000' if this level of differentiation is unnecessary. [IPDS-4-1557]|
| + 31–39 | CHAR | Engineering change level | | Engineering change (EC) level in the form of nine (9) EBCDIC characters that most closely reflect the implemented level of IPDS function in the printer. This field is right-justified and padded with X'F0' if necessary. [IPDS-4-1558]|
| + 40 to end | UNDF | Device-specific information | | Zero (0) to sixteen (16) bytes of device-specific information with device-defined padding and justification. Device-specific information can be release or EC levels or any other data a product might wish to supply to identify its characteristics. (variable length) [IPDS-4-1559]|
Notes:
1. The device-type and model-number values returned in the OPC reply represent the actual device type and [IPDS-4-1560]
model number of the IPDS device.
The device-type and model-number values returned in the STM reply represent either the actual device or
a device being emulated (in which case they are different from those in the OPC reply). Also, because the
STM reply and OPC reply fields have different sizes and data types, the STM reply might be represented in
a compressed form.
Device-type and model-number parameters were originally numeric, but many devices now use
alphanumeric values.
2. The device serial number is represented by a combination of the plant-of-manufacture and sequence- [IPDS-4-1561]
number fields.


Parameter ID = X'0002'
The parameter length must be in the range X'11'–X'21'. The parameter value contains a IPDS intermediate
device identifier in the following format: [IPDS-4-1562]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 3–4 | BITS | Intermediate-device characteristic flags | | **bit 0** Remote resource caching (B'0', B'1')<br>**bit 1** Remote job spooling (B'0', B'1')<br>**bit 2** Datastream transforms (B'0', B'1')<br>**bits 3–15** Reserved (B'0...0') [IPDS-4-1563]|
| + 5–6 | CODE | Device type | X'0000'<br>X'0001'<br>X'0002'<br>X'0003'<br>X'0004'<br>X'0005'<br>X'0006'<br>X'0007' | **Type of IPDS intermediate device specified by a unique two-byte value:**<br>Remote PrintManager 2.0<br>Remote PrintManager 3.0<br>Distributed Print Function<br>Retired item 121<br>PSF Direct (IPDS passthru, similar to RPM 2.0)<br>PSF virtual printer<br>IPDS-to-PDF transform<br>Workstation Print Manager [IPDS-4-1564]|
| + 7–15 | CHAR | Engineering change level | | Engineering change (EC) level in the form of nine (9) EBCDIC characters that most closely reflect the implemented level of IPDS function in the IPDS intermediate device. This field is right-justified and padded with X'F0' if necessary. If this information is not available, bytes 7–15 contain binary zeros. [IPDS-4-1565]|
| + 16 | UBIN | Ordering parameter | | Indicates the logical position of the intermediate device in the host-to-printer configuration. An intermediate device sets this field to the value OP(r) + 1, where OP(r) is the value of the largest ordering parameter in an intermediate device identifier self-defining parameter that is received in an inbound XOH-OPC reply. The intermediate device closest to the printer sets this field to X'00'. [IPDS-4-1566]|
| + 17 to end | UNDF | Device-specific information | | Zero (0) to sixteen (16) bytes of device-specific information with device-defined padding and justification. Device-specific information can be release or EC levels or any other data a product might wish to supply to identify its characteristics. (variable length) [IPDS-4-1567]|
Parameter ID = X'0003'
The parameter length must be in the range X'04'–X'FF'. The parameter value contains a printer name in the
following format:

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 3 to end | CHAR | Printer name | | External name of the printer in the form of a variable number of EBCDIC characters; names can be from 1 to 252 bytes long. This optional name should be supplied when the printer name is different from the device type. [IPDS-4-1568]|

Parameter ID = X'0004'
The parameter length must be in the range X'07'–X'FF'. The parameter value contains information about one
subsystem of the product in the following format: [IPDS-4-1569]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 3 | UBIN | Name length | X'02'–X'FA' | Length of the subsystem name, including this length field [IPDS-4-1570]|
| + 4 to end | CHAR | Subsystem name | | Name of the subsystem, using EBCDIC characters [IPDS-4-1571]|
| ++ 0 | UBIN | EC level length | X'01'–X'F9' | Length of the subsystem EC level, including this length field [IPDS-4-1572]|
| ++ 1 to end | CHAR | Subsystem EC level | | Engineering change (EC) level of the subsystem, using EBCDIC characters [IPDS-4-1573]|
| +++ 0 | UBIN | Info length | X'01'–X'F9' | Length of the subsystem-specific information, including this length field [IPDS-4-1574]|
| +++ 1 to end | UNDF | Subsystem-specific information | | Subsystem-specific information, in a device-defined format. This is any data a product might wish to supply to identify the subsystem's characteristics. [IPDS-4-1575]|
Notes:
1. Because the subsystem information fields are variable length, no padding or justification rules need be [IPDS-4-1576]
specified.
2. Multiple X'0004' entries can be used to report subsystem information for multiple subsystems. [IPDS-4-1577]


Object-Container Type Support Self-Defining Field
This self-defining field lists the object containers supported by the printer and for each type of object indicates whether the object is supported in home state, in page or overlay state, or in all three states. The object-type OIDs also indicate whether an object container is a presentation object or a non-presentation object. Table 17 summarizes characteristics of the currently defined object containers. [IPDS-4-1578]

Non-presentation object containers are downloaded in home state and are either used immediately (as in the case of a setup file) or are later invoked in page or overlay state (as in the case of a PostScript resource object). Presentation object containers can either be part of a page or overlay, or can be downloaded in home state and later included by means of the IDO command in page or overlay state. [IPDS-4-1579]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0016' – X'7FA2' | Length of this self-defining field, including this length field [IPDS-4-1580]|
| 2–3 | CODE | SDF ID | X'0014' | Object-Container Type Support self-defining field ID [IPDS-4-1581]|
| | | | | **One or more type records in the following format:** [IPDS-4-1582]|
| +0 | UBIN | Type record length | X'12'–X'F2' | Length of the type record, including this length field (in increments of 16) [IPDS-4-1583]|
| +1 | CODE | Type | X'01'<br>X'02' | IPDS state in which the following list of registered object-type OIDs is supported:<br>X'01' Page or overlay state<br>X'02' Home state [IPDS-4-1584]|
| | | | | **One or more 16-byte registered object-type OIDs in the following format:** [IPDS-4-1585]|
| +2–17 | CODE | Reg ID | | MO:DCA-registered object ID for the object container supported in the WOC. The ID is left-justified and padded on the right with zeroes. See the "Registered Object-Type OIDs" table below. [IPDS-4-1586]|

**Registered Object-Type OIDs** [IPDS-4-1587]

| Registered Object-Type OID | Meaning |
| :--- | :--- |
| X'0607 2B12 0004 0101 0F00 0000 0000 0000' | Anacomp COM Setup File [IPDS-4-1588]|
| X'0607 2B12 0004 0101 1000 0000 0000 0000' | Anacomp COM Tape Label Setup File [IPDS-4-1589]|
| X'0607 2B12 0004 0101 1800 0000 0000 0000' | AnaStack Record Setup File [IPDS-4-1590]|
| X'0607 2B12 0004 0101 3900 0000 0000 0000' | Color Mapping Table Setup File [IPDS-4-1591]|
| X'0607 2B12 0004 0101 1400 0000 0000 0000' | EPS (Encapsulated PostScript) without transparency [IPDS-4-1592]|
| X'0607 2B12 0004 0101 0D00 0000 0000 0000' | EPS (Encapsulated PostScript) with transparency [IPDS-4-1593]|
| X'0607 2B12 0004 0101 3000 0000 0000 0000' | GIF (Graphics Interchange Format) [IPDS-4-1594]|
| X'0607 2B12 0004 0101 1600 0000 0000 0000' | IOCA Tile Resource [IPDS-4-1595]|
| X'0607 2B12 0004 0101 2F00 0000 0000 0000' | JPEG (Joint Photographic Experts Group) [IPDS-4-1596]|
| X'0607 2B12 0004 0101 1700 0000 0000 0000' | AFPC JPEG Subset [IPDS-4-1597]|
| X'0607 2B12 0004 0101 3A00 0000 0000 0000' | JP2 (JPEG2000 File Format) [IPDS-4-1598]|
| X'0607 2B12 0004 0101 4500 0000 0000 0000' | Non-OCA Resource object; see note 1 [IPDS-4-1599]|
| X'0607 2B12 0004 0101 2200 0000 0000 0000' | PCL page object [IPDS-4-1600]|
| X'0607 2B12 0004 0101 3F00 0000 0000 0000' | PDF multiple-page file without transparency [IPDS-4-1601]|
| X'0607 2B12 0004 0101 4000 0000 0000 0000' | PDF multiple-page file with transparency [IPDS-4-1602]|
| X'0607 2B12 0004 0101 1900 0000 0000 0000' | PDF single page without transparency [IPDS-4-1603]|
| X'0607 2B12 0004 0101 3100 0000 0000 0000' | PDF single page with transparency [IPDS-4-1604]|
| X'0607 2B12 0004 0101 1A00 0000 0000 0000' | PDF (Portable Document Format) Resource Object [IPDS-4-1605]|
| X'0607 2B12 0004 0101 4100 0000 0000 0000' | PNG (Portable Network Graphics) [IPDS-4-1606]|
| X'0607 2B12 0004 0101 2E00 0000 0000 0000' | AFPC PNG Subset [IPDS-4-1607]|
| X'0607 2B12 0004 0101 4400 0000 0000 0000' | Resident Color Profile [IPDS-4-1608]|
| X'0607 2B12 0004 0101 4200 0000 0000 0000' | SVG (Scalable Vector Graphics) [IPDS-4-1609]|
| X'0607 2B12 0004 0101 0E00 0000 0000 0000' | AFPC SVG Subset; see note 2 [IPDS-4-1610]|
| X'0607 2B12 0004 0101 3C00 0000 0000 0000' | TIFF (Tag Image File Format) [IPDS-4-1611]|
| X'0607 2B12 0004 0101 3D00 0000 0000 0000' | AFPC TIFF Subset [IPDS-4-1612]|
| X'0607 2B12 0004 0101 3E00 0000 0000 0000' | TIFF (Tag Image File Format) with transparency [IPDS-4-1613]|
| X'0607 2B12 0004 0101 3500 0000 0000 0000' | TIFF (Tag Image File Format) without transparency [IPDS-4-1614]|
| X'0607 2B12 0004 0101 3300 0000 0000 0000' | TIFF multiple-image file with transparency [IPDS-4-1615]|
| X'0607 2B12 0004 0101 3800 0000 0000 0000' | TIFF multiple-image file without transparency [IPDS-4-1616]|
| X'0607 2B12 0004 0101 2500 0000 0000 0000' | TrueType/OpenType Collection [IPDS-4-1617]|
| X'0607 2B12 0004 0101 2400 0000 0000 0000' | TrueType/OpenType Font [IPDS-4-1618]|
| X'0607 2B12 0004 0101 2600 0000 0000 0000' | UP3I Print Data [IPDS-4-1619]|

**Retired Object-Type OIDs** [IPDS-4-1620]

| Registered Object-Type OID | Meaning |
| :--- | :--- |
| X'0607 2B12 0004 0101 2500 0000 0000 0000' | Retired item 136 [IPDS-4-1621]|
| X'0607 2B12 0004 0101 2400 0000 0000 0000' | Retired item 137 [IPDS-4-1622]|
| X'0607 2B12 0004 0101 2600 0000 0000 0000' | Retired item 138 [IPDS-4-1623]|
Notes:
1. Implementations that report support for the Non-OCA Resource object must support it for all supported [IPDS-4-1624]
primary object types that can use the Non-OCA Resource object as a secondary resource.
2. Implementations that report support for the AFPC SVG Subset must also report the following: [IPDS-4-1625]
• Support for the Non-OCA Resource object in this self-defining field (the Object-Container Type Support [IPDS-4-1626]
Self-Defining Field)
• Property pair X'1209' (X'9C' triplet SVG support) in the Object Container command-set vector [IPDS-4-1627]
• Property pair X'120A' (DORE extender entry support) in the Object Container command-set vector [IPDS-4-1628]
• Property pair X'120D' (TrueType/OpenType fonts as secondary resources support) in the Object [IPDS-4-1629]
Container command-set vector
Note: Originally, the required property pair to report “TrueType/OpenType fonts as secondary resources
support” was X'120B', which reported such support was provided by the DORE command.
However, that property pair has been retired (retired item 149) in favor of property pair X'120D',
which reports such support provided by the DORE2 command. See the description of the X'120D'
property pair in note 4 for more information. [IPDS-4-1630]


DF Deactivation Types Supported Self-Defining Field
The DF Deactivation Types Supported self-defining field lists the optional deactivation types that are supported by the printer. These types are in addition to those listed as required in Table 24. [IPDS-4-1631]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005' – X'000A' | Length of this self-defining field, including this length field [IPDS-4-1632]|
| 2–3 | CODE | SDF ID | X'0015' | DF Deactivation Types Supported self-defining field ID [IPDS-4-1633]|
| | | | | One or more entries in the following format: [IPDS-4-1634]|
| +0 | CODE | Type | X'22'<br>X'50'<br>X'51'<br>X'5D'<br>X'5E'<br>X'5F' | Optional deactivation type:<br>Deactivate a font index for a double-byte coded font section<br>Deactivate a coded font<br>Deactivate a coded font and all associated components<br>Deactivate all resident coded fonts and all associated components<br>Deactivate all coded fonts<br>Deactivate all coded fonts and all associated components [IPDS-4-1635]|


PFC Triplets Supported Self-Defining Field
The PFC Triplets Supported self-defining field lists the optional triplets that are supported by the printer on the
Presentation Fidelity Control command. If the PFC command is supported by a printer, this self-defining field
must be returned in the XOH-OPC reply. [IPDS-4-1636]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005' – X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1637]|
| 2–3 | CODE | SDF ID | X'0016' | PFC Triplets Supported self-defining field ID [IPDS-4-1638]|
| | | | | One or more triplet IDs in the following format: [IPDS-4-1639]|
| +0 | CODE | Triplet ID | X'74'<br>X'75'<br>X'86'<br>X'88'<br>X'96' | Supported triplet ID:<br>Toner Saver triplet<br>Color Fidelity triplet<br>Text Fidelity triplet<br>Finishing Fidelity triplet<br>CMR Tag Fidelity triplet [IPDS-4-1640]|


Printer Setup Self-Defining Field
The Printer Setup self-defining field lists all setup IDs that are currently active in the printer. There can be multiple IDs, each of which identifies a particular, implementation-defined setup in the printer or post-processor. These IDs can be used by a presentation services program to verify that a printer is properly set up for a particular print job. [IPDS-4-1641]

An IPDS printer can support setup names (with the ASN and XOA-RSNL commands) or setup IDs, or both; the two functions do not necessarily interact. This Printer Setup self-defining field is not used for setup names. [IPDS-4-1642]

Exception ID X'0108..00' is returned if one or more of the setups change. [IPDS-4-1643]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'FFFE' | Length of this self-defining field, including this length field [IPDS-4-1644]|
| 2–3 | CODE | SDF ID | X'0017' | Printer Setup self-defining field ID [IPDS-4-1645]|

One or more entries in the following format: [IPDS-4-1646]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | Setup ID | X'0000'–X'FFFF' | Currently active setup ID [IPDS-4-1647]|


Finishing Operations Self-Defining Field
The Finishing Operations self-defining field lists all the different types of finishing operations that the printer supports with the Finishing Operation (X'85') triplet. Presence of this OPC self-defining field indicates support for the X'85' triplet. There can be multiple operation-description entries, each of which identifies a supported finishing operation type. Support for a finishing operation type does not imply support for all variations of that operation type. [IPDS-4-1648]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1649]|
| 2–3 | CODE | SDF ID | X'0018' | Finishing Operations self-defining field ID [IPDS-4-1650]|

One or more operation-description entries in the following format: [IPDS-4-1651]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0 | CODE | Operation type | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'0A'<br>X'0C'<br>X'0D'<br>X'0E'<br>X'0F'<br>X'12'<br>X'14'<br>X'18'<br>X'19'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'30'<br>X'31'<br>X'32' | **Supported finishing operations:**<br>Corner staple<br>Saddle-stitch out<br>Edge stitch<br>Fold in<br>Separation cut<br>Perforation cut<br>Z fold<br>Center-fold in<br>Trim after center fold or saddle stitch<br>Punch<br>Perfect bind<br>Ring bind<br>C-fold in<br>Accordion-fold in<br>Saddle-stitch in<br>Fold out<br>Center-fold out<br>Trim<br>C-fold out<br>Accordion-fold out<br>Double parallel-fold in<br>Double gate-fold in<br>Single gate-fold in<br>Double parallel-fold out<br>Double gate-fold out<br>Single gate-fold out [IPDS-4-1652]|
Exception ID X'0109..00' exists when a finishing operation is enabled or disabled. [IPDS-4-1653]


UP3I Tupel Self-Defining Field
This self-defining field reports the physical order and properties of the UP3I devices connected to the printer. One of these self-defining fields is returned for each possible paper path combination in the line of UP3I devices; the combination of devices is called a tupel. [IPDS-4-1654]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0009' to end of SDF | Length of this self-defining field, including this length field [IPDS-4-1655]|
| 2–3 | CODE | SDF ID | X'0019' | UP3I Tupel self-defining field ID [IPDS-4-1656]|
| 4–5 | UBIN | Tupel ID | X'0001' – X'FFFF' | UP3I Tupel ID [IPDS-4-1657]|
| 6 to end | | UP3I device information | | The information returned in this self-defining field is defined by UP3I and is described in the current UP3I Specification that is available at www.afpcinc.org. Refer to the section titled “Extension for the Intelligent Printer Data Stream (IPDS)”. [IPDS-4-1658]|

Exception ID X'0109..00' exists when a finishing operation is enabled or disabled. [IPDS-4-1659]

UP3I Paper Input Media Self-Defining Field
This self-defining field reports the media attributes of all media that exist in the UP3I line. One of these self-defining fields is returned for each available IPDS media source for which there is UP3I information. [IPDS-4-1660]

In the XOH-OPC reply, there must be a Printable-Area self-defining field for each media source. In addition, if UP3I information exists for the media source, a UP3I Paper Input Media self-defining field is specified to provide additional information. [IPDS-4-1661]

It is good practice to specify the Printable-Area self-defining field for a media source before specifying the UP3I Paper Input Media self-defining field for that source. [IPDS-4-1662]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005' to end of SDF | Length of this self-defining field, including this length field [IPDS-4-1663]|
| 2–3 | CODE | SDF ID | X'001A' | UP3I Paper Input Media self-defining field ID [IPDS-4-1664]|
| 4 | CODE | Media source ID | X'00'–X'FF' | Media source ID as defined in the OPC Printable-Area self-defining field [IPDS-4-1665]|
| 5 to end | | UP3I media information | | The information returned in this self-defining field is defined by UP3I and is described in the current UP3I Specification that is available at www.afpcinc.org. Refer to the section titled “Extension for the Intelligent Printer Data Stream (IPDS)”. [IPDS-4-1666]|
The Printable-Area self-defining field and the UP3I Paper Input Media self-defining field both provide
descriptions of a media source and the media in that source. The printer must provide non-conflicting
information in each pair of these self-defining fields that are related by a media source ID. [IPDS-4-1667]


Colorant-Identification Self-Defining Field
This self-defining field lists all colorants available in the printer. Colorants and combinations of colorants can be selected using a highlight-color value in the range X'0100'–X'FFFF' along with an indexed CMR. [IPDS-4-1668]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000B' to end of SDF | Length of this self-defining field, including this length field; maximum length X'FFFF' [IPDS-4-1669]|
| 2–3 | CODE | SDF ID | X'0021' | Colorant-Identification self-defining field ID [IPDS-4-1670]|
| | | | | **One or more Colorant-Identification entries in the following format:** [IPDS-4-1671]|
| +0 | UBIN | Entry length | X'07'–X'FF' | Length of this entry, including this length field (odd values) [IPDS-4-1672]|
| +1 | CODE | Entry type | X'01' | User-defined colorant name (UTF-16BE) [IPDS-4-1673]|
| +2 | BITS | Colorant availability flags | | **bit 0** Front available (B'0' = Not available for front side; B'1' = Available for front side; can only be set if bit 6 is also set)<br>**bit 1** Back available (B'0' = Not available for back side; B'1' = Available for back side; can only be set if bit 7 is also set)<br>**bit 2** EPS/PDF (B'0' = Not available to EPS/PDF objects; B'1' = Available to EPS/PDF objects)<br>**bit 3** Reserved (B'0')<br>**bit 4** Front default (B'0' = Not used as default color for front side; B'1' = Used as default color for front side)<br>**bit 5** Back default (B'0' = Not used as default color for back side; B'1' = Used as default color for back side)<br>**bit 6** Front installed (B'0' = Not installed for front side; B'1' = Installed for front side)<br>**bit 7** Back installed (B'0' = Not installed for back side; B'1' = Installed for back side) [IPDS-4-1674]|
| +3–4 | | Reserved | X'0000' | Reserved [IPDS-4-1675]|
| +5 to end | CHAR | Colorant name | | Colorant name, using any UTF-16BE characters [IPDS-4-1676]|
Notes:
1. The front side of a sheet is the side on which front-side pages are printed; refer to the LCC command for [IPDS-4-1677]
information about simplex, duplex, and sheet sides. How paper is loaded in a media source is printer
specific and, in some cases, can be changed by a printer operator. For example, some media sources can
be switched from a face-up to a face-down printing mode (or vice versa) to accommodate post-processing
devices. When any change is made at the printer that changes the availability of any colorant, exception ID
X'0120..00' exists.
2. EPS and PDF objects can use the PostScript DeviceN function to specify colorant names. The EPS/PDF [IPDS-4-1678]
flag (bit 2) indicates which colorants are available for this function. These colorant names must follow the
naming conventions supported in EPS and PDF , that restrict names mainly to the characters available in 7-
bit ASCII.
3. The colorant name here is limited by the self-defining field to be 250 bytes in length (each Colorant- [IPDS-4-1679]
Identification entry is limited to 255 bytes and there are 5 bytes taken before the name). It is also possible [IPDS-4-1680]


to list colorant names in the Colorant Identification List tag in CMOCA and in the nColor Names parameter
in IOCA, and in both of those places the names are also encoded in UTF-16BE and limited to 250 bytes.
When appropriate, the same colorant names should be used in all of these places. In particular, the
following known colorant names defined in CMOCA in the Colorant Identification List tag can be used to
specify colors in the device color space:
AFPC_Device_C Device Cyan
AFPC_Device_M Device Magenta
AFPC_Device_Y Device Yellow
AFPC_Device_K Device Black
AFPC_Device_R Device Red
AFPC_Device_G Device Green
AFPC_Device_B Device Blue
AFPC_Device_Gray Device Gray [IPDS-4-1681]


Device-Appearance Self-Defining Field
This self-defining field lists optional device-appearance values that are supported by the printer. A device appearance can be selected with the Device Appearance (X'97') triplet in a Set Presentation Environment (SPE) command. Support for the Device Appearance (X'97') triplet is indicated by property pair X'F206' in the Device-Control command-set vector of an STM reply. [IPDS-4-1682]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0006'–X'FFFE' | Length of this self-defining field, including this length field [IPDS-4-1683]|
| 2–3 | CODE | SDF ID | X'0022' | Device-Appearance self-defining field ID [IPDS-4-1684]|

One or more appearance values in the following format: [IPDS-4-1685]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| + 0–1 | CODE | Appearance | X'0001' | Device-default monochrome appearance [IPDS-4-1686]|
Note: Printers that support the Device Appearance (X'97') triplet must support at least the device-default
appearance (X'0000'). Printers that support the triplet but do not support any of the optional device-
appearance values also do not return this OPC self-defining field. [IPDS-4-1687]


Keep-Group-Together-as-a-Recovery-Unit Self-Defining Field
This self-defining field identifies the maximum number of sheets allowed within a recovery-unit group; these
sheets include sheets containing pages and copies of such sheets. Support for this group operation is indicated by the Supported Group Operations self-defining field. [IPDS-4-1688]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000B' | Length of this self-defining field, including this length field [IPDS-4-1689]|
| 2–3 | CODE | SDF ID | X'0024' | Keep-Group-Together-as-a-Recovery-Unit self-defining field ID [IPDS-4-1690]|
| 4–5 | UBIN | Maximum number of sheets | X'0000'<br>X'0002' – X'FFFE'<br>X'FFFF' | Number of sheets allowed within a recovery-unit group:<br>Value not specified; refer to maximum total group length value<br>Maximum number of sheets that can be kept together as a recovery unit<br>Maximum value is finite, but larger than 65,534 sheets [IPDS-4-1691]|
| 6 | CODE | Unit base | X'00'<br>X'01' | Unit base for this self-defining field:<br>Ten inches<br>Ten centimeters [IPDS-4-1692]|
| 7–8 | UBIN | UPUB | X'0001' – X'7FFF' | Units per unit base value for this self-defining field [IPDS-4-1693]|
| 9–10 | UBIN | Maximum total group length | X'0000'<br>X'0001' – X'7FFF' | Maximum length of media that can be kept together as a recovery unit:<br>Value not specified; refer to maximum number of sheets value<br>Total length of media that can be kept together as a recovery unit<br>Note: For a printer using cut-sheet media, the value is the sum of the sheet lengths. For a printer using continuous-forms media, the length can be changed with the XOH Set Media Size command. Exception ID X'0101..00' exists whenever media size changes. [IPDS-4-1694]|
Note: Printers that do not support the Keep-Group-Together-as-a-Recovery-Unit group operation or have no
maximum do not return this self-defining field. If both a non-zero maximum number of sheets and a non-
zero maximum total group length is specified, the printer is responsible for ensuring that these values
are consistent and the presentation services program can use either value (and ignore the other value). [IPDS-4-1695]


Recognized Group ID Formats Self-Defining Field
This self-defining field specifies the group ID formats that are recognized by the printer in the Group ID (X'00') triplet. The printer must accept all formats (but unrecognized formats are ignored and don't need to be supplied); this self-defining field can help a host program to determine which Group ID formats to supply. [IPDS-4-1696]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0004' – X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1697]|
| 2–3 | CODE | SDF ID | X'0025' | Recognized Group ID Formats self-defining field ID [IPDS-4-1698]|
| | | | | Zero or more entries in the following format: [IPDS-4-1699]|
| +0 | CODE | Group ID format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'08'<br>X'13' | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>AIX and Windows print-data<br>Variable-length Group ID format<br>Extended OS/400 print-data format [IPDS-4-1700]|


Supported Device Resolutions Self-Defining Field
This self-defining field lists the resolution (or resolutions) controlled by the printer; this includes the resolution to which sheet-side data is RIPped and the number of printed pels per inch (often called the print-head resolution). Most data within IPDS commands is resolution independent; refer to the “IM-Image and Coded-Font Resolution Self-Defining Field” for information about resolution-dependent data. All data to be printed is resolution corrected, if necessary, into the printer’s current RIP and print-head resolution. [IPDS-4-1701]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000C' – X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1702]|
| 2–3 | CODE | SDF ID | X'0026' | Supported Device Resolutions self-defining field ID [IPDS-4-1703]|
| 4–5 | UBIN | RIP X pels | X'0001' – X'FFFF' | Resolution to which sheet-side data is RIPped for pels per inch across the media [IPDS-4-1704]|
| 6–7 | UBIN | RIP Y pels | X'0001' – X'FFFF' | Resolution to which sheet-side data is RIPped for pels per inch in the direction of the media path [IPDS-4-1705]|
| 8–9 | UBIN | Print head X pels | X'0001' – X'FFFF' | Number of printed pels per inch across the media [IPDS-4-1706]|
| 10–11 | UBIN | Print head Y pels | X'0001' – X'FFFF' | Number of printed pels per inch in the direction of the media path [IPDS-4-1707]|
| 12 to end of SDF | | | | Data without architectural definition [IPDS-4-1708]|


Object-Container Version Support Self-Defining Field
This self-defining field lists the object container versions supported by the printer.
The object containers supported by the printer are listed in the Object-Container Type Support self-defining
field. To supplement that self-defining field, this self-defining field optionally lists the supported versions of each
of the object containers listed in the Object-Container Type Support self-defining field.
Every version record in this self-defining field specifies the version support using either a numeric version
number (bytes +18 to +23), or a version name string (bytes +24 to end), or both. However, a version record
that specifies neither the number nor the name is not valid.
The version records in this self-defining field can be in any order. There can be any number of version records
corresponding to a given object container; that is, support for multiple versions of an object container can be
listed in multiple version records.
This self-defining field is optional. Furthermore, all uses of the field are optional; that is, returning version
information for a given object container, returning version information in numeric form, or returning version
information in string form, are all optional.
The following object containers have multiple object-type OIDs defined for variations of them:
• EPS (Encapsulated PostScript) [IPDS-4-1709]
– Without transparency (OID=X'0607 2B12 0004 0101 0D00 0000 0000 0000')
– With transparency (OID=X'0607 2B12 0004 0101 3000 0000 0000 0000')
• PDF (Portable Document Format) [IPDS-4-1710]
– Multiple-page, without transparency (OID=X'0607 2B12 0004 0101 3F00 0000 0000 0000')
– Multiple-page, with transparency (OID=X'0607 2B12 0004 0101 4000 0000 0000 0000')
– Single-page, without transparency (OID=X'0607 2B12 0004 0101 1900 0000 0000 0000')
– Single-page, with transparency (OID=X'0607 2B12 0004 0101 3100 0000 0000 0000')
– Resource object (OID=X'0607 2B12 0004 0101 1A00 0000 0000 0000')
• TIFF (Tag Image File Format) [IPDS-4-1711]
– With transparency (OID=X'0607 2B12 0004 0101 0E00 0000 0000 0000')
– Without transparency (OID=X'0607 2B12 0004 0101 3C00 0000 0000 0000')
– Multiple-image, with transparency (OID=X'0607 2B12 0004 0101 3D00 0000 0000 0000')
– Multiple-image, without transparency (OID=X'0607 2B12 0004 0101 3E00 0000 0000 0000')
• TrueType/OpenType [IPDS-4-1712]
– Font (OID=X'0607 2B12 0004 0101 3300 0000 0000 0000')
– Collection (OID=X'0607 2B12 0004 0101 3500 0000 0000 0000')
For these object containers, version information can be returned using one, and only one, of the following two
methods:
1. One or more version records can return version information for all variations by using any one of the [IPDS-4-1713]
possible object-type OIDs and setting the all-variations flag to B'1'.
2. One or more version records can return version information for each variation separately by using the [IPDS-4-1714]
object-type OID for that variation, and setting the all-variations flag to B'0'.
For the variations of a given object container, no mixing of the two methods is allowed. However, it is allowed to
use the first method for the variations of one object container (for example, PDF), but use the second method
for the variations of a different object container (for example, TIFF). Returning version information for any
specific variation is optional. The all-variations flag is ignored for object containers other than those in the
above list.


A list of recommended values to be returned in this self-defining field for known versions of object containers
can be found in the publication Recommended IPDS Values for Object Container Versions. [IPDS-4-1715]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'001C' – X'7FFE' | Length of this self-defining field, including this length field [IPDS-4-1716]|
| 2–3 | CODE | SDF ID | X'0027' | Object-Container Version Support self-defining field ID [IPDS-4-1717]|
| | | | | **One or more version records in the following format:** [IPDS-4-1718]|
| +0 | UBIN | Version record length | X'18'–X'FE' | Length of the version record, including this length field [IPDS-4-1719]|
| +1–16 | CODE | Object type OID | | MO:DCA-registered object ID for the object container supported in the WOC. This is the same value as specified in the “Reg ID” field in the Object-Container Type Support self-defining field. [IPDS-4-1720]|
| +17 | BITS | Flags | | **bit 0** All variations (B'0' = Version information is applicable to the specified OID only; B'1' = Version information is applicable to all object-type OIDs that are variations of the specified OID)<br>**bits 1–7** Reserved (B'0000000') [IPDS-4-1721]|
| +18–19 | UBIN | Major version | X'0000' – X'FFFF' | Major version number supported. The value X'FFFF' indicates no major version information passed; the minor version and subminor version fields must also be X'FFFF' in this case. [IPDS-4-1722]|
| +20–21 | UBIN | Minor version | X'0000' – X'FFFF' | Minor version number supported. The value X'FFFF' indicates no minor version information passed; the subminor version field must also be X'FFFF' in this case. [IPDS-4-1723]|
| +22–23 | UBIN | Subminor version | X'0000' – X'FFFF' | Subminor version number supported. The value X'FFFF' indicates no subminor version information passed. [IPDS-4-1724]|
| +24 to end | CHAR | Version name | | Name of the version supported, using UTF-16BE characters. This field is optional in the version record. [IPDS-4-1725]|


Finishing Options Self-Defining Field
The Finishing Options self-defining field lists all the finishing options that the printer supports with the Finishing Operation (X'85') triplet. Presence of this OPC self-defining field must always be accompanied by presence of the OPC Finishing Operations self-defining field. There can be multiple option-description entries, each of which identifies a supported finishing option. Support for a finishing option does not imply support for all variations of that option. [IPDS-4-1726]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0005' – X'7FFF' | Length of this self-defining field, including this length field [IPDS-4-1727]|
| 2–3 | CODE | SDF ID | X'0028' | Finishing Options self-defining field ID [IPDS-4-1728]|
| | | | | **One or more option-description entries in the following format:** [IPDS-4-1729]|
| +0 | CODE | Option type | X'01' | Crease [IPDS-4-1730]|

Exception ID X'0109..00' exists when a finishing option is enabled or disabled. [IPDS-4-1731]


Printer Speed Self-Defining Field
This self-defining field reports the speed of the printer.
The reported speed should match the marketing literature for the printer. As such, the value does not depend
on the current configuration of the printer; for example, the reported value should not change if the paper
loaded in the printer changes. In essence, the reported speed is somewhat defining the printer’s “class”, and is
not actually attempting to report the actual speed that can be expected if a job were submitted. A printer that
reports 1200 pages per minute is saying that it is in the class of printers that can print 1200 Letter-sized pages
per minute, not that the printer is currently configured to print at that speed. This avoids a printer having to
continually reevaluate its configuration, and attempt to recompute a new maximum speed given that
configuration.
Both pages-per-minute and feet-per-minute values can be reported. The pages-per-minute value is the
approximate maximum number of Letter-sized pages the printer can print per minute. The feet-per-minute
value is the approximate maximum number of feet of continuous-forms media that can be printed per minute,
and should only be reported by continuous-forms printers; such printers can report either or both speed values.
If desired, a printer can change its reported speed when an IML NACK is reported, to report a new maximum
speed based on a major hardware change (for example, an engine added) or a major configuration change (for
example, a continuous-forms printer purposely set to move paper at half speed).
The reported speed is in no way a guarantee of performance. [IPDS-4-1732]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'000C' | Length of this self-defining field, including this length field [IPDS-4-1733]|
| 2–3 | CODE | SDF ID | X'0029' | Printer Speed self-defining field ID [IPDS-4-1734]|
| 4–7 | UBIN | PPM | X'00000001' – X'FFFFFFFF'<br>X'00000000' | Number of Letter-sized pages that can be printed per minute<br>No pages-per-minute value reported [IPDS-4-1735]|
| 8–11 | UBIN | FPM | X'00000001' – X'FFFFFFFF'<br>X'00000000' | Number of feet of continuous-forms media that can be printed per minute<br>No feet-per-minute value reported [IPDS-4-1736]|


Active Setup Name Self-Defining Field
This self-defining field reports the active setup name on the printer, if any. If there is an active setup name, it is returned using a Setup Name (X'9E') triplet in the active-setup-name field. If there is no active setup name, the SDF-length field is returned as X'0004' and the active-setup-name field is omitted. [IPDS-4-1737]

Exception ID X'010A..00' is returned if the active setup name changes. [IPDS-4-1738]

The triplet is fully described in the triplets chapter: “Setup Name (X'9E') Triplet”. [IPDS-4-1739]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | SDF length | X'0004', X'000A'–X'00D0' even values | Length of this self-defining field, including this length field [IPDS-4-1740]|
| 2–3 | CODE | SDF ID | X'002A' | Active Setup Name self-defining field ID [IPDS-4-1741]|
| 4 to end of SDF | | Active setup name | | Zero or one Setup Name (X'9E') triplet [IPDS-4-1742]|


XOH Page Counters Control
The XOH Page Counters Control (PCC) command provides a counter-synchronization function that should
only be used to recover from an exception or after an XOA Discard Buffered Data command. The host sends
this order to modify the page and copy counters so that the printer and host are synchronized. Refer to
“Acknowledge Reply” for details and copy counters.
The length of the XOH-PCC command can be:
Without CID X'0008'
With CID X'000A'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
This order causes the printer to do the following:
• Eject to the next sheet if not already on a new sheet. The next received page will be the first page on the new [IPDS-4-1743]
sheet. This occurs whether or not cut-sheet emulation mode is in effect.
• Perform an XOH Print Buffered Data. [IPDS-4-1744]
• Modify the page and copy counters as specified in its page-counter-update field (byte 2). [IPDS-4-1745]
Note: PCC is a synchronizing command. Any command following a PCC is not processed until the PCC and
all preceding commands have been completely processed. Also, the ACK of the PCC order is not
returned until PCC processing is complete. [IPDS-4-1746]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'F500' | Page Counters Control (PCC) order code | X'F500' [IPDS-4-1747]|
| 2 | CODE | Counter update | X'00'–X'02' | Page counter update | X'00'–X'01' [IPDS-4-1748]|
Bytes 0–1 Page counter control order code
Byte 2 Page counter update
This byte specifies how the printer is to update the page counters by:
X'00' Doing nothing (default)
X'01' Taking the committed counters (both page and copy) and all counter pairs identical to
the committed counters and performing the following sequential operations:
1. Incrementing the page counters by the number of pages on the sheet if the copy [IPDS-4-1749]
counters are nonzero.
2. Setting the copy counters to zero. [IPDS-4-1750]
3. Setting the received page counter equal to the committed page counter. [IPDS-4-1751]
X'02' Set all page and copy counters (received, committed, operator viewing, and jam
recovery) to the stacked page and copy counters respectively. This effectively
discards all pages and copies of pages between the committed-page station and the
stacked-page station.
Support of this optional function is indicated by the X'FA00' property pair in the
Device-Control command-set vector of an STM reply.
Exception ID X'0295..02' exists if the host program specifies any other value in this field. [IPDS-4-1752]


XOH Print Buffered Data
The XOH Print Buffered Data (PBD) command causes the printer to schedule all buffered data for printing prior
to sending an Acknowledge Reply, if requested. Buffered data is page data not reflected by the committed
page and copy counters. The print buffer is empty at the completion of this command, except when the buffer
contains pages that are part of an unfinished sheet (one side of a duplex sheet, for example). In this case, the
received page counter will be greater than the committed page counter. The presence of these pages do not
cause an exception; however, they remain in the printer. This occurs whether or not cut-sheet emulation mode
is in effect.
The length of the XOH-PBD command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Print Buffered Data order is a synchronizing command. Any command following a synchronizing command
is not processed until all preceding commands have been completely processed. In addition, the ACK of the
PBD order is not returned until PBD processing is complete. [IPDS-4-1753]
| Offset | Type | Name | Range | Meaning | DC1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0100' | Print Buffered Data (PBD) order code | X'0100' [IPDS-4-1754]|


XOH Remove Saved Page Group
The XOH Remove Saved Page Group (RSPG) command directs the printer to deactivate and remove one or
more previously saved page groups.
The XOH Deactivate Saved Page Group (DSPG) command directs the printer to deactivate one or more
previously saved page groups.
The length of the XOH-RSPG command can be:
Without CID X'0007' or X'0009'–X'7FFF'
With CID X'0009' or X'000B'–X'7FFF'
However, each triplet length must also be valid. Exception ID X'0202..02' exists if the command length is
invalid or unsupported.
The groups to be removed are identified by Group ID (X'00') triplets containing a variable-length group ID. If no
triplets are specified, all open saved page groups are terminated, all currently active saved page groups are
deactivated, and all saved page groups are removed; this is a remove all function. A remove-all command
when the printer has no saved page groups is effectively a NOP.
Removing a saved page group also terminates the DGB group (if it was not already terminated) and terminates
all DGB groups with lesser group levels that are nested within the group to be removed.
Only saved page groups specified in the XOH-RSPG command are removed; other saved page groups,
including those created by DGB nesting, are not automatically removed.
The XOH-RSPG command instructs the printer to remove a saved page group, but the removal might not be
immediate. If prior to receiving the remove command, pages from the group are included (using an ISP
command) in pages to be printed, the saved page group is not removed until all of those pages are printed and
stacked.
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0A00' | Remove Saved Page Group (RSPG) order code | X'0A00' [IPDS-4-1755]|
| 2 to end | Triplets | | | Zero or more Group ID triplets:<ul><li>X'00' Group ID triplet with variable-length group ID</li></ul> [IPDS-4-1756]| |
The Remove Saved Page Group triplets are fully described in the triplets chapter:
“Group ID (X'00') Triplet”
Group ID (X'00') Triplet Considerations
This portion of the XOH-RSPG command contains zero or more Group ID (X'00') triplets that specify which
saved page groups to remove. If no triplets are specified, all open saved page groups are terminated, all
currently active saved page groups are deactivated, and all saved page groups are removed; this is a remove
all function. A remove-all command when the printer has no saved page groups is effectively a NOP.
The groups to be removed are identified by Group ID (X'00') triplets containing a variable-length group ID. If
the printer does not find the saved page group identified by a Group ID (X'00') triplet, the triplet is ignored.
Exception ID X'0255..0A' exists if any of the following occurs in the triplets field:
• Byte 2 or the first byte after a valid triplet is X'00' or X'01' (an invalid triplet length). [IPDS-4-1757]


• A triplet other than a Group ID (X'00') triplet is specified. [IPDS-4-1758]
• A Group ID (X'00') triplet without a variable-length group ID is specified. [IPDS-4-1759]


XOH Select Input Media Source
The XOH Select Input Media Source (SIMS) command selects an input media source ID and indirectly selects
the physical media contained in the media source that is mapped to this ID for subsequent sheets. This
command applies to the sheet that the next received page is printed on unless this command is received
between the pages that are to be placed on the sheet. In this case, this command applies to the next sheet
after all copies of the current sheet are printed.
The length of the XOH-SIMS command can be:
Without CID X'0008'
With CID X'000A'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Some printers also support media-source selection in the LCC command. An XOH-SIMS command overrides
a previously received LCC command for all copy subgroups. Similarly, an LCC command that specifies a
media-source ID for a copy subgroup overrides a previously received XOH-SIMS command for that copy
subgroup. When a copy subgroup within an LCC command does not specify a media-source ID, media is
selected from the media source specified by the previously received XOH-SIMS command or, if no XOH-SIMS
command has been received, from the printer-default media source. [IPDS-4-1760]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'1500' | Select Input Media Source (SIMS) order code | X'1500' [IPDS-4-1761]|
| 2 | CODE | Source ID | X'00'–X'FF' | Input media source ID | See byte description. [IPDS-4-1762]|
Bytes 0–1 SIMS order code
Byte 2 Input media source ID
This byte specifies the input media source ID to be used. All input media source IDs reported
in the Printable-Area self-defining field of the XOH-OPC Acknowledge Reply are required to
be supported by a printer. Some printers allow a media source to be identified by several
media source IDs, effectively providing an alias capability. In this case, a Printable-Area self-
defining field is returned for each of the supported media source IDs. The XOH-SIMS or LCC
command can select this media source by using any of the printer-defined media source IDs
(aliases).
If a supported input media source ID is specified but the input media source is not installed,
exception ID X'40E8..nn' exists. If a supported input media source ID is specified and the input
media source is installed but is not available, exception ID X'50F8..nn' exists. If an
unsupported input media source ID is specified, exception ID X'02C8..01' exists.
Note: The printer determines the mapping of the input media source ID to the actual input
media source used on the printer. For example, on some printers an input media source
ID of X'00' is mapped to an input media source with duplex capability. Whereas, on
other printers, a non-duplexable input media source is used. The Printable-Area self-
defining field in the XOH-OPC Acknowledge Reply contains some of the characteristics
of the input media source that is associated with a particular input media source ID. [IPDS-4-1763]


XOH Select Medium Modifications
The XOH Select Medium Modifications (SMM) command selects one or more medium modifications to be
either applied or inhibited on the current sheet of physical media. The current sheet is the sheet on which the
first copy of the next received page will be printed.
The length of the XOH-SMM command can be:
Without CID X'000F' or X'0014'–X'7FFF'
With CID X'0011' or X'0016'–X'7FFF'
However, each entry length must also be valid. Exception ID X'0202..02' exists if the command length is invalid
or unsupported.
The selected medium modifications are applied on the current sheet and all subsequent sheets until another
XOH-SMM command is received that modifies the selection. The modifications, however, are not applied to
blank sheets created by any IPDS command that causes an Eject to Front Facing, or to blank sheets created
by a hardware nonprocess runout (NPRO). The modifications can be applied by the printer or by an attached
pre-processing or post-processing device. Medium modifications are independent of and do not mix with the
data provided by the data stream.
The XOH-SMM command allows medium modifications to be individually applied or inhibited. The command
also allows all previously selected medium modifications to be inhibited. These options can be specified in any
order within an XOH-SMM command.
This is an optional command that not all printers support. If this command is not sent to a printer or if the printer
does not support the command, no medium modifications will be applied.
Some printers limit the size of the data carried in an XOH-SMM command due to storage limitations; if too
many medium modification entries are found in an XOH-SMM command, an exception exists. Refer to your
printer documentation for information about SMM size limitations.
The data in an XOA Select Medium Modifications command consists of 10 bytes of control information
followed by zero or more medium modification entries that are processed in the order that they appear in the
command. If a syntax error is encountered in one of the entries, the XOA-SMM command is discarded and any
previously active SMM entries remain in effect. Exception ID X'026E..01' exists in this situation. [IPDS-4-1764]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0E00' | Select Medium Modifications (SMM) order code | X'0E00' [IPDS-4-1765]|
| 2–9 | | | X'00...00' | Reserved | X'00...00' [IPDS-4-1766]|

Zero or more entries in the following format: [IPDS-4-1767]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| + 0–1 | UBIN | Length | X'0005'–X'7FEE' | Length of the entry, including this length field | X'0005' [IPDS-4-1768]|
| + 2 | CODE | Type | X'00'<br>X'01'<br>X'02' | Medium modification type:<ul><li>X'00' Inhibit medium modification</li><li>X'01' Apply medium modification</li><li>X'02' Inhibit all medium mods</li></ul> | X'00'<br>X'01'<br>X'02' [IPDS-4-1769]|
| + 3–4 | CODE | Modification ID | See byte description | Modification ID | At least one modification ID [IPDS-4-1770]|
| + 5 to end | UNDF | Modification parameters | See byte description | Zero or more bytes of medium-modification parameters [IPDS-4-1771]| |
See byte
description


Bytes 0–1 SMM order code
Bytes 2–9 Reserved
Bytes 10 to end of command
Medium modification entries in the following format:
Entry bytes 0–1 Length
Length of this entry (entry bytes 0 through the end of the entry)
Entry byte 2 Type
This byte specifies the type of entry:
X'00' Inhibit specific
Inhibits the application of a specific medium modification on the current
sheet and all subsequent sheets. If the specific medium modification was
not previously selected, this entry has no affect.
X'01' Apply
Selects a specific medium modification to be applied on the current sheet
and all subsequent sheets. If the specific medium modification was
previously selected, it remains selected.
X'02' Inhibit all
Inhibits the application of all medium modifications on the current sheet and
all subsequent sheets.
Entry bytes 3–4 Modification ID
This field specifies a medium modification ID to be applied or inhibited. When entry
byte 2 contains X'02', entry bytes 3–4 can have any value and are ignored by the
printer.
The valid medium modification IDs are:
X'A000' – X'A0FE' Fixed medium information
The second byte specifies a local ID for the particular fixed
medium information selected.
X'A0FF' All currently-supported fixed medium information local IDs
X'A100' Fixed perforation
A perforation will be cut into the sheet at a fixed location.
X'A200' Fixed separation cut
The sheet will be cut at a fixed location.
Support for specific medium modification IDs is indicated in the XOH-OPC Medium
Modification IDs Supported self-defining field.
Entry bytes 5 to end of entry
Modification parameters
These bytes are reserved for modification parameters. Currently, no medium
modification IDs require any parameters. [IPDS-4-1772]


XOH Separate Continuous Forms
The XOH Separate Continuous Forms (SCF) command signals the printer to separate the continuous-forms
media that is currently selected. This command signals the printer to separate the sheet on which the next
received page will be printed from previous sheets.
The length of the XOH-SCF command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
This command has no effect if cut-sheet media is selected; some printers have a capability to handle both cut-
sheet and continuous-forms media.
If a printer has a printer-configuration function that disables continuous-forms separation, the host is not
notified when continuous-forms separation is enabled or disabled. However, the reply to an XOH Obtain Printer
Characteristics command indicates whether Continuous-Forms Separation Capability is currently available.
This order is not cumulative; consecutive SCF orders produce the same effect as a single order. [IPDS-4-1773]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'0900' | Separate Continuous Forms (SCF) order code | X'0900' [IPDS-4-1774]|


XOH Set Media Origin
The XOH Set Media Origin (SMO) command sets the origin of the Xm,Ym coordinate system to one of the four
corners of the medium presentation space. An XOH-SMO command can cause the physical printable area
offset and extent values reported in the XOH-OPC command to change. This order takes effect on the next
side of a sheet that is selected.
The length of the XOH-SMO command can be:
Without CID X'0008'
With CID X'000A'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
This is an optional command that is not supported by all printers. If this command is not sent to a printer or if
the printer does not support the command, the origin corresponds to the top-left corner of the sheet, where the
viewpoint is at the center of the physical medium. This is called the printer default media origin. In this case,
the $X_m$ axis of the medium presentation space corresponds to the top edge of the sheet, and positive Xm values
begin at the origin and increase from left to right. The $Y_m$ axis of the medium presentation space corresponds
to the left edge of the sheet and positive Ym values begin at the origin and increase from top to bottom.
For printers using continuous-forms media that implement the command, the top edge of the sheet is the short
side whose left corner is closest to the leading edge of the sheet as it moves through the printer. For printers
using continuous-forms media that do not support the command, the printer defines the top edge of the sheet.
For printers using cut-sheet media, the top edge of the sheet is a short side as defined by the printer. This short
side may be the one whose left corner is closest to either the leading or trailing edge of the sheet as it moves
through the printer. It is recommended that printers using cut-sheet media use the left corner closest to the
leading edge of the sheet as it moves through the printer.
For printers using envelope media, the top edge of the sheet is as shown in Figure 20.
For printers using computer output on microfilm (COM), a sheet is a data frame, and the top edge of the sheet
is a short side of a frame. Figure 21, Figure 22, and Figure 23 illustrate the
top edge of the sheet for COM for various frame arrangements.
Note that the top edge of the sheet is fixed for each printer and for envelopes, and the XOH-SMO command
does not change the location of the top edge of the sheet. In addition, the XOH-SMO command does not alter
the relationship between the X
m axis and the $Y_m$ axis. The $Y_m$ axis is rotated 90 degrees clockwise from the Xm
axis regardless of the positioning of the medium presentation space origin with respect to the physical medium.
When Xm-axis duplex is in effect, the top edge of the sheet for the back side of a duplex sheet is the opposite
edge as that used for the front side. When Ym-axis duplex is in effect, the top edge of the sheet for the back
side of a duplex sheet is the same edge as that used for the front side.
For the front side of a duplex sheet, the origin of the medium presentation space moves in a clockwise
direction with respect to the top edge of the sheet. For the back side of a duplex sheet, the origin of the
medium presentation space moves in a counter-clockwise direction with respect to the top edge of the sheet.
**Figure 64** through Figure 70 illustrate the XOH Set Media Origin command for the
various kinds of media. [IPDS-4-1775]


**Figure 64**. The XOH Set Media Origin Command (Cut-Sheet Media)
Back Side of the Sheet
Note: The shaded circles in the illustration represent holes punched through the sheet and
show how the sheet was flipped from front side to back side.
Front Side of a Sheet
SMO=X'00' SMO=X'01'
SMO=X'03' SMO=X'02'
Top
$Y_m$-Axis Duplex (Normal)
SMO=X'00' SMO=X'03'
SMO=X'01' SMO=X'02'
Top
$X_m$-Axis Duplex (Tumble)
SMO=X'00' SMO=X'03'
SMO=X'01' SMO=X'02'
Top


**Figure 65**. The XOH Set Media Origin Command (Wide Continuous-Forms Media)
Front Side of Sheets
SMO=X'00'
SMO=X'00'
SMO=X'01'
SMO=X'01'
SMO=X'03'
SMO=X'03'
SMO=X'02'
SMO=X'02'
Back Side of Sheets
Note: The shaded circles in the illustration represent holes punched through the sheets and
show how the sheets were flipped from front side to back side.
$Y_m$-Axis Duplex (Normal) $X_m$-Axis Duplex (Tumble)
TopTop
SMO=X'02' SMO=X'03'
SMO=X'01' SMO=X'00'
To
p
Top
Top
Top


**Figure 66**. The XOH Set Media Origin Command (Narrow Continuous-Forms Media)
Front Side of Sheets
SMO=X'00'
SMO=X'00'
SMO=X'01'
SMO=X'01'
SMO=X'03'
SMO=X'03'
SMO=X'02'
SMO=X'02'
Back Side of Sheets
Note: The shaded circles in the illustration represent holes punched through the sheets and
show how the sheets were flipped from front side to back side.
$Y_m$-Axis Duplex (Normal) $X_m$-Axis Duplex (Tumble)
Top
Top
Top
Top
SMO=X'02'
SMO=X'03'
SMO=X'01'
SMO=X'00'Top
Top


**Figure 67**. Examples of Commonly Used SMO/Duplex Combinations
Note: The shaded circles in the illustration represent holes punched through the sheets and
show how the sheets were flipped from front side to back side.
Example with SMO = X'00' used on both sides
Example with SMO = X'03' used on both sides
Front Side of Sheets Back Side of Sheets
Back Side of Sheets
Top of back page
Top of back page
Page 2
Page 4
Y
m
Xm
$Y_m$-Axis Duplex (Normal)
Top of back page
Top of back page
Page 2
Page 4
$X_m$-Axis Duplex (Tumble)
Y
m
Xm
Front Side of Sheets
Top of front page
Top of front page
Page 1
Page 3
Y
m
Xm
Top of front page
Page 1
Top of front page
Page 3
Ym
X
m
$X_m$-Axis Duplex (Tumble)
Top of back page
Page 4
Top of back page
Page 2
Ym
$X_m$
$Y_m$-Axis Duplex (Normal)
Top of back page
Page 4
Top of back page
Page 2
Ym
X
m


**Figure 68**. The XOH Set Media Origin Command (Front Side of an Envelope)
SMO=X’00’ SMO=X’01’
SMO=X’02’SMO=X’03’
TOP
BOTTOM
L
E
F
T
R
I
G
T
H
SMO=X’00’ SMO=X’03’
SMO=X’02’SMO=X’01’
RIGHT
LEFT
T
O
P
B
O
T
T
O
M
**Figure 69**. The XOH Set Media Origin Command (Back Side of an $X_m$-Axis Duplex Envelope)
SMO=X’00’
SMO=X’01’SMO=X’02’
SMO=X’03’
TOP
BOTTOM
L
E
F
T
R
I
G
T
H
SMO=X’00’
SMO=X’03’ SMO=X’02’
SMO=X’01’
RIGHT
LEFT
T
O
P
B
O
T
T
O
M
**Figure 70**. The XOH Set Media Origin Command (Back Side of a $Y_m$-Axis Duplex Envelope)
SMO=X’00’
SMO=X’01’ SMO=X’02’
SMO=X’03’
TOP
BOTTOM
L
E
F
T
R
I
G
T
H
SMO=X’00’
SMO=X’03’SMO=X’02’
SMO=X’01’
RIGHT
LEFT
T
O
P
B
O
T
T
O
M


The medium presentation space origin does not change until either another XOH-SMO command is processed
or the printer is reinitialized (returns an IML NACK).
The XOH-SMO command does not alter the orientation of the physical medium and the physical printable
area, or their reflection in the medium presentation space. Therefore, changing the medium presentation
space origin by an XOH Set Media Origin command causes the origin, the current valid printable area, and the
user printable area to be effectively rotated and translated relative to the physical medium, the physical
printable area, and their reflection in the medium presentation space. This can cause a change in the
dimensions of the current valid printable area and the user's valid printable area.
Note: The dimensions of the medium presentation space and therefore the location of the corners of the
medium presentation space are determined by the printer based on valid sensor or operator input,
processing of an XOH Set Media Size command, or a combination of both; refer to “XOH Set Media
Size”.
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'1600' | Set Media Origin (SMO) order code | X'1600' [IPDS-4-1776]|
| 2 | CODE | Origin | X'00'–X'03' | Medium presentation space origin:<ul><li>X'00' Top-left corner</li><li>X'01' See byte description</li><li>X'02' Bottom-right corner</li><li>X'03' See byte description</li></ul> | X'00'<br>X'01'<br>X'02'<br>X'03' [IPDS-4-1777]|
Bytes 0–1 Set Media Origin order code
Byte 2 Medium Presentation Space Origin
This parameter specifies the medium presentation space origin. Exception ID X'026F..02'
exists if an invalid origin value is specified.
X'00' Set the medium presentation space origin to correspond to the top-left corner of the
medium presentation space. The $X_m$ axis of the medium presentation space
corresponds to the top edge of the sheet and positive Xm values begin at the origin
and increase from left to right. The $Y_m$ axis of the medium presentation space
corresponds to the left edge of the sheet and positive Ym values begin at the origin
and increase from top to bottom.
X'01' For the front side of a duplex sheet, set the medium presentation space origin to
correspond to the top-right corner of the medium presentation space. The $X_m$ axis of
the medium presentation space corresponds to the right edge of the sheet and
positive Xm values begin at the origin and increase from top to bottom. The $Y_m$ axis of
the medium presentation space corresponds to the top edge of the sheet and positive
Ym values begin at the origin and increase from right to left.
For the back side of a duplex sheet, set the medium presentation space origin to
correspond to the bottom left corner of the medium presentation space. The X
m axis of
the medium presentation space corresponds to the left edge of the sheet and positive
$X_m$ values begin at the origin and increase from bottom to top. The $Y_m$ axis of the
medium presentation space corresponds to the bottom edge of the sheet and positive
Ym values begin at the origin and increase from left to right.
X'02' Set the medium presentation space origin to correspond to the bottom-right corner of
the medium presentation space. The $X_m$ axis of the medium presentation space
corresponds to the bottom edge of the sheet and positive Xm values begin at the origin
and increase from right to left. The $Y_m$ axis of the medium presentation space
corresponds to the right edge of the sheet and positive Ym values begin at the origin
and increase from bottom to top. [IPDS-4-1778]


X'03' For the front side of a duplex sheet, set the medium presentation space origin to
correspond to the bottom left corner of the medium presentation space. The $X_m$ axis of
the medium presentation space corresponds to the left edge of the sheet and positive
$X_m$ values begin at the origin and increase from bottom to top. The $Y_m$ axis of the
medium presentation space corresponds to the bottom edge of the sheet and positive
Y
m values begin at the origin and increase from left to right.
For the back side of a duplex sheet, set the medium presentation space origin to
correspond to the top right corner of the medium presentation space. The $X_m$ axis of
the medium presentation space corresponds to the right edge of the sheet and
positive X
m values begin at the origin and increase from top to bottom. The $Y_m$ axis of
the medium presentation space corresponds to the top edge of the sheet and positive
Y
m values begin at the origin and increase from right to left. [IPDS-4-1779]


XOH Set Media Size
The XOH Set Media Size (SMS) command specifies a desired medium presentation space size to be used for
valid printable area calculations and N-up partitioning. This command applies to the sheet that the next
received page is printed on unless this order is received between the pages of a sheet; in this case, it does not
take effect until the next sheet. It also applies to all future sheets, regardless of media source, until another
XOH-SMS command is received or the printer is reinitialized (returns an IML NACK).
The length of the XOH-SMS command can be:
Without CID X'000E'
With CID X'0010'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
For cut-sheet, envelope, and COM media, the XOH Set Media Size command does not change the width and
length of the sheet as reported in the XOH Obtain Printer Characteristics reply. For continuous-forms media,
the XOH Set Media Size command does not change the width of the sheet, but it does determine the length as
reported in the XOH Obtain Printer Characteristics reply.
The printer determines the dimensions used from the methods in the following hierarchical list:
1. If an XOH-SMS command has been received and no printer-defined valid sensor or operator input exists, [IPDS-4-1780]
use the XOH-SMS supplied extents to reflect the medium presentation space size. In this case, the
medium presentation space width and length that are generated by the printer in the Printable-Area self-
defining field of the XOH-OPC reply contain the XOH-SMS supplied extents.
2. If a printer defined valid sensor or operator input exists, and if no XOH-SMS command has been received [IPDS-4-1781]
or if this command is not supported, use the sensor or operator input (in that order) to reflect the medium
presentation space size. In this case, the medium presentation space width and length that are generated
by the printer in the Printable-Area self-defining field of the XOH-OPC reply contain the printer defined valid
sensor or operator input.
3. If both the XOH-SMS extents and the printer-defined valid sensor or operator input exist, use the smaller of [IPDS-4-1782]
the XOH-SMS extents and the valid sensor or operator input in each dimension to reflect the medium
presentation space size. In this case, the medium presentation space width and length that are generated
by the printer in the Printable-Area self-defining field of the XOH-OPC reply contain the printer defined valid
sensor or operator input.
Note: For continuous-forms printers, the XOH-SMS command determines the length of the sheet and the
size of the physical printable area in the length direction. Some printers use the SMS values
unconditionally and some provide an option to use SMS values or the smaller extents as previously
described. If the SMS values are used unconditionally, and the SMS values are larger than the
physical media size, some print data might be lost and the host might not be able to track paper
usage accurately.
4. If neither XOH-SMS extents or printer defined valid sensor or operator input exist, use the printer default [IPDS-4-1783]
medium presentation space size. In this case, the medium presentation space width and length that are
generated by the printer in the Printable-Area self-defining field of the XOH-OPC reply contain the printer
default medium presentation space size.
5. A XOH-SMS extent of X'FFFF' in either dimension means ignore the previous XOH-SMS extent and use [IPDS-4-1784]
the printer defined valid sensor or operator input for that dimension of the medium presentation space. If no
valid printer defined sensor or operator input exists, use the corresponding dimension of the printer default
medium presentation space size. In the latter case, the media dimension generated by the printer in the
Printable-Area self-defining field of the XOH-OPC reply contains the appropriate printer default medium
presentation space dimension.
It is recommended that the new front-side medium presentation space be a rectangle of size $X_m$ extent by Ym
extent whose origin is at the default media origin. If duplexing, the back-side medium presentation space
should be physically lined up with the front-side presentation space as if the physical media had been cut to the
new size.


Notes:
1. If an XOH-SMS command changes the Xm and Ym extents of the medium presentation space and the [IPDS-4-1785]
medium presentation space origin as set by a previous XOH-SMO command does not correspond to the
default physical media origin, the printer must recompute the origin of the medium presentation space.
2. The medium presentation space size specified in accordance with these rules is used in all valid printable [IPDS-4-1786]
area calculations. Exception ID X'08C1..00' exists if an attempt is made to merge print data outside the
valid printable area in the medium presentation space.
The data field for the Set Media Size order has the following format: [IPDS-4-1787]
| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | Order code | X'1700' | Set Media Size (SMS) order code | X'1700' [IPDS-4-1788]|
| 2 | CODE | Unit base | X'00'<br>X'01' | Unit base for this command:<ul><li>X'00' Ten inches</li><li>X'01' Ten centimeters</li></ul> | X'00' [IPDS-4-1789]|
| 3–4 | UBIN | UPUB | X'0001'–X'7FFF' | Units per unit base | X'3840' [IPDS-4-1790]|
| 5–6 | UBIN | $X_{m}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{m}$ extent of the medium presentation space:<ul><li>X'FFFF' Printer default</li></ul>Required range: X'000A'–X'2FD0' (Refer to the note following the table.) | X'FFFF' [IPDS-4-1791]|
| 7–8 | UBIN | $Y_{m}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{m}$ extent of the medium presentation space:<ul><li>X'FFFF' Printer default</li></ul>Required range: X'000A'–X'4EC0' (Refer to the note following the table.) | X'FFFF' [IPDS-4-1792]|
Note: The required range for fields expressed in L-units has been specified assuming a unit of measure of
1/1440 of an inch. Many receivers support the required range plus additional function. If a receiver
supports additional units of measure, the IPDS architecture requires the receiver to at least support a
range equivalent to the required range relative to each supported unit of measure. More information
about supported-range requirements is provided in the section titled “L-Unit Range Conversion
Algorithm”.
Bytes 0–1 SMS order code
Byte 2 Unit base
A value of X'00' indicates that the unit base is ten inches. A value of X'01' indicates that the
unit base is ten centimeters.
The value X'02' is retired as Retired item 51.
Property pair X'FB00' in the Device-Control command-set vector of an STM reply indicates
support for all architected units of measure.
Exception ID X'0274..02' exists if an invalid or unsupported unit base value is specified.
Bytes 3–4 Units per unit base
These bytes specify the number of units per unit base for this command.
Exception ID X'0270..02' exists if an invalid or unsupported units-per-unit-base value is
specified.


Bytes 5–6 Xm extent
These bytes specify the Xm extent of the medium presentation space to be used for printable-
area calculations in accordance with the specified hierarchical rules. Refer to “Xm,Ym
Coordinate System (Medium)” for a description of how the medium presentation
space relates to the physical media, the physical printable area, the medium presentation
space origin, and the XOH-OPC width and length values.
Exception ID X'0272..02' or X'0262..02' exists if an invalid or unsupported Xm extent value is
specified; the preferred exception ID is X'0272..02'.
Bytes 7–8 Ym extent
These bytes specify the Ym extent of the medium presentation space to be used for printable-
area calculations in accordance with the specified hierarchical rules.
Exception ID X'0273..02' or X'0263..02' exists if an invalid or unsupported Y
m extent value is
specified; the preferred exception ID is X'0273..02'. [IPDS-4-1793]


**Figure 71**. Examples Showing the Effect of SMS (Method 3) and SMO Command Combinations
Width = 11"
Length = 8.5"
X PPAoffset = 0
Y PPAoffset = 0
X PPAextent = 8.5"
Y PPAextent = 11"
m
m
m
m
XOH-OPC Printable Area information:
Width = 11"
Length = 8.5"
X PPAoffset = 0
Y PPAoffset = 0
X PPAextent = 11"
Y PPAextent = 8.5"
m
m
m
m
XOH-OPC Printable Area information:
Width = 11"
Length = 5"
Width = 11"
Length = 6"
X PPAoffset = 0
Y PPAoffset = 0
X PPAextent = 5"
Y PPAextent = 11"
m
m
m
m
X PPAoffset = 0
Y PPAoffset = 0
X PPAextent = 11"
Y PPAextent = 6"
m
m
m
m
XOH-OPC Printable Area information: XOH-OPC Printable Area information:
XOH SMO = X'00' XOH SMO = X'03' (or some non-SMO printers)
Xm
Xm
Default media origin Media origin
Ym
Ym
Width = 11" Width = 11"
Length = 8.5"
Length = 8.5"
Medium
Presentation
Space as
specified by
SMS command
Medium
Presentation
Space as
specified by
SMS
command
Xm
Default media origin Media origin
Ym
XOH SMS command: XOH SMS command:
X extent = 5"
Y extent = 6"
m
m
X extent = 5"
Y extent = 6"
m
m
5"
5"
6"
6"
1st sheet 1st sheet
1st sheet 1st sheet
2nd sheet
2nd sheet
3rd sheet
2nd sheet 2nd sheet
Xm
Ym


XOH Specify Group Operation
The XOH Specify Group Operation (SGO) command indicates to an attached printer, pre-processor, or post-
processor that the specified processing option is to be performed upon subsequent boundary groups of the
group level identified in this command. All XOH-SGO commands remain in effect until either an XOH-ERPD
command resets group operations or the printer is reinitialized (returns an IML NACK).
The length of the XOH-SGO command can be:
Without CID X'0009'
With CID X'000B'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
Each XOH-SGO command is saved by the printer for use with subsequent groups. It is valid to specify multiple
XOH-SGO commands that use the same group level, but specify different group operations; in this case
multiple operations are applied to any subsequent group with that group level. It is also valid to specify multiple
XOH-SGO commands that use the same operation, but specify different group levels; in this case the
operation is applied to each group that contains one of the specified group levels. Figure 61
shows an example illustrating these combinations. XOH-SGO commands that duplicate a previously received
XOH-SGO command are ignored.
Subsequent XOH Define Group Boundary (XOH DGB) commands identify the groups of pages to which the
group operation applies. The reply to an STM command indicates whether or not the printer supports these two
XOH orders. The Supported Group Operations self-defining field in the reply to an XOH-OPC command
specifies which group operations are supported.
The group operations that apply to a particular group are those that are in effect when the XOH-DGB
command that initiates the group is received. If an XOH-SGO command is received within or after a group it
does not apply to the open group, but can apply to a subsequently received group.
This is an optional command that not all printers support. If this command is not sent to a printer or if the printer
does not support the command, the group operation is not processed.
The format of the XOH-SGO command is as follows:
Offset Type Name Range Meaning Required
0–1 CODE Order code X'0300' Specify Group Operation (SGO) order code X'0300'
2 CODE Operation X'01' [IPDS-4-1794]
X'02'
X'03'
X'04'
X'05'
X'06'
Keep group together as a print unit
Keep group together for microfilm output
Save pages
Finish
Identify named group
Keep group together as a recovery unit
At least one
operation
3 UBIN Group level X'00'–X'FF' Group Level of Boundary groups to which the [IPDS-4-1795]
operation is to be applied.
X'00'–X'FF'
Bytes 0–1 SGO order code [IPDS-4-1796]


Byte 2 SGO Operation Identifier
This byte identifies the operation the printer, pre-processor, or post-processor is to perform on
the specified group. The types of group operations supported by a printer are returned in the
Supported Group Operations self-defining field in the XOH-OPC reply. Unsupported
operations are ignored.
The currently defined operations are:
X'01' Keep group together as a print unit.
A print unit is atomic. During an IPDS dialog, a printer or intermediate device must
preserve the IPDS environment as established by the IPDS presentation services
program. If the printer has the capability of accepting and printing data from other data
streams or sessions, the printed pages that comprise the print unit must be printed
and kept together in the same manner as if the printer had been dedicated to this
IPDS session. If the pages cannot be printed and kept together in this manner, a
catastrophic event exists that requires the printer to generate exception ID X'018F..00'
(error printer restart).
Notes:
1. X'01' is used when communicating with the Remote PrintManager (RPM) and [IPDS-4-1797]
when using Distributed Print Facility (DPF) to keep print units together. It is also
used with printers that support sharing of the printhead among print sessions on
the various ports; this is sometimes called dynamic port switching.
2. Since groups can be nested, specifying the group operation appropriately is [IPDS-4-1798]
important. For example, an MVS print job can be wrapped with XOH-DGB
commands, and each data set within the print job can also be wrapped with XOH-
DGB commands; when driving a port-switching printer, the XOH-SGO X'01'
operation should apply to the outermost group (the print job), not the inner (data
set) groups.
3. A printer might provide a timer that is set within a print unit whenever the host [IPDS-4-1799]
stops communicating, to catch situations where the host has an ABEND condition
or is hung. When such a timer expires within a print unit and the printer switches to
another print session, the printer must issue exception ID X'018F..00' (error printer
restart) the next time an IPDS command is received on the hung session.
X'02' Keep group together for microfilm output
X'03' Save pages
This operation directs the printer to process each page of the group normally and
report data stream exceptions, but to save each page rather than printing it. The
pages of the group are each assigned a sequence number by the printer, and kept
together along with the variable-length group ID that is specified in the XOH-DGB
command that begins the group.
Notes:
1. The processing part of this operation is device specific; the architectural [IPDS-4-1800]
requirement is that the page to be saved must be processed with all of the needed
resources (CMRs, fonts, overlays, page segments) so that these resources can all
be deactivated after the processing is complete. Also, all data stream syntax
checking must be done for the page to be saved and all overlays included within
the page, and appropriate exceptions reported.
All appropriate CMRs are applied when the page is saved. When a saved page is
later included with an ISP command, no additional color management or color
simulation is done by the IPDS receiver. [IPDS-4-1801]


Metadata received in a saved page, either directly in the page or in any object
included on the page, is associated with the appropriate object(s) as if the page
were being printed. Similarly, home-state metadata in effect at the time the saved
page is processed is associated with the saved page and everything on it.
However, no metadata is saved with the page, and any associations are lost when
the End Page command is processed.
2. If a request for MICR printing is received within a page to be saved, either the [IPDS-4-1802]
printer must remember this request with the saved page or issue exception ID
X'02B3..01'. If the MICR request is remembered with the saved page, when the
saved page is later included, the printer must ensure that MICR printing is
available for the specific data; if not, exception ID X'02B3..01' exists.
3. Printers that support UP [IPDS-4-1803]
3I finishing can also support saved pages. However, some
printers cannot support UP3I Print Data objects (that are printed by a pre-
processing or post-processing device) while saving pages. In this case, if a UP3I
Print Data object is found in the data stream, exception ID X'020D..02' exists.
4. Preprinted form overlays can be used with saved pages. However, while saving [IPDS-4-1804]
pages, all Include Overlay commands for PFOs are ignored. When using saved
pages, a preprinted form overlay can be invoked for a page with either of the
following sequences of commands:
• BP , IO-with PFO parameter, ISP , EP [IPDS-4-1805]
• BP , ISP , IO-with PFO parameter, EP [IPDS-4-1806]
The pages are assigned four-byte sequence numbers with the first page assigned
X'00000001', and each subsequent page assigned a sequence number that is one
higher than the previously saved page. If there is not enough room to store a page,
exception ID X'02AF..01' exists.
Groups that do not have a variable-length group ID, in the XOH-DGB command that
initiates the group, are not saved. If the printer has a previously saved group with the
same variable-length group ID, exception ID X'0255..00' exists. The saved pages
remain in the printer until either an XOH-RSPG command is received to remove that
group of saved pages, the printer removes the group while it is inactive, or the printer
removes the group due to a printer restart condition (action code X'0D'); not all printer
restart conditions cause saved pages groups to be discarded. The XOH-DSPG
command deactivates a saved page group, but does not necessarily cause the printer
to remove it; the printer manages a deactivated saved page group as a resident
resource, and thus can remove it at any time while it is inactive. The first time a page
from a deactivated group is called for in an ISP command, the group is activated and
can no longer be removed.
As the commands within the group are processed, syntax checking of the data stream
is done, and NACKs are reported in the same manner as if the pages were being
printed. All data stream NACKs that are associated with a particular page contain the
sequence number of that page. The received page counter is not incremented for the
pages to be saved; the saved pages are part of a resource.
Copies and copy subgroups specified in the LCC command are ignored when saving
a page and medium overlays are not saved with the group. However, text suppression
for suppression IDs specified in an LCC command are processed normally. This
means that a separate copy of the page is saved for each combination of text
suppressions specified in the copy subgroups of the LCC command.
While VPA checking for a page to be saved, only the current logical page is used, the
physical printable area and user printable area are ignored. Page overlays are saved
with the page data; using only the overlay's current logical page for VPA checking. If
data extends outside of the appropriate current logical page, exception ID X'08C3..00'
exists.


Note: The goal of this semantic is to ensure that a saved page that includes overlays
will print the same way with a later ISP command as it would have printed when
it was saved (assuming the same size paper). There are several ways that this
could be implemented. For example, an area big enough to contain all of the
overlays and the page could be used; in this case, the origin and size of the
page must also be saved for use when positioning the overlay at ISP time.
Another method is to save the page and each overlay as a separate object and
link these objects together. For this method, care must be taken to preserve the
mixing rules; that is, data within a page overlay that overlaps the containing
page's logical page must be saved with the page; only overlay data outside the
page's logical page need be saved separately.
Before starting to save pages, the currently active LCC information is saved so that it
can be restored at the end of the group. This allows a group of pages to be saved
while in Home State, including Home State that occurs between the pages of a multi-
page sheet to be printed. At the end of the saved page group, the saved LCC
information is restored and processing continues as if the saved page group had not
occurred. Print-control commands are ignored and are not saved with the pages.
Print-control commands include: AFO, DUA, LPP , XOA APA, XOA AOS, XOA CEM,
XOA DBD, XOA DUP , XOA MF , XOH EFF , XOH ERFD, XOH PCC, XOH PBD, XOH
SIMS, XOH SMM, XOH SCF , XOH SMO, XOH SMS, and XOH SRP.
Nesting of saved pages is not allowed. If an ISP command is specified within a page
that is being saved, exception ID X'0255..05' exists. Refer to “Saving and Including
Pages” for an example of how various IPDS commands are used for
saving and including pages.
When synchronous data-stream exceptions are encountered while saving a group of
pages, the XOA-EHC command functions as if the pages were being printed. Thus
partial pages or full pages can be saved by the printer, with appropriate exception
highlighting. When partial pages are discarded because of an XOA-EHC setting, the
page is not saved by the printer and the page sequence number is not incremented.
Therefore, errors that exist within a group of pages to be saved can cause only some
of the pages to be saved. While saving a page, the printer does not increment the
received page counter nor does it adjust the counters when processing a
synchronous data-stream exception.
When asynchronous exceptions are encountered while receiving the data for a page
to be saved, the incomplete page is discarded. Also, as part of the recovery for an
asynchronous non-storage exception, all incomplete groups of saved pages are
discarded. The XOA-RRL command can be used to determine which saved page
groups are in the printer.
X'04' Finish
This operation directs the printer to finish the sheets containing a group of pages that
have been collected in a page group. The specific finishing operation parameters are
specified in zero or more Finishing Operation (X'85') triplets and UP
3I Finishing
Operation (X'8E') triplets contained in the XOH-DGB command that either initiates or
terminates the group. If multiple Finishing Operation (X'85' or X'8E') triplets are
specified, the operations are applied in the order received and duplicate operations
are ignored. If no Finishing Operation (X'85' or X'8E') triplets are specified in either
XOH-DGB command, no finishing operation is applied.
X'05' Identify Named Group
This operation directs the printer to associate a group name with a group. The name is
specified in the XOH-DGB command that initiates the group and contains a Group
Information (X'6E') triplet using the Group Name format. [IPDS-4-1807]


X'06' Keep Group Together as a Recovery Unit
This operation directs the printer to keep the printed pages of the group together with
no intervening blank sheets.
A new generation of very fast printers have been designed such that the printer
cannot immediately stop paper movement on a sheet boundary and will emit a
number of blank sheets each time the paper movement is halted; such printers return
property pair X'F002' in the Device-Control command-set vector of an STM reply. If
groups are split through one or more blank sheets when the printer stops, these
groups cannot be finished correctly and often must be reprinted. This even happens
when the printer pauses briefly to wait for data while remaining ready. Even with
conventional printers that are able to stop without emitting blank pages, the end of
paper or a paper jam will split such groups into two separate parts. The Keep-Group-
Together-as-a-Recovery-Unit operation allows the printer to manage such groups and
to provide recovery on a group boundary. If a stop or a pause within such a group
does occur, the printer will issue an appropriate NACK to cause appropriate host-level
recovery.
This group operation affects IPDS counters, exception handling, and commands as
follows:
Suspending the operation for groups that do not start on a sheet boundary
Because error recovery must occur on a sheet boundary, it is
recommended that all recovery-unit groups begin on a sheet
boundary. In the case where a recovery-unit group is begun on a
sheet that already contains pages, the group will continue, but the
operation will be suspended for this group and the printer will
increment the jam recovery page counter and stacked page counter
in a normal sheet-by-sheet manner; blank sheets might occur within
this group in which case exception ID X'4040..00' or X'0140..00'
exists with action code X'2B'. The operation will resume with
subsequent groups that do begin on a sheet boundary.
Committed page counter
Optionally, the printer can delay incrementing the committed page
counter until the whole group has been received; this guarantees that
the host doesn't cause blank sheets by stopping the group in the
middle for some reason, or simply prevent the last part of the data
from arriving too late. This is an optional behavior that printers do not
have to support even if other Recovery-Unit functions are
implemented.
Jam recovery page counter
Assuming the group operation has not been suspended, the printer
will delay incrementing the jam recovery page counter until the last
copy of the last page in the group has passed the jam-recovery point
and will then increment the counter by the number of pages in the
group plus any additional pages on the last sheet of the group. If a
paper jam happens, the presentation services program will then
reposition to a group boundary automatically.
The XOH Stack Received Pages command does cause all received
pages to be stacked and reflected in the jam-recovery and stacked-
page counters.
Stacked page counter
Assuming the group operation has not been suspended, the printer
will delay incrementing the stacked page counter until the last copy of
the last page in the group has been stacked and will then increment [IPDS-4-1808]


the counter by the number of pages in the group plus any additional
pages on the last sheet of the group. This will allow the presentation
services program to recover on a group boundary if either the printer
or the presentation services program is shutdown in the middle of a
group.
The XOH Stack Received Pages command does cause all received
pages to be stacked and reflected in the jam-recovery and stacked-
page counters.
Error recovery situations that require repositioning
The printer will handle error recovery situations that require
repositioning as follows so that action codes X'08', X'09', X'0A', X'15',
X'16', X'17', X'1A', X'1B', X'1D', X'1E', and X'23' cause the
presentation services program to restart on a group boundary:
• If a paper jam occurs, the normal exception ID X'40E5..00' will be [IPDS-4-1809]
used. Since a partial group has not yet passed the jam-recovery
point, the host system will automatically reposition to a group
boundary. The same will also apply in the case of other jam
exceptions, such as staple jam (X'407C..01').
• If the Not-ready button has been pressed, the printer should try to [IPDS-4-1810]
avoid stopping within a group. If this is successful, the normal
printer not ready exception ID X'4000..00' is reported. If for some
reason, the printer has to stop within the group such that already
committed pages must be discarded, exception ID X'4040..00' is
reported.
• If any other intervention required condition, equipment check, or [IPDS-4-1811]
condition requiring host notification occurs that causes the printer to
stop, an appropriate exception ID can be issued so that recovery
occurs on a group boundary. If the printer manages to stop outside
of a group, the normal exception ID for the specific condition
(X'40xx..xx', X'50xx..xx', X'01xx..xx', or X'02AF..01') will be
reported. If the printer must stop within the group such that already
committed pages must be discarded, exception ID X'4040..00' is
reported.
• If the continuous printing is interrupted for some reason such that [IPDS-4-1812]
already committed pages must be discarded but the printer remains
ready, exception ID X'0140..00' will be reported. This will cause the
group to be reprinted.
• If the presentation services program sends more pages within a [IPDS-4-1813]
recovery-unit group than the printer can keep together, exception
ID X'0141..00' will be reported. The OPC reply Keep-Group-
Together-as-a-Recovery-Unit self-defining field identifies the
maximum number of sheets allowed within a recovery-unit group;
these sheets include sheets containing pages and copies of such
sheets.
XOA Discard Buffered Data
If an XOA Discard Buffered Data command is received, some, but not
necessarily all pages of a recovery-unit group might have been
committed. Therefore, using this command in the middle of a
recovery-unit group can cause blank sheets to be emitted (which
results in exception ID X'0140..00'); also, if the host repositions to the
beginning of the partial group, there will be extra (waste) pages of
that group in the paper path. Printers that delay incrementing the [IPDS-4-1814]


committed page counter until all pages of a group have been
processed will be able to discard all received pages of the group; in
this case, any blank sheets will be between recovery-unit groups.
XOA Discard Unstacked Pages
If an XOA Discard Unstacked Pages command is received, since the
stacked page counter is not incremented until the complete group of
pages has been stacked, any incomplete groups will be discarded.
XOH Page Counters Control
If an XOH Page Counters Control command with a counter update
value of X'02' is received, since the stacked page counter is not
incremented until the complete group of pages has been stacked,
any incomplete groups will be discarded.
XOH Print Buffered Data
If an XOH Print Buffered Data command is received, all sheets that
are complete but not yet committed will be printed. Pages of an
incomplete sheet cause the Received Page Counter to be greater
than the Committed Page Counter. This handling is analogous to the
similar case with duplex or n-up pages. Using this command in the
middle of a recovery-unit group can cause blank sheets to be emitted
(which results in exception ID X'0140..00').
XOH Stack Received Pages
If an XOH Stack Received Pages command is received, the printer
will commit and stack all buffered pages (even those of an incomplete
recovery-unit group). Therefore, using this command in the middle of
a recovery-unit group can cause blank sheets to be emitted (which
results in exception ID X'0140..00') and will cause all received pages
to be stacked and reflected in the jam-recovery and stacked-page
counters.
Cut Sheet Emulation
Using cut sheet emulation can affect the printer's ability to keep
recovery-unit groups together because:
• The printer increments the jam recovery page counter and stacked [IPDS-4-1815]
page counter on a sheet basis rather than on a sheetlet basis.
• The presentation services program cannot control when a group is [IPDS-4-1816]
on a sheet boundary.
When cut sheet emulation is being used, if a recovery-unit group
does not begin on a sheet boundary, the group operation is
suspended and blank sheets might occur within that group in which
case exception ID X'4040..00' or X'0140..00' exists with action code
X'2B'. For example, when the last pages of a group are on the left
sheetlet and the first pages of another group are on the right sheetlet,
the operation will be suspended for the second group.
N-Up and Duplex
When multiple pages are placed on a sheet with N-up, explicit page
placement, or duplex, it is recommended that all recovery-unit groups
begin on a sheet boundary (otherwise the printer will suspend the
operation as described previously).
Byte 3 Group Level
This byte contains the Group Level, contained in subsequent XOH-DGB commands,
delimiting the group of pages upon which the specified operation is to be performed. [IPDS-4-1817]


XOH Stack Received Pages
The XOH Stack Received Pages (SRP) command causes the printer to do the following in the specified order:
1. Eject to the next sheet if not already on a new sheet. The next received page will be the first page on the [IPDS-4-1818]
new sheet. This occurs whether or not cut-sheet emulation mode is in effect.
2. Perform an XOH Print Buffered Data. [IPDS-4-1819]
3. Stack all pages that have been committed for printing. [IPDS-4-1820]
The length of the XOH-SRP command can be:
Without CID X'0007'
With CID X'0009'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
When the command is completed, the stacked-page counter equals the received-page counter and all copy
counters are zero. Any blank pages that the printer generated to accomplish this function are not included in
the page or copy counters.
Note: XOH Stack Received Pages is a synchronizing command. Any command following a synchronizing
command is not processed until all preceding commands are completely processed. If the XOH Stack
Received Pages command requests an acknowledgment, the Acknowledge Reply is not returned until
the Stack Received Pages processing is complete.
This order is not cumulative; consecutive SRP orders produce the same effect as a single order.
If an XOH Stack Received Pages command is received within a group to be finished, all received pages are
stacked and the group is unaffected. However, for finishing operations that are applied at the end of the group,
the prematurely stacked pages might or might not have the finishing operation applied.
Offset Type Name Range Meaning Required
0–1 CODE Order code X'0D00' Stack Received Pages order code X'0D00' [IPDS-4-1821]


XOH Trace
XOH Trace is a home state command used to start, stop, and obtain IPDS traces. When an IPDS trace is
started, the printer records a trace entry for each requested trace option as it processes IPDS commands.
Trace entries are kept in the same sequential order as the IPDS commands are received.
The length of the XOH-TRC command can be:
Without CID X'000A'–X'7FFF'
With CID X'000C'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
There is only one IPDS trace running at a time and the printer will continue to add trace entries until one of the
following events occur:
• XOH Trace command is received to stop the trace; if a subsequent XOH Trace command is received to start [IPDS-4-1822]
the trace, new trace entries are added to the existing trace (this sequence effectively suspends the trace for
a period of time).
• XOH Trace command is received to obtain the trace; all trace entries are discarded after they have been sent [IPDS-4-1823]
to the host.
• The maximum amount of space available for tracing is reached. In this case exception ID X'0113..00' exists [IPDS-4-1824]
and one final Trace Full entry is added at the end of the trace. The next IPDS command determines what
happens after the exception ID is reported:
– If the next command is XOH Trace to start the trace, all trace entries are discarded and a new trace
begins.
– If the next command is XOH Trace to obtain the trace, the trace is returned in Acknowledge Replies and
the trace ends.
– If the next command is XOH Trace to stop the trace, the printer stops the trace and continues with the next
IPDS command.
– If the next command is any other IPDS command, the printer processes that command and continues
processing commands until an XOH Trace command is encountered.
• The printer is reinitialized (returns an IML NACK). What happens to trace entries after an IML occurs is [IPDS-4-1825]
printer-specific.
To obtain a trace, the command must be sent to the printer with the Obtain Trace function selected and with the
ARQ flag set to B'1'. Since traces can contain many entries, it is recommended that the Long Acknowledge
Reply flag also be set to B'1' for printers that support long Acknowledge Replies.
Support for this optional command is indicated by the X'90F2' property pair in the Device-Control command-set
vector of an STM reply. [IPDS-4-1826]


Offset Type Name Range Meaning Required
0–1 CODE Order code X'F200' Trace order code X'F200'
2 CODE Function [IPDS-4-1827]
X'00'
X'01'
X'02'
Trace function:
Start trace
Stop trace
Obtain and delete trace
X'00'
X'01'
X'02'
3 BITS Control flags (only used when starting a trace) [IPDS-4-1828]
bit 0 Date and time
stamp
B'0'
B'1'
No date and time stamps
All trace entries contain a date and time stamp
B'0'
B'1'
bit 1 CMR name B'0'
B'1'
No CMR names
Trace entries contain names
B'0'
B'1'
bit 2 User data B'0'
B'1'
User data is obscured
User data can be returned in Free-Form
trace entries
B'0'
B'1'
bits 3–7 B'00000' Reserved B'00000'
4 X'00' Reserved X'00' [IPDS-4-1829]
Zero or more trace options in the following format (only used when starting a trace):
+ 0 CODE Option
X'00'
X'01'
X'02'
X'03'
X'04'
X'05'
X'06'
X'07'
X'08'
X'09'
X'0A'
X'0B'
X'0C'
X'0D'
X'0E'
X'0F'
X'10'
Trace option:
Complete trace
Page entries
Overlay entries
Presentation-Object entries
CMRs-Used entries
CMR-Activation/Deactivation entries
CMR-Invocation entries
Media-Source-Selection entries
Exception-ID entries
Free-Form entries
Include-Saved-Page entries
Include-Overlay entries
Include-Data-Object entries
Device-Appearance entries
Color-Fidelity entries
CMR-Tag-Fidelity entries
Begin-Print-Unit entries
X'00'
X'01'
X'02'
X'03'
X'04'
X'05'
X'06'
X'07'
X'08'
X'09'
X'0A'
X'0B'
X'0C'
X'0D'
X'0E'
X'0F'
X'10'
Bytes 0–1 Trace order code
Byte 2 Trace function
This parameter specifies one of the following functions:
• Start trace (X'00') causes the printer to begin tracing or, if a trace was already started, to [IPDS-4-1830]
continue tracing.
• Stop trace (X'01') causes the printer to stop tracing; the trace is left intact in the printer and [IPDS-4-1831]
can be obtained via a subsequent command. If the printer was not tracing when the stop
trace function is received, this command does nothing and is ignored.
• Obtain trace (X'02') causes the printer to do the following: [IPDS-4-1832]
1. Stop tracing [IPDS-4-1833]
2. If the ARQ flag is set to B'1' in this command, return the first portion of the stored trace in [IPDS-4-1834]
the Acknowledge Reply. The host can obtain the remaining portions of the trace (if any)
by using the acknowledge continuation method. The trace entries are returned in the
sequence they occurred beginning with the Begin-Trace trace entry. [IPDS-4-1835]


If the ARQ flag is B'0', this step is skipped.
3. Delete all trace entries. [IPDS-4-1836]
Exception ID X'025F..01' exists if an invalid trace-function value is specified.
Byte 3 Trace control flags
This parameter is used to control optional information within the trace entries. The flags are
used when starting a trace and are ignored when the XOH Trace command is used to stop or
obtain a trace. Requesting optional information causes larger trace entries to be generated.
The flags are defined as follows:
Bit 0 Date and time stamps
• When this flag is set to B'1', all trace entries contain a date and time stamp. [IPDS-4-1837]
• When this flag is B'0', the date and time stamp is omitted from all trace entries. [IPDS-4-1838]
Within each trace entry, bit 0 of the flags field identifies the presence or absence of the
date and time stamp information. Date and time stamp information is 13 bytes long.
Bit 1 CMR names
• When this flag is set to B'1', the following trace entries contain the CMR name for [IPDS-4-1839]
each identified CMR:
CMRs-Used trace entries
CMR-Activation trace entries
CMR-Invocation trace entries
CMR-Deactivation trace entries
• When this flag is B'0', the CMR names are omitted from all trace entries. [IPDS-4-1840]
Within each of the listed trace entries, bit 1 of the flags field identifies the presence or
absence of CMR names.
Bit 2 User data
• When this flag is set to B'1', Free-Form trace entries can contain user text data. [IPDS-4-1841]
• When this flag is B'0', all user text data in each Free-Form trace entry must be [IPDS-4-1842]
obscured by replacing each text character with a full-stop character (Unicode scalar
value U+002E, also called “period”).
Within each Free-Form trace entry, bit 1 of the flags field indicates whether user data
has been obscured.
Bits 3–7
Reserved
Byte 4 Reserved
Bytes 5 to end of trace order
Zero or more trace options
When starting a trace, trace options specify what is to be traced; these options are not used
and are ignored when stopping or obtaining a trace. To select all defined options (except for
Free-Form entries), specify complete trace; in this case addition options can be specified but
are ignored. Any combination of trace options can be specified:
X'00' Complete trace
This option causes all trace entries, except for Free-Form entries, to be generated in
the trace. This is the default if no options are specified.
X'01' Page entries
This option causes BP and EP commands to be traced. Begin-Page trace entries and
End-Object trace entries are generated. [IPDS-4-1843]


X'02' Overlay entries
This option causes BO and EP commands to be traced. Begin-Overlay trace entries
and End-Object trace entries are generated.
X'03' Presentation-object entries
This option causes the following commands to be traced:
• WBCC, WGC, WIC2, WOCC, and WTC commands received in page or overlay [IPDS-4-1844]
state
• End commands for objects that were begun with commands from the previous bullet [IPDS-4-1845]
• WT commands that begin a sequence of text-major text within a page, page [IPDS-4-1846]
segment, or overlay; an End-Object trace entry is generated when another object is
encountered or an EP command is encountered. There can be multiple sequences
of text within a page, page segment, or overlay.
Begin-Presentation-Object trace entries and End-Object trace entries are generated.
X'04' CMRs-Used entries
This option causes a CMRs-Used trace entry to be generated each time the printer
uses the CMR-usage hierarchy to select a new set of CMRs.
Bit 1 of the flags field within each CMRs-Used trace entry identifies the presence or
absence of the CMR name.
X'05' CMR Activation and Deactivation entries
This option causes CMR-related AR, home-state WOCC, and DDOR commands to be
traced. CMR-Activation and CMR-Deactivation trace entries are generated.
X'06' CMR-Invocation entries
This option causes ICMR commands, Invoke CMR (X'92') triplets, and Invoke Tertiary
Resource (X'A2') triplets to be traced. CMR-Invocation trace entries are generated.
X'07' Media-Source-Selection entries
This option causes XOH-SIMS commands and LCC commands that select a media
source to be traced. Media-Source-Selection trace entries are generated.
X'08' Exception-ID entries
This option causes an Exception-ID trace entry to be generated whenever a NACK is
reported to the host.
X'09' Free-Form entries
This option causes printer-defined trace entries to be generated for printer-specific
events. Refer to your printer documentation for information about this type of trace
entry.
Warning: To avoid privacy and security problems, user data must not be included
within a Free-Form entry unless that information can be appropriately
controlled.


X'0A' Include-Saved-Page entries
This option causes ISP commands to be traced. An Include-Saved-Page trace entry is
generated for each ISP command.
X'0B' Include-Overlay entries
This option causes IO commands and inclusion of medium overlays to be traced. An
Include-Overlay trace entry is generated for each included overlay.
X'0C' Include-Data-Object entries
This option causes IDO commands to be traced. An Include-Data-Object trace entry is
generated for each IDO command.
X'0D' Device-Appearance entries
This option causes Device Appearance (X'97') triplets to be traced. A Device-
Appearance trace entry is generated for each SPE command that contains a Device
Appearance (X'97') triplet.
X'0E' Color-Fidelity entries
This option causes Color Fidelity (X'75') triplets to be traced. A Color-Fidelity-Control
trace entry is generated for each PFC command that contains a Color Fidelity (X'75')
triplet.
X'0F' CMR-Tag-Fidelity entries
This option causes CMR Tag Fidelity (X'96') triplets to be traced. A CMR-Tag-Fidelity
trace entry is generated for each PFC command that contains a CMR Tag Fidelity
(X'96') triplet.
X'10' Begin-Print-Unit entries
This option causes Group ID (X'00') triplets to be traced. A Begin-Print-Unit trace entry
is generated for each XOH-DGB command that begins a print unit and contains a
Group ID (X'00') triplet. Print units are begun with an XOH-DGB command that
initiates a group using the keep group together as a print unit group operation.
Exception ID X'025F..02' exists if an invalid trace-option value is specified. Duplicate trace-
option values are ignored. [IPDS-4-1847]


Acknowledge Reply for the XOH Trace Command
To obtain a trace, the XOH Trace command must be sent to the printer with the Obtain Trace function selected
and with the ARQ flag set to B'1'. Since traces can contain many entries, it is recommended that the Long
Acknowledge Reply flag also be set to B'1' for printers that support long Acknowledge Replies.
Trace entries are returned in the special data area of Acknowledge Replies in sequence starting with the oldest
trace entry and with each subsequent trace entry immediately following the previous entry.
When the reply data is larger than will fit in one Acknowledge Reply a sequence of Acknowledge Replies is
used to obtain the complete trace (the ACK-continuation method). The special data area of each individual
Acknowledge Reply continues where the previous Acknowledge Reply left off. Trace data can be split between
Acknowledge Replies at any byte boundary.
Printer-Generated Trace Entries
**Table 36**. Printer-Generated Trace Entries
Entry Type Entry Name
X'0000' Begin Trace
X'0001' Begin Page
X'0002' Begin Overlay
X'0003' Begin Presentation Object
X'0004' CMRs Used
X'0005' CMR Activation
X'0006' CMR Invocation
X'0007' Media Source Selection
X'0008' Exception ID
X'0009' Free Form
X'000A' Include Saved Page
X'000B' Include Overlay
X'000C' Include Data Object
X'000D' Device Appearance
X'000E' Color Fidelity
X'000F' CMR Tag Fidelity
X'0010' Begin Print Unit
X'8000' Trace Full
X'8001' End Object
X'8002' CMR Deactivation
The trace entries are defined as follows. [IPDS-4-1848]


Begin-Trace Trace Entry
The first entry in each IPDS trace is a Begin-Trace entry.
Offset Type Name Range Meaning
0–1 UBIN Length X'002C' –
X'0521' or
X'0039' –
X'052E'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0000' Begin-Trace trace entry
4 BITS Entry flags [IPDS-4-1849]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1850]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–11
or
19–24
CHAR Device type EBCDIC Device type in EBCDIC
12–14
or
25–27
CHAR Model number EBCDIC Model number in EBCDIC
15–17
or
28–30
CHAR Manufacturer EBCDIC Name of manufacturer in EBCDIC
18–19
or
31–32
CHAR Plant EBCDIC Plant of manufacture in EBCDIC
20–31
or
33–44
CHAR Sequence
number
EBCDIC Sequence number of printer in EBCDIC
32–33
or
45–46
UNDF Tag Tag information
34–42
or
47–55
CHAR EC level EBCDIC Engineering change level in EBCDIC
43 or
56
UBIN Name length X'00' – X'FC' Length of printer name
44 to
end or
57 to
end
CHAR Printer name EBCDIC External name of printer in EBCDIC
If the host-information flag is B'1', the following information is provided by the host:
+ 0–1 UBIN Name length X'0000' –
X'01F4'
Length of host name for printer [IPDS-4-1851]


Offset Type Name Range Meaning
+ 2 to
end
CHAR Printer name Any UTF-
16BE value
Host name for printer
++ 0–1 UBIN Address
length
X'0000' –
X'01F4'
Length of attachment address
++ 2 to
end
CHAR Attachment
address
Any UTF-
16BE value
Attachment address for printer
+++
0–1
UBIN Port number
length
X'0000' –
X'000A'
Length of port number
+++ 2
to end
CHAR Port Any UTF-
16BE value
Port number
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Begin-Trace trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are
omitted. The date and time stamp fields are fully described in the Begin-Page trace
entry description ().
Bits 1–6
Reserved
Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information is
omitted. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry.
Byte 5 Reserved
Bytes 6–42
or 19–55
Unique Product Identifier
This parameter contains product identification information as defined by the XOH-OPC
Product Identifier SDF parameter ID X'0001';  for a full definition of this data.
Bytes 43 to
end or 56 to
end
Printer name
These parameters contain the external name of the printer as defined by the XOH-OPC
Product Identifier SDF parameter ID X'0003';  for a full definition of this field.
The external name can be from 1 to 252 bytes long.
Byte 43 (or 56) contains the length of the printer name; if no name is provided, the length is
X'00'.


Optional host-provided information
The following parameters contain host-provided information to further describe printer-
attachment information. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Bytes + 0
to end
Host name for printer
These parameters contain the host name for the printer (for example,
PRT025). The name is encoded as UTF-16BE characters and can be from 1
to 250 characters long.
Bytes + 0–1 contains the length of the name; if no name is provided, the
length is X'0000'.
Bytes ++ 0
to end
Attachment address for the printer
These parameters contain an attachment address for the printer; the address
is encoded as UTF-16BE characters and can be from 1 to 250 characters
long. The attachment address can be one of the following:
• A channel address (such as 4A0) [IPDS-4-1852]
• An Internet Protocol address (such as 9.99.12.33) [IPDS-4-1853]
• An Internet Protocol host name (such as PRTJDOE) [IPDS-4-1854]
Bytes ++ 0–1 contains the length of the address; if no attachment address is
provided, the length is X'0000'.
Bytes +++ 0
to end
Port number for the printer
These parameters contain a port number for the printer; the port number is
encoded as UTF-16BE characters and can be from 1 to 5 characters long.
The port number is provided when the printer is TCP-attached.
Bytes +++ 0–1 contains the length of the port number; if no port number is
provided, the length is X'0000'. [IPDS-4-1855]


Begin-Page Trace Entry
When tracing page entries, a Begin-Page trace entry is generated each time a Begin Page (BP) command is
processed. Also, an End-Object trace entry is generated at the end of each page when the End Page (EP)
command is processed.
Note: The Begin-Page trace entry shown below does not contain the optional date and time stamp fields
(bytes 6–18) that can be selected with a control flag in the XOH Trace command (bit 0, byte 4).
Immediately following this table is a second table that shows the Begin-Page trace entry complete with
the date and time stamp fields.
Offset Type Name Range Meaning
0–1 UBIN Length X'000A' –
X'01FE'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0001' Begin-Page trace entry
4 BITS Entry flags [IPDS-4-1856]
bit 0 Stamp
B'0'
Date and time stamp:
Omitted
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1857]
6–9 UNDF Page ID Any binary
value
Page ID
If the host-information flag is B'1', the following information is provided by the host:
10 to
end
CHAR Page name Any UTF-
16BE value
Host-provided page name [IPDS-4-1858]


Note: The Begin-Page trace entry shown below contains the optional date and time stamp fields (bytes 6–18)
that can be selected with a control flag in the XOH Trace command (bit 0, byte 4). Because these date
and time stamp fields are identical when present in all other types of trace entries, they are laid out and
described once here and are omitted in all of the other trace-entry descriptions.
Offset Type Name Range Meaning
0–1 UBIN Length X'0017' –
X'020B'
odd values
Length of trace entry, including this length field
2–3 CODE Entry ID X'0001' Begin-Page trace entry
4 BITS Entry flags [IPDS-4-1859]
bit 0 Stamp
B'1'
Date and time stamp:
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1860]
6–7 UBIN Year AD X'0000' –
X'FFFF'
Year AD using Gregorian calendar
8 UBIN Month X'01'–X'0C' Month of the year [IPDS-4-1861]
9 UBIN Day X'01'–X'1F' Day of the month [IPDS-4-1862]
10 UBIN Hour X'00'–X'17' Hour of the day in 24-hour format [IPDS-4-1863]
11 UBIN Minute X'00'–X'3B' Minute of the hour [IPDS-4-1864]
12 UBIN Second X'00'–X'3B' Second of the minute [IPDS-4-1865]
13–15 UBIN Micro seconds X'000000' –
X'0F423F'
Microseconds of the second
16 CODE Timezone [IPDS-4-1866]
X'00'
X'01'
X'02'
X'03'
Relationship of time to UTC:
No relationship—device time
Ahead of UTC
Behind UTC
17 UBIN UTCDiffH X'00'–X'17' Hours ahead of or behind UTC [IPDS-4-1867]
18 UBIN UTCDiffM X'00'–X'3B' Minutes ahead of or behind UTC [IPDS-4-1868]
19–22 UNDF Page ID Any binary
value
Page ID
If the host-information flag is B'1', the following information is provided by the host:
23 to
end
CHAR Page name Any UTF-
16BE value
Host-provided page name
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Begin-Page trace entry. [IPDS-4-1869]


Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted.
Bits 1–6 Reserved
Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information
is omitted. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Byte 5 Reserved
Bytes 6–18 Optional date and time stamp information
Presence or absence of the optional date and time stamp information is controlled by the Date
and Time Stamp flag in the XOH Trace command flags (bit 0, byte 4). If bit 0 = B'1', the date
and time stamp fields are present; otherwise these fields are omitted.
Bytes 6–7 Year AD
This parameter contains the year AD using the Gregorian calendar and
represents the YYYY component of a date in the format YYYYMMDD. For
example, the year 2004 is specified as X'07D4', the year 2005 as X'07D5',
and the year 2006 as X'07D6'.
Byte 8 Month
This parameter contains the month of the year and represents the MM
component of a date in the format YYYYMMDD. January is specified as X'01',
and subsequent months are numbered in ascending order.
Byte 9 Day
This parameter contains the day of the month and represents the DD
component of a date in the format YYYYMMDD. The first day of any month is
specified as X'01', and subsequent days are numbered in ascending order.
Examples of dates: the date December 31, 1999 is specified as
X'07CF0C1F' and January 1, 2000 is specified as X'07D00101'.
Byte 10 Hour
This parameter contains the hour of the day in 24-hour format and represents
the hh component of a time in the format hhmmssMM.
Byte 11 Minute
This parameter contains the minute of the hour and represents the mm
component of a time in the format hhmmssMM.
Byte 12 Second
This parameter contains the second of the minute and represents the ss
component of a time in the format hhmmssMM.
Byte 13–15 Microseconds
This parameter contains millionths of the second and represents the MM
component of a time in the format hhmmssMM. [IPDS-4-1870]


Examples of times: the time 4:35:21:15 PM is specified as X'10231500000F'
and the time 2:12:59:20 AM is specified as X'020C3B000014'.
Byte 16 Timezone
This parameter defines the relation of the specified time with respect to
Coordinated Universal Time (UTC). The following relationships are defined:
X'00' No relationship—device time
This value is used when the printer does not have a real-time clock
and must provide a device time. In this case, when a trace is first
started, the first trace entry contains a time of X'00000000' (in the
hour through hundredths fields) and a timer is started to provide
relative times for subsequent trace entries. The date fields (Year AD
through Day) are not used and contain X'00000000'. The UTCDiffH
and UTCDiffM fields are not used (and contain X'00').
X'01' Coordinated Universal Time (UTC)
This value is used when the time is specified in Coordinated
Universal Time (UTC). With this value, the UTCDiffH and UTCDiffM
parameters are not used and are set to X'00'. When this time is
displayed or printed, the equivalence with UTC time is normally
indicated with a Z suffix, that is, hhmmssZ.
X'02' Ahead of UTC
This value, along with the UTCDiffH and UTCDiffM parameters, is
used to accommodate differences between a specified local time and
UTC because of time zones and daylight savings programs. The
number of hours ahead of UTC is specified by the UTCDiffH
parameter; and the number of minutes ahead of UTC is specified by
the UTCDiffM parameter. When this time is displayed or printed, the
relationship with UTC time is normally indicated with a + character,
followed by the actual time difference in hours and minutes, that is,
hhmmss+hhmm.
X'03' Behind UTC
This value, along with the UTCDiffH and UTCDiffM parameters, is
used to accommodate differences between a specified local time and
UTC because of time zones and daylight savings programs. For
example, Mountain Time in the US is seven hours behind UTC when
daylight savings is inactive, and six hours behind UTC when daylight
savings is active. The number of hours behind UTC is specified by
the UTCDiffH parameter; and the number of minutes behind UTC is
specified by the UTCDiffM parameter. When this time is displayed or
printed, the relationship with UTC time is normally indicated with a -
character, followed by the actual time difference in hours and
minutes, that is, hhmmss-hhmm.
Byte 17 Hours ahead of or behind UTC
This parameter indicates how many hours the specified time is ahead of UTC
or behind UTC. If the Timezone parameter is X'00' or X'01', this value is not
used and contains X'00'.
Byte 18 Minutes ahead of or behind UTC
This parameter indicates how many minutes the specified time is ahead of
UTC or behind UTC. If the Timezone parameter is X'00' or X'01', this value is
not used and contains X'00'. [IPDS-4-1871]


Bytes 19–22 Page ID
This parameter contains the host-specified page ID from a Begin Page (BP) command.
Bytes 23 to
end
Host-provided page name
This parameter contains a host-provided page name; the name can contain up to 250 UTF-
16BE characters. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry. The host-provided page name can be obtained from a MO:DCA
Begin Page (BPG) structured field. [IPDS-4-1872]


Begin-Overlay Trace Entry
When tracing overlay entries, a Begin-Overlay trace entry is generated each time a Begin Overlay (BO)
command is processed. Processing occurs when the overlay is RIPped for use with an IO command or when a
medium overlay is included on a sheet; overlays are not normally processed when the overlay is downloaded
to the printer. Furthermore, when overlays are RIPped and cached, overlay processing occurs when the object
is RIPped, but does not reoccur each time the cached overlay is used.
Also, an End-Object trace entry is generated at the end of each overlay when the overlay's End Page (EP)
command is processed.
Offset Type Name Range Meaning
0–1 UBIN Length X'0008' –
X'01FC'
or X'0015' –
X'0209'
odd values
Length of trace entry, including this length field
2–3 CODE Entry ID X'0002' Begin-Overlay trace entry
4 BITS Entry flags [IPDS-4-1873]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1874]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0001' –
X'7EFF'
Overlay Host-Assigned ID
If the host-information flag is B'1', the following information is provided by the host:
8 to
end or
21 to
end
CHAR Overlay name Any UTF-
16BE value
Host-provided overlay name
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Begin-Overlay trace entry. [IPDS-4-1875]


Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bits 1–6 Reserved
Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information
is omitted. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Byte 5 Reserved
Bytes 6–7 or
19–20
HAID
This parameter contains the overlay's Host-Assigned ID from the Begin Overlay command.
Bytes 8 to end
or 21 to end
Host-provided overlay name
This parameter contains a host-provided overlay name; the name can contain up to 250 UTF-
16BE characters. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry. The host-provided overlay name can be obtained from a MO:DCA
Map Page Overlay (MPO) or Map Medium Overlay (MMO) structured field. [IPDS-4-1876]


Begin-Presentation-Object Trace Entry
When tracing presentation-object entries, a Begin-Presentation-Object trace entry (and later an End-Object
trace entry) is generated each time one of the following presentation object commands is processed:
• WBCC, WGC, WIC2, page-state WOCC, overlay-state WOCC, and WTC commands. [IPDS-4-1877]
• End commands for objects that were begun with commands from the previous bullet. [IPDS-4-1878]
• WT commands that begin a sequence of text-major text within a page, page segment, or overlay; an End- [IPDS-4-1879]
Object trace entry is generated when another object is encountered or an EP command is encountered.
There can be multiple sequences of text within a page, page segment, or overlay.
Processing occurs when the object is RIPped for presentation; resource objects are not normally processed
when the object is downloaded to the printer.
When the Rasterize Presentation Object (RPO) command is used, the object is traced when the object is
RIPped, but does not reoccur each time the rasterized object is used.
Offset Type Name Range Meaning
0–1 UBIN Length X'0009' –
X'01FD'
odd values
or X'0016' –
X'020A'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0003' Begin-Presentation-Object trace entry
4 BITS Entry flags [IPDS-4-1880]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1881]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID
X'0000'
X'0001' –
X'7EFF'
Host-Assigned ID of presentation object:
No HAID provided (object in page, page segment, or overlay)
Object HAID (resource object) [IPDS-4-1882]


Offset Type Name Range Meaning
8 or 21 CODE Object type [IPDS-4-1883]
X'01'
X'02'
X'03'
X'04'
X'05'
X'06'
X'07'
X'08'
X'09'
X'0A'
X'0B'
X'0C'
X'0D'
X'0E'
X'0F'
X'10'
X'11'
X'12'
X'13'
X'14'
X'15'
Type of presentation object:
PTOCA
GOCA
IOCA
BCOCA
EPS without transparency
EPS with transparency
PDF single page without transparency
PDF single page with transparency
GIF
AFPC JPEG Subset
PCL
TIFF with transparency
TIFF without transparency
JP2
PDF multiple-page file without transparency
PDF multiple-page file with transparency
TIFF multiple-image file with transparency
TIFF multiple-image file without transparency
AFPC PNG Subset
AFPC SVG Subset
AFPC TIFF Subset
If the host-information flag is B'1', the following information is provided by the host:
9 to
end or
22 to
end
CHAR Presentation
object name
Any UTF-
16BE value
Host-provided object name
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Begin-Presentation-Object trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bits 1–6 Reserved
Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information
is omitted. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Byte 5 Reserved
Bytes 6–7 or
19–20
HAID
For presentation objects used as resources, this parameter contains the presentation object's
Host-Assigned ID from the IPDS command that began the object. When the presentation
object is inline in a page, page segment, or overlay, the HAID field contains X'0000'. [IPDS-4-1884]


Bytes 8 or 21 Object type
This field identifies the type of presentation object, as follows:
X'01' PTOCA
Each time a new sequence of text-major text or a new text object is begun within a
page, page segment, or overlay, a Begin-Presentation-Object trace entry is generated
and an End-Object trace entry is generated when another object is encountered or an
EP command is encountered. There can be multiple sequences of text within a page,
page segment, or overlay.
The following commands cause the end of a sequence of text-major text and trigger
an End-Object trace entry to be generated: EP , IDO, IO, ISP , WBCC, WGC, WIC,
WIC2, WOCC, WTC.
Sequences of text cannot be used as resources; therefore, the HAID field is X'0000'
for all sequences of text.
X'02' GOCA
Each time a WGC command is encountered within a page, page segment, or overlay,
a Begin-Presentation-Object trace entry is generated and an End-Object trace entry is
generated when the End command for that graphics object is encountered. There can
be multiple graphics objects within a page, page segment, or overlay.
Graphics objects cannot be used as resources; therefore, the HAID field is X'0000' for
all GOCA objects.
X'03' IOCA
Each time a WIC2 command is encountered within a page, page segment, or overlay,
a Begin-Presentation-Object trace entry is generated and an End-Object trace entry is
generated when the End command for that image object is encountered. There can
be multiple image objects within a page, page segment, or overlay.
Image objects can be used as resources; the HAID identifies the specific IOCA-image
resource.
X'04' BCOCA
Each time a WBCC command is encountered within a page, page segment, or
overlay, a Begin-Presentation-Object trace entry is generated and an End-Object
trace entry is generated when the End command for that bar code object is
encountered. There can be multiple bar code objects within a page, page segment, or
overlay. There can also be multiple bar code symbols within a single bar code object,
but these individual symbols are not traced.
Bar code objects cannot be used as resources; therefore, the HAID field is X'0000' for
all BCOCA objects.
X'05'–X'15'
Object container
Each time a WOCC command for a presentation data object is encountered within a
page, page segment, or overlay, a Begin-Presentation-Object trace entry is generated
and an End-Object trace entry is generated when the End command for that data
object is encountered. There can be multiple presentation data objects within a page,
page segment, or overlay.
Object containers can be used as resources; the HAID identifies the specific data
object resource.


Bytes 9 to end
or 22 to end
Host-provided object name
This parameter contains a host-provided object name; the name can contain up to 250 UTF-
16BE characters. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry. The host-provided object name can be obtained from one of the
following MO:DCA structured fields: [IPDS-4-1885]


CMRs-Used Trace Entry
A CMRs-Used trace entry is generated each time the printer uses the CMR-usage hierarchy to select a new
set of CMRs. The hierarchy can be used at the beginning of each presentation object and can also be used
whenever the color space changes within an object.
Offset Type Name Range Meaning
0–1 UBIN Length X'001D',
X'002A',
X'01D3',
X'01E0',
X'0265',
X'0272',
X'02F7',
X'0304',
X'0389',
X'0396',
X'041B',
X'0428'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0004' CMRs-Used trace entry
4 BITS Entry flags [IPDS-4-1886]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bit 1 CMR names
B'0'
B'1'
CMR names:
Omitted
Present
bits 2–7 B'000000' Reserved
5 X'00' Reserved [IPDS-4-1887]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Rendering [IPDS-4-1888]
intent X'00'
X'01'
X'02'
X'03'
X'FD'
X'FE'
X'FF'
Rendering intent used for color conversion CMRs:
Perceptual
Media-relative colorimetric
Saturation
ICC-absolute colorimetric
Multiple rendering intents used
Unknown
Not specified
7 or 20 CODE RI hierarchy [IPDS-4-1889]
X'00'
X'01'
X'02'
X'03'
X'04'
X'05'
X'06'
X'FF'
Hierarchy level of selected rendering intent:
Object
Page
Page or preprinted form overlay
Medium or preprinted form overlay
Home state
Default
Internal rendering intent
Not specified


Offset Type Name Range Meaning
CMR entries (for CC-audit, CC-instruction, Link, HT-audit, HT-instruction, TC-audit, TC-instruction) in the
following format:
+ 0 CODE CMR
hierarchy X'00'
X'01'
X'02'
X'03'
X'04'
X'05'
X'FF'
Hierarchy level of the selected CMR:
Object
Page
Page overlay
Medium overlay
Home state
Default
Not specified
+ 1–2 CODE CMR ID
X'0000'
X'0001' –
X'7EFF'
X'DDDD'
X'EEEE'
X'FFFF'
Identification of the selected CMR:
Embedded ICC profile (used only for audit-CC CMRs)
HAID of the selected CMR
Default CMR
Printer-generated object (used only for Link CMRs)
Not specified
+ 3–
148
CHAR CMR name Any UTF-
16BE value
CMR name (only present when the CMR-names flag is B'1' and
the CMR ID is not X'0000', X'EEEE', or X'FFFF')
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a CMRs-Used trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bit 1 CMR name
If bit 1 = B'1', the CMR name is present for each selected CMR; otherwise all
CMR names are omitted.
Bits 2–7 Reserved
Byte 5 Reserved
Byte 6 or 19 Rendering intent
This parameter contains the rendering intent selected for use with the color-conversion CMRs.
If an ICC DeviceLink CMR was used, no rendering intent was selected and this field contains
X'FF'.
For presentation object containers, if rendering intent information internal to the object was
selected for use, the value returned in this byte can vary. If it is known that one single internal
rendering intent was used, and that rendering intent is known, the rendering intent used is
returned in this parameter. If it is known that multiple internal rendering intents were used,
X'FD' is returned. If it is unknown exactly which internal rendering intent or intents were used,
X'FE' is returned.


Byte 7 or 20 Rendering intent hierarchy
This parameter identifies the hierarchy level at which the rendering intent was selected. If an
ICC DeviceLink CMR was used, no rendering intent was selected and this field contains X'FF'.
If the rendering intent information internal to a presentation object container was selected,
X'06' is returned in this byte.
Byte + 0 to end CMR entries
The following fields describe a CMR entry; there are seven CMR entries specified in the trace
entry, one for each of the following CMR types (in the following order):
1. Audit color-conversion CMR or indexed CMR (not used when an ICC DeviceLink CMR is [IPDS-4-1890]
selected)
2. Instruction color-conversion or indexed CMR (not used when an ICC DeviceLink CMR is [IPDS-4-1891]
selected)
3. Link color-conversion (subset LK or DL) CMR (not used with indexed CMRs) [IPDS-4-1892]
4. Audit halftone CMR (not used with indexed CMRs) [IPDS-4-1893]
5. Instruction halftone CMR [IPDS-4-1894]
6. Audit tone-transfer-curve CMR (not used with indexed CMRs) [IPDS-4-1895]
7. Instruction tone-transfer-curve CMR [IPDS-4-1896]
Byte + 0 CMR hierarchy
This parameter identifies the hierarchy level at which the CMR was selected.
The default value (X'05') is used for link-color-conversion (subset “LK”)
CMRs.
Bytes + 1–2 CMR identification
This field identifies the specific CMR selected. The identification values are
defined as follows:
X'0000' Embedded ICC profile (used only for audit CC CMRs)
This special value is used to indicate that an object-level
audit color-conversion CMR was not specified, but an ICC
profile was embedded in the presentation object. In this case,
there is no CMR name and the name field is filled with @
characters.
X'0001'–
X'7EFF'
HAID of the selected CMR
When a host-invoked CMR is selected, the HAID of that
resource is specified in this field. The CMR HAID was
specified in an Invoke CMR command,
Invoke CMR (X'92')
triplet, or Invoke Tertiary Resource (X'A2') triplet.
X'DDDD' Default CMR
This special value is used to indicate that no appropriate
CMR was invoked by the host for this entry and the printer
used the appropriate default (either a default CMR or
equivalent processing).
A suggested naming scheme for default CMR names is to
specify default@ in the CMRAlias field and appropriate
values in all other CMR name fields. If the printer does not
use a physical CMR for a default, this naming scheme should
also be used. For example, the default audit halftone CMR
for an IBM 4100 model HS3 printer would be named:
default@ HT 001.000 IBM@@ 4100@@ HS3 @@@ @@@ @@
@@@ @@@@@ @@@@@@ @@@@ @@@@ @@@@ @@@@@@@@ [IPDS-4-1897]


(Spaces, that do not actually appear in the name, have
been added between fields to aid readability.)
X'EEEE' Printer-generated object (used only for the link CMR entry)
This special value is used to indicate that the printer used a
printer-generated LUT in place of a link color-conversion
(subset “LK”) CMR.
X'FFFF' Not specified
This special value is used in the following cases:
• For the link entry, the HT-audit entry, and the TC-audit entry [IPDS-4-1898]
when an indexed CMR has been selected; in this case the
indexed CMR is identified in the CC-Instruction entry.
• For the CC-audit entry, and the CC-instruction entry when [IPDS-4-1899]
an ICC DeviceLink CMR has been selected; in this case
the ICC DeviceLink CMR is identified in the link entry.
• When the printer ignores a CMR type, such as for audit [IPDS-4-1900]
halftone CMRs and audit indexed CMRs.
Byte + 3 to 148 CMR name (optional)
This field, if present, contains the 146-byte CMR name from within the CMR
header. The CMR Names flag in byte 4 indicates whether the name is present
or omitted.


CMR-Activation Trace Entry
When tracing activation and deactivation entries, a CMR-Activation trace entry is generated each time a CMR
is activated via a home state WOCC command or an AR command. Also, a CMR-Deactivation trace entry is
generated each time a CMR is deactivated via a DDOR or XOH-ERPD command.
Offset Type Name Range Meaning
0–1 UBIN Length X'0009',
X'009B',
X'0016',
X'00A8'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0005' CMR-Activation trace entry
4 BITS Entry flags [IPDS-4-1901]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bit 1 CMR name
B'0'
B'1'
CMR name:
Omitted
Present
bits 2–7 B'000000' Reserved
5 X'00' Reserved [IPDS-4-1902]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0001' –
X'7EFF'
CMR Host-Assigned ID
8 or 21 CODE Mode [IPDS-4-1903]
X'01'
X'02'
X'03'
Processing mode:
Process as an audit CMR
Process as an instruction CMR
Process as a link CMR
9–154
or
22–167
CHAR CMR name Any UTF-
16BE value
CMR name (only present when the CMR Name flag is B'1')
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a CMR-Activation trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description (). [IPDS-4-1904]


Bit 1 CMR name
If bit 1 = B'1', the CMR name is present; otherwise the CMR name is omitted.
Bits 2–7 Reserved
Byte 5 Reserved
Bytes 6–7 or
19–20
HAID
This parameter contains the CMR's Host-Assigned ID from the AR or WOCC command.
Byte 8 or 21 Processing mode
This field contains the processing mode specified in the Color Management Resource
Descriptor (X'91') triplet that was specified on the AR or WOCC command.
Bytes 9–154
or 22–167
CMR name (optional)
This field, if present, contains the 146-byte CMR name from within the CMR header. The CMR
Name flag in byte 4 indicates whether the name is present or omitted. [IPDS-4-1905]


CMR-Invocation Trace Entry
When tracing invocation entries, a CMR-Invocation trace entry is generated each time a CMR is invoked via an
ICMR command, Invoke CMR (X'92') triplet, or Invoke Tertiary Resource (X'A2') triplet. There can be multiple
CMR-Invocation trace entries for each invoking command.
Offset Type Name Range Meaning
0–1 UBIN Length X'0009',
X'009B',
X'0016',
X'00A8'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0006' CMR-Invocation trace entry
4 BITS Entry flags [IPDS-4-1906]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bit 1 CMR name
B'0'
B'1'
CMR name:
Omitted
Present
bits 2–7 B'000000' Reserved
5 X'00' Reserved [IPDS-4-1907]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0001' –
X'7EFF'
CMR Host-Assigned ID
8 or 21 CODE CMR [IPDS-4-1908]
hierarchy X'00'
X'01'
X'04'
Hierarchy level of the invoked CMR:
Object
Page or overlay
Home state
9–154
or
22–167
CHAR CMR name Any UTF-
16BE value
CMR name (only present when the CMR Name flag is B'1')
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a CMR-Invocation trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bit 1 CMR name
If bit 1 = B'1', the CMR name is present; otherwise the CMR name is omitted.
Bits 2–7 Reserved
Byte 5 Reserved


Bytes 6–7 or
19–20
HAID
This parameter contains the CMR's Host-Assigned ID from the ICMR command, Invoke CMR
(X'92') triplet, or Invoke Tertiary Resource (X'A2') triplet.
Byte 8 or 21 CMR hierarchy level
This parameter identifies the hierarchy level at which the CMR was invoked and is determined
by the command used for the invocation, as follows:
X'00' Object level; invoked via WBCC, WGC, WIC2, WOCC, or WTC command
X'01' Page, page overlay, or medium overlay level; invoked via LPD command
X'04' Home state level; invoked via ICMR command
Bytes 9–154
or 22–167
CMR name (optional)
This field, if present, contains the 146-byte CMR name from within the CMR header. The CMR
Name flag in byte 4 indicates whether the name is present or omitted. [IPDS-4-1909]


Media-Source-Selection Trace Entry
When tracing media-source-selection entries, a Media-Source-Selection trace entry is generated each time a
new media source is selected via an XOH-SIMS or LCC command.
Offset Type Name Range Meaning
0–1 UBIN Length X'001D' or
X'002A'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0007' Media-Source-Selection trace entry
4 BITS Entry flags [IPDS-4-1910]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1911]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Source ID X'00'–X'FF' Input media source ID [IPDS-4-1912]
7–28
or
20–41
CHAR Character-
istics
UTF-16BE
values
Media characteristics
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Media-Source-Selection trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted. The date and time stamp fields are fully
described in the Begin-Page trace entry description ().
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
Byte 5 Reserved
Bytes 6 or 19 Source ID
This parameter contains the input media source ID from an XOH-SIMS or LCC command.
Bytes 7–28
or 20–41
Media characteristics
This field contains the media characteristics for the selected media. This data is identical to
the media characteristics fields defined in the CMR header and consists of the following media
characteristics:
• MediaBrightness [IPDS-4-1913]
• MediaColor [IPDS-4-1914]
• MediaFinish [IPDS-4-1915]
• MediaWeight [IPDS-4-1916]
When a media characteristic is not known, the field is filled with @ characters. [IPDS-4-1917]


Exception-ID Trace Entry
When tracing exception ID entries, an Exception-ID trace entry is generated each time a NACK is returned to
the host.
Offset Type Name Range Meaning
0–1 UBIN Length X'001E' or
X'002B'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0008' Exception-ID trace entry
4 BITS Entry flags [IPDS-4-1918]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1919]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–29
or
19–42
CODE Sense data Any value Sense data
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as an Exception-ID trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted. The date and time stamp fields are fully
described in the Begin-Page trace entry description ().
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
Byte 5 Reserved
Bytes 6–29
or 19–42
Sense data
This parameter contains the 24-byte sense data from the NACK. [IPDS-4-1920]


Free-Form Trace Entry
When tracing free-form entries, a Free-Form trace entry is generated for printer-specific events.
Offset Type Name Range Meaning
0–1 UBIN Length X'0007' –
X'FFFF' or
X'0014' –
X'FFFF'
Length of trace entry, including this length field
2–3 CODE Entry ID X'0009' Free-Form trace entry
4 BITS Entry flags [IPDS-4-1921]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bit 1 B'0' Reserved
bit 2 User data B'0'
B'1'
User data is obscured
User data can be returned in Free-Form trace entries
bits 3–7 B'00000' Reserved
5 X'00' Reserved [IPDS-4-1922]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 to
end or
19 to
end
UNDF Data Any value Printer-defined trace data
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Free-Form trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted. The date and time stamp fields are fully
described in the Begin-Page trace entry description ().
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted.
Bit 2 User data
When this flag is set to B'1', Free-Form trace entries can contain user text
data.
When this flag is B'0', all user text data in each Free-Form trace entry must be
obscured by replacing each text character with a full-stop character (Unicode
scalar value U+002E, also called “period”). The encoding used to represent
the full-stop character (EBCDIC, ASCII, Unicode, etc.) is the same as the
encoding for the user data being replaced.
Bits3–7 Reserved
Byte 5 Reserved


Bytes 6 to end
or 19 to end
Data
This parameter contains printer-defined trace data. Refer to your printer documentation for
information about this type of trace entry.
Warning: To avoid privacy and security problems, user data must not be included within a
Free-Form trace entry unless that information can be appropriately controlled. [IPDS-4-1923]


Include-Saved-Page Trace Entry
When tracing saved-page entries, an Include-Saved-Page trace entry is generated each time an Include
Saved Page (ISP) command is processed.
Offset Type Name Range Meaning
0–1 UBIN Length X'000A' or
X'0017'
Length of trace entry, including this length field
2–3 CODE Entry ID X'000A' Include-Saved-Page trace entry
4 BITS Entry flags [IPDS-4-1924]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1925]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–9 or
19–22
UBIN Page
sequence
number
X'00000001' –
X'FFFFFFFF'
Page sequence number
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as an Include-Saved-Page trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Bytes 6–9
or 19–22
Page sequence number
This parameter contains the host-specified page sequence number from an Include Saved
Page (ISP) command.


Include-Overlay Trace Entry
When tracing included overlays, an Include-Overlay trace entry is generated each time an Include Overlay (IO)
command is processed and each time a medium or preprinted form overlay is processed from an LCC
command. Note that for medium and preprinted form overlays, the trace entry is generated when the overlay is
presented, not when the LCC command is received.
Offset Type Name Range Meaning
0–1 UBIN Length X'0008' –
X'01FC'
or X'0015' –
X'0209'
odd values
Length of trace entry, including this length field
2–3 CODE Entry ID X'000B' Include-Overlay trace entry
4 BITS Entry flags [IPDS-4-1926]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1927]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0001' –
X'7EFF'
Overlay Host-Assigned ID
If the host-information flag is B'1', the following information is provided by the host:
8 to
end or
21 to
end
CHAR Overlay name Any UTF-
16BE value
Host-provided overlay name
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as an Include-Overlay trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bits 1–6 Reserved


Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information
is omitted. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Byte 5 Reserved
Bytes 6–7 or
19–20
HAID
This parameter contains the overlay's Host-Assigned ID from the Include Overlay command or
LCC command. When the X'E1' keyword in the LCC command is used to specify a medium
overlay ID or when the X'D2' keyword in the LCC command is used to specify a preprinted
form overlay ID, the HAID is the overlay ID prepended with X'00'.
Bytes 8 to end
or 21 to end
Host-provided overlay name
This parameter contains a host-provided overlay name; the name can contain up to 250 UTF-
16BE characters. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry. The host-provided overlay name can be obtained from a MO:DCA
Map Page Overlay (MPO) or Map Medium Overlay (MMO) structured field. [IPDS-4-1928]


Include-Data-Object Trace Entry
When tracing included data objects, an Include-Data-Object trace entry is generated each time an Include
Data Object command is processed.
Offset Type Name Range Meaning
0–1 UBIN Length X'0009' –
X'01FD'
odd values
or X'0016' –
X'020A'
Length of trace entry, including this length field
2–3 CODE Entry ID X'000C' Include-Data-Object trace entry
4 BITS Entry flags [IPDS-4-1929]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–6 B'000000' Reserved
bit 7 Host
information B'0'
B'1'
Host-provided information:
Omitted
Present
5 X'00' Reserved [IPDS-4-1930]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0001' –
X'7EFF'
Host-Assigned ID of included object
8 or 21 CODE Object type [IPDS-4-1931]
X'03'
X'05'
X'06'
X'07'
X'08'
X'09'
X'0A'
X'0B'
X'0C'
X'0D'
X'0E'
X'0F'
X'10'
X'11'
X'12'
X'13'
X'14'
X'15'
Type of presentation object:
IOCA
EPS without transparency
EPS with transparency
PDF single page without transparency
PDF single page with transparency
GIF
AFPC JPEG Subset
PCL
TIFF with transparency
TIFF without transparency
JP2
PDF multiple-page file without transparency
PDF multiple-page file with transparency
TIFF multiple-image file with transparency
TIFF multiple-image file without transparency
AFPC PNG Subset
AFPC SVG Subset
AFPC TIFF Subset
If the host-information flag is B'1', the following information is provided by the host:
9 to
end or
22 to
end
CHAR Presentation
object name
Any UTF-
16BE value
Host-provided object name
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself. [IPDS-4-1932]


Bytes 2–3 Trace entry ID
This field identifies this as an Include-Data-Object trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bits 1–6 Reserved
Bit 7 Host-provided information
If bit 7 = B'1', host-provided information is present; otherwise this information
is omitted. Printers do not return this information; it is added to the trace entry
after the host has obtained the entry.
Byte 5 Reserved
Bytes 6–7 or
19–20
HAID
This parameter contains the presentation object's Host-Assigned ID from the Include-Data-
Object command.
Bytes 8 or 21 Object type
This field identifies the type of presentation object, as follows:
X'03' IOCA
X'05' EPS without transparency
X'06' EPS with transparency
X'07' PDF single page without transparency
X'08' PDF single page with transparency
X'09' GIF
X'0A' AFPC JPEG Subset
X'0B' PCL
X'0C' TIFF with transparency
X'0D' TIFF without transparency
X'0E' JP2
X'0F' PDF multiple-page file without transparency
X'10' PDF multiple-page file with transparency
X'11' TIFF multiple-mage file with transparency
X'12' TIFF multiple-image file without transparency
X'13' AFPC PNG Subset
X'14' AFPC SVG Subset
X'15' AFPC TIFF Subset
Bytes 9 to end
or 22 to end
Host-provided object name
This parameter contains a host-provided object name; the name can contain up to 250 UTF-
16BE characters. Printers do not return this information; it is added to the trace entry after the
host has obtained the entry. The host-provided overlay name can be obtained from a MO:DCA
Begin Object Container (BOC) or Map Data Resource (MDR) structured field. [IPDS-4-1933]


Device-Appearance Trace Entry
When tracing device appearance, a Device-Appearance trace entry is generated each time a Set-
Presentation-Environment command is processed that contains a Device Appearance (X'97') triplet.
Offset Type Name Range Meaning
0–1 UBIN Length X'0008' or
X'0015'
Length of trace entry, including this length field
2–3 CODE Entry ID X'000D' Device-Appearance trace entry
4 BITS Entry flags [IPDS-4-1934]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1935]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE Appearance
X'0000'
X'0001'
Device appearance to assume:
Assume device-default appearance
Assume device-default monochrome appearance
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Device-Appearance trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Bytes 6–7 or
19–20
Appearance
This parameter contains the device appearance specified in the Device Appearance (X'97')
triplet being traced. [IPDS-4-1936]


Color-Fidelity Trace Entry
When tracing color fidelity, a Color-Fidelity trace entry is generated each time a Presentation Fidelity Control
command is processed that contains a Color Fidelity (X'75') triplet.
Offset Type Name Range Meaning
0–1 UBIN Length X'0009' or
X'0016'
Length of trace entry, including this length field
2–3 CODE Entry ID X'000E' Color-Fidelity trace entry
4 BITS Entry flags [IPDS-4-1937]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1938]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Continue [IPDS-4-1939]
X'01'
X'02'
Color exception continuation rule:
Stop at point of first color exception
Do not stop at color exception
7 or 20 CODE Report [IPDS-4-1940]
X'01'
X'02'
Exception reporting rule:
Report color exception
Do not report color exception
8 or 21 CODE Substitute [IPDS-4-1941]
X'01'
Substitution rule:
Any color substitution is permitted
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Color-Fidelity trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Byte 6 or 19 Continue
This parameter contains the continuation rule specified in the Color Fidelity (X'75') triplet being
traced.
Byte 7 or 20 Report
This parameter contains the reporting rule specified in the Color Fidelity (X'75') triplet being
traced.
Byte 8 or 21 Substitute
This parameter contains the substitution rule specified in the Color Fidelity (X'75') triplet being
traced.


CMR-Tag-Fidelity Trace Entry
When tracing CMR Tag fidelity, a CMR-Tag-Fidelity trace entry is generated each time a Presentation Fidelity
Control command is processed that contains a CMR Tag Fidelity (X'96') triplet.
Offset Type Name Range Meaning
0–1 UBIN Length X'0008' or
X'0015'
Length of trace entry, including this length field
2–3 CODE Entry ID X'000F' CMR-Tag-Fidelity trace entry
4 BITS Entry flags [IPDS-4-1942]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1943]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Continue [IPDS-4-1944]
X'01'
X'02'
Exception continuation rule:
Stop on exception ID X'025D..04'
Continue processing CMR data
7 or 20 CODE Report [IPDS-4-1945]
X'01'
X'02'
Exception reporting rule:
Report X'025D..04' exceptions
Do not report X'025D..04' exceptions
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a CMR-Tag-Fidelity trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Byte 6 or 19 Continue
This parameter contains the continuation rule specified in the CMR Tag Fidelity (X'96') triplet
being traced.
Byte 7 or 20 Report
This parameter contains the reporting rule specified in the CMR Tag Fidelity (X'96') triplet
being traced.


Begin-Print-Unit Trace Entry
When tracing print units, a Begin-Print-Unit trace entry is generated for each XOH-DGB command that begins
a print unit and contains a Group ID (X'00') triplet. Print units are begun with an XOH-DGB command that
initiates a group using the keep group together as a print unit group operation.
Offset Type Name Range Meaning
0–1 UBIN Length See byte
description
Length of trace entry, including this length field
2–3 CODE Entry ID X'0010' Begin-Print-Unit trace entry
4 BITS Entry flags [IPDS-4-1946]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1947]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Format [IPDS-4-1948]
X'01'
X'02'
X'03'
X'06'
X'13'
Group ID format:
MVS and VSE print-data format
VM print-data format
OS/400 print-data format
AIX and Windows print-data format
Extended OS/400 print-data format
7 to
end or
20 to
end
CHAR Group ID Group ID information
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself. Valid lengths
without the date and time stamp information include: X'0020', X'0024', X'004D', X'004F', and
X'0102'. Valid lengths with the date and time stamp information include: X'002D', X'0031',
X'005A', X'005C', and X'010F'.
Bytes 2–3 Trace entry ID
This field identifies this as a Begin-Print-Unit trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Byte 6 or 19 Group ID format
This parameter identifies the format of the Group ID information that follows. [IPDS-4-1949]


Bytes 7 to end
or 20 to end
Group ID
This parameter contains group ID information as defined in the Group ID (X'00') triplet being
traced. The data is ASCII for the AIX and Windows format and is EBCDIC for all other formats;
 for a description of each of the formats. [IPDS-4-1950]


Trace-Full Trace Entry
When the maximum amount of space available for tracing is reached, exception ID X'0113..00' exists and one
final Trace Full entry is added at the end of the trace.
Offset Type Name Range Meaning
0–1 UBIN Length X'0006' or
X'0013'
Length of trace entry, including this length field
2–3 CODE Entry ID X'8000' Trace-Full trace entry
4 BITS Entry flags [IPDS-4-1951]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1952]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a Trace-Full trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved


End-Object Trace Entry
When tracing pages, overlays, or presentation objects, an End-Object trace entry is generated at the end of
each object when the ending command for that object is processed:
• End Page (EP) command for pages [IPDS-4-1953]
• End Page (EP) command for overlays [IPDS-4-1954]
• End command for presentation objects [IPDS-4-1955]
Offset Type Name Range Meaning
0–1 UBIN Length X'0007' or
X'0014'
Length of trace entry, including this length field
2–3 CODE Entry ID X'8001' End-Object trace entry
4 BITS Entry flags [IPDS-4-1956]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bits 1–7 B'0000000' Reserved
5 X'00' Reserved [IPDS-4-1957]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6 or 19 CODE Object type [IPDS-4-1958]
X'01'
X'02'
X'03'
X'04'
X'05'
X'06'
X'07'
X'08'
X'09'
X'0A'
X'0B'
X'0C'
X'0D'
X'0E'
X'0F'
X'10'
X'11'
X'12'
X'13'
X'14'
X'15'
X'F0'
X'F1'
Type of presentation object:
PTOCA
GOCA
IOCA
BCOCA
EPS without transparency
EPS with transparency
PDF single page without transparency
PDF single page with transparency
GIF
AFPC JPEG Subset
PCL
TIFF with transparency
TIFF without transparency
JP2
PDF multiple-page file without transparency
PDF multiple-page file with transparency
TIFF multiple-image file with transparency
TIFF multiple-image file without transparency
AFPC PNG Subset
AFPC SVG Subset
AFPC TIFF Subset
Page
Overlay
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as an End-Object trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted. [IPDS-4-1959]


If bit 0 = B'1', the date and time stamp fields are present; otherwise these fields are omitted.
The date and time stamp fields are fully described in the Begin-Page trace entry description
().
Byte 5 Reserved
Bytes 6 or 19 Object type
This field identifies the type of presentation object, as follows:
X'01' PTOCA
X'02' GOCA
X'03' IOCA
X'04' BCOCA
X'05' EPS without transparency
X'06' EPS with transparency
X'07' PDF singe page without transparency
X'08' PDF single page with transparency
X'09' GIF
X'0A' AFPC JPEG Subset
X'0B' PCL
X'0C' TIFF with transparency
X'0D' TIFF without transparency
X'0E' JP2
X'0F' PDF multiple-page file without transparency
X'10' PDF multiple-page file with transparency
X'11' TIFF multiple-image file with transparency
X'12' TIFF multiple-image file without transparency
X'13' AFPC PNG Subset
X'14' AFPC SVG Subset
X'15' AFPC TIFF Subset
X'F0' Page
X'F1' Overlay


CMR-Deactivation Trace Entry
When tracing activation/deactivation entries, a CMR-Deactivation trace entry is generated each time a CMR is
deactivated via a DDOR or XOH-ERPD command. Also, a CMR-Activation trace entry is generated each time
a CMR is activated via a home state WOCC command or an AR command.
Offset Type Name Range Meaning
0–1 UBIN Length X'0008',
X'009A',
X'0015',
X'00A7'
Length of trace entry, including this length field
2–3 CODE Entry ID X'8002' CMR-Deactivation trace entry
4 BITS Entry flags [IPDS-4-1960]
bit 0 Stamp
B'0'
B'1'
Date and time stamp:
Omitted
Present
bit 1 CMR name
B'0'
B'1'
CMR name:
Omitted
Present
bits 2–7 B'000000' Reserved
5 X'00' Reserved [IPDS-4-1961]
Optional date and time stamp; if the stamp flag is B'1', this box represents 13 bytes of date and time information
6–7 or
19–20
CODE HAID X'0000'
X'0001' –
X'7EFF'
All CMRs were deactivated
HAID of deactivated CMR
8–153
or
21–166
CHAR CMR name Any UTF-
16BE value
CMR name (only present when the CMR Name flag is B'1')
Bytes 0–1 Trace entry length
This field contains the length of this trace entry, including the length field itself.
Bytes 2–3 Trace entry ID
This field identifies this as a CMR-Deactivation trace entry.
Byte 4 Trace entry flags
The trace entry flags identify whether or not optional information is present in the trace entry.
The trace-control flags in the XOH Trace command are used to control whether optional,
printer-provided information is present or is omitted.
Bit 0 Date and time stamp
If bit 0 = B'1', the date and time stamp fields are present; otherwise these
fields are omitted. The date and time stamp fields are fully described in the
Begin-Page trace entry description ().
Bit 1 CMR name
If bit 1 = B'1', the CMR name is present; otherwise the CMR name is omitted.
Bits 2–7 Reserved
Byte 5 Reserved


Bytes 6–7 or
19–20
HAID
When a specific CMR is deactivated, this parameter contains the CMR's Host-Assigned ID
from the DDOR command.
This field contains X'0000' when either an XOH-ERPD command is processed or when all
CMRs are deactivated via the DDOR command.
Bytes 8–153
or 21–166
CMR name (optional)
This field, if present, contains the 146-byte CMR name from within the CMR header. The CMR
Name flag in byte 4 indicates whether the name is present or omitted. [IPDS-4-1962]




