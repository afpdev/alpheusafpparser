# Font Object Content Architecture (FOCA) Reference

**Advanced Function Presentation Consortium**
**Data Stream and Object Architectures**
**AFPC-0007-06**

---

**Copyright © AFP Consortium 1998, 2015, 2025**

**Note:** Before using this information, read the information in “Notices”. [FOCA-FM-001]

**AFPC-0007-06**
**Seventh Edition (October 2015)** [FOCA-FM-002]

This edition applies to Font Object Content Architecture (FOCA). It is the first edition produced by the AFP Consortium™ (AFPC™) and replaces and makes obsolete the previous edition (S544-3285-05) published by IBM®. This edition remains current until a new edition is published. This publication also applies to any subsequent releases of Advanced Function Presentation™ (AFP™) products that use the FOCA architecture until otherwise indicated in a new edition. [FOCA-FM-003]

Changes are indicated by a vertical bar to the left of the change. For a detailed list of changes, refer to “Changes in This Edition”. [FOCA-FM-004]

**Internet:** Visit our home page: [http://www.afpcinc.org](http://www.afpcinc.org) [FOCA-FM-005]

---

## Preface

This book describes the functions and services associated with Font Object Content Architecture (FOCA). The FOCA architecture describes semantics and terminology for font objects used in a variety of environments. Syntax is provided for AFP system fonts used in the MO:DCA™ environment; refer to “AFP System Font Resource”. The syntax for printer fonts used in the IPDS™ environment is fully described in the Intelligent Printer Data Stream™ Reference. [FOCA-FM-006]

**Note:** The FOCA architecture has been stabilized such that it can be fully used within AFP products and environments, but will not be extended. Many AFP products use either FOCA fonts, TrueType/OpenType fonts, or both. [FOCA-FM-007]

### Who Should Read This Book

This book is for systems programmers and other developers who need such information to develop or adapt a product or program to interoperate with other presentation products in an AFP environment. [FOCA-FM-008]

This book is a reference, not a tutorial. It complements individual product publications, but does not describe product implementations of the architecture. [FOCA-FM-009]

AFP data streams (MO:DCA and IPDS) provide the ability to manage and use a wide variety of font resources. These font resources can be classified into two major categories: coded fonts and data-object fonts. Coded fonts and their component parts are defined within the Font Object Content Architecture (FOCA) and are described in this book; data-object-fonts are defined elsewhere. A good description of data-object fonts and their component parts can be found in *How To Use TrueType and OpenType Fonts in an AFP System*. [FOCA-FM-010]

### AFP Consortium (AFPC)

The Advanced Function Presentation (AFP) architectures began as the strategic, general purpose document and information presentation architecture for the IBM Corporation. The first specifications and products go back to 1984. Although all of the components of the architecture have grown over the years, the major concepts of object-driven structures, print integrity, resource management, and support for high print speeds were built in from the start. [FOCA-FM-011]

In the early twenty-first century, IBM saw the need to enable applications to create color output that is independent from the device used for printing and to preserve color consistency, quality, and fidelity of the printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™ (AFPCC™). The goal was to extend the object architectures with support for full-color devices including support for comprehensive color management. The idea of doing this via a consortium consisting of the primary AFP architecture users was to build synergism with partners from across the relevant industries, such as hardware manufacturers that produce printers as well as software vendors of composition, work flow, viewer, and transform tools. Quickly more than 30 members came together in regular meetings and work group sessions to create the AFP Color Management Object Content Architecture™ (CMOCA™). A major milestone was reached by the AFP Color Consortium with the initial official release of the CMOCA specification in May 2006. [FOCA-FM-012]

Since the cooperation between the members of the AFP Color Consortium turned out to be very effective and valuable, it was decided to broaden the scope of the consortium efforts and IBM soon announced its plans to open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding member of the consortium was transferred to the InfoPrint® Solutions Company, an IBM/Ricoh® joint venture. In February 2009, the consortium was incorporated under a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures was transferred at that time to the AFP Consortium. [FOCA-FM-013]

---

## Publication History

The FOCA Reference was first published by IBM in 1988 and has had several enhancements and updates since that time. The first six editions were published by IBM Corporation and the current edition was published by the AFP Consortium. [FOCA-FM-014]

Note that the FOCA architecture has been stabilized such that it can be fully used within AFP products and environments, but will not be extended. Many AFP products use either FOCA fonts, TrueType/OpenType fonts, or both. [FOCA-FM-015]

*   **First Edition published by IBM Corporation** [FOCA-FM-016]
    S544-3285-00 dated December 1988
*   **Second Edition published by IBM Corporation** [FOCA-FM-017]
    S544-3285-01 dated February 1990
*   **Third Edition published by IBM Corporation** [FOCA-FM-018]
    S544-3285-02 dated June 1993
    This edition provides enhanced detail and clarifications:
    *   Information has been added to show the relationship between FOCA and the ISO® Font Information Interchange Standard (ISO/IEC 9541-1:1991). The concepts and parameters of FOCA can be mapped to, or transformed to, corresponding properties in the ISO font standard. Likewise, many of the concepts and properties defined by the ISO font standard can be mapped to, or transformed to, parameters of FOCA. [FOCA-FM-019]
    *   Chapter 1 has been rewritten to introduce the family of IBM Presentation Architectures. [FOCA-FM-020]
    *   Chapters 2–4 have been reorganized and edited to improve readability, arranging the information to allow the reader to move more freely from general introductory concepts to detailed architecture specifications. [FOCA-FM-021]
    *   Chapter 5 has been expanded to include new FOCA parameters and to clarify how those parameters apply to different writing systems (e.g., top to bottom). [FOCA-FM-022]
    *   Chapter 6 has been added to show the relationship between the font semantics defined in this document and the font syntax defined and interchanged by other architectures and products. [FOCA-FM-023]
    *   Appendix A has been replaced, adding a reference summary of the relationship between IBM Font Architecture parameters and ISO/IEC 9541 Font Information Interchange standard properties. [FOCA-FM-024]
    *   The Glossary has been extensively revised to include all terms applicable to the family of IBM Presentation Architectures. [FOCA-FM-025]
*   **Fourth Edition published by IBM Corporation** [FOCA-FM-026]
    S544-3285-03 dated April 1996
    This edition provides enhanced detail and the following major new additions:
    *   Syntax for AFP Host Fonts, previously contained in the *AFP Host Font Data Stream Reference* (IBM S544-3289-01), has been merged into chapter six. [FOCA-FM-027]
    *   Outline Font support and associated syntax [FOCA-FM-028]
    *   Miscellaneous technical clarifications and corrections (as marked) [FOCA-FM-029]
    *   An appendix, which describes the pattern technologies supported by the FOCA architecture [FOCA-FM-030]
*   **Fifth Edition published by IBM Corporation** [FOCA-FM-031]
    S544-3285-04 dated June 2000
    This edition provides clarifications and additional detail to support the FOCA products that IBM has provided since 1996. Changes include:
    *   GCUID and UCS Presentation encoding scheme (basic Unicode support) [FOCA-FM-032]
    *   300 pel fixed metrics [FOCA-FM-033]
    *   Metric Adjustment triplet for migrating from double-byte raster to outline fonts [FOCA-FM-034]
    *   Fully Qualified Name (FQN) triplet for identifying and referencing fonts objects [FOCA-FM-035]
    *   NOP structured field [FOCA-FM-036]
    *   FND “Typeface Name” field changed to “Typeface Description” [FOCA-FM-037]
    *   Typeface Name now allowed in FQN triplet on FND [FOCA-FM-038]
    *   Default character support for outline fonts [FOCA-FM-039]
    *   Triplet clarifications [FOCA-FM-040]
    *   Space character clarifications [FOCA-FM-041]
    *   Editorial clarifications [FOCA-FM-042]
*   **Sixth Edition published by IBM Corporation** [FOCA-FM-043]
    S544-3285-05 dated June 2005
    This edition provides clarifications and additional detailed description of the FOCA architecture. Changes include:
    *   Extended point-size range for relative metrics [FOCA-FM-044]
    *   Variable-space-enable flag in the Code Page Control [FOCA-FM-045]
    *   Pointsize terminology clarifications [FOCA-FM-046]
    *   Relationship of the FOCA architecture to TrueType and OpenType fonts [FOCA-FM-047]
    *   Unicode scalar values in code pages [FOCA-FM-048]
    *   Improved syntax tables [FOCA-FM-049]
    *   Editorial clarifications [FOCA-FM-050]

---

## How to Use This Book

This book is divided into seven chapters, with appendixes. Those readers who have little or no knowledge of fonts or font architecture concepts should read the introductory chapters first. Those readers who are experienced in using fonts in AFP implementations may wish to begin with “AFP System Font Resource”, and then use Chapter 5, “FOCA Parameters”, as a reference for parameter semantics. Those readers who are experienced in using fonts in other AFP product implementations may wish to begin with their product publications, and then use Chapter 5, “FOCA Parameters”, as a reference for parameter semantics. [FOCA-FM-051]

*   **Chapter 1, “A Presentation Architecture Perspective”** introduces the Presentation Architecture framework that is covered in this book. [FOCA-FM-052]
*   **Chapter 2, “Introduction to Fonts”** describes digitized fonts, text processing, font storage and accessing, font referencing, and FOCA font concepts. [FOCA-FM-053]
*   **Chapter 3, “Referencing Fonts”** explains the relationship of fonts to the various processes in document production. Included are topics of font selection and substitution, font identification, and document fidelity. [FOCA-FM-054]
*   **Chapter 4, “FOCA Overview”** explains, in more detail, FOCA font architecture concepts and character shape information. [FOCA-FM-055]
*   **Chapter 5, “FOCA Parameters”** provides semantic descriptions of the parameters used in font resources, references, and queries. [FOCA-FM-056]
*   **Chapter 6, “Font Interchange Formats”** provides information about the formats required for the interchange of font information. [FOCA-FM-057]
*   **Chapter 7, “Compliance Requirements”** defines the requirements for compliance to the architecture. [FOCA-FM-058]
*   **Appendix A, “AFP System Font Structured-Field and Triplet Summary”** provides tables of AFP system font data structures sorted by hexadecimal ID, with a link to the full description of each data structure. [FOCA-FM-059]
*   **Appendix B, “Mapping of ISO Parameters”** provides a summary cross-reference of all FOCA and ISO 9541 parameters. [FOCA-FM-060]
*   **Appendix C, “Pattern Technology Information”** provides information about the various shape representation formats supported by FOCA. [FOCA-FM-061]

The **“Glossary”** defines those font terms used in this book, which might also be required by other presentation architectures. [FOCA-FM-062]

---

## Related Publications

Several other publications can help you understand the architecture concepts described in this book. AFP Consortium publications and a few other AFP publications are available on the AFP Consortium web site, [www.afpcinc.org](http://www.afpcinc.org). [FOCA-FM-063]

### Table 1. AFP Consortium Architecture References

| AFP Architecture Publication | Book Identification |
| :--- | :--- |
| AFP Programming Guide and Line Data Reference | S544-3884 (IBM) [FOCA-FM-064]|
| Bar Code Object Content Architecture™ Reference | AFPC-0005 [FOCA-FM-065]|
| Color Management Object Content Architecture Reference | AFPC-0006 [FOCA-FM-066]|
| Font Object Content Architecture Reference | AFPC-0007 [FOCA-FM-067]|
| Graphics Object Content Architecture for Advanced Function Presentation Reference | AFPC-0008 [FOCA-FM-068]|
| Image Object Content Architecture Reference | AFPC-0003 [FOCA-FM-069]|
| Intelligent Printer Data Stream Reference | AFPC-0001 [FOCA-FM-070]|
| Metadata Object Content Architecture Reference | AFPC-0013 [FOCA-FM-071]|
| Mixed Object Document Content Architecture™ (MO:DCA) Reference | AFPC-0004 [FOCA-FM-072]|
| Presentation Text Object Content Architecture Reference | SC31-6803 (IBM) [FOCA-FM-073]|

### Table 2. Additional AFP Consortium Documentation

| AFPC Publication | Book Identification [FOCA-FM-074]|
| :--- | :--- |
| AFP Color Management Architecture™ (ACMA™) | AFPC-0015 [FOCA-FM-075]|
| AFPC Company Abbreviation Registry | AFPC-0012 [FOCA-FM-076]|
| AFPC Font Typeface Registry | AFPC-0016 [FOCA-FM-077]|
| BCOCA™ Frequently Asked Questions | AFPC-0011 [FOCA-FM-078]|
| MO:DCA-L:The OS/2 PM Metafile (.met) Format | AFPC-0014 [FOCA-FM-079]|
| Presentation Object Subsets for AFP | AFPC-0002 [FOCA-FM-080]|
| Recommended IPDS Values for Object Container Versions | AFPC-0017 [FOCA-FM-081]|

### Table 3. AFP Font-Related Documentation

| Publication | Book Identification [FOCA-FM-082]|
| :--- | :--- |
| Character Data Representation Architecture Reference and Registry; please refer to the online version for the most current information: [http://www-306.ibm.com/software/globalization/cdra/index.jsp](http://www-306.ibm.com/software/globalization/cdra/index.jsp) | SC09-2190 (IBM) [FOCA-FM-083]|
| Font Summary for AFP Font Collection | S544-5633 (IBM) [FOCA-FM-084]|
| How To Use TrueType and OpenType Fonts in an AFP System | G544-5876 (IBM) [FOCA-FM-085]|
| Technical Reference for Code Pages | S544-3802 (IBM) [FOCA-FM-086]|

### Table 4. UP3I™ Architecture Documentation

| UP3I Publication | Book Identification [FOCA-FM-087]|
| :--- | :--- |
| Universal Printer Pre- and Post-Processing Interface (UP3I) Specification | Available at [www.afpcinc.org](http://www.afpcinc.org) [FOCA-FM-088]|

---

## Changes in This Edition

Changes between this edition and the previous edition are marked in green by a vertical bar (|) in the left margin. [FOCA-FM-089]

This edition provides clarifications and additional detailed description of the FOCA architecture. Changes include:
*   Editorial clarifications [FOCA-FM-090]
*   IBM product-specific information has been removed and the term AFP or FOCA used in place of IBM where appropriate [FOCA-FM-091]
*   Information about the AFP Consortium [FOCA-FM-092]
*   Style changes to make this book more consistent with the other AFPC publications [FOCA-FM-093]

**Note:** The FOCA architecture has been stabilized such that it can be fully used within AFP products and environments, but will not be extended. Many AFP products use either FOCA fonts, TrueType/OpenType fonts, or both. [FOCA-FM-094]

---

## Table of Contents

*   [Preface](#preface) [FOCA-FM-095]
    *   [Who Should Read This Book](#who-should-read-this-book) [FOCA-FM-096]
    *   [AFP Consortium (AFPC)](#afp-consortium-afpc) [FOCA-FM-097]
*   [Publication History](#publication-history) [FOCA-FM-098]
*   [How to Use This Book](#how-to-use-this-book) [FOCA-FM-099]
*   [Related Publications](#related-publications) [FOCA-FM-100]
*   [Changes in This Edition](#changes-in-this-edition) [FOCA-FM-101]
*   [Chapter 1. A Presentation Architecture Perspective](Chapter_1.md) [FOCA-FM-102]
*   [Chapter 2. Introduction to Fonts](Chapter_2.md) [FOCA-FM-103]
*   [Chapter 3. Referencing Fonts](Chapter_3.md) [FOCA-FM-104]
*   [Chapter 4. FOCA Overview](Chapter_4.md) [FOCA-FM-105]
*   [Chapter 5. FOCA Parameters](Chapter_5.md) [FOCA-FM-106]
*   [Chapter 6. Font Interchange Formats](Chapter_6.md) [FOCA-FM-107]
*   [Chapter 7. Compliance Requirements](Chapter_7.md) [FOCA-FM-108]
*   [Appendix A. AFP System Font Structured-Field and Triplet Summary](Appendix_A.md) [FOCA-FM-109]
*   [Appendix B. Mapping of ISO Parameters](Appendix_B.md) [FOCA-FM-110]
*   [Appendix C. Pattern Technology Information](Appendix_C.md) [FOCA-FM-111]
*   [Glossary](Glossary.md) [FOCA-FM-112]
