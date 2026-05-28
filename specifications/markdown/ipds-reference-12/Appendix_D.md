# Appendix D. Retired Items

This appendix lists each retired item that is mentioned within the body of this book and also lists those items that have been unretired. [IPDS-D-001]

**Retired item 1 (1991):** This retired item was unretired in 1993.
X'FF' in the acknowledge type field (byte 0) of the Acknowledge Reply had previously been retired for use by the 370 channel-attached printers to indicate a Null ACK. This value is now used to cover the error case where the communications protocol that carries the IPDS commands attempts to obtain a positive Acknowledge Reply (ACK) without first sending an IPDS command with the ARQ bit set to B'1'. [IPDS-D-002]

**Retired item 2 (1991):** X'02' in the unit base field (byte 1) of the Define User Area command is retired for relative units. [IPDS-D-003]

**Retired item 3 (1990):** X'FF' in the font local ID field (byte 0) of the Load Font Equivalence command is retired for selecting a default font. [IPDS-D-004]

**Retired item 4 (1991):** X'02' in the unit base field (byte 0) of the Logical Page Descriptor command is retired for relative units. [IPDS-D-005]

**Retired item 5 (1991):** Byte 1 of the Logical Page Descriptor command is retired for the IBM 3820 printer (unit base for Y coordinate). [IPDS-D-006]

**Retired item 6 (1991):** Byte 18 of the Logical Page Descriptor command is retired for the IBM 3820 printer (escape code). [IPDS-D-007]

**Retired item 7 (1991):** Byte 20 of the Logical Page Descriptor command is retired for the IBM 3820 printer (bit controls). [IPDS-D-008]

**Retired item 8 (1991):** Byte 21 of the Logical Page Descriptor command is retired for the IBM 3820 printer (data checks). [IPDS-D-009]

**Retired item 9 (1991):** X'02' in the resource type field (Byte 2) of the Activate Resource command is retired for double-byte coded font. [IPDS-D-010]

**Retired item 10 (1991):** This retired item was unretired in 1991.
X'05' in the resource type field (Byte 2) of the Activate Resource command had previously been retired because there was no method of refreshing nested resource HAIDs in an overlay. If resident overlays are supported, nested HAIDs must be managed by the presentation services program. [IPDS-D-011]

**Retired item 11 (1991):** This retired item was unretired in 1993.
X'06' in the resource type field (Byte 2) of the Activate Resource command had previously been retired for code page. This value was unretired for use with outline fonts. [IPDS-D-012]

**Retired item 12 (1991):** This retired item was unretired in 1993.
X'07' in the resource type field (Byte 2) of the Activate Resource command had previously been retired for font character set. This value was unretired for use with outline fonts. [IPDS-D-013]

**Retired item 13 (1991):** This retired item was unretired in 1992.
X'06' in the resource ID format field (Byte 6) of the Activate Resource command had previously been retired for MVS Host Unalterable Font Environment. The value was unretired because it is used by some IPDS products. [IPDS-D-014]

**Retired item 14 (1991):** Bit 1 of the resource class flags field (byte 11) in the Activate Resource command is retired for the IBM RPM MVS product (used as a save/no save flag). [IPDS-D-015]

**Retired item 15 (1991):** X'20nn' property pair in the IO-Image command-set vector of the Sense Type and Model reply is retired as a resolution-correction support property ID. Where "nn" is a bit-mapped byte:
*   **Bits 0&ndash;2:** Reserved [IPDS-D-016]
*   **Bit 3:** Resolution correction algorithms to minimize information loss (for example, via nearest neighbor averaging techniques as opposed to discarding pels) [IPDS-D-017]
*   **Bit 4:** Resolution correction ratio may be any positive real number [IPDS-D-018]
*   **Bit 5:** Integer fraction (1/2, 1/3, ...) resolution correction supported (for example, 1/2 = discard every other pel) [IPDS-D-019]
*   **Bit 6:** Integer (x2, x3, ...) resolution correction supported for example, x2 = double every pel) [IPDS-D-020]
*   **Bit 7:** Resolution correction supported [IPDS-D-021]

**Retired item 16 (1991):** X'30nn' property pair in the IO-Image command-set vector of the Sense Type and Model reply is retired as a scaling-support property ID. Where "nn" is a bit-mapped byte:
*   **Bits 0&ndash;2:** Reserved [IPDS-D-022]
*   **Bit 3:** Scaling algorithms to minimize information loss (for example, via nearest neighbor averaging techniques as opposed to discarding pels) [IPDS-D-023]
*   **Bit 4:** Scaling ratio may be any positive real number [IPDS-D-024]
*   **Bit 5:** Integer fraction (1/2, 1/3, ...) scaling supported (for example, 1/2 = discard every other pel) [IPDS-D-025]
*   **Bit 6:** Integer (x2, x3, ...) scaling supported (for example, x2 = double every pel) [IPDS-D-026]
*   **Bit 7:** Scaling supported [IPDS-D-027]

**Retired item 17 (1991):** The range of values X'06'&ndash;X'FF' in property pair X'C0nn' in the Loaded-Font command-set vector of the Sense Type and Model reply is retired for outline-font pattern-technology IDs. The values X'1E' and X'1F' were defined and unretired in 1993. [IPDS-D-028]

**Retired item 18 (1991):** X'0000' order code of the Execute Order Anystate command is retired for IBM 3820-0, 3825-1, 3827-1, 3835-1, 3831, and 3827E printers as a NOP. [IPDS-D-029]

**Retired item 19 (1991):** X'0001' order code of the Execute Order Anystate command is retired for IBM 3812 model 2 and IBM 3816 models 11 and 12 printers as a method for printing the environment when a special debug port is attached to the printer. [IPDS-D-030]

**Retired item 20 (1991):** X'0002' order code of the Execute Order Anystate command is retired for IBM 3812 model 2 and IBM 3816 models 11 and 12 printers as a method for ringing the bell on the printer. [IPDS-D-031]

**Retired item 21 (1991):** X'0200' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Read Font List order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. [IPDS-D-032]

**Retired item 22 (1991):** X'0600' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Mark Form Carrier Strip order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. [IPDS-D-033]

**Retired item 23 (1991):** X'7BF5' order code of the Execute Order Anystate command is retired for IBM 3816 model 11 and 12 printers as a method for setting soft switches in configuration mode. [IPDS-D-034]

**Retired item 24 (1991):** X'CACA' order code of the Execute Order Anystate command is retired for IBM 3812-2, 3816-011, and 3816-012 printers as a method for escaping from IPDS to Page Map Primitives (PMP); PMP is the machine-level language for the IBM 3812 printer. [IPDS-D-035]

**Retired item 25 (1991):** X'F100' order code of the Execute Order Anystate command is retired for the IBM 3800 printer Display Operator Panel Message order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. [IPDS-D-036]

**Retired item 26 (1991):** X'F300' order code of the Execute Order Anystate command is retired for the IBM 3800 printer and IBM 3820 printer Request Printer Information order. The syntax of this order is different for the two printers; refer to the specific printer Reference Manual (GA32-0050 and S544-3175) for a description of this command. In addition, the IBM Print Services Facility (PSF) has defined a meaning for several of the reserved bytes in the IBM 3820 printer version of the XOA-RPI reply. These include:
*   Byte 2, bit 2, B'1' means that the IBM 3820 printer is connected through IBM Remote PrintManager. [IPDS-D-037]
*   Byte 2, bit 3, B'1' means that the IBM 3820 is an IBM 3820-ROM printer. [IPDS-D-038]
*   Byte 2, bit 4, B'1' means IBM Remote PrintManager group operations. [IPDS-D-039]
*   Byte 2, bit 5, B'1' means that an intermediate device is attached using the IBM Distributed Print Feature (DPF). [IPDS-D-040]
*   Byte 2, bit 6, B'1' means that an intermediate device is attached using IBM PSF Direct. [IPDS-D-041]
*   Bytes 4&ndash;5 contain a hexadecimal number (either X'0200' or X'0280') describing the amount of control store in kilobytes. [IPDS-D-042]
*   Bytes 6&ndash;7 contain a hexadecimal number (from X'0100' to X'1000' in X'0100' or greater increments) describing the amount of pattern store in kilobytes. [IPDS-D-043]

**Retired item 27 (1991):** This retired item was unretired in 1991.
X'05' in the ordering field (byte 2) of the XOA-Request Resource List command had previously been retired for the IBM Remote PrintManager product. The product used this value before it was defined in the architecture and, once discovered, it was added to the architecture. [IPDS-D-044]

**Retired item 28 (1991):** X'01' in the resource ID format field (byte 7) of the XOA-Request Resource List command is retired for the IBM 3820 printer. The IBM 3820 printer allows only the value X'00' for the RIDF in an XOA-RRL request, but returns one of the following values in the reply:
*   X'00' Downloaded resource; not present in the printer [IPDS-D-045]
*   X'01' Downloaded resource; in control storage [IPDS-D-046]
*   X'02' Downloaded resource; in pattern storage [IPDS-D-047]

**Retired item 29 (1991):** X'02' in the resource ID format field (byte 7) of the XOA-Request Resource List command is retired for the IBM 3820 printer. Refer to retired item 28 for more information. [IPDS-D-048]

**Retired item 30 (1991):** X'06' in the resource ID format field (byte 7) of the XOA-Request Resource List command is retired for MVS Host Unalterable Font Environment. [IPDS-D-049]

**Retired item 31 (1991):** X'0000' order code of the Execute Order Home State command is retired for IBM 3820-0, 3825-1, 3827-1, 3835-1, 3831, and 3827E printers as a NOP. [IPDS-D-050]

**Retired item 32 (1991):** X'0B00' order code of the Execute Order Home State command is retired for the IBM 3800 printer Set X Adjustment Range order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. [IPDS-D-051]

**Retired item 33 (1991):** X'F400' order code of the Execute Order Home State command is retired for the IBM 3800 printer Inhibit Automatic WCGM Load order. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this order. [IPDS-D-052]

**Retired item 34 (1991):** X'02' in the unit base field (byte 6) of the Printable-Area self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. [IPDS-D-053]

**Retired item 35 (1991):** X'02' in the unit base field (byte 2) of the Variable-Box Size values in the Symbol-Set Support self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. [IPDS-D-054]

**Retired item 36 (1991):** X'02' in the unit base field (byte 4) of the IM-Image and Coded-Font Resolution self-defining field in the XOH-Obtain Printer Characteristics reply is retired for relative units. [IPDS-D-055]

**Retired item 37 (1991):** This retired item was unretired in 1991.
X'0200' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a manual two-channel switch. [IPDS-D-056]

**Retired item 38 (1991):** This retired item was unretired in 1991.
X'0201' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a tightly coupled two-channel switch. [IPDS-D-057]

**Retired item 39 (1991):** X'0202' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for a loosely coupled two-channel switch. [IPDS-D-058]

**Retired item 40 (1991):** X'0400' in the Installed Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for stapler. [IPDS-D-059]

**Retired item 41 (1991):** This retired item was unretired in 1991.
X'0200' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a manual two-channel switch. [IPDS-D-060]

**Retired item 42 (1991):** This retired item was unretired in 1991.
X'0201' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for channel-attached printers. It is used to indicate a tightly coupled two-channel switch. [IPDS-D-061]

**Retired item 43 (1991):** X'0202' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for a loosely coupled two-channel switch. [IPDS-D-062]

**Retired item 44 (1991):** X'0400' in the Available Features self-defining field in the XOH-Obtain Printer Characteristics reply is retired for stapler. [IPDS-D-063]

**Retired item 45 (1991):** X'02' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply is retired for double-byte coded fonts. [IPDS-D-064]

**Retired item 46 (1991):** This retired item was unretired in 1991.
X'05' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for overlay. This value was unretired when retired item 10 was unretired. [IPDS-D-065]

**Retired item 47 (1991):** This retired item was unretired in 1993.
X'06' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for code page. This value was unretired for use with outline fonts. [IPDS-D-066]

**Retired item 48 (1991):** This retired item was unretired in 1993.
X'07' in the resource-type byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for font character set. This value was unretired for use with outline fonts. [IPDS-D-067]

**Retired item 49 (1991):** This retired item was unretired in 1992.
X'06' in the Resource-ID-Format byte of the Activate Resource RT & RIDF Support self-defining field in the XOH-Obtain Printer Characteristics reply had previously been retired for IBM MVS Host Unalterable Remote Font Environments. This value was unretired when retired item 13 was unretired. [IPDS-D-068]

**Retired item 50 (1991):** X'0000' self-defining product-identifier parameter ID in the Product Identifier self-defining field in the XOH-Obtain Printer Characteristics reply is retired for IBM 4224 and IBM 4234 printers. This product-identifier parameter ID indicates that the stack page counter is implemented as specified by the IPDS Architecture. There is no data in bytes 3&ndash;end, that is, byte 0 = X'03'. [IPDS-D-069]

**Retired item 51 (1991):** X'02' in the unit base field (byte 2) of the XOH-Set Media Size command is retired for relative units. [IPDS-D-070]

**Retired item 52 (1991):** X'0200' in the type field (bytes 0&ndash;1) of the Load Equivalence command is retired for the IBM Remote PrintManager product. It is used to map the current value of Host-Assigned IDs of page segments included within previously stored overlays onto the value in effect at the time they were first downloaded to IBM Remote PrintManager. [IPDS-D-071]

**Retired item 53 (1991):** X'02' in the unit base field (byte 4) of the Text Output Control self-defining field in the Write Text Control command is retired for relative units. [IPDS-D-072]

**Retired item 54 (1991):** X'02' in the unit base field (byte 6) of the Text Data Descriptor self-defining field in the Write Text Control command is retired for relative units. [IPDS-D-073]

**Retired item 55 (1991):** X'02' in the unit base field (byte 4) of the Image Output Control self-defining field in the Write Image Control 2 command is retired for relative units. [IPDS-D-074]

**Retired item 56 (1991):** X'02' in the unit base field (byte 6) of the Image Data Descriptor self-defining field in the Write Image Control 2 command is retired for relative units. [IPDS-D-075]

**Retired item 57 (1991):** X'02' in the unit base field (byte 4) of the Graphics Output Control self-defining field in the Write Graphics Control command is retired for relative units. [IPDS-D-076]

**Retired item 58 (1991):** X'02' in the unit base field (byte 4) of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for relative units. [IPDS-D-077]

**Retired item 59 (1991):** Bytes 22&ndash;25 of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for the $Z_{g}$ coordinates of the graphics presentation space window. [IPDS-D-078]

**Retired item 60 (1991):** Bytes 26&ndash;27 of the Graphics Data Descriptor self-defining field in the Write Graphics Control command is retired for graphic data flags. [IPDS-D-079]

**Retired item 61 (1991):** X'02' in the unit base field (byte 4) of the Bar Code Output Control self-defining field in the Write Bar Code Control command is retired for relative units. [IPDS-D-080]

**Retired item 62 (1991):** X'02' in the unit base field (byte 4) of the Bar Code Data Descriptor self-defining field in the Write Bar Code Control command is retired for relative units. [IPDS-D-081]

**Retired item 63 (1991):** Bit 4 of the flags byte (byte 0) in the Write Bar Code command is retired in the BCOCA architecture for PC ASCII data stream use; this flag indicates support for ASCII data in a BCOCA object. [IPDS-D-082]

**Retired item 64 (1991):** Bit 7 of the flags byte (byte 0) in the Write Bar Code command is retired in the BCOCA architecture for PC ASCII data stream use; in particular this flag is used by the IBM Personal Printer Data Stream (PPDS). [IPDS-D-083]

**Retired item 65 (1991):** X'02' in the unit base for pel-units field (byte 26) of the Load Font Control command is retired for relative units. [IPDS-D-084]

**Retired item 66 (1991):** Bit 0 of the flags1 field (byte 0) in the Load Symbol Set command is retired for the IBM 3270 architecture. This bit indicates "Extended Form" in the IBM 3270 architecture. That architecture allows a shorter form of this command, indicated by a value of B'0' in this bit. [IPDS-D-085]

**Retired item 67 (1991):** Bit 1 of the flags1 field (byte 0) in the Load Symbol Set (LSS) command is retired for the IBM 3270 architecture. This is the Clear bit in IBM 3270 and IBM GOCA architectures. This value indicates that the LSS data loaded with this command overwrites the font data for any existing matching code points with the same Loaded Font ID. There are only two cases:
*   If no previous LSS font or coded font with a font identifier matching bytes 15&ndash;16 exists in the printer, this command establishes a new LSS font. [IPDS-D-086]
*   If a previous LSS font or coded font with a font identifier matching bytes 15&ndash;16 exists in the printer, the information transmitted by this command replaces some or all of the existing control and all of the existing raster information about the matching code points, regardless of whether the existing information was loaded by a previous Load Symbol Set command or via the coded font commands (Load Font Control, Load Font Index, and Load Font). [IPDS-D-087]
The IBM GOCA and IBM 3270 architectures allow font deletion via a value of B'1' for this bit. IPDS font deletion is done only via the Deactivate Font command. [IPDS-D-088]

**Retired item 68 (1991):** Bit 2 of the flags1 field (byte 0) in the Load Symbol Set command is retired for the IBM 3270 architecture. A value of B'1' is used by the IBM GOCA architecture and IBM 3270 architecture to request a function known as "skip suppression" that, for IPDS printers, is better done via control sequences or, implicitly, by the font design. [IPDS-D-089]

**Retired item 69 (1991):** Byte 1 in the Load Symbol Set command is retired for the IBM 3270 architecture. The local ID specified in this byte has no significance for IPDS printers since the one-byte local identifier used by the Graphics Set Character Set, Push and Set Character Set, Set Marker Set, and Push and Set Marker Set orders; by the Bar Code LID; and by the Text SCFL are mapped to the Font Host-Assigned ID (LSS bytes 15&ndash;16) via the Load Font Equivalence command. [IPDS-D-090]

**Retired item 70 (1991):** Byte 3 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte is the read/write storage (RWS) number in IBM GOCA and IBM 3270 architectures; it has no significance for IPDS printers. [IPDS-D-091]

**Retired item 71 (1991):** Bits 0&ndash;2 of the flags2 field (byte 5) in the Load Symbol Set command are retired for the IBM 3270 architecture. These flag bits are used to control the all points addressable (APA), LCID compare bit (CB), and operator selectable by PS key (OB) functions respectively for IBM GOCA and IBM 3270 architectures. These functions are not currently supported by IPDS printers hence the bit values B'011' effectively disable these functions. [IPDS-D-092]

**Retired item 72 (1991):** Bit 3 of the flags2 field (byte 5) in the Load Symbol Set command is retired for the IBM 3270 architecture; this bit has no significance for IPDS printers. This bit specifies the multiple LCID (MULTID) setting in IBM GOCA and IBM 3270 architectures. [IPDS-D-093]

**Retired item 73 (1991):** Bit 4 of the flags2 field (byte 5) in the Load Symbol Set command is retired for the IBM 3270 architecture; this bit has no significance for IPDS printers. Retired for "Use Symbol Envelope Table". A value of B'1' indicates that the Symbol Envelope Table (SET) information (from a triplet specified in another LSS field) is to be applied to this symbol set when these symbols are used within graphics data. It is an exception if this value is specified and no SET exists in the triplet field. Refer to the IBM GOCA specification for a description of the SET. A value of B'0' indicates that the SET, if present, is not to be used. [IPDS-D-094]

**Retired item 74 (1991):** Byte 9 in the Load Symbol Set command is retired for the IBM 3270 architecture "Color plane". This field has no significance for IPDS printers. The bits of this byte identify the color planes to be loaded with the raster patterns that follow:
*   **Bits 0&ndash;4:** Reserved [IPDS-D-095]
*   **Bit 5:** Green plane [IPDS-D-096]
*   **Bit 6:** Red plane [IPDS-D-097]
*   **Bit 7:** Blue plane [IPDS-D-098]
For example, a value of X'03' indicates that the blue and red planes are to be loaded. A value of X'00' indicates that all color planes are to be loaded. [IPDS-D-099]

**Retired item 75 (1991):** Byte 10 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte specifies the Starting Subsection Identifier in the IBM 3270 architecture. It has no significance for IPDS printers. [IPDS-D-100]

**Retired item 76 (1991):** Byte 12 in the Load Symbol Set command is retired for the IBM 3270 architecture. Retired for "Width pairs"; this byte indicates the number of pairs of width-indentation values specified in the Symbol Envelope Table (SET) parameter. IBM GOCA architecture specifies the form of the SET. If the SET is not present or not supported (byte 5, bit 4), this field can be ignored. This field has no significance for IPDS printers. [IPDS-D-101]

**Retired item 77 (1991):** Byte 13 in the Load Symbol Set command is retired for the IBM 3270 architecture. Retired for "Height pairs"; this byte indicates the number of pairs of height-indentation values specified in the Symbol Envelope Table (SET) parameter. The IBM GOCA architecture specifies the form of the SET. If the SET is not present or not supported (byte 5, bit 4), this field can be ignored. This field has no significance for IPDS printers. [IPDS-D-102]

**Retired item 78 (1991):** Byte 14 in the Load Symbol Set command is retired for the IBM 3270 architecture. This byte has been reserved for future function by IBM GOCA. This field has no significance for IPDS printers. [IPDS-D-103]

**Retired item 79 (1991):** Bytes 17&ndash;i in the Load Symbol Set command are retired for the IBM 3270 architecture and are intended to be reserved for future expansion by all architectures. These are an arbitrary number of reserved bytes derived from the value in byte 4. These bytes have no significance for IPDS printers. [IPDS-D-104]

**Retired item 80 (1991):** Format 3 sense byte information as specified in sense byte 5 is retired for channel-attached printers. This format is used for control-unit sensed exceptions when an Outboard Recorder Record (OBR) is required. This format is used in channel-level sense data and is not used with IPDS sense data.
*   **Byte 4:** Reserved [IPDS-D-105]
*   **Byte 5:** Format identifier, X'03' [IPDS-D-106]
*   **Bytes 6&ndash;7:** Channel adapter trace register [IPDS-D-107]
*   **Bytes 8&ndash;9:** Channel Adapter Status register [IPDS-D-108]
*   **Bytes 10&ndash;11:** Channel adapter error log register [IPDS-D-109]
*   **Bytes 12&ndash;13:** Channel configuration register 1 [IPDS-D-110]
*   **Byte 14:** Channel command register [IPDS-D-111]
*   **Byte 15:** Channel (host) status register [IPDS-D-112]
*   **Byte 16:** Channel adapter request register [IPDS-D-113]
*   **Byte 17:** Device status table entry [IPDS-D-114]
*   **Bytes 18&ndash;19:** Reserved [IPDS-D-115]
*   **Bytes 20&ndash;21:** Channel buffer pointer register [IPDS-D-116]
*   **Bytes 22&ndash;23:** Reserved [IPDS-D-117]

**Retired item 81 (1991):** Format 4 sense byte information as specified in sense byte 5 is retired for channel-attached printers. This format is used for control-unit sensed exceptions when no Outboard Recorder Record (OBR) is required. This format is used in channel-level sense data and is not used with IPDS sense data.
*   **Byte 4:** Zero [IPDS-D-118]
*   **Byte 5:** Format identifier, X'04' [IPDS-D-119]
*   **Bytes 6&ndash;23:** Zero [IPDS-D-120]

**Retired item 82 (1993):** Format 5 sense byte information as specified in sense byte 5 is retired for serial-channel-attached printers. This format is used in channel-level sense data and is not used with IPDS sense data. Format 5 provides detailed information for channel-level exceptions on a serial-channel-attached printer. This format applies to X'8006..00', X'2001..01', X'2001..02', X'2002..01', X'2002..02', X'10E2..01', X'10E2..02', X'0401..01', X'0401..02', X'01A0..00', X'01A1..00', X'01A2..00', and X'01A3..00'.
*   **Byte 4:** Reserved [IPDS-D-121]
*   **Byte 5:** Format identifier, X'05' [IPDS-D-122]
*   **Byte 6:** Physical-interface identifier [IPDS-D-123]
*   **Bytes 7&ndash;8:** Link adapter A basic status register [IPDS-D-124]
*   **Byte 9:** Link adapter A error log register byte 1 [IPDS-D-125]
*   **Bytes 10&ndash;12:** Link adapter A link error log [IPDS-D-126]
*   **Bytes 13&ndash;14:** Link adapter B basic status register [IPDS-D-127]
*   **Byte 15:** Link adapter B error log register byte 1 [IPDS-D-128]
*   **Bytes 16&ndash;18:** Link adapter B link error log [IPDS-D-129]
*   **Byte 19:** Link adapter indicator [IPDS-D-130]
*   **Byte 20:** Reserved [IPDS-D-131]
*   **Byte 21:** VCU ID (0&ndash;15 Link A, 16&ndash;31 Link B) [IPDS-D-132]
*   **Bytes 22&ndash;23:** Virtual Error Log for VCU ID [IPDS-D-133]

**Retired item 83 (1990):** Action code X'00' (no error outstanding) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-134]

**Retired item 84 (1990):** Action code X'02' (operator intervention with OBR record) as specified in sense byte 2 is retired for channel-attached and TCP/IP-attached printers. This action code is not used with IPDS sense data. [IPDS-D-135]

**Retired item 85 (1990):** Action code X'03' (operator intervention without OBR record) as specified in sense byte 2 is retired for channel-attached and TCP/IP-attached printers. This action code is not used with IPDS sense data. [IPDS-D-136]

**Retired item 86 (1990):** Action code X'04' (channel error) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-137]

**Retired item 87 (1991):** Action code X'18' (transparent error) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-138]

**Retired item 88 (1990):** Action code X'1C' (Sense Extended CCW required) as specified in sense byte 2 is retired for channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-139]

**Retired item 89 (1993):** Action code X'24' (printer not assigned) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-140]

**Retired item 90 (1993):** Action code X'25' (printer assigned elsewhere) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-141]

**Retired item 91 (1993):** Action code X'4D' (resetting event) as specified in sense byte 2 is retired for serial-channel-attached printers. This action code is not used with IPDS sense data. [IPDS-D-142]

**Retired item 92 (1991):** Exception class X'20' as specified in sense byte 0 is retired for Bus-Out Parity Check, reserved for compatibility with channel-attached printers. [IPDS-D-143]

**Retired item 93 (1991):** IPDS command code X'D600' is retired for the IBM 3800 printer Test I/O command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. [IPDS-D-144]

**Retired item 94 (1991):** IPDS command code X'D604' is retired for the IBM 3800 printer Sense I/O command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. [IPDS-D-145]

**Retired item 95 (1991):** IPDS command code X'D60D' is retired for the IBM 3800 printer Write Factored Text Control command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. [IPDS-D-146]

**Retired item 96 (1991):** IPDS command code X'D614' is retired for the IBM 3800 printer Sense Intermediate Buffer command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. [IPDS-D-147]

**Retired item 97 (1991):** IPDS command code X'D624' is retired for the IBM 3800 printer Sense Error Log command. Refer to *Reference Manual for the IBM 3800 Printing Subsystem Model 3* for a description of this command. [IPDS-D-148]

**Retired item 98 (1990):** This retired item was unretired in 1991.
Bit 0 of byte 37 in the Load Font Control command had previously been retired for use by the IBM 3835 printer with a particular MICR device. The flag was later unretired and defined as the "intended for MICR printing" flag. [IPDS-D-149]

**Retired item 99 (1991):** Action code X'07' (retry error log full) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-150]

**Retired item 100 (1991):** Action code X'0B' (process power error) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-151]

**Retired item 101 (1991):** Action code X'0E' (not enough storage, page printed using the accumulator feature) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-152]

**Retired item 102 (1991):** Action code X'0F' (accumulator read check) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-153]

**Retired item 103 (1991):** Action code X'10' (reload electronic overlay or base page) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-154]

**Retired item 104 (1991):** Action code X'11' (count continuous-forms stacker fold wrong errors) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-155]

**Retired item 105 (1991):** Action code X'12' (count burster input checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-156]

**Retired item 106 (1991):** Action code X'13' (count no burst checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-157]

**Retired item 107 (1991):** Action code X'14' (count burster-trimmer-stacker stacker/trimmer checks) as specified in sense byte 2 is retired for IBM 3800-3,6,8 printers. This action code is not used with IPDS sense data. [IPDS-D-158]

**Retired item 108 (1991):** X'8000' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOA-NOP; see retired item 18. [IPDS-D-159]

**Retired item 109 (1991):** X'8001' property pair in the Device-Control vector of the Sense Type & Model reply is retired for environment printing; see retired item 19. [IPDS-D-160]

**Retired item 110 (1991):** X'8002' property pair in the Device-Control vector of the Sense Type & Model reply is retired for ringing a bell and for IBM 3800 printer Read Font List; see retired items 20 and 21. [IPDS-D-161]

**Retired item 111 (1991):** X'8006' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Mark Form Carrier Strip; see retired item 22. [IPDS-D-162]

**Retired item 112 (1991):** X'80F1' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Display Operator Panel Message; see retired item 25. [IPDS-D-163]

**Retired item 113 (1991):** X'80F3' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOA-Request Printer Information; see retired item 26. [IPDS-D-164]

**Retired item 114 (1991):** X'9000' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOH-NOP; see retired item 31. [IPDS-D-165]

**Retired item 115 (1991):** X'900B' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOH-Set X Adjustment Range; see retired item 32. [IPDS-D-166]

**Retired item 116 (1991):** X'90F4' property pair in the Device-Control vector of the Sense Type & Model reply is retired for IBM 3800 printer XOH-Inhibit Automatic WCGM Load; see retired item 33. [IPDS-D-167]

**Retired item 117 (1991):** LPD byte 15, bit 1 (ordered blocks flag) was not used by any implementation and is therefore retired.
*   **Bit 1:** Ordered blocks flag. A value of B'1' indicates that all text, image, graphics, or bar code blocks are in sequential order (the presentation of sequentially received blocks as opposed to data within blocks). No backward movement is required. A value of B'0' indicates that backward movement might be required. [IPDS-D-168]

**Retired item 118 (1991):** LPD byte 15, bit 2 (ordered text flag) was not used by any implementation and is therefore retired.
*   **Bit 2:** Ordered text flag. A value of B'1' indicates that all text data within every page, either in text blocks or as text data, is in sequential order. No backward movement is required. A value of B'0' indicates that some text data on a page might require backward movement. [IPDS-D-169]

**Retired item 119 (1991):** Bit 4 of XOH-OPC Printable-Area self-defining field byte 22 is retired. This flag is defined as follows:
*   **B'1':** The media size in bytes 10&ndash;13 and printable-area size in bytes 18&ndash;21 might be invalid. [IPDS-D-170]
*   **B'0':** The media size in bytes 10&ndash;13 and printable-area size in bytes 18&ndash;21 are valid. [IPDS-D-171]

**Retired item 120 (1993):** Byte 36 of the Load Font Control command is retired as a non-stageable indicator. A value of X'00' indicates that the font is stageable. A value of X'01' indicates that the font is non-stageable. A stageable font is a font that is to be stored on a non-volatile media, such as a floppy disk, assuming such a storage means is available. [IPDS-D-172]

**Retired item 121 (1991):** Intermediate Device Type code X'0003' in XOH-OPC Product Identifier self-defining field parameter ID X'0002' is retired to indicate that the intermediate device is an IBM 4019 IPDS - PPDS/HPCL Protocol Converter. [IPDS-D-173]

**Retired item 122 (1993):** Byte 5 of the LFCSC command is retired for version of pattern technology. [IPDS-D-174]

**Retired item 123 (1994):** This retired item was unretired in 2011.
This item covers the Write Text Control (WTC) command, which had been in the IPDS architecture, but was retired until a product implementation became available. [IPDS-D-175]

**Retired item 124 (1994):** This retired item was unretired in 2011.
X'2001' property pair in the text command-set vector of the Sense Type and Model reply had previously been retired for text block support using the Write Text Control command. The property pair now indicates support for text objects and the Write Text Control (WTC) command. [IPDS-D-176]

**Retired item 125 (1992):** Sense byte 3 is retired for use with IBM S/370 sense data. This byte is not used in the IPDS architecture. Sense byte 3 normally defines the printer environment at the time of the exception and not the printer environment at the time the exception is reported to the host. There are five exceptions to this rule. Sense byte 3 for action codes X'01', X'0C', X'0D', X'15', and X'18' defines the printer environment at the time the exception is reported to the host. The bits are defined as follows:
*   **Bit 0:** If B'1', indicates that the printer is in the ready state. If B'0', indicates that the printer is in the not-ready state. [IPDS-D-177]
*   **Bits 1&ndash;6:** Reserved [IPDS-D-178]
*   **Bit 7:** Set to B'1' to indicate that the printer operates in page mode only. [IPDS-D-179]

**Retired item 126 (1993):** X'90D0' property pair in the Device-Control vector of the Sense Type & Model reply is retired for XOH-Select Output Stacker; see retired item 127. [IPDS-D-180]

**Retired item 127 (1993):** X'D000' order code of the Execute Order Home State command is retired for Select Output Stacker (SOS); see retired item 126. [IPDS-D-181]

**Retired item 128 (1994):** The following resource types from byte 2 of the XOA-RRL reply are retired:
*   **X'81':** Resident single-byte LF1-type or LF2-type coded-font components [IPDS-D-182]
*   **X'82':** Resident double-byte LF1-type coded-font components [IPDS-D-183]
*   **X'83':** Resident double-byte LF1-type coded-font-section components [IPDS-D-184]
*   **X'84':** Resident page segment [IPDS-D-185]
*   **X'85':** Resident overlay [IPDS-D-186]
*   **X'86':** Resident code page [IPDS-D-187]
*   **X'87':** Resident font character set [IPDS-D-188]
*   **X'88':** Resident single-byte coded-font index [IPDS-D-189]
*   **X'89':** Resident double-byte coded-font section index [IPDS-D-190]

**Retired item 129 (1994):** StampType X'01' in the Local Date and Time Stamp (X'62') triplet is retired for use by the IBM RMARK utility programs. This StampType indicates that a resource object was marked by the IBM RMARK utility. [IPDS-D-191]

**Retired item 130 (1996):** The Standard OCA Color Value Support self-defining field (formerly in the XOH-OPC reply) is retired for use by older IPDS printers. New IPDS printers should not return this self-defining field. The Standard OCA Color Value Support self-defining field specifies the set of color values that are supported for all multiple-color data types supported. The STM reply indicates for each of the data types (text, image, graphics, or bar code) whether or not multiple-color values are supported for that data type. This field must be returned by printers that indicate multiple-color support in the STM reply. The absence of this self-defining field indicates that the printer supports the single color black along with the color values X'0008' (black), and X'FF07' (printer default). [IPDS-D-192]

| Offset | Type | Name | Range | Meaning |
| :--- | :--- | :--- | :--- | :--- |
| 0&ndash;1 | UBIN | SDF length | X'0006' &ndash; X'7FFE' | Length of this self-defining field, including itself [IPDS-D-193]|
| 2&ndash;3 | CODE | SDF ID | X'0005' | Standard OCA Color Value Support self-defining field ID [IPDS-D-194]|
| **One or more color-table values in the following format:** [IPDS-D-195]| | | | |
| +0&ndash;1 | CODE | Color value | X'0001'&ndash;X'0010' | True colors supported by the printer:<br>X'0001' Blue<br>X'0002' Red<br>X'0003' Pink/magenta<br>X'0004' Green<br>X'0005' Turquoise/cyan<br>X'0006' Yellow<br>X'0007' White or black (black for printers)<br>X'0008' Black<br>X'0009' Dark blue<br>X'000A' Orange<br>X'000B' Purple<br>X'000C' Dark green<br>X'000D' Dark turquoise<br>X'000E' Mustard<br>X'000F' Gray<br>X'0010' Brown [IPDS-D-196]|

**Implementation Notes:**
1. These colors are listed for compatibility with the IBM GOCA architecture and are unsupported. [IPDS-D-197]
2. The current architecture does not define color-support self-defining fields in the XOH-OPC response for specific data types even though the STM color-support property-pairs are generated for specific data types. [IPDS-D-198]

**Retired item 131 (1998):** XOA-RRL query type X'FE' is retired for a single-entry general query with resource ID triplets.Identifies this as a general query identical to query type X'FF' except that resource ID triplets are also returned as part of the resource ID in the reply. These resource ID triplets are not returned with a X'FF'-type query. This is an optional query type. Support for this query type is indicated by property pair X'F402' in the Device-Control command-set vector of the STM reply. Query type X'FE' may only be used with single-entry queries. Both list and individual queries are supported with query type X'FE' and both kinds of query can return multiple reply entries; each resource that matches the query is returned in a separate resource-list reply entry that includes all triplets specific to that version of the resource. [IPDS-D-199]

**Retired item 132 (1999):** Media ID type X'01' in the XOH-OPC reply Printable-Area self-defining field is retired for ISO/DPA registered media values. This is an integer corresponding to the leaf element of the DPA Standard Object ID (OID) for the physical medium identified under the medium object class. The input media ID (in bytes 27&ndash;end) contains only the characters 0&ndash;9 using the code points assigned in IBM code page 500. These values are defined in ISO Draft International Standard 10175-1 "Information Technology - Text and Office Systems - Document Printing Application (DPA)". [IPDS-D-200]

**Retired item 133 (2000):** Bit 4 of LCPC byte n+13 (processing flags for the default GCGID) is retired for a flag to distinguish some code page types. This flag was set to B'1' in outline code pages for Japanese, Korean, Simplified Chinese, and Traditional Chinese; it is set to B'0' for raster code pages and Unicode code pages. The reason for the flag being set to B'1' is unknown. The FOCA architecture does not assign a meaning to this flag. [IPDS-D-201]

**Retired item 134 (2000):** IPDS command code X'D61C' is retired for the Load HAID List (LHL) command. This command permits resources that are nested in a nesting resource via Include commands or local IDs to be referenced externally using a new Host-Assigned ID (HAID). The LHL command allows the replacement of an old HAID with a new HAID in the Include or LFE command for each nested resource. [IPDS-D-202]

**Retired item 135 (2001):** Replicate-and-trim mapping option for graphics (property pair X'F300') is retired. This option was originally defined for both IO Image and for graphics, but was later restricted to just IO Image because no IPDS printer implemented this mapping option for graphics. [IPDS-D-203]

**Retired item 136 (2006):** E-mail Setup File object OID (used by IBM Infoprint Manager) [IPDS-D-204]

### Table 74. Object Containers Used in the IPDS Environment (retired item 136)

| Registered Object-Type OID | Object Description | Internal Resource ID | Object Download State | Object Usage |
| :--- | :--- | :--- | :--- | :--- |
| X'0607 2B12 0004 0101 2500 0000 0000 0000' | E-mail Setup File | Non-presentation | Not applicable | Home state Setup file [IPDS-D-205]|

**Retired item 137 (2006):** Fax Setup File object OID (used by IBM Infoprint Manager) [IPDS-D-206]

### Table 75. Object Containers Used in the IPDS Environment (retired item 137)

| Registered Object-Type OID | Object Description | Internal Resource ID | Object Download State | Object Usage |
| :--- | :--- | :--- | :--- | :--- |
| X'0607 2B12 0004 0101 2400 0000 0000 0000' | Fax Setup File | Non-presentation | Not applicable | Home state Setup file [IPDS-D-207]|

**Retired item 138 (2006):** Infoprint 2000 Setup File object OID (used by IBM Infoprint Manager) [IPDS-D-208]

### Table 76. Object Containers Used in the IPDS Environment (retired item 138)

| Registered Object-Type OID | Object Description | Internal Resource ID | Object Download State | Object Usage |
| :--- | :--- | :--- | :--- | :--- |
| X'0607 2B12 0004 0101 2600 0000 0000 0000' | Infoprint 2000 printer Setup File | Non-presentation | Not applicable | Home state Setup file [IPDS-D-209]|

**Retired item 139 (2006):** STM Bar Code property pair X'1301' is retired for "ASCII-based code pages supported". [IPDS-D-210]

**Retired item 140 (2008):** Bytes 8&ndash;11 in the Object Offset (X'5A') triplet are retired for a high-order extension parameter. This parameter is not used within IPDS data streams, but is defined in the MO:DCA architecture to provide a means to allow a larger object offset value to be specified. IPDS printers ignore the value in this field. [IPDS-D-211]

**Retired item 141 (2011):** XOA order X'0700' is retired for use in Océ printers and server software; used for extended features control. [IPDS-D-212]

**Retired item 142 (2011):** XOA order X'0900' is retired for use in Océ printers and server software; used to enable two-up. [IPDS-D-213]

**Retired item 143 (2011):** XOA order X'CE00' is retired for use in Océ printers and server software; used for cancel synchronization. [IPDS-D-214]

**Retired item 144 (2011):** XOH order X'1C00' is retired for use in Océ printers and server software; used for two-up control. [IPDS-D-215]

**Retired item 145 (2011):** XOH order X'1D00' is retired for use in Océ printers and server software; used to select media destination. [IPDS-D-216]

**Retired item 146 (2011):** XOH order X'4C00' is retired for use in Océ printers and server software; used for cut-sheet emulation (CSE) controls. [IPDS-D-217]

**Retired item 147 (2011):** XOH order X'4D00' is retired for use in Océ printers and server software; used for the PoD HD option. [IPDS-D-218]

**Retired item 148 (2013):** XOH order X'4E00' is retired for use in Océ printers and server software; used for configurations. [IPDS-D-219]

**Retired item 149 (2021):** Property pair X'120B' is retired because it reports support of a functionality that cannot actually work. Property pair X'120B' indicates support for mapping TrueType/OpenType Fonts in the DORE command, for all presentation objects supported by the printer and for which TrueType/OpenType Fonts are valid secondary resources. The DORE command cannot be used for this purpose, since in IPDS, a TrueType/OpenType font used as a secondary resource uses a HAID in the data-object-font-component HAID pool, but the HAIDs in the DORE are only searched for in the data-object-resource HAID pool. Instead, the DORE2 command must be used for this purpose, and that functionality is reported with the X'120D' property pair. [IPDS-D-220]
