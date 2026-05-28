# Chapter 5. Exception Handling in PTOCA
This chapter:
* Describes exception condition detection [PTOCA-5-001]
* Describes exception responses and standard actions [PTOCA-5-002]
* Lists the PTOCA exception condition codes [PTOCA-5-003]
## Faithful Reproduction
PTOCA is intended to be precise enough to permit multiple products to reproduce the Presentation Text object
faithfully. Faithful reproduction includes such aspects as the size and relative positions of graphic characters
and strings of graphic characters. Examples are the placement of columns in tables, mathematical constructs
such as subscripts or limits of integrals, and the appearance of graphic characters. PTOCA can only make
faithful reproduction possible. The responsibility for faithful reproduction belongs to the process that presents
the object.
PTOCA is also designed to permit less than faithful reproduction of the Presentation Text object. It is possible
to specify those exception conditions for which continuation of processing is acceptable. This permits a
process that cannot faithfully reproduce the object to continue with its best approximation. If less than faithful
reproduction is acceptable for an application, interchange among a larger set of receivers is possible.
If a requirement for faithful reproduction is specified, and if a process cannot present a faithful reproduction,
reproduction is not continued.
To satisfy these objectives, PTOCA anticipates the existence of exception conditions, specifies how each is to
be handled so that results are predictable, and lets the controlling environment control exception condition
actions.
### Exception Conditions
An exception condition in the object is the appearance of the following:
* Invalid or unsupported parameter value [PTOCA-5-004]
* Invalid or unsupported parameter [PTOCA-5-005]
* Invalid or unsupported control sequence [PTOCA-5-006]
PTOCA specifies valid values for parameters, appropriate and inappropriate parameters, and valid and invalid
combinations of control sequences. In addition, an implementation may accept only a subset of valid values or
only a subset of appropriate parameters and control sequences. However, PTOC A specifies, by subsetting,
which of its controls and parameters are to be supported by the implementations of a subset.
Exception conditions can be classified as:
* Syntactic [PTOCA-5-007]
* Semantic [PTOCA-5-008]
* Pragmatic [PTOCA-5-009]
A syntactic exception condition is a violation of a structural architectural specification.
Syntactic exception conditions defined for PTOCA include:
* Invalid control sequence [PTOCA-5-010]
* Invalid parameter value [PTOCA-5-011]
* Control sequence appearing in invalid context [PTOCA-5-012]

<!-- Page 164 -->

A semantic exception condition is a violation of a functional architectural specification, that is, what a
parameter, structured field, or control sequence does, independent of how it looks or how it is used.
Semantic exception conditions defined for PTOCA include:
* Selection of inconsistent or contradictory functions [PTOCA-5-013]
* Loss of presentation information [PTOCA-5-014]
A pragmatic exception condition is an incorrect usage of an architectural specification that is valid structurally
and semantically. It is normally caused by an incompatibility between a Presentation Text object and a product
that processes or presents it. Pragmatic exception conditions are not defined by PTOCA and cannot be
detected by inspection of a Presentation Text object.
Pragmatic exception conditions include:
* Mismatch of characteristics of Presentation Text object and presentation product [PTOCA-5-015]
* Unavailable resource, for example, coded font [PTOCA-5-016]
* Unavailable function, for example, overstrike [PTOCA-5-017]
* Unsupported control sequence [PTOCA-5-018]
A product may be unable to distinguish between a syntactic exception and a pragmatic exception, for example,
between an invalid parameter value and a parameter value out of the product's range. [PTOCA-5-019]
## Exception Condition Detection
A potential exception condition may exist, yet not be detected during processing of a Presentation Text object.
A receiver is not required to process a Presentation Text object beyond its need to perform a specified function.
Therefore, a process usually detects only those exception conditions that pertain to the function it is
performing. PTOCA defines specific exception conditions that occur within the object, and enables the
controlling environment to provide a continuation capability by specifying standard action values to use when
no other source is available for the parameter values. In addition, PTOCA does not require that an
implementation of the controlling environment do anything more than receive exception conditions and
terminate processing the Presentation Text object as a result of them. However, the controlling environment
may place more stringent requirements.
Syntactic exception conditions can be detected without regard to the value of any other parameter or
structured field. A syntactic or semantic exception condition can be detected by inspection of a Presentation
Text object. A pragmatic exception condition cannot be detected by inspection of a Presentation Text object
alone, but requires knowledge of characteristics of the receiver. If a product that produces or processes a
Presentation Text object knows the characteristics of one or more receivers, it can avoid or detect pragmatic
exception conditions. If it does not, this detection must be performed by the receiver. [PTOCA-5-020]
## Exception Condition Handling
All processors of a Presentation Text object need not have the same capability for detecting, processing, or
reporting exception conditions. Processors of similar capabilities may handle the same exception condition in
diffe rent ways. A processor may provide alternative ways to handle an exception condition; however, the
processor's capabilities are limited by PTOCA.
The controlling environment of a Presentation Text object can specify the response a receiver should make
when exception conditions are encountered. This is specified in structures contained in the controlling
environment. These structures identify one or more exception conditions, and specify the exception response
or effect the exception condition will have on the document presentation process. For example, a document
may be terminated when an exception condition is encountered, or processing may continue using the
architected standard action for the exception condition.
A standard action is specified in PTOCA for many exception conditions. For example, if an implementation
cannot process some of the Presentation Text object, the standard action could be to present it with [PTOCA-5-021]
### Exception Conditions

<!-- Page 165 -->

unrecognized control sequences omitted or with specified valid parameters substituted for invalid parameters.
The standard actions are defined independent of where the exception condition is detected. That is, the
receiver may be an application, program product, mechanical device, and so forth. The process always
initiates the specified action, and is responsible for its satisfactory completion. [PTOCA-5-022]
## Exception Responses
When an exception condition is detected by a receiver, an exception response is assumed. Exception
responses are not specified by PTOCA. The exception condition codes specified are for reference purposes. If
the controlling environment specifies a different exception condition code for the same exception condition, the
controlling environment's exception condition code overrides the code specified by PTOCA. For example, if a
DBR control sequence will cause a rule to extend outside the boundaries of the object space, PTOCA specifies
that exception condition code EC-0103 be recognized. However, in the IPDS environment, exception ID
08C1..00 would be recognized. Please see Appendix A, “MO:DCA Environment”, and Appendix
B, “IPDS Environment”,. See “Related Publications” for data-stream documentation.
Some exception responses can be common to all exception conditions. Others are specific to particular
exception conditions.
Exception responses that can be common to all exception conditions include the following:
* T erminate processing Presentation Text object [PTOCA-5-023]
* Ignore the control that caused the exception condition and continue processing the object [PTOCA-5-024]
* Partially process the control that caused the exception condition [PTOCA-5-025]
* Report exception condition back to generator or forward it to the presenter of the object [PTOCA-5-026]
* Cause an intervention-required condition to occur at the receiver [PTOCA-5-027]
* Mark the presentation information with diagnostic information [PTOCA-5-028]
An example of an exception response that can vary depending on the exception condition is to present the
data that caused the exception condition. The data presented may be a receiver-dependent approximation if
faithful reproduction is not required. The data presented may be a special, recognizable marker instead of, or
in addition to, other data at the position of an exception condition. For example, a blotch may appear where a
rule is drawn beyond an object space extent. Another exception response is to present no data at the position
of an exception condition.
A response for each exception condition may be selected in a manner independent of any other exception
condition. Multiple responses may be selected for one exception condition. Certain exception condition actions
are mutually exclusive by their nature. PTOCA assumes that the controlling environment provides structures
external to the Presentation Text object for handling the responses to exception classes or specific exception
conditions received from the object processor. [PTOCA-5-029]
## Standard Actions
PTOCA specifies the standard actions that it assumes to be taken by the Presentation Text object processor
for specific exception conditions that can occur in the object. The receiver is expected to implement the
PTOCA standard action for those exception conditions it can recognize as part of the support of the subset
level. If the controlling environment specifies a different standard action for the same exception condition, the
action specified by the controlling environment overrides the action specified by PTOCA. For example, if an
invalid control sequence generates an exception condition, PTOCA may specify that the presentation process
ignore the invalid control sequence and continue presenting. However, for the same exception condition, IPDS
may require the presentation process to stop processing the Presentation Text object. Thus the PTOCA
processor has two options when it recognizes an exception condition: it can simply report the exception
condition to the controlling environment, and let the environment handle it; or it can apply the PTOCA standard
action.
### Exception Conditions

<!-- Page 166 -->

Exception Condition Codes
The following list contains the exception conditions that implementations of PTOCA are obligated to test for.
The codes are consistent with those of the data streams, and conform to the registry of exception condition
codes found in IPDS. Please see the appendixes for information about exception conditions in the controlling
environment.
Table 18. PTOCA Exception Conditions
PTOCA
Exception
Condition
Meaning Comments
EC-0001 Invalid text control.
* Invalid or unsupported function type in control sequence. [PTOCA-5-030]
* Invalid control sequence or initial text condition parameter. [PTOCA-5-031]
* Invalid or unsupported initial text condition parameter identifier. [PTOCA-5-032]
* Control sequence or initial text condition parameter is not in the [PTOCA-5-033]
supported subset.
EC-0103 Character box exceeds
object space.
* A character has been positioned so that a portion of its character box [PTOCA-5-034]
will exceed the object space in the I-direction or the B-direction.
* Caution - this exception condition is applicable only within a valid object [PTOCA-5-035]
area. If the boundary of the object space coincides with or extends
beyond the boundary of the object area, the appropriate data-stream
exception may take precedence over this exception condition.
EC-0201 Invalid LID in ESU.
* The active BSU LID is not the same as the LID specified in the ESU. [PTOCA-5-036]
* No active BSU LID when an ESU is processed. [PTOCA-5-037]
EC-0401 The end of the object is
encountered before a text
suppression has ended.
EC-0505 PTD unit base specified is
not a valid or supported
value.
EC-0601 Nested BSU.
* BSU is encountered before the previous suppression has ended. [PTOCA-5-038]
EC-0605 PTD $X_p$
- or $Y_p$ [PTOCA-5-039]
–units per
unit base specified is not
a valid or supported value.
EC-0705 PTD $X_p$
- or $Y_p$ [PTOCA-5-040]
–extent
specified is not a valid or
supported value.
EC-0C01 Coded Font LID specified
is not a valid or supported
value.
EC-0E02 Invalid or unsupported
color space in SEC.
EC-0E03 Invalid or unsupported
color value in SEC.
EC-0E04 Invalid percent value in
SEC.
The percent coverage parameter or the percent shading parameter in the
SEC (Highlight color space) contains an invalid value.
EC-0E05 Invalid or unsupported
number of bits for a color
component in SEC.
### Exception Conditions

<!-- Page 167 -->

Table 18 PTOCA Exception Conditions (cont'd.)
PTOCA
Exception
Condition
Meaning Comments
EC-0F01 Invalid text orientation in
STO.
* Baseline or inline orientation specified is not a valid or supported value. [PTOCA-5-041]
* The I and Borientations are identical. [PTOCA-5-042]
* Neither the I-direction nor the B-direction is parallel to the $X_p$ [PTOCA-5-043]
–direction.
EC-1001 SIM displacement
specified is not a valid or
supported value.
EC-1 101 SBI baseline increment
specified is not a valid or
supported value.
EC-1201 SIA adjustment or
direction specified is not a
valid or supported value.
EC-1301 AMB displacement
specified is not a valid or
supported value.
EC-1401 AMI displacement
specified is not a valid or
supported value.
EC-1403 Advancement of the
baseline coordinate
toward the I-axis is
specified but is not
supported.
EC-1501 RMI increment specified is
not a supported value.
EC-1601 RMB increment specified
is not a supported value.
EC-1701 SVI increment specified is
not a valid or supported
value.
EC-1802 Invalid Coded Font LID.
* The necessary mapping is not provided to support the specified coded [PTOCA-5-044]
font.
* The specified coded font is not available to the receiver. [PTOCA-5-045]
EC-1901 Invalid or unsupported
RPS target count.
* The target count parameter for RPS is invalid or unsupported. [PTOCA-5-046]
EC-1A01 RPS or TRN source string
length error or UCT data
length error.
* The data string length for TRN or RPS is an odd number. It must be [PTOCA-5-047]
even for double-byte fonts.
* The byte count specified for code points following UCT is an odd [PTOCA-5-048]
number. It must be even for double–byte encoded data. [PTOCA-5-049]
### Exception Conditions

<!-- Page 168 -->

Table 18 PTOCA Exception Conditions (cont'd.)
PTOCA
Exception
Condition
Meaning Comments
EC-1A03 Invalid Unicode data.
* A high-order surrogate code value was not immediately followed by a [PTOCA-5-050]
low-order surrogate code value. The high-order surrogate range is
U+D800 through U+DBFF.
* A low-order surrogate code value was not immediately preceded by a [PTOCA-5-051]
high-order surrogate code value. The low-order surrogate range is
U+DC00 through U+DFFF.
* An illegal UTF-8 byte sequence, as defined in the Unicode 3.2 [PTOCA-5-052]
Specification, was specified. For a discussion of illegal UTF-8 byte
sequences, see the Application Note.
EC-1B01 RPS target string length
error.
* The RPS repeat length is an odd number when a double-byte font is [PTOCA-5-053]
active. It must be even for double-byte fonts.
EC-1C01 Invalid control sequence
class.
* The class of a X'2B' control sequence is not X'D3'. [PTOCA-5-054]
EC-1E01 Invalid length for control
sequence or initial text
condition parameter.
* A required parameter has not been not specified. [PTOCA-5-055]
* Invalid control sequence or initial text condition parameter length. [PTOCA-5-056]
* Part of an optional parameter in a control sequence is missing. [PTOCA-5-057]
* A Coded Font LID has been omitted in a SCFL control sequence or in a [PTOCA-5-058]
Coded Font Local ID initial text condition parameter.
* SVIcontrol sequence increment parameter is missing. [PTOCA-5-059]
* DBR or DIR length parameter is missing. [PTOCA-5-060]
* SIM displacement parameter is missing. [PTOCA-5-061]
* I-orientation parameter or B-orientation parameter is missing in an STO [PTOCA-5-062]
control sequence.
EC-1F01 RPS length error.
* The RPS control sequence length is four and the repeat length is not [PTOCA-5-063]
zero.
EC-2100 Invalid character.
EC-3F02 Text orientation is
incompatible with selected
font.
EC-5803 An STC color or color
attribute cannot be
supported as specified.
EC-6802 Initial I-orientation
specified is not a valid or
supported value.
EC-6902 Initial B-orientation
specified is not a valid or
supported value.
EC-6A02 Initial I-displacement
specified is not a valid or
supported value.
EC-6B02 Initial B-displacement
specified is not a valid or
supported value.
EC-8002 DBR or DIR rule width
specified is not a valid or
supported value.
### Exception Conditions

<!-- Page 169 -->

Table 18 PTOCA Exception Conditions (cont'd.)
PTOCA
Exception
Condition
Meaning Comments
EC-8202 DBR or DIR rule length
specified is not a valid or
supported value.
EC-9801 BSU or ESU LID specified
is not a valid or supported
value.
EC-9803 TBM error.
* Receiver is unable to support TBM by printing full size characters. [PTOCA-5-064]
* Receiver cannot support substitution character in the TBM field. [PTOCA-5-065]
* Temporary move size exceeds the device limitations. [PTOCA-5-066]
* Substitution method receiver cannot support multi-offset temporary [PTOCA-5-067]
baselines.
EC-9A01 OVS selected overstrike
character that has an
invalid character
increment or is a non-
printing character.
* Character increment of overstrike character is less than or equal to [PTOCA-5-068]
zero.
* Character increment of overstrike character is less than the character- [PTOCA-5-069]
box size.
* Overstrike character is a non-printing character. [PTOCA-5-070]
EC-9B01 UCT parameter values for
CTLNGTH, UCTVERS,
BIDICT, or GLYPHCT are
invalid.
EC-9D01 Decryption is not available
on this device.
EC-9D02 Decryption reported an
error.
EC-9D03 No key information has
been set for decryption. [PTOCA-5-071]
### Exception Conditions

<!-- Page 170 -->


<!-- Page 171 -->

Copyright © AFP Consortium 1997, 2025 153