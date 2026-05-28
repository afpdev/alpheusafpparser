# Glossary
This glossary contains terms that apply to MOCA and also a few terms that apply to other related presentation architectures. [MOCA-GLO-001]

If you do not find the term that you are looking for, please refer to the *IBM Dictionary of Computing* (document number ZC20-1699), the *InfoPrint Dictionary of Printing*, or the glossary in the *IPDS Reference* (document number AFPC-0001). [MOCA-GLO-002]

The following definitions are provided as supporting information only, and are not intended to be used as a substitute for the semantics described in the body of this reference. [MOCA-GLO-003]

### A
* **Advanced Function Presentation (AFP)**. An open architecture for the management of presentable information that is developed by the AFP Consortium (AFPC). AFP comprises a number of data stream and data object architectures: [MOCA-GLO-004]
  - Mixed Object Document Content Architecture (MO:DCA); formerly referred to as AFPDS [MOCA-GLO-005]
  - Intelligent Printer Data Stream (IPDS) Architecture [MOCA-GLO-006]
  - AFP Line Data Architecture [MOCA-GLO-007]
  - Bar Code Object Content Architecture (BCOCA) [MOCA-GLO-008]
  - Color Management Object Content Architecture (CMOCA) [MOCA-GLO-009]
  - Font Object Content Architecture (FOCA) [MOCA-GLO-010]
  - Graphics Object Content Architecture for AFP (AFP GOCA) [MOCA-GLO-011]
  - Image Object Content Architecture (IOCA) [MOCA-GLO-012]
  - Metadata Object Content Architecture (MOCA) [MOCA-GLO-013]
  - Presentation Text Object Content Architecture (PTOCA) [MOCA-GLO-014]
* **AFP**. See Advanced Function Presentation. [MOCA-GLO-015]
* **AFP Consortium (AFPC)**. A formal open standards body that develops and maintains AFP architecture. Information about the consortium can be found at http://www.afpconsortium.org. [MOCA-GLO-016]
* **AFP data stream**. A presentation data stream that is processed in AFP environments. The MO:DCA architecture defines the strategic AFP interchange data stream. The IPDS architecture defines the strategic AFP printer data stream. [MOCA-GLO-017]
* **AFPDS**. A term formerly used to identify the composedpage MO:DCA-based data stream interchanged in AFP environments. See also MO:DCA and AFP data stream. [MOCA-GLO-018]
* **AFP environment**. Wherever the AFP architecture is used in any way; by an AFP vendor, an AFP customer, or any combination thereof. [MOCA-GLO-019]
* **AFP Tagging**. (1) Associating extra information, contained in a metadata object, with a given piece of AFP data. Among other uses, such information could enable users with vision impairments or other restrictions to make full use of the content provided by an AFP system. (2) In MOCA, a known format of a metadata object. [MOCA-GLO-020]
* **application**. (1) The use to which an information system is put. (2) A collection of software components used to perform specific types of work on a computer. [MOCA-GLO-021]
* **architected**. Identifies data that is defined and controlled by an architecture. Contrast with unarchitected. [MOCA-GLO-022]

### B
* **bar code**. An array of elements, such as bars, spaces, and two-dimensional modules that together represent data elements or characters in a particular symbology. The elements are arranged in a predetermined pattern following unambiguous rules defined by the symbology. [MOCA-GLO-023]
* **Bar Code Object Content Architecture (BCOCA)**. An architected collection of constructs used to interchange and present bar code data. [MOCA-GLO-024]
* **BCOCA**. See Bar Code Object Content Architecture. [MOCA-GLO-025]
* **big endian**. A format for storage or transmission of binary data in which the most significant bit (or byte) is placed first. Contrast with little endian. [MOCA-GLO-026]

### C
* **CMOCA**. See Color Management Object Content Architecture. [MOCA-GLO-027]
* **Color Management Object Content Architecture (CMOCA)**. An architected collection of constructs used for the interchange and presentation of the color management information required to render a print file, document, group of pages or sheets, page, overlay, or data object with color fidelity. [MOCA-GLO-028]
* **compression algorithm**. An algorithm used to compress data. Compression of data can decrease the volume of data. [MOCA-GLO-029]
* **construct**. An architected set of data such as a structured field or a triplet. [MOCA-GLO-030]
* **controlling environment**. The environment in which an object is embedded, for example, the IPDS and MO:DCA data streams. [MOCA-GLO-031]

### D
* **data stream**. A continuous stream of data that has a defined format. An example of a defined format is a structured field. [MOCA-GLO-032]
* **deprecated**. An architected construct is marked as “deprecated” to indicate that it should no longer be used because it has been superseded by a newer construct. Use or support of a deprecated construct is permitted but no longer recommended. Constructs are deprecated rather than immediately removed to provide backward compatibility. [MOCA-GLO-033]
* **device dependent**. Dependent upon one or more device characteristics. [MOCA-GLO-034]
* **device independent**. Not dependent upon device characteristics. [MOCA-GLO-035]
* **document**. (1) A machine-readable collection of one or more objects that represents a composition, a work, or a collection of data. (2) A publication or other written material. [MOCA-GLO-036]
* **document component**. An architected part of a document data stream. Examples of document components are documents, pages, page groups, indexes, resource groups, objects, and process elements. [MOCA-GLO-037]
* **document content architecture**. A family of architectures that define the syntax and semantics of the document component. See also document component and structured field. [MOCA-GLO-038]
* **document element**. A self-identifying, variable-length, bounded record, that can have a content portion that provides control information, data, or both. An application or device does not have to understand control information or data to parse a data stream when all the records in the data stream are document elements. See also structured field. [MOCA-GLO-039]

### E
* **Efficient XML Interchange (EXI)**. A format that allows XML documents to be encoded as binary data, rather than as plain text. [MOCA-GLO-040]
* **exception**. An invalid or unsupported data-stream construct. [MOCA-GLO-041]
* **exception action**. Action taken when an exception is detected. [MOCA-GLO-042]
* **exception condition**. The condition that exists when a product finds an invalid or unsupported construct. [MOCA-GLO-043]
* **exchange**. The predictable interpretation of shared information by a family of system processes in an environment where the characteristics of each process must be known to all other processes. Contrast with interchange. [MOCA-GLO-044]
* **EXI**. See Efficient XML Interchange. [MOCA-GLO-045]
* **Extensible Markup Language (XML)**. A set of rules for encoding documents in a format that is both humanreadable and machine-readable. [MOCA-GLO-046]
* **Extensible Metadata Platform (XMP)**. An ISO standard, originally created by Adobe Systems Incorporated, for the creation, processing, and interchange of standardized and custom metadata for all kinds of resources. [MOCA-GLO-047]

### F
* **FOCA**. See Font Object Content Architecture. [MOCA-GLO-048]
* **Font Object Content Architecture (FOCA)**. An architected collection of constructs used to describe fonts and to interchange those font descriptions. [MOCA-GLO-049]

### G
* **GOCA**. See Graphics Object Content Architecture. [MOCA-GLO-050]
* **graphics data**. Data containing lines, arcs, markers, and other constructs that describe a picture. [MOCA-GLO-051]
* **Graphics Object Content Architecture (GOCA)**. An architected collection of constructs used to interchange and present graphics data. [MOCA-GLO-052]
* **GZIP**. A widely-used,free software compression algorithm. [MOCA-GLO-053]

### H
* **hexadecimal**. A number system with a base of sixteen. The decimal digits 0 through 9 and characters A through F are used to represent hexadecimal digits. The hexadecimal digits A through F correspond to the decimal numbers 10 through 15, respectively. An example of a hexadecimal number is X'1B', that is equal to the decimal number 27. [MOCA-GLO-054]
* **host**. In the IPDS architecture, a computer that drives a printer. [MOCA-GLO-055]

### I
* **image**. An electronic representation of a picture produced by means of sensing light, sound, electron radiation, or other emanations coming from the picture or reflected by the picture. An image can also be generated directly by software without reference to an existing picture. [MOCA-GLO-056]
* **Image Object Content Architecture (IOCA)**. An architected collection of constructs used to interchange and present images. [MOCA-GLO-057]
* **Intelligent Printer Data Stream (IPDS)**. An architected host-to-printer data stream that contains both data and controls defining how the data is to be presented. [MOCA-GLO-058]
* **interchange**. The predictable interpretation of shared information in an environment where the characteristics of each process need not be known to all other processes. Contrast with exchange. [MOCA-GLO-059]
* **International Organization for Standardization (ISO)**. An organization of national standards bodies from various countries established to promote development of standards to facilitate international exchange of goods and services, and develop cooperation in intellectual, scientific, technological, and economic activity. [MOCA-GLO-060]
* **interoperability**. The capability to communicate, execute programs, or transfer data among various functional units in a way that requires the user to have little or no knowledge of the unique characteristics of those units. [MOCA-GLO-061]
* **IOCA**. See Image Object Content Architecture. [MOCA-GLO-062]
* **IPDS**. See Intelligent Printer Data Stream. [MOCA-GLO-063]
* **ISO**. See International Organization for Standardization. [MOCA-GLO-064]

### L
* **little endian**. A bit or byte ordering where the right-most bits or bytes (those with a higher address) are most significant. Contrast with big endian. [MOCA-GLO-065]

### M
* **M/O**. A table heading for architecture syntax. The entries under this heading indicate whether the construct is mandatory (M) or optional (O). [MOCA-GLO-066]
* **meaning**. A table heading for architecture syntax. The entries under this heading convey the meaning or purpose of a construct. A meaning entry can be a long name, a description, or a brief statement of function. [MOCA-GLO-067]
* **metadata**. Descriptive information that is associated with and augments other data. [MOCA-GLO-068]
* **Metadata command set**. In the IPDS architecture, a collection of commands used to associate MOCA data with objects. [MOCA-GLO-069]
* **metadata object**. In AFP, the resource object that carries metadata. [MOCA-GLO-070]
* **Metadata Object Content Architecture (MOCA)**. A resource object architecture to carry metadata that serves to provide context or additional information about an AFP object or other AFP data. [MOCA-GLO-071]
* **Mixed Object Document Content Architecture (MO:DCA)**. An architected, presentation-system-independent data stream for interchanging documents. [MOCA-GLO-072]
* **MO:DCA**. See Mixed Object Document Content Architecture. [MOCA-GLO-073]
* **MOCA**. See Metadata Object Content Architecture. [MOCA-GLO-074]

### N
* **name**. A table heading for architecture syntax. The entries under this heading are short names that give a general indication of the contents of the construct. [MOCA-GLO-075]

### O
* **object**. (1) A collection of structured fields. The first structured field provides a begin-object function, and the last structured field provides an end-object function. The object can contain one or more other structured fields whose content consists of one or more data elements of a particular data type. An object can be assigned a name, that can be used to reference the object. Examples of objects are metadata, presentation text, font, graphics, and image objects. (2) Something that a user works with to perform a task. [MOCA-GLO-076]
* **offset**. A table heading for architecture syntax. The entries under this heading indicate the numeric displacement into a construct. The offset is measured in bytes and starts with byte zero. Individual bits can be expressed as displacements within bytes. [MOCA-GLO-077]

### P
* **parameter**. (1) A variable that is given a constant value for a specified application. (2) A variable used in conjunction with a command to affect its result. [MOCA-GLO-078]
* **Presentation Text Object Content Architecture (PTOCA)**. An architected collection of constructs used to interchange and present presentation text data. [MOCA-GLO-079]
* **PTOCA**. See Presentation Text Object Content Architecture. [MOCA-GLO-080]

### R
* **range**. A table heading for architecture syntax. The entries under this heading give numeric ranges applicable to a construct. The ranges can be expressed in binary, decimal, or hexadecimal. The range can consist of a single value. [MOCA-GLO-081]
* **reserved**. Having no assigned meaning and put aside for future use. The content of reserved fields is not used by receivers, and should be set by generators to a specified value, if given, or to binary zeros. A reserved field or value can be assigned a meaning by an architecture at any time. [MOCA-GLO-082]
* **retired**. Set aside for a particular purpose, and not available for any other purpose. Retired fields and values are specified for compatibility with existing products and identify one of the following: [MOCA-GLO-083]
  - Fields or values that have been used by a product in a manner not compliant with the architected definition [MOCA-GLO-084]
  - Fields or values that have been removed from an architecture [MOCA-GLO-085]

### S
* **semantics**. The meaning of the parameters of a construct. See also syntax. [MOCA-GLO-086]
* **structured field**. A self-identifying, variable-length, bounded record, that can have a content portion that provides control information, data, or both. See also document element. [MOCA-GLO-087]
* **surrogate pair**. A sequence of two Unicode code points that allow for the encoding of as many as 1 million additional characters without any use of escape codes. [MOCA-GLO-088]
* **surrogates**. A way to refer to one or more surrogate pairs. [MOCA-GLO-089]
* **syntax**. The rules governing the structure of a construct. See also semantics. [MOCA-GLO-090]

### T
* **triplet**. A three-part self-defining variable-length parameter consisting of a length byte, an identifier byte, and parameter-value bytes. [MOCA-GLO-091]

### U
* **UBIN**. A data type for architecture syntax, indicating one or more bytes to be interpreted as an unsigned binary number. [MOCA-GLO-092]
* **unarchitected**. Identifies data that is neither defined nor controlled by an architecture. Contrast with architected. [MOCA-GLO-093]
* **UNDF**. A data type for architecture syntax, indicating one or more bytes that are undefined by the architecture. [MOCA-GLO-094]
* **Unicode**. A character encoding standard for information processing that includes all major scripts of the world. Unicode defines a consistent way of encoding multilingual text. Unicode specifies a numeric value, a name, and other attributes, such as directionality, for each of its characters; for example, the name for $ is “dollar sign” and its numeric value is X'0024'. This Unicode value is called a Unicode code point and is represented as U+nnnn. Unicode provides for three encoding forms (UTF-8, UTF-16, and UTF-32), described as follows: [MOCA-GLO-095]
  - **UTF-8**: A byte-oriented form that is designed for ease of use in traditional ASCII environments. Each UTF-8 code point contains from one to four bytes. All Unicode code points can be encoded in UTF-8 and all 7-bit ASCII characters can be encoded in one byte. [MOCA-GLO-096]
  - **UTF-16**: The default Unicode encoding. A fixed, two-byte Unicode encoding form that can contain surrogates and identifies the byte order of each UTF-16 code point via a Byte Order Mark in the first 2 bytes of the data. Surrogates are pairs of Unicode code points that allow for the encoding of as many as 1 million additional characters without any use of escape codes. [MOCA-GLO-097]
  - **UTF-16BE**: UTF-16 that uses big endian byte order; this is the byte order for all multi-byte data within AFP data streams. The Byte Order Mark is not necessary when the data is externally identified as UTF-16BE (or UTF-16LE). [MOCA-GLO-098]
  - **UTF-16LE**: UTF-16 that uses little endian byte order. [MOCA-GLO-099]
  - **UTF-32**: A fixed, four-byte Unicode encoding form in which each UTF-32 code point is precisely identical to the Unicode code point. [MOCA-GLO-100]
  - **UTF-32BE**: UTF-32 serialized as bytes in mostsignificant-byte-first order (big endian). UTF-32BE is structurally the same as UCS-4. [MOCA-GLO-101]
  - **UTF-32LE**: UTF-32 serialized as bytes in leastsignificant-byte-first order (little endian). [MOCA-GLO-102]

### X
* **XML**. See Extensible Markup Language. [MOCA-GLO-103]
* **XMP**. See Extensible Metadata Platform. [MOCA-GLO-104]
