# Appendix C. Image Compression and Recording Algorithms

The IO-Image command set uses the following algorithms for image compression: [IPDS-C-001]

*   Modified ITU-TSS Modified READ algorithm (IBM MMR) [IPDS-C-002]
*   Run-Length 4 compression algorithm (RL4) [IPDS-C-003]
*   ABIC (Bilevel Q-Coder) compression algorithm (ABIC) [IPDS-C-004]
*   Concatenated ABIC [IPDS-C-005]
*   ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one-dimensional) compression algorithm [IPDS-C-006]
*   ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two-dimensional) compression algorithm [IPDS-C-007]
*   ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR) compression algorithm [IPDS-C-008]
*   ISO/ITU-TSS JPEG algorithms [IPDS-C-009]
*   JBIG2 compression algorithm [IPDS-C-010]
*   Solid Fill Rectangle [IPDS-C-011]
*   TIFF LZW [IPDS-C-012]
*   TIFF LZW with Differencing Predictor [IPDS-C-013]

The IO-Image command set uses the following algorithms for image recording: [IPDS-C-014]

*   RIDIC recording algorithm [IPDS-C-015]
*   Unpadded RIDIC recording algorithm. [IPDS-C-016]

Refer to the compression and recording algorithm appendix in the *Image Object Content Architecture Reference* for further details about these algorithms. [IPDS-C-017]

## Modified ITU-TSS Modified READ Algorithm (IBM MMR)

The Modified ITU-TSS Modified READ 2 Algorithm (IBM MMR) allows image data to be compressed optionally in either one-dimensional mode or two-dimensional mode. In one-dimensional mode, color transitions in the image are coded as run length features. In two-dimensional mode, the position of each changing image data element on the current or coding line is coded with respect to the position of a corresponding reference image data element on either the coding line or the reference line that immediately precedes the coding line. One of three coding modes (pass mode, vertical mode, or horizontal mode) is chosen according to the coding procedure that identifies the coding mode to be used to code each changing element along the coding line. When one of the three coding modes is identified by the coding procedure, an appropriate code is selected from the code table. [IPDS-C-018]

**Note:** READ stands for Relative Element Address Designate. [IPDS-C-019]

## Run-Length 4 Compression Algorithm (RL4)

The Run-Length 4 (RL4) algorithm is a binary, one-dimensional, run-length coding method of compression. It is based on code words using four bits. The code words used are common to both white runs and black runs. Code words are listed in the following table: [IPDS-C-020]

### Table 73. Run-Length 4 Compression

| Run Length | Code Word | Code Length |
| :--- | :--- | :--- |
| 0 | 1111 1110 | 8 bits [IPDS-C-021]|
| 1&ndash;8 | 0xxx | 4 bits [IPDS-C-022]|
| 9&ndash;72 | 10xx xxxx | 8 bits [IPDS-C-023]|
| 73&ndash;584 | 110x xxxx xxxx | 12 bits [IPDS-C-024]|
| 585&ndash;4680 | 1110 xxxx xxxx xxxx | 16 bits [IPDS-C-025]|
| 4681&ndash;32,767 | 1111 0xxx xxxx xxxx xxxx | 20 bits [IPDS-C-026]|
| EOL | 1111 1111 (1111) | 8 or 12 bits [IPDS-C-027]|

Two EOL (End Of Line) codes are provided to make an encoded string of each scan line start at a byte boundary. Either of these codes is used, depending on whether the last run-length code of the previous scan line ends at a byte boundary. Each scan line is represented in the following format: [IPDS-C-028]

**Line Number** | **Length (in bytes)** | **W-runLength** | **B-run** | **W-run** | ... | **EOL** [IPDS-C-029]

Both line number and length are represented as two-byte integers, making it possible to skip lines efficiently or to access a specific line directly for display and editing purposes. Providing line numbers also allows completely white lines to be skipped when recording the compressed data. Regarding the run encoding, the first run of each line must be white; if a line begins with a black image data element, white run of length zero must be put in the encoded string. If a line ends with a sequence of white image data elements (that is often the case), the last white run need not be encoded, because it can be calculated from the horizontal size of the image content and the total length of the preceding runs. [IPDS-C-030]

## ABIC (Bilevel Q-Coder) Compression Algorithm (ABIC)

The ABIC algorithm provides an invertible mapping between any data file and a more compact representation of the same information. [IPDS-C-031]

## Concatenated ABIC Compression Algorithm

The image data is first rearranged in IDE bit order so that the first bit of each IDE is sequentially retrieved followed by the second bit of each IDE and so on until all of the IDE bits are retrieved. Then the data is compressed using the ABIC compression algorithm. [IPDS-C-032]

## ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, One-Dimensional)

The ITU-TSS T.4 Facsimile Coding Scheme (G3 MH, one-dimensional) compression algorithm, also called the G3 Modified Huffman compression algorithm (G3 MH) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed one-dimensionally. [IPDS-C-033]

## ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, Two-Dimensional)

The ITU-TSS T.4 Facsimile Coding Scheme (G3 MR, two-dimensional) compression algorithm, also called the G3 Modified Read compression algorithm (G3 MR) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed two-dimensionally. [IPDS-C-034]

## ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR) Compression Algorithm

The ITU-TSS T.6 Facsimile Coding Scheme (G4 MMR) compression algorithm, also known as the G4 Modified MR compression algorithm (G4 MMR) is a method of compression standardized by the International Telecommunications Union-Telecommunications Standardization Sector (ITU-TSS), previously known as CCITT, that enables image data to be compressed two-dimensionally. [IPDS-C-035]

## ISO/ITU-TSS JPEG Compression Algorithms

The JPEG (Joint Photographic Experts Group) technical specification describes a series of algorithms that can be applied to arbitrary source image resolutions, many color models, multiple image components, various sampling formats, and continuous-tone renditions of text. The algorithms are not applicable to bilevel images. Some of the JPEG compression algorithms are lossy. [IPDS-C-036]

## JBIG2 (Joint Bi-level Image Experts Group) Compression Algorithm

The JBIG (Joint Bi-level Image experts Group) technical specification JBIG2, details a set of algorithms specialized for bilevel (1 bit/pixel) source image data at arbitrary spatial resolutions; with separate methods and particular emphasis on textual and halftone bilevel images, in addition to general support for any type of bilevel image (for example, additional image types like line art, pie charts, etc.). [IPDS-C-037]

Most algorithms are lossless, with the exception of a near-lossless capability for scanned textual images that can be extended to lossless. [IPDS-C-038]

The JBIG2 compression is defined by ITU-T Recommendation T.88 and ISO/IEC International Standard 14492:2000. [IPDS-C-039]

**Note:** JBIG2 stores the actual image size in the compressed datastream, thus allowing the IOCA Process Model to determine the number of horizontal and vertical image points from the image data. HSIZE and VSIZE can therefore be zero in the Image Size Parameter. [IPDS-C-040]

For more details about the JBIG2 algorithm, refer to International Telecommunication Union, Recommendation T.88, *Information technology-Coded representation of picture and audio information- Lossy/lossless coding of bilevel images*. [IPDS-C-041]

## Solid Fill Rectangle

The Solid Fill Rectangle compression algorithm is applicable only to bilevel tiles within a tiled image. When specified for a bilevel tile, this compression algorithm indicates that the tile contains no image data (Image Data or Band Image Data) and that the tile will be colored using a solid color. The color is either specified in a Tile Set Color Parameter, or (if no Tile Set Color Parameter) specified in a Set Bilevel Image Color IOCA self-defining field (or a Set Extended Bilevel Image Color IOCA self-defining field) in the Image Data Descriptor, or (if neither is specified) defaults to the device default color. [IPDS-C-042]

## TIFF LZW Compression Algorithm

The LZW (Lempel-Ziv and Welch) algorithm uses a translation table, called the string table, that maps strings of input characters into codes. The TIFF implementation uses variable-length codes, with a maximum code length of twelve bits. The algorithm works best if the input uncompressed data is organized into strips of about 8K bytes, with each strip being compressed independently. The string table is different for every strip. [IPDS-C-043]

## TIFF LZW with Differencing Predictor Compression Algorithm

The TIFF LZW with Differencing Predictor compression algorithm is an extension of the TIFF LZW compression algorithm, compressing values that are the differences between pixels rather than the pixel values themselves. All information in the "TIFF LZW Compression Algorithm" section just above is applicable to this compression algorithm as well. [IPDS-C-044]

## RIDIC Recording Algorithm

The Recorded Image Data Inline Coding recording algorithm (RIDIC) formats a single image in the binary element sequence of a unidirectional raster scan with no interlaced fields and with parallel raster lines, from left to right and from top to bottom. Refer to Figure 121 for a diagram of a RIDIC raster scan. [IPDS-C-045]

### Figure 121. RIDIC Raster Scan

(Diagram description: x-dimension, y-dimension, scan lines 1, 2, 3 ... n-2, n-1, n) [IPDS-C-046]

Each binary element representing an image data element after decompression, without grayscale, is 0 for an image data element without intensity, and 1 for an image data element with intensity. More than one binary element can represent an image data element after decompression, corresponding to a grayscale or color algorithm. Each raster scan line is an integral multiple of 8 bits. If an image occupies an area whose width is other than an integral multiple of 8 bits, the scan line is padded with zeros. [IPDS-C-047]

## Unpadded RIDIC Recording Algorithm

The Unpadded RIDIC recording algorithm is identical to the RIDIC recording algorithm except that raster scan lines can be any length; no padding is necessary. [IPDS-C-048]

**Note:** It is an error to mark an IO Image with an incorrect recording algorithm; however, occasionally an image will be built without padding, but incorrectly marked as RIDIC (or padded but incorrectly marked as unpadded RIDIC). Some IPDS implementations can detect this error and tolerate the image by internally assuming that it was mismarked. [IPDS-C-049]
