# Chapter 3. Referencing Fonts

When you create a document, you specify the font or fonts to be used whenever the document is presented. The application programs that process the document use the font references in the document to locate the required font resources for document formatting and presentation. System and presentation device libraries provide font resources, which have assigned names that must be associated with the font references contained in a document. The association of font references to font resources is not necessarily a one-to-one association based only on the name. The principal method of referencing fonts is by listing the applicable font-description parameters. [FOCA-3-001]

To enable text processing systems to locate and use the appropriate font resources, the font references must be made in a clear, consistent manner across all the systems, applications, and data streams that use the font resources. Then, if a document containing the font references is sent to other locations, the processors at those receiving locations can use the distinguishing characteristics or attributes to find the required font resource (perhaps filed under a different name), or to select an appropriate alternative. The following sections of this chapter describe: [FOCA-3-002]

*   Understanding the font reference model [FOCA-3-003]
*   Identifying fonts [FOCA-3-004]
*   Selecting and substituting fonts [FOCA-3-005]

## Understanding the Font Reference Model

Many components of a processing system interact to produce the final presentation of a document. The actual flow of the data stream through the system depends on both the configuration of the specific system and the intent or design of the user. Figure 9 illustrates the general flow of a document from creation to presentation. The figure assumes that the system, including the font resources, are in place when the user initiates a document. The sections following Figure 9 provide more detail for some of the separate transactions within the figure. [FOCA-3-006]

**Figure 9. Font Reference Model General Flow** [FOCA-3-007]

*   **User Input**: Create Font References through a User Interface [FOCA-3-008]
*   **Document Editing**: Produces Revisable Document [FOCA-3-009]
*   **Font Services**: Font Queries, System-Level Font Resources [FOCA-3-010]
*   **Document Formatting**: Produces Presentation Document [FOCA-3-011]
*   **Document Presentation Services**: Produces Device Data Stream [FOCA-3-012]
*   **Document Presenting**: Presentation Device or Device Controller, Device-level Font Resources [FOCA-3-013]
*   **Output Document** [FOCA-3-014]

### User Input

A user interacts with a text or graphics editor to produce a document that is in a revisable form and contains references to the desired fonts, as shown in Figure 10. [FOCA-3-015]

**Figure 10. Creation of a Document** [FOCA-3-016]

The user enters the references through the editor's user interface, which might present a list of the names of the available fonts or a list of the characteristic font attributes that enable the processing system to select a font. The document data stream produced by the editor can specify fonts in one of the following ways: [FOCA-3-017]

*   Specifies the requested font by name [FOCA-3-018]
*   Maps the font attributes selected by the user to a specific font name [FOCA-3-019]
*   Reproduces the font attributes of the requested font from a separately stored list of attributes specified by the user [FOCA-3-020]
*   Reproduces the font attributes of the requested font from the font resource specified by the user [FOCA-3-021]

Because editing the document creates the font references and does not include formatting the document, the user needs to record only the intended font as input (for example, 10 point IBM Sonoran Serif italic). [FOCA-3-022]

### Editor Determination of Font Availability

The application program or editor might provide the user with a list of available fonts or locate an available font that corresponds to the font information provided. To do this, as shown in Figure 11, an editor might use an interface that permits inquiries to a font services facility concerning the font resources available to the processing system. Another method might be for the editor to directly access the font resource information in system storage. [FOCA-3-023]

**Figure 11. Editor Determination of Font Availability** [FOCA-3-024]

The font resources available to a system need not be physically resident on the user's workstation (distributed data base) or physically available in the form required for presentation (logical fonts or transformable resources). The editor queries font services about the availability of font resources. [FOCA-3-025]

### Font Services Access to Font Information

The font services facility responds to editor queries and provides font information, as shown in Figure 12, by accessing system-level font resources available in the font library. The font references that the font services facility uses are different in form and content from those required by editing applications. [FOCA-3-026]

**Figure 12. Font Services Access to Font Information** [FOCA-3-027]

### Formatter Access to Font Information

During formatting, the processing system must interpret the font references contained in the revisable document, locate the required font-metric information, and produce a formatted, final-form document. To do so, as shown in Figure 13, the formatter might use an interface that permits inquiries to a font services facility concerning the font resources available to the processing system. The formatter must generate a query to the font services facility by providing an appropriate font reference (which might be different from that issued during editing), and it will receive a response to the query. [FOCA-3-028]

**Figure 13. Formatter Access to Measurement Information** [FOCA-3-029]

The presentation document produced will contain references to the font resources used when formatting, and will often be more specific than those found in the revisable document. If the presentation document is sent to another location or system where the specified font resource is not available, it might be necessary to substitute another font for presentation of the document. Thus, font references contained in a presentation document should retain the user intent provided in the revisable document. [FOCA-3-030]

### Presentation Services Access to Font Information

The presentation services provide several font-related functions that might include: [FOCA-3-031]

*   Accessing and transforming the font information required by a device [FOCA-3-032]
*   Down loading font information to a presentation device [FOCA-3-033]
*   Determining available resident font support in the device [FOCA-3-034]
*   Substituting one font resource for another [FOCA-3-035]

When processing presentation information, the processing system, as shown in Figure 14, must generate a query to the font services facility by providing an appropriate font reference. The font services facility responds by identifying the available font resources or the font and shape information that corresponds to the referenced font. [FOCA-3-036]

**Figure 14. Presentation Services Access to Font Information** [FOCA-3-037]

When processing presentation information, the processing system might also perform a query to the attached presentation devices to determine which resident fonts are available on the device, and it will receive font references that identify those fonts. The data stream for the device produced by the presentation services must contain references to the device-specific font resources needed to present the document, and it might include the required font-metric and character-shape information. Because the data stream for the device must contain specific references to device-specific font resources, the content and format of the font references will often be more specific than those required in the revisable or presentation document references. [FOCA-3-038]

## Identifying Fonts

Each of the previously-defined process steps involves the use of font references to font resources. Each reference might require different information in different formats, and the different font resources referenced might be identified by different name formats. To fully support font referencing, the following items must be defined: [FOCA-3-039]

*   System-level identifying characteristics [FOCA-3-040]
*   Device-level identifying characteristics [FOCA-3-041]
*   User input font reference [FOCA-3-042]
*   Revisable document font reference [FOCA-3-043]
*   Presentation document font reference [FOCA-3-044]
*   Device data stream font reference [FOCA-3-045]

The general requirements for identifying font resources and referencing font resources are similar, although the specific requirements will vary by the type of installation, the type of implementation, and the point in the process. The specific content and format of a font resource name is defined by the implementing product specifications. When planning to identify font resources, the following factors must be considered: [FOCA-3-046]

*   A font reference might be a list of font attributes, or it might be a system file name, a member name, or a resource-management name that is mapped to a system file name or member name. [FOCA-3-047]
*   More than one font resource can correspond to a single font reference. [FOCA-3-048]
*   More than one font reference can correspond to a single font resource. [FOCA-3-049]
*   If a system uses raster font resources, different font resources might be necessary for each presentation device and each presentation size. [FOCA-3-050]
*   If a system uses outline font resources, one font resource might provide the data for multiple font sizes, typeface variations, and presentation devices. [FOCA-3-051]
*   The list of characters in a font resource might not match the list of characters in a code page. [FOCA-3-052]
*   A user's intent should be distinguishable from system-default or architecture-default values. [FOCA-3-053]
*   A user's font references should be as device-independent as possible to permit document distribution to a variety of sites and to permit document presentation on a variety of devices. [FOCA-3-054]

Fonts can be identified in different ways. One example is by using the descriptive parameters used in the Map Coded Font structured field in MO:DCA. Another example is by using the Global Resource Identifier, GRID, as used in MO:DCA and IPDS. Refer to the *Mixed Object Document Content Architecture Reference* and the *Intelligent Printer Data Stream Reference*. [FOCA-3-055]

The specific content and format of a font reference is defined by the appropriate document content, device service, or interface architecture. The following information is provided as a guideline for such use. [FOCA-3-056]

### System-Level Font Resource

A system-level font resource is generally designed to be shared across multiple applications and presentation devices in support of multiple document and presentation data streams. System-level font resources will contain the metric and shape information for a large number of graphic characters, identifying them by unique global identifiers, independent of any single document encoding technique. The set of attributes used to identify a system-level font resource is more extensive than those used to identify a device-level font resource because of the metric and shape information used by editor, formatter, and presentation processes. [FOCA-3-057]

The following table identifies the AFP parameters and ISO properties (properties is the term used by the ISO/IEC 9541 Font Information Interchange standard) that should appear in a system-level font resource to support font referencing. Because locating a desired font resource requires matching the parameters of a font reference to corresponding parameters in the available font resources, this set of parameters represent the union of the parameters that should appear in any of the supported font references. [FOCA-3-058]

#### Table 5. AFP Parameters Mapped to ISO Properties for System-Level Font Resources

| AFP Parameter | ISO Property |
| :--- | :--- |
| resource name | fontname [FOCA-3-059]|
| function set code | standardversion [FOCA-3-060]|
| typeface name | typeface [FOCA-3-061]|
| design source | dsnsource [FOCA-3-062]|
| data source | datasource [FOCA-3-063]|
| family name | fontfamily [FOCA-3-064]|
| posture class | posture [FOCA-3-065]|
| weight class | weight [FOCA-3-066]|
| width class | propwidth [FOCA-3-067]|
| primary GCSGID | incglyphcols [FOCA-3-068]|
| nominal vertical font size | dsnsize [FOCA-3-069]|
| minimum vertical font size | minsize [FOCA-3-070]|
| maximum vertical font size | maxsize [FOCA-3-071]|
| nominal horizontal font size | (not supported) [FOCA-3-072]|
| minimum horizontal font size | minanascale [FOCA-3-073]|
| maximum horizontal font size | maxanascale [FOCA-3-074]|
| font classification | dsngroup [FOCA-3-075]|
| structure class | structure [FOCA-3-076]|
| font type flags | escclass [FOCA-3-077]|
| character rotation | nomescdir [FOCA-3-078]|
| average escapement | avgescx/y [FOCA-3-079]|
| average lowercase escapement | avglcescx/y [FOCA-3-080]|
| average capital escapement | avgcapescx/y [FOCA-3-081]|
| figure space increment | tabescx/y [FOCA-3-082]|
| maximum baseline extent | maxfontext [FOCA-3-083]|

The various methods of document encoding are addressed by a separate collection of code page (encoding vector) resources that map the supported code points to the font glyph identifiers. Differences in device resolution and imaging technology are addressed by the presentation process that transforms a system-level font resource and an appropriate code page resource into a device-level font resource. [FOCA-3-084]

### Device-Level Font Resource

A device-level font resource is generally designed to be used by one device or family of related devices and a single document encoding technique. Device-level font resources should contain the metric and shape information for a single code page (encoding vector) and will most often identify the metric and shape information by the code point rather than by an independent glyph identifier (though it can use an attached mapping table to associate the code point to the glyph ID). The set of attributes used to identify a device-level font resource is less extensive than that used to identify a system-level font resource because the metric and shape information is used only by the presentation processes. [FOCA-3-085]

The following table identifies the AFP parameters and ISO properties that should appear in a device-level font resource to support font referencing. [FOCA-3-086]

#### Table 6. AFP Parameters Mapped to ISO Properties for Device-Level Font Resources

| AFP Parameter | ISO Property |
| :--- | :--- |
| resource name | fontname [FOCA-3-087]|
| design source | dsnsource [FOCA-3-088]|
| data source | datasource [FOCA-3-089]|
| family name | fontfamily [FOCA-3-090]|
| posture class | posture [FOCA-3-091]|
| weight class | weight [FOCA-3-092]|
| width class | propwidth [FOCA-3-093]|
| primary GCSGID | incglyphcols [FOCA-3-094]|
| primary CPGID | (not supported) [FOCA-3-095]|
| nominal vertical font size | dsnsize [FOCA-3-096]|
| minimum vertical font size | minsize [FOCA-3-097]|
| maximum vertical font size | maxsize [FOCA-3-098]|
| nominal horizontal font size | (not supported) [FOCA-3-099]|
| minimum horizontal font size | minanascale [FOCA-3-100]|
| maximum horizontal font size | maxanascale [FOCA-3-101]|
| font classification | dsngroup [FOCA-3-102]|
| structure class | structure [FOCA-3-103]|
| font type flags | escclass [FOCA-3-104]|
| character rotation | nomescdir [FOCA-3-105]|
| average escapement | avgescx/y [FOCA-3-106]|
| average lowercase escapement | avglcescx/y [FOCA-3-107]|
| average capital escapement | avgcapescx/y [FOCA-3-108]|
| figure space increment | tabescx/y [FOCA-3-109]|
| maximum baseline extent | maxfontext [FOCA-3-110]|
| pattern technology identifier | glyphshapetech [FOCA-3-111]|

### User-Input Font Reference

A user-input font reference is generally designed to be used by people who might not be knowledgeable about font technology, but who wish to use different fonts in their document. Processing applications have a high degree of freedom in how they can present information to users and can hide most of the technical detail from the users, mapping the user's input to the specifics required by the data stream. The user-input font reference can be contained in a reference book containing examples and the corresponding font resource names that users type at their workstation. The font reference can be a workstation displayed list of available fonts from which users select the desired item, or it can be a series of pop-up pull-down menus from which users select various font characteristics (for example: bold, serif, 10 pt., italic) that are desired. [FOCA-3-112]

Because the font resources available at any one workstation might not be available at all other workstations in a distributed environment, the user-input font reference should be independent of specific resource names and should focus instead on getting as much information as possible about the user's desired intent (for example, highlight this phrase with the bold version of the current font). Whatever technique is used for user input, the user's selection must be translatable or mappable into the revisable-document font reference. [FOCA-3-113]

### Revisable-Document Font Reference

A revisable-document font reference is generally designed to focus on the user's intent for text appearance, with little or no emphasis on specific font resources or font metrics. The document can then be sent to any location, formatted using the best available font resource (that satisfies the user's intent), and printed or displayed for the recipient's use. [FOCA-3-114]

Specification of the code page used for encoding the document is necessary at the revisable-document level, but the code page does not have to be linked to a font resource at this stage. The code page does not need to appear in the font reference (although it can if the document data stream architecture also uses the font reference to define the document encoding). [FOCA-3-115]

The following table identifies the AFP parameters and ISO properties that should appear in a revisable document font reference. [FOCA-3-116]

#### Table 7. AFP Parameters Mapped to ISO Properties for Revisable-Document Font References

| AFP Parameter | ISO Property |
| :--- | :--- |
| resource name | fontname [FOCA-3-117]|
| design source | dsnsource [FOCA-3-118]|
| family name | fontfamily [FOCA-3-119]|
| posture class | posture [FOCA-3-120]|
| weight class | weight [FOCA-3-121]|
| width class | propwidth [FOCA-3-122]|
| vertical font size | dsnsize [FOCA-3-123]|
| font classification | dsngroup [FOCA-3-124]|
| structure class | structure [FOCA-3-125]|
| font type flags | escclass [FOCA-3-126]|
| character rotation | nomescdir [FOCA-3-127]|

### Presentation-Document Font Reference

A presentation-document font reference is generally designed to focus on the formatter's intent for page layout, while recalling the user's intent for text appearance. The presentation-document font reference should contain all of the revisable-document font reference information along with enough additional information so as to identify the metrics used for page layout. The document can then be sent to any location, and the required font can be located, or an alternate font that closely approximates the metrics can be substituted (see “Font Selection and Substitution”). [FOCA-3-128]

Specification of the glyph complement (graphic character set) that corresponds to the code page used for encoding the document is necessary at the presentation-document level, but the code page need not be linked to the font resource at this stage. [FOCA-3-129]

#### Table 8. AFP Parameters Mapped to ISO Properties for Presentation-Document Font References

| AFP Parameter | ISO Property |
| :--- | :--- |
| resource name | fontname [FOCA-3-130]|
| function set code | standardversion [FOCA-3-131]|
| typeface name | typeface [FOCA-3-132]|
| design source | dsnsource [FOCA-3-133]|
| data source | datasource [FOCA-3-134]|
| family name | fontfamily [FOCA-3-135]|
| posture class | posture [FOCA-3-136]|
| weight class | weight [FOCA-3-137]|
| width class | propwidth [FOCA-3-138]|
| primary GCSGID | incglyphcols [FOCA-3-139]|
| vertical font size | dsnsize [FOCA-3-140]|
| font classification | dsngroup [FOCA-3-141]|
| structure class | structure [FOCA-3-142]|
| font type flags | escclass [FOCA-3-143]|
| character rotation | nomescdir [FOCA-3-144]|
| average escapement | avgescx/y [FOCA-3-145]|
| average lowercase escapement | avglcescx/y [FOCA-3-146]|
| average capital escapement | avgcapescx/y [FOCA-3-147]|
| figure space increment | tabescx/y [FOCA-3-148]|
| maximum baseline extent | maxfontext [FOCA-3-149]|

### Device Data Stream Font Reference

A device data stream font reference is generally designed to focus on the presentation process's intent for glyph shape rendering, while providing enough reference information to identify the required font information under many different device-specific font resource names. [FOCA-3-150]

Specification of the glyph complement (graphic character set) and the corresponding code page used for encoding the document is necessary at the device data stream level. [FOCA-3-151]

#### Table 9. AFP Parameters Mapped to ISO Properties for Device Data Stream Font References

| AFP Parameter | ISO Property |
| :--- | :--- |
| resource name | fontname [FOCA-3-152]|
| design source | dsnsource [FOCA-3-153]|
| data source | datasource [FOCA-3-154]|
| family name | fontfamily [FOCA-3-155]|
| posture class | posture [FOCA-3-156]|
| weight class | weight [FOCA-3-157]|
| width class | propwidth [FOCA-3-158]|
| primary GCSGID | incglyphcols [FOCA-3-159]|
| primary CPGID | (not supported) [FOCA-3-160]|
| vertical font size | dsnsize [FOCA-3-161]|
| font classification | dsngroup [FOCA-3-162]|
| structure class | structure [FOCA-3-163]|
| font type flags | escclass [FOCA-3-164]|
| character rotation | nomescdir [FOCA-3-165]|
| average escapement | avgescx/y [FOCA-3-166]|
| average lowercase escapement | avglcescx/y [FOCA-3-167]|
| average capital escapement | avgcapescx/y [FOCA-3-168]|
| figure space increment | tabescx/y [FOCA-3-169]|
| maximum baseline extent | maxfontext [FOCA-3-170]|
| pattern technology identifier | glyphshapetech [FOCA-3-171]|

## Font Selection and Substitution

When an application process encounters a font reference, it is necessary for the process to locate a font resource that corresponds to that reference. **Font selection** is the process of locating a font resource that corresponds to the font reference or that satisfies all the information contained in the font reference. **Font substitution** is the process of locating an alternate font resource that approximates the font resource, because no font resource could be found that met the selection criteria. [FOCA-3-172]

Font selection is most simply performed by having an exact match of a font resource name to a font reference that contains the font resource name. But, in a distributed processing environment, the number and combination of font resource names is without limit. The chances of an exact match for a font resource name diminishes as the number of users in a distributed environment increases. [FOCA-3-173]

Font substitution can be as simple as using a system or device default font (for example, substituting fixed pitch Courier whenever the requested font cannot be found), but that is often disappointing to the user. Font selection or substitution is most effective when all of the identifying characteristics contained in a font reference are intelligently compared to the identifying characteristics contained in all of the available font resources, until an exact or best match is located. [FOCA-3-174]

The specific data formats and processing steps required for intelligent font substitution is outside the scope of this architecture. However, the following sections provide guidelines for various selection and substitution situations. [FOCA-3-175]

### Identifying User Intent

The document data stream should contain information about a user's intent for presentation. The success of font substitution is described in terms of fidelity. There are five classes of fidelity, described in order of increasing fidelity: [FOCA-3-176]

*   **Content fidelity**: Permits a user to identify the characters used in the document, or to identify a character that cannot be presented. [FOCA-3-177]
*   **Format fidelity**: Preserves line and cell identity. A user must be able to identify the basic parts of the logical document structure. [FOCA-3-178]
*   **Layout fidelity**: Maintains the integrity of line endings, column endings, and page endings. Maintains the relationship between areas. [FOCA-3-179]
*   **Appearance fidelity**: Requires a document, or area, to appear as specified by the user. Substitution of visually equivalent fonts is permitted. [FOCA-3-180]
*   **Absolute fidelity**: Requires a document to appear precisely as specified by the user. No substitution of fonts is permitted. [FOCA-3-181]

### Document Editing

The document-editing process provides the interface between the user input and the revisable document data stream output. The preferred method is to take the information provided by the user input and select an available font resource that satisfies the user's intent, and then copy the required font parameters from the selected font resource into the corresponding parameters of the revisable-document font reference. [FOCA-3-182]

For the document editing process, the following default order should be used for font selection: [FOCA-3-183]

1.  **Content-level fidelity**: The selected font resource must contain the glyphs needed to render the code points contained in the document data stream (GCSGID). [FOCA-3-184]
2.  **Format- and layout-level fidelity**: Takes precedence over appearance-level fidelity. [FOCA-3-185]
    *   Vertical font size [FOCA-3-186]
    *   Escapement direction [FOCA-3-187]
    *   Escapement class [FOCA-3-188]
3.  **Appearance-level fidelity**: [FOCA-3-189]
    *   Structure class [FOCA-3-190]
    *   Posture class [FOCA-3-191]
    *   Weight class [FOCA-3-192]
    *   Width class [FOCA-3-193]
    *   Font classification [FOCA-3-194]
    *   Family name [FOCA-3-195]
    *   Design source [FOCA-3-196]

### Document Formatting

The document-formatting process provides the interface between the revisable-document data stream input and the presentation-document data stream output. [FOCA-3-197]

For the document formatting process, the following default order should be used for font selection (same as for document editing): [FOCA-3-198]

1.  **Content-level fidelity**: Selected font resource must contain required glyphs (GCSGID). [FOCA-3-199]
2.  **Format- and layout-level fidelity**: Vertical font size, Escapement direction, Escapement class. [FOCA-3-200]
3.  **Appearance-level fidelity**: Structure, Posture, Weight, Width classes, Font classification, Family name, Design source. [FOCA-3-201]

### Document Presentation

The document-presentation process provides the interface between the presentation-document data stream input and the device data stream output. [FOCA-3-202]

For the document-presentation process, the following default order should be used for font selection: [FOCA-3-203]

1.  **Content-level fidelity**: Selected font resource must contain required glyphs (GCSGID). [FOCA-3-204]
2.  **Format- and layout-level fidelities**: Takes precedence over appearance-level fidelity after the document has been formatted. [FOCA-3-205]
    *   Vertical font size [FOCA-3-206]
    *   Escapement direction [FOCA-3-207]
    *   Escapement class [FOCA-3-208]
    *   Maximum extents [FOCA-3-209]
    *   Average escapement (General, Lowercase, Capital) [FOCA-3-210]
    *   Tabular escapement (figure space increment) [FOCA-3-211]
3.  **Appearance-level fidelity**: Structure, Posture, Weight, Width classes, Font classification, Family name, Design source. [FOCA-3-212]
