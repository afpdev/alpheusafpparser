# Chapter 2. Introduction to PTOCA

The Presentation Text object is the data object used in document processing environments for representing text which has been prepared for presentation. Text, as used here, means an ordered string of characters, such as graphic symbols, numbers, and letters, that are suitable for the specific purpose of representing coherent information. Text which has been prepared for presentation has been reduced to a primitive form through explicit specification of the characters and their placement in the presentation space. Control sequences which designate specific control functions may be embedded within the text. These functions extend the primitive form by applying specific characteristics to the text when it is presented. The collection of the graphic characters and control sequences is called Presentation Text, and the object that contains the Presentation Text is called the Presentation Text object. [PTOCA-2-001]

Presentation Text is associated with the output of text information. A Presentation Text object is the description of Presentation text for a portion of a document, the intended connotation being finished product or formatted output. This output version of text contained within the object is in the form specified by Presentation Text Object Content Architecture (PTOCA) and has been designed for direct output on devices, such as displays or printers. [PTOCA-2-002]

A Presentation Text object is a device-independent, self-defining representation of a two-dimensional presentation space, called the Presentation Text object space, or object space, which contains the Presentation Text data. The rules of PTOCA specify how the object space is constituted, what the boundaries are for text positioning, what the text content is, and how the text content is to be placed within the object space, using concepts such as sequential order, orientation, and position. [PTOCA-2-003]

**Architecture Note:** Note that when presentation text is processed in a MO:DCA environment where the Presentation Text Data Descriptor (PTD) is carried in the Active Environment Group (AEG) for the page, or when such text is processed in an IPDS environment, the Presentation Text object is bounded by the beginning of the page and the end of the page. This is sometimes called a text major environment. When the PTD is carried in the Object Environment Group (OEG) of a MO:DCA text object, the text object is bounded by the Begin Presentation Text (BPT) and End Presentation Text (EPT) structured fields. For such objects, the PTD in the AEG is ignored. [PTOCA-2-004]

The Presentation Text object space is defined on the Xp, Yp coordinate system, which is an orthogonal coordinate system based on the fourth quadrant of a standard Cartesian coordinate system. The object space is positioned within the data stream's object area. Coincident with the Xp, Yp coordinate system is the I, Bcoordinate system, which is a translation of the Xp, Yp coordinate system. [PTOCA-2-005]

The position of the elements in the object space is described in terms of the I, Bcoordinate system. The increasing I-axis is the inline direction, which is normally the reading direction of the text. The increasing B-axis is the baseline direction, which is normally the direction for adding lines of text. [PTOCA-2-006]

The basic elements of the object are the graphic characters which are identified as code points of a code page. The identification of graphic characters, their relationship to each other, and the relationship of the code point to the graphic character are given by the coded font selected to present the text. [PTOCA-2-007]

The relationship of the elements to the space they occupy is described in terms of their orientation, starting location, and units of measure. [PTOCA-2-008]

The positioning of the graphic characters on a line is accomplished by moving the presentation position. Graphic characters may be placed adjacent to one another or positioned anywhere in the object space through the use of control sequences defined by PTOCA. Control sequences have been defined to move the presentation position to another position, to move to the beginning of another line, to adjust the distance between two adjacent characters, to draw lines such as rules, to adjust the distance between lines, to change the font, to specify the color of characters and rules, to overstrike a text field with a specified character, and to underscore a text field. [PTOCA-2-009]

<!-- Page 26 -->

National Language Support (NLS) is handled in the level of formatting above the Presentation Text object. Font NLS support is provided in the font mapping function in the controlling environment. [PTOCA-2-010]

<!-- Page 27 -->
