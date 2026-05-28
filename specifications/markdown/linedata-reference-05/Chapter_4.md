# Chapter 4. Mixed Documents: Adding MO:DCA Structured Fields to Line Data

Chapter 3, “Using a Page Definition to Print Data” describes how Page Definitions can be used to format traditional application line data without the need to make any application programming changes. Under certain circumstances, however, functions are needed that can only be accomplished by changing the application. These functions can be invoked by using one of a small set of MO:DCA structured fields, any of which can be intermixed with line data to obtain specific results. A document of this type, in which structured fields are intermixed with line data, is called a mixed document.

**Note:** MO:DCA structured fields cannot be combined with XML data.

MO:DCA structured fields cannot be interspersed with line data records indiscriminately. Data object structured fields and resource structured fields can appear only within their respective objects and resources, and only in the sequence allowed by the architecture. [LINEDATA-4-001] For example, the Map Coded Font (MCF) structured field is part of the Active Environment Group that in turn can appear in a presentation page, an overlay, or a Page Definition. However, it is not permitted to include an MCF between line-mode data records in an output file or to bracket line-mode records with Begin Page and End Page structured fields. Refer to “Page Definition Structure” for the structure of the Page Definition object and refer to the Mixed Object Document Content Architecture (MO:DCA) Reference for the structure of other data and resource objects.

This chapter discusses how data and resource objects can be intermixed with line data and provides examples of structured fields that can be included individually with line-data records. These structured fields are:
*   Invoke Data Map [LINEDATA-4-002]
*   Invoke Medium Map [LINEDATA-4-003]
*   Include Page Segment [LINEDATA-4-004]
*   Include Page Overlay [LINEDATA-4-005]
*   Include Object [LINEDATA-4-006]
*   Presentation Text [LINEDATA-4-007]

**Note:** The No Operation (NOP) structured field may appear anywhere in a mixed document and thus is not listed in the structured field groupings.

This chapter contains coding examples for some of these structured fields. Chapter 5, “Structured Fields in a Page Definition and in Line Data” contains additional information on the format of these structured fields. See the Mixed Object Document Content Architecture (MO:DCA) Reference for the formal definition of all MO:DCA structured fields.

The presence of structured fields in line data does not change the fact that the Page Definition is the controlling resource that determines how the data appears on the page. Structured fields other than those that change Data Maps or Medium Maps do not affect the placement of line-data records, nor do they affect the text orientation or font selection used to print line-data records. These characteristics of line-data records are defined in the Page Definition. Only when the application generates fully composed documents is a Page Definition not used.

## X'5A' Carriage Control Character

When printing in a z/OS system environment, if MO:DCA structured fields are used either in a fully composed document or intermixed with line data, each MO:DCA structured field must be prefixed with a X'5A' character. The X'5A' appears in the first byte position and provides a signal to a presentation services program that the record is a structured field, not a data record.

The X'5A' character precedes the MO:DCA structured field and is not formally part of the structured field, so it is not counted in the structured field length value that immediately follows it. The examples in this chapter all contain a X'5A' character in the first byte position.

In a z/OS system environment, each MO:DCA structured field must occupy one record. The requirement to prefix MO:DCA structured fields with X'5A' means that all other records in the data set must begin with a carriage control character, even if it is only a “print-and-space-one-line” carriage control. Either ANSI or machine code carriage control can be used for these records.

In an AIX environment, the carriage control character is optional. The New Line control, also called Linefeed (X'25' in EBCDIC, X'0A' in ASCII), is used to determine end-of-record in AIX. The use of the Linefeed carriage control to determine end-of-record allows variable-length records to be easily created in AIX environments. [LINEDATA-4-008]

## Print File Structure

An AFP print file consists of an optional inline resource group followed by one or more documents. Each document may, in turn, be preceded by an optional document index. All resources in an inline resource group must precede all other data in the print file. The group of resources is delimited by the Begin Resource Group (BRG) and End Resource Group (ERG) structured fields. Each resource object in the group is delimited by the Begin Resource (BRS) and End Resource (ERS) structured fields. If multiple fully composed documents are present in the print file, they must be delimited by Begin Document (BDT) and End Document (EDT) structured fields. Note that mixed line-page documents and composed documents can occur in any order following the inline resource group. Figure 15 shows the structure of an AFP print file. [LINEDATA-4-009]

**Figure 15. Structure of a Print File**

*   **Begin Print File (BPF)** \*
*   [**Inline Resource Group**] \*
    *   **Begin Resource Group (BRG)**
    *   [**Resource Object**] (S)
        *   **Begin Resource (BRS)**
        *   **Resource Object Content** (e.g., Overlay, Page Segment, Form Definition)
        *   **End Resource (ERS)**
    *   **End Resource Group (ERG)**
*   **Document** (S)
    *   [**Document Index**] \*
    *   **Begin Document (BDT)**
    *   [**Resource Environment Group**] \* (S)
    *   [**Internal Medium Map**] \* (S)
    *   **Mixed Line-Page Documents** OR **Composed Documents** (In any order)
        *   [**Invoke Medium Map (IMM)**] \* (S)
        *   [**Presentation Page**] \* (S)
        *   [**Include Page (IPG)**] \* (S)
    *   **End Document (EDT)**
*   **End Print File (EPF)** \*

**Legend:**
*   \* = optional [LINEDATA-4-010]
*   (S) = can appear more than once

**Notes:**
1.  The BPF/EPF structured fields are optional as a pair; if one is specified, the other must be specified as well. [LINEDATA-4-011]
2.  The mixed line-page documents and composed documents can occur in any order following the inline resource group. [LINEDATA-4-012]
3.  Each AFP (MO:DCA) document may optionally be preceded by a single document index that is implicitly tied to the document and that indexes the document. For the formal definition of the MO:DCA document index, see the Mixed Object Document Content Architecture (MO:DCA) Reference. [LINEDATA-4-013]
4.  An AFP (MO:DCA) document may contain Link Logical Element (LLE) structured fields following the BDT. It may also group presentation pages into named page groups. MO:DCA page groups may in turn contain Tag Logical Element (TLE) structured fields following the BNG. These structures do not affect the presentation of the document. For the formal definition of these structures, see the Mixed Object Document Content Architecture (MO:DCA) Reference. [LINEDATA-4-014]
5.  If a Medium Map is included internal (inline) to the document, it is activated by immediately following it with an IMM that explicitly invokes it, otherwise the internal Medium Map is ignored. An IMM that does not follow an internal Medium Map may not invoke an internal Medium Map elsewhere in the document and is assumed to reference a Medium Map in the current Form Definition. [LINEDATA-4-015] [LINEDATA-4-016]

The objects that comprise an AFP print file are as follows:

*   **Inline Resource Group**: Contains one or more resource objects to be associated with printing this file. See “Inline Resource Group Structure” for a detailed description of the structure of the resource group and the objects it can contain.
    *   **Note:** In the MO:DCA architecture, these resource groups are called external resource groups because they occur outside a document.
    *   The Inline Resource Group is an optional component of the Print File. If no Inline Resource Group is defined, the resources stored in the AFP resource library of the system are used. (In MVS/ESA™ with USERLIB support, resources might be stored in private libraries that are used at print time for individual data sets. Up to eight private libraries may be used with a single data set. The libraries are named in the USERLIB parameter of the OUTPUT JCL statement.)
    *   The scope of an inline resource group is the print file. Once the last document in the print file has been processed, the resources in the resource group are no longer available to the presentation system for use with another print file.
*   **Documents**: The print file may contain one or more documents to be printed. These may be fully composed-page documents, line-mode documents, or mixed-mode documents, in any order. If multiple composed-page documents appear, each one must be delimited by a BDT and an EDT structured field. For the complete definitions of document structure, see Appendix A, “Document and Resource Object Diagrams”. [LINEDATA-4-017]

## Finishing Operations for a Print File

A Form Definition may be used to specify finishing operations to be applied to the documents in a print file. The scope of the finishing operations as well as the type of operation is specified with a Medium Finishing Control (MFC) structured field in the Document Environment Group (DEG) of the Form Definition. For a definition of the finishing operations and parameters that may be specified, see the Mixed Object Document Content Architecture (MO:DCA) Reference. The following rules specify how the scope of the finishing operations applies to a print file when the file contains line-data and mixed-data documents, with or without BDT/EDT, as well as composed documents.

*   If the MFC specifies print-file level finishing, all media in the print file is collected for finishing in a print-file level media collection and the finishing operations are applied to the complete collection; that is, the complete print file. [LINEDATA-4-018]
*   If the MFC specifies document-level finishing and selects all documents, the print file is processed as a set of documents as follows: [LINEDATA-4-019]
    *   Any document bounded by BDT/EDT is processed as a single document regardless of whether the data between BDT/EDT is line data, mixed data, or composed data.
    *   Line data and mixed data that is not bounded explicitly by BDT/EDT is processed as an implied document with implied BDT/EDT. When such data follows the resource group or an EDT, a BDT is implied and the implied document lasts until a BDT is encountered or until the end of the print file is reached. In either case, the implied document is terminated with an implied EDT.
    *   The media in each document, whether implied or explicit, is collected for finishing in a document-level media collection and the finishing operations are applied to each collection, that is each document, individually. Note that, in this case, the same finishing operations are applied to each document.
*   If the MFC specifies document-level finishing and selects a single document, the print file is processed as a set of documents in the same manner as when all documents are selected. [LINEDATA-4-020] The offset of the selected document is calculated by counting all documents, whether implied or explicit, and the selected document might itself be an implied document. [LINEDATA-4-021] The media in the selected document are collected for finishing and the finishing operations are applied to the single collection; that is, the single document. If the same document is selected multiple times, finishing operations are applied in the order specified. Note that, using this type of MFC, unique finishing operations may be specified for each document in the print file. [LINEDATA-4-022]

## Inline Resource Group Structure

A resource group begins with the Begin Resource Group (BRG) structured field and ends with the End Resource Group (ERG) structured field. Inline resources are included in the inline resource group and can be referred to by name within the print file. They override objects of the same name stored in resource libraries accessed by the print server. Each individual resource begins with the Begin Resource (BRS) structured field and ends with the End Resource (ERS) structured field. When a resource object is stored in a library, the BRG, BRS, ERG, and ERS structured fields are not present. When using AFP with a z/OS system, all structured fields of resource objects included in inline resource groups must be preceded by the X'5A' character. Figure 16 shows the structure of an inline resource group. [LINEDATA-4-023]

**Figure 16. Structure of an Inline Resource Group**

*   **Begin Resource Group (BRG)**
*   [**Resource Object**] (S)
    *   **Begin Resource (BRS)**
    *   **Resource Content**
    *   **End Resource (ERS)**
*   **End Resource Group (ERG)**

The structured fields and objects in an inline resource group are as follows. (Chapter 5, “Structured Fields in a Page Definition and in Line Data” describes the structured fields.)

*   **BRG (Begin Resource Group)**: Begins an inline resource group in the Print File.
*   **BRS (Begin Resource)**: Begins a resource object, specifies the resource type, and specifies the name used to select the object for printing.
*   **Resource Object**: A resource object can be one of the following:
    *   A page segment [LINEDATA-4-024]
    *   An overlay [LINEDATA-4-025]
    *   A data object [LINEDATA-4-026]
    *   An object container [LINEDATA-4-027]
    *   A document [LINEDATA-4-028]
    *   A Form Definition [LINEDATA-4-029]
    *   A Page Definition [LINEDATA-4-030]
    *   A font object (a code page, a font character set, or a coded font) [LINEDATA-4-031]
    *   **Note:** See the description of the BRS structured field in the MO:DCA Reference for the hexadecimal codes identifying each type.
*   **ERS (End Resource)**: Ends the resource object. Any name specified in the ERS must match the name specified in the BRS.
*   **ERG (End Resource Group)**: Ends the inline resource group. Any name specified in the ERG must match the name specified in the BRG.

**Note:** Not all presentation services programs support all resource objects in a Resource Group.

### Programming Considerations for Inline Resources

Because most resource objects consist of variable-length records, any print file that includes these resources inline must be in variable-length-record format and must use data records beginning with a carriage control byte.

## Invoke Data Map

The Invoke Data Map (IDM) structured field selects a new Data Map for printing line data and ends the current line-format page.

**Note:** When using machine carriage control characters, care must be taken to prevent a blank page from being printed at the start of a document. If the application inserts IDM structured fields following records that have a “skip to channel nn immediate” carriage control (X'8B') without making an exception for the start of the document, a blank page will be generated. When the first line data record contains a skip immediate carriage control, a line-format page is started even though there is no data to be printed. When the IDM follows the initial skip immediate carriage control at the start of the document to be printed, the IDM ends the current page, causing a blank page to be printed. When the skip immediate carriage control is used later in the document to end the page and it is followed by the IDM structured field, a blank page does not occur since the skip immediate carriage control has already ended the current line-format page.

*   For traditional line data, processing begins with the first Line Descriptor (LND) structured field of the invoked Data Map for the next line-format page. [LINEDATA-4-032]
*   For record-format line data, processing begins with the first Record Descriptor (RCD) structured field that matches the record ID of the first record processed following the IDM. [LINEDATA-4-033]

The IDM structured field can be used to change formatting based on some change in the application data, such as the start of output for a different department or branch office.

The IDM structured field always contains sixteen bytes of information. The Data Map name in the data portion of this structured field must be eight bytes long. If the name of the actual Data Map to be invoked is shorter than eight bytes, trailing blanks must be added. [LINEDATA-4-034]

### Sample IDM Structured Field

The Invoke Data Map structured field shown in Figure 17 causes a presentation services program to select Data Map SUMMARY.

The IDM is 16 (X'10') bytes long and has the structured field identifier X'D3ABCA'. In the example, the flag byte is set to X'00' and bytes 6 and 7 contain a sequence number of X'0000'. It is not necessary to number MO:DCA structured fields sequentially or even to place a meaningful value in the sequence number field. However, for some errors detected by presentation services programs, the sequence number of the structured field in error is printed as part of the error information in the PIMSG data set. This field is reserved in MO:DCA data streams and should be set to zero.

When a presentation services program processes the IDM structured field, the current page is ended. The next record read by the presentation services program begins on a new page and the information contained in Data Map SUMMARY is used to format subsequent data lines. Use of this structured field assumes the currently active Page Definition contains a Data Map with the name SUMMARY in its Begin Data Map structured field. If no such Data Map exists, an error is generated.

**Figure 17. Sample Invoke Data Map Structured Field**

```
X'5A' X'0010' X'D3ABCA' X'00' X'0000' X'E2E4D4D4C1D9E840'
```
[LINEDATA-4-035]

## Invoke Medium Map

The Invoke Medium Map (IMM) structured field is similar to the IDM structured field except that it causes a presentation services program to select a new Medium Map, or Copy Group, in the current Form Definition at the point where the IMM structured field appears in the print file. The presentation services program ends printing on the current sheet when an IMM is encountered. Note that if the Medium Map specifies the N-up function, the IMM might cause the presentation services program to end printing on the current N-up partition instead of on the current sheet.

The IMM structured field can appear in line-mode, mixed-mode, or fully composed documents. For line-mode or mixed-mode data, processing resumes with the first Line Descriptor (LND) structured field in the Data Map that is active for the next line-format page. When the Data Map contains RCDs, processing resumes with the first RCD whose Record ID matches the current data record. The IMM structured field is sixteen bytes long and must be coded as shown below.

The IMM structured field can be written by the application when some physical control of the output is required. By using the IMM, the application can offset pages in the data from the medium origin, select paper from the primary or alternate bin, or change between simplex and duplex printing, simply by selecting a Medium Map that contains the desired function.

The functions provided by the IDM and IMM structured fields are the same as those provided by changing Data Maps and Medium Maps with conditional processing in a Page Definition. It is possible to use conditional processing to make the Data Map and Medium Map change without modifying the application to add the IDM and IMM structured fields.

Note that at the beginning of a new composed document and at the beginning of a new set of line-data records, control for presentation is returned to the first Medium Map in the Form Definition. This is shown in Figure 18.

**Figure 18. Returning Control to First Medium Map in Form Definition**

*   **Form Definition**
    *   Medium Map M1
    *   Medium Map M2
*   **Line data records** (presentation controlled by M1)
*   ...
*   **IMM, Medium Map M2**
*   **Line data records** (presentation controlled by M2)
*   ...
*   **Begin Document (BDT)** (presentation control reverts to M1)
*   Composed Pages
*   ...
*   **IMM, Medium Map M2** (presentation controlled by M2)
*   ...
*   Composed Pages
*   ...
*   **End Document (EDT)**
*   **Line data records** (presentation control reverts to M1)

### Sample IMM Structured Field

The Invoke Medium Map structured field shown in Figure 19 causes the presentation services program to select Medium Map BIN2. (Note that BIN2 contains four trailing blanks to fill out the eight-byte data field.)

When a presentation services program processes this structured field, the current page is ended. The next record read by the presentation services program is placed on a new sheet and the information contained in medium map BIN2 is used. Note that if the Medium Map specifies the N-up function, the next record may be placed on a new partition of the same sheet. If the currently active Form Definition does not contain a Medium Map with that name in the Begin Medium Map structured field, an error is generated.

**Figure 19. Sample Invoke Medium Map Structured Field**

```
X'5A' X'0010' X'D3ABCC' X'00' X'0000' X'C2C9D5F240404040'
```

## Using Structured Fields to Skip to a New Page or Sheet

Chapter 3, “Using a Page Definition to Print Data” described the use of conditional processing in a Page Definition to perform a skip-to-new-page or skip-to-new-sheet operation based on a change in the value of a control field in an application data stream. The conditional processing function was added to the Page Definition to provide another way of producing the same output as by embedding IDM or IMM structured fields in a line-data file to force a new page or sheet. [LINEDATA-4-036]

When an IDM or IMM structured field appears in an application data stream, the presentation services program ends the current page and resumes printing at the start of a new page, using the first Line Descriptor in the current Data Map. When the Data Map contains RCDs, printing resumes at the start of a new page using the first RCD whose Record ID matches the current data record. [LINEDATA-4-037]

The data stream shown in Figure 20 provides the same result as the Page Definition shown in Figure 13 and the data stream shown in Figure 21 provides the same result as the Page Definition shown in Figure 14.

**Figure 20. Using an IDM Structured Field to Skip to a New Page**
```
Line data records (with carriage control)
⋮
X'5A0010D3ABCA000001D5C5E6D7C7404040'
More line data records
⋮
```

**Figure 21. Using an IMM Structured Field to Skip to a New Sheet**
```
Line data records (with carriage control)
⋮
X'5A0010D3ABCC000001D5C5E6C6D4404040'
More line data records
⋮
```

The name of the Data Map invoked in Figure 20 is NEWPG. This is the name on the PAGEFORMAT statement in the PPFA example in Figure 13. Re-invoking the same Data Map causes a skip to a new page. It is not necessary to have multiple Data Maps in the Page Definition to achieve this result. Consequently, standard Page Definitions supplied with the print services software can be used with this method.

The same is true of skipping to a new physical sheet. Figure 21 invokes a Medium Map named NEWFM. Even if NEWFM is the current and only Medium Map in the Form Definition, the presence of this structured field causes a skip to a new sheet of paper, or, in the case of N-up presentation, possibly a skip to a new N-up partition.

## IMM Structured Fields to Insert a Blank Sheet

Occasionally an application requires that a blank sheet appear between groups of output within a single data set. This blank sheet might be selected from different-color paper loaded in the alternate bin, or it might just be another sheet from the primary bin. The blank sheet is generated by using a Form Definition that specifies the constant data function, which allows a sheet to be produced without any variable data on it. To generate the blank sheet, code two consecutive IMM structured fields, as shown in Figure 22. [LINEDATA-4-038]

**Figure 22. Using Two IMM Structured Fields to Force a Blank Sheet**
```
Line data records (with carriage control)
⋮
X'5A0010D3ABCC000000C1D3E3C2C9D54040'
X'5A0010D3ABCC000000D7D9C9C2C9D54040'
More line data records (with carriage control)
⋮
```

This example assumes a Form Definition with two Medium Maps, as could be built using the PPFA code shown in Figure 23. The first Medium Map coded in the example will be used for the initial pages. They will contain user data (the CONSTANT parameter does not appear in this Medium Map) and are printed on paper selected from the primary bin. When the point is reached where a blank sheet is to be inserted, the application writes out an Invoke Medium Map that selects the second Medium Map. This Medium Map selects a sheet of paper from the alternate bin. No user data is placed on the pages coming from the alternate bin, because CONSTANT FRONT and DUPLEX NO are coded. If the output were to be printed in duplex, CONSTANT BOTH and DUPLEX YES can be coded instead.

Immediately following the IMM structured field to select the second Medium Map (ALTBIN) is a second IMM to return to the original Medium Map (PRIBIN) for the next portion of the data. This set of two consecutive IMM structured fields can be included in the output data stream as often as necessary.

**Figure 23. Form Definition With Two IMMs to Force a Blank Sheet**

```
FORMDEF BLANKT
  OFFSET 0 0
  REPLACE YES;
  COPYGROUP PRIBIN
    DUPLEX NO BIN 1;
  COPYGROUP ALTBIN
    CONSTANT FRONT
    DUPLEX NO BIN 2;
```

## Variable-Length and Fixed-Length Records

MO:DCA structured fields are variable in length so their lengths can differ. Line data records intermixed with MO:DCA structured fields might also have different lengths. Fully composed MO:DCA documents may consist of records up to 32K bytes long. However, variable-length data is not always desirable. Programming requirements might make it preferable to use fixed-length records in some circumstances. A presentation services program can process a mixed document of fixed-length records even though some of the records contain structured fields with significant information that is much shorter than the data records to be printed. So long as the information in the length portion of the structured field is correct and the structured field is padded with blanks to the length of the other records in the data set, no errors are generated. The structured fields shown in Figure 24 are all considered valid by the presentation services program in a z/OS system environment. The third form, however, might not be supported in a multi-system environment. [LINEDATA-4-039]

**Figure 24. Three Versions of the Invoke Data Map Structured Field**

1.  **Standard variable-length version:**
    `X'0010' X'D3ABCA' X'00' X'0000' PFORMAT1`
    (16 bytes)

2.  **Version with padding bytes:** [LINEDATA-4-040]
    `X'0050' X'D3ABCA' X'08' X'0000' PFORMAT1`
    (followed by 63 bytes of `X'00'` and one byte of `X'40'`)

3.  **Fixed-length record version:**
    `X'0010' X'D3ABCA' X'00' X'0000' PFORMAT1`
    (followed by 64 bytes of padding to fill an 80-byte record)

The first structured field at the top of Figure 24 is the most common form of Invoke Data Map. The IDM structured field is 16 bytes (X'10') long, so the value X'10' appears in the length field of the introducer. Next is the X'D3ABCA' identifier for Invoke Data Map. The flag byte is zero. The syntax rules for Invoke Data Map indicate that the eight-byte name of the requested Data Map be coded as the data portion of the structured field. This is the rightmost information in the figure.

The second structured field in Figure 24 is 80 bytes long, but here the formal MO:DCA conventions for using padding bytes have been followed. In this example, the flag byte is coded as X'08', which signals that padding bytes appear in the structured field. The padding bytes follow the variable data for the IDM structured field and the final padding byte is coded as X'40' to signal that 64 padding bytes are present. The length field has been changed from 16 (X'10') to 80 (X'50') to reflect the increased length of the structured field.

The third structured field in Figure 24 is identical to the first, except that the actual MO:DCA data appears as the first 16 bytes in an 80-byte record. This format allows the IDM structured field to be included in a data set of fixed-length 80-byte records and no errors would result in a z/OS system environment.

Of course, fixed-length records that are longer than the number of bytes actually used to contain the MO:DCA structured field information will result in a data set that is larger than one containing variable-length records, each one no longer than necessary. This might be a consideration if the resulting data set is to be sent across a network.

## Position and Orientation of Objects

Two coordinate systems are used to position and rotate objects in line data: the page (Xp,Yp) coordinate system and the text (I,B) coordinate system. The page coordinate system is based on the fourth quadrant of a standard Cartesian coordinate system with the origin in the top-left corner, the X axis increasing from left to right, and the Y axis increasing from top to bottom. The text (I,B) coordinate system is defined, relative to the page coordinate system, by the text orientation as follows:

*   **Text Orientation 0°,90°:** Origin at top-left corner, I increases left to right, B increases top to bottom.
*   **Text Orientation 90°,180°:** Origin at top-right corner, I increases top to bottom, B increases right to left.
*   **Text Orientation 180°,270°:** Origin at bottom-right corner, I increases right to left, B increases bottom to top.
*   **Text Orientation 270°,0°:** Origin at bottom-left corner, I increases bottom to top, B increases left to right.

The coordinate system used depends on the object and how it is included in line data. Table 10 summarizes how objects are positioned and rotated in line data. The table also summarizes how objects are positioned and rotated in MO:DCA data that has been transformed from line data, using the Line Data Object Position Migration (X'27') triplet to capture the text orientation that was active when the line data was presented with a Page Definition. More details on how objects are positioned and rotated are given in the sections that follow the table. [LINEDATA-4-041]

**Table 10. Position and Rotation of Objects in Line Data and MO:DCA Data**

| Feature | Objects in Line Data | Objects with X'27' triplet in MO:DCA Data transformed from Line Data |
| :--- | :--- | :--- |
| **Page Segment Origin** | (XpsOset,YpsOset) in IPS specify an offset from the current text coordinate system origin (I=0,B=0). The offset is measured using the current text (I,B) coordinate system. | (XpsOset,YpsOset) in IPS specify an offset from the page origin (Xp=0,Yp=0). The offset is measured using the page (Xp,Yp) coordinate system. The offset was adjusted to include the LND or RCD position. |
| **IM—Image Object Origin** | (XoaOset,YoaOset) in IOC specify an offset from the page segment origin. The offset is measured using the current text (I,B) coordinate system. | (XoaOset,YoaOset) in IOC specify an offset from the page segment origin. The offset is measured using the temporary (X,Y) coordinate system. |
| **IM—Image Object Rotation** | (XoaOrent,YoaOrent) in IOC specify a rotation that is measured with respect to the page (Xp,Yp) coordinate system Xp-axis. | (XoaOrent,YoaOrent) in IOC specify a rotation that is measured with respect to the page (Xp,Yp) coordinate system Xp-axis. |
| **IM—Image Cell Origin** | (XCOset,YCOset) in ICP specify an offset from the image object origin. The offset is measured using the current text (I,B) coordinate system. | (XCOset,YCOset) in ICP specify an offset from the image object origin. The offset is measured using the temporary (X,Y) coordinate system. |
| **OCA Object Origin (OBP Byte 23=X'00')** | (XoaOset,YoaOset) in OBP specify an offset from the page segment origin. The offset is measured using the current text (I,B) coordinate system. | (XoaOset,YoaOset) in OBP specify an offset from the page segment origin. The offset is measured using the temporary (X,Y) coordinate system. |
| **OCA Object Origin (OBP Byte 23=X'01')** | (XoaOset,YoaOset) in OBP specify an offset from the page origin (Xp=0,Yp=0). The offset is measured using the page (Xp,Yp) coordinate system. | (XoaOset,YoaOset) in OBP specify an offset from the page origin (Xp=0,Yp=0). The offset is measured using the page (Xp,Yp) coordinate system. |
| **OCA Object Rotation (OBP Byte 23=X'00')** | (XoaOrent,YoaOrent) in OBP specify a rotation that is measured with respect to the current text (I,B) coordinate system I-axis. | (XoaOrent,YoaOrent) in OBP specify a rotation that is measured with respect to the temporary (X,Y) coordinate system X-axis. |
| **OCA Object Rotation (OBP Byte 23=X'01')** | (XoaOrent,YoaOrent) in OBP specify a rotation that is measured with respect to the page (Xp,Yp) coordinate system Xp-axis. | (XoaOrent,YoaOrent) in OBP specify a rotation that is measured with respect to the page (Xp,Yp) coordinate system Xp-axis. |
| **Stand-alone IM Object Origin** | (XoaOset,YoaOset) in IOC specify an offset from the current LND or RCD position. The offset is measured using the current text (I,B) coordinate system. | (XoaOset,YoaOset) in IOC specify an offset from the temporary coordinate system (X=0,Y=0) origin. The offset is measured using the temporary (X,Y) coordinate system. The offset was adjusted to include the LND or RCD position. |
| **Stand-alone OCA Object Origin (OBP Byte 23=X'00')** | (XoaOset,YoaOset) in OBP specify an offset from current LND or RCD position. The offset is measured using the current text (I,B) coordinate system. | (XoaOset,YoaOset) in OBP specify an offset from the temporary coordinate system (X=0,Y=0) origin. The offset is measured using the temporary (X,Y) coordinate system. The offset was adjusted to include the LND or RCD position. |

[LINEDATA-4-042]

## Positioning With Respect to Current Descriptor

When objects are included in line data, they occur between line-data records and can be positioned with respect to the inline/baseline position specified by the LNDs or RCDs used to process the records. More precisely, an included object can be positioned with respect to the current LND, or current RCD. This is also sometimes referred to as the current line position, which is defined as follows: [LINEDATA-4-043]

*   **Current LND Position:** If the line-data records use ANSI carriage controls, spacing or skipping is performed first and printing of the record is performed last, therefore the current LND is the LND used to process the last record. If the line-data records use machine carriage controls, printing of the record is performed first and spacing or skipping is performed last. In this case, the current LND is the LND that is spaced to or skipped to, that is, it is the LND that will be used to process the next record. Additionally, if the record is processed as a set of fields using a reuse chain, the current LND is the base LND, that is, the LND that is at the head of the reuse chain. If the current LND does not generate a position, the LND used is the last LND that did generate a position.
*   **Current RCD Position:** Because carriage controls are ignored in record-format line data, the current RCD is always the last record RCD that was used to process a data record.

## Include Page Segment

The Include Page Segment (IPS) structured field is used to place a page segment resource anywhere on the page. It contains the full eight-character name of the page segment (with trailing blanks if necessary) and the position of the page segment, often referred to as the page segment origin. The page segment might be mapped in a Map Page Segment (MPS) structured field in the Active Environment Group (AEG) of the current Data Map, in which case the page segment is downloaded to the printer and may be used multiple times. If it is not mapped, the page segment data is loaded as part of the page. [LINEDATA-4-044]

Objects within the page segment might be positioned with respect to the page segment origin. The page segment inherits the Active Environment Group definition of the including page.

AFP print servers initialize the following PTOCA control sequences as shown prior to processing a text object in an AFP page segment:

*   **Set Baseline Increment:** 6 lines per inch
*   **Set Inline Margin:** 0
*   **Set Intercharacter Adjustment:** 0
*   **Set Text Color:** X'FFFF' (printer default color)
*   **Set Text Orientation:** 0°,90°

The initial print position for text in the page segment is the reference point defined on the including page or overlay coordinate system by the IPS, that is, the page segment origin.

### Positioning of Page Segments

Special care must be taken when including page segments in line data to ensure that the objects in the page segment are positioned and oriented properly.

*   **Location of Page Segment Origin:** The page segment origin is located on the page as follows:
    *   If one of the IPS offsets is specified as X'FFFFFF', the page segment origin along that axis is located at the position specified in the current LND or RCD. [LINEDATA-4-045]
    *   If the IPS offset is not X'FFFFFF', the page segment origin is located at the IPS offset measured with respect to the current text coordinate system origin (I=0,B=0), using the current text (I,B) coordinate system. For example, if the text orientation is (90°,180°), the page segment offsets are measured from the top-right corner of the page, with the I-axis running from top to bottom and the B-axis running from right to left. [LINEDATA-4-046]
    *   If the page segment is included with a Resource Object Include (X'6C') triplet on the LND or RCD, the page segment origin is located at the specified offset measured with respect to the position specified in the current LND or RCD, using the current text (I,B) coordinate system. [LINEDATA-4-047]
    *   *Summary:* The origin of a page segment in line data is always positioned using the text (I,B) coordinate system specified in the current LND or RCD.
*   **Position and Orientation of IM Image Objects in a Page Segment:** The image object area offset, as specified in the IOC structured field is measured with respect to the page segment origin, using the text (I,B) coordinate system specified in the current LND or RCD. If the image is celled, the Image Cell Position (ICP) structured field specifies an offset from the image object origin that is measured using the current text (I,B) coordinate system. The rotation of the IM image is specified in the IOC and is measured with respect to the page coordinate system Xp-axis (origin is top-left corner of page). [LINEDATA-4-048]
    *   **Note:** For page segments in MO:DCA data, if the IM image is complex (celled), it is recommended that the rotation be set to (0°,90°). For page segments in mixed data, the rotation should be set to match the current text orientation.
*   **Position and Orientation of Image, Graphics, and Bar Code Objects in a Page Segment:**
    *   If the Object Area Position (OBP) structured field specifies byte 23 (RefCSys) = X'00' (current), the object area offset is measured with respect to the page segment origin, using the text (I,B) coordinate system specified in the current LND or RCD. The object area rotation is measured with respect to the I-axis of the current text (I,B) coordinate system.
    *   If OBP byte 23 = X'01' (page or overlay), the object area offset is measured with respect to the page origin (top-left corner of page) using the page coordinate system. The object area rotation is measured with respect to the page coordinate system Xp-axis (origin is top-left corner of page).
    *   **Note:** When line data that includes an IPS structured field is transformed into a MO:DCA document by an AFP application program, the text orientation that was set when the page segment and its objects were positioned must be captured and retained in order to properly position the page segment on the MO:DCA page. This can be done using a Line Data Object Position Migration (X'27') triplet on the IPS structured field in the MO:DCA document.

### Sample IPS Structured Field

Figure 25 contains a sample IPS structured field. This example places the segment SIGNAT at the current print position.

**Figure 25. Include Page Segment Structured Field**

```
X'5A' X'0016' X'D3AF5F' X'00' X'0000' X'E2C9C7D5C1E34040' X'FFFFFF' X'FFFFFF'
```
[LINEDATA-4-049]

**Programming Tip:** The current line position is unchanged after the page segment is printed. Additional logic might be needed in the application to place subsequent print lines so that they do not overprint the page segment.

## Include Page Overlay

The Include Page Overlay (IPO) structured field functions in a manner similar to Include Page Segment. The IPO structured field specifies the full name of the overlay (any O1 prefix in the overlay name must be included) and the position of the overlay origin. The IPO references an overlay resource that is to be positioned on the page. [LINEDATA-4-050]

The overlay name must appear in the Map Page Overlay structured field of the Active Environment Group of the Data Map currently in effect. The overlay contains its own Active Environment Group definition that specifies the coordinate system for positioning and rotating objects, the size of the overlay, and the names of any fonts used in it. Considerations for the current line position are the same as those discussed in the previous programming tip. The current line position is unchanged after the overlay has been placed.

**Note:** The IBM 3800 printer does not support the IPO function.

### Positioning Overlays

Because overlays define their own coordinate system and environment, the rules for positioning an overlay and its objects are somewhat different from those for positioning a page segment and its objects.

*   **Location of Overlay Origin:** The overlay origin is located as follows:
    *   If the IPO offset along either the page Xp-axis or the page Yp-axis is specified as X'FFFFFF', the overlay origin along that same axis is located by translating the current LND or RCD (I,B) position to an offset along that Xp or Yp axis. [LINEDATA-4-051]
    *   If the IPO offset is not X'FFFFFF', the overlay origin is positioned at the specified (Xp,Yp) offset measured with respect to the page origin (top-left corner of page), using the page coordinate system. [LINEDATA-4-052]
    *   If the overlay is included with a Resource Object Include (X'6C') triplet on the LND or RCD, the overlay origin is located at the specified offset measured with respect to the position specified in the current LND or RCD, using the current text (I,B) coordinate system. [LINEDATA-4-053]
*   **Orientation of Overlay:** If the overlay is included either with an IPO or with a Resource Object Include (X'6C') triplet on the LND or RCD, the overlay rotation may be specified as 0°, 90°, 180°, or 270°, and is measured with respect to the page coordinate system Xp axis (origin is top-left corner of page). However, the 90°, 180°, and 270° rotations of a page overlay are not supported in all AFP environments. Consult the product documentation to see which rotations are supported. Note that the MO:DCA IS/1 and IS/2 interchange sets only support 0° rotation of a page overlay.
*   **Position and Orientation of IM Image Object in an Overlay:** The image object area offset, as specified in the IOC structured field, is measured using the overlay coordinate system (origin is top-left corner of overlay). The rotation of the IM image is specified in the IOC and is measured with respect to the overlay coordinate system X-axis (origin is top-left corner of overlay). [LINEDATA-4-054]
    *   **Note:** If the IM image is complex (celled), AFP print servers require the rotation set to 0°,90°.
*   **Position and Orientation of IO Image, Graphics, and Bar Code Objects in an Overlay:** If the Object Area Position (OBP) structured field specifies byte 23 (RefCSys) = X'00' (current) or X'01' (page or overlay), the object area offset is measured with respect to the overlay origin (top-left corner of overlay) using the overlay coordinate system. The rotation of the OCA object is specified and measured using the overlay coordinate system X-axis (origin is top-left corner of overlay).

### Sample IPO Structured Field

A sample IPO structured field appears in Figure 26. It places overlay O1SIGNAT at the current print position on the page.

**Figure 26. Include Page Overlay Structured Field**

```
X'5A' X'0016' X'D3AFD8' X'00' X'0000' X'D6F1E2C9C7D5C1E3' X'FFFFFF' X'FFFFFF'
```
[LINEDATA-4-055]

## Include Object

The Include Object (IOB) structured field references an object that is to be positioned on the page. In general, the IOB may be used to include two classes of objects:

*   **OCA objects** (BCOCA, GOCA, IOCA, PTOCA) that specify an Object Environment Group (OEG) or MO:DCA page segments that contain such objects. [LINEDATA-4-056]
*   **Non-OCA paginated presentation objects**, such as TIFF images, that are supported by the presentation system. [LINEDATA-4-057]

The current AFP support for the IOB in line data is limited to the first class, OCA objects. When referencing an OCA object, the IOB may be used to override position, size, orientation, mapping, and default color parameters that are specified in the OEG. When referencing a non-OCA object, the IOB is used to specify the position, size, orientation, and mapping parameters for the object.

The RefCSys parameter in the IOB is used to select the coordinate system for positioning and rotating the object area into which the object is mapped: [LINEDATA-4-058]

*   **X'00':** The object area offset in the IOB is measured with respect to the current LND or RCD position, using the current text (I,B) coordinate system. The object area rotation in the IOB is measured with respect to the current text (I,B) coordinate system I-axis.
*   **X'01':** The object area offset in the IOB is measured with respect to the page origin (Xp=0,Yp=0), using the page (Xp,Yp) coordinate system. The object area rotation in the IOB is measured with respect to page (Xp,Yp) coordinate system Xp-axis.

## Including Data Objects Directly in Line Data

Previously it was described how complete AFP resources can be included in the resource group of a print file rather than having to be stored in an external resource library. This is one approach that can be used with applications where many different resources must be included in the print stream and where it might not be feasible to store these resources externally to the application. However, another approach is possible for applications that require large numbers of graphics, images, or bar codes.

Including them directly in the line data can eliminate the problem of devising unique names for thousands of objects that change each time the program is run. Graphics, images, and bar codes included with other print data in this manner are not true inline resources, because they do not follow the rules for inline resources described previously.

### Including Bar Code, Graphics, IO Image, and Presentation Text Objects with OEG

Objects that include an Object Environment Group (BCOCA, GOCA, IOCA, and PTOCA objects) can be included directly in a mixed-mode document intermixed with line data so long as the following rules are observed:

*   The reference coordinate system (byte 23 of the data field of the OBP structured field) must be coded to provide the desired position and rotation of the object on the page: [LINEDATA-4-059]
    *   **If OBP byte 23 (RefCSys) = X'00' (current),** the object area offset is measured with respect to the position specified in the current LND or RCD, using the current text (I,B) coordinate system. The object area rotation is measured with respect to the I-axis of the current text (I,B) coordinate system.
    *   **If OBP byte 23 (RefCSys) = X'01' (page or overlay),** the object area offset is measured with respect to the page origin, using the page coordinate system (origin is top-left corner of page). The object area rotation is specified in the OBP and is measured with respect to the page coordinate system Xp-axis (origin is top-left corner of page).
*   If the image or graphic has been built as a page segment, delete the Begin Page Segment and End Page Segment structured fields from the object. The remaining structured fields can be placed in the print stream at the point where the image or graphic should appear. [LINEDATA-4-060] [LINEDATA-4-061]

### Including IM Image Objects

Page segments containing IM image data do not have an Object Environment Group, so somewhat different considerations apply to them. Between the BPS and EPS structured fields are the records that provide positioning information for the bits that define the image and the actual bits themselves in uncompressed form. Just as for BCOCA, GOCA, IOCA, and PTOCA objects, the positioning information contained in the IOC structured field should be coded to provide the desired placement of the image. Bytes 0 through 5 in the IOC specify the image object area origin for IM images. The offset is measured with respect to the I,B position specified in the current LND or RCD, using the current text (I,B) coordinate system. The image object area offset should be coded as X'000000000000' to position the image at the current LND or RCD. If the image is celled, the Image Cell Position (ICP) structured field specifies an offset from the image object origin that is measured using the current text (I,B) coordinate system.

The rotation of the IM image is specified in the IOC and is measured with respect to the page coordinate system Xp-axis (origin is top-left corner of page).

**Note:** For page segments in MO:DCA data, if the IM image is complex (celled), it is recommended that the rotation be set to (0°,90°). For page segments in mixed data, the rotation should be set to match the current text orientation.

The Begin Page Segment (X'D3A85F') and End Page Segment (X'D3A95F') structured fields should be deleted. The remaining structured fields can then be placed in the print stream at the point where the image is to appear.

### Including Standalone Presentation Text (PTX) Structured Fields

The Presentation Text (PTX) structured field is used to specify text data and the position, rotation, and fonts to be used when presenting text data. PTX structured fields are made up of control sequences and data. PTX is probably the most frequently used structured field in fully composed MO:DCA documents. PTX structured fields can be intermixed with line data records so long as a few rules are followed:

*   Each PTX structured field should be coded as a self-contained environment. While PTX control sequences can be used to set the line spacing, page margin, data position, font, etc., these settings remain in effect only for the current PTX structured field. Processing of follow-on line data records or structured fields might change the settings. If a line data record follows a PTX, settings such as its placement and font is determined by the information in the current LND or RCD of the active Page Definition. A PTX can affect the printing of line-data records if it contains text control sequences that change inter-character and inter-word spacing, because these characteristics are not controlled by a Page Definition. If another PTX structured field follows the PTX, the text environment established by the last-used LND or RCD is re-issued before the new PTX is processed. [LINEDATA-4-062] Some presentation systems that convert the mixed-mode data to MO:DCA might also place Begin Presentation Text (BPT) and End Presentation Text (EPT) structured fields around each embedded PTX. Subsequent processing of the BPT will cause initial text conditions to be set prior to the processing of the PTX.
*   Because the presentation services software considers line-data files to be mapped totally with a Page Definition, the presentation services software generates IPDS commands containing positioning and font information for every record in the file. [LINEDATA-4-063] If a record turns out to be a PTX structured field, the information in the PTX is used to create a subsequent IPDS Write Text command. If a large number of PTX structured fields are included in a line-mode data set, the additional IPDS commands generated by the presentation services software could add an unacceptable amount of processing overhead when the data set is printed.
*   Page Definition information, PTX information, and any additional information contained in objects such as bar code and image placed on the page interact, so the programmer must keep careful track of the page position and fonts in effect as records are written. [LINEDATA-4-064] For example, if the text position, text orientation, or font is not defined in a structured field or object, the values specified in the Page Definition for the current line-data record will be used. Depending on the complexity of the application, it might be easier to write fully composed output rather than using a Page Definition to set up the environment. [LINEDATA-4-065]

**Figure 27. Presentation Text Structured Field**

*   **Introducer**
    *   Length (2B)
    *   Identifier (3B) = X'D3EE9B'
    *   Flag byte (1B)
    *   Sequence number (2B)
*   **Data** (Control sequences and/or free-standing text)

#### Record Format When Using PTX Structured Fields

When creating a mixed-mode data set that includes PTX structured fields, it is generally easier to use variable-length records. The PTX structured field length ranges up to 32,759 bytes. Much spool space is wasted if every record is padded out to this length, regardless of whether or not the entire 32K bytes contain valid data.

#### Using the PTX Structured Field

The PTX structured field contains PTOCA data, as defined in the MO:DCA Reference. The general format of the PTX structured field is shown in Figure 27. Either of two types of data can follow the PTX structured field introducer:

*   The X'2BD3' escape sequence, followed by one or more text control sequences [LINEDATA-4-066]
*   “Free-standing text”, which is a series of code points representing data to be printed [LINEDATA-4-067]

The first alternative is by far the most common use of PTX. A table of the control sequences that can be used with the PTX structured field appears in Table 11.

The PTOCA Architecture groups control sequences into function sets or subsets. PT1 is the base subset. PT2 is a superset of PT1. PT3 is a superset of PT2. PT4 is a superset of PT3. See *Advanced Function Presentation Printer Information*, G544-3290 for information on which PTOCA subsets are supported by your printer.

In a PTX structured field, a control sequence immediately follows each X'2BD3' escape sequence. Each control sequence can be coded as unchained (even-numbered functions) or chained (odd-numbered functions). In the chained format, each control sequence immediately follows the previous one with no intervening X'2BD3' escape sequence. The last control sequence in a chain must have the even-numbered (unchained) format to signal the end of the chain.

Each text control sequence is a minimum of two bytes long, where the X'2BD3' escape sequence, if present, is not counted as part of the length. The first byte indicates the length of the entire control sequence, including the length byte itself, the function byte, and any parameter bytes. The second byte contains the odd or even function code for the control sequence. A data field ranging from zero to 253 bytes follows.

One reason why free-standing text is seldom used is that one of the PTX control sequences available is Transparent Data (TRN), which has a string of code points as its data field, and thereby provides the actual text to be printed. Use of the TRN control sequence allows data whose encoding scheme uses the code points X'2B' or X'D3' to be included in a PTX without having these code points interpreted as an escape sequence. [LINEDATA-4-068]

The usual sequences for placing text on a page are as follows:

1.  Specify the beginning print position using Absolute Move Inline (AMI) and Absolute Move Baseline (AMB) control sequences. [LINEDATA-4-069]
2.  Select the coded font to be used with the Set Coded Font Local (SCFL) control sequence. [LINEDATA-4-070]
3.  Specify the code points of the text to be printed using a Transparent Data (TRN) control sequence. [LINEDATA-4-071]

*Example:*
`X'5A001BD3EE9B0000002BD304D300F004C700B403F10106DAC4C1E3C1'`

*   **X'5A'**: carriage control character (z/OS).
*   **X'001B'**: length field (27 bytes).
*   **X'D3EE9B'**: Presentation Text identifier.
*   **X'00'**: flag byte.
*   **X'0000'**: sequence number.
*   **X'2BD3'**: escape sequence.
*   **04D300F0**: Absolute Move Baseline (offset X'00F0' = 1 inch down at 240 LPI).
*   **04C700B4**: Absolute Move Inline (offset X'B4' = 180 units from margin).
*   **03F101**: Set Coded Font Local (font ID 1).
*   **06DAC4C1E3C1**: Transparent Data ("DATA", ends chaining).

**Programming Tip:** It is good programming practice to build as long a PTX structured field as possible. Text control sequences should be chained wherever possible. Within a fully composed document, the last control sequence in any text object must always indicate end of chaining. [LINEDATA-4-072]

#### Use of Fonts

Either fixed-pitch or proportionally spaced fonts can be used. Positioning of the first character in a TRN control sequence can be accomplished by preceding the TRN with move text controls. If no move control sequences follow in the same PTX, data in subsequent TRN controls will be placed immediately following the text in the preceding TRN.

**Table 11. Control Sequences Used in PTX Structured Field**

| PTOCA Control Sequence | Function | Unchained (Even) | Chained (Odd) |
| :--- | :--- | :---: | :---: |
| **PT1 Control Sequences** | | | |
| Absolute Move Baseline | AMB | 04D2 | 04D3 |
| Absolute Move Inline | AMI | 04C6 | 04C7 |
| Begin Line | BLN | 02D8 | 02D9 |
| Begin Suppression | BSU | 03F2 | 03F3 |
| Draw Baseline Rule | DBR | 07E6 | 07E7 |
| Draw Inline Rule | DIR | 07E4 | 07E5 |
| End Suppression | ESU | 03F4 | 03F5 |
| No Operation | NOP | xxF8 | xxF9 |
| Relative Move Baseline | RMB | 04D4 | 04D5 |
| Relative Move Inline | RMI | 04C8 | 04C9 |
| Repeat String | RPS | xxEE | xxEF |
| Set Baseline Increment | SBI | 04D0 | 04D1 |
| Set Coded Font Local | SCFL | 03F0 | 03F1 |
| Set Intercharacter Increment | SIA | 04C2 | 04C3 |
| Set Inline Margin | SIM | 04C0 | 04C1 |
| Set Text Color | STC | 0574 | 0575 |
| Set Text Orientation | STO | 06F6 | 06F7 |
| Set Variable Space Character Increment | SVI | 04C4 | 04C5 |
| Transparent Data | TRN | xxDA | xxDB |
| **PT2 Control Sequences** | | | |
| Overstrike | OVS | 0572 | 0573 |
| Temporary Baseline Move | TBM | xx78 | xx79 |
| Underscore | USC | 0376 | 0377 |
| **PT3 Control Sequences** | | | |
| Set Extended Text Color | SEC | xx80 | xx81 [LINEDATA-4-073] |
| **PT4 Control Sequences** | | | |
| Glyph Advance Run | GAR | xx8C | xx8D |
| Glyph ID Run | GIR | xx8B | |
| Glyph Layout Control | GLC | xx6D | |
| Glyph Offset Run | GOR | xx8E | xx8F |
| Unicode Complex Text | UCT | 106A | |

Right-justification and centering of text cannot be done simply by using PTX control sequences. Calculations must be done in the program.

#### Boxes and Rules

The Draw Baseline Rule and Draw Inline Rule control sequences may be used to draw rules and boxes. The length and thickness of the rule must be specified in the control sequence in units of measure specified in the PTD. [LINEDATA-4-074]

*   **Direction:** If the rule is to be drawn in the positive direction (top to bottom or left to right), use the positive number. If the rule is to be drawn in the opposite direction, the value must be coded in two's complement form.
*   **Two's Complement Calculation:** Invert each bit and add one to the low-order bit position. For example, a one-inch rule at 240 LPI is X'F0' units. The reverse direction is X'FF10'.
*   **Thickness:** For inline rules, a positive thickness adds pels top to bottom. For baseline rules, a positive thickness adds pels to the right. Expressing thickness as a two's complement number reverses this.

Figure 28 illustrates a text-control sequence to draw a box one inch high by two inches wide. The rules are four pels thick. [LINEDATA-4-075]

**Figure 28. Text Controls to Draw a Box**

```
… 04C7000F 04D300F0 07E501E0000400 07E700F0000400 …
  < AMI >  < AMB >  <Inline Rule>  <Baseline Rule>
                    (bottom side)     (left side)

… 04C901E0 04D500F0 07E5FE20FFFC00 07E6FF10FFFC00
  < RMI >  < RMB >  <Inline Rule>  <Baseline Rule>
                     (top side)      (right side)
```

## Composed Documents

If the positioning and formatting needed for each page of your application output is not known in advance, then generating fully-composed documents might be preferable to line-mode or mixed-mode data. [LINEDATA-4-076]

Examples of applications using fully-composed output:
*   Utility bills with graphical energy use representations. [LINEDATA-4-077]
*   Insurance policies with client-specific clauses. [LINEDATA-4-078]
*   Financial statements with varied account descriptions and boxes/shading.

**Note:** In composed documents, PTX structured fields must be bracketed by BPT and EPT structured fields. These are allowed only in fully composed documents. [LINEDATA-4-079]

### Overall Document Structure

A fully composed document will conform to the structure shown on the right side of Figure 30. Each document is composed of one or more pages. Each page must begin with an Active Environment Group (AEG), followed by presentation objects in any order.

### Document Indexing

Indexing and attribute tagging structured fields permit selective retrieval of pages and page groups:
*   Begin Document Index [LINEDATA-4-080]
*   Index Element [LINEDATA-4-081]
*   Tag Logical Element [LINEDATA-4-082]
*   End Document Index [LINEDATA-4-083]
*   Begin Named Page Group [LINEDATA-4-084]
*   End Named Page Group [LINEDATA-4-085]

In AFP environments, the document index is located external to the document. BNG, ENG, and TLE structured fields are not supported for indexing in a line-data or mixed-data environment.

### Document Links

Fully composed MO:DCA documents may contain logical links between document components (e.g., hypertext links). These are specified using Link Logical Element (LLE) structured fields. LLE structured fields are not supported in line-data or mixed-data documents. [LINEDATA-4-086]
