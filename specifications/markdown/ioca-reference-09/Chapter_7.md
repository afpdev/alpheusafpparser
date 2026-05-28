# Chapter 7. Compliance
This chapter:
• Describes the concept of IOCA function sets [IOCA-7-001]
• Lists the product compliance rules [IOCA-7-002]
• Defines IOCA Function Sets FS10, FS11, FS14, FS40, FS42, FS45, and FS48 [IOCA-7-003]
## Function Sets
A function set is a set of self-defining fields that describes an Image Object. Specifically, it is a definition of the Image Segment: which parameters the Image Segment should consist of, and what values each parameter should have. The Image Object described in the function set can thus be processed in different controlling environments.
Each function set has an identification. With that identification, products determine the level of support they must provide to generate or receive IOCA Image Objects. [IOCA-7-004]

Table 6. Function Set Identification [IOCA-7-005]

| ID | Description | Function Sets Currently Defined |
| :--- | :--- | :--- |
| 0x | Stand-alone [IOCA-7-006]| |
| 1x | Carried by presentation-level data stream, non-tiled | FS10, FS11, FS14 [IOCA-7-007]|
| 2x | Library/resource | FS20 (Retired) [IOCA-7-008]|
| 3x | Reserved [IOCA-7-009]| |
| 4x | Carried by presentation-level data stream, tiled | FS40, FS42, FS45, FS48 [IOCA-7-010]|

Note: x is generally assigned in ascending numerical order from zero.
IOCA defines seven function sets: FS10, FS11, FS14, FS40, FS42, FS45, and FS48.
• Function Set 10 is intended for bilevel images. [IOCA-7-011]
• Function Set 11 covers bilevel, grayscale, and color images. [IOCA-7-012]
• Function Set 14 covers bilevel, grayscale, and color images that allow use of transparency masks. [IOCA-7-013]
• Function Set 40 covers tiled bilevel images. [IOCA-7-014]
• Function Set 42 covers tiled bilevel images and tiled CMYK images with one bit per spot (SIZE1=SIZE2= [IOCA-7-015]
SIZE3=SIZE4=1, IDESZ=4).
• Function Set 45 carries tiled bilevel and CMYK images. CMYK images can be either one or eight bits per [IOCA-7-016]
spot (IDESZ=4 or IDESZ=32).
• Function Set 48 carries tiled bilevel, CMYK, and nColor images. CMYK images can be either one or eight [IOCA-7-017]
bits per spot (IDESZ=4 or IDESZ=32). nColor images are eight bits per spot (IDESZ is a multiple of eight).
Note: Function Set 20 is used only in MO:DCA-L and has been retired. See Appendix G, “Retired Architecture”.
Function Set 14 is a superset of Function Set 11. Function Set 11 is a superset of Function Set 10. Function Set 48 is a superset of Function Set 45. Function Set 45 is a superset of Function Set 42. Function Set 42 is a superset of Function Set 40. There are no other relationships among the function sets. [IOCA-7-018]


Products that conform to an IOCA function set must meet the following criteria:
• Products that generate IOCA Image Objects can only use the IOCA self-defining fields and parameter values [IOCA-7-019]
that are defined in the corresponding Function Set definition.
• Products that receive IOCA Image Objects must be capable of accepting any IOCA Image Object that [IOCA-7-020]
conforms to the corresponding Function Set definition.
The following sections show the self-defining fields that each function set contains and the acceptable values for each field. [IOCA-7-021]
## Function Sets


IOCA Function Set 10 (IOCA FS10)
Function Set 10 describes bilevel images. This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS10 are defined as follows: [IOCA-7-022]

Table 7. Function Set 10 Structure [IOCA-7-023]

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-024]|
| X'91' | Begin Image Content parameter [IOCA-7-025]|
| + X'94' | Image Size parameter [IOCA-7-026]|
| + [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-027]|
| + [ X'96' ] | [ IDE Size parameter ] [IOCA-7-028]|
| + [ X'97' ] | [ Retired (Image LUT-ID parameter) ] [IOCA-7-029]|
| X'FE92' | Image Data (S) [IOCA-7-030]|
| X'93' | End Image Content parameter [IOCA-7-031]|
| X'71' | End Segment parameter [IOCA-7-032]|

The self-defining fields and values acceptable for FS10 are shown in the following table. [IOCA-7-033]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| Begin Segment | ID (1) | X'70' [IOCA-7-034]| |
| | LENGTH (1) | X'00' [IOCA-7-035]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-036]| |
| | LENGTH (1) | X'01' [IOCA-7-037]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-038]|
| Image Size | ID (1) | X'94' [IOCA-7-039]| |
| | LENGTH (1) | X'09' [IOCA-7-040]| |
| | UNITBASE (1) | X'00' – X'02' [IOCA-7-041]| |
| | HRESOL (2) | X'0000' – X'7FFF' [IOCA-7-042]| |
| | VRESOL (2) | X'0000' – X'7FFF' [IOCA-7-043]| |
| | HSIZE (2) | X'0000' – X'7FFF' [IOCA-7-044]| |
| | VSIZE (2) | X'0000' – X'7FFF' [IOCA-7-045]| |
| Image Encoding | ID (1) | X'95' [IOCA-7-046]| |
| | LENGTH (1) | X'02' [IOCA-7-047]| |
| | COMPRID (1) | X'01', X'03', X'82' | X'01' IBM MMR<br>X'03' No compression<br>X'82' G4 MMR [IOCA-7-048]|
| | RECID (1) | X'01' | RIDIC [IOCA-7-049]|
| IDE Size | ID (1) | X'96' [IOCA-7-050]| |
| | LENGTH (1) | X'01' [IOCA-7-051]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-052]|
| Retired | RESERVED (3) | X'970100' | Retired Image LUT-ID parameter [IOCA-7-053]|
| Image Data | ID (2) | X'FE92' [IOCA-7-054]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-055]| |
| | DATA | Any | IDEs (see Note) [IOCA-7-056]|
| End Image Content | ID (1) | X'93' [IOCA-7-057]| |
| | LENGTH (1) | X'00' [IOCA-7-058]| |
| End Segment | ID (1) | X'71' [IOCA-7-059]| |
| | LENGTH (1) | X'00' [IOCA-7-060]| |

Note: IDE value 0 represents an insignificant image point, and 1 represents a significant image point. The controlling environment determines how to interpret each value. [IOCA-7-061]


IOCA Function Set 11 (IOCA FS11)
Function Set 11 is a superset of Function Set 10, and describes bilevel, grayscale, and color images. This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS11 are defined as follows: [IOCA-7-062]

Table 8. Function Set 11 Structure [IOCA-7-063]

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-064]|
| X'91' | Begin Image Content parameter [IOCA-7-065]|
| + X'94' | Image Size parameter [IOCA-7-066]|
| + [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-067]|
| + [ X'96' ] | [ IDE Size parameter ] [IOCA-7-068]|
| + [ X'97' ] | [ Retired (Image LUT-ID parameter) ] [IOCA-7-069]|
| + [ X'98' ] | [ Band Image parameter ] [IOCA-7-070]|
| + [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-071]|
| + [ X'9F' ] | [ External Algorithm Specification parameter ] [IOCA-7-072]|
| + [ X'FECE' ] | [ Image Subsampling parameter ] [IOCA-7-073]|
| | Image Data or Band Image Data (S) [IOCA-7-074]|
| X'93' | End Image Content parameter [IOCA-7-075]|
| X'71' | End Segment parameter [IOCA-7-076]|

Note: The External Algorithm Specification parameter is part of FS11, but in IOCA is no longer required for JPEG compression, as described in Note 2 in the description of the Image Encoding parameter. Thus, an FS11 receiver can ignore the External Algorithm Specification parameter if desired. [IOCA-7-077]

The self-defining fields and values acceptable for FS11 are shown in the following table. [IOCA-7-078]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters:** [IOCA-7-079]| | | |
| Begin Segment | ID (1) | X'70' [IOCA-7-080]| |
| | LENGTH (1) | X'00' [IOCA-7-081]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-082]| |
| | LENGTH (1) | X'01' [IOCA-7-083]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-084]|
| Image Size | ID (1) | X'94' [IOCA-7-085]| |
| | LENGTH (1) | X'09' [IOCA-7-086]| |
| | UNITBASE (1) | X'00' – X'02' [IOCA-7-087]| |
| | HRESOL (2) | X'0000' – X'7FFF' [IOCA-7-088]| |
| | VRESOL (2) | X'0000' – X'7FFF' [IOCA-7-089]| |
| | HSIZE (2) | X'0000' – X'7FFF' [IOCA-7-090]| |
| | VSIZE (2) | X'0000' – X'7FFF' [IOCA-7-091]| |
| Image Encoding | ID (1) | X'95' [IOCA-7-092]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-093]| |
| | COMPRID (1) | X'01', X'03', X'08', X'0A', X'82', X'83' | X'01' IBM MMR (see Note 1)<br>X'03' No Compression<br>X'08' ABIC (see Note 1)<br>X'0A' Concatenated ABIC (see Note 2)<br>X'82' G4 MMR (see Note 1)<br>X'83' JPEG (see Note 3) [IOCA-7-094]|
| | RECID (1) | X'01' | RIDIC [IOCA-7-095]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-096]|
| IDE Size | ID (1) | X'96' [IOCA-7-097]| |
| | LENGTH (1) | X'01' [IOCA-7-098]| |
| | IDESZ (1) | X'01', X'04', X'08', X'18' | X'01' 1 bit/IDE<br>X'04' 4 bits/IDE<br>X'08' 8 bits/IDE<br>X'18' 24 bits/IDE [IOCA-7-099]|
| External Algorithm Specification | ID (1) | X'9F' [IOCA-7-100]| |
| | LENGTH (1) | X'0A' [IOCA-7-101]| |
| | ALGTYPE (1) | X'10' | Compression algorithm specification [IOCA-7-102]|
| | RESERVED (1) | X'00' | Should be zero [IOCA-7-103]|
| | COMPRID (1) | X'83' | JPEG algorithms [IOCA-7-104]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-105]|
| | MARKER (1) | X'C0' – X'C2', X'C9' – X'CA' | **Non-differential Huffman coding:**<br>X'C0' Baseline DCT<br>X'C1' Extended sequential DCT<br>X'C2' Progressive DCT<br>**Non-differential arithmetic coding:**<br>X'C9' Extended sequential DCT<br>X'CA' Progressive DCT<br>See Note 3 [IOCA-7-106]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-107]|

**Notes on the initial parameters:**
1. ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, and G4 MMR-Modified Modified READ are applicable only to images whose IDE size is 1 bit/IDE, otherwise exception condition EC-9611 is raised. [IOCA-7-108]
2. Concatenated ABIC is applicable only to images whose IDE size is 4 or 8 bits/IDE, otherwise exception condition EC-9611 is raised. [IOCA-7-109]
3. The JPEG baseline DCT-based algorithm is applicable only to images whose IDE size is 8 bits/IDE, while the other DCT-based algorithms are applicable only to images whose IDE size is 8 or 24 bits/IDE; otherwise exception condition EC-9611 is raised. [IOCA-7-110]


| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=1:** [IOCA-7-111]| | | |
| Retired | RESERVED (3) | X'970100' | Retired Image LUT-ID parameter [IOCA-7-112]|
| Band Image (see General Note) | ID (1) | X'98' [IOCA-7-113]| |
| | LENGTH (1) | X'02' [IOCA-7-114]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-115]|
| | BITCNT (1) | X'01' | 1 bit/IDE [IOCA-7-116]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-117]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-118]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-119]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-120]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-121]|
| | SIZE1 (1) | X'01' | 1 bit/IDE [IOCA-7-122]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-123]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-124]|
| Image Subsampling (see General Note) | ID (2) | X'FECE' [IOCA-7-125]| |
| | LENGTH (2) | X'0000', X'0004' [IOCA-7-126]| |
| | ID (1) | X'01' | Sampling ratios [IOCA-7-127]|
| | LENGTH (1) | X'02' [IOCA-7-128]| |
| | HSAMPLE (1) | X'01' [IOCA-7-129]| |
| | VSAMPLE (1) | X'01' [IOCA-7-130]| |


| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-131]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=4 or IDESZ=8:** [IOCA-7-132]| | | |
| Band Image (see General Note) | ID (1) | X'98' [IOCA-7-133]| |
| | LENGTH (1) | X'02' [IOCA-7-134]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-135]|
| | BITCNT (1) | X'04', X'08' | X'04' 4 bits/IDE<br>X'08' 8 bits/IDE [IOCA-7-136]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-137]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-138]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0'–B'1' (see Note 1)<br>RESERVED B'000000' [IOCA-7-139]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb (see Note 2)<br>X'12' YCbCr (see Note 2) [IOCA-7-140]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-141]|
| | SIZE1 (1) | X'04', X'08' | X'04' 4 bits/IDE<br>X'08' 8 bits/IDE [IOCA-7-142]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-143]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-144]|
| Image Subsampling (see General Note) | ID (2) | X'FECE' [IOCA-7-145]| |
| | LENGTH (2) | X'0000', X'0004' [IOCA-7-146]| |
| | ID (1) | X'01' | Sampling ratios [IOCA-7-147]|
| | LENGTH (1) | X'02' [IOCA-7-148]| |
| | HSAMPLE (1) | X'01' [IOCA-7-149]| |
| | VSAMPLE (1) | X'01' [IOCA-7-150]| |

**Notes on parameters used when IDESZ=4 or IDESZ=8:**
1. Gray coding is valid only for the Concatenated ABIC algorithm, otherwise exception condition EC-9B10 is raised. [IOCA-7-151]
2. Grayscale images only. Grayscale IDEs are composed of the Y component only of the YCrCb or YCbCr color model. [IOCA-7-152]


| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=24:** [IOCA-7-153]| | | |
| Band Image (see General Note) | ID (1) | X'98' [IOCA-7-154]| |
| | LENGTH (1) | X'02' [IOCA-7-155]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-156]|
| | BITCNT (1) | X'18' | 24 bits/IDE [IOCA-7-157]|
| or: [IOCA-7-158]| | | |
| Band Image (see General Note) | ID (1) | X'98' [IOCA-7-159]| |
| | LENGTH (1) | X'04' [IOCA-7-160]| |
| | BCOUNT (1) | X'03' | 3 bands: R,G,B or Y,Cr,Cb or Y,Cb,Cr [IOCA-7-161]|
| | BITCNT (1) | X'08' | 8 bits/IDE for R or Y band [IOCA-7-162]|
| | BITCNT (1) | X'08' | 8 bits/IDE for G or Cr or Cb band [IOCA-7-163]|
| | BITCNT (1) | X'08' | 8 bits/IDE for B or Cb or Cr band [IOCA-7-164]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-165]| |
| | LENGTH (1) | X'08' [IOCA-7-166]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-167]|
| | FORMAT (1) | X'01', X'02', X'12' | X'01' RGB<br>X'02' YCrCb<br>X'12' YCbCr [IOCA-7-168]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-169]|
| | SIZE1 (1) | X'08' | 8 bits for R or Y component [IOCA-7-170]|
| | SIZE2 (1) | X'08' | 8 bits for G or Cr or Cb component [IOCA-7-171]|
| | SIZE3 (1) | X'08' | 8 bits for B or Cb or Cr component [IOCA-7-172]|
| Image Subsampling (see General Note) | ID (2) | X'FECE' [IOCA-7-173]| |
| | LENGTH (2) | X'0000', X'0004', X'0006', X'0008' [IOCA-7-174]| |
| | ID (1) | X'01' | Sampling ratios [IOCA-7-175]|
| | LENGTH (1) | X'02', X'04', X'06' [IOCA-7-176]| |
| | HSAMPLE1 (1) | X'01' – X'02' | X'02' for YCrCb/YCbCr only [IOCA-7-177]|
| | VSAMPLE1 (1) | X'01' [IOCA-7-178]| |
| | HSAMPLE2 (1) | X'01' [IOCA-7-179]| |
| | VSAMPLE2 (1) | X'01' [IOCA-7-180]| |
| | HSAMPLE3 (1) | X'01' [IOCA-7-181]| |
| | VSAMPLE3 (1) | X'01' [IOCA-7-182]| |


| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-183]|
| :--- | :--- | :--- | :--- |
| **Final parameters:** [IOCA-7-184]| | | |
| Image Data | ID (2) | X'FE92' [IOCA-7-185]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-186]| |
| | DATA | Any | IDEs [IOCA-7-187]|
| or: [IOCA-7-188]| | | |
| Band Image Data (BCOUNT=1) | ID (2) | X'FE9C' [IOCA-7-189]| |
| | LENGTH (2) | X'0004' – X'FFFF' [IOCA-7-190]| |
| | BANDNUM (1) | X'01' | One band [IOCA-7-191]|
| | RESERVED (2) | X'0000' | Should be zero [IOCA-7-192]|
| | DATA | Any | IDEs [IOCA-7-193]|
| or: [IOCA-7-194]| | | |
| Band Image Data (BCOUNT=3) | ID (2) | X'FE9C' [IOCA-7-195]| |
| | LENGTH (2) | X'0004' – X'FFFF' [IOCA-7-196]| |
| | BANDNUM (1) | X'01' | Band with R or Y component [IOCA-7-197]|
| | RESERVED (2) | X'0000' | Should be zero [IOCA-7-198]|
| | DATA | Any | R or Y component [IOCA-7-199]|
| | ID (2) | X'FE9C' [IOCA-7-200]| |
| | LENGTH (2) | X'0004' – X'FFFF' [IOCA-7-201]| |
| | BANDNUM (1) | X'02' | Band with G, Cr, or Cb component [IOCA-7-202]|
| | RESERVED (2) | X'0000' | Should be zero [IOCA-7-203]|
| | DATA | Any | G, Cr, or Cb component [IOCA-7-204]|
| | ID (2) | X'FE9C' [IOCA-7-205]| |
| | LENGTH (2) | X'0004' – X'FFFF' [IOCA-7-206]| |
| | BANDNUM (1) | X'03' | Band with B, Cb, or Cr component [IOCA-7-207]|
| | RESERVED (2) | X'0000' | Should be zero [IOCA-7-208]|
| | DATA | Any | B, Cb, or Cr component [IOCA-7-209]|
| End Image Content | ID (1) | X'93' [IOCA-7-210]| |
| | LENGTH (1) | X'00' [IOCA-7-211]| |
| End Segment | ID (1) | X'71' [IOCA-7-212]| |
| | LENGTH (1) | X'00' [IOCA-7-213]| |

General note: In this function set, the Image Subsampling parameter and the Band Image parameter cannot coexist within the same Image Content; otherwise exception condition EC-9801 or EC-CE01 is raised. [IOCA-7-214]


### IOCA Function Set 14 (IOCA FS14)

Function Set 14 is a superset of Function Set 10 and Function Set 11, and describes bilevel, grayscale, and color images that allow use of transparency masks, as well as some additional compression algorithm options. [IOCA-7-215]

This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS14 are defined as follows: [IOCA-7-216]

#### Table 9. Function Set 14 Structure

| Self-Defining Field | Description |
| :--- | :--- |
| X'70' | Begin Segment [IOCA-7-217]|
| X'91' | Begin Image Content [IOCA-7-218]|
| X'94' | Image Size [IOCA-7-219]|
| [ X'95' ] | Image Encoding [IOCA-7-220]|
| [ X'96' ] | IDE Size [IOCA-7-221]|
| [ X'97' ] | Retired (Image LUT-ID parameter) (ignored) [IOCA-7-222]|
| [ X'98' ] | Band Image [IOCA-7-223]|
| [ X'9B' ] | IDE Structure [IOCA-7-224]|
| [ X'9F' ] | External Algorithm Specification (ignored) [IOCA-7-225]|
| [ X'FECE' ] | Image Subsampling [IOCA-7-226]|
| [ Transparency Mask ] | See Table 10 [IOCA-7-227]|
| | Image Data or Band Image Data (S) [IOCA-7-228]|
| X'93' | End Image Content [IOCA-7-229]|
| X'71' | End Segment [IOCA-7-230]|

#### Table 10. Transparency Mask Structure

| Self-Defining Field | Description [IOCA-7-231]|
| :--- | :--- |
| X'8E' | Begin Transparency Mask [IOCA-7-232]|
| X'94' | Image Size [IOCA-7-233]|
| [ X'95' ] | Image Encoding [IOCA-7-234]|
| X'FE92' | Image Data [IOCA-7-235]|
| X'8F' | End Transparency Mask [IOCA-7-236]|

**Notes:**
1. The Image LUT-ID parameter has been retired and is thus not used in FS14. However, to keep FS14 a superset of FS11, the parameter will be allowed, but ignored. [IOCA-7-237]
2. The External Algorithm Specification parameter is not needed in FS14, as there are no restrictions on which codings can be used in the JPEG compression. The parameter is thus allowed, but ignored, as in FS45. [IOCA-7-238]

The self-defining fields and values acceptable for FS14 are shown in the following table. [IOCA-7-239]

#### IOCA Self-defining Field Parameter (Bytes) for FS14

| Parameter | ID (Length) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters:** [IOCA-7-240]| | | |
| Begin Segment | X'70' (0) [IOCA-7-241]| | |
| Begin Image Content | X'91' (1) | OBJTYPE X'FF' | IOCA [IOCA-7-242]|
| Image Size | X'94' (9) | UNITBASE X'00'–X'02' [IOCA-7-243]| |
| | | HRESOL (2) X'0000'–X'7FFF' [IOCA-7-244]| |
| | | VRESOL (2) X'0000'–X'7FFF' [IOCA-7-245]| |
| | | HSIZE (2) X'0000'–X'7FFF' [IOCA-7-246]| |
| | | VSIZE (2) X'0000'–X'7FFF' [IOCA-7-247]| |
| Image Encoding | X'95' (2–3) | COMPRID X'01', X'03', X'08', X'0A', X'0D', X'0E', X'82', X'83' | X'01' IBM MMR (see Note 1)<br>X'03' No Compression<br>X'08' ABIC (see Note 1)<br>X'0A' Concatenated ABIC (see Note 2)<br>X'0D' TIFF LZW<br>X'0E' TIFF LZW with Diff Predictor<br>X'82' G4 MMR (see Note 1)<br>X'83' JPEG algorithms (see Note 3) [IOCA-7-248]|
| | | RECID (1) X'01' | RIDIC [IOCA-7-249]|
| | | BITORDR (1) X'00'–X'01' | X'00' Left to right<br>X'01' Right to left [IOCA-7-250]|
| IDE Size | X'96' (1) | IDESZ (1) X'01', X'04', X'08', X'18' | X'01' 1 bit/IDE<br>X'04' 4 bits/IDE<br>X'08' 8 bits/IDE<br>X'18' 24 bits/IDE [IOCA-7-251]|
| **Parameters used when IDESZ=1:** [IOCA-7-252]| | | |
| Retired | X'97' (1) | X'00' | Retired Image LUT-ID parameter [IOCA-7-253]|
| Band Image | X'98' (2) | BCOUNT (1) X'01' | One band [IOCA-7-254]|
| | | BITCNT (1) X'01' | 1 bit/IDE [IOCA-7-255]|
| IDE Structure | X'9B' (6–8) | FLAGS (1) | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-256]|
| | | FORMAT (1) X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-257]|
| | | RESERVED (3) X'000000' [IOCA-7-258]| |
| | | SIZE1 (1) X'01' | 1 bit/IDE [IOCA-7-259]|
| | | SIZE2 (1) X'00' | 0 bits/IDE [IOCA-7-260]|
| | | SIZE3 (1) X'00' | 0 bits/IDE [IOCA-7-261]|
| Image Subsampling | X'FECE' (2, 4) | ID (1) X'01' | Sampling ratios [IOCA-7-262]|
| | | LENGTH (1) X'02' [IOCA-7-263]| |
| | | HSAMPLE (1) X'01' [IOCA-7-264]| |
| | | VSAMPLE (1) X'01' [IOCA-7-265]| |
| **Parameters used when IDESZ=4 or IDESZ=8:** [IOCA-7-266]| | | |
| Band Image | X'98' (2) | BCOUNT (1) X'01' | One band [IOCA-7-267]|
| | | BITCNT (1) X'04', X'08' | X'04' 4 bits/IDE<br>X'08' 8 bits/IDE [IOCA-7-268]|
| IDE Structure | X'9B' (6–8) | FLAGS (1) | ASFLAG B'0' Additive<br>GRAYCODE B'0'–B'1' (Note 1)<br>RESERVED B'000000' [IOCA-7-269]|
| | | FORMAT (1) X'02', X'12' | X'02' YCrCb (Note 2)<br>X'12' YCbCr (Note 2) [IOCA-7-270]|
| | | RESERVED (3) X'000000' [IOCA-7-271]| |
| | | SIZE1 (1) X'04', X'08' | X'04' 4 bits/IDE<br>X'08' 8 bits/IDE [IOCA-7-272]|
| | | SIZE2 (1) X'00' | 0 bits/IDE [IOCA-7-273]|
| | | SIZE3 (1) X'00' | 0 bits/IDE [IOCA-7-274]|
| Image Subsampling | X'FECE' (2, 4) | ID (1) X'01' | Sampling ratios [IOCA-7-275]|
| | | LENGTH (1) X'02' [IOCA-7-276]| |
| | | HSAMPLE (1) X'01' [IOCA-7-277]| |
| | | VSAMPLE (1) X'01' [IOCA-7-278]| |
| **Parameters used when IDESZ=24:** [IOCA-7-279]| | | |
| Band Image | X'98' (2) | BCOUNT (1) X'01' | One band [IOCA-7-280]|
| | | BITCNT (1) X'18' | 24 bits/IDE [IOCA-7-281]|
| *or:* [IOCA-7-282]| | | |
| Band Image | X'98' (4) | BCOUNT (1) X'03' | 3 bands: R,G,B or Y,Cr,Cb or Y,Cb,Cr [IOCA-7-283]|
| | | BITCNT (1) X'08' | 8 bits/IDE for R or Y band [IOCA-7-284]|
| | | BITCNT (1) X'08' | 8 bits/IDE for G or Cr or Cb band [IOCA-7-285]|
| | | BITCNT (1) X'08' | 8 bits/IDE for B or Cb or Cr band [IOCA-7-286]|
| IDE Structure | X'9B' (8) | FLAGS (1) | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-287]|
| | | FORMAT (1) X'01', X'02', X'12' | X'01' RGB<br>X'02' YCrCb<br>X'12' YCbCr [IOCA-7-288]|
| | | RESERVED (3) X'000000' [IOCA-7-289]| |
| | | SIZE1 (1) X'08' | 8 bits for R or Y [IOCA-7-290]|
| | | SIZE2 (1) X'08' | 8 bits for G/Cr/Cb [IOCA-7-291]|
| | | SIZE3 (1) X'08' | 8 bits for B/Cb/Cr [IOCA-7-292]|
| Image Subsampling | X'FECE' (2–8) | ID (1) X'01' | Sampling ratios [IOCA-7-293]|
| | | LENGTH (1) X'02', X'04', X'06' [IOCA-7-294]| |
| | | HSAMPLE1 (1) X'01'–X'02' | X'02' for YCrCb/YCbCr only [IOCA-7-295]|
| | | VSAMPLE1 (1) X'01' [IOCA-7-296]| |
| | | HSAMPLE2 (1) X'01' [IOCA-7-297]| |
| | | VSAMPLE2 (1) X'01' [IOCA-7-298]| |
| | | HSAMPLE3 (1) X'01' [IOCA-7-299]| |
| | | VSAMPLE3 (1) X'01' [IOCA-7-300]| |
| **Final parameters:** [IOCA-7-301]| | | |
| Begin Transparency Mask | X'8E' (0) [IOCA-7-302]| | |
| Image Size | X'94' (9) | UNITBASE X'00'–X'01' | X'00' 10 in; X'01' 10 cm [IOCA-7-303]|
| | | HRESOL (2) X'0001'–X'7FFF' [IOCA-7-304]| |
| | | VRESOL (2) X'0001'–X'7FFF' [IOCA-7-305]| |
| | | HSIZE (2) X'0001'–X'7FFF' [IOCA-7-306]| |
| | | VSIZE (2) X'0001'–X'7FFF' [IOCA-7-307]| |
| Image Encoding | X'95' (2–3) | COMPRID X'01', X'03', X'08', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'82' G4 MMR [IOCA-7-308]|
| | | RECID (1) X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-309]|
| | | BITORDR (1) X'00', X'01' | X'00' Left to right<br>X'01' Right to left [IOCA-7-310]|
| Image Data | X'FE92' (1–FFFF) | DATA | Any IDEs (bilevel only) [IOCA-7-311]|
| End Transparency Mask | X'8F' (0) [IOCA-7-312]| | |
| Image Data | X'FE92' (1–FFFF) | DATA | Any IDEs [IOCA-7-313]|
| *or:* [IOCA-7-314]| | | |
| Band Image Data (BCOUNT=1) | X'FE9C' (4–FFFF) | BANDNUM (1) X'01' | One band [IOCA-7-315]|
| | | RESERVED (2) X'0000' [IOCA-7-316]| |
| | | DATA | Any IDEs [IOCA-7-317]|
| *or:* [IOCA-7-318]| | | |
| Band Image Data (BCOUNT=3) | X'FE9C' (4–FFFF) | BANDNUM (1) X'01' | Band with R or Y component [IOCA-7-319]|
| | | RESERVED (2) X'0000' [IOCA-7-320]| |
| | | DATA | Any R or Y component [IOCA-7-321]|
| | | BANDNUM (1) X'02' | Band with G/Cr/Cb component [IOCA-7-322]|
| | | RESERVED (2) X'0000' [IOCA-7-323]| |
| | | DATA | Any G/Cr/Cb component [IOCA-7-324]|
| | | BANDNUM (1) X'03' | Band with B/Cb/Cr component [IOCA-7-325]|
| | | RESERVED (2) X'0000' [IOCA-7-326]| |
| | | DATA | Any B/Cb/Cr component [IOCA-7-327]|
| End Image Content | X'93' (0) [IOCA-7-328]| | |
| End Segment | X'71' (0) [IOCA-7-329]| | |

**Notes on the initial parameters:**
1. ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, and G4 MMR-Modified Modified READ are applicable only to images whose IDE size is 1 bit/IDE, otherwise exception condition EC-9611 is raised. [IOCA-7-330]
2. Concatenated ABIC is applicable only to images whose IDE size is 4 or 8 bits/IDE, otherwise exception condition EC-9611 is raised. [IOCA-7-331]
3. The JPEG algorithms are applicable only to images whose IDE size is 8 or 24 bits/IDE; otherwise exception condition EC-9611 is raised. [IOCA-7-332]

**Notes on parameters used when IDESZ=4 or IDESZ=8:**
1. Gray coding is valid only for the Concatenated ABIC algorithm, otherwise exception condition EC-9B10 is raised. [IOCA-7-333]
2. Grayscale images only. Grayscale IDEs are composed of the Y component only of the YCrCb or YCbCr color model. [IOCA-7-334]

**General note:** In this function set, the Image Subsampling parameter and the Band Image parameter cannot coexist within the same Image Content; otherwise exception condition EC-9801 or EC-CE01 is raised. [IOCA-7-335]


### IOCA Function Set 40 (IOCA FS40)

Function Set 40 is a subset of Function Set 42, Function Set 45, and Function Set 48. It describes tiled images with one bit per spot (color space YCbCr or YCrCb, IDESZ=1). This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS40 are defined as follows: [IOCA-7-336]

#### Table 11. Function Set 40 Structure

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-337]|
| X'91' | Begin Image Content parameter [IOCA-7-338]|
| X'FEBB' | Tile TOC parameter [IOCA-7-339]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-340]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-341]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-342]|
| | [ Tiles (S) ] [IOCA-7-343]|
| X'93' | End Image Content parameter [IOCA-7-344]|
| X'71' | End Segment parameter [IOCA-7-345]|

#### Table 12. Tile Structure

| Code | Name [IOCA-7-346]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-347]|
| X'B5' | Tile Position parameter [IOCA-7-348]|
| X'B6' | Tile Size parameter [IOCA-7-349]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-350]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-351]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-352]|
| X'FE92' | [ Image Data (S) ] [IOCA-7-353]|
| X'8D' | End Tile parameter [IOCA-7-354]|

**Notes:**
1. Note that the parameters in the above diagram must come in the specified order. Even though the general IOCA architecture allows different ordering for some of the parameters, the FS40 specification is more restrictive. If the parameters are given in a different order, an out-of-sequence exception is raised. [IOCA-7-355]
2. In the context of FS40, the Image Size parameter, Image Subsampling parameter, and External Algorithm Specification parameter cause the EC-0001 exception (Invalid parameter) to occur. If the first parameter after the Begin Image Content parameter is not the Tile TOC parameter, the image is not a tiled image and any of the tile-specific parameters (Tile TOC parameter, Begin Tile parameter, etc.) cause EC-0001 to occur. [IOCA-7-356]
3. The Image Encoding parameter, IDE Size parameter, Band Image parameter, and IDE Structure parameter are shown as optional and can possibly be specified in two places. The specification within a tile takes precedence over a specification outside of the tile. [IOCA-7-357]
4. If the IDE Size parameter is not present, the default IDE size is one bit per pel (bilevel image). [IOCA-7-358]
5. If the Image Encoding parameter is not present, the default compression algorithm is X'03' (No Compression), the recording algorithm defaults to X'01' (RIDIC), and the bit order defaults to zero. [IOCA-7-359]

The self-defining fields and values acceptable for FS40 are shown in the following table. [IOCA-7-360]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in Function Set 40:** [IOCA-7-361]| | | |
| Begin Segment | ID (1) | X'70' [IOCA-7-362]| |
| | LENGTH (1) | X'00' [IOCA-7-363]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-364]| |
| | LENGTH (1) | X'01' [IOCA-7-365]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-366]|
| Tile TOC | ID (2) | X'FEBB' [IOCA-7-367]| |
| | LENGTH (2) | X'0002' – X'7FFF' [IOCA-7-368]| |
| | RESERVED (2) | X'0000' | Reserved; should be set to zero [IOCA-7-369]|
| | | | Either zero repeating groups, or one per tile in the following format: [IOCA-7-370]|
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-371]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-372]|
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-373]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-374]|
| | RELRES (1) | X'01' | Relative resolution [IOCA-7-375]|
| | COMPR (1) | | Compression algorithm [IOCA-7-376]|
| | DATAPOS (8) | | File offset to the beginning of the tile [IOCA-7-377]|
| Image Encoding | ID (1) | X'95' [IOCA-7-378]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-379]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR-Modified Modified Read (see General Note)<br>X'03' No Compression<br>X'08' ABIC (Bilevel Q-Coder) (see General Note)<br>X'82' G4 MMR-Modified Modified READ (see General Note) [IOCA-7-380]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-381]|
| | BITORDR (1) | X'00' – X'01' | X'00' Bit order within each image data byte is from left to right<br>X'01' Bit order within each image data byte is from right to left [IOCA-7-382]|
| IDE Size | ID (1) | X'96' [IOCA-7-383]| |
| | LENGTH (1) | X'01' [IOCA-7-384]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-385]|
| **Initial parameters in a tile:** [IOCA-7-386]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-387]| |
| | LENGTH (1) | X'00' [IOCA-7-388]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-389]| |
| | LENGTH (1) | X'08' [IOCA-7-390]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-391]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-392]|
| Tile Size | ID (1) | X'B6' [IOCA-7-393]| |
| | LENGTH (1) | X'08' – X'09' [IOCA-7-394]| |
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-395]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-396]|
| | RELRES (1) | X'01' | Relative resolution [IOCA-7-397]|
| **Tile parameters:** [IOCA-7-398]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-399]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-400]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR-Modified Modified Read (see General Note)<br>X'03' No Compression<br>X'08' ABIC (Bilevel Q-Coder)<br>X'82' G4 MMR-Modified Modified READ (see General Note) [IOCA-7-401]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-402]|
| | BITORDR (1) | X'00' – X'01' | X'00' Bit order within each image data byte is from left to right<br>X'01' Bit order within each image data byte is from right to left [IOCA-7-403]|
| IDE Size | ID (1) | X'96' [IOCA-7-404]| |
| | LENGTH (1) | X'01' [IOCA-7-405]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-406]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-407]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-408]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' Should be zero [IOCA-7-409]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-410]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-411]|
| | SIZE1 (1) | X'01' | 1 bit/IDE [IOCA-7-412]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-413]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-414]|
| Image Data | ID (2) | X'FE92' [IOCA-7-415]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-416]| |
| | DATA | Any | IDEs [IOCA-7-417]|
| End Tile | ID (1) | X'8D' [IOCA-7-418]| |
| | LENGTH (1) | X'00' [IOCA-7-419]| |
| **Final parameters in Function Set 40:** [IOCA-7-420]| | | |
| End Image Content | ID (1) | X'93' [IOCA-7-421]| |
| | LENGTH (1) | X'00' [IOCA-7-422]| |
| End Segment | ID (1) | X'71' [IOCA-7-423]| |
| | LENGTH (1) | X'00' [IOCA-7-424]| |

**General note:** ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, and G4 MMR-Modified Modified READ are applicable only to images whose IDE size is 1 bit per band, otherwise exception condition EC-9611 is raised. [IOCA-7-425]


### IOCA Function Set 42 (IOCA FS42)

Function Set 42 is a superset of Function Set 40 and a subset of Function Set 45 and Function Set 48. It describes tiled images with one bit per spot. Images can be either bilevel (color space YCbCr or YCrCb, IDESZ=1) or color (color space CMYK, IDESZ=4). This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS42 are defined as follows: [IOCA-7-426]

#### Table 13. Function Set 42 Structure

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-427]|
| X'91' | Begin Image Content parameter [IOCA-7-428]|
| X'FEBB' | Tile TOC parameter [IOCA-7-429]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-430]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-431]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-432]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-433]|
| | [ Tiles (S) ] [IOCA-7-434]|
| X'93' | End Image Content parameter [IOCA-7-435]|
| X'71' | End Segment parameter [IOCA-7-436]|

#### Table 14. Tile Structure

| Code | Name [IOCA-7-437]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-438]|
| X'B5' | Tile Position parameter [IOCA-7-439]|
| X'B6' | Tile Size parameter [IOCA-7-440]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-441]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-442]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-443]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-444]|
| [ X'B7' ] | [ Tile Set Color parameter ] [IOCA-7-445]|
| | [ Image Data or Band Image Data (S) ] [IOCA-7-446]|
| X'8D' | End Tile parameter [IOCA-7-447]|

**Notes:**
1. Note that the parameters in Table 13 and Table 14 must come in the specified order. Even though the general IOCA architecture allows different ordering for some of the parameters, the FS42 specification is more restrictive. If the parameters are given in a different order, an out-of-sequence exception is raised. [IOCA-7-448]
2. In the context of FS42, the Image Size parameter, Image Subsampling parameter, and External Algorithm Specification parameter cause the EC-0001 exception (Invalid parameter) to occur. If the first parameter after the Begin Image Content parameter is not the Tile TOC parameter, the image is not a tiled image and any of the tile-specific parameters (Tile TOC parameter, Begin Tile parameter, etc.) cause EC-0001 to occur. [IOCA-7-449]
3. If the IDE Size is not set to 1 bit or the color space is not YCbCr or YCrCb for a tile, and the Tile Set Color parameter is specified, exception EC-B711 occurs. [IOCA-7-450]
4. If the Solid Fill Rectangle compression algorithm is specified for a tile and Image Data or Band Image Data is encountered, exception EC-0001 occurs. [IOCA-7-451]
5. The Image Encoding parameter, IDE Size parameter, Band Image parameter, and IDE Structure parameter are shown as optional and can possibly be specified in two places. The specification within a tile takes precedence over a specification outside of the tile. [IOCA-7-452]
6. If the IDE Size parameter is not present, the default IDE size is one bit per pel (bilevel image). [IOCA-7-453]
7. If the Image Encoding parameter is not present, the default compression algorithm is X'03' (No Compression), the recording algorithm defaults to X'01' (RIDIC), and the bit order defaults to zero. [IOCA-7-454]
8. If a tile contains the IDE Structure parameter specifying the CMYK color space, then the IDE Size parameter, Band Image parameter, and Band Image Data must also be present. [IOCA-7-455]
9. If the IDE Structure parameter specifying the CMYK color space is given outside of the tiles, then the IDE Size parameter and the Band Image parameter must be given either outside of the tiles or within every tile that does not contain another IDE Structure parameter specifying that the tile is bilevel. [IOCA-7-456]
10. CMYK tiles must carry the image data in Band Image Data. Bilevel tiles must carry the data in Image Data, unless the Solid Fill Rectangle compression algorithm is specified. [IOCA-7-457]
11. If a tile has Solid Fill Rectangle specified as the compression algorithm, the tile is painted using the color specified in the Tile Set Color parameter for that tile. If the Tile Set Color parameter has not been specified, the color given using the Set Bilevel Image Color field in the Image Data Descriptor is used. If the Set Bilevel Image Color field is missing, the device default is used. [IOCA-7-458]

The self-defining fields and values acceptable for FS42 are shown in the following table. [IOCA-7-459]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in Function Set 42:** [IOCA-7-460]| | | |
| Begin Segment | ID (1) | X'70' [IOCA-7-461]| |
| | LENGTH (1) | X'00' [IOCA-7-462]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-463]| |
| | LENGTH (1) | X'01' [IOCA-7-464]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-465]|
| Tile TOC | ID (2) | X'FEBB' [IOCA-7-466]| |
| | LENGTH (2) | X'0002' – X'7FFF' [IOCA-7-467]| |
| | RESERVED (2) | X'0000' | Reserved; should be set to zero [IOCA-7-468]|
| | | | Either zero repeating groups, or one per tile in the following format: [IOCA-7-469]|
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-470]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-471]|
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-472]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-473]|
| | RELRES (1) | X'01' | Relative resolution [IOCA-7-474]|
| | COMPR (1) | | Compression algorithm [IOCA-7-475]|
| | DATAPOS (8) | | File offset to the beginning of the tile [IOCA-7-476]|
| Image Encoding | ID (1) | X'95' [IOCA-7-477]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-478]| |
| | COMPRID (1) | X'01', X'03', X'08', X'20', X'82' | X'01' IBM MMR-Modified Modified Read (see General Note)<br>X'03' No Compression<br>X'08' ABIC (Bilevel Q-Coder) (see General Note)<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR-Modified Modified READ (see General Note) [IOCA-7-479]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-480]|
| | BITORDR (1) | X'00' – X'01' | X'00' Bit order within each image data byte is from left to right<br>X'01' Bit order within each image data byte is from right to left [IOCA-7-481]|
| IDE Size | ID (1) | X'96' [IOCA-7-482]| |
| | LENGTH (1) | X'01' [IOCA-7-483]| |
| | IDESZ (1) | X'01', X'04' | X'01' 1 bit/IDE<br>X'04' 4 bits/IDE [IOCA-7-484]|
| **Initial parameters in a tile:** [IOCA-7-485]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-486]| |
| | LENGTH (1) | X'00' [IOCA-7-487]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-488]| |
| | LENGTH (1) | X'08' [IOCA-7-489]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-490]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-491]|
| Tile Size | ID (1) | X'B6' [IOCA-7-492]| |
| | LENGTH (1) | X'08' – X'09' [IOCA-7-493]| |
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-494]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-495]|
| | RELRES (1) | X'01' | Relative resolution [IOCA-7-496]|
| **Tile parameters used when IDESZ=1:** [IOCA-7-497]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-498]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-499]| |
| | COMPRID (1) | X'01', X'03', X'08', X'20', X'82' | X'01' IBM MMR-Modified Modified Read (see General Note)<br>X'03' No Compression<br>X'08' ABIC (Bilevel Q-Coder)<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR-Modified Modified READ (see General Note) [IOCA-7-500]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-501]|
| | BITORDR (1) | X'00' – X'01' | X'00' Bit order within each image data byte is from left to right<br>X'01' Bit order within each image data byte is from right to left [IOCA-7-502]|
| IDE Size | ID (1) | X'96' [IOCA-7-503]| |
| | LENGTH (1) | X'01' [IOCA-7-504]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-505]|
| Band Image | ID (1) | X'98' [IOCA-7-506]| |
| | LENGTH (1) | X'02' [IOCA-7-507]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-508]|
| | BITCNT (1) | X'01' | 1 bit/IDE [IOCA-7-509]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-510]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-511]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' Should be zero [IOCA-7-512]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-513]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-514]|
| | SIZE1 (1) | X'01' | 1 bit/IDE [IOCA-7-515]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-516]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-517]|
| Tile Set Color | ID (1) | X'B7' [IOCA-7-518]| |
| | LENGTH (1) | X'0B', X'0C' [IOCA-7-519]| |
| | CSPACE (1) | X'04', X'08' | X'04' CMYK<br>X'08' CIELab [IOCA-7-520]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-521]|
| | SIZE1–SIZE3 (1) | X'08' | Bits/IDE for components 1-3 [IOCA-7-522]|
| | SIZE4 (1) | X'00', X'08' | Bits/IDE for component 4 [IOCA-7-523]|
| | CVAL1–CVAL4 (1) | X'00' – X'FF' | Color values [IOCA-7-524]|
| **Tile parameters used when IDESZ=4:** [IOCA-7-525]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-526]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-527]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR-Modified Modified Read (see General Note)<br>X'03' No Compression<br>X'08' ABIC (Bilevel Q-Coder)<br>X'82' G4 MMR-Modified Modified READ (see General Note) [IOCA-7-528]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-529]|
| | BITORDR (1) | X'00', X'01' | X'00' Bit order within each image data byte is from left to right<br>X'01' Bit order within each image data byte is from right to left [IOCA-7-530]|
| IDE Size | ID (1) | X'96' [IOCA-7-531]| |
| | LENGTH (1) | X'01' [IOCA-7-532]| |
| | IDESZ (1) | X'04' | 4 bits/IDE [IOCA-7-533]|
| Band Image | ID (1) | X'98' [IOCA-7-534]| |
| | LENGTH (1) | X'05' [IOCA-7-535]| |
| | BCOUNT (1) | X'04' | Four bands: CMYK [IOCA-7-536]|
| | BITCNT (1) | X'01' | 1 bit/IDE for C band [IOCA-7-537]|
| | BITCNT (1) | X'01' | 1 bit/IDE for M band [IOCA-7-538]|
| | BITCNT (1) | X'01' | 1 bit/IDE for Y band [IOCA-7-539]|
| | BITCNT (1) | X'01' | 1 bit/IDE for K band [IOCA-7-540]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-541]| |
| | LENGTH (1) | X'09' [IOCA-7-542]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' Should be zero [IOCA-7-543]|
| | FORMAT (1) | X'04' | CMYK [IOCA-7-544]|
| | RESERVED (3) | X'000000' | Should be zero [IOCA-7-545]|
| | SIZE1 (1) | X'01' | 1 bit/IDE (C component) [IOCA-7-546]|
| | SIZE2 (1) | X'01' | 1 bit/IDE (M component) [IOCA-7-547]|
| | SIZE3 (1) | X'01' | 1 bit/IDE (Y component) [IOCA-7-548]|
| | SIZE4 (1) | X'01' | 1 bit/IDE (K component) [IOCA-7-549]|
| **Final parameters in a tile:** [IOCA-7-550]| | | |
| Image Data | ID (2) | X'FE92' [IOCA-7-551]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-552]| |
| | DATA | Any | IDEs [IOCA-7-553]|
| *or:* [IOCA-7-554]| | | |
| **Band Image Data** | (BCOUNT=4 only) [IOCA-7-555]| | |
| | | | Four bands, in order by BANDNUM, in the following format: [IOCA-7-556]|
| | ID (2) | X'FE9C' [IOCA-7-557]| |
| | LENGTH (2) | X'0004' – X'FFFF' [IOCA-7-558]| |
| | BANDNUM (1) | X'01' – X'04' | X'01' Band contains C component<br>X'02' Band contains M component<br>X'03' Band contains Y component<br>X'04' Band contains K component [IOCA-7-559]|
| | RESERVED (2) | X'0000' | Should be zero [IOCA-7-560]|
| | DATA | Any [IOCA-7-561]| |
| End Tile | ID (1) | X'8D' [IOCA-7-562]| |
| | LENGTH (1) | X'00' [IOCA-7-563]| |
| **Final parameters in Function Set 42:** [IOCA-7-564]| | | |
| End Image Content | ID (1) | X'93' [IOCA-7-565]| |
| | LENGTH (1) | X'00' [IOCA-7-566]| |
| End Segment | ID (1) | X'71' [IOCA-7-567]| |
| | LENGTH (1) | X'00' [IOCA-7-568]| |

**General note:** ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, G4 MMR-Modified Modified READ, and Solid Fill Rectangle are applicable only to images whose IDE size is 1 bit per band, otherwise exception condition EC-9611 is raised. [IOCA-7-569]


### IOCA Function Set 45 (IOCA FS45)

Function Set 45 is a superset of Function Set 40 and Function Set 42 and a subset of Function Set 48. It describes bilevel or color tiled images. This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS45 are defined as follows: [IOCA-7-570]

#### Table 15. Function Set 45 Structure

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-571]|
| | Image Content (S) [IOCA-7-572]|
| X'71' | End Segment parameter [IOCA-7-573]|

#### Table 16. Image Content Structure

| Code | Name [IOCA-7-574]|
| :--- | :--- |
| X'91' | Begin Image Content parameter [IOCA-7-575]|
| X'FEBB' | Tile TOC parameter [IOCA-7-576]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-577]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-578]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-579]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-580]|
| | [ Data and Referencing Tiles (S) ] [IOCA-7-581]|
| X'93' | End Image Content parameter [IOCA-7-582]|

#### Table 17. Data Tile Structure

| Code | Name [IOCA-7-583]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-584]|
| X'B5' | Tile Position parameter [IOCA-7-585]|
| X'B6' | Tile Size parameter [IOCA-7-586]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-587]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-588]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-589]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-590]|
| [ X'9F' ] | [ External Algorithm Specification parameter (ignored) ] [IOCA-7-591]|
| [ X'B7' ] | [ Tile Set Color parameter ] [IOCA-7-592]|
| | [ Transparency Mask ] [IOCA-7-593]|
| | [ Image Data or Band Image Data (S) ] [IOCA-7-594]|
| X'8D' | End Tile parameter [IOCA-7-595]|

#### Table 18. Referencing Tile Structure

| Code | Name [IOCA-7-596]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-597]|
| X'B5' | Tile Position parameter [IOCA-7-598]|
| X'FEB8' | Include Tile parameter [IOCA-7-599]|
| | [ Transparency Mask ] [IOCA-7-600]|
| X'8D' | End Tile parameter [IOCA-7-601]|

#### Table 19. IOCA Tile Resource Structure

| Code | Name [IOCA-7-602]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-603]|
| X'B5' | Tile Position parameter [IOCA-7-604]|
| X'B6' | Tile Size parameter [IOCA-7-605]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-606]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-607]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-608]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-609]|
| [ X'9F' ] | [ External Algorithm Specification parameter (ignored) ] [IOCA-7-610]|
| [ X'B7' ] | [ Tile Set Color parameter ] [IOCA-7-611]|
| | [ Transparency Mask ] [IOCA-7-612]|
| | [ Image Data or Band Image Data (S) ] [IOCA-7-613]|
| X'8D' | End Tile parameter [IOCA-7-614]|

#### Table 20. Transparency Mask Structure

| Code | Name [IOCA-7-615]|
| :--- | :--- |
| X'8E' | Begin Transparency Mask parameter [IOCA-7-616]|
| X'94' | Image Size parameter [IOCA-7-617]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-618]|
| X'FE92' | Image Data [IOCA-7-619]|
| X'8F' | End Transparency Mask parameter [IOCA-7-620]|

**Notes:**
1. Note that the parameters in Table 15, Table 16, Table 17, Table 18, Table 19, and Table 20 must come in the specified order. Even though the general IOCA architecture allows different ordering for some of the parameters, the FS45 specification is more restrictive. If the parameters are given in a different order, an out-of-sequence exception is raised. [IOCA-7-621]
2. The Image Encoding parameter, IDE Size parameter, Band Image parameter, and IDE Structure parameter are shown as optional and can possibly be specified in two places. Note that tile data might require that some of these parameters be specified. [IOCA-7-622]
3. If the IDE Size parameter is not present neither in the tile nor in the image content, the default IDE size is one bit per pel (bilevel image). [IOCA-7-623]
4. If the Image Encoding parameter is not present, the default compression algorithm is X'03' (No Compression), the recording algorithm defaults to X'01' (RIDIC), and the bit order defaults to zero. [IOCA-7-624]
5. If a tile contains the IDE Structure parameter specifying the CMYK color space, then the IDE Size parameter, Band Image parameter, and Band Image Data must also be present. [IOCA-7-625]
6. If the IDE Structure parameter specifying the CMYK color space is given outside of the tiles, then the IDE Size parameter and the Band Image parameter must be given either outside of the tiles or within every tile that does not contain another IDE Structure parameter specifying a different color space. [IOCA-7-626]
7. Receivers implementing FS45 must support at least 128 image contents in a single image segment. Otherwise, if a receiver encounters too many image contents to process, it should act as if it encountered too many image objects on a page. [IOCA-7-627]
8. Resource tiles included via the Include Tile parameter must not contain the Include Tile parameter or the exception EC-B811 exists. [IOCA-7-628]

The self-defining fields and parameter values that are acceptable in Function Set 45 are shown in the following table. [IOCA-7-629]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in FS45:** [IOCA-7-630]| | | |
| Begin Segment | ID (1) | X'70' [IOCA-7-631]| |
| | LENGTH (1) | X'00' [IOCA-7-632]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-633]| |
| | LENGTH (1) | X'01' [IOCA-7-634]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-635]|
| Tile TOC | ID (2) | X'FEBB' [IOCA-7-636]| |
| | LENGTH (2) | X'0002' – X'7FFF' [IOCA-7-637]| |
| | RESERVED (2) | X'0000' | Reserved; should be set to zero [IOCA-7-638]|
| | | | Either zero repeating groups, or one per tile in the following format: [IOCA-7-639]|
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-640]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-641]|
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-642]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-643]|
| | RELRES (1) | X'01' – X'02' | Relative resolution (see Note 1) [IOCA-7-644]|
| | COMPR (1) | | Compression algorithm [IOCA-7-645]|
| | DATAPOS (8) | | File offset to the beginning of the tile [IOCA-7-646]|
| Image Encoding | ID (1) | X'95' [IOCA-7-647]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-648]| |
| | COMPRID (1) | X'01', X'03', X'08', X'0D', X'20', X'82' – X'83' | X'01' IBM MMR (see Note 2)<br>X'03' No Compression<br>X'08' ABIC (see Note 2)<br>X'0D' TIFF LZW<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR (see Note 2)<br>X'83' JPEG algorithms (see Note 3) [IOCA-7-649]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-650]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-651]|
| IDE Size | ID (1) | X'96' [IOCA-7-652]| |
| | LENGTH (1) | X'01' [IOCA-7-653]| |
| | IDESZ (1) | X'01', X'04', X'20' | X'01' 1 bit/IDE<br>X'04' 4 bits/IDE<br>X'20' 32 bits/IDE [IOCA-7-654]|

**Notes on the initial parameters:**
1. In the Tile TOC parameter, the relative resolution (RELRES) of 2 is supported only for JPEG-compressed data. Using RELRES of 1 for JPEG-compressed data and RELRES of 2 for non-JPEG data results in exception EC-B610 being raised. Note that this restriction on the relative resolution holds only for this function set, not for the IOCA architecture in general. [IOCA-7-655]
**Implementation Note:** Some FS45 receivers support a RELRES of 1 for JPEG-compressed data, and do not raise exception EC-B610 for such data. Also note that in FS48, a RELRES of 1 for JPEG-compressed data is allowed.
2. ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, G4 MMR-Modified Modified READ, and Solid Fill Rectangle are applicable only to images whose IDE size is 1 bit/band, otherwise exception condition EC-9611 is raised. [IOCA-7-656]
3. The JPEG algorithms are applicable only to images whose IDE size is 32 bits/IDE; otherwise exception condition EC-9611 is raised. [IOCA-7-657]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in a data tile:** [IOCA-7-658]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-659]| |
| | LENGTH (1) | X'00' [IOCA-7-660]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-661]| |
| | LENGTH (1) | X'08' [IOCA-7-662]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-663]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-664]|
| Tile Size | ID (1) | X'B6' [IOCA-7-665]| |
| | LENGTH (1) | X'08' – X'09' [IOCA-7-666]| |
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-667]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-668]|
| | RELRES (1) | X'01' – X'02' | Relative resolution (see Note) [IOCA-7-669]|

**Note on the data-tile initial parameters:** In the Tile Size parameter, the relative resolution (RELRES) of 2 is supported only for JPEG-compressed data. Using RELRES of 1 for JPEG-compressed data and RELRES of 2 for non-JPEG data results in exception EC-B610 being raised. Note that this restriction on the relative resolution holds only for this function set, not for the IOCA architecture in general.
**Implementation Note:** Some FS45 receivers support a RELRES of 1 for JPEG-compressed data, and do not raise exception EC-B610 for such data. Also note that in FS48, a RELRES of 1 for JPEG-compressed data is allowed. [IOCA-7-670]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in a referencing tile:** [IOCA-7-671]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-672]| |
| | LENGTH (1) | X'00' [IOCA-7-673]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-674]| |
| | LENGTH (1) | X'08' [IOCA-7-675]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-676]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-677]|
| Include Tile | ID (2) | X'FEB8' [IOCA-7-678]| |
| | LENGTH (2) | X'0004' [IOCA-7-679]| |
| | TIRID (4) | X'00000000' – X'FFFFFFFF' | Resource Tile local identifier [IOCA-7-680]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-681]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=1:** [IOCA-7-682]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-683]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-684]| |
| | COMPRID (1) | X'01', X'03', X'08', X'20', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR [IOCA-7-685]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-686]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-687]|
| IDE Size | ID (1) | X'96' [IOCA-7-688]| |
| | LENGTH (1) | X'01' [IOCA-7-689]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-690]|
| Band Image | ID (1) | X'98' [IOCA-7-691]| |
| | LENGTH (1) | X'02' [IOCA-7-692]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-693]|
| | BITCNT (1) | X'01' | 1 bit/IDE [IOCA-7-694]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-695]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-696]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'00000' [IOCA-7-697]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-698]|
| | RESERVED (3) | X'000000' [IOCA-7-699]| |
| | SIZE1 (1) | X'01' | 1 bit/IDE [IOCA-7-700]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-701]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-702]|
| Tile Set Color | ID (1) | X'B7' [IOCA-7-703]| |
| | LENGTH (1) | X'0B', X'0C' [IOCA-7-704]| |
| | CSPACE (1) | X'04', X'08' | X'04' CMYK<br>X'08' CIELab [IOCA-7-705]|
| | RESERVED (3) | X'000000' [IOCA-7-706]| |
| | SIZE1–SIZE3 (1) | X'08' | Bits/IDE for components 1-3 [IOCA-7-707]|
| | SIZE4 (1) | X'00', X'08' | Bits/IDE for component 4 [IOCA-7-708]|
| | CVAL1–CVAL4 (1) | X'00' – X'FF' | Color values [IOCA-7-709]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-710]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=4:** [IOCA-7-711]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-712]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-713]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'82' G4 MMR [IOCA-7-714]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-715]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-716]|
| IDE Size | ID (1) | X'96' [IOCA-7-717]| |
| | LENGTH (1) | X'01' [IOCA-7-718]| |
| | IDESZ (1) | X'04' | 4 bits/IDE [IOCA-7-719]|
| Band Image | ID (1) | X'98' [IOCA-7-720]| |
| | LENGTH (1) | X'05' [IOCA-7-721]| |
| | BCOUNT (1) | X'04' | Four bands: CMYK [IOCA-7-722]|
| | BITCNT (1) | X'01' | 1 bit/IDE for C band [IOCA-7-723]|
| | BITCNT (1) | X'01' | 1 bit/IDE for M band [IOCA-7-724]|
| | BITCNT (1) | X'01' | 1 bit/IDE for Y band [IOCA-7-725]|
| | BITCNT (1) | X'01' | 1 bit/IDE for K band [IOCA-7-726]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-727]| |
| | LENGTH (1) | X'09' [IOCA-7-728]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-729]|
| | FORMAT (1) | X'04' | CMYK [IOCA-7-730]|
| | RESERVED (3) | X'000000' [IOCA-7-731]| |
| | SIZE1 (1) | X'01' | 1 bit/IDE (C component) [IOCA-7-732]|
| | SIZE2 (1) | X'01' | 1 bit/IDE (M component) [IOCA-7-733]|
| | SIZE3 (1) | X'01' | 1 bit/IDE (Y component) [IOCA-7-734]|
| | SIZE4 (1) | X'01' | 1 bit/IDE (K component) [IOCA-7-735]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-736]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=32:** [IOCA-7-737]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-738]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-739]| |
| | COMPRID (1) | X'03', X'0D', X'83' | X'03' No Compression<br>X'0D' TIFF LZW<br>X'83' JPEG algorithms [IOCA-7-740]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-741]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-742]|
| IDE Size | ID (1) | X'96' [IOCA-7-743]| |
| | LENGTH (1) | X'01' [IOCA-7-744]| |
| | IDESZ (1) | X'20' | 32 bits/IDE [IOCA-7-745]|
| Band Image | ID (1) | X'98' [IOCA-7-746]| |
| | LENGTH (1) | X'05' [IOCA-7-747]| |
| | BCOUNT (1) | X'04' | 4 bands: CMYK [IOCA-7-748]|
| | BITCNT (1) | X'08' | 8 bits/IDE for C band [IOCA-7-749]|
| | BITCNT (1) | X'08' | 8 bits/IDE for M band [IOCA-7-750]|
| | BITCNT (1) | X'08' | 8 bits/IDE for Y band [IOCA-7-751]|
| | BITCNT (1) | X'08' | 8 bits/IDE for K band [IOCA-7-752]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-753]| |
| | LENGTH (1) | X'09' [IOCA-7-754]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-755]|
| | FORMAT (1) | X'04' | CMYK [IOCA-7-756]|
| | RESERVED (3) | X'000000' [IOCA-7-757]| |
| | SIZE1 (1) | X'08' | 8 bits/IDE (C component) [IOCA-7-758]|
| | SIZE2 (1) | X'08' | 8 bits/IDE (M component) [IOCA-7-759]|
| | SIZE3 (1) | X'08' | 8 bits/IDE (Y component) [IOCA-7-760]|
| | SIZE4 (1) | X'08' | 8 bits/IDE (K component) [IOCA-7-761]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-762]|
| :--- | :--- | :--- | :--- |
| **Final parameters in a tile:** [IOCA-7-763]| | | |
| Begin Transparency Mask | ID (1) | X'8E' [IOCA-7-764]| |
| | LENGTH (1) | X'00' [IOCA-7-765]| |
| Image Size | ID (1) | X'94' [IOCA-7-766]| |
| | LENGTH (1) | X'09' [IOCA-7-767]| |
| | UNITBASE (1) | X'00' – X'01' | X'00' 10 in; X'01' 10 cm [IOCA-7-768]|
| | HRESOL (2) | X'0001' – X'7FFF' [IOCA-7-769]| |
| | VRESOL (2) | X'0001' – X'7FFF' [IOCA-7-770]| |
| | HSIZE (2) | X'0001' – X'7FFF' [IOCA-7-771]| |
| | VSIZE (2) | X'0001' – X'7FFF' [IOCA-7-772]| |
| Image Encoding | ID (1) | X'95' [IOCA-7-773]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-774]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'82' G4 MMR [IOCA-7-775]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-776]|
| | BITORDR (1) | X'00', X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-777]|
| Image Data | ID (2) | X'FE92' [IOCA-7-778]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-779]| |
| | DATA | Any | IDEs (bilevel only) [IOCA-7-780]|
| End Transparency Mask | ID (1) | X'8F' [IOCA-7-781]| |
| | LENGTH (1) | X'00' [IOCA-7-782]| |

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-783]|
| :--- | :--- | :--- | :--- |
| Image Data | ID (2) | X'FE92' [IOCA-7-784]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-785]| |
| | DATA | Any | IDEs [IOCA-7-786]|
| *or:* [IOCA-7-787]| | | |
| **Band Image Data** | (BCOUNT=4 only) [IOCA-7-788]| | |
| | | | Four bands, in order by BANDNUM, in the following format: [IOCA-7-789]|
| | ID (2) | X'FE9C' [IOCA-7-790]| |
| | LENGTH (2) | X'0003' – X'FFFF' | (see Note) [IOCA-7-791]|
| | BANDNUM (1) | X'01' – X'04' | X'01' Band contains C component<br>X'02' Band contains M component<br>X'03' Band contains Y component<br>X'04' Band contains K component [IOCA-7-792]|
| | RESERVED (2) | X'0000' [IOCA-7-793]| |
| | DATA | Any [IOCA-7-794]| |
| End Tile | ID (1) | X'8D' [IOCA-7-795]| |
| | LENGTH (1) | X'00' [IOCA-7-796]| |

**Note on the tile-final parameters:** Band Image Data parameters with length of X'0003' do not have a data field. The receiver generates zeros for the corresponding band. [IOCA-7-797]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Final parameters in FS45:** [IOCA-7-798]| | | |
| End Image Content | ID (1) | X'93' [IOCA-7-799]| |
| | LENGTH (1) | X'00' [IOCA-7-800]| |
| End Segment | ID (1) | X'71' [IOCA-7-801]| |
| | LENGTH (1) | X'00' [IOCA-7-802]| |


### IOCA Function Set 48 (IOCA FS48)

Function Set 48 is a superset of Function Set 40, Function Set 42, and Function Set 45. It describes bilevel or color tiled images. This function set is carried by the MO:DCA and IPDS controlling environments. The permissible parameter groupings in FS48 are defined as follows: [IOCA-7-803]

#### Table 21. Function Set 48 Structure

| Code | Name |
| :--- | :--- |
| X'70' | Begin Segment parameter [IOCA-7-804]|
| | Image Content (S) [IOCA-7-805]|
| X'71' | End Segment parameter [IOCA-7-806]|

#### Table 22. Image Content Structure

| Code | Name [IOCA-7-807]|
| :--- | :--- |
| X'91' | Begin Image Content parameter [IOCA-7-808]|
| X'FEBB' | Tile TOC parameter [IOCA-7-809]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-810]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-811]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-812]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-813]|
| [ X'FEB3' ] | [ nColor Names parameter ] [IOCA-7-814]|
| | [ Data and Referencing Tiles (S) ] [IOCA-7-815]|
| X'93' | End Image Content parameter [IOCA-7-816]|

#### Table 23. Data Tile Structure

| Code | Name [IOCA-7-817]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-818]|
| X'B5' | Tile Position parameter [IOCA-7-819]|
| X'B6' | Tile Size parameter [IOCA-7-820]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-821]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-822]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-823]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-824]|
| [ X'FEB3' ] | [ nColor Names parameter ] [IOCA-7-825]|
| [ X'9F' ] | [ External Algorithm Specification parameter (ignored) ] [IOCA-7-826]|
| [ X'B7' ] | [ Tile Set Color parameter ] [IOCA-7-827]|
| | [ Transparency Mask ] [IOCA-7-828]|
| | [ Image Data or Band Image Data (S) ] [IOCA-7-829]|
| X'8D' | End Tile parameter [IOCA-7-830]|

#### Table 24. Referencing Tile Structure

| Code | Name [IOCA-7-831]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-832]|
| X'B5' | Tile Position parameter [IOCA-7-833]|
| X'FEB8' | Include Tile parameter [IOCA-7-834]|
| | [ Transparency Mask ] [IOCA-7-835]|
| X'8D' | End Tile parameter [IOCA-7-836]|

#### Table 25. IOCA Tile Resource Structure

| Code | Name [IOCA-7-837]|
| :--- | :--- |
| X'8C' | Begin Tile parameter [IOCA-7-838]|
| X'B5' | Tile Position parameter [IOCA-7-839]|
| X'B6' | Tile Size parameter [IOCA-7-840]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-841]|
| [ X'96' ] | [ IDE Size parameter ] [IOCA-7-842]|
| [ X'98' ] | [ Band Image parameter ] [IOCA-7-843]|
| [ X'9B' ] | [ IDE Structure parameter ] [IOCA-7-844]|
| [ X'FEB3' ] | [ nColor Names parameter ] [IOCA-7-845]|
| [ X'9F' ] | [ External Algorithm Specification parameter (ignored) ] [IOCA-7-846]|
| [ X'B7' ] | [ Tile Set Color parameter ] [IOCA-7-847]|
| | [ Transparency Mask ] [IOCA-7-848]|
| | [ Image Data or Band Image Data (S) ] [IOCA-7-849]|
| X'8D' | End Tile parameter [IOCA-7-850]|

#### Table 26. Transparency Mask Structure

| Code | Name [IOCA-7-851]|
| :--- | :--- |
| X'8E' | Begin Transparency Mask parameter [IOCA-7-852]|
| X'94' | Image Size parameter [IOCA-7-853]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-7-854]|
| X'FE92' | Image Data [IOCA-7-855]|
| X'8F' | End Transparency Mask parameter [IOCA-7-856]|

**Notes:**
1. Note that the parameters in Table 21, Table 22, Table 23, Table 24, Table 25, and Table 26 must come in the specified order. Even though the general IOCA architecture allows different ordering for some of the parameters, the FS48 specification is more restrictive. If the parameters are given in a different order, an out-of-sequence exception is raised. [IOCA-7-857]
2. The Image Encoding parameter, IDE Size parameter, Band Image parameter, and IDE Structure parameter are shown as optional and can possibly be specified in two places. Note that tile data might require that some of these parameters be specified. [IOCA-7-858]
3. If the IDE Size parameter is not present neither in the tile nor in the image content, the default IDE size is one bit per pel (bilevel image). [IOCA-7-859]
4. If the Image Encoding parameter is not present, the default compression algorithm is X'03' (No Compression), the recording algorithm defaults to X'01' (RIDIC), and the bit order defaults to zero. [IOCA-7-860]
5. If a tile contains the IDE Structure parameter specifying the CMYK or nColor color space, then the IDE Size parameter, Band Image parameter, and Band Image Data must also be present. [IOCA-7-861]
6. If the IDE Structure parameter specifying the CMYK or nColor color space is given outside of the tiles, then the IDE Size parameter and the Band Image parameter must be given either outside of the tiles or within every tile that does not contain another IDE Structure parameter specifying a different color space. [IOCA-7-862]
7. Receivers implementing FS48 must support at least 128 image contents in a single image segment. Otherwise, if a receiver encounters too many image contents to process, it should act as if it encountered too many image objects on a page. [IOCA-7-863]
8. Resource tiles included via the Include Tile parameter must not contain the Include Tile parameter or the exception EC-B811 exists. [IOCA-7-864]
9. Implementations of FS48 are very strongly recommended to also support the nColor Names parameter. Support of this parameter is not required in FS48 because FS48 became an official part of IOCA before the introduction of the nColor Names parameter; if the parameter had existed at the time FS48 was added, its support would have been made part of FS48. The nColor Names parameter is optional, but when specified with an FS48 image, must immediately follow the IDE Structure parameter in Table 22, Table 23, and Table 25. [IOCA-7-865]

The self-defining fields and parameter values that are acceptable in Function Set 48 are shown in the following table. [IOCA-7-866]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in FS48:** [IOCA-7-867]| | | |
| Begin Segment | ID (1) | X'70' [IOCA-7-868]| |
| | LENGTH (1) | X'00' [IOCA-7-869]| |
| Begin Image Content | ID (1) | X'91' [IOCA-7-870]| |
| | LENGTH (1) | X'01' [IOCA-7-871]| |
| | OBJTYPE (1) | X'FF' | IOCA [IOCA-7-872]|
| Tile TOC | ID (2) | X'FEBB' [IOCA-7-873]| |
| | LENGTH (2) | X'0002' – X'7FFF' [IOCA-7-874]| |
| | RESERVED (2) | X'0000' | Reserved; should be set to zero [IOCA-7-875]|
| | | | Either zero repeating groups, or one per tile in the following format: [IOCA-7-876]|
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-877]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-878]|
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-879]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-880]|
| | RELRES (1) | X'01' – X'02' | Relative resolution (see Note 1) [IOCA-7-881]|
| | COMPR (1) | | Compression algorithm [IOCA-7-882]|
| | DATAPOS (8) | | File offset to the beginning of the tile [IOCA-7-883]|
| Image Encoding | ID (1) | X'95' [IOCA-7-884]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-885]| |
| | COMPRID (1) | X'01', X'03', X'08', X'0D', X'0E', X'20', X'82' – X'83' | X'01' IBM MMR (see Note 2)<br>X'03' No Compression<br>X'08' ABIC (see Note 2)<br>X'0D' TIFF LZW<br>X'0E' TIFF LZW with Diff Predictor<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR (see Note 2)<br>X'83' JPEG algorithms (see Note 3) [IOCA-7-886]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-887]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-888]|
| IDE Size | ID (1) | X'96' [IOCA-7-889]| |
| | LENGTH (1) | X'01' [IOCA-7-890]| |
| | IDESZ (1) | X'01', X'04', X'10', X'18', X'20', X'28', X'30', X'38', X'40', X'48', X'50', X'58', X'60', X'68', X'70', X'78' | X'01' 1 bit/IDE<br>X'04' 4 bits/IDE<br>X'10'–X'78' 16–120 bits/IDE [IOCA-7-891]|

**Notes on the initial parameters:**
1. In the Tile TOC parameter, the relative resolution (RELRES) of 2 is supported only for JPEG-compressed data. Using a RELRES of 2 for non-JPEG data results in exception EC-B610 being raised. Note that this restriction on the relative resolution holds only for this function set, not for the IOCA architecture in general. For JPEG-compressed data, either value of RELRES is supported. [IOCA-7-892]
2. ABIC (Bilevel Q-Coder), IBM MMR-Modified Modified Read, G4 MMR-Modified Modified READ, and Solid Fill Rectangle are applicable only to images whose IDE size is 1 bit/band, otherwise exception condition EC-9611 is raised. [IOCA-7-893]
3. The JPEG algorithms are applicable only to CMYK images whose IDE size is 32 bits/IDE or to nColor images; otherwise exception condition EC-9611 is raised. [IOCA-7-894]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in a data tile:** [IOCA-7-895]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-896]| |
| | LENGTH (1) | X'00' [IOCA-7-897]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-898]| |
| | LENGTH (1) | X'08' [IOCA-7-899]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-900]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-901]|
| Tile Size | ID (1) | X'B6' [IOCA-7-902]| |
| | LENGTH (1) | X'08' – X'09' [IOCA-7-903]| |
| | THSIZE (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile size [IOCA-7-904]|
| | TVSIZE (4) | X'00000000' – X'7FFFFFFF' | Vertical tile size [IOCA-7-905]|
| | RELRES (1) | X'01' – X'02' | Relative resolution (see Note) [IOCA-7-906]|

**Note on the data-tile initial parameters:** In the Tile Size parameter, the relative resolution (RELRES) of 2 is supported only for JPEG-compressed data. Using a RELRES of 2 for non-JPEG data results in exception EC-B610 being raised. Note that this restriction on the relative resolution holds only for this function set, not for the IOCA architecture in general. For JPEG-compressed data, either value of RELRES is supported. [IOCA-7-907]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Initial parameters in a referencing tile:** [IOCA-7-908]| | | |
| Begin Tile | ID (1) | X'8C' [IOCA-7-909]| |
| | LENGTH (1) | X'00' [IOCA-7-910]| |
| Tile Position | ID (1) | X'B5' [IOCA-7-911]| |
| | LENGTH (1) | X'08' [IOCA-7-912]| |
| | XOFFSET (4) | X'00000000' – X'7FFFFFFF' | Horizontal tile origin [IOCA-7-913]|
| | YOFFSET (4) | X'00000000' – X'7FFFFFFF' | Vertical tile origin [IOCA-7-914]|
| Include Tile | ID (2) | X'FEB8' [IOCA-7-915]| |
| | LENGTH (2) | X'0004' [IOCA-7-916]| |
| | TIRID (4) | X'00000000' – X'FFFFFFFF' | Resource Tile local identifier [IOCA-7-917]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-918]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=1:** [IOCA-7-919]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-920]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-921]| |
| | COMPRID (1) | X'01', X'03', X'08', X'20', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'20' Solid Fill Rectangle<br>X'82' G4 MMR [IOCA-7-922]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-923]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-924]|
| IDE Size | ID (1) | X'96' [IOCA-7-925]| |
| | LENGTH (1) | X'01' [IOCA-7-926]| |
| | IDESZ (1) | X'01' | 1 bit/IDE [IOCA-7-927]|
| Band Image | ID (1) | X'98' [IOCA-7-928]| |
| | LENGTH (1) | X'02' [IOCA-7-929]| |
| | BCOUNT (1) | X'01' | One band [IOCA-7-930]|
| | BITCNT (1) | X'01' | 1 bit/IDE [IOCA-7-931]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-932]| |
| | LENGTH (1) | X'06' – X'08' [IOCA-7-933]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'00000' [IOCA-7-934]|
| | FORMAT (1) | X'02', X'12' | X'02' YCrCb<br>X'12' YCbCr [IOCA-7-935]|
| | RESERVED (3) | X'000000' [IOCA-7-936]| |
| | SIZE1 (1) | X'01' | 1 bit/IDE [IOCA-7-937]|
| | SIZE2 (1) | X'00' | 0 bits/IDE [IOCA-7-938]|
| | SIZE3 (1) | X'00' | 0 bits/IDE [IOCA-7-939]|
| Tile Set Color | ID (1) | X'B7' [IOCA-7-940]| |
| | LENGTH (1) | X'0B', X'0C' [IOCA-7-941]| |
| | CSPACE (1) | X'04', X'08' | X'04' CMYK<br>X'08' CIELab [IOCA-7-942]|
| | RESERVED (3) | X'000000' [IOCA-7-943]| |
| | SIZE1–SIZE3 (1) | X'08' | Bits/IDE for components 1-3 [IOCA-7-944]|
| | SIZE4 (1) | X'00', X'08' | Bits/IDE for component 4 [IOCA-7-945]|
| | CVAL1–CVAL4 (1) | X'00' – X'FF' | Color values [IOCA-7-946]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-947]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=4:** [IOCA-7-948]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-949]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-950]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'82' G4 MMR [IOCA-7-951]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-952]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-953]|
| IDE Size | ID (1) | X'96' [IOCA-7-954]| |
| | LENGTH (1) | X'01' [IOCA-7-955]| |
| | IDESZ (1) | X'04' | 4 bits/IDE [IOCA-7-956]|
| Band Image | ID (1) | X'98' [IOCA-7-957]| |
| | LENGTH (1) | X'05' [IOCA-7-958]| |
| | BCOUNT (1) | X'04' | Four bands: CMYK [IOCA-7-959]|
| | BITCNT (1) | X'01' | 1 bit/IDE for C band [IOCA-7-960]|
| | BITCNT (1) | X'01' | 1 bit/IDE for M band [IOCA-7-961]|
| | BITCNT (1) | X'01' | 1 bit/IDE for Y band [IOCA-7-962]|
| | BITCNT (1) | X'01' | 1 bit/IDE for K band [IOCA-7-963]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-964]| |
| | LENGTH (1) | X'09' [IOCA-7-965]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-966]|
| | FORMAT (1) | X'04' | CMYK [IOCA-7-967]|
| | RESERVED (3) | X'000000' [IOCA-7-968]| |
| | SIZE1 (1) | X'01' | 1 bit/IDE (C component) [IOCA-7-969]|
| | SIZE2 (1) | X'01' | 1 bit/IDE (M component) [IOCA-7-970]|
| | SIZE3 (1) | X'01' | 1 bit/IDE (Y component) [IOCA-7-971]|
| | SIZE4 (1) | X'01' | 1 bit/IDE (K component) [IOCA-7-972]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-973]|
| :--- | :--- | :--- | :--- |
| **Parameters used when IDESZ=32 and FORMAT=CMYK:** [IOCA-7-974]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-975]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-976]| |
| | COMPRID (1) | X'03', X'0D', X'0E', X'83' | X'03' No Compression<br>X'0D' TIFF LZW<br>X'0E' TIFF LZW with Diff Predictor<br>X'83' JPEG [IOCA-7-977]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-978]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-979]|
| IDE Size | ID (1) | X'96' [IOCA-7-980]| |
| | LENGTH (1) | X'01' [IOCA-7-981]| |
| | IDESZ (1) | X'20' | 32 bits/IDE [IOCA-7-982]|
| Band Image | ID (1) | X'98' [IOCA-7-983]| |
| | LENGTH (1) | X'05' [IOCA-7-984]| |
| | BCOUNT (1) | X'04' | 4 bands: CMYK [IOCA-7-985]|
| | BITCNT (1) | X'08' | 8 bits/IDE for C band [IOCA-7-986]|
| | BITCNT (1) | X'08' | 8 bits/IDE for M band [IOCA-7-987]|
| | BITCNT (1) | X'08' | 8 bits/IDE for Y band [IOCA-7-988]|
| | BITCNT (1) | X'08' | 8 bits/IDE for K band [IOCA-7-989]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-990]| |
| | LENGTH (1) | X'09' [IOCA-7-991]| |
| | FLAGS (1) | | ASFLAG B'0' Additive<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-992]|
| | FORMAT (1) | X'04' | CMYK [IOCA-7-993]|
| | RESERVED (3) | X'000000' [IOCA-7-994]| |
| | SIZE1 (1) | X'08' | 8 bits/IDE (C component) [IOCA-7-995]|
| | SIZE2 (1) | X'08' | 8 bits/IDE (M component) [IOCA-7-996]|
| | SIZE3 (1) | X'08' | 8 bits/IDE (Y component) [IOCA-7-997]|
| | SIZE4 (1) | X'08' | 8 bits/IDE (K component) [IOCA-7-998]|

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-999]|
| :--- | :--- | :--- | :--- |
| **Parameters used when FORMAT=nColor:** [IOCA-7-1000]| | | |
| Image Encoding | ID (1) | X'95' [IOCA-7-1001]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-1002]| |
| | COMPRID (1) | X'03', X'0D', X'0E', X'83' | X'03' No Compression<br>X'0D' TIFF LZW<br>X'0E' TIFF LZW with Diff Predictor<br>X'83' JPEG [IOCA-7-1003]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-1004]|
| | BITORDR (1) | X'00' – X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-1005]|
| IDE Size | ID (1) | X'96' [IOCA-7-1006]| |
| | LENGTH (1) | X'01' [IOCA-7-1007]| |
| | IDESZ (1) | X'10', X'18', X'20', X'28', X'30', X'38', X'40', X'48', X'50', X'58', X'60', X'68', X'70', X'78' | X'10'–X'78' 16–120 bits/IDE (n=2–15) [IOCA-7-1008]|
| Band Image | ID (1) | X'98' [IOCA-7-1009]| |
| | LENGTH (1) | X'03' – X'10' [IOCA-7-1010]| |
| | BCOUNT (1) | X'02' – X'0F' | 2–15 bands [IOCA-7-1011]|
| | BITCNT (1) | X'08' | 8 bits/IDE for each band [IOCA-7-1012]|
| IDE Structure | ID (1) | X'9B' [IOCA-7-1013]| |
| | LENGTH (1) | X'07' – X'14' [IOCA-7-1014]| |
| | FLAGS (1) | | ASFLAG (Ignored)<br>GRAYCODE B'0' No gray coding<br>RESERVED B'000000' [IOCA-7-1015]|
| | FORMAT (1) | X'8n' | nColor (X'2' ≤ n ≤ X'F') [IOCA-7-1016]|
| | RESERVED (3) | X'000000' [IOCA-7-1017]| |
| | SIZE1–SIZEn (1) | X'08' | 8 bits/IDE for each component [IOCA-7-1018]|

**Note on the parameters used when FORMAT=nColor:** When using FORMAT=nColor, generating implementations are very strongly recommended to also include an nColor Names parameter here, just after the IDE Structure parameter, and receiving implementations are very strongly recommended to support that parameter here. This allows an nColor FS48 IOCA image to specify the names of the colors being used. For more information on this recommendation, see Note 9. [IOCA-7-1019]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Final parameters in a tile:** [IOCA-7-1020]| | | |
| Begin Transparency Mask | ID (1) | X'8E' [IOCA-7-1021]| |
| | LENGTH (1) | X'00' [IOCA-7-1022]| |
| Image Size | ID (1) | X'94' [IOCA-7-1023]| |
| | LENGTH (1) | X'09' [IOCA-7-1024]| |
| | UNITBASE (1) | X'00' – X'01' | X'00' 10 in; X'01' 10 cm [IOCA-7-1025]|
| | HRESOL (2) | X'0001' – X'7FFF' [IOCA-7-1026]| |
| | VRESOL (2) | X'0001' – X'7FFF' [IOCA-7-1027]| |
| | HSIZE (2) | X'0001' – X'7FFF' [IOCA-7-1028]| |
| | VSIZE (2) | X'0001' – X'7FFF' [IOCA-7-1029]| |
| Image Encoding | ID (1) | X'95' [IOCA-7-1030]| |
| | LENGTH (1) | X'02' – X'03' [IOCA-7-1031]| |
| | COMPRID (1) | X'01', X'03', X'08', X'82' | X'01' IBM MMR<br>X'03' No Compression<br>X'08' ABIC<br>X'82' G4 MMR [IOCA-7-1032]|
| | RECID (1) | X'01', X'04' | X'01' RIDIC<br>X'04' Unpadded RIDIC [IOCA-7-1033]|
| | BITORDR (1) | X'00', X'01' | X'00' L-to-R<br>X'01' R-to-L [IOCA-7-1034]|
| Image Data | ID (2) | X'FE92' [IOCA-7-1035]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-1036]| |
| | DATA | Any | IDEs (bilevel only) [IOCA-7-1037]|
| End Transparency Mask | ID (1) | X'8F' [IOCA-7-1038]| |
| | LENGTH (1) | X'00' [IOCA-7-1039]| |

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments [IOCA-7-1040]|
| :--- | :--- | :--- | :--- |
| **Image Data** | (IDESZ=1 only) [IOCA-7-1041]| | |
| | ID (2) | X'FE92' [IOCA-7-1042]| |
| | LENGTH (2) | X'0001' – X'FFFF' [IOCA-7-1043]| |
| | DATA | Any | IDEs (bilevel only) [IOCA-7-1044]|
| *or:* [IOCA-7-1045]| | | |
| **Band Image Data** | (FORMAT=CMYK only) [IOCA-7-1046]| | |
| | | | Four bands, in order by BANDNUM, in the following format: [IOCA-7-1047]|
| | ID (2) | X'FE9C' [IOCA-7-1048]| |
| | LENGTH (2) | X'0003' – X'FFFF' | (see Note) [IOCA-7-1049]|
| | BANDNUM (1) | X'01' – X'04' | X'01' Band contains C component<br>X'02' Band contains M component<br>X'03' Band contains Y component<br>X'04' Band contains K component [IOCA-7-1050]|
| | RESERVED (2) | X'0000' [IOCA-7-1051]| |
| | DATA | Any [IOCA-7-1052]| |
| *or:* [IOCA-7-1053]| | | |
| **Band Image Data** | (FORMAT=nColor only) [IOCA-7-1054]| | |
| | | | n bands, in order by BANDNUM, in the following format: [IOCA-7-1055]|
| | ID (2) | X'FE9C' [IOCA-7-1056]| |
| | LENGTH (2) | X'0003' – X'FFFF' | (see Note) [IOCA-7-1057]|
| | BANDNUM (1) | X'01' – X'0F' | X'0n' Band contains the nth color component [IOCA-7-1058]|
| | RESERVED (2) | X'0000' [IOCA-7-1059]| |
| | DATA | Any [IOCA-7-1060]| |
| End Tile | ID (1) | X'8D' [IOCA-7-1061]| |
| | LENGTH (1) | X'00' [IOCA-7-1062]| |

**Note on the tile-final parameters:** Band Image Data parameters with a length of X'0003' do not have a data field. The receiver generates zeros for the corresponding band. [IOCA-7-1063]

| IOCA Self-defining Field | Parameter (Bytes) | Acceptable Value | Comments |
| :--- | :--- | :--- | :--- |
| **Final parameters in FS48:** [IOCA-7-1064]| | | |
| End Image Content | ID (1) | X'93' [IOCA-7-1065]| |
| | LENGTH (1) | X'00' [IOCA-7-1066]| |
| End Segment | ID (1) | X'71' [IOCA-7-1067]| |
| | LENGTH (1) | X'00' [IOCA-7-1068]| |


