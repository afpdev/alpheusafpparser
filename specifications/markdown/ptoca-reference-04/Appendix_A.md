Appendix A. MO:DCA Environment
This appendix describes how Presentation Text objects may be included within a MO:DCA document for the
purpose of interchanging Presentation Text objects between a generating node and a receiving node. See
Mixed Object Document Content Architecture Reference, AFPC-0004, for a full description of the MO:DCA
architecture.
The Presentation Text Data Descriptor (PTD) and Presentation Text Data (PTX) structured fields, which carry
Presentation Text objects in MO:DCA architecture documents, are defined in the following pages.
To guarantee interchange, a MO:DCA document carrying a Presentation Text object must include all
information related to the object. The MO:DCA document must therefore contain not only the definition of the
Presentation Text object, but it must also provide linkage to the resources the object references.
The discussion of MO:DCA structured fields is included in this appendix solely for setting the context of their
use by PTOCA.
Compliance with MO:DCA Interchange Sets
When Presentation Text objects are interchanged with the purpose of outputting the objects on a display ,
printer , or other output device, it is very important that visual fidelity be maintained as far as possible. In an
attempt to satisfy this objective, PTOC A defines the following for the MO:DCA environment:
• A set of rules that must be followed by all generators when constructing Presentation Text objects. [PTOCA-A-001]
• A set of Presentation Text processing capabilities that are guaranteed to be supported by all receivers. [PTOCA-A-002]
In order to comply with a particular MO:DCA Interchange Set, products that generate Presentation Text objects
must only generate objects that contain Presentation Text items and values defined in that interchange set.
Including items or values not in the interchange set can result in processing exceptions being raised by the
receiving processor , and exception actions being taken. However , a generator must not rely on a receiver
taking these actions.
In order to conform to a particular MO:DCA Interchange Set, products that receive Presentation Text objects
and convert them using a processor for output to some device are required to support all the Presentation Text
facilities defined in that interchange set.
It is optional whether the processor in the receiving node checks each Presentation Text object for compliance
with the relevant interchange set. A receiver can optionally raise warnings, in the form of errors, if a non-
compliant Presentation Text object is detected.
The Presentation Text object space is the presentation space within which the object generator may position
graphic characters without error , and it is the responsibility of the generator to ensure that the graphic
characters it places in the object space are positioned so that they do not exceed the object space. [PTOCA-A-003]

<!-- Page 182 -->

164 PTOCA Reference [PTOCA-A-004]
Presentation Text Structured Fields in the MO:DCA Architecture
Presentation Text Data Descriptor (PTD)
The PTOCA Presentation Text Data Descriptor is carried in the MO:DCA Presentation Text Data Descriptor
(PTD) structured field.
Structured Field Introducer
SF Length SF Identifier
X'D3B19B'
Flags Reserved
X'0000'
PTD Data
The following table describes the contents of the Presentation Text Data Descriptor (PTD) structured field in
the MO:DCA architecture, which has a structured field identifier of X'D3B19B'.
Offset Type Name Range Meaning M/O Def Ind
0 CODE XPBASE X'00'–X'01' Unit base for X axis; must [PTOCA-A-005]
be the same as the unit
base for Y axis:
X'00' Ten inches
X'01' Ten centimeters
M N N
1 CODE YPBASE X'00' –X'01' Unit base for Y axis; must [PTOCA-A-006]
be the same as the unit
base for X axis:
X'00' Ten inches
X'01' Ten centimeters
M N N
2–3 UBIN XPUNITVL X'0001' –
X'7FFF'
X
p
-units per unit base M N N
4–5 UBIN YPUNITVL X'0001' –
X'7FFF'
Y
p
-units per unit base M N N
6–8 UBIN XPEXTENT X'000001' –
X'007FFF'
X
p
-extent. See notes 1
and 2.
M N N
9–1 1 UBIN YPEXTENT X'000001' –
X'007FFF'
Y
p
-extent. See notes 1
and 2.
M N N
12–13 TEXTFLAGS Reserved. See note 3. O Y N
14–end of PTD TXTCONDS Initial text conditions O Y See
note 4.
Notes:
1. The range values shown assume a measurement unit of 1/1440 inch. That is, the measurement base is ten [PTOCA-A-007]
inches, and the X
p
- and Y
p
-units per unit base are 14,400. If it is necessary to convert to a diffe rent
measurement unit, please see the conversion routine described in “Interpreting Ranges”.
2. The default indicator is not allowed for this parameter in this subset. [PTOCA-A-008]
3. The TEXTFLAGS parameter is reserved. Generators should set this parameter to X'0000', and receivers [PTOCA-A-009]
should ignore it.
4. See the description of the control sequence that specifies the initial text condition. [PTOCA-A-010]
Presentation Text Structured Fields [PTOCA-A-011]

<!-- Page 183 -->

PTOCA Reference 165
When the Presentation Text Data Descriptor (PTD) is included in the Active Environment Group (AEG) for a
MO:DCA page, the PTOCA object space and object area coincide with the page presentation space, therefore
the size of the Presentation Text object space is set equal to the size of the page presentation space. When a
Presentation Text object is transformed into an IPDS data stream, the PTD parameters are mapped to IPDS
Logical Page Descriptor (LPD) command parameters. Optionally , some PTD parameters may be transformed
into control sequences as part of an IPDS Write Text command. When the PTD is specified in the OEG of a
MO:DCA text object, the extents of the text object space are defined by the XPEXTENT and YPEXTENT
parameters and can be set to any value in the allowed range.
Architecture Note: When the PTD is included in the AEG for a MO:DCA page, some AFP print servers
require that the measurement units in the PTD match the measurement units in the Page Descriptor
(PGD). It is therefore strongly recommended that whenever the PTD is included in the AEG, the same
measurement units are specified in both the PTD and PGD.
The coded font information from the MO:DCA Map Coded Font (MCF) and Map Data Resource (MDR)
structured fields is used to determine what the Load Font Equivalence command content should be in the IPDS
environment. See Appendix B, “IPDS Environment” for more information about the IPDS
environment.
Initial text conditions are specified by including the control sequence that contains the parameter to be
initialized. It is recommended that exactly one chain be used to specify initial text conditions. The first control
sequence that specifies an initial text condition parameter starts with the X'2BD3' prefix and class code and
indicates chaining with an odd function type (low-order bit is B'1') if other control sequences follow . All control
sequences that follow , except for the last, are chained control sequences that start with their length byte (not
with X'2BD3') and have an odd function type. The last control sequence in the chain starts with the length byte
but indicates termination of the chain with an even function type (low-order bit is B'0'). For a description of
chaining, see “Control Sequence Chaining”.
Table 19 shows which control sequence may be used to specify a particular initial text condition
parameter . Control sequences are optional and may appear in any order . If a control sequence appears
multiple times, the last occurrence determines the setting of the initial text condition. Control sequences that
are not listed in this table are treated as NOPs.
Table 19. PTOCA Initial Text Conditions in a MO:DCA Environment
Initial Text Condition Parameter Control Sequence
Baseline Increment Set Baseline Increment (SBI)
Coded Font Local ID Set Coded Font Local (SCFL)
Extended Text Color Set Extended Text Color (SEC)
Initial Baseline Coordinate Absolute Move Baseline (AMB)
Initial Inline Coordinate Absolute Move Inline (AMI)
Inline Margin Set Inline Margin (SIM)
Intercharacter Adjustment Set Intercharacter Adjustment (SIA)
Text Color Set Text Color (STC)
Text Orientation Set Text Orientation (STO)
Architecture Note: The Extended Text Color parameter is not supported as an initial text condition in IPDS
environments when text is specified as text-major , that is, when the PTD for the text is specified in the
AEG for the page. This parameter is supported in IPDS environments when the text is specified in a text
object with OEG and the PTD is specified in the OEG.
Presentation Text Data Descriptor (PTD) [PTOCA-A-012]

<!-- Page 184 -->

166 PTOCA Reference [PTOCA-A-013]
Architecture Note: The following format 1 version of the Presentation Text Data Descriptor is a coexistence
MO:DCA structured field and may only be used for migration purposes. The PTD format 1 structured
field is not allowed in the OEG of a MO:DCA presentation text object.
Structured Field Introducer
SF Length SF Identifier
X'D3A69B'
Flags Reserved
X'0000'
PTD Data
Offset Type Name Range Meaning M/O Def Ind
0 CODE XPBASE X'00' Unit base for X axis; ten [PTOCA-A-014]
inches
M N N
1 CODE YPBASE X'00' Unit base for Y axis; ten [PTOCA-A-015]
inches
M N N
2–3 UBIN XPUNITVL X'0960' –
X'3840'
X
p
-units per unit base M N N
4–5 UBIN YPUNITVL X'0960' –
X'3840'
Y
p
-units per unit base M N N
6–7 UBIN XPEXTENT X'0001' –
X'7FFF'
X
p
-extent M N N
8–9 UBIN YPEXTENT X'0001' –
X'7FFF'
Y
p
-extent M N N
10–1 1 BITS TEXTFLAGS Reserved. Must be set to
zeros
O Y N
Presentation Text Data (PTX)
Structured Field Introducer
SF Length SF Identifier
X'D3EE9B'
Flags Reserved
X'0000'
PTD Data
The Presentation Text Data (PTX) structured field has a structured field identifier of X'D3EE9B'. It contains the
graphic characters and the control sequences that position the graphic characters.
The contents of the PTX structured field, that is, graphic characters and control sequences, may be included
directly into one or more IPDS Write Text (WT) commands by removing the MO:DCA structured field introducer
and replacing it with the IPDS WT command syntax. The length information from the PTX structured field must
be changed to reflect the correct length in the WT command.
Presentation text data can span multiple PTX structured fields and can be split on any byte boundary .
Therefore, a control sequence or chain of control sequences, or a sequence of single- or multi-byte code points
can start in one PTX and continue or terminate in a following PTX.
Presentation Text Data (PTX) [PTOCA-A-016]

<!-- Page 185 -->

PTOCA Reference 167
Presentation Exception Conditions
An implementation of the MO:DCA architecture may detect and report PTOCA exception conditions as
required to perform or assure the function for which it was designed. The MO:DCA architecture expects a
PTOCA processor to handle exception conditions, either by using PTOCA standard actions or by recovering in
some other manner .
Presentation Suppression Handling
Suppression of graphic characters is activated by referencing the local ID from the Begin Suppression and End
Suppression control sequences with a keyword in the MO:DCA Medium Modification Control (MMC) structured
field. The local ID may also be mapped to a global name with a Map Suppression (MSU) structured field. For
further information, please see the Mixed Object Document Content Architecture Reference, AFPC-0004.
Additional Related Structured Fields
Map Coded Font (MCF): Font information for FOCA-based fonts is provided by the Map Coded Font (MCF)
structured field which maps the LID parameter of the Set Coded Font Local control sequence to a FOCA font.
Map Data Resource (MDR): Font information for TrueType/OpenType fonts is provided by the Map Data
Resource (MDR) structured field which maps the LID parameter of the Set Coded Font Local control sequence
to a TrueType/OpenType font.
For further information about these structured fields, please refer to Mixed Object Document Content
Architecture Reference, AFPC-0004.
Additional Related Structured Fields [PTOCA-A-017]

<!-- Page 186 -->

168 PTOCA Reference [PTOCA-A-018]

<!-- Page 187 -->

Copyright © AFP Consortium 1997, 2025 169
