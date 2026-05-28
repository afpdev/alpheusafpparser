# Chapter 1. A Presentation Architecture Perspective

This chapter provides a brief overview of Presentation Architecture. [MOCA-1-001]

## The Presentation Environment

Figure 1 shows today's presentation environment. [MOCA-1-002]

**Figure 1. Presentation Environment.** The environment is a coordinated set of services architected to meet the presentation needs of today's applications. [MOCA-1-003]

* **Document Creation Services** [MOCA-1-004]
  - import/export [MOCA-1-005]
  - edit/revise [MOCA-1-006]
  - format [MOCA-1-007]
  - scan
  - transform [MOCA-1-008]
* **Document Archiving Services** [MOCA-1-009]
  - store
  - retrieve [MOCA-1-010]
  - index
  - search [MOCA-1-011]
  - extract [MOCA-1-012]
* **Document Viewing Services** [MOCA-1-013]
  - browse [MOCA-1-014]
  - navigate [MOCA-1-015]
  - search [MOCA-1-016]
  - clip
  - annotate [MOCA-1-017]
  - tag
* **Document Printing Services** [MOCA-1-018]
  - print
  - submit [MOCA-1-019]
  - distribute [MOCA-1-020]
  - manage [MOCA-1-021]
  - print
  - finish [MOCA-1-022]

The ability to create, store, retrieve, view, and print data in presentation formats friendly to people is a key requirement in almost every application of computers and information processing. This requirement is becoming increasingly difficult to meet because of the number of applications, servers, and devices that must interoperate to satisfy today's presentation needs. [MOCA-1-023]

The solution is a presentation architecture base that is both robust and open ended, and easily adapted to accommodate the growing needs of the open system environment. AFP architectures provide that base by defining interchange formats for data streams and objects that enable applications, services, and devices to communicate with one another to perform presentation functions. These presentation functions might be part of an integrated system solution or they might be totally separated from one another in time and space. AFP architectures provide structures that support object-oriented models and client/server environments. [MOCA-1-024]

AFP architectures define interchange formats that are system independent and are independent of any particular format used for physically transmitting or storing data. Where appropriate, AFP architectures use industry and international standards, such as the ITU-TSS (formerly known as CCITT) facsimile standards for compressed image data. [MOCA-1-025]

## Architecture Components

AFP architectures provide the means for representing documents in a data format that is independent of the methods used to capture or create them. Documents can contain combinations of text, image, graphics, and bar code objects in presentation-system-independent and resolution-independent formats. Documents can contain fonts, overlays, and other resource objects required at presentation time to present the data properly. Finally, documents can contain resource objects, such as a document index and tagging elements supporting the search and navigation of document data, for a variety of application purposes. [MOCA-1-026]

The presentation architecture components are divided into two major categories: data streams and objects. [MOCA-1-027]

### Data Streams

A data stream is a continuous ordered stream of data elements and objects conforming to a given format. Application programs can generate data streams destined for a presentation service, archive library, presentation device, or another application program. The strategic presentation data stream architectures are:
* **Mixed Object Document Content Architecture (MO:DCA)** [MOCA-1-028]
* **Intelligent Printer Data Stream (IPDS) Architecture** [MOCA-1-029]

The **MO:DCA architecture** defines the data stream used by applications to describe documents and object envelopes for interchange with other applications and application services. The MO:DCA format supports storing and retrieving documents in an archive, viewing, annotation, and printing of documents or parts of documents in local or distributed systems environments. Presentation fidelity is accommodated by including resource objects in the documents that reference them. [MOCA-1-030]

The **IPDS architecture** defines the data stream used by print server programs and device drivers to manage all-points-addressable page printing on a full spectrum of devices from low-end workstation and local area network-attached (LAN-attached) printers to high-speed, high-volume page printers for production jobs, shared printing, and mailroom applications. The same object content architectures carried in a MO:DCA data stream can be carried in an IPDS data stream to be interpreted and presented by microcode executing in printer hardware. The IPDS architecture defines bidirectional command protocols for query, resource management, and error recovery. The IPDS architecture also provides interfaces for document finishing operations provided by pre-processing and post-processing devices attached to IPDS printers. [MOCA-1-031]

**Figure 2. Presentation Model.** This diagram shows the major components in a presentation system and their use of data stream and object architectures. [MOCA-1-032]

#### Presentation Architecture Model
* **Application** [MOCA-1-033]
* **Print Services** [MOCA-1-034]
* **Viewing Services** [MOCA-1-035]
* **Archive Services** [MOCA-1-036]
* **Device** [MOCA-1-037]
* **Library** [MOCA-1-038]
* **Printer** [MOCA-1-039]
* **Post Processor** [MOCA-1-040]

#### Resource Objects
* Fonts
* Overlays [MOCA-1-041]
* Page Segments [MOCA-1-042]
* Form Definition [MOCA-1-043]
* Color Management Resources [MOCA-1-044]
* Color Table [MOCA-1-045]
* Document Index [MOCA-1-046]
* Metadata [MOCA-1-047]

#### Data Objects
* Text
* Image
* Graphics [MOCA-1-048]
* Bar Codes [MOCA-1-049]
* Object Containers [MOCA-1-050]
* Other Objects [MOCA-1-051]

### Objects

Documents can be made up of different kinds of data, such as text, graphics, image, and bar code. Object content architectures describe the structure and content of each type of data format that can exist in a document or appear in a data stream. Objects can be either data objects or resource objects. [MOCA-1-052]

A **data object** contains a single type of presentation data, that is, presentation text, vector graphics, raster image, or bar codes, and all of the controls required to present the data. [MOCA-1-053]

A **resource object** is a collection of presentation instructions and data. These objects are referenced by name in the presentation data stream and can be stored in system libraries so that multiple applications and the print server can use them. [MOCA-1-054]

All object content architectures (OCAs) are totally self-describing and independently defined. When multiple objects are composed on a page, they exist as peer objects that can be individually positioned and manipulated to meet the needs of the presentation application. [MOCA-1-055]

The AFPC-defined object content architectures are:
* **Presentation Text Object Content Architecture (PTOCA)**: A data architecture for describing text objects that have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition. [MOCA-1-056]
* **Image Object Content Architecture (IOCA)**: A data architecture for describing resolution-independent image objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition. [MOCA-1-057]
* **Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA)**: A version of GOCA that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition. [MOCA-1-058]
* **Bar Code Object Content Architecture (BCOCA)**: A data architecture for describing bar code objects, using a number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition. [MOCA-1-059]
* **Font Object Content Architecture (FOCA)**: A resource architecture for describing the structure and content of fonts referenced by presentation data objects in the document. [MOCA-1-060]
* **Color Management Object Content Architecture (CMOCA)**: A resource architecture used to carry the color management information required to render presentation data. [MOCA-1-061]
* **Metadata Object Content Architecture (MOCA)**: A resource architecture used to carry metadata in an AFP environment. [MOCA-1-062]

The MO:DCA and IPDS architectures also support data objects that are not defined by object content architectures. Examples of such objects are Tag Image File Format (TIFF), Encapsulated PostScript® (EPS), and Portable Document Format (PDF). Such objects can be carried in a MO:DCA envelope called an object container, or they can be referenced without being enveloped in MO:DCA structures. [MOCA-1-063]

In addition to object content architectures, the MO:DCA architecture defines envelope architectures for objects of common value in the presentation environment. Examples of these are Form Definition resource objects for managing the production of pages on the physical media, overlay resource objects that accommodate electronic storage of forms data, and index resource objects that support indexing and tagging of pages in a document. [MOCA-1-064]

**Figure 3. Presentation Page.** This is an example of a mixed-object page that can be composed in a presentation-system-independent MO:DCA format and printed on an IPDS printer.
* **Page** [MOCA-1-065]
  - Presentation Text Object(s) [MOCA-1-066]
  - Graphics Object [MOCA-1-067]
  - Image Object [MOCA-1-068]
  - Letterhead can be an overlay resource containing text, image, and graphics objects [MOCA-1-069]
  - Object areas can overlap [MOCA-1-070]
