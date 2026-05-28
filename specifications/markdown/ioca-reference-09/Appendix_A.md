# Appendix A. Compression and Recording Algorithms
This appendix describes in more detail the image compression and recording algorithms currently supported by the IOCA Image Encoding parameter.
This chapter consists of the image compression and recording algorithms that are presently defined in “Image Encoding”. This appendix is not meant to be a complete description or specification of each algorithm, but is meant to be a short and concise outline of the characteristics of each image compression algorithm. [IOCA-A-001]
## Compression Algorithms
The following compression algorithms are described in this document. The number to the left of each algorithm is the value that the compression algorithm represents for the COMPRID parameter of the Image Encoding parameter.
Value Algorithm X'01' IBM MMR-Modified Modified Read X'03' No compression X'06' RL4 (Run Length 4)
X'08' ABIC (Bilevel Q-Coder)
X'09' TIFF algorithm 2 X'0A' Concatenated ABIC X'0B' Color compression used by OS/2 Image Support X'0C' TIFF PackBits X'0D' TIFF LZW X'0E' TIFF LZW with Differencing Predictor X'20' Solid Fill Rectangle X'80' G3 MH-Modified Huffman (ITU-TSS T .4 Group 3 one-dimensional coding standard for
facsimile)
X'81' G3 MH-Modified READ (ITU-TSS T .4 Group 3 two-dimensional coding option for facsimile)
X'82' G4 MMR-Modified Modified READ (ITU-TSS T .6 Group 4 two-dimensional coding standard for facsimile)
X'83' JPEG algorithms (see the External Algorithm Specification parameter for details)
X'84' JBIG2 X'FE' User-defined algorithms (see the External Algorithm Specification parameter for details)
Other values All other values are reserved All of these compression algorithms are lossless—they result in no loss of data—except for some JPEG algorithms, which are lossy. [IOCA-A-002]


Modified ITU-TSS Modified READ Algorithm (IBM MMR-Modified Modified Read)
This compression algorithm was developed by IBM by modifying the ITU-TSS Modified READ (Relative Element Algorithm Designate) algorithm. It allows both one- and two-dimensional correlations among changing color points in image data:
• In one-dimensional (1D) mode, color transitions in the image are coded by a run-length that denotes how [IOCA-A-003]
long the color continues in the horizontal direction.
• In two-dimensional (2D) mode, the image is coded by how far each IDE is positioned from different color [IOCA-A-004]
IDEs on the same line or the previous line.
The IBM MMR-Modified Modified Read algorithm differs from the ITU-TSS Modified READ algorithm in the following aspects:
• Infinite K value (only the first scan line is in 1D mode) [IOCA-A-005]
• No EOLs, except when switching from 1D to 2D and as part of the EOP [IOCA-A-006]
• No time-fill bit [IOCA-A-007]
The IBM MMR-Modified Modified Read algorithm also differs from a related algorithm, the ITU-TSS Modified Modified READ algorithm, in that the IBM MMR-Modified Modified Read uses one-dimensional coding for the first image line and two-dimensional coding for the remaining lines, while the ITU-TSS Modified Modified READ algorithm uses two-dimensional coding only.
With the Modified ITU-TSS Modified READ algorithm, only one EOP appears at the end of Image Content.
Notes:
1. IBM MMR-Modified Modified Read allows the IOCA Process Model to determine the number of image points in the horizontal and vertical directions. HSIZE and VSIZE can therefore be zero in the Image Size parameter. [IOCA-A-008]
2. If the HSIZE or VSIZE parameter of the Image Size parameter is nonzero, it may be less than the actual number of horizontal or vertical image points encoded in the image data due to padding bits or padding scan lines. [IOCA-A-009]
For more details about the ITU-TSS Modified READ algorithm, refer to Standardization of Group 3 Facsimile Apparatus for Document Transmission, ITU-TSS Recommendation T .4.
For more details about the ITU-TSS Modified Modified READ algorithm, refer to Facsimile Coding Schemes and Coding Control Functions for Group 4 Facsimile Apparatus, ITU-TSS Recommendation T .6.
For more details about the IBM MMR-Modified Modified Read compression algorithm, refer to “Binary-image- manipulation Algorithms in the Image View Facility” in IBM Journal of Research and Development, Volume 31, Number 1 (January 1987).
No Compression This method sends raw image data, in binary form, without any reduction.
Note: The value No Compression does not allow the IOCA Process Model to determine the number of horizontal image points from the image data. However, VSIZE can be zero in the Image Size parameter. [IOCA-A-010]
## Compression Algorithms


Run Length 4 (RL4) Compression Algorithm The Run Length 4 (RL4) algorithm is a binary, one-dimensional, run-length coding method of compression. It is based on code words using four bits. The code words used are common to both white runs and black runs.
T able 27lists the code words.
Table 27. RL4 Code Words Run Length Code Word Code Length 0 B'1111 1110' 8 bits 1–8 B'0'xxx 4 bits 9–72 B'10'xx xxxx 8 bits 73–584 B'110'x xxxx xxxx 12 bits 585–4680 B'1110' xxxx xxxx xxxx 16 bits 4681–32767 B'1111 0'xxx xxxx xxxx xxxx 20 bits
EOL B'1111 1111 (1111)' 8 or 12 bits Two EOL (End-Of-Line) codes are provided to make an encoded string of each scan line start at a byte boundary. Either of these codes is used, depending on whether or not the last run-length code of the previous scan line ends at a byte boundary. Each scan line is represented in the following format:
Figure 18. Scan Line Format Line Number Length W-run W-run Length (In Bytes)
B-run B-run EOL. . .
Both line number and length are represented as two-byte integers, making it possible to skip lines efficiently or to access a specific line directly for display and editing purposes. Providing line numbers also allows completely white lines to be skipped when recording the compressed data.
Regarding the run encoding, the first run of each line must be white; if a line begins with a black image data element, a white run of length zero must be put in the encoded string. If a line ends with a sequence of white image data elements (which is often the case), the last white run need not be encoded, because it can be calculated from the horizontal size of the Image Content and the total length of the preceding runs.
Note: RL4 does not allow the IOCA Process Model to determine the number of horizontal image points from the image data. However, VSIZE can be zero in the Image Size parameter.
ABIC (Bilevel Q-Coder) Compression Algorithm This algorithm uses an arithmetic coding technique to produce lossless data compression, which is an invertible mapping between any data file and a compact representation of the same information.
Note: ABIC does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data. Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter.
For more details, refer to R. Arps, T . Truong, D. Lu, R. Pasco, and T . Friedman, “A multipurpose VLSI chip for adaptive data compression of bilevel images”, in IBM Journal of Research and Development, Volume 32, No. 6 (November 1988). [IOCA-A-011]
## Compression Algorithms


TIFF algorithm 2 Compression Algorithm T ag Image File Format (TIFF) data compression scheme 2 is a method of compression that enables image data to be compressed one-dimensionally and is based upon the ITU-TSS T .4 G3 facsimile one-dimensional coding scheme (G3 MH-Modified Huffman).
The TIFF data compression scheme 2 differs from the ITU-TSS T .4 G3 facsimile one-dimensional coding scheme (G3 MH-Modified Huffman) in the following respects:
• New rows always begin on the next available byte boundary. [IOCA-A-012]
• No End-of-line (EOL) code words are used. [IOCA-A-013]
• No fill bits are used, except for the ignored bits at the end of the last byte of a row. [IOCA-A-014]
• No Return to control (RTC) is used. [IOCA-A-015]
Note: TIFF 2 does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data. Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter.
For more details about the ITU-TSS Group 3 algorithms, refer to Standardization of Group 3 Facsimile Apparatus for Document Transmission, ITU-TSS Recommendation T .4.
Concatenated ABIC Compression Algorithm This algorithm is a form of compression that utilizes the ABIC compression algorithm.
For image data with an IDE size of n bits, a processor begins the compression process by retrieving the first bit of the first IDE, and continuing until the first bit of every IDE has been retrieved, in the order in which the IDEs were recorded. The processor then retrieves the second bit of the first IDE, and so on until all the second bits have been retrieved. This sequential process is continued until the nth bit of every IDE has been retrieved.
The raster data obtained from this process is compressed using the ABIC algorithm to form a single string of ABIC compressed image data. This compression may occur during the retrieval process, or after all the raster data has been retrieved. No break in the code indicating an End-of-Line, End-of-Page, or a flag may appear in the compressed data. Thus, the length of each line, the size of the image, and the number of bits per IDE cannot be determined from the concatenated ABIC compressed image data.
Note: Concatenated ABIC does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data. Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter.
For more details about the concatenated ABIC algorithm, refer to Arps et al., “A multipurpose VLSI chip for adaptive data compression of bilevel images”.
OS/2 Image Support Compression Algorithm The color compression algorithm supported by the OS/2 Image Support program, part number 49F4608, is based on an earlier revision (R5.0) of the JPEG draft specification. It is similar to the JPEG baseline system described in “JPEG Compression Algorithms”.
The OS/2 Image Support program supports data streams in RGB pixel interleaf format only: that is, the color pixels input to the encoder and the decoder output must be of the form RGBRGB...RGB.
Note: The OS/2 Image Support implementation of the JPEG compression algorithm does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data.
Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter. [IOCA-A-016]
## Compression Algorithms


For more details, refer to William B. Pennebaker and Joan L. Mitchell, “Standardization of Color Image Data Compression”, Part I. “Sequential Coding”, in Proceedings Electronic Imaging '89 East (October 2–5, 1989):
109–112.
TIFF PackBits Compression Algorithm The TIFF PackBits algorithm came from the Apple Macintosh system and is applicable to bilevel images only.
Each line is packed independently of any other lines.
Note: TIFF PackBits does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data. Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter.
For more details about the algorithm, refer to TIFF, Revision 6.0, Final (Aldus Corp.: June 3, 1992).
TIFF LZW Compression Algorithm The LZW (Lempel-Ziv and Welch) algorithm is applicable to bilevel, and continuous tone or palette grayscale and color images. The algorithm works best if the input uncompressed data is organized into strips of about 8K bytes, with each strip being compressed independently.
The algorithm is based on a translation table, or string table, that maps strings of input characters into codes.
The TIFF implementation uses variable-length codes, with a maximum code length of twelve bits. This string table is different for every strip.
Notes:
1. TIFF LZW does not allow the IOCA Process Model to determine the number of horizontal or vertical image points from the image data. Hence both HSIZE and VSIZE cannot be zero in the Image Size parameter. [IOCA-A-017]
2. LZW encoders sometimes terminate the data early. If the LZW decoder does not produce the expected number of bytes, no exception should be raised and the receiver should fill the remaining data with binary zeros. [IOCA-A-018]
For more details about the algorithm, refer to:
• TIFF, Revision 6.0, Final (Aldus Corp.: June 3, 1992). [IOCA-A-019]
• T erry A. Welch, “A T echnique for High Performance Data Compression”, in IEEE Computer, vol. 17, no. 6 [IOCA-A-020]
(June 1984).
TIFF LZW with Differencing Predictor Compression Algorithm The TIFF LZW with Differencing Predictor compression algorithm is an extension of the TIFF LZW compression algorithm, compressing values that are the differences between pixels rather than the pixel values themselves. All information in the TIFF LZW Compression Algorithm section just above is applicable to this compression algorithm as well. The Differencing Predictor extension is described in Section 14 of the TIFF 6.0 Specification.
For continuous tone images, using the Differencing Predictor usually improves compression—often significantly—compared to base TIFF LZW. However, using this extension on other types of images, where it generally does not improve compression ratios compared to TIFF LZW, is not recommended.
In general, the nature of this algorithm lends itself to better compression when compressing a single color component, as is found in banded data. [IOCA-A-021]
## Compression Algorithms


Solid Fill Rectangle Compression Algorithm The Solid Fill Rectangle compression algorithm is applicable to tiled images only. It indicates that the tile contains no image data (Image Data or Band Image Data). Instead, the tile may contain the Tile Set Color parameter and all the image points contained within the tile are painted by the color specified in the Tile Set Color parameter. If the Tile Set Color parameter is missing, the color is specified via the Set Bilevel Image Color parameter. If Set Bilevel Image Color is missing, the device default color is used. The Solid Fill Rectangle compression algorithm is applicable only in bilevel color spaces (IDESZ=1), since Tile Set Color specifies the color space internally and requires that the tile color space specified via the optional IDE Structure and IDE
Size parameters be bilevel (that is, as either YCbCr or YCrCb and with the IDE Size as 1).
Since the tile compressed using the Solid Fill Rectangle algorithm is generated by the receiver based on the tile dimensions, the THSIZE and TVSIZE fields in the Tile Size parameters must both be nonzero.
ITU-TSS T .4 Group 3 Coding Standard (G3 MH-Modified Huffman) for Facsimile The ITU-TSS T .4 Group 3 Coding Standard (G3 MH-Modified Huffman) is a compression method for facsimile, standardized by the ITU-TSS (formerly CCITT). It enables one-dimensional compression.
Note: G3 MH-Modified Huffman does not allow the IOCA Process Model to determine the number of image points in the horizontal direction. However, VSIZE can be zero in the Image Size parameter.
For more details, refer to Standardization of Group 3 Facsimile Apparatus for Document Transmission, ITU- TSS Recommendation T .4.
ITU-TSS T .4 Group 3 Coding Option (G3 MH-Modified READ) for Facsimile The ITU-TSS T .4 Group 3 Coding Option (G3 MH-Modified READ) is a compression method for facsimile, standardized by the ITU-TSS. It enables two-dimensional compression.
Note: G3 MH-Modified READ does not allow the IOCA Process Model to determine the number of image points in the horizontal direction. However, VSIZE can be zero in the Image Size parameter.
For more details, refer to Standardization of Group 3 Facsimile Apparatus for Document Transmission, ITU- TSS Recommendation T .4.
ITU-TSS T .6 Group 4 Coding Standard (G4 MMR-Modified Modified READ)
for Facsimile The ITU-TSS T .6 Group 4 Coding Standard (G4 MMR-Modified Modified READ) is a compression method for facsimile, standardized by the ITU-TSS. It enables two-dimensional compression.
Note: G4 MMR-Modified Modified READ does not allow the IOCA Process Model to determine the number of image points in the horizontal direction. However, VSIZE can be zero in the Image Size parameter.
For more details, refer to Facsimile Coding Schemes and Coding Control Functions for Group 4 Facsimile Apparatus, ITU-TSS Recommendation T .6.
JPEG Compression Algorithms The JPEG (Joint Photographic Experts Group) technical specification details a series of algorithms that can be applied to arbitrary source image resolutions, many color models, multiple image components, various sampling formats, and continuous-tone renditions of text. The algorithms are not applicable to bilevel images. [IOCA-A-022]
## Compression Algorithms


Some of these algorithms are lossy.
Note: JPEG stores the actual image size in its header, thus allowing the IOCA Process Model to determine the number of horizontal and vertical image points from the image data. HSIZE and VSIZE can therefore be zero in the Image Size parameter.
For more details, refer to the following publications:
• ISO/IEC International Standard 10918-1 [IOCA-A-023]
• ITU-TSS Recommendation T .81 [IOCA-A-024]
JBIG2 (Joint Bi-level Image Experts Group) Compression Algorithm JBIG2 embodies a set of techniques for compressing bilevel images. It is generally an asymmetric algorithm in the sense that the compression is more computationally demanding than decompression. The data can be encoded either losslessly, so that the decompressed output is an exact copy of the original, or via lossy algorithms, where the decompressed image is “close” to the original.
JBIG2 is organized as a toolkit of many different algorithms. Different subsets of the standard are used for different images and applications. The standard codifies these subsets as “profiles”, much in the same way IOCA uses function sets. JBIG2 receivers commonly implement one or more profiles, and not the whole standard.
JBIG2 compression is defined by the ITU-T Recommendation T .88, ISO/EC 14492:2000 and ITU-T Recommendation T .89 Amendment 1.
Note: JBIG2 stores the actual image size in the compressed datastream, thus allowing the IOCA Process Model to determine the number of horizontal and vertical image points from the image data. HSIZE and VSIZE can therefore be zero in the Image Size parameter.
For more details, refer to the following publications:
• International T elecommunication Union, Recommendation T .88, “Information T echnology - Coded [IOCA-A-025]
representation of picture and audio information - Lossy/lossless coding of bi-level images”
• International T elecommunication Union, Recommendation T .89 Amendment 1, “Application Profiles for [IOCA-A-026]
Recommendation T .88 - Lossy/lossless coding of bi-level images (JBIG2) for fascimile” User-defined Compression Algorithm Code point X'FE' denotes that the compression algorithm is supplied by the user, and that the algorithmic description can be found in the External Algorithm Specification parameter . Users should contact the IOCA architecture group to obtain a unique identifier for their exclusive use. [IOCA-A-027]
## Compression Algorithms


Compression Algorithms and Explicit Image Dimensions IOCA generators should set HSIZE and VSIZE to the actual width and height of the image, regardless of the compression algorithm used. Leaving either HSIZE or VSIZE as zero might cause some IOCA receivers to abort prematurely. T able 28lists all the image compression methods recognized by IOCA. The HSIZE and VSIZE columns are intended for the use of IOCA receivers. Some compression algorithms, such as the IBM MMR-Modified Modified Read, are able to determine the uncompressed image horizontal/vertical size from the input compressed image data, without referring to the HSIZE/VSIZE of the Image Size parameter, that is, HSIZE/VSIZE can be zero in the Image Size parameter.
2 Table 28. Image Compression Algorithms Supported by IOCA Compression COMPRID HSIZE VSIZE IBM MMR-Modified Modified READ X'01' Can be zero in the Image Size parameter Can be zero in the Image Size [IOCA-A-028]
parameter No compression X'03' Must be nonzero in the Image Size parameter Can be zero in the Image Size parameter Run-Length 4 X'06' Must be nonzero in the Image Size parameter Can be zero in the Image Size
parameter ABIC (Bilevel Q-Coder) X'08' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size parameter TIFF algorithm 2 X'09' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size
parameter Concatenated ABIC X'0A' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size parameter OS/2 Image Support 2 X'0B' Must be nonzero in the Image Size parameter
Must be nonzero in the Image Size parameter TIFF PackBits X'0C' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size parameter TIFF LZW X'0D' Must be nonzero in the Image Size parameter
Must be nonzero in the Image Size parameter TIFF LZW with Differencing Predictor X'0E' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size parameter
Solid Fill Rectangle X'20' Must be nonzero in the Image Size parameter Must be nonzero in the Image Size parameter G3 MH-Modified Huffman X'80' Must be nonzero in the Image Size parameter
Can be zero in the Image Size parameter G3 MR Modified READ X'81' Must be nonzero in the Image Size parameter Can be zero in the Image Size parameter G3 MMR Modified READ X'82' Must be nonzero in the Image Size parameter
Can be zero in the Image Size parameter JPEG algorithms X'83' Can be zero in the Image Size parameter Can be zero in the Image Size parameter JBIG2 X'84' Can be zero in the Image Size parameter
Can be zero in the Image Size parameter [IOCA-A-029]
## Compression Algorithms
2. The OS/2 Image Support compression algorithm is based on an earlier version (V5.0) of the JPEG specification. Although JPEG encodes the actual image width and height in the compressed data header the current OS/2 Image Support implementation of the compression algorithm requires both the HSIZE and VSIZE parameters of the Image Size parameter to contain the actual image size. [IOCA-A-030]


Compression Algorithms for Different Image Types Each compression algorithm is generally valid for only some of the possible image data types. In some cases, even though the use of a particular algorithm is valid, the compression performance can be poor. For a selection of compression algorithms commonly used in practice, T able 29defines which compression algorithms can be used for each data type.
Table 29. Commonly-used Compression Algorithms for Each Data Type IDE Size Color Space Algorithms 1 bit/IDE YCrCb (X'02')
YCbCr (X'12')
ABIC (X'08')
G4 MMR (X'82')
4 bits/IDE CMYK (X'04') Concatenated ABIC (X'0A') [IOCA-A-031]
G4 MMR (X'82')
8 bits/IDE YCrCb (X'02') [IOCA-A-032]
YCbCr (X'12')
TIFF LZW (X'0D')
JPEG (X'83')
24 bits/IDE RGB (X'01') [IOCA-A-033]
YCrCb (X'02')
YCbCr (X'12')
TIFF LZW (X'0D')
JPEG (X'83')
32 bits/IDE CMYK (X'04') TIFF LZW (X'0D') [IOCA-A-034]
JPEG (X'83')
n×8 bits/IDE (X'2' ≤ n ≤ X'F') nColor (X'8n') TIFF LZW (X'0D')
TIFF LZW with Differencing Predictor (X'0E')
JPEG (X'83')
Notes:
1. The color space is the FORMAT field in the IDE Structure parameter. [IOCA-A-035]
2. The compression algorithm is the COMPRID field in the Image Encoding parameter. [IOCA-A-036]
3. “No Compression” (X'03') is valid for all image data types. [IOCA-A-037]
4. A mismatch between the image data and compression algorithm causes exception EC-9511 to be raised. [IOCA-A-038]
The choice of the compression algorithm can have a major impact on both the printer performance and the print quality. Poor compression ratios can result in large datasets that cannot be downloaded to the printer quickly enough. The time required for decompression generally increases with the size of the compressed image and can also be a problem. The print quality is affected by using a lossy algorithm, such as JPEG, on unsuitable data. For more information on matching the compression algorithm to the type of image data, see [IOCA-A-039]
