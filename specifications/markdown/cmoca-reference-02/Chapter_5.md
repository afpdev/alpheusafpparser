# Chapter 5. CMR Data Architecture

The CMR Data field carries all the actual color resource data. The resource data is carried in a tagged format. CMR is big endian. The tags are loosely based on the TIFF tag syntax, but with significant changes and additions. The tags are carried first, optionally followed by the tag data. The last tag is always the End Data tag. [CMOCA-5-001]

## Tag Syntax

Each tag consists of 12 bytes in the following format: [CMOCA-5-002]

### **Table 24. CMR Data Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | CODE | TagID | X'0000'–X'FFFF' | Unique identifier for the tag [CMOCA-5-003]|
| 2 | | Reserved | X'00' | Should be set to zero [CMOCA-5-004]|
| 3 | CODE | Field Type | X'01' | 1-byte UBIN [CMOCA-5-005]|
| | | | X'02' | 2-byte UBIN [CMOCA-5-006]|
| | | | X'04' | 4-byte UBIN [CMOCA-5-007]|
| | | | X'05' | BYTE (8 bits) [CMOCA-5-008]|
| | | | X'06' | ASCII [CMOCA-5-009]|
| | | | X'07' | UTF16 (UTF-16BE) [CMOCA-5-010]|
| | | | X'08' | CODE (8 bit architected constant) [CMOCA-5-011]|
| | | | X'09' | BITS [CMOCA-5-012]|
| 4–7 | UBIN | Count | X'00000000' – X'FFFFFFFF' | Number of values of the indicated Field Type (may be zero) [CMOCA-5-013]|
| 8–11 | | ValueOffset | Any | Data, left-aligned, if it fits into 4 bytes. Otherwise, offset to data is an offset from byte 164 of the CMR (i.e., from the start of CMRData). [CMOCA-5-014]|

Field Type X'05' (BYTE) is used for the tags whose data has a defined structure, such as OID, Date and Time Stamp, ICC Profile Data, and Link LUT tags. Field Type X'06' (ASCII) is defined in the MO:DCA architecture with encoding scheme ID X'2100' – PC-Data, single byte. UBIN is defined as unsigned binary. [CMOCA-5-015]

Tags X'F000'–X'FFFE' are private tags. Organizations may use a private tag in this range for their exclusive use without disclosing the tag contents. The architecture requires that such tag be non-essential, in the sense that any receivers not supporting the tag will not fail on parsing or using the resource. [CMOCA-5-016]

X'EF00'–X'EFFF' are reserved for error handling for the CMR header. [CMOCA-5-017]

The tags in a CMR must be specified in increasing order by their TagIDs. If they are out of order, the Exception EC-xxxx0F exists. Unless otherwise specified within the individual tag description, each TagID may appear at most once and Exception EC-xxxx0F exists if it is specified more than once. Multiple tags with the same ID may be accepted if the particular tag description explicitly states that it may repeat. The description in the tag must explain how the multiple tags are used and which one wins in cases of conflict. Tag values in the CMR tags are listed in the tag registry, that can be found in Appendix A, “Tag Registry”. Private tags are ignored. Any TagID not supported by the device causes the Exception EC-xxxx04. The last tag must be the End Data tag (TagID of X'FFFF'), or exception EC-FFFF0E exists. [CMOCA-5-018]

There is no restriction on where the actual data fields are located, as long as they are within the CMRData field scope. Note that all the offsets are from the beginning of the CMRData field, so that the location of the CMR header can be changed without any need to update the ValueOffset values. The offsets do not have to be increasing as the TagIDs increase, nor do they have to follow any other rule. There is no requirement that all the data in the scope be used, that is, it is permissible to have data not referenced by any tag. [CMOCA-5-019]

The number of bytes of data for a tag is the value of Count multiplied by the size of each data item as indicated by Field Type. For example, a Count of 1 indicates two bytes if Field Type is X'02' (2-byte UBIN) or X'07' (UTF16). [CMOCA-5-020]

Each type of CMR has a list of Mandatory Tags and a list of optional tags. The receivers should ignore any unknown tags. If an optional tag is not present, the default value (if one exists) should be used. [CMOCA-5-021]

## Exception Syntax

On encountering an error, an exception is raised. Each exception is defined by a three byte value. The first two bytes are the relevant TagID value (X'0000'–X'FFFF'), while the third byte is the exception code. The exception codes are defined as follows: [CMOCA-5-022]

| Code | Meaning |
| :--- | :--- |
| X'04' | Unsupported TagID Value in a CMR tag [CMOCA-5-023]|
| X'05' | Invalid Count Value [CMOCA-5-024]|
| X'06' | Invalid Field Type [CMOCA-5-025]|
| X'0E' | Missing Required Tag [CMOCA-5-026]|
| X'0F' | Invalid Sequence [CMOCA-5-027]|
| X'10' | Invalid or unsupported field value or an offset that causes the tag data to start or end after the end of the CMR (as defined by the CMR length) [CMOCA-5-028]|
| X'11' | Inconsistent Tag Contents [CMOCA-5-029]|
| X'12' | Incorrect order of repeating groups [CMOCA-5-030]|
| X'13' | Duplicate value [CMOCA-5-031]|

## Coordinate System

The CMR coordinate system is a two dimensional Cartesian coordinate system. The horizontal axis is labeled x, and the vertical axis is labeled y. Positive x is to the right of the origin, and positive y is above the origin. The measurement unit is pixel. [CMOCA-5-032]

**Figure 7. Cartesian Coordinate System** [CMOCA-5-033]

## Tag Semantics

This section defines the CMR tags. [CMOCA-5-034]

### Comment

*   **TagID:** X'0004' [CMOCA-5-035]
*   **Field Type:** X'06' (ASCII), X'07' (UTF16) [CMOCA-5-036]
*   **Count:** Number of characters [CMOCA-5-037]

This tag defines arbitrary comment text, ignored by receivers. There is no default. [CMOCA-5-038]

**Exception Conditions:**

*   **EC-000406 Invalid Field Type:** The specified Field Type is invalid for the tag. [CMOCA-5-039]
*   **EC-00040F Invalid Sequence:** The tag has been encountered out of sequence or more than once. [CMOCA-5-040]
*   **EC-000410 Invalid Value:** The offset caused some portion of the tag data to be outside of the CMRdata. [CMOCA-5-041]

### Date and Time Stamp

*   **TagID:** X'0008' [CMOCA-5-042]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-043]
*   **Count:** 10 [CMOCA-5-044]

This tag contains the date and time of the creation of the CMR. It is defined consistently with the MO:DCA definition of the Universal Date and Time Stamp Triplet X'72' that is specified in accordance with the format defined in ISO 8601:1988 (E). The tag is informational. The date and time values are not checked for validity. [CMOCA-5-045]

#### **Table 25. Date and Time Stamp Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | YearAD | 0–65,535 | Year AD using Gregorian calendar [CMOCA-5-046]|
| 2 | 1-byte UBIN | Month | 1–12 | Month of the year [CMOCA-5-047]|
| 3 | 1-byte UBIN | Day | 1–31 | Day of the month [CMOCA-5-048]|
| 4 | 1-byte UBIN | Hour | 0–23 | Hour of the day in 24-hour format [CMOCA-5-049]|
| 5 | 1-byte UBIN | Minute | 0–59 | Minute of the hour [CMOCA-5-050]|
| 6 | 1-byte UBIN | Second | 0–59 | Second of the minute [CMOCA-5-051]|
| 7 | CODE | TimeZone | X'00', X'01', X'02' | Relationship of time to UTC (Coordinated, Ahead, Behind) [CMOCA-5-052]|
| 8 | 1-byte UBIN | UTCDiffH | 0–23 | Hours ahead of or behind UTC [CMOCA-5-053]|
| 9 | 1-byte UBIN | UTCDiffM | 0–59 | Minutes ahead of or behind UTC [CMOCA-5-054]|

*   **YearAD:** Specifies the year AD using the Gregorian calendar. For example, the year 1999 is specified as X'07CF'. Represents the YYYY component. [CMOCA-5-055]
*   **Month:** Specifies the month of the year. January is X'01'. Represents the MM component. [CMOCA-5-056]
*   **Day:** Specifies the day of the month. The first day is X'01'. Represents the DD component. [CMOCA-5-057]
*   **Hour:** Specifies the hour (0-23). Represents the hh component. [CMOCA-5-058]
*   **Minute:** Specifies the minute (0-59). Represents the mm component. [CMOCA-5-059]
*   **Second:** Specifies the second (0-59). Represents the ss component. [CMOCA-5-060]
*   **TimeZone:** Defines the relation to UTC. [CMOCA-5-061]
    *   **X'00':** Time is specified in UTC. UTCDiffH/M should be X'00'. (Suffix Z) [CMOCA-5-062]
    *   **X'01':** Ahead of UTC. UTCDiffH/M specify the difference. (Suffix +hhmm) [CMOCA-5-063]
    *   **X'02':** Behind UTC. UTCDiffH/M specify the difference. (Suffix -hhmm) [CMOCA-5-064]

**Exception Conditions:**

*   **EC-000805 Invalid Count Value:** The specified Count field value is invalid for the tag. [CMOCA-5-065]
*   **EC-000806 Invalid Field Type:** The specified Field Type is invalid for the tag. [CMOCA-5-066]
*   **EC-00080F Invalid Sequence:** The tag has been encountered out of sequence or more than once. [CMOCA-5-067]
*   **EC-000810 Invalid Value:** The offset caused some portion of the tag data to be outside of the CMRdata. [CMOCA-5-068]

### Number of Components

*   **TagID:** X'0011' [CMOCA-5-069]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-070]
*   **Count:** 1 [CMOCA-5-071]

This tag defines the number of color components referenced by this resource. To comply with ICC, the number of components must be in the range of 1–15. [CMOCA-5-072]

#### **Table 26. Ordering Sequence of Color Spaces**

| Color Space | Component 1 | Component 2 | Component 3 | Component 4 |
| :--- | :--- | :--- | :--- | :--- |
| XYZ | X | Y | Z [CMOCA-5-073]| |
| Lab | L | a | b [CMOCA-5-074]| |
| Luv | L | u | v [CMOCA-5-075]| |
| Yxy | Y | X | y [CMOCA-5-076]| |
| YCbr | Y | Cb | Cr [CMOCA-5-077]| |
| RGB | R | G | B [CMOCA-5-078]| |
| GRAY | K [CMOCA-5-079]| | | |
| HSV | H | S | V [CMOCA-5-080]| |
| HLS | H | L | S [CMOCA-5-081]| |
| CMYK | C | M | Y | K [CMOCA-5-082]|
| CMY | C | M | Y [CMOCA-5-083]| |
| 2CLR | Component 1 | Component 2 [CMOCA-5-084]| | |
| 3CLR | Component 1 | Component 2 | Component 3 [CMOCA-5-085]| |
| 4CLR | Component 1 | Component 2 | Component 3 | Component 4 [CMOCA-5-086]|

The components are numbered according to the order in the ICC data tag. Additional color spaces can be added simply by defining the signature component assignments. Default is 1. [CMOCA-5-087]

**Exception Conditions:**

*   **EC-001105 Invalid Count Value** [CMOCA-5-088]
*   **EC-001106 Invalid Field Type** [CMOCA-5-089]
*   **EC-00110F Invalid Sequence** [CMOCA-5-090]
*   **EC-001110 Invalid Value:** number of components is zero or greater than 15. [CMOCA-5-091]

### Halftone Subset

*   **TagID:** X'1011' [CMOCA-5-092]
*   **Field Type:** X'08' (CODE) [CMOCA-5-093]
*   **Count:** 1 [CMOCA-5-094]

This tag denotes a subset of the Halftone CMR type. [CMOCA-5-095]

#### **Table 27. Halftone Subsets**

| Subset ID | Name |
| :--- | :--- |
| X'01' | Bilevel Point-Operation Halftone [CMOCA-5-096]|
| X'02' | Multilevel Point-Operation Halftone [CMOCA-5-097]|
| X'03' | Bilevel Error Diffusion Halftone [CMOCA-5-098]|
| X'04' | Multilevel Error Diffusion Halftone [CMOCA-5-099]|

**Exception Conditions:**

*   **EC-101110 Invalid Value:** specified subset value is none of X'01', X'02', X'03', or X'04'. [CMOCA-5-100]

### Array Width

*   **TagID:** X'1021' [CMOCA-5-101]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN) [CMOCA-5-102]
*   **Count:** Number of color components [CMOCA-5-103]

This tag defines the width of the array along the x-direction in pixels for each color component. [CMOCA-5-104]

### Array Height

*   **TagID:** X'1025' [CMOCA-5-105]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN) [CMOCA-5-106]
*   **Count:** Number of color components [CMOCA-5-107]

This tag defines the height of the array along the y-direction in pixels for each color component. [CMOCA-5-108]

### Max Image Value

*   **TagID:** X'1030' [CMOCA-5-109]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN), X'04' (4-byte UBIN) [CMOCA-5-110]
*   **Count:** Number of color components [CMOCA-5-111]

This tag defines the maximum input image value per component. [CMOCA-5-112]

### Number of Device Levels

*   **TagID:** X'1035' [CMOCA-5-113]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-114]
*   **Count:** Number of color components [CMOCA-5-115]

This tag defines the number of device levels per component for multilevel devices. Each specified Number of Device Levels must be greater than 2. [CMOCA-5-116]

### Offset Tiling

*   **TagID:** X'1040' [CMOCA-5-117]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN) [CMOCA-5-118]
*   **Count:** Number of color components [CMOCA-5-119]

This tag defines the amount of shift in pixels between the halftone tiles in two adjacent rows for each component. [CMOCA-5-120]

**Figure 8. Illustration of Offset Tiling with Offset Tiling=2** [CMOCA-5-121]

### Bilevel Point-Operation Screen Data

*   **TagID:** X'1045' [CMOCA-5-122]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN), X'04' (4-byte UBIN) [CMOCA-5-123]
*   **Count:** Sum of (Array Width × Array Height) over all color components [CMOCA-5-124]

This tag specifies the threshold array values for each screen. Arranged in row-major format. Component-wise structured. [CMOCA-5-125]

### Multilevel Point-Operation Screen Data

*   **TagID:** X'1050' [CMOCA-5-126]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-127]
*   **Count:** Sum of (Array Width × Array Height × (Max Image Value + 1)) over all color components [CMOCA-5-128]

This tag gives the device gray level for each pixel. Each screen is a 3-d table lookup. Dimensions are m × n × (Max Image Value + 1). [CMOCA-5-129]

### Error Diffusion Filter

*   **TagID:** X'1055' [CMOCA-5-130]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-131]
*   **Count:** Sum of (Array Width × Array Height) over all color components [CMOCA-5-132]

This tag specifies a set of values in the error diffusion filter. Arranged in a 2-dimensional array for each color plane. [CMOCA-5-133]

**Figure 9. Illustration of Error Distribution with Floyd-Steinberg Filter** [CMOCA-5-134]

### Location of Current Pixel

*   **TagID:** X'1060' [CMOCA-5-135]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-136]
*   **Count:** 2 × number of color components [CMOCA-5-137]

Specifies a pair of values (row, column) describing the location of the current pixel in an error diffusion filter. [CMOCA-5-138]

### Raster Direction

*   **TagID:** X'1065' [CMOCA-5-139]
*   **Field Type:** X'08' (CODE) [CMOCA-5-140]
*   **Count:** Number of color components [CMOCA-5-141]

#### **Table 28. Raster Direction Values**

| Value | Meaning |
| :--- | :--- |
| X'01' | Normal raster [CMOCA-5-142]|
| X'02' | Serpentine raster [CMOCA-5-143]|

**Figure 10. Illustration of Normal Raster and Serpentine Raster** [CMOCA-5-144]

### Boundary Condition

*   **TagID:** X'1070' [CMOCA-5-145]
*   **Field Type:** X'08' (CODE) [CMOCA-5-146]
*   **Count:** Number of color components [CMOCA-5-147]

#### **Table 29. Boundary Condition Values**

| Value | Meaning |
| :--- | :--- |
| X'01' | None [CMOCA-5-148]|
| X'02' | Zero boundary [CMOCA-5-149]|
| X'03' | Reflect [CMOCA-5-150]|
| X'04' | Periodic [CMOCA-5-151]|

### Threshold Value

*   **TagID:** X'1075' [CMOCA-5-152]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN), X'04' (4-byte UBIN) [CMOCA-5-153]
*   **Count:** Number of color components [CMOCA-5-154]

Specifies a single threshold value for bilevel error diffusion halftones. [CMOCA-5-155]

### Quantization Boundary Table

*   **TagID:** X'1080' [CMOCA-5-156]
*   **Field Type:** X'01' (1-byte UBIN), X'02' (2-byte UBIN), X'04' (4-byte UBIN) [CMOCA-5-157]
*   **Count:** Sum of (Number of Device Levels – 1) over all color components [CMOCA-5-158]

Specifies n one-dimensional arrays for multilevel error diffusion halftone. [CMOCA-5-159]

#### **Table 30. Illustration of Quantization Boundary Table**

| Index (i) | Array Entry (Threshold Value) |
| :--- | :--- |
| 1 | 60 [CMOCA-5-160]|
| 2 | 120 [CMOCA-5-161]|
| 3 | 200 [CMOCA-5-162]|

#### **Table 31. Implementation of Quantization Boundary Table**

| Initial Value | Output Device Level | Corrected value [CMOCA-5-163]|
| :--- | :--- | :--- |
| I ∈ [0, 60) | 0 | 0 [CMOCA-5-164]|
| I ∈ [60, 120) | 1 | 85 [CMOCA-5-165]|
| I ∈ [120, 200) | 2 | 170 [CMOCA-5-166]|
| I ∈ [200, 255] | 3 | 255 [CMOCA-5-167]|

### Tone Transfer Curve Subset

*   **TagID:** X'2004' [CMOCA-5-168]
*   **Field Type:** X'08' (CODE) [CMOCA-5-169]
*   **Count:** 1 [CMOCA-5-170]

#### **Table 32. Tone Transfer Curve Subsets**

| Subset ID | Name |
| :--- | :--- |
| X'01' | ToneTransferCurve Array [CMOCA-5-171]|
| X'02' | ToneTransferCurve Identity [CMOCA-5-172]|

### Tone Transfer Curve Length

*   **TagID:** X'2011' [CMOCA-5-173]
*   **Field Type:** X'08' (CODE) [CMOCA-5-174]
*   **Count:** Number of color components [CMOCA-5-175]

#### **Table 33. Tone Transfer Curve Length Values**

| Value | Meaning |
| :--- | :--- |
| X'01' | 256 1-byte entries [CMOCA-5-176]|
| X'02' | 65,536 2-byte entries [CMOCA-5-177]|

### Tone Transfer Curve Data

*   **TagID:** X'2015' [CMOCA-5-178]
*   **Field Type:** X'05' (byte) [CMOCA-5-179]
*   **Count:** Total length of the data [CMOCA-5-180]

### Inverse Tone Transfer Curve Data

*   **TagID:** X'2020' [CMOCA-5-181]
*   **Field Type:** X'05' (Byte) [CMOCA-5-182]
*   **Count:** Total length of the data [CMOCA-5-183]

### ICC Profile Subset

*   **TagID:** X'3011' [CMOCA-5-184]
*   **Field Type:** X'08' (CODE) [CMOCA-5-185]
*   **Count:** 1 [CMOCA-5-186]

#### **Table 34. ICC Profile Subsets**

| Subset ID | Name |
| :--- | :--- |
| X'01' | Monochrome input profile [CMOCA-5-187]|
| X'02' | Monochrome display profile [CMOCA-5-188]|
| X'03' | Monochrome output profile [CMOCA-5-189]|
| X'04' | Three-component matrix-based input profile [CMOCA-5-190]|
| X'05' | Three-component matrix-based display profile [CMOCA-5-191]|
| X'06' | N-component LUT-based input profile [CMOCA-5-192]|
| X'07' | N-component LUT-based display profile [CMOCA-5-193]|
| X'08' | N-component LUT-based output profiles [CMOCA-5-194]|
| X'09' | ColorSpace conversion profile [CMOCA-5-195]|
| X'0A' | Retired item 3 (Abstract profile) [CMOCA-5-196]|

### ICC Profile Data

*   **TagID:** X'3015' [CMOCA-5-197]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-198]
*   **Count:** The number of bytes in the profile [CMOCA-5-199]

#### **Table 35. ICC Header Fields**

| Byte Offset | Content |
| :--- | :--- |
| 0–3 | Profile size [CMOCA-5-200]|
| 4–7 | CMM Type signature [CMOCA-5-201]|
| 8–11 | Profile version number [CMOCA-5-202]|
| 12–15 | Profile/Device Class signature [CMOCA-5-203]|
| 16–19 | Color Space of Data [CMOCA-5-204]|
| 20–23 | Profile Connection Space (PCS) [CMOCA-5-205]|
| 24–35 | Date and time this profile was first created [CMOCA-5-206]|
| 36–39 | acsp (61637370h) profile file signature [CMOCA-5-207]|
| 40–43 | Primary Platform signature [CMOCA-5-208]|
| 44–47 | Flags to indicate various options for the CMM [CMOCA-5-209]|
| 48–51 | Device manufacturer [CMOCA-5-210]|
| 52–55 | Device model [CMOCA-5-211]|
| 56–63 | Device attributes (e.g., media type) [CMOCA-5-212]|
| 64–67 | Rendering Intent [CMOCA-5-213]|
| 68–79 | XYZ values of the illuminant (must be D50) [CMOCA-5-214]|
| 80–83 | Profile Creator signature [CMOCA-5-215]|
| 84–99 | Profile ID [CMOCA-5-216]|
| 100–127 | 28 bytes reserved (set to zeros) [CMOCA-5-217]|

### ICC Profile Filename

*   **TagID:** X'3025' [CMOCA-5-218]
*   **Field Type:** X'06' (ASCII), X'07' (UTF16) [CMOCA-5-219]
*   **Count:** Number of characters [CMOCA-5-220]

### Link Color Conversion Subset

*   **TagID:** X'4011' [CMOCA-5-221]
*   **Field Type:** X'08' (CODE) [CMOCA-5-222]
*   **Count:** 1 [CMOCA-5-223]

#### **Table 36. Link Color Conversion Subsets**

| Subset ID | Name |
| :--- | :--- |
| X'01' | LinkColorConversion LUT [CMOCA-5-224]|
| X'02' | LinkColorConversion Identity [CMOCA-5-225]|
| X'03' | ICC DeviceLink [CMOCA-5-226]|

### Link Audit CMR OID

*   **TagID:** X'4015' [CMOCA-5-227]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-228]
*   **Count:** Number of bytes in the OID [CMOCA-5-229]

### Link Instruction CMR OID

*   **TagID:** X'4020' [CMOCA-5-230]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-231]
*   **Count:** Number of bytes in the OID [CMOCA-5-232]

### Link Audit CMR Name

*   **TagID:** X'4025' [CMOCA-5-233]
*   **Field Type:** X'07' (UTF16) [CMOCA-5-234]
*   **Count:** Number of characters [CMOCA-5-235]

### Link Instruction CMR Name

*   **TagID:** X'4030' [CMOCA-5-236]
*   **Field Type:** X'07' (UTF16) [CMOCA-5-237]
*   **Count:** Number of characters [CMOCA-5-238]

### Default Rendering Intent

*   **TagID:** X'4035' [CMOCA-5-239]
*   **Field Type:** X'08' (CODE) [CMOCA-5-240]
*   **Count:** 1 [CMOCA-5-241]

#### **Table 37. ICC Rendering Intents**

| Rendering Intent | Value |
| :--- | :--- |
| Perceptual | X'00' [CMOCA-5-242]|
| Media-Relative Colorimetric | X'01' [CMOCA-5-243]|
| Saturation | X'02' [CMOCA-5-244]|
| ICC-Absolute Colorimetric | X'03' [CMOCA-5-245]|

### Link LUT Perceptual

*   **TagID:** X'4040' [CMOCA-5-246]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-247]
*   **Count:** The number of bytes in the LUT + 20 bytes of the header [CMOCA-5-248]

#### **Table 38. Link LUT Perceptual Tag Syntax**

| Bytes | Length | Type | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | 1 | 1-byte UBIN | 1–15 | Number of components of the input color space [CMOCA-5-249]|
| 1 | 1 | 1-byte UBIN | 1–15 | Number of components of the output color space [CMOCA-5-250]|
| 2–16 | 15 | 1-byte UBIN | 0–255 | Number of grid points in each component of input [CMOCA-5-251]|
| 17 | 1 | 1-byte UBIN | 1, 2 | Precision: 1=1-byte UBIN, 2=2-byte UBIN [CMOCA-5-252]|
| 18 | 1 | BITS | | Additional use flags: bit 0: Media-rel, bit 1: Saturation, bit 2: ICC-Abs [CMOCA-5-253]|
| 19 | 1 | | 0 | Reserved [CMOCA-5-254]|
| 20 to end | | | | LUT data [CMOCA-5-255]|

### Link LUT Media-Relative Colorimetric

*   **TagID:** X'4045' [CMOCA-5-256]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-257]

### Link LUT Saturation

*   **TagID:** X'4050' [CMOCA-5-258]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-259]

### Link LUT ICC-Absolute Colorimetric

*   **TagID:** X'4055' [CMOCA-5-260]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-261]

### Link CMRE Identifier

*   **TagID:** X'4090' [CMOCA-5-262]
*   **Field Type:** X'07' (UTF16) [CMOCA-5-263]
*   **Count:** Number of characters [CMOCA-5-264]

### Indexed Subset

*   **TagID:** X'5011' [CMOCA-5-265]
*   **Field Type:** X'08' (CODE) [CMOCA-5-266]
*   **Count:** 1 [CMOCA-5-267]

#### **Table 42. Indexed CMR Subset**

| Subset ID | Name |
| :--- | :--- |
| X'01' | Multi-output color spaces [CMOCA-5-268]|

### Number of Named Colorants

*   **TagID:** X'5015' [CMOCA-5-269]
*   **Field Type:** X'01' (1-byte UBIN) [CMOCA-5-270]
*   **Count:** 1 [CMOCA-5-271]

### Color Palette Gray

*   **TagID:** X'5020' [CMOCA-5-272]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-273]
*   **Count:** 9 × the number of color entries [CMOCA-5-274]

#### **Table 43. Color Palette Gray Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | IndexedColorValue | X'0100' – X'FFFF' | 2-byte indexed color value [CMOCA-5-275]|
| 2–7 | 2-byte UBIN | CIELABValue | X'0000' – X'FFFF' | L*, a*, b* components [CMOCA-5-276]|
| 8 | 1-byte UBIN | Component_1 | X'00'–X'FF' | Intensity of gray (X'00'=black, X'FF'=white) [CMOCA-5-277]|

### Color Palette CMYK

*   **TagID:** X'5025' [CMOCA-5-278]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-279]
*   **Count:** 12 × the number of color entries [CMOCA-5-280]

#### **Table 44. Color Palette CMYK Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | IndexedColorValue | X'0100' – X'FFFF' | 2-byte indexed color value [CMOCA-5-281]|
| 2–7 | 2-byte UBIN | CIELABValue | X'0000' – X'FFFF' | L*, a*, b* components [CMOCA-5-282]|
| 8 | 1-byte UBIN | Component_1 | X'00'–X'FF' | Cyan [CMOCA-5-283]|
| 9 | 1-byte UBIN | Component_2 | X'00'–X'FF' | Magenta [CMOCA-5-284]|
| 10 | 1-byte UBIN | Component_3 | X'00'–X'FF' | Yellow [CMOCA-5-285]|
| 11 | 1-byte UBIN | Component_4 | X'00'–X'FF' | Black [CMOCA-5-286]|

### Color Palette RGB

*   **TagID:** X'5030' [CMOCA-5-287]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-288]
*   **Count:** 11 × the number of color entries [CMOCA-5-289]

#### **Table 45. Color Palette RGB Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | IndexedColorValue | X'0100' – X'FFFF' | 2-byte indexed color value [CMOCA-5-290]|
| 2–7 | 2-byte UBIN | CIELABValue | X'0000' – X'FFFF' | L*, a*, b* components [CMOCA-5-291]|
| 8 | 1-byte UBIN | Component_1 | X'00'–X'FF' | Red [CMOCA-5-292]|
| 9 | 1-byte UBIN | Component_2 | X'00'–X'FF' | Green [CMOCA-5-293]|
| 10 | 1-byte UBIN | Component_3 | X'00'–X'FF' | Blue [CMOCA-5-294]|

### Color Palette CIELAB

*   **TagID:** X'5035' [CMOCA-5-295]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-296]
*   **Count:** 8 × the number of color entries [CMOCA-5-297]

#### **Table 46. Color Palette CIELAB Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | IndexedColorValue | X'0100' – X'FFFF' | 2-byte indexed color value [CMOCA-5-298]|
| 2–7 | 2-byte UBIN | CIELABValue | X'0000' – X'FFFF' | L*, a*, b* components [CMOCA-5-299]|

### Color Palette Named Colorants

*   **TagID:** X'5040' [CMOCA-5-300]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-301]
*   **Count:** (Number of Named Colorants + 8) × the number of color entries [CMOCA-5-302]

#### **Table 47. Color Palette Named Colorants Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0–1 | 2-byte UBIN | IndexedColorValue | X'0100' – X'FFFF' | 2-byte indexed color value [CMOCA-5-303]|
| 2–7 | 2-byte UBIN | CIELABValue | X'0000' – X'FFFF' | L*, a*, b* components [CMOCA-5-304]|
| 8 to 7+n | 1-byte UBIN | Component_i | X'00'–X'FF' | Intensity of i-th colorant [CMOCA-5-305]|

### Colorant Identification List

*   **TagID:** X'5045' [CMOCA-5-306]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-307]
*   **Count:** Sum of the length over the Number of Named Colorants [CMOCA-5-308]

#### **Table 48. Colorant Identification List Tag Syntax**

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0 | 1-byte UBIN | Length | X'03'–X'FB' | Length of this repeating group [CMOCA-5-309]|
| 1–end | UTF-16 | Colorant Name | | Colorant name in free format UTF-16BE [CMOCA-5-310]|

### End Data

*   **TagID:** X'FFFF' [CMOCA-5-311]
*   **Field Type:** X'05' (BYTE) [CMOCA-5-312]
*   **Count:** 0 [CMOCA-5-313]

Signifies the end of the tag list. [CMOCA-5-314]
