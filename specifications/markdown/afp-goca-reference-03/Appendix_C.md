Appendix C. AFP GOCA Migration Functions
Introduction
This appendix:
• Describes migration functions that may occur in an AFP GOCA object. [GOCA-C-001]
General
The objective in defining migration functions is twofold:
1. T o allow existing applications to run unchanged. [GOCA-C-002]
2. T o provide a clear growth direction for future applications. [GOCA-C-003]
The migration functions are divided into the following categories:
1. Obsolete functions. These are attributes, drawing orders, and parameters that will be accepted but ignored. New products must not generate these functions. [GOCA-C-004]
2. Obsolete exception conditions. These are exception conditions that will be accepted but ignored. New products must not generate these functions. [GOCA-C-005]
3. Retired functions. These are drawing orders and parameters whose use has been retired except for specific products. These specific products may use these functions. All other products should not use these functions; that is, generators should not generate these functions and receivers may ignore them. 4. Retired exception conditions. These are exception conditions whose use has been retired except for specific products. These specific products may report these exception conditions. All other products should not report these exception conditions. [GOCA-C-006]
Obsolete Functions
Obsolete Attributes
Marker Precision Attribute
The marker precision attribute was used in AFP GOCA as a method to allow implementations to draw markers in a device-dependent fashion, rather than necessarily using the marker attributes. Previously , the attribute was defined in the following way in “Markers”:
The position and appearance of a marker are dependent on the value of the marker precision attribute, as follows:
Precision 1 String Precision. In AFP GOCA, the size of the marker symbols in the default marker set are device dependent. The marker is positioned at a specified point, or at the current position.
Precision 2 Character Precision. In AFP GOCA, this is the same as Precision 1—String Precision. [GOCA-C-007]

---

More information about the marker precision attribute and the meaning of its possible values can be found in the description of the drawing order used to set the marker precision attribute, the obsolete Set Marker
Precision (GSMP) Order , shown in the next section.
Obsolete Drawing Orders
Set Marker Precision (GSMP) Order
This order sets the value of the current marker precision attribute.
Syntax
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'3B' | GSMP | order code [GOCA-C-008]|
| 1 | CODE | PREC | X'00'-X'03' | Value for marker precision attribute: [GOCA-C-009]|
| X'00' Drawing default [GOCA-C-010]| | | | |
| X'01' String precision [GOCA-C-011]| | | | |
| X'02' Character precision [GOCA-C-012]| | | | |
| X'03' | Stroke | precision | (not | supported in AFP GOCA) [GOCA-C-013]|
| All | other | values | | Reserved [GOCA-C-014]|
| Semantics [GOCA-C-015]| | | | |
| The | Set | Marker | Precision | order sets the value of the current marker precision attribute to the value specified in the order . [GOCA-C-016]|
| Value Description [GOCA-C-017]| | | | |
| X'00' | Drawing | Default. | This | value sets the current marker precision attribute to the value of the drawing default. [GOCA-C-018]|
| The | standard | default | in | AFP environments is X'02'—Character precision. [GOCA-C-019]|
| X'01' | Precision | 1—String | Precision. | The markers are drawn using the quickest, simplest mode possible in the device. In this mode, the only attributes that must, as a minimum, be [GOCA-C-020]|
| considered | when | the | markers | are drawn are the marker symbol and the marker set. The positioning of the marker can be approximate. [GOCA-C-021]|
| X'02' | Precision | 2—Character | Precision. | In AFP GOCA, this is treated the same as precision 1. [GOCA-C-022]|
| X'03' | Precision | 3—Stroke | Precision. | This value is not supported in AFP GOCA. [GOCA-C-023]|
| The | following | exception | conditions | cause a standard action to be taken: [GOCA-C-024]|
| EC-0004 | The | attribute | value | specified in the order is not valid. [GOCA-C-025]|
| Standard | action: | The | standard | default value of the attribute is used. In AFP environments, this is X'02'—Character precision. [GOCA-C-026]|
| EC-000E | The | attribute | value | specified in the order is not supported. [GOCA-C-027]|
| Standard | action: | The | standard | default value of the attribute is used. In AFP environments, this is X'02'—Character precision. [GOCA-C-028]|
| AFP | GOCA | Migration | | Functions [GOCA-C-029]|

---

Obsolete Exception Conditions
EC-C202 The current marker set attribute value identifies a marker set definition that cannot support the functions implied by the current marker precision attribute.
Standard action: The marker set identified by the current marker set attribute value is used, with the lowest value of precision that the marker set can support.
Retired Functions
Retired Parameters
Drawing Order Subset Parameter
The use of this parameter in the GDD is restricted to pre-2012 applications that generate or receive DR/2V0 (GRS2) AFP GOCA objects. [GOCA-C-030]
| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | X'F7' | Drawing | Order Subset [GOCA-C-031]|
| 1 | UBIN | LENGTH | 7 | Length of following data [GOCA-C-032]|
| 2 | CODE | X'B0' | Drawing | order subset [GOCA-C-033]|
| 3-4 | RES | X'0000' | Reserved; | only valid value [GOCA-C-034]|
| 5 | CODE | SUBLEV | X'02' | Drawing order subset level 2.0 [GOCA-C-035]|
| 6 | CODE | VERSION | X'00' | V ersion 0 [GOCA-C-036]|
| 7 | UBIN | LENGTH | X'01' | Length of following field [GOCA-C-037]|
| 8 | CODE | GEOM | X'00' | Coordinate formats in data: [GOCA-C-038]|
| X'00' | 16-bit | high-byte-first | signed | integer [GOCA-C-039]|
| If | invalid | bits | are | specified in this self-identifying parameter , EC-000A may optionally be detected. [GOCA-C-040]|
| Retired Exception Conditions [GOCA-C-041]| | | | |
| EC-0002 | A | reserved | byte | or bit in the order is not set to zero. This is an optional exception. [GOCA-C-042]|
| The | use | of | this | exception condition is restricted to pre-May-2012 AFP GOCA receivers. New [GOCA-C-043]|
| AFP | GOCA | receivers | should | not report this exception condition. [GOCA-C-044]|
| Note: | Some | pre-May-2012 | receivers | might report this exception condition when new functions are encountered that use previously reserved bits. [GOCA-C-045]|
| AFP | GOCA | Migration | | Functions [GOCA-C-046]|

---

Copyright © AFP Consortium 1997, 2017 199
