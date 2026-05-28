# Chapter 2. Introduction to CMOCA

The Color Management Object Content Architecture (CMOCA) defines objects that provide color management in presentation environments. These objects are called Color Management Resources (CMRs). CMOCA has the following objectives: [CMOCA-2-001]

*   Consistent output across different devices [CMOCA-2-002]
*   Accurate output, to the best of the device capability, from a wide variety of inputs provided that the input color information is properly described [CMOCA-2-003]
*   Consistent output across different data streams [CMOCA-2-004]
*   Flexible controls that enable customers to obtain output to their exact specifications [CMOCA-2-005]

The architecture described in this document is defined in the terms of the AFP architectures to support color management in AFP environments, but care has been taken to make the mechanisms applicable to other presentation environments as well. [CMOCA-2-006]

The device that presents the data could be a printer, a display, or other system. This document frequently references printers but it should be understood that the architecture also applies to displays and other presentation devices. [CMOCA-2-007]

A Color Management Resource (CMR) is an architected resource that is used to carry the color management information required to render a print file, document, page, or data object. Each CMR carries a single type of color management resource. There are five types of CMRs: [CMOCA-2-008]

1.  **Halftone** [CMOCA-2-009]
2.  **Tone Transfer Curve** [CMOCA-2-010]
3.  **Color Conversion** [CMOCA-2-011]
4.  **Link Color Conversion** [CMOCA-2-012]
5.  **Indexed** [CMOCA-2-013]

**Note:** Not all CMR types are applicable for a particular kind of presentation device; for instance, halftones are not applicable for a display. [CMOCA-2-014]

A CMR can reflect processing that has been done on an object, in which case it is referred to as an **audit CMR**, or it can specify processing that is to be done to an object, in which case it is referred to as an **instruction CMR**. Finally, there is a special case of an audit and instruction color conversion pair that has been combined to produce a link color conversion. This combined color conversion is called a **link CMR**. [CMOCA-2-015]

In AFP environments, CMRs are processed as AFP resources by print servers so they can be downloaded once, captured, and used repeatedly without additional downloads. CMRs are also applicable to non-AFP environments such as PostScript, PDF, and PCL®. [CMOCA-2-016]

The primary purpose of the Color Management Object Content Architecture is to provide a standard definition for color management resources that are used for controlling presentation of color objects. “Color objects”, as used in the document, means full-color, grayscale, and monochrome objects. This standardization provides conventions and directions for current and future products to present objects in a consistent way. [CMOCA-2-017]

Development of CMOCA has the following goals: [CMOCA-2-018]

*   To allow a means to represent color management information in any environment [CMOCA-2-019]
*   To use a format that is flexible enough to allow it to exist intact in interactive, presentation, and interchange environments that are defined in the following data stream architectures: [CMOCA-2-020]
    *   **Intelligent Printer Data Stream (IPDS)** and [CMOCA-2-021]
    *   **Mixed Object Document Content Architecture (MO:DCA)** [CMOCA-2-022]
*   To describe the CMR in terms of architected tags [CMOCA-2-023]
*   To use industry-standard constructs when architecting the CMRs [CMOCA-2-024]
*   To allow the CMR to be fully described in device-independent and process-independent terms [CMOCA-2-025]
*   To use a naming convention for the CMRs that allow device-specific color resources to be substituted for generic resources [CMOCA-2-026]
*   To define CMRs so that multiple CMRs can be invoked at one time, and a hierarchy can be searched to determine the appropriate CMRs to use [CMOCA-2-027]

In AFP environments, CMRs will be carried within an object container. CMRs can be associated with a document component at various levels: [CMOCA-2-028]

1.  Print file [CMOCA-2-029]
2.  Document [CMOCA-2-030]
3.  A group of pages or sheets [CMOCA-2-031]
4.  Page/Overlay [CMOCA-2-032]
5.  Data Object - for example, IOCA, EPS, TIFF [CMOCA-2-033]

Within the IPDS data stream, CMRs are activated and deactivated like all other IPDS resources but the CMR is not used until it is explicitly invoked (except for certain Link Color Conversions CMRs, that need not be invoked). Within the device, IPDS hierarchical rules are used to determine which CMRs are actually applied. [CMOCA-2-034]
