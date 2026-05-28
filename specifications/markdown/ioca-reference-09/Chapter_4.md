# Chapter 4. Formats and Codes
This chapter describes the formats of the IOCA self-defining fields.
• The formats of the IOCA self-defining fields [IOCA-4-001]
• The code points used by IOCA [IOCA-4-002]
Formats An IOCA Image Segment is a set of self-defining fields. Each self-defining field is in either long format or extended format. Both formats start with a code for the self-defining field, and the length of the parameters that follow.
Long Format Byte 0 L Parameters Length C 1 2 - n where:
C is a one-byte code for the self-defining field.
L is the length of the following parameters, excluding L itself.
Extended Format Byte 0 L L Parameters Length C C 1 4 - n2 3 where:
CC is a two-byte code for the self-defining field. The first byte is always X'FE'.
This format is used by all of the following:
• Image Data (X'FE92') [IOCA-4-003]
• Band Image Data (X'FE9C') [IOCA-4-004]
• Include Tile parameter (X'FEB8') [IOCA-4-005]
• Tile TOC parameter (X'FEBB') [IOCA-4-006]
• Image Subsampling parameter (X'FECE') [IOCA-4-007]
Other values for the second byte of CC are reserved.
LL is the length of the parameters, excluding LL itself. [IOCA-4-008]


Code Points Table 3 lists the codes used by IOCA, the names of the associated elements, and the formats used. [IOCA-4-009]

Table 3. IOCA Code Points [IOCA-4-010]

| Code | Name | Format |
| :--- | :--- | :--- |
| X'70' | Begin Segment | Long format [IOCA-4-011]|
| X'71' | End Segment | Long format [IOCA-4-012]|
| X'8C' | Begin Tile | Long format [IOCA-4-013]|
| X'8D' | End Tile | Long format [IOCA-4-014]|
| X'8E' | Begin Transparency Mask | Long format [IOCA-4-015]|
| X'8F' | End Transparency Mask | Long format [IOCA-4-016]|
| X'91' | Begin Image Content | Long format [IOCA-4-017]|
| X'93' | End Image Content | Long format [IOCA-4-018]|
| X'94' | Image Size | Long format [IOCA-4-019]|
| X'95' | Image Encoding | Long format [IOCA-4-020]|
| X'96' | IDE Size | Long format [IOCA-4-021]|
| X'97' | Image LUT-ID (Retired) | Long format [IOCA-4-022]|
| X'98' | Band Image | Long format [IOCA-4-023]|
| X'9B' | IDE Structure | Long format [IOCA-4-024]|
| X'9F' | External Algorithm Specification | Long format [IOCA-4-025]|
| X'B5' | Tile Position | Long format [IOCA-4-026]|
| X'B6' | Tile Size | Long format [IOCA-4-027]|
| X'B7' | Tile Set Color | Long format [IOCA-4-028]|
| X'F4' | Set Extended Bilevel Image Color | Long format [IOCA-4-029]|
| X'F6' | Set Bilevel Image Color | Long format [IOCA-4-030]|
| X'F7' | IOCA Function Set Identification | Long format [IOCA-4-031]|
| X'FE92' | Image Data | Extended format [IOCA-4-032]|
| X'FE9C' | Band Image Data | Extended format [IOCA-4-033]|
| X'FEB3' | nColor Names | Extended format [IOCA-4-034]|
| X'FEB8' | Include Tile | Extended format [IOCA-4-035]|
| X'FEBB' | Tile TOC | Extended format [IOCA-4-036]|
| X'FECE' | Image Subsampling | Extended format [IOCA-4-037]|


