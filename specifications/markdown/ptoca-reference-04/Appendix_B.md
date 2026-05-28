Appendix B. IPDS Environment
The Intelligent Printer Data Stream (IPDS) provides the printer subsystem environment for Presentation Text
objects. This appendix describes:
• The context of Presentation Text objects in the IPDS environment; as either text-major text or as independent [PTOCA-B-001]
text objects
• A comparison of PTOCA and IPDS exception conditions [PTOCA-B-002]
• IPDS commands specific to presentation text [PTOCA-B-003]
For further information about the IPDS Architecture, refer to Intelligent Printer Data Stream Reference, AFPC-
0001.
IPDS Presentation Text
Presentation Text objects are transmitted to print devices acting as receivers as part of an IPDS data stream so
that a presentation on the print device may be made with visual fidelity . The IPDS Architecture is expressly
designed to support all points addressable (APA) printing function that allows text, image, graphics, and bar
code to be positioned and presented at any point on the printed page.
Text is described to the IPDS printers in terms of Presentation Text data within Write Text commands. The
Presentation Text data is comprised of graphic characters and control sequences in the same format as carried
in MO:DCA data streams except that the containing IPDS structure has a diffe rent syntax. The printers that
receive the Presentation Text object are expected to process the object contents accurately according to the
semantic definitions for the supported PTOC A subset.
IPDS Architecture provides the following for the object:
• Command structure and syntax [PTOCA-B-004]
• Initial conditions for the object [PTOCA-B-005]
• A means of reporting exception conditions [PTOCA-B-006]
• A means of resolving fonts and suppression identifiers [PTOCA-B-007]
IPDS Architecture provides positioning information for the object. The object does not carry the size and shape
of the physical medium or information about object positioning. The object does not control what part of its
object space is to be presented or what part will fit into the logical page specified by the data stream. The
object expresses the exact boundaries that the graphic characters it positions in the object space will require if
they are to be presented without error . The logical page may also be used to provide that boundary .
Text data can be positioned anywhere on the logical page in two diff erent ways:
1. All IPDS printers allow text to be placed directly within a logical page using the Write Text command. The [PTOCA-B-008]
logical page can also contain other presentation objects specified with other IPDS commands either before
or after a Write Text command. With this method, called “text major”, there is no text object area, and text
may be printed anywhere within the valid printable area. For text-major text, the text presentation space is
the logical page. Furthermore, object areas for other objects may be positioned with respect to the text.
2. Some IPDS printers support text objects (in addition to the text-major concept). In this case, the Write Text [PTOCA-B-009]
Control command defines a presentation space, object area, and mapping option. The text data is carried
within one or more Write Text commands.
Architecture Note: The Extended Text Color parameter is not supported as an initial text condition in IPDS
environments for text-major text. This parameter is supported in IPDS environments when the text is
specified in a text object. [PTOCA-B-010]

<!-- Page 188 -->

170 PTOCA Reference [PTOCA-B-011]
IPDS Text Command Set
Presentation Text Data Descriptor for Text-Major Text
The following table describes the contents of the PTOCA Presentation Text Data Descriptor and the
corresponding parameter location within the IPDS Logical Page Descriptor (LPD) command.
The offset in the table indicates the beginning of the parameter data relative to the beginning of the data
portion of the LPD command. This table reflects the descriptor subset syntax for the Presentation Text Data
Descriptor .
PTOCA Parameter IPDS LPD Offset Notes
UNITBASE 0
XPUNITVL 2-3
YPUNITVL 4-5
XPEXTENT 7-9 5
YPEXTENT 1 1-13 5
TEXTFLAGS 3
Initial Text Condition Parameters
Text Orientation:
IORNTION
BORNTION
24-25
26-27
2
2
Initial Inline Coordinate 28-29 5
Initial Baseline Coordinate 30-31 5
Inline Margin 32-33 2
Intercharacter Adjustment:
ADJSTMNT
DIRCTION
34-35 2
4
Baseline Increment 38-39 2
Coded Font Local ID 40 2
Text Color:
FRGCOLOR
41-42 1,2
Notes:
1. If a receiver cannot provide the color specified by FRGCOLOR, it may substitute values from Table 13 on [PTOCA-B-012]
page 103 defined in the STC control sequence.
2. The default indicator is allowed, meaning use the printer default. [PTOCA-B-013]
3. The TEXTFLAGS parameter is reserved. This parameter is not used in the IPDS environment. [PTOCA-B-014]
4. DIRCTION is always defaulted to X'00', that is, the positive direction, so this parameter is not carried in the [PTOCA-B-015]
Logical Page Descriptor (LPD) command.
5. The default indicator is not allowed for this parameter in this subset. [PTOCA-B-016]
IPDS Presentation Text Data Descriptor for Text Objects
When a text object is used within an IPDS data stream, the PTOCA Presentation Text Data Descriptor is
carried within the Write Text Control (WTC) command. This portion of the WTC command is called the Text
IPDS Text Command Set [PTOCA-B-017]

<!-- Page 189 -->

PTOCA Reference 171
Data Descriptor (TDD) and specifies parameters that define the text presentation space size and initial text
default conditions. The format of the TDD is as follows:
Offset Type Name Range Meaning Required
0–1 UBIN Length X'0014' – end
of TDD
Length of TDD, including this field X'0014' – end of
TDD
2–3 CODE SDF ID X'A69B' Self-defining-field ID X'A69B'
4–5 X'0000' Reserved X'0000'
6 CODE Unit base X'00' [PTOCA-B-018]
X'01'
X'02'
Ten inches
Ten centimeters
Retired item 54
X'00'
7 X'00' Reserved X'00' [PTOCA-B-019]
8–9 UBIN XUPUB X'0001' –
X'7FFF'
X
t
units per unit base X'3840'
10–1 1 UBIN YUPUB X'0001' –
X'7FFF'
Y
t
units per unit base X'3840'
12–14 UBIN X
t
extent X'000001' –
X'007FFF'
X
t
extent of the text presentation space in L-
units
X'000001' –
X'007FFF'
15–17 UBIN Y
t
extent X'000001' –
X'007FFF'
Y
t
extent of the text presentation space in L-
units
X'000001' –
X'007FFF'
18–19 BITS Text flags B'00...00' Reserved for text flags B'00...00'
20 to
end of
TDD
Initial text
conditions
Initial text conditions, in the same syntax as
bytes 14 to end of the MO:DCA PTD found in
the table under “Presentation Text Data
Descriptor (PTD)”
Presentation Text Data
Presentation Text data, which contains the graphic characters and the control sequences that position the
graphic characters, is carried in the IPDS Write Text (WT) command. The subsets of PTOC A that are used by
IPDS printers are the PT1, PT2, PT3, and PT4 subsets. Notice that each subset contains all of the lower
number subsets; for example, PT3 contains all of PT2 (and therefore all of PT1).
The contents of the PTX structured field in a MO:DCA architecture data stream, that is, graphic characters and
control sequences, may be included directly into one or more IPDS Write Text (WT) commands. Remove the
MO:DCA structured field introducer , replace it with the IPDS WT command syntax, and correct the length
information to reflect the length of the WT command.
Presentation Text data can span multiple WT commands. That is, a control sequence or chain of control
sequences can be started in the data sent by one WT command and can be completed in the data sent by the
WT commands that follow .
A WT command may end in the middle of an embedded control sequence or a double-byte code point. In this
event, an exception results if any commands other than Execute Order Anystate, No Operation, Set Home
State, or Sense Type and Model are received before the next WT command.
Presentation Exception Conditions
The IPDS Architecture defines its own exception condition codes, called exception IDs, which consist of three
bytes. PTOCA exception condition codes are mapped to IPDS exception IDs by mapping the two-byte PTOCA
code to the last two bytes of the IPDS exception code. In most cases, this mapping is one-to-one. Where it is
Presentation Exception Conditions [PTOCA-B-020]

<!-- Page 190 -->

172 PTOCA Reference [PTOCA-B-021]
not, the IPDS exception ID overrides the PTOC A exception condition code. The IPDS Architecture also defines
its own exception responses. In some cases, this exception response is the same as the standard exception
action defined by PTOCA. Where it is not, the IPDS exception response overrides the PTOCA standard
exception action. Table 20 shows the mapping of PTOCA exception condition codes to IPDS exception IDs.
Table 20. PTOCA Exception Conditions in an IPDS Environment
PTOCA Exception Condition Code IPDS Exception ID
EC-0001 0200..01
EC-0103 08C1..00 for text major
0201..03 for text objects [PTOCA-B-022]
EC-0201 0202..01
EC-0401 0204..01
EC-0505 0264..02
EC-0601 0206..01
EC-0605 0260..02
EC-0705 0261..02
EC-0C01 020C..01
EC-0E02 020E..02
EC-0E03 020E..03
EC-0E04 020E..04
EC-0E05 020E..05
EC-0F01 020F ..01
EC-1001 0210..01
EC-1 101 021 1..01
EC-1201 0212..01
EC-1301 0213..01
EC-1401 0214..01
EC-1403 0214..03
EC-1501 0215..01
EC-1601 0216..01
EC-1701 0217..01
EC-1802 0218..02
EC-1901 0219..01
EC-1A01 021A..01
EC-1A03 021A..03
EC-1B01 021B..01
EC-1C01 021C..01
EC-1E01 021E..01
EC-1F01 021F ..01
EC-2100 0821..00
EC-3F02 023F ..02
EC-5803 0258..03
Presentation Exception Conditions [PTOCA-B-023]

<!-- Page 191 -->

PTOCA Reference 173
Table 20 PTOCA Exception Conditions in an IPDS Environment (cont'd.)
PTOCA Exception Condition Code IPDS Exception ID
EC-6802 0268..02
EC-6902 0269..02
EC-6A02 026A..02
EC-6B02 026B..02
EC-8002 0280..02
EC-8202 0282..02
EC-9801 0298..01
EC-9803 0298..03
EC-9A01 029A..01
EC-9B01 029B..01
EC-9D01 029D..01
EC-9D02 029D..02
EC-9D03 029D..03
Additional Related Commands
The following description is an overview . For further information about these commands, please refer to
Intelligent Printer Data Stream Reference, AFPC-0001.
Sense Type and Model (STM): Requests information from the printer that identifies the type and model of the
device and the command sets supported. The information requested is returned in the Special Data Area of the
Acknowledge Reply to the STM command. The command sets and data levels supported, such as PT1, are
also returned as part of the acknowledgement data.
XOH Obtain Printer Characteristics (XOH OPC): Requests information from the printer that identifies
characteristics of the device. The characteristics include information about the printable area currently
available, coded font resolution, and color support.
XOA Request Resource List (XOA RRL): Requests the printer to return a specified list of available resources
(coded fonts, overlays, and page segments) in the Acknowledge Reply to this command. This information is
provided for use in resource management functions.
Load Font Equivalence (LFE): This command is sent to the printer to map the LID parameter of the Set
Coded Font Local control sequence to the coded font's Host Assigned ID to select the font and font attributes
to be used by the printer . The coded fonts do not have to exist in the printer when the printer receives this
command.
The mapping function provided by this command is independent of the specific font technology employed by
the printer . For example, the device may resolve the mapping to stored font patterns downloaded from the host
or to permanently resident font patterns.
The coded font resource used for Presentation Text objects may be used by other data objects, such as
graphics, as well.
Load Equivalence (LE) and Load Copy Control (LCC): The LE command permits physical values
embedded in printer data to be referenced externally using different values, and the LCC command specifies
external suppression values to be used in conjunction with the presentation text. These two functions are used
to control the suppression function in PTOCA.
Additional Related Commands [PTOCA-B-024]

<!-- Page 192 -->

174 PTOCA Reference [PTOCA-B-025]
Suppression is started by external suppression values specified in the current Load Copy Control (LCC)
Command. The external suppression value works with the LID parameter in two ways:
• The external suppression value maps to the LID parameter of the BSU control sequence. For example, if the [PTOCA-B-026]
external value is X'0A', the presentation text identified with the LID X'0A' is suppressed.
• The external suppression value maps to the LID through an equivalence table, using the current Load [PTOCA-B-027]
Equivalence (LE) command. For example, if the external value is X'0B', the LE table may define the following
equivalences: X'0B'=X'01', X'0B'=X'02', X'0B'=X'03'. Thus, the external value X'0B' identifies presentation
text marked with LID values X'01', X'02', and X'03' for suppression.
If no external suppression values are started by the LCC command, the BSU and ESU control sequences
are processed as No Operation control sequences. Exception conditions defined for BSU or ESU control
sequences are detected by the printer whether or not an external suppression value is specified by the LCC
command.
During suppression, the printer performs all composition actions, including field checking, and processes all
control sequences, such as SCFL, RMI, AMI, DIR, and so on.
The printer can use a single suppression LID for more than one Begin and End Suppression pair if the LIDs are
all mapped to the same external value. For example, if LID values of X'06', X'07', and X'09' from BSU, ESU
control sequence pairs are mapped to an external value of X'02' from a Load Copy Control (LCC) command by
an appropriate LE command received by the printer , the presentation text marked by the three LID values will
be suppressed by the single value of X'02'.
BSU, ESU pairs outside an overlay have no effe ct within the overlay , and BSU, ESU pairs within an overlay
have no effect outside the overlay .
Font Commands
The IPDS architecture provides the ability to manage and use a wide variety of font resources. These font
resources can be classified into two major categories: coded font components and data-object-font
components. Coded fonts and their component parts are defined within the Font Object Content Architecture
(FOCA); data-object-font components include the FOCA code page, TrueType/OpenType fonts, and TrueType/
OpenType collections. TrueType/OpenType fonts use an outline font technology as do FOCA outline fonts. The
FOCA and IPDS architectures also include support for raster fonts and a very simple type of font called a
symbol set.
The following IPDS commands are used with TrueType/OpenType fonts and collections:
• Activate Resource (AR) [PTOCA-B-028]
• Deactivate Data-Object-Font Component (DDOFC) [PTOCA-B-029]
• Deactivate Font (DF) [PTOCA-B-030]
• Load Code Page (LCP) [PTOCA-B-031]
• Load Code Page Control (LCPC) [PTOCA-B-032]
• Write Object Container Control (WOCC) [PTOCA-B-033]
• Write Object Container (WOC) [PTOCA-B-034]
• XOH Erase Residual Font Data (ERFD) [PTOCA-B-035]
• XOH Erase Residual Print Data (ERPD) [PTOCA-B-036]
The following IPDS commands are used with FOCA outline fonts:
• Activate Resource (AR) [PTOCA-B-037]
• Deactivate Font (DF) [PTOCA-B-038]
• Load Code Page (LCP) [PTOCA-B-039]
• Load Code Page Control (LCPC) [PTOCA-B-040]
• Load Font (LF) [PTOCA-B-041]
• Load Font Character Set Control (LFCSC) [PTOCA-B-042]
Font Commands

<!-- Page 193 -->

PTOCA Reference 175
• Load Font Equivalence (LFE) [PTOCA-B-043]
• XOH Erase Residual Font Data (ERFD) [PTOCA-B-044]
The following IPDS commands are used with FOCA raster fonts:
• Activate Resource (AR) [PTOCA-B-045]
• Deactivate Font (DF) [PTOCA-B-046]
• Load Font (LF) [PTOCA-B-047]
• Load Font Control (LFC) [PTOCA-B-048]
• Load Font Equivalence (LFE) [PTOCA-B-049]
• Load Font Index (LFI) [PTOCA-B-050]
• XOH Erase Residual Font Data (ERFD) [PTOCA-B-051]
The following IPDS commands are used with symbol set fonts:
• Activate Resource (AR) [PTOCA-B-052]
• Deactivate Font (DF) [PTOCA-B-053]
• Load Font Equivalence (LFE) [PTOCA-B-054]
• Load Symbol Set (LSS) [PTOCA-B-055]
Font Commands

<!-- Page 194 -->

176 PTOCA Reference [PTOCA-B-056]

<!-- Page 195 -->

Copyright © AFP Consortium 1997, 2025 177
