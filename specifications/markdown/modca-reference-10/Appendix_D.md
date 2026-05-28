# Appendix D. MO:DCA Registry
This appendix provides a registry for the following object type identifiers:
• non-OCA object-type identifiers, which can identify either presentation object types or non-presentation [MODCA-D-001]
object types
• media type identifiers [MODCA-D-002]
• resident color profile identifiers. Note that resident color profiles have been replaced by Color Management [MODCA-D-003]
Resources (CMRs)
Object Type Identifiers
Non-OCA object types supported in MO:DCA document interchange must be identified using ASN.1 Object
Identifiers (OIDs) defined in ISO/IEC 8824:1990(E), whose last component identifier is registered in this
appendix. Such identifiers are referred to as encoded object-type OIDs.
Architecture Note: Encoded object-type OIDs are only assigned to objects that have a clear presentation
semantic. Objects can be registered as presentation objects or as non-presentation objects. If an object
can be a presentation object and a non-presentation object, a different encoded object-type OID will be
assigned to each usage.
The following ISO OID sub-tree is used for the registry:
ISO (1)
Identified Organization (3)
IBM (18)
Objects (0)
Print (4)
Document Format (1)
MO:DCA (1)
Object Type (nnnn)
The complete encoded object-type OID is encoded using the Basic Encoding Rules for ASN.1 specified in ISO/
IEC 8825:1990(E). The encoding is in the “definite short form” and has the following syntax:
Byte Description
0 Identifier byte, set to X'06' to indicate an OID encoding [MODCA-D-004]
1 Length of content bytes that follow [MODCA-D-005]
2–n Content bytes that encode the OID component identifiers
Application Note: The definition of an encoded object-type OID in this registry does not guarantee that the
object type identified by the OID is supported in an AFP system. T o see which encoded object-type OIDs
are supported, consult the product documentation. [MODCA-D-006]


Registered Encoded Object-type OIDs
• IOCA FS10: Image Object Content Architecture, subset FS10. This is an IOCA subset for bilevel raster [MODCA-D-007]
image.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground Significant image points
Background Insignificant image points; all portions of object space not covered by image
points
Component ID (5)
Encoded Object-type OID X'06072B120004010105'
• IOCA FS11: Image Object Content Architecture, subset FS11. This is an IOCA subset for grayscale and [MODCA-D-008]
color raster image.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground All image points
Background All portions of object space not covered by image points
Component ID (11)
Encoded Object-type OID X'06072B12000401010B'
• IOCA FS45: Image Object Content Architecture, subset FS45. This is an IOCA subset for grayscale and [MODCA-D-009]
color tiled raster image.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground For color or grayscale tiles, all image points in the tile, except image points
for which a transparency mask specifies B'0'; for bilevel tiles, all significant
image points in the tile, except image points for which a transparency mask
specifies B'0'
Background Insignificant image points (bilevel image), image points for which a
transparency mask specifies B'0', and all portions of the presentation space
not covered by image points or tiles
Component ID (12)
Encoded Object-type OID X'06072B12000401010C'
Registry


• EPS: Encapsulated Postscript. [MODCA-D-010]
Definition Encapsulated Postscript is defined in Appendix H of the Postscript
Language Reference Manual (Second Edition, Adobe Systems
Incorporated).
Object Type Presentation
Presentation Space Size Specified by the mandatory “%%BoundingBox” comment in the EPS
header.
Foreground Complete object presentation space
Background None
Component ID (13)
Encoded Object-type OID X'06072B12000401010D'
• TIFF: T ag Image File Format. This is a raster image format for bilevel, grayscale, and color images. The [MODCA-D-011]
object contains a single, paginated image, defined by TIFF fields.
Definition TIFF is defined in TIFF Revision 6.0 (Aldus Corporation, June 3, 1992).
Object Type Presentation
Presentation Space Size Specified by the ImageLength (T ag 257), ImageWidth (T ag 256),
XResolution (T ag 282), YResolution (T ag 283), and ResolutionUnit (T ag
296) TIFF tags.
Foreground Grayscale & color: all image points; bilevel: all significant image points
Background Grayscale & color: none; bilevel: all insignificant image points
Component ID (14)
Encoded Object-type OID X'06072B12000401010E'
Architecture Note: Transparency mask images and alpha channels are ignored for this object type.
• COM Set-up File: This is a set-up file that contains information used to present MO:DCA data on [MODCA-D-012]
microfiche media with Anacomp devices.
Definition Anacomp COM Set-up files are defined in XFP2000 Reference (XF-07-
9201 [Device Recorder Software], Anacomp Inc., July 15, 1992). [MODCA-D-013]
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (15)
Encoded Object-type OID X'06072B12000401010F'
• Tape Label Set-up File: This is a set-up file that contains information used to present MO:DCA [MODCA-D-014]
documents that exists in tape libraries on microfiche media.
Definition T ape Label Set-up files are defined in MVS/DFP V3.3: Using Magnetic Tape
Labels and File Structure, SC26-4565.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Registry


Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (16)
Encoded Object-type OID X'06072B120004010110'
• Device Independent Bit Map (DIB), Windows Version: This is an image file format used by [MODCA-D-015]
Microsoft Windows Version 3.0 and higher for bilevel and color images.
Definition This image file format is defined in Microsoft Windows Software
Development Kit: Reference Volume 2, Version 3.0 (Microsoft Corporation,
1990).
Object Type Presentation
Presentation Space Size Specified by the biWidth and biHeight parameters in the
BITMAPINFOHEADER structure.
Foreground Grayscale & color: all image points; bilevel: all significant image points
Background Grayscale and color: none; bilevel: all insignificant image points
Component ID (17)
Encoded Object-type OID X'06072B120004010111'
• Device Independent Bit Map (DIB), OS/2 PM Version: This is an image file format used by OS/ [MODCA-D-016]
2 PM Version 1.1 and 1.2 for bilevel and color images. [MODCA-D-017]
Definition This image file format is defined in Microsoft Windows Software
Development Kit: Reference Volume 2, Version 3.0 (Microsoft Corporation,
1990).
Object Type Presentation
Presentation Space Size Specified by the bcWidth and bcHeight parameters in the
BITMAPCOREHEADER structure.
Foreground Grayscale & color: all image points; bilevel: all significant image points
Background Grayscale & color: none; bilevel: all insignificant image points
Component ID (18)
Encoded Object-type OID X'06072B120004010112'
• Paintbrush Picture File Format (PCX): This is an image file format for bilevel and color images. [MODCA-D-018]
Definition This image file format is defined in Technical Documentation for PC
Paintbrush & Frieze Graphics (Z Soft Corporation, 1985).
Object Type Presentation
Presentation Space Size Header bytes 4–11 define the x,y coordinates of the upper-left and lower-
right corners of the image, in pixels. The x-difference + 1 is the width of the
image, the y-difference + 1 is the height of the image.
Foreground Gray-scale and color: all image points; bilevel: all significant image points
Background Gray-scale & color: none; bilevel: all insignificant image points
Component ID (19)
Encoded Object-type OID X'06072B120004010113'
Registry


• Color Mapping Table (CMT): This is a set-up file that provides mappings for color values specified in [MODCA-D-019]
one or more documents.
Definition The Color Mapping T able is defined in the Mixed Object Document Content
Architecture (MO:DCA) Reference.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (20)
Encoded Object-type OID X'06072B120004010114'
• Graphics Interchange Format (GIF): This is an image file format for bilevel and color images. [MODCA-D-020]
Definition This image file format is defined in Graphics Interchange Format, Version
89a Programming Reference (CompuServe Incorporated, July 31, 1990).
Object Type Presentation
Presentation Space Size The width and height of the image, in pixels, is specified in the Image
Descriptor.
Foreground All image points
Background None
Component ID (22)
Encoded Object-type OID X'06072B120004010116'
• AFPC JPEG Subset: This is an image file format for grayscale and color images. [MODCA-D-021]
Architecture Note: This object type was previously called JPEG File Interchange Format (JFIF). The object
has been renamed and redefined to correct inconsistencies between the object definition, which was
based on the JFIF definition, and what has actually been implemented in support of this object type
within the AFP community. This object type registration previously referenced the following document
for the definition of the file format: JPEG File Interchange Format, Version 1.02 (Eric Hamilton, C-
Cube Microsystems, Inc., September 31, 1990). In practice, receivers of this format have supported
functionality not defined in this document, such as the 4-component CMYK color space. The
document that is now referenced for the object definition, Presentation Object Subsets for AFP, has
been generated by the AFP Consortium (AFPC) to reflect the support that receivers have
implemented and should implement for this object type in AFP environments.
Definition This image file format is defined in Presentation Object Subsets for AFP,
available from the AFP Consortium (AFPC) at www.afpcinc.org.
Object Type Presentation
Presentation Space Size The number of rows and number of columns for the image are specified in
the frame header of the Start of Frame (SOF) Marker.
Application Note: Image resolution information specified inside the object
is unreliable and should be specified using the Image Resolution
(X'9A') triplet.
Foreground All image points.
Note: This definition has not changed.
Registry


Background None.
Note: This definition has not changed.
Component ID (23)
Note: This definition has not changed.
Encoded Object-type OID X'06072B120004010117'
Note: This definition has not changed.
•
Anacomp AnaStak Control Record: This is a set-up file that contains accounting and control
information to present MO:DCA documents on microfiche media using Anacomp devices via tape or data
transmission.
Definition The Anacomp AnaStak Control Record is defined in AnaStak, The
Anacomp Report-Stacking System: User's Guide and Reference
(Anast203, Anacomp Inc.).
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (24)
Encoded Object-type OID X'06072B120004010118'
• Portable Document Format (PDF) Single-page Object: This is a presentation object consisting [MODCA-D-022]
of a PDF file that defines a single page containing text, graphics, and image using PDF operators.
Definition The PDF file format is defined in the Portable Document Format Reference
Manual (Adobe Systems Incorporated, 1993).
Object Type Presentation
Presentation Space Size The (x,y) coordinates of the lower-left corner and upper-right corner are
specified by the required MediaBox key in the Page Object dictionary.
Architecture Note: An Object Container Presentation Space Size (X'9C')
triplet may be used to specify a different key in the PDF to use as the
presentation space size. This triplet is specified on the structured
field that includes the PDF , such as an IOB or PPO.
Foreground Complete object presentation space
Background None
Component ID (25)
Encoded Object-type OID X'06072B120004010119'
• Portable Document Format (PDF) Resource Object: This is a resource object that may be [MODCA-D-023]
referenced by a PDF single-page object. Examples of PDF resource objects are fonts, font descriptors, and
images.
Definition The PDF file format is defined in the Portable Document Format Reference
Manual (Adobe Systems Incorporated, 1993).
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Registry


Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (26)
Encoded Object-type OID X'06072B12000401011A'
• PCL Page Object: This is a paginated presentation object that is specified using the PCL language. [MODCA-D-024]
Definition The PCL printer language is defined in the PCL 5 Printer Language
Technical Reference Manual (Hewlett-Packard® Company).
Object Type Presentation
Presentation Space Size Specified by the E c&l#A command.
Foreground Complete object presentation space
Background None
Component ID (34)
Encoded Object-type OID X'06072B120004010122'
• IOCA FS42: Image Object Content Architecture, subset FS42. This is an IOCA subset for bilevel and color [MODCA-D-025]
(1 bit per CMYK component) tiled raster image.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground All image points
Background None
Component ID (45)
Encoded Object-type OID X'06072B12000401012D'
• Resident Color Profile Resource Object: This is a device-resident resource object that defines [MODCA-D-026]
how presentation-system-dependent colors in a data object are related to presentation-system-independent
colors.
Definition Resident Color Profile objects are presentation-system dependent and are
defined by the presentation device.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (46)
Encoded Object-type OID X'06072B12000401012E'
Implementation Note: If a presentation object references a color profile resource object and this resource is
not supported by the presentation device, AFP print servers will issue a warning message and allow
presentation to proceed without the color profile.
Registry


• IOCA Tile Resource: This is an IOCA tile resource. [MODCA-D-027]
Definition The IOCA resource tile is defined in Image Object Content Architecture
Reference.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (47)
Encoded Object-type OID X'06072B12000401012F'
• Encapsulated PostScript (EPS) Object with Transparency: [MODCA-D-028]
Definition Encapsulated Postscript is defined in Appendix H of the Postscript
Language Reference Manual (Second Edition, Adobe Systems
Incorporated).
Object Type Presentation
Presentation Space Size Specified by the mandatory “%%BoundingBox” comment in the EPS
header.
Foreground The painted portions of the object presentation space
Background The unpainted portions of the object presentation space
Component ID (48)
Encoded Object-type OID X'06072B120004010130'
• Portable Document Format (PDF) Single-page Object with Transparency: This is a [MODCA-D-029]
presentation object consisting of a PDF file that defines a single page containing text, graphics, and image
using PDF operators.
Definition The PDF file format is defined in the Portable Document Format Reference
Manual (Adobe Systems Incorporated, 1993).
Object Type Presentation
Presentation Space Size The (x,y) coordinates of the lower-left corner and upper-right corner are
specified by the required MediaBox key in the Page Object dictionary.
Architecture Note: An Object Container Presentation Space Size (X'9C')
triplet may be used to specify a different key in the PDF to use as the
presentation space size. This triplet is specified on the structured
field that includes the PDF , such as an IOB or PPO.
Foreground The painted portions of the object presentation space
Background The unpainted portions of the object presentation space
Component ID (49)
Encoded Object-type OID X'06072B120004010131'
• TrueType/OpenType Font Resource Object: This is a font resource object that may be [MODCA-D-030]
referenced by a data object.
Definition The TrueType Font format is defined in the TrueType Reference Manual
(Apple Computer, Inc., 1999). It is a subset of the OpenType Font Format,
Registry


which is defined in the OpenType Specification (Microsoft Corporation and
Adobe Systems Incorporated, 2000).
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (51)
Encoded Object-type OID X'06072B120004010133'
• TrueType/OpenType Collection Resource Object: This is a collection of TrueType/OpenType [MODCA-D-031]
font resources. It is identified with a TTC file extension in Windows environments.
Definition The TrueType Font collection format is defined in the TrueType Reference
Manual (Apple Computer, Inc., 1999). It is a subset of the OpenType Font
Format, which is defined in the OpenType Specification (Microsoft
Corporation and Adobe Systems Incorporated, 2000).
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component identifier (53)
Encoded Object-type OID X'06072B120004010135'
• Resource Access Table (RAT): This is a set-up file that provides information used to access and [MODCA-D-032]
process resources that are referenced in MO:DCA documents.
Definition The Resource Access T able is defined in the Mixed Object Document
Content Architecture (MO:DCA) Reference.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (54)
Encoded Object-type OID X'06072B120004010136'
• IOCA FS40: Image Object Content Architecture, subset FS40. This is an IOCA subset for bilevel tiled [MODCA-D-033]
raster image.
Definition This IOCA subset is defined in the Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground Significant image points
Background Insignificant image points; all portions of object space not covered by image
points
Registry


Component ID (55)
Encoded Object-type OID X'06072B120004010137'
• UP3i Print Data Object: This is an object that contains data to be processed and presented by a UP3i- [MODCA-D-034]
attached pre/post processing device.
Definition The UP3i Print Data object is defined in the UP3i Specification, available
at www.afpcinc.org. This object comprises the data destined for bytes
3–n of the UP3i-defined Print Data triplet. The structure of the data, its
encoding, and its presentation rules are defined by the Print Data Format
ID, which is registered in the UP3i specification and is specified in the first 4
bytes of the Print Data object.
Architecture Note: Since the UP3i print data is presented by a UP3i device
after (or possibly before) the complete page is rendered by the printer,
the presentation container cannot mix with the remainder of the page
data according to the default MO:DCA mixing rules. A new type of
mixing is defined for this object type, as follows:
– The object area of the presentation container is mixed according to
the default MO:DCA mixing rules.
– The UP3i Print Data object is processed in its own presentation
space by the UP3i device in accordance with the Print Data format.
It is mixed in a manner that is defined by the specific Print Data
format.
Object Type Presentation
Presentation Space Size Defined by the UP3i Print Data format, which is identified by the Print Data
Format ID that is specified in the first 4 bytes of the Print Data object. The
only presentation space mapping option supported for this object type is
UP3i Print Data mapping.
Foreground This object type does not mix in accordance with the default mixing rules.
The foreground mixing is defined by the UP3i Print Data format, which is
identified by the Print Data Format ID that is specified in the first 4 bytes of
the Print Data object. For a definition of the foreground and a description of
the appearance of this object type when rendered, see the UP3i
specification.
Background This object type does not mix in accordance with the default mixing rules.
The background mixing is defined by the UP3i Print Data format, which is
identified by the Print Data Format ID that is specified in the first 4 bytes of
the Print Data object. For a definition of the background and a description of
the appearance of this object type when rendered, see the UP3i
specification.
Component ID (56)
Encoded Object-type OID X'06072B120004010138'
•
Color Management Resource (CMR): This is a resource object that provides information used to
process color or grayscale data.
Definition The Color Management Resource is defined in the Color Management
Resource (CMR) Architecture Reference.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Registry


Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (57)
Encoded Object-type OID X'06072B120004010139'
• JPEG2000 (JP2) File Format: This is an image file format for grayscale and color images. [MODCA-D-035]
Definition The JPEG2000 (JP2) File Format is defined in the ISO/IEC 15444–1. Image
Coding System, 2000, standard.
Object Type Presentation
Presentation Space Size The height and width of the image are specified by the H and W
parameters, respectively, in the Image Header Box.
Foreground All image points that are not identified as transparent
Background All image points that are identified as transparent
Component ID (58)
Encoded Object-type OID X'06072B12000401013A'
• TIFF without Transparency: T ag Image File Format. This is a raster image format for bilevel, [MODCA-D-036]
grayscale, and color images. The object contains a single, paginated image, defined by TIFF fields.
Definition TIFF is defined in TIFF Revision 6.0 (Aldus Corporation, June 3, 1992).
Object Type Presentation
Presentation Space Size Specified by the ImageLength (T ag 257), ImageWidth (T ag 256),
XResolution (T ag 282), YResolution (T ag 283), and ResolutionUnit (T ag
296) TIFF tags.
Foreground All image points
Background None
Component ID (60)
Encoded Object-type OID X'06072B12000401013C'
Architecture Note: Transparency mask images and alpha channels are ignored for this object type.
• TIFF Multiple Image File: This is a TIFF file containing multiple TIFF images in bilevel, grayscale, or [MODCA-D-037]
color format. Each TIFF image is assumed to be a paginated object and is defined by encoded object-type
OID X'06072B12000401010E' (component ID 14). Image-like structures such as thumbnails and image
masks are considered to be a part of the paginated image object but are not themselves considered
paginated objects.
Definition See encoded object-type OID X'06072B12000401010E'
Object Type Presentation
Presentation Space Size See encoded object-type OID X'06072B12000401010E'
Foreground See encoded object-type OID X'06072B12000401010E'
Background See encoded object-type OID X'06072B12000401010E'
Component ID (61)
Encoded Object-type OID X'06072B12000401013D'
Registry


• TIFF Multiple Image - without Transparency - File: This is a TIFF file containing multiple TIFF [MODCA-D-038]
images in bilevel, grayscale, or color format. Each TIFF image is assumed to be a paginated object and is
defined by encoded object-type OID X'06072B12000401013C' (component ID 60). Image-like structures
such as thumbnails and image masks are considered to be a part of the paginated image object but are not
themselves considered paginated objects.
Definition See encoded object-type OID X'06072B12000401013C'
Object Type Presentation
Presentation Space Size See encoded object-type OID X'06072B12000401013C'
Foreground See encoded object-type OID X'06072B12000401013C'
Background See encoded object-type OID X'06072B12000401013C'
Component ID (62)
Encoded Object-type OID X'06072B12000401013E'
•
PDF Multiple Page File: This is a PDF file containing multiple PDF page objects. Each PDF page
object is defined by encoded object-type OID X'06072B120004010119' (component ID 25). A PDF page
object is selected for presentation by its page number; other identifiers such as object numbers in the PDF
file are not used for selection.
Definition See encoded object-type OID X'06072B120004010119'
Object Type Presentation
Presentation Space Size See encoded object-type OID X'06072B120004010119'
Foreground See encoded object-type OID X'06072B120004010119'
Background See encoded object-type OID X'06072B120004010119'
Component ID (63)
Encoded Object-type OID X'06072B12000401013F'
•
PDF Multiple Page - with Transparency - File: This is a PDF file containing multiple PDF page
objects. Each PDF page object is defined by encoded object-type OID X'06072B120004010131' (component
ID 49). A PDF page object is selected for presentation by its page number; other identifiers such as object
numbers in the PDF file are not used for selection.
Definition See encoded object-type OID X'06072B120004010131'
Object Type Presentation
Presentation Space Size See encoded object-type OID X'06072B120004010131'
Foreground See encoded object-type OID X'06072B120004010131'
Background See encoded object-type OID X'06072B120004010131'
Component ID (64)
Encoded Object-type OID X'06072B120004010140'
•
AFPC PNG Subset: This is an image file format for bilevel, grayscale, indexed color, and color images.
Definition The AFPC PNG Subset is defined in Presentation Object Subsets for AFP,
available from the AFP Consortium (AFPC) at www.afpcinc.org.
Object Type Presentation
Registry


Presentation Space Size The width and height of the image in pixels are specified by the Width and
Height parameters in the Image Header (IHDR chunk).
Application Note: Image resolution information specified inside the object
is ignored (pHYs chunk) and can be specified using the Image
Resolution (X'9A') triplet.
Foreground All image points if no alpha channel is specified. If an alpha channel is
specified, all image points that are not identified as transparent (alpha
channel values 1-255).
Background No image points if no alpha channel is specified. If an alpha channel is
specified, all image points that are identified as transparent (alpha channel
value 0).
Component ID (65)
Encoded Object-type OID X'06072B120004010141'
•
AFPC Tag Image File Format (TIFF) Subset: This is a TIFF file containing one or more TIFF
images in bilevel, grayscale, or color format. If there are multiple images, each image is assumed to be a
paginated object. Image-like structures such as thumbnails and image masks are considered to be a part of
the paginated image object but are not themselves considered paginated objects.
Definition TIFF is defined in TIFF Revision 6.0 Specification (Aldus Corporation, June
3, 1992). The AFPC subset is defined in Presentation Object Subsets for
AFP, available from the AFP Consortium (AFPC) at:
www.afpcinc.org.
Object Type Presentation
Presentation Space Size Specified by the ImageLength (T ag 257), ImageWidth (T ag 256),
XResolution (T ag 282), YResolution (T ag 283),
and ResolutionUnit (T ag
296) TIFF tags.
Foreground All image points if no alpha channel is specified. If an alpha channel is
specified, all image points that are not identified as transparent (alpha
channel values 1-255).
Background No image points if no alpha channel is specified. If an alpha channel is
specified, all image points that are identified as transparent (alpha channel
value 0).
Component ID (66)
Encoded Object-type OID X'06072B120004010142'
•
Metadata Object (MO): Metadata for MO:DCA components.
Definition The Metadata Object is defined in the Metadata Object Content
Architecture (MOCA) Reference.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page-level presentation object
Foreground N/A; this is not a page-level presentation object
Background N/A; this is not a page-level presentation object
Component ID (67)
Encoded Object-type OID X'06072B120004010143'
Registry


• AFPC SVG Subset: This is a presentation object consisting of an SVG (Scalable Vector Graphics) file [MODCA-D-039]
that defines a single page containing text, image, and graphics.
Definition The SVG definition is available from the W3C at:
www.w3.org/Graphics/SVG. The AFPC SVG subset is defined in
Presentation Object Subsets for AFP, available from the AFP Consortium
(AFPC) at:
www.afpcinc.org.
Object Type Presentation
Presentation Space Size The size of the SVG presentation space is defined by the SVG viewport
when absolute width and height values are specified within the SVG file.
Architecture Note: An Object Container Presentation Space Size (X'9C')
triplet may be used to override the presentation space size specified
within the SVG file. This triplet is specified on the structured field that
includes the SVG, such as an IOB or PPO. If a presentation space
size is not specified with a X'9C' triplet, and if the SVG viewport does
not specify absolute width and height values, then the architected
default is to use the size of the including page or overlay.
Foreground The stroked and filled portions of the SVG presentation space.
Background All other portions of the SVG presentation space.
Component ID (68)
Encoded Object-type OID X'06072B120004010144'
•
Non-OCA Resource Object: This is a non-presentation object. The object may be referenced as a
secondary resource by a non-OCA object such as SVG and PDF . The format of this resource object is not
known to the AFP system, but is understood by the non-OCA object that references this resource object.
Definition The supported formats for the non-OCA Resource Object, or any
restrictions on those formats, are described in the document that defines
that non-OCA object.
Object Type Non-presentation
Presentation Space Size N/A; this is not a page level presentation object
Foreground N/A; this is not a page level presentation object
Background N/A; this is not a page level presentation object
Component ID (69)
Encoded Object-type OID X'06072B120004010145'
•
IOCA FS48: Image Object Content Architecture, subset FS48. This is an IOCA subset for grayscale and
color tiled raster image.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground For color or grayscale tiles, all image points in the tile, except image points
for which a transparency mask specifies B'0'; for bilevel tiles, all significant
image points in the tile, except image points for which a transparency mask
specifies B'0'
Registry


Background Insignificant image points (bilevel image), image points for which a
transparency mask specifies B'0', and all portions of the presentation space
not covered by image points or tiles
Component ID (70)
Encoded Object-type OID X'06072B120004010146'
• IOCA FS14: Image Object Content Architecture, subset FS14. This is an IOCA subset for bilevel, [MODCA-D-040]
grayscale, and color images that allow use of transparency masks.
Definition This IOCA subset is defined in Image Object Content Architecture
Reference.
Object Type Presentation
Presentation Space Size Specified in Image Data Descriptor (IDD)
Foreground All image points except image points for which a transparency mask
specifies B'0'
Background Image points for which a transparency mask specifies B'0', and all portions
of the object space not covered by image points
Component ID (71)
Encoded Object-type OID X'06072B120004010147'
Registry


Object Type Summary
T able 47lists the object types registered in the MO:DCA architecture along with their component identifier and
their encoded object-type OID.
Table 47. Registered Object Types Sorted by Component ID
Component ID Object Type Encoded Object-type OID
5 IOCA FS10 X'06072B120004010105' [MODCA-D-041]
11 IOCA FS11 X'06072B12000401010B' [MODCA-D-042]
12 IOCA FS45 X'06072B12000401010C' [MODCA-D-043]
13 EPS X'06072B12000401010D' [MODCA-D-044]
14 TIFF X'06072B12000401010E' [MODCA-D-045]
15 COM Set-up X'06072B12000401010F' [MODCA-D-046]
16 T ape Label Set-up X'06072B120004010110' [MODCA-D-047]
17 DIB, Windows Version X'06072B120004010111' [MODCA-D-048]
18 DIB, OS/2 PM Version X'06072B120004010112' [MODCA-D-049]
19 PCX X'06072B120004010113' [MODCA-D-050]
20 Color Mapping T able (CMT) X'06072B120004010114' [MODCA-D-051]
22 GIF X'06072B120004010116' [MODCA-D-052]
23 AFPC JPEG Subset X'06072B120004010117' [MODCA-D-053]
24 AnaStak Control Record X'06072B120004010118' [MODCA-D-054]
25 PDF Single-page Object X'06072B120004010119' [MODCA-D-055]
26 PDF Resource Object X'06072B12000401011A' [MODCA-D-056]
34 PCL Page Object X'06072B120004010122' [MODCA-D-057]
45 IOCA FS42 X'06072B12000401012D' [MODCA-D-058]
46 Resident Color Profile X'06072B12000401012E' [MODCA-D-059]
47 IOCA Tile Resource X'06072B12000401012F' [MODCA-D-060]
48 EPS with Transparency X'06072B120004010130' [MODCA-D-061]
49 PDF with Transparency X'06072B120004010131' [MODCA-D-062]
51 TrueType/OpenType Font X'06072B120004010133' [MODCA-D-063]
53 TrueType/OpenType Font Collection X'06072B120004010135' [MODCA-D-064]
54 Resource Access T able X'06072B120004010136' [MODCA-D-065]
55 IOCA FS40 X'06072B120004010137' [MODCA-D-066]
56 UP3i Print Data X'06072B120004010138' [MODCA-D-067]
57 Color Management Resource (CMR) X'06072B120004010139' [MODCA-D-068]
58 JPEG2000 (JP2) File Format X'06072B12000401013A' [MODCA-D-069]
60 TIFF without Transparency X'06072B12000401013C' [MODCA-D-070]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-D-071]
62 TIFF Multiple Image - without Transparency - [MODCA-D-072]
File
X'06072B12000401013E'
Registry


Table 47 Registered Object Types Sorted by Component ID (cont'd.)
Component ID Object Type Encoded Object-type OID
63 PDF Multiple Page File X'06072B12000401013F' [MODCA-D-073]
64 PDF Multiple Page - with Transparency - File X'06072B120004010140' [MODCA-D-074]
65 AFPC PNG Subset X'06072B120004010141' [MODCA-D-075]
66 AFPC TIFF Subset X'06072B120004010142' [MODCA-D-076]
67 Metadata Object X'06072B120004010143' [MODCA-D-077]
68 AFPC SVG Subset X'06072B120004010144' [MODCA-D-078]
69 Non-OCA Resource Object X'06072B120004010145' [MODCA-D-079]
70 IOCA FS48 X'06072B120004010146' [MODCA-D-080]
71 IOCA FS14 X'06072B120004010147' [MODCA-D-081]
Registry


Non-OCA Object Types Supported by the IOB Structured Field
T able 48lists the object types that can be included for presentation by the Include Object (IOB) structured field
with ObjType = X'92'—Other object data. All object types in this table are not supported by all presentation
systems.
Table 48. Non-OCA Object Types Supported by the IOB
Component ID Object Type Encoded Object-type OID
13 EPS X'06072B12000401010D' [MODCA-D-082]
14 TIFF X'06072B12000401010E' [MODCA-D-083]
17 DIB, Windows Version X'06072B120004010111' [MODCA-D-084]
18 DIB, OS/2 PM Version X'06072B120004010112' [MODCA-D-085]
19 PCX X'06072B120004010113' [MODCA-D-086]
22 GIF X'06072B120004010116' [MODCA-D-087]
23 AFPC JPEG Subset X'06072B120004010117' [MODCA-D-088]
25 PDF Single-page Object X'06072B120004010119' [MODCA-D-089]
34 PCL Page Object X'06072B120004010122' [MODCA-D-090]
48 EPS with Transparency X'06072B120004010130' [MODCA-D-091]
49 PDF with Transparency X'06072B120004010131' [MODCA-D-092]
58 JPEG2000 (JP2) File Format X'06072B12000401013A' [MODCA-D-093]
60 TIFF without Transparency X'06072B12000401013C' [MODCA-D-094]
61 TIFF Multiple Image File X'06072B12000401013D' [MODCA-D-095]
62 TIFF Multiple Image - without Transparency - [MODCA-D-096]
File
X'06072B12000401013E'
63 PDF Multiple Page File X'06072B12000401013F' [MODCA-D-097]
64 PDF Multiple Page - with Transparency - File X'06072B120004010140' [MODCA-D-098]
65 AFPC PNG Subset X'06072B120004010141' [MODCA-D-099]
66 AFPC TIFF Subset X'06072B120004010142' [MODCA-D-100]
68 AFPC SVG Subset X'06072B120004010144' [MODCA-D-101]
Data Objects and Supported Secondary Resources
T able 49lists the secondary resources that are supported by various data objects.
Table 49. Data Objects and Secondary Resources
Data Object Secondary Resource Internal Resource Identifier
IOCA Image IOCA Tile Resource
Color Management Resource
4-byte local ID
None
Encapsulated PostScript (EPS) (with
or without transparency)
Resident Color Profile
Color Management Resource
None
None
Registry


Table 49 Data Objects and Secondary Resources (cont'd.)
Data Object Secondary Resource Internal Resource Identifier
PDF Single-Page Object or Multi-page
File (with or without transparency);
see Note 2
Resident Color Profile
PDF Resource Object
Color Management Resource
Non-OCA Resource Object
TrueType/OpenType Font
None
Identifier with syntax defined by PDF
None
Identifier with syntax defined by PDF
Identifier with syntax defined by PDF
PTOCA T ext; see Note1 TrueType/OpenType Font 1-byte local ID
AFP GOCA; see Note 1 TrueType/OpenType Font
Color Management Resource
1-byte local ID
None
BCOCA T ext; see Note1 TrueType/OpenType Font
Color Management Resource
1-byte local ID
None
TIFF Single Image or Multi-image File
(with or without transparency), and
AFPC TIFF Subset
Color Management Resource None
GIF Color Management Resource None
AFPC JPEG Subset Color Management Resource None
PCL Color Management Resource None
JPEG2000 (JP2) Color Management Resource None
AFPC PNG Subset Color Management Resource None
AFPC SVG Subset; see Note 2 Non-OCA Resource Object
Color Management Resource
TrueType/OpenType Font
Identifier with syntax defined by SVG
None
Identifier with syntax defined by SVG
BCOCA QR Code with Image; see
Notes
1 and 3
Presentation data object resource
Color Management Resource
2-byte local ID
None
Notes:
1. These table entries are not formally primary resource/secondary resource pairs since PTOCA, AFP GOCA, and [MODCA-D-102]
BCOCA objects currently cannot be processed as resource objects. However, the resources for these objects are
processed like other secondary resources.
2. When a non-OCA object such as PDF or SVG references a TTF/OTF as a secondary resource, the FQN type X'DE' [MODCA-D-103]
triplet on the IOB/PPO/MDR must specify the full font name of the font.
3. The potential secondary resource object types used by a BCOCA QR Code with Image bar code are the [MODCA-D-104]
presentation data object resources, which are defined to be IOCA images along with the object types shown in T able
48. When such a secondary resource is a multi-page resource object, such as a PDF Multi-page File or [MODCA-D-105]
TIFF Multi-image File, then only the first paginated object in the file is presented.
Registry


Media Type Identifiers
Media types supported in MO:DCA document interchange may be identified using ASN.1 Object Identifiers
(OIDs) defined in ISO/IEC 8824:1990(E), whose last component identifier is registered in this appendix. Such
identifiers are referred to as media-type OIDs.
The following ISO OID sub-tree is used for the registry:
ISO(1)
Identified Organization (3)
IBM (18)
Objects (0)
Print (4)
Print Attributes (3)
Media Types (1)
Media (nnnn)
Architecture Note: The Document Printing Application (DPA) ISO/IEC DIS 10175:1991 draft standard has
also registered media types with OIDs using a DPA ISO OID sub-tree. Wherever media types in the
MO:DCA registry are also registered in the DPA registry, the last leaf in the MO:DCA OID, also called
the MO:DCA media type component ID, has been chosen to match the last leaf in the DPA OID.
The complete media-type OID is encoded using the Basic Encoding Rules for ASN.1 specified in ISO/IEC
8825:1990(E). The encoding is in the “definite short form” and has the following syntax:
Byte Description
0 Identifier byte, set to X'06' to indicate an OID encoding [MODCA-D-106]
1 Length of content bytes that follow [MODCA-D-107]
2–n Content bytes that encode the OID component identifiers
Registry


Media Type Summary
T able 50 and T able 51 list the media types registered in the MO:DCA architecture
along with their component identifier and their encoded media-type OID.
Table 50. Registered Media Types Sorted by Component ID
Component ID Media Name Media Type Encoded Media-type OID
0 ISO A4 ISO A4 white (210 × 297 mm) X'06072B120004030100' [MODCA-D-108]
1 ISO A4 CO ISO A4 colored X'06072B120004030101' [MODCA-D-109]
2 ISO A4 TR ISO A4 transparent X'06072B120004030102' [MODCA-D-110]
5 ISO A4 THD ISO 1/3 A4 X'06072B120004030105' [MODCA-D-111]
7 ISO A4 TAB ISO A4 tab (225 × 297 mm) X'06072B120004030107' [MODCA-D-112]
10 ISO A3 ISO A3 white (297 × 420 mm) X'06072B12000403010A' [MODCA-D-113]
11 ISO A3 CO ISO A3 colored X'06072B12000403010B' [MODCA-D-114]
20 ISO A5 ISO A5 white (148.5 × 210 mm) X'06072B120004030114' [MODCA-D-115]
21 ISO A5 CO ISO A5 colored X'06072B120004030115' [MODCA-D-116]
30 ISO B4 ISO B4 white (250 × 353 mm) X'06072B12000403011E' [MODCA-D-117]
31 ISO B4 CO ISO B4 colored X'06072B12000403011F' [MODCA-D-118]
40 ISO B5 ISO B5 white (176 × 250 mm) X'06072B120004030128' [MODCA-D-119]
41 ISO B5 CO ISO B5 colored X'06072B120004030129' [MODCA-D-120]
42 JIS B4 JIS B4 (257 × 364 mm) X'06072B12000403012A' [MODCA-D-121]
43 JIS B5 JIS B5 (182 × 257 mm) X'06072B12000403012B' [MODCA-D-122]
50 LETTER North American letter white (8.5 × 11 [MODCA-D-123]
in.)
X'06072B120004030132'
51 LETTER CO North American letter colored X'06072B120004030133' [MODCA-D-124]
52 LETTER TR North American letter transparent X'06072B120004030134' [MODCA-D-125]
60 LEGAL North American legal white (8.5 × 14 [MODCA-D-126]
in.)
X'06072B12000403013C'
61 LEGAL CO North American legal colored X'06072B12000403013D' [MODCA-D-127]
63 LEGAL 13 North American legal 13 (Folio) (8.5 × [MODCA-D-128]
13 in.)
X'06072B12000403013F'
65 EXEC North American executive (7.25 × 10.5 [MODCA-D-129]
in.)
X'06072B120004030141'
67 LEDGER North American ledger (11 × 17 in.) X'06072B120004030143' [MODCA-D-130]
69 STATEMNT North American statement (5.5 × 8.5 [MODCA-D-131]
in.)
X'06072B120004030145'
73 ISO B5 ENV ISO B5 envelope (176 × 250 mm) X'06072B120004030149' [MODCA-D-132]
75 COM 10 ENV Com10 envelope (9.5 × 4.125 in.) X'06072B12000403014B' [MODCA-D-133]
76 MON ENV Monarch envelope (7.5 × 3.875 in.) X'06072B12000403014C' [MODCA-D-134]
77 DL ENV DL envelope (220 × 110 mm) X'06072B12000403014D' [MODCA-D-135]
Registry


Table 50 Registered Media Types Sorted by Component ID (cont'd.)
Component ID Media Name Media Type Encoded Media-type OID
79 C5 ENV C5 envelope (229 × 162 mm) X'06072B12000403014F' [MODCA-D-136]
80 JP PC ENV Japan postcard envelope (200 × 150 [MODCA-D-137]
mm)
X'06072B120004030150'
81 JP PC Japan postcard (Hagaki) (100 × 148 [MODCA-D-138]
mm)
X'06072B120004030151'
83 ISO B4 ENV ISO B4 envelope (250 × 353 mm) X'06072B120004030153' [MODCA-D-139]
93 ISO C4 ENV ISO C4 envelope (229 × 324 mm) X'06072B12000403015D' [MODCA-D-140]
103 ISO C5 ENV ISO C5 envelope (162 × 229 mm) X'06072B120004030167' [MODCA-D-141]
113 ISO LNG ENV ISO long envelope X'06072B120004030171' [MODCA-D-142]
123 10×13 ENV North American 10×13 envelope X'06072B12000403017B' [MODCA-D-143]
133 9×12 ENV North American 9×12 envelope X'06082B12000403018105' [MODCA-D-144]
143 BSNS ENV North American business envelope (9.5 [MODCA-D-145]
x 4.125 in)
X'06082B1200040301810F'
145 LETTER TAB Letter tab (9 × 11 in.) X'06082B12000403018111' [MODCA-D-146]
146 LEGAL TAB Legal tab (9 × 14 in.) X'06082B12000403018112' [MODCA-D-147]
147 9×12 MAN Manual (9 × 12 in.) X'06082B12000403018113' [MODCA-D-148]
148 8×10.5 MED Media (8 × 10.5 in.) X'06082B12000403018114' [MODCA-D-149]
149 9×14 MED Media (9 × 14 in.) X'06082B12000403018115' [MODCA-D-150]
150 INDEX CD Index Card X'06082B12000403018116' [MODCA-D-151]
151 US PC US Postcard X'06082B12000403018117' [MODCA-D-152]
152 ISO A6 PC ISO A6 Postcard (105 × 148 mm) X'06082B12000403018118' [MODCA-D-153]
153 RA3 Oversize A3 (16.923 × 12.007 in.) X'06082B12000403018119' [MODCA-D-154]
154 14×17 MED Media (14 × 17 in.) X'06082B1200040301811A' [MODCA-D-155]
155 12×18 MED Media (12 × 18 in.) X'06082B1200040301811B' [MODCA-D-156]
156 14×18 MED Media (14 × 18 in.) X'06082B1200040301811C' [MODCA-D-157]
157 8.5×10 MED Media (8.5 × 10 in.) X'06082B1200040301811D' [MODCA-D-158]
160 8×10 MED Media (8 × 10 in.) X'06082B12000403018120' [MODCA-D-159]
162 RA4 Oversize A4 (8.465 × 12.007 in.) X'06082B12000403018122' [MODCA-D-160]
163 8×13 MED Media (8 × 13 in) X'06082B12000403018123' [MODCA-D-161]
164 8.25×13 MED Media (8.25 × 13 in) X'06082B12000403018124' [MODCA-D-162]
165 8.25×14 MED Media (8.25 × 14 in) X'06082B12000403018125' [MODCA-D-163]
166 8.5×12.4 MED Media (8.5 × 12.4 in) X'06082B12000403018126' [MODCA-D-164]
167 10×14 MED Media (10 × 14 in) X'06082B12000403018127' [MODCA-D-165]
168 10×15 MED Media (10 × 15 in) X'06082B12000403018128' [MODCA-D-166]
169 11×14 MED Media (11 × 14 in) X'06082B12000403018129' [MODCA-D-167]
170 11×15 MED Media (11 × 15 in) X'06082B1200040301812A' [MODCA-D-168]
Registry


Table 50 Registered Media Types Sorted by Component ID (cont'd.)
Component ID Media Name Media Type Encoded Media-type OID
171 ISO B6 ISO B6 (128 × 182 mm) X'06082B1200040301812B' [MODCA-D-169]
172 REP PD PC Reply-paid PC (148 × 200 mm) X'06082B1200040301812C' [MODCA-D-170]
173 170×210 MED Media (170 × 210 mm) X'06082B1200040301812D' [MODCA-D-171]
174 182×210 MED Media (182 × 210 mm) X'06082B1200040301812E' [MODCA-D-172]
175 210×340 MED Media (210 × 340 mm) X'06082B1200040301812F' [MODCA-D-173]
176 8KAI 8KAI Media (267 × 390 mm) X'06082B12000403018130' [MODCA-D-174]
177 16KAI 16KAI Media (195 × 267 mm) X'06082B12000403018131' [MODCA-D-175]
Table 51. Registered Media Types Sorted by Media Names
Media Name Media Type Component ID Encoded Media-type OID
BSNS ENV North American business envelope (9.5
x 4.125 in)
143 X'06082B1200040301810F' [MODCA-D-176]
COM 10 ENV Com10 envelope (9.5 × 4.125 in.) 75 X'06072B12000403014B'
C5 ENV C5 envelope (229 × 162 mm) 79 X'06072B12000403014F'
DL ENV DL envelope (220 × 110 mm) 77 X'06072B12000403014D'
EXEC North American executive (7.25× 10.5
in.)
65 X'06072B120004030141' [MODCA-D-177]
INDEX CD Index Card 150 X'06082B12000403018116'
ISO A3 ISO A3 white (297 × 420 mm) 10 X'06072B12000403010A'
ISO A3 CO ISO A3 colored 11 X'06072B12000403010B'
ISO A4 ISO A4 white (210 × 297 mm) 0 X'06072B120004030100'
ISO A4 CO ISO A4 colored 1 X'06072B120004030101'
ISO A4 TAB ISO A4 tab (225 × 297 mm) 7 X'06072B120004030107'
ISO A4 THD ISO 1/3 A4 5 X'06072B120004030105'
ISO A4 TR ISO A4 Transparent 2 X'06072B120004030102'
ISO A5 ISO A5 white (148.5 × 210 mm) 20 X'06072B120004030114'
ISO A5 CO ISO A5 colored 21 X'06072B120004030115'
ISO A6 PC ISO A6 Postcard (105 × 148 mm) 152 X'06082B12000403018118'
ISO B4 ISO B4 white (250 × 353 mm) 30 X'06072B12000403011E'
ISO B4 CO ISO B4 colored 31 X'06072B12000403011F'
ISO B5 ISO B5 white (176 × 250 mm) 40 X'06072B120004030128'
ISO B5 CO ISO B5 colored 41 X'06072B120004030129'
ISO B4 ENV ISO B4 envelope (250 × 353 mm) 83 X'06072B120004030153'
ISO B5 ENV ISO B5 envelope (176 × 250 mm) 73 X'06072B120004030149'
ISO B6 ISO B6 (128 × 182 mm) 171 X'06082B1200040301812B'
ISO C4 ENV ISO C4 envelope (229 × 324 mm) 93 X'06072B12000403015D'
Registry


Table 51 Registered Media Types Sorted by Media Names (cont'd.)
Media Name Media Type Component ID Encoded Media-type OID
ISO C5 ENV ISO C5 envelope (162 × 229 mm) 103 X'06072B120004030167'
ISO LNG ENV ISO long envelope 113 X'06072B120004030171'
JIS B4 JIS B4 (257 × 364 mm) 42 X'06072B12000403012A'
JIS B5 JIS B5 (182 × 257 mm) 43 X'06072B12000403012B'
JP PC Japan postcard (Hagaki) (100 × 148
mm)
81 X'06072B120004030151' [MODCA-D-178]
JP PC ENV Japan postcard envelope (200 × 150
mm)
80 X'06072B120004030150' [MODCA-D-179]
LEDGER North American ledger (11 × 17 in.) 67 X'06072B120004030143'
LEGAL North American legal white (8.5 × 14
in.)
60 X'06072B12000403013C' [MODCA-D-180]
LEGAL CO North American legal colored 61 X'06072B12000403013D'
LEGAL TAB Legal tab (9 × 14 in.) 146 X'06082B12000403018112'
LEGAL 13 North American legal 13 (Folio) (8.5 ×
13 in.)
63 X'06072B12000403013F' [MODCA-D-181]
LETTER North American letter white (8.5 × 11
in.)
50 X'06072B120004030132' [MODCA-D-182]
LETTER CO North American letter colored 51 X'06072B120004030133'
LETTER TAB Letter tab (9 × 11 in.) 145 X'06082B12000403018111'
LETTER TR North American letter transparent 52 X'06072B120004030134'
MON ENV Monarch envelope (7.5 × 3.875 in.) 76 X'06072B12000403014C'
RA3 Oversize A3 (16.923 × 12.007 in.) 153 X'06082B12000403018119'
RA4 Oversize A4 (8.465 × 12.007 in.) 162 X'06082B12000403018122'
REP PD PC Reply-paid PC (148 × 200 mm) 172 X'06082B1200040301812C'
STATEMNT North American statement (5.5 × 8.5
in.)
69 X'06072B120004030145' [MODCA-D-183]
US PC US Postcard 151 X'06082B12000403018117'
8×10 MED Media (8 × 10 in.) 160 X'06082B12000403018120'
8×10.5 MED Media (8 × 10.5 in.) 148 X'06082B12000403018114'
8×13 MED Media (8 × 13 in.) 163 X'06082B12000403018123'
8.25×13 MED Media (8.25 × 13 in.) 164 X'06082B12000403018124'
8.25×14 MED Media (8.25 × 14 in.) 165 X'06082B12000403018125'
8.5×10 MED Media (8.5 × 10 in.) 157 X'06082B1200040301811D'
8.5×12.4 MED Media (8.5 × 12.4 in.) 166 X'06082B12000403018126'
9×12 ENV North American 9×12 envelope 133 X'06082B12000403018105'
9×12 MAN Manual (9 × 12 in.) 147 X'06082B12000403018113'
9×14 MED Media (9 × 14 in.) 149 X'06082B12000403018115'
Registry


Table 51 Registered Media Types Sorted by Media Names (cont'd.)
Media Name Media Type Component ID Encoded Media-type OID
10×13 ENV North American 10×13 envelope 123 X'06072B12000403017B'
10×14 MED Media (10 × 14 in) 167 X'06082B12000403018127'
10×15 MED Media (10 × 15 in) 168 X'06082B12000403018128'
11×14 MED Media (11 × 14 in) 169 X'06082B12000403018129'
11×15 MED Media (11 × 15 in) 170 X'06082B1200040301812A'
12×18 MED Media (12 × 18 in.) 155 X'06082B1200040301811B'
14×17 MED Media (14 × 17 in.) 154 X'06082B1200040301811A'
14×18 MED Media (14 × 18 in.) 156 X'06082B1200040301811C'
170×210 MED Media (170 × 210 mm) 173 X'06082B1200040301812D'
182×210 MED Media (182 × 210 mm) 174 X'06082B1200040301812E'
210×340 MED Media (210 × 340 mm) 175 X'06082B1200040301812F'
8KAI 8KAI Media (267 × 390 mm) 176 X'06082B12000403018130'
16KAI 16KAI Media (195 × 267 mm) 177 X'06082B12000403018131'
Architecture Notes:
1. A total of 2 7 = 128 media types can be registered using one byte to encode the component ID, as, for [MODCA-D-184]
example, in the encoding for component IDs 0–123. A total of 2 14 = 16,384 media types can be registered
using two bytes to encode the component ID, as, for example, in the encoding for component IDs 133 and
143. A total of 2 21 = 2,097,152 media types can be registered using three bytes to encode the component [MODCA-D-185]
ID. A total of 2 28 = 268,435,456 media types can be registered using four bytes to encode the component
ID. This registry will support a maximum of 4 bytes for the encoding of the component ID.
2. The range from media type OID X'06082B1200040301E000' (component ID 12,288) to [MODCA-D-186]
X'060A2B1200040301FFFFFF7F' (component ID 268,435,455) is reserved for user-defined media types.
Registry


Resident Color Profile Identifiers
Resident color profiles may be identified using ASN.1 Object Identifiers (OIDs) defined in ISO/IEC 8824:1990
(E), whose last component identifier is registered in this appendix. Such identifiers are referred to as object
OIDs. Note that such resident color profiles have been replaced by Color Management Resources (CMRs).
The following ISO OID sub-tree is used for the registry:
ISO (1)
Identified Organization (3)
IBM (18)
Objects (0)
Print (4)
Print Attributes (3)
Color Profiles (3)
Profiles (nnnn)
The complete OID is encoded using the Basic Encoding Rules for ASN.1 specified in ISO/IEC 8825:1990(E).
The encoding is in the “definite short form” and has the following syntax:
Byte Description
0 Identifier byte, set to X'06' to indicate an OID encoding [MODCA-D-187]
1 Length of content bytes that follow [MODCA-D-188]
2–n Content bytes that encode the OID component identifiers
Registry


Resident Color Profile Summary
T able 52 lists the color profiles registered in the MO:DCA architecture along with their component
identifier and their object OID.
Table 52. Color Profile Registry
Component ID Profile Name Object OID
0 CMYK SWOP X'06072B120004030300' [MODCA-D-189]
1 CMYK Euroscale X'06072B120004030301' [MODCA-D-190]
Architecture Notes:
1. A total of 2 7 = 128 color profiles can be registered using one byte to encode the component ID. A total of [MODCA-D-191]
228 = 268,435,456 color profiles can be registered using four bytes to encode the component ID. This [MODCA-D-192]
registry will support a maximum of 4 bytes for the encoding of the component ID.
2. Many PostScript level 1 files contain color specified in the CMYK color space but tuned to one of a number [MODCA-D-193]
of offset press standards that are geography-based. Two such standards are CMYK SWOP (US), and
CMYK Euroscale (Europe). The standards essentially define the color rendering of hypothetical presses.
For example, a specific color C 1M1Y1K1 defined as SWOP CMYK has a specific colorimetric representation
that is normally defined by a color swatch. The CMYK SWOP and CMYK Euroscale color profiles are
supported in AFP environments for EPS objects and PDF objects.
Registry




