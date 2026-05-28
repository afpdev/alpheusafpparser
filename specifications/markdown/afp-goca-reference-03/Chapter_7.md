Chapter 7. Commands and Drawing Orders
This chapter describes:
• The Begin Segment command [GOCA-7-001]
• Order formats [GOCA-7-002]
• Order alignment [GOCA-7-003]
• Current position in drawing orders [GOCA-7-004]
• Coordinate data [GOCA-7-005]
• Offset data [GOCA-7-006]
• Default values for drawing orders and attributes [GOCA-7-007]
• The following drawing orders: [GOCA-7-008]
- Begin Area - Begin Custom Pattern - Begin Image at Given Position - Begin Image at Current Position - Box at Given Position - Box at Current Position - Character String at Given Position - Character String at Current Position - Comment - Cubic Bezier Curve at Given Position - Cubic Bezier Curve at Current Position - Delete Pattern - End Area - End Custom Pattern - End Image - End Prolog - Fillet at Given Position - Fillet at Current Position - Full Arc at Given Position - Full Arc at Current Position - Image Data - Line at Given Position - Line at Current Position - Linear Gradient - Marker at Given Position - Marker at Current Position - No-Operation - Partial Arc at Given Position - Partial Arc at Current Position - Radial Gradient - Relative Line at Given Position - Relative Line at Current Position - Segment Characteristics - Set Arc Parameters - Set Background Mix - Set Character Angle - Set Character Cell - Set Character Direction - Set Character Precision [GOCA-7-009]

---

- Set Character Set - Set Character Shear - Set Color - Set Current Position - Set Custom Line Type - Set Extended Color - Set Fractional Line Width - Set Line End - Set Line Join - Set Line Type - Set Line Width - Set Marker Cell - Set Marker Set - Set Marker Symbol - Set Mix - Set Pattern Reference Point - Set Pattern Set - Set Pattern Symbol - Set Process Color [GOCA-7-010]

---

Begin Segment Command
This command defines or modifies a segment, its properties, or both. It can be transmitted as part of the picture chain or as an unchained segment. If the segment is transmitted as part of the chain it is executed as received, then discarded. If a segment is transmitted as unchained, it is ignored.
Note: There is no formal End Segment command, and none is needed. However , the reserved fixed two-byte drawing order with order code X'71' is used as an End Segment drawing order by some applications.
This order is treated as a No-Op in AFP GOCA.
Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'70' | Begin | Segment command [GOCA-7-011]|
| 1 | UBIN | LENGTH | X'0C' | Length of following parameters [GOCA-7-012]|
| 2-5 | UBIN | NAME | Any | value Name of segment to be created; ignored [GOCA-7-013]|
| 6 | BITS | FLAG1 | Any | value Ignored [GOCA-7-014]|
| 7 | BITS | FLAG2 | Segment | Properties 2 [GOCA-7-015]|
| Bit | 0 | CHAIN | | B'0',B'1' [GOCA-7-016]|
| B'0' Chained [GOCA-7-017]| | | | |
| B'1' Unchained [GOCA-7-018]| | | | |
| Bits | 1-2 | CHPOS | Any | value Ignored [GOCA-7-019]|
| Bit | 3 | PROLOG | | B'0',B'1' [GOCA-7-020]|
| B'0' No Prolog [GOCA-7-021]| | | | |
| B'1' Prolog [GOCA-7-022]| | | | |
| Bit | 4 | Any | value | Ignored [GOCA-7-023]|
| Bits | 5-6 | APP | B'00',B'01',B'10', | or B'1 1' [GOCA-7-024]|
| B'00' New segment [GOCA-7-025]| | | | |
| B'01' Reserved [GOCA-7-026]| | | | |
| B'10' Reserved [GOCA-7-027]| | | | |
| B'1 | 1' | Append | the | specified data to the end of the existing segment [GOCA-7-028]|
| Bit | 7 | DATAFL | Any | value Ignored [GOCA-7-029]|
| 8-9 | UBIN | SEGL | X'0000'-X'FFFF' | Segment data length [GOCA-7-030]|
| 10-13 | CHAR | P/SNAME | Any | value Predecessor/successor name; ignored [GOCA-7-031]|
| The | segment | data | in | the form of drawing orders follows immediately in the following format: [GOCA-7-032]|
| Offset | Type | Name | Range | Meaning [GOCA-7-033]|
| 0-n | SEGDATA | Segment | data; | the number of bytes is equal to the number defined by SEGL [GOCA-7-034]|
| Semantics [GOCA-7-035]| | | | |
| This | command | defines | a | segment for immediate execution. It consists of a command code, a command length, and a parameters section. The command is followed by a data section that contains the drawing orders for the [GOCA-7-036]|
| segment. | Bytes | 0-13 | of | this command are often referred to as the Begin Segment Introducer (BSI). [GOCA-7-037]|
| Begin Segment Command [GOCA-7-038]| | | | |

---

Byte 0 The command code is X'70' for a Begin Segment command.
LENGTH Specifies the length of the parameter section, that is, it does not include the length of the command code or its own length. This length must be X'0C'.
FLAG2 Contains the CHAIN, PROLOG, and APP flags, which are used to set various segment properties for the specified segment.
CHAIN Indicates whether this segment is chained or not. Chained segments define the picture, unchained segments are ignored in AFP GOCA.
PROLOG Indicates whether the segment has a prolog section at the beginning of the segment data.
APP Provides the following functions:
Value Description
B'00' New Segment. In chained immediate mode, if the segment starts a chain of appended segments, the prolog property is saved. The segment data in a new segment is processed with the drawing defaults.
B'01' Reserved. The command is rejected.
B'10' Reserved. The command is rejected.
B'1 1' Append data to segment. In chained immediate mode, if the segment is the first in a graphics object, the command can be rejected; if accepted, it is treated as a new segment. In chained immediate mode, if the segment is not the first in a graphics object, the segment data is considered to be a continuation of the data that followed the
Begin Segment command immediately prior to the current Begin
Segment command. This data is invoked with the current values of the drawing attributes, not with the drawing defaults, and uses the value of the prolog property that was saved in a previous New
Segment command. This means that a prolog that is started in a new segment may be terminated with an End Prolog order in one of the appended segments.
The appended segment data may possibly complete any unfinished drawing order , area, image definition, or prolog.
SEGL Specifies the length of the data section. A zero length for the data section is invalid when creating a new segment, but this does not cause an exception in AFP GOCA.
SEGDATA The data section for the segment. This parameter contains the drawing orders.
The actual mechanism by which these Communication Process Checks are reported is environment dependent.
CPC-0001 Invalid command code specified.
CPC-7001 APP has the value B'10'.
CPC-7082 APP has the value B'01'.
CPC-70C1 Invalid parameter length specified.
CPC-70C5 Insufficien t data. Segment data is less than length specified by SEGL.
Begin Segment Command [GOCA-7-039]

---

Order Formats
Drawing orders are represented in one of four formats, depending on the length of the operand data:
• Fixed 1-byte format is used for orders that have no operand data. [GOCA-7-040]
• Fixed 2-byte format is used for orders that have a single byte of operand data. [GOCA-7-041]
• Long format is used for orders with up to 255 bytes of operand data. [GOCA-7-042]
• Extended format is used for orders with up to 65,535 bytes of operand data. [GOCA-7-043]
The format of an order is determined by its order code:
• One fixed 1-byte order has an order code of X'00'. [GOCA-7-044]
• For fixed 2-byte orders, bit 0 is set to 0, and bit 4 is set to 1, that is, the first digit of the order code is less than 8, and the second digit is greater than, or equal to, 8. [GOCA-7-045]
• Orders that are not any of the other three formats are long format orders. [GOCA-7-046]
• Extended orders have an order code of X'FE', which introduces the extended format. [GOCA-7-047]
Order Formats

---

Fixed 1-Byte Format
The fixed 1-byte order format has a 1-byte order code only . One order has this format: the No-Operation (GNOP1) order .
Figure 34. Fixed 1-Byte Order Format
Order code
Fixed 2-Byte Format
The fixed 2-byte format has a 1-byte order code and one byte of data:
Figure 35. Fixed 2-Byte Order Format
Order code Operand
Long Format
The long format has a 1-byte order code and a 1-byte length field followed by the number of data bytes specified by the length field. The value of the length field is the length of the operand data in bytes; that is, the length does not include the order code or length fields.
Figure 36. Long Order Format
Order code Length
⋮
Operand data
⋮
Extended Format
The extended format has a 1-byte order code, X'FE', and a 1-byte qualifier field, followed by a 2-byte length field, followed by the number of data bytes specified by the length field. The value of the length field is the length of the operand data, in bytes; that is, the length does not include the order code, qualifier , or length fields.
Figure 37. Extended Order Format
Order code Qualifier
Length
⋮
Operand data
⋮
Order Formats

---

Order Alignment
Orders can be followed by any number of No-Operation (GNOP1) or Comment (GCOMT) orders, to align the next order on any convenient byte boundary . Orders can be aligned on any byte boundary , although, depending on the implementation, a performance benefit can be obtained if orders are aligned on even-byte boundaries. Note that a drawing order may start in one segment and be completed in an appended segment.
Current Position in Drawing Orders
Some orders have two forms. One form uses the current position as one of its coordinate values; the other form does not. See “Current Position” for more details.
Coordinate Data
Coordinate data is used in orders to specify points in GPS, each point being specified by a set of two parameters, X g and Y g
. The sequence of parameters in coordinate data is (X g , Y g ); the format of the parameters is 16-bit twos-complement signed binary integers (SBIN). The drawing processor interprets coordinate data and raises an exception condition if the length of the data is not consistent with complete specification of points. [GOCA-7-048]
Offset Data
Offs et data is used in orders to specify a point in GPS by specifying its offset from a given point. Each point is specified by a set of two parameters. The sequence of parameters in offset data is x,y; the format of the parameters is 8-bit twos-complement signed binary integers (SBIN).
Default Values
The defaulting mechanism used is as follows. See also Figure 33.
• All current attributes and drawing process controls are set to their default values when the environment containing the graphics processor is initialized. These default values are referred to as the standard defaults. [GOCA-7-049]
The standard defaults are either:
- Environment-dependent values. [GOCA-7-050]
- Architected values, that is, one of the values that can be selected with a nonzero attribute value has been architected as the default. [GOCA-7-051]
• The standard defaults are copied into another set of defaults, referred to as the current defaults, when the graphics processor is initialized. [GOCA-7-052]
• The current defaults can be changed by the Set Current Defaults control instruction. [GOCA-7-053]
• When a drawing process is initiated, the current defaults are copied into a set of defaults called the drawing defaults. These are the defaults that are assumed during the execution of the drawing process. [GOCA-7-054]
The drawing defaults apply during the whole of the drawing process. They cannot be changed by a control instruction while the drawing process is being executed.
• The current values of the primitive attributes are either set or propagated at the start of a segment. “Current [GOCA-7-055]
Attributes” describes how the initial values are determined.
Default Values

---

• The current values of the drawing process controls are either set or propagated at the start of a segment. [GOCA-7-056]
“Drawing Process Controls” describes how the initial values are determined. [GOCA-7-057]
### Summary List of Orders

| Length | Hex | Order | Meaning |
| :--- | :--- | :--- | :--- |
| 1-byte | X'00' | GNOP1 | No-Operation [GOCA-7-058]|
| Long | X'01' | GCOMT | Comment [GOCA-7-059]|
| Long | X'04' | GSGCH | Segment Characteristics [GOCA-7-060]|
| 2-byte | X'08' | GSPS | Set Pattern Set [GOCA-7-061]|
| 2-byte | X'0A' | GSCOL | Set Color [GOCA-7-062]|
| 2-byte | X'0C' | GSMX | Set Mix [GOCA-7-063]|
| 2-byte | X'0D' | GSBMX | Set Background Mix [GOCA-7-064]|
| Long | X'11' | GSFLW | Set Fractional Line Width [GOCA-7-065]|
| 2-byte | X'18' | GSLT | Set Line Type [GOCA-7-066]|
| 2-byte | X'19' | GSLW | Set Line Width [GOCA-7-067]|
| 2-byte | X'1A' | GSLE | Set Line End [GOCA-7-068]|
| 2-byte | X'1B' | GSLJ | Set Line Join [GOCA-7-069]|
| Long | X'20' | GSCLT | Set Custom Line Type [GOCA-7-070]|
| Long | X'21' | GSCP | Set Current Position [GOCA-7-071]|
| Long | X'22' | GSAP | Set Arc Parameters [GOCA-7-072]|
| Long | X'26' | GSECOL | Set Extended Color [GOCA-7-073]|
| 2-byte | X'28' | GSPT | Set Pattern Symbol [GOCA-7-074]|
| 2-byte | X'29' | GSMT | Set Marker Symbol [GOCA-7-075]|
| Long | X'33' | GSCC | Set Character Cell [GOCA-7-076]|
| Long | X'34' | GSCA | Set Character Angle [GOCA-7-077]|
| Long | X'35' | GSCH | Set Character Shear [GOCA-7-078]|
| Long | X'37' | GSMC | Set Marker Cell [GOCA-7-079]|
| 2-byte | X'38' | GSCS | Set Character Set [GOCA-7-080]|
| 2-byte | X'39' | GSCR | Set Character Precision [GOCA-7-081]|
| 2-byte | X'3A' | GSCD | Set Character Direction [GOCA-7-082]|
| 2-byte | X'3B' | GSMP | Set Marker Precision (obsolete) [GOCA-7-083]|
| 2-byte | X'3C' | GSMS | Set Marker Set [GOCA-7-084]|
| 2-byte | X'3E' | GEPROL | End Prolog [GOCA-7-085]|
| 2-byte | X'5E' | GECP | End Custom Pattern [GOCA-7-086]|
| Long | X'60' | GEAR | End Area [GOCA-7-087]|
| 2-byte | X'68' | GBAR | Begin Area [GOCA-7-088]|
| Long | X'80' | GCBOX | Box at Current Position [GOCA-7-089]|
| Long | X'81' | GCLINE | Line at Current Position [GOCA-7-090]|
| Long | X'82' | GCMRK | Marker at Current Position [GOCA-7-091]|
| Long | X'83' | GCCHST | Character String at Current Position [GOCA-7-092]|
| Long | X'85' | GCFLT | Fillet at Current Position [GOCA-7-093]|
| Long | X'87' | GCFARC | Full Arc at Current Position [GOCA-7-094]|
| Long | X'91' | GCBIMG | Begin Image at Current Position [GOCA-7-095]|
| Long | X'92' | GIMD | Image Data [GOCA-7-096]|
| Long | X'93' | GEIMG | End Image [GOCA-7-097]|
| Long | X'A0' | GSPRP | Set Pattern Reference Point [GOCA-7-098]|
| Long | X'A1' | GCRLINE | Relative Line at Current Position [GOCA-7-099]|
| Long | X'A3' | GCPARC | Partial Arc at Current Position [GOCA-7-100]|
| Long | X'A5' | GCCBEZ | Cubic Bezier Curve at Current Position [GOCA-7-101]|
| Long | X'B2' | GSPCOL | Set Process Color [GOCA-7-102]|
| Long | X'C0' | GBOX | Box at Given Position [GOCA-7-103]|
| Long | X'C1' | GLINE | Line at Given Position [GOCA-7-104]|
| Long | X'C2' | GMRK | Marker at Given Position [GOCA-7-105]|
| Long | X'C3' | GCHST | Character String at Given Position [GOCA-7-106]|
| Long | X'C5' | GFLT | Fillet at Given Position [GOCA-7-107]|
| Long | X'C7' | GFARC | Full Arc at Given Position [GOCA-7-108]|
| Long | X'D1' | GBIMG | Begin Image at Given Position [GOCA-7-109]|
| Long | X'DE' | GBCP | Begin Custom Pattern [GOCA-7-110]|
| Long | X'DF' | GDPT | Delete Pattern [GOCA-7-111]|
| Long | X'E1' | GRLINE | Relative Line at Given Position [GOCA-7-112]|
| Long | X'E3' | GPARC | Partial Arc at Given Position [GOCA-7-113]|
| Long | X'E5' | GCBEZ | Cubic Bezier Curve at Given Position [GOCA-7-114]|
| Extended | X'FEDC' | GLGD | Linear Gradient [GOCA-7-115]|
| Extended | X'FEDD' | GRGD | Radial Gradient [GOCA-7-116]|

**Architecture Note:** Some AFP printers accept the following drawing orders and process them as No-Ops:
* Set Pick Identifier (GSPIK, X'43'). This drawing order is in long format. [GOCA-7-117]
* End Segment drawing order (X'71'). This drawing order is in fixed 2-byte format, where the second byte is reserved and should be set to X'00'. [GOCA-7-118]

---

### Begin Area (GBAR) Order

This order indicates the start of a set of primitives that define an area boundary. [GOCA-7-119]

#### GBAR Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'68' | GBAR | Order code [GOCA-7-120]|
| 1 | BITS | FLAGS | | Internal flags:<br>bit 0 RES1 (B'1'): Reserved for migration; only valid value<br>bit 1 BOUNDARY: Boundary-line draw indicator:<br> - B'0' Do not draw boundary lines<br> - B'1' Draw boundary lines<br>bit 2 INSIDE: Mode to determine inside:<br> - B'0' Alternate mode<br> - B'1' Nonzero Winding mode<br>bits 3–7 RES2 (B'00000'): Reserved; only valid value [GOCA-7-121]|
| Semantics [GOCA-7-122]| | | | |
| The | Begin | Area | order | starts the definition of a filled area. The area definition must be terminated by an End [GOCA-7-123]|
| Area | order | . | See | “Areas” for details of the area definition. [GOCA-7-124]|
| The | BOUNDARY | bit | determines | whether or not the boundary of the area is drawn. The INSIDE bit determines the method of filling the interior . [GOCA-7-125]|
| The | pattern | set, | pattern | symbol, pattern mix, and pattern background mix attributes that are current when the [GOCA-7-126]|
| Begin | Area | order | is | executed are used to fill the area. When using the default pattern set or a bilevel custom pattern (but not when using a full-color custom pattern or gradient ), the pattern color attribute that is current [GOCA-7-127]|
| when | the | Begin | Area | order was executed is also used to fill the area. When using a custom pattern, the pattern reference point attribute that is current when the Begin Area order was executed is also used in generating the [GOCA-7-128]|
| fill | pattern. | The | line | attributes that are current at the time the orders defining the boundary are executed are used to draw the boundary . [GOCA-7-129]|
| The | value | of | the | current position is not changed by the Begin Area order itself, but is changed by those orders used to define the area boundary , including any implicit figure-closing orders that are required. [GOCA-7-130]|
| Area | orders | must | not | be nested and an area must be defined completely within a single segment. [GOCA-7-131]|
| For | an | area | within | an immediate-mode segment, temporary storage can be required. An exception condition, [GOCA-7-132]|
| EC-6805, | is | set | if | this storage overflows. [GOCA-7-133]|
| RES1 | is | set | to | B'1' for compatibility with old implementations. Generators must set this value, but receivers can ignore this bit. [GOCA-7-134]|
| Implementation | Note: | The | Nonzero | Winding mode value of the INSIDE bit was added to the architecture later than the Alternate mode value. As such, not all GOCA receivers support Nonzero Winding mode, and [GOCA-7-135]|
| some | receivers | will | ignore | the new value and use Alternate mode instead. Other receivers might report the retired exception condition EC-0002 if passed a Begin Area drawing order with INSIDE=B'1'. [GOCA-7-136]|
| Begin Area [GOCA-7-137]| | | | |

---

The following exception conditions raise a drawing process check:
EC-6800 A Begin Area order has been executed after another Begin Area order , without an intervening
End Area order being executed.
EC-6801 A Begin Area order has been executed in a segment, and the end of the segment is reached without an End Area order having been executed.
Note: This exception condition is raised when the end of the segment is reached.
EC-6802 A supported order that is invalid within an area is detected.
Note: This exception condition is raised when the order that is invalid is detected.
The following exception conditions cause a standard action to be taken:
EC-6803 The pattern set identified by the current pattern set is not supported . This exception condition exists even if the current pattern symbol is set to X'00'.
Standard action: The standard default pattern set is used.
EC-6804 The current pattern symbol identifies an undefined symbol in the current pattern set.
Standard action: The standard default pattern symbol is used. In AFP environments this is
X'10'—Solid fill.
EC-6805 T emporary storage overflow while drawing an area in an immediate segment.
Standard action: Drawing of the area is completed in an implementation-defined manner .
EC-6806 While inside the definition of a custom pattern, a Begin Area order has been executed but the current pattern symbol identifies a custom pattern or gradient .
Standard action: The standard default pattern symbol is used. In AFP environments this is
X'10'—Solid fill.
Begin Area

---

### Begin Custom Pattern (GBCP) Order

This order indicates the start of a set of primitives that define a custom pattern. [GOCA-7-138]

#### GBCP Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'DE' | GBCP | Order code [GOCA-7-139]|
| 1 | UBIN | LENGTH | 13 | Length of following data [GOCA-7-140]|
| 2–3 | RES1 | X'0000' | Reserved; only valid value [GOCA-7-141]|
| 4 | BITS | FLAGS | | Custom Pattern flags:<br>bit 0 FULLCOLOR:<br> - B'0' Bilevel custom pattern<br> - B'1' Full-color custom pattern<br>bits 1–7 RES2 (B'0000000'): Reserved; only valid value [GOCA-7-142]|
| 5 | CODE | PATTSET | X'01'–X'FD' | Pattern set of the custom pattern [GOCA-7-143]|
| 6 | CODE | PATTSYM | X'01'–X'FF' | Pattern symbol of the custom pattern [GOCA-7-144]|
| 7–8 | SBIN | XLWIND | X'8000'–X'7FFF' | $X_{g}$ coordinate for left edge of the pattern window [GOCA-7-145]|
| 9–10 | SBIN | XRWIND | X'8000'–X'7FFF' | $X_{g}$ coordinate for right edge of the pattern window [GOCA-7-146]|
| 11–12 | SBIN | YBWIND | X'8000'–X'7FFF' | $Y_{g}$ coordinate for bottom edge of the pattern window [GOCA-7-147]|
| 13–14 | SBIN | YTWIND | X'8000'–X'7FFF' | $Y_{g}$ coordinate for top edge of the pattern window [GOCA-7-148]|
| Semantics [GOCA-7-149]| | | | |
| The | Begin | Custom | Pattern | order starts the definition of a custom pattern. The custom pattern must be terminated by an End Custom Pattern order . [GOCA-7-150]|
| The | FULLCOLOR | flag | specifies | whether the custom pattern being defined is a bilevel custom pattern or full- [GOCA-7-151]|
| color | custom | pattern. | Both | types must be supported by drawing processors. A custom pattern cannot change from bilevel to full-color or vice versa. [GOCA-7-152]|
| See | “Custom | Patterns | ” | for details of custom patterns. [GOCA-7-153]|
| The | PATTSET | and | PATTSYM | values specify the pattern set and pattern symbol where this custom pattern will reside. When the current values of the pattern set and pattern symbol attributes specify these PATTSET and [GOCA-7-154]|
| PATTSYM | values, | respectively | , | this custom pattern will be used to do area fill. [GOCA-7-155]|
| Custom | patterns | (defined | with | this order), linear gradients (defined with the Linear Gradient order), and radial gradients (defined with the Radial Gradient order) share the pattern sets X'01'-X'FD'; the patterns using these [GOCA-7-156]|
| pattern | sets | can | be | any combination of custom patterns and gradients without restriction. [GOCA-7-157]|
| Definition | of | a | custom | pattern using this drawing order does not set the pattern set and pattern symbol attributes to use the custom pattern. T o use the custom pattern, the pattern set and pattern symbol attributes [GOCA-7-158]|
| must | be | explicitly | | set. [GOCA-7-159]|
| All | possible | valid | values | of the PATTSET and PATTSYM fields must be supported. There are 253 ∙ 255 = [GOCA-7-160]|
| 64,515 | possible | combinations | of | PATTSET and PATTSYM. However , it is not required that implementations [GOCA-7-161]|
| Begin Custom Pattern [GOCA-7-162]| | | | |

---

support the creation of 64,515 custom patterns. If insufficient storage is available to correctly handle a custom pattern definition, exception condition EC-DE06 is raised, for which the standard action is to skip the custom pattern definition.
The XLWIND, XR WIND, YBWIND, and YTWIND values specify the coordinates of the edges of the pattern window , in GPS units, in the temporary GPS used to draw the pattern. Only the parts of the picture drawn by the drawing orders in the custom pattern definition that lie within the pattern window make up the custom pattern. Any drawing done outside the window is discarded without raising an exception condition. If the pattern window values are specified in such a way as to define a zero- or negative-sized window , exception condition EC-DE04 is raised, for which the standard action is to skip the custom pattern definition.
Custom pattern definitions must not be nested and a custom pattern must be defined completely within a single segment. Similarly , a previously defined custom pattern cannot be used in the definition of a new custom pattern; an attempt to do so will cause exception EC-6806 to be raised.
It is not possible to replace a custom pattern or gradient simply by sending a Begin Custom Pattern with the same PATTSET and PATTSYM parameters. If this is attempted, exception EC-DE05 is raised, for which the standard action is to skip the custom pattern definition. T o replace a pattern at a given pattern set and pattern symbol, first delete the existing pattern using the Delete Pattern drawing order .
The following exception condition raises a drawing process check:
EC-0003 The order has an incorrect length.
The following exception conditions cause a standard action to be taken:
EC-DE00 A Begin Custom Pattern order has been executed after another Begin Custom Pattern order , without an intervening End Custom Pattern order being executed.
Standard action: Act as if an End Custom Pattern order had been received just prior to the current Begin Custom Pattern order .
EC-DE01 A Begin Custom Pattern order has been executed in a segment, and the end of the segment is reached without an End Custom Pattern order having been executed.
Note: This exception condition is raised when the end of the segment is reached.
Standard action: Act as if an End Custom Pattern order had been received just prior to the end of the segment.
EC-DE02 The value specified for the PATTSET parameter is invalid.
Standard action: Skip to the End Custom Pattern drawing order , ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order .
EC-DE03 The value specified for the PATTSYM parameter is invalid; a custom pattern cannot be assigned to pattern symbol X'00'.
Standard action: Skip to the End Custom Pattern drawing order , ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order .
EC-DE04 The pattern window values are ill defined: XLWIND is equal to or greater than XR WIND, or
YBWIND is equal to or greater than YTWIND.
Standard action: Skip to the End Custom Pattern drawing order , ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order .
EC-DE05 The PATTSET and PATTSYM values are within the valid range, but a pattern already resides at that location.
Begin Custom Pattern

---

Standard action: Skip to the End Custom Pattern drawing order , ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order .
EC-DE06 Insufficien t storage available to process and store a custom pattern.
Standard action: Skip to the End Custom Pattern drawing order , ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order .
EC-DE07 A supported order that is invalid within a custom pattern definition is detected.
Note: The exception condition is raised when the order that is invalid is detected.
Standard action: Ignore the invalid order .
Begin Custom Pattern

---

### Begin Image (GBIMG, GCBIMG) Orders

These orders identify the start of an image definition at a given position or at the current position. [GOCA-7-163]

#### GBIMG (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'D1' | GBIMG | Order code [GOCA-7-164]|
| 1 | UBIN | LENGTH | 10 | Length of following data [GOCA-7-165]|
| 2–3 | SBIN | XPOS | X'8000'–X'7FFF' | $X_{g}$ coordinate of image origin (first image point of first image scan line) [GOCA-7-166]|
| 4–5 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_{g}$ coordinate of image origin (first image point of first image scan line) [GOCA-7-167]|
| 6 | CODE | FORMAT | X'00' | Format of the image data:<br> - X'00' Each image point is mapped to a presentation device pel [GOCA-7-168]|
| 7 | RES | | X'00' | Reserved; only valid value [GOCA-7-169]|
| 8–9 | UBIN | WIDTH | X'0000'–X'FFFF' | Width of the image data, in image points [GOCA-7-170]|
| 10–11 | UBIN | HEIGHT | X'0000'–X'FFFF' | Height of the image data, in rows, or scan lines [GOCA-7-171]|

#### GCBIMG (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-172]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'91' | GCBIMG | Order code [GOCA-7-173]|
| 1 | UBIN | LENGTH | 6 | Length of following data [GOCA-7-174]|
| 2 | CODE | FORMAT | X'00' | Format of the image data:<br> - X'00' Each image point is mapped to a presentation device pel [GOCA-7-175]|
| 3 | RES | | X'00' | Reserved; only valid value [GOCA-7-176]|
| 4–5 | UBIN | WIDTH | X'0000'–X'FFFF' | Width of the image data, in image points [GOCA-7-177]|
| 6–7 | UBIN | HEIGHT | X'0000'–X'FFFF' | Height of the image data, in rows, or scan lines [GOCA-7-178]|
| Semantics [GOCA-7-179]| | | | |
| The | Begin | Image | at | Given Position (GBIMG) order defines an image at the specified position. The Begin [GOCA-7-180]|
| Image | at | Current | Position | (GCBIMG) order defines an image at the current position. [GOCA-7-181]|
| An | image | consists | of | a rectangular region of points and is defined by a sequence of orders, starting with a [GOCA-7-182]|
| Begin | Image | order | and | ending with an End Image order . Between these two orders are one or more Image [GOCA-7-183]|
| Data, | Comment, | or | No-Operation | orders, and these are the only orders permitted. [GOCA-7-184]|
| The | XPOS | and | YPOS | parameters define the position of the image origin—that is, the first point of the first row—which is at the top-left corner of the image. This origin is defined in GPS units. [GOCA-7-185]|
| Begin Image [GOCA-7-186]| | | | |

---

If a particular bit in the image data is B'1', it defines a foreground pel, and the point is drawn using the current values of the image mix and color attributes. If the bit is B'0', it defines a background pel, and the point is drawn using the current value of the image background mix attribute.
There is an Image Data order for every row of the image. That is, for an image n rows high there must be n
Image Data orders between the Begin Image and End Image orders. Each Image Data order contains suff icient integral bytes of data for the width of the image. If the width of the image is not an integral number of bytes, the padding bits at the right-hand end of the last byte in the Image Data order are ignored.
When the image is not contained in a custom pattern definition, each image point is mapped to a presentation device pel, unless the image resolution is explicitly specified in the Graphics Data Descriptor; see “Window
Specification (Mandatory)”. If the image resolution is explicitly specified, the mapping may include resolution correction so that the image is presented at its original size.
Implementation Note: In the absence of any other image resolution information, AFP printers map image points to device pels as follows:
• Printers that have a fixed resolution map point-to-pel at that resolution. [GOCA-7-187]
• Printers that have an acceptance mode for a fixed resolution map point-to-pel at the acceptance-mode resolution and then scale to the device resolution. [GOCA-7-188]
• Printers that have a fixed resolution but scale transparently to a diff erent device resolution map point- to-pel at the fixed resolution and then scale to the device resolution. [GOCA-7-189]
• Printers that support multiple raster source resolutions map point-to-pel to the single (maximum) device resolution reported in the IPDS XOH-OPC IM-Image and Coded-Font Resolution self-defining field. Such printers normally also provide acceptance modes at lower resolutions, so that if the GOCA image size is too small at the device resolution, the customer can switch to a lower-resolution acceptance mode. [GOCA-7-190]
• The image is not scaled when a scale-to-fit or scale-to-fill mapping to the object area is specified for the graphics object. [GOCA-7-191]
When the image is contained in a custom pattern definition, each image point is mapped into the temporary
GPS in a 1-to-1 manner , such that, for example, an image that is 100x200 image points would appear in the custom pattern definition’s temporary GPS at size 100x200 GPS units.
The image is drawn in HEIGHT rows, each row having WIDTH image points. Each row is drawn by taking sequential bits from the corresponding Image Data order , and drawing them in sequential points left-to-right in the horizontal direction. The first row , first Image Data order , starts at the image origin point. Each subsequent row (HEIGHT-1 successive Image Data orders) starts at a point adjacent to the first point of the previous row , in the top-to-bottom direction. The first row of the image is oriented parallel to the GPS X g axis, and subsequent rows are generated in the negative Y g direction.
Architecture Notes:
1. The practical limit for the WIDTH parameter range is 2040, which is the maximum number of bits in the [GOCA-7-192]
Image Data order .
2. Some presentation devices support a smaller range than X'0000'-X'FFFF' for the HEIGHT parameter . [GOCA-7-193]
Current position is set to the image origin. The current values of the color , mix, and background mix attributes are taken into account when drawing the image.
An image definition must be completely within one segment, that is, the Begin Image, Image Data, and End
Image orders that define a particular image must all be in one segment. Note that a segment may consist of a new segment followed by one or more appended segments. T ogether they are treated as one segment;
therefore, an image definition may start in a new or appended segment and finish in an appended segment.
Begin Image

---

The following exception conditions raise a drawing process check:
EC-0003 The order has an incorrect length.
EC-D100 A Begin Image order has been executed in a segment, and the end of the segment is reached without an End Image order having been executed.
Note: This exception condition is raised when the end of the segment is reached.
EC-D101 A Begin Image order has been executed in a segment, and a supported order other than a
Comment, No-Operation, Image Data, or End Image order is executed.
Note: This exception condition is raised when the order that is invalid is detected.
EC-D102 The value specified for the FORMAT parameter is not supported.
The following exception conditions cause a standard action to be taken:
EC-D103 The value specified for the WIDTH parameter is too large to allow the environment to completely present the image.
Standard action: The width of the image is truncated to allow presentation of the smaller image.
EC-D104 The value specified for the HEIGHT parameter is too large to allow the environment to completely present the image.
Standard action: The height of the image is truncated to allow presentation of the smaller image.
Begin Image

---

### Box (GBOX, GCBOX) Orders

These orders define a box with square or round corners, drawn with its first corner at a given position or at the current position. [GOCA-7-194]

#### GBOX (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C0' | GBOX | Order code [GOCA-7-195]|
| 1 | UBIN | LENGTH | 10, 12, 14 | Length of following data [GOCA-7-196]|
| 2–3 | RES | | X'2000' | Reserved; only valid value [GOCA-7-197]|
| 4–5 | SBIN | XPOS0 | X'8000'–X'7FFF' | $X_{g}$ coordinate of first corner [GOCA-7-198]|
| 6–7 | SBIN | YPOS0 | X'8000'–X'7FFF' | $Y_{g}$ coordinate of first corner [GOCA-7-199]|
| 8–9 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_{g}$ coordinate of diagonal corner [GOCA-7-200]|
| 10–11 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_{g}$ coordinate of diagonal corner [GOCA-7-201]|
| _Optional:_ [GOCA-7-202]| | | | |
| 12–13 | UBIN | HAXIS | 0–32,767 | Full length of x-direction axis of ellipse for rounded corner [GOCA-7-203]|
| 14–15 | UBIN | VAXIS | 0–32,767 | Full length of y-direction axis of ellipse for rounded corner [GOCA-7-204]|

#### GCBOX (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-205]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'80' | GCBOX | Order code [GOCA-7-206]|
| 1 | UBIN | LENGTH | 6, 8, 10 | Length of following data [GOCA-7-207]|
| 2–3 | RES | | X'2000' | Reserved; only valid value [GOCA-7-208]|
| 4–5 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_{g}$ coordinate of diagonal corner [GOCA-7-209]|
| 6–7 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_{g}$ coordinate of diagonal corner [GOCA-7-210]|
| _Optional:_ [GOCA-7-211]| | | | |
| 8–9 | UBIN | HAXIS | 0–32,767 | Full length of x-direction axis of ellipse for rounded corner [GOCA-7-212]|
| 10–11 | UBIN | VAXIS | 0–32,767 | Full length of y-direction axis of ellipse for rounded corner [GOCA-7-213]|
| Semantics [GOCA-7-214]| | | | |
| The | Box | at | Given | Position (GBOX) order defines a rectangular box with square or rounded corners with its first corner specified by the first coordinate pair , and the diagonally-opposite corner specified by the second [GOCA-7-215]|
| coordinate | pair | . | The | Box at Current Position (GCBOX) order defines a rectangular box with square or rounded [GOCA-7-216]|
| Box [GOCA-7-217]| | | | |

---

corners with its first corner at the current position, and the diagonally-opposite corner specified by the first coordinate pair . The sides of the rectangle are drawn parallel to the GPS X g , Y g axes.
For the GBOX order , the box is drawn in a counterclockwise direction in GPS if the value of (XPOS1-XPOS0)/ (YPOS1-YPOS0) is positive, and in a clockwise direction otherwise. For the GCBOX order , the box is drawn in a counterclockwise direction in GPS if the value of (XPOS1-x
CP )/(YPOS1-y
CP ) is positive, and in a clockwise direction otherwise, where (x
CP , y
CP ) are the coordinates of the current position.
Implementation Note: For historical reasons, not all GOCA receivers support the directionality of boxes;
therefore, some receivers will draw all boxes in a counterclockwise direction.
If this drawing order is in an area definition, the box is treated as a closed figure. The BOUNDARY parameter in the Begin Area order determines whether the boundary of the box is drawn.
If HAXIS and V AXIS are omitted, or HAXIS or V AXIS is zero, a rectangular box with square corners is drawn with its first corner at the current position (GCBOX), or at (XPOS0,YPOS0) (GBOX). The diagonally-opposite corner is at (XPOS1,YPOS1). If the values of HAXIS and V AXIS are nonzero, and HAXIS is not equal to
V AXIS, a similar rectangle is drawn, except that the corners are replaced by quarter ellipses whose full axes are HAXIS and V AXIS. IF HAXIS = V AXIS, the corners are quadrants of a circle whose diameter is HAXIS. If
V AXIS is omitted, it is assumed to be equal to HAXIS.
Note: HAXIS cannot be omitted without omitting V AXIS as well.
The current values of the line attributes, except for line join, are taken into account when drawing the boundary of the box. The line end attribute is used only for the internal ends of dotted or dashed lines.
Current position is set to (XPOS0,YPOS0) (GBOX), or is unchanged (GCBOX).
The following exception condition raises a drawing process check:
EC-0003 The order has an incorrect length.
The following exception conditions cause a standard action to be taken:
EC-C000 The HAXIS or V AXIS parameter is too large to fit the indicated corner into the size of the box.
Standard action: Corners with the largest axis that fit the box are drawn.
EC-C001 Either the HAXIS or V AXIS parameter is outside the range.
Standard action: A box with square corners is drawn.
Box

---

### Character String (GCHST, GCCHST) Orders

These orders draw a character string at a given position or at the current position. [GOCA-7-218]

#### GCHST (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C3' | GCHST | Order code [GOCA-7-219]|
| 1 | UBIN | LENGTH | 4–255 | Length of following data [GOCA-7-220]|
| 2–3 | SBIN | XPOS | X'8000'–X'7FFF' | $X_{g}$ coordinate of character string origin [GOCA-7-221]|
| 4–5 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_{g}$ coordinate of character string origin [GOCA-7-222]|
| 6–n | CHAR | CP | | Code points of each character in the string [GOCA-7-223]|

#### GCCHST (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-224]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'83' | GCCHST | Order code [GOCA-7-225]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-226]|
| 2–n | CHAR | CP | | Code points of each character in the string [GOCA-7-227]|
| Semantics [GOCA-7-228]| | | | |
| The | Character | String | at | Given Position (GCHST) order draws a character string that starts at the specified position. The Character String at Current Position (GCCHST) order draws a character string that starts at the [GOCA-7-229]|
| current position. [GOCA-7-230]| | | | |
| Note: | The | current | position | is changed to (XPOS,YPOS) (GCHST), or is unchanged (GCCHST). [GOCA-7-231]|
| The | font | from | which | the character definitions are to be obtained is given by the value of the current character set attribute. If the font identified by the value in the current character set attribute is not available, EC-C300 is [GOCA-7-232]|
| raised. | The | standard | action | for EC-C300 is to use the standard default character set. [GOCA-7-233]|
| The | particular | character | definitions | identified by the current character set are determined by the code points in the Character String order . The length of the code points is determined by the font. [GOCA-7-234]|
| All | code | points | in | the Character String order must refer to valid graphic characters. If they do not, EC-C301 is raised. The standard action for EC-C301 is to use the standard default character symbol. [GOCA-7-235]|
| The | color | of | the | foreground of all characters in the string is given by the current value of the character color attribute. [GOCA-7-236]|
| The | way | in | which | characters in the string are merged with any output primitives that have already been drawn is controlled by the values of the character mix and background mix attributes. [GOCA-7-237]|
| The | current | values | of | the line type, line width, pattern set, and pattern symbol attributes have no effect on the appearance of the characters in the string. [GOCA-7-238]|
| Character String [GOCA-7-239]| | | | |

---

A Character String at Given Position (GCHST) order with an initial position (XPOS,YPOS), but with no string of code points, is permitted. This serves only to move the current position. A Character String at Current Position (GCCHST) order with no string of code points is permitted and is treated as a No-Op.
The Set Character Precision, Set Character Set, Set Character Cell, Set Character Angle, and Set Character
Direction drawing orders determine the character size, character rotation, and character direction. For more information, see “Character Strings”.
The following exception condition raises a drawing process check:
EC-0003 The order has an incorrect length.
The following exception conditions cause a standard action to be taken:
EC-C300 The font identified by the value in the current character set attribute is not available.
Standard action: The standard default character set is used. In AFP environments, this is the presentation device default font.
EC-C301 A code point in the order does not refer to a valid graphic character .
Standard action: The standard default character symbol is used.
EC-C302 The current character set attribute value identifies a character set definition that cannot support the functions implied by the current character precision attribute.
Standard action: The character set identified by the current character set attribute is used with the highest value of precision that the character set can support.
EC-C303 The character encoding is Unicode, but the code points in the drawing order are not valid
Unicode data. This can be caused by one of the following:
• A high-order surrogate code value was not immediately followed by a low-order surrogate code value. The high-order surrogate range is U+D800 through U+DBFF . [GOCA-7-240]
• A low-order surrogate code value was not immediately preceded by a high-order surrogate code value. The low-order surrogate range is U+DC00 through U+DFFF . [GOCA-7-241]
• An illegal UTF-8 byte sequence, as defined in the Unicode Specification, was specified. [GOCA-7-242]
Standard action: The remainder of the code points are skipped.
Application Note: For a formal definition of Unicode and UTF-8, consult the Unicode
Specification, which is available from the Unicode Consortium at www.unicode.org.
The illegal UTF-8 byte sequences can be summarized as follows:
• The value in the 1st byte of the UTF-8 byte sequence was not in the legal UTF-8 range (X'00' - X'7F' and X'C2' - X'F4'). [GOCA-7-243]
• The value in the 2nd byte of the UTF-8 byte sequence was not in the legal UTF-8 range allowed by the value in the 1st byte. The valid ranges for the 2nd byte are shown in Table 14. [GOCA-7-244]
Table 14. V alid Values for UTF-8 First and Second Bytes
First Byte Second Byte
X'C2' - X'DF' X'80' - X'BF'
X'E0' X'A0' - X'BF'
X'E1' - X'EC' X'80' - X'BF'
Character String

---

Table 14 V alid Values for UTF-8 First and Second Bytes (cont'd.)
First Byte Second Byte
X'ED' X'80' - X'9F'
X'EE' - X'EF' X'80' - X'BF'
X'F0' X'90' - X'BF'
X'F1' - X'F3' X'80' - X'BF'
X'F4' X'80' - X'8F'
• The value in the 3rd or 4th byte of the UTF-8 byte sequence was not in the legal UTF- 8 range for that byte (X'80' - X'BF'). [GOCA-7-245]
Character String

---

### Comment (GCOMT) Order

This order enables data to be stored within a segment. [GOCA-7-246]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'01' | GCOMT | Order code [GOCA-7-247]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-248]|
| 2–n | UNDF | DATA | Any value | Comment data [GOCA-7-249]|

#### Semantics

This order is treated as a No-Op. It has no effect on the GPS or on any current attribute or control. The data within the order can be any value and is ignored. The order can appear anywhere within a segment. [GOCA-7-250]

This order does not raise any exception conditions. [GOCA-7-251]

---

### Cubic Bezier Curve (GCBEZ, GCCBEZ) Orders

These orders generate a Cubic Bezier Curve that starts at a given position or at the current position. [GOCA-7-252]

#### Syntax

##### Cubic Bezier Curve at Given Position (GCBEZ) Order

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'E5' | GCBEZ | Order code [GOCA-7-253]|
| 1 | UBIN | LENGTH | 4–n | Length of following data; n must be less than 255 and be equal to 12m + 4, where m is the number of curves [GOCA-7-254]|
| 2–3 | SBIN | XPOS0 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve start point [GOCA-7-255]|
| 4–5 | SBIN | YPOS0 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve start point [GOCA-7-256]|
| 6–7 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve, first control point [GOCA-7-257]|
| 8–9 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve, first control point [GOCA-7-258]|
| 10–11 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve, second control point [GOCA-7-259]|
| 12–13 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve, second control point [GOCA-7-260]|
| 14–15 | SBIN | XPOS3 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve endpoint, second curve start point [GOCA-7-261]|
| 16–17 | SBIN | YPOS3 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve endpoint, second curve start point [GOCA-7-262]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-263]|
| SBIN | XPOSF-2 | X'8000'–X'7FFF' | $X_g$ coordinate of final curve, first control point [GOCA-7-264]|
| SBIN | YPOSF-2 | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve, first control point [GOCA-7-265]|
| SBIN | XPOSF-1 | X'8000'–X'7FFF' | $X_g$ coordinate of final curve, second control point [GOCA-7-266]|
| SBIN | YPOSF-1 | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve, second control point [GOCA-7-267]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final curve endpoint [GOCA-7-268]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve endpoint [GOCA-7-269]|

##### Cubic Bezier Curve at Current Position (GCCBEZ) Order

| Offset | Type | Name | Range | Meaning [GOCA-7-270]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'A5' | GCCBEZ | Order code [GOCA-7-271]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; n must be less than 255 and be equal to 12m, where m is the number of curves [GOCA-7-272]|
| 2–3 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve, first control point [GOCA-7-273]|
| 4–5 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve, first control point [GOCA-7-274]|
| 6–7 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve, second control point [GOCA-7-275]|
| 8–9 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve, second control point [GOCA-7-276]|
| 10–11 | SBIN | XPOS3 | X'8000'–X'7FFF' | $X_g$ coordinate of first curve endpoint, second curve start point [GOCA-7-277]|
| 12–13 | SBIN | YPOS3 | X'8000'–X'7FFF' | $Y_g$ coordinate of first curve endpoint, second curve start point [GOCA-7-278]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-279]|
| SBIN | XPOSF-2 | X'8000'–X'7FFF' | $X_g$ coordinate of final curve, first control point [GOCA-7-280]|
| SBIN | YPOSF-2 | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve, first control point [GOCA-7-281]|
| SBIN | XPOSF-1 | X'8000'–X'7FFF' | $X_g$ coordinate of final curve, second control point [GOCA-7-282]|
| SBIN | YPOSF-1 | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve, second control point [GOCA-7-283]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final curve endpoint [GOCA-7-284]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final curve endpoint [GOCA-7-285]|

#### Semantics

The Cubic Bezier Curve at Given Position (GCBEZ) order generates a curve that starts at $P_0$ and uses points $P_1, P_2$, and $P_3$. The Cubic Bezier Curve at Current Position (GCCBEZ) order generates a curve that starts at the current position and uses points $P_1, P_2$, and $P_3$. [GOCA-7-286]

Further points are used in groups of three to form a polycurve. Each group of points, together with the last point of the previous curve, generates a new curve, every curve being drawn independently for the set of four points. [GOCA-7-287]

See “Cubic Bezier Curve” for details of curve drawing. [GOCA-7-288]

The length of the order, LENGTH, must be consistent with the two-byte x-coordinates and two-byte y-coordinates and the requirement for sets of points, three at a time after the initial curve. [GOCA-7-289]

The current values of the line attributes are taken into account when drawing the curve. [GOCA-7-290]

A Cubic Bezier Curve at Given Position (GCBEZ) order with only one point is permitted. This serves only to move the current position, which is set to the specified point. A Cubic Bezier Curve at Current Position (GCCBEZ) order with only one point (the current position) is permitted and is treated as a No-Op. [GOCA-7-291]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. The number of points, including the current position for the GCCBEZ drawing order, must equal $3m + 1$, where $m$ is the number of curves. Each point requires a length of 4 bytes. [GOCA-7-292]

---

### Delete Pattern (GDPT) Order

This order deletes a previously defined custom pattern or gradient, or deletes all previously defined custom patterns or gradients in a given pattern set. [GOCA-7-293]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'DF' | GDPT | Order code [GOCA-7-294]|
| 1 | UBIN | LENGTH | 3, 4 | Length of following data [GOCA-7-295]|
| 2–3 | RES | X'0000' | Reserved; only valid value [GOCA-7-296]|
| 4 | CODE | PATTSET | X'01'–X'FD' | Pattern set of the pattern(s) to be deleted [GOCA-7-297]|
| 5 | CODE | PATTSYM | X'01'–X'FF' | (Optional) Pattern symbol of the pattern to be deleted [GOCA-7-298]|

#### Semantics

The Delete Pattern order, when it specifies a pattern symbol value, deletes one single custom pattern or gradient that was previously defined using the Begin Custom Pattern, Linear Gradient, or Radial Gradient orders. When the Delete Pattern order does not specify a pattern symbol value, it deletes all previously defined patterns in the specified pattern set. [GOCA-7-299]

The PATTSET value specifies the pattern set of the pattern(s) to be deleted. The PATTSYM value, if included, specifies the pattern symbol of the pattern to be deleted. [GOCA-7-300]

A request to delete all patterns in a given pattern set does not raise an exception condition if that pattern set has no patterns defined in it. However, a request to delete a specific pattern that has not been defined raises exception condition EC-DF00, for which the standard action is to ignore the Delete Pattern order. [GOCA-7-301]

Patterns in the default pattern set cannot be deleted. An attempt to do so will raise exception condition EC-DF01, for which the standard action is to ignore the Delete Pattern order. [GOCA-7-302]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-303]

The following exception conditions cause a standard action to be taken:
- **EC-DF00**: The PATTSET and PATTSYM parameters are within the valid range, but no pattern exists with the pattern set and pattern symbol specified. [GOCA-7-304]
  - Standard action: Ignore the Delete Pattern order. [GOCA-7-305]
- **EC-DF01**: The value specified for the PATTSET parameter is invalid. [GOCA-7-306]
  - Standard action: Ignore the Delete Pattern order. [GOCA-7-307]
- **EC-DF02**: The value specified for the PATTSYM parameter is invalid; pattern symbol X'00' cannot be deleted. [GOCA-7-308]
  - Standard action: Ignore the Delete Pattern order. [GOCA-7-309]

---

### End Area (GEAR) Order

This order indicates the end of a set of primitives that define an area boundary. [GOCA-7-310]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'60' | GEAR | Order code [GOCA-7-311]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-312]|
| 2–n | DATA | X'00'... | Reserved; only valid value [GOCA-7-313]|

#### Semantics

The End Area order identifies the end of an area. The bytes of data on this order must all be X'00'. LENGTH is the number of bytes of zeros, and can be zero. [GOCA-7-314]

The following exception condition raises a drawing process check:
- **EC-6000**: An End Area order has been executed without an unmatched Begin Area order having previously been executed. [GOCA-7-315]

---

### End Custom Pattern (GECP) Order

This order indicates the end of a set of primitives that define a custom pattern. [GOCA-7-316]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'5E' | GECP | Order code [GOCA-7-317]|
| 1 | RES | X'00' | Reserved; only valid value [GOCA-7-318]|

#### Semantics

The End Custom Pattern order identifies the end of the definition of a custom pattern. [GOCA-7-319]

The following exception condition causes a standard action to be taken:
- **EC-5E00**: An End Custom Pattern order has been executed without an unmatched Begin Custom Pattern order having previously been executed. [GOCA-7-320]
  - Standard action: Ignore the End Custom Pattern order. [GOCA-7-321]

---

### End Image (GEIMG) Order

This order identifies the end of an image definition. [GOCA-7-322]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'93' | GEIMG | Order code [GOCA-7-323]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-324]|
| 2–n | DATA | X'00'... | Reserved; only valid value [GOCA-7-325]|

#### Semantics

The End Image order identifies the end of an image. The bytes of data on this order must all be X'00'. LENGTH is the number of bytes of zeros, and can be zero. [GOCA-7-326]

The following exception conditions raise a drawing process check:
- **EC-9300**: An End Image order is executed without an unmatched Begin Image order having been executed previously. [GOCA-7-327]
- **EC-9301**: The number of Image Data orders between the Begin Image and End Image orders is not equal to the number of rows in the image, as given by the value of HEIGHT in the Begin Image order. [GOCA-7-328]

---

### End Prolog (GEPROL) Order

This order indicates the end of the prolog of a segment. [GOCA-7-329]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'3E' | GEPROL | Order code [GOCA-7-330]|
| 1 | RES | X'00' | Reserved; only valid value [GOCA-7-331]|

#### Semantics

The End Prolog order indicates the end of the prolog section of a segment. See “Segment Prolog” for details of the processing of segment prologs. [GOCA-7-332]

The following exception conditions raise a drawing process check:
- **EC-000C**: One of the following conditions has occurred within the prolog section of a segment: [GOCA-7-333]
  - A supported order that is not valid within a prolog is specified. [GOCA-7-334]
  - The end of the segment has been reached without an End Prolog order. [GOCA-7-335]
- **EC-3E00**: An End Prolog order has occurred outside the prolog section of a segment. [GOCA-7-336]

---

### Fillet (GFLT, GCFLT) Orders

These orders draw a curved line tangential to a specified set of straight lines, at the given position or at the current position. [GOCA-7-337]

#### GFLT (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C5' | GFLT | Order code [GOCA-7-338]|
| 1 | UBIN | LENGTH | 4–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-339]|
| 2–3 | SBIN | XPOS0 | X'8000'–X'7FFF' | $X_g$ coordinate of first line start point [GOCA-7-340]|
| 4–5 | SBIN | YPOS0 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line start point [GOCA-7-341]|
| 6–7 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first line endpoint [GOCA-7-342]|
| 8–9 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line endpoint [GOCA-7-343]|
| 10–11 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of second line endpoint [GOCA-7-344]|
| 12–13 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of second line endpoint [GOCA-7-345]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-346]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final line endpoint [GOCA-7-347]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final line endpoint [GOCA-7-348]|

#### GCFLT (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-349]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'85' | GCFLT | Order code [GOCA-7-350]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-351]|
| 2–3 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first line endpoint [GOCA-7-352]|
| 4–5 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line endpoint [GOCA-7-353]|
| 6–7 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of second line endpoint [GOCA-7-354]|
| 8–9 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of second line endpoint [GOCA-7-355]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-356]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final line endpoint [GOCA-7-357]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final line endpoint [GOCA-7-358]|

#### Semantics

The Fillet at Given Position (GFLT) order generates a single curve that starts at a specified position. The Fillet at Current Position (GCFLT) order generates a single curve that starts at the current position. Additional points can be added to form a polycurve. [GOCA-7-359]

The points specified in the order are joined by imaginary straight lines and a curve is then fitted to the lines. The curve is tangential to the first line at its start point and to the last line at its end point. If there are intermediate lines, the curve is tangential to these lines at their center points. See “Fillet” for the definition of the curves drawn. [GOCA-7-360]

A Fillet at Given Position (GFLT) order with only an initial position is permitted. This serves only to move the current position. A Fillet at Current Position (GCFLT) order with only an initial position (the current position) is permitted and is treated as a No-Op. [GOCA-7-361]

When only two points are supplied, a straight line results. [GOCA-7-362]

The current values of the line attributes are taken into account when drawing the fillet, and the current position is set to the last point specified. [GOCA-7-363]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-364]

---

### Full Arc (GFARC, GCFARC) Orders

These orders construct a full circle or an ellipse with the center at a specified point or at the current position. [GOCA-7-365]

#### GFARC (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C7' | GFARC | Order code [GOCA-7-366]|
| 1 | UBIN | LENGTH | 6 | Length of following data [GOCA-7-367]|
| 2–3 | SBIN | XPOS | X'8000'–X'7FFF' | $X_g$ coordinate of the center of the circle or ellipse [GOCA-7-368]|
| 4–5 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_g$ coordinate of the center of the circle or ellipse [GOCA-7-369]|
| 6 | UBIN | MH | X'00'–X'FF' | Integer portion of multiplier [GOCA-7-370]|
| 7 | UBIN | MFR | X'00'–X'FF' | Fractional portion of multiplier [GOCA-7-371]|

#### GCFARC (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-372]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'87' | GCFARC | Order code [GOCA-7-373]|
| 1 | UBIN | LENGTH | 2 | Length of following data [GOCA-7-374]|
| 2 | UBIN | MH | X'00'–X'FF' | Integer portion of multiplier [GOCA-7-375]|
| 3 | UBIN | MFR | X'00'–X'FF' | Fractional portion of multiplier [GOCA-7-376]|

#### Semantics

The Full Arc at Given Position (GFARC) order constructs a circle or an ellipse with its center at the specified position. The Full Arc at Current Position (GCFARC) order constructs a circle or an ellipse with its center at the current position. A previous Set Arc Parameters drawing order determines the shape and orientation of the arc. [GOCA-7-377]

If no Set Arc Parameters drawing order has been received, the presentation process draws an arc using the drawing default values of the arc parameters. The drawing direction is defined by the determinant of the transform, which is defined by the arc parameters. [GOCA-7-378]

Note: The current position is set to (XPOS, YPOS) (GFARC), or is unchanged (GCFARC). [GOCA-7-379]

The current values of the line attributes, except for line join, are taken into account when drawing the full arc. The line end attribute is used only for the internal ends of dotted or dashed lines. [GOCA-7-380]

If this drawing order is in an area definition, the arc is treated as a closed figure. The BOUNDARY parameter in the Begin Area order determines whether the boundary of the arc is drawn. [GOCA-7-381]

MH specifies the integer portion of a scale factor; MFR specifies the fractional portion of the scale factor. A combined value of X'0000' specifies a scale factor of 0. A decimal point is implied between MH and MFR. The fractional portion of the scale factor is calculated by dividing MFR by 256. For example, if MFR=X'40', its decimal value is 64, which, divided by 256 results in a fractional component for the scale factor of 1/4. [GOCA-7-382]

For a circle, the radius is $(MH \cdot R + MFR \cdot R)$ where $R$ is the radius of the circle defined by the current arc parameters. For an ellipse, the major and minor axes are $(MH \cdot MAJ + MFR \cdot MAJ)$ and $(MH \cdot MIN + MFR \cdot MIN)$, where $MAJ$ and $MIN$ are the major and minor axes of the ellipse defined by the current arc parameters. [GOCA-7-383]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-384]

The following exception condition causes a standard action to be taken:
- **EC-C601**: The drawing processor has detected an exceptional condition that can prevent the drawing of the arc within the normal limits of pel accuracy. [GOCA-7-385]
  - Standard action: The arc is drawn as accurately as the implementation can define. This action might produce straight lines. [GOCA-7-386]

---

### Image Data (GIMD) Order

This order specifies the raster data for one scan line or row of an image. [GOCA-7-387]

#### GIMD Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'92' | GIMD | Order code [GOCA-7-388]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-389]|
| 2–n | BITS | DATA | Any value | Image Data [GOCA-7-390]|

#### Semantics

The Image Data order contains the data for one scan line or row of an image. Each Image Data order can contain any number of bytes of data, from zero up to a maximum of 255 bytes. [GOCA-7-391]

The current position is not changed by the order. [GOCA-7-392]

If the LENGTH parameter is not equal to the rounded-up quotient of image WIDTH divided by 8, there are too few or too many data bytes, and exception EC-9201 exists. [GOCA-7-393]

See “Begin Image (GBIMG, GCBIMG) Orders” for details of the image construct. [GOCA-7-394]

The following exception conditions raise a drawing process check:
- **EC-9200**: A Begin Image order was not executed before the Image Data order in this segment. [GOCA-7-395]
- **EC-9201**: There are insufficient, or too many, bytes of data in the Image Data order. [GOCA-7-396]
- **EC-9301**: The number of Image Data orders between the Begin Image and End Image orders is not equal to the number of rows in the image, as specified by the HEIGHT parameter in the Begin Image order. [GOCA-7-397]

---

### Line (GLINE, GCLINE) Orders

These orders define one or more connected straight lines, drawn from the given position or from the current position. [GOCA-7-398]

#### GLINE (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C1' | GLINE | Order code [GOCA-7-399]|
| 1 | UBIN | LENGTH | 4–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-400]|
| 2–3 | SBIN | XPOS0 | X'8000'–X'7FFF' | $X_g$ coordinate of first line start point [GOCA-7-401]|
| 4–5 | SBIN | YPOS0 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line start point [GOCA-7-402]|
| 6–7 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first line endpoint [GOCA-7-403]|
| 8–9 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line endpoint [GOCA-7-404]|
| 10–11 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of second line endpoint [GOCA-7-405]|
| 12–13 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of second line endpoint [GOCA-7-406]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-407]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final line endpoint [GOCA-7-408]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final line endpoint [GOCA-7-409]|

#### GCLINE (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-410]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'81' | GCLINE | Order code [GOCA-7-411]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-412]|
| 2–3 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of first line endpoint [GOCA-7-413]|
| 4–5 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of first line endpoint [GOCA-7-414]|
| 6–7 | SBIN | XPOS2 | X'8000'–X'7FFF' | $X_g$ coordinate of second line endpoint [GOCA-7-415]|
| 8–9 | SBIN | YPOS2 | X'8000'–X'7FFF' | $Y_g$ coordinate of second line endpoint [GOCA-7-416]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-417]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final line endpoint [GOCA-7-418]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final line endpoint [GOCA-7-419]|

#### Semantics

The Line at Given Position (GLINE) order draws a line from the point specified by the first pair of coordinates to the point specified by the second pair of coordinates. If additional coordinate pairs are specified, the presentation process draws a line from the previous endpoint to the next coordinate pair. [GOCA-7-420]

The Line at Current Position (GCLINE) order draws a line from the current position to the point specified by the first coordinate pair. If additional coordinate pairs are specified, the presentation process draws a line from the previous endpoint to the next coordinate pair. Consecutive points in the orders are joined by straight lines. [GOCA-7-421]

The current values of the line attributes are taken into account when drawing the line. The current position is set to the last point specified. [GOCA-7-422]

A Line at Given Position (GLINE) order with only an initial position is permitted. This form of GLINE moves the current position. A Line at Current Position (GCLINE) order with only an initial position (the current position) is permitted and is treated as a No-Op. [GOCA-7-423]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-424]

---

### Linear Gradient (GLGD) Order

This order defines a linear gradient to be used to fill an area. [GOCA-7-425]

#### GLGD Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'FE' | Extended | Format order code [GOCA-7-426]|
| 1 | CODE | X'DC' | GLGD | Qualifier code [GOCA-7-427]|
| 2–3 | UBIN | LENGTH | 29–65,535 | Length of following data [GOCA-7-428]|
| 4–5 | RES | X'0000' | Reserved; only valid value [GOCA-7-429]|
| 6 | CODE | PATTSET | X'01'–X'FD' | Pattern set of the gradient [GOCA-7-430]|
| 7 | CODE | PATTSYM | X'01'–X'FF' | Pattern symbol of the gradient [GOCA-7-431]|
| 8–9 | SBIN | $X_S$ | X'8000'–X'7FFF' | $X_g$ coordinate of the start of the gradient line [GOCA-7-432]|
| 10–11 | SBIN | $Y_S$ | X'8000'–X'7FFF' | $Y_g$ coordinate of the start of the gradient line [GOCA-7-433]|
| 12–13 | SBIN | $X_E$ | X'8000'–X'7FFF' | $X_g$ coordinate of the end of the gradient line [GOCA-7-434]|
| 14–15 | SBIN | $Y_E$ | X'8000'–X'7FFF' | $Y_g$ coordinate of the end of the gradient line [GOCA-7-435]|
| 16–n | COLSPEC_S | | See Semantics | Color specification of the start color (13–15 bytes) [GOCA-7-436]|
| (n+1)–m | COLVALUE_E | | See Semantics | Color value of the end color (2–4 bytes) [GOCA-7-437]|
| m+1 | CODE | OUTSIDE_S | X'00'–X'03' | Value for how to fill areas outside the start side of the gradient:<br> - X'00' None<br> - X'01' Pad<br> - X'02' Repeat<br> - X'03' Reflect<br> - All other values: Reserved [GOCA-7-438]|
| m+2 | CODE | OUTSIDE_E | X'00'–X'03' | Value for how to fill areas outside the end side of the gradient:<br> - X'00' None<br> - X'01' Pad<br> - X'02' Repeat<br> - X'03' Reflect<br> - All other values: Reserved [GOCA-7-439]|
| _Optional:_ [GOCA-7-440]| | | | |
| m+3 | UBIN | OFFSET_1 | 0–10,000 | Offset along the gradient line of the first optional color stop (2 bytes) [GOCA-7-441]|
| | COLVALUE_1 | | See Semantics | Color value of the color of the first color stop (2–4 bytes) [GOCA-7-442]|
| | UBIN | OFFSET_2 | 0–10,000 | Offset along the gradient line of the second optional color stop (2 bytes) [GOCA-7-443]|
| | COLVALUE_2 | | See Semantics | Color value of the color of the second color stop (2–4 bytes) [GOCA-7-444]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-445]|
| | UBIN | OFFSET_F | 0–10,000 | Offset along the gradient line of the final optional color stop (2 bytes) [GOCA-7-446]|
| | COLVALUE_F | | See Semantics | Color value of the color of the final color stop (2–4 bytes) [GOCA-7-447]|

#### Semantics

The Linear Gradient order defines a linear gradient to be used later to fill an area. See “Gradients” for details of gradients. [GOCA-7-448]

The gradient goes from the start point ($X_S, Y_S$) to the end point ($X_E, Y_E$), with the color gradually changing from the start color (COLSPEC_S) to the end color (COLVALUE_E). Areas outside the gradient are filled based on the OUTSIDE_S and OUTSIDE_E parameters. Any number of color stops can be defined along the gradient line from the start point to the end point, which define offsets along the line where a specific color is to be found. [GOCA-7-449]

If the start point and end point are the same point, usage of the gradient will result in no fill, no matter the value of the OUTSIDE_S and OUTSIDE_E parameters and no matter how many color stops have been specified. [GOCA-7-450]

The offset fields in the color stops have values that can range from 0 to 10,000. This value is then divided by 10,000 to produce a number from 0.0 to 1.0, with 0.0 meaning the start point, 1.0 the end point, 0.5 the halfway point, and so on. [GOCA-7-451]

The color stops must be in increasing order of offset; that is, each color stop offset value must be greater than or equal to the previous color stop offset value. If a color stop has an offset value that is smaller than the offset value of any previous color stop, or is otherwise invalid, exception condition EC-DC05 is raised, for which the standard action is to ignore the color stop. [GOCA-7-452]

The color specification of the start color, COLSPEC_S, has the same format as bytes 1–end of the Set Process Color (GSPCOL) drawing order; see “Set Process Color (GSPCOL) Order” for information on how to process the color specification. Included in the color specification is a length field, a color space field, and four fields indicating how many bits are in each color component, as well as a color value field. The color value field specifies the start color and is interpreted using the other fields in the color specification. For all other colors in this order—that is, for the end color and for all color stop colors—only the color value field is specified. These color values are all the same length as the color value contained in COLSPEC_S, and are interpreted in the same way. As an example, if the start color is an RGB color encoded in three bytes, one for each component (R, G, and B), then all other colors in this order will also be three-byte values, one byte for each component. [GOCA-7-453]

For problems with the colors specified in this order, exception conditions EC-0E02, EC-0E03, EC-0E04, and EC-0E05 are reported as described in the Set Process Color order. Note, however, that the standard action for the EC-0E02, EC-0E03, and EC-0E05 exceptions is different for this order than for the Set Process Color drawing order. For all three exception conditions, the standard action is to ignore this Linear Gradient order. [GOCA-7-454]

If the length field in COLSPEC_S (the first byte) is invalid, exception condition EC-DC06 is raised, for which the standard action is to ignore the Linear Gradient order. [GOCA-7-455]

In addition, there are some rules about the colors specified in this order:
- The Standard OCA color space (X'40') cannot be used. [GOCA-7-456]
- If the Highlight color space (X'06') is used, all color values must resolve to Indexed CMR Color Palette tags. [GOCA-7-457]

If the color specifications do not follow these rules, exception condition EC-DC07 is raised, for which the standard action is to ignore the Linear Gradient order. [GOCA-7-458]

---

1 14 GOCA for AFP Reference [GOCA-7-459]
The smooth transition from one color to another requires interpolation calculations to be performed. For consistency between implementations:
• Linear interpolation is done. [GOCA-7-460]
• Interpolation is done in the specified color space. If the specified color space is the Highlight color space: [GOCA-7-461]
- If all colors resolve to Color Palette tags of the same type, interpolation is done in the color space corresponding to that type; for example, if all colors resolve to Color Palette CMYK tags, interpolation is done in the CMYK color space. - A special case of the above is if all colors resolve to Color Palette Named Colorants tags. In this case, if all named colorants required for all the colors in the gradient are available in the device, interpolation is done in the intensity of the named colorants; otherwise, interpolation is done in the CIELAB color space, using the CIELABValue field of the Color Palette Named Colorants tags. - If all colors do not resolve to Color Palette tags of the same type, interpolation in done in the CIELAB color space, using the CIELABValue field of the Color Palette tags. [GOCA-7-462]
If the LENGTH field of this drawing order is not a valid length, given the expected color value sizes, exception
EC-0003 is raised. The valid values for the LENGTH field, where n is the number of color stops, are as follows:
First byte of COLSPEC_S V alid values of the LENGTH field 12 29 + (n * 4) 13 31 + (n * 5) 14 33 + (n * 6)
The PATTSET and PATTSYM values specify the pattern set and pattern symbol where this gradient will reside.
When the current values of the pattern set and pattern symbol attributes specify these PATTSET and
PATTSYM values, respectively , this gradient will be used to do area fill.
Linear gradients (defined with this order), radial gradients (defined with the Radial Gradient order) , and custom patterns (defined with the Begin Custom Pattern order) share the pattern sets X'01'-X'FD'; the patterns using these pattern sets can be any combination of gradients and custom patterns without restriction.
Definition of a gradient using this drawing order does not set the pattern set and pattern symbol attributes to use the gradient. T o use the gradient, the pattern set and pattern symbol attributes must be explicitly set.
All possible valid values of the PATTSET and PATTSYM fields must be supported. There are 253 ∙ 255 = 64,515 possible combinations of PATTSET and PATTSYM. However , it is not required that implementations support the creation of 64,515 gradients. If insufficient storage is available to correctly handle a linear gradient definition, exception condition EC-DC03 is raised, for which the standard action is to ignore the Linear Gradient order .
It is not possible to replace a gradient or custom pattern simply by sending a Linear Gradient order with the same PATTSET and PATTSYM parameters. If this is attempted, exception EC-DC02 is raised, for which the standard action is to ignore the Linear Gradient order . T o replace a pattern at a given pattern set and pattern symbol, first delete the existing pattern using the Delete Pattern drawing order .
The current position is not used nor changed by this drawing order .
Linear Gradient

---

GOCA for AFP Reference 1 15
The following exception condition raises a drawing process check:
EC-0003 The order has an incorrect length.
The following exception conditions cause a standard action to be taken:
EC-0E02 The color space specified in the order is invalid or unsupported.
Standard action: Ignore the Linear Gradient order .
EC-0E03 A color value specified in the order is invalid or unsupported.
Standard action: Ignore the Linear Gradient order .
EC-0E04 A highlight color percent value specified in the order is invalid.
Standard action: The maximum valid percent value is used.
EC-0E05 The number of bits for a color component specified in the order is invalid or unsupported.
Standard action: Ignore the Linear Gradient order .
EC-DC00 The value specified for the PATTSET parameter is invalid.
Standard action: Ignore the Linear Gradient order .
EC-DC01 The value specified for the PATTSYM parameter is invalid; a gradient cannot be assigned to pattern symbol X'00'.
Standard action: Ignore the Linear Gradient order .
EC-DC02 The PATTSET and PATTSYM values are within the valid range, but a pattern already resides at that location.
Standard action: Ignore the Linear Gradient order .
EC-DC03 Insufficien t storage available to process and store a linear gradient.
Standard action: Ignore the Linear Gradient order .
EC-DC04 A value for one or both of the OUTSIDE_S or OUTSIDE_E parameters is invalid.
Standard action: Use the value X'00' - None.
EC-DC05 The value specified for a color stop off set is invalid or is less than a previous color stop off set.
Standard action: The color stop is ignored.
EC-DC06 Invalid length value in the color specification.
Standard action: Ignore the Linear Gradient order .
EC-DC07 Color space or color values in the order are valid, but do not follow the rules for colors in a gradient.
Standard action: Ignore the Linear Gradient order .
Linear Gradient

---

1 16 GOCA for AFP Reference [GOCA-7-463]
### Marker (GMRK, GCMRK) Orders

These orders draw the current marker symbol at one or more positions starting from the given position or from the current position. [GOCA-7-464]

#### GMRK (Given Position) Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'C2' | GMRK | Order code [GOCA-7-465]|
| 1 | UBIN | LENGTH | 4–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-466]|
| 2–3 | SBIN | XPOS0 | X'8000'–X'7FFF' | $X_g$ coordinate of first marker [GOCA-7-467]|
| 4–5 | SBIN | YPOS0 | X'8000'–X'7FFF' | $Y_g$ coordinate of first marker [GOCA-7-468]|
| 6–7 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of second marker [GOCA-7-469]|
| 8–9 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of second marker [GOCA-7-470]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-471]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final marker [GOCA-7-472]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final marker [GOCA-7-473]|

#### GCMRK (Current Position) Syntax

| Offset | Type | Name | Range | Meaning [GOCA-7-474]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'82' | GCMRK | Order code [GOCA-7-475]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-476]|
| 2–3 | SBIN | XPOS1 | X'8000'–X'7FFF' | $X_g$ coordinate of second marker [GOCA-7-477]|
| 4–5 | SBIN | YPOS1 | X'8000'–X'7FFF' | $Y_g$ coordinate of second marker [GOCA-7-478]|
| ⋮ | ⋮ | ⋮ | ⋮ | ⋮ [GOCA-7-479]|
| SBIN | XPOSF | X'8000'–X'7FFF' | $X_g$ coordinate of final marker [GOCA-7-480]|
| SBIN | YPOSF | X'8000'–X'7FFF' | $Y_g$ coordinate of final marker [GOCA-7-481]|

#### Semantics

The Marker at Given Position (GMRK) order draws an initial marker symbol at the point specified by the first coordinate pair, and draws additional marker symbols at all the points specified by the remaining coordinate pairs. The Marker at Current Position (GCMRK) order draws an initial marker symbol at the current position and draws additional marker symbols at all the points specified by the remaining coordinate pairs. [GOCA-7-482]

Markers are positioned in GPS. The specified points define the position of the center of the marker. The current position is set to the last coordinate specified. If no coordinate has been specified, the current position remains unchanged. [GOCA-7-483]

A Marker at Current Position (GCMRK) order with no coordinate values specified—that is, the value of LENGTH is zero—draws a marker at the current position. [GOCA-7-484]

The markers are drawn at a size determined by the marker cell-size attribute. [GOCA-7-485]

The marker set from which the marker symbol is obtained is given by the value of the marker set attribute. If this marker set is not available, EC-C200 is raised, the standard action for which is to use the standard default marker set. In AFP environments, this is the default marker set. [GOCA-7-486]

The particular marker symbol that is drawn is given by the value of the current marker symbol attribute. If the code point is undefined in the marker set identified by the current marker set attribute, EC-C201 is raised, the standard action for which is to use the standard default marker symbol. In AFP environments, this is X'01'—Cross. [GOCA-7-487]

The color of the markers is given by the value of the current marker color. The way in which the markers are merged with any output primitives that have already been drawn is controlled by the values of the marker mix and marker background mix attributes. [GOCA-7-488]

Note: It is not an error if a marker symbol is placed inside the GPS such that part of the marker lies outside the GPS. However, the appearance of such a marker in the GPS is implementation defined. [GOCA-7-489]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-490]

The following exception conditions cause a standard action to be taken:
- **EC-C200**: The marker set identified by the value in the current marker set attribute is not available. [GOCA-7-491]
  - Standard action: The standard default marker set is used. In AFP environments, this is the default marker set. [GOCA-7-492]
- **EC-C201**: The code point in the current marker symbol attribute is not defined in the current marker set. [GOCA-7-493]
  - Standard action: The standard default marker symbol is used. In AFP environments, this is X'01'—Cross. [GOCA-7-494]

---
### No-Operation (GNOP1) Order

This order is a No-Operation. [GOCA-7-495]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'00' | GNOP1 | Order code [GOCA-7-496]|

#### Semantics

The No-Operation order is a null operation. It has no effect on the GPS, or any current attribute or control. [GOCA-7-497]

This order does not raise any exception conditions. [GOCA-7-498]
### Partial Arc (GPARC, GCPARC) Orders

These orders draw a line from the given position or the current position to the start of an arc, and then construct a partial arc. The start point of the arc is specified by the start angle, and the length of the arc is specified by the sweep angle. [GOCA-7-499]

#### Syntax

##### Partial Arc at Given Position (GPARC) Order

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'E3' | GPARC | Order code [GOCA-7-500]|
| 1 | UBIN | LENGTH | 18 | Length of following data [GOCA-7-501]|
| 2-3 | SBIN | XPOS | X'8000'-X'7FFF' | $X_g$ coordinate of line start point [GOCA-7-502]|
| 4-5 | SBIN | YPOS | X'8000'-X'7FFF' | $Y_g$ coordinate of line start point [GOCA-7-503]|
| 6-7 | SBIN | XCENT | X'8000'-X'7FFF' | $X_g$ coordinate of the center of the arc [GOCA-7-504]|
| 8-9 | SBIN | YCENT | X'8000'-X'7FFF' | $Y_g$ coordinate of the center of the arc [GOCA-7-505]|
| 10 | UBIN | MH | X'00'-X'FF' | Integer portion of multiplier [GOCA-7-506]|
| 11 | UBIN | MFR | X'00'-X'FF' | Fractional portion of multiplier [GOCA-7-507]|
| 12-15 | SBIN | START | X'00000000'-X'7FFFFFFF' | Start angle of arc, modulo 360 [GOCA-7-508]|
| 16-19 | SBIN | SWEEP | X'00000000'-X'7FFFFFFF' | Sweep angle of arc, modulo 360 [GOCA-7-509]|

##### Partial Arc at Current Position (GCPARC) Order

| Offset | Type | Name | Range | Meaning [GOCA-7-510]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'A3' | GCPARC | Order code [GOCA-7-511]|
| 1 | UBIN | LENGTH | 14 | Length of following data [GOCA-7-512]|
| 2-3 | SBIN | XCENT | X'8000'-X'7FFF' | $X_g$ coordinate of the center of the arc [GOCA-7-513]|
| 4-5 | SBIN | YCENT | X'8000'-X'7FFF' | $Y_g$ coordinate of the center of the arc [GOCA-7-514]|
| 6 | UBIN | MH | X'00'-X'FF' | Integer portion of multiplier [GOCA-7-515]|
| 7 | UBIN | MFR | X'00'-X'FF' | Fractional portion of multiplier [GOCA-7-516]|
| 8-11 | SBIN | START | X'00000000'-X'7FFFFFFF' | Start angle of arc, modulo 360 [GOCA-7-517]|
| 12-15 | SBIN | SWEEP | X'00000000'-X'7FFFFFFF' | Sweep angle of arc, modulo 360 [GOCA-7-518]|

#### Semantics

The Partial Arc at Given Position (GPARC) order draws a line from point (XPOS, YPOS) to the start of an arc, then draws the arc with its center at point (XCENT, YCENT). The Partial Arc at Current Position (GCPARC) order draws a line from the current position to the start of an arc, then draws the arc with its center at point (XCENT, YCENT). The arc is part of the full arc defined by the current arc parameters and the multiplier specified by MH and MFR. [GOCA-7-519]

The part of the arc that is drawn is defined by the starting angle, START, and the sweep angle, SWEEP. Both angles are defined on the unit circle space and are transformed by an amount defined by the current arc parameters in the same way that the unit circle is transformed. See "Partial Arc".or details. [GOCA-7-520]

A previous Set Arc Parameters drawing order determines the shape and orientation of the arc. If no Set Arc Parameters drawing order has been received, the presentation process draws an arc using the drawing default values of the arc parameters. [GOCA-7-521]

The drawing direction is defined by the determinant of the transform, which is defined by the arc parameters. For details, see page 24. [GOCA-7-522]

MH specifies the integer portion of a scale factor; MFR specifies the fractional portion of the scale factor. A decimal point is implied between MH and MFR. The fractional portion of the scale factor is calculated by dividing MFR by 256. For example, if MFR=X'40', its decimal value is 64, which, divided by 256 results in a fractional component for the scale factor of 1/4. [GOCA-7-523]

For a circle, the radius is $(MH \cdot R + MFR \cdot R)$ where R is the radius of the circle defined by the current arc parameters. [GOCA-7-524]

For an ellipse, the major and minor axes are $(MH \cdot MAJ + MFR \cdot MAJ)$ and $(MH \cdot MIN + MFR \cdot MIN)$, where MAJ and MIN are the major and minor axes of the ellipse defined by the current arc parameters. [GOCA-7-525]

The START and SWEEP parameters are defined as signed 32-bit integers, whose range is restricted to positive values, that is, X'00000000' to X'7FFFFFFF'. The START and SWEEP angles are the numbers, in degrees, that result from dividing the integers by 65,536 ($2^{16}$) and interpreting the result as a modulo 360 number. The effective range of the angles is therefore greater than or equal to 0° and less than 360°. For example, if the sweep angle is specified to be X'00007FFF', its value is $32,767 \div 65,536 \pmod{360} = .5^\circ$. [GOCA-7-526]

Note that since a sweep angle of any integer multiple of 360° results in a 0° arc, this drawing order cannot be used to draw a complete arc. The Full Arc drawing order can be used to draw a complete arc. [GOCA-7-527]

The current values of the line attributes are taken into account when drawing the partial arc. [GOCA-7-528]

The current position is moved to the endpoint of the arc. [GOCA-7-529]

The following exception conditions raise a drawing process check:
- **EC-0003**: The order has incorrect length. [GOCA-7-530]
- **EC-E300**: The partial arc started inside GPS but then finished outside. Therefore, the calculated new current position is outside GPS. [GOCA-7-531]
- **EC-E302**: A negative value is specified for the SWEEP angle. [GOCA-7-532]
- **EC-E303**: A negative value is specified for the START angle. [GOCA-7-533]

The following exception conditions cause a standard action to be taken:
- **EC-000D**: The start and end points of a partial arc are inside GPS, but a portion of the arc is outside GPS. [GOCA-7-534]
  - Standard action: All drawing outside the GPS is suppressed. The portion of the arc that is inside the GPS is drawn. [GOCA-7-535]
- **EC-C601**: The drawing processor has detected an exceptional condition that can prevent the drawing of the arc within the normal limits of pel accuracy. [GOCA-7-536]
  - Standard action: The arc is drawn as accurately as the implementation can define. This action might produce straight lines. [GOCA-7-537]

### Radial Gradient (GRGD) Order

This order defines a radial gradient to be used to fill an area. [GOCA-7-538]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'FE' | Extended | Format order code [GOCA-7-539]|
| 1 | CODE | X'DD' | GRGD | Qualifier code [GOCA-7-540]|
| 2-3 | UBIN | LENGTH | 33-65,535 | Length of following data [GOCA-7-541]|
| 4-5 | RES | X'0000' | Reserved | Only valid value [GOCA-7-542]|
| 6 | CODE | PATTSET | X'01'-X'FD' | Pattern set of the gradient [GOCA-7-543]|
| 7 | CODE | PATTSYM | X'01'-X'FF' | Pattern symbol of the gradient [GOCA-7-544]|
| 8-9 | SBIN | XPOS_S | X'8000'-X'7FFF' | $X_g$ coordinate of the center of the start full arc [GOCA-7-545]|
| 10-11 | SBIN | YPOS_S | X'8000'-X'7FFF' | $Y_g$ coordinate of the center of the start full arc [GOCA-7-546]|
| 12 | UBIN | MH_S | X'00'-X'FF' | Integer portion of the multiplier for the start full arc [GOCA-7-547]|
| 13 | UBIN | MFR_S | X'00'-X'FF' | Fractional portion of the multiplier for the start full arc [GOCA-7-548]|
| 14-15 | SBIN | XPOS_E | X'8000'-X'7FFF' | $X_g$ coordinate of the center of the end full arc [GOCA-7-549]|
| 16-17 | SBIN | YPOS_E | X'8000'-X'7FFF' | $Y_g$ coordinate of the center of the end full arc [GOCA-7-550]|
| 18 | UBIN | MH_E | X'00'-X'FF' | Integer portion of the multiplier for the end full arc [GOCA-7-551]|
| 19 | UBIN | MFR_E | X'00'-X'FF' | Fractional portion of the multiplier for end full arc [GOCA-7-552]|
| 20-n | COLSPEC_S | See Semantics | | Color specification of the start color (13-15 bytes) [GOCA-7-553]|
| (n+1)-m | COLVALUE_E | See Semantics | | Color value of the end color (2-4 bytes) [GOCA-7-554]|
| m+1 | CODE | OUTSIDE_S | X'00'-X'03' | Value for how to fill areas outside the start side of the gradient:<br>X'00' None<br>X'01' Pad<br>X'02' Repeat<br>X'03' Reflect<br>All other values Reserved [GOCA-7-555]|
| m+2 | CODE | OUTSIDE_E | X'00'-X'03' | Value for how to fill areas outside the end side of the gradient:<br>X'00' None<br>X'01' Pad<br>X'02' Repeat<br>X'03' Reflect<br>All other values Reserved [GOCA-7-556]|

The following parameters are optional: [GOCA-7-557]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| m+3 | UBIN | OFFSET_1 | 0-10,000 | Offset of the intermediate full arc of the first optional color stop (2 bytes) [GOCA-7-558]|
| | COLVALUE_1 | See Semantics | | Color value of the color of the first color stop (2-4 bytes) [GOCA-7-559]|
| | UBIN | OFFSET_2 | 0-10,000 | Offset of the intermediate full arc of the second optional color stop (2 bytes) [GOCA-7-560]|
| | COLVALUE_2 | See Semantics | | Color value of the color of the second color stop (2-4 bytes) [GOCA-7-561]|
| ⋮ | ⋮ | ⋮ | ⋮ | Further color stop information [GOCA-7-562]|
| | UBIN | OFFSET_F | 0-10,000 | Offset of the intermediate full arc of the final optional color stop (2 bytes) [GOCA-7-563]|
| | COLVALUE_F | See Semantics | | Color value of the color of the final color stop (2-4 bytes) [GOCA-7-564]|

#### Semantics

The Radial Gradient order defines a radial gradient to be used later to fill an area. See "Gradients" for details of gradients. [GOCA-7-565]

The gradient goes from the start full arc to the end full arc. The color changes gradually from the start color (COLSPEC_S) to the end color (COLVALUE_E). Areas outside the gradient are filled based on the OUTSIDE_S and OUTSIDE_E parameters. Any number of color stops can be defined along the gradient lines from the start full arc to the end full arc, which define intermediate full arcs between the start and end full arcs where a specific color is to be found. [GOCA-7-566]

The start and end full arcs are defined in the same way as in the Full Arc at Given Position (GFARC) drawing order, using the appropriate XPOS, YPOS, MH, and MFR values in this drawing order. See "Full Arc (GFARC, GCFARC) Orders" for more information. Note that since both full arcs use the same arc parameters, the two will have the same shape (as will all intermediate full arcs along the gradient). [GOCA-7-567]

Either multiplier value can be zero (that is, either MH_S=MFR_S=0 or MH_E=MFR_E=0), in which case the gradient starts or ends at a point instead of a full arc. If both multiplier values are zero (MH_S=MFR_S=MH_E=MFR_E=0), however, usage of the gradient will result in no fill, no matter the value of the OUTSIDE_S and OUTSIDE_E parameters and no matter how many color stops have been specified. In addition, if the start and end full arc have the same center and multiplier, usage of the gradient will result in no fill. [GOCA-7-568]

If part or all of either full arc is outside the GPS, this is not an error. This functionality can be used to get radial gradients that completely fill the GPS. Implementations that can maintain a position outside the GPS should produce a gradient as expected—gradually changing from the start color at the start full arc toward the end color at the end full arc, even though some parts of the intermediate full arcs might be outside the GPS. For implementations that cannot maintain a position outside GPS, the results are implementation dependent. [GOCA-7-569]

The offset fields in the color stops have values that can range from 0 to 10,000. This value is then divided by 10,000 to produce a number from 0.0 to 1.0, with 0.0 meaning the start full arc, 1.0 the end full arc, 0.5 the intermediate full arc halfway between the two, and so on. [GOCA-7-570]

The color stops must be in increasing order of offset; that is, each color stop offset value must be greater than or equal to the previous color stop offset value. If a color stop has an offset value that is smaller than the offset value of any previous color stop, or is otherwise invalid, exception condition EC-DD05 is raised, for which the standard action is to ignore the color stop. [GOCA-7-571]

The color specification of the start color, COLSPEC_S, has the same format as bytes 1–end of the Set Process Color (GSPCOL) drawing order; see "Set Process Color (GSPCOL) Order" for information on how to process the color specification. Included in the color specification is a length field, a color space field, and four fields indicating how many bits are in each color component, as well as a color value field. The color value field specifies the start color and is interpreted using the other fields in the color specification. For all other colors in this order—that is, for the end color and for all color stop colors—only the color value field is specified. [GOCA-7-572]

These color values are all the same length as the color value contained in COLSPEC_S, and are interpreted in the same way. As an example, if the start color is an RGB color encoded in three bytes, one for each component (R, G, and B), then all other colors in this order will also be three-byte values, one byte for each component. [GOCA-7-573]

For problems with the colors specified in this order, exception conditions EC-0E02, EC-0E03, EC-0E04, and EC-0E05 are reported as described in the Set Process Color order. Note, however, that the standard action for the EC-0E02, EC-0E03, and EC-0E05 exceptions is different for this order than for the Set Process Color drawing order. For all three exception conditions, the standard action is to ignore this Radial Gradient order. [GOCA-7-574]

If the length field in COLSPEC_S (the first byte) is invalid, exception condition EC-DD06 is raised, for which the standard action is to ignore the Radial Gradient order. [GOCA-7-575]

In addition, there are some rules about the colors specified in this order:
- The Standard OCA color space (X'40') cannot be used. [GOCA-7-576]
- If the Highlight color space (X'06') is used, all color values must resolve to Indexed CMR Color Palette tags. [GOCA-7-577]

If the color specifications do not follow these rules, exception condition EC-DD07 is raised, for which the standard action is to ignore the Radial Gradient order. [GOCA-7-578]

The smooth transition from one color to another requires interpolation calculations to be performed. For consistency between implementations:
- Linear interpolation is done. [GOCA-7-579]
- Interpolation is done in the specified color space. If the specified color space is the Highlight color space: [GOCA-7-580]
  - If all colors resolve to Color Palette tags of the same type, interpolation is done in the color space corresponding to that type; for example, if all colors resolve to Color Palette CMYK tags, interpolation is done in the CMYK color space. [GOCA-7-581]
  - A special case of the above is if all colors resolve to Color Palette Named Colorants tags. In this case, if all named colorants required for all the colors in the gradient are available in the device, interpolation is done in the intensity of the named colorants; otherwise, interpolation is done in the CIELAB color space, using the CIELABValue field of the Color Palette Named Colorants tags. [GOCA-7-582]
  - If all colors do not resolve to Color Palette tags of the same type, interpolation in done in the CIELAB color space, using the CIELABValue field of the Color Palette tags. [GOCA-7-583]

If the LENGTH field of this drawing order is not a valid length, given the expected color value sizes, exception EC-0003 is raised. The valid values for the LENGTH field, where $n$ is the number of color stops, are as follows: [GOCA-7-584]

| First byte of COLSPEC_S | Valid values of the LENGTH field |
| :--- | :--- |
| 12 | 33 + ($n \cdot 4$) [GOCA-7-585]|
| 13 | 35 + ($n \cdot 5$) [GOCA-7-586]|
| 14 | 37 + ($n \cdot 6$) [GOCA-7-587]|

The PATTSET and PATTSYM values specify the pattern set and pattern symbol where this gradient will reside. When the current values of the pattern set and pattern symbol attributes specify these PATTSET and PATTSYM values, respectively, this gradient will be used to do area fill. [GOCA-7-588]

Linear gradients (defined with the Linear Gradient order), radial gradients (defined with this order), and custom patterns (defined with the Begin Custom Pattern order) share the pattern sets X'01'–X'FD'; the patterns using these pattern sets can be any combination of gradients and custom patterns without restriction. [GOCA-7-589]

Definition of a gradient using this drawing order does not set the pattern set and pattern symbol attributes to use the gradient. To use the gradient, the pattern set and pattern symbol attributes must be explicitly set. [GOCA-7-590]

All possible valid values of the PATTSET and PATTSYM fields must be supported. There are $253 \cdot 255 = 64,515$ possible combinations of PATTSET and PATTSYM. However, it is not required that implementations support the creation of 64,515 gradients. If insufficient storage is available to correctly handle a radial gradient definition, exception condition EC-DD03 is raised, for which the standard action is to ignore the Radial Gradient order. [GOCA-7-591]

It is not possible to replace a gradient or custom pattern simply by sending a Radial Gradient order with the same PATTSET and PATTSYM parameters. If this is attempted, exception EC-DD02 is raised, for which the standard action is to ignore the Radial Gradient order. To replace a pattern at a given pattern set and pattern symbol, first delete the existing pattern using the Delete Pattern drawing order. [GOCA-7-592]

The current position is not used nor changed by this drawing order. [GOCA-7-593]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-594]

The following exception conditions cause a standard action to be taken:
- **EC-0E02**: The color space specified in the order is invalid or unsupported. [GOCA-7-595]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-596]
- **EC-0E03**: A color value specified in the order is invalid or unsupported. [GOCA-7-597]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-598]
- **EC-0E04**: A highlight color percent value specified in the order is invalid. [GOCA-7-599]
  - Standard action: The maximum valid percent value is used. [GOCA-7-600]
- **EC-0E05**: The number of bits for a color component specified in the order is invalid or unsupported. [GOCA-7-601]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-602]
- **EC-C601**: The drawing processor has detected an exceptional condition that can prevent the drawing of one of the full arcs within the normal limits of pel accuracy. [GOCA-7-603]
  - Standard action: The arc is drawn as accurately as the implementation can define. This action might produce straight lines. [GOCA-7-604]
- **EC-DD00**: The value specified for the PATTSET parameter is invalid. [GOCA-7-605]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-606]
- **EC-DD01**: The value specified for the PATTSYM parameter is invalid; a gradient cannot be assigned to pattern symbol X'00'. [GOCA-7-607]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-608]
- **EC-DD02**: The PATTSET and PATTSYM values are within the valid range, but a pattern already resides at that location. [GOCA-7-609]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-610]
- **EC-DD03**: Insufficient storage available to process and store a radial gradient. [GOCA-7-611]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-612]
- **EC-DD04**: A value for one or both of the OUTSIDE_S or OUTSIDE_E parameters is invalid. [GOCA-7-613]
  - Standard action: Use the value X'00' - None. [GOCA-7-614]
- **EC-DD05**: A color stop has an offset value that is smaller than the offset value of any previous color stop, or is otherwise invalid. [GOCA-7-615]
  - Standard action: Ignore the color stop. [GOCA-7-616]
- **EC-DD06**: The length field in COLSPEC_S (the first byte) is invalid. [GOCA-7-617]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-618]
- **EC-DD07**: Color specifications do not follow the specific rules for the Highlight color space or Standard OCA color space. [GOCA-7-619]
  - Standard action: Ignore the Radial Gradient order. [GOCA-7-620]

---
### Relative Line (GRLINE, GCRLINE) Orders

These orders define one or more connected straight lines, at the given position or at the current position. For these drawing orders, the endpoint of each line is specified as an offset from the previous endpoint rather than as an absolute value. [GOCA-7-621]

#### Syntax

##### Relative Line at Given Position (GRLINE) Order

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'E1' | GRLINE | Order code [GOCA-7-622]|
| 1 | UBIN | LENGTH | 4–n | Length of following data; $n$ must be less than 255 and a multiple of 2 [GOCA-7-623]|
| 2-3 | SBIN | XPOS0 | X'8000'-X'7FFF' | $X_g$ coordinate of line start point [GOCA-7-624]|
| 4-5 | SBIN | YPOS0 | X'8000'-X'7FFF' | $Y_g$ coordinate of line start point [GOCA-7-625]|
| 6 | SBIN | XOFFS0 | X'80'-X'7F' | Offset in $X_g$ direction for first endpoint [GOCA-7-626]|
| 7 | SBIN | YOFFS0 | X'80'-X'7F' | Offset in $Y_g$ direction for first endpoint [GOCA-7-627]|
| 8 | SBIN | XOFFS1 | X'80'-X'7F' | Offset in $X_g$ direction for second endpoint [GOCA-7-628]|
| 9 | SBIN | YOFFS1 | X'80'-X'7F' | Offset in $Y_g$ direction for second endpoint [GOCA-7-629]|
| ⋮ | ⋮ | ⋮ | ⋮ | Offset data for further points [GOCA-7-630]|
| 2n+4 | SBIN | XOFFSF | X'80'-X'7F' | Offset in $X_g$ direction for final endpoint [GOCA-7-631]|
| 2n+5 | SBIN | YOFFSF | X'80'-X'7F' | Offset in $Y_g$ direction for final endpoint [GOCA-7-632]|

##### Relative Line at Current Position (GCRLINE) Order

| Offset | Type | Name | Range | Meaning [GOCA-7-633]|
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'A1' | GCRLINE | Order code [GOCA-7-634]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; $n$ must be less than 255 and a multiple of 2 [GOCA-7-635]|
| 2 | SBIN | XOFFS0 | X'80'-X'7F' | Offset in $X_g$ direction for first endpoint [GOCA-7-636]|
| 3 | SBIN | YOFFS0 | X'80'-X'7F' | Offset in $Y_g$ direction for first endpoint [GOCA-7-637]|
| 4 | SBIN | XOFFS1 | X'80'-X'7F' | Offset in $X_g$ direction for second endpoint [GOCA-7-638]|
| 5 | SBIN | YOFFS1 | X'80'-X'7F' | Offset in $Y_g$ direction for second endpoint [GOCA-7-639]|
| ⋮ | ⋮ | ⋮ | ⋮ | Offset data for further points [GOCA-7-640]|
| 2n | SBIN | XOFFSF | X'80'-X'7F' | Offset in $X_g$ direction for final endpoint [GOCA-7-641]|
| 2n+1 | SBIN | YOFFSF | X'80'-X'7F' | Offset in $Y_g$ direction for final endpoint [GOCA-7-642]|

#### Semantics

The Relative Line at Given Position (GRLINE) order adds the offset of each line endpoint cumulatively to the line start point (specified by XPOS0, YPOS0) to generate a sequence of points $P_1, P_2, \dots, P_f$, where $P_f$ is the final endpoint specified. The Relative Line at Current Position (GCRLINE) order adds the offset of each line endpoint cumulatively to the current position to generate a sequence of points $P_1, P_2, \dots, P_f$, where $P_f$ is the final endpoint specified. [GOCA-7-643]

Straight lines are drawn connecting the points in sequence, that is, from the starting point to $P_1$, from $P_1$ to $P_2, \dots$, and from $P_{f-1}$ to $P_f$. [GOCA-7-644]

Any number of offsets can be included within the limits of the length specifications. [GOCA-7-645]

A Relative Line at Given Position (GRLINE) order with only an initial position is permitted. This serves only to move the current position, which is set to the specified point. A Relative Line at Current Position (GCRLINE) order with only an initial position (the current position) is permitted and is treated as a No-Op. [GOCA-7-646]

A relative line that starts inside GPS, but has values of offset specified that accumulate to be outside GPS, is an error. [GOCA-7-647]

The current values of the line attributes are taken into account when drawing the relative lines. [GOCA-7-648]

#### Exceptions

The following exception conditions raise a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-649]
- **EC-E100**: A relative line starts inside GPS, but goes outside. [GOCA-7-650]

### Segment Characteristics (GSGCH) Order

This order is processed as a No-Op in AFP GOCA. It is valid only in the prolog of a segment. [GOCA-7-651]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'04' | GSGCH | Order code [GOCA-7-652]|
| 1 | UBIN | LENGTH | 0–255 | Length of following data [GOCA-7-653]|
| 2 | CODE | CHID | X'00' | Identification code for characteristics [GOCA-7-654]|
| 3–n | UNDF | PARMS | | Parameters of characteristics [GOCA-7-655]|

#### Semantics

Not applicable in AFP GOCA. [GOCA-7-656]

The following exception condition may optionally raise a drawing process check:
- **EC-0400**: The Segment Characteristics order is detected outside the prolog section of a segment. [GOCA-7-657]

### Set Arc Parameters (GSAP) Order

This order sets the values of the current arc parameters. [GOCA-7-658]

#### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'22' | GSAP | Order code [GOCA-7-659]|
| 1 | UBIN | LENGTH | 8 | Length of following data [GOCA-7-660]|
| 2-3 | SBIN | P | X'8000'-X'7FFF' | P parameter of arc transform [GOCA-7-661]|
| 4-5 | SBIN | Q | X'8000'-X'7FFF' | Q parameter of arc transform [GOCA-7-662]|
| 6-7 | SBIN | R | X'8000'-X'7FFF' | R parameter of arc transform [GOCA-7-663]|
| 8-9 | SBIN | S | X'8000'-X'7FFF' | S parameter of arc transform [GOCA-7-664]|

#### Semantics

The Set Arc Parameters order specifies the shape and orientation of a circle or an ellipse. Subsequent Full Arc orders specify the size and location of the circle or ellipse. Subsequent Partial Arc orders specify the size and location of the circle or ellipse that the partial arc is part of. For details, see "Full Arc" and "Partial Arc". [GOCA-7-665]

The parameters P, Q, R, and S define a transformation that maps the unit circle at the GPS origin ($X_g=0, Y_g=0$) to the required circle or ellipse, also at the GPS origin, such that:
$$X' = P \cdot X + R \cdot Y$$
$$Y' = S \cdot X + Q \cdot Y$$
where $X$ and $Y$ are the coordinates of points on the unit circle, and $X'$ and $Y'$ are the coordinates of points on the arc. The drawing direction is defined by the determinant of the transform, which is defined by the arc parameters. For details, see page 24. [GOCA-7-666]

For a circle of radius $r$ the parameters are:
- $P=r$
- $Q=r$
- $R=0$
- $S=0$

For an ellipse with major axis $2a$ and minor axis $2b$ the parameters are:
- $P=a$
- $Q=b$
- $R=0$
- $S=0$

For the same ellipse, but rotated $A$ degrees counterclockwise with respect to the $X_g$ axis the parameters are:
- $P = a \cdot \cos(A)$ [GOCA-7-667]
- $Q = b \cdot \cos(A)$ [GOCA-7-668]
- $R = -b \cdot \sin(A)$ [GOCA-7-669]
- $S = a \cdot \sin(A)$ [GOCA-7-670]

This drawing order does not change the current position. [GOCA-7-671]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-672]

### Set Background Mix (GSBMX) Order

This order provides a shorthand way of setting the following background mix attributes to the same value:
* Character background mix [GOCA-7-673]
* Image background mix [GOCA-7-674]
* Marker background mix [GOCA-7-675]
* Pattern background mix [GOCA-7-676]

#### GSBMX Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'0D' | GSBMX | Order code [GOCA-7-677]|
| 1 | CODE | MODE | X'00'–X'05' | Mix-mode value:<br>X'00' Drawing default<br>X'01'–X'04' Not supported in AFP GOCA<br>X'05' Leave Alone<br>All other values are reserved [GOCA-7-678]|

#### GSBMX Semantics

The Set Background Mix order sets the current value of all four background mix attributes to the value specified in the order. The standard default in AFP environments is X'05'—Leave Alone. [GOCA-7-679]

Background mix attributes control the way in which the color of the background of a primitive is combined with the color of the GPS. With MODE set to X'05', the background pels are transparent and do not affect the color of underlying pels in the GPS. Since this is the only background mix mode supported in AFP GOCA, selecting the drawing default (MODE X'00') will also default to MODE X'05'. [GOCA-7-680]

For a description of the meaning of the various mix modes, see “Mix”. [GOCA-7-681]

The following exception conditions cause a standard action to be taken: [GOCA-7-682]

**EC-0004** The attribute value specified in the order is not valid.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'05'—Leave Alone. [GOCA-7-683]

**EC-000E** The attribute value specified in the order is not supported.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'05'—Leave Alone. [GOCA-7-684]

---

### Set Character Angle (GSCA) Order

This order sets the value of the current character angle attribute. [GOCA-7-685]

#### GSCA Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'34' | GSCA | Order code [GOCA-7-686]|
| 1 | UBIN | LENGTH | 4 | Length of following data [GOCA-7-687]|
| 2–3 | SBIN | XPOS | X'8000'–X'7FFF' | $X_{g}$ coordinate of point [GOCA-7-688]|
| 4–5 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_{g}$ coordinate of point [GOCA-7-689]|

#### GSCA Semantics

The Set Character Angle order sets the value of the current character angle attribute to the value specified in the order. [GOCA-7-690]

The character angle attribute controls the angle of the character baseline with respect to the GPS $X_{g}$ axis for subsequent character strings. This angle is specified using the values XPOS and YPOS, where the character baseline is a line parallel to the line that passes through the points ($X_{g}=0, Y_{g}=0$) and ($X_{g}=$ XPOS, $Y_{g}=$ YPOS). The angle of the baseline relative to the $X_{g}$ axis of GPS is then the angle whose tangent is YPOS/XPOS. The values of YPOS and XPOS are not required to be the sine and cosine of the angle. [GOCA-7-691]

* If YPOS is zero, and XPOS is positive, the character angle is 0°. [GOCA-7-692]
* If XPOS is zero, and YPOS is positive, the character angle is 90°. [GOCA-7-693]
* If YPOS is zero, and XPOS is negative, the character angle is 180°. [GOCA-7-694]
* If XPOS is zero, and YPOS is negative, the character angle is 270°. [GOCA-7-695]

In AFP GOCA, the only supported values for character angle are 0°, 90°, 180°, and 270°. If XPOS is zero and YPOS is zero, the character angle is set to the drawing default. The standard default in AFP environments is 0°. [GOCA-7-696]

The application of this attribute is dependent on the value of the character precision attribute; see “Character Strings” for details. This drawing order does not change the attributes of any other drawing order. [GOCA-7-697]

The following exception condition raises a drawing process check: [GOCA-7-698]

**EC-0003** The order has an incorrect length. [GOCA-7-699]

The following exception condition causes a standard action to be taken: [GOCA-7-700]

**EC-3400** The specific character angle requested is not supported.
Standard action: The closest character angle supported is used. [GOCA-7-701]

---

### Set Character Cell (GSCC) Order

This order sets the value of the current character cell-size attribute. [GOCA-7-702]

#### GSCC Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'33' | GSCC | Order code [GOCA-7-703]|
| 1 | UBIN | LENGTH | 4, 8 | Length of following data [GOCA-7-704]|
| 2–3 | SBIN | CELLWI | X'8000'–X'7FFF' | Width of character cell, integer part [GOCA-7-705]|
| 4–5 | SBIN | CELLHI | X'8000'–X'7FFF' | Height of character cell, integer part [GOCA-7-706]|
| 6–7 | UBIN | CELLWFR | X'0000'–X'FFFF' | Width of character cell, fractional part (optional) [GOCA-7-707]|
| 8–9 | UBIN | CELLHFR | X'0000'–X'FFFF' | Height of character cell, fractional part (optional) [GOCA-7-708]|

#### GSCC Semantics

The Set Character Cell order sets the value of the current character cell-size attribute to the value specified in the order. Depending on the device implementation of the specified precision, this attribute is used to scale characters specified in subsequent Character String drawing orders. Devices that use the font positioning method ignore the character cell. [GOCA-7-709]

The application of this attribute is dependent on the value of the character precision attribute. See “Character Strings” for details. [GOCA-7-710]

Two formats exist for this drawing order:
* **Four-byte format** [GOCA-7-711]
  - CELLWI specifies the width of the character cell in GPS units. [GOCA-7-712]
  - CELLHI specifies the height of the character cell in GPS units. [GOCA-7-713]
* **Eight-byte format** [GOCA-7-714]
  In this format, both integer and fractional values are specified for the character cell width and height. The width and width-fraction fields form a 4-byte signed value, and the height and height-fraction fields form a 4-byte signed value. A decimal point is implied between the integer part and the fractional part of each parameter.
  - CELLWI specifies the width of the character cell in GPS units. [GOCA-7-715]
  - CELLWFR specifies the fractional part of the width of the character cell in GPS units. [GOCA-7-716]
  - CELLHI specifies the height of the character cell in GPS units. [GOCA-7-717]
  - CELLHFR specifies the fractional part of the height of the character cell in GPS units. [GOCA-7-718]

The fractional parts do not exist in the drawing defaults and are set to zero when the drawing default is set into the current attribute. [GOCA-7-719]

For device implementations that do not ignore the character cell, when the width or height in the current character cell-size attribute is 0, Character String drawing orders will draw nothing. [GOCA-7-720]

This drawing order does not change the current position. Note that, for precisions 1 and 2 for some implementations, if the character cell size is specified as negative values, a mirror image of the character is generated. That is, if the cell width is negative, the character is mirrored about the $Y_{g}$ axis, and if the cell height is negative, the character is mirrored about the $X_{g}$ axis. Refer to “Character Strings” for a description of how the character cell is used on various AFP devices. [GOCA-7-721]

The following exception condition raises a drawing process check: [GOCA-7-722]

**EC-0003** The order has an incorrect length. [GOCA-7-723]

---

### Set Character Direction (GSCD) Order

This order sets the value of the current character direction attribute. [GOCA-7-724]

#### GSCD Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'3A' | GSCD | Order code [GOCA-7-725]|
| 1 | CODE | DIRECTION | X'00'–X'04' | Value for character direction:<br>X'00' Drawing default<br>X'01' Left to right<br>X'02' Top to bottom<br>X'03' Right to left<br>X'04' Bottom to top<br>All other values are reserved [GOCA-7-726]|

#### GSCD Semantics

The Set Character Direction order sets the value of the current character direction attribute to the value specified in the order. The character direction attribute controls the placement of the first character in the string and each succeeding character relative to the previous character. [GOCA-7-727]

**Value Description** [GOCA-7-728]

* **X'00' Drawing Default.** The standard default in AFP environments is left to right (X'01'). [GOCA-7-729]
* **X'01' Left to right.** Characters are positioned so that, at a 0° character angle, the character reference point, which is point R in Figure 29, is coincident with the current position. A vector is then drawn from the left edge of the character box to the right edge, and successive characters are placed in the direction of this vector. [GOCA-7-730]
* **X'02' Top to bottom.** Characters are positioned so that, at a 0° character angle, the character reference point, which is point T in Figure 29, is coincident with the current position. A vector is then drawn from the top edge of the character box to the bottom edge, and successive characters are placed in the direction of this vector. [GOCA-7-731]
* **X'03' Right to left.** Characters are positioned so that, at a 0° character angle, the character reference point, which is point E in Figure 29, is coincident with the current position. A vector is then drawn from the right edge of the character box to the left edge, and successive characters are placed in the direction of this vector. [GOCA-7-732]
* **X'04' Bottom to top.** Characters are positioned so that, at a 0° character angle, the character reference point, which is point B in Figure 29, is coincident with the current position. A vector is then drawn from the bottom edge of the character box to the top edge, and successive characters are placed in the direction of this vector. [GOCA-7-733]

**Architecture Note:** This graphics drawing order defines a function that is analogous to part of the text orientation function in presentation text, which defines an inline direction and the development of characters along this direction. [GOCA-7-734]

The following exception conditions cause a standard action to be taken: [GOCA-7-735]

**EC-0004** The attribute value specified in the order is not valid.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'01'—Left to right. [GOCA-7-736]

**EC-000E** The attribute value specified in the order is not supported.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'01'—Left to right. [GOCA-7-737]

---

### Set Character Precision (GSCR) Order

This order sets the value of the current character precision attribute. [GOCA-7-738]

#### GSCR Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'39' | GSCR | Order code [GOCA-7-739]|
| 1 | CODE | PREC | X'00'–X'03' | Value for character precision attribute:<br>X'00' Drawing default<br>X'01' String precision<br>X'02' Character precision<br>X'03' Stroke precision (not supported in AFP GOCA)<br>All other values are reserved [GOCA-7-740]|

#### GSCR Semantics

The Set Character Precision order sets the value of the current character precision attribute to the value specified in the order. The character precision attribute controls the type of character that is to be used for drawing character strings. Refer to “Character Strings” for a description of how character precision is defined. [GOCA-7-741]

**Value Description** [GOCA-7-742]

* **X'00' Drawing Default.** This value sets the current character precision attribute to the value of the drawing default. The standard default in AFP environments is precision X'02'. [GOCA-7-743]
* **X'01' Precision 1—Device-Specific (String) Precision.** This precision has been implemented differently on different devices; it is not consistent among implementations. The characters are drawn using the quickest, simplest mode possible for the implementation. In this mode, the only attributes that must be considered when the characters are drawn are the character code point, character set, and character direction attributes. The character angle and character cell-size attributes are not guaranteed to have any effect on the appearance of characters in the string. [GOCA-7-744]
* **X'02' Precision 2—Device-Specific (Character) Precision.** This precision has been implemented differently on different devices; it is not consistent among implementations. The character string is drawn taking into account all the attributes to determine the positioning of the characters. The character attributes are not guaranteed to affect the appearance of the characters in the string. [GOCA-7-745]
* **X'03' Precision 3—Stroke Precision.** This value is not supported in AFP GOCA. If it is specified, exception EC-000E exists. [GOCA-7-746]

The following exception conditions cause a standard action to be taken: [GOCA-7-747]

**EC-0004** The attribute value specified in the order is not valid.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'02'—Device-Specific (Character) Precision. [GOCA-7-748]

**EC-000E** The attribute value specified in the order is not supported.
Standard action: The standard default value of the attribute is used. In AFP environments, this is X'02'—Device-Specific (Character) Precision. [GOCA-7-749]

---

### Set Character Set (GSCS) Order

This order sets the value of the current character set attribute. [GOCA-7-750]

#### GSCS Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'38' | GSCS | Order code [GOCA-7-751]|
| 1 | CODE | LCID | X'00'–X'FF' | Local identifier (LCID) for the character set:<br>X'00' Drawing default<br>X'01'–X'FE' Local identifier for the character set<br>X'FF' Special character set [GOCA-7-752]|

#### GSCS Semantics

The Set Character Set order sets the value of the current character set attribute to the value specified in the order. [GOCA-7-753]

When the current character set attribute is X'00', it identifies the drawing default, that is, the default from the GDD, or if not specified, the standard default character set. In AFP environments, this is the presentation device default font. [GOCA-7-754]

When the current character set attribute is X'01' to X'FE', it identifies the character set that is to be used to draw characters in subsequent Character String orders. The controlling environment maps the LCID to a specific font. [GOCA-7-755]

When the current character set attribute is X'FF', it identifies the special character set, which is implementation-defined. [GOCA-7-756]

**Architecture Note:** In AFP environments, the special character set is the presentation device default font. [GOCA-7-757]

Character definitions from the character set identified by the current character set attribute are modified under control of the current character precision attribute before being drawn. [GOCA-7-758]

**Architecture Note:** In MO:DCA and IPDS environments, the MO:DCA character rotation (IPDS font inline sequence) associated with the font is ignored when determining character direction and angle. However, when the font positioning method is used, the selected character direction should match the selected character rotation (font inline sequence) so that appropriate font metrics, such as character increment and A-space, are available. If a font with the required character rotation is not available to the presentation device, the spacing and positioning of characters is unpredictable. [GOCA-7-759]

The following exception condition causes a standard action to be taken: [GOCA-7-760]

**EC-C300** The font identified by the value in the current character set attribute is not available.
Standard action: The standard default character set is used. In AFP environments, this is the presentation device default font. [GOCA-7-761]

---

### Set Character Shear (GSCH) Order

This order sets the value of the current character shear attribute. This order is processed as a No-Op in AFP GOCA. [GOCA-7-762]

#### GSCH Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'35' | GSCH | Order code [GOCA-7-763]|
| 1 | UBIN | LENGTH | 4 | Length of following data [GOCA-7-764]|
| 2–3 | SBIN | HX | X'8000'–X'7FFF' | Dividend of shear ratio [GOCA-7-765]|
| 4–5 | SBIN | HY | X'8000'–X'7FFF' | Divisor of shear ratio [GOCA-7-766]|

#### GSCH Semantics

Not applicable in AFP GOCA. [GOCA-7-767]

The following exception condition raises a drawing process check: [GOCA-7-768]

**EC-0003** The order has an incorrect length. [GOCA-7-769]

---

### Set Color (GSCOL) Order

This order provides a shorthand way of setting the following foreground color attributes to the same value:
* Character color [GOCA-7-770]
* Image color [GOCA-7-771]
* Line color [GOCA-7-772]
* Marker color [GOCA-7-773]
* Pattern color [GOCA-7-774]

**Architecture Note:** To fill an area with the color specified by this drawing order, select the drawing default with the Set Pattern Set order, and either the drawing default or solid fill with the Set Pattern Symbol order. [GOCA-7-775]

#### GSCOL Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'0A' | GSCOL | Order code [GOCA-7-776]|
| 1 | CODE | COL | X'00'–X'08' | Value for color attribute:<br>X'00' Drawing default<br>X'01' Blue<br>X'02' Red<br>X'03' Magenta (Pink)<br>X'04' Green<br>X'05' Cyan (Turquoise)<br>X'06' Yellow<br>X'07' Device default<br>X'08' Color of medium<br>All other values are reserved [GOCA-7-777]|

#### GSCOL Semantics

The Set Color order sets the current value of all five color attributes to the value specified in the order. Color attributes control the color of the foreground of the output primitives as they are drawn. [GOCA-7-778]

The standard default in AFP environments is the presentation device default color. [GOCA-7-779]

The color value specified by this order is prefixed with X'FF' to generate a two-byte color index value into the standard color table. See “Color”. [GOCA-7-780]

The following exception conditions cause a standard action to be taken: [GOCA-7-781]

**EC-0004** The attribute value specified in the order is not valid.
Standard action: The action is implementation dependent. [GOCA-7-782]

**EC-000E** The attribute value specified in the order is not supported.
Standard action: The action is implementation dependent. [GOCA-7-783]

**Architecture Note:** If colors are simulated in AFP environments, color exceptions need not be generated. [GOCA-7-784]

---

### Set Current Position (GSCP) Order

This order sets the value of the current position in the Graphics Presentation Space (GPS). [GOCA-7-785]

#### GSCP Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'21' | GSCP | Order code [GOCA-7-786]|
| 1 | UBIN | LENGTH | 4 | Length of following data [GOCA-7-787]|
| 2–3 | SBIN | XPOS | X'8000'–X'7FFF' | $X_{g}$ coordinate of point [GOCA-7-788]|
| 4–5 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_{g}$ coordinate of point [GOCA-7-789]|

#### GSCP Semantics

The Set Current Position order sets the value of current position to the value specified in the order. [GOCA-7-790]

The following exception condition raises a drawing process check: [GOCA-7-791]

**EC-0003** The order has an incorrect length. [GOCA-7-792]

---

### Set Custom Line Type (GSCLT) Order

This order sets the value of the current line type attribute to a custom value. [GOCA-7-793]

#### GSCLT Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'20' | GSCLT | Order code [GOCA-7-794]|
| 1 | UBIN | LENGTH | 0–n | Length of following data; n must be less than 255 and a multiple of 4 [GOCA-7-795]|
| 2 | UBIN | DASH0H | X'00'–X'FF' | Integer portion of length of first dash/dot [GOCA-7-796]|
| 3 | UBIN | DASH0FR | X'00'–X'FF' | Fractional portion of length of first dash/dot [GOCA-7-797]|
| 4 | UBIN | MOVE0H | X'00'–X'FF' | Integer portion of length of first move [GOCA-7-798]|
| 5 | UBIN | MOVE0FR | X'00'–X'FF' | Fractional portion of length of first move [GOCA-7-799]|
| 6 | UBIN | DASH1H | X'00'–X'FF' | Integer portion of length of second dash/dot [GOCA-7-800]|
| 7 | UBIN | DASH1FR | X'00'–X'FF' | Fractional portion of length of second dash/dot [GOCA-7-801]|
| 8 | UBIN | MOVE1H | X'00'–X'FF' | Integer portion of length of second move [GOCA-7-802]|
| 9 | UBIN | MOVE1FR | X'00'–X'FF' | Fractional portion of length of second move [GOCA-7-803]|
| ... | ... | ... | ... | ... [GOCA-7-804]|
| n–2 | UBIN | DASHFH | X'00'–X'FF' | Integer portion of length of final dash/dot [GOCA-7-805]|
| n–1 | UBIN | DASHFFR | X'00'–X'FF' | Fractional portion of length of final dash/dot [GOCA-7-806]|
| n | UBIN | MOVEFH | X'00'–X'FF' | Integer portion of length of final move [GOCA-7-807]|
| n+1 | UBIN | MOVEFFR | X'00'–X'FF' | Fractional portion of length of final move [GOCA-7-808]|

#### GSCLT Semantics

The Set Custom Line Type order sets the value of the current line type attribute to the custom value specified in the order. The current line type attribute controls the type of line used to draw line primitives. [GOCA-7-809]

When setting the line type attribute, this drawing order will set it to a custom value. The Set Line Type drawing order will set the attribute to a standard value. [GOCA-7-810]

The first byte of the length of a dash/dot or move—this will be referred to as the H byte—specifies the integer portion of the length; the second byte—referred to as the FR byte—specifies the fractional portion. A combined value of X'0000' for a dash/dot length indicates a dash of length 0—that is, a dot. A decimal point is implied between H and FR. The fractional portion of the length is calculated by dividing FR by 256. For example, if FR=X'40', its decimal value is 64, which, divided by 256 results in a fractional component for the length of 1/4. [GOCA-7-811]

All dash and move lengths are expressed in units of line width. [GOCA-7-812]

If no dash and move lengths are provided (that is, if the LENGTH field is zero), or if all dash and move lengths are specified as zero, a solid line is drawn. [GOCA-7-813]

If a dash length is not zero, but on a given device is less than one presentation device pel, the length of the dash is set to one pel. Similarly, if a move length is not zero, but is less than one presentation device pel, the move length is set to one pel. In other words, a nonzero length, no matter how small, must not become a zero length. [GOCA-7-814]

The standard default for the line type attribute in AFP environments is the standard line type value X'07'—Solid line. [GOCA-7-815]

See “Line Type” for more information on the line type attribute, including a discussion of how the dash/dot and move lengths are used to generate lines, and a discussion of standard and custom line type values. [GOCA-7-816]

The following exception condition raises a drawing process check: [GOCA-7-817]

**EC-0003** The order has an incorrect length. [GOCA-7-818]

---

Set Extended Color (GSECOL) Order
This order provides a shorthand way of setting the following foreground color attributes to the same value:
• Character color [GOCA-7-819]
• Image color [GOCA-7-820]
• Line color [GOCA-7-821]
• Marker color [GOCA-7-822]
• Pattern color [GOCA-7-823]

Architecture Note: To fill an area with the color specified by this drawing order, select the drawing default with the Set Pattern Set order, and either the drawing default or solid fill with the Set Pattern Symbol order. [GOCA-7-824]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'26' | GSECOL | Order code [GOCA-7-825]|
| 1 | UBIN | LENGTH | 2 | Length of following data [GOCA-7-826]|
| 2–3 | CODE | COLOR | See Table 5 | Value for color attribute [GOCA-7-827]|

#### Semantics
The Set Extended Color order sets the current value of all five color attributes to the value specified in the order. Color attributes control the color of the foreground bits of the output primitives as they are drawn. [GOCA-7-828]

The color value specified by this order is used as a two-byte color index value into the standard color table; see "Color" for the meaning of the two-byte values. The color values supported by this order are the same as the values defined in the standard color table, and they are also the same as the values defined in the Standard OCA Color Value Table defined in the MO:DCA controlling environment; see the Mixed Object Document Content Architecture (MO:DCA) Reference. [GOCA-7-829]

The standard default in AFP environments is the presentation device default color. [GOCA-7-830]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-831]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-832]
  - **Standard action**: The action is implementation dependent. [GOCA-7-833]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-834]
  - **Standard action**: The action is implementation dependent. [GOCA-7-835]

Architecture Note: If colors are simulated in AFP environments, color exceptions need not be generated. [GOCA-7-836]

---

Set Fractional Line Width (GSFLW) Order
This order sets the value of the current line width attribute. [GOCA-7-837]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'11' | GSFLW | Order code [GOCA-7-838]|
| 1 | UBIN | LENGTH | 2 | Length of following data [GOCA-7-839]|
| 2 | UBIN | MH | X'00'–X'FF' | Integral multiplier of normal line width [GOCA-7-840]|
| 3 | UBIN | MFR | X'00'–X'FF' | Fractional multiplier of normal line width [GOCA-7-841]|

#### Semantics
The Set Fractional Line Width order sets the value of the current line width attribute to the value specified in the order. The current line width attribute controls the width of line used to draw line primitives. [GOCA-7-842]

MH specifies the integer portion of the normal line width multiplier; MFR specifies the fractional portion of the normal line width multiplier. A combined value of X'0000' specifies the drawing default. A combined value of X'0100' represents a unity multiplier, that is, normal line width. A decimal point is implied between MH and MFR. The fractional portion of the multiplier is calculated by dividing MFR by 256. For example, if MFR=X'40', its decimal value is 64, which, divided by 256 results in a fractional component for the multiplier of 1/4. [GOCA-7-843]

See "Line Width" for more information on the line width attribute. [GOCA-7-844]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-845]

The following exception condition causes a standard action to be taken:
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-846]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is a multiplier of X'0100', that is, normal line width. [GOCA-7-847]

---

Set Line End (GSLE) Order
This order sets the value of the current line end attribute. [GOCA-7-848]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'1A' | GSLE | Order code [GOCA-7-849]|
| 1 | CODE | LINEEND | X'00'–X'03' | Value for line end attribute:<br>X'00' Drawing default<br>X'01' Flat<br>X'02' Square<br>X'03' Round<br>All other values: Reserved [GOCA-7-850]|

#### Semantics
The Set Line End order sets the value of the current line end attribute to the value specified in the order. [GOCA-7-851]

The current line end attribute applies to those output primitives that are drawn as straight or curved lines and have ends; that is, not complete figures, such as Box and Full Arc. It defines the shape of the start and end of groups of contiguous straight and curved lines. If the line type is not solid, the line end attribute also defines the shape of the internal ends of the dots and dashes, even for complete figures. [GOCA-7-852]

The standard default in AFP environments is X'03'—Round. [GOCA-7-853]

See "Line End and Line Join" for details of the line-end shapes and their application. [GOCA-7-854]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-855]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'03'—Round. [GOCA-7-856]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-857]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'03'—Round. [GOCA-7-858]

---

Set Line Join (GSLJ) Order
This order sets the value of the current line join attribute. [GOCA-7-859]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'1B' | GSLJ | Order code [GOCA-7-860]|
| 1 | CODE | LINEJOIN | X'00'–X'03' | Value for line join attribute:<br>X'00' Drawing default<br>X'01' Bevel<br>X'02' Round<br>X'03' Miter<br>All other values: Reserved [GOCA-7-861]|

#### Semantics
The Set Line Join order sets the value of the current line join attribute to the value specified in the order. [GOCA-7-862]

The current line join attribute applies to those output primitives that are drawn as straight or curved lines and have joins; that is, not complete figures, such as Box and Full Arc. The line join attribute defines the shape of the joins between contiguous straight and curved lines. [GOCA-7-863]

The standard default in AFP environments is X'02'—Round. [GOCA-7-864]

See "Line End and Line Join" for details of the line-join shapes and their application. [GOCA-7-865]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-866]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'02'—Round. [GOCA-7-867]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-868]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'02'—Round. [GOCA-7-869]

---

Set Line Type (GSLT) Order
This order sets the value of the current line type attribute to a standard value. [GOCA-7-870]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'18' | GSLT | Order code [GOCA-7-871]|
| 1 | CODE | LINETYPE | X'00'–X'08' | Value for line type attribute:<br>X'00' Drawing default; solid if none specified<br>X'01' Dotted line<br>X'02' Short-dashed line<br>X'03' Dash-dot line<br>X'04' Double-dotted line<br>X'05' Long-dashed line<br>X'06' Dash-double-dot line<br>X'07' Solid line<br>X'08' Invisible line<br>All other values: Reserved [GOCA-7-872]|

#### Semantics
The Set Line Type order sets the value of the current line type attribute to the standard value specified in the order. The current line type attribute controls the type of line used to draw line primitives. [GOCA-7-873]

When setting the line type attribute, this drawing order will set it to a standard value. The Set Custom Line Type drawing order will set the attribute to a custom value. [GOCA-7-874]

The standard default for the line type attribute in AFP environments is X'07'—Solid line. [GOCA-7-875]

See "Line Type" for more information on the line type attribute, for guidelines on how the sequence of dashes, dots, and spaces should be generated, and for a discussion of standard and custom line type values. [GOCA-7-876]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-877]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'07'—Solid line. [GOCA-7-878]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-879]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'07'—Solid line. [GOCA-7-880]

---

Set Line Width (GSLW) Order
This order sets the value of the current line width attribute. [GOCA-7-881]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'19' | GSLW | Order code [GOCA-7-882]|
| 1 | UBIN | MH | X'00'–X'FF' | Value for line width attribute:<br>X'00' Drawing default<br>X'01'–X'FF' Integral multiplier of normal line width [GOCA-7-883]|

#### Semantics
The Set Line Width order sets the value of the current line width attribute to the value specified in the order. [GOCA-7-884]

This order also resets the fractional part of the line width attribute to zero. The current line width attribute controls the width of line used to draw line primitives. [GOCA-7-885]

MH specifies an integer multiplier of the normal line width. A value of X'01' represents a unity multiplier, that is, normal line width. [GOCA-7-886]

The standard default in AFP environments is a multiplier of X'01'—normal line width. [GOCA-7-887]

See "Line Width" for more information on the line width attribute. [GOCA-7-888]

The following exception condition causes a standard action to be taken:
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-889]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is a multiplier of X'01', that is, normal line width. [GOCA-7-890]

---

Set Marker Cell (GSMC) Order
This order sets the value of the current marker cell-size attribute. [GOCA-7-891]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'37' | GSMC | Order code [GOCA-7-892]|
| 1 | UBIN | LENGTH | 4, 6 | Length of following data [GOCA-7-893]|
| 2–3 | SBIN | CELLWI | X'8000'–X'7FFF' | Width of marker cell [GOCA-7-894]|
| 4–5 | SBIN | CELLHI | X'8000'–X'7FFF' | Height of marker cell [GOCA-7-895]|
| 6–7 | BITS | FLAGS | | Internal flags (optional):<br>Bit 0 NOTDEFLT: How to interpret a zero cell-size<br>B'0' Zero means drawing default<br>B'1' Zero means a size of zero<br>Bits 1–15: Reserved; only valid value is B'000...0' [GOCA-7-896]|

#### Semantics
The Set Marker Cell order sets the value of the current marker cell-size attribute to the value specified in the order. [GOCA-7-897]

Implementation Note: In earlier versions of AFP GOCA, the Set Marker Cell order was processed as a No-Op with a LENGTH field with value 4. Thus, some implementations will ignore this drawing order, and some will raise an exception if a LENGTH field with value 6 is encountered. [GOCA-7-898]

The CELLWI and CELLHI values are in GPS units. [GOCA-7-899]

If the value of CELLWI is a negative value, this indicates to present the marker as a mirror image in the $x$-direction—that is, about the $Y_g$-axis—of the normal marker symbol. Similarly, a negative CELLHI value indicates to mirror the marker about the $X_g$-axis. Note, however, that all symbols in the default marker set are symmetric in both the $x$ and $y$ directions, so mirror imaging will have no effect on them. [GOCA-7-900]

The NOTDEFLT bit indicates how to interpret a CELLWI or CELLHI value of zero.
- If NOTDEFLT = B'0' (or the FLAGS field is omitted), if either or both of CELLWI or CELLHI are X'0000', the marker cell-size is set to the drawing default value. [GOCA-7-901]
- If NOTDEFLT = B'1', if either or both of CELLWI or CELLHI are X'0000', the marker cell-size is set to zero. [GOCA-7-902]

While the marker cell-size attribute is set to zero, markers will be drawn with zero size: that is, the current position will be updated, but no actual markers will be drawn. [GOCA-7-903]

The standard default marker cell-size in AFP environments is device dependent. However, it is recommended that the standard default marker cell-size be 7/120 of an inch for both width and height (although due to possible scaling, default-sized markers will not necessarily appear at 7/120 of an inch in the usable area). [GOCA-7-904]

Markers are scaled along with the rest of the GPS if scaling is necessary in the mapping from the GPS window into the usable area (object area). [GOCA-7-905]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-906]

---

Set Marker Set (GSMS) Order
This order sets the value of the current marker set attribute. [GOCA-7-907]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'3C' | GSMS | Order code [GOCA-7-908]|
| 1 | CODE | LCID | X'00'–X'FF' | Local identifier (LCID) for the marker set:<br>X'00' Default marker set<br>X'01'–X'FE' Local identifier for marker set (not supported in AFP GOCA)<br>X'FF' Default marker set (not supported in AFP GOCA) [GOCA-7-909]|

#### Semantics
The Set Marker Set order sets the value of the current marker set attribute to the value specified in the order. [GOCA-7-910]

When the value of the marker set attribute is X'00', the marker is drawn from the default marker set. See "Markers" for diagrams of the marker symbols in the default marker set. [GOCA-7-911]

Values X'01' to X'FF' are not supported in AFP GOCA. [GOCA-7-912]

The following exception condition causes a standard action to be taken:
- **EC-C200**: The marker set identified by the value in the current marker set attribute is not available. [GOCA-7-913]
  - **Standard action**: The standard default marker set is used. In AFP environments, this is the default marker set. [GOCA-7-914]

---

Set Marker Symbol (GSMT) Order
This order sets the value of the current marker symbol attribute. [GOCA-7-915]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'29' | GSMT | Order code [GOCA-7-916]|
| 1 | CODE | MCPT | X'00'–X'0A', X'40' | Value of marker symbol code point:<br>X'00' Drawing default; cross if not specified<br>When Marker Set = X'00':<br>X'01' Cross<br>X'02' Plus<br>X'03' Diamond<br>X'04' Square<br>X'05' 6-point star<br>X'06' 8-point star<br>X'07' Filled diamond<br>X'08' Filled square<br>X'09' Dot<br>X'0A' Small circle<br>X'40' Blank<br>All other values: Reserved [GOCA-7-917]|

#### Semantics
The Set Marker Symbol order sets the value of the current marker symbol attribute to the value in the order. [GOCA-7-918]

See "Markers" for diagrams of the marker symbols corresponding to attribute values X'01'–X'0A' in the default marker set. [GOCA-7-919]

The standard default in AFP environments is X'01'—Cross. [GOCA-7-920]

The following exception condition causes a standard action to be taken:
- **EC-C201**: The code point identified by the value in the current marker symbol attribute is not defined in the current marker set. [GOCA-7-921]
  - **Standard action**: The standard default marker symbol is used. In AFP environments, this is X'01'—Cross. [GOCA-7-922]

---

Set Mix (GSMX) Order
This order provides a shorthand way of setting the following foreground mix attributes to the same value:
• Character foreground mix [GOCA-7-923]
• Image foreground mix [GOCA-7-924]
• Line foreground mix [GOCA-7-925]
• Marker foreground mix [GOCA-7-926]
• Pattern foreground mix [GOCA-7-927]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'0C' | GSMX | Order code [GOCA-7-928]|
| 1 | CODE | MODE | X'00'–X'05' | Mix-mode value:<br>X'00' Drawing default<br>X'01' Not supported in AFP GOCA<br>X'02' Overpaint<br>X'03'–X'05' Not supported in AFP GOCA<br>All other values: Reserved [GOCA-7-929]|

#### Semantics
The Set Mix order sets the current value of all five mix attributes to the value specified in the order. Mix attributes control the way in which the color of the foreground of a primitive is combined with the color of the presentation space. [GOCA-7-930]

With MODE set to X'02', the foreground pels are opaque and their color replaces the color of underlying pels in the GPS. Since this is the only foreground mix mode supported in AFP GOCA, selecting the drawing default (MODE X'00') will also default to MODE X'02'. [GOCA-7-931]

For a description of the meaning of the various mix modes, see "Mix". [GOCA-7-932]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-933]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'02'—Overpaint. [GOCA-7-934]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-935]
  - **Standard action**: The standard default value of the attribute is used. In AFP environments, this is X'02'—Overpaint. [GOCA-7-936]

---

Set Pattern Reference Point (GSPRP) Order
This order sets the value of the current pattern reference point attribute. [GOCA-7-937]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'A0' | GSPRP | Order code [GOCA-7-938]|
| 1 | UBIN | LENGTH | 6 | Length of following data [GOCA-7-939]|
| 2 | BITS | FLAGS | | Internal flags:<br>Bit 0 DEFAULT: How to interpret XPOS/YPOS<br>B'0' Set to the specified value<br>B'1' Set to the drawing default<br>Bits 1–7: Reserved; only valid value is B'000...0' [GOCA-7-940]|
| 3 | RES | | X'00' | Reserved; only valid value [GOCA-7-941]|
| 4–5 | SBIN | XPOS | X'8000'–X'7FFF' | $X_g$ coordinate of the pattern reference point [GOCA-7-942]|
| 6–7 | SBIN | YPOS | X'8000'–X'7FFF' | $Y_g$ coordinate of the pattern reference point [GOCA-7-943]|

#### Semantics
The Set Pattern Reference Point order sets the value of the current pattern reference point attribute to the value specified in the order. [GOCA-7-944]

The value of the pattern reference point attribute is used as the origin for the placement of custom patterns when filling an area. The pattern reference point is not used when filling an area either with patterns from the default pattern set or with gradients. [GOCA-7-945]

Note that the pattern reference point does not have to be inside an area being filled. Conceptually, the custom pattern is tiled in all directions from the pattern reference point, all the way to the edges of the GPS. Therefore, the pattern reference point precisely determines the appearance of an area filled with a custom pattern, whether or not the pattern reference point is located on the inside of that area. [GOCA-7-946]

If DEFAULT is B'1', the pattern reference point is set to the drawing default and the XPOS and YPOS parameters are ignored. [GOCA-7-947]

The standard default pattern reference point in AFP GOCA is (0,0). [GOCA-7-948]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-949]

---

Set Pattern Set (GSPS) Order
This order sets the value of the current pattern set attribute. [GOCA-7-950]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'08' | GSPS | Order code [GOCA-7-951]|
| 1 | CODE | LCID | X'00'–X'FF' | Local identifier (LCID) for the pattern set:<br>X'00' Default pattern set<br>X'01'–X'FD' Pattern set containing custom patterns and/or gradients<br>X'FE' Local identifier for the pattern set (not supported in AFP GOCA)<br>X'FF' Default pattern set (not supported in AFP GOCA) [GOCA-7-952]|

#### Semantics
The Set Pattern Set order sets the value of the current pattern set attribute to the value specified in the order. [GOCA-7-953]

When the value of the pattern set attribute is X'00', the pattern is drawn from the default pattern set. See "Patterns" for diagrams of the patterns in the default pattern set. [GOCA-7-954]

When the value of the pattern set attribute is in the range X'01'–X'FD', the pattern is either a custom pattern that has been defined in the current segment using the Begin Custom Pattern drawing order or a gradient that has been defined in the current segment using the Linear Gradient or Radial Gradient drawing orders. [GOCA-7-955]

The standard default in AFP environments is the default pattern set, X'00'. [GOCA-7-956]

Values X'FE' and X'FF' are not supported in AFP GOCA. [GOCA-7-957]

No exceptions are generated until the pattern set is used for area fill. See "Begin Area (GBAR) Order". [GOCA-7-958]

---

Set Pattern Symbol (GSPT) Order
This order sets the value of the current pattern symbol attribute. [GOCA-7-959]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'28' | GSPT | Order code [GOCA-7-960]|
| 1 | CODE | PATT | | Value of pattern-symbol code point:<br>X'00' Drawing default<br>When Pattern Set = X'00':<br>X'01'–X'08' Dotted patterns of decreasing density<br>X'09' Vertical lines<br>X'0A' Horizontal lines<br>X'0B' Diagonal lines 1 (bottom-left to top-right)<br>X'0C' Diagonal lines 2 (bottom-left to top-right)<br>X'0D' Diagonal lines 1 (top-left to bottom-right)<br>X'0E' Diagonal lines 2 (top-left to bottom-right)<br>X'0F' No fill<br>X'10' Solid fill<br>X'40' Blank (processed the same as X'0F', No fill)<br>All other values: Reserved<br>When Pattern Set = X'01'–X'FD':<br>X'01'–X'FF' Pattern symbol value of a custom pattern or gradient [GOCA-7-961]|

#### Semantics
The Set Pattern Symbol order sets the value of the current pattern symbol attribute to the value specified in the order. The value of the pattern symbol attribute determines which particular pattern from the current pattern set is used to fill the interior of subsequent areas. [GOCA-7-962]

See "Patterns" for diagrams of the patterns corresponding to the attribute values X'01'–X'10' in the default pattern set. [GOCA-7-963]

The standard default in AFP environments is X'10'. If the default pattern set is selected, this corresponds to the Solid-fill pattern. [GOCA-7-964]

The pattern symbol value X'00' specifies to use the drawing default pattern symbol, no matter the value of the current pattern set attribute (as long as it is a supported value). For example, if the drawing default pattern, as set by the Set Current Defaults instruction, is pattern set X'03', pattern symbol X'14', that pattern will be used if the current pattern symbol attribute is set to X'00', whether the current pattern set attribute is X'03', X'00', X'11', or any other supported value. [GOCA-7-965]

No exceptions are generated until the pattern symbol is used for area fill. See "Begin Area (GBAR) Order". [GOCA-7-966]

---

Set Process Color (GSPCOL) Order
This order specifies a process color, highlight color, or named color that sets the following color attributes to the same value:
• Character color [GOCA-7-967]
• Image color [GOCA-7-968]
• Line color [GOCA-7-969]
• Marker color [GOCA-7-970]
• Pattern color [GOCA-7-971]

Architecture Note: To fill an area with the color specified by this drawing order, select the drawing default with the Set Pattern Set order, and either the drawing default or solid fill with the Set Pattern Symbol order. [GOCA-7-972]

#### Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'B2' | GSPCOL | Order code [GOCA-7-973]|
| 1 | UBIN | LENGTH | 12–14 | Length of following data [GOCA-7-974]|
| 2 | RES | | X'00' | Reserved; only valid value [GOCA-7-975]|
| 3 | CODE | COLSPCE | | Color space:<br>X'01' RGB<br>X'04' CMYK<br>X'06' Highlight color space<br>X'08' CIELAB<br>X'40' Standard OCA color space [GOCA-7-976]|
| 4–7 | RES | | X'00000000' | Reserved; only valid value [GOCA-7-977]|
| 8 | UBIN | COLSIZE1 | X'01'–X'08', X'10' | Number of bits in component 1; see color space definitions [GOCA-7-978]|
| 9 | UBIN | COLSIZE2 | X'00'–X'08' | Number of bits in component 2; see color space definitions [GOCA-7-979]|
| 10 | UBIN | COLSIZE3 | X'00'–X'08' | Number of bits in component 3; see color space definitions [GOCA-7-980]|
| 11 | UBIN | COLSIZE4 | X'00'–X'08' | Number of bits in component 4; see color space definitions [GOCA-7-981]|
| 12–n | COLVALUE | | See Semantics | Color specification [GOCA-7-982]|

#### Semantics
COLSPCE is a code that defines the color space and the encoding for the color specification. If the color space is invalid, exception condition EC-0004 exists. The standard action is to use the device default color. If the color space is unsupported, exception condition EC-000E exists. The standard action is to use the device default color. A more specific and preferred exception for an invalid or unsupported color space is EC-0E02. The standard action is to use the device default color. [GOCA-7-983]

**Value Descriptions:** [GOCA-7-984]

- **X'01' RGB color space**: The color value is specified with three components. Components 1, 2, and 3 are unsigned binary numbers that specify the red, green, and blue intensity values, in that order. COLSIZE1, COLSIZE2, and COLSIZE3 are nonzero and define the number of bits used to specify each component. COLSIZE4 is reserved and should be set to zero. The intensity range for the R, G, B components is 0 to 1, which is mapped to the binary value range 0 to $(2^{\text{COLSIZE}N} - 1)$, where $N=1,2,3$. [GOCA-7-985]
  - **Architecture Note**: The reference white point and the chromaticity coordinates for RGB are defined in SMPTE RP145-1987, entitled Color Monitor Colorimetry, and in RP37-1969, entitled Color Temperature for Color Television Studio Monitors, respectively. The reference white point is commonly known as Illuminant D6500 or simply D65. The R, G, B components are assumed to be gamma-corrected (nonlinear) with a gamma of 2.2. [GOCA-7-986]
- **X'04' CMYK color space**: The color value is specified with four components. Components 1, 2, 3, and 4 are unsigned binary numbers that specify the cyan, magenta, yellow, and black intensity values, in that order. COLSIZE1, COLSIZE2, COLSIZE3, and COLSIZE4 are nonzero and define the number of bits used to specify each component. The intensity range for the C, M, Y, K components is 0 to 1, which is mapped to the binary value range 0 to $(2^{\text{COLSIZE}N} - 1)$, where $N=1,2,3,4$. This is a device-dependent color space. [GOCA-7-987]
- **X'06' Highlight color space**: This color space defines a request for the presentation device to generate a highlight color. The color value is specified with one to three components. [GOCA-7-988]
  - **Component 1** is a two-byte unsigned binary number that specifies the highlight color number. The first highlight color is assigned X'0001', the second highlight color is assigned X'0002', and so on. The value X'0000' specifies the presentation device default color. COLSIZE1 = X'10' and defines the number of bits used to specify component 1. [GOCA-7-989]
  - **Component 2** is an optional one-byte unsigned binary number that specifies a percent coverage for the specified color. Percent coverage can be any value from 0% to 100% (X'00'–X'64'). The number of distinct values supported is presentation-device dependent. If the coverage is less than 100%, the remaining coverage is achieved with color of medium. COLSIZE2 = X'00' or X'08' and defines the number of bits used to specify component 2. A value of X'00' indicates that component 2 is not specified in the color value, in which case the architected default for percent coverage is 100%. A value of X'08' indicates that component 2 is specified in the color value. [GOCA-7-990]
  - **Component 3** is an optional one-byte unsigned binary number that specifies a percent shading, which is a percentage of black that is to be added to the specified color. Percent shading can be any value from 0% to 100% (X'00'–X'64'). The number of distinct values supported is presentation-device dependent. If percent coverage and percent shading are specified, the effective range for percent shading is 0% to (100-coverage)%. If the sum of percent coverage plus percent shading is less than 100%, the remaining coverage is achieved with color of medium. COLSIZE3 = X'00' or X'08' and defines the number of bits used to specify component 3. A value of X'00' indicates that component 3 is not specified in the color value, in which case the architected default for percent shading is 0%. A value of X'08' indicates that component 3 is specified in the color value. [GOCA-7-991]
  - **Implementation Note**: The percent shading parameter is currently not supported in AFP environments. If the percent value for component 2 or component 3 is invalid, exception condition EC-0E04 exists. The standard action is to use the maximum valid percent value. COLSIZE4 is reserved and should be set to zero. This is a device-dependent color space. [GOCA-7-992]
  - **Architecture Notes**: [GOCA-7-993]
    1. The color that is rendered when a highlight color is specified is device dependent. For presentation devices that support colors other than black, highlight color values in the range X'0001' to X'FFFF' may be mapped to any color. For bilevel devices, the color may be simulated with a graphic pattern. [GOCA-7-994]
    2. If the specified highlight color is "presentation device default", devices whose default color is black use the percent coverage parameter, which is specified in component 2, to render a percent shading. [GOCA-7-995]
    3. On printing devices, the color of medium is normally white, in which case a coverage of $n$% results in adding $(100-n)$% white to the specified color, or tinting the color with $(100-n)$% white. Display devices may assume the color of medium to always be white and use this algorithm to render the specified coverage. [GOCA-7-996]
    4. The highlight color space can also specify indexed colors when used in conjunction with a Color Mapping Table (CMT) or an Indexed (IX) Color Management Resource (CMR). In that case, component 1 specifies a two-byte value that is the index into the CMT or the IX CMR, and components 2 and 3 are ignored. Note that when both a CMT and Indexed CMRs are used, the CMT is always accessed first. To preserve compatibility with existing highlight color devices, indexed color values X'0000'–X'00FF' are reserved for existing highlight color applications and devices. That is, indexed color values in the range X'0000'–X'00FF', assuming they are not mapped to a different color space in a CMT, are mapped directly to highlight colors. Indexed color values in the range X'0100'–X'FFFF', assuming they are not mapped to a different color space in a CMT, are used to access Indexed CMRs. For a description of the Color Mapping Table in MO:DCA environments, see the Mixed Object Document Content Architecture (MO:DCA) Reference. [GOCA-7-997]
- **X'08' CIELAB color space**: The color value is specified with three components. Components 1, 2, and 3 are binary numbers that specify the $L, a, b$ values, in that order, where $L$ is the luminance and $a$ and $b$ are the chrominance differences. Component 1 specifies the $L$ value as an unsigned binary number; components 2 and 3 specify the $a$ and $b$ values as signed binary numbers. COLSIZE1, COLSIZE2, and COLSIZE3 are nonzero and define the number of bits used to specify each component. COLSIZE4 is reserved and should be set to zero. The range for the $L$ component is 0 to 100, which is mapped to the binary value range 0 to $(2^{\text{COLSIZE}1} - 1)$. The range for the $a$ and $b$ components is -127 to +127, which is mapped to the binary range $-(2^{\text{COLSIZE}N - 1} - 1)$ to $+(2^{\text{COLSIZE}N - 1} - 1)$, where $N=2,3$. [GOCA-7-998]
  - For color fidelity, 8-bit encoding should be used for each component, that is, COLSIZE1, COLSIZE2, and COLSIZE3 are set to X'08'. When the recommended 8-bit encoding is used for the $a$ and $b$ components, the range is extended to include -128, which is mapped to the value X'80'. If the encoding is less than 8 bits, treatment of the most negative binary endpoint for the $a$ and $b$ components is device dependent, and tends to be insignificant because of the quantization error. [GOCA-7-999]
  - **Architecture Note**: The reference white point for CIELAB is known as D50 and is defined in CIE publication 15-2 entitled Colorimetry. [GOCA-7-1000]
- **X'40' Standard OCA color space**: The color value is specified with one component. Component 1 is an unsigned binary number that specifies a named color using a two-byte value from the Standard OCA Color Value Table. For a complete description of the Standard OCA Color Value Table, see the Mixed Object Document Content Architecture (MO:DCA) Reference. COLSIZE1 = X'10' and defines the number of bits used to specify component 1. COLSIZE2, COLSIZE3, and COLSIZE4 are reserved and should be set to zero. This is a device-dependent color space. See Table 5 for the meaning of the two-byte values. [GOCA-7-1001]
- **All others**: Reserved [GOCA-7-1002]

COLSIZE1 defines the number of bits used to specify the first color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary. For example, if COLSIZE1 = X'06', the first color component has two padding bits. [GOCA-7-1003]

COLSIZE2 defines the number of bits used to specify the second color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary. [GOCA-7-1004]

COLSIZE3 defines the number of bits used to specify the third color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary. [GOCA-7-1005]

COLSIZE4 defines the number of bits used to specify the fourth color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary. [GOCA-7-1006]

For COLSIZE1–COLSIZE4, if the specified value is invalid, exception condition EC-0004 exists. The standard action is to use the device default color. If the specified value is unsupported, exception condition EC-000E exists. The standard action is to use the device default color. A more specific and preferred exception for an invalid or unsupported number of bits in a color component is EC-0E05. The standard action is to use the device default color. [GOCA-7-1007]

COLVALUE specifies the color value in the defined format and encoding. If the color value is invalid, exception condition EC-0004 exists. The standard action is to use the device default color. If the color value is unsupported, exception condition EC-000E exists. The standard action is to use the device default color. A more specific and preferred exception for an invalid or unsupported color value is EC-0E03. The standard action is to use the device default color. Note that the number of bytes specified for this parameter depends on the color space. For example, when there are 8 bits per component, an RGB color value is specified with 3 bytes, while a CMYK color value is specified with 4 bytes. If extra bytes are specified, they are ignored as long as the drawing order length is valid. [GOCA-7-1008]

The following exception condition raises a drawing process check:
- **EC-0003**: The order has an incorrect length. [GOCA-7-1009]

The following exception conditions cause a standard action to be taken:
- **EC-0004**: The attribute value specified in the order is not valid. [GOCA-7-1010]
  - **Standard action**: The device default color is used. [GOCA-7-1011]
- **EC-000E**: The attribute value specified in the order is not supported. [GOCA-7-1012]
  - **Standard action**: The device default color is used. [GOCA-7-1013]
- **EC-0E02**: The color space specified in the order is invalid or unsupported. [GOCA-7-1014]
  - **Standard action**: The device default color is used. [GOCA-7-1015]
- **EC-0E03**: The color value specified in the order is invalid or unsupported. [GOCA-7-1016]
  - **Standard action**: The device default color is used. [GOCA-7-1017]
- **EC-0E04**: The highlight color percent value specified in the order is invalid. [GOCA-7-1018]
  - **Standard action**: The maximum valid percent value is used. [GOCA-7-1019]
- **EC-0E05**: The number of bits for a color component specified in the order is invalid or unsupported. [GOCA-7-1020]
  - **Standard action**: The device default color is used. [GOCA-7-1021]

Architecture Notes:
1. AFP printers should generate the specific and preferred exceptions defined for this drawing order. For example, if the color value is invalid or unsupported, AFP printers should generate EC-0E03. [GOCA-7-1022]
2. If colors are simulated in AFP environments, color exceptions need not be generated. [GOCA-7-1023]
3. When a color space other than the standard OCA color space is selected with this drawing order, the concept of mixing color index values in the GPS does not apply. The use of mixing rules other than "Overpaint" or "Leave Alone" is not possible. [GOCA-7-1024]
4. For a description of color spaces and their relationships, see R. Hunt, The Reproduction of Colour in Photography, Printing, and Television, Fifth Edition, Fountain Press, 1995. [GOCA-7-1025]

Copyright © AFP Consortium 1997, 2017 167
