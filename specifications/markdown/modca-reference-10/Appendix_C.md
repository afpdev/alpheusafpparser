# Appendix C. MO:DCA Migration Functions
This appendix:
• Describes obsolete structured fields and triplets that may occur in a MO:DCA data stream [MODCA-C-001]
• Describes retired structured fields and triplets that may occur in a MO:DCA data stream [MODCA-C-002]
• Describes coexistence functions that may occur in a MO:DCA data stream [MODCA-C-003]
The objective in defining obsolete, retired, and coexistence functions is twofold:
• T o allow existing MO:DCA applications to run unchanged [MODCA-C-004]
• T o provide a clear growth direction for future MO:DCA applications [MODCA-C-005]
Migration Functions
The migration functions are divided into three different categories:
• Obsolete functions. These are objects, structured fields, triplets, and parameters that will be accepted but [MODCA-C-006]
ignored. New products must not generate these functions.
• Retired functions. Retired functions are objects, structured fields, triplets, and parameters whose use has [MODCA-C-007]
been retired except for specific products. Only these specific products may use these functions. Other
products should not use these functions, that is, generators should not generate these functions and
receivers may ignore them.
• Coexistence functions. These are objects, structured fields, triplets, and parameters whose function has [MODCA-C-008]
been enhanced or superseded by newer functions. In this case, the old and new functions can coexist. New
generators must generate the new functions. New receivers must process the new functions, but may also
continue to process the old functions. [MODCA-C-009]


Obsolete Functions
Obsolete functions are objects, structured fields, triplets, and parameters that will be accepted but ignored.
New products must not generate these functions.
Obsolete Structured Fields
The following four structured fields are obsolete in the current data stream, but are still allowed to be present
as constant data. AFP servers recognize these fields and ignore them:
• Composed-T ext Control (CTC) [MODCA-C-010]
• Begin Form Environment Group (BFG) [MODCA-C-011]
• End Form Environment Group (EFG) [MODCA-C-012]
• Form Environment Group Descriptor (FGD) [MODCA-C-013]
The CTC can appear as a constant in the Active Environment Group of a page. The BFG, EFG, and FGD can
appear optionally in the Medium Map object of a Form Map.
New applications must not generate these structured fields.
Composed Text Control (CTC)
CTC (X'D3A79B') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3A79B' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-014]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-015]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-016]|
| 0–9 | ConData | Constant | | data | M | X'06' CTC Semantics ConData Constant data. Must be set to X'0000 0000 0000 0000 2D00'. Begin Form Environment Group (BFG) BFG (X'D3A8C5') Syntax Structured Field Introducer SF Length (2B) ID = X'D3A8C5' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-017]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-018]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-019]|
| 0–7 CHAR FEGName Name of the Form Environment | | | | | | Group O X'02' Obsolete Functions [MODCA-C-020]|


BFG Semantics
FEGName Is the name of the form environment group.
End Form Environment Group (EFG)
EFG (X'D3A9C5') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3A9C5' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-021]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-022]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-023]|
| 0–7 CHAR FEGName Name of the Form Environment | | | | | | Group O X'02' EFG Semantics FEGName Is the name of the form environment group being terminated. If a name is specified, it must match the name in the most recent Begin Form Environment Group structured field in the Form Map. If the first two bytes in FEGName contain the value X'FFFF', the name matches any name specified on the Begin Form Environment Group structured field that initiated the current definition. Form Environment Group Descriptor (FGD) FGD (X'D3A6C5') Syntax Structured Field Introducer SF Length (2B) ID = X'D3A6C5' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-024]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-025]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-026]|
| 0–3 | ConData | Constant | | data | M | X'06' FGD Semantics Constant data Must be set to X'0001 00FF'. Obsolete Functions [MODCA-C-027]|


Obsolete Structured Field Names
The following structured fields are still in use, but have been renamed:
• Composed T ext Data (CTX) [MODCA-C-028]
• Composed T ext Descriptor (CTD) [MODCA-C-029]
• Begin Composed T ext (BCT) [MODCA-C-030]
• End Composed T ext (ECT) [MODCA-C-031]
Composed Text Data (CTX) Structured Field (X'D3EE9B')
This structured field has been renamed Presentation T ext Data (PTX).
Composed Text Descriptor (CTD) Structured Field (X'D3A69B')
This structured field has been renamed Presentation T ext Data Descriptor Format 1 (PTD-1).
Begin Composed Text (BCT) Structured Field (X'D3A89B')
This structured field has been renamed Begin Presentation T ext (BPT).
End Composed Text (ECT) Structured Field (X'D3A99B')
This structured field has been renamed End Presentation T ext (EPT).
Obsolete Functions


Retired Functions
Retired functions. Retired functions are objects, structured fields, triplets, and parameters whose use has been
retired except for specific products. Only these specific products may use these functions. Other products
should not use these functions, that is, generators should not generate these functions and receivers may
ignore them.
Retired Structured Fields
The following structured fields were previously retired but are now valid MO:DCA structured fields:
• Begin Resource (BR), see “Begin Resource (BRS)”. [MODCA-C-032]
• End Resource (ER), see “End Resource (ERS)”. [MODCA-C-033]
Retired Triplets
The following triplets have been retired:
• MDD Two-up Triplet X'10' [MODCA-C-034]
• T ext Orientation Triplet X'1D' [MODCA-C-035]
• Object Function Set Specification Triplet X'21' [MODCA-C-036]
• Line Data Object Position Migration Triplet X'27' [MODCA-C-037]
• Page Overlay Conditional Processing Triplet X'46' [MODCA-C-038]
• Resource Usage Attribute Triplet X'47' [MODCA-C-039]
• Object Checksum Triplet X'63' [MODCA-C-040]
• Object Origin Identifier Triplet X'64' [MODCA-C-041]
• IMM Insertion Triplet X'73' [MODCA-C-042]
MDD Two-up Triplet X'10'
Provides two-up functionality specific to Océ implementations. The use of this triplet is restricted to the MDD
structured field for the following products:
• Océ PRISMAproduction Server [MODCA-C-043]
• Océ printers driven by the Océ PRISMAproduction Server that support two-up printing using this control [MODCA-C-044]
MDD Two-up Triplet X'10' Syntax [MODCA-C-045]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-046]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-047]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-048]|
| 1 | CODE | Tid | | X'10' Identifies the Océ Two-up triplet | M | X'00' [MODCA-C-049]|
| 2 CODE Oce2up X'00', X'01', | | | | | | X'02', X'05' Specifies the Océ Two-up method to be used: X'00' No Two-up X'01' Two-up left/right X'02' Two-up identical copies X'05' Two-up right/left M X'06' Retired Functions [MODCA-C-050]|


MDD Two-up Triplet X'10' Semantics
Tlength Contains the length of the triplet.
Tid Identifies the Océ Two-up triplet.
Oce2up Specifies the Océ Two-up method to be used:
Value Two-up Method
X'00' No Two-up
X'01' Two-up left/right
X'02' Two-up identical copies
X'05' Two-up right/left
All others Reserved
Structured Field Using MDD Two-up Triplet X'10'
• “Medium Descriptor (MDD)” [MODCA-C-051]
Text Orientation Triplet X'1D'
The use of this triplet is restricted to the MCF-2 structured field for IBM 3800 printer compatibility for the
following products:
• PSF/MVS [MODCA-C-052]
• PSF/VM [MODCA-C-053]
• PSF/VSE [MODCA-C-054]
• PSF/400 [MODCA-C-055]
• PSF/2
• Infoprint Manager (IPM) [MODCA-C-056]
• IBM 3800 printer [MODCA-C-057]
• Applications that generate MCF-2s in documents to be printed on the IBM 3800 printer [MODCA-C-058]
The T ext Orientation triplet is used to specify the text orientation for a coded font.
When the MCF-2 structured field is used to reference different sections of the same double-byte font, a T ext
Orientation (X'1D') triplet may be specified in any of the repeating groups associated with the font and need
only be specified in one of the repeating groups. However, if specified in more than one of the associated
repeating groups, the value of all T ext Orientation (X'1D') triplets must be identical.
Triplet X'1D' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-059]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-060]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-061]|
| 1 CODE Tid X'1D' Identifies the T ext Orientation | | | | | | triplet M X'00' Retired Functions [MODCA-C-062]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-063]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-064]|
| 2–3 CODE IAxis X'0000', X'2D00', | | | | | | X'5A00', X'8700' Specifies the orientation of the Inline axis: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' [MODCA-C-065]|
| 4–5 CODE BAxis X'0000', X'2D00', | | | | | | X'5A00', X'8700' Specifies the orientation of the Baseline axis: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' Triplet X'1D' Semantics Tlength Contains the length of the triplet. Tid Identifies the T ext Orientation triplet. IAxis Specifies the orientation of the I-axis with respect to the X axis of the page or overlay. Valid values are the following: Value I-Axis Orientation X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees All others Reserved BAxis Specifies the orientation of the B-axis with respect to the X axis of the page or overlay. Valid values are the following: Value B-Axis Orientation X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees All others Reserved Structured Field Using Triplet X'1D' • “Map Coded Font (MCF) Format 2” Object Function Set Specification Triplet X'21' The use of this triplet is restricted to the BDT structured field in the following products: • Pre-year 2012 AFP applications. The Object Function Set Specification triplet is used to specify the Object Content Architecture (OCA) level for objects in a MO:DCA document. Architecture Note: A similar triplet, the Resource Object Type triplet, that unfortunately also uses triplet ID X'21', is used on the BRS structured field; see “Resource Object Type Triplet X'21'”. Retired Functions [MODCA-C-066]|


Triplet X'21' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-067]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-068]|
| 0 UBIN Tlength 8–254 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-069]|
| 1 CODE Tid X'21' Identifies the Object Function Set | | | | | | Specification triplet M X'00' [MODCA-C-070]|
| 2 CODE ObjType X'02'–X'03', | | | | | | X'05'–X'06' Specifies the OCA: X'02' Presentation T ext X'03' Graphics X'05' Bar Code X'06' Image M X'06' [MODCA-C-071]|
| 3 CODE ArchVrsn X'00' Specifies the architecture level of | | | | | | the OCA M X'06' [MODCA-C-072]|
| 4–5 CODE DCAFnSet X'8000' Specifies the MO:DCA function | | | | | | set identifier M X'06' [MODCA-C-073]|
| 6–7 CODE OCAFnSet X'0000', X'4000', | | | | | | X'8000' Identifies the OCA function set: X'0000' PTOCA PT1 or BCOCA BCD1 X'4000' GOCA DR/2V0 (GRS2) or PTOCA PT2 X'8000' IOCA FS10 M X'06' [MODCA-C-074]|
| 8–n | Reserved; | not | | checked | O | X'00' Triplet X'21' Semantics Tlength Contains the length of the triplet. Tid Identifies the Object Function Set Specification triplet. ObjType Specifies the object for which a function set is being defined. The codes for the objects are as follows: Value Description X'02' Presentation T ext (PTOCA) X'03' Graphics (GOCA) X'05' Bar Code (BCOCA) X'06' Image (IOCA) All others Reserved ArchVrsn Specifies the architecture level of the OCA. DCAFnSet Defines the function set for the group of MO:DCA constructs identified by the ObjType parameter. OCAFnSet Specifies the function set of the OCA defined by the ObjType parameter. The presence of this parameter containing the value X'0000' indicates that at least one object from the base function set is present in the data stream. OCAFnSet values have the following meanings: Value Description X'0000' Presentation T ext data - PTOCA PT1 level, or Bar Code data - BCOCA BCD1 level X'4000' Graphics data - GOCA DR/2V0 (GRS2) level, or Presentation T ext data - PTOCA PT2 level X'8000' Image data - IOCA FS10 level All others Reserved Retired Functions [MODCA-C-075]|


Structured Field Using Triplet X'21'
• “Begin Document (BDT)” [MODCA-C-076]
Line Data Object Position Migration Triplet X'27'
The use of this triplet is restricted to the BBC, BGR, BII, BIM, BPT , and IPS structured fields for the migration of
line-data containing bar code objects, graphic objects, image objects, text objects with OEG, and page
segments to MO:DCA document format. This triplet may be specified on these structured fields only for objects
that occur directly in a page. The triplet may not be specified on objects in a resource group or in a resource
library; if it is specified, it is ignored.
Triplet X'27' Syntax
Use of this triplet is restricted to the following products:
• ACIF
• PSF/MVS [MODCA-C-077]
• PSF/VM [MODCA-C-078]
• PSF/VSE [MODCA-C-079]
• PSF/2
• Infoprint Manager (IPM) [MODCA-C-080]
• PSF/400 [MODCA-C-081]
• AFP Workbench [MODCA-C-082]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-083]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-084]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-085]|
| 1 CODE Tid X'27' Identifies the Line Data Object | | | | | | Position Migration triplet M X'00' [MODCA-C-086]|
| 2 CODE T empOrient X'00'–X'03' Location and orientation of | | | | | | coordinate system for object position and rotation: X'00' Standard page origin, 0° rotation X'01' Lower left origin, 270° rotation X'02' Lower right origin, 180° rotation X'03' Upper right origin, 90° rotation M X'06' Triplet X'27' Semantics Tlength Contains the length of the triplet. Tid Identifies the Line Data Object Position Migration triplet. TempOrient Specifies a temporary page coordinate system (X,Y) that matches the text coordinate (I,B) system that was defined when the objects that specify this triplet were included in line data. The origin of the temporary coordinate system is specified as one of the four corners of the page presentation space. The orientation of the temporary coordinate system is specified as a rotation of the X axis with respect to the page presentation space X p axis. The temporary coordinate system uses the same units of measure as the page coordinate system. The temporary coordinate system is used as follows: Retired Functions [MODCA-C-087]|


• For objects in a page segment, the X'27' triplet may be specified on the IPS and has the [MODCA-C-088]
following effect on object offset and orientation:
– IM image objects. The image origin offset from the page segment origin is measured
using the temporary (X,Y) coordinate system. If the image is celled, cell offsets from the
image origin are also measured using the temporary (X,Y) coordinate system. The image
rotation is measured using the page (X
p,Yp) coordinate system.
– OCA objects (bar code, graphics, image). If OBP byte 23 = X'00', the object area offset
from the page segment origin and the object area rotation are measured using the
temporary (X,Y) coordinate system. If OBP byte 23 = X'01', the object area offset from the
page segment origin and the object area rotation are measured using the page (X
p,Yp)
coordinate system.
If specified on the IPS, the X'27' triplet overrides any X'27' triplet that is specified on the
Begin structured field of an object in the page segment.
• For standalone objects, the X'27' triplet may be specified on the object Begin structured field [MODCA-C-089]
and has the following effect on object offset and orientation:
– IM image objects. The image origin offset is measured from the temporary (X,Y)
coordinate system origin (X=0,Y=0) using the temporary (X,Y) coordinate system. If the
image is celled, cell offsets from the image origin are also measured using the temporary
(X,Y) coordinate system. The image rotation is measured using the page (X
p,Yp)
coordinate system.
– OCA objects (bar code, graphics, image, text with OEG). If OBP byte 23 = X'00', the
object area offset is measured from the temporary (X,Y) coordinate system origin (X=0,Y=
0) using the temporary (X,Y) coordinate system. The object area rotation is also measured
using the temporary (X,Y) coordinate system. If OBP byte 23 = X'01', the object area offset
is measured from the page origin (X
p=0,Yp=0) using the page (X p,Yp) coordinate system.
Object area rotation is also measured using the page (X p,Yp) coordinate system.
The following values are defined:
Value Description
X'00' The temporary (X,Y) coordinate system is the page (X p,Yp) coordinate
system. This is the standard MO:DCA page coordinate system that is used for
object positioning and rotation. This coordinate system is used if this triplet is
omitted.
X'01' The temporary coordinate system origin is the lower-left corner of the page
presentation space (X
p=0, Yp=Yextent). Its axes are rotated 270° from the axes
of the page presentation space, so that the X axis increases from bottom to
top and the Y axis increases from left to right.
X'02' The temporary coordinate system origin is the lower-right corner of the page
presentation space (X
p=Xextent, Yp=Yextent). Its axes are rotated 180° from the
axes of the page presentation space, so that the X axis increases from right to
left and the Y axis increases from bottom to top.
X'03' The temporary coordinate system origin is the upper-right corner of the page
presentation space (X
p=Xextent, Yp=0). Its axes are rotated 90° from the axes
of the page presentation space, so that the X axis increases from top to
bottom and the Y axis increases from right to left.
T able 45 provides a comparison of object position and rotation in line data and object position and
rotation in MO:DCA data transformed from line data.
Retired Functions


Table 45. Position and Rotation of Objects in Line Data and MO:DCA Data
Objects in Line Data Objects with X'27' Triplet in MO:DCA Data Transformed
from Line Data
Page Segment Object
Page Segment Origin
(XpsOset,YpsOset) in IPS specify an offset from the
current text coordinate system origin (I=0,B=0). The offset
is measured using the current text (I,B) coordinate system.
(XpsOset,YpsOset) in IPS specify an offset from the page
origin (X
p=0,Yp=0). The offset is measured using the page
(Xp,Yp) coordinate system. The offset was adjusted to
include the LND position.
IM Image Object in Page Segment
IM Image Object Origin
(XoaOset,YoaOset) in IOC specify an offset from the page
segment origin. The offset is measured using the current
text (I,B) coordinate system.
(XoaOset,YoaOset) in IOC specify an offset from the page
segment origin. The offset is measured using the
temporary (X,Y) coordinate system.
IM Image Object Rotation
(XoaOrent,YoaOrent) in IOC specify a rotation that is
measured with respect to the page (X p,Yp) coordinate
system Xp-axis.
(XoaOrent,YoaOrent) in IOC specify a rotation that is
measured with respect to the page (X
p,Yp) coordinate
system Xp-axis.
IM Image Cell Origin
(XCOset,YCOset) in ICP specify an offset from the image
object origin. The offset is measured using the current text
(I,B) coordinate system.
(XCOset,YCOset) in ICP specify an offset from the image
object origin. The offset is measured using the temporary
(X,Y) coordinate system.
OCA Object in Page Segment
OCA Object Origin—Byte 23=X'00'
(XoaOset,YoaOset) in OBP specify an offset from the page
segment origin. The offset is measured using the current
text (I,B) coordinate system.
(XoaOset,YoaOset) in OBP specify an offset from the page
segment origin. The offset is measured using the
temporary (X,Y) coordinate system.
OCA Object Origin—Byte 23=X'01'
(XoaOset,YoaOset) in OBP specify an offset from the page
origin (X
p=0,Yp=0). The offset is measured using the page
(Xp,Yp) coordinate system.
(XoaOset,YoaOset) in OBP specify an offset from the page
origin (Xp=0,Yp=0). The offset is measured using the page
(Xp,Yp) coordinate system.
OCA Object Rotation—Byte 23=X'00'
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the current text (I,B) coordinate
system I-axis.
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the temporary (X,Y) coordinate
system X-axis.
OCA Object Rotation—Byte 23=X'01'
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the page (X p,Yp) coordinate
system Xp-axis.
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the page (X p,Yp) coordinate
system Xp-axis.
Stand-alone IM Image Object
IM Image Object Origin
(XoaOset,YoaOset) in IOC specify an offset from the
current LND position. The offset is measured using the
current text (I,B) coordinate system.
(XoaOset,YoaOset) in IOC specify an offset from the
temporary coordinate system (X=0,Y=0) origin. The offset
is measured using the temporary (X,Y) coordinate system.
The offset was adjusted to include the LND position.
Retired Functions


Table 45 Position and Rotation of Objects in Line Data and MO:DCA Data (cont'd.)
Objects in Line Data Objects with X'27' Triplet in MO:DCA Data Transformed
from Line Data
IM Image Object Rotation
(XoaOrent,YoaOrent) in IOC specify a rotation that is
measured with respect to the page (X
p,Yp) coordinate
system Xp-axis.
(XoaOrent,YoaOrent) in IOC specify a rotation that is
measured with respect to the page (X
p,Yp) coordinate
system Xp-axis.
IM Image Cell Origin
(XCOset,YCOset) in ICP specify an offset from the image
object origin. The offset is measured using the current text
(I,B) coordinate system.
(XCOset,YCOset) in ICP specify an offset from the image
object origin. The offset is measured using the temporary
(X,Y) coordinate system.
Stand-alone OCA Object
OCA Object Origin—OBP Byte 23= X'00'
(XoaOset,YoaOset) in OBP specify an offset from current
LND position. The offset is measured using the current text
(I,B) coordinate system.
(XoaOset,YoaOset) in OBP specify an offset from the
temporary coordinate system (X=0,Y=0) origin. The offset
is measured using the temporary (X,Y) coordinate system.
The offset was adjusted to include the LND position.
OCA Object Origin—OBP Byte 23= X'01'
(XoaOset,YoaOset) in OBP specify an offset from the page
origin (Xp=0,Yp=0). The offset is measured using the page
(Xp,Yp) coordinate system.
(XoaOset,YoaOset) in OBP specify an offset from the page
origin (Xp=0,Yp=0). The offset is measured using the page
(Xp,Yp) coordinate system.
OCA Object Rotation—OBP Byte 23= X'00'
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the current text (I,B) coordinate
system I-axis.
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the temporary (X,Y) coordinate
system X-axis.
OCA Object Rotation—OBP Byte 23= X'01'
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the page (X p,Yp) coordinate
system Xp-axis.
(XoaOrent,YoaOrent) in OBP specify a rotation that is
measured with respect to the page (X
p,Yp) coordinate
system Xp-axis.
Structured Fields Using Triplet X'27'
• “Begin Bar Code Object (BBC)” [MODCA-C-090]
• “Begin Graphics Object (BGR)” [MODCA-C-091]
• “Begin IM Image Object (BII)” [MODCA-C-092]
• “Begin Image Object (BIM)” [MODCA-C-093]
• “Begin Presentation T ext Object (BPT)” [MODCA-C-094]
• “Include Page Segment (IPS)” [MODCA-C-095]
Page Overlay Conditional Processing Triplet X'46'
The use of this triplet is restricted to products that generate or process the retired MO:DCA interchange set
MO:DCA IS/2.
The Page Overlay Conditional Processing triplet is used to identify the intended utilization of a page overlay as
produced by a generator. This triplet can also be used to define an overlay level that determines whether the
overlay is to be processed.
Retired Functions


Triplet X'46' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-096]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-097]|
| 0 UBIN Tlength 3–4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-098]|
| 1 CODE Tid X'46' Identifies the Page Overlay | | | | | | Conditional Processing triplet M X'00' [MODCA-C-099]|
| 2 CODE PgOvType X'00'–X'03' Specifies the page overlay type: | | | | | | X'00' Type 0: Normal X'01' Type 1: Annotation X'02' Type 2: Redaction X'03' Type 3: Highlight M X'06' [MODCA-C-100]|
| 3 | CODE | Level | | X'01'–X'FE' The level of the overlay | O | X'02' Triplet X'46' Semantics Tlength Contains the length of the triplet. Tid Identifies the Page Overlay Conditional Processing triplet. PgOvType Specifies the intended use of the overlay. If this parameter contains a value that is not supported by the receiver, the overlay is not processed. The page overlay types are defined as follows: Type Description Type 0 Normal page overlay. Type 1 Annotation overlay. Type 1 indicates that the page overlay is an annotation overlay used to indicate changes or annotations to the contents of the page to which it applies. Type 2 Redaction overlay. Type 2 indicates that the page overlay is a redaction overlay used to mask or hide all or a portion of the page to which it applies. Type 3 Highlight overlay. Type 3 indicates that the page overlay is a highlight overlay used to highlight all or a portion of the page to which it applies. Level Specifies the processing level of the overlay. An overlay level is used to determine whether the overlay is to be processed by a particular application. Value Description X'01'–X'FE' Level All others Reserved Note: Should the optional Level value be omitted, the architected default is X'01'. Overlay Type Conditional Processing Conditional processing is applied to the overlay types as follows: Type Conditional Processing Description Type 0 No conditional processing is applied. If a level value was specified, it is ignored, and the page overlay is processed normally. Type 1 The overlay level is matched against one contained within the application, and if it is equal to or lower than the application's level it is processed. Should the level be higher than the level Retired Functions [MODCA-C-101]|


contained in the application, or if the application does not contain a level, overlay processing is
not performed.
Type 2 The overlay level is matched against one contained within the application, and if it is higher
than the application's level, or if the application does not contain a level, it is processed. If the
level be equal to or lower than the level contained in the application, overlay processing is not
performed.
Type 3 If the receiver is enabled to present highlighted areas, the overlay is processed. If the receiver
is not enabled to present highlighted areas, the overlay is not processed. The enablement is
achieved external to the data stream. The overlay level is not used with highlight overlays. If a
level is specified, it is ignored.
Architecture Note: In general, the highlighting effect is achieved by including a colored
highlight overlay on a page using a specified set of mixing rules. When a presentation
device does not support the functions necessary to present the specified highlighting,
as in the case of a bilevel device, it may choose to default to a highlighting
implementation where the area defined by the highlight overlay is presented in reverse
video.
Note: If this triplet is omitted, the architected default value for PgOvType is X'00', Type 0, which indicates that
the page overlay is always processed.
Structured Fields Using Triplet X'46'
• “Include Page Overlay (IPO)” [MODCA-C-102]
• “Map Page Overlay (MPO)” [MODCA-C-103]
Resource Usage Attribute Triplet X'47'
The use of this triplet is restricted to products that generate or process the retired MO:DCA interchange set
MO:DCA IS/2.
The Resource Usage Attribute triplet can be used for resource management. It is used with the Include Page
Overlay and Map Page Overlay structured fields to identify the approximate frequency with which an
associated page overlay is processed. This is indicated by assigning either a low or high value to this triplet.
The Resource Usage Attribute triplet has no processing semantics associated with it.
Triplet X'47' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-104]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-105]|
| 0 UBIN Tlength 3 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-106]|
| 1 CODE Tid X'47' Identifies the Resource Usage | | | | | | Attribute triplet M X'00' [MODCA-C-107]|
| 2 CODE Frequency X'00', X'FF' Frequency of use: | | | | | | X'00' Low X'FF' High M X'06' Triplet X'47' Semantics Tlength Contains the length of the triplet. Tid Identifies the Resource Usage Attribute triplet. Retired Functions [MODCA-C-108]|


Frequency Specifies the processing frequency of the associated page overlay. The valid values are:
Value Description
X'00' Low
X'FF' High
All others Reserved
Structured Fields Using Triplet X'47'
• “Include Page Overlay (IPO)” [MODCA-C-109]
• “Map Page Overlay (MPO)” [MODCA-C-110]
Object Checksum Triplet X'63'
The use of this triplet is restricted to the BMO and BPS structured fields in external (print file level) AFP
resource groups for the following products:
• PSF/MVS [MODCA-C-111]
• PSF/VSE [MODCA-C-112]
• RPM 2.0 [MODCA-C-113]
• RPM 3.0 [MODCA-C-114]
• PSF/2 (DPF) [MODCA-C-115]
• RMARK
The Object Checksum specifies a qualifier that can be used to identify or fingerprint an object.
Triplet X'63' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-116]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-117]|
| 0 UBIN Tlength 6 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-118]|
| 1 | CODE | Tid | | X'63' Identifies the Object Checksum | M | X'00' [MODCA-C-119]|
| 2 CODE Format X'01'–X'02' Specifies the format of the | | | | | | checksum: X'01' Object Cyclic Redundancy Check (CRC) X'02' Retired for private use M X'06' [MODCA-C-120]|
| 3–4 | UBIN | Qualifier | | X'0000'–X'FFFF' Object CRC check sum | M | X'06' [MODCA-C-121]|
| 5 BITS ClassFlgs Object class flags. See “Triplet | | | | | | X'63' Semantics” for ClassFlgs bit definitions. M X'06' Triplet X'63' Semantics Tlength Contains the length of the triplet. Tid Identifies the Object Checksum. Format Specifies the format of the checksum. Value Description X'01' Cyclic Redundancy Code (CRC) check sum X'02' Retired for private use Retired Functions [MODCA-C-122]|


All others Reserved
Application Note: Format X'02' is used in AFP environments for font resource management.
For a description, see the Font Object Content Architecture Reference.
Qualifier A two-byte value that may be used to support object identification based on the bit-content of
the object. This value is the Cyclic Redundancy Check (CRC) check sum and is generated as
follows:
1. All bits in the object, from the first bit in the Begin structured field to the last bit in the End [MODCA-C-123]
structured field, are treated as coefficients of an nth order polynomial.
2. A second bit string is formed based on the coefficients of a generator polynomial, which is [MODCA-C-124]
the CCITT V.41 polynomial defined as X
16 + X12 + X5 + 1. [MODCA-C-125]
3. The object polynomial is divided by the generator polynomial using binary division on the [MODCA-C-126]
bit strings that represent the coefficients of the two polynomials.
4. The remainder of this division is a polynomial of order less than 16. The coefficients of this [MODCA-C-127]
polynomial are the CRC check sum.
ClassFlgs Classifies objects for resource management. ClassFlgs bits have the following descriptions:
Bit Description
0 Usage scope: [MODCA-C-128]
B'0' Public resource object, unlimited usage
B'1' Private resource object, limited usage
1 Resource retention indicator: [MODCA-C-129]
B'0' Save resource
B'1' Do not save resource
2–7 Reserved; all bits must be B'0'
Structured Fields Using Triplet X'63'
• “Begin Overlay (BMO)” [MODCA-C-130]
• “Begin Page Segment (BPS)” [MODCA-C-131]
Object Origin Identifier Triplet X'64'
The use of this triplet is restricted to the BMO and BPS structured fields in external (print file level) AFP
resource groups for the following products:
• PSF/MVS [MODCA-C-132]
• PSF/VSE [MODCA-C-133]
• RPM 2.0 [MODCA-C-134]
• PSF/2
• RMARK
The Object Origin Identifier triplet is used to identify the system on which an object originated.
Triplet X'64' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-135]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-136]|
| 0 UBIN Tlength 61 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-137]|
| 1 CODE Tid X'64' Identifies the Object Origin | | | | | | Identifier triplet M X'00' Retired Functions [MODCA-C-138]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-139]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-140]|
| 2 CODE System X'01'–X'04' Identifies originating system: | | | | | | X'01' MVS X'02' VM X'03' PC-DOS X'04' VSE M X'06' [MODCA-C-141]|
| 3–10 | CHAR | SysID | | System ID and serial number | M | X'06' [MODCA-C-142]|
| 11–16 | CHAR | MedID | | Storage media ID | M | X'06' [MODCA-C-143]|
| 17–60 | CHAR | DSID | | Data set ID | M | X'06' Triplet X'64' Semantics Tlength Contains the length of the triplet. Tid Identifies the Object Origin Identifier triplet. System Specifies the type of system on which the object originated: Value Description X'01' MVS X'02' VM X'03' PC-DOS X'04' VSE All others Reserved SysID Specifies the ID and serial number of the processor on which the object originated MedID Identifies the storage media that contains the object (for example, the Volume Serial Number on an MVS system) DSID Identifies the data set on the storage media that contains the object Structured Fields Using Triplet X'64' • “Begin Overlay (BMO)” • “Begin Page Segment (BPS)” IMM Insertion Triplet X'73' The use of this triplet is restricted to the IMM structured field for the following products: • AFP OnDemand • AFP Workbench The IMM Insertion triplet is used to indicate that the Invoke Medium Map (IMM) structured field on which it is specified was inserted at the beginning of a page group by a filtering application. The IMM was inserted between the BNG and the first BPG in the group, but only if an IMM was not already specified there. The purpose of the inserted IMM is to allow the page group to be processed in standalone fashion. This triplet is ignored by presentation servers, and the IMM on which it is specified is processed as if the triplet were absent. The presence of this triplet on an IMM may be used by an inverse filtering application to remove the IMM when it is desired to present the complete document as it appeared before the IMM was inserted. Retired Functions [MODCA-C-144]|


Triplet X'73' Syntax
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-145]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-146]|
| 0 UBIN Tlength 4 Length of the triplet, including | | | | | | Tlength M X'02' [MODCA-C-147]|
| 1 | CODE | Tid | | X'73' Identifies the IMM Insertion triplet | M | X'00' [MODCA-C-148]|
| 2–3 | Reserved; | should | | be zero | M | X'06' Triplet X'73' Semantics Tlength Contains the length of the triplet. Tid Identifies the IMM Insertion triplet. Structured Field Using Triplet X'73' • “Invoke Medium Map (IMM)” Retired Parameters The following parameters have been retired: • MMC Keyword X'0Enn' • MMC Keyword X'F1nn' • MMO Flag Byte Bit 0 • Triplet X'62' StampType X'01' • OBP RefCSys (Byte 23) = X'05' • IPO value of X'FFFFFF' for XolOset, YolOset • IPS value of X'FFFFFF' for XpsOset, YpsOset • CDD Bytes 0–11 • GRID Font Width value of X'FFFF' • MGO Mapping Option X'50': Replicate-and-Trim • IOB RefCSys = X'00' • Triplet X'22' ResType = X'30' • MFC MFCScpe = X'06' - Printjob MFC • Triplet X'18' ISid = X'0C00' MMC Keyword X'0Enn' The use of this keyword is restricted to products that generate and process Form Maps for the IBM 3800 printer. The maximum horizontal adjustment, in pels, that an IBM 3800 printer operator can make to position the printing on each form in this subgroup. This modification can occur only in the first repeating group. If X'0E' is not specified, the previous horizontal adjustment value remains in effect. Retired Functions [MODCA-C-149]|


If more than one MMC contains an adjustment value, the maximum value is specified to the operator. The
operator can make an adjustment from 0 to twice the value of this parameter.
At the start of a data stream, this value defaults to 0. Once a value is set, it remains in effect for the entire print
job unless it is changed in another subgroup.
The value of nn must be from 0 through 20 or X'FF'. X'FF' indicates that the maximum horizontal adjustment is
unchanged.
MMC Keyword X'F1nn'
The use of this keyword is restricted to products that generate and process Form Maps for the IBM 3800
printer.
Shows whether forms flash is active. This value is not used by printers that do not support forms flash. This
modification can occur only once in the structured field. If this keyword is not present, forms flash is not active.
The value of nn can be:
Value Description
X'00' Forms flash is not active
X'01' Forms flash is active
MMO Flag Byte Bit 0
The use of this flag bit is restricted to products that generate and process Form Maps for the IBM 3800 printer.
Bit Description
0 Raster Indicator [MODCA-C-150]
Shows whether the overlay is to be loaded into the printer as a raster pattern overlay or as a
coded overlay:
B'0' Coded overlay
B'1' Raster overlay
If this bit is B'1' and a raster overlay is already loaded, the overlay is processed as a coded
overlay.
Triplet X'62' StampType X'01'
Use of this parameter value is restricted to RMARK.
Value Description
X'01' Date and time stamp indicates when the resource object was marked by the RMARK utility
program.
OBP RefCSys (Byte 23) = X'05'
Use of this parameter value is restricted to the following products:
• PSF/MVS [MODCA-C-151]
• PSF/VSE [MODCA-C-152]
• PSF/VM [MODCA-C-153]
• PSF/400 [MODCA-C-154]
• PSF/2
• Infoprint Manager (IPM) [MODCA-C-155]
Retired Functions


This value is used to specify the current text (I,B) coordinate system as the reference coordinate system. The
products that use this value also use three additional bytes in the Object Area Position (OBP) structured field to
identify which text coordinate system (absolute I,B or relative I,B) is specified.
IPO value of X'FFFFFF' for XolOset, YolOset
Use of this parameter value is restricted to the following products:
• ACIF
• PSF/MVS [MODCA-C-156]
• PSF/VSE [MODCA-C-157]
• PSF/VM [MODCA-C-158]
• PSF/400 [MODCA-C-159]
• Infoprint Manager (IPM) [MODCA-C-160]
When specified for XolOset or YolOset, this value indicates that the X p or Yp value, respectively, of the current
text print position should be used for the origin of the overlay.
IPS value of X'FFFFFF' for XpsOset, YpsOset
Use of this parameter value is restricted to the following products:
• ACIF
• PSF/MVS [MODCA-C-161]
• PSF/VSE [MODCA-C-162]
• PSF/VM [MODCA-C-163]
• PSF/400 [MODCA-C-164]
• Infoprint Manager (IPM) [MODCA-C-165]
When specified for XpsOset or YpsOset, this value indicates that the X p or Yp value, respectively, of the current
text print position should be used for the “origin” of the page segment.
CDD Bytes 0–11
Use of this parameter is restricted to the following products:
• Pre-year 2000 AFP applications [MODCA-C-166]
These parameters define the unit base, units per unit base, and extents for the object presentation space:
XocBase (byte 0) Specifies the unit base for the X axis of the object presentation space
coordinate system. The range is X'00', X'01' (10 inches, 10 centimeters).
YocBase (byte 1) Specifies the unit base for the Y axis of the object presentation space
coordinate system. The range is X'00', X'01' (10 inches, 10 centimeters).
XocUnits (bytes 2–3) Specifies the number of units per unit base for the X axis of the object
presentation space coordinate system. The range is 1–32,767. A value of
X'0000' indicates that this parameter is not specified.
YocUnits (bytes 4–5) Specifies the number of units per unit base for the Y axis of the object
presentation space coordinate system. The range is 1–32,767. A value of
X'0000' indicates that this parameter is not specified.
XocSize (bytes 6–8) Specifies the extent of the X axis of the object presentation space coordinate
system. This is also known as the object presentation space's X axis size.
The range is 1–32,767; a value of X'000000' indicates that the presentation
space X axis extent is not specified.
YocSize (bytes 9–11) Specifies the extent of the Y axis of the object presentation space coordinate
system. This is also known as the object presentation space's Y axis size. The
Retired Functions


range is 1–32,767; a value of X'000000' indicates that the presentation space
Y axis extent is not specified.
GRID Font Width value of X'FFFF'
Use of this parameter value is restricted to the following products:
• OS/400 ® print applications [MODCA-C-167]
When specified for the GRID font width on an FQN type X'84' triplet, this value indicates that the device default
font width should be used.
MGO Mapping Option X'50': Replicate-and-Trim
Use of this parameter is restricted to the following products:
• PSF/390 [MODCA-C-168]
• PSF/400 [MODCA-C-169]
• Infoprint Manager for AIX ® [MODCA-C-170]
• Infoprint Manager for Windows ® [MODCA-C-171]
This parameter defines the following mapping option.
The Graphics Presentation Space Window is positioned so that the top left corner of the window is coincident
with the origin of the object area and the window size is unchanged. The Graphics Presentation Space Window
is then replicated in the X and Y directions of the object area until the object area is filled. Each new replicate of
the window in the X direction is precisely aligned with the window previously placed in the X direction. Each
new replicate of the window in the Y direction is precisely aligned with the window previously placed in the Y
direction. If the last Graphics Presentation Space Window in either the X or Y direction fits only partially into the
object area, the portion of the window that falls outside the object area is trimmed. All data that falls within the
object area extents is presented, but data that falls outside of the object area is not presented. When this
option is specified, the data object's content origin specified in the XocaOset and YocaOset parameters in the
Object Area Position structured field is ignored.
IOB RefCSys = X'00'
This parameter value is retired for private use in AFP line-data environments. It is used in AFP line-data
environments to position and rotate the object area with respect to the current text (I,B) coordinate system. For
more information, see Advanced Function Presentation: Programming Guide and Line Data Reference.
Triplet X'22' ResType = X'30'
This parameter value is retired for private use in AFP line-data environments. It is used in AFP line-data
environments in a PageDef object to denote an IOB Reference. It matches an Include Object (IOB) structured
field to a Descriptor. For more information, see Advanced Function Presentation: Programming Guide and Line
Data Reference.
MFC MFCScpe = X'06'– Printjob MFC
Use of this parameter is restricted to the following products:
• PSF for z/OS ® [MODCA-C-172]
This parameter value defines the following scope for the MFC.
The scope of this MFC is the complete printjob, which includes the printjob header pages, the user print files
that follow the header pages, all message pages and trailer pages, and all other separator pages that are
associated with the printjob. This scope may only be specified on an MFC in the DEG of the form map that is
Retired Functions


used to generate the header pages for a printjob; if specified anywhere else it is ignored. The message,
separator, and trailer pages are optional and have finishing applied if they are generated.
Triplet X'18' ISid = X'0C00'
The use of this parameter value is restricted to products that generate or process the retired MO:DCA
interchange set MO:DCA IS/2.
Retired Functions


Retired Interchange Set
The MO:DCA Interchange Set 2 (MO:DCA IS/2) has been retired for products that implemented this set before
2012. This interchange set is no longer part of the MO:DCA interchange set hierarchy. [MODCA-C-173]
MO:DCA Interchange Set 2
This section defines the MO:DCA Interchange Set 2 (MO:DCA IS/2) used for presentation documents.
For information on the level of function required for the OCAs included in this interchange set, refer to the
MO:DCA environment appendix in the following AFP documents:
BCOCA Bar Code Object Content Architecture Reference
GOCA Graphics Object Content Architecture for AFP Reference
IOCA Image Object Content Architecture Reference
PTOCA Presentation Text Object Content Architecture Reference
Note: MO:DCA IS/2 is a proper superset of MO:DCA IS/1 and therefore contains all of the function defined by
MO:DCA IS/1. Generators of data streams that contain only MO:DCA IS/1 function may choose to
identify those data streams as either MO:DCA IS/1 or MO:DCA IS/2 data streams. However, be aware
that identifying them as MO:DCA IS/2 potentially limits the receivers of the data stream to only those that
claim to support MO:DCA IS/2.
Data Stream Syntax Structure
The groupings of MO:DCA structured fields that follow identify those structured fields which appear within each
begin-end structured field pair or state. This section specifies the structured fields allowed within a MO:DCA
Presentation Interchange Set 2 data stream and shows both the MO:DCA state hierarchy and the validity of
structured fields within each state.
If a structured field that is not identified as being part of this interchange set appears anywhere within the data
stream, a X'40' exception condition exists. If a structured field appears within any state where it is not
permitted, or if it appears out of the stated order or more than the permitted number of times, a X'20' exception
condition exists. If a structured field that is identified as required does not appear within a specific state, a X'08'
exception condition exists.
The conventions used in these structured field groupings are:
( ) The structured field acronym and identifier are shown in parentheses. The presence of dots or periods
in the identifier indicates that the item is not a structured field, but instead is a structure, for example a
page. The structure is composed of an assortment of structured fields, and is defined separately.
[ ] Brackets indicate optional structured fields. When a structured field is shown without brackets, it must
appear between the begin and end structured fields.
+ Plus signs indicate structured fields may appear in any order relative to those that precede or succeed
it except when the preceding or succeeding structured field does not have a plus (+) sign. Then the
order is as listed.
(S) The enclosed (S) indicates that the structured field may be repeated. When present on a required
structured field, at least one occurrence of the structured field is required, but multiple instances of it
may occur.
F2 An F2 indicates that the structured field is a format two structured field. See “Structured Field Formats”
for further details.
MO:DCA IS/2


Notes:
1. The Begin Document and End Document structured fields are required in a MO:DCA data stream. [MODCA-C-174]
2. The No Operation structured field may appear within any begin-end domain and thus is not listed in the [MODCA-C-175]
structured field groupings.
3. The architecture that owns and controls the content of each of the data and resource objects carried in a [MODCA-C-176]
MO:DCA data stream is identified in the following structured field groupings. Please refer to the referenced
documentation for further details.
4. The Flag byte (byte 5) in the Structured Field Introducer (SFI) must be set to X'00'. MO:DCA IS/2 does not [MODCA-C-177]
support SFI extension, structured field segmentation, or structured field padding.
Document
Figure 89. MO:DCA IS/2: Document Structure
Begin Document (BDT, D3A8A8)
[ ( D3..A7) Document Index ]
+ [ (IMM, D3ABCC) Invoke Medium Map (S) ]
+ [ ( D3..AF) Page (S) ]
End Document (EDT, D3A9A8)
Document Index
Figure 90. MO:DCA IS/2: Document Index Structure
Begin Document Index (BDI, D3A8A7)
(IEL, D3B2A7) Index Element (S)
End Document Index (EDI, D3A9A7)
Note: These structured fields are used for informational purposes only. Thus, there is no requirement that
these fields be processed by a receiver. A compliant receiver must be able to recognize the document
index structure, but it may elect to simply skip the entire structure without processing its content.
Resource Group
Figure 91. MO:DCA IS/2: Resource Group Structure
Begin Resource Group (BRG, D3A8C6)
+ [ ( D3..DF) Overlay (S) ]
End Resource Group (ERG, D3A9C6)
Page
Figure 92. MO:DCA IS/2: Page Structure
Begin Page (BPG, D3A8AF)
[ ( D3..C6) Resource Group ]
( D3..C9) Active Environment Group
+ [ ( D3..EB) Bar Code Object (S) ]
+ [ ( D3..BB) Graphics Object (S) ]
+ [ ( D3..FB) Image Object (S) ]
+ [ (IPO, D3AFD8) Include Page Overlay (S) ] 1
+ [ ( D3..9B) Presentation Text Object (S) ]
End Page (EPG, D3A9AF)
MO:DCA IS/2


Notes:
1. For purposes of print server resource management, each overlay included on a page with an IPO must first [MODCA-C-178]
be mapped to a local ID with an MPO in the AEG for that page. Note that the MPO is only specified in the
AEG for a page; it is not allowed in the AEG for an overlay.
Overlay
Figure 93. MO:DCA IS/2: Overlay Structure
Begin Overlay (BMO, D3A8DF)
( D3..C9) Active Environment Group
+ [ ( D3..EB) Bar Code Object (S) ]
+ [ ( D3..BB) Graphics Object (S) ]
+ [ ( D3..FB) Image Object (S) ]
+ [ ( D3..9B) Presentation Text Object (S) ]
End Overlay (EMO, D3A9DF)
Active Environment Group
Figure 94. MO:DCA IS/2: Active Environment Group Structure
Begin Active Environment Group (BAG, D3A8C9)
[ (MCF, D3AB8A) Map Coded Font F2 (S) ] 3
[ (MPO, D3ABD8) Map Page Overlay (S) ] 4
(PGD, D3A6AF) Page Descriptor
[ (OBD, D3A66B) Object Area Descriptor ] 1
[ (OBP, D3AC6B) Object Area Position ] 1
[ (PTD, D3B19B) Presentation Text Data Descriptor F2 2
End Active Environment Group (EAG, D3A9C9)
Notes:
1. Used for presentation text objects only and is optional. For graphics and image objects, the OBD and OBP [MODCA-C-179]
must be specified in the OEG associated with the graphic, bar code, or image object.
2. Required only when the associated page contains one or more presentation text objects. [MODCA-C-180]
3. For purposes of print server resource management, an MCF mapping the same font must be specified in [MODCA-C-181]
the AEG whenever an MCF is specified in a bar code or graphics OEG. The local ID used in the page or
overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped
in the AEG solely due to their presence in an object's OEG.
4. For purposes of print server resource management, each overlay included on a page with an IPO must first [MODCA-C-182]
be mapped to a local ID with an MPO in the AEG for that page. Note that the MPO is only specified in the
AEG for a page; it is not allowed in the AEG for an overlay.
MO:DCA IS/2


Bar Code Object (BCOCA BCD1)
Figure 95. MO:DCA IS/2: Bar Code Object Structure
Begin Bar Code Object (BBC, D3A8EB)
( D3..C7) Object Environment Group
[ (BDA, D3EEEB) Bar Code Data (S) ]
End Bar Code Object (EBC, D3A9EB)
Note: Refer to the Bar Code Object Content Architecture Reference for a full description of the BCOCA
content, syntax, and semantics for MO:DCA IS/2.
Object Environment Group (OEG) for Bar Code Object
Figure 96. MO:DCA IS/2: Object Environment Group for Bar Code Object Structure
Begin Object Environment Group (BOG, D3A8C7)
(OBD, D3A66B) Object Area Descriptor
(OBP, D3AC6B) Object Area Position
[ (MBC, D3ABEB) Map Bar Code Object ]
[ (MCF, D3AB8A) Map Coded Font F2 (S) ]1
(BDD, D3A6EB) Object Area Descriptor
End Object Environment Group (EOG, D3A9C7)
Notes:
1. For purposes of print server resource management, an MCF mapping the same font must be specified in [MODCA-C-183]
the AEG whenever an MCF is specified in a bar code or graphics OEG. The local ID used in the page or
overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped
in the AEG solely due to their presence in an object's OEG.
Graphics Object (GOCA DR/2V0)
Figure 97. MO:DCA IS/2: Graphics Object Structure
Begin Graphics Object (BGR, D3A8BB)
( D3..C7) Object Environment Group
[ (GAD, D3EEBB) Graphics Data (S) ]
End Graphics Object (EGR, D3A9BB)
Note: Refer to the Graphics Object Content Architecture for AFP Reference for a full description of the GOCA
DR/2V0 content, syntax, and semantics for MO:DCA IS/2.
Object Environment Group (OEG) for Graphics Object
Figure 98. MO:DCA IS/2: Object Environment Group for Graphics Object Structure
Begin Object Environment Group (BOG, D3A8C7)
(OBD, D3A66B) Object Area Descriptor
(OBP, D3AC6B) Object Area Position
[ (MGO, D3ABBB) Map Graphics Object ]
[ (MCF, D3AB8A) Map Coded Font F2 (S) ] 1
(GDD, D3A6BB) Graphics Data Descriptor
End Object Environment Group (EOG, D3A9C7)
MO:DCA IS/2


Notes:
1. For purposes of print server resource management, an MCF mapping the same font must be specified in [MODCA-C-184]
the AEG whenever an MCF is specified in a bar code or graphics OEG. The local ID used in the page or
overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped
in the AEG solely due to their presence in an object's OEG.
Image Object (IOCA FS10 or FS11)
Figure 99. MO:DCA IS/2: Image Object Structure
Begin Image Object (BIM, D3A8FB)
( D3..C7) Object Environment Group
[ (IPD, D3EEFB) Image Picture Data (S) ]
End Image Object (EIM, D3A9FB)
Note: Refer to the Image Object Content Architecture Reference for a full description of the IOCA FS10 and
FS11 content, syntax, and semantics for MO:DCA IS/2.
Object Environment Group (OEG) for Image Object
Figure 100. MO:DCA IS/2: Object Environment Group for Image Object Structure
Begin Object Environment Group (BOG, D3A8C7)
(OBD, D3A66B) Object Area Descriptor
(OBP, D3AC6B) Object Area Position
[ (MIO, D3ABFB) Map Image Object ]
(IDD, D3A6FB) Image Data Descriptor
End Object Environment Group (EOG, D3A9C7)
Presentation Text Object (PTOCA PT1)
Figure 101. MO:DCA IS/2: Presentation Text Object Structure
Begin Presentation Text Object (BPT, D3A89B)
[ (PTX, D3EE9B) Presentation Text Data (S) ]
End Presentation Text Object (EPT, D3A99B)
Note: Refer to the Presentation Text Object Content Architecture Reference for a full description of the PTOCA
PT1 content, syntax, and semantics for MO:DCA IS/2.
Permitted Structured Fields
This section describes the parameters and ranges of values supported for each of the structured fields
contained in this interchange set.
The structured fields are listed alphabetically and described using tables. The table heading for each
structured field contains the structured field's acronym, its three-byte hexadecimal identifier, and its full name.
Also included is the page number in the document where a detailed description of the structured field can be
found.
MO:DCA IS/2


Structured Field Parameters
In general, the structured field tables contain the following information for each parameter:
1. The offset from the beginning of the data portion of the structured field or from the beginning of the triplet. [MODCA-C-185]
2. Values and description: [MODCA-C-186]
• When a specific parameter value is required, the specific value or the range of acceptable values is [MODCA-C-187]
specified, followed by “→” and an explanation or description of the parameter.
• When no specific value is required, or when a choice of values is required, the parameter name or a [MODCA-C-188]
description of the parameter is given. If a choice of values is required, the choices are identified in the
table.
3. For those parameters defined and owned by the MO:DCA architecture, occurrence is specified either as a [MODCA-C-189]
lowercase n indicating that the occurrence is unlimited by the interchange set, or as a number representing
the maximum number of times the parameter may appear within the containing structured field, repeating
group, or triplet.
4. For those parameters defined and owned by the MO:DCA architecture, optionally is specified as: [MODCA-C-190]
O Optional. The parameter may or may not appear.
M Mandatory. The parameter must always appear.
R Retired. A receiver must be able to receive this parameter, but a generator should not
generate it.
Unless a specific order is required, self-identifying parameters are listed in alphanumeric sequence by
identifier and include the page number in the document where a detailed description of the parameter is
located.
In general, no exception conditions are identified within the interchange set definition for the structured fields or
their parameters. The page numbers provided for each structured field and each triplet provide the source for
determining what exception conditions may be anticipated. However, the following general rules apply:
• For those structured fields where a parameter order is stated, if a parameter appears outside that stated [MODCA-C-191]
order, a X'01' exception condition exists.
• If a parameter value appears that is outside the range specified for that parameter, a X'02' exception [MODCA-C-192]
condition exists.
• If a parameter that is identified as mandatory does not appear on a specific structured field, a X'04' exception [MODCA-C-193]
condition exists.
• Unless otherwise stated, if any unrecognized parameter or triplet appears on any structured field, a X'10' [MODCA-C-194]
exception condition exists.
Notes:
1. Any triplet encountered on any of the Begin structured fields listed below that is not explicitly defined as [MODCA-C-195]
being valid for that structured field should be ignored and should not cause an exception condition.
2. If specified, the name contained in the name parameter on an End structured field must match that [MODCA-C-196]
specified in the name parameter on its matching Begin structured field, or a X'01' exception condition
exists.
Bar Code Data
BDA X'D3EEEB' Bar Code Data (See “Bar Code Data (BDA)”)
0–n Up to 8,192 bytes of bar code data as defined by BCOCA BCD1
MO:DCA IS/2


Bar Code Data Descriptor
BDD X'D3A6EB' Bar Code Data Descriptor (See “Bar Code Data Descriptor (BDD)”)
0–n Bar Code descriptor data as defined by BCOCA BCD1
Begin Active Environment Group
BAG X'D3A8C9' Begin Active Environment Group (See “Begin Active Environment Group (BAG)”)
0–7 Active Environment Group name (8 characters) 1 O
Begin Bar Code Object
BBC X'D3A8EB' Begin Bar Code Object (See “Begin Bar Code Object (BBC)”)
0–7 Bar Code Object name (8 characters) 1 O
Begin Document Index
BDI X'D3A8A7' Begin Document Index (See “Begin Document Index (BDI)”)
0–7 Document Index name (8 characters) 1 O
Begin Document
BDT X'D3A8A8' Begin Document (See “Begin Document (BDT)”)
0–7 Document name (8 characters) 1 M
8–9 X'0000' → Reserved; must be binary zero 1 M
10–n The following triplets, in any order:
Coded Graphic Character Set Global Identifier Triplet (See “Coded Graphic Character Set Global Identifier Triplet
X'01'”)
0–1 X'0601' → Triplet length and identifier 1 M
2–5 Character set and code page identification 1 M
Fully Qualified Name (See “Fully Qualified Name Triplet X'02'”) 1 O
0–1 X'nn02' → Triplet length and identifier 1 M
2–3 X'0100' → FQN type and format. Replace first GID Name. 1 M
4–n Name of the document. It may be 1 to 250 bytes in length. 1 M
MO:DCA Interchange Set Triplet (See “MO:DCA Interchange Set Triplet X'18'” on
page 367)
1 M
0–1 X'0518' → Triplet length and identifier 1 M
2 X'01' → Interchange set type, presentation 1 M [MODCA-C-197]
3–4 X'0C00' → Interchange set identifier (MO:DCA IS/2) 1 M
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'”
)
1 R
MO:DCA IS/2


BDT X'D3A8A8' Begin Document (See “Begin Document (BDT)”)
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'02' → Object type, presentation text 1 M [MODCA-C-198]
3 X'00' → Architecture version 1 M [MODCA-C-199]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'0000' → Presentation text function set definition (PT/1) 1 M
8–n Reserved, not checked 1 O
Note: For compatibility with MO:DCA IS/1, one instance of this triplet is permitted when the data stream contains a PT1
presentation text object. However, this triplet has been retired and should not be included in MO:DCA IS/2 data
streams.
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'”
)
1 R
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'03' → Object type, graphics 1 M [MODCA-C-200]
3 X'00' → Architecture version 1 M [MODCA-C-201]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'4000' → Graphics function set definition (DR/2V0) 1 M
8–n Reserved, not checked 1 O
Note: For compatibility with MO:DCA IS/1, one instance of this triplet is permitted when the data stream contains a DR/
2V0 graphics object. However, this triplet has been retired and should not be included in MO:DCA IS/2 data streams.
Object Function Set Specification Triplet (See “Resource Object Type Triplet X'21'”
)
1 R
0–1 X'nn21' → Triplet length and identifier 1 M
2 X'06' → Object type, image 1 M [MODCA-C-202]
3 X'00' → Architecture version 1 M [MODCA-C-203]
4–5 X'8000' → MO:DCA function set definition 1 M
6–7 X'8000' → Image function set definition (FS10) 1 M
8–n Reserved, not checked 1 O
For compatibility with MO:DCA IS/1, one instance of this triplet is permitted when the data stream contains an FS10
image object. However, this triplet has been retired and should not be included in MO:DCA IS/2 data streams. For this
reason, no value has been provided for IOCA FS11.
Begin Graphics Object
BGR X'D3A8BB' Begin Graphics Object (See “Begin Graphics Object (BGR)”)
0–7 Graphics Object name (8 characters) 1 O
Begin Image Object
BIM X'D3A8FB' Begin Image Object (See “Begin Image Object (BIM)”)
0–7 Image Object name (8 characters) 1 O
MO:DCA IS/2


Begin Object Environment Group
BOG X'D3A8C7' Begin Object Environment Group (See “Begin Object Environment Group (BOG)”)
0–7 Object Environment Group name (8 characters) 1 O
Begin Overlay
BMO X'D3A8DF' Begin Overlay (See “Begin Overlay (BMO)”)
0–7 Overlay name (8 characters) 1 M
Begin Page
BPG X'D3A8AF' Begin Page (See “Begin Page (BPG)”)
0–7 Page name (8 characters) 1 O
Begin Presentation Text Object
BPT X'D3A89B' Begin Presentation Text Object (See “Begin Presentation Text Object (BPT)”)
0–7 Presentation T ext Object name (8 characters) 1 O
Begin Resource Group
BRG X'D3A8C6' Begin Resource Group (See “Begin Resource Group (BRG)”)
0–7 Resource Group name (8 characters) 1 O
End Active Environment Group
EAG X'D3A9C9' End Active Environment Group (See “End Active Environment Group (EAG)”)
0–7 Active Environment Group name (8 characters) 1 O
End Bar Code Object
EBC X'D3A9EB' End Bar Code Object (See “End Bar Code Object (EBC)”)
0–7 Bar Code Object name (8 characters) 1 O
End Document Index
EDI X'D3A9A7' End Document Index (See “End Document Index (EDI)”)
0–7 Document Index name (8 characters) 1 O
MO:DCA IS/2


End Document
EDT X'D3A9A8' End Document (See “End Document (EDT)”)
0–7 Document name (8 characters) 1 O
End Graphics Object
EGR X'D3A9BB' End Graphics Object (See “End Graphics Object (EGR)”)
0–7 Graphics Object name (8 characters) 1 O
End Image Object
EIM X'D3A9FB' End Image Object (See “End Image Object (EIM)”)
0–7 Image Object name (8 characters) 1 O
End Object Environment Group
EOG X'D3A9C7' End Object Environment Group (See “End Object Environment Group (EOG)”)
0–7 Object Environment Group name (8 characters) 1 O
End Overlay
EMO X'D3A9DF' End Overlay (See “End Overlay (EMO)”)
0–7 Overlay name (8 characters) 1 O
End Page
EPG X'D3A9AF' End Page (See “End Page (EPG)”)
0–7 Page name (8 characters) 1 O
End Presentation Text Object
EPT X'D3A99B' End Presentation Text Object (See “End Presentation Text Object (EPT)”)
0–7 Presentation T ext Object name (8 characters) 1 O
End Resource Group
ERG X'D3A9C6' End Resource Group (See “End Resource Group (ERG)”)
0–7 Resource Group name (8 characters) 1 O
MO:DCA IS/2


Graphics Data
GAD X'D3EEBB' Graphics Data (See “Graphics Data (GAD)”)
0–n Up to 8,192 bytes of graphics data as defined by GOCA DR/2V0
Graphics Data Descriptor
GDD X'D3A6BB' Graphics Data Descriptor (See “Graphics Data Descriptor (GDD)”)
0–n Graphics descriptor data as defined by GOCA
Image Data Descriptor
IDD X'D3A6FB' Image Data Descriptor (See “Image Data Descriptor (IDD)”)
0–n Image descriptor data as defined by IOCA FS10 and FS11
Image Picture Data
IPD X'D3EEFB' Image Picture Data (See “Image Picture Data (IPD)”)
0–n Up to 8,192 bytes of image segment data as defined by IOCA FS10 or FS11
Include Page Overlay
IPO X'D3AFD8' Include Page Overlay (See “Include Page Overlay (IPO)”)
0–7 Page overlay reference name. 1 M
8–10 Page overlay origin, X-coordinate. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page X measurement units
1 M
11–13 Page overlay origin, Y-coordinate. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page Y measurement units
1 M
14–15 X'0000' → Overlay orientation of 0 degrees 1 O
16–n The following triplets, in any order:
Page Overlay Conditional Processing Triplet (See “Page Overlay Conditional
Processing Triplet X'46'”)
n O
0–1 X'nn46' → Triplet length and identifier 1 M
2 Page Overlay Type. It must be one of the following: [MODCA-C-204]
X'00' → Type 0 (No conditional processing)
X'01' → Type 1 (Annotation)
1 M
3 X'01'–X'FE' → Level. It must be in the range of 1 to 254. 1 O [MODCA-C-205]
MO:DCA IS/2


IPO X'D3AFD8' Include Page Overlay (See “Include Page Overlay (IPO)”)
Resource Usage Attribute Triplet (See “Resource Usage Attribute Triplet X'47'” on
page 566)
1 O
0–1 X'0347' → Triplet length and identifier 1 M
2 Frequency of use. It must be one of the following: [MODCA-C-206]
X'00' → Low
X'FF' → High
1 M
Index Element
IEL X'D3B2A7' Index Element (See “Index Element (IEL)”)
0–n The following triplets, in any order:
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 M
0–1 X'nn02' → Triplet length and identifier 1 M
2–3 X'CA00' → FQN type and format, Index Element Name 1 M
4–n Name of this IEL. It may be 1 to 250 bytes in length. 1 M
Object Byte Offset Triplet (See “Object Byte Offset Triplet X'2D'”) 1 M
0–1 X'062D' → Triplet length and identifier 1 M
2–5 Direct byte offset. It must be one of the following:
X'00000000'–X'7FFFFFFF' → Byte offset from beginning of
document containing indexed element
X'FFFFFFFF' → Indexed element is outside the
document
1 M
Invoke Medium Map
IMM X'D3ABCC' Invoke Medium Map (See “Invoke Medium Map (IMM)”)
0–7 External name of the medium map to be invoked (8 characters) 1 M
Map Bar Code Object
MBC X'D3ABEB' Map Bar Code Object (See “Map Bar Code Object (MBC)”)
0–1 X'0005' → Length of this repeating group is 5 bytes 1 M
2–4 The following triplet:
Mapping Option Triplet (See “Mapping Option Triplet X'04'”) 1 M
0–1 X'0304' → Triplet length and identifier 1 M
2 X'00' → Output option (position) 1 M [MODCA-C-207]
Note: If this structured field is not specified, the architected default is position.
MO:DCA IS/2


Map Coded Font, Format 2
MCF X'D3AB8A' Map Coded Font (See “Map Coded Font (MCF) Format 2”)
0–1 X'00nn' → Length of this repeating group 254 M
2–n The following triplets, in any order:
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”)
Note: See “MCF Font Names” for details.
2 M
0–1 X'0C02' → Triplet length and identifier 1 M
2 The FQN type. It must be one of the following: [MODCA-C-208]
X'84' → Coded Font Reference
X'85' → Code Page Reference
X'86' → Font Character Set Reference
1 M
3 X'00' → FQN format 1 M [MODCA-C-209]
4–11 External name of the coded font, code page, or font character set. 1 M
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 O
0–1 X'nn02' → Triplet length and identifier 1 M
2–3 X'0800' → FQN type and format, Font Typeface Name 1 M
4–n External name of the font typeface. It may be 1 to 32 bytes in length. 1 M
Font Descriptor Specification Triplet (See “Font Descriptor Specification Triplet
X'1F'”)
1 O
0–1 X'141F' → Triplet length and identifier 1 M
2 X'01'–X'09' → Font Weight Class. It must be in the range of 1 to 9. 1 M [MODCA-C-210]
3 X'01'–X'09' → Font Width Class. It must be in the range of 1 to 9. 1 M [MODCA-C-211]
4–5 X'0000'–X'7FFF' → Font Height. It must be in the range of 0 to 32,767
1440ths of an inch.
1 M
6–7 X'0000'–X'7FFF' → Font Width. It must be in the range of 0 to 32,767
1440ths of an inch.
1 M
8 Font Descriptor Flags, as follows: [MODCA-C-212]
Bit Description
0 Italics [MODCA-C-213]
1 Underscored [MODCA-C-214]
2 Reserved, must be B'0' [MODCA-C-215]
3 Hollow [MODCA-C-216]
4 Overstruck [MODCA-C-217]
5 Proportional [MODCA-C-218]
6 Kerned characters (pairwise) [MODCA-C-219]
7 Reserved, must be B'0' [MODCA-C-220]
1 M
9–19 Reserved 1 M
Font Coded Graphic Character Set Global Identifier Triplet (See “Font Coded
Graphic Character Set Global Identifier Triplet X'20'”)
1 O
0–1 X'0620' → Triplet length and identifier 1 M
2–5 The GCSGID and CPGID for the font. 1 M
Resource Local Identifier Triplet (See “Resource Local Identifier Triplet X'24'” on
page 378)
1 M
MO:DCA IS/2


MCF X'D3AB8A' Map Coded Font (See “Map Coded Font (MCF) Format 2”)
0–1 X'0424' → Triplet length and identifier 1 M
2 X'05' → Resource type, coded font 1 M [MODCA-C-221]
3 Resource Local Identifier. It must be one of the following: [MODCA-C-222]
X'01'–X'7F' → It must be in the range of 1 to 127
when used for mapping a font.
X'FE' → It must be 254 when used for resource
management purposes in the AEG.
1 M
Resource Section Number Triplet (See “Resource Section Number Triplet X'25'” on
page 379)
1 O
0–1 X'0325' → Triplet length and identifier 1 M
2 Resource Section Number. It must be one of the following: [MODCA-C-223]
X'00' → It must be 0 when referencing an
EBCDIC Presentation single-byte
coded font (encoding scheme ID
X'61xx') or all sections of an EBCDIC
Presentation double-byte coded font
(encoding scheme ID X'62xx').
X'41'–X'FE' → It must be in the range of 65 to 254
when referencing a specific section of
an EBCDIC Presentation double-byte
coded font (encoding scheme ID
X'62xx').
1 M
Character Rotation Triplet (See “Character Rotation Triplet X'26'”) 1 O
0–1 X'0426' → Triplet length and identifier 1 M
2–3 Character Rotation. It must be one of the following:
X'0000' → 0-degree character rotation
X'2D00' → 90-degree character rotation
X'5A00' → 180-degree character rotation
X'8700' → 270-degree character rotation
1 MMCF Font Names [MODCA-C-224]
The MCF must have one of the following:
• A type X'84' (Coded Font Reference) Fully Qualified Name (X'02') triplet. T o support existing products, the [MODCA-C-225]
coded font name must be specified as a global resource identifier (GRID). For a definition of the GRID, see
“Global Resource Identifier (GRID) Definition”.
• Both a type X'85' (Code Page Name Reference) and a type X'86' (Font Character Set Name Reference) [MODCA-C-226]
Fully Qualified Name (X'02') triplet. T o support existing products, the names of the code page and font
character set must be eight characters in length and must match the external names of these objects in their
respective resource libraries.
Map Graphics Object
MGO X'D3ABBB' Map Graphics Object (See “Map Graphics Object (MGO)”)
0–1 X'0005' → Length of this repeating group is 5 bytes 1 M
2–4 The following triplet:
Mapping Option Triplet (See “Mapping Option Triplet X'04'”) 1 M
MO:DCA IS/2


MGO X'D3ABBB' Map Graphics Object (See “Map Graphics Object (MGO)”)
0–1 X'0304' → Triplet length and identifier 1 M
2 Output Option. It must be one of the following: [MODCA-C-227]
X'10' → Position and trim
X'20' → Scale to fit
X'30' → Center and trim
1 M
Note: If this structured field is not specified, the architected default is scale to fit.
Map Image Object
MIO X'D3ABFB' Map Image Object (See “Map Image Object (MIO)”)
0–1 X'0005' → Length of this repeating group is 5 bytes 1 M
2–4 The following triplet:
Mapping Option Triplet (See “Mapping Option Triplet X'04'”) 1 M
0–1 X'0304' → Triplet length and identifier 1 M
2 Output Option. It must be one of the following: [MODCA-C-228]
X'10' → Position and trim
X'20' → Scale to fit
X'30' → Center and trim
1 M
Note: If this structured field is not specified, the architected default is scale to fit.
Map Page Overlay
MPO X'D3ABD8' Map Page Overlay (See “Map Page Overlay (MPO)”)
0–1 X'nnnn' → Length of this repeating group 127 M
2–17 The following triplet:
Fully Qualified Name Triplet (See “Fully Qualified Name Triplet X'02'”) 1 M
0–1 X'0C02' → Triplet length and identifier 1 M
2–3 X'8400' → FQN type and format, reference to overlay 1 M
4–11 External name of the overlay. 1 M
Resource Local Identifier Triplet (See “Resource Local Identifier Triplet X'24'” on
page 378)
1 M
0–1 X'0424' → Triplet length and identifier 1 M
2 X'02' → Resource type, page overlay 1 M [MODCA-C-229]
3 X'01'–X'7F' → Resource Local Identifier. It must be in the range of 1 to [MODCA-C-230]
127.
1 M
Page Overlay Conditional Processing Triplet (See “Page Overlay Conditional
Processing Triplet X'46'”)
n O
0–1 X'nn46' → Triplet length and identifier 1 M
2 Page Overlay Type. It must be one of the following: [MODCA-C-231]
X'00' → Type 0 (No conditional processing)
X'01' → Type 1 (Annotation)
1 M
MO:DCA IS/2


MPO X'D3ABD8' Map Page Overlay (See “Map Page Overlay (MPO)”)
3 X'01'–X'FE' → It must be in the range of 1 to 254. 1 O [MODCA-C-232]
Resource Usage Attribute Triplet (See “Resource Usage Attribute Triplet X'47'” on
page 566)
1 O
0–1 X'0347' → Triplet length and identifier 1 M
2 Frequency of use. It must be one of the following: [MODCA-C-233]
X'00' → Low
X'FF' → High
1 M
No Operation
NOP X'D3EEEE' No Operation (See “No Operation (NOP)”)
0–n Up to 32,759 bytes of data.
Object Area Descriptor
OBD X'D3A66B' Object Area Descriptor (See “Object Area Descriptor (OBD)”)
0–n The following triplets, in any order:
Descriptor Position Triplet (See “Descriptor Position Triplet X'43'”) 1 M
0–1 X'0343' → Triplet length and identifier 1 M
2 X'01'–X'7F' → Descriptor position ID. It must be in the range of 1 to 127. 1 M [MODCA-C-234]
Measurement Units Triplet (See “Measurement Units Triplet X'4B'”) 1 M
0–1 X'084B' → Triplet length and identifier 1 M
2–3 X'0000' → Object area measurement units base for X and Y 1 M
4–5 Object area measurement units value for X. It must be one of the following:
X'0960' → 2400 units per unit base (240 units per
inch)
X'3840' → 14400 units per unit base (1440 units
per inch)
1 M
6–7 Object area measurement units value for Y . It must be identical to bytes 4–
5.
1 M
Object Area Size Triplet (See “Object Area Size Triplet X'4C'”). 1 M
0–1 X'094C' → Triplet length and identifier 1 M
2 X'02' → Type, actual object area size 1 M [MODCA-C-235]
3–5 Object area size in the X direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using
240 units per inch for the object area X [MODCA-C-236]
measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using
1440 units per inch for the object area [MODCA-C-237]
X measurement units
1 M
MO:DCA IS/2


OBD X'D3A66B' Object Area Descriptor (See “Object Area Descriptor (OBD)”)
6–8 Object area size in the Y direction. It must be one of the following:
X'000001'–X'001555' → In the range of 1 to 5,461 when using
240 units per inch for the object area Y [MODCA-C-238]
measurement units
X'000001'–X'007FFF' → In the range of 1 to 32,767 when using
1440 units per inch for the object area [MODCA-C-239]
Y measurement units
1 M
Presentation Space Reset Mixing Triplet (See “Presentation Space Reset Mixing
Triplet X'70'”)
1 O
0–1 X'0370' → Triplet length and identifier 1 M
2 Mixing Flags, as follows: [MODCA-C-240]
Bit Description
0 Reset
0 Do not reset to color of medium [MODCA-C-241]
1 Reset to color of medium [MODCA-C-242]
1–7 Reserved, must be zero
1 M
Note: This triplet is only permitted on Object Area Descriptor structured fields that are contained within a page overlay.
The page overlay itself must be carried within the inline page resource group. If specified on any other Object Area
Descriptor structured field, a X'01' exception condition exists.
Note: If the presentation text Object Area Descriptor structured field appears in the AEG, the measurement
units and extents specified on it must match those specified on the Page Descriptor structured field, or a
X'01' exception condition exists. If the presentation text Object Area Descriptor structured field is omitted,
the architected default is to use the measurement units and extents specified on the Page Descriptor
structured field for the presentation text object area. Thus, the presentation text object area and the
page are always the same size and points within their respective coordinate systems are always
coincident.
Object Area Position
OBP X'D3AC6B' Object Area Position (See “Object Area Position (OBP)”)
0 X'01'–X'7F' → Object Area Position ID. It must be in the range of 1 to 127. 1 M [MODCA-C-243]
1 X'17' → Length of this repeating group is 23 bytes 1 M [MODCA-C-244]
2–4 Object area origin for X. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay X measurement
units
1 M
5–7 Object area origin for Y . It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay Y measurement
units
1 M
8–11 Object Area orientation, X and Y coordinates. It must be one of the following:
X'0000 2D00' → X=0 degrees, Y=90 degrees
X'2D00 5A00' → X=90 degrees, Y=180 degrees
X'5A00 8700' → X=180 degrees, Y=270 degrees
X'8700 0000' → X=270 degrees, Y=0 degrees
1 M
MO:DCA IS/2


OBP X'D3AC6B' Object Area Position (See “Object Area Position (OBP)”)
12 X'00' → Reserved; must be binary zero 1 M [MODCA-C-245]
13–15 Object content origin for X. It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay X measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay X measurement
units
1 M
16–18 Object content origin for Y . It must be one of the following:
X'000000'–X'001555' → In the range of 0 to 5,461 when using 240 units per
inch for the page or overlay Y measurement units
X'000000'–X'007FFF' → In the range of 0 to 32,767 when using 1440 units
per inch for the page or overlay Y measurement
units
1 M
19–20 X'0000' → Object content orientation, X (0 degrees) 1 M
21–22 X'2D00' → Object content orientation, Y (90 degrees) 1 M
23 Referenced coordinate system. It must be one of the following: [MODCA-C-246]
X'00' → Current coordinate system
X'01' → Page or overlay coordinate system
1 M
Notes:
1. If the presentation text Object Area Position structured field appears in the AEG, the X and Y values for the [MODCA-C-247]
object area origin and the object content origin must be set to zero, or a X'01' exception condition exists. If
the presentation text Object Area Position structured field is omitted, the architected default is to set the X
and Y values for the object area origin and the object content origin to zero. For presentation text, the data
object presentation space origin is positioned coincident with the object content origin. Thus, the
presentation text object presentation space, the presentation text object area, and the page always have
the same origin.
2. If the presentation text OBP appears in the AEG, the object area orientation must be set to X'0000 2D00' [MODCA-C-248]
(0°,90°). If it is omitted, the architected default is to set the object area orientation to X'0000 2D00' (0°,90°).
3. For this interchange set, the values X'00' and X'01' in byte 23 specify the same function since positioning [MODCA-C-249]
with respect to a page segment offset is not part of the interchange set definition. That is, both values
specify that the object area is to be positioned with respect to the including page or overlay coordinate
system.
Page Descriptor
PGD X'D3A6AF' Page Descriptor (See “Page Descriptor (PGD)”)
0–1 X'0000' → Page measurement units base for X and Y 1 M
2–3 Page measurement units value for X. It must be one of the following:
X'0960' → 2400 units per unit base (240 units per inch)
X'3840' → 14400 units per unit base (1440 units per inch)
1 M
4–5 Page measurement units value for Y . It must be identical to bytes 2–3. 1 M
6–8 Page size in the X direction. It must be one of the following:
X'000001'–X'001555' In the range of 1 to 5,461 when using 240 units per
inch for the page X measurement units
X'000001'–X'007FFF' In the range of 1 to 32,767 when using 1440 units
per inch for the page X measurement units
1 M
MO:DCA IS/2


PGD X'D3A6AF' Page Descriptor (See “Page Descriptor (PGD)”)
9–11 Page size in the Y-direction. It must be one of the following:
X'000001'–X'001555' In the range of 1 to 5,461 when using 240 units per
inch for the page Y measurement units
X'000001'–X'007FFF' In the range of 1 to 32,767 when using 1440 units
per inch for the page Y measurement units
1 M
12–14 X'000000' → Reserved; must be binary zero 1 M
15–17 The following triplet:
Presentation Space Reset Mixing Triplet (See “Presentation Space Reset Mixing
Triplet X'70'”)
1 O
0–1 X'0370' → Triplet length and identifier 1 M
2 Mixing Flags, as follows: [MODCA-C-250]
Bit Description
0 Reset
0 Do not reset to color of medium [MODCA-C-251]
1 Reset to color of medium [MODCA-C-252]
1–7
1 M
Note: This triplet is permitted only Descriptor structured fields that are contained within a page overlay. The
page overlay itself must be carried within the inline page resource group. If specified on any other Page Descriptor
structured field, a X'01' exception condition exists.
Application Note: The IS/1 and IS/2 interchange set definitions limit the page size to 22.75 inches in the X
and Y directions. T o specify a larger page size, 240 units per inch should be specified in the PGD for the
page measurement units. Using a range of 1 to 32,767, this will allow a maximum page size in the X and
Y directions of 136.5 inches, is supported by all IPDS printers, and keeps the complete page
presentation space within the range of two-byte addressing parameters in the IPDS and PTOCA
architectures.
Presentation Text Data
PTX X'D3EE9B' Presentation Text Data (See “Presentation Text Data (PTX)”)
0–n Up to 8,192 bytes of presentation text data as defined by PTOCA PT1
Presentation Text Data Descriptor, Format 2
PTD X'D3B19B' Presentation Text Data Descriptor (See “Presentation Text Data Descriptor (PTD) Format 2” on
page 340)
0–n Presentation text descriptor data as defined by PTOCA
Note: When the PTD is included in the AEG for a page, some AFP print servers require that the measurement
units in the PTD match the measurement units in the Page Descriptor (PGD). It is therefore strongly
recommended that whenever the PTD is included in the AEG, the same measurement units are
specified in both the PTD and PGD.
MO:DCA IS/2


Coexistence Functions
Coexistence functions are objects, structured fields, triplets, and parameters whose function has been
enhanced or superseded by newer functions. In this case, the old and new functions can coexist. New
generators must generate the new functions. New receivers must process the new functions, but may also
continue to process the old functions.
Coexistence Objects
The following objects are coexistence objects:
• AFP page segment [MODCA-C-253]
• IM image [MODCA-C-254]
AFP Page Segment
The AFP page segment is a coexistence resource object that is being superseded by the MO:DCA page
segment. The AFP page segment has the following structure:
Figure 102. AFP Page Segment Structure
Begin Page Segment (BPS, D3A85F)
+ [ ( D3..FB) Image Object (S) ]
+ [ ( D3..7B) IM Image Object (S) ]
[ ( D3..BB) Graphics Object (S) ]
[ ( D3..9B) Presentation Text Object ]
End Page Segment (EPS, D3A95F)
Architecture Note: The PTOCA object with OEG is not supported in the AFP page segment.
Positioning of IM Image Objects in an AFP Page Segment
When an IM image object is included in an AFP page segment, it is always positioned relative to the reference
point defined in the Include Page Segment (IPS) structured field using the offset, in image points, specified in
the Image Output Control (IOC) structured field. This offset is resolved using the units of measure specified in
the Image Input Descriptor (IID) structured field.
Orientation of Objects in an AFP Page Segment
Unless a Line Data Object Position Migration (X'27') triplet is specified for the AFP page segment or for objects
in the page segment, the orientation of the objects in an AFP page segment is always measured with respect
to the including page (X
p,Yp) or overlay (X ol,Yol) coordinate system. For a description of object orientation when
the X'27' triplet is specified, see T able 45.
Positioning of IO Image and Graphics Objects in an AFP Page Segment
When an IO image object or a graphics object is included in an AFP page segment, it is positioned relative to
the page or overlay coordinate system reference point defined in the IPS or relative to the page or overlay
coordinate system origin. This is determined by the Reference Coordinate System parameter in the object's
OBP structured field. The OBP also specifies the offset with respect to either reference point. This offset is
specified in logical units, and if non-zero, must be resolved using the including page or overlay's units of
measure. Because these units of measure are, in general, not known when the page segment is created, using
Coexistence Functions [MODCA-C-255]


non-zero offsets can lead to unpredictable object positioning and is strongly discouraged. A MO:DCA page
segment or an overlay should be generated to avoid these positioning problems.
Font Mapping for Graphics Objects in an AFP Page Segment
The OEG of a graphics object may not contain any MCF structured fields.
Text Objects in an AFP Page Segment
If an AFP page segment contains text, the following rules apply:
• T ext suppressions specified for the including page or overlay also apply to text in the page segment if the [MODCA-C-256]
suppression local IDs are the same.
• The Absolute Move Baseline (AMB) and Absolute Move Inline (AMI) PTOCA control sequences are [MODCA-C-257]
processed relative to the origin of the including page or overlay coordinate system.
• The Relative Move Baseline (RMB) and Relative Move Inline (RMI) PTOCA control sequences are [MODCA-C-258]
processed relative to the reference point defined on the including page or overlay coordinate system by the
IPS when these control sequences occur first in the text object.
• Fonts used in the text object must be mapped in the AEG of the including page or overlay. If the text object [MODCA-C-259]
does not explicitly specify a font using the Set Coded Font Local (SCFL) control sequence, the font that is
currently active on the including page or overlay is used. Because this font is, in general, not known when the
page segment is created, including a text object that does not explicitly specify a font can lead to
unpredictable text presentation and is strongly discouraged.
• AFP print servers initialize the following PTOCA control sequences as shown prior to processing a text object [MODCA-C-260]
in an AFP page segment:
Control Sequence Value
Set Baseline Increment 6 lines per inch
Set Inline Margin 0
Set Intercharacter Adjustment 0
Set Text Color X'FFFF' (printer default color)
Set Text Orientation 0°,90°
The initial print position for text in the page segment is the reference point defined on the including page or
overlay coordinate system by the IPS.
Architecture Note: In non-MO:DCA data streams that contain a mixture of structured fields and line data, an
IPS offset set to (X'FFFFFF') indicates that the position defined by the current Line Descriptor (LND) is
to be used as the reference point for the IPS.
IM Image Object
An IM image data object specifies the contents of a raster image and its placement on a page, overlay, or page
segment. An IM image can be either simple or complex. A simple image is composed of one or more Image
Raster Data (IRD) structured fields that define the raster pattern for the entire image. A complex image is
divided into regions called image cells. Each image cell is composed of one or more IRD structured fields that
define the raster pattern for the image cell, and one Image Cell Position (ICP) structured field that defines the
position of the image cell relative to the origin of the entire image. Each ICP also specifies the size of the image
cell and a fill rectangle into which the cell is replicated. An example of a simple image and a complex image is
shown in Figure 103.
The IM image object is a valid MO:DCA object, but has been superseded by the IOCA image object. This
object may appear in MO:DCA structures wherever the IOCA image object may appear. New MO:DCA
generators must generate IO image objects instead of IM image objects. New MO:DCA receivers can continue
to receive and process IM image objects. The same MO:DCA document can contain both types of objects.
Coexistence Functions [MODCA-C-261]


This provides upward compatible growth for applications to take advantage of the expanded functions offered
by IO Image objects: data compression, image scaling, and resolution-independent output mappings.
Figure 103. Two Forms of IM Image
In the description of the IM image structured fields that follow, the X-direction, unless otherwise qualified, is the
direction in which image points are added to a scan line. The image width is measured in the X-direction. The
Y-direction, unless otherwise qualified, is the direction in which scan lines are added to the image. The image
height is measured in the Y-direction.
IM Image Object Structure
The structure of an IM image data object is defined as follows using the notation conventions defined in
Chapter 4, “MO:DCA Objects”,.
Figure 104. IM Image Object Structure: Simple (Non-celled) Image
Begin IM Image Object (BII, D3A87B)
(IOC, D3A77B) IM Image Output Control
(IID, D3A67B) IM Image Input Descriptor
(IRD, D3EE7B) IM Image Raster Data (S)
End IM Image Object (EII, D3A97B)
Coexistence Functions [MODCA-C-262]


Figure 105. IM Image Object Structure: Complex (Celled) Image
Begin IM Image Object (BII, D3A87B)
(IOC, D3A77B) IM Image Output Control
(IID, D3A67B) IM Image Input Descriptor
( D3..7B) IM Image Cell (S)
End IM Image Object (EII, D3A97B)
IM Image Cell
(ICP, D3AC7B) IM Image Cell Position
(IRD, D3EE7B) IM Image Raster Data (S)
IM Image Structured Fields
The following IM Image structured fields are described under “Coexistence Structured Fields”:
• Begin IM Image Object [MODCA-C-263]
• End IM Image Object [MODCA-C-264]
• IM Image Cell Position [MODCA-C-265]
• IM Image Input Descriptor [MODCA-C-266]
• IM Image Output Control [MODCA-C-267]
• IM Image Raster Data [MODCA-C-268]
Coexistence Structured Fields
The following structured fields are provided in two formats:
• Map Coded Font (MCF) [MODCA-C-269]
• Page Position (PGP) [MODCA-C-270]
• Presentation T ext Descriptor (PTD) [MODCA-C-271]
MCF structured fields are called MCF Format 1 and MCF Format 2. PGP structured fields are called PGP
Format 1 and PGP Format 2. PTD structured fields are called PTD Format 1 and PTD Format 2. An obsolete
name for the PTD Format 1 is Composed T ext Descriptor (CTD).
MO:DCA receivers may continue to receive and process format-1 structured fields. New MO:DCA generators
must generate only format-2 versions of these structured fields.
Application Note: The Format 1 version of these structured fields is supported by current AFP data stream
applications; but Format 2 is the designated format that is to be used by new AFP applications. IBM print
servers accept both Format 1 and format 2 structured fields. If both MCF Format 1 and MCF Format 2
structured fields are present in the same environment group, IBM print servers require that the MCF
Format 1 structured fields precede the MCF Format 2 structured fields.
The following structured fields are described in this section because they are used by a coexistence object, the
IM Image object:
• Begin IM Image Object (BII) [MODCA-C-272]
• End IM Image Object (EII) [MODCA-C-273]
• IM Image Cell Position (ICP) [MODCA-C-274]
• IM Image Input Descriptor (IID) [MODCA-C-275]
• IM Image Output Control (IOC) [MODCA-C-276]
• IM Image Raster Data (IRD) [MODCA-C-277]
Coexistence Functions [MODCA-C-278]


Map Coded Font (MCF-1) Format 1
The Map Coded Font Format 1 structured field identifies the correspondence between external font names and
resource local identifiers.
A font is specified either with the name for a coded font or with a pair of names for the code page and font
character set. For a double-byte font, a coded font name is specified, or each coded font section is specified by
a code page and font character set pair.
MCF-1 (X'D3B18A') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3B18A' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-279]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-280]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-281]|
| 0 | UBIN | RGLength | | X'1C', X'1E' Length of each repeating group | M | X'06' [MODCA-C-282]|
| 1–3 | Reserved; | must | | be zero | M | X'04' Zero or more repeating groups in the following format: [MODCA-C-283]|
| 0 | UBIN | CFLid | | X'01'–X'7F', X'FE' Coded font local ID | M | X'06' [MODCA-C-284]|
| 1 | Reserved; | must | | be zero | M | X'04' [MODCA-C-285]|
| 2 CODE Sectid X'00', X'41'–X'FE' Coded font section ID: | | | | | | X'00' Single-byte coded font X'41'– X'FE' Double-byte coded font M X'04' [MODCA-C-286]|
| 3 | Reserved; | must | | be zero | M | X'04' [MODCA-C-287]|
| 4–11 | CHAR | CFName | | Coded font name | M | X'04' [MODCA-C-288]|
| 12–19 | CHAR | CPName | | Code page name | M | X'06' [MODCA-C-289]|
| 20–27 | CHAR | FCSName | | Font character set name | M | X'06' [MODCA-C-290]|
| 28–29 CODE CharRot X'0000', X'2D00', | | | | | | X'5A00', X'8700' Character rotation for font: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees O X'02' MCF-1 Semantics RGLength Length of each repeating group. Set to 28 if no character rotation is specified; set to 30 if character rotation is specified. CFLid Coded font local ID. The value must be from 1 to 127. A value of 254 may be used when the MCF-1 structured field is included in the Active Environment Group of a page or overlay for resource management purposes. When a local ID is mapped to a single-byte coded font, or when it is mapped to a double-byte coded font identified with a coded font name, the local ID must be unique across all repeating groups. When a local ID is mapped to a double-byte coded font section, the same local ID must be used to map all sections of the double-byte coded font, and the repeating groups must be contiguous and in ascending order by section number. Coexistence Functions [MODCA-C-291]|


Architecture Note: A unique local ID must be mapped for each character rotation of a font.
Sectid Coded font section ID. For a single-byte coded font, only one section ID can be specified and
must be X'00'. For a double-byte coded font that is identified using a coded font name, the
sections are specified in the font resource object, and the section ID in the MCF-1 repeating
group should be set to X'00'. For a double-byte coded font that is identified using code page
and font character set pairs for each section, this value specifies the coded font section
number (the first byte of each two-byte code point). The value must be from X'41' to X'FE' for
bounded box coded fonts and from X'41' to X'7F' for unbounded box fonts. Each repeating
group with the same font local ID must have a unique coded font section ID, and the section ID
must be greater than the section ID of the previous repeating group.
CFName Coded font name. Specifies the name of the coded font. If the name contains a value of
X'FFFF' in the first two bytes, it is considered to be a null name, and the coded font must be
identified using a code page name and a font character set name. Multiple font local IDs may
be mapped to the same coded font name.
CPName Code page name. Specifies the name of the code page for the single-byte coded font or
double-byte coded font section. If the name contains a value of X'FFFF' in the first two bytes, it
is considered to be a null name, and the coded font must be identified using a coded font
name. In this case, the font character set name must also be specified with a null name. A
code page name can appear in multiple repeating groups coupled with the same font
character set or with a different font character set.
FCSName Font character set name. Specifies the name of the font character set for the single-byte
coded font or double-byte coded font section. If the name contains a value of X'FFFF' in the
first two bytes, it is considered to be a null name, and the coded font must be identified using a
coded font name. In this case, the code page name must also be specified with a null name. A
font character set name can appear in multiple repeating groups coupled with the same code
page or with a different code page.
CharRot Character rotation (optional). Specifies the clockwise character rotation of a font relative to the
character baseline. It must be one of the following:
Value Rotation
X'0000' 0°
X'2D00' 90°
X'5A00' 180°
X'8700' 270°
If the character rotation is not specified, the architected default value for the character rotation
should be X'0000' = zero degrees. However, in practice, most AFP products derive the default
character rotation from the second character of the coded font name or of the font character
set name if the character rotation is not specified. If the first character is either “X”, which
denotes a coded font, or “C”, which denotes a font character set, the second character is used
to determine the character rotation as follows:
0,1,2,3,4 0 degrees
5,6,7,8 90 degrees
9,A,B,C 180 degrees
D,E,F ,G 270 degrees
If the first two characters of the name do not follow this convention, a default character rotation
of X'0000' = zero degrees is assumed.
Application Notes: The character rotation parameter does not exist for unbounded-box fonts,
such as the fonts used by the IBM 3800 printer.
Application Note: In AFP environments, the names specified in this structured field must be encoded using
the conventions defined in “External Resource Naming Conventions”.
Coexistence Functions [MODCA-C-292]


Page Position (PGP-1) Format 1
The Page Position Format 1 structured field specifies the position of a page's presentation space on the
medium presentation space of the physical medium. The page presentation space is oriented so that its X axis,
Xpg is oriented at zero degrees relative to the X m axis of the medium presentation space.
PGP-1 (X'D3ACAF') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3ACAF' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-293]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-294]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-295]|
| 0–2 UBIN X mOset X'0000'–X'7FFF' X m coordinate of page | | | | | | presentation space origin M X'06' [MODCA-C-296]|
| 3–5 UBIN Y mOset X'0000'–X'7FFF' Y m coordinate of page | | | | | | presentation space origin M X'06' PGP-1 Semantics XmOset Offset of the page's presentation space origin along the X m axis of the medium presentation space using the measurement units specified in the Medium Descriptor structured field. YmOset Offset of the page's presentation space origin along the Y m axis of the medium presentation space using the measurement units specified in the Medium Descriptor structured field. Application Note: In AFP environments, the offset range for X mOset and Y mOset is 0 to 5,461 when the medium coordinate system units of measure are 240 units per inch, and 0 to [MODCA-C-297]|
| 32,767 when they are 1440 units per inch. | | | | | | Presentation Text Data Descriptor (PTD-1) Format 1 The Presentation T ext Data Descriptor Format 1 structured field specifies the size of a text object presentation space and the measurement units used for the size and for all linear measurements within the text object. PTD-1 (X'D3A69B') Syntax Structured Field Introducer SF Length (2B) ID = X'D3A69B' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-298]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-299]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-300]|
| 0 CODE XptBase X'00' T ext presentation space unit | | | | | | base for the X axis: X'00' 10 inches M X'06' [MODCA-C-301]|
| 1 CODE YptBase X'00' T ext presentation space unit | | | | | | base for the Y axis: X'00' 10 inches M X'06' Coexistence Functions [MODCA-C-302]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-303]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-304]|
| 2–3 UBIN XptUnits 2400, 14400 T ext presentation space units per | | | | | | unit base for the X axis M X'06' [MODCA-C-305]|
| 4–5 UBIN YptUnits 2400, 14400 T ext presentation space units per | | | | | | unit base for the Y axis M X'06' [MODCA-C-306]|
| 6–7 UBIN XptSize X'0001'–X'7FFF' T ext presentation space extent | | | | | | for the X axis M X'06' [MODCA-C-307]|
| 8–9 UBIN YptSize X'0001'–X'7FFF' T ext presentation space extent | | | | | | for the Y axis M X'06' [MODCA-C-308]|
| 10–11 | Reserved; | must | | be binary zero | O | X'00' PTD-1 Semantics XptBase Specifies the unit base for the X axis of the text presentation space. YptBase Specifies the unit base for the Y axis of the text presentation space. XptUnits Specifies the number of units per unit base for the X axis of the text presentation space. YptUnits Specifies the number of units per unit base for the Y axis of the text presentation space. XptSize Specifies the extent along the X axis of the text presentation space. This must be equal to the extent along the X axis of the including page or overlay presentation space. YptSize Specifies the extent along the Y axis of the text presentation space. This must be equal to the extent along the Y axis of the including page or overlay presentation space. Begin IM Image Object (BII) The Begin IM Image Object structured field begins an IM image data object, which becomes the current data object. BII (X'D3A87B') Syntax Structured Field Introducer SF Length (2B) ID = X'D3A87B' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-309]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-310]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-311]|
| 0–7 CHAR ImoName Name of the IM image data | | | | | | object O X'02' BII Semantics ImoName Is the name of the IM image data object. The page, overlay, or resource group containing the Begin IM Image Object structured field must also contain a subsequent matching End IM Image Object structured field, or a X'08' exception condition exists. Application Note: In AFP environments, the following retired triplet is used on this structured field: Coexistence Functions [MODCA-C-312]|


• Line Data Object Position Migration (X'27') triplet; see “Line Data Object Position Migration Triplet [MODCA-C-313]
X'27'”.
End IM Image Object (EII)
The End IM Image Object structured field terminates the current IM image object initiated by a Begin IM Image
Object structured field.
EII (X'D3A97B') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3A97B' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-314]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-315]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-316]|
| 0–7 CHAR ImoName Name of the IM image data | | | | | | object O X'02' EII Semantics ImoName Is the name of the IM image data object being terminated. If a name is specified, it must match the name in the most recent Begin IM Image Object structured field in the containing page, overlay, or resource group or a X'01' exception condition exists. If the first two bytes of ImoName contain the value X'FFFF', the name matches any name specified on the Begin IM Image Object structured field that initiated the current definition. A matching Begin IM Image Object structured field must appear at some location preceding the End Image Object structured field, or a X'20' exception condition exists. IM Image Cell Position (ICP) The IM Image Cell Position structured field specifies the placement, size, and replication of IM image cells. ICP (X'D3AC7B') Syntax Structured Field Introducer SF Length (2B) ID = X'D3AC7B' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-317]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-318]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-319]|
| 0–1 | UBIN | XCOset | | X'0000'–X'7FFF' Offset of image cell in X direction | M | X'06' [MODCA-C-320]|
| 2–3 | UBIN | YCOset | | X'0000'–X'7FFF' Offset of image cell in Y direction | M | X'06' [MODCA-C-321]|
| 4–5 | UBIN | XCSize | | X'0001'–X'7FFF' Size of image cell in X direction | M | X'06' X'FFFF' Use default X-extent in IID [MODCA-C-322]|
| 6–7 | UBIN | YCSize | | X'0001'–X'7FFF' Size of image cell in Y direction | M | X'06' Coexistence Functions [MODCA-C-323]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-324]|
| --- | --- | --- | --- | --- | --- | --- X'FFFF' Use default Y-extent in IID [MODCA-C-325]|
| 8–9 | UBIN | XFilSize | | X'0001'–X'7FFF' Size of fill rectangle in X direction | M | X'06' X'FFFF' Use image cell X-extent [MODCA-C-326]|
| 10–11 | UBIN | YFilSize | | X'0001'–X'7FFF' Size of fill rectangle in Y direction | M | X'06' X'FFFF' Use image cell Y-extent ICP Semantics XCOset Specifies the offset along the X p direction, in image points, of this image cell from the IM image object area origin. YCOset Specifies the offset along the Y p direction, in image points, of this image cell from the IM image object area origin. XCSize Specifies the extent in the X direction, in image points, of this image cell. A value of X'FFFF' indicates that the default extent specified in bytes 28–29 of the Image Input Descriptor (IID) is to be used. YCSize Specifies the extent in the Y direction, in image points, of this image cell. A value of X'FFFF' indicates that the default extent specified in bytes 30–31 of the Image Input Descriptor (IID) is to be used. XFilSize Specifies the extent of the fill rectangle in the X direction, in image points. This value can be smaller than, equal to, or larger than the image cell extent in the X direction (XCSize). A value of X'FFFF' indicates that the image cell X-extent should be used as the fill rectangle X-extent. The fill rectangle is filled in the X direction by repeating the image cell in the X direction. The image cell can be truncated to fit the rectangle. YFilSize Specifies the extent of the fill rectangle in the Y direction, in image points. This value can be smaller than, equal to, or larger than the image cell extent in the Y direction (YCSize). A value of X'FFFF' indicates that the image cell Y-extent should be used as the fill rectangle Y-extent. The fill rectangle is filled in the Y direction by repeating the image cell in the Y direction. The image cell can be truncated to fit the rectangle. IM Image Input Descriptor (IID) The IM Image Input Descriptor structured field contains the descriptor data for an IM image data object. This data specifies the resolution, size, and color of the IM image. IID (X'D3A67B') Syntax Structured Field Introducer SF Length (2B) ID = X'D3A67B' Flags (1B) Reserved X'0000' Structured Field Data [MODCA-C-327]|
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-328]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-329]|
| 0–11 | CODE | ConData1 | | Constant data | M | X'06' [MODCA-C-330]|
| 12 CODE XBase X'00' Unit base for the image X axis: | | | | | | X'00' 10 inches M X'06' Coexistence Functions [MODCA-C-331]|


| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-332]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-333]|
| 13 CODE YBase X'00' Unit base for the image Y axis: | | | | | | X'00' 10 inches M X'06' [MODCA-C-334]|
| 14–15 UBIN XUnits 1–32,767 Image points per unit base for the | | | | | | image X axis M X'06' [MODCA-C-335]|
| 16–17 UBIN YUnits 1–32,767 Image points per unit base for the | | | | | | image Y axis M X'06' [MODCA-C-336]|
| 18–19 | UBIN | XSize | | X'0001'–X'7FFF' Size of image in X direction | M | X'06' [MODCA-C-337]|
| 20–21 | UBIN | YSize | | X'0001'–X'7FFF' Size of image in Y direction | M | X'06' [MODCA-C-338]|
| 22–27 | CODE | ConData2 | | Constant data | M | X'06' [MODCA-C-339]|
| 28–29 UBIN XCSizeD X'0000'–X'7FFF' Default size of image cell in X | | | | | | direction M X'06' [MODCA-C-340]|
| 30–31 UBIN YCSizeD X'0000'–X'7FFF' Default size of image cell in Y | | | | | | direction M X'06' [MODCA-C-341]|
| 32–33 | CODE | ConData3 | | Constant data | M | X'06' [MODCA-C-342]|
| 34–35 CODE Color See IID | | | | | | Semantics for details Image color M X'06' IID Semantics ConData1 Constant data. Must be set to X'0000 0960 0960 0000 0000 0000'. XBase Specifies the unit base for the X axis of the image. YBase Specifies the unit base for the Y axis of the image. XUnits Specifies the number of image points per unit base for the X axis of the image. This value is ten times the resolution of the image in the X direction. YUnits Specifies the number of image points per unit base for the Y axis of the image. This value is ten times the resolution of the image in the Y direction. XSize Specifies the extent in the X direction, in image points, of an non-celled (simple) image. YSize Specifies the extent in the Y direction, in image points, of an non-celled (simple) image. ConData2 Constant data. Must be set to X'0000 0000 2D00'. XCSizeD Specifies the default extent in the X direction, in image points, of the image cell. This value is used if the IM Image Cell Position (ICP) structured field does not specify the image cell X extent in bytes 4–5. This value must be set to X'0000' for non-celled images. YCSizeD Specifies the default extent in the Y direction, in image points, of the image cell. This value is used if the IM Image Cell Position (ICP) structured field does not specify the image cell Y extent in bytes 6–7. This value must be set to X'0000' for non-celled images. ConData3 Constant data. Must be set to X'0001'. Color Specifies the color of the image. Syntactically valid values for specifying colors are X'0000' through X'0010' and X'FF00' through X'FF08', which is the range of values defined in the Standard OCA Color Value T able. For a complete description of this table, see “Standard OCA Color Value T able”. An additional valid value for IM image is X'FFFF'— presentation process default. Architecture Note: The value X'FFFF' is not a valid color value for IM image in IPDS environments. Coexistence Functions [MODCA-C-343]|


IM Image Output Control (IOC)
The IM Image Output Control structured field specifies the position and orientation of the IM image object area
and the mapping of the image points to presentation device pels.
IOC (X'D3A77B') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3A77B' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-344]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-345]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-346]|
| 0–2 | UBIN | XoaOset | | 0–32,767 X-axis origin of the object area | M | X'06' [MODCA-C-347]|
| 3–5 | UBIN | YoaOset | | 0–32,767 Y-axis origin of the object area | M | X'06' [MODCA-C-348]|
| 6–7 CODE XoaOrent X'0000', X'2D00', | | | | | | X'5A00', X'8700' The object area's X-axis rotation from the X axis of the reference coordinate system: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' [MODCA-C-349]|
| 8–9 CODE YoaOrent X'0000', X'2D00', | | | | | | X'5A00', X'8700' The object area's Y-axis rotation from the X axis of the reference coordinate system: X'0000' 0 degrees X'2D00' 90 degrees X'5A00' 180 degrees X'8700' 270 degrees M X'06' Note: See “IOC Semantics” for valid combinations of the XoaOrent and YoaOrent values. [MODCA-C-350]|
| 10–17 | CODE | ConData1 | | Constant data | M | X'06' [MODCA-C-351]|
| 18–19 CODE XMap X'03E8', X'07D0' Image mapping in X direction: | | | | | | X'03E8' Image point-to-pel X'07D0' Image point-to-two pel (double-dot) M X'06' [MODCA-C-352]|
| 20–21 CODE YMap X'03E8', X'07D0' Image mapping in Y direction: | | | | | | X'03E8' Image point-to-pel X'07D0' Image point-to-two pel (double-dot) M X'06' [MODCA-C-353]|
| 22–23 | CODE | ConData2 | | Constant data | M | X'06' IOC Semantics XoaOset Specifies the offset, along the X-axis, of the IM image object area origin to the origin of the including page or overlay coordinate system. If the IM image object is contained in a page segment, specifies the offset, along the X-axis, of the IM image object area origin to the reference point on the including page or overlay coordinate system defined by the Include Page Segment (IPS) structured field. The offset is specified in image points and is resolved using the units of measure specified for the image in the IID structured field. Coexistence Functions [MODCA-C-354]|


YoaOset Specifies the offset, along the Y axis, of the IM image object area origin to the origin of the
including page or overlay coordinate system. If the IM image object is contained in a page
segment, specifies the offset, along the Y-axis, of the IM image object area origin to the
reference point on the including page or overlay coordinate system defined by the Include
Page Segment (IPS) structured field. The offset is specified in image points and is resolved
using the units of measure specified for the image in the IID structured field.
XoaOrent Specifies the amount of clockwise rotation of the IM image object area's X axis about its
defined origin relative to the X axis of the page or overlay coordinate system.
YoaOrent Specifies the amount of clockwise rotation of the IM image object area's Y axis about its
defined origin relative to the Y axis of the page or overlay coordinate system. The YoaOrent
value must be 90 degrees greater than the XoaOrent value or a X'01' exception condition
exists.
Note: The following combinations of values are the only ones valid for the XoaOrent and
YoaOrent parameters:
Table 46. IOC: Valid Values for XoaOrent and YoaOrent
XoaOrent YoaOrent Description
X'0000' X'2D00' 0 and 90 degrees respectively
X'2D00' X'5A00' 90 and 180 degrees respectively
X'5A00' X'8700' 180 and 270 degrees respectively
X'8700' X'0000' 270 and 0 degrees respectively
Note: When a complex image is rotated, each cell must be repositioned and rotated.
Application Note: The XoaOrent and YoaOrent values do not affect the placement of image
cell origins. Image cell origins can be expressed only in the Xp, Yp coordinate system.
When the orientation of a complex (celled) image is changed, the image cell origins
must be recalculated so that the appearance of the image is preserved. T o simplify the
processing of image rotation, it is recommended that the orientation of complex images
always be (0, 90).
ConData1 Constant data. Must be set to X'0000 0000 0000 0000'.
XMap Specifies mapping of image points to presentation device pels in the X direction. This value
must match the value for YMap.
Value Description
X'03E8' Map an image point to a single presentation device pel in the X direction of
the IM image object area
X'07D0' Map an image point to two presentation device pels in the X direction of the
IM image object area (double-dot)
YMap Specifies mapping of image points to presentation device pels in the Y direction. This value
must match the value for XMap.
Value Description
X'03E8' Map an image point to a single presentation device pel in the Y direction of
the IM image object area
X'07D0' Map an image point to two presentation device pels in the Y direction of the
IM image object area (double-dot)
Coexistence Functions [MODCA-C-355]


Note: If the double-dot function is specified for a complex (celled) image, this function is
performed before the cells are used to populate the fill rectangle and before any
truncation occurs to fit the cell into the rectangle.
ConData2 Constant data. Must be set to X'FFFF'.
IM Image Raster Data (IRD)
The IM Image Raster Data structured field contains the image points that define the raster pattern for an IM
image data object.
IRD (X'D3EE7B') Syntax
Structured Field Introducer
SF Length (2B) ID = X'D3EE7B' Flags (1B) Reserved
X'0000'
Structured Field Data [MODCA-C-356]
| Offset | Type | Name | Range | Meaning | M/O | Exc [MODCA-C-357]|
| --- | --- | --- | --- | --- | --- | --- [MODCA-C-358]|
| 0–n UNDF IMdata Up to 32,759 bytes of IM image | | | | | | raster data O X'00' IRD Semantics IMdata Contains the image points that define the IM image raster pattern. A raster pattern is the array of presentation device pels that forms the image. The image data is uncompressed. Bits are grouped into bytes and are ordered from left to right within each byte. Each bit in the image data represents an image point and is mapped to presentation device pels as specified in the IOC structured field. A bit with value B'1' indicates a significant image point; a bit with value B'0' indicates an insignificant image point. Image points are recorded from left to right in rows that represents scan lines (X direction), and rows representing scan lines are recorded from top to bottom (Y direction). When the image is presented, all image points in a row are presented before any image points in the next sequential row are presented, and all rows have the same number of image points. If the total number of image points is not a multiple of 8, the last byte of the image data is padded to a byte boundary. The padding bits do not represent image points and are ignored by presentation devices. Architecture Note: The presentation environment determines how to map significant image points and insignificant image points to presentation device pels. For example, some printers map significant image points to toned pels and insignificant image points to untoned pels. Coexistence Triplets None. Coexistence Parameters The following parameters are coexistence parameters: • Triplet X'04' mapping option X'41': image point-to-pel • Triplet X'04' mapping option X'42': image point-to-pel with double dot Coexistence Functions [MODCA-C-359]|


• Triplet X'04' mapping option X'50': replicate and trim [MODCA-C-360]
Triplet X'04' Mapping Option X'41': Image Point-to-Pel
This mapping is supported for IOCA FS10 for the migration of IM image objects. It provides a mapping for the
IOCA FS10 image object similar to the mapping defined for the IM image object. The origin of the IOCA FS10
presentation space is positioned at the origin of the object area. Each image point in the presentation space is
mapped to a presentation device pel. Any portion of the image that falls outside the object area is trimmed.
Architecture Note: Resolution correction is not required with this mapping. Therefore, the size of the image
presented in the object area is dependent on the pel resolution of the presentation device.
Triplet X'04' Mapping Option X'42': Image Point-to-Pel with Double Dot
This mapping is supported for IOCA FS10 for the migration of IM image objects. It provides a mapping for the
IOCA FS10 image object similar to that defined for the IM image object. The origin of the IOCA FS10
presentation space is positioned at the origin of the object area. Each image point in the presentation space is
doubled in both directions, resulting in four new image points. The four new image points are then mapped to
presentation device pels. Any portion of the image that falls outside the object area is trimmed.
Architecture Note: Resolution correction is not required with this mapping; therefore the size of the image
presented in the object area is dependent on the pel resolution of the presentation device.
Triplet X'04' Mapping Option X'50': Replicate and Trim
This mapping is supported for IOCA FS10 for the migration of IM image objects. It provides a function for the
IOCA FS10 image object similar to that defined for the celled IM image object. The IOCA FS10 presentation
space is positioned in the object area so that its origin is coincident with the origin of the object area and its
size is unchanged. The presentation space is then replicated in the X and Y directions of the object area until
the object area is filled. Each new replicate of the presentation space in the X direction is precisely aligned with
the presentation space previously placed in the X direction. Each new replicate of the presentation space in the
Y direction is precisely aligned with the presentation space previously placed in the Y direction. If the last
presentation space in either the X or Y direction fits only partially into the object area, the portion of the
presentation space that falls outside the object area is trimmed. All data that falls within the object area extents
is presented, but data that falls outside of the object area is not presented. When this option is specified, the
data object's content origin specified in the XocaOset and YocaOset parameters in the Object Area Position
structured field is ignored.
Coexistence Functions [MODCA-C-361]


