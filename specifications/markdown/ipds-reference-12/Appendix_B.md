# Appendix B. Examples of IPDS Command Sequences

This appendix provides examples of the following IPDS command sequences: [IPDS-B-001]

*   Initialization [IPDS-B-002]
*   Page Segment [IPDS-B-003]
*   Overlay [IPDS-B-004]
*   Page

**Note:** These sequences are only examples, and the host need not send each command listed. [IPDS-B-005]

In the following examples, commands that request an acknowledgment from the printer have the Acknowledgment Required (ARQ) bit on. When the ARQ bit is on, the printer sends an Acknowledge Reply (ACK) to the host. In the example below: [IPDS-B-006]

*   **&rarr;** indicates a command from the host to the printer [IPDS-B-007]
*   **&larr;** indicates a reply from the printer to the host. [IPDS-B-008]

### Table 72. A Typical IPDS Command Sequence

| Sequence Flow | Command Name | Description |
| :--- | :--- | :--- |
| **Initialization** | &rarr; Sense Type and Model (STM) with ARQ | IPDS command set implementation [IPDS-B-009]|
| | &larr; Acknowledge Reply (ACK) | Return type and model information [IPDS-B-010]|
| | &rarr; XOH Obtain Printer Characteristics (OPC) with ARQ | Request printer characteristics [IPDS-B-011]|
| | &larr; Acknowledge Reply (ACK) | Return printer characteristics [IPDS-B-012]|
| | &rarr; Set Home State (SHS) | Set printer to home state [IPDS-B-013]|
| | &rarr; Logical Page Descriptor (LPD) | Define a logical page [IPDS-B-014]|
| | &rarr; Logical Page Position (LPP) | Position a logical page [IPDS-B-015]|
| | &rarr; Load Copy Control (LCC) | Select copy options [IPDS-B-016]|
| | &rarr; Load Font Equivalence (LFE) with ARQ | Establish font equivalences [IPDS-B-017]|
| | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation [IPDS-B-018]|
| **Page Segment** | &rarr; Begin Page Segment (BPS) | Set printer to Page Segment state [IPDS-B-019]|
| | &rarr; Write Text (WT) | Store text data in page segment [IPDS-B-020]|
| | &rarr; Write Text (WT) | Store text data in page segment [IPDS-B-021]|
| | &rarr; Write Text (WT) | Store text data in page segment [IPDS-B-022]|
| | &rarr; Write Image Control 2 (WIC2) | Start IO-Image state [IPDS-B-023]|
| | &rarr; Write Image 2 (WI2) | Store IO-Image data in page segment [IPDS-B-024]|
| | &rarr; End | End IO-Image state [IPDS-B-025]|
| | &rarr; Write Text (WT) | Store text data in page segment [IPDS-B-026]|
| | &rarr; Write Text (WT) | Store text data in page segment [IPDS-B-027]|
| | &rarr; End Page (EP) with ARQ | Return to home state [IPDS-B-028]|
| | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation [IPDS-B-029]|
| | &rarr; Logical Page Descriptor (LPD) | Define a logical page [IPDS-B-030]|
| | &rarr; Load Font Equivalence (LFE) | Establish font equivalences [IPDS-B-031]|
| **Overlay** | &rarr; Begin Overlay (BO) | Enter overlay state [IPDS-B-032]|
| | &rarr; Write Text (WT) | Store text data in overlay [IPDS-B-033]|
| | &rarr; Write Text (WT) | Store text data in overlay [IPDS-B-034]|
| | &rarr; Write Text (WT) | Store text data in overlay [IPDS-B-035]|
| | &rarr; Include Overlay (IO) | Include another overlay [IPDS-B-036]|
| | &rarr; Write Graphics Control (WGC) | Enter graphics state [IPDS-B-037]|
| | &rarr; Write Graphics (WG) | Store graphics data in overlay [IPDS-B-038]|
| | &rarr; Write Graphics (WG) | Store graphics data in overlay [IPDS-B-039]|
| | &rarr; End | End graphics state [IPDS-B-040]|
| | &rarr; Write Text (WT) | Store text data in overlay [IPDS-B-041]|
| | &rarr; Write Text (WT) | Store text data in overlay [IPDS-B-042]|
| | &rarr; Include Overlay (IO) | Include another overlay [IPDS-B-043]|
| | &rarr; Include Page Segment (IPS) | Include page segment in overlay [IPDS-B-044]|
| | &rarr; End Page (EP) with ARQ | Return to home state [IPDS-B-045]|
| | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation [IPDS-B-046]|
| **Page** | &rarr; Begin Page (BP) | Enter page state [IPDS-B-047]|
| | &rarr; Write Text (WT) | Send text data to printer [IPDS-B-048]|
| | &rarr; Include Overlay (IO) | Print overlay [IPDS-B-049]|
| | &rarr; Include Page Segment (IPS) | Print page segment [IPDS-B-050]|
| | &rarr; Write Image Control (WIC) | Start IM-Image state [IPDS-B-051]|
| | &rarr; Write Image (WI) | Send IM-Image data to printer [IPDS-B-052]|
| | &rarr; Write Image (WI) | Send IM-Image data to printer [IPDS-B-053]|
| | &rarr; End | End IM-Image state [IPDS-B-054]|
| | &rarr; Write Text (WT) | Send text data to printer [IPDS-B-055]|
| | &rarr; Write Text (WT) | Send text data to printer [IPDS-B-056]|
| | &rarr; Include Overlay (IO) | Print overlay [IPDS-B-057]|
| | &rarr; Include Page Segment (IPS) | Print page segment [IPDS-B-058]|
| | &rarr; End Page (EP) with ARQ | Complete all printing and return to home state [IPDS-B-059]|
| | &larr; Acknowledge Reply (ACK) | Acknowledge successful operation [IPDS-B-060]|

## A Printer-Initialization Sequence

Before any printing begins, the host must specify certain parameters and conditions for the printer. The following command sequence, as shown in Table 72, accomplishes this task. [IPDS-B-061]

### STM (Sense Type and Model)
The host sends the STM command to sense the IPDS command set implementation. [IPDS-B-062]

### ACK (Acknowledge Reply)
If the STM command has the ARQ bit on, the printer responds with type and model information to the host. This information includes printer type, model, and command-set vector information. [IPDS-B-063]

### XOH OPC (Obtain Printer Characteristics)
The host sends the XOH-OPC command to the printer, requesting printer characteristics to be placed in the Acknowledge Reply special data area. [IPDS-B-064]

### ACK (Acknowledge Reply)
If the XOH-OPC command has the ARQ bit on, the information is supplied; if the ARQ bit is off, the XOH-OPC command is treated as a NOP. [IPDS-B-065]

### SHS (Set Home State)
The host sends the SHS command to ensure the printer is in home state. [IPDS-B-066]

### LPD (Logical Page Descriptor)
The LPD command sets print characteristics for the current logical page. These parameters include logical page size, initial text-coordinate positions and text direction, initial text margin, intercharacter adjustment, baseline increment, font local ID, and text color. [IPDS-B-067]

### LPP (Logical Page Position)
The LPP command positions the upper-left corner of the logical page (as defined by the LPD command) with respect to the medium presentation space. [IPDS-B-068]

### LCC (Load Copy Control)
The LCC command specifies how many copies of each sheet are produced, whether to print simplex or duplex, the overlays that are to be included on each copy, and the suppressions that are to be activated for each copy. Suppression allows text data to be selectively suppressed during printing. [IPDS-B-069]

### LFE (Load Font Equivalence)
The LFE command maps font local IDs (used within the text, graphics, or bar code data) to font Host-Assigned IDs used for resource management. [IPDS-B-070]

### ACK (Acknowledge Reply)
If the LFE command has the ARQ bit on, the printer responds with the Acknowledge Reply to inform the host of successful receipt of all the previous commands. The printer is now ready to accept data for print operations. The initialization and preparation sequence is finished. [IPDS-B-071]

## The Page-Segment Sequence

The page-segment sequence creates a page segment resource for later printing. The following command sequence, as shown in Table 72, illustrates the loading of a page segment. [IPDS-B-072]

**Note:** This sequence is only an example. A page segment can contain any combination of text, image, graphics, and bar code data. [IPDS-B-073]

### BPS (Begin Page Segment)
The host sends the BPS command to the printer, causing the printer to leave home state and enter page segment state. Page segment state creates a segment of page data to save within the printer for later printing. The BPS command contains an ID for later use in selecting this segment. This segment can contain combinations of text, images, bar code data, and graphics. [IPDS-B-074]

### WT (Write Text)
The WT command sends text data to the printer. Because the printer is currently in page segment state, the text information does not print at this time. Instead, the data becomes part of the page segment. If no text data is to be included in the segment, this command does not occur. Multiple WT commands can be sent to the printer while in page segment state. [IPDS-B-075]

### WIC2 (Write Image Control 2)
The WIC2 command causes the printer to enter IO-Image state. [IPDS-B-076]

### WI2 (Write Image 2)
The WI2 command sends IO-Image data to the printer. [IPDS-B-077]

**Note:** Both the IM-Image commands (WIC and WI) and the IO-Image commands (WIC2 and WI2) send image data to the printer. However, the IO-Image commands provide additional functions over the IM-Image commands. [IPDS-B-078]

### END (End)
The END command terminates IO-Image state. The printer returns to page segment state, with the image stored for later use. [IPDS-B-079]

### WT (Write Text)
The WT command is repeated at this point in the sequence, to illustrate that additional text data can be added to the page segment. Graphics data, bar code data, or additional image data can also be included in the page segment. [IPDS-B-080]

### EP (End Page)
The EP command causes the printer to leave page segment state and return to home state. This command can contain an ARQ to ensure successful transmission of the page segment. [IPDS-B-081]

### ACK (Acknowledge Reply)
If the EP command has the ARQ bit on, the printer responds with the ACK to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has accepted all the segment data and has stored this information for later printing. [IPDS-B-082]

**Note:** Page segment commands need not be syntax-checked until they are included on a logical page by use of the Include Page Segment command. [IPDS-B-083]

## The Overlay Sequence

The overlay sequence creates an overlay resource for later printing. The following command sequence, as shown in Table 72, illustrates the loading of a typical overlay. [IPDS-B-084]

**Note:** This sequence is only an example. An overlay can contain any combination of text data, image data, graphics data, bar code data, object container data, included page segments, and included overlays. [IPDS-B-085]

### LPD (Logical Page Descriptor)
The LPD command sets print characteristics for the current logical page; it will be stored with the overlay. These parameters include logical page size, initial text-coordinate positions and text direction, initial text margin, intercharacter adjustment, baseline increment, font local ID, and text color. [IPDS-B-086]

### LFE (Load Font Equivalence)
The LFE command maps font local IDs (used within the text, graphics, or bar code data) to font Host-Assigned IDs used for resource management. It will be stored with the overlay. [IPDS-B-087]

### BO (Begin Overlay)
The host sends the BO command to the printer, causing the printer to leave home state and enter overlay state. Overlay state creates an overlay resource to be saved within the printer for later printing. The BO command contains an ID for later use in selecting this overlay. [IPDS-B-088]

### WT (Write Text)
The WT command sends text data to the printer. Because the printer is currently in overlay state, this text information does not print at this time. Instead, the data becomes part of the overlay. If no text data is to be included in the overlay, this command does not occur. Multiple WT commands can be sent to the printer while in overlay state. [IPDS-B-089]

### IO (Include Overlay)
The IO command causes a previously stored overlay to be included with the current overlay. The IO command contains an ID field that specifies the overlay. [IPDS-B-090]

### WGC (Write Graphics Control)
The WGC command causes the printer to enter graphics state. Parameters in this command specify the placement, size, and orientation of the graphics object area. [IPDS-B-091]

### WG (Write Graphics)
The WG command sends graphics data to the printer. The graphics data is contained in drawing orders, that specify various elements of the graphics picture. These include color, size, line type, line width, and other parameters. One or more WG commands present the graphics picture. [IPDS-B-092]

### END (End)
The END command terminates graphics state. The printer returns to overlay state with the graphics data now part of the overlay. [IPDS-B-093]

### WT (Write Text)
The WT command is repeated at this point in the sequence to illustrate that additional text data can be added to the overlay. Image data, graphics data, bar code data, or object container data can also be included in the overlay. [IPDS-B-094]

### IO (Include Overlay)
The IO command causes a previously stored overlay to be included with the current overlay. [IPDS-B-095]

### IPS (Include Page Segment)
The IPS command causes a previously stored page segment to be included in the current overlay as if it had been part of the overlay data. The IPS command contains an ID field that specifies the page segment. [IPDS-B-096]

### EP (End Page)
The EP command causes the printer to leave overlay state and return to home state. This command can contain an ARQ to verify transmission of the overlay. [IPDS-B-097]

### ACK (Acknowledge Reply)
If the EP command has the ARQ bit on, the printer responds with the ACK reply to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has accepted all of the overlay data and has stored this information for later printing. [IPDS-B-098]

**Note:** Overlay commands need not be syntax-checked until they are included on a logical page by use of the Include Overlay command or on the medium presentation space by use of the Load Copy Control command. [IPDS-B-099]

## The Page Sequence

The page sequence causes a page to be created and printed on the current sheet. This data can include previously stored overlays or page segments, as well as immediate text or object data. The following command sequence, as shown in Table 72, illustrates the loading of a page. [IPDS-B-100]

**Note:** This sequence is only an example. A page sequence can contain any combination of text data, image data, graphics data, bar code data, object container data, page segments, or overlays. [IPDS-B-101]

### BP (Begin Page)
The host sends the BP command to the printer, causing the printer to leave home state and enter page state. [IPDS-B-102]

### WT (Write Text)
The WT command sends text data to the printer. Because the printer is currently in page state, this text information prints on the current logical page. Multiple WT commands can be sent to the printer while in page state. [IPDS-B-103]

### IO (Include Overlay)
The IO command causes a previously stored overlay to merge with the current logical page. The IO command contains an ID field that specifies the overlay. This included overlay is independent of the page and can extend partially or completely outside of the page's logical page. [IPDS-B-104]

### IPS (Include Page Segment)
The IPS command causes a previously stored page segment to merge with the current logical page as if it had been part of the page data. An ID field in this command identifies the page segment. [IPDS-B-105]

### WIC (Write Image Control)
The WIC command causes the printer to enter IM-Image state. [IPDS-B-106]

### WI (Write Image)
The WI command sends IM-Image data to the printer. One or more of these commands create the actual image for printing. [IPDS-B-107]

**Note:** Both the IM-Image commands (WIC and WI) and the IO-Image commands (WIC2 and WI2) send image data to the printer. However, the IO-Image commands provide additional functions over the IM-Image commands. [IPDS-B-108]

### END (End)
The END command terminates IM-Image state. The printer returns to page state. [IPDS-B-109]

### WT (Write Text)
The WT command is repeated at this point in the sequence to illustrate that additional text data can be added to the page. Image data, graphics data, bar code data, or object container data can also be included on the page. [IPDS-B-110]

### IO (Include Overlay)
The IO command causes a previously stored overlay to merge with the current logical page. [IPDS-B-111]

### IPS (Include Page Segment)
The IPS command causes a previously stored page segment to merge with the current logical page as if it had been part of the page data. An ID field in this command identifies the page segment. [IPDS-B-112]

### EP (End Page)
The EP command causes the printer to leave page state and return to home state. This command can contain an ARQ to verify successful transmission of the page data. [IPDS-B-113]

### ACK (Acknowledge Reply)
If the EP command had the ARQ bit on, the printer responds with the ACK reply to inform the host of successful receipt of all the previous commands. This reply indicates to the host that the printer has successfully accepted all the previous commands, and that the page will subsequently be transferred to paper. [IPDS-B-114]
