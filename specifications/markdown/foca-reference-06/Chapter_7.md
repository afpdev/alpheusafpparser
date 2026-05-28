# Chapter 7. Compliance Requirements

Compliance to the font architecture is defined in terms of semantic or syntactic support of a subset of a FOCA function set. FOCA function sets are defined as a list of font parameters. The font parameters making up a FOCA Function Set are divided into three subsets, representing those parameters required for font referencing (the minimal subset), character positioning (minimal subset plus font and character positioning parameters), and character presentation or font interchange (the full or complete function set). [FOCA-7-001]

**Semantic support** means that a product supports one of the three subsets of parameters defined for a function set, the parameter definitions, and the parameter range of values. The information may be retained in any product-defined syntax, arrangement, or packaging. In addition, a product may use the formal parameter name, the acronym, a product-defined synonym, or a local identifier. [FOCA-7-002]

**Syntactic support** means that the product supports the interchange format for one of the subsets of the function set, in addition to the semantic support. The number of parameters supported under semantic support is a subset of those supported under syntactic support because additional control parameters are required to define the syntactic relationship (maps, pointers, and indices) between parameters. [FOCA-7-003]

Syntactic support includes the order of parameter occurrence (if order is prescribed), the encoding of parameter identities and values, and any additional parameters required for management of the structure. Syntactic support of an interchange function set of the font architecture requires support of the MO:DCA-defined resource-object wrappers (for example, BRG/ERG). [FOCA-7-004]

It is the responsibility of each product which uses or provides font resource information to designate, in its product specification, the FOCA function set and subset supported. If the product is a system consisting of several products or modules, the functional specification should indicate the function set and subset supported by each of the applicable products or modules. [FOCA-7-005]

The following items define the compliance requirements for each of four different classes of products and architectures: [FOCA-7-006]

*   **Document Editors and Revisable Document Data Streams** [FOCA-7-007]
    This class includes any program (or module of a larger program) that processes text input from a user and generates a revisable document data stream containing references to the user identified or described font resources. Editing products must designate the document data stream architectures that they support. The revisable document data stream architecture, if it supports the font architecture, must designate the function set supported and at least semantic support of the referencing subset of that function set. [FOCA-7-008]

*   **Font Services and Font Service Programming Interface** [FOCA-7-009]
    This class includes any font utility, resource management program or font service programming interface that manages or provides font resource information to another product, including any communication service program that accesses, stores, or transforms font resource information for interchange. It also includes any font generation program or utility that generates, transforms, or modifies font resource information. A product or interface specification in this class, which supports the font architecture, must designate the function set supported and provide syntactic support of the parameters in that function set. [FOCA-7-010]

    **Note:** For any product that performs a transformation on font resources (to or from an internal format used by another product), one side of the transformation must be in the interchange format. [FOCA-7-011]

*   **Document Formatters and Final-Form Document Data Streams** [FOCA-7-012]
    This class includes any program (or module of a larger program) that uses character positioning information from a font resource to assist in the determination of the document format (for example, line breaks, column alignment, page breaks, and character string content), and the final-form document data stream used to represent that format. Any formatting product, which supports the font architecture, must designate the function set supported and at least semantic support of the font and character positioning subset. The final-form document data stream, if it supports the font architecture, must designate the function set supported and at least semantic support of the referencing subset. [FOCA-7-013]

    **Note:** Formatting product support of the font architecture does not necessarily imply revision or final-form data stream support of the font architecture, although this might require complex data transformations and reference tables. [FOCA-7-014]

*   **Document Presentation and Presentation Service Data Streams** [FOCA-7-015]
    This class includes any program (or module of a larger program) or device that uses font resource information to assist in the generation of character images on the presentation surface, and it includes the presentation service or device service data streams required to control the presentation process. Any presentation product or presentation service data stream, which supports the font architecture, must designate the function set supported and at least the semantic support of the parameters for that function set. [FOCA-7-016]

## Using National Language Support

This font architecture fully supports all known IBM requirements for national language support, including multidirectional text and multibyte document encoding. It is, however, the responsibility of implementing products to provide the necessary collections of font information required to support the national language variations required by their product. That is, the font architecture provides for the definition of metric information for support of multiple character rotations, but the implementing product is responsible for providing the character positioning information for those rotations. [FOCA-7-017]
