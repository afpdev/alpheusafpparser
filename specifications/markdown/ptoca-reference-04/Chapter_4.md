# Chapter 4. Data Structures in PTOCA
This chapter:
* Describes the role of parameters [PTOCA-4-001]
* Explains documentation conventions used in this chapter [PTOCA-4-002]
* Provides a detailed description of the control sequence [PTOCA-4-003]
* Explains how graphic characters are processed [PTOCA-4-004]
* Provides a detailed description of the Presentation Text data [PTOCA-4-005]
* Provides a detailed description of the Presentation Text Data Descriptor [PTOCA-4-006]
## Parameters and Parameter Values
Kinds of Parameters: The control sequences used within the Presentation Text object may contain parameters
that describe and control the way the control sequence affects the graphic characters to be presented. A
parameter is a variable to which a value is assigned. A parameter has an architected length, a set of values,
and a functional definition. These parameter values may be numeric, such as those that tell where text is to be
presented, or logical, such as those that tell whether data should be suppressed. A parameter value can be the
default indicator, specified by the value X'F...F'. The default indicator means that the effective hierarchical value
is to be used instead of a value explicitly specified at this point. A parameter can be a reserved field. A
reserved field has a prescribed value, but no functional definition. Reserved fields should be set to zero by
PTOCA generators and should be ignored by receivers.
A mandatory parameter appears in a control sequence because the function of that parameter is required and
an actual value is necessary for proper performance. A mandatory parameter value may be the default
indicator, provided that the parameter has an actual value somewhere in the hierarchy. Mandatory parameters
must be supported by both PTOCA generators and receivers. In a sense, all parameters are required, since
the value of each parameter must be known if it is to have an effect on presentation. However, some
parameters are required only for specific types of presentation, and an actual value is not always necessary for
some parameters. For example, a default value may be acceptable.
An optional parameter need not appear in a control sequence. The function of that parameter may not be
required, or if the function is required, a default value may be acceptable instead. An optional parameter may
appear if the default value is not acceptable, if it is desirable to make a value explicit for documentation, or to
avoid the effect of values specified externally to the Presentation Text object. Optional parameters must be
supported by all PTOCA receivers.
Hierarchy: Certain parameters, called external parameters, use the following hierarchical techniques in
specification. If the parameter is not specified by individual control sequences in the Presentation Text object,
that is, the parameter is omitted or the parameter is the default indicator, the parameter may be specified in the
Presentation Text Data Descriptor. If it is not, the PTOCA default value is used. Not all parameters need to be
set at all levels of the specification hierarchy. Table 9 identifies the valid hierarchical specification
levels for the parameters, indicated by X in the associated column. Note that the hierarchy consists of the
control sequence first, then the descriptor, and finally the PTOCA default value. Thus from a receiver's point of
view, the primary source for a parameter value is a control sequence. If it is possible for a control sequence to
provide the value, there will be an X in the control sequence column. If there is no control sequence to provide
the needed value, or if the appropriate control sequence is present but specifies the default indicator, the
descriptor becomes the source of the value. If it is possible for the descriptor to provide the value, there will be
an X in the descriptor column. However, if the descriptor cannot provide the value, or if the descriptor specifies
the default indicator, the PTOCA default value is used.
Default values and Presentation Text Data Descriptor values are termed external parameter specifications,
because these parameters need not be explicitly defined in the Presentation Text object. These values become
current values each time presentation of a Presentation Text object begins. [PTOCA-4-007]

<!-- Page 52 -->

Ranges of Values: Every value must fit within the field defined to contain it. Additional constraints on values are
defined by the PTOCA subsets. See Chapter 6, “Compliance with PTOCA”, and the appendixes
for further information about ranges.
Negative numbers are expressed in twos-complement form. If a parameter is less than the minimum specified
or more than the maximum specified, an exception condition exists. See the semantics of the affected control
for the appropriate exception condition code.
The maximum absolute value of a one-byte arithmetic value is 254 when the default indicator is valid. When
the default indicator is specifically excepted, the one-byte maximum arithmetic value is 255. One-byte values
are always unsigned. The maximum absolute value of a signed two-byte arithmetic value is 32,768. The
maximum absolute value of an unsigned two-byte arithmetic value is 65,534 when the default indicator is valid,
and 65,535 when the default indicator is not valid.
The minimum requirements of PTOCA for receivers regarding range is to provide calculation capacity equal in
size to the number of bits in the respective parameters. This is limited by the subset. Processing of the
Presentation Text object necessitates tracking the current positions within the object space. In addition,
PTOCA requires that receivers be capable of tracking the current positions outside of the object space as long
as presentation is not attempted.
The following example illustrates the intent of this concept for $I_c$
. A receiver may determine the maximum [PTOCA-4-008]
number of bits for $I_c$
based on the physical size and resolution of the mechanism. For example, the receiver
may base it on a carriage width of three inches and a resolution of 1/1440 inch. For this receiver, positioning
outside the object space could be handled in the cases where the object space is smaller than the carriage
width. But when the object space is equal to or greater than the carriage width, the receiver's calculation
capacity may not be large enough to contain the calculations outside the object space, and the results may be
unpredictable.
Such overflow is not considered to be an exception condition by PTOCA. However, the architectural
recommendation to generators is to avoid addressable positions that are outside of the object space.
Parameter Data Types: Every parameter value is one of the following data types:
A bit string (BITS) is a string of bits one or more bytes long. Each bit has the value B'1' or B'0'.
A code (CODE) is a constant for which PTOCA defines a particular meaning.
A number is an unsigned (UBIN) or signed (SBIN) arithmetic value that implies count or magnitude.
A character string (CHAR) is one or more bytes of character information.
An undefined field (UNDF) is a field that is not defined by PTOCA.
In all cases bytes are composed of eight bits, called bits 0 - 7. Bit 0 is in the high-order position; that is, bit 0 =
2
7
and bit 7 = 2
0
. Two bytes considered together are sixteen bits, called bits 0 - 15. Bit 0 is in the high-order [PTOCA-4-009]
position; that is, bit 0 = 2
15.
and bit 15 = 2
0
. The first byte received is the high-order byte, that is, bits 0 - 7. The [PTOCA-4-010]
second byte is the low-order byte, that is, bits 8 - 15. This is called big-endian bit order and big-endian byte
order.
Different syntax is used for the expression of values that pertain to rotation.
The Default Indicator: For certain parameters, the value X'F...F' represents the default indicator. For these
parameters, the arithmetic value -1 is not available. The default indicator does not specify a value for a
parameter and, therefore, maintains a default value for that parameter. This indicator specifies that the default
value be obtained from the hierarchy, not including previous instances of the same parameter in the current
object. The syntax of each control sequence specifies whether the default indicator is valid for its parameters.
In general, the default value for an omitted optional trailing parameter is obtained from previous instances of
the same parameter in the current object according to the hierarchy. [PTOCA-4-011]
## Parameters and Parameter Values

<!-- Page 53 -->

**Table 9. Parameter Specification Hierarchy** [PTOCA-4-012]

| Parameter | Set by Control Sequence (highest priority) | Set by Descriptor | PTOCA Default Value (lowest priority) |
|:--- |:---: |:---: |:--- |
| Measurement Units | | X | Receiver dependent [PTOCA-4-013]|
| Size | | X | Receiver dependent [PTOCA-4-014]|
| Baseline Increment | X | X | Receiver dependent, should be based on the coded font [PTOCA-4-015]|
| Suppression identifier | | | None [PTOCA-4-016]|
| Coded Font Local ID | X | X | Receiver dependent [PTOCA-4-017]|
| Intercharacter Adjustment | X | X | 0 [PTOCA-4-018]|
| Intercharacter Direction | X | X | 0 [PTOCA-4-019]|
| Inline Margin | X | X | 0 [PTOCA-4-020]|
| Initial Baseline Coordinate | | X | Receiver dependent [PTOCA-4-021]|
| Initial Inline Coordinate | | X | 0 [PTOCA-4-022]|
| Foreground Color | X | X | X'FF07' [PTOCA-4-023]|
| Text Orientation | X | X | 0,90 [PTOCA-4-024]|
| Rule Length | X | | None [PTOCA-4-025]|
| Rule Width | X | | Receiver dependent [PTOCA-4-026]|
| Overstrike Bypass Identifiers | X | | X'01' [PTOCA-4-027]|
| Overstrike Character | X | | Coded font dependent [PTOCA-4-028]|
| Temporary Baseline Increment | X | | ½ the Baseline Increment [PTOCA-4-029]|
| Temporary Baseline Direction | X | | 0 [PTOCA-4-030]|
| Temporary Baseline Precision | X | | 0 [PTOCA-4-031]|
| Underscore Bypass Identifiers | X | | X'01' [PTOCA-4-032]|
| Variable Space Character Code | | | Coded font dependent [PTOCA-4-033]|
| Variable Space Character Increment | X | | Coded font dependent [PTOCA-4-034]|
| Alternate Text | X | | None [PTOCA-4-035]|
| Key Information | X | | None [PTOCA-4-036]|
Key Information X None [PTOCA-4-037]
## Parameters and Parameter Values

<!-- Page 54 -->

Control Sequence
The Presentation Text object can specify that text functions are to be performed, such as underlining, margin
setting, or justification. These functions are invoked by sequences that must begin with identifiers that
distinguish them from code points. A character that delimits a string that must be processed in a different
manner may be thought of as an escape character. In the Presentation Text object, the equivalent of an escape
character is the Control Sequence Prefix. The string it delimits is a control sequence. The Presentation Text
object supports only one type of control sequence. [PTOCA-4-038]
### Control Sequence Format
A control sequence contains a Control Sequence Introducer followed by parameters. The parameter portion of
the control sequence may be from 0 to 253 bytes in length.
Introducer Parm
1
Parm
2
Parm
3
...
Parm
n
An unchained Control Sequence Introducer contains the following fields:
* A one-byte prefix, X'2B' [PTOCA-4-039]
* A one-byte class, X'D3' [PTOCA-4-040]
* A one-byte length [PTOCA-4-041]
* A one-byte function type [PTOCA-4-042]
Prefix Class Length Function Type
X'2B' X'D3'
The length field delimits the control sequence by indicating the last byte in the control sequence. The length
field specifies the count of bytes in the control sequence, starting with itself. If the value of the length field is 2,
there are no parameters in the control sequence. If the value is 3 or greater, there are parameters.
The function type uniquely identifies a control sequence, defines its syntax, and determines its semantics. The
number of parameters and the number of bytes in each parameter are implicit in the function type definition.
A chained Control Sequence Introducer contains the following fields:
* A one-byte length [PTOCA-4-043]
* A one-byte function type [PTOCA-4-044]
Length Function Type
Thus a Control Sequence Introducer is either two bytes or four bytes in length, depending on whether it is
chained or unchained. [PTOCA-4-045]
### Control Sequence Chaining
Control sequences may be chained, that is, concatenated. Chaining provides a look-ahead function that
permits a processor to avoid changes from processing control sequences to processing graphic characters
while scanning or executing Presentation Text Data. When control sequences are chained, the prefix and class
bytes are only required in the first control sequence in the chain.
Chaining is signaled by the presence of an odd function type. That is, the low-order bit is B'1'. If a control
sequence has a function type with the low-order bit equal to B'1', the string that follows the control sequence is
a chained control sequence. A chained control sequence begins with the length field, whereas an unchained
control sequence begins with the Control Sequence Prefix. The chain is terminated by a control sequence with
an even numbered function type (low-order bit is B'0'), or by the end of the current Presentation Text object. If a
Control Sequence

<!-- Page 55 -->

control sequence has a function type with the low-order bit equal to B'0', the string which follows the control
sequence may be code points or an unchained control sequence.
Note: In some environments, terminating a chain by ending the Presentation Text object might cause problems,
therefore it is recommended that generators always terminate chains with a control sequence whose
low-order bit is B'0'.
Chains of control sequences may be as long as desired. Control sequences in a chain are interpreted in the
order in which they are encountered.
Table 4 lists the control sequences that can appear within Presentation Text data and their function
types, both unchained and chained. [PTOCA-4-046]
### Modal Control Sequences
Certain control sequences are modal. That is, they establish a parameter value that persists after the control
sequence has been processed. An example is Set Inline Margin, which sets the size of the inline margin. When
such a control sequence is processed, its parameter value replaces the existing parameter value. The existing
parameter value may have been set in one of the following ways:
* A previous modal control sequence in the current Presentation Text object [PTOCA-4-047]
* Externally to the Presentation Text object [PTOCA-4-048]
* By default [PTOCA-4-049]
The new parameter value remains in effect until a subsequent control sequence for that function is
encountered or until the end of the current Presentation Text object.
Architecture Note: Note that when presentation text is processed in a MO:DCA environment where the
Presentation Text Data Descriptor (PTD) is carried in the Active Environment Group (AEG) for the page
or overlay, or when such text is processed in an IPDS environment, the Presentation Text object is
bounded by the beginning of the page and the end of the page. This is sometimes called a text major
environment. When the PTD is carried in the Object Environment Group (OEG) of a MO:DCA text object,
the text object is bounded by the Begin Presentation Text (BPT) and End Presentation Text (EPT)
structured fields. For such objects, the PTD in the AEG is ignored.
Control Sequence

<!-- Page 56 -->

Application Note: When the Begin Presentation Text (BPT) structured field is encountered in a MO:DCA data
stream, all initial text conditions specified in the Presentation Text Data Descriptor (PTD) structured field
are set prior to processing the text object. In addition, in AFP environments, whenever a BPT is
encountered, AFP presentation servers set the following default page-level initial text conditions before
the PTD initial conditions are set:
Parameter Value
Initial (I,B) Presentation Position (0,0)
Text Orientation 0°,90°
Coded Font Local ID X'FF' (default font)
Baseline Increment 6 lpi
Inline Margin 0
Intercharacter Adjustment 0
Text Color X'FFFF' (default color)
Application Note: The PTD also specifies the size of the text object space. When the PTD is specified in the
AEG of a MO:DCA page in a text major environment, the extents of the text object space are set equal
to the extents of the page. When the PTD is specified in the OEG of a MO:DCA text object, the extents
of the text object space can be set to any value in the allowed range. [PTOCA-4-050]
### Control Sequence Default Indicator
The default indicator (X'F..F') in a parameter of a control sequence causes the parameter to use the
hierarchical default value for that parameter. The hierarchical default values are listed in Table 9. [PTOCA-4-051]
### Control Sequence Introducer
The Control Sequence Introducer is a two-byte or four-byte field, depending on whether the control sequence
is unchained or chained.
Syntax: A Control Sequence Introducer can appear only at the beginning of a control sequence.
**Syntax:** A Control Sequence Introducer can appear only at the beginning of a control sequence. [PTOCA-4-052]

An unchained Control Sequence Introducer has the following format: [PTOCA-4-053]

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-054]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-055]|
| 2 | UBIN | LENGTH | 2–255 | Control sequence length | M | N | N [PTOCA-4-056]|
| 3 | CODE | TYPE | X'00' – X'FE' | Control sequence function type | M | N | N [PTOCA-4-057]|

A chained Control Sequence Introducer has the following format: [PTOCA-4-058]

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | UBIN | LENGTH | 2–255 | Control sequence length | M | N | N [PTOCA-4-059]|
| 1 | CODE | TYPE | X'00' – X'FE' | Control sequence function type | M | N | N [PTOCA-4-060]|

<!-- Page 57 -->

Semantics: A Control Sequence Introducer begins, specifies the length of, and identifies the type of a control
sequence. It suspends the processing of text and begins the processing of control sequences.
Pragmatics: A Control Sequence Introducer immediately precedes the data portion of a control sequence. [PTOCA-4-061]
### Control Sequence Prefix
The Control Sequence Prefix marks the beginning of an unchained control sequence. This parameter causes a
change in the mode of interpretation of a presentation text stream. When a Control Sequence Prefix is
encountered, the bytes immediately following are interpreted as a control sequence rather than as code points.
This mode of interpretation continues until the control sequence or control sequence chain is terminated. [PTOCA-4-062]
### Control Sequence Class
The control sequence class characterizes the syntax of the Control Sequence Introducer. This parameter
specifies how the introducer of the current control sequence should be interpreted. X'D3' has been assigned
for PTOCA. If any other class is encountered, exception condition EC-1C01 exists. The standard action is to
ignore the control sequence. [PTOCA-4-063]
### Control Sequence Length
The control sequence length specifies the length of the control sequence beginning with and including itself.
The prefix and class are not included in the length. If the length parameter, as specified for individual control
sequences, is invalid, exception condition EC-1E01 exists:
* If a mandatory parameter is missing from the control sequence, the standard action is to ignore the control [PTOCA-4-064]
sequence.
* If the control sequence is longer than specified, the standard action is to ignore only the undefined [PTOCA-4-065]
parameters.
If part of an optional parameter field is missing from the control sequence, exception condition EC-1E01 exists.
The standard action is to ignore the partially specified optional parameter field.
Control Sequence Function Type
The control sequence function type characterizes the eff ect and syntax of a control sequence.
This parameterspecifies how parameters in the control sequence are to be interpreted. The function type
identifies the operation of the control sequence; for example, to set a value or to draw a rule.
Please refer to Table 4 for a listing of PTOCA function types. These are the only valid function
types. If any other function type is encountered, exception condition EC-0001 exists. The standard action is to
ignore the control sequence and continue presenting.
Control Sequence

<!-- Page 58 -->

## Graphic Character Processing
Format: The format of the graphic characters is specified by the active coded font. The coded font is specified
by an external reference to a coded font resource by specification of a coded font local identifier. The coded
font determines the length of the code point used to specify each graphic character, and the assignment of a
code point to each graphic character. If a character is contained in the Presentation Text object that is not
defined in the active coded font, exception condition EC-2100 exists. The standard action is to present the
default character defined by the active coded font. Please see “Font Concepts” for more
information on fonts.
Presentation: Graphic character processing uses the I, B coordinate system. $I_c$
and $B_c$
represent the I and B
coordinates of the current presentation position. $I_o$
and $B_o$
represent the origin of the I, B coordinate system.
Please refer to Table 10, Figure 8, and Figure 9. Upon entry into the
Presentation Text object, $I_c$
and $B_c$
are initialized to the values specified at the data stream level. If values are
not specified by the controlling environment, these coordinates are set to their standard action values; that is, $I_c$
= $I_o$
= 0 and $B_c$
= $B_o$
= 0. See “Enter Object” in Table 10. After the object has been entered, the
values of $I_c$
and $B_c$
may be changed before the first character is presented, either by parameters in the
Presentation Text Data Descriptor or by control sequences in the Presentation Text data.
Each graphic character in a string of Presentation Text data normally causes a character shape, as defined in
the active font resource, to be placed on the presentation surface. The character's reference point coincides
with the presentation position, $I_c$
,$B_c$
, and the character baseline is made parallel to the baseline by rotation.
Simultaneously, the $I_c$
coordinate of the current presentation position is increased by the character increment
specified for that character in the active coded font. See “Present character, general case” in Table 10 on page
42.
If a character is to be placed immediately following another character on the presentation surface, the $I_c$
coordinate is first increased or decreased by the intercharacter adjustment. See “Present character, specific
cases” in Table 10. Then the character shape is placed with the character reference point
coincident with this revised presentation position. Last, the current presentation position is incremented by the
addition of the character increment value. As each graphic character is placed, the current presentation
position is moved in the positive inline direction.
The concept of between-the-pels positioning on a presentation surface is important for the presentation of
rules. For inline rules, please see Figure 10. Use one of the four diagrams, depending upon the
rule length and rule width values. When presentation is to begin for an inline rule, the physical pels are located
as shown. I and B are not directly represented in the diagrams, since the diagrams are intended to provide the
approximate location of the pels to be presented for inline rules. The I and B refer to direction only. For
example, if the I-axis is vertical and the I-direction is down as a result of an orientation change, an inline rule of
positive length and positive width would still be positioned as in diagram (b).
For baseline rules, please see Figure 11. Use one of the four diagrams, depending upon the rule
length and rule width values. When presentation is to begin for a baseline rule, the physical pels are located as
shown. I and B are not directly represented in the diagrams, since the diagrams are intended to provide the
approximate location of the pels to be presented for baseline rules. The I and B refer to direction only. For
example, if the B-axis is horizontal and the B-direction is to the left as a result of an orientation change, a
baseline rule of positive length and positive width would still be positioned as in diagram (b).
Overrun: If the intercharacter adjustment is zero, n characters, each having a fixed character increment of CI,
may be printed in a presentation space whose I-extent is n times CI. For example, if n is 100 characters and CI
is 0.1 inch, that is, 10-pitch, all 100 characters will fit on a logical page with an I-extent of 10 inches. In this
case, the 100th character just fits within the presentation space. Following placement of the 100th character,
the current inline coordinate position, $I_c$
, is one measurement unit beyond the I-extent. [PTOCA-4-066]
## Graphic Character Processing

<!-- Page 59 -->

If an attempt is made to present any portion of a character's character box or any portion of a rule beyond the I-
extent, exception condition EC-0103 exists. If the presented character is defined in terms of A-space, B-space,
and C-space, only the B-space is considered part of the character box. The standard action for this exception
condition is to refrain from presenting the character whose character box is outside the object space boundary,
and to continue processing without presenting characters. Processing continues until the presentation position
is moved to an addressable point that is valid. This exception condition exists regardless of the graphic
character causing the condition. For example, if the graphic character is a blank or a variable space character,
the exception condition exists even though no marks are made on the presentation surface.
This exception condition ceases to exist when an intercharacter adjustment causes the presented character to
move into the object space. The exception condition exists if an intercharacter adjustment causes any part of
the character box of the presented character to move out of the object space. Throughout this process the
current baseline coordinate, $B_c$
, remains unchanged.
The minimum calculation capacity for overrun handling is equal to the number of bytes that constitutes the
parameter. Thus a two-byte parameter requires a minimum of two bytes of storage.
Control sequences are processed according to their semantics without regard to the presence of an overrun.
Consider the following example. Assume that the presentation position is $I_o$
, $B_o$
, that is, the upper left corner of
the presentation space, and that the character reference point of the character to be presented is the lower left
corner of its character box. In this case, an exception condition exists even though the presentation position is
within the object space, because some of the character shape extends outside of the object space. Invoking
the standard action causes additional character increments to be applied repeatedly, and each character is
outside of the object space. In effect, each character received is rejected until a control sequence is received
that moves the baseline away from the I-axis, such as Begin Line or Absolute Move Baseline.
Superscripting and Subscripting: Superscripts and subscripts are graphic characters that appear above or
below adjacent graphic characters on the same line, and may be smaller than adjacent graphic characters on
the same line. They have special purposes, such as representing exponents or footnote numbers.
Superscripts and subscripts may be implemented in different ways.
* A font may contain smaller graphic characters which are designed to appear as superscripts or subscripts. [PTOCA-4-067]
These characters may be designed with their character base above, on, or below the nominal baseline. With
a font like this, superscripting or subscripting is implicit in presenting one of these graphic characters.
* A font may consist entirely of graphic characters which are designed so that they will appear as superscripts [PTOCA-4-068]
or subscripts when used with other fonts. With a font like this, superscripting or subscripting is implicit in
invoking the font.
* A font may be designed without smaller characters for use as superscripts and subscripts. With a font like [PTOCA-4-069]
this, other means must be used to create superscripts and subscripts.
– Superscripting or subscripting can be accomplished by temporarily lowering or raising the B-axis
coordinate of the presentation position. With this technique, the size of the superscript or subscript graphic
characters is the same as the immediately preceding graphic characters. This technique is useful for
typescript, and the B-axis coordinate is usually lowered or raised ½ line.
– Superscripting or subscripting can be accomplished by temporarily lowering or raising the B-axis
coordinate of the presentation position and invoking a different font, whose graphic characters are smaller
than the immediately preceding graphic characters. Such smaller graphic characters are usually in a
diffe rent style specifically designed for superscripting or subscripting. This technique is useful in formal
letters and compositions. The distance that the B-axis coordinate is lowered or raised depends on the
purpose of the superscript or subscript. For example, a limit to an integral is displaced further than an
exponent.
This last technique is the most general, since it can apply to a variety of requirements, including the following:
* Nested superscripts and subscripts, that is, superscripts and subscripts of superscripts or subscripts [PTOCA-4-070]
* Mathematical symbols of different sizes, for example, integrals, sums, products, and exponents [PTOCA-4-071]
## Graphic Character Processing

<!-- Page 60 -->

* Specially stylized superscripts or subscripts, such as italic characters and Greek letters [PTOCA-4-072]
In the context of superscripting and subscripting, the established baseline is the baseline on which a string of
graphic characters appears to rest, the temporary baseline is the baseline on which a superscript or subscript
appears to rest, and the current baseline is the baseline on which the next graphic character will appear to rest.
The current baseline may be the established baseline or the temporary baseline.
In PTOCA superscripting and subscripting, including the establishment of a temporary baseline, is specified by
the Temporary Baseline Move control sequence.
**Table 10. Equations for Graphic Character Presentation** [PTOCA-4-073]

| WHEN | WHAT | EQUATIONS |
|:--- |:--- |:--- |
| **Enter Object** | Use initial values from data stream | $I_c$ = $I_h$, $B_c$ = $B_h$ [PTOCA-4-074]|
| | Or use default initial values | $I_c$ = $I_o$ = 0, $B_c$ = $B_o$ = 0 [PTOCA-4-075]|
| **Present character, general case** | $B_c$ = $B_o$ = 0 [PTOCA-4-076]| |
| | Present variable space character | $I_{cnew}$ = $I_c$ + VSI [PTOCA-4-077]|
| | Present graphic character | $I_{cnew}$ = $I_c$ + CI [PTOCA-4-078]|
| **Present character, specific cases** | **Following incrementing character:** [PTOCA-4-079]| |
| | Present first character (incrementing) | $I_{cnew}$ = $I_c$ +/- ADJSTMNT [PTOCA-4-080]|
| | Followed by second character (incrementing) | Present first character (incrementing): $I_{cnew}$ = $I_c$ + CI [PTOCA-4-081]|
| | | $I_{cnew}$ = $I_c$ +/- ADJSTMNT [PTOCA-4-082]|
| | | Present second character (incrementing): $I_{cnew}$ = $I_c$ + CI [PTOCA-4-083]|
| | **Following incrementing character:** [PTOCA-4-084]| |
| | Present first character (non-incrementing) | $I_{cnew}$ = $I_c$ +/- ADJSTMNT [PTOCA-4-085]|
| | Present second character (incrementing) | Present first character (non-incrementing): $I_{cnew}$ = $I_c$ [PTOCA-4-086]|
| | | Present second character (incrementing): $I_{cnew}$ = $I_c$ + CI [PTOCA-4-087]|
| | **Following incrementing character:** [PTOCA-4-088]| |
| | Present Variable Space Character | $I_{cnew}$ = $I_c$ + VSI [PTOCA-4-089]|
| | Followed by first character (incrementing) | Present first character (incrementing): $I_{cnew}$ = $I_c$ + CI [PTOCA-4-090]|

This table shows how the $I_{cnew}$ coordinate is modified during the presentation of characters. [PTOCA-4-091]
## Graphic Character Processing

<!-- Page 61 -->

Figure 8. Presentation Position without Intercharacter Adjustment
Figure 9. Presentation Position with Intercharacter Adjustment [PTOCA-4-092]
## Graphic Character Processing

<!-- Page 62 -->

Figure 10. Between-the-Pels Illustrations for Inline Rules
Figure 11. Between-the-Pels Illustrations for Baseline Rules [PTOCA-4-093]
## Graphic Character Processing

<!-- Page 63 -->

Exception Conditions: Control sequence processing and graphic character processing can cause the
following exception conditions:
* EC-1E01...A mandatory parameter is missing. [PTOCA-4-094]
* EC-1C01...The control sequence class is invalid. [PTOCA-4-095]
* EC-1E01...The control sequence length is not valid. [PTOCA-4-096]
* EC-1E01...An optional parameter is partially missing. [PTOCA-4-097]
* EC-0001...The control sequence function type is invalid. [PTOCA-4-098]
* EC-2100...The Presentation Text object contains a graphic character code point that is not defined in the [PTOCA-4-099]
active coded font.
* EC-0103...An attempt is made to present a character or a rule outside of the object space. [PTOCA-4-100]
## Graphic Character Processing

<!-- Page 64 -->

## Presentation Text Data
Presentation Text data consists of character code points and embedded control sequences, which together are
called presentation text. The architected content of the presentation text depends on the subset selected by
the generator of the object.
Syntax: Please see the appendixes for environmental information about the syntax of Presentation Text data.
Semantics: Presentation Text data inherits any modal parameter values, such as inline margin and coded font,
that were specified externally to the Presentation Text object. It also inherits the current presentation position.
These values may be specified by the controlling environment.
The content of Presentation Text data is graphic character code points and control sequences. For the syntax,
semantics, and pragmatics of the control sequences, see “Control Sequence Detailed Descriptions” on page
47.
Pragmatics: Presentation text can consist of almost any string of eight-bit bytes. In the single-byte mode, these
bytes may be seven-bit code points, with the leading bit zero, or eight-bit code points. In the double-byte mode,
they may be sixteen-bit code points. The coded character representation is determined through reference to a
coded font in the controlling environment. All code points in the presentation text are translated for
presentation by reference to the active coded font, with the following exceptions:
* The V ariable Space Character, which is normally X'40' for EBCDIC single-byte fonts, X'20' for ASCII single- [PTOCA-4-101]
byte fonts, X'4040' for EBCDIC double-byte fonts, X'2020' for ASCII double-byte fonts, and X'0020' or
X'00A0' for Unicode fonts;
* The Control Sequence Prefix, which is X'2B'. [PTOCA-4-102]
If it is necessary to present the Control Sequence Prefix code point, use the Transparent Data control
sequence.
All control sequence displacements are expressed in terms of the I, B coordinate system. The directions of the
I, B coordinates are specified initially in the text orientation initial conditions in the Presentation Text Data
Descriptor. They can be respecified within a Presentation Text object with a Set Text Orientation control
sequence.
When processing begins for the first Presentation Text Data in a given Presentation Text object, the current
presentation position, $I_c$
and $B_c$
, is set using values from the Presentation Text Data Descriptor. The initial inline
coordinate value and initial baseline coordinate value are used. When processing begins for subsequent
Presentation Text data within the same Presentation Text object, the current presentation position is set to the
last presentation position from the previous Presentation Text data.
Each graphic character code point in a Presentation Text object causes the character reference point of the
character shape to be placed at $I_c$
, $B_c$
with the character baseline parallel to the baseline. $I_c$
is increased by the
character increment and, for a character immediately followed by another character, is adjusted by the
intercharacter adjustment.
In addition to graphic character code points, Presentation Text data can contain embedded control sequences.
These are strings of two or more bytes which signal an alternate mode of processing for the content of the
current Presentation Text data. Although PTOCA allows the definition of various types of control sequences,
only one type of control sequence is permitted in the Presentation Text data. The escape character to be used
in the Control Sequence Introducer is X'2B'.
As previously stated, control sequences can be chained. However, there is no requirement that control
sequences be chained. [PTOCA-4-103]
## Presentation Text Data

<!-- Page 65 -->

## Control Sequence Detailed Descriptions
The following pages contain detailed descriptions of the PTOCA control sequences. The descriptions show the
syntax, semantics, and pragmatics of the control sequences.
Documentation Conventions: Each PTOCA control sequence is described syntactically in this reference by a
table. Following each table is semantic information related to each component of the control sequence.
Syntactically descriptive material following the table may indicate that additional restrictions apply to the control
sequence defined by the table. Each syntax table has the following columns:
* Offset refers to the position of a parameter. [PTOCA-4-104]
* Type denotes the syntax of the parameter by data type. Please see “Parameter Data Types” for [PTOCA-4-105]
more information.
* Name is a short field name suitable for coding. [PTOCA-4-106]
* Range denotes the smallest and largest valid values that may occur in this field. Negative numbers are [PTOCA-4-107]
expressed in twos-complement form.
* Meaning gives an explanatory or descriptive name for the parameter. [PTOCA-4-108]
* M/O refers to the parameter's appearance in the control sequence. O means that the parameter is optional. [PTOCA-4-109]
That is, the generator of the Presentation Text object does not have to include this parameter. However, the
receiver must support this parameter if it supports the control sequence that contains the parameter. M
means that the parameter's appearance is mandatory. If a particular control sequence occurs in the object,
all mandatory parameters in that control sequence must be present.
If a mandatory parameter is missing, exception condition EC-1E01 exists. The standard action is to ignore
the control sequence to which the missing parameter belongs.
* Def refers to the existence of a PTOC A default value for the parameter which is to be used when no explicit [PTOCA-4-110]
value is provided and the standard action is to continue. Y means that there is such a value. N means that
there is no such value. This value, also called the standard action value, is defined in the description which
follows each syntax table.
* Ind specifies the validity of the default indicator. Y means that the default indicator is valid. N means that the [PTOCA-4-111]
default indicator is not valid. The default indicator is represented by X'F..F' and is described in “Control
Sequence Default Indicator”.
Reserved Fields Certain fields may be denoted as reserved. A reserved field is a parameter that has no
functional definition at the current time, but may have at some time in the future. All bytes comprising a field
defined to be reserved should be given a value of zero by generating applications. Receiving applications
should ignore any value in a reserved field.
Interpreting Ranges The range values shown in the syntax tables assume a measurement unit of 1/1440 inch.
That is, they assume that the measurement base is ten inches, and that the $X_p$
-units per unit base and $Y_p$
-units
per unit base are 14,400. If this assumption is not correct, and a different measurement unit is supported, the
correct range values can be determined by using the following steps:
1. Calculate the number of actual supported units per inch (X) as follows: [PTOCA-4-112]
* If the measurement base is ten inches, divide the number of supported units per ten inches by 10. [PTOCA-4-113]
* If the measurement base is ten centimeters, multiply the number of supported units per ten centimeters [PTOCA-4-114]
by 0.254.
2. Calculate the ratio of actual supported units per inch (X) to the assumed 1,440 units per inch as follows: [PTOCA-4-115]
* Divide (X) by 1,440, yielding the ratio (Y). [PTOCA-4-116]
3. Calculate the new range value in the supported measurement units as follows: [PTOCA-4-117]
* Convert the old range value to base ten; then multiply it by the ratio (Y). [PTOCA-4-118]
* Round to the nearest integer. [PTOCA-4-119]
## Control Sequence Detailed Descriptions

<!-- Page 66 -->

For example, suppose that the specified range is X'8000'–X'7FFF' when using 14,400 units per 10 inches. The
equivalent range at a unit of measure of 1/240 of an inch is calculated as follows:
1. Supported units per inch = 2,400 ÷ 10 = 240 [PTOCA-4-120]
2. Ratio of supported units per inch to 1,440 units per inch = 240 ÷ 1,440 = 1/6 [PTOCA-4-121]
3. Range at 2,400 units per 10 inches: [PTOCA-4-122]
a. X'8000' = -32,768 (converted to base 10)
-32,768 × 1/6 = -5,461.3333
b. X'7FFF' = 32,767(converted to base 10)
32,767 × 1/6 = 5,461.1667
Therefore, the equivalent range at 2,400 units per 10 inches is -5,461 to 5,461 which in hexadecimal is
X'EAAB' to X'1555'.
## Control Sequence Detailed Descriptions

<!-- Page 67 -->

## Absolute Move Baseline (AMB)
The Absolute Move Baseline control sequence moves the baseline coordinate relative to the I-axis. [PTOCA-4-123]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-124]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-125]|
| 2 | UBIN | LENGTH | 4 | Control sequence length | M | N | N [PTOCA-4-126]|
| 3 | CODE | TYPE | X'D2' – X'D3' | Control sequence function type | M | N | N [PTOCA-4-127]|
| 4–5 | SBIN | DSPLCMNT | X'0000' – X'7FFF' | Displacement | M | N | N [PTOCA-4-128]|
DSPLCMNT is a signed binary number expressed in measurement units. It does not accept the default
indicator. The range for this parameter assumes a measurement unit of 1/1440 inch. If it is necessary to
convert to a different measurement unit, please see the conversion routine described in “Interpreting Ranges”
on.
### Semantics
This control sequence specifies a displacement, DSPLCMNT, in the B-direction from the I-axis of the object
space to a new baseline coordinate position. After execution of this control sequence, presentation is resumed
at the new baseline coordinate position. This control sequence does not modify the current inline coordinate
position.
$I_{cnew}$ = $I_c$
$B_{cnew}$ = $B_o$
+ DSPLCMNT
If the value of DSPLCMNT is not supported or is not within the range specified by PTOC A, exception condition
EC-1301 exists. The standard action in this case is to continue presentation according to the description given
in the Pragmatics section.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-129]
### Pragmatics
If DSPLCMNT is zero, the addressable position is the I-axis, and any intercharacter adjustment is not applied.
If a move is to a presentation position for which the character box will exceed the object space and an attempt
is made to present there, exception condition EC-0103 exists. The standard action in this case is to refrain
from presenting the character that exceeds the object space, and to continue processing without presenting
characters until the presentation position occupies a valid addressable position for the character being
presented. Then presentation of characters may resume. PTOCA does not constrain the advancement of the
baseline coordinate position toward the I-axis. However, a constraint of this type may be imposed by the
subset level or by the receiver. If this constraint is applied and DSPLCMNT is negative, exception condition
EC-1403 exists. The standard action in this case is to ignore the control sequence.
AMB Control Sequence

<!-- Page 68 -->

### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1301...The value of DSPLCMNT is not supported or is not within the range specified by PTOCA. [PTOCA-4-130]
* EC-0103...The presentation position is outside the object space and presentation is attempted. [PTOCA-4-131]
* EC-1403...Negative DSPLCMNT is not valid. [PTOCA-4-132]
AMB Control Sequence

<!-- Page 69 -->

## Absolute Move Inline (AMI)
The Absolute Move Inline control sequence moves the inline coordinate position relative to the B-axis. [PTOCA-4-133]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control sequence prefix | M | N | N [PTOCA-4-134]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-135]|
| 2 | UBIN | LENGTH | 4 | Control sequence length | M | N | N [PTOCA-4-136]|
| 3 | CODE | TYPE | X'C6' – X'C7' | Control sequence function type | M | N | N [PTOCA-4-137]|
| 4–5 | SBIN | DSPLCMNT | X'0000' – X'7FFF' | Displacement | M | N | N [PTOCA-4-138]|
DSPLCMNT is a signed binary number expressed in measurement units. It does not accept the default
indicator. The range for this parameter assumes a measurement unit of 1/1440 inch. If it is necessary to
convert to a different measurement unit, please see the conversion routine described in “Interpreting Ranges”
on.
### Semantics
This control sequence specifies a displacement, DSPLCMNT, in the I-direction from the B-axis of the object
space to a new inline coordinate position, and resumes presentation at the new inline coordinate position. It
does not modify the current baseline coordinate position.
$I_{cnew}$ = $I_o$
+ DSPLCMNT
$B_{cnew}$ = $B_c$
If the value of DSPLCMNT is not supported or is not within the range specified by PTOC A, exception condition
EC-1401 exists. The standard action in this case is to continue presentation according to the description given
in the Pragmatics section.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-139]
### Pragmatics
If the value of DSPLCMNT is zero, the addressable position is at the B-axis, and any intercharacter adjustment
is not applied. If a move is to a presentation position for which the character's character box will exceed the
object space and an attempt is made to present there, exception condition EC-0103 exists. The standard
action in this case is to refrain from presenting the character that exceeds the object space, and to continue
processing without presenting characters until the presentation position occupies a valid addressable position
for the character being presented. Then presentation of characters may resume. [PTOCA-4-140]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1401...The value of DSPLCMNT is not supported or is not within the range specified by PTOCA. [PTOCA-4-141]
AMI Control Sequence

<!-- Page 70 -->

* EC-0103...The presentation position is outside the object space and presentation is attempted. [PTOCA-4-142]
AMI Control Sequence

<!-- Page 71 -->

## Begin Line (BLN)
The Begin Line control sequence begins a new line. [PTOCA-4-143]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control sequence prefix | M | N | N [PTOCA-4-144]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-145]|
| 2 | UBIN | LENGTH | 2 | Control sequence length | M | N | N [PTOCA-4-146]|
| 3 | CODE | TYPE | X'D8' – X'D9' | Control sequence function type | M | N | N [PTOCA-4-147]|
### Semantics
This control sequence marks the beginning of a new line. It increments the current baseline coordinate position
by the amount of the baseline increment, INCRMENT. It sets the current inline coordinate to the inline margin.
Presentation is resumed at the new baseline coordinate position at the inline margin.
$I_{cnew}$ = $I_{margin}$
$B_{cnew}$ = $B_c$
+ INCRMENT
Exception Condition
This control sequence can cause the following exception condition:
* None
BLN Control Sequence

<!-- Page 72 -->

## Begin Suppression (BSU)
The Begin Suppression control sequence marks the beginning of a string of presentation text that may be
suppressed from the visible output. [PTOCA-4-148]
#### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-149]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-150]|
| 2 | UBIN | LENGTH | 3 | Control sequence length | M | N | N [PTOCA-4-151]|
| 3 | CODE | TYPE | X'F2' – X'F3' | Control sequence function type | M | N | N [PTOCA-4-152]|
| 4 | CODE | LID | X'00' – X'FF' | Suppression identifier | M | N | N [PTOCA-4-153]|
This control sequence marks the beginning of a string of presentation text that may be suppressed from the
visible output. It is activated by a local identifier, LID. This control sequence works in conjunction with the End
Suppression control sequence, which also contains a LID. Please see “End Suppression (ESU)”. If
the LID in this control sequence has been activated for the current Presentation Text object in the data stream
hierarchy, the string of presentation text between this control sequence and the next End Suppression control
sequence with the same LID does not appear in the visible output. Even though the text does not appear, all
control sequences within the suppressed field are executed, and the I-coordinate and B-coordinate are
updated as if the text had appeared. Only the actual presentation of the graphic characters and rules is
suppressed. Please see Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS
Environment”, for further information about suppression.
If the value of the LID is not supported or is not within the range specified by PTOCA, exception condition EC-
9801 exists. The standard action in this case is to ignore the control sequence. Please see the Pragmatics [PTOCA-4-154]
section for additional exception conditions.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation.
If the LID in this control sequence is not activated in the data stream hierarchy, this control sequence and the
corresponding End Suppression control sequence are processed as no-operations. [PTOCA-4-155]
### Pragmatics
Several kinds of exception conditions can exist with the Begin and End Suppression control sequences. These
exception conditions can exist whether or not the LID has been activated in the data-stream hierarchy.
* Nesting of Begin and End Suppression control sequences is not allowed. If a second Begin Suppression [PTOCA-4-156]
control sequence follows a Begin Suppression control sequence without an intervening and corresponding
End Suppression control sequence, exception condition EC-0601 exists. The standard action in this case is
to ignore the second Begin Suppression control sequence.
BSU Control Sequence

<!-- Page 73 -->

* If a Begin Suppression control sequence is followed by an End Suppression control sequence that has a [PTOCA-4-157]
diffe rent value for the LID, exception condition EC-0201 exists. The standard action in this case is to ignore
the End Suppression control sequence.
* If an End Suppression control sequence occurs in a Presentation Text object without a previous valid Begin [PTOCA-4-158]
Suppression control sequence, exception condition EC-0201 exists. The standard action is to ignore the End
Suppression control sequence.
* If a Begin Suppression control sequence appears in a Presentation Text object with no corresponding End [PTOCA-4-159]
Suppression control sequence, exception condition EC-0401 exists. The standard action in this case is to
process the object as if the corresponding End Suppression control sequence were present at the end of the
object. That is, all of the data following the Begin Suppression control sequence is suppressed. [PTOCA-4-160]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9801...The value of the LID is not supported or is not in the range specified by PTOCA. [PTOCA-4-161]
* EC-0601...Nesting exists. [PTOCA-4-162]
* EC-0201...The values of the LIDs do not match within a BSU, ESU pair. [PTOCA-4-163]
* EC-0201...An ESU control sequence occurs without a preceding BSU control sequence. [PTOCA-4-164]
* EC-0401...A BSU control sequence occurs without a succeeding ESU control sequence. [PTOCA-4-165]
BSU Control Sequence

<!-- Page 74 -->

## Draw B-axis Rule (DBR)
The Draw B-axis Rule control sequence draws a rule in the B-direction. [PTOCA-4-166]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-167]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-168]|
| 2 | UBIN | LENGTH | 4, 7 | Control sequence length | M | N | N [PTOCA-4-169]|
| 3 | CODE | TYPE | X'E6' – X'E7' | Control sequence function type | M | N | N [PTOCA-4-170]|
| 4–5 | SBIN | RLENGTH | X'8000' – X'7FFF' | Length | M | N | N [PTOCA-4-171]|
| 6–8 | SBIN | RWIDTH | See Semantics section | Width | O | Y | Y [PTOCA-4-172]|
a measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the
conversion routine described in “Interpreting Ranges”. [PTOCA-4-173]
### Semantics
This control sequence specifies the dimensions of a rule that extends from the current presentation position in
both the B-direction and the I-direction. The current I-axis and B-axis coordinates are not changed by this
control sequence.
The length parameter, RLENGTH, is the length of the rule in the B-direction. If RLENGTH is omitted, exception
condition EC-1E01 exists. The standard action is to treat this control sequence as a no-operation. If the value
of RLENGTH is not supported or is not within the range specified by PTOCA, exception condition EC-8202
exists. The standard action is to ignore the control sequence and continue presentation with the value
determined according to the description given in the Pragmatics section.
The width parameter, RWIDTH, is the width of rule in the I-direction. RWIDTH consists of a three-byte two-part
binary number of the form AB. Both A and B are in measurement units.
* A is a two-byte binary number from -32,768 through +32,767 [PTOCA-4-174]
* B is a one-byte binary fraction with bit 0 denoting 1/2 measurement unit, bit 1 denoting 1/4 measurement [PTOCA-4-175]
unit, and bit N denoting 1/(2
(N+1)
) measurement unit.
If RWI DTH is the default indicator, a value is obtained from the hierarchy. Please see the Pragmatics section
for further information. If the value of RWIDTH is not supported or is not within the range specified by PTOCA,
exception condition EC-8002 exists. The standard action is to ignore the control sequence and continue
presentation with the value determined according to the description given in the Pragmatics section.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation.
DBR Control Sequence

<!-- Page 75 -->

### Pragmatics
If a width or length is specified that, when converted to pels, requires finer resolution than a device supports,
the device uses the next smaller width or length that it does support. If a specified width or length becomes
less than the finest device resolution, the device uses its finest resolution. If the value of RLENGTH or
RWIDTH is zero, no marks appear. Such resolution correction does not constitute an exception condition. If a
parameter value will cause any part of the rule to extend outside of the object space, exception condition code
EC-0103 exists. The standard actions in this case are the following:
* For extensions in the B-direction, truncate the rule at the object space boundary. Receivers using character [PTOCA-4-176]
underscore to create rules must position, begin, or end characters in such a way as to prevent extension
beyond the limits of the object space.
* For extensions in the I-direction, limit presentation to the portion of the rule that can be presented within the [PTOCA-4-177]
object space. If the receiver's minimum resolution will cause the object space to be exceeded, do not present
the rule.
A negative value for RLENGTH or RWIDTH reverses the direction of the corresponding extent, allowing
definition of a rule in four directions from a given presentation position. The support of negative values in the I-
direction and the B-direction is optional.
A rule of length +1 must have a diffe rent starting point from a rule of -1. The dif ference, within the tolerances of
the receiver, is equal to the receiver's finest resolution. If a parameter value is received by a receiver that does
not support it, exception condition EC-8202 exists. The standard action in this case is to ignore the control
sequence. The recommended default value for RWIDTH is receiver-dependent. For example, it may be the
width of the vertical bar character in the active font. A receiver incapable of drawing the rule may substitute a
graphic sequence that represents it.
For further information on rule positioning, please refer to Figure 11. [PTOCA-4-178]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-0103...A parameter value will cause the rule to be outside the object space. [PTOCA-4-179]
* EC-1E01...RLENGTH is missing. [PTOCA-4-180]
* EC-8002...The value for RWIDTH is not supported or is not in the range specified by PTOCA. [PTOCA-4-181]
* EC-8202...The value for RLENGTH is not supported or is not in the range specified by PTOCA; or, a [PTOCA-4-182]
parameter value is negative and the receiver cannot process it.
DBR Control Sequence

<!-- Page 76 -->

## Draw I-axis Rule (DIR)
The Draw I-axis Rule control sequence draws a rule in the I-direction. [PTOCA-4-183]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-184]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-185]|
| 2 | UBIN | LENGTH | 4, 7 | Control sequence length | M | N | N [PTOCA-4-186]|
| 3 | CODE | TYPE | X'E4' – X'E5' | Control sequence function type | M | N | N [PTOCA-4-187]|
| 4–5 | SBIN | RLENGTH | X'8000' – X'7FFF' | Length | M | N | N [PTOCA-4-188]|
| 6–8 | SBIN | RWIDTH | See Semantics section | Width | O | Y | Y [PTOCA-4-189]|
a measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the
conversion routine described in “Interpreting Ranges”. [PTOCA-4-190]
### Semantics
This control sequence specifies dimensions of a rule that extends from the current presentation position in both
the I-direction and the B-direction. The current I-axis and B-axis coordinates are not changed by this control
sequence.
The length parameter, RLENGTH, is the length of the rule in the I-direction. If RLENGTH is omitted, exception
condition EC-1E01 exists. The standard action is to treat this control sequence as a no-operation. If the value
of RLENGTH is not supported or is not within the range specified by PTOCA, exception condition EC-8202
exists. The standard action is to continue presentation with the value determined according to the description
given in the Pragmatics section.
The width parameter, RWIDTH, is the width of the rule in the B-direction.
RWIDTH consists of a three-byte two-part binary number of the form AB. Both A and B are in measurement
units.
* A is a two-byte binary number from -32,768 through +32,767 [PTOCA-4-191]
* B is a one-byte binary fraction with bit 0 denoting 1/2 measurement unit, bit 1 denoting 1/4 measurement [PTOCA-4-192]
unit, and bit N denoting 1/(2
(N+1)
) measurement unit.
If RWI DTH is the default indicator, a value is obtained from the hierarchy. See the Pragmatics section for
further information. If the value of RWIDTH is not supported or is not within the range specified by PTOCA,
exception condition EC-8002 exists. The standard action is to continue presentation with the value determined
according to the description given in the Pragmatics section.
DIR Control Sequence

<!-- Page 77 -->

The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-193]
### Pragmatics
If a width or length is specified that, when converted to pels, requires finer resolution than a device supports,
the device uses the next smaller width or length that it does support. If a specified width or length becomes
less than the finest device resolution, the device uses its finest resolution. If the value of RLENGTH or
RWIDTH is zero, no marks appear. Such resolution correction does not constitute an exception condition. If a
parameter value will cause any part of the rule to extend outside of the object space, exception condition EC-
0103 exists. The standard actions in this case are the following: [PTOCA-4-194]
* For extensions in the I-direction, truncate the rule at the object space boundary. Receivers using character [PTOCA-4-195]
underscore to create rules must position, begin, or end characters in such a way as to prevent extension
beyond the limits of the object space.
* For extensions in the B-direction, presentation is limited to the portion of the rule that can be presented within [PTOCA-4-196]
the object space. If the receiver's minimum resolution will cause the object space to be exceeded, do not
present the rule.
A negative value of RLENGTH or RWID TH reverses the direction of the corresponding extent, allowing
definition of a rule in four directions from a given presentation position. The support of negative values in the I-
direction and B-direction is optional.
A rule of length +1 must have a diffe rent starting point from a rule of -1. The dif ference, within the tolerances of
the receiver, is equal to the receiver's finest resolution. If a parameter value is received by a receiver that does
not support it, exception condition EC-8202 exists. The standard action in this case is to ignore the control
sequence. The recommended default value for RWIDTH is receiver-dependent. For example, it may be the
width of the underscore character in the active font. A receiver incapable of drawing the rule may substitute a
graphic sequence that represents it.
For further information on rule positioning, please refer to Figure 10. [PTOCA-4-197]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-0103...A parameter value will cause the rule to be outside the object space. [PTOCA-4-198]
* EC-1E01...RLENGTH is missing. [PTOCA-4-199]
* EC-8002...The value for RWIDTH is not supported or is not in the range specified by PTOCA. [PTOCA-4-200]
* EC-8202...The value for RLENGTH is not supported or is not in the range specified by PTOCA; or, a [PTOCA-4-201]
parameter value is negative and the receiver cannot process it.
DIR Control Sequence

<!-- Page 78 -->

## Encrypted Data (ENC)
The Encrypted Data control sequence contains a sequence of bytes that are encrypted and must be decrypted
into text strings for standard text processing. This data is not scanned for embedded control sequences. [PTOCA-4-202]
#### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-203]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-204]|
| 2 | UBIN | LENGTH | 7–255 | Control sequence length | M | N | N [PTOCA-4-205]|
| 3 | CODE | TYPE | X'98' – X'99' | Control sequence function type | M | N | N [PTOCA-4-206]|
| 4-7 | | | | Reserved; should be zero | M | N | N [PTOCA-4-207]|
| 8-256 | UNDF | ENCDATA | Not applicable | Encrypted bytes to be decrypted | M | N | N [PTOCA-4-208]|
The data type of the contents of ENCDATA is UNDF (undefined). ENCDATA does not accept the default
indicator, but X'F....F' is valid. [PTOCA-4-209]
### Semantics
This control sequence specifies a sequence of undefined encrypted bytes that must be decrypted. Once these
bytes have been decrypted using the decryption mechanism, the result is a character string whose encoding
scheme is set by the currently active font. Once decrypted, the standard rules for processing character strings
apply. If the ENCDATA cannot be decrypted, then the alternate text is used from the Set Encrypted Alternate
(SEA) control and the standard rules for processing character strings also apply. No code point within the data
field is recognized as a Control Sequence Prefix. The current inline position is incremented for each graphic
character in the string.
For graphic characters following each other:
$I_{cnew}$ = $I_c$
+ ADJSTMNT + CI
Intervening non-incrementing characters are ignored.
For graphic characters following RMI, AMI, BLN control sequences or following a space character or variable
space character:
$I_{cnew}$ = $I_c$
+ CI
Intervening non-incrementing characters are ignored.
For the variable space character:
$I_{cnew}$ = $I_c$
+ VSI
For a non-incrementing character:
$I_{cnew}$ = $I_c$
In all cases:
$B_{cnew}$ = $B_c$
ENC Control Sequence

<!-- Page 79 -->

### Pragmatics
If the value of the LENGTH field is less than 7, exception condition EC-1E01 exists. The standard action is to
ignore the control sequence and continue processing.
When considering the exception conditions that can occur with an ENC, it is important to note that there are
three cases:
1. The ENCDATA can be decrypted into printable code points [PTOCA-4-210]
2. The ENCDATA cannot be decrypted [PTOCA-4-211]
3. The ENCDATA cannot be decrypted and alternate text is used [PTOCA-4-212]
What follows are the considerations for each case.
The ENCDATA can be decrypted:
Once ENCDATA has been decrypted, no absolute move, relative move, baseline positioning, or other
immediate or modal function is available. If code points representing control sequences are processed within
ENCDATA, they are presented as graphic characters, and the active coded font determines whether any
character shapes appear on the presentation surface. If an Encrypted Data control sequence causes any
part of the character box of the character to exceed the object space, exception condition EC-0103 exists.
The standard action is not to present the character that exceeds the object space, and continue processing
without presenting characters until the presentation position is returned to an addressable position within the
object space that is a valid presentation position for the character being presented. Then presentation of
characters may resume. Note that when decrypted data causes EC-0103, alternate text will not be used.
Given that the length of the encrypted bytes to be decrypted can exceed the space available within a single
ENC control, a method to specify more than 249 bytes for encrypted bytes to be decrypted is provided. ENC
controls that are consecutive and part of the same control sequence chain have their ENCDATA fields
concatenated together to form encrypted bytes that can be much longer than 249 bytes. Consecutive, in this
case, means the ENC controls have no intervening PTOCA controls between them.
The data length after decryption must be an even number for double-byte fonts. If the length of the decrypted
character string is an odd number when a double-byte font is active, exception condition EC-1A01 exists.
The standard action is to process the decrypted character string up to the last byte, skip the odd byte, and
continue processing. If alternate text has been set using the SEA control, it will not be used.
If the character encoding is Unicode but the code points in the decrypted character string are not valid
Unicode data, exception condition EC-1A03 exists. The standard action is to skip the remainder of the code
points in the ENC and continue processing. If alternate text has been set using the SEA control, it will not be
used.
The ENCDATA cannot be decrypted:
If decryption is not available, exception condition EC-9D01 exists. If alternate text has been set using the
SEA control, then the standard action is to substitute the alternate text for the text string originally intended to
be printed. If no alternate text has been set using the SEA control, then the standard action is to ignore the
control sequence and continue processing.
Architecture Note: Some IPDS printers, under control of an environment-specific text fidelity control, will
allow reporting and continuation to be controlled when an ENC is encountered by a printer that cannot
decrypt text strings.
If decryption fails on the receiver, exception condition EC-9D02 exists. If alternate text has been set using the
SEA control, then the standard action is to substitute the alternate text for the text string originally intended to
be printed. If no alternate text has been set using the SEA control, then the standard action is to ignore the
control sequence and continue processing.
If no key information has been set for decryption using the SKI control, exception condition EC-9D03 exists.
If alternate text has been set using the SEA control, then the standard action is to substitute the alternate text
ENC Control Sequence

<!-- Page 80 -->

for the text string originally intended to be printed. If no alternate text has been set using the SEA control,
then the standard action is to ignore the control sequence and continue processing.
Application Note: It is recommended that a positioning control sequence (such as an AMI) follow each ENC
(or group of ENCs in the case where the ENCDATA is concatenated together) to make sure that any
text that follows the ENCDATA is positioned correctly (should the byte data fail to be decrypted).
The ENCDATA cannot be decrypted and alternate text is used:
When alternate text is used, no absolute move, relative move, baseline positioning, or other immediate or
modal function is available. If code points representing control sequences are processed within ALTTEXT,
they are presented as graphic characters, and the active coded font determines whether any character
shapes appear on the presentation surface. If the alternate text causes any part of the character box of the
character to exceed the object space, exception condition EC-0103 exists. The standard action is not to
present the character that exceeds the object space, and continue processing without presenting characters
until the presentation position is returned to an addressable position within the object space that is a valid
presentation position for the character being presented. Then presentation of characters may resume.
The data length of the alternate text must be an even number for double-byte fonts. If the length of the
alternate text is an odd number when a double-byte font is active, exception condition EC-1A01 exists. The
standard action is to process the alternate text up to the last byte, skip the odd byte, and continue
processing.
If the character encoding in the alternate text is Unicode but the code points in the alternate text character
string are not valid Unicode data, exception condition EC-1A03 exists. The standard action is to skip the
remainder of the code points in the alternate text and continue processing. [PTOCA-4-213]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-0103...The decrypted character string or alternate text will cause part of a character's character box to be [PTOCA-4-214]
outside the object space.
* EC-1A01...The length of the decrypted character string or alternate text is an odd number, but a double-byte [PTOCA-4-215]
font is active.
* EC-1A03...Invalid Unicode data in the decrypted character string or alternate text. This can be caused by [PTOCA-4-216]
one of the following:
– A high-order surrogate code value was not immediately followed by a low-order surrogate code value. The
high-order surrogate range is U+D800 through U+DBFF.
– A low-order surrogate code value was not immediately preceded by a high-order surrogate code value.
The low-order surrogate range is U+DC00 through U+DFFF.
– An illegal UTF-8 byte sequence, as defined in the Unicode 3.2 Specification, was specified. For more
information on illegal UTF-8 byte sequences, see Table 15.
* EC-1E01...LENGTH is not valid. [PTOCA-4-217]
* EC-9D01...Decryption is not available on this device. [PTOCA-4-218]
* EC-9D02...Decryption reported an error. [PTOCA-4-219]
* EC-9D03...No key information has been set for decryption. [PTOCA-4-220]
ENC Control Sequence

<!-- Page 81 -->

## End Suppression (ESU)
The End Suppression control sequence marks the end of a string of presentation text suppressed from the
visible output.
#### Syntax

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
|:--- |:--- |:--- |:--- |:--- |:--- |:--- |:--- |
| 0 | CODE | PREFIX | X'2B' | Control Sequence Prefix | M | N | N [PTOCA-4-221]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-4-222]|
| 2 | UBIN | LENGTH | 3 | Control sequence length | M | N | N [PTOCA-4-223]|
| 3 | CODE | TYPE | X'F4' – X'F5' | Control sequence function type | M | N | N [PTOCA-4-224]|
| 4 | CODE | LID | X'00' – X'FF' | Suppression identifier | M | N | N [PTOCA-4-225]|
This control sequence marks the end of a string of presentation text that has been suppressed. It works in
conjunction with the Begin Suppression control sequence. Please see “Begin Suppression (BSU)”
for information on the Begin Suppression control sequence. If the value of the LID is not supported or is not
within the range specified by PTOCA, exception condition EC-9801 exists. The standard action in this case is
to ignore this control sequence and continue presentation with the value determined according to the data-
stream hierarchy.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-226]
### Pragmatics
This control sequence does not change the current addressable position. In order to suppress a text string
from the visible output, the string should be bounded by a Begin Suppression control sequence and an End
Suppression control sequence that have the same values for the LID. In addition, the controlling environment
must activate the LID. [PTOCA-4-227]
### Exception Conditions
This control sequence can cause the following exception condition:
* EC-9801...The value of LID is not supported or is not in the range specified by PTOCA. [PTOCA-4-228]
ESU Control Sequence

<!-- Page 82 -->

## Glyph Advance Run (GAR)
The Glyph Advance Run control sequence specifies the relative displacement along the current baseline (in
the i-direction) to the glyph origin for each glyph ID in the preceding GIR from the text position at the start of the
GLC.
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 UBIN LENGTH X'04' – X'FE'; [PTOCA-4-229]
even values
only
Control sequence length.
The length is 4 + n∙(size
of ADVANCE)
M N N
1 CODE TYPE X'8C', X'8D' Control sequence [PTOCA-4-230]
function type
M N N
2-3 X'0000' Reserved; should be zero M N N [PTOCA-4-231]
Zero or more advances along the baseline in the following format:
+0-1 UBIN ADVANCE X'0000' –
X'FFFF'
Glyph advance along the
baseline
O N N
ADVANCE is a signed binary number in measurement units. The range for ADVANCE assumes a
measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the
conversion routine described in “Interpreting Ranges”. [PTOCA-4-232]
### Semantics
This control sequence carries a sequence of glyph advances that correspond to the glyph IDs in the preceding
GIR. Each advance is a 2-byte unsigned displacement along the current baseline that is measured from the
text position that was defined at the start of the GLC chain to the glyph origin for each glyph ID. The
displacement is in the i-axis direction. The first advance corresponds to the first glyph ID, the second advance
corresponds to the second glyph ID, and so forth. Advances are specified using the current measurement
units.
Each GAR control sequence must be chained to a preceding GIR control sequence. It can be followed by a
chained GOR, GIR, or UCT control sequence. If it is followed by a diffe rent control sequence, the GAR
terminates the GLC chain. [PTOCA-4-233]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9C03...Invalid sequence. The GAR is not preceded by a GIR, or, if it indicates chaining, it is not followed [PTOCA-4-234]
by a GOR, GIR, or a UCT.
* EC-9C06...GIR, GAR, or GOR control sequence found outside of a GLC chain. A GIR, GAR, or GOR was [PTOCA-4-235]
found that is not part of a GLC chain.
* EC-9C08...Glyph Advance count mismatch. The number of glyph advances specified is not the same as the [PTOCA-4-236]
number of glyph IDs specified in the preceding GIR.
GAR Control Sequence

<!-- Page 83 -->

## Glyph ID Run (GIR)
The Glyph ID Run control sequence specifies an array of glyph IDs from the current TrueType/OpenType font. [PTOCA-4-237]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 UBIN LENGTH X'04' – X'FE'; [PTOCA-4-238]
even values
only
Control sequence length.
The length is 4 + n∙(size
of GLYPHID)
M N N
1 CODE TYPE X'8B' Control sequence [PTOCA-4-239]
function type
M N N
2-3 X'0000' Reserved; should be zero M N N [PTOCA-4-240]
Zero or more glyph IDs in the following format:
+0-1 UBIN GLYPHID X'0000' –
X'FFFF'
Glyph ID O N N
### Semantics
This control sequence carries a sequence of glyph ids in the current font or in a font linked to the current font.
This font is identified by the font OID in the GLC. If the font is in a font collection, the OID in the GLC
corresponds to the font collection and the font name in the GLC is used to identify the font.
A GIR control sequence must be chained to a GLC, if in the first grouping, or to a preceding GAR or GOR, if in
subsequent groupings. It must be followed by a chained GAR that contains a glyph advance for each glyph ID
in this control sequence. [PTOCA-4-241]
### Pragmatics
It is possible that a text run may require more glyphs than the GIR can contain. Composition applications can
handle this by specifying multiple GIR/GAR[/GOR] groupings. If there are n glyph IDs contained in a GIR, then
the chained GAR must contain n advances and the optional GOR must contain n offsets. [PTOCA-4-242]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9C02...Invalid glyph ID. The current font does not contain a specified glyph ID. [PTOCA-4-243]
* EC-9C03...Invalid sequence. The GIR is not preceded by either a GLC or a GAR or a GOR. [PTOCA-4-244]
* EC-9C06...GIR, GAR, or GOR control sequence found outside of a GLC chain. A GIR, GAR, or GOR was [PTOCA-4-245]
found that is not part of a GLC chain.
GIR Control Sequence

<!-- Page 84 -->

## Glyph Layout Control (GLC)
The Glyph Layout Control control sequence marks the start of one or more glyph run groupings that together
render text using arrays of glyph identifiers and positions. [PTOCA-4-246]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-247]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-248]
2 UBIN LENGTH 10 - (p - 1) Control sequence length M N N [PTOCA-4-249]
3 CODE TYPE X'6D' Control sequence function [PTOCA-4-250]
type
M N N
4–5 SBIN IADVNCE X'8000' –
X'7FFF'
The advance along the
current baseline after
processing this GLC chain.
A value of X'0000'
indicates that the current
text position is not changed
after processing the GLC
chain.
M N N
6 UBIN OIDLGTH 0, 13-129 Length of FONTOID [PTOCA-4-251]
parameter
M N N
7 UBIN FFNLGTH 0 - (255 - (10 + [PTOCA-4-252]
OIDLGTH)),
even values
only
Length of FFONTNME
parameter
M N N
8-1 1 X'00...00' Reserved; should be zero M N N [PTOCA-4-253]
12-n UBIN FONTOID Object Identifier for the font
that contains the glyphs to
be rendered.
* Offset 12 – must be X'06' [PTOCA-4-254]
* Offset 13 – length of [PTOCA-4-255]
content bytes
* Offset 14 to n – OID [PTOCA-4-256]
content
O N N
(n + 1) - p CHAR FFONTNME Full font name of the font
identified by the FONTOID
parameter, unless the font
is in a collection, in which
case the OID corresponds
to the collection. The name
is encoded in UTF-16BE.
O N N
IADVNCE is a signed binary number in measurement units. The range for IADVNCE assumes a measurement
unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the conversion
routine described in “Interpreting Ranges”.
IADVNCE specifies the advance along the current baseline (in the inline direction) that is caused by
processing this GLC chain. When this advance is added to the text position at the start of the GLC, it defines
GLC Control Sequence

<!-- Page 85 -->

the current text position after the GLC chain has been processed. A value of X'0000' indicates that the current
text position is not changed after processing the GLC chain.
OIDLGTH specifies the length in bytes of the FONT OID parameter. A value of X'00' indicates that the
FONT OID parameter is not specified and the presentation device is to use the FONTOID specified in the
previous GLC in the text object that specified this parameter. Note that when a font matching the FONT OID is
found, it is always processed with the presentation parameters determined by the font mapping for the LID in
the last SCFL control sequence.
Application Note: When OIDLGTH = 0, the presentation device searches backwards through the PTOCA
control sequences until a GLC with an OID is found or the beginning of the text object is reached. In
MO:DCA environments, the beginning of the text object is indicated by the Begin Presentation Text
(BPT) structured field, which causes AFP presentation servers to reset the currently active font to the
presentation device default font by issuing a SCFL with LID = X'FF'. The AFP generator must therefore
always define an active font at the start of the text object by generating a SCFL control sequence that
specifies the font LID and a GLC that specifies the font OID.
FFNLGTH specifies the length in bytes of the FFONTNME parameter. A value of X'00' indicates that the
FFONTNME parameter is not specified. This parameter must be set to X'00' if the OIDLGTH parameter is set
to X'00', or exception condition EC-9C0B exists.
FONT OID specifies the OID of the font used to present the glyph runs in the GLC chain. If the font is in a font
collection, the OID is for the collection. Presentation devices must validate the OID specified in the GLC
against the current font or font collection. If the current font is linked to additional fonts, the FONT OID may refer
to the current font (the base font) or to any font linked to that base font. Only the font identified by FONT OID
will be used with the GLC chain, the other linked fonts are ignored when placing glyphs. See the Mixed Object
Document Content Architecture Reference, AFPC-0004 for the definition of the algorithm used to calculate the
OID of a TrueType/OpenType font.
FFONTNME specifies the Full Font Name (FFN) of the font identified by the FONT OID parameter, unless the
FONT OID corresponds to a font collection. If FONT OID corresponds to a font collection, the FFONTNME
parameterspecifies the Full Font Name of the font in the collection. This parameter is used to search for a
specific font in a font collection if the FONT OID parameter identifies a collection. The name is encoded in UTF-
16BE. This parameter must not be specified if the FONTOID parameter is not specified; if it is, exception
condition EC-9C0B exists. [PTOCA-4-257]
### Semantics
This control sequence marks the start of a run of glyph IDs and positions contained in subsequent glyph run
control sequence groupings that make up the GLC chain. The glyph IDs in the TrueType/OpenType font
specified by the FONTOID are carried in GIR control sequences. The advances along the current baseline (in
the i-axis direction) are carried in GAR control sequences. The optional offsets from the current baseline (in the
b-axis direction) are carried in GOR control sequences. These subsequent controls must be chained to the
GLC using the PTOCA chaining rules.
The GLC control sequence must be followed by one or more GIR/GAR[/GOR] groupings. (The notation
“[/GOR]” will be used to indicate that the GOR is optional.) No other control sequences can be interspersed
within the GIR/GAR[/GOR] groupings or between them. Each GIR/GAR[/GOR] grouping causes a set of
glyphs to be rendered.
An optional UCT containing the Unicode encoded text may be chained to the last GAR[/GOR] and terminates
the chain. This UCT is not rendered. If the UCT is not specified, the last GAR[/GOR] terminates the chain.
After the GLC chain has been processed:
$I_{cnew}$ = $I_c$
+ IADVNCE
$B_{cnew}$ = $B_c$
GLC Control Sequence

<!-- Page 86 -->

### Pragmatics
The GIR/GAR[/GOR] groupings accumulate glyphs from the current font along a single baseline. While there is
no restriction on the order of the glyphs, it is recommended that the composition application list them
sequentially in the inline direction.
The UCT is optional and carries the Unicode text that the GIR/GAR[/GOR] groupings will present. Its use is
recommended as it provides applications the ability to interpret the sequence of glyphs rendered by the
presentation device.
The application must specify the glyph IDs, glyph advances, and glyph offsets in the same order. If there are n
glyph IDs present in a GIR, then the subsequent GAR must contain n advances, and the optional GOR must
contain n offsets.
If a text run requires more glyphs than a GIR can contain, the applications can provide multiple GIR/GAR
[/GOR] groupings chained to a single GLC.
In a GIR/GAR[/GOR] grouping, if all glyph offsets are 0, the application can choose not to specify the GOR(s).
If one glyph offset is not zero, the application must specify the GOR entries in the same order as the GIR and
GAR entries.
The introduction of support for glyph runs affects the operation of some current PTOCA control sequences.
The effects are described in the following table:
Table 1 1. Interaction of GLC chain with other control sequences
Control Sequence
Effect
Modal control sequences
Set Baseline Increment (SBI) Has no effect on GLC presentation
Set Coded Font Local (SCFL) Establishes the current font for use by GLC. When linked
fonts are used, the font specified by the SCFL is always
the base font.
Set Extended Text Color (SEC) Specifies the foreground color used to present the glyphs
in the subsequent GLC chains
Set Inter-character adjustment (SIA) Has no effect on GLC presentation
Set Inline Margin (SIM) Has no effect on GLC presentation
Set Text Color (STC) Specifies the foreground color used to present the glyphs
in the subsequent GLC chains
Set Text Orientation (STO) Establishes the I-direction and B-direction used to present
the glyphs in the subsequent GLC chains
Set V ariable Space Character Increment (SVI) Has no effect on GLC presentation
Field control sequences
Temporary Baseline Move (TBM) T emporarily moves the baseline coordinate relative to the
established baseline coordinate position. As with
character-based text, glyph runs in the GLC chains are
positioned relative to this temporary baseline.
Overstrike (OVS), Underscore (USC) Have no effect on GLC presentation
Directive control sequences
Absolute Move Baseline (AMB) Moves the baseline coordinate position relative to the I-
axis.
GLC Control Sequence

<!-- Page 87 -->

Table 1 1 Interaction of GLC chain with other control sequences (cont'd.)
Control Sequence
Effect
Absolute Move Inline (AMI) Moves the inline coordinate position relative to the B-axis
Begin Line (BLN) Begins a new line
Begin Suppression (BSU)/End Suppression (ESU) Causes presentation of the glyphs in all GLC chains that
are between the BSU and ESU to be suppressed if the
corresponding suppression ID is activated
Draw B-axis Rule (DBR) Has no effect on subsequent GLC presentation
Draw I-axis Rule (DIR) Has no effect on subsequent GLC presentation
No Operation (NOP) Has no effect on subsequent GLC presentation
Relative Move Baseline (RMB) Moves the baseline coordinate relative to the current
baseline coordinate position
Relative Move Inline (RMI) Moves the inline coordinate relative to the current inline
position
Repeat String (RPS) Has no effect on subsequent GLC presentation
Transparent Data (TRN) Has no effect on subsequent GLC presentation
The following examples demonstrate various valid examples of glyph layout control chaining:
* Example 1. GLC chain without optional controls. The GLC may reference the base font, or any font linked to [PTOCA-4-258]
the current font. Since all glyphs are positioned on the current effective baseline, the GOR control sequence
is omitted:
GLC GIR GAR <<chain ends>>
* Example 2. GLC chain with optional controls. Since one or more glyphs must be positioned with an offset [PTOCA-4-259]
from the baseline, the optional GOR control sequence is included in this chain. The PTOCA chain is
terminated by an optional UCT control which carries the Unicode encoded text presented by the glyph layout
control sequences:
GLC GIR GAR GOR UCT <<chain ends>>
* Example 3. GLC chain with multiple GIR/GAR[/GOR] groupings. The text required more glyphs than a single [PTOCA-4-260]
GIR could list, so multiple GIR/GAR[/GOR] groupings are included. Since one or more glyphs in the first
grouping must be positioned with an offset from the baseline, the optional GOR control sequence is included
in this group. The optional GOR is not needed in the second grouping, and is omitted. The PTOCA chain is
terminated by an optional UCT control which carries the Unicode encoded text presented by the glyph layout
control sequences:
GLC GIR GAR GOR GIR GAR UCT <<chain ends>> [PTOCA-4-261]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9C00...Font Mismatch. The object OID specified in the GLC control sequence does not match the object [PTOCA-4-262]
OID of the current font or any font linked to the current font. If the OID matched the OID of a font collection,
the Full Font Name did not match any font in the collection.
* EC-9C01...Font format not valid for use with glyph layout control sequences. The current font is not a [PTOCA-4-263]
TrueType/OpenType font.
* EC-9C03...Unexpected control sequence. An unexpected control sequence was encountered between the [PTOCA-4-264]
GLC control sequence and the terminating control sequence.
GLC Control Sequence

<!-- Page 88 -->

* EC-9C09...Missing font OID. The GLC specified an OIDLGTH of zero, but no previous font OID was supplied [PTOCA-4-265]
within the text object.
* EC-9C0A...Count mismatch or invalid length. The byte count specified by the OIDLGTH and FFONTNME [PTOCA-4-266]
parameters is not consistent with the overall control sequence length, or the OIDLGTH or FFNLGTH
parameters are outside their valid range.
* EC-9C0B...Full Font Name specified without font OID. A font OID was not specified (OIDLGTH = 0), but a [PTOCA-4-267]
Full Font Name was specified (FFNLGTH ≠ 0).
GLC Control Sequence

<!-- Page 89 -->

## Glyph Offset Run (GOR)
The Glyph offset Run control sequence specifies an offset (relative displacement) from the current baseline (in
the b-direction) to the glyph origin for each glyph ID in the preceding GIR. [PTOCA-4-268]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 UBIN LENGTH X'04' – X'FE'; [PTOCA-4-269]
even values
only
Control sequence length.
The length is 4 + n∙(size
of OFFSET)
M N N
1 CODE TYPE X'8E', X'8F' Control sequence [PTOCA-4-270]
function type
M N N
2-3 X'0000' Reserved; should be zero M N N [PTOCA-4-271]
Zero or more offsets from the current baseline in the following format:
+0-1 SBIN OFFSET X'8000' –
X'7FFF'
Glyph offset from the
current baseline
O N N
### Semantics
This control sequence carries a sequence of glyph offsets that correspond to the glyph IDs in the preceding
GIR. Each offset is a 2-byte signed displacement from the current baseline in the b-axis direction to the glyph
origin for each glyph ID. Application of the offset does not change the position of the current baseline. That is,
the offset for each glyph ID is applied against the baseline that was defined at the start of the GLC chain. The
first offset corresponds to the first glyph ID and the first glyph advance, the second offset corresponds to the
second glyph ID and the second glyph advance, and so forth. offsets are specified using the current
measurement units. A positive offset is measured towards the i-axis; a negative offset is measured away from
the i-axis.
Architecture Note: The direction in which GOR offsets are measured, as indicated by the sign of the offset, is
opposite to the direction in which increments in a RMB are measured, as indicated by the sign of the
increment.
The GOR control sequence is optional, but if present, must be chained to a GAR. It can be followed by a
chained GIR or UCT. Composition applications must specify the same number of glyph offsets as glyph
advances so that presentation devices can offset the correct glyph. [PTOCA-4-272]
### Pragmatics
If a composition application needs to offset one glyph, it must offset all the glyphs identified in the preceding
GIR.
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9C03...Invalid sequence. The GOR is not preceded by a GAR, or, if it indicates chaining, is not followed [PTOCA-4-273]
by a GIR or a UCT.
* EC-9C06...GIR, GAR, or GOR control sequence found outside of a GLC chain. A GIR, GAR, or GOR was [PTOCA-4-274]
found that is not part of a GLC chain.
* EC-9C08...Glyph offset count mismatch. The number of glyph offsets specified is not the same as the [PTOCA-4-275]
number of glyph IDs specified in the preceding GIR.
GOR Control Sequence

<!-- Page 90 -->

GOR Control Sequence

<!-- Page 91 -->

## No Operation (NOP)
The No Operation control sequence has no effect on presentation. [PTOCA-4-276]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-277]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-278]
2 UBIN LENGTH 2–255 Control sequence length M N N [PTOCA-4-279]
3 CODE TYPE X'F8' – X'F9' Control sequence [PTOCA-4-280]
function type
M N N
4–256 UNDF IGNDATA Not
applicable
Ignored data O N N
### Semantics
This control sequence specifies a string of bytes that are to be ignored.
Exception Condition
This control sequence can cause the following exception condition:
* None
NOP Control Sequence

<!-- Page 92 -->

## Overstrike (OVS)
The Overstrike control sequence identifies text that is to be overstruck with a specified character. [PTOCA-4-281]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-282]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-283]
2 UBIN LENGTH 5 Control sequence length M N N [PTOCA-4-284]
3 CODE TYPE X'72' – X'73' Control sequence [PTOCA-4-285]
function type
M N N
4 BITS BYPSIDEN See [PTOCA-4-286]
### Semantics
Bypass identifiers M Y Y
5–6 CODE OVERCHAR X'0000' –
X'FFFF'
Overstrike identifiers M N N
BYPSIDEN is a flag byte with no measurement units. The PTOCA default for BYPSIDEN is X'01'. OVERCHAR
is defined as a one-byte or two-byte code point that, when coupled with the active coded font, specifies the
character to be used for overstriking. Single-byte code points are loaded in byte 6. OVERCHAR does not
accept the default indicator. [PTOCA-4-287]
### Semantics
Overstrike is accomplished with a pair of Overstrike control sequences. A beginning OVS with a non-zero
value in BYPSIDEN bits 4-7 activates overstrike. An ending OVS with a zero value in BYPSIDEN bits 4-7
deactivates overstrike. The beginning OVS control sequence immediately precedes the field of text to be
overstruck. It specifies the following:
* The overstrike character [PTOCA-4-288]
* How to place the overstrike characters in relation to the characters in the text field [PTOCA-4-289]
* Which controlled inline white space is to be overstruck [PTOCA-4-290]
The text field to be overstruck, called the overstrike field, is delimited by the beginning OVS and either an
ending OVS control sequence or the end of the Presentation Text object. The overstrike field is a sequential
string of presentation text.
BYPSIDEN specifies which controlled inline white space within the overstrike field is to be overstruck.
Controlled inline white space is that area of the presented line that contains no visible material due to
movement of the presentation position in the I-direction caused by the following:
* Absolute Move Inline control sequence [PTOCA-4-291]
* Relative Move Inline control sequence [PTOCA-4-292]
* Space character or variable space character [PTOCA-4-293]
Application Note: The following code points are normally used for the variable space character:
* X'40' in EBCDIC single-byte code pages [PTOCA-4-294]
* X'20' in ASCII single-byte code pages [PTOCA-4-295]
* X'4040' in EBCDIC double-byte code pages [PTOCA-4-296]
* X'2020' in ASCII double-byte code pages [PTOCA-4-297]
OVS Control Sequence

<!-- Page 93 -->

The following code points are used for the variable space character in TrueType/OpenType fonts that
use a Unicode (UTF-16) encoding:
* X'0020' [PTOCA-4-298]
* X'00A0' [PTOCA-4-299]
Movement of the current inline position in the I-direction to or through a presentation position that already
contains material to be overstruck creates controlled inline white space for the entire move in the I-direction.
Not all inline white space is controlled.
White space resulting from non-printing characters (other than space characters or variable space characters)
within the character set, from substitution of non-printing characters for unsupported characters, from
intercharacter adjustment, or from the inline margin is not considered controlled inline white space.
Inline moves that cause the current addressable position to move in a direction opposite to the I-direction, that
is, negative inline moves, do not cause overstrike.
BYPSIDEN is bit-encoded as follows.
BIT MEANING
0-3 Reserved, that is, set to B'0' by generators and ignored by receivers [PTOCA-4-300]
4 Bypass Relative Move Inline [PTOCA-4-301]
5 Bypass Absolute Move Inline [PTOCA-4-302]
6 Bypass space characters and variable space characters [PTOCA-4-303]
7 No Bypass in Effect [PTOCA-4-304]
Bits 0-3, Reserved
Reserved bits are set to B'0' by generators and ignored by receivers.
Bit 4, Bypass Relative Move Inline
A value of B'0' in this bit indicates that the controlled white space generated as a result of a Relative Move
Inline control sequence is to be overstruck. A value of B'1' in this bit indicates that such controlled white space
is not to be overstruck. It should be bypassed.
Bit 5, Bypass Absolute Move Inline
A value of B'0' in this bit indicates that the controlled white space generated as a result of an Absolute Move
Inline control sequence is to be overstruck. A value of B'1' in this bit indicates that such controlled white space
is not to be overstruck. It should be bypassed.
Bit 6, Bypass space characters
A value of B'0' in this bit indicates that the controlled white space generated as a result of space characters or
variable space characters is to be overstruck. A value of B'1' in this bit indicates that such controlled white
space is not to be overstruck. It should be bypassed.
Bit 7, No Bypass in Effect
A value of B'0' in this bit activates the other bypass flags. A value of B'1' in this bit indicates that the other
bypass flags are overridden, and that all text and white space bounded by the OVS pair should be overstruck.
If the value of BYPSIDEN is the default indicator, a value is obtained from the hierarchy. Please see Table 9 on
page 35.
Implementation Note: Most IPDS printers have implemented X'FF' as the default indicator, which results in
BYPSIDEN = X'01' - no bypass in effect. However, it could be argued that the proper default indicator is
OVS Control Sequence

<!-- Page 94 -->

X'0F', since BYPSIDEN bits 0-3 are reserved, should be set to zero by generators, and should be
ignored by receivers. To avoid confusion, it is strongly recommended that a default indicator not be used
for this parameter, and that the value X'01' is specified directly if the default is desired.
An overstrike area is that portion of the overstrike field for which text is actually overstruck. An overstrike area
is delimited by the addressable position in the following cases.:
* A beginning OVS [PTOCA-4-305]
* Either end of bypassed controlled inline white space [PTOCA-4-306]
* Either end of a baseline move, which may be for the established baseline or for a temporary baseline [PTOCA-4-307]
* The beginning of negative changes in the presentation position caused by inline moves or negative [PTOCA-4-308]
intercharacter adjustments
* Boundaries where violation causes truncation [PTOCA-4-309]
* An ending OVS [PTOCA-4-310]
* The end of the Presentation Text object [PTOCA-4-311]
Additionally, the dimension in the positive I-direction of the overstrike field is defined by the minimum and
maximum I-coordinates specified between the overstrike area delimiters. White space resulting from the
application of the inline margin is overstruck only if this area is entered by means of an inline move.
Overstrike characters are presented side by side without regard to the position of the characters being
overstruck. Intercharacter adjustments are not applied to the placement of overstrike characters. The number
of overstrike characters required for each overstrike area within an overstrike field is equal to the inline
dimension of the overstrike area divided by the character increment of the overstrike character. If the result of
this division is less than one, one overstrike character must be presented in the overstrike area. If the result of
this division is greater than one and is not an integer, the decision to place an overstrike character is based on
rounding to the nearest integer. Normally this rounding is down to the next lower integer, except as specified in
the Pragmatics section.
If the value of OVERCHAR is not supported or is not within the range specified by PTOCA, an exception
condition exists. See the Pragmatics section for the exception condition code and the standard action. [PTOCA-4-312]
### Pragmatics
The intent of the semantic is to distribute the overstrike characters evenly in the overstrike area without
violating the delimiters of the overstrike area, that is, the I-coordinates at the beginning and end of the
overstrike area.
Presentation of a portion of an overstrike character is acceptable to avoid overlapping characters.
Characters are overstruck using the current baseline. That is, the overstrike function follows the current
baseline even when the baseline is changed by a Temporary Baseline Move control sequence.
OVERCHAR is defined as a one-byte or two-byte code point that, when coupled with the active coded font,
specifies the character to be used for overstriking. Single-byte code points are located in byte 6.
The selected overstrike character must be a printable character and must specify a positive, non-zero
character increment. If these conditions are not met by the selected overstrike character, exception condition
EC-9A01 exists. To avoid an overflow of the overstrike field by the last overstrike character, the character
increment of the overstrike character should also be equal to or greater than the character box size. If this is
not true, exception condition EC-9A01 may optionally be detected.
Multiple beginning and ending Overstrike pairs may not be nested. However, no exception condition exists if a
beginning OVS is processed when another OVS is already active. The subsequent beginning OVS terminates
the previous OVS sequence and starts another. If an ending OVS is encountered when there has been no
previous OVS in the Presentation Text object, no exception condition exists. Ignore the ending OVS. If a
OVS Control Sequence

<!-- Page 95 -->

Presentation Text object contains a beginning OVS without a matching ending OVS, no exception condition
exists. T erminate the OVS at the end of the Presentation Text object.
There is no provision in this control sequence to specify a coded font, and Set Coded Font Local control
sequences that occur within the overstrike field do not affect the appearance of the overstrike character. If the
desired overstrike character is not defined in the active font which is controlling the text presentation, the
beginning Overstrike control sequence must be preceded by a Set Coded Font Local control sequence that
specifies a different coded font that contains the overstrike character. In this case, the coded font controlling
the text presentation must be re-established following the ending Overstrike control sequence. If the graphic
character specified in the OVS control sequence is not valid in the active coded font, exception condition EC-
2100 exists. The standard action is to use a device default character as the overstrike character. If the graphic [PTOCA-4-313]
character does not have a rotation available that is equivalent to the current character rotation, exception
condition EC-3F02 exists. The standard action is to accomplish the overstrike function to the best of the
receiver's ability.
There are no syntactic restrictions on the occurrence of Begin Line, Absolute Move Baseline, Relative Move
Baseline, and Temporary Baseline Move control sequences within the overstrike field.
Color is not a parameter of this control sequence.
Utilization of algorithms to reposition the overstrike characters within the overstrike areas of an overstrike field
is restricted in several ways.
* At least one overstrike character must occur in an overstrike area. [PTOCA-4-314]
* The overstrike characters must be positioned relative to the delimiters of the overstrike area, rather than to [PTOCA-4-315]
the last overstrike character in any previous overstrike area.
* Overlap of any portion of the B-space of the overstrike character with the B-space of a character not within [PTOCA-4-316]
the overstrike area is considered unacceptable, except in the case of a single character.
* Overlap of any portion of the B-space of the overstrike character with the B-space of another overstrike [PTOCA-4-317]
character within the overstrike field should be avoided. If overlap occurs, it should not be allowed to exceed
1/4 the B-space of either of the characters.
* Rounding of f the number of overstrike characters is permitted. The minimum required support is to round of f [PTOCA-4-318]
to the nearest integer number of overstrike characters in the overstrike area. This overlap may be allowed at
a change of baseline, except when the overlap causes a portion of the character box to extend beyond a
boundary of the object space.
* Multiple passes over the same portions of the presentation space through the use of AMI and RMI control [PTOCA-4-319]
sequences are not restricted.
* An area of zero length is not considered to be a valid overstrike area. [PTOCA-4-320]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-2100...The graphic character specified is not valid in the active font. [PTOCA-4-321]
* EC-3F02...The graphic character specified does not have a rotation available that is equivalent to the current [PTOCA-4-322]
character rotation.
* EC-9A01...The graphic character specified has an invalid character increment or is not a printable character. [PTOCA-4-323]
OVS Control Sequence

<!-- Page 96 -->

## Relative Move Baseline (RMB)
The Relative Move Baseline control sequence moves the baseline coordinate relative to the current baseline
coordinate position.
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-324]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-325]
2 UBIN LENGTH 4 Control sequence length M N N [PTOCA-4-326]
3 CODE TYPE X'D4' – X'D5' Control sequence [PTOCA-4-327]
function type
M N N
4–5 SBIN INCRMENT X'8000' –
X'7FFF'
Increment M N N
INCRMENT is a signed binary number in measurement units. It does not accept the default indicator. The
range for this parameter assumes a measurement unit of 1/1440 inch. If it is necessary to convert to a diffe rent
measurement unit, please see the conversion routine described in “Interpreting Ranges”. [PTOCA-4-328]
### Semantics
This control sequence specifies an increment, INCRMENT, in the B-direction from the current baseline
coordinate position to a new baseline coordinate position. After execution of this control sequence,
presentation is resumed at the new baseline coordinate position. A positive value causes movement in the B-
direction, while a negative value causes movement toward the I-axis. This control sequence does not modify
the current inline coordinate position.
$I_{cnew}$ = $I_c$
$B_{cnew}$ = $B_c$
+ INCRMENT where 0 <= $B_{cnew}$
<= B-extent
If the value of INCRMENT is not supported or is not within the range specified by PTOCA, exception condition
EC-1601 exists. The standard action is to continue presentation according to the description given in the
Pragmatics section.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-329]
### Pragmatics
If the value of INCRMENT is zero, the addressable position is not displaced, and any intercharacter increment
is not applied. If a move is to a presentation position for which the character's character box will exceed the
object space and an attempt is made to present there, exception condition EC-0103 exists. The standard
action is to refrain from presenting the character that exceeds the object space, and to continue processing
without presenting characters until the presentation position occupies a valid addressable position for the
character being presented. Then presentation of characters may resume. PTOCA does not constrain
advancement of the baseline coordinate in the negative B-direction, that is, toward the I-axis. However, a
constraint of this type may be imposed by the subset level or by the receiver. If this constraint is applied and
RMB Control Sequence

<!-- Page 97 -->

INCRMENT is negative, exception condition EC-1403 exists. The standard action is to ignore the control
sequence.
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1601...The value of INCRMENT is not supported or is not in the range specified by PTOCA. [PTOCA-4-330]
* EC-0103..The presentation position is outside the object space and presentation is attempted. [PTOCA-4-331]
* EC-1403...Negative INCRMENT is not valid. [PTOCA-4-332]
RMB Control Sequence

<!-- Page 98 -->

## Relative Move Inline (RMI)
The Relative Move Inline control sequence moves the inline coordinate of the presentation position relative to
the current inline position. [PTOCA-4-333]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-334]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-335]
2 UBIN LENGTH 4 Control sequence length M N N [PTOCA-4-336]
3 CODE TYPE X'C8' – X'C9' Control sequence [PTOCA-4-337]
function type
M N N
4–5 SBIN INCRMENT X'8000' –
X'7FFF'
Increment M N N
INCRMENT is a signed binary number in measurement units. It does not accept the default indicator. The
range for this parameter assumes a measurement unit of 1/1440 inch. If it is necessary to convert to a diffe rent
measurement unit, please see the conversion routine described in “Interpreting Ranges”. [PTOCA-4-338]
### Semantics
This control sequence specifies an increment, INCRMENT, in the I-direction from the current inline coordinate
position to a new inline coordinate position. After execution of this control sequence, presentation is resumed
at the new inline coordinate position. A positive value is in the direction of line growth, while a negative value
logically backspaces. This control sequence does not modify the current baseline coordinate position.
$I_{cnew}$ = $I_c$
+ INCRMENT where 0 <= $I_{cnew}$
<= I-extent
$B_{cnew}$ = $B_c$
If the value of INCRMENT is not supported or is not within the range specified by PTOCA, exception condition
EC-1501 exists. The standard action is to continue presentation according to the description given in the
Pragmatics section.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-339]
### Pragmatics
If the value of INCRMENT is zero, the addressable position is not moved, and any intercharacter adjustment is
not applied. If a move is to a presentation position for which the character's character box will exceed the
object space and an attempt is made to present there, exception condition EC-0103 exists. The standard
action is to refrain from presenting the character that exceeds the object space, and continue processing
without presenting characters until the presentation position occupies a valid addressable position for the
character being presented. Then presentation of characters may resume. [PTOCA-4-340]
### Exception Conditions
This control sequence can cause the following exception conditions:
RMI Control Sequence

<!-- Page 99 -->

* EC-1501...The value of INCRMENT is not supported or is not in the range specified by PTOCA. [PTOCA-4-341]
* EC-0103..The presentation position is outside the object space and presentation is attempted. [PTOCA-4-342]
RMI Control Sequence

<!-- Page 100 -->

## Repeat String (RPS)
### Syntax
The Repeat String control sequence contains a string of graphic character code points that is repeated on the
current line.
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-343]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-344]
2 UBIN LENGTH 4–255 Control sequence length M N N [PTOCA-4-345]
3 CODE TYPE X'EE' – X'EF' Control sequence [PTOCA-4-346]
function type
M N N
4–5 UBIN RLENGTH 0–32,767 Repeat length M N N
6–256 CHAR RPTDATA Not
applicable
Repeated data O N N
The contents of RPTDATA are unknown. RLENGTH is a binary number expressing byte count. RLENGTH and
RPTDATA do not accept the default indicator. [PTOCA-4-347]
### Semantics
This control sequence specifies a string of bytes that is to be processed entirely as graphic character code
points. No code point is recognized as a Control Sequence Prefix. Current inline position is incremented for
each graphic character specified by a code point in the string. The baseline position is not changed.
For graphic characters following each other:
$I_{cnew}$ = $I_c$
+ intercharacter adjustment + character increment
Intervening non-incrementing characters are ignored.
For graphic characters following RMI, AMI, or BLN control sequences or following a space character or
variable space character:
$I_{cnew}$ = $I_c$
+ character increment
Intervening non-incrementing characters are ignored.
For the variable space character:
$I_{cnew}$ = $I_c$
+ VSI
For non-incrementing characters:
$I_{cnew}$ = $I_c$
In all cases:
$B_{cnew}$ = $B_c$
RPTDATA is repeated until it fills the number of bytes specified by RLENGTH. If the number of bytes specified
by RLENGTH is not an integral multiple of the length of the data, a portion of the data is truncated. If the
number of bytes specified by the value of RLENGTH is less than the length of the data, a portion of the data is
truncated. If the number of bytes specified by RLENGTH is equal to the data provided, the Repeat String
control sequence has the same function as the Transparent Data control sequence.
When a double-byte font is active and the length of RPTDATA is an odd number, exception condition EC-1A01
exists. The standard action is to ignore the Repeat String control sequence and continue processing. When a
double-byte font is active and RLENGTH is an odd number, exception condition EC-1B01 exists. The standard
RPS Control Sequence

<!-- Page 101 -->

action is to ignore the Repeat String control sequence and continue processing. If the value of RLENGTH is
not supported or is not within the range specified by PTOCA, exception condition EC-1901 exists. The
standard action is to ignore the control sequence and continue presenting.
The subset may limit the range permitted in this control sequence. For detailed information about function
subsets, please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”,
on, and Appendix B, “IPDS Environment”,. See “Related Publications” for
data-stream documentation. [PTOCA-4-348]
### Pragmatics
The standard action value for RLENGTH is the length of RPTDATA. If any part of a character's character box
will exceed the object space as a result of the Repeat String control sequence and an attempt is made to
present the string of characters, exception condition EC-0103 exists. The standard action is not to present the
character that exceeds the object space and to continue processing without presenting graphic characters until
the presentation position is returned to an addressable position that is a valid presentation position for the
character being presented.
If the value of the control sequence length parameter is four, indicating a length of four bytes, and the value of
RLENGTH is zero, that is, there are no data bytes, this control sequence has the effect of a no-operation. If the
value of the control sequence length parameter is greater than four, that is, data bytes are provided, and if the
value of RLENGTH is zero, no exception condition exists and the data bytes are ignored. If, however, the value
of the control sequence length parameter is four and RLENGTH is not zero, exception condition EC-1F01
exists. The standard action is to ignore this control sequence and continue processing.
If the character encoding is Unicode but the code points in the RPS are not valid Unicode data, exception
condition EC-1A03 exists. The standard action is to skip the remainder of the code points in the RPS, repeat
only the valid code point sequence, and continue processing.
Architecture Note: If the length of RLENGTH is the same as the length of RPTDATA, the RPS control
sequence has the same effect as the Transparent Data control sequence. It is recommended that the
RPS control sequence not be used in this manner, and that the TRN control sequence be used instead. [PTOCA-4-349]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1A01...A double-byte font is active and the length of RPTDATA is an odd number. [PTOCA-4-350]
* EC-1A03...Invalid Unicode data. This can be caused by one of the following: [PTOCA-4-351]
– A high-order surrogate code value was not immediately followed by a low-order surrogate code value. The
high-order surrogate range is U+D800 through U+DBFF.
– A low-order surrogate code value was not immediately preceded by a high-order surrogate code value.
The low-order surrogate range is U+DC00 through U+DFFF.
– An illegal UTF-8 byte sequence, as defined in the Unicode 3.2 Specification, was specified. For more
information on illegal UTF-8 byte sequences, see Table 15.
* EC-1B01...A double-byte font is active and RLENGTH is an odd number. [PTOCA-4-352]
* EC-1901...The value of RLENGTH is not supported or is not in the range specified by PTOCA. [PTOCA-4-353]
* EC-0103...A parameter value will cause part of a character's character box to be outside the object space, [PTOCA-4-354]
and presentation is attempted.
* EC-1F01...The control sequence length parameter is four and RLENGTH is not zero. [PTOCA-4-355]
RPS Control Sequence

<!-- Page 102 -->

## Set Baseline Increment (SBI)
The Set Baseline Increment control sequence specifies the increment to be added to the current baseline
coordinate when a Begin Line control sequence is executed. This is a modal control sequence. [PTOCA-4-356]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-357]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-358]
2 UBIN LENGTH 4 Control sequence length M N N [PTOCA-4-359]
3 CODE TYPE X'D0' – X'D1' Control sequence [PTOCA-4-360]
function type
M N N
4–5 SBIN INCRMENT X'0000' –
X'7FFF'
Increment M Y Y
INCRMENT is a positive binary number expressed in measurement units. The range for this parameter
assumes a measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit,
please see the conversion routine described in “Interpreting Ranges”. The PTOCA default value for
INCRMENT should be the Default Baseline Increment of the default coded font for the device. [PTOCA-4-361]
### Semantics
This control sequence specifies an increment, INCRMENT, in the positive B-direction from the current baseline
coordinate position to a new established baseline coordinate position for subsequent presentation text in the
current Presentation Text object. INCRMENT is applied when a Begin Line control sequence is executed. If the
value of INCRMENT is not supported or is not within the range specified by PTOCA, exception condition EC-
1 101 exists. The standard action is to ignore this control sequence and continue presentation with the value [PTOCA-4-362]
determined according to the hierarchy. If the value of INCRMENT is the default indicator, a value is obtained
from the hierarchy. Please see Table 9.
If INCRMENT is omitted, the standard action is to make no change to the existing Baseline Increment
parameter.
If this control sequence is omitted, the Baseline Increment initial text condition parameter in the Presentation
Text Data Descriptor (PTD) is used. If this initial text condition is not specified, a receiver default based on the
receiver default font should be used.
Note: The baseline increment, whether specified by the SBI control sequence, by the Baseline Increment initial
text condition, or by a receiver default, is not affe cted by the active font or by changes to the active font.
Implementation Note: Most IPDS printers use a default baseline increment of 240/1440 inch = 1/6 inch if this
parameter is not specified in an SBI control sequence or as an initial text condition in the PTD.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation.
SBI Control Sequence

<!-- Page 103 -->

### Pragmatics
The baseline coordinate position is incremented by INCRMENT after each Begin Line control sequence is
processed. If the value of INCRMENT is zero, each line appears superimposed over the preceding line.
This control sequence overrides the Baseline Increment initial text condition parameter that may occur in the
Presentation Text Data Descriptor. [PTOCA-4-363]
### Exception Conditions
This control sequence can cause the following exception condition:
* EC-1 101...The value of INCRMENT is not supported or is not in the range specified by PTOCA. [PTOCA-4-364]
SBI Control Sequence

<!-- Page 104 -->

Set Coded Font Local (SCFL)
The Set Coded Font Local control sequence activates a coded font and specifies the character attributes to be
used. This is a modal control sequence. [PTOCA-4-365]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-366]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-367]
2 UBIN LENGTH 3 Control sequence length M N N [PTOCA-4-368]
3 CODE TYPE X'F0' – X'F1' Control sequence [PTOCA-4-369]
function type
M N N
4 CODE LID X'00' – X'FE' Local identifier M Y Y [PTOCA-4-370]
The PTOCA default value for the LID is X'00'. [PTOCA-4-371]
### Semantics
This control sequence specifies a local identifier, LID, which is used by the controlling environment to access a
coded font for presentation of subsequent text in the current Presentation Text object. The current presentation
position is not changed by this control sequence. If the value of the LID is the default indicator, a value is
obtained from the hierarchy. Please see Table 9.
If the LID is omitted, exception condition EC-1E01 exists. The standard action in this case is to ignore the
control sequence and continue presentation with the active font determined according to the hierarchy. If the
value of the LID is not supported or is not within the range specified by PTOCA, exception condition EC-0C01
exists. The standard action is to ignore the control sequence and continue presentation with the active coded
font determined according to the hierarchy.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-372]
### Pragmatics
The LID is equated to a coded font, character rotation, and font modification parameters by a mapping function
in the controlling environment. Please see “Related Publications” and “Font Concepts”
for font documentation.
PTOCA expects the local identifier, LID, to be mapped to a global identifier. For example, this mapping could
be accomplished in the following ways:
* The receiver provides internal mapping, using device defaults. [PTOCA-4-373]
* The controlling environment provides the mapping to the receiver. [PTOCA-4-374]
If a mapping is not provided, exception condition EC-1802 exists. The standard action is to ignore the control
sequence, substitute a coded font determined according to the hierarchy, and continue processing. If the
coded font specified by the mapping is not available to the receiver, exception condition EC-1802 exists. The
standard action in this case is to substitute a coded font, determined according to the hierarchy, for the
SCFL Control Sequence [PTOCA-4-375]

<!-- Page 105 -->

specified coded font and continue processing. The PTOC A parameter specification hierarchy is defined in
Table 9.
If the text orientation is changed and the specified coded font is not compatible with the new orientation, when
an attempt is made to present a character from the selected coded font, exception condition EC-3F02 exists.
The standard action is to skip the presentation at this presentation position and use the active coded font
determined according to the hierarchy. This results in the best possible presentation within the receiver's
capability. In this case, any intercharacter adjustment is not applied.
The measurement units used by the medium and those used by the coded font selected for presentation are
assumed to be compatible. If they are not compatible, the standard action, when not superseded by the
controlling environment, is to present a best fit of the character and continue processing. A best fit could result
in no mark or unintelligible marks on the presentation surface. However, incompatible measurement units are
not an exception condition in PTOCA. [PTOCA-4-376]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1E01...The LID is missing. [PTOCA-4-377]
* EC-0C01...The value of the LID is not supported or is not in the range specified by PTOCA. [PTOCA-4-378]
* EC-1802...A font mapping has not been provided. [PTOCA-4-379]
* EC-1802...The coded font specified by the mapping is not available to the receiver. [PTOCA-4-380]
* EC-3F02...The specified coded font is not compatible with the text orientation. [PTOCA-4-381]
SCFL Control Sequence [PTOCA-4-382]

<!-- Page 106 -->

## Set Encrypted Alternate (SEA)
The Set Encrypted Alternate control sequence contains the alternate text as a series of code points to be used
if the decryption of the encrypted bytes in the ENC control fails. This data is not scanned for embedded control
sequences. Alternate text will be used when the encrypted bytes in ENCDATA cannot be decrypted, either
because: decryption is not available on the target device, the decryption fails during processing, or the
KEYINFO key information required for decryption has not been defined using the Set Key Information (SKI)
control.
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-383]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-384]
2 UBIN LENGTH 6, 7–255 Control sequence length M N N [PTOCA-4-385]
3 CODE TYPE X'9C' – X'9D' Control sequence [PTOCA-4-386]
function type
M N N
4-7 Reserved; should be zero M N N [PTOCA-4-387]
8-256 CHAR ALTTEXT Not [PTOCA-4-388]
applicable
Alternate text to be used
if decryption fails
O N N
The contents of ALTTEXT (if specified) are a character string of code points. ALTTEXT does not accept the
default indicator, but X'F....F' is valid. [PTOCA-4-389]
### Semantics
Alternate text is processed when an ENC decryption cannot be performed and ALTTEXT is substituted (see
the ENC Semantics for a description of the details). [PTOCA-4-390]
### Pragmatics
Given that the length of the alternative text can exceed the space available within a single SEA control, a
method to specify more than 249 bytes for alternate text is provided. SEA controls that are consecutive and
part of the same control sequence chain have their ALTTEXT fields concatenated together to form alternate
text that can be much longer than 249 bytes. Consecutive, in this case, means the SEA controls have no
intervening PTOCA controls between them.
Application Note: The Set Encrypted Alternate (SEA) control sequence is modal, but is not an initial text
condition. For text major text, the SEA is not changed by AFP presentation servers when a MO:DCA
BPT structured field is encountered, and therefore later text on the same page will inherit any SEA set
previously on the page.
ALTTEXT is initially set to no value, meaning that in the event of a failure to decrypt the encrypted data, no text
will be substituted. If ALTTEXT is defined using a SEA control, it can be reset back to no value by specifying an
SEA with the ALTTEXT field omitted (LENGTH = 6). An SEA with LENGTH=6 resets the ALTTEXT back to no
value even if the SEA has other consecutive SEAs either before or after it. Consecutive SEAs before it, if any,
are eff ectively ignored, while consecutive SEAs after it, if any, begin a new ALTTEXT definition.
The alternate text specified by ALTTEXT will not be verified until it is used by the ENC control. Refer to the
description of the ENC control for a description of exception conditions that can occur within
ALTTEXT data.
SEA Control Sequence

<!-- Page 107 -->

If the value of the LENGTH field is less than 6, exception condition EC-1E01 exists. The standard action is to
ignore the control sequence and continue processing. [PTOCA-4-391]
### Exception Conditions
This control sequence can cause the following exception condition:
* EC-1E01...LENGTH is not valid. [PTOCA-4-392]
SEA Control Sequence

<!-- Page 108 -->

## Set Extended Text Color (SEC)
The Set Extended Text Color control sequence specifies a color value and defines the color space and
encoding for that value. The specified color value is applied to foreground areas of the text presentation space.
Foreground areas consist of the following:
* The stroked and filled areas of solid text characters, including overstrike characters; with hollow characters, [PTOCA-4-393]
only the stroked portion of the character is considered foreground.
* The stroked area of a rule [PTOCA-4-394]
* The stroked area of an underscore [PTOCA-4-395]
All other areas of the text presentation space are considered background.
This is a modal control sequence.
Note: Colors may be specified using the Set Text Color (STC) or the Set Extended Text Color (SEC) control
sequences. Both STC and SEC can coexist in the same text object. The last issued control sequence
determines the current text color in accordance with the rules defined for modal control sequences. For
a definition of modal control sequences, see “Modal Control Sequences”. [PTOCA-4-396]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-397]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-398]
2 UBIN LENGTH 14–16 Control sequence length M N N [PTOCA-4-399]
3 CODE TYPE X'80' – X'81' Control sequence [PTOCA-4-400]
function type
M N N
4 Reserved; should be zero M N N [PTOCA-4-401]
5 CODE COLSPCE Color space M N N [PTOCA-4-402]
X'01'
X'04'
X'06'
X'08'
X'40'
RGB
CMYK
Highlight color space
CIELAB
Standard OCA color
space
6–9 Reserved; should be zero M N N
10 UBIN COLSIZE1 X'01' – X'08', [PTOCA-4-403]
X'10'
Number of bits in
component 1, see color
space definitions
M N N
1 1 UBIN COLSIZE2 X'00' – X'08' Number of bits in [PTOCA-4-404]
component 2, see color
space definitions
M N N
12 UBIN COLSIZE3 X'00' – X'08' Number of bits in [PTOCA-4-405]
component 3, see color
space definitions
M N N
SEC Control Sequence

<!-- Page 109 -->

Offset Type Name Range Meaning M/O Def Ind
13 UBIN COLSIZE4 X'00' – X'08' Number of bits in [PTOCA-4-406]
component 4, see color
space definitions
M N N
14–(n-1) COL Value See [PTOCA-4-407]
### Semantics
for details
Color specifications M N N [PTOCA-4-408]
### Semantics
COLSPCE is a code that defines the color space and the encoding for the color specification. If the color space
is invalid or unsupported, exception condition EC-0E02 exists. The standard action is to use the device default
color.
Value Description
X'01' RGBcolor space. The color value is specified with three components. Components 1, 2, and 3
are unsigned binary numbers that specify the red, green, and blue intensity values, in that
order. COLSIZE1, COLSIZE2, and COLSIZE3 are non-zero and define the number of bits
used to specify each component. COLSIZE4 is reserved and should be set to zero. The
intensity range for the R, G, and Bcomponents is 0 to 1, which is mapped to the binary value
range 0 to (2
ColSizeN
- 1), where N=1,2,3. [PTOCA-4-409]
Architecture Note: The reference white point and the chromaticity coordinates for RGB are
defined in SMPTE RP 145-1987 entitled Color Monitor Colorimetry and RP 37-1969
entitled Color T emperature for Color T elevision Studio Monitors, respectively. The
reference white point is commonly known as Illuminant D
6500
or simply D65. The R, G,
and Bcomponents are assumed to be gamma-corrected (nonlinear) with a gamma of
2.2.
X'04' CMYK color space. The color value is specified with four components. Components 1, 2, 3,
and 4 are unsigned binary numbers that specify the cyan, magenta, yellow, and black intensity
values, in that order. COLSIZE1, COLSIZE2, COLSIZE3, and COLSIZE4 are non-zero and
define the number of bits used to specify each component. The intensity range for the C, M, Y,
and K components is 0 to 1, which is mapped to the binary value range 0 to (2
ColSizeN
- 1),
where N=1,2,3,4. This is a device-dependent color space.
X'06' Highlight color space. This color space defines a request for the presentation device to
generate a highlight color. The color value is specified with one to three components.
Component 1 is a two-byte unsigned binary number that specifies the highlight color number.
The first highlight color is assigned X'0001', the second highlight color is assigned X'0002',
and so on. The value X'0000' specifies the presentation device default color. COLSIZE1 =
X'10' and defines the number of bits used to specify component 1.
Component 2 is an optional one-byte unsigned binary number that specifies a percent
coverage for the specified color. Percent coverage can be any value from 0% to 100% (X'00'–
X'64'). The number of distinct values supported is device-dependent. If the coverage is less
than 100%, the remaining coverage is achieved with color of medium. COLSIZE2 = X'00' or
X'08' and defines the number of bits used to specify component 2. A value of X'00' indicates
that component 2 is not specified in the color value, in which case the architected default for
percent coverage is 100%. A value of X'08' indicates that component 2 is specified in the color
value.
Component 3 is an optional one-byte unsigned binary number that specifies a percent
shading, which is a percentage of black that is to be added to the specified color. Percent
shading can be any value from 0% to 100% (X'00'–X'64'). The number of distinct values
supported is device-dependent. If percent coverage and percent shading are specified, the
effe ctive range for percent shading is 0% to (100-coverage)%. If the sum of percent coverage
SEC Control Sequence

<!-- Page 110 -->

plus percent shading is less than 100%, the remaining coverage is achieved with color of
medium. COLSIZE3 = X'00' or X'08' and defines the number of bits used to specify component
3. A value of X'00' indicates that component 3 is not specified in the color value, in which case [PTOCA-4-410]
the architected default for percent shading is 0%. A value of X'08' indicates that component 3
is specified in the color value.
Implementation Note: The percent shading parameter is currently not supported in AFP
environments.
If the percent value for component 2 or component 3 is invalid, exception condition EC-0E04
exists. The standard action is to use the maximum valid percent value.
COLSIZE4 is reserved and should be set to zero. This is a device-dependent color space.
Architecture Notes:
1. The color that is rendered when a highlight color is specified is device dependent. For [PTOCA-4-411]
presentation devices that support colors other than black, highlight color values in the
range X'0001' to X'FFFF' may be mapped to any color. For bi-level devices, the color may
be simulated with a graphic pattern. In addition, presentation devices may not support the
% coverage and % shading parameters for highlight colors in PTOCA text. In that case,
these parameters are simulated with 100% coverage and 0% shading, respectively.
2. If the specified highlight color is 'presentation device default', devices whose default color [PTOCA-4-412]
is black use the percent coverage parameter, which is specified in component 2, to render
a percent shading.
3. On printing devices, the color of medium is normally white, in which case a coverage of n [PTOCA-4-413]
% results in adding (100-n)% white to the specified color, or tinting the color with (100-n)%
white. Display devices may assume the color of medium to always be white and use this
algorithm to render the specified coverage.
4. The highlight color space can also specify indexed colors when used in conjunction with a [PTOCA-4-414]
Color Mapping Table (CMT) or an Indexed (IX) Color Management Resource (CMR).
When used with an Indexed CMR, component 1 specifies a two-byte value that is the
index into the CMR, and components 2 and 3 are ignored. Note that when both a CMT
and Indexed CMRs are used, the CMT is always accessed first. To preserve compatibility
with existing highlight color devices, indexed color values X'0000' – X'00FF' are reserved
for existing highlight color applications and devices. That is, indexed colors values in the
range X'0000' – X'00FF', assuming they are not mapped to a different color space in a
CMT, are mapped directly to highlight colors. Indexed color values in the range X'0100' –
X'FFFF', assuming they are not mapped to a different color space in a CMT, are used to
access Indexed CMRs. For a description of the Color Mapping Table and Indexed CMRs
in MO:DCA environments, see the Mixed Object Document Content Architecture
Reference, AFPC-0004.
X'08' CIELABcolor space. The color value is specified with three components. Components 1, 2,
and 3 are binary numbers that specify the L, a, b values, in that order, where L is the
luminance and a and b are the chrominance dif ferences. Component 1 specifies the L value
as an unsigned binary number; components 2 and 3 specify the a and b values as signed
binary numbers. COLSIZE1, COLSIZE2, and COLSIZE3 are non-zero and define the number
of bits used to specify each component. COLSIZE4 is reserved and should be set to zero. The
range for the L component is 0 to 100, which is mapped to the binary value range 0 to (2
ColSize1
- 1). The range for the a and b components is -127 to +127, which is mapped to the binary [PTOCA-4-415]
range -(2
ColSizeN-1
- 1) to +(2 [PTOCA-4-416]
ColSizeN-1
- 1).
For color fidelity, 8-bit encoding should be used for each component, that is, ColSize1,
ColSize2, and ColSize3 are set to X'08'. When the recommended 8-bit encoding is used for
the a and b components, the range is extended to include -128, which is mapped to the value
X'80'. If the encoding is less than 8 bits, treatment of the most negative binary endpoint for the
a and b components is device-dependent, and tends to be insignificant due to the quantization
error.
SEC Control Sequence

<!-- Page 111 -->

Architecture Note: The reference white point for CIELAB is known as D50 and is defined in
CIE publication 15-2 entitled Colorimetry.
X'40' Standard OCA color space. The color value is specified with one component. Component 1 is
an unsigned binary number that specifies a named color using a two-byte value from the
Standard OCA Color Value table. For a complete description of the Standard OCA Color Value
Table, see the Mixed Object Document Content Architecture Reference, AFPC-0004.
COLSIZE1 = X'10' and defines the number of bits used to specify component 1. COLSIZE2,
COLSIZE3, COLSIZE4 are reserved and should be set to zero. This is a device-dependent
color space. The following table defines the valid color values used to specify named colors.
The table also specifies the RGB values that can be used for each named color, assuming that
each component is specified with 8 bits and that the component intensity range 0 to 1 is
mapped to the binary value range 0 to 255.
Table 12. SEC Color Values
Value Color Red
(R)
Green
(G)
Blue
(B)
X'0000' or X'FF00' Presentation-process default;
see Note 1
X'0001' or X'FF01' Blue 0 0 255
X'0002' or X'FF02' Red 255 0 0
X'0003' or X'FF03' Pink/Magenta 255 0 255
X'0004' or X'FF04' Green 0 255 0
X'0005' or X'FF05' T urquoise/cyan 0 255 255
X'0006' or X'FF06' Y ellow 255 255 0
X'0007' White; see Note 2 255 255 255
X'0008' Black 0 0 0
X'0009' Dark blue 0 0 170
X'000A' Orange 255 128 0
X'000B' Purple 170 0 170
X'000C' Dark green 0 146 0
X'000D' Dark turquoise 0 146 170
X'000E' Mustard 196 160 32
X'000F' Gray 131 131 131
X'0010' Brown 144 48 0
X'FF07' Presentation-process default;
see Note 3
— — —
SEC Control Sequence

<!-- Page 112 -->

Table 12 SEC Color Values (cont'd.)
Value Color Red
(R)
Green
(G)
Blue
(B)
X'FF08' Color of medium
— — —
Notes:
1. The presentation-process default specified by X'0000' and X'FF00' is resolved as follows: [PTOCA-4-417]
* For PTOCA text data, it is the presentation device default [PTOCA-4-418]
2. The color rendered on presentation devices that do not support white is device-dependent. For [PTOCA-4-419]
example, some printers simulate with color of medium, which results in white if white media is
used.
3. The presentation-process default specified by X'FF07' is resolved as the presentation device [PTOCA-4-420]
default. This color value is also known in GOCA as neutral white for compatibility with display
devices.
4. The value X'FFFF' is not defined in the Standard OCA Color Value Table but is used by some [PTOCA-4-421]
objects as a default indicator as follows:
* For PTOCA text data, X'FFFF' may be specified in the Set Text Color (STC) control sequence to [PTOCA-4-422]
indicate that the PTOCA default hierarchy is used to generate the color value. Note that X'FFFF'
is not supported in the Set Extended Text Color (SEC) control sequence.
5. While the RGB values in the table can be used to render the OCA named colors, many [PTOCA-4-423]
implementations are and have been device-dependent. Nevertheless, it is recommended that
OCA Black (X'0008') be rendered as C = M = Y = X'00', and K = X'FF'.
All others Reserved
COLSIZE1 defines the number of bits used to specify the first color component. The color component is right-
aligned and padded with zeros on the left to the nearest byte boundary. For example, if COLSIZE1 = X'06', the
first color component has two padding bits. If the specified value is invalid or unsupported, exception condition
EC-0E05 exists. The standard action is to use the device default color.
COLSIZE2 defines the number of bits used to specify the second color component. The color component is
right-aligned and padded with zeros on the left to the nearest byte boundary. If the specified value is invalid or
unsupported, exception condition EC-0E05 exists. The standard action is to use the device default color.
COLSIZE3 defines the number of bits used to specify the third color component. The color component is right-
aligned and padded with zeros on the left to the nearest byte boundary. If the specified value is invalid or
unsupported, exception condition EC-0E05 exists. The standard action is to use the device default color.
COLSIZE4 defines the number of bits used to specify the fourth color component. The color component is
right-aligned and padded with zeros on the left to the nearest byte boundary. If the specified value is invalid or
unsupported, exception condition EC-0E05 exists. The standard action is to use the device default color.
COLOR Value specifies the color value in the defined format and encoding. If the color value is invalid or
unsupported, exception condition EC-0E03 exists. The standard action is to use the device default color.
Note that the number of bytes specified for this parameter depends on the color space. For example, when
using 8 bits per component, an RGBcolor value is specified with 3 bytes, while a CMYK color value is
specified with 4 bytes. If extra bytes are specified, they are ignored as long as the control sequence length is
valid.
Architecture Note: For a description of color spaces and their relationships, see Hunt, R., The Reproduction
of Colour in Photography, Printing, and T elevision, Fifth Edition, Fountain Press, 1995.
SEC Control Sequence

<!-- Page 113 -->

The subset may limit the parameter ranges permitted in this control sequence. For detailed information about
subsets, please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”,
on, and Appendix B, “IPDS Environment”,. See “Related Publications” for
data-stream documentation. [PTOCA-4-424]
### Pragmatics
If the receiver does not support the specified color value exception condition EC-0E03 exists. The standard
action in this case is to use the presentation device default color. [PTOCA-4-425]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-0E02...Invalid or unsupported color space. [PTOCA-4-426]
* EC-0E03...Invalid or unsupported color value. [PTOCA-4-427]
* EC-0E04...Invalid percent value. [PTOCA-4-428]
* EC-0E05...Invalid or unsupported number of bits in a color component. [PTOCA-4-429]
SEC Control Sequence

<!-- Page 114 -->

## Set Intercharacter Adjustment (SIA)
The Set Intercharacter Adjustment control sequence specifies additional increment or decrement between
graphic characters. This is a modal control sequence. [PTOCA-4-430]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-431]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-432]
2 UBIN LENGTH 4–5 Control sequence length M N N [PTOCA-4-433]
3 CODE TYPE X'C2' – X'C3' Control sequence [PTOCA-4-434]
function type
M N N
4–5 SBIN ADJSTMNT X'0000' –
X'7FFF'
Adjustment M Y Y
6 CODE DIRECTION X'00' – X'01' Direction O Y Y [PTOCA-4-435]
ADJSTMNT is a positive binary number expressed in measurement units. The range for this parameter
assumes a measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit,
please see the conversion routine described in “Interpreting Ranges”. The PTOCA default value for
ADJSTMNT is X'0000'. DIRECTION is a code with no measurement units. The PTOCA default value for
DIRECTION is X'00'.
### Semantics
ADJSTMNT specifies the value of additional space between graphic characters. This space is in the I-direction
from the end of the current character increment to the presentation position of the following graphic character.
When this value is positive, the adjustment is called an increment. When the value is negative, the adjustment
is called a decrement. DIRECTION specifies the direction in which the intercharacter adjustment is to be
applied. Intercharacter increment, which occurs when DIRECTION is X'00', is applied in the positive I-direction.
Intercharacter decrement, which occurs when DIRECTION is X'01', is applied in the negative I-direction. This
control sequence does not change the current presentation position.
For graphic characters following each other:
$I_{cnew}$ = $I_c$
+ ADJSTMNT + CI
For a graphic character following a RMI, AMI, BLN control sequence or following a space character or
variable space character:
$I_{cnew}$ = $I_c$
+ CI
For a non-incrementing character:
$I_{cnew}$ = $I_c$
For the variable space character:
$I_{cnew}$ = $I_c$
+ VSI
In all cases:
$B_{cnew}$ = $B_c$
If intercharacter adjustment is valid for a given graphic character, it is applied before presenting the character.
If it is an incrementing character, the current inline coordinate is incremented first by the intercharacter
adjustment, and then by the character increment. The same is true for a non-incrementing character, but the
intercharacter adjustment is inhibited for the character that follows the non-incrementing character. The result
SIA Control Sequence

<!-- Page 115 -->

is that the non-incrementing character is coupled with the following graphic character. This accomplishes an
overstrike function, and the intercharacter adjustment is applied to the coupled characters as a unit.
Intercharacter adjustment is not applied before or after the following:
* A space character or variable space character [PTOCA-4-436]
* A Begin Line control sequence [PTOCA-4-437]
* A Relative Move Inline control sequence [PTOCA-4-438]
* An Absolute Move Inline control sequence [PTOCA-4-439]
Non-presenting characters are graphic characters except when identified as the designated variable space
character.
Intercharacter adjustment is inserted between the characters that form a word, but not between words. That is,
intercharacter adjustment is applied only when in inword mode. Inword mode is entered after any incrementing
character has been processed. Inword mode is exited after any word delimiter has been processed. The
following are word delimiters:
* The space character or variable space character [PTOCA-4-440]
* Begin Line control sequences [PTOCA-4-441]
* Relative Move Inline control sequences [PTOCA-4-442]
* Absolute Move Inline control sequences [PTOCA-4-443]
Application Note: The following code points are normally used for the variable space character:
* X'40' in EBCDIC single-byte code pages [PTOCA-4-444]
* X'20' in ASCII single-byte code pages [PTOCA-4-445]
* X'4040' in EBCDIC double-byte code pages [PTOCA-4-446]
* X'2020' in ASCII double-byte code pages [PTOCA-4-447]
The following code points are used for the variable space character in TrueType/OpenType fonts that
use a Unicode (UTF-16) encoding:
* X'0020' [PTOCA-4-448]
* X'00A0' [PTOCA-4-449]
If the value of ADJSTMNT is the default indicator, a value is obtained from the hierarchy. If the value of
DIRECTION is the default indicator, a value is obtained from the hierarchy. Please see Table 9.
If the value of ADJSTMNT or DIRECTION is not supported or is not within the range specified by PTOCA,
exception condition EC-1201 exists. The standard action is to ignore this control sequence and continue
presentation with the parameter values determined according to the hierarchy.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-450]
### Pragmatics
If the value of ADJSTMNT is zero, no additional intercharacter increment or decrement appears between
graphic characters. In this case, DIRECTION is optional, and the SIA control sequence does not change the
following:
* The current presentation position [PTOCA-4-451]
* The current I-unit value [PTOCA-4-452]
* The current inline margin [PTOCA-4-453]
* The current intercharacter increment value [PTOCA-4-454]
* The current intercharacter decrement value [PTOCA-4-455]
SIA Control Sequence

<!-- Page 116 -->

### Exception Conditions
This control sequence can cause the following exception condition:
* EC-1201...The value of ADJSTMNT or DIRECTION is not supported or is not in the range specified by [PTOCA-4-456]
PTOCA.
SIA Control Sequence

<!-- Page 117 -->

## Set Inline Margin (SIM)
The Set Inline Margin control sequence specifies the position of an inline margin. This is a modal control
sequence.
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-457]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-458]
2 UBIN LENGTH 4 Control sequence length M N N [PTOCA-4-459]
3 CODE TYPE X'C0' – X'C1' Control sequence [PTOCA-4-460]
function type
M N N
4–5 SBIN DSPLCMNT X'0000' –
X'7FFF'
Displacement M Y Y
DSPLCMNT is a positive binary number expressed in measurement units. The range for this parameter
assumes a measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit,
please see the conversion routine described in “Interpreting Ranges”. The PTOCA default value for
DSPLCMNT is the B-axis, that is, $I_c$
is zero.
### Semantics
This control sequence specifies a displacement, DSPLCMNT, from the B-axis in the I-direction that is to be
applied when a Begin Line control sequence is processed in the current Presentation Text object. If the value
of DSPLCMNT is the default indicator, a value is obtained from the hierarchy. Please see Table 9.
If DSPLCMNT is omitted, exception condition EC-1E01 exists. The standard action is to ignore the control
sequence and continue presentation with the Inline Margin that was in effect prior to this control sequence. If
the value of DSPLCMNT is not supported or is not within the range specified by PTOCA, exception condition
EC-1001 exists. The standard action is to ignore this control sequence and continue presentation with the
value determined according to the hierarchy.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation.
This control sequence does not change the current addressable position. [PTOCA-4-461]
### Pragmatics
The current addressable position is at the inline margin after each Begin Line control sequence is executed. If
the value of DSPLCMNT is zero, the inline margin is at the B-axis. [PTOCA-4-462]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1E01...DSPLCMNT is missing. [PTOCA-4-463]
* EC-1001...The value of DSPLCMNT is not supported or is not in the range specified by PTOCA. [PTOCA-4-464]
SIM Control Sequence

<!-- Page 118 -->

## Set Key Information (SKI)
The Set Key Information control sequence provides encryption key information to be used with Encrypted Data
(ENC) controls. This is a modal control sequence. [PTOCA-4-465]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-466]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-467]
2 UBIN LENGTH 6, 7–255 Control sequence length M N N [PTOCA-4-468]
3 CODE TYPE X'9A' – X'9B' Control sequence [PTOCA-4-469]
function type
M N N
4–7 Reserved; should be zero M N N
8–256 UNDF KEYINFO Any value Key information O N N
The data type of the contents of KEYINFO is UNDF (undefined). KEYINFO does not accept the default
indicator, but X'F....F' is valid. [PTOCA-4-470]
### Semantics
KEYINFO is a collection of bytes that are used by the decryption method to perform decryption on encrypted
text specified in the ENC control. The contents and length of this field will vary based on the encryption
implementation, with different bytes used for diffe rent things. For example, when a Hardware Security Module
(HSM) is used, the key information might consist of a slot ID, key name, key label, and so forth. Diffe rent
decryption implementations might use diffe rent information.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-471]
### Pragmatics
If the value of the LENGTH field is less than 6, exception condition EC-1E01 exists. The standard action is to
ignore the control sequence and continue processing.
If the output device does not support decryption, exception condition EC-9D01 exists. The standard action is to
ignore the control sequence and continue processing.
Architecture Note: Some IPDS printers, under control of an environment-specific text fidelity control, will allow
reporting and continuation to be controlled when an ENC is encountered by a printer that cannot decrypt
text strings.
Application Note: The Set Key information (SKI) control sequence is modal, but is not an initial text condition.
For text major text, the SKI is not changed by AFP presentation servers when a MO:DCA BPT structured
field is encountered, and therefore later text on the same page will inherit any SKI set previously on the
page.
Given the large variety of diffe rent decryption devices, a method to specify more than 249 bytes for decryption
information (if required) is provided. SKI controls that are consecutive and part of the same control sequence
SKI Control Sequence

<!-- Page 119 -->

chain have their KEYINFO fields concatenated together to form key information that can be much longer than
249 bytes. Consecutive, in this case, means the SKI controls have no intervening PTOCA controls between [PTOCA-4-472]
them. Every single SKI control on its own, or every set of consecutive SKI controls, is handled as a normal
modal control, and simply replaces the previous encryption key information, for use in subsequent ENC
controls.
Note: It is poor security practice for the data bytes in KEYINFO to contain the encryption key used to originally
encrypt the data, since including these bytes in the print stream defeats the purpose of encrypting it in
the first place.
KEYINFO is initially set to no value. If KEYINFO is defined, it can be reset back to no value by specifying a SKI
with the KEYINFO field omitted (LENGTH = 6). A SKI with LENGTH=6 resets the KEYINFO field back to no
value even if the SKIhas other consecutive SKIs either before or after it. Consecutive SKIs before it, if any, are
effe ctively ignored, while consecutive SKIs after it, if any, begin a new KEYINFO definition. [PTOCA-4-473]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1E01...LENGTH is not valid. [PTOCA-4-474]
* EC-9D01...Decryption is not available on this device. [PTOCA-4-475]
SKI Control Sequence

<!-- Page 120 -->

## Set Text Color (STC)
The Set Text Color control sequence specifies a color attribute for the foreground areas of the text presentation
space. Foreground areas consist of the following:
* The stroked and filled areas of solid text characters, including overstrike characters; with hollow characters, [PTOCA-4-476]
only the stroked portion of the character is considered foreground.
* The stroked area of a rule [PTOCA-4-477]
* The stroked area of an underscore [PTOCA-4-478]
All other areas of the text presentation space are considered background.
This is a modal control sequence.
Note: Colors may be specified using the Set Text Color (STC) or the Set Extended Text Color (SEC) control
sequences. Both STC and SEC can coexist in the same text object. The last issued control sequence
determines the current text color in accordance with the rules defined for modal control sequences. For
a definition of modal control sequences, see “Modal Control Sequences”. [PTOCA-4-479]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-480]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-481]
2 UBIN LENGTH 4, 5 Control sequence length M N N [PTOCA-4-482]
3 CODE TYPE X'74' – X'75' Control sequence [PTOCA-4-483]
function type
M N N
4–5 CODE FRGCOLOR See [PTOCA-4-484]
### Semantics
section
Foreground color M Y Y
6 Retired parameter, see [PTOCA-4-485]
Architecture Note, also
see “Retired Parameters”
on.
O
The PTOCA default value for FRGCOLOR is X'FF07'. Please see the pragmatics section for further details.
Architecture Note: Pre-year 2000 applications and printers support an optional PRECISION parameter in byte
6. This parameter has been retired. It should not be generated by new applications, and should be [PTOCA-4-486]
ignored by new printers. For a definition of this parameter, see “Retired Parameters”. [PTOCA-4-487]
### Semantics
The FRGCOLOR parameterspecifies a color value. Syntactically valid values for specifying colors are X'0000'
through X'0010' and X'FF00' through X'FF08', which is the range of values defined in the Standard OCA Color
Value Table. An additional valid value is X'FFFF', which is the default indicator and specifies that the color
value is obtained from the hierarchy. Please see the Pragmatics section, as well as Table 9. The
PTOCA default value for FRGCOLOR is X'FF07'. For a definition of the Standard OCA Color Value Table, see
the Mixed Object Document Content Architecture Reference, AFPC-0004.
If the color is not supported, or if the FRGCOLOR value is not syntactically valid, exception condition EC-5803
exists. The standard action in this case is to use X'FF07'.
STC Control Sequence

<!-- Page 121 -->

The following table defines the valid color values used to specify named colors. The table also specifies the
RGB values that can be used for each named color, assuming that each component is specified with 8 bits and
that the component intensity range 0 to 1 is mapped to the binary value range 0 to 255.
Table 13. STC Color Values
Value Color Red
(R)
Green
(G)
Blue
(B)
X'0000' or X'FF00' Presentation-process default; see Note
1
X'0001' or X'FF01' Blue 0 0 255
X'0002' or X'FF02' Red 255 0 0
X'0003' or X'FF03' Pink/Magenta 255 0 255
X'0004' or X'FF04' Green 0 255 0
X'0005' or X'FF05' T urquoise/cyan 0 255 255
X'0006' or X'FF06' Y ellow 255 255 0
X'0007' White; see Note 2 255 255 255
X'0008' Black 0 0 0
X'0009' Dark blue 0 0 170
X'000A' Orange 255 128 0
X'000B' Purple 170 0 170
X'000C' Dark green 0 146 0
X'000D' Dark turquoise 0 146 170
X'000E' Mustard 196 160 32
X'000F' Gray 131 131 131
X'0010' Brown 144 48 0
X'FF07' Presentation-process default; see Note
3
— — —
X'FF08' Color of medium
— — —
X'FFFF' Default indicator
— — —
Notes:
1. The presentation-process default specified by X'0000' and X'FF00' is resolved as follows: [PTOCA-4-488]
* For PTOCA text data, it is the presentation device default [PTOCA-4-489]
2. The color rendered on presentation devices that do not support white is device-dependent. For example, some [PTOCA-4-490]
printers simulate with color of medium, which results in white if white media is used.
3. The presentation-process default specified by X'FF07' is resolved as the presentation device default. This color [PTOCA-4-491]
value is also known in GOCA as neutral white for compatibility with display devices.
4. The value X'FFFF' is not defined in the Standard OCA Color Value Table but is used by some objects as a default [PTOCA-4-492]
indicator as follows:
* For PTOCA text data, X'FFFF' may be specified in the Set Text Color (STC) control sequence to indicate that the [PTOCA-4-493]
PTOCA default hierarchy is used to generate the color value. Note that X'FFFF' is not supported in the Set
Extended Text Color (SEC) control sequence.
5. While the RGB values in the table can be used to render the OCA named colors, many implementations are and [PTOCA-4-494]
have been device-dependent. Nevertheless, it is recommended that OCA Black (X'0008') be rendered as C = M = Y
= X'00', and K = X'FF'.
STC Control Sequence

<!-- Page 122 -->

The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-495]
### Pragmatics
The presentation process default color attribute value (FRGCOLOR = X'FFFF') is determined hierarchically.
The following order applies:
1. Value set by Text Color initial text condition parameter in descriptor [PTOCA-4-496]
2. PTOCA default X'FF07' [PTOCA-4-497]
The device default value is the receiver ’ s default. For example, characters, rules, and underscores will be
presented in black on a receiver which supports only black. The receiver ’ s best possible value means that if
the receiver has limited color capabilities, then it may substitute a color it supports for one it does not support.
For example, the receiver may substitute red for pink, blue for turquoise, and so forth.
The color attribute values X'FF00' to X'FF06' are translated to X'0000' to X'0006' and treated exactly like those
colors. The PTOCA default value of X'FF07', is the device default. For example, characters, rules, and
underscores will be presented in black on a device which supports only black. A color attribute value of X'FF08'
means that the receiver's default background color should be used for the foreground color. This provides an
erase function.
### Exception Conditions
This control sequence can cause the following exception condition:
* EC-5803...The value of FRGCOLOR is invalid, or the specified color is not supported. [PTOCA-4-498]
Architecture Notes:
1. The MO:DCA environment supports a Color Mapping Table (CMT) that may be used to map colors in a [PTOCA-4-499]
PTOCA object to other colors. When a CMT is active, valid FRGCOLOR values are mapped to their target
values. The retired PRECISION parameter, if supported, is processed for the target values.
2. The IPDS environment allows a presentation device to implement limited simulated color support for [PTOCA-4-500]
PTOCA text and rules. When the printer is working in a mode where color simulation is allowed, all valid
but unsupported color values are accepted and result in a device-dependent simulation of the specified
color without the generation of exception EC-5803.
STC Control Sequence

<!-- Page 123 -->

Set Text Orientation (STO)
The Set Text Orientation control sequence establishes the I-direction and B-direction for the subsequent text.
This is a modal control sequence. [PTOCA-4-501]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-502]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-503]
2 UBIN LENGTH 6 Control sequence length M N N [PTOCA-4-504]
3 CODE TYPE X'F6' – X'F7' Control sequence [PTOCA-4-505]
function type
M N N
4–5 CODE IORNTION See [PTOCA-4-506]
### Semantics
section
I-axis orientation M Y Y
6–7 CODE BORNTION See [PTOCA-4-507]
### Semantics
section
B-axis orientation M Y Y
The PTOCA default for IORNTION is zero. The PTOCA default for BORNTION is 90. [PTOCA-4-508]
### Semantics
This control sequence specifies the I-axis and B-axis orientations with respect to the $X_p$
-axis for the current
Presentation Text object. The orientations are rotational values expressed in degrees and minutes. IORNTION
and BORNTION have the same format. Each is a two-byte, three-part binary code of the form ABC.
* A is a nine-bit binary number (bits 0 - 8) which provides from 0 through 359 degrees. Values from 360 [PTOCA-4-509]
through 51 1 are invalid.
* B is a six-bit binary number (bits 9 - 14) which provides from 0 through 59 minutes. Values from 60 through [PTOCA-4-510]
63 are invalid. [PTOCA-4-511]
* C is a one-bit reserved field (bit 15) which must be 0. [PTOCA-4-512]
The maximum value for IORNTION and BORNTION is X'B3F6' or B'101 1001 1 1 1 1 101 10', which is 359 degrees
and 59 minutes. Increasing values indicate increasing clockwise rotation of the I,B axes with respect to the $X_p$
,
$Y_p$
axes. A value of 0 for IORNTION indicates that there is no rotation relative to the $X_p$
-axis. That is, positive I-
direction is parallel to the $X_p$
-axis.
The origin of the I,B axes is always one of the four corners of the object space. If the text orientation is
changed, this origin may also change. See Figure 12 for the location of the I,Borigin for the eight
text orientations that are supported by the PT1, PT2, and PT3 subsets. For example, if IORNTION and
BORNTION are 0,90 or 90,0, the origin of the I,B axes is at the upper left corner, or origin, of the object space.
This is where $X_p$
= 0 and $Y_p$
= 0. If IORNTION and BORNTION are 180,90 or 90,180, the origin of the I,B axes
is at the upper right corner of the object space. This is where $X_p$
= $X_p$
-extent, and $Y_p$
= 0.
STO Control Sequence

<!-- Page 124 -->

Figure 12. Location of I,B Origin
If the inline direction changes or the origin of the B-axis changes, the inline margin also changes. The new
inline margin is parallel to the new B-axis, and it is displaced in the new I-direction from the new B-axis
according to the value of the Inline Margin parameter. If the value of IORNTION or BORNTION is the default
indicator, a value is obtained from the hierarchy. Please see Table 9.
If IORNTION or BORNTION is omitted, exception condition EC-1E01 exists. The standard action is to assume
an orientation with the I-axis parallel to the $X_p$
-axis, and the B-axis parallel to the $Y_p$
-axis. If IORNTION and
BORNTION are identical, exception condition EC-0F01 exists. The standard action is to assume an orientation
with the I-axis parallel to the $X_p$
-axis, and the B-axis parallel to the $Y_p$
-axis.
### Pragmatics
When the text orientation is changed, the text appears to rotate about the current presentation position, which
is an $X_p$
,$Y_p$
coordinate. Please see Figure 13 for examples of text orientation and character
rotation. When such a change occurs, the current presentation position is not changed, but the values of $I_c$
and
$B_c$
are adjusted to correspond to the current presentation position relative to the new orientation.
If neither the I-direction nor the B-direction is parallel to the $X_p$
-direction or the $Y_p$
-direction, exception condition
EC-0F01 exists. The standard action is to set the I-direction parallel to the $X_p$
-direction and the B-direction
parallel to the $Y_p$
-direction.
Orientations other than 0,90 are valid, but may be constrained by receiver limitations or by parameters in the
controlling environment. If the orientation is not supported by the receiver, exception condition EC-0F01 exists.
The standard action is to use 0,90 degrees for the orientation. This exception condition and standard action
also apply to values for IORNTION and BORNTION not within the range specified by PTOCA.
Architecture Notes:
1. The following remain as previously specified: [PTOCA-4-513]
* The current presentation position, an $X_p$,$Y_p$ coordinate, [PTOCA-4-514]
* The current I-unit value, [PTOCA-4-515]
* The current inline margin, [PTOCA-4-516]
* The current intercharacter increment value, [PTOCA-4-517]
* The current intercharacter decrement value, [PTOCA-4-518]
* The current B-unit value, [PTOCA-4-519]
* The current baseline increment value, [PTOCA-4-520]
* The current coded font. [PTOCA-4-521]
STO Control Sequence

<!-- Page 125 -->

2. The following will change: [PTOCA-4-522]
* The $X_p$ [PTOCA-4-523]
- $Y_p$
-axis from which inline margin is measured (that is, the inline margin appears to rotate).
* Font character rotations appropriate to the new orientation are used. [PTOCA-4-524]
* Presentation position should be respecified if subsequent text is to be positioned elsewhere in the [PTOCA-4-525]
Presentation Text space.
* Other modal parameter values should be respecified if they are more appropriate to the new orientation. [PTOCA-4-526]
* A new coded font should be specified: [PTOCA-4-527]
– If the current coded font is not valid in the new text orientation,
– If it is desired that the graphic characters be rotated in proper orientation with respect to the new
baseline.
3. If the Presentation Text object measurement units specified for the $X_p$ [PTOCA-4-528]
-axis are different from the
measurement units specified for the $Y_p$
-axis, the result of a Set Text Orientation control sequence may be
unexpected and use of a Set Text Orientation control sequence should be avoided. [PTOCA-4-529]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1E01...IORNTION or BORNTION is missing. [PTOCA-4-530]
* EC-0F01...IORNTION and BORNTION are identical. [PTOCA-4-531]
* EC-0F01...IORNTION or BORNTION not parallel to $X_p$ [PTOCA-4-532]
-axis or $Y_p$
-axis.
* EC-0F01...IORNTION and BORNTION not supported by receiver. [PTOCA-4-533]
Figure 13. Examples of Text Orientation and Character Rotation
STO Control Sequence

<!-- Page 126 -->

## Set V ariable Space Character Increment (SVI)
The Set V ariable Space Character Increment control sequence specifies the increment for a variable space
character. This is a modal control sequence. [PTOCA-4-534]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-535]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-536]
2 UBIN LENGTH 4 Control sequence length M N N [PTOCA-4-537]
3 CODE TYPE X'C4' – X'C5' Control sequence [PTOCA-4-538]
function type
M N N
4–5 SBIN INCRMENT X'0000' –
X'7FFF'
Increment M Y Y
INCRMENT is a positive number expressed in measurement units. The range for this parameter assumes a
measurement unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the
conversion routine described in “Interpreting Ranges”. [PTOCA-4-539]
### Semantics
This control sequence specifies an increment, INCRMENT, for the variable space character. The increment is
in the I-direction from the presentation position of the variable space character to the addressable position for
the next graphic character or control sequence for subsequent text in the current Presentation Text object. This
control sequence does not change the current presentation position.
To return to the current coded font's default value for the variable space character increment, set INCRMENT
to the default indicator. If the current coded font does not have such a default value, INCRMENT is set to the
character increment for the default variable space character.
If INCRMENT is omitted, exception condition EC-1E01 exists. The standard action is to continue with the
presentation using the standard action value for the variable space character increment. If the value of
INCRMENT is not supported or is not within the range specified by PTOCA, exception condition EC-1701
exists. The standard action is to ignore this control sequence and continue presentation with the value
determined according to the hierarchy. Please refer to the Pragmatics section for details.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-540]
### Pragmatics
The inline coordinate of the presentation position is incremented by INCRMENT after each variable space
character is processed. Each variable space character causes the presentation position to move in the I-
direction by the amount of the variable space character increment. If the value of INCRMENT is zero, no
variable space appears between words, and intercharacter adjustment is not applied even though the resulting
characters appear side-by-side. When a Set V ariable Space Increment control sequence is received, the new
value of INCRMENT is saved and is applied to any subsequent variable space character received.
SVI Control Sequence

<!-- Page 127 -->

The code point used for the variable space character is specified, either implicitly or explicitly, by the active
font. In this case, the default value for INCRMENT is the value specified by the active font in its current
orientation. The value is obtained in this order:
1. The current variable space character increment [PTOCA-4-541]
2. The default variable space character increment of the active coded font [PTOCA-4-542]
3. The character increment of the default variable space character code point [PTOCA-4-543]
When the value of INCRMENT changes because of changes in the font or the SVIcontrol sequences, the new
value is carried but is not used until the variable space character is enabled.
The variable space character increment is not effe ctive for other graphic characters that are not presented, nor
for graphic characters that make no marks.
Architecture Note: The following remain as previously specified:
* The current presentation position, an $X_p$,$Y_p$ coordinate [PTOCA-4-544]
* The current I-unit value [PTOCA-4-545]
* The current inline margin, [PTOCA-4-546]
* The current intercharacter increment value [PTOCA-4-547]
* The current intercharacter decrement value [PTOCA-4-548]
* The current B-unit value [PTOCA-4-549]
* The current baseline increment value [PTOCA-4-550]
* The current coded font [PTOCA-4-551]
Application Note: The following code points are normally used for the variable space character:
* X'40' in EBCDIC single-byte code pages [PTOCA-4-552]
* X'20' in ASCII single-byte code pages [PTOCA-4-553]
* X'4040' in EBCDIC double-byte code pages [PTOCA-4-554]
* X'2020' in ASCII double-byte code pages [PTOCA-4-555]
The following code points are used for the variable space character in TrueType/OpenType fonts that
use a Unicode (UTF-16) encoding:
* X'0020' [PTOCA-4-556]
* X'00A0' [PTOCA-4-557]
Application Note: The Set V ariable Space Character Increment (SVI) control sequence is modal, but is not an
initial text condition. For text major text, SVI is not reset by AFP presentation servers when a MO:DCA
BPT structured field is encountered, and therefore later text on the same page will inherit any SVI set
previously on the page. If no SVI is specified in a page, then the default variable space character
increment of the active coded font is used. [PTOCA-4-558]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1E01...INCRMENT is missing [PTOCA-4-559]
* EC-1701...The value of INCRMENT is not supported or is not in the range specified by PTOCA.EC-1E01EC- [PTOCA-4-560]
1701
SVI Control Sequence

<!-- Page 128 -->

1 10 PTOCA Reference [PTOCA-4-561]
Temporary Baseline Move (TBM)
The Temporary Baseline Move control sequence changes the position of the baseline without changing the
established baseline. [PTOCA-4-562]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-563]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-564]
2 UBIN LENGTH 3, 4, 6 Control sequence length M N N [PTOCA-4-565]
3 CODE TYPE X'78' – X'79' Control sequence [PTOCA-4-566]
function type
M N N
4 CODE DIRECTION X'00' – X'03' Direction M Y Y [PTOCA-4-567]
5 BITS PRECISION X'00' – X'01' Precision O Y Y [PTOCA-4-568]
6–7 SBIN INCRMENT X'0000' –
X'7FFF'
Temporary baseline
increment
O Y Y
INCRMENT is a positive number expressed in measurement units. The range for this parameter assumes a
measurement unit of 1/1440 inch. If it is necessary to convert to different measurement unit, please see the
conversion routine described in “Interpreting Ranges”. The PTOCA default value for INCRMENT is
1/2 the baseline increment value. The PTOC A default value for DIRECTION and PRECISION is zero. [PTOCA-4-569]
### Semantics
This control sequence does one of the following:
* Change the current baseline coordinate by the amount specified by INCRMENT in the direction specified by [PTOCA-4-570]
DIRECTION
* Return the baseline coordinate to the established baseline coordinate position [PTOCA-4-571]
This control sequence does not change the position of the established baseline coordinate or the inline
presentation position. INCRMENT specifies the magnitude of the temporary baseline increment. PRECISION
specifies the method by which the receiver exhibits the change in the baseline coordinate. DIRECTION specifies
the direction of the change.
DIRECTION is a bit-encoded value that specifies the following:
Value Meaning
X'00' Do not change the baseline.
X'01' Return to the established baseline. Delete the temporary baseline created by TBM control
sequences.
X'02' Move the temporary baseline away from the I-axis one value of INCRMENT, performing a
subscript function. The increment is applied to the current baseline coordinate, not to the
established baseline, and has no effect on the established baseline.
X'03' Move the temporary baseline toward the I-axis one value of INCRMENT, performing a
superscript function. The increment is applied to the current baseline coordinate, not to the
established baseline, and has no effect on the established baseline.
TBM Control Sequence

<!-- Page 129 -->

The following equations apply to DIRECTION:
If DIRECTION = 0:
$B_{cnew}$ = $B_c$
If DIRECTION = 1:
$B_{cnew}$ = $B_{est}$
If DIRECTION = 2:
$B_{cnew}$ = $B_c$
+ INCRMENT
If DIRECTION = 3:
$B_{cnew}$ = $B_c$
- INCRMENT [PTOCA-4-572]
In all cases:
$I_{cnew}$ = $I_c$
PRECISION is bit-encoded as follows.
Bits 0-6
Bits 0-6 are reserved. They must be set to B'0' by generators, and ignored by receivers.
Bit 7
If bit 7 is B'0', the receiver must accurately place and represent the character using the coded font that is active
when the control sequence is executed. In this case, the movement of the baseline is not simulated, and the
character presented on the shifted baseline is the same as the characters used in the surrounding text. That is,
it is not a character specially designed for subscripting or superscripting. However, this does not prohibit
changing the coded font. If this bit is B'0', the intent is to ensure that the receiver has the word processing
capability of producing formal documents.
If bit 7 is B'1', the movement of the baseline may be simulated using specially designed subscript or
superscript characters which appear smaller than the surrounding text.
If the value of INCRMENT, PRECISION, or DIRECTION is the default indicator, a value is obtained from the
hierarchy. Please see Table 9.
If the value of INCRMENT, PRECISION, or DIRECTION is not supported or is not within the range specified by
PTOCA, exception condition EC-9803 exists. The standard action is to ignore this control sequence and
continue presentation with the value determined according to the hierarchy. Please see the Pragmatics section
for details.
The subset may limit the range permitted in this control sequence. For detailed information about subsets,
please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:DCA Environment”, on page
163, and Appendix B, “IPDS Environment”,. See “Related Publications” for data-
stream documentation. [PTOCA-4-573]
### Pragmatics
* How TBM operates: [PTOCA-4-574]
Changing the baseline coordinate with this control sequence does not modify the position of the
established baseline coordinate.
Once the baseline coordinate has been changed by a TBM control sequence, it will remain at the new
location until it is terminated by another TBM control sequence or the end of the Presentation Text object.
After processing a TBM control sequence, additional TBM control sequences are processed relative to the
temporary baseline coordinate position, not the established baseline coordinate position. That is, a second
TBM with magnitude and direction equal to the first TBM causes the temporary baseline coordinate to be
TBM Control Sequence

<!-- Page 130 -->

1 12 PTOCA Reference [PTOCA-4-575]
moved farther from the established baseline coordinate. A second TBM with magnitude equal to the first
but opposite direction cancels the effect of the first TBM and terminates the temporary baseline function.
The Temporary Baseline Increment parameter is modal. That is, once it is set, it remains set until it is
changed by another TBM control sequence. It is not necessary to generate a TBM control sequence in
order to give this parameter a value. The parameter is required only if its value is to be changed. If a TBM
control sequence does not include INCRMENT or specifies the default indicator for INCRMENT, the default
value is used. The default is 1/2 the current baseline increment.
The temporary baseline may be canceled, that is, reset to the established baseline coordinate position, by
specifying the “Return to Established Baseline” function. This is specified by setting DIRECTION equal to
X'01'. If a TBM control sequence is processed with DIRECTION set to X'01' when a temporary baseline has
not been established, the result is a no-operation. If a TBM control sequence specifies the Return to
Established Baseline function but includes the Temporary Baseline Increment parameter INCRMENT, the
baseline coordinate is returned to the established baseline coordinate position, and INCRMENT is
changed to the new value specified. PRECISION has no effect on the Return to Established Baseline
function.
The temporary baseline function is terminated if the temporary baseline coordinate coincides with the
established baseline coordinate, that is, when the established baseline and the current baseline are equal
at the end of processing the TBM control sequence. Therefore, creating a temporary baseline on one side
of the established baseline followed by another temporary baseline on the other side of the established
baseline without terminating the temporary baseline field is possible by changing the value of INCRMENT.
* How TBM affects other control sequences: [PTOCA-4-576]
The temporary baseline field is not canceled by any other control sequences. Here are some examples:
A Begin Line control sequence is processed relative to the established baseline coordinate position, not
the temporary baseline coordinate position. Following the processing of a Begin Line control sequence,
new baseline coordinate positions are determined. The Baseline Increment is added to the current
baseline coordinate position to provide a new current baseline position. The Baseline Increment is also
added to the established baseline coordinate position to provide a new established baseline position.
Following the processing of a Begin Line control sequence, the temporary baseline is continued until a
terminating TBM control sequence ends the temporary baseline function.
A Relative Move Baseline control sequence is processed relative to the established baseline coordinate
position, not the temporary baseline coordinate position. Following the processing of a Relative Move
Baseline control sequence, new baseline coordinate positions are determined. The relative baseline
increment is added to the current baseline coordinate position to provide a new current baseline position.
The relative baseline increment is also added to the established baseline coordinate position to provide a
new established baseline position. Following execution of a Relative Move Baseline control sequence, the
temporary baseline is continued until a terminating TBM control sequence ends the temporary baseline
function.
* How TBM uses the PRECISION parameter: [PTOCA-4-577]
PRECISION specifies how the receiver should exhibit characters at the temporary baseline. There is an
actual placement method and a substitution method. If PRECISION is X'01', a receiver may simulate the
temporary baseline move by substituting subscript or superscript graphics for graphics actually positioned
below or above the established baseline. This is the substitution method. If PRECISION is X'00', the
receiver is required to support the physical shift in the baseline coordinate, and may not substitute special
graphics for those specified at the temporary baseline. This is the actual placement method. Receivers
must support one of these two methods of presentation.
Receivers that implement the substitution method should support the other aspects of the TBM control
sequence just as if an actual shift in the baseline coordinate had occurred. That is, the character increment
of the active font, the current baseline, the established baseline, and the baseline increment must all be
maintained, just as though the characters being used were from the active font and the shift of the current
baseline had actually occurred.
TBM Control Sequence

<!-- Page 131 -->

If a receiver implements only the substitution method and PRECISION is set to X'00', exception condition
EC-9803 exists. The standard action is to perform the substitution method. Furthermore, if a receiver using
the substitution method cannot generate the necessary substitution character, exception condition EC-
9803 exists. The standard action is to present the requested character at the established baseline [PTOCA-4-578]
coordinate. It is not an exception if a TBM control sequence with PRECISION set to X'01' is encountered by
a receiver which implements only the actual placement method. If a receiver supports the substitution
method in addition to the actual placement method, setting PRECISION to X'01' causes selection of the
substitution method.
PRECISION is not a modal parameter. However, if another TBM control sequence that does not terminate
the temporary baseline function is received, and PRECISION is not specified, then the current value of
PRECISION is assumed. If the value of PRECISION is changed from X'00' to X'01' or from X'01' to X'00', the
precision change must be honored according to the rules for PRECISION as indicated in the semantics.
* How TBM relates to underscore and overstrike: [PTOCA-4-579]
For receivers that support the actual placement method, the baseline position of characters resulting from
the underscore function is the established baseline. The baseline position of characters resulting from the
overstrike function is the current baseline, that is, the overstriking characters follow the temporary baseline.
If an overstrike or underscore function continues after the temporary baseline function is terminated, no
exception condition exists. The corresponding function continues relative to the current baseline, which is
equal to the established baseline. For receivers that support only the substitution method, the overstrike
and underscore functions occur relative to the established baseline.
* Miscellaneous TBM exception conditions: [PTOCA-4-580]
A receiver that supports the actual placement method for the TBM control sequence establishes the
maximum temporary baseline displacement that it can support. If the Temporary Baseline Increment
parameter exceeds the limits supported by the receiver, exception condition EC-9803 exists. The standard
action is to use the established maximum limit that was exceeded as the standard action value for this
parameter.
More than one TBM control sequence without an intervening termination, such as a TBM of equal
magnitude and opposite direction, or a TBM specifying Return to Established Baseline, is called multi-
offsetting. Multi-offsetting support is required by PTOCA for receivers that support the actual placement
method. It is not required to know the number of multi-offsets that have been accepted.
Since receivers that support the substitution method do not physically create the temporary baseline, they
cannot support multi-offset. If a multi-offset TBM is received by a substitution method receiver, exception
condition EC-9803 exists. The standard action is to present according to the substitution method.
If processing a TBM causes any portion of a character box on the current baseline to exceed the
boundaries of the object space and presentation is attempted, exception condition EC-0103 exists. The
standard action is to continue processing without presenting characters until the addressable point
specified is valid for use as a presentation position, and then start presenting again. While processing
without presenting characters, the current presentation position is maintained as though the affe cted
characters were being presented. [PTOCA-4-581]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-9803...The value of INCRMENT, PRECISION, or DIRECTION is not supported or is not in the range [PTOCA-4-582]
specified by PTOCA.
* EC-9803...The PRECISION parameterspecifies the actual placement method but the receiver does not [PTOCA-4-583]
support it.
* EC-9803...A receiver using the substitution method cannot generate the required substitution character. [PTOCA-4-584]
* EC-9803...For a receiver that uses the actual placement method, the INCRMENT parameter exceeds the [PTOCA-4-585]
physical limit.
* EC-9803...A multi-offset TBM control sequence is received by a receiver that uses the substitution method. [PTOCA-4-586]
TBM Control Sequence

<!-- Page 132 -->

1 14 PTOCA Reference [PTOCA-4-587]
* EC-0103...The control sequence will cause part of a character's character box to be outside of the object [PTOCA-4-588]
space, and presentation is attempted.
TBM Control Sequence

<!-- Page 133 -->

Transparent Data (TRN)
The Transparent Data control sequence contains a sequence of code points that are presented without a scan
for embedded control sequences. [PTOCA-4-589]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-590]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-591]
2 UBIN LENGTH 2–255 Control sequence length M N N [PTOCA-4-592]
3 CODE TYPE X'DA' – X'DB' Control sequence [PTOCA-4-593]
function type
M N N
4–256 CHAR TRNDATA Not
applicable
Transparent data O N N
The contents of TRNDATA are unknown. TRNDATA does not accept the default indicator, but X'F....F' is valid. [PTOCA-4-594]
### Semantics
This control sequence specifies a string of code points, all of which are to be processed as graphic characters.
No code point within the data field is recognized as a Control Sequence Prefix. The current inline position is
incremented for each graphic character in the string.
For graphic characters following each other:
$I_{cnew}$ = $I_c$
+ ADJSTMNT + CI
Intervening non-incrementing characters are ignored.
For graphic characters following RMI, AMI, BLN control sequences or following a space character or variable
space character:
$I_{cnew}$ = $I_c$
+ CI
Intervening non-incrementing characters are ignored.
For the variable space character:
$I_{cnew}$ = $I_c$
+ VSI
For a non-incrementing character:
$I_{cnew}$ = $I_c$
In all cases:
$B_{cnew}$ = $B_c$
### Pragmatics
No absolute move, relative move, baseline positioning, or other immediate or modal function is available within
TRNDATA. If code points representing control sequences are processed within TRNDATA, they are presented
as graphic characters, and the active coded font determines whether any character shapes appear on the
presentation surface. If a Transparent Data control sequence causes any part of a character's character box to
exceed the object space, exception condition EC-0103 exists. The standard action is not to present the
character that exceeds the object space, and continue processing without presenting characters until the
presentation position is returned to an addressable position within the object space that is a valid presentation
position for the character being presented. Then presentation of characters may resume.
TRN Control Sequence

<!-- Page 134 -->

1 16 PTOCA Reference [PTOCA-4-595]
The data length must be an even number for double-byte fonts. If the Transparent Data control sequence
length is an odd number when a double-byte font is active, exception condition EC-1A01 exists. The standard
action is to process the Transparent Data control sequence up to the last byte, skip the odd byte, and continue
processing.
If the character encoding is Unicode but the code points in the TRN are not valid Unicode data, exception
condition EC-1A03 exists. The standard action is to skip the remainder of the code points in the TRN and
continue processing.
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-0103...The control sequence will cause part of a character's character box to be outside the object [PTOCA-4-596]
space, and presentation is attempted.
* EC-1A01...The control sequence length is an odd number, but a double-byte font is active. [PTOCA-4-597]
* EC-1A03...Invalid Unicode data. This can be caused by one of the following: [PTOCA-4-598]
– A high-order surrogate code value was not immediately followed by a low-order surrogate code value. The
high-order surrogate range is U+D800 through U+DBFF.
– A low-order surrogate code value was not immediately preceded by a high-order surrogate code value.
The low-order surrogate range is U+DC00 through U+DFFF.
– An illegal UTF-8 byte sequence, as defined in the Unicode 3.2 Specification, was specified. For more
information on illegal UTF-8 byte sequences, see Table 15.
TRN Control Sequence

<!-- Page 135 -->

## Underscore (USC)
The Underscore control sequence identifies text fields that are to be underscored. [PTOCA-4-599]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-600]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-601]
2 UBIN LENGTH 3 Control sequence length M N N [PTOCA-4-602]
3 CODE TYPE X'76' – X'77' Control sequence [PTOCA-4-603]
function type
M N N
4 BITS BYPSIDEN See [PTOCA-4-604]
### Semantics
section
Bypass identifiers M Y Y
BYPSIDEN is a binary field with no measurement units. The PTOCA default is X'01', which means no bypass
is in effect.
### Semantics
Underscore is accomplished with a pair of USC control sequences. Underscore is activated with a beginning
USC with a non-zero value for BYPSIDEN bits 4-7. Underscore is deactivated with an ending USC with a zero
value for BYPSIDEN bits 4-7. This control sequence immediately precedes the field of text to be underscored,
which is called the underscore field. The control sequence specifies that text bounded by the two USC control
sequences is to be underscored, and it specifies which controlled inline white space within that text is to be
underscored.
The underscore field is delimited by a beginning USC control sequence and either an ending USC control
sequence or the end of the Presentation Text object. The underscore field is a sequential string of text, that is,
graphic characters or control sequences.
BYPSIDEN specifies which controlled inline white space within the underscore field is to be underscored.
Controlled inline white space is that area of the presented line that contains no visible material due to
movement of the presentation position in the I-direction caused by the following:
* Absolute Move Inline control sequence [PTOCA-4-605]
* Relative Move Inline control sequence [PTOCA-4-606]
* Space character or variable space character [PTOCA-4-607]
Application Note: The following code points are normally used for the variable space character:
* X'40' in EBCDIC single-byte code pages [PTOCA-4-608]
* X'20' in ASCII single-byte code pages [PTOCA-4-609]
* X'4040' in EBCDIC double-byte code pages [PTOCA-4-610]
* X'2020' in ASCII double-byte code pages [PTOCA-4-611]
The following code points are used for the variable space character in TrueType/OpenType fonts that
use a Unicode (UTF-16) encoding:
* X'0020' [PTOCA-4-612]
* X'00A0' [PTOCA-4-613]
USC Control Sequence

<!-- Page 136 -->

1 18 PTOCA Reference [PTOCA-4-614]
Movement of the current inline position in the I-direction to or through a presentation position that already
contains material to be underscored creates controlled inline white space for the entire move in the I-direction.
Not all inline white space is controlled. White space resulting from non-printing characters (other than space
characters or variable space characters) within the character set, from substitution of non-printing characters
for unsupported characters, from intercharacter adjustment, or from the inline margin is not considered
controlled inline white space.
Inline moves that cause the current addressable position to move in a direction opposite to the inline direction,
that is, in the negative inline direction, do not cause underscore.
BYPSIDEN is bit encoded as follows.
BIT MEANING
0-3 Reserved, that is, set to 0 by generators and ignored by receivers [PTOCA-4-615]
4 Bypass Relative Move Inline [PTOCA-4-616]
5 Bypass Absolute Move Inline [PTOCA-4-617]
6 Bypass space characters and variable space characters [PTOCA-4-618]
7 No Bypass in effect [PTOCA-4-619]
Bits 0-3, Reserved
Reserved bits are set to 0 by generators and ignored by receivers.
Bit 4, Bypass Relative Move Inline
A value of B'0' in this bit indicates that the controlled white space generated as a result of a Relative Move
Inline control sequence is to be underscored. A value of B'1' in this bit indicates that such controlled white
space is not to be underscored. It should be bypassed.
Bit 5, Bypass Absolute Move Inline
A value of B'0' in this bit indicates that the controlled white space generated as a result of an Absolute Move
Inline control sequence is to be underscored. A value of B'1' in this bit indicates that such controlled white
space is not to be underscored. It should be bypassed.
Bit 6, Bypass space characters
A value of B'0' in this bit indicates that the controlled white space generated as a result of space characters or
variable space characters is to be underscored. A value of B'1' in this bit indicates that such controlled white
space is not to be underscored. It should be bypassed.
Bit 7, No Bypass in Effect
A value of B'0' in this bit activates the other bypass flags. A value of B'1' in this bit indicates that the other
bypass flags are overridden, and that all text and white space bounded by the USC pair should be
underscored. If the value of BYPSIDEN is the default indicator, a value is obtained from the hierarchy. Please
see Table 9.
Implementation Note: Most IPDS printers have implemented X'FF' as the default indicator, which results in
BYPSIDEN = X'01' - no bypass in effect. However, it could be argued that the proper default indicator is
X'0F', since BYPSIDEN bits 0-3 are reserved, should be set to zero by generators, and should be
ignored by receivers. To avoid confusion, it is strongly recommended that a default indicator not be used
for this parameter, and that the value X'01' is specified directly if the default is desired.
An underscore area is that portion of the underscore field for which text is actually underscored. An underscore
area is delimited by the addressable position in the following cases.
* A beginning USC [PTOCA-4-620]
USC Control Sequence

<!-- Page 137 -->

* Either end of bypassed controlled inline white space [PTOCA-4-621]
* Either end of a baseline move, which may be for the established baseline or for a temporary baseline [PTOCA-4-622]
* The beginning of negative changes in the presentation position caused by inline moves or negative [PTOCA-4-623]
intercharacter adjustments
* Boundaries where violation causes truncation [PTOCA-4-624]
* An ending USC [PTOCA-4-625]
* The end of the Presentation Text object [PTOCA-4-626]
The dimension in the positive I-direction of the underscore field is defined by the minimum and maximum I-
coordinates specified between the underscore area delimiters. White space resulting from the application of
the inline margin is underscored only if this area is entered by means of an inline move. [PTOCA-4-627]
### Pragmatics
The underscore area must be underscored with a solid line. For each character to be underscored, the solid
line must extend for the entire character box and for any intercharacter adjustment. The solid line may not
extend past the character box for the final character in the underscore field. That is, the solid line may extend
up to but not include the current presentation position, $I_c$
.
Underscore does not occur in the negative I-direction. If there is negative intercharacter adjustment, the
underscore is applied from the beginning of the character reference point, after it is placed, to a point which is
one character increment from there in the positive I-direction. If the negative intercharacter adjustment is large
enough to move the presentation position of the next character to a point which precedes the previous
character's presentation position by more than the character increment of the next character, then an
underscore beginning at the reference point of the next character will cause a gap in the underscore, as shown
in Figure 14.
Figure 14. Example of Intercharacter Increment in Underscore
Multiple beginning and ending USC pairs may not be nested. However, no exception condition exists if a
beginning USC control sequence is processed when another USC control sequence is already active. The
subsequent beginning USC terminates the previous USC and starts another. If an ending USC is encountered
when there has been no previous USC, no exception condition exists. Ignore the ending USC. If a Presentation
Text object contains a beginning USC without a matching ending USC, no exception condition exists.
T erminate the USC at the end of the Presentation Text object.
There is no provision in the USC control sequence to specify a coded font. It is assumed that the receiver can
underscore. Underscore positioning is determined by the active coded font. If the active coded font is changed
USC Control Sequence

<!-- Page 138 -->

within the underscore field, discontinuity of the underscore, such as mismatched lines, different line weights, or
multiple lines, could result. However, this does not constitute a violation of the requirement for a solid line.
If an STO control sequence changes the text orientation within an underscore field, the position of the
underscore is still determined by the active coded font. Please see Figure 15.
Figure 15. Relationship of Underscore to Changes in Font, Orientation, and Rotation
Typically an STO control sequence is accompanied by an SCFL control sequence that specifies the
appropriate coded font to use in the new orientation. The coded font specified by the SCFL determines where
the underscore is positioned in the new orientation. The requirement for a solid line is not violated as long as
the underscore extends for the character increment and any intercharacter adjustment. For some
combinations of text orientation and character rotation, valid underscore might be discontinuous.
An underscore area is delimited by a beginning USC, bypassed controlled inline white space, and either an
ending USC or the end of the Presentation Text object. Additionally, the dimension of this area in the I-direction
is defined by the minimum and maximum I-coordinates specified between the underscore delimiters.
There are no syntactic restrictions on the occurrence of Begin Line, Absolute Move Baseline, Relative Move
Baseline, and Temporary Baseline Move control sequences within an underscore field.
USC Control Sequence

<!-- Page 139 -->

Characters occurring at a temporary baseline coordinate are underscored at the established baseline
coordinate position.
Color is not a parameter of this control sequence.
Exception Condition
This control sequence can cause the following exception condition:
* None
USC Control Sequence

<!-- Page 140 -->

## Unicode Complex Text (UCT)
Architecture Note: The recommended method for rendering Unicode complex text is to use GLC chains. With
that method, the UCT may optionally be specified within a GLC chain as a carrier for metadata that
specifies the original Unicode code points and control information describing how those code points may
be rendered. In that usage, the UCT does not cause any characters to be rendered. Applications can
use the UCT in GLC chains to “virtually“ render the code points in order to better correlate substrings of
glyph runs with their corresponding code points. The use of the UCT as a stand-alone control sequence
to render complex text has not been implemented widely and is not supported in IPDS environments.
The Unicode Complex Text (UCT) control sequence marks the start of a sequence of Unicode code points.
This sequence starts with the first byte following the end of the UCT control sequence and ends with the last
byte identified by the complex text length parameter in the control sequence. All bytes in this sequence are
processed as code points without a scan for embedded control sequences. There is no odd function type
defined for the UCT, therefore the UCT can be part of a chain of control sequences but will always terminate
the chain.
If the active font is a data-object font and the data is encoded in a Unicode-based character encoding such as
UTF-8 or UTF-16, the code points in the text that follows UCT can be processed as Unicode complex text.
Under the following conditions the code points cannot be processed as Unicode complex text and instead are
processed by the presentation device as if they were within a TRN control sequence, in which case all UCT
control information is ignored:
* the active font is not a data-object font [PTOCA-4-628]
* the data is not encoded in a Unicode-based character encoding [PTOCA-4-629]
* the writing mode is vertical, as determined by a font character rotation of 90° or 270° [PTOCA-4-630]
The UCT can be used in two ways.
1. When the UCT is specified within a GLC chain, that is when it is chained from a GAR or a GOR, it is used [PTOCA-4-631]
to identify the code points rendered by the glyphs in the GLC chain. In that case, the code points following
the UCT are not rendered, since the corresponding glyph IDs are rendered by the GLC chain. How much of
the UCT controls are processed by the receiver to correlate code points to glyphs is device-dependent. If
bidi processing and glyph processing are enabled within the UCT, a receiver may choose to process all of
the UCT controls to actually lay out the code points in order to more closely associate code points with
rendered glyphs. Or a receiver may choose to ignore all of the UCT controls, in which case the association
of code points with glyph IDs can be more coarse. Processing of the UCT has no effect on the current
inline position, $I_c$
, or on any other PTOCA environment controls.
2. When the UCT is specified outside a GLC chain (a “stand-alone“ UCT), it is used to process and render the [PTOCA-4-632]
code points that follow.
Architecture Note: The processing and rendering of a UCT outside a GLC chain is not part of the PTOC A
PT4 function set. Not all presentation devices support this function; consult the appropriate product
documentation. IPDS printers generate an exception when a UCT is specified outside a GLC chain.
Some IPDS printers, under control of an environment-specified text fidelity control, will render the
UCT code points in a one-to-one manner.
Rendering complex text involves two types of special processing:
* bidirectional (bidi) layout processing. When the writing mode is horizontal, as determined by a character [PTOCA-4-633]
rotation in the active font of 0° or 180°, characters are presented on the current PTOCA baseline in a
direction determined by the Unicode bidi algorithm. For a description of the Unicode bidi algorithm, see
http://unicode.org/reports/tr9. The direction can be left-to-right (L→R), right-to-left (R→L), or a
combination. Characters are presented at the character rotation specified in the active font, and the
appropriate horizontal metrics are used to position the glyphs. See Table 14 for a description of
UCT character positioning.
UCT Control Sequence

<!-- Page 141 -->

Application Note: It is strongly recommended that the logical (storage) order of Unicode text strings be
preserved when such strings are placed into text objects. This will allow the text, when carried in an
interchange format such as a MO:DCA document, to be interchanged among applications that support
text processing functions such as searching, sorting, and indexing. If the logical order is modified,
which might be tempting as an aid to formatting, the capability to apply such text processing functions
is lost.
* glyph processing. Characters may require language-specific shaping such as arabic character shaping, [PTOCA-4-634]
composition/decomposition, and position adjustments. The rendering is no longer one code point to one
character to one glyph, and requires the assistance of a Unicode layout engine.
Implementation Note: In AFP environments, the ICU ParagraphLayout API is used to apply Unicode bidi
layout processing and glyph processing to complex text. This API is developed by the International
Components for Unicode (ICU) project, which is jointly managed by a group of companies and
individuals throughout the world; see http://site.icu-project.org.
Complex text processing for UCT text is enabled or disabled by controls in the UCT. If both bidi layout
processing and glyph processing are disabled, the code points in the text that follows UCT are not processed
as complex text and instead are processed as if they were contained within a TRN control sequence.
If either bidi layout processing or glyph processing is to be applied, the sequence of Unicode code points must
first be normalized. This involves, for example, replacing composite character sequences with their equivalent
composed character. The Unicode normalization format used in PTOCA objects is Normalization Form C
(NFC). If the normalization step was not applied by the formatter that generated the complex text, it is applied
by the presentation device before bidi layout processing or glyph processing is applied.
Architecture Note: The Unicode character encoding is defined in the Unicode Standard, which is available
from the Unicode Consortium at http://unicode.org/standard/standard.html. [PTOCA-4-635]
### Syntax
Offset Type Name Range Meaning M/O Def Ind
0 CODE PREFIX X'2B' Control Sequence Prefix M N N [PTOCA-4-636]
1 CODE CLASS X'D3' Control sequence class M N N [PTOCA-4-637]
2 UBIN LENGTH X'10' Control sequence length M N N [PTOCA-4-638]
3 CODE TYPE X'6A' Control sequence function type M N N [PTOCA-4-639]
4 CODE UCTVERS X'01' UCT version level [PTOCA-4-640]
X'01' Base level
M N N
5 Reserved; should be zero M N N [PTOCA-4-641]
6–7 UBIN CTLNGTH 0 – 32,767 Length of complex text data that follows
this control sequence
M N N
8 BITS CTFLGS See [PTOCA-4-642]
### Semantics
section
Complex text processing control flags M N N
9 Reserved; should be zero M N N [PTOCA-4-643]
UCT Control Sequence

<!-- Page 142 -->

10 CODE BIDICT X'02', X'04', [PTOCA-4-644]
X'05', X'12',
X'20', X'22',
X'23'
Bidi layout processing control:
X'02' Enable; default p.d. is
L→R
X'04' Enable; set p.d. L→R
X'05' Enable; set p.d. R→L
X'12' Enable; p.d. set from
previous UCT;
default L→R
X'20' Disable
X'22' Disable; t.d. L→R
X'23' Disable; t.d. R→L
M N N
1 1 CODE GLYPHCT X'01', X'20' Glyph processing control: [PTOCA-4-645]
X'01' Enable
X'20' Disable
M N N
12 –
15
Reserved; should be zero M N N
16 –
17
SBIN ALTIPOS X'8000' –
X'7FFF'
Alternate current inline position M N N
Table Note: The terms 'p.d.' and 't.d.' stand for paragraph direction and text direction, respectively.
ALTIPOS is a signed binary number in measurement units.The range for ALTIPOS assumes a measurement
unit of 1/1440 inch. If it is necessary to convert to a different measurement unit, please see the conversion
routine described in “Interpreting Ranges”. [PTOCA-4-646]
### Semantics
This control sequence marks the start of a string of code points, all of which are to be processed as graphic
characters. No code point within the marked string of text is recognized as a Control Sequence Prefix. This
string of code points is also called “UCT text”.
If the code points in the text that follows the UCT control sequence cannot be processed as Unicode complex
text, or if glyph processing and bidi processing are disabled, the code points are processed as if they were
contained in a TRN control sequence. In that case, the current inline position is incremented for each graphic
character in the string as follows:
* For graphic characters following each other: [PTOCA-4-647]
$I_{cnew}$ = $I_c$
+ ADJSTMNT + CI
and intervening non-incrementing characters are ignored.
* For graphic characters following RMI, AMI, or BLN control sequences or following a space character or [PTOCA-4-648]
variable space character:
$I_{cnew}$ = $I_c$
+ CI
and intervening non-incrementing characters are ignored.
* For the variable space character: [PTOCA-4-649]
$I_{cnew}$ = $I_c$
+ VSI
* For a non-incrementing character: [PTOCA-4-650]
$I_{cnew}$ = $I_c$
* In all cases: [PTOCA-4-651]
$B_{cnew}$ = $B_c$
UCT Control Sequence

<!-- Page 143 -->

If the code points in the text that follows the UCT control sequence can be processed as Unicode complex text,
and if glyph processing or bidi processing is enabled, glyph positions and advances are determined with the
aid of a Unicode layout engine. In that case the advancement of the current text position is no longer a direct
function of the individual character increments. Additionally, the optional placement of text at the alternate
inline position may dictate the new text position.
If CTFLGS bit 3 = B'0' - advance $I_c$
, the current text position $I_{cnew}$
at the completion of a UCT is then generated
as follows:
* If $I_c$ [PTOCA-4-652]
was used to position the UCT text:
$I_{cnew}$ = $I_c$
+ sum{G
I
}
* If I
a
was used to position the UCT text:
$I_{cnew}$ = I
a
If CTFLGS bit 3 = B'1' - maintain $I_c$
, the current text position $I_{cnew}$
at the completion of a UCT is given by:
$I_{cnew}$ = $I_c$
Where:
* $I_c$
= the current text position at the start of UCT processing
* I
a
= the alternate text position at the start of UCT processing
* G
I
= the increment for a grapheme in the UCT
* sum = summation over all the graphemes that were presented for the UCT [PTOCA-4-653]
UCTVERS specifies the functional level of the complex text support in the UCT.
Value Definition
X'01' Base level of complex text support
All others Reserved
CTLNGTH specifies the total number of bytes in the sequence of code points that follows UCT; that is, it
specifies the length of the text that follows UCT. The bytes identified by this parameter are processed as code
points and are presented without a scan for embedded control sequences.
CTFLGS is a bit-encoded parameter that specifies controls for processing the Unicode complex text code
points that follow, and is defined as follows:
BIT MEANING
0 Normalization [PTOCA-4-654]
1 Alternate inline position (I [PTOCA-4-655]
a
) valid
2 Alternate inline position (I [PTOCA-4-656]
a
) coordinates
3 Maintain current inline position ($I_c$ [PTOCA-4-657]
)
4 Reset paragraph direction [PTOCA-4-658]
5-7 Reserved, that is, set to B'0' by generators and ignored by receivers [PTOCA-4-659]
Bit 0, Normalization
A value of B'0' in this bit indicates that the Unicode complex text code points that follow have not been
normalized. The presentation device will apply a normalization step as long as bidi layout processing or glyph
processing (or both) is to be applied. If neither bidi layout processing or glyph processing is to be applied, the
normalization step is not applied. The normalization format generated is Normalization Form C (NFC). A value
of B'1' in this bit indicates that the Unicode complex text code points that follow have been normalized by the
generator of the text object. No additional normalization is applied by the presentation device. The
normalization format assumed is Normalization Form C (NFC).
Reserved bits are set to B'0' by generators and ignored by receivers.
Architecture Note: The definition of Normalization Format C (NFC) is available at
http://unicode.org/reports/tr15.
UCT Control Sequence

<!-- Page 144 -->

Bit 1, Alternate inline position (I
a
) valid
A value of B'0' in this bit indicates that the alternate inline position parameter ALTIPOS is not valid and cannot
be used to position UCT text. A value of B'1' in this bit indicates that the alternate inline position parameter
ALTIPOS is valid and can be used to position UCT text.
Bit 2, Alternate inline position (I
a
) coordinates
A value of B'0' in this bit indicates that the alternate inline position parameter I
a
is specified using absolute i-
coordinate values. In this case the range of I
a
is restricted to positive values. A value of B'1' in this indicates
that the alternate inline position parameter I
a
is specified using relative i-coordinate values that are measured
with respect to the current inline position $I_c$
. In this case the range of I [PTOCA-4-660]
a
allows positive and negative values.
Bit 3, Maintain current inline position ($I_c$
)
A value of B'0' in this bit indicates that the current inline position $I_c$
is advanced in accordance with the
equations for $I_{cnew}$
at the completion of the UCT. A value of B'1' in this bit indicates that the current inline
position $I_c$
should not be advanced when the UCT is processed. In this case the value of $I_c$
at the completion of
UCT processing is equal to the value of $I_c$
at the start of UCT processing.
Bit 4, Reset paragraph direction
A value of B'0' in this bit has no effect on the paragraph direction and is treated as a No-op. A value of B'1' in
this bit coupled with CTLNGTH= X'0000' causes the paragraph direction to be reset to an undefined state. In
that case all other UCT controls are ignored. If this bit is set to B'1' and CTLNGTH≠X'0000', the bit is treated as
if it were set to B'0'.
Implementation Note: When AFP print servers process a Begin Presentation Text (BPT) structured field in a
MO:DCA data stream, they issue a set of PTOCA control sequences to establish default initial text
conditions for processing the text object. When the server supports the processing of stand-alone UCT s
(UCT s that are not part of a GLC chain) in attached presentation devices, this set must include a UCT
control sequence with CTLNGTH = X'0000' and CTFLGS bit 4 = B'1' to reset the paragraph direction to
an undefined state at the beginning of each text object. When the server only supports the processing of
UCT s within GLC chains in attached presentation devices, this additional control is not necessary.
Bits 5-7, Reserved
Reserved bits are set to B'0' by generators and ignored by receivers.
BIDICT is a code that specifies controls for processing the Unicode complex text code points that follow with
the Unicode bidi algorithm. In all cases, characters are presented at the character rotation specified in the
active font. If the active font is not a data-object font, or if it is a data-object font that does not specify a
Unicode-based character encoding, this parameter is ignored. For most BIDICT values, this parameter
establishes the Unicode paragraph direction, which is used as an input to the Unicode bidi algorithm. Table 14
on shows how the paragraph direction for Unicode complex text affects the positioning of the UCT
text. See “Bidi Layout Processing for UCT Text” for a description of bidi layout processing for UCT
text.
Value Description
X'02' Enable Unicode bidi layout processing for the complex text code points that follow. The
paragraph direction is determined by the complex text string based on the first strong
directional character that is encountered. If no paragraph direction can be determined, use a
left-to-right default paragraph direction.
X'04' Enable Unicode bidi layout processing for the complex text code points that follow. The
paragraph direction is set to left-to-right.
UCT Control Sequence

<!-- Page 145 -->

X'05' Enable Unicode bidi layout processing for the complex text code points that follow. The
paragraph direction is set to right-to-left.
X'12' Enable Unicode bidi layout processing for the complex text code points that follow. The
paragraph direction is determined by the previously processed complex text string in this text
object. If this is the first complex text string that is encountered in this text object, or if the
paragraph direction is undefined, the paragraph direction is based on the first strong
directional character that is encountered. If no paragraph direction can be determined, use a
left-to-right default paragraph direction.
X'20' Disable Unicode bidi layout processing for the complex text code points that follow. The
current PTOCA inline direction determines the text direction on the current PTOCA baseline.
The code points in the UCT are processed with respect to text direction as if they were within a
TRN.
X'22' Disable Unicode bidi layout processing for the complex text code points that follow. The UCT
contains a single directional run and is processed with a fixed text direction that is left-to-right.
There are no text direction changes for this UCT. The alternate inline position parameter I
a
, if
specified, is not used.
X'23' Disable Unicode bidi layout processing for the complex text code points that follow. The UCT
contains a single directional run and is processed with a fixed text direction that is right-to-left.
There are no text direction changes for this UCT. The alternate inline position parameter I
a
, if
specified, is not used.
All others Reserved
GLYPHCT is a code that specifies controls for applying glyph processing to the Unicode complex text code
points that follow. In all cases, characters are presented at the character rotation specified in the active font. If
the active font is not a data-object font, or if it is a data-object font that does not specify a Unicode-based
character encoding, this parameter is ignored.
Value Description
X'01' Enable glyph processing for the complex text code points that follow. Language-specific
processing is applied to runs of characters and may result in shaping, composition/
decomposition, and positional adjustments.
X'20' Disable glyph processing for the complex text code points that follow. The code points in the
UCT are processed with respect to glyph processing as if they were within a TRN. Characters
are rendered in a one-to-one fashion using the selected glyph in the active font.
All others Reserved
If Unicode bidi layout processing is disabled (BIDICT=X'20') and if Unicode glyph processing is disabled
(GLYPHCT=X'20') the UCT code points are not treated as complex text and are processed as if they were
within a TRN control sequence.
ALTIPOS is a parameter that specifies an alternate inline position (I
a
) on the current baseline that may be used
in place of the current inline text position ($I_c$
) when processing Unicode complex text and the paragraph
direction is opposite the writing mode direction. This parameter is ignored if Unicode bidi layout processing is
disabled for the code points that follow. This parameter is also ignored if the active font is not a data-object
font, or if it is a data-object font that specifies a non-Unicode-based character encoding. The use of this
parameter depends on the current character rotation–as specified in the active font–and the paragraph
direction for the UCT. Table 14 shows how the text in a UCT is positioned either with respect to
the current inline position $I_c$
, or with respect to the alternate inline position I
a
.
UCT Control Sequence

<!-- Page 146 -->

Table 14. UCT Text Positioning
PTOCA (i,b)
Orientation
Character Rotation UCT Para-
graph
Direction
UCT Character
Position w.r. to I
a
or $I_c$
UCT Character Position
w.r. to $I_c$
only
All 8 (i,b)
orientations
Character rotation 0°: Horizontal
L→R writing mode
L→R Start at $I_c$
; place UCT
text so it extends in (+) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
R→L Start at I
a
; place UCT
text so it extends in (-) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
All 8 (i,b)
orientations
Character rotation 180°:
Horizontal R→L writing mode
R→L Start at $I_c$
; place UCT
text so it extends in (+) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
L→R Start at I
a
; place UCT
text so it extends in (-) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
All 8 (i,b)
orientations
Character rotation 270°: V ertical
T→B writing mode
N/A Start at $I_c$
; place UCT
text so it extends in (+) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
All 8 (i,b)
orientations
Character rotation 90°: V ertical
B→T writing mode
N/A Start at $I_c$
; place UCT
text so it extends in (+) i-
direction
Start at $I_c$
; place UCT text
so it extends in (+) i-
direction
Table Notes:
1. The terms 'left', 'right', 'top', and 'bottom–as in 'L→R', 'R→L', 'T→B', and 'B→T'–only have meaning when [PTOCA-4-661]
the media has been oriented so that the characters appear right-side-up.
2. The (+) i-direction is the direction of increasing i-values; the (-) i-direction is the direction of decreasing [PTOCA-4-662]
i-values.
### Pragmatics
The CTLNGTH parameter must specify an even number when the character encoding is double-byte, as in
UTF-16. If CTLNGTH is an odd number when the character encoding is double byte, exception condition
EC-1A01 exists. The standard action is to process the code point sequence up to the last byte, skip the odd
byte, exit UCT processing mode, and continue processing. If the CTLNGTH value is out of the architected
range, exception condition EC-9B01 exists. The standard action is to process the UCT code points up to the
maximum valid value of CTLNGTH, exit UCT processing mode, and continue processing.
If the UCTVERS, BIDICT, or GLYPHCT parameters specify an invalid value, exception condition EC-9B01
exists. The standard action is to process the UCT code points as if they were within a TRN.
The code points that follow the UCT may not specify valid Unicode data. In that case exception condition
EC-1A03 exists, and the standard action is to skip the remainder of the UCT code points, exit UCT processing
mode, and continue processing.
The UCT control sequence always terminates chaining. Therefore, in the exception cases noted, when the
presentation device exits UCT processing mode, it continues processing by interpreting the next bytes as
either code points or the start of an unchained control sequence. [PTOCA-4-663]
### Exception Conditions
This control sequence can cause the following exception conditions:
* EC-1A01...The CTLNGTH parameter is an odd number, but the character encoding is double byte. [PTOCA-4-664]
UCT Control Sequence

<!-- Page 147 -->

* EC-9B01...The CTLNGTH, UCTVERS, BIDICT, or GLYPHCT parameter values are invalid. [PTOCA-4-665]
* EC-1A03...Invalid Unicode data. This can be caused by one of the following: [PTOCA-4-666]
– A high-order surrogate code value was not immediately followed by a low-order surrogate code value. The
high-order surrogate range is U+D800 through U+DBFF.
– A low-order surrogate code value was not immediately preceded by a high-order surrogate code value.
The low-order surrogate range is U+DC00 through U+DFFF.
– An illegal UTF-8 byte sequence, as defined in the Unicode 3.2 Specification, was specified. For more
information on illegal UTF-8 byte sequences, see Table 15.
Application Note. For a formal definition of UTF-8, consult the Unicode 3.2 Specification. The illegal UTF-8
byte sequences can be summarized as follows:
* The value in the 1st byte of the UTF-8 byte sequence was not in the legal UTF-8 range (X'00' - X'7F' and [PTOCA-4-667]
X'C2' - X'F4').
* The value in the 2nd byte of the UTF-8 byte sequence was not in the legal UTF-8 range allowed by the value [PTOCA-4-668]
in the 1st byte. The valid ranges for the 2nd byte are shown in Table 15.
Table 15. V alid Values for UTF-8 First and Second Bytes
First Byte Second Byte
X'C2'–X'DF' X'80''–X'BF'
X'E0' X'A0''–X'BF'
X'E1''–X'EC' X'80''–X'BF'
X'ED' X'80''–X'9F'
X'EE''–X'EF' X'80''–X'BF'
X'F0' X'90''–X'BF'
X'F1''–X'F3' X'80''–X'BF'
X'F4' X'80''–X'8F'
* The value in the 3rd or 4th byte of the UTF-8 byte sequence was not in the legal UTF-8 range for that byte [PTOCA-4-669]
(X'80' - X'BF').
Bidi Layout Processing for UCT Text
A number of parameters–both outside the UCT and inside the UCT–determine how Unicode bidi layout
processing is applied to text in a UCT:
* Text orientation. The orientation of the (i,b) coordinate system which specifies the baseline on which glyphs [PTOCA-4-670]
are positioned and the reference inline direction for progressing text directional runs.
* Character rotation. Alignment of a character with respect to the baseline. Differentiates between horizontal [PTOCA-4-671]
and vertical writing modes.
* Writing mode. Can be horizontal (L→R or R→L) or vertical (top->bottom or bottom->top). Determines– [PTOCA-4-672]
together with the paragraph direction–how UCT text is positioned with respect to the current inline position
($I_c$
) or with respect to an alternate inline position (I
a
).
* Bidirectional character property. A property value assigned by the Unicode standard to each character, [PTOCA-4-673]
including unassigned characters. Values include strong L→R, strong R→L, weak L→R, weak R→L, and
neutral characters.
* Text direction. Specifies the visual ordering of characters in a given directional run. The inherent directional [PTOCA-4-674]
properties of Unicode characters dictate the text direction assigned many characters, while the Unicode bidi
layout algorithm will assign a direction to characters such as punctuation marks which have a neutral
directional property.
UCT Control Sequence

<!-- Page 148 -->

* Paragraph direction. Specifies the dominant text direction for a UCT. Used as an input to the Unicode bidi [PTOCA-4-675]
layout algorithm where it influences the ordering of directional runs and of directionally-neutral characters in
a UCT.
The processing of the Unicode complex text in a UCT occurs within the context of the PTOCA and AFP
presentation models, which define 32 ways to print text based on the 8 (i,b) text orientations and 4 character
rotations. Figure 16 shows the 32 ways to print text in AFP environments.
UCT Control Sequence

<!-- Page 149 -->

Figure 16. 32 Ways to Print Text in AFP Environments
A B□C
D□E□F
D
A
E□BF□C
F□E□DC□B
A
F□E□D
C□BA
C□F
B□E
A
D
C□F
B□E
A D
C□FB□EA
D
C□F
B□E
AD
F□E□D
C□B
A
F□E□D
C□BA
C□B
A
F□E□D
C□BA
F□E□D
F□C
E□B
D A
F□C
E□B
D
A
F□CE□B
D
A
F□C
E□B
DA
C□BA
F□E□D
C□B
A
F□E□D
D A
E□B
F□C
A D
B□E
C□F
A
D
B□E
C□F
A
D
B□E
C□F
AD
B□E
C□F
D
A
E□B
F□C
DA
E□B
F□C
D□E□F
A
B□C
D□E□F
AB□C
D□E□F
A B□C
D□E□FA
B□C
A
B□C
D□E□F
AB□C
D□E□F
A
B□C
D□E□F
I□=□0°,□□B□=□90°□□□□□□□□I□=□90°,□□B□=□180°
I□=□0°,□□B□=□90°□□□□□□□□I□=□90°,□□B□=□180°
I□=□0°,□□B□=□90°□□□□□□□□I□=□90°,□□B□=□180°
I□=□0°,□□B□=□90°□□□□□□□□I□=□90°,□□B□=□180°
I□=□90°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□90°
I□=□90°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□90°
I□=□90°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□90°
I□=□90°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□90°
I□=□270°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□270°
I□=□270°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□270°
I□=□270°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□270°
 I□=□270°,□□B□=□0°□□□□□□□□I□=□180°,□□B□=□270°
Character□Rotation□=□0°
Character□Rotation□=□270°
Character□Rotation□=□180°
Character□Rotation□=□90°
I□=□0°,□□B□=□270°□□□□□□□□I□=□270°,□□B□=□180°
I□=□0°,□□B□=□270°□□□□□□□□I□=□270°,□□B□=□180°
I□=□0°,□□B□=□270°□□□□□□□□I□=□270°,□□B□=□180°
 I□=□0°,□□B□=□270°□□□□□□□□I□=□270°,□□B□=□180°
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
I□□□□□□□□□□□□B
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B□□□□□□□□□□□□I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
I
B
I
B
I
B
I
B
I
B
I
B
I
B
I
B
UCT Control Sequence

<!-- Page 150 -->

UCT Bidi Processing Examples
The following examples illustrate how the writing mode, text direction, and paragraph direction influence the
rendering of UCT Unicode bidi text. For all the examples, assume a character string that is stored in logical
(storage) order as:
R0 R1 R2 L3 L4 L5 R6 R7 R8 N9
where the R
i
are strong R→L characters, such as Arabic and Hebrew characters, the L
i
are strong L→R
characters, such as English (Latin) characters, and the N
i
have a neutral directional property, such as a
punctuation mark.
The text direction affects character ordering when processing a given directional run from logical (storage)
order into visual (rendering) order.
* The text direction of the L [PTOCA-4-676]
i
characters is L→R. In that case the characters are rendered as:
L3 L4 L5
* The text direction of the R [PTOCA-4-677]
i
characters is R→L. In that case the characters are rendered as:
R8 R7 R6 and R2 R1 R0
* The text direction can also be set by the BIDICT parameter on the UCT. With BIDICT values X'22'and X'23', [PTOCA-4-678]
the text direction is fixed as either L→R or R→L, respectively, regardless of the directional property of the
characters. Care must be taken when using these BIDICT values or undesirable results may occur, as for
example, in the following cases:
– If BIDICT=X'22' (set L→R text direction), the R
i
strings are rendered as:
R0 R1 R2 and R6 R7 R8
– Similarly, if BIDICT=X'23' (set R→L text direction), the L
i
string is rendered as:
L5 L4 L3
The paragraph direction affects the ordering of directional runs in a UCT and of directionally-neutral characters
in a UCT. Assume that the complete string in the previous example is included in a single UCT.
* If the paragraph direction is R→L, the complete string is rendered as: [PTOCA-4-679]
N9 R8 R7 R6 L3 L4 L5 R2 R1 R0
* If the paragraph direction is L→R, the complete string is rendered as: [PTOCA-4-680]
R2 R1 R0 L3 L4 L5 R8 R7 R6 N9
The writing mode determines how UCT text is positioned on the current baseline. Assume the current inline
position is $I_c$
.
* Writing mode L→R, paragraph direction L→R: [PTOCA-4-681]
($I_c$
) R2 R1 R0 L3 L4 L5 R8 R7 R6 N9
* Writing mode R→L, paragraph direction L→R: [PTOCA-4-682]
R2 R1 R0 L3 L4 L5 R8 R7 R6 N9 ($I_c$
)
* Writing mode L→R, paragraph direction R→L: [PTOCA-4-683]
($I_c$
) N9 R8 R7 R6 L3 L4 L5 R2 R1 R0
* Writing mode R→L, paragraph direction R→L: [PTOCA-4-684]
N9 R8 R7 R6 L3 L4 L5 R2 R1 R0 ($I_c$
)
The alternate inline position (I
a
), if specified, can aff ect how the UCT text is positioned on the baseline as
follows.
* Writing mode L→R, paragraph direction L→R: [PTOCA-4-685]
($I_c$
) R2 R1 R0 L3 L4 L5 R8 R7 R6 N9 (I
a
)
* Writing mode L→R, paragraph direction R→L: [PTOCA-4-686]
($I_c$
) N9 R8 R7 R6 L3 L4 L5 R2 R1 R0 (I
a
)
* Writing mode R→L, paragraph direction R→L: [PTOCA-4-687]
N9 R8 R7 R6 L3 L4 L5 R2 R1 R0 ($I_c$
) (I
a
)
* Writing mode R→L, paragraph direction L→R: [PTOCA-4-688]
($I_c$
(I
a
) R2 R1 R0 L3 L4 L5 R8 R7 R6 N9
UCT Control Sequence

<!-- Page 151 -->

Unicode bidi layout processing is not supported in vertical T→Bor B→T writing modes. When characters that
have a horizontal directional attribute–such as Latin characters–are encountered while in a vertical writing
mode, they are presented in the same direction and at the same character rotation as the characters with a
vertical directional attribute. There is also no additional Unicode bidi layout processing for directional changes
due to embedded L→R or R→L sequences.
Positioning Considerations for UCT Text
The above examples showing the effect of paragraph direction assume that the complete text string fits on one
line, can be included in a single UCT, and can be treated as a single paragraph. This is important because
proper rendering of Unicode bidi text requires that the Unicode bidi layout algorithm be applied to a complete
paragraph. This is not always practical. It may be necessary to break up a paragraph into multiple UCT s to
insert formatting commands for functions such as line breaks, page breaks, font changes, and color changes.
In those cases the single paragraph is segmented into multiple UCT s so that the appropriate PTOC A control
sequences can be inserted between UCT s.
Unfortunately, when a paragraph is segmented into multiple UCT s, the scope of the original paragraph is lost.
Applying a paragraph direction to each segmented UCT will, in general, not result in the correct rendering of
the original paragraph. To avoid this, the PTOCA generator needs to invoke the Unicode bidi layout algorithm
to break the paragraph into directional runs and then package these directional runs into individual UCT s that
specify the desired text direction (BIDICT values X'22'and X'23'). For example the complete string could be
broken into the following three UCT s when a R→L paragraph direction is used. These UCT s must appear in the
data stream in the order shown to preserve the logical order of the Unicode text:
UCT1{TD=R→L} R0 R1 R2 UCT2{TD=L→R} L3 L4 L5 UCT3{TD=R→L} R6 R7 R8 N9
Since the substrings now no longer belong to the same UCT, care must be taken by the PTOCA generator to
ensure that the UCT s containing the substrings are positioned properly. Setting the writing mode to match the
paragraph direction for the complete string will minimize the need for explicit UCT positioning. In our example,
if the writing mode is R→L, the three UCT s would be rendered correctly based on the default text position
advancements:
(I
c3
) N9 R8 R7 R6 (I
c2
) L3 L4 L5 (I
c1
) R2 R1 R0 ($I_c$
)
where $I_c$
is the text position before the first UCT (UCT1) is processed, and I
c1
is the text position after UCT1 is
processed. However if the writing mode is L→R, the three UCT s would not be rendered correctly using the
default text position advancements:
($I_c$
) R2 R1 R0 (I
c1
) L3 L4 L5 (I
c2
) N9 R8 R7 R6 (I
c3
)
In many circumstances explicit positioning of segmented UCT s is unavoidable, even if the writing mode
matches the paragraph direction of the complete string. Consider the need to print the character L5 in a bold
font:
UCT1{TD=R→L} R0 R1 R2
UCT2a{TD=L→R} L3 L4
<< font change >>
UCT2b{TD=L→R} L5
<< font change >>
UCT3{TD=R→L} R6 R7 R8 N9
Even with a R→L writing mode, the default positioning would result in the following incorrect rendering:
(I
c3
) N9 R8 R7 R6 (I
c2b
) L5 (I
c2a
) L3 L4 (I
c1
) R2 R1 R0 ($I_c$
)
In this case, UCT2a, UCT2b, and UCT3 must be explicitly positioned to maintain the correct layout, as follows.
Assume that M
i
is the movement of the text position caused by processing UCT1; that is, it is the sum of the
grapheme increments for UCT1:
UCT1{TD=R→L} R0 R1 R2
<< Relative Move Inline in (+) i-direction by M2b >>
UCT2a{TD=L→R} L3 L4
<< Relative Move Inline in (-) i-direction by {M2a + M2b}>>
UCT Control Sequence

<!-- Page 152 -->

<< font change >>
UCT2b{TD=L→R} L5
<< font change >>
<< Relative Move Inline in (+) i-direction by M2a >>
UCT3{TD=R→L} R6 R7 R8 N9
This results in the correct rendering of the string:
(I
c1
) R2 R1 R0 ($I_c$
)
(I
c2a
) L3 L4 (I
c1
) R2 R1 R0 ($I_c$
)
(I
c2a
) L3 L4 (I
c2b
) L5 (I
c1
) R2 R1 R0 ($I_c$
)
(I
c3
) N9 R8 R7 R6 (I
c2a
) L3 L4 (I
c2b
) L5 (I
c1
) R2 R1 R0 ($I_c$
)
Implementation Note: The use of the ICU ParagraphLayout API is recommended for PTOCA generators that
need to segment bidi paragraphs. This APIcan provide the information needed to specify a text direction and
position for each segmented UCT. See http://site.icu-project.or.
Effect of Other PTOCA Control Sequences on UCT Text
The unique characteristics of complex text require that some PTOCA control sequences have a different effect
on UCT text processing than they do on non-UCT text processing. These differenc es are defined as follows:
* Overstrike (OVS). Table 16 defines which Unicode space characters are treated as PTOCA [PTOCA-4-689]
space characters or variable space characters when an overstrike is generated. This applies to both UCT
text and Unicode-encoded non-UCT text.
* Set Intercharacter Adjustment (SIA). If glyph processing or bidi layout processing is enabled, intercharacter [PTOCA-4-690]
adjustment is not applied to UCT text, and the current inline position is incremented for each graphic
character as follows:
$I_{cnew}$ = $I_c$
+ CI
In all other cases the SIA control sequence is processed the same for UCT text as for non-UCT text.
* Set Text Orientation (STO). If bidi layout processing is enabled and the writing mode is horizontal, characters [PTOCA-4-691]
in complex text may be progressed in the negative i-direction as defined in Table 14. In all other
cases the STO control sequence is processed the same for UCT text as for non-UCT text.
* Set V ariable Space Character Increment (SVI). Table 16 defines which Unicode space [PTOCA-4-692]
characters are mapped to the PTOCA variable space character and are assigned the increment specified by
the SVIcontrol sequence. This applies to both UCT text and Unicode-encoded non-UCT text.
* Temporary Baseline Move (TBM). If glyph processing or bidi layout processing is enabled, the precision [PTOCA-4-693]
parameter in this control sequence is ignored for complex text, and such text is processed as if the precision
parameter were set to B'0' - actual placement method. This means that the presentation device must actually
move the baseline and position the complex text characters on the temporary baseline. In all other cases the
TBM control sequence is processed the same for UCT text as for non-UCT text.
* Underscore (USC). Table 16 defines which Unicode space characters are treated as PTOCA [PTOCA-4-694]
space characters or variable space characters when an underscore is generated. This applies to both UCT
text and Unicode-encoded non-UCT text.
All other PTOCA control sequences are processed the same for UCT text as for non-UCT text.
Table 16. Unicode Space Characters Mapped to PTOCA Space and V ariable Space Characters
Unicode Space Character Name Unicode Code Point PTOCA Space
Character
PTOCA V ariable
Space Character
SP ACE U+0020
yes yes
NO-BREAK SP ACE U+00A0
yes yes
UCT Control Sequence

<!-- Page 153 -->

UCT Control Sequence

<!-- Page 154 -->

## Presentation Text Data Descriptor
The Presentation Text Data Descriptor provides initial parameters for the Presentation Text Object.
Syntax: Please see the appendixes for information about the syntax of the Presentation Text Data Descriptor
and its parameters within the MO:DCA and IPDS environments.
Semantics: A Presentation Text Data Descriptor describes a Presentation Text object. It specifies the
measurement units for the object and the size of the object. The descriptor also may describe the object
through a list of initial text conditions, which contain initial values such as the size of the inline margin.
Pragmatics: The Presentation Text Data Descriptor is optional, because its parameters may be specified by
the controlling environment or by default.
The descriptor parameters fall into the following categories:
1. Measurement units parameters: [PTOCA-4-695]
* Unit base [PTOCA-4-696]
* $X_p$
-units per unit base
* $Y_p$
-units per unit base
2. Size parameters [PTOCA-4-697]
* $X_p$
-extent of the presentation space
* $Y_p$
-extent of the presentation space
3. Initial text condition parameters [PTOCA-4-698]
The following section describes these parameters.
Measurement Unit Parameters
Semantics: These parameters specify the measurement units for the Presentation Text object space::
Unit base A number from 0 through 1
$X_p$
-units per unit base A number from 1 through 32,767
$Y_p$
-units per unit base A number from 1 through 32,767
The unit base parameterspecifies the measurement base. It has the following values:
Values Description
0 Ten inches [PTOCA-4-699]
1 Ten centimeters [PTOCA-4-700]
2-254 Reserved [PTOCA-4-701]
If the value of the unit base parameter is not supported or is not within the range specified by PTOCA,
exception condition EC-0505 exists. The standard action is to ignore this parameter and continue presentation
with the value determined according to the hierarchy. The subset may limit the range permitted. For detailed
information about subsets, please see Chapter 6, “Compliance with PTOCA”,, Appendix A, “MO:
DCA Environment”,, and Appendix B, “IPDS Environment”,. See “Related
Publications” for data stream documentation.
Units of measure are defined as the measurement base divided by the units per unit base. That is, if the
measurement base is 10 inches and the units per unit base are 5,000, the units of measure are 10 inches /
5000 or one five-hundredth of an inch. Here are further examples. [PTOCA-4-702]
## Presentation Text Data Descriptor

<!-- Page 155 -->

Table 17. Examples of Measurement Units
Units/inch Parameter Values Comments
800 X 800 units/in. Unit base = 0 [PTOCA-4-703]
$X_p$
-units per unit base = 8,000
$Y_p$
-units per unit base = 8,000
8,000 divisions in 10 in. on both axes
80 X 77 units/cm. Unit base = 1 [PTOCA-4-704]
$X_p$
-units per unit base = 800
$Y_p$
-units per unit base = 770
800 divisions in 10 cm. on $X_p$ [PTOCA-4-705]
770 divisions in 10 cm. on $Y_p$ [PTOCA-4-706]
203.3 X 195.5 [PTOCA-4-707]
units/in.
Unit base = 0
$X_p$
-units per unit base = 2,033
$Y_p$
-units per unit base = 1,955
2,033 divisions in 10 in. on $X_p$
1,955 divisions in 10 in. on $Y_p$
240 x 240 units/in. Unit base = 0 [PTOCA-4-708]
$X_p$
-units per unit base = 2,400
$Y_p$
-units per unit base = 2,400
2,400 divisions in 10 in. on both axes
1,440 x 1,440
units/in.
Unit base = 0
$X_p$
-units per unit base = 14,400
$Y_p$
-units per unit base = 14,400
14,400 divisions in 10 in. on both axes
In row 1 of the table, the measurement base is 10 inches. The units per unit base value specifies 8,000
divisions in 10 inches, which is 800 divisions per inch. In row 2 of the table, the measurement base is 10
centimeters. The units per unit base value for the $X_p$
-axis specifies 800 divisions in each 10 centimeters, which
is 80 divisions per centimeter. The units per unit base value for the $Y_p$
-axis specifies 770 divisions per
measurement base, which is 77 divisions per centimeter. In this case, the units per unit base values dif fer
along the $X_p$
-axis and $Y_p$
-axis.
If the value of the $X_p$
-units per unit base parameter or the $Y_p$
-units per unit base parameter is not supported or
is not within the range specified by PTOCA, exception condition EC-0605 exists. The standard action in this
case is to ignore the parameter and continue presentation with the value determined according to the
hierarchy. The subset may limit the range permitted.
Measurement units specified for the $X_p$
-axis may be diffe rent from the measurement units specified for the $Y_p$
-
axis.
Pragmatics: The measurement units for the I-axis are the same as the measurement units for the $X_p$
,$Y_p$
axis
that is parallel to it. The units of the B-axis are the same as those of the other $X_p$
,$Y_p$
axis. The origin and
orientation of the I-axis and B-axis can be specified by the Text Orientation initial text conditions, and by the Set
Text Orientation control sequence. The values of the presentation text measurement units cannot be changed
within a Presentation Text object, but the values of presentation text orientation can be changed.
Size Parameters
Semantics: Size consists of two parameters that specify the dimensions of a Presentation Text object, that is,
the length of the $X_p$
-axis and the $Y_p$
-axis.
These dimensions, or extents, are described as follows.
$X_p$
-extent A number specifying the size of the Presentation Text object along the $X_p$
-axis.
$Y_p$
-extent A number specifying the size of the Presentation Text object along the $Y_p$
-axis.
Each extent is measured in $X_p$
-units or $Y_p$
-units. For information about the syntax of the extent fields, please
see Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS Environment”,.
If the value of the $X_p$
-extent parameter or the $Y_p$
-extent parameter is not supported or is not within the range
specified by PTOCA, exception condition EC-0705 exists. The standard action is to ignore the invalid [PTOCA-4-709]
## Presentation Text Data Descriptor

<!-- Page 156 -->

parameter and continue presentation with the parameter value determined according to the hierarchy. The
subset may limit the range permitted.
These extents are used in conjunction with the measurement units to specify the size of the Presentation Text
object and for positions and displacements within the object.
Architecture Note: Note that when presentation text is processed in a MO:DCA environment where the
Presentation Text Data Descriptor (PTD) is carried in the Active Environment Group (AEG) for the page,
or when such text is processed in an IPDS environment, the Presentation Text object is bounded by the
beginning of the page and the end of the page. This is sometimes called a text major environment.
When the PTD is carried in the Object Environment Group (OEG) of a MO:DCA text object, the text
object is bounded by the Begin Presentation Text (BPT) and End Presentation Text (EPT) structured
fields. For such objects, the PTD in the AEG is ignored.
The range of supported values is receiver dependent.
Pragmatics: If the object's origin, orientation, and size are such that the object projects beyond the associated
object area, the applicable data-stream standard action or default action must be invoked. This allows the
controlling environment to control clipping.
If a control sequence, parameter, or other data element within the object causes presentation outside of the
object space, exception condition EC-0103 exists when an attempt is made to present. The standard action is
to ignore the character or control sequence that is presenting outside of the object space.
If any part of the character box for a character exceeds the boundaries of the object space, an exception
condition exists even though the character's presentation position is within the object space. Please see
“Graphic Character Processing”.
Exception Conditions: The measurement units parameters and the size parameters can cause the following
exception conditions:
* EC-0505...The value of the unit base parameter is not supported, or is not in the range specified by PTOC A. [PTOCA-4-710]
* EC-0605...The value of the $X_p$ [PTOCA-4-711]
-units per unit base parameter or the $Y_p$
-units per unit base parameter is not
supported, or is not in the range specified by PTOCA.
* EC-0705...The value of the $X_p$ [PTOCA-4-712]
-extent parameter or the $Y_p$
-extent parameter is not supported, or is not in the
range specified by PTOCA.
* EC-0103...The contents of the Presentation Text object will cause presentation outside of the object space. [PTOCA-4-713]
Presentation Text Flags
Semantics: This parameter is reserved. Generators should set it to zero and receivers should ignore it. [PTOCA-4-714]
## Presentation Text Data Descriptor

<!-- Page 157 -->

Initial Text Condition Parameters
The Initial Text Condition parameters specify initial values for the Presentation Text object. They include the
following parameters:
* Baseline increment [PTOCA-4-715]
* Coded font local ID [PTOCA-4-716]
* Extended text color [PTOCA-4-717]
* Initial baseline coordinate [PTOCA-4-718]
* Initial inline coordinate [PTOCA-4-719]
* Inline margin [PTOCA-4-720]
* Intercharacter adjustment [PTOCA-4-721]
* Text color [PTOCA-4-722]
* Text orientation [PTOCA-4-723]
The following pages contain the semantics and the pragmatics of the PTOCA initial text condition parameters.
For the syntax, please see Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS
Environment”,.
Baseline Increment
Baseline Increment specifies the distance between successive baselines.
Semantics: This parameterspecifies the initial value of the increment in the B-direction from the current
baseline position to a new baseline position. If the value of this parameter is not supported or is not within the
range specified, exception condition EC-1 101 exists. The standard action is to ignore this parameter and
continue presentation with the value determined according to the hierarchy. The subset level may limit the
range permitted. See Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS
Environment”, for more information about valid ranges.
The default value is the Default Baseline Increment associated with the default coded font of the device.
Pragmatics: The baseline position is incremented by this value after each Begin Line control sequence is
encountered in a text stream. If the value of this parameter is zero, each line appears superimposed over the
preceding line. This value can be overridden for the duration of a Presentation Text object by a Set Baseline
Increment control sequence. [PTOCA-4-724]
### Exception Conditions
This parameter can cause the following exception condition:
* EC-1 101...The value of the baseline increment parameter is not supported or is not in the range specified by [PTOCA-4-725]
PTOCA.
Coded Font Local ID
C oded Font Local ID specifies a font.
Semantics: This parameterspecifies the initial value of the coded font local identifier (LID). The LID is used by
the controlling environment to access a coded font when presenting subsequent text in the current
Presentation Text object. If the value of the LID is not supported or is not within the range specified, exception
condition EC-0C01 exists. The standard action is to ignore this parameter and to use the active coded font and
character attributes determined according to the hierarchy. The subset level may also limit the range permitted.
See Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS Environment”, for
more information about valid ranges.
The default value is the LID of the default coded font of the device. [PTOCA-4-726]
## Presentation Text Data Descriptor

<!-- Page 158 -->

Pragmatics: The LID is equated to a coded font, character rotation, and font modification parameters by a
mapping function in the controlling environment. If the text orientation is changed and the coded font selected
by this parameter is not compatible with the new orientation, when an attempt is made to present a character
from the selected coded font, exception condition EC-3F02 exists. The standard action is to complete the
presentation at this presentation position using the receiver's default font. This results in the best possible
presentation within the limits of the receiver's capability. If there is an intercharacter adjustment value, it is not
applied. The measurement units used in the object and those used by the coded font selected for presentation
are assumed to be compatible. If the measurement units are not compatible, the standard action is to present a
best fit of the character and continue processing. A best fit could result in no mark or unintelligible marks on the
presentation surface. Incompatibility of measurement units is not an exception condition in PTOCA.
Exception condition EC-1802 occurs in the following cases:
* The Coded Font Local ID parameter is present in the Presentation Text Data Descriptor but a corresponding [PTOCA-4-727]
mapping function does not exist in the controlling environment.
* An equate of the local identifier to a global identifier does not exist, or substitution parameters do not exist, in [PTOCA-4-728]
the controlling environment. The coded font identified by the map is not available in the receiver.
The standard action is to substitute the receiver's default font for the requested font and continue processing. [PTOCA-4-729]
### Exception Conditions
This parameter can cause the following exception conditions:
* EC-0C01...The value of the LID parameter is not supported or is not in the range specified by PTOCA. [PTOCA-4-730]
* EC-3F02...The font is not compatible with the text orientation. [PTOCA-4-731]
* EC-1802...The font requested cannot be provided. [PTOCA-4-732]
Extended Text Color
The Extended Text Color parameterspecifies a process or highlight color value and defines the color space
and encoding for that value. The specified color value is applied to foreground areas of the text presentation
space. Foreground areas consist of the following:
* The stroked and filled areas of solid text characters, including overstrike characters. With hollow characters, [PTOCA-4-733]
only the stroked portion of the character is considered foreground.
* The stroked area of a rule. [PTOCA-4-734]
* The stroked area of an underscore. [PTOCA-4-735]
All other areas of the text presentation space are considered background.
Semantics: Refer to “Set Extended Text Color (SEC)” for a description of the parameter
semantics. The controlling environment may limit the range permitted. See “Related Publications”
for the appropriate data-stream documentation. Note that the Extended Text Color parameter is not supported
as an initial text condition in IPDS Environments, see “Presentation Text Data Descriptor for Text-Major Text”
on.
Pragmatics: If the receiver does not support the specified color value exception condition EC-0E03 exists.
The standard action in this case is to use the presentation device default color. [PTOCA-4-736]
### Exception Conditions
This parameter can cause the following exception conditions:
* EC-0E02...Invalid or unsupported color space. [PTOCA-4-737]
* EC-0E03...Invalid or unsupported color value. [PTOCA-4-738]
* EC-0E04...Invalid percent value. [PTOCA-4-739]
* EC-0E05...Invalid or unsupported number of bits in a color component. [PTOCA-4-740]
## Presentation Text Data Descriptor

<!-- Page 159 -->

Initial Baseline Coordinate
The Initial Baseline Coordinate specifies the point on the B-axis within the object space to which the current
addressable position will be initialized. This value is called the B-displacement.
Semantics: This parameterspecifies a displacement in the B-direction from the I-axis for the initial
addressable position. If the value of the B-displacement is not supported or is not within the range specified,
exception condition EC-6B02 exists. The standard action in this case is to ignore the invalid parameter and
continue presentation with the value determined according to the hierarchy. The subset level may also limit the
range permitted. See Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS
Environment”, for more information about valid ranges.
The default value is device-dependent.
Pragmatics: If the value of the B-displacement is X'0000', the initial addressable position in the B-direction is
at the I-axis. This does not affect the inline margin. [PTOCA-4-741]
### Exception Conditions
This parameter can cause the following exception condition:
* EC-6B02...The value of the B-displacement parameter is not supported or is not in the range specified by [PTOCA-4-742]
PTOCA.
Initial Inline Coordinate
The Initial Inline Coordinate specifies the point on the I-axis within the object space to which the current
addressable position will be initialized. This value is called the I-displacement.
Semantics: This parameterspecifies a displacement in the I-direction from the B-axis for the initial
addressable position. If the value of the I-displacement is not supported or is not within the range specified,
exception condition EC-6A02 exists. The standard action in this case is to ignore the invalid parameter and
continue presentation with the value determined according to the hierarchy. The subset level may also limit the
range permitted. See Appendix A, “MO:DCA Environment”, and Appendix B, “IPDS
Environment”, for more information about valid ranges.
The default value is zero.
Pragmatics: If the value of the I-displacement is X'0000', the initial addressable position in the I-direction is at
the B-axis. This does not affect the inline margin. [PTOCA-4-743]
### Exception Conditions
This parameter can cause the following exception condition:
* EC-6A02...The value of the I-displacement parameter is not supported or is not in the range specified by [PTOCA-4-744]
PTOCA.
Inline Margin
Inline Margin specifies the position of the inline margin.
Semantics: This parameterspecifies the initial value for the location of the inline margin. This value is a
displacement in the I-direction from the B-axis to the inline margin in the current Presentation Text object. If this
parameter is not supported or is not within the range specified, the exception condition EC-1001 exists. The
standard action is to ignore this parameter and continue presentation with the value determined according to
the hierarchy. The subset level may limit the range permitted. See Appendix A, “MO:DCA Environment”, on
page 163 and Appendix B, “IPDS Environment”, for more information about valid ranges. [PTOCA-4-745]
## Presentation Text Data Descriptor

<!-- Page 160 -->

The default value is zero.
Pragmatics: The addressable position for the first line of presentation text is specified by the Initial Baseline
Coordinate and Initial Inline Coordinate initial text conditions. After each Begin Line control sequence is
processed, the addressable position is placed at the inline margin. If the value of the inline margin parameter is
X'0000', the inline margin is at the B-axis. [PTOCA-4-746]
### Exception Conditions
This parameter can cause the following exception condition:
* EC-1001...The displacement parameter is not supported or is not in the range specified by PTOCA. [PTOCA-4-747]
Intercharacter Adjustment
Intercharacter Adjustment specifies additional increment or decrement between graphic characters.
Semantics: The adjustment parameterspecifies the initial value of additional space between graphic
characters. This space is in the I-direction from the end of the current character increment to the presentation
position of the following graphic character. When this value is positive, the adjustment is called an increment.
When the value is negative, the adjustment is called a decrement. The direction parameterspecifies the
direction in which the intercharacter adjustment is to be applied. Intercharacter increment, which occurs when
the direction parameter is X'00', is applied in the positive I-direction. Intercharacter decrement, which occurs
when the direction parameter is X'01', is applied in the negative I-direction. The default value for the direction
parameter is X'00'.
Intercharacter adjustment is not applied before or after the following:
* A space character or variable space character [PTOCA-4-748]
* A Begin Line control sequence [PTOCA-4-749]
* A Relative Move Inline control sequence [PTOCA-4-750]
* An Absolute Move Inline control sequence [PTOCA-4-751]
For non-incrementing characters, the adjustment is applied from the current addressable position to locate the
presentation position of the non-incrementing character, but the current addressable position is unchanged.
The effect is that the non-incrementing character may be coupled with a graphic character, resulting in an
overstrike function, and the adjustment is applied to the coupled characters.
If the value of the adjustment parameter or the direction parameter is not supported or is not within the range
specified, exception condition EC-1201 exists. The standard action in this case is to ignore the Intercharacter
Adjustment parameter and continue presentation with the parameter values determined according to the
hierarchy. The subset level may also limit the range permitted. See Appendix A, “MO:DCA Environment”, on
page 163 and Appendix B, “IPDS Environment”, for more information about valid ranges.
The default value is zero for both the adjustment parameter and the direction parameter.
Pragmatics: If the value of the adjustment parameter is X'0000', no additional intercharacter increment or
decrement appears between graphic characters. [PTOCA-4-752]
### Exception Conditions
This parameter can cause the following exception conditions:
* EC-1201...The value of the adjustment parameter or the direction parameter is not supported, or is not in the [PTOCA-4-753]
range specified by PTOCA. [PTOCA-4-754]
## Presentation Text Data Descriptor

<!-- Page 161 -->

Text Color
The Text Color parameterspecifies a named color that selects the foreground color of subsequent text
characters, rules, and underscores.
Architecture Note: Pre-year 2000 applications and printers support an optional PRECISION parameter for text
color. This parameter has been retired. It should not be generated by new applications, and should be
ignored by new printers. For a definition of this parameter, see “Retired Parameters”.
Semantics: The named color value is applied to foreground areas of the text presentation space. Foreground
areas consist of the following:
* The stroked and filled areas of solid text characters, including overstrike characters. With hollow characters, [PTOCA-4-755]
only the stroked portion of the character is considered foreground.
* The stroked area of a rule. [PTOCA-4-756]
* The stroked area of an underscore. [PTOCA-4-757]
All other areas of the text presentation space are considered background. Please refer to Table 13 on page
103 for the foreground color values and their associated colors. The default color attribute value is X'FF07'. [PTOCA-4-758]
If the value of the foreground color attribute is not supported or is not within the range specified by PTOCA,
exception condition EC-5803 exists. The standard action is to ignore these parameters and continue
presentation with the value determined according to the hierarchy. The controlling environment may limit the
range permitted. See “Related Publications” for the appropriate data-stream documentation.
Pragmatics:
The default color attribute value (FRGCOLOR = X'FFFF') is determined hierarchically. The following order
applies:
1. Value set by Text Color initial text condition parameter in Descriptor [PTOCA-4-759]
2. PTOCA default – X'FF07'. [PTOCA-4-760]
The device default value is the receiver ’ s default. For example, characters, rules, and underscores will be
presented in black on a receiver which supports only black. The receiver ’ s best possible value means that if
the receiver has limited color capabilities, then it may substitute a color it supports for one it does not support.
For example, the receiver may substitute red for pink, blue for turquoise, and so forth.
The color attribute values X'FF00' to X'FF06' are translated to X'0000' to X'0006' and treated exactly like those
colors. The PTOCA default value of X'FF07' is the device default. For example, characters, rules, and
underscores will be presented in black on a device which supports only black. A color attribute value of X'FF08'
means that the receiver's default background color should be used for the foreground color. This provides an
erase function.
### Exception Conditions
This parameter can cause the following exception condition:
* EC-5803...The foreground color parameter (FRGCOLOR) value is invalid, or the specified color is not [PTOCA-4-761]
supported.
Text Orientation
Text Orientation consists of two parameters that establish the I-direction and the B-direction for presentation
text.
Semantics: These parameters specify the initial I-axis and B-axis orientations with respect to the $X_p$
-axis.
These orientations are expressed in degrees and minutes. The same format is used for both orientations. Each
is a two-byte, three-part code of the form ABC. [PTOCA-4-762]
## Presentation Text Data Descriptor

<!-- Page 162 -->

* A is a nine-bit binary number (bits 0 - 8) which provides from 0 through 359 degrees. Values from 360 [PTOCA-4-763]
through 51 1 are invalid.
* B is a six-bit binary number (bits 9 - 14) which provides from 0 through 59 minutes. Values from 60 through [PTOCA-4-764]
63 are invalid. [PTOCA-4-765]
* C is a one-bit reserved field (bit 15) which must be B'0'. [PTOCA-4-766]
A value of X'0000' for the I-axis orientation parameter indicates that the positive direction is parallel to the $X_p$
-
axis. Increasing values for these parameters indicate increasing clockwise rotation.
The default value for the I-axis is X'0000', that is, zero degrees. The default value for the B-axis is X'2D00', that
is, 90 degrees. The maximum value for IORNTION and BORNTION is X'B3F6' or B'101 1001 1 1 1 1 101 10', which
is 359 degrees and 59 minutes.
Pragmatics: I-direction is the direction in which additional characters appear in a line of text. B-direction is the
direction in which additional lines of text appear. If the I-axis is not parallel to either the $X_p$
-axis or $Y_p$
-axis,
exception condition EC-6802 exists. If the B-axis is not parallel to either the $X_p$
-axis or $Y_p$
-axis, exception
condition EC-6902 exists. The standard action in this case is to set the I-axis equal to the $X_p$
-axis direction and
the B-axis equal to the $Y_p$
-axis direction. The origin of the I-axis and the B-axis is always one of the four corners
of the object space.
Orientations other than 0,90 are valid, but may be constrained by receiver limitations or parameters in
controlling environment. If the I-axis orientation is not supported by the receiver, exception condition EC-6802
exists. If the B-axis orientation is not supported by the receiver, exception condition EC-6902 exists. The
standard action is to use 0,90 degrees. These exception condition codes and standard actions also apply to
orientation values not within the range specified by PTOCA. [PTOCA-4-767]
### Exception Conditions
This parameter can cause the following exception conditions:
* EC-6802...The I-axis is not parallel to the $X_p$ [PTOCA-4-768]
-axis or the $Y_p$
-axis.
* EC-6902...The B-axis is not parallel to the $X_p$ [PTOCA-4-769]
-axis or the $Y_p$
-axis.
## Presentation Text Data Descriptor

<!-- Page 163 -->

Copyright © AFP Consortium 1997, 2025 145