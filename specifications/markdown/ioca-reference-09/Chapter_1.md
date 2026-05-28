# Chapter 1. A Presentation Architecture Perspective
This chapter provides a brief overview of Presentation Architecture.
The Presentation Environment Figure 1 shows today's presentation environment.
Figure 1. Presentation Environment. The environment is a coordinated set of services architected to meet the presentation needs of today's applications.
import/export edit/revise format scan transform store retrieve index
search extract browse navigate search clip annotate tag
print submit distribute manage print finish Document Creation
Services Document Archiving Services Document Printing Services Document
Viewing Services The ability to create, store, retrieve, view, and print data in presentation formats friendly to people is a key requirement in almost every application of computers and information processing. This requirement is becoming increasingly difficult to meet because of the number of applications, servers, and devices that must interoperate to satisfy today's presentation needs.
The solution is a presentation architecture base that is both robust and open ended, and easily adapted to accommodate the growing needs of the open system environment. AFP architectures provide that base by defining interchange formats for data streams and objects that enable applications, services, and devices to communicate with one another to perform presentation functions. These presentation functions might be part of an integrated system solution or they might be totally separated from one another in time and space. AFP architectures provide structures that support object-oriented models and client/server environments.
AFP architectures define interchange formats that are system independent and are independent of any particular format used for physically transmitting or storing data. Where appropriate, AFP architectures use industry and international standards, such as the ITU-TSS (formerly known as CCITT) facsimile standards for compressed image data. [IOCA-1-001]


Architecture Components AFP architectures provide the means for representing documents in a data format that is independent of the methods used to capture or create them. Documents can contain combinations of text, image, graphics, and bar code objects in presentation-system-independent and resolution-independent formats. Documents can contain fonts, overlays, and other resource objects required at presentation time to present the data properly.
Finally, documents can contain resource objects, such as a document index and tagging elements supporting the search and navigation of document data, for a variety of application purposes.
The presentation architecture components are divided into two major categories: data streams and objects.
Data Streams A data stream is a continuous ordered stream of data elements and objects conforming to a given format.
Application programs can generate data streams destined for a presentation service, archive library, presentation device, or another application program. The strategic presentation data stream architectures are:
• Mixed Object Document Content Architecture (MO:DCA) [IOCA-1-002]
• Intelligent Printer Data Stream (IPDS) Architecture. [IOCA-1-003]
The MO:DCA architecture defines the data stream used by applications to describe documents and object envelopes for interchange with other applications and application services. The MO:DCA format supports storing and retrieving documents in an archive, viewing, annotation, and printing of documents or parts of documents in local or distributed systems environments. Presentation fidelity is accommodated by including resource objects in the documents that reference them.
The IPDS architecture defines the data stream used by print server programs and device drivers to manage all-points-addressable page printing on a full spectrum of devices from low-end workstation and local area network-attached (LAN-attached) printers to high-speed, high-volume page printers for production jobs, shared printing, and mailroom applications. The same object content architectures carried in a MO:DCA data stream can be carried in an IPDS data stream to be interpreted and presented by microcode executing in printer hardware. The IPDS architecture defines bidirectional command protocols for query, resource management, and error recovery. The IPDS architecture also provides interfaces for document finishing operations provided by pre-processing and post-processing devices attached to IPDS printers.
Architecture Components [IOCA-1-004]


Figure 2 shows a system model relating MO:DCA and IPDS data streams to the presentation environment previously described. Also shown in the model are the object content architectures that apply to all levels of presentation processing in a system.
Figure 2. Presentation Model. This diagram shows the major components in a presentation system and their use of data stream and object architectures.
Data Objects Resource Objects Object Architectures MO:DCA to presentation servers IPDS
to printers and post processors Presentation Architecture Model Intermediate Device Post Processor PrinterPrint
Services Viewing Services Archive Services Specifies open architectures and international standards that allow interoperability and portability of data, applications, and skills.
Appli- cation Display Library Resource Fonts Overlays Page Segments Form Definition
Color Management Resources Color Table Document Index Metadata Text Image Graphics Bar Codes
Object Containers Other Objects Architecture Components [IOCA-1-005]


Objects Documents can be made up of different kinds of data, such as text, graphics, image, and bar code. Object content architectures describe the structure and content of each type of data format that can exist in a document or appear in a data stream. Objects can be either data objects or resource objects.
A data object contains a single type of presentation data, that is, presentation text, vector graphics, raster image, or bar codes, and all of the controls required to present the data.
A resource object is a collection of presentation instructions and data. These objects are referenced by name in the presentation data stream and can be stored in system libraries so that multiple applications and the print server can use them.
All object content architectures (OCAs) are totally self-describing and independently defined. When multiple objects are composed on a page, they exist as peer objects that can be individually positioned and manipulated to meet the needs of the presentation application.
The AFPC-defined object content architectures are:
• Presentation Text Object Content Architecture (PTOCA): A data architecture for describing text objects that [IOCA-1-006]
have been formatted for all-points-addressable presentations. Specifications of fonts, text color, and other visual attributes are included in the architecture definition.
• Image Object Content Architecture (IOCA): A data architecture for describing resolution-independent image [IOCA-1-007]
objects captured from a number of different sources. Specifications of recording formats, data compression, color, and grayscale encoding are included in the architecture definition.
• Graphics Object Content Architecture for Advanced Function Presentation (AFP GOCA): A version of GOCA [IOCA-1-008]
that is used in Advanced Function Presentation (AFP) environments. GOCA is a data architecture for describing vector graphics picture objects and line art drawings for a variety of applications. Specification of drawing primitives, such as lines, arcs, areas, and their visual attributes, are included in the architecture definition.
• Bar Code Object Content Architecture (BCOCA): A data architecture for describing bar code objects, using a [IOCA-1-009]
number of different symbologies. Specification of the data to be encoded and the symbology attributes to be used are included in the architecture definition.
• Font Object Content Architecture (FOCA): A resource architecture for describing the structure and content of [IOCA-1-010]
fonts referenced by presentation data objects in the document.
• Color Management Object Content Architecture (CMOCA): A resource architecture used to carry the color [IOCA-1-011]
management information required to render presentation data.
• Metadata Object Content Architecture (MOCA): A resource architecture used to carry metadata in an AFP [IOCA-1-012]
environment.
The MO:DCA and IPDS architectures also support data objects that are not defined by object content architectures. Examples of such objects are T ag Image File Format (TIFF), Encapsulated PostScript ® (EPS), and Portable Document Format (PDF). Such objects can be carried in a MO:DCA envelope called an object container, or they can be referenced without being enveloped in MO:DCA structures.
In addition to object content architectures, the MO:DCA architecture defines envelope architectures for objects of common value in the presentation environment. Examples of these are Form Definition resource objects for managing the production of pages on the physical media, overlay resource objects that accommodate electronic storage of forms data, and index resource objects that support indexing and tagging of pages in a document.
Architecture Components [IOCA-1-013]


Figure 3 shows an example of an all-points-addressable page composed of multiple presentation objects.
Figure 3. Presentation Page. This is an example of a mixed-object page that can be composed in a presentation-system-independent MO:DCA format and printed on an IPDS printer.
To:  Joan Rogers Dear Joan:
Security Systems, Inc.
205 Main Street Plains, Iowa Sales have improved so dramatically since you have joined our team, I would like to know your techniques. [IOCA-1-014]
Page Presentation Text Object(s)
Graphics Object Image Object Letterhead can be an overlay resource containing text, image, and graphics objects Object areas can overlap Let’s get together and discuss your promotion!
Jim D. Bolt Architecture Components [IOCA-1-015]




