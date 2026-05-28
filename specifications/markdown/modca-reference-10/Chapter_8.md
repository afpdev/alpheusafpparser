Chapter 8. MO:DCA Function Sets
This chapter:
• Describes function sets [MODCA-8-001]
• Describes compliance in terms of function sets [MODCA-8-002]
• Registers each function set [MODCA-8-003]
• Defines the extensions made by each registered function set to specific interchange sets [MODCA-8-004]
Function Sets
A MO:DCA function set is a set of constructs that are used to extend the functionality of a MO:DCA
interchange set. The function set is normally not sufficiently pervasive or complex to warrant definition of a new
interchange set. Therefore each function set is defined by its formal extensions to one or more interchange
sets. MO:DCA function sets are identified using the MO:DCA Function Set (X'8F') triplet. This triplet specifies
the identifier of the function set using the 2–byte FctSetID parameter.
Since MO:DCA interchange compliance is based on interchange sets, when an interchange set is extended
with one or more function sets, compliance is based on the definition of the interchange set plus the function
set(s). A print file or document that claims compliance with an interchange set plus a function set must specify
this compliance as follows:
• The MO:DCA Interchange Set (X'18') triplet on the BPF/BDT must specify the interchange set and indicate [MODCA-8-005]
that the interchange set is extended with one or more function sets, e.g. ISid=X'0D80': IS/3 + function set(s)
• The MO:DCA Function Set (X'8F') triplet on the BPF/BDT must specify the function set ID using the FctSetID [MODCA-8-006]
parameter, e.g. FctSetID=X'0001': MO:DCA GA.
This edition of the Mixed Object Document Content Architecture Reference contains one function set
definition:
• MO:DCA GA (Graphic Arts) [MODCA-8-007]
### MO:DCA Function Set X'0001': MO:DCA GA (Graphic Arts)
The FctSetID parameter in the MO:DCA Function Set (X'8F') triplet is set to X'0001' for this function set.
MO:DCA GA and IS/3
The following defines the extensions made by the MO:DCA GA function set to IS/3. Compliance with IS/3 +
MO:DCA GA requires compliance with the IS/3 definition and compliance with these extensions.
Table 38. IS/3 + MO:DCA GA Containers - Presentation Objects. This table contains rows that extend Table 28.
Component
ID
Object Type Encoded Object-type OID
25 PDF Single-page Object X'06072B120004010119' [MODCA-8-008]
49 PDF Single-page Object with Transparency X'06072B120004010131' [MODCA-8-009]
63 PDF Multiple Page File X'06072B12000401013F' [MODCA-8-010]
64 PDF Multiple Page - with Transparency - File X'06072B120004010140' [MODCA-8-011]


Table 39. IS/3 + MO:DCA GA IOB and DOR RAT Presentation Object Containers. This table contains rows that extend
Table 30.
Component
ID
Object Type Encoded Object-type OID
25 PDF Single-page Object X'06072B120004010119' [MODCA-8-012]
49 PDF Single-page Object with Transparency X'06072B120004010131' [MODCA-8-013]
63 PDF Multiple Page File X'06072B12000401013F' [MODCA-8-014]
64 PDF Multiple Page - with Transparency - File X'06072B120004010140' [MODCA-8-015]
Table 40. IS/3 + MO:DCA GA Data Objects and Secondary Resources. This table contains rows that extend Table 31 on
page 499.
Data Object Resource Secondary Resource Internal Resource Identifier
PDF Single-Page Object or Multi-page
File (with or without transparency)
PDF Resource Object
Color Management Resource
Identifier with syntax defined
by PDF
None
Table 41. IS/3 + MO:DCA GA Begin Structured Fields. This table contains rows that extend Table 33.
IS/3 Begin Structured Fields
Structured Field Name
Structured
Field ID
IS1/3 triplets (listed by ID) and their allowed occurrence; differences
from IS/3 noted
Begin Document (BDT) X'D3A8A8' X'18' 1 occurrence; must specify ISID = X'0D80' - MO:DCA
IS/3 + function set
X'8F' 1 occurrence; must specify FctSetID = X'0001' -
MO:DCA GA function set
Begin Print File (BPF) X'D3A8A5' X'18' 1 occurrence; must specify ISID = X'0D80' - MO:DCA
IS/3 + function set
X'8F' 1 occurrence; must specify FctSetID = X'0001' -
MO:DCA GA function set
Table 42. IS/3 + MO:DCA GA Structured Fields with Triplets. This table contains rows that extend Table 36.
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from IS/3 noted
Container Data Descriptor
(CDD)
X'D3A692' X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the container
contains one of the multi-page TIFF object types
supported in IS/3 (see T able 28) or multi-
page PDF object types supported in MO:DCA GA (see
T able 40); otherwise should not be
specified.
X'9C' 0 or 1 occurrences if the container contains one of the
PDF object types listed in T able 40;
otherwise should not be specified.
MO:DCA Function Sets


Table 42 IS/3 + MO:DCA GA Structured Fields with Triplets (cont'd.)
IS/3 Structured Fields with Triplets
Structured Field Name
Structured
Field ID
IS/3 triplets (listed by ID) and their allowed occurrence; differences
from IS/3 noted
Include Object (IOB) X'D3AFC3' X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the container
contains one of the multi-page TIFF object types
supported in IS/3 (see T able 28) or multi-
page PDF object types supported in MO:DCA GA (see
T able 40); otherwise should not be
specified.
X'9C' 0 or 1 occurrences if the container contains one of the
PDF object types listed in T able 40;
otherwise should not be specified.
Preprocess Presentation
Object (PPO)
X'D3ADC3' X'5A' 0 or 1 occurrences with ObjTpe=X'AF' if the container
contains one of the multi-page TIFF object types
supported in IS/3 (see T able 28) or multi-
page PDF object types supported in MO:DCA GA (see
T able 40); otherwise should not be
specified.
X'9C' 0 or 1 occurrences if the container contains one of the
PDF object types listed in T able 40;
otherwise should not be specified.
MO:DCA Function Sets




