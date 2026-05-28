# Chapter 4. MO:DCA Objects

This chapter:
*   Defines the structure of a MO:DCA print file [MODCA-4-001]
*   Defines the structure of a MO:DCA document [MODCA-4-002]
*   Defines the structure of a MO:DCA index [MODCA-4-003]
*   Defines the structure of a MO:DCA page [MODCA-4-004]
*   Defines the structure of a MO:DCA page group [MODCA-4-005]
*   Describes the resource objects that may be referenced in a MO:DCA document and defines their structure [MODCA-4-006]
*   Describes how resource objects may be carried in resource groups [MODCA-4-007]
*   Defines the structure of print control resource objects [MODCA-4-008]
*   Describes the data objects that may be included in a MO:DCA document and defines their structure [MODCA-4-009]
*   Defines the structure of object containers [MODCA-4-010]

## Object Syntax Structure

This section specifies the syntax used to define MO:DCA objects.

If a structured field that is not identified as being part of the object appears anywhere within the object, a X'40' exception condition exists. If a structured field appears out of the stated order or more than the permitted number of times, a X'20' exception condition exists. If a structured field that is identified as required does not appear within the object, a X'08' exception condition exists.

The conventions used in these structured field groupings are:
*   **( )** The structured field acronym and identifier are shown in parentheses. The presence of dots or periods in the identifier indicates that the item is not a structured field, but instead is a structure, for example a medium map. The structure is composed of an assortment of structured fields, and is defined separately.
*   **[ ]** Brackets indicate optional structured fields. When a structured field is shown without brackets, it must appear between the begin and end structured fields.
*   **+** Plus signs indicate structured fields may appear in any order relative to those that precede or succeed it except when the preceding or succeeding structured field does not have a plus (+) sign. In that case, the order is as listed.
*   **(S)** The enclosed (S) indicates that the structured field may be repeated. When present on a required structured field, at least one occurrence of the structured field is required, but multiple instances of it may occur.
*   **F2** An F2 indicates that the structured field is a format two structured field. See “Structured Field Formats”.

**Note:** The No Operation structured field may appear within any begin-end domain. Therefore, it is not listed in the structured field groupings. [MODCA-4-011]

## Print File

The print file is an object that contains one or more documents to be printed. A print file may also optionally contain an external resource group, also referred to as a print file level resource group, as well as document indexes. Resources carried in a print file level resource group are sometimes referred to as inline resources. [MODCA-4-012]

**Figure 23. Print File Structure**

*   [ Begin Print File (BPF, D3A8A5) ]
*   [ (D3..C6) Resource Group ]
*   (D3..A7/A8) Index + Document (S)
*   [ End Print File (EPF, D3A9A5) ]

**Index + Document Structure**
*   [ (D3..A7) Document Index ]
*   (D3..A8) Document (S)

**Figure 23** shows the interchange form of a MO:DCA print file.

**Warning:** Any other form may cause inconsistent, presentation-system-dependent results.

For a definition of the Resource Group structure, see “Resource Groups”.

**Notes:**
1. The BPF/EPF structured fields are optional as a pair; if one is specified, the other must be specified as well. [MODCA-4-013]
2. Only one BPF/EPF pair is allowed in a print file, and a single Form Map is associated with each print file. [MODCA-4-014]

**Architecture Note:** The BPF / EPF pair is not intended to provide significant additional functionality in and of itself; it is intended to add an explicit wrapper to the existing print file definition. For example, it is not intended to allow trivial print stream concatenation, support multiple inline resource groups, or legalize any other function that is not otherwise allowed without the BPF / EPF wrapper. Simply adding BFP/EPF around an “illegal” print stream (multiple inline resource groups, for example) cannot be used to make it legal or correct, especially in the case of print stream concatenation.

**Application Note:** All operating systems that support printing have the concept of a file that is to be printed. These systems know where the file starts and where it ends. Such a file is often generically referred to as a “physical file”. When a physical file contains AFP data, that file is printed with a single MO:DCA Form Definition. The MO:DCA architecture does not define the relationship between a print file and a physical file. However, AFP consumers, including print servers that process MO:DCA data, should consider a physical file to be a single MO:DCA (AFP) print file that contains at most one BPF/EPF pair and at most one print file level resource group. MO:DCA IS/3 compliant consumers and print servers must treat the physical file in this manner and should generate a presentation-system-specific exception if the physical file contains more than one BPF/EPF pair. This is true even when the physical file is streamed with protocols such as sockets or named pipes. Consult your product documentation for its definition of a physical file and its relationship to a MO:DCA (AFP) print file.

3. The index, as shown in the Index + Document Structure, is optional. When specified, it must precede the document to be indexed and is implicitly tied to that document. Pointers from the index to the document and pointers from the document back to the index are not needed in this case and are ignored. That is, any FQN type X'83'—Begin Document triplet on the BDI is ignored, and any X'98'—Begin Document Index on the BDT is ignored. [MODCA-4-015]
4. Only a single resource group is permitted at the print file level. If multiple resource groups appear before the first document, or if one or more resource groups follow the first document, the treatment of these resource groups is presentation-system-dependent. [MODCA-4-016]
5. A single document index before the inline resource group is accepted by AFP print servers and is implicitly tied to the first document in the print file. However, this format is not compliant with the MO:DCA interchange print file format and its use is discouraged. [MODCA-4-017]
6. Metadata may be associated with a print file and is optional. To specify metadata, one or more metadata objects (MO) may be included within the resource group of the print file. The MO Type object container(s) must directly follow the Begin Resource Group (BRG), otherwise they are ignored. When including multiple MOs the series of object containers must be contiguous and, as a whole, constitutes the metadata for the print file. The MO:DCA architecture places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-018]

## Document

The document is the highest level object in the MO:DCA document component hierarchy. A document is delimited by Begin Document and End Document structured fields.

**Figure 24. Document Structure**

*   Begin Document (BDT, D3A8A8)
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   + [ (IMM, D3ABCC) Invoke Medium Map (S) ]
*   + [ (IPG, D3AFAF) Include Page (S) ]
*   + [ (LLE, D3B490) Link Logical Element (S) ]
*   + [ (D3..CC) Medium Map (S) ]
*   + [ (D3..D9) Resource Environment Group (S) ]
*   + [ (D3..AF) Page (S) ]
*   + [ (D3..AD) Page Group (S) ]
*   End Document (EDT, D3A9A8)

**Architecture Note:** The retired MO:DCA IS/2 interchange set allows an optional Document Index, bounded by BDI/EDI, to occur once directly following BDT. The content of the document index structure is defined in the IS/2 definition; see “Retired Functions”. This structure is still allowed in products that support MO:DCA IS/2.

**Figure 24** shows the general form of a MO:DCA document. MO:DCA interchange sets may specify a more restrictive document structure; however, such a structure must be a proper subset of the general form.

**Notes:**
1. At the beginning of a document, if a document does not invoke a medium map by name, and if it does not include an internal medium map, the first medium map in the selected form map controls the printing. The Media Eject Control (X'45') triplet, which may be included on the Begin Medium Map structured field to specify a partition eject, is ignored when it occurs on the medium map that is activated at the beginning of a document regardless of whether this medium map is explicitly invoked or implicitly invoked as the default. As a result, a sheet-eject is processed when the first medium map in a document is selected to control printing. Note that in Cut-sheet Emulation mode (CSE), this means an eject to the front side of a new sheetlet. [MODCA-4-019]
2. If a medium map is included internal (inline) to the document, it is activated by immediately following it with an IMM that explicitly invokes it; otherwise, the internal medium map is ignored. An IMM that does not follow an internal medium map may not invoke an internal medium map elsewhere in the document and is assumed to reference a medium map in the processing system's form map. [MODCA-4-020]
3. A page that is included with an IPG in document state may be indexed using an offset to the location of the IPG in the document. [MODCA-4-021]
4. A Resource Environment Group (REG) maps some of the resources required to present the pages that follow. Resources mapped in a REG must still be mapped in the AEG for the page that uses the resources. The scope of the resource mapping in the REG is from the point where it occurs up to the next REG, which is a complete replacement for the current REG, or the end of the document, whichever occurs first. [MODCA-4-022] [MODCA-4-023]
5. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the document structure. The MO Type object container(s) must directly follow the Begin Document (BDT), otherwise they are ignored. When including multiple MOs the series of object containers must be contiguous and, as a whole, constitute the metadata for the document. The MO:DCA architecture places no restriction on or significance to the sequence or order of included metadata. If an object container is specified in document state, the ObjClass parameter on the mandatory Object Classification (X'10') triplet must be set to X'50'—Metadata; otherwise, the object container is ignored. [MODCA-4-024]

**Application Notes:**
1. Internal (inline) medium maps are not supported by all AFP print servers; consult your product documentation. [MODCA-4-025]
2. The use of internal medium maps may significantly decrease document processing throughput, especially if the internal Medium Map specifies conditional media ejects using the Media Eject Control (X'45') triplet. [MODCA-4-026]
3. For optimum performance a REG is normally placed at the beginning of the document before the first page. [MODCA-4-027]
4. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-028] [MODCA-4-029]

## Document Index

A document index is an object that provides functions for indexing the document based on document structure and on application-defined document tags. A document index is delimited by Begin Document Index and End Document Index structured fields.

A document index is used for informational purposes only. Parameters in a document index are descriptive in nature and do not provide presentation specifications.

**Figure 25. Document Index Structure**

*   Begin Document Index (BDI, D3A8A7)
*   + (IEL, D3B2A7) Index Element (S)
*   + [ (LLE, D3B490) Link Logical Element (S) ]
*   + [ (TLE, D3A090) Tag Logical Element (S) ]
*   End Document Index (EDI, D3A9A7) [MODCA-4-030]

## Resource Environment Group

A resource environment group (REG) is associated with a document or a group of pages in a document. It is contained in the document's begin-end envelope in the data stream. The REG is used to identify complex resources, such as high-resolution color images, that need to be downloaded to the presentation device before the pages that follow are processed. The scope of a REG is the pages that follow, up to the next REG, which is a complete replacement for the current REG, or the end of the document, whichever occurs first. The mapping of resources in a REG is optional. Resources mapped in a REG must still be mapped in the AEG for the page that uses the resources. When more than one REG is specified in a document, each REG is a complete replacement for the preceding REG.

**Figure 26. Resource Environment Group Structure**

*   Begin Resource Environment Group (BSG, D3A8D9)
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   [ (MPO, D3ABD8) Map Page Overlay (S) ]
*   [ (PPO, D3ADC3) Preprocess Presentation Object (S) ] [MODCA-4-031]
*   End Resource Environment Group (ESG, D3A9D9)

**Notes:**
1. When an MDR is specified in a REG, the FQN type X'BE' triplet, which specifies the internal identifier used to reference the resource being mapped, is ignored. An example of an internal identifier is the local ID used to reference a data-object font in a PTOCA object. The assignment of internal identifier to resource name is made when the MDR is specified in the environment group of the object that uses the resource. For example, in the case of a data-object font used in a PTOCA object, the internal identifier of the font is mapped to the font name in the AEG of the page. If the data-object font is used in an AFP GOCA object or a BCOCA object, the internal identifier of the font is mapped to the resource name in the OEG of the object. [MODCA-4-032]
2. There is no correlation between MPO Resource Local IDs (LIDs) in an AEG and MPO LIDs in an REG. For example, an MPO in an AEG can use LID x, and an MPO for the same overlay in a REG can use LID x or a different LID. The only restriction is that regardless of where the MPO is specified, it is not permissible within a given MPO to map the same LID to more than one overlay. [MODCA-4-033]
3. An MDR reference to a specific resource may only be specified once in the REG. [MODCA-4-034]
4. Any object specified for preprocessing in a PPO must first be mapped in an MDR or an MPO in the same REG. This includes secondary resources that are specified in the PPO and that are required by the object to be preprocessed. [MODCA-4-035]
5. When an MDR in the REG is used to map a Color Management Resource (CMR), the processing mode, as specified in the mandatory CMR Descriptor (X'91') triplet, is downloaded along with the CMR and is used by the presentation device. However, the CMR scope, which is also specified in the CMR Descriptor triplet, is ignored and must be established in an ensuing mapping of the same CMR with the same processing mode at the page/sheet group (Medium Map) level, page/overlay level, or data object level. [MODCA-4-036]

**Application Note:** For optimum performance a REG is normally placed at the beginning of the document before the first page. [MODCA-4-037]

## Page

A page is an object that contains the data objects to be presented. A page establishes its own environment and is independent of any other page in the document. A page is delimited by Begin Page and End Page structured fields. A MO:DCA page object has the following syntax structure:

**Figure 27. Page Structure**

*   Begin Page (BPG, D3A8AF)
*   (D3..C9) Active Environment Group
*   + [ (IOB, D3AFC3) Include Object (S) ]
*   + [ (IPG, D3AFAF) Include Page ]
*   + [ (IPO, D3AFD8) Include Page Overlay (S) ]
*   + [ (IPS, D3AF5F) Include Page Segment (S) ]
*   + [ (LLE, D3B490) Link Logical Element (S) ]
*   + [ (TLE, D3A090) Tag Logical Element (S) ]
*   + [ (D3..EB) Bar Code Object (S) ]
*   + [ (D3..BB) Graphics Object (S) ]
*   + [ (D3..FB) Image Object (S) ]
*   + [ (D3..92) Object Container (see Note 13 for MO) (S) ]
*   + [ (D3..9B) Presentation Text Object (S) ]
*   End Page (EPG, D3A9AF)

**Active Environment Group (AEG)**
*   Begin Active Environment Group (BAG, D3A8C9) [MODCA-4-038]
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   [ (MCF, D3AB8A) Map Coded Font F2 (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   [ (MPG, D3ABAF) Map Page ]
*   [ (MPO, D3ABD8) Map Page Overlay (S) ]
*   [ (MPS, D3B15F) Map Page Segment (S) ]
*   (PGD, D3A6AF) Page Descriptor
*   [ (OBD, D3A66B) Object Area Descriptor ]
*   [ (OBP, D3AC6B) Object Area Position ]
*   (PTD, D3B19B) Presentation Text Data Descriptor F2
*   End Active Environment Group (EAG, D3A9C9)

**Architecture Note:** The retired MO:DCA IS/2 interchange set allowed an optional Resource Group, bounded by BRG/ERG, to occur once directly following BPG. The content of the resource group structure is defined in the IS/2 definition; see “Retired Functions”. This structure is still allowed in products that support MO:DCA IS/2.

**Figure 27** shows the general form of a MO:DCA page object. MO:DCA interchange sets may specify a more restrictive page structure; however, such a structure must be a proper subset of the general form.

**Notes:**
1. The presentation text object in MO:DCA documents can have two structures that differ by the presence or absence of an optional Object Environment Group (OEG). When the presentation text object does not contain an OEG, some of the presentation parameters normally specified in the OEG are specified in the Active Environment Group (AEG) of the containing page. [MODCA-4-039]
2. The OBD and OBP structured fields in the AEG for the page are only used for presentation text objects that do not contain an Object Environment Group (OEG), in which case they are optional. MO:DCA interchange sets require that the OBD specify measurement units and extents that match those specified for the page in the PGD. If the OBD is omitted, the architected default is to use the measurement units and extents specified in the PGD for the text object area measurement units and object area extents. MO:DCA interchange sets also require that the OBP specifies zeros for the object area origin and the object content origin and that it specifies a 0° object area rotation. If the OBP is omitted, the architected default is to set the object area origin and the object content origin to zeros, and the object area rotation to 0°. [MODCA-4-040]
3. The PTD structured field in the AEG for the page is only used when the page contains one or more presentation text objects that do not contain an Object Environment Group (OEG), in which case it is mandatory. When the PTD is included in the AEG for a page, some AFP print servers require that the measurement units in the PTD match the measurement units in the Page Descriptor (PGD). It is therefore strongly recommended that whenever the PTD is included in the AEG, the same measurement units are specified in both the PTD and PGD. [MODCA-4-041]
4. If a presentation text object that does not contain an OEG specifies a font other than the presentation environment default font, the font local ID must be mapped to a font global name with an MCF or MDR structured field in the AEG for the page. This mapping must be unique, that is, the font local ID can only be mapped to one font in the AEG. However different font local IDs can be mapped to the same font. For rules on mapping local IDs (LIDs) to resource identifiers such as font global names, see “Environment Hierarchies”. [MODCA-4-042]
5. If a presentation text object contains an OEG, each MCF or MDR that maps a font in the text object's OEG must have a corresponding MCF or MDR mapping the same font in the AEG for that page. Local ID X'FE' may be used for such font mappings in the AEG to distinguish them from font mappings for presentation text objects without OEG. [MODCA-4-044]
6. If a presentation object container is included directly in a page, it must specify, at minimum, BOC/EOC, an OEG with OBD, OBP, CDD, and the object data must be carried in OCDs. If the object container data object is metadata (MO) then the object container must follow the Active Environment Group (AEG). [MODCA-4-045]
7. When an IPG structured field occurs in a page, the bit map for the referenced page is merged with the data defined for the current page. The referenced page must be mapped in the AEG for the current page and must not contain another IPG. Only a single IPG may occur within a page. [MODCA-4-046]
8. When an IPG occurs in a page, the included page becomes a part of the containing page, therefore only the containing page may be indexed using an offset to its location in the document. [MODCA-4-047]
9. For purposes of print server resource management, each MDR that is specified in an object container OEG must have a corresponding MDR mapping the same resource in the AEG for that page. Note that an FQN type X'BE' triplet, if specified on the MDR in the OEG, is not factored up to the AEG, unless the MDR maps a data-object font. [MODCA-4-048]
10. An MDR reference to a specific resource may only be specified once in the AEG. Note that an MDR reference to a specific CMR resource done using an FQN type X'DE' triplet and an MDR reference to the same CMR resource done using an FQN type X'EE' triplet are considered separate and can both be specified in the same AEG. [MODCA-4-049]
11. The retired MO:DCA IS/2 interchange set supports a resource group following BPG, called an internal resource group or a page level resource group, see “Retired Interchange Set”. [MODCA-4-050]
12. The PEC structured field in the AEG for the page is only used to specify the rendering intent for the page using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-051]
13. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the page structure. The MO Type object container(s) must directly follow the Active Environment Group (AEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the document. The MO:DCA architecture places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-052]

MO:DCA data streams support IM image objects on a page for migration purposes. One or more IM image objects may be included on a page in the same manner that IO image objects are included on a page. Both forms of image may coexist on the same page. For a definition of the IM image object, see Appendix C.

MO:DCA data streams support the Map Coded Font format-1 (MCF-1) structured field in the AEG for migration purposes. An MCF-1 may appear in place of an MCF format-2 (MCF-2) structured field. If both MCF-1 and MCF-2 structured fields are in the same environment group, the MCF-1 structured fields must precede the MCF-2 structured fields. For a definition of the MCF-1 structured field, see Appendix C.

**Application Notes:**
1. For purposes of print server resource management, each MCF or MDR that maps a font in a data object OEG must have a corresponding MCF or MDR mapping the same font in the AEG for that page. The local ID used in the page AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-053]
2. For purposes of print server resource management, each overlay included on a page with an IPO must first be mapped to a local ID with an MPO in the AEG for that page. [MODCA-4-054]
3. A page segment included on a page with an IPS may optionally be mapped with an MPS in the AEG for that page. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment is sent to the presentation device as part of the page and is called a soft page segment. [MODCA-4-055]
4. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-056] [MODCA-4-057]

## Page Group

A page group object is a named set of sequential pages in a document. All pages in a page group inherit the attributes and processing characteristics that are assigned to the page group. A page group is delimited by Begin Named Page Group and End Named Page Group structured fields.

**Figure 28. Page Group Structure**

*   Begin Named Page Group (BNG, D3A8AD)
*   [ (TLE, D3A090) Tag Logical Element (S) ]
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   + [ (IMM, D3ABCC) Invoke Medium Map (S) ]
*   + [ (IPG, D3AFAF) Include Page (S) ]
*   + [ (LLE, D3B490) Link Logical Element (S) ]
*   + [ (D3..CC) Medium Map (S) ]
*   + [ (D3..D9) Resource Environment Group (S) ]
*   + [ (D3..AF) Page (S) ]
*   + [ (D3..AD) Page Group (S) ]
*   End Named Page Group (ENG, D3A9AD)

**Figure 28** shows the general form of a MO:DCA page group object. MO:DCA interchange sets may specify a more restrictive page group structure; however, such a structure must be a proper subset of the general form.

**Notes:**
1. If a medium map is included internal (inline) to the document, it is activated by immediately following it with an IMM that explicitly invokes it, otherwise the internal medium map is ignored. An IMM that does not follow an internal medium map may not invoke an internal medium map elsewhere in the document and is assumed to reference a medium map in the processing system's form map. [MODCA-4-058]
2. A page that is included with an IPG in page-group state may be indexed using an offset to the location of the IPG in the document. [MODCA-4-059]
3. A resource environment group (REG) maps some of the resources required to present the pages that follow. Resources mapped in a REG must still be mapped in the AEG for the page that uses the resources. The scope of the resource mapping in the REG is from the point where it occurs up to the next REG, which is a complete replacement for the current REG, or the end of the document, whichever occurs first. [MODCA-4-060]
4. If the Keep Group Together (X'9D') triplet is specified on a BNG, that page group is subject to special nesting rules. See the “Begin Named Page Group (BNG)” Semantics section for details. [MODCA-4-061]
5. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the page group structure. The MO Type object container(s) must directly follow the one or more optional Tag Logical Elements (TLE), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the page group. The MO:DCA architecture places no restriction on or significance to the sequence or order of included metadata. If an object container is specified in page group state (other than part of an included page), the ObjClass parameter on the mandatory Object Classification (X'10') triplet must be set to X'50'—Metadata; otherwise, the object container is ignored. [MODCA-4-062]

**Application Notes:**
1. Internal (inline) medium maps are not supported by all AFP print servers; consult your product documentation. [MODCA-4-063]
2. The use of internal medium maps may significantly decrease document processing throughput, especially if the internal Medium Map specifies conditional media ejects using the Media Eject Control (X'45') triplet. [MODCA-4-064] [MODCA-4-065]
3. Page groups are often processed in standalone fashion; that is, they are indexed, retrieved, and presented outside the context of the containing document. While the pages in the group are independent of each other and of any other pages in the document, their formatting on media depends on when the last medium map was invoked and on how many pages precede the BNG since this invocation. To make the media formatting of page groups self-contained, a Medium Map should be invoked at the beginning of the page group between the Begin Named Group (BNG) structured field and the first Begin Page (BPG) structured field. If this is not done, the presentation system may need to “play back” all pages between the invocation of the active medium map and the BNG to determine media formatting such as sheet-side and partition number for the first page in the group. [MODCA-4-066]

It is therefore strongly recommended that in environments where standalone page group processing is required or anticipated, page groups are built with an Invoke Medium Map (IMM) structured field specified after the BNG and before the first BPG.

A newer method to specify how a page or page group should be formatted involves use of the Page Position Information (X'81') triplet. This triplet may be specified on a BPG and indicates the repeating group in the PGP structured field in the active medium map that should be used to format the page.

4. For optimum performance a REG is normally placed at the beginning of the document before the first page. [MODCA-4-067]
5. Some AFP applications that generate page groups will support a user option that ensures that an IMM is specified after BNG and before the first BPG, and some AFP archive servers will expect an IMM there and may not present the page group correctly if none is found. However, note that this may cause the complete document to print differently. [MODCA-4-068]
6. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-069] [MODCA-4-070]
## Resource Objects

Objects are considered to be resource objects when they are explicitly referenced from the document instead of being directly included in the document. Resource objects may reside in external resource libraries, or in print file level resource groups. Note that data objects such as IOCA image objects and object containers become resource objects when included with an Include Object (IOB) structured field.

**Architecture Note:** Any presentation object, other than an overlay, when processed as a resource, must not contain font mappings defined with Map Coded Font (MCF) structured fields in the object environment group. A presentation object is processed as a resource when it is mapped using a Map structured field and included using an Include structured field.

## Font Objects

A font is a collection of graphic characters with the same type family, typeface, and size. Fonts are referenced by MO:DCA documents for presenting text.

### Font Object Content Architecture (FOCA) Fonts

The Font Object Content Architecture (FOCA) defines a font format that is supported in MO:DCA documents. Such fonts are referenced in the data stream using an MCF structured field. This font format defines three types of font objects:

*   Coded font objects [MODCA-4-071]
*   Code page objects [MODCA-4-072]
*   Font character set objects [MODCA-4-073]

Each object is bounded by begin and end structured fields that are registered as private structured fields in the MO:DCA architecture. The content of each object is carried in structured fields that are also registered as private structured fields in the MO:DCA architecture. For a description of these objects and their structured fields, see the Font Object Content Architecture Reference. [MODCA-4-074]

### TrueType/OpenType Fonts

TrueType and OpenType fonts are non-FOCA fonts, also called data-object fonts, that are supported in MO:DCA documents. They are referenced in the data stream using an MDR structured field. They can be installed in a resource library in their native, unaltered format, or they can be carried in a print file level resource group in an object container. Collections of TrueType or OpenType fonts, called TrueType Collections, are also supported.

The TrueType font format is based on scalable outline technology with flexible hinting. Mathematically, TrueType shapes are based on quadratic curves; this is in contrast to Adobe® Type 1 outlines which are based on cubic curves. TrueType is an open font standard and is widely published. The technology is described in the following documents available from the Microsoft® and Apple® web sites:

*   TrueType Font Files Technical Specification (Microsoft Corporation) [MODCA-4-075]
*   TrueType Reference Manual (Apple Computer, Inc.) [MODCA-4-076]

The OpenType font format is an extension of the TrueType font format that allows better support for international character sets and broader multi-platform support. OpenType defines tables that can be used to carry the formatting information needed to fully support Unicode. Additionally, this format allows either TrueType or Adobe Type 1 outlines to be packaged as an OpenType font. The OpenType font format was developed jointly by the Adobe and Microsoft Corporations, and it is described in the OpenType Specification, which is available on the Microsoft web site. [MODCA-4-077]

## Overlay Objects

An overlay is a MO:DCA resource object. It may be stored in an external resource library or it may be carried in a resource group. An overlay is similar to a page in that it defines its own environment and carries the same data objects.

**Figure 29. Overlay Structure**

*   Begin Overlay (BMO, D3A8DF)
*   (D3..C9) Active Environment Group
*   + [ (LLE, D3B490) Link Logical Element (S) ]
*   + [ (TLE, D3A090) Tag Logical Element (S) ]
*   + [ (D3..EB) Bar Code Object (S) ]
*   + [ (D3..BB) Graphics Object (S) ]
*   + [ (D3..FB) Image Object (S) ]
*   + [ (D3..9B) Presentation Text Object (S) ]
*   + [ (D3..92) Object Container (see Note 10 for MO) (S) ]
*   + [ (IOB, D3AFC3) Include Object (S) ]
*   + [ (IPS, D3AF5F) Include Page Segment (S) ]
*   End Overlay (EMO, D3A9DF)

**Active Environment Group (AEG)**

*   Begin Active Environment Group (BAG, D3A8C9)
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   [ (MCF, D3AB8A) Map Coded Font F2 (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   [ (MPS, D3B15F) Map Page Segment (S) ]
*   (PGD, D3A6AF) Page Descriptor
*   [ (OBD, D3A66B) Object Area Descriptor ]
*   [ (OBP, D3AC6B) Object Area Position ]
*   (PTD, D3B19B) Presentation Text Data Descriptor F2
*   End Active Environment Group (EAG, D3A9C9)

**Figure 29** shows the general form of a MO:DCA overlay object. MO:DCA interchange sets may specify a more restrictive overlay structure; however, such a structure must be a proper subset of the general form.

**Notes:**
1. The presentation text object in MO:DCA documents can have two structures that differ by the presence or absence of an optional Object Environment Group (OEG). When the presentation text object does not contain an OEG, some of the presentation parameters normally specified in the OEG are specified in the Active Environment Group (AEG) of the containing overlay. [MODCA-4-078]
2. The OBD and OBP structured fields in the AEG for the overlay are only used for presentation text objects that do not contain an Object Environment Group (OEG), in which case they are optional. MO:DCA interchange sets require that the OBD specify measurement units and extents that match those specified for the overlay in the PGD. If the OBD is omitted, the architected default is to use the measurement units and extents specified in the PGD for the text object area measurement units and object area extents. MO:DCA interchange sets also require that the OBP specifies zeros for the object area origin and the object content origin and that it specifies a 0° object area rotation. If the OBP is omitted, the architected default is to set the object area origin and the object content origin to zeros, and the object area rotation to 0°. [MODCA-4-079]
3. The PTD structured field in the AEG for the overlay is only used when the overlay contains one or more presentation text objects that do not contain an Object Environment Group (OEG), in which case it is mandatory. When the PTD is included in the AEG for an overlay, some AFP print servers require that the measurement units in the PTD match the measurement units in the Page Descriptor (PGD). It is therefore strongly recommended that whenever the PTD is included in the AEG, the same measurement units are specified in both the PTD and PGD. [MODCA-4-080] [MODCA-4-081]
4. If a presentation text object that does not contain an OEG specifies a font other than the presentation environment default font, the font local ID must be mapped to a font global name with an MCF or MDR structured field in the AEG for the overlay. This mapping must be unique, that is, the font local ID can only be mapped to one font in the AEG. However different font local IDs can be mapped to the same font. For rules on mapping local IDs (LIDs) to resource identifiers such as font global names, see “Environment Hierarchies”. [MODCA-4-082]
5. If a presentation text object contains an OEG, each MCF or MDR that maps a font in the text object's OEG must have a corresponding MCF or MDR mapping the same font in the AEG for that overlay. Local ID X'FE' may be used for such font mappings in the AEG to distinguish them from font mappings for presentation text objects without OEG. [MODCA-4-083]
6. If a presentation object container is included directly in an overlay, it must specify, at minimum, BOC/EOC, an OEG with OBD, OBP, CDD, and the object data must be carried in OCDs. See “Object Containers” for a complete definition of the object container structure. If the object container data object is metadata (MO) then the object container must follow the Active Environment Group (AEG). [MODCA-4-084]
7. For purposes of print server resource management, each MDR that is specified in an object container OEG must have a corresponding MDR mapping the same resource in the AEG for that overlay. Note that an FQN type X'BE' triplet, if specified on the MDR in the OEG, is not factored up to the AEG, unless the MDR maps a data-object font. [MODCA-4-085]
8. An MDR reference to a specific resource may only be specified once in the AEG. Note that an MDR reference to a specific CMR resource done using an FQN type X'DE' triplet and an MDR reference to the same CMR resource done using an FQN type X'EE' triplet are considered separate and can both be specified in the same AEG. [MODCA-4-086]
9. The PEC structured field in the AEG for the overlay is only used to specify the rendering intent for the overlay using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-087]
10. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the overlay structure. The MO Type object container(s) must directly follow the Active Environment Group (AEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the overlay. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-088]

MO:DCA data streams support IM image objects on an overlay for migration purposes. One or more IM image objects may be included on an overlay in the same manner that IO image objects are included on an overlay. Both forms of image may coexist on the same overlay. For a definition of the IM image object, see Appendix C, “MO:DCA Migration Functions”.

MO:DCA data streams support the Map Coded Font format-1 (MCF-1) structured field in the AEG for migration purposes. An MCF-1 may appear in place of an MCF format-2 (MCF-2) structured field. If both MCF-1 and MCF-2 structured fields are in the same environment group, the MCF-1 structured fields must precede the MCF-2 structured fields. For a definition of the MCF-1 structured field, see Appendix C, “MO:DCA Migration Functions”.

**Application Notes:**
1. For purposes of print server resource management, each MCF or MDR that maps a font in a data object OEG must have a corresponding MCF or MDR mapping the same font in the AEG for that overlay. The local ID used in the overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-089]
2. A page segment included on an overlay with an IPS may optionally be mapped with an MPS in the AEG for that overlay. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment is sent to the presentation device as part of the overlay and is called a soft page segment. [MODCA-4-090] [MODCA-4-091]
3. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-092]

## Page Segment Objects

A page segment is a MO:DCA resource object. It may be stored in an external resource library or it may be carried in a resource group. Page segments contain any combination of the following data objects:

*   Image objects in IOCA format [MODCA-4-093]
*   Graphics objects in GOCA format [MODCA-4-094]
*   Bar code objects in BCOCA format [MODCA-4-095]

A page segment does not define a presentation space and has no coordinate system, therefore objects cannot be positioned relative to each other within a page segment. Instead, all objects in a page segment must specify an object area offset of zero. Objects within the page segment may be positioned on the including page or overlay at a reference point specified by the IPS structured field, or they may be positioned at the including page or overlay origin. This positioning is specified by the Reference Coordinate System parameter in the object's Object Area Position (OBP) structured field.

**Application Note:** A page segment included on a page or overlay with an IPS may optionally be mapped with an MPS in the AEG for that page or overlay. If such a mapping exists, the page segment is sent to the presentation device as a separate object and is called a hard page segment. If such a mapping does not exist, the page segment is sent to the presentation device as part of the page or overlay and is called a soft page segment.

A page segment resource object does not contain an active environment group and therefore does not define its own environment. Instead, the page segment assumes the environment definition of the including page or overlay.

**Figure 30. Page Segment Structure**

*   Begin Page Segment (BPS, D3A85F)
*   + [ (D3..EB) Bar Code Object (S) ]
*   + [ (D3..BB) Graphics Object (S) ]
*   + [ (D3..FB) Image Object (S) ]
*   End Page Segment (EPS, D3A95F)

MO:DCA supports the AFP Page Segment object for migration purposes. For a definition of this object, see “AFP Page Segment”.

**Application Note:** For hard page segments included via IPS, the OEGs for all objects in the page segment must not contain any secondary resource mappings using MCF or MDR structured fields; such mappings are ignored. For page segments included via IOB, which are always processed as soft page segments, the OEGs for all objects in the page segment can only contain secondary resource mappings using MCFs to map FOCA fonts and MDRs to map data-object fonts (TrueType/OpenType fonts); all other secondary resource mappings are ignored. [MODCA-4-096]
## Resource Groups

A resource group is an object that contains a collection of resource objects, including:

*   Overlays [MODCA-4-097]
*   Page segments [MODCA-4-098]
*   Fonts
*   Form maps [MODCA-4-099]
*   Referenced data objects [MODCA-4-100]
*   Object containers [MODCA-4-101]
*   Color Mapping Table (CMT) [MODCA-4-102]

A resource group may be located in the print file, in which case it is called an external or print file level resource group. Resources that are carried in a resource group are said to be inline. A resource group is delimited by Begin Resource Group and End Resource Group structured fields.

**Architecture Note:** The retired MO:DCA IS/2 interchange set allowed an optional Resource Group, bounded by BRG/ERG, to occur once directly following BPG. The content of the resource group structure is defined in the IS/2 definition; see “Retired Functions”. This structure is still allowed in products that support MO:DCA IS/2.

The scope of a resource group is the object or component that contains the resource group. That is, the resources within the resource group are available for use by the presentation system only for the duration of the containing object or component. For example, when a resource group is specified as part of a print file, that is, when it is specified as an external resource group, the resources within the group are available only for the duration of the print file. Once the last document in the print file has been processed, these resources are no longer available to the presentation system for use with another print file.

The general search order for MO:DCA resources is as follows:

1. Print file level resource group [MODCA-4-103]
2. External resource libraries [MODCA-4-104]

Within a resource group, resource objects of the same type must have unique identifiers; if they do not, the treatment of such resources is presentation-system-dependent.

**Figure 31. External (Print file level) Resource Group Structure**

*   Begin Resource Group (BRG, D3A8C6)
*   + [ (D3..DF) Overlay (S) ]
*   + [ (D3..5F) Page Segment (S) ]
*   + [ (D3..CD) Form Map (S) ]
*   + [ (D3..EB) Bar Code Object (S) ]
*   + [ (D3..BB) Graphics Object (S) ]
*   + [ (D3..FB) Image Object (S) ]
*   + [ (D3..92) Object Container (S) ]
*   + [ (D3..9B) Presentation Text Object (S) ]
*   [ (D3..A8) Document (S) ]
*   End Resource Group (ERG, D3A9C6)

**Notes:**
1. In AFP environments, resources carried in print file level (external) resource groups are called inline resources. [MODCA-4-105]
2. The retired MO:DCA IS/2 interchange set allowed an optional Resource Group, bounded by BRG/ERG, to occur once directly following BPG. The content of the resource group structure is defined in the IS/2 definition; see “Retired Functions”. This structure is still allowed in products that support MO:DCA IS/2. [MODCA-4-106]
3. If an object container is included in a resource group, it must at a minimum be bounded by a BOC/EOC pair, an Object Classification (X'10') triplet must be specified on the BOC with a registered object-type identifier (encoded object-type OID) for the object data, and the data must be carried in OCDs. [MODCA-4-107]
4. Within a resource group, resource objects of the same type must have unique identifiers. [MODCA-4-108]
5. Documents are carried as resource objects in a resource group so that pages in these documents can be processed and saved in the presentation device for fast subsequent retrieval using Include Page (IPG) structured fields. [MODCA-4-109]
6. The only presentation text objects supported in this structure are those that include an Object Environment Group (PTOCA with OEG). [MODCA-4-110]

In AFP environments, each resource object in an external resource group must be wrapped with a Begin Resource (BRS) and End Resource (ERS) envelope as shown in Figure 32.

**Figure 32. BRS/ERS Envelope for Resources in External (Print File Level) Resource Group**

*   [ (BRS, D3A8CE) Begin Resource ]
*   (D3..xx) Resource Object
*   [ (ERS, D3A9CE) End Resource ]

The BRS and ERS structured fields must be specified as a pair, that is, one may not be specified without the other.

**Application Note:** In AFP environments, the following objects may also be included in print file level (external) resource groups:

*   Page Maps (also called Page Definitions or PageDefs) [MODCA-4-111]
*   FOCA font objects [MODCA-4-112]
    *   Coded fonts
    *   Code pages
    *   Font character sets

For a description of Page Maps, see the Advanced Function Presentation: Programming Guide and Line Data Reference. For a description of FOCA font objects, see the Font Object Content Architecture Reference.
## External Resource Naming Conventions

MO:DCA objects can be named using one of the following two formats:

*   **Token name.** This name is specified using a fixed-length 8-byte parameter on Begin, Invoke, Map, and Include structured fields. [MODCA-4-113]
*   **Fully qualified name.** This name can be up to 250 bytes long and is specified using the Fully Qualified Name (FQN) X'02' triplet on Begin, Map, and Include structured fields, as well as on object-processing structured fields. For names, the FQNFmt parameter on this triplet is set to X'00' to specify a character string format, and the FQNType parameter specifies how the name is used. When a fully qualified name is specified using FQNType X'01' on a Begin structured field, it overrides any token name that may have been specified on the structured field. The length of the name is determined by the length of the triplet, and all bytes in the triplet are considered to be part of the name. [MODCA-4-114]

MO:DCA object names are encoded using the code page and character set specified in a Coded Graphic Character Set Global ID X'01' triplet, except in those cases where the name defines a fixed encoding. Examples of such cases are the Code Page, Font Character Set, and Coded Font names carried in the FQN type X'85', X'86', and X'8E' triplets, respectively, which define a fixed EBCDIC encoding. The X'01' triplet can specify the encoding in two forms; use of the Coded Character Set Identifier (CCSID) form is recommended. For a definition of the X'01' triplet and its scope in the document hierarchy, see “Coded Graphic Character Set Global Identifier Triplet X'01'”. The X'01' triplet is mandatory on the Begin Document (BDT) structured field and may be specified on most MO:DCA structured fields that contain character data such as an object name. Careful specification of code page and character set is essential for interchange since the system defaults for code page and character set may vary from one system environment to another. [MODCA-4-115]

**Application Note:** In AFP environments, print servers treat an external object name—other than a TrueType or OpenType full font name—as a resource library member name and attempt to process a resource library member with the same name. This means that the external names are subject to the system-imposed file naming rules.

To ensure portability across all AFP platforms, external object names other than TrueType or OpenType full font names must be composed according to the following conventions:

*   Names consist only of the following characters: A-Z, 0-9, $, #, @. When the object name is specified using the fixed-length 8-byte token name parameter, different systems impose additional constraints: [MODCA-4-116]
    *   Systems that use fixed 8-byte file names require the complete 8-byte token name parameter, even if padded with space (X'40' in the EBCDIC encoding) or null (X'00') characters, match the name of the resource, whether the resource is located in an inline resource group or a resource library.
    *   Systems that can use fewer than 8-byte resource names may require padding bytes be stripped from the 8-byte token name field. Some systems expect the space character to be used for padding; other systems may also accept the null code point for padding.
*   To ensure portability across older versions of print servers that do not support encoding definitions in the X'01' triplet, names use only the recommended characters and are encoded in EBCDIC using code page 500 and a character set that includes the above-mentioned characters, such as character set 697. With this encoding, the code points for the characters are: [MODCA-4-117]
    *   A–I (code points X'C1'–X'C9')
    *   J–R (code points X'D1'–X'D9')
    *   S–Z (code points X'E2'–X'E9')
    *   0–9 (code points X'F0'–X'F9')
    *   $, #, and @ (code points X'5B', X'7B', and X'7C' respectively)

Note that such older print servers normally assume this EBCDIC encoding as the default encoding for the document. This EBCDIC encoding can be identified with CCSID 500, which represents the combination of code page 500 and character set 697.

TrueType and OpenType full font names specified in the MDR structured field are not restricted to these characters and may be encoded as required by the AFP-generating application. However, since these names are used to search inline font containers and Resource Access Tables (RATs) which use a fixed UTF-16BE encoding for full font names, efficiency is gained if the full font names in the MDR are also encoded in UTF-16BE. This avoids an encoding conversion. The UTF-16BE encoding can be identified with CCSID 1200. This encoding needs to be specified with a X'01' triplet on the MDR that specifies the full font name.

**Application Note:** To optimize print performance, it is strongly recommended that the same encoding scheme be used for a resource reference wherever in a print file that resource reference is specified. That is, the encoding scheme used for the resource include, the resource map, and the resource wrapper should be the same. For TrueType/OpenType fonts, optimal performance can be achieved by using UTF-16BE as the encoding scheme.

## Print Control Objects

Print control objects are resource objects that are used to control the presentation of pages on physical media, also known as forms or sheets, in a printer. There are two types of print control objects, form maps, also known as form definitions or formdefs, and medium maps, also known as copy groups.

## Form Map

A form map is a print control resource object that consists of: [MODCA-4-118]

*   An optional document environment group (DEG) that defines the print environment for the form map [MODCA-4-119]
*   One or more medium map resource objects that are invokable on document and page boundaries and that specify a complete set of print controls. The name assigned to each medium map object is unique within the form map [MODCA-4-120]

A form map is selected for controlling print file presentation when the print request for the print file is generated. The scope of a form map is a print file, and control for presentation starts with the first medium map in the form map. If the form map is associated with a print file that contains multiple documents, control for presentation is returned to the first medium map in the form map whenever a new document is encountered.

**Figure 33. Form Map Structure**

*   Begin Form Map (BFM, D3A8CD)
*   [ (D3..C4) Document Environment Group ]
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   (D3..CC) Medium Map (S)
*   End Form Map (EFM, D3A9CD)

### Document Environment Group

The document environment group (DEG), when present, establishes the presentation environment for a form map resource object. This presentation environment consist of the following:

*   A definition of the medium presentation space, including units of measure, size, and orientation [MODCA-4-121]
*   The default position of the logical page on the medium presentation space [MODCA-4-122]
*   A mapping of overlay local IDs, as specified in a medium map in the form map, to overlay names [MODCA-4-123]
*   A mapping of text suppression local IDs, as specified in a medium map in the form map, to text suppression names [MODCA-4-124]
*   A specification of the fidelity that is required for presentation [MODCA-4-125]
*   A specification of finishing operations that are to be applied to media [MODCA-4-126]
*   A specification of the rendering intent that is to be applied to the print file or to documents in the print file [MODCA-4-127]
*   A specification of an appearance to be assumed by the presentation device for the processing of the print file [MODCA-4-128]
*   A specification of Color Management Resources (CMRs) that are to be associated with the print file or with a document in the print file [MODCA-4-129]
*   A specification of a setup name that is to be associated with the print file [MODCA-4-130]

If a parameter is specified in the DEG as well as in a medium map, the specification in the medium map takes precedence.

**Note:** When an internal (inline) medium map is used, structured fields which can be specified in the DEG and/or in a medium map, specifically the MDD, MMO, PEC, MDR, and PGP, must be specified in the internal medium map if they are to affect the pages/sheets processed using that medium map. If they are specified in the Document Environment Group (DEG), they do not apply to the pages/sheets processed using internal medium maps. Therefore if a PEC with the Device Appearance (X'97') triplet is not specified in the internal medium map, the device assumes its device default appearance, it does not inherit the appearance specified in the DEG. Similarly, if a PEC with Rendering Intent (X'95') triplet is not specified in the internal medium map, the rendering intent from the DEG is not inherited and does not apply to the pages/sheets processed with the inline medium map. Structured fields and triplets that can only be specified in the DEG and not in a medium map, such as the MSU, PFC, and Setup Name (X'9E') triplet in the PEC apply to the complete document or print file and are independent of internal medium maps and medium maps in the form map. The MFC structured field can be specified in the DEG and/or a Medium Map and defines its scope explicitly. [MODCA-4-131]
**Figure 34. Document Environment Group Structure**

*   Begin Document Environment Group (BDG, D3A8C4)
*   [ (PFC, D3B288) Presentation Fidelity Control (S) ]
*   [ (PEC, D3A7A8) Presentation Environment Control (S) ]
*   [ (MMO, D3B1DF) Map Medium Overlay ]
*   [ (MSU, D3ABEA) Map Suppression ]
*   (PGP, D3B1AF) Page Position F2
*   (MDD, D3A688) Medium Descriptor
*   [ (MFC, D3A088) Medium Finishing Control (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   End Document Environment Group (EDG, D3A9C4)

**Notes:**
1. An MMO is required in either the document environment group or a medium map if an MMC structured field references a medium overlay. If specified in both, the structured field in the medium map takes precedence. [MODCA-4-132]
2. A PGP and an MDD is required in either the document environment group or a medium map. If specified in both, the structured field in the medium map takes precedence. [MODCA-4-133]
3. The DEG may contain one print file level MFC that applies to the complete print file, one document level MFC that applies to all documents in the print file, and one or more document level MFCs that apply to single documents in the print file. In the last case, only one MFC in the DEG may select a given document in the print file. If the DEG contains more than one print file level MFC, more than one document level MFC that applies to all documents, or more than one document level MFC that selects the same document, only the last-specified MFC having that particular scope is used. [MODCA-4-134]
4. The PEC may be specified with the Rendering Intent (X'95') triplet, the Device Appearance (X'97') triplet, or both. Only a single rendering intent and a single device appearance should be assigned to the print file or to a specific document in the print file; if more than one is assigned, only the last assignment is used and the rest are ignored. For example, if two PECs assign a rendering intent to the third document in the print file, the second rendering intent is used and the first is ignored. [MODCA-4-135]
5. The PEC may be specified with the Device Appearance (X'97') triplet in the DEG and in a medium map. If specified in both, the PEC with Device Appearance triplet in the medium map takes precedence. [MODCA-4-136]
6. The MDR may only be used in the DEG to reference Color Management Resources (CMRs); MDR repeating groups referencing any other resource are ignored. [MODCA-4-137]
7. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the form map structure. The MO Type object container(s) must directly follow the Document Environment Group (DEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the form map. While the scope of a form map is the print file, the scope of this embedded metadata is simply the form map, itself. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-138]

**Application Note:** When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs.

## Medium Map

A medium map is a print control resource object that contains the print control parameters for presenting pages on a physical medium and for generating copies of the physical medium. Print control parameters may be grouped into two categories: [MODCA-4-139]

*   Medium level controls [MODCA-4-140]
*   Page level controls [MODCA-4-141]

Medium level controls are controls that affect the medium, such as the specification of medium overlays, medium size, medium orientation, medium copies, simplex or duplex, medium finishing, device appearance, rendering intent, and media source and destination selection. These controls are defined by the Map Medium Overlay (MMO), Medium Descriptor (MDD), Medium Copy Count (MCC), Medium Finishing Control (MFC), Map Media Type (MMT), Map Media Destination (MMD), Presentation Environment Control (PEC), and Medium Modification Control (MMC) structured fields. Page level controls are controls that affect the pages that are placed on the medium, such as the specification of page modifications, page position, and page orientation. These controls are defined by the Map Page Overlay (MPO), Page Position (PGP), and Page Modification Control (PMC) structured fields. When N-up partitioning is specified, the Media Eject Control (X'45') triplet may be included on the Begin Medium Map structured field to specify the type of media eject that is performed and the type of controls that are activated when the medium map is invoked.

A medium map contains one Medium Copy Count (MCC) structured field that generates a copy group for each sheet, therefore a medium map is also sometimes referred to as a copy group. Each MCC contains one or more copy subgroups that specify the number of copies of a sheet to be generated for the copy subgroup and the modifications to be applied to all copies in the copy subgroup. The modifications are specified by a Medium Modification Control (MMC) structured field. If the modifications for a copy subgroup specify duplexing, that copy subgroup and all successive copy subgroups are paired such that the first copy subgroup in the pair specifies the copy count as well as the modifications to be applied to the front side of each copy, and the second copy subgroup in the pair specifies the same copy count as well as an independent set of modifications to be applied to the back side of each copy. The pairing of copy subgroups continues as long as duplexing is specified. Note that with simplex printing, each copy subgroup builds the front sheet-side on all sheet copies generated by the copy subgroup. With duplex printing, the first and second copy subgroup in each pair of copy subgroups build front and back sheet-sides, respectively, on all sheet copies generated by the pair of copy subgroups. Figure 35 illustrates the copy subgroup concept.

**Figure 35.** Copy Subgroups

Medium Modiﬁcation Control (MMC)
Fixed
Medium
Information ID
Offset Media
Source
Overlays Suppressions
Duplex PQC CFCID

Medium Modiﬁcation Control (MMC)
ID
Fixed
Medium
Information
Offset
Media
Source
Overlays Suppressions
Duplex PQC CFC
ID ID ID ID ID. . . . . .

Medium Modiﬁcation Control (MMC)
ID
Fixed
Medium
Information
Offset
Media
Source
Overlays Suppressions
Duplex PQC CFC
ID ID ID ID ID ID. . . . . .

Medium Copy Count (MCC)
copies/ID
. . .
copies/ID
. . .
copies/ID

## Invocation of Medium Maps

*   A medium map can be invoked by name on any page boundary in a document. This is done by including an IMM (Invoke Medium Map) structured field in the document data stream. Multiple IMMs may be used within a single document. [MODCA-4-142]
*   A medium map can be directly included on any page boundary in the document data stream. Such a medium map is called an internal medium map. Multiple internal medium maps may be included in a document. An internal medium map is activated by following it immediately with an IMM that invokes the internal medium map. If an internal medium map is not explicitly invoked with an immediately-following IMM, it is ignored. IMMs cannot be used to invoke internal medium maps elsewhere in the document. When an IMM does not follow and reference an internal medium map, it references an external medium map in the processing system's form map. [MODCA-4-143] [MODCA-4-144]

The name assigned to each internal medium map object is unique within the document.

**Note:** When an internal (inline) medium map is used, structured fields which can be specified in the DEG and/or in a medium map, specifically the MDD, MMO, PEC, MDR, and PGP, must be specified in the internal medium map if they are to affect the pages/sheets processed using that medium map. If they are specified in the Document Environment Group (DEG), they do not apply to the pages/sheets processed using internal medium maps. Therefore if a PEC with the Device Appearance (X'97') triplet is not specified in the internal medium map, the device assumes its device default appearance, it does not inherit the appearance specified in the DEG. Similarly, if a PEC with Rendering Intent (X'95') triplet is not specified in the internal medium map, the rendering intent from the DEG is not inherited and does not apply to the pages/sheets processed with the inline medium map. Structured fields and triplets that can only be specified in the DEG and not in a medium map, such as the MSU, PFC, and Setup Name (X'9E') triplet in the PEC apply to the complete document or print file and are independent of internal medium maps and medium maps in the form map. The MFC structured field can be specified in the DEG and/or a Medium Map and defines its scope explicitly.

**Application Notes:**
1. Internal (inline) medium maps are not supported by all AFP print servers; consult your product documentation. [MODCA-4-145]
2. The use of internal medium maps may significantly decrease document processing throughput, especially if the internal medium map specifies conditional media ejects using the Media Eject Control (X'45') triplet. [MODCA-4-146]
3. Internal medium maps are also sometimes referred to as inline medium maps. The term “internal” is preferred. [MODCA-4-147]

*   If a parameter is specified both in the Document Environment Group (DEG) and in a medium map, the specification in the medium map takes precedence. [MODCA-4-148]
*   A medium map remains in effect until another medium map is selected or the end of the document is reached. [MODCA-4-149]
*   If a document does not invoke a medium map by name, and if it does not include an internal medium map, the first medium map in the selected form map controls the printing. [MODCA-4-150]
*   When an invoked medium map is used to process medium overlays or variable page data, it causes a media eject to occur before any data is presented. If not explicitly specified otherwise, the eject is to a new physical medium (form). When N-up partitioning is specified, the Media Eject Control (X'45') triplet may be included on the Begin Medium Map structured field to specify one of the following partition ejects: [MODCA-4-151]
    *   Conditional eject to next partition
    *   Conditional eject to next front-side partition
    *   Conditional eject to next back-side partition
    However, this triplet is ignored when it occurs on the medium map that is activated at the beginning of a document regardless of whether this medium map is explicitly invoked or implicitly invoked as the default.
*   If a contiguous sequence of IMMs is specified in the data stream, they are processed according to the following rules: [MODCA-4-152]
    *   If the sequence of IMMs is followed by a page, the last IMM must invoke a medium map that allows the presentation of pages. If it does not, an exception is generated.
    *   If the sequence of IMMs is followed by a page, only the last invoked medium map is used for processing; preceding medium maps are ignored. For example, if the first invoked medium map specifies a conditional eject to the next front partition and the last invoked medium map specifies a conditional eject to the next partition, the page is placed into the next partition. Similarly, if the first invoked medium map specifies “constant front” but allows page placement on the back, and if the last invoked medium map specifies “constant back” but allows page placement on the front, the first invoked medium map is ignored and the page is placed on the front, with constant data placed on the back. For a definition of the constant forms control, see page 283. [MODCA-4-153]
    *   If the sequence of IMMs invoke medium maps that prohibit the presentation of pages but that present medium overlays or PMC overlays, each medium map generates a sheet or multiple copies of a sheet with constant overlay data, as specified. These sheets are generated whether the last IMM is followed by a page or not.

**Application Note:** Page groups are often processed in standalone fashion, that is, they are indexed, retrieved, and presented outside the context of the containing document. While the pages in the group are independent of each other and of any other pages in the document, their formatting on media depends on when the last medium map was invoked and on how many pages precede the BNG since this invocation. To make the media formatting of page groups self-contained, a Medium Map should be invoked at the beginning of the page group between the Begin Named Group (BNG) structured field and the first Begin Page (BPG) structured field. If this is not done, the presentation system may need to “play back” all pages between the invocation of the active medium map and the BNG to determine media formatting such as sheet-side and partition number for the first page in the group.

It is therefore strongly recommended that in environments where standalone page group processing is required or anticipated, page groups are built with an Invoke Medium Map (IMM) structured field specified after the BNG and before the first BPG. Note that some AFP applications that generate page groups will support a user option which ensures that an IMM is specified after BNG and before the first BPG, and some AFP archive servers will expect an IMM there and may not present the page group correctly if none is found. This may cause the complete document to print differently.

A newer method to specify how a page or page group should be formatted involves use of the Page Position Information (X'81') triplet. This triplet may be specified on a BPG and indicates the repeating group in the PGP structured field in the active Medium Map that should be used to format the page.

**Figure 36. Medium Map Structure**

*   Begin Medium Map (BMM, D3A8CC)
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (MMO, D3B1DF) Map Medium Overlay ]
*   [ (MPO, D3ABD8) Map Page Overlay (S) ]
*   [ (MMT, D3AB88) Map Media Type (S) ]
*   [ (MMD, D3ABCD) Map Media Destination (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   (PGP, D3B1AF) Page Position F2
*   (MDD, D3A688) Medium Descriptor
*   (MCC, D3A288) Medium Copy Count
*   [ (MMC, D3A788) Medium Modification Control (S) ]
*   [ (PMC, D3A7AF) Page Modification Control (S) ]
*   [ (MFC, D3A088) Medium Finishing Control (S) ]
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   End Medium Map (EMM, D3A9CC)

**Notes:**
1. An MMO is required in either the document environment group or a medium map if an MMC structured field references a medium overlay. If specified in both, the structured field in the medium map takes precedence. [MODCA-4-154]
2. Within a medium map, a given media type local ID may only be mapped once to a media type OID and/or a media type name using an MMT. [MODCA-4-155]
3. The MDR may only be used in a Medium Map to reference Color Management Resources (CMRs); MDR repeating groups referencing any other resource are ignored. [MODCA-4-156]
4. A PGP and an MDD is required in either the document environment group or a medium map. If specified in both, the structured field in the medium map takes precedence. [MODCA-4-157]
5. MMC identifiers must be unique for all MMC structured fields in the medium map. PMC identifiers must be unique for all PMC structured fields in the medium map. [MODCA-4-158]
6. Each overlay included on a page with a PMC must first be mapped to a local ID with an MPO in the medium map containing the PMC. [MODCA-4-159]
7. Modifications specified by PMC structured fields are applied to pages on the medium depending on the MMC N-up Format Control (X'FC') keyword as follows: [MODCA-4-160]
    *   If N-up is not specified, the page on each sheet-side is processed with the PGP repeating group for that sheet side. All modifications specified by all PMCs in the active medium map are applied to the page on the sheet-side. [MODCA-4-161]
    *   If N-up with default page placement is specified, all pages on a sheet-side are processed with the PGP repeating group for that sheet side. If this repeating group does not specify a PMC identifier, or if the PMC identifier specifies X'FF', all modifications specified by all PMCs in the active medium map are applied to each page on the sheet side. If this repeating group specifies a PMC identifier, only the modifications included by the selected PMC are applied to all pages on the sheet-side. [MODCA-4-162]
    *   If N-up with explicit page placement is specified, each page is processed with a PGP repeating group. If this repeating group does not specify a PMC identifier, or if the PMC identifier specifies X'FF', all modifications specified by all PMCs in the active medium map are applied to the page. If this repeating group specifies a PMC identifier, only the modifications included by the selected PMC are applied to the page. [MODCA-4-163]
8. The actual presentation of the selected PMC modifications is controlled by the MMC Constant Forms Control (X'F9') keyword and the PGP PgFlgs parameter. See “Page Position (PGP) Format 2”. [MODCA-4-164]
9. All overlays included with a PMC structured field are presented on the page presentation space before any variable page data is presented. [MODCA-4-165]
10. MFCs can be specified in the document environment group, in a medium map, or in both places. When specified in both places, all specified finishing operations are applied according to their scope, as long as the operations are compatible. Note that the location of the MFC may restrict which operations are supported. For rules on how finishing operations are nested, see “Finishing Operation Nesting Rules” on page 270. [MODCA-4-166]
11. The PEC may be specified with the Rendering Intent (X'95') triplet, the Device Appearance (X'97') triplet, or both. Only a single rendering intent and a single device appearance should be assigned to the group of pages/sheets processed by this medium map; if more than one is assigned, only the last assignment is used and the rest are ignored. [MODCA-4-167]
12. The PEC may be specified with the Rendering Intent (X'95') triplet and the Device Appearance (X'97') triplet in the DEG and in a medium map. If specified in both, the triplet on the PEC in the medium map takes precedence. [MODCA-4-168]
13. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the medium map structure. The MO Type object container(s) must directly follow the Begin Medium Map (BMM), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the document. The MO:DCA architecture places no restriction on or significance to the sequence or order of included metadata. If an object container is specified in medium map state, the ObjClass parameter on the mandatory Object Classification (X'10') triplet must be set to X'50'—Metadata; otherwise, the object container is ignored. [MODCA-4-169] [MODCA-4-170]

**Application Note:** When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-171]
## Data Objects

Data objects contain presentation data and the controls to present this data. Data objects are generated in an object presentation space in accordance with controls defined by the data object architecture. The object presentation space is mapped to an object area on the page in accordance with controls defined in MO:DCA environment groups. Data object mappings are shown in the specific object descriptions that follow. Object area positioning is shown in **Figure 37**.

**Figure 37. Object Area Positioning on a Page**

Data objects are defined for the following types of presentation data: text, image, graphics, and bar codes. The corresponding data object architectures may define various functional levels for the data objects. When such levels are formally defined, they are called function sets or subsets. Wherever support for a data object in MO:DCA is limited to particular function sets, the function-set level is indicated in the object structure definition. Wherever a MO:DCA interchange set further restricts the level of function set that is supported in the interchange set, such restriction is indicated in the interchange set definition. [MODCA-4-172]

## Bar Code Objects

Bar code data consists of patterns of bars and spaces that represent alphanumeric information. Characteristics of the patterns are defined by specific bar code symbologies. A bar code object carries the alphanumeric information that is to be presented as a bar code and the controls to present this information using a specific bar code symbology. The bar code data object is defined by the Bar Code Object Content Architecture.

**Figure 38. Bar Code Object Structure**

*   Begin Bar Code Object (BBC, D3A8EB)
*   (D3..C7) Object Environment Group
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (BDA, D3EEEB) Bar Code Data (S) ]
*   End Bar Code Object (EBC, D3A9EB)

**Object Environment Group (OEG) for Bar Code Object**

*   Begin Object Environment Group (BOG, D3A8C7)
*   (OBD, D3A66B) Object Area Descriptor
*   (OBP, D3AC6B) Object Area Position
*   [ (MBC, D3ABEB) Map Bar Code Object ]
*   [ (MCF, D3AB8A) Map Coded Font F2 (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   (BDD, D3A6EB) Bar Code Data Descriptor
*   End Object Environment Group (EOG, D3A9C7)

**Note:** Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the data object structure. The MO Type object container(s) must directly follow the Object Environment Group (OEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata.

**Application Notes:**
1. For purposes of print server resource management, each MCF that maps a font in the bar code OEG must have a corresponding MCF mapping the same font in the AEG for the page or overlay that includes the bar code object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-173]
2. An MDR is used to map a non-FOCA data-object font resource in a bar code object, for use as a secondary resource of the bar code object. [MODCA-4-174] For purposes of print server resource management, each MDR that maps a font in the bar code OEG must have a corresponding MDR mapping the same font resource and attributes in the AEG for the page or overlay that includes the bar code object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-175]
3. An MDR is used to map a Color Management Resource (CMR) that is to be associated with the bar code object and that is to be used for rendering the bar code object. For purposes of print server resource management, each MDR that maps a CMR in the bar code OEG must have a corresponding MDR mapping the same CMR in the AEG for the page or overlay that includes the bar code object. [MODCA-4-176]
4. The mapping of a font local ID to a font must be unique; that is, the font local ID can only be mapped to one font in the OEG. However, different font local IDs can be mapped to the same font. The font mapping in an OEG must always be factored up to a mapping in the AEG. [MODCA-4-177]
5. The rendering intent for BCOCA objects is fixed as media-relative colorimetric. [MODCA-4-178]
6. An MDR is used to map a presentation data object in a QR Code with Image bar code object. [MODCA-4-179] For purposes of print server resource management, each MDR that is specified in the bar code OEG must have a corresponding MDR mapping the same resource in the AEG for that page or overlay. Each MDR in the bar code OEG must specify both external and internal identifiers in the OEG and the external identifiers are factored up to the AEG for the page or overlay. [MODCA-4-180]

A QR Code with Image bar code object may contain secondary presentation data object resources mapped by the OEG. When the presentation data object resource is an IOCA image, the FQN type X'BE' triplet would be paired with an FQN type X'84' triplet. When the presentation data object resource is a non-OCA presentation object, the FQN type X'BE' triplet would be paired with an FQN type X'CE' triplet. Presentation data object resources supported for this purpose are IOCA images along with the object types shown in **Table 48**.

These secondary presentation data object resources may themselves contain OEGs with MDRs for non-presentation data object resources (IOCA tiles, CMRs, data object fonts, etc.), also known as tertiary objects. These tertiary MDRs have external names and some may have internal names. These tertiary MDRs must be specified in the bar code OEG, including both external and, if specified, internal names. The tertiary MDR external names must be factored up to the AEG for the page or overlay.

In the case where a tertiary CMR resource is mapped for use by a secondary image resource, the FQN type X'EE' triplet containing the CMR external name would be paired with the FQN type X'BE' triplet containing the internal name of the secondary image from within the QR Code with Image bar code object. This pairing identifies the image that uses this CMR, effectively making this association known to the print server. The X'EE' CMR external name must be factored up to the AEG for the page or overlay.

**Note:** All external IDs and internal IDs for secondary resources and tertiary resources must be unique and must be mapped in the QR Code with Image bar code OEG.

7. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-181]

**Figure 39. Bar Code Presentation Space Mapping: Position**

Bar Code Presentation Space
Page Presentation Space
Object Content Position specified in the Object Area Position (OBP)
Bar Code Object Area
Bar Code Presentation Space

**Note:** Refer to the Bar Code Object Content Architecture Reference for a full description of the BCOCA object content, syntax, and semantics for MO:DCA data streams. [MODCA-4-182]

### Mapping the Bar Code Presentation Space

The mapping option is specified by the Mapping Option (X'04') triplet on the Map Bar Code Object (MBC) structured field. The only valid option is position. This mapping is shown in **Figure 39**. [MODCA-4-183]

## Graphics Objects

Graphics data consists of controls and parameters to generate pictures based on lines, characters, and shaded areas. The graphics data object is defined by the Graphics Object Content Architecture for Advanced Function Presentation.

**Figure 40. Graphics Object Structure**

*   Begin Graphics Object (BGR, D3A8BB)
*   (D3..C7) Object Environment Group
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (GAD, D3EEBB) Graphics Data (S) ]
*   End Graphics Object (EGR, D3A9BB)

**Object Environment Group (OEG) for Graphics Object**

*   Begin Object Environment Group (BOG, D3A8C7)
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   (OBD, D3A66B) Object Area Descriptor
*   (OBP, D3AC6B) Object Area Position
*   [ (MGO, D3ABBB) Map Graphics Object ]
*   [ (MCF, D3AB8A) Map Coded Font F2 (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   (GDD, D3A6BB) Graphics Data Descriptor
*   End Object Environment Group (EOG, D3A9C7)

**Notes:**
1. Refer to the Graphics Object Content Architecture for AFP Reference for a full description of the GOCA object content, syntax, and semantics for MO:DCA data streams. [MODCA-4-184]
2. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the data object structure. The MO Type object container(s) must directly follow the Object Environment Group (OEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-185]

**Application Notes:**
1. For purposes of print server resource management, each MCF that maps a font in the graphics OEG must have a corresponding MCF mapping the same font in the AEG for the page or overlay that includes the graphics object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-186]
2. An MDR is used to map a non-FOCA data-object font resource in a graphics object. For purposes of print server resource management, each MDR that maps a font in the graphics OEG must have a corresponding MDR mapping the same font resource and attributes in the AEG for the page or overlay that includes the graphics object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-187]
3. An MDR is used to map a Color Management Resource (CMR) that is to be associated with the graphics object and that is to be used for rendering the graphics object. For purposes of print server resource management, each MDR that maps a CMR in the graphics OEG must have a corresponding MDR mapping the same CMR in the AEG for the page or overlay that includes the graphics object. [MODCA-4-188]
4. The mapping of a font local ID to a font must be unique; that is, the font local ID can only be mapped to one font in the OEG. However, different font local IDs can be mapped to the same font. The font mapping in an OEG must always be factored up to a mapping in the AEG. [MODCA-4-189] [MODCA-4-190]
5. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-191]

**Architecture Note:** The PEC structured field in the OEG for the graphics object is only used to specify the rendering intent for the object using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-192] [MODCA-4-193]

### Mapping the Graphics Presentation Space

The mapping option is specified by the Mapping Option (X'04') triplet on the Map Graphics Object (MGO) structured field. The valid mapping options are:

*   Scale to fit [MODCA-4-194]
*   Scale to fill [MODCA-4-195]
*   Center and trim [MODCA-4-196]
*   Position and trim [MODCA-4-197]

The replicate-and-trim mapping option has been retired for graphics objects; see “Retired Parameters” on page 570. These mapping options are shown in **Figure 41**, **Figure 42**, **Figure 43**, and **Figure 44**.

**Figure 41. Graphics Presentation Space Mapping: Scale to Fit** [MODCA-4-198]

**Figure 42. Graphics Presentation Space Mapping: Scale to Fill**

Note that the scale to fill mapping option is similar to scale to fit except that the Graphics presentation space window may be scaled asymmetrically to fill the object area completely. This means that the aspect ratio of the graphics picture may not be preserved. [MODCA-4-199]

**Figure 43. Graphics Presentation Space Mapping: Center and Trim** [MODCA-4-200]

**Figure 44. Graphics Presentation Space Mapping: Position and Trim** [MODCA-4-201]

## Image Objects

Image data consists of an electronic representation of a picture in the form of an array of raster data, along with the controls to present this data. The image data object is defined by the Image Object Content Architecture and is sometimes referred to as an IO image object.

MO:DCA also supports the IM image object for migration purposes. For a definition of this object, see “IM Image Object”.

**Figure 45. Image Object Structure**

*   Begin Image Object (BIM, D3A8FB)
*   (D3..C7) Object Environment Group
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (IPD, D3EEFB) Image Picture Data (S) ]
*   End Image Object (EIM, D3A9FB)

**Object Environment Group (OEG) for Image Object**

*   Begin Object Environment Group (BOG, D3A8C7)
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   (OBD, D3A66B) Object Area Descriptor
*   (OBP, D3AC6B) Object Area Position
*   [ (MIO, D3ABFB) Map Image Object ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   (IDD, D3A6FB) Image Data Descriptor
*   End Object Environment Group (EOG, D3A9C7)

**Notes:**
1. Refer to the Image Object Content Architecture Reference for a full description of the IOCA object content, syntax, and semantics for MO:DCA data streams. [MODCA-4-202]
2. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the data object structure. The MO Type object container(s) must directly follow the Object Environment Group (OEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-203]

**Application Notes:**
1. An MDR is used to map a Tile Resource that is invoked by the IOCA object. For purposes of print server resource management, each MDR that maps a Tile Resource in the image OEG must have a corresponding MDR mapping the same resource in the AEG for the page or overlay that includes the image object. [MODCA-4-204]
2. An MDR is also used to map a Color Management Resource (CMR) that is to be associated with the IOCA object and that is to be used for rendering the IOCA object. For purposes of print server resource management, each MDR that maps a CMR in the image OEG must have a corresponding MDR mapping the same CMR in the AEG for the page or overlay that includes the image object. [MODCA-4-205]
3. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-206]

**Architecture Note:** The PEC structured field in the OEG for the image object is only used to specify the rendering intent for the object using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-207] [MODCA-4-208]

### Mapping the Image Presentation Space

The mapping option is specified by the Mapping Option (X'04') triplet on the Map Image Object (MIO) structured field. The valid mapping options are:

*   Scale to fit [MODCA-4-209]
*   Scale to fill [MODCA-4-210]
*   Center and trim [MODCA-4-211]
*   Position and trim [MODCA-4-212]

These mapping options are shown in **Figure 46**, **Figure 47**, **Figure 48**, and **Figure 49**.

**Figure 46. Image Presentation Space Mapping: Scale to Fit** [MODCA-4-213]

**Figure 47. Image Presentation Space Mapping: Scale to Fill**

Note that the scale to fill mapping option is similar to scale to fit except that the Image presentation space may be scaled asymmetrically to fill the object area completely. This means that the aspect ratio of the image may not be preserved.

**Figure 48. Image Presentation Space Mapping: Center and Trim** [MODCA-4-214]

**Figure 49. Image Presentation Space Mapping: Position and Trim**

The MO:DCA architecture supports three additional mappings for the IOCA FS10 object for IM image migration purposes. For a definition of these mappings, see “Coexistence Triplets”.

## Text Objects

Presentation text data consists of graphic character code points and the controls required to position and present the corresponding graphic characters. The presentation text data object is defined by the Presentation Text Object Content Architecture. The presentation text object in MO:DCA can have two structures that differ by the presence or absence of an Object Environment Group (OEG).

**Note:** Refer to the Presentation Text Object Content Architecture Reference for a full description of the PTOCA object content, syntax, and semantics for MO:DCA environments.

**Figure 50. Presentation Text Object Structure - Without OEG**

*   Begin Presentation Text Object (BPT, D3A89B)
*   [ (PTX, D3EE9B) Presentation Text Data (S) ]
*   End Presentation Text Object (EPT, D3A99B)

When the presentation text object in a MO:DCA data stream does not contain an OEG, the presentation parameters normally specified in the OEG are specified in the Active Environment Group (AEG) of the containing page or overlay as follows:

*   **Object Area Descriptor (OBD).** Optionally included once in the AEG, therefore it applies to all presentation text objects on the page or overlay that do not contain an OEG. Furthermore, MO:DCA interchange sets require that the OBD specify measurement units and extents that match those specified for the page or overlay in the PGD. If the OBD is omitted, the architected default is to use the measurement units and extents specified in the PGD for the text object area measurement units and object area extents. [MODCA-4-215] [MODCA-4-216]
*   **Object Area Position (OBP).** Optionally included once in the AEG, therefore it applies to all presentation text objects on the page or overlay that do not contain an OEG. Furthermore, MO:DCA interchange sets require that the OBP specifies zeros for the object area origin and the object content origin and that it specifies a 0° object area rotation. If the OBP is omitted, the architected default is to set the object area origin and the object content origin to zeros, and the object area rotation to 0°. [MODCA-4-217]
*   **Object mapping options.** Not defined for presentation text objects that do not contain an OEG. The text data is presented as specified by the text object. [MODCA-4-218]
*   **Font mapping.** All fonts required by presentation text objects that do not contain an OEG must be mapped with MCF or MDR structured fields in the AEG of the page or overlay. [MODCA-4-219]
*   **Presentation Text Descriptor (PTD).** Included once in the AEG, therefore it applies to all presentation text objects on the page or overlay that do not contain an OEG. When the BPT structured field is processed for a PTOCA object without OEG, all initial text conditions specified in the Presentation Text Descriptor (PTD) structured field of the page or overlay's AEG are set prior to processing the text object. [MODCA-4-220]

**Application Note:** Whenever a BPT is encountered for a text object without OEG, AFP servers set default page-level initial text conditions before the PTD initial conditions are set; see Table 16.

**Note:** Presentation text objects that do not contain an OEG that are found in a page or overlay may be referred to as text major.

**Figure 51. Presentation Text Object Structure - With OEG**

*   Begin Presentation Text Object (BPT, D3A89B)
*   (D3..C7) Object Environment Group
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (PTX, D3EE9B) Presentation Text Data (S) ]
*   End Presentation Text Object (EPT, D3A99B)

**Object Environment Group (OEG) for Presentation Text Object**

*   Begin Object Environment Group (BOG, D3A8C7)
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   (OBD, D3A66B) Object Area Descriptor
*   (OBP, D3AC6B) Object Area Position
*   [ (MPT, D3AB9B) Map Presentation Text ]
*   [ (MCF, D3AB8A) Map Coded Font F2 (S) ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   (PTD, D3B19B) Presentation Text Data Descriptor
*   End Object Environment Group (EOG, D3A9C7)

When the presentation text object in a MO:DCA data stream contains an OEG, all initial text conditions specified in the Presentation Text Descriptor (PTD) structured field of the OEG are set prior to processing the text object. For such text objects, the OBD, OBP, and PTD structured fields in the AEG of the page or overlay are ignored.

**Architecture Note:** The coexistence PTD-1 structured field is not allowed in the OEG of the Presentation Text Object.

**Note:** Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the data object structure. The MO Type object container(s) must directly follow the Object Environment Group (OEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-221]

**Application Notes:**
1. For purposes of print server resource management, each MCF that maps a font in the text OEG must have a corresponding MCF mapping the same font in the AEG for the page or overlay that includes the text object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-222]
2. An MDR is used to map a non-FOCA data-object font resource in a text object. For purposes of print server resource management, each MDR that maps a font in the text OEG must have a corresponding MDR mapping the same font resource and attributes in the AEG for the page or overlay that includes the text object. The local ID used in the page or overlay AEG need not match the ID in the object OEG. ID X'FE' may be used in the AEG for fonts mapped in the AEG solely due to their presence in an object's OEG. [MODCA-4-223]
3. An MDR is used to map a Color Management Resource (CMR) that is to be associated with the text object and that is to be used for rendering the text object. For purposes of print server resource management, each MDR that maps a CMR in the text OEG must have a corresponding MDR mapping the same CMR in the AEG for the page or overlay that includes the text object. [MODCA-4-224]
4. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-225]

**Architecture Notes:**
1. The PEC structured field in the OEG for the image object is only used to specify the rendering intent for the object using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-226]
2. Any text suppressions that need to be applied, whether the text object contains an OEG or not, need to be specified in the active Medium Map. [MODCA-4-227]
3. The mapping of a font local ID to a font must be unique; that is, the font local ID can only be mapped to one font in the OEG. However, different font local IDs can be mapped to the same font. The font mapping in an OEG must always be factored up to a mapping in the AEG. [MODCA-4-228] [MODCA-4-229]

### Mapping the Text Presentation Space (Text Object with OEG)

The mapping option is specified by the Mapping Option (X'04') triplet on the Map Presentation Text (MPT) structured field. The valid mapping option is:

*   Position [MODCA-4-230]

This mapping option is shown in **Figure 52**.

**Figure 52. Text Presentation Space Mapping: Position** [MODCA-4-231]
## Object Containers

Object containers are MO:DCA objects that envelop and carry object data. The object data may or may not be specified by an AFP architecture. The object data is not constrained to be traditional text, image, or graphics. However if it is a presentation object, it must have a well-defined processing semantic resulting in a fixed, deterministic presentation when processed by a receiver capable of presenting the object. If the object is a traditional time-invariant presentation object, it must be paginated, that is its presentation space must be constrained to a single page. For presentation objects, the object data in the container is presented when the object container is included on a page or overlay using the Include Object (IOB) structured field. The object container may also be included directly on a page or overlay. **Figure 53** shows how object container data is included on a page using the Include Object (IOB) structured field.

When a presentation object container is included on a page or overlay, the object is first completely processed into final-form in its own presentation space, including applying any transformations specified within the object, before that object presentation space is mapped into the MO:DCA object area and the MO:DCA position, rotation, and scaling parameters are applied to the object area. [MODCA-4-232]

**Figure 53. Use of the IOB to Include Object Container Data**

The object container provides a range of functions that may be used to identify and structure the enveloped object data. At minimum, the container provides Begin and End structured fields, categorizes the object into a class, identifies the object type using a registered numeric identifier, and carries the object data in OCD structured fields. Above this minimum level of function, the object container may include additional optional functions such as an OEG to specify data object presentation space size, position, mapping and orientation.

For presentation objects, the required container structure depends on where the object is stored and how it is included in a page or overlay:

*   If the object is included directly in a page or overlay, the container must, at a minimum, have the following structure: [MODCA-4-233]
    *   BOC/EOC with the Object Classification (X'10') triplet on the BOC specifying the registered object-type identifier (encoded object-type OID) for the object data format
    *   OEG with OBD, OBP, and CDD
    *   All object data partitioned into OCDs
*   If the object is included using an Include Object (IOB) structured field and is carried in an external (print file level) resource group, the container must, at a minimum, have the following structure: [MODCA-4-234] [MODCA-4-235]
    *   BOC/EOC with the Object Classification (X'10') triplet on the BOC specifying the registered object-type identifier (encoded object-type OID) for the object data format
    *   All object data partitioned into OCDs
*   If the object is included using an Include Object (IOB) structured field and is stored in a resource library, there is no minimum container structure requirement, that is, the object may be stored and included in its unaltered, original form. However, if the included object is carried in a BOC/EOC container, the object data must be partitioned into OCDs. If the object is installed in a resource library using a Data Object Resource Access Table (DO RAT), it must not be wrapped with a MO:DCA object envelope such as BOC/EOC, that is, it must be installed in its raw source format. Examples of presentation objects that can be installed using a DO RAT are EPS, PDF, GIF, TIFF, and AFPC JPEG objects. [MODCA-4-236]

**Figure 54. Object Container Structure for Presentation Objects**

*   Begin Object Container (BOC, D3A892)
*   [ (D3..C7) Object Environment Group ]
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (OCD, D3EE92) Object Container Data (S) ]
*   End Object Container (EOC, D3A992)

**Object Environment Group (OEG) for Object Container**

*   Begin Object Environment Group (BOG, D3A8C7)
*   [ (PEC, D3A7A8) Presentation Environment Control ]
*   [ (OBD, D3A66B) Object Area Descriptor ]
*   [ (OBP, D3AC6B) Object Area Position ]
*   [ (MCD, D3AB92) Map Container Data ]
*   [ (MDR, D3ABC3) Map Data Resource (S) ]
*   [ (CDD, D3A692) Container Data Descriptor ]
*   End Object Environment Group (EOG, D3A9C7)

**Note:** Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the object container structure. The MO Type object container(s) must directly follow the Object Environment Group (OEG), otherwise they are ignored. When including multiple MOs the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata.

**Application Notes:**
1. For purposes of print server resource management, each MDR that is specified in the object container OEG must have a corresponding MDR mapping the same resource in the AEG for the page or overlay that includes the object container. Note that an FQN type X'BE' triplet, if specified on the MDR in the OEG, is not factored up to the AEG, unless the MDR maps a data-object font. [MODCA-4-237]
2. An MDR is used to map a Color Management Resource (CMR) that is to be associated with the object in the container and that is to be used for rendering the object. For purposes of print server resource management, each MDR that maps a CMR in the object container OEG must have a corresponding MDR mapping the same CMR in the AEG for the page or overlay that includes the object container. [MODCA-4-238]
3. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-239]

**Architecture Notes:**
1. An MDR reference to a specific resource may only be specified once in the object container OEG. [MODCA-4-240]
2. The PEC structured field in the OEG for the object container is only used to specify the rendering intent for the object using the Rendering Intent triplet; all other PEC triplets are ignored. [MODCA-4-241]

For non-presentation objects, the required container structure depends on where the object is stored:

*   If the object is carried in an external (print file level) resource group, the container must have the following structure: [MODCA-4-242]
    *   BOC/EOC with the Object Classification (X'10') triplet on the BOC specifying the registered object-type identifier (encoded object-type OID) for the object data format
    *   All object data partitioned into OCDs
*   If the object is stored in a resource library, there is no minimum container structure requirement, that is, the object may be stored in its unaltered, original form. However, if the object is stored in a BOC/EOC container, the object data must be partitioned into OCDs. If the non-presentation object is installed in a resource library using a Resource Access Table (RAT), it must not be wrapped with a MO:DCA object envelope such as BOC/EOC, that is, it must be installed in its raw source format. Examples of non-presentation objects that are installed using a RAT and that must not be wrapped with a BOC/EOC envelope are: [MODCA-4-243]
    *   TrueType/OpenType fonts and TrueType/OpenType Collections
    *   Color Management Resources (CMRs)

**Figure 55. Object Container Structure for Non-Presentation Objects**

*   Begin Object Container (BOC, D3A892)
*   [ (D3..92) Object Container (MO Type only) (S) ]
*   [ (OCD, D3EE92) Object Container Data (S) ]
*   End Object Container (EOC, D3A992)

**Notes:**
1. Metadata is optional. If metadata is present, one or more metadata objects (MO) may be included within the object container structure. The MO Type only object container(s) must directly follow the Begin Object Container (BOC), otherwise they are ignored. When including multiple MOs, the series of MO Type object containers must be contiguous and, as a whole, constitute the metadata for the data object. MO:DCA places no restriction on or significance to the sequence or order of included metadata. [MODCA-4-244]
2. If the object container data in the non-presentation object contains an MO, then the additional metadata (MO type only) object containers are not allowed in the object container structure. [MODCA-4-245]

**Application Notes:**
1. When an object container is carried in an external (print file level) resource group in AFP environments, a BRS/ERS envelope is mandatory. [MODCA-4-246]
2. When encountering a misplaced MO, some MO:DCA receivers ignore or discard it. Some MO:DCA receivers also issue a message when this occurs. [MODCA-4-247]

### Mapping the Container Data Presentation Space

The mapping option is specified by the Mapping Option (X'04') triplet on the Map Container Data (MCD) structured field. The valid mapping options are:

*   Scale to fit [MODCA-4-248]
*   Scale to fill [MODCA-4-249]
*   Center and trim [MODCA-4-250]
*   Position and trim [MODCA-4-251]
*   Position [MODCA-4-252]
*   UP3i Print Data mapping; only valid for the UP3i Print Data object [MODCA-4-253]

For a description of the supported mapping options see “Mapping Option Triplet X'04'”. For the scale-to-fit and scale-to-fill mapping of presentation data in an object container, a data object presentation space size is required. See “Object Type Identifiers” for information on how the presentation space size is specified by various data objects. If the presentation space size is not specified by the object, the architected default is the presentation space size of the including page or overlay. [MODCA-4-254]

The UP3i Print Data mapping is only valid for the UP3i Print Data object type; if any other mapping option is specified for this object type a X'02' exception condition exists. [MODCA-4-255]

