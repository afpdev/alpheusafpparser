# Chapter 6. Exception Conditions and Actions
This chapter outlines the exception conditions and exception actions for IOCA, and summarizes:
• Exception conditions causing the common standard action [IOCA-6-001]
• Exception conditions causing unique standard actions [IOCA-6-002]
An exception condition is either mandatory or optional. If a function is supported and a mandatory exception condition for the function is detected, the process must notify the controlling environment. If an optional exception condition for the function is detected, the process might or might not need to notify the controlling environment.
The table in “Mandatory or Optional Exception Conditions” summarizes for each IOCA exception condition whether it is mandatory or optional. Also shown in the table is whether the two primary IOCA controlling environments–MO:DCA and IPDS–would consider the exception condition to be mandatory or optional. [IOCA-6-003]
## Exception Conditions
Exception conditions include violations of the following:
• Syntax [IOCA-6-004]
• Parameter value [IOCA-6-005]
• Self-defining field sequence [IOCA-6-006]
Exceptions are represented in the following format:
eee-xxxx where:
eee identifies the exception group. The exception group can be one of the following:
EC Image Segment exception xxxx is the exception code (two-byte hexadecimal value).
There are two types of exception conditions:
• Those that cause the common standard action [IOCA-6-007]
• Those that cause unique standard actions [IOCA-6-008]
The exception conditions are summarized in the following sections. Unique exception conditions and actions that are related to a specific element are described in the corresponding section for the element.
Image Segment Exception Conditions This exception occurs when a violation of format, parameter, or sequence of execution to this architecture is detected in the Image Segment.
The exception is represented in the following format:
EC-xxxx where:
EC identifies an Image Segment exception condition.
xxxx is the Image Segment exception condition code (two-byte hexadecimal value).
The following Image Segment exception conditions are defined:
• Exception conditions that cause the common standard action [IOCA-6-009]


• Exception conditions that cause unique standard actions [IOCA-6-010]
The IOCA Process Model recovers the exception condition according to the severity of the exception. The severity of an exception depends on the Image Data parameter or the Image Data.
If an exception action is defined in the corresponding section, the action is taken first, and the exception condition is kept until it is reported to the controlling environment.
If the action is not defined in the corresponding section, the rest of the Image Segment is not processed and the exception condition is reported immediately to the controlling environment. [IOCA-6-011]
## Exception Actions
The IOCA Process Model responds with an exception action when it detects an exception condition.
An exception condition prompts either of the following kinds of actions:
• An exception action defined by IOCA [IOCA-6-012]
• An alternative action that is defined outside the IOCA Process Model [IOCA-6-013]
The controlling environment governs which way the IOCA Process Model responds to the exception conditions. For example, the IPDS architecture has a command to specify whether the IPDS-defined page continuation action or the IOCA-defined exception action is to be taken.
IOCA Process Model Actions The IOCA Process Model recovers the exception condition according to the severity of the exception. The severity depends on the condition where the exception is detected.
The exception conditions are reset after the controlling environment has been notified.
Unique Standard Actions If a unique exception action is defined for the exception condition (such as for EC-9401 and EC-9511), the IOCA Process Model:
• Notifies the controlling environment of the condition [IOCA-6-014]
• Performs the defined unique action [IOCA-6-015]
Common Standard Action If no unique exception action is defined, the IOCA Process Model:
• Notifies the controlling environment of the condition [IOCA-6-016]
• Does not process the rest of the Image Segment [IOCA-6-017]
The exception conditions are reset after the controlling environment has been notified. [IOCA-6-018]
## Exception Conditions


Exception Conditions Causing the Common Standard Action EC-0001 Invalid or unsupported code within an Image Segment Condition: An invalid or unsupported self-defining field is detected within the Image Segment.
EC-0002 Retired Condition: Retired. See Appendix G, “Retired Architecture” for information about this retired exception condition.
EC-0003 The LENGTH value is not in the valid range Condition: The LENGTH value is not in the valid range.
EC-0004 Invalid parameter value Condition: A parameter value is not in the valid range.
Note: In cases where this exception is being generated for self-defining fields for which an EC-xx10 exception is available, it is recommended that IOCA receivers generate the EC-xx10 exception instead of exception EC-0004. For example, for the IDE Size parameter, EC-9610 would be generated and not EC-0004.
EC-0005 Invalid length Condition: The LENGTH value is not in the valid, function-set specified range. EC-0005 is optional—IOCA receivers can generate EC-0003 instead of EC-0005.
EC-xx0F Invalid sequence Condition: The sequence of self-defining fields is not correct within an Image Segment. This exception condition is also raised when a mandatory or necessary self-defining field is missing, or when a self-defining field other than Image Data, or Band Image Data, appears more than once in an Image Segment. Value xx in the exception code depends on the parameter in which the exception occurred. The parameter code is placed in this xx position.
For example:
• EC-700F for Begin Segment [IOCA-6-019]
• EC-710F for End Segment (see Note below) [IOCA-6-020]
• EC-8C0F for Begin Tile [IOCA-6-021]
• EC-8D0F for End Tile [IOCA-6-022]
• EC-8E0F for Begin Transparency Mask [IOCA-6-023]
• EC-8F0F for End Transparency Mask [IOCA-6-024]
• EC-910F for Begin Image Content [IOCA-6-025]
• EC-920F for Image Data (see Note below) [IOCA-6-026]
• EC-930F for End Image Content [IOCA-6-027]
• EC-940F for Image Size [IOCA-6-028]
• EC-950F for Image Encoding [IOCA-6-029]
• EC-960F for IDE Size [IOCA-6-030]
• EC-970F (Retired) [IOCA-6-031]
• EC-980F for Band Image [IOCA-6-032]
• EC-9B0F for IDE Structure [IOCA-6-033]
• EC-9C0F for Band Image Data (see Note below) [IOCA-6-034]
• EC-9F0F for External Algorithm Specification [IOCA-6-035]
• EC-B30F for nColor Names [IOCA-6-036]
• EC-B50F for Tile Position [IOCA-6-037]
• EC-B60F for Tile Size [IOCA-6-038]
• EC-B70F for Tile Set Color [IOCA-6-039]
• EC-B80F for Include Tile [IOCA-6-040]
• EC-BB0F for Tile TOC [IOCA-6-041]
• EC-CE0F for Image Subsampling [IOCA-6-042]
Note: This exception condition is not raised when the indicated self-defining field appears more than once.
Exceptions


EC-xx10 Invalid or unsupported Image Data parameter value Condition: The specified value for a parameter is not valid in the architecture or function sets, or is not supported by the implementation. Value xx in the exception code depends on the parameter in which the exception occurred. The parameter code is placed in this xx position.
For example:
• EC-9410 for Image Size [IOCA-6-043]
• EC-9510 for Image Encoding [IOCA-6-044]
• EC-9610 for IDE Size [IOCA-6-045]
• EC-9710 (Retired) [IOCA-6-046]
• EC-9810 for Band Image [IOCA-6-047]
• EC-9B10 for IDE Structure [IOCA-6-048]
• EC-9F10 for External Algorithm Specification [IOCA-6-049]
• EC-B310 for nColor Names [IOCA-6-050]
• EC-B510 for Tile Position [IOCA-6-051]
• EC-B610 for Tile Size [IOCA-6-052]
• EC-B710 for Tile Set Color [IOCA-6-053]
• EC-BB10 for Tile TOC [IOCA-6-054]
• EC-CE10 for Image Subsampling [IOCA-6-055]
Note: Some controlling environments, such as IPDS, define an exception for an invalid value in the Set Extended Bilevel Image Color self-defining field. Therefore, exception condition EC-F410 is reserved in IOCA for this use.
EC-xx11 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: The specified value for a parameter is not consistent with a value specified in another parameter or with the image data following it. Value xx in the exception code depends on the parameter in which the exception occurred. The parameter code is placed in this xx position.
For example:
• EC-9411 for Image Size [IOCA-6-056]
• EC-9611 for IDE Size [IOCA-6-057]
• EC-9F11 for External Algorithm Specification [IOCA-6-058]
• EC-B311 for nColor Names [IOCA-6-059]
• EC-B511 for Tile Position [IOCA-6-060]
• EC-B611 for Tile Size [IOCA-6-061]
• EC-B711 for Tile Set Color [IOCA-6-062]
• EC-B811 for Include Tile [IOCA-6-063]
• EC-BB11 for Tile TOC [IOCA-6-064]
EC-9201 Invalid existence of Image Data Condition: Image Data should not be present, as in the case when the image data is in bands.
EC-9801 Invalid Band Image parameter and Image Subsampling parameter coexistence Condition: In some function sets, the Band Image parameter and the Image Subsampling parameter cannot coexist in the same Image Content.
EC-9814 Invalid number of bands and bit counts Condition: The number of BITCNT parameters is not equal to the BCOUNT in the Band Image parameter.
EC-9815 Invalid IDE size Condition: The IDE size, determined by the Band Image parameter, does not match the IDE Size parameter.
Exceptions


EC-9B18 Invalid IDE Structure parameter Condition: One of the following conditions occurs in the IDE Structure parameter:
• The sum of the SIZE values does not match the IDE size specified by the IDE Size parameter. [IOCA-6-065]
• The color model is CMYK and SIZE4 is missing. [IOCA-6-066]
• SIZE4 is present and the color model is not CMYK or nColor. [IOCA-6-067]
• More than four SIZE parameters are present and the color model is not nColor. [IOCA-6-068]
• The color model is nColor and the number of SIZE parameters is not equal to the second half of the FORMAT byte. [IOCA-6-069]
EC-9C01 Invalid existence of Band Image Data Condition: Band Image Data should not be present, as in the case when the image data is not in bands.
EC-9C17 Invalid number/sequence of Band Image Data Condition: The band numbers in a Band Image Data do not appear for the number of bands defined in the Band Image parameter, or do not appear in succeeding order.
EC-9F01 Missing External Algorithm Specification parameter or Image Encoding parameter Condition: An External Algorithm Specification parameter exists without a corresponding Image Encoding parameter, or an Image Encoding parameter exists that requires an External Algorithm Specification parameter that cannot be found.
EC-CE01 Invalid Band Image parameter and Image Subsampling parameter coexistence Condition: In some function sets, the Band Image parameter and the Image Subsampling parameter cannot coexist in the same Image Content.
Exceptions


Exception Conditions Causing Unique Standard Actions EC-9401 Inconsistent Image Size parameter value and Image Data Condition: The size detected in the image data is different from the HSIZE or VSIZE value of the Image Size parameter.
System action: The size detected from the image data is used.
EC-9511 Inconsistent Image Data parameters, or inconsistent Image Data parameter and Image Data Condition: The decoder encountered one of the following conditions when decompressing the image data:
• The image data is not encoded according to the compression or recording algorithm specified in the Image Encoding [IOCA-6-070]
parameter.
• The image data cannot be decoded successfully using the size values specified in the Image Size parameter. This [IOCA-6-071]
condition applies to compression or recording algorithms that do not permit the image size to be encoded in the image data.
• The image data is not in complete accordance with the compression algorithm specified in the Image Encoding [IOCA-6-072]
parameter.
• The image data is encoded using the algorithm specified in the Image Encoding parameter, but uses a function of the [IOCA-6-073]
algorithm that is unsupported by the receiver.
System action: Receivers should attempt to present or make use of all successfully decompressed image data. Note that the resulting partial image might differ from the original image.
EC-A902 Write outside of the Image Presentation Space Condition: A portion of the extracted image is written outside the Image Presentation Space.
System action: The portion inside the Image Presentation Space is presented, and the rest is discarded.
Exceptions


Mandatory or Optional Exception Conditions Exception IOCA MO:DCA IPDS EC-0001 Mandatory Same as IOCA Same as IOCA EC-0003 Mandatory Same as IOCA Same as IOCA EC-0004 Mandatory Same as IOCA Same as IOCA EC-0005 Optional* Same as IOCA Same as IOCA EC-xx0F Mandatory Same as IOCA Same as IOCA EC-xx10 Mandatory Same as IOCA Same as IOCA
EC-xx11 Mandatory Same as IOCA Same as IOCA EC-9201 Mandatory Same as IOCA Same as IOCA EC-9401 Mandatory Same as IOCA Same as IOCA EC-9801 Mandatory Same as IOCA Same as IOCA EC-9814 Mandatory Same as IOCA Same as IOCA EC-9815 Mandatory Same as IOCA Same as IOCA EC-9B18 Mandatory Same as IOCA Same as IOCA EC-9C01 Mandatory Same as IOCA Same as IOCA
EC-9C17 Mandatory Same as IOCA Same as IOCA EC-9F01 Mandatory Same as IOCA Same as IOCA EC-A902 Mandatory Same as IOCA Same as IOCA EC-CE01 Mandatory Same as IOCA Same as IOCA *Receiver can generate EC-0003 instead of EC-0005.
Mandatory or Optional Exception Conditions [IOCA-6-074]




