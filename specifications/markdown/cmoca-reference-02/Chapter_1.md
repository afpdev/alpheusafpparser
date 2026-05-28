# Chapter 1. A Presentation Architecture Perspective

This chapter provides a brief overview of Presentation Architecture. [CMOCA-1-001]

## The Presentation Environment

Figure 1 shows today's presentation environment. [CMOCA-1-002]

**Figure 1. Presentation Environment.** The environment is a coordinated set of services architected to meet the presentation needs of today's applications. [CMOCA-1-003]

*   **Document Creation Services** [CMOCA-1-004]
    *   import/export [CMOCA-1-005]
    *   edit/revise [CMOCA-1-006]
    *   format [CMOCA-1-007]
    *   scan
    *   transform [CMOCA-1-008]
*   **Document Archiving Services** [CMOCA-1-009]
    *   store
    *   retrieve [CMOCA-1-010]
    *   index
    *   search [CMOCA-1-011]
    *   extract [CMOCA-1-012]
*   **Document Viewing Services** [CMOCA-1-013]
    *   browse [CMOCA-1-014]
    *   navigate [CMOCA-1-015]
    *   search [CMOCA-1-016]
    *   clip
    *   annotate [CMOCA-1-017]
    *   tag
*   **Document Printing Services** [CMOCA-1-018]
    *   print
    *   submit [CMOCA-1-019]
    *   distribute [CMOCA-1-020]
    *   manage [CMOCA-1-021]
    *   print
    *   finish [CMOCA-1-022]

The ability to create, store, retrieve, view, and print data in presentation formats friendly to people is a key requirement in almost every application of computers and information processing. This requirement is becoming increasingly difficult to meet because of the number of applications, servers, and devices that must interoperate to satisfy today's presentation needs. [CMOCA-1-023]

The solution is a presentation architecture base that is both robust and open ended, and easily adapted to accommodate the growing needs of the open system environment. AFP architectures provide that base by defining interchange formats for data streams and objects that enable applications, services, and devices to communicate with one another to perform presentation functions. These presentation functions might be part of an integrated system solution or they might be totally separated from one another in time and space. AFP architectures provide structures that support object-oriented models and client/server environments. [CMOCA-1-024]

AFP architectures define interchange formats that are system independent and are independent of any particular format used for physically transmitting or storing data. Where appropriate, AFP architectures use industry and international standards, such as the ITU-TSS (formerly known as CCITT) facsimile standards for compressed image data. [CMOCA-1-025]

## Architecture Components

AFP architectures provide the means for representing documents in a data format that is independent of the methods used to capture or create them. Documents can contain combinations of text, image, graphics, and bar code objects in presentation-system-independent and resolution-independent formats. Documents can contain fonts, overlays, and other resource objects required at presentation time to present the data properly. Finally, documents can contain resource objects, such as a document index and tagging elements supporting the search and navigation of document data, for a variety of application purposes. [CMOCA-1-026]

The presentation architecture components are divided into two major categories: data streams and objects. [CMOCA-1-027]

### Data Streams

A data stream is a continuous ordered stream of data elements and objects conforming to a given format. Application programs can generate data streams destined for a presentation service, archive library, presentation device, or another application program. The strategic presentation data stream architectures are: [CMOCA-1-028]

*   **Mixed Object Document Content Architecture (MO:DCA)** [CMOCA-1-029]
*   **Intelligent Printer Data Stream (IPDS) Architecture** [CMOCA-1-030]

The MO:DCA architecture defines the data stream used by applications to describe documents and object envelopes for interchange with other applications and application services. The MO:DCA format supports storing and retrieving documents in an archive, viewing, annotation, and printing of documents or parts of documents in local or distributed systems environments. Presentation fidelity is accommodated by including resource objects in the documents that reference them. [CMOCA-1-031]

The IPDS architecture defines the data stream used by print server programs and device drivers to manage all-points-addressable page printing on a full spectrum of devices from low-end workstation and local area network-attached (LAN-attached) printers to high-speed, high-volume page printers for production jobs, shared printing, and mailroom applications. The same object content architectures carried in a MO:DCA data stream can be carried in an IPDS data stream to be interpreted and presented by microcode executing in printer hardware. The IPDS architecture defines bidirectional command protocols for query, resource management, and error recovery. The IPDS architecture also provides interfaces for document finishing operations provided by pre-processing and post-processing devices attached to IPDS printers. [CMOCA-1-032]

Figure 2 shows a system model relating MO:DCA and IPDS data streams to the presentation environment previously described. Also shown in the model are the object content architectures that apply to all levels of presentation processing in a system. [CMOCA-1-033]

**Figure 2. Presentation Model.** This diagram shows the major components in a presentation system and their use of data stream and object architectures. [CMOCA-1-034]

*   **Data Objects** [CMOCA-1-035]
    *   Text
    *   Image
    *   Graphics [CMOCA-1-036]
    *   Bar Codes [CMOCA-1-037]
    *   Object Containers [CMOCA-1-038]
    *   Other Objects [CMOCA-1-039]
*   **Resource Objects** [CMOCA-1-040]
    *   Fonts
    *   Overlays [CMOCA-1-041]
    *   Page Segments [CMOCA-1-042]
    *   Form Definition [CMOCA-1-043]
    *   Color Management Resources [CMOCA-1-044]
    *   Color Table [CMOCA-1-045]
    *   Document Index [CMOCA-1-046]
    *   Metadata [CMOCA-1-047]

### Objects

Documents can be made up of different kinds of data, such as text, graphics, image, and bar code. Object content architectures describe the structure and content of each type of data format that can exist in a document or appear in a data stream. Objects can be either data objects or resource objects. [CMOCA-1-048]

A data object contains a single type of presentation data, that is, presentation text, vector graphics, raster image, or bar codes, and all of the controls required to present the data. [CMOCA-1-049]

A resource object is a collection of presentation instructions and data. These objects are referenced by name in the presentation data stream and can be stored in system libraries so that multiple applications and the print server can use them. [CMOCA-1-050]

All object content architectures (OCAs) are totally self-describing and independently defined. When multiple objects are composed on a page, they exist as peer objects that can be individually positioned and manipulated to meet the needs of the presentation application. [CMOCA-1-051]

The AFPC-defined object content architectures are: [CMOCA-1-052]

*   **Presentation Text Object Content Architecture (PTOCA):** A data architecture for describing text objects that have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition. [CMOCA-1-053]
*   **Image Object Content Architecture (IOCA):** A data architecture for describing resolution-independent image objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition. [CMOCA-1-054]
*   **Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA):** A version of GOCA that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition. [CMOCA-1-055]
*   **Bar Code Object Content Architecture (BCOCA):** A data architecture for describing bar code objects, using a number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition. [CMOCA-1-056]
*   **Font Object Content Architecture (FOCA):** A resource architecture for describing the structure and content of fonts referenced by presentation data objects in the document. [CMOCA-1-057]
*   **Color Management Object Content Architecture (CMOCA):** A resource architecture used to carry the color management information required to render presentation data. [CMOCA-1-058]
*   **Metadata Object Content Architecture (MOCA):** A resource architecture used to carry metadata in an AFP environment. [CMOCA-1-059]

The MO:DCA and IPDS architectures also support data objects that are not defined by object content architectures. Examples of such objects are Tag Image File Format (TIFF), Encapsulated PostScript® (EPS), and Portable Document Format (PDF). Such objects can be carried in a MO:DCA envelope called an object container, or they can be referenced without being enveloped in MO:DCA structures. [CMOCA-1-060]

In addition to object content architectures, the MO:DCA architecture defines envelope architectures for objects of common value in the presentation environment. Examples of these are Form Definition resource objects for managing the production of pages on the physical media, overlay resource objects that accommodate electronic storage of forms data, and index resource objects that support indexing and tagging of pages in a document. [CMOCA-1-061]

Figure 3 shows an example of an all-points-addressable page composed of multiple presentation objects. [CMOCA-1-062]

**Figure 3. Presentation Page.** This is an example of a mixed-object page that can be composed in a presentation-system-independent MO:DCA format and printed on an IPDS printer. [CMOCA-1-063]
