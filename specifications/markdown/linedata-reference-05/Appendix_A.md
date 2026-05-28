Appendix A. Document and Resource Object Diagrams
This appendix contains diagrams of the elements that make up a data stream accepted by presentation
services program servers in the AFP environment. Some presentation services programs might accept
additional elements and objects. For the formal definition of all valid AFP (MO:DCA) object structures, see the
Mixed Object Document Content Architecture (MO:DCA) Reference.
Unless otherwise noted, the objects and structured fields are presented from left to right in the order in which
they must appear.
The following symbols apply to the syntax structures in this appendix:
* An asterisk indicates optional structured fields or objects. Those not marked are required. [LINEDATA-A-001]
S The letter “S” indicates structured fields or objects that can appear more than once in the
document. Those not marked can appear only once in the document.
xx object
A shaded box indicates objects that contain structured fields or other objects.
Other All other symbols are explained in the figures. [LINEDATA-A-002]


### Figure 30. Structure of a Print File
**Print File**
[ Begin Print File (BPF, D3A8A5) ]
&nbsp;&nbsp;&nbsp;&nbsp;[ (BRG, D3A8C6) Begin Resource Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (BRS, D3A8CE) Begin Resource
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Resource Object (Note: can be any valid Resource Object)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(ERS, D3A9CE) End Resource ] (S)
&nbsp;&nbsp;&nbsp;&nbsp;(ERG, D3A9C6) End Resource Group ]
&nbsp;&nbsp;&nbsp;&nbsp;(Document) (S)
[ End Print File (EPF, D3A9A5) ] [LINEDATA-A-003]

**Document** (Note 2)
[ (BDI, D3A8A7) Begin Document Index
&nbsp;&nbsp;&nbsp;&nbsp;(IEL, D3B2A7) Index Element (S)
(EDI, D3A9A7) End Document Index ]
(BDT, D3A8A8) Begin Document
&nbsp;&nbsp;&nbsp;&nbsp;[ (BSG, D3A8D9) Begin Resource Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...
&nbsp;&nbsp;&nbsp;&nbsp;(ESG, D3A9D9) End Resource Environment Group ] (S)
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IMM, D3ABCC) Invoke Medium Map (S) ] (Note 5)
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BPG, D3A8AF) Presentation Page (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IPG, D3AFAF) Include Page (S) ]
(EDT, D3A9A8) End Document [LINEDATA-A-004]

**Notes:**
1. The BPF/EPF structured fields are optional as a pair; if one is specified, the other must be specified as well. [LINEDATA-A-005]
2. The mixed line-page documents and composed documents can occur in any order following the inline resource group. [LINEDATA-A-006]
3. Each AFP (MO:DCA) document may optionally be preceded by a single document index that is implicitly tied to the document and that indexes the document. For the formal definition of the MO:DCA document index see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. [LINEDATA-A-007]
4. An AFP (MO:DCA) document may contain Link Logical Element (LLE) structured fields following the BDT and may also group presentation pages into named page groups. MO:DCA page groups may in turn contain Tag Logical Element (TLE) structured fields following BNG. These structures do not affect the presentation of the document. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. [LINEDATA-A-008]
5. If a Medium Map is included internal (inline) to the document, it is activated by immediately following it with an IMM that explicitly invokes it, otherwise the internal Medium Map is ignored. An IMM that does not follow an internal Medium Map may not invoke an internal Medium Map elsewhere in the document and is assumed to reference a Medium Map in the current Form Definition. [LINEDATA-A-009]

### Figure 31. Structure of a Mixed Line-Page Document
**Mixed Line-Page Document**
(BDT, D3A8A8) Begin Document
&nbsp;&nbsp;&nbsp;&nbsp;[ (IMM, D3ABCC) Invoke Medium Map (S) ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IDM, D3ABCA) Invoke Data Map (S) ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;+ (Presentation Page) (S)
&nbsp;&nbsp;&nbsp;&nbsp;+ (Line Format Data) (S)
(EDT, D3A9A8) End Document [LINEDATA-A-010]

**Note †:** An IMM or IDM may be specified before any page; multiple IMMs and IDMs are allowed.
**Note:** The No Operation (NOP) structured field may appear anywhere in a mixed document and thus is not listed in the structured field groupings. [LINEDATA-A-011]
### Figure 32. Structure of a Presentation Page Object
**Presentation Page**
(BPG, D3A8AF) Begin Page
&nbsp;&nbsp;&nbsp;&nbsp;[ (BRG, D3A8C6) Begin Resource Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (BRS, D3A8CE) Begin Resource
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Resource Object
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(ERS, D3A9CE) End Resource ] (S)
&nbsp;&nbsp;&nbsp;&nbsp;(ERG, D3A9C6) End Resource Group ]
&nbsp;&nbsp;&nbsp;&nbsp;(BAG, D3A8C9) Begin Active Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPO, D3ABD8) Map Page Overlay (S) ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPS, D3B15F) Map Page Segment (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (PGD, D3A6AF) Page Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBD, D3A66B) Object Area Descriptor (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBP, D3AC6B) Object Area Position (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PTD, D3B19B) Presentation Text Descriptor ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPG, D3ABAF) Map Page ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control ]
&nbsp;&nbsp;&nbsp;&nbsp;(EAG, D3A9C9) End Active Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BBC, D3A8EB) Begin Bar Code Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BGR, D3A8BB) Begin Graphics Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BIM, D3A8FB) Begin Image Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BPT, D3A89B) Begin Presentation Text Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BOC, D3A892) Begin Object Container (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IPO, D3AFD8) Include Page Overlay (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IPS, D3AF5F) Include Page Segment (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IOB, D3AFC3) Include Object (S) ]
(EPG, D3A9AF) End Page [LINEDATA-A-012]

**Notes:**
1. An AFP (MO:DCA) presentation page can contain one or more Tag Logical Element (TLE) or Link Logical Element (LLE) structured fields following the AEG. These structures do not affect the presentation of the page. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. [LINEDATA-A-013]
2. **Note †:** required for every IPO specified in a page. [LINEDATA-A-014]
### Figure 33. Structure of Line Format Data
**Line Format Data**
[ (PTX, D3EE9B) Presentation Text Data (S) ]
+ [ (BBC, D3A8EB) Begin Bar Code Object (S) ]
+ [ (BGR, D3A8BB) Begin Graphics Object (S) ]
+ [ (BIM, D3A8FB) Begin Image Object (S) ]
+ [ (BPT, D3A89B) Begin Presentation Text Object (with OEG) (S) ]
+ [ (IPO, D3AFD8) Include Page Overlay (S) ]
+ [ (IPS, D3AF5F) Include Page Segment (S) ]
+ [ (IOB, D3AFC3) Include Object (S) ] [LINEDATA-A-015]

**Note:** These items can appear in any order. [LINEDATA-A-016]

### Figure 34. Structure of a Presentation Text Data Object
**Presentation Text Data Object**
(BPT, D3A89B) Begin Presentation Text Object
&nbsp;&nbsp;&nbsp;&nbsp;[ (BOG, D3A8C7) Begin Object Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPT, D3AB9B) Map Presentation Text ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBD, D3A66B) Object Area Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBP, D3AC6B) Object Area Position
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (PTD, D3B19B) Presentation Text Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control ]
&nbsp;&nbsp;&nbsp;&nbsp;(EOG, D3A9C7) End Object Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;[ (PTX, D3EE9B) Presentation Text Data (S) ]
(EPT, D3A99B) End Presentation Text Object [LINEDATA-A-017]

**Note:** A Presentation Text Descriptor is required in an Active Environment Group when a text object is used in a page. [LINEDATA-A-018]


### Figure 35. Structure of an IM Image Data Object
**IM Image Data Object**
(BII, D3A87B) Begin Image Object IM
&nbsp;&nbsp;&nbsp;&nbsp;+ (IID, D3A67B) Image Input Descriptor IM
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IOC, D3A77B) Image Output Control IM ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (ICP, D3AC7B) Image Cell Position (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ (IRD, D3EE7B) Image Raster Data IM (S)
(EII, D3A97B) End Image Object IM [LINEDATA-A-019]

### Figure 36. Structure of an IO Image Data Object
**IO Image Data Object**
(BIM, D3A8FB) Begin Image Object IO
&nbsp;&nbsp;&nbsp;&nbsp;[ (BOG, D3A8C7) Begin Object Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MIO, D3ABFB) Map IO Image Object ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBD, D3A66B) Object Area Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBP, D3AC6B) Object Area Position
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (IDD, D3A6FB) Image Data Descriptor IO
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;(EOG, D3A9C7) End Object Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;[ (IPD, D3EEFB) Image Picture Data IO (S) ]
(EIM, D3A9FB) End Image Object IO [LINEDATA-A-020]

**Note †:** allowed in FS45 only. [LINEDATA-A-021]

### Figure 37. Structure of a Graphics Data Object
**Graphics Data Object**
(BGR, D3A8BB) Begin Graphics Object
&nbsp;&nbsp;&nbsp;&nbsp;[ (BOG, D3A8C7) Begin Object Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MGO, D3ABBB) Map Graphic Object ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBD, D3A66B) Object Area Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBP, D3AC6B) Object Area Position
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (GDD, D3A6BB) Graphics Data Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control ]
&nbsp;&nbsp;&nbsp;&nbsp;(EOG, D3A9C7) End Object Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;[ (GAD, D3EEBB) Graphics Data (S) ]
(EGR, D3A9BB) End Graphics Object [LINEDATA-A-022]

### Figure 38. Structure of a Bar Code Data Object
**Bar Code Data Object**
(BBC, D3A8EB) Begin Bar Code Object
&nbsp;&nbsp;&nbsp;&nbsp;[ (BOG, D3A8C7) Begin Object Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBD, D3A66B) Object Area Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (OBP, D3AC6B) Object Area Position
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (BDD, D3A6EB) Bar Code Data Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;(EOG, D3A9C7) End Object Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;[ (BDA, D3EEEB) Bar Code Data (S) ]
(EBC, D3A9EB) End Bar Code Object [LINEDATA-A-023]

### Figure 39. Structure of a Page Segment Resource Object
**Page Segment Resource Object**
(BPS, D3A85F) Begin Page Segment
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PTX, D3EE9B) Presentation Text Data (without OEG) (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BGR, D3A8BB) Begin Graphics Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BIM, D3A8FB) Begin Image Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BPT, D3A89B) Begin Presentation Text Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BOC, D3A892) Begin Object Container (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IOB, D3AFC3) Include Object (S) ]
(EPS, D3A95F) End Page Segment [LINEDATA-A-024]

**Note:** This is the structure of an AFP page segment. This structure is supported but is replaced strategically with the MO:DCA page segment. For more information, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. [LINEDATA-A-025]

### Figure 40. Structure of an Overlay Resource Object
**Overlay Resource Object**
(BMO, D3A8DF) Begin Overlay
&nbsp;&nbsp;&nbsp;&nbsp;[ (BRG, D3A8C6) Begin Resource Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (BRS, D3A8CE) Begin Resource
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Resource Object
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(ERS, D3A9CE) End Resource ] (S)
&nbsp;&nbsp;&nbsp;&nbsp;(ERG, D3A9C6) End Resource Group ]
&nbsp;&nbsp;&nbsp;&nbsp;(BAG, D3A8C9) Begin Active Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPS, D3B15F) Map Page Segment (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (PGD, D3A6AF) Page Descriptor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBD, D3A66B) Object Area Descriptor (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBP, D3AC6B) Object Area Position (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PTD, D3B19B) Presentation Text Descriptor ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control ]
&nbsp;&nbsp;&nbsp;&nbsp;(EAG, D3A9C9) End Active Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BBC, D3A8EB) Begin Bar Code Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BGR, D3A8BB) Begin Graphics Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BIM, D3A8FB) Begin Image Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BPT, D3A89B) Begin Presentation Text Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BOC, D3A892) Begin Object Container (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IPO, D3AFD8) Include Page Overlay (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IPS, D3AF5F) Include Page Segment (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IOB, D3AFC3) Include Object (S) ]
(EMO, D3A9DF) End Overlay [LINEDATA-A-026]

**Notes:**
1. An AFP (MO:DCA) overlay object may contain one or more Tag Logical Element (TLE) or Link Logical Element (LLE) structured fields following the AEG. These structures do not affect the presentation of the overlay. For the formal definition of these structures, see the *Mixed Object Document Content Architecture (MO:DCA) Reference*. [LINEDATA-A-027]
2. The MPG and MPO structured fields are not supported in the AEG for an overlay. [LINEDATA-A-028]

### Figure 41. Structure of a Form Definition Resource Object
**Form Definition Resource Object**
(BFM, D3A8CD) Begin Form Map
&nbsp;&nbsp;&nbsp;&nbsp;[ (BDG, D3A8C4) Begin Document Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MFC, D3A088) Medium Finishing Control ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (MDD, D3A688) Medium Descriptor (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PGP, D3B1AF) Page Position ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MSU, D3ABEA) Map Suppression ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MMO, D3B1DF) Map Medium Overlay (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PFC, D3B288) Presentation Fidelity Control ]
&nbsp;&nbsp;&nbsp;&nbsp;(EDG, D3A9C4) End Document Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BMM, D3A8CC) Begin Medium Map (S)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MMD, D3ABCD) Map Media Destination ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MMT, D3AB88) Map Media Type ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PGP, D3B1AF) Page Position (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDD, D3A688) Medium Descriptor ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (MCC, D3A288) Medium Copy Count (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MMO, D3B1DF) Map Medium Overlay ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPO, D3ABD8) Map Page Overlay ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PMC, D3A7AF) Page Modification Control (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MFC, D3A088) Medium Finishing Control (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PEC, D3A7A8) Presentation Environment Control (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;(EMM, D3A9CC) End Medium Map ]
(EFM, D3A9CD) End Form Map [LINEDATA-A-029]

**Note †:** The structured field is required in either the Document Environment Group or the Medium Map. [LINEDATA-A-030]

### Figure 42. Structure of a Page Definition Resource Object
**Page Definition Resource Object**
(BPM, D3A8CB) Begin Page Map
&nbsp;&nbsp;&nbsp;&nbsp;[ (BSG, D3A8D9) Begin Resource Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPO, D3ABD8) Map Page Overlay (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PPO, D3ADC3) Preprocess Presentation Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;(ESG, D3A9D9) End Resource Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;+ (BDM, D3A8CA) Begin Data Map (S)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (BAG, D3A8C9) Begin Active Environment Group
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (PEC, D3A7A8) Presentation Environment Control ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MCF, D3AB8A) Map Coded Font (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MDR, D3ABC3) Map Data Resource (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (MPO, D3ABD8) Map Page Overlay (S) ] (Note †)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PGD, D3A6AF) Page Descriptor ] (S)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBD, D3A66B) Object Area Descriptor (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (OBP, D3AC6B) Object Area Position (text) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (PTD, D3B19B) Presentation Text Descriptor ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(EAG, D3A9C9) End Active Environment Group ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (BDX, D3A8E3) Begin Data Map Transmission Subcase
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ (DXD, D3A6E3) Data Map Transmission Subcase Descriptor ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (LNC, D3AAE7) Line Descriptor Count ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (LND, D3A6E7) Line Descriptor (S+)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (RCD, D3A68D) Record Descriptor (S+)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ (XMD, D3A68E) XML Descriptor (S+)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (FDS, D3AAEC) Fixed Data Size ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (FDX, D3EEEC) Fixed Data Text (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(EDX, D3A9E3) End Data Map Transmission Subcase ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (CCP, D3A7CA) Conditional Processing Control (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ (IOB, D3AFC3) Include Object (S) ]
&nbsp;&nbsp;&nbsp;&nbsp;(EDM, D3A9CA) End Data Map
(EPM, D3A9CB) End Page Map [LINEDATA-A-031]

**Notes:**
1. The Data Map Transmission Subcase may contain RCDs or XMDs instead of LNDs. [LINEDATA-A-032]
2. The Data Maps in a Page Definition must all contain LNDs, RCDs, or XMDs. A mixture is not allowed. (+ indicates these can appear more than once but not in a mixture). [LINEDATA-A-033]
3. A Presentation Text Descriptor (PTD) is required in the AEG when a presentation text object is used on a page. [LINEDATA-A-034]
4. **Note †:** required for every IPO specified in a page. [LINEDATA-A-035]




Copyright © AFP Consortium 1994, 2018 179
