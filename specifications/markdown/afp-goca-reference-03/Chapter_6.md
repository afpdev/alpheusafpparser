# Chapter 6. Environment Controls

This chapter describes:
*   Control instructions [GOCA-6-001]
*   Drawing processor facilities, including: [GOCA-6-002]
    *   Current attributes [GOCA-6-003]
    *   Drawing process controls [GOCA-6-004]

## Control Instructions

The controlling environment communicates with the graphics processor by means of control instructions and drawing process controls. Control instructions are embedded in environment-dependent carriers in AFP GOCA as follows:
*   When the graphics object is carried in a MO:DCA data stream, the carrier is a Graphics Data Descriptor (GDD) structured field; for more information, see Appendix A, “Mixed Object Document Content Architecture (MO:DCA) Environment”. [GOCA-6-005]
*   When the graphics object is carried in an IPDS data stream, the carrier is a Graphics Data Descriptor self-defining field in the Write Graphics Control (WGC) command; for more information, see Appendix B, “Intelligent Printer Data Stream (IPDS) Environment”. [GOCA-6-006]

Both the GDD and WGC contain the Set Current Defaults control instruction, defined in “Set Current Defaults (SCD) Instruction”. [GOCA-6-007]

**Note:** In the MO:DCA environment, if the drawing defaults contain any invalid bits, the processor optionally raises exception condition EC-000A. [GOCA-6-008]

---

## Set Current Defaults (SCD) Instruction

This control instruction sets the current default values of the selected attributes and drawing process controls.
When the graphics object is carried in a MO:DCA data stream, this control instruction is contained in the Graphics Data Descriptor (GDD) structured field. When the graphics object is carried in an IPDS data stream, this control instruction is contained in the Graphics Data Descriptor (GDD) self-defining field of the Write Graphics Control (WGC) command. [GOCA-6-009]

### Syntax

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'21' | | Set Current Defaults instruction [GOCA-6-010]|
| 1 | UBIN | LENGTH | 4–n | Length of following data [GOCA-6-011]|
| 2 | SET | SET | | Drawing attributes:<br>X'00' Drawing attributes<br>X'01' Line attributes<br>X'02' Character attributes<br>X'03' Marker attributes<br>X'04' Pattern attributes<br>X'0B' Arc parameters<br>X'10' Process color attributes<br>X'11' Normal line width attribute<br>All others: Reserved [GOCA-6-012]|
| 3–4 | CODE | MASK | X'0000'–X'FFFF' | Set mask [GOCA-6-013]|
| 5 | BITS | FLAGS | | Bit 0: DEFAULT<br>B'0' Set all indicated items to their standard default values<br>B'1' Set the indicated items to the values contained in the source data<br>Bits 1–3: RES1 (Reserved; only valid value is B'000')<br>Bits 4–7: RES2 (Reserved; only valid value is B'1111') [GOCA-6-014]|
| 6–n | DATA | | | Default values; bytes 6 onward are not present if DEFAULT = B'0' [GOCA-6-015]|

This instruction permits the setting of a variable number of values, under control of the MASK parameter in bytes 3–4, into the attribute set selected by the value of the SET parameter in byte 2. When a MASK bit equals 0, the default does not change and data bytes are not present for that attribute. A B'1' in any bit of MASK indicates that the corresponding item is to be set. If the DEFAULT bit is B'0', these items are set to the standard defaults; if it is B'1', these items are set to the values contained in the data in bytes 6–n of the instruction. [GOCA-6-016]

If the value of an attribute specifies the drawing default in an attribute setting order, for example the X'00' value of the MODE parameter used in the Set Mix order, it causes the current default to be set to the standard default value. [GOCA-6-017]

Bits 0–15 in MASK correspond to items within the selected attribute set, as shown in the following tables. The number of bytes required is set into the item corresponding to each 1 bit in Mask, in ascending numerical order of the MASK bit (0–15). Setting is terminated when all the items requested have been loaded. [GOCA-6-018]

---

The default value of a given attribute should be specified only once. If specified more than once, the results are implementation dependent; it is recommended that future implementations use the last-specified value. [GOCA-6-019]

**Notes:**
1. When the integral part of the line width attribute is set, the fractional part is reset to zero. See “Line Attributes” for a description of the Line Width attribute. [GOCA-6-020]
2. The format of the DATA field is the same as the corresponding data in the attribute setting drawing orders. [GOCA-6-021]

### Drawing Attributes (SET=X'00')

| Mask bit | Item name | Length (bytes) |
| :--- | :--- | :--- |
| 0 | Color | 2 [GOCA-6-022]|
| 1 | Reserved; must be zero | N/A [GOCA-6-023]|
| 2 | Foreground mix | 1 [GOCA-6-024]|
| 3 | Background mix | 1 [GOCA-6-025]|
| 4–15 | Reserved; must be zeros | N/A [GOCA-6-026]|

**Note:** Setting any of the above attributes to a value is a shorthand way of setting all color, or mix, attributes to the same value. [GOCA-6-027]

### Line Attributes (SET=X'01')

| Mask bit | Item name | Length (bytes) |
| :--- | :--- | :--- |
| 0 | Line type | 1 [GOCA-6-028]|
| 1 | Line width | 1 [GOCA-6-029]|
| 2 | Line end | 1 [GOCA-6-030]|
| 3 | Line join | 1 [GOCA-6-031]|
| 4–15 | Reserved; must be zeros | N/A [GOCA-6-032]|

**Note:** The line type attribute cannot be set to a custom value by this instruction. [GOCA-6-033]

### Character Attributes (SET=X'02')

| Mask bit | Item name | Length (bytes) |
| :--- | :--- | :--- |
| 0 | Angle X,Y | 4 [GOCA-6-034]|
| 1 | Cell-size CW,CH | 4 [GOCA-6-035]|
| 2 | Direction | 1 [GOCA-6-036]|
| 3 | Precision | 1 [GOCA-6-037]|
| 4 | Character Set | 1 [GOCA-6-038]|
| 5 | Shear, X,Y | 4 [GOCA-6-039]|
| 6–15 | Reserved; must be zeros | N/A [GOCA-6-040]|

**Note:** The character symbol default attribute is not settable by this instruction. [GOCA-6-041]

### Marker Attributes (SET=X'03')

| Mask bit | Item name | Length (bytes) |
| :--- | :--- | :--- |
| 0 | Reserved; must be zero | N/A [GOCA-6-042]|
| 1 | Marker cell-size width, height | 4 [GOCA-6-043]|
| 2 | Reserved; must be zero | N/A [GOCA-6-044]|
| 3 | Marker precision (obsolete, see Appendix C, “AFP GOCA Migration Functions”) | 1 [GOCA-6-045]|
| 4 | Marker set | 1 [GOCA-6-046]|
| 5–6 | Reserved; must be zeros | N/A [GOCA-6-047]|
| 7 | Marker symbol | 1 [GOCA-6-048]|
| 8–15 | Reserved; must be zeros | N/A [GOCA-6-049]|

### Pattern Attributes (SET=X'04')

| Mask bit | Item name | Length (bytes) [GOCA-6-050]|
| :--- | :--- | :--- |
| 0–3 | Reserved; must be zeros | N/A [GOCA-6-051]|
| 4 | Pattern Set | 1 [GOCA-6-052]|
| 5–6 | Reserved; must be zeros | N/A [GOCA-6-053]|
| 7 | Pattern Symbol | 1 [GOCA-6-054]|
| 8–10 | Reserved; must be zeros | N/A [GOCA-6-055]|
| 11 | Pattern Reference Point | 4 [GOCA-6-056]|
| 12–15 | Reserved; must be zeros | N/A [GOCA-6-057]|

### Arc Parameters (SET=X'0B')

| Mask bit | Item name | Length (bytes) [GOCA-6-058]|
| :--- | :--- | :--- |
| 0 | P value | 2 [GOCA-6-059]|
| 1 | Q value | 2 [GOCA-6-060]|
| 2 | R value | 2 [GOCA-6-061]|
| 3 | S value | 2 [GOCA-6-062]|
| 4–15 | Reserved; must be zeros | N/A [GOCA-6-063]|

### Process Color Attributes (SET=X'10')

| Mask bit | Item name | Length (bytes) [GOCA-6-064]|
| :--- | :--- | :--- |
| 0 | Foreground mix | 1 [GOCA-6-065]|
| 1 | Background mix | 1 [GOCA-6-066]|
| 2 | Process Color | 12–14 [GOCA-6-067]|
| 3–15 | Reserved; must be zeros | N/A [GOCA-6-068]|

**Architecture Note:** If the color is specified using Drawing Attributes (SET = X'00'), and the process color is also specified using Process Color (SET = X'10'), the last-specified color is used. [GOCA-6-069]

### Normal Line Width Attribute (SET=X'11')

| Mask bit | Item name | Length (bytes) |
| :--- | :--- | :--- |
| 0 | Normal line width | 2 [GOCA-6-070]|
| 1–15 | Reserved; must be zeros | N/A [GOCA-6-071]|

**Architecture Note:** If the normal line width attribute is specified, it establishes the absolute value of the normal line width in 1440ths of an inch. If the line attributes are also specified and define the line width as a multiple of the normal line width, the multiple is calculated based on the absolute value of the normal line width. Furthermore, all Set Line Width and Set Fractional Line Width orders in the object are also calculated based on the absolute value of the normal line width. [GOCA-6-072]

### Instruction Process Checks

A check condition is set under the following conditions:
*   **IPC-0002** [GOCA-6-073]
    *   If the SET parameter (byte 2) is invalid or unsupported [GOCA-6-074]
    *   If the FLAGS parameter (byte 5) bits 1–3 are not B'000', or bits 4–7 are not B'1111' [GOCA-6-075]
    *   If an unallocated item is referenced in the MASK parameter (bytes 3–4) [GOCA-6-076]
*   **IPC-0003** [GOCA-6-077]
    *   If the FLAGS parameter (byte 5) bit 0 is B'0' and LENGTH is not X'04' [GOCA-6-078]
    *   If the FLAGS parameter (byte 5) bit 0 is B'1' and the length of the immediate data (byte 6 onward) does not exactly match the length implied by the MASK parameter [GOCA-6-079]
*   **IPC-0021** [GOCA-6-080]
    *   If any values in the data are invalid or unsupported [GOCA-6-081]

---

## Drawing Processor Facilities

The following facilities are available to the drawing processor while it is processing segments:
*   Current attributes [GOCA-6-082]
*   Drawing process controls [GOCA-6-083]

### Current Attributes

As the orders in a segment are processed, the drawing processor maintains the current values of all primitive attribute types in the current attributes. These values are used by the graphics processor to draw output primitives in the GPS. [GOCA-6-084]

Figure 33 shows how the controlling environment uses pre-defined standard defaults and the Set Current Defaults control instruction in the GDD and WGC to establish drawing defaults before the drawing processor is invoked to process a segment. At the start of processing of each new segment, the drawing default values are set into the current attributes. [GOCA-6-085]

### Figure 33. Attributes and Drawing Process Control

CONTROLLING ENVIRONMENT -> GRAPHICS PROCESSOR
STANDARD DEFAULTS -> DRAWING DEFAULTS
CURRENT DEFAULTS -> CURRENT VALUES (New values SET by Instructions)
Drawing Processor Invocation -> (New values SET by Orders)
SET Instructions and Initialization -> SET Orders and SEGMENT Initiation [GOCA-6-086]

### Drawing Process Controls

The following controls manage various aspects of the drawing process:
*   **Parameter type:** The format of the parameters in the drawing orders. These controls are described in “Parameter Type”. [GOCA-6-087]
*   **Arc parameters:** Values used as parameters when drawing circles or ellipses. These controls are described in “Set Arc Parameters (GSAP) Order”. [GOCA-6-088]

Drawing defaults exist for each drawing process control. The defaults are maintained by the processor, and they are set to the standard defaults, or to the current defaults provided by the environment, whenever the processor is invoked. [GOCA-6-089]

### Parameter Type

The parameter type specifies the format of the parameters within drawing orders, and has two parts:
*   Coordinate type [GOCA-6-090]
*   Geometric parameter format [GOCA-6-091]

The format of this control is: [GOCA-6-092]

| Mnemonic | Standard default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| COORD | X'00' | 1 | Coordinate type [GOCA-6-093]|
| GEOM | X'00' | 1 | Geometric parameter format [GOCA-6-094]|

The following value of the coordinate type parameter is specified by both the GRS3 and DR/2V0 subsets and is supported in AFP GOCA environments:
*   **X'00'** 2-D coordinates [GOCA-6-095]

The following value of the geometric parameter format is specified by both the GRS3 and DR/2V0 subsets and is supported in AFP GOCA environments:
*   **X'00'** 16-bit signed integer, “high byte first” format [GOCA-6-096]

---

Copyright © AFP Consortium 1997, 2017 73
