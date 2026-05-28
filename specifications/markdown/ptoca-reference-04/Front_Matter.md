# Front Matter

<!-- Page 1 -->

**Advanced Function Presentation Consortium**
**Data Stream and Object Architectures** [PTOCA-FM-001]

**Presentation Text Object Content Architecture Reference** [PTOCA-FM-002]

**AFPC-0009-04**

<!-- Page 2 -->

Copyright © AFP Consortium 1997, 2025 ii

**Note:** Before using this information, read the information in "Notices". [PTOCA-FM-003]

AFPC-0009-04
Fifth Edition (February 2025) [PTOCA-FM-004]

This edition applies to the Presentation Text Object Content Architecture (PTOCA). It is the second edition produced by the AFP Consortium™ (AFPC™) and replaces and makes obsolete the previous edition, AFPC-0009-03. This edition remains current until a new edition is published. [PTOCA-FM-005]

Technical changes are indicated in green, with a green vertical bar to the left of the change. Editorial changes that have no technical significance are not noted. For a detailed list of changes, see "Summary of Changes". [PTOCA-FM-006]

**Internet**
Visit our home page: [www.afpconsortium.org](http://www.afpconsortium.org) [PTOCA-FM-007]

<!-- Page 3 -->

## Preface

This book describes the functions and services associated with the Presentation Text Object Content Architecture (PTOCA) architecture. [PTOCA-FM-008]

It is a reference, not a tutorial. It complements individual product publications, but does not describe product implementations of the architecture. [PTOCA-FM-009]

### Who Should Read This Book

This book is for systems programmers and other developers who develop or adapt a product or program to interoperate with other presentation products in an Advanced Function Presentation™ environment. [PTOCA-FM-010]

### AFP Consortium (AFPC)

The AFP Consortium is an international group bringing together voices from across the printing and presentation industry to keep the AFP™ architecture up to date and continually improving. AFP Consortium members, often market competitors, work together to ensure this stable, efficient, flexible architecture continues to thrive, even as the world of printing and presentation changes. [PTOCA-FM-011]

The Advanced Function Presentation (AFP) architectures began as the strategic, general purpose document and information presentation architecture for the IBM® Corporation. The first specifications and products go back to 1984. Although all of the components of the architecture have grown over the years, the major concepts of object-driven structures, print integrity, resource management, and support for high print speeds were built in from the start. [PTOCA-FM-012]

In the early twenty-first century, IBM saw the need to enable applications to create color output that is independent from the device used for printing and to preserve color consistency, quality, and fidelity of the printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™ (AFPCC™). The goal was to extend the object architectures with support for full-color devices including support for comprehensive color management. The idea of doing this via a consortium consisting of the primary AFP architecture users was to build synergism with partners from across the relevant industries, such as hardware manufacturers that produce printers as well as software vendors of composition, workflow, viewer, and transform tools. Quickly more than 30 members came together in regular meetings and work group sessions to create the AFP Color Management Object Content Architecture™ (CMOCA™). A major milestone was reached by the AFP Color Consortium with the initial official release of the CMOCA specification in May 2006. [PTOCA-FM-013]

Since the cooperation between the members of the AFP Color Consortium turned out to be very effective and valuable, it was decided to broaden the scope of the consortium efforts and IBM soon announced its plans to open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding member of the consortium was transferred to the InfoPrint® Solutions Company, an IBM/Ricoh® joint venture; currently Ricoh holds the founding member position. In February 2009, the consortium was incorporated under a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures was transferred at that time to the AFP Consortium. [PTOCA-FM-014]

### How to Use This Book

This book is divided into six chapters, three appendixes, and a glossary. [PTOCA-FM-015]

<!-- Page 4 -->

*   **Chapter 1, "A Presentation Architecture Perspective"** introduces the AFP presentation architectures and positions Presentation Text Object Content Architecture as a strategic object content architecture. [PTOCA-FM-016]
*   **Chapter 2, "Introduction to PTOCA"** briefly states the purpose and function of PTOCA. [PTOCA-FM-017]
*   **Chapter 3, "Overview of PTOCA"** introduces the concepts that form the basis of PTOCA and provides a brief description of the data structures. [PTOCA-FM-018]
*   **Chapter 4, "Data Structures in PTOCA"** provides the detailed syntax, semantics, and pragmatics of the data structures found in PTOCA. [PTOCA-FM-019]
*   **Chapter 5, "Exception Handling in PTOCA"** describes how exceptions are handled in PTOCA and lists the exception codes. [PTOCA-FM-020]
*   **Chapter 6, "Compliance with PTOCA"** describes how products may be valid generators or receivers of PTOCA. [PTOCA-FM-021]
*   **Appendix A, "MO:DCA Environment"** describes the Presentation Text object in the context of a MO:DCA™ data stream. [PTOCA-FM-022]
*   **Appendix B, "IPDS Environment"** describes the Presentation Text object in the context of an IPDS™ data stream. [PTOCA-FM-023]
*   **Appendix C, "PTOCA Retired Functions"** describes the retired PTOCA functions. [PTOCA-FM-024]
*   **The "Glossary"** defines some of the terms used within this book. [PTOCA-FM-025]

### How to Read the Syntax Diagrams

Throughout this book, syntax is described using the structure defined below. Six basic data types are used in the syntax descriptions: [PTOCA-FM-026]

*   **CODE**: Architected constant [PTOCA-FM-027]
*   **CHAR**: Character string, which may consist of any code points [PTOCA-FM-028]
*   **BITS**: Bit string [PTOCA-FM-029]
*   **UBIN**: Unsigned binary [PTOCA-FM-030]
*   **SBIN**: Signed binary [PTOCA-FM-031]
*   **UNDF**: Undefined type [PTOCA-FM-032]

Syntax for PTOCA is shown in tables like the following: [PTOCA-FM-033]

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| The field's offset, data type, or both | Name of field if applicable | Range of valid values if applicable | Meaning or purpose of the data element [PTOCA-FM-034]| | | | |

*   **M/O**: **M** means mandatory. **O** means optional. [PTOCA-FM-035]
*   **Def**: **Y** means that a default value is defined for the field. **N** means that there is no default value defined for the field. [PTOCA-FM-036]
*   **Ind**: **Y** means that the field defaults to a hierarchical default value when the default indicator (X'F..F') is present. **N** means that the default indicator semantic is not valid for the field. [PTOCA-FM-037]

The following is an example of PTOCA syntax for the Begin Line (BLN) control sequence as it appears in this book: [PTOCA-FM-038]

<!-- Page 5 -->

| Offset | Type | Name | Range | Meaning | M/O | Def | Ind |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | PREFIX | X'2B' | Control sequence Prefix | M | N | N [PTOCA-FM-039]|
| 1 | CODE | CLASS | X'D3' | Control sequence class | M | N | N [PTOCA-FM-040]|
| 2 | UBIN | LENGTH | 2 | Control sequence length | M | N | N [PTOCA-FM-041]|
| 3 | CODE | TYPE | X'D8' – X'D9' | Control sequence function type | M | N | N [PTOCA-FM-042]|

Please refer to "Control Sequence Detailed Descriptions" for a more detailed description of PTOCA syntax. [PTOCA-FM-043]

<!-- Page 6 -->

### Related Publications

Several other publications can help you understand the architecture concepts described in this book. The AFP Consortium publications and other AFP publications below are available on the AFP Consortium web site, [www.afpconsortium.org](http://www.afpconsortium.org). [PTOCA-FM-044]

**Table 1. AFP Consortium Architecture References** [PTOCA-FM-045]

| AFP Architecture Publication | Book Identification |
| :--- | :--- |
| AFP Programming Guide and Line Data Reference | AFPC-0010 [PTOCA-FM-046]|
| Bar Code Object Content Architecture™ Reference | AFPC-0005 [PTOCA-FM-047]|
| Color Management Object Content Architecture Reference | AFPC-0006 [PTOCA-FM-048]|
| Font Object Content Architecture Reference | AFPC-0007 [PTOCA-FM-049]|
| Graphics Object Content Architecture for Advanced Function Presentation Reference | AFPC-0008 [PTOCA-FM-050]|
| Image Object Content Architecture Reference | AFPC-0003 [PTOCA-FM-051]|
| Intelligent Printer Data Stream™ (IPDS) Reference | AFPC-0001 [PTOCA-FM-052]|
| Metadata Object Content Architecture Reference | AFPC-0013 [PTOCA-FM-053]|
| Mixed Object Document Content Architecture™ (MO:DCA) Reference | AFPC-0004 [PTOCA-FM-054]|
| Presentation Text Object Content Architecture Reference | AFPC-0009 [PTOCA-FM-055]|

**Table 2. Additional AFP Consortium Documentation** [PTOCA-FM-056]

| AFPC Publication | Book Identification |
| :--- | :--- |
| AFP Color Management Architecture™ (ACMA™) | G550–1046 (IBM) [PTOCA-FM-057]|
| AFPC Company Abbreviation Registry | AFPC-0012 [PTOCA-FM-058]|
| AFPC Font Typeface Registry | AFPC-0016 [PTOCA-FM-059]|
| BCOCA™ Frequently Asked Questions | AFPC-0011 [PTOCA-FM-060]|
| Metadata Guide for AFP | AFPC-0018 [PTOCA-FM-061]|
| MO:DCA-L:The OS/2 PM Metafile (.met) Format | AFPC-0014 [PTOCA-FM-062]|
| Presentation Object Subsets for AFP | AFPC-0002 [PTOCA-FM-063]|
| Recommended IPDS Values for Object Container Versions | AFPC-0017 [PTOCA-FM-064]|

**Table 3. AFP Font-Related Documentation** [PTOCA-FM-065]

| Publication | Book Identification |
| :--- | :--- |
| Character Data Representation Architecture Reference and Registry | SC09-2190 (IBM) [PTOCA-FM-066]|
| Font Summary for AFP Font Collection | S544-5633 (IBM) [PTOCA-FM-067]|
| Using OpenType Fonts in an AFP System | G544-5876 (IBM) [PTOCA-FM-068]|
| Technical Reference for Code Pages | S544-3802 (IBM) [PTOCA-FM-069]|

<!-- Page 7 -->

## Summary of Changes

This fifth edition of the PTOCA Reference contains the following changes: [PTOCA-FM-070]

*   Support for encrypted text data for secure printing and presentation [PTOCA-FM-071]
*   Glossary entries were updated to the latest common level [PTOCA-FM-072]
*   Small updates were made to correct errors and increase consistency and readability [PTOCA-FM-073]

As stated in the edition notice, the additions are marked in this publication in green, with green revision bars located on the left-hand side of a page. [PTOCA-FM-074]

<!-- Page 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 omitted as they were TOC or blank --> [PTOCA-FM-075]
