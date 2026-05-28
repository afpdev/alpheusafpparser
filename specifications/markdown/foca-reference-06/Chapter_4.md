# Chapter 4. FOCA Overview

The Font Object Content Architecture (FOCA) supports presentation of character shapes by defining their characteristics, which include Font-Description information for identifying the characters, Font-Metric information for positioning the characters, and Character-Shape information for presenting the character images. Presenting a graphic character on a presentation surface requires that you communicate this information clearly to rotate and position characters correctly on the physical or logical page. For example, you can: [FOCA-4-001]

*   Rotate a physical page within a print system [FOCA-4-002]
*   Rotate and move a logical page to new locations on a physical page [FOCA-4-003]
*   Rotate and position character shapes anywhere within a logical page [FOCA-4-004]

**Note:** FOCA does not address the orientation of pages on devices, logical pages on a physical page, or lines of text on a logical page. [FOCA-4-005]

By defining FOCA parameters, you enable the system to separate font information from physical and logical page information as the system identifies, positions, and presents characters. This chapter gives an overview of FOCA by defining the terms you need when you use FOCA parameters, including terms for: [FOCA-4-006]

*   The character coordinate system, including units of measure and direction within the coordinate system [FOCA-4-007]
*   Graphic characters [FOCA-4-008]
*   Character-set metrics [FOCA-4-009]
*   Design metrics [FOCA-4-010]

The chapter concludes with some general recommendations when using FOCA to design fonts. [FOCA-4-011]

## Character Coordinate System

FOCA positions character shapes within an orthogonal character coordinate system, as shown in Figure 15. [FOCA-4-012]

**Figure 15. Character Coordinate System** [FOCA-4-013]

*   **Origin (Character Reference Point)**: The point where the axes intersect. [FOCA-4-014]
*   **X-Axis (Baseline)**: Positive X direction is the Escapement Direction. [FOCA-4-015]
*   **Y-Axis**: Positive Y direction is 90° counterclockwise or 270° clockwise from the positive X direction. [FOCA-4-016]

FOCA locates font or character measurements in this system as distances between points. All the positioning and rotating measurements of a character are relative to the origin; as a character is rotated, the character coordinate system does not rotate. [FOCA-4-017]

**Note:** The default escapement direction is left-to-right along the x-axis of the character coordinate system. [FOCA-4-018]

### Units of Measure

A font designer specifies the type of measurements used in defining a font as either **fixed units**, such as inches or centimeters, or **relative units**, which are dimensionless. [FOCA-4-019]

Fixed units apply only to a single or limited number of devices, while relative units allow a font to be used by multiple devices. If the designated font was designed using relative units, the system scales or proportions the font measurements to accommodate the presentation device. [FOCA-4-020]

#### Unit-Em

FOCA expresses relative units as a decimal fraction of a Unit-Em. A **Unit-Em** is a unit of one that corresponds to the height of the design space, which is also the nominal vertical font size (specified in the Nominal Vertical Font Size parameter). [FOCA-4-021]

**Figure 16. Relative Unit as the Unit-Em** [FOCA-4-022]

In order to find the fixed value of any of the relative metrics expressed as fractions of a Unit-Em, the fraction must be multiplied by the Unit-Em point value (customarily 1/72 inch). [FOCA-4-023]

#### Unit-Base

A Unit-Base specifies a code that represents the length of the measurement base. [FOCA-4-024]

**Table 10. Unit-Base Values** [FOCA-4-025]

| Value | Unit-Base |
| :--- | :--- |
| 0 | Ten inches [FOCA-4-026]|
| 1 | Ten centimeters [FOCA-4-027]|
| 2 | Relative units (measurement base = one Unit-Em) [FOCA-4-028]|
| 3–255 | Reserved [FOCA-4-029]|

#### Units Per Unit-Base

The Units per Unit-Base specifies the number of units in the measurement base. Allowable values are from 1 to 32,767. A value of 1000 is usually taken for the Units per Unit-Base multiplier for relative metrics. [FOCA-4-030]

#### Calculating the Units of Measure

Units of Measure is calculated using the following formula:
`Units of Measure = measurement base / Units per Unit-Base` [FOCA-4-031]

**Example 1 (Fixed)**: 240 pels per inch.
*   Unit-Base = 0 (10 inches) [FOCA-4-032]
*   Units per Unit-Base = 2400 [FOCA-4-033]
*   `10 / 2400 = 1/240 inch` [FOCA-4-034]

**Example 2 (Relative)**: 20 divisions per Unit-Em.
*   Unit-Base = 2 (1 Unit-Em) [FOCA-4-035]
*   Units per Unit-Base = 20 [FOCA-4-036]
*   `1 / 20 = 0.05 Unit-Em` [FOCA-4-037]

### Units of Direction

In FOCA, units of direction are specified as two integer values: degrees (0 to +359) and minutes (0 to +59). Increasing values indicate increasing clockwise directions. [FOCA-4-038]

**Figure 17. Directions** [FOCA-4-039]

## Character Concepts

This section defines the terms and concepts used to describe individual graphic characters in FOCA. [FOCA-4-040]

### Character Boxes

FOCA uses a concept in which each character shape is defined to be within a **character box**, which is a conceptual rectangle. Boxes can be **bounded** (no extra space, just touching the shape) or **unbounded** (extra space). FOCA typically uses bounded boxes. [FOCA-4-041]

**Figure 18. Bounded Character Box for the Latin Letter ‘h’** [FOCA-4-042]

### Character Baseline

All characters in a font are aligned on an imaginary line called the **character baseline**, which corresponds to the x-axis of the character coordinate system. [FOCA-4-043]

**Figure 19. Character Baseline** [FOCA-4-044]

### Character Reference Point

The **character reference point** corresponds to the origin (0,0) of the character coordinate system. It coincides with the presentation position. [FOCA-4-045]

**Figure 21. Character Reference Point** [FOCA-4-046]

### Character Escapement Point

The **character escapement point** is the point where the next character reference point is usually positioned. [FOCA-4-047]

**Figure 22. Character Escapement Point** [FOCA-4-048]

### A-space

**A-space** is the distance from the character reference point to the least positive character coordinate system x-axis value of the character shape. [FOCA-4-049]

*   **Positive**: Reference point is before the leftmost edge of the box. [FOCA-4-050]
*   **Zero**: Reference point coincides with the leftmost edge. [FOCA-4-051]
*   **Negative**: Implementation technique for kerning (left kerning). [FOCA-4-052]

**Figure 23. A-space** [FOCA-4-053]

### B-space

**B-space** is the distance between the character coordinate system x-axis values of the two extremities of a character shape (the width of a bounded box). [FOCA-4-054]

**Figure 24. B-space** [FOCA-4-055]

### C-space

**C-space** is the distance from the character’s most positive x-axis coordinate value to the character escapement point. [FOCA-4-056]

*   **Positive**: Escapement point is after the right-hand edge. [FOCA-4-057]
*   **Zero**: Escapement point coincides with the right-hand edge. [FOCA-4-058]
*   **Negative**: Implementation technique for kerning (right kerning). [FOCA-4-059]

**Figure 25. C-space** [FOCA-4-060]

### Character Increment

The **character increment** is the algebraic sum of A-space, B-space, and C-space. It is the distance from the character reference point to the character escapement point. [FOCA-4-061]

**Figure 26. Character Increment** [FOCA-4-062]

### Kerning

**Kerning** reduces the spacing between adjacent characters so they overlap. This is done by making A-space or C-space negative. [FOCA-4-063]

**Figure 27. An Example of Kerning** [FOCA-4-064]

**Pair Kerning** allows adjusting the increment between specific pairs of characters. [FOCA-4-065]

### Ascender Height

**Ascender height** is the distance from the character baseline to the top of the character box (most positive y-axis value). [FOCA-4-066]

**Figure 28. Ascender Height** [FOCA-4-067]

### Descender Depth

**Descender depth** is the distance from the character baseline to the bottom of a character box (most negative y-axis value). [FOCA-4-068]

**Figure 29. Descender Depth** [FOCA-4-069]

### Baseline Extent

**Baseline extent** is the space parallel to the character baseline that can be used to place characters (the y-axis height of the box). [FOCA-4-070]

**Figure 30. Baseline Extent: Subscript Character**
**Figure 31. Baseline Extent: Superscript Character**
**Figure 32. Baseline Extent: Character on the Baseline** [FOCA-4-071]

### Baseline Offset

The **baseline offset** is the perpendicular distance from the character baseline to the top of a character box. [FOCA-4-072]

### Slope

The **slope** is the posture (incline) of the main strokes, measured clockwise from the vertical. Upright is usually 0°; italics is usually 17.5°. [FOCA-4-073]

**Figure 33. Slope 0°**
**Figure 34. Slope 17.5°** [FOCA-4-074]

## Font Concepts

This section defines terms that apply to the entire font. [FOCA-4-075]

### Vertical Size

The **vertical size** (also known as body size, point size, em-height) represents the nominal baseline-to-baseline increment, including the designer's recommendation for internal leading. [FOCA-4-076]

**Figure 35. An Illustration of Vertical Size and Internal Leading** [FOCA-4-077]

### Horizontal Font Size

The size of a uniformly-spaced font, measured parallel to the baseline (also known as character width or character pitch). [FOCA-4-078]

### Cap-M Height

The average height of the uppercase characters, usually the height of the uppercase letter M. [FOCA-4-079]

### X-Height

The nominal height above the baseline of lowercase characters (ignoring ascenders), usually the height of 'x'. [FOCA-4-080]

### Internal Leading

The total amount of space above and below text character shapes providing aesthetically pleasing interline spacing.
`Internal Leading = Vertical Font Size - Baseline Extent (for text characters)` [FOCA-4-081]

### External Leading

Additional white space that can be added to interline spacing without degrading appearance. [FOCA-4-082]

**Figure 36. An Illustration of External Leading** [FOCA-4-083]

### Maximum Ascender Height

The maximum height attained by any graphic character in a font from the baseline to the top of its box. [FOCA-4-084]

### Maximum Descender Depth

The maximum depth attained by any graphic character in a font from the baseline to the bottom of its box. [FOCA-4-085]

### Maximum Baseline Extent

The sum of the most positive baseline offset and the most positive descender depth of any character in the font. [FOCA-4-086]

### Superscripts and Subscripts

Designers may provide recommended size and position offsets for superscript and subscript characters. [FOCA-4-087]

### Overscores, Throughscores, and Underscores

*   **Overscore**: Bar over the character. [FOCA-4-088]
*   **Throughscore**: Bar through the character. [FOCA-4-089]
*   **Underscore**: Bar under the character. [FOCA-4-090]

Offsets are specified from the character baseline to the top of the score stroke. [FOCA-4-091]

**Figure 37. Overscore, Throughscore, and Underscore** [FOCA-4-092]

## Non-Latin Language Support

FOCA supports multidirectional text and multi-byte encoding. [FOCA-4-093]

### Character Rotation

The principle distinctive characteristic is top-to-bottom or right-to-left direction. FOCA uses multiple character rotations (0°, 90°, 180°, 270°) in a single font resource. [FOCA-4-094]

*   **Horizontal writing**: 0° and 180° rotations. [FOCA-4-095]
*   **Vertical writing**: 90° and 270° rotations. [FOCA-4-096]

**Figures 38–41. 0°, 90°, 180°, 270° Rotations** [FOCA-4-097]

### Rotated Baseline and Character Boxes

For traditional Kanji, the baseline is rotated 90°, and characters are rotated 270°. [FOCA-4-098]

**Figure 42. Rotating Baseline and Characters** [FOCA-4-099]

### Eastern Writing Systems

In systems like Japanese Kanji, characters can be horizontal (0°) or vertical (270°). [FOCA-4-100]

**Figure 43. Two Rotations of a Kanji Character**
**Figure 44. 0° Character Rotation for Horizontal Writing**
**Figure 45. 270° Character Rotation for Vertical Writing** [FOCA-4-101]

### Middle Eastern Writing Systems

Hebrew is written right-to-left. Hebrew code pages are usually bilingual Latin/Hebrew. FOCA specifies rules for consistent presentation of mixed Latin and Hebrew text. [FOCA-4-102]

## Non-AFP Architecture Support

### ISO 9541-1 Font Architecture

ISO describes metrics in terms of X,Y positions in a glyph coordinate system, while FOCA uses distances/offsets. ISO allows arbitrary positioning points; FOCA fixes them at the origin. They are equivalent but require transformation. [FOCA-4-103]

**Figure 48. FOCA and ISO Coordinate System Relationship** [FOCA-4-104]

#### Global Naming

ISO uses a hierarchical naming tree (e.g., `ISO / ICD / IBM / IBM-CS / FONT / FGID / 12`). FOCA names can be mapped into this structure. [FOCA-4-105]
