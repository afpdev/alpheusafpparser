# Chapter 3. Using a Page Definition to Print Data

One of the major enhancements provided by AFP to existing line-data applications is the ability to print application-generated output in different formats without making any application changes. This function is called outboard formatting, and is provided by the Page Definition resource object.

Page Definitions are supported by presentation services programs for z/OS, z/VM, z/VSE, AIX, Linux, Windows, and iSeries. A presentation services program uses Page Definitions to map the line data produced by application programs. Page Definitions are not used when printing fully composed MO:DCA documents, as formatting information is already included internally in these documents.

Page Definition names are provided in job control statements. Any print file can be associated with a Page Definition by using the appropriate parameters in the job control statements for the applicable operating system. See the reference publications for your print server and operating system for complete information. [LINEDATA-3-001]

## Common Examples of Page Definition Use

Many users want to take advantage of AFP capabilities that provide multiple-up printing or rotated printing without any need to change the application that generates the output. Line data can be printed in any desired format by creating a Page Definition that describes that format. Presentation services program software includes many Page Definitions that address common user needs, such as printing two-up output on 8.5 by 11 inch paper.

One example of multiple-up printing is provided by IBM-supplied Page Definition W12883. This Page Definition prints two pages of 64 lines each, side by side in landscape mode on letter-sized paper. The data is printed at 8.2 lines per inch, so a 15-pitch or smaller font must be used to prevent the lines from overlapping. [LINEDATA-3-002]

Another example applies to users of continuous-forms impact printers who install a cut-sheet laser printer and begin to use letter-size sheets of paper, rather than the larger forms found on impact printers. The impact printers always printed in the ACROSS direction on paper whose width exceeded its length. But ACROSS printing on cut-sheet paper is generally considered to mean printing parallel to the narrow edge, not the wide edge. A Page Definition that prints in the DOWN direction, or in the landscape orientation, can be used to get the same result with letter-size paper on a cut-sheet printer as with larger forms on an impact printer. [LINEDATA-3-003]

## Using More than One Page Definition

When a line-data file (such as a SYSOUT file produced by a System/370 application program) is printed, only one Page Definition can be used to map the output format of that file. However, multiple copies of the file can be printed, each one using a different Page Definition, if the appropriate job control statements are used. The actual syntax varies depending on the operating system. An example for z/OS is shown in Figure 5. By using a job stream similar to the one shown in the figure, multiple copies of a line-mode data set can be generated, each one in a different format.

This example produces three different collated copies of the entire output file, each one formatted using a different Page Definition. The same approach can be used with Form Definitions. Each OUTPUT statement includes a different Form Definition name to invoke various options such as overlays, paper source, simplex, or duplex.

Although only one Page Definition and Form Definition can be used when printing a single file, the internal structure of Page Definitions and Form Definitions includes multiple sets of formatting rules. These sets of rules are called Data Maps (also called Page Formats) in the Page Definition and Medium Maps (also called copy groups) in the Form Definition. The Invoke Data Map and Invoke Medium Map structured fields can be written in the output by an application program and used to switch between maps as printing proceeds. This makes it possible for subsets of the file to be presented in different formats. Examples will be provided later in this chapter and in Chapter 4, “Mixed Documents: Adding MO:DCA Structured Fields to Line Data”. [LINEDATA-3-004]

When a Page Definition containing more than one Data Map, or a Form Definition containing more than one Medium Map, is used, the one that appears physically first in the resource object is selected as the default.

**Figure 5. Printing a Data Set in z/OS Multiple Times with Different Page Definitions**

```
//PRINTJOB JOB ...
//STEP1 EXEC PGM=IEBGENER
//OUT1 OUTPUT PAGEDEF=PD1
//OUT2 OUTPUT PAGEDEF=PD2
//OUT3 OUTPUT PAGEDEF=PD3
⋮
//SYSUT2 DD SYSOUT=A,OUTPUT=(*.OUT1,*.OUT2,*.OUT3)
```
[LINEDATA-3-005]

## Page Definition Structure

A Page Definition is required to compose line data into pages for printing on page printers. A Page Definition consists of one or more Data Maps that define the page environment and provide instructions for mapping each line of data to the page.

A Page Definition object can be referenced from a library defined to a presentation services program or can be included inline at the beginning of a print file in some system environments. The structured fields in the Page Definition conform to the MO:DCA architecture rules for structured fields. These rules are summarized in Chapter 5, “Structured Fields in a Page Definition and in Line Data” of this publication and are formally defined in the Mixed Object Document Content Architecture (MO:DCA) Reference. [LINEDATA-3-006]

A Page Definition optionally can contain one or more Conditional Processing Control (CCP) structured fields. Conditional processing permits the application programmer to define tests on selected data fields in the input line records and to specify actions to take when the conditions of the test are satisfied. Figure 6 shows the structure of a Page Definition.

**Figure 6. Page Definition Structure**

*   **Begin Page Map (BPM)**
*   [**Resource Environment Group**] *
*   [**Conditional Processing Control (CCP)**] (S)
*   [**Include Object (IOB)**] (S)
*   **Data Map** (S)
*   **End Page Map (EPM)**

**Legend:**
*   **\*** = optional [LINEDATA-3-007]
*   **(S)** = can appear more than once

The structured fields and objects that compose a Page Definition are as follows. (Chapter 5, “Structured Fields in a Page Definition and in Line Data” describes the structured fields.)

*   **BPM (Begin Page Map)**: Begins a Page Definition resource object. An optional token name may be specified to identify the object.
*   **Resource Environment Group**: The Resource Environment Group (REG) identifies complex resources that need to be loaded in the presentation device before the pages that follow are processed.
*   **CCP (Conditional Processing Control)**: The CCP structured field is optional but can occur multiple times in the Page Definition. This structured field appears at the beginning of the Page Definition, outside any of the Data Map definitions, since it can be used by any Data Map to control switching among Data Maps in the Page Definition and Medium Maps in the Form Definition. The CCP defines the condition to be tested, the data value to be used to compare against the application data, the action to be taken based on the result of the test, and when the action is to be taken.
    *   A single CCP can make multiple tests, and Page Definitions can contain multiple conditions to form complex testing sequences. These multiple conditions are reflected in multiple CCPs.
    *   Each CCP in a Page Definition object has a unique identifier. The LND structured fields of a Data Map use this identifier to invoke conditional processing. Each LND using conditional processing specifies the length and position of the field in the application data record to be tested. Different LNDs can invoke the same CCP multiple times in the same Data Map definition.
    *   See “Conditional Processing Control (CCP)” for details about the CCP structured field.
*   **IOB (Include Object)**: The IOB structured field is optional but can occur multiple times in the Page Definition. The IOB appears at the beginning of the Page Definition, following the CCP structured fields. The IOB is processed when it is referenced by an LND or RCD. The reference consists of an ID that is specified on the LND or RCD and that must match the ID on an IOB.
*   **Data Map Object**: Provides specific line definitions and mapping instructions for composing line data into a presentation page format. A single Page Definition object may specify multiple Data Maps. Different Data Maps in the Page Definition can be selected by using the Invoke Data Map structured field or by using conditional processing.
*   **EPM (End Page Map)**: Ends a Page Definition resource object. Any name specified in the EPM must match the name specified in the BPM. [LINEDATA-3-008]

## Resource Environment Group

Figure 7 shows the structure of a Resource Environment Group (REG) in the Page Definition.

**Figure 7. Resource Environment Group Structure for a Page Definition**

*   **Begin Resource Environment Group (BSG)**
*   [**Map Data Resource (MDR)**] (S)
*   [**Map Page Overlay (MPO)**] (S)
*   [**Preprocess Presentation Object (PPO)**] (S)
*   **End Resource Environment Group (ESG)**

**Legend:**
*   **\*** = optional [LINEDATA-3-009]
*   **(S)** = can appear more than once

A Resource Environment Group (REG), when specified in a Page Definition, is associated with a print file. The REG is used to identify complex resources, such as high-resolution color images, that need to be downloaded to the presentation device before the pages that follow are processed. The scope of a REG in the Page Definition is the line-format data in the print file. When a print file contains multiple line-format data and mixed data documents, the REG applies only to the line-format data documents in the print file. For a definition of line-format data, see Figure 33. Line-format data may be bounded by explicit BDT/EDT pairs or by implicit BDT/EDT pairs.

**Architecture Note:** To get the optimum performance benefit from the REG in the Page Definition, the print file should contain only line-format data, and only large, complex objects should be mapped in the REG. This will allow the line-format data to be treated as a single document, and the REG will cause all mapped objects to be preloaded to the printer at the start of that document.

The REG in the Page Definition is not applied to MO:DCA documents in the print file. The mapping of resources in a REG is optional. Resources mapped in a REG must still be mapped in the AEG for the Data Map that uses the resources. The structured fields that compose a Resource Environment Group are as follows:

*   **BSG (Begin Resource Environment Group)**: Begins a Resource Environment Group. A token name may be specified to identify the REG.
*   **MDR (Map Data Resource)**: The MDR structured field is optional but can occur multiple times in a REG. The MDR specifies a resource that is required for presentation. The resource is identified with a file name, the identifier of a begin structured field for the resource, or any other identifier associated with the resource. The MDR may additionally specify a local or internal identifier for the resource object. Such a local identifier may be embedded one or more times within an object's data.
*   **MPO (Map Page Overlay)**: The MPO specifies overlay resources required for presentation. It is optional and can occur multiple times in a REG.
*   **PPO (Preprocess Presentation Object)**: The PPO structured field is optional but can occur multiple times in a REG. The PPO specifies presentation parameters for a data object that has been mapped as a resource. These parameters allow the presentation device to preprocess and cache the object so that it is in presentation-ready format when it is included with a subsequent include structured field in the document. Such preprocessing may involve a rasterization or RIP of the object, but is not limited to that. The resource is identified with a file name, the identifier of a begin structured field for the resource, or any other identifier associated with the resource. The referenced resource and all required secondary resources must previously have been mapped with an MDR or an MPO in this environment group.
    *   **Note:** Preprocessing is not supported for objects that are included with structures that are outside the document. Examples of such objects are medium overlays and PMC overlays, both of which are included with structures in the Form Definition.
*   **ESG (End Resource Environment Group)**: Ends a Resource Environment Group. [LINEDATA-3-010]

## Data Map Structure

Figure 8 shows the structure of a Data Map, also called a Page Format.

**Figure 8. Data Map Structure for a Page Definition**

*   **Begin Data Map (BDM)**
*   **Active Environment Group (AEG)**
*   **Data Map Transmission Subcase**
*   **End Data Map (EDM)**

Each Page Definition must include at least one Data Map. Structured fields in the Data Map accomplish the page layout functions similar to those provided by FCBs used with non-AFP printers, but many additional functions are available.

Each Data Map provides instructions for mapping line data to a page. The number of Data Maps that can be included in a Page Definition is limited only by practical considerations such as whether the total size of the Page Definition will be so large that it might not fit in a presentation services program’s program storage. Each Data Map in the Page Definition can contain entirely different information about how a page should appear, so different Data Maps can be used from one page to the next with output produced by a line-data application.

The Data Maps in a Page Definition can select two types of line data processing:
*   Traditional line data containing optional CCs and TRCs are processed using LNDs in the Data Map Transmission Subcase. [LINEDATA-3-011] [LINEDATA-3-012]
*   Record-format line data containing record IDs and optional CCs are processed using RCDs in the Data Map Transmission Subcase. [LINEDATA-3-013]

All Data Maps in the Page Definition must specify the same line data processing.

The application can select which Data Map to use by writing an Invoke Data Map structured field in the output file or by using conditional processing in the Page Definition to select a Data Map based on the value of a field in the application data stream. Examples of using an IDM can be found in Chapter 4, “Mixed Documents: Adding MO:DCA Structured Fields to Line Data”. Examples of conditional processing appear at the end of this chapter.

The Data Map consists of two parts: the Active Environment Group and the Data Map Transmission Subcase. Bracketing them are the Begin Data Map and End Data Map structured fields. The format of these structured fields is as follows:

*   **BDM (Begin Data Map)**: Begins a Data Map. A one-to-eight character token name is required to identify the Data Map. A one-byte code indicates whether the Data Map contains LNDs or RCDs. For the latter, the BDM may contain a triplet that specifies the page margins, a triplet that specifies page count controls, and a triplet that specifies an encoding scheme.
*   **EDM (End Data Map)**: Ends a Data Map. Any name specified in the EDM must match the name specified in the BDM.

The Active Environment Group establishes the page environment, including page size, and can contain the names of resources, such as fonts and page segments, that are to be mapped. The Data Map Transmission Subcase specifies the position, orientation, color, and font selection for text, the identification of data fields to be suppressed, any “fixed text” for the page, and any conditional processing tests and actions. It may also specify objects to be included on the page. [LINEDATA-3-014]

## Active Environment Group Structure

Figure 9 shows the structure of an Active Environment Group (AEG) in the Data Map.

**Figure 9. Data Map Active Environment Group Structure for a Page Definition**

*   **Begin Active Environment Group (BAG)**
*   [**Presentation Environment Control (PEC)**]
*   [**Map Coded Font (MCF)**] (S)
*   [**Map Data Resource (MDR)**] (S)
*   [**Map Page Overlay (MPO)**] (S) †
*   [**Map Page Segment (MPS)**] (S)
*   **Page Descriptor (PGD)**
*   [**Object Area Descriptor (OBD)**] (text)
*   [**Object Area Position (OBP)**] (text)
*   [**Presentation Text Descriptor (PTD)**]
*   **End Active Environment Group (EAG)**

**Legend:**
*   **\*** = optional [LINEDATA-3-015]
*   **(S)** = can appear more than once
*   **†** = required for every IPO specified in a page

The Active Environment Group contains structured fields that describe the features and characteristics of the entire page. Page size, names of fonts, page segments, page overlays, and identifiers of objects to be used are all part of the AEG. Because a page might consist entirely of text or entirely of image (as in a page segment), most of these fields are optional. The only required structured field in the AEG is the Page Descriptor, which contains the size of the page. If an application attempts to place data outside the page boundaries, a positioning data-check error will be generated by the printer and reported by the presentation services program.

### Font Lists

The Map Coded Font (MCF) and Map Data Resource (MDR) structured fields may be used in the AEG to list fonts to be used on the page. If either is used, each font is assigned a local identifier that is used in the body of the page to select the font for a given line or field.

*   **Record-format processing** [LINEDATA-3-016]: When the Page Definition specifies record-format processing, font specifications external to the Page Definition are ignored.
    Each font that is used must be mapped with an MCF or MDR in the AEG, and the MCF or MDR should specify the encoding scheme with an Encoding Scheme ID (X'50') triplet. The values supported in the ESidCP field of the Encoding Scheme ID triplet when printing page numbers with record-format processing are:
    *   X'6100'—EBCDIC Presentation SBCS
    *   X'6200'—EBCDIC Presentation DBCS
    *   X'2100'—PC Data SBCS (ASCII)
    *   X'8200'—Unicode Presentation
    The values supported in the ESidUD field of the Encoding Scheme ID triplet when printing page numbers with record-format processing are:
    *   X'7200'—UTF-16, including surrogates; byte order is big-endian (UTF-16BE)
    *   X'7807'—UTF-8
    The code points used for printing page numbers are:
    *   X'F0'–X'F9' for EBCDIC Presentation SBCS
    *   X'42F0'–X'42F9' for EBCDIC Presentation DBCS
    *   X'30'–X'39' for PC Data SBCS (ASCII) or UTF-8
    *   X'0030'–X'0039' for Unicode Presentation or UTF-16
*   **XML processing** [LINEDATA-3-017]: When the Page Definition specifies XML Data processing, font specifications external to the PageDef are ignored.
    Each font that is used must be mapped with an MCF or MDR in the AEG, and the MCF or MDR must specify the Encoding Scheme ID (X'50') triplet. The values supported in the ESidCP field of the Encoding Scheme ID triplet when formatting XML data with a Page Definition are:
    *   X'6100'—EBCDIC Presentation SBCS
    *   X'2100'—PC Data SBCS (ASCII)
    *   X'8200'—EBCDIC Presentation
    The values supported in the ESidUD field of the Encoding Scheme ID triplet when formatting XML data with a Page Definition are:
    *   X'7200'—UTF-16, including surrogates; byte order is big-endian (UTF-16BE)
    *   X'7807'—UTF-8
    The code points used for printing page numbers are:
    *   X'F0'–X'F9' for EBCDIC Presentation SBCS
    *   X'30'–X'39' for PC Data SBCS (ASCII) or UTF-8
    *   X'0030'–X'0039' for Unicode Presentation or UTF-16 [LINEDATA-3-018]

### Table Reference Characters

If the data to be printed contains 3800-style table reference characters, font information is required to map each table reference character to the name of the font to be used when the data is printed. This information can be provided either by font character-set names in job control statements accompanying the data to be printed or by the fonts mapped in the AEG in the Page Definition. When no fonts are mapped in the AEG but font character-set names are specified in the job control, the first character set specified corresponds to TRC 0, the second to TRC 1, and so forth.

In z/OS, z/VM, and z/VSE, the maximum number of characters allowed in the character-set name (CHARS) parameter was four. This presented no problem when 3800 compatibility-mode character sets were used, as none of them had names of more than four characters. But the typographic fonts available for page-mode printing have eight-character names (including a two-character font prefix), and as a result cannot be coded in the CHARS parameter. To associate a table reference character with an eight-character font name, a Page Definition must be built that explicitly makes that mapping. A Page Definition is also required if five or more fonts are to be used. Page Printer Formatting Aid (PPFA) is a software product available from IBM and Ricoh. Figure 10 provides an example of PPFA source code that could be used to build a Page Definition that addresses both requirements. Here, six table reference characters are defined and each one is associated with a different font of the Sonoran Sans Serif family.

**Figure 10. PPFA Code for Page Definition with Six TRCs to Select Typographic Fonts**

```
SETUNITS LINESP 6 LPI;
PAGEDEF TRCXMP
WIDTH 8.2 IN HEIGHT 10.8 IN
REPLACE YES;
FONT ZERO A0758C; /* EIGHT-POINT SANS SERIF BOLD */
FONT ONE A0759C;  /* NINE-POINT SANS SERIF BOLD */
FONT TWO A0750C;  /* TEN-POINT SANS SERIF BOLD */
FONT THREE A075BC;/* 12-POINT SANS SERIF BOLD */
FONT FOUR A0559C; /* NINE-POINT SANS SERIF ROMAN */
FONT FIVE A0550C; /* TEN-POINT SANS SERIF ROMAN */
PAGEFORMAT JSTRC;
TRCREF 0 FONT ZERO;
TRCREF 1 FONT ONE;
TRCREF 2 FONT TWO;
TRCREF 3 FONT THREE;
TRCREF 4 FONT FOUR;
TRCREF 5 FONT FIVE;
PRINTLINE CHANNEL 1
POSITION .1 IN .2 IN REPEAT 20;
ENDSUBPAGE;
```

The rules for coding Table Reference Characters are different for page mode printers and for the 3800 running in compatibility mode. Table 8 summarizes the differences. [LINEDATA-3-019]

**Table 8. Use of TRCs in Page Mode and 3800 Compatibility Mode**

| Function | Compatibility Mode | Page Mode |
| :--- | :--- | :--- |
| Number of table reference characters supported for a single output file | 4 | 127 |
| Valid hexadecimal values for table reference characters | X'F0'–X'F3' | X'F0'–X'F3' or X'00'–X'7E' for 4 or fewer TRCs; X'00'–X'7E' for more than 4 TRCs |
| How table reference characters are associated with fonts | With character set names in job control language | Same as compatibility mode for 4 or fewer TRCs; with font names in the AEG of the Data Map for more than 4 TRCs |

For compatibility with 3800-1 applications, AFP print servers accept TRC values of X'F0' through X'F3' when four or fewer TRCs are used. TRC values of X'00' through X'7E' are valid regardless of how many fonts are used.

The Line Descriptor structured fields in the Data Map contain a bit that indicates which type of TRC to use. PPFA and PMF set this bit to B'1' to indicate compatibility TRCs when four or fewer TRCs are described in the Page Definition. These software programs set the bit to B'0' when more than four TRCs are used.

**Note:** Regardless of whether font character set names are specified in the job control or not, if fonts are mapped in the AEG:
*   Table reference character 0 (X'00' or X'F0') selects the first font mapped in the Active Environment Group (AEG) of the Data Map; table reference character 1 (X'01' or X'F1') selects the second font mapped in the AEG; and so on. This historically positional selection of fonts mapped in the AEG precludes the use of a mixture of fonts mapped with MCFs and fonts mapped with MDRs. TRCs may be used when all fonts in the AEG are mapped using MCFs only or MDRs only. [LINEDATA-3-020]
*   A table reference character higher than 127 selects the first font mapped in the AEG of the Data Map. [LINEDATA-3-021]
*   A table reference character higher than the number of fonts mapped defaults to the first font mapped in the AEG of the Data Map. [LINEDATA-3-022]

### Page Overlays

If the application uses the Include Page Overlay structured field to place overlays dynamically at any point on the page, a Map Page Overlay structured field must be included in the Active Environment Group containing the name of each overlay to be used.

### Page Segments

A Map Page Segment structured field is not required in the Active Environment Group for each page segment to be used by the application, but printer throughput improves if MPS structured fields are included. Mapped page segments are loaded to the printer the first time they are included and are not reloaded on subsequent invocations. These are called hard page segments. When a page segment is not mapped in the Active Environment Group of the currently active Data Map, the page segment data is loaded to the printer every time the segment is included by an Include Page Segment structured field. Such segments are called soft page segments.

### Data Objects

Data objects that are included with an IOB structured field, such as EPS objects and IOCA objects, can be mapped using the MDR structured field. An MDR for such objects is not required in the AEG of the Data Map, but might improve printer throughput if used. Mapped data objects are loaded to the printer the first time they are included and are not reloaded on subsequent includes. [LINEDATA-3-023]

### Color Management

A Color Management Resource (CMR) can be associated with a page, a data object included on the page with an Include Object (IOB) structured field, or a GOCA or BCOCA object generated by the page definition. Associating a CMR is accomplished by using the MDR structured field to reference the CMR as a resource in the AEG for the Data Map. The CMR reference will be identified as targeted to the page or data object. If a data object is included on a page with an Include Object (IOB) structured field or generated by the page definition, a CMR can be associated with this object by specifying the name of the CMR on the IOB, LND, RCD, or XMD as an external resource reference and then referencing the CMR with a MDR in the AEG of the Data Map. See the Mixed Object Document Content Architecture (MO:DCA) Reference and Color Management Object Content Architecture Reference for more information on Color Management in an AFP environment.

### Structured Fields

The structured fields that comprise the Active Environment Group in a Data Map are as follows: (See the Mixed Object Document Content Architecture (MO:DCA) Reference for a complete description of these structured fields.)

*   **BAG (Begin Active Environment Group)**: Begins an Active Environment Group. A token name may be specified to identify the AEG.
*   **PEC (Presentation Environment Control)**: Specifies parameters that affect the rendering of presentation data and the appearance that is to be assumed by the presentation device. The scope is the page generated using the Data Map.
    *   **Note:** The PEC in the AEG is only used to specify rendering intent using the X'95' triplet.
*   **MCF (Map Coded Font)**: Identifies each font resource object used in the page and assigns each a 1-byte local identifier. The strategic format is MCF-2; the coexistence format is MCF-1.
*   **MDR (Map Data Resource)**: Identifies data object resources that are to be downloaded to the printer for subsequent use in the page.
*   **MPO (Map Page Overlay)**: Identifies overlay object resources used in the page. Each overlay referenced by an Include Page Overlay structured field in the page must be mapped in an MPO structured field.
*   **MPS (Map Page Segment)**: Identifies page segments used on the page that are to be downloaded to the printer.
*   **PGD (Page Descriptor)**: Specifies the units of measure for the page presentation space and the size of the page. This parameter is required.
*   **OBD (Object Area Descriptor)**: Specifies the units of measure for the text output area and the size of the area. The OBD is optional. If specified, units must match the PGD, and the size must match the page size. [LINEDATA-3-024]
*   **OBP (Object Area Position)**: Specifies the origin and orientation of the text output area on the page. The OBP is optional. If specified, origin must be (0,0) and orientation 0°.
*   **PTD (Presentation Text Descriptor)**: Specifies the units of measure for the text presentation space and the size of the space. Required if the page contains presentation text objects.
    *   **PTD-1** (formerly CTD): specifies only units of measure and size. [LINEDATA-3-025]
    *   **PTD-2**: specifies units, extents (3 bytes), and allows initial text conditions. [LINEDATA-3-026]
    *   **Note:** Default initial text conditions (set before PTD conditions):
        *   Initial (I,B) Presentation Position: (0,0)
        *   Text Orientation: 0°, 90°
        *   Coded Font Local ID: X'FF' (default font)
        *   Baseline Increment: 6 lpi
        *   Inline Margin: 0
        *   Intercharacter Adjustment: 0
        *   Text Color: X'FFFF' (default color)
*   **EAG (End Active Environment Group)**: Ends the AEG. Any name specified must match the BAG.

**Table 9. Initial Text Conditions in PTD-2**

| Initial Text Condition | Parameter Control Sequence |
| :--- | :--- |
| Baseline Increment | Set Baseline Increment (SBI) |
| Coded Font Local ID | Set Coded Font Local (SCFL) |
| Initial Baseline Coordinate | Absolute Move Baseline (AMB) |
| Initial Inline Coordinate | Absolute Move Inline (AMI) |
| Inline Margin | Set Inline Margin (SIM) |
| Intercharacter Adjustment | Set Intercharacter Adjustment (SIA) |
| Extended Text Color | Set Extended Text Color (SEC) |
| Text Color | Set Text Color (STC) |
| Text Orientation | Set Text Orientation (STO) |
[LINEDATA-3-027]

## Data Map Transmission Subcase

A Data Map Transmission Subcase can contain LNDs, RCDs, or XMDs, but not a mixture.

### Data Map Transmission Subcase with LNDs

Figure 11 shows the structure of a Data Map Transmission Subcase with LNDs.

**Figure 11. Data Map Transmission Subcase with LNDs**

*   **Begin Data Map Transmission Subcase (BDX)**
*   [**Data Map Transmission Subcase Descriptor (DXD)**]
*   [**Fixed Data Size (FDS)**]
*   [**Fixed Data Text (FDX)**] (S)
*   [**Line Descriptor Count (LNC)**]
*   **Line Descriptor (LND)** (S)
*   **End Data Map Transmission Subcase (EDX)**

**Legend:**
*   **\*** = optional [LINEDATA-3-028]
*   **(S)** = can appear more than once [LINEDATA-3-029]

The principal function of the Data Map Transmission Subcase with LNDs is to map the lines of data to the page. Each line on a page is represented by a Line Descriptor structured field, which contains the horizontal and vertical position on the page where the line is to appear. Rotation and font information is also contained in the Line Descriptors, as is the association with any conditional processing controls used to test data on the current line. The Line Descriptor structured fields are generally used to map lines of text, but they can also be used to position page segments or page overlays or to present line data code points as a bar code. An Include Page Segment or Include Page Overlay structured field that contains a value of X'FFFFFF' in the X-axis positioning parameter, the Y-axis positioning parameter, or both indicates that the page segment or overlay is to be placed at the X-axis or Y-axis position specified by the current LND.

Functions that originated with older printers are also implemented in Line Descriptors. If carriage control skip-to-channel codes are used with the data, each channel code must be defined in at least one Line Descriptor (LND) in the Page Definition; this LND defines the page position associated with that channel code number. Carriage control characters that call for double spacing, triple spacing, or space suppression cause a presentation services program to skip over Line Descriptors or reuse the same Line Descriptor in the Data Map, in a manner analogous to FCBs used with impact printers. Either ANSI or machine code carriage controls can be used, but whichever type is selected must be used for the entire print file. Part of the data cannot contain ANSI carriage controls and another part contain machine code carriage controls. In addition, if carriage control characters are used, every record in the print file must begin with a carriage-control byte, even if it is only “print with single spacing”. The type of carriage control being used must be identified in the job control associated with the print file, just as in a non-AFP environment. [LINEDATA-3-030]

The following new functions are provided in Page Definitions that are not available in FCBs. These functions are triggered by information in the Line Descriptor structured field:

*   **Field formatting** [LINEDATA-3-031]: The ability to separate specific fields in a line-data record and place them anywhere on a page. Field size, orientation, placement, color, and font to be used are specified in the Line Descriptor. Fixed text may be specified and added to data from application programs.
*   **Multiple-up printing** [LINEDATA-3-032]: The ability to divide the page into sections, each with the appearance of a smaller page. This can be accomplished by defining subpages, which are subsets of the page, using Line Descriptors.
*   **Conditional processing** [LINEDATA-3-033]: The ability to define tests on certain characters of the line data and perform actions based on the results of the tests.
*   **Resource object include** [LINEDATA-3-034]: The ability to include an overlay or page segment and position it relative to the current line.
*   **Bar code generation** [LINEDATA-3-035]: The ability to select a field in a record and present it as a bar code.
*   **Color specification** [LINEDATA-3-036]: Specification of spot (highlight) colors or process colors for a record or field.
*   **Object include** [LINEDATA-3-037]: The ability to include a data object relative to the current line and change its position, size, and orientation.

Each Line Descriptor formats only one line-data record. The same record may be formatted multiple times on a page using the “reuse record” function in the Line Descriptor. Since Line Descriptors are contained in a Data Map Transmission Subcase whose scope is a page, they cannot be used to place a single record on more than one page.

The text suppression function in AFP is an implementation of the 3800-1 COPYMOD function. A combination of information in the Line Descriptor structured field in the Page Definition and the Medium Modification Control structured field in the Form Definition, it provides the same function as “spot carbons” with impact printers, where multi-part forms with carbon paper were often used. Some of these applications required that selected fields not be printed on certain copies of the output (for example, internal prices should not appear on customer copies). The same effect can be obtained with AFP printers by defining fields as suppressible in the Page Definition and then suppressing these fields on certain copies in the Form Definition.

### Data Map Transmission Subcase with RCDs

A Data Map Transmission Subcase with RCDs has the same structure as one with LNDs except that the LNDs are replaced with RCDs. A Data Map Transmission Subcase with RCDs is used to process record-format line data instead of traditional line data.

Each record in the data contains a 1 to 250-byte Record ID and is processed by the RCD in the Data Map Transmission Subcase that contains a matching Record ID. If a CC byte is specified in the record, it must precede the Record ID and is not part of the compare. In addition to providing LND-like functions such as data position, orientation, font selection, coloring, bar code generation, and object includes, RCDs support additional functions like headers, trailers, page numbering, and graphics generation.

### Data Map Transmission Subcase with XMDs

A Data Map Transmission Subcase with XMDs has the same structure as one with LNDs except that the LNDs are replaced with XMDs. A Data Map Transmission Subcase with XMDs is used to process XML data instead of traditional line data.

To process XML data, the processor must build a Qualified Tag by concatenating XML start tags. These Qualified Tags are then compared to Qualified Tags in the Data Map. The Qualified Tags in the Data Map are built by specifying a separate XML Name (X'8A') triplet on each XML Descriptor (XMD) for each XML Start tag that has to be traversed in order to process the content of an XML element. If an XMD with a matching Qualified Tag is found, the content of the XML element is formatted with that XMD. If an XMD with a matching Qualified Tag is not found, processing resumes with the next start tag. Note that as the processor parses the XML, it must buffer the XML start tags traversed in order to have a current Qualified Tag. Each time an end tag is found, the last matching start tag is removed. For example, in the following XML hierarchy:

```xml
<person>
  <name>
    <first>John</first>
    <last>Doe</last>
  </name>
</person>
```

The Qualified Tag for the element `<first>` is `{person name first}`. The Qualified Tag for the element `<last>` is `{person name last}`. Notice that the tag for element `<first>` has been removed since its end was received prior to the start tag for element `<last>`. To process this “current” Qualified Tag, an XMD in the Data Map would also have a Qualified Tag made up from separate XML Name (X'8A') triplets for each XML start tag. This Qualified Tag for this XMD would match the current Qualified Tag and the XMD would be used to present the XML element content “John” on the page. [LINEDATA-3-038]

In addition to providing LND-like functions such as data position, orientation, font selection, coloring, bar code generation, and object includes, XMDs support additional functions like headers, trailers, page numbering, and graphics generation.

### Data Map Transmission Subcase Structure

The structured fields that compose the Data Map Transmission Subcase are as follows. (See Chapter 5, “Structured Fields in a Page Definition and in Line Data” for a formal description of these structured fields.)

*   **BDX (Begin Data Map Transmission Subcase)**: Begins the Data Map Transmission Subcase.
*   **DXD (Data Map Transmission Subcase Descriptor)**: Contains constant data.
*   **LNC (Line Descriptor Count)**: Specifies the number of Line Descriptor (LND) or Record Descriptor (RCD) structured fields in the Data Map Transmission Subcase.
*   **LND (Line Descriptor)**: Specifies how the current line data should be processed. The Data Map Transmission Subcase can contain more than one LND and each LND points to the next LND used.
    *   When the print file does not use carriage control characters, processing begins with the first LND structured field.
    *   When the data record contains a carriage control character that specifies a channel code, the first LND containing that channel code is selected.
    *   If conditional processing is specified, the LND identifies the field to be tested and the CCP ID.
    *   When fixed text is specified, it is located in the Fixed Data Text (FDX) structured field.
*   **RCD (Record Descriptor)**: Specifies how the record with matching record ID should be processed.
    *   With RCD processing, carriage controls are ignored.
    *   If a matching RCD is not found, an error is generated. [LINEDATA-3-039]
*   **XMD (XML Descriptor)**: Specifies how the data with matching start tags should be processed.
    *   With XMD processing, carriage controls and TRCs are not allowed.
    *   If a matching XMD is not found, the data is ignored.
*   **FDS (Fixed Data Size)**: Required if constant text is included. Specifies the number of bytes of text in the following FDX structured fields.
*   **FDX (Fixed Data Text)**: Contains data that can be added to or used instead of line data.
*   **EDX (End Data Map Transmission Subcase)**: Ends the Data Map Transmission Subcase. [LINEDATA-3-040]

## Field Formatting—LND Processing

A Page Definition may be used to break line-data records into fields that are formatted individually. This is done by building a chain of LND structured fields called a reuse chain.

The first LND used to process an input record is called the base LND. If this LND specifies flag byte bit 6=B'1' (reuse record), it is also the head of a reuse chain and points to the next LND in the chain. This next LND is used to select and process a field in the same record. If additional field processing is required, the next LND also specifies flag byte bit 6=B'1' and points to another LND, and so on. All LNDs in a reuse chain are called reuse LNDs. The last LND in a reuse chain specifies flag byte bit 6=B'0' and points to X'0000'. This LND terminates the reuse chain. [LINEDATA-3-041]

## Field Formatting—RCD Processing

Field formatting is also supported when RCDs are used to process record-format line data. The first RCD used to process an input record is called a record RCD. It is identified by RCDFlgs bit 6=B'0' and RCDFlgs bit 11=B'0'. If the FLDrcd parameter in a record RCD is non-zero, it specifies the RCD number of a field RCD that is to be used to process a field in this record. A field RCD is identified by RCDFlgs bit 6=B'1' and RCDFlgs bit 11=B'0'. Multiple field RCDs can be chained to a record RCD in this manner. The last field RCD in this chain must specify FLDrcd= X'0000'. [LINEDATA-3-042] [LINEDATA-3-043]

## Field Formatting—XMD Processing

Field formatting is also supported when XMDs are used to process XML data. The first XMD used to process a start tag is called an element XMD. It is identified by XMDFlgs bit 6=B'0', XMDFlgs bit 10=B'0', and XMDFlgs bit 11=B'0'. If the FLDxmd parameter in an element XMD is non-zero, it specifies the XMD number of a field XMD that is to be used to process a field in this element data. A field XMD is identified by XMDFlgs bit 6=B'1' and XMDFlgs bit 11=B'0'. Multiple field XMDs can be chained to an element XMD in this manner. The last field XMD in this chain must specify FLDxmd=X'0000'. [LINEDATA-3-044]

## Using Conditional Processing in a Page Definition

The conditional processing function allows a different Data Map in the current Page Definition, a different Medium Map in the current Form Definition, or both to be selected for use with the next page based on characteristics of the application data stream. This provides a way to change Data Maps or Medium Maps as necessary without having to make application programming changes. The new format can take effect either before or after a specified line or a specified subpage.

With LND-based Data Maps, a subpage is a subset of the lines presented on a page. Subpages are defined in the Data Map by the user when coding a Page Definition and are often used to create multiple-up Page Definitions. Subpages are ignored with RCD-based and XMD-based Data Maps, that is, each page is a single subpage.

Conditional processing is implemented by a combination of structured fields in the Page Definition. CCP structured fields specify the test to be performed and action to be taken, while LND, RCD, and XMD structured fields include the location and length of data fields to be tested and a pointer to the CCP. When the conditions of a test are satisfied, the actions that can be taken are switching to a new Data Map, switching to a new Medium Map, or both, either before or after the current line or subpage is printed.

When the action takes effect, printing of the current page ends. If a new Data Map is selected, printing resumes on a new side of a sheet of paper. If a new Medium Map is selected, printing resumes on a new physical sheet. As a result, it is not possible to format part of a page with one Data Map and format another part of the same page with a different Data Map. Note that the Medium Map might specify the N-up function, which places multiple pages into partitions on a sheet side. When N-up is specified, switching to a new Data Map or a new Medium Map might cause printing to resume in a new partition instead of on a new sheet-side or a new sheet.

### Using Different Formats for Different Subsets of Output

A common example of this is an application program requirement to print detail pages of a report in a different format from summary pages. Assuming that a known field in the application data stream can be tested to identify the detail records and the summary records, a Page Definition with two Data Maps can be constructed.

Figure 12 assumes a file where each record has identifying information in bytes 2 through 5. Records with the characters DETL in these positions use Data Map PF1 and Medium Map CG1. Records with the characters SUMM use Data Map PF2 and Medium Map CG2. Figure 12 shows the PPFA code that generates a Page Definition to test these positions and to print the detail pages in the ACROSS direction and the summary pages in the DOWN direction. [LINEDATA-3-045]

**Figure 12. PPFA Code for Page Definition with Conditional Processing**

```
SETUNITS 1 IN 1 IN ;
LINESP 6 LPI ;
PAGEDEF CPSAM1 REPLACE YES ;
PAGEFORMAT PF1
  WIDTH 8.2 HEIGHT 10.0
  DIRECTION ACROSS ;
  PRINTLINE REPEAT 1
    CHANNEL 1 ;
  CONDITION TEST1 START 2 LENGTH 4
    WHEN EQ 'SUMM'
    BEFORE SUBPAGE
    COPYGROUP CG2
    PAGEFORMAT PF2 ;
  PRINTLINE REPEAT 59 ;
ENDSUBPAGE ;

PAGEFORMAT PF2
  WIDTH 10.0 HEIGHT 8.2
  DIRECTION DOWN;
  PRINTLINE REPEAT 1
    CHANNEL 1 ;
  CONDITION TEST2 START 2 LENGTH 4
    WHEN EQ 'DETL'
    BEFORE SUBPAGE
    COPYGROUP CG1
    PAGEFORMAT PF1 ;
  PRINTLINE REPEAT 34 ;
ENDSUBPAGE ;
```

### Conditionally Skipping to a New Page or a New Sheet

Another common use of conditional processing is to skip to a new page or a new sheet when a control break in the output data occurs. This control break might be the start of a new customer number, a new department, or some other change in the output that requires starting on a new page, or on a new sheet of paper.

Such cases include applications that print using multiple-up format, where having data for one department appear on the left-hand half of the page while having data for a different department appear on the right-hand half of the page is not desirable. This possibility can be avoided by having the application force a completely new page when the department number changes. In PPFA, this condition is coded with the NEWSIDE parameter.

Applications that print duplex output (using both front and back of the form) probably must force a new physical sheet at a control break in the data, to avoid having output for two different user destinations on the front and back of the same sheet. In PPFA, this condition is coded with the NEWFORM parameter. For output printed multiple-up on both sides of the sheet, the NEWFORM parameter forces a new page and a new sheet.

A new page or sheet can be forced when using a Page Definition with only one Data Map or a Form Definition with only one Medium Map. Conditional processing can be used to re-invoke the currently active Data Map or Medium Map when the condition is satisfied. Note that if the Medium Map specifies the N-up function, the new “sheet” might actually be a new N-up partition on the sheet. [LINEDATA-3-046]

The example in Figure 13 shows PPFA source code to accomplish a skip to a new page when the department number in character positions 1 through 3 changes.

**Figure 13. PPFA Code for Page Definition to Skip to New Page**

```
SETUNITS 1 IN 1 IN ;
LINESP 8 LPI ;
PAGEDEF NEWPG REPLACE YES
WIDTH 10.5 HEIGHT 8.1
DIRECTION DOWN ;
PAGEFORMAT NEWPG ;
PRINTLINE REPEAT 40
  CHANNEL 1
  POSITION .5 TOP ;
CONDITION TEST1 START 1 LENGTH 3
  WHEN CHANGE
  BEFORE SUBPAGE
  NEWSIDE ;
ENDSUBPAGE ;
```

The example in Figure 14 is similar; but in this case the skip is to a new sheet, or form, where printing of the output is resumed.

**Figure 14. PPFA Code for Page Definition to Skip to New Sheet**

```
SETUNITS 1 IN 1 IN ;
LINESP 8 LPI ;
PAGEDEF NEWFM REPLACE YES
WIDTH 10.5 HEIGHT 8.1
DIRECTION DOWN ;
PAGEFORMAT NEWFM ;
PRINTLINE REPEAT 40
  CHANNEL 1
  POSITION .5 TOP ;
CONDITION TEST1 START 1 LENGTH 3
  WHEN CHANGE
  BEFORE SUBPAGE
  NEWFORM ;
ENDSUBPAGE ;
```

### Processing Line Data with Shift-Out/Shift-In (SOSI) Controls

Shift-out (SO-X'0E') and shift-in (SI-X'0F') controls are used to signal the beginning and end, respectively, of a string of double-byte code points that are to be rendered using characters from a double-byte font or rendered as a QR Code bar code symbol. SOSI processing is specified in the print request and applies to both fixed text fields and the input line data.

SOSI processing for text output is supported by two modes of font selection in the PageDef. Both modes may be intermixed in the same Page Map.

*   **Record-based or field-based font selection** [LINEDATA-3-047]: In this mode, the font to be used following an SO can be uniquely selected for each record or field by specifying a non-zero shift-out font local ID in the LND or RCD. [LINEDATA-3-048] The font used following an explicit SI is then always the primary font and use of this font must be enabled. An error condition exists if the primary font is not enabled. Note that an implicit SI is assumed at the start of every record. [LINEDATA-3-049]
*   **Page-based font selection** [LINEDATA-3-050]: In this mode, the font to be used following an SO is the same for all records and fields on the page. The font used following an SO is the font mapped to local ID X'02' in the AEG for the Data Map. The font used following an explicit SI is the font mapped to local ID X'01' in the AEG. Both an SO font and an SI font must be mapped in the AEG with the proper local IDs.

For text output, the SOSI processing modes are described as follows:

| SOSI mode | Action |
| :--- | :--- |
| **SOSI1** | Each SO (X'0E') is replaced with a blank (X'40') and a Set Coded Font Local (SCFL) for font X'02'. Each SI (X'0F') is replaced with an SCFL for font X'01' and a blank (X'40'). [LINEDATA-3-051] [LINEDATA-3-052] |
| **SOSI2** | Each SO (X'0E') is replaced with an SCFL for font X'02'. Each SI (X'0F') is replaced with an SCFL for font X'01'. [LINEDATA-3-053] [LINEDATA-3-054] |
| **SOSI3** | Each SO (X'0E') is replaced with an SCFL for font X'02'. Each SI (X'0F') is replaced with an SCFL for font X'01' and two blanks (X'4040'). [LINEDATA-3-055] [LINEDATA-3-056] |
| **SOSI4** | Each SO and SI control is skipped and not counted when calculating offsets. The conversion is the same as for SOSI2. |

For QR Code bar code output, data is converted to Shift/JIS ASCII. SO and SI characters are removed. This processing is the same for all SOSI modes.

When processing data with SOSI controls, the processor assumes that each line or record starts with single-byte code points. This means that the data is scanned for SOSI controls one byte at a time. After processing a shift-out control, the data is scanned two bytes at a time. [LINEDATA-3-057]

**Notes:**
1.  Avoid mixing SOSI controls and TRCs when using page-based font selection. [LINEDATA-3-058]
2.  Shift-out/Shift-in controls are not used in Unicode data. [LINEDATA-3-059]
3.  SOSI input data is not appropriate for bar code symbologies other than QR Code. [LINEDATA-3-060]
4.  When using SOSI with Record Format data using delimited fields, if the field is to print using double-byte code points, the SO control must follow the delimiter. [LINEDATA-3-061]
5.  Shift-out/Shift-in controls are not supported when processing XML data. [LINEDATA-3-062]

### Printing Bar Codes with a Page Definition

A Page Definition can be used to print a bar code symbol using data from a line data record, an XML element, or a field within them. [LINEDATA-3-063] [LINEDATA-3-064] [LINEDATA-3-065] [LINEDATA-3-066]

This is done by specifying a Bar Code Symbol Descriptor (X'69') triplet on the LND, RCD, or XMD. The position specified by the descriptor indicates the symbol origin, and the text orientation indicates the rotation. Data can be obtained from multiple fields using the Concatenate Bar Code Data (X'93') triplet.

For improved throughput, bar code symbols using the same descriptor and rotation are grouped into a single bar code object. The origin of the object presentation space is selected as one of the four corners of the page based on the text orientation. [LINEDATA-3-067]

### Printing Graphics with a Page Definition

A Page Definition can generate simple graphics primitives (lines, boxes, circles, ellipses) when processing record-format line data (RCDs) or XML data (XMDs). This is done by specifying a Graphics Descriptor (X'7E') triplet. Graphics primitives with the same descriptor and orientation are grouped into a single graphics object. The mapping between graphics window and object area is position and trim.

### Relative Baseline Positioning—LND Processing

Relative baseline positioning allows records, fields, and objects to be positioned (in the baseline direction) relative to a previous record, field, or object. It is used when LND flag byte bit 13 is set to B'1'. [LINEDATA-3-068]

The baseline position used as a reference is determined as follows:
*   **For base LNDs** [LINEDATA-3-069]: Relative to the last base LND processed. If a page/subpage boundary was crossed, relative to the first LND for the page/subpage.
*   **For reuse LNDs** [LINEDATA-3-070]: Relative to the last LND used to print.
*   **First LND of a Data Map** [LINEDATA-3-071]: Relative to the origin (I=0, B=0).
*   **First LND of a subpage** [LINEDATA-3-072]: Relative to the last print position.

**Restriction:** The text orientation of an LND that specifies relative baseline positioning must be the same as the text orientation of the reference LND. [LINEDATA-3-073]

### Skip-to-Channel Processing for Relative Baseline Positioning

When a skip-to-channel carriage control is received, the remainder of the LNDs are searched for a matching channel code. If none is found (and it's not channel 1), the search repeats from the top of the subpage for a relative LND. If still not found, the search continues in the next subpage.

### Relative Baseline Positioning—RCD Processing

*   **For record RCDs** [LINEDATA-3-074]: Relative to the last record RCD processed. If a page boundary was crossed, relative to the top margin.
*   **For field RCDs** [LINEDATA-3-075]: Relative to the last RCD used to print.
*   **First RCD of a Data Map** [LINEDATA-3-076]: Relative to the top margin.

**Restriction:** Text orientation must match the reference RCD. [LINEDATA-3-077]

### Relative Baseline Positioning—XMD Processing

*   **For element XMDs** [LINEDATA-3-078]: Relative to the last element XMD processed. If a page boundary was crossed, relative to the top margin.
*   **For field XMDs** [LINEDATA-3-079]: Relative to the last XMD used to print.
*   **First XMD of a Data Map** [LINEDATA-3-080]: Relative to the top margin.

**Restriction:** Text orientation must match the reference XMD. [LINEDATA-3-081]

### Relative Inline Positioning—XMD Processing

Relative inline positioning allows data and objects to be positioned (in the inline direction) relative to data placed previously on the page. It is used when XMD flag byte bit 12 is set to B'1'.

**Restriction:** Text orientation must match the reference XMD. [LINEDATA-3-082]

**Note:** Data must not exceed page boundaries defined in the Page Descriptor (PGD). [LINEDATA-3-083]

## The Function of the Form Definition

A Form Definition is a MO:DCA print control object used to place pages on sheets. Form Definitions contain physical environment information such as paper drawer selection and simplex/duplex mode. They might also specify medium overlays and PMC overlays.

Form Definitions include components called Medium Maps or Copy Groups. An application can switch between Medium Maps using conditional processing or Invoke Medium Map (IMM) structured fields. Control for presentation starts with the first Medium Map and reverts to it at the beginning of each document or set of line-data records. [LINEDATA-3-084]
