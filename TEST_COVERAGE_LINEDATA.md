# Granular Test Coverage - LINEDATA

| Requirement ID | Summary | Coverage |
| :--- | :--- | :---: |
| LINEDATA-1-001 | Advanced Interactive Executive (AIX ®) | ❓ |
| LINEDATA-1-002 | Application System/400 (AS/400), iSeries, and System i5 ® | ❓ |
| LINEDATA-1-003 | Operating System/2 ® (OS/2) | ❓ |
| LINEDATA-1-004 | IBM mainframe environments, including: | ❓ |
| LINEDATA-1-005 | Linux™ | ❓ |
| LINEDATA-1-006 | Microsoft ® Windows® | ❓ |
| LINEDATA-1-007 | Traditional line printer format, also called 1403 format | ❓ |
| LINEDATA-1-008 | Unformatted ASCII files without escape sequences | ❓ |
| LINEDATA-1-009 | DBCS (double-byte character set) ASCII files generated for an IBM 5577 or 5587 | ❓ |
| LINEDATA-1-010 | Definition (Formdef) to be used for formatting and printing the data. | ❓ |
| LINEDATA-1-011 | Bar Code Object Content Architecture™ (BCOCA) | ❓ |
| LINEDATA-1-012 | Color Management Object Content Architecture (CMOCA) | ❓ |
| LINEDATA-1-013 | Font Object Content Architecture (FOCA) | ❓ |
| LINEDATA-1-014 | Graphics Object Content Architecture (GOCA) | ❓ |
| LINEDATA-1-015 | Image Object Content Architecture (IOCA) | ❓ |
| LINEDATA-1-016 | Presentation Text Object Content Architecture (PTOCA) | ❓ |
| LINEDATA-1-017 | Non-OCA paginated presentation objects such as Encapsulated PostScript ® (EPS) | ❓ |
| LINEDATA-1-018 | architecture. This is the data stream architecture used by print server products to manage IPDS printers. | ❓ |
| LINEDATA-1-019 | See these books for information on setting up jobs for printing. | ❓ |
| LINEDATA-1-020 | AS/400 and System i5 | ❓ |
| LINEDATA-1-021 | OS/390 and z/OS | ❓ |
| LINEDATA-1-022 | VM and z/VM | ❓ |
| LINEDATA-1-023 | VSE and z/VSE | ❓ |
| LINEDATA-1-024 | Windows | ❓ |
| LINEDATA-2-001 | Font objects, which consist of font character sets containing the patterns for letters, numbers, and special | ❓ |
| LINEDATA-2-002 | Resource objects, including overlays and page segments, which in turn can include text, graphics, image, | ❓ |
| LINEDATA-2-003 | Print control objects, which include Page Definitions used to format line data and Form Definitions used to | ❓ |
| LINEDATA-2-004 | used of these is called line data. | ❓ |
| LINEDATA-2-005 | except IBM OS/2. The nature of line data is slightly different in the system environments where it appears. | ❓ |
| LINEDATA-2-006 | other structures that determine how the records will appear on paper. AFP print server products support | ❓ |
| LINEDATA-2-007 | 1 2 3 I S L A N D P L A C E | ❓ |
| LINEDATA-2-008 | 123 Island Place | ❓ |
| LINEDATA-2-009 | 123 Island Place | ❓ |
| LINEDATA-2-010 | 3800 Model 1 added a spacing value of 12 lines per inch to the FCB; and the | ❓ |
| LINEDATA-2-011 | American National Standards Institute (ANSI) carriage control is a standard representation used with printers | ❓ |
| LINEDATA-2-012 | Machine code control characters were defined by IBM. They correspond to the channel command words | ❓ |
| LINEDATA-2-013 | X'B1' Print the data, then skip to the line position defined as Channel 6 | ❓ |
| LINEDATA-2-014 | Note: The line separator is described in Line Data Summary. | ❓ |
| LINEDATA-2-015 | formatting and printing the data. | ❓ |
| LINEDATA-2-016 | 1. Only supported when input data is XML. | ❓ |
| LINEDATA-2-017 | 2. Only supported when the length of each record is contained in a two byte prefix to the record or when each record is | ❓ |
| LINEDATA-2-018 | EBCDIC data: Line Feed (X'25'). | ❓ |
| LINEDATA-2-019 | ASCII and UTF-8 data: Line Feed (X'0A') or Carriage Return (X'0D') and Line Feed (X'0A') pair. | ❓ |
| LINEDATA-2-020 | UTF-16BE: Line Feed (X'000A') or Carriage Return (X'000D') and Line Feed (X'000A') pair. | ❓ |
| LINEDATA-2-021 | UTF-16LE: Line Feed (X'0A00') or Carriage Return (X'0D00') and Line Feed (X'0A00') pair. Note that | ❓ |
| LINEDATA-2-022 | encoding UTF-16 or UTF-8. | ❓ |
| LINEDATA-2-023 | allowed only on the first record and applies to all records in the print file. | ❓ |
| LINEDATA-2-024 | allowed only on the first record and applies to all records in the print file. | ❓ |
| LINEDATA-2-025 | UTF-8: X'EFBBBF' | ❓ |
| LINEDATA-2-026 | UTF-16BE (big-endian byte order): X'FEFF' | ❓ |
| LINEDATA-2-027 | UTF-16LE (little-endian byte order): X'FFFE' | ❓ |
| LINEDATA-2-028 | Shift-out/Shift-in (SOSI) controls are not used in Unicode to signify a shift into and out-of DBCS processing. | ❓ |
| LINEDATA-2-029 | If the Byte Order Mark used in UTF-16 data indicates the data is in little-endian byte order, programs that | ❓ |
| LINEDATA-2-030 | If carriage control bytes (CC) or table reference character bytes (TRC) are used with UTF-16 encoded data, | ❓ |
| LINEDATA-2-031 | the CC and TRC bytes remain 1 byte fields and are not encoded in UTF-16. | ❓ |
| LINEDATA-2-032 | Carriage Control (CC) and Table Reference Characters (TRC) are not supported. | ❓ |
| LINEDATA-2-033 | The data is encoded using one of the following: | ❓ |
| LINEDATA-2-034 | MO:DCA data cannot be mixed with XML data. | ❓ |
| LINEDATA-2-035 | can be found at the World Wide Web Consortium web site, http://www.w3.org/. | ❓ |
| LINEDATA-2-036 | MO:DCA data is fully described in the Mixed Object Document Content Architecture (MO:DCA) Reference. | ❓ |
| LINEDATA-2-037 | Structured Fields to Line Data” provides guidelines on the valid combinations. | ❓ |
| LINEDATA-2-038 | and some AFP products allow users to create their own Page Definitions. | ❓ |
| LINEDATA-3-001 | system. See the reference publications for your print server and operating system for complete information. | ❓ |
| LINEDATA-3-002 | 8.2 lines per inch, so a 15-pitch or smaller font must be used to prevent the lines from overlapping. | ❓ |
| LINEDATA-3-003 | the same result with letter-size paper on a cut-sheet printer as with larger forms on an impact printer. | ❓ |
| LINEDATA-3-004 | copy groups) in the Form Definition. The Invoke Data Map and Invoke Medium Map structured fields can be | ❓ |
| LINEDATA-3-005 | //SYSUT2 DD SYSOUT=A,OUTPUT=(*.OUT1,*.OUT2,*.OUT3) | ❓ |
| LINEDATA-3-006 | Definition conform to the MO:DCA architecture rules for structured fields. These rules are summarized in | ❓ |
| LINEDATA-3-007 | =  optional | ❓ |
| LINEDATA-3-008 | specified in the BPM. | ❓ |
| LINEDATA-3-009 | =  optional | ❓ |
| LINEDATA-3-010 | Ends a Resource Environment Group. | ❓ |
| LINEDATA-3-011 | Traditional line data containing optional CCs and TRCs are processed using LNDs in the Data Map | ❓ |
| LINEDATA-3-012 | Transmission Subcase. | ❓ |
| LINEDATA-3-013 | Record-format line data containing record IDs and optional CCs are processed using RCDs in the Data Map | ❓ |
| LINEDATA-3-014 | specify objects to be included on the page. | ❓ |
| LINEDATA-3-015 | =  optional | ❓ |
| LINEDATA-3-016 | Record-format processing | ❓ |
| LINEDATA-3-017 | XML processing | ❓ |
| LINEDATA-3-018 | – X'0030'–X'0039' for Unicode Presentation or UTF-16 | ❓ |
| LINEDATA-3-019 | in compatibility mode. Table 8 summarizes the differences. | ❓ |
| LINEDATA-3-020 | Table reference character 0 (X'00' or X'F0') selects the first font mapped in the Active Environment | ❓ |
| LINEDATA-3-021 | A table reference character higher than 127 selects the first font mapped in the AEG of the Data Map. | ❓ |
| LINEDATA-3-022 | A table reference character higher than the number of fonts mapped defaults to the first font mapped | ❓ |
| LINEDATA-3-023 | mapped using the MDR structured field. An MDR for such objects is not required in the AEG of the Data Map, | ❓ |
| LINEDATA-3-024 | the PGD, and the output area size must be the same size as the page. | ❓ |
| LINEDATA-3-025 | PTD-1, formerly called CTD, specifies only the text presentation space units of measure and | ❓ |
| LINEDATA-3-026 | PTD-2 specifies the text presentation space units of measure, expands the fields containing | ❓ |
| LINEDATA-3-027 | Text Orientation Set Text Orientation (STO) | ❓ |
| LINEDATA-3-028 | =  optional | ❓ |
| LINEDATA-3-029 | s s | ❓ |
| LINEDATA-3-030 | associated with the print file, just as in a non-AFP environment. | ❓ |
| LINEDATA-3-031 | Field formatting, which is the ability to separate specific fields in a line-data record and place them anywhere | ❓ |
| LINEDATA-3-032 | Multiple-up printing, which is the ability to divide the page into sections, each with the appearance of a | ❓ |
| LINEDATA-3-033 | Conditional processing, which is the ability to define tests on certain characters of the line data and perform | ❓ |
| LINEDATA-3-034 | Resource object include, which is the ability to include an overlay or page segment and position it relative to | ❓ |
| LINEDATA-3-035 | Bar code generation, which is the ability to select a field in a record and present it as a bar code. | ❓ |
| LINEDATA-3-036 | Specification of spot (highlight) colors or process colors for a record or field. | ❓ |
| LINEDATA-3-037 | Object include, which is the ability to include a data object relative to the current line and change its position, | ❓ |
| LINEDATA-3-038 | Qualified Tag is found, the content of the XML element is formatted with that XMD. If an XMD with a matching | ❓ |
| LINEDATA-3-039 | Transmission Subcase can contain more than one RCD. | ❓ |
| LINEDATA-3-040 | name specified in the BDX. | ❓ |
| LINEDATA-3-041 | specifies flag byte bit 6=B'0' and bytes 16–17=X'0000'. This LND terminates the reuse chain. | ❓ |
| LINEDATA-3-042 | be used to process a field in this record. A field RCD is identified by RCDFlgs bit 6=B'1' and RCDFlgs bit 11= | ❓ |
| LINEDATA-3-043 | specify FLDrcd= X'0000'. | ❓ |
| LINEDATA-3-044 | XMD in this chain must specify FLDxmd=X'0000'. | ❓ |
| LINEDATA-3-045 | in the DOWN direction. | ❓ |
| LINEDATA-3-046 | actually be a new N-up partition on the sheet. | ❓ |
| LINEDATA-3-047 | Record-based or field-based font selection. In this mode, the font to be used following an SO can be uniquely | ❓ |
| LINEDATA-3-048 | 34 of the RCD that is used to process the line data. The font used following an explicit SI is then always the | ❓ |
| LINEDATA-3-049 | flag bit 4 = B'1'. An error condition exists if flag bit 4 = B'0'. Note that an implicit SI is assumed at the start of | ❓ |
| LINEDATA-3-050 | Page-based font selection. In this mode, the font to be used following an SO is the same for all records and | ❓ |
| LINEDATA-3-051 | Each SO (X'0E') is replaced with a blank (X'40'), followed by a PTOCA structure that | ❓ |
| LINEDATA-3-052 | Each SI (X'0F') is replaced with a PTOCA structure that contains a Set Coded Font Local | ❓ |
| LINEDATA-3-053 | Each SO (X'0E') is replaced with a PTOCA structure that contains a Set Coded Font Local | ❓ |
| LINEDATA-3-054 | Each SI (X'0F') is replaced with a PTOCA structure that contains a Set Coded Font Local | ❓ |
| LINEDATA-3-055 | Each SO (X'0E') is replaced with a PTOCA structure that contains a Set Coded Font Local | ❓ |
| LINEDATA-3-056 | Each SI (X'0F') is replaced with a PTOCA structure that contains a Set Coded Font Local | ❓ |
| LINEDATA-3-057 | shift-out control, the data is scanned two bytes at a time. The first byte of each pair is checked to see if it is a | ❓ |
| LINEDATA-3-058 | 1. Since table reference characters (TRCs) also might | ❓ |
| LINEDATA-3-059 | 2. Shift-out/Shift-in controls are not used in Unicode data to signify a shift into and out of DBCS processing. | ❓ |
| LINEDATA-3-060 | 3. When building bar codes from line data, SOSI input data is not appropriate for bar code symbologies other | ❓ |
| LINEDATA-3-061 | 4. When using Shift-out/Shift-in controls with Record Format data using delimited fields, if the field is to print | ❓ |
| LINEDATA-3-062 | 5. Shift-out/Shift-in controls are not supported when processing XML data. | ❓ |
| LINEDATA-3-063 | Line data record | ❓ |
| LINEDATA-3-064 | XML element | ❓ |
| LINEDATA-3-065 | Field in the line data record | ❓ |
| LINEDATA-3-066 | Field in the XML element | ❓ |
| LINEDATA-3-067 | current text coordinate system (I,B) origin. For example, if an LND specifies a (90°,180°) text orientation, the | ❓ |
| LINEDATA-3-068 | relative offset depends on whether the LND that specifies relative positioning is a base LND and on whether a | ❓ |
| LINEDATA-3-069 | For base LNDs, offsets are defined relative to the last base LND processed, either by printing or by spacing. | ❓ |
| LINEDATA-3-070 | For reuse LNDs other than base LNDs, the offset is defined relative to the last LND used to print. | ❓ |
| LINEDATA-3-071 | If the first LND of a Data Map specifies relative positioning, the offset is defined relative to the current text | ❓ |
| LINEDATA-3-072 | If the first LND of a subpage specifies relative positioning, the offset is defined relative to the last print | ❓ |
| LINEDATA-3-073 | The text orientation of an LND that specifies relative baseline positioning must be the same as the text | ❓ |
| LINEDATA-3-074 | For record RCDs, offsets are defined relative to the last record RCD processed. However, if a page boundary | ❓ |
| LINEDATA-3-075 | For field RCDs, the offset is defined relative to the last RCD used to print. | ❓ |
| LINEDATA-3-076 | If the first RCD of a Data Map specifies relative positioning, the offset is defined relative to the top margin. | ❓ |
| LINEDATA-3-077 | The text orientation of an RCD that specifies relative baseline positioning must be the same as the text | ❓ |
| LINEDATA-3-078 | For element XMDs, offsets are defined relative to the last element XMD processed. However, if a page | ❓ |
| LINEDATA-3-079 | For field XMDs, the offset is defined relative to the last XMD used to print. | ❓ |
| LINEDATA-3-080 | If the first XMD of a Data Map specifies relative positioning, the offset is defined relative to the top margin. | ❓ |
| LINEDATA-3-081 | The text orientation of an XMD that specifies relative baseline positioning must be the same as the text | ❓ |
| LINEDATA-3-082 | The text orientation of an XMD that specifies relative inline positioning must be the same as the text | ❓ |
| LINEDATA-3-083 | structured field. If the new print position is outside these boundaries, printing of the page stops. | ❓ |
| LINEDATA-3-084 | with presentation services program software, but users can also create customized Form Definitions. | ❓ |
| LINEDATA-4-001 | . For example, the Map Coded Font (MCF) structured field is part of | ❓ |
| LINEDATA-4-002 | Invoke Data Map | ❓ |
| LINEDATA-4-003 | Invoke Medium Map | ❓ |
| LINEDATA-4-004 | Include Page Segment | ❓ |
| LINEDATA-4-005 | Include Page Overlay | ❓ |
| LINEDATA-4-006 | Include Object | ❓ |
| LINEDATA-4-007 | Presentation Text | ❓ |
| LINEDATA-4-008 | control to determine end-of-record allows variable-length records to be easily created in AIX environments. | ❓ |
| LINEDATA-4-009 | inline resource group. Figure 15 shows the structure of an AFP print file. | ❓ |
| LINEDATA-4-010 | =  optional | ❓ |
| LINEDATA-4-011 | 1. The BPF/EPF structured fields are optional as a pair; if one is specified, the other must be specified as | ❓ |
| LINEDATA-4-012 | 2. The mixed line-page documents and composed documents can occur in any order following the inline | ❓ |
| LINEDATA-4-013 | 3. Each AFP (MO:DCA) document may optionally be preceded by a single document index that is implicitly | ❓ |
| LINEDATA-4-014 | 4. An AFP (MO:DCA) document may contain Link Logical Element (LLE) structured fields following the BDT . | ❓ |
| LINEDATA-4-015 | 5. If a Medium Map is included internal (inline) to the document, it is activated by immediately following it with | ❓ |
| LINEDATA-4-016 | assumed to reference a Medium Map in the current Form Definition. | ❓ |
| LINEDATA-4-017 | “Document and Resource Object Diagrams”. | ❓ |
| LINEDATA-4-018 | If the MFC specifies print-file level finishing, all media in the print file is collected for finishing in a print-file | ❓ |
| LINEDATA-4-019 | If the MFC specifies document-level finishing and selects all documents, the print file is processed as a set of | ❓ |
| LINEDATA-4-020 | If the MFC specifies document-level finishing and selects a single document, the print file is processed as a | ❓ |
| LINEDATA-4-021 | itself be an implied document. The media in the selected document are collected for finishing and the | ❓ |
| LINEDATA-4-022 | MFC, unique finishing operations may be specified for each document in the print file. | ❓ |
| LINEDATA-4-023 | 16 shows the structure of an inline resource group. | ❓ |
| LINEDATA-4-024 | A page segment | ❓ |
| LINEDATA-4-025 | An overlay | ❓ |
| LINEDATA-4-026 | A data object | ❓ |
| LINEDATA-4-027 | An object container | ❓ |
| LINEDATA-4-028 | A document | ❓ |
| LINEDATA-4-029 | A Form Definition | ❓ |
| LINEDATA-4-030 | A Page Definition | ❓ |
| LINEDATA-4-031 | A font object (a code page, a font character set, or a coded font) | ❓ |
| LINEDATA-4-032 | For traditional line data, processing begins with the first Line Descriptor (LND) structured field of the invoked | ❓ |
| LINEDATA-4-033 | For record-format line data, processing begins with the first Record Descriptor (RCD) structured field that | ❓ |
| LINEDATA-4-034 | than eight bytes, trailing blanks must be added. | ❓ |
| LINEDATA-4-035 | X'5A' X'0010' X'D3ABCA' X'00' X'0000' X'E2E4D4D4C1D9E840' | ❓ |
| LINEDATA-4-036 | Using Structured Fields to Skip to a New Page or Sheet | ❓ |
| LINEDATA-4-037 | Page Definition shown in Figure 14. | ❓ |
| LINEDATA-4-038 | structured fields can be included in the output data stream as often as necessary. | ❓ |
| LINEDATA-4-039 | 63 bytes of X'00' followed by | ❓ |
| LINEDATA-4-040 | 64 bytes of any information to | ❓ |
| LINEDATA-4-041 | (X,Y) coordinate system. | ❓ |
| LINEDATA-4-042 | p,Yp) coordinate system. | ❓ |
| LINEDATA-4-043 | RCD that was used to process a data record. | ❓ |
| LINEDATA-4-044 | segment inherits the Active Environment Group definition of the including page. | ❓ |
| LINEDATA-4-045 | If one of the IPS offsets is specified as X'FFFFFF', the page segment origin along that axis is located at the | ❓ |
| LINEDATA-4-046 | If the IPS offset is not X'FFFFFF', the page segment origin is located at the IPS offset measured with respect | ❓ |
| LINEDATA-4-047 | If the page segment is included with a Resource Object Include (X'6C') triplet on the LND or RCD, the page | ❓ |
| LINEDATA-4-048 | current text orientation. | ❓ |
| LINEDATA-4-049 | the application to place subsequent print lines so that they do not overprint the page segment. | ❓ |
| LINEDATA-4-050 | . The current line position is unchanged after the overlay has been placed. | ❓ |
| LINEDATA-4-051 | If the IPO offset along either the page X p-axis or the page Y p-axis is specified as X'FFFFFF', the overlay | ❓ |
| LINEDATA-4-052 | If the IPO offset is not X'FFFFFF', the overlay origin is positioned at the specified (X p,Yp) offset measured | ❓ |
| LINEDATA-4-053 | If the overlay is included with a Resource Object Include (X'6C') triplet on the LND or RCD, the overlay origin | ❓ |
| LINEDATA-4-054 | Note: If the IM image is complex (celled), AFP print servers require the rotation set to 0°,90°. | ❓ |
| LINEDATA-4-055 | X'5A' X'0016' X'D3AFD8' X'00' X'0000' X'D6F1E2C9C7D5C1E3' X'FFFFFF' X'FFFFFF' | ❓ |
| LINEDATA-4-056 | OCA objects (BCOCA, GOCA, IOCA, PTOCA) that specify an Object Environment Group (OEG) or MO:DCA | ❓ |
| LINEDATA-4-057 | Non-OCA paginated presentation objects, such as TIFF images, that are supported by the presentation | ❓ |
| LINEDATA-4-058 | (Xp,Yp) coordinate system X p-axis. | ❓ |
| LINEDATA-4-059 | The reference coordinate system (byte 23 of the data field of the Object Area Position [OBP] structured field) | ❓ |
| LINEDATA-4-060 | If the image or graphic has been built as a page segment, delete the Begin Page Segment and End Page | ❓ |
| LINEDATA-4-061 | at the point where the image or graphic should appear. | ❓ |
| LINEDATA-4-062 | Each PTX structured field should be coded as a self-contained environment. While PTX control sequences | ❓ |
| LINEDATA-4-063 | Because the presentation | ❓ |
| LINEDATA-4-064 | Page Definition information, PTX information, and any additional information contained in objects such as | ❓ |
| LINEDATA-4-065 | bar code and image placed on the page interact, so the programmer must keep careful track of the page | ❓ |
| LINEDATA-4-066 | The X'2BD3' escape sequence, followed by one or more text control sequences | ❓ |
| LINEDATA-4-067 | “Free-standing text”, which is a series of code points representing data to be printed | ❓ |
| LINEDATA-4-068 | X'2B' or X'D3' to be included in a PTX without having these code points interpreted as an escape sequence. | ❓ |
| LINEDATA-4-069 | Specify the beginning print position using Absolute Move Inline (AMI) and Absolute Move Baseline (AMB) | ❓ |
| LINEDATA-4-070 | Select the coded font to be used with the Set Coded Font Local (SCFL) control sequence | ❓ |
| LINEDATA-4-071 | Specify the code points of the text to be printed using a Transparent Data (TRN) control sequence | ❓ |
| LINEDATA-4-072 | with an even function type (X'02F8'). | ❓ |
| LINEDATA-4-073 | Set Extended Text Color xx80 xx81 | ❓ |
| LINEDATA-4-074 | have been extended by 4 pels where necessary to make sure the corners are complete. | ❓ |
| LINEDATA-4-075 | (top side)        (right side) | ❓ |
| LINEDATA-4-076 | Utility bills containing line-by-line details and graphical representations of energy use for each customer | ❓ |
| LINEDATA-4-077 | Insurance policies with clauses, supplements, and detailed client-specific information that vary from one | ❓ |
| LINEDATA-4-078 | Financial statements containing sections that | ❓ |
| LINEDATA-4-079 | highlighted in this chapter in boxes titled “Programming Tip”. | ❓ |
| LINEDATA-4-080 | Begin Document Index | ❓ |
| LINEDATA-4-081 | Index Element | ❓ |
| LINEDATA-4-082 | Tag Logical Element | ❓ |
| LINEDATA-4-083 | End Document Index | ❓ |
| LINEDATA-4-084 | Begin Named Page Group | ❓ |
| LINEDATA-4-085 | End Named Page Group | ❓ |
| LINEDATA-4-086 | fields are not supported in line-data or mixed-data documents. | ❓ |
| LINEDATA-5-001 | For 1 or 2 bytes of padding, the length is specified in the last padding byte. | ❓ |
| LINEDATA-5-002 | For 256 to 32,759 bytes of padding, the length is contained in the last three bytes. The last | ❓ |
| LINEDATA-5-003 | For 3 to 255 bytes of padding, the length can be specified by either method. | ❓ |
| LINEDATA-5-004 | Note: The length count of the padding data includes the length field itself. | ❓ |
| LINEDATA-5-005 | Purpose | ❓ |
| LINEDATA-5-006 | Meaning and allowed values of variable parameters | ❓ |
| LINEDATA-5-007 | Contents of constant parameters | ❓ |
| LINEDATA-5-008 | The three-letter abbreviation | ❓ |
| LINEDATA-5-009 | The three-byte hexadecimal code | ❓ |
| LINEDATA-5-010 | The full name of the structured field | ❓ |
| LINEDATA-5-011 | The byte position of each structured field parameter is given. The first byte of the data field is byte 0. | ❓ |
| LINEDATA-5-012 | If the parameter is a variable, the name and function of the parameter is given. | ❓ |
| LINEDATA-5-013 | Each parameter is specified as either optional (O) or mandatory (M). | ❓ |
| LINEDATA-5-014 | Any valid triplets (see “Structured Field Triplets” for a description) listed in the tables are specified | ❓ |
| LINEDATA-5-015 | Any number not preceded by X (hexadecimal) or B (binary) is a decimal number. | ❓ |
| LINEDATA-5-016 | If the parameter is a constant or reserved parameter, only the parameter contents are given; for example, | ❓ |
| LINEDATA-5-017 | If the same parameter occurs more than once but contains different values, the values in the last parameter | ❓ |
| LINEDATA-5-018 | The tables specify the type of parameter, where appropriate. The parameter type notations are: | ❓ |
| LINEDATA-5-019 | three components: the triplet length, the triplet identifier, and the triplet values. See Table 12. | ❓ |
| LINEDATA-5-020 | 0 | UBIN | Triplet Length | 3–254 | Specifies the length of the triplet, including this byte | M | ❓ |
| LINEDATA-5-021 | 1 | CODE | Triplet Identifier | | Identifies the triplet | M | ❓ |
| LINEDATA-5-022 | 2–n | | Triplet Data | | Contains the data for this triplet | M | ❓ |
| LINEDATA-5-023 | Token name. This name is specified using a fixed-length 8-byte parameter on Begin, Invoke, Map, and | ❓ |
| LINEDATA-5-024 | Fully qualified name. This name can be up to 250 bytes long and is specified using the Fully Qualified Name | ❓ |
| LINEDATA-5-025 | Names consist only of the following characters: A–Z, 0–9, $, #, @. When the object name is specified using | ❓ |
| LINEDATA-5-026 | trailing null code point (X'00') is assumed to terminate the name. | ❓ |
| LINEDATA-5-027 | To ensure portability across older versions of print servers that do not support encoding definitions in the | ❓ |
| LINEDATA-5-028 | have meaning in other applications. | ❓ |
| LINEDATA-5-029 | The Begin Data Map structured field begins a Data Map resource object. | ❓ |
| LINEDATA-5-030 | Structured Field Introducer:** SF Length (2B), ID = X'D3A8CA', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-031 | 0–7 | CHAR | DMName | | Name of the Data Map | M | ❓ |
| LINEDATA-5-032 | 8 | CODE | DatFmt | X'00', X'01', X'02' | Data formatting specified by this Data Map: X'00': LNDs, X'01': RCDs, X'02': XMDs | O | ❓ |
| LINEDATA-5-033 | 9–n | | Triplets | | See BDM Semantics for triplet applicability | M | ❓ |
| LINEDATA-5-034 | 1. If a triplet is included on this structured field, the optional positional parameters become mandatory. | ❓ |
| LINEDATA-5-035 | 2. If one of the Data Maps in a PageDef contains LNDs, then all of the Data Maps in a PageDef must be LND | ❓ |
| LINEDATA-5-036 | 3. If one of the Data Maps in a PageDef contains RCDs, then all of the Data Maps in a PageDef must be RCD | ❓ |
| LINEDATA-5-037 | 4. If one of the Data Maps in a PageDef contains XMDs, then all of the Data Maps in a PageDef must be XMD | ❓ |
| LINEDATA-5-038 | based and subpages are not used (the Data Map contains only one subpage). | ❓ |
| LINEDATA-5-039 | X'6100': EBCDIC Presentation SBCS | ❓ |
| LINEDATA-5-040 | X'2100': PC Data SBCS (ASCII) | ❓ |
| LINEDATA-5-041 | X'7807': UTF-8 | ❓ |
| LINEDATA-5-042 | X'7200': UTF-16 | ❓ |
| LINEDATA-5-043 | 1. ESidUD is required for Data Maps that are to print XML data. | ❓ |
| LINEDATA-5-044 | 2. This triplet may occur once. If this triplet is specified more than once, only the first is used. | ❓ |
| LINEDATA-5-045 | 3. Each Encoding Scheme ID triplet specified on each BDM structured field of a single Page Definition must | ❓ |
| LINEDATA-5-046 | 4. This Encoding Scheme ID triplet overrides the encoding specified in the XML data. | ❓ |
| LINEDATA-5-047 | 5. Except for certain situations in processing XML data with FOCA fonts (see “XML Data”), the | ❓ |
| LINEDATA-5-048 | font selected to print the data must match the encoding of the user data specified in this triplet. | ❓ |
| LINEDATA-5-049 | that contains LNDs, it is ignored. | ❓ |
| LINEDATA-5-050 | 0 | UBIN | Tlength | 7 | Length of the triplet, including Tlength | M | ❓ |
| LINEDATA-5-051 | 1 | CODE | Tid | X'7C' | Identifies the Page Count Control triplet | M | ❓ |
| LINEDATA-5-052 | 2–3 | CODE | PageNum | X'0001'–X'FFFF' | Initial page number | M | ❓ |
| LINEDATA-5-053 | 4 | | | 0 | Reserved; should be zero | M | ❓ |
| LINEDATA-5-054 | 5 | CODE | CountCtr | X'00', X'01', X'02', X'03' | Page count control for Data Map:<br>X'00': Stop<br>X'01': Resume<br>X'02': Continue<br>X'03': Reset | M | ❓ |
| LINEDATA-5-055 | 6 | BITS | CountFlgs | | Bits that specify additional page count controls. See Triplet X'7C' Semantics for bit definitions. | M | ❓ |
| LINEDATA-5-056 | used with the previous Data Map, which is the current page number. If there is no | ❓ |
| LINEDATA-5-057 | The Formdef specifies “constant form”. | ❓ |
| LINEDATA-5-058 | The Formdef specifies N-up and no variable data is allowed on the page. | ❓ |
| LINEDATA-5-059 | MO:DCA pages and constant pages that occur in mixed-mode data). | ❓ |
| LINEDATA-5-060 | graphics processing. If this triplet is specified on a Data Map that contains LNDs, it is ignored. | ❓ |
| LINEDATA-5-061 | 0 | UBIN | Tlength | 14 | Length of the triplet, including Tlength | M | ❓ |
| LINEDATA-5-062 | 1 | CODE | Tid | X'7F' | Identifies the Margin Definition triplet | M | ❓ |
| LINEDATA-5-063 | 2–5 | CODE | TxtOrent | X'0000 2D00', X'2D00 5A00', X'5A00 8700', X'8700 0000' | Text (I,B) Orientation:<br>X'0000 2D00': 0, 90 degrees<br>X'2D00 5A00': 90, 180 degrees<br>X'5A00 8700': 180, 270 degrees<br>X'8700 0000': 270, 360 degrees | M | ❓ |
| LINEDATA-5-064 | 6–7 | UBIN | LeftMar | 0 to page extent minus 1 | Left Margin Offset from page edge | M | ❓ |
| LINEDATA-5-065 | 8–9 | UBIN | TopMar | 0 to page extent minus 1 | Top Margin Offset from page edge | M | ❓ |
| LINEDATA-5-066 | 10–11 | UBIN | RightMar | 0 to page extent minus 1 | Right Margin Offset from page edge | M | ❓ |
| LINEDATA-5-067 | 12–13 | UBIN | BotMar | 0 to page extent minus 1 | Bottom Margin Offset from page edge | M | ❓ |
| LINEDATA-5-068 | which contains the structured fields used to map lines of data to the page. | ❓ |
| LINEDATA-5-069 | Structured Field Introducer:** SF Length (2B), ID = X'D3A8E3', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-070 | 0–7 | CHAR | DMXName |  | Name of the Data Map Transmission Subcase | O | ❓ |
| LINEDATA-5-071 | This is an optional parameter. | ❓ |
| LINEDATA-5-072 | Structured Field Introducer:** SF Length (2B), ID = X'D3A8CB', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-073 | 0–7 | CHAR | PMName |  | Name of the Page Map | O | ❓ |
| LINEDATA-5-074 | This is an optional parameter. | ❓ |
| LINEDATA-5-075 | Definition, it must appear before the Data Maps in the Page Definition. | ❓ |
| LINEDATA-5-076 | Structured Field Introducer:** SF Length (2B), ID = X'D3A7CA', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-077 | 0–1 | CODE | CCPid | X'0001'–X'FFFF' | CCP Identifier | M | ❓ |
| LINEDATA-5-078 | 2–3 | CODE | NxtCCPid | X'0001'–X'FFFF' | Next CCP Identifier | M | ❓ |
| LINEDATA-5-079 | 4 | BITS | CCPFlgs |  | Conditional Processing Flags | M | ❓ |
| LINEDATA-5-080 | 5 |  |  | X'00' | Reserved | M | ❓ |
| LINEDATA-5-081 | 6–7 | UBIN | NumRGs | X'0001'–X'FFFF' | Number of repeating groups | M | ❓ |
| LINEDATA-5-082 | 8–9 | UBIN | RGLgth | X'0015'–X'FFFF' | Length of each repeating group | M | ❓ |
| LINEDATA-5-083 | 10–11 | UBIN | CSLgth | X'0000'–X'FFFF' | Length of comparison string | M | ❓ |
| LINEDATA-5-084 | 12–n |  | Repeating groups |  | One or more repeating groups | M | ❓ |
| LINEDATA-5-085 | Bit 1 If B'1', this CCP requires action after a subpage boundary. | ❓ |
| LINEDATA-5-086 | A new page is started by a true condition. | ❓ |
| LINEDATA-5-087 | Conditional processing is active. | ❓ |
| LINEDATA-5-088 | ANSI control characters are used. | ❓ |
| LINEDATA-5-089 | 0 | Timing of Action | UBIN | 0: Take default action (immediately before presenting current line)<br>1: Take action immediately before presenting current line<br>2: Take action before presenting current subpage<br>129: Take action immediately after presenting current line<br>130: Take action after presenting current subpage | ❓ |
| LINEDATA-5-090 | 1 | Medium Map Action | UBIN | 0: Ignore<br>1: Continue using current medium map with page eject<br>2: Invoke named Medium Map<br>3: Invoke first Medium Map<br>4: Invoke next Medium Map | ❓ |
| LINEDATA-5-091 | 2–9 | Medium Map Name | CHAR | Any 8-byte value | ❓ |
| LINEDATA-5-092 | 10 | Data Map Action | UBIN | 0: Ignore<br>1: Continue using current Data Map with page eject<br>2: Invoke named Data Map<br>3: Invoke first Data Map<br>4: Invoke next Data Map | ❓ |
| LINEDATA-5-093 | 11–18 | Data Map Name | CHAR | Any 8-byte value | ❓ |
| LINEDATA-5-094 | 19 | Comparison | UBIN | 0: Any change<br>1: Equal to<br>2: Less than<br>3: Equal to or less than<br>4: Greater than<br>5: Equal to or greater than<br>6: Not equal<br>7: Take the action without comparison | ❓ |
| LINEDATA-5-095 | 20–nnn | Comparison String | CHAR | 1 to nnn bytes, where nnn plus the total length of the fixed-length fields in the CCP is less than, or equal to, 32,759 bytes | ❓ |
| LINEDATA-5-096 | specified in this parameter must match the encoding of the input data. | ❓ |
| LINEDATA-5-097 | The Data Map Transmission Subcase Descriptor structured field is supported only for migration purposes. | ❓ |
| LINEDATA-5-098 | Structured Field Introducer:** SF Length (2B), ID = X'D3A6E3', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-099 | 0–3 |  | ConData | X'0001 00FF' | Constant data | M | ❓ |
| LINEDATA-5-100 | This field must be set to X'0001 00FF', but is not checked. | ❓ |
| LINEDATA-5-101 | Structured Field Introducer:** SF Length (2B), ID = X'D3A9CA', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-102 | 0–7 | CHAR | DMName |  | Name of the Data Map | O | ❓ |
| LINEDATA-5-103 | name specified on the corresponding Begin Data Map structured field. | ❓ |
| LINEDATA-5-104 | initiated by a Begin Data Map Transmission Subcase structured field. | ❓ |
| LINEDATA-5-105 | Structured Field Introducer:** SF Length (2B), ID = X'D3A9E3', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-106 | 0–7 | CHAR | DMXName |  | Name of the Data Map Transmission Subcase | O | ❓ |
| LINEDATA-5-107 | Transmission Subcase structured field. | ❓ |
| LINEDATA-5-108 | Structured Field Introducer:** SF Length (2B), ID = X'D3A9CB', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-109 | 0–7 | CHAR | PMName |  | Name of the Page Map | O | ❓ |
| LINEDATA-5-110 | name specified on the corresponding Begin Page Map structured field. | ❓ |
| LINEDATA-5-111 | (FDX) structured fields. | ❓ |
| LINEDATA-5-112 | Structured Field Introducer:** SF Length (2B), ID = X'D3AAEC', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-113 | 0–1 | UBIN | TxtLgth | 1–65,535 | Number of data bytes in following FDX | M | ❓ |
| LINEDATA-5-114 | structured fields should not be specified. | ❓ |
| LINEDATA-5-115 | The DataStrt and DataLgth fields of the LND, RCD, or XMD specify the fixed data offset and length. | ❓ |
| LINEDATA-5-116 | Structured Field Introducer:** SF Length (2B), ID = X'D3EEEC', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-117 | 0–n | CHAR | Text |  | Fixed text to be added | O | ❓ |
| LINEDATA-5-118 | From 0 to 32,743 bytes may be specified. | ❓ |
| LINEDATA-5-119 | Structured Field Introducer:** SF Length (2B), ID = X'D3ABCA', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-120 | 0–7 | CHAR | DMName |  | Name of the new Data Map | M | ❓ |
| LINEDATA-5-121 | shorter than eight bytes, trailing blanks must be added. | ❓ |
| LINEDATA-5-122 | 1. The IOB is a MO:DCA structured field. The following description documents its use in line-mode and mixed | ❓ |
| LINEDATA-5-123 | 2. When processing XML data, the IOB may only be used in a Page Definition resource. | ❓ |
| LINEDATA-5-124 | Structured Field Introducer:** SF Length (2B), ID = X'D3AFC3', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-125 | 0–7 | CHAR | ObjName |  | Name of the object | M | ❓ |
| LINEDATA-5-126 | 8 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-127 | 9 | CODE | ObjType |  | Object type | M | ❓ |
| LINEDATA-5-128 | 10–12 | SBIN | XoaOset | -32,768 – +32,767 | X-axis origin of the object area | M | ❓ |
| LINEDATA-5-129 | 13–15 | SBIN | YoaOset | -32,768 – +32,767 | Y-axis origin of the object area | M | ❓ |
| LINEDATA-5-130 | 16–17 | CODE | XoaOrent |  | X-axis rotation | M | ❓ |
| LINEDATA-5-131 | 18–19 | CODE | YoaOrent |  | Y-axis rotation | M | ❓ |
| LINEDATA-5-132 | 20–22 | SBIN | XocaOset |  | X-axis offset | M | ❓ |
| LINEDATA-5-133 | 23–25 | SBIN | YocaOset |  | Y-axis offset | M | ❓ |
| LINEDATA-5-134 | 26 | CODE | RefCSys | X'00', X'01' | Reference coordinate system | M | ❓ |
| LINEDATA-5-135 | 27–n |  | Triplets |  | Triplets | M | ❓ |
| LINEDATA-5-136 | p,Yp) coordinate system. | ❓ |
| LINEDATA-5-137 | each IOB must be unique within the PageDef. | ❓ |
| LINEDATA-5-138 | 0 | UBIN | Tlength | 7 | Length of the triplet | M | ❓ |
| LINEDATA-5-139 | 1 | CODE | Tid | X'22' | Identifies the Extended Resource Local Identifier triplet | M | ❓ |
| LINEDATA-5-140 | 2 | CODE | ResType | X'30' | Resource type: IOB Reference | M | ❓ |
| LINEDATA-5-141 | 3–6 | CODE | ResLID | X'00000000'–X'FFFFFFFF' | Specifies the extended resource local ID | M | ❓ |
| LINEDATA-5-142 | It may be in the range of X'00000000' to X'FFFFFFFF'. | ❓ |
| LINEDATA-5-143 | Structured Field Introducer:** SF Length (2B), ID = X'D3AFD8', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-144 | 0–7 | CHAR | OvlyName |  | Name of the overlay resource | M | ❓ |
| LINEDATA-5-145 | 8–10 | SBIN | XolOset | -32,768 – +32,767 | X-axis origin for the page overlay | M | ❓ |
| LINEDATA-5-146 | 11–13 | SBIN | YolOset | -32,768 – +32,767 | Y-axis origin for the page overlay | M | ❓ |
| LINEDATA-5-147 | 14–15 | CODE | OvlyOrent |  | The overlay's X-axis rotation | O | ❓ |
| LINEDATA-5-148 | 16–n |  | Triplets |  | Triplets | O | ❓ |
| LINEDATA-5-149 | 270 degrees. The page overlay Y axis rotation is always 90 degrees greater than the page | ❓ |
| LINEDATA-5-150 | IS/1 and IS/2 interchange sets only support 0° rotation of a page overlay. | ❓ |
| LINEDATA-5-151 | either the I-axis offset (bytes 8–10), the B-axis offset (bytes 11–13), or both. | ❓ |
| LINEDATA-5-152 | Structured Field Introducer:** SF Length (2B), ID = X'D3AF5F', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-153 | 0–7 | CHAR | PsegName |  | Name of the page segment resource | M | ❓ |
| LINEDATA-5-154 | 8–10 | SBIN | IpsOset | -32,768 – +32,767 | I-axis origin for the page segment | M | ❓ |
| LINEDATA-5-155 | 11–13 | SBIN | BpsOset | -32,768 – +32,767 | B-axis origin for the page segment | M | ❓ |
| LINEDATA-5-156 | 14–n |  | Triplets |  | Triplets | O | ❓ |
| LINEDATA-5-157 | be positioned and oriented correctly on the MO:DCA page. | ❓ |
| LINEDATA-5-158 | (RCD) or XML Descriptor (XMD) structured fields in the Data Map Transmission Subcase. | ❓ |
| LINEDATA-5-159 | Structured Field Introducer:** SF Length (2B), ID = X'D3AAE7', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-160 | 0–1 | UBIN | NumDSC | 1–65,535 | Number of LND, RCD, or XMD structured fields | M | ❓ |
| LINEDATA-5-161 | Values from 1 through the number of LND, RCD, or XMD are allowed. | ❓ |
| LINEDATA-5-162 | Structured Field Introducer:** SF Length (2B), ID = X'D3A6E7', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-163 | 0–1 | BITS | LNDFlgs |  | LND flags | M | ❓ |
| LINEDATA-5-164 | 2–3 | UBIN | IPos |  | Inline Position | M | ❓ |
| LINEDATA-5-165 | 4–5 | UBIN/SBIN | BPos |  | Baseline position | M | ❓ |
| LINEDATA-5-166 | 6–9 | CODE | TxtOrent |  | Text (I,B) Orientation | M | ❓ |
| LINEDATA-5-167 | 10 | CODE | FntLID |  | Primary font local ID | M | ❓ |
| LINEDATA-5-168 | 11 | CODE | ChnlCde |  | Channel code | M | ❓ |
| LINEDATA-5-169 | 12–13 | UBIN | NLNDskp |  | Next LND if skipping | M | ❓ |
| LINEDATA-5-170 | 14–15 | UBIN | NLNDsp |  | Next LND if spacing | M | ❓ |
| LINEDATA-5-171 | 16–17 | UBIN | NLNDreu |  | Next LND if reusing data | M | ❓ |
| LINEDATA-5-172 | 18–25 | CHAR | SupName |  | Suppression token name | M | ❓ |
| LINEDATA-5-173 | 26 | CODE | SOLid |  | Shift-out font local ID | M | ❓ |
| LINEDATA-5-174 | 27–30 | UBIN | DataStrt |  | Data start position | M | ❓ |
| LINEDATA-5-175 | 31–32 | UBIN | DataLgth |  | Data length | M | ❓ |
| LINEDATA-5-176 | 33–34 | CODE | TxtColor |  | Text color value | M | ❓ |
| LINEDATA-5-177 | 35–36 | UBIN | NLNDccp |  | Next LND if conditional processing | M | ❓ |
| LINEDATA-5-178 | 37 | CODE | SubpgID |  | Subpage ID | M | ❓ |
| LINEDATA-5-179 | 38–39 | CODE | CCPID |  | CCP Identifier | M | ❓ |
| LINEDATA-5-180 | 40–n |  | Triplets |  | Triplets | O | ❓ |
| LINEDATA-5-181 | 37 CODE SubpgID X'00'–X'FF' Subpage ID M | ❓ |
| LINEDATA-5-182 | zero. LND triplets are not allowed on the shorter version of the LND. | ❓ |
| LINEDATA-5-183 | the inline position specified in bytes 2–3. This position becomes the new print position. | ❓ |
| LINEDATA-5-184 | If the record to be processed contains a TRC, use the font corresponding to | ❓ |
| LINEDATA-5-185 | If the current Data Map maps fonts with MCF or MDR structured fields, use | ❓ |
| LINEDATA-5-186 | If the current Data Map does not map fonts, use the hardware default font. | ❓ |
| LINEDATA-5-187 | is measured as a positive offset in the baseline direction from the current text (I,B) | ❓ |
| LINEDATA-5-188 | The text orientation of an LND that specifies relative baseline positioning must be | ❓ |
| LINEDATA-5-189 | For base LNDs, offsets are defined relative to the last base LND processed, that is, the last | ❓ |
| LINEDATA-5-190 | For reuse LNDs other than base LNDs, the offset is defined relative to the last LND that was | ❓ |
| LINEDATA-5-191 | If the first LND of a Data Map specifies relative positioning, its offset is defined relative to the | ❓ |
| LINEDATA-5-192 | If the first LND of a subpage specifies relative positioning, its offset is defined relative to the | ❓ |
| LINEDATA-5-193 | of byte 0 is B'1', this position is used and becomes the new print position. | ❓ |
| LINEDATA-5-194 | number of LNDs are allowed. | ❓ |
| LINEDATA-5-195 | defined in the Page Descriptor (PGD) structured field, the printing of the page stops. | ❓ |
| LINEDATA-5-196 | range for each component is 0–255. | ❓ |
| LINEDATA-5-197 | X'0000' or X'FF00' | Device default | — | — | — | ❓ |
| LINEDATA-5-198 | X'0001' or X'FF01' | Blue | 0 | 0 | 255 | ❓ |
| LINEDATA-5-199 | X'0002' or X'FF02' | Red | 255 | 0 | 0 | ❓ |
| LINEDATA-5-200 | X'0003' or X'FF03' | Pink/magenta | 255 | 0 | 255 | ❓ |
| LINEDATA-5-201 | X'0004' or X'FF04' | Green | 0 | 255 | 0 | ❓ |
| LINEDATA-5-202 | X'0005' or X'FF05' | Turquoise/cyan | 0 | 255 | 255 | ❓ |
| LINEDATA-5-203 | X'0006' or X'FF06' | Yellow | 255 | 255 | 0 | ❓ |
| LINEDATA-5-204 | X'0007' | White; see note 1 | 255 | 255 | 255 | ❓ |
| LINEDATA-5-205 | X'0008' | Black | 0 | 0 | 0 | ❓ |
| LINEDATA-5-206 | X'0009' | Dark blue | 0 | 0 | 170 | ❓ |
| LINEDATA-5-207 | X'000A' | Orange | 255 | 128 | 0 | ❓ |
| LINEDATA-5-208 | X'000B' | Purple | 170 | 0 | 170 | ❓ |
| LINEDATA-5-209 | X'000C' | Dark green | 0 | 146 | 0 | ❓ |
| LINEDATA-5-210 | X'000D' | Dark turquoise | 0 | 146 | 170 | ❓ |
| LINEDATA-5-211 | X'000E' | Mustard | 196 | 160 | 32 | ❓ |
| LINEDATA-5-212 | X'000F' | Gray | 131 | 131 | 131 | ❓ |
| LINEDATA-5-213 | X'0010' | Brown | 144 | 48 | 0 | ❓ |
| LINEDATA-5-214 | X'FF07' | Device default | — | — | — | ❓ |
| LINEDATA-5-215 | X'FF08' | Color of medium | — | — | — | ❓ |
| LINEDATA-5-216 | All others | Reserved | — | — | — | ❓ |
| LINEDATA-5-217 | 1. The color rendered on presentation devices that do not support white is device-dependent. For | ❓ |
| LINEDATA-5-218 | 2. The value X'FFFF' is supported for migration purposes only and specifies the presentation | ❓ |
| LINEDATA-5-219 | 11 of byte 1 is B'1'), this parameter is used to identify subpages when the timing of the | ❓ |
| LINEDATA-5-220 | CMR must be UTF-16BE. | ❓ |
| LINEDATA-5-221 | 0 | UBIN | Tlength | 5–254 | Length of the triplet | M | ❓ |
| LINEDATA-5-222 | 1 | CODE | Tid | X'02' | Identifies the Fully Qualified Name triplet | M | ❓ |
| LINEDATA-5-223 | 2 | CODE | FQNType |  | FQN type | M | ❓ |
| LINEDATA-5-224 | 3 | CODE | FQNFmt | X'00' | GID format is character string | M | ❓ |
| LINEDATA-5-225 | 4–n |  | FQName |  | GID of the Record Descriptor | M | ❓ |
| LINEDATA-5-226 | The encoding for the external identifier of the CMR must be UTF-16BE. | ❓ |
| LINEDATA-5-227 | matching ID is not found, an exception is generated. | ❓ |
| LINEDATA-5-228 | 0 | UBIN | Tlength | 7 | Length of the triplet | M | ❓ |
| LINEDATA-5-229 | 1 | CODE | Tid | X'22' | Identifies the Extended Resource Local Identifier triplet | M | ❓ |
| LINEDATA-5-230 | 2 | CODE | ResType | X'30' | Resource type: IOB Reference | M | ❓ |
| LINEDATA-5-231 | 3–6 | CODE | ResLID | X'00000000'–X'FFFFFFFF' | Specifies the extended resource local ID | M | ❓ |
| LINEDATA-5-232 | in the IOB is measured with respect to page (X p,Yp) coordinate system X p-axis. | ❓ |
| LINEDATA-5-233 | Highlight | ❓ |
| LINEDATA-5-234 | CIELAB | ❓ |
| LINEDATA-5-235 | Standard OCA color space | ❓ |
| LINEDATA-5-236 | 1 to the binary values 0 to 255. The color value specifies the color. With the RGB color space and an 8 bit per | ❓ |
| LINEDATA-5-237 | 0 | UBIN | Tlength | 4 or 18 | Length of the triplet | M | ❓ |
| LINEDATA-5-238 | 1 | CODE | Tid | X'69' | Identifies the Bar Code Symbol Descriptor triplet | M | ❓ |
| LINEDATA-5-239 | 2–3 | CODE | DescID | X'0001'–X'FFFE' | Identifies a bar code symbol descriptor | M | ❓ |
| LINEDATA-5-240 | 4 | BITS | SymbFlgs |  | Symbol flags | O | ❓ |
| LINEDATA-5-241 | 5–6 | UBIN | BCWdth |  | Desired symbol width | O | ❓ |
| LINEDATA-5-242 | 7 | CODE | BCType |  | Bar code type | O | ❓ |
| LINEDATA-5-243 | 8 | CODE | BCMod |  | Bar code modifier | O | ❓ |
| LINEDATA-5-244 | 9 | CODE | FntLID | X'00'–X'FE', X'FF' | Font local identifier | O | ❓ |
| LINEDATA-5-245 | 10–11 | CODE | Color |  | Bar code color | O | ❓ |
| LINEDATA-5-246 | 12 | UBIN | ModWdth |  | Module width in mils | O | ❓ |
| LINEDATA-5-247 | 13–14 | UBIN | ElmtHt |  | Element Height in L-units | O | ❓ |
| LINEDATA-5-248 | 15 | UBIN | Mult |  | Height multiplier | O | ❓ |
| LINEDATA-5-249 | 16–17 | UBIN | WE:NE |  | Wide-to-narrow ratio | O | ❓ |
| LINEDATA-5-250 | p-axis as the bar code symbols in the object. | ❓ |
| LINEDATA-5-251 | If the user specified an EAN bar code type (X'08', X'09', X'16', or X'17'), | ❓ |
| LINEDATA-5-252 | 2 X'16'—two-digit supplemental X'00' | ❓ |
| LINEDATA-5-253 | 5 X'17'—five-digit supplemental X'00' | ❓ |
| LINEDATA-5-254 | 7 X'08'—EAN-8 X'00' | ❓ |
| LINEDATA-5-255 | 12 X'09'—EAN-13 X'00' | ❓ |
| LINEDATA-5-256 | 14 X'16'—two-digit supplemental X'01' | ❓ |
| LINEDATA-5-257 | 17 X'17'—five-digit supplemental X'01' | ❓ |
| LINEDATA-5-258 | If the user specified a UPC bar code type (X'03', X'05', X'06', or X'07'), | ❓ |
| LINEDATA-5-259 | 2 X'06'—two-digit supplemental X'00' | ❓ |
| LINEDATA-5-260 | 5 X'07'—five-digit supplemental X'00' | ❓ |
| LINEDATA-5-261 | 10 X'05'—UPC version E X'00' | ❓ |
| LINEDATA-5-262 | 11 X'03'—UPC version A X'00' | ❓ |
| LINEDATA-5-263 | 12 X'06'—two-digit supplemental X'02' | ❓ |
| LINEDATA-5-264 | 13 X'06'—two-digit supplemental X'01' | ❓ |
| LINEDATA-5-265 | 15 X'07'—five-digit supplemental X'02' | ❓ |
| LINEDATA-5-266 | 16 X'07'—five-digit supplemental X'01' | ❓ |
| LINEDATA-5-267 | If the user specified a POSTNET bar code type (X'18'), truncate the data | ❓ |
| LINEDATA-5-268 | 5 X'18'—POSTNET X'00' | ❓ |
| LINEDATA-5-269 | 9 X'18'—POSTNET X'01' | ❓ |
| LINEDATA-5-270 | 11 X'18'—POSTNET X'02' or X'04' | ❓ |
| LINEDATA-5-271 | If the user specified an Intelligent Mail | ❓ |
| LINEDATA-5-272 | 20 X'22'—Intelligent Mail Barcode X'00' | ❓ |
| LINEDATA-5-273 | 25 X'22'—Intelligent Mail Barcode X'01' | ❓ |
| LINEDATA-5-274 | 29 X'22'—Intelligent Mail Barcode X'02' | ❓ |
| LINEDATA-5-275 | 31 X'22'—Intelligent Mail Barcode X'03' | ❓ |
| LINEDATA-5-276 | If the user specified any other bar code type, use the user-specified bar | ❓ |
| LINEDATA-5-277 | two different size elements exist, that is, for a two-level bar code | ❓ |
| LINEDATA-5-278 | 0 | UBIN | Tlength | 17 or 19 | Length of the triplet | M | ❓ |
| LINEDATA-5-279 | 1 | CODE | Tid | X'6C' | Identifies the Resource Object Include triplet | M | ❓ |
| LINEDATA-5-280 | 2 | CODE | ObjType | X'DF', X'5F' | Object type | M | ❓ |
| LINEDATA-5-281 | 3–10 | CHAR | ObjName |  | Name of the object | M | ❓ |
| LINEDATA-5-282 | 11–13 | SBIN | IobjOset | -32,768 – +32,767 | Relative I-axis offset | M | ❓ |
| LINEDATA-5-283 | 14–16 | SBIN | BobjOset | -32,768 – +32,767 | Relative B-axis offset | M | ❓ |
| LINEDATA-5-284 | 17–18 | CODE | ObOrent |  | The overlay's X axis rotation | O | ❓ |
| LINEDATA-5-285 | ObjName Specifies the object name | ❓ |
| LINEDATA-5-286 | 1. If this parameter is omitted, the architected default value for the overlay rotation is X'0000', | ❓ |
| LINEDATA-5-287 | 2. When a page segment is included with this triplet, the ObOrent parameter is ignored and | ❓ |
| LINEDATA-5-288 | the rotation of objects in the page segment is summarized in Table 10. | ❓ |
| LINEDATA-5-289 | 0 UBIN Tlength 4–254 Length of the triplet, including Tlength M | ❓ |
| LINEDATA-5-290 | 1 CODE Tid X'7B' Identifies the Additional Bar Code Parameters | ❓ |
| LINEDATA-5-291 | 2 Reserved; should be zero M | ❓ |
| LINEDATA-5-292 | the Bar Code Data structured field, see the Bar Code Object Content Architecture Reference. | ❓ |
| LINEDATA-5-293 | If this triplet is specified more than once, only the first is used. | ❓ |
| LINEDATA-5-294 | This triplet applies to the first Resource Object Include (X'6C') triplet or Extended Resource Local Identifier | ❓ |
| LINEDATA-5-295 | If this triplet is not followed by either the Resource Object Include triplet or the Extended Resource Local | ❓ |
| LINEDATA-5-296 | The LND or RCD DataStrt/DataLgth fields or the RCD Fldno is used to select the name of the object. The | ❓ |
| LINEDATA-5-297 | 11=B'1' if present, it is ignored. | ❓ |
| LINEDATA-5-298 | 0 | UBIN | Tlength | 4 | Length of the triplet | M | ❓ |
| LINEDATA-5-299 | 1 | CODE | Tid | X'89' | Identifies the Object Reference Qualifier triplet | M | ❓ |
| LINEDATA-5-300 | 2 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-301 | 3 | BITS | QualFlg |  | Object reference qualifier flags | M | ❓ |
| LINEDATA-5-302 | 0 Object reference qualifier flags | ❓ |
| LINEDATA-5-303 | 1. If this triplet is omitted, the architected default for QualFlg bit 0 is B'0'. | ❓ |
| LINEDATA-5-304 | 2. When the QualFlag bit 0 is B'1', the encoding of the object name obtained from the input | ❓ |
| LINEDATA-5-305 | Must be no more than 8 characters long; if longer, some AFP print servers use only | ❓ |
| LINEDATA-5-306 | Must follow the naming conventions used in AFP environments; see External | ❓ |
| LINEDATA-5-307 | Must not contain platform-dependent library names or path names. | ❓ |
| LINEDATA-5-308 | Must be encoded using EBCDIC if the resource is mapped in the active environment | ❓ |
| LINEDATA-5-309 | Must not use double-byte encoding. | ❓ |
| LINEDATA-5-310 | Must not contain any shift-in or shift-out characters. | ❓ |
| LINEDATA-5-311 | must immediately follow the FQN type X'DE' triplet that specifies the CMR name. | ❓ |
| LINEDATA-5-312 | 0 | UBIN | Tlength | 5 | Length of the triplet | M | ❓ |
| LINEDATA-5-313 | 1 | CODE | Tid | X'91' | Identifies the Color Management Descriptor triplet | M | ❓ |
| LINEDATA-5-314 | 2 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-315 | 3 | CODE | ProcMode |  | Processing mode for the CMR | M | ❓ |
| LINEDATA-5-316 | 4 | CODE | CMRScpe | X'01' | Scope of CMR | M | ❓ |
| LINEDATA-5-317 | specify flag bit 11=B'1'). If the triplet is present, it is ignored. | ❓ |
| LINEDATA-5-318 | 0 | UBIN | Tlength | 7 | Length of the triplet | M | ❓ |
| LINEDATA-5-319 | 1 | CODE | Tid | X'93' | Identifies the Concatenate Bar Code Data triplet | M | ❓ |
| LINEDATA-5-320 | 2 | BITS | CBCFlgs |  | Concatenation flags | M | ❓ |
| LINEDATA-5-321 | 3–4 | UBIN | SymbID | X'0001'–X'FFFF' | Identifies a bar code symbol concatenation | M | ❓ |
| LINEDATA-5-322 | 5–6 | UBIN | SegOrder | X'0001'–X'FFFF', X'0000' | Order of concatenation | M | ❓ |
| LINEDATA-5-323 | Note: The RCDs in a Data Map are numbered sequentially, starting with 1. | ❓ |
| LINEDATA-5-324 | Structured Field Introducer:** SF Length (2B), ID = X'D3A68D', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-325 | 0–9 | CHAR | RecID |  | Record descriptor ID | M | ❓ |
| LINEDATA-5-326 | 10 | CODE | RecType | X'00'–X'03' | Record Type | M | ❓ |
| LINEDATA-5-327 | 11–13 | BITS | RCDFlgs |  | RCD flags | M | ❓ |
| LINEDATA-5-328 | 14 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-329 | 15–16 | UBIN | IPos |  | Inline Position | M | ❓ |
| LINEDATA-5-330 | 17–18 | UBIN/SBIN | BPos |  | Baseline position | M | ❓ |
| LINEDATA-5-331 | 19–22 | CODE | TxtOrent |  | Text (I,B) Orientation | M | ❓ |
| LINEDATA-5-332 | 23 | CODE | FntLID |  | Primary font local ID | M | ❓ |
| LINEDATA-5-333 | 24–25 | UBIN | FLDrcd |  | Field RCD Pointer | M | ❓ |
| LINEDATA-5-334 | 26–33 | CHAR | SupName |  | Suppression token name | M | ❓ |
| LINEDATA-5-335 | 34 | CODE | SOLid |  | Shift-out font local ID | M | ❓ |
| LINEDATA-5-336 | 35–38 | UBIN | DataStrt |  | Data start position | M | ❓ |
| LINEDATA-5-337 | 39–40 | UBIN | DataLgth |  | Data length | M | ❓ |
| LINEDATA-5-338 | 41–42 | UBIN | CONDrcd |  | Conditional Processing RCD Pointer | M | ❓ |
| LINEDATA-5-339 | 43 | CODE | SubpgID |  | Subpage ID (always X'00' for RCDs) | M | ❓ |
| LINEDATA-5-340 | 44–45 | CODE | CCPID |  | CCP Identifier | M | ❓ |
| LINEDATA-5-341 | 46–47 | UBIN | Pgno |  | Starting page number | M | ❓ |
| LINEDATA-5-342 | 48–49 | UBIN | ESpac |  | End Space | M | ❓ |
| LINEDATA-5-343 | 50 | CODE | Align |  | Field Alignment | M | ❓ |
| LINEDATA-5-344 | 51–52 | CODE | FldDelim |  | Field Delimiter | M | ❓ |
| LINEDATA-5-345 | 53–54 | UBIN | Fldno |  | Field Number | M | ❓ |
| LINEDATA-5-346 | 55–56 | UBIN | AdBIncr |  | Additional baseline increment | M | ❓ |
| LINEDATA-5-347 | 57–69 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-348 | 70–n |  | Triplets |  | Triplets | O | ❓ |
| LINEDATA-5-349 | RCD Semantics for triplet applicability. O | ❓ |
| LINEDATA-5-350 | Record RCDs, which define the processing for an input record or define a default page header or trailer | ❓ |
| LINEDATA-5-351 | Field RCDs, which define the processing for an input field or specify constant text or graphics | ❓ |
| LINEDATA-5-352 | Conditional Processing RCDs, which specify the Conditional Processing associated with an input record | ❓ |
| LINEDATA-5-353 | contain a Fully Qualified Name (FQN) X'02' triplet type X'01'. The FQN type X'01' triplet is | ❓ |
| LINEDATA-5-354 | 1. To be able to find a matching Record Descriptor ID, the encoding of the identifier specified | ❓ |
| LINEDATA-5-355 | 2. If the FQN type X'01' triplet is used, all record type RCD structured fields must use the | ❓ |
| LINEDATA-5-356 | 3. If the FQN type X'01' triplet is used, the names specified for all the FQN triplets must be | ❓ |
| LINEDATA-5-357 | Page Trailer RCD can be specified in a Data Map, and no input record data is | ❓ |
| LINEDATA-5-358 | page number is generated. | ❓ |
| LINEDATA-5-359 | See “Line Descriptor (LND)”. | ❓ |
| LINEDATA-5-360 | Relative Baseline Position for Record and Field RCDs | ❓ |
| LINEDATA-5-361 | Overflow Processing for a Record RCD | ❓ |
| LINEDATA-5-362 | be performed on the current input data record. This parameter specifies the relative RCD | ❓ |
| LINEDATA-5-363 | “Rendering Intent (X'95') Triplet” | ❓ |
| LINEDATA-5-364 | Any Record RCD with a specification of New Page | ❓ |
| LINEDATA-5-365 | A relative baseline overflow | ❓ |
| LINEDATA-5-366 | A Data Map change or Medium Map change, or, in mixed-mode, a Begin Document or Begin Page | ❓ |
| LINEDATA-5-367 | 1. If this is the start of a line data document (no previous page ejects, group header records, or body records | ❓ |
| LINEDATA-5-368 | 2. If an active page header record was in effect prior to this RCD, that record is presented on the current page | ❓ |
| LINEDATA-5-369 | 3. If an active page trailer record was in effect prior to this RCD, that record is presented on the current page | ❓ |
| LINEDATA-5-370 | 1. The current print position is moved to the top of the new page. If the Data Map is changed, the new Data | ❓ |
| LINEDATA-5-371 | 2. The baseline position is offset from the top of the new page by the top margin. | ❓ |
| LINEDATA-5-372 | 3. If the RCD causing the page eject is a Page Header, Group Header, or Page Trailer RCD, the input record | ❓ |
| LINEDATA-5-373 | 4. If an active group header record exists for this Data Map, that record is presented on the new page using | ❓ |
| LINEDATA-5-374 | 5. If the page eject was caused by a Body RCD, the input record causing the page eject is presented on the | ❓ |
| LINEDATA-5-375 | Definition (X'7F') Triplet”. | ❓ |
| LINEDATA-5-376 | Descriptor's RecID field and is used as the Record Descriptor ID. | ❓ |
| LINEDATA-5-377 | 0 | UBIN | Tlength | 5–254 | Length of the triplet | M | ❓ |
| LINEDATA-5-378 | 1 | CODE | Tid | X'02' | Identifies the Fully Qualified Name triplet | M | ❓ |
| LINEDATA-5-379 | 2 | CODE | FQNType |  | FQN type | M | ❓ |
| LINEDATA-5-380 | 3 | CODE | FQNFmt | X'00' | GID format is character string | M | ❓ |
| LINEDATA-5-381 | 4–n |  | FQName |  | GID of the Record Descriptor | M | ❓ |
| LINEDATA-5-382 | specified in this parameter must match the encoding of the input data. | ❓ |
| LINEDATA-5-383 | See “Fully Qualified Name (X'02') Triplet”. | ❓ |
| LINEDATA-5-384 | See “Extended Resource Local Identifier (X'22') Triplet”. | ❓ |
| LINEDATA-5-385 | If it is specified once, the color applies to all graphics constructs. | ❓ |
| LINEDATA-5-386 | If it is specified twice, then: | ❓ |
| LINEDATA-5-387 | If it is specified more than twice, only the first two are used. | ❓ |
| LINEDATA-5-388 | Highlight | ❓ |
| LINEDATA-5-389 | CIELAB | ❓ |
| LINEDATA-5-390 | Standard OCA color space | ❓ |
| LINEDATA-5-391 | 1 to the binary values 0 to 255. The color value specifies the color. With the RGB color space and an 8 bit per | ❓ |
| LINEDATA-5-392 | Document Content Architecture (MO:DCA) Reference. | ❓ |
| LINEDATA-5-393 | this triplet may be at different offsets in the LND and RCD. | ❓ |
| LINEDATA-5-394 | See “Resource Object Include (X'6C') Triplet”. | ❓ |
| LINEDATA-5-395 | See “Additional Bar Code Parameters (X'7B') Triplet”. | ❓ |
| LINEDATA-5-396 | . Not all presentation services programs support this triplet. | ❓ |
| LINEDATA-5-397 | 0 | UBIN | Tlength | 20 or 35 | Length of the triplet | M | ❓ |
| LINEDATA-5-398 | 1 | CODE | Tid | X'7E' | Identifies the Graphics Descriptor triplet | M | ❓ |
| LINEDATA-5-399 | 2 | CODE | ParmSpc | X'01'–X'03' | Parameter specification | M | ❓ |
| LINEDATA-5-400 | 3–4 | UBIN | Graphid | X'0000'–X'FFFE' | ID for matching Start/End graphic pairs | M | ❓ |
| LINEDATA-5-401 | 5 | CODE | GrPrim | X'01'–X'05' | Graphics primitive | M | ❓ |
| LINEDATA-5-402 | 6 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-403 | 7 | BITS | GraFlgs |  | Graphics flags | M | ❓ |
| LINEDATA-5-404 | 8–9 | UBIN | Iend |  | I-coordinate for primitive end point | M | ❓ |
| LINEDATA-5-405 | 10–11 | UBIN | Bend |  | B-coordinate for primitive end point | M | ❓ |
| LINEDATA-5-406 | 12–13 | UBIN | HAXIS | 0–32,767 | Rounded corner X-axis | M | ❓ |
| LINEDATA-5-407 | 14–15 | UBIN | VAXIS | 0–32,767 | Rounded corner Y-axis | M | ❓ |
| LINEDATA-5-408 | 16 | UBIN | MH | X'00'–X'FF' | Integer multiplier for radius | M | ❓ |
| LINEDATA-5-409 | 17 | UBIN | MFR | X'00'–X'FF' | Fractional multiplier for radius | M | ❓ |
| LINEDATA-5-410 | 18–19 | CODE | DescID | X'0001'–X'FFFE' | Identifies a graphics descriptor | M | ❓ |
| LINEDATA-5-411 | 20 | CODE | FGMix | X'02' | Foreground mixing rule | O | ❓ |
| LINEDATA-5-412 | 21 |  |  | 0 | Reserved | O | ❓ |
| LINEDATA-5-413 | 22 | CODE | LineTpe |  | Line type | O | ❓ |
| LINEDATA-5-414 | 23 | CODE | LineWMH |  | Line width integral multiplier | O | ❓ |
| LINEDATA-5-415 | 24 | CODE | LineWMFR |  | Line width fractional multiplier | O | ❓ |
| LINEDATA-5-416 | 25 | CODE | PattSet | X'00' | Pattern set | O | ❓ |
| LINEDATA-5-417 | 26 | CODE | PattSymb |  | Pattern symbol | O | ❓ |
| LINEDATA-5-418 | 27–28 | SBIN | XMAJ |  | I-coordinate of major axis end point | O | ❓ |
| LINEDATA-5-419 | 29–30 | SBIN | YMIN |  | B-coordinate of minor axis end point | O | ❓ |
| LINEDATA-5-420 | 31–32 | SBIN | XMIN |  | I-coordinate of minor axis end point | O | ❓ |
| LINEDATA-5-421 | 33–34 | SBIN | YMAJ |  | B-coordinate of major axis end point | O | ❓ |
| LINEDATA-5-422 | specified in the start parameters. The RCD IPos/Bpos parameters specify the | ❓ |
| LINEDATA-5-423 | color of the fill pattern is the presentation process default color. | ❓ |
| LINEDATA-5-424 | This parameter is ignored if ParmSpc does not equal X'01' or X'02'. | ❓ |
| LINEDATA-5-425 | object with the GDD Set Current Defaults instruction and the Set Mix drawing order. | ❓ |
| LINEDATA-5-426 | . This parameter is specified in an AFP GOCA object with the GDD | ❓ |
| LINEDATA-5-427 | 1. The last 15 bytes (bytes 20–34) in this triplet are optional as a group. That is, either they are all specified or | ❓ |
| LINEDATA-5-428 | 2. The X'22', X'69', and X'6C' triplets are ignored when this triplet is specified on an RCD. | ❓ |
| LINEDATA-5-429 | See “Object Reference Qualifier (X'89') Triplet”. | ❓ |
| LINEDATA-5-430 | See “Color Management Resource Descriptor (X'91') Triplet”. | ❓ |
| LINEDATA-5-431 | See “Concatenate Bar Code Data (X'93') Triplet ”. | ❓ |
| LINEDATA-5-432 | rendering intents are ignored. | ❓ |
| LINEDATA-5-433 | 0 | UBIN | Tlength | 10 | Length of the triplet | M | ❓ |
| LINEDATA-5-434 | 1 | CODE | Tid | X'95' | Identifies the Rendering Intent triplet | M | ❓ |
| LINEDATA-5-435 | 2–3 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-436 | 4–6 |  |  | X'FFFFFF' | Not used | M | ❓ |
| LINEDATA-5-437 | 7 | CODE | GOCARI | X'00'–X'03', X'FF' | Rendering intent for AFP GOCA objects | M | ❓ |
| LINEDATA-5-438 | 8–9 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-439 | Note: The XMDs in a Data Map are numbered sequentially, starting with 1. | ❓ |
| LINEDATA-5-440 | Structured Field Introducer:** SF Length (2B), ID = X'D3A68E', Flags (1B), Reserved X'0000' | ❓ |
| LINEDATA-5-441 | 0 | CODE | ElmType | X'00'–X'03' | Element Type | M | ❓ |
| LINEDATA-5-442 | 1–3 | BITS | XMDFlgs |  | XMD flags | M | ❓ |
| LINEDATA-5-443 | 4 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-444 | 5–6 | UBIN/SBIN | IPos |  | Inline position | M | ❓ |
| LINEDATA-5-445 | 7–8 | UBIN/SBIN | BPos |  | Baseline position | M | ❓ |
| LINEDATA-5-446 | 9–12 | CODE | TxtOrent |  | Text (I,B) Orientation | M | ❓ |
| LINEDATA-5-447 | 13 | CODE | FntLID |  | Primary font local ID | M | ❓ |
| LINEDATA-5-448 | 14–15 | UBIN | FLDxmd |  | Field XMD Pointer | M | ❓ |
| LINEDATA-5-449 | 16–17 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-450 | 18–25 | CHAR | SupName |  | Suppression token name | M | ❓ |
| LINEDATA-5-451 | 26 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-452 | 27–30 | UBIN | DataStrt |  | Data start position | M | ❓ |
| LINEDATA-5-453 | 31–32 | UBIN | DataLgth |  | Data length | M | ❓ |
| LINEDATA-5-454 | 33–34 | UBIN | CONDxmd |  | Conditional Processing XMD Pointer | M | ❓ |
| LINEDATA-5-455 | 35 | CODE | SubpgID |  | Subpage ID (Always X'00' for XMDs) | M | ❓ |
| LINEDATA-5-456 | 36–37 | CODE | CCPID |  | CCP Identifier | M | ❓ |
| LINEDATA-5-457 | 38–39 | UBIN | Pgno |  | Starting page number | M | ❓ |
| LINEDATA-5-458 | 40–41 | UBIN | ESpac |  | End Space | M | ❓ |
| LINEDATA-5-459 | 42 | CODE | Align |  | Field Alignment | M | ❓ |
| LINEDATA-5-460 | 43–44 | CODE | FldDelim |  | Field Delimiter | M | ❓ |
| LINEDATA-5-461 | 45–46 | UBIN | Fldno |  | Field Number | M | ❓ |
| LINEDATA-5-462 | 47–48 | UBIN | AdBIncr |  | Additional baseline increment | M | ❓ |
| LINEDATA-5-463 | 49–61 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-464 | 62–n |  | Triplets |  | Triplets | O | ❓ |
| LINEDATA-5-465 | XMD Semantics for triplet applicability. O | ❓ |
| LINEDATA-5-466 | Element XMDs, which define the processing of the data content of the XML element or define a default page | ❓ |
| LINEDATA-5-467 | Field XMDs, which define the processing for an input field or specify constant text or graphics. | ❓ |
| LINEDATA-5-468 | Attribute XMDs, which define the processing for attributes specified in an XML start tag. Attribute XMDs are a | ❓ |
| LINEDATA-5-469 | Conditional Processing XMDs, which specify the Conditional Processing associated with an input element. | ❓ |
| LINEDATA-5-470 | nor the Conditional Processing XMD bits are on in the XMDFlgs byte. | ❓ |
| LINEDATA-5-471 | program. Whenever the same Data Map is re-invoked, this saved group header | ❓ |
| LINEDATA-5-472 | 1. The attribute value is the quoted string not containing the quotation mark | ❓ |
| LINEDATA-5-473 | 2. The color for the data presented by this XMD is always the color specified | ❓ |
| LINEDATA-5-474 | The following restriction applies to relative inline positioning: | ❓ |
| LINEDATA-5-475 | The text orientation of an XMD that specifies relative inline positioning must | ❓ |
| LINEDATA-5-476 | This function is ignored on Body Element XMDs. | ❓ |
| LINEDATA-5-477 | See “Record Descriptor (RCD)”. | ❓ |
| LINEDATA-5-478 | Descriptor (RCD); note that page eject processing for RCD and XMD is identical . | ❓ |
| LINEDATA-5-479 | See “Fully Qualified Name (X'02') Triplet”. | ❓ |
| LINEDATA-5-480 | See “Extended Resource Local Identifier (X'22') Triplet”. | ❓ |
| LINEDATA-5-481 | See “Color Specification (X'4E') Triplet”. | ❓ |
| LINEDATA-5-482 | by this triplet may be at different offsets in the LND, RCD, and XMD. | ❓ |
| LINEDATA-5-483 | See “Resource Object Include (X'6C') Triplet”. | ❓ |
| LINEDATA-5-484 | See “Additional Bar Code Parameters (X'7B') Triplet”. | ❓ |
| LINEDATA-5-485 | See “Graphics Descriptor (X'7E') Triplet”. | ❓ |
| LINEDATA-5-486 | (X'50') triplet of the BDM structured field. | ❓ |
| LINEDATA-5-487 | 0 | UBIN | Tlength | 5–254 | Length of the triplet | M | ❓ |
| LINEDATA-5-488 | 1 | CODE | Tid | X'8A' | Identifies the XML Name triplet | M | ❓ |
| LINEDATA-5-489 | 2–3 |  |  | 0 | Reserved | M | ❓ |
| LINEDATA-5-490 | 4–n | CHAR | XMLName |  | Name of Start Tag or Attribute in XML data | M | ❓ |
| LINEDATA-5-491 | This XMLName is used to build Qualified Tags when used on Element XMDs. | ❓ |
| LINEDATA-5-492 | See “Color Management Resource Descriptor (X'91') Triplet”. | ❓ |
| LINEDATA-5-493 | See “Concatenate Bar Code Data (X'93') Triplet ”. | ❓ |
| LINEDATA-5-494 | See “Rendering Intent (X'95') Triplet”. | ❓ |
| LINEDATA-A-001 | An asterisk indicates optional structured fields or objects. Those not marked are required. | ❓ |
| LINEDATA-A-002 | Other All other symbols are explained in the figures. | ❓ |
| LINEDATA-A-003 | [ End Print File (EPF, D3A9A5) ] | ❓ |
| LINEDATA-A-004 | (EDT, D3A9A8) End Document | ❓ |
| LINEDATA-A-005 | 1. The BPF/EPF structured fields are optional as a pair; if one is specified, the other must be specified as well. | ❓ |
| LINEDATA-A-006 | 2. The mixed line-page documents and composed documents can occur in any order following the inline resource group. | ❓ |
| LINEDATA-A-007 | 3. Each AFP (MO:DCA) document may optionally be preceded by a single document index that is implicitly tied to the document and that indexes the document. For the formal definition of the MO:DCA document index see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. | ❓ |
| LINEDATA-A-008 | 4. An AFP (MO:DCA) document may contain Link Logical Element (LLE) structured fields following the BDT and may also group presentation pages into named page groups. MO:DCA page groups may in turn contain Tag Logical Element (TLE) structured fields following BNG. These structures do not affect the presentation of the document. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. | ❓ |
| LINEDATA-A-009 | 5. If a Medium Map is included internal (inline) to the document, it is activated by immediately following it with an IMM that explicitly invokes it, otherwise the internal Medium Map is ignored. An IMM that does not follow an internal Medium Map may not invoke an internal Medium Map elsewhere in the document and is assumed to reference a Medium Map in the current Form Definition. | ❓ |
| LINEDATA-A-010 | (EDT, D3A9A8) End Document | ❓ |
| LINEDATA-A-011 | Note:** The No Operation (NOP) structured field may appear anywhere in a mixed document and thus is not listed in the structured field groupings. | ❓ |
| LINEDATA-A-012 | (EPG, D3A9AF) End Page | ❓ |
| LINEDATA-A-013 | 1. An AFP (MO:DCA) presentation page can contain one or more Tag Logical Element (TLE) or Link Logical Element (LLE) structured fields following the AEG. These structures do not affect the presentation of the page. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. | ❓ |
| LINEDATA-A-014 | 2. **Note †:** required for every IPO specified in a page. | ❓ |
| LINEDATA-A-015 | + [ (IOB, D3AFC3) Include Object (S) ] | ❓ |
| LINEDATA-A-016 | Note:** These items can appear in any order. | ❓ |
| LINEDATA-A-017 | (EPT, D3A99B) End Presentation Text Object | ❓ |
| LINEDATA-A-018 | Note:** A Presentation Text Descriptor is required in an Active Environment Group when a text object is used in a page. | ❓ |
| LINEDATA-A-019 | (EII, D3A97B) End Image Object IM | ❓ |
| LINEDATA-A-020 | (EIM, D3A9FB) End Image Object IO | ❓ |
| LINEDATA-A-021 | Note †:** allowed in FS45 only. | ❓ |
| LINEDATA-A-022 | (EGR, D3A9BB) End Graphics Object | ❓ |
| LINEDATA-A-023 | (EBC, D3A9EB) End Bar Code Object | ❓ |
| LINEDATA-A-024 | (EPS, D3A95F) End Page Segment | ❓ |
| LINEDATA-A-025 | Note:** This is the structure of an AFP page segment. This structure is supported but is replaced strategically with the MO:DCA page segment. For more information, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. | ❓ |
| LINEDATA-A-026 | (EMO, D3A9DF) End Overlay | ❓ |
| LINEDATA-A-027 | 1. An AFP (MO:DCA) overlay object may contain one or more Tag Logical Element (TLE) or Link Logical Element (LLE) structured fields following the AEG. These structures do not affect the presentation of the overlay. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. | ❓ |
| LINEDATA-A-028 | 2. The MPG and MPO structured fields are not supported in the AEG for an overlay. | ❓ |
| LINEDATA-A-029 | (EFM, D3A9CD) End Form Map | ❓ |
| LINEDATA-A-030 | Note †:** The structured field is required in either the Document Environment Group or the Medium Map. | ❓ |
| LINEDATA-A-031 | (EPM, D3A9CB) End Page Map | ❓ |
| LINEDATA-A-032 | 1. The Data Map Transmission Subcase may contain RCDs or XMDs instead of LNDs. | ❓ |
| LINEDATA-A-033 | 2. The Data Maps in a Page Definition must all contain LNDs, RCDs, or XMDs. A mixture is not allowed. (+ indicates these can appear more than once but not in a mixture). | ❓ |
| LINEDATA-A-034 | 3. A Presentation Text Descriptor (PTD) is required in the AEG when a presentation text object is used on a page. | ❓ |
| LINEDATA-A-035 | 4. **Note †:** required for every IPO specified in a page. | ❓ |
| LINEDATA-B-001 | BPT D3A89B Begin Presentation Text Object | ❓ |
| LINEDATA-B-002 | FDS D3AAEC Fixed Data Size | ❓ |
| LINEDATA-B-003 | MMO D3B1DF Map Medium Overlay | ❓ |
| LINEDATA-B-004 | XMD D3A68E XML Descriptor | ❓ |
| LINEDATA-B-005 | D3A8AD BNG Begin Named Page Group | ❓ |
| LINEDATA-B-006 | D3A9CC EMM End Medium Map | ❓ |
| LINEDATA-B-007 | D3B1AF PGP-2 Page Position (Format 2) | ❓ |
| LINEDATA-B-008 | D3EEFB IPD Image Picture Data IO | ❓ |
| LINEDATA-B-009 | TRN DA(DB) Transparent Data | ❓ |
| LINEDATA-B-010 | USC 76(77) Underscore | ❓ |
| LINEDATA-B-011 | F8(F9) NOP No Operation | ❓ |
| LINEDATA-B-012 | entirely coincidental. | ❓ |
| LINEDATA-B-013 | Mixed Object Document Content Architecture (MO:DCA); | ❓ |
| LINEDATA-B-014 | Intelligent Printer Data Stream (IPDS) | ❓ |
| LINEDATA-B-015 | AFP Line Data Architecture | ❓ |
| LINEDATA-B-016 | Bar Code Object Content Architecture (BCOCA) | ❓ |
| LINEDATA-B-017 | Color Management Object Content Architecture | ❓ |
| LINEDATA-B-018 | Font Object Content Architecture (FOCA) | ❓ |
| LINEDATA-B-019 | Graphics Object Content Architecture for AFP (AFP | ❓ |
| LINEDATA-B-020 | Image Object Content Architecture (IOCA) | ❓ |
| LINEDATA-B-021 | Metadata Object Content Architecture (MOCA) | ❓ |
| LINEDATA-B-022 | Presentation Text Object Content Architecture (PTOCA) | ❓ |
| LINEDATA-B-023 | file, in an ASCII file format defined by Adobe ® Systems | ❓ |
| LINEDATA-B-024 | array • bar code symbology | ❓ |
| LINEDATA-B-025 | bar height • B extent | ❓ |
| LINEDATA-B-026 | character-box reference edges • character shape | ❓ |
| LINEDATA-B-027 | character shape presentation • coded font | ❓ |
| LINEDATA-B-028 | coded font local identifier • color of medium | ❓ |
| LINEDATA-B-029 | color palette • control sequence length | ❓ |
| LINEDATA-B-030 | control sequence prefix • current inline presentation coordinate (I c) | ❓ |
| LINEDATA-B-031 | A TrueType/OpenType font, an optional code page, and | ❓ |
| LINEDATA-B-032 | A TrueType/OpenType collection, either an index value | ❓ |
| LINEDATA-B-033 | Used to prepare for the presentation of a data object; | ❓ |
| LINEDATA-B-034 | Included in a page or overlay via the Include Data Object | ❓ |
| LINEDATA-B-035 | Invoked from within a data object; examples | ❓ |
| LINEDATA-B-036 | current inline print coordinate (i c) • DBCS | ❓ |
| LINEDATA-B-037 | Print file (highest level) | ❓ |
| LINEDATA-B-038 | Document | ❓ |
| LINEDATA-B-039 | Page group | ❓ |
| LINEDATA-B-040 | Data object (lowest level) | ❓ |
| LINEDATA-B-041 | 300 dpi, there are 90,000 dots or bits of electronic data | ❓ |
| LINEDATA-B-042 | discrete code • drawing order | ❓ |
| LINEDATA-B-043 | drawing order coordinate space (DOCS) • European Article Numbering (EAN) | ❓ |
| LINEDATA-B-044 | exception • font character set | ❓ |
| LINEDATA-B-045 | For fixed-pitch, uniform character increment fonts: the | ❓ |
| LINEDATA-B-046 | For PSM fonts: the width of the space character | ❓ |
| LINEDATA-B-047 | For typographic, proportionally spaced fonts: one-third of | ❓ |
| LINEDATA-B-048 | font control record • foreground color | ❓ |
| LINEDATA-B-049 | Wherever the color attribute of P PFO is either color of | ❓ |
| LINEDATA-B-050 | With other overlapping color values, the intersection | ❓ |
| LINEDATA-B-051 | Wherever the color attribute of P e is either the color of | ❓ |
| LINEDATA-B-052 | Wherever the color attribute of P e is not the color of | ❓ |
| LINEDATA-B-053 | Coded Character Set Identifier (CCSID). | ❓ |
| LINEDATA-B-054 | Coded Graphic Character Set Global Identifier | ❓ |
| LINEDATA-B-055 | Code Page Global ID (CPGID) | ❓ |
| LINEDATA-B-056 | Font Typeface Global Identifier (FGID) | ❓ |
| LINEDATA-B-057 | Global Resource Identifier (GRID) | ❓ |
| LINEDATA-B-058 | Graphic Character Global Identifier (GCGID) | ❓ |
| LINEDATA-B-059 | Graphic Character Set Global Identifier (GCSGID) | ❓ |
| LINEDATA-B-060 | Graphic Character UCS Identifier (GCUID) | ❓ |
| LINEDATA-B-061 | An identifier used by a data object to reference a | ❓ |
| LINEDATA-B-062 | In MO:DCA, an encoded graphic character string that | ❓ |
| LINEDATA-B-063 | Object identifier (OID) | ❓ |
| LINEDATA-B-064 | A Uniform Resource Locator (URL), as defined in RFC | ❓ |
| LINEDATA-B-065 | 1. GCSGID of a minimum set of graphic characters | ❓ |
| LINEDATA-B-066 | 2. CPGID of the associated code page | ❓ |
| LINEDATA-B-067 | 3. FGID of the associated font character set | ❓ |
| LINEDATA-B-068 | 4. Font width in 1440ths of an inch. | ❓ |
| LINEDATA-B-069 | GIF • Graphic Character Set Global Identifier (GCSGID) | ❓ |
| LINEDATA-B-070 | Graphic Character UCS Identifier (GCUID) • hierarchy | ❓ |
| LINEDATA-B-071 | For fixed-pitch, uniform character increment fonts: the | ❓ |
| LINEDATA-B-072 | For PSM fonts: the width of the space character | ❓ |
| LINEDATA-B-073 | For typographic fonts and proportionally spaced fonts: | ❓ |
| LINEDATA-B-074 | highlight color • ICC | ❓ |
| LINEDATA-B-075 | ICC-absolute colorimetric • indexed object | ❓ |
| LINEDATA-B-076 | information density • International Organization for Standardization (ISO) | ❓ |
| LINEDATA-B-077 | interoperability • landscape | ❓ |
| LINEDATA-B-078 | 1 logical unit = 1/1440 inch | ❓ |
| LINEDATA-B-079 | 1 logical unit = 1/240 inch | ❓ |
| LINEDATA-B-080 | 1 L unit = 1/1440 inch | ❓ |
| LINEDATA-B-081 | 1 L unit = 1/240 inch | ❓ |
| LINEDATA-B-082 | medium preprinted form overlay (M-PFO) • monospaced font | ❓ |
| LINEDATA-B-083 | move order • object identifier (OID) | ❓ |
| LINEDATA-B-084 | posture • process element | ❓ |
| LINEDATA-B-085 | Profile Connection Space (PCS) • rearranged file | ❓ |
| LINEDATA-B-086 | record-format line data • resource caching | ❓ |
| LINEDATA-B-087 | Fields or values that have been used by a product in a | ❓ |
| LINEDATA-B-088 | Fields or values that have been removed from an | ❓ |
| LINEDATA-B-089 | section identifier • signed integers | ❓ |
| LINEDATA-B-090 | simplex printing • subsetting tower | ❓ |
| LINEDATA-B-091 | Document-processing applications can obtain resolution- | ❓ |
| LINEDATA-B-092 | Device-service applications can obtain device-specific | ❓ |
| LINEDATA-B-093 | substrate • text object | ❓ |
| LINEDATA-B-094 | 1440 twips in one inch. | ❓ |
| LINEDATA-B-095 | text object space • typographic font | ❓ |
| LINEDATA-B-096 | user printable area (UPA) • ward | ❓ |
| LINEDATA-B-097 | weight class • Y p extent | ❓ |
| LINEDATA-B-098 | Yxy color space • Yxy color space | ❓ |
| LINEDATA-B-099 | Fixed Data Text (FDX). . .. . . .. . .. . . .. . .. . . .. . .. . . .. . . .. . .. . . .. . .. . . .. . .87 | ❓ |
| LINEDATA-B-100 | relative inline. . . .. . .. . . .. . . .. . .. . . .. . .. . . .. . .. . . .. . . .. . .. . . .. . .. . . .. . .37 | ❓ |
| LINEDATA-B-101 | XMD Graphics Descriptor . . .. . . .. . .. . . .. . .. . . .. . . .. . .. . . .. . .. . . .. 164 | ❓ |
| LINEDATA-B-102 | XML Name (X'8A') triplet .. . . .. . .. . . .. . . .. . .. . . .. . .. . . .. . .. . . .. . . .. . . 165 | ❓ |
