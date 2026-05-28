# Chapter 3. IOCA Overview
This chapter outlines:
• IOCA Representation of Images [IOCA-3-001]
• Image Points [IOCA-3-002]
• Size and Resolution [IOCA-3-003]
• Compression [IOCA-3-004]
• Image Coordinate System [IOCA-3-005]
• Image Presentation Space [IOCA-3-006]
• Image Tiling [IOCA-3-007]
• Function Sets [IOCA-3-008]
IOCA Representation of Images IOCA provides a way to represent images in a device-independent format, which allows them to be interchangeable across environments. IOCA uses a consistent set of constructs, called self-defining fields, to describe the characteristics of the image data. A self-defining field is a field that contains one or two bytes identifying the content of that field.
An image consists of image points. Each image point is represented by one or more bits of information, called Image Data Elements (IDEs). IDEs are grouped together into Image Data. Image Data is known as non-coded information (NCI) since no codes are embedded in it. This characteristic makes Image Data different from either text or graphic data.
Note: Non-coded information does not contain any IOCA codes that would impact the presentation of image points. The data, however, may be carried in compressed format, such as JPEG, that contains codes that specify how the data is compressed.
Certain properties characterize the image, and must be processed in order to interpret the data properly, such as:
• Size (how large) [IOCA-3-009]
• Resolution (how sharp) [IOCA-3-010]
• Color (whether it is black-and-white, grayscale, or color) [IOCA-3-011]
• Recording and compression algorithms (how Image Data is encoded) [IOCA-3-012]
• Image Data layout [IOCA-3-013]
Image data parameters encapsulate these properties and separate them from the image data. The Image Data and Image Data parameters are collectively referred to as the Image Content.
The Image Contents are independent of the controlling environment in which they exist. In every controlling environment, an image can be represented by its Image Contents alone.
When an image is carried in data streams, all of its image components are contained in Image Segments.
The Image Segment, a set of self-defining fields, is passed to and from controlling environment, which determine how it is handled. That is, the Image Segment can be presented as a displayed or a printed image in an environment, or can be merged with text and graphics objects into a compound document. [IOCA-3-014]


Figure 7. Image Concept and IOCA Representation Concept Image [IOCA-3-015]
## Image Segment
Name
## Image Size
Encoding Algorithms
## External Algorithm Specification
Subsampling Methods
## IDE Size
## IDE Structure
Number of Bands Tiling Attributes Image points Resolution Size Recording Compressor Bilevel, gray scale,
 or color
## Image Data Elements
## Image Content
## Image Data Parameters
## Image Data
## Image Data
Image Characteristics Representation IOCA Representation [IOCA-3-016]


Image Points When digitized for processing, images are expressed by a two-dimensional array of pixels, called image points.
Each image point has information called the image data element (IDE). The IDE has one or more bits that are interpreted in the context of the current color space to determine its property, such as black, white, grayscale, or color.
Consider a color image in the CMYK color space that is represented by four bits per IDE. Figure 8 shows how an intensified image point, say an IDE with a binary value of B'1000', is interpreted.
Figure 8. Image Point and IDE Each image point has an IDE.
Image is a collection of image points.
B ‘1000’ cyan magenta yellow black IDE value B ‘ 1000’ is interpreted for each color plane...
The image foreground and background are defined as follows:
• For bilevel images, the image foreground consists of all those image points whose IDE values are B'1'. The [IOCA-3-017]
rest of the image points along with the unoccupied areas of the Image Presentation Space (IPS) are considered to be the image background.
• For any other images, the entire image is considered to be foreground. The unoccupied areas in the image [IOCA-3-018]
presentation space are the image background.
Image Points


Size and Resolution In addition to color, images are characterized by their size and resolution.
• The size of an image is expressed in terms of the number of image points in the horizontal and vertical [IOCA-3-019]
directions.
• The resolution of an image determines its sharpness. It is expressed in terms of the number of image points [IOCA-3-020]
in the measurement base, in the horizontal and vertical directions. The measurement base, indicated by unit base, can be 10 inches or 10 centimeters.
Figure 9 shows how an image's size and resolution are calculated:
Figure 9. Image Resolution Width:
3 Inches Height: [IOCA-3-021]
5 Inches If the image is divided into 600 image points horizontally and 1500 image points vertically, the image is represented as: [IOCA-3-022]
• Sizes: [IOCA-3-023]
600 Horizontal 1500 Vertical [IOCA-3-024]
• Resolutions: [IOCA-3-025]
200 image points/inch Horizontal 300 image points/inch Vertical Size and Resolution [IOCA-3-026]


Compression Consider an image that has the dimensions of letter-size paper. If it is represented in black and white (bilevel, represented by one bit per IDE) at 600dpi, its image data would be about 3,366,000 bits long. Such large data volumes are expensive to process, store, and transmit.
The size of an image's data can be reduced by one of many compression techniques. In order to reconstruct a compressed image, an application or device must know which compression technique was used to compress the data. IOCA provides two self-defining fields, the Image Encoding parameter and the External Algorithm Specification parameter, to describe the compression algorithm.
In the Image Data, it is not unusual to find lengthy strings of IDEs that all have the same value. Compression algorithms use codes to represent these strings in the Image Data.
Figure 10 shows a compression example that takes advantage of IDE repetitions in the Image Data. The compression algorithm represents a group of similar IDEs by the length of that group.
Figure 10. Image Compression Table to encode 0111 0101 1000 EOL Meaning:
End of line 8 white image points 5 black image points 7 white image points The effectiveness of compression algorithms differs depending on the content of the image. The compression algorithm has to be matched to the data type. For example, bilevel text, business graphics such as a pie chart, and a color photograph will each require a different compression algorithm.
Compression


Image Coordinate System Each Image Content, which consists of image data and image characteristics information, has a coordinate system, called the Image Coordinate System. This is an X-Y Cartesian system that uses only the fourth quadrant and positive values for the Y-axis. In other words, the origin is top left. Units along the X and Y axes correspond directly to image points that are represented by IDEs in the Image Content.
Figure 11. Image Coordinate System Origin Y X
Image points in the horizontal direction are mapped in the X direction of the Image Coordinate System.
Image points in the vertical direction are mapped in the Y direction of the Image Coordinate System.
Image Coordinate System [IOCA-3-027]


Image Presentation Space Before an Image Content can be displayed or printed, it is placed in a conceptual space, called an Image Presentation Space (IPS). The physical characteristics of the IPS are defined and provided by the controlling environment. The IPS is two-dimensional, and has an Image Coordinate System. It acts as a bridge between the IOCA Process Model and the controlling environment.
Figure 12. Image Presentation Space IOCA Segment IOCA Process Model Image Presentation Space
Controlling Environment Image Presentation Space [IOCA-3-028]


Image Tiling For large images, such as engineering drawings, it is often advantageous to partition the image into smaller non-overlapping rectangular pieces called tiles.
Each tile can be thought of as an individual image. The tiles may differ in the color space, encoding, and compression algorithms, but must have resolution that evenly divides the underlying Image Presentation Space resolution. The tiles need not cover the whole Image Presentation Space.
IOCA provides a series of self-defining fields to encode tiling information.
Figure 13 illustrates an image composed of three tiles, each with a different data type.
Figure 13. Tiles of an Image Box 1 Box 2 Box B Box A Box 3 Arrow Title Text line 1 Text line 2
Longer text line 3 Still longer text line 4 Bigger space to some more text Image Tiling [IOCA-3-029]


## Function Sets
For some applications, it is not necessary or feasible to implement all the features in the architecture, or support the entire range of values and parameters in a self-defining field.
Chapter 7, “Compliance” defines several subsets of the architecture (called function sets) that satisfy some particular common needs. It is the responsibility of the application to determine which function set(s) it must provide to generate and receive IOCA Image Objects. [IOCA-3-030]
## Function Sets




