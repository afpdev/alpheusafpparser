Appendix B. MO:DCA Environment
This appendix describes how bar code objects may be included within a MO:DCA document for the purpose of
interchanging the bar code object between a generating node and one or more receiving nodes. Refer to
Mixed Object Document Content Architecture (MO:DCA) Reference for a full description of the MO:DCA
architecture.
The description of MO:DCA structured fields is included in this appendix solely for setting the context of their
use by bar codes.
Bar Codes in MO:DCA Documents
The MO:DCA bar code object presents one or more bar code symbols of the same type on a page or overlay.
Bar code symbols are developed within an abstract bar code presentation space before they are mapped to
the MO:DCA bar code object area.
The MO:DCA Bar Code Data Descriptor (BDD) and Bar Code Data (BDA) structured fields are used to carry
bar code object information. These structured fields are described in “Bar Code Data Object Structured Fields”
.
A MO:DCA bar code object has the following basic structure:
Begin Bar Code Object structured field
Object Environment Group (contains the BCOCA BSD structure and other information)
Zero or more Bar Code Data structured fields (contains the BCOCA BSA structure); there is one Bar Code
Data structured field per bar code symbol
End Bar Code Object structured field [BCOCA-B-001]


Bar Code Data Object Structured Fields
The following sections describe two structured fields: Bar Code Data Descriptor (BDD) and Bar Code Data
(BDA).
Bar Code Data Descriptor (BDD)
The BDD specifies the size of the bar code presentation space, the type of bar code to be generated, and the
parameters used to generate the bar code symbols. [BCOCA-B-002]
### Table 37. MO:DCA Bar Code Data Descriptor (BDD)

| Item | Value |
| :--- | :--- |
| **Structured Field Introducer** [BCOCA-B-003]| |
| SF Length [BCOCA-B-004]| |
| SF Identifier | X'D3A6EB' [BCOCA-B-005]|
| Flags [BCOCA-B-006]| |
| Reserved | (2 bytes); should be X'0000' [BCOCA-B-007]|
| **Structured Field Data** [BCOCA-B-008]| |
| Data | Bar Code Symbol Descriptor followed by zero or one Color Specification (X'4E') triplets [BCOCA-B-009]|
The data portion of the BDD structured field is defined in “Bar Code Symbol Descriptor (BSD)” .
When a Color Specification (X'4E') triplet is present in the BDD, this triplet overrides the color value specified in
BSD bytes 15-16.
Note: Support for the Color Specification (X'4E') triplet in the MO:DCA BDD structured field is part of the BCD2
subset of BCOCA.
Application Note: In AFP environments, some applications use reserved bytes 6–7 of the Structured Field
Introducer to specify a sequence number for the structured field. This is an unarchitected use of these
bytes and should be avoided.
Bar Code Data (BDA)
The BDA structured field contains parameters to position a single bar code symbol within a bar code
presentation space, parameters to specify special functions for 2D bar codes, flags to specify attributes
specific to the symbol, and the data to be encoded. The data is encoded according to the parameters specified
in the Bar Code Data Descriptor (BDD) structured field.
The format of the BDA structured field follows: [BCOCA-B-010]
### Table 38. MO:DCA Bar Code Data (BDA)

| Item | Value |
| :--- | :--- |
| **Structured Field Introducer** [BCOCA-B-011]| |
| SF Length [BCOCA-B-012]| |
| SF Identifier | X'D3EEEB' [BCOCA-B-013]|
| Flags [BCOCA-B-014]| |
| Reserved | (2 bytes); should be X'0000' [BCOCA-B-015]|
| **Structured Field Data** [BCOCA-B-016]| |
| Data | Bar Code Symbol Data [BCOCA-B-017]|
The data portion of the BDA structured field is described in “Bar Code Symbol Data (BSA)” .
Application Note: In AFP environments, some applications use reserved bytes 6–7 of the Structured Field
Introducer to specify a sequence number for the structured field. This is an unarchitected use of these
bytes and should be avoided.
MO:DCA Environment


Copyright © AFP Consortium 1991, 2025 177
