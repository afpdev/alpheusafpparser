# Granular Test Coverage - IPDS

| Requirement ID | Summary | Coverage |
| :--- | :--- | :---: |
| IPDS-1-001 | compressed image data. | ❓ |
| IPDS-1-002 | Mixed Object Document Content Architecture (MO:DCA) | ❓ |
| IPDS-1-003 | Intelligent Printer Data Stream (IPDS) Architecture | ❓ |
| IPDS-1-004 | by pre-processing and post-processing devices attached to IPDS printers. | ❓ |
| IPDS-1-005 | Presentation Text Object Content Architecture (PTOCA): A data architecture for describing text objects that | ❓ |
| IPDS-1-006 | Image Object Content Architecture (IOCA): A data architecture for describing resolution-independent image | ❓ |
| IPDS-1-007 | Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA): A version of GOCA | ❓ |
| IPDS-1-008 | Bar Code Object Content Architecture (BCOCA): A data architecture for describing bar code objects, using a | ❓ |
| IPDS-1-009 | Font Object Content Architecture (FOCA): A resource architecture for describing the structure and content of | ❓ |
| IPDS-1-010 | Color Management Object Content Architecture (CMOCA): A resource architecture used to carry the color | ❓ |
| IPDS-1-011 | Metadata Object Content Architecture (MOCA): A resource architecture used to carry metadata in an AFP | ❓ |
| IPDS-1-012 | 205 Main Street | ❓ |
| IPDS-2-001 | Different applications, which can be independent of one another, create source data (such as graphics, | ❓ |
| IPDS-2-002 | IPDS data streams are independent of the carrying communications protocol. This allows the transmission of | ❓ |
| IPDS-2-003 | IPDS products transfer all data and controls through self-identifying structured fields, called IPDS | ❓ |
| IPDS-2-004 | IPDS architecture provides an extensive acknowledgment protocol at the data-stream level. This | ❓ |
| IPDS-2-005 | IPDS architecture provides support for media finishing using printer-attached devices or using pre- | ❓ |
| IPDS-2-006 | IPDS architecture provides support for color management whose goal is to provide consistent and accurate | ❓ |
| IPDS-2-007 | The spooled system environment | ❓ |
| IPDS-2-008 | The mainframe interactive environment | ❓ |
| IPDS-2-009 | The intelligent workstation or departmental system environment | ❓ |
| IPDS-2-010 | The local area network environment | ❓ |
| IPDS-2-011 | convert it into IPDS commands for the application. | ❓ |
| IPDS-2-012 | graphics, and image, that GDDM sends to the printer in the form of IPDS commands. | ❓ |
| IPDS-2-013 | transporting IPDS commands with a mixture of text, image, graphics, and bar code data. | ❓ |
| IPDS-2-014 | PDF , and PostScript, into IPDS data. | ❓ |
| IPDS-2-015 | 370 and 390 parallel channel (CCWs) | ❓ |
| IPDS-2-016 | IPDS Dialog command to end a dialog, the printer can then safely switch to a different dialog or session. | ❓ |
| IPDS-2-017 | Loaded Font This command set contains the IPDS commands to load font information. | ❓ |
| IPDS-2-018 | the MOCA architecture. | ❓ |
| IPDS-2-019 | function for text, IO-Image, graphics, and bar code data. | ❓ |
| IPDS-3-001 | size as the medium presentation space into which it is merged. | ❓ |
| IPDS-3-002 | 1. All IPDS printers allow text to be placed directly within a logical page using the Write Text | ❓ |
| IPDS-3-003 | 2. Some IPDS printers support text objects (in addition to the text-major concept). In this case, the | ❓ |
| IPDS-3-004 | Text data contains lines of character information and text rules, which the printer places at specified | ❓ |
| IPDS-3-005 | Image data contains raster information. Two types of image data, IM-Image and IO-Image, are | ❓ |
| IPDS-3-006 | Graphics data contains lines, curves, areas, and other drawing elements. Refer to Chapter 8, | ❓ |
| IPDS-3-007 | Bar Code data contains bar-coded, machine-readable characters and can also contain human- | ❓ |
| IPDS-3-008 | Object containers contain presentation data or non-presentation data whose syntactic and semantic | ❓ |
| IPDS-3-009 | BCOCA architectures define the presentation space for their respective data types. | ❓ |
| IPDS-3-010 | Figure 9. IPDS Presentation Spaces | ❓ |
| IPDS-3-011 | output rather than make you use integrated applications. | ❓ |
| IPDS-3-012 | Figure 11. Examples of Multiple Pages on a Medium Presentation Space | ❓ |
| IPDS-3-013 | placed on top of others. See Figure 57 for an example of this ability. | ❓ |
| IPDS-3-014 | Figure 12. Logical Division of Continuous Forms for Cut-Sheet Emulation | ❓ |
| IPDS-3-015 | An LCC command without the X'C3nn' keyword is encountered. | ❓ |
| IPDS-3-016 | More than one copy is specified in any copy subgroup. | ❓ |
| IPDS-3-017 | specified while X'C300' is in effect, the printer exits cut-sheet emulation mode. | ❓ |
| IPDS-3-018 | media source and one media destination. | ❓ |
| IPDS-3-019 | object area within an overlay, and an object area within a page segment. | ❓ |
| IPDS-3-020 | Figure 13. A Sample Page with an Overlay and Page Segment | ❓ |
| IPDS-3-021 | 1. When the PFO is intended for an entire sheet side, it is invoked via the LCC command in a manner similar | ❓ |
| IPDS-3-022 | 2. When the PFO is intended for a page (such as a MO:DCA PMC-PFO), it is invoked via the IO command | ❓ |
| IPDS-3-023 | A special merging occurs when using a preprinted form overlay (PFO). In this case, the printer treats the | ❓ |
| IPDS-3-024 | A special merging occurs for UP3I Print Data objects; these objects are printed by a pre-processing or post- | ❓ |
| IPDS-3-025 | Medium presentation space – This is the base IPDS presentation space into which all other presentation | ❓ |
| IPDS-3-026 | Medium overlay presentation space – This presentation space is often called the medium overlay's logical | ❓ |
| IPDS-3-027 | Page presentation space – This presentation space is often called the page's logical page, and is merged | ❓ |
| IPDS-3-028 | Page overlay presentation space – This presentation space is often called the page overlay's logical page, | ❓ |
| IPDS-3-029 | Object area presentation space – This presentation space is provided for each text object, IO Image | ❓ |
| IPDS-3-030 | Data object presentation space – This presentation space is provided for each PTOCA, IOCA, GOCA, | ❓ |
| IPDS-3-031 | option, into the corresponding object area presentation space. | ❓ |
| IPDS-3-032 | Figure 14. Merging Presentation Spaces | ❓ |
| IPDS-3-033 | Wherever the color attribute of P PFO is either color of medium, or “white” (CMYK = | ❓ |
| IPDS-3-034 | With other overlapping color values, the intersection assumes a new color attribute that is | ❓ |
| IPDS-3-035 | onto a preprinted form that has the color attribute of P PFO. In general, this mixing is a | ❓ |
| IPDS-3-036 | 1. Color of medium (X'FF08') is a valid color in all respects and the mixing rules apply to this color. Thus, | ❓ |
| IPDS-3-037 | 2. Some printers might print different data types or different elements within the same data type at different | ❓ |
| IPDS-3-038 | 3. Some printers simulate unsupported colors using device-dependent color simulation. Pels whose color is | ❓ |
| IPDS-3-039 | simulated in such a manner are subject to the same mixing rules as pels whose color is not simulated. | ❓ |
| IPDS-3-040 | Figure 15. Examples of Shaded Areas | ❓ |
| IPDS-3-041 | WI2-IOCA controls Extended Tile Set Color X'4402' None | ❓ |
| IPDS-3-042 | Consistent output across different devices | ❓ |
| IPDS-3-043 | Accurate output, to the best of the device's capability, with a wide variety of inputs | ❓ |
| IPDS-3-044 | Consistent output across different types of data created by a variety of applications or devices | ❓ |
| IPDS-3-045 | Flexible controls that enable the tuning of output to exact specifications | ❓ |
| IPDS-3-046 | important to know which color space is being used for the current print data before the hierarchy is searched | ❓ |
| IPDS-3-047 | 1. Some presentation data objects contain internal color management information; internal color | ❓ |
| IPDS-3-048 | 2. For EPS and PDF page objects only, when there is no object-level audit color-conversion CMR invoked, | ❓ |
| IPDS-3-049 | Reference for a description of situations in which audit color-conversion CMRs are used. | ❓ |
| IPDS-3-050 | 1. Some IPDS printers do not use link color-conversion (subset “LK”) CMRs, but instead use the audit color-conversion and instruction | ❓ |
| IPDS-3-051 | and instruction color-conversion CMRs. | ❓ |
| IPDS-3-052 | 1. Search at the data-object level | ❓ |
| IPDS-3-053 | 2. If an ICC Profile exists within the data object (for example, and ICC profile within a TIFF image), use it | ❓ |
| IPDS-3-054 | 3. Else search at the page or overlay level: | ❓ |
| IPDS-3-055 | 4. Else search at the home-state level: | ❓ |
| IPDS-3-056 | 5. Else use a default audit color-conversion CMR. Find an instruction color-conversion CMR by | ❓ |
| IPDS-3-057 | Color conversion: | ❓ |
| IPDS-3-058 | Halftoning: | ❓ |
| IPDS-3-059 | Color calibration: | ❓ |
| IPDS-3-060 | Color Management Resource Descriptor (X'91') triplet | ❓ |
| IPDS-3-061 | Invoke CMR (X'92') triplet | ❓ |
| IPDS-3-062 | Rendering Intent (X'95') triplet | ❓ |
| IPDS-3-063 | Fully Qualified Name (X'02') triplet (with FQN Type X'41') | ❓ |
| IPDS-3-064 | Invoke CMR command support (property pair X'706B') | ❓ |
| IPDS-3-065 | Set Presentation Environment-command support (property pair X'7008') | ❓ |
| IPDS-3-066 | Device Appearance (X'97') triplet support (property pair X'F206'); within the triplet, the printer-default | ❓ |
| IPDS-3-067 | Data-object-resource support (property pair X'1201') | ❓ |
| IPDS-3-068 | CMR object-type OID (in the XOH-OPC reply) | ❓ |
| IPDS-3-069 | XOH-OPC Product Identifier self-defining field with parameter ID X'0001' | ❓ |
| IPDS-3-070 | Color Fidelity (X'75') triplet | ❓ |
| IPDS-3-071 | CMR Tag Fidelity (X'96') triplet | ❓ |
| IPDS-3-072 | Support for CMR capture (property pair X'E000') | ❓ |
| IPDS-3-073 | Host-activated CMR support: | ❓ |
| IPDS-3-074 | CMRs can be reliably applied to all EPS/PDF objects (property pair X'E100') | ❓ |
| IPDS-3-075 | Pass-through audit color-conversion CMRs (property pair X'E102') | ❓ |
| IPDS-3-076 | Extended bar code color support (property pair X'4400') | ❓ |
| IPDS-3-077 | Extended IOCA bilevel color support (property pair X'4401') | ❓ |
| IPDS-3-078 | Extended IOCA tile-set-color support (property pair X'4402') | ❓ |
| IPDS-3-079 | Bilevel IO-Image color support on the RPO command (property pair X'4403') | ❓ |
| IPDS-3-080 | Bilevel and grayscale image color support for object containers (property pair X'5801') | ❓ |
| IPDS-3-081 | IPDS Trace support (property pair X'90F2') | ❓ |
| IPDS-3-082 | Long-ACK support (property pair X'F003') | ❓ |
| IPDS-3-083 | Note that audit color-conversion CMRs do not apply to named colors. | ❓ |
| IPDS-3-084 | To see which types of font resources are supported by your printer, refer to your printer documentation. | ❓ |
| IPDS-3-085 | LF1-type coded font, that consists of a fully described font plus font indexes, or that consists of several fully | ❓ |
| IPDS-3-086 | LF2-type coded font, that consists of a symbol set. | ❓ |
| IPDS-3-087 | LF3-type coded font, that consists of a code page plus a font character set. In addition, specific activation | ❓ |
| IPDS-3-088 | Font subset IDs: LF3 or LF4. | ❓ |
| IPDS-3-089 | 240 of an inch; there are 240 units in one inch. Resolution is the reciprocal of units of measure. For example, | ❓ |
| IPDS-3-090 | sometimes used as a synonym for unit of measure. | ❓ |
| IPDS-3-091 | where the sides are of unequal length and the wide side corresponds to the leading edge. | ❓ |
| IPDS-3-092 | edge of the physical medium as it moves through the printer. | ❓ |
| IPDS-3-093 | 105 mm microfilm and CINE representation on 16 mm microfilm, the title edge is equivalent to the leading edge | ❓ |
| IPDS-3-094 | coordinate system origin and the size is specified in Xm,Ym coordinates. | ❓ |
| IPDS-3-095 | extents of the medium presentation space. These values are used along with any printer-defined valid | ❓ |
| IPDS-3-096 | - Bar code data | ❓ |
| IPDS-3-097 | - Graphics data | ❓ |
| IPDS-3-098 | - IO Image data | ❓ |
| IPDS-3-099 | - Object Container data | ❓ |
| IPDS-3-100 | - Text data | ❓ |
| IPDS-3-101 | Location of the object area origin in the Xp,Yp coordinate system | ❓ |
| IPDS-3-102 | Rotation of the object area with respect to the Xp axis in the plane of the logical page, also referred to as | ❓ |
| IPDS-3-103 | Location of the object area origin in the I,B coordinate system | ❓ |
| IPDS-3-104 | Rotation of the object area with respect to the I axis in the plane of the logical page, also referred to as object | ❓ |
| IPDS-3-105 | page is given by the sum (O oa + Oi) modulo 360; that is, the remainder when (O oa + Oi) is divided by 360. | ❓ |
| IPDS-3-106 | 90 degrees, 180 degrees, or 270 degrees. | ❓ |
| IPDS-3-107 | p,Yp coordinate system and the logical page. | ❓ |
| IPDS-3-108 | 0 degree object area orientation with respect to I,B = 0,90 | ❓ |
| IPDS-3-109 | 0 degree object area orientation with respect to I,B = 0,270 | ❓ |
| IPDS-3-110 | 90 degree object area orientation with respect to I,B = 270,0 | ❓ |
| IPDS-3-111 | 90 degree object area orientation with respect to I,B = 270,180 | ❓ |
| IPDS-3-112 | 180 degree object area orientation with respect to I,B = 180,270 | ❓ |
| IPDS-3-113 | 180 degree object area orientation with respect to I,B = 180,90 | ❓ |
| IPDS-3-114 | 270 degree object area orientation with respect to I,B = 90,180 | ❓ |
| IPDS-3-115 | 270 degree object area orientation with respect to I,B = 90,0 | ❓ |
| IPDS-3-116 | presentation spaces within object areas that are then positioned on a logical page. | ❓ |
| IPDS-3-117 | orientation. See Figure 57 for an example of this situation. | ❓ |
| IPDS-3-118 | The area that corresponds to the physical printable area if working with a secure overlay or if there is no | ❓ |
| IPDS-3-119 | The intersection of the area corresponding to the physical printable area with the user-printable area; see | ❓ |
| IPDS-3-120 | print a security label on each side of a sheet. | ❓ |
| IPDS-3-121 | 1 X'0201..03' | ❓ |
| IPDS-3-122 | 2 X'08C1..00' 2 X'08C1..00' 2 X'08C1..00' X'08C1..00' | ❓ |
| IPDS-3-123 | 1. If a presentation object type has the ability to generate data outside its presentation space, and if that is considered | ❓ |
| IPDS-3-124 | 2. Some older printers used X'020A..05' instead of X'08C1..00' or X'0411..00'. | ❓ |
| IPDS-3-125 | 1. Presentation-services programs should use the STM property pairs and XOH-OPC self-defining fields to | ❓ |
| IPDS-3-126 | 2. Presentation-services programs should use the information in the XOH-OPC Product Identifier self- | ❓ |
| IPDS-3-127 | 3. Presentation-services programs should use the STM type and model information to determine whether or | ❓ |
| IPDS-3-128 | function as long as it is appropriately identified in the STM or XOH-OPC replies. | ❓ |
| IPDS-3-129 | Each byte contains eight bits. | ❓ |
| IPDS-3-130 | Bytes of an IPDS structure are numbered from left to right beginning with byte 0 with the leftmost byte as | ❓ |
| IPDS-3-131 | Bit strings are numbered, from left to right, beginning with 0. For example, a one-byte bit string contains bit 0, | ❓ |
| IPDS-3-132 | For numerical binary data, bit 0 is the most significant bit. For example, decimal 13 is equivalent to binary | ❓ |
| IPDS-3-133 | Negative values are in twos-complement form. | ❓ |
| IPDS-3-134 | Field values are expressed in hexadecimal or binary notation: | ❓ |
| IPDS-3-135 | Some bits or bytes are labeled reserved. The content of reserved fields are not checked by printers. | ❓ |
| IPDS-3-136 | Some fields or values are labeled Retired item n, where n is an identifying number. These fields or values are | ❓ |
| IPDS-3-137 | Values not explicitly defined in the range column of a field are reserved. | ❓ |
| IPDS-3-138 | On the following pages, commands are described in tables, with additional information about specific fields | ❓ |
| IPDS-3-139 | 1. Calculate the number of printer-supported units per inch as follows: | ❓ |
| IPDS-3-140 | If the length of the measurement base for a field is 10 inches, divide the number of printer-supported | ❓ |
| IPDS-3-141 | If the length of the measurement base for a field is 10 centimeters, multiply the number of printer- | ❓ |
| IPDS-3-142 | 2. Calculate the number of printer-supported units per IPDS unit as follows: | ❓ |
| IPDS-3-143 | Divide the number of printer-supported units per inch calculated in the previous step by 1440 (the | ❓ |
| IPDS-3-144 | 3. Calculate the required value in the printer-supported unit of measure as follows: | ❓ |
| IPDS-3-145 | Multiply the IPDS-specified subset range values for the desired field, after converting to base 10, by the | ❓ |
| IPDS-3-146 | Round off the product to the nearest integer; for example, 2.5 becomes 3 and 2.4 becomes 2. | ❓ |
| IPDS-3-147 | Adjust the new range so that it is a subset of the IPDS-specified range. | ❓ |
| IPDS-3-148 | 1. Printer-supported units per inch = 2400 ÷ 10 = 240 | ❓ |
| IPDS-3-149 | 2. Printer-supported units per IPDS unit = 240 ÷ 1440 = 1/6 | ❓ |
| IPDS-3-150 | 3. Range at 2400 units per 10 inches: | ❓ |
| IPDS-3-151 | 2400 units per 10 inches 945 units per 10 | ❓ |
| IPDS-3-152 | be addressed with 1440ths to also support 240ths in these commands. | ❓ |
| IPDS-3-153 | 4 BITS IPDS command flags | ❓ |
| IPDS-3-154 | The host wants a positive acknowledgment that the printer has received, accepted, and syntax-checked the | ❓ |
| IPDS-3-155 | The command is sent by the host to request the return of printer information. | ❓ |
| IPDS-3-156 | Indicate that a received command or command sequence requesting acknowledgment has been accepted | ❓ |
| IPDS-3-157 | Return requested printer information | ❓ |
| IPDS-3-158 | Report exceptions | ❓ |
| IPDS-3-159 | sends a positive Acknowledge Reply (ACK) only in response to ARQs. | ❓ |
| IPDS-3-160 | Home state | ❓ |
| IPDS-3-161 | Presentation-object | ❓ |
| IPDS-3-162 | Page state | ❓ |
| IPDS-3-163 | Overlay state | ❓ |
| IPDS-3-164 | Page segment state | ❓ |
| IPDS-3-165 | Object- | ❓ |
| IPDS-3-166 | Font state | ❓ |
| IPDS-3-167 | Code page state | ❓ |
| IPDS-3-168 | IO-Image resource state | ❓ |
| IPDS-3-169 | Metadata state | ❓ |
| IPDS-3-170 | Anystate | ❓ |
| IPDS-3-171 | printer information to the host presentation services program. | ❓ |
| IPDS-3-172 | Text state | ❓ |
| IPDS-3-173 | IO-Image state | ❓ |
| IPDS-3-174 | IM-Image state | ❓ |
| IPDS-3-175 | Graphics state | ❓ |
| IPDS-3-176 | Bar Code state | ❓ |
| IPDS-3-177 | Object Container state (presentation form) | ❓ |
| IPDS-3-178 | Data Object Resource Equivalence 2 | ❓ |
| IPDS-3-179 | Data Object Resource Equivalence | ❓ |
| IPDS-3-180 | Data Object Resource Equivalence | ❓ |
| IPDS-3-181 | Home state | ❓ |
| IPDS-3-182 | Page state | ❓ |
| IPDS-3-183 | Overlay state | ❓ |
| IPDS-3-184 | IO-Image Resource state | ❓ |
| IPDS-3-185 | Object-Container Resource | ❓ |
| IPDS-3-186 | Page Text state | ❓ |
| IPDS-3-187 | Page Graphics state | ❓ |
| IPDS-3-188 | Page IO-Image state | ❓ |
| IPDS-3-189 | Page Bar Code state | ❓ |
| IPDS-3-190 | Page Object Container state | ❓ |
| IPDS-3-191 | Page Segment Graphics state | ❓ |
| IPDS-3-192 | Page Segment IO-Image state | ❓ |
| IPDS-3-193 | Page Segment Bar Code state | ❓ |
| IPDS-3-194 | Overlay Text state | ❓ |
| IPDS-3-195 | Overlay Graphics state | ❓ |
| IPDS-3-196 | Overlay IO-Image state | ❓ |
| IPDS-3-197 | Overlay Bar Code state | ❓ |
| IPDS-3-198 | Overlay Object Container state | ❓ |
| IPDS-3-199 | Home state after completion. | ❓ |
| IPDS-3-200 | Load Equivalence (LE) X'D61D' Home Home | ❓ |
| IPDS-3-201 | 1. The End command is valid at any time in the text, IO-Image, graphics, bar code, and object-container | ❓ |
| IPDS-3-202 | 2. The XOA Discard Buffered Data and XOA Discard Unstacked Pages commands cause the printer to enter | ❓ |
| IPDS-3-203 | associated with the state transition. | ❓ |
| IPDS-3-204 | The presentation services program in the host is responsible for the overall management of resources in the | ❓ |
| IPDS-3-205 | The printer is responsible for the management of stored resources that are not directly controlled by the host. | ❓ |
| IPDS-3-206 | A resource-caching intermediate device, if present, assumes part of the management responsibility for those | ❓ |
| IPDS-3-207 | A data-stream-spooling intermediate device, if present, takes on the role of host, and its presentation | ❓ |
| IPDS-3-208 | Coded fonts | ❓ |
| IPDS-3-209 | Data object fonts | ❓ |
| IPDS-3-210 | Data-object-font components | ❓ |
| IPDS-3-211 | Data object resources | ❓ |
| IPDS-3-212 | Page segments | ❓ |
| IPDS-3-213 | Overlays | ❓ |
| IPDS-3-214 | Saved page groups | ❓ |
| IPDS-3-215 | refer to “Data Object Resources, Data-Object-Font Components, and Setup Files”. | ❓ |
| IPDS-3-216 | Graphic character set global ID (GCSGID) | ❓ |
| IPDS-3-217 | Code page global ID (CPGID) | ❓ |
| IPDS-3-218 | Font typeface global ID (FGID) | ❓ |
| IPDS-3-219 | Font width (FW) | ❓ |
| IPDS-3-220 | Remote PrintManager MVS™ format (82 or 164 bytes per resource) | ❓ |
| IPDS-3-221 | Extended remote PrintManager MVS format (86 bytes per resource) | ❓ |
| IPDS-3-222 | MVS host unalterable remote font format (172 bytes per font) | ❓ |
| IPDS-3-223 | particular host-to-printer session. | ❓ |
| IPDS-3-224 | Complete fonts, including the following: | ❓ |
| IPDS-3-225 | Code pages | ❓ |
| IPDS-3-226 | Data-object resources (including IOCA tile resources, PDF resources, Non-OCA Resource objects, color | ❓ |
| IPDS-3-227 | Data-object-font components (including TrueType/OpenType fonts and TrueType/OpenType collections) | ❓ |
| IPDS-3-228 | Font character sets | ❓ |
| IPDS-3-229 | Overlays | ❓ |
| IPDS-3-230 | Page segments | ❓ |
| IPDS-3-231 | Function related to the physical presence of resources in the printer | ❓ |
| IPDS-3-232 | Function related to the availability of resources in the printer | ❓ |
| IPDS-3-233 | Function related to the invocation of resources in the printer. | ❓ |
| IPDS-3-234 | case of an intermediate device with a resource caching function, the resource to be captured and made | ❓ |
| IPDS-3-235 | 1. Ensuring that the resource is physically present in a usable form in the printer | ❓ |
| IPDS-3-236 | 2. Assigning a HAID, HARID, or variable-length group ID to the resource | ❓ |
| IPDS-3-237 | such as printers. Resource activation using the AR + XOA-RRL command sequence is used with devices that | ❓ |
| IPDS-3-238 | and resource ID formats are supported in an AR command or an AR + XOA-RRL command sequence. | ❓ |
| IPDS-3-239 | IDs used in the invocation. | ❓ |
| IPDS-3-240 | Page or overlay state WIC2 | ❓ |
| IPDS-3-241 | Page or overlay state WOCC | ❓ |
| IPDS-3-242 | ICMR (home state) | ❓ |
| IPDS-3-243 | IDO (included object) | ❓ |
| IPDS-3-244 | LPD (page) | ❓ |
| IPDS-3-245 | LPD (page overlay) | ❓ |
| IPDS-3-246 | LPD (medium overlay) | ❓ |
| IPDS-3-247 | LPD (preprinted form overlay) | ❓ |
| IPDS-3-248 | RPO (preRIPped object) | ❓ |
| IPDS-3-249 | WBCC (bar code object) | ❓ |
| IPDS-3-250 | WGC (graphics object) | ❓ |
| IPDS-3-251 | WIC2 (IOCA image object) | ❓ |
| IPDS-3-252 | WOCC (data object) | ❓ |
| IPDS-3-253 | WTC (text object) | ❓ |
| IPDS-3-254 | HAID from an Invoke CMR command | ❓ |
| IPDS-3-255 | 1. Setup files are not treated as resources; when a setup file is downloaded, the HAID value in the WOCC | ❓ |
| IPDS-3-256 | 2. Metadata objects are treated as object containers in MO:DCA, but are not valid object containers in | ❓ |
| IPDS-3-257 | Not applicable Home state Color management | ❓ |
| IPDS-3-258 | ----- or ----- | ❓ |
| IPDS-3-259 | ----- or ----- | ❓ |
| IPDS-3-260 | ----- or ----- | ❓ |
| IPDS-3-261 | ----- or ----- | ❓ |
| IPDS-3-262 | ----- or ----- | ❓ |
| IPDS-3-263 | ----- or ----- | ❓ |
| IPDS-3-264 | ----- or ----- | ❓ |
| IPDS-3-265 | ----- or ----- | ❓ |
| IPDS-3-266 | ----- or ----- | ❓ |
| IPDS-3-267 | ----- or ----- | ❓ |
| IPDS-3-268 | ----- or ----- | ❓ |
| IPDS-3-269 | ----- or ----- | ❓ |
| IPDS-3-270 | ----- or ----- | ❓ |
| IPDS-3-271 | ----- or ----- | ❓ |
| IPDS-3-272 | ----- or ----- | ❓ |
| IPDS-3-273 | ----- or ----- | ❓ |
| IPDS-3-274 | ----- or ----- | ❓ |
| IPDS-3-275 | Not applicable Home state Font resource | ❓ |
| IPDS-3-276 | ----- or ----- | ❓ |
| IPDS-3-277 | state (or in all three states). Refer to the “Object-Container Type Support Self-Defining Field” in the XOH-OPC reply for more information. | ❓ |
| IPDS-3-278 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-3-279 | EPS without transparency | ❓ |
| IPDS-3-280 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-3-281 | IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-3-282 | JPEG (Joint Photographic Experts Group) AFPC JPEG Subset | ❓ |
| IPDS-3-283 | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-3-284 | Overlay | ❓ |
| IPDS-3-285 | PCL (Printer Command Language) page object | ❓ |
| IPDS-3-286 | PDF (Portable Document Format) multiple-page file with transparency | ❓ |
| IPDS-3-287 | PDF multiple-page file without transparency | ❓ |
| IPDS-3-288 | PDF single page with transparency | ❓ |
| IPDS-3-289 | PDF single page without transparency | ❓ |
| IPDS-3-290 | PNG (Portable Network Graphics) AFPC PNG Subset | ❓ |
| IPDS-3-291 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-3-292 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-3-293 | TIFF with transparency | ❓ |
| IPDS-3-294 | TIFF without transparency | ❓ |
| IPDS-3-295 | TIFF multiple-image file with transparency | ❓ |
| IPDS-3-296 | TIFF multiple-image file without transparency | ❓ |
| IPDS-3-297 | demonstrate an optimal data stream. | ❓ |
| IPDS-3-298 | - operation = Save Pages | ❓ |
| IPDS-3-299 | - group level = X'08' | ❓ |
| IPDS-3-300 | - initiate group | ❓ |
| IPDS-3-301 | - terminate group | ❓ |
| IPDS-3-302 | - group level = X'08' | ❓ |
| IPDS-3-303 | - group level = X'08' | ❓ |
| IPDS-3-304 | - variable length group ID = document one | ❓ |
| IPDS-3-305 | - variable length group ID = document one | ❓ |
| IPDS-3-306 | -sequence number = X'00000001' | ❓ |
| IPDS-3-307 | 116. This overview section describes the relationship between various TrueType metrics and the | ❓ |
| IPDS-3-308 | 0  character rotation | ❓ |
| IPDS-3-309 | 0  inline direction, 0  character rotation | ❓ |
| IPDS-3-310 | needed to estimate appropriate FOCA equivalent values. | ❓ |
| IPDS-3-311 | 90  inline direction, 270  character rotation | ❓ |
| IPDS-3-312 | 0  character rotation | ❓ |
| IPDS-3-313 | usually are the largest characters in the font. | ❓ |
| IPDS-3-314 | manner used to convert 0° metrics to 180° metrics. | ❓ |
| IPDS-3-315 | WMC ... End sequences can be received immediately following the BO, with one metadata | ❓ |
| IPDS-3-316 | All metadata immediately after the BP | ❓ |
| IPDS-3-317 | All current home-state metadata | ❓ |
| IPDS-3-318 | All metadata immediately after the BO in the overlay definition | ❓ |
| IPDS-3-319 | All current home-state metadata | ❓ |
| IPDS-3-320 | All metadata associated with the page on which the overlay is being included | ❓ |
| IPDS-3-321 | If the overlay is included in another overlay, all metadata associated with the | ❓ |
| IPDS-3-322 | All metadata immediately after the WOCC/WIC2 in the object definition | ❓ |
| IPDS-3-323 | All current home-state metadata | ❓ |
| IPDS-3-324 | All metadata associated with the page on which the object is being included | ❓ |
| IPDS-3-325 | If the object is included in an overlay, all metadata associated with the including | ❓ |
| IPDS-3-326 | All metadata immediately after the “begin” command in the object definition | ❓ |
| IPDS-3-327 | All current home-state metadata | ❓ |
| IPDS-3-328 | All metadata associated with the page on which the object is being included | ❓ |
| IPDS-3-329 | If included in an overlay, all metadata associated with the including overlay | ❓ |
| IPDS-3-330 | All metadata immediately after the begin command for the object | ❓ |
| IPDS-3-331 | All current home-state metadata | ❓ |
| IPDS-3-332 | All metadata associated with the page | ❓ |
| IPDS-3-333 | exception handling to debug documents. | ❓ |
| IPDS-3-334 | Pages exactly | ❓ |
| IPDS-3-335 | Report | ❓ |
| IPDS-3-336 | Do not report | ❓ |
| IPDS-3-337 | Report | ❓ |
| IPDS-3-338 | No AEA | ❓ |
| IPDS-3-339 | EPP off | ❓ |
| IPDS-3-340 | EPP on | ❓ |
| IPDS-3-341 | Continuation | ❓ |
| IPDS-3-342 | Do not report | ❓ |
| IPDS-3-343 | EPP on | ❓ |
| IPDS-3-344 | Continuation | ❓ |
| IPDS-3-345 | Report | ❓ |
| IPDS-3-346 | The reporting or suppressing of three types of exceptions: undefined-character checks, page-position | ❓ |
| IPDS-3-347 | The implementation of an Alternate Exception Action (AEA) when a valid request is received but not | ❓ |
| IPDS-3-348 | The termination or continuation procedure to follow if the AEA is not to be taken, or if no AEA is associated | ❓ |
| IPDS-3-349 | For some printers, the highlighting of position-check exceptions when a Page-Continuation Action (PCA) is | ❓ |
| IPDS-3-350 | For some printers, the use of Exception Page Print (EPP) to control printing of page information when the | ❓ |
| IPDS-3-351 | Control printing of page information when the printer detects a data-stream exception | ❓ |
| IPDS-3-352 | Enable automatic skipping of data types not supported by the printer | ❓ |
| IPDS-3-353 | Suppress the return of exception reports to the host when alternate actions are acceptable to the user and no | ❓ |
| IPDS-3-354 | The host can issue the XOA Exception-Handling Control command in any printer state. | ❓ |
| IPDS-3-355 | Whether printing should continue when an exception is detected | ❓ |
| IPDS-3-356 | Whether an exception should be reported | ❓ |
| IPDS-3-357 | For color exceptions, what type of color substitution is permitted | ❓ |
| IPDS-3-358 | Bilevel IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-3-359 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-3-360 | EPS without transparency | ❓ |
| IPDS-3-361 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-3-362 | Graphics (characters, lines, arcs, image, solid-area fill, pattern fill) | ❓ |
| IPDS-3-363 | IM Image | ❓ |
| IPDS-3-364 | JPEG (Joint Photographic Experts Group) AFPC JPEG Subset | ❓ |
| IPDS-3-365 | JPEG2000 File Format (JP2) | ❓ |
| IPDS-3-366 | Logical-page colors | ❓ |
| IPDS-3-367 | Object-area colors | ❓ |
| IPDS-3-368 | PCL (Printer Command Language) page object | ❓ |
| IPDS-3-369 | PDF (Portable Document Format) multiple-page file with transparency | ❓ |
| IPDS-3-370 | PDF multiple-page file without transparency | ❓ |
| IPDS-3-371 | PDF single page with transparency | ❓ |
| IPDS-3-372 | PDF single page without transparency | ❓ |
| IPDS-3-373 | PNG (Portable Network Graphics) AFPC PNG Subset | ❓ |
| IPDS-3-374 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-3-375 | Text (characters, underscores, text rules) | ❓ |
| IPDS-3-376 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-3-377 | TIFF with transparency | ❓ |
| IPDS-3-378 | TIFF without transparency | ❓ |
| IPDS-3-379 | TIFF multiple-image file with transparency | ❓ |
| IPDS-3-380 | TIFF multiple-image file without transparency | ❓ |
| IPDS-3-381 | printer to emulate a black-only printer without grayscale capability. | ❓ |
| IPDS-3-382 | If power has been interrupted or if the printer has been reinitialized (returned an IML NACK), printer- | ❓ |
| IPDS-3-383 | Initial logical-page values are established when the printer receives the Logical Page Descriptor command. | ❓ |
| IPDS-3-384 | Initial data object values are established when the printer receives a Write Text Control, Write Image Control, | ❓ |
| IPDS-3-385 | All IPDS printers provide a printer default font that can be invoked within the data stream. The default font is | ❓ |
| IPDS-3-386 | a specific coded font. | ❓ |
| IPDS-4-001 | Table 19**. Device Control Commands | ❓ |
| IPDS-4-002 | AR | X'D62E' | Activate Resource | No | ❓ |
| IPDS-4-003 | ASN | X'D60A' | Activate Setup Name | No | ❓ |
| IPDS-4-004 | AFO | X'D602' | Apply Finishing Operations | No | ❓ |
| IPDS-4-005 | BP | X'D6AF' | Begin Page | Yes | ❓ |
| IPDS-4-006 | DF | X'D64F' | Deactivate Font | Yes | ❓ |
| IPDS-4-007 | DUA | X'D6CE' | Define User Area | No | ❓ |
| IPDS-4-008 | END | X'D65D' | End | Yes | ❓ |
| IPDS-4-009 | EP | X'D6BF' | End Page | Yes | ❓ |
| IPDS-4-010 | ISP | X'D67E' | Include Saved Page | No | ❓ |
| IPDS-4-011 | ICMR | X'D66B' | Invoke CMR | No | ❓ |
| IPDS-4-012 | LCC | X'D69F' | Load Copy Control | Yes | ❓ |
| IPDS-4-013 | LFE | X'D63F' | Load Font Equivalence | Yes | ❓ |
| IPDS-4-014 | LPD | X'D6CF' | Logical Page Descriptor | Yes | ❓ |
| IPDS-4-015 | LPP | X'D66D' | Logical Page Position | Yes | ❓ |
| IPDS-4-016 | MID | X'D601' | Manage IPDS Dialog | No | ❓ |
| IPDS-4-017 | NOP | X'D603' | No Operation | Yes | ❓ |
| IPDS-4-018 | PFC | X'D634' | Presentation Fidelity Control | No | ❓ |
| IPDS-4-019 | RPO | X'D67B' | Rasterize Presentation Object | No | ❓ |
| IPDS-4-020 | STM | X'D6E4' | Sense Type and Model | Yes | ❓ |
| IPDS-4-021 | SHS | X'D697' | Set Home State | Yes | ❓ |
| IPDS-4-022 | SPE | X'D608' | Set Presentation Environment | No | ❓ |
| IPDS-4-023 | XOA | X'D633' | Execute Order Anystate | See command description | ❓ |
| IPDS-4-024 | XOH | X'D68F' | Execute Order Home State | See command description | ❓ |
| IPDS-4-025 | X'D61C' | Retired Command Code | No | ❓ |
| IPDS-4-026 | Table 20**. Acknowledge Protocol | ❓ |
| IPDS-4-027 | ACK or NACK | X'D6FF' | Acknowledge Reply | Yes | ❓ |
| IPDS-4-028 | When the acknowledgment required (ARQ) bit in the flag byte of a received IPDS command is set to B'1' | ❓ |
| IPDS-4-029 | When the printer detects an exception and sends a negative response to the presentation services program | ❓ |
| IPDS-4-030 | If bit 1 of the flag byte is B'1', a correlation ID is present. If bit 1 of the flag byte is B'0', no correlation ID is | ❓ |
| IPDS-4-031 | If bit 2 of the flag byte is B'1', this response can be continued in a subsequent Acknowledge Reply; refer to | ❓ |
| IPDS-4-032 | Bit 6 of the flag byte can only be set to B'1' for NACKs, and indicates that additional information is available | ❓ |
| IPDS-4-033 | Bit 7 is the Persistent NACK bit for DSC Mode NACKs, but this bit has no meaning in other attachment | ❓ |
| IPDS-4-034 | 4 | BITS | IPDS Acknowledge Reply flags | ❓ |
| IPDS-4-035 | bit 0 | | B'0' | Reserved | ❓ |
| IPDS-4-036 | bit 1 | CID flag | B'0', B'1' | Correlation ID flag. If this bit is B'1', a two-byte correlation ID follows the flag byte. If this bit is B'0', the optional correlation ID is not present, and the following byte or bytes, if any, contain the data field. | ❓ |
| IPDS-4-037 | bit 2 | ACK continuation flag | B'0', B'1' | Acknowledgment continuation flag. If this bit is B'1', this response can be continued in a subsequent Acknowledge Reply. If this bit is B'0', this response is complete in this Acknowledge Reply. | ❓ |
| IPDS-4-038 | bits 3–5 | | B'000' | Reserved | ❓ |
| IPDS-4-039 | bit 6 | Additional information flag | B'0', B'1' | Additional exception information available flag. If this bit is B'1', this indicates that additional information is available for the exception reported by this NACK. This additional information can be obtained by sending the XOA Obtain Additional Exception Information (OAEI) command. A printer can make additional exception information available—and thus this bit can be B'1'—only if the printer is ready to respond to an immediate OAEI command to retrieve that information. If this bit is B'0', no additional information is available. This bit is only valid for NACKs and is ignored for ACKs. | ❓ |
| IPDS-4-040 | bit 7 | Persistent NACK flag | B'0', B'1' | Persistent NACK flag. This flag has meaning only in DSC-Mode NACKs; the flag has no meaning in all other attachment environments. | ❓ |
| IPDS-4-041 | Acknowledge Reply Data Format | ❓ |
| IPDS-4-042 | The Acknowledge Reply data field contains the acknowledge type, the page and copy counters, and the special data area. The two formats of the Acknowledge Reply data field are as follows: | ❓ |
| IPDS-4-043 | Four-Byte Page and Copy Counter Format** | ❓ |
| IPDS-4-044 | Eighteen-Byte Page and Copy Counter Format** | ❓ |
| IPDS-4-045 | The complete Acknowledge Reply is limited to either 256 bytes or 65,535 bytes depending on whether or not the printer supports long ACKs (indicated by property pair X'F003' in the Device-Control command-set vector of an STM reply). The maximum size of the special data area depends on the format used. Although an Acknowledge Reply is limited to a maximum of 256 bytes (or 65,535 bytes) successive Acknowledge Replies, each less than or equal to 256 bytes (or 65,535 bytes) in length, can be used to communicate responses longer than the Special Data Area in one Acknowledge Reply. The Acknowledgment Continuation bit is used to signal availability of more reply data. | ❓ |
| IPDS-4-046 | 0 | CODE | Acktype | See byte description | Acknowledge type. Determines the content of the remaining fields; see byte 0 description. | Either all of the X'00', X'01', X'04', X'06', and X'80' values or all of the X'40', X'41', X'44', X'46', and X'C0' values; see byte 0 description. | ❓ |
| IPDS-4-047 | 1–4 -or- 1–18 | UBIN | Counters | See byte description | Page and copy counters. See byte description. | ❓ |
| IPDS-4-048 | 5 to end -or- 19 to end | SDA | SDA | Depends on acktype (byte 0) | Special data area. Depends on acktype (byte 0). | ❓ |
| IPDS-4-049 | Table 21**. Acknowledge Types | ❓ |
| IPDS-4-050 | X'00' | No data | Four-byte | None | ❓ |
| IPDS-4-051 | X'40' | No data | Eighteen-byte | None | ❓ |
| IPDS-4-052 | X'01' | STM reply | Four-byte | Sense Type and Model reply | ❓ |
| IPDS-4-053 | X'41' | STM reply | Eighteen-byte | Sense Type and Model reply | ❓ |
| IPDS-4-054 | X'02' | Trace reply | Four-byte | Trace reply | ❓ |
| IPDS-4-055 | X'42' | Trace reply | Eighteen-byte | Trace reply | ❓ |
| IPDS-4-056 | X'03' | RRRL reply | Four-byte | Request Resident Resource List reply | ❓ |
| IPDS-4-057 | X'43' | RRRL reply | Eighteen-byte | Request Resident Resource List reply | ❓ |
| IPDS-4-058 | X'04' | RRL reply | Four-byte | Request Resource List reply | ❓ |
| IPDS-4-059 | X'44' | RRL reply | Eighteen-byte | Request Resource List reply | ❓ |
| IPDS-4-060 | X'05' | OAEI reply | Four-byte | Obtain Additional Exception Information reply | ❓ |
| IPDS-4-061 | X'45' | OAEI reply | Eighteen-byte | Obtain Additional Exception Information reply | ❓ |
| IPDS-4-062 | X'06' | OPC reply | Four-byte | Obtain Printer Characteristics reply | ❓ |
| IPDS-4-063 | X'46' | OPC reply | Eighteen-byte | Obtain Printer Characteristics reply | ❓ |
| IPDS-4-064 | X'07' | ASN reply | Four-byte | Activate Setup Name reply | ❓ |
| IPDS-4-065 | X'47' | ASN reply | Eighteen-byte | Activate Setup Name reply | ❓ |
| IPDS-4-066 | X'08' | RSNL reply | Four-byte | Request Setup Name List reply | ❓ |
| IPDS-4-067 | X'48' | RSNL reply | Eighteen-byte | Request Setup Name List reply | ❓ |
| IPDS-4-068 | X'80' | Sense data | Four-byte | Sense bytes | ❓ |
| IPDS-4-069 | X'C0' | Sense data | Eighteen-byte | Sense bytes | ❓ |
| IPDS-4-070 | X'FF' | Null ACK | No counters provided | This acknowledgment is returned if the presentation services program, using the communications protocol that carries IPDS commands, attempts to obtain a positive Acknowledge Reply (ACK) without first sending an IPDS command with the ARQ bit set to B'1'. This is considered to be an error at the IPDS level. | ❓ |
| IPDS-4-071 | Activate Setup Name | ❓ |
| IPDS-4-072 | Request Resident Resource List | ❓ |
| IPDS-4-073 | Sense Type and Model | ❓ |
| IPDS-4-074 | XOA Obtain Additional Exception Information | ❓ |
| IPDS-4-075 | XOA Request Resource List | ❓ |
| IPDS-4-076 | XOA Request Setup Name List | ❓ |
| IPDS-4-077 | XOH Obtain Printer Characteristics | ❓ |
| IPDS-4-078 | XOH Trace | ❓ |
| IPDS-4-079 | Positive responses with no special data area (Type X'00', X'40') | ❓ |
| IPDS-4-080 | Sense Type and Model (Type X'01', X'41') | ❓ |
| IPDS-4-081 | Trace (Type X'02', X'42') | ❓ |
| IPDS-4-082 | Request Resident Resource List (Type X'03', X'43') | ❓ |
| IPDS-4-083 | Request Resource List (Type X'04', X'44') | ❓ |
| IPDS-4-084 | Obtain Additional Exception Information (Type X'05', X'45') | ❓ |
| IPDS-4-085 | Obtain Printer Characteristics (Type X'06', X'46') | ❓ |
| IPDS-4-086 | Activate Setup Name (Type X'07', X'47') | ❓ |
| IPDS-4-087 | Request Setup Name List (Type X'08', X'48') | ❓ |
| IPDS-4-088 | Negative Acknowledgment (Type X'80', X'C0') | ❓ |
| IPDS-4-089 | An ACK indicates that the data stream up to and including the command with the Acknowledgment Request | ❓ |
| IPDS-4-090 | If the printer receives a command requesting acknowledgment, and if this command also requests specific | ❓ |
| IPDS-4-091 | If the printer generates the Acknowledge Reply as a result of detecting an exception, the printer sends a | ❓ |
| IPDS-4-092 | The Exception-Handling Control (EHC) used for a given exception is the one most recently received at the | ❓ |
| IPDS-4-093 | If the printer receives a command requesting an acknowledgment, the printer expects the presentation | ❓ |
| IPDS-4-094 | The IPDS architecture does not specify the number of NACKs that a printer must queue. Some printers | ❓ |
| IPDS-4-095 | When an exception is reported, any upstream data is discarded. Refer to “Page and Copy Counter | ❓ |
| IPDS-4-096 | Positive acknowledgment of page segments or overlays and the data they contain means that the command sequence is a valid IPDS command sequence and has been accepted for processing; see Figure 45. This acknowledgment does not necessarily mean that the commands have been syntax-checked. The | ❓ |
| IPDS-4-097 | Logical lockouts that occur as the result of a presentation services program failing to adhere to the rules | ❓ |
| IPDS-4-098 | LF3-type coded font: A font character set and a code page; activated at a particular size and a particular font | ❓ |
| IPDS-4-099 | Data-object font: A TrueType/OpenType font, an optional code page, and optional linked TrueType/ | ❓ |
| IPDS-4-100 | Data-object font: A TrueType/OpenType collection, either an index value or a full font name to identify the | ❓ |
| IPDS-4-101 | the AR entry remains in effect. | ❓ |
| IPDS-4-102 | The resource is successfully activated. | ❓ |
| IPDS-4-103 | An AR entry is encountered with the Reset Bit set; refer to byte 11. | ❓ |
| IPDS-4-104 | Another AR entry is encountered with the same Host-Assigned Resource ID; its values replace the values in | ❓ |
| IPDS-4-105 | If a date and time stamp is not supplied in the AR entry, the printer does not activate a captured LF1 or LF3 | ❓ |
| IPDS-4-106 | If the HARID is already activated for this resource type by a previous download command, by a previous AR | ❓ |
| IPDS-4-107 | For a coded font activation, the same HAID can be used with more than one FIS for a given resource ID. The | ❓ |
| IPDS-4-108 | If the HAID has not been used in a previous activation for this resource type and if the components of the | ❓ |
| IPDS-4-109 | If the resident resource identified by the resource ID was already activated by means of a different HARID, it | ❓ |
| IPDS-4-110 | When the requested activation fails, a subsequent XOA-RRL activation query normally receives a reply | ❓ |
| IPDS-4-111 | Zero or more AR entries in the following format: | ❓ |
| IPDS-4-112 | 0–1 | UBIN | Length | | Entry length, including this length field | See byte description | ❓ |
| IPDS-4-113 | 2 | CODE | RT | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'40'<br>X'41'<br>X'42' | Resource Type (RT):<br>No value supplied<br>Single-byte LF1-type or LF2-type coded font<br>Reserved<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Code page<br>Font character set<br>Single-byte coded-font index<br>Double-byte coded-font index<br>Coded font<br>Data object resource<br>Data-object font<br>Data-object-font component | X'00' and at least one Resource Type | ❓ |
| IPDS-4-114 | 3–4 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Host-Assigned ID (see note following table) | X'0000'<br>X'0001'–X'7EFF' | ❓ |
| IPDS-4-115 | 5 | CODE | Section ID | X'00'<br>X'41'–X'FE' | Section Identifier: No value supplied or Double-byte font section ID (see note following table) | See byte description | ❓ |
| IPDS-4-116 | 6 | CODE | RIDF | X'00'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'09'<br>X'0A' | Resource ID Format (RIDF):<br>No value supplied<br>GRID-parts format<br>Remote PrintManager MVS format<br>Extended Remote PrintManager MVS format<br>MVS Host Unalterable Remote Font Environment<br>Coded-font format<br>Object-OID format<br>Data-object-font format | X'00' and at least one Resource ID Format | ❓ |
| IPDS-4-117 | 7–8 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font Inline Sequence (see note following table):<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-4-118 | 9–10 | X'0000' | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-119 | 11 | BITS | Resource class flags | bit 0<br><br>bit 1<br>bit 2<br><br>bit 3<br><br>bit 4<br><br>bit 5<br><br>bits 6–7 | bit 0: Don't capture (B'0' May be captured, B'1' Must not be captured)<br>bit 1: Reserved (B'0')<br>bit 2: Reset (B'0' No Reset, B'1' Reset)<br>bit 3: Activation failed NACK (B'0' No NACK, B'1' NACK if fails)<br>bit 4: Substitution (B'0' Not allowed, B'1' Outline-font allowed)<br>bit 5: Private object name (B'0' Tied to object, B'1' Private)<br>bits 6–7: Reserved (B'00') | bit 0: B'0', B'1'<br>bit 1: B'0'<br>bit 2: B'0', B'1'<br>bit 3: B'0'<br>bit 4: B'0'<br>bit 5: B'0', B'1'<br>bits 6–7: B'00' | ❓ |
| IPDS-4-120 | 12 to end | | Resource ID | | Resource ID of the resource to be activated. If the printer supports resource ID triplets, the fixed portion of the resource ID can be followed by one or more of the following triplets:<br>X'01' Coded Graphic Character Set Global Identifier triplet<br>X'02' Fully Qualified Name triplet<br>X'50' Encoding Scheme ID triplet<br>X'62' Local Date and Time Stamp triplet<br>X'79' Metric Adjustment triplet<br>X'84' Font Resolution and Metric Technology triplet<br>X'8B' Data Object Font Descriptor triplet<br>X'8D' Linked Font triplet<br>X'91' Color Management Resource Descriptor triplet | ❓ |
| IPDS-4-121 | X'0002' (null entry), | ❓ |
| IPDS-4-122 | X'000C' (used to reset without specifying an equivalence) | ❓ |
| IPDS-4-123 | X'000C' plus the length of the resource ID (bytes 12 to end of entry) | ❓ |
| IPDS-4-124 | Table 22**. Valid RID Entry Lengths | ❓ |
| IPDS-4-125 | X'01', X'03', X'08', X'09', or X'10' | X'03' | X'0014' plus length of triplets | ❓ |
| IPDS-4-126 | X'06' | X'03' | X'000E', X'0010', or X'0010' plus length of triplets | ❓ |
| IPDS-4-127 | X'07' | X'03' | X'0010' or X'0010' plus length of triplets | ❓ |
| IPDS-4-128 | X'01', X'03', X'08', or X'09' | X'04' | X'00B0' | ❓ |
| IPDS-4-129 | X'04' or X'05' | X'04' | X'005E' | ❓ |
| IPDS-4-130 | X'04' | X'05' | X'0062' | ❓ |
| IPDS-4-131 | X'01', X'03', X'08', or X'09' | X'06' | X'00B8' plus length of triplets | ❓ |
| IPDS-4-132 | X'10' | X'07' | X'001E' plus length of triplets | ❓ |
| IPDS-4-133 | X'40' | X'09' | X'000E'–X'008D' plus length of triplets | ❓ |
| IPDS-4-134 | X'41' | X'0A' | X'0012' plus length of triplets | ❓ |
| IPDS-4-135 | X'42' | X'09' | X'000E'–X'008D' plus length of triplets | ❓ |
| IPDS-4-136 | RT = X'01'—Host-Assigned ID of the single-byte LF1-type or LF2-type coded font | ❓ |
| IPDS-4-137 | RT = X'03'—Host-Assigned ID of the double-byte LF1-type coded font section | ❓ |
| IPDS-4-138 | RT = X'04'—Host-Assigned ID of the Page Segment | ❓ |
| IPDS-4-139 | RT = X'05'—Host-Assigned ID of the Overlay | ❓ |
| IPDS-4-140 | RT = X'06'—Host-Assigned ID of the code page | ❓ |
| IPDS-4-141 | RT = X'07'—Host-Assigned ID of the font character set | ❓ |
| IPDS-4-142 | RT = X'08'—Host-Assigned ID of the single-byte LF1-type coded font | ❓ |
| IPDS-4-143 | RT = X'09'—Host-Assigned ID of the double-byte LF1-type coded font | ❓ |
| IPDS-4-144 | RT = X'10'—Host-Assigned ID of the coded font | ❓ |
| IPDS-4-145 | RT = X'40'—Host-Assigned ID of the data object resource | ❓ |
| IPDS-4-146 | RT = X'41'—Host-Assigned ID of the data-object font | ❓ |
| IPDS-4-147 | RT = X'42'—Host-Assigned ID of the data-object-font component | ❓ |
| IPDS-4-148 | RT = X'01'—Section Identifier field is ignored | ❓ |
| IPDS-4-149 | RT = X'03'—Section Identifier of the double-byte LF1-type coded-font section | ❓ |
| IPDS-4-150 | RT = X'04'—Section Identifier field is ignored | ❓ |
| IPDS-4-151 | RT = X'05'—Section Identifier field is ignored | ❓ |
| IPDS-4-152 | RT = X'06'—Section Identifier field is ignored | ❓ |
| IPDS-4-153 | RT = X'07'—Section Identifier field is ignored | ❓ |
| IPDS-4-154 | RT = X'08'—Section Identifier field is ignored | ❓ |
| IPDS-4-155 | RT = X'09'—Section Identifier of the double-byte LF1-type coded-font section | ❓ |
| IPDS-4-156 | RT = X'10'—Section Identifier field is ignored | ❓ |
| IPDS-4-157 | RT = X'40'—Section Identifier field is ignored | ❓ |
| IPDS-4-158 | RT = X'41'—Section Identifier field is ignored | ❓ |
| IPDS-4-159 | RT = X'42'—Section Identifier field is ignored | ❓ |
| IPDS-4-160 | X'00'—no format specified; valid only when the AR entry length is X'000C' | ❓ |
| IPDS-4-161 | X'03'—GRID-parts format | ❓ |
| IPDS-4-162 | X'04'—Remote PrintManager MVS format | ❓ |
| IPDS-4-163 | X'05'—Extended Remote PrintManager MVS format | ❓ |
| IPDS-4-164 | X'06'—MVS Host Unalterable Remote Font environment | ❓ |
| IPDS-4-165 | X'07'—Coded-font format | ❓ |
| IPDS-4-166 | X'09'—Object-OID format | ❓ |
| IPDS-4-167 | X'0A'—Data-object-font format | ❓ |
| IPDS-4-168 | For a specific code page, specify a two-byte GCSGID followed by a two-byte | ❓ |
| IPDS-4-169 | For a device version of the code page, specify a two-byte CPGID. A device version | ❓ |
| IPDS-4-170 | Followed by two-byte FGID | ❓ |
| IPDS-4-171 | also used to locate the resource. | ❓ |
| IPDS-4-172 | 1. If the LF3 command subset is supported, the printer attempts to find a font | ❓ |
| IPDS-4-173 | 2. If LF1-type coded fonts are supported, the printer attempts to find the components | ❓ |
| IPDS-4-174 | 3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set | ❓ |
| IPDS-4-175 | For fonts with FGID values less than 750 and with FGID values between 3840 and | ❓ |
| IPDS-4-176 | 4095 inclusive (fixed-pitch, uniform-character-increment, and PSM fonts), both the | ❓ |
| IPDS-4-177 | 1000 X font width | ❓ |
| IPDS-4-178 | FGID values between 750 and 2303 inclusive are invalid and the activation fails. | ❓ |
| IPDS-4-179 | For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and | ❓ |
| IPDS-4-180 | factor are three times the font width. | ❓ |
| IPDS-4-181 | For fonts with FGID values between 53,248 and 61,439 inclusive, both the | ❓ |
| IPDS-4-182 | Cyclic Redundancy Check: 2 bytes. | ❓ |
| IPDS-4-183 | MVS Host System ID: 8 bytes. | ❓ |
| IPDS-4-184 | VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the | ❓ |
| IPDS-4-185 | DSNAME of Host Library containing the resource: 44 bytes padded with blanks to | ❓ |
| IPDS-4-186 | Date Stamp: 6 bytes. | ❓ |
| IPDS-4-187 | Time Stamp: 8 bytes. | ❓ |
| IPDS-4-188 | Member Name from Host Library containing the resource: 8 bytes padded with | ❓ |
| IPDS-4-189 | subfields of the resource ID. | ❓ |
| IPDS-4-190 | Cyclic Redundancy Check: 2 bytes. | ❓ |
| IPDS-4-191 | MVS Host System ID: 8 bytes. | ❓ |
| IPDS-4-192 | VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the | ❓ |
| IPDS-4-193 | DSNAME of Host Library containing the resource: 44 bytes padded with blanks to | ❓ |
| IPDS-4-194 | Date Stamp: 6 bytes. | ❓ |
| IPDS-4-195 | Time Stamp: 8 bytes. | ❓ |
| IPDS-4-196 | Member Name from Host Library containing the resource: 8 bytes padded with | ❓ |
| IPDS-4-197 | External Unit Base Specification: 1 byte | ❓ |
| IPDS-4-198 | Reserved: 1 byte | ❓ |
| IPDS-4-199 | External Units Per Unit Base Specification: 2 bytes | ❓ |
| IPDS-4-200 | subfields of the resource ID. | ❓ |
| IPDS-4-201 | Cyclic Redundancy Check: 2 bytes. | ❓ |
| IPDS-4-202 | MVS Host System ID: 8 bytes. | ❓ |
| IPDS-4-203 | VOLSER of Host Library containing the resource: 6 bytes padded with blanks to the | ❓ |
| IPDS-4-204 | DSNAME of Host Library containing the resource: 44 bytes padded with blanks to | ❓ |
| IPDS-4-205 | Date Stamp: 6 bytes. | ❓ |
| IPDS-4-206 | Time Stamp: 8 bytes. | ❓ |
| IPDS-4-207 | Member Name from Host Library containing the resource: 8 bytes padded with | ❓ |
| IPDS-4-208 | GRID half: 4 bytes. For the code page subfield group, this is the Graphic Character | ❓ |
| IPDS-4-209 | 12 of the LFE command), that is described. | ❓ |
| IPDS-4-210 | subfields of the resource ID. | ❓ |
| IPDS-4-211 | 24 CODE Pattern | ❓ |
| IPDS-4-212 | 25 X'00' Reserved | ❓ |
| IPDS-4-213 | distance between adjacent character baselines when character rotation is zero | ❓ |
| IPDS-4-214 | 1. If the LF3 command subset is supported, the printer attempts to find a font | ❓ |
| IPDS-4-215 | 2. If LF1-type coded fonts are supported, the printer attempts to find the components | ❓ |
| IPDS-4-216 | 3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set | ❓ |
| IPDS-4-217 | For fonts with FGID values less than 750 and with FGID values between 3840 and | ❓ |
| IPDS-4-218 | 4095 inclusive (fixed-pitch, uniform-character-increment, and PSM fonts), the | ❓ |
| IPDS-4-219 | FGID values between 750 and 2303 inclusive are invalid and the activation fails. | ❓ |
| IPDS-4-220 | For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and | ❓ |
| IPDS-4-221 | For fonts with FGID values between 53,248 and 61,439 inclusive, the vertical scale | ❓ |
| IPDS-4-222 | If an FGID was not supplied in the AR command but a font character set was found, | ❓ |
| IPDS-4-223 | the printer may obtain the FGID from the font character set. | ❓ |
| IPDS-4-224 | 0 CODE Identifier X'06' This is a definite-short-form OID | ❓ |
| IPDS-4-225 | 1 UBIN Length X'00'–X'7F' Length of the following content bytes | ❓ |
| IPDS-4-226 | 2 to end Content Any value Content bytes that provide a unique ID for this object | ❓ |
| IPDS-4-227 | 1. A code page | ❓ |
| IPDS-4-228 | 2. A Unicode transformation (UTF-8); this method is specified with an Encoding | ❓ |
| IPDS-4-229 | 3. Default to the encoding scheme indicated in the Data Object Font Descriptor | ❓ |
| IPDS-4-230 | table. Any IPDS code page can be used (either fixed single byte or | ❓ |
| IPDS-4-231 | (as specified in the X'8B' triplet) and is not encoded with a | ❓ |
| IPDS-4-232 | “Linked Font (X'8D') Triplet” | ❓ |
| IPDS-4-233 | RT = X'01'—Font Inline Sequence of a single-byte LF1-type coded-font index; ignored for | ❓ |
| IPDS-4-234 | RT = X'03'—Font Inline Sequence of a double-byte LF1-type coded-font index; ignored for | ❓ |
| IPDS-4-235 | RT = X'04'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-236 | RT = X'05'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-237 | RT = X'06'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-238 | RT = X'07'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-239 | RT = X'08'—Font Inline Sequence of the single-byte LF1-type coded-font index | ❓ |
| IPDS-4-240 | RT = X'09'—Font Inline Sequence of the double-byte LF1-type coded-font section index | ❓ |
| IPDS-4-241 | RT = X'10'—Desired Font Inline Sequence for a coded font | ❓ |
| IPDS-4-242 | RT = X'40'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-243 | RT = X'41'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-244 | RT = X'42'—Font Inline Sequence field is ignored | ❓ |
| IPDS-4-245 | entry is saved with the resource. All resource ID triplets are also saved with the | ❓ |
| IPDS-4-246 | When a double-byte coded-font section (RT = X'03') is being activated | ❓ |
| IPDS-4-247 | When a CID-keyed outline font is the only substitution candidate | ❓ |
| IPDS-4-248 | “Data Object Font Descriptor (X'8B') Triplet” | ❓ |
| IPDS-4-249 | Table 23**. RT/RIDF Triplet Combinations | ❓ |
| IPDS-4-250 | For RT = X'01'** (single-byte LF1-type or LF2-type coded font) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'06'** (MVS Host Unalterable Remote Font environment) | Font Resolution and Metric Technology (X'84') triplet<br>-- used to find a single-byte LF1-type coded font | Optional | ❓ |
| IPDS-4-251 | For RT = X'03'** (double-byte LF1-type coded-font section) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'06'** (MVS Host Unalterable Remote Font environment) | Font Resolution and Metric Technology (X'84') triplet<br>-- used to find a double-byte LF1-type coded-font section | Optional | ❓ |
| IPDS-4-252 | For RT = X'06'** (code page) with **RIDF = X'03'** (GRID-parts format) | Local Date and Time Stamp (X'62') triplet<br>-- used to identify a particular version of a specific code page | Optional | ❓ |
| IPDS-4-253 | For RT = X'07'** (font character set) with **RIDF = X'03'** (GRID-parts format) | Local Date and Time Stamp (X'62') triplet<br>-- used to identify a particular version of a font character set | Optional | ❓ |
| IPDS-4-254 | For RT = X'10'** (coded font) with **RIDF = X'03'** (GRID-parts format) or **RIDF = X'07'** (coded-font format) | Metric Adjustment (X'79') triplet<br>-- used to adjust metrics in an outline coded font | Optional | ❓ |
| IPDS-4-255 | For RT = X'40'** (data object resource) with **RIDF = X'09'** (object-OID format) | Color Management Resource Descriptor (X'91') triplet<br>-- used to specify control information for color management resources | Required for a CMR, ignored for other data-object-resource types | ❓ |
| IPDS-4-256 | Coded Graphic Character Set Global Identifier (X'01') triplet<br>-- used to identify the encoding of any following AR-command triplets that contain character-encoded data | Optional | ❓ |
| IPDS-4-257 | Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name)<br>-- used to provide an optional object name for the resource (character encoding can be identified with a preceding X'01' triplet; however, if there is no preceding X'01' triplet, the name must be a character string that is encoded as UTF-16BE) | Optional | ❓ |
| IPDS-4-258 | For RT = X'41'** (data-object font) with **RIDF = X'0A'** (data-object-font format) | Fully Qualified Name (X'02') triplet (FQN type = X'DE')<br>-- used to specify a Full Font Name for a TrueType/OpenType collection; for the base font | Optional | ❓ |
| IPDS-4-259 | Encoding Scheme ID (X'50') triplet<br>-- used to specify how the data to be printed is encoded | Optional | ❓ |
| IPDS-4-260 | Data Object Font Descriptor (X'8B') triplet<br>-- used to specify activation parameters for a data-object font | Required | ❓ |
| IPDS-4-261 | Linked Font (X'8D') triplet<br>-- used to specify a linked TrueType/OpenType object | Optional | ❓ |
| IPDS-4-262 | For RT = X'42'** (data-object-font component) with **RIDF = X'09'** (object-OID format) | Coded Graphic Character Set Global Identifier (X'01') triplet<br>-- used to identify the encoding of any following AR-command triplets that contain character-encoded data | Optional | ❓ |
| IPDS-4-263 | Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name)<br>-- used to provide an optional object name for the resource (character encoding can be identified with a preceding X'01' triplet; however, if there is no preceding X'01' triplet, the name must be a character string that is encoded as UTF-16BE) | Optional | ❓ |
| IPDS-4-264 | With CID X'0009' or X'000F'–X'00D5' odd values | ❓ |
| IPDS-4-265 | The format of the data field for this command is as follows: | ❓ |
| IPDS-4-266 | 0–1 | | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-267 | 2 to end of ASN | | Triplet | | Zero or one Setup Name (X'9E') triplet | ❓ |
| IPDS-4-268 | The special data area of an ASN reply has the following format: | ❓ |
| IPDS-4-269 | 0–1 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-270 | 2 to end of ASN reply | | Triplet | | Zero or one Setup Name (X'9E') triplet | ❓ |
| IPDS-4-271 | A change from one active setup name to another active setup name | ❓ |
| IPDS-4-272 | A change from no active setup name to an active setup name | ❓ |
| IPDS-4-273 | A change from an active setup name to no active setup name | ❓ |
| IPDS-4-274 | A change from an active setup name to the same active setup name but the settings | ❓ |
| IPDS-4-275 | “Setup Name (X'9E') Triplet ” | ❓ |
| IPDS-4-276 | The format of the data field for this command is as follows: | ❓ |
| IPDS-4-277 | 0 to end of AFO | | Triplets | | Zero or more triplets:<br>X'85' Finishing Operation triplet<br>X'8E' UP3I Finishing Operation triplet | ❓ |
| IPDS-4-278 | 3I Finishing Operation (X'8E') Triplet” | ❓ |
| IPDS-4-279 | If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the | ❓ |
| IPDS-4-280 | If an operation can only be fully specified in one of the triplets, that triplet must be used. | ❓ |
| IPDS-4-281 | IPDS nesting rules apply equally to both triplets (for identical finishing operations the triplets are | ❓ |
| IPDS-4-282 | Compatible nesting combinations are determined by the printing system; UP3I operation-compatibility rules | ❓ |
| IPDS-4-283 | are ignored, a sequence number must be used to ensure that the triplets are not identical. | ❓ |
| IPDS-4-284 | 0–3 | UNDF | Page ID | Any binary value | Page ID | Any binary value | ❓ |
| IPDS-4-285 | value X'00000000' for exceptions that occur outside the scope of a page. | ❓ |
| IPDS-4-286 | specified deactivation type is ignored. | ❓ |
| IPDS-4-287 | 0 | CODE | Deactivation type | X'11'<br>X'12'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'2F'<br>X'30'<br>X'3F'<br>X'40'<br>X'4F'<br>X'50'<br>X'51'<br>X'5D'<br>X'5E'<br>X'5F'<br>X'60'<br>X'6E' | Deactivate a single-byte LF1-type or LF2-type coded font and all indexes<br>Deactivate a single-byte font index<br>Deactivate all single-byte LF1-type or LF2-type coded fonts and all indexes<br>Deactivate all single-byte LF1-type or LF2-type coded fonts and all indexes; identical to X'1E'<br>Deactivate double-byte LF1-type coded font section and all indexes<br>Deactivate a double-byte LF1-type coded font section, all higher sections, and all indexes<br>Deactivate a font index for a double-byte coded font section<br>Deactivate all double-byte LF1-type coded fonts and all indexes<br>Deactivate a code page<br>Deactivate all code pages<br>Deactivate a font character set<br>Deactivate all font character sets<br>Deactivate a coded font<br>Deactivate a coded font and all associated components<br>Deactivate all resident coded fonts and all associated components<br>Deactivate all coded fonts<br>Deactivate all coded fonts and all associated components<br>Deactivate a data-object font<br>Deactivate all data-object fonts | Refer to the note following the table. | ❓ |
| IPDS-4-288 | 1–2 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID; needed for deactivation types X'11', X'12', X'20', X'21', X'22', X'30', X'40', X'50', X'51', and X'60' | X'0001' – X'7EFF' | ❓ |
| IPDS-4-289 | 3 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier; needed for deactivation types X'20', X'21', and X'22' | X'00' | ❓ |
| IPDS-4-290 | 4–5 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font inline sequence; needed for deactivation types X'12' and X'22':<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-4-291 | are listed in the following table: | ❓ |
| IPDS-4-292 | Table 24**. Required and Optional Deactivation Types | ❓ |
| IPDS-4-293 | LF1 | X'11', X'12', X'1E', X'1F' | X'22', X'50', X'51', X'5D', X'5E', X'5F' | ❓ |
| IPDS-4-294 | LF2 | X'11', X'1E', X'1F' | X'50', X'51', X'5D', X'5E', X'5F' | ❓ |
| IPDS-4-295 | LF3 | X'30', X'3F', X'40', X'4F', X'50', X'51', X'5D', X'5E', X'5F' | None | ❓ |
| IPDS-4-296 | LF4 | X'30', X'3F' | None | ❓ |
| IPDS-4-297 | Double-byte coded fonts (in addition to the supported LFn subset(s)) STM property pair X'B001' | X'20', X'21', X'2F' | None | ❓ |
| IPDS-4-298 | Data-object fonts STM property pair X'F204' | X'60', X'6E' | None | ❓ |
| IPDS-4-299 | 3 contains the starting section number. | ❓ |
| IPDS-4-300 | command to deactivate data-object-font components. | ❓ |
| IPDS-4-301 | supplied. Exception ID X'0240..02' exists if an invalid or unsupported value is specified. | ❓ |
| IPDS-4-302 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-303 | The format of the data field for the DUA command is as follows: | ❓ |
| IPDS-4-304 | 0 | CODE | Reset | X'00'<br>X'01' | Reset user printable area:<br>X'00' A new UPA is being defined<br>X'01' Reset the UPA to the physical printable area | X'00'<br>X'01' | ❓ |
| IPDS-4-305 | 1 | CODE | Unit base | X'00'<br>X'01' | Unit base for this command:<br>X'00' Ten inches<br>X'01' Ten centimeters | X'00' | ❓ |
| IPDS-4-306 | 2–3 | UBIN | UPUB | X'0001' – X'7FFF' | Xm and Ym units per unit base | X'3840' | ❓ |
| IPDS-4-307 | 4–6 | SBIN | $X_m$ offset | X'FF8000'– X'007FFF' | Xm coordinate of the UPA origin specified in L-units | X'000000'– X'007FFF' | ❓ |
| IPDS-4-308 | 7–9 | SBIN | $Y_m$ offset | X'FF8000'– X'007FFF' | Ym coordinate of the UPA origin specified in L-units | X'000000'– X'007FFF' | ❓ |
| IPDS-4-309 | 10–12 | UBIN | Xm extent | X'000001'– X'007FFF' | Xm extent of the UPA specified in L-units | X'000001'– X'007FFF' | ❓ |
| IPDS-4-310 | 13–15 | UBIN | Ym extent | X'000001'– X'007FFF' | Ym extent of the UPA specified in L-units | X'000001'– X'007FFF' | ❓ |
| IPDS-4-311 | represented in the printer. | ❓ |
| IPDS-4-312 | The length of the END command can be: | ❓ |
| IPDS-4-313 | Without CID | X'0005'–X'7FFF' | ❓ |
| IPDS-4-314 | With CID | X'0007'–X'7FFF' | ❓ |
| IPDS-4-315 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-316 | logical page. Zero or more data bytes can be transmitted but are ignored. | ❓ |
| IPDS-4-317 | When an LCPC command is not followed by at least one LCP command | ❓ |
| IPDS-4-318 | When an LFC command is not followed by at least one LF command | ❓ |
| IPDS-4-319 | When an LFCSC command is not followed by at least one LF command | ❓ |
| IPDS-4-320 | When a WIC command is not followed by at least one WI command | ❓ |
| IPDS-4-321 | The length of the EP command can be: | ❓ |
| IPDS-4-322 | Without CID | X'0005'–X'7FFF' | ❓ |
| IPDS-4-323 | With CID | X'0007'–X'7FFF' | ❓ |
| IPDS-4-324 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-325 | that page is available to the printer. Zero or more data bytes can be transmitted but are ignored. | ❓ |
| IPDS-4-326 | Remove the saved page group (so that the group name can be reused) and re-save the group with the | ❓ |
| IPDS-4-327 | Direct the operator to restore the printer characteristics to those originally used when the group was | ❓ |
| IPDS-4-328 | BP , IO-with PFO parameter, ISP , EP | ❓ |
| IPDS-4-329 | BP , ISP , IO-with PFO parameter, EP | ❓ |
| IPDS-4-330 | The length of the ISP command can be: | ❓ |
| IPDS-4-331 | Without CID | X'000B'–X'7FFF' | ❓ |
| IPDS-4-332 | With CID | X'000D'–X'7FFF' | ❓ |
| IPDS-4-333 | invalid or unsupported. | ❓ |
| IPDS-4-334 | The format of the data field for this command is as follows: | ❓ |
| IPDS-4-335 | 0–3 | UBIN | Page sequence number | X'00000001'–X'FFFFFFFF' | Page sequence number for the page to be included | X'00000001'–X'FFFFFFFF' | ❓ |
| IPDS-4-336 | 4 to end of ISP | | Triplets | | One or more ISP triplets:<br>X'00' Group ID triplet with variable-length group ID | ❓ |
| IPDS-4-337 | exception ID X'0255..02' exists. | ❓ |
| IPDS-4-338 | “Group ID (X'00') Triplet” | ❓ |
| IPDS-4-339 | absent, exception ID X'0255..03' exists. | ❓ |
| IPDS-4-340 | hierarchy and the home-state level. | ❓ |
| IPDS-4-341 | ICMR command or until the printer is reinitialized (returns an IML NACK). | ❓ |
| IPDS-4-342 | CMR is not an error, but it performs no function. Refer to the CMR-Processing-Modes table, Table 62, for a description of which processing mode is appropriate for each type of CMR. | ❓ |
| IPDS-4-343 | vector of an STM reply. | ❓ |
| IPDS-4-344 | The length of the ICMR command can be: | ❓ |
| IPDS-4-345 | Without CID | X'000A'–X'7FFE' even values | ❓ |
| IPDS-4-346 | With CID | X'000C'–X'7FFE' even values | ❓ |
| IPDS-4-347 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-348 | The data in an ICMR command is defined as follows: | ❓ |
| IPDS-4-349 | 0 | BITS | Invocation flags | | bit 0: Reset<br>B'0' Don't reset<br>B'1' Reset to printer defaults<br>bits 1–7: Reserved (B'0000000') | B'0' or B'1' | ❓ |
| IPDS-4-350 | 1–4 | | Reserved | X'00000000' | Reserved | X'00000000' | ❓ |
| IPDS-4-351 | 5 to end | | ICMR entries | | Zero or more entries in the format below | ❓ |
| IPDS-4-352 | ICMR Entry Format:** | ❓ |
| IPDS-4-353 | 0–1 | CODE | HAID | X'0001'–X'7EFF' | Host-Assigned ID of previously activated CMR | X'0001'–X'7EFF' | ❓ |
| IPDS-4-354 | - **Bit 0 Reset flag**: When B'1', all previous home-state-level CMRs are reset so that there are no | ❓ |
| IPDS-4-355 | invoked CMRs at the home-state level. When B'0', this step is skipped (no resets occur). | ❓ |
| IPDS-4-356 | LIFO manner (last-invoked to first-invoked). | ❓ |
| IPDS-4-357 | Bits 1–7 Reserved** | ❓ |
| IPDS-4-358 | Bytes 1–4 Reserved** | ❓ |
| IPDS-4-359 | Bytes 5 to end ICMR entries** | ❓ |
| IPDS-4-360 | of that HAID are also removed. | ❓ |
| IPDS-4-361 | An LCC command without the X'C3nn' keyword (CSE) is encountered. | ❓ |
| IPDS-4-362 | An LCC command with the X'C2nn' keyword (N-up) is encountered. | ❓ |
| IPDS-4-363 | More than one copy is specified in any copy subgroup. | ❓ |
| IPDS-4-364 | 1. Mixing simplex and duplex copy subgroups causes exception ID X'02C3..01' to exist. All copy subgroups | ❓ |
| IPDS-4-365 | 2. If duplex is specified and the number of identical copies (byte 1) is not the same for both copy subgroups of | ❓ |
| IPDS-4-366 | Eject to new sheet | ❓ |
| IPDS-4-367 | One copy of each sheet | ❓ |
| IPDS-4-368 | Simplex printing | ❓ |
| IPDS-4-369 | One page per side | ❓ |
| IPDS-4-370 | No medium or preprinted form overlays | ❓ |
| IPDS-4-371 | No text suppressions | ❓ |
| IPDS-4-372 | Media selected from the media source specified by an XOH-SIMS command or, if no XOH-SIMS command | ❓ |
| IPDS-4-373 | has been received, from the printer-default media source | ❓ |
| IPDS-4-374 | Media stacked in the printer-default media destination | ❓ |
| IPDS-4-375 | command is discarded and the previously received LCC command remains in effect. | ❓ |
| IPDS-4-376 | 0 | UBIN | Count | X'02'–X'FE' (even values) | Copy-subgroup byte count, including this count field | X'02' | ❓ |
| IPDS-4-377 | 1 | UBIN | Copies | X'01'–X'FF' | Number of identical copies | X'01'; default value if no LCC is received | ❓ |
| IPDS-4-378 | 2 to end of copy subgroup | CODE | Keywords | | Zero or more copy modification keyword pairs. The first byte is the keyword ID; the second byte is the parameter. | ❓ |
| IPDS-4-379 | Table 25. LCC Keywords** | ❓ |
| IPDS-4-380 | X'80' | X'00'–X'FF' | Media source ID | Not in DC1 | ❓ |
| IPDS-4-381 | X'90' | X'00'–X'FF' | High-order byte of media-destination ID | Not in DC1 | ❓ |
| IPDS-4-382 | X'91' | X'00'–X'FF' | Low-order byte of media-destination ID | Not in DC1 | ❓ |
| IPDS-4-383 | X'C1' | X'00' | Simplex (the default) | X'00' | ❓ |
| IPDS-4-384 | X'01' | $Y_m$-axis duplex | ❓ |
| IPDS-4-385 | X'02' | $X_m$-axis duplex | ❓ |
| IPDS-4-386 | X'C2' | X'01'–X'04' | Number of partitions on each side (N-up) | Not in DC1 | ❓ |
| IPDS-4-387 | X'C3' | X'00' | Enable cut-sheet emulation, eject to next sheetlet, and do not allow N-up. | Not in DC1 | ❓ |
| IPDS-4-388 | X'D1' | X'01'–X'FF' | Suppression ID | X'01'–X'7F' | ❓ |
| IPDS-4-389 | X'D2' | X'01'–X'FE' | Preprinted form overlay ID | Not in DC1 | ❓ |
| IPDS-4-390 | X'E1' | X'01'–X'FE' | Medium overlay ID | X'01'–X'FE' | ❓ |
| IPDS-4-391 | X'E4' | X'00'–X'7E' | High-order byte of a medium overlay HAID | Not in DC1 | ❓ |
| IPDS-4-392 | X'E5' | X'00'–X'FF' | Low-order byte of a medium overlay HAID | Not in DC1 | ❓ |
| IPDS-4-393 | X'E6' | X'00'–X'7E' | High-order byte of a preprinted form overlay HAID | Not in DC1 | ❓ |
| IPDS-4-394 | X'E7' | X'00'–X'FF' | Low-order byte of a preprinted form overlay HAID | Not in DC1 | ❓ |
| IPDS-4-395 | The first byte is the control ID. | ❓ |
| IPDS-4-396 | The second byte is the associated parameter for this control ID. | ❓ |
| IPDS-4-397 | 1. These two-byte controls may appear in any order within the list. | ❓ |
| IPDS-4-398 | 2. Unspecified controls are set to the printer defaults. | ❓ |
| IPDS-4-399 | 3. Exception ID X'0232..01' exists if an invalid or unsupported keyword ID is specified. | ❓ |
| IPDS-4-400 | is specified in a copy subgroup. | ❓ |
| IPDS-4-401 | Table 26. Media Source Commands** | ❓ |
| IPDS-4-402 | LCC 2, ,3 | 2,D,3 where D is the default media source | ❓ |
| IPDS-4-403 | LCC 5,7 | 5,7 | ❓ |
| IPDS-4-404 | SIMS 9 | 9,9 | ❓ |
| IPDS-4-405 | LCC 2,3 | 2,3 | ❓ |
| IPDS-4-406 | LCC , ,2 | 9,9,2 | ❓ |
| IPDS-4-407 | LCC 7 | 7 | ❓ |
| IPDS-4-408 | SIMS 10 | 10 | ❓ |
| IPDS-4-409 | LCC 12, | 12,10 | ❓ |
| IPDS-4-410 | LCC , , , | 10,10,10,10 | ❓ |
| IPDS-4-411 | SIMS 3 | 3,3,3,3 | ❓ |
| IPDS-4-412 | supported by the printer. | ❓ |
| IPDS-4-413 | 1. If neither X'90' nor X'91' is specified, all sheets for this copy subgroup are | ❓ |
| IPDS-4-414 | 2. If only one of the X'90' and X'91' keywords are specified, the other | ❓ |
| IPDS-4-415 | 3. There can only be one stacker active at a time; when a new media | ❓ |
| IPDS-4-416 | 4. Exception ID X'0237..04' exists if the media-source ID specified cannot be | ❓ |
| IPDS-4-417 | 5. Exception ID X'0105..00' exists if a media-destination ID becomes | ❓ |
| IPDS-4-418 | 6. The X'90' keyword and the X'91' keyword may be specified only once per | ❓ |
| IPDS-4-419 | 7. For some printers, finishing operations can only be done when the output | ❓ |
| IPDS-4-420 | to, exception ID X'027C..09' exists. | ❓ |
| IPDS-4-421 | 11 for examples of how the N-up keyword can be used. | ❓ |
| IPDS-4-422 | 1    2    3 | ❓ |
| IPDS-4-423 | 2 11    2    3 | ❓ |
| IPDS-4-424 | 1    2    3 | ❓ |
| IPDS-4-425 | 1     23     4 | ❓ |
| IPDS-4-426 | 1       2 1       2 | ❓ |
| IPDS-4-427 | 1    2    3 | ❓ |
| IPDS-4-428 | 1    2    3 1    2    3 | ❓ |
| IPDS-4-429 | 1        23        4 | ❓ |
| IPDS-4-430 | 1        2 3        4 | ❓ |
| IPDS-4-431 | pages in the order in which they appear in the LCC command. The media | ❓ |
| IPDS-4-432 | exception ID X'0290..01' exists if an invalid overlay HAID value is specified. | ❓ |
| IPDS-4-433 | commands, new IPDS printers must support full-length LCC commands. | ❓ |
| IPDS-4-434 | Supports Independent Exception Page Print, the Exception Page Print bit from the most recently processed | ❓ |
| IPDS-4-435 | Does not support Independent Exception Page Print but has completed processing a page because the | ❓ |
| IPDS-4-436 | Does not support Independent Exception Page Print but has completed processing a page after successfully | ❓ |
| IPDS-4-437 | 1. Buffers copies of the sheet resulting from copy subgroups prior to the one in which the exception occurred. | ❓ |
| IPDS-4-438 | 2. Buffers N partial copies of the page in which the exception occurred (where N is the number of copies | ❓ |
| IPDS-4-439 | 3. Discards upstream data. | ❓ |
| IPDS-4-440 | 4. Adjusts page and copy counters as follows: | ❓ |
| IPDS-4-441 | 5. Reports any queued NACKs. | ❓ |
| IPDS-4-442 | 6. Continues processing the copy subgroup in which the exception occurred against the remaining pages of | ❓ |
| IPDS-4-443 | 7. If the data stream exception was synchronous, processes all subsequent copy subgroups using the LCC | ❓ |
| IPDS-4-444 | 8. Enters home state. | ❓ |
| IPDS-4-445 | 1. Terminates processing for the copy subgroup in which the exception occurs and for all subsequent copy | ❓ |
| IPDS-4-446 | 2. Discards the copy of the page for the exception copy subgroup. | ❓ |
| IPDS-4-447 | 3. Saves copies resulting from the previous copy subgroups. | ❓ |
| IPDS-4-448 | 4. Discards the page with the exception and any subsequent pages that have been received for the sheet. | ❓ |
| IPDS-4-449 | 5. Enters home state. | ❓ |
| IPDS-4-450 | 6. Discards upstream data. | ❓ |
| IPDS-4-451 | 7. Adjusts page and copy counters as follows: | ❓ |
| IPDS-4-452 | 8. Reports any queued NACKs. | ❓ |
| IPDS-4-453 | 9. Continues to process as determined by the next command received from the host, as shown in the | ❓ |
| IPDS-4-454 | Table 27. Exception Continuation Rules** | ❓ |
| IPDS-4-455 | Synchronous | Not any of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages</li><li>Load Copy Control.</li></ul> | Continue the copy subgroup processing with the copy subgroup in which the exception occurred. The host must resend the page that caused the exception and all subsequent pages for the sheet. | ❓ |
| IPDS-4-456 | Asynchronous | Not any of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages</li><li>Load Copy Control.</li></ul> | Process the next page received from the host starting with copy subgroup one, against the most recently-received LCC command. | ❓ |
| IPDS-4-457 | Synchronous or Asynchronous | One of the following:<ul><li>XOH Eject to Front Facing</li><li>XOH Erase Residual Font Data</li><li>XOH Erase Residual Print Data</li><li>XOH Page Counters Control with Byte 2 = X'00' or with Byte 2 = X'01'</li><li>XOA Discard Buffered Data</li><li>XOA Discard Unstacked Pages</li><li>XOH Stack Received Pages.</li></ul> | For all commands except XOA-DBD and XOA-DUP, buffer the remaining copy subgroups without the exception or any subsequent pages. Process the next page received from the host starting with copy subgroup one, against the LCC command associated with the page in which the exception occurred. | ❓ |
| IPDS-4-458 | Synchronous or Asynchronous | Load Copy Control | Buffer the remaining copy subgroups without the exception page or any subsequent pages. Process the next page received from the host starting with copy subgroup one, against the most recently-received LCC command. | ❓ |
| IPDS-4-459 | 1. Multiple data-stream exceptions can be detected on a page if the Page Continuation bit that applies to the | ❓ |
| IPDS-4-460 | 2. If an out of storage exception is detected on a page, the copy subgroup rules that apply when the page is | ❓ |
| IPDS-4-461 | 3. If an asynchronous non-data-stream exception is detected, the host must ensure that the copy counters | ❓ |
| IPDS-4-462 | against the most recently received LCC command beginning with copy subgroup one. | ❓ |
| IPDS-4-463 | 1. If the LF3 command subset is supported, the printer attempts to find a font character set and a code page. | ❓ |
| IPDS-4-464 | 2. If LF1-type coded fonts are supported, the printer attempts to find the components of either a single-byte or | ❓ |
| IPDS-4-465 | 3. If LF2-type coded fonts are supported, the printer attempts to find a symbol set coded font using the | ❓ |
| IPDS-4-466 | GCSGID, CPGID, FGID, and font width values. | ❓ |
| IPDS-4-467 | For fonts with FGID values less than 750 and with FGID values between 3840 and 4095 inclusive (fixed- | ❓ |
| IPDS-4-468 | 1000 X font width | ❓ |
| IPDS-4-469 | FGID values between 750 and 2303 inclusive are invalid and the activation fails. | ❓ |
| IPDS-4-470 | For fonts with FGID values between 2304 and 3839 inclusive, between 4096 and 53,247 inclusive, and | ❓ |
| IPDS-4-471 | For fonts with FGID values between 53,248 and 61,439 inclusive, both the horizontal scale factor and the | ❓ |
| IPDS-4-472 | If the HARID was already activated for this resource type by a previous download command or by a previous | ❓ |
| IPDS-4-473 | The same HAID can be used with more than one FIS for a given GRID. The activation fails, however, if the | ❓ |
| IPDS-4-474 | If the HAID has not been used in a previous coded font activation and if the components of the resident | ❓ |
| IPDS-4-475 | If the resident coded font identified by the GRID was already activated by means of a different HARID, it | ❓ |
| IPDS-4-476 | When the requested activation fails, a subsequent XOA-RRL activation query normally receives a reply | ❓ |
| IPDS-4-477 | indicating that the resource specified by the HARID is not activated. However, if the activation failed because | ❓ |
| IPDS-4-478 | an empty LFE command is received. | ❓ |
| IPDS-4-479 | that entry and all following entries in the LFE command are discarded; preceding entries remain in effect. | ❓ |
| IPDS-4-480 | 0 | CODE | LID | X'00'–X'FE' | Font local ID | X'00'–X'7F' | ❓ |
| IPDS-4-481 | 1–2 | CODE | HAID | X'0001'–X'7EFF' | Complete font Host-Assigned ID | X'0001'–X'7EFF' | ❓ |
| IPDS-4-482 | 3–4 | CODE | FIS | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Font inline sequence:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-4-483 | 5–6 | CODE | GCSGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Graphic Character Set Global ID (GCSGID)<br>Use default value<br>See note 2 | ❓ |
| IPDS-4-484 | 7–8 | CODE | CPGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Code Page Global ID (CPGID)<br>Use default value<br>See note 2 | ❓ |
| IPDS-4-485 | 9–10 | CODE | FGID | X'0000'<br>X'0001'–X'FFFE'<br>X'FFFF' | No value supplied; see note 1<br>Font Typeface Global ID (FGID)<br>Use default value<br>See note 2 | ❓ |
| IPDS-4-486 | 11–12 | CODE | FW | X'0000'<br>X'0001'–X'7FFF'<br>X'FFFF' | No value supplied; see note 1<br>Font Width (FW)<br>Use default value<br>See note 2 | ❓ |
| IPDS-4-487 | 13 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-488 | 14 | BITS | LFE flags | ❓ |
| IPDS-4-489 | bit 0 | | B'0', B'1' | Symbol Set present in printer | B'0' | ❓ |
| IPDS-4-490 | bits 1–2 | | B'00' | Reserved | B'00' | ❓ |
| IPDS-4-491 | bit 3 | | B'0', B'1' | Double high | B'0' | ❓ |
| IPDS-4-492 | bit 4 | | B'0', B'1' | Italics | B'0' | ❓ |
| IPDS-4-493 | bit 5 | | B'0', B'1' | Double strike | B'0' | ❓ |
| IPDS-4-494 | bit 6 | | B'0', B'1' | Bold | B'0' | ❓ |
| IPDS-4-495 | bit 7 | | B'0', B'1' | Double wide | B'0' | ❓ |
| IPDS-4-496 | 15 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-497 | 1. If bytes 5–12 contain zeros, the LFE command is not requesting the activation of a resident, raster fully | ❓ |
| IPDS-4-498 | 2. These values are printer-specific. Refer to your printer documentation for available values. | ❓ |
| IPDS-4-499 | 3. Exception ID X'023A..02' exists if the maximum number of activated font components supported by the | ❓ |
| IPDS-4-500 | Select the default font (the preferred method) | ❓ |
| IPDS-4-501 | Select an EBCDIC coded font | ❓ |
| IPDS-4-502 | Select a data-object font using an EBCDIC code page | ❓ |
| IPDS-4-503 | the HAID value is already in use. | ❓ |
| IPDS-4-504 | character set, all graphic characters in the specified character set | ❓ |
| IPDS-4-505 | it has been previously downloaded. | ❓ |
| IPDS-4-506 | Support of a 43 byte long data field is mandatory. | ❓ |
| IPDS-4-507 | It is permissible for a printer to support shorter LPD commands that provide some, but not all of the initial text | ❓ |
| IPDS-4-508 | Optional triplets can be placed at the end of the LPD command (bytes 43 to end) if support for these triplets | ❓ |
| IPDS-4-509 | is indicated by property pairs in the Device-Control command-set vector of an STM reply. | ❓ |
| IPDS-4-510 | The format of the data field for the LPD command is as follows: | ❓ |
| IPDS-4-511 | 0 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-4-512 | 1 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-513 | 2–3 | UBIN | XUPUB | X'0001'–X'7FFF' | $X_m, X_p,$ and $I$ units per unit base | X'3840' | ❓ |
| IPDS-4-514 | 4–5 | UBIN | YUPUB | X'0001'–X'7FFF' | $Y_m, Y_p,$ and $B$ units per unit base; must equal the value in bytes 2–3 | X'3840' | ❓ |
| IPDS-4-515 | 6 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-516 | 7–9 | UBIN | $X_p$ extent | X'000001'–X'007FFF' | $X_p$ extent of the logical page | X'000001'–X'007FFF' (Refer to the note following the table.) | ❓ |
| IPDS-4-517 | 10 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-518 | 11–13 | UBIN | $Y_p$ extent | X'000001'–X'007FFF' | $Y_p$ extent of the logical page | X'000001'–X'007FFF' (Refer to the note following the table.) | ❓ |
| IPDS-4-519 | 14 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-520 | 15 | BITS | Ordered data flags | ❓ |
| IPDS-4-521 | bit 0 | | B'0', B'1' | Ordered page | B'0' | ❓ |
| IPDS-4-522 | bits 1–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-4-523 | 16–23 | X'00..00' | Reserved | X'00..00' | ❓ |
| IPDS-4-524 | Initial text-major conditions:** | ❓ |
| IPDS-4-525 | 24–25 | CODE | $I$-axis orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700'<br>X'FFFF' | 0 degrees<br>90 degrees<br>180 degrees<br>270 degrees<br>Printer default | X'0000'<br>X'FFFF' | ❓ |
| IPDS-4-526 | 26–27 | CODE | $B$-axis orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700'<br>X'FFFF' | 0 degrees<br>90 degrees<br>180 degrees<br>270 degrees<br>Printer default | X'2D00'<br>X'FFFF' | ❓ |
| IPDS-4-527 | 28–29 | SBIN | Initial $I$ | X'0000'–X'7FFF' | Initial $I$ print coordinate | X'0000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-4-528 | 30–31 | SBIN | Initial $B$ | X'0000'–X'7FFF' | Initial $B$ print coordinate | X'0000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-4-529 | 32–33 | UBIN | Inline margin | X'0000'–X'7FFF'<br>X'FFFF' | Inline margin<br>Printer default | X'0000'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-4-530 | 34–35 | UBIN | Interchar. adjustment | X'0000'–X'7FFF'<br>X'FFFF' | Intercharacter adjustment<br>Printer default | X'0000'–X'00FF' (Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-4-531 | 36–37 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-532 | 38–39 | UBIN | Baseline increment | X'0000'–X'7FFF'<br>X'FFFF' | Baseline increment<br>Printer default | X'0000'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-4-533 | 40 | CODE | LID | X'00'–X'FE'<br>X'FF' | Font local ID<br>Printer default | X'00'–X'7F'<br>X'FF' | ❓ |
| IPDS-4-534 | 41–42 | CODE | Color | See byte description<br>X'FFFF' | Text color<br>Printer default | X'FF07' | ❓ |
| IPDS-4-535 | End of initial text-major conditions** | ❓ |
| IPDS-4-536 | 43 to end of LPD | Triplets | | | Zero or more optional LPD triplets; not all IPDS printers support LPD triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-4-537 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. | ❓ |
| IPDS-4-538 | support for these triplets is indicated by property pairs returned in the Device-Control | ❓ |
| IPDS-4-539 | Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the LPD command. | ❓ |
| IPDS-4-540 | Some IPDS printers interpret the LPP offset values when each LPP command is processed, using the | ❓ |
| IPDS-4-541 | Some IPDS printers interpret the LPP offset values when each BP command is processed, using the | ❓ |
| IPDS-4-542 | Partition 1 Partition 2 | ❓ |
| IPDS-4-543 | The format of the data field for the LPP command is as follows: | ❓ |
| IPDS-4-544 | 0 | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-545 | 1–3 | SBIN | $X_m$ page offset | X'FF8000'–X'007FFF' | $X_m$ offset for the logical page origin specified in L-units | X'000000'–X'001555' (Refer to the note following the table.) | ❓ |
| IPDS-4-546 | 4 | CODE | Placement | X'00'<br>X'10'<br>X'11'<br>X'20'<br>X'21'<br>X'30'<br>X'31'<br>X'40'<br>X'41' | Page placement:<br>Default placement<br>Partition 1, front side<br>Partition 1, back side<br>Partition 2, front side<br>Partition 2, back side<br>Partition 3, front side<br>Partition 3, back side<br>Partition 4, front side<br>Partition 4, back side | X'00' | ❓ |
| IPDS-4-547 | 5–7 | SBIN | $Y_m$ page offset | X'FF8000'–X'007FFF' | $Y_m$ offset for the logical page origin specified in L-units | X'000000'–X'001555' (Refer to the note following the table.) | ❓ |
| IPDS-4-548 | 8–9 | CODE | Orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Page orientation:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-4-549 | the printer supports the full range of logical-page-offset values. | ❓ |
| IPDS-4-550 | orientation value is specified. | ❓ |
| IPDS-4-551 | The length of the MID command can be: | ❓ |
| IPDS-4-552 | Without CID | X'0006' | ❓ |
| IPDS-4-553 | With CID | X'0008' | ❓ |
| IPDS-4-554 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-555 | The format of the data field for this command is as follows: | ❓ |
| IPDS-4-556 | 0 | CODE | Type | X'00'<br>X'01' | Start IPDS dialog<br>End IPDS dialog | X'00'<br>X'01' | ❓ |
| IPDS-4-557 | exception ID X'025B..01' exists. | ❓ |
| IPDS-4-558 | The length of the NOP command can be: | ❓ |
| IPDS-4-559 | Without CID | X'0005'–X'7FFF' | ❓ |
| IPDS-4-560 | With CID | X'0007'–X'7FFF' | ❓ |
| IPDS-4-561 | are ignored. This command is valid in any printer state. | ❓ |
| IPDS-4-562 | The length of the PFC command can be: | ❓ |
| IPDS-4-563 | Without CID | X'0009' or X'000B'–X'7FFF' | ❓ |
| IPDS-4-564 | With CID | X'000B' or X'000D'–X'7FFF' | ❓ |
| IPDS-4-565 | invalid or unsupported. | ❓ |
| IPDS-4-566 | The format of the data field for this command is as follows: | ❓ |
| IPDS-4-567 | 0 | X'00' | Reserved | X'00' | Reserved | X'00' | ❓ |
| IPDS-4-568 | 1 | BITS | Fidelity control flags | | bit 0: Activate<br>B'0' Reset to default fidelity controls and activate PFC triplets<br>B'1' Just activate PFC triplets<br>bits 1–7: Reserved (B'0000000') | B'0'<br>B'1' | ❓ |
| IPDS-4-569 | 2–3 | X'0000' | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-570 | 4 to end of PFC | | Triplets | | Zero or more optional PFC triplets:<br>X'74' Toner Saver triplet<br>X'75' Color Fidelity triplet<br>X'86' Text Fidelity triplet<br>X'88' Finishing Fidelity triplet<br>X'96' CMR Tag Fidelity triplet | ❓ |
| IPDS-4-571 | Bytes 2–3 Reserved** | ❓ |
| IPDS-4-572 | “CMR Tag Fidelity (X'96') Triplet” | ❓ |
| IPDS-4-573 | The RPO command can be used to rasterize any of the following types of presentation object: | ❓ |
| IPDS-4-574 | Table 26. RPO Object-Type IDs** | ❓ |
| IPDS-4-575 | EPS | Encapsulated PostScript with transparency | ❓ |
| IPDS-4-576 | EPS | Encapsulated PostScript without transparency | ❓ |
| IPDS-4-577 | GIF | Graphics Interchange Format | ❓ |
| IPDS-4-578 | IOCA | Image Object Content Architecture image | ❓ |
| IPDS-4-579 | JPEG | Joint Photographic Experts Group (AFPC JPEG Subset) | ❓ |
| IPDS-4-580 | JP2 | JPEG2000 File Format | ❓ |
| IPDS-4-581 | Overlay | IPDS Overlay | ❓ |
| IPDS-4-582 | PCL | Printer Command Language page object | ❓ |
| IPDS-4-583 | PDF | Portable Document Format multiple-page file with transparency | ❓ |
| IPDS-4-584 | PDF | Portable Document Format multiple-page file without transparency | ❓ |
| IPDS-4-585 | PDF | Portable Document Format single page with transparency | ❓ |
| IPDS-4-586 | PDF | Portable Document Format single page without transparency | ❓ |
| IPDS-4-587 | PNG | Portable Network Graphics (AFPC PNG Subset) | ❓ |
| IPDS-4-588 | SVG | Scalable Vector Graphics (AFPC SVG Subset) | ❓ |
| IPDS-4-589 | TIFF | Tag Image File Format (AFPC TIFF Subset) | ❓ |
| IPDS-4-590 | TIFF | Tag Image File Format with transparency | ❓ |
| IPDS-4-591 | TIFF | Tag Image File Format without transparency | ❓ |
| IPDS-4-592 | TIFF | Tag Image File Format multiple-image file with transparency | ❓ |
| IPDS-4-593 | TIFF | Tag Image File Format multiple-image file without transparency | ❓ |
| IPDS-4-594 | before issuing the RPO command. | ❓ |
| IPDS-4-595 | Fidelity information (toner saver, color fidelity control) | ❓ |
| IPDS-4-596 | CMRs and rendering intent | ❓ |
| IPDS-4-597 | Device appearance | ❓ |
| IPDS-4-598 | Orientation of the object after taking into consideration media orientation, page or overlay orientation, media source, cut-sheet emulation, sheet side, duplex setting, N-up, paper-feed direction, finishing-operation induced rotation, and the orientation specified in the IO or IDO command | ❓ |
| IPDS-4-599 | RPO and EHC reporting flags | ❓ |
| IPDS-4-600 | Mapping control option | ❓ |
| IPDS-4-601 | Side-sensitive attributes; some attributes, such as highlight color values, can be used on one side of a sheet but not on the other side. For example, highlight color is sometimes provided by a post-processor that can print only on one side. | ❓ |
| IPDS-4-602 | Image Resolution (X'9A') triplet (if the triplet was specified on the IDO command) | ❓ |
| IPDS-4-603 | For multi-page resource objects, Object Offset (X'5A') triplet | ❓ |
| IPDS-4-604 | Object Container Presentation Space Size (X'9C') triplet (if the triplet will be specified on the IDO command) | ❓ |
| IPDS-4-605 | Color Specification (X'4E') triplet (if the triplet was specified on the IDO-DODD command). This triplet is used for bilevel IO-Image objects and for object-container objects that contain bilevel or grayscale image, and is ignored for all other object types. The specific object types that can contain bilevel and grayscale image are identified in the MO:DCA object-type OID registry. | ❓ |
| IPDS-4-606 | IO) and each time the object is selected in an RPO command (but is already in the cache). | ❓ |
| IPDS-4-607 | The data-object-resource override parameters (bytes 7 to end) are not needed for an overlay resource; if | ❓ |
| IPDS-4-608 | The preprocessed and cached version of an overlay might not be used at include time if any portion of the | ❓ |
| IPDS-4-609 | An overlay that has been preRIPped with the RPO command can be used as a preprinted form overlay | ❓ |
| IPDS-4-610 | If the mapping control is scale to fit or scale to fill, the object is preprocessed into the object area. | ❓ |
| IPDS-4-611 | - If both $X_{oa}$ extent and $Y_{oa}$ extent values are specified in the RPO command, the preprocessed object is scaled using the object area size specified in the RPO command. | ❓ |
| IPDS-4-612 | - If either of the extent values is not specified in the RPO command, the extent is obtained from the object itself. However, if the optional output control self-defining field is not found in the object, the presentation space size of the object is used. | ❓ |
| IPDS-4-613 | If the mapping control is center-and-trim, the object is first preprocessed into the object presentation space. | ❓ |
| IPDS-4-614 | - If both $X_{oa}$ extent and $Y_{oa}$ extent values are specified in the RPO command, the preprocessed object is then trimmed to fit into the object area. If a subsequent IDO command selects center-and-trim, one of the preprocessed orientations, and the same object area size, the cached version of the object can be used. | ❓ |
| IPDS-4-615 | - If either of the extent values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. | ❓ |
| IPDS-4-616 | If the mapping control is position-and-trim, the object is first preprocessed into the object presentation space. | ❓ |
| IPDS-4-617 | - If $X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, and $Y_{oa}$ offset values are specified in the RPO command, the preprocessed presentation space is positioned in the RPO-specified object area, trimmed to fit the object area, and cached. If a subsequent IDO command selects position-and-trim, one of the preprocessed orientations, and the same object area size, the cached version of the object can be used. | ❓ |
| IPDS-4-618 | - If any one of the four object area values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. | ❓ |
| IPDS-4-619 | If the mapping control is position, the object is first preprocessed into the object presentation space. Note that the position mapping option is not valid for an IO Image. | ❓ |
| IPDS-4-620 | - If $X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, and $Y_{oa}$ offset values are specified in the RPO command, the preprocessed presentation space is positioned in the RPO-specified object area, and as long as there is no overflow of the object area, is cached. If there is an overflow, no caching occurs. If a subsequent IDO command selects the position mapping option, one of the preprocessed orientations, and the same offset, the cached version of the object can be used. | ❓ |
| IPDS-4-621 | - If any one of the four object area values is not specified in the RPO command, the preprocessed object is cached at its presentation space size. If a subsequent IDO command selects position, position-and-trim, or center-and-trim, and selects one of the preprocessed orientations, the cached version of the object can be used. If the IDO command selects an object area size and mapping option that requires trimming, the trimming is done at include time with a potential performance penalty. For the position mapping, the cached object presentation space must fit within the object area, or the cached object cannot be used. | ❓ |
| IPDS-4-622 | If the mapping control is one of the migration mapping options (point-to-pel, point-to-pel-with-double-dot, | ❓ |
| IPDS-4-623 | Color mapping tables are not used when the RPO command is processed. When a non-reset color mapping | ❓ |
| IPDS-4-624 | When a Color Specification (X'4E') triplet is specified in an IDO-DODD command to override bilevel and | ❓ |
| IPDS-4-625 | might not be able to use any previously cached variations of this object. | ❓ |
| IPDS-4-626 | When an image-migration mapping control is specified in an IDO command for an IO Image, the printer might | ❓ |
| IPDS-4-627 | If a synchronous exception is detected while rasterizing an object for an RPO command and if the exception | ❓ |
| IPDS-4-628 | Some printers support finishing operations, such as edge stitching and corner stapling, that are allowed on | ❓ |
| IPDS-4-629 | Text suppressions within an overlay are not activated during RPO processing and therefore the overlay is | ❓ |
| IPDS-4-630 | The XOA-PQC command is ignored during RPO processing and the printer's default print-quality level is | ❓ |
| IPDS-4-631 | The format of the RPO data is as follows: | ❓ |
| IPDS-4-632 | Table 25. RPO Entry Syntax** | ❓ |
| IPDS-4-633 | 0–1 | UBIN | Length | X'0007' or<br>X'0013' to end of entry | Length of RPO entry, including this length field | X'0007' or<br>X'0013' to end of entry | ❓ |
| IPDS-4-634 | 2 | CODE | RT | X'05'<br>X'40' | Resource type:<br>Overlay<br>Data Object Resource | X'05'<br>X'40' | ❓ |
| IPDS-4-635 | 3–4 | CODE | HAID | X'0001'–X'7EFF' | Presentation object's Host-Assigned ID | X'0001'–X'7EFF' | ❓ |
| IPDS-4-636 | Desired presentation environment** | ❓ |
| IPDS-4-637 | 5 | BITS | Exception reporting flags | | bit 0: Undefined character<br>bit 1: Page position<br>bits 2–5: Reserved (B'0000')<br>bit 6: Highlight<br>bit 7: Others | ❓ |
| IPDS-4-638 | 6 | BITS | Additional processing information flags | | bit 0: 0 degrees (Orient 0 degrees)<br>bit 1: 90 degrees (Orient 90 degrees)<br>bit 2: 180 degrees (Orient 180 degrees)<br>bit 3: 270 degrees (Orient 270 degrees)<br>bit 4: All objects (Process all objects of a multi-page resource file)<br>bits 5–7: Reserved (B'000') | ❓ |
| IPDS-4-639 | Data-object-resource override parameters** | ❓ |
| IPDS-4-640 | 7 | CODE | Unit base | X'00'<br>X'01'<br>X'FF' | Ten inches<br>Ten centimeters<br>Not specified | X'00'<br>X'FF' | ❓ |
| IPDS-4-641 | 8–9 | UBIN | UPUB | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ and $Y_{oa}$ units per unit base<br>Not specified | X'3840'<br>X'FFFF' | ❓ |
| IPDS-4-642 | 10–11 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ extent of object area in L-units<br>Not specified | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-4-643 | 12–13 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ extent of object area in L-units<br>Not specified | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-4-644 | 14 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'60'<br>X'FF' | Override for mapping control option:<br>Position (not valid for IO Image)<br>Scale to fit<br>Center and trim<br>Position and trim<br>Scale to fill<br>Not specified | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'FF' | ❓ |
| IPDS-4-645 | 15–16 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ offset in L-units; (for the position-and-trim and position mappings only)<br>Not specified | X'0000'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-4-646 | 17–18 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ offset in L-units; (for the position-and-trim and position mappings only)<br>Not specified | X'0000'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-4-647 | 19 to end of entry | | Triplets | | Zero or more triplets:<br>X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet | ❓ |
| IPDS-4-648 | command. Valid resources for the RPO command are listed in Table 26. | ❓ |
| IPDS-4-649 | Undefined-character checks (applies only to overlays) | ❓ |
| IPDS-4-650 | Page-position checks (applies only to overlays) | ❓ |
| IPDS-4-651 | All other exceptions with AEAs (applies to overlays and data object resources) | ❓ |
| IPDS-4-652 | This 4-bit field specifies one or more desired orientations, measured in a clockwise direction, of the $X$ axis of the object with respect to the leading edge of the media. Each bit that is set to B'1' causes a variation of the object to be rasterized and cached. For example, X'A0' yields a 0 degree variation and a 180 degree variation. If all 4 flags are B'0', this entry is processed at 0 degrees. | ❓ |
| IPDS-4-653 | Table 27. RPO Orientation Flags** | ❓ |
| IPDS-4-654 | bit 0 | 0 degrees | ❓ |
| IPDS-4-655 | bit 1 | 90 degrees | ❓ |
| IPDS-4-656 | bit 2 | 180 degrees | ❓ |
| IPDS-4-657 | bit 3 | 270 degrees | ❓ |
| IPDS-4-658 | 13 and bytes 15–18 and these bytes are also treated as unspecified. | ❓ |
| IPDS-4-659 | These fields specify overrides for the $X_{oa}$ and $Y_{oa}$ extents of the object area in L-units using the units of measure specified in bytes 7–9. X'FFFF' is a special value that indicates that the parameter has not been specified. For these parameters to be used, both the $X_{oa}$ extent and the $Y_{oa}$ extent must be specified in the RPO entry. | ❓ |
| IPDS-4-660 | Table 28. RPO Object-Area Size Overrides** | ❓ |
| IPDS-4-661 | 10–11 | $X_{oa}$ extent | X'0001'–X'7FFF' | Override for $X_{oa}$ extent of object area | ❓ |
| IPDS-4-662 | X'FFFF' | Not specified | ❓ |
| IPDS-4-663 | 12–13 | $Y_{oa}$ extent | X'0001'–X'7FFF' | Override for $Y_{oa}$ extent of object area | ❓ |
| IPDS-4-664 | X'FFFF' | Not specified | ❓ |
| IPDS-4-665 | This field specifies an override for the mapping control option that selects how the object's presentation space is mapped to the output area. Resolution correction occurs whenever the resolution of the object is different in one or both dimensions from the device resolution. X'FF' is a special value that indicates that this parameter has not been specified. | ❓ |
| IPDS-4-666 | Table 29. RPO Mapping-Control Overrides** | ❓ |
| IPDS-4-667 | X'00' | Position (not valid for IO Image) | ❓ |
| IPDS-4-668 | X'10' | Scale to fit | ❓ |
| IPDS-4-669 | X'20' | Center and trim | ❓ |
| IPDS-4-670 | X'30' | Position and trim | ❓ |
| IPDS-4-671 | X'60' | Scale to fill | ❓ |
| IPDS-4-672 | X'FF' | Not specified | ❓ |
| IPDS-4-673 | These fields specify overrides in L-units for the $X_{oa}$ and $Y_{oa}$ offsets from the object area origin. The units of measure used to interpret these offsets are specified in bytes 7–9. The offset fields are ignored when the actual mapping option used is not position or position and trim. X'FFFF' is a special value that indicates that this parameter has not been specified; an offset of -1 cannot be specified. For these parameters to be used, all four of the object area parameters ($X_{oa}$ extent, $Y_{oa}$ extent, $X_{oa}$ offset, $Y_{oa}$ offset) must be specified in the RPO entry. | ❓ |
| IPDS-4-674 | 15–16 | $X_{oa}$ offset | X'8000'–X'7FFF' | Override for $X_{oa}$ offset | ❓ |
| IPDS-4-675 | X'FFFF' | Not specified | ❓ |
| IPDS-4-676 | 17–18 | $Y_{oa}$ offset | X'8000'–X'7FFF' | Override for $Y_{oa}$ offset | ❓ |
| IPDS-4-677 | X'FFFF' | Not specified | ❓ |
| IPDS-4-678 | For bilevel IO-Image objects: | ❓ |
| IPDS-4-679 | - Printers that support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use the Color | ❓ |
| IPDS-4-680 | - Printers that do not support the Set Extended Bilevel Image Color (X'F4') IOCA self-defining field use a | ❓ |
| IPDS-4-681 | this case, the triplet must specify the Standard OCA color space (X'40') or the triplet is ignored. | ❓ |
| IPDS-4-682 | The Color Specification (X'4E') triplet is not used with grayscale IO-Image objects. | ❓ |
| IPDS-4-683 | For object-container objects that contain bilevel image but do not specify an internal color value, the Color | ❓ |
| IPDS-4-684 | For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a | ❓ |
| IPDS-4-685 | them on the RPO command; all X'92' and X'95' triplets specified on an RPO command for an overlay are | ❓ |
| IPDS-4-686 | 35 for a description of the hierarchy. | ❓ |
| IPDS-4-687 | Presentation Space Size (X'9C') triplet for an SVG object. | ❓ |
| IPDS-4-688 | were found at the end of the preceding instance of that command-set vector. | ❓ |
| IPDS-4-689 | The following table shows the STM reply format contained in the special data area of the Acknowledge Reply: | ❓ |
| IPDS-4-690 | 0 | CODE | | X'FF' | System/370 convention | ❓ |
| IPDS-4-691 | 1–2 | CODE | Type | | Device type of the printer, or of the printer that is being emulated or mimicked. For example, X'3820' for the 3820 page printer. | ❓ |
| IPDS-4-692 | 3 | CODE | Model | | Model number; see your printer documentation | ❓ |
| IPDS-4-693 | 4–5 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-694 | 6–7 | UBIN | Length | | Length of the command-set vector, including this length field | ❓ |
| IPDS-4-695 | 8–9 | CODE | Subset ID or command set ID | | For data command sets, the subset ID of a command set. For other command sets, the command set ID. | ❓ |
| IPDS-4-696 | 10–11 | CODE | Level or subset ID | | For data command sets, the level ID of a data tower. For other command sets, the subset ID of a command set. | ❓ |
| IPDS-4-697 | 12 to end | CODE | Property pairs | | Zero or more command-set property ID and data pairs. | ❓ |
| IPDS-4-698 | The following command-set vectors can be returned in the STM Acknowledge Reply: | ❓ |
| IPDS-4-699 | 6–7 | UBIN | Length | X'0006' to end of vector (even values) | Length of the Device-Control command-set vector, including this length field | ❓ |
| IPDS-4-700 | 8–9 | CODE | Command set ID | X'C4C3' | Device-Control command-set ID | ❓ |
| IPDS-4-701 | 10–11 | CODE | Subset ID | X'FF10' | DC1 subset ID | ❓ |
| IPDS-4-702 | 12 to end of vector | CODE | Command specific property pairs | X'6001'<br>X'6002'<br>X'6003'<br>X'6004'<br>X'6005'<br>X'6006'<br>X'6007'<br>X'6008'<br>X'6009'<br>X'6101'<br>X'6201' | Multiple copy & copy-subgroup support in LCC<br>Media-source-selection support in LCC; see note 1<br>Media-destination-selection support in LCC; see note 1<br>Full-length LCC commands supported; see note 2<br>Full range of font local IDs supported in LFE and LPD commands; see note 3<br>Font-modification flags supported in LFE commands (byte 14, bits 3–7)<br>Short LPD commands supported; see note 4<br>Full range of logical-page-offset values supported in LPP commands; see note 5<br>Empty LFE commands supported; see note 6<br>Explicit page placement and orientation support in the LPP command<br>Logical page and object area coloring support; see note 7 | ❓ |
| IPDS-4-703 | Offset | Type | Name | Range | Meaning | ❓ |
| IPDS-4-704 | Optional command property pairs | X'7001'<br>X'7002'<br>X'7008'<br>X'700A'<br>X'701C'<br>X'702E'<br>X'7034'<br>X'706B'<br>X'707B'<br>X'707E'<br>X'70CE' | Manage IPDS Dialog (MID) command support<br>Apply Finishing Operations (AFO) command<br>Set Presentation Environment (SPE) command support; see note 8<br>Activate Setup Name (ASN) command support; see note 9<br>Retired item 134<br>Activate Resource command support: indicates that the printer supports the AR command as well as XOA-RRL queries of query type X'05', activation query<br>Presentation Fidelity Control command support<br>Invoke CMR (ICMR) command support; see note 8<br>Rasterize Presentation Object (RPO) command support; see note 10<br>Include Saved Page (ISP) command support<br>DUA command-support property ID. If the DUA command and the Overlay command set is supported, secure overlays are also supported in the IO command. | ❓ |
| IPDS-4-705 | XOA property pairs | X'8000'<br>X'8001'<br>X'8002'<br>X'8006'<br>X'8008'<br>X'800A'<br>X'800C'<br>X'8010'<br>X'80F1'<br>X'80F2'<br>X'80F3'<br>X'80F4'<br>X'80F5'<br>X'80F6'<br>X'80F8'<br>X'80F9'<br>X'80FA' | Retired item 108<br>Retired item 109<br>Retired item 110<br>Retired item 111<br>Mark Form<br>Alternate Offset Stacker<br>Control Edge Marks<br>Activate Printer Alarm<br>Retired item 112<br>Discard Buffered Data; see note 11<br>Retired item 113<br>Request Resource List; see note 11<br>Discard Unstacked Pages<br>Exception-Handling Control; see note 11<br>Print-Quality Control<br>Obtain Additional Exception Information<br>Request Setup Name List; see note 9 | ❓ |
| IPDS-4-706 | XOH property pairs | X'9000'<br>X'9001'<br>X'9002'<br>X'9003'<br>X'9004'<br>X'9005'<br>X'9007'<br>X'9009'<br>X'900A'<br>X'900B'<br>X'900D'<br>X'900E'<br>X'9013'<br>X'9015'<br>X'9016'<br>X'9017'<br>X'90D0'<br>X'90F2'<br>X'90F3'<br>X'90F4'<br>X'90F5' | Retired item 114<br>Print Buffered Data; see note 11<br>Deactivate Saved Page Group<br>Specify Group Operation<br>Define Group Boundary<br>Erase Residual Print Data<br>Erase Residual Font Data<br>Separate Continuous Forms<br>Remove Saved Page Group<br>Retired item 115<br>Stack Received Pages<br>Select Medium Modifications<br>Eject to Front Facing<br>Select Input Media Source<br>Set Media Origin<br>Set Media Size<br>Retired item 126<br>Trace<br>Obtain Printer Characteristics; see note 11<br>Retired item 116<br>Page Counters Control | ❓ |
| IPDS-4-707 | CMR property pairs | X'E000'<br>X'E001'<br>X'E002'<br>X'E003'<br>X'E004'<br>X'E006'<br>X'E100'<br>X'E102' | CMRs can be captured<br>Host-activated link color-conversion (subset “LK”) CMRs supported; see note 12<br>Host-activated, non-generic halftone CMRs supported; see note 13<br>Host-activated, non-generic tone-transfer-curve CMRs supported; see note 14<br>Host-activated indexed CMRs supported; see note 15<br>Host-activated ICC DeviceLink (subset “DL”) CMRs supported; see note 12<br>CMRs can be reliably applied to all EPS/PDF objects; see note 16<br>Pass-through audit color-conversion CMRs supported | ❓ |
| IPDS-4-708 | Miscellaneous property pairs | X'F001'<br>X'F002'<br>X'F003'<br>X'F004'<br>X'F005'<br>X'F100' | End Persistent NACK without leaving IPDS mode; see note 17<br>Blank sheets are emitted when paper movement is stopped; see note 18<br>Long ACK support (up to 65,535 byte long Acknowledge Replies)<br>Grayscale simulation supported; see note 19<br>Grayscale simulation supported for device-default-monochrome device appearance; see note 20<br>An IPDS intermediate device is present. An instance of this property pair must be generated by each intermediate device in the configuration. | ❓ |
| IPDS-4-709 | X'F101'<br>X'F102'<br>X'F200'<br>X'F201'<br>X'F202'<br>X'F203'<br>X'F204'<br>X'F205'<br>X'F206'<br>X'F209'<br>X'F211'<br>X'F212'<br>X'F401'<br>X'F402'<br>X'F403'<br>X'F601'<br>X'F602'<br>X'F603'<br>X'F604'<br>X'F605'<br>X'F7nn'<br>X'F8nn'<br>X'F902' | UP3I finishing supported; see note 21<br>Media feed direction returned in the XOH-OPC reply Printable-Area self-defining field; see note 22<br>Local Date and Time Stamp (X'62') triplets supported in AR commands<br>Activation-failed NACK support<br>Font Resolution and Metric Technology (X'84') triplets supported in AR commands<br>Metric Adjustment (X'79') triplets supported in AR commands<br>Data-object font support; see note 23<br>Color Management triplet support in IDO, LPD, RPO, SPE, WBCC, WGC, WIC2, WOCC, and WTC commands; see note 8<br>Device Appearance (X'97') triplet support; see note 8<br>Extended copy set number format supported in the Group Information (X'6E') triplet<br>Character-encoded object names in AR commands; see note 24<br>QR Code with Image tertiary resource support; see note 25<br>XOA-RRL Multiple Entry Query Support; the printer supports multiple-entry queries of query type X'05', activation query<br>Retired (for “XOA-RRL query type X'FE' supported”)<br>Detailed settings support in XOA RSNL<br>Position-Check Highlighting Support in XOA EHC<br>Independent Exception Page-Print in XOA EHC; see note 26<br>Support for operator-directed recovery in XOA EHC; see note 27<br>Support for Page-Continuation Actions; see note 28<br>Support for Skip-and-Continue Actions; see note 28<br>Simplex N-up supported in the LCC command; see note 29<br>Simplex and duplex N-up supported in the LCC command; see note 29<br>Basic cut-sheet emulation mode supported; see note 30 | ❓ |
| IPDS-4-710 | X'FA00'<br>X'FB00'<br>X'FC00'<br>X'FC01'<br>X'FF01'<br>X'FF02'<br>X'FF03' | XOH PCC X'02' counter update support; property pair X'90F5' must also be specified<br>All architected units of measure supported; see note 31<br>All function listed for IS/3 is supported; see description<br>All function listed for MO:DCA GA is supported; see “Additional required support for the MO:DCA GA (Graphic Arts) Function Set”; property pair X'FC00' must also be specified<br>Positioning Exception Sense Format Supported. Presence indicates support for Sense Format 1. Absence indicates support for Sense Format 7.<br>Three-Byte Sense Data Support; see note 32<br>Internal rendering intent support in XOH Trace; see note 33 | ❓ |
| IPDS-4-711 | 1. Printers that support either X'6002' (media-source selection in LCC) or X'6003' (media-destination | ❓ |
| IPDS-4-712 | 2. Property pair X'6004' indicates that the printer supports as many LCC copy subgroups and keywords as | ❓ |
| IPDS-4-713 | 3. Property pair X'6005' indicates support for font local IDs in the range X'80'–X'FE' in LFE and LPD | ❓ |
| IPDS-4-714 | 4. Property pair X'6007' indicates that the printer will accept short LPD commands that do not provide all of | ❓ |
| IPDS-4-715 | 5. Property pair X'6008' indicates that the printer supports the full range of logical-page-offset values | ❓ |
| IPDS-4-716 | 6. Property pair X'6009' indicates that the printer supports empty LFE commands, which can be used to reset | ❓ |
| IPDS-4-717 | 7. Logical page and object area coloring includes support for the Color Specification (X'4E') triplet and the | ❓ |
| IPDS-4-718 | 8. Property pair X'F205' is the basic property pair to identify support for color management (as defined in the | ❓ |
| IPDS-4-719 | Property pair X'7008' (SPE command support) in the STM Device-Control command-set vector. | ❓ |
| IPDS-4-720 | Property pair X'706B' (ICMR command support) in the STM Device-Control command-set vector. | ❓ |
| IPDS-4-721 | Property pair X'F205' (Color Management triplet support) in the STM Device-Control command-set | ❓ |
| IPDS-4-722 | vector, including support for the Color Management Resource Descriptor (X'91') triplet, the Invoke CMR | ❓ |
| IPDS-4-723 | Property pair X'F206' (Device Appearance (X'97') triplet support) in the STM Device-Control command- | ❓ |
| IPDS-4-724 | Property pair X'1201' (Data-object-resource support) in the STM Object Container command-set vector. | ❓ |
| IPDS-4-725 | The CMR object-type OID in the home state list of the XOH-OPC Object-Container Type Support self- | ❓ |
| IPDS-4-726 | The XOH-OPC Product Identifier self-defining field with parameter ID X'0001'. | ❓ |
| IPDS-4-727 | 9. Property pairs X'700A' and X'80FA' must always be reported together and indicate support for the Activate | ❓ |
| IPDS-4-728 | 10. Property pair X'707B' indicates support for the RPO command and also indicates 1) that if the DORE | ❓ |
| IPDS-4-729 | 11. These property pairs are implied by support of this command set and therefore need not be returned by a | ❓ |
| IPDS-4-730 | 12. Presence of X'E001' indicates that the printer uses host-activated link color-conversion (subset “LK”) | ❓ |
| IPDS-4-731 | 13. Presence of X'E002' indicates that the printer uses host-activated halftone CMRs; absence of X'E002' | ❓ |
| IPDS-4-732 | exception ID can be controlled with the Color Fidelity (X'75') triplet in the PFC command. | ❓ |
| IPDS-4-733 | 14. Presence of X'E003' indicates that the printer uses host-activated tone-transfer-curve CMRs; absence of | ❓ |
| IPDS-4-734 | 15. Presence of X'E004' indicates that the printer uses host-activated indexed CMRs; absence of X'E004' | ❓ |
| IPDS-4-735 | 16. Presence of X'E100' indicates that the printer reliably applies CMRs to EPS and PDF page objects. | ❓ |
| IPDS-4-736 | 17. “End Persistent NACK without leaving IPDS mode” indicates that the printer stops persisting with a given | ❓ |
| IPDS-4-737 | 18. This printer cannot immediately stop paper movement on a sheet boundary and emits a few blank sheets | ❓ |
| IPDS-4-738 | 19. Property pair X'F004' indicates that the printer is using a single color for printing, but will simulate color | ❓ |
| IPDS-4-739 | 20. Property pair X'F005' indicates that when a full-color printer is using a single color for printing because an | ❓ |
| IPDS-4-740 | 21. Property pair X'F101' indicates that the UP | ❓ |
| IPDS-4-741 | Tupel self-defining fields (X'0019') and UP3I Paper Input Media self-defining fields (X'001A'). | ❓ |
| IPDS-4-742 | 22. Presence of X'F102' indicates that bit 10 of OPC bytes 22–23 provides the media-source feed direction | ❓ |
| IPDS-4-743 | 23. The data-object font support property pair (X'F204') indicates printer support for data-object fonts; this | ❓ |
| IPDS-4-744 | 24. Property pair X'F211' indicates that, when capturing data object resources and data-object-font | ❓ |
| IPDS-4-745 | Fully Qualified Name (X'02') triplet (FQN type X'DE' with a character-encoded name) | ❓ |
| IPDS-4-746 | Coded Graphic Character Set Global Identifier (X'01') triplet | ❓ |
| IPDS-4-747 | 25. Presence of X'F212' indicates support for the QR Code with Image bar code type, for the Invoke Tertiary | ❓ |
| IPDS-4-748 | 26. If the printer allows the host to control printing or discarding of pages that contain a reported exception | ❓ |
| IPDS-4-749 | 7 description. | ❓ |
| IPDS-4-750 | 27. The operator-directed recovery function allows the host to control reporting or suppressing of certain | ❓ |
| IPDS-4-751 | 28. Property pairs X'F604' (Page-Continuation Actions) and X'F605' (Skip-and-Continue Actions) indicate the | ❓ |
| IPDS-4-752 | supported method of continuation after an exception condition when an AEA is not taken. Only one of | ❓ |
| IPDS-4-753 | 29. Absence of both the X'F7nn' and the X'F8nn' property pairs indicates that only one page can be placed on | ❓ |
| IPDS-4-754 | 30. Basic cut-sheet emulation mode provides support for the X'C300' keyword in the LCC command. Refer to | ❓ |
| IPDS-4-755 | 31. Presence of X'FB00' indicates that all architected units per unit base (X'0001'–X'7FFF') and all architected | ❓ |
| IPDS-4-756 | 32. A printer must return the “Three-Byte Sense Data Support” property pair if the printer returns three bytes of | ❓ |
| IPDS-4-757 | 33. Support for internal rendering intent in the XOH Trace command includes support for the X'FD' and X'FE' | ❓ |
| IPDS-4-758 | CMRs-Used trace entry. | ❓ |
| IPDS-4-759 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Text command-set vector, including this length field | ❓ |
| IPDS-4-760 | 8–9 | CODE | Subset ID | X'D7E3' | TX1 subset of the Text command set | ❓ |
| IPDS-4-761 | 10–11 | CODE | Level ID | X'FF10'<br>X'FF20'<br>X'FF30'<br>X'FF40' | PTOCA PT1 data<br>PTOCA PT2 data<br>PTOCA PT3 data<br>PTOCA PT4 data | ❓ |
| IPDS-4-762 | 12 to end of vector | CODE | Property pairs | X'1000'<br>X'1001'<br>X'2001'<br>X'2002'<br>X'40nn' | Optimum performance if text data is in an ordered page<br>Unordered text supported<br>Text object support; includes support for the WTC command<br>Full range of text suppression IDs supported in LCC and LE commands; see note 1<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bit 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for text data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”. This property overrides the precision parameter in all STC control sequences.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for text data<br>**Bit 7**: Multiple-color support. Multiple-color support for text data; see note 2. | ❓ |
| IPDS-4-763 | X'4303'<br>X'4304'<br>X'50nn' | Support for PTOCA glyph layout controls; see note 3<br>Support of encrypted text string control sequences; see note 4<br>Multiple text-orientation support for all supported media origins, where nn is a bit-mapped byte:<br>**Bit 0**: I = 270°, B = 180°<br>**Bit 1**: I = 180°, B = 90°<br>**Bit 2**: I = 90°, B = 0°<br>**Bit 3**: I = 0°, B = 270°<br>**Bit 4**: I = 270°, B = 0°<br>**Bit 5**: I = 180°, B = 270°<br>**Bit 6**: I = 90°, B = 180°<br>**Bit 7**: I = 0°, B = 90° | ❓ |
| IPDS-4-764 | X'A0nn' | WTC-TAP object area orientation support property ID (see notes 5 and 6), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-765 | 1. Property pair X'2002' indicates support for text suppression IDs in the range X'80'–X'FF' in the LCC | ❓ |
| IPDS-4-766 | 2. Multiple-color support for text data means that from the table that follows, color value X'FF07' (printer | ❓ |
| IPDS-4-767 | 3. Presence of property pair X'4303' indicates support for all of the glyph layout controls defined within | ❓ |
| IPDS-4-768 | 4. Presence of property pair X'4304' indicates that the implementation understands PTOCA encrypted text | ❓ |
| IPDS-4-769 | 5. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area | ❓ |
| IPDS-4-770 | Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-771 | Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-772 | Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-773 | Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-774 | 6. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum | ❓ |
| IPDS-4-775 | number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. | ❓ |
| IPDS-4-776 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the IM-Image command-set vector, including this length field | ❓ |
| IPDS-4-777 | 8–9 | CODE | Subset ID | X'C9D4' | IM1 subset of the IM-Image command set | ❓ |
| IPDS-4-778 | 10–11 | CODE | Level ID | X'FF10' | IMD1 data | ❓ |
| IPDS-4-779 | 12 to end of vector | CODE | Property pairs | X'1000'<br>X'1001'<br>X'40nn' | Optimum performance when IM Image is in an ordered page<br>IM-Image objects may be sent in any order<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for IM-Image data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for IM-Image data<br>**Bit 7**: Multiple-color support. Multiple-color support for IM-Image data; see note. | ❓ |
| IPDS-4-780 | X'A0nn' | Orientation-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree scan-line direction and 90 degree scan-line sequence direction supported in the WIC command<br>**Bit 1**: 90 degree scan-line direction and 180 degree scan-line sequence direction supported in the WIC command<br>**Bit 2**: 180 degree scan-line direction and 270 degree sequence direction supported in the WIC command<br>**Bit 3**: 270 degree scan-line direction and 0 degree scan-line sequence direction supported in the WIC command<br>**Bit 4**: Reserved<br>**Bit 5**: All four scan-line direction/scan-line sequence direction combinations supported in the WIC command<br>**Bits 6–7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-781 | X'FF07' Printer default | ❓ |
| IPDS-4-782 | returned (one for FS10 and one for FS45). | ❓ |
| IPDS-4-783 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the IO-Image command-set vector, including this length field | ❓ |
| IPDS-4-784 | 8–9 | CODE | Subset ID | X'C9D6' | IO1 subset of the IO-Image command set | ❓ |
| IPDS-4-785 | 10–11 | CODE | Level ID | X'FF10'<br>X'FF11'<br>X'FF14'<br>X'FF40'<br>X'FF42'<br>X'FF45'<br>X'FF48'<br>X'0010'<br>X'0011' | IOCA FS10 data; see note 1<br>IOCA FS11 data; implies FS10 also supported<br>IOCA FS14 data; implies FS11 also supported<br>IOCA FS40 data<br>IOCA FS42 data; implies FS40 also supported<br>IOCA FS45 data; implies FS42 also supported<br>IOCA FS48 data; implies FS45 also supported<br>Subset of IOCA FS10 data<br>Subset of IOCA FS11 data | ❓ |
| IPDS-4-786 | 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1202'<br>X'1206'<br>X'1208'<br>X'20nn'<br>X'30nn'<br>X'40nn' | IO-Image objects may be sent in any order<br>IO-Image objects can be downloaded in home state as resources<br>IO-Image support for LPD extents; see note 2<br>Negative object-area positioning; see note 3<br>Retired item 15<br>Retired item 16<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for bilevel IO Images are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support for bilevel IO Images. Color of medium (also known as reset color) supported for bilevel IO Images; this color has the effect of erasing any data that is underneath the significant image points.<br>**Bit 7**: Multiple-color support for bilevel IO Images. Multiple-color support for IO-Image data; see note 4. | ❓ |
| IPDS-4-787 | X'4401'<br>X'4402'<br>X'4403' | Extended IOCA bilevel color support; when this property pair is present, the printer supports Set Extended Bilevel Image Color (X'F4') IOCA self-defining field on the WIC2-IDD<br>Extended IOCA Tile-Set-Color support; see note 5<br>Bilevel IO-Image color support on the RPO command | ❓ |
| IPDS-4-788 | X'5001'<br>X'5003'<br>X'5006'<br>X'5008'<br>X'500A'<br>X'500D'<br>X'500E'<br>X'5020'<br>X'5080'<br>X'5081'<br>X'5082'<br>X'5083'<br>X'5084' | Compression algorithm-support property IDs; see note 1:<br>X'5001': Modified ITU-TSS Modified READ Algorithm (IBM MMR)<br>X'5003': Uncompressed image<br>X'5006': Run-Length 4 Compression Algorithm<br>X'5008': ABIC (bilevel Q-coder) Compression Algorithm (ABIC)<br>X'500A': Concatenated ABIC<br>X'500D': TIFF LZW<br>X'500E': TIFF LZW with Differencing Predictor<br>X'5020': Solid Fill Rectangle<br>X'5080': ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one dimensional)<br>X'5081': ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two dimensional)<br>X'5082': ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR)<br>X'5083': ISO/ITU-TSS JPEG algorithms<br>X'5084': JBIG2 Compression Algorithm | ❓ |
| IPDS-4-789 | X'5101'<br>X'5204'<br>X'5308'<br>X'5501'<br>X'5505'<br>X'5506' | Bit ordering supported in the IOCA Image Encoding Parameter<br>Unpadded RIDIC recording algorithm<br>IDE size = 8 supported<br>Transparency masks<br>Multiple image content support; see note 6<br>nColor Names parameter supported | ❓ |
| IPDS-4-790 | X'A0nn' | WIC2–IAP object area orientation support property ID (see notes 7 and 8); where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported; see note 9.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-791 | X'F300'<br>X'F301' | Replicate-and-trim mapping supported; see note 10.<br>Scale-to-fill mapping supported | ❓ |
| IPDS-4-792 | 1. When the level ID is X'0010', all of the first level (IOCA FS10) is supported except for some of the | ❓ |
| IPDS-4-793 | Compression algorithms | ❓ |
| IPDS-4-794 | Bit ordering | ❓ |
| IPDS-4-795 | Grayscale (IDE size = 4 or 8) | ❓ |
| IPDS-4-796 | Process color (IDE size = 24) | ❓ |
| IPDS-4-797 | 2. Property pair X'1206' indicates that the value X'FFFF' (use LPD value) is supported within page segments | ❓ |
| IPDS-4-798 | 3. Property pair X'1208' indicates support for negative object-area-offset values in WIC2-IOC self-defining | ❓ |
| IPDS-4-799 | 4. Multiple-color support for bilevel IO Images indicates that from the table that follows, color value X'FF07' | ❓ |
| IPDS-4-800 | 5. Property pair X'4402' indicates that the printer supports the optional color spaces (RGB, highlight colors, | ❓ |
| IPDS-4-801 | 6. Property pair X'5505' indicates that the printer supports multiple image contents within an IOCA image | ❓ |
| IPDS-4-802 | pair, and one vector for FS45 that includes the X'5505' property pair. | ❓ |
| IPDS-4-803 | 7. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area | ❓ |
| IPDS-4-804 | Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-805 | Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-806 | Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-807 | Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-808 | 8. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum | ❓ |
| IPDS-4-809 | 9. Bit 6 of property pair X'A0nn' indicates support for all values of degrees and minutes for IO-Image object | ❓ |
| IPDS-4-810 | 10. Property pair X'F300' indicates that the printer supports the replicate-and-trim mapping option for FS10 | ❓ |
| IPDS-4-811 | be specified for any other IOCA function set. | ❓ |
| IPDS-4-812 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Graphics command-set vector, including this length field | ❓ |
| IPDS-4-813 | 8–9 | CODE | Subset ID | X'E5C7' | GR1 subset of the Graphics command set | ❓ |
| IPDS-4-814 | 10–11 | CODE | Level ID | X'FF20'<br>X'FF30' | GOCA DR/2V0 data<br>GOCA GRS3 data | ❓ |
| IPDS-4-815 | 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1207'<br>X'1208'<br>X'40nn' | Graphics objects may be sent in any order<br>Support for GOCA image resolution in the WGC-GDD<br>Negative object-area positioning; see note 1<br>Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: Reserved<br>**Bit 1**: Simulated area fill color support as an AEA. Simulated color support for all valid but unsupported color values as an AEA for graphics area fill. The simulation technique is device dependent and generates distinguishable simulated colors. This bit and bit 2 cannot both be set to B'1'. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for graphics data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. This bit and bit 1 cannot both be set to B'1'. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3–5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for graphics data<br>**Bit 7**: Multiple-color support. Multiple-color support for graphics; see note 2 | ❓ |
| IPDS-4-816 | X'4100'<br>X'4101'<br>X'4102'<br>X'4106'<br>X'4107'<br>X'4108'<br>X'4109'<br>X'4110'<br>X'4111'<br>X'4112'<br>X'4113'<br>X'4114'<br>X'4115'<br>X'4116'<br>X'4117'<br>X'4130'<br>X'4131'<br>X'4132' | Set Process Color drawing order supported<br>Box drawing orders supported<br>Partial Arc drawing orders supported<br>Set Fractional Line Width drawing order supported (including support between Begin Area and End Area drawing orders and in segment prologs)<br>Cubic Bézier Curve drawing orders<br>Set default support in GDD for Normal Line Width<br>Set default support in GDD for Process Color<br>Set Line End drawing order supported<br>Set Line Join drawing order supported<br>Clockwise full and partial arcs supported<br>Nonzero Winding mode supported; see note 3<br>Clockwise boxes supported<br>Custom line types supported<br>Font positioning method used for GOCA character positioning; see note 4<br>Cell positioning method used for GOCA character positioning; see note 4<br>Custom patterns supported; see note 5<br>Gradients supported for area fill; see note 6<br>Marker size supported; see note 7 | ❓ |
| IPDS-4-817 | X'A0nn' | WGC–GAP object area orientation support property ID (see notes 8 and 9), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-818 | X'F300'<br>X'F301' | Retired item 135 (replicate-and-trim mapping)<br>Scale-to-fill mapping supported | ❓ |
| IPDS-4-819 | 1. Property pair X'1208' indicates support for negative object-area-offset values in WGC-GOC self-defining | ❓ |
| IPDS-4-820 | 2. Multiple-color support for graphics data means that from the table that follows, color value X'FF07' (printer | ❓ |
| IPDS-4-821 | X'0002' or X'FF02' Red | ❓ |
| IPDS-4-822 | 3. Property pair X'4113' indicates that the printer supports Nonzero Winding mode. Such printers must also | ❓ |
| IPDS-4-823 | 4. Property pairs X'4116' and X'4117' are mutually exclusive; only one of the two property pairs can be | ❓ |
| IPDS-4-824 | 5. Support for custom patterns (property pair X'4130') includes support for the Begin Custom Pattern, End | ❓ |
| IPDS-4-825 | 6. Support for gradients (property pair X'4131') includes support for the Linear Gradient, Radial Gradient, and | ❓ |
| IPDS-4-826 | 7. Support for marker size (property pair X'4132') includes: | ❓ |
| IPDS-4-827 | Varying the size of markers based on the marker cell-size attribute | ❓ |
| IPDS-4-828 | Not processing the Set Marker Cell (GSMC) drawing order as a No-Op | ❓ |
| IPDS-4-829 | Treating the marker precision attribute and Set Marker Precision (GSMP) drawing order as obsolete | ❓ |
| IPDS-4-830 | Following the recommendation for standard default marker cell-size | ❓ |
| IPDS-4-831 | 8. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area | ❓ |
| IPDS-4-832 | Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-833 | Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-834 | Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-835 | Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-836 | 9. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum | ❓ |
| IPDS-4-837 | number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. | ❓ |
| IPDS-4-838 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Bar Code command-set vector, including this length field | ❓ |
| IPDS-4-839 | 8–9 | CODE | Subset ID | X'C2C3' | BC1 subset of the Bar Code command set | ❓ |
| IPDS-4-840 | 10–11 | CODE | Level ID | X'FF10'<br>X'FF20' | BCOCA BCD1 data<br>BCOCA BCD2 data | ❓ |
| IPDS-4-841 | 12 to end of vector | CODE | Property pairs | X'1001'<br>X'1208'<br>X'1300'<br>X'1301'<br>X'1302'<br>X'1303'<br>X'1304'<br>X'1305'<br>X'1306'<br>X'1307' | Bar code objects may be sent in any order<br>Negative object-area positioning; see note 1<br>Small-symbol support; see note 2<br>Retired item 139<br>Desired-symbol-width parameter supported in the Bar Code Symbol Descriptor<br>Data Matrix encodation scheme support<br>Full range of font local IDs supported in WBCC-BCDD; see note 3<br>Support for bar code suppression; see note 4<br>Support for the too-much-data flag in the QR Code Special-Function Parameters<br>Support for the too-much-data flag in the Data Matrix Special-Function Parameters; see note 5 | ❓ |
| IPDS-4-842 | X'40nn' | Standard OCA color-support property ID, where nn is a bit-mapped byte:<br>**Bits 0–1**: Reserved<br>**Bit 2**: Limited simulated-color support. All valid but unsupported color values for bar code data are accepted and result in a device-dependent simulation of the specified color without the generation of unsupported color exceptions. Simulated colors need not be distinguishable. For a list of valid color values, see the “Standard OCA Color-Value Table”.<br>**Bits 3-5**: Reserved<br>**Bit 6**: Color of medium support. Color of medium (also known as reset color) supported for bar code data<br>**Bit 7**: Multiple-color support for bar code data; see note 6. | ❓ |
| IPDS-4-843 | X'4400'<br>X'A0nn' | Extended bar code color support; when this property pair is present, the printer supports the Color Specification (X'4E') triplet on the WBCC-BCDD.<br>WBCC–BCAP object area orientation support property ID (see note 7 and note 8), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I,B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-844 | 1. Property pair X'1208' indicates support for negative object-area-offset values in WBCC-BCOC self-defining | ❓ |
| IPDS-4-845 | 2. Printers that provide small-symbol support can produce the smallest bar code symbol defined for the fixed- | ❓ |
| IPDS-4-846 | 3. Property pair X'1304' indicates support for font local ID X'00' and font local IDs in the range X'80'–X'FE' in | ❓ |
| IPDS-4-847 | 4. Property pair X'1305' indicates support for the suppress-bar-code-symbol flag in the BCOCA BSA data | ❓ |
| IPDS-4-848 | 5. Property pair X'1307' is reported in two cases: | ❓ |
| IPDS-4-849 | The printer supports only Data Matrix bar codes with bar code modifier X'00' and supports the too-much- | ❓ |
| IPDS-4-850 | The printer supports Data Matrix bar codes with bar code modifiers X'00' and X'01', in which case | ❓ |
| IPDS-4-851 | 6. Multiple-color support for bar code data means that from the table that follows, color value X'FF07' (printer | ❓ |
| IPDS-4-852 | X'0002' or X'FF02' Red | ❓ |
| IPDS-4-853 | 7. Object area orientation with respect to the Xp,Yp coordinate system also implies a level of object area | ❓ |
| IPDS-4-854 | Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-855 | Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-856 | Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-857 | Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-858 | 8. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum | ❓ |
| IPDS-4-859 | 9. Printers that implement the common bar code types and modifiers that are listed in Table 34 | ❓ |
| IPDS-4-860 | appropriately encoded Common Bar Code Type/Modifier Self-Defining Field. | ❓ |
| IPDS-4-861 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Object Container command-set vector, including this length field | ❓ |
| IPDS-4-862 | 8–9 | CODE | Subset ID | X'D6C3' | OC1 subset of the Object Container command set | ❓ |
| IPDS-4-863 | 10–11 | CODE | Level ID | X'0000' | No levels defined | ❓ |
| IPDS-4-864 | 12 to end of vector | CODE | Property pairs | X'1201'<br>X'1203'<br>X'1204'<br>X'1205'<br>X'1208'<br>X'1209'<br>X'120A'<br>X'120B'<br>X'120D'<br>X'120E'<br>X'5800'<br>X'5801' | Data-object-resource support; see note 1<br>Object Container Presentation Space Size (X'9C') triplet supported for PDF objects in IDO, RPO, and WOCC commands<br>Remove Resident Resource (RRR) command support<br>Request Resident Resource List (RRRL) command support<br>Negative object-area positioning; see note 2<br>Object Container Presentation Space Size (X'9C') triplet supported for SVG objects in IDO, RPO, and WOCC commands<br>Extension entries supported in the DORE command; see note 3<br>Retired item 149 ; see note 4<br>TrueType/OpenType Fonts supported as secondary resources in the DORE2 command; see note 4<br>Data Object Resource Equivalence 2 (DORE2) command support; see note 5<br>Image Resolution (X'9A') triplet supported in IDO, RPO, and WOCC commands<br>Bilevel and grayscale image color support for object containers; see note 6 | ❓ |
| IPDS-4-865 | X'A0nn' | WOCC-OCAP and IDO-DOAP object area orientation support property ID (see notes 7, 8, and 9), where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 1**: 90 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 2**: 180 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 3**: 270 degree orientation supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system<br>**Bit 4**: Reserved<br>**Bit 5**: The four orientations 0, 90, 180, and 270 degrees are all supported in the WOCC-OCAP with respect to the $X_{p}, Y_{p}$ coordinate system. As a result, all four object area orientations are supported with respect to all supported I, B orientations.<br>**Bit 6**: All values of degrees and minutes for object area orientation are supported in both the WOCC-OCAP and the IDO-DOAP.<br>**Bit 7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-866 | X'F301' | Scale-to-fill mapping supported | ❓ |
| IPDS-4-867 | 1. Property pair X'1201' indicates support for data object resources and includes support for the DDOR, | ❓ |
| IPDS-4-868 | 2. Property pair X'1208' indicates support for negative object-area-offset values in IDO-DOOC and WOCC- | ❓ |
| IPDS-4-869 | 3. Property pair X'120A' indicates support for the HAID value X'E47D' in the DORE command, for all states in | ❓ |
| IPDS-4-870 | 4. Property pair X'120D' | ❓ |
| IPDS-4-871 | 5. Property pair X'120E' indicates support for the DORE2 command in page, page segment, and overlay | ❓ |
| IPDS-4-872 | 6. Property pair X'5801' indicates support for the Color Specification (X'4E') triplet in IDO-DODD, RPO, and | ❓ |
| IPDS-4-873 | 7. Object area orientation with respect to the X | ❓ |
| IPDS-4-874 | Bit 0: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-875 | Bit 1: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-876 | Bit 2: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-877 | Bit 3: An object area orientation of O oa is supported with respect to the I axis such that the sum (O oa + Oi) | ❓ |
| IPDS-4-878 | 8. Bits 0–3 and 5 report the object area orientation support for the WOCC-OCAP , since the only orientation | ❓ |
| IPDS-4-879 | 9. When setting the bits in the second byte of the X'A0nn' property pair, it is recommended that the minimum | ❓ |
| IPDS-4-880 | number of bits necessary be set to B'1'. For example, if bit 6 is set, bits 0–3 and 5 should not be set. | ❓ |
| IPDS-4-881 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Metadata command-set vector, including this length field | ❓ |
| IPDS-4-882 | 8–9 | CODE | Subset ID | X'D4C4' | MO1 subset of the Metadata command set | ❓ |
| IPDS-4-883 | 10–11 | CODE | Level ID | X'FF10' | MOCA MS1 data | ❓ |
| IPDS-4-884 | 12 to end of vector | CODE | Property pairs | X'D001' | Support for the AFP Tagging format | ❓ |
| IPDS-4-885 | Offset | Type | Name | Range | Meaning | ❓ |
| IPDS-4-886 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Overlay command-set vector, including this length field | ❓ |
| IPDS-4-887 | 8–9 | CODE | Command set ID | X'D6D3' | Overlay command-set ID | ❓ |
| IPDS-4-888 | 10–11 | CODE | Subset ID | X'FF10' | OL1 subset ID | ❓ |
| IPDS-4-889 | 12 to end of vector | CODE | Property pairs | X'1102'<br>X'15nn' | Extended overlay support; up to 32,511 overlays can be activated at one time.<br>Overlay nesting support; if not reported, two levels of nesting (X'02') are assumed; see note 1. Where nn is:<br>X'01': No overlay nesting is supported. Overlays may not include other overlays.<br>X'mm': Overlay nesting up to mm levels is supported. Valid values for mm are X'02'–X'FE'.<br>X'FF': 255 or more levels of overlay nesting supported. | ❓ |
| IPDS-4-890 | X'1600'<br>X'A004' | Preprinted form overlay support in LCC and IO commands<br>Page-overlay-rotation support; all 4 orientations supported in the IO command | ❓ |
| IPDS-4-891 | 1. For example, if X'1503' is returned, the host can invoke an overlay by means of an IO or LCC command | ❓ |
| IPDS-4-892 | 2. Support of overlay invocation via LCC is implied by support of OL1. | ❓ |
| IPDS-4-893 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Page-Segment command-set vector, including this length field | ❓ |
| IPDS-4-894 | 8–9 | CODE | Command set ID | X'D7E2' | Page-Segment command-set ID | ❓ |
| IPDS-4-895 | 10–11 | CODE | Subset ID | X'FF10' | PS1 subset ID | ❓ |
| IPDS-4-896 | 12 to end of vector | CODE | Property pairs | X'1101' | Extended page segment support; up to 32,511 page segments can be activated at one time. | ❓ |
| IPDS-4-897 | Offset | Type | Name | Range | Meaning | ❓ |
| IPDS-4-898 | 6–7 | UBIN | Length | X'0006' to end of vector | Length of the Loaded-Font command-set vector, including this length field | ❓ |
| IPDS-4-899 | 8–9 | CODE | Command set ID | X'C3C6' | Loaded-Font command-set ID | ❓ |
| IPDS-4-900 | 10–11 | CODE | Subset ID | X'FF10'<br>X'FF20'<br>X'FF30'<br>X'FF40' | LF1 subset ID; fully described font plus font index<br>LF2 subset ID; symbol set coded font<br>LF3 subset ID; code page plus font character set<br>LF4 subset ID; code page<br>There is no subset or superset relationship between the loaded-font subsets. | ❓ |
| IPDS-4-901 | 12 to end of vector | CODE | Property pairs | X'A0nn' | Orientation-support property ID, where nn is a bit-mapped byte:<br>**Bit 0**: 0 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 1**: 90 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 2**: 180 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 3**: 270 degree Font Inline Sequence supported for all supported text orientations in the LFI command<br>**Bit 4**: Reserved<br>**Bit 5**: All four Font Inline Sequences supported for all supported text orientations in the LFI command<br>**Bits 6–7**: Reserved<br>If no X'A0nn' property pair is specified, the default is X'A080'. | ❓ |
| IPDS-4-902 | X'B001'<br>X'B002'<br>X'B003'<br>X'B004'<br>X'B005'<br>X'C0nn'<br>X'C1nn' | Double-byte coded fonts and code pages<br>Underscore width and position parameters in the LFI command are used by printer<br>GRID-parts fields allowed in the LFC, LFCSC, and LCPC commands<br>Default character parameters supported in the LCPC command (implies that X'B003' is also supported)<br>Extended (Unicode mapping) code page support; see note 1.<br>Coded-font pattern-technology ID (see note 2), nn is:<br>X'05': Bounded-box raster-font technology<br>X'1E': CID-keyed outline-font technology<br>X'1F': Type 1 PFB outline-font technology | ❓ |
| IPDS-4-903 | 1. X'B005' is independent from X'B003' and X'B004'; as a couple of examples, a printer might return X'B005' | ❓ |
| IPDS-4-904 | 2. The range of values X'06'–X'FF' in property pair X'C0nn' has been set aside for outline-font pattern- | ❓ |
| IPDS-4-905 | 3. The LF1 command subset supports either fixed-metric technology or relative-metric technology (or both). | ❓ |
| IPDS-4-906 | The printer need not return the X'C1nn' property pair for LF2 or LF3 command subsets. | ❓ |
| IPDS-4-907 | The length of the SHS command can be: | ❓ |
| IPDS-4-908 | Without CID | X'0005' | ❓ |
| IPDS-4-909 | With CID | X'0007' | ❓ |
| IPDS-4-910 | command is treated as a No Operation (NOP) command. | ❓ |
| IPDS-4-911 | The length of the SPE command can be: | ❓ |
| IPDS-4-912 | Without CID | X'0007' or X'0009'–X'7FFF' | ❓ |
| IPDS-4-913 | With CID | X'0009' or X'000B'–X'7FFF' | ❓ |
| IPDS-4-914 | invalid or unsupported. | ❓ |
| IPDS-4-915 | The data in an SPE command is defined as follows: | ❓ |
| IPDS-4-916 | 0–1 | X'0000' | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-917 | 2 to end of SPE | | Triplets | | Zero or more triplets:<br>X'95' Rendering Intent triplet<br>X'97' Device Appearance triplet | ❓ |
| IPDS-4-918 | Bytes 0–1 Reserved** | ❓ |
| IPDS-4-919 | Zero or more triplets** | ❓ |
| IPDS-4-920 | 1. If no triplets are specified in the SPE command, all presentation attributes are set to the | ❓ |
| IPDS-4-921 | 2. If one or more triplets are specified in the SPE command, each specified triplet sets the | ❓ |
| IPDS-4-922 | X'F205'—Rendering Intent (X'95') triplet support | ❓ |
| IPDS-4-923 | X'F206'—Device Appearance (X'97') triplet support | ❓ |
| IPDS-4-924 | “Device Appearance (X'97') Triplet” | ❓ |
| IPDS-4-925 | In alphabetic sequence, the orders are: | ❓ |
| IPDS-4-926 | Table 28. XOA Order Summary** | ❓ |
| IPDS-4-927 | X'1000' | XOA Activate Printer Alarm | No | ❓ |
| IPDS-4-928 | X'0A00' | XOA Alternate Offset Stacker | No | ❓ |
| IPDS-4-929 | X'0C00' | XOA Control Edge Marks | No | ❓ |
| IPDS-4-930 | X'F200' | XOA Discard Buffered Data | Yes | ❓ |
| IPDS-4-931 | X'F500' | XOA Discard Unstacked Pages | No | ❓ |
| IPDS-4-932 | X'F600' | XOA Exception-Handling Control | Yes | ❓ |
| IPDS-4-933 | X'0800' | XOA Mark Form | No | ❓ |
| IPDS-4-934 | X'F900' | XOA Obtain Additional Exception Information | No | ❓ |
| IPDS-4-935 | X'F800' | XOA Print-Quality Control | No | ❓ |
| IPDS-4-936 | X'F400' | XOA Request Resource List | Yes | ❓ |
| IPDS-4-937 | X'FA00' | XOA Request Setup Name List | No | ❓ |
| IPDS-4-938 | Retired Order Codes** | ❓ |
| IPDS-4-939 | X'0000' | Retired item 18 | No | ❓ |
| IPDS-4-940 | X'0001' | Retired item 19 | No | ❓ |
| IPDS-4-941 | X'0002' | Retired item 20 | No | ❓ |
| IPDS-4-942 | X'0200' | Retired item 21 | No | ❓ |
| IPDS-4-943 | X'0600' | Retired item 22 | No | ❓ |
| IPDS-4-944 | X'0700' | Retired item 141 | No | ❓ |
| IPDS-4-945 | X'0900' | Retired item 142 | No | ❓ |
| IPDS-4-946 | X'7BF5' | Retired item 23 | No | ❓ |
| IPDS-4-947 | X'CACA' | Retired item 24 | No | ❓ |
| IPDS-4-948 | X'CE00' | Retired item 143 | No | ❓ |
| IPDS-4-949 | X'F100' | Retired item 25 | No | ❓ |
| IPDS-4-950 | X'F300' | Retired item 26 | No | ❓ |
| IPDS-4-951 | Unknown or unsupported orders are treated as No Operation (NOP) commands. | ❓ |
| IPDS-4-952 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-4-953 | 0–1 | CODE | Order code | X'1000' | Activate Printer Alarm (APA) order code | X'1000' | ❓ |
| IPDS-4-954 | operation is performed. | ❓ |
| IPDS-4-955 | 0–1 | CODE | Order code | X'0A00' | Alternate Offset Stacker (AOS) order code | X'0A00' | ❓ |
| IPDS-4-956 | the bottom of the sheet, all identical copies of this sheet have one edge mark at the top and two edge marks at | ❓ |
| IPDS-4-957 | contain any edge marks. | ❓ |
| IPDS-4-958 | 0–1 | CODE | Order code | X'0C00' | Control Edge Marks (CEM) order code | X'0C00' | ❓ |
| IPDS-4-959 | 2 | CODE | Edgemark | | Edge Mark:<br>X'00' Inhibit (default)<br>X'01' Continue<br>X'F1' One edge mark<br>X'F2' Two edge marks<br>X'F3' Three edge marks<br>X'FE' Alternate | ❓ |
| IPDS-4-960 | Bytes 0–1 Control Edge Marks order code** | ❓ |
| IPDS-4-961 | Exception ID X'0299..02' exists if an invalid edge mark value is specified. | ❓ |
| IPDS-4-962 | ACK of the DBD order is not returned until DBD processing is complete. | ❓ |
| IPDS-4-963 | 0–1 | CODE | Order code | X'F200' | Discard Buffered Data (DBD) order code | X'F200' | ❓ |
| IPDS-4-964 | processed. Also, the ACK of the DUP order is not returned until DUP processing is complete. | ❓ |
| IPDS-4-965 | 0–1 | CODE | Order code | X'F500' | Discard Unstacked Pages (DUP) order code | X'F500' | ❓ |
| IPDS-4-966 | Which exceptions with AEAs are reported to the host. | ❓ |
| IPDS-4-967 | Whether the AEA, if one exists, is to be taken. | ❓ |
| IPDS-4-968 | How processing continues if the AEA is not taken. | ❓ |
| IPDS-4-969 | For some printers, the highlighting of position-check exceptions when a Page-Continuation Action is not | ❓ |
| IPDS-4-970 | For some printers, the use of Exception Page Print to control printing of page information when the printer | ❓ |
| IPDS-4-971 | For some printers, the reporting or suppressing of certain NACKs that cause the printer to discard buffered | ❓ |
| IPDS-4-972 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-973 | Whether or not printing should continue when an exception is detected | ❓ |
| IPDS-4-974 | Whether or not an exception should be reported | ❓ |
| IPDS-4-975 | For color exceptions, what type of color substitution is permitted | ❓ |
| IPDS-4-976 | 2 BITS Exception reporting flags | ❓ |
| IPDS-4-977 | 3 BITS Automatic Recovery flags | ❓ |
| IPDS-4-978 | 4 BITS Exception-presentation processing flags | ❓ |
| IPDS-4-979 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-980 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-981 | 1. Support for this bit is optional and is reported in the reply to a Sense Type | ❓ |
| IPDS-4-982 | 2. There are other action codes that can cause page data to be discarded by | ❓ |
| IPDS-4-983 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-984 | 1. Skip and Continue Action | ❓ |
| IPDS-4-985 | supports Independent Exception Page Print and if the ExceptiOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-986 | If the exception occurs in a text-major Write Text command within a | ❓ |
| IPDS-4-987 | If the exception occurs in an Anystate command, the next valid | ❓ |
| IPDS-4-988 | If the exception occurs in a presentation-object | ❓ |
| IPDS-4-989 | If the exception occurs in any other command, the next valid command | ❓ |
| IPDS-4-990 | An asynchronous exception occurs such that the printer cannot recover | ❓ |
| IPDS-4-991 | A command is received with the ARQ flag set on. The exception that | ❓ |
| IPDS-4-992 | A command is received with a length outside the valid IPDS range. The | ❓ |
| IPDS-4-993 | A command is received that violates the IPDS state diagram. The | ❓ |
| IPDS-4-994 | SHS, XOA DBD, and XOA DUP are next valid commands in all cases. | ❓ |
| IPDS-4-995 | Anystate commands with the ARQ bit set on are terminating conditions. | ❓ |
| IPDS-4-996 | All other Anystate commands are processed as normal. However, | ❓ |
| IPDS-4-997 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-998 | 1. The order of exception discovery is device dependent. For example, | ❓ |
| IPDS-4-999 | 2. For a printer that uses parallel processing, if bit 6 is set to B'0', the | ❓ |
| IPDS-4-1000 | 2. Page Continuation Action | ❓ |
| IPDS-4-1001 | Reduce the possibility of presenting incorrect data | ❓ |
| IPDS-4-1002 | Reduce the possibility that incorrect data can be mistaken for correct | ❓ |
| IPDS-4-1003 | A hardware exception occurs when the printer cannot recover without | ❓ |
| IPDS-4-1004 | A command is received with the ARQ flag set to B'1'. | ❓ |
| IPDS-4-1005 | A command is received with a length outside the valid IPDS range. | ❓ |
| IPDS-4-1006 | A command is received that violates the IPDS state diagram. | ❓ |
| IPDS-4-1007 | SHS, XOA DBD, and XOA DUP are next valid commands in all cases. | ❓ |
| IPDS-4-1008 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1009 | Anystate commands with the ARQ bit set on are terminating conditions. | ❓ |
| IPDS-4-1010 | All other Anystate commands are processed as normal. However, | ❓ |
| IPDS-4-1011 | 1. The order of exception discovery is device dependent. For example, | ❓ |
| IPDS-4-1012 | 2. For a printer that uses parallel processing, if bit 6 is set to B'0', the | ❓ |
| IPDS-4-1013 | If the printer supports Independent Exception Page Print as specified by | ❓ |
| IPDS-4-1014 | If the printer does not support Independent Exception Page Print support, | ❓ |
| IPDS-4-1015 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1016 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1017 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1018 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1019 | XOA Exception-Handling Control (EHC) | ❓ |
| IPDS-4-1020 | Front Facing or on sheets created by a hardware nonprocess runout (NPRO). | ❓ |
| IPDS-4-1021 | 0–1 | CODE | Order code | X'0800' | Mark Form (MF) order code | X'0800' | ❓ |
| IPDS-4-1022 | vector in an STM reply. | ❓ |
| IPDS-4-1023 | 0–1 | CODE | Order code | X'F900' | Obtain Additional Exception Information (OAEI) order code | X'F900' | ❓ |
| IPDS-4-1024 | The special data area of an OAEI reply has the following format: | ❓ |
| IPDS-4-1025 | 0–3 | UBIN | Length | X'00000006'<br>X'0000000B'–X'FFFFFFFF' | No additional exception information available<br>Length of data, including this length field | ❓ |
| IPDS-4-1026 | 4–5 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-1027 | Zero or more entries in the following format: | ❓ |
| IPDS-4-1028 | + 0–3 | UBIN | Entry Length | X'00000005'–X'FFFFFFFA' | Length of the entry, including this length field | ❓ |
| IPDS-4-1029 | + 4 | CODE | Format | X'01' | Format of the additional information: Unarchitected text | ❓ |
| IPDS-4-1030 | + 5 to end of entry | | Additional information | See format descriptions below | Additional exception information. The syntax for this field is specific to the format code; refer to the byte descriptions below for specific syntax. | ❓ |
| IPDS-4-1031 | X'01' Unarchitected text | ❓ |
| IPDS-4-1032 | Format X'01' is used to return additional exception information as unarchitected text. | ❓ |
| IPDS-4-1033 | + 5 | CODE | Encoding | X'01' | The encoding used in the text-information field: UTF-16BE | ❓ |
| IPDS-4-1034 | + 6–7 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-1035 | + 8 to end of entry | | Text information | Any valid text | Unarchitected text containing the additional exception information | ❓ |
| IPDS-4-1036 | fonts, overlays, or page segments. The format of the PQC order is as follows: | ❓ |
| IPDS-4-1037 | 0–1 | CODE | Order code | X'F800' | Print-Quality Control (PQC) order code | X'F800' | ❓ |
| IPDS-4-1038 | 2 | UBIN | Quality level | X'01'–X'FF' | Print-quality level | X'01'–X'FF' | ❓ |
| IPDS-4-1039 | 171 through 254 to its third text level. If only one quality level is supported for image data, | ❓ |
| IPDS-4-1040 | XOA Print-Quality Control (PQC) | ❓ |
| IPDS-4-1041 | . The reply format is shown in “Resource List Reply”. | ❓ |
| IPDS-4-1042 | 0–1 CODE Order code X'F400' Request Resource List order code X'F400' | ❓ |
| IPDS-4-1043 | 0–1 | CODE | Order code | X'F400' | Request Resource List order code | X'F400' | ❓ |
| IPDS-4-1044 | 2 | CODE | Query type | X'05'<br>X'FF', X'00' | Activation query, multiple entries optional<br>General query, single entry only X'FF' | ❓ |
| IPDS-4-1045 | 3–4 | CODE | Continue | Any value | Entry-continuation indicator | Any value | ❓ |
| IPDS-4-1046 | One or more resource query entries in the following format: | ❓ |
| IPDS-4-1047 | 5 | UBIN | Length | X'03' to end of entry | Entry length of Bytes 5–n | X'03' | ❓ |
| IPDS-4-1048 | 6 | CODE | RT | | Resource Type: | ❓ |
| IPDS-4-1049 | X'01' | Single-byte LF1-type or LF2-type coded font | ❓ |
| IPDS-4-1050 | X'02' | Double-byte LF1-type coded font | ❓ |
| IPDS-4-1051 | X'03' | Double-byte LF1-type coded-font section | ❓ |
| IPDS-4-1052 | X'04' | Page segment | ❓ |
| IPDS-4-1053 | X'05' | Overlay | ❓ |
| IPDS-4-1054 | X'06' | Device-version code page | ❓ |
| IPDS-4-1055 | X'07' | Font character set | ❓ |
| IPDS-4-1056 | X'08' | Single-byte raster, single-byte outline, or double-byte outline coded-font FIS | ❓ |
| IPDS-4-1057 | X'09' | Double-byte coded-font section index | ❓ |
| IPDS-4-1058 | X'10' | Coded font | ❓ |
| IPDS-4-1059 | X'11' | Graphic character set supported in a font character set | ❓ |
| IPDS-4-1060 | X'12' | Specific code page | ❓ |
| IPDS-4-1061 | X'20' | Saved page group | ❓ |
| IPDS-4-1062 | X'40' | Data object resource | ❓ |
| IPDS-4-1063 | X'41' | Data-object font | ❓ |
| IPDS-4-1064 | X'42' | Data-object-font component | ❓ |
| IPDS-4-1065 | X'FF' | All resources | ❓ |
| IPDS-4-1066 | 7 | CODE | RIDF | | Resource ID format: | ❓ |
| IPDS-4-1067 | X'00' | Host-assigned resource ID | ❓ |
| IPDS-4-1068 | X'03' | GRID-parts format | ❓ |
| IPDS-4-1069 | X'08' | Variable-length Group ID (X'00') triplet | ❓ |
| IPDS-4-1070 | X'09' | Object-OID format | ❓ |
| IPDS-4-1071 | 8 to end of entry | | Resource ID | | Resource ID | Any value | ❓ |
| IPDS-4-1072 | issued following an AR command to interrogate the device on the activation status of | ❓ |
| IPDS-4-1073 | RRL reply sequence is ended with a reply-list entry with a length of X'01'. | ❓ |
| IPDS-4-1074 | byte (byte 4) in the Request Resource List reply to zero. | ❓ |
| IPDS-4-1075 | Table 29. Architecturally-Valid RT and RIDF Query Combinations** | ❓ |
| IPDS-4-1076 | X'01' | Single-byte LF1-type or LF2-type coded-font | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1077 | X'03' GRID-parts format | X'08' | ❓ |
| IPDS-4-1078 | X'02' | Double-byte LF1-type coded-font | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1079 | X'03' GRID-parts format | X'08' | ❓ |
| IPDS-4-1080 | X'03' | Double-byte LF1-type coded-font section | X'00' HAID format | X'03' | ❓ |
| IPDS-4-1081 | X'03' GRID-parts format | X'09' | ❓ |
| IPDS-4-1082 | X'04' | Page segment | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1083 | X'05' | Overlay | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1084 | X'06' | Device-version code page | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1085 | X'03' GRID-parts format | X'02' | ❓ |
| IPDS-4-1086 | X'07' | Font character set | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1087 | X'03' GRID-parts format | X'06' | ❓ |
| IPDS-4-1088 | X'08' | Single-byte raster, single-byte outline, or double-byte outline coded-font FIS | X'00' HAID format | X'02' or X'04' | ❓ |
| IPDS-4-1089 | X'09' | Double-byte coded-font section index | X'00' HAID format | X'03' or X'05' | ❓ |
| IPDS-4-1090 | X'10' | Coded font | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1091 | X'03' GRID-parts format | X'08' | ❓ |
| IPDS-4-1092 | X'11' | Graphic character set supported in a font character set | X'03' GRID-parts format | X'02' | ❓ |
| IPDS-4-1093 | X'12' | Specific code page | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1094 | X'03' GRID-parts format | X'04' | ❓ |
| IPDS-4-1095 | X'20' | Saved page group | X'08' Variable-length Group ID (X'00') triplet format | X'02'–X'F7' | ❓ |
| IPDS-4-1096 | X'40' | Data object resource | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1097 | X'09' Object-OID format | X'02'–X'81' | ❓ |
| IPDS-4-1098 | X'41' | Data-object font | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1099 | X'42' | Data-object-font component | X'00' HAID format | X'02' | ❓ |
| IPDS-4-1100 | X'09' Object-OID format | X'02'–X'81' | ❓ |
| IPDS-4-1101 | X'FF' | All resources | X'00' HAID format | N/A | ❓ |
| IPDS-4-1102 | Single-byte LF1-type or LF2-type coded font (RT = X'01') | ❓ |
| IPDS-4-1103 | Double-byte LF1-type coded font (RT = X'02') | ❓ |
| IPDS-4-1104 | Double-byte LF1-type coded-font section (RT = X'03') | ❓ |
| IPDS-4-1105 | Page segment (RT = X'04') | ❓ |
| IPDS-4-1106 | Overlay (RT = X'05') | ❓ |
| IPDS-4-1107 | Code page (RT = X'06' or X'12') | ❓ |
| IPDS-4-1108 | Font character set (RT = X'07') | ❓ |
| IPDS-4-1109 | Single-byte raster, single-byte outline, or double-byte outline coded-font FIS | ❓ |
| IPDS-4-1110 | – A Load Font Index command | ❓ |
| IPDS-4-1111 | All FISes of a particular single-byte raster, single-byte outline, or double- | ❓ |
| IPDS-4-1112 | 1. The reply to this query consists of zero or more entries, each entry | ❓ |
| IPDS-4-1113 | 2. For a list query, the reply contains a list of all present single-byte raster | ❓ |
| IPDS-4-1114 | Double-byte coded-font index (RT = X'09') | ❓ |
| IPDS-4-1115 | All the indexes of a particular double-byte coded font (RT = X'09') | ❓ |
| IPDS-4-1116 | Coded font (RT = X'10') | ❓ |
| IPDS-4-1117 | Data object resource (RT = X'40') | ❓ |
| IPDS-4-1118 | Data-object font (RT = X'41') | ❓ |
| IPDS-4-1119 | Data-object-font component (RT = X'42') | ❓ |
| IPDS-4-1120 | – A home state Write Object Container Control command | ❓ |
| IPDS-4-1121 | Use either two bytes or four bytes to select a code page: | ❓ |
| IPDS-4-1122 | Use six bytes to select a font character set (RT = X'07'), | ❓ |
| IPDS-4-1123 | Use eight bytes to select a coded font | ❓ |
| IPDS-4-1124 | Use nine bytes to select a double-byte LF1-type coded-font section (RT = | ❓ |
| IPDS-4-1125 | Use two bytes to select a graphic character set (RT = X'11') supported in a | ❓ |
| IPDS-4-1126 | This triplet is described in “Group ID (X'00') Triplet”. | ❓ |
| IPDS-4-1127 | 129 bytes) binary ID that uniquely identifies an object. OIDs use the ASN.1 | ❓ |
| IPDS-4-1128 | 0 CODE Identifier X'06' This is a definite-short-form OID | ❓ |
| IPDS-4-1129 | 1 UBIN Length X'00'–X'7F' Length of the following content bytes | ❓ |
| IPDS-4-1130 | present and cannot be activated by setting the resource size byte to zero. | ❓ |
| IPDS-4-1131 | Reply to a query of an individual resource. There is one reply for each query entry: | ❓ |
| IPDS-4-1132 | Reply to a query of an individual resource: | ❓ |
| IPDS-4-1133 | Reply to a query of a list of resources: | ❓ |
| IPDS-4-1134 | Multiple versions of a font character set or code page with different date and time stamps | ❓ |
| IPDS-4-1135 | Multiple versions of a raster font with different resolutions or different date and time stamps | ❓ |
| IPDS-4-1136 | Host-assigned resource ID format. | ❓ |
| IPDS-4-1137 | 1. RRL-continuation method | ❓ |
| IPDS-4-1138 | 2. ACK-continuation method | ❓ |
| IPDS-4-1139 | 0 CODE Ordering | ❓ |
| IPDS-4-1140 | 1 UBIN Length X'01' | ❓ |
| IPDS-4-1141 | 2 CODE RT X'00'–X'FF' Resource type of this entry Any type queried | ❓ |
| IPDS-4-1142 | 3 CODE RIDF X'00'–X'FF' Resource ID format for this entry Any ID format | ❓ |
| IPDS-4-1143 | 4 CODE Size | ❓ |
| IPDS-4-1144 | X'04'–X'FF' should be accepted for this byte. | ❓ |
| IPDS-4-1145 | the format specified in byte 3. | ❓ |
| IPDS-4-1146 | property pair in the Device-Control command-set vector in an STM reply. | ❓ |
| IPDS-4-1147 | 0–1 | CODE | Order code | X'FA00' | Request Setup Name List (RSNL) order code | X'FA00' | ❓ |
| IPDS-4-1148 | 2 | CODE | Query type | X'00'<br>X'01' | Active setup name only<br>Setup name list | X'00'<br>X'01' | ❓ |
| IPDS-4-1149 | 3 | BITS | Setup name information requested | ❓ |
| IPDS-4-1150 | bit 0 | Activation possibility | B'0', B'1' | Return whether activation of the setup name is possible with the ASN command | B'0', B'1' | ❓ |
| IPDS-4-1151 | bit 1 | Detailed settings | B'0', B'1' | Return detailed settings for each setup name | B'0', B'1' | ❓ |
| IPDS-4-1152 | bits 2–7 | | B'000000' | Reserved | B'000000' | ❓ |
| IPDS-4-1153 | 4–5 | | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-4-1154 | 6 to end of RSNL | ❓ |
| IPDS-4-1155 | If an invalid value is specified, exception ID X'02A2..00' exists. | ❓ |
| IPDS-4-1156 | The special data area of an RSNL reply has the following format: | ❓ |
| IPDS-4-1157 | 0–3 | UBIN | Length | X'00000007'<br>X'0000000D'<br>X'0000000F'<br>X'00000011' – X'FFFFFFFF' | Length of reply data, including this field | ❓ |
| IPDS-4-1158 | 4–5 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-1159 | 6 | BITS | Setup name information returned | ❓ |
| IPDS-4-1160 | 7 to end | ❓ |
| IPDS-4-1161 | A change from one active setup name to another active setup name | ❓ |
| IPDS-4-1162 | A change from no active setup name to an active setup name | ❓ |
| IPDS-4-1163 | A change from an active setup name to no active setup name | ❓ |
| IPDS-4-1164 | A change from an active setup name to the same active setup name but the settings | ❓ |
| IPDS-4-1165 | Control command-set vector in an STM reply. | ❓ |
| IPDS-4-1166 | accept, but ignore this field; senders of this reply should not specify this field. | ❓ |
| IPDS-4-1167 | In alphabetic sequence, the orders are: | ❓ |
| IPDS-4-1168 | Table 30. XOH Order Summary** | ❓ |
| IPDS-4-1169 | X'0200' | XOH Deactivate Saved Page Group | No | ❓ |
| IPDS-4-1170 | X'0400' | XOH Define Group Boundary | No | ❓ |
| IPDS-4-1171 | X'1300' | XOH Eject to Front Facing | No | ❓ |
| IPDS-4-1172 | X'0700' | XOH Erase Residual Font Data | No | ❓ |
| IPDS-4-1173 | X'0500' | XOH Erase Residual Print Data | No | ❓ |
| IPDS-4-1174 | X'F300' | XOH Obtain Printer Characteristics | Yes | ❓ |
| IPDS-4-1175 | X'F500' | XOH Page Counters Control | No | ❓ |
| IPDS-4-1176 | X'0100' | XOH Print Buffered Data | Yes | ❓ |
| IPDS-4-1177 | X'0A00' | XOH Remove Saved Page Group | No | ❓ |
| IPDS-4-1178 | X'1500' | XOH Select Input Media Source | No | ❓ |
| IPDS-4-1179 | X'0E00' | XOH Select Medium Modifications | No | ❓ |
| IPDS-4-1180 | X'0900' | XOH Separate Continuous Forms | No | ❓ |
| IPDS-4-1181 | X'1600' | XOH Set Media Origin | No | ❓ |
| IPDS-4-1182 | X'1700' | XOH Set Media Size | No | ❓ |
| IPDS-4-1183 | X'0300' | XOH Specify Group Operation | No | ❓ |
| IPDS-4-1184 | X'0D00' | XOH Stack Received Pages | No | ❓ |
| IPDS-4-1185 | X'F200' | XOH Trace | No | ❓ |
| IPDS-4-1186 | Retired Order Codes** | ❓ |
| IPDS-4-1187 | X'0000' | Retired item 31 | No | ❓ |
| IPDS-4-1188 | X'0B00' | Retired item 32 | No | ❓ |
| IPDS-4-1189 | X'1C00' | Retired item 144 | No | ❓ |
| IPDS-4-1190 | X'1D00' | Retired item 145 | No | ❓ |
| IPDS-4-1191 | X'4C00' | Retired item 146 | No | ❓ |
| IPDS-4-1192 | X'4D00' | Retired item 147 | No | ❓ |
| IPDS-4-1193 | X'4E00' | Retired item 148 | No | ❓ |
| IPDS-4-1194 | X'D000' | Retired item 127 | No | ❓ |
| IPDS-4-1195 | X'F400' | Retired item 33 | No | ❓ |
| IPDS-4-1196 | Unknown or unsupported orders are treated as No Operation (NOP) commands. | ❓ |
| IPDS-4-1197 | group is likely to be activated soon. | ❓ |
| IPDS-4-1198 | 0–1 | CODE | Order code | X'0200' | Deactivate Saved Page Group (DSPG) order code | X'0200' | ❓ |
| IPDS-4-1199 | 2 to end | Triplets | | | Zero or more Group ID (X'00') triplets:<ul><li>X'00' Group ID triplet with variable-length group ID</li></ul> | ❓ |
| IPDS-4-1200 | “Group ID (X'00') Triplet” | ❓ |
| IPDS-4-1201 | Byte 2 or the first byte after a valid triplet was X'00' or X'01' (an invalid triplet length). | ❓ |
| IPDS-4-1202 | A triplet other than a Group ID (X'00') triplet was specified. | ❓ |
| IPDS-4-1203 | A Group ID (X'00') triplet without a variable-length group ID was specified. | ❓ |
| IPDS-4-1204 | Table 31. Group Operation Nesting** | ❓ |
| IPDS-4-1205 | Keep Group Together as a Print Unit | OK | OK | Ignored | OK | OK | OK | ❓ |
| IPDS-4-1206 | Keep Group Together as a Recovery Unit | Ignored | Ignored | Ignored | OK | OK | OK | ❓ |
| IPDS-4-1207 | Keep Group Together for Microfilm Output | Ignored | OK | OK | OK | Ignored | OK | ❓ |
| IPDS-4-1208 | Save Pages | Ignored | Ignored | Ignored | OK | Ignored | OK | ❓ |
| IPDS-4-1209 | Finish | OK | OK | Ignored | OK | OK | OK | ❓ |
| IPDS-4-1210 | Identify Named Group | OK | OK | OK | OK | OK | OK | ❓ |
| IPDS-4-1211 | group requires a finishing triplet. | ❓ |
| IPDS-4-1212 | The format of the XOH-DGB command is as follows: | ❓ |
| IPDS-4-1213 | 0–1 | CODE | Order code | X'0400' | Define Group Boundary (DGB) order code | X'0400' | ❓ |
| IPDS-4-1214 | 2 | CODE | Order type | X'00' or X'01' | DGB order type:<ul><li>X'00' Initiate group</li><li>X'01' Terminate group</li></ul> | X'00' or X'01' | ❓ |
| IPDS-4-1215 | 3 | UBIN | Group level | X'00'–X'FF' | Group level | X'00'–X'FF' | ❓ |
| IPDS-4-1216 | 4 to end | Triplets | | | Zero or more triplets:<ul><li>X'00' Group ID triplet</li><li>X'01' CGCSGID triplet</li><li>X'6E' Group Information triplet</li><li>X'85' Finishing Operation triplet</li><li>X'8E' UP3I Finishing Operation triplet</li></ul> | ❓ |
| IPDS-4-1217 | Each group operation defines the relationship among the triplets. | ❓ |
| IPDS-4-1218 | Table 32. Triplets Used With Each Group Operation** | ❓ |
| IPDS-4-1219 | Keep group together as a print unit | Group ID (X'00') triplet<br>Group Information (X'6E') triplet | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>Extended OS/400 print-data format<br>AIX® and Windows® print-data format<br>Copy set number format<br>Extended copy set number format<br>Page count format | ❓ |
| IPDS-4-1220 | Keep Group Together as a Recovery Unit | Group Information (X'6E') triplet | Group name format (one)<br>Additional information format (zero or more) | ❓ |
| IPDS-4-1221 | Keep group together for microfilm output | Group ID (X'00') triplet<br>Group Information (X'6E') triplet | MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>Microfilm save/restore format | ❓ |
| IPDS-4-1222 | Save pages | Group ID (X'00') triplet<br>CGCSGID (X'01') triplet | Variable-length group ID format<br>GCSGID/CPGID format<br>CCSID format | ❓ |
| IPDS-4-1223 | Finish | Finishing operation (X'85') triplet<br>UP3I Finishing operation (X'8E') triplet | AFP finishing format (triplet X'85')<br>UP3I finishing format (triplet X'8E') | ❓ |
| IPDS-4-1224 | Identify named group | Group Information (X'6E') triplet<br>CGCSGID (X'01') triplet | Group name format (one)<br>Additional information format (zero or more)<br>GCSGID/CPGID format<br>CCSID format | ❓ |
| IPDS-4-1225 | 3I Finishing Operation (X'8E') Triplet” | ❓ |
| IPDS-4-1226 | Variable-length group ID format in the Group ID (X'00') triplet | ❓ |
| IPDS-4-1227 | Group name format in the Group Information (X'6E') triplet | ❓ |
| IPDS-4-1228 | 1. Some printers must know about a finishing operation before the first page of a group is received. In this | ❓ |
| IPDS-4-1229 | 2. If an XOH Stack Received Pages command is received within a group to be finished, all received pages | ❓ |
| IPDS-4-1230 | 3. For some printers, alternate offset stacking cannot be combined with a finishing operation. In this case, if | ❓ |
| IPDS-4-1231 | finishing operation is performed. | ❓ |
| IPDS-4-1232 | If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the | ❓ |
| IPDS-4-1233 | If an operation can only be fully specified in one of the triplets, that triplet must be used. | ❓ |
| IPDS-4-1234 | IPDS nesting rules apply equally to both triplets (for identical finishing operations the triplets are | ❓ |
| IPDS-4-1235 | Compatible nesting combinations are determined by the printing system; UP3I operation-compatibility rules | ❓ |
| IPDS-4-1236 | This order is not cumulative; consecutive EFF orders produce the same effect as a single order. | ❓ |
| IPDS-4-1237 | 0–1 | CODE | Order code | X'1300' | Eject to Front Facing (EFF) order code | X'1300' | ❓ |
| IPDS-4-1238 | 1. Eject to the next sheet if not already on a new sheet. The next received page is the first page on the new | ❓ |
| IPDS-4-1239 | 2. Perform an XOH Print Buffered Data. | ❓ |
| IPDS-4-1240 | 3. For downloaded fonts, clear all font information from printer storage by setting all bits to the same value or | ❓ |
| IPDS-4-1241 | 4. For resident fonts activated by an AR command, clear HARID-to-global-name mappings. | ❓ |
| IPDS-4-1242 | 1. Information about activated coded fonts and activated data-object fonts is erased by this command. | ❓ |
| IPDS-4-1243 | 2. TrueType/OpenType fonts used as secondary resources in presentation data objects such as a PDF or | ❓ |
| IPDS-4-1244 | ACK of the XOH-ERFD command is not returned until the command's processing is complete. | ❓ |
| IPDS-4-1245 | 0–1 | CODE | Order code | X'0700' | Erase Residual Font Data (ERFD) order code | X'0700' | ❓ |
| IPDS-4-1246 | 1. Eject to the next sheet if not already on a new sheet. The next received page is the first page on the new | ❓ |
| IPDS-4-1247 | 2. Perform an XOH Print Buffered Data. | ❓ |
| IPDS-4-1248 | 3. Terminate all open groups, deactivate all saved page groups, and remove all previously received group | ❓ |
| IPDS-4-1249 | 4. Deactivate all page segments, overlays, saved page groups, data object resources, and data-object-font | ❓ |
| IPDS-4-1250 | 5. For downloaded page segments, overlays, data object resources, data-object-font components, and | ❓ |
| IPDS-4-1251 | ACK of the XOH-ERPD command is not returned until the command's processing is complete. | ❓ |
| IPDS-4-1252 | 0–1 | CODE | Order code | X'0500' | Erase Residual Print Data (ERPD) order code | X'0500' | ❓ |
| IPDS-4-1253 | Table 33. OPC Self-Defining Field Summary** | ❓ |
| IPDS-4-1254 | X'0001' | “Printable-Area Self-Defining Field” | ❓ |
| IPDS-4-1255 | X'0002' | “Symbol-Set Support Self-Defining Field” | ❓ |
| IPDS-4-1256 | X'0003' | “IM-Image and Coded-Font Resolution Self-Defining Field” | ❓ |
| IPDS-4-1257 | X'0004' | “Storage Pools Self-Defining Field” | ❓ |
| IPDS-4-1258 | X'0005' | “Retired Item 130 (Standard OCA Color Value Support Self-Defining Field)” | ❓ |
| IPDS-4-1259 | X'0006' | “Installed Features Self-Defining Field” | ❓ |
| IPDS-4-1260 | X'0007' | “Available Features Self-Defining Field” | ❓ |
| IPDS-4-1261 | X'0008' | “Resident Symbol-Set Support Self-Defining Field” | ❓ |
| IPDS-4-1262 | X'0009' | “Print-Quality Support Self-Defining Field” | ❓ |
| IPDS-4-1263 | X'000A' | “XOA-RRL RT & RIDF Support Self-Defining Field” | ❓ |
| IPDS-4-1264 | X'000B' | “Activate Resource RT & RIDF Support Self-Defining Field” | ❓ |
| IPDS-4-1265 | X'000C' | Retired item 134 | ❓ |
| IPDS-4-1266 | X'000D' | “Medium Modification IDs Supported Self-Defining Field” | ❓ |
| IPDS-4-1267 | X'000E' | “Deprecated (Common Bar Code Type/Modifier Self-Defining Field)” | ❓ |
| IPDS-4-1268 | X'000F' | “Bar Code Type/Modifier Self-Defining Field” | ❓ |
| IPDS-4-1269 | X'0010' | “Media-Destinations Self-Defining Field” | ❓ |
| IPDS-4-1270 | X'0012' | “Supported Group Operations Self-Defining Field” | ❓ |
| IPDS-4-1271 | X'0013' | “Product Identifier Self-Defining Field” | ❓ |
| IPDS-4-1272 | X'0014' | “Object-Container Type Support Self-Defining Field” | ❓ |
| IPDS-4-1273 | X'0015' | “DF Deactivation Types Supported Self-Defining Field” | ❓ |
| IPDS-4-1274 | X'0016' | “PFC Triplets Supported Self-Defining Field” | ❓ |
| IPDS-4-1275 | X'0017' | “Printer Setup Self-Defining Field” | ❓ |
| IPDS-4-1276 | X'0018' | “Finishing Operations Self-Defining Field” | ❓ |
| IPDS-4-1277 | X'0019' | “UP3I Tupel Self-Defining Field” | ❓ |
| IPDS-4-1278 | X'001A' | “UP3I Paper Input Media Self-Defining Field” | ❓ |
| IPDS-4-1279 | X'0021' | “Colorant-Identification Self-Defining Field” | ❓ |
| IPDS-4-1280 | X'0022' | “Device-Appearance Self-Defining Field” | ❓ |
| IPDS-4-1281 | X'0024' | “Keep-Group-Together-as-a-Recovery-Unit Self-Defining Field” | ❓ |
| IPDS-4-1282 | X'0025' | “Recognized Group ID Formats Self-Defining Field” | ❓ |
| IPDS-4-1283 | X'0026' | “Supported Device Resolutions Self-Defining Field” | ❓ |
| IPDS-4-1284 | X'0027' | “Object-Container Version Support Self-Defining Field” | ❓ |
| IPDS-4-1285 | X'0028' | “Finishing Options Self-Defining Field” | ❓ |
| IPDS-4-1286 | X'0029' | “Printer Speed Self-Defining Field ” | ❓ |
| IPDS-4-1287 | X'002A' | “Active Setup Name Self-Defining Field ” | ❓ |
| IPDS-4-1288 | source IDs (aliases). | ❓ |
| IPDS-4-1289 | 0–1 | UBIN | SDF length | X'0018' plus length of 0, 1, or 2 media ID entries | Length of this self-defining field, including this length field; maximum length X'0115' | ❓ |
| IPDS-4-1290 | 2–3 | CODE | SDF ID | X'0001' | Printable-Area self-defining field ID | ❓ |
| IPDS-4-1291 | 4 | CODE | Media source ID | X'00'–X'FF' | Media-source ID: This ID can be selected by either the XOH-SIMS command or the LCC command. The STM reply specifies which method, or methods, of media-source selection the printer supports. | ❓ |
| IPDS-4-1292 | 5 | | | X'00' | Reserved | ❓ |
| IPDS-4-1293 | 6 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Unit base for this self-defining field:<br>Ten inches<br>Ten centimeters<br>Retired item 34 | ❓ |
| IPDS-4-1294 | 7 | | | X'00' | Reserved | ❓ |
| IPDS-4-1295 | 8–9 | UBIN | UPUB | X'0001' – X'7FFF' | Units per unit base value for this self-defining field | ❓ |
| IPDS-4-1296 | 10–11 | UBIN | Actual medium presentation space width | X'0001' – X'7FFF' | Actual width of the medium presentation space in L-units. For a printer using cut-sheet media, the width is along the top edge of the sheet. For a printer using continuous-forms media, the width is along the leading edge of the physical media as it moves through the printer and does not include the width of the carrier strips. For a printer using envelope media, the width is along the top edge of the envelope. When the medium presentation space origin corresponds to the printer default media origin, this parameter determines the $X_m$ extent of the medium presentation space in all cases but one. In the case of continuous-forms printers that define the top edge of the sheet to be perpendicular to the leading edge, this parameter determines the $Y_m$ extent of the medium presentation space.<br>This parameter specifies the actual width of the medium presentation space, not necessarily the width used for VPA calculations and N-up partitioning. Refer to “XOH Set Media Size” for details. | ❓ |
| IPDS-4-1297 | 12–13 | UBIN | Actual medium presentation space length | X'0001' – X'7FFF' | Actual length of the medium presentation space in L-units. When the medium presentation space origin corresponds to the printer default media origin, this parameter determines the $Y_m$ extent of the medium presentation space in all cases but one. In the case of continuous-forms printers that define the top edge of the sheet to be perpendicular to the leading edge, this parameter determines the $X_m$ extent of the medium presentation space.<br>This parameter specifies the actual length of the medium presentation space, not necessarily the length used for VPA calculations and N-up partitioning. Refer to “XOH Set Media Size” for details. For continuous-forms media, the XOH-SMS command can be used to change the actual length of the medium presentation space that results in a corresponding adjustment to the length of the sheet and the physical printable area. | ❓ |
| IPDS-4-1298 | 14–15 | UBIN | $X_m$ PPAoffset | X'0000' – X'7FFF' | $X_m$ offset of the physical printable area in L-units | ❓ |
| IPDS-4-1299 | 16–17 | UBIN | $Y_m$ PPAoffset | X'0000' – X'7FFF' | $Y_m$ offset of the physical printable area in L-units | ❓ |
| IPDS-4-1300 | 18–19 | UBIN | $X_m$ PPAextent | X'0001' – X'7FFF' | $X_m$ extent of the physical printable area in L-units | ❓ |
| IPDS-4-1301 | 20–21 | UBIN | $Y_m$ PPAextent | X'0001' – X'7FFF' | $Y_m$ extent of the physical printable area in L-units | ❓ |
| IPDS-4-1302 | 22–23 | BITS | Input media source characteristic flags | | All combinations of all the characteristics are architecturally valid. | ❓ |
| IPDS-4-1303 | Bit | Name | Value | Meaning | ❓ |
| IPDS-4-1304 | bit 0 | Duplex | B'1'<br>B'0' | The media source (bin) is currently capable of duplexing, but it does not imply that there currently is duplexable physical media in the bin.<br>The media source is not currently capable of duplexing. | ❓ |
| IPDS-4-1305 | bits 1–2 | Primary media characteristic | B'01'<br>B'10' | Continuous forms<br>Cut sheet<br>This characteristic determines how the printer interprets certain commands such as XOH EFF, XOH SCF, and XOA CEM, as well as informing the host of the location of the top edge of the sheet. See the following envelope and COM bits for a description of the top edge of the sheet. | ❓ |
| IPDS-4-1306 | bit 3 | Available | B'1'<br>B'0' | Media source available<br>Media source not available; bytes 6–21 of the Printable-Area self-defining field might contain inaccurate information. | ❓ |
| IPDS-4-1307 | bit 4 | | B'0' | Retired item 119 | ❓ |
| IPDS-4-1308 | bit 5 | Envelope | B'1'<br>B'0' | Envelope media; the media source is currently set up for envelopes; either envelopes are in the media source, or the media source is empty. Envelopes are either continuous forms or cut sheet. However, the top edge of the sheet is as described in Figure 20.<br>Not envelope media. | ❓ |
| IPDS-4-1309 | bit 6 | Manual | B'1'<br>B'0' | Manual media feed<br>Automatic media feed | ❓ |
| IPDS-4-1310 | bit 7 | Computer Output on Microfilm (COM) | B'1'<br>B'0' | Computer output on microfilm media. COM is either continuous forms or cut-sheet. However, the top edge of the sheet is as described in Figure 21, Figure 22, and **Figure 23**.<br>Not computer output microfilm media | ❓ |
| IPDS-4-1311 | bit 8 | No carrier strips | B'1'<br>B'0' | Continuous forms media without carrier strips; this flag ignored for cut sheet media.<br>Continuous forms media with carrier strips | ❓ |
| IPDS-4-1312 | bit 9 | Inserter bin | B'1'<br>B'0' | The physical media in this bin is tracked with the page and copy counters, but no printing is done. Medium overlays and preprinted form overlays are suppressed on physical media selected from this bin. Edge marks and mark forms are also suppressed.<br>Not an inserter bin<br>Note: If the printer can duplex, the inserter bin should also be marked as duplex capable. | ❓ |
| IPDS-4-1313 | bit 10 | Media feed direction | B'0'<br>B'1' | Media feed direction:<br>Long-edge fed<br>Short-edge fed<br>Note: Not all IPDS devices set this flag; support is indicated by the X'F102' property pair in the Device-Control command-set vector of an STM reply. | ❓ |
| IPDS-4-1314 | bits 11–15 | | B'00000' | Reserved | ❓ |
| IPDS-4-1315 | Zero, one, or two media ID entries in the following format; the media ID type value must be different for each entry: | ❓ |
| IPDS-4-1316 | 24–25 | UBIN | Media ID length | X'0004' to end of entry | Length of input media identification (bytes 24 to end)<br>Note: This value is limited by the maximum length of data in a MO:DCA triplet (250 bytes). | ❓ |
| IPDS-4-1317 | 26 | CODE | Media ID type | X'00'<br>X'01'<br>X'10' | Type of input media identification. This is a registered code that identifies the naming scheme used; registered values include:<br>**User defined** (entry length range is X'0004'–X'00FD')<br>The input media ID (in bytes 27 to end) contains characters from IBM character set 640 using the code points assigned in IBM code page 500. The space character (X'40') is also allowed.<br>**Retired item 132**<br>**MO:DCA media type OID** (entry length range is X'000C'–X'000F')<br>The input media ID (in bytes 27 to end) contains an ASN.1 OID encoded using the definite short form (also called encoded form). For example, bytes 27–35 would contain X'06072B120004030101' to indicate ISO A4 colored media.<br>The registry of standard media types along with their OID is provided in the Media-Type-Identifiers section of the MO:DCA Registry Appendix in the Mixed Object Document Content Architecture Reference, SC31-6802. | ❓ |
| IPDS-4-1318 | 27 to end | UNDF | Input media ID | Any value | Input media identification | ❓ |
| IPDS-4-1319 | The input media ID is data whose meaning is printer specific. | ❓ |
| IPDS-4-1320 | The Symbol-Set Support self-defining field specifies the limits of support for the Load Symbol Set command. | ❓ |
| IPDS-4-1321 | 0–1 | UBIN | SDF length | X'000C' – X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1322 | 2–3 | CODE | SDF ID | X'0002' | Symbol-Set Support self-defining field ID | ❓ |
| IPDS-4-1323 | 4 to end of SDF | | Value entries | See following tables | Fixed-box size and variable-box size value entries as shown in the next two tables. | ❓ |
| IPDS-4-1324 | This value entry defines the acceptable character-box size for downloaded, monospaced symbol sets. The font identifiers in bytes 6 to end are the same as the font identifiers in bytes 9 and 10 of the Load Font Equivalence command. The symbol-set font identified has a uniform box X-size and box Y-size. Refer to “Load Symbol Set” for more information. This value entry has the following format: | ❓ |
| IPDS-4-1325 | 0 | UBIN | Value entry length | X'08'–X'FE' | Length of this value entry, including this length field | ❓ |
| IPDS-4-1326 | 1 | CODE | Value entry ID | X'01' | Fixed-box size value entry ID | ❓ |
| IPDS-4-1327 | 2 | UBIN | X box size | X'01'–X'FF' | Character-box X size in pels | ❓ |
| IPDS-4-1328 | 3 | UBIN | Y box size | X'01'–X'FF' | Character-box Y size in pels | ❓ |
| IPDS-4-1329 | 4 | | | X'00' | Reserved | ❓ |
| IPDS-4-1330 | 5 | UBIN | Entry length | X'02' | Length of each repeating group entry | ❓ |
| IPDS-4-1331 | One to 124 entries in the following format: | ❓ |
| IPDS-4-1332 | + 0–1 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID (FGID) supporting the box size | ❓ |
| IPDS-4-1333 | This value entry defines the acceptable character-box size for any proportional symbol-set identifiers that can be downloaded. The font identifiers in bytes 10 to end are the same as the font identifiers in bytes 9 and 10 of the Load Font Equivalence command. The symbol-set font identified has a uniform box Y-size and has a variable box X-size that serves as the character width. This value entry is formatted as follows: | ❓ |
| IPDS-4-1334 | 0 | UBIN | Value entry length | X'0C'–X'FA' | Length of this value entry, including this length field | ❓ |
| IPDS-4-1335 | 1 | CODE | Value entry ID | X'02' | Variable-box size value entry ID | ❓ |
| IPDS-4-1336 | 2 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Ten-inch increments<br>Ten-centimeter increments<br>Retired item 35 | ❓ |
| IPDS-4-1337 | 3 | | | X'00' | Reserved | ❓ |
| IPDS-4-1338 | 4–5 | UBIN | PPUB | X'0001' – X'7FFF' | Pels per unit base | ❓ |
| IPDS-4-1339 | 6 | UBIN | Maximum size | X'01'–X'FF' | Maximum character-box X size in pels | ❓ |
| IPDS-4-1340 | 7 | UBIN | Uniform size | X'01'–X'FF' | Uniform character-box Y size in pels | ❓ |
| IPDS-4-1341 | 8 | | | X'00' | Reserved | ❓ |
| IPDS-4-1342 | 9 | UBIN | Entry length | X'02' | Length of each repeating group entry | ❓ |
| IPDS-4-1343 | One to 120 entries in the following format: | ❓ |
| IPDS-4-1344 | + 0–1 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID (FGID) supporting this box size | ❓ |
| IPDS-4-1345 | The IM-Image and Coded-Font Resolution self-defining field specifies the supported resolutions in pels per unit base for IM Image and downloaded LF1-type and LF2-type coded-font pattern data. Most other data is resolution independent. For example, a resolution can be specified for GOCA image; however, if an image resolution is not specified, the image is resolution corrected by the printer based on assumptions. Refer to the implementation note in the description of the Begin Image orders within the GOCA Reference. | ❓ |
| IPDS-4-1346 | 0–1 | UBIN | SDF length | X'000A' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1347 | 2–3 | CODE | SDF ID | X'0003' | IM-Image and Coded-Font Resolution self-defining field ID | ❓ |
| IPDS-4-1348 | 4 | CODE | Unit base | X'00'<br>X'01'<br>X'02' | Unit base for this self-defining field:<br>Ten-inch increments<br>Ten-centimeter increments<br>Retired item 36 | ❓ |
| IPDS-4-1349 | 5 | CODE | Font resolutions | X'00'<br>X'FF' | LF1 raster-pattern resolutions supported:<br>Only the resolution specified in bytes 6–9<br>All resolutions in the range X'0001' – X'7FFF' (in this case, bytes 6–9 contain the highest device resolution) | ❓ |
| IPDS-4-1350 | 6–7 | UBIN | X pels | X'0001' – X'7FFF' | X pels per unit base | ❓ |
| IPDS-4-1351 | 8–9 | UBIN | Y pels | X'0001' – X'7FFF' | Y pels per unit base | ❓ |
| IPDS-4-1352 | 1. If all raster-pattern resolutions are supported (byte 5 = X'FF'), the printer must also support the Font | ❓ |
| IPDS-4-1353 | 2. The resolution values specified in bytes 6–7 and 8–9 are not necessarily the same as the current device | ❓ |
| IPDS-4-1354 | The “Supported Device Resolutions Self-Defining Field” lists the current device resolutions. | ❓ |
| IPDS-4-1355 | The Storage Pools self-defining field specifies storage pools within the printer. Each storage pool is defined with an entry that specifies total storage and the objects that are stored within the pool. | ❓ |
| IPDS-4-1356 | 0–1 | UBIN | SDF length | X'0004' plus length of zero or more storage-pool entries | Length of this self-defining field, including this length field; maximum length X'7FFF' | ❓ |
| IPDS-4-1357 | 2–3 | CODE | SDF ID | X'0004' | Storage Pools self-defining field ID | ❓ |
| IPDS-4-1358 | Zero or more storage-pool entries in the following format: | ❓ |
| IPDS-4-1359 | + 0 | UBIN | Entry length | X'0B'–X'FF' (odd values) | Length of the entry, including this length field | ❓ |
| IPDS-4-1360 | + 1 | CODE | Entry ID | X'01' | Entry ID | ❓ |
| IPDS-4-1361 | + 2 | CODE | Storage pool ID | X'00'–X'FF' | Storage pool ID | ❓ |
| IPDS-4-1362 | + 3–6 | UBIN | Empty size | X'00000000' – X'FFFFFFFF' | Size of the storage pool, in bytes, when empty | ❓ |
| IPDS-4-1363 | + 7–10 | | | X'00000000' | Reserved | ❓ |
| IPDS-4-1364 | Zero or more object ID entries in the following format: | ❓ |
| IPDS-4-1365 | ++ 0–1 | CODE | Object ID | | The ID of an object that is stored in this storage pool. If no object IDs are present, all supported objects that are not specified in other storage pool entries are stored in this pool. Only one of the various storage pools reported may use this default reporting format. | ❓ |
| IPDS-4-1366 | X'0007' | Symbol sets | ❓ |
| IPDS-4-1367 | X'0011' | Page graphics data | ❓ |
| IPDS-4-1368 | X'0012' | Page image data | ❓ |
| IPDS-4-1369 | X'0013' | Page text data | ❓ |
| IPDS-4-1370 | X'0014' | Page bar code data | ❓ |
| IPDS-4-1371 | X'0021' | Overlay graphics data | ❓ |
| IPDS-4-1372 | X'0022' | Overlay image data | ❓ |
| IPDS-4-1373 | X'0023' | Overlay text data | ❓ |
| IPDS-4-1374 | X'0024' | Overlay bar code data | ❓ |
| IPDS-4-1375 | X'0031' | Page segment graphics data | ❓ |
| IPDS-4-1376 | X'0032' | Page segment image data | ❓ |
| IPDS-4-1377 | X'0033' | Page segment text data | ❓ |
| IPDS-4-1378 | X'0034' | Page segment bar code data | ❓ |
| IPDS-4-1379 | X'0040' | Single-byte coded-font index tables | ❓ |
| IPDS-4-1380 | X'0041' | Single-byte coded-font descriptors | ❓ |
| IPDS-4-1381 | X'0042' | Single-byte coded-font patterns | ❓ |
| IPDS-4-1382 | X'0048' | Double-byte coded-font index tables | ❓ |
| IPDS-4-1383 | X'0049' | Double-byte coded-font descriptors | ❓ |
| IPDS-4-1384 | X'004A' | Double-byte coded-font patterns | ❓ |
| IPDS-4-1385 | X'0050' | Code pages | ❓ |
| IPDS-4-1386 | X'0060' | Font character sets | ❓ |
| IPDS-4-1387 | X'0070' | Coded fonts | ❓ |
| IPDS-4-1388 | transition, must be communicated to the host by returning an action code X'1D' NACK. | ❓ |
| IPDS-4-1389 | 0–1 | UBIN | SDF length | X'0006' – X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1390 | 2–3 | CODE | SDF ID | X'0006' | Installed Features self-defining field ID | ❓ |
| IPDS-4-1391 | One or more feature IDs in the following format: | ❓ |
| IPDS-4-1392 | + 0–1 | CODE | Feature ID | X'0100'<br>X'0200'<br>X'0201'<br>X'0202'<br>X'0300'<br>X'0400'<br>X'0600'<br>X'0700'<br>X'0800'<br>X'0900'<br>X'0B00'<br>X'0C00'<br>X'0D00' | **Features that are currently installed in the printer:**<br>Duplex<br>Manual two-channel switch<br>Tightly coupled two-channel switch<br>Retired item 39<br>Cut-sheet output<br>Retired item 40<br>Offset stacker<br>Envelopes<br>MICR: capable of printing toned pels that are impregnated with a magnetic material<br>Burster-trimmer-stacker or cutter-trimmer-stacker<br>Continuous-forms output<br>Continuous-forms separation capability<br>PTOCA text decryption capability | ❓ |
| IPDS-4-1393 | Note: The absence of both X'0300' and X'0B00' specifies that continuous-forms output is installed. | ❓ |
| IPDS-4-1394 | 0–1 | UBIN | SDF length | X'0006' – X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1395 | 2–3 | CODE | SDF ID | X'0007' | Available Features self-defining field ID | ❓ |
| IPDS-4-1396 | One or more feature IDs in the following format: | ❓ |
| IPDS-4-1397 | + 0–1 | CODE | Feature ID | X'0100'<br>X'0200'<br>X'0201'<br>X'0202'<br>X'0300'<br>X'0400'<br>X'0600'<br>X'0700'<br>X'0800'<br>X'0900'<br>X'0B00'<br>X'0C00'<br>X'0D00' | **Features that are currently available in the printer:**<br>Duplex available from at least one media source<br>Manual two-channel switch<br>Tightly coupled two-channel switch<br>Retired item 43<br>Cut-sheet output<br>Retired item 44<br>Offset stacker<br>Envelopes available from at least one installed media source<br>MICR: capable of printing toned pels that are impregnated with a magnetic material<br>Burster-trimmer-stacker or cutter-trimmer-stacker<br>Continuous-forms output<br>Continuous-forms separation capability<br>PTOCA text decryption capability | ❓ |
| IPDS-4-1398 | 1. If duplex is designated as available in the available-features self-defining field, there must be at least one | ❓ |
| IPDS-4-1399 | 2. The absence of both X'0300' and X'0B00' specifies that continuous-forms output is available. | ❓ |
| IPDS-4-1400 | 3. If offset stacker is designated as available, there is at least one media destination that can jog. If a media | ❓ |
| IPDS-4-1401 | 4. Feature ID X'0800' indicates that the printer is currently enabled to print using a MICR material. Some | ❓ |
| IPDS-4-1402 | individual media source or media destination; these features include: manual two-channel switch, tightly | ❓ |
| IPDS-4-1403 | trimmer-stacker, continuous-forms output, continuous-forms separation capability. | ❓ |
| IPDS-4-1404 | The Resident Symbol-Set Support self-defining field specifies that symbol sets are resident in the printer. | ❓ |
| IPDS-4-1405 | 0–1 | UBIN | SDF length | X'000E' to end of SDF | Length of this self-defining field, including this length field; maximum length X'7FFE' | ❓ |
| IPDS-4-1406 | 2–3 | CODE | SDF ID | X'0008' | Resident Symbol-Set Support Self-Defining Field ID | ❓ |
| IPDS-4-1407 | One or more Resident Symbol-Set Repeating Group Lists in the following format: | ❓ |
| IPDS-4-1408 | + 0 | UBIN | Length | X'0A'–X'FE' | Total Length of Code Page/Font ID Repeating Group List, including this length field | ❓ |
| IPDS-4-1409 | + 1 | CODE | Code page ID | X'01' | Code Page Support ID | ❓ |
| IPDS-4-1410 | + 2 | UBIN | Code page list length | X'04'–X'F8' | Length of Code Page List, including this length field | ❓ |
| IPDS-4-1411 | + 3 | UBIN | Entry length | X'02' | Length of Code Page Repeating Group Entry | ❓ |
| IPDS-4-1412 | One or more Code Page Global IDs (CPGIDs) in the following format: | ❓ |
| IPDS-4-1413 | ++ 0–1 | CODE | CPGID | X'0001' – X'FFFE' | A Code Page Global Id. This list specifies all of the resident code pages that are available in each of the fonts that are specified in the following Font ID List | ❓ |
| IPDS-4-1414 | One matching Font ID List for each Code Page List in the following format: | ❓ |
| IPDS-4-1415 | + 0 | UBIN | Font ID list length | X'04'–X'F8' | Length of Font ID List, including this length field | ❓ |
| IPDS-4-1416 | + 1 | UBIN | Entry length | X'02' | Length of Font ID Repeating Group Entry | ❓ |
| IPDS-4-1417 | One or more Font Typeface Global IDs (FGIDs) in the following format: | ❓ |
| IPDS-4-1418 | ++ 0–1 | CODE | FGID | X'0001' – X'FFFE' | A Font Typeface Global Id. This list specifies all of those fonts in which each of the preceding code pages is supported. | ❓ |
| IPDS-4-1419 | The Print-Quality Support self-defining field specifies the minimum values for print quality supported by the printer. This field need not be returned by printers that have only one print quality. | ❓ |
| IPDS-4-1420 | 0–1 | UBIN | SDF length | X'0005'–X'0102' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1421 | 2–3 | CODE | SDF ID | X'0009' | Print-Quality Support self-defining field ID | ❓ |
| IPDS-4-1422 | One or more print quality boundaries in the following format: | ❓ |
| IPDS-4-1423 | + 0 | UBIN | Boundary | X'01'–X'FE' | The lower boundary of an implemented print quality, as specified by the Print-Quality Control order in the Execute Order Anystate command. See “XOA Print-Quality Control”. | ❓ |
| IPDS-4-1424 | The Execute Order Anystate RRL RT & RIDF Support self-defining field specifies the combinations of resource types and resource ID formats that the printer supports in an XOA-RRL command. | ❓ |
| IPDS-4-1425 | 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1426 | 2–3 | CODE | SDF ID | X'000A' | XOA-RRL RT & RIDF Support Self-Defining Field ID | ❓ |
| IPDS-4-1427 | One or more entries in the following format: The list of entries identifies those RRL query combinations to which the printer responds with a nonzero Resource Type reply. A Resource Type reply of zero means that the queried Resource Type, Resource ID Format, or Resource ID are unknown, unsupported, or inconsistent. | ❓ |
| IPDS-4-1428 | + 0 | CODE | RT | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'11'<br>X'12'<br>X'20'<br>X'40'<br>X'41'<br>X'42'<br>X'FF' | **A supported resource type:**<br>Single-byte LF1-type and LF2-type coded font<br>Double-byte LF1-type coded font<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Device-version code page<br>Font character set<br>Single-byte coded font index<br>Double-byte coded font section index<br>Coded font<br>Graphic character set supported in a font character set<br>Specific code page<br>Saved page group<br>Data object resource<br>Data-object font<br>Data-object-font components<br>All-resources resource type | ❓ |
| IPDS-4-1429 | + 1 | CODE | RIDF | X'00'<br>X'03'<br>X'08'<br>X'09' | **A supported resource ID format:**<br>Host-Assigned Resource ID<br>GRID-parts format<br>Variable-length Group ID (X'00') triplet<br>Object-OID format | ❓ |
| IPDS-4-1430 | X'0100'—single-byte LF1-type or LF2-type coded font queried by Host-Assigned Resource ID; implicit in | ❓ |
| IPDS-4-1431 | X'0200'—double-byte LF1-type coded font queried by Host-Assigned Resource ID; implicit in support of | ❓ |
| IPDS-4-1432 | X'0400'—page segment queried by Host-Assigned Resource ID; implicit in support of PS1 | ❓ |
| IPDS-4-1433 | X'0500'—overlay queried by Host-Assigned Resource ID; implicit in support of OL1 | ❓ |
| IPDS-4-1434 | X'0600'—device-version code page queried by Host-Assigned ID; implicit in support of the LF3 and LF4 | ❓ |
| IPDS-4-1435 | X'0700'—font character set queried by Host-Assigned ID; implicit in support of the LF3 subset | ❓ |
| IPDS-4-1436 | X'1000'—coded font queried by Host-Assigned ID; implicit in support of the LF3 subset | ❓ |
| IPDS-4-1437 | X'1200'—specific code page queried by Host-Assigned ID; implicit in support of the LF3 and LF4 subsets | ❓ |
| IPDS-4-1438 | This self-defining field specifies the combinations of Resource Types and Resource ID Formats supported by the printer, within the Activate Resource command. If this self-defining field is returned, the printer must also return the AR-supported vector in the Sense Type and Model reply. | ❓ |
| IPDS-4-1439 | 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1440 | 2–3 | CODE | SDF ID | X'000B' | Activate Resource RT & RIDF Support Self-Defining Field ID | ❓ |
| IPDS-4-1441 | One or more entries in the following format: These entries specify available AR command support. The first byte of each entry identifies a resource type; the second byte of each entry identifies a resource ID format. | ❓ |
| IPDS-4-1442 | + 0 | CODE | RT | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'10'<br>X'40'<br>X'41'<br>X'42' | **A supported resource type:**<br>Single-byte LF1-type and LF2-type coded font<br>Retired item 45<br>Double-byte LF1-type coded-font section<br>Page segment<br>Overlay<br>Code page<br>Font character set<br>Single-byte LF1-type coded-font index<br>Double-byte LF1-type coded-font section index<br>Coded font<br>Data object resource<br>Data-object font<br>Data-object-font components | ❓ |
| IPDS-4-1443 | + 1 | CODE | RIDF | X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'09'<br>X'0A' | **A supported resource ID format:**<br>GRID-parts format<br>Remote PrintManager MVS naming format<br>Extended Remote PrintManager MVS naming format<br>MVS Host Unalterable Remote Font Environment<br>Coded-font format<br>Object-OID format<br>Data-object-font format | ❓ |
| IPDS-4-1444 | This self-defining field lists the medium modification IDs that are currently supported by the XOH-SMM command. If this self-defining field is returned, the printer must also return the Select-Medium-Modifications-support property ID (X'900E') in the Sense Type and Model reply. | ❓ |
| IPDS-4-1445 | 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1446 | 2–3 | CODE | SDF ID | X'000D' | Medium Modifications ID Support Self-Defining Field ID | ❓ |
| IPDS-4-1447 | One or more entries in the following format: | ❓ |
| IPDS-4-1448 | + 0–1 | CODE | Medium modification ID | Any ID that is valid in the XOH-SMM command | ID of a currently-supported medium modification | ❓ |
| IPDS-4-1449 | Note: Medium modification ID X'A0FF' should not be returned in the XOH-OPC reply unless there is at least one other supported fixed medium information modification ID. X'A0FF' is used by the host only if it is returned in the XOH-OPC reply. | ❓ |
| IPDS-4-1450 | 35 for a comparison of the two self-defining fields. | ❓ |
| IPDS-4-1451 | plus the additional bar code type and modifier combinations listed in the self-defining field. | ❓ |
| IPDS-4-1452 | 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1453 | 2–3 | CODE | SDF ID | X'000E' | Common Bar Code Type/Modifier Self-Defining Field ID | ❓ |
| IPDS-4-1454 | One or more entries in the following format: | ❓ |
| IPDS-4-1455 | + 0 | CODE | Combination | X'0D'<br>X'11'<br>X'18'<br>X'1A'<br>X'1B'<br>X'1C'<br>X'1D'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'23'<br>X'24'<br>X'86'<br>X'87'<br>X'8C'<br>X'91'<br>X'92'<br>X'93'<br>X'96'<br>X'97'<br>X'98'<br>X'9A' | **Bar code type/modifier combinations that are not in the common set:**<br>Codabar, modifier-byte options X'01' and X'02'<br>Code 128, modifier-byte option X'02'<br>POSTNET (deprecated), modifier-byte options X'00' through X'03'<br>RM4SCC, modifier-byte option X'00'<br>Japan Postal Bar Code, modifier-byte options X'00' and X'01'<br>Data Matrix, modifier-byte option X'00'<br>MaxiCode, modifier-byte option X'00'<br>PDF417, modifier-byte options X'00' and X'01'<br>Australia Post Bar Code, modifier-byte options X'01'–X'08'<br>QR Code, modifier-byte option X'02'<br>Code 93, modifier-byte option X'00'<br>Intelligent Mail Barcode, modifier-byte options X'00' through X'03'<br>Royal Mail RED TAG (deprecated), modifier-byte option X'00'<br>GS1 DataBar, modifier-byte options X'00'–X'04' and X'11'–X'1B'<br>UPC–Two-digit Supplemental, modifier-byte options X'01' and X'02'<br>UPC–Five-digit Supplemental, modifier-byte options X'01' and X'02'<br>Interleaved 2-of-5, modifier-byte options X'03' and X'04'<br>Code 128, modifier-byte option X'03'<br>Code 128, modifier-byte option X'04'<br>Code 128 (Intelligent Mail Container Barcode), modifier-byte option X'05'<br>EAN Two-digit Supplemental, modifier-byte option X'01'<br>EAN Five-digit Supplemental, modifier-byte option X'01'<br>POSTNET (PLANET, deprecated), modifier-byte option X'04'<br>RM4SCC, modifier-byte option X'01' | ❓ |
| IPDS-4-1456 | Table 34**. Common Values for Bar Code Types and Modifiers | ❓ |
| IPDS-4-1457 | X'01' | 3-of-9 code | X'01' and X'02' | ❓ |
| IPDS-4-1458 | X'02' | MSI | X'01' through X'09' | ❓ |
| IPDS-4-1459 | X'03' | UPC/CGPC, Version A | X'00' | ❓ |
| IPDS-4-1460 | X'05' | UPC/CGPC, Version E | X'00' | ❓ |
| IPDS-4-1461 | X'06' | UPC–Two-digit Supplemental | X'00' | ❓ |
| IPDS-4-1462 | X'07' | UPC–Five-digit Supplemental | X'00' | ❓ |
| IPDS-4-1463 | X'08' | EAN 8 (includes JAN short) | X'00' | ❓ |
| IPDS-4-1464 | X'09' | EAN 13 (includes JAN standard) | X'00' | ❓ |
| IPDS-4-1465 | X'0A' | Industrial 2-of-5 | X'01' and X'02' | ❓ |
| IPDS-4-1466 | X'0B' | Matrix 2-of-5 | X'01' and X'02' | ❓ |
| IPDS-4-1467 | X'0C' | Interleaved 2-of-5 | X'01' and X'02' | ❓ |
| IPDS-4-1468 | X'16' | EAN Two-digit Supplemental | X'00' | ❓ |
| IPDS-4-1469 | X'17' | EAN Five-digit Supplemental | X'00' | ❓ |
| IPDS-4-1470 | Reference* for a description of the BCOCA bar code types and modifiers. | ❓ |
| IPDS-4-1471 | with older presentation services programs that only recognize X'000E'. | ❓ |
| IPDS-4-1472 | 0–1 | UBIN | SDF length | X'0006'–X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1473 | 2–3 | CODE | SDF ID | X'000F' | Bar Code Type/Modifier Self-Defining Field ID | ❓ |
| IPDS-4-1474 | 4–5 | CODE | BCOCA subset | X'FF10'<br>X'FF20' | **BCOCA subset used as base for this SDF:**<br>BCD1<br>BCD2 | ❓ |
| IPDS-4-1475 | Zero or more two-byte entries in the following format: | ❓ |
| IPDS-4-1476 | + 0 | CODE | Type | X'06'<br>X'07'<br>X'0A'<br>X'0B'<br>X'0C'<br>X'0D'<br>X'11'<br>X'16'<br>X'17'<br>X'18'<br>X'1A'<br>X'1B'<br>X'1C'<br>X'1D'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'23'<br>X'24'<br>X'25'<br>X'26' | **Bar code type:**<br>UPC - Two-digit Supplemental<br>UPC - Five-digit Supplemental<br>Industrial 2-of-5<br>Matrix 2-of-5<br>Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5<br>Codabar, 2-of-7 and AIM USS-Codabar<br>Code 128 – GS1-128, UCC/EAN 128, Intelligent Mail Container Barcode, Intelligent Mail Package Barcode, and AIM USS-128<br>EAN Two-digit Supplemental<br>EAN Five-digit Supplemental<br>POSTNET and PLANET (deprecated)<br>RM4SCC and Dutch KIX<br>Japan Postal Bar Code<br>Data Matrix (2D bar code)<br>MaxiCode (2D bar code)<br>PDF417 (2D bar code)<br>Australia Post Bar Code<br>QR Code, QR Code with Image (2D bar code)<br>Code 93<br>Intelligent Mail Barcode<br>Royal Mail RED TAG (deprecated)<br>GS1 DataBar<br>Royal Mail Mailmark<br>Aztec Code | ❓ |
| IPDS-4-1477 | + 1 | CODE | Code value for modifiers | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'12'<br>X'80'<br>X'81'<br>X'82'<br>X'90'<br>X'91'<br>X'A0' | **Bar code modifier(s):**<br>**X'00' for the following types:**<br>Code 93, Data Matrix (2D bar code), MaxiCode (2D bar code), RM4SCC, Royal Mail RED TAG (deprecated)<br>**X'01' for the following types:**<br>Data Matrix (2D bar code), Dutch KIX, EAN Five-digit Supplemental, EAN Two-digit Supplemental<br>**X'02' for the following types:**<br>Code 128 – AIM USS-128, QR Code (2D bar code)<br>**X'03' for the following type:**<br>Code 128 – UCC/EAN 128<br>**X'04' for the following types:**<br>Code 128 – GS1-128 and UCC/EAN 128, PLANET (deprecated)<br>**X'05' for the following type:**<br>Intelligent Mail Container Barcode<br>**X'06' for the following type:**<br>Intelligent Mail Package Barcode<br>**X'12' for the following type:**<br>QR Code with Image (2D bar code)<br>**X'80' (X'00' and X'01') for the following types:**<br>Japan Postal Bar Code, PDF417 (2D bar code), Royal Mail Mailmark<br>**X'81' (X'00' through X'03') for the following types:**<br>Aztec Code, Intelligent Mail Barcode, POSTNET (deprecated)<br>**X'82' (X'00' through X'04' and X'11' through X'1B') for the following type:**<br>GS1 DataBar<br>**X'90' (X'01' and X'02') for the following types:**<br>Codabar, 2-of-7 and AIM USS-Codabar, Industrial 2-of-5, Matrix 2-of-5, UPC - Five-digit Supplemental, UPC - Two-digit Supplemental<br>**X'91' (X'01' through X'08') for the following type:**<br>Australia Post Bar Code<br>**X'A0' (X'03' and X'04') for the following type:**<br>Interleaved 2-of-5, ITF-14 and AIM USS-I 2/5 (to show Bearer Bars) | ❓ |
| IPDS-4-1478 | 0 , 90 , 180 , and 270 object-area orientation support | ❓ |
| IPDS-4-1479 | UPC Two-digit Supplemental - modifier X'00' | ❓ |
| IPDS-4-1480 | - X'000E' – Deprecated (Common Bar Code Type/Modifier self-defining field) | ❓ |
| IPDS-4-1481 | - X'000F' – Bar Code Type/Modifier self-defining field | ❓ |
| IPDS-4-1482 | Table 35**. Relationship Between SDF X'000E' and X'000F' | ❓ |
| IPDS-4-1483 | Type** | **Modifier** | **For SDF X'000E'** | **For SDF X'000F'** | ❓ |
| IPDS-4-1484 | Code 39 (3-of-9 Code) and AIM USS-39 | X'01' and X'02' | in common set | in BCD1 | ❓ |
| IPDS-4-1485 | MSI (modified Plessey code) | X'01' through X'09' | in common set | in BCD1 | ❓ |
| IPDS-4-1486 | UPC/CGPC – Version A | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1487 | UPC/CGPC – Version E | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1488 | UPC - Two-digit Supplemental (Periodicals) | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1489 | X'01' and X'02' | X'86' | X'0690' | ❓ |
| IPDS-4-1490 | UPC - Five-digit Supplemental (Paperbacks) | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1491 | X'01' and X'02' | X'87' | X'0790' | ❓ |
| IPDS-4-1492 | EAN-8 (includes JAN-short) | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1493 | EAN-13 (includes JAN-standard) | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1494 | Industrial 2-of-5 | X'01' and X'02' | in common set | X'0A90' | ❓ |
| IPDS-4-1495 | Matrix 2-of-5 | X'01' and X'02' | in common set | X'0B90' | ❓ |
| IPDS-4-1496 | Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5 | X'01' and X'02' | in common set | in BCD1 | ❓ |
| IPDS-4-1497 | Bearer Bars – Interleaved 2-of-5, ITF-14, and AIM USS-I 2/5 | X'03' and X'04' | X'8C' | X'0CA0' | ❓ |
| IPDS-4-1498 | Codabar, 2-of-7 and AIM USS-Codabar | X'01' and X'02' | X'0D' | X'0D90' | ❓ |
| IPDS-4-1499 | Code 128 | X'02' (AIM USS-128) | X'11' | X'1102' | ❓ |
| IPDS-4-1500 | X'03' (UCC/EAN 128) | X'91' | X'1103' | ❓ |
| IPDS-4-1501 | X'04' (GS1-128 and UCC/EAN 128) | X'92' | X'1104' | ❓ |
| IPDS-4-1502 | X'05' (Intelligent Mail Container Barcode) | X'93' | X'1105' | ❓ |
| IPDS-4-1503 | X'06' (Intelligent Mail Package Barcode) | not supported | X'1106' | ❓ |
| IPDS-4-1504 | EAN - Two-digit Supplemental | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1505 | X'01' | X'96' | X'1601' | ❓ |
| IPDS-4-1506 | EAN - Five-digit Supplemental | X'00' | in common set | in BCD1 | ❓ |
| IPDS-4-1507 | X'01' | X'97' | X'1701' | ❓ |
| IPDS-4-1508 | POSTNET (deprecated) | X'00' through X'03' | X'18' | X'1881' | ❓ |
| IPDS-4-1509 | X'04' (PLANET, deprecated) | X'98' | X'1804' | ❓ |
| IPDS-4-1510 | RM4SCC | X'00' | X'1A' | X'1A00' | ❓ |
| IPDS-4-1511 | X'01' (Dutch KIX) | X'9A' | X'1A01' | ❓ |
| IPDS-4-1512 | Japan Postal Bar Code | X'00' and X'01' | X'1B' | X'1B80' | ❓ |
| IPDS-4-1513 | Data Matrix and GS1 DataMatrix (2D bar code) | X'00' | X'1C' | X'1C00' | ❓ |
| IPDS-4-1514 | X'01' (including DMRE) | not supported | X'1C01' | ❓ |
| IPDS-4-1515 | MaxiCode (2D bar code) | X'00' | X'1D' | X'1D00' | ❓ |
| IPDS-4-1516 | PDF417 (2D bar code) | X'00' and X'01' | X'1E' | X'1E80' | ❓ |
| IPDS-4-1517 | Australia Post Bar Code | X'01' through X'08' | X'1F' | X'1F91' | ❓ |
| IPDS-4-1518 | QR Code (2D bar code) | X'02' | X'20' | X'2002' | ❓ |
| IPDS-4-1519 | X'12' (QR Code with Image) | not supported | X'2012' | ❓ |
| IPDS-4-1520 | Code 93 | X'00' | X'21' | X'2100' | ❓ |
| IPDS-4-1521 | Intelligent Mail Barcode | X'00' through X'03' | X'22' | X'2281' | ❓ |
| IPDS-4-1522 | Royal Mail RED TAG (deprecated) | X'00' | X'23' | X'2300' | ❓ |
| IPDS-4-1523 | GS1 DataBar | X'00' through X'04' and X'11' through X'1B' | X'24' | X'2482' | ❓ |
| IPDS-4-1524 | Royal Mail Mailmark | X'00' and X'01' | not supported | X'2580' | ❓ |
| IPDS-4-1525 | Aztec Code (2D bar code) | X'00' through X'03' | not supported | X'2681' | ❓ |
| IPDS-4-1526 | destination is replaced by a UP3I tupel). | ❓ |
| IPDS-4-1527 | 0–1 | UBIN | SDF length | X'000A'–X'7FFE' | Length of this self-defining field, including this length field; must be in increments of 4 | ❓ |
| IPDS-4-1528 | 2–3 | CODE | SDF ID | X'0010' | Media-Destinations self-defining field ID | ❓ |
| IPDS-4-1529 | 4–5 | CODE | Default | X'0001'–X'FFFF' | Default media-destination ID | ❓ |
| IPDS-4-1530 | One or more entries in the following format: | ❓ |
| IPDS-4-1531 | + 0–1 | CODE | First | X'0001'–X'FFFF' | First number in a range of available, contiguous media-destination IDs | ❓ |
| IPDS-4-1532 | + 2–3 | CODE | Last | X'0001'–X'FFFF' | Last number in a range of available, contiguous media-destination IDs; this ID must be greater than or equal to the value specified in bytes +0–1 for this set | ❓ |
| IPDS-4-1533 | This self-defining field specifies the group operations supported by a printer, pre-processor, or post-processor in the XOH Specify Group Operation command. If this self-defining field is returned, the printer must also return the XOH-DGB-supported property pair (X'9004') and the XOH-SGO-supported property pair (X'9003') in the Device-Control command-set vector of an STM reply. | ❓ |
| IPDS-4-1534 | Support for a group operation also implies support for all triplets defined for that group operation. The relationship between group operations and triplets is shown in Table 32. | ❓ |
| IPDS-4-1535 | 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1536 | 2–3 | CODE | SDF ID | X'0012' | Supported Group Operations Self-Defining Field ID | ❓ |
| IPDS-4-1537 | One or more entries in the following format: | ❓ |
| IPDS-4-1538 | + 0 | CODE | Operation | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06' | **Group Operation supported in the XOH-SGO command:**<br>Keep group together as a print unit<br>Keep group together for microfilm output<br>Save pages<br>Finish<br>Identify named group<br>Keep group together as a recovery unit | ❓ |
| IPDS-4-1539 | 1. Exception ID X'0100..00' (normal printer restart) exists when a group operation is enabled or disabled. | ❓ |
| IPDS-4-1540 | 2. The XOH-OPC Finishing Operations self-defining field (X'0018') lists the currently supported finishing | ❓ |
| IPDS-4-1541 | 3. The Keep-Group-Together-as-a-Recovery-Unit self-defining field identifies the maximum number of sheets | ❓ |
| IPDS-4-1542 | subsequent product identifier describes. | ❓ |
| IPDS-4-1543 | 0–1 | UBIN | SDF length | X'0007' to end of SDF | Length of this self-defining field, including this length field; maximum length X'7FFF' | ❓ |
| IPDS-4-1544 | 2–3 | CODE | SDF ID | X'0013' | Product Identifier self-defining field ID | ❓ |
| IPDS-4-1545 | One or more self-defining product-identifier parameters in the following format: | ❓ |
| IPDS-4-1546 | + 0 | UBIN | Parameter length | X'03' to end of parameter | Length of this Product-identifier parameter, including this length field; refer to each parameter description for information about valid lengths | ❓ |
| IPDS-4-1547 | + 1–2 | CODE | Parameter ID | X'0000'<br>X'0001'<br>X'0002'<br>X'0003'<br>X'0004' | **Product-identifier parameter ID:**<br>Retired item 50<br>Unique Product Identifier<br>This product identifier parameter ID indicates that bytes 3 to end contain information that can be used to uniquely identify the printer.<br>IPDS Intermediate Device Identifier<br>This product identifier parameter ID indicates that bytes 3 to end contain information that can be used to uniquely identify an IPDS intermediate device.<br>Printer name<br>Subsystem information | ❓ |
| IPDS-4-1548 | +3 to end | | Parameter value | Depends on parameter ID | ❓ |
| IPDS-4-1549 | appropriate code point to character association. | ❓ |
| IPDS-4-1550 | There is no parameter value for this parameter ID. The parameter length must be X'03'. | ❓ |
| IPDS-4-1551 | identifier in the following format: | ❓ |
| IPDS-4-1552 | + 3–8 | CHAR | Device type | | Device type of the printer in the form of six (6) EBCDIC characters that correspond to the device type imprinted on the serial number plate that is physically attached to the printer. This field is right-justified and padded with X'F0' if necessary. | ❓ |
| IPDS-4-1553 | + 9–11 | CHAR | Model number | | Model number of the printer in the form of three (3) EBCDIC characters that correspond to the model number imprinted on the serial number plate that is physically attached to the printer. This field is right-justified and padded with X'F0' if necessary. | ❓ |
| IPDS-4-1554 | + 12–14 | CHAR | Manufacturer | | Name of the manufacturer in the form of three (3) EBCDIC characters. If this information is not available, bytes 12–14 contain binary zeros. A registry of manufacturer values (AFPC Company Abbreviation Registry) is kept on the AFP Consortium website www.afpcinc.org. | ❓ |
| IPDS-4-1555 | + 15–16 | CHAR | Plant of manufacture | | Plant of manufacture in the form of two (2) EBCDIC characters. If this information is not available, bytes 15–16 contain binary zeros. | ❓ |
| IPDS-4-1556 | + 17–28 | CHAR | Sequence number | | Sequence number of the printer in the form of twelve (12) EBCDIC characters. This field is right-justified and padded with X'F0' if necessary. The sequence number along with the plant of manufacture make up the serial number imprinted on the serial number plate that is physically attached to the printer. If this information is not available, bytes 17–28 contain binary zeros. | ❓ |
| IPDS-4-1557 | + 29–30 | UNDF | Tag | | Used to differentiate between devices whose IDs specified in bytes 3–28 are otherwise identical, as in the case of two print mechanisms on the same printer control unit. This field is set to X'0000' if this level of differentiation is unnecessary. | ❓ |
| IPDS-4-1558 | + 31–39 | CHAR | Engineering change level | | Engineering change (EC) level in the form of nine (9) EBCDIC characters that most closely reflect the implemented level of IPDS function in the printer. This field is right-justified and padded with X'F0' if necessary. | ❓ |
| IPDS-4-1559 | + 40 to end | UNDF | Device-specific information | | Zero (0) to sixteen (16) bytes of device-specific information with device-defined padding and justification. Device-specific information can be release or EC levels or any other data a product might wish to supply to identify its characteristics. (variable length) | ❓ |
| IPDS-4-1560 | 1. The device-type and model-number values returned in the OPC reply represent the actual device type and | ❓ |
| IPDS-4-1561 | 2. The device serial number is represented by a combination of the plant-of-manufacture and sequence- | ❓ |
| IPDS-4-1562 | device identifier in the following format: | ❓ |
| IPDS-4-1563 | + 3–4 | BITS | Intermediate-device characteristic flags | | **bit 0** Remote resource caching (B'0', B'1')<br>**bit 1** Remote job spooling (B'0', B'1')<br>**bit 2** Datastream transforms (B'0', B'1')<br>**bits 3–15** Reserved (B'0...0') | ❓ |
| IPDS-4-1564 | + 5–6 | CODE | Device type | X'0000'<br>X'0001'<br>X'0002'<br>X'0003'<br>X'0004'<br>X'0005'<br>X'0006'<br>X'0007' | **Type of IPDS intermediate device specified by a unique two-byte value:**<br>Remote PrintManager 2.0<br>Remote PrintManager 3.0<br>Distributed Print Function<br>Retired item 121<br>PSF Direct (IPDS passthru, similar to RPM 2.0)<br>PSF virtual printer<br>IPDS-to-PDF transform<br>Workstation Print Manager | ❓ |
| IPDS-4-1565 | + 7–15 | CHAR | Engineering change level | | Engineering change (EC) level in the form of nine (9) EBCDIC characters that most closely reflect the implemented level of IPDS function in the IPDS intermediate device. This field is right-justified and padded with X'F0' if necessary. If this information is not available, bytes 7–15 contain binary zeros. | ❓ |
| IPDS-4-1566 | + 16 | UBIN | Ordering parameter | | Indicates the logical position of the intermediate device in the host-to-printer configuration. An intermediate device sets this field to the value OP(r) + 1, where OP(r) is the value of the largest ordering parameter in an intermediate device identifier self-defining parameter that is received in an inbound XOH-OPC reply. The intermediate device closest to the printer sets this field to X'00'. | ❓ |
| IPDS-4-1567 | + 17 to end | UNDF | Device-specific information | | Zero (0) to sixteen (16) bytes of device-specific information with device-defined padding and justification. Device-specific information can be release or EC levels or any other data a product might wish to supply to identify its characteristics. (variable length) | ❓ |
| IPDS-4-1568 | + 3 to end | CHAR | Printer name | | External name of the printer in the form of a variable number of EBCDIC characters; names can be from 1 to 252 bytes long. This optional name should be supplied when the printer name is different from the device type. | ❓ |
| IPDS-4-1569 | subsystem of the product in the following format: | ❓ |
| IPDS-4-1570 | + 3 | UBIN | Name length | X'02'–X'FA' | Length of the subsystem name, including this length field | ❓ |
| IPDS-4-1571 | + 4 to end | CHAR | Subsystem name | | Name of the subsystem, using EBCDIC characters | ❓ |
| IPDS-4-1572 | ++ 0 | UBIN | EC level length | X'01'–X'F9' | Length of the subsystem EC level, including this length field | ❓ |
| IPDS-4-1573 | ++ 1 to end | CHAR | Subsystem EC level | | Engineering change (EC) level of the subsystem, using EBCDIC characters | ❓ |
| IPDS-4-1574 | +++ 0 | UBIN | Info length | X'01'–X'F9' | Length of the subsystem-specific information, including this length field | ❓ |
| IPDS-4-1575 | +++ 1 to end | UNDF | Subsystem-specific information | | Subsystem-specific information, in a device-defined format. This is any data a product might wish to supply to identify the subsystem's characteristics. | ❓ |
| IPDS-4-1576 | 1. Because the subsystem information fields are variable length, no padding or justification rules need be | ❓ |
| IPDS-4-1577 | 2. Multiple X'0004' entries can be used to report subsystem information for multiple subsystems. | ❓ |
| IPDS-4-1578 | This self-defining field lists the object containers supported by the printer and for each type of object indicates whether the object is supported in home state, in page or overlay state, or in all three states. The object-type OIDs also indicate whether an object container is a presentation object or a non-presentation object. Table 17 summarizes characteristics of the currently defined object containers. | ❓ |
| IPDS-4-1579 | Non-presentation object containers are downloaded in home state and are either used immediately (as in the case of a setup file) or are later invoked in page or overlay state (as in the case of a PostScript resource object). Presentation object containers can either be part of a page or overlay, or can be downloaded in home state and later included by means of the IDO command in page or overlay state. | ❓ |
| IPDS-4-1580 | 0–1 | UBIN | SDF length | X'0016' – X'7FA2' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1581 | 2–3 | CODE | SDF ID | X'0014' | Object-Container Type Support self-defining field ID | ❓ |
| IPDS-4-1582 | One or more type records in the following format:** | ❓ |
| IPDS-4-1583 | +0 | UBIN | Type record length | X'12'–X'F2' | Length of the type record, including this length field (in increments of 16) | ❓ |
| IPDS-4-1584 | +1 | CODE | Type | X'01'<br>X'02' | IPDS state in which the following list of registered object-type OIDs is supported:<br>X'01' Page or overlay state<br>X'02' Home state | ❓ |
| IPDS-4-1585 | One or more 16-byte registered object-type OIDs in the following format:** | ❓ |
| IPDS-4-1586 | +2–17 | CODE | Reg ID | | MO:DCA-registered object ID for the object container supported in the WOC. The ID is left-justified and padded on the right with zeroes. See the "Registered Object-Type OIDs" table below. | ❓ |
| IPDS-4-1587 | Registered Object-Type OIDs** | ❓ |
| IPDS-4-1588 | X'0607 2B12 0004 0101 0F00 0000 0000 0000' | Anacomp COM Setup File | ❓ |
| IPDS-4-1589 | X'0607 2B12 0004 0101 1000 0000 0000 0000' | Anacomp COM Tape Label Setup File | ❓ |
| IPDS-4-1590 | X'0607 2B12 0004 0101 1800 0000 0000 0000' | AnaStack Record Setup File | ❓ |
| IPDS-4-1591 | X'0607 2B12 0004 0101 3900 0000 0000 0000' | Color Mapping Table Setup File | ❓ |
| IPDS-4-1592 | X'0607 2B12 0004 0101 1400 0000 0000 0000' | EPS (Encapsulated PostScript) without transparency | ❓ |
| IPDS-4-1593 | X'0607 2B12 0004 0101 0D00 0000 0000 0000' | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-4-1594 | X'0607 2B12 0004 0101 3000 0000 0000 0000' | GIF (Graphics Interchange Format) | ❓ |
| IPDS-4-1595 | X'0607 2B12 0004 0101 1600 0000 0000 0000' | IOCA Tile Resource | ❓ |
| IPDS-4-1596 | X'0607 2B12 0004 0101 2F00 0000 0000 0000' | JPEG (Joint Photographic Experts Group) | ❓ |
| IPDS-4-1597 | X'0607 2B12 0004 0101 1700 0000 0000 0000' | AFPC JPEG Subset | ❓ |
| IPDS-4-1598 | X'0607 2B12 0004 0101 3A00 0000 0000 0000' | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-4-1599 | X'0607 2B12 0004 0101 4500 0000 0000 0000' | Non-OCA Resource object; see note 1 | ❓ |
| IPDS-4-1600 | X'0607 2B12 0004 0101 2200 0000 0000 0000' | PCL page object | ❓ |
| IPDS-4-1601 | X'0607 2B12 0004 0101 3F00 0000 0000 0000' | PDF multiple-page file without transparency | ❓ |
| IPDS-4-1602 | X'0607 2B12 0004 0101 4000 0000 0000 0000' | PDF multiple-page file with transparency | ❓ |
| IPDS-4-1603 | X'0607 2B12 0004 0101 1900 0000 0000 0000' | PDF single page without transparency | ❓ |
| IPDS-4-1604 | X'0607 2B12 0004 0101 3100 0000 0000 0000' | PDF single page with transparency | ❓ |
| IPDS-4-1605 | X'0607 2B12 0004 0101 1A00 0000 0000 0000' | PDF (Portable Document Format) Resource Object | ❓ |
| IPDS-4-1606 | X'0607 2B12 0004 0101 4100 0000 0000 0000' | PNG (Portable Network Graphics) | ❓ |
| IPDS-4-1607 | X'0607 2B12 0004 0101 2E00 0000 0000 0000' | AFPC PNG Subset | ❓ |
| IPDS-4-1608 | X'0607 2B12 0004 0101 4400 0000 0000 0000' | Resident Color Profile | ❓ |
| IPDS-4-1609 | X'0607 2B12 0004 0101 4200 0000 0000 0000' | SVG (Scalable Vector Graphics) | ❓ |
| IPDS-4-1610 | X'0607 2B12 0004 0101 0E00 0000 0000 0000' | AFPC SVG Subset; see note 2 | ❓ |
| IPDS-4-1611 | X'0607 2B12 0004 0101 3C00 0000 0000 0000' | TIFF (Tag Image File Format) | ❓ |
| IPDS-4-1612 | X'0607 2B12 0004 0101 3D00 0000 0000 0000' | AFPC TIFF Subset | ❓ |
| IPDS-4-1613 | X'0607 2B12 0004 0101 3E00 0000 0000 0000' | TIFF (Tag Image File Format) with transparency | ❓ |
| IPDS-4-1614 | X'0607 2B12 0004 0101 3500 0000 0000 0000' | TIFF (Tag Image File Format) without transparency | ❓ |
| IPDS-4-1615 | X'0607 2B12 0004 0101 3300 0000 0000 0000' | TIFF multiple-image file with transparency | ❓ |
| IPDS-4-1616 | X'0607 2B12 0004 0101 3800 0000 0000 0000' | TIFF multiple-image file without transparency | ❓ |
| IPDS-4-1617 | X'0607 2B12 0004 0101 2500 0000 0000 0000' | TrueType/OpenType Collection | ❓ |
| IPDS-4-1618 | X'0607 2B12 0004 0101 2400 0000 0000 0000' | TrueType/OpenType Font | ❓ |
| IPDS-4-1619 | X'0607 2B12 0004 0101 2600 0000 0000 0000' | UP3I Print Data | ❓ |
| IPDS-4-1620 | Retired Object-Type OIDs** | ❓ |
| IPDS-4-1621 | X'0607 2B12 0004 0101 2500 0000 0000 0000' | Retired item 136 | ❓ |
| IPDS-4-1622 | X'0607 2B12 0004 0101 2400 0000 0000 0000' | Retired item 137 | ❓ |
| IPDS-4-1623 | X'0607 2B12 0004 0101 2600 0000 0000 0000' | Retired item 138 | ❓ |
| IPDS-4-1624 | 1. Implementations that report support for the Non-OCA Resource object must support it for all supported | ❓ |
| IPDS-4-1625 | 2. Implementations that report support for the AFPC SVG Subset must also report the following: | ❓ |
| IPDS-4-1626 | Support for the Non-OCA Resource object in this self-defining field (the Object-Container Type Support | ❓ |
| IPDS-4-1627 | Property pair X'1209' (X'9C' triplet SVG support) in the Object Container command-set vector | ❓ |
| IPDS-4-1628 | Property pair X'120A' (DORE extender entry support) in the Object Container command-set vector | ❓ |
| IPDS-4-1629 | Property pair X'120D' (TrueType/OpenType fonts as secondary resources support) in the Object | ❓ |
| IPDS-4-1630 | property pair in note 4 for more information. | ❓ |
| IPDS-4-1631 | The DF Deactivation Types Supported self-defining field lists the optional deactivation types that are supported by the printer. These types are in addition to those listed as required in Table 24. | ❓ |
| IPDS-4-1632 | 0–1 | UBIN | SDF length | X'0005' – X'000A' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1633 | 2–3 | CODE | SDF ID | X'0015' | DF Deactivation Types Supported self-defining field ID | ❓ |
| IPDS-4-1634 | One or more entries in the following format: | ❓ |
| IPDS-4-1635 | +0 | CODE | Type | X'22'<br>X'50'<br>X'51'<br>X'5D'<br>X'5E'<br>X'5F' | Optional deactivation type:<br>Deactivate a font index for a double-byte coded font section<br>Deactivate a coded font<br>Deactivate a coded font and all associated components<br>Deactivate all resident coded fonts and all associated components<br>Deactivate all coded fonts<br>Deactivate all coded fonts and all associated components | ❓ |
| IPDS-4-1636 | must be returned in the XOH-OPC reply. | ❓ |
| IPDS-4-1637 | 0–1 | UBIN | SDF length | X'0005' – X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1638 | 2–3 | CODE | SDF ID | X'0016' | PFC Triplets Supported self-defining field ID | ❓ |
| IPDS-4-1639 | One or more triplet IDs in the following format: | ❓ |
| IPDS-4-1640 | +0 | CODE | Triplet ID | X'74'<br>X'75'<br>X'86'<br>X'88'<br>X'96' | Supported triplet ID:<br>Toner Saver triplet<br>Color Fidelity triplet<br>Text Fidelity triplet<br>Finishing Fidelity triplet<br>CMR Tag Fidelity triplet | ❓ |
| IPDS-4-1641 | The Printer Setup self-defining field lists all setup IDs that are currently active in the printer. There can be multiple IDs, each of which identifies a particular, implementation-defined setup in the printer or post-processor. These IDs can be used by a presentation services program to verify that a printer is properly set up for a particular print job. | ❓ |
| IPDS-4-1642 | An IPDS printer can support setup names (with the ASN and XOA-RSNL commands) or setup IDs, or both; the two functions do not necessarily interact. This Printer Setup self-defining field is not used for setup names. | ❓ |
| IPDS-4-1643 | Exception ID X'0108..00' is returned if one or more of the setups change. | ❓ |
| IPDS-4-1644 | 0–1 | UBIN | SDF length | X'0006'–X'FFFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1645 | 2–3 | CODE | SDF ID | X'0017' | Printer Setup self-defining field ID | ❓ |
| IPDS-4-1646 | One or more entries in the following format: | ❓ |
| IPDS-4-1647 | + 0–1 | CODE | Setup ID | X'0000'–X'FFFF' | Currently active setup ID | ❓ |
| IPDS-4-1648 | The Finishing Operations self-defining field lists all the different types of finishing operations that the printer supports with the Finishing Operation (X'85') triplet. Presence of this OPC self-defining field indicates support for the X'85' triplet. There can be multiple operation-description entries, each of which identifies a supported finishing operation type. Support for a finishing operation type does not imply support for all variations of that operation type. | ❓ |
| IPDS-4-1649 | 0–1 | UBIN | SDF length | X'0005'–X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1650 | 2–3 | CODE | SDF ID | X'0018' | Finishing Operations self-defining field ID | ❓ |
| IPDS-4-1651 | One or more operation-description entries in the following format: | ❓ |
| IPDS-4-1652 | + 0 | CODE | Operation type | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'0A'<br>X'0C'<br>X'0D'<br>X'0E'<br>X'0F'<br>X'12'<br>X'14'<br>X'18'<br>X'19'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'30'<br>X'31'<br>X'32' | **Supported finishing operations:**<br>Corner staple<br>Saddle-stitch out<br>Edge stitch<br>Fold in<br>Separation cut<br>Perforation cut<br>Z fold<br>Center-fold in<br>Trim after center fold or saddle stitch<br>Punch<br>Perfect bind<br>Ring bind<br>C-fold in<br>Accordion-fold in<br>Saddle-stitch in<br>Fold out<br>Center-fold out<br>Trim<br>C-fold out<br>Accordion-fold out<br>Double parallel-fold in<br>Double gate-fold in<br>Single gate-fold in<br>Double parallel-fold out<br>Double gate-fold out<br>Single gate-fold out | ❓ |
| IPDS-4-1653 | Exception ID X'0109..00' exists when a finishing operation is enabled or disabled. | ❓ |
| IPDS-4-1654 | This self-defining field reports the physical order and properties of the UP3I devices connected to the printer. One of these self-defining fields is returned for each possible paper path combination in the line of UP3I devices; the combination of devices is called a tupel. | ❓ |
| IPDS-4-1655 | 0–1 | UBIN | SDF length | X'0009' to end of SDF | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1656 | 2–3 | CODE | SDF ID | X'0019' | UP3I Tupel self-defining field ID | ❓ |
| IPDS-4-1657 | 4–5 | UBIN | Tupel ID | X'0001' – X'FFFF' | UP3I Tupel ID | ❓ |
| IPDS-4-1658 | 6 to end | | UP3I device information | | The information returned in this self-defining field is defined by UP3I and is described in the current UP3I Specification that is available at www.afpcinc.org. Refer to the section titled “Extension for the Intelligent Printer Data Stream (IPDS)”. | ❓ |
| IPDS-4-1659 | Exception ID X'0109..00' exists when a finishing operation is enabled or disabled. | ❓ |
| IPDS-4-1660 | This self-defining field reports the media attributes of all media that exist in the UP3I line. One of these self-defining fields is returned for each available IPDS media source for which there is UP3I information. | ❓ |
| IPDS-4-1661 | In the XOH-OPC reply, there must be a Printable-Area self-defining field for each media source. In addition, if UP3I information exists for the media source, a UP3I Paper Input Media self-defining field is specified to provide additional information. | ❓ |
| IPDS-4-1662 | It is good practice to specify the Printable-Area self-defining field for a media source before specifying the UP3I Paper Input Media self-defining field for that source. | ❓ |
| IPDS-4-1663 | 0–1 | UBIN | SDF length | X'0005' to end of SDF | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1664 | 2–3 | CODE | SDF ID | X'001A' | UP3I Paper Input Media self-defining field ID | ❓ |
| IPDS-4-1665 | 4 | CODE | Media source ID | X'00'–X'FF' | Media source ID as defined in the OPC Printable-Area self-defining field | ❓ |
| IPDS-4-1666 | 5 to end | | UP3I media information | | The information returned in this self-defining field is defined by UP3I and is described in the current UP3I Specification that is available at www.afpcinc.org. Refer to the section titled “Extension for the Intelligent Printer Data Stream (IPDS)”. | ❓ |
| IPDS-4-1667 | information in each pair of these self-defining fields that are related by a media source ID. | ❓ |
| IPDS-4-1668 | This self-defining field lists all colorants available in the printer. Colorants and combinations of colorants can be selected using a highlight-color value in the range X'0100'–X'FFFF' along with an indexed CMR. | ❓ |
| IPDS-4-1669 | 0–1 | UBIN | SDF length | X'000B' to end of SDF | Length of this self-defining field, including this length field; maximum length X'FFFF' | ❓ |
| IPDS-4-1670 | 2–3 | CODE | SDF ID | X'0021' | Colorant-Identification self-defining field ID | ❓ |
| IPDS-4-1671 | One or more Colorant-Identification entries in the following format:** | ❓ |
| IPDS-4-1672 | +0 | UBIN | Entry length | X'07'–X'FF' | Length of this entry, including this length field (odd values) | ❓ |
| IPDS-4-1673 | +1 | CODE | Entry type | X'01' | User-defined colorant name (UTF-16BE) | ❓ |
| IPDS-4-1674 | +2 | BITS | Colorant availability flags | | **bit 0** Front available (B'0' = Not available for front side; B'1' = Available for front side; can only be set if bit 6 is also set)<br>**bit 1** Back available (B'0' = Not available for back side; B'1' = Available for back side; can only be set if bit 7 is also set)<br>**bit 2** EPS/PDF (B'0' = Not available to EPS/PDF objects; B'1' = Available to EPS/PDF objects)<br>**bit 3** Reserved (B'0')<br>**bit 4** Front default (B'0' = Not used as default color for front side; B'1' = Used as default color for front side)<br>**bit 5** Back default (B'0' = Not used as default color for back side; B'1' = Used as default color for back side)<br>**bit 6** Front installed (B'0' = Not installed for front side; B'1' = Installed for front side)<br>**bit 7** Back installed (B'0' = Not installed for back side; B'1' = Installed for back side) | ❓ |
| IPDS-4-1675 | +3–4 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-4-1676 | +5 to end | CHAR | Colorant name | | Colorant name, using any UTF-16BE characters | ❓ |
| IPDS-4-1677 | 1. The front side of a sheet is the side on which front-side pages are printed; refer to the LCC command for | ❓ |
| IPDS-4-1678 | 2. EPS and PDF objects can use the PostScript DeviceN function to specify colorant names. The EPS/PDF | ❓ |
| IPDS-4-1679 | 3. The colorant name here is limited by the self-defining field to be 250 bytes in length (each Colorant- | ❓ |
| IPDS-4-1680 | Identification entry is limited to 255 bytes and there are 5 bytes taken before the name). It is also possible | ❓ |
| IPDS-4-1681 | AFPC_Device_Gray Device Gray | ❓ |
| IPDS-4-1682 | This self-defining field lists optional device-appearance values that are supported by the printer. A device appearance can be selected with the Device Appearance (X'97') triplet in a Set Presentation Environment (SPE) command. Support for the Device Appearance (X'97') triplet is indicated by property pair X'F206' in the Device-Control command-set vector of an STM reply. | ❓ |
| IPDS-4-1683 | 0–1 | UBIN | SDF length | X'0006'–X'FFFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1684 | 2–3 | CODE | SDF ID | X'0022' | Device-Appearance self-defining field ID | ❓ |
| IPDS-4-1685 | One or more appearance values in the following format: | ❓ |
| IPDS-4-1686 | + 0–1 | CODE | Appearance | X'0001' | Device-default monochrome appearance | ❓ |
| IPDS-4-1687 | appearance values also do not return this OPC self-defining field. | ❓ |
| IPDS-4-1688 | sheets include sheets containing pages and copies of such sheets. Support for this group operation is indicated by the Supported Group Operations self-defining field. | ❓ |
| IPDS-4-1689 | 0–1 | UBIN | SDF length | X'000B' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1690 | 2–3 | CODE | SDF ID | X'0024' | Keep-Group-Together-as-a-Recovery-Unit self-defining field ID | ❓ |
| IPDS-4-1691 | 4–5 | UBIN | Maximum number of sheets | X'0000'<br>X'0002' – X'FFFE'<br>X'FFFF' | Number of sheets allowed within a recovery-unit group:<br>Value not specified; refer to maximum total group length value<br>Maximum number of sheets that can be kept together as a recovery unit<br>Maximum value is finite, but larger than 65,534 sheets | ❓ |
| IPDS-4-1692 | 6 | CODE | Unit base | X'00'<br>X'01' | Unit base for this self-defining field:<br>Ten inches<br>Ten centimeters | ❓ |
| IPDS-4-1693 | 7–8 | UBIN | UPUB | X'0001' – X'7FFF' | Units per unit base value for this self-defining field | ❓ |
| IPDS-4-1694 | 9–10 | UBIN | Maximum total group length | X'0000'<br>X'0001' – X'7FFF' | Maximum length of media that can be kept together as a recovery unit:<br>Value not specified; refer to maximum number of sheets value<br>Total length of media that can be kept together as a recovery unit<br>Note: For a printer using cut-sheet media, the value is the sum of the sheet lengths. For a printer using continuous-forms media, the length can be changed with the XOH Set Media Size command. Exception ID X'0101..00' exists whenever media size changes. | ❓ |
| IPDS-4-1695 | are consistent and the presentation services program can use either value (and ignore the other value). | ❓ |
| IPDS-4-1696 | This self-defining field specifies the group ID formats that are recognized by the printer in the Group ID (X'00') triplet. The printer must accept all formats (but unrecognized formats are ignored and don't need to be supplied); this self-defining field can help a host program to determine which Group ID formats to supply. | ❓ |
| IPDS-4-1697 | 0–1 | UBIN | SDF length | X'0004' – X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1698 | 2–3 | CODE | SDF ID | X'0025' | Recognized Group ID Formats self-defining field ID | ❓ |
| IPDS-4-1699 | Zero or more entries in the following format: | ❓ |
| IPDS-4-1700 | +0 | CODE | Group ID format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'08'<br>X'13' | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>AIX and Windows print-data<br>Variable-length Group ID format<br>Extended OS/400 print-data format | ❓ |
| IPDS-4-1701 | This self-defining field lists the resolution (or resolutions) controlled by the printer; this includes the resolution to which sheet-side data is RIPped and the number of printed pels per inch (often called the print-head resolution). Most data within IPDS commands is resolution independent; refer to the “IM-Image and Coded-Font Resolution Self-Defining Field” for information about resolution-dependent data. All data to be printed is resolution corrected, if necessary, into the printer’s current RIP and print-head resolution. | ❓ |
| IPDS-4-1702 | 0–1 | UBIN | SDF length | X'000C' – X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1703 | 2–3 | CODE | SDF ID | X'0026' | Supported Device Resolutions self-defining field ID | ❓ |
| IPDS-4-1704 | 4–5 | UBIN | RIP X pels | X'0001' – X'FFFF' | Resolution to which sheet-side data is RIPped for pels per inch across the media | ❓ |
| IPDS-4-1705 | 6–7 | UBIN | RIP Y pels | X'0001' – X'FFFF' | Resolution to which sheet-side data is RIPped for pels per inch in the direction of the media path | ❓ |
| IPDS-4-1706 | 8–9 | UBIN | Print head X pels | X'0001' – X'FFFF' | Number of printed pels per inch across the media | ❓ |
| IPDS-4-1707 | 10–11 | UBIN | Print head Y pels | X'0001' – X'FFFF' | Number of printed pels per inch in the direction of the media path | ❓ |
| IPDS-4-1708 | 12 to end of SDF | | | | Data without architectural definition | ❓ |
| IPDS-4-1709 | EPS (Encapsulated PostScript) | ❓ |
| IPDS-4-1710 | PDF (Portable Document Format) | ❓ |
| IPDS-4-1711 | TIFF (Tag Image File Format) | ❓ |
| IPDS-4-1712 | TrueType/OpenType | ❓ |
| IPDS-4-1713 | 1. One or more version records can return version information for all variations by using any one of the | ❓ |
| IPDS-4-1714 | 2. One or more version records can return version information for each variation separately by using the | ❓ |
| IPDS-4-1715 | can be found in the publication Recommended IPDS Values for Object Container Versions. | ❓ |
| IPDS-4-1716 | 0–1 | UBIN | SDF length | X'001C' – X'7FFE' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1717 | 2–3 | CODE | SDF ID | X'0027' | Object-Container Version Support self-defining field ID | ❓ |
| IPDS-4-1718 | One or more version records in the following format:** | ❓ |
| IPDS-4-1719 | +0 | UBIN | Version record length | X'18'–X'FE' | Length of the version record, including this length field | ❓ |
| IPDS-4-1720 | +1–16 | CODE | Object type OID | | MO:DCA-registered object ID for the object container supported in the WOC. This is the same value as specified in the “Reg ID” field in the Object-Container Type Support self-defining field. | ❓ |
| IPDS-4-1721 | +17 | BITS | Flags | | **bit 0** All variations (B'0' = Version information is applicable to the specified OID only; B'1' = Version information is applicable to all object-type OIDs that are variations of the specified OID)<br>**bits 1–7** Reserved (B'0000000') | ❓ |
| IPDS-4-1722 | +18–19 | UBIN | Major version | X'0000' – X'FFFF' | Major version number supported. The value X'FFFF' indicates no major version information passed; the minor version and subminor version fields must also be X'FFFF' in this case. | ❓ |
| IPDS-4-1723 | +20–21 | UBIN | Minor version | X'0000' – X'FFFF' | Minor version number supported. The value X'FFFF' indicates no minor version information passed; the subminor version field must also be X'FFFF' in this case. | ❓ |
| IPDS-4-1724 | +22–23 | UBIN | Subminor version | X'0000' – X'FFFF' | Subminor version number supported. The value X'FFFF' indicates no subminor version information passed. | ❓ |
| IPDS-4-1725 | +24 to end | CHAR | Version name | | Name of the version supported, using UTF-16BE characters. This field is optional in the version record. | ❓ |
| IPDS-4-1726 | The Finishing Options self-defining field lists all the finishing options that the printer supports with the Finishing Operation (X'85') triplet. Presence of this OPC self-defining field must always be accompanied by presence of the OPC Finishing Operations self-defining field. There can be multiple option-description entries, each of which identifies a supported finishing option. Support for a finishing option does not imply support for all variations of that option. | ❓ |
| IPDS-4-1727 | 0–1 | UBIN | SDF length | X'0005' – X'7FFF' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1728 | 2–3 | CODE | SDF ID | X'0028' | Finishing Options self-defining field ID | ❓ |
| IPDS-4-1729 | One or more option-description entries in the following format:** | ❓ |
| IPDS-4-1730 | +0 | CODE | Option type | X'01' | Crease | ❓ |
| IPDS-4-1731 | Exception ID X'0109..00' exists when a finishing option is enabled or disabled. | ❓ |
| IPDS-4-1732 | The reported speed is in no way a guarantee of performance. | ❓ |
| IPDS-4-1733 | 0–1 | UBIN | SDF length | X'000C' | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1734 | 2–3 | CODE | SDF ID | X'0029' | Printer Speed self-defining field ID | ❓ |
| IPDS-4-1735 | 4–7 | UBIN | PPM | X'00000001' – X'FFFFFFFF'<br>X'00000000' | Number of Letter-sized pages that can be printed per minute<br>No pages-per-minute value reported | ❓ |
| IPDS-4-1736 | 8–11 | UBIN | FPM | X'00000001' – X'FFFFFFFF'<br>X'00000000' | Number of feet of continuous-forms media that can be printed per minute<br>No feet-per-minute value reported | ❓ |
| IPDS-4-1737 | This self-defining field reports the active setup name on the printer, if any. If there is an active setup name, it is returned using a Setup Name (X'9E') triplet in the active-setup-name field. If there is no active setup name, the SDF-length field is returned as X'0004' and the active-setup-name field is omitted. | ❓ |
| IPDS-4-1738 | Exception ID X'010A..00' is returned if the active setup name changes. | ❓ |
| IPDS-4-1739 | The triplet is fully described in the triplets chapter: “Setup Name (X'9E') Triplet”. | ❓ |
| IPDS-4-1740 | 0–1 | UBIN | SDF length | X'0004', X'000A'–X'00D0' even values | Length of this self-defining field, including this length field | ❓ |
| IPDS-4-1741 | 2–3 | CODE | SDF ID | X'002A' | Active Setup Name self-defining field ID | ❓ |
| IPDS-4-1742 | 4 to end of SDF | | Active setup name | | Zero or one Setup Name (X'9E') triplet | ❓ |
| IPDS-4-1743 | Eject to the next sheet if not already on a new sheet. The next received page will be the first page on the new | ❓ |
| IPDS-4-1744 | Perform an XOH Print Buffered Data. | ❓ |
| IPDS-4-1745 | Modify the page and copy counters as specified in its page-counter-update field (byte 2). | ❓ |
| IPDS-4-1746 | returned until PCC processing is complete. | ❓ |
| IPDS-4-1747 | 0–1 | CODE | Order code | X'F500' | Page Counters Control (PCC) order code | X'F500' | ❓ |
| IPDS-4-1748 | 2 | CODE | Counter update | X'00'–X'02' | Page counter update | X'00'–X'01' | ❓ |
| IPDS-4-1749 | 1. Incrementing the page counters by the number of pages on the sheet if the copy | ❓ |
| IPDS-4-1750 | 2. Setting the copy counters to zero. | ❓ |
| IPDS-4-1751 | 3. Setting the received page counter equal to the committed page counter. | ❓ |
| IPDS-4-1752 | Exception ID X'0295..02' exists if the host program specifies any other value in this field. | ❓ |
| IPDS-4-1753 | PBD order is not returned until PBD processing is complete. | ❓ |
| IPDS-4-1754 | 0–1 | CODE | Order code | X'0100' | Print Buffered Data (PBD) order code | X'0100' | ❓ |
| IPDS-4-1755 | 0–1 | CODE | Order code | X'0A00' | Remove Saved Page Group (RSPG) order code | X'0A00' | ❓ |
| IPDS-4-1756 | 2 to end | Triplets | | | Zero or more Group ID triplets:<ul><li>X'00' Group ID triplet with variable-length group ID</li></ul> | ❓ |
| IPDS-4-1757 | Byte 2 or the first byte after a valid triplet is X'00' or X'01' (an invalid triplet length). | ❓ |
| IPDS-4-1758 | A triplet other than a Group ID (X'00') triplet is specified. | ❓ |
| IPDS-4-1759 | A Group ID (X'00') triplet without a variable-length group ID is specified. | ❓ |
| IPDS-4-1760 | command has been received, from the printer-default media source. | ❓ |
| IPDS-4-1761 | 0–1 | CODE | Order code | X'1500' | Select Input Media Source (SIMS) order code | X'1500' | ❓ |
| IPDS-4-1762 | 2 | CODE | Source ID | X'00'–X'FF' | Input media source ID | See byte description. | ❓ |
| IPDS-4-1763 | of the input media source that is associated with a particular input media source ID. | ❓ |
| IPDS-4-1764 | previously active SMM entries remain in effect. Exception ID X'026E..01' exists in this situation. | ❓ |
| IPDS-4-1765 | 0–1 | CODE | Order code | X'0E00' | Select Medium Modifications (SMM) order code | X'0E00' | ❓ |
| IPDS-4-1766 | 2–9 | | | X'00...00' | Reserved | X'00...00' | ❓ |
| IPDS-4-1767 | Zero or more entries in the following format: | ❓ |
| IPDS-4-1768 | + 0–1 | UBIN | Length | X'0005'–X'7FEE' | Length of the entry, including this length field | X'0005' | ❓ |
| IPDS-4-1769 | + 2 | CODE | Type | X'00'<br>X'01'<br>X'02' | Medium modification type:<ul><li>X'00' Inhibit medium modification</li><li>X'01' Apply medium modification</li><li>X'02' Inhibit all medium mods</li></ul> | X'00'<br>X'01'<br>X'02' | ❓ |
| IPDS-4-1770 | + 3–4 | CODE | Modification ID | See byte description | Modification ID | At least one modification ID | ❓ |
| IPDS-4-1771 | + 5 to end | UNDF | Modification parameters | See byte description | Zero or more bytes of medium-modification parameters | ❓ |
| IPDS-4-1772 | modification IDs require any parameters. | ❓ |
| IPDS-4-1773 | This order is not cumulative; consecutive SCF orders produce the same effect as a single order. | ❓ |
| IPDS-4-1774 | 0–1 | CODE | Order code | X'0900' | Separate Continuous Forms (SCF) order code | X'0900' | ❓ |
| IPDS-4-1775 | various kinds of media. | ❓ |
| IPDS-4-1776 | 0–1 | CODE | Order code | X'1600' | Set Media Origin (SMO) order code | X'1600' | ❓ |
| IPDS-4-1777 | 2 | CODE | Origin | X'00'–X'03' | Medium presentation space origin:<ul><li>X'00' Top-left corner</li><li>X'01' See byte description</li><li>X'02' Bottom-right corner</li><li>X'03' See byte description</li></ul> | X'00'<br>X'01'<br>X'02'<br>X'03' | ❓ |
| IPDS-4-1778 | and increase from bottom to top. | ❓ |
| IPDS-4-1779 | m values begin at the origin and increase from right to left. | ❓ |
| IPDS-4-1780 | 1. If an XOH-SMS command has been received and no printer-defined valid sensor or operator input exists, | ❓ |
| IPDS-4-1781 | 2. If a printer defined valid sensor or operator input exists, and if no XOH-SMS command has been received | ❓ |
| IPDS-4-1782 | 3. If both the XOH-SMS extents and the printer-defined valid sensor or operator input exist, use the smaller of | ❓ |
| IPDS-4-1783 | 4. If neither XOH-SMS extents or printer defined valid sensor or operator input exist, use the printer default | ❓ |
| IPDS-4-1784 | 5. A XOH-SMS extent of X'FFFF' in either dimension means ignore the previous XOH-SMS extent and use | ❓ |
| IPDS-4-1785 | 1. If an XOH-SMS command changes the Xm and Ym extents of the medium presentation space and the | ❓ |
| IPDS-4-1786 | 2. The medium presentation space size specified in accordance with these rules is used in all valid printable | ❓ |
| IPDS-4-1787 | The data field for the Set Media Size order has the following format: | ❓ |
| IPDS-4-1788 | 0–1 | CODE | Order code | X'1700' | Set Media Size (SMS) order code | X'1700' | ❓ |
| IPDS-4-1789 | 2 | CODE | Unit base | X'00'<br>X'01' | Unit base for this command:<ul><li>X'00' Ten inches</li><li>X'01' Ten centimeters</li></ul> | X'00' | ❓ |
| IPDS-4-1790 | 3–4 | UBIN | UPUB | X'0001'–X'7FFF' | Units per unit base | X'3840' | ❓ |
| IPDS-4-1791 | 5–6 | UBIN | $X_{m}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{m}$ extent of the medium presentation space:<ul><li>X'FFFF' Printer default</li></ul>Required range: X'000A'–X'2FD0' (Refer to the note following the table.) | X'FFFF' | ❓ |
| IPDS-4-1792 | 7–8 | UBIN | $Y_{m}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{m}$ extent of the medium presentation space:<ul><li>X'FFFF' Printer default</li></ul>Required range: X'000A'–X'4EC0' (Refer to the note following the table.) | X'FFFF' | ❓ |
| IPDS-4-1793 | specified; the preferred exception ID is X'0273..02'. | ❓ |
| IPDS-4-1794 | 2 CODE Operation X'01' | ❓ |
| IPDS-4-1795 | 3 UBIN Group level X'00'–X'FF' Group Level of Boundary groups to which the | ❓ |
| IPDS-4-1796 | Bytes 0–1 SGO order code | ❓ |
| IPDS-4-1797 | 1. X'01' is used when communicating with the Remote PrintManager (RPM) and | ❓ |
| IPDS-4-1798 | 2. Since groups can be nested, specifying the group operation appropriately is | ❓ |
| IPDS-4-1799 | 3. A printer might provide a timer that is set within a print unit whenever the host | ❓ |
| IPDS-4-1800 | 1. The processing part of this operation is device specific; the architectural | ❓ |
| IPDS-4-1801 | simulation is done by the IPDS receiver. | ❓ |
| IPDS-4-1802 | 2. If a request for MICR printing is received within a page to be saved, either the | ❓ |
| IPDS-4-1803 | 3. Printers that support UP | ❓ |
| IPDS-4-1804 | 4. Preprinted form overlays can be used with saved pages. However, while saving | ❓ |
| IPDS-4-1805 | BP , IO-with PFO parameter, ISP , EP | ❓ |
| IPDS-4-1806 | BP , ISP , IO-with PFO parameter, EP | ❓ |
| IPDS-4-1807 | Information (X'6E') triplet using the Group Name format. | ❓ |
| IPDS-4-1808 | the last page in the group has been stacked and will then increment | ❓ |
| IPDS-4-1809 | If a paper jam occurs, the normal exception ID X'40E5..00' will be | ❓ |
| IPDS-4-1810 | If the Not-ready button has been pressed, the printer should try to | ❓ |
| IPDS-4-1811 | If any other intervention required condition, equipment check, or | ❓ |
| IPDS-4-1812 | If the continuous printing is interrupted for some reason such that | ❓ |
| IPDS-4-1813 | If the presentation services program sends more pages within a | ❓ |
| IPDS-4-1814 | that group in the paper path. Printers that delay incrementing the | ❓ |
| IPDS-4-1815 | The printer increments the jam recovery page counter and stacked | ❓ |
| IPDS-4-1816 | The presentation services program cannot control when a group is | ❓ |
| IPDS-4-1817 | delimiting the group of pages upon which the specified operation is to be performed. | ❓ |
| IPDS-4-1818 | 1. Eject to the next sheet if not already on a new sheet. The next received page will be the first page on the | ❓ |
| IPDS-4-1819 | 2. Perform an XOH Print Buffered Data. | ❓ |
| IPDS-4-1820 | 3. Stack all pages that have been committed for printing. | ❓ |
| IPDS-4-1821 | 0–1 CODE Order code X'0D00' Stack Received Pages order code X'0D00' | ❓ |
| IPDS-4-1822 | XOH Trace command is received to stop the trace; if a subsequent XOH Trace command is received to start | ❓ |
| IPDS-4-1823 | XOH Trace command is received to obtain the trace; all trace entries are discarded after they have been sent | ❓ |
| IPDS-4-1824 | The maximum amount of space available for tracing is reached. In this case exception ID X'0113..00' exists | ❓ |
| IPDS-4-1825 | The printer is reinitialized (returns an IML NACK). What happens to trace entries after an IML occurs is | ❓ |
| IPDS-4-1826 | vector of an STM reply. | ❓ |
| IPDS-4-1827 | 2 CODE Function | ❓ |
| IPDS-4-1828 | 3 BITS Control flags (only used when starting a trace) | ❓ |
| IPDS-4-1829 | 4 X'00' Reserved X'00' | ❓ |
| IPDS-4-1830 | Start trace (X'00') causes the printer to begin tracing or, if a trace was already started, to | ❓ |
| IPDS-4-1831 | Stop trace (X'01') causes the printer to stop tracing; the trace is left intact in the printer and | ❓ |
| IPDS-4-1832 | Obtain trace (X'02') causes the printer to do the following: | ❓ |
| IPDS-4-1833 | 1. Stop tracing | ❓ |
| IPDS-4-1834 | 2. If the ARQ flag is set to B'1' in this command, return the first portion of the stored trace in | ❓ |
| IPDS-4-1835 | sequence they occurred beginning with the Begin-Trace trace entry. | ❓ |
| IPDS-4-1836 | 3. Delete all trace entries. | ❓ |
| IPDS-4-1837 | When this flag is set to B'1', all trace entries contain a date and time stamp. | ❓ |
| IPDS-4-1838 | When this flag is B'0', the date and time stamp is omitted from all trace entries. | ❓ |
| IPDS-4-1839 | When this flag is set to B'1', the following trace entries contain the CMR name for | ❓ |
| IPDS-4-1840 | When this flag is B'0', the CMR names are omitted from all trace entries. | ❓ |
| IPDS-4-1841 | When this flag is set to B'1', Free-Form trace entries can contain user text data. | ❓ |
| IPDS-4-1842 | When this flag is B'0', all user text data in each Free-Form trace entry must be | ❓ |
| IPDS-4-1843 | End-Object trace entries are generated. | ❓ |
| IPDS-4-1844 | WBCC, WGC, WIC2, WOCC, and WTC commands received in page or overlay | ❓ |
| IPDS-4-1845 | End commands for objects that were begun with commands from the previous bullet | ❓ |
| IPDS-4-1846 | WT commands that begin a sequence of text-major text within a page, page | ❓ |
| IPDS-4-1847 | option values are ignored. | ❓ |
| IPDS-4-1848 | The trace entries are defined as follows. | ❓ |
| IPDS-4-1849 | 4 BITS Entry flags | ❓ |
| IPDS-4-1850 | 5 X'00' Reserved | ❓ |
| IPDS-4-1851 | Length of host name for printer | ❓ |
| IPDS-4-1852 | A channel address (such as 4A0) | ❓ |
| IPDS-4-1853 | An Internet Protocol address (such as 9.99.12.33) | ❓ |
| IPDS-4-1854 | An Internet Protocol host name (such as PRTJDOE) | ❓ |
| IPDS-4-1855 | provided, the length is X'0000'. | ❓ |
| IPDS-4-1856 | 4 BITS Entry flags | ❓ |
| IPDS-4-1857 | 5 X'00' Reserved | ❓ |
| IPDS-4-1858 | Host-provided page name | ❓ |
| IPDS-4-1859 | 4 BITS Entry flags | ❓ |
| IPDS-4-1860 | 5 X'00' Reserved | ❓ |
| IPDS-4-1861 | 8 UBIN Month X'01'–X'0C' Month of the year | ❓ |
| IPDS-4-1862 | 9 UBIN Day X'01'–X'1F' Day of the month | ❓ |
| IPDS-4-1863 | 10 UBIN Hour X'00'–X'17' Hour of the day in 24-hour format | ❓ |
| IPDS-4-1864 | 11 UBIN Minute X'00'–X'3B' Minute of the hour | ❓ |
| IPDS-4-1865 | 12 UBIN Second X'00'–X'3B' Second of the minute | ❓ |
| IPDS-4-1866 | 16 CODE Timezone | ❓ |
| IPDS-4-1867 | 17 UBIN UTCDiffH X'00'–X'17' Hours ahead of or behind UTC | ❓ |
| IPDS-4-1868 | 18 UBIN UTCDiffM X'00'–X'3B' Minutes ahead of or behind UTC | ❓ |
| IPDS-4-1869 | This field identifies this as a Begin-Page trace entry. | ❓ |
| IPDS-4-1870 | component of a time in the format hhmmssMM. | ❓ |
| IPDS-4-1871 | not used and contains X'00'. | ❓ |
| IPDS-4-1872 | Begin Page (BPG) structured field. | ❓ |
| IPDS-4-1873 | 4 BITS Entry flags | ❓ |
| IPDS-4-1874 | 5 X'00' Reserved | ❓ |
| IPDS-4-1875 | This field identifies this as a Begin-Overlay trace entry. | ❓ |
| IPDS-4-1876 | Map Page Overlay (MPO) or Map Medium Overlay (MMO) structured field. | ❓ |
| IPDS-4-1877 | WBCC, WGC, WIC2, page-state WOCC, overlay-state WOCC, and WTC commands. | ❓ |
| IPDS-4-1878 | End commands for objects that were begun with commands from the previous bullet. | ❓ |
| IPDS-4-1879 | WT commands that begin a sequence of text-major text within a page, page segment, or overlay; an End- | ❓ |
| IPDS-4-1880 | 4 BITS Entry flags | ❓ |
| IPDS-4-1881 | 5 X'00' Reserved | ❓ |
| IPDS-4-1882 | Object HAID (resource object) | ❓ |
| IPDS-4-1883 | 8 or 21 CODE Object type | ❓ |
| IPDS-4-1884 | object is inline in a page, page segment, or overlay, the HAID field contains X'0000'. | ❓ |
| IPDS-4-1885 | following MO:DCA structured fields: | ❓ |
| IPDS-4-1886 | 4 BITS Entry flags | ❓ |
| IPDS-4-1887 | 5 X'00' Reserved | ❓ |
| IPDS-4-1888 | 6 or 19 CODE Rendering | ❓ |
| IPDS-4-1889 | 7 or 20 CODE RI hierarchy | ❓ |
| IPDS-4-1890 | 1. Audit color-conversion CMR or indexed CMR (not used when an ICC DeviceLink CMR is | ❓ |
| IPDS-4-1891 | 2. Instruction color-conversion or indexed CMR (not used when an ICC DeviceLink CMR is | ❓ |
| IPDS-4-1892 | 3. Link color-conversion (subset LK or DL) CMR (not used with indexed CMRs) | ❓ |
| IPDS-4-1893 | 4. Audit halftone CMR (not used with indexed CMRs) | ❓ |
| IPDS-4-1894 | 5. Instruction halftone CMR | ❓ |
| IPDS-4-1895 | 6. Audit tone-transfer-curve CMR (not used with indexed CMRs) | ❓ |
| IPDS-4-1896 | 7. Instruction tone-transfer-curve CMR | ❓ |
| IPDS-4-1897 | @@@ @@@@@ @@@@@@ @@@@ @@@@ @@@@ @@@@@@@@ | ❓ |
| IPDS-4-1898 | For the link entry, the HT-audit entry, and the TC-audit entry | ❓ |
| IPDS-4-1899 | For the CC-audit entry, and the CC-instruction entry when | ❓ |
| IPDS-4-1900 | When the printer ignores a CMR type, such as for audit | ❓ |
| IPDS-4-1901 | 4 BITS Entry flags | ❓ |
| IPDS-4-1902 | 5 X'00' Reserved | ❓ |
| IPDS-4-1903 | 8 or 21 CODE Mode | ❓ |
| IPDS-4-1904 | Begin-Page trace entry description (). | ❓ |
| IPDS-4-1905 | Name flag in byte 4 indicates whether the name is present or omitted. | ❓ |
| IPDS-4-1906 | 4 BITS Entry flags | ❓ |
| IPDS-4-1907 | 5 X'00' Reserved | ❓ |
| IPDS-4-1908 | 8 or 21 CODE CMR | ❓ |
| IPDS-4-1909 | Name flag in byte 4 indicates whether the name is present or omitted. | ❓ |
| IPDS-4-1910 | 4 BITS Entry flags | ❓ |
| IPDS-4-1911 | 5 X'00' Reserved | ❓ |
| IPDS-4-1912 | 6 or 19 CODE Source ID X'00'–X'FF' Input media source ID | ❓ |
| IPDS-4-1913 | MediaBrightness | ❓ |
| IPDS-4-1914 | MediaColor | ❓ |
| IPDS-4-1915 | MediaFinish | ❓ |
| IPDS-4-1916 | MediaWeight | ❓ |
| IPDS-4-1917 | When a media characteristic is not known, the field is filled with @ characters. | ❓ |
| IPDS-4-1918 | 4 BITS Entry flags | ❓ |
| IPDS-4-1919 | 5 X'00' Reserved | ❓ |
| IPDS-4-1920 | This parameter contains the 24-byte sense data from the NACK. | ❓ |
| IPDS-4-1921 | 4 BITS Entry flags | ❓ |
| IPDS-4-1922 | 5 X'00' Reserved | ❓ |
| IPDS-4-1923 | Free-Form trace entry unless that information can be appropriately controlled. | ❓ |
| IPDS-4-1924 | 4 BITS Entry flags | ❓ |
| IPDS-4-1925 | 5 X'00' Reserved | ❓ |
| IPDS-4-1926 | 4 BITS Entry flags | ❓ |
| IPDS-4-1927 | 5 X'00' Reserved | ❓ |
| IPDS-4-1928 | Map Page Overlay (MPO) or Map Medium Overlay (MMO) structured field. | ❓ |
| IPDS-4-1929 | 4 BITS Entry flags | ❓ |
| IPDS-4-1930 | 5 X'00' Reserved | ❓ |
| IPDS-4-1931 | 8 or 21 CODE Object type | ❓ |
| IPDS-4-1932 | This field contains the length of this trace entry, including the length field itself. | ❓ |
| IPDS-4-1933 | Begin Object Container (BOC) or Map Data Resource (MDR) structured field. | ❓ |
| IPDS-4-1934 | 4 BITS Entry flags | ❓ |
| IPDS-4-1935 | 5 X'00' Reserved | ❓ |
| IPDS-4-1936 | triplet being traced. | ❓ |
| IPDS-4-1937 | 4 BITS Entry flags | ❓ |
| IPDS-4-1938 | 5 X'00' Reserved | ❓ |
| IPDS-4-1939 | 6 or 19 CODE Continue | ❓ |
| IPDS-4-1940 | 7 or 20 CODE Report | ❓ |
| IPDS-4-1941 | 8 or 21 CODE Substitute | ❓ |
| IPDS-4-1942 | 4 BITS Entry flags | ❓ |
| IPDS-4-1943 | 5 X'00' Reserved | ❓ |
| IPDS-4-1944 | 6 or 19 CODE Continue | ❓ |
| IPDS-4-1945 | 7 or 20 CODE Report | ❓ |
| IPDS-4-1946 | 4 BITS Entry flags | ❓ |
| IPDS-4-1947 | 5 X'00' Reserved | ❓ |
| IPDS-4-1948 | 6 or 19 CODE Format | ❓ |
| IPDS-4-1949 | This parameter identifies the format of the Group ID information that follows. | ❓ |
| IPDS-4-1950 | for a description of each of the formats. | ❓ |
| IPDS-4-1951 | 4 BITS Entry flags | ❓ |
| IPDS-4-1952 | 5 X'00' Reserved | ❓ |
| IPDS-4-1953 | End Page (EP) command for pages | ❓ |
| IPDS-4-1954 | End Page (EP) command for overlays | ❓ |
| IPDS-4-1955 | End command for presentation objects | ❓ |
| IPDS-4-1956 | 4 BITS Entry flags | ❓ |
| IPDS-4-1957 | 5 X'00' Reserved | ❓ |
| IPDS-4-1958 | 6 or 19 CODE Object type | ❓ |
| IPDS-4-1959 | printer-provided information is present or is omitted. | ❓ |
| IPDS-4-1960 | 4 BITS Entry flags | ❓ |
| IPDS-4-1961 | 5 X'00' Reserved | ❓ |
| IPDS-4-1962 | Name flag in byte 4 indicates whether the name is present or omitted. | ❓ |
| IPDS-5-001 | washed the eggnog off his right foot | ❓ |
| IPDS-5-002 | text controls within the text object have no effect on subsequent text within the page, page segment, or overlay. | ❓ |
| IPDS-5-003 | 0–1 | CODE | Type | X'0100' | Mapping type:<br>Suppression equivalence | X'0100' | ❓ |
| IPDS-5-004 | Zero or more equivalence entries in the following format:** | ❓ |
| IPDS-5-005 | 2–3 | CODE | Internal | X'0001' – X'00FF' | Internal value | X'0001' – X'007F' | ❓ |
| IPDS-5-006 | 4–5 | CODE | External | X'0001' – X'00FF' | External value | X'0001' – X'007F' | ❓ |
| IPDS-5-007 | text suppression IDs in the range X'0080'–X'00FF'. | ❓ |
| IPDS-5-008 | considered to be a direct reference to an internal value suppression ID used in a BSU ...ESU pair. | ❓ |
| IPDS-5-009 | 1. Text Area Position (TAP) | ❓ |
| IPDS-5-010 | 2. Text Output Control (TOC), optional | ❓ |
| IPDS-5-011 | 3. Text Data Descriptor (TDD) | ❓ |
| IPDS-5-012 | X'2001' property pair that is returned in the Text command-set vector of the Sense Type and Model reply. | ❓ |
| IPDS-5-013 | The format of the TAP is as follows: | ❓ |
| IPDS-5-014 | 0–1 | UBIN | Length | X'000B' to end of TAP | Length of TAP, including this length field | X'000B' to end of TAP | ❓ |
| IPDS-5-015 | 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' | ❓ |
| IPDS-5-016 | 4–5 | SBIN | X offset | X'8000' – X'7FFF' | Text object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-017 | 6–7 | SBIN | Y offset | X'8000' – X'7FFF' | Text object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-018 | 8–9 | CODE | Text object area orientation | ❓ |
| IPDS-5-019 | bits 0–8 | Degrees | B'000000000' – B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' | ❓ |
| IPDS-5-020 | bits 9–14 | Minutes | B'000000' – B'111011' | Number of minutes (0–59) in the orientation | B'000000' | ❓ |
| IPDS-5-021 | bit 15 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-5-022 | 10 | CODE | Coordinate system | X'00', X'20', X'40', X'60', X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | ❓ |
| IPDS-5-023 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on | ❓ |
| IPDS-5-024 | LPD command that is current when this object is printed in a page or overlay. | ❓ |
| IPDS-5-025 | 0 degrees | ❓ |
| IPDS-5-026 | 90 degrees | ❓ |
| IPDS-5-027 | 180 degrees | ❓ |
| IPDS-5-028 | 270 degrees | ❓ |
| IPDS-5-029 | If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-5-030 | If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-5-031 | If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the | ❓ |
| IPDS-5-032 | If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. TAP | ❓ |
| IPDS-5-033 | If byte 10 equals X'A0', the current logical page X | ❓ |
| IPDS-5-034 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-5-035 | Text object area size equals the text presentation space size defined in the TDD self-defining field | ❓ |
| IPDS-5-036 | Mapping option X'00' (position) | ❓ |
| IPDS-5-037 | Xoa offset and Yoa offset equals 0 | ❓ |
| IPDS-5-038 | The text object area is not colored | ❓ |
| IPDS-5-039 | No object-level CMRs | ❓ |
| IPDS-5-040 | No object-level rendering intent | ❓ |
| IPDS-5-041 | The format of the TOC is as follows: | ❓ |
| IPDS-5-042 | 0–1 | UBIN | Length | X'0010' to end of TOC | Length of TOC, including this length field | X'0010' to end of TOC | ❓ |
| IPDS-5-043 | 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' | ❓ |
| IPDS-5-044 | 4 | CODE | Unit base | X'00', X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-5-045 | 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-5-046 | 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF', X'FFFF' | $X_{oa}$ extent of text object area in L-units<br>Use the LPD value. | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-5-047 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF', X'FFFF' | $Y_{oa}$ extent of text object area in L-units<br>Use the LPD value. | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-5-048 | 11 | CODE | Mapping control option | X'00' | Mapping control option: Position | X'00' | ❓ |
| IPDS-5-049 | 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units | X'0000'–X'7FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-050 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units | X'0000'–X'7FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-051 | 16 to end of TOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets | X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-5-052 | 1/1440 of an inch. Many receivers support the subset plus additional function. If a receiver supports | ❓ |
| IPDS-5-053 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on | ❓ |
| IPDS-5-054 | If an invalid value is specified, exception ID X'0207..05' exists. | ❓ |
| IPDS-5-055 | The units of measure used to interpret this offset are specified in bytes 4–6. | ❓ |
| IPDS-5-056 | The format of the TDD is as follows: | ❓ |
| IPDS-5-057 | 0–1 | UBIN | Length | X'0014' to end of TDD | Length of TDD, including this length field | X'0014' to end of TDD | ❓ |
| IPDS-5-058 | 2–3 | CODE | SDF ID | X'A69B' | Self-defining-field ID | X'A69B' | ❓ |
| IPDS-5-059 | 4–5 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-5-060 | 6 | CODE | Unit base | X'00', X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-5-061 | 7 | X'00' | Reserved | X'00' | ❓ |
| IPDS-5-062 | 8–9 | UBIN | $X_{t}$ units per unit base | X'0001'–X'7FFF' | $X_{t}$ units per unit base | X'3840' | ❓ |
| IPDS-5-063 | 10–11 | UBIN | $Y_{t}$ units per unit base | X'0001'–X'7FFF' | $Y_{t}$ units per unit base, must be the same value as XUPUB | X'3840' | ❓ |
| IPDS-5-064 | 12–14 | UBIN | $X_{t}$ extent | X'000001' – X'007FFF' | $X_{t}$ extent of the text presentation space in L-units | X'000001' – X'007FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-065 | 15–17 | UBIN | $Y_{t}$ extent | X'000001' – X'007FFF' | $Y_{t}$ extent of the text presentation space in L-units | X'000001' – X'007FFF'<br>(Refer to the note following the table.) | ❓ |
| IPDS-5-066 | 18–19 | BITS | Text flags | B'00...00' | Reserved for text flags | B'00...00' | ❓ |
| IPDS-5-067 | 20 to end of TDD | | Initial text conditions | Defined in PTOCA | Defined in PTOCA | Defined in PTOCA | ❓ |
| IPDS-5-068 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm” on | ❓ |
| IPDS-5-069 | If an invalid or unsupported value is specified, exception ID X'0205..05' exists. | ❓ |
| IPDS-5-070 | If an invalid or unsupported value is specified, exception ID X'0200..01' exists. | ❓ |
| IPDS-5-071 | Home State, or Sense Type and Model are received before the next Write Text command. | ❓ |
| IPDS-5-072 | When a code page is associated with the font, the code page indicates the encoding scheme (typically | ❓ |
| IPDS-5-073 | An Encoding Scheme ID (X'50') triplet can be used to indicate that the data is encoded as UTF-8. | ❓ |
| IPDS-5-074 | Otherwise, the encoding scheme used within the font applies. With a TrueType/OpenType font, the encoding | ❓ |
| IPDS-5-075 | Alternate glyphs as used in CJK fonts | ❓ |
| IPDS-5-076 | Bidirectional rendering | ❓ |
| IPDS-5-077 | Contextual shaping | ❓ |
| IPDS-5-078 | Combined characters (for example, ligatures that are mandatory and have no equivalent Unicode code point) | ❓ |
| IPDS-5-079 | Specialized word break and justification rules | ❓ |
| IPDS-5-080 | Printers that return STM property pair X'4303' in the Text command-set vector handle the UCT differently | ❓ |
| IPDS-5-081 | depending on its use: | ❓ |
| IPDS-5-082 | Printers that do not return X'4303' will detect exception ID X'0200..01' whenever a UCT control sequence is | ❓ |
| IPDS-5-083 | offsets are also provided to allow for advanced glyph layout. By allowing glyph IDs, glyph advances, and glyph | ❓ |
| IPDS-5-084 | Text Object Content Architecture Reference. | ❓ |
| IPDS-5-085 | Unchained** | **Chained** | | **PT1** | **PT2** | **PT3** | **PT4** | ❓ |
| IPDS-5-086 | X'D2' | X'D3' | Absolute Move Baseline (AMB) | X | X | X | X | ❓ |
| IPDS-5-087 | X'C6' | X'C7' | Absolute Move Inline (AMI) | X | X | X | X | ❓ |
| IPDS-5-088 | X'D8' | X'D9' | Begin Line (BLN)—also known as Next Line | X | X | X | X | ❓ |
| IPDS-5-089 | X'F2' | X'F3' | Begin Suppression (BSU) | X | X | X | X | ❓ |
| IPDS-5-090 | X'E6' | X'E7' | Draw B-Axis Rule (DBR) | X | X | X | X | ❓ |
| IPDS-5-091 | X'E4' | X'E5' | Draw I-Axis Rule (DIR) | X | X | X | X | ❓ |
| IPDS-5-092 | X'98' | X'99' | Encrypted Data (ENC) | ❓ |
| IPDS-5-093 | X'F4' | X'F5' | End Suppression (ESU) | X | X | X | X | ❓ |
| IPDS-5-094 | X'8C' | X'8D' | Glyph Advance Run (GAR) | | | | X | ❓ |
| IPDS-5-095 | X'8B' | Glyph ID Run (GIR) | | | | X | ❓ |
| IPDS-5-096 | X'6D' | Glyph Layout Control (GLC) | | | | X | ❓ |
| IPDS-5-097 | X'8E' | X'8F' | Glyph Offset Run (GOR) | | | | X | ❓ |
| IPDS-5-098 | X'F8' | X'F9' | No Operation (NOP) | X | X | X | X | ❓ |
| IPDS-5-099 | X'72' | X'73' | Overstrike (OVS) | | X | X | X | ❓ |
| IPDS-5-100 | X'D4' | X'D5' | Relative Move Baseline (RMB) | X | X | X | X | ❓ |
| IPDS-5-101 | X'C8' | X'C9' | Relative Move Inline (RMI) | X | X | X | X | ❓ |
| IPDS-5-102 | X'EE' | X'EF' | Repeat String (RPS) | X | X | X | X | ❓ |
| IPDS-5-103 | X'D0' | X'D1' | Set Baseline Increment (SBI) | X | X | X | X | ❓ |
| IPDS-5-104 | X'F0' | X'F1' | Set Coded Font Local (SCFL) | X | X | X | X | ❓ |
| IPDS-5-105 | X'9C' | X'9D' | Set Encrypted Alternate (SEA) | ❓ |
| IPDS-5-106 | X'80' | X'81' | Set Extended Text Color (SEC) | | | X | X | ❓ |
| IPDS-5-107 | X'C0' | X'C1' | Set Inline Margin (SIM) | X | X | X | X | ❓ |
| IPDS-5-108 | X'C2' | X'C3' | Set Intercharacter Adjustment (SIA) | X | X | X | X | ❓ |
| IPDS-5-109 | X'9A' | X'9B' | Set Key Information (SKI) | ❓ |
| IPDS-5-110 | X'74' | X'75' | Set Text Color (STC) | X | X | X | X | ❓ |
| IPDS-5-111 | X'F6' | X'F7' | Set Text Orientation (STO) | X | X | X | X | ❓ |
| IPDS-5-112 | X'C4' | X'C5' | Set Variable-Space Character Increment (SVI) | X | X | X | X | ❓ |
| IPDS-5-113 | X'78' | X'79' | Temporary Baseline Move (TBM) | | X | X | X | ❓ |
| IPDS-5-114 | X'DA' | X'DB' | Transparent Data (TRN) | X | X | X | X | ❓ |
| IPDS-5-115 | X'76' | X'77' | Underscore (USC) | | X | X | X | ❓ |
| IPDS-5-116 | X'6A' | Unicode Complex Text (UCT) | | | | X | ❓ |
| IPDS-5-117 | X'029A..01' – Invalid overstrike character increment | ❓ |
| IPDS-5-118 | X'029D..03' – No text string encryption key information set | ❓ |
| IPDS-6-001 | Table 39. IM-Image Commands | ❓ |
| IPDS-6-002 | WIC | X'D63D' | “Write Image Control” | Yes | ❓ |
| IPDS-6-003 | WI | X'D64D' | “Write Image” | Yes | ❓ |
| IPDS-6-004 | Table 40. IM-Image and IO-Image Comparison | ❓ |
| IPDS-6-005 | Replicate and trim input to fill output | X | O | ❓ |
| IPDS-6-006 | Bilevel image | X | X | ❓ |
| IPDS-6-007 | Bilevel image with a color specification | X | O | ❓ |
| IPDS-6-008 | Unpadded recording algorithm | X | O | ❓ |
| IPDS-6-009 | Grayscale image | | X | ❓ |
| IPDS-6-010 | Compression | | X | ❓ |
| IPDS-6-011 | Resolution-independent data presentation | | X | ❓ |
| IPDS-6-012 | Resolution correction to device resolution | | X | ❓ |
| IPDS-6-013 | Scaling | | X | ❓ |
| IPDS-6-014 | Position and trim | | X | ❓ |
| IPDS-6-015 | Center and trim | | X | ❓ |
| IPDS-6-016 | Scale to fill | | O | ❓ |
| IPDS-6-017 | Full-color image | | O | ❓ |
| IPDS-6-018 | Color Management Resources (CMRs); see note | | O | ❓ |
| IPDS-6-019 | Image Banding | | O | ❓ |
| IPDS-6-020 | Subsampling | | O | ❓ |
| IPDS-6-021 | Relative resolution for a tile | | O | ❓ |
| IPDS-6-022 | Tiling | | O | ❓ |
| IPDS-6-023 | Transparency masks | | O | ❓ |
| IPDS-6-024 | Bit allocation | | O | ❓ |
| IPDS-6-025 | Area coloring | | O | ❓ |
| IPDS-6-026 | IOCA tile resources | | O | ❓ |
| IPDS-6-027 | Multiple image contents in an IOCA image segment | | O | ❓ |
| IPDS-6-028 | The data field bytes have the following meaning for this command: | ❓ |
| IPDS-6-029 | 0–1 | UBIN | PPSL output | X'0001' – X'7FFF' | Pels per scan line in the output image | X'0001' – X'0FA0' | ❓ |
| IPDS-6-030 | 2–3 | UBIN | NSL output | X'0001' – X'7FFF' | Number of scan lines in the output image | X'0001' – X'0FA0' | ❓ |
| IPDS-6-031 | 4–5 | UBIN | PPSL input | X'0001' – X'7FFF' | Pels per scan line in the input image | X'0001' – X'0FA0' | ❓ |
| IPDS-6-032 | 6–7 | UBIN | NSL input | X'0001' – X'7FFF' | Number of scan lines in the input image | X'0001' – X'0FA0' | ❓ |
| IPDS-6-033 | 8 | CODE | Compress | X'00' | Uncompressed input image | X'00' | ❓ |
| IPDS-6-034 | 9 | CODE | Bits per pel | X'00' | One bit per pel in the input image format | X'00' | ❓ |
| IPDS-6-035 | 10 | UBIN | Pel mag. | X'01', X'02' | Pel magnification factor | X'01', X'02' | ❓ |
| IPDS-6-036 | 11 | UBIN | Scan-line mag. | X'01', X'02' | Scan-line magnification factor; must equal the value in byte 10. | X'01', X'02' | ❓ |
| IPDS-6-037 | 12–13 | CODE | SL direction | X'0000' X'2D00' X'5A00' X'8700' | Scan-line direction:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-6-038 | 14–15 | CODE | SLS direction | X'0000' X'2D00' X'5A00' X'8700' | Scan-line sequence direction:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'2D00' | ❓ |
| IPDS-6-039 | 16 | CODE | RCS | X'00' X'20' X'40' X'60' X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>$X_{p}, Y_{p}$ | X'00' X'20' X'40' X'60' X'A0' | ❓ |
| IPDS-6-040 | 17–19 | SBIN | X offset | X'FF8000'– X'007FFF' | $X_{p}$, $I$, or $I$-offset coordinate of the output image origin | X'000000'– X'007FFF' | ❓ |
| IPDS-6-041 | 20 | | | X'00' | Reserved | X'00' | ❓ |
| IPDS-6-042 | 21–23 | SBIN | Y offset | X'FF8000'– X'007FFF' | $Y_{p}$, $B$, or $B$-offset coordinate of the output image origin | X'000000'– X'007FFF' | ❓ |
| IPDS-6-043 | 24–25 | CODE | Color | X'0000' – X'0010', X'FF00' – X'FF08' | Image color (same as graphics color values) | X'FF07' | ❓ |
| IPDS-6-044 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. | ❓ |
| IPDS-6-045 | printer documentation for information about a particular IPDS printer. | ❓ |
| IPDS-6-046 | = Untoned pel in the raster pattern | ❓ |
| IPDS-6-047 | Exception ID X'0246..01' exists if an invalid value is specified. | ❓ |
| IPDS-6-048 | Repeating each pel on the scan line | ❓ |
| IPDS-6-049 | Repeating each scan line | ❓ |
| IPDS-6-050 | X'01' indicates no magnification of pels. | ❓ |
| IPDS-6-051 | X'02' indicates a magnification factor of 2. Each pel on a scan line is repeated. | ❓ |
| IPDS-6-052 | X'01' indicates no magnification of scan lines. | ❓ |
| IPDS-6-053 | X'02' indicates a magnification factor of 2. Each scan line in the image is repeated. | ❓ |
| IPDS-6-054 | Exception ID X'0247..01' exists if an invalid value is specified. | ❓ |
| IPDS-6-055 | = Untoned pel in the raster pattern | ❓ |
| IPDS-6-056 | 12 and 13. | ❓ |
| IPDS-6-057 | If byte 16 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-6-058 | If byte 16 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-6-059 | If byte 16 equals X'40', the relative inline and absolute baseline coordinates determine the | ❓ |
| IPDS-6-060 | If byte 16 equals X'60', the relative inline and baseline coordinates determine the origin. | ❓ |
| IPDS-6-061 | If byte 16 equals X'A0', the current logical page $X_{p}$ and $Y_{p}$ coordinates determine the origin. | ❓ |
| IPDS-6-062 | 23 specify the offset from the $X_{p}$-coordinate and $Y_{p}$-coordinate origin specified in the IO | ❓ |
| IPDS-6-063 | Exception ID X'024A..01' exists if an invalid or unsupported value is specified. | ❓ |
| IPDS-6-064 | Standard OCA Color-Value Table | ❓ |
| IPDS-6-065 | Table 41. Standard OCA Color-Value Table | ❓ |
| IPDS-6-066 | X'0000' or X'FF00' | Current default; see note 1 | ❓ |
| IPDS-6-067 | X'0001' or X'FF01' | Blue | 0 | 0 | 255 | ❓ |
| IPDS-6-068 | X'0002' or X'FF02' | Red | 255 | 0 | 0 | ❓ |
| IPDS-6-069 | X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 | ❓ |
| IPDS-6-070 | X'0004' or X'FF04' | Green | 0 | 255 | 0 | ❓ |
| IPDS-6-071 | X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 | ❓ |
| IPDS-6-072 | X'0006' or X'FF06' | Yellow | 255 | 255 | 0 | ❓ |
| IPDS-6-073 | X'0007' | White, see note 2 | 255 | 255 | 255 | ❓ |
| IPDS-6-074 | X'0008' | Black, see note 3 | 0 | 0 | 0 | ❓ |
| IPDS-6-075 | X'0009' | Dark blue | 0 | 0 | 170 | ❓ |
| IPDS-6-076 | X'000A' | Orange | 255 | 128 | 0 | ❓ |
| IPDS-6-077 | X'000B' | Purple | 170 | 0 | 170 | ❓ |
| IPDS-6-078 | X'000C' | Dark green | 0 | 146 | 0 | ❓ |
| IPDS-6-079 | X'000D' | Dark turquoise | 0 | 146 | 170 | ❓ |
| IPDS-6-080 | X'000E' | Mustard | 196 | 160 | 32 | ❓ |
| IPDS-6-081 | X'000F' | Gray | 131 | 131 | 131 | ❓ |
| IPDS-6-082 | X'0010' | Brown | 144 | 48 | 0 | ❓ |
| IPDS-6-083 | X'FF07' | Printer default; see note 4 | ❓ |
| IPDS-6-084 | X'FF08' | Color of medium; also known as reset color | ❓ |
| IPDS-6-085 | 1. The definition of current default is dependent on the data type. | ❓ |
| IPDS-6-086 | For graphics data, the current default is the drawing order default defined in the GDD self-defining field of | ❓ |
| IPDS-6-087 | For text, IM-Image, bilevel IO-Image, and bar code data, the current default is the printer default. | ❓ |
| IPDS-6-088 | 2. The color rendered on presentation devices that do not support white is device dependent. For example, | ❓ |
| IPDS-6-089 | 3. It is recommended that OCA Black (X'0008') be rendered as C=M=Y= X'00' and K = X'FF'. | ❓ |
| IPDS-6-090 | 4. The printer default color specified by X'FF07' is also known in GOCA as neutral white for compatibility with | ❓ |
| IPDS-6-091 | 5. The value X'FFFF' is not defined in the Standard OCA Color-Value Table, but is used by some objects as a | ❓ |
| IPDS-6-092 | For PTOCA text data, X'FFFF' can be specified in the Set Text Color (STC) control sequence to indicate | ❓ |
| IPDS-6-093 | For IM-Image data in MO:DCA environments, X'FFFF' can be specified to indicate use of a presentation | ❓ |
| IPDS-6-094 | For bilevel IOCA image data (FS10), X'FFFF' can be specified to indicate use of a printer default color. | ❓ |
| IPDS-6-095 | For BCOCA bar code data, X'FFFF' can be specified to indicate use of a printer default color. | ❓ |
| IPDS-6-096 | Scan-line pel length (bytes 4 and 5 of the WIC command) | ❓ |
| IPDS-6-097 | Scan-line count (bytes 6 and 7 of the WIC command). | ❓ |
| IPDS-6-098 | the image; the preferred exception ID is X'02AF..01'. | ❓ |
| IPDS-7-001 | Table 42. IO-Image Commands | ❓ |
| IPDS-7-002 | WIC2 | X'D63E' | “Write Image Control 2” | Yes | ❓ |
| IPDS-7-003 | WI2 | X'D64E' | “Write Image 2” | Yes | ❓ |
| IPDS-7-004 | Table 43. IM-Image and IO-Image Comparison | ❓ |
| IPDS-7-005 | Replicate and trim input to fill output | X | O | ❓ |
| IPDS-7-006 | Bilevel image | X | X | ❓ |
| IPDS-7-007 | Bilevel image with a color specification | X | O | ❓ |
| IPDS-7-008 | Unpadded recording algorithm | X | O | ❓ |
| IPDS-7-009 | Grayscale image | | X | ❓ |
| IPDS-7-010 | Compression | | X | ❓ |
| IPDS-7-011 | Resolution-independent data presentation | | X | ❓ |
| IPDS-7-012 | Resolution correction to device resolution | | X | ❓ |
| IPDS-7-013 | Scaling | | X | ❓ |
| IPDS-7-014 | Position and trim | | X | ❓ |
| IPDS-7-015 | Center and trim | | X | ❓ |
| IPDS-7-016 | Scale to fill | | O | ❓ |
| IPDS-7-017 | Full-color image | | O | ❓ |
| IPDS-7-018 | Color Management Resources (CMRs); see note | | O | ❓ |
| IPDS-7-019 | Image Banding | | O | ❓ |
| IPDS-7-020 | Subsampling | | O | ❓ |
| IPDS-7-021 | Relative resolution for a tile | | O | ❓ |
| IPDS-7-022 | Tiling | | O | ❓ |
| IPDS-7-023 | Transparency masks | | O | ❓ |
| IPDS-7-024 | Bit allocation | | O | ❓ |
| IPDS-7-025 | Area coloring | | O | ❓ |
| IPDS-7-026 | IOCA tile resources | | O | ❓ |
| IPDS-7-027 | Multiple image contents in an IOCA image segment | | O | ❓ |
| IPDS-7-028 | 14 of the IDD self-defining field. The image presentation space is then replicated in the X and Y directions of | ❓ |
| IPDS-7-029 | IO-Image command-set vector of an STM reply by those printers that do support the mapping option. | ❓ |
| IPDS-7-030 | include time is applied to the image. | ❓ |
| IPDS-7-031 | 1. Image Area Position (IAP) | ❓ |
| IPDS-7-032 | 2. Image Output Control (IOC), optional | ❓ |
| IPDS-7-033 | 3. Image Data Descriptor (IDD) | ❓ |
| IPDS-7-034 | specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. | ❓ |
| IPDS-7-035 | The format of the IAP is as follows: | ❓ |
| IPDS-7-036 | 0–1 | UBIN | Length | X'000B' to end of IAP | Length of IAP, including this length field | X'000B' to end of IAP | ❓ |
| IPDS-7-037 | 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' | ❓ |
| IPDS-7-038 | 4–5 | SBIN | X offset | X'8000' – X'7FFF' | Image object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-7-039 | 6–7 | SBIN | Y offset | X'8000' – X'7FFF' | Image object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-7-040 | 8–9 | CODE | Image object area orientation | ❓ |
| IPDS-7-041 | bits 0–8 | Degrees | B'000000000' – B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' | ❓ |
| IPDS-7-042 | bits 9–14 | Minutes | B'000000' – B'111011' | Number of minutes (0–59) in the orientation | B'000000' | ❓ |
| IPDS-7-043 | bit 15 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-7-044 | 10 | CODE | Coordinate system | X'00' X'20' X'40' X'60' X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00' X'20' X'40' X'60' X'A0' | ❓ |
| IPDS-7-045 | 11 to end of IAP | UNDF | | | Data without architectural definition | ❓ |
| IPDS-7-046 | LPD command that is current when this object is printed in a page or overlay. | ❓ |
| IPDS-7-047 | 0 degrees | ❓ |
| IPDS-7-048 | 90 degrees | ❓ |
| IPDS-7-049 | 180 degrees | ❓ |
| IPDS-7-050 | 270 degrees | ❓ |
| IPDS-7-051 | coordinate location. Therefore, the following applies: | ❓ |
| IPDS-7-052 | If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-7-053 | If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-7-054 | If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the | ❓ |
| IPDS-7-055 | If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. IAP | ❓ |
| IPDS-7-056 | If byte 10 equals X'A0', the current logical page $X_{p}$ and $Y_{p}$ coordinates determine the origin. | ❓ |
| IPDS-7-057 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-7-058 | Mapping option X'30' (position and trim). | ❓ |
| IPDS-7-059 | $X_{oa}$ offset and $Y_{oa}$ offset equals 0. | ❓ |
| IPDS-7-060 | Image object area size equals the image presentation space size defined in the IDD self-defining field. | ❓ |
| IPDS-7-061 | No coloring. | ❓ |
| IPDS-7-062 | No object-level CMRs. | ❓ |
| IPDS-7-063 | No object-level rendering intent. | ❓ |
| IPDS-7-064 | The format of the IOC is as follows: | ❓ |
| IPDS-7-065 | 0–1 | UBIN | Length | X'0010', X'0012' to end of IOC | Length of IOC, including this length field | X'0010', X'0012' to end of IOC | ❓ |
| IPDS-7-066 | 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' | ❓ |
| IPDS-7-067 | 4 | CODE | Unit base | X'00' X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-7-068 | 5–6 | UBIN | UPUB | X'0001' – X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-7-069 | 7–8 | UBIN | $X_{oa}$ extent | X'0001' – X'7FFF' X'FFFF' | $X_{oa}$ extent of IO-Image object area in L-units<br>X'FFFF' = Use the LPD value. | See byte description. | ❓ |
| IPDS-7-070 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001' – X'7FFF' X'FFFF' | $Y_{oa}$ extent of IO-Image object area in L-units<br>X'FFFF' = Use the LPD value. | See byte description. | ❓ |
| IPDS-7-071 | 11 | CODE | Mapping control option | X'10' X'20' X'30' X'41' X'42' X'50' X'60' | Mapping control option:<br>X'10' = Scale to fit<br>X'20' = Center and trim<br>X'30' = Position and trim<br>X'41' = Point to pel<br>X'42' = Point to pel w/ double dot<br>X'50' = Replicate & trim (FS10 only)<br>X'60' = Scale to fill | X'10' X'20' X'30' X'41' X'42' | ❓ |
| IPDS-7-072 | 12–13 | SBIN | $X_{oa}$ offset | X'8000' – X'7FFF' | $X_{oa}$ offset in L-units (for position-and-trim only) | X'0000' – X'7FFF' | ❓ |
| IPDS-7-073 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000' – X'7FFF' | $Y_{oa}$ offset in L-units (for position-and-trim only) | X'0000' – X'7FFF' | ❓ |
| IPDS-7-074 | 16– | Triplets | | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-7-075 | Conversion Algorithm”. The printer must support X'FFFF' for pages and overlays. | ❓ |
| IPDS-7-076 | Exception ID X'0208..05' exists if an invalid or unsupported mapping option is specified. | ❓ |
| IPDS-7-077 | object area is dependent on the device resolution. | ❓ |
| IPDS-7-078 | colored green; if the reset flag is B'1' (reset to color of medium), the area is colored in the color of medium. | ❓ |
| IPDS-7-079 | triplets in the WBCC command. | ❓ |
| IPDS-7-080 | The format of the IDD is as follows: | ❓ |
| IPDS-7-081 | 0–1 | UBIN | Length | X'000F' to end of IDD | Length of IDD, including this length field | X'000F' to end of IDD | ❓ |
| IPDS-7-082 | 2–3 | CODE | SDF ID | X'A6FB' | Self-defining-field ID | X'A6FB' | ❓ |
| IPDS-7-083 | 4–5 | CODE | HAID | X'0000' X'0001' – X'7EFF' | No value assigned<br>Data object resource Host-Assigned ID | X'0000' | ❓ |
| IPDS-7-084 | 6 | CODE | Unit base | X'00' X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-7-085 | 7–8 | UBIN | $X_{io}$ resolution | X'0001' – X'7FFF' | $X_{io}$ image points per unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-7-086 | 9–10 | UBIN | $Y_{io}$ resolution | X'0001' – X'7FFF' | $Y_{io}$ image points per unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-7-087 | 11–12 | UBIN | $X_{io}$ extent | X'0001' – X'7FFF' | $X_{io}$ extent of the image presentation space in image points | X'0001' – X'7FFF' | ❓ |
| IPDS-7-088 | 13–14 | UBIN | $Y_{io}$ extent | X'0001' – X'7FFF' | $Y_{io}$ extent of the image presentation space in image points | X'0001' – X'7FFF' | ❓ |
| IPDS-7-089 | 15– | | | | Zero or more of the following IOCA self-defining fields:<br>X'F4' Set Extended Bilevel Image Color<br>X'F6' Set Bilevel Image Color | ❓ |
| IPDS-7-090 | Exception ID X'0205..05' exists if an invalid or unsupported unit base value is specified. | ❓ |
| IPDS-7-091 | 136 inches. Refer to your printer documentation for specific information. | ❓ |
| IPDS-7-092 | the IO-Image command-set vector of an STM reply with any defined nn bit set to B'1' | ❓ |
| IPDS-7-093 | encountered, the last one encountered is used and the others are ignored. | ❓ |
| IPDS-7-094 | 0 | CODE | IOCA SDF ID | X'F6' | Set Bilevel Image Color SDF ID | X'F6' | ❓ |
| IPDS-7-095 | 1 | UBIN | Length | X'04' | Length of the parameters that follow | X'04' | ❓ |
| IPDS-7-096 | 2 | CODE | Area | X'00' | Applicability area:<br>X'00' = Foreground IDEs | X'00' | ❓ |
| IPDS-7-097 | 3 | | | X'00' | Reserved | X'00' | ❓ |
| IPDS-7-098 | 4–5 | CODE | Named color | | Named-color value for each of the image data elements in the applicability area. The following values are defined, all other values are reserved:<br>X'0000' Printer default<br>X'0001' Blue<br>X'0002' Red<br>X'0003' Pink/magenta<br>X'0004' Green<br>X'0005' Turquoise/cyan<br>X'0006' Yellow<br>X'0007' White, see note<br>X'0008' Black<br>X'0009' Dark blue<br>X'000A' Orange<br>X'000B' Purple<br>X'000C' Dark green<br>X'000D' Dark turquoise<br>X'000E' Mustard<br>X'000F' Gray<br>X'0010' Brown<br>X'FF00' Printer default<br>X'FF01' Blue<br>X'FF02' Red<br>X'FF03' Pink/magenta<br>X'FF04' Green<br>X'FF05' Turquoise/cyan<br>X'FF06' Yellow<br>X'FF07' Printer default<br>X'FF08' Color of medium<br>X'FFFF' Printer default | X'FF07' | ❓ |
| IPDS-7-099 | white when white media is used. | ❓ |
| IPDS-7-100 | IOCA self-defining fields are: | ❓ |
| IPDS-7-101 | Table 44. IOCA Self-Defining Fields | ❓ |
| IPDS-7-102 | X'70' | Begin Segment | All | ❓ |
| IPDS-7-103 | X'71' | End Segment | All | ❓ |
| IPDS-7-104 | X'8C' | Begin Tile Parameter | FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-105 | X'8D' | End Tile Parameter | FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-106 | X'8E' | Begin Transparency Mask Parameter | FS14, FS45, FS48 | ❓ |
| IPDS-7-107 | X'8F' | End Transparency Mask Parameter | FS14, FS45, FS48 | ❓ |
| IPDS-7-108 | X'91' | Begin Image Content | All | ❓ |
| IPDS-7-109 | X'93' | End Image Content | All | ❓ |
| IPDS-7-110 | X'94' | Image Size Parameter | FS10, FS11, FS14, FS45, FS48 | ❓ |
| IPDS-7-111 | X'95' | Image Encoding Parameter | All | ❓ |
| IPDS-7-112 | X'96' | Image Data Element Size Parameter | All | ❓ |
| IPDS-7-113 | X'97' | Image Look Up Table ID Parameter | FS10, FS11 | ❓ |
| IPDS-7-114 | X'98' | Band Image Parameter | FS11, FS14, FS42, FS45, FS48 | ❓ |
| IPDS-7-115 | X'9B' | Image Data Element Structure Parameter | FS11, FS14, FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-116 | X'9F' | External Algorithm Specification Parameter | FS11 | ❓ |
| IPDS-7-117 | X'B5' | Tile Position Parameter | FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-118 | X'B6' | Tile Size Parameter | FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-119 | X'B7' | Tile Set Color Parameter | FS42, FS45, FS48 | ❓ |
| IPDS-7-120 | X'FE92' | Image Data (one or more) | All | ❓ |
| IPDS-7-121 | X'FE9C' | Band Image Data (one or more) | FS11, FS14, FS42, FS45, FS48 | ❓ |
| IPDS-7-122 | X'FEB3' | nColor Names | All | ❓ |
| IPDS-7-123 | X'FEB8' | Include Tile Parameter | FS45, FS48 | ❓ |
| IPDS-7-124 | X'FEBB' | Tile TOC Parameter | FS40, FS42, FS45, FS48 | ❓ |
| IPDS-7-125 | X'FECE' | Image Subsampling Parameter | FS11, FS14 | ❓ |
| IPDS-7-126 | X'F4' – Set Extended Bilevel Image Color | ❓ |
| IPDS-7-127 | X'F6' – Set Bilevel Image Color | ❓ |
| IPDS-7-128 | X'F7' – IOCA Function Set identification (allowed in the MO:DCA IDD; ignored by IPDS receivers) | ❓ |
| IPDS-7-129 | If the number of bits per image point of the Image Data Element size parameter is X'01', this Look-Up Table | ❓ |
| IPDS-7-130 | If the number of bits per image point of the Image Data Element size parameter is greater than X'01', this | ❓ |
| IPDS-7-131 | vector in an STM reply. | ❓ |
| IPDS-7-132 | supports the given function set: | ❓ |
| IPDS-7-133 | Table 45. Exception IDs for IOCA Function Sets | ❓ |
| IPDS-7-134 | X'0500..01' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-135 | X'0500..03' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-136 | X'0500..04' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-137 | X'0570..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-138 | X'0571..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-139 | X'058C..0F' | | | | X | X | X | X | ❓ |
| IPDS-7-140 | X'058D..0F' | | | | X | X | X | X | ❓ |
| IPDS-7-141 | X'058E..0F' | | | | | | X | X | ❓ |
| IPDS-7-142 | X'058F..0F' | | | | | | X | X | ❓ |
| IPDS-7-143 | X'0591..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-144 | X'0592..01' | | X | X | X | X | X | ❓ |
| IPDS-7-145 | X'0592..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-146 | X'0593..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-147 | X'0594..01' | | X | X | X | X | X | ❓ |
| IPDS-7-148 | X'0594..0F' | | X | X | X | X | X | ❓ |
| IPDS-7-149 | X'0594..10' | | X | X | X | X | X | ❓ |
| IPDS-7-150 | X'0594..11' | | X | X | X | X | X | ❓ |
| IPDS-7-151 | X'0595..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-152 | X'0595..10' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-153 | X'0595..11' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-154 | X'0596..0F' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-155 | X'0596..10' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-156 | X'0596..11' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-157 | X'0597..0F' | X | X | ❓ |
| IPDS-7-158 | X'0597..10' | X | X | ❓ |
| IPDS-7-159 | X'0598..01' | | X | X | ❓ |
| IPDS-7-160 | X'0598..0F' | | X | X | | | X | X | ❓ |
| IPDS-7-161 | X'0598..10' | | X | X | | | X | X | ❓ |
| IPDS-7-162 | X'0598..14' | | X | X | | | X | X | ❓ |
| IPDS-7-163 | X'0598..15' | | X | X | | | X | X | ❓ |
| IPDS-7-164 | X'059B..0F' | | X | X | X | X | X | X | ❓ |
| IPDS-7-165 | X'059B..10' | | X | X | X | X | X | X | ❓ |
| IPDS-7-166 | X'059B..18' | | X | X | X | X | X | X | ❓ |
| IPDS-7-167 | X'059C..01' | | X | X | | | X | X | ❓ |
| IPDS-7-168 | X'059C..0F' | | X | X | | | X | X | ❓ |
| IPDS-7-169 | X'059C..17' | | X | X | | | X | X | ❓ |
| IPDS-7-170 | X'059F..01' | | X | ❓ |
| IPDS-7-171 | X'059F..0F' | | X | ❓ |
| IPDS-7-172 | X'059F..10' | | X | ❓ |
| IPDS-7-173 | X'059F..11' | | X | ❓ |
| IPDS-7-174 | X'05A9..02' | X | X | X | X | X | X | X | ❓ |
| IPDS-7-175 | X'05B3..0F' | ❓ |
| IPDS-7-176 | X'05B3..10' | ❓ |
| IPDS-7-177 | X'05B3..11' | ❓ |
| IPDS-7-178 | X'05B5..0F' | | | | X | X | X | X | ❓ |
| IPDS-7-179 | X'05B5..10' | | | | X | X | X | X | ❓ |
| IPDS-7-180 | X'05B5..11' | | | | X | X | X | X | ❓ |
| IPDS-7-181 | X'05B6..0F' | | | | X | X | X | X | ❓ |
| IPDS-7-182 | X'05B6..10' | | | | X | X | X | X | ❓ |
| IPDS-7-183 | X'05B6..11' | | | | X | X | X | X | ❓ |
| IPDS-7-184 | X'05B7..0F' | | | | | X | X | X | ❓ |
| IPDS-7-185 | X'05B7..10' | | | | | X | X | X | ❓ |
| IPDS-7-186 | X'05B7..11' | | | | | X | X | X | ❓ |
| IPDS-7-187 | X'05B8..0F' | | | | | | X | X | ❓ |
| IPDS-7-188 | X'05B8..11' | | | | | | X | X | ❓ |
| IPDS-7-189 | X'05BB..0F' | | | | X | X | X | X | ❓ |
| IPDS-7-190 | X'05BB..10' | | | | X | X | X | X | ❓ |
| IPDS-7-191 | X'05BB..11' | | | | X | X | X | X | ❓ |
| IPDS-7-192 | X'05CE..01' | | X | X | ❓ |
| IPDS-7-193 | X'05CE..0F' | | X | X | ❓ |
| IPDS-7-194 | X'05CE..10' | | X | X | ❓ |
| IPDS-7-195 | X'05F4..10' | ❓ |
| IPDS-8-001 | Table 46. Graphics Commands | ❓ |
| IPDS-8-002 | WGC | X'D684' | “Write Graphics Control” | Yes | ❓ |
| IPDS-8-003 | WG | X'D685' | “Write Graphics” | Yes | ❓ |
| IPDS-8-004 | graphics presentation space window, and the graphics object area. | ❓ |
| IPDS-8-005 | X Left Limitg X Right Limitg | ❓ |
| IPDS-8-006 | vector of the Sense Type and Model command reply. | ❓ |
| IPDS-8-007 | 1. IPDS printers should scale the entire GOCA presentation space window, but for some printers, graphics | ❓ |
| IPDS-8-008 | Graphics images | ❓ |
| IPDS-8-009 | Markers | ❓ |
| IPDS-8-010 | Patterns | ❓ |
| IPDS-8-011 | Line widths | ❓ |
| IPDS-8-012 | Character strings | ❓ |
| IPDS-8-013 | 2. GOCA architecture states that “the line width should be scaled when the controlling environment specifies | ❓ |
| IPDS-8-014 | exception. A detailed description of graphics mapping follows under “Mapping Control Options”. | ❓ |
| IPDS-8-015 | 1. Graphics area position (GAP) | ❓ |
| IPDS-8-016 | 2. Graphics output control (GOC), optional | ❓ |
| IPDS-8-017 | 3. Graphics data descriptor (GDD) | ❓ |
| IPDS-8-018 | specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. | ❓ |
| IPDS-8-019 | The format of the GAP is as follows: | ❓ |
| IPDS-8-020 | 0–1 | UBIN | Length | X'000B' to end of GAP | Length of GAP, including this length field | X'000B' to end of GAP | ❓ |
| IPDS-8-021 | 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' | ❓ |
| IPDS-8-022 | 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Graphics object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-8-023 | 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Graphics object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-8-024 | 8–9 | CODE | Graphics object area orientation | ❓ |
| IPDS-8-025 | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' | ❓ |
| IPDS-8-026 | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' | ❓ |
| IPDS-8-027 | bit 15 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-8-028 | 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | ❓ |
| IPDS-8-029 | 11 to end of GAP | UNDF | | | Data without architectural definition | ❓ |
| IPDS-8-030 | Exception ID X'0860..00' exists if the position cannot be represented by the printer. | ❓ |
| IPDS-8-031 | 0 degrees | ❓ |
| IPDS-8-032 | 90 degrees | ❓ |
| IPDS-8-033 | 180 degrees | ❓ |
| IPDS-8-034 | 270 degrees | ❓ |
| IPDS-8-035 | 6 and 7 is specified at an absolute baseline coordinate location, that is, bytes 6 and 7 are | ❓ |
| IPDS-8-036 | Therefore, the following applies: | ❓ |
| IPDS-8-037 | If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-8-038 | If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-8-039 | If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the | ❓ |
| IPDS-8-040 | If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. | ❓ |
| IPDS-8-041 | If byte 10 equals X'A0', the current logical page X | ❓ |
| IPDS-8-042 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-8-043 | Mapping option X'30' (position and trim). | ❓ |
| IPDS-8-044 | Graphics object area size equals the graphics presentation space window size defined in the GDD self- | ❓ |
| IPDS-8-045 | No coloring. | ❓ |
| IPDS-8-046 | No object-level CMRs. | ❓ |
| IPDS-8-047 | No object-level rendering intent. | ❓ |
| IPDS-8-048 | The format of the GOC is as follows: | ❓ |
| IPDS-8-049 | 0–1 | UBIN | Length | X'0010', X'0012' to end of GOC | Length of GOC, including this length field | X'0010', X'0012' to end of GOC | ❓ |
| IPDS-8-050 | 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' | ❓ |
| IPDS-8-051 | 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-8-052 | 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-8-053 | 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units<br>Use the LPD value | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-8-054 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units<br>Use the LPD value | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-8-055 | 11 | CODE | Mapping control | X'10'<br>X'20'<br>X'30'<br>X'60' | Mapping control option:<br>Scale to fit<br>Center and trim<br>Position and trim<br>Scale to fill | X'10'<br>X'20'<br>X'30' | ❓ |
| IPDS-8-056 | 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units; (for position and trim only) | X'0000'–X'7FFF' | ❓ |
| IPDS-8-057 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units; (for position and trim only) | X'0000'–X'7FFF' | ❓ |
| IPDS-8-058 | 16 to end of GOC | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-8-059 | measure specified in bytes 4–6. A value of X'FFFF' causes the printer to use the $Y_{p}$ extent and | ❓ |
| IPDS-8-060 | X'10'—Scale to fit | ❓ |
| IPDS-8-061 | X'20'—Center and trim | ❓ |
| IPDS-8-062 | X'30'—Position and trim | ❓ |
| IPDS-8-063 | X'50'—Retired item 135 | ❓ |
| IPDS-8-064 | X'60'—Scale to fill | ❓ |
| IPDS-8-065 | “Rendering Intent (X'95') Triplet” | ❓ |
| IPDS-8-066 | Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the WGC command. | ❓ |
| IPDS-8-067 | 1. IPDS printers should scale the entire GOCA presentation space window, but for some printers, graphics | ❓ |
| IPDS-8-068 | Graphics images | ❓ |
| IPDS-8-069 | Markers | ❓ |
| IPDS-8-070 | Patterns | ❓ |
| IPDS-8-071 | Line widths | ❓ |
| IPDS-8-072 | Character strings | ❓ |
| IPDS-8-073 | 2. GOCA architecture states that “the line width should be scaled when the controlling environment specifies | ❓ |
| IPDS-8-074 | (x = -32,768, y = -32,768)g g | ❓ |
| IPDS-8-075 | The format of the GDD is as follows: | ❓ |
| IPDS-8-076 | 0–1 | UBIN | Length | X'001C' to end of GDD | Length of GDD, including this length field | X'001C' to end of GDD | ❓ |
| IPDS-8-077 | 2–3 | CODE | SDF ID | X'A6BB' | Self-defining-field ID | X'A6BB' | ❓ |
| IPDS-8-078 | 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-8-079 | 5 | X'00' | Reserved | | | X'00' | ❓ |
| IPDS-8-080 | 6–7 | UBIN | XUPUB | X'0001'–X'7FFF' | $X_{g}$-units/unit base | X'3840' | ❓ |
| IPDS-8-081 | 8–9 | UBIN | YUPUB | X'0001'–X'7FFF' | $Y_{g}$-units/unit base; must be the same value as XUPUB | X'3840' | ❓ |
| IPDS-8-082 | 10–11 | UBIN | XIRES | X'0000'–X'7FFF' | Graphics image resolution in the X direction | X'0000' | ❓ |
| IPDS-8-083 | 12–13 | UBIN | YIRES | X'0000'–X'7FFF' | Graphics image resolution in the Y direction | X'0000' | ❓ |
| IPDS-8-084 | 14–15 | SBIN | $X_{g}$ left limit | X'8000'–X'7FFF' | $X_{g}$ left limit of graphics presentation space window | X'8000'–X'7FFF' | ❓ |
| IPDS-8-085 | 16–17 | SBIN | $X_{g}$ right limit | X'8000'–X'7FFF' | $X_{g}$ right limit of graphics presentation space window | X'8000'–X'7FFF' | ❓ |
| IPDS-8-086 | 18–19 | SBIN | $Y_{g}$ top limit | X'8000'–X'7FFF' | $Y_{g}$ top limit of graphics presentation space window | X'8000'–X'7FFF' | ❓ |
| IPDS-8-087 | 20–21 | SBIN | $Y_{g}$ bottom limit | X'8000'–X'7FFF' | $Y_{g}$ bottom limit of graphics presentation space window | X'8000'–X'7FFF' | ❓ |
| IPDS-8-088 | 22–27 | X'00...00' | Reserved | | | X'00...00' | ❓ |
| IPDS-8-089 | 28 to end | | Defaults | See GOCA Reference | Initial graphics default conditions: self-describing instructions that set the drawing defaults for the picture | All defaults allowed by the supported GOCA subset | ❓ |
| IPDS-8-090 | If an invalid value is specified, exception ID X'0206..05' exists. | ❓ |
| IPDS-8-091 | 1. Default values for Normal Line Width (set-byte value X'11') and Process Color (set-byte | ❓ |
| IPDS-8-092 | 2. Line End and Line Join are not supported by all IPDS printers and the mask bits for these | ❓ |
| IPDS-8-093 | unsupported mask attribute in bytes 3 and 4. | ❓ |
| IPDS-8-094 | Advanced Function Presentation for a description of this command. | ❓ |
| IPDS-8-095 | Table 47. Summary of GOCA Drawing Orders | ❓ |
| IPDS-8-096 | X'68' | Begin Area | Fixed 2 byte | X | X | ❓ |
| IPDS-8-097 | X'D1' | Begin Image | Long | X | X | ❓ |
| IPDS-8-098 | X'91' | Begin Image at Current Position | Long | X | X | ❓ |
| IPDS-8-099 | X'C0' | Box | Long | | X | ❓ |
| IPDS-8-100 | X'80' | Box at Current Position | Long | | X | ❓ |
| IPDS-8-101 | X'C3' | Character String | Long | X | X | ❓ |
| IPDS-8-102 | X'83' | Character String at Current Position | Long | X | X | ❓ |
| IPDS-8-103 | X'01' | Comment | Long | X | X | ❓ |
| IPDS-8-104 | X'60' | End Area | Long | X | X | ❓ |
| IPDS-8-105 | X'93' | End Image | Long | X | X | ❓ |
| IPDS-8-106 | X'3E' | End Prolog | Fixed 2 byte | X | X | ❓ |
| IPDS-8-107 | X'C5' | Fillet | Long | X | X | ❓ |
| IPDS-8-108 | X'85' | Fillet at Current Position | Long | X | X | ❓ |
| IPDS-8-109 | X'C7' | Full Arc | Long | X | X | ❓ |
| IPDS-8-110 | X'87' | Full Arc at Current Position | Long | X | X | ❓ |
| IPDS-8-111 | X'92' | Image Data | Long | X | X | ❓ |
| IPDS-8-112 | X'C1' | Line | Long | X | X | ❓ |
| IPDS-8-113 | X'81' | Line at Current Position | Long | X | X | ❓ |
| IPDS-8-114 | X'C2' | Marker | Long | X | X | ❓ |
| IPDS-8-115 | X'82' | Marker at Current Position | Long | X | X | ❓ |
| IPDS-8-116 | X'00' | No Operation | Fixed 1 byte | X | X | ❓ |
| IPDS-8-117 | X'E3' | Partial Arc | Long | | X | ❓ |
| IPDS-8-118 | X'A3' | Partial Arc at Current Position | Long | | X | ❓ |
| IPDS-8-119 | X'E1' | Relative Line | Long | X | X | ❓ |
| IPDS-8-120 | X'A1' | Relative Line at Current Position | Long | X | X | ❓ |
| IPDS-8-121 | X'04' | Segment Characteristics | Long | X | X | ❓ |
| IPDS-8-122 | X'22' | Set Arc Parameters | Long | X | X | ❓ |
| IPDS-8-123 | X'0D' | Set Background Mix | Fixed 2 byte | X | X | ❓ |
| IPDS-8-124 | X'34' | Set Character Angle | Long | X | X | ❓ |
| IPDS-8-125 | X'33' | Set Character Cell | Long | X | X | ❓ |
| IPDS-8-126 | X'3A' | Set Character Direction | Fixed 2 byte | X | X | ❓ |
| IPDS-8-127 | X'39' | Set Character Precision | Fixed 2 byte | X | X | ❓ |
| IPDS-8-128 | X'38' | Set Character Set | Fixed 2 byte | X | X | ❓ |
| IPDS-8-129 | X'35' | Set Character Shear | Long | X | X | ❓ |
| IPDS-8-130 | X'0A' | Set Color | Fixed 2 byte | X | X | ❓ |
| IPDS-8-131 | X'21' | Set Current Position | Long | X | X | ❓ |
| IPDS-8-132 | X'26' | Set Extended Color | Long | X | X | ❓ |
| IPDS-8-133 | X'11' | Set Fractional Line Width | Long | | X | ❓ |
| IPDS-8-134 | X'18' | Set Line Type | Fixed 2 byte | X | X | ❓ |
| IPDS-8-135 | X'19' | Set Line Width | Fixed 2 byte | X | X | ❓ |
| IPDS-8-136 | X'37' | Set Marker Cell | Long | X | X | ❓ |
| IPDS-8-137 | X'3B' | Set Marker Precision | Fixed 2 byte | X | X | ❓ |
| IPDS-8-138 | X'3C' | Set Marker Set | Fixed 2 byte | X | X | ❓ |
| IPDS-8-139 | X'29' | Set Marker Symbol | Fixed 2 byte | X | X | ❓ |
| IPDS-8-140 | X'0C' | Set Mix | Fixed 2 byte | X | X | ❓ |
| IPDS-8-141 | X'08' | Set Pattern Set | Fixed 2 byte | X | X | ❓ |
| IPDS-8-142 | X'28' | Set Pattern Symbol | Fixed 2 byte | X | X | ❓ |
| IPDS-8-143 | X'B2' | Set Process Color | Long | | X | ❓ |
| IPDS-8-144 | Table 48. Additional Drawing Orders Supported by Some Printers | ❓ |
| IPDS-8-145 | X'DE' | Begin Custom Pattern | Long | ❓ |
| IPDS-8-146 | X'E5' | Cubic Bézier Curve | Long | ❓ |
| IPDS-8-147 | X'A5' | Cubic Bézier Curve at Current Position | Long | ❓ |
| IPDS-8-148 | X'DF' | Delete Pattern | Long | ❓ |
| IPDS-8-149 | X'5E' | End Custom Pattern | Fixed 2 byte | ❓ |
| IPDS-8-150 | X'FEDC' | Linear Gradient | Extended | ❓ |
| IPDS-8-151 | X'FEDD' | Radial Gradient | Extended | ❓ |
| IPDS-8-152 | X'20' | Set Custom Line Type | Long | ❓ |
| IPDS-8-153 | X'1A' | Set Line End | Fixed 2 byte | ❓ |
| IPDS-8-154 | X'1B' | Set Line Join | Fixed 2 byte | ❓ |
| IPDS-8-155 | X'A0' | Set Pattern Reference Point | Long | ❓ |
| IPDS-8-156 | and exception conditions. | ❓ |
| IPDS-9-001 | Table 49. Bar Code Commands | ❓ |
| IPDS-9-002 | WBCC | X'D680' | “Write Bar Code Control” | Yes | ❓ |
| IPDS-9-003 | WBC | X'D681' | “Write Bar Code” | Yes | ❓ |
| IPDS-9-004 | vector of the Sense Type and Model command reply. | ❓ |
| IPDS-9-005 | ... END string is required whenever: | ❓ |
| IPDS-9-006 | A new bar code object area is started. | ❓ |
| IPDS-9-007 | The type of bar code symbol is changed (refer to byte 16 of the BCDD self-defining field). | ❓ |
| IPDS-9-008 | One of the parameters changes in bytes 17–26 of the Bar Code Data Descriptor self-defining field. | ❓ |
| IPDS-9-009 | 1. Bar Code Area Position (BCAP) | ❓ |
| IPDS-9-010 | 2. Bar Code Output Control (BCOC), optional | ❓ |
| IPDS-9-011 | 3. Bar Code Data Descriptor (BCDD) | ❓ |
| IPDS-9-012 | specified, or one of the self-defining fields appears more than once, exception ID X'020B..05' exists. | ❓ |
| IPDS-9-013 | The format of the BCAP is as follows: | ❓ |
| IPDS-9-014 | 0–1 | UBIN | Length | X'000B' to end of BCAP | Length of BCAP, including this length field | X'000B' to end of BCAP | ❓ |
| IPDS-9-015 | 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' | ❓ |
| IPDS-9-016 | 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Bar code object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-9-017 | 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Bar code object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' | ❓ |
| IPDS-9-018 | 8–9 | CODE | Bar code object area orientation | ❓ |
| IPDS-9-019 | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' | ❓ |
| IPDS-9-020 | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' | ❓ |
| IPDS-9-021 | bit 15 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-9-022 | 10 | CODE | Reference system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>Absolute I, absolute B<br>Absolute I, relative B<br>Relative I, absolute B<br>Relative I, relative B<br>Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | ❓ |
| IPDS-9-023 | 11 to end of BCAP | UNDF | | | Data without architectural definition | ❓ |
| IPDS-9-024 | Bytes 6–7 Bar code object area origin Y offset in L-units | ❓ |
| IPDS-9-025 | 0 degrees | ❓ |
| IPDS-9-026 | 90 degrees | ❓ |
| IPDS-9-027 | 180 degrees | ❓ |
| IPDS-9-028 | 270 degrees | ❓ |
| IPDS-9-029 | 6 and 7 forms an absolute baseline coordinate location, that is, bytes 6 and 7 are offset from | ❓ |
| IPDS-9-030 | If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-9-031 | If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-9-032 | If byte 10 equals X'40', the relative I and absolute B coordinates determine the origin. BCAP | ❓ |
| IPDS-9-033 | If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. | ❓ |
| IPDS-9-034 | If byte 10 equals X'A0', the current logical page X | ❓ |
| IPDS-9-035 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-9-036 | The presentation space origin is located at the same point as the bar code object area origin. | ❓ |
| IPDS-9-037 | Bar code object area size equals the bar code presentation space size defined in the BCDD self-defining | ❓ |
| IPDS-9-038 | No coloring. | ❓ |
| IPDS-9-039 | No object-level CMRs. | ❓ |
| IPDS-9-040 | The format of the BCOC is as follows: | ❓ |
| IPDS-9-041 | 0–1 | UBIN | Length | X'0010', X'0012' to end of BCOC | Length of BCOC, including this length field | X'0010', X'0012' to end of BCOC | ❓ |
| IPDS-9-042 | 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' | ❓ |
| IPDS-9-043 | 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | X'00' | ❓ |
| IPDS-9-044 | 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-9-045 | 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units<br>Use LPD value | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-9-046 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units<br>Use LPD value | X'0001'–X'7FFF'<br>X'FFFF' | ❓ |
| IPDS-9-047 | 11 | CODE | Option | X'30' | Mapping option (position) | X'30' | ❓ |
| IPDS-9-048 | 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units | X'0000'–X'7FFF' | ❓ |
| IPDS-9-049 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units | X'0000'–X'7FFF' | ❓ |
| IPDS-9-050 | 16 to end of BCOC | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'A2' Invoke Tertiary Resource triplet | ❓ |
| IPDS-9-051 | If an invalid or unsupported value is specified, exception ID X'0207..05' exists. | ❓ |
| IPDS-9-052 | “Invoke Tertiary Resource (X'A2') Triplet” | ❓ |
| IPDS-9-053 | The format of the BCDD is as follows: | ❓ |
| IPDS-9-054 | 0–1 | UBIN | Length | X'001B', X'001D' to end of BCDD | Length of BCDD, including this length field | X'001B', X'001D' to end of BCDD | ❓ |
| IPDS-9-055 | 2–3 | CODE | SDF ID | X'A6EB' | Self-defining-field ID | X'A6EB' | ❓ |
| IPDS-9-056 | 4 | CODE | Unit base | X'00'<br>X'01' | Ten inches<br>Ten centimeters | See byte description | ❓ |
| IPDS-9-057 | 5 | X'00' | Reserved | | | X'00' | ❓ |
| IPDS-9-058 | 6–7 | UBIN | X UPUB | X'0001'–X'7FFF' | Units per unit base for $X_{bc}$ | See byte description | ❓ |
| IPDS-9-059 | 8–9 | UBIN | Y UPUB | X'0001'–X'7FFF' | Units per unit base for $Y_{bc}$; must be the same as X UPUB | See byte description | ❓ |
| IPDS-9-060 | 10–11 | UBIN | $X_{bc}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{bc}$ extent of presentation space<br>Use WBCC BCOC value. If BCOC is absent use logical page X extent. | See byte description | ❓ |
| IPDS-9-061 | 12–13 | UBIN | $Y_{bc}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{bc}$ extent of presentation space<br>Use WBCC BCOC value. If BCOC is absent use logical page Y extent. | See byte description | ❓ |
| IPDS-9-062 | 14–15 | UBIN | Symbol width | X'0000'<br>X'0001'–X'7FFF' | Desired symbol width:<br>X'0000' = Not specified (use module width)<br>Desired width of bar code symbol in L-units | X'0000' | ❓ |
| IPDS-9-063 | 16 | CODE | Type | See byte description | Bar code type | See byte description | ❓ |
| IPDS-9-064 | 17 | CODE | Modifier | See byte description | Bar code modifier | See byte description | ❓ |
| IPDS-9-065 | 18 | CODE | LID | X'00'–X'FE'<br>X'FF' | Font Local ID<br>X'FF' = Printer default | See byte description | ❓ |
| IPDS-9-066 | 19–20 | CODE | Color | See byte description | Color | See byte description | ❓ |
| IPDS-9-067 | 21 | UBIN | Module width | X'01'–X'FE'<br>X'FF' | Module width in mils<br>X'FF' = Printer default | See byte description | ❓ |
| IPDS-9-068 | 22–23 | UBIN | Height | X'0001'–X'7FFF'<br>X'FFFF' | Element height in L-units<br>X'FFFF' = Printer default | See byte description | ❓ |
| IPDS-9-069 | 24 | UBIN | Multiplier | X'01'–X'FF' | Height multiplier | See byte description | ❓ |
| IPDS-9-070 | 25–26 | UBIN | W/N ratio | X'0000'<br>X'0001'–X'7FFF'<br>X'FFFF' | Wide-to-narrow ratio:<br>X'0000' = Bar Code does not use W/N ratio<br>Wide-to-narrow ratio<br>X'FFFF' = Printer default | See byte description | ❓ |
| IPDS-9-071 | 27 to end of BCDD | | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets:<br>X'4E' Color Specification triplet | ❓ |
| IPDS-9-072 | X'1302' in the Bar Code command-set vector – The desired symbol-width parameter is | ❓ |
| IPDS-9-073 | X'1304' in the Bar Code command-set vector – The full range of font local IDs is supported. | ❓ |
| IPDS-9-074 | X'FB00' in the Device-Control command-set vector – All architected units of measure are | ❓ |
| IPDS-9-075 | X'4E' triplets are specified, the last one specified is used and the others are ignored. | ❓ |
| IPDS-9-076 | X'1300' - Small symbol support | ❓ |
| IPDS-9-077 | X'1303' - Data Matrix encodation scheme support | ❓ |
| IPDS-9-078 | X'1305' - Bar code suppression support | ❓ |
| IPDS-9-079 | X'1306' - QR Code Special-Function Parameters too-much-data flag support | ❓ |
| IPDS-9-080 | X'1307' - Data Matrix Special-Function Parameters too-much-data flag support | ❓ |
| IPDS-9-081 | exception conditions. | ❓ |
| IPDS-10-001 | Table 50**. Object Container Commands | ❓ |
| IPDS-10-002 | DORE | X'D66C' | Data Object Resource Equivalence | No | ❓ |
| IPDS-10-003 | DORE2 | X'D66E' | Data Object Resource Equivalence 2 | No | ❓ |
| IPDS-10-004 | DDOFC | X'D65B' | Deactivate Data-Object-Font Component | No | ❓ |
| IPDS-10-005 | DDOR | X'D65C' | Deactivate Data Object Resource | No | ❓ |
| IPDS-10-006 | IDO | X'D67C' | Include Data Object | No | ❓ |
| IPDS-10-007 | RRR | X'D65A' | Remove Resident Resource | No | ❓ |
| IPDS-10-008 | RRRL | X'D659' | Request Resident Resource List | No | ❓ |
| IPDS-10-009 | WOCC | X'D63C' | Write Object Container Control | Yes | ❓ |
| IPDS-10-010 | WOC | X'D64C' | Write Object Container | Yes | ❓ |
| IPDS-10-011 | object container is saved as a data object resource to be presented later via an IDO command. If the WOCC is | ❓ |
| IPDS-10-012 | function is indicated by the X'6201' property pair that is returned in the Device-Control command-set vector of | ❓ |
| IPDS-10-013 | Object Container Area | ❓ |
| IPDS-10-014 | Object Container Output | ❓ |
| IPDS-10-015 | apply equally to tertiary resources, unless otherwise noted. | ❓ |
| IPDS-10-016 | The following table shows the valid secondary resources for each presentation data object: | ❓ |
| IPDS-10-017 | Table 51**. Secondary Resource Usage | ❓ |
| IPDS-10-018 | BCOCA bar code (QR Code with Image) | Presentation data object resource (see notes 2 and 3) | Yes, 2-byte image local ID | DOR | ❓ |
| IPDS-10-019 | Encapsulated PostScript (EPS) (with or without transparency) | Resident Color Profile (see note 3) | No | DOR | ❓ |
| IPDS-10-020 | IOCA image | IOCA Tile Resource | Yes, 4-byte local ID | DOR | ❓ |
| IPDS-10-021 | PDF single page or multi page (with or without transparency) | PDF Resource Object | Yes, PostScript name or string | DOR | ❓ |
| IPDS-10-022 | Non-OCA Resource object | Yes, PostScript name or string | DOR | ❓ |
| IPDS-10-023 | TrueType/OpenType Font (see note 4) | Yes, PostScript name or string | DOFC | ❓ |
| IPDS-10-024 | Resident Color Profile (see note 3) | No | DOR | ❓ |
| IPDS-10-025 | AFPC SVG Subset | Non-OCA Resource object | Yes, SVG name or string | DOR | ❓ |
| IPDS-10-026 | TrueType/OpenType Font (see note 4) | Yes, SVG name or string | DOFC | ❓ |
| IPDS-10-027 | DOR=Data-object-resource HAID pool; DOFC=Data-object-font-component HAID pool | ❓ |
| IPDS-10-028 | 1. When an attempt to use a secondary resource is made, if the specific combination of presentation data object type | ❓ |
| IPDS-10-029 | 2. The potential secondary-resource object types used by a BCOCA QR Code with Image bar code are the | ❓ |
| IPDS-10-030 | 3. Note that all object types in the first column above, other than BCOCA bar code, are potential secondary-resource | ❓ |
| IPDS-10-031 | 4. Because TrueType/OpenType Font secondary resources use the data-object-font-component HAID pool, the | ❓ |
| IPDS-10-032 | DORE2 command must be used to establish equivalence entries for them. | ❓ |
| IPDS-10-033 | type OID of the referenced secondary resource to determine the appropriate use for that resource. Only one | ❓ |
| IPDS-10-034 | remain in effect. The DORE command data field is defined as follows: | ❓ |
| IPDS-10-035 | Zero or more equivalence entries in the following format:** | ❓ |
| IPDS-10-036 | 0 | UBIN | Length | X'03'–X'FD' | Entry length, including this length field | X'03'–X'FD' | ❓ |
| IPDS-10-037 | 1–2 | CODE | HAID | X'0001'–X'7EFF'<br>X'E47D' | Host-Assigned ID of secondary resource<br>Extender entry | X'0001'–X'7EFF' | ❓ |
| IPDS-10-038 | 3 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource; this field is omitted if there is no internal ID for this resource | Any value | ❓ |
| IPDS-10-039 | could specify HAID=X'E47D' and contain the last 100 bytes of the internal resource ID. | ❓ |
| IPDS-10-040 | entries remain in effect. The DORE2 command data field is defined as follows: | ❓ |
| IPDS-10-041 | Zero or more equivalence entries in the following format:** | ❓ |
| IPDS-10-042 | 0–1 | UBIN | Length | X'0007'–X'7FFA' | Entry length, including this length field | X'0007'–X'7FFA' | ❓ |
| IPDS-10-043 | 2–3 | CODE | HAID | X'0001'–X'7EFF' | Host-Assigned ID of secondary resource | X'0001'–X'7EFF' | ❓ |
| IPDS-10-044 | 4 | CODE | HAID pool | X'01'<br>X'02' | HAID pool to search:<br>X'01' Data-object resource<br>X'02' Data-object-font component | X'01'<br>X'02' | ❓ |
| IPDS-10-045 | 5–6 | X'0000' | Reserved | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-10-046 | 7 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource; this field is omitted if there is no internal ID for this resource | Any value | ❓ |
| IPDS-10-047 | Data Object Resource Equivalence 2 (DORE2) | ❓ |
| IPDS-10-048 | Data Object Resource Equivalence 2 (DORE2) | ❓ |
| IPDS-10-049 | The DDOFC command data field is as follows: | ❓ |
| IPDS-10-050 | 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Data-object-font component Host-Assigned ID | X'0000'<br>X'0001'–X'7EFF' | ❓ |
| IPDS-10-051 | Deactivate Data-Object-Font Component (DDOFC) | ❓ |
| IPDS-10-052 | The DDOR command data field is as follows: | ❓ |
| IPDS-10-053 | 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Data object resource Host-Assigned ID | X'0000'<br>X'0001'–X'7EFF' | ❓ |
| IPDS-10-054 | A deactivate-all command when there are no active data object resources is effectively a NOP. | ❓ |
| IPDS-10-055 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-10-056 | EPS without transparency | ❓ |
| IPDS-10-057 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-10-058 | IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-10-059 | JPEG (Joint Photographic Experts Group) AFPC JPEG Subset | ❓ |
| IPDS-10-060 | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-10-061 | PCL (Printer Command Language) page object | ❓ |
| IPDS-10-062 | PDF (Portable Document Format) multiple-page file with transparency | ❓ |
| IPDS-10-063 | PDF multiple-page file without transparency | ❓ |
| IPDS-10-064 | PDF single page with transparency | ❓ |
| IPDS-10-065 | PDF single page without transparency | ❓ |
| IPDS-10-066 | PNG (Portable Network Graphics) AFPC PNG Subset | ❓ |
| IPDS-10-067 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-10-068 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-10-069 | TIFF with transparency | ❓ |
| IPDS-10-070 | TIFF without transparency | ❓ |
| IPDS-10-071 | TIFF multiple-image file with transparency | ❓ |
| IPDS-10-072 | TIFF multiple-image file without transparency | ❓ |
| IPDS-10-073 | UP3I print data | ❓ |
| IPDS-10-074 | 1. Data Object Area Position (DOAP), optional | ❓ |
| IPDS-10-075 | 2. Data Object Output Control (DOOC), optional | ❓ |
| IPDS-10-076 | 3. Data Object Data Descriptor (DODD), mandatory | ❓ |
| IPDS-10-077 | cached variation rather than rasterizing the object at include time. | ❓ |
| IPDS-10-078 | The format of the DOAP is as follows: | ❓ |
| IPDS-10-079 | 0–1 | UBIN | Length | X'000B' to end of DOAP | Length of DOAP, including this length field | X'000B' to end of DOAP | ❓ |
| IPDS-10-080 | 2–3 | CODE | SDF ID | X'ACC3' | Self-defining-field ID | X'ACC3' | ❓ |
| IPDS-10-081 | 4–5 | SBIN | X offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for object area origin; an I, I-offset, or $ coordinate position in L-units. Special value: X'FFFF' (Use X offset from object) | X'8000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-082 | 6–7 | SBIN | Y offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for object area origin; a B, B-offset, or $Y_{p}$ coordinate position in L-units. Special value: X'FFFF' (Use Y offset from object) | X'8000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-083 | 8–9 | CODE | Override for object area orientation | ❓ |
| IPDS-10-084 | bits 0–8 | Degrees | B'000000000'–B'101100111'<br>B'111111111' | Number of degrees (0–359) in the orientation. Special value: B'111111111' (see byte description) | B'000000000'<br>B'001011010'<br>B'010110100'<br>B'100001110'<br>B'111111111' | ❓ |
| IPDS-10-085 | bits 9–14 | Minutes | B'000000'–B'111011'<br>B'111111' | Number of minutes (0–59) in the orientation. Special value: B'111111' (see byte description) | B'000000'<br>B'111111' | ❓ |
| IPDS-10-086 | bit 15 | | B'0'<br>B'1' | Reserved. Special value: B'1' (see byte description) | B'0'<br>B'1' | ❓ |
| IPDS-10-087 | 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>X'00' Absolute $I$, absolute $B$<br>X'20' Absolute $I$, relative $B$<br>X'40' Relative $I$, absolute $B$<br>X'60' Relative $I$, relative $B$<br>X'A0' Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | ❓ |
| IPDS-10-088 | 11 to end of DOAP | UNDF | | | Data without architectural definition | ❓ |
| IPDS-10-089 | reference coordinate system specified in the IDO before applying the override. | ❓ |
| IPDS-10-090 | 0 degrees | ❓ |
| IPDS-10-091 | 90 degrees | ❓ |
| IPDS-10-092 | 180 degrees | ❓ |
| IPDS-10-093 | 270 degrees | ❓ |
| IPDS-10-094 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-10-095 | The format of the DOOC is as follows: | ❓ |
| IPDS-10-096 | 0–1 | UBIN | Length | X'0010', X'0012' to end of DOOC | Length of DOOC, including this length field | X'0010', X'0012' to end of DOOC | ❓ |
| IPDS-10-097 | 2–3 | CODE | SDF ID | X'ABC3' | Self-defining-field ID | X'ABC3' | ❓ |
| IPDS-10-098 | 4 | CODE | Unit base | X'00'<br>X'01' | X'00' Ten inches<br>X'01' Ten centimeters | X'00' | ❓ |
| IPDS-10-099 | 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-10-100 | 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use $X_{oa}$ extent from object) | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-101 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use $Y_{oa}$ extent from object) | X'0001'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-102 | 11 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'41'<br>X'42'<br>X'50'<br>X'60'<br>X'FF' | Override for mapping control option:<br>X'00' Position (not valid for IO Image)<br>X'10' Scale to fit<br>X'20' Center and trim<br>X'30' Position and trim<br>X'41' Point to pel (IO Image only)<br>X'42' Point to pel w/double dot (IO Image only)<br>X'50' Replicate and trim (IO Image only)<br>X'60' Scale to fill<br>X'FF' Use object's mapping option | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'FF' | ❓ |
| IPDS-10-103 | 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $X_{oa}$ offset in L-units; (for the position and position-and-trim mappings only). Special value: X'FFFF' (Use $X_{oa}$ offset from object) | X'0000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-104 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF'<br>X'FFFF' | Override for $Y_{oa}$ offset in L-units; (for the position and position-and-trim mappings only). Special value: X'FFFF' (Use $Y_{oa}$ offset from object) | X'0000'–X'7FFF'<br>(Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-105 | 16 to end of DOOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets | X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-10-106 | If an invalid or unsupported value is specified, exception ID X'0207..05' exists. | ❓ |
| IPDS-10-107 | Scale to fit for an object container | ❓ |
| IPDS-10-108 | Position and trim for an IO Image | ❓ |
| IPDS-10-109 | X'10'—Scale to fit | ❓ |
| IPDS-10-110 | X'20'—Center and trim | ❓ |
| IPDS-10-111 | X'30'—Position and trim | ❓ |
| IPDS-10-112 | X'60'—Scale to fill | ❓ |
| IPDS-10-113 | X'41'—Point to pel | ❓ |
| IPDS-10-114 | X'42'—Point to pel with double dot | ❓ |
| IPDS-10-115 | X'50'—Replicate and trim | ❓ |
| IPDS-10-116 | for a description of these mapping control options. | ❓ |
| IPDS-10-117 | Point to pel (X'41') and point to pel with double dot (X'42') is indicated by property pair | ❓ |
| IPDS-10-118 | Replicate and trim (X'50') is indicated by property pair X'F300' in the IO-Image command-set | ❓ |
| IPDS-10-119 | Scale to fill (X'60') is indicated by property pair X'F301' in the IO-Image command-set vector | ❓ |
| IPDS-10-120 | “Rendering Intent (X'95') Triplet” | ❓ |
| IPDS-10-121 | Invoke CMR (X'92') and Rendering Intent (X'95') triplets in the IDO command. | ❓ |
| IPDS-10-122 | The format of the DODD is as follows: | ❓ |
| IPDS-10-123 | 0–1 | UBIN | Length | X'0016', X'0018' to end of DODD | Length of DODD, including this length field | X'0016', X'0018' to end of DODD | ❓ |
| IPDS-10-124 | 2–3 | CODE | SDF ID | X'A6C3' | Self-defining-field ID | X'A6C3' | ❓ |
| IPDS-10-125 | 4–19 | | Reserved | X'00...00' | Reserved | X'00...00' | ❓ |
| IPDS-10-126 | 20–21 | CODE | HAID | X'0001'–X'7EFF' | Data object's Host-Assigned ID | X'0001'–X'7EFF' | ❓ |
| IPDS-10-127 | 22 to end of DODD | Triplets | | Zero or more of the following triplets: | X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet | ❓ |
| IPDS-10-128 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-10-129 | EPS without transparency | ❓ |
| IPDS-10-130 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-10-131 | IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-10-132 | JPEG (Joint Photographic Experts Group) AFPC JPEG Subset | ❓ |
| IPDS-10-133 | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-10-134 | PCL (Printer Command Language) page object | ❓ |
| IPDS-10-135 | PDF (Portable Document Format) multiple-page file with transparency | ❓ |
| IPDS-10-136 | PDF multiple-page file without transparency | ❓ |
| IPDS-10-137 | PDF single page with transparency | ❓ |
| IPDS-10-138 | PDF single page without transparency | ❓ |
| IPDS-10-139 | PNG (Portable Network Graphics) AFPC PNG Subset | ❓ |
| IPDS-10-140 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-10-141 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-10-142 | TIFF with transparency | ❓ |
| IPDS-10-143 | TIFF without transparency | ❓ |
| IPDS-10-144 | TIFF multiple-image file with transparency | ❓ |
| IPDS-10-145 | TIFF multiple-image file without transparency | ❓ |
| IPDS-10-146 | UP3I print data | ❓ |
| IPDS-10-147 | For bilevel IO-Image objects: | ❓ |
| IPDS-10-148 | The Color Specification (X'4E') triplet is not used with grayscale IO-Image objects. | ❓ |
| IPDS-10-149 | For object-container objects that contain bilevel image but do not specify an internal color value, the Color | ❓ |
| IPDS-10-150 | to be bilevel. Some presentation object containers can specify color by constructs within the object and, if | ❓ |
| IPDS-10-151 | For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a | ❓ |
| IPDS-10-152 | Property pair X'1201' in the Object Container command-set vector indicates support for the IDO command | ❓ |
| IPDS-10-153 | Property pair X'1202' in the IO-Image command-set vector indicates support for IO-Image objects as | ❓ |
| IPDS-10-154 | Property pair X'40nn' in the IO-Image command-set vector with any defined nn bit set to B'1' indicates | ❓ |
| IPDS-10-155 | Property pair X'4401' in the IO-Image command-set vector indicates support for the Set Extended Bilevel | ❓ |
| IPDS-10-156 | 1. When specified in the DODD, the X'4E' triplet is used to override the default color of a bilevel or grayscale | ❓ |
| IPDS-10-157 | 2. Color Specification (X'4E') triplets that are ignored may be syntax checked before they are ignored. | ❓ |
| IPDS-10-158 | any, from the WOCC command. | ❓ |
| IPDS-10-159 | Presentation Space Size (X'9C') triplet for an SVG object. | ❓ |
| IPDS-10-160 | Data object resource | ❓ |
| IPDS-10-161 | Data-object-font component | ❓ |
| IPDS-10-162 | The format of the data field for this command is as follows: | ❓ |
| IPDS-10-163 | Zero or one Object OIDs in the following format:** | ❓ |
| IPDS-10-164 | 0 | CODE | Identifier | X'06' | This is a definite-short-form OID | X'06' | ❓ |
| IPDS-10-165 | 1 | UBIN | Length | X'00'–X'7F' | Length of the following content bytes | X'00'–X'7F' | ❓ |
| IPDS-10-166 | 2 to end of OID | Content | | any value | Content bytes that provide a unique ID for this object | any value | ❓ |
| IPDS-10-167 | Remove Resident Resource | ❓ |
| IPDS-10-168 | Remove Resident Resource | ❓ |
| IPDS-10-169 | Data object resource | ❓ |
| IPDS-10-170 | Data-object-font component | ❓ |
| IPDS-10-171 | 1. These resource types are listed in Table 16 and Table 17 and cover all resident | ❓ |
| IPDS-10-172 | 2. Unlike the XOA-RRL command, deactivated resident resources that are listed in an RRRL reply can be | ❓ |
| IPDS-10-173 | Request Resident Resource List | ❓ |
| IPDS-10-174 | The format of the Special Data Area for the reply is as follows: | ❓ |
| IPDS-10-175 | Zero or more RRRL-reply entries in the following format:** | ❓ |
| IPDS-10-176 | 0–1 | UBIN | Entry Length | X'0010'–X'0193' | Length of RRRL-reply entry, including this length field | ❓ |
| IPDS-10-177 | Object OID** | ❓ |
| IPDS-10-178 | 2 | CODE | Identifier | X'06' | This is a definite-short-form OID | ❓ |
| IPDS-10-179 | 3 | UBIN | OID length | X'00'–X'7F' | Length of the following content bytes | ❓ |
| IPDS-10-180 | 4 to end of OID | Content | | any value | Content bytes that provide a unique ID for this object | ❓ |
| IPDS-10-181 | Object information** | ❓ |
| IPDS-10-182 | +0–7 | UBIN | Object type | X'00...00'–X'FF...FF' | Component ID of a registered object-type OID | ❓ |
| IPDS-10-183 | +8–9 | BITS | Object information flags | ❓ |
| IPDS-10-184 | bit 0 | Removable | B'1'<br>B'0' | B'1' The object is removable<br>B'0' The object is not removable | ❓ |
| IPDS-10-185 | bits 1–15 | | B'0...0' | Reserved | ❓ |
| IPDS-10-186 | +10–11 | | Reserved | X'0000' | Reserved | ❓ |
| IPDS-10-187 | Zero, one, or two human-readable name triplets** | ❓ |
| IPDS-10-188 | +12 to end of entry | Triplets | | See byte description | If triplets are available there can either be a X'01' triplet followed by a X'02' triplet, or a single X'02' triplet. The X'02' triplet must have FQN type X'DE' with a character-encoded name.<br>X'01' CGCSGID triplet<br>X'02' Fully Qualified Name triplet | ❓ |
| IPDS-10-189 | Request Resident Resource List | ❓ |
| IPDS-10-190 | 1. A Fully Qualified Name (X'02') triplet with FQN type X'DE' and with a character-encoded | ❓ |
| IPDS-10-191 | 2. Two triplets: a Coded Graphic Character Set Global Identifier (X'01') triplet to identify an | ❓ |
| IPDS-10-192 | 3. No triplets (used when there is no human-readable name available) | ❓ |
| IPDS-10-193 | Request Resident Resource List | ❓ |
| IPDS-10-194 | 1. Object container Area Position (OCAP), optional | ❓ |
| IPDS-10-195 | 2. Object container Output Control (OCOC), optional | ❓ |
| IPDS-10-196 | 3. Object container Data Descriptor (OCDD) | ❓ |
| IPDS-10-197 | Each self-defining field contains a two-byte length field, a two-byte self-defining field ID, and a data field. | ❓ |
| IPDS-10-198 | The format of the OCAP is as follows: | ❓ |
| IPDS-10-199 | 0–1 | UBIN | Length | X'000B' to end of OCAP | Length of OCAP, including this length field | X'000B' to end of OCAP | ❓ |
| IPDS-10-200 | 2–3 | CODE | SDF ID | X'AC6B' | Self-defining-field ID | X'AC6B' | ❓ |
| IPDS-10-201 | 4–5 | SBIN | X offset | X'8000'–X'7FFF' | Object container object area origin; an $X_{p}$, I, or I-offset coordinate position in L-units | X'8000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-10-202 | 6–7 | SBIN | Y offset | X'8000'–X'7FFF' | Object container object area origin; a $Y_{p}$, B, or B-offset coordinate position in L-units | X'8000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-10-203 | 8–9 | CODE | Object Container object area orientation | ❓ |
| IPDS-10-204 | bits 0–8 | Degrees | B'000000000'–B'101100111' | Number of degrees (0–359) in the orientation | B'000000000' | ❓ |
| IPDS-10-205 | bits 9–14 | Minutes | B'000000'–B'111011' | Number of minutes (0–59) in the orientation | B'000000' | ❓ |
| IPDS-10-206 | bit 15 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-10-207 | 10 | CODE | Coordinate system | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | Reference coordinate system:<br>X'00' Absolute $I$, absolute $B$<br>X'20' Absolute $I$, relative $B$<br>X'40' Relative $I$, absolute $B$<br>X'60' Relative $I$, relative $B$<br>X'A0' Page $X_{p}, Y_{p}$ | X'00'<br>X'20'<br>X'40'<br>X'60'<br>X'A0' | ❓ |
| IPDS-10-208 | 11 to end of OCAP | UNDF | | | Data without architectural definition | ❓ |
| IPDS-10-209 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. | ❓ |
| IPDS-10-210 | 0 degrees | ❓ |
| IPDS-10-211 | 90 degrees | ❓ |
| IPDS-10-212 | 180 degrees | ❓ |
| IPDS-10-213 | 270 degrees | ❓ |
| IPDS-10-214 | object area, using either the $,$ or the inline-baseline (I,B) coordinate system. | ❓ |
| IPDS-10-215 | If byte 10 equals X'00', the absolute inline and baseline coordinates determine the origin. | ❓ |
| IPDS-10-216 | If byte 10 equals X'20', the absolute inline and relative baseline coordinates determine the | ❓ |
| IPDS-10-217 | If byte 10 equals X'40', the relative inline and absolute baseline coordinates determine the | ❓ |
| IPDS-10-218 | If byte 10 equals X'60', the relative inline and baseline coordinates determine the origin. | ❓ |
| IPDS-10-219 | If byte 10 equals X'A0', the current logical page X | ❓ |
| IPDS-10-220 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-10-221 | The object area extent equals the size of the logical page; | ❓ |
| IPDS-10-222 | The scale-to-fit mapping control is used for all objects except for the following: | ❓ |
| IPDS-10-223 | The object area is not colored. | ❓ |
| IPDS-10-224 | No object-level CMRs. | ❓ |
| IPDS-10-225 | No object-level rendering intent. | ❓ |
| IPDS-10-226 | The format of the OCOC is as follows: | ❓ |
| IPDS-10-227 | 0–1 | UBIN | Length | X'0010', X'0012' to end of OCOC | Length of OCOC, including this length field | X'0010', X'0012' to end of OCOC | ❓ |
| IPDS-10-228 | 2–3 | CODE | SDF ID | X'A66B' | Self-defining-field ID | X'A66B' | ❓ |
| IPDS-10-229 | 4 | CODE | Unit base | X'00'<br>X'01' | X'00' Ten inches<br>X'01' Ten centimeters | X'00' | ❓ |
| IPDS-10-230 | 5–6 | UBIN | UPUB | X'0001'–X'7FFF' | $X_{oa}$ and $Y_{oa}$ units per unit base | X'3840' | ❓ |
| IPDS-10-231 | 7–8 | UBIN | $X_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $X_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use the LPD value) | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-232 | 9–10 | UBIN | $Y_{oa}$ extent | X'0001'–X'7FFF'<br>X'FFFF' | $Y_{oa}$ extent of object area in L-units. Special value: X'FFFF' (Use the LPD value) | X'0001'–X'7FFF' (Refer to the note following the table.)<br>X'FFFF' | ❓ |
| IPDS-10-233 | 11 | CODE | Mapping control | X'00'<br>X'10'<br>X'20'<br>X'30'<br>X'60'<br>X'70' | Mapping control option:<br>X'00' Position<br>X'10' Scale to fit<br>X'20' Center and trim<br>X'30' Position and trim<br>X'60' Scale to fill<br>X'70' UP3I Print Data | X'00'<br>X'10'<br>X'20'<br>X'30' | ❓ |
| IPDS-10-234 | 12–13 | SBIN | $X_{oa}$ offset | X'8000'–X'7FFF' | $X_{oa}$ offset in L-units; (for the position and position-and-trim mappings only) | X'0000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-10-235 | 14–15 | SBIN | $Y_{oa}$ offset | X'8000'–X'7FFF' | $Y_{oa}$ offset in L-units; (for the position and position-and-trim mappings only) | X'0000'–X'7FFF' (Refer to the note following the table.) | ❓ |
| IPDS-10-236 | 16 to end of OCOC | Triplets | | Zero or more optional triplets; not all IPDS printers support these triplets: | X'4E' Color Specification triplet<br>X'70' Presentation Space Reset Mixing triplet<br>X'92' Invoke CMR triplet<br>X'95' Rendering Intent triplet | ❓ |
| IPDS-10-237 | when this object is printed in a page or overlay. | ❓ |
| IPDS-10-238 | X'00'—Position | ❓ |
| IPDS-10-239 | X'10'—Scale to fit | ❓ |
| IPDS-10-240 | X'20'—Center and trim | ❓ |
| IPDS-10-241 | X'30'—Position and trim | ❓ |
| IPDS-10-242 | X'60'—Scale to fill | ❓ |
| IPDS-10-243 | X'70'—UP3I print data | ❓ |
| IPDS-10-244 | “Rendering Intent (X'95') Triplet” | ❓ |
| IPDS-10-245 | triplets in the WBCC command. | ❓ |
| IPDS-10-246 | in the Object Container | ❓ |
| IPDS-10-247 | in the Object Container | ❓ |
| IPDS-10-248 | in the Object Container | ❓ |
| IPDS-10-249 | X offset specified in OCOC | ❓ |
| IPDS-10-250 | in the Object Container | ❓ |
| IPDS-10-251 | Object Container Object Area | ❓ |
| IPDS-10-252 | If all pages that have not yet reached the Jam-Recovery station can be discarded or marked as waste | ❓ |
| IPDS-10-253 | If the recovery requires operator intervention, IPDS exception ID X'407E..00' is used when this error is | ❓ |
| IPDS-10-254 | AFP mixing rules applied | ❓ |
| IPDS-10-255 | Area coloring applied | ❓ |
| IPDS-10-256 | Object area origin and size translated | ❓ |
| IPDS-10-257 | Object area rotation translated relative | ❓ |
| IPDS-10-258 | No mixing with AFP data | ❓ |
| IPDS-10-259 | Appearance of object is defined | ❓ |
| IPDS-10-260 | across the UP3I interface to the target device. The printer ensures that the UP3I Print Data object is sent for | ❓ |
| IPDS-10-261 | The format of the OCDD is as follows: | ❓ |
| IPDS-10-262 | 0–1 | UBIN | Length | X'0016', X'0018' to end of OCDD | Length of OCDD, including this length field | X'0016', X'0018' to end of OCDD | ❓ |
| IPDS-10-263 | 2–3 | CODE | SDF ID | X'A692' | Self-defining-field ID | X'A692' | ❓ |
| IPDS-10-264 | 4–19 | CODE | Object type OID | See byte description. | Registered object-type OID | See byte description. | ❓ |
| IPDS-10-265 | 20–21 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | X'0000' No value specified<br>X'0001'–X'7EFF' Data object resource or data-object-font component Host-Assigned ID | X'0000' | ❓ |
| IPDS-10-266 | 22 to end of OCDD | Triplets | | Zero or more of the following triplets: | X'02' Fully Qualified Name triplet<br>X'4E' Color Specification triplet<br>X'5A' Object Offset triplet<br>X'91' Color Management Resource Descriptor triplet<br>X'9A' Image Resolution triplet<br>X'9C' Object Container Presentation Space Size triplet | ❓ |
| IPDS-10-267 | For a home-state WOCC command that downloads a data object resource (other than a | ❓ |
| IPDS-10-268 | For a page-state or overlay-state WOCC command, the HAID is not used and is ignored. In | ❓ |
| IPDS-10-269 | The X'91' triplet is not used with other data-object-resource types and, if specified, is ignored. | ❓ |
| IPDS-10-270 | For object-container objects that contain bilevel image but do not specify an internal color value, the Color | ❓ |
| IPDS-10-271 | For object-container objects that contain grayscale image, the Color Specification (X'4E') triplet specifies a | ❓ |
| IPDS-10-272 | the triplet is used and overrides the resolution inside the image, if any. However, if an image resolution is not | ❓ |
| IPDS-10-273 | have a font OID at the time a glyph run is processed. | ❓ |
| IPDS-10-274 | X'020D..01' for a non-presentation object | ❓ |
| IPDS-10-275 | X'020D..05' for a presentation object | ❓ |
| IPDS-10-276 | X'025D..ee' for a Color Management Resource (CMR) object | ❓ |
| IPDS-10-277 | Note: Only Anystate commands are valid between concatenated WOC commands; refer to Figure 45 for a list of Anystate commands. | ❓ |
| IPDS-11-001 | Table 52. Metadata Commands | ❓ |
| IPDS-11-002 | DHM | X'D658' | “Delete Home-State Metadata” | Yes | ❓ |
| IPDS-11-003 | WMC | X'D68A' | “Write Metadata control” | Yes | ❓ |
| IPDS-11-004 | WM | X'D68B' | “Write Metadata” | Yes | ❓ |
| IPDS-11-005 | metadata is deleted with a Delete Home-State Metadata (DHM) command. | ❓ |
| IPDS-11-006 | The DHM command data field is as follows: | ❓ |
| IPDS-11-007 | 0–1 | UBIN | MDLevel | X'0000'<br>X'0001' – X'FFFF' | Used to delete all home-state metadata<br>Level of the home-state metadata being deleted | X'0000'<br>X'0001' – X'FFFF' | ❓ |
| IPDS-11-008 | 2–3 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-11-009 | Metadata Data Descriptor (MDD) | ❓ |
| IPDS-11-010 | The format of the MDD is as follows: | ❓ |
| IPDS-11-011 | 0–1 | UBIN | Length | X'0008' to end of MDD | Length of MDD, including this length field | X'0008' to end of MDD | ❓ |
| IPDS-11-012 | 2–3 | CODE | SDF ID | X'A6BC' | Self-defining-field ID | X'A6BC' | ❓ |
| IPDS-11-013 | 4–5 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-11-014 | 6–7 | UBIN | MDLevel | X'0001' – X'FFFF' | Level of the home-state metadata being added | X'0001' – X'FFFF' | ❓ |
| IPDS-11-015 | 8 to end of MDD | UNDF | | | Data without architectural definition | ❓ |
| IPDS-11-016 | but ignore this field; generators should not specify this field. | ❓ |
| IPDS-11-017 | Note: Only Anystate commands are valid between concatenated WM commands; refer to Figure 45 for a list of Anystate commands. | ❓ |
| IPDS-12-001 | Table 53. Overlay Commands** | ❓ |
| IPDS-12-002 | BO | X'D6DF' | Begin Overlay | Yes | ❓ |
| IPDS-12-003 | DO | X'D6EF' | Deactivate Overlay | Yes | ❓ |
| IPDS-12-004 | IO | X'D67D' | Include Overlay | Yes | ❓ |
| IPDS-12-005 | Support for page-overlay rotation is indicated by a X'A004' property pair in the Overlay command-set vector of | ❓ |
| IPDS-12-006 | operates exactly as it does on a page. | ❓ |
| IPDS-12-007 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-12-008 | Basic support: Supported by all printers that support overlays. | ❓ |
| IPDS-12-009 | 0 | CODE | Overlay ID | X'01'–X'FE' | Overlay ID | X'01'–X'FE' | ❓ |
| IPDS-12-010 | Extended support: Optional support identified by the X'1102' property pair in the Overlay command-set vector of an STM reply. | ❓ |
| IPDS-12-011 | 0–1 | CODE | Overlay HAID | X'0001'–X'7EFF' | Overlay HAID | X'0001'–X'7EFF' | ❓ |
| IPDS-12-012 | command is sent to a printer that provides only basic support. | ❓ |
| IPDS-12-013 | Basic support: Supported by all printers that support overlays. | ❓ |
| IPDS-12-014 | 0 | CODE | Overlay ID | X'00'<br>X'01'–X'FE' | Deactivate All indicator<br>Overlay ID | X'00'<br>X'01'–X'FE' | ❓ |
| IPDS-12-015 | Extended support: Optional support identified by the X'1102' property pair in the Overlay command-set vector of an STM reply. | ❓ |
| IPDS-12-016 | 0–1 | CODE | Overlay HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate all indicator<br>Overlay HAID | X'0000'<br>X'0001'–X'7EFF' | ❓ |
| IPDS-12-017 | overlays. The value X'0000' and all values in the range X'0001' – X'7EFF' are supported by the | ❓ |
| IPDS-12-018 | command is sent to a printer that provides only basic support. | ❓ |
| IPDS-12-019 | specifically with the overlay. | ❓ |
| IPDS-12-020 | The format of the IO command data is as follows: | ❓ |
| IPDS-12-021 | 0–1 | CODE | HAID | X'0001'–X'7EFF' | Overlay Host-Assigned ID | X'0001'–X'00FE' | ❓ |
| IPDS-12-022 | 2 | CODE | Type | X'00'<br>X'01' | Overlay type:<br>X'00' Nonsecure overlay<br>X'01' Secure overlay | X'00' | ❓ |
| IPDS-12-023 | 3–5 | SBIN | $X_{p}$ offset | X'FF8000'–X'007FFF'<br>X'FFFFFF' | $X_{p}$ offset from the logical-page origin. Special value: X'FFFFFF' (Use the current position) | X'FF8000'–X'007FFF' (Refer to the note following the table.) | ❓ |
| IPDS-12-024 | 6 | CODE | Overlay use | X'00'<br>X'01' | Intended use for this overlay:<br>X'00' Page overlay<br>X'01' Preprinted form overlay | X'00' | ❓ |
| IPDS-12-025 | 7–9 | SBIN | $Y_{p}$ offset | X'FF8000'–X'007FFF'<br>X'FFFFFF' | $Y_{p}$ offset from the logical-page origin. Special value: X'FFFFFF' (Use the current position) | X'FF8000'–X'007FFF' (Refer to the note following the table.) | ❓ |
| IPDS-12-026 | Optional page-overlay rotation; only allowed if X'A004' property pair returned in STM reply** | ❓ |
| IPDS-12-027 | 10–11 | CODE | Orientation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Page-overlay orientation:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | Not supported in OL1 | ❓ |
| IPDS-12-028 | supported-range requirements is provided in the section titled “L-Unit Range Conversion Algorithm”. | ❓ |
| IPDS-12-029 | This three-byte parameter defines the $X_{p}$ position of the overlay as an offset from the origin of the containing logical page. This parameter is expressed in L-units (defined by the LPD data). A value of X'FFFFFF' causes this coordinate to default to the $X_{p}$ value of the current text coordinate ($I_{c}, B_{c}$); to interpret X'FFFFFF', the current text position ($I_{c}, B_{c}$) must be first converted to an ($X_{p}, Y_{p}$) coordinate value. Exception ID X'02AE..01' exists if an invalid or unsupported $X_{p}$-offset value is specified. | ❓ |
| IPDS-12-030 | merged before returning to home state and updating page and copy counters. If a | ❓ |
| IPDS-12-031 | exception ID X'0202..02' exists. | ❓ |
| IPDS-13-001 | Table 54. Page Segment Commands** | ❓ |
| IPDS-13-002 | BPS | X'D65F' | Begin Page Segment | Yes | ❓ |
| IPDS-13-003 | DPS | X'D66F' | Deactivate Page Segment | Yes | ❓ |
| IPDS-13-004 | IPS | X'D67F' | Include Page Segment | Yes | ❓ |
| IPDS-13-005 | distinction. Soft page segments do not exist at the IPDS level. | ❓ |
| IPDS-13-006 | The BPS command data field is as follows: | ❓ |
| IPDS-13-007 | 0–1 | CODE | HAID | X'0001'–X'7EFF' | Page Segment Host-Assigned ID | X'0001'–X'007F' | ❓ |
| IPDS-13-008 | The DPS command data field is as follows: | ❓ |
| IPDS-13-009 | 0–1 | CODE | HAID | X'0000'<br>X'0001'–X'7EFF' | Deactivate All indicator<br>Page Segment Host-Assigned ID | X'0000'<br>X'0001'–X'007F' | ❓ |
| IPDS-13-010 | The IPS command data field is as follows: | ❓ |
| IPDS-13-011 | 0–1 | CODE | HAID | X'0001'–X'7EFF' | Page Segment Host-Assigned ID | X'0001'–X'007F' | ❓ |
| IPDS-14-001 | Table 55. Loaded-Font Commands | ❓ |
| IPDS-14-002 | LCP | X'D61B' | Load Code Page | LF3, LF4 | ❓ |
| IPDS-14-003 | LCPC | X'D61A' | Load Code Page Control | LF3, LF4 | ❓ |
| IPDS-14-004 | LF | X'D62F' | Load Font | LF1, LF3 | ❓ |
| IPDS-14-005 | LFCSC | X'D619' | Load Font Character Set Control | LF3 | ❓ |
| IPDS-14-006 | LFC | X'D61F' | Load Font Control | LF1 | ❓ |
| IPDS-14-007 | LFI | X'D60F' | Load Font Index | LF1 | ❓ |
| IPDS-14-008 | LSS | X'D61E' | Load Symbol Set | LF2 | ❓ |
| IPDS-14-009 | A LF1-type coded font, that consists of a fully described font plus font indexes, or of several fully described | ❓ |
| IPDS-14-010 | A LF2-type coded font, that consists of a symbol set | ❓ |
| IPDS-14-011 | A LF3-type coded font, that consists of a code page plus a font character set | ❓ |
| IPDS-14-012 | To see which types of coded font are supported by your printer, refer to your printer documentation. | ❓ |
| IPDS-14-013 | The left-character edge for a 0° font inline sequence | ❓ |
| IPDS-14-014 | The right-character edge for a 180° font inline sequence | ❓ |
| IPDS-14-015 | The top-character edge for a 90° font inline sequence | ❓ |
| IPDS-14-016 | The bottom-character edge for a 270° font inline sequence | ❓ |
| IPDS-14-017 | component of the character reference point, that coincides with the print position when the character is printed. | ❓ |
| IPDS-14-018 | 0  font inline sequence | ❓ |
| IPDS-14-019 | 180  font inline sequence | ❓ |
| IPDS-14-020 | 180  inline direction | ❓ |
| IPDS-14-021 | 90  font inline sequence | ❓ |
| IPDS-14-022 | 270  font inline sequence | ❓ |
| IPDS-14-023 | 90  inline direction | ❓ |
| IPDS-14-024 | 270  inline direction | ❓ |
| IPDS-14-025 | Table 56. Identifying the Baseline Offset Value | ❓ |
| IPDS-14-026 | 0° | Parallel to the top and bottom character box reference edges. | The number of L-units from the character reference point to the top character box reference edge. | ❓ |
| IPDS-14-027 | 90° | Parallel to the left and right character box reference edges. | The number of L-units from the character reference point to the right character box reference edge. | ❓ |
| IPDS-14-028 | 180° | Parallel to the top and bottom character box reference edges. | The number of L-units from the character reference point to the bottom character box reference edge. | ❓ |
| IPDS-14-029 | 270° | Parallel to the left and right character box reference edges. | The number of L-units from the character reference point to the left character box reference edge. | ❓ |
| IPDS-14-030 | Offset A-Space A-Space | ❓ |
| IPDS-14-031 | 107 is 270°. | ❓ |
| IPDS-14-032 | a b c d e f g h l k j | ❓ |
| IPDS-14-033 | A fully described font, downloaded using the LFC, LF , and End commands. For a double-byte coded font, | ❓ |
| IPDS-14-034 | A font index, downloaded using the LFI command. There can be 1 to 4 font indexes for each fully described | ❓ |
| IPDS-14-035 | fully described font section as it prints graphic characters from that font. | ❓ |
| IPDS-14-036 | The order in which the entries are listed. This order defines the code point that specifies the character pattern | ❓ |
| IPDS-14-037 | That no mandatory correlation exists between the index entry order and the order of the character pattern | ❓ |
| IPDS-14-038 | 40 bytes of font control information | ❓ |
| IPDS-14-039 | 32 bytes of font control information | ❓ |
| IPDS-14-040 | 256 font index entry | ❓ |
| IPDS-14-041 | 32 bytes of font control | ❓ |
| IPDS-14-042 | Font Inline Sequence = 2D00 | ❓ |
| IPDS-14-043 | A font character set, downloaded using the LFCSC, LF , and END commands. | ❓ |
| IPDS-14-044 | A code page, downloaded using the LCPC, LCP , and END commands. | ❓ |
| IPDS-14-045 | font inline sequence of a coded font. | ❓ |
| IPDS-14-046 | Equate a local ID (specified in SCFL control sequences, Set Character Set drawing orders, or BCDD self- | ❓ |
| IPDS-14-047 | Specify the font-index table to use for character processing by specifying the font inline sequence. | ❓ |
| IPDS-14-048 | Downloading coded font components. | ❓ |
| IPDS-14-049 | Activating a resident coded font with an Activate Resource command or a Load Font Equivalence command. | ❓ |
| IPDS-14-050 | Combining appropriate downloaded and resident components and giving them the same HAID. | ❓ |
| IPDS-14-051 | and use the second byte as a code point within that section. | ❓ |
| IPDS-14-052 | The data for the LCP command consists of LCP entries in the following format: | ❓ |
| IPDS-14-053 | Zero or more code point entries in the following format:** | ❓ |
| IPDS-14-054 | 0–7 | CHAR | GCGID | Any value | Graphic Character Global ID | Any value | ❓ |
| IPDS-14-055 | 8 | BITS | Processing flags | ❓ |
| IPDS-14-056 | bit 0 | Undefined | B'0', B'1' | Undefined | B'0', B'1' | ❓ |
| IPDS-14-057 | bit 1 | Noprint | B'0', B'1' | Nonprinting | B'0', B'1' | ❓ |
| IPDS-14-058 | bit 2 | Noincr | B'0', B'1' | Nonincrementing | B'0', B'1' | ❓ |
| IPDS-14-059 | bits 3–7 | | B'00000' | Reserved | B'00000' | ❓ |
| IPDS-14-060 | 9 or 9–10 | CODE | Code point | Any value | Code point | Any value | ❓ |
| IPDS-14-061 | Zero or one Unicode-mapping-entry in the following format:** | ❓ |
| IPDS-14-062 | + 0 | UBIN | Count | X'00'–X'FF' | Number of Unicode scalar values | See byte description | ❓ |
| IPDS-14-063 | Zero or more Unicode-scalar-value entries (depending on count value) in the following format:** | ❓ |
| IPDS-14-064 | ++ 0–3 | UBIN | Unicode scalar value | X'00000000' – X'FFFFFFFF' (excluding surrogates) | Unicode scalar value to be mapped to the GCGID value and code point value | See byte description | ❓ |
| IPDS-14-065 | character checks is blocked through the XOA Exception-Handling Control. If | ❓ |
| IPDS-14-066 | If a default GCGID is specified in the LCPC command, the default GCGID and its processing | ❓ |
| IPDS-14-067 | If the default GCGID from the code page is used, but does not have a corresponding | ❓ |
| IPDS-14-068 | If the LCPC command does not contain a default GCGID, the code point is treated as | ❓ |
| IPDS-14-069 | X'00' indicates that there are no Unicode scalar values mapped to this code point. | ❓ |
| IPDS-14-070 | value is registered), such as X'00FC' (ü). | ❓ |
| IPDS-14-071 | The data for the LCPC command contains the following information: | ❓ |
| IPDS-14-072 | 0–1 | CODE | HAID | X'0001' – X'7EFF' | Code Page Host-Assigned ID | X'0001' – X'7EFF' | ❓ |
| IPDS-14-073 | 2–3 | BITS | Encoding Scheme | ❓ |
| IPDS-14-074 | bits 0–3 | | B'0000' | Reserved | B'0000' | ❓ |
| IPDS-14-075 | bits 4–7 | Code point size | B'0001', B'0010' | Number of bytes indicator:<br>Fixed single byte<br>Fixed double byte | B'0001' | ❓ |
| IPDS-14-076 | bits 8–15 | | B'00000000' | Reserved | B'00000000' | ❓ |
| IPDS-14-077 | 4–7 | UBIN | Byte count | X'0000000A' – X'FFFFFFFF' | Number of data bytes in subsequent LCP commands | X'0000000A' – X'00000A00' | ❓ |
| IPDS-14-078 | 8 | BITS | Extension flags | ❓ |
| IPDS-14-079 | bit 0 | Unicode | B'0', B'1' | Unicode entries provided:<br>No<br>Yes | B'0', B'1' | ❓ |
| IPDS-14-080 | bits 1–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-14-081 | 9 | X'00' | | | Reserved | X'00' | ❓ |
| IPDS-14-082 | 10–n | CODE | Space | Any value | Variable-space code point | Any value | ❓ |
| IPDS-14-083 | GRID information if required (see byte description):** | ❓ |
| IPDS-14-084 | n+1 to n+2 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description | ❓ |
| IPDS-14-085 | n+3 to n+4 | CODE | CPGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Code Page Global ID | See byte description | ❓ |
| IPDS-14-086 | Default-character information (see byte description):** | ❓ |
| IPDS-14-087 | n+5 to n+12 | CODE | Default GCGID | Any value | Default Graphic Character Global ID | Any value | ❓ |
| IPDS-14-088 | n+13 | BITS | Processing flags for the default GCGID | ❓ |
| IPDS-14-089 | bit 0 | Undefined | B'0', B'1' | Undefined | B'0', B'1' | ❓ |
| IPDS-14-090 | bit 1 | Noprint | B'0', B'1' | Nonprinting | B'0', B'1' | ❓ |
| IPDS-14-091 | bit 2 | Noincr | B'0', B'1' | Nonincrementing | B'0', B'1' | ❓ |
| IPDS-14-092 | bits 3–7 | | B'00000' | Reserved; bit 4 retired | B'00000' | ❓ |
| IPDS-14-093 | Zero or one Unicode scalar value for the default GCGID (only present when the Unicode flag (byte 8) is B'1')** | ❓ |
| IPDS-14-094 | + 0–3 | UBIN | Unicode scalar value | X'00000000' – X'FFFFFFFF' (excluding surrogates) | Unicode scalar value to be mapped to the default GCGID value | See byte description | ❓ |
| IPDS-14-095 | unsupported byte-count value is specified. | ❓ |
| IPDS-14-096 | means that no GCSGID information is supplied. | ❓ |
| IPDS-14-097 | The printer requires a valid CPGID value and one isn't supplied. | ❓ |
| IPDS-14-098 | The printer uses the CPGID value, but an invalid value is specified. | ❓ |
| IPDS-14-099 | When no code-point entry has been specified for a code point | ❓ |
| IPDS-14-100 | When a code point without a corresponding character in the font character set has been | ❓ |
| IPDS-14-101 | treat this as a variable-space code point. | ❓ |
| IPDS-14-102 | Bit 4 of the Processing flags is retired as Retired item 133. | ❓ |
| IPDS-14-103 | in all TrueType/OpenType fonts used with this code page. | ❓ |
| IPDS-14-104 | Load Font Control (LFC) | ❓ |
| IPDS-14-105 | Load Font Character Set Control (LFCSC) | ❓ |
| IPDS-14-106 | A consecutive sequence of Load Font (LF) commands transmits the character raster patterns for both single- | ❓ |
| IPDS-14-107 | Font character raster patterns are received as a string of bits representing the character. B'1' indicates a | ❓ |
| IPDS-14-108 | The first scan received is the top reference edge of the character box. The last scan line received is the | ❓ |
| IPDS-14-109 | An End command is valid only after all of the font data has been transmitted. | ❓ |
| IPDS-14-110 | LF1 format** When downloading a fully described font (LF1 format), the LF data consists of a series of character raster-pattern bit strings. The data for the LF command contains the following information: | ❓ |
| IPDS-14-111 | 0 to end | UNDF | Font data | Any value | Character raster-pattern bit string | Any value | ❓ |
| IPDS-14-112 | LF3 format** When downloading a font character set (LF3 format), the LF data consists of a character ID map followed by zero or more technology-specific objects. | ❓ |
| IPDS-14-113 | 0 to n | Character ID map | | See detail description | Character ID map | See detail description | ❓ |
| IPDS-14-114 | Zero or more technology-specific objects in the following format:** | ❓ |
| IPDS-14-115 | n+1 to end | Tech object | | See detail description | Technology-specific object | See detail description | ❓ |
| IPDS-14-116 | exception ID X'02B1..04' exists. | ❓ |
| IPDS-14-117 | The character ID map contains the following information: | ❓ |
| IPDS-14-118 | 0 | CODE | IBM format | X'02' | IBM character ID format, IBM Registered EBCDIC GCGID | X'02' | ❓ |
| IPDS-14-119 | 1 | CODE | Technology format | X'03', X'05' | Technology-specific character ID format:<br>X'03' Font-specific ASCII character name, used with Type 1 PFB fonts<br>X'05' CMAP binary code point, used with CID-keyed fonts | At least one value | ❓ |
| IPDS-14-120 | Zero or more entries in the following format:** | ❓ |
| IPDS-14-121 | +0–7 | CHAR | GCGID | Any value | IBM character ID as used in code pages | Any value | ❓ |
| IPDS-14-122 | +8–11 | UBIN | Offset | Any value | Offset from the beginning of the character ID map (byte 0), into the following list of technology-specific ID entries; each GCGID maps to exactly one technology-specific ID | Any value | ❓ |
| IPDS-14-123 | Zero or more technology-specific ID entries in the following format:** | ❓ |
| IPDS-14-124 | ++0 | UBIN | Length | X'02' – X'80' | Length of technology-specific ID entry, including this length field | X'02' – X'80' | ❓ |
| IPDS-14-125 | ++ 1–n | UNDF | Tech ID | Any value | Technology-specific ID | Any value | ❓ |
| IPDS-14-126 | supported technology format. | ❓ |
| IPDS-14-127 | Each of the technology-specific objects contain the following information: | ❓ |
| IPDS-14-128 | 0–3 | UBIN | Length | X'0000000A' – X'FFFFFFFF' | Length of this technology-specific object, including this length field | X'0000000A' – X'FFFFFFFF' | ❓ |
| IPDS-14-129 | 4–7 | UBIN | Checksum | Any value | Checksum value | Any value | ❓ |
| IPDS-14-130 | 8–9 | UBIN | Identifier length | X'0002' – X'FFFF' | Length of the object identifier (bytes 8–n) | X'0002' – X'FFFF' | ❓ |
| IPDS-14-131 | 10–n | CHAR | Identifier | Any value | Object identifier for this technology-specific object | Any value | ❓ |
| IPDS-14-132 | Bytes n+1 to m are only present for CID-keyed fonts. These bytes are omitted for Type 1 PFB fonts.** | ❓ |
| IPDS-14-133 | n+1 to n+2 | UBIN | Descriptor length | X'0002' – X'FFFF' | Length of the object descriptor (bytes n+1 to m) | X'0002' – X'FFFF' | ❓ |
| IPDS-14-134 | n+3 | CODE | Object type | X'00', X'01', X'05', X'06', X'07', X'08' | Object type:<br>X'00' No object type specified<br>X'01' CMap file<br>X'05' CID file<br>X'06' PFB file<br>X'07' AFM file<br>X'08' Filename Map file (for example, ATMDATA.DAT) | X'00', X'01', X'05', X'06', X'07', X'08' | ❓ |
| IPDS-14-135 | n+4 to m | | Object-type specific information | See byte description | Object-type specific information | See byte description | ❓ |
| IPDS-14-136 | m+1 to end | UBIN | Object data | Any value | Object data as defined for the specific technology | Any value | ❓ |
| IPDS-14-137 | The 1st byte of object data becomes byte 0 of the array (most significant byte). | ❓ |
| IPDS-14-138 | The 2nd byte of object data becomes byte 1 of the array. | ❓ |
| IPDS-14-139 | The 3rd byte of object data becomes byte 2 of the array. | ❓ |
| IPDS-14-140 | The 4th byte of object data becomes byte 3 of the array (least significant byte). | ❓ |
| IPDS-14-141 | done such that the 5th byte is added to the value in array position 0, the 6th byte to array | ❓ |
| IPDS-14-142 | Unrecognized values are treated as if X'00' had been specified. | ❓ |
| IPDS-14-143 | For types X'00' and X'06'–X'08', this field is not defined by architecture and is ignored. | ❓ |
| IPDS-14-144 | For type X'01' (CMap file), five fields in the following sequence: | ❓ |
| IPDS-14-145 | n+4 | CODE | Precedence | X'00', X'01' | X'00' Primary<br>X'01' Auxiliary | X'00', X'01' | ❓ |
| IPDS-14-146 | n+5 | CODE | Linkage | X'00', X'01' | X'00' Character ID map linked<br>X'01' Not character ID map linked | X'00', X'01' | ❓ |
| IPDS-14-147 | n+6 | CODE | Writing direction | X'00', X'01', X'02', X'03' | X'00' No writing direction specified<br>X'01' Horizontal<br>X'02' Vertical<br>X'03' Both horizontal and vertical | X'00', X'01', X'02', X'03' | ❓ |
| IPDS-14-148 | n+7 to n+8 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | X'0000', X'0001' – X'FFFE', X'FFFF' | ❓ |
| IPDS-14-149 | n+9 to n+10 | CODE | CPGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Code Page Global ID<br>Use default value | X'0000', X'0001' – X'FFFE', X'FFFF' | ❓ |
| IPDS-14-150 | X'03' Both horizontal and vertical | ❓ |
| IPDS-14-151 | For type X'05' (CID file), three fields in the following sequence: | ❓ |
| IPDS-14-152 | n+4 | CODE | Precedence | X'00', X'01' | X'00' Primary<br>X'01' Auxiliary | X'00', X'01' | ❓ |
| IPDS-14-153 | n+5 to n+6 | UBIN | Maximum V(y) | Any value | Maximum V(y) value for all characters in the CID font | Any value | ❓ |
| IPDS-14-154 | n+7 to n+8 | UBIN | Maximum W(y) | Any value | Maximum W(y) value for all characters in the CID font | Any value | ❓ |
| IPDS-14-155 | Any additional bytes are ignored. | ❓ |
| IPDS-14-156 | specific object is missing, exception ID X'02B1..0B' exists. | ❓ |
| IPDS-14-157 | The data for the LFCSC command contains the following information: | ❓ |
| IPDS-14-158 | 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font character set Host-Assigned ID | X'0001' – X'7EFF' | ❓ |
| IPDS-14-159 | 2–3 | X'0000' | | | Reserved | X'0000' | ❓ |
| IPDS-14-160 | 4 | CODE | Pattern technology | X'1E', X'1F' | Pattern Technology ID:<br>X'1E' CID-keyed technology<br>X'1F' Type 1 PFB technology | At least one value | ❓ |
| IPDS-14-161 | 5 | X'00' | | | Reserved | X'00' | ❓ |
| IPDS-14-162 | 6 | BITS | Intended-use flags | ❓ |
| IPDS-14-163 | bit 0 | MICR | B'0', B'1' | Intended for MICR printing | B'0' | ❓ |
| IPDS-14-164 | bit 1 | Extension | B'0', B'1' | This is a FCS extension | B'0' | ❓ |
| IPDS-14-165 | bits 2–7 | | B'000000' | Reserved | B'000000' | ❓ |
| IPDS-14-166 | 7–10 | UBIN | Load Font count | X'00000002' – X'FFFFFFFF' | Number of data bytes carried in subsequent Load Font commands | X'00000002' | ❓ |
| IPDS-14-167 | 11–14 | UBIN | Map size | X'00000002' – X'FFFFFFFF' | Number of bytes in the character ID map | X'00000002' | ❓ |
| IPDS-14-168 | 15–16 | UBIN | Character ID count | Any value | Number of GCGIDs in the character ID map | Any value | ❓ |
| IPDS-14-169 | GRID information if required (see byte description):** | ❓ |
| IPDS-14-170 | 17–18 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description. | ❓ |
| IPDS-14-171 | 19–20 | CODE | FGID | X'0001' – X'FFFE' | Font Typeface Global ID | See byte description. | ❓ |
| IPDS-14-172 | Load Font Character Set Control (LFCSC) | ❓ |
| IPDS-14-173 | Load Font Character Set Control (LFCSC) | ❓ |
| IPDS-14-174 | The printer requires a valid FGID value and one isn't supplied. | ❓ |
| IPDS-14-175 | The printer uses the FGID value, but an invalid value is specified. | ❓ |
| IPDS-14-176 | Load Font Character Set Control (LFCSC) | ❓ |
| IPDS-14-177 | Exception ID X'0202..02' exists if the command length is invalid or unsupported. | ❓ |
| IPDS-14-178 | The data for the LFC command contains the following information: | ❓ |
| IPDS-14-179 | 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' | ❓ |
| IPDS-14-180 | 2 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single-byte<br>Double-byte section ID | X'00' | ❓ |
| IPDS-14-181 | 3 | CODE | LFC, LFI format | X'00' | Font control record and font index table format | X'00' | ❓ |
| IPDS-14-182 | 4 | CODE | Pattern format | X'05' | Pattern data format (bounded box) | X'05' | ❓ |
| IPDS-14-183 | 5 | BITS | Font type flags | ❓ |
| IPDS-14-184 | bits 0–1 | | B'00' | Reserved | B'00' | ❓ |
| IPDS-14-185 | bits 2–3 | Font type | B'01', B'10' | Single-byte coded font<br>Double-byte coded font | B'01' | ❓ |
| IPDS-14-186 | bits 4–5 | | B'00' | Reserved | B'00' | ❓ |
| IPDS-14-187 | bit 6 | Uniform character box | B'1', B'0' | B'1': Font has a uniform character-box size, specified in bytes 6 and 7<br>B'0': Box size for each character is expressed in the character-pattern descriptor for that character (bytes 40 to end of command) | B'1', B'0' | ❓ |
| IPDS-14-188 | bit 7 | | B'0' | Reserved | B'0' | ❓ |
| IPDS-14-189 | 6–7 | UBIN | X size | X'0000' – X'7FFF' | Uniform or maximum character-box X size | See byte description. | ❓ |
| IPDS-14-190 | 8–9 | UBIN | Y size | X'0000' – X'7FFF' | Uniform or maximum character-box Y size | See byte description. | ❓ |
| IPDS-14-191 | 10 | CODE | L-unit unit base | X'00', X'01', X'02' | Unit base for L-units:<br>Ten inches (fixed-metric technology)<br>Ten centimeters (fixed-metric technology)<br>Relative units (relative-metric technology) | See byte description. | ❓ |
| IPDS-14-192 | 11 | X'00' | Reserved | X'00' | ❓ |
| IPDS-14-193 | 12–13 | UBIN | X UPUB | X'0001' – X'7FFF' | Units per unit base in the X direction for L-units | See byte description. | ❓ |
| IPDS-14-194 | 14–15 | UBIN | Y UPUB | X'0001' – X'7FFF' | Units per unit base in the Y direction for L-units (same as bytes 12–13 for relative metrics) | See byte description. | ❓ |
| IPDS-14-195 | 16–17 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-14-196 | 18–20 | UBIN | Byte count | X'000001' – X'7FFFFF' | Font byte count | X'000001' – X'7FFFFF' | ❓ |
| IPDS-14-197 | 21 | UBIN | Data alignment | X'01'–X'FF' | Pattern data alignment value | X'01', X'04', X'08' | ❓ |
| IPDS-14-198 | 22–23 | CODE | GCSGID | X'0000', X'0001' – X'FFFE', X'FFFF' | No value supplied<br>Graphic Character Set Global ID<br>Use default value | See byte description. | ❓ |
| IPDS-14-199 | 24–25 | CODE | CPGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Code Page Global ID | See byte description. | ❓ |
| IPDS-14-200 | 26 | CODE | Pel-unit unit base | X'00', X'01' | Unit base for pel units:<br>ten inches<br>ten centimeters | See byte description. | ❓ |
| IPDS-14-201 | 27 | X'00' | Reserved | X'00' | ❓ |
| IPDS-14-202 | 28–29 | UBIN | X pel units | X'0000' – X'7FFF' | Pel units per unit base in the X direction | See byte description. | ❓ |
| IPDS-14-203 | 30–31 | UBIN | Y pel units | X'0000' – X'7FFF' | Pel units per unit base in the Y direction | See byte description. | ❓ |
| IPDS-14-204 | 32–33 | UBIN | RMMF | X'0001' – X'7FFF' | Relative-metric multiplying factor | See byte description. | ❓ |
| IPDS-14-205 | 34–35 | CODE | FGID | X'0000', X'0001' – X'FFFE' | No value supplied<br>Font Typeface Global ID | See byte description. | ❓ |
| IPDS-14-206 | 36 | X'01' | Reserved | X'01' | ❓ |
| IPDS-14-207 | 37 | BITS | Intended-use flags | ❓ |
| IPDS-14-208 | bit 0 | MICR | B'0', B'1' | Intended for MICR printing | B'0' | ❓ |
| IPDS-14-209 | bits 1–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-14-210 | 38–39 | CODE | FW | X'0000', X'0001' – X'7FFF' | No value supplied<br>Font Width (FW) | See byte description. | ❓ |
| IPDS-14-211 | Zero or more character-pattern descriptors in the following format:** | ❓ |
| IPDS-14-212 | + 0–1 | UBIN | X size | X'0000' – X'7FFF' | Character-box X size | See byte description. | ❓ |
| IPDS-14-213 | + 2–3 | UBIN | Y size | X'0000' – X'7FFF' | Character-box Y size | See byte description. | ❓ |
| IPDS-14-214 | + 4–7 | UBIN | Address | X'00000000' – X'007FFFFE' | Character-pattern address | X'00000000' – X'007FFFFE' | ❓ |
| IPDS-14-215 | 16 L-units, and 31 indicates 32 L-units. | ❓ |
| IPDS-14-216 | padding, is an integral number of bytes. | ❓ |
| IPDS-14-217 | 16 L-units, and 31 indicates 32 L-units. | ❓ |
| IPDS-14-218 | X'00' specifies ten inches (fixed-metric technology). | ❓ |
| IPDS-14-219 | X'01' specifies ten centimeters (fixed-metric technology). | ❓ |
| IPDS-14-220 | X'02' specifies relative units (relative-metric technology). | ❓ |
| IPDS-14-221 | defining field. If relative-metric technology is supported, the printer must support X'03E8' in | ❓ |
| IPDS-14-222 | The printer requires a valid CPGID value and one isn't supplied. | ❓ |
| IPDS-14-223 | The printer uses the CPGID value, but an invalid value is specified. | ❓ |
| IPDS-14-224 | support for all architected units of measure. | ❓ |
| IPDS-14-225 | 1. If the host-library font-metric values are specified as dimensionless values, the IPDS | ❓ |
| IPDS-14-226 | 2. If the host-library font-metric values are specified in pels, the IPDS relative values are set | ❓ |
| IPDS-14-227 | 1. The relative-metric value (in L-units) is multiplied by the Relative-Metric Multiplying Factor | ❓ |
| IPDS-14-228 | 2. The fixed-metric value in 1440ths of an inch can then be converted to a fixed-metric value | ❓ |
| IPDS-14-229 | 3. The fixed-metric value in inches can then be converted to a fixed-metric value in pels, by | ❓ |
| IPDS-14-230 | The device resolution is 300 pels per inch | ❓ |
| IPDS-14-231 | The unit base for L-units is relative (one em) | ❓ |
| IPDS-14-232 | The units per unit base in the X and Y direction is 1000 (X'03E8') | ❓ |
| IPDS-14-233 | The desired fontsize is 240 1440ths of an inch/em (12-point font) | ❓ |
| IPDS-14-234 | The host-font resolution is 240 pels per inch | ❓ |
| IPDS-14-235 | A particular host-font character increment is 20 pels | ❓ |
| IPDS-14-236 | 1. If the host chooses to use dimensionless units, that is, the character increment was | ❓ |
| IPDS-14-237 | 500 X 240 1 300 | ❓ |
| IPDS-14-238 | 1000 1440 1 | ❓ |
| IPDS-14-239 | 2. If the host chooses to use pels units (because the character increment was not previously | ❓ |
| IPDS-14-240 | 1000 X 1440 | ❓ |
| IPDS-14-241 | 20 X 6000 1 300 | ❓ |
| IPDS-14-242 | 1000 1440 1 | ❓ |
| IPDS-14-243 | 3. For implementation reasons, a printer can do this calculation in two stages. The first stage, | ❓ |
| IPDS-14-244 | 120 000 1 300 | ❓ |
| IPDS-14-245 | 1000 1440 1 | ❓ |
| IPDS-14-246 | The printer requires a valid FGID value and one isn't supplied. | ❓ |
| IPDS-14-247 | The printer uses the FGID value, but an invalid value is specified. | ❓ |
| IPDS-14-248 | Exception ID X'0220..02' exists if an invalid value is specified. | ❓ |
| IPDS-14-249 | The printer requires a valid FW and one isn't supplied. | ❓ |
| IPDS-14-250 | The printer uses the FW value, but an invalid value is specified. | ❓ |
| IPDS-14-251 | 1000 character pattern descriptors. Each descriptor is 8 bytes in length and contains the | ❓ |
| IPDS-14-252 | box used by the printer and includes no padding. | ❓ |
| IPDS-14-253 | The long format consists of a 32-byte header plus 256 sixteen-byte index entries. | ❓ |
| IPDS-14-254 | The short format consists of a 32-byte header. | ❓ |
| IPDS-14-255 | an ENC, RPS, SEA, or TRN control sequence are treated as if they had occurred directly in text data. | ❓ |
| IPDS-14-256 | The data for the LFI command contains the following information: | ❓ |
| IPDS-14-257 | 0–1 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' | ❓ |
| IPDS-14-258 | 2 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single byte<br>Double-byte section | X'00' | ❓ |
| IPDS-14-259 | 3 | BITS | Space flags | ❓ |
| IPDS-14-260 | bit 0 | | B'1', B'0' | Variable space enable:<br>Enabled<br>Disabled | B'1', B'0' | ❓ |
| IPDS-14-261 | bits 1–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-14-262 | 4–5 | CODE | FIS | X'0000', X'2D00', X'5A00', X'8700' | Font inline sequence:<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000' | ❓ |
| IPDS-14-263 | 6–7 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-14-264 | 8–9 | SBIN | Baseline offset | X'8000' – X'7FFF' | Uniform or maximum baseline offset | See byte description. | ❓ |
| IPDS-14-265 | 10–11 | SBIN | Character increment | X'8000' – X'7FFF' | Uniform or maximum character increment | See byte description. | ❓ |
| IPDS-14-266 | 12–13 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-14-267 | 14–15 | SBIN | Max extent | X'0000' – X'7FFF' | Maximum baseline extent | See byte description. | ❓ |
| IPDS-14-268 | 16 | BITS | Orientation flags | ❓ |
| IPDS-14-269 | bits 0–4 | | B'00000' | Reserved | B'00000' | ❓ |
| IPDS-14-270 | bit 5 | Uniform A-space | B'1', B'0' | B'1': The uniform A-space value is in bytes 18–19.<br>B'0': The A-space of each character is in the character index entry and bytes 18–19 specify the minimum value for this font index. | B'1', B'0' | ❓ |
| IPDS-14-271 | bit 6 | Uniform baseline offset | B'1', B'0' | B'1': The uniform baseline offset is in bytes 8–9.<br>B'0': The baseline offset of each character is in the character index entry and bytes 8–9 specify the maximum value for this font index. | B'1', B'0' | ❓ |
| IPDS-14-272 | bit 7 | Uniform character increment | B'1', B'0' | B'1': The uniform character increment is in bytes 10–11.<br>B'0': The increment of each character is in the character index entry and bytes 10–11 specify the maximum value for this font index. | B'1', B'0' | ❓ |
| IPDS-14-273 | 17 | X'00' | Reserved | X'00' | ❓ |
| IPDS-14-274 | 18–19 | SBIN | A-space | X'8000' – X'7FFF' | Uniform or minimum A-space | See byte description. | ❓ |
| IPDS-14-275 | 20–21 | CODE | VSP | X'0000' – X'FFFF' | Variable-space code point (VSP) | X'0000' – X'FFFF' | ❓ |
| IPDS-14-276 | 22–23 | SBIN | Default VSI | X'8000' – X'7FFF' | Default variable-space increment | See byte description. | ❓ |
| IPDS-14-277 | 24–25 | UBIN | Underscore width | X'0000', X'0001' – X'7FFF' | Recommended width:<br>No recommendation provided<br>Underscore width in L-units | X'0000' | ❓ |
| IPDS-14-278 | 26–27 | SBIN | Underscore position | X'8000' – X'7FFF' | Recommended position of underscore in L-units | X'0000' | ❓ |
| IPDS-14-279 | 28–31 | X'00000000' | Reserved | X'00000000' | ❓ |
| IPDS-14-280 | Zero or 256 character-index entries in the following format:** | ❓ |
| IPDS-14-281 | + 0–1 | BITS | Character flags | B'000' – B'111' | Character flags | B'000' – B'111' | ❓ |
| IPDS-14-282 | + 2–3 | UBIN | Pattern index | X'0000' – one less than the number of character patterns in the font | Pattern index | X'0000' – one less than the number of character patterns in the font | ❓ |
| IPDS-14-283 | + 4–5 | SBIN | Character increment | X'8000' – X'7FFF' | Character increment | See byte description. | ❓ |
| IPDS-14-284 | + 6–7 | SBIN | A-space | X'8000' – X'7FFF' | A-space | See byte description. | ❓ |
| IPDS-14-285 | + 8–13 | X'00...00' | Reserved | X'00...00' | ❓ |
| IPDS-14-286 | + 14–15 | SBIN | Baseline offset | X'8000' – X'7FFF' | Baseline offset | See byte description. | ❓ |
| IPDS-14-287 | Locating the character reference point | ❓ |
| IPDS-14-288 | Locating the character-box leading edge (used to position font patterns on a baseline) | ❓ |
| IPDS-14-289 | sequence direction is measured in degrees, as follows: | ❓ |
| IPDS-14-290 | If the font inline sequence is 0°, the baseline offset is the number of L-units from the | ❓ |
| IPDS-14-291 | If the font inline sequence is 180°, the baseline offset is the number of L-units from the | ❓ |
| IPDS-14-292 | character reference point to the bottom character box reference edge. | ❓ |
| IPDS-14-293 | If the font inline sequence is 90°, the baseline offset is the number of L-units from the | ❓ |
| IPDS-14-294 | If the font inline sequence is 270°, the baseline offset is the number of L-units from the | ❓ |
| IPDS-14-295 | If all of the characters in the font are above the character baseline, the maximum baseline | ❓ |
| IPDS-14-296 | If all of the characters in the font are below the character baseline, the maximum baseline | ❓ |
| IPDS-14-297 | Otherwise, the maximum baseline extent is the sum of the uniform or maximum baseline | ❓ |
| IPDS-14-298 | If the font inline sequence is 0°, the baseline descender is the number of L-units from the | ❓ |
| IPDS-14-299 | If the font inline sequence is 180°, the baseline descender is the number of L-units from the | ❓ |
| IPDS-14-300 | If the font inline sequence is 90°, the baseline descender is the number of L-units from the | ❓ |
| IPDS-14-301 | If the a font inline sequence is 270°, the baseline descender is the number of L-units from | ❓ |
| IPDS-14-302 | all values in the range X'0000' through X'0100' in this field. If relative-metric technology is | ❓ |
| IPDS-14-303 | double-byte code points that reference a font section that is not stored in the printer. | ❓ |
| IPDS-14-304 | 1. Defined or undefined | ❓ |
| IPDS-14-305 | 2. To be printed or not to be printed | ❓ |
| IPDS-14-306 | 3. Incrementing or nonincrementing | ❓ |
| IPDS-14-307 | 277. If the data-check exception is blocked, this combination is | ❓ |
| IPDS-14-308 | combination can be used as a space character (B'010'). | ❓ |
| IPDS-14-309 | exception ID X'023C..02' exists. | ❓ |
| IPDS-14-310 | If a uniform baseline offset is in effect, this field is ignored. | ❓ |
| IPDS-14-311 | No previous symbol set with a font Host-Assigned ID matching bytes 15–16 is activated. Therefore, this | ❓ |
| IPDS-14-312 | A previous symbol set with a font Host-Assigned ID matching bytes 15–16 is activated. Therefore, the | ❓ |
| IPDS-14-313 | R is the number of bytes of raster data. Refer to the formulas. | ❓ |
| IPDS-14-314 | The data field for the LSS command follows: | ❓ |
| IPDS-14-315 | 0 | BITS | Flags1 | X'90', X'91' | Flags; bit mapped | X'90' | ❓ |
| IPDS-14-316 | 1 | X'00' | | | Retired item 69 | X'00' | ❓ |
| IPDS-14-317 | 2 | CODE | SCODE | X'00'–X'FF' | Starting code point | X'01'–X'FF' | ❓ |
| IPDS-14-318 | 3 | X'00' | | | Retired item 70 | X'00' | ❓ |
| IPDS-14-319 | 4 | UBIN | Length | X'0D'–X'FF' | Additional parameter byte length. See byte description. | X'0D' | ❓ |
| IPDS-14-320 | 5 | BITS | Flags2 | X'60', X'61' | Flags; bit mapped | X'60', X'61' | ❓ |
| IPDS-14-321 | 6 | UBIN | X box size | X'01'–X'FF' | Uniform character-box size X dimension, measured in pels | Per XOH OPC | ❓ |
| IPDS-14-322 | 7 | UBIN | Y box size | X'01'–X'FF' | Uniform character-box size Y dimension, measured in pels | Per XOH OPC | ❓ |
| IPDS-14-323 | 8 | CODE | Section ID | X'00', X'41'–X'FE' | Section identifier:<br>Single-byte<br>Double-byte section ID | X'00' | ❓ |
| IPDS-14-324 | 9–10 | X'0000' | | | Retired items 74 and 75 | X'0000' | ❓ |
| IPDS-14-325 | 11 | CODE | ECODE | Starting code point–X'FF' | Ending code point | Starting code point–X'FF' | ❓ |
| IPDS-14-326 | 12–14 | X'000000' | | | Retired items 76, 77, 78 | X'000000' | ❓ |
| IPDS-14-327 | 15–16 | CODE | HAID | X'0001' – X'7EFF' | Font Host-Assigned ID | X'0001' – X'7EFF' | ❓ |
| IPDS-14-328 | 17–i | X'00...00' | | | Retired item 79 | X'00...00' | ❓ |
| IPDS-14-329 | j–k | Triplets | | See byte description. | One or more triplets | X'02FF' | ❓ |
| IPDS-14-330 | (k+1) to end of cmnd | UNDF | Raster | See byte description. | Character raster patterns | Any value | ❓ |
| IPDS-14-331 | A value of B'10000' indicates that the character patterns contained in bytes | ❓ |
| IPDS-14-332 | A value of B'10001' indicates that the character patterns contained in bytes | ❓ |
| IPDS-14-333 | Byte 3 Retired item 70 | ❓ |
| IPDS-14-334 | Byte 10 Retired item 75 | ❓ |
| IPDS-14-335 | character patterns of the previously specified code points are replaced. | ❓ |
| IPDS-15-001 | The following triplets are used within IPDS commands: | ❓ |
| IPDS-15-002 | X'00' | Group ID | ISP, XOA RRL, XOH DGB, XOH DSPG, XOH RSPG | ❓ |
| IPDS-15-003 | X'01' | Coded Graphic Character Set Global Identifier | AR, XOH DGB | ❓ |
| IPDS-15-004 | X'02' | Fully Qualified Name | AR, WOCC | ❓ |
| IPDS-15-005 | X'4E' | Color Specification | IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, WTC | ❓ |
| IPDS-15-006 | X'50' | Encoding Scheme ID | AR | ❓ |
| IPDS-15-007 | X'5A' | Object Offset | IDO, RPO, WOCC | ❓ |
| IPDS-15-008 | X'62' | Local Date and Time Stamp | AR | ❓ |
| IPDS-15-009 | X'6E' | Group Information | XOH DGB | ❓ |
| IPDS-15-010 | X'70' | Presentation Space Reset Mixing | IDO, LPD, WBCC, WGC, WIC2, WOCC, WTC | ❓ |
| IPDS-15-011 | X'74' | Toner Saver | PFC | ❓ |
| IPDS-15-012 | X'75' | Color Fidelity | PFC | ❓ |
| IPDS-15-013 | X'79' | Metric Adjustment | AR | ❓ |
| IPDS-15-014 | X'84' | Font Resolution and Metric Technology | AR | ❓ |
| IPDS-15-015 | X'85' | Finishing Operation | AFO, XOH DGB | ❓ |
| IPDS-15-016 | X'86' | Text Fidelity | PFC | ❓ |
| IPDS-15-017 | X'88' | Finishing Fidelity | PFC | ❓ |
| IPDS-15-018 | X'8B' | Data Object Font Descriptor | AR | ❓ |
| IPDS-15-019 | X'8D' | Linked Font | AR | ❓ |
| IPDS-15-020 | X'8E' | UP3I Finishing Operation | AFO, XOH DGB | ❓ |
| IPDS-15-021 | X'91' | Color Management Resource Descriptor | AR, home-state WOCC | ❓ |
| IPDS-15-022 | X'92' | Invoke CMR | IDO, LPD, RPO, WBCC, WGC, WIC2, WOCC, WTC | ❓ |
| IPDS-15-023 | X'95' | Rendering Intent | IDO, LPD, RPO, SPE, WGC, WIC2, WOCC, WTC | ❓ |
| IPDS-15-024 | X'96' | CMR Tag Fidelity | PFC | ❓ |
| IPDS-15-025 | X'97' | Device Appearance | SPE | ❓ |
| IPDS-15-026 | X'9A' | Image Resolution | IDO, RPO, WOCC | ❓ |
| IPDS-15-027 | X'9C' | Object Container Presentation Space Size | IDO, RPO, WOCC | ❓ |
| IPDS-15-028 | X'9E' | Setup Name | ASN, XOA RSNL | ❓ |
| IPDS-15-029 | X'A2' | Invoke Tertiary Resource | WBCC | ❓ |
| IPDS-15-030 | Invoke Tertiary Resource (X'A2') triplet | ❓ |
| IPDS-15-031 | 0 | UBIN | Length | X'02'–X'FF' | Length of the triplet, including this length field | X'02'–X'FF' | ❓ |
| IPDS-15-032 | 1 | CODE | TID | X'00' | Group ID triplet | X'00' | ❓ |
| IPDS-15-033 | 2 | CODE | Format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'08'<br>X'13' | MVS and VSE print-data format<br>VM print-data format<br>OS/400 print-data format<br>MVS and VSE COM-data format<br>AIX and OS/2 COM-data format<br>AIX and Windows print-data<br>Variable-length Group ID format<br>Extended OS/400 print-data format | See byte description | ❓ |
| IPDS-15-034 | 3 to end | | Data | | Data bytes | See byte description | ❓ |
| IPDS-15-035 | X'00'—Group ID | ❓ |
| IPDS-15-036 | Triplet X'00'—Group ID | ❓ |
| IPDS-15-037 | Keep group together as a print unit | ❓ |
| IPDS-15-038 | MVS and VSE (Format X'01')** | ❓ |
| IPDS-15-039 | 2 | CODE | Format | X'01'—MVS and VSE print-data format identifier | ❓ |
| IPDS-15-040 | 3 | CHAR | CLASS | The one-character EBCDIC job CLASS parameter | ❓ |
| IPDS-15-041 | 4–11 | CHAR | Job Name | The eight-character EBCDIC job name parameter | ❓ |
| IPDS-15-042 | 12–19 | CHAR | Job ID | The eight-character EBCDIC job identification number | ❓ |
| IPDS-15-043 | 20–27 | CHAR | FORMS | The eight-character EBCDIC job FORMS parameter | ❓ |
| IPDS-15-044 | VM (Format X'02')** | ❓ |
| IPDS-15-045 | 2 | CODE | Format | X'02'—VM print-data format identifier | ❓ |
| IPDS-15-046 | 3 | CHAR | Class | The one-character EBCDIC spool class value | ❓ |
| IPDS-15-047 | 4–11 | CHAR | Filename | The eight-character EBCDIC filename of the spool print file | ❓ |
| IPDS-15-048 | 12–19 | CHAR | User ID | The eight-character EBCDIC userid of the print file originator | ❓ |
| IPDS-15-049 | 20–27 | CHAR | Formname | The eight-character EBCDIC spool formname value | ❓ |
| IPDS-15-050 | 28–31 | CHAR | Spool ID | The four-character EBCDIC spool identification number (spoolid) | ❓ |
| IPDS-15-051 | 9999 to 999999. The first format (X'03') provides a 4 character spooled file number. The | ❓ |
| IPDS-15-052 | OS/400 (Format X'03')** | ❓ |
| IPDS-15-053 | 2 | CODE | Format | X'03', OS/400 print-data format identifier | ❓ |
| IPDS-15-054 | 3–12 | CHAR | Library | The ten-character EBCDIC library name | ❓ |
| IPDS-15-055 | 13–22 | CHAR | Output Queue | The ten-character EBCDIC output queue name | ❓ |
| IPDS-15-056 | 23–32 | CHAR | Spooled File | The ten-character EBCDIC spooled file name | ❓ |
| IPDS-15-057 | 33–36 | CHAR | Spool Number | The four-character EBCDIC spooled file number | ❓ |
| IPDS-15-058 | 37–46 | CHAR | Job Name | The ten-character EBCDIC job name | ❓ |
| IPDS-15-059 | 47–56 | CHAR | User Name | The ten-character EBCDIC user name | ❓ |
| IPDS-15-060 | 57–62 | CHAR | Job Number | The six-character EBCDIC job number | ❓ |
| IPDS-15-061 | 63–72 | CHAR | Forms Name | The ten-character EBCDIC forms name parameter | ❓ |
| IPDS-15-062 | Extended OS/400 (Format X'13')** | ❓ |
| IPDS-15-063 | 2 | CODE | Format | X'13', Extended OS/400 print-data format identifier | ❓ |
| IPDS-15-064 | 3–12 | CHAR | Library | The ten-character EBCDIC library name | ❓ |
| IPDS-15-065 | 13–22 | CHAR | Output Queue | The ten-character EBCDIC output queue name | ❓ |
| IPDS-15-066 | 23–32 | CHAR | Spooled File | The ten-character EBCDIC spooled file name | ❓ |
| IPDS-15-067 | 33–38 | CHAR | Spool Number | The six-character EBCDIC spooled file number | ❓ |
| IPDS-15-068 | 39–48 | CHAR | Job Name | The ten-character EBCDIC job name | ❓ |
| IPDS-15-069 | 49–58 | CHAR | User Name | The ten-character EBCDIC user name | ❓ |
| IPDS-15-070 | 59–64 | CHAR | Job Number | The six-character EBCDIC job number | ❓ |
| IPDS-15-071 | 65–74 | CHAR | Forms Name | The ten-character EBCDIC forms name parameter | ❓ |
| IPDS-15-072 | AIX and Windows (Format X'06')** | ❓ |
| IPDS-15-073 | 2 | CODE | Format | X'06', AIX and Windows print-data format identifier | ❓ |
| IPDS-15-074 | 3–253 | CHAR | Job Name | The 1–251 character ASCII name associated with the job | ❓ |
| IPDS-15-075 | Keep group together for microfilm output | ❓ |
| IPDS-15-076 | MVS and VSE COM-data (Format X'04')** | ❓ |
| IPDS-15-077 | 2 | CODE | Format | X'04'—MVS and VSE COM-data format identifier | ❓ |
| IPDS-15-078 | 3 | CODE | Type | Type of print file:<br>X'80' Job header<br>X'40' Data set header<br>X'20' User data set<br>X'10' Message data set<br>X'04' Job trailer<br>X'00' Type not specified | ❓ |
| IPDS-15-079 | 4 | CHAR | Class | The one-character EBCDIC job class | ❓ |
| IPDS-15-080 | 5–12 | CHAR | Job Name | The eight-character EBCDIC job name | ❓ |
| IPDS-15-081 | 13–20 | CHAR | Job ID | The eight-character EBCDIC job identification number | ❓ |
| IPDS-15-082 | 21–28 | CHAR | Job Form | The eight-character EBCDIC job form | ❓ |
| IPDS-15-083 | 29–88 | CHAR | Programmer | The 60-character EBCDIC programmer name | ❓ |
| IPDS-15-084 | 89–148 | CHAR | Room Number | The 60-character EBCDIC room number | ❓ |
| IPDS-15-085 | 149–159 | CHAR | Date | The 11-character EBCDIC submission date parameter | ❓ |
| IPDS-15-086 | 160–170 | CHAR | Time | The 11-character EBCDIC submission time parameter | ❓ |
| IPDS-15-087 | Triplet X'00'—Group ID | ❓ |
| IPDS-15-088 | AIX and OS/2 COM-data (Format X'05')** | ❓ |
| IPDS-15-089 | 2 | CODE | Format | X'05'—AIX and OS/2 COM-data format identifier | ❓ |
| IPDS-15-090 | 3 | CODE | Type | Type of print file:<br>X'80' Job header<br>X'40' Copy separator<br>X'20' User print file<br>X'10' Message file<br>X'08' User exit page<br>X'04' Job trailer<br>X'00' Type not specified | ❓ |
| IPDS-15-091 | 4–254 | CHAR | File Name | The 1–251 character ASCII file name | ❓ |
| IPDS-15-092 | Save Pages | ❓ |
| IPDS-15-093 | Variable-length Group ID (Format X'08')** | ❓ |
| IPDS-15-094 | 2 | CODE | Format | X'08'—Variable-length group ID | ❓ |
| IPDS-15-095 | 3–246 | UNDF | Group ID | A 1 to 244 byte long group ID. Binary data unless preceded by X'01' triplet. | ❓ |
| IPDS-15-096 | Triplet X'00'—Group ID | ❓ |
| IPDS-15-097 | GCSGID/CPGID form** | ❓ |
| IPDS-15-098 | 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' | ❓ |
| IPDS-15-099 | 1 | CODE | TID | X'01' | Identifies the CGCSGID triplet | X'01' | ❓ |
| IPDS-15-100 | 2–3 | CODE | GCSGID | X'0001'–X'FFFE'<br>X'FFFF' | Graphic Character Set Global Identifier<br>Full character set | X'0001'–X'FFFE'<br>X'FFFF' | ❓ |
| IPDS-15-101 | 4–5 | CODE | CPGID | X'0001'–X'FFFE' | Code Page Global Identifier | X'0001'–X'FFFE' | ❓ |
| IPDS-15-102 | 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' | ❓ |
| IPDS-15-103 | 1 | CODE | TID | X'01' | Identifies the CGCSGID triplet | X'01' | ❓ |
| IPDS-15-104 | 2–3 | CODE | | X'0000' | Identifies this as the CCSID form of the triplet | X'0000' | ❓ |
| IPDS-15-105 | 4–5 | CODE | CCSID | X'0000'–X'FFFF' | Coded Character Set Identifier | X'0000'–X'FFFF' | ❓ |
| IPDS-15-106 | Triplet X'01'—CGCSGID | ❓ |
| IPDS-15-107 | Triplet X'01'—CGCSGID | ❓ |
| IPDS-15-108 | The Fully Qualified Name (X'02') triplet is used to specify a fully qualified name (FQN) and how that name is to be used. The FQN Type field specifies how the FQN is to be used and the FQN format field specifies how the FQN is encoded. | ❓ |
| IPDS-15-109 | 0 | UBIN | Length | X'06'–X'FE' | Length of the triplet, including this length field | See byte description | ❓ |
| IPDS-15-110 | 1 | CODE | TID | X'02' | Fully Qualified Name triplet | X'02' | ❓ |
| IPDS-15-111 | 2 | CODE | FQN type | X'41'<br>X'DE' | Specifies how the FQN is used:<br>Color Management Resource reference<br>Data-object external resource reference | See byte description | ❓ |
| IPDS-15-112 | 3 | CODE | FQN format | X'00'<br>X'10' | Format of the FQN:<br>Character-encoded name<br>Object ID (OID) | See byte description | ❓ |
| IPDS-15-113 | 4 to end | | FQN | Any value | Fully qualified name | Any value | ❓ |
| IPDS-15-114 | In the AR command, this type is used to reference a TrueType/OpenType font within | ❓ |
| IPDS-15-115 | In the AR command, this type is used to provide an optional object name for the | ❓ |
| IPDS-15-116 | Triplet X'02'—Fully Qualified Name | ❓ |
| IPDS-15-117 | In the WOCC-OCDD command, this type is used to specify the object OID of a | ❓ |
| IPDS-15-118 | Triplet X'02'—Fully Qualified Name | ❓ |
| IPDS-15-119 | defining field) in an IO Image object. | ❓ |
| IPDS-15-120 | 0 | UBIN | Length | X'0E'–X'10' | Length of the triplet, including this length field | X'0E'–X'10' | ❓ |
| IPDS-15-121 | 1 | CODE | TID | X'4E' | Color Specification triplet | X'4E' | ❓ |
| IPDS-15-122 | 2 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-123 | 3 | CODE | Color space | X'01'<br>X'04'<br>X'06'<br>X'08'<br>X'40' | RGB color space<br>CMYK color space<br>Highlight color space<br>CIELAB color space<br>Standard OCA color space | X'01'<br>X'04'<br>X'06'<br>X'08'<br>X'40' | ❓ |
| IPDS-15-124 | 4–7 | | X'00000000' | Reserved | | X'00000000' | ❓ |
| IPDS-15-125 | 8 | UBIN | ColSize1 | X'01'–X'08',<br>X'10' | Number of bits in component 1; the range depends on the color space | See color-space description | ❓ |
| IPDS-15-126 | 9 | UBIN | ColSize2 | X'00'–X'08' | Number of bits in component 2; the range depends on the color space | See color-space description | ❓ |
| IPDS-15-127 | 10 | UBIN | ColSize3 | X'00'–X'08' | Number of bits in component 3; the range depends on the color space | See color-space description | ❓ |
| IPDS-15-128 | 11 | UBIN | ColSize4 | X'00'–X'08' | Number of bits in component 4; the range depends on the color space | See color-space description | ❓ |
| IPDS-15-129 | 12 to end | | Color value | All values | Color specification | See byte description | ❓ |
| IPDS-15-130 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-131 | 6500 or simply D65: | ❓ |
| IPDS-15-132 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-133 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-134 | 1 specifies a two-byte value that is an index into the CMR and components 2 | ❓ |
| IPDS-15-135 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-136 | Table 58. Standard OCA Color-Value Table | ❓ |
| IPDS-15-137 | X'0000' or X'FF00' | Current default (printer default) | ❓ |
| IPDS-15-138 | X'0001' or X'FF01' | Blue | 0 | 0 | 255 | ❓ |
| IPDS-15-139 | X'0002' or X'FF02' | Red | 255 | 0 | 0 | ❓ |
| IPDS-15-140 | X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 | ❓ |
| IPDS-15-141 | X'0004' or X'FF04' | Green | 0 | 255 | 0 | ❓ |
| IPDS-15-142 | X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 | ❓ |
| IPDS-15-143 | X'0006' or X'FF06' | Yellow | 255 | 255 | 0 | ❓ |
| IPDS-15-144 | X'0007' | White; see note 2 | 255 | 255 | 255 | ❓ |
| IPDS-15-145 | X'0008' | Black | 0 | 0 | 0 | ❓ |
| IPDS-15-146 | X'0009' | Dark blue | 0 | 0 | 170 | ❓ |
| IPDS-15-147 | X'000A' | Orange | 255 | 128 | 0 | ❓ |
| IPDS-15-148 | X'000B' | Purple | 170 | 0 | 170 | ❓ |
| IPDS-15-149 | X'000C' | Dark green | 0 | 146 | 0 | ❓ |
| IPDS-15-150 | X'000D' | Dark turquoise | 0 | 146 | 170 | ❓ |
| IPDS-15-151 | X'000E' | Mustard | 196 | 160 | 32 | ❓ |
| IPDS-15-152 | X'000F' | Gray | 131 | 131 | 131 | ❓ |
| IPDS-15-153 | X'0010' | Brown | 144 | 48 | 0 | ❓ |
| IPDS-15-154 | X'FF07' | Printer default | ❓ |
| IPDS-15-155 | X'FF08' | Color of medium; also known as reset color | ❓ |
| IPDS-15-156 | 1. The table specifies the RGB values for each named color; the actual printed color is device dependent. | ❓ |
| IPDS-15-157 | 2. The color rendered on presentation devices that do not support white is device-dependent. For example, some | ❓ |
| IPDS-15-158 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-159 | Table 59. Color Space Examples | ❓ |
| IPDS-15-160 | RGB | X'08' | X'08' | X'08' | N/A | X'00B761' | ❓ |
| IPDS-15-161 | RGB | X'05' | X'05' | X'05' | N/A | X'00160B' | ❓ |
| IPDS-15-162 | CMYK | X'08' | X'08' | X'08' | X'08' | X'FF489E00' | ❓ |
| IPDS-15-163 | CMYK | X'01' | X'02' | X'04' | X'08' | X'01010900' | ❓ |
| IPDS-15-164 | Highlight (see note) | X'10' | X'08' | X'00' | N/A | X'000264' | ❓ |
| IPDS-15-165 | CIELAB | X'08' | X'08' | X'08' | N/A | X'A8CC21' | ❓ |
| IPDS-15-166 | Standard OCA | X'10' | N/A | N/A | N/A | X'0004' | ❓ |
| IPDS-15-167 | 1. If extra bytes are specified in the color value field, they are ignored as long as the triplet length is valid. | ❓ |
| IPDS-15-168 | 2. This triplet is identical to the corresponding MO:DCA Color Specification (X'4E') triplet. | ❓ |
| IPDS-15-169 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-170 | Triplet X'4E'—Color Specification | ❓ |
| IPDS-15-171 | the Encoding Scheme ID (X'50') triplet. | ❓ |
| IPDS-15-172 | 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' | ❓ |
| IPDS-15-173 | 1 | CODE | TID | X'50' | Encoding Scheme ID triplet | X'50' | ❓ |
| IPDS-15-174 | 2–3 | | X'0000' | Reserved | | X'0000' | ❓ |
| IPDS-15-175 | 4–5 | CODE | Data ESID | X'7807' | Encoding scheme ID for the data:<br>UTF-8 | X'7807' | ❓ |
| IPDS-15-176 | Triplet X'50'—Encoding Scheme ID | ❓ |
| IPDS-15-177 | Triplet X'50'—Encoding Scheme ID | ❓ |
| IPDS-15-178 | 0 | UBIN | Length | X'08' or X'0C' | Length of the triplet, including this length field | X'08' or X'0C' | ❓ |
| IPDS-15-179 | 1 | CODE | TID | X'5A' | Object Offset triplet | X'5A' | ❓ |
| IPDS-15-180 | 2 | CODE | Object type | X'AF' | Object type:<br>Page or paginated object | X'AF' | ❓ |
| IPDS-15-181 | 3 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-182 | 4–7 | UBIN | Object offset | X'0000..00' –<br>X'FFFF ..FF' | Number of objects that precede the selected object in the file | X'0000..00' –<br>X'FFFF ..FF' | ❓ |
| IPDS-15-183 | 8–11 | | X'0000..00' | Reserved; not used in IPDS | | X'0000..00' | ❓ |
| IPDS-15-184 | Triplet X'5A'—Object Offset | ❓ |
| IPDS-15-185 | Triplet X'5A'—Object Offset | ❓ |
| IPDS-15-186 | If the AR command has an Local Date and Time Stamp (X'62') triplet, activation takes place only if the | ❓ |
| IPDS-15-187 | If the AR command does not have an Local Date and Time Stamp (X'62') triplet, activation takes place. | ❓ |
| IPDS-15-188 | Triplet X'62'—Time Stamp | ❓ |
| IPDS-15-189 | The Local Date and Time Stamp (X'62') triplet is defined as follows: | ❓ |
| IPDS-15-190 | 0 | UBIN | Length | X'11' | Length of the triplet, including this length field | X'11' | ❓ |
| IPDS-15-191 | 1 | CODE | TID | X'62' | Identifies the Local Date and Time Stamp Triplet | X'62' | ❓ |
| IPDS-15-192 | 2 | CODE | StampType | X'00'<br>X'03' | Type of date and time stamp:<br>Creation<br>Revision | X'00'<br>X'03' | ❓ |
| IPDS-15-193 | 3 | CODE | Year (part 1) | X'40',<br>X'F0'–X'F9' | Thousands and hundreds position of year:<br>19xx<br>20xx through 29xx | X'40',<br>X'F0'–X'F9' | ❓ |
| IPDS-15-194 | 4–5 | CODE | Year (part 2) | X'F0F0' –<br>X'F9F9' | Tens and units position of the year | X'F0F0' –<br>X'F9F9' | ❓ |
| IPDS-15-195 | 6–8 | CODE | Day | X'F0F0F1' –<br>X'F3F6F6' | Day of year | X'F0F0F1' –<br>X'F3F6F6' | ❓ |
| IPDS-15-196 | 9–10 | CODE | Hour | X'F0F0' –<br>X'F2F3' | Hour of day | X'F0F0' –<br>X'F2F3' | ❓ |
| IPDS-15-197 | 11–12 | CODE | Minute | X'F0F0' –<br>X'F5F9' | Minute of hour | X'F0F0' –<br>X'F5F9' | ❓ |
| IPDS-15-198 | 13–14 | CODE | Second | X'F0F0' –<br>X'F5F9' | Second of minute | X'F0F0' –<br>X'F5F9' | ❓ |
| IPDS-15-199 | 15–16 | CODE | Hundredth | X'F0F0' –<br>X'F9F9' | Hundredth of second | X'F0F0' –<br>X'F9F9' | ❓ |
| IPDS-15-200 | Triplet X'62'—Time Stamp | ❓ |
| IPDS-15-201 | Table 60. Examples of the Date Fields | ❓ |
| IPDS-15-202 | February 1, 1972 | 72032 | X'40F7F2F0F3F2' | ❓ |
| IPDS-15-203 | December 31, 1999 | 99365 | X'40F9F9F3F6F5' | ❓ |
| IPDS-15-204 | January 1, 2000 | 000001 | X'F0F0F0F0F0F1' | ❓ |
| IPDS-15-205 | February 3, 2072 | 072034 | X'F0F7F2F0F3F4' | ❓ |
| IPDS-15-206 | Triplet X'62'—Time Stamp | ❓ |
| IPDS-15-207 | microfilm device. Unrecognized formats should be ignored. | ❓ |
| IPDS-15-208 | 0 | UBIN | Length | X'02'–X'FF' | Length of the triplet, including this length field | X'02'–X'FF' | ❓ |
| IPDS-15-209 | 1 | CODE | TID | X'6E' | Group Information triplet | X'6E' | ❓ |
| IPDS-15-210 | 2 | CODE | Format | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'82' | Microfilm save/restore format<br>Copy set number format<br>Group name format<br>Additional information format<br>Page count format<br>Extended copy set number format | See byte description | ❓ |
| IPDS-15-211 | 3 to end of triplet | | Data | X'00'–X'FF' | Group Information Data Bytes | X'00'–X'FF' | ❓ |
| IPDS-15-212 | X'6E'—Group Information | ❓ |
| IPDS-15-213 | Triplet X'6E'—Group Information | ❓ |
| IPDS-15-214 | Keep group together as a print unit | ❓ |
| IPDS-15-215 | Triplet X'6E'—Group Information | ❓ |
| IPDS-15-216 | Keep group together for microfilm output | ❓ |
| IPDS-15-217 | Triplet X'6E'—Group Information | ❓ |
| IPDS-15-218 | Identify Named Group and | ❓ |
| IPDS-15-219 | Triplet X'6E'—Group Information | ❓ |
| IPDS-15-220 | Mixing (X'70') triplet. | ❓ |
| IPDS-15-221 | 0 | UBIN | Length | X'03' | Length of the triplet, including this length field | X'03' | ❓ |
| IPDS-15-222 | 1 | CODE | TID | X'70' | Presentation Space Reset Mixing triplet | X'70' | ❓ |
| IPDS-15-223 | 2 | BITS | Mixing flags | ❓ |
| IPDS-15-224 | bit 0 | Reset flag | B'0'<br>B'1' | Do not reset the color of the presentation space to color of medium. This value makes this triplet an effective NOP.<br>Reset the color of the presentation space to color of medium prior to placing object data into the presentation space. | B'0'<br>B'1' | ❓ |
| IPDS-15-225 | bits 1–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-15-226 | Triplet X'70'—Reset Mixing | ❓ |
| IPDS-15-227 | used and the XOA-PQC command is ignored for toner saving purposes. | ❓ |
| IPDS-15-228 | 0 | UBIN | Length | X'06' | Length of the triplet, including this length field | X'06' | ❓ |
| IPDS-15-229 | 1 | CODE | TID | X'74' | Identifies the Toner Saver triplet | X'74' | ❓ |
| IPDS-15-230 | 2 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-231 | 3 | CODE | Control | X'00'<br>X'01'<br>X'FF' | Toner saver control:<br>Deactivate toner saver<br>Activate toner saver<br>Use printer default setting | X'00'<br>X'01'<br>X'FF' | ❓ |
| IPDS-15-232 | 4–5 | | X'0000' | Reserved | | X'0000' | ❓ |
| IPDS-15-233 | 1. The toner saver setting (activated or deactivated) that is in effect when data is printed controls whether or | ❓ |
| IPDS-15-234 | Triplet X'74'—Toner Saver | ❓ |
| IPDS-15-235 | 2. The toner saver function is not applied to IO Image tiles (IOCA FS4x) that specify CMYK colors. Other tiles | ❓ |
| IPDS-15-236 | 3. For resources, toner saver is applied based on the setting that is active at include (presentation) time, not | ❓ |
| IPDS-15-237 | Data objects: | ❓ |
| IPDS-15-238 | IO Images | ❓ |
| IPDS-15-239 | Overlays | ❓ |
| IPDS-15-240 | Page segments | ❓ |
| IPDS-15-241 | Saved page groups (see also note 4) | ❓ |
| IPDS-15-242 | 4. With saved pages, some printers apply toner saver when the pages are saved. In that case, if the toner | ❓ |
| IPDS-15-243 | 5. No toner saving is applied when a data object resource is captured. | ❓ |
| IPDS-15-244 | Triplet X'74'—Toner Saver | ❓ |
| IPDS-15-245 | If the printer supports color simulation, simulate valid but unsupported standard-OCA color values. | ❓ |
| IPDS-15-246 | Follow the XOA-EHC settings for unsupported color values that are not simulated, as follows: | ❓ |
| IPDS-15-247 | Triplet X'75'—Color Fidelity | ❓ |
| IPDS-15-248 | 0 | UBIN | Length | X'08' | Length of the triplet, including this length field | X'08' | ❓ |
| IPDS-15-249 | 1 | CODE | TID | X'75' | Identifies the Color Fidelity triplet | X'75' | ❓ |
| IPDS-15-250 | 2 | CODE | Continue | X'01'<br>X'02' | Color exception continuation rule:<br>Stop at point of first color exception and report exception<br>Do not stop at color exception | X'01'<br>X'02' | ❓ |
| IPDS-15-251 | 3 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-252 | 4 | CODE | Report | X'01'<br>X'02' | Reporting rule if the presentation process was not stopped:<br>Report color exception<br>Do not report color exception | X'01'<br>X'02' | ❓ |
| IPDS-15-253 | 5 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-254 | 6 | CODE | Substitute | X'01' | Substitution rule if the presentation process was not stopped:<br>Any color substitution is permitted | X'01' | ❓ |
| IPDS-15-255 | 7 | | X'00' | Reserved | | X'00' | ❓ |
| IPDS-15-256 | Triplet X'75'—Color Fidelity | ❓ |
| IPDS-15-257 | Triplet X'75'—Color Fidelity | ❓ |
| IPDS-15-258 | This triplet is defined as follows: | ❓ |
| IPDS-15-259 | 0 | UBIN | Length | X'0F' | Length of the triplet, including this length field | X'0F' | ❓ |
| IPDS-15-260 | 1 | CODE | TID | X'79' | Identifies the Metric Adjustment triplet | X'79' | ❓ |
| IPDS-15-261 | 2 | CODE | Unit base | X'00' | Metric technology unit base:<br>Fixed metrics, 10 inches | X'00' | ❓ |
| IPDS-15-262 | 3–4 | UBIN | XUPUB | X'0001' – X'7FFF' | Units per unit base in the X direction | See byte description | ❓ |
| IPDS-15-263 | 5–6 | UBIN | YUPUB | X'0001' – X'7FFF' | Units per unit base in the Y direction | See byte description | ❓ |
| IPDS-15-264 | 7–8 | SBIN | H uniform increment | X'8000' – X'7FFF' | Uniform character increment value for horizontal writing | X'8000' – X'7FFF' | ❓ |
| IPDS-15-265 | 9–10 | SBIN | V uniform increment | X'8000' – X'7FFF' | Uniform character increment value for vertical writing | X'8000' – X'7FFF' | ❓ |
| IPDS-15-266 | 11–12 | SBIN | H baseline adjustment | X'8000' – X'7FFF' | Baseline offset adjustment value for horizontal writing | X'8000' – X'7FFF' | ❓ |
| IPDS-15-267 | 13–14 | SBIN | V baseline adjustment | X'8000' – X'7FFF' | Baseline offset adjustment value for vertical writing | X'8000' – X'7FFF' | ❓ |
| IPDS-15-268 | Triplet X'79'—Metric Adjustment | ❓ |
| IPDS-15-269 | Triplet X'79'—Metric Adjustment | ❓ |
| IPDS-15-270 | Triplet X'79'—Metric Adjustment | ❓ |
| IPDS-15-271 | If the AR command contains a Font Resolution and Metric Technology (X'84') triplet, activation takes place | ❓ |
| IPDS-15-272 | If the AR command does not contain a Font Resolution and Metric Technology (X'84') triplet, activation can | ❓ |
| IPDS-15-273 | This triplet is defined as follows: | ❓ |
| IPDS-15-274 | 0 | UBIN | Length | X'06' or X'08' | Length of the triplet, including this length field | X'06' or X'08' | ❓ |
| IPDS-15-275 | 1 | CODE | TID | X'84' | Identifies the Font Resolution and Metric Technology triplet | X'84' | ❓ |
| IPDS-15-276 | 2 | CODE | Metric technology | X'01'<br>X'02' | Fixed-metric technology<br>Relative-metric technology | X'01'<br>X'02' | ❓ |
| IPDS-15-277 | 3 | CODE | Unit base | X'00' | Raster-pattern resolution unit base:<br>Ten inches | X'00' | ❓ |
| IPDS-15-278 | 4–5 | UBIN | X units per unit base | X'0001' – X'7FFF' | Raster-pattern resolution units per unit base in the X direction:<br>X'0960' = 240 pels per inch<br>X'0BB8' = 300 pels per inch | X'0960'<br>X'0BB8' | ❓ |
| IPDS-15-279 | 6–7 | UBIN | Y units per unit base | X'0001' – X'7FFF' | Optional raster-pattern resolution units per unit base in the Y direction:<br>X'0960' = 240 pels per inch<br>X'0BB8' = 300 pels per inch<br>This optional field can be omitted if the X and Y resolutions are equal. | X'0960'<br>X'0BB8' | ❓ |
| IPDS-15-280 | Triplet X'84'—Font Resolution | ❓ |
| IPDS-15-281 | Triplet X'84'—Font Resolution | ❓ |
| IPDS-15-282 | If specified on an AFO command, the operation applies to the current sheet and each copy of that sheet. | ❓ |
| IPDS-15-283 | If specified on an XOH-DGB command, the operation applies to a collection of sheets (the sheets within a | ❓ |
| IPDS-15-284 | If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the | ❓ |
| IPDS-15-285 | If an operation can only be fully specified in one of the triplets, that triplet must be used. | ❓ |
| IPDS-15-286 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-287 | This triplet is defined as follows: | ❓ |
| IPDS-15-288 | 0 | UBIN | Length | X'09'–X'FD' odd values | Length of the triplet, including this length field | X'09' | ❓ |
| IPDS-15-289 | 1 | CODE | TID | X'85' | Identifies the Finishing Operation triplet | X'85' | ❓ |
| IPDS-15-290 | 2 | CODE | Operation type | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05'<br>X'06'<br>X'07'<br>X'08'<br>X'09'<br>X'0A'<br>X'0C'<br>X'0D'<br>X'0E'<br>X'0F'<br>X'12'<br>X'14'<br>X'18'<br>X'19'<br>X'1E'<br>X'1F'<br>X'20'<br>X'21'<br>X'22'<br>X'30'<br>X'31'<br>X'32' | Corner staple<br>Saddle-stitch out<br>Edge stitch<br>Fold in<br>Separation cut<br>Perforation cut<br>Z fold<br>Center-fold in<br>Trim after center fold or saddle stitch<br>Punch<br>Perfect bind<br>Ring bind<br>C-fold in<br>Accordion-fold in<br>Saddle-stitch in<br>Fold out<br>Center-fold out<br>Trim<br>C-fold out<br>Accordion-fold out<br>Double parallel-fold in<br>Double gate-fold in<br>Single gate-fold in<br>Double parallel-fold out<br>Double gate-fold out<br>Single gate-fold out | At least one value | ❓ |
| IPDS-15-291 | 3 | CODE | Finishing option | X'00'<br>X'01' | Finishing option, for certain finishing operations:<br>No finishing option<br>Crease (for folding operations) | X'00' | ❓ |
| IPDS-15-292 | 4 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-293 | 5 | CODE | Reference | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Reference corner and edge:<br>Bottom-right corner; bottom edge<br>Top-right corner; right edge<br>Top-left corner; top edge<br>Bottom-left corner; left edge<br>Default corner; default edge | X'FF' | ❓ |
| IPDS-15-294 | 6 | UBIN | Count | X'00'<br>X'01'–X'7A' | Not specified<br>Number of operations to apply | X'00' | ❓ |
| IPDS-15-295 | 7–8 | UBIN | Axis offset | X'0000' – X'7FFF'<br>X'FFFF' | Axis offset in millimeters<br>Device default axis offset | X'FFFF' | ❓ |
| IPDS-15-296 | Zero or more finishing operation positions in the following format:** | ❓ |
| IPDS-15-297 | + 0–1 | UBIN | Position | X'0000' – X'7FFF' | Operation position on axis in millimeters | ❓ |
| IPDS-15-298 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-299 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-300 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-301 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-302 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-303 | If, within a single XOH Define Group Boundary command, a Finishing | ❓ |
| IPDS-15-304 | If this operation is specified, but is not immediately after a center-fold or | ❓ |
| IPDS-15-305 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-306 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-307 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-308 | 3 hole punch operation | ❓ |
| IPDS-15-309 | 4.4 inches from the top. | ❓ |
| IPDS-15-310 | 4.4 inches from the top. | ❓ |
| IPDS-15-311 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-312 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-313 | If no position values are specified, the count value specifies the number of staples or holes | ❓ |
| IPDS-15-314 | If any position values are specified, the count must either be X'00' or match the number of | ❓ |
| IPDS-15-315 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-316 | Triplet X'85'—Finishing Operation | ❓ |
| IPDS-15-317 | directions of the currently active XOA-EHC command. | ❓ |
| IPDS-15-318 | 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' | ❓ |
| IPDS-15-319 | 1 | CODE | TID | X'86' | Identifies the Text Fidelity triplet | X'86' | ❓ |
| IPDS-15-320 | 2 | CODE | Continue | X'01'<br>X'02' | Text exception continuation rule:<br>Stop processing WT data<br>Continue processing WT data | X'01'<br>X'02' | ❓ |
| IPDS-15-321 | 3 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-322 | 4 | CODE | Report | X'01'<br>X'02' | Text exception reporting rule:<br>Report text exceptions<br>Do not report text exceptions | X'01'<br>X'02' | ❓ |
| IPDS-15-323 | 5–6 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-324 | Triplet X'86'—Text Fidelity | ❓ |
| IPDS-15-325 | Triplet X'86'—Text Fidelity | ❓ |
| IPDS-15-326 | the finishing operation. | ❓ |
| IPDS-15-327 | 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' | ❓ |
| IPDS-15-328 | 1 | CODE | TID | X'88' | Identifies the Finishing Fidelity triplet | X'88' | ❓ |
| IPDS-15-329 | 2 | CODE | Continue | X'01'<br>X'02' | Finishing exception continuation rule:<br>Stop at first finishing exception<br>Continue without the finishing operation | X'01'<br>X'02' | ❓ |
| IPDS-15-330 | 3 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-331 | 4 | CODE | Report | X'01'<br>X'02' | Finishing exception reporting:<br>Report finishing exceptions<br>Do not report finishing exceptions | X'01'<br>X'02' | ❓ |
| IPDS-15-332 | 5–6 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-333 | Triplet X'88'—Finishing Fidelity | ❓ |
| IPDS-15-334 | Triplet X'88'—Finishing Fidelity | ❓ |
| IPDS-15-335 | Vertical font size | ❓ |
| IPDS-15-336 | Horizontal scale factor | ❓ |
| IPDS-15-337 | Character rotation | ❓ |
| IPDS-15-338 | Character encoding scheme | ❓ |
| IPDS-15-339 | 1. A data-object-font component (a TrueType/OpenType font, or a TrueType/OpenType collection) must be | ❓ |
| IPDS-15-340 | 2. The component parts (a font or font collection, an optional code page, and optional linked TrueType/ | ❓ |
| IPDS-15-341 | 3. A Load Font Equivalence command must be sent to the printer to establish the LID to data-object font | ❓ |
| IPDS-15-342 | Triplet X'8B'—Data Object Font Descriptor | ❓ |
| IPDS-15-343 | 1. A code page | ❓ |
| IPDS-15-344 | 2. A Unicode transformation (UTF-8) | ❓ |
| IPDS-15-345 | 3. Default to the encoding scheme specified in the Encoding Environment and Encoding ID parameters of this | ❓ |
| IPDS-15-346 | triplets are ignored. | ❓ |
| IPDS-15-347 | 0 | UBIN | Length | X'10' | Length of the triplet, including this length field | X'10' | ❓ |
| IPDS-15-348 | 1 | CODE | TID | X'8B' | Data Object Font Descriptor triplet | X'8B' | ❓ |
| IPDS-15-349 | 2 | BITS | Font flags | ❓ |
| IPDS-15-350 | bit 0 | MICR | B'0'<br>B'1' | MICR print flag:<br>Normal printing<br>MICR printing | B'0'<br>B'1' | ❓ |
| IPDS-15-351 | bit 1 | Location of font | B'0'<br>B'1' | Location of font:<br>Font can be located anywhere in the MO:DCA resource hierarchy<br>Font is located in the resource group for the print file<br>Note: This flag is ignored by IPDS printers. | B'0'<br>B'1' | ❓ |
| IPDS-15-352 | bits 2–7 | | B'0000000' | Reserved | B'0000000' | ❓ |
| IPDS-15-353 | 3 | CODE | Font technology | X'20' | Font technology: TrueType/OpenType | X'20' | ❓ |
| IPDS-15-354 | 4–5 | UBIN | VFS | X'0001' – X'7FFF' | Specified vertical font size in 1440ths of an inch | X'0001' – X'7FFF' | ❓ |
| IPDS-15-355 | 6–7 | UBIN | HSF | X'0000'<br>X'0001' – X'7FFF' | No value supplied<br>Horizontal scale factor in 1440ths of an inch | X'0000'<br>X'0001' – X'7FFF' | ❓ |
| IPDS-15-356 | 8–9 | SBIN | Character rotation | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | Clockwise rotation of a character pattern (glyph):<br>0 degrees<br>90 degrees<br>180 degrees<br>270 degrees | X'0000'<br>X'2D00'<br>X'5A00'<br>X'8700' | ❓ |
| IPDS-15-357 | 10–11 | CODE | Encoding environment | X'0003' | Encoding environment: Microsoft | X'0003' | ❓ |
| IPDS-15-358 | 12–13 | CODE | Encoding ID | X'0001' | Environment-specific encoding: Microsoft—Unicode | X'0001' | ❓ |
| IPDS-15-359 | 14–15 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-360 | Triplet X'8B'—Data Object Font Descriptor | ❓ |
| IPDS-15-361 | Triplet X'8B'—Data Object Font Descriptor | ❓ |
| IPDS-15-362 | For the Microsoft environment (bytes 10–11 = X'0003'), the defined encoding identifiers | ❓ |
| IPDS-15-363 | Triplet X'8B'—Data Object Font Descriptor | ❓ |
| IPDS-15-364 | Triplet X'8B'—Data Object Font Descriptor | ❓ |
| IPDS-15-365 | character (represented by glyph index 0 for a TrueType or OpenType font) is used from the base font. | ❓ |
| IPDS-15-366 | 0 | UBIN | Length | X'05'–X'FF' odd values | Length of the triplet, including this length field | X'05'–X'FF' odd values | ❓ |
| IPDS-15-367 | 1 | CODE | TID | X'8D' | Linked Font triplet | X'8D' | ❓ |
| IPDS-15-368 | 2–3 | CODE | HAID | X'0001' – X'7EFF' | Host-assigned ID of previously activated linked font object | X'0001' – X'7EFF' | ❓ |
| IPDS-15-369 | 4 | CODE | Font ID type | X'00'<br>X'01'<br>X'02' | Type of data in font ID field:<br>No font ID provided<br>TTC font index<br>Full font name | X'00'<br>X'01'<br>X'02' | ❓ |
| IPDS-15-370 | 5–254 | | Font ID | Any value | Identification of a TrueType/OpenType font within a collection | Any value | ❓ |
| IPDS-15-371 | TrueType/OpenType font | ❓ |
| IPDS-15-372 | TrueType/OpenType collection | ❓ |
| IPDS-15-373 | Triplet X'8D'—Linked Font | ❓ |
| IPDS-15-374 | X'00'—No font ID provided; this value must be used when this triplet is linking a TrueType/ | ❓ |
| IPDS-15-375 | X'01'—TTC font index; this value must be used when this triplet is linking a TrueType/ | ❓ |
| IPDS-15-376 | X'02'—Full font name; this value must be used when this triplet is linking a TrueType/ | ❓ |
| IPDS-15-377 | When the Font ID type is X'01', this parameter contains a TTC font index value. The index | ❓ |
| IPDS-15-378 | When the Font ID type is X'02', this parameter contains a full font name. The full font name | ❓ |
| IPDS-15-379 | Triplet X'8D'—Linked Font | ❓ |
| IPDS-15-380 | If specified on an AFO command, the operation applies to the current sheet and each copy of that sheet. | ❓ |
| IPDS-15-381 | If specified on an XOH-DGB command, the operation applies to a collection of sheets (the sheets within a | ❓ |
| IPDS-15-382 | If an operation (and all parameters) can be specified in either triplet, either triplet can be specified and the | ❓ |
| IPDS-15-383 | If an operation can only be fully specified in one of the triplets, that triplet must be used. | ❓ |
| IPDS-15-384 | This triplet is defined as follows: | ❓ |
| IPDS-15-385 | 0 | UBIN | Length | X'0D'–X'FE' | Length of the triplet, including this length field | X'0D'–X'FE' | ❓ |
| IPDS-15-386 | 1 | CODE | TID | X'8E' | Identifies the UP3I Finishing Operation triplet | X'8E' | ❓ |
| IPDS-15-387 | 2 | UBIN | Sequence number | X'00'–X'FF' | Sequence number of this triplet | X'00'–X'FF' | ❓ |
| IPDS-15-388 | 3 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-389 | 4 to end | | Data | | Finishing operation data as defined in the UP3I Specification; this field contains bytes 4 to end of the UP3I Form Finishing Operating (X'03') triplet; extra bytes beyond the UP3I-defined bytes are ignored. | ❓ |
| IPDS-15-390 | Triplet X'8E'—UP³I Finishing Operation | ❓ |
| IPDS-15-391 | Triplet X'8E'—UP³I Finishing Operation | ❓ |
| IPDS-15-392 | 1. A color-conversion CMR can be defined as a pass-through audit color-conversion CMR by specifying the character | ❓ |
| IPDS-15-393 | 2. Some IPDS printers support instruction tone-transfer-curve CMRs, but ignore audit tone-transfer-curve CMRs. | ❓ |
| IPDS-15-394 | Triplet X'91'—Color Management Resource Descriptor | ❓ |
| IPDS-15-395 | command-set vector of an STM reply. | ❓ |
| IPDS-15-396 | 0 | UBIN | Length | X'05' | Length of the triplet, including this length field | X'05' | ❓ |
| IPDS-15-397 | 1 | CODE | TID | X'91' | Color Management Resource Descriptor triplet | X'91' | ❓ |
| IPDS-15-398 | 2 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-399 | 3 | CODE | Mode | X'01'<br>X'02'<br>X'03' | Processing mode:<br>Process as an audit CMR<br>Process as an instruction CMR<br>Process as a link CMR | X'01'<br>X'02'<br>X'03' | ❓ |
| IPDS-15-400 | 4 | X'01'–X'05' | Reserved | | Ignored | ❓ |
| IPDS-15-401 | Link color-conversion (subset “LK”) CMR that is used to provide for more efficient | ❓ |
| IPDS-15-402 | ICC DeviceLink (subset “DL”) CMR that is used to customize the conversion | ❓ |
| IPDS-15-403 | Triplet X'91'—Color Management Resource Descriptor | ❓ |
| IPDS-15-404 | Triplet X'91'—Color Management Resource Descriptor | ❓ |
| IPDS-15-405 | command-set vector of an STM reply. | ❓ |
| IPDS-15-406 | 0 | UBIN | Length | X'04' | Length of the triplet, including this length field | X'04' | ❓ |
| IPDS-15-407 | 1 | CODE | TID | X'92' | Invoke CMR triplet | X'92' | ❓ |
| IPDS-15-408 | 2–3 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID of CMR | X'0001' – X'7EFF' | ❓ |
| IPDS-15-409 | Triplet X'92'—Invoke CMR | ❓ |
| IPDS-15-410 | Perceptual: gamut mapping is vendor-specific, and colors are adjusted to give a pleasing appearance. This | ❓ |
| IPDS-15-411 | Media-relative colorimetric: in-gamut colors are rendered accurately, and out-of-gamut colors are mapped to | ❓ |
| IPDS-15-412 | Saturation: gamut mapping is vendor-specific, and colors are adjusted to emphasize saturation. This intent | ❓ |
| IPDS-15-413 | ICC-absolute colorimetric: in-gamut colors are rendered accurately, and out-of-gamut colors are mapped to | ❓ |
| IPDS-15-414 | Triplet X'95'—Rendering Intent | ❓ |
| IPDS-15-415 | command-set vector of an STM reply. | ❓ |
| IPDS-15-416 | 0 | UBIN | Length | X'0A' | Length of the triplet, including this length field | X'0A' | ❓ |
| IPDS-15-417 | 1 | CODE | TID | X'95' | Rendering Intent triplet | X'95' | ❓ |
| IPDS-15-418 | 2–3 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-419 | 4 | CODE | IOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for IO-Image objects:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | ❓ |
| IPDS-15-420 | 5 | CODE | Object container | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for presentation object containers:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | ❓ |
| IPDS-15-421 | 6 | CODE | PTOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for PTOCA text:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | ❓ |
| IPDS-15-422 | 7 | CODE | GOCA | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | Desired rendering intent for GOCA graphics objects:<br>Perceptual<br>Media-relative colorimetric<br>Saturation<br>ICC-absolute colorimetric<br>Not specified | X'00'<br>X'01'<br>X'02'<br>X'03'<br>X'FF' | ❓ |
| IPDS-15-423 | 8–9 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-424 | Triplet X'95'—Rendering Intent | ❓ |
| IPDS-15-425 | Triplet X'95'—Rendering Intent | ❓ |
| IPDS-15-426 | field in the XOH-OPC reply. | ❓ |
| IPDS-15-427 | 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' | ❓ |
| IPDS-15-428 | 1 | CODE | TID | X'96' | CMR Tag Fidelity triplet | X'96' | ❓ |
| IPDS-15-429 | 2 | CODE | Continue | X'01'<br>X'02' | Exception continuation rule:<br>Stop on X'025D..04'<br>Continue processing CMR data | X'01'<br>X'02' | ❓ |
| IPDS-15-430 | 3 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-431 | 4 | CODE | Report | X'01'<br>X'02' | Exception reporting rule:<br>Report X'025D..04'<br>Do not report X'025D..04' | X'01'<br>X'02' | ❓ |
| IPDS-15-432 | 5–6 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-433 | Triplet X'96'—CMR Tag Fidelity | ❓ |
| IPDS-15-434 | Triplet X'96'—CMR Tag Fidelity | ❓ |
| IPDS-15-435 | Device Appearance (X'97') triplets in the SPE command. | ❓ |
| IPDS-15-436 | 0 | UBIN | Length | X'07' | Length of the triplet, including this length field | X'07' | ❓ |
| IPDS-15-437 | 1 | CODE | TID | X'97' | Device Appearance triplet | X'97' | ❓ |
| IPDS-15-438 | 2 | X'00' | Reserved | X'00' | ❓ |
| IPDS-15-439 | 3–4 | CODE | Appearance | X'0000'<br>X'0001' | Device appearance to assume:<br>Assume device-default appearance<br>Assume device-default monochrome appearance | X'0000' | ❓ |
| IPDS-15-440 | 5–6 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-441 | Triplet X'97'—Device Appearance | ❓ |
| IPDS-15-442 | Triplet X'97'—Device Appearance | ❓ |
| IPDS-15-443 | When the mapping option is | ❓ |
| IPDS-15-444 | When the mapping option is scale to fit, if the resolution in the x and y directions is the same, the pixel | ❓ |
| IPDS-15-445 | When the mapping option is position, position and trim, or center and trim, both the pixel counts and | ❓ |
| IPDS-15-446 | Container command-set vector. | ❓ |
| IPDS-15-447 | 0 | UBIN | Length | X'0A' | Length of the triplet, including this length field | X'0A' | ❓ |
| IPDS-15-448 | 1 | CODE | TID | X'9A' | Image Resolution triplet | X'9A' | ❓ |
| IPDS-15-449 | 2–3 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-450 | 4 | CODE | X unit base | X'00'<br>X'01' | Unit base for image resolution in the X direction:<br>10 inches<br>10 centimeters | X'00' | ❓ |
| IPDS-15-451 | 5 | CODE | Y unit base | X'00'<br>X'01' | Unit base for image resolution in the Y direction (must be the same as X unit base):<br>10 inches<br>10 centimeters | X'00' | ❓ |
| IPDS-15-452 | 6–7 | UBIN | XUPUB | X'0001' – X'7FFF' | Number of image points in the X-direction per X unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-15-453 | 8–9 | UBIN | YUPUB | X'0001' – X'7FFF' | Number of image points in the Y-direction per Y unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-15-454 | Triplet X'9A'—Image Resolution | ❓ |
| IPDS-15-455 | Triplet X'9A'—Image Resolution | ❓ |
| IPDS-15-456 | return the X'1209' property pair. | ❓ |
| IPDS-15-457 | 0 | UBIN | Length | X'05', X'11' | Length of the triplet, including this length field | X'05' | ❓ |
| IPDS-15-458 | 1 | CODE | TID | X'9C' | Object Container Presentation Space Size triplet | X'9C' | ❓ |
| IPDS-15-459 | 2–3 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-460 | 4 | CODE | PDF Presentation space size | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05' | Choice of presentation-space size:<br>Use the MediaBox (default)<br>Use the CropBox<br>Use the BleedBox<br>Use the TrimBox<br>Use the ArtBox | X'01'<br>X'02'<br>X'03'<br>X'04'<br>X'05' | ❓ |
| IPDS-15-461 | 5 | CODE | X unit base | X'00'<br>X'01' | Unit base for presentation-space size in the X direction:<br>10 inches<br>10 centimeters | X'00' | ❓ |
| IPDS-15-462 | 6 | CODE | Y unit base | X'00'<br>X'01' | Unit base for presentation-space size in the Y direction (must be the same as the X unit base):<br>10 inches<br>10 centimeters | X'00' | ❓ |
| IPDS-15-463 | 7–8 | UBIN | XUPUB | X'0001' – X'7FFF' | $X_{oc}$ units per unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-15-464 | 9–10 | UBIN | YUPUB | X'0001' – X'7FFF' | $Y_{oc}$ units per unit base | X'0001' – X'7FFF' | ❓ |
| IPDS-15-465 | 11–13 | UBIN | $X_{oc}$ extent | X'000001' – X'007FFF' | $X_{oc}$ extent of the presentation-space size<br>(Refer to the note following the table.) | X'000001' – X'007FFF' | ❓ |
| IPDS-15-466 | 14–16 | UBIN | $Y_{oc}$ extent | X'000001' – X'007FFF' | $Y_{oc}$ extent of the presentation-space size<br>(Refer to the note following the table.) | X'000001' – X'007FFF' | ❓ |
| IPDS-15-467 | Triplet X'9C'—Object Container Presentation Space Size | ❓ |
| IPDS-15-468 | Portable Document Format (PDF) single page | ❓ |
| IPDS-15-469 | Portable Document Format (PDF) single page with transparency | ❓ |
| IPDS-15-470 | PDF Multiple Page File | ❓ |
| IPDS-15-471 | PDF Multiple Page - with Transparency - File | ❓ |
| IPDS-15-472 | 1. In addition to specifying the presentation space size, this parameter also indicates the | ❓ |
| IPDS-15-473 | 2. As specified in the PDF specification, if the CropBox, BleedBox, TrimBox, or ArtBox | ❓ |
| IPDS-15-474 | AFPC SVG Subset | ❓ |
| IPDS-15-475 | Triplet X'9C'—Object Container Presentation Space Size | ❓ |
| IPDS-15-476 | Triplet X'9C'—Object Container Presentation Space Size | ❓ |
| IPDS-15-477 | command-set vector of an STM reply. | ❓ |
| IPDS-15-478 | 0 | UBIN | Length | X'06'–X'CC' even values | Length of the triplet, including this length field | X'06'–X'CC' even values | ❓ |
| IPDS-15-479 | 1 | CODE | TID | X'9E' | Setup Name triplet | X'9E' | ❓ |
| IPDS-15-480 | 2–3 | X'0000' | Reserved | X'0000' | ❓ |
| IPDS-15-481 | 4 to end | CHAR | Setup name | any UTF-16BE character | Setup name specified as a UTF-16BE character string | any UTF-16BE character | ❓ |
| IPDS-15-482 | Triplet X'9E'—Setup Name | ❓ |
| IPDS-15-483 | so invocation of tertiary CMRs is done using this triplet. | ❓ |
| IPDS-15-484 | 0 | UBIN | Length | X'0A'–X'FF' | Length of the triplet, including this length field | X'0A'–X'FF' | ❓ |
| IPDS-15-485 | 1 | CODE | TID | X'A2' | Invoke Tertiary Resource triplet | X'A2' | ❓ |
| IPDS-15-486 | 2 | CODE | TRType | X'01' | Tertiary resource type:<br>CMR | X'01' | ❓ |
| IPDS-15-487 | 3–4 | CODE | HAID | X'0001' – X'7EFF' | Host-Assigned ID of tertiary resource | X'0001' – X'7EFF' | ❓ |
| IPDS-15-488 | 5–8 | | X'00000000' | Reserved | | X'00000000' | ❓ |
| IPDS-15-489 | 9 | CODE | IDType | X'01' | Internal resource ID type:<br>Image local ID | X'01' | ❓ |
| IPDS-15-490 | 10 to end | UNDF | Internal resource ID | Any value | Internal identifier for the secondary resource for which the tertiary resource is being invoked | Any value | ❓ |
| IPDS-15-491 | Triplet X'A2'—Invoke Tertiary Resource | ❓ |
| IPDS-15-492 | Triplet X'A2'—Invoke Tertiary Resource | ❓ |
| IPDS-16-001 | The printer reports exceptions with a NACK. Only one exception can be returned in each NACK. However, | ❓ |
| IPDS-16-002 | The IPDS architecture does not specify the number of NACKs that a printer must queue. Some printers | ❓ |
| IPDS-16-003 | There is no prioritization or required order of reporting of synchronous data stream or resource storage | ❓ |
| IPDS-16-004 | Exceptions contain either three or twenty–four bytes of sense data containing details about the exception. In | ❓ |
| IPDS-16-005 | Other than the case discussed in the next bullet, once a printer with queued NACKs begins returning its | ❓ |
| IPDS-16-006 | The OAEI command is the one exception to the rule that IPDS commands are not processed while the | ❓ |
| IPDS-16-007 | there are NACKs remaining, the command is not processed and the next NACK is returned. If, however, the | ❓ |
| IPDS-16-008 | A printer can make additional exception information available for a given exception—and thus the additional- | ❓ |
| IPDS-16-009 | The printer can send an ACK or a NACK in response to an Acknowledgment Request (ARQ) flag. If an | ❓ |
| IPDS-16-010 | If the printer receives a command requesting an acknowledgment, it expects the host to wait for the | ❓ |
| IPDS-16-011 | When an exception is reported, all upstream data is discarded. | ❓ |
| IPDS-16-012 | All synchronous exceptions for a given page must be reported to the host before any exceptions on | ❓ |
| IPDS-16-013 | When the printer has one or more asynchronous exceptions to report (exceptions with an action code other | ❓ |
| IPDS-16-014 | An ACK indicates that the data stream up to the command with the Acknowledgment Request (ARQ) has | ❓ |
| IPDS-16-015 | pages on the sheet have been syntax-checked for synchronous data-stream exceptions. | ❓ |
| IPDS-16-016 | The Exception-Handling Control (EHC) order of the Execute Order Anystate (XOA) command is used to tell | ❓ |
| IPDS-16-017 | If a command-reject exception is detected by the printer, no portion of the command is accepted by the | ❓ |
| IPDS-16-018 | printer; that is, the entire command is discarded. | ❓ |
| IPDS-16-019 | Skip and Continue Actions. The printer can skip the data object containing the command | ❓ |
| IPDS-16-020 | Page Continuation Actions. The printer can terminate or continue processing a page that | ❓ |
| IPDS-16-021 | Whether or not printing should continue when an exception is detected | ❓ |
| IPDS-16-022 | Whether or not an exception should be reported | ❓ |
| IPDS-16-023 | For color exceptions, what type of color substitution is permitted | ❓ |
| IPDS-16-024 | command and the XOA-EHC command. | ❓ |
| IPDS-16-025 | Undefined characters | ❓ |
| IPDS-16-026 | Position exceptions | ❓ |
| IPDS-16-027 | All other exceptions that have AEAs | ❓ |
| IPDS-16-028 | For complete information about alternate exception actions, refer to “XOA Exception-Handling Control”. | ❓ |
| IPDS-16-029 | 3 bytes of sense data or 24 bytes of sense data. | ❓ |
| IPDS-16-030 | Command reject | ❓ |
| IPDS-16-031 | Equipment check with intervention required | ❓ |
| IPDS-16-032 | Intervention required | ❓ |
| IPDS-16-033 | Equipment check | ❓ |
| IPDS-16-034 | Data check | ❓ |
| IPDS-16-035 | Metadata specification check | ❓ |
| IPDS-16-036 | IO-Image specification check | ❓ |
| IPDS-16-037 | Bar Code specification check | ❓ |
| IPDS-16-038 | Graphics specification check | ❓ |
| IPDS-16-039 | General specification check | ❓ |
| IPDS-16-040 | Conditions requiring host notification | ❓ |
| IPDS-16-041 | Bytes 20–23 Contains the page identifier for the page that has the exception (except for format 2). | ❓ |
| IPDS-16-042 | 24 sense bytes | ❓ |
| IPDS-16-043 | 0       1       2       3       4       5       6       7       8       9      10     11     12     13     14     15     16     17     18     19     20     21     22     23 | ❓ |
| IPDS-16-044 | Undefined code point | ❓ |
| IPDS-16-045 | IOCA self-defining field in error | ❓ |
| IPDS-16-046 | Data object error code | ❓ |
| IPDS-16-047 | Inconsistent media ID | ❓ |
| IPDS-16-048 | Bad Unicode value | ❓ |
| IPDS-16-049 | 1. The fields in sense bytes 8–17 contains binary zeroes when information is not appropriate or available for | ❓ |
| IPDS-16-050 | 2. For exception ID X'0237..04', sense bytes 12–13 contain the command code for a BP , EP , or LCC | ❓ |
| IPDS-16-051 | and sense bytes 16–17 contain the media-destination ID that is inconsistent with the media-source ID. | ❓ |
| IPDS-16-052 | 3. Sense bytes 16–17 contain additional useful information that is specific to the particular NACK, as follows: | ❓ |
| IPDS-16-053 | Table 63. Exception ID Specific Information | ❓ |
| IPDS-16-054 | X'0821..00'<br>X'0829..00'<br>X'028F..50' | Bytes 16–17 contain the code point that caused the error. For double-byte fonts, byte 16 contains the section ID and byte 17 contains the 2nd byte of the code point. For single-byte fonts, byte 17 contains the code point. | ❓ |
| IPDS-16-055 | X'0500..01'<br>X'0500..03'<br>X'0500..04' | Bytes 16–17 contain the IOCA self-defining field code that caused the error. For one-byte codes, byte 16 contains X'00' and byte 17 contains the code. | ❓ |
| IPDS-16-056 | X'0406..11' | Bytes 16–17 contain the size of the smallest valid symbol width in twips. | ❓ |
| IPDS-16-057 | X'0412..00' | Bytes 16–17 contain the application ID (ai) value for the bad data. The ai value is read as four decimal digits, with leading zeroes if necessary. For example, ai = 01 is shown as X'0001' in bytes 16–17 and ai = 8005 is shown as X'8005' in bytes 16–17. | ❓ |
| IPDS-16-058 | X'03C3..03'<br>X'021A..03' | Bytes 16–17 contain the Unicode code value in error, as follows:<br>• When a high-order surrogate code value was not immediately followed by a low-order surrogate code value, bytes 16–17 contain the high-order surrogate code value.<br>• When a low-order surrogate code value was not immediately preceded by a high-order surrogate code value, bytes 16–17 contain the low-order surrogate code value.<br>• When an illegal UTF-8 code value sequence was specified, bytes 16–17 contain the first two bytes of the UTF-8 code value sequence. | ❓ |
| IPDS-16-059 | X'0200..01' | Bytes 16–17 contain the unsupported or unrecognized PTOCA control sequence function type that caused this error. Byte 16 contains X'00' and byte 17 contains the function-type value. | ❓ |
| IPDS-16-060 | X'020D..01'<br>X'020D..05'<br>X'0115..00' | Bytes 16–17 contain an object-specific error code. Refer to “Error Codes for Other Data Objects” for a list of object-specific error codes. | ❓ |
| IPDS-16-061 | X'0237..04' | Bytes 16–17 contain the inconsistent media destination ID. | ❓ |
| IPDS-16-062 | X'025D..ee' | Bytes 16–17 contain a CMR TagID value as defined in the Color Management Object Content Architecture Reference. | ❓ |
| IPDS-16-063 | X'029C..02' | Bytes 16–17 contain the glyph ID that caused the error. | ❓ |
| IPDS-16-064 | X'0000' in sense bytes 16–17. | ❓ |
| IPDS-16-065 | Format 5 Retired item 82 | ❓ |
| IPDS-16-066 | associated with a particular page, this field contains X'00000000'. | ❓ |
| IPDS-16-067 | Table 64. Action Codes | ❓ |
| IPDS-16-068 | X'01' | **Data-Stream Exception**<br>A syntax error has been found in an IPDS command. The host recovery action depends on the specific exception and on host-support requirements. Data-stream exceptions are discovered while the printer is accepting and syntax checking IPDS commands. For commands containing page data, the page in which the error occurred is either the page just before the Received Page Counter or the page at the Received Page Counter depending upon the current XOA-EHC command and whether multiple pages are to be presented on the current sheet. Refer to “Load Copy Control” for further details. | ❓ |
| IPDS-16-069 | X'05' | **End IPDS dialog**<br>The printer has received a request to print from another session and asks the presentation services program to end the current IPDS dialog (or the current carrying-protocol session) as soon as possible, such as at the end of the current print unit. If the printer is currently receiving a page or resource, the partial page or resource is discarded so that the host and printer are synchronized to the beginning of the page or resource. This condition does not affect the page or copy counters. | ❓ |
| IPDS-16-070 | X'06' | **Function no longer achievable**<br>The printer detected that a previously requested function can no longer be performed. The host recovery depends on the specific exception and on host-support requirements. This condition does not adjust the page or copy counters. | ❓ |
| IPDS-16-071 | X'08' | **Physical Media Jam**<br>The printer has detected a physical media jam. The printer has discarded all buffered pages and modified the page and copy counters. Retransmit all pages that have not passed the printer-defined jam-recovery point and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Physical media jams occur on the next sheet that would have reached the Jam-Recovery Page Counter. | ❓ |
| IPDS-16-072 | X'09' | **Data-Related Print Exception**<br>A sheet cannot be printed because of something within the data stream; for example, the data might be too complex, or too dense, or the media source selected might be incompatible with the media destination selected. The printer has discarded all buffered pages and modified the page and copy counters. Recovery depends on host-support requirements. Data-related print exceptions occur on the next sheet that would have reached the Committed Page Counter. | ❓ |
| IPDS-16-073 | X'0A' | **Pre-processor or post-processor exception**<br>The printer has detected a condition in a pre-processor or post-processor device that has caused all pages that would have reached the jam-recovery station to be discarded. The printer has discarded all buffered pages and modified the page and copy counters. Host recovery depends on the specific exception and on host-support requirements. Post-processor exceptions cause all pages that would have reached the Jam-Recovery Page Counter to be discarded. | ❓ |
| IPDS-16-074 | X'0C' | **Resource Storage Exception**<br>The printer cannot accept a page or resource (overlay, page segment, font, or data object resource) because the storage area is full; the printer has discarded the partial page or resource. If the exception occurred while saving a page, that page is discarded, but previously saved pages are kept. When an out-of-storage exception causes the first page of a group to be discarded, the group is terminated and information concerning the group is discarded. Deactivate unused resources and retry; if this action fails, the recovery action depends on host-support requirements. Resource storage exceptions occur on the next page that would have reached the Received Page Counter. | ❓ |
| IPDS-16-075 | X'0D' | **Printer Restart**<br>The printer has discarded all pages and downloaded resources (overlays, page segments, fonts, and data object resources) because of operator intervention or because of a hardware failure. All saved page groups are deactivated and might also be removed. All page and copy counters have been reset to zero. Recovery depends on host-support requirements. | ❓ |
| IPDS-16-076 | X'15' | **Cancel**<br>The printer operator has requested that the current print data be canceled. The printer has discarded all buffered pages and modified the page and copy counters. If the Committed Copy Counter is zero, cancel the print data containing the page at the Committed Page Counter. If the Committed Copy Counter is not zero, cancel the print data containing the page that will next reach the Committed Page Counter. | ❓ |
| IPDS-16-077 | X'16' | **Hardware-Related Print Exception**<br>The printer has discarded all buffered pages because of a condition detected at the printer. Retransmit all pages that have not been committed for printing and any associated resources that are not already in the printer. Hardware-related print exceptions occur on the next sheet that would have reached the Committed Page Counter. | ❓ |
| IPDS-16-078 | X'17' | **Printer Mechanism Unusable**<br>A printer mechanism, such as the offset stacker, a duplex media path, or an input media source, has become unusable. Printing might still be possible if the unusable mechanism is bypassed. The printer has discarded all buffered pages and modified the page and copy counters. Host software should take appropriate action. | ❓ |
| IPDS-16-079 | X'19' | **Asynchronous Data-Stream Exception**<br>An attempt was made to print outside the valid printable area or to print an undefined text, bar code HRI, or graphics character. The printer has discarded all buffered pages and modified the page and copy counters. The appropriate recovery action depends on host-support requirements. Asynchronous data-stream exceptions occur on a page that is between the Received and Committed Page Counters. The host must issue an XOH-PBD command to ensure that the page and copy counters are accurately adjusted. After the XOH-PBD command has successfully completed, the page in error is either one of the pages on the last sheet just before the Committed Page Counter or the page at the Committed Page Counter, depending on the appropriate XOA-EHC command. | ❓ |
| IPDS-16-080 | X'1A' | **Redrive Buffered Pages**<br>The printer has discarded buffered pages due to a printer operator action or a hardware problem. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Redrive-buffered-pages exceptions occur on the next sheet that would have reached the Committed Page Counter. | ❓ |
| IPDS-16-081 | X'1B' | **Recovery-Unit Group Exception**<br>The printer has detected a condition that has caused all pages that have not yet reached the jam-recovery point to be discarded while a recovery-unit group operation was active. The printer has discarded all pages that have not yet reached the jam-recovery point and has modified the page and copy counters. Retransmit pages from the jam recovery page counter plus one and reload any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Recovery-unit group exceptions cause all pages that have not yet reached the jam-recovery point to be discarded so that host recovery can reposition to a group boundary. | ❓ |
| IPDS-16-082 | X'1D' | **Printer Characteristics Changed**<br>At least one of the printer characteristics that is reported in the reply to an XOH-OPC command has changed. The printer has discarded all buffered pages and modified the page and copy counters. The host should issue an XOH-OPC command to obtain the new printer characteristics. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Printer-characteristics-changed exceptions occur on the next sheet that would have reached the Committed Page Counter. | ❓ |
| IPDS-16-083 | X'1E' | **Asynchronous Out-of-Storage Exception**<br>A resource or a page that is not currently being received at the Received Page station caused an out-of-storage exception. The printer has discarded all buffered pages and reset the page and copy counters. If the exception occurred on a sheet, the sheet will not print and will be discarded. The host must issue an XOH-PBD command to ensure that the page and copy counters are accurately adjusted. Deactivate all resources not necessary to continue printing and retransmit the next page after the one at the Received Page Counter. If this action fails, the recovery action depends on host-support requirements. | ❓ |
| IPDS-16-084 | X'1F' | **Data-Stream Exception in a Secure Overlay**<br>A syntax error has been found in the IPDS data stream of a Secure Overlay. This action code is used to report Data-Stream Exceptions (action code X'01') that occur within a secure overlay, so that the host can perform special recovery for these exceptions. Action code X'1F' is used in place of action code X'01' when:<br>• A data-stream exception is detected while processing a Secure Overlay that was specified by an Include Overlay command in page state, or<br>• An Overlay ID Outside Valid Range exception (X'0290..01') or Overlay ID Not Activated (X'0292..01') is detected while processing an Include Overlay command for a Secure Overlay in page state. If one of these exceptions is detected while processing an Include Overlay command for a non-secure overlay, action code X'01' is used.<br>Note: Printers that completely syntax-check images during download (such as, the IBM 3825 printer) do not return action code X'1F' for data-stream exceptions in an image. | ❓ |
| IPDS-16-085 | X'22' | **Printer Inoperative**<br>A printer condition, such as a permanent hardware exception or an uncleared operator-intervention condition, has occurred from which the printer cannot recover. The host should terminate communication with the printer. Note: An Action Code other than X'22' (such as X'1A') should be used for intervention-required conditions that require host-software recovery. | ❓ |
| IPDS-16-086 | X'23' | **Temporary Hardware Exception**<br>A temporary hardware exception has occurred. The printer has discarded all buffered pages and modified the page and copy counters. Retransmit all pages that have not been committed for printing and any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Temporary hardware exceptions occur on the next sheet that would have reached the Committed Page Counter. | ❓ |
| IPDS-16-087 | X'2B' | **Suspended Recovery-Unit Group Exception**<br>The printer has detected a condition that has caused all pages that have not yet reached the jam-recovery point to be discarded while a suspended recovery-unit group operation was active. The printer has discarded all pages that have not yet reached the jam-recovery point and has modified the page and copy counters. Retransmit pages from the jam recovery page counter plus one and reload any associated resources (overlays, page segments, fonts, saved page groups, and data object resources) that are not already in the printer. Because a suspended recovery-unit group did not start on a sheet boundary, the host cannot reposition to a group boundary; therefore, the entire group is printed, but with blank sheets within the group. | ❓ |
| IPDS-16-088 | Mandatory exceptions must be generated by a printer only if the printer supports the function or command to | ❓ |
| IPDS-16-089 | Mandatory exceptions that can be caused by multiple conditions must be generated by a printer under all the | ❓ |
| IPDS-16-090 | A mandatory exception can be presented with any of the action codes registered for the exception ID. | ❓ |
| IPDS-16-091 | Wherever an OCA-defined exception is classified as mandatory, the IPDS architecture requires that the | ❓ |
| IPDS-16-092 | The subsequent sections provide detailed information about each of the classes listed. | ❓ |
| IPDS-16-093 | Within each exception class, the exceptions are listed in ascending numeric order. | ❓ |
| IPDS-16-094 | 8001..00 Invalid or unsupported IPDS command | ❓ |
| IPDS-16-095 | 8002..00 Invalid or unsupported IPDS command | ❓ |
| IPDS-16-096 | 8004..00 Data received after ARQ | ❓ |
| IPDS-16-097 | 1. The length of an IPDS command is not within the | ❓ |
| IPDS-16-098 | 2. The length of the data within a WGC, WIC2, or WBCC | ❓ |
| IPDS-16-099 | 1. Some printers report this exception as X'0202..02' or | ❓ |
| IPDS-16-100 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-101 | 8001..00 • 80E0..00 | ❓ |
| IPDS-16-102 | 5010..00 Printer-Hardware Exception | ❓ |
| IPDS-16-103 | 5010..00 Printer-Hardware Exception | ❓ |
| IPDS-16-104 | 5010..00 • 50F6..00 | ❓ |
| IPDS-16-105 | 4000..00 Printer not ready | ❓ |
| IPDS-16-106 | 4000..00 Printer not ready | ❓ |
| IPDS-16-107 | The printer has been not ready for a specified amount of | ❓ |
| IPDS-16-108 | A printer door is open and has not been closed by the | ❓ |
| IPDS-16-109 | 4001..00 Out of paper | ❓ |
| IPDS-16-110 | 4001..00 Out of paper | ❓ |
| IPDS-16-111 | 4002..00 Media destination is full | ❓ |
| IPDS-16-112 | 4002..00 Media destination is full | ❓ |
| IPDS-16-113 | 4004..00 Out of toner | ❓ |
| IPDS-16-114 | 4004..00 Out of toner | ❓ |
| IPDS-16-115 | 4005..00 Empty fuser oil supply | ❓ |
| IPDS-16-116 | 4000..00 • 4005..00 | ❓ |
| IPDS-16-117 | 4006..00 Invalid physical media | ❓ |
| IPDS-16-118 | 4010..00 Paper adjustment check | ❓ |
| IPDS-16-119 | 4011..00 Suppressed jam recovery | ❓ |
| IPDS-16-120 | 4012..00 An attempt to print an undefined character | ❓ |
| IPDS-16-121 | An operator intervention condition has occurred because | ❓ |
| IPDS-16-122 | An operator intervention condition has occurred because | ❓ |
| IPDS-16-123 | The operator intervention condition might have been | ❓ |
| IPDS-16-124 | 4013..00 Continuous-forms media needs to be torn | ❓ |
| IPDS-16-125 | 4014..00 Asynchronous decompression error | ❓ |
| IPDS-16-126 | 4016..00 Data validation error | ❓ |
| IPDS-16-127 | 4017..00 Ribbon Fault | ❓ |
| IPDS-16-128 | 4006..00 • 4017..00 | ❓ |
| IPDS-16-129 | 4020..00 Incorrect Form Module selection | ❓ |
| IPDS-16-130 | 4031..00 Paper-Length Check | ❓ |
| IPDS-16-131 | 4031..00 Paper-Length Check | ❓ |
| IPDS-16-132 | 4033..00 Paper-Width Check | ❓ |
| IPDS-16-133 | 4033..00 Paper-Width Check | ❓ |
| IPDS-16-134 | 4035..00 Printer-detected FORMs mismatch | ❓ |
| IPDS-16-135 | 4040..00 Printer emitted blank sheets in the middle | ❓ |
| IPDS-16-136 | 4040..00 Printer emitted blank sheets in the middle | ❓ |
| IPDS-16-137 | 4020..00 • 4040..00 | ❓ |
| IPDS-16-138 | 4050..00 Fuser oil supply empty | ❓ |
| IPDS-16-139 | 4050..00 Fuser oil supply empty | ❓ |
| IPDS-16-140 | 4051..00 Developer mix needs changing | ❓ |
| IPDS-16-141 | 4051..00 Developer mix needs changing | ❓ |
| IPDS-16-142 | 4052..00 Oiler felt needs changing | ❓ |
| IPDS-16-143 | 4052..00 Oiler felt needs changing | ❓ |
| IPDS-16-144 | 4053..00 Toner collector full | ❓ |
| IPDS-16-145 | 4053..00 Toner collector full | ❓ |
| IPDS-16-146 | 4054..00 Fine filter needs changing | ❓ |
| IPDS-16-147 | 4054..00 Fine filter needs changing | ❓ |
| IPDS-16-148 | 4050..00 • 407C..00 | ❓ |
| IPDS-16-149 | 1. Some printers report this error as exception ID | ❓ |
| IPDS-16-150 | 2. For some printers, reporting of this exception is | ❓ |
| IPDS-16-151 | 1. Some printers report this error as exception ID | ❓ |
| IPDS-16-152 | 2. For some printers, reporting of this exception is | ❓ |
| IPDS-16-153 | A permanent hardware failure exists. | ❓ |
| IPDS-16-154 | The printer has detected either a logic exception from | ❓ |
| IPDS-16-155 | 0821..00 Undefined character | ❓ |
| IPDS-16-156 | An invalid or undefined character has been detected in | ❓ |
| IPDS-16-157 | An undefined character has been detected in the font | ❓ |
| IPDS-16-158 | A character has been detected in Write Text command | ❓ |
| IPDS-16-159 | 1. Reporting of this exception for an undefined character | ❓ |
| IPDS-16-160 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-161 | 3. Undefined characters in GOCA character string data | ❓ |
| IPDS-16-162 | 4. Sense bytes 16–17 contain the undefined character's | ❓ |
| IPDS-16-163 | 0821..00 Asynchronous undefined character | ❓ |
| IPDS-16-164 | An invalid or undefined character has been detected in | ❓ |
| IPDS-16-165 | An undefined character has been detected in the font | ❓ |
| IPDS-16-166 | A character has been detected in Write Text command | ❓ |
| IPDS-16-167 | 0821..00 • 0821..00 | ❓ |
| IPDS-16-168 | 1. Reporting of this exception for an undefined character | ❓ |
| IPDS-16-169 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-170 | 3. Undefined characters in GOCA character string data | ❓ |
| IPDS-16-171 | 4. Sense bytes 16–17 contain the undefined character's | ❓ |
| IPDS-16-172 | 0829..00 Double-byte coded font section is not | ❓ |
| IPDS-16-173 | The double-byte coded font section specified in a code | ❓ |
| IPDS-16-174 | The double-byte coded font section ID specified in a | ❓ |
| IPDS-16-175 | 1. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-176 | 2. Sense bytes 16–17 should contain the code point that | ❓ |
| IPDS-16-177 | 0860..00 Numeric representation precision check | ❓ |
| IPDS-16-178 | 1. Some printers report this exception as X'0215..01' or | ❓ |
| IPDS-16-179 | 2. Some printers report this exception when an invalid | ❓ |
| IPDS-16-180 | 1. When the data to be printed outside of the VPA is | ❓ |
| IPDS-16-181 | 0829..00 • 08C1..00 | ❓ |
| IPDS-16-182 | 2. Some printers report this exception as X'0411..00' for | ❓ |
| IPDS-16-183 | 3. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-184 | 4. This corresponds to an exception code defined by | ❓ |
| IPDS-16-185 | 1. When the data to be printed outside of the VPA is | ❓ |
| IPDS-16-186 | 2. Some printers report this exception as X'0411..00' for | ❓ |
| IPDS-16-187 | 3. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-188 | 4. This corresponds to an exception code defined by | ❓ |
| IPDS-16-189 | A portion of a saved page included with an Include | ❓ |
| IPDS-16-190 | A portion of an overlay saved with a page and included | ❓ |
| IPDS-16-191 | A user-printable area has been specified and a portion of | ❓ |
| IPDS-16-192 | 1. The page ID from the Begin Page command of the | ❓ |
| IPDS-16-193 | 2. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-194 | A portion of a page to be saved extends outside of the | ❓ |
| IPDS-16-195 | A portion of an overlay to be saved with a page extends | ❓ |
| IPDS-16-196 | 1. The page sequence number associated with the saved | ❓ |
| IPDS-16-197 | 2. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-198 | 0601..00 Invalid metadata length | ❓ |
| IPDS-16-199 | 0602..00 Invalid metadata header length | ❓ |
| IPDS-16-200 | 0602..10 Invalid metadata name | ❓ |
| IPDS-16-201 | 0602..20 Invalid or unsupported metadata type | ❓ |
| IPDS-16-202 | 0602..30 Invalid or unsupported metadata format | ❓ |
| IPDS-16-203 | 0602..40 Invalid or unsupported metadata | ❓ |
| IPDS-16-204 | 0602..50 Invalid metadata name length | ❓ |
| IPDS-16-205 | 0603..00 Invalid metadata | ❓ |
| IPDS-16-206 | 1. The metadata is not of the specified type. | ❓ |
| IPDS-16-207 | 2. The metadata is not of the specified format. | ❓ |
| IPDS-16-208 | 3. The metadata is not compressed using the specified | ❓ |
| IPDS-16-209 | 4. The metadata is invalid, unsupported, or missing. This | ❓ |
| IPDS-16-210 | 0601..00 • 0603..00 | ❓ |
| IPDS-16-211 | 0603..00 • 0603..00 | ❓ |
| IPDS-16-212 | 0500..01 Invalid or unsupported IO-Image self- | ❓ |
| IPDS-16-213 | 0500..03 Invalid or unsupported IO-Image self- | ❓ |
| IPDS-16-214 | 0500..04 Invalid IO-Image self-defining field value | ❓ |
| IPDS-16-215 | 1. When an appropriate X'05xx..10' exception is available | ❓ |
| IPDS-16-216 | 2. Sense bytes 16–17 contain the IOCA self-defining field | ❓ |
| IPDS-16-217 | 0500..01 • 0570..0F | ❓ |
| IPDS-16-218 | 0592..01 IO-Image Image Data self-defining field | ❓ |
| IPDS-16-219 | 0594..01 Inconsistent Image Size Parameter value | ❓ |
| IPDS-16-220 | 0594..10 IO-Image Image Size Parameter value | ❓ |
| IPDS-16-221 | 0594..11 IO-Image Image Size cannot be determined | ❓ |
| IPDS-16-222 | 1. The horizontal size (bytes 7,8) or vertical size (bytes | ❓ |
| IPDS-16-223 | 2. An Image Size Parameter within a transparency mask | ❓ |
| IPDS-16-224 | 0595..10 IO-Image Image Encoding Parameter value | ❓ |
| IPDS-16-225 | 0595..11 IO-Image decompression error | ❓ |
| IPDS-16-226 | 1. The image data was not encoded according to the | ❓ |
| IPDS-16-227 | 2. The image data could not be decoded successfully | ❓ |
| IPDS-16-228 | 0594..01 • 0595..11 | ❓ |
| IPDS-16-229 | 3. The image data was not in complete accordance with | ❓ |
| IPDS-16-230 | 4. The image data is encoded using the algorithm | ❓ |
| IPDS-16-231 | 0596..10 IO-Image Image Data Element Size | ❓ |
| IPDS-16-232 | 0596..11 IO-Image Image Data Element Size | ❓ |
| IPDS-16-233 | 0597..10 IO-Image Image Look Up Table ID | ❓ |
| IPDS-16-234 | 0598..01 Inconsistent Band Image Parameter and | ❓ |
| IPDS-16-235 | 0598..10 IO-Image Band Image Parameter value | ❓ |
| IPDS-16-236 | 0598..14 IO-Image Band Image Parameter values | ❓ |
| IPDS-16-237 | 0598..15 IO-Image Band Image Parameter | ❓ |
| IPDS-16-238 | 1. The value of ASFLAG is invalid or unsupported. | ❓ |
| IPDS-16-239 | 2. The value of GRAYCODE is invalid or unsupported. | ❓ |
| IPDS-16-240 | 3. The value of FORMAT is invalid or unsupported. | ❓ |
| IPDS-16-241 | 4. The value of a SIZE field is invalid or unsupported. | ❓ |
| IPDS-16-242 | 1. The sum of the SIZE | ❓ |
| IPDS-16-243 | 2. The color space is CMYK and SIZE4 is missing. | ❓ |
| IPDS-16-244 | 3. SIZE4 is present and the color space is not CMYK or | ❓ |
| IPDS-16-245 | 4. More than four SIZE parameters are present and the | ❓ |
| IPDS-16-246 | 5. The color space is nColor and the number of SIZE | ❓ |
| IPDS-16-247 | 0598..10 • 059C..01 | ❓ |
| IPDS-16-248 | 1. An External Algorithm Specification Parameter is | ❓ |
| IPDS-16-249 | 2. An Image Encoding Parameter is specified in an IO | ❓ |
| IPDS-16-250 | 1. A NAMELEN value is an odd value, or is greater than | ❓ |
| IPDS-16-251 | 2. A color name is not a valid UTF-16BE character string. | ❓ |
| IPDS-16-252 | 3. A color name appears more than once. | ❓ |
| IPDS-16-253 | 1. The nColor Names Parameter should not be present | ❓ |
| IPDS-16-254 | 2. The number of color names specified in the nColor | ❓ |
| IPDS-16-255 | 1. Tiles are specified out of order. This exception can | ❓ |
| IPDS-16-256 | 2. The Tile TOC Parameter does contain the table of | ❓ |
| IPDS-16-257 | 1. The tile overlaps a previously specified tile. | ❓ |
| IPDS-16-258 | 2. The RELRES value specified in the table of contents | ❓ |
| IPDS-16-259 | 3. The THSIZE or TVSIZE specified in the table of | ❓ |
| IPDS-16-260 | 1. An invalid color space (CSPACE) value is specified. | ❓ |
| IPDS-16-261 | 2. An invalid size value is specified. | ❓ |
| IPDS-16-262 | 3. An invalid color value is specified. | ❓ |
| IPDS-16-263 | 1. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-264 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-265 | 1. Not all tiles are listed in the table of contents, even | ❓ |
| IPDS-16-266 | 2. The table of contents lists a non-existent tile. | ❓ |
| IPDS-16-267 | 3. Invalid tile order; two or more tiles in the table of | ❓ |
| IPDS-16-268 | 4. The specified offset for one or more tiles does not point | ❓ |
| IPDS-16-269 | 1. An invalid color space value was specified. | ❓ |
| IPDS-16-270 | 2. An invalid size value was specified. | ❓ |
| IPDS-16-271 | 3. An invalid color value was specified. | ❓ |
| IPDS-16-272 | 1. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-273 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-274 | 0403..00 Invalid or unsupported bar code type | ❓ |
| IPDS-16-275 | 0404..00 Unsupported font local ID or font not | ❓ |
| IPDS-16-276 | 1. A font local ID specified in the bar code data descriptor | ❓ |
| IPDS-16-277 | 2. A font local ID specified in the bar code data descriptor | ❓ |
| IPDS-16-278 | 3. A font local ID specified in the bar code data descriptor | ❓ |
| IPDS-16-279 | 4. For those symbologies that require a specific type | ❓ |
| IPDS-16-280 | 1. Some printers report this exception as X'0218..02'. | ❓ |
| IPDS-16-281 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-282 | 0405..00 Invalid or unsupported bar code color | ❓ |
| IPDS-16-283 | 0406..00 Invalid or unsupported module width | ❓ |
| IPDS-16-284 | 0406..10 Invalid desired-symbol-width parameter | ❓ |
| IPDS-16-285 | 0403..00 • 0406..10 | ❓ |
| IPDS-16-286 | 0406..11 Bar code symbol cannot fit within the | ❓ |
| IPDS-16-287 | 0407..00 Invalid or unsupported element height | ❓ |
| IPDS-16-288 | 0408..00 Invalid height multiplier | ❓ |
| IPDS-16-289 | 0408..05 Invalid height values for GS1 DataBar bar | ❓ |
| IPDS-16-290 | 0409..00 Invalid or unsupported wide-to-narrow | ❓ |
| IPDS-16-291 | 1. The Xoffset value is invalid or unsupported. | ❓ |
| IPDS-16-292 | 2. The Yoffset value is invalid or unsupported. | ❓ |
| IPDS-16-293 | 0406..11 • 040B..00 | ❓ |
| IPDS-16-294 | 1. An invalid desired number of rows value is specified. | ❓ |
| IPDS-16-295 | 2. The number of rows times the number of data symbol | ❓ |
| IPDS-16-296 | 8 was specified. | ❓ |
| IPDS-16-297 | 1. A structured append is specified, but either the reader | ❓ |
| IPDS-16-298 | 2. The GS1 FNC1 flag is set to B'1', but either the | ❓ |
| IPDS-16-299 | 3. The industry FNC1 flag is set to B'1', but either the | ❓ |
| IPDS-16-300 | 4. The reader programming flag is set to B'1', but either a | ❓ |
| IPDS-16-301 | 5. A hdr/trl macro is specified, but either a structured | ❓ |
| IPDS-16-302 | 040F ..05 • 040F ..0B | ❓ |
| IPDS-16-303 | 1. An invalid Macro PDF417 Control Block length value is | ❓ |
| IPDS-16-304 | 2. The length of the Macro PDF417 Control Block is too | ❓ |
| IPDS-16-305 | 040F ..0C • 040F ..12 | ❓ |
| IPDS-16-306 | 1. The data was invalid. | ❓ |
| IPDS-16-307 | 2. The data resulted in a USPS Service Banner that was | ❓ |
| IPDS-16-308 | 040F ..13 • 040F ..1A | ❓ |
| IPDS-16-309 | 1. The length is invalid. | ❓ |
| IPDS-16-310 | 2. The length is too large to fit into the repeating groups | ❓ |
| IPDS-16-311 | 040F ..1B • 040F ..32 | ❓ |
| IPDS-16-312 | 1. The X offset value for the image-object-area origin is | ❓ |
| IPDS-16-313 | 2. The Y offset value for the image-object-area origin is | ❓ |
| IPDS-16-314 | 1. The X extent value for the image object area is invalid | ❓ |
| IPDS-16-315 | 2. The Y extent value for the image object area is invalid | ❓ |
| IPDS-16-316 | 040F ..33 • 040F ..39 | ❓ |
| IPDS-16-317 | 0410..00 Invalid or unsupported human-readable | ❓ |
| IPDS-16-318 | 0411..00 Attempt to print portion of bar code | ❓ |
| IPDS-16-319 | A portion of the bar code symbol or HRI extends beyond | ❓ |
| IPDS-16-320 | A portion of a bar code symbol or HRI extends outside | ❓ |
| IPDS-16-321 | A portion of a bar code symbol or HRI extends beyond | ❓ |
| IPDS-16-322 | For printers that cannot detect bar code symbol position | ❓ |
| IPDS-16-323 | 1. When the data to be printed outside of the VPA is | ❓ |
| IPDS-16-324 | 2. Some printers report this as exception ID X'020A..05', | ❓ |
| IPDS-16-325 | 3. Since this exception ID does not have an AEA, | ❓ |
| IPDS-16-326 | 0411..00 Asynchronous attempt to print portion of | ❓ |
| IPDS-16-327 | A portion of the bar code symbol or HRI extends beyond | ❓ |
| IPDS-16-328 | A portion of a bar code symbol or HRI extends outside | ❓ |
| IPDS-16-329 | A portion of a bar code symbol or HRI extends beyond | ❓ |
| IPDS-16-330 | For printers that cannot detect bar code symbol position | ❓ |
| IPDS-16-331 | 1. When the data to be printed outside of the VPA is | ❓ |
| IPDS-16-332 | 2. Some printers report this as exception ID X'020A..05', | ❓ |
| IPDS-16-333 | 3. Since this exception ID does not have an AEA, | ❓ |
| IPDS-16-334 | 0412..00 Invalid data in a GS1 DataBar Expanded, | ❓ |
| IPDS-16-335 | FNC1 is not the first data character (for UCC/EAN 128 | ❓ |
| IPDS-16-336 | Invalid application identifier (ai) value encountered | ❓ |
| IPDS-16-337 | Data for an ai doesn't match the ai definition | ❓ |
| IPDS-16-338 | Insufficient (or no) data following an ai | ❓ |
| IPDS-16-339 | Too much data for an ai | ❓ |
| IPDS-16-340 | Invalid use of FNC1 character | ❓ |
| IPDS-16-341 | 0412..01 Invalid data for a Data Matrix encodation | ❓ |
| IPDS-16-342 | 0412..02 Invalid or insufficient data for a RED TAG | ❓ |
| IPDS-16-343 | 0412..03 Invalid or insufficient data for an Intelligent | ❓ |
| IPDS-16-344 | 1. Too many or too few characters were specified; there | ❓ |
| IPDS-16-345 | 2. An invalid character was encountered in the data; | ❓ |
| IPDS-16-346 | 3. Data was encountered that would cause subset A to | ❓ |
| IPDS-16-347 | 0412..04 Invalid, insufficient, or too much data was | ❓ |
| IPDS-16-348 | 0412..00 • 0412..04 | ❓ |
| IPDS-16-349 | 0412..05 Invalid or insufficient data for an Intelligent | ❓ |
| IPDS-16-350 | 1. Too many or too few characters were specified; there | ❓ |
| IPDS-16-351 | 2. An invalid character was encountered in the data. | ❓ |
| IPDS-16-352 | 3. Data was encountered that would cause subset A or B | ❓ |
| IPDS-16-353 | 0412..05 • 0412..05 | ❓ |
| IPDS-16-354 | 0300..01 Unallocated or unsupported graphics | ❓ |
| IPDS-16-355 | 1. An attempt is made to process an unallocated or | ❓ |
| IPDS-16-356 | 2. In a Write Graphics command, a Begin Segment | ❓ |
| IPDS-16-357 | 3. A Self-Describing Instruction identifier in a Graphics | ❓ |
| IPDS-16-358 | 0300..02 Reserved field exception or invalid | ❓ |
| IPDS-16-359 | 1. Retired (a reserved bit or byte in a graphics drawing | ❓ |
| IPDS-16-360 | 2. The Set Current Defaults instruction in the data- | ❓ |
| IPDS-16-361 | 3. The Set Current Defaults instruction has a length of | ❓ |
| IPDS-16-362 | 4. The Set Current Defaults instruction has a length | ❓ |
| IPDS-16-363 | 5. The Set Current Defaults instruction attempts to set an | ❓ |
| IPDS-16-364 | 1. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-365 | 2. Bullet 1 in the explanation corresponds to AFP GOCA | ❓ |
| IPDS-16-366 | 3. For bullets 3 and 4 in the explanation, if the problem is | ❓ |
| IPDS-16-367 | 4. Older AFP GOCA implementations might issue this | ❓ |
| IPDS-16-368 | 0300..03 Incorrect drawing order length | ❓ |
| IPDS-16-369 | 1. The flags byte (byte 5) of the Set Current Defaults | ❓ |
| IPDS-16-370 | 2. The segment length (bytes 8, 9) in a Begin Segment | ❓ |
| IPDS-16-371 | 3. An invalid length is specified in a drawing order. | ❓ |
| IPDS-16-372 | 4. The flags byte (byte 5) of the Set Current Defaults | ❓ |
| IPDS-16-373 | 5. The flags byte (byte 5) of the Set Current Defaults | ❓ |
| IPDS-16-374 | 0300..01 • 0300..03 | ❓ |
| IPDS-16-375 | 0300..04 Invalid attribute value | ❓ |
| IPDS-16-376 | 0300..08 Truncated order exception | ❓ |
| IPDS-16-377 | 1. A fixed two-byte order, and the second byte is not in | ❓ |
| IPDS-16-378 | 2. A long format order, and the length byte is not in the | ❓ |
| IPDS-16-379 | 3. A long or extended format order, and the number of | ❓ |
| IPDS-16-380 | 4. An extended format order, and the qualifier byte is not | ❓ |
| IPDS-16-381 | 5. An extended format order, and one or both of the | ❓ |
| IPDS-16-382 | 1. A supported order has been encountered that is not | ❓ |
| IPDS-16-383 | 2. The end of a segment has been reached without an | ❓ |
| IPDS-16-384 | 0300..04 • 0300..0E | ❓ |
| IPDS-16-385 | 1. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-386 | 2. The printer identifies specific support for OCA color | ❓ |
| IPDS-16-387 | 0300..21 Invalid or unsupported default | ❓ |
| IPDS-16-388 | 0304..00 Invalid Segment Characteristics drawing | ❓ |
| IPDS-16-389 | 0334..00 Character angle value not supported | ❓ |
| IPDS-16-390 | 0360..00 Area bracket exception | ❓ |
| IPDS-16-391 | 0368..00 Begin Area received incorrectly | ❓ |
| IPDS-16-392 | 0368..01 Area truncated exception | ❓ |
| IPDS-16-393 | 0300..21 • 0368..01 | ❓ |
| IPDS-16-394 | 0368..02 Supported order invalid in area | ❓ |
| IPDS-16-395 | 0368..03 Pattern Set not supported | ❓ |
| IPDS-16-396 | 0368..04 Undefined pattern symbol | ❓ |
| IPDS-16-397 | 0368..05 Temporary-storage overflow while drawing | ❓ |
| IPDS-16-398 | 0368..06 Invalid pattern use inside custom pattern | ❓ |
| IPDS-16-399 | 0370..01 Unsupported Begin Segment Introducer | ❓ |
| IPDS-16-400 | 0370..82 Invalid Begin Segment Introducer segment | ❓ |
| IPDS-16-401 | 0368..02 • 0370..C5 | ❓ |
| IPDS-16-402 | 0392..00 Graphics image order sequence exception | ❓ |
| IPDS-16-403 | 0392..01 Image data discrepancy | ❓ |
| IPDS-16-404 | 0393..00 Graphics image bracket exception | ❓ |
| IPDS-16-405 | 0393..01 Incorrect number of Image Data drawing | ❓ |
| IPDS-16-406 | 0392..00 • 03C2..02 | ❓ |
| IPDS-16-407 | 1. A Character String drawing order is encountered, but | ❓ |
| IPDS-16-408 | 2. A character set specified either in a Set Character Set | ❓ |
| IPDS-16-409 | 3. A character set specified either in a Set Character Set | ❓ |
| IPDS-16-410 | A high-order surrogate code value is not immediately | ❓ |
| IPDS-16-411 | A low-order surrogate code value is not immediately | ❓ |
| IPDS-16-412 | An illegal UTF-8 byte sequence, as defined in the | ❓ |
| IPDS-16-413 | 1. The color space specified is the Standard OCA color | ❓ |
| IPDS-16-414 | 2. The color space specified is the Highlight color space, | ❓ |
| IPDS-16-415 | 1. The color space specified is the Standard OCA color | ❓ |
| IPDS-16-416 | 2. The color space specified is the Highlight color space, | ❓ |
| IPDS-16-417 | 1. The X position of the left side of the pattern window is | ❓ |
| IPDS-16-418 | 2. The Y position of the bottom side of the pattern window | ❓ |
| IPDS-16-419 | X'020A..05' uses either format 1 or format 7 | ❓ |
| IPDS-16-420 | X'027E..00' uses format 8 | ❓ |
| IPDS-16-421 | 0200..01 Text control-sequence code exception | ❓ |
| IPDS-16-422 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-423 | 2. For printers that support text fidelity control, reporting | ❓ |
| IPDS-16-424 | 3. Sense bytes 16–17 contain the unsupported or | ❓ |
| IPDS-16-425 | 0201..03 Text data extends outside of the text | ❓ |
| IPDS-16-426 | 0202..01 End Suppression (ESU) control-sequence | ❓ |
| IPDS-16-427 | 1. The active Begin Suppression (BSU) ID within the | ❓ |
| IPDS-16-428 | 2. There is no active suppression ID when an ESU | ❓ |
| IPDS-16-429 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-430 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-431 | 0202..02 Invalid or unsupported IPDS command | ❓ |
| IPDS-16-432 | 0200..01 • 0202..02 | ❓ |
| IPDS-16-433 | 1. The length value of a command is less than X'05' (or | ❓ |
| IPDS-16-434 | 2. The length of a command is greater than X'7FFF'. | ❓ |
| IPDS-16-435 | 3. The command length is not valid or is unsupported for | ❓ |
| IPDS-16-436 | 4. The length of the data within an IDO, WBCC, WGC, | ❓ |
| IPDS-16-437 | 1. Some printers report this exception as X'0203..02' or | ❓ |
| IPDS-16-438 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-439 | 0202..05 Invalid self-defining-field length | ❓ |
| IPDS-16-440 | 0203..02 IPDS command header length too small | ❓ |
| IPDS-16-441 | 0203..05 Invalid or unsupported object area | ❓ |
| IPDS-16-442 | 0204..01 EP or END command encountered before | ❓ |
| IPDS-16-443 | 0204..02 Invalid use of Acknowledgment- | ❓ |
| IPDS-16-444 | 1. The Acknowledgment-Continuation bit in the flag byte | ❓ |
| IPDS-16-445 | 2. The Acknowledgment-Continuation bit in the flag byte | ❓ |
| IPDS-16-446 | 0204..05 Invalid or unsupported value for area- | ❓ |
| IPDS-16-447 | 0205..01 Invalid spanning sequence | ❓ |
| IPDS-16-448 | 0202..05 • 0205..01 | ❓ |
| IPDS-16-449 | 1. A WT command is required to complete a partial | ❓ |
| IPDS-16-450 | 2. A WI2 command is required to complete a partial IO- | ❓ |
| IPDS-16-451 | 3. A WG command is required to complete a partial | ❓ |
| IPDS-16-452 | 4. A WOC command is required to complete the data for | ❓ |
| IPDS-16-453 | 5. A WM command is required to complete the data for a | ❓ |
| IPDS-16-454 | 0205..02 Invalid setting of the LPD ordered page | ❓ |
| IPDS-16-455 | 0205..05 Invalid or unsupported self-defining-field | ❓ |
| IPDS-16-456 | 0206..01 Invalid Begin Suppression (BSU) | ❓ |
| IPDS-16-457 | 0206..05 Invalid or unsupported units per unit base | ❓ |
| IPDS-16-458 | 1. The units per unit base value specified in either the | ❓ |
| IPDS-16-459 | 2. The units per unit base specified for the Y coordinate | ❓ |
| IPDS-16-460 | 3. In a WGC-GDD self-defining field, an invalid value is | ❓ |
| IPDS-16-461 | 1. Some printers report this exception as X'0860..00'. | ❓ |
| IPDS-16-462 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-463 | 3. For the data-descriptor self-defining field, this | ❓ |
| IPDS-16-464 | 0207..05 Invalid or unsupported self-defining-field | ❓ |
| IPDS-16-465 | 0205..02 • 0207..05 | ❓ |
| IPDS-16-466 | 1. The extents (Xg and Yg limits for WGC-GDD) specified | ❓ |
| IPDS-16-467 | 2. The graphics presentation space window specified in | ❓ |
| IPDS-16-468 | 1. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-469 | 2. For the data-descriptor self-defining field, this | ❓ |
| IPDS-16-470 | 0208..05 Invalid or unsupported mapping option | ❓ |
| IPDS-16-471 | 0209..05 Unsupported axis offsets | ❓ |
| IPDS-16-472 | A portion of the graphics presentation space window, the | ❓ |
| IPDS-16-473 | A portion of the graphics, IO-Image, or bar code object | ❓ |
| IPDS-16-474 | 1. This exception is used by printers that cannot detect | ❓ |
| IPDS-16-475 | 2. This exception ID has been deprecated and should not | ❓ |
| IPDS-16-476 | 3. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-477 | A portion of the graphics presentation space window, the | ❓ |
| IPDS-16-478 | A portion of the graphics, IO-Image, or bar code object | ❓ |
| IPDS-16-479 | 0208..05 • 020A..05 | ❓ |
| IPDS-16-480 | 1. This exception is used by printers that cannot detect | ❓ |
| IPDS-16-481 | 2. This exception ID has been deprecated and should not | ❓ |
| IPDS-16-482 | 3. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-483 | 1. A two-byte self-defining-field identifier in an IDO, | ❓ |
| IPDS-16-484 | 2. A required self-defining-field identifier in an IDO, | ❓ |
| IPDS-16-485 | 3. One of the self-defining fields appears more than once | ❓ |
| IPDS-16-486 | 1. Sense bytes 16–17 can contain an object-specific | ❓ |
| IPDS-16-487 | 2. This exception ID is used when a TrueType/OpenType | ❓ |
| IPDS-16-488 | 3. This exception ID is used when a non-presentation | ❓ |
| IPDS-16-489 | 4. This exception ID might indicate that the version of the | ❓ |
| IPDS-16-490 | 1. The registered object-type OID specified in the WOCC | ❓ |
| IPDS-16-491 | 2. The registered object-type OID specified in the WOCC | ❓ |
| IPDS-16-492 | 1. Sense bytes 16–17 can contain an object-specific | ❓ |
| IPDS-16-493 | 2. This exception ID is used when a presentation object | ❓ |
| IPDS-16-494 | 3. This exception ID might indicate that the version of the | ❓ |
| IPDS-16-495 | 1. The extender entry is the first entry in the command. | ❓ |
| IPDS-16-496 | 2. The extender entry immediately follows a HAID-only | ❓ |
| IPDS-16-497 | 1. A presentation data object references a data object | ❓ |
| IPDS-16-498 | 2. A presentation data object references a data object | ❓ |
| IPDS-16-499 | 3. The resource identified by a DORE or DORE2 | ❓ |
| IPDS-16-500 | 4. An Invoke Tertiary Resource (X'A2') triplet references | ❓ |
| IPDS-16-501 | 5. An Invoke Tertiary Resource (X'A2') triplet references | ❓ |
| IPDS-16-502 | 1. The Host-Assigned ID value specified in a DDOFC, | ❓ |
| IPDS-16-503 | 2. A Host-Assigned ID value of X'0000' is specified in an | ❓ |
| IPDS-16-504 | 1. The invalid HAID value is specified in sense bytes 14– | ❓ |
| IPDS-16-505 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-506 | 1. An invalid equivalence entry length value is specified | ❓ |
| IPDS-16-507 | 2. A DORE or DORE2 equivalence entry is too long to fit | ❓ |
| IPDS-16-508 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-16-509 | EPS without transparency | ❓ |
| IPDS-16-510 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-16-511 | IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-16-512 | JPEG (Joint Photographic Experts Group) AFPC JPEG | ❓ |
| IPDS-16-513 | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-16-514 | PCL (Printer Command Language) page object | ❓ |
| IPDS-16-515 | PDF (Portable Document Format) multiple-page file with | ❓ |
| IPDS-16-516 | PDF multiple-page file without transparency | ❓ |
| IPDS-16-517 | PDF single page with transparency | ❓ |
| IPDS-16-518 | PDF single page without transparency | ❓ |
| IPDS-16-519 | PNG (Portable Network Graphics) AFPC PNG Subset | ❓ |
| IPDS-16-520 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-16-521 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-16-522 | TIFF with transparency | ❓ |
| IPDS-16-523 | TIFF without transparency | ❓ |
| IPDS-16-524 | TIFF multiple-image file with transparency | ❓ |
| IPDS-16-525 | TIFF multiple-image file without transparency | ❓ |
| IPDS-16-526 | 1. The Host-Assigned ID value specified in an ICMR | ❓ |
| IPDS-16-527 | 2. The Host-Assigned ID value specified in an ICMR | ❓ |
| IPDS-16-528 | 1. The HAID value is specified in sense bytes 14–15. | ❓ |
| IPDS-16-529 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-530 | 1. The triplet-length field in a X'4E' or X'70' triplet | ❓ |
| IPDS-16-531 | 2. A X'4E' or X'70' triplet is too long to fit in its containing | ❓ |
| IPDS-16-532 | 1. The color space field in a Color Specification (X'4E') | ❓ |
| IPDS-16-533 | 2. The color space field in a PTOCA Set Extended Text | ❓ |
| IPDS-16-534 | 3. The color space field in a GOCA Set Process Color | ❓ |
| IPDS-16-535 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-536 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-537 | 1. The color value field in a Color Specification (X'4E') | ❓ |
| IPDS-16-538 | 2. The color value field in a PTOCA Set Extended Text | ❓ |
| IPDS-16-539 | 3. The color value field in a GOCA Set Process Color | ❓ |
| IPDS-16-540 | 4. A specified highlight color number is in the range | ❓ |
| IPDS-16-541 | 5. An indexed CMR is selected for use with a highlight | ❓ |
| IPDS-16-542 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-543 | 2. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-544 | 3. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-545 | 1. Either the coverage field, the shading field, or both in a | ❓ |
| IPDS-16-546 | 2. Either the coverage field, the shading field, or both in a | ❓ |
| IPDS-16-547 | 3. Either the coverage field, the shading field, or both in a | ❓ |
| IPDS-16-548 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-549 | 2. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-550 | 3. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-551 | 1. An invalid or unsupported value is specified in the | ❓ |
| IPDS-16-552 | 2. The Colsize fields in a Color Specification (X'4E') triplet | ❓ |
| IPDS-16-553 | 3. An invalid or unsupported value is specified in the | ❓ |
| IPDS-16-554 | 4. The Colsize fields in a PTOCA Set Extended Text | ❓ |
| IPDS-16-555 | 5. An invalid or unsupported value is specified in the | ❓ |
| IPDS-16-556 | 6. The Colsize fields in a GOCA Set Process Color | ❓ |
| IPDS-16-557 | 7. The Colsize fields in a GOCA Linear Gradient (GLGD) | ❓ |
| IPDS-16-558 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-559 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-560 | 020F ..01 Invalid or unsupported Set Text | ❓ |
| IPDS-16-561 | 1. The inline or baseline orientation specified in a text | ❓ |
| IPDS-16-562 | 2. The combination of the baseline and inline orientations | ❓ |
| IPDS-16-563 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-564 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-565 | 0210..01 Invalid or unsupported Set Inline Margin | ❓ |
| IPDS-16-566 | 0211..01 Invalid or unsupported Set Baseline | ❓ |
| IPDS-16-567 | 0212..01 Invalid or unsupported intercharacter | ❓ |
| IPDS-16-568 | 0212..02 Font storage is full | ❓ |
| IPDS-16-569 | 1. Either pattern storage or auxiliary storage is | ❓ |
| IPDS-16-570 | 2. Insufficient storage exists to load the data transmitted | ❓ |
| IPDS-16-571 | 3. Insufficient storage exists to activate the font specified | ❓ |
| IPDS-16-572 | 1. Some printers report this exception as X'023A..02' or | ❓ |
| IPDS-16-573 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-574 | 0213..01 Invalid or unsupported Absolute Move | ❓ |
| IPDS-16-575 | 0214..01 Invalid or unsupported Absolute Move | ❓ |
| IPDS-16-576 | 0210..01 • 0214..01 | ❓ |
| IPDS-16-577 | 0214..02 The font resource to be deactivated was | ❓ |
| IPDS-16-578 | 0214..03 Unsupported baseline move | ❓ |
| IPDS-16-579 | 1. Some printers report this exception as X'0205..02'. | ❓ |
| IPDS-16-580 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-581 | 0215..01 Invalid or unsupported Relative Move | ❓ |
| IPDS-16-582 | 1. Some printers report this exception as X'0860..00'. | ❓ |
| IPDS-16-583 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-584 | 0215..02 Invalid or unsupported DF command font | ❓ |
| IPDS-16-585 | 1. The font Host-Assigned ID field is required in the DF | ❓ |
| IPDS-16-586 | 2. The double-byte coded font-section-ID field is required | ❓ |
| IPDS-16-587 | 3. The font-inline-sequence field is required; but is not | ❓ |
| IPDS-16-588 | 0216..01 Invalid or unsupported Relative Move | ❓ |
| IPDS-16-589 | 1. Some printers report this exception as X'0860..00'. | ❓ |
| IPDS-16-590 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-591 | 0217..01 Invalid or unsupported Set Variable-Space | ❓ |
| IPDS-16-592 | 0214..02 • 0217..01 | ❓ |
| IPDS-16-593 | 0217..02 Invalid or unsupported value for DF | ❓ |
| IPDS-16-594 | 1. The deactivation type in a DF command is invalid or | ❓ |
| IPDS-16-595 | 2. The font Host-Assigned ID value identifies a double- | ❓ |
| IPDS-16-596 | 3. The font Host-Assigned ID value identifies a single- | ❓ |
| IPDS-16-597 | 0218..02 Invalid, unsupported, or unavailable font | ❓ |
| IPDS-16-598 | 1. The font Host-Assigned ID in an LSS, LFC, LFI, or LFE | ❓ |
| IPDS-16-599 | 2. The font Host-Assigned ID in an LFI command does | ❓ |
| IPDS-16-600 | 3. The font local ID in an LFE command is invalid or | ❓ |
| IPDS-16-601 | 4. A font is referenced in a Set Coded-Font Local (SCFL) | ❓ |
| IPDS-16-602 | 5. The font referenced in an LPD, WBCC, WT , or WTC | ❓ |
| IPDS-16-603 | 1. Some printers report this exception as X'0404..00' | ❓ |
| IPDS-16-604 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-605 | 3. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-606 | 0219..01 Invalid or unsupported value for Repeat | ❓ |
| IPDS-16-607 | 0219..02 Multiple occurrences of the same LFE font | ❓ |
| IPDS-16-608 | 0217..02 • 021A..01 | ❓ |
| IPDS-16-609 | A high-order surrogate code value is not immediately | ❓ |
| IPDS-16-610 | A low-order surrogate code value is not immediately | ❓ |
| IPDS-16-611 | An illegal UTF-8 byte sequence, as defined in the | ❓ |
| IPDS-16-612 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-613 | 2. Sense bytes 16–17 contain the Unicode code value in | ❓ |
| IPDS-16-614 | 1. The combination of parameters specified in the LFE | ❓ |
| IPDS-16-615 | 2. The FGID value specified in the LFE command is | ❓ |
| IPDS-16-616 | 0220..01 Double-byte MICR font section mismatch | ❓ |
| IPDS-16-617 | 0220..02 Invalid LFC reserved byte | ❓ |
| IPDS-16-618 | 0221..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-619 | 0222..02 Invalid or unsupported Load Font Control | ❓ |
| IPDS-16-620 | 0223..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-621 | 0226..02 Invalid or unsupported LSS or LFC X-box | ❓ |
| IPDS-16-622 | 1. The character X-box size (specified in byte 6 in an LSS | ❓ |
| IPDS-16-623 | 2. The character X-box size (specified in bytes 6 and 7 in | ❓ |
| IPDS-16-624 | 0227..02 Invalid or unsupported LSS or LFC Y-box | ❓ |
| IPDS-16-625 | 1. The character Y-box size (specified in byte 7 in an LSS | ❓ |
| IPDS-16-626 | 2. The character Y-box size (specified in bytes 8 and 9 in | ❓ |
| IPDS-16-627 | 0228..02 The LSS pattern download format is either | ❓ |
| IPDS-16-628 | 0229..02 Invalid or unsupported value for LSS | ❓ |
| IPDS-16-629 | 0222..02 • 022A..02 | ❓ |
| IPDS-16-630 | 0231..01 Invalid or unsupported value for Load | ❓ |
| IPDS-16-631 | 0232..01 Invalid or unsupported Load Copy Control | ❓ |
| IPDS-16-632 | 1. An LCC command keyword control ID is invalid or | ❓ |
| IPDS-16-633 | 2. An LCC command suppression keyword is invalid or | ❓ |
| IPDS-16-634 | 3. An LCC command overlay keyword is invalid or | ❓ |
| IPDS-16-635 | 1. Some printers report X'0232..01' when an LCC | ❓ |
| IPDS-16-636 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-637 | 0232..02 Excess font data received | ❓ |
| IPDS-16-638 | 0233..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-639 | 0234..01 Invalid or unsupported value for Load | ❓ |
| IPDS-16-640 | 1. The number of bytes in the LCC command copy | ❓ |
| IPDS-16-641 | 2. The number of bytes in the LCC command copy | ❓ |
| IPDS-16-642 | 1. If a printer limits the number of overlay keywords in a | ❓ |
| IPDS-16-643 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-644 | 0236..01 Invalid or unsupported Load Copy Control | ❓ |
| IPDS-16-645 | 0237..01 Invalid or unsupported Load Copy Control | ❓ |
| IPDS-16-646 | 0237..03 Invalid or unsupported Load Copy Control | ❓ |
| IPDS-16-647 | 0237..04 Incompatible media source and media | ❓ |
| IPDS-16-648 | 0237..04 Incompatible media source and media | ❓ |
| IPDS-16-649 | 0237..05 Mixture of media-source IDs or media- | ❓ |
| IPDS-16-650 | 0236..01 • 0237..05 | ❓ |
| IPDS-16-651 | 1. In a Load Copy Control command, duplex is specified, | ❓ |
| IPDS-16-652 | 2. In a Load Copy Control command, duplex is specified, | ❓ |
| IPDS-16-653 | 0238..01 Maximum supported number of overlays | ❓ |
| IPDS-16-654 | 0238..03 Missing medium overlay HAID keyword | ❓ |
| IPDS-16-655 | 1. A X'E4' keyword is encountered but the next | ❓ |
| IPDS-16-656 | 2. A X'E5' keyword is encountered without an | ❓ |
| IPDS-16-657 | 0238..04 Missing preprinted form overlay HAID | ❓ |
| IPDS-16-658 | 1. A X'E6' keyword is encountered but the next | ❓ |
| IPDS-16-659 | 2. A X'E7' keyword is encountered without an | ❓ |
| IPDS-16-660 | 0238..10 Invalid invocation of a preprinted form | ❓ |
| IPDS-16-661 | 0238..11 Invalid preprinted form overlay keyword | ❓ |
| IPDS-16-662 | 0239..01 Maximum supported number of | ❓ |
| IPDS-16-663 | 0238..01 • 0239..01 | ❓ |
| IPDS-16-664 | 0239..02 LFC font Host-Assigned Resource ID | ❓ |
| IPDS-16-665 | 1. The single-byte fully described font HAID specified in | ❓ |
| IPDS-16-666 | 2. The section ID specified in the LFC command for this | ❓ |
| IPDS-16-667 | 1. The uniform or maximum baseline offset value in an | ❓ |
| IPDS-16-668 | 2. The uniform or maximum character increment value in | ❓ |
| IPDS-16-669 | 3. The maximum baseline extent value in an LFI | ❓ |
| IPDS-16-670 | 4. The uniform or minimum A-space value in an LFI | ❓ |
| IPDS-16-671 | 5. The variable-space increment value in an LFI | ❓ |
| IPDS-16-672 | 6. A pattern-index value in an LFI command refers to a | ❓ |
| IPDS-16-673 | 7. A character-increment value in an LFI command is | ❓ |
| IPDS-16-674 | 8. An A-space value in an LFI command is invalid or | ❓ |
| IPDS-16-675 | 9. A baseline-offset value in an LFI command is invalid or | ❓ |
| IPDS-16-676 | 10. A parameter value specified for an individual character | ❓ |
| IPDS-16-677 | 11. The combination of baseline offset and pattern size for | ❓ |
| IPDS-16-678 | 12. The underscore width value in an LFI command is | ❓ |
| IPDS-16-679 | 13. One or more orientation flags for a section between | ❓ |
| IPDS-16-680 | 0239..02 • 023C..02 | ❓ |
| IPDS-16-681 | 1. Some printers generate exception X'0233..02' for an | ❓ |
| IPDS-16-682 | 2. The maximum baseline extent value is provided in the | ❓ |
| IPDS-16-683 | 3. Some double-byte raster fonts were built with incorrect | ❓ |
| IPDS-16-684 | 4. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-685 | 1. The character-pattern addresses in the Character | ❓ |
| IPDS-16-686 | 2. A character-pattern address in the Character Pattern- | ❓ |
| IPDS-16-687 | 3. A character-pattern address in the Character Pattern- | ❓ |
| IPDS-16-688 | 4. A character-pattern address in the Character Pattern- | ❓ |
| IPDS-16-689 | 1. A fully described font or font index required as a result | ❓ |
| IPDS-16-690 | 2. The printer does not support the requested | ❓ |
| IPDS-16-691 | 3. The font-inline-sequence field of an LFE command is | ❓ |
| IPDS-16-692 | 1. Some printers report X'023F ..02' when an invalid or | ❓ |
| IPDS-16-693 | 2. This corresponds to an exception code defined by | ❓ |
| IPDS-16-694 | 3. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-695 | 0240..02 Invalid or unsupported value for font inline | ❓ |
| IPDS-16-696 | 0242..01 WIC command pel count is less than the | ❓ |
| IPDS-16-697 | 0243..01 WIC command pel count is greater than | ❓ |
| IPDS-16-698 | 0243..02 Invalid double-byte coded font section | ❓ |
| IPDS-16-699 | 1. The section-identifier value in an LFC or LFI command | ❓ |
| IPDS-16-700 | 2. The section-identifier value in an LFC or LFI command | ❓ |
| IPDS-16-701 | 3. The section-identifier value in an LFI command does | ❓ |
| IPDS-16-702 | 0244..01 WIC command scan-line count is less than | ❓ |
| IPDS-16-703 | 0244..02 Nonmatching double-byte coded font | ❓ |
| IPDS-16-704 | 0245..01 WIC command scan-line count is greater | ❓ |
| IPDS-16-705 | 0246..01 Invalid WIC input image format | ❓ |
| IPDS-16-706 | 1. Byte 8 of a WIC command is not X'00'. | ❓ |
| IPDS-16-707 | 2. The image-format value (byte 9) of a WIC command is | ❓ |
| IPDS-16-708 | 0246..02 Invalid parameter in an LFI command | ❓ |
| IPDS-16-709 | 1. A short-form LFI is loaded when a long-form LFI is | ❓ |
| IPDS-16-710 | 2. A long-form LFI is loaded when a short-form LFI is | ❓ |
| IPDS-16-711 | 3. The font-inline sequence (bytes 4 and 5) matches that | ❓ |
| IPDS-16-712 | 0246..03 Invalid GRID value in an LFC command | ❓ |
| IPDS-16-713 | 1. The GRID contains an invalid (out of range) value in | ❓ |
| IPDS-16-714 | 2. The printer requires a valid GRID, but one is not | ❓ |
| IPDS-16-715 | 0243..02 • 0246..03 | ❓ |
| IPDS-16-716 | 0247..01 Invalid or unsupported value for Write | ❓ |
| IPDS-16-717 | 1. The pel-magnification factor in a WIC command is | ❓ |
| IPDS-16-718 | 2. The scan-line magnification factor in a WIC command | ❓ |
| IPDS-16-719 | 0247..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-720 | 0248..01 Invalid or unsupported value for Write | ❓ |
| IPDS-16-721 | 0248..02 Invalid or unsupported value for Load | ❓ |
| IPDS-16-722 | 0249..01 Invalid scan-line-sequence direction in a | ❓ |
| IPDS-16-723 | 0249..02 Invalid or unsupported value for Load | ❓ |
| IPDS-16-724 | 1. The reference coordinate system (byte 16) in a WIC | ❓ |
| IPDS-16-725 | 2. The first pel location X | ❓ |
| IPDS-16-726 | 3. The first pel location Yp or B value (bytes 21–23) in a | ❓ |
| IPDS-16-727 | 0247..01 • 024B..02 | ❓ |
| IPDS-16-728 | 1. The length of the LSS additional parameter byte does | ❓ |
| IPDS-16-729 | 2. The LSS self-identifying field length is an invalid or | ❓ |
| IPDS-16-730 | 3. The amount of raster data in the LSS command does | ❓ |
| IPDS-16-731 | 0253..01 Invalid or unsupported value for Write | ❓ |
| IPDS-16-732 | 0254..01 Invalid Color Fidelity (X'75') triplet length | ❓ |
| IPDS-16-733 | 1. The triplet-length field in a Color Fidelity (X'75') triplet | ❓ |
| IPDS-16-734 | 2. A Color Fidelity (X'75') triplet is too long to fit in the | ❓ |
| IPDS-16-735 | 0254..02 Invalid Color Fidelity (X'75') triplet | ❓ |
| IPDS-16-736 | 0254..03 Invalid Color Fidelity (X'75') triplet report | ❓ |
| IPDS-16-737 | 0254..04 Invalid Color Fidelity (X'75') triplet | ❓ |
| IPDS-16-738 | 0254..05 Invalid triplet information in a PFC | ❓ |
| IPDS-16-739 | 0254..31 Invalid Toner Saver (X'74') triplet length | ❓ |
| IPDS-16-740 | 1. The triplet-length field in a Toner Saver (X'74') triplet | ❓ |
| IPDS-16-741 | 2. A Toner Saver (X'74') triplet is too long to fit in the | ❓ |
| IPDS-16-742 | 0254..32 Mismatched toner saver value for a saved | ❓ |
| IPDS-16-743 | 0254..33 Invalid Toner Saver (X'74') triplet control | ❓ |
| IPDS-16-744 | 0254..41 Invalid Finishing Fidelity (X'88') triplet | ❓ |
| IPDS-16-745 | 1. The triplet-length field in a Finishing Fidelity (X'88') | ❓ |
| IPDS-16-746 | 2. A Finishing Fidelity (X'88') triplet is too long to fit in the | ❓ |
| IPDS-16-747 | 0254..42 Invalid Finishing Fidelity (X'88') triplet | ❓ |
| IPDS-16-748 | 0254..43 Invalid Finishing Fidelity (X'88') triplet | ❓ |
| IPDS-16-749 | 0254..51 Invalid Text Fidelity (X'86') triplet length | ❓ |
| IPDS-16-750 | 0254..05 • 0254..51 | ❓ |
| IPDS-16-751 | 1. The triplet-length field in a Text Fidelity (X'86') triplet | ❓ |
| IPDS-16-752 | 2. A Text Fidelity (X'86') triplet is too long to fit in the | ❓ |
| IPDS-16-753 | 0254..52 Invalid Text Fidelity (X'86') triplet continue | ❓ |
| IPDS-16-754 | 0254..53 Invalid Text Fidelity (X'86') triplet report | ❓ |
| IPDS-16-755 | 0254..71 Invalid CMR Tag Fidelity (X'96') triplet | ❓ |
| IPDS-16-756 | 1. The triplet-length field in a CMR Tag Fidelity (X'96') | ❓ |
| IPDS-16-757 | 2. A CMR Tag Fidelity (X'96') triplet is too long to fit in the | ❓ |
| IPDS-16-758 | 0254..72 Invalid CMR Tag Fidelity (X'96') triplet | ❓ |
| IPDS-16-759 | 0254..73 Invalid CMR Tag Fidelity (X'96') triplet | ❓ |
| IPDS-16-760 | 0255..00 Page group already saved | ❓ |
| IPDS-16-761 | 0255..01 Included page not previously saved | ❓ |
| IPDS-16-762 | 0254..52 • 0255..01 | ❓ |
| IPDS-16-763 | 0255..02 Invalid page sequence number in an ISP | ❓ |
| IPDS-16-764 | 0255..03 Saved page group not found | ❓ |
| IPDS-16-765 | 1. An Include Saved Page command attempts to include | ❓ |
| IPDS-16-766 | 2. An Include Saved Page command does not contain | ❓ |
| IPDS-16-767 | 0255..04 Multiple ISP commands encountered | ❓ |
| IPDS-16-768 | 0255..05 Nested ISP command encountered | ❓ |
| IPDS-16-769 | 0255..06 Included page not previously saved with | ❓ |
| IPDS-16-770 | 0255..07 Saved page group to be deleted was not | ❓ |
| IPDS-16-771 | 0255..08 Invalid triplet information in an XOH-DSPG | ❓ |
| IPDS-16-772 | 1. Byte 2 or the first byte after a valid triplet is X'00' or | ❓ |
| IPDS-16-773 | 2. A triplet other than a Group ID (X'00') triplet is | ❓ |
| IPDS-16-774 | 3. A Group ID (X'00') triplet without a variable-length | ❓ |
| IPDS-16-775 | 0255..09 Page too large to save | ❓ |
| IPDS-16-776 | 0255..02 • 0255..09 | ❓ |
| IPDS-16-777 | 1. Byte 2 or the first byte after a valid triplet is X'00' or | ❓ |
| IPDS-16-778 | 2. A triplet other than a Group ID (X'00') triplet is | ❓ |
| IPDS-16-779 | 3. A Group ID (X'00') triplet without a variable-length | ❓ |
| IPDS-16-780 | 1. Media-specific CMRs changed | ❓ |
| IPDS-16-781 | 2. The device resolution changed | ❓ |
| IPDS-16-782 | 3. Colorants used when the page was saved are no | ❓ |
| IPDS-16-783 | 0256..01 Invalid CPGID value in a CGCSGID (X'01') | ❓ |
| IPDS-16-784 | 0256..11 Invalid TTC-font-index value in a Linked | ❓ |
| IPDS-16-785 | 0256..12 Invalid HAID value in a Linked Font (X'8D') | ❓ |
| IPDS-16-786 | 0256..13 Invalid font-ID-type value in a Linked Font | ❓ |
| IPDS-16-787 | 0256..14 Invalid full-font-name value in a Linked | ❓ |
| IPDS-16-788 | 0256..21 Invalid FQN type value in a Fully Qualified | ❓ |
| IPDS-16-789 | 0256..22 Invalid FQN format value in a Fully | ❓ |
| IPDS-16-790 | 0256..23 Mismatched object OIDs found while | ❓ |
| IPDS-16-791 | 1. CMR objects, whose OID is specified in a Fully | ❓ |
| IPDS-16-792 | 2. TrueType/OpenType objects, whose OID is specified | ❓ |
| IPDS-16-793 | 0256..24 Invalid FQN in a Fully Qualified Name | ❓ |
| IPDS-16-794 | 1. The FQN Format value is X'00' and the FQN value is | ❓ |
| IPDS-16-795 | 2. The FQN Format value is X'10' and the FQN value is | ❓ |
| IPDS-16-796 | 0256..31 Invalid or unsupported encoding-scheme- | ❓ |
| IPDS-16-797 | 0256..51 Invalid processing-mode value in a Color | ❓ |
| IPDS-16-798 | 0256..61 Invalid HAID value in an Invoke CMR (X'92') | ❓ |
| IPDS-16-799 | 0256..21 • 0256..61 | ❓ |
| IPDS-16-800 | 0256..71 Invalid rendering-intent value in a | ❓ |
| IPDS-16-801 | 0256..81 Invalid or unsupported appearance value | ❓ |
| IPDS-16-802 | 0256..91 Invalid or unsupported unit base value in | ❓ |
| IPDS-16-803 | 1. The X unit base field in an Image Resolution (X'9A') | ❓ |
| IPDS-16-804 | 2. The Y unit base field in an Image Resolution (X'9A') | ❓ |
| IPDS-16-805 | 3. The Y unit base in an Image Resolution (X'9A') triplet | ❓ |
| IPDS-16-806 | 0256..92 Invalid units per unit base value in an | ❓ |
| IPDS-16-807 | 0256..71 • 0256..B1 | ❓ |
| IPDS-16-808 | 1. The X unit base field in an Object Container | ❓ |
| IPDS-16-809 | 2. The Y unit base field in an Object Container | ❓ |
| IPDS-16-810 | 3. The Y unit base in an Object Container Presentation | ❓ |
| IPDS-16-811 | 1. The Host-Assigned ID specified in an Invoke Tertiary | ❓ |
| IPDS-16-812 | 2. The Host-Assigned ID specified in an Invoke Tertiary | ❓ |
| IPDS-16-813 | 0257..01 Invalid RPO entry-length value | ❓ |
| IPDS-16-814 | 1. An invalid entry-length value is specified in a Rasterize | ❓ |
| IPDS-16-815 | 2. A RPO entry is too long to fit into the RPO command. | ❓ |
| IPDS-16-816 | 0257..02 Invalid RPO resource type | ❓ |
| IPDS-16-817 | 1. An invalid resource type value is specified in a | ❓ |
| IPDS-16-818 | 2. The RPO entry specified a data object resource, but | ❓ |
| IPDS-16-819 | EPS (Encapsulated PostScript) with transparency | ❓ |
| IPDS-16-820 | EPS without transparency | ❓ |
| IPDS-16-821 | GIF (Graphics Interchange Format) | ❓ |
| IPDS-16-822 | IOCA (Image Object Content Architecture) image | ❓ |
| IPDS-16-823 | JPEG (Joint Photographic Experts Group) AFPC | ❓ |
| IPDS-16-824 | JP2 (JPEG2000 File Format) | ❓ |
| IPDS-16-825 | Overlay | ❓ |
| IPDS-16-826 | PCL (Printer Command Language) page object | ❓ |
| IPDS-16-827 | PDF (Portable Document Format) multiple-page file | ❓ |
| IPDS-16-828 | PDF multiple-page file without transparency | ❓ |
| IPDS-16-829 | PDF single page with transparency | ❓ |
| IPDS-16-830 | PDF single page without transparency | ❓ |
| IPDS-16-831 | PNG (Portable Network Graphics) AFPC PNG | ❓ |
| IPDS-16-832 | SVG (Scalable Vector Graphics) AFPC SVG Subset | ❓ |
| IPDS-16-833 | TIFF (Tag Image File Format) AFPC TIFF Subset | ❓ |
| IPDS-16-834 | TIFF with transparency | ❓ |
| IPDS-16-835 | TIFF without transparency | ❓ |
| IPDS-16-836 | TIFF multiple-image file with transparency | ❓ |
| IPDS-16-837 | TIFF multiple-image file without transparency | ❓ |
| IPDS-16-838 | 0257..03 Invalid or unsupported RPO HAID value | ❓ |
| IPDS-16-839 | 1. An invalid HAID value is specified in a Rasterize | ❓ |
| IPDS-16-840 | 2. An overlay HAID value in the range X'00FF'–X'7EFF' is | ❓ |
| IPDS-16-841 | 0257..04 Resource not activated for RPO command | ❓ |
| IPDS-16-842 | 0257..05 Invalid or unsupported RPO unit-base | ❓ |
| IPDS-16-843 | 0257..06 Invalid or unsupported RPO units-per-unit- | ❓ |
| IPDS-16-844 | 0257..07 Invalid or unsupported RPO object-area- | ❓ |
| IPDS-16-845 | 1. An invalid or unsupported X | ❓ |
| IPDS-16-846 | 2. An invalid or unsupported Yoa-extent value is specified | ❓ |
| IPDS-16-847 | 0257..08 Invalid or unsupported RPO mapping- | ❓ |
| IPDS-16-848 | 1. An invalid or unsupported mapping-control-option | ❓ |
| IPDS-16-849 | 2. The position mapping is specified for an IOCA object. | ❓ |
| IPDS-16-850 | 0257..09 Unsupported RPO object-area-offset value | ❓ |
| IPDS-16-851 | 1. A negative X | ❓ |
| IPDS-16-852 | 2. A valid Xoa-offset or Yoa-offset value is specified in a | ❓ |
| IPDS-16-853 | 0258..03 Invalid or unsupported value for text color | ❓ |
| IPDS-16-854 | 1. The color field in the Set Text Color (STC) control | ❓ |
| IPDS-16-855 | 2. The text-color field in an LPD or WTC command is | ❓ |
| IPDS-16-856 | 3. The precision field in the Set Text Color (STC) control | ❓ |
| IPDS-16-857 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-858 | 2. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-859 | 3. The STC precision parameter has been retired in the | ❓ |
| IPDS-16-860 | 4. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-861 | 0257..05 • 0258..03 | ❓ |
| IPDS-16-862 | 1. The Reset parameter or the X | ❓ |
| IPDS-16-863 | 2. The Unit Base parameter, the Units per Unit Base | ❓ |
| IPDS-16-864 | 1. Sense bytes 16–17 contain the CMR TagID value for | ❓ |
| IPDS-16-865 | 2. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-866 | 3. For printers that support CMR tag fidelity control, | ❓ |
| IPDS-16-867 | 4. This corresponds to an exception code defined by | ❓ |
| IPDS-16-868 | 1. A generic CMR is selected, but the printer does not | ❓ |
| IPDS-16-869 | 2. A generic CMR is downloaded that the printer is | ❓ |
| IPDS-16-870 | 1. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-871 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-872 | 1. A printer-operator control has been invoked to achieve | ❓ |
| IPDS-16-873 | 2. A selected halftone CMR is not appropriate for the | ❓ |
| IPDS-16-874 | 3. A halftone (or tone-transfer-curve) CMR has been | ❓ |
| IPDS-16-875 | 4. A limited number of halftone or tone-transfer-curve | ❓ |
| IPDS-16-876 | 1. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-877 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-878 | 0260..02 Invalid or unsupported value for Logical | ❓ |
| IPDS-16-879 | 0261..02 Invalid or unsupported value for Logical | ❓ |
| IPDS-16-880 | 0262..02 Invalid or unsupported value for LPD Xp | ❓ |
| IPDS-16-881 | 2. The Xm extent in an XOH Set Media Size command is | ❓ |
| IPDS-16-882 | 1. X'0272..02' is used by some printers for this exception | ❓ |
| IPDS-16-883 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-884 | 0263..01 Insufficient pattern storage | ❓ |
| IPDS-16-885 | 0263..02 Invalid or unsupported value for LPD Y | ❓ |
| IPDS-16-886 | 1. The Yp extent in a Logical Page Descriptor command | ❓ |
| IPDS-16-887 | 2. The Ym extent in an XOH Set Media Size command is | ❓ |
| IPDS-16-888 | 1. X'0273..02' is used by some printers for this exception | ❓ |
| IPDS-16-889 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-890 | 0264..01 Insufficient control storage | ❓ |
| IPDS-16-891 | 0264..02 Invalid or unsupported value for Logical | ❓ |
| IPDS-16-892 | 0260..02 • 0264..02 | ❓ |
| IPDS-16-893 | 0268..02 Invalid or unsupported value for LPD or | ❓ |
| IPDS-16-894 | 0269..02 Invalid baseline-sequence direction in the | ❓ |
| IPDS-16-895 | 1. An entry length value specified in an XOH-SMM | ❓ |
| IPDS-16-896 | 2. An entry type value specified in an XOH-SMM | ❓ |
| IPDS-16-897 | 3. A medium modification ID value specified in an XOH- | ❓ |
| IPDS-16-898 | 0270..02 Invalid or unsupported value for XOH Set | ❓ |
| IPDS-16-899 | 0268..02 • 0270..02 | ❓ |
| IPDS-16-900 | 0272..02 Invalid or unsupported value for XOH Set | ❓ |
| IPDS-16-901 | 0273..02 Invalid or unsupported value for XOH Set | ❓ |
| IPDS-16-902 | 0274..02 Invalid or unsupported value for XOH Set | ❓ |
| IPDS-16-903 | 0277..01 Group termination exception | ❓ |
| IPDS-16-904 | 0278..01 Invalid or unsupported order type | ❓ |
| IPDS-16-905 | 1. A Group ID (X'00') triplet in an ISP , XOH-DGB, XOH- | ❓ |
| IPDS-16-906 | 2. A Coded Graphic Character Set Global Identifier | ❓ |
| IPDS-16-907 | 3. A Fully Qualified Name (X'02') triplet in an AR or | ❓ |
| IPDS-16-908 | 4. An Encoding Scheme ID (X'50') triplet in an AR | ❓ |
| IPDS-16-909 | 5. An Object Offset (X'5A') triplet in an IDO, RPO, or | ❓ |
| IPDS-16-910 | 6. A Group Information (X'6E') triplet in an XOH-DGB | ❓ |
| IPDS-16-911 | 7. A Finishing Operation (X'85') triplet or a UP | ❓ |
| IPDS-16-912 | 8. A Data Object Font Descriptor (X'8B') triplet in an AR | ❓ |
| IPDS-16-913 | 9. A Linked Font (X'8D') triplet in an AR command | ❓ |
| IPDS-16-914 | 10. A Color Management Resource Descriptor (X'91') | ❓ |
| IPDS-16-915 | 11. An Invoke CMR (X'92') triplet in an IDO, LPD, RPO, | ❓ |
| IPDS-16-916 | 12. A Rendering Intent (X'95') triplet in an IDO, LPD, RPO, | ❓ |
| IPDS-16-917 | 13. A Device Appearance (X'97') triplet in an SPE | ❓ |
| IPDS-16-918 | 14. An Image Resolution (X'9A') triplet in an IDO, RPO, or | ❓ |
| IPDS-16-919 | 15. An Object Container Presentation Space Size (X'9C') | ❓ |
| IPDS-16-920 | 16. A Setup Name (X'9E') triplet in an ASN or XOA-RSNL | ❓ |
| IPDS-16-921 | 17. An Invoke Tertiary Resource (X'A2') triplet in a WBCC | ❓ |
| IPDS-16-922 | 0272..02 • 027B..01 | ❓ |
| IPDS-16-923 | The number of data bytes specified in a triplet length field | ❓ |
| IPDS-16-924 | The number of data bytes specified in a triplet length field | ❓ |
| IPDS-16-925 | 1. A Group ID (X'00') triplet in an ISP , XOH-DGB, XOH- | ❓ |
| IPDS-16-926 | 2. A Coded Graphic Character Set Global Identifier | ❓ |
| IPDS-16-927 | 3. A Fully Qualified Name (X'02') triplet in an AR or | ❓ |
| IPDS-16-928 | 4. An Encoding Scheme ID (X'50') triplet in an AR | ❓ |
| IPDS-16-929 | 5. An Object Offset (X'5A') triplet in an IDO, RPO, or | ❓ |
| IPDS-16-930 | 6. A Group Information (X'6E') triplet in an XOH-DGB | ❓ |
| IPDS-16-931 | 7. A Finishing Operation (X'85') triplet or a UP 3I Finishing | ❓ |
| IPDS-16-932 | 8. A Data Object Font Descriptor (X'8B') triplet in an AR | ❓ |
| IPDS-16-933 | 9. A Linked Font (X'8D') triplet in an AR command | ❓ |
| IPDS-16-934 | 10. A Color Management Resource Descriptor (X'91') | ❓ |
| IPDS-16-935 | 11. An Invoke CMR (X'92') triplet in an IDO, LPD, RPO, | ❓ |
| IPDS-16-936 | 12. A Rendering Intent (X'95') triplet in an IDO, LPD, RPO, | ❓ |
| IPDS-16-937 | 13. A Device Appearance (X'97') triplet in an SPE | ❓ |
| IPDS-16-938 | 14. An Image Resolution (X'9A') triplet in an IDO, RPO, or | ❓ |
| IPDS-16-939 | 15. An Object Container Presentation Space Size (X'9C') | ❓ |
| IPDS-16-940 | 16. A Setup Name (X'9E') triplet in an ASN or XOA-RSNL | ❓ |
| IPDS-16-941 | 17. An Invoke Tertiary Resource (X'A2') triplet in a WBCC | ❓ |
| IPDS-16-942 | 1. An AFO or XOH-DGB command contains two or more | ❓ |
| IPDS-16-943 | 2. A pair of nested XOH-DGB commands contains two or | ❓ |
| IPDS-16-944 | 1. For printers that support finishing fidelity control, | ❓ |
| IPDS-16-945 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-946 | 1. A Finishing Operation (X'85') triplet specified on an | ❓ |
| IPDS-16-947 | 2. A Finishing Operation (X'85') triplet specified on an | ❓ |
| IPDS-16-948 | 3. An AFO or XOH-DGB command contains two or more | ❓ |
| IPDS-16-949 | 4. A pair of nested XOH-DGB commands contains two or | ❓ |
| IPDS-16-950 | 1. For printers that support finishing fidelity control, | ❓ |
| IPDS-16-951 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-952 | 1. For printers that support finishing fidelity control, | ❓ |
| IPDS-16-953 | 2. For more information about the error code in sense | ❓ |
| IPDS-16-954 | 1. For printers that support finishing fidelity control, | ❓ |
| IPDS-16-955 | 2. For more information about the error code in sense | ❓ |
| IPDS-16-956 | 1. For printers that support finishing fidelity control, | ❓ |
| IPDS-16-957 | 2. For more information about the error code in sense | ❓ |
| IPDS-16-958 | 0280..02 Invalid or unsupported rule width | ❓ |
| IPDS-16-959 | 0281..01 Insufficient storage for a page segment or | ❓ |
| IPDS-16-960 | 0282..02 Invalid or unsupported rule length | ❓ |
| IPDS-16-961 | 0285..01 Invalid overlay ID or overlay HAID value in | ❓ |
| IPDS-16-962 | 0287..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-963 | 0288..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-964 | 0289..02 Invalid or unsupported value for Load Font | ❓ |
| IPDS-16-965 | 0280..02 • 028A..01 | ❓ |
| IPDS-16-966 | 1. The length of an AR entry is invalid or unsupported. | ❓ |
| IPDS-16-967 | 2. The Host-Assigned ID in an AR entry is invalid. | ❓ |
| IPDS-16-968 | 3. The section ID in an AR entry is invalid. | ❓ |
| IPDS-16-969 | 4. The font inline sequence in an AR entry is invalid. | ❓ |
| IPDS-16-970 | 5. The resource type in an AR entry is invalid. | ❓ |
| IPDS-16-971 | 6. The resource ID format in an AR entry is invalid. | ❓ |
| IPDS-16-972 | 7. The resource type in an AR entry is not valid with or is | ❓ |
| IPDS-16-973 | 8. The resource ID in an AR entry is invalid. | ❓ |
| IPDS-16-974 | 1. The first byte after the fixed portion of a resource ID or | ❓ |
| IPDS-16-975 | 2. The triplet-length field in a Resource ID triplet contains | ❓ |
| IPDS-16-976 | 3. A Resource ID triplet is too long to fit in the containing | ❓ |
| IPDS-16-977 | 1. The metric technology field (byte 2) contains an invalid | ❓ |
| IPDS-16-978 | 2. The unit base field (byte 3) contains an invalid value. | ❓ |
| IPDS-16-979 | 3. The X units per unit base field (bytes 4–5) contains an | ❓ |
| IPDS-16-980 | 4. The Y units per unit base field (bytes 6–7) contains an | ❓ |
| IPDS-16-981 | 1. The unit base field (byte 2) contains an invalid value. | ❓ |
| IPDS-16-982 | 2. The XUPUB or YUPUB field (bytes 3–4 or bytes 5–6) | ❓ |
| IPDS-16-983 | 3. The XUPUB value is not equal to the YUPUB value. | ❓ |
| IPDS-16-984 | 1. An invalid font-technology value is specified in a Data | ❓ |
| IPDS-16-985 | 2. The font technology specified in a Data Object Font | ❓ |
| IPDS-16-986 | 1. An invalid encoding environment value is specified in a | ❓ |
| IPDS-16-987 | 2. The encoding environment value specified in a Data | ❓ |
| IPDS-16-988 | 028F ..11 • 028F ..26 | ❓ |
| IPDS-16-989 | 1. An AR command to activate a data-object font | ❓ |
| IPDS-16-990 | 2. An AR command to activate a data-object font | ❓ |
| IPDS-16-991 | 1. Reporting of this exception is controlled by the Report | ❓ |
| IPDS-16-992 | 2. The HAID value returned in sense bytes 14–15 is the | ❓ |
| IPDS-16-993 | 3. Sense bytes 16–17 contain the unknown character's | ❓ |
| IPDS-16-994 | 0290..01 Invalid or unsupported overlay ID or | ❓ |
| IPDS-16-995 | 1. Either an overlay ID or overlay HAID value in a BO | ❓ |
| IPDS-16-996 | 2. An overlay HAID value in an IO command is invalid or | ❓ |
| IPDS-16-997 | 3. An overlay ID or overlay HAID value in an LCC | ❓ |
| IPDS-16-998 | 0291..01 Overlay already activated | ❓ |
| IPDS-16-999 | 0291..02 Invalid or unsupported value for XOA | ❓ |
| IPDS-16-1000 | 1. The length of a Request Resource List entry is invalid | ❓ |
| IPDS-16-1001 | 2. A nonzero value is specified in the entry-continuation | ❓ |
| IPDS-16-1002 | 3. A Request Resource List command has multiple | ❓ |
| IPDS-16-1003 | 4. The query type parameter of a Request Resource List | ❓ |
| IPDS-16-1004 | 5. The resource type in a Request Resource List entry is | ❓ |
| IPDS-16-1005 | 6. A Request Resource List entry of query type X'05', | ❓ |
| IPDS-16-1006 | 7. The resource ID format in a Request Resource List | ❓ |
| IPDS-16-1007 | 0292..01 Overlay not activated | ❓ |
| IPDS-16-1008 | 1. An overlay identified by the overlay ID or overlay HAID | ❓ |
| IPDS-16-1009 | 2. An overlay identified by the overlay ID or overlay HAID | ❓ |
| IPDS-16-1010 | 0292..02 Invalid XOA Print-Quality Control (PQC) | ❓ |
| IPDS-16-1011 | 0293..00 Invalid nesting of a preprinted form overlay | ❓ |
| IPDS-16-1012 | 0293..01 Recursive overlay invocation | ❓ |
| IPDS-16-1013 | 0293..02 Invalid orientation value in an IO command | ❓ |
| IPDS-16-1014 | 0293..03 Invalid invocation of a preprinted form | ❓ |
| IPDS-16-1015 | 1. More than one Include Overlay command specified a | ❓ |
| IPDS-16-1016 | 0292..01 • 0293..03 | ❓ |
| IPDS-16-1017 | 2. A PFO has already been invoked via an LCC | ❓ |
| IPDS-16-1018 | 0293..04 Invalid overlay-use value in an IO | ❓ |
| IPDS-16-1019 | 0294..01 Invalid or unsupported value for page | ❓ |
| IPDS-16-1020 | 0295..01 Page segment Host-Assigned ID already | ❓ |
| IPDS-16-1021 | 0295..02 Invalid or unsupported value for XOH Page | ❓ |
| IPDS-16-1022 | 0296..01 Page segment not activated | ❓ |
| IPDS-16-1023 | 1. The page segment identified by the page segment | ❓ |
| IPDS-16-1024 | 2. The page segment identified by the page segment | ❓ |
| IPDS-16-1025 | 0297..01 Overlay nesting limit exceeded | ❓ |
| IPDS-16-1026 | 1. While processing an overlay the printer is unable to | ❓ |
| IPDS-16-1027 | 2. While processing an overlay, the printer is unable to | ❓ |
| IPDS-16-1028 | 0298..01 Invalid or unsupported suppression ID | ❓ |
| IPDS-16-1029 | 0293..04 • 0298..01 | ❓ |
| IPDS-16-1030 | 1. The suppression ID in an LCC command is invalid or | ❓ |
| IPDS-16-1031 | 2. The Begin Suppression (BSU) suppression ID in a WT | ❓ |
| IPDS-16-1032 | LCC: none | ❓ |
| IPDS-16-1033 | WT : ignore the control sequence | ❓ |
| IPDS-16-1034 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1035 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-1036 | 0298..03 Invalid or unsupported value for | ❓ |
| IPDS-16-1037 | 1. The TBM increment, move direction, or precision value | ❓ |
| IPDS-16-1038 | 2. Unsupported multiple-offset TBM. | ❓ |
| IPDS-16-1039 | 3. Unsupported substitution character in the TBM field. | ❓ |
| IPDS-16-1040 | 4. Unable to support TBM by printing full-size characters. | ❓ |
| IPDS-16-1041 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1042 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-1043 | 0299..02 Invalid Edge Mark Parameter | ❓ |
| IPDS-16-1044 | 1. (Mandatory) The character increment of the selected | ❓ |
| IPDS-16-1045 | 2. (Optional) The character increment of the selected | ❓ |
| IPDS-16-1046 | 3. (Mandatory) The overstrike character is not a printable | ❓ |
| IPDS-16-1047 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1048 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-1049 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1050 | 2. IPDS printers that return property pair X'4303' in the | ❓ |
| IPDS-16-1051 | 029C..00 Wrong font used with a Glyph Layout | ❓ |
| IPDS-16-1052 | 0298..03 • 029C..00 | ❓ |
| IPDS-16-1053 | 1. The object OID specified in the GLC control sequence | ❓ |
| IPDS-16-1054 | 2. The OIDs do match for a font collection, however the | ❓ |
| IPDS-16-1055 | 3. The printer does not have a font OID at the time a | ❓ |
| IPDS-16-1056 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1057 | 2. Sense bytes 14–15 should contain the HAID of the | ❓ |
| IPDS-16-1058 | 3. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-1059 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1060 | 2. Sense bytes 16–17 contain the glyph ID that caused | ❓ |
| IPDS-16-1061 | ... [GIR GAR [GOR]] [UCT] | ❓ |
| IPDS-16-1062 | 1. The OID-length field or the full-font-name-length field | ❓ |
| IPDS-16-1063 | 2. The OID-length field contains an invalid value. | ❓ |
| IPDS-16-1064 | 3. The full-font-name-length field contains an invalid | ❓ |
| IPDS-16-1065 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1066 | 2. Sense bytes 16–17 should contain the explanation | ❓ |
| IPDS-16-1067 | 1. This corresponds to an exception code defined by | ❓ |
| IPDS-16-1068 | 2. For printers that support text fidelity control, reporting | ❓ |
| IPDS-16-1069 | 1. The Xp coordinate or Yp coordinate in an IO command | ❓ |
| IPDS-16-1070 | 2. The Overlay Type parameter in an IO command is | ❓ |
| IPDS-16-1071 | 1. The CPGID value specified in a Load Code Page | ❓ |
| IPDS-16-1072 | 2. The printer requires a valid CPGID, but one is not | ❓ |
| IPDS-16-1073 | 1. The amount of code page data received in a series of | ❓ |
| IPDS-16-1074 | 2. The last entry in a code page is incomplete. | ❓ |
| IPDS-16-1075 | 1. The entries in a series of LCP commands or in a | ❓ |
| IPDS-16-1076 | 2. A code point appeared more than once. | ❓ |
| IPDS-16-1077 | 1. The FGID value specified in a Load Font Character | ❓ |
| IPDS-16-1078 | 2. The printer requires a valid FGID, but one is not | ❓ |
| IPDS-16-1079 | 1. A string of text within a WT or WG command is | ❓ |
| IPDS-16-1080 | 2. Some printers can print MICR text on one side of the | ❓ |
| IPDS-16-1081 | 3. Some printers can print MICR text only in specific | ❓ |
| IPDS-16-1082 | 4. Some printers allow MICR printing to be disabled | ❓ |
| IPDS-16-1083 | 1. In a Load Copy Control command, more than one | ❓ |
| IPDS-16-1084 | 2. In a Load Copy Control command, more than one | ❓ |
| IPDS-16-1085 | 0100..00 Normal Printer Restart | ❓ |
| IPDS-16-1086 | The printer is IMLed in a normal manner. | ❓ |
| IPDS-16-1087 | The printer is switched from Offline to Online State. | ❓ |
| IPDS-16-1088 | The channel issues a System_Reset (applies only to | ❓ |
| IPDS-16-1089 | The channel issues a Selective_Reset (applies only to | ❓ |
| IPDS-16-1090 | An IPDS dialog ends and the printer resets the IPDS | ❓ |
| IPDS-16-1091 | 0101..00 Media-source characteristics changed | ❓ |
| IPDS-16-1092 | The media source ID of one or more of the installed | ❓ |
| IPDS-16-1093 | The size of the medium presentation space in one or | ❓ |
| IPDS-16-1094 | The size or offset of the physical printable area in one or | ❓ |
| IPDS-16-1095 | One or more of the media-source-characteristics flags for | ❓ |
| IPDS-16-1096 | The media identification of one or more of the installed | ❓ |
| IPDS-16-1097 | 0102..00 MICR printing status changed | ❓ |
| IPDS-16-1098 | 0103..00 Burster-trimmer-stacker/cutter-trimmer- | ❓ |
| IPDS-16-1099 | 0104..00 Medium modification availability has | ❓ |
| IPDS-16-1100 | 0105..00 Media-destination status changed | ❓ |
| IPDS-16-1101 | 0106..00 Device resolution changed | ❓ |
| IPDS-16-1102 | 0100..00 • 0106..00 | ❓ |
| IPDS-16-1103 | 0108..00 Printer setup changed | ❓ |
| IPDS-16-1104 | 0109..00 Supported finishing operations changed | ❓ |
| IPDS-16-1105 | 1. A change from one active setup name to another | ❓ |
| IPDS-16-1106 | 2. A change from no active setup name to an active | ❓ |
| IPDS-16-1107 | 3. A change from an active setup name to no active | ❓ |
| IPDS-16-1108 | 4. A change from an active setup name to the same | ❓ |
| IPDS-16-1109 | 1. This exception ID uses sense format 0 so that sense | ❓ |
| IPDS-16-1110 | 2. Some of the format 0 sense byte information that is | ❓ |
| IPDS-16-1111 | 0110..00 Print Position Adjustment | ❓ |
| IPDS-16-1112 | 0111..00 Buffered pages discarded | ❓ |
| IPDS-16-1113 | 0113..00 IPDS Trace full | ❓ |
| IPDS-16-1114 | 0114..00 Asynchronous decompression error | ❓ |
| IPDS-16-1115 | 0108..00 • 0114..00 | ❓ |
| IPDS-16-1116 | 0115..00 Asynchronous color-related error | ❓ |
| IPDS-16-1117 | 1. This exception ID uses sense format 0 so that sense | ❓ |
| IPDS-16-1118 | 2. Some of the format 0 sense byte information that is | ❓ |
| IPDS-16-1119 | 3. For printers that support color fidelity control, reporting | ❓ |
| IPDS-16-1120 | 0120..00 Colorant information changed | ❓ |
| IPDS-16-1121 | 0140..00 Printer paused in the middle of a recovery- | ❓ |
| IPDS-16-1122 | 0140..00 Printer paused in the middle of a | ❓ |
| IPDS-16-1123 | 0141..00 Too many pages were sent for a recovery- | ❓ |
| IPDS-16-1124 | 0115..00 • 0141..00 | ❓ |
| IPDS-16-1125 | 0180..00 Request to end IPDS dialog | ❓ |
| IPDS-16-1126 | Table 65. Error Codes for Data Objects | ❓ |
| IPDS-16-1127 | 0 X'0000' | No error code provided | ❓ |
| IPDS-16-1128 | PostScript Object Errors** | ❓ |
| IPDS-16-1129 | 30 X'001E' | PostScript Object Error: Setpagedevice or setdevparams request cannot be satisfied | ❓ |
| IPDS-16-1130 | 31 X'001F' | PostScript Object Error: Dictionary has no more room in it to store entry | ❓ |
| IPDS-16-1131 | 32 X'0020' | PostScript Object Error: Too many begin operators detected | ❓ |
| IPDS-16-1132 | 33 X'0021' | PostScript Object Error: Too many end operators detected | ❓ |
| IPDS-16-1133 | 34 X'0022' | PostScript Object Error: Executive stack nesting too deep | ❓ |
| IPDS-16-1134 | 35 X'0023' | PostScript Object Error: External interrupt request detected | ❓ |
| IPDS-16-1135 | 36 X'0024' | PostScript Object Error: Attempt to violate access attribute | ❓ |
| IPDS-16-1136 | 37 X'0025' | PostScript Object Error: Operator exit was not found in loop context | ❓ |
| IPDS-16-1137 | 38 X'0026' | PostScript Object Error: Unacceptable access string | ❓ |
| IPDS-16-1138 | 39 X'0027' | PostScript Object Error: Invalid font resource name or font or CIDFont dictionary | ❓ |
| IPDS-16-1139 | 40 X'0028' | PostScript Object Error: Improper restore has been detected | ❓ |
| IPDS-16-1140 | 41 X'0029' | PostScript Object Error: Input/output error has been detected | ❓ |
| IPDS-16-1141 | 42 X'002A' | PostScript Object Error: Implementation limit has been exceeded | ❓ |
| IPDS-16-1142 | 43 X'002B' | PostScript Object Error: The current point undefined | ❓ |
| IPDS-16-1143 | 44 X'002C' | PostScript Object Error: An operator's operand is out of bounds | ❓ |
| IPDS-16-1144 | 45 X'002D' | PostScript Object Error: An operand stack overflow has been detected | ❓ |
| IPDS-16-1145 | 46 X'002E' | PostScript Object Error: An operand stack underflow has been detected | ❓ |
| IPDS-16-1146 | 47 X'002F' | PostScript Object Error: A PostScript language syntax error has been detected | ❓ |
| IPDS-16-1147 | 48 X'0030' | PostScript Object Error: Object processing time limit has been exceeded | ❓ |
| IPDS-16-1148 | 49 X'0031' | PostScript Object Error: An operator's operand has been detected as the wrong type | ❓ |
| IPDS-16-1149 | 50 X'0032' | PostScript Object Error: A name used in object is not known to the interpreter | ❓ |
| IPDS-16-1150 | 51 X'0033' | PostScript Object Error: Filename used in object was not found by the interpreter | ❓ |
| IPDS-16-1151 | 52 X'0034' | PostScript Object Error: Resource used in object was not found by the interpreter | ❓ |
| IPDS-16-1152 | 53 X'0035' | PostScript Object Error: An overflow, underflow, or meaningless result occurred | ❓ |
| IPDS-16-1153 | 54 X'0036' | PostScript Object Error: Interpreter expected a mark on the stack; none was found | ❓ |
| IPDS-16-1154 | 55 X'0037' | PostScript Object Error: Internal error occurred within the interpreter | ❓ |
| IPDS-16-1155 | 56 X'0038' | PostScript Object Error: The interpreter's virtual memory has been exhausted | ❓ |
| IPDS-16-1156 | 57 X'0039' | PostScript Object Error: No output generated from EPS/PDF; check input data | ❓ |
| IPDS-16-1157 | PDF Object Errors** | ❓ |
| IPDS-16-1158 | 80 X'0050' | PDF Object Error: Failure to open a secure PDF document | ❓ |
| IPDS-16-1159 | 81 X'0051' | PDF Object Error: General failure to convert PDF to PostScript code | ❓ |
| IPDS-16-1160 | 82 X'0052' | PDF Object Error: Failure to enumerate fonts contained in PDF document | ❓ |
| IPDS-16-1161 | 83 X'0053' | PDF Object Error: Failure to open PDF document | ❓ |
| IPDS-16-1162 | TrueType/OpenType Object Errors** | Error codes in the range X'0100'–X'01FF' can apply to either TrueType/OpenType fonts or TrueType/OpenType collections. For these codes, the rightmost 4 bits contains the collection index value plus one. A value of X'0' indicates either that the object was a font or indicates that the collection index is not provided (because it is greater than 14). | ❓ |
| IPDS-16-1163 | 256–271 X'0100'–X'010F' | TrueType/OpenType Object Error: File read failure detected by the font rasterizer | ❓ |
| IPDS-16-1164 | 272–287 X'0110'–X'011F' | TrueType/OpenType Object Error: Font rasterizer was unable to understand (parse) the data in a font | ❓ |
| IPDS-16-1165 | 288–303 X'0120'–X'012F' | TrueType/OpenType Object Error: Font rasterizer failed to gather a font's font-level metrics | ❓ |
| IPDS-16-1166 | 304–319 X'0130'–X'013F' | TrueType/OpenType Object Error: The first four bytes of a font are invalid | ❓ |
| IPDS-16-1167 | 320–335 X'0140'–X'014F' | TrueType/OpenType Object Error: The printer control unit failed to read a portion of a font that should be present | ❓ |
| IPDS-16-1168 | 336–351 X'0150'–X'015F' | TrueType/OpenType Object Error: The printer control unit unexpectedly reached end of file while reading a font | ❓ |
| IPDS-16-1169 | 352–367 X'0160'–X'016F' | TrueType/OpenType Object Error: The first four bytes of a collection are invalid | ❓ |
| IPDS-16-1170 | 368–383 X'0170'–X'017F' | TrueType/OpenType Object Error: The required format 4 cmap is missing from a font | ❓ |
| IPDS-16-1171 | 384–399 X'0180'–X'018F' | TrueType/OpenType Object Error: An error that the printer control unit is not currently prepared to handle has occurred | ❓ |
| IPDS-16-1172 | TIFF Object Errors (X'0200'–X'02FF')** | ❓ |
| IPDS-16-1173 | 512 X'0200' | TIFF Object Error: An internal error was encountered while processing the image | ❓ |
| IPDS-16-1174 | 528 X'0210' | TIFF Object Error: Object contains invalid controls; see note 1 | ❓ |
| IPDS-16-1175 | 544 X'0220' | TIFF Object Error: Object contains invalid image data; see note 2 | ❓ |
| IPDS-16-1176 | 560 X'0230' | TIFF Object Error: Object contains unsupported image | ❓ |
| IPDS-16-1177 | 576 X'0240' | TIFF Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1178 | JPEG Object Errors (X'0300'–X'03FF')** | ❓ |
| IPDS-16-1179 | 768 X'0300' | JPEG Object Error: An internal error was encountered while processing the image | ❓ |
| IPDS-16-1180 | 784 X'0310' | JPEG Object Error: Object contains invalid controls; see note 1 | ❓ |
| IPDS-16-1181 | 800 X'0320' | JPEG Object Error: Object contains invalid image data; see note 2 | ❓ |
| IPDS-16-1182 | 816 X'0330' | JPEG Object Error: Object contains unsupported image | ❓ |
| IPDS-16-1183 | 832 X'0340' | JPEG Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1184 | JPEG2000 Object Errors (X'0400'–X'04FF')** | ❓ |
| IPDS-16-1185 | 1024 X'0400' | JPEG2000 Object Error: An internal error was encountered while processing the image | ❓ |
| IPDS-16-1186 | 1040 X'0410' | JPEG2000 Object Error: Object contains invalid controls; see note 1 | ❓ |
| IPDS-16-1187 | 1056 X'0420' | JPEG2000 Object Error: Object contains invalid image data; see note 2 | ❓ |
| IPDS-16-1188 | 1072 X'0430' | JPEG2000 Object Error: Object contains unsupported image | ❓ |
| IPDS-16-1189 | 1088 X'0440' | JPEG2000 Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1190 | GIF Object Errors (X'0500'–X'05FF')** | ❓ |
| IPDS-16-1191 | 1280 X'0500' | GIF Object Error: An internal error was encountered while processing the image | ❓ |
| IPDS-16-1192 | 1296 X'0510' | GIF Object Error: Object contains invalid controls; see note 1 | ❓ |
| IPDS-16-1193 | 1312 X'0520' | GIF Object Error: Object contains invalid image data; see note 2 | ❓ |
| IPDS-16-1194 | 1328 X'0530' | GIF Object Error: Object contains unsupported image | ❓ |
| IPDS-16-1195 | 1344 X'0540' | GIF Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1196 | PNG Object Errors (X'0600'–X'06FF')** | ❓ |
| IPDS-16-1197 | 1536 X'0600' | PNG Object Error: An internal error was encountered while processing the image | ❓ |
| IPDS-16-1198 | 1552 X'0610' | PNG Object Error: Object contains invalid controls; see note 1 | ❓ |
| IPDS-16-1199 | 1568 X'0620' | PNG Object Error: Object contains invalid image data; see note 2 | ❓ |
| IPDS-16-1200 | 1584 X'0630' | PNG Object Error: Object contains unsupported image | ❓ |
| IPDS-16-1201 | 1600 X'0640' | PNG Object Error: Image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1202 | AFP SVG Subset Object Errors (X'0700'–X'07FF')** | ❓ |
| IPDS-16-1203 | 1792 X'0700' | SVG Object Error: An internal error was encountered while processing the vector image | ❓ |
| IPDS-16-1204 | 1808 X'0710' | SVG Object Error: Error decompressing object | ❓ |
| IPDS-16-1205 | 1824 X'0720' | SVG Object Error: Content does not conform to the XML specification | ❓ |
| IPDS-16-1206 | 1840 X'0730' | SVG Object Error: Element or attribute encountered that is not part of the SVG DTD and that is not properly identified as being part of another namespace | ❓ |
| IPDS-16-1207 | 1856 X'0740' | SVG Object Error: Element has an attribute or property value that is not permissible according to the SVG specification | ❓ |
| IPDS-16-1208 | 1872 X'0750' | SVG Object Error: Situations that are described as being “in error” in the SVG specification | ❓ |
| IPDS-16-1209 | 1888 X'0760' | SVG Object Error: Object contains unsupported vector image | ❓ |
| IPDS-16-1210 | 1904 X'0770' | SVG Object Error: Vector image in the object exceeds the capabilities of the receiver; see note 3 | ❓ |
| IPDS-16-1211 | 1. Examples of invalid controls include: | ❓ |
| IPDS-16-1212 | Missing TIFF tag | ❓ |
| IPDS-16-1213 | Invalid or inconsistent TIFF tags | ❓ |
| IPDS-16-1214 | Invalid TIFF signature | ❓ |
| IPDS-16-1215 | Could not process data, or have to make assumptions | ❓ |
| IPDS-16-1216 | Required JPEG marker is missing | ❓ |
| IPDS-16-1217 | JPEG marker is invalid | ❓ |
| IPDS-16-1218 | JPEG markers are inconsistent | ❓ |
| IPDS-16-1219 | Invalid GIF signature | ❓ |
| IPDS-16-1220 | Invalid or missing GIF image descriptor | ❓ |
| IPDS-16-1221 | Invalid PNG signature | ❓ |
| IPDS-16-1222 | Missing PNG critical chunk | ❓ |
| IPDS-16-1223 | 2. Examples of invalid image data include: | ❓ |
| IPDS-16-1224 | Decompression failure | ❓ |
| IPDS-16-1225 | Data outside of range | ❓ |
| IPDS-16-1226 | 3. Examples of capability-exceeded errors include: | ❓ |
| IPDS-16-1227 | Image too large | ❓ |
| IPDS-16-1228 | Processing requires too much memory | ❓ |
| IPDS-16-1229 | Table 66. Method of Adjusting the Counters | ❓ |
| IPDS-16-1230 | Action codes X'01', X'19', X'1F' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Refer to “Page and Copy Counter Adjustments When a Data-Stream Exception Occurs”. | ❓ |
| IPDS-16-1231 | Action codes X'05', X'06' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1232 | Action code X'08' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Jam Recovery Page Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>No change<br>No change<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter | ❓ |
| IPDS-16-1233 | Action codes X'09', X'15', X'16', X'17', X'1A', X'1D', X'23' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1234 | Action code X'0A'<br>X'1B'<br>X'2B' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Jam Recovery Page Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>Set to Jam Recovery Page Counter<br>Set to Jam Recovery Copy Counter<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1235 | Action code X'0C' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | No change (Should not be incremented for page in error; that is, no partial page should be created.)<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1236 | Action code X'0D' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0 | ❓ |
| IPDS-16-1237 | Action code X'1E' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter unless duplexing is active for the page in which the exception occurs and the page that caused the exception is on the back side of a duplex sheet. In this case, the back-side pages are discarded and the Received Page Counter is set to the Committed Page Counter plus the number of pages on the front side. The host must issue an XOH-PBD command to ensure that the counters are accurately adjusted.<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1238 | Action code X'22' | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer<br>Defined by the printer | ❓ |
| IPDS-16-1239 | XOA Discard Buffered Data command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Committed Page Counter<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change<br>No change | ❓ |
| IPDS-16-1240 | XOA Discard Unstacked Pages command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to Stacked Page Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>Set to Stacked Page Counter<br>Set to Stacked Copy Counter<br>No change<br>No change | ❓ |
| IPDS-16-1241 | Normal counter wrap (on a per-counter basis) | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0<br>Set to 0 | ❓ |
| IPDS-16-1242 | XOH Page Counters Control command is processed | Received Page Counter<br>Committed Page Counter<br>Committed Copy Counter<br>Operator Viewing Page Counter<br>Operator Viewing Copy Counter<br>Jam Recovery Page Counter<br>Jam Recovery Copy Counter<br>Stacked Page Counter<br>Stacked Copy Counter | Refer to “XOH Page Counters Control”. | ❓ |
| IPDS-16-1243 | Table 67. Method of Adjusting the Counters When a Data-Stream Exception Occurs | ❓ |
| IPDS-16-1244 | Action code X'01', X'1F', or X'19' and the page is printed.<br><br>For action code X'19', the host must issue an XOH-PBD command to ensure that the counters are accurately adjusted. | Received Page Counter | Reflects the last page received from the host, unless the error occurred on the last page on a sheet. The received page counter is incremented for the last page on a sheet after all copy subgroups are processed for all pages on the sheet. | ❓ |
| IPDS-16-1245 | Committed Page Counter | No Change | ❓ |
| IPDS-16-1246 | Committed Copy Counter | Reflects any committed copies resulting from prior copy subgroups. If the error occurred in the last page on the sheet, reflects committed copies from the copy subgroup in error. Since no copies have been discarded, additional copies might also be buffered between the received page station and the committed page station. | ❓ |
| IPDS-16-1247 | Operator Viewing Page Counter | No Change | ❓ |
| IPDS-16-1248 | Operator Viewing Copy Counter | No Change | ❓ |
| IPDS-16-1249 | Jam Recovery Page Counter | No Change | ❓ |
| IPDS-16-1250 | Jam Recovery Copy Counter | No Change | ❓ |
| IPDS-16-1251 | Stacked Page Counter | No Change | ❓ |
| IPDS-16-1252 | Stacked Copy Counter | No Change | ❓ |
| IPDS-16-1253 | Action code X'01', or X'1F', and the page is not printed. | Received Page Counter | If a synchronous data stream exception occurred in the first copy subgroup (or, if duplexing, the second copy subgroup), the received page counter includes all received pages prior to the error page. If a synchronous data stream exception occurred in a subsequent copy subgroup, the received page counter includes all but the last page on the sheet. | ❓ |
| IPDS-16-1254 | Committed Page Counter | No Change | ❓ |
| IPDS-16-1255 | Committed Copy Counter | Reflects any committed copies resulting from prior error-free copy subgroups. Since prior error-free copies have not been discarded, additional copies might also be buffered between the received page station and the committed page station. | ❓ |
| IPDS-16-1256 | Operator Viewing Page Counter | No Change | ❓ |
| IPDS-16-1257 | Operator Viewing Copy Counter | No Change | ❓ |
| IPDS-16-1258 | Jam Recovery Page Counter | No Change | ❓ |
| IPDS-16-1259 | Jam Recovery Copy Counter | No Change | ❓ |
| IPDS-16-1260 | Stacked Page Counter | No Change | ❓ |
| IPDS-16-1261 | Stacked Copy Counter | No Change | ❓ |
| IPDS-16-1262 | Action code X'19' and the page is not printed.<br><br>For action code X'19', the host must issue an XOH-PBD command to ensure that the counters are accurately adjusted. | Received Page Counter | If an asynchronous data stream exception occurred and if there were any error free copy subgroups committed, the received page counter reflects all of the pages on the sheet. If there were no previous error free copy subgroups, it reflects none of the pages on the sheet. | ❓ |
| IPDS-16-1263 | Committed Page Counter | No Change | ❓ |
| IPDS-16-1264 | Committed Copy Counter | Reflects any committed copies resulting from prior error-free copy subgroups. Since prior error-free copies have not been discarded, additional copies might also be buffered between the received page station and the committed page station. | ❓ |
| IPDS-16-1265 | Operator Viewing Page Counter | No Change | ❓ |
| IPDS-16-1266 | Operator Viewing Copy Counter | No Change | ❓ |
| IPDS-16-1267 | Jam Recovery Page Counter | No Change | ❓ |
| IPDS-16-1268 | Jam Recovery Copy Counter | No Change | ❓ |
| IPDS-16-1269 | Stacked Page Counter | No Change | ❓ |
| IPDS-16-1270 | Stacked Copy Counter | No Change | ❓ |
| IPDS-16-1271 | 0 0 0 0 0 | ❓ |
| IPDS-16-1272 | 0 0 0 0 0 | ❓ |
| IPDS-16-1273 | 0 0 0 0 0 | ❓ |
| IPDS-16-1274 | 1 0 0 0 0 | ❓ |
| IPDS-16-1275 | 1 0 1 0 0 | ❓ |
| IPDS-16-1276 | 1 0 2 0 0 | ❓ |
| IPDS-16-1277 | 1 0 3 0 0 | ❓ |
| IPDS-16-1278 | 1 1 0 0 0 | ❓ |
| IPDS-16-1279 | 1 1 0 0 1 | ❓ |
| IPDS-16-1280 | 1 1 0 0 2 | ❓ |
| IPDS-16-1281 | 1 1 0 0 3 | ❓ |
| IPDS-16-1282 | 1 1 0 1 0 | ❓ |
| IPDS-16-1283 | 1 0 0 0 0 | ❓ |
| IPDS-16-1284 | 2 0 0 0 0 | ❓ |
| IPDS-16-1285 | 2 0 0 0 0 | ❓ |
| IPDS-16-1286 | 2 2 0 0 0 | ❓ |
| IPDS-16-1287 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1288 | 2 have been stacked | ❓ |
| IPDS-16-1289 | 2 2 0 2 0 | ❓ |
| IPDS-16-1290 | 0 0 0 0 0 | ❓ |
| IPDS-16-1291 | 0 0 0 0 0 | ❓ |
| IPDS-16-1292 | 0 0 0 0 0 | ❓ |
| IPDS-16-1293 | 0 0 0 0 0 | ❓ |
| IPDS-16-1294 | 1 0 0 0 0 | ❓ |
| IPDS-16-1295 | 1 0 0 0 0 | ❓ |
| IPDS-16-1296 | 1 0 0 0 0 | ❓ |
| IPDS-16-1297 | 1 0 0 0 0 | ❓ |
| IPDS-16-1298 | 2 0 0 0 0 | ❓ |
| IPDS-16-1299 | 2 2 0 0 0 | ❓ |
| IPDS-16-1300 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1301 | 2 have been stacked | ❓ |
| IPDS-16-1302 | 2 2 0 2 0 | ❓ |
| IPDS-16-1303 | 1 0 0 0 0 | ❓ |
| IPDS-16-1304 | 1 0 0 0 0 | ❓ |
| IPDS-16-1305 | 1 0 0 0 0 | ❓ |
| IPDS-16-1306 | 1 0 0 0 0 | ❓ |
| IPDS-16-1307 | 1 0 0 0 0 | ❓ |
| IPDS-16-1308 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1309 | 2 have been committed | ❓ |
| IPDS-16-1310 | 1 0 2 0 0 | ❓ |
| IPDS-16-1311 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1312 | 2 have been stacked | ❓ |
| IPDS-16-1313 | 1 0 2 0 2 | ❓ |
| IPDS-16-1314 | 1 0 2 0 2 | ❓ |
| IPDS-16-1315 | 2 0 2 0 2 | ❓ |
| IPDS-16-1316 | 2 through copy subgroups 3 and | ❓ |
| IPDS-16-1317 | 4 have been committed | ❓ |
| IPDS-16-1318 | 2 2 0 0 2 | ❓ |
| IPDS-16-1319 | 2 through copy subgroups 3 and | ❓ |
| IPDS-16-1320 | 4 have been stacked | ❓ |
| IPDS-16-1321 | 2 2 0 2 0 | ❓ |
| IPDS-16-1322 | 1 0 0 0 0 | ❓ |
| IPDS-16-1323 | 2 0 0 0 0 | ❓ |
| IPDS-16-1324 | 0 0 0 0 0 | ❓ |
| IPDS-16-1325 | 0 0 0 0 0 | ❓ |
| IPDS-16-1326 | 0 0 0 0 0 | ❓ |
| IPDS-16-1327 | 1 0 0 0 0 | ❓ |
| IPDS-16-1328 | 2 0 0 0 0 | ❓ |
| IPDS-16-1329 | 1 0 0 0 0 | ❓ |
| IPDS-16-1330 | 1 0 0 0 0 | ❓ |
| IPDS-16-1331 | 0 0 0 0 0 | ❓ |
| IPDS-16-1332 | 1 0 0 0 0 | ❓ |
| IPDS-16-1333 | 1 0 0 0 0 | ❓ |
| IPDS-16-1334 | 1 0 0 0 0 | ❓ |
| IPDS-16-1335 | 2 0 0 0 0 | ❓ |
| IPDS-16-1336 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1337 | 2 have been committed | ❓ |
| IPDS-16-1338 | 2 0 2 0 0 | ❓ |
| IPDS-16-1339 | 1 0 2 0 0 | ❓ |
| IPDS-16-1340 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1341 | 2 have been stacked | ❓ |
| IPDS-16-1342 | 1 0 2 0 2 | ❓ |
| IPDS-16-1343 | 1 0 2 0 2 | ❓ |
| IPDS-16-1344 | 0 0 2 0 2 | ❓ |
| IPDS-16-1345 | 2 2 0 2 0 | ❓ |
| IPDS-16-1346 | 0 0 0 0 0 | ❓ |
| IPDS-16-1347 | 0 0 0 0 0 | ❓ |
| IPDS-16-1348 | 0 0 0 0 0 | ❓ |
| IPDS-16-1349 | 0 0 0 0 0 | ❓ |
| IPDS-16-1350 | 0 0 0 0 0 | ❓ |
| IPDS-16-1351 | 0 0 1 0 0 | ❓ |
| IPDS-16-1352 | 0 0 2 0 0 | ❓ |
| IPDS-16-1353 | 0 0 3 0 0 | ❓ |
| IPDS-16-1354 | 0 0 3 0 1 | ❓ |
| IPDS-16-1355 | 0 0 3 0 2 | ❓ |
| IPDS-16-1356 | 0 0 3 0 3 | ❓ |
| IPDS-16-1357 | 0 0 3 0 3 | ❓ |
| IPDS-16-1358 | 1 0 3 0 3 | ❓ |
| IPDS-16-1359 | 1 1 0 0 3 | ❓ |
| IPDS-16-1360 | 1 1 0 1 0 | ❓ |
| IPDS-16-1361 | 0 0 0 0 0 | ❓ |
| IPDS-16-1362 | 0 0 0 0 0 | ❓ |
| IPDS-16-1363 | 0 0 0 0 0 | ❓ |
| IPDS-16-1364 | 1 0 0 0 0 | ❓ |
| IPDS-16-1365 | 1 0 1 0 0 | ❓ |
| IPDS-16-1366 | 1 0 2 0 0 | ❓ |
| IPDS-16-1367 | 1 0 3 0 0 | ❓ |
| IPDS-16-1368 | 1 0 3 0 1 | ❓ |
| IPDS-16-1369 | 0 0 3 0 1 | ❓ |
| IPDS-16-1370 | 0 0 3 0 2 | ❓ |
| IPDS-16-1371 | 0 0 3 0 3 | ❓ |
| IPDS-16-1372 | 0 0 3 0 3 | ❓ |
| IPDS-16-1373 | 0 0 3 0 3 | ❓ |
| IPDS-16-1374 | 1 1 0 1 0 | ❓ |
| IPDS-16-1375 | 1 0 0 0 0 | ❓ |
| IPDS-16-1376 | 1 0 0 0 0 | ❓ |
| IPDS-16-1377 | 1 0 0 0 0 | ❓ |
| IPDS-16-1378 | 2 0 0 0 0 | ❓ |
| IPDS-16-1379 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1380 | 2 have been committed, discard | ❓ |
| IPDS-16-1381 | 1 0 2 0 0 | ❓ |
| IPDS-16-1382 | 2 through copy subgroups 3 and | ❓ |
| IPDS-16-1383 | 4 have been committed | ❓ |
| IPDS-16-1384 | 2 2 0 0 0 | ❓ |
| IPDS-16-1385 | 2 2 0 0 0 | ❓ |
| IPDS-16-1386 | 1 0 0 0 0 | ❓ |
| IPDS-16-1387 | 1 0 0 0 0 | ❓ |
| IPDS-16-1388 | 1 0 0 0 0 | ❓ |
| IPDS-16-1389 | 2 0 0 0 0 | ❓ |
| IPDS-16-1390 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1391 | 2 have been committed | ❓ |
| IPDS-16-1392 | 2 0 2 0 0 | ❓ |
| IPDS-16-1393 | 0 0 2 0 0 | ❓ |
| IPDS-16-1394 | 2 through copy subgroups 1 and | ❓ |
| IPDS-16-1395 | 2 have been stacked | ❓ |
| IPDS-16-1396 | 0 0 2 0 2 | ❓ |
| IPDS-16-1397 | 0 0 2 0 2 | ❓ |
| IPDS-16-1398 | 0 0 2 0 2 | ❓ |
| IPDS-16-1399 | Table 68. Retired Non-IPDS Action Codes | ❓ |
| IPDS-16-1400 | X'00' | **No error outstanding**<br>Retired item 83; used in channel-attached printers<br>Software should redrive the channel with the failing CCW. This action code is specified if the printer operator clears a temporary intervention required condition and readies the printer between the time a Unit Check is posted and the Basic Sense command is received by the host. The printer was in the ready state when this action code was generated. | ❓ |
| IPDS-16-1401 | X'02' | **Operator intervention with OBR record**<br>Retired item 84; used in channel-attached and TCP/IP-attached printers<br>An operator intervention condition has occurred that requires an OBR record. Supply a system operator message indicating that operator intervention is required and generate an OBR record. The printer was in the not ready state when this action code was generated. After the printer is made ready, restart the channel program with the failing CCW (or restart the flow of TCP/IP data). | ❓ |
| IPDS-16-1402 | X'03' | **Operator intervention without OBR record**<br>Retired item 85; used in channel-attached and TCP/IP-attached printers<br>An operator intervention condition has occurred that does not require an OBR record. Supply a system operator message indicating that operator intervention is required. The printer was in the not ready state when this action code was generated. After the printer is made ready, restart the channel program with the failing CCW (or restart the flow of TCP/IP data). | ❓ |
| IPDS-16-1403 | X'04' | **Channel error**<br>Retired item 86; used in channel-attached printers<br>A channel error has occurred. Generate an OBR record and retry the operation at least once. If retry fails, provide a system operator message that shows an unrecoverable error has occurred and notify presentation software. The printer might be in either the ready or not ready state when this action code is received. | ❓ |
| IPDS-16-1404 | X'07' | **Retry Error Log full**<br>Retired item 99; used in 3800-3,6,8 printers<br>Software should retrieve the retry error log entries with a Sense Error Log CCW, generate an MDR record to save the retry error log information, and restart the channel program with the failing CCW. | ❓ |
| IPDS-16-1405 | X'0B' | **Process power error**<br>Retired item 100; used in 3800-3,6,8 printers<br>Software should generate an OBR record, issue the XOA-DBD command, provide an operator message that indicates an error has occurred, and redrive on the page at the jam recovery counter plus one. | ❓ |
| IPDS-16-1406 | X'0E' | **Not Enough Storage, page printed using the accumulator feature**<br>Retired item 101; used in 3800-3,6,8 printers<br>Delete overlays, page segments, or fonts that are not required for the job. | ❓ |
| IPDS-16-1407 | X'0F' | **Accumulator read check**<br>Retired item 102; used in 3800-3,6,8 printers<br>Software should generate an OBR record, issue the XOA-DBD command, and redrive on the page at the committed counter plus one. If an electronic overlay was lost, retransmit the electronic overlay and then retransmit the lost pages. | ❓ |
| IPDS-16-1408 | X'10' | **Reload Electronic Overlay or Base Page**<br>Retired item 103; used in 3800-3,6,8 printers<br>Terminate the print job. | ❓ |
| IPDS-16-1409 | X'11' | **Count continuous-forms stacker fold wrong errors**<br>Retired item 104; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 6, and restart the channel program with the failing CCW. | ❓ |
| IPDS-16-1410 | X'12' | **Count burster input checks**<br>Retired item 105; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 7, and restart the channel program with the failing CCW. | ❓ |
| IPDS-16-1411 | X'13' | **Count no burst checks**<br>Retired item 106; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 8, and restart the channel program with the failing CCW. | ❓ |
| IPDS-16-1412 | X'14' | **Count burster-trimmer-stacker stacker/trimmer checks**<br>Retired item 107; used in 3800-3,6,8 printers<br>Supply an operator message, increase SDR counter 9, and restart the channel program with the failing CCW. | ❓ |
| IPDS-16-1413 | X'18' | **Transparent error**<br>Retired item 87; used in channel-attached printers | ❓ |
| IPDS-16-1414 | X'1C' | **Sense Extended CCW required**<br>Retired item 88; used in channel-attached printers<br>An exception has occurred that requires an IPDS NACK to be sent to host software. The IPDS NACK is obtained when the host software issues a Sense Extended CCW. Purge the channel program, obtain and send the Acknowledge Reply to presentation software. The printer might either be in the ready or not ready state when this action code is received. | ❓ |
| IPDS-16-1415 | X'24' | **Printer not assigned**<br>Retired item 89; used in Serial-Channel-attached printers<br>The printer has not been assigned to the host. Reissue this CCW following the successful execution of an Assign CCW. The printer might either be in the ready or not ready state when this action code is received. | ❓ |
| IPDS-16-1416 | X'25' | **Printer assigned elsewhere**<br>Retired item 90; used in Serial-Channel-attached printers<br>The printer is assigned to another host. Issue an Assign CCW at a later time or when the printer is known to be available. The printer might either be in the ready or not ready state when this action code is received. | ❓ |
| IPDS-16-1417 | X'4D' | **Resetting event**<br>Retired item 91; used in Serial-Channel-attached printers<br>A resetting event has occurred at the printer. Reestablish the path group ID and path mode and reissue the failing CCW. The printer might either be in the ready or not ready state when this action code is received. | ❓ |
| IPDS-16-1418 | 8003..00 Retired (abnormal end of data | ❓ |
| IPDS-16-1419 | 8005..00 Retired (invalid channel command) | ❓ |
| IPDS-16-1420 | 8005..00 Retired (invalid channel command | ❓ |
| IPDS-16-1421 | 8006..00 Retired (printer not assigned) | ❓ |
| IPDS-16-1422 | 8003..00 • 8006..00 | ❓ |
| IPDS-16-1423 | 5010..00 Retired (printer hardware exception) | ❓ |
| IPDS-16-1424 | 5010..00 • 5010..00 | ❓ |
| IPDS-16-1425 | 4005..00 Retired (empty fuser oil supply) | ❓ |
| IPDS-16-1426 | 4011..00 Retired (suppressed jam recovery) | ❓ |
| IPDS-16-1427 | 4012..00 Retired (attempt to print an undefined | ❓ |
| IPDS-16-1428 | An operator intervention condition has occurred because | ❓ |
| IPDS-16-1429 | An operator intervention condition has occurred because | ❓ |
| IPDS-16-1430 | The operator intervention condition might have been | ❓ |
| IPDS-16-1431 | 4005 Hi-Lite Color Post-Processor Device). | ❓ |
| IPDS-16-1432 | 4031..00 Retired (paper length check) | ❓ |
| IPDS-16-1433 | 4033..00 Retired (paper width check) | ❓ |
| IPDS-16-1434 | 4050..00 Retired (fuser oil supply empty) | ❓ |
| IPDS-16-1435 | 4051..00 Retired (developer mix needs changing) | ❓ |
| IPDS-16-1436 | 4052..00 Retired (oiler belt needs changing) | ❓ |
| IPDS-16-1437 | 4053..00 Retired (toner collector full) | ❓ |
| IPDS-16-1438 | 4054..00 Retired (fine filter needs changing) | ❓ |
| IPDS-16-1439 | 2001..01 Retired (link adapter A device-level error) | ❓ |
| IPDS-16-1440 | 2001..02 Retired (link adapter B device-level error) | ❓ |
| IPDS-16-1441 | 2002..01 Retired (link adapter A link-level error) | ❓ |
| IPDS-16-1442 | 2002..02 Retired (link adapter B link-level error) | ❓ |
| IPDS-16-1443 | 2011..00 Retired (channel command parity error) | ❓ |
| IPDS-16-1444 | 2012..00 Retired (channel data parity error) | ❓ |
| IPDS-16-1445 | 3800 printer. | ❓ |
| IPDS-16-1446 | 2001..01 • 2012..00 | ❓ |
| IPDS-16-1447 | Strip buffer overrun | ❓ |
| IPDS-16-1448 | Not enough accumulator storage | ❓ |
| IPDS-16-1449 | No accumulator feature installed | ❓ |
| IPDS-16-1450 | CGEN RPS cycle-steal end status check | ❓ |
| IPDS-16-1451 | Missing interrupt from CGEN | ❓ |
| IPDS-16-1452 | No response timeout | ❓ |
| IPDS-16-1453 | Unexpected accumulator feature interrupt | ❓ |
| IPDS-16-1454 | The physical-page size and printable-area size might be | ❓ |
| IPDS-16-1455 | The default physical-page size and printable-area size | ❓ |
| IPDS-16-1456 | Data truncation might occur without being reported. | ❓ |
| IPDS-16-1457 | 3800 printer and use an IBM 3800 printer-specific | ❓ |
| IPDS-16-1458 | 3800 sense-data formats. | ❓ |
| IPDS-16-1459 | 0824..00 Retired (position check; print position is | ❓ |
| IPDS-16-1460 | 0825..00 Retired (position check; print position is | ❓ |
| IPDS-16-1461 | 0826..00 Retired (position check; print position is | ❓ |
| IPDS-16-1462 | 0827..00 Retired (position check; print position is | ❓ |
| IPDS-16-1463 | 0830..00 Retired (position check; print position is | ❓ |
| IPDS-16-1464 | 0824..00 • 0830..00 | ❓ |
| IPDS-16-1465 | 0831..00 Retired (position check; print position is | ❓ |
| IPDS-16-1466 | 0834..00 Retired (position check; baseline extent for | ❓ |
| IPDS-16-1467 | 0835..00 Retired (position check; baseline extent for | ❓ |
| IPDS-16-1468 | 0836..00 Retired (position check; baseline extent for | ❓ |
| IPDS-16-1469 | 0837..00 Retired (position check; baseline extent for | ❓ |
| IPDS-16-1470 | 0838..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1471 | 0839..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1472 | 0831..00 • 083E..00 | ❓ |
| IPDS-16-1473 | 0842..00 Retired (position check; image extends | ❓ |
| IPDS-16-1474 | 0843..00 Retired (position check; image extends | ❓ |
| IPDS-16-1475 | 0846..00 Retired (position check; image extends | ❓ |
| IPDS-16-1476 | 0847..00 Retired (position check; image extends | ❓ |
| IPDS-16-1477 | 0850..00 Retired (accumulator check; paper or page | ❓ |
| IPDS-16-1478 | 0851..00 Retired (accumulator check; paper or page | ❓ |
| IPDS-16-1479 | 0852..00 Retired (accumulator check; base page | ❓ |
| IPDS-16-1480 | 0853..00 Retired (accumulator check; raster overlay | ❓ |
| IPDS-16-1481 | 0854..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1482 | 0855..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1483 | 0856..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1484 | 0857..00 Retired (position check; rule width extends | ❓ |
| IPDS-16-1485 | 0858..00 Retired (position check; rule length | ❓ |
| IPDS-16-1486 | 0859..00 Retired (position check; rule length | ❓ |
| IPDS-16-1487 | 0864..00 Retired (position check; addressability | ❓ |
| IPDS-16-1488 | 0865..00 Retired (position check; addressability | ❓ |
| IPDS-16-1489 | 0866..00 Retired (position check; addressability | ❓ |
| IPDS-16-1490 | 0867..00 Retired (position check; addressability | ❓ |
| IPDS-16-1491 | 0868..00 Retired (too little control store while | ❓ |
| IPDS-16-1492 | 0854..00 • 0868..00 | ❓ |
| IPDS-16-1493 | 0868..00 • 0868..00 | ❓ |
| IPDS-16-1494 | 0500..02 Retired (reserved bits or bytes are not | ❓ |
| IPDS-16-1495 | 0500..02 • 0500..02 | ❓ |
| IPDS-16-1496 | 0401..00 Retired (channel overrun) | ❓ |
| IPDS-16-1497 | 0401..01 Retired (link adapter A overrun) | ❓ |
| IPDS-16-1498 | 0401..02 Retired (link adapter B overrun) | ❓ |
| IPDS-16-1499 | 0402..00 Retired (attempt to print symbol or HRI | ❓ |
| IPDS-16-1500 | 4234 printers | ❓ |
| IPDS-16-1501 | 4234 printers | ❓ |
| IPDS-16-1502 | 0401..00 • 040D..00 | ❓ |
| IPDS-16-1503 | 0300..05 Retired (segment call stack full) | ❓ |
| IPDS-16-1504 | 0300..06 Retired (homogeneous coordinate | ❓ |
| IPDS-16-1505 | A draw operation with Homogeneous specified is | ❓ |
| IPDS-16-1506 | A Set W Coordinate order is requested that sets the w | ❓ |
| IPDS-16-1507 | The segment call stack has no values above the return | ❓ |
| IPDS-16-1508 | The segment call stack is empty when in a chained | ❓ |
| IPDS-16-1509 | 0307..00 Retired (called segment not found) | ❓ |
| IPDS-16-1510 | 0324..00 Retired (model transform matrix element | ❓ |
| IPDS-16-1511 | 0327..00 Retired (invalid viewing window) | ❓ |
| IPDS-16-1512 | 0327..01 Retired (invalid viewing window) | ❓ |
| IPDS-16-1513 | 0327..02 Retired (invalid viewing window) | ❓ |
| IPDS-16-1514 | 0331..00 Retired (viewing transform matrix element | ❓ |
| IPDS-16-1515 | 0331..02 Retired (invalid viewing transform) | ❓ |
| IPDS-16-1516 | 0332..00 Retired (invalid segment boundary) | ❓ |
| IPDS-16-1517 | 0300..05 • 0332..00 | ❓ |
| IPDS-16-1518 | 0332..02 Retired (invalid segment boundary) | ❓ |
| IPDS-16-1519 | 0335..00 Retired (invalid character shear) | ❓ |
| IPDS-16-1520 | 0360..01 Retired (area nested call error) | ❓ |
| IPDS-16-1521 | 0370..81 Retired (invalid segment length) | ❓ |
| IPDS-16-1522 | 0370..83 Retired (invalid segment length for | ❓ |
| IPDS-16-1523 | 0370..84 Retired (invalid segment identifier for | ❓ |
| IPDS-16-1524 | 0332..02 • 03C6..02 | ❓ |
| IPDS-16-1525 | 3820 printer), X'0290..03', X'0291..03', X'02EA..01', | ❓ |
| IPDS-16-1526 | 0200..02 Retired (not enough raster-pattern storage | ❓ |
| IPDS-16-1527 | 0200..03 Retired (character exceeds presentation | ❓ |
| IPDS-16-1528 | 4234 printers | ❓ |
| IPDS-16-1529 | 0201..01 Retired (Set Text Orientation without Set | ❓ |
| IPDS-16-1530 | 0201..02 Retired (not enough control storage) | ❓ |
| IPDS-16-1531 | 0203..01 Retired (factored text control location | ❓ |
| IPDS-16-1532 | 0204..03 Retired (for PTOCA) | ❓ |
| IPDS-16-1533 | 0207..01 Retired (Begin Suppression found within | ❓ |
| IPDS-16-1534 | 0209..01 Retired (factored text control location | ❓ |
| IPDS-16-1535 | 0211..02 Retired (X | ❓ |
| IPDS-16-1536 | 0213..02 Retired (font not loaded) | ❓ |
| IPDS-16-1537 | 0200..02 • 0213..02 | ❓ |
| IPDS-16-1538 | 0216..02 Retired (invalid Delete Font command) | ❓ |
| IPDS-16-1539 | 0218..01 Retired (source string length not valid) | ❓ |
| IPDS-16-1540 | 0220..03 Retired (invalid VSP code) | ❓ |
| IPDS-16-1541 | 0223..01 Retired (not enough RPS) | ❓ |
| IPDS-16-1542 | 0224..01 Retired (not enough control storage) | ❓ |
| IPDS-16-1543 | 0224..02 Retired (one or both inline offset values | ❓ |
| IPDS-16-1544 | 0225..01 Retired (not enough raster-pattern storage | ❓ |
| IPDS-16-1545 | 0225..02 Retired (invalid inline offset value) | ❓ |
| IPDS-16-1546 | 0226..01 Retired (not enough raster-pattern storage | ❓ |
| IPDS-16-1547 | 0227..01 Retired (not enough control storage for | ❓ |
| IPDS-16-1548 | 0228..01 Retired (not enough control storage for | ❓ |
| IPDS-16-1549 | 0216..02 • 022C..02 | ❓ |
| IPDS-16-1550 | 0230..01 Retired (not enough control storage for | ❓ |
| IPDS-16-1551 | 0230..02 Retired (invalid font constant character | ❓ |
| IPDS-16-1552 | 0231..02 Retired (invalid font orientation count) | ❓ |
| IPDS-16-1553 | 0235..01 Retired (invalid forms flash value) | ❓ |
| IPDS-16-1554 | 0235..02 Retired (invalid font orientation) | ❓ |
| IPDS-16-1555 | 0236..02 Retired (invalid font baseline offset) | ❓ |
| IPDS-16-1556 | 0237..02 Retired (invalid font orientation flags) | ❓ |
| IPDS-16-1557 | 0238..02 Retired (double-byte fonts not supported ) | ❓ |
| IPDS-16-1558 | 0241..02 Retired (double-byte font section ID too | ❓ |
| IPDS-16-1559 | 0242..02 Retired (unmatched font control values) | ❓ |
| IPDS-16-1560 | 0250..01 Retired (first pel location absolute B | ❓ |
| IPDS-16-1561 | 0251..01 Retired (first pel location relative I value | ❓ |
| IPDS-16-1562 | 0252..01 Retired (first pel location relative B value | ❓ |
| IPDS-16-1563 | 0252..02 Retired (too many Load Font Index | ❓ |
| IPDS-16-1564 | 0253..02 Retired (Load Font Index command CCW | ❓ |
| IPDS-16-1565 | 0254..00 Retired (Y | ❓ |
| IPDS-16-1566 | 0254..10 Retired (control-sequence prefix code | ❓ |
| IPDS-16-1567 | 0259..01 Retired (invalid or unsupported resource | ❓ |
| IPDS-16-1568 | 0259..02 Retired (invalid HAID in a LHL command) | ❓ |
| IPDS-16-1569 | 0266..02 Retired (invalid Load Page Description | ❓ |
| IPDS-16-1570 | 3820 printers | ❓ |
| IPDS-16-1571 | 0267..02 Retired (invalid Load Page Description | ❓ |
| IPDS-16-1572 | 0271..02 Retired (invalid Load Page Description | ❓ |
| IPDS-16-1573 | 0272..01 Retired (raster overlay was specified | ❓ |
| IPDS-16-1574 | 0273..01 Retired (raster overlay already loaded) | ❓ |
| IPDS-16-1575 | 0274..01 Retired (fonts named in font equivalence | ❓ |
| IPDS-16-1576 | 0275..01 Retired (too little control storage available) | ❓ |
| IPDS-16-1577 | 0278..02 Retired (factored text control record ended | ❓ |
| IPDS-16-1578 | 0279..02 Retired (factored text control location | ❓ |
| IPDS-16-1579 | 0259..01 • 0279..02 | ❓ |
| IPDS-16-1580 | 0281..02 Retired (inline rule thickness less than one | ❓ |
| IPDS-16-1581 | 0283..02 Retired (baseline rule thickness outside | ❓ |
| IPDS-16-1582 | 0284..02 Retired (baseline rule thickness less than | ❓ |
| IPDS-16-1583 | 0285..02 Retired (baseline rule length outside valid | ❓ |
| IPDS-16-1584 | 0290..02 Retired (Xp adjustment range value outside | ❓ |
| IPDS-16-1585 | 0290..03 Retired (invalid or unsupported value for | ❓ |
| IPDS-16-1586 | 0291..03 Retired (for PTOCA) | ❓ |
| IPDS-16-1587 | 0281..02 • 02EC..01 | ❓ |
| IPDS-17-001 | function for text, IO-Image, graphics, and bar code data. | ❓ |
| IPDS-17-002 | Device-Control command set | ❓ |
| IPDS-17-003 | Text command set | ❓ |
| IPDS-17-004 | IM-Image command set | ❓ |
| IPDS-17-005 | IO-Image command set | ❓ |
| IPDS-17-006 | Graphics command set | ❓ |
| IPDS-17-007 | Bar Code command set | ❓ |
| IPDS-17-008 | Object-Container command set | ❓ |
| IPDS-17-009 | Metadata command set | ❓ |
| IPDS-17-010 | Page-Segment command set | ❓ |
| IPDS-17-011 | Overlay command set | ❓ |
| IPDS-17-012 | Loaded-Font command set | ❓ |
| IPDS-17-013 | pages are supported for TrueType/OpenType fonts, but the LF3 subset is not supported. | ❓ |
| IPDS-17-014 | architectures and are simply registered in the IPDS architecture. Table 69 summarizes this. | ❓ |
| IPDS-17-015 | Device Control | control printer | DC1 | none | ❓ |
| IPDS-17-016 | Text | print text data | TX1 | PT1, PT2, PT3, PT4 | PTOCA | ❓ |
| IPDS-17-017 | IM Image | print IM-Image data | IM1 | IMD1 | IPDS | ❓ |
| IPDS-17-018 | IO Image | print IO-Image data | IO1 | FS10, FS11, FS14, FS40, FS42, FS45, FS48 | IOCA | ❓ |
| IPDS-17-019 | Graphics | print graphics data | GR1 | DR/2V0, GRS3 | GOCA | ❓ |
| IPDS-17-020 | Bar Code | print bar code data | BC1 | BCD1, BCD2 | BCOCA | ❓ |
| IPDS-17-021 | Object Container | control object containers | OC1 | none | Specific to object container | ❓ |
| IPDS-17-022 | Metadata | control metadata | MO1 | MS1 | MOCA | ❓ |
| IPDS-17-023 | Page Segment | control page segments | PS1 | none | ❓ |
| IPDS-17-024 | Overlay | control overlays | OL1 | none | ❓ |
| IPDS-17-025 | Loaded Font | control coded fonts | LF1, LF2, LF3, LF4 | none | ❓ |
| IPDS-17-026 | IM Image | ❓ |
| IPDS-17-027 | IO Image | ❓ |
| IPDS-17-028 | Graphics | ❓ |
| IPDS-17-029 | Bar Code | ❓ |
| IPDS-17-030 | Metadata | ❓ |
| IPDS-17-031 | PT4, defined by the PTOCA architecture. | ❓ |
| IPDS-17-032 | Implement the DC1 subset of the device control command set. | ❓ |
| IPDS-17-033 | Implement at least one of the following IPDS command set subsets: | ❓ |
| IPDS-17-034 | Generate mandatory IPDS exceptions in accordance with the following rules: | ❓ |
| IPDS-17-035 | All commands generated by the presentation services must conform to the IPDS state diagram. | ❓ |
| IPDS-17-036 | The syntax for all commands generated by the presentation services must conform with that defined by the | ❓ |
| IPDS-17-037 | Accept syntactically-correct Acknowledge Replies and process the data contained therein, including: | ❓ |
| IPDS-17-038 | can support additional tower levels, and additional optional elements of the data tower. | ❓ |
| IPDS-17-039 | Device Control command set at the DC1 level | ❓ |
| IPDS-17-040 | Text command set at the TX1 level plus support for the PTOCA PT3 subset, including the following: | ❓ |
| IPDS-17-041 | IO-Image command set at the IO1 level plus support for the FS10 and FS45 function sets (an FS45 vector | ❓ |
| IPDS-17-042 | Graphics command set at the GR1 level plus support for the GOCA GRS3 subset | ❓ |
| IPDS-17-043 | Bar Code command set at the BC1 level plus support for the BCOCA BCD2 subset | ❓ |
| IPDS-17-044 | Object Container command set at the OC1 level plus support for the following object types: | ❓ |
| IPDS-17-045 | Page Segment command set at the PS1 level | ❓ |
| IPDS-17-046 | Overlay command set at the OL1 level | ❓ |
| IPDS-17-047 | Code pages (identified by support for the Loaded-font command set at the LF3 or LF4 level); note that this | ❓ |
| IPDS-17-048 | includes support for the LCP and LCPC commands, but excludes support for FOCA fonts. | ❓ |
| IPDS-17-049 | Support for negative object-area offset values in the BCOC, DOOC, IOC, GOC, and OCOC self-defining | ❓ |
| IPDS-17-050 | Support for the full range of logical page offset values in the LPP command | ❓ |
| IPDS-17-051 | Support for the 0, 90, 180, and 270 degree object area orientations in the BCAP , IAP , GAP , andOCAP self- | ❓ |
| IPDS-17-052 | Support for all defined values within units-per-unit-base fields (X'0001'–X'7FFF') | ❓ |
| IPDS-17-053 | STM properties: | ❓ |
| IPDS-17-054 | color values supported) | ❓ |
| IPDS-17-055 | OPC self-defining fields: | ❓ |
| IPDS-17-056 | Support for the following object types: | ❓ |
| IPDS-17-057 | STM properties: | ❓ |
| IPDS-17-058 | XOH Set Media Size (an optional Device Control command) | ❓ |
| IPDS-17-059 | IM-Image command set | ❓ |
| IPDS-17-060 | Optional compression algorithms (for IO Image) | ❓ |
| IPDS-17-061 | Downloaded symbol sets (using the LSS command) | ❓ |
| IPDS-17-062 | Font-modification parameters (bits 3–7 of LFE byte 14) | ❓ |
| IPDS-17-063 | Ordered pages (bit 0 of LPD byte 15) | ❓ |
| IPDS-A-001 | document where they are described. | ❓ |
| IPDS-A-002 | X'D601' | Manage IPDS Dialog (MID) | “Manage IPDS Dialog” | ❓ |
| IPDS-A-003 | X'D602' | Apply Finishing Operations (AFO) | “Apply Finishing Operations” | ❓ |
| IPDS-A-004 | X'D603' | No Operation (NOP) | “No Operation” | ❓ |
| IPDS-A-005 | X'D608' | Set Presentation Environment (SPE) | “Set Presentation Environment” | ❓ |
| IPDS-A-006 | X'D60A' | Activate Setup Name (ASN) | “Activate Setup Name” | ❓ |
| IPDS-A-007 | X'D60F' | Load Font Index (LFI) | “Load Font Index” | ❓ |
| IPDS-A-008 | X'D619' | Load Font Character Set Control (LFCSC) | “Load Font Character Set Control” | ❓ |
| IPDS-A-009 | X'D61A' | Load Code Page Control (LCPC) | “Load Code Page Control” | ❓ |
| IPDS-A-010 | X'D61B' | Load Code Page (LCP) | “Load Code Page” | ❓ |
| IPDS-A-011 | X'D61D' | Load Equivalence (LE) | “Load Equivalence” | ❓ |
| IPDS-A-012 | X'D61E' | Load Symbol Set (LSS) | “Load Symbol Set” | ❓ |
| IPDS-A-013 | X'D61F' | Load Font Control (LFC) | “Load Font Control” | ❓ |
| IPDS-A-014 | X'D62D' | Write Text (WT) | “Write Text” | ❓ |
| IPDS-A-015 | X'D62E' | Activate Resource (AR) | “Activate Resource” | ❓ |
| IPDS-A-016 | X'D62F' | Load Font (LF) | “Load Font” | ❓ |
| IPDS-A-017 | X'D633' | Execute Order Anystate (XOA) | “Execute Order Anystate” | ❓ |
| IPDS-A-018 | X'D634' | Presentation Fidelity Control (PFC) | “Presentation Fidelity Control” | ❓ |
| IPDS-A-019 | X'D63C' | Write Object Container Control (WOCC) | “Write Object Container Control” | ❓ |
| IPDS-A-020 | X'D63D' | Write Image Control (WIC) | “Write Image Control” | ❓ |
| IPDS-A-021 | X'D63E' | Write Image Control 2 (WIC2) | “Write Image Control 2” | ❓ |
| IPDS-A-022 | X'D63F' | Load Font Equivalence (LFE) | “Load Font Equivalence” | ❓ |
| IPDS-A-023 | X'D64C' | Write Object Container (WOC) | “Write Object Container” | ❓ |
| IPDS-A-024 | X'D64D' | Write Image (WI) | “Write Image” | ❓ |
| IPDS-A-025 | X'D64E' | Write Image 2 (WI2) | “Write Image 2” | ❓ |
| IPDS-A-026 | X'D64F' | Deactivate Font (DF) | “Deactivate Font” | ❓ |
| IPDS-A-027 | X'D658' | Delete Home-State Metadata (DHM) | “Delete Home-State Metadata” | ❓ |
| IPDS-A-028 | X'D659' | Request Resident Resource List (RRRL) | “Request Resident Resource List” | ❓ |
| IPDS-A-029 | X'D65A' | Remove Resident Resource (RRR) | “Remove Resident Resource” | ❓ |
| IPDS-A-030 | X'D65B' | Deactivate Data-Object-Font Component (DDOFC) | “Deactivate Data-Object-Font Component” | ❓ |
| IPDS-A-031 | X'D65C' | Deactivate Data Object Resource (DDOR) | “Deactivate Data Object Resource” | ❓ |
| IPDS-A-032 | X'D65D' | End (END) | “End” | ❓ |
| IPDS-A-033 | X'D65F' | Begin Page Segment (BPS) | “Begin Page Segment” | ❓ |
| IPDS-A-034 | X'D66B' | Invoke CMR (ICMR) | “Invoke CMR” | ❓ |
| IPDS-A-035 | X'D66C' | Data Object Resource Equivalence (DORE) | “Data Object Resource Equivalence” | ❓ |
| IPDS-A-036 | X'D66D' | Logical Page Position (LPP) | “Logical Page Position” | ❓ |
| IPDS-A-037 | X'D66E' | Data Object Resource Equivalence 2 (DORE2) | “Data Object Resource Equivalence 2” | ❓ |
| IPDS-A-038 | X'D66F' | Deactivate Page Segment (DPS) | “Deactivate Page Segment” | ❓ |
| IPDS-A-039 | X'D67B' | Rasterize Presentation Object (RPO) | “Rasterize Presentation Object” | ❓ |
| IPDS-A-040 | X'D67C' | Include Data Object (IDO) | “Include Data Object” | ❓ |
| IPDS-A-041 | X'D67D' | Include Overlay (IO) | “Include Overlay” | ❓ |
| IPDS-A-042 | X'D67E' | Include Saved Page (ISP) | “Include Saved Page” | ❓ |
| IPDS-A-043 | X'D67F' | Include Page Segment (IPS) | “Include Page Segment” | ❓ |
| IPDS-A-044 | X'D680' | Write Bar Code Control (WBCC) | “Write Bar Code Control” | ❓ |
| IPDS-A-045 | X'D681' | Write Bar Code (WBC) | “Write Bar Code” | ❓ |
| IPDS-A-046 | X'D684' | Write Graphics Control (WGC) | “Write Graphics Control” | ❓ |
| IPDS-A-047 | X'D685' | Write Graphics (WG) | “Write Graphics” | ❓ |
| IPDS-A-048 | X'D688' | Write Text Control (WTC) | “Write Text Control” | ❓ |
| IPDS-A-049 | X'D68A' | Write Metadata Control (WMC) | “Write Metadata Control” | ❓ |
| IPDS-A-050 | X'D68B' | Write Metadata (WM) | “Write Metadata” | ❓ |
| IPDS-A-051 | X'D68F' | Execute Order Home State (XOH) | “Execute Order Home State” | ❓ |
| IPDS-A-052 | X'D697' | Set Home State (SHS) | “Set Home State” | ❓ |
| IPDS-A-053 | X'D69F' | Load Copy Control (LCC) | “Load Copy Control” | ❓ |
| IPDS-A-054 | X'D6AF' | Begin Page (BP) | “Begin Page” | ❓ |
| IPDS-A-055 | X'D6BF' | End Page (EP) | “End Page” | ❓ |
| IPDS-A-056 | X'D6CE' | Define User Area (DUA) | “Define User Area” | ❓ |
| IPDS-A-057 | X'D6CF' | Logical Page Descriptor (LPD) | “Logical Page Descriptor” | ❓ |
| IPDS-A-058 | X'D6DF' | Begin Overlay (BO) | “Begin Overlay” | ❓ |
| IPDS-A-059 | X'D6E4' | Sense Type and Model (STM) | “Sense Type and Model” | ❓ |
| IPDS-A-060 | X'D6EF' | Deactivate Overlay (DO) | “Deactivate Overlay” | ❓ |
| IPDS-A-061 | X'D61C' Retired item 134 | ❓ |
| IPDS-A-062 | The following table lists the Acknowledge Reply: | ❓ |
| IPDS-A-063 | X'D6FF' | Acknowledge Reply (ACK) | “Acknowledge Reply” | ❓ |
| IPDS-B-001 | This appendix provides examples of the following IPDS command sequences: | ❓ |
| IPDS-B-002 | Initialization | ❓ |
| IPDS-B-003 | Page Segment | ❓ |
| IPDS-B-004 | Overlay | ❓ |
| IPDS-B-005 | Note:** These sequences are only examples, and the host need not send each command listed. | ❓ |
| IPDS-B-006 | In the following examples, commands that request an acknowledgment from the printer have the Acknowledgment Required (ARQ) bit on. When the ARQ bit is on, the printer sends an Acknowledge Reply (ACK) to the host. In the example below: | ❓ |
| IPDS-B-007 | &rarr;** indicates a command from the host to the printer | ❓ |
| IPDS-B-008 | &larr;** indicates a reply from the printer to the host. | ❓ |
| IPDS-B-009 | Initialization** | &rarr; Sense Type and Model (STM) with ARQ | IPDS command set implementation | ❓ |
| IPDS-B-010 | &larr; Acknowledge Reply (ACK) | Return type and model information | ❓ |
| IPDS-B-011 | &rarr; XOH Obtain Printer Characteristics (OPC) with ARQ | Request printer characteristics | ❓ |
| IPDS-B-012 | &larr; Acknowledge Reply (ACK) | Return printer characteristics | ❓ |
| IPDS-B-013 | &rarr; Set Home State (SHS) | Set printer to home state | ❓ |
| IPDS-B-014 | &rarr; Logical Page Descriptor (LPD) | Define a logical page | ❓ |
| IPDS-B-015 | &rarr; Logical Page Position (LPP) | Position a logical page | ❓ |
| IPDS-B-016 | &rarr; Load Copy Control (LCC) | Select copy options | ❓ |
| IPDS-B-017 | &rarr; Load Font Equivalence (LFE) with ARQ | Establish font equivalences | ❓ |
| IPDS-B-018 | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation | ❓ |
| IPDS-B-019 | Page Segment** | &rarr; Begin Page Segment (BPS) | Set printer to Page Segment state | ❓ |
| IPDS-B-020 | &rarr; Write Text (WT) | Store text data in page segment | ❓ |
| IPDS-B-021 | &rarr; Write Text (WT) | Store text data in page segment | ❓ |
| IPDS-B-022 | &rarr; Write Text (WT) | Store text data in page segment | ❓ |
| IPDS-B-023 | &rarr; Write Image Control 2 (WIC2) | Start IO-Image state | ❓ |
| IPDS-B-024 | &rarr; Write Image 2 (WI2) | Store IO-Image data in page segment | ❓ |
| IPDS-B-025 | &rarr; End | End IO-Image state | ❓ |
| IPDS-B-026 | &rarr; Write Text (WT) | Store text data in page segment | ❓ |
| IPDS-B-027 | &rarr; Write Text (WT) | Store text data in page segment | ❓ |
| IPDS-B-028 | &rarr; End Page (EP) with ARQ | Return to home state | ❓ |
| IPDS-B-029 | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation | ❓ |
| IPDS-B-030 | &rarr; Logical Page Descriptor (LPD) | Define a logical page | ❓ |
| IPDS-B-031 | &rarr; Load Font Equivalence (LFE) | Establish font equivalences | ❓ |
| IPDS-B-032 | Overlay** | &rarr; Begin Overlay (BO) | Enter overlay state | ❓ |
| IPDS-B-033 | &rarr; Write Text (WT) | Store text data in overlay | ❓ |
| IPDS-B-034 | &rarr; Write Text (WT) | Store text data in overlay | ❓ |
| IPDS-B-035 | &rarr; Write Text (WT) | Store text data in overlay | ❓ |
| IPDS-B-036 | &rarr; Include Overlay (IO) | Include another overlay | ❓ |
| IPDS-B-037 | &rarr; Write Graphics Control (WGC) | Enter graphics state | ❓ |
| IPDS-B-038 | &rarr; Write Graphics (WG) | Store graphics data in overlay | ❓ |
| IPDS-B-039 | &rarr; Write Graphics (WG) | Store graphics data in overlay | ❓ |
| IPDS-B-040 | &rarr; End | End graphics state | ❓ |
| IPDS-B-041 | &rarr; Write Text (WT) | Store text data in overlay | ❓ |
| IPDS-B-042 | &rarr; Write Text (WT) | Store text data in overlay | ❓ |
| IPDS-B-043 | &rarr; Include Overlay (IO) | Include another overlay | ❓ |
| IPDS-B-044 | &rarr; Include Page Segment (IPS) | Include page segment in overlay | ❓ |
| IPDS-B-045 | &rarr; End Page (EP) with ARQ | Return to home state | ❓ |
| IPDS-B-046 | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation | ❓ |
| IPDS-B-047 | Page** | &rarr; Begin Page (BP) | Enter page state | ❓ |
| IPDS-B-048 | &rarr; Write Text (WT) | Send text data to printer | ❓ |
| IPDS-B-049 | &rarr; Include Overlay (IO) | Print overlay | ❓ |
| IPDS-B-050 | &rarr; Include Page Segment (IPS) | Print page segment | ❓ |
| IPDS-B-051 | &rarr; Write Image Control (WIC) | Start IM-Image state | ❓ |
| IPDS-B-052 | &rarr; Write Image (WI) | Send IM-Image data to printer | ❓ |
| IPDS-B-053 | &rarr; Write Image (WI) | Send IM-Image data to printer | ❓ |
| IPDS-B-054 | &rarr; End | End IM-Image state | ❓ |
| IPDS-B-055 | &rarr; Write Text (WT) | Send text data to printer | ❓ |
| IPDS-B-056 | &rarr; Write Text (WT) | Send text data to printer | ❓ |
| IPDS-B-057 | &rarr; Include Overlay (IO) | Print overlay | ❓ |
| IPDS-B-058 | &rarr; Include Page Segment (IPS) | Print page segment | ❓ |
| IPDS-B-059 | &rarr; End Page (EP) with ARQ | Complete all printing and return to home state | ❓ |
| IPDS-B-060 | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation | ❓ |
| IPDS-B-061 | Before any printing begins, the host must specify certain parameters and conditions for the printer. The following command sequence, as shown in Table 72, accomplishes this task. | ❓ |
| IPDS-B-062 | The host sends the STM command to sense the IPDS command set implementation. | ❓ |
| IPDS-B-063 | If the STM command has the ARQ bit on, the printer responds with type and model information to the host. This information includes printer type, model, and command-set vector information. | ❓ |
| IPDS-B-064 | The host sends the XOH-OPC command to the printer, requesting printer characteristics to be placed in the Acknowledge Reply special data area. | ❓ |
| IPDS-B-065 | If the XOH-OPC command has the ARQ bit on, the information is supplied; if the ARQ bit is off, the XOH-OPC command is treated as a NOP. | ❓ |
| IPDS-B-066 | The host sends the SHS command to ensure the printer is in home state. | ❓ |
| IPDS-B-067 | The LPD command sets print characteristics for the current logical page. These parameters include logical page size, initial text-coordinate positions and text direction, initial text margin, intercharacter adjustment, baseline increment, font local ID, and text color. | ❓ |
| IPDS-B-068 | The LPP command positions the upper-left corner of the logical page (as defined by the LPD command) with respect to the medium presentation space. | ❓ |
| IPDS-B-069 | The LCC command specifies how many copies of each sheet are produced, whether to print simplex or duplex, the overlays that are to be included on each copy, and the suppressions that are to be activated for each copy. Suppression allows text data to be selectively suppressed during printing. | ❓ |
| IPDS-B-070 | The LFE command maps font local IDs (used within the text, graphics, or bar code data) to font Host-Assigned IDs used for resource management. | ❓ |
| IPDS-B-071 | If the LFE command has the ARQ bit on, the printer responds with the Acknowledge Reply to inform the host of successful receipt of all the previous commands. The printer is now ready to accept data for print operations. The initialization and preparation sequence is finished. | ❓ |
| IPDS-B-072 | The page-segment sequence creates a page segment resource for later printing. The following command sequence, as shown in Table 72, illustrates the loading of a page segment. | ❓ |
| IPDS-B-073 | Note:** This sequence is only an example. A page segment can contain any combination of text, image, graphics, and bar code data. | ❓ |
| IPDS-B-074 | The host sends the BPS command to the printer, causing the printer to leave home state and enter page segment state. Page segment state creates a segment of page data to save within the printer for later printing. The BPS command contains an ID for later use in selecting this segment. This segment can contain combinations of text, images, bar code data, and graphics. | ❓ |
| IPDS-B-075 | The WT command sends text data to the printer. Because the printer is currently in page segment state, the text information does not print at this time. Instead, the data becomes part of the page segment. If no text data is to be included in the segment, this command does not occur. Multiple WT commands can be sent to the printer while in page segment state. | ❓ |
| IPDS-B-076 | The WIC2 command causes the printer to enter IO-Image state. | ❓ |
| IPDS-B-077 | The WI2 command sends IO-Image data to the printer. | ❓ |
| IPDS-B-078 | Note:** Both the IM-Image commands (WIC and WI) and the IO-Image commands (WIC2 and WI2) send image data to the printer. However, the IO-Image commands provide additional functions over the IM-Image commands. | ❓ |
| IPDS-B-079 | The END command terminates IO-Image state. The printer returns to page segment state, with the image stored for later use. | ❓ |
| IPDS-B-080 | The WT command is repeated at this point in the sequence, to illustrate that additional text data can be added to the page segment. Graphics data, bar code data, or additional image data can also be included in the page segment. | ❓ |
| IPDS-B-081 | The EP command causes the printer to leave page segment state and return to home state. This command can contain an ARQ to ensure successful transmission of the page segment. | ❓ |
| IPDS-B-082 | If the EP command has the ARQ bit on, the printer responds with the ACK to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has accepted all the segment data and has stored this information for later printing. | ❓ |
| IPDS-B-083 | Note:** Page segment commands need not be syntax-checked until they are included on a logical page by use of the Include Page Segment command. | ❓ |
| IPDS-B-084 | The overlay sequence creates an overlay resource for later printing. The following command sequence, as shown in Table 72, illustrates the loading of a typical overlay. | ❓ |
| IPDS-B-085 | Note:** This sequence is only an example. An overlay can contain any combination of text data, image data, graphics data, bar code data, object container data, included page segments, and included overlays. | ❓ |
| IPDS-B-086 | The LPD command sets print characteristics for the current logical page; it will be stored with the overlay. These parameters include logical page size, initial text-coordinate positions and text direction, initial text margin, intercharacter adjustment, baseline increment, font local ID, and text color. | ❓ |
| IPDS-B-087 | The LFE command maps font local IDs (used within the text, graphics, or bar code data) to font Host-Assigned IDs used for resource management. It will be stored with the overlay. | ❓ |
| IPDS-B-088 | The host sends the BO command to the printer, causing the printer to leave home state and enter overlay state. Overlay state creates an overlay resource to be saved within the printer for later printing. The BO command contains an ID for later use in selecting this overlay. | ❓ |
| IPDS-B-089 | The WT command sends text data to the printer. Because the printer is currently in overlay state, this text information does not print at this time. Instead, the data becomes part of the overlay. If no text data is to be included in the overlay, this command does not occur. Multiple WT commands can be sent to the printer while in overlay state. | ❓ |
| IPDS-B-090 | The IO command causes a previously stored overlay to be included with the current overlay. The IO command contains an ID field that specifies the overlay. | ❓ |
| IPDS-B-091 | The WGC command causes the printer to enter graphics state. Parameters in this command specify the placement, size, and orientation of the graphics object area. | ❓ |
| IPDS-B-092 | The WG command sends graphics data to the printer. The graphics data is contained in drawing orders, that specify various elements of the graphics picture. These include color, size, line type, line width, and other parameters. One or more WG commands present the graphics picture. | ❓ |
| IPDS-B-093 | The END command terminates graphics state. The printer returns to overlay state with the graphics data now part of the overlay. | ❓ |
| IPDS-B-094 | The WT command is repeated at this point in the sequence to illustrate that additional text data can be added to the overlay. Image data, graphics data, bar code data, or object container data can also be included in the overlay. | ❓ |
| IPDS-B-095 | The IO command causes a previously stored overlay to be included with the current overlay. | ❓ |
| IPDS-B-096 | The IPS command causes a previously stored page segment to be included in the current overlay as if it had been part of the overlay data. The IPS command contains an ID field that specifies the page segment. | ❓ |
| IPDS-B-097 | The EP command causes the printer to leave overlay state and return to home state. This command can contain an ARQ to verify transmission of the overlay. | ❓ |
| IPDS-B-098 | If the EP command has the ARQ bit on, the printer responds with the ACK reply to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has accepted all of the overlay data and has stored this information for later printing. | ❓ |
| IPDS-B-099 | Note:** Overlay commands need not be syntax-checked until they are included on a logical page by use of the Include Overlay command or on the medium presentation space by use of the Load Copy Control command. | ❓ |
| IPDS-B-100 | The page sequence causes a page to be created and printed on the current sheet. This data can include previously stored overlays or page segments, as well as immediate text or object data. The following command sequence, as shown in Table 72, illustrates the loading of a page. | ❓ |
| IPDS-B-101 | Note:** This sequence is only an example. A page sequence can contain any combination of text data, image data, graphics data, bar code data, object container data, page segments, or overlays. | ❓ |
| IPDS-B-102 | The host sends the BP command to the printer, causing the printer to leave home state and enter page state. | ❓ |
| IPDS-B-103 | The WT command sends text data to the printer. Because the printer is currently in page state, this text information prints on the current logical page. Multiple WT commands can be sent to the printer while in page state. | ❓ |
| IPDS-B-104 | The IO command causes a previously stored overlay to merge with the current logical page. The IO command contains an ID field that specifies the overlay. This included overlay is independent of the page and can extend partially or completely outside of the page's logical page. | ❓ |
| IPDS-B-105 | The IPS command causes a previously stored page segment to merge with the current logical page as if it had been part of the page data. An ID field in this command identifies the page segment. | ❓ |
| IPDS-B-106 | The WIC command causes the printer to enter IM-Image state. | ❓ |
| IPDS-B-107 | The WI command sends IM-Image data to the printer. One or more of these commands create the actual image for printing. | ❓ |
| IPDS-B-108 | Note:** Both the IM-Image commands (WIC and WI) and the IO-Image commands (WIC2 and WI2) send image data to the printer. However, the IO-Image commands provide additional functions over the IM-Image commands. | ❓ |
| IPDS-B-109 | The END command terminates IM-Image state. The printer returns to page state. | ❓ |
| IPDS-B-110 | The WT command is repeated at this point in the sequence to illustrate that additional text data can be added to the page. Image data, graphics data, bar code data, or object container data can also be included on the page. | ❓ |
| IPDS-B-111 | The IO command causes a previously stored overlay to merge with the current logical page. | ❓ |
| IPDS-B-112 | The IPS command causes a previously stored page segment to merge with the current logical page as if it had been part of the page data. An ID field in this command identifies the page segment. | ❓ |
| IPDS-B-113 | The EP command causes the printer to leave page state and return to home state. This command can contain an ARQ to verify successful transmission of the page data. | ❓ |
| IPDS-B-114 | If the EP command had the ARQ bit on, the printer responds with the ACK reply to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has successfully accepted all the previous commands, and that the page will subsequently be transferred to paper. | ❓ |
| IPDS-C-001 | The IO-Image command set uses the following algorithms for image compression: | ❓ |
| IPDS-C-002 | Modified ITU-TSS Modified READ algorithm (IBM MMR) | ❓ |
| IPDS-C-003 | Run-Length 4 compression algorithm (RL4) | ❓ |
| IPDS-C-004 | ABIC (Bilevel Q-Coder) compression algorithm (ABIC) | ❓ |
| IPDS-C-005 | Concatenated ABIC | ❓ |
| IPDS-C-006 | ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one-dimensional) compression algorithm | ❓ |
| IPDS-C-007 | ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two-dimensional) compression algorithm | ❓ |
| IPDS-C-008 | ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR) compression algorithm | ❓ |
| IPDS-C-009 | ISO/ITU-TSS JPEG algorithms | ❓ |
| IPDS-C-010 | JBIG2 compression algorithm | ❓ |
| IPDS-C-011 | Solid Fill Rectangle | ❓ |
| IPDS-C-012 | TIFF LZW | ❓ |
| IPDS-C-013 | TIFF LZW with Differencing Predictor | ❓ |
| IPDS-C-014 | The IO-Image command set uses the following algorithms for image recording: | ❓ |
| IPDS-C-015 | RIDIC recording algorithm | ❓ |
| IPDS-C-016 | Unpadded RIDIC recording algorithm. | ❓ |
| IPDS-C-017 | Refer to the compression and recording algorithm appendix in the *Image Object Content Architecture Reference* for further details about these algorithms. | ❓ |
| IPDS-C-018 | The Modified ITU-TSS Modified READ 2 Algorithm (IBM MMR) allows image data to be compressed optionally in either one-dimensional mode or two-dimensional mode. In one-dimensional mode, color transitions in the image are coded as run length features. In two-dimensional mode, the position of each changing image data element on the current or coding line is coded with respect to the position of a corresponding reference image data element on either the coding line or the reference line that immediately precedes the coding line. One of three coding modes (pass mode, vertical mode, or horizontal mode) is chosen according to the coding procedure that identifies the coding mode to be used to code each changing element along the coding line. When one of the three coding modes is identified by the coding procedure, an appropriate code is selected from the code table. | ❓ |
| IPDS-C-019 | Note:** READ stands for Relative Element Address Designate. | ❓ |
| IPDS-C-020 | The Run-Length 4 (RL4) algorithm is a binary, one-dimensional, run-length coding method of compression. It is based on code words using four bits. The code words used are common to both white runs and black runs. Code words are listed in the following table: | ❓ |
| IPDS-C-021 | 0 | 1111 1110 | 8 bits | ❓ |
| IPDS-C-022 | 1&ndash;8 | 0xxx | 4 bits | ❓ |
| IPDS-C-023 | 9&ndash;72 | 10xx xxxx | 8 bits | ❓ |
| IPDS-C-024 | 73&ndash;584 | 110x xxxx xxxx | 12 bits | ❓ |
| IPDS-C-025 | 585&ndash;4680 | 1110 xxxx xxxx xxxx | 16 bits | ❓ |
| IPDS-C-026 | 4681&ndash;32,767 | 1111 0xxx xxxx xxxx xxxx | 20 bits | ❓ |
| IPDS-C-027 | EOL | 1111 1111 (1111) | 8 or 12 bits | ❓ |
| IPDS-C-028 | Two EOL (End Of Line) codes are provided to make an encoded string of each scan line start at a byte boundary. Either of these codes is used, depending on whether the last run-length code of the previous scan line ends at a byte boundary. Each scan line is represented in the following format: | ❓ |
| IPDS-C-029 | Line Number** | **Length (in bytes)** | **W-runLength** | **B-run** | **W-run** | ... | **EOL** | ❓ |
| IPDS-C-030 | Both line number and length are represented as two-byte integers, making it possible to skip lines efficiently or to access a specific line directly for display and editing purposes. Providing line numbers also allows completely white lines to be skipped when recording the compressed data. Regarding the run encoding, the first run of each line must be white; if a line begins with a black image data element, white run of length zero must be put in the encoded string. If a line ends with a sequence of white image data elements (that is often the case), the last white run need not be encoded, because it can be calculated from the horizontal size of the image content and the total length of the preceding runs. | ❓ |
| IPDS-C-031 | The ABIC algorithm provides an invertible mapping between any data file and a more compact representation of the same information. | ❓ |
| IPDS-C-032 | The image data is first rearranged in IDE bit order so that the first bit of each IDE is sequentially retrieved followed by the second bit of each IDE and so on until all of the IDE bits are retrieved. Then the data is compressed using the ABIC compression algorithm. | ❓ |
| IPDS-C-033 | The ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one-dimensional) compression algorithm, also called the G3 Modified Huffman compression algorithm (G3 MH) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed one-dimensionally. | ❓ |
| IPDS-C-034 | The ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two-dimensional) compression algorithm, also called the G3 Modified Read compression algorithm (G3 MR) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed two-dimensionally. | ❓ |
| IPDS-C-035 | The ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR) compression algorithm, also known as the G4 Modified MR compression algorithm (G4 MMR) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed two-dimensionally. | ❓ |
| IPDS-C-036 | The JPEG (Joint Photographic Experts Group) technical specification describes a series of algorithms that can be applied to arbitrary source image resolutions, many color models, multiple image components, various sampling formats, and continuous-tone renditions of text. The algorithms are not applicable to bilevel images. Some of the JPEG compression algorithms are lossy. | ❓ |
| IPDS-C-037 | The JBIG (Joint Bi-level Image experts Group) technical specification JBIG2, details a set of algorithms specialized for bilevel (1 bit/pixel) source image data at arbitrary spatial resolutions; with separate methods and particular emphasis on textual and halftone bilevel images, in addition to general support for any type of bilevel image (for example, additional image types like line art, pie charts, etc.). | ❓ |
| IPDS-C-038 | Most algorithms are lossless, with the exception of a near-lossless capability for scanned textual images that can be extended to lossless. | ❓ |
| IPDS-C-039 | The JBIG2 compression is defined by ITU-T Recommendation T.88 and ISO/IEC International Standard 14492:2000. | ❓ |
| IPDS-C-040 | Note:** JBIG2 stores the actual image size in the compressed datastream, thus allowing the IOCA Process Model to determine the number of horizontal and vertical image points from the image data. HSIZE and VSIZE can therefore be zero in the Image Size Parameter. | ❓ |
| IPDS-C-041 | For more details about the JBIG2 algorithm, refer to International Telecommunication Union, Recommendation T.88, *Information technology-Coded representation of picture and audio information- Lossy/lossless coding of bilevel images*. | ❓ |
| IPDS-C-042 | The Solid Fill Rectangle compression algorithm is applicable only to bilevel tiles within a tiled image. When specified for a bilevel tile, this compression algorithm indicates that the tile contains no image data (Image Data or Band Image Data) and that the tile will be colored using a solid color. The color is either specified in a Tile Set Color Parameter, or (if no Tile Set Color Parameter) specified in a Set Bilevel Image Color IOCA self-defining field (or a Set Extended Bilevel Image Color IOCA self-defining field) in the Image Data Descriptor, or (if neither is specified) defaults to the device default color. | ❓ |
| IPDS-C-043 | The LZW (Lempel-Ziv and Welch) algorithm uses a translation table, called the string table, that maps strings of input characters into codes. The TIFF implementation uses variable-length codes, with a maximum code length of twelve bits. The algorithm works best if the input uncompressed data is organized into strips of about 8K bytes, with each strip being compressed independently. The string table is different for every strip. | ❓ |
| IPDS-C-044 | The TIFF LZW with Differencing Predictor compression algorithm is an extension of the TIFF LZW compression algorithm, compressing values that are the differences between pixels rather than the pixel values themselves. All information in the "TIFF LZW Compression Algorithm" section just above is applicable to this compression algorithm as well. | ❓ |
| IPDS-C-045 | The Recorded Image Data Inline Coding recording algorithm (RIDIC) formats a single image in the binary element sequence of a unidirectional raster scan with no interlaced fields and with parallel raster lines, from left to right and from top to bottom. Refer to Figure 121 for a diagram of a RIDIC raster scan. | ❓ |
| IPDS-C-046 | (Diagram description: x-dimension, y-dimension, scan lines 1, 2, 3 ... n-2, n-1, n) | ❓ |
| IPDS-C-047 | Each binary element representing an image data element after decompression, without grayscale, is 0 for an image data element without intensity, and 1 for an image data element with intensity. More than one binary element can represent an image data element after decompression, corresponding to a grayscale or color algorithm. Each raster scan line is an integral multiple of 8 bits. If an image occupies an area whose width is other than an integral multiple of 8 bits, the scan line is padded with zeros. | ❓ |
| IPDS-C-048 | The Unpadded RIDIC recording algorithm is identical to the RIDIC recording algorithm except that raster scan lines can be any length; no padding is necessary. | ❓ |
| IPDS-C-049 | Note:** It is an error to mark an IO Image with an incorrect recording algorithm; however, occasionally an image will be built without padding, but incorrectly marked as RIDIC (or padded but incorrectly marked as unpadded RIDIC). Some IPDS implementations can detect this error and tolerate the image by internally assuming that it was mismarked. | ❓ |
| IPDS-D-001 | This appendix lists each retired item that is mentioned within the body of this book and also lists those items that have been unretired. | ❓ |
| IPDS-D-002 | X'FF' in the acknowledge type field (byte 0) of the Acknowledge Reply had previously been retired for use by the 370 channel-attached printers to indicate a Null ACK. This value is now used to cover the error case where the communications protocol that carries the IPDS commands attempts to obtain a positive Acknowledge Reply (ACK) without first sending an IPDS command with the ARQ bit set to B'1'. | ❓ |
| IPDS-D-003 | Retired item 2 (1991):** X'02' in the unit base field (byte 1) of the Define User Area command is retired for relative units. | ❓ |
| IPDS-D-004 | Retired item 3 (1990):** X'FF' in the font local ID field (byte 0) of the Load Font Equivalence command is retired for selecting a default font. | ❓ |
| IPDS-D-005 | Retired item 4 (1991):** X'02' in the unit base field (byte 0) of the Logical Page Descriptor command is retired for relative units. | ❓ |
| IPDS-D-006 | Retired item 5 (1991):** Byte 1 of the Logical Page Descriptor command is retired for the IBM 3820 printer (unit base for Y coordinate). | ❓ |
| IPDS-D-007 | Retired item 6 (1991):** Byte 18 of the Logical Page Descriptor command is retired for the IBM 3820 printer (escape code). | ❓ |
| IPDS-D-008 | Retired item 7 (1991):** Byte 20 of the Logical Page Descriptor command is retired for the IBM 3820 printer (bit controls). | ❓ |
| IPDS-D-009 | Retired item 8 (1991):** Byte 21 of the Logical Page Descriptor command is retired for the IBM 3820 printer (data checks). | ❓ |
| IPDS-D-010 | Retired item 9 (1991):** X'02' in the resource type field (Byte 2) of the Activate Resource command is retired for double-byte coded font. | ❓ |
| IPDS-D-011 | X'05' in the resource type field (Byte 2) of the Activate Resource command had previously been retired because there was no method of refreshing nested resource HAIDs in an overlay. If resident overlays are supported, nested HAIDs must be managed by the presentation services program. | ❓ |
| IPDS-D-012 | X'06' in the resource type field (Byte 2) of the Activate Resource command had previously been retired for code page. This value was unretired for use with outline fonts. | ❓ |
| IPDS-D-013 | X'07' in the resource type field (Byte 2) of the Activate Resource command had previously been retired for font character set. This value was unretired for use with outline fonts. | ❓ |
| IPDS-D-014 | X'06' in the resource ID format field (Byte 6) of the Activate Resource command had previously been retired for MVS Host Unalterable Font Environment. The value was unretired because it is used by some IPDS products. | ❓ |
| IPDS-D-015 | Retired item 14 (1991):** Bit 1 of the resource class flags field (byte 11) in the Activate Resource command is retired for the IBM RPM MVS product (used as a save/no save flag). | ❓ |
| IPDS-D-016 | Bits 0&ndash;2:** Reserved | ❓ |
| IPDS-D-017 | Bit 3:** Resolution correction algorithms to minimize information loss (for example, via nearest neighbor averaging techniques as opposed to discarding pels) | ❓ |
| IPDS-D-018 | Bit 4:** Resolution correction ratio may be any positive real number | ❓ |
| IPDS-D-019 | Bit 5:** Integer fraction (1/2, 1/3, ...) resolution correction supported (for example, 1/2 = discard every other pel) | ❓ |
| IPDS-D-020 | Bit 6:** Integer (x2, x3, ...) resolution correction supported for example, x2 = double every pel) | ❓ |
| IPDS-D-021 | Bit 7:** Resolution correction supported | ❓ |
| IPDS-D-022 | Bits 0&ndash;2:** Reserved | ❓ |
| IPDS-D-023 | Bit 3:** Scaling algorithms to minimize information loss (for example, via nearest neighbor averaging techniques as opposed to discarding pels) | ❓ |
| IPDS-D-024 | Bit 4:** Scaling ratio may be any positive real number | ❓ |
| IPDS-D-025 | Bit 5:** Integer fraction (1/2, 1/3, ...) scaling supported (for example, 1/2 = discard every other pel) | ❓ |
| IPDS-D-026 | Bit 6:** Integer (x2, x3, ...) scaling supported (for example, x2 = double every pel) | ❓ |
| IPDS-D-027 | Bit 7:** Scaling supported | ❓ |
| IPDS-D-028 | Retired item 17 (1991):** The range of values X'06'&ndash;X'FF' in property pair X'C0nn' in the Loaded-Font command-set vector of the Sense Type and Model reply is retired for outline-font pattern-technology IDs. The values X'1E' and X'1F' were defined and unretired in 1993. | ❓ |
| IPDS-D-029 | Retired item 18 (1991):** X'0000' order code of the Execute Order Anystate command is retired for IBM 3820-0, 3825-1, 3827-1, 3835-1, 3831, and 3827E printers as a NOP. | ❓ |
| IPDS-D-030 | Retired item 19 (1991):** X'0001' order code of the Execute Order Anystate command is retired for IBM 3812 model 2 and IBM 3816 models 11 and 12 printers as a method for printing the environment when a special debug port is attached to the printer. | ❓ |
| IPDS-D-031 | Retired item 20 (1991):** X'0002' order code of the Execute Order Anystate command is retired for IBM 3812 model 2 and IBM 3816 models 11 and 12 printers as a method for ringing the bell on the printer. | ❓ |
| IPDS-D-032 | Retired item 21 (1991):** X'0200' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Read Font List order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. | ❓ |
| IPDS-D-033 | Retired item 22 (1991):** X'0600' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Mark Form Carrier Strip order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. | ❓ |
| IPDS-D-034 | Retired item 23 (1991):** X'7BF5' order code of the Execute Order Anystate command is retired for IBM 3816 model 11 and 12 printers as a method for setting soft switches in configuration mode. | ❓ |
| IPDS-D-035 | Retired item 24 (1991):** X'CACA' order code of the Execute Order Anystate command is retired for IBM 3812-2, 3816-011, and 3816-012 printers as a method for escaping from IPDS to Page Map Primitives (PMP); PMP is the machine-level language for the IBM 3812 printer. | ❓ |
| IPDS-D-036 | Retired item 25 (1991):** X'F100' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Display Operator Panel Message order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. | ❓ |
| IPDS-D-037 | Byte 2, bit 2, B'1' means that the IBM 3820 printer is connected through IBM Remote PrintManager. | ❓ |
| IPDS-D-038 | Byte 2, bit 3, B'1' means that the IBM 3820 is an IBM 3820-ROM printer. | ❓ |
| IPDS-D-039 | Byte 2, bit 4, B'1' means IBM Remote PrintManager group operations. | ❓ |
| IPDS-D-040 | Byte 2, bit 5, B'1' means that an intermediate device is attached using the IBM Distributed Print Feature (DPF). | ❓ |
| IPDS-D-041 | Byte 2, bit 6, B'1' means that an intermediate device is attached using IBM PSF Direct. | ❓ |
| IPDS-D-042 | Bytes 4&ndash;5 contain a hexadecimal number (either X'0200' or X'0280') describing the amount of control store in kilobytes. | ❓ |
| IPDS-D-043 | Bytes 6&ndash;7 contain a hexadecimal number (from X'0100' to X'1000' in X'0100' or greater increments) describing the amount of pattern store in kilobytes. | ❓ |
| IPDS-D-044 | X'05' in the ordering field (byte 2) of the XOA-Request Resource List command had previously been retired for the IBM Remote PrintManager product. The product used this value before it was defined in the architecture and, once discovered, it was added to the architecture. | ❓ |
| IPDS-D-045 | X'00' Downloaded resource; not present in the printer | ❓ |
| IPDS-D-046 | X'01' Downloaded resource; in control storage | ❓ |
| IPDS-D-047 | X'02' Downloaded resource; in pattern storage | ❓ |
| IPDS-D-048 | Retired item 29 (1991):** X'02' in the resource ID format field (byte 7) of the XOA-Request Resource List command is retired for the IBM 3820 printer. Refer to retired item 28 for more information. | ❓ |
| IPDS-D-049 | Retired item 30 (1991):** X'06' in the resource ID format field (byte 7) of the XOA-Request Resource List command is retired for MVS Host Unalterable Font Environment. | ❓ |
| IPDS-D-050 | Retired item 31 (1991):** X'0000' order code of the Execute Order Home State command is retired for IBM 3820-0, 3825-1, 3827-1, 3835-1, 3831, and 3827E printers as a NOP. | ❓ |
| IPDS-D-051 | Retired item 32 (1991):** X'0B00' order code of the Execute Order Home State command is retired for the IBM 3800 printer Set X Adjustment Range order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. | ❓ |
| IPDS-D-052 | Retired item 33 (1991):** X'F400' order code of the Execute Order Home State command is retired for the IBM 3800 printer Inhibit Automatic WCGM Load order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. | ❓ |
| IPDS-D-053 | Retired item 34 (1991):** X'02' in the unit base field (byte 6) of the Printable-Area self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. | ❓ |
| IPDS-D-054 | Retired item 35 (1991):** X'02' in the unit base field (byte 2) of the Variable-Box Size values in the Symbol-Set Support self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. | ❓ |
| IPDS-D-055 | Retired item 36 (1991):** X'02' in the unit base field (byte 4) of the IM-Image and Coded-Font Resolution self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. | ❓ |
| IPDS-D-056 | X'0200' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a manual two-channel switch. | ❓ |
| IPDS-D-057 | X'0201' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a tightly coupled two-channel switch. | ❓ |
| IPDS-D-058 | Retired item 39 (1991):** X'0202' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for a loosely coupled two-channel switch. | ❓ |
| IPDS-D-059 | Retired item 40 (1991):** X'0400' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for stapler. | ❓ |
| IPDS-D-060 | X'0200' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a manual two-channel switch. | ❓ |
| IPDS-D-061 | X'0201' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a tightly coupled two-channel switch. | ❓ |
| IPDS-D-062 | Retired item 43 (1991):** X'0202' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for a loosely coupled two-channel switch. | ❓ |
| IPDS-D-063 | Retired item 44 (1991):** X'0400' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for stapler. | ❓ |
| IPDS-D-064 | Retired item 45 (1991):** X'02' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply is retired for double-byte coded fonts. | ❓ |
| IPDS-D-065 | X'05' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for overlay. This value was unretired when retired item 10 was unretired. | ❓ |
| IPDS-D-066 | X'06' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for code page. This value was unretired for use with outline fonts. | ❓ |
| IPDS-D-067 | X'07' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for font character set. This value was unretired for use with outline fonts. | ❓ |
| IPDS-D-068 | X'06' in the Resource-ID-Format byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for IBM MVS Host Unalterable Remote Font Environments. This value was unretired when retired item 13 was unretired. | ❓ |
| IPDS-D-069 | Retired item 50 (1991):** X'0000' self-defining product-identifier parameter ID in the Product Identifier self-defining field in the XOH-Obtain Printer Characteristics reply is retired for IBM 4224 and IBM 4234 printers. This product-identifier parameter ID indicates that the stack page counter is implemented as specified by the IPDS Architecture. There is no data in bytes 3&ndash;end, that is, byte 0 = X'03'. | ❓ |
| IPDS-D-070 | Retired item 51 (1991):** X'02' in the unit base field (byte 2) of the XOH-Set Media Size command is retired for relative units. | ❓ |
| IPDS-D-071 | Retired item 52 (1991):** X'0200' in the type field (bytes 0&ndash;1) of the Load Equivalence command is retired for the IBM Remote PrintManager product. It is used to map the current value of Host-Assigned IDs of page segments included within previously stored overlays onto the value in effect at the time they were first downloaded to IBM Remote PrintManager. | ❓ |
| IPDS-D-072 | Retired item 53 (1991):** X'02' in the unit base field (byte 4) of the Text Output Control self-defining field in the Write Text Control command is retired for relative units. | ❓ |
| IPDS-D-073 | Retired item 54 (1991):** X'02' in the unit base field (byte 6) of the Text Data Descriptor self-defining field in the Write Text Control command is retired for relative units. | ❓ |
| IPDS-D-074 | Retired item 55 (1991):** X'02' in the unit base field (byte 4) of the Image Output Control self-defining field in the Write Image Control 2 command is retired for relative units. | ❓ |
| IPDS-D-075 | Retired item 56 (1991):** X'02' in the unit base field (byte 6) of the Image Data Descriptor self-defining field in the Write Image Control 2 command is retired for relative units. | ❓ |
| IPDS-D-076 | Retired item 57 (1991):** X'02' in the unit base field (byte 4) of the Graphics Output Control self-defining field in the Write Graphics Control command is retired for relative units. | ❓ |
| IPDS-D-077 | Retired item 58 (1991):** X'02' in the unit base field (byte 4) of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for relative units. | ❓ |
| IPDS-D-078 | Retired item 59 (1991):** Bytes 22&ndash;25 of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for the $Z_{g}$ coordinates of the graphics presentation space window. | ❓ |
| IPDS-D-079 | Retired item 60 (1991):** Bytes 26&ndash;27 of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for graphic data flags. | ❓ |
| IPDS-D-080 | Retired item 61 (1991):** X'02' in the unit base field (byte 4) of the Bar Code Output Control self-defining field in the Write Bar Code Control command is retired for relative units. | ❓ |
| IPDS-D-081 | Retired item 62 (1991):** X'02' in the unit base field (byte 4) of the Bar Code Data Descriptor self-defining field in the Write Bar Code Control command is retired for relative units. | ❓ |
| IPDS-D-082 | Retired item 63 (1991):** Bit 4 of the flags byte (byte 0) in the Write Bar Code command is retired in the BCOCA architecture for PC ASCII data stream use; this flag indicates support for ASCII data in a BCOCA object. | ❓ |
| IPDS-D-083 | Retired item 64 (1991):** Bit 7 of the flags byte (byte 0) in the Write Bar Code command is retired in the BCOCA architecture for PC ASCII data stream use; in particular this flag is used by the IBM Personal Printer Data Stream (PPDS). | ❓ |
| IPDS-D-084 | Retired item 65 (1991):** X'02' in the unit base for pel-units field (byte 26) of the Load Font Control command is retired for relative units. | ❓ |
| IPDS-D-085 | Retired item 66 (1991):** Bit 0 of the flags1 field (byte 0) in the Load Symbol Set command is retired for the IBM 3270 architecture. This bit indicates "Extended Form" in the IBM 3270 architecture. That architecture allows a shorter form of this command, indicated by a value of B'0' in this bit. | ❓ |
| IPDS-D-086 | If no previous LSS font or coded font with a font identifier matching bytes 15&ndash;16 exists in the printer, this command establishes a new LSS font. | ❓ |
| IPDS-D-087 | If a previous LSS font or coded font with a font identifier matching bytes 15&ndash;16 exists in the printer, the information transmitted by this command replaces some or all of the existing control and all of the existing raster information about the matching code points, regardless of whether the existing information was loaded by a previous Load Symbol Set command or via the coded font commands (Load Font Control, Load Font Index, and Load Font). | ❓ |
| IPDS-D-088 | The IBM GOCA and IBM 3270 architectures allow font deletion via a value of B'1' for this bit. IPDS font deletion is done only via the Deactivate Font command. | ❓ |
| IPDS-D-089 | Retired item 68 (1991):** Bit 2 of the flags1 field (byte 0) in the Load Symbol Set command is retired for the IBM 3270 architecture. A value of B'1' is used by the IBM GOCA architecture and IBM 3270 architecture to request a function known as "skip suppression" that, for IPDS printers, is better done via control sequences or, implicitly, by the font design. | ❓ |
| IPDS-D-090 | Retired item 69 (1991):** Byte 1 in the Load Symbol Set command is retired for the IBM 3270 architecture. The local ID specified in this byte has no significance for IPDS printers since the one-byte local identifier used by the Graphics Set Character Set, Push and Set Character Set, Set Marker Set, and Push and Set Marker Set orders; by the Bar Code LID; and by the Text SCFL are mapped to the Font Host-Assigned ID (LSS bytes 15&ndash;16) via the Load Font Equivalence command. | ❓ |
| IPDS-D-091 | Retired item 70 (1991):** Byte 3 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte is the read/write storage (RWS) number in IBM GOCA and IBM 3270 architectures; it has no significance for IPDS printers. | ❓ |
| IPDS-D-092 | Retired item 71 (1991):** Bits 0&ndash;2 of the flags2 field (byte 5) in the Load Symbol Set command are retired for the IBM 3270 architecture. These flag bits are used to control the all points addressable (APA), LCID compare bit (CB), and operator selectable by PS key (OB) functions respectively for IBM GOCA and IBM 3270 architectures. These functions are not currently supported by IPDS printers hence the bit values B'011' effectively disable these functions. | ❓ |
| IPDS-D-093 | Retired item 72 (1991):** Bit 3 of the flags2 field (byte 5) in the Load Symbol Set command is retired for the IBM 3270 architecture; this bit has no significance for IPDS printers. This bit specifies the multiple LCID (MULTID) setting in IBM GOCA and IBM 3270 architectures. | ❓ |
| IPDS-D-094 | Retired item 73 (1991):** Bit 4 of the flags2 field (byte 5) in the Load Symbol Set command is retired for the IBM 3270 architecture; this bit has no significance for IPDS printers. Retired for "Use Symbol Envelope Table". A value of B'1' indicates that the Symbol Envelope Table (SET) information (from a triplet specified in another LSS field) is to be applied to this symbol set when these symbols are used within graphics data. It is an exception if this value is specified and no SET exists in the triplet field. Refer to the IBM GOCA specification for a description of the SET. A value of B'0' indicates that the SET, if present, is not to be used. | ❓ |
| IPDS-D-095 | Bits 0&ndash;4:** Reserved | ❓ |
| IPDS-D-096 | Bit 5:** Green plane | ❓ |
| IPDS-D-097 | Bit 6:** Red plane | ❓ |
| IPDS-D-098 | Bit 7:** Blue plane | ❓ |
| IPDS-D-099 | For example, a value of X'03' indicates that the blue and red planes are to be loaded. A value of X'00' indicates that all color planes are to be loaded. | ❓ |
| IPDS-D-100 | Retired item 75 (1991):** Byte 10 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte specifies the Starting Subsection Identifier in the IBM 3270 architecture. It has no significance for IPDS printers. | ❓ |
| IPDS-D-101 | Retired item 76 (1991):** Byte 12 in the Load Symbol Set command is retired for the IBM 3270 architecture. Retired for "Width pairs"; this byte indicates the number of pairs of width-indentation values specified in the Symbol Envelope Table (SET) parameter. IBM GOCA architecture specifies the form of the SET. If the SET is not present or not supported (byte 5, bit 4), this field can be ignored. This field has no significance for IPDS printers. | ❓ |
| IPDS-D-102 | Retired item 77 (1991):** Byte 13 in the Load Symbol Set command is retired for the IBM 3270 architecture. Retired for "Height pairs"; this byte indicates the number of pairs of height-indentation values specified in the Symbol Envelope Table (SET) parameter. The IBM GOCA architecture specifies the form of the SET. If the SET is not present or not supported (byte 5, bit 4), this field can be ignored. This field has no significance for IPDS printers. | ❓ |
| IPDS-D-103 | Retired item 78 (1991):** Byte 14 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte has been reserved for future function by IBM GOCA. This field has no significance for IPDS printers. | ❓ |
| IPDS-D-104 | Retired item 79 (1991):** Bytes 17&ndash;i in the Load Symbol Set command are retired for the IBM 3270 architecture and are intended to be reserved for future expansion by all architectures. These are an arbitrary number of reserved bytes derived from the value in byte 4. These bytes have no significance for IPDS printers. | ❓ |
| IPDS-D-105 | Byte 4:** Reserved | ❓ |
| IPDS-D-106 | Byte 5:** Format identifier, X'03' | ❓ |
| IPDS-D-107 | Bytes 6&ndash;7:** Channel adapter trace register | ❓ |
| IPDS-D-108 | Bytes 8&ndash;9:** Channel Adapter Status register | ❓ |
| IPDS-D-109 | Bytes 10&ndash;11:** Channel adapter error log register | ❓ |
| IPDS-D-110 | Bytes 12&ndash;13:** Channel configuration register 1 | ❓ |
| IPDS-D-111 | Byte 14:** Channel command register | ❓ |
| IPDS-D-112 | Byte 15:** Channel (host) status register | ❓ |
| IPDS-D-113 | Byte 16:** Channel adapter request register | ❓ |
| IPDS-D-114 | Byte 17:** Device status table entry | ❓ |
| IPDS-D-115 | Bytes 18&ndash;19:** Reserved | ❓ |
| IPDS-D-116 | Bytes 20&ndash;21:** Channel buffer pointer register | ❓ |
| IPDS-D-117 | Bytes 22&ndash;23:** Reserved | ❓ |
| IPDS-D-118 | Byte 4:** Zero | ❓ |
| IPDS-D-119 | Byte 5:** Format identifier, X'04' | ❓ |
| IPDS-D-120 | Bytes 6&ndash;23:** Zero | ❓ |
| IPDS-D-121 | Byte 4:** Reserved | ❓ |
| IPDS-D-122 | Byte 5:** Format identifier, X'05' | ❓ |
| IPDS-D-123 | Byte 6:** Physical-interface identifier | ❓ |
| IPDS-D-124 | Bytes 7&ndash;8:** Link adapter A basic status register | ❓ |
| IPDS-D-125 | Byte 9:** Link adapter A error log register byte 1 | ❓ |
| IPDS-D-126 | Bytes 10&ndash;12:** Link adapter A link error log | ❓ |
| IPDS-D-127 | Bytes 13&ndash;14:** Link adapter B basic status register | ❓ |
| IPDS-D-128 | Byte 15:** Link adapter B error log register byte 1 | ❓ |
| IPDS-D-129 | Bytes 16&ndash;18:** Link adapter B link error log | ❓ |
| IPDS-D-130 | Byte 19:** Link adapter indicator | ❓ |
| IPDS-D-131 | Byte 20:** Reserved | ❓ |
| IPDS-D-132 | Byte 21:** VCU ID (0&ndash;15 Link A, 16&ndash;31 Link B) | ❓ |
| IPDS-D-133 | Bytes 22&ndash;23:** Virtual Error Log for VCU ID | ❓ |
| IPDS-D-134 | Retired item 83 (1990):** Action code X'00' (no error outstanding) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-135 | Retired item 84 (1990):** Action code X'02' (operator intervention with OBR record) as specified in sense byte 2 is retired for channel-attached and TCP/IP-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-136 | Retired item 85 (1990):** Action code X'03' (operator intervention without OBR record) as specified in sense byte 2 is retired for channel-attached and TCP/IP-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-137 | Retired item 86 (1990):** Action code X'04' (channel error) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-138 | Retired item 87 (1991):** Action code X'18' (transparent error) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-139 | Retired item 88 (1990):** Action code X'1C' (Sense Extended CCW required) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-140 | Retired item 89 (1993):** Action code X'24' (printer not assigned) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-141 | Retired item 90 (1993):** Action code X'25' (printer assigned elsewhere) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-142 | Retired item 91 (1993):** Action code X'4D' (resetting event) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-143 | Retired item 92 (1991):** Exception class X'20' as specified in sense byte 0 is retired for Bus-Out Parity Check, reserved for compatibility with channel-attached printers. | ❓ |
| IPDS-D-144 | Retired item 93 (1991):** IPDS command code X'D600' is retired for the IBM 3800 printer Test I/O command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. | ❓ |
| IPDS-D-145 | Retired item 94 (1991):** IPDS command code X'D604' is retired for the IBM 3800 printer Sense I/O command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. | ❓ |
| IPDS-D-146 | Retired item 95 (1991):** IPDS command code X'D60D' is retired for the IBM 3800 printer Write Factored Text Control command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. | ❓ |
| IPDS-D-147 | Retired item 96 (1991):** IPDS command code X'D614' is retired for the IBM 3800 printer Sense Intermediate Buffer command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. | ❓ |
| IPDS-D-148 | Retired item 97 (1991):** IPDS command code X'D624' is retired for the IBM 3800 printer Sense Error Log command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. | ❓ |
| IPDS-D-149 | Bit 0 of byte 37 in the Load Font Control command had previously been retired for use by the IBM 3835 printer with a particular MICR device. The flag was later unretired and defined as the "intended for MICR printing" flag. | ❓ |
| IPDS-D-150 | Retired item 99 (1991):** Action code X'07' (retry error log full) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-151 | Retired item 100 (1991):** Action code X'0B' (process power error) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-152 | Retired item 101 (1991):** Action code X'0E' (not enough storage, page printed using the accumulator feature) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-153 | Retired item 102 (1991):** Action code X'0F' (accumulator read check) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-154 | Retired item 103 (1991):** Action code X'10' (reload electronic overlay or base page) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-155 | Retired item 104 (1991):** Action code X'11' (count continuous-forms stacker fold wrong errors) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-156 | Retired item 105 (1991):** Action code X'12' (count burster input checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-157 | Retired item 106 (1991):** Action code X'13' (count no burst checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-158 | Retired item 107 (1991):** Action code X'14' (count burster-trimmer-stacker stacker/trimmer checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. | ❓ |
| IPDS-D-159 | Retired item 108 (1991):** X'8000' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOA-NOP; see retired item 18. | ❓ |
| IPDS-D-160 | Retired item 109 (1991):** X'8001' property pair in the Device-Control vector of the Sense Type & Model reply is retired for environment printing; see retired item 19. | ❓ |
| IPDS-D-161 | Retired item 110 (1991):** X'8002' property pair in the Device-Control vector of the Sense Type & Model reply is retired for ringing a bell and for IBM 3800 printer Read Font List; see retired items 20 and 21. | ❓ |
| IPDS-D-162 | Retired item 111 (1991):** X'8006' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Mark Form Carrier Strip; see retired item 22. | ❓ |
| IPDS-D-163 | Retired item 112 (1991):** X'80F1' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Display Operator Panel Message; see retired item 25. | ❓ |
| IPDS-D-164 | Retired item 113 (1991):** X'80F3' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Request Printer Information; see retired item 26. | ❓ |
| IPDS-D-165 | Retired item 114 (1991):** X'9000' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOH-NOP; see retired item 31. | ❓ |
| IPDS-D-166 | Retired item 115 (1991):** X'900B' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOH-Set X Adjustment Range; see retired item 32. | ❓ |
| IPDS-D-167 | Retired item 116 (1991):** X'90F4' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOH-Inhibit Automatic WCGM Load; see retired item 33. | ❓ |
| IPDS-D-168 | Bit 1:** Ordered blocks flag. A value of B'1' indicates that all text, image, graphics, or bar code blocks are in sequential order (the presentation of sequentially received blocks as opposed to data within blocks). No backward movement is required. A value of B'0' indicates that backward movement might be required. | ❓ |
| IPDS-D-169 | Bit 2:** Ordered text flag. A value of B'1' indicates that all text data within every page, either in text blocks or as text data, is in sequential order. No backward movement is required. A value of B'0' indicates that some text data on a page might require backward movement. | ❓ |
| IPDS-D-170 | B'1':** The media size in bytes 10&ndash;13 and printable-area size in bytes 18&ndash;21 might be invalid. | ❓ |
| IPDS-D-171 | B'0':** The media size in bytes 10&ndash;13 and printable-area size in bytes 18&ndash;21 are valid. | ❓ |
| IPDS-D-172 | Retired item 120 (1993):** Byte 36 of the Load Font Control command is retired as a non-stageable indicator. A value of X'00' indicates that the font is stageable. A value of X'01' indicates that the font is non-stageable. A stageable font is a font that is to be stored on a non-volatile media, such as a floppy disk, assuming such a storage means is available. | ❓ |
| IPDS-D-173 | Retired item 121 (1991):** Intermediate Device Type code X'0003' in XOH-OPC Product Identifier self-defining field parameter ID X'0002' is retired to indicate that the intermediate device is an IBM 4019 IPDS - PPDS/HPCL Protocol Converter. | ❓ |
| IPDS-D-174 | Retired item 122 (1993):** Byte 5 of the LFCSC command is retired for version of pattern technology. | ❓ |
| IPDS-D-175 | This item covers the Write Text Control (WTC) command, which had been in the IPDS architecture, but was retired until a product implementation became available. | ❓ |
| IPDS-D-176 | X'2001' property pair in the text command-set vector of the Sense Type and Model reply had previously been retired for text block support using the Write Text Control command. The property pair now indicates support for text objects and the Write Text Control (WTC) command. | ❓ |
| IPDS-D-177 | Bit 0:** If B'1', indicates that the printer is in the ready state. If B'0', indicates that the printer is in the not-ready state. | ❓ |
| IPDS-D-178 | Bits 1&ndash;6:** Reserved | ❓ |
| IPDS-D-179 | Bit 7:** Set to B'1' to indicate that the printer operates in page mode only. | ❓ |
| IPDS-D-180 | Retired item 126 (1993):** X'90D0' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOH-Select Output Stacker; see retired item 127. | ❓ |
| IPDS-D-181 | Retired item 127 (1993):** X'D000' order code of the Execute Order Home State command is retired for Select Output Stacker (SOS); see retired item 126. | ❓ |
| IPDS-D-182 | X'81':** Resident single-byte LF1-type or LF2-type coded-font components | ❓ |
| IPDS-D-183 | X'82':** Resident double-byte LF1-type coded-font components | ❓ |
| IPDS-D-184 | X'83':** Resident double-byte LF1-type coded-font-section components | ❓ |
| IPDS-D-185 | X'84':** Resident page segment | ❓ |
| IPDS-D-186 | X'85':** Resident overlay | ❓ |
| IPDS-D-187 | X'86':** Resident code page | ❓ |
| IPDS-D-188 | X'87':** Resident font character set | ❓ |
| IPDS-D-189 | X'88':** Resident single-byte coded-font index | ❓ |
| IPDS-D-190 | X'89':** Resident double-byte coded-font section index | ❓ |
| IPDS-D-191 | Retired item 129 (1994):** StampType X'01' in the Local Date and Time Stamp (X'62') triplet is retired for use by the IBM RMARK utility programs. This StampType indicates that a resource object was marked by the IBM RMARK utility. | ❓ |
| IPDS-D-192 | Retired item 130 (1996):** The Standard OCA Color Value Support self-defining field (formerly in the XOH-OPC reply) is retired for use by older IPDS printers. New IPDS printers should not return this self-defining field. The Standard OCA Color Value Support self-defining field specifies the set of color values that are supported for all multiple-color data types supported. The STM reply indicates for each of the data types (text, image, graphics, or bar code) whether or not multiple-color values are supported for that data type. This field must be returned by printers that indicate multiple-color support in the STM reply. The absence of this self-defining field indicates that the printer supports the single color black along with the color values X'0008' (black), and X'FF07' (printer default). | ❓ |
| IPDS-D-193 | 0&ndash;1 | UBIN | SDF length | X'0006' &ndash; X'7FFE' | Length of this self-defining field, including itself | ❓ |
| IPDS-D-194 | 2&ndash;3 | CODE | SDF ID | X'0005' | Standard OCA Color Value Support self-defining field ID | ❓ |
| IPDS-D-195 | One or more color-table values in the following format:** | ❓ |
| IPDS-D-196 | +0&ndash;1 | CODE | Color value | X'0001'&ndash;X'0010' | True colors supported by the printer:<br>X'0001' Blue<br>X'0002' Red<br>X'0003' Pink/magenta<br>X'0004' Green<br>X'0005' Turquoise/cyan<br>X'0006' Yellow<br>X'0007' White or black (black for printers)<br>X'0008' Black<br>X'0009' Dark blue<br>X'000A' Orange<br>X'000B' Purple<br>X'000C' Dark green<br>X'000D' Dark turquoise<br>X'000E' Mustard<br>X'000F' Gray<br>X'0010' Brown | ❓ |
| IPDS-D-197 | 1. These colors are listed for compatibility with the IBM GOCA architecture and are unsupported. | ❓ |
| IPDS-D-198 | 2. The current architecture does not define color-support self-defining fields in the XOH-OPC response for specific data types even though the STM color-support property-pairs are generated for specific data types. | ❓ |
| IPDS-D-199 | Retired item 131 (1998):** XOA-RRL query type X'FE' is retired for a single-entry general query with resource ID triplets.Identifies this as a general query identical to query type X'FF' except that resource ID triplets are also returned as part of the resource ID in the reply. These resource ID triplets are not returned with a X'FF'-type query. This is an optional query type. Support for this query type is indicated by property pair X'F402' in the Device-Control command-set vector of the STM reply. Query type X'FE' may only be used with single-entry queries. Both list and individual queries are supported with query type X'FE' and both kinds of query can return multiple reply entries; each resource that matches the query is returned in a separate resource-list reply entry that includes all triplets specific to that version of the resource. | ❓ |
| IPDS-D-200 | Retired item 132 (1999):** Media ID type X'01' in the XOH-OPC reply Printable-Area self-defining field is retired for ISO/DPA registered media values. This is an integer corresponding to the leaf element of the DPA Standard Object ID (OID) for the physical medium identified under the medium object class. The input media ID (in bytes 27&ndash;end) contains only the characters 0&ndash;9 using the code points assigned in IBM code page 500. These values are defined in ISO Draft International Standard 10175-1 "Information Technology - Text and Office Systems - Document Printing Application (DPA)". | ❓ |
| IPDS-D-201 | Retired item 133 (2000):** Bit 4 of LCPC byte n+13 (processing flags for the default GCGID) is retired for a flag to distinguish some code page types. This flag was set to B'1' in outline code pages for Japanese, Korean, Simplified Chinese, and Traditional Chinese; it is set to B'0' for raster code pages and Unicode code pages. The reason for the flag being set to B'1' is unknown. The FOCA architecture does not assign a meaning to this flag. | ❓ |
| IPDS-D-202 | Retired item 134 (2000):** IPDS command code X'D61C' is retired for the Load HAID List (LHL) command. This command permits resources that are nested in a nesting resource via Include commands or local IDs to be referenced externally using a new Host-Assigned ID (HAID). The LHL command allows the replacement of an old HAID with a new HAID in the Include or LFE command for each nested resource. | ❓ |
| IPDS-D-203 | Retired item 135 (2001):** Replicate-and-trim mapping option for graphics (property pair X'F300') is retired. This option was originally defined for both IO Image and for graphics, but was later restricted to just IO Image because no IPDS printer implemented this mapping option for graphics. | ❓ |
| IPDS-D-204 | Retired item 136 (2006):** E-mail Setup File object OID (used by IBM Infoprint Manager) | ❓ |
| IPDS-D-205 | X'0607 2B12 0004 0101 2500 0000 0000 0000' | E-mail Setup File | Non-presentation | Not applicable | Home state Setup file | ❓ |
| IPDS-D-206 | Retired item 137 (2006):** Fax Setup File object OID (used by IBM Infoprint Manager) | ❓ |
| IPDS-D-207 | X'0607 2B12 0004 0101 2400 0000 0000 0000' | Fax Setup File | Non-presentation | Not applicable | Home state Setup file | ❓ |
| IPDS-D-208 | Retired item 138 (2006):** Infoprint 2000 Setup File object OID (used by IBM Infoprint Manager) | ❓ |
| IPDS-D-209 | X'0607 2B12 0004 0101 2600 0000 0000 0000' | Infoprint 2000 printer Setup File | Non-presentation | Not applicable | Home state Setup file | ❓ |
| IPDS-D-210 | Retired item 139 (2006):** STM Bar Code property pair X'1301' is retired for "ASCII-based code pages supported". | ❓ |
| IPDS-D-211 | Retired item 140 (2008):** Bytes 8&ndash;11 in the Object Offset (X'5A') triplet are retired for a high-order extension parameter. This parameter is not used within IPDS data streams, but is defined in the MO:DCA architecture to provide a means to allow a larger object offset value to be specified. IPDS printers ignore the value in this field. | ❓ |
| IPDS-D-212 | Retired item 141 (2011):** XOA order X'0700' is retired for use in Océ printers and server software; used for extended features control. | ❓ |
| IPDS-D-213 | Retired item 142 (2011):** XOA order X'0900' is retired for use in Océ printers and server software; used to enable two-up. | ❓ |
| IPDS-D-214 | Retired item 143 (2011):** XOA order X'CE00' is retired for use in Océ printers and server software; used for cancel synchronization. | ❓ |
| IPDS-D-215 | Retired item 144 (2011):** XOH order X'1C00' is retired for use in Océ printers and server software; used for two-up control. | ❓ |
| IPDS-D-216 | Retired item 145 (2011):** XOH order X'1D00' is retired for use in Océ printers and server software; used to select media destination. | ❓ |
| IPDS-D-217 | Retired item 146 (2011):** XOH order X'4C00' is retired for use in Océ printers and server software; used for cut-sheet emulation (CSE) controls. | ❓ |
| IPDS-D-218 | Retired item 147 (2011):** XOH order X'4D00' is retired for use in Océ printers and server software; used for the PoD HD option. | ❓ |
| IPDS-D-219 | Retired item 148 (2013):** XOH order X'4E00' is retired for use in Océ printers and server software; used for configurations. | ❓ |
| IPDS-D-220 | Retired item 149 (2021):** Property pair X'120B' is retired because it reports support of a functionality that cannot actually work. Property pair X'120B' indicates support for mapping TrueType/OpenType Fonts in the DORE command, for all presentation objects supported by the printer and for which TrueType/OpenType Fonts are valid secondary resources. The DORE command cannot be used for this purpose, since in IPDS, a TrueType/OpenType font used as a secondary resource uses a HAID in the data-object-font-component HAID pool, but the HAIDs in the DORE are only searched for in the data-object-resource HAID pool. Instead, the DORE2 command must be used for this purpose, and that functionality is reported with the X'120D' property pair. | ❓ |
