# Chapter 2. Line Data and MO:DCA (AFP) Data

The Advanced Function Presentation (AFP) products have been developed to be consistent with a set of architectures that define the format of documents and the nature of the commands sent from the host software to the supported printers. The Mixed Object Document Content Architecture (MO:DCA) defines a device-independent data stream format for interchanging documents among AFP products. Data to be printed can include text, graphics, images, and bar codes.

The objects used for Advanced Function Presentation include:
*   **Font objects**, which consist of font character sets containing the patterns for letters, numbers, and special characters, and code pages that associate a hexadecimal value with each character in the font character set [LINEDATA-2-001]
*   **Resource objects**, including overlays and page segments, which in turn can include text, graphics, image, and bar code [LINEDATA-2-002]
*   **Print control objects**, which include Page Definitions used to format line data and Form Definitions used to control physical aspects of the print environment. [LINEDATA-2-003]

These objects can exist in resource libraries external to the data to be printed or can be included inline with the data that will use them.

AFP objects can be obtained in a number of different ways. For example, a wide variety of fonts are available from IBM and other sources, and Page Definitions, Form Definitions, and overlays can be built using any of several tools available from IBM and other AFP vendors.

MO:DCA documents can be generated using a variety of AFP products. In line data environments, users can add MO:DCA structured fields directly to their line data. Chapter 4, “Mixed Documents: Adding MO:DCA Structured Fields to Line Data” provides information on how to do this.

Presentation services programs, available on IBM mainframe systems, such as z/OS, z/VM, and z/VSE, IBM i (previously known as OS/400 ®), IBM OS/2, and on AIX, Linux, and Windows systems, accept MO:DCA documents and resources and in turn generate Intelligent Printer Data Stream (IPDS) commands to drive the printers. Presentation services programs can also accept other forms of data as input. One of the most widely used of these is called line data. [LINEDATA-2-004]

## Line Data

Line data, meaning application output to be printed that is not already in MO:DCA format, is supported by presentation services programs and formatted by Page Definition resource objects in all system environments except IBM OS/2. The nature of line data is slightly different in the system environments where it appears. [LINEDATA-2-005]

## IBM Mainframe Environments

IBM mainframe applications written in programming languages such as Assembler, COBOL, FORTRAN, PL/I, RPG, or others have historically produced output files to be printed on line-mode printers such as the IBM 1403, 3211, or 3800-1. These line data files consist of individual print records, each of which corresponds to one line of data on an impact printer. The application program either formats line data records or leaves them unformatted.

Formatted line data records contain information exactly as it will be printed, because line printers have little or no capability of formatting print output records. Unformatted line data records contain only the fields of data to be printed. With unformatted line data records, the data is not formatted into lines, columns, paragraphs or other structures that determine how the records will appear on paper. AFP print server products support printing of both formatted and unformatted line data records using the Page Definition (also called PageDef) resource object. [LINEDATA-2-006]

**Figure 2. Formatted and Unformatted Line Data Records** [LINEDATA-2-007] [LINEDATA-2-008] [LINEDATA-2-009]

Traditional impact printers allowed only one variation on the line-by-line format of output. The first character of the line, preceding the actual data characters, could optionally be a carriage control byte. This byte indicated whether the data line should be printed using single, double, or triple spacing, whether spacing should be suppressed (creating an overstrike), or whether the line should be placed at a predefined position on the page that was associated with a value of 1 through 12, known as the channel code. This page position value was defined in an auxiliary object called a forms control buffer (FCB). The FCB defined the number of lines per page, whether lines were spaced at 6 or 8 per inch, and which line, if any, was associated with the position values of 1 through 12. The IBM 3800 Model 1 added a spacing value of 12 lines per inch to the FCB; and the IBM 3800 Model 3 added 10 lines per inch. [LINEDATA-2-010]

The carriage control character could be represented in either of two coding schemes:
*   **American National Standards Institute (ANSI) carriage control** is a standard representation used with printers from many different vendors. Table 5 lists the ANSI codes and their functions. [LINEDATA-2-011]
*   **Machine code control characters** were defined by IBM. They correspond to the channel command words issued by the operating system to accomplish the desired function. Table 6 lists the IBM machine code values and their functions. [LINEDATA-2-012]

ANSI and machine codes may not be intermixed within a single data set.

Line spacing is handled differently by ANSI and machine code carriage controls. ANSI conventions cause spacing to take place first, followed by printing the line, while with machine codes, the line is printed first, and then the spacing action is performed.

Note that if a spacing control action moves the print position past the last line of the current page, processing continues at the first print position of a new page. That is, the spacing action is not carried over to the new page.

### Table 5. ANSI Carriage Control Characters

| Control Character Value (in hexadecimal) | Function |
| :--- | :--- |
| X'40' (blank) | Space 1 line, then print (single spacing) |
| X'F0' (zero) | Space 2 lines, then print (double spacing) |
| X'60' (dash) | Space 3 lines, then print (triple spacing) |
| X'4E' (plus sign) | Suppress spacing, then print (overstrike previous line) |
| X'F1' | Print the data at line position defined as Channel 1 (by convention, the first line on a new page) |
| X'F2' | Print the data at the line position defined as Channel 2 in the FCB |
| X'F3' | Print the data at the line position defined as Channel 3 in the FCB |
| X'F4' | Print the data at the line position defined as Channel 4 in the FCB |
| X'F5' | Print the data at the line position defined as Channel 5 in the FCB |
| X'F6' | Print the data at the line position defined as Channel 6 in the FCB |
| X'F7' | Print the data at the line position defined as Channel 7 in the FCB |
| X'F8' | Print the data at the line position defined as Channel 8 in the FCB |
| X'F9' | Print the data at the line position defined as Channel 9 in the FCB |
| X'C1' | Print the data at the line position defined as Channel 10 in the FCB |
| X'C2' | Print the data at the line position defined as Channel 11 in the FCB |
| X'C3' | Print the data at the line position defined as Channel 12 in the FCB |

**Note:** When ANSI carriage controls are used, only the values that appear in this table are considered valid by presentation services programs, which treat any other ANSI carriage control value as invalid and print any data on the line using single spacing.

### Table 6. Machine Code Control Characters

| Control Character Value (in hexadecimal) | Function |
| :--- | :--- |
| X'03' | No operation |
| X'09' | Print and space 1 line (single spacing) |
| X'11' | Print and space 2 lines (double spacing) |
| X'19' | Print and space 3 lines (triple spacing) |
| X'01' | Print without spacing (overstrike next line) |
| X'89' | Print the data, then skip to the line position defined as Channel 1 (by convention, the first line on a new page) |
| X'91' | Print the data, then skip to the line position defined as Channel 2 |
| X'99' | Print the data, then skip to the line position defined as Channel 3 |
| X'A1' | Print the data, then skip to the line position defined as Channel 4 |
| X'A9' | Print the data, then skip to the line position defined as Channel 5 |
| X'B1' | Print the data, then skip to the line position defined as Channel 6 [LINEDATA-2-013] |
| X'B9' | Print the data, then skip to the line position defined as Channel 7 |
| X'C1' | Print the data, then skip to the line position defined as Channel 8 |
| X'C9' | Print the data, then skip to the line position defined as Channel 9 |
| X'D1' | Print the data, then skip to the line position defined as Channel 10 |
| X'D9' | Print the data, then skip to the line position defined as Channel 11 |
| X'E1' | Print the data, then skip to the line position defined as Channel 12 |
| X'0B' | Space 1 line without printing |
| X'13' | Space 2 lines without printing |
| X'1B' | Space 3 lines without printing |
| X'8B' | Skip to Channel 1 immediate (by convention, the first line on a new page) |
| X'93' | Skip to the Channel 2 FCB position immediate |
| X'9B' | Skip to the Channel 3 FCB position immediate |
| X'A3' | Skip to the Channel 4 FCB position immediate |
| X'AB' | Skip to the Channel 5 FCB position immediate |
| X'B3' | Skip to the Channel 6 FCB position immediate |
| X'BB' | Skip to the Channel 7 FCB position immediate |
| X'C3' | Skip to the Channel 8 FCB position immediate |
| X'CB' | Skip to the Channel 9 FCB position immediate |
| X'D3' | Skip to the Channel 10 FCB position immediate |
| X'DB' | Skip to the Channel 11 FCB position immediate |
| X'E3' | Skip to the Channel 12 FCB position immediate |

**Note:** Presentation services programs ignore the following hexadecimal machine-code carriage control characters and do not print lines containing them: X'02' through X'07', X'0A', X'12', X'23', X'43', X'63', X'6B', X'73', X'7B', X'EB', X'F3', and X'FB'. Presentation services programs treat any other carriage control value as invalid and print any data on the line using single spacing.

One other modification to printer line data was introduced with the IBM 3800 Model 1. This modification allows an additional byte to appear at the beginning of a line to indicate which one of up to four different character arrangement tables loaded in the printer is used to print the line. This byte, the table reference character (TRC), contains a value of X'F0', X'F1', X'F2', or X'F3', corresponding to the relative position of the desired character arrangement table in the list of table names specified in the data set's job control language. If carriage control bytes are used with the data, the table reference character follows the carriage control byte but precedes the data bytes. If carriage control is not used, the table reference character is the first byte of the data record. As with carriage control, if table reference characters are used, every data record must contain a TRC byte. More information on table reference characters can be found in the application programming guides for IBM Print Services Facility™ (PSF) products.

## AIX, Linux, and Windows Environments

Data in an AIX, Linux, or Windows environment can be in stream format, with each record or line to be printed delimited by a line separator; or it can be in record-based format. In record-based format, the line separator is not required, as the length of each record is contained in a two-byte prefix to the record. Either of these formats is considered line data and can be mapped for printing by presentation services programs if a Page Definition is used.

Note: The line separator is described in Line Data Summary. [LINEDATA-2-014]

## IBM i Environment

In IBM i (previously known as OS/400) print environments, line data and mixed data is written to the system spool using a Printer File. This file may also reference the Page Definition and Form Definition to be used for formatting and printing the data. [LINEDATA-2-015]

## IBM OS/2 Environment

There is no known support on IBM OS/2 systems for Page Definitions. A presentation services program in the IBM OS/2 environment can accept data in a MO:DCA format or it can print IPDS data streams sent from another AFP system.

### Table 7. Platform Support of Data formats

| Platform | Record-based | Stream |
| :--- | :---: | :---: |
| IBM mainframe | X | X (Note 1) |
| AIX, Linux, and Windows | X (Note 2) | X |
| IBM i | X | X (Note 1) |

**Notes:**
1.  Only supported when input data is XML. [LINEDATA-2-016]
2.  Only supported when the length of each record is contained in a two byte prefix to the record or when each record is the same size. [LINEDATA-2-017]

## Line Data Summary

To print line data, presentation services programs must know the dimensions of the page, the exact position on that page where each record must be printed, and the fonts to be used. This information is provided for line data records in an AFP resource object called the Page Definition (PageDef). The Page Definition is described in Chapter 3, “Using a Page Definition to Print Data”.

Figure 3 and Figure 4 summarize the valid forms of line data.

Note: In Figure 3 and Figure 4, the stream formats are terminated with a line separator. A line separator is normally a Line Feed character or a combined Carriage Return character and Line Feed character pair. Windows platforms typically use the Carriage Return and Line Feed pair as the line separator. The line separator code points vary based on the data encoding and platform. The supported line separators are:
*   **EBCDIC data**: Line Feed (X'25'). [LINEDATA-2-018]
*   **ASCII and UTF-8 data**: Line Feed (X'0A') or Carriage Return (X'0D') and Line Feed (X'0A') pair. [LINEDATA-2-019]
*   **UTF-16BE**: Line Feed (X'000A') or Carriage Return (X'000D') and Line Feed (X'000A') pair. [LINEDATA-2-020]
*   **UTF-16LE**: Line Feed (X'0A00') or Carriage Return (X'0D00') and Line Feed (X'0A00') pair. Note that when the input data is UTF-16LE (little-endian byte order), the program that processes the line data needs to convert the data to big-endian byte order. Big-endian byte order is the only byte order supported in AFP environments. [LINEDATA-2-021]

**Figure 3. Valid Line Data Records** [LINEDATA-2-022] [LINEDATA-2-023]

*   `D A T A`: Simple data line
*   `CC D A T A`: Data line with carriage control byte
*   `TRC D A T A`: Data line with table reference character
*   `CC TRC D A T A`: Data line with carriage control byte and table reference character
*   `D A T A LS`: Data line in stream format with line separator
*   `CC D A T A LS`: Data line in stream format with carriage control byte and line separator
*   `TRC D A T A LS`: Data line in stream format with table reference character and line separator
*   `CC TRC D A T A LS`: Data line in stream format with carriage control byte, table reference character, and line separator
*   `BOM D A T A`: Unicode data line with Byte Order Mark
*   `CC BOM D A T A`: Unicode data line with carriage control byte and Byte Order Mark
*   `TRC BOM D A T A`: Unicode data line with table reference character and Byte Order Mark
*   `CC TRC BOM D A T A`: Unicode data line with carriage control byte, table reference character, Byte Order Mark, and line separator
*   `BOM D A T A LS`: Unicode data line in stream format with Byte Order Mark and line separator
*   `CC BOM D A T A LS`: Unicode data line in stream format with carriage control byte, Byte Order Mark, and line separator
*   `TRC BOM D A T A LS`: Unicode data line in stream format with table reference character, Byte Order Mark, and line separator
*   `CC TRC BOM D A T A LS`: Unicode data line in stream format with carriage control byte, table reference character, Byte Order Mark, and line separator

**Note:** For a description of the BOM (Byte Order Mark) see “Unicode Line Data”. The BOM is allowed only on the first record and applies to all records in the print file.

## Record-Format Line Data

Another form of line data that is supported by presentation services programs and formatted by a Page Definition resource is record-format line data. With this format, each line data record contains a 1 to 250-byte record identifier, which selects the Record Descriptor (RCD) in a record-format Data Map that is used to format the line data. A carriage control (CC) byte is optional but is ignored if specified; table reference characters (TRCs) are not supported. Many functions used in the Line Descriptor (LND) to format traditional line data are also used in the RCD to format record-format line data. Others, such as header and trailer processing, are unique to RCDs. [LINEDATA-2-024]

**Figure 4. Valid Record-Format Line Data**

*   `Record ID D A T A`: Record format line data
*   `CC Record ID D A T A`: Record format line data with carriage control byte
*   `Record ID D A T A LS`: Record format line data in stream format with line separator
*   `CC Record ID D A T A LS`: Record format line data in stream format with carriage control byte and line separator
*   `BOM Record ID D A T A`: Unicode Record format line data with Byte Order Mark
*   `CC BOM Record ID D A T A`: Unicode Record format line data with carriage control byte and Byte Order Mark
*   `BOM Record ID D A T A LS`: Unicode Record format line data in stream format with Byte Order Mark and line separator
*   `CC BOM Record ID D A T A LS`: Unicode Record format line data in stream format with carriage control byte, Byte Order Mark, and line separator

## Unicode Line Data

The data portion of the valid line data formats shown in Figure 3 and in Figure 4 can be encoded using Unicode Standard encodings UTF-16 or UTF-8. The Unicode Standard recommends that a byte order mark (BOM) be the first sequence of bytes in the data. This is to accommodate platforms, such as Windows, that use the little-endian byte order. It also serves as a signature to identify Unicode text. The Byte Order Marks supported are:
*   **UTF-8**: X'EFBBBF' [LINEDATA-2-025]
*   **UTF-16BE (big-endian byte order)**: X'FEFF' [LINEDATA-2-026]
*   **UTF-16LE (little-endian byte order)**: X'FFFE' [LINEDATA-2-027]

The Byte Order Mark is optional. If used, the BOM is only on the first line or record of line data in the print file. It is recommended that switching the encoding in the page definition be avoided. If the font selected indicates the data is UTF-16 and the BOM is not used, big-endian byte order is assumed. When the BOM is not used, switching the encoding in the page definition should not pose any problems.

Unicode encoding is subject to these restrictions in an AFP environment:
*   Shift-out/Shift-in (SOSI) controls are not used in Unicode to signify a shift into and out-of DBCS processing. Therefore, it is not possible to switch processing between Unicode encoding and single-byte (SBCS) encoding within a line data field or record using SOSI as described in “Processing Line Data with Shift-Out/ Shift-In (SOSI) Controls”. That is, when a line data field is processed with a Page Definition, either the whole field is treated as Unicode-encoded, or none of it is treated as Unicode-encoded. [LINEDATA-2-028]
*   If the Byte Order Mark used in UTF-16 data indicates the data is in little-endian byte order, programs that process the UTF-16 data will need to convert little-endian to big-endian byte order. [LINEDATA-2-029]
*   If carriage control bytes (CC) or table reference character bytes (TRC) are used with UTF-16 encoded data, the CC and TRC bytes remain 1 byte fields and are not encoded in UTF-16. [LINEDATA-2-030] [LINEDATA-2-031]

## XML Data

XML data may be formatted using a Page Definition resource, however this is subject to the following restrictions:
*   Carriage Control (CC) and Table Reference Characters (TRC) are not supported. [LINEDATA-2-032]
*   The data is encoded using one of the following: [LINEDATA-2-033]
    *   EBCDIC (Single-byte only)
    *   ASCII (Single-byte only)
    *   UTF-8
    *   UTF-16 (including surrogates; see Unicode Line Data for information about byte order)

**Application Note:** When using FOCA fonts (fonts mapped with an MCF in the AEG) to process XML data, the following can occur:
*   If the data is encoded in ASCII or UTF-8 and the font specifies an encoding scheme of Unicode Presentation, the processor of the XML data may convert the data to UTF-16BE.
*   If the data is encoded in UTF-16 and the font specifies an encoding scheme of PC Data SBCS, the processor of the XML data may convert the data to ASCII. This conversion might result in unprintable code points.

*   MO:DCA data cannot be mixed with XML data. [LINEDATA-2-034]

For a description of XML Data, refer to the XML specification, Extensible Markup Language (XML) 1.0, which can be found at the World Wide Web Consortium web site, http://www.w3.org/. [LINEDATA-2-035]

## MO:DCA Data Summary

In contrast to line data, fully composed page data, or MO:DCA (AFP) data, contains control information such as position, orientation, and font selection intermixed with the data to be printed. Presentation services programs accept a MO:DCA document and generate the corresponding IPDS commands needed to drive a printer. An external Page Definition resource is not needed with MO:DCA data because all the formatting information is already included in the document.

MO:DCA data is fully described in the Mixed Object Document Content Architecture (MO:DCA) Reference. [LINEDATA-2-036]

## Combining Line Data with MO:DCA Structured Fields

It is possible to intermix line data records and MO:DCA structured fields in a single file and send the mixed data to a presentation services program for printing. This permits the addition of image, graphics, and bar code objects to existing line data output. Applications can be written to generate line data or other objects as needed to produce the desired final print product.

Note: MO:DCA structured fields cannot be combined with XML data.

Line data and MO:DCA records cannot be mixed haphazardly. Chapter 4, “Mixed Documents: Adding MO:DCA Structured Fields to Line Data” provides guidelines on the valid combinations. [LINEDATA-2-037]

## The Function of the Page Definition

Any print file that contains line data, whether alone or in combination with MO:DCA structured fields, requires a Page Definition for printing using presentation services programs. The Page Definition is necessary to establish the environment for each page and to position each line of print.

A number of Page Definitions mapping common page layouts are provided with some AFP software products and some AFP products allow users to create their own Page Definitions. [LINEDATA-2-038]
