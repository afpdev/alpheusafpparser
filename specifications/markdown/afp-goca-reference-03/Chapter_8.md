Chapter 8. Exception Conditions
Exception conditions are detected in the graphics processor by the environment interface when interpreting instructions and commands, and by the drawing processor when interpreting commands and orders. The detection of exception conditions is mandatory unless noted otherwise.
Set Current Defaults Instruction Exceptions
The following are the exceptions, called instruction process checks, detected when interpreting Set Current
Defaults control instructions:
IPC-0002 This instruction process check is detected:
• If the SET parameter (byte 2) is invalid or unsupported [GOCA-8-001]
• If the FLAGS parameter (byte 5) bits 1-3 are not B'000', or bits 4-7 are not B'1 1 1 1' [GOCA-8-002]
• If an unallocated item is referenced in the MASK parameter (bytes 3-4) [GOCA-8-003]
IPC-0003 This instruction process check is detected:
• If the FLAGS parameter (byte 5) bit 0 is B'0' and LENGTH is not X'04' [GOCA-8-004]
• If the FLAGS parameter (byte 5) bit 0 is B'1' and the length of the immediate data (byte 6 onward) does not exactly match the length implied by the MASK parameter [GOCA-8-005]
IPC-0021 This instruction process check is detected if any values in the data are invalid or unsupported.
Begin Segment Command Exceptions
The following are the exceptions, called command process checks, detected when interpreting Begin Segment commands:
CPC-0001 Invalid command code specified.
CPC-7001 Begin Segment APP parameter has the value B'10'.
CPC-7082 Begin Segment APP parameter has the value B'01'.
CPC-70C1 Invalid parameter length specified.
CPC-70C5 Insufficien t data. The segment data is less than the length specified by SEGL parameter. [GOCA-8-006]

---

Drawing Order Exceptions
A drawing process exception condition (EC) exists whenever the drawing processor detects an invalid or unsupported order or an invalid or unsupported parameter value on an order. Each exception condition identified by the architecture has been assigned a unique code of the form EC-xxxx.
The architecture provides control over the way in which an exception condition is to be handled, as follows:
• For each exception condition, the AFP GOCA architecture defines the action that is to be taken when the condition arises. This action is one of the following: [GOCA-8-007]
- Report a drawing process check (DPC). The identifier of the DPC is the same as that of the exception condition; that is, exception condition EC-xxxx raises DPC-xxxx. [GOCA-8-008]
- Perform some architecture- or implementation-defined Standard action. For example, for EC-C301 on the [GOCA-8-009]
Character String order, which is the condition where a code point in the order does not refer to a valid graphic character , the architected standard action is to draw the standard default character symbol.
• The environment—for example, the IPDS environment—optionally can provide an exception handling control that causes the drawing processor to raise a drawing process check for each and every exception condition, rather than execute the standard action, if any , defined for the exception condition. This exception handling control, if provided, can specify what is to happen after the drawing process check has been raised; for example, terminate the draw function or skip to the next drawing order. [GOCA-8-010]
The exception conditions associated with each drawing order are listed with each order.
There are two types of exception condition detected when interpreting drawing orders:
• Those for which no architected standard action is defined [GOCA-8-011]
• Those that have a standard action defined [GOCA-8-012]
Drawing Order Exceptions [GOCA-8-013]

---

Exception Conditions without Standard Actions
This section lists those exception conditions that raise a drawing process check and that do not have a standard action defined:
EC-0002 Retired. See “ Retired Exception Conditions ” for more information.
EC-0003 Incorrect length specification. The length in the order is not a valid value for the order.
EC-0008 T runcated order. The order about to be executed is not a complete order.
This error can occur when the last order in a segment is being executed. This order meets one of the following conditions:
• The order is a fixed, two-byte format order, and the second byte is not in the segment. [GOCA-8-014]
• The order is a long format order, and the length byte is not in the segment. [GOCA-8-015]
• The order is a long or extended format order, and the number of bytes from the end of the length field to the end of the segment is less than the value of the length count. [GOCA-8-016]
• The order is an extended format order, and the qualifier byte is not in the segment. [GOCA-8-017]
• The order is an extended format order, and one or both of the length bytes are not in the segment. [GOCA-8-018]
Note that it is valid for drawing orders to be split across a segment boundary , as long as the second segment is an appended segment.
EC-000A Invalid descriptor . This condition occurs when the Graphics Data Descriptor (GDD), passed in the invocation sent to the drawing processor , contains invalid bits. A drawing process check is raised. This exception may optionally be generated in MO:DCA environments.
EC-000C One of the following conditions has occurred within the prolog section of a segment:
• An order that is not valid in the prolog has been detected. [GOCA-8-019]
• The end of the segment has been reached without an End Prolog order. [GOCA-8-020]
EC-0400 The Segment Characteristics order is detected outside the prolog section of a segment. This is an optional exception.
EC-3E00 An End Prolog order has occurred outside the prolog section of a segment.
EC-6000 An End Area order has been executed without an unmatched Begin Area order having previously been executed.
EC-6800 A Begin Area order has been executed after another Begin Area order, without an intervening
End Area order being executed.
EC-6801 A Begin Area order has been executed in a segment, and the end of the segment is reached without an End Area order having been executed.
EC-6802 A supported order that is invalid within an area is detected.
EC-9200 A Begin Image order was not executed before the Image Data order in this segment.
EC-9201 There are insufficient, or too many , bytes of data in the Image Data order.
EC-9300 An End Image order is executed without an unmatched Begin Image order having been executed previously.
EC-9301 The number of Image Data orders between the Begin Image and End Image orders is not equal to the number of rows in the image, as specified by the HEIGHT parameter in the Begin
Image order.
EC-D100 A Begin Image order has been executed in a segment, and the end of the segment is reached without an End Image order having been executed.
Drawing Order Exceptions [GOCA-8-021]

---

EC-D101 A Begin Image order has been executed in a segment, and a supported order other than a
Comment, No-Operation, Image Data, or End Image order is executed.
EC-D102 The value specified for the FORMAT parameter in a Begin Image order is not supported.
EC-E100 A relative line starts inside GPS, but then goes outside GPS. For devices that can maintain a position outside the GPS, this is an optional exception.
EC-E300 A partial arc started inside GPS, but then finished outside. Therefore, the calculated new current position is outside GPS. For devices that can maintain a position outside the GPS, this is an optional exception.
EC-E302 A negative value is specified for the SWEEP angle in a Partial Arc order.
EC-E303 A negative value is specified for the START angle in a Partial Arc order.
Drawing Order Exceptions [GOCA-8-022]

---

Exception Conditions with Standard Actions
This section lists those exception conditions that raise a drawing process check and that do have standard actions defined.
EC-0001 Unallocated order codes. All unallocated order codes are reserved for future use. If an attempt is made to execute one of these unallocated order codes, this exception condition occurs. This exception condition is also raised when a device tries to execute an order that it does not support.
EC-0001 takes priority over all other exception conditions when multiple exception conditions occur; for example, an unsupported order that is invalid in the current context.
Standard action: Skip over the order.
EC-0004 The attribute value specified in the order is not valid.
Standard action: The standard default value of the attribute is used, except for the Set Color and Set Extended Color drawing orders, where the action is implementation dependent.
EC-000D Graphic presentation space overflow . This condition occurs when an order is executed that tries to draw something outside the Graphic Presentation space. For devices that can maintain a position outside the GPS, this is an optional exception.
Standard action: The action is implementation dependent.
EC-000E The attribute value specified in the order is not supported.
Standard action: The standard default value of the attribute is used, except for the Set Color and Set Extended Color drawing orders, where the action is implementation dependent.
EC-0E02 Invalid or unsupported color space in Set Process Color , Linear Gradient, or Radial Gradient order.
Standard action: For the Set Process Color order, the device default color is used. For the
Linear Gradient or Radial Gradient orders, ignore the order.
EC-0E03 Invalid or unsupported color value in Set Process Color , Linear Gradient, or Radial Gradient order.
Standard action: For the Set Process Color order, the device default color is used. For the
Linear Gradient or Radial Gradient orders, ignore the order.
EC-0E04 Invalid highlight color percent value in Set Process Color , Linear Gradient, or Radial Gradient order.
Standard action: The maximum valid percent value is used.
EC-0E05 Invalid or unsupported number of bits for a color component in Set Process Color , Linear
Gradient, or Radial Gradient order.
Standard action: For the Set Process Color order, the device default color is used. For the
Linear Gradient or Radial Gradient orders, ignore the order.
EC-3400 The specific character angle requested is not supported.
Standard action: The closest character angle supported is used.
EC-5E00 An End Custom Pattern order has been executed without an unmatched Begin Custom
Pattern order having previously been executed.
Standard action: Ignore the End Custom Pattern order.
EC-6803 The pattern set identified by the current pattern set is not supported . This exception condition exists even if the current pattern symbol is set to X'00'.
Standard action: The standard default pattern set is used. In AFP environments, this is the default pattern set.
Drawing Order Exceptions [GOCA-8-023]

---

EC-6804 The current pattern symbol identifies an undefined symbol in the current pattern set.
Standard action: The standard default pattern symbol is used. In AFP environments, this is
X'10'—Solid fill.
EC-6805 T emporary storage overflow while drawing an area in an immediate segment.
Standard action: Drawing of the area is completed in an implementation-defined manner .
EC-6806 While inside the definition of a custom pattern, a Begin Area order has been executed but the current pattern symbol identifies a custom pattern or gradient .
Standard action: The standard default pattern symbol is used. In AFP environments this is
X'10'—Solid fill.
EC-C000 The HAXIS or V AXIS parameter in a Box order is too large to fit the indicated corner into the size of the box.
Standard action: Corners with the largest axis that fit the box are drawn.
EC-C001 The HAXIS or V AXIS parameter in a Box order is outside the range.
Standard action: A box with square corners is drawn.
EC-C200 The marker set identified by the value in the current marker set attribute is not available.
Standard action: The standard default marker set is used. In AFP environments, this is the default marker set.
EC-C201 The code point in the current marker symbol attribute is not defined in the current marker set.
Standard action: The standard default marker symbol is used. In AFP environments, this is
X'01'—Cross.
EC-C300 The font identified by the value in the current character set attribute is not available.
Standard action: The standard default character set is used. In AFP environments, this is the presentation device default font.
EC-C301 A code point in the order does not refer to a valid graphic character .
Standard action: The standard default character symbol is used.
EC-C302 The current character set attribute value identifies a character set definition that cannot support the functions implied by the current character precision attribute.
Standard action: The character set identified by the current character set attribute value is used, with the highest value of precision that the character set can support.
EC-C303 The character encoding is Unicode, but the code points are not valid Unicode data. For more details on the possible causes of this exception, see page 93.
Standard action: The remainder of the code points are skipped.
EC-C601 The drawing processor has detected an exceptional condition that might prevent the drawing of the arc within the normal limits of pel accuracy .
Standard action: The arc is drawn as accurately as the implementation can define. This action might produce straight lines.
EC-D103 The value specified for the WIDTH parameter in a Begin Image order is too large to allow the environment to completely present the image.
Standard action: The width of the image is truncated to allow presentation of the smaller image.
EC-D104 The value specified for the HEIGHT parameter in a Begin Image order is too large to allow the environment to completely present the image.
Drawing Order Exceptions [GOCA-8-024]

---

Standard action: The height of the image is truncated to allow presentation of the smaller image.
EC-DC00 The value specified for the pattern set parameter in a Linear Gradient order is invalid.
Standard action: Ignore the Linear Gradient order.
EC-DC01 The value specified for the pattern symbol parameter in a Linear Gradient order is invalid; a gradient cannot be assigned to pattern symbol X'00'.
Standard action: Ignore the Linear Gradient order.
EC-DC02 The pattern set and pattern symbol values in a Linear Gradient order are within the valid range, but a pattern already resides at that location.
Standard action: Ignore the Linear Gradient order.
EC-DC03 Insufficien t storage available to process and store a linear gradient.
Standard action: Ignore the Linear Gradient order.
EC-DC04 A value for one or both of the outside parameters in a Linear Gradient order is invalid.
Standard action: Use the value X'00' - None.
EC-DC05 The value specified for a color stop off set in a Linear Gradient order is invalid or is less than a previous color stop offs et.
Standard action: The color stop is ignored.
EC-DC06 Invalid length value in the color specification in a Linear Gradient order.
Standard action: Ignore the Linear Gradient order.
EC-DC07 Color space or color values in the Linear Gradient order are valid, but do not follow the rules for colors in a gradient.
Standard action: Ignore the Linear Gradient order.
EC-DD00 The value specified for the pattern set parameter in a Radial Gradient order is invalid.
Standard action: Ignore the Radial Gradient order.
EC-DD01 The value specified for the pattern symbol parameter in a Radial Gradient order is invalid; a gradient cannot be assigned to pattern symbol X'00'.
Standard action: Ignore the Radial Gradient order.
EC-DD02 The pattern set and pattern symbol values in a Radial Gradient order are within the valid range, but a pattern already resides at that location.
Standard action: Ignore the Radial Gradient order.
EC-DD03 Insufficien t storage available to process and store a radial gradient.
Standard action: Ignore the Radial Gradient order.
EC-DD04 A value for one or both of the outside parameters in a Radial Gradient order is invalid.
Standard action: Use the value X'00' - None.
EC-DD05 The value specified for a color stop off set in a Radial Gradient order is invalid or is less than a previous color stop offs et.
Standard action: The color stop is ignored.
EC-DD06 Invalid length value in the color specification in a Radial Gradient order.
Standard action: Ignore the Radial Gradient order.
Drawing Order Exceptions [GOCA-8-025]

---

EC-DD07 Color space or color values in the Radial Gradient order are valid, but do not follow the rules for colors in a gradient.
Standard action: Ignore the Radial Gradient order.
EC-DE00 A Begin Custom Pattern order has been executed after another Begin Custom Pattern order, without an intervening End Custom Pattern order being executed.
Standard action: Act as if an End Custom Pattern order had been received just prior to the current Begin Custom Pattern order.
EC-DE01 A Begin Custom Pattern order has been executed in a segment, and the end of the segment is reached without an End Custom Pattern order having been executed.
Standard action: Act as if an End Custom Pattern order had been received just prior to the end of the segment.
EC-DE02 The value specified for the pattern set parameter in a Begin Custom Pattern order is invalid.
Standard action: Skip to the End Custom Pattern drawing order, ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order.
EC-DE03 The value specified for the pattern symbol parameter in a Begin Custom Pattern order is invalid; a custom pattern cannot be assigned to pattern symbol X'00'.
Standard action: Skip to the End Custom Pattern drawing order, ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order.
EC-DE04 In a Begin Custom Pattern order, the pattern window values are ill defined: XLWIND is equal to or greater than XR WIND, or YBWIND is equal to or greater than YTWIND.
Standard action: Skip to the End Custom Pattern drawing order, ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order.
EC-DE05 The pattern set and pattern symbol parameters in a Begin Custom Pattern are within the valid range, but a pattern already resides at that location.
Standard action: Skip to the End Custom Pattern drawing order, ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order.
EC-DE06 Insufficien t storage available to process and store a custom pattern.
Standard action: Skip to the End Custom Pattern drawing order, ignoring all orders making up the custom pattern definition, including this Begin Custom Pattern order.
EC-DE07 A supported order that is invalid within a custom pattern definition is detected.
Note: The exception condition is raised when the order that is invalid is detected.
Standard action: Ignore the invalid order.
EC-DF00 The pattern set and pattern symbol parameters in a Delete Pattern order are within the valid range, but no pattern exists with the pattern set and pattern symbol specified.
Standard action: Ignore the Delete Pattern order.
EC-DF01 The value specified for the pattern set parameter in a Delete Pattern order is invalid.
Standard action: Ignore the Delete Pattern order.
EC-DF02 The value specified for the pattern symbol parameter in a Delete Pattern order is invalid;
pattern symbol X'00' cannot be deleted.
Standard action: Ignore the Delete Pattern order.
Drawing Order Exceptions [GOCA-8-026]

---

Copyright © AFP Consortium 1997, 2017 175
