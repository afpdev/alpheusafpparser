# Chapter 5. Segments

Segments are self-contained collections of drawing orders. This chapter describes:
*   Segment types [GOCA-5-001]
*   Segment processing sequence [GOCA-5-002]
*   Segment properties [GOCA-5-003]
*   Segment prolog [GOCA-5-004]

## Segment Types

Segments of the following types can be created, as determined by the CHAIN parameter in the Begin Segment command:
*   Chained [GOCA-5-005]
*   Unchained [GOCA-5-006]

In AFP GOCA, all segments are processed in immediate mode. In this mode, chained segments define the picture. They are processed by the drawing processor as they are received from the environment interface, and are not stored. Unchained segments are ignored.
Segments are transmitted by the controlling environment to the drawing processor with Begin Segment Commands and the drawing orders that follow these commands. The Begin Segment (chained) command invokes the drawing processor to draw the segment. [GOCA-5-007]

In the MO:DCA and IPDS environments, a graphics object can contain multiple chained segments. All chained segments within the object are processed independently in the sequence in which they arrive; together they generate the graphics picture. A segment cannot be split across multiple graphics objects. [GOCA-5-008]

The Append option indicates that the segment is a continuation of the preceding segment. Unfinished drawing orders, areas, images, and prologs may be completed in appended segment data. See “Begin Segment Command” for further details of the functions of the Append option. [GOCA-5-009]

## Segment Processing Sequence

Segment processing starts at the first segment in the segment chain. The processing of a segment always starts at its first order and proceeds in sequence, order by order, until the last order is processed, at which time the segment is terminated. [GOCA-5-010]

When the invocation operates on a single segment, it is complete when the segment is terminated. [GOCA-5-011]

When the invocation operates on a chain of segments, the graphics processor sequentially processes and terminates each segment in the chain. When the last specified chained segment is terminated, the invocation is complete. [GOCA-5-012]

---

## Segment Properties

Associated with each segment is a set of properties. These properties are specified in the Begin Segment command; see “Begin Segment Command”. The function of these properties is to provide control information relevant to the processing of the segment. [GOCA-5-013]

The properties and their functions are as follows: [GOCA-5-014]

| Property | Function |
| :--- | :--- |
| **Length** | 2-byte length of segment data. [GOCA-5-015]|
| **Chain** | Indicates whether or not the segment is to be chained. [GOCA-5-016]|
| **Prolog** | Indicates that the segment has a prolog section at the beginning of the data. See **Segment Prolog** for details of prolog processing. [GOCA-5-017]|
| **New/Append** | Indicates whether this is a new segment or a segment to be appended to the previous segment. [GOCA-5-018]|

These properties are unique to the segment. They are not inherited between segments. They are defined when a segment is created. [GOCA-5-019]
## Segment Prolog

Segment prologs provide a defined position where initial attributes and drawing process controls are set. A prolog is optional; its presence is indicated by the PROLOG bit in the Begin Segment command. [GOCA-5-020]

If present, the prolog is always at the beginning of the segment, and is ended by an End Prolog order within the same segment. Note that for a segment that is spread over multiple appended segments with multiple Begin Segment commands, the End Prolog drawing order may be specified in any of the appended segments. [GOCA-5-021]

The end of a prolog in a segment must be indicated by an End Prolog order. Exception condition EC-000C is raised if the end of the segment is reached while still in the prolog. [GOCA-5-022]

**Note:** Exception condition EC-3E00 is raised if an End Prolog order is found when not in a prolog. [GOCA-5-023]

Within the prolog, only the following orders are valid:
*   Comment [GOCA-5-024]
*   No-Operation [GOCA-5-025]
*   Segment Characteristics [GOCA-5-026]
*   Set Arc Parameters [GOCA-5-027]
*   Set Background Mix [GOCA-5-028]
*   Set Character Angle [GOCA-5-029]
*   Set Character Cell [GOCA-5-030]
*   Set Character Direction [GOCA-5-031]
*   Set Character Precision [GOCA-5-032]
*   Set Character Set [GOCA-5-033]
*   Set Character Shear [GOCA-5-034]
*   Set Color [GOCA-5-035]
*   Set Current Position [GOCA-5-036]
*   Set Custom Line Type [GOCA-5-037]
*   Set Extended Color [GOCA-5-038]
*   Set Fractional Line Width [GOCA-5-039]
*   Set Line End [GOCA-5-040]
Segment Properties

---

*   Set Line Join [GOCA-5-041]
*   Set Line Type [GOCA-5-042]
*   Set Line Width [GOCA-5-043]
*   Set Marker Cell [GOCA-5-044]
*   Set Marker Precision (obsolete, see Appendix C, “AFP GOCA Migration Functions”) [GOCA-5-045]
*   Set Marker Set [GOCA-5-046]
*   Set Marker Symbol [GOCA-5-047]
*   Set Mix [GOCA-5-048]
*   Set Pattern Reference Point [GOCA-5-049]
*   Set Pattern Set [GOCA-5-050]
*   Set Pattern Symbol [GOCA-5-051]
*   Set Process Color [GOCA-5-052]

**Implementation Note:** Some AFP printers also accept the Set Pick Identifier order in a prolog, and process this order as a No-Op. [GOCA-5-053]

The other supported orders, when specified in the prolog, cause exception condition EC-000C to be raised. [GOCA-5-054]

### Segment Prolog Semantics

The semantics of the segment prolog for chained segments processed in immediate mode are as follows. [GOCA-5-055]

The segment data is processed by the graphics processor following processing of a Begin Segment command. [GOCA-5-056]

For an appended segment—that is, one specified with the APP flag equal to B'11'—the segment data that follows the Begin Segment command is part of the segment, not the whole segment. [GOCA-5-057]

For an Immediate mode chained segment that is spread over multiple appended segments with multiple Begin Segment commands, only the PROLOG flag bit in the Begin Segment that is marked as New determines whether the segment has a prolog or not. The PROLOG bits in subsequent Begin Segment commands for appended segments are ignored. [GOCA-5-058]

For a segment that is spread over multiple appended segments with multiple Begin Segment commands, it is necessary for the graphics processor to check the segment data in all the Begin Segment commands for the segment before it can determine whether the segment is valid or not, that is, whether or not the segment contains an End Prolog order to match the setting of the segment prolog property. [GOCA-5-059]

For example, with a segment whose data is spread over three appended segments with three Begin Segment commands, the first command could indicate that the segment has a prolog, but the End Prolog order could well be in data in the third segment. The PROLOG flag in the first Begin Segment command of a multi-part segment does not indicate that an End Prolog order is necessarily contained within the segment data that follows the Begin Segment command.
Segment Prolog

---

Copyright © AFP Consortium 1997, 2017 65
