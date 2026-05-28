# Chapter 1. A Presentation Architecture Perspective

This chapter provides a brief overview of Presentation Architecture. [GOCA-1-001]

## The Presentation Environment

Figure 1 shows today's presentation environment. [GOCA-1-002]

### Figure 1. Presentation Environment
The environment is a coordinated set of services architected to meet the presentation needs of today's applications. [GOCA-1-003]

| Document Creation Services | Document Archiving Services | Document Printing Services | Document Viewing Services |
| :--- | :--- | :--- | :--- |
| import/export | edit/revise | format | scan [GOCA-1-004]|
| transform | store | retrieve | index [GOCA-1-005]|
| search | extract | browse | navigate [GOCA-1-006]|
| search | clip | annotate | tag [GOCA-1-007]|
| print | submit | distribute | manage [GOCA-1-008]|
| print | finish [GOCA-1-009]| | |

The ability to create, store, retrieve, view, and print data in presentation formats friendly to people is a key requirement in almost every application of computers and information processing. This requirement is becoming increasingly difficult to meet because of the number of applications, servers, and devices that must interoperate to satisfy today's presentation needs.
The solution is a presentation architecture base that is both robust and open ended, and easily adapted to accommodate the growing needs of the open system environment. AFP presentation architectures provide that base by defining interchange formats for data streams and objects that enable applications, services, and devices to communicate with one another to perform presentation functions. These presentation functions might be part of an integrated system solution or they might be totally separated from one another in time and space. AFP presentation architectures provide structures that support object-oriented models and client/server environments. [GOCA-1-010]

AFP presentation architectures define interchange formats that are system independent and are independent of any particular format used for physically transmitting or storing data. Where appropriate, AFP presentation architectures use industry and international standards, such as the ITU-TSS (formerly known as CCITT) facsimile standards for compressed image data. [GOCA-1-011]

---

## Architecture Components

AFP presentation architectures provide the means for representing documents in a data format that is independent of the methods used to capture or create them. Documents can contain combinations of text, image, graphics, and bar code objects in device-independent and resolution-independent formats. Documents can contain fonts, overlays, and other resource objects required at presentation time to present the data properly. Finally, documents can contain resource objects, such as a document index and tagging elements supporting the search and navigation of document data, for a variety of application purposes.
The presentation architecture components are divided into two major categories: data streams and objects. [GOCA-1-012]

### Data Streams

A data stream is a continuous ordered stream of data elements and objects conforming to a given format. Application programs can generate data streams destined for a presentation service, archive library, presentation device, or another application program. The strategic presentation data stream architectures are: [GOCA-1-013]

*   **Mixed Object Document Content Architecture (MO:DCA)** [GOCA-1-014]
*   **Intelligent Printer Data Stream (IPDS) Architecture** [GOCA-1-015]
The MO:DCA architecture defines the data stream used by applications to describe documents and object envelopes for interchange with other applications and application services. Documents defined in the MO:DCA format can be archived in a database, then later retrieved, viewed, annotated, and printed in local or distributed systems environments. Presentation fidelity is accommodated by including resource objects in the documents that reference them. [GOCA-1-016]

The IPDS architecture defines the data stream used by print server programs and device drivers to manage all-points-addressable page printing on a full spectrum of devices from low-end workstation and local area network-attached (LAN-attached) printers to high-speed, high-volume page printers for production jobs, shared printing, and mailroom applications. The same object content architectures carried in a MO:DCA data stream can be carried in an IPDS data stream to be interpreted and presented by microcode executing in printer hardware. The IPDS architecture defines bidirectional command protocols for query, resource management, and error recovery. The IPDS architecture also provides interfaces for document finishing operations provided by pre-processing and post-processing devices attached to IPDS printers. [GOCA-1-017]

---

Figure 2 shows a system model relating MO:DCA and IPDS data streams to the presentation environment previously described. Also shown in the model are the object content architectures that apply to all levels of presentation processing in a system. [GOCA-1-018]

### Figure 2. Presentation Model
This diagram shows the major components in a presentation system and their use of data stream and object architectures. [GOCA-1-019]
| Data Objects | Resource Objects |
| :--- | :--- |
| **Object Architectures** [GOCA-1-020]| |
| MO:DCA to presentation servers [GOCA-1-021]| |
| IPDS to printers and post processors [GOCA-1-022]| |
| **Presentation Architecture Model** [GOCA-1-023]| |
| Intermediate Device | Application [GOCA-1-024]|
| Post Processor | Display [GOCA-1-025]|
| Printer | Library [GOCA-1-026]|
| Print Services | Resource [GOCA-1-027]|
| Viewing Services | Fonts [GOCA-1-028]|
| Archive Services | Overlays [GOCA-1-029]|
| Specifies open architectures and international standards that allow interoperability and portability of data, applications, and skills. | Page Segments [GOCA-1-030]|
| | Form Definition [GOCA-1-031]|
| | Color Management Resources [GOCA-1-032]|
| | Color Table [GOCA-1-033]|
| | Document Index [GOCA-1-034]|
| | Metadata [GOCA-1-035]|
| | Text [GOCA-1-036]|
| | Image [GOCA-1-037]|
| | Graphics [GOCA-1-038]|
| | Bar Codes [GOCA-1-039]|
| | Object Containers [GOCA-1-040]|
| | Other Objects [GOCA-1-041]|

---

### Objects

Documents can be made up of different kinds of data, such as text, graphics, image, and bar code. Object content architectures describe the structure and content of each type of data format that can exist in a document or appear in a data stream. Objects can be either data objects or resource objects.
A data object contains a single type of presentation data, that is, presentation text, vector graphics, raster image, or bar codes, and all of the controls required to present the data. [GOCA-1-042]

A resource object is a collection of presentation instructions and data. These objects are referenced by name in the presentation data stream and can be stored in system libraries so that multiple applications and the print server can use them. [GOCA-1-043]

All object content architectures (OCAs) are totally self-describing and independently defined. When multiple objects are composed on a page, they exist as peer objects that can be individually positioned and manipulated to meet the needs of the presentation application. [GOCA-1-044]

The AFPC-defined object content architectures are: [GOCA-1-045]

*   **Presentation Text Object Content Architecture (PTOCA):** A data architecture for describing text objects that have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition. [GOCA-1-046]
*   **Image Object Content Architecture (IOCA):** A data architecture for describing resolution-independent image objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition. [GOCA-1-047]
*   **Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA):** A version of GOCA that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition. [GOCA-1-048]
*   **Bar Code Object Content Architecture (BCOCA):** A data architecture for describing bar code objects, using a number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition. [GOCA-1-049]
*   **Font Object Content Architecture (FOCA):** A resource architecture for describing the structure and content of fonts referenced by presentation data objects in the document. [GOCA-1-050]
*   **Color Management Object Content Architecture (CMOCA):** A resource architecture used to carry the color management information required to render presentation data. [GOCA-1-051]
*   **Metadata Object Content Architecture (MOCA):** A resource architecture used to carry metadata in an AFP environment. [GOCA-1-052]

The MO:DCA and IPDS architectures also support data objects that are not defined by object content architectures. Examples of such objects are Tag Image File Format (TIFF), Encapsulated PostScript® (EPS), and Portable Document Format (PDF). Such objects can be carried in a MO:DCA envelope called an object container, or they can be referenced without being enveloped in MO:DCA structures. [GOCA-1-053]

In addition to object content architectures, the MO:DCA architecture defines envelope architectures for objects of common value in the presentation environment. Examples of these are Form Definition resource objects for managing the production of pages on the physical media, overlay resource objects that accommodate electronic storage of forms data, and index resource objects that support indexing and tagging of pages in a document. [GOCA-1-054]

---

Figure 3 shows an example of an all-points-addressable page composed of multiple presentation objects. [GOCA-1-055]

### Figure 3. Presentation Page
This is an example of a mixed-object page that can be composed in a device-independent MO:DCA format and can be printed on an IPDS printer. [GOCA-1-056]

**To:** Joan Rogers
**Dear Joan:**
**Security Systems, Inc.**
205 Main Street [GOCA-1-057]
Plains, Iowa

Sales have improved so dramatically since you have joined our team, I would like to know your techniques. [GOCA-1-058]

**Page Elements:**
*   Presentation Text Object(s) [GOCA-1-059]
*   Graphics Object [GOCA-1-060]
*   Image Object [GOCA-1-061]
*   Letterhead can be an overlay resource containing text, image, and graphics objects [GOCA-1-062]
*   Object areas can overlap [GOCA-1-063]

Let’s get together and discuss your promotion!
**Jim D. Bolt**
