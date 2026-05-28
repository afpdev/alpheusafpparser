# Appendix G. Retired Architecture
Architecture listed in this appendix has been retired in the sense that generators can stop issuing the self- defining fields. The receivers must not generate the EC-0001 exception on receiving them, but are allowed to ignore them. Receivers that support the retired self defining fields can continue to parse these fields and generate exceptions if the fields are specified out of sequence, or their contents are invalid.
Each section in this Appendix that does not cover a self-defining field has the receiver impact described in the introduction.
Image LUT-ID This optional self-defining field identifies the LUT-ID (LUT) that should be used to interpret the image data.
Each IDE value is an index into this LUT . [IOCA-G-001]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'97' Image LUT-ID parameter M 1 UBIN LENGTH X'01' Length of the parameters to follow | M [IOCA-G-002]|
| 2 | CODE | LUTID | | X'00' – X'FF' LUT-ID identifier M If the Image LUT-ID parameter is not present, the default value for LUTID is zero for the standard | LUT-ID. [IOCA-G-003]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-970F Invalid sequence Condition: The Image LUT-ID parameter appeared out of sequence or more than once.
EC-9710 Invalid or unsupported Image Data parameter value Condition: The Image LUT-ID parameter contains an invalid or unsupported value. [IOCA-G-004]


Image Structured Fields in MO:DCA-L Data Stream MO:DCA-L has been retired and removed from the MO:DCA reference into a new book: MO:DCA-L: The OS/2 Presentation Manager Metafile (.met) Format. IOCA constructs that support MO:DCA-L have been retired.
MO:DCA-L was not used in printing. Encountering data specific for MO:DCA-L and not allowed in MO:DCA should generate an exception.
This section shows the syntax of IDD and IPD in the MO:DCA-L interchange set. An IOCA Image Segment is carried by one or more IPD structured fields.
IDD in MO:DCA-L Data Stream Structured Field Introducer SF Length X'D3A6FB' Flags Sequence Number Data Offset Type Name Range Meaning M/O 0 CODE UNITBASE X'00' – X'01' Unit base:
X'00' 10 inches X'01' 10 centimeters All other values are reserved.
M
1–2 UBIN XRESOL X'0001' – X'7FFF' Horizontal resolution in image points per unit base M 3–4 UBIN YRESOL X'0001' – X'7FFF' Vertical resolution in image points per unit base M 5–6 UBIN XSIZE X'0001' – X'7FFF'
Horizontal size of the Image Presentation Space in image points M 7–8 UBIN YSIZE X'0001' – X'7FFF' Vertical size of the Image Presentation Space in image points
M
IPD in MO:DCA-L Data Stream Structured Field Introducer SF Length X'D3EEFB' Flags Sequence Number IOCA Function Set 20 See “IOCA Function Set 20 (IOCA FS20)” for details.
Note: An IOCA FS20 Image Segment can be split into multiple IPD structured fields. Data beyond the End Segment self-defining field is ignored by receivers.
Retired Architecture


IOCA Function Set 20 (IOCA FS20)
Function Set 20 was not used in printing. If a data stream specifies Function Set 20, products can either ignore it or generate an exception.
Function Set 20 describes bilevel, grayscale, and color images. This function set is carried by the MO:DCA-L controlling environment. The permissible parameter groupings in FS20 are defined as follows:
Table 31. Function Set 20 Structure X'70' Begin Segment parameter X'91' Begin Image Content parameter + X'94' Image Size parameter + [ X'95' Image Encoding parameter ] + [ X'96' IDE Size parameter ] + [ X'97' Retired (Image LUT-ID parameter) ] + [ X'9B' IDE Structure parameter ]
X'FE92' Image Data (S)
X'93' End Image Content parameter X'71' End Segment parameter Its acceptable self-defining fields and parameter values are shown in the following table.
IOCA Self-defining Field Parameter (Bytes)
Acceptable Value Comments Initial parameters:
Begin Segment ID (1) X'70' LENGTH (1) X'00' Begin Image Content ID (1) X'91' LENGTH (1) X'01' OBJTYPE (1) X'FF' IOCA Image Size parameter ID (1) X'94' LENGTH (1) X'09' UNITBASE (1) X'00' – X'01'
HRESOL (2) X'0000' – X'7FFF' VRESOL (2) X'0000' – X'7FFF' HSIZE (2) X'0000' – X'7FFF' VSIZE (2) X'0000' – X'7FFF' Image Encoding parameter ID (1) X'95' LENGTH (1) X'02' COMPRID (1) X'01', X'03', X'82' X'01' IBM MMR-Modified Modified Read (see Note 1)
X'03' No Compression X'82' G4 MMR-Modified Modified READ (see Note 1)
Retired Architecture


IOCA Self-defining Field Parameter (Bytes)
Acceptable Value Comments RECID (1) X'01', X'03' X'01' RIDIC X'03' Bottom-to-T op(see Note 2)
IDE Size parameter ID (1) X'96' LENGTH (1) X'01' IDESZ (1) X'01', X'04', X'08', X'18' X'01' 1 bit/IDE X'04' 4 bits/IDE X'08' 8 bits/IDE X'18' 24 bits/IDE
Notes on the initial parameters:
1. IBM MMR-Modified Modified Read and G4 MMR-Modified Modified READ are applicable only to images whose IDE size is 1 bit/IDE; otherwise exception condition EC-9611 is raised. [IOCA-G-005]
2. Bottom-to-T opis used only in conjunction with No Compression; otherwise exception condition EC-9510 is raised. [IOCA-G-006]
Parameters used when IDESZ=1:
Retired RESERVED (3) X'970100', X'970101' Retired Image LUT-ID parameter Parameters used when IDESZ=4 or IDESZ=8:
Retired RESERVED (3) X'970101' Retired Image LUT-ID parameter Parameters used when IDESZ=24:
Retired RESERVED (3) X'970100' Retired Image LUT-ID parameter IDE Structure parameter ID (1) X'9B' LENGTH (1) X'08' FLAGS (1) X'00' Additive and No gray coding FORMAT (1) X'01' RGB RESERVED (3) X'000000' Should be zero SIZE1 (1) X'08' 8 bits of the IDE for the R component SIZE2 (1) X'08' 8 bits of the IDE for the G component
SIZE3 (1) X'08' 8 bits of the IDE for the B component Final parameters:
Image Data ID (2) X'FE92' LENGTH (2) X'0001' – X'FFFF' DATA Any IDEs End Image Content ID (1) X'93' LENGTH (1) X'00' End Segment ID (1) X'71' LENGTH (1) X'00' Retired Architecture [IOCA-G-007]


Exception Condition EC-0002 Exception condition EC-0002 was an optional exception that, based on the definition of reserved fields, should never be returned by a receiver. Its definition was as follows:
EC-0002 Reserved bits or bytes are not zeros Condition: Reserved bits or bytes are not zeros in the Image Data parameter within the Image Segment.
Note: This exception condition is optional.
Retired Architecture




Notices The AFP Consortium or consortium member companies might have patents or pending patent applications covering subject matter described in this document. The furnishing of this document does not give you any license to these patents.
The following statement does not apply to the United Kingdom or any other country where such provisions are inconsistent with local law: AFP Consortium PROVIDES THIS PUBLICATION “AS IS” WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF NON-INFRINGEMENT , MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Some states do not allow disclaimer of express or implied warranties in certain transactions, therefore, this statement might not apply to you.
This publication could include technical inaccuracies or typographical errors. Changes are periodically made to the information herein; these changes will be incorporated in new editions of the publication. The AFP Consortium might make improvements and/or changes in the architecture described in this publication at any time without notice.
Any references in this publication to Web sites are provided for convenience only and do not in any manner serve as an endorsement of those Web sites. The materials at those Web sites are not part of the materials for this architecture and use of those Web sites is at your own risk.
The AFP Consortium may use or distribute any information you supply in any way it believes appropriate without incurring any obligation to you.
This information contains examples of data and reports used in daily business operations. T o illustrate them in a complete manner, some examples include the names of individuals, companies, brands, or products. These names are fictitious and any similarity to the names and addresses used by an actual business enterprise is entirely coincidental. [IOCA-G-008]


Trademarks These terms are trademarks or registered trademarks of Adobe Systems Incorporated in the United States, in other countries, or both:
Acrobat Adobe Photoshop PostScript AFPC and AFP Consortium are trademarks of the AFP Consortium.
These terms are trademarks or registered trademarks of Hewlett-Packard Company in the United States, in other countries, or both:
Hewlett-Packard PCL These terms are trademarks or registered trademarks of the International Business Machines Corporation in the United States, in other countries, or both:
IBM
MVS
PANTONE is a registered trademark of Pantone LLC.
These terms are trademarks or registered trademarks of Ricoh Co., Ltd., in the United States, in other countries, or both:
ACMA
Advanced Function Presentation AFP AFPCC AFP Color Consortium AFP Color Management Architecture Bar Code Object Content Architecture BCOCA
CMOCA
Color Management Object Content Architecture InfoPrint Intelligent Printer Data Stream IPDS Mixed Object Document Content Architecture MO:DCA Ricoh Other company, product, or service names might be trademarks or service marks of others. [IOCA-G-009]


