# Appendix E. IPDS Environment
The Intelligent Printer Data Stream (IPDS) provides the printer subsystem environment for Image Objects. This appendix describes:
• The context of Image Objects in the IPDS environment [IOCA-E-001]
• IPDS commands specific to images [IOCA-E-002]
• Some special considerations when printing an image [IOCA-E-003]
For further information about the IPDS architecture, refer to Intelligent Printer Data Stream Reference.
IOCA Image Objects in an IPDS Architecture The IPDS architecture provides various commands to control advanced-function printers. It supports all-points- addressable printing functions that allow text and individual image, graphics, and bar code objects to be positioned and presented at any point on the printed page.
Image Objects are described to IPDS printers in terms of Image Segments as defined by IOCA. They are presented in rectangular output areas called object areas. These object areas may be positioned at any addressable point on a page, in an overlay or a page segment definition, and may be defined in any orientation relative to the X-axis of the reference system. The size, position, and orientation of the image object area is defined to the printer by parameters that are specified in the Write Image Control 2 command.
The data within the Image Presentation Space (IPS) can be mapped to the image object area in several different ways, as specified by the Mapping Control Option parameter of the Write Image Control 2 command.
These options are as follows:
Scale to Fit Map the center of the IPS to the center of the object area, and uniformly scale to fit, without changing the aspect ratio of the image. All image data within the IPS is presented when this option is specified.
Scale to Fill Map the center of the IPS to the center of the object area. Scale independently in the X and Y directions so that the object area is filled. The aspect ratio of the image may change. All image data within the IPS is presented when this option is specified.
Center and Trim Map the center of the IPS to the center of the object area without scaling. Excess image data, if any, is trimmed at object area boundaries.
Position and Trim Map the upper left corner of the IPS to the object area without scaling, using the specified offset from the image object area origin. Excess image data, if any, is trimmed at object area boundaries.
Replicate and Trim Map the upper-left corner of the IPS to the object area without scaling, then replicate in both the X and Y directions until the object area is filled. Excess image data, if any, is trimmed at object area boundaries.
Image Point-to-Pel Map the upper-left corner of the IPS to the origin of the object area. Each image point is mapped to a single output pel: that is, no resolution correction is done. Excess image data, if any, is trimmed at object area boundaries.
Image Point-to-Pel with Double Dot Same as Image Point-to-Pel, except that each image point is mapped to four pels in the object area by doubling the image point in both dimensions. No resolution correction is done. Excess image data, if any, is trimmed at object area boundaries.
If the Image Output Control parameters are omitted, the default is Position and Trim. [IOCA-E-004]


Note: If the IOCA object is included in a MO:DCA object and the Map Image Object structured field is not present, the MO:DCA default of Scale to Fit applies and the resulting IPDS contains an explicit Scale to Fit Mapping Control Option. For this reason, the IPDS default is very unlikely to be relevant for most applications.
Resolution correction occurs in the Scale to Fit, Scale to Fill, Center and Trim, Position and Trim, and Replicate and Trim mapping options whenever the resolution of the image points in the IPS, in one or both dimensions, is different from the pel resolution of the printer.
Manipulation of Image Objects can be performed in an IO-Image object state that is entered from any one of three IPDS printer states:
• Page state [IOCA-E-005]
• Overlay state [IOCA-E-006]
• Page segment state [IOCA-E-007]
When the image functions are carried out in the overlay or page segment state, the image data sent to the printer is saved as part of the overlay or page segment definition. It is later included on pages by the Load Copy Control, Include Overlay, or Include Page Segment command.
IPDS IO-Image Command Set The IPDS architecture provides the IO-Image Command Set to convey image information to printers. This Command Set consists of:
• The Write Image Control 2 command, to define where and how to present an Image Object [IOCA-E-008]
• The Write Image 2 command, which contains an Image Segment [IOCA-E-009]
Write Image Control 2 The Write Image Control 2 (WIC2) command is identified by command code X'D63E', and is sent to the printer before the Write Image 2 command. It tells the printer that the Write Image 2 commands that follow are directed to an image object area on the current page, overlay, or page segment.
This command defines the size, placement, and orientation of the image object area. It also establishes the parameters required to interpret the Image Segment.
The Write Image Control 2 data is made up of the following three consecutive self-defining fields:
Image Area Position (IAP)
This mandatory self-defining field defines the position and orientation of the image object area relative to a reference coordinate system.
Image Output Control (IOC)
This optional self-defining field specifies the size of the image object area and mapping option for mapping the Image Presentation Space to the image object area.
If it is omitted, the Position and Trim mapping option applies where the offsets are zero, and the image object area size is the same as the Image Presentation Space size as defined in the IDD self-defining field.
Image Data Descriptor (IDD)
This mandatory self-defining field specifies the parameters that define the Image Presentation Space size, and control parameters required to interpret the Image Segment.
Refer to the Intelligent Printer Data Stream Reference for a complete description of the above self-defining fields.
IPDS Environment


Write Image 2 The Write Image 2 (WI2) command is identified by command code X'D64E'. One or more Write Image 2 commands carry one IOCA Image Segment to the printer.
All Image Segments are executed in Immediate mode. That is, they are not retained or stored as named segments, but processed immediately when the printer receives them.
There are no quantity restrictions on data sent to the printer in a single Write Image 2 command, except for the 32K-length limit of the command. An Image Segment, delimited by the Begin Segment and End Segment self- defining fields, may span two or more consecutive Write Image 2 commands.
The IO-Image Command Set allows for Image Segments that conform to Function Sets, described in Chapter 7, “Compliance”.
Exception Handling A data-stream exception occurs when the printer detects an invalid or unsupported command, control, or parameter value in the data stream received from the controlling environment. The IPDS architecture assigns a unique exception code to each exception condition.
The IPDS architecture defines exception conditions and actions that might be detected in IOCA Image Segments carried in the IPDS data stream. They are compatible with IOCA-defined exception conditions and actions.
The IPDS Exception Identifier consists of the two-byte EC identifier defined by IOCA, prefixed by an IPDS exception class value of X'05'. The exception class value is used to distinguish between the two-byte EC identifiers assigned by IOCA, and other two-byte EC identifiers assigned to presentation text (PTOCA), graphics (GOCA), and bar code (BCOCA) objects.
Unsupported IOCA Function in an IPDS Environment Not all IOCA printers support the full range of IOCA function; these printers will return an appropriate NACK if unsupported IOCA self-defining fields or values are included in an image. For example, if an IOCA FS11, FS14, FS40, FS42, FS45, or FS48 image is sent to an IPDS printer that only supports IOCA FS10, the printer will encounter a data stream error and will return one or more exception conditions such as EC-0001 (invalid or unsupported self-defining field code) or EC-9510 (unsupported compression algorithm).
IPDS Environment


Additional Related Commands The following commands are used for query and resource management functions. Only an overview of these commands is presented here. They are described in detail in the Intelligent Printer Data Stream Reference.
Sense Type and Model (STM)
Requests information from the printer that identifies the type and model of the device and the supported command sets. The information requested is returned in the Special Data Area of the Acknowledge Reply. The command sets and the data levels supported are also returned as part of the acknowledgement data.
Execute Order Homestate Obtain Printer Characteristics (XOH OPC)
Requests information from the printer that identifies various characteristics of the device. The characteristics include information about the printable area currently available, symbol-set support, image and coded-font resolution, and color support.
Special Notes This section describes special considerations for the IPDS environment.
Image Segment in IO-Image Command Set For untiled image contents, the image size is specified in the Image Size parameter that is a mandatory parameter within an untiled Image Content. An exception condition occurs if the parameter either is not found, appears more than once, appears before the Begin Image Content, or appears after the first Image Data self- defining field. In this situation, the IOCA standard exception action and IPDS Alternate Exception Action (AEA)
is to process the rest of the Image Segment.
Since the Image Size parameter is mandatory in each untiled Image Content, its contents (except for values in Unit Base, Horizontal, and Vertical Resolutions) must be checked for validation. Exceptions occur under the following conditions:
• The Image Size parameter specifies an unknown horizontal image size (HSIZE=0), and an image [IOCA-E-010]
compression algorithm other than IBM MMR-Modified Modified Read, JPEG, or JBIG2 is selected in the Image Encoding parameter. The IOCA exception action and the IPDS AEA is to skip to the end of the Image Segment.
• The size detected from the image data is different from that specified in the Image Size parameter. The IOCA [IOCA-E-011]
exception action and the IPDS AEA is to use the size of the image detected from the image data.
When the image size extends beyond the XSIZE or YSIZE of the Image Presentation Space, an exception condition occurs. The IOCA exception action and the IPDS AEA is to write only portions of the image that are within the Image Presentation Space, and discard all portions that extend outside it. The portions that are not written onto are filled with zeros.
Each image point in IOCA Image Content is mapped to one image point in the Image Presentation Space. The relationship between the resolution and size parameters of the IDD and the Image Size parameter are further described in “Image Data Descriptor (IDD)”.
Interpretation of IDE Value Bilevel images are represented by an IDE size of one. Each IDE can represent two values, B'1' or B'0'. In the IPDS architecture, an IDE value of B'1' represents a significant bit that is an image point representing a toned pel in the printer, while B'0' represents an insignificant bit that is an image point representing an untoned pel in the printer.
IPDS Environment


Image Presentation Space Mapping The image to be printed is represented as an array of image points in the Image Presentation Space after execution of the Image Segment. The size of the Image Presentation Space and the resolution of the image points within it are defined in the IPDS WIC2 IDD self-defining field.
The size of the Image object area is defined in the IPDS WIC2 IOC self-defining field.
Printing the image data requires the printer to map the logical image existing in the Image Presentation Space to a physical image in the image object area on the page. The mapping options specified in the IPDS WIC2 IOC self-defining field define how the image will be located with respect to the object area, and whether scaling is needed.
Resolution correction occurs in the Scale to Fit, Scale to Fill, Center and Trim, Position and Trim, and Replicate and Trim mapping options whenever the resolution of the image points in the IPS, in one or both dimensions, is different from the pel resolution of the printer.
IPDS Environment




