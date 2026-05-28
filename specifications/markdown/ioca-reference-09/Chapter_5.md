# Chapter 5. IOCA Image Segment
This chapter:
• Briefly describes the IOCA Image Segment [IOCA-5-001]
• States the purpose of each IOCA self-defining field in the Image Segment [IOCA-5-002]
• Provides the syntax and semantics of each self-defining field, its parameter set, and its exception conditions [IOCA-5-003]
For an explanation of the layout of the syntax diagrams in this chapter, see “How to Read the Syntax Diagrams”. For an explanation of the notation conventions, see “Notation Conventions”. [IOCA-5-004]


## Image Segment
An Image Segment is represented by a set of self- defining fields, fields that describe their own contents. It starts with a Begin Segment, and ends with an End Segment.
Between the Begin Segment and End Segment is the image information to be processed, called the Image Content.
The Image Content can be either untiled or tiled.
Untiled image content consists of:
• Image Data parameters that describe the [IOCA-5-005]
characteristics of the image data
• An optional Transparency Mask [IOCA-5-006]
• Zero or more image data elements: Image Data and [IOCA-5-007]
## Band Image Data
Tiled image content consists of:
• Image Data parameters that describe the [IOCA-5-008]
characteristics of the image content
• Zero or more Tiles [IOCA-5-009]
Each tile consists of:
• Image Data parameters that describe the [IOCA-5-010]
characteristics of the image data
• An optional Transparency Mask [IOCA-5-011]
• Zero or more image data elements: Image Data and [IOCA-5-012]
## Band Image Data
Multiple image contents may exist within a single IOCA image segment. All image contents share the same Image Presentation Space and are presented in the order they appear. [IOCA-5-013]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-014]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-015]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-016]
## Image Data Elements
## End Transparency Mask
## Image Segment


## Begin Segment
The Begin Segment parameter defines the beginning of the Image Segment. [IOCA-5-017]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'70' Begin Segment | M [IOCA-5-018]|
| 1 | UBIN | LENGTH | X'00' – X'04' | Length of the parameters to follow | M [IOCA-5-019]|
| 2 | UBIN | NAME | | X'00000000' – X'FFFFFFFF' Name of the Image Segment | O [IOCA-5-020]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-700F Invalid sequence Condition: A Begin Segment is missing, or it appeared out of sequence or more than once. IOCA receivers can generate an out-of-sequence exception condition—EC-xx0F—instead of EC-700F , where xx is the one-byte ID code of the IOCA self-defining field encountered in place of the Begin Segment self-defining field. [IOCA-5-021]
## Begin Segment


## End Segment
The End Segment parameter defines the end of the Image Segment. [IOCA-5-022]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'71' End Segment | M [IOCA-5-023]|
| 1 | UBIN | LENGTH | X'00' | Length of the parameters to follow | M [IOCA-5-024]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-710F Invalid sequence Condition: An End Segment is missing, or it appeared out of sequence. [IOCA-5-025]
## End Segment


## Image Content
An Image Content begins with a Begin Image Content and ends with an End Image Content.
The Image Content can be either untiled or tiled.
If the Image Content is untiled, it contains a number of Image Data parameters, followed by an optional Transparency Mask, followed by the Image Data. The Image Data is contained in one or more self-defining fields. The same Image Data parameter cannot appear more than once within a single Image Content.
If the Image Content is tiled, it starts with a Tile T able of Contents, followed optionally by a number of parameters that set the default values, followed by zero or more Tiles. The structure of each tile is very similar to that inside an untiled Image Content, with Image Data parameters, an optional Transparency Mask, and Image Data. [IOCA-5-026]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-027]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-028]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-029]
## Image Data Elements
## End Transparency Mask
## Image Content


## Begin Image Content
The Begin Image Content parameter defines the beginning of the Image Content. [IOCA-5-030]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'91' Begin Image Content | M [IOCA-5-031]|
| 1 | UBIN | LENGTH | X'01' | Length of the parameters to follow | M [IOCA-5-032]|
| 2 | CODE | OBJTYPE | | X'FF' Object type: X'FF' IOCA image object. All other values are reserved. | M [IOCA-5-033]|

**Notes for Begin Image Content OBJTYPE:**
1. IOCA allows multiple image contents in a single Image Segment, but the receivers are not required to support more than one image content in each image segment. If a receiver that does not support multiple image contents in a single image segment receives a second Begin Image Content Parameter in an image segment, exception EC-910F exists. [IOCA-5-034]
2. All receivers that support multiple image contents must support at least 128 image contents per image segment. [IOCA-5-035]
3. Architecture does not restrict the number of image contents contained within a single image segment. If an image segment contains too many image contents for a receiver to present, the receiver should take the same action as if too many image objects were specified on a page. [IOCA-5-036]
4. If a receiver supports multiple image contents, it must support them for any type of image. For example, such a receiver must process multiple image contents containing FS10 data without raising an exception, even though the FS10 definition specifies a single image content in each image segment. [IOCA-5-037]
5. Multiple image contents are treated by the receiver as if they were sent as multiple image objects, in the same order in which they appear in the image segment. [IOCA-5-038]
6. All of the image contents are presented using the same Image Presentation Space characteristics, as defined in the image data descriptor for the image object. [IOCA-5-039]
7. Function Sets 45 and 48 are the only current function sets that require receivers to support multiple image contents in a single image segment. [IOCA-5-040]

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The OBJTYPE value is not in the valid range.
EC-910F Invalid sequence Condition: One or more of the following conditions holds: [IOCA-5-041]
## Begin Image Content


• A Begin Image Content is missing, or it appeared out of sequence. IOCA receivers can generate an out-of-sequence [IOCA-5-042]
exception condition—EC-xx0F—instead of EC-910F , where xx is the one-byte ID code of the IOCA self-defining field encountered in place of the Begin Image Content parameter.
• The Begin Image Content has appeared more than once and the receiver supports only a single image content in each [IOCA-5-043]
image segment.
## Begin Image Content


## End Image Content
The End Image Content parameter defines the end of the Image Content. [IOCA-5-044]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'93' End Image Content | M [IOCA-5-045]|
| 1 | UBIN | LENGTH | X'00' | Length of the parameters to follow | M [IOCA-5-046]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-930F Invalid sequence Condition: An End Image Content is missing, or it appeared out of sequence. IOCA receivers can generate an out-of- sequence exception condition—EC-xx0F—instead of EC-930F , where xx is the one-byte ID code of the IOCA self-defining field encountered in place of the End Image Content parameter. [IOCA-5-047]
## End Image Content


## Image Data Parameters
Image Data parameters describe the characteristics of the image data within a particular Image Content. They do not affect the image data in other Image Contents.
This section describes:
• Image Size parameter [IOCA-5-048]
• Image Encoding parameter [IOCA-5-049]
• IDE Size parameter [IOCA-5-050]
• Band Image parameter [IOCA-5-051]
• IDE Structure parameter [IOCA-5-052]
• nColor Names parameter [IOCA-5-053]
• External Algorithm Specification parameter [IOCA-5-054]
• Image Subsampling parameter [IOCA-5-055]
The Image Size parameter must exist in each untiled Image Content; the other Image Data parameters are optional. The Image Size parameter must not exist in a tiled image content. Some optional parameters are not permitted in some Function Sets. If you omit an optional parameter permissible in the function set, its default value is used.
In a tiled Image Content, the Image Data parameters described in this section can appear either within Tiles or before the first tile. Any value set in an Image Data parameter specified before the first tile is used as a default in all the tiles. The same Image Data parameter can appear outside of tiles and within a tile, in which case the values specified within the tile are used.
A function set is a set of self-defining fields that describes an Image Object. For more information on function sets, see “Function Sets”. [IOCA-5-056]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-057]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-058]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-059]
## Image Data Elements
## End Transparency Mask
## Image Data Parameters


## Image Size
This self-defining field, which is mandatory in non-tiled Image Contents, describes the measurement characteristics of the image when it is created. There is no default value. [IOCA-5-060]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'94' Image Size parameter M 1 UBIN LENGTH X'09' Length of the parameters to follow | M [IOCA-5-061]|
| 2 | CODE | UNITBASE | | X'00' – X'02' Unit base: X'00' 10 inches X'01' 10 centimeters X'02' Logical (resolution ratio) All other values are reserved. M 3–4 UBIN HRESOL X'0000' – X'7FFF' Horizontal resolution M 5–6 UBIN VRESOL X'0000' – X'7FFF' Vertical resolution M 7–8 UBIN HSIZE X'0000' – X'7FFF' Horizontal size in image points (excluding any padding bit in each scan line) M 9–10 UBIN VSIZE X'0000' – X'7FFF' Vertical size in image points (excluding any padding scan line) M UNITBASE=X'02' (logical) indicates that the following HRESOL and VRESOL specify a ratio of the horizontal and vertical resolutions. The combinations of UNITBASE, HRESOL, and VRESOL have the following meanings: • When UNITBASE=X'00' or X'01': – When HRESOL or VRESOL (or both) is zero, the resolution of the Image Content in that direction is undefined. Image Contents with undefined resolutions are written with each image point mapped onto one point in the Image Presentation Space. – Nonzero HRESOL or VRESOL values, divided by 10, yield the number of image points per inch or per centimeter in the corresponding direction. Example: If the distance between image points is 1/200th of an inch, the resolutions are specified as X'0007D007D0'. This means that there are 2000 image points per 10 inches in both the horizontal and vertical directions. • With UNITBASE=X'02': – When either HRESOL or VRESOL is zero, the Image Content's resolutions in both directions are undefined. Image Contents with undefined resolutions are written with each image point mapped on a point in the Image Presentation Space. – Dividing a nonzero HRESOL value by a nonzero VRESOL value yields the ratio of the horizontal and vertical resolutions. Example: X'0200010002' means that the vertical resolution is twice the horizontal resolution, and that the image is sharper in the vertical direction than in the horizontal direction. T o keep this ratio, the controlling environment allows you to define the Image Presentation Space so as to have the doubled resolution in the vertical | direction. [IOCA-5-062]|

## Image Size


The total number of image points, excluding any padding bit and padding scan line, in the image data can be obtained by multiplying the nonzero HSIZE and VSIZE values.
For non-tiled images, HSIZE=X'00' means that the image data has an unknown horizontal size, and VSIZE= X'00' means that it has an unknown vertical size. These are valid only for compression algorithms where the IOCA Process Model can determine the width or height of the image from the image data during decompression time.
Note: The width or height determined by the IOCA Process Model may be larger than the actual image width or height, as the image data may include padding bits or padding scan lines.
HSIZE=X'00' or VSIZE=X'00' for other compression algorithms raises exception condition EC-9411. See Appendix A, “Compression and Recording Algorithms” for details.
When VSIZE=X'00', the actual vertical size of such image data is determined after all image data is received.
For example, with IBM MMR-Modified Modified Read, the vertical size is determined when the end-of-page (EOP) condition is detected. See Appendix A, “Compression and Recording Algorithms” for details.
Note: IOCA generators should set HSIZE and VSIZE to the image's actual width and height regardless of the compression algorithm used. Setting either HSIZE or VSIZE to zero might cause some IOCA receivers to abort prematurely. [IOCA-5-063]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The HRESOL, VRESOL, HSIZE, or VSIZE value is not in the valid range.
Note: It is recommended that IOCA receivers generate exception EC-9410 instead of exception EC-0004 for this condition.
EC-940F Invalid sequence Condition: An Image Size parameter is missing, or it appeared out of sequence or more than once.
EC-9410 Invalid or unsupported Image Data parameter value Condition: The Image Size parameter contains an invalid or unsupported value.
EC-9411 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: HSIZE or VSIZE is zero (X'0000'), and the size in that direction cannot be determined from the image data.
The following exception condition causes a unique action to be taken:
EC-9401 Inconsistent Image Size parameter value and Image Data Condition: The size detected in the image data is different from the HSIZE or VSIZE value of the Image Size parameter.
System action: The size detected from the image data is used. [IOCA-5-064]
## Image Size


## Image Encoding
This optional self-defining field describes the algorithms by which the image data is encoded. See Appendix A, “Compression and Recording Algorithms” for details. [IOCA-5-065]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'95' Image Encoding parameter M 1 UBIN LENGTH X'02' – X'03' Length of the parameters to follow | M [IOCA-5-066]|
| 2 | CODE | COMPRID | | X'00' – X'0E', X'20', X'80' – X'84', X'A0' – X'AF', X'FE' Compression algorithm: X'00' (Retired) X'01' IBM MMR-Modified Modified Read X'02' (Retired) X'03' No compression X'04' (Retired) X'05' (Retired) X'06' RL4 (Run Length 4) X'07' (Retired) X'08' ABIC (Bilevel Q-Coder) X'09' TIFF algorithm 2 X'0A' Concatenated ABIC X'0B' Color compression used by OS/2 Image Support X'0C' TIFF PackBits X'0D' TIFF LZW X'0E' TIFF LZW with Differencing Predictor X'20' Solid Fill Rectangle X'80' G3 MH-Modified Huffman (ITU-TSS T .4 Group 3 one-dimensional coding standard for facsimile) X'81' G3 MH-Modified READ (ITU-TSS T .4 Group 3 two-dimensional coding option for facsimile) X'82' G4 MMR-Modified Modified READ (ITU-TSS T .6 Group 4 two-dimensional coding standard for facsimile) X'83' JPEG algorithms (See the External Algorithm Specification parameter for detail) X'84' JBIG2 X'A0'– X'AF' (Retired) X'FE' User-defined algorithms (see the External Algorithm Specification parameter for details) All other values are reserved. | M [IOCA-5-067]|
| 3 | CODE | RECID | | X'00' – X'04', X'FE' Recording algorithm: X'00' (Retired) X'01' RIDIC (Recording Image Data Inline Coding) X'03' Bottom-to-T op X'04' Unpadded RIDIC X'FE' See the External Algorithm Specification parameter for details All other values are reserved. | M [IOCA-5-068]|
| 4 | CODE | BITORDR | | X'00' – X'01' Bit order within each image data byte: X'00' Left-to-right X'01' Right-to-left All other values are reserved. | O [IOCA-5-069]|

## Image Encoding


Notes:
1. When COMPRID or RECID are X'FE', the External Algorithm Specification parameter must also be present within the same Image Content, otherwise exception condition EC-9F01 exists. [IOCA-5-070]
2. The External Algorithm Specification Parameter is no longer required when COMPRID is X'83'. If the decompressor in the receiver fails because the compressed datastream requires a feature unimplemented in the decoder, exception EC-9511 occurs. [IOCA-5-071]
3. The Solid Fill Rectangle compression algorithm can be used only within tiled images, for bilevel tiles. [IOCA-5-072]
Otherwise, exception EC-9510 occurs. This compression algorithm indicates that all the image points in the tile are set to the same color and that the tile does not contain any actual image data.
4. JBIG2 is a toolkit with many different capabilities. The standard recognizes a number of profiles that serve the same function as Function Sets in IOCA. Receivers declaring the JBIG2 support must support at least one JBIG2 profile, but are not obliged to support all of them. If a receiver encounters JBIG2-compressed data encoding unsupported function, exception EC-9511 occurs. [IOCA-5-073]
5. LZW encoders sometimes terminate the data early. For either the TIFF LZW or the TIFF LZW with Differencing Predictor compression algorithms, if the LZW decoder does not produce the expected number of bytes, no exception should be raised and the receiver should fill the remaining data with binary zeros. [IOCA-5-074]
BITORDR indicates the bit order within each image data byte. Figure 14, for example, shows a bilevel image with a width of eight image points:
Figure 14. Top Three Lines of a Bilevel Image The uncompressed serial bit stream for the top three lines would be:
B'00011010 00001101 01110001 ...' When the bits are packed into image data bytes, with BITORDR=X'00', the first three bytes would be as follows:
B'00011010 00001101 01110001 ...' For BITORDR=X'01', the first three bytes of the image data would be:
B'01011000 10110000 10001110 ...' If the image data is compressed, the BITORDR parameter denotes the bit order within each compressed image data byte prior to decompression.
Zero is the default for BITORDR if it is absent.
If the Image Encoding parameter is not present, the defaults are X'03' for the compression algorithm, X'01' for the recording algorithm, and zero for the bit order. [IOCA-5-075]
## Image Encoding


## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-950F Invalid sequence Condition: The Image Encoding parameter is required in some function sets but missing, or it appeared out of sequence or more than once.
EC-9510 Invalid or unsupported Image Data parameter value Condition: The Image Encoding parameter contains an invalid or unsupported value.
The following exception condition causes a unique action to be taken:
EC-9511 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: The decoder encountered one of the following conditions when decompressing the image data:
• The image data is not encoded according to the compression or recording algorithm specified in the Image Encoding [IOCA-5-076]
parameter.
• The image data cannot be decoded successfully using the size values specified in the Image Size parameter. This [IOCA-5-077]
condition applies to compression or recording algorithms that do not permit the image size to be encoded in the image data.
• The image data is not in complete accordance with the compression algorithm specified in the Image Encoding [IOCA-5-078]
parameter.
• Image is encoded using the algorithm specified in the Image Encoding Parameter, but uses a function of the algorithm [IOCA-5-079]
that is unsupported by the receiver.
System action: Receivers should attempt to present or make use of all successfully decompressed image data. Note, however, that the resulting partial image might differ from the original image. [IOCA-5-080]
## Image Encoding


## IDE Size
This optional self-defining field specifies the number of bits that comprise each Image Data Element (IDE) in the image data, before any subsampling or compression method is performed on the IDEs. [IOCA-5-081]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'96' IDE Size parameter M 1 UBIN LENGTH X'01' Length of the parameters to follow | M [IOCA-5-082]|
| 2 | UBIN | IDESZ | | X'01' – X'FF' Number of bits in each IDE M If the IDE Size parameter is not present, the default value for IDESZ is 1 (bilevel | image). [IOCA-5-083]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The IDESZ value is not in the valid range.
Note: It is recommended that IOCA receivers generate exception EC-9610 instead of exception EC-0004 for this condition.
EC-960F Invalid sequence Condition: The IDE Size parameter appeared out of sequence or more than once.
EC-9610 Invalid or unsupported Image Data parameter value Condition: The IDE Size parameter contains an invalid or unsupported value.
EC-9611 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: The compression scheme specified in the Image Encoding parameter does not support the IDE size specified in the IDE Size parameter. [IOCA-5-084]
## IDE Size


## Band Image
This optional self-defining field describes the format of one or more bands that represent an image. A band is a plane where, typically, image data of similar attributes is placed. Certain bits of an IDE can be placed into separate bands, for example the bits that represent the red, green, and blue color components of each IDE.
If the Band Image parameter is present, then the image data must be carried by the Band Image Data parameter. Each band of the image IDEs is carried by one or more Band Image Data parameters. The Band Image Data parameter is described in “Band Image Data”. [IOCA-5-085]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'98' Band Image parameter M 1 UBIN LENGTH X'02' – X'FE' Length of the parameters to follow | M [IOCA-5-086]|
| 2 | UBIN | BCOUNT | | X'01' – X'FD' Number of bands M One or more repeating groups in the following | format: [IOCA-5-087]|
| 0 | UBIN | BITCNT | | X'01' – X'FF' Bit count for the band M BITCNT specifies how many bits of the IDE comprise one band, and BCOUNT specifies how many bands comprise the image data. The number of BITCNT s in the self-defining field must equal the BCOUNT value. The BITCNT s appear in the order in which the bits were placed into the band. For boundary alignment purposes, BITCNT can include padding bits inserted into the data. If BITCNT contains no padding bits, then the sum of all the BITCNT values equals the IDE size specified by the IDE Size parameter. Example 1: For a single-band image with an IDE size of four with no padding bit, the first four bits of data represent the first IDE, the next four represent the second IDE, and so on. Figure 15 illustrates the layout of image bits in this image. Figure 15. Example of a Four-Bit Single-Band Image with No Padding Bit IDE1 IDE2 IDE3 IDE4 | IDEn [IOCA-5-088]|
| 0000 | 1000 | 0110 | | 1010 1110 XXXX Band | 1 [IOCA-5-089]|

## Band Image


Example 2: For an image with an IDE size of four that is represented by four bands with no padding bit, the first bit in each of the four bands represents the first IDE, the second bit represents the second IDE, and so on.
Figure 16 illustrates the layout of image bits in this image.
Figure 16. Example of a Four-Bit Four-Band Image with No Padding Bit IDE1 0 1 1 1 1 11 0 0 1 0 0 0 0 0 X X X X X 0 1 1 X 0 0 1 X
0 1 1 X IDE2 IDE3 IDEn Band 1 Band 2 Band 3 Band 4 [IOCA-5-090]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The BCOUNT or BITCNT value is not in the valid range.
Note: It is recommended that IOCA receivers generate exception EC-9810 instead of exception EC-0004 for this condition.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-9801 Invalid Band Image parameter and Image Subsampling parameter coexistence Condition: In some function sets, the Band Image parameter and the Image Subsampling parameter cannot coexist in the same Image Content.
EC-980F Invalid sequence Condition: The Band Image parameter appeared out of sequence or more than once. [IOCA-5-091]
## Band Image


EC-9810 Invalid or unsupported Image Data parameter value Condition: The Band Image parameter contains an invalid or unsupported value.
EC-9814 Invalid number of bands and bit counts Condition: The number of BITCNT parameters is not equal to the BCOUNT in the Band Image parameter.
EC-9815 Invalid IDE size Condition: The IDE size, determined by the Band Image parameter, does not match the IDE Size parameter. [IOCA-5-092]
## Band Image


## IDE Structure
This optional self-defining field describes the structure of each IDE for a bilevel, grayscale, or color image.
If the IDE Structure parameter is not present, each IDE of the image data consists of a single component whose size is dependent on the IDE Size parameter. If the IDE Size is 1, the IDE value of B'1' represents a significant (toned) pel, while the value of B'0' represents an insignificant (untoned) pel. If the IDE Size is more than 1, the color model is YCbCr and the value is expressed using the Y component. This is a grayscale color model, where the value of zero represents black, while the maximum value represents white.
With this self-defining field, color images are expressed by using the RGB, YCrCb, YCbCr, CMYK, or nColor model, while grayscale images are expressed by using only the Y component of the YCrCb or YCbCr model.
See Appendix B, “Bilevel, Grayscale, and Color Images” for details on the relationship with the IDE Size parameter. [IOCA-5-093]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'9B' IDE Structure parameter M 1 UBIN LENGTH X'06' – X'14' Length of the parameters to follow | M [IOCA-5-094]|
| 2 | BITS | FLAGS | | M Bit 0 ASFLAG B'0' – B'1' Additive or Subtractive: B'0' Additive B'1' Subtractive Bit 1 GRAYCODE B'0' – B'1' Gray coding: B'0' Off B'1' On Bits 2–7 B'000000' Reserved; should be zero 3 CODE FORMAT X'01', X'02', X'04', X'12', X'8n' Color model: X'01' RGB X'02' YCrCb X'04' CMYK X'12' YCbCr X'8n' nColor (X'2' ≤ n ≤ X'F') All other values are reserved. M 4–6 X'000000' Reserved; should be zero M 7 UBIN SIZE1 X'00' – X'FF' Number of bits/IDE for component 1 | M [IOCA-5-095]|
| 8 | UBIN | SIZE2 | | X'00' – X'FF' Number of bits/IDE for component 2 O 9 UBIN SIZE3 X'00' – X'FF' Number of bits/IDE for component 3 | O [IOCA-5-096]|
| 10 | UBIN | SIZE4 | | X'00' – X'FF' Number of bits/IDE for component 4 O 11 UBIN SIZE5 X'00' – X'FF' Number of bits/IDE for component 5 O | ... [IOCA-5-097]|
| 21 | UBIN | SIZE15 | | X'00' – X'FF' Number of bits/IDE for component 15 | O [IOCA-5-098]|

## IDE Structure


You can specify whether increasing IDE values correspond to brighter or darker levels of gray or color, with the ASFLAG=0 (additive) or ASFLAG=1 (subtractive) parameters, respectively. ASFLAG applies to all three components of the RGB model, all four components of the CMYK model, and to the Y component of the YCrCb and YCbCr models.
• Additive means that the maximum color value represents full intensity of that color, while the minimum color [IOCA-5-099]
value represents zero intensity. For example, in a black-and-white system, the minimum color value (usually zero) means black, and the maximum value means white.
• Subtractive means that the minimum color value represents full intensity of that color, while the maximum [IOCA-5-100]
color value represents zero intensity. For example, in a black-and-white system, the minimum color value (usually zero) means white, and the maximum value means black.
See Appendix B, “Bilevel, Grayscale, and Color Images” for more information on the use of ASFLAG. Note in particular that ASFLAG is ignored for bilevel images and for images that use the nColor color model.
FORMAT specifies the breakdown format for each IDE value:
• RGB means that each value is to be treated as a set of red, green, blue intensity values, and the set is in the [IOCA-5-101]
order red, green, blue.
• YCrCb means that each value is to be treated as a set of Y , Cr, Cb values, and the set is in the order Y , Cr, [IOCA-5-102]
Cb, where Y is the intensity, and Cr and Cb are the chrominance differences.
• YCbCr means that each value is to be treated as a set of Y , Cb, Cr values, and the set is in the order Y , Cb, [IOCA-5-103]
Cr, where Y is the intensity, and Cb and Cr are the chrominance differences.
• CMYK means that each value is to be treated as a set of cyan, magenta, yellow, black intensity values and [IOCA-5-104]
the set is in the order cyan, magenta, yellow, black.
• nColor means that each value is to be treated as a set of n separate intensity values. A color management [IOCA-5-105]
resource from the controlling environment might be required to process the n values, depending on whether the names of the colors are reported in the nColor Names parameter, and whether all the named colors are available.
GRAYCODE specifies whether or not the Gray coding scheme is used to encode the image data. Gray code is a type of binary code that is applied to the entire IDE whose size is specified in the IDE Size parameter self- defining field, not just to each individual bit plane of the IDE. Gray code is constructed such that two successive codes always differ by just one bit. T able 4shows the series of gray codes from 0 to 15 in decimal.
1 Table 4. Gray Code Values (Decimal) [IOCA-5-106]
Decimal Gray Code 0 B'0000' 1 B'0001' 2 B'0011' 3 B'0010' 4 B'0110' 5 B'0111' 6 B'0101'
7 B'0100' 8 B'1100' 9 B'1101' [IOCA-5-107]
## IDE Structure
1. Source: R. W. Lucky, J. Salz, and E. J. Weldon Jr., (New York: McGraw-Hill, 1968). [IOCA-5-108]


Table 4 Gray Code Values (Decimal) (cont'd.)
Decimal Gray Code 10 B'1111' 11 B'1110' 12 B'1010' 13 B'1011' 14 B'1001' 15 B'1000' Refer to R. Hunt, The Representation of Colour in Photography, Printing and Television (Foundation Press,
1995), for an explanation of each color model.
SIZE1, SIZE2, ..., SIZE15 specify the number of bits required to express each color component of an IDE before any subsampling or compression method is performed on the IDEs. The maximum possible value of a particular color component is equal to 2 SIZEm-1, where 1 ≤ m ≤ 15.
The SIZE parameter values must appear in the sequence of the color components whose size they specify.
For an RGB image, this sequence is R, G, and B; for a YCrCb image, it is Y , Cr, and Cb; for a YCbCr image, it is Y , Cb, and Cr; and for a CMYK image, it is C, M, Y , and K. For an nColor image, if such an image must be matched with a color management resource (CMR) in the controlling environment, the color components must be in the order expected by the CMR.
Other than nColor, the number of SIZE parameters varies from one to four, depending on the color components that are used to express each IDE.
For bilevel and grayscale images, expressed by the YCrCb or YCbCr color model, specifying SIZE1 is sufficient; SIZE2 and SIZE3 can be omitted, or you can specify zero for them. However, any preceding SIZE parameter must be included, and zero must be specified. For example, if an image uses only the third component of a color model, then SIZE1=0 and SIZE2=0 must be specified.
Other than nColor, the SIZE4 field is only allowed for the CMYK color model (IDE Size of 4 or 32), where it is mandatory. If SIZE4 is missing for the CMYK color model, or if it appears for any other non-nColor color model, exception EC-9B18 occurs.
For the CMYK color model, the color value is specified with four components. Components 1, 2, 3, and 4 are unsigned binary numbers that specify the cyan, magenta, yellow, and black intensity values, in that order.
SIZE1, SIZE2, SIZE3, and SIZE4 in the IDE Structure parameter are nonzero and define the number of bits used in each component. The intensity range for the C, M, Y , K components is 0 to 1, which is mapped to the binary value range 0 to 2 SIZEm-1, where m=1,2,3,4. This is a device-dependent color model.
For the nColor color model, the number of SIZE parameters must be equal to the second half of the X'8n' value specified in the FORMAT byte of the IDE Structure; if not, exception EC-9B18 occurs. For example, if FORMAT is specified as X'87', there must be exactly 7 SIZE parameters. Each SIZE parameter defines the number of bits used by that component.
Note: If the IDE Structure parameter is not present, the defaults are ASFLAG of B'0' (additive), GRAYCODE of B'0' (off), FORMAT of YCbCr, and SIZE1 the same as the IDE size specified in the IDE Size parameter. [IOCA-5-109]
## IDE Structure


## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 The LENGTH value is not in the valid range Condition: The LENGTH value is not in the valid range.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-9B0F Invalid sequence Condition: The IDE Structure parameter is required but missing, or it appeared out of sequence or more than once.
EC-9B10 Invalid or unsupported IDE Structure parameter value Condition: One or more of the following conditions has been encountered:
• The value of ASFLAG is invalid or unsupported. [IOCA-5-110]
• The value of GRAYCODE is invalid or unsupported. [IOCA-5-111]
• The value of FORMAT is invalid or unsupported. [IOCA-5-112]
• The value of a SIZE field is invalid or unsupported. [IOCA-5-113]
EC-9B18 Invalid IDE Structure parameter Condition: One of the following conditions has been encountered:
• The sum of the SIZE values does not match the IDE size specified by the IDE Size parameter. [IOCA-5-114]
• Color model is CMYK and SIZE4 is missing. [IOCA-5-115]
• SIZE4 is present and the color model is not CMYK or nColor. [IOCA-5-116]
• More than four SIZE parameters are present and the color model is not nColor. [IOCA-5-117]
• Color model is nColor and the number of SIZE parameters is not equal to the second half of the FORMAT byte. [IOCA-5-118]
## IDE Structure


## nColor Names
This optional self-defining field defines the names of the colors when using the nColor color model, which is done through the X'8n' value for the FORMAT field in the IDE Structure parameter. The number of color names specified in this parameter must match the value X'n' in that FORMAT field X'8n' value, or exception EC-B311 exists. If nColor Names appears in the image data parameters when the image does not use the nColor color model, exception EC-B311 also exists.
If an IOCA receiver has available all of the colorants named in this parameter, it can present the IOCA nColor image without color management. Otherwise, the colors in the nColor image must be color-managed in the controlling environment.
The data in this field is made up of n repeating groups, each of which contains a color name. [IOCA-5-119]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FEB3' nColor Names parameter M 2–3 UBIN LENGTH X'0006' – X'0EC6' Length of the parameters to follow M 4–5 X'0000' Reserved; should be zero M n repeating groups in the following format, when the IDE Structure FORMAT field is | X'8n' [IOCA-5-120]|
| 0 | X'00' | Reserved; | | should be zero M 1 UBIN NAMELEN X'00' – X'FA', even values only Length, in bytes, of the name to follow M 2–m CHAR CLRNAME any UTF-16BE characters Color name O The color names are specified using the UTF-16BE encoding. This means that the length of the name, NAMELEN, must be an even number, otherwise exception EC-B310 exists. If the color name is an invalid UTF- 16BE character string, exception EC-B310 also exists. Color names of zero-length are allowed, and state no information about the color name. If there is no nColor Names parameter, there is no information about any of the names of the colors. If the nColor Names parameter appears and correctly has an entry in the repeating groups for all n color names, but some color names have zero-length, then there is no information about the name of those colors. There is a similarity between this IOCA parameter and the Colorant Identification List tag in CMOCA, since they are both naming colors. In an IOCA nColor image, the color names are contained in the nColor Names parameter. When specifying a single “nColor” color to use in the Tile Set Color parameter—among many other similar places in AFP—the Highlight Color Space can be used in conjunction with an Indexed CMR, and in that CMR the color names are found in the Colorant Identification List tag. The names here are limited to 250 (X'FA') bytes, to match the same limit in CMOCA; if a name is longer, exception EC-B310 exists. When appropriate, the same color names should be used in both places. In particular, the following known colorant names defined in CMOCA in the Colorant Identification List tag can be used to specify colors in the device color space: AFPC_Device_C Device Cyan AFPC_Device_M Device Magenta AFPC_Device_Y Device Yellow AFPC_Device_K Device Black AFPC_Device_R Device | Red [IOCA-5-121]|

## nColor Names


AFPC_Device_G Device Green AFPC_Device_B Device Blue AFPC_Device_Gray Device Gray [IOCA-5-122]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Explanation: The LENGTH value is not in the valid range.
EC-B30F Invalid sequence Explanation: nColor Names appeared out of sequence.
EC-B310 Invalid or unsupported Image Data parameter value Explanation: Either a NAMELEN value is not in the valid range, a CLRNAME value is not a valid UTF-16BE character string, or a given colorant name appears in more than one CLRNAME value.
EC-B311 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Explanation: Either nColor Names should not be present because the nColor color model is not being used, or the number of color names in the nColor Names parameter does not match the number of colors in the nColor color model in the IDE Structure parameter. [IOCA-5-123]
## nColor Names


## External Algorithm Specification
This optional self-defining field provides complementary information about the algorithm specified in the Image Encoding parameter. It can be used only in conjunction with that parameter. [IOCA-5-124]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'9F' External Algorithm Specification parameter M 1 UBIN LENGTH X'03' – X'FF' Length of the parameters to follow | M [IOCA-5-125]|
| 2 | CODE | ALGTYPE | | X'00', X'10' Type of algorithm specified: X'00' Recording algorithm X'10' Compression algorithm All other values are reserved. | M [IOCA-5-126]|
| 3 | X'00F' | Reserved; | | should be zero M 4–n CODE ALGSPEC Recording Algorithm Specification or Compression Algorithm Specification M Recording Algorithm Specification This subparameter is carried by the External Algorithm Specification parameter . Syntax Offset Type Name Range Meaning | M/O [IOCA-5-127]|
| 0 | BITS | DIRCTN | | Direction of IDEs M Bits 0–1 IDEPTH B'11' Direction of successive IDEs along a line (clockwise from X-axis): B'11' 270 degrees All other values are reserved. Bits 2–3 LINEPRG B'00' Direction of progression of successive lines (clockwise from X-axis): B'00' 0 degrees All other values are reserved. Bit 4 RNDTRIP B'0' Direction of successive IDEs relative to the previous line (clockwise from the previous line): B'0' 0 degrees All other values are reserved. Bits 5–7 B'000' Reserved; should be zero 1 CODE PADBDRY X'03' Boundary length for padding: X'03' 32-bit boundary All other values are reserved. | M [IOCA-5-128]|
| 2 | CODE | PADALMT | | X'00' Alignment for padding: X'00' Data left-aligned within boundary All other values are reserved. | M [IOCA-5-129]|

## External Algorithm Specification


DIRCTN specifies how the IDEs are positioned in a set of image data self-defining fields. The following subparameters are defined:
• IDEPTH specifies how successive IDEs proceed along a line in relation to the X-axis of the Image [IOCA-5-130]
Coordinate System. Degrees are measured clockwise from the X-axis of the Image Coordinate System.
• LINEPRG specifies how successive lines of IDEs proceed in relation to the X-axis of the Image Coordinate [IOCA-5-131]
System. Degrees are measured clockwise from the X-axis of the Image Coordinate System.
• RNDTRIP specifies how the next line of IDEs proceeds in relation to the previous line. Degrees are [IOCA-5-132]
measured clockwise from the previous line.
PADBDRY specifies if each line of the IDEs is padded with zeros where necessary for boundary alignment purposes.
PADALMT specifies whether the padding bits used for alignment purposes are located at the beginning or at the end of each line of the IDEs.
Figure 17. IDE Progression X-axis of image coordinate system IDEPTH = B’ 11’ (270 degrees)
LINEPRG = B’ 00’ ( 0 degrees)
RNDTRIP = B’ 0’ ( 0 degrees) [IOCA-5-133]
## External Algorithm Specification


Compression Algorithm Specification This subparameter is carried by the External Algorithm Specification parameter . The syntax table specifies the JPEG compression algorithm that conforms to the following publications:
• ITU-TSS Recommendation T .81 [IOCA-5-134]
• ISO/IEC International Standard 10918-1 [IOCA-5-135]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | COMPRID | | X'83' JPEG algorithms M 1 X'00' Reserved; should be zero | M [IOCA-5-136]|
| 2 | CODE | VERSION | | X'00' Version M 3 X'00' Reserved; should be zero | M [IOCA-5-137]|
| 4 | CODE | MARKER | | X'C0' – X'C3', X'C5' – X'C7', X'C9' – X'CB', X'CD' – X'CF' Marker code: Non-differential Huffman coding: X'C0' Baseline DCT X'C1' Extended sequential DCT X'C2' Progressive DCT X'C3' Lossless (sequential) Differential Huffman coding: X'C5' Differential sequential DCT X'C6' Differential progressive DCT X'C7' Differential lossless Non-differential arithmetic coding: X'C9' Extended sequential DCT X'CA' Progressive DCT X'CB' Lossless (sequential) Differential arithmetic coding: X'CD' Differential sequential DCT X'CE' Differential progressive DCT X'CF' Differential lossless All other values are reserved. M 5–7 X'000000' Reserved; should be zero M JPEG algorithms have the following restrictions: • They cannot be applied to images whose IDE size is 1 bit/IDE. • The baseline DCT-based algorithm is applicable only to images with 8-bits/component. The other DCT- based algorithms are applicable only to images with 8-bits/component or 12-bits/component. The IDE of the image can consist of at most four components. • The lossless algorithms are applicable only to images with n-bits/component, where 2 ≤ n ≤ 16. The IDE of the image can consist of at most four | components. [IOCA-5-138]|

## External Algorithm Specification


Syntax of a User-defined Compression algorithm Offset Type Name Range Meaning M/O 0 CODE COMPRID X'FE' User-defined compression algorithm M 1 UBIN LENGTH X'04' – X'FF' Length of the parameters to follow M 2–5 CODE USRCPID Architecture-assigned user compression algorithm code point The assignment of compression code points is controlled by the IOCA data stream architecture group.
M
6–n COMPSPEC Any User-defined specification O [IOCA-5-139]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-9F01 Missing External Algorithm Specification parameter or Image Encoding parameter Condition: An External Algorithm Specification parameter exists without a corresponding Image Encoding parameter, or an Image Encoding parameter exists that requires an External Algorithm Specification parameter that cannot be found.
EC-9F0F Invalid sequence Condition: An External Algorithm Specification parameter appeared out of sequence.
EC-9F10 Invalid or unsupported Image Data parameter value Condition: The External Algorithm Specification parameter contains an invalid or unsupported value.
EC-9F11 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: An External Algorithm Specification parameter is present, but the Image Encoding parameter does not require it. [IOCA-5-140]
## External Algorithm Specification


## Image Subsampling
This optional self-defining field describes the subsampling methods used to encode the uncompressed IDEs within the image data. The methods are encoded in self-defining fields.
Subsampling is a technique of reducing the amount of image data, resulting in lower storage and processing requirements. This is accomplished by combining the color information of adjacent IDEs. If done properly, there is little or no visual degradation of the image quality.
Subsampling relies on the fact that in color images the difference between adjacent IDEs is small for certain color components. For example, in the YCrCb and YCbCr color models, most of the image information is concentrated in the Y component; hence it is fairly common to store only the average values of the Cr and Cb components of two adjacent IDEs. [IOCA-5-141]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FECE' Image Subsampling parameter M 2–3 UBIN LENGTH X'0000' – X'FFFF' Length of the parameters to follow M 4–n CODE SDF Zero or more self-defining fields that specify the subsampling methods O If the Image Subsampling parameter is not present, the default is that the IDEs have not been subsampled. Sampling Ratios This optional self-defining field is carried by the Image Subsampling parameter. It specifies the number of horizontal and vertical samples that make up each component of the IDEs. Syntax Offset Type Name Range Meaning | M/O [IOCA-5-142]|
| 0 | CODE | ID | | X'01' Sampling Ratios M 1 UBIN LENGTH X'02' – X'FE' Length of the parameters to follow | M [IOCA-5-143]|
| 1 | to | 127 | | repeating groups in the following | format: [IOCA-5-144]|
| 0 | UBIN | HSAMPLE | | X'00' – X'7F' Number of horizontal samples that make up the component of the IDEs | M [IOCA-5-145]|
| 1 | UBIN | VSAMPLE | | X'00' – X'7F' Number of vertical samples that make up the component of the IDEs M If the HSAMPLE and VSAMPLE group for a particular component of the IDEs is not present, the default value is 1 for both HSAMPLE and VSAMPLE. However, any preceding HSAMPLE and VSAMPLE group must be included. For example, a color image with only its third component subsampled must have HSAMPLE1, VSAMPLE1, HSAMPLE2, and VSAMPLE2 specified as equal to | 1. [IOCA-5-146]|

## Image Subsampling


Example: For a 24-bit YCrCb uncompressed color image that has eight bits per component using the following sampling ratios:
HSAMPLE1=2 VSAMPLE1=1 HSAMPLE2=1 VSAMPLE2=1 HSAMPLE3=1 VSAMPLE3=1 the resulting image data layout would be as follows:
Offset Content 0 The Y component value of the first IDE 1 The Y component value of the second IDE 2 The average of the first and second IDEs’ Cr component values 3 The average of the first and second IDEs’ Cb component values 4 The Y component value of the third IDE 5 The Y component value of the fourth IDE 6 The average of the third and fourth IDEs’ Cr component values
7 The average of the third and fourth IDEs’ Cb component values ... ... [IOCA-5-147]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 The LENGTH value is not in the valid range Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The HSAMPLE or VSAMPLE value is not in the valid range.
Note: It is recommended that IOCA receivers generate exception EC-CE10 instead of exception EC-0004 for this condition.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-CE01 Invalid Band Image parameter and Image Subsampling parameter coexistence Condition: In some function sets, the Band Image parameter and the Image Subsampling parameter cannot coexist in the same Image Content.
EC-CE0F Invalid sequence Condition: The Image Subsampling parameter appeared out of sequence or more than once.
EC-CE10 Invalid or unsupported Image Data parameter value Condition: The Image Subsampling parameter contains an invalid or unsupported value. [IOCA-5-148]
## Image Subsampling


## Tiles
Tiles are used when different parts of an image are described using different color spaces, resolutions, and compression algorithms. Tiles can also be used as resources (see Appendix C, “IOCA Tile Resource”). [IOCA-5-149]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-150]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-151]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-152]
## Image Data Elements
## End Transparency Mask
## Tiles


The tiling scheme used in IOCA has the following features:
• Each Image Content can be either tiled or untiled. In an untiled Image Content, no tiles may appear. Tiled [IOCA-5-153]
Image Contents are indicated by the presence of the Tile TOC parameter immediately following the Begin Image Content parameter. In a tiled Image Content, no image data elements may appear outside of the tiles.
• Tiles can use different color spaces and compression algorithms. [IOCA-5-154]
• Each tile must either have the resolution of the underlying Image Presentation Space, or be subsampled by [IOCA-5-155]
the same integer factor in both horizontal and vertical dimensions.
• Tiles must be non-overlapping and must also be specified in top-down, left-to-right order. [IOCA-5-156]
• Tiles do not have to cover the whole Image Presentation Space. The part of the Image Presentation Space [IOCA-5-157]
not covered by tiles is treated as background. Tiles must be fully contained in the Image Presentation Space.
• Within tiles, foreground and background are determined based on the color space used. [IOCA-5-158]
• A tile can be either a data tile (that is, a fully defined tile with all the data present), or a referencing tile. A [IOCA-5-159]
referencing tile contains an invocation, positioning, and merging instruction for a tile resource and is intended to save bandwidth and processing time when processing multiple images that have some areas in common. [IOCA-5-160]
## Tiles


## Begin Tile
The Begin Tile parameter defines the beginning of a tile. [IOCA-5-161]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'8C' Begin Tile parameter M 1 UBIN LENGTH X'00' Length of the parameters to follow M Notes: 1. In tiled images, all of the image data must be contained in tiles. That is, no Image Data or Band Image Data can appear outside of the sequence delimited by the Begin Tile/End Tile pairs. 2. The Begin Tile Parameter can appear in all of the contexts where Image Data and Band Image Data can appear in non-tiled images. 3. If the Begin Tile Parameter is encountered, the first parameter after the Begin Image Content parameter must be the Tile TOC parameter. Otherwise, exception EC-8C0F | occurs. [IOCA-5-162]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-8C0F Invalid sequence Condition: A Begin Tile has appeared out of sequence. [IOCA-5-163]
## Begin Tile


## End Tile
The End Tile parameter defines the end of a tile. [IOCA-5-164]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'8D' End Tile parameter M 1 UBIN LENGTH X'00' Length of the parameters to follow | M [IOCA-5-165]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-8D0F Invalid sequence Condition: An End Tile is missing after a Begin Tile has been encountered, or it appeared out of sequence. [IOCA-5-166]
## End Tile


## Tile Position
The Tile Position parameter determines the position of the upper-left corner of the tile in the Image Presentation Space. [IOCA-5-167]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'B5' Tile Position parameter M 1 UBIN LENGTH X'08' Length of the parameters to follow M 2–5 UBIN XOFFSET X'00000000' – X'7FFFFFFF' Horizontal offset of the tile origin, relative to the presentation space origin M 6–9 UBIN YOFFSET X'00000000' – X'7FFFFFFF' Vertical offset of the tile origin, relative to the presentation space origin M Notes: 1. XOFFSET and YOFFSET are specified in presentation space image points. If subsampling is specified in the Tile Size parameter, it does not apply to XOFFSET and YOFFSET . 2. The upper-left corner of the tile must be contained in the presentation space; that is, XOFFSET and YOFFSET must be less than XSIZE and YSIZE, respectively, as specified in the Image Data Descriptor. For the definition of the Image Data Descriptor, see “Image Data Descriptor (IDD)”. 3. If the current tile is not the first tile specified, the YOFFSET value must be at least as large as any specified for the previous tiles. If YOFFSET is identical to the previous YOFFSET , XOFFSET must be greater than the previous XOFFSET . This requirement forces the tile order of top down (primary key) and left to right (secondary key). This condition applies only if the Tile TOC parameter does not contain the tile table of | contents. [IOCA-5-168]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-B50F Invalid sequence Condition: A Tile Position is missing, or it appeared out of sequence.
EC-B510 Invalid Tile Position Parameters Condition: XOFFSET , YOFFSET , or both are outside of the valid range or outside of the Image Presentation Space.
EC-B511 Inconsistent Tile Position Parameters Condition: One of the following conditions has been encountered:
• Tiles are specified out of order. This exception can occur only if the Tile TOC parameter does not contain the table of [IOCA-5-169]
contents. If the Tile TOC Parameter does contain the table of contents, the tiles themselves can be specified in any order.
• Offset mismatch: the tile table of contents has been specified, but the XOFFSET or YOFFSET given for this tile does not [IOCA-5-170]
match the values specified in the Tile Position Parameter. [IOCA-5-171]
## Tile Position


## Tile Size
The Tile Size parameter defines the size and resolution of a tile. [IOCA-5-172]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'B6' Tile Size parameter M 1 UBIN LENGTH X'08' – X'09' Length of the parameters to follow M 2–5 UBIN THSIZE X'00000000' – X'7FFFFFFF' Horizontal size in image points, excluding any padding bits in each scan line M 6–9 UBIN TVSIZE X'00000000' – X'7FFFFFFF' Vertical size in image points, excluding any padding scan lines | M [IOCA-5-173]|
| 10 | UBIN | RELRES | | X'01' – X'02' Relative resolution of the tile O Notes: 1. If RELRES has not been specified, the tile resolution is the same as the resolution of the Image Presentation Space. 2. A RELRES value of 1 means that the tile has the same resolution as the Image Presentation Space. A RELRES value of 2 means the resolution of the tile is half the resolution of the Image Presentation Space. For example, if the Image Presentation Space has a resolution of 600 dpi, a tile with a RELRES of 2 has a resolution of 300 dpi. The default value of RELRES is 1. 3. The tile dimensions THSIZE and TVSIZE are specified in the tile resolution. T o get the size of the tile in presentation space points, multiply the THSIZE and TVSIZE by RELRES. 4. The tile must be wholly contained in the presentation space; that is, (XOFFSET + RELRES × THSIZE) must not exceed the XSIZE specified in the Image Data Descriptor and (YOFFSET + RELRES × TVSIZE) must not exceed the YSIZE specified in the Image Data Descriptor. 5. Tiles must be non-overlapping. If X1, Y1, H1, V1, S1 and X2, Y2, H2, V2, S2 describe the offset, size, and subsampling of any two tiles, at least one of the following relationships must hold: X1 + S1 × H1 ≤ X2 X2 + S2 × H2 ≤ X1 Y1 + S1 × V1 ≤ Y2 Y2 + S2 × V2 ≤ Y1 Note that, in this example, tiles 1 and 2 are not necessarily sorted. That is, the origin of tile 1 need not be above or left of the origin of tile 2. 6. The JPEG compression algorithm works on 8-by-8-pixel blocks. Depending on the JPEG subsampling (note that this is different from RELRES in Tile Size), the Minimum Coded Units (MCUs) used by JPEG might be larger. The most common MCU size is 16 by 16 pixels. The image must be padded before compression to the MCU boundary and the decompressor discards the padding pixels. T o help receivers merge JPEG-compressed tiles efficiently, the tile data must be padded to the left and top to the nearest 8- pixel boundary in the tile resolution, after applying tile subsampling and before compression. After padding on the left and top, the tile is padded as usual on the right and bottom. On decompression, the decompressor discards the right and bottom padding pixels. The receiver then must discard any left and top padding pixels. The number of pad pixels on the left and top can be computed by dividing the XOFFSET and YOFFSET by RELRES×8 and taking the remainder. Note that padding is done in the Image Presentation Space image points, before subsampling. Otherwise, images with odd XOFFSET or YOFFSET could not be | aligned. [IOCA-5-174]|

## Tile Size


Example This example shows how to construct, compress, and decompress a tile with JPEG and RELRES of 2.
Let the area of the image that we wish to use as a tile have the origin of XOFFSET = 21 and YOFFSET = 36.
Let the area be 100 presentation space points wide and 211 presentation space points high. Assume that we use no JPEG subsampling. XOFFSET and YOFFSET can be used to indicate the tile origin in the Tile Position parameter. The tile size is set to THSIZE = 50 and TVSIZE = 105.
T o compress the data, start at the image point with the horizontal offset of 16 and the vertical offset of 32 in the presentation space. Select the region 112 pixels wide and 224 pixels high. If the presentation space is not large enough, pad at the right and bottom, until these dimensions are reached. Subsample by the factor of two, which yields an image 56 pixels wide and 112 pixels high. Since the image sizes are even multiples of 8 and no JPEG subsampling is desired, the data can be compressed with JPEG without further padding.
T o merge the tile into the presentation space, decompress the tile with JPEG. Upsample by a factor of two, yielding a tile that is 112 by 224 pixels. Since XOFFSET is 21, we know that the leftmost five pixels have to be discarded. Similarly, the YOFFSET value of 36 indicates a top pad of 4 pixels. From the THSIZE and TVSIZE, after upsampling, the actual tile is 100 pixels wide and 210 pixels high. Thus, the left 5 pixels, top 4 pixels, right 7 pixels and bottom 10 pixels are discarded to yield the unpadded tile. Note that a scanline on the bottom was lost due to downsampling.
In this example, the right and bottom are padded before the data is passed to the compressor. If you do not pad first, the compressor does the padding and the decompressor strips it. Manual padding, however, allows control over how the padding is done. If the tiles are constructed so that a single continuous tone image is broken into multiple adjoining tiles, selecting the actual image data for padding eliminates edge artifacts when the tiles are joined.
If the compressor allows the caller to specify the padding data, manual padding is not necessary. Note that manual padding also assumes that the receiver checks the image returned by the decompressor and discards not only the top and left pads, but also the bottom and right pads. The receiver can compute the pad sizes from the values of RELRES, XOFFSET , YOFFSET , THSIZE, and TVSIZE. [IOCA-5-175]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-A902 A portion of the extracted image is written outside the Image Presentation Space Condition: The tile is not wholly contained in the Image Presentation Space.
EC-B60F Invalid sequence Condition: A Tile Size is missing, or it appeared out of sequence.
EC-B610 Invalid Tile Size parameters Condition: The tile size or relative resolution are outside valid ranges or are invalid for the function set.
EC-B611 Inconsistent Tile Size parameters Condition: At least one of the following conditions is true:
• The tile overlaps a previously specified tile. [IOCA-5-176]
• Subsampling mismatch: the RELRES value in the table of contents does not match the RELRES value in the Tile Size [IOCA-5-177]
parameter.
## Tile Size


• Size mismatch: the THSIZE or TVSIZE specified in the table of contents does not match the corresponding value in the [IOCA-5-178]
Tile Size parameter.
## Tile Size


## Tile Set Color
The Tile Set Color parameter specifies the color used to paint significant pels of a bilevel tile. [IOCA-5-179]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'B7' Tile Set Color parameter M 1 UBIN LENGTH X'0A' – X'0C' Length of the parameters to follow | M [IOCA-5-180]|
| 2 | CODE | CSPACE | | X'01', X'04', X'06', X'08', X'40' Color space: X'01' RGB X'04' CMYK X'06' Highlight color space X'08' CIELAB X'40' Standard OCA color space M 3–5 X'000000' Reserved; should be zero M 6 UBIN SIZE1 X'01' – X'08', X'10' Number of bits/IDE for component 1; see color space definitions | M [IOCA-5-181]|
| 7 | UBIN | SIZE2 | | X'00' – X'08' Number of bits/IDE for component 2; see color space definitions | M [IOCA-5-182]|
| 8 | UBIN | SIZE3 | | X'00' – X'08' Number of bits/IDE for component 3; see color space definitions | M [IOCA-5-183]|
| 9 | UBIN | SIZE4 | | X'00' – X'08' Number of bits/IDE for component 4; see color space definitions M 10–n Color Color specification; see “Tile Set Color Semantics” for details M Notes: 1. The Tile Set Color Parameter serves two purposes. One purpose is to define the color of the significant pels in a bilevel tile. The other is to paint the whole tile with the specified color. In the second use, the tile does not contain any image data. 2. If the Tile Set Color Parameter is present, the significant image pels are painted with the specified color. Insignificant image pels are treated according to the rules for bilevel images. 3. If all pels are significant (that is, if the whole tile is to be painted), the compression algorithm must be set to Solid Fill Rectangle. In this case (solid fill), Image Data and Band Image Data cannot appear, or the exceptions EC-920F and EC-9C0F occur. 4. The Image Encoding parameter and IDE Structure parameter can appear for the tile, but must specify a bilevel image (the IDE size must be 1). The color space given in the IDE Structure parameter must be either YCbCr or YCrCb. Tile Set Color Semantics CSPACE Is a code that defines the color space and the encoding for the color specification. Value Description X'01' RGB color space. The color value is specified with three components. Components 1, 2, and 3 are unsigned binary numbers that specify the red, green, and blue intensity values, in that order. SIZE1, SIZE2, and SIZE3 are nonzero and define the number of bits used to specify each component. SIZE4 is reserved and should be set to | zero. [IOCA-5-184]|

## Tile Set Color


The intensity range for the R,G,B components is 0 to 1, which is mapped to the binary value range 0 to (2 SIZEN - 1), where N=1,2,3.
Architecture Note: The reference white point and the chromaticity coordinates for RGB are defined in SMPTE RP 145-1987, entitled Color Monitor Colorimetry, and in RP 37-1969, entitled Color Temperature for Color Television Studio Monitors, respectively. The reference white point is commonly known as Illuminant D 6500 or simply D65. The R,G,B components are assumed to be gamma-corrected (non-linear) with a gamma of 2.2.
X'04' CMYK color space. The color value is specified with four components. Components 1, 2, 3, and 4 are unsigned binary numbers that specify the cyan, magenta, yellow, and black intensity values, in that order. SIZE1, SIZE2, SIZE3, and SIZE4 are nonzero and define the number of bits used to specify each component. The intensity range for the C,M,Y ,K components is 0 to 1, which is mapped to the binary value range 0 to (2 SIZEN - 1), where N=1,2,3,4. This is a device-dependent color space.
X'06' Highlight color space. This color space defines a request for the presentation device to generate a highlight color. The color value is specified with one to three components.
Component 1 is a two-byte unsigned binary number that specifies the highlight color number. The first highlight color is assigned X'0001', the second highlight color is assigned X'0002', and so on. The value X'0000' specifies the presentation device default color. SIZE1 = X'10' and defines the number of bits used to specify component 1.
Component 2 is an optional one-byte unsigned binary number that specifies a percent coverage for the specified color. Percent coverage can be any value from 0% to 100% (X'00'–X'64'). The number of distinct values supported is presentation-device dependent. If the coverage is less than 100%, the remaining coverage is achieved with color of medium. SIZE2 = X'00' or X'08' and defines the number of bits used to specify component 2. A value of X'00' indicates that component 2 is not specified in the color value, in which case the architected default for percent coverage is 100%. A value of X'08' indicates that component 2 is specified in the color value.
Component 3 is an optional one-byte unsigned binary number that specifies a percent shading, which is a percentage of black that is to be added to the specified color.
Percent shading can be any value from 0% to 100% (X'00'–X'64'). The number of distinct values supported is presentation-device dependent. If percent coverage and percent shading are specified, the effective range for percent shading is 0% to (100- coverage)%. If the sum of percent coverage plus percent shading is less than 100%, the remaining coverage is achieved with color of medium. SIZE3 = X'00' or X'08' and defines the number of bits used to specify component 3. A value of X'00' indicates that component 3 is not specified in the color value, in which case the architected default for percent shading is 0%. A value of X'08' indicates that component 3 is specified in
the color value.
Implementation Note: The percent shading parameter is currently not supported in AFP environments.
SIZE4 is reserved and should be set to zero.
This is a device-dependent color space.
Architecture Notes:
1. The color that is rendered when a highlight color is specified is device dependent. [IOCA-5-185]
For presentation devices that support colors other than black, highlight color values in the range X'0001' to X'FFFF' may be mapped to any color. For bilevel devices, the color may be simulated with a graphic pattern. [IOCA-5-186]
## Tile Set Color


2. If the specified highlight color is “presentation device default”, devices whose default color is black use the percent coverage parameter, which is specified in component 2, to render a percent shading. [IOCA-5-187]
3. On printing devices, the color of medium is normally white, in which case a coverage of n% results in adding (100-n)% white to the specified color, or tinting the color with (100-n)% white. Display devices may assume the color of medium to always be white and use this algorithm to render the specified coverage. [IOCA-5-188]
4. The highlight color space can also specify indexed colors when used in conjunction with a Color Mapping T able (CMT) or an Indexed (IX) Color Management Resource (CMR). In that case, component 1 specifies a two-byte value that is the index into the CMT or the IX CMR and components 2 and 3 are ignored. Note that when both a CMT and Indexed CMRs are used, the CMT is always accessed first. T o preserve compatibility with existing highlight color devices, indexed color values X'0000' to X'00FF' are reserved for existing highlight color applications and devices. That is, indexed color values in the range [IOCA-5-189]
X'0000' to X'00FF', assuming they are not mapped to a different color space in a CMT , are mapped directly to highlight colors. Indexed color values in the range X'0100' to X'FFFF', assuming they are not mapped to a different color space in a CMT , are used to access Indexed CMRs. For a description of the Color Mapping T able in MO:DCA environments, see the Mixed Object Document Content Architecture (MO:DCA) Reference.
X'08' CIELAB color space. The color value is specified with three components.
Components 1, 2, and 3 are binary numbers that specify the L, a, b values, in that order, where L is the luminance and a and b are the chrominance differences.
Component 1 specifies the L value as an unsigned binary number; components 2 and 3 specify the a and b values as signed binary numbers. SIZE1, SIZE2, and SIZE3 are nonzero and define the number of bits used to specify each component. SIZE4 is reserved and should be set to zero. The range for the L component is 0 to 100, which is mapped to the binary value range 0 to (2 SIZE1 - 1). The range for the a and b components is -127 to +127, which is mapped to the binary range -(2 SIZEN-1 - 1) to +(2SIZEN-1 - 1), where N=2,3.
For color fidelity, 8-bit encoding should be used for each component, that is, SIZE1, SIZE2, and SIZE3 are set to X'08'. When the recommended 8-bit encoding is used for the a and b components, the range is extended to include -128, which is mapped to the value X'80'. If the encoding is less than 8 bits, treatment of the most negative binary endpoint for the a and b components is device dependent, and tends to be insignificant because of the quantization error.
Architecture Note: The reference white point for CIELAB is known as D50 and is defined in CIE publication 15-2 entitled Colorimetry.
X'40' Standard OCA color space. The color value is specified with one component.
Component 1 is an unsigned binary number that specifies a named color using a two- byte value from the Standard OCA Color Value T able. For a complete description of the Standard OCA Color Value T able, see the Mixed Object Document Content Architecture (MO:DCA) Reference. SIZE1 = X'10' and defines the number of bits used to specify component 1. SIZE2, SIZE3, and SIZE4 are reserved and should be set to zero. This is a device-dependent color space.
All others Reserved SIZE1 Defines the number of bits used to specify the first color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary. For example, if SIZE1 = X'06', the first color component has two padding bits. [IOCA-5-190]
## Tile Set Color


SIZE2 Defines the number of bits used to specify the second color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary.
SIZE3 Defines the number of bits used to specify the third color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary.
SIZE4 Defines the number of bits used to specify the fourth color component. The color component is right-aligned and padded with zeros on the left to the nearest byte boundary.
Color Specifies the color value in the defined format and encoding. Note that the number of bytes specified for this parameter depends on the color space. For example, when using 8 bits per component, an RGB color value is specified with 3 bytes, while a CMYK color value is specified with 4 bytes. If extra bytes are specified, they are ignored as long as the self-defining field length is valid.
T o illustrate the syntax for the color value specified in the Color field, the following table shows examples of various ways that a light-green color can be specified. Note that the light-green color value is approximated in each of the color spaces.
CSPACE SIZE1 SIZE2 SIZE3 SIZE4 Color RGB X'08' X'08' X'08' N/A X'00B761' RGB X'05' X'05' X'05' N/A X'00160B' CMYK X'08' X'08' X'08' X'08' X'FF489E00' CMYK X'01' X'02' X'04' X'08' X'01010900' Highlight (see note) X'10' X'08' X'00' N/A X'000264' CIELAB X'08' X'08' X'08' N/A X'A8CC21' Standard OCA X'10' N/A N/A N/A X'0004'
Note: This example assumes that the light-green color is loaded in the printer as highlight color X'0002'.
Architecture Note: For a description of color spaces and their relationships, see R. Hunt, The Reproduction of Colour in Photography, Printing and Television, Fifth Edition, Fountain Press, 1995. [IOCA-5-191]
## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-B70F Invalid sequence Condition: The Tile Set Color parameter appears out of sequence, or more than once within a single tile.
EC-B710 Invalid Tile Set Color parameter Condition: At least one of the following values is not valid:
• CSPACE [IOCA-5-192]
• SIZE
• Color
EC-B711 Inconsistent Tile Set Color parameter Condition: The IDESZ field in the IDE Size parameter has a value other than 1, or the color space specified in the IDE Structure parameter is not YCbCr or YCrCb. [IOCA-5-193]
## Tile Set Color


## Include Tile
The Include Tile parameter defines the tile as a referencing tile. The tile does not contain any image data, except possibly a Transparency Mask, and is instead read from the referenced resource. [IOCA-5-194]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FEB8' Include Tile parameter M 2–3 UBIN LENGTH X'0004' Length of the parameters to follow M 4–7 CODE TIRID X'00000000' – X'FFFFFFFF' Tile Resource local identifier M Notes: 1. If a tile contains the Include Tile parameter, it must contain a Tile Position parameter and can also contain a transparency mask. Any other parameters cause one of the Invalid Sequence (EC-xx0F) exceptions to be raised. 2. The Tile Position parameter in the included tile is ignored. The Tile Position parameter specified in the referencing tile is used instead. 3. If a referencing tile contains a transparency mask and the included tile also contains a transparency mask, the two masks are combined by using the logical AND operation. That is, a pixel is foreground if it is defined as foreground in both masks. 4. Tile resources do not contain any references to the Image Presentation Space. Each included tile is interpreted according to the current Image Presentation Space. 5. Except for the Tile Position and transparency mask, the included tile is treated exactly as if it was specified entirely locally. All defaulting and override rules for tile data apply. 6. The included tile must not contain another Include Tile parameter (that is, no nested references are allowed). There are no other constraints on the tile content. 7. Any other errors, such as the tile not being contained in the Image Presentation Space, are treated by raising the same exceptions as if the tile were specified | locally. [IOCA-5-195]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-B80F Invalid sequence Condition: An Include Tile parameter has appeared out of sequence or more than once.
EC-B811 Inconsistent Include Tile parameter Condition: The included tile resource contains an Include Tile parameter. [IOCA-5-196]
## Include Tile


## Tile TOC
The Tile T able of Contents (TOC) parameter defines the image as a tiled image. Optionally, it also defines the size and position of each tile. [IOCA-5-197]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FEBB' Tile TOC parameter M 2–3 UBIN LENGTH X'0002' – X'7FFF' Length of the parameters to follow; this value must be a multiple of 26, plus 2 M 4–5 X'0000' Reserved; should be zero M Zero or more repeating groups, with tile table of contents. If any TOC entries are present, then an entry for each tile must be present. The groups have the following format: 0–3 UBIN XOFFSET X'00000000' – X'7FFFFFFF' Horizontal offset of the tile origin, relative to the image origin M 4–7 UBIN YOFFSET X'00000000' – X'7FFFFFFF' Vertical offset of the tile origin, relative to the image origin M 8–11 UBIN THSIZE X'00000000' – X'7FFFFFFF' Horizontal size in image points, excluding any padding bits in each scan line M 12–15 UBIN TVSIZE X'00000000' – X'7FFFFFFF' Vertical size in image points, excluding any padding scan lines | M [IOCA-5-198]|
| 16 | UBIN | RELRES | | X'01' – X'02' Relative resolution of the tile M 17 CODE COMPR Compression algorithm; see Image Encoding parameter M 18–25 UBIN DATAPOS Any Offset, in bytes, from the start of the Begin Segment parameter of the current image, to the start of the Begin Tile parameter starting the tile M Notes: 1. Tiles in the table of contents must be specified in top-down, left-to-right order. If the table of contents is specified, the tiles themselves can be specified in any order (that is, the order restriction described for the Tile Position parameter is lifted). 2. The Tile TOC parameter must appear immediately after the Begin Image Content parameter; otherwise exception EC-BB0F occurs. If a Begin Tile parameter is encountered without a Tile TOC Parameter having been specified, exception EC-8C0F occurs. 3. If the image contains the Tile TOC parameter, no Image Data or Band Image Data may appear outside of the tiles (Begin Tile/End Tile pairs). Otherwise, exception EC-9201 (Image Data) or EC-9C01 (Band Image Data) occurs. 4. The presence of the Tile TOC parameter does not require that any tiles be actually specified. An empty image (no tiles present) is valid and all the image points are treated as background. 5. In terms of the DATAPOS, the first byte of the Begin Segment parameter has the offset zero. 6. If the Tile TOC parameter contains the table of contents, the values in the table of contents entry for each tile must match the values specified in the Tile Position parameter and Tile Size parameter for that tile. Otherwise, exception EC-B511 or EC-B611, respectively, occurs when inconsistent values are encountered in the Tile Position parameter and the Tile Size | parameter. [IOCA-5-199]|

## Tile TOC


## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-BB0F Invalid sequence Condition: A Tile TOC parameter appeared somewhere other than immediately after the Begin Image Content parameter or appeared more than once.
EC-BB10 Invalid Tile TOC values Condition: One or more values specified in the Tile TOC Parameter is outside of the valid range.
EC-BB11 Inconsistent Tile TOC parameter Condition: The parameter contains the tile table of contents and one or more of the following conditions is true:
• Not all tiles are listed in the table of contents, even though the table of contents contains at least one tile. [IOCA-5-200]
• The table of contents lists a non-existent tile. [IOCA-5-201]
• Invalid tile order: two or more tiles in the table of contents have identical sort keys, or the sort keys are out of sequence. [IOCA-5-202]
Note: The primary sort key is YOFFSET . The secondary sort key is XOFFSET .
• Invalid DATAPOS: the specified offset for one or more tiles does not point to a position where a Begin Tile parameter [IOCA-5-203]
starts.
## Tile TOC


## Transparency Masks
Transparency masks are bilevel images that are used to turn some image points into background.
Function Sets 14, 45, and 48 are currently the only function sets that include transparency masks. For more information on function sets, see “Function Sets”. [IOCA-5-204]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-205]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-206]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-207]
## Image Data Elements
## End Transparency Mask
## Transparency Masks


The transparency mask is a restricted bilevel image in the sense that it must have the same size in pels as the underlying image or tile. If the transparency mask is specified within a tile, the mask has the same resolution as the presentation space; that is, the relative resolution specified in RELRES does not apply. Transparency mask dimensions are carried explicitly using the Image Size parameter. These dimensions must match dimensions obtained by multiplying the tile dimensions by RELRES; otherwise exception EC-9411 occurs.
The transparency mask, if present, must immediately precede the first Image Data or Band Image Data.
Images that are not tiled can have at most one transparency mask. In tiled images, the transparency masks must be contained in tiles and each tile can contain at most one transparency mask. Note that tiled images can thus contain multiple transparency masks, each contained in and applying to a different tile.
If the transparency mask is specified in a tile that contains the Include Tile parameter, it must be specified after both the Tile Position and Include Tile parameters.
Tiles using the Include Tile parameter to invoke tile resources can have two transparency masks, one in the calling tile and one in the resource tile itself. The two transparency masks are combined using the logical AND operation; that is, an image point is in the foreground if it is in foreground in both masks. In other words, the caller can declare some of the resource image foreground points as background, but not the reverse.
The transparency mask has a point for each underlying image or presentation space point. If the transparency mask has been specified, the receiver should apply it on a point by point basis. If, at an image point, the mask contains B'0', the point is treated as background. Otherwise, if the mask contains B'1', the image point is treated according to the rules of the current color space, as if no transparency mask has been specified.
Table 5. Transparency Mask Structure [IOCA-5-208]

| Code | Name |
| :--- | :--- |
| X'8E' | Begin Transparency Mask parameter [IOCA-5-209]|
| X'94' | Image Size parameter [IOCA-5-210]|
| [ X'95' ] | [ Image Encoding parameter ] [IOCA-5-211]|
| X'FE92' | Image Data (S) [IOCA-5-212]|
| X'8F' | End Transparency Mask parameter [IOCA-5-213]|
• Begin Transparency Mask [IOCA-5-214]
• Image Size [IOCA-5-215]
• Image Encoding [IOCA-5-216]
• Image Data [IOCA-5-217]
• End Transparency Mask [IOCA-5-218]
Notes:
1. All recording algorithms and compression algorithms allowed for bilevel images in the IOCA Function Set specified for the image can be used. If the datastream does not specify the function set, any architecturally valid Image Encoding parameter values can be used, except Solid Fill Rectangle. The Solid Fill Rectangle algorithm is not needed, since omitting the transparency mask achieves the same effect as setting all the transparency mask image points to 1. Completely removing the image achieves the same effect as setting all transparency mask image points to 0. [IOCA-5-219]
2. If the Image Encoding parameter is missing, the default encoding (no compression and RIDIC) applies. [IOCA-5-220]
## Transparency Masks


## Begin Transparency Mask
The Begin Transparency Mask defines the beginning of the transparency mask. [IOCA-5-221]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'8E' Begin Transparency Mask parameter M 1 UBIN LENGTH X'00' Length of the parameters to follow | M [IOCA-5-222]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-8E0F Invalid sequence Condition: A Begin Transparency Mask has appeared out of sequence or more than once within a tile or a non-tiled image. [IOCA-5-223]
## Begin Transparency Mask


## End Transparency Mask
The End Transparency Mask defines the end of a transparency mask. [IOCA-5-224]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | ID | | X'8F' End Transparency Mask parameter M 1 UBIN LENGTH X'00' Length of the parameters to follow | M [IOCA-5-225]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-8F0F Invalid sequence Condition: An End Transparency Mask is missing after a Begin Transparency Mask has been encountered, or it appeared out of sequence. [IOCA-5-226]
## End Transparency Mask


## Image Data Elements
This section describes the parameters used to carry the Image Data Elements. [IOCA-5-227]
## Begin Segment
## Begin Image Content
## Begin Image Content
## Begin Tile
## Begin Transparency Mask
## End Image Content
## Image Data Elements
Image Size Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter External Algorithm Specification Parameter Image Subsampling Parameter
Tile TOC Parameter Image Encoding Parameter IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Position Parameter Tile Size Parameter Image Encoding Parameter
IDE Size Parameter Band Image Parameter IDE Structure Parameter Tile Set Color Parameter Include Tile Parameter Image Size Parameter Image Encoding Parameter [IOCA-5-228]
## Image Data Elements
## End Transparency Mask
## Image Data Elements
## End Tile
## End Image Content
## End Segment
nColor Names Parameter nColor Names Parameter nColor Names Parameter [IOCA-5-229]
## Begin Transparency Mask
Image Size Parameter Image Encoding Parameter [IOCA-5-230]
## Image Data Elements
## End Transparency Mask
## Image Data Elements


## Image Data
The Image Data is an element of the Image Content, and is a set of IDEs. Multiple Image Data self-defining fields of the same type can be contained in a single untiled Image Content, a single tile, or a single transparency mask. [IOCA-5-231]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FE92' Image Data parameter M 2–3 UBIN LENGTH X'0001' – X'FFFF' Length of the image data to follow M 4–n DATA Any Image Data Elements. The data in this self-defining field is recorded, compressed, and ordered as specified by the Image Encoding parameter. For some function sets, the data can also be subsampled as described by the Image Subsampling parameter. | M [IOCA-5-232]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-9201 Invalid existence of Image Data Condition: Image Data should not be present, as in the case when the image data is in bands.
EC-920F Invalid sequence Condition: Image Data is missing, or it appeared out of sequence. [IOCA-5-233]
## Image Data


## Band Image Data
The Band Image Data is an element of the Image Content. It is a set of IDEs typically having similar attributes to each other.
Band Image Data must appear within an Image Content or a tile for each band defined by the Band Image parameter. The bands must appear in the sequential order of their band numbers. The Band Image parameter must exist in the Image Content if this parameter is used. See “Band Image” for more detail.
If the data for a particular band exceeds the length of a single Band Image Data, the remaining data is contained in one or more Band Image Data parameters having the same band number, and following in sequence. [IOCA-5-234]
### Syntax

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | ID | | X'FE9C' Band Image Data parameter M 2–3 UBIN LENGTH X'0003' – X'FFFF' Length of the parameters to follow | M [IOCA-5-235]|
| 4 | CODE | BANDNUM | | X'01' – X'FD' Band number M 5–6 X'0000' Reserved; should be zero M 7–n DATA Any Image Data Elements for the band. The data in this self- defining field is recorded, compressed, and ordered as specified by the Image Encoding parameter. For some function sets, the data can also be subsampled as described by the Image Subsampling parameter. ONotes: 1. If the Band Image Data contains no data (the length is X'0003') for a certain band, all the uncompressed image data elements in the band are zero. For such a band, only a single Band Image Data parameter can appear; otherwise exception EC-9C0F occurs. 2. If the color space of the image is CMYK and the bands 1, 2, and 3 (cyan, magenta, and yellow) contain no data, the receivers should color-manage the image as | monochrome. [IOCA-5-236]|

## Exception Conditions
The following exception conditions cause the standard action to be taken:
EC-0003 Invalid length Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: The BANDNUM value is not in the valid range.
EC-9C01 Invalid existence of Band Image Data Condition: Band Image Data should not be present, as in the case when the Image Data is not in bands.
EC-9C0F Invalid sequence Condition: Band Image Data is missing, or it appeared out of sequence. [IOCA-5-237]
## Band Image Data


EC-9C17 Invalid number/sequence of Band Image Data Condition: The band numbers of a band image do not appear for the number of bands defined in the Band Image parameter, or do not appear in succeeding order. [IOCA-5-238]
## Band Image Data




