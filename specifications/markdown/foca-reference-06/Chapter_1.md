# Chapter 1. A Presentation Architecture Perspective

This chapter provides a brief overview of Presentation Architecture. [FOCA-1-001]

## The Presentation Environment

Figure 1 shows today’s presentation environment. [FOCA-1-002]

**Figure 1. Presentation Environment.** The environment is a coordinated set of services architected to meet the presentation needs of today’s applications. [FOCA-1-003]

*   **Document Creation Services**: import/export, edit/revise, format, scan, transform [FOCA-1-004]
*   **Document Archiving Services**: store, retrieve, index, search, extract [FOCA-1-005]
*   **Document Viewing Services**: browse, navigate, search, clip, annotate, tag [FOCA-1-006]
*   **Document Printing Services**: print, submit, distribute, manage, print, finish [FOCA-1-007]

The ability to create, store, retrieve, view, and print data in presentation formats friendly to people is a key requirement in almost every application of computers and information processing. This requirement is becoming increasingly difficult to meet because of the number of applications, servers, and devices that must interoperate to satisfy today’s presentation needs. [FOCA-1-008]

The solution is a presentation architecture base that is both robust and open ended, and easily adapted to accommodate the growing needs of the open system environment. AFP presentation architectures provide that base by defining interchange formats for data streams and objects that enable applications, services, and devices to communicate with one another to perform presentation functions. These presentation functions might be part of an integrated system solution or they might be totally separated from one another in time and space. AFP presentation architectures provide structures that support object-oriented models and client/server environments. [FOCA-1-009]

AFP presentation architectures define interchange formats that are system independent and are independent of any particular format used for physically transmitting or storing data. Where appropriate, AFP presentation architectures use industry and international standards, such as the ITU-TSS (formerly known as CCITT) facsimile standards for compressed image data. [FOCA-1-010]

## Architecture Components

AFP presentation architectures provide the means for representing documents in a data format that is independent of the methods used to capture or create them. Documents can contain combinations of text, image, graphics, and bar code objects in device-independent and resolution-independent formats. Documents can contain fonts, overlays, and other resource objects required at presentation time to present the data properly. Finally, documents can contain resource objects, such as a document index and tagging elements supporting the search and navigation of document data, for a variety of application purposes. [FOCA-1-011]

The presentation architecture components are divided into two major categories: data streams and objects. [FOCA-1-012]

### Data Streams

A data stream is a continuous ordered stream of data elements and objects conforming to a given format. Application programs can generate data streams destined for a presentation service, archive library, presentation device, or another application program. The strategic presentation data stream architectures are: [FOCA-1-013]

*   **Mixed Object Document Content Architecture (MO:DCA)** [FOCA-1-014]
*   **Intelligent Printer Data Stream (IPDS) Architecture** [FOCA-1-015]

The MO:DCA architecture defines the data stream used by applications to describe documents and object envelopes for interchange with other applications and application services. Documents defined in the MO:DCA format can be archived in a database, then later retrieved, viewed, annotated, and printed in local or distributed systems environments. Presentation fidelity is accommodated by including resource objects in the documents that reference them. [FOCA-1-016]

The IPDS architecture defines the data stream used by print server programs and device drivers to manage all-points-addressable page printing on a full spectrum of devices from low-end workstation and local area network-attached (LAN-attached) printers to high-speed, high-volume page printers for production jobs, shared printing, and mailroom applications. The same object content architectures carried in a MO:DCA data stream can be carried in an IPDS data stream to be interpreted and presented by microcode executing in printer hardware. The IPDS architecture defines bidirectional command protocols for query, resource management, and error recovery. The IPDS architecture also provides interfaces for document finishing operations provided by pre-processing and post-processing devices attached to IPDS printers. [FOCA-1-017]

Figure 2 shows a system model relating MO:DCA and IPDS data streams to the presentation environment previously described. Also shown in the model are the object content architectures that apply to all levels of presentation processing in a system. [FOCA-1-018]

**Figure 2. Presentation Model.** This diagram shows the major components in a presentation system and their use of data stream and object architectures. [FOCA-1-019]

*   **Data Objects**: Text, Image, Graphics, Bar Codes, Object Containers [FOCA-1-020]
*   **Resource Objects**: Fonts, Overlays, Page Segments, Form Definition, Color Management Resources, Color Table, Document Index, Metadata [FOCA-1-021]

### Objects

Documents can be made up of different kinds of data, such as text, graphics, image, and bar code. Object content architectures describe the structure and content of each type of data format that can exist in a document or appear in a data stream. Objects can be either data objects or resource objects. [FOCA-1-022]

A **data object** contains a single type of presentation data, that is, presentation text, vector graphics, raster image, or bar codes, and all of the controls required to present the data. [FOCA-1-023]

A **resource object** is a collection of presentation instructions and data. These objects are referenced by name in the presentation data stream and can be stored in system libraries so that multiple applications and the print server can use them. [FOCA-1-024]

All object content architectures (OCAs) are totally self-describing and independently defined. When multiple objects are composed on a page, they exist as peer objects, that can be individually positioned and manipulated to meet the needs of the presentation application. [FOCA-1-025]

The AFPC-defined object content architectures are: [FOCA-1-026]

*   **Presentation Text Object Content Architecture (PTOCA)**: A data architecture for describing text objects that have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition. [FOCA-1-027]
*   **Image Object Content Architecture (IOCA)**: A data architecture for describing resolution-independent image objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition. [FOCA-1-028]
*   **Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA)**: A version of GOCA that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition. [FOCA-1-029]
*   **Bar Code Object Content Architecture (BCOCA)**: A data architecture for describing bar code objects, using a number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition. [FOCA-1-030]
*   **Font Object Content Architecture (FOCA)**: A resource architecture for describing the structure and content of fonts referenced by presentation data objects in the document. [FOCA-1-031]
*   **Color Management Object Content Architecture (CMOCA)**: A resource architecture used to carry the color management information required to render presentation data. [FOCA-1-032]
*   **Metadata Object Content Architecture (MOCA)**: A resource architecture used to carry metadata in an AFP environment. [FOCA-1-033]

The MO:DCA and IPDS architectures also support data objects that are not defined by AFPC object content architectures. Examples of such objects are Tag Image File Format (TIFF), Encapsulated PostScript® (EPS), and Portable Document Format (PDF). Such objects can be carried in a MO:DCA envelope called an object container, or they can be referenced without being enveloped in MO:DCA structures. [FOCA-1-034]

In addition to object content architectures, the MO:DCA architecture defines envelope architectures for objects of common value in the presentation environment. Examples of these are Form Definition resource objects for managing the production of pages on the physical media, overlay resource objects that accommodate electronic storage of forms data, and index resource objects that support indexing and tagging of pages in a document. [FOCA-1-035]

Figure 3 shows an example of an all-points-addressable page composed of multiple presentation objects. [FOCA-1-036]

**Figure 3. Presentation Page.** This is an example of a mixed-object page that can be composed in a device-independent MO:DCA format and can be printed on an IPDS printer. [FOCA-1-037]
